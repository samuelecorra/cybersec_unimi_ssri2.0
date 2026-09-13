import { chromium } from 'playwright';
import { spawn } from 'node:child_process';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const course = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const repo = path.resolve(course, '../../../..');
const base = 'http://127.0.0.1:5197/';
const server = spawn(process.execPath, [path.join(repo,'node_modules/vite/bin/vite.js'),
  '--host','127.0.0.1','--port','5197','--strictPort'], {cwd:repo,stdio:'ignore',windowsHide:true});
let browser;
try {
  let ready=false;
  for (let i=0;i<60;i++) {
    if (server.exitCode!==null) throw Error('Dedicated dev server exited');
    try { if ((await fetch(base)).ok) {ready=true;break;} } catch {}
    await new Promise(resolve=>setTimeout(resolve,250));
  }
  if (!ready) throw Error('Dev server unavailable');
  browser=await chromium.launch({headless:true});
  const reports=[];
  for (const [name,width,height] of [['desktop',1440,1000],['mobile',390,844]]) {
    const page=await browser.newPage({viewport:{width,height}});
    const errors=[];page.on('pageerror',e=>errors.push(e.message));
    const p='cybersecurity/extra/1_Intelligent_Systems/Lezione01/L01 - Introduzione ai sistemi intelligenti.md';
    await page.goto(base+'#/'+p.split('/').map(encodeURIComponent).join('/'),{waitUntil:'networkidle'});
    await page.waitForSelector('.markdown-body h2');
    const report=await page.locator('.markdown-body').evaluate(e=>({
      title:e.querySelector('h2')?.textContent,sections:e.querySelectorAll('h3').length,
      tables:e.querySelectorAll('table').length,pythonBlocks:e.querySelectorAll('pre code').length,
      mathErrors:e.querySelectorAll('.katex-error').length,
      math:e.querySelectorAll('.katex').length,
      leakedPlaceholder:e.textContent.includes('SOURCE_VISUAL')||e.textContent.includes('INSERT INSTRUCTOR'),
      overflow:e.scrollWidth>e.clientWidth+2,
    }));
    if (report.sections!==10||report.tables!==5||report.pythonBlocks!==2||report.mathErrors||report.leakedPlaceholder||report.overflow||errors.length) throw Error(JSON.stringify({name,report,errors}));
    const pdfResponse=await page.request.get(base+'lessons/'+p.replace(/[^/]+$/,'Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf').split('/').map(encodeURIComponent).join('/'));
    if (pdfResponse.status()!==200) throw Error('Source PDF unavailable');
    await page.goto(base+'#/cybersecurity/extra/1_Intelligent_Systems/'+encodeURIComponent('L0 - Indice del corso.md'),{waitUntil:'networkidle'});
    await page.waitForSelector('.markdown-body table');
    const indexLinks=await page.locator('.markdown-body table a').count();
    if(indexLinks!==27) throw Error('Index incomplete: '+indexLinks);
    reports.push({viewport:name,...report,indexLinks,sourcePdfStatus:pdfResponse.status(),errors});
    await page.close();
  }
  console.log(JSON.stringify({reports,screenshots:0},null,2));
} finally {
  if(browser) await browser.close();
  server.kill();
}
