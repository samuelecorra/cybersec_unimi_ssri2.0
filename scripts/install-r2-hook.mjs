import { execFileSync } from 'node:child_process';
import { seedCurrentState } from './r2-image-state.mjs';

execFileSync('git', ['config', 'core.hooksPath', '.githooks'], { stdio: 'inherit' });
const files = await seedCurrentState();
console.log(`Installed pre-commit R2 uploader; recorded ${files} local image files.`);
