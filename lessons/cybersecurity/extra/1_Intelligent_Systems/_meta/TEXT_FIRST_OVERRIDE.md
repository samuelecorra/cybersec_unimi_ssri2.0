# CORRECTIVE EXECUTION PROMPT — SWITCH INTELLIGENT SYSTEMS TO TEXT-FIRST MODE

You are already working on the reconstruction of:

**Intelligent Systems for Industry, Supply Chain and Environment**

inside:

`C:\Users\nabis\Developer\cybersec_unimi_ssri2.0`

This message OVERRIDES the previous PDF inspection strategy where necessary.

Do NOT restart the task from scratch.

Do NOT discard work already completed.

Do NOT regenerate files unnecessarily.

Continue from the current repository state, but immediately change the processing strategy as described below.

---

# 1. STOP SYSTEMATIC SCREENSHOTS / PAGE RENDERING NOW

You are currently spending too much session usage visually rendering slide pages.

STOP any workflow equivalent to:

- screenshot every 4 slides;
- screenshot every N slides;
- render batches of slides merely to inspect them visually;
- visually inspect every page by default;
- create page images as routine evidence of having processed the PDF.

This behaviour is no longer allowed.

It is consuming too much Codex session capacity for a course containing approximately 1000+ slides.

From this point onward:

> **native PDF text extraction is the DEFAULT processing path.**

Visual page rendering becomes an **exception only**.

---

# 2. DO NOT THROW AWAY CURRENT PROGRESS

Before continuing:

1. inspect the current repository state;
2. read the `_meta` files already created;
3. inspect existing Markdown output;
4. determine exactly where the previous processing stopped.

Preserve:

- all valid Markdown already written;
- existing inventory;
- existing source coverage;
- existing useful image placeholders;
- existing page analyses;
- already inspected pages.

Do NOT redo pages merely because the processing strategy changed.

Continue from the first genuinely unfinished point.

---

# 3. NEW DEFAULT PIPELINE

For all remaining PDFs use:

```text
PDF
↓
page count
↓
native text extraction
↓
page-aware UTF-8 text
↓
semantic reconstruction
↓
Markdown lesson
↓
image placeholder where useful
```

NOT:

```text
PDF
↓
render pages
↓
capture screenshots
↓
inspect screenshots
↓
write lesson
```

---

# 4. PREFERRED EXTRACTION TOOLS

Use efficient local native extraction.

Prefer, depending on availability:

- `pdftotext -layout`
- `pdftotext`
- PyMuPDF / `fitz`
- `pdfinfo`
- other local native PDF text extraction tools

Avoid OCR unless actually necessary.

The extraction must preserve page boundaries.

Conceptually:

```text
===== PAGE 1 =====
...

===== PAGE 2 =====
...

===== PAGE 3 =====
...
```

Do NOT flatten the entire PDF into an untraceable blob.

---

# 5. TEXT ENCODING

Prefer UTF-8.

Do not restrict yourself to ASCII if doing so destroys technical notation.

Preserve symbols such as:

```text
≤ ≥ ≠ ≈
μ σ λ
Σ
→
α β γ
```

when meaningful.

Normalize only obvious extraction noise such as:

- malformed spaces;
- repeated whitespace;
- broken line wrapping;
- harmless PDF ligature problems.

Never silently alter formulas.

---

# 6. NEW DEFINITION OF “PAGE PROCESSED”

A page counts as processed when:

1. its page-aware native text has been extracted;
2. its semantic purpose has been understood;
3. relevant content has been incorporated into the lesson or consciously classified;
4. any likely useful visual has an image placeholder when appropriate;
5. its page number is recorded in source coverage.

A screenshot is NOT required.

A rendered image is NOT required.

Visual inspection of every page is NOT required.

This is the key change.

---

# 7. VISUAL INSPECTION ONLY ON EXCEPTIONS

Open/render a page only if one of these occurs:

### A. Text extraction fails

Example:

- page contains meaningful content;
- extracted text is empty or nearly empty.

