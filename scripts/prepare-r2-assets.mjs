import fs from 'node:fs/promises';
import { createReadStream } from 'node:fs';
import path from 'node:path';
import { createHash } from 'node:crypto';
import { execFileSync } from 'node:child_process';
import sharp from 'sharp';

const repoRoot = process.cwd();
const stageRoot = path.join(repoRoot, '.cache', 'r2-upload');
const manifestPath = path.join(repoRoot, 'src', 'data', 'r2-assets.json');
const publicManifestPath = path.join(repoRoot, 'src', 'data', 'r2-assets-public.json');
const imageExtensions = new Set(['.png', '.jpg', '.jpeg', '.gif', '.webp', '.avif', '.svg']);
const heavyExtensions = new Set([
  '.pdf', '.zip', '.7z', '.rar', '.tar', '.gz', '.tgz',
  '.wav', '.mp3', '.m4a', '.ogg', '.mp4', '.mov',
  '.caffemodel', '.pb', '.onnx', '.pt', '.pth', '.h5', '.mat',
  '.pcap', '.pcapng', '.exe', '.jar',
]);
const textExtensions = new Set([
  '.md', '.txt', '.csv', '.json', '.xml', '.html', '.css', '.js', '.jsx',
  '.ts', '.tsx', '.py', '.c', '.h', '.java', '.m', '.sh', '.bat', '.yml',
  '.yaml', '.toml', '.ipynb', '.sql', '.log', '.svg',
]);

const mimeTypes = Object.freeze({
  '.png': 'image/png', '.jpg': 'image/jpeg', '.jpeg': 'image/jpeg',
  '.gif': 'image/gif', '.webp': 'image/webp', '.avif': 'image/avif',
  '.svg': 'image/svg+xml', '.pdf': 'application/pdf', '.zip': 'application/zip',
  '.7z': 'application/x-7z-compressed', '.rar': 'application/vnd.rar',
  '.wav': 'audio/wav', '.mp3': 'audio/mpeg', '.m4a': 'audio/mp4',
  '.ogg': 'audio/ogg', '.mp4': 'video/mp4', '.mov': 'video/quicktime',
});

function normalizeRepoPath(filePath) {
  return filePath.split(path.sep).join('/');
}

function shouldMigrate(filePath, size) {
  const extension = path.extname(filePath).toLowerCase();
  if (imageExtensions.has(extension) || heavyExtensions.has(extension)) return true;
  return size >= 1024 * 1024 && !textExtensions.has(extension);
}

function mapLimit(items, limit, mapper) {
  let next = 0;
  const workers = Array.from({ length: Math.min(limit, items.length) }, async () => {
    while (next < items.length) {
      const index = next++;
      await mapper(items[index], index);
    }
  });
  return Promise.all(workers);
}

async function isSmallerLosslessWebp(inputPath, originalSize) {
  if (path.extname(inputPath).toLowerCase() !== '.png') return null;

  const metadata = await sharp(inputPath, { limitInputPixels: false }).metadata();
  if ((metadata.pages && metadata.pages > 1) || metadata.depth !== 'uchar') return null;

  const webp = await sharp(inputPath, { limitInputPixels: false })
    .webp({ lossless: true, effort: 4, quality: 100 })
    .keepIccProfile()
    .toBuffer();
  if (webp.length >= originalSize) return null;

  const originalPixels = await sharp(inputPath, { limitInputPixels: false })
    .raw().toBuffer({ resolveWithObject: true });
  const webpPixels = await sharp(webp, { limitInputPixels: false })
    .raw().toBuffer({ resolveWithObject: true });
  const samePixels = originalPixels.info.width === webpPixels.info.width
    && originalPixels.info.height === webpPixels.info.height
    && originalPixels.info.channels === webpPixels.info.channels
    && originalPixels.data.equals(webpPixels.data);
  if (!samePixels) return null;
  return webp;
}

async function sha256File(filePath) {
  const hash = createHash('sha256');
  for await (const chunk of createReadStream(filePath)) hash.update(chunk);
  return hash.digest('hex');
}

