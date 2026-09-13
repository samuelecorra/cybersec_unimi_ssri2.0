# COURSE_PROGRESS — Tecniche e Applicazioni Biometriche

Last update: 2026-09-12

## 1. Status board

| Lesson | PDFs | Pages | Pages processed | Supplements | Markdown | Visual exceptions | Coverage | Status |
|---|---:|---:|---:|---|---|---:|---|---|
| 01 | 1 | 94 | 94 | — | complete | 0 | 94/94 | DONE |
| 02 | 1 | 68 | 68 | — | complete | 0 | 68/68 | DONE |
| 03 | 1 | 59 | 59 | — | complete | 0 | 59/59 | DONE |
| 04 | 1 | 53 | 53 | — | complete | 0 | 53/53 | DONE |
| 05 | 1 | 68 | 68 | — | complete | 0 | 68/68 | DONE |
| 06 | 2 | 86 | 86 | — | complete | 0 | 86/86 | DONE |
| 07 | 2 | 116 | 116 | 3 images | complete | 0 | 116/116 | DONE |
| 08 | 1 | 77 | 77 | — | complete | 0 | 77/77 | DONE |
| 09 | 1 | 82 | 82 | — | complete | 0 | 82/82 | DONE |
| 10 | 2 | 118 | 118 | — | complete | 0 | 118/118 | DONE |
| 11 | 1 | 135 | 135 | Colab script, erratum, images, models | complete | 0 | 135/135 | DONE |
| 12 | 1 | 109 | 109 | Colab script, 8 images | complete | 0 | 109/109 | DONE |
| 13 | 2 | 129 | 129 | — | complete | 0 | 129/129 | DONE |
| 14 | 3 | 131 | 131 | — | complete | 0 | 131/131 | DONE |
| 15 | 1 | 70 | 70 | — | complete | 0 | 70/70 | DONE |
| 16 | 1 | 93 | 93 | — | complete | 0 | 93/93 | DONE |
| 17 | 2 | 145 | 145 | — | complete | 0 | 145/145 | DONE |
| 18 | 1 | 116 | 116 | — | complete | 0 | 116/116 | DONE |
| 19 | 1 | 129 | 129 | — | complete | 0 | 129/129 | DONE |
| 20 | 1 | 76 | 76 | — | complete | 0 | 76/76 | DONE |
| 21 | 3 | 137 | 137 | README | complete | 0 | 137/137 | DONE |
| **Total** | **30** | **2091** | **2091** | | **21/21** | **0** | **2091/2091** | |

"Visual exceptions" counts pages actually rendered or OCR'd. Pages whose visual meaning was recovered from the prerequisite course or from PyMuPDF word coordinates are **not** counted (they required no rendering).

## 2. Resume state

```text
Course status  : COMPLETE — 21/21 lessons, 2091/2091 pages accounted (SOURCE_COVERAGE.md)
Last completed : Lesson 21 — Lezione21/L21 - Ambient intelligence, pagamenti biometrici, applicazioni particolari e trend.md
Next lesson    : none
Rendering/OCR  : 0 pages across the whole course (text-first)
KaTeX          : 0 errors in all 21 lesson folders (node scripts/check-latex.mjs)
Remaining work : (1) paste instructor images into the SOURCE_VISUAL placeholders;
                 (2) check on the slides the formulas/values marked "Formula ricostruita"
                     or "da verificare" in the lessons (grep for those strings);
                 (3) exam simulation text is not published (Lezione21/README.md): only the grid is covered.
```

If `_meta/_text_cache/` is missing (it is git-ignored), rebuild it first:
`python _meta/extract_pdf_text.py` (whole course, ~1 min) or `python _meta/extract_pdf_text.py --lesson 2`.

## 3. Per-lesson procedure

1. List the lesson's files in `COURSE_INVENTORY.md`.
2. Read the page-delimited text from `_text_cache/LezioneNN/` in two or three page ranges (never the whole course).
3. List non-`TEXT_OK` pages from `page_index.json`.
4. Resolve ambiguities **without rendering**, in this order:
   1. the text of neighbouring pages;
   2. the corresponding lesson in `anno3/6_Sistemi_Biometrici` (grep for the topic);
   3. PyMuPDF word/block coordinates (`page.get_text('blocks')`) for tables and caption pairing;
   4. only then render the single page.
