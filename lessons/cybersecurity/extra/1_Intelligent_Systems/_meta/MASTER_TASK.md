# MASTER TASK — Reconstruct the complete “Intelligent Systems for Industry, Supply Chain and Environment” course

You are working inside my university monorepo:

`C:\Users\nabis\Developer\cybersec_unimi_ssri2.0`

The course to process is located under:

`C:\Users\nabis\Developer\cybersec_unimi_ssri2.0\lessons\cybersecurity\extra\1_Intelligent_Systems`

This is a **large, multi-iteration documentation/reconstruction task**.

The course contains approximately:

- 24 lessons;
- roughly 900 slide pages overall;
- multiple PDFs;
- datasets;
- scripts;
- notebooks and/or other supplementary material;
- possibly exercises, examples, images, plots, diagrams, tables and code associated with individual lessons.

The objective is NOT to produce a superficial summary.

The objective is to reconstruct the entire course as a set of **complete, pedagogically improved, exam-ready Markdown lessons**, consistent with the rest of this university repository, while maintaining strict traceability to the original teaching material.

This task will require multiple Codex sessions / loops.

Design your work so that another Codex session can resume it deterministically from files stored in the repository.

---

# 0. NON-NEGOTIABLE PRINCIPLE

The source material is authoritative.

You MUST NOT decide that you “understand the lesson well enough” and stop reading the remaining pages.

Every source PDF must eventually be inspected:

**PDF by PDF → page by page → until every page is accounted for.**

A lesson is NOT complete merely because its main concepts have been captured.

A lesson can only be marked complete when:

1. every PDF belonging to that lesson has been inspected;
2. every page of every PDF has been accounted for;
3. relevant visual material has been identified;
4. relevant formulas, definitions, algorithms, examples, tables and code have been incorporated;
5. supplementary files associated with the lesson have been inspected;
6. nothing important from the sources remains unrepresented.

Never claim completion unless these conditions are satisfied.

---

# 1. FIRST: UNDERSTAND THE REPOSITORY

Before writing any Intelligent Systems lesson, inspect the repository.

Look for files such as:

- `AGENTS.md`
- `README.md`
- documentation instructions;
- repository-specific Markdown conventions;
- existing course indexes;
- naming conventions;
- asset conventions;
- scripts used to generate/render course material.

Then inspect a representative sample of existing Markdown lessons elsewhere in:

`lessons/`

Do NOT inspect only one document.

Read enough existing lessons to understand the repository's established educational style.

In particular infer:

- heading hierarchy;
- tone;
- average level of detail;
- mathematical notation;
- Markdown conventions;
- LaTeX conventions;
- code block conventions;
- use of bold/italics;
- callout conventions;
- examples;
- exercises;
- definitions;
- terminology conventions;
- links between lessons;
- image references;
- file naming;
- directory organization;
- how English technical terminology and Italian explanations are handled.

The generated Intelligent Systems lessons must feel like they belong to the SAME repository.

Do not arbitrarily introduce a new documentation style if one already exists.

---

# 2. OUTPUT LANGUAGE AND PEDAGOGICAL STYLE

Unless the repository clearly establishes another convention:

**write the educational content in Italian.**

However, preserve the standard international terminology of Computer Science, Artificial Intelligence, Machine Learning, Data Science, Operations Research and related disciplines.

Prefer formulations such as:

**Machine Learning / apprendimento automatico**

or:

**Knowledge Representation / rappresentazione della conoscenza**

when introducing important terminology.

After the term has been introduced, use whichever form makes the subsequent explanation clearest.

Do not translate established technical terminology into unusual or non-standard Italian terminology.

The lessons should be understandable by a university student studying the topic from scratch, but they must remain technically rigorous.

The target is:

> source-faithful + pedagogically superior + exam-ready

Do not merely rewrite slide bullet points as paragraphs.

Slides often contain compressed statements.

Your job is to reconstruct the logical explanation that connects them.

The reader should understand:

