# Revisione delle lezioni sull’edizione 2026/27

## Perimetro e metodo

Prima tranche: lezioni 02–12. I PDF ricevuti dall’utente sono conservati senza rinomina in `LezioneNN/sources_2026-27/`; gli originali dell’edizione precedente restano intatti per il confronto. L’estrazione nativa UTF-8 con numerazione delle pagine è in `_text_cache_2026-27/`, l’indice per pagina in `page_index_2026-27.json` e i dump compatti si generano con `python _meta/dump_lesson_edition.py N`.

La revisione confronta contenuti, struttura, esempi e dati soggetti a cambiamento. Le immagini significative non ancora presenti negli appunti ricevono il placeholder vuoto del repository; l’utente aggiungerà gli screenshot. Il rendering delle pagine è un fallback per ambiguità semantiche, formule o tabelle che il testo nativo non consente di ricostruire. I fatti normativi o di prodotto soggetti a cambiamento sono controllati su fonti primarie e datati quando utile.

| Lezione | PDF precedente / pagine | PDF 2026/27 / pagine | Differenza di paginazione | Stato | Note |
|---|---:|---:|---:|---|---|
| 02 | 1 / 84 | 1 / 78 | −6 | COMPLETATA | AI Act e proroga 2026, interazione naturale, agenti e quadro di opinioni aggiornati; 10 leggi gestaltiche integrate dalla tavola p. 56. Copertura completa in `SOURCE_COVERAGE_2026-27_L02.md`; 1 pagina resa per ambiguità semantica, 0 OCR. |
| 03 | 2 / 87 | 2 / 92 | +5 | TESTO ALLINEATO | Aggiornati test di Turing, benchmark 2026, ARC-AGI-3, tassonomia a due assi/self-supervised, bias induttivo, tre casi applicativi, metriche e stack riproducibile. Copertura pagina-sezione L03–05 in `SOURCE_COVERAGE_2026-27_L03-L05.md`; nessun rendering/OCR. |
| 04 | 1 / 77 | 1 / 77 | 0 | TESTO ALLINEATO | Aggiornati conteggi pubblicati/attivi dei modelli, famiglie 2026 e strategia di deployment; rimosse stime non ufficiali. Copertura pagina-sezione L03–05 registrata; final audit delle domande d’esame e dei residui meta da completare. |
| 05 | 1 / 72 | 1 / 74 | +2 | TESTO ALLINEATO | Integrati guardrail per agenti, servizi AutoML correnti e autoresearch con cautela sulla validation; aggiornati riferimenti edge/IoT. Copertura pagina-sezione L03–05 registrata; restano immagini legacy da sostituire manualmente dove necessario. |
| 06 | 1 / 85 | 1 / 85 | 0 | TESTO ALLINEATO | Integrati armonizzazione multifornitore, conversioni eoliche, MCAR/MAR/MNAR, leakage dell’imputazione e ricerca vettoriale/HNSW; stima GPS aggiornata con le ipotesi 2026. Copertura dettagliata in `SOURCE_COVERAGE_L06-08_2026-27.md`. |
| 07 | 1 / 73 | 1 / 82 | +9 | TESTO ALLINEATO | Integrati laboratorio con coding agent, DINOv3 come esempio datato e controlli nuovi di leakage: split, negative control, validation avversaria e audit degli agenti. Copertura dettagliata in `SOURCE_COVERAGE_L06-08_2026-27.md`. |
| 08 | 1 / 69 | 2 / 95 | +26 | TESTO ALLINEATO | Integrati provenienza/disaccordo delle etichette, ritardi, livelli di similarità, embedding/ANN e corrispondenze locali; coperto il supplemento mind map. Copertura dettagliata in `SOURCE_COVERAGE_L06-08_2026-27.md`. |
| 09 | 1 / 69 | 1 / 91 | +22 | COMPLETATA | Riscritta il 2026-11-23 nell'ordine delle 91 slide (sezioni 0, A–F, appendice MATLAB); 48 placeholder solo su `L09.pdf`; Colab sugli hash e percorso MATLAB in due passi conservati nel §11. Copertura in `SOURCE_COVERAGE_2026-27_L09-L12.md`. |
| 10 | 1 / 76 | 1 / 82 | +6 | COMPLETATA | Riscritta il 2026-11-23: estrazione delle feature, MFCC con tabella dei coefficienti, feature engineering (A)/(B)/(C) e scaling, Simpson con appendice Python, EDA e diagnostica; 39 placeholder su `L10-web.pdf`; soluzioni dei compiti di correlazione conservate nel §9. |
| 11 | 1 / 100 | 1 / 154 | +54 | COMPLETATA | Riscritta il 2026-11-23 sulle 154 slide v24: visualizzazione classica, PSI con tabella, esperimento sulle cifre, prestazioni e costi, operazione, agenti, quattro viste di Simpson, appendice MATLAB; 108 placeholder su `L11-web-v24.pdf`; nota AI Act 2026/1744 conservata. |
| 12 | 2 / 113 | 1 / 93 | −20 | COMPLETATA | Riscritta il 2026-11-23: alberi, Gini/entropia con dimostrazione del massimo, bootstrap svolto, random forest, Wine, 10 quesiti con risposte lette dai riquadri gialli vettoriali; le 10 domande aggiuntive della simulazione 2025/26 conservate nel §6.2; 22 placeholder su `L12-web.pdf`. |