5. Inspect supplementary files (scripts, images, readings).
6. Write `LezioneNN/LNN - Titolo.md` following `STYLE_GUIDE_INFERRED.md`.
7. Add placeholders for meaningful visuals.
8. Add the page table to `SOURCE_COVERAGE.md`.
9. Run the quality gate (§4).
10. Mark DONE here, then update `Project Progress State` in both `CLAUDE.md` and `AGENTS.md` (identical sections).

## 4. Quality gate

Run from the repository root:

```bash
node scripts/check-latex.mjs "lessons/cybersecurity/extra/2_Tecniche_ed_applicazioni_biometriche/LezioneNN"
```

Then check, for the lesson file:
- 0 broken relative links (`](<...>)` targets exist);
- every `SOURCE_VISUAL` line is immediately followed by `<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->`; ids sequential (`TABnn-V01`, `V02`, …); pages unique and within the PDF range; no multi-line marker (the SPA only hides the single-line form, see `STYLE_GUIDE_INFERRED.md` §7);
- no `\(` inline math, no `e'`/`perche'`/`piu'`/`puo'` transliterations;
- all headings of the form `# **`, `## **`, `### **`, `#### **`, `##### **`;
- every PDF page listed in `SOURCE_COVERAGE.md`.

Lesson 01 result (after merge): 54 formulas / 0 KaTeX errors · 16 links / 0 broken · 41 placeholders / 0 malformed or out of range · 73 headings / 0 malformed.

## 5. Efficiency log

| Lesson | Pages | Rendered | OCR | Resolved without render | Notes |
|---|---:|---:|---:|---:|---|
| 01 | 94 | 0 | 0 | 5 (pp. 8, 40, 44, 50, 91) | p. 8 false `FORMULA_AMBIGUOUS` (slide labels with `=`) |

Heuristic note: `FORMULA_AMBIGUOUS` over-triggers on slide annotations that contain `=` (for example `happy = 0.0001%`). Read the text before escalating.

## 6. Incident log

- **2026-09-11 — concurrent writers on Lesson 01.** After a session restart, the original turn kept running
  as a duplicate process of the same Claude Code session (same session id, same transcript). Both branches
  wrote Lesson 01, `COURSE_PROGRESS.md` and `SOURCE_COVERAGE.md` within minutes, overwriting each other.
  Resolution chosen by the user: keep the richer version as base (FPIR ≈ N·FMR, FMR/FNMR vs FAR/FRR per
  ISO/IEC 19795-1, eigenfaces, DTW recurrence, rule of 3), convert its 33 multi-line placeholders to
  `SOURCE_VISUAL` pairs, and port from the other version 8 placeholders (pp. 22, 29, 34, 47, 48, 49, 68, 75)
  and the notes on Automated Border Control, positive/negative classification, SIFT/SURF, intra-class
  variability vs inter-class similarity, distributed vs multimodal. This file and `SOURCE_COVERAGE.md` were
  restored from that branch and patched. Both original versions are recoverable from the session transcript.
- **Rule for future sessions:** before writing, make sure only one agent session is working on this course,
  and check `git status` plus the modification time of this file; stop if they changed unexpectedly.

