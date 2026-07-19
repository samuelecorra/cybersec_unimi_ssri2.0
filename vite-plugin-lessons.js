import fs from 'node:fs';
import path from 'node:path';
import {
  getFileExtension,
  getFileKind,
  getSourceFileType,
} from './src/utils/fileTypes.js';

const LESSONS_DIR = 'lessons';
const VIRTUAL_TREE = 'virtual:lesson-tree';
const VIRTUAL_SEARCH = 'virtual:lesson-search';
const RESOLVED_TREE = '\0' + VIRTUAL_TREE;
const RESOLVED_SEARCH = '\0' + VIRTUAL_SEARCH;
const PROGRAMMING_PREFIX = 'cybersecurity/anno1/3_Programmazione/';

// Extensions to copy to dist for static hosting
export const STATIC_EXTS = new Set([
  '.md', '.png', '.jpg', '.jpeg', '.gif', '.svg', '.webp',
  '.html', '.js', '.css', '.pdf',
]);

export const MIME_TYPES = Object.freeze({
  '.md': 'text/plain; charset=utf-8',
  '.html': 'text/plain; charset=utf-8',
  '.js': 'text/plain; charset=utf-8',
  '.css': 'text/plain; charset=utf-8',
  '.c': 'text/plain; charset=utf-8',
  '.h': 'text/plain; charset=utf-8',
  '.java': 'text/plain; charset=utf-8',
  '.txt': 'text/plain; charset=utf-8',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.gif': 'image/gif',
  '.svg': 'image/svg+xml',
  '.webp': 'image/webp',
  '.wav': 'audio/wav',
  '.pdf': 'application/pdf',
});

export function isTreeFile(fileName, relativePath = fileName) {
  const kind = getFileKind(fileName);
  if (['markdown', 'pdf'].includes(kind)) return true;
  return ['source', 'image', 'audio'].includes(kind)
    && relativePath.split(path.sep).join('/').startsWith(PROGRAMMING_PREFIX);
}

export function isPublishedFile(fileName, relativePath = fileName) {
  if (STATIC_EXTS.has(getFileExtension(fileName))) return true;
  const kind = getFileKind(fileName);
  return ['source', 'audio'].includes(kind)
    && relativePath.split(path.sep).join('/').startsWith(PROGRAMMING_PREFIX);
}

export function scanDir(dir, base = '') {
  const entries = fs.readdirSync(dir, { withFileTypes: true });
  const result = [];
  for (const entry of entries) {
    const rel = base ? `${base}/${entry.name}` : entry.name;
    if (entry.isDirectory()) {
      const subDir = path.join(dir, entry.name);
      const subNames = fs.readdirSync(subDir);
      if (subNames.includes('index.html')) {
        result.push({ type: 'web-lesson', name: entry.name, path: `${rel}/index.html` });
      } else {
        const children = scanDir(subDir, rel);
        if (children.length > 0) {
          result.push({ type: 'dir', name: entry.name, path: rel, children });
        }
      }
    } else if (isTreeFile(entry.name, rel)) {
      const stat = fs.statSync(path.join(dir, entry.name));
      const sourceType = getSourceFileType(entry.name);
      result.push({
        type: 'file',
        name: entry.name,
        path: rel,
        size: stat.size,
        kind: getFileKind(entry.name),
        ...(sourceType ? { language: sourceType.language, category: sourceType.category } : {}),
      });
    }
  }
  result.sort((a, b) => {
    const aLeaf = a.type !== 'dir';
    const bLeaf = b.type !== 'dir';
    if (aLeaf !== bLeaf) return aLeaf ? 1 : -1;
    return a.name.localeCompare(b.name, 'it', { numeric: true });
  });
  return result;
}

export function flattenFiles(tree, list = []) {
  for (const node of tree) {
    if (node.type === 'file' || node.type === 'web-lesson') {
      list.push(node.path);
    } else if (node.children) {
      flattenFiles(node.children, list);
    }
  }
  return list;
}

export function buildSearchIndex(lessonsPath, files) {
  const index = [];
  for (const filePath of files) {
    const kind = getFileKind(filePath);
    if (kind !== 'markdown' && kind !== 'source') continue;
    try {
      const fullPath = path.join(lessonsPath, filePath);
      const content = fs.readFileSync(fullPath, 'utf-8');
      const headings = [];
      if (kind === 'markdown') {
        const lines = content.split('\n');
        for (const line of lines) {
          const match = line.match(/^#{1,4}\s+(.+)/);
          if (match) headings.push(match[1].trim());
        }
      }
      const previewLength = kind === 'source' ? 800 : 300;
      const preview = content.slice(0, previewLength).replace(/\s+/g, ' ');
      index.push({ path: filePath, headings, preview, kind });
    } catch {
      index.push({ path: filePath, headings: [], preview: '', kind });
    }
  }
  return index;
}

/** Recursively copy published lesson and support files from src to dest. */
export function copyStaticFiles(srcDir, destDir, rootDir = srcDir) {
  const entries = fs.readdirSync(srcDir, { withFileTypes: true });
  for (const entry of entries) {
    const srcPath = path.join(srcDir, entry.name);
    const destPath = path.join(destDir, entry.name);
    if (entry.isDirectory()) {
      copyStaticFiles(srcPath, destPath, rootDir);
    } else {
      const relativePath = path.relative(rootDir, srcPath);
      if (isPublishedFile(entry.name, relativePath)) {
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

        let filePath;
        try {
          const requestPath = req.url.split('?', 1)[0];
          filePath = decodeURIComponent(requestPath.slice(prefix.length));
        } catch {
          res.statusCode = 400;
          res.end('Bad request');
          return;
        }

        const fullPath = path.resolve(lessonsPath, filePath);
        const relativePath = path.relative(lessonsPath, fullPath);
        if (relativePath.startsWith('..') || path.isAbsolute(relativePath)) {
          res.statusCode = 403;
          res.end('Forbidden');
          return;
        }

        try {
          const ext = getFileExtension(fullPath);
          const contentType = MIME_TYPES[ext] || 'application/octet-stream';
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
