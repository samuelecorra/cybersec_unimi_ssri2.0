import assert from 'node:assert/strict';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { scanDir } from '../vite-plugin-lessons.js';
import {
  HASH_ROUTE_MAX_LENGTH,
  buildHashRouteIndex,
  createHashPath,
  findNearestDirectory,
  resolveHashRoute,
  resolveKnownRoute,
} from '../src/utils/hashRouting.js';

const lessonPath = 'cybersecurity/anno1/3_Programmazione/UD 1/L1 - A&B + introduzione.md';
const webLessonPath = 'cybersecurity/anno2/Corso web/Lab/index.html';
const tree = [{
  type: 'dir',
  name: 'cybersecurity',
  path: 'cybersecurity',
  children: [{
    type: 'dir',
    name: 'anno1',
    path: 'cybersecurity/anno1',
    children: [{
      type: 'dir',
      name: '3_Programmazione',
      path: 'cybersecurity/anno1/3_Programmazione',
      children: [{
        type: 'dir',
        name: 'UD 1',
        path: 'cybersecurity/anno1/3_Programmazione/UD 1',
        children: [{ type: 'file', name: 'L1 - A&B + introduzione.md', path: lessonPath }],
      }],
    }],
  }, {
    type: 'dir',
    name: 'anno2',
    path: 'cybersecurity/anno2',
    children: [{
      type: 'dir',
      name: 'Corso web',
      path: 'cybersecurity/anno2/Corso web',
      children: [{ type: 'web-lesson', name: 'Lab', path: webLessonPath }],
    }],
  }],
}];

const index = buildHashRouteIndex(tree);
const lessonHash = createHashPath(lessonPath);
assert.equal(lessonHash, '#/cybersecurity/anno1/3_Programmazione/UD%201/L1%20-%20A%26B%20%2B%20introduzione.md');

const lessonRoute = resolveHashRoute(lessonHash, index);
assert.equal(lessonRoute.kind, 'file');
assert.equal(lessonRoute.path, lessonPath);
assert.equal(lessonRoute.canonicalHash, lessonHash);

const subjectRoute = resolveHashRoute('#/cybersecurity/anno1/3_Programmazione/', index);
assert.equal(subjectRoute.kind, 'directory');
assert.equal(subjectRoute.canonicalHash, '#/cybersecurity/anno1/3_Programmazione');
assert.deepEqual(subjectRoute.segments, ['cybersecurity', 'anno1', '3_Programmazione']);

assert.equal(resolveHashRoute('', index).kind, 'unset');
assert.equal(resolveHashRoute('#/', index).kind, 'home');
assert.equal(resolveKnownRoute('file', lessonPath, index)?.kind, 'file');
assert.equal(resolveKnownRoute('directory', 'cybersecurity/anno1', index)?.kind, 'directory');
assert.equal(resolveKnownRoute('file', 'cybersecurity/anno1/inesistente.md', index), null);
assert.deepEqual(findNearestDirectory(lessonPath, index), [
  'cybersecurity', 'anno1', '3_Programmazione', 'UD 1',
]);
assert.deepEqual(findNearestDirectory(webLessonPath, index), [
  'cybersecurity', 'anno2', 'Corso web',
]);

const invalidHashes = [
  '#javascript:alert(1)',
  '#/cybersecurity/../segreti',
  '#/cybersecurity/%2e%2e/segreti',
  '#/%2Fetc/passwd',
  '#/cybersecurity%5Canno1',
  '#/cybersecurity//anno1',
  '#/cybersecurity/%00/anno1',
  '#/%E0%A4%A',
  '#/cybersecurity/anno9',
  `#/${'a'.repeat(HASH_ROUTE_MAX_LENGTH)}`,
];
for (const hash of invalidHashes) assert.equal(resolveHashRoute(hash, index).kind, 'invalid', hash);

assert.throws(() => createHashPath(['cybersecurity', '..', 'segreti']), /non validi/);

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const realTree = scanDir(path.join(repoRoot, 'lessons'));
const realIndex = buildHashRouteIndex(realTree);
for (const directoryPath of realIndex.directories) {
  const route = resolveHashRoute(createHashPath(directoryPath), realIndex);
  assert.equal(route.kind, 'directory', directoryPath);
  assert.equal(route.path, directoryPath, directoryPath);
}
for (const filePath of realIndex.files) {
  const route = resolveHashRoute(createHashPath(filePath), realIndex);
  assert.equal(route.kind, 'file', filePath);
  assert.equal(route.path, filePath, filePath);
}

console.log(`HASH_ROUTING_UNIT_OK dirs=${realIndex.directories.size} files=${realIndex.files.size}`);
