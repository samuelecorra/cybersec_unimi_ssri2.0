import fs from 'node:fs/promises';
import path from 'node:path';
import { createHash } from 'node:crypto';
import sharp from 'sharp';
import { PutObjectCommand, S3Client } from '@aws-sdk/client-s3';

const root = process.cwd();
const manifestPath = path.join(root, 'src/data/r2-assets.json');
const publicManifestPath = path.join(root, 'src/data/r2-assets-public.json');
const stageRoot = path.resolve(root, '.cache/r2-upload');
if (!stageRoot.startsWith(`${path.resolve(root, '.cache')}${path.sep}`)) throw new Error(`Unsafe staging path: ${stageRoot}`);

try {
  const localEnv = await fs.readFile(path.join(root, '.env.r2.local'), 'utf8');
  for (const line of localEnv.split(/\r?\n/)) {
    const match = line.match(/^\s*([A-Z0-9_]+)=(.*)\s*$/);
    if (match && !process.env[match[1]]) process.env[match[1]] = match[2];
  }
} catch { /* Credentials may be supplied by the process environment. */ }

const required = ['CLOUDFLARE_ACCOUNT_ID', 'R2_ACCESS_KEY_ID', 'R2_SECRET_ACCESS_KEY'];
const missing = required.filter((name) => !process.env[name]);
if (missing.length) throw new Error(`R2 credentials missing: ${missing.join(', ')}`);

const bucket = process.env.R2_BUCKET || 'cybersec-unimi-assets';
const publicBase = (process.env.R2_PUBLIC_BASE_URL || 'https://assets.samuelecorra.dev').replace(/\/$/, '');
const digest = (bytes) => createHash('sha256').update(bytes).digest('hex');
const mime = (file) => path.extname(file).toLowerCase() === '.webp' ? 'image/webp' : 'application/octet-stream';
const publicUrl = (key) => `${publicBase}/${key.split('/').map(encodeURIComponent).join('/')}`;
const manifest = JSON.parse(await fs.readFile(manifestPath, 'utf8'));
const publicManifest = JSON.parse(await fs.readFile(publicManifestPath, 'utf8'));
const candidates = Object.entries(manifest.assets).filter(([source, asset]) =>
  path.extname(source).toLowerCase() === '.png' && asset.key === source);
const outputOwner = new Map();
for (const [source, asset] of Object.entries(manifest.assets)) {
  const owners = outputOwner.get(asset.key) ?? [];
  owners.push(source);
  outputOwner.set(asset.key, owners);
}

const client = new S3Client({
  region: 'auto', endpoint: `https://${process.env.CLOUDFLARE_ACCOUNT_ID}.r2.cloudflarestorage.com`,
  forcePathStyle: true, maxAttempts: 4,
  credentials: { accessKeyId: process.env.R2_ACCESS_KEY_ID, secretAccessKey: process.env.R2_SECRET_ACCESS_KEY },
});

const updates = [];
let examined = 0;
let skippedMissing = 0;
let skippedNotSmaller = 0;
let uploaded = 0;
let next = 0;
const workerCount = Math.max(1, Math.min(4, Number(process.env.R2_OPTIMIZE_CONCURRENCY) || 4));

