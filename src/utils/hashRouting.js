const MAX_HASH_LENGTH = 16_384;
const FORBIDDEN_SEGMENT_CHARS = /[\\/\u0000-\u001f\u007f]/;

function isFileNode(node) {
  return node?.type === 'file' || node?.type === 'web-lesson';
}

function isSafeSegment(segment) {
  return typeof segment === 'string'
    && segment.length > 0
    && segment !== '.'
    && segment !== '..'
    && !FORBIDDEN_SEGMENT_CHARS.test(segment);
}

function splitInternalPath(path) {
  const segments = Array.isArray(path) ? [...path] : String(path ?? '').split('/');
  if (segments.length === 1 && segments[0] === '') return [];
  return segments.every(isSafeSegment) ? segments : null;
}

function makeRoute(kind, path = '') {
  const segments = path ? path.split('/') : [];
  return Object.freeze({
    kind,
    path,
    segments: Object.freeze(segments),
    canonicalHash: createHashPath(segments),
  });
}

export function buildHashRouteIndex(tree) {
  const directories = new Set();
  const files = new Set();

  function visit(nodes) {
    for (const node of nodes ?? []) {
      if (!node || typeof node.path !== 'string') continue;
      const segments = splitInternalPath(node.path);
      if (!segments) continue;

      if (node.type === 'dir') {
        if (directories.has(node.path) || files.has(node.path)) {
          throw new Error(`Percorso duplicato nel manifest: ${node.path}`);
        }
        directories.add(node.path);
        visit(node.children);
      } else if (isFileNode(node)) {
        if (directories.has(node.path) || files.has(node.path)) {
          throw new Error(`Percorso duplicato nel manifest: ${node.path}`);
        }
        files.add(node.path);
      }
    }
  }

  visit(tree);
  return Object.freeze({ directories, files });
}

export function createHashPath(path) {
  const segments = splitInternalPath(path);
  if (!segments) throw new TypeError('Il percorso contiene segmenti non validi');
  if (segments.length === 0) return '#/';
  return `#/${segments.map(segment => encodeURIComponent(segment)).join('/')}`;
}

export function resolveHashRoute(hash, index) {
  if (hash === '' || hash === '#') {
    return Object.freeze({ kind: 'unset', path: '', segments: Object.freeze([]), canonicalHash: '' });
  }
  if (typeof hash !== 'string' || hash.length > MAX_HASH_LENGTH || !hash.startsWith('#/')) {
    return Object.freeze({ kind: 'invalid' });
  }

  let encodedPath = hash.slice(2);
  if (encodedPath === '') return makeRoute('home');
  if (encodedPath.endsWith('/')) encodedPath = encodedPath.slice(0, -1);
  if (encodedPath === '' || encodedPath.split('/').some(segment => segment === '')) {
    return Object.freeze({ kind: 'invalid' });
  }

  let segments;
  try {
    segments = encodedPath.split('/').map(segment => decodeURIComponent(segment));
  } catch {
    return Object.freeze({ kind: 'invalid' });
  }

  if (!segments.every(isSafeSegment)) return Object.freeze({ kind: 'invalid' });

  const path = segments.join('/');
  if (index.files.has(path)) return makeRoute('file', path);
  if (index.directories.has(path)) return makeRoute('directory', path);
  return Object.freeze({ kind: 'invalid' });
}

export function resolveKnownRoute(kind, path, index) {
  if (kind === 'home') return makeRoute('home');
  if (kind !== 'directory' && kind !== 'file') return null;
  const segments = splitInternalPath(path);
  if (!segments || segments.length === 0) return null;
  const normalizedPath = segments.join('/');
  const knownPaths = kind === 'file' ? index.files : index.directories;
  return knownPaths.has(normalizedPath) ? makeRoute(kind, normalizedPath) : null;
}

export function findNearestDirectory(filePath, index) {
  const segments = splitInternalPath(filePath);
  if (!segments) return [];
  segments.pop();
  while (segments.length > 0) {
    if (index.directories.has(segments.join('/'))) return segments;
    segments.pop();
  }
  return [];
}

export const HASH_ROUTE_MAX_LENGTH = MAX_HASH_LENGTH;
