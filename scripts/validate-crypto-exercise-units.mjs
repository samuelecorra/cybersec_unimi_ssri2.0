// Validate the generated per-module exercise units:
//  (a) verbatim fidelity: each generated file must END with the exact source
//      Domanda section (only a source-tag header is prepended).
//  (b) link integrity: every relative `](...)` target must resolve to a real file.
import { readFileSync, existsSync, readdirSync } from 'fs';
import { join, dirname } from 'path';

const CRYPTO = 'c:/Users/nabis/samu-cyberlocker/lessons/cybersecurity/anno2/6_Crittografia';
const M7 = join(CRYPTO, 'M7_Appelli_Svolti');

// Re-derive source sections (same splitter as the builder).
function extractDomande(text) {
  const lines = text.split('\n');
  const h2 = [];
  for (let i = 0; i < lines.length; i++) if (/^##\s/.test(lines[i])) h2.push(i);
  const sections = {};
  for (let k = 0; k < h2.length; k++) {
    const m = lines[h2[k]].match(/^##\s+Domanda\s+(\d+)/);
    if (!m) continue;
    const end = k + 1 < h2.length ? h2[k + 1] : lines.length;
    let body = lines.slice(h2[k], end);
    while (body.length && body[body.length - 1].trim() === '') body.pop();
    if (body.length && body[body.length - 1].trim() === '---') body.pop();
    while (body.length && body[body.length - 1].trim() === '') body.pop();
    sections[Number(m[1])] = body.join('\n');
  }
  return sections;
}

// Same link-fix transform as the builder (keep in sync).
const PATHFIX = [
  ['../../M2_Cifrature_Simmetriche_Moderne/UD3/L1 - AES.md', '../../M2_Cifrature_Simmetriche_Moderne/UD3/L1 - AES (Advanced Encryption Standard).md'],
  ['../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 - Struttura del DES.md', '../../M2_Cifrature_Simmetriche_Moderne/UD2/L1 -  Struttura del DES.md'],
  ['../../M2_Cifrature_Simmetriche_Moderne/UD2/L3 - Modalità operative del DES.md', '../../M2_Cifrature_Simmetriche_Moderne/UD2/L2 - Modalità operative del DES.md'],
  ['../../M1_Crittografia_Classica/UD1/L2 - Teoria dei numeri.md', '../../M3_Cifrature_Asimmetriche/UD2/L2 - Aritmetica modulare e numeri primi.md'],
  ['../../M5_Firme_Digitali/UD2/L1 - Firma digitale con RSA.md', '../../M5_Firme_Digitali/UD2/L1 - Schema RSA.md'],
  ['../../M4_Funzioni_Hash_e_Mac/UD2/L1 - MAC.md', '../../M4_Funzioni_Hash_e_Mac/UD3/L1 - Codici MAC.md'],
  ['../../M6_Applicazioni_Crittografiche/UD3/L1 - Introduzione al Secret Sharing.md', '../../M6_Applicazioni_Crittografiche/UD3/L1 - Scenari e applicazioni.md'],
  ['../../M1_Crittografia_Classica/UD2/L1 - Cifrari monoalfabetici.md', '../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md'],
  ['../../M1_Crittografia_Classica/UD2/L2 - Cifrario affine.md', '../../M1_Crittografia_Classica/UD2/L3 - Cifrari a sostituzione.md'],
  ['../../M1_Crittografia_Classica/UD2/L4 - Cifrari a permutazione.md', '../../M1_Crittografia_Classica/UD4_Approfondimenti_Esame/L2 - Composizione di cifrari a permutazione.md'],
  ['../../M1_Crittografia_Classica/UD2/L5 - Cifrario di Hill.md', '../../M1_Crittografia_Classica/UD3/L2 - Il Cifrario di Hill.md'],
];
const encUrl = (p) => p.replace(/ /g, '%20').replace(/\(/g, '%28').replace(/\)/g, '%29');
function fixLinks(section) {
  let s = section;
  for (const [w, r] of PATHFIX) s = s.split(w).join(r);
  s = s.replace(/\[`([^`]+\.md)`\]\([^\n]*?\.md>?\)/g, (_m, p) => `[\`${p}\`](${encUrl(p)})`);
  return s;
}

const MODS = [
  ['M1_Crittografia_Classica', 'UD5_Esercizi_Appelli'],
  ['M2_Cifrature_Simmetriche_Moderne', 'UD6_Esercizi_Appelli'],
  ['M3_Cifrature_Asimmetriche', 'UD6_Esercizi_Appelli'],
  ['M4_Funzioni_Hash_e_Mac', 'UD5_Esercizi_Appelli'],
  ['M5_Firme_Digitali', 'UD5_Esercizi_Appelli'],
  ['M6_Applicazioni_Crittografiche', 'UD6_Esercizi_Appelli'],
];

// Build a lookup of every source section text -> true, for fidelity matching.
const allSections = [];
for (const ud of ['UD2_Anno_2024', 'UD3_Anno_2025']) {
  for (const f of readdirSync(join(M7, ud)).filter((x) => /^L[1-9].*\.md$/.test(x))) {
    const secs = extractDomande(readFileSync(join(M7, ud, f), 'utf8'));
    for (const n of Object.keys(secs)) allSections.push(fixLinks(secs[n]));
  }
}

let fidOk = 0, fidBad = 0, linkOk = 0, linkBad = 0;
const problems = [];

for (const [mdir, ud] of MODS) {
  const dir = join(CRYPTO, mdir, ud);
  for (const f of readdirSync(dir).filter((x) => /^L[1-9].*\.md$/.test(x))) {
    const full = join(dir, f);
    const content = readFileSync(full, 'utf8');

    // (a) fidelity: the file must end with exactly one of the source sections (+trailing \n)
    const matched = allSections.some((s) => content.endsWith(s + '\n'));
    if (matched) fidOk++; else { fidBad++; problems.push(`FIDELITY: ${mdir}/${ud}/${f} does not end with a verbatim source section`); }

    // (b) links — after URL regen every URL is fully %-encoded (no literal parens)
    const links = [...content.matchAll(/\]\((?!<)([^)]+)\)/g)].map((m) => m[1]);
    for (let target of links) {
      if (/^https?:/.test(target)) continue;
      target = target.split('#')[0];
      if (!target) continue;
      const resolved = join(dirname(full), decodeURIComponent(target));
      if (existsSync(resolved)) linkOk++;
      else { linkBad++; problems.push(`LINK MISSING: ${mdir}/${ud}/${f}\n     -> ${target}`); }
    }
  }
}

console.log(`Fidelity verbatim: ${fidOk} OK, ${fidBad} FAILED`);
console.log(`Link relativi:     ${linkOk} OK, ${linkBad} MISSING`);
if (problems.length) {
  console.log('\nPROBLEMI:');
  problems.slice(0, 40).forEach((p) => console.log('  - ' + p));
  process.exit(1);
}
console.log('\n✅ Tutto valido: copie verbatim e link risolti.');
