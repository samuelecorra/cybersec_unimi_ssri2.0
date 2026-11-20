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
| 09 | 1 / 69 | 1 / 91 | +22 | TESTO ALLINEATO | Rappresentazioni, k-NN, retrieval, metriche e casi d’uso aggiornati; copertura in `SOURCE_COVERAGE_2026-27_L09-L12.md`. Restano riferimenti visuali e numeri di pagina legacy nel testo preesistente: mapping puntuale ancora necessario. |
| 10 | 1 / 76 | 1 / 82 | +6 | TESTO ALLINEATO | Integrati estrazione delle feature, MFCC, Simpson, dataset ed EDA; copertura in `SOURCE_COVERAGE_2026-27_L09-L12.md`. Alcuni riferimenti a figure/pagine dell’edizione precedente restano da riallineare. |
| 11 | 1 / 100 | 1 / 154 | +54 | TESTO ALLINEATO | Versione v24: aggiunti diagnostica visuale, costi/prestazioni operative e visual analytics con agenti; copertura in `SOURCE_COVERAGE_2026-27_L09-L12.md`. Restano riferimenti visuali legacy da riallineare. |
| 12 | 2 / 113 | 1 / 93 | −20 | TESTO ALLINEATO | Integrati esempio sintetico dei frutti, correzioni sul dataset Wine e simulazione aggiornata a dieci quesiti; copertura in `SOURCE_COVERAGE_2026-27_L09-L12.md`. La scheda punteggi precedente è conservata separatamente; possibili riferimenti visuali legacy richiedono mapping puntuale. |

## Estrazione della prima tranche

- PDF estratti: **13**.
- Pagine estratte: **1.003**.
- Flag euristici: 902 `TEXT_OK`, 49 `LIKELY_VISUAL`, 52 `FORMULA_AMBIGUOUS`, 0 `TABLE_AMBIGUOUS`, 0 `EXTRACTION_FAILED`.
- Le etichette sono indizi per la lettura, non richiedono automaticamente rendering. Per L02 è stata resa soltanto p. 56 perché l’estrazione non conteneva le leggi gestaltiche raffigurate; nessun OCR. Le altre lezioni mantengono la strategia text-first.

## Stato editoriale e limite residuo

Le lezioni 02–12 sono state confrontate con i PDF 2026/27 e il testo è stato riallineato. I placeholder presenti corrispondono uno a uno ai commenti `SOURCE_VISUAL`. Restano **180 riferimenti visuali preesistenti** la cui pagina aggiornata non è stata associata con sufficiente affidabilità; sono elencati in `VISUAL_REMAP_PENDING_2026-27.md`, mantenuti senza modifiche per evitare indicazioni errate. Per quei casi il PDF nuovo corretto è indicato nella tabella e la descrizione aiuta a individuare la figura, ma il numero pagina va ancora riallineato prima che il riferimento sia pronto per lo screenshot.

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
