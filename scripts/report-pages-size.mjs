import { appendFile, readdir, stat } from "node:fs/promises";
import path from "node:path";
import { pathToFileURL } from "node:url";

const root = path.resolve(process.env.PAGES_DIST_DIR || "dist");
const MiB = 1024 * 1024;
const ARTIFACT_LIMIT_BYTES = 1_000_000_000;
// Estimated from the most recent measured Pages artifact/raw-dist pair. This is a projection, not a second archive build.
const ARTIFACT_TO_RAW_RATIO = 0.9462035274591719;
const imageExts = new Set([".png", ".jpg", ".jpeg", ".gif", ".webp", ".svg", ".bmp"]);
const audioExts = new Set([".wav", ".mp3", ".m4a", ".ogg", ".aac", ".flac"]);
const totals = {
  files: 0,
  bytes: 0,
  pdf: { count: 0, bytes: 0 },
  image: { count: 0, bytes: 0 },
  audio: { count: 0, bytes: 0 },
};
const largest = [];

async function walk(directory) {
  for (const entry of await readdir(directory, { withFileTypes: true })) {
    const fullPath = path.join(directory, entry.name);
    if (entry.isDirectory()) {
      await walk(fullPath);
      continue;
    }
    if (!entry.isFile()) continue;

    const { size } = await stat(fullPath);
    const relativePath = path.relative(root, fullPath).split(path.sep).join("/");
    const ext = path.extname(entry.name).toLowerCase();
    totals.files += 1;
    totals.bytes += size;
    const bucket = ext === ".pdf" ? totals.pdf : imageExts.has(ext) ? totals.image : audioExts.has(ext) ? totals.audio : null;
    if (bucket) {
      bucket.count += 1;
      bucket.bytes += size;
    }
    largest.push({ path: relativePath, bytes: size });
  }
}

await walk(root);
largest.sort((a, b) => b.bytes - a.bytes || a.path.localeCompare(b.path));
const projectedArtifactBytes = Math.round(totals.bytes * ARTIFACT_TO_RAW_RATIO);
const warning = projectedArtifactBytes > ARTIFACT_LIMIT_BYTES;
const format = (value) => `${value.toLocaleString("en-US")} B`;
const topRows = largest.slice(0, 10).map(({ path: filePath, bytes }) => `| \`${filePath.replaceAll("|", "\\|")}\` | ${format(bytes)} |`);
const lines = [
  "### Pages payload size (report only)",
  "",
  `- Raw dist: **${format(totals.bytes)}** across **${totals.files.toLocaleString("en-US")} files**.`,
  `- PDFs: ${totals.pdf.count.toLocaleString("en-US")} files, ${format(totals.pdf.bytes)}.`,
  `- Images: ${totals.image.count.toLocaleString("en-US")} files, ${format(totals.image.bytes)}.`,
  `- Audio: ${totals.audio.count.toLocaleString("en-US")} files, ${format(totals.audio.bytes)}.`,
  `- Estimated Pages artifact: **${format(projectedArtifactBytes)}** (raw dist × ${ARTIFACT_TO_RAW_RATIO}; estimate only).`,
  ...(warning ? [`- **Warning:** projected artifact is above ${format(ARTIFACT_LIMIT_BYTES)}.`] : []),
  "",
  "| Top 10 files | Bytes |",
  "|---|---:|",
  ...topRows,
  "",
  "Estimate uses the latest measured artifact/raw ratio; it does not create or upload another artifact.",
  "",
].join("\n");

if (process.env.GITHUB_STEP_SUMMARY) {
  await appendFile(process.env.GITHUB_STEP_SUMMARY, lines);
} else {
  process.stdout.write(lines);
}
if (warning) {
  console.log(`::warning title=Pages artifact size projection::Estimated artifact size ${projectedArtifactBytes} B exceeds ${ARTIFACT_LIMIT_BYTES} B.`);
}
