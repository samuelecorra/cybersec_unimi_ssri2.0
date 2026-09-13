#!/usr/bin/env python3
"""Coverage + progress + index update for one finished lesson (from Lezione02 on).

Usage: python record_lesson.py 2 "<sintesi>" "<supplementi letti, es. 3/3 o nessuno>"

Page accounting comes from the lesson Markdown source lines:
- `_(slide pp. 3–6)_`, `_(slide p. 7)_`  -> main deck; `_(slide2 pp. …)_` -> second deck (alphabetical);
- `_(matrix pp. …)_` -> PDF containing APOCALYPSE; `_(scores p. …)_` -> PDF containing SCORES.
Every page must fall in at least one range; SOURCE_VISUAL pages are marked `+ PH`.
Replaces the NOT STARTED section of each PDF in SOURCE_COVERAGE.md, marks the non-PDF sources
of the lesson as read, updates the row/totals/resume state in COURSE_PROGRESS.md and the index row.
"""
import json
import re
import sys
from pathlib import Path
from urllib.parse import quote

META = Path(__file__).resolve().parent
COURSE = META.parent
n = int(sys.argv[1])
summary = sys.argv[2] if len(sys.argv) > 2 else ""
supplements = sys.argv[3] if len(sys.argv) > 3 else "nessuno"
lesson = f"Lezione{n:02d}"
md_files = sorted((COURSE / lesson).glob(f"L{n:02d} - *.md"))
assert md_files, f"no lesson markdown in {lesson}"
md = md_files[0]
all_index = json.loads((META / "page_index.json").read_text(encoding="utf-8"))
index = [r for r in all_index if r["lesson"] == lesson]
pdfs = sorted({r["pdf"] for r in index})
SECONDARY = {"matrix": "APOCALYPSE", "scores": "SCORES"}
decks = [p for p in pdfs if not any(k in p.upper() for k in SECONDARY.values())]
tag_to_pdf = {}
if decks:
    tag_to_pdf["slide"] = decks[0]
if len(decks) > 1:
    tag_to_pdf["slide2"] = decks[1]
for tag, key in SECONDARY.items():
    hits = [p for p in pdfs if key in p.upper()]
    if hits:
        tag_to_pdf[tag] = hits[0]

errors = []
accounted = {p: {} for p in pdfs}
visual = {p: set() for p in pdfs}
section = "§0"
text = md.read_text(encoding="utf-8")
for line in text.splitlines():
    h = re.match(r"^#{3,5} \*\*(\d+(?:\.\d+)*)\.? ", line)
    if h:
        section = "§" + h.group(1)
    for m in re.finditer(r"_\((slide2|slide|matrix|scores) pp?\. ([\d–\-, ]+)\)_", line):
        pdf = tag_to_pdf.get(m.group(1))
        if not pdf:
            errors.append(f"tag '{m.group(1)}' has no matching PDF")
            continue
        for part in re.split(r",\s*", m.group(2).strip()):
            a, _, b = part.replace("–", "-").partition("-")
            if a.strip():
                for page in range(int(a), int(b or a) + 1):
                    accounted[pdf].setdefault(page, set()).add(section)
    v = re.match(r'<!-- SOURCE_VISUAL id="[^"]+" source="([^"]+)" page="(\d+)"', line)
    if v:
        if v.group(1) in visual:
            visual[v.group(1)].add(int(v.group(2)))
        else:
            errors.append(f"SOURCE_VISUAL source not in lesson: {v.group(1)}")

link = f"../{lesson}/{quote(md.name)}"
cov_path = META / "SOURCE_COVERAGE.md"
cov = cov_path.read_text(encoding="utf-8")
total_pages = covered = 0
for pdf in pdfs:
    raw = (META / "_text_cache" / lesson / (Path(pdf).stem + ".txt")).read_text(encoding="utf-8")
    parts = re.split(r"===== \[PAGE (\d+)\] =====", raw)
    pages = {int(parts[i]): parts[i + 1] for i in range(1, len(parts), 2)}
    rows = sorted((r for r in index if r["pdf"] == pdf), key=lambda r: r["page"])
    total_pages += len(rows)
    out = [f"## {lesson}/{pdf}", "",
           f"Destinazione consolidata: [Lezione {n:02d}]({link}). Elaborazione TEXT-FIRST: testo nativo estratto, "
           "contenuto integrato o classificato; nessun rendering né OCR. `+ PH` = placeholder SOURCE_VISUAL.", "",
           "| Pagina | Testo estratto | Flag euristico | Contenuto (inizio) | Destinazione | Visual inspection |",
           "|---:|---|---|---|---|---|"]
    for r in rows:
        p = r["page"]
        body = [l.strip() for l in pages.get(p, "").splitlines()
                if l.strip() and not re.fullmatch(r"\d{1,3}", l.strip()) and "Università degli Studi di Milano" not in l]
        first = re.sub(r"\s+", " ", " ".join(body))[:80].replace("|", "/") or "(nessun testo)"
        state = "sì" if r["chars"] >= 60 else ("solo titolo" if body else "nessun testo")
        dest = ", ".join(sorted(accounted[pdf].get(p, set()), key=lambda s: [int(x) for x in re.findall(r"\d+", s)]))
        if dest:
            covered += 1
        else:
            errors.append(f"{pdf} p.{p} not accounted")
            dest = "**MANCANTE**"
        if p in visual[pdf]:
            dest += " + PH"
        out.append(f"| {p} | {state} | {r['flag']} | {first} | {dest} | not required |")
    new_section = "\n".join(out) + "\n\n"
    cov, k = re.subn(rf"## {re.escape(lesson)}/{re.escape(pdf)}\n.*?(?=\n## )", new_section.rstrip("\n") + "\n", cov, count=1, flags=re.S)
    if k != 1:
        errors.append(f"coverage section not found for {pdf}")
