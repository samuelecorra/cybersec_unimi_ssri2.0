import fs from "node:fs";
import path from "node:path";

const repoRoot = path.resolve(process.cwd());
const rootDir = path.join(repoRoot, "lessons", "cybersecurity");
const outFile = path.join(repoRoot, "manifest.json");

function walk(dir, base, acc) {
  const entries = fs.readdirSync(dir, { withFileTypes: true });
  for (const entry of entries) {
    const abs = path.join(dir, entry.name);
    const rel = path.relative(base, abs).split(path.sep).join("/");
    if (entry.isDirectory()) {
      walk(abs, base, acc);
      continue;
    }
    if (!entry.isFile()) continue;
    const stat = fs.statSync(abs);
    const ext = path.extname(entry.name).toLowerCase();
    acc.push({ path: rel, size: stat.size, ext });
  }
}

if (!fs.existsSync(rootDir)) {
  console.error(`Missing lessons root: ${rootDir}`);
  process.exit(1);
}

const files = [];
walk(rootDir, rootDir, files);
files.sort((a, b) => a.path.localeCompare(b.path, "en"));

const payload = {
  generatedAt: new Date().toISOString(),
  root: "lessons/cybersecurity",
  totalFiles: files.length,
  files,
};

fs.writeFileSync(outFile, `${JSON.stringify(payload, null, 2)}\n`, "utf8");
console.log(`manifest.json generated with ${files.length} files`);
