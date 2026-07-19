import { spawn } from 'node:child_process';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const repoRoot = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const baseUrl = 'http://127.0.0.1:5193/';
const viteEntry = path.join(repoRoot, 'node_modules', 'vite', 'bin', 'vite.js');

function waitForExit(child) {
  return new Promise((resolve, reject) => {
    child.once('error', reject);
    child.once('exit', (code, signal) => resolve({ code, signal }));
  });
}

async function waitForServer(server) {
  for (let attempt = 0; attempt < 80; attempt++) {
    if (server.exitCode !== null) {
      throw new Error(`Preview terminata prima dell'avvio (exit code ${server.exitCode})`);
    }
    try {
      const response = await fetch(baseUrl);
      if (response.ok) return;
    } catch {
      // Il server non è ancora pronto.
    }
    await new Promise(resolve => setTimeout(resolve, 250));
  }
  throw new Error(`Preview non raggiungibile su ${baseUrl}`);
}

const server = spawn(
  process.execPath,
  [viteEntry, 'preview', '--host', '127.0.0.1', '--port', '5193', '--strictPort'],
  {
    cwd: repoRoot,
    stdio: 'ignore',
    windowsHide: true,
  },
);

let exitCode = 1;
try {
  await waitForServer(server);
  const verifier = spawn(
    process.execPath,
    [path.join(repoRoot, 'scripts', 'verify-source-renderer.mjs')],
    {
      cwd: repoRoot,
      env: { ...process.env, SOURCE_RENDERER_BASE_URL: baseUrl },
      stdio: 'inherit',
      windowsHide: true,
    },
  );
  const result = await waitForExit(verifier);
  exitCode = result.code ?? 1;
} finally {
  if (!server.killed) server.kill();
  await Promise.race([
    waitForExit(server),
    new Promise(resolve => setTimeout(resolve, 2000)),
  ]);
}

process.exit(exitCode);