- what the concept is;
- why it exists;
- what problem it solves;
- how it works;
- how it relates to previous concepts;
- what assumptions it makes;
- what its limitations are;
- what examples demonstrate it;
- what distinctions are likely to matter in an exam.

---

# 3. ABSOLUTELY DO NOT MODIFY SOURCE MATERIAL

Treat original teaching material as immutable.

Do NOT:

- modify PDFs;
- delete PDFs;
- rename original PDFs;
- overwrite datasets;
- alter scripts supplied by the professor;
- alter notebooks supplied by the professor;
- move original course assets unless repository conventions explicitly require it.

Create new Markdown/meta files alongside them or according to existing repository conventions.

Do not perform destructive Git operations.

Do not push anything.

Do not rewrite unrelated files.

Before starting, inspect:

`git status`

At the end of each work cycle inspect it again and clearly distinguish your generated files from pre-existing user changes.

---

# 4. CREATE A PERSISTENT COURSE CONTROL LAYER

Because this task cannot reliably be completed in a single context window, create persistent metadata inside the Intelligent Systems course directory.

If the repository already has an equivalent mechanism, reuse it.

Otherwise create something conceptually equivalent to:

```text
1_Intelligent_Systems/
    _meta/
        COURSE_INVENTORY.md
        COURSE_PROGRESS.md
        STYLE_GUIDE_INFERRED.md
        SOURCE_COVERAGE.md
```

These files are part of the engineering process.

They are essential.

## `STYLE_GUIDE_INFERRED.md`

Record the conventions inferred from existing repository lessons.

Include things such as:

- lesson structure;
- heading levels;
- terminology;
- equation syntax;
- code formatting;
- callout syntax;
- image/asset convention;
- filename convention;
- level of pedagogical detail;
- any repository-specific pattern future sessions must preserve.

This file exists so future Codex sessions do not need to rediscover the entire style from scratch.

---

# 5. BUILD A COMPLETE INVENTORY BEFORE LARGE-SCALE WRITING

Recursively inspect:

`lessons\cybersecurity\extra\1_Intelligent_Systems`

Do not assume every relevant file is a PDF.

Inventory ALL meaningful files.

Examples include:

```text
.pdf
.pptx
.csv
.tsv
.xlsx
.xls
.json
.txt
.md
.py
.R
.ipynb
.m
.sql
.zip
.png
.jpg
.jpeg
.svg
```

and any other course-specific format you encounter.

For every lesson identify:

- lesson number;
- directory;
- PDF files;
- page count of every PDF;
- datasets;
- scripts;
- notebooks;
- images;
- exercises;
- other attachments;
- apparent relationships between those files.

Write this into `COURSE_INVENTORY.md`.

The inventory should make it immediately visible if, for example:

```text
Lezione01
  - Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf — 42 pages
  - dataset_xyz.csv
  - example.py

Lezione02
  - ...
```

Do this for ALL lessons before declaring the inventory complete.

Recursively search subdirectories.

Do not inspect only files at the first directory level.

---

# 6. PDF PROCESSING REQUIREMENT: TEXT EXTRACTION IS NOT ENOUGH

A PDF must NOT be considered inspected merely because `pdftotext`, PyMuPDF, pdfplumber or another parser extracted its text.

Slides contain semantic information that may exist only visually:

- diagrams;
- arrows;
- graphs;
- screenshots;
- plots;
- illustrations;
- architecture diagrams;
- tables;
- highlighted relationships;
- spatial layouts;
- flow charts;
- equations rendered as images;
- annotations.

Therefore use TWO complementary views whenever technically possible:

### A. textual extraction

Extract native PDF text so content can be searched and processed accurately.

### B. visual page inspection

Render or otherwise inspect the actual page representation.

For every page understand both:

> what text exists

and

> what the slide visually communicates.

Use available local tools appropriately.

Examples could include:

- PyMuPDF;
- `pdftotext`;
- `pdfinfo`;
- `pdftoppm`;
- Poppler;
- Python libraries;
- other already available tooling.

