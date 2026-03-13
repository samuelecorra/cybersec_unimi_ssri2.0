import fs from 'node:fs';
import path from 'node:path';

const LESSONS_DIR = 'lessons';
const VIRTUAL_TREE = 'virtual:lesson-tree';
const VIRTUAL_SEARCH = 'virtual:lesson-search';
const RESOLVED_TREE = '\0' + VIRTUAL_TREE;
const RESOLVED_SEARCH = '\0' + VIRTUAL_SEARCH;

// Extensions to copy to dist for static hosting
const STATIC_EXTS = new Set([
  '.md', '.png', '.jpg', '.jpeg', '.gif', '.svg', '.webp',
]);

function scanDir(dir, base = '') {
  const entries = fs.readdirSync(dir, { withFileTypes: true });
  const result = [];
  for (const entry of entries) {
    const rel = base ? `${base}/${entry.name}` : entry.name;
    if (entry.isDirectory()) {
      const children = scanDir(path.join(dir, entry.name), rel);
      if (children.length > 0) {
        result.push({ type: 'dir', name: entry.name, path: rel, children });
      }
    } else if (entry.name.endsWith('.md')) {
      const stat = fs.statSync(path.join(dir, entry.name));
      result.push({ type: 'file', name: entry.name, path: rel, size: stat.size });
    }
  }
  result.sort((a, b) => {
    if (a.type !== b.type) return a.type === 'dir' ? -1 : 1;
    return a.name.localeCompare(b.name, 'it', { numeric: true });
  });
  return result;
}

function flattenFiles(tree, list = []) {
  for (const node of tree) {
    if (node.type === 'file') {
      list.push(node.path);
    } else if (node.children) {
      flattenFiles(node.children, list);
    }
  }
  return list;
}

function buildSearchIndex(lessonsPath, files) {
  const index = [];
  for (const filePath of files) {
    try {
      const fullPath = path.join(lessonsPath, filePath);
      const content = fs.readFileSync(fullPath, 'utf-8');
      const headings = [];
      const lines = content.split('\n');
      for (const line of lines) {
        const match = line.match(/^#{1,4}\s+(.+)/);
        if (match) headings.push(match[1].trim());
      }
      const preview = content.slice(0, 300).replace(/\n/g, ' ');
      index.push({ path: filePath, headings, preview });
    } catch {
      index.push({ path: filePath, headings: [], preview: '' });
    }
  }
  return index;
}

/** Recursively copy .md and image files from src to dest */
function copyStaticFiles(srcDir, destDir) {
  const entries = fs.readdirSync(srcDir, { withFileTypes: true });
  for (const entry of entries) {
    const srcPath = path.join(srcDir, entry.name);
    const destPath = path.join(destDir, entry.name);
    if (entry.isDirectory()) {
      copyStaticFiles(srcPath, destPath);
    } else {
      const ext = path.extname(entry.name).toLowerCase();
      if (STATIC_EXTS.has(ext)) {
        fs.mkdirSync(path.dirname(destPath), { recursive: true });
        fs.copyFileSync(srcPath, destPath);
      }
    }
  }
}

export default function lessonsPlugin() {
  let lessonsPath;
  let outDir;

  return {
    name: 'vite-plugin-lessons',

    configResolved(config) {
      lessonsPath = path.resolve(config.root, LESSONS_DIR);
      outDir = path.resolve(config.root, config.build.outDir);
    },

    resolveId(id) {
      if (id === VIRTUAL_TREE) return RESOLVED_TREE;
      if (id === VIRTUAL_SEARCH) return RESOLVED_SEARCH;
    },

    load(id) {
      if (id === RESOLVED_TREE) {
        const tree = scanDir(lessonsPath);
        return `export default ${JSON.stringify(tree)};`;
      }
      if (id === RESOLVED_SEARCH) {
        const tree = scanDir(lessonsPath);
        const files = flattenFiles(tree);
        const index = buildSearchIndex(lessonsPath, files);
        return `export default ${JSON.stringify(index)};`;
      }
    },

    // Dev server: serve lesson files statically at /lessons/...
    configureServer(server) {
      server.middlewares.use((req, res, next) => {
        const prefix = '/lessons/';
        if (!req.url?.startsWith(prefix)) return next();

        const filePath = decodeURIComponent(req.url.slice(prefix.length));
        const safePath = path.normalize(filePath).replace(/\.\.\\/g, '').replace(/\.\.\//g, '');
        const fullPath = path.join(lessonsPath, safePath);

        if (!fullPath.startsWith(lessonsPath)) {
          res.statusCode = 403;
          res.end('Forbidden');
          return;
        }

        try {
          const ext = path.extname(fullPath).toLowerCase();
          const mimeTypes = {
            '.md': 'text/plain; charset=utf-8',
            '.png': 'image/png',
            '.jpg': 'image/jpeg',
            '.jpeg': 'image/jpeg',
            '.gif': 'image/gif',
            '.svg': 'image/svg+xml',
            '.webp': 'image/webp',
          };
          const contentType = mimeTypes[ext] || 'application/octet-stream';
          const data = fs.readFileSync(fullPath);
          res.setHeader('Content-Type', contentType);
          res.setHeader('Cache-Control', 'no-cache');
          res.end(data);
        } catch {
          res.statusCode = 404;
          res.end('Not found');
        }
      });
    },

    // Build: copy .md + images into dist/lessons/
    closeBundle() {
      const destDir = path.join(outDir, 'lessons');
      console.log('Copying lesson files to dist/lessons/ ...');
      copyStaticFiles(lessonsPath, destDir);
      // Count copied files
      let count = 0;
      (function countFiles(dir) {
        if (!fs.existsSync(dir)) return;
        for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
          if (entry.isDirectory()) countFiles(path.join(dir, entry.name));
          else count++;
        }
      })(destDir);
      console.log(`Copied ${count} lesson files to dist/lessons/`);
    },
  };
}
