import fs from 'node:fs/promises';
import path from 'node:path';

const root = process.cwd();
const manifest = JSON.parse(await fs.readFile(path.join(root, 'src/data/r2-assets.json'), 'utf8'));
const imagePattern = /!\[[^\]]*\]\(([^)]+)\)/g;
const encodePath = (value) => value.split('/').map(encodeURIComponent).join('/');
const decodePath = (value) => value.split('/').map((part) => decodeURIComponent(part)).join('/');
let changedFiles = 0;
let removedBlocks = 0;

async function walk(directory) {
  const entries = await fs.readdir(directory, { withFileTypes: true });
  for (const entry of entries) {
    const absolute = path.join(directory, entry.name);
    if (entry.isDirectory()) await walk(absolute);
    else if (entry.isFile() && entry.name.toLowerCase().endsWith('.md')) await clean(absolute);
  }
}

async function clean(file) {
  const original = await fs.readFile(file, 'utf8');
  const lines = original.split(/(?<=\n)/);
  const remove = new Set();
  for (let i = 0; i < lines.length; i += 1) {
    if (!lines[i].includes('<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->')) continue;
    let imageFound = false;
    for (let j = i + 1; j < Math.min(lines.length, i + 9); j += 1) {
      if (/^\s{0,3}#{1,6}\s/.test(lines[j]) || lines[j].includes('<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->')) break;
      for (const match of lines[j].matchAll(imagePattern)) {
        const target = match[1].trim().replace(/^<|>$/g, '').split(/\s+/)[0];
        if (/^(?:https?:|data:)/i.test(target)) continue;
        let decoded;
        try { decoded = decodePath(target); } catch { continue; }
        const source = path.posix.normalize(path.posix.join(path.relative(root, path.dirname(file)).split(path.sep).join('/'), decoded));
        if (manifest.assets[source]) imageFound = true;
      }
      if (imageFound) break;
    }
    if (!imageFound) continue;
    remove.add(i);
    for (let j = i - 1; j >= Math.max(0, i - 3); j -= 1) {
      if (/<!--\s*SOURCE_VISUAL\b/.test(lines[j])) { remove.add(j); break; }
      if (lines[j].trim() && !lines[j].trim().startsWith('<!--')) break;
    }
    removedBlocks += 1;
  }
  if (!remove.size) return;
  const updated = lines.filter((_, i) => !remove.has(i)).join('');
  await fs.writeFile(file, updated, 'utf8');
  changedFiles += 1;
}

await walk(path.join(root, 'lessons'));
console.log(JSON.stringify({ changedFiles, removedBlocks }));
