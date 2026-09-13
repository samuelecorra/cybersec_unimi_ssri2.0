# SOURCE_COVERAGE — Tecniche e Applicazioni Biometriche

Page-level content accounting. **A page counts as covered when its native text was
extracted, its role identified, its content incorporated (or judged content-free) and any
meaningful visual represented by a placeholder.** Covering a page does **not** mean
rendering it.

Columns:
- **Flag**: heuristic from `_meta/extract_pdf_text.py` (`TEXT_OK`, `LIKELY_VISUAL`, `FORMULA_AMBIGUOUS`, `TABLE_AMBIGUOUS`, `EMPTY_OR_DECORATIVE`, `EXTRACTION_FAILED`).
- **Destination**: section of the lesson Markdown; `+ PH` = image placeholder inserted (a `SOURCE_VISUAL` line with id `TABnn-Vnn`, followed by the `INSERT INSTRUCTOR SLIDE/DIAGRAM HERE` marker).
- **Visual check**: `not required` · `inspected` · `OCR required` · `needs later inspection`.

---

## Lezione01 — DONE

**Lesson file:** `Lezione01/L01 - Descrizione del corso, esame e introduzione alla biometria.md`

### `Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf` (94 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | course title, lecturer | — (title) | not required |
| 2 | yes | TEXT_OK | outline L1; note on slides reused from Sistemi Biometrici | §0 | not required |
| 3 | yes | TEXT_OK | divider "Parte A" | Parte A heading | not required |
| 4 | yes | LIKELY_VISUAL | divider "Struttura dell'insegnamento" (decorative image) | §1 | not required |
| 5 | yes | TEXT_OK | crash course definition; basic topics may be examined | §1.1 | not required |
| 6 | yes | TEXT_OK | lesson components; lab code not examined | §1.2 | not required |
| 7 | yes | TEXT_OK | differences from Sistemi Biometrici (3 factors) | §2 | not required |
| 8 | yes | FORMULA_AMBIGUOUS | annotated image: emotion, ethnicity, height/weight, movement metrics, P(mask attack), re-identification. **False positive**: `=` are labels, not a formula | §3.1 + PH | not required |
| 9 | yes | TEXT_OK | teaser: sensors, optics, devices | §3.2 table | not required |
| 10 | yes | TEXT_OK | teaser: deep learning, CNN pipeline, DeepFace | §3.2 + PH | not required |
| 11 | yes | LIKELY_VISUAL | teaser: biometric surveillance and privacy | §3.2 table | not required |
| 12 | yes | LIKELY_VISUAL | teaser: behavioural recognition | §3.2 table | not required |
| 13 | yes | LIKELY_VISUAL | teaser: anomaly behavior detection | §3.2 table | not required |
| 14 | yes | LIKELY_VISUAL | teaser: emotions, internal states | §3.2 table | not required |
| 15 | yes | LIKELY_VISUAL | teaser: multimodal (face 67%, voice 71%, gait 30%, soft 7%) | §3.2 + PH | not required |
| 16 | yes | LIKELY_VISUAL | teaser: continuous authentication | §3.2 table | not required |
| 17 | yes | LIKELY_VISUAL | teaser: attack and defence | §3.2 table | not required |
| 18 | yes | LIKELY_VISUAL | teaser: deepfake liveness detection | §3.2 table | not required |
| 19 | yes | LIKELY_VISUAL | teaser: high-security systems | §3.2 table | not required |
| 20 | yes | TEXT_OK | teaser: privacy, personal vs database storage | §3.2 + PH | not required |
| 21 | yes | LIKELY_VISUAL | teaser: innovative biometric systems | §3.2 table | not required |
| 22 | yes | TEXT_OK | multidisciplinary areas (6) | §3.3 + PH | not required |
| 23 | yes | TEXT_OK | exam rules | §4.1 (+ callout §1.2) | not required |
| 24 | yes | TEXT_OK | exam registration on UNIMIA | §4.2 | not required |
| 25 | yes | LIKELY_VISUAL | optional references (titles only in cover images) | §4.3 + PH | not required |
| 26 | yes | TEXT_OK | how to prepare | §4.4 | not required |
| 27 | yes | TEXT_OK | divider "Parte B" | Parte B heading | not required |
| 28 | yes | LIKELY_VISUAL | divider "basi e terminologia" | §5 | not required |
| 29 | yes | TEXT_OK | know / have / are / do; classic vs biometric | §5.1 + PH | not required |
| 30 | yes | TEXT_OK | definition of biometrics | §5.2 | not required |
| 31 | yes | TEXT_OK | identity recognition; two categories | §6.1 | not required |
| 32 | yes | TEXT_OK | authentication 1:1 vs identification 1:N | §6.2 | not required |
| 33 | yes | TEXT_OK | closed vs open identification, 2 examples | §6.3 | not required |
| 34 | yes | TEXT_OK | current terminology tree | §6.4 + PH | not required |
| 35 | yes | TEXT_OK | positive vs negative; title typo "passiva" flagged | §7.1 | not required |
| 36 | yes | TEXT_OK | 3 examples positive/negative | §7.2 | not required |
| 37 | yes | TEXT_OK | probe definition | §8 | not required |
| 38 | yes | TEXT_OK | gallery definition | §8 | not required |
| 39 | yes | TEXT_OK | physical vs behavioural traits | §5.3 | not required |
| 40 | yes | TEXT_OK | qualitative security chart; ranking resolved from Biometrics I L01 §2.10 | §5.4 + PH | not required |
| 41 | yes | TEXT_OK | advantages (6) | §9.1 (+ callout §7.2) | not required |
| 42 | yes | TEXT_OK | disadvantages (5) | §9.2 | not required |
| 43 | yes | TEXT_OK | 7 properties of the trait | §10 | not required |
| 44 | yes | TEXT_OK | long-running deployments; caption pairing rebuilt from word coordinates; "Belgio" error flagged | §11.1 + PH | not required |
| 45 | yes | TEXT_OK | ABC: Visionbox, SmartGates NZ; «Advanced» → Automated Border Control flagged | §11.2 + PH | not required |
| 46 | yes | LIKELY_VISUAL | surveillance systems (title only) | §11.3 + PH | not required |
| 47 | yes | LIKELY_VISUAL | commercial applications list | §11.3 + PH | not required |
| 48 | yes | TEXT_OK | mobile devices timeline; date errors flagged | §11.4 + PH | not required |
| 49 | yes | TEXT_OK | special-purpose devices | §11.4 + PH | not required |
| 50 | yes | TEXT_OK | applications by sector; 3-column table rebuilt from word coordinates (x≈164/366/568) | §11.5 | not required |
| 51 | yes | LIKELY_VISUAL | market trend chart (no values in text) | §11.6 + PH | not required |
| 52 | yes | TEXT_OK | divider "Funzionamento" | §12 | not required |
| 53 | yes | TEXT_OK | enrollment vs recognition | §12.1 | not required |
| 54 | yes | TEXT_OK | pattern recognition training/testing | §12.2 + PH | not required |
| 55 | yes | TEXT_OK | phases and terminology (trait, sample, features, coding, template) | §12.3 + PH | not required |
| 56 | yes | TEXT_OK | matching score and threshold (87%) | §12.4 + PH | not required |
| 57 | yes | TEXT_OK | most used traits | §13.1 | not required |
| 58 | yes | TEXT_OK | fingerprint and iris deferred | §13.1 | not required |
| 59 | footer only | LIKELY_VISUAL | image-only slide | §13.1 PH | not required |
| 60 | yes | TEXT_OK | face: characteristics, mug shot, 3D | §13.2 + PH | not required |
| 61 | yes | TEXT_OK | face sensors and difficulties | §13.2 | not required |
| 62 | yes | TEXT_OK | face matching: eigenfaces, attributes, DeepFace | §13.2 + PH | not required |
| 63 | footer only | LIKELY_VISUAL | image-only slide | §13.2 PH | not required |
| 64 | yes | TEXT_OK | hand: characteristics, 3 views | §13.3 | not required |
| 65 | yes | TEXT_OK | hand sensors and algorithms | §13.3 + PH | not required |
| 66 | footer only | LIKELY_VISUAL | image-only slide | §13.3 PH | not required |
| 67 | yes | TEXT_OK | voice characteristics | §13.4 | not required |
| 68 | yes | TEXT_OK | speech vs speaker recognition | §13.4 + PH | not required |
| 69 | footer only | LIKELY_VISUAL | image-only slide | §13.4 PH | not required |
| 70 | yes | TEXT_OK | signature general | §13.5.1 | not required |
| 71 | yes | TEXT_OK | dynamic characteristics; banking | §13.5.1 | not required |
| 72 | yes | TEXT_OK | time signals x, y, pressure, azimuth, inclination | §13.5.2 | not required |
| 73 | yes | TEXT_OK | online matching, singular points, techniques, DTW | §13.5.2 + PH | not required |
| 74 | yes | TEXT_OK | acquisition; template ~1500 bytes | §13.5.2 | not required |
| 75 | yes | TEXT_OK | offline: projections histograms, Extended Shadow Code | §13.5.3 + PH | not required |
| 76 | yes | TEXT_OK | offline template, Adaptive SIFT–SURF | §13.5.3 + PH | not required |
| 77 | yes | TEXT_OK | pros/cons; same person signing 10 times | §13.5.4 + PH | not required |
| 78 | yes | TEXT_OK | APACS FRR 0.001% / FAR 5%; second reading | §13.5.5 | not required |
| 79 | yes | TEXT_OK | multimodal systems | §13.6 | not required |
| 80 | yes | TEXT_OK | soft biometrics | §13.7 | not required |
| 81 | yes | LIKELY_VISUAL | divider "Strutture" | §14 | not required |
| 82 | yes | TEXT_OK | possible structures | §14.1 | not required |
| 83 | yes | TEXT_OK | enrollment diagram (template → DB) | §14.2 + PH | not required |
| 84 | yes | TEXT_OK | enrollment (template + identity → DB) | §14.2 + PH | not required |
| 85 | yes | TEXT_OK | verification with DB | §14.3 + PH | not required |
| 86 | yes | TEXT_OK | identification | §14.4 + PH | not required |
| 87 | yes | TEXT_OK | enrollment into biometric document | §14.5 + PH | not required |
| 88 | yes | TEXT_OK | verification with biometric document | §14.5 + PH | not required |
| 89 | yes | TEXT_OK | distributed systems | §14.6 | not required |
| 90 | yes | TEXT_OK | distributed architecture (title only) | §14.6 + PH | not required |
| 91 | yes | LIKELY_VISUAL | Mansfield & Wayman (title only); 4 phases recalled from Biometrics I L04 §4.3 | §14.7 + PH | not required |
| 92 | yes | TEXT_OK | on-card systems, 4 products | §14.8 + PH | not required |
| 93 | yes | TEXT_OK | match on sensor, Synaptics SentryPoint, under the glass | §14.9 + PH | not required |
| 94 | yes | TEXT_OK | summary | §15 | not required |

**Totals:** 94/94 pages accounted for · 41 placeholders (`TAB01-V01`…`V41`, `SOURCE_VISUAL` pairs) · 0 pages rendered · 0 OCR.
**Resolved without rendering:** p. 8 (false formula flag), p. 40 and p. 91 (via Biometrics I), p. 44 and p. 50 (via PyMuPDF word coordinates).
**Slide errors documented in the lesson:** p. 35 "passiva" → negativa; p. 43 "Circonvezione" spelling and opposite convention vs Jain; p. 44 Schiphol in the Netherlands, 2004 Olympics in Athens; p. 45 «Advanced» → Automated Border Control; p. 48 Touch ID/Face ID/Galaxy S5/Nexus dates.

---

## Lezione02 — DONE

**Lesson file(s):** `Lezione02/L02 - Principali nozioni teoriche dei sistemi biometrici.md`

