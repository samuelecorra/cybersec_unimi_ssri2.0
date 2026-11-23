# Copertura pagina-per-pagina — nuova edizione, Lezioni 09–12

Revisione integrale del 2026-11-23: le quattro lezioni sono state riscritte seguendo l'ordine delle slide 2026/27. Per ogni pagina è stato estratto testo nativo UTF-8 parola per parola (senza scartare le celle numeriche isolate, che il dump compatto omette); la presenza di figure è stata stimata dai blocchi immagine e dai tracciati vettoriali di PyMuPDF, senza rendering né OCR. I placeholder `SOURCE_VISUAL` puntano esclusivamente ai PDF in `LezioneNN/sources_2026-27/` e sono riservati a figure, grafici, schermate e schemi; testo, tabelle, formule e codice delle slide sono trascritti nel Markdown. Il materiale dell'edizione precedente non più presente nelle slide è conservato in una sezione di approfondimento dedicata in ciascuna lezione.

| Lezione | PDF | Pagine | Placeholder |
|---|---|---:|---:|
| 09 | `L09.pdf` | 91 | 48 |
| 10 | `L10-web.pdf` | 82 | 39 |
| 11 | `L11-web-v24.pdf` | 154 | 108 |
| 12 | `L12-web.pdf` | 93 | 22 |

