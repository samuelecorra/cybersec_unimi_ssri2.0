import { chromium } from 'playwright';

const BASE = process.env.SOURCE_RENDERER_BASE_URL || 'http://127.0.0.1:5180/';
const PREFIX = 'cybersecurity/anno1/3_Programmazione';
const CASES = [
  {
    path: `${PREFIX}/c/teoria-c/M03_Condizioni_&_Logica/4_switches/main.c`,
    badge: 'C',
    marker: 'switch',
    highlighted: true,
  },
  {
    path: `${PREFIX}/c/teoria-c/M07_C_avanzato/7_preprocessore_e_macro/config.h`,
    badge: 'Header C',
    marker: '#ifndef',
    highlighted: true,
  },
  {
    path: `${PREFIX}/c/teoria-c/M07_C_avanzato/4_combinare_typedef+enum/main.c`,
    badge: 'C',
    marker: 'typedef',
    highlighted: true,
  },
  {
    path: `${PREFIX}/java/tracce-java/E5_Giochi/PinkPong/PongGame.java`,
    badge: 'Java',
    marker: 'class PongGame',
    highlighted: true,
  },
  {
    path: `${PREFIX}/c/tracce-c/1-esercizi-stringhe/anagrammaSens/README.txt`,
    badge: 'Testo',
    marker: 'anagram',
    highlighted: false,
  },
];

const browser = await chromium.launch();
const context = await browser.newContext({ viewport: { width: 1280, height: 900 } });
await context.grantPermissions(['clipboard-read', 'clipboard-write'], { origin: new URL(BASE).origin });
const page = await context.newPage();
let failures = 0;

async function openFile(filePath, selector = '.source-viewer') {
  await page.goto(BASE, { waitUntil: 'domcontentloaded' });
  await page.evaluate(path => {
    localStorage.setItem('cyberlocker:currentFile', JSON.stringify(path));
    localStorage.setItem('cyberlocker:viewMode', JSON.stringify('viewer'));
  }, filePath);
  await page.reload({ waitUntil: 'networkidle' });
  await page.waitForSelector(selector, { timeout: 8000 });
  if (selector === '.source-viewer') await page.waitForSelector('.source-code', { timeout: 8000 });
}

for (const testCase of CASES) {
  try {
    await openFile(testCase.path);
    const report = await page.evaluate(async ({ path, marker, highlighted }) => {
      const encodedPath = path.split('/').map(segment => encodeURIComponent(segment)
        .replace(/%26/gi, '&')
        .replace(/%2B/gi, '+')).join('/');
      const response = await fetch(`/lessons/${encodedPath}`);
      const raw = response.ok ? await response.text() : '';
      const source = document.querySelector('.source-code code');
      const text = source?.textContent ?? '';
      const physicalLines = raw.length === 0 ? 0 : raw.split('\n').length;
      return {
        fetchStatus: response.status,
        badge: document.querySelector('.source-language-badge')?.textContent?.trim(),
        marker: raw.toLowerCase().includes(marker.toLowerCase()),
        contentMatches: text === raw,
        lineNumbers: document.querySelectorAll('.source-line-numbers span').length,
        physicalLines,
        highlighted: document.querySelectorAll('.source-code .hljs-keyword, .source-code .hljs-meta').length > 0,
        markdownBody: Boolean(document.querySelector('.markdown-body')),
        documentOverflow: document.documentElement.scrollWidth - document.documentElement.clientWidth,
        expectedHighlight: highlighted,
      };
    }, testCase);

    const ok = report.fetchStatus === 200
      && report.badge === testCase.badge
      && report.marker
      && report.contentMatches
      && report.lineNumbers === report.physicalLines
      && !report.markdownBody
      && report.documentOverflow <= 1
      && (!testCase.highlighted || report.highlighted);
    if (!ok) failures++;
    console.log(`${ok ? 'OK' : 'FAIL'} ${testCase.path.split('/').pop()} ${JSON.stringify(report)}`);
  } catch (error) {
    failures++;
    console.log(`FAIL ${testCase.path}: ${error.message}`);
  }
}

