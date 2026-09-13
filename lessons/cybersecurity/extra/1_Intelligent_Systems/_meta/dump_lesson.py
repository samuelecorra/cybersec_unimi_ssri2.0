#!/usr/bin/env python3
"""Compact page-tagged dump of one lesson (PDFs + supplements) for fast reading.

Usage: python dump_lesson.py 2
Writes _meta/_text_cache/LezioneNN_compact.txt: one line per page `[p.N] ...`,
footers and blank lines removed; notebooks (.ipynb) reduced to their cells;
.py/.m/.dat supplements appended verbatim (first 400 lines).
"""
import json
import re
import sys
from pathlib import Path

META = Path(__file__).resolve().parent
COURSE = META.parent
n = int(sys.argv[1])
lesson = f"Lezione{n:02d}"
SECONDARY = ("SCORES", "APOCALYPSE")
FOOTER = re.compile(r"^(Fabio Scotti\s*-?\s*Universit.*Milano\s*|\d{1,3})$")

index = [r for r in json.loads((META / "page_index.json").read_text(encoding="utf-8")) if r["lesson"] == lesson]
pdfs = sorted({r["pdf"] for r in index}, key=lambda p: (any(k in p.upper() for k in SECONDARY), p))
out = [f"# {lesson} compact dump"]
for pdf in pdfs:
    rows = [r for r in index if r["pdf"] == pdf]
    flags = [f"p{r['page']}:{r['flag'][:9]}" for r in rows if r["flag"] != "TEXT_OK"]
    out.append(f"\n## PDF {pdf} ({len(rows)} pages)\nflags: {' '.join(flags) or 'none'}")
    raw = (META / "_text_cache" / lesson / (Path(pdf).stem + ".txt")).read_text(encoding="utf-8")
    parts = re.split(r"===== \[PAGE (\d+)\] =====", raw)
    for i in range(1, len(parts), 2):
        page, body = parts[i], parts[i + 1]
        lines = []
        for line in body.splitlines():
            line = line.strip()
            if not line or FOOTER.match(line) or line in {"-", "•", "–"}:
                continue
            line = re.sub(r"^[-•–]\s+", "- ", line)
            if lines and not line.startswith("- ") and not lines[-1].endswith((".", ":", "?", "!", ";")) and len(lines[-1]) > 60:
                lines[-1] += " " + line
            else:
                lines.append(line)
        out.append(f"[p.{page}] " + " | ".join(lines) if lines else f"[p.{page}] (no text)")

for sup in sorted((COURSE / lesson).rglob("*")):
    if not sup.is_file() or sup.suffix.lower() not in {".ipynb", ".py", ".m", ".dat"}:
        continue
    rel = sup.relative_to(COURSE / lesson).as_posix()
    if sup.suffix.lower() == ".ipynb":
        nb = json.loads(sup.read_text(encoding="utf-8"))
        out.append(f"\n## NOTEBOOK {rel} ({len(nb.get('cells', []))} cells)")
        for k, cell in enumerate(nb.get("cells", []), 1):
            src = "".join(cell.get("source", []))
            outs = []
            for o in cell.get("outputs", []) or []:
                txt = "".join(o.get("text", []) or o.get("data", {}).get("text/plain", []))
                if txt:
                    outs.append(txt[:600])
            out.append(f"--- cell {k} [{cell.get('cell_type')}]\n{src}" + (f"\n>>> output:\n{''.join(outs)}" if outs else ""))
    else:
        text = sup.read_text(encoding="utf-8", errors="replace").splitlines()
        out.append(f"\n## FILE {rel} ({len(text)} lines)\n" + "\n".join(text[:400]))

target = META / "_text_cache" / f"{lesson}_compact.txt"
target.write_text("\n".join(out) + "\n", encoding="utf-8")
print(target.name, target.stat().st_size, "bytes")
