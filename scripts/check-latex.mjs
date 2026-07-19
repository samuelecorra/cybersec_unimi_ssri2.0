// Valida tutte le formule LaTeX ($...$ e $$...$$) dei file .md sotto una
// directory, compilandole con KaTeX (stesso motore del frontend). Uso:
//   node scripts/check-latex.mjs "lessons/cybersecurity/anno1/1_Analisi 1"
// Exit code 1 se almeno una formula non compila.
import fs from "node:fs";
import path from "node:path";
import katex from "katex";

const root = process.argv[2];
if (!root || !fs.existsSync(root)) {
  console.error("Directory non trovata:", root);
  process.exit(2);
}

function mdFiles(dir, acc = []) {
  for (const e of fs.readdirSync(dir, { withFileTypes: true })) {
    const abs = path.join(dir, e.name);
    if (e.isDirectory()) mdFiles(abs, acc);
    else if (e.isFile() && e.name.toLowerCase().endsWith(".md")) acc.push(abs);
  }
  return acc;
}

// Estrae le formule con il numero di riga d'inizio, ignorando i fenced code block.
function extractMath(text) {
  const out = [];
  const lines = text.split("\n");
  let inCode = false;
  let inDisplay = false;
  let buf = [];
  let startLine = 0;
  for (let i = 0; i < lines.length; i++) {
    const line = lines[i];
    if (/^\s*(```|~~~)/.test(line)) { inCode = !inCode; continue; }
    if (inCode) continue;

    if (inDisplay) {
      const end = line.indexOf("$$");
      if (end !== -1) {
        buf.push(line.slice(0, end));
        out.push({ math: buf.join("\n"), display: true, line: startLine });
        inDisplay = false;
        buf = [];
        // eventuale resto della riga dopo $$ chiuso: raro, lo ignoriamo per l'inline
      } else {
        buf.push(line);
      }
      continue;
    }

    let rest = line;
    let offsetSearch = 0;
    while (true) {
      const idx = rest.indexOf("$$", offsetSearch);
      if (idx === -1) break;
      const close = rest.indexOf("$$", idx + 2);
      if (close !== -1) {
        out.push({ math: rest.slice(idx + 2, close), display: true, line: i + 1 });
        rest = rest.slice(0, idx) + rest.slice(close + 2);
        offsetSearch = idx;
      } else {
        inDisplay = true;
        startLine = i + 1;
        buf = [rest.slice(idx + 2)];
        rest = rest.slice(0, idx);
        break;
      }
    }
    if (inDisplay) continue;

    // inline $...$ (non $$), su singola riga
    const inlineRe = /(?<!\$)\$(?!\$)((?:\\.|[^$\\])+?)\$(?!\$)/g;
    let m;
    while ((m = inlineRe.exec(rest)) !== null) {
      out.push({ math: m[1], display: false, line: i + 1 });
    }
  }
  return out;
}

let files = 0, formulas = 0, errors = 0;
for (const f of mdFiles(root)) {
  const text = fs.readFileSync(f, "utf8");
  if (!text.trim()) continue;
  files++;
  for (const { math, display, line } of extractMath(text)) {
    formulas++;
    try {
      katex.renderToString(math, { displayMode: display, throwOnError: true, strict: false });
    } catch (e) {
      errors++;
      const snippet = math.replace(/\s+/g, " ").slice(0, 90);
      console.log(`ERRORE ${path.relative(root, f)}:${line}`);
      console.log(`  ${snippet}`);
      console.log(`  ${String(e.message).split("\n")[0]}`);
    }
  }
}
console.log(`\n${files} file, ${formulas} formule, ${errors} errori`);
process.exit(errors ? 1 : 0);