async function main() {
  const trackedFiles = execFileSync('git', ['ls-files', '-co', '--exclude-standard', '-z'], { encoding: 'utf8' })
    .split('\0').filter(Boolean);
  const candidates = [];
  for (const filePath of trackedFiles) {
    const absolutePath = path.join(repoRoot, filePath);
    let stat;
    try { stat = await fs.stat(absolutePath); } catch { continue; }
    if (stat.isFile() && shouldMigrate(filePath, stat.size)) {
      candidates.push({ filePath: normalizeRepoPath(filePath), absolutePath, size: stat.size });
    }
  }

  const resolvedStageRoot = path.resolve(stageRoot);
  if (!resolvedStageRoot.startsWith(path.resolve(repoRoot, '.cache') + path.sep)) {
    throw new Error(`Unsafe staging directory: ${resolvedStageRoot}`);
  }
  await fs.mkdir(resolvedStageRoot, { recursive: true });
  await fs.mkdir(path.dirname(manifestPath), { recursive: true });

  const assets = {};
  let assetsFromExistingManifest = {};
  try {
    assetsFromExistingManifest = JSON.parse(await fs.readFile(manifestPath, 'utf8')).assets ?? {};
  } catch { /* First run: there is no existing manifest to reuse. */ }
  let originalBytes = 0;
  let stagedBytes = 0;
  let losslessWebpCount = 0;
  let losslessWebpBytesSaved = 0;

  await mapLimit(candidates, 4, async (candidate) => {
    const previous = assetsFromExistingManifest[candidate.filePath];
    const previousPath = previous && path.join(resolvedStageRoot, ...previous.key.split('/'));
    if (previous && previous.key === candidate.filePath && previous.originalSize === candidate.size) {
      try {
        const [stagedStat, sourceHash] = await Promise.all([fs.stat(previousPath), sha256File(candidate.absolutePath)]);
        if (stagedStat.size === previous.size && sourceHash === previous.sha256) {
          assets[candidate.filePath] = { ...previous, sourceSha256: sourceHash };
          originalBytes += candidate.size;
          stagedBytes += previous.size;
          return;
        }
      } catch { /* Rebuild missing or changed staged objects below. */ }
    }
    let key = candidate.filePath;
    let data;
    const webp = await isSmallerLosslessWebp(candidate.absolutePath, candidate.size);
    if (webp) {
      key = `${candidate.filePath.slice(0, -path.extname(candidate.filePath).length)}.webp`;
      data = webp;
      losslessWebpCount += 1;
      losslessWebpBytesSaved += candidate.size - webp.length;
    }

    const outputPath = path.join(resolvedStageRoot, ...key.split('/'));
    await fs.mkdir(path.dirname(outputPath), { recursive: true });
    if (data) await fs.writeFile(outputPath, data);
    else await fs.copyFile(candidate.absolutePath, outputPath);

    const outputSize = data?.length ?? candidate.size;
    originalBytes += candidate.size;
    stagedBytes += outputSize;
    assets[candidate.filePath] = {
      key,
      size: outputSize,
      originalSize: candidate.size,
      sourceSha256: await sha256File(candidate.absolutePath),
      sha256: data ? createHash('sha256').update(data).digest('hex') : await sha256File(outputPath),
      contentType: mimeTypes[path.extname(key).toLowerCase()] || 'application/octet-stream',
    };
  });

  const manifest = {
    version: 1,
    generatedAt: new Date().toISOString(),
    stageRoot: '.cache/r2-upload',
    totalObjects: Object.keys(assets).length,
    originalBytes,
    stagedBytes,
    losslessWebpCount: Object.values(assets).filter((asset) => asset.key.endsWith('.webp') && asset.originalSize > asset.size).length,
    losslessWebpBytesSaved: Object.values(assets).reduce((sum, asset) => sum + Math.max(0, asset.originalSize - asset.size), 0),
    assets,
  };
  await fs.writeFile(manifestPath, `${JSON.stringify(manifest, null, 2)}\n`, 'utf8');
  await fs.writeFile(publicManifestPath, `${JSON.stringify({ version: 1, assets: Object.fromEntries(Object.entries(assets).map(([source, asset]) => [source, { key: asset.key }])) })}\n`, 'utf8');
  console.log(JSON.stringify({
    totalObjects: manifest.totalObjects,
    originalGiB: Number((originalBytes / 2 ** 30).toFixed(3)),
    stagedGiB: Number((stagedBytes / 2 ** 30).toFixed(3)),
    totalSavedGiB: Number(((originalBytes - stagedBytes) / 2 ** 30).toFixed(3)),
    losslessWebpCount,
    losslessWebpSavedMiB: Number((losslessWebpBytesSaved / 2 ** 20).toFixed(1)),
    manifest: normalizeRepoPath(path.relative(repoRoot, manifestPath)),
    uploadDirectory: normalizeRepoPath(path.relative(repoRoot, resolvedStageRoot)),
  }, null, 2));
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