### B. Formula becomes ambiguous

Example:

```text
x = ?? / ??
```

and correctness cannot be established from surrounding text.

### C. Table structure is destroyed

Rows/columns become impossible to reconstruct reliably.

### D. Meaning is primarily graphical

For example:

- architecture diagrams;
- process diagrams;
- charts;
- plots;
- flowcharts;
- graph structures;
- visual comparisons;
- spatial relationships.

### E. Explicit unresolved textual reference

Examples:

- “as shown in the following figure”;
- “see diagram”;
- “the graph below shows”;
- “as illustrated”.

If the conceptual meaning can still be reconstructed without seeing the image, DO NOT render it merely out of caution.

---

# 8. IMAGE HANDLING — PLACEHOLDERS ONLY

The user will manually add images later.

Therefore you are NOT responsible for systematically extracting images.

When a meaningful slide visual exists, insert a placeholder.

If repository conventions already exist, follow them.

Otherwise use something like:

```md
<!-- IMAGE_PLACEHOLDER
source: exact_file.pdf
page: 42
type: diagram
description: Diagram illustrating the relationship between X, Y and Z.
study_value: useful
-->
```

Do NOT render the slide merely to obtain the image unless the image is necessary for understanding the textual material.

---

# 9. PLACEHOLDERS MAY BE CREATED FROM TEXTUAL EVIDENCE

You may infer that a meaningful image exists from textual context without opening the page.

Example extracted text:

```text
Supply Chain Network

Supplier
Manufacturer
Warehouse
Customer
```

This is sufficient to reasonably create:

```md
<!-- IMAGE_PLACEHOLDER
source: ...
page: ...
type: supply-chain diagram
description: Supply-chain network connecting supplier, manufacturer, warehouse and customer.
-->
```

No screenshot is needed.

Use conservative descriptions.

Do not invent details absent from the text.

---

# 10. KEEP PAGE-LEVEL COVERAGE

The previous requirement of page-level traceability REMAINS.

Do NOT weaken it.

Every page must still eventually be accounted for.

However update the meaning of coverage.

A good `SOURCE_COVERAGE.md` row now looks like:

```md
| Page | Text extraction | Content | Destination | Visual inspection |
|---:|---|---|---|---|
| 18 | OK | Data vs Knowledge | §3.2 | not required |
| 19 | OK | conceptual diagram | §3.2 + placeholder | not required |
| 20 | partial | mathematical relation | §3.3 | inspected |
```

The normal value under `Visual inspection` should be:

`not required`

not:

`inspected`.

---

# 11. UPDATE META DOCUMENTATION TO REFLECT THIS CHANGE

Update the relevant `_meta` documentation so future Codex sessions understand the new policy.

In particular ensure that:

- `COURSE_PROGRESS.md`
- `SOURCE_COVERAGE.md`
- `STYLE_GUIDE_INFERRED.md` or equivalent workflow documentation

make it clear that:

> page coverage does not imply page rendering.

If useful, add a brief permanent section such as:

```md
## PDF Processing Policy

Native page-aware text extraction is the default.

Visual inspection is performed only for ambiguous formulas, broken tables, failed extraction or semantically essential graphics.

Systematic screenshots are prohibited because of session cost.
```

---

# 12. DO NOT REVISIT ALREADY VISUALLY INSPECTED PAGES

If pages have already been screenshot/rendered and correctly processed:

leave them alone.

Do NOT spend resources reprocessing them under the new strategy.

The optimisation applies primarily to remaining work.

---

# 13. BATCH EXTRACT TEXT WHEN USEFUL

For large PDFs, prefer one efficient extraction pass rather than repeated page-by-page GUI operations.

It is acceptable to create temporary page-aware text artifacts such as:

```text
_tmp_text/
    Lesson_01.txt
    Lesson_02.txt
```

or structured JSON/Markdown.

However:

- avoid enormous unnecessary duplicated files;
- keep temporary artifacts separate from final lessons;
- do not commit pointless extraction caches unless they materially help resumability.