## 7. Lesson log
- **Lezione02** — crash course parte 2: variabilità intra/interclasse, qualità ICAO, rappresentazione e template, regole di decisione verifica/identificazione, FMR/FNMR, DET/ROC/EER, zoo di Doddington, ISO 9241-11 e 19795, comparazione dei tratti, penetration rate (38,9% e 35,8%) e binning error; 21 placeholder, 131 formule KaTeX
- **Lezione03** — impronta a contatto: sensori Synaptics/Qualcomm/IDEMIA/Suprema con FVC e MINEX, spoofing silicone/gelatina, classificazione arch/loop/whorl, tre livelli, segmentazione, filtri contestuali, crossing number, Gabor e FingerCode, matching minuzie e ibrido, casi Mayfield/McKie, AFIS italiano; 35 placeholder, 41 formule
- **Lezione04** — convoluzione digitale e kernel; iride: vantaggi/svantaggi, NIR 700-900 nm, sistema di Daugman con operatore integro-differenziale, unwrapping, Gabor 2D, IrisCode 256 byte e maschere, distanza di Hamming con shift, 249 bit significativi, attacco Samsung S8, iride nel visibile, watch list, caso Sharbat Gula (HD 0,24) e privacy; 22 placeholder
- **Lezione05** — ML per la biometria: sistema come classificatore, primitive allenabili (face detection, indexing, qualità, anti-spoofing PurePrint), rappresentazione/valutazione/ottimizzazione, induttivo/deduttivo/trasduttivo, supervisionato/non supervisionato/RL, transfer learning, data augmentation, LOPO/LNPO; 17 placeholder
- **Lezione06** — progettazione ML: Nobel Hopfield/Hinton, workflow e caratteristiche dei modelli, rasoio di Occam, 4 regole per la biometria (workflow, L(NP)O MIX, semplicità, transfer learning), Computational Intelligence, neurone e MLP, curse of dimensionality, kNN con normalizzazione, alberi decisionali, feature manuali contro apprese, perché il deep funziona; dispensa MATLAB su CNN, AlexNet e transfer learning; 27 placeholder
- **Lezione07** — deep learning e CNN: E_train, CV e LNPO; connessioni locali e pesi condivisi, stride e feature map, ReLU, max pooling, flattening, esempio Keras; backpropagation, vanishing/exploding gradient, autoencoder e greedy layer-wise; fine tuning freeze/tune; bias e leakage dei template; classification vs detection; lab Haar Cascade; survey Sundararajan-Woodard 2018 (architetture, 8 modalità, dataset, risultati, limiti); 29 placeholder
- **Lezione08** — HW/SW per deep learning (CPU/GPU/TPU/NPU/VPU, Movidius, AI HAT+), Model Zoo, LeNet/AlexNet/VGG16/ResNet con skip connection ed embedding 512, divide et impera; identificazione con classificatore pericoloso contro estrattore di feature con N autenticazioni; softmax vs ArcFace e distanza coseno; DeepFace e FaceNet; GAN; template selection e update, catena e grafo, soglia t*, cancellazione; 34 placeholder
- **Lezione09** — metodi statistici parte 1: flusso di data reporting, inferenza contro modelli, cardinalità genuini N e impostori N^2-N, FMR/FNMR da conteggi e integrali, DET/ROC/EER, FNMR@FMR, ZeroFMR/ZeroFNMR, CMC e identificazione gerarchica, probabilità di errore p; libreria DeepFace (modelli, detector, laboratorio, compiti, bug dell'appendice); 28 placeholder
- **Lezione11** — DeepFace: embedding VGG-Face 2622, estrattori, distanza euclidea e coseno (erratum 09/02/2026), dashboard, distanze tra genuini; materiali di laboratorio e modelli Caffe/OpenCV; ottiche: ingrandimento, autofocus a contrasto, aberrazioni, lenti asferiche/liquide/metalenti, due lenti, zoom ottico/digitale/neurale (luna S21), ottiche mobile e C-mount, f-number e stop, profondità di campo, deformazioni del volto con la focale, FOV, camere di sorveglianza, risoluzione/fps/ROI/esposizione, gamma dinamica in dB, WDR/HDR, macro, rPPG, iride low cost; 43 placeholder
- **Lezione10** — metodi statistici parte 2: Bernoulli e binomiale, mezzo pollo e zoo, regola dei 3 e dei 30, FMR_N = 1-(1-FMR)^N; ML: overfitting/underfitting, partizioni, k-FCV, stratified, 5x2, LOO, LOPO/LNPO, zero/few-shot; dispensa Best Practices (Mansfield-Wayman 2002) completa: definizioni, FAR/FRR vs FMR/FNMR, FTE/FTA, pianificazione, dimensione del test, raccolta genuini/impostori, analisi DET e formule FAR/FRR, reporting, appendici; 22 placeholder
- **Lezione12** — Effetto delle ottiche con DeepFace, immagine digitale e bit per pixel, CCD/CMOS, global/rolling shutter e aliasing, sensori ad alta risoluzione, filtri, polarizzatore e biometria multispettrale, sistemi di acquisizione
- **Lezione13** — Livelli e domande di rischio privacy, linee guida, GDPR e AI Act, biometria cancellabile (salting, permutazioni IrisCode, trasformazioni non invertibili), crittosistemi biometrici, dominio crittato, schema iride destra+sinistra, bias, leakage, social media, caso uiguri, Italia 2026
- **Lezione14** — Sicurezza fisica (elementi, minacce, livelli, tailgating/piggybacking), brochure Pelco, ABC ed eGate, e-Passport, one-step/two-step, VIS/EES/RTP, volto/impronta/iride con requisiti Frontex, multibiometria e cascata spagnola, attacchi e privacy negli ABC, Vision-Box con gallery dinamiche
- **Lezione15** — Multimodalita e multibiometria, FTE/FTA dei sistemi multimodali, livelli di fusione, somma pesata e normalizzazioni, rank e decision level, sistemi gerarchici, cohort, fusione di feature classica e deep, soglie per utente, soft biometrics con Bayes, fusione pesata dalla qualita
- **Lezione16** — Flusso ottico e HOOF, gait recognition con STIP, riconoscimento delle emozioni (Ekman vs Feldman Barrett, pipeline statica/dinamica/multimodale, dataset, inter-rater agreement, SOTA SFER/DFER, WAR), etica e AI Act in HR, autenticazione silente e continua (IMU, ECG, EER(t), fusione con qualita), Keystroke DNA e Plurilock, caso Amazon France CNIL
- **Lezione17** — Inverse Turing Test e reCAPTCHA v3, Proof of Personhood e World ID con Orb e ZKP, deepfake (tipi, usi, strumenti, DeepMasterPrints), detection (forense, C2PA, challenge-response, rPPG, umani vicini al caso, pipeline IA e dataset), art. 612-quater c.p., report Entrust/Onfido 2025 (deepfake, falsificazioni digitali, injection attack, FaaS, identita sintetiche, best practice)
- **Lezione18** — Re-identificazione (corpo e volto, cross-modal, CMC e mAP, YOLOv8n e OSNet), control room, videosorveglianza classica (ottiche, zoom, dB e gamma dinamica, HDR/WDR, SoC, IP camera, PoE, ONVIF/PSIA), H.264/H.265/H.266 e GoP, i-PRO EXTREME ed edge processing, analytics comportamentali, dimensionamento per il volto (60 px tra gli occhi), sistemi enterprise e FacePRO
- **Lezione19** — Vettori di attacco classici e moderni, adversarial attack (gradiente, FGSM, white/gray/black box, targeted, attacchi fisici, evasione/avvelenamento/estrazione/inferenza, difese), presentation attack con APCER/BPCER e definizioni ISO/IEC 30107, impostori e occultatori, liveness, casi reali, antispoofing generale (NIR, 3D, moire, flusso ottico, alto frame rate, challenge-response), volto 2D (palpebre, rPPG) e 3D (Face ID, shape from motion, FaceTec, BioID)
- **Lezione20** — Spoofing e antispoofing dell'iride (artefatti, dataset, attacchi con occhio vero, PAD statico/dinamico, caso Galaxy S8, CNN cross-dataset, pupilla e micro-saccadi), impronta (stampo, gummy finger, riattivazione, sintesi, antispoofing hardware e software, sensori moderni, MasterPrints), palmo (Amazon One, PalmSecure e spoofing in cera), voce (feature, ASV, impersonificazione, replay, voice conversion, TTS, toni avversari, labbra, Doppler, LifeProof), conclusioni
- **Lezione21** — Ambient intelligence (definizione, percezione multimodale, adattivita, scenari, tratti adatti, eta e genere), dispensa sui trend (accuratezza e accettazione, progressi per tratto, scenari emergenti, otto aspetti di ricerca), pagamenti biometrici (Match on Card, NatWest, Banca Sella, PayEye, Ingenico Palm Vein), conteggio 3D, iride guidata, droni, indossabili, robot, EEG e biofeedback, 4P, griglia di simulazione
