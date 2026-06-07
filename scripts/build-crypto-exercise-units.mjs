// Build per-module "exercise" units for Crittografia.
//
// For each module M1..M6 it creates a new didactic unit (numbered n+1 vs the
// current max UD) that aggregates ALL the M7 exam questions belonging ONLY to
// that module — one .md per exercise, copied VERBATIM from the source exam
// (the relative `../../Mx/...` prerequisite links are already correct because
// the new files sit at the same depth under 6_Crittografia/), plus a source tag.
//
// Idempotent: re-running overwrites the generated files.
import { readFileSync, writeFileSync, mkdirSync, readdirSync, rmSync } from 'fs';
import { join } from 'path';

const CRYPTO = 'c:/Users/nabis/samu-cyberlocker/lessons/cybersecurity/anno2/6_Crittografia';
const M7 = join(CRYPTO, 'M7_Appelli_Svolti');

const MODULES = {
  M1: { dir: 'M1_Crittografia_Classica',        ud: 'UD5_Esercizi_Appelli', title: 'Crittografia Classica' },
  M2: { dir: 'M2_Cifrature_Simmetriche_Moderne', ud: 'UD6_Esercizi_Appelli', title: 'Cifrature Simmetriche Moderne' },
  M3: { dir: 'M3_Cifrature_Asimmetriche',        ud: 'UD6_Esercizi_Appelli', title: 'Cifrature Asimmetriche' },
  M4: { dir: 'M4_Funzioni_Hash_e_Mac',           ud: 'UD5_Esercizi_Appelli', title: 'Funzioni Hash e MAC' },
  M5: { dir: 'M5_Firme_Digitali',                ud: 'UD5_Esercizi_Appelli', title: 'Firme Digitali' },
  M6: { dir: 'M6_Applicazioni_Crittografiche',   ud: 'UD6_Esercizi_Appelli', title: 'Applicazioni Crittografiche' },
};

