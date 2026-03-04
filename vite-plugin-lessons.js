import fs from 'node:fs';
import path from 'node:path';

const LESSONS_DIR = 'lessons';
const VIRTUAL_TREE = 'virtual:lesson-tree';
const VIRTUAL_SEARCH = 'virtual:lesson-search';
const RESOLVED_TREE = '\0' + VIRTUAL_TREE;
const RESOLVED_SEARCH = '\0' + VIRTUAL_SEARCH;

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
    return a.name.localeCompare(b.name, 'it');
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

export default function lessonsPlugin() {
  let lessonsPath;

  return {
    name: 'vite-plugin-lessons',

    configResolved(config) {
      lessonsPath = path.resolve(config.root, LESSONS_DIR);
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

    configureServer(server) {
      server.middlewares.use((req, res, next) => {
        if (req.url?.startsWith('/api/file?path=')) {
          const filePath = decodeURIComponent(req.url.replace('/api/file?path=', ''));
          const safePath = path.normalize(filePath).replace(/\.\./g, '');
          const fullPath = path.join(lessonsPath, safePath);

          if (!fullPath.startsWith(lessonsPath)) {
            res.statusCode = 403;
            res.end('Forbidden');
            return;
          }

          try {
            const content = fs.readFileSync(fullPath, 'utf-8');
            res.setHeader('Content-Type', 'text/plain; charset=utf-8');
            res.end(content);
          } catch {
            res.statusCode = 404;
            res.end('Not found');
          }
          return;
        }

        if (req.url?.startsWith('/api/image?path=')) {
          const imgPath = decodeURIComponent(req.url.replace('/api/image?path=', ''));
          const safePath = path.normalize(imgPath).replace(/\.\./g, '');
          const fullPath = path.join(lessonsPath, safePath);

          if (!fullPath.startsWith(lessonsPath)) {
            res.statusCode = 403;
            res.end('Forbidden');
            return;
          }

          try {
            const data = fs.readFileSync(fullPath);
            const ext = path.extname(fullPath).toLowerCase();
            const mime = {
              '.png': 'image/png',
              '.jpg': 'image/jpeg',
              '.jpeg': 'image/jpeg',
              '.gif': 'image/gif',
              '.svg': 'image/svg+xml',
              '.webp': 'image/webp',
            }[ext] || 'application/octet-stream';
            res.setHeader('Content-Type', mime);
            res.end(data);
          } catch {
            res.statusCode = 404;
            res.end('Not found');
          }
          return;
        }

        next();
      });
    },
  };
}