Choose tools according to the environment.

Do NOT perform OCR indiscriminately.

If native text extraction works, prefer native text.

Use OCR only when a page/file is image-based or when important textual information cannot otherwise be recovered.

But visual inspection is still required even when native text extraction succeeds.

---

# 7. PAGE-BY-PAGE COVERAGE

Maintain explicit source coverage.

For every PDF processed, account for **every page**.

`SOURCE_COVERAGE.md` should ultimately make it possible to verify something conceptually like:

```text
## Lesson 01

### Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf

| Page | Type | Incorporated | Destination | Notes |
|---:|---|---|---|---|
| 1 | title | yes | Intro | title slide |
| 2 | course overview | yes | §1 | |
| 3 | conceptual diagram | yes | §2 + image placeholder | important |
| 4 | definition | yes | §2.1 | |
| 5 | decorative | intentionally omitted | — | professor/contact/logo only |
...
```

Do NOT needlessly reproduce boilerplate such as:

- university logo;
- repeated footer;
- professor email repeated on every slide;
- decorative background.

But the page must still be accounted for.

Use explicit reasons such as:

- `incorporated`;
- `duplicate of page X`;
- `decorative/non-semantic`;
- `title page`;
- `bibliography`;
- `administrative`;
- `covered in section X`.

This is the mechanism that prevents accidental page skipping.

---

# 8. DEFINITION OF “READ THE PDF”

For this project, “I read the PDF” means:

1. you know how many pages it contains;
2. you processed every page;
3. you inspected extracted text;
4. you inspected relevant visual information;
5. you determined the pedagogical purpose of every page;
6. you recorded its coverage;
7. important content was incorporated into the lesson.

Anything less is not considered complete.

---

# 9. LESSON WRITING STRATEGY

Process the course lesson by lesson.

Prefer finishing a complete lesson before starting several incomplete lessons.

For each lesson:

1. discover every associated source;
2. inspect every PDF page;
3. inspect supplementary material;
4. construct the conceptual structure;
5. write/rewrite the Markdown lesson;
6. add visual placeholders;
7. verify source coverage;
8. run quality checks;
9. mark the lesson complete.

Avoid having ten lessons simultaneously at 30%.

Prefer:

```text
Lesson 01 — DONE
Lesson 02 — DONE
Lesson 03 — IN PROGRESS
Lesson 04 — NOT STARTED
```

rather than fragmented progress everywhere.

---

# 10. DO NOT MERELY SUMMARIZE THE SLIDES

The final Markdown lesson should be a proper university lesson.

When slides contain bullet points such as:

```text
Data
Information
Knowledge
Wisdom
```

do NOT simply transform this into:

> Data, information, knowledge and wisdom are four concepts...

Instead reconstruct the conceptual relationship.

Explain:

- what each level means;
- how one transitions to another;
- what processing/context is added;
- why the distinction matters;
- examples;
- any diagrams supplied by the slides;
- connections with Intelligent Systems.

Likewise, if a slide contains one formula and no explanation, explain the formula.

If a slide contains an architecture diagram, explain the components and data flow.

If a slide compares two techniques, make the comparison explicit.

If several slides collectively build one argument, reconstruct that argument as one coherent section rather than treating every slide as an isolated paragraph.

---

# 11. SOURCE FIDELITY VS PEDAGOGICAL ENRICHMENT

You may improve explanations using your technical knowledge when necessary to make compressed slide material understandable.

However there is an important distinction:

### Source-derived content

Content directly represented by the professor's material.

This is the core.

### Explanatory enrichment

Additional explanation added to make source material understandable.

This is allowed and desirable.

### Unsupported course claims

Claims about what the professor teaches, requires, considers important, or will ask in the exam without evidence.

This is NOT allowed.

Never fabricate:

- professor preferences;
- exam questions;
- definitions supposedly used in the slides;
- datasets;
- experimental results;
- formulas;
- references;
- claims that something is “important for the exam” unless supported by material/context.