## Estrazione della prima tranche

- PDF estratti: **13**.
- Pagine estratte: **1.003**.
- Flag euristici: 902 `TEXT_OK`, 49 `LIKELY_VISUAL`, 52 `FORMULA_AMBIGUOUS`, 0 `TABLE_AMBIGUOUS`, 0 `EXTRACTION_FAILED`.
- Le etichette sono indizi per la lettura, non richiedono automaticamente rendering. Per L02 è stata resa soltanto p. 56 perché l’estrazione non conteneva le leggi gestaltiche raffigurate; nessun OCR. Le altre lezioni mantengono la strategia text-first.

## Stato editoriale e limite residuo

Le lezioni 02–12 sono state confrontate con i PDF 2026/27 e il testo è stato riallineato. I placeholder presenti corrispondono uno a uno ai commenti `SOURCE_VISUAL`. Restavano **180 riferimenti visuali preesistenti** la cui pagina aggiornata non è stata associata con sufficiente affidabilità; sono elencati in `VISUAL_REMAP_PENDING_2026-27.md`, mantenuti senza modifiche per evitare indicazioni errate. Per quei casi il PDF nuovo corretto è indicato nella tabella e la descrizione aiuta a individuare la figura, ma il numero pagina va ancora riallineato prima che il riferimento sia pronto per lo screenshot.

## Registro di completamento

Una lezione passa a `COMPLETATA` solo dopo lettura del nuovo PDF pagina per pagina, confronto con la lezione e con la versione precedente, aggiornamento end-to-end del Markdown, collocazione dei placeholder pertinenti, controllo dei fatti dinamici e aggiornamento della copertura pagina-sezione.


## Seconda tranche: lezioni 13–24

I PDF `L13-web.pdf`–`L24-web.pdf` sono stati copiati byte per byte dalle cartelle Downloads in `LezioneNN/sources_2026-27/`. Estrazione nativa UTF-8 pagina per pagina completata; nessuna pagina ha avuto un fallimento di estrazione. Dump compatti disponibili con `dump_lesson_edition.py N 2026-27`.

| Lezione | PDF | Pagine 2026/27 | Stato | Note |
|---|---:|---:|---|---|
| 13 | 1 | 70 | COMPLETATA | Tokenizzazione/BPE, sequenze, Transformer e multimodalità riallineati; modelli e prezzi verificati su fonti ufficiali. Copertura completa. |
| 14 | 1 | 97 | COMPLETATA | Ottimizzazione, bilanciamento, split e validazione riallineati; aggiunte metriche finali della nuova edizione. Copertura completa. |
| 15 | 1 | 99 | COMPLETATA | Applicazioni agentiche, permessi, arresto, Rule of Two e calendario AI Act aggiornati. Copertura completa. |
| 16 | 1 | 116 | COMPLETATA | Zero/few-shot, valutazione e laboratorio aggiornati; distinta normalizzazione L2 da min-max. Copertura completa. |
| 17 | 1 | 89 | COMPLETATA | Contesto agentico, bias-varianza, regressori/classificatori, laboratori e visuali riallineati. |
| 18 | 1 | 93 | COMPLETATA | Selezione/RFE, riduzione dimensionale, embedding, System 1 e snapshot dei modelli decisionali aggiornati. |
| 19 | 1 | 112 | COMPLETATA | Classificatore Bayes-ottimo, k-NN, clustering, sistemi ibridi e visuali riallineati. |
| 20 | 1 | 76 | COMPLETATA | Curse of dimensionality, perceptron, reti feed-forward e appendice aggiornati. |
| 21 | 1 | 109 | COMPLETATA | Aggiunti laboratorio Keras/MLP, XOR, funzioni di perdita e CNN-A su MNIST. |
| 22 | 1 | 102 | COMPLETATA | Integrati MAE, LoRA, RandAugment/AugMix, Copy-Paste e apprendimento contrastivo. |
| 23 | 1 | 112 | COMPLETATA | Integrati SAM 3, hardware aggiornato e fonti ufficiali per le TPU Ironwood. |
| 24 | 1 | 121 | COMPLETATA | Integrati ViT, DINOv3 e aggiornamenti AIoT/cloud e decorrenze normative. |

Totale tranche: **12 PDF, 1.196 pagine**. Estratte in modalità text-first; flag euristici: **919 TEXT_OK**, **68 LIKELY_VISUAL**, **59 FORMULA_AMBIGUOUS**, **0 TABLE_AMBIGUOUS**, **0 EMPTY/FAILED**. Le categorie sono indicatori, non richiedono rendering automatico.


