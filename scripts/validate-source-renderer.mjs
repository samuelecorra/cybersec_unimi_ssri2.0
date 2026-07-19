import assert from 'node:assert/strict';
import fs from 'node:fs';
import path from 'node:path';
import {
  buildSearchIndex,
  flattenFiles,
  isPublishedFile,
  MIME_TYPES,
  scanDir,
} from '../vite-plugin-lessons.js';
import {
  getFileKind,
  getSourceFileType,
  isSourceFile,
} from '../src/utils/fileTypes.js';

const repoRoot = path.resolve(process.cwd());
const lessonsRoot = path.join(repoRoot, 'lessons');
const coursePrefix = 'cybersecurity/anno1/3_Programmazione/';
const courseRoot = path.join(lessonsRoot, coursePrefix);
const checkDist = process.argv.includes('--dist');

function walkFiles(dir, base = dir, result = []) {
  for (const entry of fs.readdirSync(dir, { withFileTypes: true })) {
    const absolutePath = path.join(dir, entry.name);
    if (entry.isDirectory()) {
      walkFiles(absolutePath, base, result);
    } else if (entry.isFile()) {
      result.push(path.relative(base, absolutePath).split(path.sep).join('/'));
    }
  }
  return result;
}

const diskFiles = walkFiles(courseRoot);
const expectedSources = diskFiles.filter(isSourceFile).sort();
const expectedLessons = diskFiles
  .filter(file => getFileKind(file) !== 'unsupported')
  .sort();
const expectedMedia = expectedLessons.filter(file => ['image', 'audio'].includes(getFileKind(file)));

const tree = scanDir(lessonsRoot);
const flattened = flattenFiles(tree);
const indexedCourseFiles = flattened
  .filter(file => file.startsWith(coursePrefix))
  .map(file => file.slice(coursePrefix.length))
  .sort();
const indexedSources = indexedCourseFiles.filter(isSourceFile);

assert.deepEqual(indexedCourseFiles, expectedLessons, 'Il tree frontend non copre tutti i file didattici del corso');
assert.deepEqual(indexedSources, expectedSources, 'Il tree frontend non copre tutti i sorgenti/supporti');

const searchIndex = buildSearchIndex(lessonsRoot, flattened);
const searchablePaths = new Set(searchIndex.map(item => item.path));
for (const source of expectedSources) {
  assert(searchablePaths.has(`${coursePrefix}${source}`), `Sorgente assente dalla ricerca: ${source}`);
}

for (const media of expectedMedia) {
  assert(isPublishedFile(media, `${coursePrefix}${media}`), `Risorsa multimediale non pubblicata: ${media}`);
  const extension = path.extname(media).toLowerCase();
  assert(MIME_TYPES[extension]?.startsWith(getFileKind(media)), `MIME errato: ${media}`);
}

const utf8Decoder = new TextDecoder('utf-8', { fatal: true });
for (const source of expectedSources) {
  const absolutePath = path.join(courseRoot, source);
  const bytes = fs.readFileSync(absolutePath);
  assert(!bytes.includes(0), `File con byte NUL non renderizzabile come testo: ${source}`);
  utf8Decoder.decode(bytes);
  assert(isPublishedFile(source, `${coursePrefix}${source}`), `File non pubblicato in build: ${source}`);
  const sourceType = getSourceFileType(source);
  assert(sourceType, `Metadati mancanti: ${source}`);
  const extension = path.extname(source).toLowerCase();
  assert.equal(MIME_TYPES[extension], 'text/plain; charset=utf-8', `MIME errato: ${source}`);
}

if (checkDist) {
  const distLessons = path.join(repoRoot, 'dist', 'lessons');
  assert(fs.existsSync(distLessons), 'dist/lessons non esiste: eseguire prima npm run build');
  for (const source of expectedSources) {
    const original = fs.readFileSync(path.join(courseRoot, source));
    const publishedPath = path.join(distLessons, coursePrefix, source);
    assert(fs.existsSync(publishedPath), `File non copiato in dist: ${source}`);
    assert(original.equals(fs.readFileSync(publishedPath)), `Copia in dist alterata: ${source}`);
  }
  for (const media of expectedMedia) {
    const original = fs.readFileSync(path.join(courseRoot, media));
    const publishedPath = path.join(distLessons, coursePrefix, media);
    assert(fs.existsSync(publishedPath), `Risorsa non copiata in dist: ${media}`);
    assert(original.equals(fs.readFileSync(publishedPath)), `Copia in dist alterata: ${media}`);
  }
}

const summary = expectedSources.reduce((counts, file) => {
  const extension = path.extname(file).toLowerCase();
  counts[extension] = (counts[extension] ?? 0) + 1;
  return counts;
}, {});

console.log(`Tree Programmazione: ${indexedCourseFiles.length}/${expectedLessons.length} file indicizzati`);
console.log(`Sorgenti/supporti: ${indexedSources.length}/${expectedSources.length} — ${JSON.stringify(summary)}`);
console.log(`Ricerca: ${expectedSources.length}/${expectedSources.length} sorgenti indicizzati`);
console.log(`UTF-8, MIME e pubblicazione: ${expectedSources.length}/${expectedSources.length}`);
console.log(`Risorse multimediali: ${expectedMedia.length}/${expectedMedia.length}`);
if (checkDist) console.log(`Copia byte-identica in dist: ${expectedSources.length + expectedMedia.length}/${expectedSources.length + expectedMedia.length}`);
