import fs from 'node:fs/promises';
import path from 'node:path';
import sharp from 'sharp';
import { listLessonImages, writeCurrentState } from './r2-image-state.mjs';

const root = process.cwd();
const manifestPath = path.join(root, 'src', 'data', 'r2-assets.json');
const publicBase = (process.env.R2_PUBLIC_BASE_URL || 'https://assets.samuelecorra.dev').replace(/\/$/, '');
const imageExtensions = new Set(['.png', '.jpg', '.jpeg', '.gif', '.webp', '.avif', '.svg']);
const manifest = JSON.parse(await fs.readFile(manifestPath, 'utf8'));
const assets = Object.entries(manifest.assets).filter(([source]) => imageExtensions.has(path.extname(source).toLowerCase()));
let restored = 0;
let skipped = 0;
let next = 0;

function publicUrl(key) {
  return `${publicBase}/${key.split('/').map(encodeURIComponent).join('/')}`;
}

async function hydrate([source, asset]) {
  const output = path.resolve(root, ...source.split('/'));
  if (!output.startsWith(`${path.resolve(root, 'lessons')}${path.sep}`)
    && !output.startsWith(`${path.resolve(root, 'tmp')}${path.sep}`)) {
    throw new Error(`Refusing path outside lesson media: ${source}`);
  }
  try { await fs.access(output); skipped += 1; return; } catch { /* Download below. */ }
  const response = await fetch(publicUrl(asset.key));
  if (!response.ok) throw new Error(`R2 GET ${response.status} for ${asset.key}`);
  const bytes = Buffer.from(await response.arrayBuffer());
  await fs.mkdir(path.dirname(output), { recursive: true });
  const temporary = `${output}.hydrate-${process.pid}`;
  const targetExt = path.extname(source).toLowerCase();
  const keyExt = path.extname(asset.key).toLowerCase();
  if (targetExt === '.png' && keyExt === '.webp') {
    await sharp(bytes, { limitInputPixels: false }).png().toFile(temporary);
  } else {
    await fs.writeFile(temporary, bytes);
  }
  await fs.rename(temporary, output);
  restored += 1;
}

while (next < assets.length) {
  const group = assets.slice(next, next + 6);
  next += group.length;
  await Promise.all(group.map(hydrate));
}
console.log(JSON.stringify({ total: assets.length, restored, alreadyPresent: skipped, publicBase }, null, 2));
await writeCurrentState(await listLessonImages());