When introducing significant information that goes beyond the material, keep it clearly pedagogical rather than pretending it originated from the slides.

The notes should remain compatible with the professor's terminology.

---

# 12. VISUAL MATERIAL AND IMAGE PLACEHOLDERS

The user explicitly wants visual material represented in the final Markdown.

Whenever the original slides contain a **semantically meaningful visual element**, insert a placeholder in the Markdown at the exact conceptual point where that image should accompany the explanation.

Relevant visuals include:

- diagrams;
- system architectures;
- pipelines;
- charts;
- graphs;
- plots;
- meaningful photos;
- screenshots;
- workflows;
- visual examples;
- complex tables;
- conceptual schemas.

Do NOT create placeholders merely for:

- university logos;
- decorative icons;
- backgrounds;
- professor portrait;
- ornamental imagery;
- repeated branding.

If the repository already has an image placeholder convention, follow it.

Otherwise use a machine-readable convention similar to:

```md
<!-- IMAGE_PLACEHOLDER
source: Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf
page: 17
type: diagram
description: Diagram showing the relationship between data, information and knowledge.
study_value: essential
-->
```

Immediately around the placeholder, provide enough explanation that the future reader understands what the image contributes.

Example:

```md
Il diagramma della slide mostra il passaggio progressivo dai dati grezzi alla conoscenza attraverso l'aggiunta di struttura e contesto.

<!-- IMAGE_PLACEHOLDER
source: Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf
page: 17
type: diagram
description: Data → Information → Knowledge conceptual hierarchy.
study_value: essential
-->
```

Do NOT invent a visual that does not exist in the source.

Every image placeholder must correspond to an actual visual element present in an original slide.

Always store:

- source PDF;
- page number;
- visual type;
- concise description.

This must make later image extraction/recreation deterministic.

---

# 13. TABLES

If a slide contains a table:

- reconstruct it as Markdown when doing so preserves the information clearly;
- verify every row and column carefully;
- do not approximate numerical values.

If the table is excessively complex or strongly visual:

- explain the relevant information;
- add an image placeholder;
- record the original page.

---

# 14. FORMULAS AND MATHEMATICS

Preserve formulas accurately.

Never infer missing operators or variables casually.

Inspect the rendered page when text extraction produces malformed mathematical notation.

Use the mathematical conventions already used by the repository.

For every non-trivial formula explain:

- meaning of the formula;
- meaning of every relevant variable;
- assumptions;
- interpretation;
- when it is used;
- if appropriate, a simple example.

Do not make the lesson mathematically noisier than the source requires.

---

# 15. ALGORITHMS AND CODE

If the slides contain pseudocode, algorithms or code:

- preserve the algorithmic meaning exactly;
- reconstruct formatting when extraction destroys indentation;
- use fenced code blocks with appropriate language;
- explain inputs;
- explain outputs;
- explain important steps;
- explain the reason for the algorithm;
- explain relevant complexity only if present or naturally required;
- distinguish professor-provided code from your explanatory examples.

Never silently “fix” professor-provided source code while presenting it as original.

If code contains an apparent bug or inconsistency:

1. preserve what the source says;
2. identify the issue separately;
3. explain the likely correction.

---

# 16. DATASETS, SCRIPTS AND NOTEBOOKS

PDFs are not the only course material.

Inspect datasets, scripts and notebooks associated with each lesson.

For datasets determine, where reasonably possible:

- file format;
- column/schema structure;
- approximate role in the lesson;
- which slides/reference examples use it;
- whether scripts consume it.

For scripts/notebooks determine:

- what task they implement;
- inputs;
- outputs;
- major libraries;
- algorithm/model involved;
- relationship with the lecture;
- whether they demonstrate a concept from the slides.

Do not dump entire datasets into Markdown.

Do not reproduce huge scripts unnecessarily.

Instead integrate them pedagogically.

A lesson can contain sections such as:

```md
## Dataset ed esercitazione associata

Il file `example.csv` contiene ...

Lo script `example.py` utilizza questo dataset per ...
```

Use relative repository links where consistent with existing lessons.

If supplementary files are unrelated to a specific lesson but belong to the course globally, document them appropriately in the course index.

---

# 17. CROSS-FILE RELATIONSHIPS MATTER

Look for relationships such as:

```text
slide → dataset
slide → Python script
slide → notebook
dataset → script
script → output figure
lesson N → concept introduced in lesson N-1
```

Make these relationships explicit in the generated lessons when useful.

Do not treat files as isolated artifacts.

---

# 18. LESSON STRUCTURE

Do NOT mechanically impose one structure if existing repository lessons use another.

Infer repository style first.

Nevertheless, a strong lesson will generally contain concepts equivalent to:

```text
# Lesson title

## Introduction / objective

## Main concepts

### Definitions

### Explanation

### Examples

### Diagrams / visual references

## Methods / models / algorithms

## Practical examples or associated material

## Important distinctions

## Summary / key takeaways
```

Only include sections that make sense for that lesson.

The lesson should read naturally.

Do not produce a rigid template filled with empty sections.

---

# 19. EXAM-READY DOES NOT MEAN “CHEAT SHEET”

Do not overcompress material.

These Markdown files should serve BOTH purposes:

### First study

A student who does not understand the topic should be able to learn from the lesson.

### Revision

A student who already studied it should be able to rapidly recover:

- definitions;
- differences;
- mechanisms;
- assumptions;
- formulas;
- examples;
- relationships.

Highlight distinctions that are easy to confuse.

Examples:

```text
A vs B
supervised vs unsupervised
data vs information vs knowledge
classification vs regression
training vs inference
```

when such distinctions actually occur in the course.

---

# 20. REDUNDANCY MANAGEMENT

Slide decks often repeat content.

Do not replicate the same paragraph five times merely because five slides restate it.

Instead:

- merge genuinely duplicated information;
- retain any new nuance introduced in later slides;
- record every duplicated page in source coverage.

The source coverage might say:

```text
Page 22 — duplicate/rephrasing of pages 19–21; incorporated into §4.2
```

This preserves page-level completeness without damaging the quality of the final lesson.

---

# 21. PRESERVE IMPORTANT EXAMPLES

Examples are often more useful than abstract definitions.

Do not remove examples merely to shorten the lesson.

If the professor explains a concept through:

- an industrial process;
- a supply-chain scenario;
- an environmental system;
- a business case;
- a dataset;
- a numerical example;
- a graph;
- an application of AI;

retain it.

Explain why the example illustrates the underlying concept.

---

# 22. INTERNAL SOURCE TRACEABILITY

The final lesson itself should remain pleasant to read.

Do not pollute every paragraph with page references unless existing repository style does so.

Detailed traceability belongs primarily in `_meta/SOURCE_COVERAGE.md`.

However image placeholders MUST include precise source PDF + page because they will later be used to recover the correct visual.

When useful, you may also keep lightweight source comments invisible in rendered Markdown.

---

# 23. FILE NAMING

First infer the repository's naming convention.

Follow existing naming patterns whenever possible.

If there is no obvious convention, a sensible fallback for:

```text
Lezione01/
Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf
```

would be:

```text
Lezione01/
Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.md
```

Do NOT rename the source PDF.

If one lesson contains several PDFs that logically form a single lecture, determine whether existing repository conventions favor:

- one consolidated lesson;
- multiple Markdown units.

Choose based on actual course structure and document the choice.

---

# 24. COURSE INDEX

Eventually produce or update a global course index according to repository convention.

It should allow navigation across all Intelligent Systems lessons.

Conceptually:

```text
Intelligent Systems for Industry, Supply Chain and Environment

01 — Introduction ...
02 — ...
03 — ...
...
24 — ...
```

Include relevant supplementary material where useful.

Do not create duplicate indexes if an existing one already serves this role.

