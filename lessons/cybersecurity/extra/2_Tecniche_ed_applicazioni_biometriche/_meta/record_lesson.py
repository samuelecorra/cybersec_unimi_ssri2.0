#!/usr/bin/env python3
"""Quality gate + coverage + progress for one finished lesson.

Usage: python record_lesson.py 2 "<summary for the status board>"

Page accounting is derived from the lesson Markdown:
- source lines `_(slide pp. 3–6)_`, `_(slide p. 7)_` refer to the main deck (first non-reading PDF);
- `_(dispensa pp. …)_`, `_(paper pp. …)_`, `_(report pp. …)_`, `_(brochure pp. …)_`, `_(griglia p. …)_`
  refer to the reading whose file name contains that tag (DISPENSA/PAPER/REPORT/BROCURE/GRIGLIA);
  when a lesson has two decks, `_(slide2 pp. …)_` refers to the second one (alphabetical);
- every page must fall in at least one range; SOURCE_VISUAL pages are marked `+ PH`.
Writes the lesson section into SOURCE_COVERAGE.md and the status row into COURSE_PROGRESS.md.
"""
import json
import re
import sys
from pathlib import Path

META = Path(__file__).resolve().parent
COURSE = META.parent
n = int(sys.argv[1])
summary = sys.argv[2] if len(sys.argv) > 2 else ""
lesson = f"Lezione{n:02d}"
md_files = sorted((COURSE / lesson).glob(f"L{n:02d}*.md"))
assert md_files, f"no lesson markdown in {lesson}"

index = [r for r in json.loads((META / "page_index.json").read_text(encoding="utf-8")) if r["lesson"] == lesson]
pdfs = sorted({r["pdf"] for r in index})
READ_TAGS = {"dispensa": "DISPENSA", "paper": "PAPER", "report": "REPORT", "brochure": "BROCURE", "griglia": "GRIGLIA"}


def is_reading(p):
    return any(v in p.upper() for v in READ_TAGS.values())


decks = [p for p in pdfs if not is_reading(p)]
tag_to_pdf = {}
if decks:
    tag_to_pdf["slide"] = decks[0]
    if len(decks) > 1:
        tag_to_pdf["slide2"] = decks[1]
for tag, key in READ_TAGS.items():
    hits = [p for p in pdfs if key in p.upper()]
    if hits:
        tag_to_pdf[tag] = hits[0]
# A PAPER file whose name also contains DISPENSA answers to both tags.

errors = []
accounted = {p: {} for p in pdfs}   # pdf -> page -> set(sections)
visual = {p: set() for p in pdfs}
stats = {"formulas_hint": 0, "placeholders": 0, "links": 0, "headings": 0}

for md in md_files:
    text = md.read_text(encoding="utf-8")
    section = "§0"
    for line in text.splitlines():
        h = re.match(r"^#{3,5} \*\*(\d+(?:\.\d+)*)\.? ", line)
        if h:
            section = "§" + h.group(1)
        for m in re.finditer(r"_\((slide2|slide|dispensa|paper|report|brochure|griglia) pp?\. ([\d–\-, ]+)\)_", line):
            tag, spec = m.group(1), m.group(2)
            pdf = tag_to_pdf.get(tag)
            if not pdf:
                errors.append(f"{md.name}: tag '{tag}' has no matching PDF")
                continue
            for part in re.split(r",\s*", spec.strip()):
                a, _, b = part.replace("–", "-").partition("-")
                if not a.strip():
                    continue
                for page in range(int(a), int(b or a) + 1):
                    accounted[pdf].setdefault(page, set()).add(f"{md.stem[:3]}{section}" if len(md_files) > 1 else section)
        v = re.match(r'<!-- SOURCE_VISUAL id="[^"]+" source="([^"]+)" page="(\d+)"', line)
        if v:
            if v.group(1) not in visual:
                errors.append(f"SOURCE_VISUAL source not in lesson: {v.group(1)}")
            else:
                visual[v.group(1)].add(int(v.group(2)))

# ---- coverage section ---------------------------------------------------------------
cache = {}
for pdf in pdfs:
    raw = (META / "_text_cache" / lesson / (Path(pdf).stem + ".txt")).read_text(encoding="utf-8")
    parts = re.split(r"===== \[PAGE (\d+)\] =====", raw)
    cache[pdf] = {int(parts[i]): parts[i + 1] for i in range(1, len(parts), 2)}

