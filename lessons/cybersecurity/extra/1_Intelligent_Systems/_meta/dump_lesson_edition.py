#!/usr/bin/env python3
"""Write a compact, page-labelled text dump from a named course edition.

Usage: python dump_lesson_edition.py 2 [2026-27]
"""
import json
import re
import sys
from pathlib import Path

META = Path(__file__).resolve().parent
COURSE = META.parent
n = int(sys.argv[1])
edition = sys.argv[2] if len(sys.argv) > 2 else "2026-27"
lesson = f"Lezione{n:02d}"
cache = META / f"_text_cache_{edition}"
index_path = META / f"page_index_{edition}.json"
if not index_path.exists():
    sys.exit(f"Missing extraction index: {index_path}")

index = [r for r in json.loads(index_path.read_text(encoding="utf-8")) if r["lesson"] == lesson]
if not index:
    sys.exit(f"No extracted pages for {lesson} ({edition})")

secondary = ("APOCALYPSE", "MINDMAP", "SCORES")
pdfs = sorted({r["pdf"] for r in index}, key=lambda name: (any(k in name.upper() for k in secondary), name))
out = [f"# {lesson} — edizione {edition}"]
for pdf in pdfs:
    rows = [r for r in index if r["pdf"] == pdf]
    flags = [f"p{r['page']}:{r['flag']}" for r in rows if r["flag"] != "TEXT_OK"]
    out.append(f"\n## PDF {pdf} ({len(rows)} pagine)\nFlag: {'; '.join(flags) or 'nessuno'}")
    raw = (cache / lesson / f"{Path(pdf).stem}.txt").read_text(encoding="utf-8")
    parts = re.split(r"===== \[PAGE (\d+)\] =====", raw)
    for i in range(1, len(parts), 2):
        page, body = parts[i], parts[i + 1]
        lines = []
        for line in body.splitlines():
            line = line.strip()
            if not line or re.fullmatch(r"\d{1,3}", line) or "Università degli Studi di Milano" in line:
                continue
            line = re.sub(r"^[-•–]\s+", "- ", line)
            if lines and not line.startswith("- ") and not lines[-1].endswith((".", ":", "?", "!", ";")) and len(lines[-1]) > 60:
                lines[-1] += " " + line
            else:
                lines.append(line)
        out.append(f"[p.{page}] " + " | ".join(lines) if lines else f"[p.{page}] (nessun testo)")

target = cache / f"{lesson}_compact.txt"
target.write_text("\n".join(out) + "\n", encoding="utf-8")
print(target.name, target.stat().st_size, "bytes", len(index), "pagine")
