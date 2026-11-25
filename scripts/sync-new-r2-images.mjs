import fs from 'node:fs/promises';
import path from 'node:path';
import { createHash } from 'node:crypto';
import sharp from 'sharp';
import { PutObjectCommand, S3Client } from '@aws-sdk/client-s3';
import { listLessonImages, statePath, writeCurrentState } from './r2-image-state.mjs';

const root = process.cwd();
const manifestPath = path.join(root, 'src/data/r2-assets.json');
const publicManifestPath = path.join(root, 'src/data/r2-assets-public.json');
const envNames = ['CLOUDFLARE_ACCOUNT_ID', 'R2_ACCESS_KEY_ID', 'R2_SECRET_ACCESS_KEY'];
try {
  const localEnv = await fs.readFile(path.join(root, '.env.r2.local'), 'utf8');
  for (const line of localEnv.split(/\r?\n/)) {
    const match = line.match(/^\s*([A-Z0-9_]+)=(.*)\s*$/);
    if (match && !process.env[match[1]]) process.env[match[1]] = match[2];
  }
} catch { /* Credentials may instead be provided by the process environment. */ }
const missing = envNames.filter((name) => !process.env[name]);
const bucket = process.env.R2_BUCKET || 'cybersec-unimi-assets';
const base = (process.env.R2_PUBLIC_BASE_URL || 'https://assets.samuelecorra.dev').replace(/\/$/, '');

const digest = (buffer) => createHash('sha256').update(buffer).digest('hex');
const mime = (file) => ({ '.png':'image/png','.jpg':'image/jpeg','.jpeg':'image/jpeg','.gif':'image/gif','.webp':'image/webp','.avif':'image/avif','.svg':'image/svg+xml' })[path.extname(file).toLowerCase()] || 'application/octet-stream';
const publicUrl = (key) => `${base}/${key.split('/').map(encodeURIComponent).join('/')}`;
const full = JSON.parse(await fs.readFile(manifestPath, 'utf8'));
const publicManifest = JSON.parse(await fs.readFile(publicManifestPath, 'utf8'));
let savedState = { entries: {} };
try { savedState = JSON.parse(await fs.readFile(statePath, 'utf8')); } catch { /* Seed once below. */ }
const files = await listLessonImages();
const changed = [];
const currentEntries = {};
for (const absolute of files) {
  const source = path.relative(root, absolute).split(path.sep).join('/');
  const stat = await fs.stat(absolute);
  currentEntries[source] = { size: stat.size, mtimeMs: stat.mtimeMs };
  const oldStat = savedState.entries?.[source];
  if (oldStat && oldStat.size === stat.size && oldStat.mtimeMs === stat.mtimeMs) continue;
  if (!oldStat && full.assets[source]?.sourceSha256
    && (path.extname(source).toLowerCase() !== '.png' || full.assets[source].key !== source)) continue;
  const bytes = await fs.readFile(absolute);
  const sourceSha256 = digest(bytes);
  const existing = full.assets[source];
  // Re-evaluate a changed PNG that is still stored under its original key: it
  // may become losslessly compressible after the channel-safe pixel check.
  if (existing?.sourceSha256 === sourceSha256 && (path.extname(source).toLowerCase() !== '.png' || existing.key !== source)) continue;
  let key = source;
  let data = bytes;
  if (path.extname(source).toLowerCase() === '.png') {
    const meta = await sharp(bytes, { limitInputPixels: false }).metadata();
    if ((!meta.pages || meta.pages === 1) && meta.depth === 'uchar') {
      const webp = await sharp(bytes, { limitInputPixels: false }).webp({ lossless: true, effort: 4, quality: 100 }).keepIccProfile().toBuffer();
      if (webp.length < bytes.length) {
        const original = await sharp(bytes, { limitInputPixels: false }).ensureAlpha().raw().toBuffer({ resolveWithObject: true });
        const converted = await sharp(webp, { limitInputPixels: false }).ensureAlpha().raw().toBuffer({ resolveWithObject: true });
        if (original.info.width === converted.info.width && original.info.height === converted.info.height
          && original.data.equals(converted.data)) {
          key = `${source.slice(0, -4)}.webp`;
          data = webp;
        }
      }
    }
  }
  if (existing?.sourceSha256 === sourceSha256 && existing.key === key
    && existing.sha256 === digest(data)) continue;
  changed.push({ source, sourceSha256, key, data, originalSize: bytes.length, contentType: mime(key) });
}

if (!changed.length) {
  await fs.mkdir(path.dirname(statePath), { recursive: true });
  await fs.writeFile(statePath, `${JSON.stringify({ version: 1, entries: currentEntries }, null, 2)}\n`, 'utf8');
  console.log('R2 lesson images are already synchronized.');
  process.exit(0);
}
if (missing.length) {
  console.error(`New or changed lesson images need R2 sync, but credentials are missing: ${missing.join(', ')}.`);
  console.error('Set them in .env.r2.local or in the process environment, then retry the commit.');
  process.exit(1);
}
const client = new S3Client({
  region: 'auto', endpoint: `https://${process.env.CLOUDFLARE_ACCOUNT_ID}.r2.cloudflarestorage.com`,
  forcePathStyle: true, maxAttempts: 4,
  credentials: { accessKeyId: process.env.R2_ACCESS_KEY_ID, secretAccessKey: process.env.R2_SECRET_ACCESS_KEY },
});
try {
  for (const item of changed) {
    const sha256 = digest(item.data);
    await client.send(new PutObjectCommand({
      Bucket: bucket, Key: item.key, Body: item.data, ContentLength: item.data.length,
      ContentType: item.contentType, CacheControl: 'public, max-age=604800, stale-while-revalidate=86400',
      Metadata: { sha256, 'source-path': encodeURIComponent(item.source) },
    }));
    full.assets[item.source] = { key: item.key, size: item.data.length, originalSize: item.originalSize, sourceSha256: item.sourceSha256, sha256, contentType: item.contentType };
    publicManifest.assets[item.source] = { key: item.key };
    console.log(`Uploaded ${item.source} → ${publicUrl(item.key)}`);
  }
} finally { client.destroy(); }

full.totalObjects = Object.keys(full.assets).length;
full.originalBytes = Object.values(full.assets).reduce((sum, asset) => sum + (asset.originalSize || asset.size), 0);
full.stagedBytes = Object.values(full.assets).reduce((sum, asset) => sum + asset.size, 0);
full.generatedAt = new Date().toISOString();
await fs.writeFile(manifestPath, `${JSON.stringify(full, null, 2)}\n`);
await fs.writeFile(publicManifestPath, `${JSON.stringify(publicManifest, null, 2)}\n`);
const { execFileSync } = await import('node:child_process');
execFileSync('git', ['add', '--', 'src/data/r2-assets.json', 'src/data/r2-assets-public.json'], { stdio: 'inherit' });
await writeCurrentState(files);
console.log(`${changed.length} image(s) uploaded and both manifests staged.`);
