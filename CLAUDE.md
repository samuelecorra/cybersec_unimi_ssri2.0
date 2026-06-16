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

### Matematica Discreta [IN PROGRESS]

- M1_Insiemi_numerici/UD1_Numeri_naturali: [COMPLETED] — creati L1-L6 (operazioni in ℕ, induzione, definizioni ricorsive, numeri primi, divisori/crivello, MCD/mcm)
- M1_Insiemi_numerici/UD2_Relazioni_di_equivalenza: [COMPLETED] — creati L1-L2 (relazioni e proprietà, equivalenza e ordine)
- M1_Insiemi_numerici/UD3_Congruenze_e_criteri_divisibilita: [COMPLETED] — creati L1-L7 (congruenza mod n, ℤₙ, congruenze lineari, TCR, criteri divisibilità, Wilson/Fermat/Eulero, esercizi)
- M2_Gruppi_Anelli_Campi/UD1_Gruppi: [COMPLETED] — creati L1-L6 (strutture algebriche, gruppi e proprietà, ℤₙ/ℤₙ* e gruppi ciclici, gruppo simmetrico Sₙ, omomorfismi, teorema di Lagrange)
- M2_Gruppi_Anelli_Campi/UD2_Campi_e_anelli: [COMPLETED] — creati L1-L3 (campi e anelli, anelli di polinomi A[x], anello delle matrici Mₙₓₙ[K])
- M3 e moduli successivi: [NEXT TASK]

### Statistica [IN PROGRESS]

- M4_Esami_svolti/esami_2025: [COMPLETED] — creati 4 file soluzione completa (23-lug-2025, 5-lug-2025, 12-feb-2025, 15-gen-2025); coprono Binomiale, Poisson, Gaussiana, Ipergeometrica, Esponenziale, Bayes, stat. descrittiva, TLC
- M1-M3 (teoria): [PENDING]

### Crittografia [IN PROGRESS]

- M7_Appelli_Svolti/UD3_Anno_2025: [COMPLETED] — creati L0 (intro) + L1-L6 (6 appelli 2025 con soluzioni complete passo-passo)
- M7_Appelli_Svolti/UD1_Anno_2023: [COMPLETED] — creati L0 + L1-L8 (8 appelli 2023 risolti da zero dai PDF in `Temi d'esame`; L7=08/09 è rimando a L6=07/09, contenuto identico).
- Unità "Esercizi_Appelli" per modulo: [COMPLETED] — per ogni modulo M1-M6 una UD che accorpa, un file per esercizio, tutte le domande degli appelli M7 (2023+2024+2025) di quel solo modulo (80 esercizi: M1=18, M2=20, M3=13, M4=11, M5=5, M6=13). Generate da `scripts/build-crypto-exercise-units.mjs` (+ validate). **Naming**: `NN - [gg-mm-aaaa] Titolo.md` (prefisso numerico zero-padded per ordine cronologico + data italiana; intro `00 - Intro.md`). Modifiche di contenuto vanno fatte nel SORGENTE M7 (le copie si rigenerano e sovrascrivono). NB: alcuni link prerequisiti restano rotti nei sorgenti M7 (difetti preesistenti).
- M1 UD1 (teoria): [NEXT TASK]
