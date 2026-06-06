// Capture iPhone 15 portrait screenshots for visual confirmation.
import { chromium } from 'playwright';
const BASE = 'http://127.0.0.1:5180/';
const UA = 'Mozilla/5.0 (iPhone; CPU iPhone OS 17_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.0 Mobile/15E148 Safari/604.1';
const out = 'c:/Users/nabis/samu-cyberlocker/scripts/_shots';
import { mkdirSync } from 'fs';
mkdirSync(out, { recursive: true });

const lesson = 'cybersecurity/anno1/1_Analisi 1/M08_Limiti/UD3 - Limiti Notevoli/L1 - Limiti notevoli – introduzione e primi esempi.md';

const browser = await chromium.launch();
const ctx = await browser.newContext({ viewport: { width: 393, height: 852 }, deviceScaleFactor: 2, isMobile: true, hasTouch: true, userAgent: UA });
const page = await ctx.newPage();

await page.goto(BASE);
await page.evaluate((f) => { localStorage.setItem('cyberlocker:currentFile', JSON.stringify(f)); localStorage.setItem('cyberlocker:viewMode', JSON.stringify('viewer')); }, lesson);
await page.goto(BASE, { waitUntil: 'networkidle' });
await page.waitForSelector('.markdown-body').catch(() => {});
await page.waitForTimeout(800);
await page.screenshot({ path: `${out}/portrait-lesson.png` });

await page.click('.sidebar-toggle').catch(() => {});
await page.waitForTimeout(500);
await page.screenshot({ path: `${out}/portrait-sidebar.png` });

await page.evaluate(() => { localStorage.setItem('cyberlocker:viewMode', JSON.stringify('browse')); localStorage.removeItem('cyberlocker:currentFile'); });
await page.goto(BASE, { waitUntil: 'networkidle' });
await page.waitForTimeout(700);
await page.screenshot({ path: `${out}/portrait-browse.png` });

await browser.close();
console.log('shots saved to', out);
