#!/usr/bin/env python3
"""Page-aware native text extraction + heuristic page flagging for the
'Tecniche e Applicazioni Biometriche' course.

Text-first strategy: this script is the PRIMARY evidence source. Visual
rendering of a page is an exception, reserved for pages this script flags.

Usage:
    python extract_pdf_text.py            # extract everything, write cache + report
    python extract_pdf_text.py --lesson 3 # only Lezione03

Outputs (under _meta/):
    _text_cache/<LezioneNN>/<pdfstem>.txt   page-delimited plain text
    page_index.json                         per-page metrics + flags
    EXTRACTION_REPORT.md                    human-readable summary
"""

import argparse
import json
import re
import sys
from pathlib import Path

try:
    import fitz  # PyMuPDF
except ImportError:
    sys.exit("PyMuPDF required:  pip install pymupdf")

META = Path(__file__).resolve().parent
COURSE = META.parent
CACHE = META / "_text_cache"

PAGE_SEP = "\n\n===== [PAGE {n}] =====\n"

# Heuristic thresholds. Deliberately conservative: a page is only escalated to
# a visual flag when the text alone is unlikely to carry the meaning.
MIN_CHARS_CONTENTFUL = 60      # below this a page is empty/decorative/visual
MIN_CHARS_TEXT_OK = 180        # above this the text is usually self-sufficient
IMG_AREA_DOMINANT = 0.35       # fraction of page covered by raster images

MATH_HINT = re.compile(r"[=∑∫√±≤≥≈∈∉⊂μσλθΦΨαβγδρ]|\b(FAR|FRR|FMR|FNMR|EER|P\()")
# An equation that lost its right-hand side or has dangling operators.
MATH_BROKEN = re.compile(r"=\s*$|=\s*[\)\]]|\(\s*\)|\b\w+\s*=\s*/|/\s*$", re.M)
TABLE_HINT = re.compile(r"(\S+\s{3,}\S+\s{3,}\S+)")
FIGURE_REF = re.compile(
    r"\b(figura|figure|fig\.|grafico|diagramma|schema|curva|curve|immagine|"
    r"tabella|table|vedi|come mostrato|esempio in|ROC|DET|CMC)\b",
    re.I,
)


def classify(text: str, img_area: float, n_images: int) -> str:
    """Return one of the page flags defined in the task brief."""
    stripped = text.strip()
    n = len(stripped)

    if n == 0:
        return "EXTRACTION_FAILED" if (n_images or img_area) else "EMPTY_OR_DECORATIVE"

    if n < MIN_CHARS_CONTENTFUL:
        # Very little text. Images present -> the meaning lives in the picture.
        return "LIKELY_VISUAL" if img_area > 0.10 else "EMPTY_OR_DECORATIVE"

    if MATH_BROKEN.search(stripped) and MATH_HINT.search(stripped):
        return "FORMULA_AMBIGUOUS"

    if img_area >= IMG_AREA_DOMINANT and n < MIN_CHARS_TEXT_OK:
        return "LIKELY_VISUAL"

    if TABLE_HINT.search(stripped) and stripped.count("\n") > 4:
        return "TABLE_AMBIGUOUS"

    if n < MIN_CHARS_TEXT_OK and FIGURE_REF.search(stripped):
        return "LIKELY_VISUAL"

    return "TEXT_OK"


def image_area_fraction(page) -> tuple[float, int]:
    """Fraction of the page rectangle covered by raster image blocks."""
    page_area = abs(page.rect.get_area()) or 1.0
    covered = 0.0
    count = 0
    try:
        for block in page.get_text("dict").get("blocks", []):
            if block.get("type") == 1:  # 1 == image block
                x0, y0, x1, y1 = block["bbox"]
                covered += abs((x1 - x0) * (y1 - y0))
                count += 1
    except Exception:
        return 0.0, 0
    return min(covered / page_area, 1.0), count


def normalize(text: str) -> str:
    """Repair extraction artefacts WITHOUT touching mathematical meaning."""
    # Ligatures that PDF fonts split apart.
    for bad, good in (("ﬁ", "fi"), ("ﬂ", "fl"), ("ﬀ", "ff"), ("ﬃ", "ffi"), ("ﬄ", "ffl")):
        text = text.replace(bad, good)
    # Typographic dashes/quotes -> keep readable but do not touch minus signs
    # inside formulas (U+2212 is preserved deliberately).
    text = text.replace("­", "")          # soft hyphen
    text = text.replace("’", "'").replace("‘", "'")
    text = text.replace("“", '"').replace("”", '"')
    text = text.replace("", "- ").replace("•", "- ")  # bullets
    text = re.sub(r"[ \t]{2,}", "  ", text)     # keep 2 spaces: table hint needs them
    text = re.sub(r"\n{3,}", "\n\n", text)
    return text


