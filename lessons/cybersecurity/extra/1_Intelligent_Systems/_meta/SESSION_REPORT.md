# INTELLIGENT SYSTEMS — SESSION REPORT

Repository: `C:/Users/nabis/Developer/cybersec_unimi_ssri2.0`.

## Inventario

24 lezioni numerate + cartella RAG; 57 file originali, 26 PDF, **2.068 pagine**, 31 file non PDF (7 notebook, 6 MATLAB, 3 Python, 12 immagini, 1 dataset .dat, 2 Markdown introduttivi). Inventario ricorsivo completo, originali identificati con SHA-256.

## Completato

**Lezione 01 DONE**, 82/82 pagine rappresentate in `Lezione01/L01 - Introduzione ai sistemi intelligenti.md`; 46 placeholder, 10 sezioni, 5 tabelle, 2 frammenti di codice. Fonte unica e nessun supplemento separato. Corso complessivo: 1/24 lezioni, 82/2.068 pagine.

Nessuna lezione lasciata parzialmente scritta. Prossima: **Lezione02**, PDF `Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf`, 0/84 pagine, iniziare da pagina 1.

## File creati/aggiornati da questo lavoro

- `Lezione01/L01 - Introduzione ai sistemi intelligenti.md` e `L0 - Indice del corso.md`.
- `_meta/COURSE_INVENTORY.md`, `COURSE_PROGRESS.md`, `SOURCE_COVERAGE.md`, `STYLE_GUIDE_INFERRED.md`.
- `_meta/MASTER_TASK.md`, `TEXT_FIRST_OVERRIDE.md`, `PROCESSING_POLICY.md`: richieste e policy persistenti, con precedenza text-first.
- `_meta/SOURCE_MANIFEST.json`, `SESSION_BASELINE.json`, `IS01_INSPECTION.json`, `SOURCE_REFERENCES.md`, `SESSION_REPORT.md`.
- `_meta/build_inventory.py`, `record_lesson01.py`, `validate_course.py`, `verify_render.mjs`: inventario iniziale, log della lettura storica e verifiche ripetibili; nessun helper genera screenshot automaticamente.
- `src/utils/remarkSourceComments.js` e due righe in `src/components/Viewer.jsx`: nascondono soltanto commenti di tracciabilità e placeholder HTML del corso, lasciando intatti codice e altri HTML/commenti.
- `AGENTS.md` e `CLAUDE.md`: nuova sezione di avanzamento sincronizzata, Lezione01 COMPLETED e Lezione02 NEXT TASK.

## Verifiche e limiti

57/57 originali byte-identici; 467 file di Algoritmi invariati. 35 link locali validi, 17 formule KaTeX senza errori, 2 blocchi Python sintatticamente validi e 3 controlli numerici. Verifica DOM desktop/mobile superata, nessun overflow o commento esposto; indice con 27 collegamenti e PDF sorgente accessibile. Build finale: 530 moduli e 6.095 file copiati; warning non bloccanti su CSS @import e dimensione bundle.

Nessun blocco per Lezione01. Immagini lasciate intenzionalmente all’inserimento manuale. Resta da risolvere prima del completamento globale la distribuzione web di notebook/script/dataset, attualmente conservati nel repository ma esclusi dalla build fuori da Programmazione. Non sono state certificate informazioni di mercato, disponibilità di prodotti o regole amministrative correnti: sono descritte le versioni delle slide.

## Separazione dalle modifiche dell’utente

Il corso con i suoi 57 originali era già non tracciato da Git all’inizio. Le modifiche e gli allegati di Algoritmi già presenti sono preservati; una cartella `extra/2_Tecniche_ed_applicazioni_biometriche/` è comparsa durante il lavoro ed è estranea a questa ricostruzione. Nessun commit, push, spostamento o modifica delle fonti.

## PROCESSING EFFICIENCY

- Pagine elaborate nel ciclo: **82**.
- Solo testo: **0**.
- Pagine viste visivamente: **82, tutte prima dell’istruzione correttiva**.
- OCR: **0**.
- Dopo la correzione: **0 nuovi rendering/PDF riletti**, 0 OCR; soltanto completamento e validazione della Lezione01.
- Ragioni delle nuove eccezioni visuali/OCR: nessuna. Le tavole e gli ingrandimenti precedenti appartengono alla strategia sostituita, non sono un precedente da replicare.

## Prossima azione deterministica

Leggere PROCESSING_POLICY e TEXT_FIRST_OVERRIDE; verificare manifest e stato; estrarre in un’unica passata il testo nativo UTF-8 del PDF della Lezione02 mantenendo i confini delle 84 pagine. Leggere il primo blocco 1–15, aggiornare la copertura, renderizzare singole pagine solo per ambiguità semantiche irrisolvibili dal testo. **Non rifare l’inventario né la Lezione01.**