const MEDIA_CASES = [
  {
    path: `${PREFIX}/c/teoria-c/M01_Fondamenti/10_Mad_Libs_Game/image.png`,
    kind: 'image',
    badge: 'Immagine',
  },
  {
    path: `${PREFIX}/java/tracce-java/E0_esercizi_yt/T11_MusicPlayers/audio/Lazza-Molotov.wav`,
    kind: 'audio',
    badge: 'Audio',
  },
];

for (const mediaCase of MEDIA_CASES) {
  try {
    await openFile(mediaCase.path, '.asset-viewer');
    const report = await page.evaluate(async ({ path, kind, badge }) => {
      const encodedPath = path.split('/').map(segment => encodeURIComponent(segment)
        .replace(/%26/gi, '&')
        .replace(/%2B/gi, '+')).join('/');
      const response = await fetch(`/lessons/${encodedPath}`);
      const mediaElement = document.querySelector(kind === 'image' ? '.asset-image' : '.asset-audio');
      if (kind === 'image' && mediaElement && !mediaElement.complete) {
        await new Promise(resolve => mediaElement.addEventListener('load', resolve, { once: true }));
      }
      return {
        fetchStatus: response.status,
        contentType: response.headers.get('content-type'),
        badge: document.querySelector('.source-language-badge')?.textContent?.trim(),
        element: Boolean(mediaElement),
        imageLoaded: kind !== 'image' || mediaElement?.naturalWidth > 0,
        documentOverflow: document.documentElement.scrollWidth - document.documentElement.clientWidth,
        expectedBadge: badge,
      };
    }, mediaCase);
    const ok = report.fetchStatus === 200
      && report.contentType?.startsWith(mediaCase.kind)
      && report.badge === mediaCase.badge
      && report.element
      && report.imageLoaded
      && report.documentOverflow <= 1;
    if (!ok) failures++;
    console.log(`${ok ? 'OK' : 'FAIL'} ${mediaCase.path.split('/').pop()} ${JSON.stringify(report)}`);
  } catch (error) {
    failures++;
    console.log(`FAIL ${mediaCase.path}: ${error.message}`);
  }
}

try {
  await openFile(CASES[3].path);
  await page.locator('.source-action', { hasText: 'A capo' }).click();
  const wrapped = await page.locator('.source-viewer').evaluate(element => element.classList.contains('source-viewer--wrap'));
  await page.locator('.source-action', { hasText: 'Copia' }).click();
  const copied = await page.locator('.source-action.success').textContent();
  if (!wrapped || !copied?.includes('Copiato')) failures++;
  console.log(`${wrapped && copied?.includes('Copiato') ? 'OK' : 'FAIL'} controlli wrap/copia`);
} catch (error) {
  failures++;
  console.log(`FAIL controlli wrap/copia: ${error.message}`);
}

try {
  await page.goto(BASE, { waitUntil: 'networkidle' });
  await page.click('.sidebar-toggle');
  await page.fill('.search-input', 'PongGame.java');
  const result = page.locator('.search-result-item', { hasText: 'PongGame.java' }).first();
  await result.waitFor({ state: 'visible', timeout: 5000 });
  await result.click();
  await page.waitForSelector('.source-viewer');
  console.log('OK ricerca e apertura sorgente');
} catch (error) {
  failures++;
  console.log(`FAIL ricerca sorgenti: ${error.message}`);
}

try {
  await page.setViewportSize({ width: 393, height: 852 });
  await openFile(CASES[3].path);
  const mobileReport = await page.evaluate(() => ({
    viewerVisible: Boolean(document.querySelector('.source-viewer')),
    documentOverflow: document.documentElement.scrollWidth - document.documentElement.clientWidth,
    codeCanScroll: document.querySelector('.source-scroll')?.scrollWidth
      >= document.querySelector('.source-scroll')?.clientWidth,
  }));
  const mobileOk = mobileReport.viewerVisible
    && mobileReport.documentOverflow <= 1
    && mobileReport.codeCanScroll;
  if (!mobileOk) failures++;
  console.log(`${mobileOk ? 'OK' : 'FAIL'} layout mobile ${JSON.stringify(mobileReport)}`);
} catch (error) {
  failures++;
  console.log(`FAIL layout mobile: ${error.message}`);
}

await browser.close();
console.log(failures === 0 ? 'SOURCE_RENDERER_E2E_OK' : `SOURCE_RENDERER_E2E_FAIL=${failures}`);
process.exit(failures === 0 ? 0 : 1);