// q maps Domanda number -> [moduleKey, topic slug]. Gli appelli 2024/2025 sono
// elencati per primi per preservare la numerazione esistente delle unità
// per-modulo; gli appelli 2023 sono accodati in fondo (ogni file porta comunque
// il tag [AAAA-MM-GG], quindi l'anno resta evidente).
// NB: 230908 (08/09/2023) ha testo identico a 230907 → escluso per non duplicare.
const EXAMS = [
  { ud: 'UD2_Anno_2024', file: 'L1 - Appello 17 gennaio 2024.md', iso: '2024-01-17', label: '17 gennaio 2024',
    q: { 1: ['M1', 'Sostituzione su blocchi binari'], 2: ['M2', 'AES'], 3: ['M4', 'Funzioni hash'], 4: ['M3', 'El-Gamal'] } },
  { ud: 'UD2_Anno_2024', file: 'L2 - Appello 20 febbraio 2024.md', iso: '2024-02-20', label: '20 febbraio 2024',
    q: { 1: ['M1', 'Playfair'], 2: ['M2', 'DES simmetria cifratura-decifratura'], 3: ['M4', 'MAC e HMAC'], 4: ['M5', 'Firma digitale DSS'] } },
  { ud: 'UD2_Anno_2024', file: 'L3 - Appello 24 giugno 2024.md', iso: '2024-06-24', label: '24 giugno 2024',
    q: { 1: ['M1', 'Sicurezza e cifrario affine composto'], 2: ['M2', 'Feistel 2 round con funzione aritmetica'], 3: ['M3', 'RSA'], 4: ['M5', 'Firma digitale DSS'] } },
  { ud: 'UD2_Anno_2024', file: 'L4 - Appello 5 luglio 2024.md', iso: '2024-07-05', label: '5 luglio 2024',
    q: { 1: ['M1', 'Sicurezza e cifrario affine composto'], 2: ['M2', 'Feistel 2 round con funzione XOR'], 3: ['M3', 'RSA'], 4: ['M6', 'Secret Sharing'] } },
  { ud: 'UD2_Anno_2024', file: 'L5 - Appello 8 luglio 2024.md', iso: '2024-07-08', label: '8 luglio 2024',
    q: { 1: ['M2', 'Cifratura a blocchi KOSTI'], 2: ['M2', 'Modalita operative del DES'], 3: ['M3', 'RSA sicurezza'], 4: ['M6', 'Secret Sharing numerico'] } },
  { ud: 'UD2_Anno_2024', file: 'L6 - Appello 6 settembre 2024.md', iso: '2024-09-06', label: '6 settembre 2024',
    q: { 1: ['M1', 'Cifrari a permutazione e composizione'], 2: ['M4', 'MAC e HMAC'], 3: ['M3', 'RSA equivalenza con fattorizzazione'], 4: ['M6', 'Secret Sharing numerico'] } },
  { ud: 'UD2_Anno_2024', file: 'L7 - Appello 20 settembre 2024.md', iso: '2024-09-20', label: '20 settembre 2024',
    q: { 1: ['M1', 'Hill'], 2: ['M2', 'Feistel 2 round mod 15'], 3: ['M4', 'Hash identita e paradosso del compleanno'], 4: ['M3', 'RSA completo'] } },
  { ud: 'UD3_Anno_2025', file: 'L1 - Appello 20 gennaio 2025.md', iso: '2025-01-20', label: '20 gennaio 2025',
    q: { 1: ['M2', 'Cifratura simmetrica a blocchi XOR e somma'], 2: ['M2', 'DES simmetria cifratura-decifratura'], 3: ['M4', 'Funzioni hash'], 4: ['M3', 'El-Gamal'] } },
  { ud: 'UD3_Anno_2025', file: 'L2 - Appello 10 febbraio 2025.md', iso: '2025-02-10', label: '10 febbraio 2025',
    q: { 1: ['M1', 'Vigenere'], 2: ['M2', 'Cifrario a blocchi XOR e hash'], 3: ['M4', 'Funzione hash identita'], 4: ['M6', 'Secret Sharing'] } },
  { ud: 'UD3_Anno_2025', file: 'L3 - Appello 4 luglio 2025.md', iso: '2025-07-04', label: '4 luglio 2025',
    q: { 1: ['M1', 'Hill'], 2: ['M2', 'Blowfish'], 3: ['M4', 'Hash compleanno e one-wayness di H(x)=DESk(x)'], 4: ['M6', 'Secret Sharing'] } },
  { ud: 'UD3_Anno_2025', file: 'L4 - Appello 15 luglio 2025.md', iso: '2025-07-15', label: '15 luglio 2025',
    q: { 1: ['M1', 'Sostituzione su parole binarie'], 2: ['M4', 'Funzioni hash'], 3: ['M3', 'RSA e Double-RSA'], 4: ['M6', 'Secret Sharing'] } },
  { ud: 'UD3_Anno_2025', file: 'L5 - Appello 5 settembre 2025.md', iso: '2025-09-05', label: '5 settembre 2025',
    q: { 1: ['M1', 'Cifrario affine'], 2: ['M2', 'Double DES e meet-in-the-middle'], 3: ['M4', 'Funzioni MAC'], 4: ['M6', 'Secret Sharing'] } },
  { ud: 'UD3_Anno_2025', file: 'L6 - Appello 19 settembre 2025.md', iso: '2025-09-19', label: '19 settembre 2025',
    q: { 1: ['M1', 'Cifrari classici attacchi e resistenza'], 2: ['M2', 'DES doppio e AES-X'], 3: ['M5', 'Firma DSS'], 4: ['M6', 'Secret Sharing'] } },
  // ── Appelli 2023 (accodati per preservare la numerazione 2024/2025) ──
  { ud: 'UD1_Anno_2023', file: 'L1 - Appello 24 gennaio 2023.md', iso: '2023-01-24', label: '24 gennaio 2023',
    q: { 1: ['M1', 'Cifrari classici attacchi e resistenza'], 2: ['M4', 'MAC AES-hash con attacco di collisione'], 3: ['M5', 'Firma DSS'], 4: ['M6', 'Secret Sharing'] } },
  { ud: 'UD1_Anno_2023', file: 'L2 - Appello 16 febbraio 2023.md', iso: '2023-02-16', label: '16 febbraio 2023',
    q: { 1: ['M1', 'Vigenere IC e MIC'], 2: ['M2', 'Cifrario a blocchi hash e XOR'], 3: ['M2', 'Modalita operative del DES'], 4: ['M3', 'El-Gamal'] } },
  { ud: 'UD1_Anno_2023', file: 'L3 - Appello 22 giugno 2023.md', iso: '2023-06-22', label: '22 giugno 2023',
    q: { 1: ['M1', 'Cifrario affine e doppia cifratura'], 2: ['M2', 'Modalita operative del DES'], 3: ['M3', 'RSA correttezza e fattorizzazione da phi'], 4: ['M5', 'Firma DSS e caso (a,0)'] } },
  { ud: 'UD1_Anno_2023', file: 'L4 - Appello 7 luglio 2023.md', iso: '2023-07-07', label: '7 luglio 2023',
    q: { 1: ['M1', 'Cifrari classici attacchi e resistenza'], 2: ['M2', 'Cifrario a blocchi hash e XOR'], 3: ['M6', 'Certificati digitali'], 4: ['M3', 'El-Gamal'] } },
  { ud: 'UD1_Anno_2023', file: 'L5 - Appello 12 luglio 2023.md', iso: '2023-07-12', label: '12 luglio 2023',
    q: { 1: ['M1', 'Hill'], 2: ['M2', 'Feistel a 2 round'], 3: ['M3', 'Parametri RSA e cenni ECC'], 4: ['M6', 'Diffie-Hellman'] } },
  { ud: 'UD1_Anno_2023', file: 'L6 - Appello 7 settembre 2023.md', iso: '2023-09-07', label: '7 settembre 2023',
    q: { 1: ['M1', 'Playfair'], 2: ['M2', 'Modalita OFB con keystream riusato'], 3: ['M4', 'MAC e HMAC'], 4: ['M6', 'Secret Sharing'] } },
  { ud: 'UD1_Anno_2023', file: 'L8 - Appello 22 settembre 2023.md', iso: '2023-09-22', label: '22 settembre 2023',
    q: { 1: ['M1', 'Hill'], 2: ['M2', 'Meet-in-the-middle e AES-X'], 3: ['M3', 'RSA vs fattorizzazione e ottimizzazioni'], 4: ['M6', 'Diffie-Hellman'] } },
];

