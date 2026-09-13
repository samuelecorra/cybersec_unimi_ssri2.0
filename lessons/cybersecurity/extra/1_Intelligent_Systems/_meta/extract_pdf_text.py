#!/usr/bin/env python3
"""Page-aware native text extraction + heuristic page flagging (TEXT-FIRST policy).

Usage:
    python extract_pdf_text.py            # all lessons
    python extract_pdf_text.py --lesson 3 # only Lezione03

Outputs (under _meta/, git-ignored except the report):
    _text_cache/<LezioneNN>/<pdfstem>.txt   page-delimited plain text
    page_index.json                         per-page metrics + flags
    EXTRACTION_REPORT.md                    summary
Flags are hints only: no page is rendered automatically (see PROCESSING_POLICY.md).
"""
import argparse
import json
import re
import sys
from pathlib import Path

try:
    import fitz  # PyMuPDF
except ImportError:
    sys.exit("PyMuPDF required: pip install pymupdf")

META = Path(__file__).resolve().parent
COURSE = META.parent
CACHE = META / "_text_cache"
PAGE_SEP = "\n\n===== [PAGE {n}] =====\n"

MIN_CHARS_CONTENTFUL = 60
MIN_CHARS_TEXT_OK = 180
IMG_AREA_DOMINANT = 0.35
MATH_HINT = re.compile(r"[=∑∫√±≤≥≈∈∉⊂μσλθΦΨαβγδρ∂]")
MATH_BROKEN = re.compile(r"=\s*$|=\s*[\)\]]|\(\s*\)|\b\w+\s*=\s*/|/\s*$", re.M)
TABLE_HINT = re.compile(r"(\S+\s{3,}\S+\s{3,}\S+)")
FIGURE_REF = re.compile(r"\b(figure|fig\.|graph|chart|plot|diagram|scheme|curve|image|table|see|as shown|example)\b", re.I)


def classify(text, img_area, n_images):
    s = text.strip()
    n = len(s)
    if n == 0:
        return "EXTRACTION_FAILED" if (n_images or img_area) else "EMPTY_OR_DECORATIVE"
    if n < MIN_CHARS_CONTENTFUL:
        return "LIKELY_VISUAL" if img_area > 0.10 else "EMPTY_OR_DECORATIVE"
    if MATH_BROKEN.search(s) and MATH_HINT.search(s):
        return "FORMULA_AMBIGUOUS"
    if img_area >= IMG_AREA_DOMINANT and n < MIN_CHARS_TEXT_OK:
        return "LIKELY_VISUAL"
    if TABLE_HINT.search(s) and s.count("\n") > 4:
        return "TABLE_AMBIGUOUS"
    if n < MIN_CHARS_TEXT_OK and FIGURE_REF.search(s):
        return "LIKELY_VISUAL"
    return "TEXT_OK"


def image_area_fraction(page):
    area = abs(page.rect.get_area()) or 1.0
    covered, count = 0.0, 0
    try:
        for block in page.get_text("dict").get("blocks", []):
            if block.get("type") == 1:
                x0, y0, x1, y1 = block["bbox"]
                covered += abs((x1 - x0) * (y1 - y0))
                count += 1
    except Exception:
        return 0.0, 0
    return min(covered / area, 1.0), count


def normalize(text):
    for bad, good in (("ﬁ", "fi"), ("ﬂ", "fl"), ("ﬀ", "ff"), ("ﬃ", "ffi"), ("ﬄ", "ffl")):
        text = text.replace(bad, good)
    text = text.replace("­", "").replace("’", "'").replace("‘", "'").replace("“", '"').replace("”", '"')
    text = text.replace("", "- ").replace("•", "- ")
    text = re.sub(r"[ \t]{2,}", "  ", text)
    return re.sub(r"\n{3,}", "\n\n", text)


def process_pdf(pdf, lesson):
    doc = fitz.open(pdf)
    rows, chunks = [], []
    for i, page in enumerate(doc, start=1):
        text = normalize(page.get_text("text") or "")
        img_area, n_img = image_area_fraction(page)
        rows.append({"lesson": lesson, "pdf": pdf.name, "page": i, "chars": len(text.strip()),
                     "img_area": round(img_area, 3), "n_images": n_img, "flag": classify(text, img_area, n_img)})
        chunks.append(PAGE_SEP.format(n=i) + text)
    doc.close()
    out = CACHE / lesson
    out.mkdir(parents=True, exist_ok=True)
    (out / f"{pdf.stem}.txt").write_text("".join(chunks), encoding="utf-8")
    return rows


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--lesson")
    args = ap.parse_args()
    target = None
    if args.lesson:
        target = args.lesson if args.lesson.startswith("Lezione") else f"Lezione{int(args.lesson):02d}"
    pdfs = sorted(COURSE.glob("Lezione*/**/*.pdf"))
    if target:
        pdfs = [p for p in pdfs if target in p.parts]
    rows = []
    for pdf in pdfs:
        lesson = next(p for p in pdf.parts if p.startswith("Lezione"))
        rows.extend(process_pdf(pdf, lesson))
    index_path = META / "page_index.json"
    merged = []
    if index_path.exists() and target:
        merged = [r for r in json.loads(index_path.read_text(encoding="utf-8")) if r["lesson"] != target]
    merged.extend(rows)
    merged.sort(key=lambda r: (r["lesson"], r["pdf"], r["page"]))
    index_path.write_text(json.dumps(merged, ensure_ascii=False, indent=1), encoding="utf-8")

    order = ["TEXT_OK", "LIKELY_VISUAL", "FORMULA_AMBIGUOUS", "TABLE_AMBIGUOUS", "EMPTY_OR_DECORATIVE", "EXTRACTION_FAILED"]
    lines = ["# Estrazione del testo nativo — Intelligent Systems", "",
             "Generato da `_meta/extract_pdf_text.py` (politica TEXT-FIRST). I flag sono indizi euristici:",
             "nessuna pagina viene renderizzata automaticamente.", "",
             "| Lezione | PDF | Pagine | TEXT_OK | LIKELY_VISUAL | FORMULA_AMB | TABLE_AMB | EMPTY | FAILED |",
             "|---|---:|---:|---:|---:|---:|---:|---:|---:|"]
    totals = dict.fromkeys(order, 0)
    for lesson in sorted({r["lesson"] for r in merged}):
        lr = [r for r in merged if r["lesson"] == lesson]
        c = {k: sum(r["flag"] == k for r in lr) for k in order}
        for k in order:
            totals[k] += c[k]
        lines.append(f"| {lesson} | {len({r['pdf'] for r in lr})} | {len(lr)} | " + " | ".join(str(c[k]) for k in order) + " |")
    lines.append(f"| **Totale** | | **{len(merged)}** | " + " | ".join(f"**{totals[k]}**" for k in order) + " |")
    (META / "EXTRACTION_REPORT.md").write_text("\n".join(lines) + "\n", encoding="utf-8")
    print(f"PDF: {len({(r['lesson'], r['pdf']) for r in rows})}  pagine: {len(merged)}")
    for k in order:
        print(f"  {k:<22}{totals[k]}")


if __name__ == "__main__":
    main()
