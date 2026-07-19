import { chromium } from 'playwright';

const BASE = process.env.HASH_ROUTING_BASE_URL || 'http://127.0.0.1:5180/';
const subjectPath = 'cybersecurity/anno1/3_Programmazione';
const lessonPath = `${subjectPath}/c/teoria-c/README.md`;
const sourcePath = `${subjectPath}/c/teoria-c/M07_C_avanzato/4_combinare_typedef+enum/main.c`;
const hashFor = path => `#/${path.split('/').map(encodeURIComponent).join('/')}`;

const browser = await chromium.launch();
const page = await browser.newPage({ viewport: { width: 1280, height: 900 } });
let failures = 0;

async function check(label, operation) {
  try {
    await operation();
    console.log(`OK ${label}`);
  } catch (error) {
    failures++;
    console.log(`FAIL ${label}: ${error.message}`);
  }
}

await check('deep link all\'anno', async () => {
  const yearPath = 'cybersecurity/anno1';
  await page.goto(`${BASE}${hashFor(yearPath)}`, { waitUntil: 'networkidle' });
  await page.waitForSelector('.browse-card--subject');
  const titles = await page.locator('.browse-card-title').allTextContents();
  if (!titles.some(title => title.includes('Programmazione'))) throw new Error('materia Programmazione non visibile');
  if (windowHash(await page.url()) !== hashFor(yearPath)) throw new Error('hash anno non canonico');
});

await check('deep link alla materia', async () => {
  await page.goto(`${BASE}${hashFor(subjectPath)}`, { waitUntil: 'networkidle' });
  await page.waitForSelector('.browse-card--subject');
  const titles = await page.locator('.browse-card-title').allTextContents();
  if (!titles.some(title => title.trim().toLowerCase() === 'c')) throw new Error('cartella C non visibile');
  if (!titles.some(title => title.trim().toLowerCase() === 'java')) throw new Error('cartella Java non visibile');
  if (page.url().split('#')[1] !== hashFor(subjectPath).slice(1)) throw new Error('hash materia non canonico');
});

await check('cronologia avanti/indietro tra cartelle', async () => {
  const cCard = page.locator('.browse-card--subject').filter({
    has: page.locator('.browse-card-title', { hasText: /^c$/i }),
  }).first();
  await cCard.click();
  await page.waitForFunction(expected => window.location.hash === expected, `${hashFor(subjectPath)}/c`);
  await page.goBack({ waitUntil: 'networkidle' });
  await page.waitForFunction(expected => window.location.hash === expected, hashFor(subjectPath));
  await page.waitForSelector('.browse-card--subject');
});

await check('deep link e refresh della lezione', async () => {
  await page.goto(`${BASE}${hashFor(lessonPath)}`, { waitUntil: 'networkidle' });
  await page.waitForSelector('.markdown-body');
  await page.reload({ waitUntil: 'networkidle' });
  await page.waitForSelector('.markdown-body');
  const current = await page.locator('.breadcrumb-current').textContent();
  if (!current?.includes('README')) throw new Error('lezione non ripristinata');
  if (windowHash(await page.url()) !== hashFor(lessonPath)) throw new Error('hash lezione alterato');
});

await check('caratteri speciali nel link a un sorgente', async () => {
  await page.goto(`${BASE}${hashFor(sourcePath)}`, { waitUntil: 'networkidle' });
  await page.waitForSelector('.source-code');
  const source = await page.locator('.source-code').textContent();
  if (!source?.includes('typedef')) throw new Error('sorgente non caricato');
  const routedPath = decodeURIComponent(windowHash(await page.url()).slice(2));
  if (routedPath !== sourcePath) throw new Error('caratteri speciali non preservati nel round trip');
});

await check('rifiuto di traversal e percorsi inesistenti', async () => {
  for (const unsafeHash of [
    '#/cybersecurity/%2E%2E/anno3',
    '#/%2Fetc/passwd',
    '#/cybersecurity/anno9/materia-inesistente',
    '#/%E0%A4%A',
  ]) {
    await page.goto(`${BASE}${unsafeHash}`, { waitUntil: 'domcontentloaded' });
    await page.waitForFunction(() => window.location.hash === '#/');
    await page.waitForSelector('.browse-hero');
  }
});

await check('migrazione trasparente dello stato localStorage senza hash', async () => {
  await page.goto(`${BASE}#/`, { waitUntil: 'domcontentloaded' });
  await page.evaluate(path => {
    localStorage.setItem('cyberlocker:currentFile', JSON.stringify(path));
    localStorage.setItem('cyberlocker:viewMode', JSON.stringify('viewer'));
  }, lessonPath);
  await page.goto(BASE, { waitUntil: 'networkidle' });
  await page.waitForSelector('.markdown-body');
  if (windowHash(await page.url()) !== hashFor(lessonPath)) throw new Error('stato precedente non migrato');
});

function windowHash(url) {
  return `#${url.split('#')[1] ?? ''}`;
}

await browser.close();
console.log(failures === 0 ? 'HASH_ROUTING_E2E_OK' : `HASH_ROUTING_E2E_FAIL=${failures}`);
process.exit(failures === 0 ? 0 : 1);
