# AGENTS.md - Codex Configuration for Multi-Subject University Notes Integration Project

## Project Context

- This repository digitizes and academically enriches University of Milan course notes for Cybersecurity and Computer Science.
- Repository structure: `[Subject] / [Module] / [Didactic_Unit] / [Lesson_Name].md`.
- Treat each lesson integration as a targeted markdown file update driven by the user's subject, target file, and transcript.

## Input Workflow Protocol

- User prompts for integration tasks follow this pattern:
  `Subject: [Name] | Target File: [Path/To/Lesson.md] | Transcript: [Verbatim text]`
- Extract `Subject`, `Target File`, and `Transcript` dynamically from the prompt.
- Do not assume hardcoded courses, subjects, modules, units, or lesson names.
- Use the extracted `Target File` as the only lesson file to enrich unless the user explicitly asks otherwise.

## Execution Constraints

- NO CHAT DUMP: do not write gap analyses, explanations, preambles, or progress summaries in chat for integration tasks.
- ZERO CONVENTIONAL TEXT: omit conversational fillers, greetings, and post-summaries such as `Sure!`, `Done.`, or equivalent acknowledgements.
- Keep chat output tokens as close to zero as possible.
- BATCH EDITS: group lesson content changes and progress-state updates into a single comprehensive edit pass whenever feasible.
- IGNORE ALL FORMATTING LINTERS: do not spend time or tokens fixing non-breaking markdown lint issues such as trailing spaces, missing blank lines around headings, final newline style, or emphasis style differences (`*` vs `_`).

## Markdown Enrichment Rules

- Preserve the target lesson's existing structure and style unless the integration requires a direct addition.
- Use this strict section hierarchy for new or reorganized lesson sections:
  - `### **N. Title**`
  - `#### **N.M. Title**`
  - `##### **N.M.X. Title**`
- Render mathematical formulas and systems using standard LaTeX block format with double dollar signs, using `\begin{cases}` for matching definitions or piecewise systems.
- Do not use ASCII art, pseudo-diagrams, or code blocks to recreate visual material.

## Visual Callouts

- Emphasize key notes strictly with markdown blockquotes using the exact markers below:
  - `> 📌` for critical core concepts or definitions.
  - `> ⚠️` for warnings, edge cases, or frequent exam pitfalls.
  - `> 💡` for intuitive insights or contextual analogies.
  - `> ✅` for concise section recaps.
- Keep callouts concise and directly tied to lesson content.

## Visual Placeholder Policy

- If the transcript explicitly implies the professor is pointing at a slide, diagram, graph, image, table, drawing, or visual example, never attempt to recreate it using ASCII art, symbols, markdown tables, or code blocks.
- Insert this exact empty HTML comment line placeholder where the visual belongs: `<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->`
- Leave the placeholder empty so the user can manually paste screenshots later.

## Automatic Lifecycle & State Management

- As the absolute final step of every future integration task, update the `Project Progress State` section in both root agent files: `AGENTS.md` and `CLAUDE.md`.
- Locate the current subject from the user's prompt.
- Locate the current lesson or task corresponding to the target file.
- Move the current lesson/task to `[COMPLETED]`.
- Mark the immediate next chronological lesson/task in the same subject as `[NEXT TASK]`.
- Perform the lifecycle update in both files in the same edit context as the lesson integration whenever feasible.
- Keep the `Project Progress State` sections in `AGENTS.md` and `CLAUDE.md` synchronized after every integration, regardless of whether the integration is executed by Codex or Claude Code CLI.
- Do not mark unrelated subjects or non-chronological tasks as next.

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