n_ph = sum(len(v) for v in visual.values())
cov = re.sub(rf"^(\| `{lesson}/[^`]+` \| )solo inventario \| da integrare nella lezione associata \|$",
             rf"\1letto integralmente | integrato in [Lezione {n:02d}]({link}) |", cov, flags=re.M)

if errors:
    print(f"{lesson}: {covered}/{total_pages} pages accounted, {n_ph} placeholders")
    print("RESULT: FAIL")
    for e in errors[:40]:
        print(" -", e)
    sys.exit(1)
cov_path.write_text(cov, encoding="utf-8", newline="\n")

prog_path = META / "COURSE_PROGRESS.md"
prog = prog_path.read_text(encoding="utf-8")
row = re.compile(rf"^\| {lesson} \| (\d+) / (\d+) \| .*$", re.M)
m = row.search(prog)
assert m, "progress row not found"
prog = prog[:m.start()] + f"| {lesson} | {m.group(1)} / {m.group(2)} | {covered} | {supplements} | completo | {n_ph} placeholder | {covered}/{m.group(2)} | DONE |" + prog[m.end():]
done = re.findall(r"^\| Lezione\d{2} \| \d+ / (\d+) \| .*\| DONE \|$", prog, re.M)
pages_done = sum(int(x) for x in done)
nxt = next((r["lesson"] for r in sorted(all_index, key=lambda r: r["lesson"])
            if int(r["lesson"][7:]) > n), None)
totals = (f"**Totale corso (aggiornamento automatico):** {len(done)}/24 lezioni DONE, "
          f"{pages_done}/2.068 pagine elaborate; prossima unità: {nxt or 'nessuna, corso completo'}.")
if "**Totale corso (aggiornamento automatico):**" in prog:
    prog = re.sub(r"^\*\*Totale corso \(aggiornamento automatico\):\*\*.*$", totals, prog, flags=re.M)
else:
    prog = re.sub(r"^(\*\*Politica attiva.*)$", lambda x: x.group(1) + "\n\n" + totals, prog, count=1, flags=re.M)
prog = prog.replace("\n## Ripresa\n", "\n## Ripresa del ciclo 01 (storico)\n")
if nxt:
    nx_pdfs = sorted({r["pdf"] for r in all_index if r["lesson"] == nxt})
    nx_pages = sum(1 for r in all_index if r["lesson"] == nxt)
    block = (f"## Ripresa corrente\n\n```text\nUltima completata : {lesson} — {md.name}\nProssima lezione  : {nxt}\n"
             f"Fonti             : {'; '.join(nx_pdfs)} ({nx_pages} pagine)\n"
             f"Dump              : python _meta/dump_lesson.py {int(nxt[7:])}  ->  _meta/_text_cache/{nxt}_compact.txt\n"
             f"Registrazione     : python _meta/record_lesson.py {int(nxt[7:])} \"<sintesi>\" \"<supplementi>\"\n```\n")
else:
    block = "## Ripresa corrente\n\n```text\nCorso completo: nessuna lezione rimanente. Restano le immagini dei placeholder.\n```\n"
if "## Ripresa corrente" in prog:
    prog = re.sub(r"## Ripresa corrente\n\n```text\n.*?```\n", block, prog, flags=re.S)
else:
    prog = prog.replace("\n## Ripresa del ciclo 01 (storico)\n", "\n" + block + "\n## Ripresa del ciclo 01 (storico)\n", 1)
if summary:
    if "## Log delle lezioni (ciclo TEXT-FIRST)" not in prog:
        prog = prog.rstrip("\n") + "\n\n## Log delle lezioni (ciclo TEXT-FIRST)\n"
    prog = re.sub(rf"^- \*\*{lesson}\*\* — .*\n?", "", prog, flags=re.M)
    prog = prog.rstrip("\n") + f"\n- **{lesson}** — {summary}\n"
prog_path.write_text(prog, encoding="utf-8", newline="\n")

idx_path = COURSE / "L0 - Indice del corso.md"
idx = idx_path.read_text(encoding="utf-8")
title = md.stem.split(" - ", 1)[1]
idx = re.sub(rf"^\| {n:02d} \| Da ricostruire \|", f"| {n:02d} | [{title}]({lesson}/{quote(md.name)}) |", idx, flags=re.M)
idx_path.write_text(idx, encoding="utf-8", newline="\n")

print(f"{lesson}: {covered}/{total_pages} pages accounted, {n_ph} placeholders")
print("RESULT: PASS")