async function optimize([source, previous]) {
  const absolute = path.resolve(root, ...source.split('/'));
  if (!absolute.startsWith(`${path.resolve(root, 'lessons')}${path.sep}`)
    && !absolute.startsWith(`${path.resolve(root, 'tmp')}${path.sep}`)) throw new Error(`Refusing path outside lesson assets: ${source}`);
  let bytes;
  try { bytes = await fs.readFile(absolute); } catch (error) {
    if (error.code === 'ENOENT') { skippedMissing += 1; return; }
    throw error;
  }
  examined += 1;
  const metadata = await sharp(bytes, { limitInputPixels: false }).metadata();
  if ((metadata.pages && metadata.pages > 1) || metadata.depth !== 'uchar') { skippedNotSmaller += 1; return; }
  const webp = await sharp(bytes, { limitInputPixels: false })
    .webp({ lossless: true, effort: 4, quality: 100 }).keepIccProfile().toBuffer();
  if (webp.length >= bytes.length) { skippedNotSmaller += 1; return; }
  const [originalPixels, webpPixels] = await Promise.all([
    sharp(bytes, { limitInputPixels: false }).ensureAlpha().raw().toBuffer({ resolveWithObject: true }),
    sharp(webp, { limitInputPixels: false }).ensureAlpha().raw().toBuffer({ resolveWithObject: true }),
  ]);
  if (originalPixels.info.width !== webpPixels.info.width
    || originalPixels.info.height !== webpPixels.info.height
    || !originalPixels.data.equals(webpPixels.data)) {
    skippedNotSmaller += 1;
    return;
  }

  const key = `${source.slice(0, -4)}.webp`;
  const webpHash = digest(webp);
  const owners = (outputOwner.get(key) ?? []).filter((owner) => owner !== source);
  for (const owner of owners) {
    if (manifest.assets[owner]?.sha256 !== webpHash) {
      throw new Error(`Optimized key collision for ${key}; it is already used by ${owner}`);
    }
  }
  const localOutput = path.resolve(stageRoot, ...key.split('/'));
  if (!localOutput.startsWith(`${stageRoot}${path.sep}`)) throw new Error(`Unsafe output path: ${key}`);
  await fs.mkdir(path.dirname(localOutput), { recursive: true });
  await fs.writeFile(localOutput, webp);
  await client.send(new PutObjectCommand({
    Bucket: bucket, Key: key, Body: webp, ContentLength: webp.length, ContentType: mime(key),
    CacheControl: 'public, max-age=604800, stale-while-revalidate=86400',
    Metadata: { sha256: webpHash, 'source-path': encodeURIComponent(source) },
  }));
  updates.push([source, {
    key, size: webp.length, originalSize: bytes.length, sourceSha256: digest(bytes),
    sha256: webpHash, contentType: 'image/webp',
  }]);
  uploaded += 1;
  if (uploaded % 50 === 0) console.log(`${uploaded} converted and uploaded; ${examined}/${candidates.length} candidates examined.`);
}

async function worker() {
  while (next < candidates.length) await optimize(candidates[next++]);
}

try {
  await Promise.all(Array.from({ length: workerCount }, worker));
} finally {
  client.destroy();
}

for (const [source, asset] of updates) {
  manifest.assets[source] = asset;
  publicManifest.assets[source] = { key: asset.key };
}
manifest.originalBytes = Object.values(manifest.assets).reduce((sum, asset) => sum + (asset.originalSize || asset.size), 0);
manifest.stagedBytes = Object.values(manifest.assets).reduce((sum, asset) => sum + asset.size, 0);
manifest.losslessWebpCount = Object.values(manifest.assets).filter((asset) => asset.key.endsWith('.webp') && asset.originalSize > asset.size).length;
manifest.losslessWebpBytesSaved = Object.values(manifest.assets).reduce((sum, asset) => sum + Math.max(0, (asset.originalSize || asset.size) - asset.size), 0);
manifest.generatedAt = new Date().toISOString();
await fs.writeFile(manifestPath, `${JSON.stringify(manifest, null, 2)}\n`, 'utf8');
await fs.writeFile(publicManifestPath, `${JSON.stringify(publicManifest)}\n`, 'utf8');
const { execFileSync } = await import('node:child_process');
execFileSync('git', ['add', '--', 'src/data/r2-assets.json', 'src/data/r2-assets-public.json'], { stdio: 'inherit' });
console.log(JSON.stringify({
  bucket, candidatePngs: candidates.length, examined, optimizedAndUploaded: uploaded,
  skippedMissing, skippedNotSmaller,
  bytesSavedThisPass: updates.reduce((sum, [, asset]) => sum + asset.originalSize - asset.size, 0),
  lifetimeWebpSavings: manifest.losslessWebpBytesSaved,
  estimatedManifestStorageGiB: Number((manifest.stagedBytes / 2 ** 30).toFixed(3)),
}, null, 2));
