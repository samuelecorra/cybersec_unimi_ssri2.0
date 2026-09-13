# COURSE_INVENTORY — Tecniche e Applicazioni Biometriche

> Control file for multi-session reconstruction. Regenerate the page metrics with
> `python _meta/extract_pdf_text.py` (writes `_text_cache/` and `page_index.json`, both git-ignored).

## 1. Identity of the course

| Field | Value |
|---|---|
| Course | Tecniche e Applicazioni Biometriche |
| Former / alternate name | "Complementi di Biometria" (used in L01 pp. 5–7 and in the header of `Lezione12/.../effetto_ottiche.py`) |
| Lecturer | Fabio Scotti — Università degli Studi di Milano |
| Directory | `lessons/cybersecurity/extra/2_Tecniche_ed_applicazioni_biometriche/` |
| Prerequisite course in repo | `lessons/cybersecurity/anno3/6_Sistemi_Biometrici/` (Biometrics I, 20 non-empty lessons L0–L14) |
| Exam format (L01 p. 23) | Multiple-choice questions and design exercises; lab code not examined, lab theory may be |

## 2. Totals

| Item | Count |
|---|---:|
| Lessons (folders `Lezione01`–`Lezione21`) | 21 |
| Source PDFs | 30 |
| Total PDF pages | 2091 |
| Pages with a native text layer | 2091 (0 extraction failures) |
| Non-PDF supplementary files | 31 (images, 2 Python scripts, 1 Markdown erratum, 1 README, 6 model files) |

## 3. Per-lesson inventory

| Lesson | Source PDFs (pages) | Supplementary files | Apparent topic (from filenames + text) |
|---|---|---|---|
| 01 | `Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf` (94) | — | Course/exam description; crash course of Biometrics I: definitions, 1:1 vs 1:N, 7 properties, traits, system structures |
| 02 | `Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf` (68) | — | Core theoretical notions of biometric systems (crash course, part 2) |
| 03 | `Lezione_03_Approfondimento_Impronta.pdf` (59) | — | Fingerprint deep dive |
| 04 | `Lezione_04_Convoluzione_Approfondimento_Iride.pdf` (53) | — | Convolution; iris deep dive |
| 05 | `Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf` (68) | — | Inductive machine learning for biometrics |
| 06 | `Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf` (71); `Lezione_06_Deeplearning_Network_Matlab_toolobox_examples_DISPENSA.pdf` (15) | — | Designing ML models; MATLAB Deep Learning Toolbox examples (reading) |
| 07 | `Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf` (82); `Lezione_07_DeepLearning_per_biometria_DISPENSA_PAPER.pdf` (34) | `face1.jpg`, `face2.jpg`, `face3.jpg` | Deep learning and CNNs for biometrics; paper-style reading |
| 08 | `Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf` (77) | — | CNNs for identification/recognition; template update |
| 09 | `Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf` (82) | — | DeepFace; statistical methods for biometrics (part 1) |
| 10 | `Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf` (82); `Lezione_10b_Best Practices Biometrics_Dispensa.pdf` (36) | — | Statistical methods (part 2); best practices for biometric testing (reading) |
| 11 | `Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf` (135) | `Cartella Codice e immagini-20260911/` (code + 7 images + erratum); `File per lab e prove-20260911/` (3 images + `modelNweight/` 6 files) | Biometric image acquisition: techniques and examples; DeepFace lab |
| 12 | `Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf` (109) | `Immagini e script per analisi effetto focali sul riconoscimento del volto-20260911/` (script + 8 images) | Optics and sensors; effect of focal length on face recognition |
| 13 | `Lezione_13_Difesa_della_Privacy_Crittazione.pdf` (111); `Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf` (18) | — | Privacy defence, encryption; privacy rules and design (reading) |
| 14 | `Lezione_14_ElevataSicurezzaFisica_ABC.pdf` (80); `Lezione_14_DispensaABC.pdf` (39); `Lezione_14_brocure_commerciale_Pelco_Physical_Security_Guide.pdf` (12) | — | High physical security; Automated Border Control; commercial brochure |
| 15 | `Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf` (70) | — | Advanced design: template update, information fusion, multimodality |
| 16 | `Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf` (93) | — | Optical flow, gait, face, emotions, continuous authentication |
| 17 | `Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf` (103); `Lezione_17_ONFIDO_2025-identity-fraud-report.pdf` (42) | — | Reverse Turing test, deepfake detection; industry identity-fraud report |
| 18 | `Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf` (116) | — | Video surveillance and biometric recognition |
| 19 | `Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf` (129) | — | Spoofing, adversarial attacks, anti-spoofing for 2D/3D face |
| 20 | `Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf` (76) | — | Spoofing/anti-spoofing for iris, fingerprint, voice; final remarks |
| 21 | `Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf` (108); `Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf` (28); `Lezione21_c_griglia_simulazione.pdf` (1) | `README.md` | Ambient intelligence, special applications; monomodal trends (reading); exam-simulation answer grid |

## 4. Supplementary material — details

### Lezione07
- `face1.jpg` (647 KB), `face2.jpg` (780 KB), `face3.jpg` (582 KB): face photographs for CNN examples.
- `face3.jpg` is byte-identical to `Lezione11/File per lab e prove-20260911/face3.jpg` (MD5 `796e531f4eb15e52be63a16991a4c953`).