// Pre-existing broken prerequisite links in the M7 source (wrong filenames):
// raw path -> correct existing file. Applied to the COPIES so links resolve.
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

// Encode only the chars that break Markdown link parsing (space + parens).
const encUrl = (p) => p.replace(/ /g, '%20').replace(/\(/g, '%28').replace(/\)/g, '%29');

// ISO date (aaaa-mm-gg) -> Italian date (gg-mm-aaaa) for filenames.
const itDate = (iso) => { const [y, m, d] = iso.split('-'); return `${d}-${m}-${y}`; };

// Fix broken filenames, then regenerate every link URL from its authoritative
// code-span path  [`PATH`](...)  so the URL always matches a real file.
function fixLinks(section) {
  let s = section;
  for (const [w, r] of PATHFIX) s = s.split(w).join(r);
  s = s.replace(/\[`([^`]+\.md)`\]\([^\n]*?\.md>?\)/g, (_m, p) => `[\`${p}\`](${encUrl(p)})`);
  return s;
}

const asciiSlug = (s) =>
  s.normalize('NFD').replace(/[̀-ͯ]/g, '')   // strip diacritics
    .replace(/[^A-Za-z0-9 ()=.-]/g, '')                 // filesystem-safe
    .replace(/\s+/g, ' ').trim();

const enc = (s) => s.replace(/ /g, '%20');