def process_pdf(pdf: Path, lesson: str) -> list[dict]:
    doc = fitz.open(pdf)
    rows = []
    chunks = []
    for i, page in enumerate(doc, start=1):
        raw = page.get_text("text") or ""
        text = normalize(raw)
        img_area, n_img = image_area_fraction(page)
        flag = classify(text, img_area, n_img)
        rows.append(
            {
                "lesson": lesson,
                "pdf": pdf.name,
                "page": i,
                "chars": len(text.strip()),
                "img_area": round(img_area, 3),
                "n_images": n_img,
                "flag": flag,
            }
        )
        chunks.append(PAGE_SEP.format(n=i) + text)
    doc.close()

    out_dir = CACHE / lesson
    out_dir.mkdir(parents=True, exist_ok=True)
    (out_dir / f"{pdf.stem}.txt").write_text("".join(chunks), encoding="utf-8")
    return rows


def main() -> None:
    ap = argparse.ArgumentParser()
    ap.add_argument("--lesson", help="restrict to one lesson number, e.g. 3 or Lezione03")
    args = ap.parse_args()

    target = None
    if args.lesson:
        target = args.lesson if args.lesson.startswith("Lezione") else f"Lezione{int(args.lesson):02d}"

    pdfs = sorted(COURSE.glob("Lezione*/**/*.pdf"))
    if target:
        pdfs = [p for p in pdfs if target in p.parts]
    if not pdfs:
        sys.exit(f"No PDFs found (target={target})")

    all_rows: list[dict] = []
    for pdf in pdfs:
        lesson = next(p for p in pdf.parts if p.startswith("Lezione"))
        try:
            all_rows.extend(process_pdf(pdf, lesson))
        except Exception as exc:  # keep going; report the failure
            all_rows.append(
                {"lesson": lesson, "pdf": pdf.name, "page": 0, "chars": 0,
                 "img_area": 0, "n_images": 0, "flag": f"OPEN_FAILED: {exc}"}
            )

    # Merge into the existing index so per-lesson reruns do not lose other lessons.
    index_path = META / "page_index.json"
    merged: list[dict] = []
    if index_path.exists() and target:
        prev = json.loads(index_path.read_text(encoding="utf-8"))
        merged = [r for r in prev if r.get("lesson") != target]
    merged.extend(all_rows)
    merged.sort(key=lambda r: (r["lesson"], r["pdf"], r["page"]))
    index_path.write_text(json.dumps(merged, ensure_ascii=False, indent=1), encoding="utf-8")

    # ---- human-readable report -------------------------------------------------
    by_lesson: dict[str, list[dict]] = {}
    for r in merged:
        by_lesson.setdefault(r["lesson"], []).append(r)

    lines = [
        "# Extraction report — Tecniche e Applicazioni Biometriche",
        "",
        "Generated by `_meta/extract_pdf_text.py`. Text-first pipeline: pages flagged",
        "`TEXT_OK` need no visual inspection. Only `FORMULA_AMBIGUOUS`,",
        "`EXTRACTION_FAILED` and (selectively) `LIKELY_VISUAL` / `TABLE_AMBIGUOUS`",
        "justify opening the page.",
        "",
        "| Lesson | PDFs | Pages | TEXT_OK | LIKELY_VISUAL | FORMULA_AMB | TABLE_AMB | EMPTY | FAILED |",
        "|---|---:|---:|---:|---:|---:|---:|---:|---:|",
    ]
    order = ["TEXT_OK", "LIKELY_VISUAL", "FORMULA_AMBIGUOUS", "TABLE_AMBIGUOUS",
             "EMPTY_OR_DECORATIVE", "EXTRACTION_FAILED"]
    totals = dict.fromkeys(order, 0)
    for lesson in sorted(by_lesson):
        rows = by_lesson[lesson]
        counts = {k: sum(1 for r in rows if r["flag"] == k) for k in order}
        for k in order:
            totals[k] += counts[k]
        n_pdf = len({r["pdf"] for r in rows})
        lines.append(
            f"| {lesson} | {n_pdf} | {len(rows)} | " + " | ".join(str(counts[k]) for k in order) + " |"
        )
    lines.append(
        f"| **TOTAL** | | **{len(merged)}** | " + " | ".join(f"**{totals[k]}**" for k in order) + " |"
    )

    lines += ["", "## Pages needing a targeted look", ""]
    watch = [r for r in merged if r["flag"] in
             ("FORMULA_AMBIGUOUS", "EXTRACTION_FAILED") or r["flag"].startswith("OPEN_FAILED")]
    if watch:
        lines += ["| Lesson | PDF | Page | Flag | chars | img_area |", "|---|---|---:|---|---:|---:|"]
        lines += [f"| {r['lesson']} | {r['pdf']} | {r['page']} | {r['flag']} | {r['chars']} | {r['img_area']} |"
                  for r in watch]
    else:
        lines.append("_None — no page failed extraction or lost a formula._")

    (META / "EXTRACTION_REPORT.md").write_text("\n".join(lines) + "\n", encoding="utf-8")

    print(f"PDFs processed : {len({(r['lesson'], r['pdf']) for r in all_rows})}")
    print(f"Pages indexed  : {len(merged)}")
    for k in order:
        print(f"  {k:<22}{totals[k]}")


if __name__ == "__main__":
    main()
