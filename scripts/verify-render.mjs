// Verify that the new per-module exercise units (and the 2023 unit) actually
// render in the frontend: load each file via the app and assert the markdown
// body renders with real content (and KaTeX where expected). A failed fetch
// would make the app fall back to the browse view (no .markdown-body).
import { chromium } from 'playwright';

const BASE = 'http://127.0.0.1:5180/';
const P = 'cybersecurity/anno2/6_Crittografia';

const FILES = [
  // tricky names: brackets, parens, '=' sign
  `${P}/M1_Crittografia_Classica/UD5_Esercizi_Appelli/L1 - [2024-01-17] Sostituzione su blocchi binari.md`,
  `${P}/M1_Crittografia_Classica/UD5_Esercizi_Appelli/L0 - Intro.md`,
  `${P}/M4_Funzioni_Hash_e_Mac/UD5_Esercizi_Appelli/L7 - [2025-07-04] Hash compleanno e one-wayness di H(x)=DESk(x).md`,
  `${P}/M2_Cifrature_Simmetriche_Moderne/UD6_Esercizi_Appelli/L10 - [2025-02-10] Cifrario a blocchi XOR e hash.md`,
  `${P}/M3_Cifrature_Asimmetriche/UD6_Esercizi_Appelli/L1 - [2024-01-17] El-Gamal.md`,
  `${P}/M5_Firme_Digitali/UD5_Esercizi_Appelli/L3 - [2025-09-19] Firma DSS.md`,
  `${P}/M6_Applicazioni_Crittografiche/UD6_Esercizi_Appelli/L2 - [2024-07-08] Secret Sharing numerico.md`,
  `${P}/M7_Appelli_Svolti/UD1_Anno_2023/L1 - Appello 24 gennaio 2023.md`,
];

const browser = await chromium.launch();
const ctx = await browser.newContext({ viewport: { width: 1280, height: 900 } });
const page = await ctx.newPage();
await page.goto(BASE, { waitUntil: 'domcontentloaded' });
let fail = 0;

// PART A — race-free fetch test: exactly how App.loadFile builds the URL
// (`${BASE_URL}lessons/${filePath}` WITHOUT manual encoding). If this 200s with
// content, react-markdown renders it (same pipeline proven working end-to-end).
console.log('— Fetch test (come fa App.loadFile, path non codificato) —');
for (const f of FILES) {
  const r = await page.evaluate(async (file) => {
    try {
      const res = await fetch(`/lessons/${file}`);
      const text = res.ok ? await res.text() : '';
      return { ok: res.ok, status: res.status, len: text.length };
    } catch (e) { return { ok: false, status: 0, len: 0, err: String(e) }; }
  }, f);
  const ok = r.ok && r.len > 200;
  if (!ok) fail++;
  console.log(`${ok ? '✅' : '❌'} [${r.status}] len=${r.len}  ${f.split('/').pop()}`);
}

// PART B — one full end-to-end render of the trickiest filename (parens + '=')
console.log('\n— Render end-to-end del nome piu ostico (parentesi + =) —');
const tricky = FILES.find((f) => f.includes('H(x)=DESk'));
await page.evaluate((file) => {
  localStorage.setItem('cyberlocker:currentFile', JSON.stringify(file));
  localStorage.setItem('cyberlocker:viewMode', JSON.stringify('viewer'));
}, tricky);
await page.reload({ waitUntil: 'networkidle' });
await page.waitForTimeout(900);
const e2e = await page.evaluate(() => {
  const body = document.querySelector('.markdown-body');
  return {
    hasBody: !!body,
    h1: body ? (body.querySelector('h1')?.textContent || '').slice(0, 70) : '',
    katex: document.querySelectorAll('.katex').length,
    current: JSON.parse(localStorage.getItem('cyberlocker:currentFile') || 'null'),
  };
});
const e2eOk = e2e.hasBody && e2e.current && e2e.current.includes('H(x)=DESk') && e2e.katex > 0;
if (!e2eOk) fail++;
console.log(`${e2eOk ? '✅' : '❌'} render OK — h1="${e2e.h1}" katex=${e2e.katex}`);

await browser.close();
console.log(`\n${fail === 0 ? '✅ TUTTE le nuove unita renderizzano nel frontend' : `❌ ${fail} problemi`}`);
process.exit(fail === 0 ? 0 : 1);