// Split an exam file into { domandaNumber -> verbatim section text }.
function extractDomande(text) {
  const lines = text.split('\n');
  const h2 = [];
  for (let i = 0; i < lines.length; i++) if (/^##\s/.test(lines[i])) h2.push(i);
  const sections = {};
  for (let k = 0; k < h2.length; k++) {
    const start = h2[k];
    const m = lines[start].match(/^##\s+Domanda\s+(\d+)/);
    if (!m) continue;
    const end = k + 1 < h2.length ? h2[k + 1] : lines.length;
    let body = lines.slice(start, end);
    // trim trailing blank lines and the `---` separator that precedes the next section
    while (body.length && body[body.length - 1].trim() === '') body.pop();
    if (body.length && body[body.length - 1].trim() === '---') body.pop();
    while (body.length && body[body.length - 1].trim() === '') body.pop();
    sections[Number(m[1])] = body.join('\n');
  }
  return sections;
}

// Collect exercises per module, preserving chronological order.
const perModule = {}; // moduleKey -> [{slug, label, iso, examUd, examFile, qn, section}]
for (const ex of EXAMS) {
  const text = readFileSync(join(M7, ex.ud, ex.file), 'utf8');
  const domande = extractDomande(text);
  for (const qn of Object.keys(ex.q)) {
    const [mod, slug] = ex.q[qn];
    const section = domande[Number(qn)];
    if (!section) { console.warn(`!! missing Domanda ${qn} in ${ex.file}`); continue; }
    (perModule[mod] ||= []).push({ slug, label: ex.label, iso: ex.iso, examUd: ex.ud, examFile: ex.file, qn, section });
  }
}

let totalFiles = 0;
const summary = [];
for (const mod of Object.keys(MODULES)) {
  const meta = MODULES[mod];
  const items = perModule[mod] || [];
  const dir = join(CRYPTO, meta.dir, meta.ud);
  mkdirSync(dir, { recursive: true });
  // Clean stale files first so chronological renumbering doesn't leave orphans
  for (const old of readdirSync(dir)) if (old.endsWith('.md')) rmSync(join(dir, old));

  // Chronological order (oldest first); 2-digit numeric prefix keeps the
  // filesystem/IDE sort correct while the [gg-mm-aaaa] tag shows the Italian date.
  items.sort((a, b) => a.iso.localeCompare(b.iso));

  const indexLines = [];
  items.forEach((it, idx) => {
    const n = idx + 1;
    const fname = `${String(n).padStart(2, '0')} - [${itDate(it.iso)}] ${asciiSlug(it.slug)}.md`;
    const backRel = `../../M7_Appelli_Svolti/${it.examUd}/${enc(it.examFile)}`;
    const header =
`# ${it.slug} — Esercizio d'esame

> 🗂️ **Fonte:** appello del **${it.label}**, Domanda ${it.qn} — Modulo ${mod} (${meta.title}).
> Esercizio estratto integralmente (traccia + soluzione passo-passo) dall'[appello del ${it.label}](${backRel}). Vedi l'appello completo per le altre domande e le osservazioni di sessione.

---

`;
    writeFileSync(join(dir, fname), header + fixLinks(it.section) + '\n', 'utf8');
    indexLines.push(`${n}. [${it.slug}](${encUrl(fname)}) — appello ${it.label}, Domanda ${it.qn}`);
    totalFiles++;
  });

  // L0 intro for the unit
  const intro =
`# ${meta.title} — Esercizi d'esame (${meta.ud})

> 📌 **Unità extra di allenamento.** Raccoglie **tutti gli esercizi degli appelli svolti** (modulo M7) che riguardano *esclusivamente* il modulo ${mod} — ${meta.title}. Un file per esercizio, in ordine cronologico, con traccia originale e soluzione completa passo-passo. Pensata per ripassare un singolo argomento attraversando tutti gli appelli in cui è stato chiesto.

> 💡 Gli appelli completi (con tutte e 4 le domande di ciascuna sessione) restano in [M7 Appelli Svolti](../../M7_Appelli_Svolti/). Questa unità è una **vista per argomento** degli stessi esercizi.

## Elenco esercizi (${items.length})

${indexLines.join('\n')}
`;
  writeFileSync(join(dir, '00 - Intro.md'), intro, 'utf8');
  summary.push(`${mod} (${meta.dir}/${meta.ud}): ${items.length} esercizi`);
}

console.log('Esercizi per modulo:');
summary.forEach((s) => console.log('  ' + s));
console.log(`\nTotale file esercizio creati: ${totalFiles} (+ 6 L0 intro)`);