| Lezione | Pagina | Titolo della slide | Destinazione | Trattamento | Ispezione visiva |
|---:|---:|---|---|---|---|
| 09 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica e collocazione | testo/tabella/codice trascritti | non richiesta |
| 09 | 2 | Outline | §0 Panoramica e collocazione | testo/tabella/codice trascritti | non richiesta |
| 09 | 3 | Where this lesson sits in the course | §0 Panoramica e collocazione | testo/tabella/codice trascritti | non richiesta |
| 09 | 4 | Recap | §1 Richiamo: dalle feature alle rappresentazioni | testo/tabella/codice trascritti | non richiesta |
| 09 | 5 | From pattern matching to classical ML | §1 Richiamo: dalle feature alle rappresentazioni | placeholder IS09-V01 | non richiesta |
| 09 | 6 | When we cannot design the right features | §1 Richiamo: dalle feature alle rappresentazioni | placeholder IS09-V02 | non richiesta |
| 09 | 7 | From learned features to an explicit comparison | §1 Richiamo: dalle feature alle rappresentazioni | placeholder IS09-V03 | non richiesta |
| 09 | 8 | Recall from lesson 8: a signature and a distance | §1 Richiamo: dalle feature alle rappresentazioni | placeholder IS09-V04 | non richiesta |
| 09 | 9 | Section 0 | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 10 | What is produced, and what is reused? | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 11 | Designed features and learned embeddings | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V05 | non richiesta |
| 09 | 12 | Different modalities can share one representation space | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V06 | non richiesta |
| 09 | 13 | The representation determines what “similar” means | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V07 | non richiesta |
| 09 | 14 | From a comparison score to three decisions | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 15 | CLASSIFY — map a probe to a class | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 16 | MATCH — compare a probe with a reference | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 17 | RANK — retrieve the closest stored items | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 18 | k-NN connects retrieval and classification | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V08 | non richiesta |
| 09 | 19 | k-NN: classify by the vote of the closest examples | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 20 | Nearest neighbours: the choice of k | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V09 | non richiesta |
| 09 | 21 | Scaling changes the neighbours | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V10 | non richiesta |
| 09 | 22 | Classical and current systems share an abstraction | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 23 | Image similarity: histogram comparison | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V11 | non richiesta |
| 09 | 24 | Histogram comparison: pros and cons | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | placeholder IS09-V12 | non richiesta |
| 09 | 25 | Which comparison tool answers the question? | §2 Sezione 0: rappresentazione, confronto, decisione, k-NN | testo/tabella/codice trascritti | non richiesta |
| 09 | 26 | Section A | §3 Sezione A: cross-correlazione e NCC | testo/tabella/codice trascritti | non richiesta |
| 09 | 27 | Cross-correlation searches for a shifted pattern | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V13 | non richiesta |
| 09 | 28 | One fixed shift τ0: multiply corresponding samples and add | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V14 | non richiesta |
| 09 | 29 | The output: the score at every shift | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V15 | non richiesta |
| 09 | 30 | Correlation and convolution are closely related | §3 Sezione A: cross-correlazione e NCC | testo/tabella/codice trascritti | non richiesta |
| 09 | 31 | Normalized cross-correlation (NCC) | §3 Sezione A: cross-correlazione e NCC | testo/tabella/codice trascritti | non richiesta |
| 09 | 32 | Template matching on a controlled geometric example | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V16 | non richiesta |
| 09 | 33 | Why normalize? Brightness can fool the raw sum | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V17 | non richiesta |
| 09 | 34 | Reading an NCC map: value, peak and size | §3 Sezione A: cross-correlazione e NCC | testo/tabella/codice trascritti | non richiesta |
| 09 | 35 | The same NCC map as heatmap and surface | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V18 | non richiesta |
| 09 | 36 | Correlate a pattern with itself: autocorrelation | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V19 | non richiesta |
| 09 | 37 | One kernel, many matches: a peak for every instance | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V20 | non richiesta |
| 09 | 38 | A kernel that is not in the image still has a maximum | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V21 | non richiesta |
| 09 | 39 | Peak height and peak width: show the kernel | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V22 | non richiesta |
| 09 | 40 | A kernel cut from a corner: exact match and weaker echoes | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V23 | non richiesta |
| 09 | 41 | From local correlation to convolutional neural networks | §3 Sezione A: cross-correlazione e NCC | placeholder IS09-V24 | non richiesta |
| 09 | 42 | How we read code in this lesson | §3 Sezione A: cross-correlazione e NCC | testo/tabella/codice trascritti | non richiesta |
| 09 | 43 | Section B | §4 Sezione B: retrieval | testo/tabella/codice trascritti | non richiesta |
| 09 | 44 | The retrieval pipeline: from a probe to a ranked list | §4 Sezione B: retrieval | placeholder IS09-V25 | non richiesta |
| 09 | 45 | The accuracy of retrieval: precision, recall, average precision | §4 Sezione B: retrieval | testo/tabella/codice trascritti | non richiesta |
| 09 | 46 | A good ranking is not yet a decision: the threshold gap | §4 Sezione B: retrieval | placeholder IS09-V26 | non richiesta |
| 09 | 47 | Hash or embedding: same retrieval, different signature | §4 Sezione B: retrieval | testo/tabella/codice trascritti | non richiesta |
| 09 | 48 | Section C | §5 Sezione C: lettura del codice | testo/tabella/codice trascritti | non richiesta |
| 09 | 49 | Code literacy: read a matching call (OpenCV) | §5 Sezione C: lettura del codice | testo/tabella/codice trascritti | non richiesta |
| 09 | 50 | Libraries differ: check what your call really computes | §5 Sezione C: lettura del codice | testo/tabella/codice trascritti | non richiesta |
| 09 | 51 | The same search in MATLAB and in Python | §5 Sezione C: lettura del codice | testo/tabella/codice trascritti | non richiesta |
| 09 | 52 | Code literacy: scaling and leakage in kNN | §5 Sezione C: lettura del codice | testo/tabella/codice trascritti | non richiesta |
| 09 | 53 | Section D | §6 Sezione D: trova le differenze | testo/tabella/codice trascritti | non richiesta |
| 09 | 54 | Have you noticed the puzzle? | §6 Sezione D: trova le differenze | placeholder IS09-V27 | non richiesta |
| 09 | 55 | How to automate the task? | §6 Sezione D: trova le differenze | placeholder IS09-V28 | non richiesta |
| 09 | 56 | The contract of the whole system: inputs and outputs | §6 Sezione D: trova le differenze | testo/tabella/codice trascritti | non richiesta |
| 09 | 57 | Experimental data and objective | §6 Sezione D: trova le differenze | placeholder IS09-V29 | non richiesta |
| 09 | 58 | Python experiment I - estimate the translation | §6 Sezione D: trova le differenze | placeholder IS09-V30 | non richiesta |
| 09 | 59 | Python experiment II - subtract in a signed type | §6 Sezione D: trova le differenze | placeholder IS09-V31 | non richiesta |
| 09 | 60 | Code literacy: why subtraction needs a signed type | §6 Sezione D: trova le differenze | placeholder IS09-V32 | non richiesta |
| 09 | 61 | Python experiment III - details added, details removed | §6 Sezione D: trova le differenze | placeholder IS09-V33 | non richiesta |
| 09 | 62 | Python experiment IV - candidate regions for review | §6 Sezione D: trova le differenze | placeholder IS09-V34 | non richiesta |
| 09 | 63 | Section E | §7 Sezione E: sensori e campione di riferimento | testo/tabella/codice trascritti | non richiesta |
| 09 | 64 | Same tool, another signal: the delay between two sensors | §7 Sezione E: sensori e campione di riferimento | placeholder IS09-V35 | non richiesta |
| 09 | 65 | Code: from two series to a delay in milliseconds | §7 Sezione E: sensori e campione di riferimento | testo/tabella/codice trascritti | non richiesta |
| 09 | 66 | Same tool, a product: compare a part with a golden sample | §7 Sezione E: sensori e campione di riferimento | placeholder IS09-V36 | non richiesta |
| 09 | 67 | When correlation is the wrong tool | §7 Sezione E: sensori e campione di riferimento | placeholder IS09-V37 | non richiesta |
| 09 | 68 | Section F | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 69 | Why retrieval? A calorie counter that looks at the dish | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V38 | non richiesta |
| 09 | 70 | Problem: find many pictures of a risotto | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V39 | non richiesta |
| 09 | 71 | Retrieval experiment #1: looking for the risotto images | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V40 | non richiesta |
| 09 | 72 | Retrieval experiment #1: ranking the features | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V41 | non richiesta |
| 09 | 73 | Retrieval experiment #2: which factors change the metrics? | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V42 | non richiesta |
| 09 | 74 | Retrieval experiment #2: predict before you run | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 75 | Retrieval experiment #2: the core of the script | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 76 | Retrieval experiment #2: the distance matrix | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V43 | non richiesta |
| 09 | 77 | Retrieval experiment #2: which measure survives? | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 78 | Retrieval experiment #2: your turn | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 79 | Deduplication by meaning (embeddings) | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V44 | non richiesta |
| 09 | 80 | Why not compare the text directly? | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V45 | non richiesta |
| 09 | 81 | Images and texts in one aligned space | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V46 | non richiesta |
| 09 | 82 | Embedding space: a conceptual picture (not real data) | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V47 | non richiesta |
| 09 | 83 | Four claims about retrieval: which are wrong? | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 84 | Four claims about retrieval: the answers | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 85 | Working with an assistant: ask, run, check | §8 Sezione F: retrieval al lavoro, embedding | testo/tabella/codice trascritti | non richiesta |
| 09 | 86 | Retrieval basics: query, gallery, score, ranking | §8 Sezione F: retrieval al lavoro, embedding | placeholder IS09-V48 | non richiesta |
| 09 | 87 | Main points | §9 Punti principali | testo/tabella/codice trascritti | non richiesta |
| 09 | 88 | Appendix | §10 Appendice MATLAB | testo/tabella/codice trascritti | non richiesta |
| 09 | 89 | The material (MATLAB version) | §10 Appendice MATLAB | testo/tabella/codice trascritti | non richiesta |
| 09 | 90 | MATLAB recipe: align, subtract, threshold | §10 Appendice MATLAB | testo/tabella/codice trascritti | non richiesta |
| 09 | 91 | MATLAB ↔Python: same idea, key differences | §10 Appendice MATLAB | testo/tabella/codice trascritti | non richiesta |
| 10 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica, passo 2, mappa EDA | testo/tabella/codice trascritti | non richiesta |
| 10 | 2 | Next Complete Exam Simulation | §0 Panoramica, passo 2, mappa EDA | testo/tabella/codice trascritti | non richiesta |
| 10 | 3 | Outline | §0 Panoramica, passo 2, mappa EDA | testo/tabella/codice trascritti | non richiesta |
| 10 | 4 | Step 2 of the ML workﬂow: prepare the data | §0 Panoramica, passo 2, mappa EDA | placeholder IS10-V01 | non richiesta |
| 10 | 5 | Exploratory Data Analysis for ML | §0 Panoramica, passo 2, mappa EDA | placeholder IS10-V02 | non richiesta |
| 10 | 6 | Methods matter more than the toolbox | §0 Panoramica, passo 2, mappa EDA | testo/tabella/codice trascritti | non richiesta |
| 10 | 7 | Feature Extraction | §1 Estrazione delle feature | testo/tabella/codice trascritti | non richiesta |
| 10 | 8 | Feature extraction: turning signals into usable features | §1 Estrazione delle feature | testo/tabella/codice trascritti | non richiesta |
| 10 | 9 | From a raw signal to an informative feature vector | §1 Estrazione delle feature | placeholder IS10-V03 | non richiesta |
| 10 | 10 | Check what is producing the data | §1 Estrazione delle feature | placeholder IS10-V04 | non richiesta |
| 10 | 11 | Sliding windows produce frame-wise features | §1 Estrazione delle feature | placeholder IS10-V05 | non richiesta |
| 10 | 12 | What goes into a frame feature vector? | §1 Estrazione delle feature | placeholder IS10-V06 | non richiesta |
| 10 | 13 | Time or frequency representation? | §1 Estrazione delle feature | placeholder IS10-V07 | non richiesta |
| 10 | 14 | Time versus frequency: a periodic industrial signal | §1 Estrazione delle feature | placeholder IS10-V08 | non richiesta |
| 10 | 15 | Spectrogram: frequency changes over time | §1 Estrazione delle feature | placeholder IS10-V09 | non richiesta |
| 10 | 16 | Use case: from speech to MFCC vectors | §2 Caso d'uso MFCC | testo/tabella/codice trascritti | non richiesta |
| 10 | 17 | The sample: a spoken phrase | §2 Caso d'uso MFCC | placeholder IS10-V10 | non richiesta |
| 10 | 18 | The processing chain | §2 Caso d'uso MFCC | placeholder IS10-V11 | non richiesta |
| 10 | 19 | What the computer sees: 12 numbers per window | §2 Caso d'uso MFCC | testo/tabella/codice trascritti | non richiesta |
| 10 | 20 | How the coeﬃcients are computed | §2 Caso d'uso MFCC | testo/tabella/codice trascritti | non richiesta |
| 10 | 21 | From signal to spectrogram to MFCC | §2 Caso d'uso MFCC | placeholder IS10-V12 | non richiesta |
| 10 | 22 | Mel cepstral coeﬃcients (MFCC) | §2 Caso d'uso MFCC | placeholder IS10-V13 | non richiesta |
| 10 | 23 | Three aligned views of the same 1.4 seconds | §2 Caso d'uso MFCC | placeholder IS10-V14 | non richiesta |
| 10 | 24 | From windows to words: vectors and distances | §2 Caso d'uso MFCC | placeholder IS10-V15 | non richiesta |
| 10 | 25 | Three recordings compared | §2 Caso d'uso MFCC | placeholder IS10-V16 | non richiesta |
| 10 | 26 | The three spectrograms side by side | §2 Caso d'uso MFCC | placeholder IS10-V17 | non richiesta |
| 10 | 27 | Can we tell the words apart? Two principal components | §2 Caso d'uso MFCC | placeholder IS10-V18 | non richiesta |
| 10 | 28 | A reconstructed sample and an exercise | §2 Caso d'uso MFCC | testo/tabella/codice trascritti | non richiesta |
| 10 | 29 | Feature engineering and scaling | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 30 | From features to model inputs | §3 Feature engineering e scaling | placeholder IS10-V19 | non richiesta |
| 10 | 31 | Where feature engineering ﬁts | §3 Feature engineering e scaling | placeholder IS10-V20 | non richiesta |
| 10 | 32 | Step 2 of the ML workﬂow: where we are now | §3 Feature engineering e scaling | placeholder IS10-V21 | non richiesta |
| 10 | 33 | Feature engineering is iterative | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 34 | The learning table: X and y | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 35 | AI model: Y = FUNC(X) | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 36 | Y = FUNC(X) | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 37 | Feature engineering (A): one single feature at a time | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 38 | Y = FUNC(X) | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 39 | Feature engineering (B): all features, no labels | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 40 | Y = FUNC(X) | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 41 | Selection, extraction, engineering: not synonyms | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 42 | EDA and feature engineering overlap | §3 Feature engineering e scaling | placeholder IS10-V22 | non richiesta |
| 10 | 43 | Scaling: learn shape, not just magnitude | §3 Feature engineering e scaling | placeholder IS10-V23 | non richiesta |
| 10 | 44 | Four common transformations | §3 Feature engineering e scaling | placeholder IS10-V24 | non richiesta |
| 10 | 45 | Min-max and z-score: diﬀerent invariants | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 46 | Which transform changes which property? | §3 Feature engineering e scaling | placeholder IS10-V25 | non richiesta |
| 10 | 47 | Clipping is a policy, not a default | §3 Feature engineering e scaling | placeholder IS10-V26 | non richiesta |
| 10 | 48 | Log transform compresses a long right tail | §3 Feature engineering e scaling | placeholder IS10-V27 | non richiesta |
| 10 | 49 | Recap: from raw features to model inputs | §3 Feature engineering e scaling | testo/tabella/codice trascritti | non richiesta |
| 10 | 50 | Hidden groups can reverse a conclusion | §4 Paradosso di Simpson | testo/tabella/codice trascritti | non richiesta |
| 10 | 51 | Why EDA matters: even with good feature extraction | §4 Paradosso di Simpson | testo/tabella/codice trascritti | non richiesta |
| 10 | 52 | Simpson’s paradox: regression case | §4 Paradosso di Simpson | placeholder IS10-V28 | non richiesta |
| 10 | 53 | Two production lines, two kinds of cookies | §4 Paradosso di Simpson | placeholder IS10-V29 | non richiesta |
| 10 | 54 | Simpson’s paradox: industrial measurement case | §4 Paradosso di Simpson | testo/tabella/codice trascritti | non richiesta |
| 10 | 55 | Inspect the groups before computing one mean | §4 Paradosso di Simpson | testo/tabella/codice trascritti | non richiesta |
| 10 | 56 | The reversal: measurements by product diﬃculty | §4 Paradosso di Simpson | placeholder IS10-V30 | non richiesta |
| 10 | 57 | Why it happens: group weights are diﬀerent | §4 Paradosso di Simpson | testo/tabella/codice trascritti | non richiesta |
| 10 | 58 | Compare under a common product mix | §4 Paradosso di Simpson | testo/tabella/codice trascritti | non richiesta |
| 10 | 59 | Simpson’s paradox: why EDA and clustering matter | §4 Paradosso di Simpson | testo/tabella/codice trascritti | non richiesta |
| 10 | 60 | EDA: ﬁrst part | §5 EDA prima parte | testo/tabella/codice trascritti | non richiesta |
| 10 | 61 | Descriptive statistics: one outlier changes the summary | §5 EDA prima parte | testo/tabella/codice trascritti | non richiesta |
| 10 | 62 | The mean does not describe the full shape | §5 EDA prima parte | placeholder IS10-V31 | non richiesta |
| 10 | 63 | Same summary, diﬀerent distribution | §5 EDA prima parte | placeholder IS10-V32 | non richiesta |
| 10 | 64 | A box plot summarises a distribution | §5 EDA prima parte | placeholder IS10-V33 | non richiesta |
| 10 | 65 | Outliers: use complementary plots | §5 EDA prima parte | placeholder IS10-V34 | non richiesta |
| 10 | 66 | The MATLAB box-plot: compare distributions by group | §5 EDA prima parte | placeholder IS10-V35 | non richiesta |
| 10 | 67 | EDA beyond inputs | §6 Diagnostica dell'apprendimento | testo/tabella/codice trascritti | non richiesta |
| 10 | 68 | Four locations to inspect an ML system | §6 Diagnostica dell'apprendimento | placeholder IS10-V36 | non richiesta |
| 10 | 69 | 1. Analyse model inputs | §6 Diagnostica dell'apprendimento | testo/tabella/codice trascritti | non richiesta |
| 10 | 70 | Why scaling can ease gradient-based training | §6 Diagnostica dell'apprendimento | placeholder IS10-V37 | non richiesta |
| 10 | 71 | 2. Inspect weights and gradients | §6 Diagnostica dell'apprendimento | testo/tabella/codice trascritti | non richiesta |
| 10 | 72 | 3. Inspect internal activations | §6 Diagnostica dell'apprendimento | testo/tabella/codice trascritti | non richiesta |
| 10 | 73 | 4. Inspect outputs and prediction errors | §6 Diagnostica dell'apprendimento | testo/tabella/codice trascritti | non richiesta |
| 10 | 74 | Optional lab: inspect a real dataset | §7 Laboratorio e dataset | testo/tabella/codice trascritti | non richiesta |
| 10 | 75 | Four datasets to practise on | §7 Laboratorio e dataset | placeholder IS10-V38 | non richiesta |
| 10 | 76 | Main points | §8 Punti principali e appendici | testo/tabella/codice trascritti | non richiesta |
| 10 | 77 | Appendix | §8 Punti principali e appendici | testo/tabella/codice trascritti | non richiesta |
| 10 | 78 | Two lines, 30 products each: which is better? | §8 Punti principali e appendici | testo/tabella/codice trascritti | non richiesta |
| 10 | 79 | Python solution: compare by product type | §8 Punti principali e appendici | placeholder IS10-V39 | non richiesta |
| 10 | 80 | Appendix: download the four datasets | §8 Punti principali e appendici | testo/tabella/codice trascritti | non richiesta |
| 10 | 81 | Appendix: a ﬁrst round of EDA | §8 Punti principali e appendici | testo/tabella/codice trascritti | non richiesta |
| 10 | 82 | Appendix: from here, it is your turn | §8 Punti principali e appendici | testo/tabella/codice trascritti | non richiesta |
| 11 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 11 | 2 | From data visualization to AI-assisted visual analytics | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 11 | 3 | Why EDA before ML? | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 11 | 4 | Classical visualization | §1 Visualizzazione classica | testo/tabella/codice trascritti | non richiesta |
| 11 | 5 | Visualization is used in three steps of the workﬂow | §1 Visualizzazione classica | placeholder IS11-V01 | non richiesta |
| 11 | 6 | A picture is worth a thousand words | §1 Visualizzazione classica | placeholder IS11-V02 | non richiesta |
| 11 | 7 | Introduction | §1 Visualizzazione classica | testo/tabella/codice trascritti | non richiesta |
| 11 | 8 | Why (almost) only 2D and 3D plots? | §1 Visualizzazione classica | placeholder IS11-V03 | non richiesta |
| 11 | 9 | Beyond PCA: t-SNE and UMAP on the examples of this lesson | §1 Visualizzazione classica | placeholder IS11-V04 | non richiesta |
| 11 | 10 | A cluster suggests a segment, it does not explain it | §1 Visualizzazione classica | placeholder IS11-V05 | non richiesta |
| 11 | 11 | What is data visualization? | §1 Visualizzazione classica | placeholder IS11-V06 | non richiesta |
| 11 | 12 | Data to easily understood pictures (IMAGE) | §1 Visualizzazione classica | placeholder IS11-V07 | non richiesta |
| 11 | 13 | Bertin’s 7 visual variables | §1 Visualizzazione classica | placeholder IS11-V08 | non richiesta |
| 11 | 14 | Visualization: encoding data using visual cues | §1 Visualizzazione classica | placeholder IS11-V09 | non richiesta |
| 11 | 15 | Not all visual features are of the same kind | §1 Visualizzazione classica | placeholder IS11-V10 | non richiesta |
| 11 | 16 | Image theory | §1 Visualizzazione classica | placeholder IS11-V11 | non richiesta |
| 11 | 17 | Important for users and for the designers of intelligent systems | §1 Visualizzazione classica | placeholder IS11-V12 | non richiesta |
| 11 | 18 | What chart? | §1 Visualizzazione classica | placeholder IS11-V13 | non richiesta |
| 11 | 19 | What chart? (continued) | §1 Visualizzazione classica | placeholder IS11-V14 | non richiesta |
| 11 | 20 | Comparison: bars to compare, a line for time | §1 Visualizzazione classica | placeholder IS11-V15 | non richiesta |
| 11 | 21 | Distribution: how the values of one variable are spread | §1 Visualizzazione classica | placeholder IS11-V16 | non richiesta |
| 11 | 22 | Composition: parts of a whole | §1 Visualizzazione classica | placeholder IS11-V17 | non richiesta |
| 11 | 23 | Relationship: how two or three variables move together | §1 Visualizzazione classica | placeholder IS11-V18 | non richiesta |
| 11 | 24 | What are the beneﬁts of data visualization? | §1 Visualizzazione classica | testo/tabella/codice trascritti | non richiesta |
| 11 | 25 | Beneﬁts (2) | §1 Visualizzazione classica | placeholder IS11-V19 | non richiesta |
| 11 | 26 | Conclusions: graphical displays should. . . | §1 Visualizzazione classica | placeholder IS11-V20 | non richiesta |
| 11 | 27 | Conclusions: graphical displays should. . . (2) | §1 Visualizzazione classica | placeholder IS11-V21 | non richiesta |
| 11 | 28 | Drift and monitoring | §2 Drift e monitoraggio | testo/tabella/codice trascritti | non richiesta |
| 11 | 29 | Deployment dashboards and drift monitoring | §2 Drift e monitoraggio | placeholder IS11-V22 | non richiesta |
| 11 | 30 | Population Stability Index (PSI) | §2 Drift e monitoraggio | testo/tabella/codice trascritti | non richiesta |
| 11 | 31 | PSI applied to an input feature | §2 Drift e monitoraggio | placeholder IS11-V23 | non richiesta |
| 11 | 32 | Detecting drift after deployment | §2 Drift e monitoraggio | placeholder IS11-V24 | non richiesta |
| 11 | 33 | Prediction Distribution Drift | §2 Drift e monitoraggio | testo/tabella/codice trascritti | non richiesta |
| 11 | 34 | The same index on the model output: score drift | §2 Drift e monitoraggio | placeholder IS11-V25 | non richiesta |
| 11 | 35 | Checking drift and performance in time | §2 Drift e monitoraggio | placeholder IS11-V26 | non richiesta |
| 11 | 36 | Expert visualization | §3 Visualizzazione esperta | testo/tabella/codice trascritti | non richiesta |
| 11 | 37 | Distributions in time | §3 Visualizzazione esperta | placeholder IS11-V27 | non richiesta |
| 11 | 38 | Complex phenomena | §3 Visualizzazione esperta | placeholder IS11-V28 | non richiesta |
| 11 | 39 | Vax or no Vax? Measles (Morbilli) | §3 Visualizzazione esperta | placeholder IS11-V29 | non richiesta |
| 11 | 40 | 3D surfaces | §3 Visualizzazione esperta | placeholder IS11-V30 | non richiesta |
| 11 | 41 | 3D data in time | §3 Visualizzazione esperta | placeholder IS11-V31 | non richiesta |
| 11 | 42 | 3D: slices and projections | §3 Visualizzazione esperta | placeholder IS11-V32 | non richiesta |
| 11 | 43 | 3D and surfaces: MRI (IMAGE) | §3 Visualizzazione esperta | placeholder IS11-V33 | non richiesta |
| 11 | 44 | 3D and surfaces: choose the format | §3 Visualizzazione esperta | placeholder IS11-V34 | non richiesta |
| 11 | 45 | Gallery: more tools and plots are in the appendix | §3 Visualizzazione esperta | placeholder IS11-V35 | non richiesta |
| 11 | 46 | What is behind a beautiful plot? | §3 Visualizzazione esperta | testo/tabella/codice trascritti | non richiesta |
| 11 | 47 | Simple examples with 3D data in MATLAB | §3 Visualizzazione esperta | placeholder IS11-V36 | non richiesta |
| 11 | 48 | Visualizing an AI system | §4 Visualizzare un sistema di AI | testo/tabella/codice trascritti | non richiesta |
| 11 | 49 | What do we visualize in an AI system in 2026? | §4 Visualizzare un sistema di AI | testo/tabella/codice trascritti | non richiesta |
| 11 | 50 | A system designer needs many views, one for each question | §4 Visualizzare un sistema di AI | placeholder IS11-V37 | non richiesta |
| 11 | 51 | A tour of the system, component by component | §4 Visualizzare un sistema di AI | testo/tabella/codice trascritti | non richiesta |
| 11 | 52 | Every part of the system needs its own visualization | §4 Visualizzare un sistema di AI | placeholder IS11-V38 | non richiesta |
| 11 | 53 | Multivariate sensor signals: plotting the signals | §4 Visualizzare un sistema di AI | placeholder IS11-V39 | non richiesta |
| 11 | 54 | Multivariate sensor signals: EDA correlation matrix | §4 Visualizzare un sistema di AI | placeholder IS11-V40 | non richiesta |
| 11 | 55 | Feature space with PCA: are there clusters or states? | §4 Visualizzare un sistema di AI | placeholder IS11-V41 | non richiesta |
| 11 | 56 | Visualizing a neural network | §5 Visualizzare una rete neurale | testo/tabella/codice trascritti | non richiesta |
| 11 | 57 | One small experiment for all the views | §5 Visualizzare una rete neurale | placeholder IS11-V42 | non richiesta |
| 11 | 58 | Data: projections are pictures, not measurements | §5 Visualizzare una rete neurale | placeholder IS11-V43 | non richiesta |
| 11 | 59 | What about images? Visualizing CIFAR-10 | §5 Visualizzare una rete neurale | placeholder IS11-V44 | non richiesta |
| 11 | 60 | Facets: open source visualization tool for ML training data | §5 Visualizzare una rete neurale | placeholder IS11-V45 | non richiesta |
| 11 | 61 | Model: weights as images and as histograms | §5 Visualizzare una rete neurale | placeholder IS11-V46 | non richiesta |
| 11 | 62 | Visualizing kernels in CNNs: what patterns are they detecting? | §5 Visualizzare una rete neurale | placeholder IS11-V47 | non richiesta |
| 11 | 63 | Model: activations by class | §5 Visualizzare una rete neurale | placeholder IS11-V48 | non richiesta |
| 11 | 64 | Saliency maps | §5 Visualizzare una rete neurale | placeholder IS11-V49 | non richiesta |
| 11 | 65 | Saliency maps: example | §5 Visualizzare una rete neurale | placeholder IS11-V50 | non richiesta |
| 11 | 66 | How the saliency map is computed | §5 Visualizzare una rete neurale | placeholder IS11-V51 | non richiesta |
| 11 | 67 | How to use the saliency maps | §5 Visualizzare una rete neurale | testo/tabella/codice trascritti | non richiesta |
| 11 | 68 | How to use the saliency maps: look at the pixels | §5 Visualizzare una rete neurale | placeholder IS11-V52 | non richiesta |
| 11 | 69 | Model: attention as a heat map | §5 Visualizzare una rete neurale | placeholder IS11-V53 | non richiesta |
| 11 | 70 | Exploring the parameter space: the loss landscape | §5 Visualizzare una rete neurale | placeholder IS11-V54 | non richiesta |
| 11 | 71 | Showing the optimization process | §5 Visualizzare una rete neurale | placeholder IS11-V55 | non richiesta |
| 11 | 72 | Steps 3 and 4 of the ML workﬂow: why EDA after ML? | §5 Visualizzare una rete neurale | testo/tabella/codice trascritti | non richiesta |
| 11 | 73 | Performance: training curves | §5 Visualizzare una rete neurale | placeholder IS11-V56 | non richiesta |
| 11 | 74 | Showing the conﬁdence intervals of a trained NN | §5 Visualizzare una rete neurale | placeholder IS11-V57 | non richiesta |
| 11 | 75 | How to compute the conﬁdence of a regression network | §5 Visualizzare una rete neurale | testo/tabella/codice trascritti | non richiesta |
| 11 | 76 | The interval on the plot: errors of dataset A | §5 Visualizzare una rete neurale | placeholder IS11-V58 | non richiesta |
| 11 | 77 | Where the conﬁdence of one image comes from | §5 Visualizzare una rete neurale | placeholder IS11-V59 | non richiesta |
| 11 | 78 | Data: a map of the training set (dataset cartography) | §5 Visualizzare una rete neurale | placeholder IS11-V60 | non richiesta |
| 11 | 79 | Monitoring dashboards: TensorBoard (Google) | §5 Visualizzare una rete neurale | placeholder IS11-V61 | non richiesta |
| 11 | 80 | Performance: confusion matrix | §5 Visualizzare una rete neurale | placeholder IS11-V62 | non richiesta |
| 11 | 81 | Conﬁdence and accuracy: is the model sure when right? | §5 Visualizzare una rete neurale | placeholder IS11-V63 | non richiesta |
| 11 | 82 | Performance: look at slices, not only at the average | §5 Visualizzare una rete neurale | placeholder IS11-V64 | non richiesta |
| 11 | 83 | Visualizing performance and trust | §6 Prestazioni e fiducia | testo/tabella/codice trascritti | non richiesta |
| 11 | 84 | Forecast with intervals (fan chart) | §6 Prestazioni e fiducia | placeholder IS11-V65 | non richiesta |
| 11 | 85 | Errors have a price: cost-weighted confusion matrix | §6 Prestazioni e fiducia | testo/tabella/codice trascritti | non richiesta |
| 11 | 86 | Tuning a classiﬁer: moving its ﬁnal threshold | §6 Prestazioni e fiducia | placeholder IS11-V66 | non richiesta |
| 11 | 87 | Is the model as sure as it should be? Calibration | §6 Prestazioni e fiducia | placeholder IS11-V67 | non richiesta |
| 11 | 88 | Which variables does the model use? Permutation importance | §6 Prestazioni e fiducia | placeholder IS11-V68 | non richiesta |
| 11 | 89 | Visualizing operation and decisions | §7 Funzionamento e decisioni | testo/tabella/codice trascritti | non richiesta |
| 11 | 90 | Condition monitoring: control chart | §7 Funzionamento e decisioni | placeholder IS11-V69 | non richiesta |
| 11 | 91 | Degradation curves and remaining useful life (RUL) | §7 Funzionamento e decisioni | placeholder IS11-V70 | non richiesta |
| 11 | 92 | Choosing under conﬂict: the Pareto frontier | §7 Funzionamento e decisioni | placeholder IS11-V71 | non richiesta |
| 11 | 93 | Flows: where does the material go? (Sankey) | §7 Funzionamento e decisioni | placeholder IS11-V72 | non richiesta |
| 11 | 94 | Schedules: a Gantt chart | §7 Funzionamento e decisioni | placeholder IS11-V73 | non richiesta |
| 11 | 95 | Cheat sheet: what to plot and what can go wrong | §7 Funzionamento e decisioni | testo/tabella/codice trascritti | non richiesta |
| 11 | 96 | Frontier AI as a visualization agent | §8 AI di frontiera come agente | testo/tabella/codice trascritti | non richiesta |
| 11 | 97 | The loop: designer, agent, ﬁgure | §8 AI di frontiera come agente | testo/tabella/codice trascritti | non richiesta |
| 11 | 98 | Instructions you do not see also shape the ﬁgure | §8 AI di frontiera come agente | testo/tabella/codice trascritti | non richiesta |
| 11 | 99 | What happens inside the agent while it works: the trace | §8 AI di frontiera come agente | placeholder IS11-V74 | non richiesta |
| 11 | 100 | What the agent programs: a plot is a short script | §8 AI di frontiera come agente | placeholder IS11-V75 | non richiesta |
| 11 | 101 | Four visualizations, one dataset | §9 Quattro visualizzazioni, un dataset | testo/tabella/codice trascritti | non richiesta |
| 11 | 102 | The case: the same data, four eﬀective views | §9 Quattro visualizzazioni, un dataset | testo/tabella/codice trascritti | non richiesta |
| 11 | 103 | Four views of the same eight numbers | §9 Quattro visualizzazioni, un dataset | placeholder IS11-V76 | non richiesta |
| 11 | 104 | Comparing the four views | §9 Quattro visualizzazioni, un dataset | testo/tabella/codice trascritti | non richiesta |
| 11 | 105 | Which view is the right one? It depends on the question | §9 Quattro visualizzazioni, un dataset | testo/tabella/codice trascritti | non richiesta |
| 11 | 106 | BI and monitoring dashboard tools with AI assistant | §9 Quattro visualizzazioni, un dataset | testo/tabella/codice trascritti | non richiesta |
| 11 | 107 | Example: Microsoft Power BI | §9 Quattro visualizzazioni, un dataset | placeholder IS11-V77 | non richiesta |
| 11 | 108 | The designer remains responsible | §10 Responsabilità del progettista | testo/tabella/codice trascritti | non richiesta |
| 11 | 109 | The agent does not replace the designer | §10 Responsabilità del progettista | testo/tabella/codice trascritti | non richiesta |
| 11 | 110 | Some funny slides before closing the lesson | §10 Responsabilità del progettista | testo/tabella/codice trascritti | non richiesta |
| 11 | 111 | Excessive use of data visualization (humor, not in the exam) | §10 Responsabilità del progettista | placeholder IS11-V78 | non richiesta |
| 11 | 112 | Are you creative? (not in the exam) | §10 Responsabilità del progettista | placeholder IS11-V79 | non richiesta |
| 11 | 113 | Main points | §10 Responsabilità del progettista | testo/tabella/codice trascritti | non richiesta |
| 11 | 114 | Appendix: visualization details | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 115 | A data pipeline as a graph (DAG) | §11 Appendice | placeholder IS11-V80 | non richiesta |
| 11 | 116 | Space and time: PM2.5 maps | §11 Appendice | placeholder IS11-V81 | non richiesta |
| 11 | 117 | Checking the bands: coverage of the prediction interval | §11 Appendice | placeholder IS11-V82 | non richiesta |
| 11 | 118 | Checking the forecast: residuals | §11 Appendice | placeholder IS11-V83 | non richiesta |
| 11 | 119 | Appendix: training and validation curves | §11 Appendice | placeholder IS11-V84 | non richiesta |
| 11 | 120 | Appendix: feature attribution (illustrative) | §11 Appendice | placeholder IS11-V85 | non richiesta |
| 11 | 121 | Appendix: calibration diagram | §11 Appendice | placeholder IS11-V86 | non richiesta |
| 11 | 122 | Appendix: drift and monitoring dashboard | §11 Appendice | placeholder IS11-V87 | non richiesta |
| 11 | 123 | Appendix: confusion matrix and ROC curve | §11 Appendice | placeholder IS11-V88 | non richiesta |
| 11 | 124 | Appendix: saliency map on a synthetic image | §11 Appendice | placeholder IS11-V89 | non richiesta |
| 11 | 125 | Appendix: Pareto frontier of 120 logistic plans | §11 Appendice | placeholder IS11-V90 | non richiesta |
| 11 | 126 | Appendix: parallel coordinates and learning curves | §11 Appendice | placeholder IS11-V91 | non richiesta |
| 11 | 127 | The scanned 3D penny | §11 Appendice | placeholder IS11-V92 | non richiesta |
| 11 | 128 | Visualization in ML | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 129 | Image | §11 Appendice | placeholder IS11-V93 | non richiesta |
| 11 | 130 | A fusion of methods for your applications | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 131 | Conﬁdence | §11 Appendice | placeholder IS11-V94 | non richiesta |
| 11 | 132 | Annotations, colormaps and bounds | §11 Appendice | placeholder IS11-V95 | non richiesta |
| 11 | 133 | The structure of a very basic (but complete) plot | §11 Appendice | placeholder IS11-V96 | non richiesta |
| 11 | 134 | Plot in plot, 3D line vectors | §11 Appendice | placeholder IS11-V97 | non richiesta |
| 11 | 135 | Online interactive tools: observablehq.com | §11 Appendice | placeholder IS11-V98 | non richiesta |
| 11 | 136 | Interactive visualization: more examples | §11 Appendice | placeholder IS11-V99 | non richiesta |
| 11 | 137 | Fancy plots | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 138 | R’s ggplot2 library | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 139 | R’s ggplot2 library: examples | §11 Appendice | placeholder IS11-V100 | non richiesta |
| 11 | 140 | 3D bar chart (IMAGE) | §11 Appendice | placeholder IS11-V101 | non richiesta |
| 11 | 141 | 3D bar chart (CODE) | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 142 | Find the maximum of a 2D function (IMAGE) | §11 Appendice | placeholder IS11-V102 | non richiesta |
| 11 | 143 | Find the maximum of a 2D function (CODE) | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 144 | Vectors in 3D (IMAGE) | §11 Appendice | placeholder IS11-V103 | non richiesta |
| 11 | 145 | Vectors in 3D (CODE) | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 146 | Showing a property in 3D (IMAGE) | §11 Appendice | placeholder IS11-V104 | non richiesta |
| 11 | 147 | Showing a property in 3D (CODE) | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 148 | Inspect decision surfaces adding lights (IMAGE) | §11 Appendice | placeholder IS11-V105 | non richiesta |
| 11 | 149 | Inspect decision surfaces adding lights (CODE) | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 150 | 3D data: speed vectors in a ﬂuid jet (IMAGE) | §11 Appendice | placeholder IS11-V106 | non richiesta |
| 11 | 151 | 3D data: speed vectors in a ﬂuid jet (CODE) | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 152 | 3D and surfaces: MRI (CODE) | §11 Appendice | testo/tabella/codice trascritti | non richiesta |
| 11 | 153 | Appendix: Mapper, the topology of the data (TDA) | §11 Appendice | placeholder IS11-V107 | non richiesta |
| 11 | 154 | The original Sankey ﬁgure: many bifurcations | §11 Appendice | placeholder IS11-V108 | non richiesta |
| 12 | 1 | Intelligent Systems for Industry, Supply Chain and Environment | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 12 | 2 | Lesson outline | §0 Panoramica | testo/tabella/codice trascritti | non richiesta |
| 12 | 3 | Interpretable learning | §1 Alberi di decisione | testo/tabella/codice trascritti | non richiesta |
| 12 | 4 | Decision trees for security and privacy | §1 Alberi di decisione | testo/tabella/codice trascritti | non richiesta |
| 12 | 5 | Play Tennis - training table X and label y | §1 Alberi di decisione | placeholder IS12-V01 | non richiesta |
| 12 | 6 | A tree is an explicit hypothesis | §1 Alberi di decisione | placeholder IS12-V02 | non richiesta |
| 12 | 7 | From a tree to axis-aligned regions | §1 Alberi di decisione | placeholder IS12-V03 | non richiesta |
| 12 | 8 | Explainability - readable decision paths | §1 Alberi di decisione | testo/tabella/codice trascritti | non richiesta |
| 12 | 9 | Binary and multiway splits | §1 Alberi di decisione | placeholder IS12-V04 | non richiesta |
| 12 | 10 | Fruit classiﬁcation — from objects to features | §1 Alberi di decisione | placeholder IS12-V05 | non richiesta |
| 12 | 11 | The simulated dataset — 32 examples represented as X and y | §1 Alberi di decisione | placeholder IS12-V06 | non richiesta |
| 12 | 12 | The same 32 simulated observations in feature space | §1 Alberi di decisione | placeholder IS12-V07 | non richiesta |
| 12 | 13 | From fruit measurements to decision regions | §1 Alberi di decisione | placeholder IS12-V08 | non richiesta |
| 12 | 14 | The same boundaries are simple if–then rules | §1 Alberi di decisione | placeholder IS12-V09 | non richiesta |
| 12 | 15 | How boundaries change across classiﬁers | §1 Alberi di decisione | placeholder IS12-V10 | non richiesta |
| 12 | 16 | How to check the knowledge in the tree? | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 17 | One tree - three visual viewpoints | §2 Profondità e impurità | placeholder IS12-V11 | non richiesta |
| 12 | 18 | Depth and impurity answer diﬀerent questions | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 19 | Depth of the tree - what does it measure? | §2 Profondità e impurità | placeholder IS12-V12 | non richiesta |
| 12 | 20 | From a simple tree to a very deep tree | §2 Profondità e impurità | placeholder IS12-V13 | non richiesta |
| 12 | 21 | Gini impurity - how mixed is a node? | §2 Profondità e impurità | placeholder IS12-V14 | non richiesta |
| 12 | 22 | Entropy - another measure of node uncertainty | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 23 | Why is binary entropy maximal at a balanced node? | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 24 | Minimum and maximum entropy | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 25 | Entropy and information gain are used broadly | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 26 | Gini, entropy and classiﬁcation error | §2 Profondità e impurità | placeholder IS12-V15 | non richiesta |
| 12 | 27 | Decision trees — algorithms and advantages | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 28 | Decision trees — practical disadvantages | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 29 | Decision trees — ﬁne-tuning and validation | §2 Profondità e impurità | testo/tabella/codice trascritti | non richiesta |
| 12 | 30 | Why one tree is unstable | §3 Random forest | testo/tabella/codice trascritti | non richiesta |
| 12 | 31 | Bootstrap sampling — three trees from resampled data | §3 Random forest | placeholder IS12-V16 | non richiesta |
| 12 | 32 | Random Forest — independent trees, combined predictions | §3 Random forest | placeholder IS12-V17 | non richiesta |
| 12 | 33 | Fruit example - random forest after its deﬁnition | §3 Random forest | placeholder IS12-V18 | non richiesta |
| 12 | 34 | Decision Tree vs Random Forest — when should we use each? | §3 Random forest | testo/tabella/codice trascritti | non richiesta |
| 12 | 35 | Decision tree versus random forest | §3 Random forest | testo/tabella/codice trascritti | non richiesta |
| 12 | 36 | Industrial classiﬁcation | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 37 | Tools - Decision Trees in Python | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 38 | Wine inspector - an industrial use case | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 39 | Wine pipeline - measure, train, predict | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 40 | Load the UCI Wine dataset | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 41 | Wine classes - three cultivars | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 42 | Load labels and features in Python | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 43 | Inspect X and y before training | §4 Caso d'uso UCI Wine | placeholder IS12-V19 | non richiesta |
| 12 | 44 | Train and plot a decision tree | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 45 | Wine decision tree - depth 3 | §4 Caso d'uso UCI Wine | placeholder IS12-V20 | non richiesta |
| 12 | 46 | Reading the trained tree | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 47 | Wine decision tree - depth 5 | §4 Caso d'uso UCI Wine | placeholder IS12-V21 | non richiesta |
| 12 | 48 | Classify an existing wine measurement | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 49 | Interpret the prediction output | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 50 | Classify a new measurement | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 51 | Evaluate the new-sample result carefully | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 52 | Export human-readable decision rules | §4 Caso d'uso UCI Wine | placeholder IS12-V22 | non richiesta |
| 12 | 53 | Train a Random Forest in Python | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 54 | Inspect Random Forest predictions | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 55 | Trees and forests also explain data | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 56 | Wine benchmark - avoid data leakage | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 57 | Appendix | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 58 | Appendix - oﬃcial UCI Wine access | §4 Caso d'uso UCI Wine | testo/tabella/codice trascritti | non richiesta |
| 12 | 59 | Exam simulation - in the L12 PDF | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 60 | Exam practice - prepare the approach | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 61 | Ready for the ten worked questions? | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 62 | Intelligent Systems: | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 63 | A: Intelligent Systems: | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 64 | Exam simulation – question 1: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 65 | Design | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 66 | A: Design | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 67 | Exam simulation – question 2: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 68 | Data Preprocessing: | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 69 | A: Data Preprocessing: | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 70 | Exam simulation – question 3: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 71 | Generalization | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 72 | A: Generalization | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 73 | Exam simulation – question 4: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 74 | Generalization | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 75 | A: Generalization | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 76 | Exam simulation – question 5: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 77 | #DoF | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 78 | A: #DoF | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 79 | Exam simulation – question 6: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 80 | Basic metrics in data | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 81 | Basic metrics in data | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 82 | Exam simulation – question 7: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 83 | Similarity | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 84 | Similarity | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 85 | Exam simulation – question 8: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 86 | Similarity in dataset | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 87 | Similarity in dataset | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 88 | Exam simulation – question 9: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 89 | Dataset preparation: | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 90 | Dataset preparation: | §5 Simulazione d'esame | testo/tabella/codice trascritti | risposta evidenziata letta dai riquadri vettoriali gialli, senza rendering |
| 12 | 91 | Exam simulation – question 10: classroom context | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 92 | What about topics not in the simulation? | §5 Simulazione d'esame | testo/tabella/codice trascritti | non richiesta |
| 12 | 93 | Main points | §6 Punti principali | testo/tabella/codice trascritti | non richiesta |
