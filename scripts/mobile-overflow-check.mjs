// Mobile horizontal-overflow validator.
// Loads lessons in an emulated iPhone 15 (portrait + landscape) and reports any
// element that pushes the document wider than the viewport (true page overflow,
// i.e. not contained inside an overflow-x scroll container).
//
// Usage: node scripts/mobile-overflow-check.mjs
import { chromium } from 'playwright';

const BASE = 'http://127.0.0.1:5180/';

// iPhone 15 logical viewport (CSS px). Portrait 393x852, landscape 852x393, dpr 3.
const IPHONE15_UA =
  'Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Mobile/15E148 Safari/604.1';

const VIEWPORTS = [
  { name: 'portrait', width: 393, height: 852 },
  { name: 'landscape', width: 852, height: 393 },
];

// Content-heavy lessons used as stress cases (relative to /lessons/).
const FILES = [
  'cybersecurity/anno1/1_Analisi 1/M08_Limiti/UD3 - Limiti Notevoli/L1 - Limiti notevoli – introduzione e primi esempi.md',
  'cybersecurity/anno1/1_Analisi 1/M01_Matematica Liceale/UD1 - 1o e 2o grado/L1 - Rette.md',
  'cybersecurity/anno2/6_Crittografia/REPORT_COPERTURA_ESAMI.md', // wide tables
  'cybersecurity/anno2/5_Reti di Calcolatori/M5_Esame/RECAP_05_Esercizi_template.md', // wide tables
  'cybersecurity/anno2/3_Sistemi Operativi 2/M3_FileSystem/UD3/L2 - Tecniche di realizzazione della protezione.md', // code blocks
];

const overflowProbe = (vw) => {
  const results = [];
  const docW = document.documentElement.scrollWidth;
  const inScrollContainer = (el) => {
    let p = el.parentElement;
    while (p && p !== document.body) {
      const ox = getComputedStyle(p).overflowX;
      if (ox === 'auto' || ox === 'scroll' || ox === 'hidden') return true;
      p = p.parentElement;
    }
    return false;
  };
  for (const el of document.body.querySelectorAll('*')) {
    const r = el.getBoundingClientRect();
    if (r.width === 0 || r.height === 0) continue;
    if (r.right > vw + 1 && !inScrollContainer(el)) {
      results.push({
        tag: el.tagName.toLowerCase(),
        cls: (el.className && el.className.toString().slice(0, 40)) || '',
        right: Math.round(r.right),
        width: Math.round(r.width),
        text: (el.textContent || '').trim().slice(0, 50),
      });
    }
  }
  // Deduplicate: keep only the deepest offenders (skip an element if a child also overflows)
  return { docW, viewport: vw, offenders: results };
};

const run = async () => {
  const browser = await chromium.launch();
  let totalFail = 0;
  for (const vp of VIEWPORTS) {
    const context = await browser.newContext({
      viewport: { width: vp.width, height: vp.height },
      deviceScaleFactor: 3,
      isMobile: true,
      hasTouch: true,
      userAgent: IPHONE15_UA,
    });
    const page = await context.newPage();

    const assess = async (label) => {
      await page.waitForTimeout(700);
      const report = await page.evaluate(overflowProbe, vp.width);
      const overflowAmount = report.docW - vp.width;
      const tag = `[${vp.name} ${vp.width}px] ${label}`;
      if (overflowAmount > 1 || report.offenders.length) {
        totalFail++;
        console.log(`\n❌ ${tag}`);
        console.log(`   docScrollWidth=${report.docW} (overflow +${overflowAmount}px)`);
        const seen = new Set();
        for (const o of report.offenders.slice(0, 12)) {
          const key = o.tag + o.cls + o.text;
          if (seen.has(key)) continue;
          seen.add(key);
          console.log(`   ↳ <${o.tag} class="${o.cls}"> right=${o.right} w=${o.width}  "${o.text}"`);
        }
      } else {
        console.log(`✅ ${tag}  (docWidth=${report.docW})`);
      }
    };

    const openLesson = async (file) => {
      await page.goto(BASE, { waitUntil: 'domcontentloaded' });
      await page.evaluate((f) => {
        localStorage.setItem('cyberlocker:currentFile', JSON.stringify(f));
        localStorage.setItem('cyberlocker:viewMode', JSON.stringify('viewer'));
      }, file);
      await page.goto(BASE, { waitUntil: 'networkidle' });
      await page.waitForSelector('.markdown-body', { timeout: 8000 }).catch(() => {});
    };

    // 1) Each content-heavy lesson
    for (const file of FILES) {
      await openLesson(file);
      await assess(file.split('/').pop());
    }

    // 2) Sidebar opened as overlay over a lesson
    await openLesson(FILES[0]);
    await page.click('.sidebar-toggle').catch(() => {});
    await assess('· sidebar OPEN over lesson');

    // 3) Browse landing grid (no file)
    await page.evaluate(() => {
      localStorage.setItem('cyberlocker:viewMode', JSON.stringify('browse'));
      localStorage.removeItem('cyberlocker:currentFile');
    });
    await page.goto(BASE, { waitUntil: 'networkidle' });
    await page.waitForSelector('.browse-grid, .browse-view', { timeout: 8000 }).catch(() => {});
    await assess('· browse landing');

    await context.close();
  }
  await browser.close();
  console.log(`\n${totalFail === 0 ? '✅ ALL CLEAN — no horizontal overflow on iPhone 15' : `❌ ${totalFail} failing case(s)`}`);
  process.exit(totalFail === 0 ? 0 : 1);
};

run().catch((e) => { console.error(e); process.exit(2); });
