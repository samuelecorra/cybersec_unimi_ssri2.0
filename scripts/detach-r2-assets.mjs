import fs from 'node:fs';
import { execFileSync } from 'node:child_process';
import path from 'node:path';

const repoRoot = process.cwd();
const manifestPath = path.join(repoRoot, 'src', 'data', 'r2-assets.json');
const manifest = JSON.parse(fs.readFileSync(manifestPath, 'utf8'));
const tracked = new Set(execFileSync('git', ['ls-files', '-z'], { encoding: 'utf8' }).split('\0').filter(Boolean));
const trackedAssets = Object.keys(manifest.assets ?? {}).filter((filePath) => tracked.has(filePath));

if (!trackedAssets.length) {
  console.log('No tracked R2 assets to detach.');
  process.exit(0);
}

const pathspecs = trackedAssets.map((filePath) => `:(literal)${filePath}`).join('\0') + '\0';
execFileSync('git', [
  'rm', '--cached', '--quiet', '--ignore-unmatch',
  '--pathspec-from-file=-', '--pathspec-file-nul',
], { cwd: repoRoot, input: Buffer.from(pathspecs, 'utf8'), stdio: ['pipe', 'inherit', 'inherit'] });

console.log(`Detached ${trackedAssets.length} tracked assets from the Git index; local files were preserved.`);