---

# 25. COURSE_PROGRESS.md

Maintain persistent status.

Use states such as:

```text
NOT STARTED
INVENTORIED
PDF INSPECTION
DRAFTING
VALIDATING
DONE
BLOCKED
```

For each lesson record at minimum:

- lesson identifier;
- source files;
- PDF pages total;
- pages inspected;
- supplementary files inspected;
- Markdown status;
- image placeholders status;
- coverage verification;
- final state.

Example:

```md
| Lesson | PDF pages | Pages inspected | Supplements | Markdown | Visuals | Coverage | Status |
|---|---:|---:|---|---|---|---|---|
| 01 | 42 | 42 | yes | complete | complete | 42/42 | DONE |
| 02 | 37 | 15 | pending | not started | pending | 15/37 | PDF INSPECTION |
```

A lesson may be marked `DONE` only when its page coverage is complete.

---

# 26. INTERRUPTIBILITY / LOOP ENGINEERING

Assume you can be interrupted at any moment.

Therefore never rely only on conversational context for progress.

Persist important state to the repository.

At the end of every meaningful batch:

1. update `COURSE_PROGRESS.md`;
2. update `SOURCE_COVERAGE.md`;
3. update inventory if new files/relationships were discovered;
4. save completed Markdown;
5. leave a clean resumable state.

A future Codex session should be able to receive simply:

> Continue the Intelligent Systems reconstruction following the master task and existing `_meta` state.

and know exactly what to do next.

---

# 27. WHEN CONTEXT/TIME IS LIMITED

Do NOT rush through remaining PDFs to maximize the number of lessons marked complete.

Quality and completeness are more important than artificial throughput.

If only enough capacity remains for one lesson:

finish one lesson correctly.

If a lesson cannot be finished:

stop at a deterministic boundary and record exactly where processing stopped.

Example:

```text
Lesson 04
PDF: xyz.pdf
Pages 1–28 inspected
Next page: 29
Markdown sections §1–§5 complete
Page 24 contains an unresolved diagram requiring reinspection
Status: PDF INSPECTION
```

Never write:

> “I reviewed most of the material.”

Use measurable state.

---

# 28. DO NOT TRUST PREVIOUS SESSION CLAIMS BLINDLY

The repository files are the source of truth for progress.

On a resumed session:

- read `_meta/COURSE_PROGRESS.md`;
- inspect existing generated files;
- inspect source coverage;
- verify the next work unit.

If previous metadata claims `DONE` but obvious coverage is missing, repair the discrepancy.

---

# 29. QUALITY GATE FOR EACH LESSON

Before setting any lesson to `DONE`, verify ALL of the following:

### Source coverage

- every source PDF identified;
- every PDF page accounted for;
- supplementary files inspected;
- no unexplained page gaps.

### Concepts

- all definitions retained;
- all major explanations retained;
- relevant examples retained;
- formulas retained accurately;
- algorithm/code semantics retained;
- comparisons retained.

### Visuals

- every semantically relevant visual identified;
- appropriate placeholder inserted;
- placeholder references a REAL source page;
- no invented images.

### Pedagogy

- slide fragments converted into coherent explanations;
- prerequisites introduced when needed;
- transitions between concepts are understandable;
- important distinctions are explicit;
- lesson can be studied without constantly opening the PDF.

### Markdown

- syntax valid;
- heading hierarchy coherent;
- LaTeX valid according to repository style;
- code blocks properly closed;
- relative links sensible;
- no malformed extracted characters;
- no raw PDF extraction garbage.

### Integrity

- no hallucinated professor claims;
- no accidental modification of originals;
- no unrelated repository changes.

Only then:

`Status = DONE`.

---

# 30. FINAL COURSE QUALITY GATE

The entire course must NOT be declared complete until:

```text
total pages inspected == total pages inventoried
```

and every lesson is `DONE`.

Also verify:

- all 24 lessons are represented;
- all discovered supplementary files are accounted for;
- global navigation works;
- no lesson remains partially processed;
- no unresolved placeholders exist except intentional image placeholders;
- no important source is orphaned;
- no PDF was accidentally omitted because of nested directories.

---

# 31. WHAT NOT TO DO

Do NOT:

- summarize PDFs from filenames;
- inspect only extracted text;
- skip image-heavy slides;
- process only “important-looking” pages;
- assume repeated slides are irrelevant without checking them;
- invent missing information;
- blindly copy slide bullets;
- generate generic AI textbook content unrelated to the professor's material;
- create fake diagrams;
- create fake datasets;
- create fake exam questions;
- overwrite original files;
- mark incomplete lessons as complete;
- claim all 900 pages were inspected unless coverage proves it;
- compress 40 slides into three paragraphs merely for brevity.

---

# 32. PREFERRED WORK GRANULARITY

Use **one complete lesson as the normal atomic work unit**.

If a lesson contains multiple tightly related PDFs, treat the whole lesson as the atomic unit.

Within a lesson, internally process material in manageable page ranges if necessary, but do not lose global page tracking.

After completing a lesson, perform its quality gate before starting the next.

---

# 33. FIRST EXECUTION — START NOW

Begin work immediately.

Do NOT ask me for confirmation unless there is an actual technical blocker that makes progress impossible.

For this first execution perform the following sequence:

### Phase A — Repository reconnaissance

Inspect repository instructions and existing Markdown lessons.

Infer the style.

Create/update:

`_meta/STYLE_GUIDE_INFERRED.md`

### Phase B — Course discovery

Recursively inspect the entire:

`lessons\cybersecurity\extra\1_Intelligent_Systems`

directory.

Create the complete file inventory.

Determine:

- number of lesson directories;
- PDFs;
- page counts;
- supplementary files;
- apparent source relationships.

Create/update:

- `COURSE_INVENTORY.md`
- `COURSE_PROGRESS.md`
- `SOURCE_COVERAGE.md`

### Phase C — Validate expected course scope

Check whether the discovered material actually corresponds to approximately 24 lessons.

Do NOT assume this number blindly.

If the filesystem structure differs, record the real structure.

### Phase D — Process Lesson 01 completely

Start with:

`Lezione01`

including:

`Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf`

and every other file associated with that lesson.

Inspect the PDF from page 1 through its final page.

Inspect visuals.

Inspect supplements.

Write the complete Markdown lesson.

Add accurate image placeholders.

Update source coverage.

Run the Lesson 01 quality gate.

Only if all requirements are satisfied:

`Lesson 01 = DONE`

### Phase E — Continue if capacity remains

If Lesson 01 is completely finished and sufficient capacity remains, begin Lesson 02.

Do NOT sacrifice Lesson 01 quality merely to begin Lesson 02.

Continue sequentially.

---

# 34. END-OF-RUN REPORT

At the end of each Codex run, give me a concise engineering report.

I do NOT need a giant prose summary of the course.

Report:

```text
INTELLIGENT SYSTEMS — SESSION REPORT

Repository:
...

Inventory:
- lessons discovered:
- PDFs:
- total PDF pages:
- supplementary files:

Completed:
- Lesson XX: ...

In progress:
- Lesson XX
- PDF:
- pages inspected / total:
- next page:

Files created/updated:
- ...

Visual placeholders:
- ...

Problems/blockers:
- ...

Next deterministic action:
...
```

The report must agree with repository metadata.

---

# 35. MOST IMPORTANT RULE

Completeness must be **provable from the repository state**.

Do not optimize for appearing productive.

Optimize for building a complete and reliable course reconstruction over multiple engineering loops.

The final objective is that, after all iterations, I can open the Markdown material in this repository and study the entire **Intelligent Systems for Industry, Supply Chain and Environment** course without needing to reconstruct the logic of hundreds of terse PowerPoint slides myself.

Start now with repository reconnaissance, full course inventory, and Lesson 01.