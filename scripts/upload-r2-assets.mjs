import fs from 'node:fs/promises';
import path from 'node:path';
import { createReadStream } from 'node:fs';
import {
  HeadObjectCommand,
  PutObjectCommand,
  S3Client,
} from '@aws-sdk/client-s3';

const repoRoot = process.cwd();
const manifestPath = path.join(repoRoot, 'src', 'data', 'r2-assets.json');
const required = ['CLOUDFLARE_ACCOUNT_ID', 'R2_ACCESS_KEY_ID', 'R2_SECRET_ACCESS_KEY'];
const missing = required.filter((name) => !process.env[name]);
if (missing.length) {
  console.error(`Missing environment variables: ${missing.join(', ')}`);
  process.exit(1);
}

const bucket = process.env.R2_BUCKET || 'cybersec-unimi-assets';
const accountId = process.env.CLOUDFLARE_ACCOUNT_ID;
const stageRoot = path.resolve(repoRoot, '.cache', 'r2-upload');
if (!stageRoot.startsWith(path.resolve(repoRoot, '.cache') + path.sep)) {
  throw new Error(`Unsafe staging directory: ${stageRoot}`);
}

const manifest = JSON.parse(await fs.readFile(manifestPath, 'utf8'));
const client = new S3Client({
  region: 'auto',
  endpoint: `https://${accountId}.r2.cloudflarestorage.com`,
  forcePathStyle: true,
  maxAttempts: 4,
  credentials: {
    accessKeyId: process.env.R2_ACCESS_KEY_ID,
    secretAccessKey: process.env.R2_SECRET_ACCESS_KEY,
  },
});

const assets = Object.entries(manifest.assets);
let uploaded = 0;
let skipped = 0;
let completed = 0;
let next = 0;

async function uploadAsset([sourcePath, asset]) {
  const localPath = path.join(stageRoot, ...asset.key.split('/'));
  const key = asset.key;
  let current;
  try {
    current = await client.send(new HeadObjectCommand({ Bucket: bucket, Key: key }));
  } catch (error) {
    if (!['NotFound', 'NoSuchKey', 'NotFoundException'].includes(error.name)
      && error.$metadata?.httpStatusCode !== 404) throw error;
  }

  if (current && current.Metadata?.sha256 === asset.sha256 && current.ContentLength === asset.size) {
    skipped += 1;
  } else {
    await client.send(new PutObjectCommand({
      Bucket: bucket,
      Key: key,
      Body: createReadStream(localPath),
      ContentLength: asset.size,
      ContentType: asset.contentType,
      CacheControl: 'public, max-age=604800, stale-while-revalidate=86400',
      Metadata: {
        sha256: asset.sha256,
        'source-path': encodeURIComponent(sourcePath),
      },
    }));
    uploaded += 1;
  }

  completed += 1;
  if (completed % 50 === 0 || completed === assets.length) {
    console.log(`${completed}/${assets.length} objects processed (${uploaded} uploaded, ${skipped} unchanged)`);
  }
}

async function worker() {
  while (next < assets.length) {
    const index = next++;
    await uploadAsset(assets[index]);
  }
}

try {
  await Promise.all(Array.from({ length: 6 }, worker));
} finally {
  client.destroy();
}

console.log(JSON.stringify({ bucket, total: assets.length, uploaded, unchanged: skipped }, null, 2));