---

# 14. OPTIONAL LIGHTWEIGHT AUTOMATION

If it improves efficiency, create a small local helper script that:

1. discovers PDFs;
2. obtains page counts;
3. extracts text page by page;
4. preserves page numbers;
5. flags suspicious pages.

Useful classifications:

```text
TEXT_OK
LOW_TEXT
EMPTY
FORMULA_SUSPECT
TABLE_SUSPECT
LIKELY_VISUAL
```

But do not over-engineer this.

The task is course reconstruction, not building a PDF framework.

---

# 15. SMART VISUAL-INSPECTION HEURISTIC

As a practical guideline:

### `TEXT_OK`

Do not render.

### `LIKELY_VISUAL`

Add placeholder from context if possible.

Do not render automatically.

### `LOW_TEXT`

Check surrounding pages first.

Render only if semantic content appears missing.

### `FORMULA_SUSPECT`

Render the exact page only if formula correctness cannot otherwise be established.

### `TABLE_SUSPECT`

Render only that page if table reconstruction matters.

### `EMPTY`

Determine whether page is:

- title;
- divider;
- decorative;
- image-only educational content.

Only render if uncertain.

---

# 16. CONTEXT EFFICIENCY

Do not dump huge extracted PDFs wholesale into conversational context.

Process locally in manageable chunks.

Prefer something like:

```text
pages 1–15
pages 16–30
...
```

while retaining global page tracking.

Persist progress in repository files frequently.

---

# 17. QUALITY STANDARD DOES NOT CHANGE

This optimisation is NOT permission to produce worse notes.

The resulting Markdown must still be:

- complete;
- technically rigorous;
- pedagogically coherent;
- exam-ready;
- faithful to source material;
- consistent with the rest of the repository.

The change concerns HOW you inspect the source, not the expected quality of the result.

---

# 18. TEXT-FIRST DOES NOT MEAN BLINDLY TRUSTING PDF EXTRACTION

Native extraction is the default but not infallible.

Be alert for:

- missing mathematical symbols;
- reordered columns;
- broken superscripts;
- incorrectly extracted formulas;
- text embedded inside figures;
- slide layouts whose meaning depends on spatial structure.

When there is a genuine semantic ambiguity, targeted visual inspection is appropriate.

Use it precisely.

Do not use it routinely.

---

# 19. EFFICIENCY MONITOR

At the end of each processing batch, briefly assess:

```text
pages processed
pages text-only
pages visually inspected
pages requiring OCR
```

If visual inspection is becoming frequent, investigate why.

For normal text-heavy slide decks, the large majority of pages should be processed without rendering.

Do NOT enforce an artificial percentage, but treat excessive visual inspection as a warning.

---

# 20. CURRENT RUN — IMMEDIATE ACTION

Perform these actions now:

1. stop systematic screenshot generation;
2. preserve all work already completed;
3. identify the exact current progress point;
4. update meta documentation with the text-first policy;
5. switch remaining PDF analysis to page-aware native text extraction;
6. continue the CURRENT lesson from where you stopped;
7. render individual pages only when one of the exception conditions applies;
8. continue updating Markdown and source coverage normally.

Do NOT restart Lesson 01 if substantial valid work already exists.

Do NOT restart the overall inventory.

Resume.

---

# 21. END-OF-RUN REPORT ADDITION

In addition to the existing session report, include:

```text
PROCESSING EFFICIENCY

Pages processed this run:
Text-only pages:
Pages visually inspected:
Pages OCR'd:
Reason for each visual/OCR exception:
```

Keep this concise.

---

# 22. CRITICAL RULE

From this point forward:

> **PROCESS THE INFORMATION, NOT THE PIXELS.**

Systematic slide rendering is prohibited.

The expected workflow is:

> **native page-aware UTF-8 extraction first → targeted visual fallback only when semantic correctness requires it.**

Continue the existing Intelligent Systems reconstruction immediately from the current repository state using this corrected strategy.