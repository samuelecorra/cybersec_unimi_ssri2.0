# STYLE_GUIDE_INFERRED — Tecniche e Applicazioni Biometriche

Inferred from `CLAUDE.md` / `AGENTS.md`, from the prerequisite course
`anno3/6_Sistemi_Biometrici` and from the SPA renderer (`vite-plugin-lessons.js`,
`src/utils/remarkCallouts.js`). Every future session must follow it.

## 1. Files and naming

- One Markdown lesson per folder: `LezioneNN/LNN - <Titolo in italiano>.md` (e.g. `Lezione01/L01 - Descrizione del corso, esame e introduzione alla biometria.md`).
- If a lesson's readings (`DISPENSA`, `PAPER`) are large enough, add a sibling `LNNb - <Titolo>.md` in the same folder.
- Future screenshots go in `LezioneNN/imgs/` and are referenced as `![](imgs/<file>)` (spaces encoded as `%20`, as in Biometrics I).
- The SPA tree shows only `.md` and `.pdf` outside Programmazione: `_meta/*.md` are visible, `_meta/*.py|json|txt` are not.

## 2. Structure of a lesson

```
# **Lezione N: Titolo**

---

### **0. Panoramica della lezione**
_(slide pp. 1–2)_

## **Parte A — ...**            <- only when the deck is split into parts
### **1. Titolo sezione**
#### **1.1 Titolo sottosezione**
##### **1.1.1 Titolo**
```

- Section hierarchy is mandatory: `### **N. Title**`, `#### **N.M Title**`, `##### **N.M.X Title**` (CLAUDE.md). `## **Parte X — ...**` is allowed as a part divider (used throughout Biometrics I).
- Separate top-level sections with `---`.
- Under each section put an italic source line `_(slide pp. a–b)_` so coverage stays auditable.
- End with a `✅` recap section and a "Distinzioni da non confondere" table when the lesson introduces commonly confused pairs.

## 3. Language and terminology

- Italian prose with proper accents (`è`, `perché`, `più`, `unità`), never `e'`/`perche'`.
- Standard English biometric terms are kept, introduced once as `English (ACRONYM) / traduzione`: e.g. **False Match Rate (FMR)** / tasso di falso match.
- Preserve the lecturer's terminology; when it differs from standard literature (e.g. "Performabilità", "Circonvenzione"), give the correspondence explicitly.

## 4. Mathematics

- Inline `$...$`, display `$$...$$` on their own lines; never `\(...\)`.
- `\begin{cases}` for piecewise definitions.
- After each important formula: variables, event measured, range, effect of the threshold, relation to other metrics.
- Validate with `node scripts/check-latex.mjs "<lesson dir>"` (KaTeX, same engine as the SPA).
- Tooling caveat: the Bash tool halves backslashes in inline commands/heredocs; write files containing LaTeX with the Write/Edit tools.

## 5. Callouts (CLAUDE.md, exact emojis)

| Marker | Use |
|---|---|
| `> 📌` | core concept / definition |
| `> ⚠️` | pitfall, edge case, exam trap, **correction of a slide error** |
| `> 💡` | intuition, analogy, **pedagogical addition not present in the slides** |
| `> ✅` | concise recap |

- Recalls of Biometrics I use a plain blockquote: `> **Richiamo — Sistemi Biometrici, L06A.** ...` with a link.
- The renderer also understands Obsidian `> [!type]` callouts, but the repository instruction (emoji blockquotes) prevails.

## 6. Source fidelity labels

- Content from slides: written directly, with the `_(slide pp. …)_` line.
- Explanations added for clarity: allowed inline; anything substantive that is **not** in the slides (standards, formulas, historical corrections) is flagged with `> 💡 **Nota didattica (non presente nelle slide).**` or, for errors, `> ⚠️ **Precisazione sulle slide.**`.
- Never invent numbers, product claims, exam questions or lecturer preferences.

## 7. Image placeholders

Each meaningful visual gets **two consecutive comment lines**: a single-line `SOURCE_VISUAL`
record with the metadata, followed by the repository marker. Both are stripped by the SPA
(`src/utils/remarkSourceComments.js`, regex `SOURCE_VISUAL\b...-->` and
`INSERT INSTRUCTOR SLIDE/DIAGRAM HERE\s*-->`), and the same pair is used by the
Intelligent Systems course.

```
<!-- SOURCE_VISUAL id="TAB01-V19" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="56" type="diagramma decisionale" description="Matching score confrontato con soglia = 87%; Entrano tutti / Non entra nessuno" reason="Ancora visiva del compromesso della soglia" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
```

- Ids: `TAB<lesson 2 digits>-V<2 digits>`, sequential in page order within the lesson.
- **Do not** put extra metadata lines inside the `INSERT INSTRUCTOR ...` comment: the renderer's
  regex only strips it when `-->` follows the marker, so a multi-line version would be shown as text.
- Attribute values must not contain double quotes or the sequence `-->`; use «» for quotations.
- Place the pair right after the paragraph that explains the visual, with blank lines around it.
- Add one only where the visual carries meaning (pipelines, curves, tables, comparisons, image-only
  slides); purely decorative title slides get none. When the content of an image-only slide is not
  recoverable from text, say so in `description` instead of guessing.
- Placeholders may be inferred from text + context; rendering is not required to add one.

## 8. Links

- Cross-course links use the angle-bracket form, which tolerates spaces and apostrophes:
  `[L06A — Misura dei parametri](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>)`
- From `LezioneNN/` the prerequisite course is at `../../../anno3/6_Sistemi_Biometrici/`.
- Forward references to lessons of this course not yet written are **plain text** ("→ Lezione 15"), never links, to avoid broken links.