### `Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf` (68 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Lezione 2 Seconda parte alla Introduzione alla biometria Fabio Scotti fabio.scot | §0 | not required |
| 3 | yes | TEXT_OK | Outline - Aspetti analitici e definizioni - Variabilità intraclasse - Similitudi | §0 | not required |
| 4 | yes | LIKELY_VISUAL | Tratto biometrico: aspetti analitici (Sistema biometrico: elementi caratteristic | §1 | not required |
| 5 | yes | TEXT_OK | Variabilità temporale del tratto (1) Campioni dello stesso individuo tempo mese  | §1 + PH | not required |
| 6 | yes | TEXT_OK | Variabilità temporale del tratto (2) Campioni dello stesso individuo tempo +1 an | §1 + PH | not required |
| 7 | yes | TEXT_OK | Umano VS. Sistema (1) Un sistema biometrico NON deve necessariamente ragionare c | §1, §1.2 | not required |
| 8 | yes | TEXT_OK | Umano VS. Sistema (2) Il nostro cervello ci fa vedere a sinistra il volto di A.  | §1, §1.2 + PH | not required |
| 9 | yes | TEXT_OK | Variabilità intraclasse (1) - Si intende la variazione del sample o delle featur | §2 | not required |
| 10 | yes | TEXT_OK | Similitudine interclasse - Particolare vicinanza dei sample o delle feature acqu | §2 + PH | not required |
| 11 | title only | LIKELY_VISUAL | Basi del miglioramento del sistema biometrico | §3, §3.1 | not required |
| 12 | yes | TEXT_OK | Fasi da analizzare - Acquisizione del tratto - Sensori e dati ambientali - Contr | §3, §3.1 | not required |
| 13 | yes | TEXT_OK | Miglioramento dell'acquisizione - Analizziamo i metodi per progettare ed eventua | §3, §3.1 | not required |
| 14 | yes | TEXT_OK | Controllo della qualità - Introduciamo le basi del controllo di qualità del samp | §3, §3.2 | not required |
| 15 | yes | TEXT_OK | Acquisizione: controllo della qualità (1) - Dopo l'acquisizione molti sistemi at | §3, §3.2 | not required |
| 16 | yes | TEXT_OK | Esempi di indice di qualità Nel caso delle impronte è possibile calcolare un ind | §3, §3.2 + PH | not required |
| 17 | yes | TEXT_OK | Acquisizione: controllo della qualità (2) - Il concetto di base è semplice, ma l | §3, §3.2 + PH | not required |
| 18 | yes | TEXT_OK | Esempio di enhacement (1) - Un algoritmo automatico può rilevare la presenza dei | §3, §3.3 + PH | not required |
| 19 | yes | TEXT_OK | Esempio di enhacement (2) - In questa applicazione, l'algoritmo di enhancement r | §3, §3.3 + PH | not required |
| 20 | yes | TEXT_OK | Metodi di segmentazione - La regione di interesse nell'immagine acquisita viene  | §3, §3.4 + PH | not required |
| 21 | yes | TEXT_OK | Funzionamento del riconoscimento (matching) Le basi del pattern recognition che  | §4, §4.1 | not required |
| 22 | yes | TEXT_OK | Fasi da analizzare - Acquisizione del tratto - Sensori e dati ambientali - Contr | §4, §4.1 | not required |
| 23 | yes | TEXT_OK | Rappresentazione (3) - Visualizzazione del problema della rappresentazione in un | §4, §4.2 | not required |
| 24 | yes | TEXT_OK | Rappresentazione (4) - Visualizzazione del problema della rappresentazione in un | §4, §4.2 + PH | not required |
| 25 | yes | TEXT_OK | Rappresentazione (5) - Il problema della rappresentazione si suddivide in - Rapp | §4, §4.3 | not required |
| 26 | yes | TEXT_OK | Diversi tipi di template http://www.thesmokinggun.com Hand feature set Lunghezza | §4, §4.4 + PH | not required |
| 27 | yes | TEXT_OK | Matching (1) Le problematiche inerenti al problema della rappresentazione nel si | §5, §5.1 | not required |
| 28 | yes | TEXT_OK | Matching (2) Definizione della metrica Feature 1 Feature 2 Feature 3 Feature 4 F | §5, §5.1 | not required |
| 29 | yes | TEXT_OK | Matching (3) Esempio di matching fra impronte fra input e template Template in i | §5, §5.2 + PH | not required |
| 30 | yes | TEXT_OK | Quanto sbaglia? - Nel caso dei sistemi biometrici non è banale rispondere alla d | §6 | not required |
| 31 | yes | TEXT_OK | Match Score e soglia DataBase Acquisition Feature Extraction Coding Matching Ide | §6 | not required |
| 32 | yes | TEXT_OK | Genuini ed impostori - Prima di tutto la formulazione del problema è diversa a s | §7, §7.1 | not required |
| 33 | yes | TEXT_OK | Formulazione per la verifica - Nel caso di verification ("tu sei chi dici di ess | §7, §7.2 | not required |
| 34 | yes | TEXT_OK | Regola di decisione per la verifica - Si tratta di una comparazione con soglia - | §7, §7.2 | not required |
| 35 | yes | TEXT_OK | Formulazione per l'identificazione - Nel caso di identificazione ("il sistema co | §7, §7.3 | not required |
| 36 | yes | FORMULA_AMBIGUOUS | Regola di decisione per l'identificazione - Si tratta di M comparazioni con sogl | §7, §7.3 | not required |
| 37 | yes | TEXT_OK | Distanza fra i template (1) - N template, provenienti dalla stessa persona ed ac | §8 | not required |
| 38 | yes | FORMULA_AMBIGUOUS | Distanza fra i template (2) - Questo porta al fatto che la soglia T non può esse | §8 | not required |
| 39 | yes | TEXT_OK | Misurazione della accuratezza dei sistemi biometrici Una delle principali metric | §9 | not required |
| 40 | yes | TEXT_OK | Distribuzioni dei match score - Sia Xi_j il j-esimo template dell'individuo i-es | §9, §9.1 + PH | not required |
| 41 | yes | TEXT_OK | False Match e False Non-Match Occorre assolutamente distinguere i due casi possi | §9, §9.2 | not required |
| 42 | yes | FORMULA_AMBIGUOUS | Decision Error Tradeoff (DET) Receiver Operating Characteristic (ROC) Le perform | §10, §10.1 + PH | not required |
| 43 | yes | TEXT_OK | Regioni di funzionamento nella DET Regolando la soglia T possiamo regolare il li | §10, §10.2 + PH | not required |
| 44 | title only | LIKELY_VISUAL | Caratteristiche degli utenti e usabilità | §11, §11.1 | not required |
| 45 | yes | TEXT_OK | Caratterizzazione degli utenti: lo zoo di Doddington - SHEEPS (Pecore): utenti c | §11, §11.1 + PH | not required |
| 46 | yes | TEXT_OK | - ISO 9241-11: The usability is the extent to which a product can be used by spe | §11, §11.2 | not required |
| 47 | yes | LIKELY_VISUAL | NIST Valutazioni indipendenti Si possono usare laboratori esterni accreditati (e | §11, §11.3 | not required |
| 48 | yes | TEXT_OK | Strategie di valutazione ISO/IEC 19795 Biometric Performance Testing and Reporti | §11, §11.3 + PH | not required |
| 49 | yes | TEXT_OK | Progettazione e comparazione Dei sistemi biometrici | §12 | not required |
| 50 | yes | TEXT_OK | Problema della comparazione E' un problema molto complesso: - Vi sono molti para | §12, §12.1 + PH | not required |
| 51 | yes | TEXT_OK | Comparazione (qualitativa-quantitativa) Tratto Auten- ticazio ne Identifi- cazio | §12, §12.2 + PH | not required |
| 52 | yes | TEXT_OK | Autenticazione/Identificazione - Attenzione! Non tutti i sistemi possono essere  | §12, §12.3 | not required |
| 53 | yes | LIKELY_VISUAL | Gradimento/Accuratezza Come è possibile misurare il gradimento? Questionari, int | §12, §12.4 | not required |
| 54 | yes | TEXT_OK | Interoperabilità (2) L'argomento della interoperabilità diventerà sempre più imp | §13 | not required |
| 55 | yes | TEXT_OK | Scalabilità - Come viene influenzata l'accuratezza e la velocità del sistema ris | §13 | not required |
| 56 | yes | TEXT_OK | Indicizzazione dei DB biometrici Come rendere le ricerche più efficienti e veloc | §13 | not required |
| 57 | yes | TEXT_OK | Scalabilità - I sistemi che devono gestire una grande quantità di identità dovre | §13 | not required |
| 58 | yes | TEXT_OK | Partizione del DB: caso delle impronte - Sir Edward Henry, un ispettore della Po | §14, §14.1 | not required |
| 59 | yes | TEXT_OK | Organizzazione del DB, Tasso di Penetrazione nel DB - L'obiettivo di gestire eff | §14, §14.2 | not required |
| 60 | yes | TEXT_OK | Penetration rate: caso semplificato - Immaginiamo avere un DB di 900 persone. Ci | §14, §14.3 | not required |
| 61 | yes | TEXT_OK | Feature Extraction Module Classifier Bambino/Teenager/Adulto Vettore con i 6 pro | §14, §14.3 + PH | not required |
| 62 | yes | FORMULA_AMBIGUOUS | Penetration rate - Supponiamo di essere nelle fasi iniziali del progetto e di no | §14, §14.4 | not required |
| 63 | yes | TEXT_OK | Tasso di penetrazione: caso realistico - Prendiamo il caso di un sistema naziona | §14, §14.5 | not required |
| 64 | yes | TEXT_OK | Binning (1) Acquisition Module Feature Extraction Module Template Traits Quality | §15, §15.1 + PH | not required |
| 65 | yes | TEXT_OK | Binning (2) - Per giovare delle partizioni del DB occorre disporre di un algorit | §15, §15.1 | not required |
| 66 | yes | TEXT_OK | Binning error (1) - I problemi nascono quando un individuo presenta i propri tra | §15, §15.2 | not required |
| 67 | yes | TEXT_OK | Binning error Binning error = Prob(fare almeno un errore) per una delle due clas | §15, §15.2 | not required |
| 68 | yes | TEXT_OK | In sintesi FINE Abbiamo visto: – Aspetti analitici e definizioni ▪ Variabilità i | §17 | not required |

**Totals:** 68/68 pages accounted for · 21 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione03 — DONE

**Lesson file(s):** `Lezione03/L03 - Impronte digitali a contatto - sensori, classificazione, elaborazione e matching.md`

### `Lezione_03_Approfondimento_Impronta.pdf` (59 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Lezione 03 Principali tratti monomodali: impronta a contatto Fabio Scotti fabio. | §0 | not required |
| 3 | yes | TEXT_OK | Impronte digitali a contatto L3 Nota: le impronte senza contatto verranno presen | §0 | not required |
| 4 | yes | TEXT_OK | Cosa sono le impronte digitali? - Sono creste e valli della pelle (Dermatoglyphi | §1, §1.1 + PH | not required |
| 5 | yes | TEXT_OK | Diverse scale Sistemi - sistemi integrati - smartcard - per PC - "stand alone" - | §1, §1.2 + PH | not required |
| 6 | yes | TEXT_OK | Alcune applicazioni basate sulle impronte Applicazioni forensi Governative Comme | §1, §1.2 | not required |
| 7 | yes | TEXT_OK | Punti di forza e debolezze - Punti di forza - E' una tecnologia matura, ampiamen | §1, §1.3 | not required |
| 8 | yes | LIKELY_VISUAL | SENSORI e SISTEMI COMMERCIALI PER L'IMPRONTA A CONTATTO | §2, §2.1 | not required |
| 9 | yes | LIKELY_VISUAL | Panoramica delle immagini dei sensori I vari sensori producono delle immagini co | §2, §2.1 + PH | not required |
| 10 | yes | TEXT_OK | Proprietà del sensore - Nello scegliere un sensore dobbiamo controllare: - risol | §2, §2.1 | not required |
| 11 | yes | TEXT_OK | Smartphone Fingerprint Sensor Synaptics SentryPoint - Encryption - All biometric | §2, §2.2 | not required |
| 12 | yes | TEXT_OK | Under ….. (capacitivo) - Synaptics FS4500: 6x6mm Natural ID fingerprint sensor s | §2, §2.2 + PH | not required |
| 13 | yes | TEXT_OK | Match-in-sensor: Synaptic FS7600 (capacitive) (FS7600 supports up to a 0.2 mm se | §2, §2.2 | not required |
| 14 | yes | TEXT_OK | The Synaptics Clear ID FS9500 - Optical in-display fingerprint sensors - Works w | §2, §2.2 + PH | not required |
| 15 | yes | LIKELY_VISUAL | Qualcomm 3D Sonic Sensors - First ultrasonic fingerprint readers on mobile devic | §2, §2.3 + PH | not required |
| 16 | yes | TEXT_OK | Qualcomm 3D Sonic Max Avere due impronte apre moltissimi scenari (multimodale, c | §2, §2.3 + PH | not required |
| 17 | yes | TEXT_OK | Esempi di sensori/sistemi commerciali per sicurezza fisica di alta gamma (high e | §2, §2.4 | not required |
| 18 | yes | TEXT_OK | Esempio di sistema commerciale IDEMIA linea SIGMA - SIGMA Extreme - Sensore 23x2 | §2, §2.4 + PH | not required |
| 19 | yes | TEXT_OK | Immagini delle impronte - Il sample della impronta è una immagine in toni di gri | §2, §2.5 | not required |
| 20 | yes | TEXT_OK | Spoofing e controlli Confrontiamo le immagini ottenute dei sensori Dito reale Di | §2, §2.6 + PH | not required |
| 21 | yes | TEXT_OK | UNICITA CLASSIFICAZIONE DELLE IMPRONTE | §3, §3.1 | not required |
| 22 | yes | TEXT_OK | Unicità delle impronte (4) - In realtà abbiamo a disposizione molti altri parame | §3, §3.1 | not required |
| 23 | yes | TEXT_OK | Sistema di classificazione attuale (1) - Le impronte si dividono in - Arch (Plai | §3, §3.2 + PH | not required |
| 24 | yes | TEXT_OK | Arches Plain arch Tented arch Ridges enter on one side of the impression and flo | §3, §3.3 + PH | not required |
| 25 | yes | TEXT_OK | Loops - A loop is that type of fingerprint pattern in which one or more of the r | §3, §3.4 + PH | not required |
| 26 | yes | TEXT_OK | Whorls (1) - at least two deltas are present - with a recurve in front of each P | §3, §3.5 + PH | not required |
| 27 | yes | TEXT_OK | Whorls (2) The Double Loop Two separate loop formations, with two separate and d | §3, §3.5 + PH | not required |
| 28 | yes | TEXT_OK | Tre livelli di analisi - Una impronta può essere esaminata su tre livelli: - glo | §4 | not required |
| 29 | yes | TEXT_OK | Livello I A livello globale si osservano: 1. il flusso delle linee (arch, loop,  | §4 + PH | not required |
| 30 | yes | TEXT_OK | Livello II - A livello locale è possibile identificare fino a circa 150 diverse  | §4 + PH | not required |
| 31 | yes | TEXT_OK | Livello III A livello ultra-fine è possibile individuare i seguenti dettagli: -  | §4 + PH | not required |
| 32 | yes | TEXT_OK | Esempi al Livello III Esempio a 800 DPI (si vedono solo alcuni pori, anche se di | §4 + PH | not required |
| 33 | yes | TEXT_OK | ELABORAZIONE DELLE IMPRONTE | §5, §5.1 | not required |
| 34 | yes | TEXT_OK | Sequenza di passi tipica Nel modulo per l'Estrazione delle feature si eseguono t | §5, §5.1 | not required |
| 35 | yes | TEXT_OK | Segmentazione - Gli algoritmi per la segmentazione estraggono il foreground (l'i | §5, §5.2 + PH | not required |
| 36 | yes | TEXT_OK | Manipolazione delle immagini: filtri contestuali (2) - Questi filtri lavorano su | §5, §5.3 + PH | not required |
| 37 | yes | TEXT_OK | Panoramica delle tecniche Prefiltering Enhancement Binarization Thinning Minutia | §5, §5.4 + PH | not required |
| 38 | yes | TEXT_OK | Livello 1: ridge counting - E' una misura dei ridge che attraversano una linea i | §6, §6.1 + PH | not required |
| 39 | yes | TEXT_OK | Livelli 1: analisi delle frequenze spaziali - E' una misura di quanto sono stret | §6, §6.2 + PH | not required |
| 40 | yes | TEXT_OK | Crossing Number 8 neigborhood of the pixel p 𝐶𝑁𝑝= 1 2 ෍ 𝑘=1 𝑛𝑘−𝑛 𝑘+1 mod 8 If 𝐶𝑁 | §7, §7.1 + PH | not required |
| 41 | yes | LIKELY_VISUAL | Livello 2: Esempi di pattern associati a fine riga e biforcazioni nelle immagini | §7, §7.1 + PH | not required |
| 42 | yes | TEXT_OK | Structural post processing (1) - I moduli di structural post processing sono tip | §7, §7.2 + PH | not required |
| 43 | yes | TEXT_OK | Direct Gray Scale Extraction - Si sceglie un insieme di punti di partenza con un | §7, §7.3 + PH | not required |
| 44 | yes | FORMULA_AMBIGUOUS | The even-symmetric Gabor filter has the general form where Φ is the orientation  | §8, §8.1 + PH | not required |
| 45 | yes | TEXT_OK | Il template FingerCode è importante perché - di livello 1 - funziona anche con b | §8, §8.2 + PH | not required |
| 46 | yes | TEXT_OK | Matching Livello 2 (base): Minutiae-based - Two minutiae are considered as corre | §8, §8.3 | not required |
| 47 | yes | LIKELY_VISUAL | Matching Livello 2 classico (con angoli): | §8, §8.3 + PH | not required |
| 48 | yes | LIKELY_VISUAL | Matching con minutiae classico | §8, §8.3 + PH | not required |
| 49 | yes | TEXT_OK | Hybrid matching Comparing subsets with: - Minutiae types - Angles - Distances In | §8, §8.4 + PH | not required |
| 50 | yes | TEXT_OK | Esempio conclusivo - Ecco alcune feature estratte sui 3 livelli Livello I: Mappa | §8, §8.5 + PH | not required |
| 51 | yes | TEXT_OK | Dove è l'errore ? In tutti questi casi ha fallito l'esaminatore Le impronte sono | §9 + PH | not required |
| 52 | yes | TEXT_OK | SISTEMI AFIS E NAZIONALI per le IMPRONTE | §10, §10.1 | not required |
| 53 | yes | TEXT_OK | Progetto Inizio funzionamento Hardware Software AFIS Statistiche - 1994 - 1995 - | §10, §10.1 + PH | not required |
| 54 | yes | TEXT_OK | Impronte acquisite con un rapporto 1:1, ad una risoluzione di 500dpi, in scala d | §10, §10.1 | not required |
| 55 | yes | TEXT_OK | Tecnologia sul mercato (1) - Sistema della ditta M2SYS - AFIS / ABIS (Automated  | §10, §10.2 | not required |
| 56 | yes | TEXT_OK | Tecnologia sul mercato (2) - Sistema della ditta DERMALOG - AFIS / ABIS (Automat | §10, §10.2 | not required |
| 57 | yes | TEXT_OK | Es. Tecnologia SDK della Innovatrics - Alte prestazioni, integrabilità, standard | §10, §10.2 | not required |
| 58 | yes | TEXT_OK | Trend nelle applicazioni Dispositivi embedded Mobile Contactless Servers | §10, §10.3 | not required |
| 59 | yes | TEXT_OK | In sintesi FINE Impronte a contatto – Biologia e caratteristiche – Tipi di senso | §12 | not required |

**Totals:** 59/59 pages accounted for · 35 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione04 — DONE

**Lesson file(s):** `Lezione04/L04 - Convoluzione e riconoscimento dell'iride.md`

### `Lezione_04_Convoluzione_Approfondimento_Iride.pdf` (53 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Lezione 04 Convoluzione Principali tratti monomodali: iride Prima breve simulazi | §0 | not required |
| 3 | yes | TEXT_OK | TEORIA Convoluzione Una delle basi del signal & image processing, ma anche del d | §1 | not required |
| 4 | yes | LIKELY_VISUAL | Uno dei sistemi di miglioramento delle immagini più diffuso… | §1 + PH | not required |
| 5 | yes | TEXT_OK | Persino nelle CNN, le reti di deep learning più famose (la C sta per convoluzion | §1 | not required |
| 6 | yes | LIKELY_VISUAL | Filtraggio immagini = convoluzione 𝑘𝑥, 𝑦 I 𝑥, 𝑦 𝑂𝑥, 𝑦= 𝐼𝑥, 𝑦∗𝑘𝑥, 𝑦 O 𝑥, 𝑦 | §2, §2.1 + PH | not required |
| 7 | yes | FORMULA_AMBIGUOUS | Equazione della convoluzione digitale nelle immagini 𝑂[𝑥, 𝑦] = ෍ 𝑟=1 𝑁𝑘 ෍ 𝑠=1 𝑀𝑘 | §2, §2.2 + PH | not required |
| 8 | yes | LIKELY_VISUAL | Alcuni semplici esempi di kernel 3x3 Original image Original image | §2, §2.3 + PH | not required |
| 9 | yes | LIKELY_VISUAL | Iride L4 + Nuovi Argomenti | §3, §3.1 | not required |
| 10 | yes | TEXT_OK | Panoramica della applicazione - Considerato come il tratto biometrico più accura | §3, §3.1 | not required |
| 11 | yes | LIKELY_VISUAL | Potente e impiegabile dove altri tratti non si possono usare (contactless) | §3, §3.1 + PH | not required |
| 12 | yes | TEXT_OK | Esempio di risultati ottenibili (irisguard) - Esaminiamo e commentiamo insieme i | §3, §3.1 | not required |
| 13 | yes | TEXT_OK | Altri vantaggi - L'acquisizione avviene senza contatto - Contiene molte caratter | §3, §3.2 | not required |
| 14 | yes | TEXT_OK | Svantaggi - Difficile acquisire un target sempre in movimento come l'iride largo | §3, §3.2 | not required |
| 15 | yes | TEXT_OK | Unicità dell'iride - Esattamente come per il caso delle impronte, non esistono d | §3, §3.3 | not required |
| 16 | yes | LIKELY_VISUAL | Alcuni fattori che aumentano la variabilità intraclasse Rotazioni della testa Va | §3, §3.3 + PH | not required |
| 17 | yes | TEXT_OK | Quale luce per acquisire l'iride? - Le feature che maggiormente sono interessant | §4, §4.1 | not required |
| 18 | yes | TEXT_OK | Quale sensore per acquisire l'iride? - Per catturare i complessi dettagli dell'i | §4, §4.2 | not required |
| 19 | yes | TEXT_OK | Sensori commerciali sul mercato - Sono presenti sistemi con grandi differenze di | §4, §4.3 | not required |
| 20 | title only | LIKELY_VISUAL | Altri dispositivi | §4, §4.3 + PH | not required |
| 21 | yes | TEXT_OK | Mondo mobile Galaxy Note7, S8-9, Iris scanner La tecnologia di scansione iridea  | §4, §4.3 | not required |
| 22 | yes | TEXT_OK | Un esempio come ATM - Il gruppo bancario americano Citigroup in collaborazione c | §4, §4.3 | not required |
| 23 | yes | TEXT_OK | Struttura dei moduli secondo Dougman Acquisition Module Feature Extraction Modul | §5, §5.1 + PH | not required |
| 24 | yes | TEXT_OK | La sequenza dei passi principali Passi che ci permettono di passare da una immag | §5, §5.2 | not required |
| 25 | yes | TEXT_OK | Come trovare l'iride (1) - Approfondiamo l'algoritmo per il rilevamento robusto  | §5, §5.3 + PH | not required |
| 26 | yes | TEXT_OK | Come trovare l'iride (2) - Gσ (r) rappresenta un prefiltraggio dell'immagine pas | §5, §5.3 | not required |
| 27 | yes | TEXT_OK | Rimozione di palpebre e ciglia - Solo una parte dell'iride è utile al riconoscim | §5, §5.4 + PH | not required |
| 28 | yes | LIKELY_VISUAL | Attacco sensore iride Samsung S8 Mostrare il foglio con la lente al cellulare… d | §5, §5.5 + PH | not required |
| 29 | yes | TEXT_OK | Proprietà dell'iris code (2) - L'unica informazione estratta dall'iride con l'Ir | §5, §5.8 | not required |
| 30 | yes | TEXT_OK | Linearizzazione dell'iride - Individuati raggi e centri si procede alla fase di  | §5, §5.6 + PH | not required |
| 31 | yes | TEXT_OK | Calcolo dell'iris code (1) - Avendo l'iride linearizzata I espressa nelle coordi | §5, §5.7 + PH | not required |
| 32 | yes | TEXT_OK | Calcolo dell'iris code (2) "The detailed iris pattern is encoded into a 256-byte | §5, §5.7 + PH | not required |
| 33 | yes | FORMULA_AMBIGUOUS | Algoritmi di matching per gli Iriscode (1) - La comparazione è effettuata fra Ir | §5, §5.9 | not required |
| 34 | yes | TEXT_OK | Creazione delle maschere Se vi sono occlusioni dell'iride (palpebre, riflessi, c | §5, §5.9 + PH | not required |
| 35 | yes | FORMULA_AMBIGUOUS | Uso delle maschere - Se vi sono occlusioni dell'iride (palpebre, riflessi, cigli | §5, §5.9 | not required |
| 36 | yes | TEXT_OK | Distribuzioni - Mostriamo la distribuzione del match score tipica dell'Iriscode  | §6, §6.1 + PH | not required |
| 37 | yes | TEXT_OK | Perché le distribuzioni reali non sono simmetriche? - Si nota che la distribuzio | §6, §6.1 + PH | not required |
| 38 | yes | TEXT_OK | APPLICAZIONI Iride nel visibile e problemi di privacy relativi L'estensione del  | §6, §7, §7.1 | not required |
| 39 | yes | TEXT_OK | Quanti bit significativi nell'Iriscode? DIPENDE! - Ipotesi di partenza: iriscode | §6, §6.2 + PH | not required |
| 40 | yes | TEXT_OK | Iride nel visibile e con luce ambientale Possibile, ma è da affrontare una maggi | §7, §7.1 | not required |
| 41 | yes | LIKELY_VISUAL | Iride nel visibile (2) Vi sono molte feature sfruttabili NIR NIR: meno dettagli  | §7, §7.1 + PH | not required |
| 42 | yes | TEXT_OK | Iride nel visibile: occhi scuri - Gli occhi scuri nel VIS sono più problematici  | §7, §7.1 | not required |
| 43 | yes | TEXT_OK | Watch list - Se i problemi delle carte di credito rubate e delle frodi legate al | §7, §7.2 | not required |
| 44 | yes | TEXT_OK | Riconoscimento di Sharbat Gula mediante iride Note di John Daugman: https://www. | §7, §7.3 | not required |
| 45 | yes | LIKELY_VISUAL | Riconoscimento di Sharbat Gula mediante iride Note di John Daugman: https://www. | §7, §7.3 + PH | not required |
| 46 | yes | LIKELY_VISUAL | Riconoscimento di Sharbat Gula (2) Note di John Daugman: https://www.cl.cam.ac.u | §7, §7.3 + PH | not required |
| 47 | yes | TEXT_OK | Sharbat Gula: mathcing - Facendo lo XOR fra l'iris code vecchio ed il nuovo ha o | §7, §7.3 | not required |
| 48 | yes | TEXT_OK | Cosa vuole dire avere un matching a 0.24? Corretta interpretazione del risultato | §7, §7.3, §7.4 | not required |
| 49 | yes | TEXT_OK | Sharbat Gula: iris codes Focus quality = 97 Percent of iris area visible between | §7, §7.3 + PH | not required |
| 50 | yes | TEXT_OK | Problemi di privacy? - L'exploit di Daugman (riconoscimento con iride da foto ad | §7, §7.5 | not required |
| 51 | yes | TEXT_OK | Altri monomodali che verranno poi approfonditi - Per la loro particolare rilevan | §8 | not required |
| 52 | yes | TEXT_OK | Inizio della breve simulazione di esame inerente al crash course - NOTA: durante | §8 | not required |
| 53 | yes | TEXT_OK | In sintesi FINE Abbiamo visto per la biometria dell'iride: – Panoramica e fisiol | §8, §10 | not required |

**Totals:** 53/53 pages accounted for · 22 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione05 — DONE

**Lesson file(s):** `Lezione05/L05 - Apprendimento induttivo automatico per applicazioni biometriche.md`

### `Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf` (68 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | L5 Apprendimento induttivo per applicazioni biometriche Fabio Scotti fabio.scott | §0 | not required |
| 3 | yes | TEXT_OK | Outline - Primitive biometriche viste come blocchi di ML di classificazione e re | §0 | not required |
| 4 | yes | TEXT_OK | Conoscenza nei sistemi biometrici Aspetti multi- disciplinari Pattern matching A | §0 | not required |
| 5 | yes | LIKELY_VISUAL | DL < ML < AI | §1, §1.1 + PH | not required |
| 6 | yes | TEXT_OK | Applicazione della AI nella Computer Science - Biometrics - Web search - Computa | §1, §1.2 | not required |
| 7 | yes | TEXT_OK | Esempi di task risolti con AI in Computer Science - Optical character recognitio | §1, §1.2 | not required |
| 8 | yes | TEXT_OK | Usabilità-HMI con ML Neuralink E. Musk (and years) Continuous biometric profilin | §1, §1.2 | not required |
| 9 | yes | LIKELY_VISUAL | A cosa serve il Machine Learning in biometria Vedere il sistema biometrico come  | §2, §2.1 | not required |
| 10 | yes | TEXT_OK | Chiudiamo in un blocco il SB DataBase Acquisition Feature Extraction Coding Matc | §2, §2.1 | not required |
| 11 | yes | FORMULA_AMBIGUOUS | Il sistema biometrico è un classificatore - Multiclasse = Identificatore - Binar | §2, §2.2 | not required |
| 12 | yes | FORMULA_AMBIGUOUS | Il sistema biometrico è un classificatore I classificatori sono il modello più c | §2, §2.2 | not required |
| 13 | yes | FORMULA_AMBIGUOUS | Esempio di primitive biometriche (classificatori): Face detection + Recognition  | §3 + PH | not required |
| 14 | yes | TEXT_OK | Esempio di primitive biometriche (classificatori): Fingerprint Indexing Quale ti | §3 | not required |
| 15 | yes | TEXT_OK | Acquisition Module Feature Extraction Module Template Trait Quality Checker Samp | §3 | not required |
| 16 | yes | TEXT_OK | Esempio di primitive biometriche (classificatori): Controllo qualità impronte ?  | §3 | not required |
| 17 | yes | TEXT_OK | Esempio di primitive biometriche (classificatori): Controllo qualità volti (ICAO | §3 | not required |
| 18 | yes | TEXT_OK | Esempio di primitive biometriche (classificatori): Antispoofing - Es.: Synaptics | §3 + PH | not required |
| 19 | yes | LIKELY_VISUAL | Fondamenti di Machine Learning (ML) Cosa significa allenare un modello | §4 | not required |
| 20 | yes | TEXT_OK | Obiettivo del ML in biometria - Immagini - 2D - 3dD - Segnali - Dati anagrafici  | §4, §4.1 | not required |
| 21 | title only | LIKELY_VISUAL | If … then… | §4, §4.2 | not required |
| 22 | yes | FORMULA_AMBIGUOUS | Height (cm) Weight (Kg) Esempio: Classificatore Cane/Gatto - Classical programmi | §4, §4.2 + PH | not required |
| 23 | yes | TEXT_OK | ML in una metafora E' simile al giardinaggio - Semi = Algoritmi - Nutrienti = Da | §4, §4.3 | not required |
| 24 | yes | TEXT_OK | Esempio: Reti Neurali Artific. Senza perdita di generalità ci riferiremo nel cor | §4, §4.4 | not required |
| 25 | yes | TEXT_OK | Machine Learning: 3 component principali - Decine di migliaia di algoritmi di ML | §5 | not required |
| 26 | yes | TEXT_OK | Representation - La Rappresentazione nel ML rappresenta l'insieme di tutti i mod | §5, §5.1 | not required |
| 27 | yes | TEXT_OK | Representation - Decision trees - Sets of rules - Bayes/Markov nets - Neural net | §5, §5.1 + PH | not required |
| 28 | yes | TEXT_OK | Evaluation - "Come giudichi o preferisci un modello rispetto a un altro" - Defin | §5, §5.2 + PH | not required |
| 29 | yes | TEXT_OK | Evaluation - Accuracy - Precision and recall - Squared error - Likelihood - Post | §5, §5.2 | not required |
| 30 | yes | TEXT_OK | Optimization - "Come esplori lo spazio dei modelli rappresentati per ottenere va | §5, §5.3 | not required |
| 31 | yes | TEXT_OK | Optimization - Combinatorial optimization E.g.: Greedy search - Convex optimizat | §5, §5.3 | not required |
| 32 | yes | TEXT_OK | Machine learning: learning types and models Apprendimento induttivo e deduttivo, | §6, §6.1 | not required |
| 33 | yes | TEXT_OK | Deductive VS Inductive Teoria Un modello generale che spiega un insieme di fenom | §6, §6.1 | not required |
| 34 | yes | TEXT_OK | Deductive VS Inductive: esercizio Quale modalità sto applicando? Sulla base di q | §6, §6.1 | not required |
| 35 | yes | TEXT_OK | Deductive VS Inductive: esercizio Quale modalità sto applicando? Sulla base di q | §6, §6.1 | not required |
| 36 | yes | TEXT_OK | Deductive VS Inductive: esercizio Quale modalità sto applicando? Nelle settimane | §6, §6.1 | not required |
| 37 | yes | TEXT_OK | Deductive VS Inductive: esercizio Quale modalità sto applicando? Nelle settimane | §6, §6.1 | not required |
| 38 | yes | TEXT_OK | Transduction… - Induzione: Si tratta di generalizzare da un insieme di esempi sp | §6, §6.2 | not required |
| 39 | yes | TEXT_OK | Test ChatGPT in quale funzione rientra? | §6, §6.2 | not required |
| 40 | yes | TEXT_OK | Test ChatGPT in quale funzione rientra? - ChatGPT come tutti i Large Linguistic  | §6, §6.2 | not required |
| 41 | yes | TEXT_OK | Types of Learning - Dati - X un vettore di valori dati (numeri, feature, immagin | §6, §6.3 | not required |
| 42 | yes | LIKELY_VISUAL | Types of Learning (II) | §6, §6.3 + PH | not required |
| 43 | yes | LIKELY_VISUAL | Tassonomia del ML | §6, §6.3 + PH | not required |
| 44 | yes | TEXT_OK | Height (cm) Weight (Kg) Inductive Learning Dati esempi di ingressi X e uscite Y  | §6, §6.4 | not required |
| 45 | yes | TEXT_OK | Perchè l'apprendimento induttivo va bene in biometria? - Mitiga il collo di bott | §6, §6.5 | not required |
| 46 | yes | TEXT_OK | Perché è raro usare metodi deduttivi in biometria? E' più probabile avere tanti  | §6, §6.5 | not required |
| 47 | yes | TEXT_OK | Reinforcement Learning (RL) - I modelli di apprendimento per rinforzo utilizzano | §6, §6.6 | not required |
| 48 | yes | TEXT_OK | Reinforcement Learning (II) Obiettivo: ottenere quante più ricompense possibile! | §6, §6.6 | not required |
| 49 | yes | TEXT_OK | Esempio di Reinforcement Learning nelle impronte - Trovare le minutiae (fine rig | §6, §6.6 + PH | not required |
| 50 | yes | LIKELY_VISUAL | Principali modelli di Machine Learning Usabili in biometria Es: matlab | §6, §6.7 | not required |
| 51 | yes | LIKELY_VISUAL | Principali modelli di Machine Learning Usati in biometria Più usati | §6, §6.7 + PH | not required |
| 52 | yes | TEXT_OK | Classificazione in base agli I/O Age estimation, Soft biometrics, … General… Dat | §6, §6.7 + PH | not required |
| 53 | yes | LIKELY_VISUAL | Transfer Learning Come trasferire conoscenza fra domini applicativi | §7 | not required |
| 54 | yes | TEXT_OK | Transfer Learning - Machine Learning's Next Frontier - Ulteriore miglioramento e | §7 | not required |
| 55 | yes | LIKELY_VISUAL | Applicazioni del Transfer Learning: imparare da simulazioni | §7 + PH | not required |
| 56 | yes | TEXT_OK | Applicazioni del Transfer Learning: trasferire il dominio Testi ufficiali Testi  | §7 | not required |
| 57 | yes | LIKELY_VISUAL | Applicazione del transfer learning con CNN - Pre-trained Convolutional Neural Ne | §7 + PH | not required |
| 58 | yes | LIKELY_VISUAL | Preparazione dei dati e verifica dell'apprendimento Data augmentation → LOPO → s | §8, §8.1 | not required |
| 59 | yes | LIKELY_VISUAL | Dati/Prestazioni: Classical ML vs DeepLearning Il DL ha bisogno di molti dati….  | §8, §8.1 + PH | not required |
| 60 | yes | TEXT_OK | Data augmentation - YOUR ALLAY TO OVERCOME OVERFITTING PROBLEMS! Please keep in  | §8, §8.2 | not required |
| 61 | yes | TEXT_OK | Data augmentation #1: rotations, translations, scales Same tennisball input imag | §8, §8.2 | not required |
| 62 | yes | TEXT_OK | Data augmentation #2: with noise/noises Noise of different types (gaussian, addi | §8, §8.2 | not required |
| 63 | yes | TEXT_OK | Data augmentation #3: with different (simulated) conditions (even for big datase | §8, §8.2 + PH | not required |
| 64 | yes | LIKELY_VISUAL | 3D Face Tracking and Texture Fusion in the Wild Esempio di approccio real-time p | §8, §8.2 + PH | not required |
| 65 | yes | TEXT_OK | Leave One Person Out (LOPO) - L'apprendimento di una rete neurale a molti livell | §9, §9.1 | not required |
| 66 | yes | TEXT_OK | LOPO L(NP)O MIX Ora è più difficile che impari a riconoscere solo dal colore dei | §9, §9.2 | not required |
| 67 | yes | TEXT_OK | L(1)PO = L One P O (esempio semplificato) 1) Creiamo un DB di coppie input/outpu | §9, §9.3 + PH | not required |
| 68 | yes | TEXT_OK | Punti principali visti - Primitive biometriche viste come blocchi di ML di class | §11 | not required |

**Totals:** 68/68 pages accounted for · 17 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione06 — DONE

**Lesson file(s):** `Lezione06/L06 - Progettazione di modelli di machine learning per applicazioni biometriche.md`

### `Lezione_06_Deeplearning_Network_Matlab_toolobox_examples_DISPENSA.pdf` (15 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | title only | LIKELY_VISUAL | Introducing Deep Learning with MATLAB | §12, §12.1 | not required |
| 2 | yes | TEXT_OK | Deep learning is a type of machine learning in which a model learns to perform c | §12, §12.1 | not required |
| 3 | yes | TEXT_OK | Introducing Deep Learning with MATLAB Here are just a few examples of deep learn | §12, §12.1 | not required |
| 4 | yes | TEXT_OK | Introducing Deep Learning with MATLAB UCLA researchers built an advanced microsc | §12, §12.1 | not required |
| 5 | yes | TEXT_OK | Introducing Deep Learning with MATLAB What Makes Deep Learning State-of-the-Art? | §12, §12.1 | not required |
| 6 | yes | TEXT_OK | Introducing Deep Learning with MATLAB Inside a Deep Neural Network A deep neural | §12, §12.2 | not required |
| 7 | yes | TEXT_OK | Introducing Deep Learning with MATLAB How A Deep Neural Network Learns Let's say | §12, §12.2 | not required |
| 8 | yes | TEXT_OK | Introducing Deep Learning with MATLAB About Convolutional Neural Networks A conv | §12, §12.2 + PH | not required |
| 9 | yes | TEXT_OK | Introducing Deep Learning with MATLAB About Convolutional Neural Networks contin | §12, §12.2 | not required |
| 10 | yes | TEXT_OK | Introducing Deep Learning with MATLAB What is the Difference Between Deep Learni | §12, §12.3 + PH | not required |
| 11 | yes | TEXT_OK | Introducing Deep Learning with MATLAB Getting Started with Deep Learning If you' | §12, §12.4 | not required |
| 12 | yes | TEXT_OK | An Example Using AlexNet You can use AlexNet to classify objects in any image. I | §12, §12.4 | not required |
| 13 | yes | TEXT_OK | Introducing Deep Learning with MATLAB Retraining an Existing Network In the prev | §12, §12.4 | not required |
| 14 | yes | TEXT_OK | Introducing Deep Learning with MATLAB Computational Resources for Deep Learning  | §12, §12.5 | not required |
| 15 | yes | TEXT_OK | © 2017 The MathWorks, Inc. MATLAB and Simulink are registered trademarks of The  | §12, §12.5 | not required |

### `Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf` (71 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | LIKELY_VISUAL | Progettazione di modelli di Machine Learning per applicazioni biometriche Fabio  | §0 | not required |
| 3 | yes | TEXT_OK | Outline 1. Progettazione di modelli di Machine Learning 1. Flusso di lavoro 2. F | §0 | not required |
| 4 | yes | LIKELY_VISUAL | Design of machine learning models Suggerimenti per migliorare la progettazione | §0 | not required |
| 5 | yes | TEXT_OK | Premio Nobel della fisica John Hopfield e Geoffrey Hinton Per le scoperte e inve | §1 + PH | not required |
| 6 | yes | TEXT_OK | Premio Nobel della fisica John Hopfield e Geoffrey Hinton - Per le scoperte e in | §1 + PH | not required |
| 7 | yes | LIKELY_VISUAL | ML workflow Modello configurato (Algoritmo) | §2, §2.1 + PH | not required |
| 8 | yes | TEXT_OK | ML in Practice - Comprensione del dominio, conoscenze pregresse e obiettivi - In | §2, §2.1 | not required |
| 9 | yes | LIKELY_VISUAL | Ci sono ambienti completi per tutti i passi 1-5 | §2, §2.1 + PH | not required |
| 10 | yes | TEXT_OK | La tecnica di ML giusta - La selezione della giusta tecnica di ML richiede una c | §2, §2.2 | not required |
| 11 | yes | TEXT_OK | Selezione della tecnica di ML - Studia i requisiti del compito - Valuta quale te | §2, §2.2 | not required |
| 12 | yes | TEXT_OK | Features del modello da considerare - Accuracy - Explainability - Response speed | §2, §2.3 | not required |
| 13 | yes | TEXT_OK | Selezione della tecnica "migliore" - Un dato problema può essere risolto in molt | §2, §2.4 | not required |
| 14 | yes | TEXT_OK | Effetti del rasoio di Occam 1. Per non perdere tempo - Mentale - Di elaborazione | §2, §2.4 | not required |
| 15 | yes | TEXT_OK | Usare il ML in biometria Due possibilità 1. Usare modelli/reti già allenati per  | §3, §3.1 | not required |
| 16 | yes | TEXT_OK | Usare il ML in biometria: 4 punti importanti 1. Seguire un corretto flusso di la | §3, §3.2 | not required |
| 17 | yes | TEXT_OK | 1) Procedere ad progettazione →Seguire il workflow Non si saltano passaggi o si  | §3, §3.2 | not required |
| 18 | yes | TEXT_OK | 2) Procedere ad progettazione Usare L(NP)O (se possibile) MIX Forziamo il modell | §3, §3.2 | not required |
| 19 | yes | TEXT_OK | 3) Procedura di progettazione Preferire soluzioni semplici - Vedi rasoio di OCCA | §3, §3.2 | not required |
| 20 | yes | TEXT_OK | 4) Procedura di progettazione Considerare tecniche di Transfer Learning Fine tun | §3, §3.2 | not required |
| 21 | yes | LIKELY_VISUAL | I 3 principali drivers in AI and Algorithms Specialmente per il Deep Learning | §3, §3.3 + PH | not required |
| 22 | yes | TEXT_OK | Ma il 4° driver è il più importante - Il cervello del designer! - A priori knowl | §3, §3.3 | not required |
| 23 | yes | LIKELY_VISUAL | Principali modelli induttivi per il ML Applicazioni biometriche (non solo reti n | §4 | not required |
| 24 | yes | TEXT_OK | Computational intelligence - Secondo la IEEE computational intelligence society  | §4 | not required |
| 25 | yes | TEXT_OK | Computational Intelligence (CI) main techniques Neural Networks - Deep learning  | §4 | not required |
| 26 | yes | LIKELY_VISUAL | Artificial neural networks Uno dei modelli di ML più applicato in biometria | §5, §5.1 | not required |
| 27 | yes | LIKELY_VISUAL | Applicazioni Tipiche Behavior prediction Age, weight Soft biometrics Biometric p | §5, §5.1 + PH | not required |
| 28 | yes | LIKELY_VISUAL | Neural Networks - L'ispirazione originale era il cervello umano; l'enfasi ora è  | §5, §5.2 | not required |
| 29 | yes | TEXT_OK | Parallelismo (II) Biological Neural Network Artificial Neural Network Soma Neuro | §5, §5.2 + PH | not required |
| 30 | yes | TEXT_OK | Neural Networks Feed-forward Network: - Una struttura a strati composta da un nu | §5, §5.2 | not required |
| 31 | yes | LIKELY_VISUAL | Diagramma di un neurone Neuron Y Input Signals x1 x2 xn Output Signals Y Y Y w2  | §5, §5.3 + PH | not required |
| 32 | yes | TEXT_OK | - Il neurone calcola la somma ponderata dei segnali in ingresso e confronta il r | §5, §5.3 | not required |
| 33 | yes | TEXT_OK | Threshold Inputs x1 x2 Output Y  Hard Limiter w2 w1 Linear Combiner  Single-la | §5, §5.3 + PH | not required |
| 34 | yes | LIKELY_VISUAL | Activation functions Ogni tipo di funzione di attivazione può modificare il comp | §5, §5.4 + PH | not required |
| 35 | yes | TEXT_OK | Multilayer perceptron with two hidden layers Input layer First hidden layer Seco | §5, §5.5 + PH | not required |
| 36 | yes | TEXT_OK | Neural Networks training - Durante l'addestramento, la Rete Neurale è costretta  | §5, §5.5 | not required |
| 37 | yes | LIKELY_VISUAL | Neural Networks training (2) | §5, §5.5 + PH | not required |
| 38 | yes | TEXT_OK | Curse of Dimensionality Figure Bishop 1.21 Premessa: →avere in ingresso immagini | §6 + PH | not required |
| 39 | yes | LIKELY_VISUAL | Metodi tradizionali: K-nearest neighbors Induttivi ma non CI | §7, §7.1 | not required |
| 40 | yes | TEXT_OK | Nearest Neighbor Classifier Basic idea: If it walks like a duck, quacks like a d | §7, §7.1 + PH | not required |
| 41 | yes | TEXT_OK | Nearest-Neighbor Classifiers Richiede 3 cose: - L'insieme dei record memorizzati | §7, §7.1 | not required |
| 42 | yes | TEXT_OK | Definizione di primo vicino X X X (a) 1-nearest neighbor (b) 2-nearest neighbor  | §7, §7.1 + PH | not required |
| 43 | yes | FORMULA_AMBIGUOUS | Nearest Neighbor Classification 1) Calcola la distanza tra due punti: Esempio: D | §7, §7.2 | not required |
| 44 | yes | TEXT_OK | Nearest Neighbor Classification… Scegliere il valore di k: - Se k è troppo picco | §7, §7.3 + PH | not required |
| 45 | yes | FORMULA_AMBIGUOUS | kNN: problema con le scale degli ingressi Problemi di scalabilità: - Gli attribu | §7, §7.4 | not required |
| 46 | yes | TEXT_OK | kNN: sono lazy learners (pigri!) - k-NN - Non creano un modello esplicito - Clas | §7, §7.5 | not required |
| 47 | yes | TEXT_OK | Overfitting e Underfitting - k troppo piccolo ➔ overfitting - k troppo grande ➔  | §7, §7.3 + PH | not required |
| 48 | yes | TEXT_OK | Metodi classici: Decision tree Induttivi ma non CI | §8 | not required |
| 49 | yes | LIKELY_VISUAL | ALBERI DECISIONALI Sono strutture di IF con soglie | §8 + PH | not required |
| 50 | yes | TEXT_OK | Al termine dell'apprendimento - Indipendentemente dal modello di ML scelto, al t | §9 | not required |
| 51 | yes | LIKELY_VISUAL | Feature di diverso livello Cosa serve per la parte iniziale di un processo di ma | §10, §10.1 | not required |
| 52 | yes | LIKELY_VISUAL | ML vs Deep Learning | §10, §10.1 + PH | not required |
| 53 | yes | TEXT_OK | Focalizziamoci su ML Trad. - Vediamo come lavora il designer della applicazione  | §10, §10.1 | not required |
| 54 | yes | TEXT_OK | Human Designer Feature extraction Adapted from © James Hays Low Level Features M | §10, §10.2 | not required |
| 55 | yes | TEXT_OK | SIFT Spin image (Kernels) Textons (Kernels) SURF, MSER, LBP, Color-SIFT, Color h | §10, §10.2 + PH | not required |
| 56 | yes | TEXT_OK | - Mid-level features Continuation Parallelism Junctions Corners "Tokens" from Vi | §10, §10.2 + PH | not required |
| 57 | yes | LIKELY_VISUAL | Deep learning Introduzione | §11, §11.1 | not required |
| 58 | yes | LIKELY_VISUAL | DL < ML < AI (richiamo della lezione precedente) | §11, §11.1 | not required |
| 59 | yes | TEXT_OK | Il deeplearn non è 1 solo metodo.. Ma indica una classe di algoritmi 1. usano va | §11, §11.1 | not required |
| 60 | yes | TEXT_OK | 3. apprendimento di livelli gerarchici multipli di caratteristiche (e di rappres | §11, §11.1 | not required |
| 61 | yes | LIKELY_VISUAL | Shallow and Deep Learning networks | §11, §11.2 + PH | not required |
| 62 | yes | TEXT_OK | In questo esempio di classificazione di immagini le rete impara ad estrarre le f | §11, §11.2 | not required |
| 63 | yes | TEXT_OK | Feature representation Input data 1st layer "Edges" 2nd layer "Object parts" 3rd | §11, §11.2 + PH | not required |
| 64 | yes | TEXT_OK | Apprendimento nel caso di Deep Learning NVIDIA Deep Learning Course Il concetto  | §11, §11.3 | not required |
| 65 | yes | TEXT_OK | Quando si parla di Deep Learning? NVIDIA Deep Learning Course In modo generale d | §11, §11.3 + PH | not required |
| 66 | yes | TEXT_OK | - Di solito è migliore quando lo spazio di input è strutturato localmente, sia s | §11, §11.3 + PH | not required |
| 67 | yes | TEXT_OK | - Plausibilità biologica – per esempio la corteccia visiva - Noi già elaboriamo  | §11, §11.4 | not required |
| 68 | yes | TEXT_OK | - Le sotto-caratteristiche create in un'architettura profonda possono potenzialm | §11, §11.4 | not required |
| 69 | yes | TEXT_OK | Cosa succede a una rete neurale Mutli-Layer Perceptron (MLP) o similare con molt | §11, §11.5 | not required |
| 70 | yes | TEXT_OK | - Hardware - L'avvento delle unità di elaborazione grafica (GPU) ad alte prestaz | §11, §11.5 | not required |
| 71 | yes | TEXT_OK | Punti principali visti 1. Progettazione di modelli di Machine Learning 1. Flusso | §14 | not required |

**Totals:** 86/86 pages accounted for · 27 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione07 — DONE

**Lesson file(s):** `Lezione07/L07 - Deep learning e CNN per applicazioni biometriche.md`

### `Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf` (82 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Lezione Deep learning per applicazioni biometriche Fabio Scotti fabio.scotti@uni | §0 | not required |
| 3 | yes | TEXT_OK | Outline 1. Deep learning VS classical neural networks 2. Convolutional neural ne | §0 | not required |
| 4 | yes | TEXT_OK | Riassumendo 3 concetti fondamentali del ML per la biometria - E_train →0! I mode | §0 | not required |
| 5 | yes | TEXT_OK | Convolutional Neural Networks (CNNs) | §1 | not required |
| 6 | yes | TEXT_OK | Come useremo le CNN nel resto del corso? - Prima capiamo - come sono fatte le CN | §1 | not required |
| 7 | yes | TEXT_OK | Partiamo da una piccola rete totalmente connessa - Sappiamo che è utile partire  | §2 | not required |
| 8 | yes | TEXT_OK | Caso applicativo: classificare una imagine - Processare una immagine con una ret | §2 + PH | not required |
| 9 | yes | TEXT_OK | Lo stesso modello appare in diversi luoghi: può essere compresso creare una rete | §2 + PH | not required |
| 10 | yes | TEXT_OK | IS4 Intelligent systems for industry, supply chain and environment - Per chi è i | §1 | not required |
| 11 | yes | TEXT_OK | Strati convoluzionali Filtro Una CNN è una rete neurale con alcuni livelli convo | §3, §3.1 | not required |
| 12 | yes | TEXT_OK | Convolution kernels da imparare 6 x 6 input image -1 -1 -1 -1 -1 -1 Filter 1 -1  | §3, §3.1 + PH | not required |
| 13 | yes | TEXT_OK | Ripasso della convoluzione 6 x 6 input image -1 -1 -1 -1 -1 -1 Filter 1 -1 strid | §3, §3.2 | not required |
| 14 | yes | TEXT_OK | Ripasso della convoluzione 6 x 6 input image -1 -1 -1 -1 -1 -1 Filter 1 -3 If st | §3, §3.2 | not required |
| 15 | yes | TEXT_OK | Ripasso della convoluzione 6 x 6 image -1 -1 -1 -1 -1 -1 Filter 1 -1 -3 -1 -3 -3 | §3, §3.2 + PH | not required |
| 16 | yes | TEXT_OK | Strato della CNN 6 x 6 image -1 -3 -1 -3 -3 -3 -3 -2 -2 -1 -1 -1 -1 -1 -1 -1 Fil | §3, §3.2 + PH | not required |
| 17 | yes | TEXT_OK | Immagine a colori RGB 3 channels -1 -1 -1 -1 -1 -1 Filter 1 -1 -1 -1 -1 -1 -1 Fi | §3, §3.3 + PH | not required |
| 18 | yes | TEXT_OK | image convolution -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 1x 2x … … x … … Convolutio | §4 | not required |
| 19 | yes | TEXT_OK | 6 x 6 image -1 -1 -1 -1 -1 -1 Filter 1 … … … Only connect to 9 inputs, not fully | §4 + PH | not required |
| 20 | yes | TEXT_OK | -1 -1 -1 -1 -1 -1 Filter 1 1: 2: 3: … 7: 8: 9: … 13: 14: 15: … 4: 10: 16: -1 Sha | §4 + PH | not required |
| 21 | yes | LIKELY_VISUAL | ReLu Utile per creare gradiente nullo nella funzione di ottimizzazione dei pesi  | §5 + PH | not required |
| 22 | yes | LIKELY_VISUAL | Ecco la CNN! Fully Connected Feedforward network Face, body, dog, horse, Convolu | §6, §6.1 + PH | not required |
| 23 | yes | TEXT_OK | CNN → Fully connected network - La CNN fa quello che fa la Fully connected netw | §6, §6.1 | not required |
| 24 | yes | TEXT_OK | Perchè si adotta il "Pooling" - Il sottocampionamento dei pixel non cambierà l'o | §6, §6.2 | not required |
| 25 | yes | TEXT_OK | Come si realizza il pooling Layer 1 Layer 2 Layer 3 Simple Classifier Image/Vide | §6, §6.2 | not required |
| 26 | yes | TEXT_OK | Max Pooling 6 x 6 image -1 2 x 2 image Each filter is a channel New image but sm | §6, §6.2 + PH | not required |
| 27 | yes | TEXT_OK | - Il sottocampionamento (Pooling) permette di ridurre il numero di caratteristic | §6, §6.2 | not required |
| 28 | yes | LIKELY_VISUAL | Esempio di max pooling | §6, §6.2 + PH | not required |
| 29 | yes | TEXT_OK | La CNN completa Convolution Max Pooling Convolution Max Pooling Ripetere più vol | §6, §6.3 + PH | not required |
| 30 | yes | LIKELY_VISUAL | La CNN completa Fully Connected Feedforward network cat dog …… Convolution Max P | §6, §6.3 | not required |
| 31 | yes | LIKELY_VISUAL | Flattening -1 Flattened -1 Fully Connected Feedforward network | §6, §6.3 + PH | not required |
| 32 | yes | TEXT_OK | Only modified the network structure and input format (vector -> 3-D tensor) CNN  | §7 | not required |
| 33 | yes | FORMULA_AMBIGUOUS | Only modified the network structure and input format (vector -> 3-D array) CNN i | §7 + PH | not required |
| 34 | yes | TEXT_OK | Only modified the network structure and input format (vector -> 3-D array) CNN i | §7 | not required |
| 35 | yes | TEXT_OK | AlphaGo Neural Network (19 x 19 positions) Next move 19 x 19 matrix Black: 1 whi | §8 + PH | not required |
| 36 | yes | LIKELY_VISUAL | Allenare reti Deep Con metodi supervisionati e non supervisionati | §9, §9.1 | not required |
| 37 | yes | TEXT_OK | - La maggior parte dei pesi sono addestrati con la Retropropagazione (Backpropag | §9, §9.1 | not required |
| 38 | yes | TEXT_OK | Backpropagation - La backpropagation (abbreviazione di "backward propagation of  | §9, §9.1 | not required |
| 39 | yes | TEXT_OK | Limiti del gradiente nei primi strati delle reti deep - Nei primi strati della r | §9, §9.2 | not required |
| 40 | yes | LIKELY_VISUAL | Deep Net with Greedy Layer Wise Training Adapted from © Tony Martinez Risolverem | §10 + PH | not required |
| 41 | yes | TEXT_OK | Autoencoders (encode->decode) - Cercano di scoprire caratteristiche generiche de | §10 | not required |
| 42 | yes | TEXT_OK | - Stack many (sparse) auto-encoders in succession and train them using greedy la | §10 + PH | not required |
| 43 | yes | TEXT_OK | - Stack many (sparse) auto-encoders in succession and train them using greedy la | §10 | not required |
| 44 | yes | TEXT_OK | - Tendere sempre a ridurre il numero di neuroni procedendo con gli strati Stacke | §10 | not required |
| 45 | yes | TEXT_OK | - Do supervised training on the last layer using final features Stacked Auto-Enc | §10 | not required |
| 46 | yes | LIKELY_VISUAL | - Then do supervised training on the entire network to fine- tune all weights St | §10 | not required |
| 47 | yes | TEXT_OK | Riallenare con una back propagation sulla parte fully connected per cambiare out | §11, §11.1 | not required |
| 48 | yes | TEXT_OK | FINE TUNING partendo da una CNN pubblica Fully Connected Feedforward network Cla | §11, §11.1 + PH | not required |
| 49 | yes | TEXT_OK | Dal modello generico all'applicazione biometrica - CNN sono nate per classificaz | §11, §11.2 + PH | not required |
| 50 | yes | TEXT_OK | Perché non (sempre) alleniamo tutto da zero? - Dataset biometrici spesso limitat | §11, §11.2 | not required |
| 51 | yes | TEXT_OK | Ruolo delle CNN nel sistema biometrico - La CNN non è (tutto) il sistema biometr | §12, §12.1 | not required |
| 52 | yes | TEXT_OK | Ci sono rischi a usare modelli riallenati? - SI! Potrebbero esserci dei bias che | §12, §12.2 | not required |
| 53 | yes | TEXT_OK | Altri rischi a usare modelli riallenati? SI! - Scarsa separabilità inter-soggett | §12, §12.3 + PH | not required |
| 54 | yes | TEXT_OK | Cosa significa Leakage informativo - Se trovi in un file che dovrebbe essere usa | §12, §12.4 | not required |
| 55 | yes | TEXT_OK | Molto importante! Privacy →Vettori estratti - "Un buon template biometrico deve | §12, §12.4 | not required |
| 56 | yes | TEXT_OK | Dispensa introduttiva Per approfondire i temi sul Deep Learning presentati, si c | §13 | not required |
| 57 | yes | TEXT_OK | Direct image classification «Dog» No more information about the object Image cla | §13 + PH | not required |
| 58 | yes | TEXT_OK | Direct image classification uso classico in biometria - Molto veloce, per sistem | §13 | not required |
| 59 | yes | TEXT_OK | Object detection bounding box Output is the bounding box {x1, y1, x2, y2} (x1, y | §13 + PH | not required |
| 60 | yes | TEXT_OK | Object detection: bounding box - Realizzato con reti piu' grandi e quindi richie | §13 | not required |
| 61 | yes | TEXT_OK | Laboratorio Gli strumenti da usare | §14 | not required |
| 62 | yes | TEXT_OK | TOOLS - Alcune note solo per PREPARARE i toolbox per le prossime lezioni. Rivedr | §14 | not required |
| 63 | yes | TEXT_OK | TOOL#1: Matlab - Matlab è il primo strumento nel nostro corso. Non solo apprendi | §14 | not required |
| 64 | yes | TEXT_OK | TOOL#1: Matlab Tooboxes Installa tutte i toolbox, ma se hai poco spazio sul disc | §14 | not required |
| 65 | yes | TEXT_OK | TOOL#2: Google Colab COLAB = Colaboratory è un ambiente gratuito per notebook Ju | §14 | not required |
| 66 | yes | TEXT_OK | TOOL#2: Google Colab COLAB È molto, molto utile! Ci aiuta ad ottimizzare il temp | §14 | not required |
| 67 | yes | TEXT_OK | TOOL#3 (forse Colab è preferibile per noi) Uno strumento potente per il calcolo  | §14 | not required |
| 68 | yes | TEXT_OK | Anaconda Navigator L'applicazione desktop ti permette di gestire facilmente appl | §14 + PH | not required |
| 69 | yes | TEXT_OK | Jupyter Notebook | §14 | not required |
| 70 | yes | TEXT_OK | Jupyter Notebook and Colab Google Colab is ready and more hardware- independent  | §14 | not required |
| 71 | yes | LIKELY_VISUAL | Or use an IDE (Integrated Development Environment) | §14 | not required |
| 72 | yes | LIKELY_VISUAL | Caricare il file da elaborare | §14 | not required |
| 73 | yes | TEXT_OK | Modello di ML allenato (non deep): Haar cascade Il modello Haar Cascade di OpenC | §15, §15.1 | not required |
| 74 | yes | TEXT_OK | Aprire un nuovo blocco e scrivere/copiare il codice OpenCV è una libreria Intel  | §15 | not required |
| 75 | yes | FORMULA_AMBIGUOUS | # Step 3: Load Haar Cascade Classifier face_cascade = cv2.CascadeClassifier(cv2. | §15 | not required |
| 76 | yes | TEXT_OK | # Step 5: Perform Face Detection gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY) fa | §15 | not required |
| 77 | yes | LIKELY_VISUAL | Output face1.jpg | §15, §15.3 + PH | not required |
| 78 | yes | TEXT_OK | Output face2.jpg Nella semplicità del modello, pur non essendo stato allenato co | §15, §15.3 + PH | not required |
| 79 | yes | LIKELY_VISUAL | Output face3.jpg | §15, §15.3 + PH | not required |
| 80 | yes | TEXT_OK | Abbiamo visto - Struttura - Funzionamento degli strati convoluzionali - Apprendi | §15, §16 | not required |
| 81 | yes | TEXT_OK | Appendici con il codice Non richiesto all'esame | §15 | not required |
| 82 | yes | TEXT_OK | # Step 1: Install OpenCV (In Google Colab, run it as a shell command with !) !pi | §15 | not required |

### `Lezione_07_DeepLearning_per_biometria_DISPENSA_PAPER.pdf` (34 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey KALAIVANI SUNDARARAJAN and DAMON L. WOODA | §17, §17.1 | not required |
| 2 | yes | TEXT_OK | 65:2 K. Sundararajan and D. L. Woodard Biometrics deals with identifying people  | §17, §17.1, §17.2 | not required |
| 3 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:3 functions. Recently, deep learning h | §17, §17.2 | not required |
| 4 | yes | TEXT_OK | 65:4 K. Sundararajan and D. L. Woodard Fig. 1. Deep learning architectures. join | §17, §17.2 + PH | not required |
| 5 | yes | FORMULA_AMBIGUOUS | Deep Learning for Biometrics: A Survey 65:5 Perceptron (MLP), typically referred | §17, §17.2 | not required |
| 6 | yes | TEXT_OK | 65:6 K. Sundararajan and D. L. Woodard Fig. 2. Biometric modalities and correspo | §17, §17.2, §18, §18.1 + PH | not required |
| 7 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:7 person's face to his previously coll | §18, §18.1 | not required |
| 8 | yes | TEXT_OK | 65:8 K. Sundararajan and D. L. Woodard Table 1. Various CNN Architectures Used f | §18, §18.1 | not required |
| 9 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:9 can be used for face verification, i | §18, §18.1 | not required |
| 10 | yes | TEXT_OK | 65:10 K. Sundararajan and D. L. Woodard that retaining only 12% of parameters ca | §18, §18.1 | not required |
| 11 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:11 et al. [169] used an ensemble of 15 | §18, §18.1, §18.2 | not required |
| 12 | yes | TEXT_OK | 65:12 K. Sundararajan and D. L. Woodard matching include global features (loop,  | §18, §18.2 | not required |
| 13 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:13 failure-to-acquire rate. This invol | §18, §18.2, §18.3 | not required |
| 14 | yes | TEXT_OK | 65:14 K. Sundararajan and D. L. Woodard out-of-domain PLDA parameters were adapt | §18, §18.3 | not required |
| 15 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:15 3.7 Signature Signature is perhaps  | §18, §18.3 | not required |
| 16 | yes | TEXT_OK | 65:16 K. Sundararajan and D. L. Woodard Some approaches use pretrained or fine-t | §18, §18.3, §19, §19.1 | not required |
| 17 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:17 - ChaLearn 2015 [34]: This dataset  | §19, §19.1 | not required |
| 18 | yes | TEXT_OK | 65:18 K. Sundararajan and D. L. Woodard 4.1.6 Signature. - GPDS-960 corpus [146] | §19, §19.1, §19.2 | not required |
| 19 | yes | FORMULA_AMBIGUOUS | Deep Learning for Biometrics: A Survey 65:19 Table 2. Face Recognition Results:  | §19, §19.2 | not required |
| 20 | yes | TEXT_OK | 65:20 K. Sundararajan and D. L. Woodard Table 3. Face Recognition Results: Other | §19, §19.2 | not required |
| 21 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:21 Table 4. Age Estimation Results Tes | §19, §19.2 | not required |
| 22 | yes | TEXT_OK | 65:22 K. Sundararajan and D. L. Woodard Table 5. Gender and Ethnicity Estimation | §19, §19.2 | not required |
| 23 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:23 Table 8. Iris Recognition Results D | §19, §19.2 | not required |
| 24 | yes | TEXT_OK | 65:24 K. Sundararajan and D. L. Woodard Table 10. Signature Verification Results | §19, §19.2 | not required |
| 25 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:25 Table 12. Keystroke Recognition Res | §19, §19.2, §20 | not required |
| 26 | yes | TEXT_OK | 65:26 K. Sundararajan and D. L. Woodard 5.2 Real-World Applicability While deep  | §20 | not required |
| 27 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:27 few biometric modalities (e.g., fac | §20, §21 | not required |
| 28 | yes | TEXT_OK | 65:28 K. Sundararajan and D. L. Woodard [9] Yoshua Bengio. 2009. Learning deep a | §21 | not required |
| 29 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:29 [35] Jude Ezeobiejesi and Bir Bhanu | §21 | not required |
| 30 | yes | TEXT_OK | 65:30 K. Sundararajan and D. L. Woodard [59] Guosheng Hu, Yongxin Yang, Dong Yi, | §21 | not required |
| 31 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:31 [83] Hao Liu, Jiwen Lu, Jianjiang F | §21 | not required |
| 32 | yes | TEXT_OK | 65:32 K. Sundararajan and D. L. Woodard [108] N. Pattabhi Ramaiah, Earnest Paul  | §21 | not required |
| 33 | yes | TEXT_OK | Deep Learning for Biometrics: A Survey 65:33 [130] Pedro Silva, Eduardo Luz, Raf | §21 | not required |
| 34 | yes | TEXT_OK | 65:34 K. Sundararajan and D. L. Woodard [155] Thomas Wolf, Mohammadreza Babaee,  | §21 | not required |

**Totals:** 116/116 pages accounted for · 29 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione08 — DONE

**Lesson file(s):** `Lezione08/L08 - CNN per identificazione e riconoscimento, template update.md`

### `Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf` (77 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | PARTE A Deep learning HW e SW per applicazioni biometriche PARTE B Metodi avanza | §0 | not required |
| 3 | yes | TEXT_OK | Outline 1. HW e SW per deep learning 2. Esempi di CNN pubbliche utili 3. CNN in  | §0 | not required |
| 4 | yes | TEXT_OK | HW per deep learning Focus su applicazioni biometriche Jensen Huang unveils NVID | §1, §1.1 | not required |
| 5 | yes | TEXT_OK | Perchè il Machine Learning è difficile? - Archiviazione di Massa →necessità di d | §1, §1.1 | not required |
| 6 | yes | TEXT_OK | GPU vs CPU In breve…. Ti serve una GPU per i progetti impegnativi (10-20x di acc | §1, §1.1 + PH | not required |
| 7 | yes | TEXT_OK | CPU, GPU, TPU, NPU, VPU CPU Central Processing Unit GPU Graphics Processing Unit | §1, §1.2 + PH | not required |
| 8 | yes | TEXT_OK | CPU, GPU CPU (Central Processing Unit) General purpose, elevata flessibilità, ba | §1, §1.2 | not required |
| 9 | yes | TEXT_OK | TPU, NPU TPU (Tensor Processing Unit) Acceleratore specializzato per operazioni  | §1, §1.2 | not required |
| 10 | yes | TEXT_OK | VPU - VPU (Vision Processing Unit) - Acceleratore specializzato per elaborazione | §1, §1.2 | not required |
| 11 | yes | TEXT_OK | GPU e acceleratori: quale HW serve davvero? - Training di CNN biometriche (offli | §1, §1.3 | not required |
| 12 | yes | TEXT_OK | ML Server farm… - Amazon (AWS) - Google - Facebook - IBM (AzureML) - … In Intell | §1, §1.4 | not required |
| 13 | yes | LIKELY_VISUAL | GPUs: non servono per forza | §1, §1.4 | not required |
| 14 | yes | TEXT_OK | - Cosa si può fare con una Raspberry PI e una VPU come la Movidius? https://deve | §1, §1.4 + PH | not required |
| 15 | yes | TEXT_OK | Raspberry Pi AI HAT+ - Il Raspberry Pi AI HAT+ integra un acceleratore hardware  | §1, §1.4 + PH | not required |
| 16 | yes | TEXT_OK | SW per deep learning | §2, §2.1 | not required |
| 17 | yes | TEXT_OK | I modelli preallenati si trovano online - Le grandi CNN impiegano settimane per  | §2, §2.1 | not required |
| 18 | yes | TEXT_OK | Model Zoo e fonti di modelli pre-addestrati - Hugging Face Hub - Hub centralizza | §2, §2.1 | not required |
| 19 | yes | TEXT_OK | Esempi di modelli per biometria da Hugging face - In Hugging Face non ci sono so | §2, §2.1 | not required |
| 20 | yes | TEXT_OK | Approccio corretto all'uso dei sistemi deep - Non è quasi mai conveniente chiede | §2, §2.2 + PH | not required |
| 21 | yes | LIKELY_VISUAL | Esempi di reti famose Impiegate in centinaia di applicazioni, anche dopo re-trai | §2, §2.3 | not required |
| 22 | yes | TEXT_OK | CNN – LeNet 5 (Yann LeCun) Una immagine piccola in ingresso e/o a toni di grigio | §2, §2.3 + PH | not required |
| 23 | yes | TEXT_OK | AlexNet Output: 1000x1 probability vector che corrisponde ad ognuna delle 1000 c | §2, §2.3 + PH | not required |
| 24 | yes | TEXT_OK | VGG16 – Convolutional Network for Classification and Detection (Visual Geometry  | §2, §2.3 + PH | not required |
| 25 | yes | TEXT_OK | Stato di fatto in applicazioni: Residual neural network: ResNet ResNet è lo stan | §2, §2.4 | not required |
| 26 | yes | TEXT_OK | ResNet idea (skip connections) - ResNet è un'architettura di deep learning in cu | §2, §2.4 + PH | not required |
| 27 | yes | TEXT_OK | Famiglia delle ResNet ResNet-34, ResNet-50, ResNet-101, ResNet-152 - Cambia il n | §2, §2.4 | not required |
| 28 | yes | TEXT_OK | Come usare la ResNet - Prima dello strato finale Fully Connected (FC) arrivano 5 | §2, §2.4 + PH | not required |
| 29 | yes | TEXT_OK | SW libraries and toolboxes - Centinaia di toolboxes sono ora disponibili - Deepl | §2, §2.5 | not required |
| 30 | yes | TEXT_OK | Esempi di applicazioni deep in biometria | §3 | not required |
| 31 | yes | LIKELY_VISUAL | Kalaivani Sundararajan,D. L. Woodard, Deep Learning for Biometrics: A Survey, AC | §3 | not required |
| 32 | yes | TEXT_OK | Deep Learning per Identification Trained Classifier Sample Name/Identifier (è il | §3, §3.1 + PH | not required |
| 33 | yes | TEXT_OK | Deep Learning per Identification Trained Classifier Sample Name/Identifier (è il | §3, §3.1 | not required |
| 34 | yes | TEXT_OK | Deep Learning per Identification Non identificatore ma autenticatore! Vorremmo i | §3, §3.2 | not required |
| 35 | yes | TEXT_OK | Creare un estrattore automatico di feature biometriche PASSO 1 (Training con ide | §4, §4.1 + PH | not required |
| 36 | yes | TEXT_OK | Creare un estrattore automatic di feature biometriche: Passo 2 (Uso biometrico e | §4, §4.2 + PH | not required |
| 37 | yes | TEXT_OK | Come si allena una CNN per funzionare in modo biom.? - La rete è usata come estr | §4, §4.3 | not required |
| 38 | yes | TEXT_OK | Stato finale softmax o arcface Teniamo la parte convoluzionale (backbone) che pr | §4, §4.3 + PH | not required |
| 39 | yes | TEXT_OK | Stato finale softmax o arcface Softmax (classificazione chiusa) Gli embedding ve | §4, §4.3 | not required |
| 40 | yes | TEXT_OK | In deployment devo adattare la metrica di matching Template A Matcher DB Templat | §4, §4.3 | not required |
| 41 | yes | LIKELY_VISUAL | Esempi di applicazioni deep in biometria Vediamo alcuni esempi di moduli tipici  | §5, §5.1 | not required |
| 42 | yes | TEXT_OK | Face recognition: Database e performance Labeled Faces in the Wild Dataset (LFW) | §5, §5.1 | not required |
| 43 | yes | TEXT_OK | Esempio: VGGFACE+ PCA https://www.robots.ox.ac.uk/~vgg/software/vgg_face/ L'uso  | §5, §5.1 + PH | not required |
| 44 | yes | TEXT_OK | CNN per Speech recognition Time Frequency Spectrogram CNN Image «I'm very» | §5, §5.2 + PH | not required |
| 45 | yes | TEXT_OK | CNN per Speech recognition Time Frequency Spectrogram CNN Image «Happy» | §5, §5.2 | not required |
| 46 | title only | LIKELY_VISUAL | Palm Recognition: A Contacless Deep Learning Approach | §5, §5.2 + PH | not required |
| 47 | yes | LIKELY_VISUAL | Robust Age Estimation Rete pretrained Principal Component Analysis Strati finali | §5, §5.2 + PH | not required |
| 48 | title only | LIKELY_VISUAL | Tool OpenFace 2.0 (Carnegie Mellon University.) | §5, §5.2 + PH | not required |
| 49 | yes | LIKELY_VISUAL | Deep learning per applicazioni basate sulla voce - Speaker recognition - Speech  | §5, §5.2 | not required |
| 50 | yes | TEXT_OK | DeepFace (ex FaceBook, ora Meta) - Rete Deep per Face Recognition composta da 9  | §5, §5.3 | not required |
| 51 | yes | TEXT_OK | DeepFace (cont.) Input: imagine RGB del volto scalata a 152x152 Processing: a) 2 | §5, §5.3 + PH | not required |
| 52 | yes | TEXT_OK | FaceNet (Google) - Accuratezza del 99.63% si LFW (2015, 260M param.) - Esegue so | §5, §5.3 | not required |
| 53 | yes | TEXT_OK | Migliori algoritmi per Labeled Faces in the Wild (LFW) Alcuni modelli hanno segn | §5, §5.3 + PH | not required |
| 54 | yes | TEXT_OK | Un esempio di tecnica di deep learning: GANs DeepMasterPrints Biometric deep fak | §5, §5.4 + PH | not required |
| 55 | yes | TEXT_OK | E' reale o creato da una AI? A: It's Fake… https://www.thispersondoesnotexist.co | §5, §5.4 | not required |
| 56 | yes | LIKELY_VISUAL | Esempi di fake faces | §5, §5.4 + PH | not required |
| 57 | yes | TEXT_OK | La «Gestalt» umana è ancora mancante….. | §5, §5.4 + PH | not required |
| 58 | yes | TEXT_OK | Ma sempre nuovi strumenti arrivano.. https://grokaiimagegenerator.net/generate h | §5, §5.4 | not required |
| 59 | yes | TEXT_OK | Altri esempi di applicazioni Altri esempi di applicazioni biometriche con reti t | §5, §5.4 | not required |
| 60 | yes | TEXT_OK | Deep Learning for Biometrics: a survey Si consiglia la lettura della dispensa al | §5, §5.4 | not required |
| 61 | yes | TEXT_OK | Metodi avanzati per la progettazione e miglioramento dei sistemi biometrici Fabi | §6 | not required |
| 62 | yes | TEXT_OK | Outline 1. Biometria adattativa 1. Template Update (Re-enrollment) 2. Template S | §6 | not required |
| 63 | yes | LIKELY_VISUAL | Biometria adattativa | §7, §7.1 | not required |
| 64 | yes | TEXT_OK | Template Selection e Update La questione della selezione e dell'aggiornamento de | §7, §7.1 | not required |
| 65 | yes | LIKELY_VISUAL | Template selection Elevatissimo frame rate + Indice di qualità del frame KO OK I | §7, §7.2 + PH | not required |
| 66 | yes | TEXT_OK | Le viste 2,5 ottenute servono per integrare il modello 3D del volto Questo puo'  | §7, §7.2 + PH | not required |
| 67 | yes | TEXT_OK | Template update (singolo) Se abbiamo un buon match, possiamo aggiungere una regi | §7, §7.2 + PH | not required |
| 68 | yes | TEXT_OK | Template Selection e Update: stato dell'arte Può essere riassunto attraverso le  | §7, §7.3 | not required |
| 69 | yes | LIKELY_VISUAL | Template selection manuale | §7, §7.3 + PH | not required |
| 70 | yes | TEXT_OK | Algoritmo per Template Update automatico nel tempo Se abbiamo un buon match, pos | §7, §7.4 + PH | not required |
| 71 | yes | TEXT_OK | Template update temporale (cont.) IMP!! A piccoli passi possiamo agganciare imma | §7, §7.4 + PH | not required |
| 72 | yes | TEXT_OK | Template update temporale (cont.2) Possiamo in realtà immaginarlo come un grafo  | §7, §7.4 + PH | not required |
| 73 | yes | TEXT_OK | Template update temporale: cancellazione - Un meccanismo automatico di re-enroll | §7, §7.5 | not required |
| 74 | footer only | LIKELY_VISUAL | (no text) | §8 + PH | not required |
| 75 | yes | TEXT_OK | Ricorda di segnalare le cose da cambiare, ma anche le buone prassi, Per evitare  | §8 | not required |
| 76 | footer only | LIKELY_VISUAL | (no text) | §8 + PH | not required |
| 77 | footer only | LIKELY_VISUAL | (no text) | §8 + PH | not required |

**Totals:** 77/77 pages accounted for · 34 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione09 — DONE

**Lesson file(s):** `Lezione09/L09 - Metodi statistici per la biometria (parte 1) e libreria DeepFace.md`

### `Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf` (82 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Parte A Lezione Modelli e metodi statistici per la biometria Fabio Scotti fabio. | §0 | not required |
| 3 | yes | TEXT_OK | Outline DATA REPORTING sistemi biometrici 1. Misura della accuratezza dei sistem | §0 | not required |
| 4 | yes | TEXT_OK | Conoscenza nei sistemi biometrici Aspetti multi- disciplinari Pattern matching E | §0 | not required |
| 5 | yes | TEXT_OK | Conoscenza nei sistemi biometrici Aspetti multi- disciplinari Pattern matching E | §0 | not required |
| 6 | yes | LIKELY_VISUAL | Modelli e metodi statistici per la misura dei sistemi biometrici Metriche, Uso d | §1 | not required |
| 7 | yes | TEXT_OK | Premessa: sistema configurato - Se il sistema ha una soglia T variabile → dobbia | §1, §1.1 | not required |
| 8 | yes | TEXT_OK | Flusso di lavoro Usare i dati biometrici per creare impostori e genuini Calcolar | §1, §1.2 + PH | not required |
| 9 | yes | TEXT_OK | Comprare un sistema «of the shelf» Si possono avere diversi casi: - Avere già p  | §1, §1.2 | not required |
| 10 | yes | TEXT_OK | EER o p(errore) ? Meglio usare i grafici della DET senza ragionare sulla soglia  | §1, §1.3 | not required |
| 11 | yes | TEXT_OK | Uso i miei dati o dei modelli statistici? L'inferenza statistica (statistica) Pr | §2 | not required |
| 12 | yes | TEXT_OK | Non ho nessun dato→Uso volontari per le stimare le distribuzioni dei match score | §2 + PH | not required |
| 13 | yes | TEXT_OK | DATA REPORTING LE DISTRIBUZIONI matchscore - Ecco il primo e fondamentale dato d | §2 | not required |
| 14 | yes | TEXT_OK | Caratteristiche generali delle distribuzioni genuini ed impostori (nel caso da i | §3, §3.1 + PH | not required |
| 15 | yes | TEXT_OK | Cardinalità genuini (nel caso da inferenza statistica = dati veri) Match Genuini | §3, §3.2 | not required |
| 16 | yes | TEXT_OK | Cardinalità impostori (nel caso da inferenza statistica = dati veri) Match Impos | §3, §3.2 | not required |
| 17 | yes | TEXT_OK | Cardinalità impostori (nel caso da inferenza statistica = dati veri) Match Impos | §3, §3.2 | not required |
| 18 | yes | FORMULA_AMBIGUOUS | Cardinalità impostori (nel caso da inferenza statistica = dati veri) Match Impos | §3, §3.2 + PH | not required |
| 19 | yes | TEXT_OK | Riassunto delle cardinalità Impostori ≈N2-N Genuini ≈N Caso analizzato: M foto p | §3, §3.2 | not required |
| 20 | yes | TEXT_OK | Attenzione alle code delle distribuzioni rumorose calcolando la soglia T Soglia  | §3, §3.3 + PH | not required |
| 21 | yes | FORMULA_AMBIGUOUS | Modello probabilistico/teorico - Un modello teorico permette di calcolare FMR()  | §4, §4.1 + PH | not required |
| 22 | yes | TEXT_OK | Calcolo diretto dalle distrubuzioni →FMR FNMR - Caso diverso: ho a disposizione  | §4, §4.2 | not required |
| 23 | yes | FORMULA_AMBIGUOUS | Dalle distribuzioni al FM Rate - Come vediamo dal grafico il numero di numero di | §4, §4.2 + PH | not required |
| 24 | yes | FORMULA_AMBIGUOUS | Decision Error Tradeoff (DET) Receiver Operating Characteristic (ROC) Le perform | §5, §5.1 + PH | not required |
| 25 | yes | FORMULA_AMBIGUOUS | DATA REPORTING LA CURVA DET - Secondo fondamentale dato da ricavare! - Controlla | §5, §5.1 | not required |
| 26 | yes | TEXT_OK | FMR, FNMR, FAR e FNAR - Il funzionamento di un sistema biometrico dal punto di v | §5, §5.2 | not required |
| 27 | yes | TEXT_OK | DET = 1 - ROC La curva DET e la curva ROC mostrano le stesse informazioni. Rifer | §5, §5.2 | not required |
| 28 | yes | TEXT_OK | Regioni di funzionamento - Regolando la soglia T possiamo regolare il livello di | §5, §5.3 + PH | not required |
| 29 | yes | TEXT_OK | Equal Error Rate - L'EER è il tasso di errore corrispondente all'unico punto nel | §5, §5.4 | not required |
| 30 | yes | TEXT_OK | DATA REPORTING EER o FNMR@FMR=xxx - Terzo fondamentale dato da ricavare! - Contr | §5, §5.4 + PH | not required |
| 31 | yes | TEXT_OK | DET: Sistema ideale - Soglia –infinito : (FMR=1, FNMR=0) - Soglia intermedia : ( | §5, §5.5 + PH | not required |
| 32 | yes | TEXT_OK | Variazioni della ROC - Cambiando la scala degli assi si ottengono delle rapprese | §5, §5.5 + PH | not required |
| 33 | yes | TEXT_OK | ZeroFMR e ZeroFNMR - Come possiamo capire quanto alzare la soglia della somiglia | §5, §5.6 + PH | not required |
| 34 | yes | TEXT_OK | ZeroFAR e ZeroFRR Per quanto i termini FMR e FNMR valgono sempre, talvolta nei s | §5, §5.6 | not required |
| 35 | yes | TEXT_OK | Esempio di creazione di CMC k = 1 DataBase Acquisition Feature Extraction Coding | §6, §6.1 + PH | not required |
| 36 | yes | TEXT_OK | Score = 0.71 Score = 0.70 Corretto! Score = 0.32 Score = 0.30 Score = 0.23 Esemp | §6, §6.1 + PH | not required |
| 37 | yes | TEXT_OK | Accuratezza in identificazione Cumulative Match Characteristic (CMC) - Nel conte | §6, §6.2 + PH | not required |
| 38 | yes | TEXT_OK | Identificazione: metodo gerarchico Possibile soluzione per un problema di identi | §6, §6.3 + PH | not required |
| 39 | yes | FORMULA_AMBIGUOUS | Fissare la soglia T, Calcolare p Una volta fissata la soglia T (similitudine*) i | §7, §7.1 | not required |
| 40 | yes | TEXT_OK | Che modello usiamo? Quale è il modello statistico migliore per caratterizzare un | §7, §7.2 | not required |
| 41 | yes | FORMULA_AMBIGUOUS | Tipico classificatore di immagini - Multiclasse - Binario Sistema riconoscimento | §7, §7.2 | not required |
| 42 | yes | FORMULA_AMBIGUOUS | Classificatore biometrico - Multiclasse = Identificatore - Binario = Autenticato | §7, §7.2 | not required |
| 43 | yes | TEXT_OK | Il sistema biometrico visto come classificatore DataBase Acquisition Feature Ext | §7, §7.2 | not required |
| 44 | yes | TEXT_OK | SB = classif. DataBase Acquisition Feature Extraction Coding Matching Identifica | §7, §7.2 + PH | not required |
| 45 | yes | TEXT_OK | Valutazione - Accuratezza - Precisione e Recall - Squared error - Likelihood - P | §7, §7.3 | not required |
| 46 | yes | TEXT_OK | Reti Deep per il volto e Modelli e metodi statistici per la biometria Fabio Scot | §8, §8.1 | not required |
| 47 | yes | TEXT_OK | Parte B Approfondimento Reti Deep per il volto Fabio Scotti fabio.scotti@unimi.i | §8, §8.1 | not required |
| 48 | yes | TEXT_OK | DeepFace - Andiamo a dettagliare meglio una delle reti maggiormente versatili, u | §8, §8.1 | not required |
| 49 | yes | TEXT_OK | Contesto e Panoramica delle funzioni - Il codice di DeepFace (scritto principalm | §8, §8.1 + PH | not required |
| 50 | yes | TEXT_OK | Deepface contiene diversi modelli (chiamarlo «wrapper» è riduttivo…) VGG-Face: q | §8, §8.2 | not required |
| 51 | yes | TEXT_OK | Deepface contiene diversi modelli OpenFace è un modello open- source che mira a  | §8, §8.2 | not required |
| 52 | yes | TEXT_OK | Deepface contiene diversi modelli ArcFace è noto per la sua robustezza e accurat | §8, §8.2 | not required |
| 53 | yes | TEXT_OK | Deepface contiene diversi modelli DeepID è una serie di modelli che sono stati s | §8, §8.2 | not required |
| 54 | yes | TEXT_OK | Deepface contiene diversi modelli Dlib: denominato «La più semplice API di ricon | §8, §8.2 | not required |
| 55 | yes | TEXT_OK | Come scelgo? - Quando scegliete un modello da utilizzare o allenare, considerate | §8, §8.2 | not required |
| 56 | yes | LIKELY_VISUAL | Facial Recognition (detector_backend) - Si hanno diversi modelli usabili per que | §9 + PH | not required |
| 57 | yes | LIKELY_VISUAL | Facial Recognition - Si hanno diversi modelli usabili per questo task Questo di  | §9 + PH | not required |
| 58 | yes | TEXT_OK | Facial Recognition (Note utili in sviluppo) Dlib Questo rilevatore utilizza un a | §9 | not required |
| 59 | yes | LIKELY_VISUAL | RetinaFace…. | §9 + PH | not required |
| 60 | yes | FORMULA_AMBIGUOUS | Uso molto intuitivo detectors = ["opencv", "ssd", "mtcnn", "dlib", "retinaface"] | §9 | not required |
| 61 | yes | LIKELY_VISUAL | Emotion Detection Più avanti nell'insegnamento lo tratteremo più in dettaglio | §10 + PH | not required |
| 62 | yes | LIKELY_VISUAL | Alcune delle informazioni estraibili | §10 + PH | not required |
| 63 | yes | TEXT_OK | Face authentication | §10 | not required |
| 64 | yes | TEXT_OK | Face recognition (identification) PROBE GALLERY | §10 + PH | not required |
| 65 | yes | TEXT_OK | Probe e Gallery Sonda (Probe) si riferisce al campione biometrico che viene attu | §10 | not required |
| 66 | yes | TEXT_OK | Probe e Gallery Galleria (Gallery) si riferisce al database o alla collezione di | §10 | not required |
| 67 | yes | TEXT_OK | Non è perfetto! - Il fatto che sia molto avanzato o provenga da un ottimo labora | §10 | not required |
| 68 | yes | TEXT_OK | Laboratorio DeepFace: Face analysis CODICE NON IN ESAME Studiare invece le osser | §11, §11.1 | not required |
| 69 | yes | TEXT_OK | Approccio corretto all'uso dei sistemi deep (recall) - Non è quasi mai convenien | §11, §11.1 | not required |
| 70 | yes | TEXT_OK | Nota sulle versioni - Controlla se esistono versioni aggiornate delle librera, e | §11, §11.1 | not required |
| 71 | yes | TEXT_OK | Installazione e uso (versione Colab) !pip install deepface from deepface import  | §11, §11.2 | not required |
| 72 | yes | FORMULA_AMBIGUOUS | Attenzione di verificare gli output! Ripartiamo dal nostro detector di Haar # In | §11, §11.2 | not required |
| 73 | yes | TABLE_AMBIGUOUS | # Perform Face Detection gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY) faces = fa | §11, §11.2 | not required |
| 74 | footer only | LIKELY_VISUAL | (no text) | §11, §11.2 + PH | not required |
| 75 | yes | LIKELY_VISUAL | Non sempre le cose vanno come ti aspetti…. 1) Verificare gli output 2) Inserire  | §11, §11.2 + PH | not required |
| 76 | yes | TEXT_OK | Verifica della identità result = DeepFace.verify(img1_path = "img1.jpg", img2_pa | §11, §11.3 | not required |
| 77 | yes | TEXT_OK | Compito - Non è un progetto di esame - Non da punti per l'esame - Inviare per em | §11, §11.4 | not required |
| 78 | yes | TEXT_OK | Compito «BLUR MINORI» - Unendo le tecnologie già viste, creare uno script Python | §11, §11.4 + PH | not required |
| 79 | yes | TEXT_OK | Compito «AGE DETECTOR IN THE WILD» - Raffinare lo script per cercare di analizza | §11, §11.4 | not required |
| 80 | yes | TEXT_OK | Punti principali visti - Reti Deep per il volto e Modelli e metodi statistici pe | §13 | not required |
| 81 | yes | TEXT_OK | Appendici con il codice Non richiesto all'esame | §11, §11.5 | not required |
| 82 | yes | TABLE_AMBIGUOUS | # Install required packages !pip install deepface !pip install opencv-python # I | §11, §11.5 | not required |

**Totals:** 82/82 pages accounted for · 28 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione11 — DONE

**Lesson file(s):** `Lezione11/L11 - Acquisizione delle immagini biometriche - embedding, ottiche e parametri della camera.md`

### `Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf` (135 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Parte A Effetto delle focali suo volto, Esame delle distanze Fabio Scotti fabio. | §1, §1.1 | not required |
| 3 | yes | TEXT_OK | DeepFace - Continuiamo ad esplorare una delle migliori e aggiornate librerie a d | §1, §1.1 | not required |
| 4 | yes | LIKELY_VISUAL | Implementiamo quindi alcuni dei metodi teorici presentati. | §1, §1.1 | not required |
| 5 | yes | TEXT_OK | Importiamo le librerie !pip install deepface !pip install opencv-python import n | §1, §1.1 | not required |
| 6 | yes | TEXT_OK | Ripasso analisi volto # Carica l'immagine da analizzare img_path1 = "Cindy24.jpg | §1, §1.2 | not required |
| 7 | yes | TEXT_OK | Ripasso analisi volto # Carica l'immagine da analizzare img_path2 = "Cindy57.jpg | §1, §1.2 + PH | not required |
| 8 | yes | TEXT_OK | Face detector da Deepface # Load the image using OpenCV image = cv2.imread(img_p | §1, §1.2 | not required |
| 9 | yes | TEXT_OK | Estrazione vettore di embedding img_path1 = "Cindy50.jpg" img_path2 = "Cindy57.j | §1, §1.3 | not required |
| 10 | yes | TABLE_AMBIGUOUS | Altri estrattori # Proviamo altri estrattori models = ['VGG-Face', 'Facenet', 'O | §1, §1.3 | not required |
| 11 | yes | TEXT_OK | Altri estrattori # Proviamo altri estrattori models = ['VGG-Face', 'Facenet', 'O | §1, §1.3 | not required |
| 12 | yes | TEXT_OK | Come visualizzare un vettore di embedding Si tratta di un vettore di double. Si  | §1, §1.3 + PH | not required |
| 13 | yes | LIKELY_VISUAL | Distanza Come uniformare distanza e similitudine | §2, §2.1 | not required |
| 14 | yes | TEXT_OK | Distanza (codice) from scipy.spatial import distance def calculate_metrics(embed | §2, §2.1 | not required |
| 15 | yes | FORMULA_AMBIGUOUS | Dashboard distanza # Calculate the delta between the two embeddings delta_embedd | §2, §2.2 | not required |
| 16 | yes | LIKELY_VISUAL | Dashboard distanza (codice nel file allegato) | §2, §2.2 + PH | not required |
| 17 | yes | LIKELY_VISUAL | Dashboard distanza img_path1 = "Cindy24.jpg" img_path2 = "Cindy57.jpg" Osservare | §2, §2.2 + PH | not required |
| 18 | yes | LIKELY_VISUAL | Altre comparazioni | §2 + PH | not required |
| 19 | yes | LIKELY_VISUAL | Altre comparazioni | §2 | not required |
| 20 | yes | TEXT_OK | Distanza fra genuini un ottimo modo di ragionare… 0,5 Soglia da settare Note - A | §2, §2.3 + PH | not required |
| 21 | yes | TEXT_OK | Compito - Non è un progetto di esame - Non da punti per l'esame - Inviare per em | §3 | not required |
| 22 | yes | TEXT_OK | Compito Similitudine parentale - Cercare similitudini parentali per studiare l'e | §3 + PH | not required |
| 23 | yes | TEXT_OK | Compito Esame della risoluzione (analisi con distanza fra genuini) 0,5 Esaminare | §3 | not required |
| 24 | yes | TEXT_OK | Outline - Magnificazione/ ingrandimento - Messa a fuoco - Zoom (Magnificazione v | §4 | not required |
| 25 | yes | TEXT_OK | Perché' serve conoscere le ottiche in biometria? - La parte di visione è diventa | §4 | not required |
| 26 | yes | TEXT_OK | A cosa servono le ottiche? La prima funzione di un'ottica è raccogliere luce, ma | §4 | not required |
| 27 | yes | LIKELY_VISUAL | Magnificazione (ingrandimento) e messa a fuoco Cosa si può fare con 1 sola lente | §5, §5.1 | not required |
| 28 | yes | LIKELY_VISUAL | Magnificazione/Ingrandimento lente singola Oggetto vicino alla lente Oggetto lon | §5, §5.1 + PH | not required |
| 29 | yes | LIKELY_VISUAL | Magnificazione/Ingrandimento lente singola | §5, §5.1 | not required |
| 30 | yes | TEXT_OK | Messa a fuoco lente singola Sensore Non potendo spostare il sensore, si sposta l | §5, §5.2 | not required |
| 31 | yes | TEXT_OK | Messa a fuoco lente singola mediante contrasto Immaginiamo un punto luminoso →Es | §5, §5.2 + PH | not required |
| 32 | yes | LIKELY_VISUAL | Magnificazione Focale di arrivo = 660mm !!! Ricoh H55ZC-ME-F-HD-PR01 | §5, §5.3 + PH | not required |
| 33 | yes | TEXT_OK | Aberrazioni delle ottiche | §6, §6.1 | not required |
| 34 | yes | TEXT_OK | Compound (Thick) Lens Vignetting Chromatic Abberation Radial and Tangential Dist | §6, §6.1 + PH | not required |
| 35 | yes | LIKELY_VISUAL | Vignetting (Vignettatura) 1L L L B A more light from A than B ! | §6, §6.1 | not required |
| 36 | yes | FORMULA_AMBIGUOUS | Vignetting (Vignettatura) 1L L L B A more light from A than B ! Vignettatura: I/ | §6, §6.1 + PH | not required |
| 37 | title only | LIKELY_VISUAL | Chromatic Abberation | §6, §6.1 | not required |
| 38 | yes | LIKELY_VISUAL | Spherical Aberration Le lenti sferiche sono l'unico formato facile da produrre,  | §6, §6.1 + PH | not required |
| 39 | yes | TEXT_OK | Es.: aberrazione sferica nella surveillance Per esempio nel volto, l'aberrazione | §6, §6.1 | not required |
| 40 | yes | TEXT_OK | Aberrazione cromatica - Difetto nella formazione dell'immagine dovuta al diverso | §6, §6.1 | not required |
| 41 | yes | TEXT_OK | Correzzione aberrazione sferica - L'aberrazione sferica si presenta quando le le | §6, §6.2 | not required |
| 42 | yes | LIKELY_VISUAL | Lenti asferiche Impieghi in biometria | §6, §6.2 | not required |
| 43 | yes | TEXT_OK | Lente asferica Lente il cui profilo che non è né una porzione di sfera né un cil | §6, §6.2 | not required |
| 44 | yes | LIKELY_VISUAL | Comparazione con lenti sferiche Cosa vedo se appoggio su un reticolo stampato le | §6, §6.2 + PH | not required |
| 45 | title only | LIKELY_VISUAL | Lenti liquide | §6, §6.3 | not required |
| 46 | yes | TEXT_OK | Lenti liquide - Obiettivo costituito da un'unica piccola lente 'liquida' - Due f | §6, §6.3 | not required |
| 47 | yes | LIKELY_VISUAL | Lenti liquide Rappresentano il futuro dell'ottica e la possibilità di studiare n | §6, §6.3 | not required |
| 48 | yes | LIKELY_VISUAL | Lenti liquide: realizzazione e funzionamento | §6, §6.3 + PH | not required |
| 49 | yes | LIKELY_VISUAL | Diaframma liquido Possibilità di regolare diaframma e magnificazione a 30FPS | §6, §6.3 | not required |
| 50 | yes | TEXT_OK | Lenti liquide: esempi commerciali - Applicazioni - sistemi piatti "low footprint | §6, §6.3 | not required |
| 51 | yes | TEXT_OK | Nuovi tipi di lenti: Metalenti Lenti piatte a nanostruttura - Realizzate utilizz | §6, §6.4 | not required |
| 52 | yes | TEXT_OK | Es. commerciale Metalenz https://metalenz.com/ - Si usano per creare micro proie | §6, §6.4 | not required |
| 53 | yes | TEXT_OK | Esempio di uso delle lenti liquide per impronte senza contatto Dispositivo compa | §6 + PH | not required |
| 54 | yes | FORMULA_AMBIGUOUS | Regola: L'immagine formata dalla prima lente diventa l'oggetto della seconda len | §6, §6.5 + PH | not required |
| 55 | yes | LIKELY_VISUAL | Zoom: la magnificazione variabile Funzionamento e applicazioni | §7, §7.1 | not required |
| 56 | yes | TEXT_OK | Zoom = Magnificazione variabile Sorveglianza Industriale Biometrico In ottica un | §7, §7.1 | not required |
| 57 | yes | TEXT_OK | Zoom ottico Azionamento Struttura Zoom: obiettivo complesso la cui lunghezza foc | §7, §7.1 | not required |
| 58 | yes | TEXT_OK | Zoom ottico e digitale 150x ottico 150x ottico + 350x digitale Solo lo zoom otti | §7, §7.1 + PH | not required |
| 59 | yes | TEXT_OK | Come leggere i parametri di uno zoom 18-55 mm Escursione focale. Questo zoom va  | §7, §7.2 | not required |
| 60 | yes | TEXT_OK | Zoom motorizzati Telecamera industriale Zoom motorizzato Cavo controllo zoom Cav | §7, §7.2 + PH | not required |
| 61 | yes | TEXT_OK | Zoom «neurale»: il caso della luna dell'S21 - Algoritmi neurali possono fare la  | §7, §7.3 | not required |
| 62 | yes | TEXT_OK | Finchè arriva uno spoofing convincente L'utente ibreakphotos dice WHAT I DID (TE | §7, §7.3 | not required |
| 63 | yes | LIKELY_VISUAL | Parliamone… zoom | §7, §7.3 + PH | not required |
| 64 | yes | LIKELY_VISUAL | Test 2 zoom La metà luna non è stata riconosciuta e «migliorata» | §7, §7.3 + PH | not required |
| 65 | yes | TEXT_OK | «Effetto» finale - Samsung è stata costretta ad ammettere che le foto vengono el | §7, §7.3 | not required |
| 66 | yes | TEXT_OK | Samsung e la AI nelle immagini … - Since the introduction of the Galaxy S10, the | §7, §7.3 | not required |
| 67 | yes | LIKELY_VISUAL | AI enhacing Passo 1: moon detector (CNN) Passo 2: adjusting brightness Passo 3:  | §7, §7.3 + PH | not required |
| 68 | yes | LIKELY_VISUAL | Applicazione dello zoom in sorveglianza Il volto è stato mascherato, era visibil | §7, §7.4 + PH | not required |
| 69 | yes | TEXT_OK | Focali da record Focale di arrivo = 660mm !!! Si possono impiegare focali impres | §7, §7.4 | not required |
| 70 | yes | LIKELY_VISUAL | Valori più comuni di focale f=5.7~205.2mm Focali da sotto i 10 mm a sopra i 200  | §7, §7.4 | not required |
| 71 | yes | TEXT_OK | Sistema vero di lenti nell'obiettivo Una buona ottica per fotocamera può contere | §7, §7.4 + PH | not required |
| 72 | yes | TEXT_OK | Esempio di camera frontale Lenti aferiche per correzione aberrazioni Lente mobil | §7, §7.5 + PH | not required |
| 73 | yes | LIKELY_VISUAL | Iphone X TrueDept Module Integrazione di dispositivi miniaturizzati | §7, §7.5 + PH | not required |
| 74 | yes | TEXT_OK | Il mondo mobile non ha (principalmente) zoom ottici, ma software →riscalano in m | §7, §7.5 | not required |
| 75 | yes | TEXT_OK | Primi esempi di EMULAZIONE zoom ottici nel mondo mobile Es. Oppo: tecnologia con | §7, §7.5 | not required |
| 76 | yes | TEXT_OK | Samsung s25 periscopio All Lens on Prism (ALoP) - Samsung Galaxy S25 Ultra ha un | §7, §7.5 | not required |
| 77 | yes | TEXT_OK | Galaxy S9 Iris scanner Comparazione con la camera RGB frontale Samsung: Le lenti | §7, §7.5 | not required |
| 78 | yes | LIKELY_VISUAL | Obiettivo di un drone Lenti asferiche SIMTOO XT - 175 Numerose applicazioni biom | §7, §7.5 + PH | not required |
| 79 | yes | TEXT_OK | Lenti industriali e scientifiche: C-mount - C-mount - Filetto maschio sulla lent | §7, §7.6 | not required |
| 80 | yes | LIKELY_VISUAL | Riassumendo: ottiche e sensori proporzionati! | §7, §7.6 | not required |
| 81 | yes | LIKELY_VISUAL | Regolazioni ottiche Per iniziare ad usare al meglio il nostro sistema ottico Ste | §8 | not required |
| 82 | yes | TEXT_OK | Principali regolazioni ottiche Focale Diaframma Messa a fuoco | §8 | not required |
| 83 | yes | LIKELY_VISUAL | Regolazione del diaframma | §8, §8.1 | not required |
| 84 | yes | TEXT_OK | Apertura diaframma/lente f-number La quantità della luce catturata dalla lente è | §8, §8.1 | not required |
| 85 | yes | TEXT_OK | Luminosità immagine VS Apertura Stesso tempo di esposizione Aperture della lente | §8, §8.1 + PH | not required |
| 86 | yes | TEXT_OK | Apertura Vs Profondità di campo (di fuoco) Occorre però più tempo di esposizione | §8, §8.2 + PH | not required |
| 87 | yes | LIKELY_VISUAL | Apertura Vs Profondità di campo (2) lens at f / 22 lens at f / 4 Or more exposur | §8, §8.2 | not required |
| 88 | yes | TEXT_OK | Stop? Uno "stop" indica raddoppiare o dimezzare la quantità di luce che arriva s | §8, §8.2 | not required |
| 89 | yes | LIKELY_VISUAL | Regolazione della messa a fuoco | §8, §8.3 | not required |
| 90 | yes | LIKELY_VISUAL | Piano di messa a fuoco | §8, §8.3 | not required |
| 91 | yes | LIKELY_VISUAL | Regolazione della focale | §8, §8.3 | not required |
| 92 | yes | LIKELY_VISUAL | Scelta della focale | §8, §8.3 | not required |
| 93 | title only | LIKELY_VISUAL | Focale | §8, §8.3 | not required |
| 94 | yes | TEXT_OK | Effetto della deformazione sulle proporzioni del volto f=40mm f=240mm E' possibi | §8, §8.3 + PH | not required |
| 95 | yes | LIKELY_VISUAL | Altro esempio | §8, §8.3 | not required |
| 96 | yes | LIKELY_VISUAL | Altro esempio Cercare le varie deformazioni del volto a partire dal naso | §8, §8.3 | not required |
| 97 | yes | LIKELY_VISUAL | Scelta della focale Sorveglianza Sorv. con volto Volto iride | §8, §8.3 + PH | not required |
| 98 | yes | LIKELY_VISUAL | Ampiezza delle focali | §8, §8.4 | not required |
| 99 | yes | TEXT_OK | Legame fra FOV e focale Il Field of View (FOV) e la focale hanno una relazione i | §8, §8.4 + PH | not required |
| 100 | yes | TEXT_OK | Visione umana binocoluare FOV ≈120° Att! Con risoluzione diverse! Un sistema bio | §8, §8.4 | not required |
| 101 | yes | TEXT_OK | F e FOV - Attenzione e non confondere - la focale (f) misurata in mm - E il Fiel | §8, §8.4 | not required |
| 102 | yes | TEXT_OK | Esempio di scelta del FOV: sorveglianza e grandangolari (f<=20mm) In video sorve | §8, §8.4 + PH | not required |
| 103 | yes | LIKELY_VISUAL | Esempio di scelta del FOV: iris recognition | §8, §8.4 + PH | not required |
| 104 | yes | LIKELY_VISUAL | Lenti per videosorveglianza Possibili soluzioni e dettagli ottici | §9 | not required |
| 105 | yes | TEXT_OK | Lenti e dispositivi sorveglianza Esempi di caratteristiche da datasheet - Wide-a | §9 | not required |
| 106 | yes | TEXT_OK | Struttura di una Surveillance camera Composizione modulare di 1) Sensore CMOS 2) | §9 + PH | not required |
| 107 | yes | TEXT_OK | Regolazioni elettroniche A livello del sensore della camera | §10, §10.1 | not required |
| 108 | yes | TEXT_OK | Resolution/video mode - Deve essere adattato alla applicazione Leggere la targa  | §10, §10.1 | not required |
| 109 | yes | TEXT_OK | Resolution/video mode - Deve essere adattato alla applicazione Contare i veicoli | §10, §10.1 | not required |
| 110 | yes | LIKELY_VISUAL | Risoluzione: esempio per il Face | §10, §10.1 + PH | not required |
| 111 | yes | LIKELY_VISUAL | Frame Per Second (FPS) Veicoli in moto: - Automatic Number Plate Recognition (AN | §10, §10.1 + PH | not required |
| 112 | yes | TEXT_OK | Resolution/FPS - Deve essere adattato alla applicazione Acquisire il volto per F | §10, §10.1 | not required |
| 113 | yes | TEXT_OK | Region Of Interest (ROI) - Miglioramenti tramite ROI - Più Framerate - Meno erro | §10, §10.2 | not required |
| 114 | yes | TEXT_OK | Tempo di esposizione = Tempo di shutter = Exposure time Devono vedersi i dettagl | §10, §10.2 | not required |
| 115 | yes | TEXT_OK | Gamma di luminosità: CCD vs CMOS x surveillance CCD cameras have great WDR (wide | §10, §10.3 | not required |
| 116 | yes | TEXT_OK | Wide Dynamic Range (WDR) Surveillance Cameras WDR are perfect for the more compl | §10, §10.3 | not required |
| 117 | yes | TEXT_OK | Wide Dynamic Range: definizione - Wide Dynamic Range, è un termine utilizzato ne | §10, §10.3 | not required |
| 118 | yes | TEXT_OK | Esempio per face Intervallo dinamico (o range dinamico, dall'inglese dynamic ran | §10, §10.3 + PH | not required |
| 119 | yes | TEXT_OK | Cosa sono i dB? Quando due grandezze da confrontare sono distano diversi ordini  | §10, §10.3 | not required |
| 120 | yes | TEXT_OK | Confronto tecnologie consumer / per la sicurezza Milesight H.265+ Speed Dome Net | §10, §10.3 | not required |
| 121 | yes | LIKELY_VISUAL | CCD/CMOS un esempio: Train survelliance Vibrazioni →Effetto jello Differenze di  | §10, §10.3 + PH | not required |
| 122 | yes | TEXT_OK | Multiple Exposure - Scene complesse con - presenza di sole/ombre - Illuminazione | §10, §10.4 | not required |
| 123 | yes | TEXT_OK | Multiple Exposure contemporanea Due immagini vengono acquisite simultaneamente ( | §10, §10.4 | not required |
| 124 | yes | TEXT_OK | High Dynamic Range imaging (solitamente da frame diversi) - Usata in fotografia  | §10, §10.4 | not required |
| 125 | title only | LIKELY_VISUAL | High Dynamic Range imaging (2) | §10, §10.4 | not required |
| 126 | yes | LIKELY_VISUAL | HDR: esempio in sorveglianza Multiframe HDR Immagine commerciale pro HDR… | §10, §10.4 + PH | not required |
| 127 | yes | TEXT_OK | Macro Un'ottica macro è un sistema ottico progettato per acquisire immagini di s | §11, §11.1 | not required |
| 128 | yes | TEXT_OK | Macro in biometria un esempio per le impronte digitali contactless Immagine di u | §11, §11.1 + PH | not required |
| 129 | yes | LIKELY_VISUAL | Frame Rate: molto importante! - Motion detection - Segmentation - Anti spoofing | §11, §11.2 | not required |
| 130 | yes | LIKELY_VISUAL | Es. di elevato Frame Rrate e #bit/pixel: Anti-spoofing con Remote PhotoPlethysmo | §11, §11.2 + PH | not required |
| 131 | yes | TEXT_OK | Lenti liquide per iride Improving_iris_recognition_liquid_lense.pdf (corning.com | §11, §11.3 | not required |
| 132 | yes | TEXT_OK | Iris recognition (sistema a basso costo, lenti tradizionali) - Budget: 50 euro - | §11, §11.3 | not required |
| 133 | yes | TEXT_OK | Iris recognition (low cost) - Davvero utilizzabile? - Histogram Gamma transforma | §11, §11.3 + PH | not required |
| 134 | yes | TEXT_OK | Biometric Access Control System (BACS CrucialTrack - Quattro) Informazioni tratt | §11, §11.4 | not required |
| 135 | yes | TEXT_OK | In sintesi FINE Abbiamo visto: - Magnificazione/ingrandimento, Messa a fuoco, Zo | §11, §13 | not required |

**Totals:** 135/135 pages accounted for · 43 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione10 — DONE

**Lesson file(s):** `Lezione10/L10 - Metodi statistici per la biometria (parte 2) e best practices di testing.md`

### `Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf` (82 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Modelli statistici per sistemi biometrici Parte 2 | §0 | not required |
| 3 | yes | FORMULA_AMBIGUOUS | Classificatore biometrico - Multiclasse = Identificatore - Binario = Autenticato | §1, §1.1 | not required |
| 4 | yes | TEXT_OK | Il sistema biometrico visto come classificatore DataBase Acquisition Feature Ext | §1, §1.1 | not required |
| 5 | yes | TEXT_OK | SB = classif. DataBase Acquisition Feature Extraction Coding Matching Identifica | §1, §1.1 | not required |
| 6 | yes | TEXT_OK | Esperimenti di Bernoulli - Questi eventi sono esperimenti/prove di Bernoulli - " | §1, §1.1 | not required |
| 7 | yes | TEXT_OK | Come modelliamo gli errori? - Date due impronte generiche (genuini e/o impostori | §1, §1.1 | not required |
| 8 | yes | TEXT_OK | Distribuzione degli errori Similmente al problema famoso del «mezzo pollo statis | §1, §1.2 | not required |
| 9 | yes | TEXT_OK | Distribuzione di Bernoulli Es: probabilità di errore di autenticazione p = 0,01  | §1, §1.3 + PH | not required |
| 10 | yes | FORMULA_AMBIGUOUS | DESCRIVE ERRORI in IDENTIFICAZIONE / N utenti AUTENTICATI SB → Proc. Bernoulli  | §2, §2.1 | not required |
| 11 | yes | TEXT_OK | La roulette russa →sistema biometrico in ident. - Roulette russa - p errore= 1/6 | §2, §2.2 | not required |
| 12 | yes | TEXT_OK | Binomiale (distribuzione errori SB) Caso di 20 lanci di moneta normale p=0.5 e m | §2, §2.3 + PH | not required |
| 13 | yes | TEXT_OK | Binomiale p=0.01 >> k = 0:20; >> y = binopdf(k,20,0.01); >> plot(k,y,'+') Se l'e | §2, §2.3 + PH | not required |
| 14 | yes | TEXT_OK | Cosa stimare? Se ho un Sistema Biometrico (SB) con tasso di errore p (quindi dat | §3 | not required |
| 15 | yes | TEXT_OK | Regola dei 3 "Quale è il tasso di errore più basso p che può essere stimato con  | §4 | not required |
| 16 | yes | TEXT_OK | Estensione della regola dei 3 Se il sistema commette 5 errori su N prove indipen | §4 + PH | not required |
| 17 | yes | TEXT_OK | Errori da Verification a Identification "Quanto aumentano gli errori se uso un s | §5 | not required |
| 18 | yes | TEXT_OK | La regola dei 30 - La regola dei 30 è usata per determinare la larghezza del cam | §6 | not required |
| 19 | yes | TEXT_OK | Abbiamo visto molti argomenti teorici utili nei progetti biometrici - Non-ideali | §7 | not required |
| 20 | yes | TEXT_OK | Outline 1. Dispensa Best Pratices 2. Metodo per la progettazione e misura delle  | §7 | not required |
| 21 | yes | TEXT_OK | Altri errori: FTE e FTA Acquisition Module Feature Extraction Module DataBase Te | §7 | not required |
| 22 | yes | TEXT_OK | Zero-Effort attempts - Presentation attack Vanno previsti e dettagliati VEDREMO  | §7 | not required |
| 23 | yes | TEXT_OK | MEMO: Strategie di valutazione Technology - DB dati pubblici Test algoritmici. S | §7 | not required |
| 24 | yes | TEXT_OK | Dispensa Best Practices in Testing and Reporting Performance of Biometric Device | §8 | not required |
| 25 | yes | LIKELY_VISUAL | Parti molto utili | §8 + PH | not required |
| 26 | yes | TEXT_OK | REPORTING PERFORMANCE RESULTS (1) DATA REPORTING Esaminiamo i dettagli richiesti | §8 | not required |
| 27 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (1) DATA REPORTING | §8 | not required |
| 28 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (1) DATA REPORTING | §8 | not required |
| 29 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (1) DATA REPORTING | §8 | not required |
| 30 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (1) DATA REPORTING | §8 | not required |
| 31 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (1) DATA REPORTING | §8 | not required |
| 32 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (2) DATA REPORTING Sono dati molto importanti da c | §8 + PH | not required |
| 33 | yes | TEXT_OK | REPORTING PERFORMANCE RESULTS (2) DATA REPORTING Sono dati molto importanti da c | §8 | not required |
| 34 | yes | TEXT_OK | REPORTING PERFORMANCE RESULTS (2) DATA REPORTING Sono dati molto importanti da c | §8 | not required |
| 35 | yes | TEXT_OK | REPORTING PERFORMANCE RESULTS (2) DATA REPORTING Sono dati molto importanti da c | §8 | not required |
| 36 | yes | TEXT_OK | REPORTING PERFORMANCE RESULTS (2) DATA REPORTING Sono dati molto importanti da c | §8 | not required |
| 37 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (2) DATA REPORTING Sono dati molto importanti da c | §8 + PH | not required |
| 38 | yes | LIKELY_VISUAL | REPORTING PERFORMANCE RESULTS (2) DATA REPORTING Sono dati molto importanti da c | §8 | not required |
| 39 | yes | TEXT_OK | Procedure in caso di apprendimento (uso di Machine Learning) Come valutare corre | §9, §9.1 | not required |
| 40 | yes | TEXT_OK | Conoscenza nei sistemi biometrici Aspetti multi- disciplinari Pattern matching E | §9, §9.1 | not required |
| 41 | yes | TEXT_OK | Flusso di lavoro Usare i dati biometrici per creare impostori e genuini Allenare | §9, §9.1 | not required |
| 42 | yes | TEXT_OK | Comprare un sistema «of the shelf» con ML Si possono avere diversi casi - Avere  | §9, §9.1 | not required |
| 43 | yes | TEXT_OK | Come modelliamo gli errori se il classificatore apprende? - Se facciamo il class | §9, §9.2 | not required |
| 44 | yes | TEXT_OK | Problemi e soluzioni quando si usa Machine Learning La gestione dei dati quando  | §9, §9.2 | not required |
| 45 | yes | TEXT_OK | Quando creiamo più soluzioni con il machine learning, quale scegliamo? Potremmo  | §9, §9.2 | not required |
| 46 | yes | TEXT_OK | Data Sets e gestione delle Partizioni Quali sono le tecniche migliori per partiz | §10, §10.1 | not required |
| 47 | yes | TEXT_OK | Data Sets e Partizioni - Lo scopo finale della maggior parte dei processi di Mac | §10, §10.1 + PH | not required |
| 48 | yes | TEXT_OK | Creazione del Data Set - I dataset di "benchmark" sono usati con questo scopo: V | §10, §10.1 | not required |
| 49 | yes | TEXT_OK | Usare tutti i dati per train? NO! - Se usando tecniche di Machine Learning il da | §10, §10.2 | not required |
| 50 | yes | TEXT_OK | Soluzione: Data Set Partitioning L'esperimento rappresentato consiste nel rifare | §10, §10.2 + PH | not required |
| 51 | yes | TEXT_OK | Underfitting e Overfitting 1D Questo modello lineare non è sufficiente per impar | §10, §10.2 + PH | not required |
| 52 | yes | LIKELY_VISUAL | Underfitting e Overfitting 2D | §10, §10.2 + PH | not required |
| 53 | yes | LIKELY_VISUAL | Overfitting 2D (bis) | §10, §10.2 + PH | not required |
| 54 | yes | TEXT_OK | Se non faccio bene Data Set Partitioning Underfitting - Se usiamo anche tutti i  | §10, §10.3 | not required |
| 55 | yes | TEXT_OK | Se non faccio bene Data Set Partitioning Overfitting - Per evitare di continuare | §10, §10.3 | not required |
| 56 | yes | TEXT_OK | Data Set Partitioning - Necessario per : - Controllare la performance del modell | §10, §10.3 | not required |
| 57 | yes | LIKELY_VISUAL | Approccio generale: CrossValidazione (CV) ei = errore sul singolo gruppo | §11, §11.1 + PH | not required |
| 58 | yes | TEXT_OK | Quale è il concetto principale della Cross Validazione? - La cross-validazione è | §11, §11.1 | not required |
| 59 | yes | TEXT_OK | k-Fold Cross Validation (k-FCV) | §11, §11.2 | not required |
| 60 | yes | TEXT_OK | Data Set Partitioning: k-FCV - Il più comune metodo di cross validazione si chia | §11, §11.2 | not required |
| 61 | title only | LIKELY_VISUAL | k-FCV {e1, e2, … , ek } | §11, §11.2 + PH | not required |
| 62 | yes | TEXT_OK | Dagli errori →media, std Avendo fatto k test separati, possono studiare gli erro | §11, §11.2 | not required |
| 63 | yes | TEXT_OK | k-FCV: che valore scelgo per k? - Di solito si usano valori di k fra 5 e 10-12 - | §11, §11.2 | not required |
| 64 | yes | TEXT_OK | Stratified k-FCV - Il normale k-FCV può portare a sbilanciare la proporzione del | §11, §11.3 | not required |
| 65 | yes | TEXT_OK | Stratified k-FCV Esempio con k=5 Notare come la distribuzione delle classi (F e  | §11, §11.3 + PH | not required |
| 66 | yes | TEXT_OK | 5×2 Cross Validation (5x2 CV) | §11, §11.4 | not required |
| 67 | yes | TEXT_OK | Data Set Partitioning: 5 × 2 CV (5 times 2-fold Crossvalidation) - Il dataset vi | §11, §11.4 | not required |
| 68 | yes | LIKELY_VISUAL | Data Set Partitioning: 5×2 CV k=5 5 times 2-fold Crossvalidation Può essere una  | §11, §11.4 + PH | not required |
| 69 | yes | TEXT_OK | Ok, ma quale modello mando in deployment? Che tu abbia eseguito una delle tecnic | §11, §11.5 | not required |
| 70 | yes | LIKELY_VISUAL | Ok, ma quale modello mando in deployment? Tenere una valutazione finale! Altro m | §11, §11.5 + PH | not required |
| 71 | yes | TEXT_OK | Leave one out! (LOO) SAMPLE | §11, §11.6 | not required |
| 72 | yes | TEXT_OK | Data Set Partitioning: Leave One Out - In casi estremi si ha che k-FCV →k è ugua | §11, §11.6 | not required |
| 73 | title only | LIKELY_VISUAL | Data Set Partitioning: Leave one out | §11, §11.6 + PH | not required |
| 74 | yes | TEXT_OK | Leave one PERSON out! (LOPO) PERSON | §12 | not required |
| 75 | yes | TEXT_OK | Rivediamo il contesto - Abbiamo un sistema biometrico (per esempio una rete neur | §12 | not required |
| 76 | yes | TEXT_OK | Apprendimento e CrossValidation semplice… No good MIX Feature facciali o misura  | §12 | not required |
| 77 | yes | TEXT_OK | Leave One Person Out (LOPO) - L'apprendimento di una rete neurale a molti livell | §12 | not required |
| 78 | yes | LIKELY_VISUAL | LOPO L(NP)O MIX Ora è più difficile che impari a riconoscere solo dal colore dei | §12 + PH | not required |
| 79 | yes | TEXT_OK | Nuove Tecniche Modelli multimodali, Zero shot, Frewshot - Nelle prossime lezioni | §13 + PH | not required |
| 80 | yes | TEXT_OK | Punti principali visti - Il sistema biometrico visto come classificatore che sba | §14 | not required |
| 81 | yes | TEXT_OK | Appendici con il codice Non richiesto all'esame | §14 | not required |
| 82 | yes | TABLE_AMBIGUOUS | # Install required packages !pip install deepface !pip install opencv-python # I | §14 | not required |

### `Lezione_10b_Best Practices Biometrics_Dispensa.pdf` (36 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | NPL Report CMSC 14/02 Best Practices in Testing and Reporting Performance of Bio | §15 | not required |
| 2 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 NPL Report CMSC 14/02 © Crown Cop | §15 | not required |
| 3 | yes | TABLE_AMBIGUOUS | Biometric Testing Best Practices, Version 2.01 NPL Report CMSC 14/02 Best Practi | §15 | not required |
| 4 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 NPL Report CMSC 14/02 CONTENTS In | §15 | not required |
| 5 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 1 of 32 INTRODUCTION 1. This | §15 | not required |
| 6 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 2 of 32 7. Biometric technic | §16, §16.1 + PH | not required |
| 7 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 3 of 32 biometric features f | §16, §16.1, §16.2 | not required |
| 8 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 4 of 32 21. Operational eval | §16, §16.2, §16.3, §16.4 | not required |
| 9 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 5 of 32 33. False accept rat | §16, §16.4 | not required |
| 10 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 6 of 32 false non-matches ov | §16, §16.4, §16.5 | not required |
| 11 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 7 of 32 53. Impostor attempt | §16, §16.5, §16.6 | not required |
| 12 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 8 of 32 allowing performance | §16, §16.6 + PH | not required |
| 13 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 9 of 32 PLANNING THE EVALUAT | §17, §17.1, §17.2 | not required |
| 14 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 10 of 32 b. Factors controll | §17, §17.2, §17.3 | not required |
| 15 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 11 of 32 system performance  | §17, §17.3, §17.4 | not required |
| 16 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 12 of 32 So, for example, if | §17, §17.4 | not required |
| 17 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 13 of 32 c. Finally, once da | §17, §17.4, §17.5, §18, §18.1 | not required |
| 18 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 14 of 32 a. Volunteers using | §18, §18.1, §18.2 | not required |
| 19 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 15 of 32 b. In the case of o | §18, §18.2, §18.3 | not required |
| 20 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 16 of 32 Lighting "noise" is | §18, §18.3, §18.4 | not required |
| 21 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 17 of 32 119. For scenario e | §18, §18.4 | not required |
| 22 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 18 of 32 proportion of such  | §18, §18.4, §18.5 | not required |
| 23 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 19 of 32 speaker recognition | §18, §18.5 | not required |
| 24 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 20 of 32 If the answers to b | §18, §18.5, §19 | not required |
| 25 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 21 of 32 highly dependent up | §19 | not required |
| 26 | yes | FORMULA_AMBIGUOUS | Biometric Testing Best Practices, Version 2.01 Page 22 of 32 160. Frequently, th | §19, §20 | not required |
| 27 | yes | FORMULA_AMBIGUOUS | Biometric Testing Best Practices, Version 2.01 Page 23 of 32 ai number of false  | §20 | not required |
| 28 | yes | FORMULA_AMBIGUOUS | Biometric Testing Best Practices, Version 2.01 Page 24 of 32  = j ij i b d numb | §20 | not required |
| 29 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 25 of 32 Many bootstrap samp | §20, §21, §22 | not required |
| 30 | yes | TABLE_AMBIGUOUS | Biometric Testing Best Practices, Version 2.01 Page 26 of 32 181. However, we ho | §22 | not required |
| 31 | yes | TABLE_AMBIGUOUS | Biometric Testing Best Practices, Version 2.01 Page 27 of 32 [16] NEWMAN, H.H.,  | §22 | not required |
| 32 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 28 of 32 APPENDIX A – FACTOR | §23, §23.1 | not required |
| 33 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 29 of 32 TIME OF DAY: Behavi | §23, §23.1 | not required |
| 34 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 30 of 32 HATS, EARRINGS, SCA | §23, §23.1 | not required |
| 35 | yes | TEXT_OK | Biometric Testing Best Practices, Version 2.01 Page 31 of 32 197. Illumination O | §23, §23.1, §23.2 | not required |
| 36 | yes | FORMULA_AMBIGUOUS | Biometric Testing Best Practices, Version 2.01 Page 32 of 32 If n volunteers eac | §23, §23.2, §23.3 | not required |

**Totals:** 118/118 pages accounted for · 22 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione12 — DONE

**Lesson file(s):** `Lezione12/L12 - Acquisizione delle immagini biometriche - effetto delle ottiche, sensori e biometria multispettrale.md`

### `Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf` (109 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Acquisizioni di immagini biometriche: ottiche e sensori Fabio Scotti fabio.scott | §0 | not required |
| 3 | yes | TEXT_OK | Parte A Effetti delle ottiche sul riconoscimento del volto Fabio Scotti fabio.sc | §1 | not required |
| 4 | yes | TEXT_OK | Effetti delle ottiche sul riconoscimento del volto - Usiamo la libreria Deepface | §1 | not required |
| 5 | yes | TEXT_OK | Librerie # carichiamo le varie librerie necessarie !pip install deepface !pip in | §1 | not required |
| 6 | yes | LIKELY_VISUAL | Estraiamo gli embeddings … | §2 | not required |
| 7 | yes | LIKELY_VISUAL | Estraiamo gli embeddings … | §2 | not required |
| 8 | yes | LIKELY_VISUAL | Lista degli nostri embeddings … | §2 | not required |
| 9 | yes | LIKELY_VISUAL | Calcoliamo la distanza fra le immagini | §2 | not required |
| 10 | yes | LIKELY_VISUAL | Plot della matrice delle distanze | §2 + PH | not required |
| 11 | yes | TEXT_OK | Prendiamo la prima riga della matrice delle distanze | §2 | not required |
| 12 | yes | LIKELY_VISUAL | Esaminiamo l'angolo in basso | §2 + PH | not required |
| 13 | yes | TEXT_OK | Dist = 0.43 Dist = 0.83 f200, f24 Dist = 0.35 f24, f50 Dist = 0.24 | §2 + PH | not required |
| 14 | yes | FORMULA_AMBIGUOUS | Estendere lo studio con Distribuzioni e DET Cosa ci aspettiamo nel nostro DB ris | §3 + PH | not required |
| 15 | yes | TEXT_OK | Effetto dell'ottiche - Anche usando l'estrattore più potente della libreria VGG- | §3 | not required |
| 16 | yes | TEXT_OK | Compito «Effetto ottiche» Eseguire una analisi comparativa fra gli estrattori VG | §3 | not required |
| 17 | yes | TEXT_OK | Sensori e acquisizione biometrica L12b | §4 | not required |
| 18 | yes | TEXT_OK | Outline L12b 1. Introduzione alle applicazioni biometriche basate su immagini 2. | §4 | not required |
| 19 | yes | TEXT_OK | Approfondimento su ottica e dispositivi di acquisizione Ottica e dispositivi Pat | §4 | not required |
| 20 | title only | LIKELY_VISUAL | Setup e parametri per acquisizione di immagini biometriche | §4 | not required |
| 21 | yes | TEXT_OK | Acquisizioni immagini biom. Tratti fisiologici - Iride - Perioculare - Volto - I | §4 | not required |
| 22 | yes | TEXT_OK | Setup per acquisizione delle immagini: elementi principali 1) Camera 6) Illumina | §4 + PH | not required |
| 23 | yes | TEXT_OK | Esempi di setup «semplici» 1 Sensore + ottiche 1 illuminatore (non si vedono ma  | §4 | not required |
| 24 | yes | TEXT_OK | Image acquisition: il primo passo nella catena di elaborazione Image Acquisition | §4 | not required |
| 25 | yes | LIKELY_VISUAL | Il pixel - Un'immagine digitale è una rappresentazione di un'immagine bidimensio | §5, §5.1 | not required |
| 26 | yes | TEXT_OK | Image acquisition 8bit <->Unsigned char Rappresentazione compatta ma potente Sin | §5, §5.1 | not required |
| 27 | yes | LIKELY_VISUAL | Numero di bit 4 bits - Uniform quantization 4 bits – Optimal quantization 8 bits | §5, §5.1 | not required |
| 28 | yes | TEXT_OK | Gli spazi colore CIE - Nel 1931 la Commission Internationale de l'Eclairage (Com | §5, §5.2 | not required |
| 29 | yes | TEXT_OK | Numero di bit/pixel →Numero dei colori # di colori 10M Colori Occhio Umano «I no | §5, §5.2 | not required |
| 30 | yes | LIKELY_VISUAL | Effetti quantizzazione (8 bit ok?) | §5, §5.3 | not required |
| 31 | yes | LIKELY_VISUAL | Rumore da quantizzazione Molti algoritmi di enhancement biometrici esaltano le d | §5, §5.3 + PH | not required |
| 32 | yes | LIKELY_VISUAL | Effetti sul rumore delle immagini 8-10bit/pixel | §5, §5.3 | not required |
| 33 | yes | LIKELY_VISUAL | Esempio di comparazione fra sensori | §5, §5.4 | not required |
| 34 | yes | LIKELY_VISUAL | Comparazione di sensori (2) | §5, §5.4 | not required |
| 35 | yes | TEXT_OK | Comparazione di sensori (3) Filtraggio →Vein pattern (immagine 10bit) Web cam Mi | §5, §5.4 + PH | not required |
| 36 | yes | TEXT_OK | Immagini da sensori contatto (impronte e palmi) Alcune feature importanti del se | §5, §5.4 | not required |
| 37 | yes | TEXT_OK | Anche da scanner per impronte latenti Qualità impronte latenti bassa →gamma crit | §5, §5.4 | not required |
| 38 | yes | TEXT_OK | Riassumendo per i sensori Risoluzione [pixel x pixel] #bit colore/pixel [bit] Fr | §5, §5.5 + PH | not required |
| 39 | yes | TEXT_OK | Riassumendo (tutto) - Immagini più larghe-risolute →acquisisco più tratti - Imma | §5, §5.5 | not required |
| 40 | yes | LIKELY_VISUAL | Tecnologie dei sensori Come scegliere il sensore in ambito biometrico | §6, §6.1 | not required |
| 41 | yes | TEXT_OK | Sensori CCD principio - Charge-Coupled Device (CCD): funzionamento simile ad un  | §6, §6.1 | not required |
| 42 | yes | LIKELY_VISUAL | CCD sensor Notare la presenza di unico amplificatore →Collo di bottiglia →Ma gra | §6, §6.1 + PH | not required |
| 43 | yes | TEXT_OK | Sensori: CCD e CMOS CMOS: Complementary Metal-Oxide Semiconductor. Ogni singolo  | §6, §6.1 + PH | not required |
| 44 | yes | TEXT_OK | Global VS Rolling Shutter - Global Shutter (CCD e alcuni CMOS) Tutti i pixel sul | §6, §6.2 | not required |
| 45 | yes | LIKELY_VISUAL | Pros-Cons dei CMOS | §6, §6.3 + PH | not required |
| 46 | yes | TEXT_OK | Smear e Blooming nei CCD e CMOS Smear: 1. Il "Smear" si verifica quando una stri | §6, §6.3 | not required |
| 47 | yes | TEXT_OK | Rumore tipico del CMOS Il rumore di pattern proveniente dal sensore CMOS risulta | §6, §6.3 | not required |
| 48 | yes | TEXT_OK | CCD o CMOS? - CMOS sta diventando sempre più popolare, ma il CCD è lontano dall' | §6, §6.4 | not required |
| 49 | yes | LIKELY_VISUAL | CMOS! Il trend dei CMOS è in costante ascesa | §6, §6.4 + PH | not required |
| 50 | yes | TEXT_OK | Tecnologia: microlenti e filtri RGB Delle microlenti (condensatori di luce) sono | §6, §6.5 | not required |
| 51 | yes | LIKELY_VISUAL | CMOS struttura Due pixel dedicati al verde! Canale VERDE molto interessante per  | §6, §6.5 + PH | not required |
| 52 | yes | TEXT_OK | CMOS GLOBAL 128MP! (costoso…) IMX661 uno dei più grandi sensori al mondo Global  | §6, §6.6 | not required |
| 53 | yes | TEXT_OK | Rolling shutter (tipico x CMOS grandi o economici) La esposizione avviene non si | §7, §7.1 | not required |
| 54 | yes | TEXT_OK | Rolling vs global shutter GLOBAL ROLLING ROLLING (SLOW) ROLLING (VERY FAST) ROLL | §7, §7.1 + PH | not required |
| 55 | yes | TEXT_OK | Effetti negativi del rolling shutter 1. Wobble (jello effect) 2. Skew 3. Spatial | §7, §7.1, §7.2 | not required |
| 56 | yes | TEXT_OK | Rolling shutter: wobble-jello - Wobble-jello effect: appare quando la camera vib | §7, §7.2 | not required |
| 57 | yes | LIKELY_VISUAL | Rolling shutter: Skew l'immagine si piega diagonalmente in una direzione mentre  | §7, §7.2 + PH | not required |
| 58 | yes | TEXT_OK | Rolling shutter: Spatial aliasing (CCD e CMOS) I pixel verticali sono campionati | §7, §7.2 + PH | not required |
| 59 | yes | TEXT_OK | Rolling shutter: Temporal aliasing Temporal aliasing Avvengono movimenti signifi | §7, §7.2 + PH | not required |
| 60 | yes | TEXT_OK | Correzione effetti del rolling shutter via algoritmica E' possibile correggere l | §7, §7.3 | not required |
| 61 | yes | LIKELY_VISUAL | CMOS con Shutter lento CMOS Image Sensor (CIS) Interface | §7, §7.3 + PH | not required |
| 62 | yes | TEXT_OK | CMOS veloci CMOS image sensor (CIS) CMOS veloci stanno di fatto raggiungendo i C | §7, §7.3 | not required |
| 63 | yes | TEXT_OK | Sony's 3-layer stacked CMOS image sensor technology 120 fps for all 19.3M pixels | §7, §7.3 | not required |
| 64 | yes | TEXT_OK | Dimensioni sensore →ottica Type Diagonal (mm) Width (mm) Canon EF-S, APS-C 26.82 | §8, §8.1 | not required |
| 65 | yes | LIKELY_VISUAL | Casing Canon 600D T3i PIEZO CLEANING | §8, §8.1 | not required |
| 66 | yes | TEXT_OK | Low pass filter Un filtro passa-basso, noto anche come filtro anti- aliasing o " | §8, §8.1 + PH | not required |
| 67 | yes | TEXT_OK | Stabilizzatori: tecnologia Oppo Five-axis optical image stabilization La tecnolo | §8, §8.2 | not required |
| 68 | yes | TEXT_OK | Sensore >100Mpixel - Samsung ISOCELL Bright HMX (in collaborazione con Xiaomi) 1 | §8, §8.3 | not required |
| 69 | yes | TEXT_OK | Significato biometrico (@108MP) - 30fpsx108Mpixel x 8bit = 3.24GB/s = 25.924Gb/s | §8, §8.3 + PH | not required |
| 70 | yes | TEXT_OK | Uno sguardo al mercato Samusung S20 Ultra, Xiaomi Mi Note 10, 11, … Mi Mix 4, S2 | §8, §8.3 | not required |
| 71 | yes | LIKELY_VISUAL | 600Mpixel! ISOCELL 600 MP In sviluppo…. Diagonale maggiore del 108MP Spesso 22mm | §8, §8.3 + PH | not required |
| 72 | yes | TEXT_OK | In sintesi FINE Abbiamo visto: – Acquisizione delle immagini – Risoluzione/Spazi | §8, §8.4 | not required |
| 73 | yes | LIKELY_VISUAL | Radiazione e Ottica Image acquisition – Multispectral biometrics L14 | §9 | not required |
| 74 | title only | LIKELY_VISUAL | Electromagnetic spectrum | §9 | not required |
| 75 | title only | LIKELY_VISUAL | Band selection X-Ray UV Visible Infrared Radio Composite | §9 + PH | not required |
| 76 | yes | TEXT_OK | Filtri ottici - Strumento che trasmette selettivamente la luce con particolari p | §9 | not required |
| 77 | yes | TEXT_OK | Filtro polarizzatore - Usati per la normale fotografia applicati alla biometria  | §10 + PH | not required |
| 78 | yes | LIKELY_VISUAL | Uso del polarizzatore - Esterno - Interno Sorveglianza! filetto | §10 | not required |
| 79 | yes | LIKELY_VISUAL | Filtro polarizzatore applicato alla sorveglianza | §10 + PH | not required |
| 80 | yes | TEXT_OK | Applicazione filtro polarizzatore alla biometria - L'uso del polarizzatore nelle | §10 + PH | not required |
| 81 | yes | LIKELY_VISUAL | Applicazione biometrica del sensore polarizzato SONY Sensore senza polarizzatore | §10 + PH | not required |
| 82 | yes | LIKELY_VISUAL | Filtro multi banda - Con un solo filtro possiamo permettere il passaggio di dive | §11, §11.1 | not required |
| 83 | yes | TEXT_OK | VIS + NIR (separati) Per cogliere nuove feature biometriche, rendere il sistema  | §11, §11.1 | not required |
| 84 | yes | TEXT_OK | Multispectral in biometria Mid-Wave Infrared (MWIR) band. "Multispectral Imagery | §11, §11.2 | not required |
| 85 | yes | TEXT_OK | Multispectral in biometria (2) Short-Wave Infrared Face Images captured when usi | §11, §11.2 + PH | not required |
| 86 | yes | LIKELY_VISUAL | Il mondo del IR in biometria Visible Near IR ShortWave IR Mid-Wave IR Long-Wave  | §11, §11.2 + PH | not required |
| 87 | yes | TEXT_OK | I canali RGB come filtri I canali stessi della telecamera sono di fatto dei filt | §11, §11.3 | not required |
| 88 | yes | LIKELY_VISUAL | Esempio: iris recognition Dal NIR Occhio scuro | §11, §11.3 + PH | not required |
| 89 | yes | LIKELY_VISUAL | Esempio: iris recognition (2) Occhio chiaro | §11, §11.3 | not required |
| 90 | yes | TEXT_OK | Esempio: iris recognition (3) Occhio chiaro Feature circolari dal canale BLU Fea | §11, §11.3 + PH | not required |
| 91 | yes | TEXT_OK | Day/Night surveillance Es: IR cut-off filter exchanger with integrated lens moun | §11, §11.4 | not required |
| 92 | yes | TEXT_OK | Multispectral imaging per impronte Multispectral imaging sensor with advanced se | §12 + PH | not required |
| 93 | yes | TEXT_OK | Multispectral biometrics - Eye Detection, iris biometrics - Face Recognition fuo | §12 | not required |
| 94 | yes | LIKELY_VISUAL | Sistemi di acquisizione per la biometria - Regolazioni avanzate - Casi d'uso L12 | §13 | not required |
| 95 | yes | LIKELY_VISUAL | Un esempio di sistema general purpose con zoom ottico Per applicazioni low cost | §13 | not required |
| 96 | yes | LIKELY_VISUAL | Esempio 1 1-8 Mpixel zoom camera 150 euro | §13 + PH | not required |
| 97 | yes | LIKELY_VISUAL | 1-8 Mpixel zoom camera Applicazioni | §13 | not required |
| 98 | yes | LIKELY_VISUAL | 1-8 Mpixel zoom camera 2640x2448 Provided | §13 | not required |
| 99 | yes | TEXT_OK | Esempio 2: Tamron MP3010M-EV Camera Module with 10x Optical Zoom Tamron's compac | §13 | not required |
| 100 | yes | LIKELY_VISUAL | MP3010M-EV Tamron | §13 + PH | not required |
| 101 | yes | LIKELY_VISUAL | Sistemi multicamera Esempi nel settore mobile e applicazioni di sicurezza | §14 | not required |
| 102 | yes | TEXT_OK | Disponibilità di ottiche: settore mobile Iphone X - Ottiche per panoramica - Ott | §14 + PH | not required |
| 103 | yes | TEXT_OK | 3 cameras: Huawei P20 Pro, Galaxy A7 - 24-megapixel sensor with autofocus, - 5-m | §14 | not required |
| 104 | yes | TEXT_OK | BioEnable EF-45 Iris recon. sys. - Ottica e illuminatore per - Volto (RGB) per « | §14 + PH | not required |
| 105 | yes | TEXT_OK | CONCLUSIONE | §15 | not required |
| 106 | yes | TEXT_OK | Tante opzioni per tue nuove applicazioni! - Dispositivi CMOS ad alta risoluzione | §15 | not required |
| 107 | yes | TEXT_OK | Non all'esame ma interessante.. - In questo video Derek Muller (Ph.D) discute la | §15 | not required |
| 108 | yes | TEXT_OK | Inizio della breve simulazione di esame inerente alle ottiche e sensori - NOTA:  | §15 | not required |
| 109 | yes | TEXT_OK | In sintesi FINE Abbiamo visto: – Luce e radiazione elettromagnetica – Filtri e b | §15, §17 | not required |

**Totals:** 109/109 pages accounted for · 35 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione13 — DONE

**Lesson file(s):** `Lezione13/L13 - Difesa della privacy, protezione del template e crittografia biometrica.md`

### `Lezione_13_Difesa_della_Privacy_Crittazione.pdf` (111 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Inizio della breve simulazione di esame inerente alle ottiche e sensori - NOTA:  | §0 | not required |
| 3 | yes | TEXT_OK | Difesa della Privacy, Protezione del template Fabio Scotti fabio.scotti@unimi.it | §0 | not required |
| 4 | yes | TEXT_OK | Outline 1. Difesa della Privacy and linee guida 2. Biometria cancellabile - Perm | §0 | not required |
| 5 | yes | LIKELY_VISUAL | Uno degli aspetti di progettazione dei sistemi biometrici | §0 + PH | not required |
| 6 | yes | TEXT_OK | 1. Privacy-protective applications ₋ La biometria protegge le informazioni perso | §2 | not required |
| 7 | yes | TEXT_OK | 7 domande Segue un breve elenco di domande ci aiuterà a comprendere i livelli di | §3.1 | not required |
| 8 | yes | TEXT_OK | Is the system deployed overtly or covertly? Overt Covert ! Privacy Privacy++ | §3.1 | not required |
| 9 | yes | TEXT_OK | Is the system optional or mandatory? Optional Mandatory ! Privacy Privacy++ | §3.1 | not required |
| 10 | yes | TEXT_OK | Is the system used for Identification or Verification Verification Identificatio | §3.1 | not required |
| 11 | yes | TEXT_OK | Is the system deployed in the private or public sector? Private Sector Customer  | §3.1 | not required |
| 12 | yes | TEXT_OK | In what role is the user interacting with the system? Individual Customer Employ | §3.1 | not required |
| 13 | yes | TEXT_OK | Where is the biometric data stored? Personal Storage Database Storage ! Privacy  | §3.1 | not required |
| 14 | yes | TEXT_OK | What type of biometric technology is being deployed? Behavioral Physiological !  | §3.1 | not required |
| 15 | yes | TEXT_OK | Does the system use templates, samples or both? Templates Sample/ Images ! Priva | §3.1 | not required |
| 16 | yes | LIKELY_VISUAL | Lista completa Privacy++ ! Privacy | §3.1 + PH | not required |
| 17 | yes | TEXT_OK | Discussione di un caso di privacy - Dal 1/12/2019 in Cina chi vuole acquistare u | §3.4 | not required |
| 18 | yes | TEXT_OK | GDPR - La discussione delle norme e della loro applicazione del Regolamento gene | §5.1 | not required |
| 19 | yes | TEXT_OK | EU AI ACT →biometria Sistemi proibiti e ad alto rischio - L'articolo 5(g) proibi | §5.2 | not required |
| 20 | yes | TEXT_OK | EU AI ACT →biometria - Questa distinzione può portare a confusione, in particola | §5.2 | not required |
| 21 | yes | TEXT_OK | Divieto di categorizzazione biometrica Dal 2 agosto 2026 è VIETATO usare sistemi | §5.2 | not required |
| 22 | yes | TEXT_OK | Applicazioni permesse ma regolati (alto rischio) - Applicazioni come - la stima  | §5.2 | not required |
| 23 | yes | TEXT_OK | Emotion recognition: caso speciale - L'AI Act tratta emotion recognition quasi c | §5.2 | not required |
| 24 | yes | TEXT_OK | EU AI ACT, biometria: Critiche https://edri.org/our-work/how-to-fight-biometric- | §5.3 | not required |
| 25 | yes | TEXT_OK | EU AI ACT, biometria: Critiche (2) https://www.article19.org/resources/eu-ai-act | §5.3 | not required |
| 26 | yes | TEXT_OK | Biometrics privacy (1) - Controllo sull'uso e sulla divulgazione dell'identità p | §1 | not required |
| 27 | yes | LIKELY_VISUAL | Biometrics privacy (2) | §1 + PH | not required |
| 28 | yes | TEXT_OK | Basta usare solo i templates? - E' vero che potrebbe aiutare perché diminuisce l | §6.1 | not required |
| 29 | yes | TEXT_OK | Template →sample Non è detto che memorizzare solo i template e non i sample sia  | §6.2 + PH | not required |
| 30 | yes | LIKELY_VISUAL | Protezione del template Approcci specifici sul template biometrico | §6.3 | not required |
| 31 | yes | TEXT_OK | Classical password protection Hash (PASSWORD) PASSWORD Hash (PASSWORD) PASSWORD  | §6.3 + PH | not required |
| 32 | yes | TEXT_OK | Attenzione a questa distinzione BIOMETRIA CANCELLABILE Rimango nello stesso spaz | §6.4 + PH | not required |
| 33 | yes | LIKELY_VISUAL | Biometria cancellabile Prima soluzione per la privacy sul dato | §7.1 | not required |
| 34 | yes | TEXT_OK | Biometria cancellabile, contesto - Se rubano - la password →la cambio, - il toke | §7.1 | not required |
| 35 | yes | TEXT_OK | Biometria cancellabile: definizione La distorsione intenzionale e sistematicamen | §7.1 | not required |
| 36 | yes | TEXT_OK | Biometria cancellabile/rinnovabile: attuazione della protezione - Se una biometr | §7.1 | not required |
| 37 | yes | TEXT_OK | 4 obiettivi principali - Diversità - Non deve essere usata lo stesso template ca | §7.2 | not required |
| 38 | footer only | LIKELY_VISUAL | (no text) | §7.3 + PH | not required |
| 39 | yes | TEXT_OK | Feature transformation - Bio-hashing (Salting) - The transformation function is  | §7.3 | not required |
| 40 | yes | LIKELY_VISUAL | Aggiungo del «rumore» con una chiave K | §7.3 | not required |
| 41 | yes | LIKELY_VISUAL | Feature trasformation: Salting Segreta! Segreta! | §7.3 + PH | not required |
| 42 | yes | TEXT_OK | Problemi del salting - La funzione F() è invertibile! Se perdo la chiave K, ho c | §7.3 | not required |
| 43 | yes | TEXT_OK | Applicazione biometria cancellabile: Permutazioni Iriscode "se mi rubano il mio  | §8 + PH | not required |
| 44 | yes | TEXT_OK | Applicazione biometria cancellabile: Permutazioni Iriscode(2) "se mi rubano il m | §8 | not required |
| 45 | yes | TEXT_OK | Permutazioni Iriscode (cont.) Esempio di un iriscode di 5 bit Iriscode = [b1, b2 | §8 + PH | not required |
| 46 | yes | TEXT_OK | Cambio della Permutazione per protezione da Furto del Template Schema di permuta | §8 + PH | not required |
| 47 | yes | LIKELY_VISUAL | Uso funzioni non invertibili! " many-to-one function" | §9 + PH | not required |
| 48 | yes | TEXT_OK | Uso funzioni non invertibili! " many-to-one function" 4 = (2^2) ? 4 = (-2^-2) ?  | §9 | not required |
| 49 | yes | TEXT_OK | Trasformazione non invertibile Il modello biometrico acquisito viene trasformato | §9 | not required |
| 50 | yes | TEXT_OK | Trasformazione delle feature non invertibile 1 solo dato estratto La trasformazi | §9 + PH | not required |
| 51 | yes | TEXT_OK | Esempio sul volto morphing non invertibile | §9 | not required |
| 52 | yes | LIKELY_VISUAL | Volto: morphing non invertibile | §9 + PH | not required |
| 53 | yes | TEXT_OK | Esempio sulle impronte: deformazione geometrica coordinate minutiae Estrattore m | §9 + PH | not required |
| 54 | yes | LIKELY_VISUAL | Biometric Encryption Crittosistemi biometrici →il template biometrico deve diven | §10.1 | not required |
| 55 | yes | TEXT_OK | Approcci di Biometric Encryption (BE) NOTA: Rispetto alle tecniche appena viste  | §10.1 | not required |
| 56 | yes | TEXT_OK | Biometric Encryption (BE) Is a group of emerging technologies that securely - bi | §10.1 | not required |
| 57 | yes | TEXT_OK | Biometric Encryption: caratteristiche - Unlike conventional cryptography, this " | §10.1 | not required |
| 58 | yes | TEXT_OK | Biometric cryptosystem (helper data protection methods) - Depending on how the h | §10.2 | not required |
| 59 | footer only | LIKELY_VISUAL | (no text) | §10.3 + PH | not required |
| 60 | yes | TEXT_OK | Key Binding La chiave k è legata ad una trasformazione del template b con un cod | §10.3 + PH | not required |
| 61 | yes | LIKELY_VISUAL | Esempio di schema completo di key binding (non all'esame) Il trucco è tutto qua! | §10.3 + PH | not required |
| 62 | footer only | LIKELY_VISUAL | (no text) | §10.4 + PH | not required |
| 63 | yes | LIKELY_VISUAL | Helper data Helper data (può essere pubblico!) ATT! Entra solo il template! Nota | §10.4 + PH | not required |
| 64 | yes | TEXT_OK | Biometric cryptosystem (helper data protection methods) - In this case the helpe | §10.4 | not required |
| 65 | yes | LIKELY_VISUAL | Riassunto dei metodi di protezione standard | §10.5 + PH | not required |
| 66 | yes | TEXT_OK | Altre tecniche di protezione della privacy dei template biometrici - Feature tra | §10.5 | not required |
| 67 | yes | LIKELY_VISUAL | Elaborazione biometrica nel dominio crittato BIOMETRIC | §11 + PH | not required |
| 68 | yes | LIKELY_VISUAL | DEVO USARE MATCHER SPECIALI Elaborazione nel dominio crittato | §11 + PH | not required |
| 69 | yes | TEXT_OK | Elaborazione del dominio crittato (il server non sa cosa sta facendo) - Solo ope | §11 | not required |
| 70 | yes | LIKELY_VISUAL | Dispensa su questi temi | §11 | not required |
| 71 | yes | LIKELY_VISUAL | Esempi avanzati di trasfomazioni Per la protezione dei template | §12.1 + PH | not required |
| 72 | yes | TEXT_OK | Come usare la biometria come chiave crittografica OBIETTIVO: generare un identif | §12.1 | not required |
| 73 | yes | TEXT_OK | Un esempio di possible soluzione IrideSX+IrideDX - Crea un'identificazione biome | §12.1 | not required |
| 74 | yes | TEXT_OK | Idea: "critto" un occhio con l'altro Semplificando molto, l'idea e' la seguente  | §12.1 + PH | not required |
| 75 | yes | TEXT_OK | Contesto: biometric cryptosystem (helper data protection methods) - Al posto che | §12.1 | not required |
| 76 | yes | FORMULA_AMBIGUOUS | IrideSX+IrideDX Enroll Error Correction Encoding Hash Function Pseudo-Random Per | §12.2 + PH | not required |
| 77 | yes | FORMULA_AMBIGUOUS | IrideSX+IrideDX Verification I1' δ Error Correction Decoding Inverse Pseudo-Rand | §12.2 + PH | not required |
| 78 | yes | TEXT_OK | Esempio di risultati: CASIA dataset - CASIA dataset (version 1.0) which contains | §12.2 | not required |
| 79 | yes | TEXT_OK | Implementation: the iris templates (2) - Daugman method for creating the Iriscod | §12.2 | not required |
| 80 | yes | TEXT_OK | Implementation: the iris templates (2) - Template I1: the right eye with 9600 bi | §12.2 | not required |
| 81 | yes | FORMULA_AMBIGUOUS | Implementation: the codes - Error correction code: - Reed-Solomon [9600,1920,768 | §12.2 + PH | not required |
| 82 | yes | TEXT_OK | Implementation: enroll and authentication procedures Enroll: Best of three (qual | §12.2 | not required |
| 83 | yes | TEXT_OK | Performance (concetti in esame) Esempio di fusione biometrica con metodi crittog | §12.3 + PH | not required |
| 84 | yes | TEXT_OK | Conclusioni sulla applicazione della biometria come chiave - Lo schema di esempi | §12.3 | not required |
| 85 | yes | TEXT_OK | Primi esempi commerciali: ZeroBiometrics (Face) https://zerobiometrics.com/ - Ze | §13 | not required |
| 86 | yes | TEXT_OK | Decentralized Biometric Cryptosystem (DBC) - Nei DBC l'informazione biometrica n | §13 | not required |
| 87 | yes | TEXT_OK | Decentralized Biometric Cryptosystem (DBC) - Esempi - Applicazioni web3.0 costru | §13 | not required |
| 88 | yes | TEXT_OK | Feature di Privacy Protection nei SW biometrici da ricercare Come progettista di | §13 | not required |
| 89 | yes | TEXT_OK | Bias e fairness nei modelli pre-addestrati ATTENZIONE! Non sono ancora molto dif | §14 | not required |
| 90 | yes | TEXT_OK | Esempi di bias osservati - Google – FaceNet FMR più alto per: - donne - pelle sc | §14 + PH | not required |
| 91 | yes | TEXT_OK | Cosa significa Leakage informativo - Se trovi in un file che dovrebbe essere usa | §14 | not required |
| 92 | yes | TEXT_OK | Molto importante! Privacy →Vettori estratti - "Un buon template biometrico deve | §14 | not required |
| 93 | yes | TEXT_OK | Discussione della privacy biometrica degli utenti nei social media Quanto è preo | §15 | not required |
| 94 | yes | TEXT_OK | Caso di studio: riconoscimento di Sharbat Gula mediante iride Note di John Daugm | §15 + PH | not required |
| 95 | yes | TEXT_OK | Problemi di privacy? SI!! - L'exploit di Daugman (riconoscimento con iride da fo | §15 | not required |
| 96 | yes | TEXT_OK | Possibile soluzioni: IRIDE SINTETICA DEEP (CNN generator) IRIDE LINEARIZZATA Ese | §15 + PH | not required |
| 97 | yes | TEXT_OK | Puoi fare poco quando sono gli utenti stessi… - "Facebook 10 Year Challenge", un | §15 | not required |
| 98 | yes | TEXT_OK | Rimozione della Personally Identifiable Information - Nella gestione di dati per | §15 | not required |
| 99 | yes | TEXT_OK | Smart Anonymization (I-ID) https://www.deidentification.co/smart-video-anonymiza | §15 + PH | not required |
| 100 | yes | LIKELY_VISUAL | Comparazione dei possibili scenari Features of biometric systems for heterogenou | §15 + PH | not required |
| 101 | yes | TEXT_OK | Inferenze Comportamentali e Stato Interno: Rischio Privacy Negli ultimi anni, mo | §16 | not required |
| 102 | yes | TEXT_OK | Caso Uiguri Etnia turcofona di religione islamica che vive nel nord-ovest della  | §16 | not required |
| 103 | yes | TEXT_OK | Caso Uiguri - SenseTime, China's largest facial recognition startup, claims to h | §16 | not required |
| 104 | yes | TEXT_OK | Attenzione alla normativa (moratoria ora terminata) - La moratoria che vietava i | §17 | not required |
| 105 | yes | TEXT_OK | Nuovo Decreto Sicurezza Febbraio 2026 il Governo ha approvato nuove norme che co | §17 | not required |
| 106 | yes | TEXT_OK | La posizione del Garante Privacy (per l'italia) Settore Privato - Per i soggetti | §17 | not required |
| 107 | yes | TEXT_OK | Particolare attenzione ai segnali fisiologici e "Heart biometrics"! Il tuo stato | §18 + PH | not required |
| 108 | yes | TEXT_OK | Humor ? x Buongiorno, Le comunichiamo che il suo premio della sulla «X health in | §18 | not required |
| 109 | yes | TEXT_OK | Raccomandazioni di buon senso e «Sharenting» - Di particolare efficacia (quasi d | §18 | not required |
| 110 | yes | TEXT_OK | Sommario 1. Difesa della Privacy and linee guida 2. Biometria cancellabile - Per | §19 | not required |
| 111 | yes | TEXT_OK | Considerazioni finali - La protezione a livello di template biometrico - è un bu | §19 | not required |

### `Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf` (18 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Ruggero Donida Labati, | §1 | not required |
| 2 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies intelligence, etc.); l | §1 | not required |
| 3 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Fig. 1. Points of atta | §1 + PH | not required |
| 4 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies It is possible to dist | §3.1 | not required |
| 5 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Table 1. Applicative a | §3.1, §3.2 | not required |
| 6 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies identification are the | §2, §3.2, §3.3 | not required |
| 7 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Fig. 2. Evaluative asp | §2, §3.3, §4 + PH | not required |
| 8 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies and frame sequences) a | §4, §6.4 | not required |
| 9 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Fig. 3. Cancelable bio | §6.4, §7.1 + PH | not required |
| 10 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Usually, systems based | §7.3 | not required |
| 11 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Fig. 4. Key-binding bi | §10.1, §10.3 + PH | not required |
| 12 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Fig. 5. Key generating | §10.3, §10.4 + PH | not required |
| 13 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies all the properties of  | §10.4, §11 | not required |
| 14 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Fig. 6. Example of a c | §11 + PH | not required |
| 15 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies Conclusions Relevant p | §19 | not required |
| 16 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies fingerprint recognitio | §20 | not required |
| 17 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies 20. Fontaine, C., Gala | §20 | not required |
| 18 | yes | TEXT_OK | Biometric Privacy Protection: Guidelines and Technologies 39. Nanni, L., Lumini, | §20 | not required |

**Totals:** 129/129 pages accounted for · 41 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione14 — DONE

**Lesson file(s):** `Lezione14/L14 - Accesso fisico ad elevata sicurezza e Automated Border Control.md`

### `Lezione_14_DispensaABC.pdf` (39 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | A Biometric Recognition in Automated Border Control: A Survey RUGGERO DONIDA LAB | §9 | not required |
| 2 | yes | TEXT_OK | A:2 DONIDA LABATI et al. on improving the efficiency and effectiveness of the ch | §9 | not required |
| 3 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:3 Fig. 1. ABC process steps. | §9, §11.1 + PH | not required |
| 4 | yes | TEXT_OK | A:4 DONIDA LABATI et al. (a) (b) Fig. 2. Structure of an e-Gate (a) and deployme | §11.1 + PH | not required |
| 5 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:5 Fig. 3. Logical architectu | §11.1, §12 + PH | not required |
| 6 | yes | TEXT_OK | A:6 DONIDA LABATI et al. (a) (b) (c) Fig. 4. Topologies of an e-Gate: mantrap fo | §11.1, §12, §13 + PH | not required |
| 7 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:7 producing electronic recor | §11.2, §13 | not required |
| 8 | yes | TEXT_OK | A:8 DONIDA LABATI et al. (PKI) cryptographic technology, and the chip implements | §11.2, §14.1 | not required |
| 9 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:9 — Biometric sample capture | §14.1 | not required |
| 10 | yes | TEXT_OK | A:10 DONIDA LABATI et al. Several factors can deteriorate the quality of a biome | §14.1, §14.2 | not required |
| 11 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:11 (a) Fig. 5. Face recognit | §14.2 | not required |
| 12 | yes | TEXT_OK | A:12 DONIDA LABATI et al. ever, a number of works have assessed specific measure | §14.2 | not required |
| 13 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:13 component analysis [Turk  | §14.2 | not required |
| 14 | yes | TEXT_OK | A:14 DONIDA LABATI et al. (a) (b) Fig. 6. Fingerprint recognition at the e-Gate: | §14.2, §14.3 + PH | not required |
| 15 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:15 Factors that influence fi | §14.3 | not required |
| 16 | yes | FORMULA_AMBIGUOUS | A:16 DONIDA LABATI et al. are usually adopted to support the methods based on mi | §14.3, §14.4 | not required |
| 17 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:17 Fig. 7. Iris recognition  | §14.4 + PH | not required |
| 18 | yes | TEXT_OK | A:18 DONIDA LABATI et al. and camera effects (e.g., insufficient lighting, defoc | §14.4 | not required |
| 19 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:19 the size of the pupil, th | §14.4, §14.6 | not required |
| 20 | yes | TEXT_OK | A:20 DONIDA LABATI et al. for border check applications (further discussion in S | §14.6 | not required |
| 21 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:21 the ABC4EU project, we ha | §14.6, §14.7 | not required |
| 22 | yes | TEXT_OK | A:22 DONIDA LABATI et al. Fig. 8. Biometric traits used at ABC systems deployed  | §14.7, §15 + PH | not required |
| 23 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:23 possible attacks on biome | §15 | not required |
| 24 | yes | TEXT_OK | A:24 DONIDA LABATI et al. 4.1.1. Challenges and design considerations for passen | §15 | not required |
| 25 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:25 — People with total visua | §15 | not required |
| 26 | yes | TEXT_OK | A:26 DONIDA LABATI et al. 4.2. Evaluation of the biometric recognition The evalu | §15 | not required |
| 27 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:27 processed by ABC systems  | §15 | not required |
| 28 | yes | TEXT_OK | A:28 DONIDA LABATI et al. Fig. 9. Vulnerabilities of the biometric system of an  | §15 + PH | not required |
| 29 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:29 — different sensors: use  | §15 | not required |
| 30 | yes | TEXT_OK | A:30 DONIDA LABATI et al. a user does not want to be recognized anymore, and the | §15 | not required |
| 31 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:31 it is the right of the in | §15, §17 | not required |
| 32 | yes | TEXT_OK | A:32 DONIDA LABATI et al. A. Acquisti, S. Gritzalis, C. Lambrinoudakis, and S. D | §18 | not required |
| 33 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:33 A. M. Burrows and J. F. C | §18 | not required |
| 34 | yes | TEXT_OK | A:34 DONIDA LABATI et al. R. Donida Labati, V. Piuri, and F. Scotti. 2012. Biome | §18 | not required |
| 35 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:35 D. Gorodnichy, S. Yanushk | §18 | not required |
| 36 | yes | TEXT_OK | A:36 DONIDA LABATI et al. R. Koslowski. 2003. Information technology and integra | §18 | not required |
| 37 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:37 K. Nguyen, C. Fookes, S.  | §18 | not required |
| 38 | yes | TEXT_OK | A:38 DONIDA LABATI et al. K. Sava. 2014. Automated passport control: business re | §18 | not required |
| 39 | yes | TEXT_OK | Biometric Recognition in Automated Border Control A:39 L. Wiskott, J.-M. Fellous | §18 | not required |

### `Lezione_14_ElevataSicurezzaFisica_ABC.pdf` (80 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Accesso fisico ad elevata sicurezza e Automated Border Control (ABC) Fabio Scott | §0 | not required |
| 3 | yes | TEXT_OK | Outline - Sicurezza fisica - Basi ed elementi - Focus su biometria e videosorveg | §0 | not required |
| 4 | yes | LIKELY_VISUAL | Sicurezza Fisica Terminologia Elementi principali Fabio Scotti - Università degl | §1 + PH | not required |
| 5 | yes | TEXT_OK | Perché ripassiamo le basi della Sicurezza Fisica? Per capire che tipo di sfide a | §1 | not required |
| 6 | yes | TEXT_OK | Sicurezza Logica VS Fisica Sicurezza logica Protegge dati, servizi e risorse dig | §1 | not required |
| 7 | yes | TEXT_OK | Fallimenti Sicurezza logica - Compromissione di Dati, servizi, account - Perdite | §1 | not required |
| 8 | yes | TEXT_OK | Cosa è la Sicurezza Fisica? DEFINIZIONE (per questo insegnamento) la sicurezza f | §2 | not required |
| 9 | yes | TEXT_OK | Principali 7 elementi di Sicurezza Fisica (1) 1. Configurazione del Sito e Sicur | §2 | not required |
| 10 | yes | TEXT_OK | Principali elementi di Sicurezza Fisica (2) 5. Rilevazione delle Intrusioni e de | §2 | not required |
| 11 | yes | TEXT_OK | Esempi di minacce, vulnerabilità e rischi tipici della sicurezza fisica - Access | §3 | not required |
| 12 | yes | TEXT_OK | Come si usano le tecnologie biometriche (ex ante) Le tecniche biometriche e la v | §4 | not required |
| 13 | yes | TEXT_OK | Come si usano le tecnologie biometriche (ex ante) - Integrazione con altri siste | §4 | not required |
| 14 | yes | TEXT_OK | Come si usano le tecnologie biometriche (ex post) Gestione delle Problematiche ( | §4 | not required |
| 15 | yes | TEXT_OK | In 4 Livelli/Aree della Sicurezza Fisica Riassumendo il tutto in breve… [1] Diss | §5 | not required |
| 16 | yes | TEXT_OK | Terminologia Tailgating: quando una persona non autorizzata segue da vicino un i | §8 | not required |
| 17 | yes | LIKELY_VISUAL | Terminologia: Tailgating e Piggybacking Contromisura Contromisura Contromisura | §8 + PH | not required |
| 18 | yes | TEXT_OK | Come limitare il Tailgating e Piggybacking Automated Border Control Telecamere d | §8 + PH | not required |
| 19 | yes | TEXT_OK | Un esempio commerciale: Gunnebo Speedstile FLs (Si consiglia la visione video in | §8 | not required |
| 20 | yes | TEXT_OK | Tipi di varchi che possono ospitare biometria Valutazione eseguita da https://ww | §8 + PH | not required |
| 21 | yes | TEXT_OK | Automated Border Control Applicabile anche a dipartimenti di ricerca e sviluppo, | §9 | not required |
| 22 | yes | TEXT_OK | Accesso fisico a elevata sicurezza - In questa parte di lezione ci si focalizzer | §9 | not required |
| 23 | yes | TEXT_OK | Dispensa ABC (riassume e descrive i concetti delle prossime slide) La dispensa è | §9 | not required |
| 24 | yes | TEXT_OK | Introduction (1) - 3.3 billion travelers per year (only air transport) and still | §9 | not required |
| 25 | yes | TEXT_OK | Introduction (2) - Border checks ensure that a person is allowed to enter or lea | §9 | not required |
| 26 | yes | TEXT_OK | Introduction (3) - Use of biometrics to identify the travelers - A person's uniq | §9 | not required |
| 27 | yes | TEXT_OK | ABC e eGates eGates - Le eGates, o porte elettroniche, sono porte automatiche ut | §10 | not required |
| 28 | yes | TEXT_OK | ABC e eGates Automated Border Control (ABC) - Sistema automatizzato utilizzato n | §10 | not required |
| 29 | yes | TEXT_OK | Benefici degli ABC biometrici - Ridurre il tempo di elaborazione Attraversamento | §10 | not required |
| 30 | yes | TEXT_OK | Sistemi installati 100+ stati attualmente impiegano tecnologie ABC per I loro pu | §10 | not required |
| 31 | yes | TEXT_OK | Diverse applicazioni in aeroporto - Self-service Baggage Drop stations Stazioni  | §10 | not required |
| 32 | title only | LIKELY_VISUAL | Infrastruttura di un ABC Automated Border Control | §11.1 + PH | not required |
| 33 | title only | LIKELY_VISUAL | ABC process Automated Border Control | §11.1 + PH | not required |
| 34 | yes | TEXT_OK | Electronic passports - Specified by the ICAO, in standard Doc 9303 - Uses RFID t | §11.2 + PH | not required |
| 35 | yes | LIKELY_VISUAL | ABC Gate architecture: Components - Entry and exit doors | §11.3 + PH | not required |
| 36 | title only | LIKELY_VISUAL | ABC Gate architecture: Components - Document reader | §11.3 + PH | not required |
| 37 | yes | TEXT_OK | ABC Gate architecture: Components - Biometric capture and verification devices:  | §11.3 | not required |
| 38 | yes | TEXT_OK | ABC Gate architecture: Components - Security sensors to monitor the clearance pr | §11.3 | not required |
| 39 | yes | TEXT_OK | ABC Gate architecture: Components - Instructional displays (Signalling) | §11.3 | not required |
| 40 | yes | TEXT_OK | Il signalling in un sistema biometrico di elevata sicurezza Il termine "signalli | §11.3 | not required |
| 41 | yes | TEXT_OK | Struttura di un ABC ABC Parte Fisica One step Man trap Virtual Trap Two steps Se | §12 + PH | not required |
| 42 | yes | TEXT_OK | Architetture ABC Gate : One-step Aggrega tutte le verifiche in un'unica transazi | §12 | not required |
| 43 | yes | TEXT_OK | One step 2 realizzazioni Man-trap - Una man-trap è una struttura fisica o una st | §12 + PH | not required |
| 44 | yes | TEXT_OK | Esempio di sensori per conteggio persone Questo sono usabili per applicazioni di | §12 + PH | not required |
| 45 | yes | TEXT_OK | ABC Gate architecture: Two-step process - Step 1: information about the traveler | §12 + PH | not required |
| 46 | yes | TEXT_OK | ABC Gate architecture: Two-step process - Two possibilities: - Integrated (reade | §12 | not required |
| 47 | yes | TEXT_OK | Other systems interfaced with the ABC system - Visa Information Systems (VIS) -  | §13 | not required |
| 48 | yes | TEXT_OK | EU Entry-Exit System (EES) - Il nuovo Entry-Exit System (EES) dell'Unione Europe | §13 | not required |
| 49 | yes | LIKELY_VISUAL | A cosa serve l'EES … aggiungiendo la biometria Turismo (o affari brevi): 90- 80  | §13 + PH | not required |
| 50 | yes | TEXT_OK | Registered Traveler Program (RTP) - A voluntary enrolment system aimed at expedi | §13 | not required |
| 51 | yes | TEXT_OK | Advanced ABC Gate: Mobile devices - Handheld devices that aid border guards to:  | §13 | not required |
| 52 | title only | LIKELY_VISUAL | Biometric traits in ABCs Automated Border Control | §14.1 + PH | not required |
| 53 | yes | TEXT_OK | Frequently used biometric traits - Identity checks in ABC gates are based on the | §14.1 | not required |
| 54 | yes | TEXT_OK | Face recognition in ABC Gates - Face image is the biometric trait preferred by t | §14.2 | not required |
| 55 | yes | TEXT_OK | Face recognition setup - Acquisition cameras: - More than one camera for diverse | §14.2 | not required |
| 56 | yes | TEXT_OK | Face recognition: Quality recommendations (1) Image dimensions Damaged or staine | §14.2 + PH | not required |
| 57 | yes | TEXT_OK | Face recognition: Quality recommendations (2) Red eyes Wearing a hat Covered fac | §14.2 + PH | not required |
| 58 | yes | TEXT_OK | Face recognition: Challenges for ABC Gates - Ensuring the quality of the capture | §14.2 | not required |
| 59 | yes | TEXT_OK | Fingerprint recognition in ABC Gates - ICAO describes it as optional biometric t | §14.3 | not required |
| 60 | yes | TEXT_OK | Iris recognition in ABC Gates - ICAO describes it as optional biometric trait fo | §14.4 | not required |
| 61 | yes | LIKELY_VISUAL | Biometric traits for future ABC Gates - Contactless fingerprint - Contactless pa | §14.5 + PH | not required |
| 62 | yes | TEXT_OK | On-the-move iris - Advantages: - Less constrained - More usability - Increased u | §14.5 | not required |
| 63 | yes | TEXT_OK | Multibiometrics in ABC Gates - 28% of the deployed systems use multibiometrics - | §14.6 | not required |
| 64 | yes | LIKELY_VISUAL | Different applications in the airport - Self-service Baggage Drop stations - Aut | §16.1 + PH | not required |
| 65 | yes | LIKELY_VISUAL | A multimodale system: Visionbox Happyflow - Integrated system - Iris - Fingerpri | §16.1 + PH | not required |
| 66 | yes | TEXT_OK | Soluzioni a bassissimo impatto di infrastruttura e alta velocità https://www.tha | §16.1 + PH | not required |
| 67 | yes | TEXT_OK | Ulteriori azioni per le applicazioni - Integrazione di ABC e eGates con altri si | §16.1 | not required |
| 68 | yes | TEXT_OK | Esempio di tecnologia attuale inter aeroportuale Vision-Box Seamless Journey Pla | §16.2 | not required |
| 69 | yes | TEXT_OK | Vision-Box Seamless Journey Platform - Esaminiamo una delle tecnologie più attua | §16.2 | not required |
| 70 | yes | TEXT_OK | Si parte dalla creazione di un token biometrico «a casa» - Usando il cellulare e | §16.2 + PH | not required |
| 71 | yes | TEXT_OK | In aeroporto il sistema lavora 1:N I limiti attuali dell'uso del volto in accura | §16.2 + PH | not required |
| 72 | yes | TEXT_OK | Il riconoscimento biometrico nei passi successivi è automatico | §16.2 | not required |
| 73 | yes | TEXT_OK | L'aeroporto diventa un ecosistema smart che offre (e vende) servizi Basandosi su | §16.2 | not required |
| 74 | yes | TEXT_OK | Bag drop Anche la consegna dei bagagli è senza personale, e l'utente stesso tagg | §16.2 | not required |
| 75 | yes | TEXT_OK | Gate Basandosi sulla identificazione 1:N (con N limitato dalla selezione dinamic | §16.2 | not required |
| 76 | yes | TEXT_OK | E' possibile integrare i servi in all'arrivo Screening automatico per la nuova f | §16.2 | not required |
| 77 | yes | LIKELY_VISUAL | Servizi e accessori | §16.2 + PH | not required |
| 78 | yes | TEXT_OK | Esempio di una applicazione Vision-box @ Dubai Airport (Emiratas) Integrated Bio | §16.2 | not required |
| 79 | yes | LIKELY_VISUAL | Dispensa (non richiesta all'esame) Per chi è interessato al tema si allega la se | §17 + PH | not required |
| 80 | yes | TEXT_OK | In sintesi FINE Sicurezza fisica - Basi ed elementi - Focus su biometria Advance | §17 | not required |

### `Lezione_14_brocure_commerciale_Pelco_Physical_Security_Guide.pdf` (12 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | LIKELY_VISUAL | PELCO.COM GUIDE TO PHYSICAL SECURITY CONTROLS, PLANNING, POLICIES & MEASURES | §2 | not required |
| 2 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY Physical security planning can feel like a daun | §2 | not required |
| 3 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY COMMON PHYSICAL SECURITY THREATS, VULNERABILITI | §3 | not required |
| 4 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY PHYSICAL SECURITY MEASURES AND METHODS There ar | §5 + PH | not required |
| 5 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY PHYSICAL SECURITY CONTROL TECHNOLOGY Within the | §6 | not required |
| 6 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY VIDEO SECURITY Video surveillance technology is | §6 | not required |
| 7 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY ACCESS CONTROL Access control technology is ano | §6 | not required |
| 8 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY ANALYTICS AND ARTIFICIAL INTELLIGENCE Physical  | §6 | not required |
| 9 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY METHODS TO IDENTIFY PHYSICAL SECURITY THREATS T | §7 | not required |
| 10 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY PHYSICAL SECURITY PLANNING Drawing up physical  | §7 | not required |
| 11 | yes | TEXT_OK | EBOOK GUIDE TO PHYSICAL SECURITY 3. COMMERCIAL AND OPERATIONAL APPROVAL At this  | §7 | not required |
| 12 | yes | TEXT_OK | To learn more, contact a Pelco Sales Representative today! ©2023 Pelco Inc. All  | §7 | not required |

**Totals:** 131/131 pages accounted for · 34 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione15 — DONE

**Lesson file(s):** `Lezione15/L15 - Metodi avanzati di progettazione - multimodalità e fusione dell'informazione.md`

### `Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf` (70 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Metodi avanzati per la progettazione e miglioramento dei sistemi biometrici Fabi | §0 | not required |
| 3 | yes | TEXT_OK | Outline Metodi avanzati per la progettazione e miglioramento dei sistemi biometr | §0 | not required |
| 4 | yes | LIKELY_VISUAL | Introduzione ai sistemi multimodali Nota: alcuni lucidi introduttivi sono presi  | §0 + PH | not required |
| 5 | yes | TEXT_OK | Svantaggi dei sistemi monomodali - Rumore presente nei dati in ingresso - ad ese | §1.1 | not required |
| 6 | yes | TEXT_OK | Sistemi multimodali "Multimodal biometric systems are those which utilize, or ar | §1.1 | not required |
| 7 | yes | TEXT_OK | L'essere umano è "multimodale" State aspettando la vostra amica Laura. Una perso | §1.2 + PH | not required |
| 8 | yes | TEXT_OK | I sistemi multimodali sono più accurati! - E' perfettamente assodato in letterat | §1.3 + PH | not required |
| 9 | yes | TEXT_OK | Vantaggi e svantaggi dei sistemi multimodali - VANTAGGI - Usare N tratti biometr | §1.3 | not required |
| 10 | yes | TEXT_OK | Perché un sistema multimodale abbassa il FTA e FTE - Supponiamo di avere un sist | §2 | not required |
| 11 | yes | TEXT_OK | Perché un sistema multimodale abbassa il FTA e FTE Definizione FTE sistema multi | §2 | not required |
| 12 | yes | TEXT_OK | Perché un sistema multimodale abbassa il FTA e FTE (Caso Generale) - La seguente | §2 | not required |
| 13 | title only | LIKELY_VISUAL | Cosa si può unire? | §3 + PH | not required |
| 14 | yes | TEXT_OK | Quali tratti unire? Alcuni esempi di combinazioni di tratti biometrici presenti  | §3 + PH | not required |
| 15 | yes | TEXT_OK | Tratti più usati insieme I sistemi biometrici multimodali maggiormente implement | §3 | not required |
| 16 | yes | TEXT_OK | Applicazioni target - Interesse potenziale per sistemi multimodali - alto - acce | §3 | not required |
| 17 | yes | TEXT_OK | Terminologia usata in letteratura In letteratura all'interno della macrodistinzi | §3 | not required |
| 18 | yes | TEXT_OK | Schemi classici Fusione a livello di matchscore Fusione a livello di matchscore  | §4 + PH | not required |
| 19 | yes | LIKELY_VISUAL | Esempio di sistema multibiometrico Al posto del tipico Iriscode viene usata una  | §4 + PH | not required |
| 20 | yes | LIKELY_VISUAL | Livelli di integrazione della informazione FM: Fusion Module DM: Decision Module | §4 + PH | not required |
| 21 | yes | LIKELY_VISUAL | Fusione a livello di matching Feature Extraction 1 Matching 1 Fusion Decision Fe | §4 | not required |
| 22 | yes | TEXT_OK | Fusione a livelli diversi L'incremento di accuratezza maggiore si verifica usand | §4, §4.2 + PH | not required |
| 23 | yes | TEXT_OK | Tecniche generali per i vari livelli Feature Extraction 1 Fusion Matching Decisi | §4 | not required |
| 24 | yes | TEXT_OK | Match score level Partiamo con il più diffuso: Feature Extraction 1 Matching 1 F | §4, §5 | not required |
| 25 | yes | TEXT_OK | Metodi di fusione del match score (1) - La regola della somma si è dimostrata in | §5 + PH | not required |
| 26 | yes | TEXT_OK | Metodi di fusione del match score (1) - Perché la regola della somma supera il m | §5 + PH | not required |
| 27 | yes | TEXT_OK | Metodi di fusione del match score (2) - Vi sono 2 filosofie diverse per unire i  | §5, §5.2 | not required |
| 28 | yes | TEXT_OK | Normalizzazione degli score (1) - Per confrontare fra loro correttamente i valor | §6 | not required |
| 29 | yes | TEXT_OK | Normalizzazione degli score (2) - A titolo di esempio mostriamo delle distribuzi | §6 + PH | not required |
| 30 | yes | FORMULA_AMBIGUOUS | Normalizzazione degli score (3) - In questo esempio viene mostrato come una dist | §6 + PH | not required |
| 31 | yes | TEXT_OK | Necessità di omogeneizzazione, regolarizzazione e normalizzazione RETI NEURALI,  | §6, §6.2 | not required |
| 32 | yes | TEXT_OK | Funzioni di normalizzazione - Normalizzazione min-max - dati i match score {sk}, | §6, §6.3 | not required |
| 33 | yes | LIKELY_VISUAL | Un esempio riassuntivo (1) Un metodo di fusione semplice ed efficace è quella a  | §6, §6.4 + PH | not required |
| 34 | title only | LIKELY_VISUAL | Un esempio riassuntivo (2) | §6, §6.4 + PH | not required |
| 35 | yes | TEXT_OK | Integrazione di sistemi commericali - In questo test viene mostrato come si comp | §6, §6.4 + PH | not required |
| 36 | yes | TEXT_OK | Esempio classico Supponiamo di avere 3 sistemi biometrici a disposizione che rit | §7 | not required |
| 37 | yes | LIKELY_VISUAL | Match score fusion Passaggi Ogni tratto verrà normalizzato | §7, §7.1 + PH | not required |
| 38 | yes | TEXT_OK | Rank level Il rango è un numero (intero) assegnato al punteggio dello score (flo | §7, §7.2 | not required |
| 39 | yes | TEXT_OK | Rank level (2) Analizziamo per semplicità due utenti L'utente A si è piazzato Ir | §7, §7.2 + PH | not required |
| 40 | yes | TEXT_OK | Rank level (3) L'utente A si è piazzato Iride: 2° Volto: 3° Impronta: 2° L'utent | §7, §7.2 | not required |
| 41 | yes | FORMULA_AMBIGUOUS | Rank level (3) L'utente A si è piazzato Iride: 2° Volto: 3° Impronta: 2° L'utent | §7, §7.2 | not required |
| 42 | yes | TEXT_OK | Decision Level And/Or/Voting Passaggi score1 = 0.5 score2 = 0.7 score3 = 0.9 Bas | §7, §7.3 + PH | not required |
| 43 | yes | TEXT_OK | In sintesi FINE - Abbiamo visto: – gli schemi classici dei sistemi multimodali e | §7 | not required |
| 44 | yes | LIKELY_VISUAL | Tecniche avanzate (tradizionali) di datafusion per sistemi multimodali | §8 + PH | not required |
| 45 | yes | TEXT_OK | Sistemi multimodali gerarchici (1) - Nei sistemi multimodali gerarchici (Sequent | §8 + PH | not required |
| 46 | yes | TEXT_OK | Sistemi multimodali gerarchici (2) - Performance del sistema gerarchico mostrato | §8 + PH | not required |
| 47 | yes | TEXT_OK | Normalizzazione con cohort Come normalizzare gli score in modo intelligente usan | §9 | not required |
| 48 | yes | TEXT_OK | Il concetto di cohort (coorte) - La coorte (cohort) in statistica e nella demogr | §9 | not required |
| 49 | yes | FORMULA_AMBIGUOUS | Normalizzazione degli score con la tecnica dei cohort Per esempio: prendiamo i 2 | §9 + PH | not required |
| 50 | yes | TEXT_OK | Normalizzazione degli score con la tecnica dei cohort (2) Confrontare le distanz | §9 | not required |
| 51 | yes | TEXT_OK | Caratteristiche delle tecniche di normalizzaizone dei cohort in biometria - Priv | §9 | not required |
| 52 | yes | TEXT_OK | Feature level fusion Unire direttamente le informazioni estratte dal tratto | §10 | not required |
| 53 | yes | TEXT_OK | Fusione a livello di feature - Potente ma è più complessa, occorre creare - un m | §10 | not required |
| 54 | yes | TEXT_OK | Fusione a livello di feature (cont.) - Non è sempre facile riuscire a realizzare | §10 | not required |
| 55 | yes | TEXT_OK | Fusione a livello di feature (2) Esempio - si estraggono le feature - mano (14 l | §10 | not required |
| 56 | yes | TEXT_OK | Fusione a livello di feature: esempio volto e mano - Volto: vettore di feature d | §10 + PH | not required |
| 57 | yes | LIKELY_VISUAL | Datafusion per sistemi multimodali mediante Deep Learning Come estrarre in modo  | §11 | not required |
| 58 | yes | TEXT_OK | Feature-level fusion via Deep Learning: PASSO 1 (Training con identificazione) T | §11 + PH | not required |
| 59 | yes | TEXT_OK | Feature-level fusion via Deep Learning: Passo 2 (Uso biometrico e fusion) Templa | §11 + PH | not required |
| 60 | yes | TEXT_OK | Memo - Nel corso di Intelligent systems for industry, supply chain and environme | §11 | not required |
| 61 | yes | TEXT_OK | Parametrizzazione specifica per il singolo utente - Esistono principalmente due  | §12 | not required |
| 62 | yes | TEXT_OK | Parametrizzazione soglia di ogni utente - Sia Xi_j il j-esimo template dell'indi | §12 | not required |
| 63 | yes | TEXT_OK | Parametrizzazione soglia di ogni utente - Immaginiamo di avere le distribuzioni  | §12 + PH | not required |
| 64 | yes | TEXT_OK | Modalità di integrazione della soft biometrics (1) - Alcuni i tratti chiamati di | §13 | not required |
| 65 | yes | TEXT_OK | Modalità di integrazione della soft biometrics (2) - L'integrazione corretta di  | §13 | not required |
| 66 | yes | TEXT_OK | Implementazione del modulo Un impronta x e una altezza y sono considerabili come | §13 + PH | not required |
| 67 | yes | TEXT_OK | Risultati della integrazione della soft-biometrics - In questo diagramma si most | §13 + PH | not required |
| 68 | yes | TEXT_OK | Quality-based match score level fusion Approccio ibrido di selezione e fusione | §14 | not required |
| 69 | yes | FORMULA_AMBIGUOUS | Fusione con qualità del tratto Feature Extraction 1 Matching 1 Fusion Decision F | §14 + PH | not required |
| 70 | yes | TEXT_OK | In sintesi Abbiamo visto: – Tecniche di update dei template ▪Update del singolo  | §16 | not required |

**Totals:** 70/70 pages accounted for · 30 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione16 — DONE

**Lesson file(s):** `Lezione16/L16 - Flusso ottico, gait recognition, riconoscimento delle emozioni e autenticazione continua.md`

### `Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf` (93 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Flusso ottico e applicazioni in biometria. Gait recognition. Riconoscimento dell | §0 | not required |
| 2 | yes | TEXT_OK | Nella lezione - Flusso ottico - Applicazioni in biometria e Gait Recognition - R | §0 | not required |
| 3 | yes | TEXT_OK | Flusso ottico e impieghi in biometria Elaborazione di segnale ed immagini molto  | §1.1 | not required |
| 4 | yes | TEXT_OK | Introduzione al flusso ottico Guardiamo i due frame iniziali. Cosa è successo? C | §1.1 | not required |
| 5 | yes | TEXT_OK | Esempi di flusso ottico blob detection + speed measurement Il flusso ottico (cal | §1.1 + PH | not required |
| 6 | yes | TEXT_OK | Flusso ottico Servono dei riferimenti per calcolare i vettori spostamento Servon | §1.1 + PH | not required |
| 7 | yes | TEXT_OK | Concetto principale Immaginiamo un drone che si muove in avanti → In modo relati | §1.1 | not required |
| 8 | yes | LIKELY_VISUAL | Flusso ottico: definizione | §1.2 + PH | not required |
| 9 | yes | LIKELY_VISUAL | Flusso ottico e interpretazione del movimento | §1.2 + PH | not required |
| 10 | yes | TEXT_OK | Applicazioni biometriche del flusso ottico Video sorveglianza Movimenti facciali | §1.3 | not required |
| 11 | yes | TEXT_OK | Flusso ottico: passi principali Distribuzione in intensità e angolo del Flusso o | §2 + PH | not required |
| 12 | yes | TEXT_OK | Estrazione di caratteristiche da Optical Flow - Ogni vettore di flusso ottico vi | §2 + PH | not required |
| 13 | yes | TEXT_OK | Passi principali per eseguire Gait Recognition Example: Space-time interest poin | §3 + PH | not required |
| 14 | yes | TEXT_OK | Flusso ottico per il volto Il flusso ottico del volto permette di catturare anch | §3 + PH | not required |
| 15 | yes | LIKELY_VISUAL | Automatic Emotion Recognition | §4 | not required |
| 16 | yes | TEXT_OK | 7 emozioni base - Secondo Paul Ekman le emozioni principali dell'uomo possono es | §4 + PH | not required |
| 17 | yes | TEXT_OK | Ma è tutto vero? Ascoltiamo il parere di uno dei massimi esperti mondiali sul ce | §4 | not required |
| 18 | yes | TEXT_OK | Cosa possiamo pensare? P. Ekann Le emozioni sono «hard-wired» generali e leggibi | §4 | not required |
| 19 | yes | LIKELY_VISUAL | Emotion Recognition as a Classification Problem Facial Expression Recognition (F | §5.1 + PH | not required |
| 20 | yes | TEXT_OK | Mercato delle emozioni…. Il mercato del affective computing (80B USD @2025) è pr | §5.1 | not required |
| 21 | yes | TEXT_OK | Esempio di sistema Realeyes adverteyes https://adverteyes.ai/human-measurement/# | §5.1 + PH | not required |
| 22 | yes | TEXT_OK | Tipi di input - Ci focalizzeremo su immagini e video. - Tuttavia le emozioni son | §5.1 | not required |
| 23 | yes | TEXT_OK | Altri tipi di output Open-Vocabulary La ricerca si sta spostando dal classificar | §5.1 | not required |
| 24 | title only | LIKELY_VISUAL | Immagini e Video | §5.2 | not required |
| 25 | yes | TEXT_OK | Technologies Static-based: based on a single frame Dynamic-based: based on a fra | §5.2 | not required |
| 26 | yes | FORMULA_AMBIGUOUS | Traditional Computational Schema Face detection Preprocessing Classification Cla | §5.2 + PH | not required |
| 27 | yes | TEXT_OK | Step 1: Face Detection Algoritmic - Viola and Jones, Haar wavelets, histogram of | §5.2 | not required |
| 28 | yes | TEXT_OK | Step 2: Preprocessing (optional) Illumination compensation - Isotropic diffusion | §5.2 | not required |
| 29 | yes | FORMULA_AMBIGUOUS | Step 3: Static-based Methods (Classic Approach) Classifiers - k-nearest neighbor | §5.2 | not required |
| 30 | yes | TEXT_OK | Step 3: Static-based Methods (Commonly Used Features) General purpose features - | §5.2 + PH | not required |
| 31 | yes | FORMULA_AMBIGUOUS | Step 3: Static-based Methods (Deep Classifiers) Architectures - CNNs - Ensables  | §5.2 | not required |
| 32 | yes | TEXT_OK | Dynamic-based Methods N frames in input Main techniques - Facial landmark trajec | §5.2 | not required |
| 33 | yes | FORMULA_AMBIGUOUS | Funsione multimodale a livello di score Emotion recognition Emotion recognition  | §5.3 + PH | not required |
| 34 | yes | TEXT_OK | Speech Emotion Recognition - Il riconoscimento delle emozioni nel parlato è un c | §5.3 | not required |
| 35 | yes | TEXT_OK | Multimodale con Deep Learning In modo piu' avanzato e' possible estrarre da audi | §5.3 + PH | not required |
| 36 | yes | FORMULA_AMBIGUOUS | Client-based and Client-Server Implementations Face detection Preprocessing Clas | §5.3 + PH | not required |
| 37 | yes | TEXT_OK | Allenamento e validazione - Esistono dataset di immagini labellizate appositamen | §6.1 | not required |
| 38 | yes | TEXT_OK | Altri DB in caso servissero Spesso è necessario riallenare i modelli su DB più v | §6.1 + PH | not required |
| 39 | yes | TEXT_OK | Esempio commerciale: FaceReader - La società NOLDUS produce FaceReader (con la c | §6.2 | not required |
| 40 | yes | TEXT_OK | Alcuni risultati in letteratura Errors bars represent 95% Confidence Interval (m | §6.2 + PH | not required |
| 41 | yes | TEXT_OK | Emozioni «difficili» e «facili» da classificare Studi trasversali su DB diversi  | §6.2 + PH | not required |
| 42 | yes | TEXT_OK | Altri esempi da DB Static Face Emotion Recognition (SFER) RAF-DB (Real-world Aff | §6.3 | not required |
| 43 | yes | TEXT_OK | Altri esempi da DB Dynamic Emotion Recognition (DFER) Task di computer vision /  | §6.3 | not required |
| 44 | yes | TEXT_OK | Altri esempi da DB Dynamic Emotion Recognition (DFER) DFEW (Dynamic Facial Expre | §6.3 | not required |
| 45 | yes | TEXT_OK | Il Paradosso dell'Annotatore (Riproducibilità) - Un elemento critico è il Inter- | §6.4 | not required |
| 46 | yes | TEXT_OK | 2026 SOA vs Umani nel riconoscimento delle emozioni (immagini e video) - Il conf | §6.4 | not required |
| 47 | yes | TEXT_OK | 2026 SOA vs Umani nel riconoscimento delle emozioni: immagini - Immagini statich | §6.5 | not required |
| 48 | yes | TEXT_OK | 2026 SOA vs Umani nel riconoscimento delle emozioni: video Nei dataset video: -  | §6.5 + PH | not required |
| 49 | yes | TEXT_OK | 2026 SOA vs Umani nel riconoscimento delle emozioni: video DFER (Video) - DFEW m | §6.5 | not required |
| 50 | yes | TEXT_OK | Riassunto finale Immagini statiche → problema relativamente "chiuso": modelli mo | §6.5 | not required |
| 51 | yes | TEXT_OK | ROC del classificatore - Poiché in uscita ai classificatori abbiamo dei valori d | §6.6 | not required |
| 52 | yes | TEXT_OK | Stato dell'arte 2020 + Uomini VS sw Receiver Operating Characteristic (ROC) curv | §6.6 + PH | not required |
| 53 | yes | TEXT_OK | Nuove applicazioni: gestione risorse umane Il valore del mercato globale dell'AI | §7 | not required |
| 54 | yes | TEXT_OK | AI for Human Resources (HR) Uso di chatbot (AI RECRUITING ASSISTANT). Da Hire Vu | §7 | not required |
| 55 | title only | LIKELY_VISUAL | Accuse di discriminazione e Emozioni Facciali per HR | §7 + PH | not required |
| 56 | title only | LIKELY_VISUAL | AI for HR Micro espressioni! Il candidato mente? | §7 + PH | not required |
| 57 | yes | TEXT_OK | Legge approvata nel marzo 2024 Regolamento sull'Intelligenza Artificiale (AI Act | §7 | not required |
| 58 | title only | LIKELY_VISUAL | Continuous authentication Comparazione delle tecnologie | §8 + PH | not required |
| 59 | yes | LIKELY_VISUAL | Continuous authentication L'aspetto della gestione del tempo diventa critico! | §8 + PH | not required |
| 60 | yes | TEXT_OK | Silent Authentication (base-mobile) - L'autenticazione silenziosa fornisce un'au | §9 | not required |
| 61 | yes | TEXT_OK | Silent authentication (avanzata) L'autenticazione silenziosa è particolarmente a | §9 | not required |
| 62 | yes | TEXT_OK | Silent authentication (avanzata) Per identificare e autenticare l'utente, in mod | §9 | not required |
| 63 | yes | LIKELY_VISUAL | Continuous authentication: Cellulare Inertial Measuring Unit (IMU) | §10 + PH | not required |
| 64 | yes | TEXT_OK | Controllo dei movimenti con IMU Bosch BMI085 e BHA260 Bosch has launched a 6 axi | §10 | not required |
| 65 | yes | TEXT_OK | Continuous authentication: Wrist band / Smart watch 1 punto di autenticazione pu | §10 + PH | not required |
| 66 | yes | TEXT_OK | Continuous authentication: Esempio della NYMI (Finger+ECG) https://www.nymi.com/ | §10 | not required |
| 67 | yes | TEXT_OK | ECG for automotive - detect heart rate anomalies - perform continuous driver aut | §10 | not required |
| 68 | yes | TEXT_OK | Polar Vantage V Sport data ECG data Heart rate variability (HRV) | §10 | not required |
| 69 | yes | TEXT_OK | APPLE WATCH SERIES 4,5,6… Apple Watch 4+: ECG similar to a single- lead electroc | §10 + PH | not required |
| 70 | yes | TEXT_OK | R. Donida Labati, E. Muñoz, V. Piuri, R. Sassi, F. Scotti, Deep-ECG: Convolution | §10 | not required |
| 71 | yes | TEXT_OK | Nuove tecnologie RF Tecnologia WISE measures the driver's vital signs including  | §10 | not required |
| 72 | yes | LIKELY_VISUAL | Autenticazione Continua Multimodale Irrobustire funzionamento - migliorare accur | §11 | not required |
| 73 | yes | TEXT_OK | Fusione multimodale continua - Servono approcci per gestire la indeterminazio ne | §11 + PH | not required |
| 74 | yes | TEXT_OK | Fusione multimodale continua Serve un approccio capace di gestire le incertezze  | §11 | not required |
| 75 | yes | TEXT_OK | Esempio di Multimodal Continuous Authentication Less-constrained and non-coopera | §11 | not required |
| 76 | yes | TEXT_OK | Test: EER(t) EER(0)=3.3% tempo t=0 t=1s Usiamo il singolo frame I(t) per calcola | §11 | not required |
| 77 | yes | TEXT_OK | Test: EER(t) EER(3)=45% tempo t=0 t=1s Usiamo il singolo frame I(t) per calcolar | §11 + PH | not required |
| 78 | yes | TEXT_OK | Esempio di Multimodal Continuous Authentication: EER(t) EER obtained at differen | §11 + PH | not required |
| 79 | yes | FORMULA_AMBIGUOUS | Fusione multimodale continua VOICE+FACE+QUALITY Frame sequences Fused matching s | §11 + PH | not required |
| 80 | yes | LIKELY_VISUAL | Terminale Autenticazione continua | §12 | not required |
| 81 | yes | LIKELY_VISUAL | Continuous authentication via terminale (tastiera e mouse): approccio Multimodal | §12 + PH | not required |
| 82 | yes | TEXT_OK | Un esempio di tecnologia Keystroke DNA - Non continuous - Starting from 8 charac | §12 | not required |
| 83 | yes | TEXT_OK | Continuous authentication Behavioral biometrics PLURILOCK DEFEND - Full-day, rea | §12 | not required |
| 84 | yes | TEXT_OK | PLURILOCK DEFEND - What users do is not stored - Template: patterns of microbeha | §12 | not required |
| 85 | yes | LIKELY_VISUAL | Caratteristiche del PLURILOCK - Enrollment automatico - Riconoscimento continuo  | §12 + PH | not required |
| 86 | yes | TEXT_OK | PLURILOCK DEFEND - Keyboard - keystroke speed, - keystroke duration, - variation | §12 | not required |
| 87 | yes | LIKELY_VISUAL | PLURILOCK DEFEND - Non sono disponibili EER, DET, ROC, ecc. - Venduto come Softw | §12 | not required |
| 88 | yes | TEXT_OK | Aspetti biometrici dai dati di processo Quando la business intelligence diventa  | §13 | not required |
| 89 | yes | TEXT_OK | Biometria comportamentale dati per misurare performance CASO Amazon France (CNIL | §13 | not required |
| 90 | yes | TEXT_OK | Biometria comportamentale dai dati di processo (e problemi relativi) Il punto cr | §13 | not required |
| 91 | yes | TEXT_OK | Normali dati di processo dei lavoratori Esempi di normali dati di lavoratori reg | §13 | not required |
| 92 | yes | TEXT_OK | Come diventa un problema Da normali dati di processo, mediante selezione e aggre | §13 + PH | not required |
| 93 | yes | TEXT_OK | Abbiamo visto - Flusso ottico - Definizione - Estrazione del template con la tec | §15 | not required |

**Totals:** 93/93 pages accounted for · 35 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione17 — DONE

**Lesson file(s):** `Lezione17/L17 - Test di Turing inverso, proof of personhood e deepfake detection.md`

### `Lezione_17_ONFIDO_2025-identity-fraud-report.pdf` (42 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | LIKELY_VISUAL | 2025 Identity Fraud Report ENTRUST CYBERSECURITY INSTITUTE PRESENTS | §12.1 | not required |
| 2 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Foreword Executive Summary 2024 Fraud Lan | §12.1 | not required |
| 3 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Emerging threats catalyzed by the rise and  | §12.1 | not required |
| 4 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Executive Summary Fraud is a problem that h | §12.1 | not required |
| 5 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 2024 Fraud Landscape The state of fraud in  | §12.1 + PH | not required |
| 6 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Key Findings 1. Digital manipulation replac | §12.1 | not required |
| 7 | yes | LIKELY_VISUAL | Entrust / Identity Fraud Report 2025 FRAUD TRENDS Data Stories | §12.2 | not required |
| 8 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Average fraud rates help monitor for any si | §12.2 + PH | not required |
| 9 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Pre-COVID: 2019 Before the COVID-19 global  | §12.2 | not required |
| 10 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Fraudsters focus on finance The top three m | §12.2 | not required |
| 11 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Cryptocurrency An FBI report found that rep | §12.2 | not required |
| 12 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 It's difficult to determine exactly where f | §12.2 | not required |
| 13 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Top 10 Most Targeted Documents Some of the  | §12.2 + PH | not required |
| 14 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 National ID Cards Remain Top Target for Fra | §12.2 | not required |
| 15 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Key Indicators of Fraudulent Documents Not  | §12.2 | not required |
| 16 | yes | LIKELY_VISUAL | Entrust / Identity Fraud Report 2025 DEEP DIVE Fraudster Techniques | §12.3 | not required |
| 17 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 GenAI Fuels Rise in Digital Document Fraud  | §12.3 + PH | not required |
| 18 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Historically, fraudsters have predominantly | §12.3 | not required |
| 19 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Deepfakes: The New Face of Video Biometric  | §12.3 | not required |
| 20 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 There are different types of biometric chec | §12.3 | not required |
| 21 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 The Impact of Deepfakes The prevalence of f | §12.3 | not required |
| 22 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 2023 was the first year that deepfakes beca | §12.3 + PH | not required |
| 23 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 One of the main ways that fraudsters are su | §12.4 + PH | not required |
| 24 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Fraud-as-a-service (FaaS) is the practice o | §12.4 | not required |
| 25 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Synthetic Identities: From Stolen SSNs to F | §12.4 | not required |
| 26 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Globalization and the Rise of Coordinated F | §12.4 + PH | not required |
| 27 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Fraudsters Focus Their Efforts Fraudsters t | §12.4 | not required |
| 28 | yes | LIKELY_VISUAL | Entrust / Identity Fraud Report 2025 Entrust / Identity Fraud Report 2025 LOOKIN | §12.5 + PH | not required |
| 29 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 1. The increasing use of AI AI-assisted too | §12.5 | not required |
| 30 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 2. AI (specifically deepfake) regulation Th | §12.5 | not required |
| 31 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Europe The EU AI Act is currently the world | §12.5 | not required |
| 32 | yes | TEXT_OK | 3. Data security and Zero Trust The global average cost of a data breach reached | §12.5 | not required |
| 33 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 4. Preparing for the post-quantum era By 20 | §12.5 | not required |
| 34 | yes | TEXT_OK | 6. Increased use of behavioral biometrics to detect fraud Bots are a significant | §12.5 | not required |
| 35 | yes | LIKELY_VISUAL | Entrust / Identity Fraud Report 2025 Fraud Prevention Best Practices | §12.6 | not required |
| 36 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 How To Fight Fraud at Onboarding and Beyond | §12.6 | not required |
| 37 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Check for fraud throughout the customer lif | §12.6 | not required |
| 38 | yes | LIKELY_VISUAL | Comprehensive Fraud Detection Entrust / Identity Fraud Report 2025 | §12.6 | not required |
| 39 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Respond and Adapt to Evolving Fraud With Fl | §12.6 | not required |
| 40 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 A good fraud prevention solution won't rely | §12.6 | not required |
| 41 | yes | TEXT_OK | Entrust / Identity Fraud Report 2025 Lean Into Pattern and Risk Analysis Preempt | §12.6 | not required |
| 42 | yes | TEXT_OK | ©2024 Entrust Corporation. All rights reserved. Entrust and the hexagon logo are | §12.6 | not required |

### `Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf` (103 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | LIKELY_VISUAL | Inverse Turing Test Proof of Personhood Deep Fake Detection Fabio Scotti | §0 + PH | not required |
| 2 | yes | TEXT_OK | OUTLINE - Inverse Turing Test - Proof of Personhood - Deep fake - Cosa sono - A  | §0 | not required |
| 3 | title only | LIKELY_VISUAL | Inverse Turing Test Bear up! →Coraggio! | §1.1 + PH | not required |
| 4 | yes | TEXT_OK | Inverse Turing Test (IIT) (anche chiamato Reverse Turing test) Partiamo dall'ide | §1.1 | not required |
| 5 | yes | TEXT_OK | Inverse Turing Test (IIT) E' quindi un problema di classificazione binaria - ris | §1.1 | not required |
| 6 | yes | TEXT_OK | Cosa si cerca di individuare Definizione operativa Un Inverse Turing Test è un t | §1.1 | not required |
| 7 | yes | TEXT_OK | Storicamente…. Ipotesi alla base - Per un umano: facile - Per una macchina: diff | §1.2 | not required |
| 8 | yes | TEXT_OK | Recentemente …. Problema attuale - Con CNN e Vision Transformer, molti CAPTCHA s | §1.2 | not required |
| 9 | yes | LIKELY_VISUAL | ReCAPTCHA con mouse L'uso del mouse rileva la dinamica umana | §1.2 + PH | not required |
| 10 | yes | TEXT_OK | reCAPTCHA v3 (Google) - Lavora in background analizzando il comportamento. - Sco | §1.2 | not required |
| 11 | yes | TEXT_OK | reCAPTCHA v3 (Google) Analisi comportamentale continua - All'apertura della pagi | §1.2 | not required |
| 12 | yes | TEXT_OK | BeCAPTCHA - BeCAPTCHA è un sistema di CAPTCHA comportamentale progettato per dis | §1.3 | not required |
| 13 | yes | LIKELY_VISUAL | Fusioni di tecniche Usando le conoscenze sviluppate nelle lezioni precedenti sul | §1.3 + PH | not required |
| 14 | yes | TEXT_OK | Deepfake e Inverse Turing Test - Sembrano funzionalità simili ma in realtà rispo | §1.3 | not required |
| 15 | title only | LIKELY_VISUAL | Deepfake VS Inverse Turing Test Cosa muove il mouse? | §1.3 + PH | not required |
| 16 | title only | LIKELY_VISUAL | Proof of Personhood Identità digitale e Proof of Personhood | §2.1 + PH | not required |
| 17 | yes | TEXT_OK | Proof of Personhood (PoP) Insieme di metodi, protocolli o sistemi per verificare | §2.1 | not required |
| 18 | yes | TEXT_OK | Alcuni progetti in atto - Worldcoin (ora "World") - Lo analizziamo fra poco - Pr | §2.1 | not required |
| 19 | yes | TEXT_OK | Alcuni progetti in atto - BrightID - Sistema di verifica basato su grafi sociali | §2.1 | not required |
| 20 | yes | TEXT_OK | World ID e World Coin Un esmepio di soluzione per la Proof of Personhood | §2.2 | not required |
| 21 | yes | TEXT_OK | WorldCoin → WorldID Ideazione e Prototipazione (2019 - 2022) - Obiettivo: Creare | §2.2 | not required |
| 22 | title only | LIKELY_VISUAL | Orb: the worldcoin scanner | §2.2 + PH | not required |
| 23 | yes | TEXT_OK | Componenti LED Ring Near Infrared (NIR) Range compatibile con iride ≈ 740–850 nm | §2.2 + PH | not required |
| 24 | yes | TEXT_OK | Deepfake detection 1. Verifica della "Vivacità" (Liveness Detection) - Immagini  | §2.2 | not required |
| 25 | yes | TEXT_OK | Deepfake detection 2. Risoluzione e Micro-Dettagli - Mentre i deepfake possono s | §2.2 | not required |
| 26 | yes | TEXT_OK | Passi della creazione del worldCoin FASE 1: Scansione (L'Orb) - L'occhio viene i | §2.3 | not required |
| 27 | yes | TEXT_OK | Passi della creazione del worldCoin FASE 3: Verifica (Unicità) - Il codice viene | §2.3 | not required |
| 28 | yes | TEXT_OK | Passi della creazione del worldCoin FASE 4: Attivazione (App & Token) - Il World | §2.3 | not required |
| 29 | title only | LIKELY_VISUAL | Riassumendo | §2.3 + PH | not required |
| 30 | yes | LIKELY_VISUAL | Enrollment e claim del In generale $TOKEN indica che è un asset con valore di me | §2.3 + PH | not required |
| 31 | yes | TEXT_OK | Esempio di DBC: Worldcoin (esordi) Claims - "Worldcoin is building the world's l | §2.3 | not required |
| 32 | yes | TEXT_OK | Il Progetto WORLD in sintesi Il progetto è passato dall'essere una "criptovaluta | §2.4 | not required |
| 33 | yes | TEXT_OK | Face Auth per World ID - Confronto locale per accedere all'app - Serve a sblocca | §2.4 | not required |
| 34 | yes | TEXT_OK | World Network makes its Italian debut (Rome) - Nel gennaio 2026 inzia la campagn | §2.4 | not required |
| 35 | title only | LIKELY_VISUAL | Viene mostrato in aula un filmato | §3 | not required |
| 36 | yes | TEXT_OK | Commento sui video mostrati in aula Non ho mai pronunciato nessuna di queste fra | §3 | not required |
| 37 | yes | TEXT_OK | Deepfake Il termine "deepfake" si riferisce a un contenuto creato utilizzando l' | §3 | not required |
| 38 | yes | TEXT_OK | Frodi e Deepfake Esaminando il report 2025 sulle frodi una delle maggiori firm d | §3 | not required |
| 39 | yes | TEXT_OK | Spoofing e Deepfake Sono due cose leggermente diverse… - Deepfake - Qui andremo  | §3 | not required |
| 40 | yes | TEXT_OK | Tipi di deepfake per tipo di output Video Questi sono video in cui il viso di un | §4.1 | not required |
| 41 | yes | TEXT_OK | Esempi di impiego Deepfake di Personaggi Pubblici Questi sono spesso i più noti, | §4.2 | not required |
| 42 | yes | TEXT_OK | Deepfake per i sistemi biometrici Impieghi leciti - Creazione di nuovi individui | §4.2 | not required |
| 43 | yes | TEXT_OK | Nvidia Eye Contact Feature - L'effetto "Eye Contact" sposta gli occhi del parlan | §4.2 + PH | not required |
| 44 | yes | TEXT_OK | Manipolazione/ Data augmentation - SW come Face Poke o Expression editor permett | §4.2 | not required |
| 45 | yes | TEXT_OK | Avatar e individui sintetici - Rimanere giovani e dinamici in rappresentazioni,  | §4.2 | not required |
| 46 | yes | TEXT_OK | Problematiche associate ai deepfake (impieghi illeciti) - Non vedere i Kiss live | §4.3 | not required |
| 47 | yes | TEXT_OK | Impieghi illeciti (cont.) Fake news/contents, accounts - Siamo di fronte ad un n | §4.3 | not required |
| 48 | yes | TEXT_OK | Vietato per legge: il corpo e' sotto "copyright" Prime promulgazioni - Giugno 20 | §4.3 | not required |
| 49 | title only | LIKELY_VISUAL | 3 tipi principali | §5 + PH | not required |
| 50 | yes | TEXT_OK | I face swap stanno diventando molto sofisticati interpretando il 3D del volto I  | §5 | not required |
| 51 | yes | LIKELY_VISUAL | Tecniche «classiche» morphing and image manipulation sui documenti di identifica | §5 + PH | not required |
| 52 | yes | TEXT_OK | Uso delle AI generative Diventa assolutamente facile creare immagini realistiche | §5 | not required |
| 53 | yes | TEXT_OK | Generazioni di immagini e video sintetici con le GANs Applicationi delle GANs in | §5 | not required |
| 54 | yes | LIKELY_VISUAL | Esempi di fake faces https://thispersondoesnotexist.com/ | §5 + PH | not required |
| 55 | yes | TEXT_OK | Attuali trand nel deepfake - Una recente analisi di OnFido mostra un aumento di  | §6.1 | not required |
| 56 | yes | LIKELY_VISUAL | Frodi documentali con e senza biometria Report OnFido 2025 | §6.1 + PH | not required |
| 57 | yes | TEXT_OK | Frodi documentali con e senza biometria Report OnFido 2023 Nel 2023 i truffatori | §6.1 | not required |
| 58 | yes | TEXT_OK | Aggiornamento attacchi biometrici fine 2024: video Gli attacchi ai sistemi biome | §6.1 + PH | not required |
| 59 | yes | TEXT_OK | Aggiornamento attacchi biometrici fine 2024: selfie e motion Nelle alter categor | §6.1 + PH | not required |
| 60 | yes | TEXT_OK | Alcuni tool recenti pubblici per la creazione di deepfake (Swap Face) - Swap Fac | §6.2 | not required |
| 61 | yes | TEXT_OK | Voice cloning - La clonazione della voce, è una tecnologia che utilizza l'intell | §6.2 | not required |
| 62 | yes | TEXT_OK | Utilizzi positivo del voicecloning - In caso di perdita della voce clonare la vo | §6.2 | not required |
| 63 | yes | TEXT_OK | Attacchi in real time - Free Real Time Voice Changer https://voice.ai/ - Società | §6.2 | not required |
| 64 | yes | TEXT_OK | MS Azure AI Speech text to speech avatar Azure AI Speech text to speech avatar,  | §6.2 | not required |
| 65 | yes | TEXT_OK | DeepMasterPrints esempio di attacco tramitte AI generativa Enrollment Verifica O | §6.2 + PH | not required |
| 66 | yes | TEXT_OK | Sora generazione di cameo Contesto - Sora 2 (OpenAI, Ottobre 2025): modello avan | §6.2 | not required |
| 67 | yes | TEXT_OK | Sora generazione di cameo (esempio di policy nativa) Policy di protezione dell'u | §6.2 | not required |
| 68 | title only | LIKELY_VISUAL | Approcci generali per la deepfake detection | §7 + PH | not required |
| 69 | yes | TEXT_OK | Approcci generali Analisi Forense Analisi dettagliata dei media per identificare | §7 | not required |
| 70 | yes | TEXT_OK | Approcci generali Verifica dell'Autenticità del Contenuto Si possono utilizzare  | §7 | not required |
| 71 | yes | TEXT_OK | Soluzioni basate su Blockchain - Come si sta iniziando a fare per la notizie fak | §7 | not required |
| 72 | yes | TEXT_OK | Approcci generali Rilevamento Basato su Intelligenza Artificiale Algoritmi di ap | §7 | not required |
| 73 | yes | TEXT_OK | Approcci generali Challenge-Response Authentication - In questo metodo, si chied | §7 | not required |
| 74 | yes | TEXT_OK | Challenge-response authentication Nasce nel contesto della cybersecurity. - Ques | §7 | not required |
| 75 | yes | TEXT_OK | La difesa dipende dall'input! - Singolo frame - Caratteristiche e alterazioni -  | §8 | not required |
| 76 | yes | TEXT_OK | Singolo frame - Transizione di colore - Nelle deepfake di neural puppetry e face | §8, §8.1 | not required |
| 77 | yes | TEXT_OK | Singolo frame - Sfondo o contesto sfocato - Le deepfake di StyleGAN sono buone n | §8, §8.1 + PH | not required |
| 78 | yes | TEXT_OK | La «Gestalt» manca ancora, ma per poco… La teoria della Gestalt, originaria dell | §8, §8.1 | not required |
| 79 | yes | TEXT_OK | Esempio di challange Motion biometric verification (video) - Inserire dei movime | §8, §8.2 + PH | not required |
| 80 | yes | TEXT_OK | Esempio di Challenge-Response: Sincronia parlato-video (video con audio) - Un si | §8, §8.2 | not required |
| 81 | yes | TEXT_OK | Liveness anti-spoofing by rPPG in realtime: Server Intel (x video) - Intel ha re | §8, §8.2 | not required |
| 82 | yes | TEXT_OK | Ulteriori informazioni sfruttabili Segnali di rilevamento delle frodi che posson | §8, §8.2 | not required |
| 83 | title only | LIKELY_VISUAL | Deepfake detection: performance degli umani | §9 + PH | not required |
| 84 | yes | TEXT_OK | L'uomo è accurato a trovarli? Affrontiamo un modello generativo recente - https: | §9 | not required |
| 85 | yes | TEXT_OK | Studio recente - Dr. Klaire Somoray and Dr. Dan J Miller from James Cook Univers | §9 | not required |
| 86 | yes | TEXT_OK | Altri risultati Gli studi più citati (MIT, UCL, UC Berkeley, Microsoft, Synthesi | §9 | not required |
| 87 | yes | TEXT_OK | Fattori che influenzano la performance umana Realismo percettivo - Le GAN modern | §9 | not required |
| 88 | title only | EMPTY_OR_DECORATIVE | Classificazione con AI Catena di passi | §10, §10.1 | not required |
| 89 | yes | TEXT_OK | Esistono servizi online per la detezione Servizi basilari - https://weverify.eu/ | §10, §10.1 | not required |
| 90 | yes | TEXT_OK | DeepFake-o-Meter - risposta a queste minacce, un team di ricercatori dell'Univer | §10, §10.1 | not required |
| 91 | title only | LIKELY_VISUAL | Analisi dei risultati del video fake di richiesta a Gianni | §10, §10.1 + PH | not required |
| 92 | yes | TEXT_OK | Servizi professionali - Incode Deepsight – Enterprise-grade deepfake detection - | §10, §10.1 | not required |
| 93 | yes | TEXT_OK | Catena di inferenza classica Attenzione al downsampling che farà la rete deep ne | §10, §10.2 + PH | not required |
| 94 | yes | TEXT_OK | 6 passi per l'allenamento di modelli AI per la detezione Trova un modello adatto | §10, §10.2 + PH | not required |
| 95 | title only | LIKELY_VISUAL | Per immagini e video Approcci basati su Deep learning | §10, §10.2 + PH | not required |
| 96 | yes | TEXT_OK | Uso di reti ricorrenti (RNN) per sequenze di frame Illustrazione della strategia | §10, §10.2 + PH | not required |
| 97 | yes | TEXT_OK | Database di fake per training di modelli - Deepfake Detection Challenge (meta) D | §10, §10.2 | not required |
| 98 | yes | TEXT_OK | Ci stiamo avvicinando a questa scena? - Do Androids Dream of Electric Sheep? (re | §11 | not required |
| 99 | yes | TEXT_OK | «Filosofia» biometrica - Oggi, quasi interamente la nostra esperienza è mediata  | §11 | not required |
| 100 | yes | TEXT_OK | Adattamento Continuo alle Nuove Sfide per combattere i Deepfake Innovazione Tecn | §11 | not required |
| 101 | yes | TEXT_OK | Reato penale dal 2025 Nuovo art. 612-quater c.p. Illecita diffusione di contenut | §11 | not required |
| 102 | yes | TEXT_OK | Abbiamo visto - Inverse Turing Test - Proof of Personhood - Deep fake - Cosa son | §11 | not required |
| 103 | yes | TEXT_OK | Stato dell'arte 2024 (non all'esame) - Deepfake Generation and Detection: A Benc | §11 | not required |

**Totals:** 145/145 pages accounted for · 35 placeholders · 0 pages rendered · 0 OCR.

---


---

## Lezione18 — DONE

**Lesson file(s):** `Lezione18/L18 - Re-identificazione, videosorveglianza e riconoscimento biometrico.md`

### `Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf` (116 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | LIKELY_VISUAL | Re-identificazione e Videosorveglianza Fabio Scotti fabio.scotti@unimi.it Univer | §0 + PH | not required |
| 3 | yes | TEXT_OK | Outline 1. Re-identificazione 2. Videosorveglianza (VS) Classica 3. Moduli avanz | §0 | not required |
| 4 | yes | TEXT_OK | Re-identificazione | §1 | not required |
| 5 | yes | TEXT_OK | Re-Identification (Re-ID) La re-identificazione è il processo che permette di: r | §1 | not required |
| 6 | yes | TEXT_OK | Problemi tipici della re-ID - Variazioni di posa - Illuminazione - Occlusioni -  | §1 | not required |
| 7 | yes | TEXT_OK | Re-ID: due livelli 1️⃣Corpo (Person Re-ID) - Statica - Come appare come figura c | §1 + PH | not required |
| 8 | yes | TEXT_OK | Re-ID livello 1: Corpo (Person Re-ID) Si utilizza quando: - il volto non è visib | §1 | not required |
| 9 | yes | TEXT_OK | Re-ID livello 2: Volto (Face Recognition) Si attiva quando: - il volto è visibil | §1 | not required |
| 10 | yes | TEXT_OK | Evoluzioni in corso Cross-modal person Re-ID La re-identificazione non si basa s | §1 + PH | not required |
| 11 | yes | TEXT_OK | Metriche Re-ID CMC (Rank-k) Nella re-identificazione si utilizza tipicamente la  | §2 | not required |
| 12 | yes | TEXT_OK | Metriche Re-ID CMC (Rank-k) Ecco la CMC completa per un esperimento Sull'asse or | §2 + PH | not required |
| 13 | yes | TEXT_OK | Metriche Re-ID mAP (mean Average Precision) - È la metrica standard nei benchmar | §2 + PH | not required |
| 14 | yes | TEXT_OK | Metriche Re-ID: CMC vs mAP CMC misura la probabilità che l'identità corretta com | §2 + PH | not required |
| 15 | yes | LIKELY_VISUAL | Pipeline: person detection (bounding box) | §3 + PH | not required |
| 16 | yes | TEXT_OK | Pipeline: feature extraciton and matching | §3 + PH | not required |
| 17 | yes | LIKELY_VISUAL | Esercitazione | §3 | not required |
| 18 | yes | LIKELY_VISUAL | YOLOv8n - Versione: YOLOv8 - Variant: n = nano (la più piccola) | §3 | not required |
| 19 | yes | LIKELY_VISUAL | YOLO – Estrazione Bounding Box Persone (semplificato) | §3 + PH | not required |
| 20 | yes | LIKELY_VISUAL | YOLO (basic) Estrazione Embedding | §3 | not required |
| 21 | yes | TEXT_OK | OSNet (meglio) Esrazione Embedding Modello Re-ID (OSNet) OSNet (Omni-Scale Netwo | §3 + PH | not required |
| 22 | title only | LIKELY_VISUAL | Re-ID | §3 + PH | not required |
| 23 | yes | LIKELY_VISUAL | Concetto di Control room Comune di Pescara Comune di Palermo Gestione di privati | §4 + PH | not required |
| 24 | yes | FORMULA_AMBIGUOUS | Control Room = Identità (chi sono?) Comportamento (cosa fanno?) | §4 | not required |
| 25 | yes | TEXT_OK | Control room: funzioni Scenario - Ambiente controllato (ingressi, tornelli, spor | §4 | not required |
| 26 | yes | LIKELY_VISUAL | Videosorveglianza Classica (VS) HW, SW, Infrastrutture Verso la videosorveglianz | §5 | not required |
| 27 | yes | LIKELY_VISUAL | Comparison with Different Scenarios Features of biometric systems for heterogeno | §5 + PH | not required |
| 28 | yes | TEXT_OK | App. tipiche Education Sorveglianza biometrica settore privato e governativo Ban | §5 | not required |
| 29 | yes | TEXT_OK | Mercato della VS - Il mercato dei nuovi dispositivi e delle applicazioni di VS c | §5 | not required |
| 30 | yes | TEXT_OK | Surveillance camera Sensore CMOS/CCD Chip comunicazione Accoppiamento con ottica | §6 | not required |
| 31 | yes | LIKELY_VISUAL | Zoom semplice e motorizzato Pan, Tilt, Zoom (PTZ) Zoom only PTZ | §6 + PH | not required |
| 32 | yes | TEXT_OK | Lenti per VS - Wide-angle field of view (da 74° fino ai "fisheye" di 190°) - Ott | §6 | not required |
| 33 | yes | TEXT_OK | Ottiche super grandangolari (f<=20mm)? In video sorveglianza si possono vedere 3 | §6 + PH | not required |
| 34 | yes | LIKELY_VISUAL | Scelta del FOV →focale necess. f=1.2mm!! Serve cambiare anche la risoluzione del | §6 + PH | not required |
| 35 | yes | TEXT_OK | Zoom con Focali da record Focale di arrivo = 660mm !!! (Ripasso) Si possono ragg | §6 | not required |
| 36 | yes | TEXT_OK | Esempio di sistema commerciale: Surveillance camera FUJIFILM SX800 - Focal lengh | §6 | not required |
| 37 | yes | LIKELY_VISUAL | Surveillance camera FUJIFILM SX800 | §6 + PH | not required |
| 38 | yes | LIKELY_VISUAL | Applicazioni per long range surv. cameras | §6 | not required |
| 39 | yes | LIKELY_VISUAL | Applicazione dello zoom in sorveglianza (*) (*) Notare Contrasto fra luce/ombra  | §6 + PH | not required |
| 40 | yes | FORMULA_AMBIGUOUS | Cosa sono i decibel (dB)? - un'unità di misura inizialmente utilizzata in acusti | §7 | not required |
| 41 | yes | TEXT_OK | Lumen? Lux? Candele? Heeelp! Es. per studiare e leggere in modo ottimale, è cons | §7 | not required |
| 42 | yes | TEXT_OK | Dynamic ranges (Gamme dinamiche) Qui è importante capire che range di gamma dina | §7 + PH | not required |
| 43 | yes | LIKELY_VISUAL | CCD vs CMOS in surveillance. CCD cameras have great WDR (wide dynamic range) not | §7 + PH | not required |
| 44 | yes | TEXT_OK | CMOS Sony Starlight IMX226 8MP/4K UHD Starlight IP Camera Hi3519 IMX226 Night Co | §7 | not required |
| 45 | yes | TEXT_OK | SoC dedicati alla VS Esempio Hi3519 (4K) Attraverso dei SoC (System on chip) ded | §7 | not required |
| 46 | yes | TEXT_OK | Applicazione generale Driver motori PTZ SD Board principale/Codec/Eth. CMOS Moto | §7 + PH | not required |
| 47 | yes | LIKELY_VISUAL | High Dynamic Range (HDR) in VS Multiframe HDR Long exposure Short exposure HDR | §7 + PH | not required |
| 48 | yes | TEXT_OK | HDR e WDR HDR (High Dynamic Range): - Mira a migliorare la qualità dell'immagine | §7 | not required |
| 49 | yes | LIKELY_VISUAL | Hi3519 processing on the camera WDR = Wide Dynamic Range (≠HDR) | §7 + PH | not required |
| 50 | yes | TEXT_OK | Surv. camera particolari: Dome La telecamera da esterno dome - Copertura multidi | §7.4 | not required |
| 51 | yes | TEXT_OK | Surv. Camera particolari> Explosion-proof Bispectral PTZ La Pelco ExSite Enhance | §7.4 | not required |
| 52 | yes | TEXT_OK | Infrastruttura e Protocolli nella VS (passato, ma ancora installato) Telecamere  | §8 | not required |
| 53 | yes | TEXT_OK | Infrastruttura e Protocolli nella attuale VS Telecamere IP intelligenti (edge) c | §8 | not required |
| 54 | yes | TEXT_OK | Vantaggi delle IP camera per VS (1 di 3) - Calcolo distribuito, spingendo l'inte | §8 | not required |
| 55 | yes | TEXT_OK | Vantaggi delle IP camera per VS (2 di 3) - Riduzione dei costi di cablaggio graz | §8 | not required |
| 56 | yes | TEXT_OK | Vantaggi delle IP camera per VS (3 di 3) - Trasmissione di comandi pan, tilt, zo | §8 | not required |
| 57 | yes | TEXT_OK | Svantaggi tecnologia IP Camera - Lo svantaggio principale per le telecamere IP d | §8 | not required |
| 58 | yes | TEXT_OK | Standard video H.264 H.265 Analisi delle caratteristiche più importanti di quest | §9 | not required |
| 59 | yes | TEXT_OK | Rilevanza del H.264-5-6 in VS Le potenzialità dei questi standard sono molto ril | §9 | not required |
| 60 | yes | TEXT_OK | Standard H.264/ H.265/H.266 - Lo standard H.264, noto anche come MPEG-4 Part 10  | §9 | not required |
| 61 | yes | TEXT_OK | Standard H.264/ H.265/H.266 - Sono standard molto adatti per videosorveglianza,  | §9 | not required |
| 62 | yes | TEXT_OK | Feature interessanti per VS H.264/ H.265/H.266 - Supporto HDR - Region of Intere | §9, §9.2 | not required |
| 63 | yes | LIKELY_VISUAL | Miglioramento della blocchettizzazione | §9, §9.2 + PH | not required |
| 64 | yes | TEXT_OK | Elevata compressione e Group of pictures - GoP (Group of pictures) Il codec H.26 | §9, §9.2 | not required |
| 65 | yes | LIKELY_VISUAL | Comparazione | §9, §9.2 + PH | not required |
| 66 | yes | TEXT_OK | Funzioni avanzate per sorveglianza Analisi delle caratteristiche delle Panasonic | §10 | not required |
| 67 | yes | LIKELY_VISUAL | Esempio di SV CAM commerciale | §10 | not required |
| 68 | yes | LIKELY_VISUAL | Piattaforma i-PRO Extreme Esaminiamo in questo esempio le diverse feature | §10 + PH | not required |
| 69 | yes | TEXT_OK | Gamma dinamica estrema Per vedere i volti in sempre più condizioni di luce (144d | §10 | not required |
| 70 | yes | TEXT_OK | "Intelligent Auto Mode" Oltre al WDR, offre il controllo riflessioni, migliorame | §10 | not required |
| 71 | yes | TEXT_OK | Color Night Vision - Continuare a vedere colori anche a bassissima luminosità am | §10 | not required |
| 72 | yes | TEXT_OK | Compressione Auto-VIQS - Auto-VIQS (Variable Image Quality on Specified Area) Au | §10 | not required |
| 73 | yes | LIKELY_VISUAL | Riduzione del rumore - FDF (Frequency Divided Filter) Dati da telecamere di VS P | §10 + PH | not required |
| 74 | yes | LIKELY_VISUAL | Riduzione del rumore (cont.) Dati da telecamere di VS Panasonic i-PRO EXTREME | §10 | not required |
| 75 | yes | TEXT_OK | Intelligent Face Compression La telecamera determina automaticamente quale parte | §10 | not required |
| 76 | yes | TEXT_OK | Regolazione esposizione per i volti Rilevamento dei volti la telecamera rileva f | §10 | not required |
| 77 | yes | TEXT_OK | RainWash/ ClearSight coating - Rivestimento del dome per evitare aderenza di goc | §10 | not required |
| 78 | yes | TEXT_OK | Edge processing - La biometria non è più "manda tutto al server", ma "filtra e s | §10 | not required |
| 79 | yes | TEXT_OK | Integrazione API Funzioni avanzate Antitaccheggio in VS Incidenti Nuove applicaz | §11 | not required |
| 80 | yes | TEXT_OK | Nuove applicazioni abilitate dalla VS - Monitoraggio delle folle - Vandalismo, a | §11 | not required |
| 81 | yes | LIKELY_VISUAL | App. avanzate in VS: Crowd monitoring | §11 + PH | not required |
| 82 | yes | TEXT_OK | App. avanzate in VS: Suspicious Activity Detection Analisi delle differenze fra  | §11 | not required |
| 83 | yes | LIKELY_VISUAL | https://www.labellerr.com/blog/deciphering-the-complexities-of-anomaly-detection | §11 + PH | not required |
| 84 | yes | LIKELY_VISUAL | Advanced retail - FLOW Retail at the Azena Application Store | §11 | not required |
| 85 | yes | LIKELY_VISUAL | Piattaforma AZENA | §11 + PH | not required |
| 86 | yes | TEXT_OK | Focus sul retail (vendite al dettagli e dirette al consumatore) https://www.azen | §11 | not required |
| 87 | yes | TEXT_OK | Esempio sistemi AZENA per intrusion detection (vedi lezione sicurezza fisica) -  | §11 | not required |
| 88 | yes | TEXT_OK | AZENA Flow security: Configurazione custom degli eventi di sicurezza - FLOW Secu | §11 | not required |
| 89 | yes | TEXT_OK | AI per antitaccheggio in VS - Nella piattaforma di VS è possible integrare Video | §11 | not required |
| 90 | yes | TEXT_OK | Esempio VEESION: anti taccheggio Veesion (https://veesion.io/it) - AI per indivi | §11 | not required |
| 91 | yes | TEXT_OK | Esempio VEESION: notifiche real-time - Analizza i movimenti delle persone e segn | §11 | not required |
| 92 | yes | LIKELY_VISUAL | Biometria applicata alla videosorveglianza Requisiti e teniche | §12 | not required |
| 93 | yes | LIKELY_VISUAL | Comparison of possible Scenarios Features of biometric systems for heterogenous  | §12 | not required |
| 94 | yes | LIKELY_VISUAL | Un esempio per il Face | §12 | not required |
| 95 | yes | LIKELY_VISUAL | Effetto della risoluzione sul volto | §12 + PH | not required |
| 96 | yes | TEXT_OK | Esempio di dimensionamento telecamera SV. x face biom. DISTANZA OCCHI: servono 6 | §12 | not required |
| 97 | yes | TEXT_OK | Esempi di sistemi integrati avanzati Studiamo e commentiamo le più avanzate feat | §13 | not required |
| 98 | yes | LIKELY_VISUAL | Software di VS avanzati - Pacchetti pre-installati per controlli | §13 + PH | not required |
| 99 | yes | TEXT_OK | Riassunto delle feature principali Permettere di riconoscere la persona in altre | §13 | not required |
| 100 | yes | TEXT_OK | Esempio di feature avanzata: Enterprise Face Recognition Surveillance - REAL-TIM | §13 | not required |
| 101 | yes | TEXT_OK | Feature principali da ricercare - SOFTWARE INTEGRATION - APIs capable to empower | §13 | not required |
| 102 | yes | TEXT_OK | Altre Feature molto utili per la velocità e scalabilità - ENTERPRISE READY - Fle | §13 | not required |
| 103 | yes | TEXT_OK | Feature per la parte mobile - MOBILE THREAT ALERTS - Instant surveillance match  | §13 | not required |
| 104 | yes | TEXT_OK | Feature per la privacy e sicurezza - ANTI-PROFILING - Designed to prevent profil | §13 | not required |
| 105 | yes | TEXT_OK | Feature per il reporting e l'analisi dei dati - MATCH EVENT HISTORY - Identify m | §13 | not required |
| 106 | yes | TEXT_OK | Hardware avanzato: Panasonic i-PRO® Extreme 4x4K - Limits of technology in video | §13 | not required |
| 107 | yes | TEXT_OK | Panasonic server for Facial Recognition FacePRO: WV-ASF950 - Approccio basato su | §13 | not required |
| 108 | yes | TEXT_OK | Panasonic server for Facial Recognition FacePRO:WV-ASF950 - Variazioni di angoli | §13 | not required |
| 109 | yes | LIKELY_VISUAL | Scalabilità del sistema FacePRO: WV-ASF950 Singolo server Cluster di server Gest | §13 + PH | not required |
| 110 | yes | TEXT_OK | Esempi di feature dei SW di SV avanzati - Face Search - Search the face database | §13 | not required |
| 111 | yes | LIKELY_VISUAL | Esempio di installazione completa | §13 + PH | not required |
| 112 | yes | TEXT_OK | Sistemi sorveglianza integrata BOSCH BVMS 10.1 - Sistema modulare integrato in g | §13.3 | not required |
| 113 | yes | TEXT_OK | Esempio di screening (Threat detection) Sistema SPO-NX per screening impiegato p | §13.3 | not required |
| 114 | yes | TEXT_OK | Esempio: RoboK Safety Risks at Seaport - Identify incident hotspots - Integrate  | §13.3 + PH | not required |
| 115 | yes | TEXT_OK | Integrazione di AI in VS per analytics e altre funzionalità - Per chi è interess | §13.3 | not required |
| 116 | yes | TEXT_OK | Abbiamo visto 1. Video sorveglianza classica 2. Sistemi avanzati per videosorveg | §13.3, §15 | not required |

**Totals:** 116/116 pages accounted for · 35 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione19 — DONE

**Lesson file(s):** `Lezione19/L19 - Adversarial attack, spoofing e antispoofing del volto 2D e 3D.md`

### `Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf` (129 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | LIKELY_VISUAL | L21 Spoofing e antispoofing Adversarial Attacks Detection Volto (2D e 3D) Fabio  | §0 + PH | not required |
| 2 | yes | TEXT_OK | OUTLINE Adversarial attacks Spoofing e antispoofing - Approccio classico e appro | §0 | not required |
| 3 | yes | TEXT_OK | Vettori di attacco: Framework Classico Il framework mentale tradizionale si basa | §1 | not required |
| 4 | yes | TEXT_OK | Vettori di attacco: Framework Classico Il secondo pilastro: b) Attacchi sugli in | §1 | not required |
| 5 | yes | TEXT_OK | Vettori di attacco: Framework Classico Vettori classici (vedi altri insegnamenti | §1 | not required |
| 6 | yes | TEXT_OK | Vettori di attacco: Framework Classico Vettori classici (vedi altri insegnamenti | §1 | not required |
| 7 | yes | TEXT_OK | Attacchi: Framework Avanzato Il nuovo framework mentale integra i vettori classi | §1 | not required |
| 8 | yes | TEXT_OK | Riassumendo - In un framework moderno si possono prevedere attacchi non solo sug | §1 + PH | not required |
| 9 | yes | TEXT_OK | Adversarial Attacks (parte 2) DETECTION Cambiamenti impercettibili effettuati su | §2 | not required |
| 10 | yes | TEXT_OK | Memo L'idea base Con la perturbazione si aumenta artificiosamente la similitudin | §2 | not required |
| 11 | yes | TEXT_OK | Adversarial attacks and images - Adversarial attack consiste nel modificare in m | §2 | not required |
| 12 | title only | LIKELY_VISUAL | Schema generale degli Adversarial Attacks | §2 + PH | not required |
| 13 | title only | LIKELY_VISUAL | Schema generale degli Adversarial Attacks | §2 | not required |
| 14 | yes | TEXT_OK | Perché le DNN sono vulnerabili agli attacchi avversari? - Le DNN sono vulnerabil | §2 | not required |
| 15 | yes | TEXT_OK | Adversarial attack VS deeplearn - In 2013, Szegedy et al. introduced one ot the  | §2 | not required |
| 16 | yes | LIKELY_VISUAL | Example of Adversarial attacks: the MNIST dataset Dataset: MNIST Network: LeNet  | §2 + PH | not required |
| 17 | yes | TEXT_OK | Design the minimum distorsion A CLEVER Way to Resist Adversarial Attack, Written | §2 + PH | not required |
| 18 | yes | TEXT_OK | Choice of the sample to be distorted (cont..) Chihuahua or muffin? …but the conc | §2 | not required |
| 19 | yes | TEXT_OK | Come Funzionano gli Attacchi Avversari? - Esiste una vasta varietà di diversi at | §3 | not required |
| 20 | yes | TEXT_OK | Uso del gradiente - Come una leva, si cerca di creare una minima variazione in i | §3 | not required |
| 21 | yes | TEXT_OK | White or black box attacks - White box attacks - The attacker has access to the  | §3, §3.2 | not required |
| 22 | yes | TEXT_OK | Attacchi fisici a modelli black box «switch» Con il termine «fisico» si intende  | §3, §3.2 | not required |
| 23 | yes | TEXT_OK | Non-targeted Adversarial Attack - A noise addition aiming to trick the model int | §3, §3.3 | not required |
| 24 | yes | TEXT_OK | Esempio nella visione La macchina con adesivi mimetici ad-hoc è stata erroneamen | §3, §3.3 + PH | not required |
| 25 | yes | TEXT_OK | Esempio della visione Nome del metodo: Robust Physical Perturbations (RP2) "to g | §3, §3.3 + PH | not required |
| 26 | yes | TEXT_OK | Targeted Adversarial Attack - use small amounts of imperceptible noise to transf | §3, §3.3 | not required |
| 27 | yes | LIKELY_VISUAL | Face Identity concealing via adversarial images Dataset: 1070-W dataset Network: | §3, §3.3 + PH | not required |
| 28 | yes | TEXT_OK | Compression noise - La supposizione che il rumore di compressione (per esempio a | §3, §3.3 | not required |
| 29 | yes | LIKELY_VISUAL | Adversarial attacks | §3, §3.3 + PH | not required |
| 30 | yes | TEXT_OK | Esempio per lo speech to text Alcuni esempi di exploit Capiamo bene la rilevanza | §3, §3.3 | not required |
| 31 | yes | TEXT_OK | Adversarial attacks - Molte tecniche di face recognition sono dotate di moduli i | §3, §3.3 + PH | not required |
| 32 | yes | TEXT_OK | 4 tipi di attacco avversario Evasione, Avvelenamento, Estrazione, Inferenza | §4 | not required |
| 33 | yes | TEXT_OK | 4 tipi principali di attacco avversario - Evasione Attacchi che modificano l'inp | §4 | not required |
| 34 | yes | TEXT_OK | Evasion Attacks - Gli attacchi di evasione sono i tipi di attacchi più diffusi e | §4 | not required |
| 35 | yes | TEXT_OK | Poisoning Attacks ("backdoor") - L'attaccante influenza i dati di addestramento  | §4 + PH | not required |
| 36 | yes | TEXT_OK | Estrazione - L'obiettivo dell'attacco è sondare il modello di machine learning c | §4 | not required |
| 37 | title only | LIKELY_VISUAL | Estrazione | §4 + PH | not required |
| 38 | yes | TEXT_OK | Inferenza - Gli attacchi di inferenza sono progettati per invertire il flusso di | §4 | not required |
| 39 | yes | TEXT_OK | Come ci difendiamo? In letteratura e nella pratica ci sono 3 approcci Approccio  | §4 | not required |
| 40 | yes | TEXT_OK | Cose da tenere conto per proteggersi… Approccio 1 «intelligence» - Gli avversari | §4 | not required |
| 41 | yes | TEXT_OK | Cose da tenere conto per proteggersi… Approccio 1 «intelligence» Ecco solo alcun | §4 | not required |
| 42 | yes | TEXT_OK | Cose da tenere conto per proteggersi… Approccio 1 «intelligence» Ecco solo alcun | §4 | not required |
| 43 | yes | TEXT_OK | Cose da tenere conto per proteggersi… Approccio 1 «intelligence» (cont.) Stai ut | §4 | not required |
| 44 | yes | TEXT_OK | Cose da tenere conto per proteggersi… Approccio 1 «intelligence» (cont.) Un avve | §4 | not required |
| 45 | yes | TEXT_OK | Approccio 2 per difendersi dagli Adversarial Attacks Inserire Dati nel Training! | §4 | not required |
| 46 | yes | TEXT_OK | Approccio 3 per difendersi dagli Adversarial Attacks Regolarizzazione - Le tecni | §4 | not required |
| 47 | yes | TEXT_OK | Attacchi sugli ingressi e ISO 30107 DeepFake, Attacchi sul sensore | §5 | not required |
| 48 | yes | TEXT_OK | Fake news, contents, accounts - Nuovo livello: generazione di contenuti provocat | §5 | not required |
| 49 | yes | TEXT_OK | Biometric Systems Vulnerability According to ISO terminologies, the attacks are  | §5 | not required |
| 50 | yes | TEXT_OK | Metriche attacchi Non-zero effort Non si usano FAR e FRR classici, ma APCER e BP | §5 | not required |
| 51 | yes | TEXT_OK | Metriche attacchi Non-zero effort BPCER - Bona Fide Presentation Classification  | §5 | not required |
| 52 | yes | TEXT_OK | Biometric Systems Vulnerability (cont.) (*) Function creep: the gradual widening | §5 + PH | not required |
| 53 | yes | TEXT_OK | Biometric systems vulnerability to adversarial attacks Un Sistema biometrico e'  | §5 | not required |
| 54 | yes | LIKELY_VISUAL | Adversary attacks: DIRECT vs INDIRECT | §5, §5.2 + PH | not required |
| 55 | yes | TEXT_OK | INDIRECT Attacks - From the inside of the system: - bypassing the feature extrac | §5, §5.2 | not required |
| 56 | yes | LIKELY_VISUAL | Examples of INDIRECT Attacks - Substituion in the DB, Override the biometric fin | §5, §5.2 + PH | not required |
| 57 | yes | TEXT_OK | DIRECT Attacks - Direct attacks (presentation/spoofing attacks) are performed at | §5, §5.2 | not required |
| 58 | yes | LIKELY_VISUAL | Presentation Attacks Definitions, glossary and examples | §5, §5.3 | not required |
| 59 | yes | TEXT_OK | Presentation attacks ISO standard IEC 30107-1:2016(E) - Presentation Attack (PA) | §5, §5.3 | not required |
| 60 | yes | TEXT_OK | Essere compliant ISO 30107 Cosa vuole dire quando una azienda è compliant alla I | §5, §5.3 | not required |
| 61 | yes | TEXT_OK | Impostors - Impostor (Definitions in ISO/IEC 2382-37) - subversive biometric cap | §6 | not required |
| 62 | yes | TEXT_OK | Application of cosmetics Anti-Makeup: Learning A Bi-Level Adversarial Network fo | §6 | not required |
| 63 | yes | TEXT_OK | …making appearance similar to the reference subject #1 before makeup targets sub | §6 + PH | not required |
| 64 | yes | TEXT_OK | Identity concealer - Identity concealer (Definitions in ISO/IEC 2382-37) - subve | §6 | not required |
| 65 | yes | TEXT_OK | Example of Identity concealing: camouflage from face detection https://cvdazzle. | §6 | not required |
| 66 | yes | LIKELY_VISUAL | Identity concealing | §6 + PH | not required |
| 67 | yes | TEXT_OK | Non-conformant presentation Apple Face ID technology is working with polarized l | §6 | not required |
| 68 | yes | TEXT_OK | Occlusions - The face can be occluded by beard, sunglasses or clothes (in enroll | §6 | not required |
| 69 | yes | TEXT_OK | Glossary (cont.) Liveness - the quality or state of being alive, made evident by | §6, §6.3 | not required |
| 70 | yes | TEXT_OK | Glossary (cont.) Artefact: - artificial object or representation presenting a co | §6, §6.3 | not required |
| 71 | yes | TEXT_OK | Presentation Attack Instruments Attacks at the Sensor: Standards Development on  | §6, §6.3 + PH | not required |
| 72 | yes | TEXT_OK | Professional makeup - A combination of makeup and-or artificial masks made of ar | §6, §6.3 | not required |
| 73 | yes | TEXT_OK | "Fascinazione" degli attacci I media rappresentano gli attacchi al sistema biome | §6, §6.4 | not required |
| 74 | yes | LIKELY_VISUAL | Genuine vs Attacks in real life Julian Fierrez et al. Introduction to Face Prese | §6, §6.4 + PH | not required |
| 75 | yes | LIKELY_VISUAL | Antispoofing Livelli ISO https://www.iso.org/standard/67381.html | §6, §6.4 + PH | not required |
| 76 | yes | TEXT_OK | Epidermide e volto simulati - Nel campo delle protesi e dei materiali sintetici  | §6, §6.4 | not required |
| 77 | yes | TEXT_OK | Fornitori di dati e maschere (per test) iBeta Quality Assurance è un laboratorio | §6, §6.4 | not required |
| 78 | yes | TEXT_OK | Real life spoofing: silicon masks - Conrad Zdzierak, 30, pleaded guilty in Ohio  | §6, §6.4 | not required |
| 79 | yes | TEXT_OK | The Marc Terrance George's case - TUCSON, Ariz. – Marc Terrance George, 41, of J | §6, §6.4 | not required |
| 80 | yes | TEXT_OK | Types of Presentation Attacks Artificial - Complete - gummy finger, video of fac | §6, §6.5 | not required |
| 81 | yes | TEXT_OK | Coerced: unconscious, under duress or menace ….sometime adversarial attacks can  | §6, §6.5 | not required |
| 82 | yes | TEXT_OK | ANTI-SPOOFING Metodi generali | §7 | not required |
| 83 | yes | TEXT_OK | Rilevamento dell'attacco (dentro o fuori il Sistema biometrico) Attacks at the S | §7 | not required |
| 84 | yes | LIKELY_VISUAL | Schema generale di antispoofing FR = Face Recognition | §7 + PH | not required |
| 85 | yes | TEXT_OK | Studiamo le variazioni reali delle immagini VS quelle negli attacchi Learning De | §7 + PH | not required |
| 86 | yes | LIKELY_VISUAL | Approcci multibanda Maggiori tipi di immagine possiamo sfruttare migliore sarà l | §7 | not required |
| 87 | yes | LIKELY_VISUAL | 1) Observations on infrared images - Printed images or images showed on a screen | §7 + PH | not required |
| 88 | yes | TEXT_OK | 2) Composite approaches: Visible + NIR + 3D - Using devices able to acquire info | §7 + PH | not required |
| 89 | yes | TEXT_OK | 3) Attack based on color images: image histogram analysis Examples of attacks to | §7 + PH | not required |
| 90 | yes | TEXT_OK | 3) Attack based on images and display: Moiré effect Images acquired by framing p | §7 + PH | not required |
| 91 | yes | TEXT_OK | 4) 3D analysis using a 2D sensor - Using a single camera, it is possible to appl | §7 | not required |
| 92 | yes | LIKELY_VISUAL | 5) Flusso ottico e antispoofing | §7 | not required |
| 93 | yes | TEXT_OK | 5) Flusso ottico e replay/presentation attack Nei replay/presentation attack sem | §7 + PH | not required |
| 94 | yes | TEXT_OK | 6 ) Uso di sensori ad altissimo framerate per catturare le dinamiche del tratto  | §7 | not required |
| 95 | yes | TEXT_OK | 7) Challenge-response 1) The system challenges the user with some random instruc | §7 | not required |
| 96 | yes | TEXT_OK | Antispoofing: ISO di riferimento - ISO/IEC 30107-3:2017 - Information technology | §7 | not required |
| 97 | yes | LIKELY_VISUAL | Antispoofing per il volto 2D | §8 | not required |
| 98 | yes | TEXT_OK | Epidermide e volto simulati - Nel campo delle protesi e dei materiali sintetici  | §8 | not required |
| 99 | yes | TEXT_OK | Possibili approcci Si applicano i concetti generali appena visti - Studio istogr | §8 | not required |
| 100 | yes | TEXT_OK | Approccio deep generalista singolo frame - Usando i DB pubblici di attacchi o im | §8 | not required |
| 101 | yes | TEXT_OK | Possibili controlli: battito delle palpebre video frames (b) corresponding optic | §8 + PH | not required |
| 102 | yes | TEXT_OK | Attacco e difesa della rilevazione del battito di palpebre Un attacco con masche | §8 + PH | not required |
| 103 | yes | TEXT_OK | Anti-spoofing by remote photoplethysmography Remote Photoplethysmography Corresp | §8 + PH | not required |
| 104 | yes | TEXT_OK | Anti-spoofing by rPPG in realtime: Server Intel - Intel ha realizzato nel 2022 l | §8 | not required |
| 105 | yes | LIKELY_VISUAL | Il 3D come antispoofing 3D OK | §9 | not required |
| 106 | yes | TEXT_OK | Sensori-webcam 3D, laptop e cellulari Apple Iphone X Face ID RealSense D435 Micr | §9 | not required |
| 107 | yes | LIKELY_VISUAL | D435 immagini [ripasso] Fabio Scotti - Università degli Studi di Milano Sistemi  | §9 + PH | not required |
| 108 | yes | LIKELY_VISUAL | Tecnologia Intel RealSense Permette di rilevare congruenza fra 2D e 3D della sce | §9 | not required |
| 109 | yes | LIKELY_VISUAL | Apple FaceID Sistema a luce strutturata per 3D (30.000 punti) Esempio FaceId App | §9 + PH | not required |
| 110 | yes | TEXT_OK | Spoofing IR (3D) + Visibile →Apple Face ID Bkav Corporation: A leading firm in n | §9 | not required |
| 111 | yes | TEXT_OK | Ulteriori miglioramenti alla biometria del volto - Avevamo indicato fra i princi | §9 | not required |
| 112 | yes | TEXT_OK | Controllo del volto 3D usando un sistema 2D - Partendo dal sensore più semplice  | §9 | not required |
| 113 | yes | LIKELY_VISUAL | Controllo del volto 3D usando un sistema 2D - Le caso del volto ecco un esempio  | §9 + PH | not required |
| 114 | yes | TEXT_OK | Ulteriori possibili controlli: spettrometria e termografia del volto - Usando il | §9 | not required |
| 115 | yes | FORMULA_AMBIGUOUS | Approfondimento (non richiesto all'esame) - https://www.spoofbounty.com/ - Demo  | §9 | not required |
| 116 | yes | TEXT_OK | Produzione Fake + Attacco a Response-Challenge: 3D Face Tracking and Texture Fus | §9 | not required |
| 117 | title only | LIKELY_VISUAL | FaceTech Esempi di soluzioni commerciali | §9, §9.3 | not required |
| 118 | yes | TEXT_OK | Controllo del volto 3D usando un sistema 2D - Es: ZoOm (Facetech) →avvicinare il | §9, §9.3 | not required |
| 119 | yes | TEXT_OK | Affermazioni del produttore – No test indipendenti Tecnologia riconoscimento (3D | §9, §9.3 | not required |
| 120 | yes | TEXT_OK | Tecnologia offerta da Facetech 3D FaceMap - Con 1 video da 2s sono possibili 3 r | §9, §9.3 | not required |
| 121 | yes | TEXT_OK | Antispoofing FaceTech - We have performed hundreds-of-millions of spoof attempts | §9, §9.3 | not required |
| 122 | yes | TEXT_OK | Antispoofing FaceTech - Filmato della demo sul test di liveness - (https://youtu | §9, §9.3 | not required |
| 123 | yes | LIKELY_VISUAL | Confronto fra 2D, 3D, FaceTec ATT! Fornito dal produttore | §9, §9.3 + PH | not required |
| 124 | yes | FORMULA_AMBIGUOUS | Antispoofing Livelli ISO https://www.iso.org/standard/67381.html ZoOM is passing | §9, §9.3 + PH | not required |
| 125 | title only | EMPTY_OR_DECORATIVE | BioID Esempi di soluzioni commerciali | §9.4 | not required |
| 126 | yes | TEXT_OK | Soluzione BioID - BioID GmbH è una società tedesca attiva da 20 anni specializza | §9.4 | not required |
| 127 | yes | LIKELY_VISUAL | BioID liveness-detection https://www.bioid.com/liveness-detection/ | §9.4 | not required |
| 128 | yes | TEXT_OK | BioID level 3 ISO 30107 https://www.youtube.com/watch?v=R-ySuJ-KiAQ il sistema u | §9.4 | not required |
| 129 | yes | TEXT_OK | Abbiamo visto - Adversarial Attacks Detection - Evasione - Avvelenamento - Estra | §11 | not required |

**Totals:** 129/129 pages accounted for · 35 placeholders · 0 pages rendered · 0 OCR.

---


---

## Lezione20 — DONE

**Lesson file(s):** `Lezione20/L20 - Spoofing e antispoofing di iride, impronta, palmo e voce.md`

### `Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf` (76 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | LIKELY_VISUAL | L22 Spoofing/antispoofing Iride, Impronta, Palmo, Voce Fabio Scotti Università d | §0 | not required |
| 2 | yes | TEXT_OK | OUTLINE - Iride - Impronta - Palmo (accenni) - Voce - Conclusioni | §0 | not required |
| 3 | title only | LIKELY_VISUAL | Iris IRIS | §1 | not required |
| 4 | yes | TEXT_OK | Iris recognition methods - Iris acquisition - Near infrared illumination - Natur | §1 | not required |
| 5 | yes | TEXT_OK | Iris recognition setup - High end devices: high quality digital camera - Short r | §1 + PH | not required |
| 6 | yes | TEXT_OK | Recent improvements: On-the-move iris - Advantages: - Less constrained - More us | §1 | not required |
| 7 | yes | TEXT_OK | Iris image quality - Quality factors: - Environmental - User behavior - Assessme | §1 + PH | not required |
| 8 | yes | TEXT_OK | Types of Presentation Attack - Impersonate attack - Acquire iris image and enrol | §2.1 | not required |
| 9 | yes | TEXT_OK | Types of Presentation Attack - Impersonate attack - Artefacts - Conceal attack - | §2.1 + PH | not required |
| 10 | yes | TEXT_OK | Creation of Artificial Iris Identities - Techniques for fake or artificial Iris  | §2.1 | not required |
| 11 | yes | TEXT_OK | Attack Technology on Artifacts Eye - Paper printouts - Textured contact lenses A | §2.1 + PH | not required |
| 12 | yes | TEXT_OK | Attack Technology on Artifacts Eye (cont.) - Display methods Original Iris Iris  | §2.1 + PH | not required |
| 13 | yes | TEXT_OK | Attack Technology on Artifacts Eye (cont.) - Prosthetic Eyes methods Glassy pros | §2.1 + PH | not required |
| 14 | yes | TEXT_OK | Main antispoofing datasets CASIA-Iris-Fake BERC-Iris-Fake IRIS LivDet 2017 (merg | §2.2 + PH | not required |
| 15 | yes | TEXT_OK | Benchmark Iris Database - Dataset containing fake iris samples - IIITD Iris Spoo | §2.2 | not required |
| 16 | yes | TEXT_OK | Benchmark Iris Database (cont.) - Dataset for non-uniform category of Iris sampl | §2.2 | not required |
| 17 | yes | TEXT_OK | Attacks: actual eye - Non-Conformant - Excessive eyelid closure - Small number o | §2.3 | not required |
| 18 | yes | TEXT_OK | Attacks: actual eye (cont.) - Presentation attack Using cadaver eye - Post-morte | §2.3 | not required |
| 19 | yes | TEXT_OK | PAD Techniques - Classification of methodologies - Iris a static or a dynamic ob | §3.1 + PH | not required |
| 20 | yes | TEXT_OK | Basic presentation attacks IRIS - Perspective adjustment required in the print - | §3.2 + PH | not required |
| 21 | yes | TEXT_OK | Samsung S8 iris spoofing "Chaos Computer Clubs (CCC) breaks iris recognition sys | §3.2 | not required |
| 22 | yes | TEXT_OK | Samsung S8 iris spoofing (cont.) Night vision mode for better details … or remov | §3.2 + PH | not required |
| 23 | yes | LIKELY_VISUAL | Samsung S8 iris spoofing (cont.) Using a Samsung printer…. → Histogram stretchin | §3.2 + PH | not required |
| 24 | yes | LIKELY_VISUAL | S8 attack from CCC A wet contact lens will spoof the liveness test … IRIS | §3.2 + PH | not required |
| 25 | yes | LIKELY_VISUAL | Attacco sensore iride 4 Position the print and the wet contact lens in front of  | §3.2 + PH | not required |
| 26 | yes | LIKELY_VISUAL | Basic antispoofing against fake printed contact lens IRIS Spoof Detection Scheme | §3.2 + PH | not required |
| 27 | yes | TEXT_OK | Example of antispoofing - Example of a CNN-based approach - Tessellation (1° cha | §3.3 + PH | not required |
| 28 | yes | LIKELY_VISUAL | Liveness of iris: pupil dilatation https://www.youtube.com/watch?v=DW2iwEshWME h | §3.3 + PH | not required |
| 29 | yes | TEXT_OK | Liveness of iris: micro-saccadi - Quando fissiamo un punto con gli occhi, potrem | §3.3 | not required |
| 30 | yes | TEXT_OK | Fingerprint And Contacless palmprint FINGERPRINT | §4 | not required |
| 31 | yes | TEXT_OK | Taxonomy of Spoofing Methods for fingeprints ➢Duplication of Fingerprints ➢Artif | §4 + PH | not required |
| 32 | yes | LIKELY_VISUAL | Presentation attacks: Concealing fingerprints FINGERPRINT Figura lasciata intenz | §4 + PH | not required |
| 33 | yes | TEXT_OK | Making of an Artificial Fingerprint ➢Direct Mold ➢Spoof created from live finger | §4 | not required |
| 34 | yes | TEXT_OK | Gummy Finger Production Attack without support of the target victim - Recording  | §4 | not required |
| 35 | yes | TEXT_OK | Other raw materials can be used If the spoof print layer capping the spoofer's f | §4 + PH | not required |
| 36 | yes | TEXT_OK | Non-Cooperative Spoofing Fingerprint reactivation Cadaver Fingerprint synthesis  | §4 | not required |
| 37 | yes | TEXT_OK | Tassonomia dei Metodi di Anti-Spoofing Anti-Spoofing Methods Hardware-based Soft | §5 + PH | not required |
| 38 | yes | TEXT_OK | Hardware-based Anti-Spoofing - Caratteristiche della vitalità umana sfruttate: - | §5 | not required |
| 39 | yes | TEXT_OK | Software-based Anti-Spoofing Tecniche basate su caratteristiche dinamiche: - Ela | §5 | not required |
| 40 | yes | TEXT_OK | 3D fingerprint under-the-glass sensors Esempio: Qualcomm Snapdragon (leader nel  | §5, §5.2 + PH | not required |
| 41 | yes | TEXT_OK | Example of new devices working under the glass The Synaptics Clear ID Fs9500 FIN | §5, §5.2 | not required |
| 42 | yes | TEXT_OK | Latent Variable Evolution→ MasterPrints - Le MasterPrints sono impronte digitali | §5, §5.2 + PH | not required |
| 43 | yes | TEXT_OK | An example of multimodal multiband palm-fingerprint touchless system - Velocità  | §5, §5.2 + PH | not required |
| 44 | yes | TEXT_OK | 4 multimodal palm-fingerprint touchless system! - 4-in-1 Touchless multi-biometr | §5, §5.2 | not required |
| 45 | yes | TEXT_OK | Dermalog fingerprint Scanner ZF2 Applicazioni high-end, forze dell'ordine e fore | §5, §5.2 | not required |
| 46 | yes | TEXT_OK | Bosch Suprema BioEntry W2 - Processore Quad-core leader di categoria da 1,2 GHz: | §5, §5.2 | not required |
| 47 | yes | LIKELY_VISUAL | Contactless palm authentication Amazon One https://one.amazon.com/ PALMPRINT (Co | §6 + PH | not required |
| 48 | yes | TEXT_OK | Palm-vein (Fujitsu Palmsecure) Dal produttore: "A false acceptance rate below 0. | §6 | not required |
| 49 | yes | TEXT_OK | Spoofing palm vein: pochissime pubblicazioni e informazioni, ma pare che sia pos | §6 | not required |
| 50 | yes | TEXT_OK | Spoofing palm vein https://youtu.be/dyO7BFKfiaQ - Acquisizione effettuta con una | §6 + PH | not required |
| 51 | yes | TEXT_OK | Voice Voce VOICE | §7 | not required |
| 52 | yes | TEXT_OK | La voce: un tratto fisiologico Prosodia: proprietà delle sillabe e delle unità p | §7 | not required |
| 53 | yes | TEXT_OK | Si può estrarre un ampio «spettro» of features VOICE Phonetic features Low-level | §7 + PH | not required |
| 54 | yes | TEXT_OK | Text-independent/dependent. Automatic Speaker Verification (ASV) usano - Text-de | §7 | not required |
| 55 | yes | TEXT_OK | Principali attacchi ai sistemi di Automatic Speaker Verification Attacks to AVS  | §8 | not required |
| 56 | yes | TEXT_OK | Impersonificazione/Imitazione Impersonation (mimicry) - Un attacco di spoofing i | §8, §8.1 | not required |
| 57 | yes | TEXT_OK | Replay attacks Attacco ottenuto registrando la voce del genuino o unendo piccoli | §8, §8.2 | not required |
| 58 | yes | TEXT_OK | Replay attacks rumore tipico Rumore di Quantizzazione è un artefatto introdotto  | §8, §8.2 | not required |
| 59 | yes | TEXT_OK | Replay attacks Attacco a bassa tecnologia: - Dispositivi di registrazione di alt | §8, §8.2 | not required |
| 60 | yes | TEXT_OK | Voice Conversion - VC = moduli che convertono la voce di una persona in quella d | §8, §8.3 + PH | not required |
| 61 | yes | TEXT_OK | Voice Conversion attacks e contromisure - Diversamente dall'orecchio umano, i si | §8, §8.3 | not required |
| 62 | yes | TEXT_OK | Adversarial attack a modelli Deeplearn Gli attacchi avversari ai sistemi di rico | §8, §8.3 | not required |
| 63 | yes | TEXT_OK | Speech Synthesis - La sintesi vocale (comunemente chiamata text-to-speech - TTS) | §8, §8.4 | not required |
| 64 | yes | TEXT_OK | Speech Synthesis: Vall-e - Rete realizzata da Microsoft che può imitare la voce  | §8, §8.4 | not required |
| 65 | title only | LIKELY_VISUAL | Altri produttori (https://elevenlabs.io/voice-design) | §8, §8.4 + PH | not required |
| 66 | yes | LIKELY_VISUAL | Altri produttori (MSAzure) https://ai.azure.com/explore/aiservices/speech/custom | §8, §8.4 | not required |
| 67 | yes | TEXT_OK | Artificial, non-speech-like tones - Attacco avversario in cui l'input non è una  | §8, §8.5 | not required |
| 68 | yes | TEXT_OK | Approcci multimodali: valutazione della sincronia dei movimenti delle labbra - I | §9 + PH | not required |
| 69 | yes | TEXT_OK | Esempio commerciale per face + lip movements Il sistema commerciale già in comme | §9 | not required |
| 70 | yes | TEXT_OK | Articulatory Gesture Based Liveness Detection (VoiceGesture) - Un sistema di ril | §9 + PH | not required |
| 71 | yes | TEXT_OK | Recent work: LipPass (auth.+liveness) - Il metodo analizza le sottili ma distint | §9 | not required |
| 72 | yes | TEXT_OK | Onfido + LiveProof - Mentre VoiceGesture e LipPass sono prototipali, la tecnolog | §9 | not required |
| 73 | yes | TEXT_OK | Conclusioni Sulle tecniche di spoofing e antispoofing viste nelle due lezioni | §10 | not required |
| 74 | yes | TEXT_OK | Un'osservazione generale dalla letteratura e dal mercato Dispositivi molto costo | §10 + PH | not required |
| 75 | yes | TEXT_OK | Alcune linee guida generali dalla letteratura e dal mercato - I sensori che util | §10 | not required |
| 76 | yes | FORMULA_AMBIGUOUS | = «intelligence»…. | §10 + PH | not required |

**Totals:** 76/76 pages accounted for · 32 placeholders · 0 pages rendered · 0 OCR.

---

## Lezione21 — DONE

**Lesson file(s):** `Lezione21/L21 - Ambient intelligence, pagamenti biometrici, applicazioni particolari e trend.md`

### `Lezione21_c_griglia_simulazione.pdf` (1 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Num. Dom. Risposta Data Risposta Giusta Esatto (1 punto) Sbagliato (0 punti) Not | §15 | not required |

### `Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf` (108 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Tecniche e applicazioni biometriche Fabio Scotti fabio.scotti@unimi.it Universit | §0 | not required |
| 2 | yes | TEXT_OK | Pagamenti bancari biometrici Ambient Intelligence Applicazioni particolari Fabio | §0 | not required |
| 3 | yes | TEXT_OK | In questa lezione conclusiva vedremo - Applicazioni biometriche per la Ambient i | §0 | not required |
| 4 | yes | LIKELY_VISUAL | Parte 1 Ambient intelligence e biometria | §1 + PH | not required |
| 5 | yes | TEXT_OK | Definizione di Ambient Intelligence A digital environment that proactively, but  | §1 | not required |
| 6 | yes | FORMULA_AMBIGUOUS | Aggiornamento - Nel 2007 AmI era: sensori + rete + server centrale - AmI oggi =  | §1 | not required |
| 7 | yes | TEXT_OK | Multimodal Perception - L'AmI moderna non è più mono-biometrica. - Identity = 𝑓F | §1 | not required |
| 8 | yes | TEXT_OK | Smart environments L'infrastruttura fisica (sensori, attuatori e reti) che suppo | §1 | not required |
| 9 | yes | TEXT_OK | Alcuni termini del settore - Accessible from everywhere Ubiquitous - Which integ | §1 | not required |
| 10 | yes | TEXT_OK | Alcuni termini del settore (2) - Which takes into account the execution environm | §1 | not required |
| 11 | yes | TEXT_OK | AmI Proprietà Scalability Invisibility Context-awareness Smartness Pro-action, a | §1 | not required |
| 12 | yes | LIKELY_VISUAL | Oggi casa/ufficio Domani Smart city | §2 + PH | not required |
| 13 | yes | LIKELY_VISUAL | Tecnologie biometriche per la AmI: Comparazione degli scenari Features of biomet | §2 + PH | not required |
| 14 | yes | TEXT_OK | Ambient Intelligence (AmI) Come deve essere questo ambiente? unobtrusive interco | §2 | not required |
| 15 | yes | TEXT_OK | AmI: interazione Uomo – Macchina - I tradizionali strumenti di input e output de | §2 | not required |
| 16 | yes | TEXT_OK | AmI caratteristiche ambientali - Sensibile alle esigenze dei suoi abitanti. - Ca | §2 | not required |
| 17 | yes | TEXT_OK | AmI caratteristiche chiave - Intelligence - Il sistema è sensibile al contesto,  | §2 | not required |
| 18 | yes | TEXT_OK | AmI caratteristiche Hardware - L'ambiente AmI si basa su hardware miniaturizzato | §2 | not required |
| 19 | yes | TEXT_OK | AmI & Biometrics Uso efficace, efficiente e rispettoso della privacy delle carat | §2 | not required |
| 20 | yes | TEXT_OK | Adaptivity through recognition - Person identification - for assigning known nee | §2 | not required |
| 21 | yes | LIKELY_VISUAL | Protezioni dei bambini | §3 + PH | not required |
| 22 | yes | LIKELY_VISUAL | Cucine e ristoranti Next 10 slides are from the presentation of Prof. Vincenzo P | §3 + PH | not required |
| 23 | yes | LIKELY_VISUAL | Igiene Personale | §3 + PH | not required |
| 24 | yes | LIKELY_VISUAL | Sistemi per Smart Entertainement | §3 + PH | not required |
| 25 | yes | LIKELY_VISUAL | Smart Cars Limite di potenza Configurazione Contatti …. | §3 + PH | not required |
| 26 | yes | LIKELY_VISUAL | Sistemi di Trasporto Intelligenti | §3 + PH | not required |
| 27 | yes | LIKELY_VISUAL | Gestione intelligente del traffico 0,001 m/s Soft biometrics! | §3 + PH | not required |
| 28 | title only | LIKELY_VISUAL | Smart shops | §3 + PH | not required |
| 29 | yes | LIKELY_VISUAL | Information Kiosks and Augmented Reality | §3 + PH | not required |
| 30 | title only | LIKELY_VISUAL | Ticket Office e ingressi | §3 + PH | not required |
| 31 | yes | LIKELY_VISUAL | Health Care e Ospedali | §3 + PH | not required |
| 32 | yes | TEXT_OK | Che tratto usiamo per l'AmI? Ogni tratto ha diverse proprietà e usabilità → Non  | §4 | not required |
| 33 | yes | TEXT_OK | Che biometria usare per AmI? Physiological traits Behavioral traits Soft biometr | §4 | not required |
| 34 | yes | TEXT_OK | Biometrie adatte per AmI Physiological traits Behavioral traits Soft biometric t | §4 + PH | not required |
| 35 | yes | TEXT_OK | AmI Face Recognition: miglioramento rispetto alle rotazioni 1) L'uso di Reti Neu | §4 | not required |
| 36 | yes | TEXT_OK | AmI Age Estimation: Classification, Quantization, Regression 3 classes N classes | §4 + PH | not required |
| 37 | yes | LIKELY_VISUAL | AmI Age Estimation: miglioramento rispetto alle rotazioni E' possibile fare un t | §4 + PH | not required |
| 38 | yes | TEXT_OK | K .Zhang, C. Gao, L. Guo. M. Sun, X. Yuan, T.X. Han, Z. Zhao, B. Li, Age Group a | §4 | not required |
| 39 | yes | TEXT_OK | Settore di applicazione AmI Notare come sia possibile aggiungere molti contribut | §4 | not required |
| 40 | yes | LIKELY_VISUAL | PARTE 2 Trends di mercato e di ricerca nei sistemi biometrici | §5 | not required |
| 41 | yes | TEXT_OK | Dispensa «Trends» (riassume e descrive i prossimi lucidi di questa sezione) | §5 | not required |
| 42 | yes | TEXT_OK | Trend secondo i produttori Point of sale (POS) biometrici (anche biometrici) Mob | §5 | not required |
| 43 | yes | TEXT_OK | Continuare sulla dispensa L21b - Titolo: "Advanced Biometric Technologies: Emerg | §5 | not required |
| 44 | title only | LIKELY_VISUAL | Trends | §5 + PH | not required |
| 45 | yes | TEXT_OK | Pagamenti biometrici Vediamo come il mondo bancario si sta aggiornando per front | §7, §7.1 | not required |
| 46 | yes | TEXT_OK | Introduzione della biometria nei pagamenti bancari - Il settore bancario sta abb | §7, §7.1 | not required |
| 47 | yes | TEXT_OK | Impronte su smart card Dalla memorizzazione al Match on Card | §7, §7.2 | not required |
| 48 | yes | LIKELY_VISUAL | Obiettivo 1: rendere la carta di credito biometrica come il cellulare nell'uso c | §7, §7.2 | not required |
| 49 | yes | LIKELY_VISUAL | Obiettivo 2: limitare problemi di privacy percepiti dal cliente | §7, §7.2 | not required |
| 50 | yes | LIKELY_VISUAL | Perché Match on Card (MoC)? | §7, §7.2 + PH | not required |
| 51 | yes | LIKELY_VISUAL | Fingerprint contact Match On Card | §7, §7.2 | not required |
| 52 | yes | LIKELY_VISUAL | Struttura di una card con match Migliore Attivo Passivo | §7, §7.2 + PH | not required |
| 53 | yes | TEXT_OK | Sensori per impronte su card Per le necessità della applicazione occorrono senso | §7, §7.2 | not required |
| 54 | yes | TEXT_OK | Sensore per impronte su card - NEXT sensor NB-0610-S2 is produced in materials o | §7, §7.2 | not required |
| 55 | yes | TEXT_OK | Esempio NatWest - Test della Royal Bank of Scotland (con National Westminster Ba | §7, §7.2 | not required |
| 56 | yes | TEXT_OK | Esempio Dettagli operativi - Enrolment is simple and takes as little as five min | §7, §7.2 + PH | not required |
| 57 | yes | TEXT_OK | Inizio sperimentazione in Banca Sella - Il gruppo Sella ha avviato, in collabora | §7, §7.2 | not required |
| 58 | yes | TEXT_OK | Pagamenti con iride Ulteriore evoluzione del settore che non richiede nemmeno un | §7, §7.3 | not required |
| 59 | yes | TEXT_OK | PayEye (Mastercard) Viso+Iride (multimodale) - Tecnologia Utilizzata: Riconoscim | §7, §7.3 | not required |
| 60 | yes | LIKELY_VISUAL | 500 euro Illuminatori IR, telecamere multiple con diverse focali Eyepos! | §7, §7.3 + PH | not required |
| 61 | yes | TEXT_OK | Pagamento con il palmo Ingenico ha inserito la tecnologia del palmo (IR) nei pos | §7, §7.3 | not required |
| 62 | yes | TEXT_OK | Ingenico Palm Vein (collaborazione con Fujitsu) - Obiettivi: maggiore sicurezza  | §7, §7.3 + PH | not required |
| 63 | yes | LIKELY_VISUAL | Conteggio persone 3D | §8 + PH | not required |
| 64 | yes | TEXT_OK | Esempio: AXIS People Counter - AXIS P8804 Stereo Sensor Kit. - Precise and relia | §8 | not required |
| 65 | yes | TEXT_OK | Sensore 3D per conteggio persone - Una mappa di profondità 3D consente di - affr | §8 | not required |
| 66 | yes | TEXT_OK | Iride non vincolato (guidato) Soluzioni a basso costo in arrivo sul mercato | §9 | not required |
| 67 | yes | TEXT_OK | Iride non vincolato M2-AutoTilt Iris scanner M2SYS - Large scale enrollment - Au | §9 | not required |
| 68 | yes | TEXT_OK | Features - Intuitive subject guide indicator system enables fast and repeatable  | §9 | not required |
| 69 | yes | TEXT_OK | Category Description Dimension 8 x 3.7 x 2.5" (202mm x 94mmx 62mm) Operating Ran | §9 + PH | not required |
| 70 | yes | TEXT_OK | Iride a distanza e usabilità Esempio di impiego del sistema Safran Morpho IAD co | §9 | not required |
| 71 | yes | TEXT_OK | Biometria su persone, droni e robot La biometria si è evoluta fino a diventare u | §10 | not required |
| 72 | yes | TEXT_OK | Biometria su droni Drone: Aeromobile a pilotaggio remoto | §10 | not required |
| 73 | yes | TEXT_OK | Drone biometrics Brevetto IBM Il riconoscimento biometrico nei droni è un passo  | §10 + PH | not required |
| 74 | yes | TEXT_OK | Esempio: controllo perimetrale con droni - Il controllo perimetrale delle carcer | §10 | not required |
| 75 | yes | TEXT_OK | Obiettivi dei droni Lenti asferiche SIMTOO XT - 175 Uso della camera: - Navigazi | §10 | not required |
| 76 | yes | TEXT_OK | Uso del flusso ottico - Droni e Unmanned Aerial Vehicles (UAVs) usano il flusso  | §10 | not required |
| 77 | yes | TEXT_OK | Tecnologia 3D su Droni Yuneec Typhoon H Pro Realsense Camera Drone The RealSense | §10 | not required |
| 78 | yes | TEXT_OK | Sensori adatti al volo RealSense D435 camera - Depth Field of View (FOV)—(H×V)@H | §10 | not required |
| 79 | yes | TEXT_OK | Sensori integrati 6 degrees of freedom (6DoF) The Inertial Measurement Unit (IMU | §10 | not required |
| 80 | yes | TEXT_OK | Segmentazione del volto per inseguimento automatico GeniusIdea Follow - facial r | §10 + PH | not required |
| 81 | yes | TABLE_AMBIGUOUS | Droni biometrici - Non sono commerciali, ma integrati in progetti di sicurezza ( | §10 | not required |
| 82 | yes | TEXT_OK | Esempio applicativo #2 - polizia scozzese userà un RPAS (remotely-piloted aircra | §10 | not required |
| 83 | yes | TEXT_OK | Dataset e nuove possibilità - Sono già disponibili dataset per addestrare reti i | §10 + PH | not required |
| 84 | yes | TEXT_OK | Biometria indossabile (camera based) Usi innovativi della biometria nelle forze  | §11 | not required |
| 85 | yes | TEXT_OK | Biometria indossabile, pervasiva e ubiqua - Dispositivi indossabili come smartwa | §11 | not required |
| 86 | yes | TEXT_OK | Biometric Surveillance Mobile apps/devices - MOBILE THREAT ALERTS - Instant surv | §11 | not required |
| 87 | yes | LIKELY_VISUAL | HELIX SDK Utilizzo delle biometria on board sulle body cam delle forze dell'ordi | §11 + PH | not required |
| 88 | yes | TEXT_OK | Gesture e sistemi pervasivi https://hu.ma.ne/ Il sistema AI PIN rappresenta un c | §11 | not required |
| 89 | yes | TEXT_OK | Robot biometrici Applicazioni recenti o disponibili a breve | §12 | not required |
| 90 | yes | TEXT_OK | LG CLOi Robot: Server, Porter and Shopping cart New robots are the Serving Robot | §12 | not required |
| 91 | yes | TEXT_OK | Cani da guardia robotici: Boston Dynamics SpotMini robot Boston Dynamics vende r | §12 | not required |
| 92 | yes | TEXT_OK | I CARABINIERI HANNO ARRUOLATO "SAETTA", IL PRIMO CANE ROBOT CHE EFFETTUERÀ LE RI | §12 + PH | not required |
| 93 | yes | TEXT_OK | Esistono robot sul mercato con face recognition Suzhou Pangolin Robot Corp., Ltd | §12 | not required |
| 94 | yes | TEXT_OK | Esoscheletri LG CLOi SuitBot LG CLOi SuitBot Sistemi capace di apprendere ed evo | §12 | not required |
| 95 | yes | TEXT_OK | Esoscheletri robotici I produttori di esoscheletri stanno aumentado l'offerta e  | §12 | not required |
| 96 | yes | TEXT_OK | Applicazioni innovative: uEsepressioni, EEG, Brain state, Biofeedback Accedere i | §13 | not required |
| 97 | yes | TEXT_OK | Analisi biometrica microespressioni - (Financial Times) Ping An, the financial s | §13 | not required |
| 98 | yes | LIKELY_VISUAL | Stati del cervello e biofeedback | §13 + PH | not required |
| 99 | yes | TEXT_OK | Possibilità di analisi avanzate - Stato emotivo - Biofeedback - Concentrazione - | §13 | not required |
| 100 | yes | TEXT_OK | EEG Biometrics - L'elettroencefalografia (EEG) è un metodo di monitoraggio elett | §13 | not required |
| 101 | yes | TEXT_OK | Semplificazione dell'HW per EEG Safilo – Interaxon: Eyewear http://safilox.com/  | §13 | not required |
| 102 | yes | LIKELY_VISUAL | Tecnologia Neurosky ECG EEG | §13 + PH | not required |
| 103 | yes | LIKELY_VISUAL | Neurosky MindWave Mobile 2 | §13 + PH | not required |
| 104 | yes | TEXT_OK | Muse (Interaxon) - Un archetto da indossare sulla fronte e intorno alle orecchie | §13 | not required |
| 105 | yes | TEXT_OK | Example of EEG tecnologies - Sensing Eyewear - instant feedback on what's happen | §13 | not required |
| 106 | yes | TEXT_OK | Narbis Biofeedback - Narbis utilizza il neurofeedback con un modello a penalità  | §13 | not required |
| 107 | yes | TEXT_OK | Abbiamo visto - Applicazioni biometriche per la Ambient intelligence - Caratteri | §14 | not required |
| 108 | yes | TEXT_OK | Riassumendo Attenzione alle 4P! - Performance in continuo aumento - Pervasività  | §14 | not required |

### `Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf` (28 pages)

| Page | Text extracted | Flag | Content | Destination | Visual check |
|---:|---|---|---|---|---|
| 1 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Angelo G | §6.1 | not required |
| 2 | yes | TEXT_OK | A. Genovese et al. The innovations in recent biometric systems have led to the h | §6.1 | not required |
| 3 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Table 1. | §6.1 | not required |
| 4 | yes | TEXT_OK | A. Genovese et al. Fig. 1. Examples of face images of different individuals, alo | §6.2 | not required |
| 5 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Fig. 2.  | §6.2 | not required |
| 6 | yes | TEXT_OK | A. Genovese et al. Fig. 3. Examples of images of the eyes of different individua | §6.2 | not required |
| 7 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends shows ex | §6.2 | not required |
| 8 | yes | TEXT_OK | A. Genovese et al. Fig. 4. Examples of regions of interest considered by palmpri | §6.2 | not required |
| 9 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Fig. 5.  | §6.2 + PH | not required |
| 10 | yes | TEXT_OK | A. Genovese et al. Fig. 6. Examples of images used to perform gait recognition 2 | §6.2 | not required |
| 11 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Gender:  | §6.2 | not required |
| 12 | yes | TEXT_OK | A. Genovese et al. Typically, biometric systems consider data originated from a  | §6.2 | not required |
| 13 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Feature  | §6.3 + PH | not required |
| 14 | yes | TEXT_OK | A. Genovese et al. With the increased adoption of ABC systems and e-Passports, a | §6.3 | not required |
| 15 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Fig. 9.  | §6.3 | not required |
| 16 | yes | TEXT_OK | A. Genovese et al. Fig. 10. Architecture of a biometric and health monitoring sy | §6.3 + PH | not required |
| 17 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends being in | §6.3 | not required |
| 18 | yes | TEXT_OK | A. Genovese et al. Height: 188 cm Gender: male Age: adult Fig. 11. Example of ag | §6.3 | not required |
| 19 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Fig. 12. | §6.3 | not required |
| 20 | yes | TEXT_OK | A. Genovese et al. 4 Challenges and research trends of current biometric systems | §6.4 | not required |
| 21 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends Usabilit | §6.4 + PH | not required |
| 22 | yes | TEXT_OK | A. Genovese et al. Acquisition problems ‡ Dirt ‡ Grease ‡ Swollen fingers ‡ Y Ac | §6.4 + PH | not required |
| 23 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends 4.2 Priv | §6.4 | not required |
| 24 | yes | TEXT_OK | A. Genovese et al. 4.4 Interoperability At present, biometric systems are compos | §6.4 | not required |
| 25 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends perform  | §6.4 | not required |
| 26 | yes | TEXT_OK | A. Genovese et al. 4. Anand, A., Donida Labati, R., Hanmandlu, M., Piuri, V., Sc | §6.5 | not required |
| 27 | yes | TEXT_OK | Advanced Biometric Technologies: Emerging Scenarios and Research Trends 20. Grot | §6.5 | not required |
| 28 | yes | TEXT_OK | A. Genovese et al. 40. Odinaka, I., Lai, P.H., Kaplan, A.D., O'Sullivan, J.A., S | §6.5 | not required |

**Totals:** 137/137 pages accounted for · 38 placeholders · 0 pages rendered · 0 OCR.

---

## Pending lessons — heuristic flag statistics

| Lesson | PDF | Pages | TEXT_OK | LIKELY_VISUAL | FORMULA_AMB | TABLE_AMB | EMPTY |
|---|---|---:|---:|---:|---:|---:|---:|
| 02 | Lezione_02_Principali_nozioni_teoriche_dei_sistemi_biometrici.pdf | 68 | 59 | 5 | 4 | 0 | 0 |
| 03 | Lezione_03_Approfondimento_Impronta.pdf | 59 | 52 | 6 | 1 | 0 | 0 |
| 04 | Lezione_04_Convoluzione_Approfondimento_Iride.pdf | 53 | 39 | 11 | 3 | 0 | 0 |
| 05 | Lezione_05_Apprendimento_Induttivo_Automatico_per_Applicazioni_Biometriche.pdf | 68 | 50 | 14 | 4 | 0 | 0 |
| 06 | Lezione_06_Progettazione_ModelliML_per_Applicazioni_Biometriche.pdf | 71 | 50 | 19 | 2 | 0 | 0 |
| 06 | Lezione_06_Deeplearning_Network_Matlab_toolobox_examples_DISPENSA.pdf | 15 | 14 | 1 | 0 | 0 | 0 |
| 07 | Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf | 82 | 68 | 12 | 2 | 0 | 0 |
| 07 | Lezione_07_DeepLearning_per_biometria_DISPENSA_PAPER.pdf | 34 | 32 | 0 | 2 | 0 | 0 |
| 08 | Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf | 77 | 62 | 15 | 0 | 0 | 0 |
| 09 | Lezione_09_DeepFace_e_Metodi_statistici_per_la_biometria_parte1.pdf | 82 | 62 | 8 | 10 | 2 | 0 |
| 10 | Lezione_10_Metodi_statistici_per_la_biometria_parte2.pdf | 82 | 62 | 17 | 2 | 1 | 0 |
| 10 | Lezione_10b_Best Practices Biometrics_Dispensa.pdf | 36 | 29 | 0 | 4 | 3 | 0 |
| 11 | Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf | 135 | 82 | 49 | 3 | 1 | 0 |
| 12 | Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf | 109 | 68 | 40 | 1 | 0 | 0 |
| 13 | Lezione_13_Difesa_della_Privacy_Crittazione.pdf | 111 | 87 | 21 | 3 | 0 | 0 |
| 13 | Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf | 18 | 18 | 0 | 0 | 0 | 0 |
| 14 | Lezione_14_ElevataSicurezzaFisica_ABC.pdf | 80 | 67 | 13 | 0 | 0 | 0 |
| 14 | Lezione_14_DispensaABC.pdf | 39 | 38 | 0 | 1 | 0 | 0 |
| 14 | Lezione_14_brocure_commerciale_Pelco_Physical_Security_Guide.pdf | 12 | 11 | 1 | 0 | 0 | 0 |
| 15 | Lezione_15_Metodi_Avanzati_Progettazione_UpdateTemplate_Fusione_Informazione_Multimodalita.pdf | 70 | 56 | 10 | 4 | 0 | 0 |
| 16 | Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf | 93 | 72 | 15 | 6 | 0 | 0 |
| 17 | Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf | 103 | 84 | 18 | 0 | 0 | 1 |
| 17 | Lezione_17_ONFIDO_2025-identity-fraud-report.pdf | 42 | 36 | 6 | 0 | 0 | 0 |
| 18 | Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf | 116 | 79 | 35 | 2 | 0 | 0 |
| 19 | Lezione_19_Spoofing_Adversarial_Attacks_Antispoofing_Tecniche_Volto2De3D.pdf | 129 | 100 | 26 | 2 | 0 | 1 |
| 20 | Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf | 76 | 64 | 11 | 1 | 0 | 0 |
| 21 | Lezione_21_AmbientIntelligence_ApplicazioniParticolari.pdf | 108 | 78 | 28 | 1 | 1 | 0 |
| 21 | Lezione_21_MonomodalSystems_and_Trends_DISPENSA.pdf | 28 | 28 | 0 | 0 | 0 | 0 |
| 21 | Lezione21_c_griglia_simulazione.pdf | 1 | 1 | 0 | 0 | 0 | 0 |

**Course coverage:** 94 / 2091 pages (4.5%).