Copertura pagina-sezione per le lezioni 13–24: `SOURCE_COVERAGE_2026-27_L13-L16.md`, `SOURCE_COVERAGE_2026-27_L17-L20.md` e `SOURCE_COVERAGE_2026-27_L21-L24.md`; **1.196/1.196 pagine** registrate. Per questa tranche non sono state renderizzate pagine né usato OCR; i PDF sono stati elaborati con estrazione nativa. I placeholder delle 12 lezioni sono stati controllati e puntano ai PDF aggiornati.


## Aggiornamento 2026-11-23 — Lezioni 09–12

Le lezioni 09–12 sono state riscritte integralmente seguendo l'impaginazione dei PDF 2026/27. Tutti i loro placeholder puntano ora ai nuovi PDF (217 in totale), quindi gli 87 riferimenti legacy di queste lezioni sono stati rimossi da `VISUAL_REMAP_PENDING_2026-27.md`. Restano da riallineare i riferimenti legacy delle lezioni 02–08 elencati in quel file.

## Aggiornamento 2026-11-23 — Lezioni 13–24

Dopo le lezioni 09–12, anche le lezioni 13–24 sono state riscritte end-to-end nell'ordine delle slide 2026/27, con la stessa procedura: estrazione parola per parola con PyMuPDF (comprese le celle numeriche isolate), stima delle figure da blocchi immagine e tracciati vettoriali, nessun rendering né OCR. Il controllo iniziale aveva mostrato placeholder mancanti su molte pagine visive e contenuti compressi rispetto alle nuove slide. Testo, tabelle, formule e codice sono trascritti e resi narrativi; i placeholder sono riservati a figure, grafici, schermate e schemi e puntano solo ai PDF `sources_2026-27/`. Le note didattiche, il codice ricostruito e i paragrafi datati dell'audit del 20 novembre 2026 delle versioni precedenti sono conservati; il materiale non più presente nelle slide è in sezioni di approfondimento (per esempio L17 §10 Iris in MATLAB, L18 §11 notebook t-SNE con `n_iter`, L21 §10 notebook Inception, L24 §14 simulazione d'esame).

| Lezione | Pagine | Placeholder | Formule KaTeX | Note principali |
|---|---:|---:|---:|---|
| 13 | 70 | 40 | 189 | BLEU con i bigrammi fuori dalla matematica |
| 14 | 97 | 56 | 279 | bilanciamento, partizione, validazione incrociata |
| 15 | 99 | 37 | 58 | agenti, sovranità, Rule of Two, RAG; paragrafi datati conservati |
| 16 | 116 | 42 | 201 | metriche, foundation model, Iris, codifiche, laboratorio |
| 17 | 89 | 40 | 365 | contesto (350N−100, costo quadratico), Wine 97,75%/100%/CV 0,871, bias-varianza, LDA, gradiente svolto, Anscombe, `r2_score` invertito |
| 18 | 93 | 54 | 586 | SFS/SBS/RFE con ranking 8×8, PCA svolta, LDA, t-SNE e KL, cinque mappe, EmbeddingGemma 2, System One, ECE, cascata |
| 19 | 112 | 80 | 408 | Bayes-ottimo, k-NN, coseno e origine, embedding di gatti/cani e scatole, k-means, anomalie, GA |
| 20 | 76 | 46 | 639 | bin $b^d$, perceptron AND completo, XOR, backpropagation svolta; segno di Δθ5 corretto nell'edizione 2026/27 |
| 21 | 109 | 64 | 516 | Keras, due loss, CNN-A 137 660 parametri e 98,65%, AlexNet 62,4 M, transfer learning, YOLO, profilazione |
| 22 | 102 | 84 | 428 | autoencoder e anomalie, BatchNorm, Adam, greedy layer-wise, LoRA, oblio e rehearsal, augmentation, GAN, diffusione, MAD |
| 23 | 112 | 85 | 248 | compiti definiti dall'uscita, SAM 1–3, convoluzione trasposta, ResNet-50, drone, hardware, memoria e quantizzazione |
| 24 | 121 | 85 | 123 | ViT, AIoT, sicurezza e OTA, CRA/NIST, RTOS, schede, TinyML, MLaaS, V2X, Cyber Tyre, Industria 4.0/5.0 |
| **Totale** | **1.196** | **713** | **4.040** | 0 errori KaTeX, 0 placeholder fuori pagina o spaiati, tabelle con riga vuota prima |

I tre registri `SOURCE_COVERAGE_2026-27_L13-L16.md`, `SOURCE_COVERAGE_2026-27_L17-L20.md` e `SOURCE_COVERAGE_2026-27_L21-L24.md` sono stati rigenerati con una riga per pagina (titolo della slide, sezione di destinazione, placeholder). Gli identificativi dei placeholder sono stati rinumerati (`IS13-V01`…); in queste lezioni non era ancora stata incollata alcuna immagine, quindi nulla è andato perso.
