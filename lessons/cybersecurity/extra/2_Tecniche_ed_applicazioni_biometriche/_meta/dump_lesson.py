#!/usr/bin/env python3
"""Write a compact, page-tagged text dump of one lesson for fast reading.

Usage: python dump_lesson.py 2 [out_dir]
Removes the lecturer footer, blank lines and bullet-only lines; joins wrapped lines;
tags every page as [PDF-TAG p.N] and prints the non-TEXT_OK flags at the top.
"""
import json
import re
import sys
from pathlib import Path

META = Path(__file__).resolve().parent
n = int(sys.argv[1])
lesson = f"Lezione{n:02d}"
out_dir = Path(sys.argv[2]) if len(sys.argv) > 2 else META / "_text_cache"
FOOTER = re.compile(r"^(Fabio Scotti\s*-?\s*Università degli Studi di Milano\s*|\d{1,3})$")

index = [r for r in json.loads((META / "page_index.json").read_text(encoding="utf-8")) if r["lesson"] == lesson]
pdfs = sorted({r["pdf"] for r in index}, key=lambda p: (("DISPENSA" in p.upper()) or ("PAPER" in p.upper()) or ("REPORT" in p.upper()) or ("BROCURE" in p.upper()) or ("GRIGLIA" in p.upper()), p))

out = [f"# {lesson} compact dump"]
for pdf in pdfs:
    rows = [r for r in index if r["pdf"] == pdf]
    flags = [f"p{r['page']}:{r['flag'][:9]}" for r in rows if r["flag"] != "TEXT_OK"]
    out.append(f"\n## PDF {pdf} ({len(rows)} pages)\nflags: {' '.join(flags) or 'none'}")
    raw = (META / "_text_cache" / lesson / (Path(pdf).stem + ".txt")).read_text(encoding="utf-8")
    for chunk in re.split(r"\n*===== \[PAGE (\d+)\] =====\n", raw)[1:]:
        pass
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

target = out_dir / f"{lesson}_compact.txt"
target.write_text("\n".join(out) + "\n", encoding="utf-8")
print(target, target.stat().st_size, "bytes")
