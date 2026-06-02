# CLAUDE.md - Multi-Subject University Notes Integration Project

## Project Context

Digitization and academic enrichment of University of Milan courses (Cybersecurity/Computer Science).
Repository Structure: `[Subject] / [Module] / [Didactic_Unit] / [Lesson_Name].md`

## Input Workflow Protocol

The user prompt will always follow this standard pattern:
`"Subject: [Name] | Target File: [Path/To/Lesson.md] | Transcript: [Verbatim text]"`
YOU MUST extract the subject and file dynamically from the prompt. Do not assume hardcoded courses.

## Token-Saving & Output Constraints (CRITICAL)

- NO CHAT DUMP: NEVER write gap analysis, explanations, or preambles in the chat interface. Go straight to file modifications.
- ZERO CONVENTIONAL TEXT: Omit conversational fillers, greetings, or post-summaries ("Sure!", "Done."). Chat output tokens must approach ZERO.
- BATCH EDITS: Group all changes into a single, comprehensive file edit execution to minimize tool call overhead.
- IGNORE ALL FORMATTING LINTERS: Completely ignore trailing spaces, missing blank lines around headings (MD0xx), trailing newlines, or emphasis styles (`*` vs `_`). Do not waste tokens refactoring non-breaking markdown lint errors.

## Markdown Enrichment & Content Rules

- Section Hierarchy: Strictly use `### **N. Title**`, `#### **N.M. Title**`, and `##### **N.M.X. Title**`.
- Mathematical Notation: Render formulas according to the canonical Markdown/MathJax style: use `$...$` for inline math, for example `$L$`, and `$$...$$` for display math. Do not use `\(...\)` for inline formulas. Render systems with `\begin{cases}` where matching definitions.
- Italian Orthography: Write proper accented characters (`è`, `é`, `ò`, `à`, `ù`, `ì`) instead of apostrophe transliterations such as `e'`, `perche'`, `puo'`, `piu'`, `unita'`.
- Visual Callouts: Emphasize key notes strictly using markdown blockquotes with exact emojis:
  - `> 📌` for critical core concepts or definitions.
  - `> ⚠️` for warnings, edge cases, or frequent exam pitfalls.
  - `> 💡` for intuitive insights or contextual analogies.
  - `> ✅` for concise section recaps.
- Video Diagrams/Images Handling: If the transcript explicitly implies the professor is pointing at a slide, diagram, or graph, NEVER attempt to recreate it using ASCII art, symbols, or code blocks. Instead, inject an empty placeholder line: `<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->` so the user can manually paste screenshots later.

## Automatic Lifecycle & State Management

- ATOMIC AUTO-UPDATE: As the absolute final step of every integration task, you MUST update the `Project Progress State` section in both root agent files: `AGENTS.md` and `CLAUDE.md`.
- Locate the current subject from the user's prompt, move the current lesson to `[COMPLETED]`, and mark the immediate next chronological lesson as `[NEXT TASK]`.
- Perform the lifecycle update in both files in the same tool context as the lesson integration whenever feasible.
- Keep the `Project Progress State` sections in `AGENTS.md` and `CLAUDE.md` synchronized after every integration, regardless of whether the integration is executed by Claude Code CLI or Codex.

## Project Progress State

### Sistemi Operativi 1 [COMPLETED]

- Stato contenutistico: [COMPLETED]
- Tutti i file `.md` dell'insegnamento sono allineati con il programma del professore.
- Rimane solo l'integrazione manuale delle foto/screenshot nei placeholder visivi gia' predisposti.

### Sistemi Operativi 2 [COMPLETED]

- Stato contenutistico: [COMPLETED]
- Tutti i file `.md` dell'insegnamento sono allineati con il programma del professore.
- Rimane solo l'integrazione manuale delle foto/screenshot nei placeholder visivi gia' predisposti.

### Stato Esami Sistemi Operativi [COMPLETED]

- Sistemi Operativi 1 + Sistemi Operativi 2: 6 CFU + 6 CFU = 12 crediti universitari.
- Entrambi gli insegnamenti sono preparati a livello contenutistico per i rispettivi esami scritti.
- Non aggiungere nuove integrazioni contenutistiche a Sistemi Operativi 1 o 2 salvo richiesta esplicita dell'utente.

### Focus Operativo Corrente

- Da ora il lavoro principale passa a Statistica e Crittografia.
- Per i prossimi prompt integrativi, privilegiare l'avanzamento di queste due materie.

### Statistica [NEXT FOCUS]

- M1 UD1: [PENDING]

### Crittografia [NEXT FOCUS]

- M1 UD1: [PENDING]