### Lezione11 — `Cartella Codice e immagini-20260911/`
- `codice_L11.py` (10 KB): **Google Colab notebook export**, not a runnable plain script (`!pip` lines, `google.colab.patches.cv2_imshow`).
  - Purpose: demonstrate DeepFace on face images.
  - Steps: `DeepFace.analyze(..., actions=['age','gender'])` on `Cindy24.jpg` / `Cindy57.jpg`; draws the detected face `region` box; `DeepFace.represent` to obtain embeddings (a comment states VGG-Face embeddings have length 2622); loops over 7 extractors (`VGG-Face`, `Facenet`, `OpenFace`, `DeepFace`, `DeepID`, `Dlib`, `ArcFace`) printing embedding sizes; computes Euclidean distance, cosine distance and cosine similarity between two embeddings; plots images, embedding bar charts and the squared-difference vector as a heatmap.
  - Known defects in the export: calls `plot_embedding(...)`, which is never defined; a leftover `print` after the loop reuses the last `model_name`; the plotting function is defined twice.
  - Libraries: `deepface`, `opencv-python`, `numpy`, `matplotlib`, `scipy.spatial.distance`, `PIL`.
- `aggiornamento.md`: erratum dated 09/02/2026 — `calculate_metrics` was corrected to distinguish **cosine distance** (SciPy returns a distance; 0 = same direction) from **cosine similarity** = 1 − distance (range ≈ [−1, 1]).
- Images: `Cindy24.jpg`, `Cindy50.jpg`, `Cindy57.jpg` (same person at different ages, per filenames and script usage), `KaiaGerber_CindyCrawford.jpg`, `gerber_familiy.jpg`, `Rande.jpg`, `Finiteleferie.jpg` (alternative inputs, some commented out in the script).

### Lezione11 — `File per lab e prove-20260911/`
- `face3.jpg` (duplicate, see above), `Mark1.jpg`, `Mark2.jpg`: test images.
- `modelNweight/`: pretrained models (identified by file type only; no script in the course loads them):
  - `age_deploy.prototxt` + `age_net.caffemodel` (45.7 MB): Caffe age-estimation network definition + weights.
  - `gender_deploy.prototxt` + `gender_net.caffemodel` (45.6 MB): Caffe gender-classification network definition + weights.
  - `opencv_face_detector.pbtxt` + `opencv_face_detector_uint8.pb` (2.7 MB): OpenCV DNN face detector (TensorFlow graph, quantised uint8).

### Lezione12 — `Immagini e script per analisi effetto focali sul riconoscimento del volto-20260911/`
- `effetto_ottiche.py` (3 KB): **Colab export** (header: "Corso di Complementi di biometria — Fabio Scotti — Acquisizioni di immagini biometriche: ottiche e sensori").
  - Purpose: show how lens focal length (perspective) changes face embeddings.
  - Input: `girl2_f24.jpg`, `girl2_f50.jpg`, `girl2_f85.jpg`, `girl2_f200.jpg`, `man_f16mm.jpg`, `man_f200mm.jpg`.
  - Algorithm: VGG-Face embeddings via `DeepFace.represent`, 6×6 Euclidean distance matrix, plotted as an annotated heatmap.
  - Images in the folder not used by the script: `girl_f14mm.jpg`, `girl_f300mm.jpg`.

### Lezione21
- `README.md`: the lesson-21 material is published, but the **exam-simulation text is not**; only the printable answer grid (`Lezione21_c_griglia_simulazione.pdf`) is provided. Lists lesson-21 topics (ambient intelligence, biometric payments, fingerprint on credit card, iris, IR palm, people counting, biometrics on drones/wearables/glasses/bodycams/robots, biometrics of mental states, final recommendations).

## 5. Relationship notes

- Files named `*DISPENSA*` / `*Dispensa*` / `*PAPER*` are **readings** complementing the slide deck of the same lesson; merge them into that lesson's Markdown, or into a sibling `LNNb` file when large.
- `Lezione_14_brocure_commerciale_Pelco_Physical_Security_Guide.pdf` and `Lezione_17_ONFIDO_2025-identity-fraud-report.pdf` are **third-party industry documents**: extract concepts and figures with attribution, never present them as lecturer material.
- L01 (and likely L02) is a **crash course** of Biometrics I (L01 p. 2 note): reconstruct it fully but use recall links to `anno3/6_Sistemi_Biometrici` instead of re-deriving everything.

## 6. Page-flag totals from the batch scan

| Flag | Pages | Handling |
|---|---:|---|
| `TEXT_OK` | 1619 | text only |
| `LIKELY_VISUAL` | 403 | placeholder when the visual carries meaning; resolve from text/Biometrics I first |
| `FORMULA_AMBIGUOUS` | 59 | heuristic over-triggers on `=` in slide labels; check text, inspect only if a real formula is lost |
| `TABLE_AMBIGUOUS` | 8 | rebuild from word coordinates (`page.get_text('blocks')`) before any render |
| `EMPTY_OR_DECORATIVE` | 2 | no content |
| `EXTRACTION_FAILED` | 0 | — |

Full per-page list: `_meta/EXTRACTION_REPORT.md` (committed) and `_meta/page_index.json` (regenerable).