FOOTER = re.compile(r"Fabio Scotti\s*-?\s*Università degli Studi di Milano")
out = [f"## {lesson} — DONE", "", "**Lesson file(s):** " + ", ".join(f"`{lesson}/{m.name}`" for m in md_files), ""]
total_pages = 0
covered_pages = 0
for pdf in pdfs:
    rows = sorted((r for r in index if r["pdf"] == pdf), key=lambda r: r["page"])
    total_pages += len(rows)
    out += [f"### `{pdf}` ({len(rows)} pages)", "",
            "| Page | Text extracted | Flag | Content | Destination | Visual check |",
            "|---:|---|---|---|---|---|"]
    for r in rows:
        p = r["page"]
        body = [l.strip() for l in cache[pdf].get(p, "").splitlines() if l.strip() and not FOOTER.search(l) and not re.fullmatch(r"\d{1,3}", l.strip())]
        first = re.sub(r"\s+", " ", " ".join(body))[:80].replace("|", "/") or "(no text)"
        text_state = "yes" if r["chars"] >= 60 else ("title only" if body else "footer only")
        dest = ", ".join(sorted(accounted[pdf].get(p, set()), key=lambda s: [int(x) if x.isdigit() else 0 for x in re.findall(r"\d+", s)]))
        if not dest:
            errors.append(f"{pdf} p.{p} not accounted in any section range")
            dest = "**MISSING**"
        else:
            covered_pages += 1
        if p in visual[pdf]:
            dest += " + PH"
        out.append(f"| {p} | {text_state} | {r['flag']} | {first} | {dest} | not required |")
    out.append("")
n_ph = sum(len(v) for v in visual.values())
out.append(f"**Totals:** {covered_pages}/{total_pages} pages accounted for · {n_ph} placeholders · 0 pages rendered · 0 OCR.")
out.append("")
section_text = "\n".join(out) + "\n"

cov_path = META / "SOURCE_COVERAGE.md"
cov = cov_path.read_text(encoding="utf-8")
cov = re.sub(rf"## {lesson} — DONE\n.*?(?=\n---\n|\n## )", "", cov, flags=re.S)
marker = "## Lezione02 – Lezione21 — NOT STARTED"
if marker in cov:
    cov = cov.replace(marker, "## Pending lessons — heuristic flag statistics")
anchor = "## Pending lessons — heuristic flag statistics"
if anchor in cov:
    cov = cov.replace(anchor, section_text + "---\n\n" + anchor, 1)
else:
    cov = cov.rstrip("\n") + "\n\n---\n\n" + section_text
cov_path.write_text(cov, encoding="utf-8", newline="\n")

# ---- progress row ------------------------------------------------------------------
prog_path = META / "COURSE_PROGRESS.md"
prog = prog_path.read_text(encoding="utf-8")
row_re = re.compile(rf"^\| {n:02d} \| (\d+) \| (\d+) \| \d+ \| ([^|]*) \| [^|]* \| (\d+) \| [^|]* \| \w+ \|$", re.M)
m = row_re.search(prog)
if m and not errors:
    prog = prog[:m.start()] + f"| {n:02d} | {m.group(1)} | {m.group(2)} | {covered_pages} | {m.group(3).strip()} | complete | 0 | {covered_pages}/{total_pages} | DONE |" + prog[m.end():]
    nxt = re.compile(rf"^(\| {n + 1:02d} \|.*\| )PENDING \|$", re.M)
    prog = nxt.sub(r"\1NEXT |", prog)
    done_rows = re.findall(r"^\| \d{2} \| \d+ \| (\d+) \| (\d+) \|.*\| DONE \|$", prog, re.M)
    pages_done = sum(int(b) for _, b in done_rows)
    prog = re.sub(r"^\| \*\*Total\*\* \| \*\*30\*\* \| \*\*2091\*\* \| \*\*\d+\*\* \| \| \*\*\d+/21\*\* \| \*\*0\*\* \| \*\*\d+/2091\*\* \| \|$",
                  f"| **Total** | **30** | **2091** | **{pages_done}** | | **{len(done_rows)}/21** | **0** | **{pages_done}/2091** | |", prog, flags=re.M)
    if summary:
        log = f"- **{lesson}** — {summary}"
        if "## 7. Lesson log" not in prog:
            prog = prog.rstrip("\n") + "\n\n## 7. Lesson log\n"
        prog = re.sub(rf"^- \*\*{lesson}\*\* — .*\n?", "", prog, flags=re.M)
        prog = prog.rstrip("\n") + "\n" + log + "\n"
    resume = re.search(r"## 2\. Resume state\n\n```text\n.*?```", prog, re.S)
    if resume and n < 21:
        nx = f"Lezione{n + 1:02d}"
        nx_pdfs = sorted({r["pdf"] for r in json.loads((META / "page_index.json").read_text(encoding="utf-8")) if r["lesson"] == nx})
        nx_pages = sum(1 for r in json.loads((META / "page_index.json").read_text(encoding="utf-8")) if r["lesson"] == nx)
        block = (f"## 2. Resume state\n\n```text\nLast completed : Lesson {n:02d} — {lesson}/{md_files[0].name}\n"
                 f"Next lesson    : Lesson {n + 1:02d}\nSources        : {'; '.join(nx_pdfs)} ({nx_pages} pages)\n"
                 f"Dump           : python _meta/dump_lesson.py {n + 1}  ->  _meta/_text_cache/{nx}_compact.txt\n"
                 f"Pages done     : 0 / {nx_pages}\nNext page      : 1\n```")
        prog = prog[:resume.start()] + block + prog[resume.end():]
    prog_path.write_text(prog, encoding="utf-8", newline="\n")

print(f"{lesson}: {covered_pages}/{total_pages} pages accounted, {n_ph} placeholders")
print("RESULT:", "PASS" if not errors else "FAIL")
for e in errors[:40]:
    print(" -", e)
sys.exit(1 if errors else 0)
