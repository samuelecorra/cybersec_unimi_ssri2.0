import fs from 'node:fs/promises';
import path from 'node:path';

export const repoRoot = process.cwd();
export const statePath = path.join(repoRoot, '.cache/r2-image-state.json');
export const imageExtensions = new Set(['.png', '.jpg', '.jpeg', '.gif', '.webp', '.avif', '.svg']);

export async function listLessonImages() {
  const walk = async (directory) => {
    let output = [];
    let entries;
    try { entries = await fs.readdir(directory, { withFileTypes: true }); } catch { return output; }
    for (const entry of entries) {
      const absolute = path.join(directory, entry.name);
      if (entry.isDirectory()) output = output.concat(await walk(absolute));
      else if (entry.isFile() && imageExtensions.has(path.extname(entry.name).toLowerCase())) output.push(absolute);
    }
    return output;
  };
  return (await walk(path.join(repoRoot, 'lessons'))).sort();
}

export async function writeCurrentState(files) {
  const entries = {};
  for (const absolute of files) {
    const source = path.relative(repoRoot, absolute).split(path.sep).join('/');
    const stat = await fs.stat(absolute);
    entries[source] = { size: stat.size, mtimeMs: stat.mtimeMs };
  }
  await fs.mkdir(path.dirname(statePath), { recursive: true });
  await fs.writeFile(statePath, `${JSON.stringify({ version: 1, entries }, null, 2)}\n`, 'utf8');
  return entries;
}

export async function seedCurrentState() {
  const files = await listLessonImages();
  await writeCurrentState(files);
  return files.length;
}
