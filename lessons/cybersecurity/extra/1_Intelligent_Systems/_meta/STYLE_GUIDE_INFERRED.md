# Convenzioni dedotte e decisioni operative

## Politica di elaborazione attiva

**TEXT-FIRST**, come richiesto dall’utente in TEXT_FIRST_OVERRIDE.md: estrazione nativa UTF-8 con confini di pagina; rendering solo per ambiguità semantiche irrisolvibili dal testo. Vietati screenshot/rendering sistematici. I placeholder possono derivare da evidenze testuali conservative. Copertura pagina-per-pagina non implica visione della pagina: il valore ordinario è `not required`. Vedi PROCESSING_POLICY.md; questa correzione prevale sulle disposizioni precedenti del MASTER_TASK. Non rifare le 82 pagine della Lezione 01 già lette.

## Ricognizione del 11 settembre 2026

Letti `AGENTS.md` (istruzioni), `CLAUDE.md` (istruzioni), README e package.json della radice, `vite-plugin-lessons.js` e `scripts/check-latex.mjs`. Nessun AGENTS.md annidato rilevato. Letti integralmente i seguenti campioni, scelti fra materie diverse:

- Algoritmi, M07/UD1: `L3_Fibonacci_ricorsione_vs_iterativo.md` e `L0 - Intro UD1.md`: spiegazioni progressive, esempi, codice, costi e limiti, rimandi relativi.
- Architettura, M5/UD1: `L1 - Principio di Funzionamento della Memoria Cache.md`: prosa italiana, motivazione tecnica, dati storici contestualizzati, formule e sintesi.
- Sicurezza Sistemi e Reti, M3/UD1: `L1 - Principi dell’autenticazione.md`: definizioni, distinzioni, meccanismi, callout e placeholder vuoto.
- Programmazione Java, M11/L09: README delle lambda: codice con linguaggio dichiarato, spiegazione di input/risultato, avvertenze pertinenti.
- Corso corrente: `Intro_Corso.md` e `Lezione00_RAG_Jarvis_for_IS4/RAG.md`, conservati integralmente.

## Lezioni

- Italiano con accenti corretti; terminologia internazionale introdotta insieme al significato italiano. Prosa discorsiva, definizione e motivazione prima dei dettagli; esempi del docente conservati. Nessuna lunghezza fissa: decide la copertura delle fonti.
- Titolo `## **Lezione N: Titolo**`; sezioni `### **N. Titolo**`, sottosezioni `#### **N.M. Titolo**`, terzo livello `##### **N.M.X. Titolo**` secondo AGENTS.md. Non copiare le difformità storiche dei campioni.
- Grassetto per definizioni e distinzioni; corsivo per termini o specificazioni. Liste per elementi paralleli e procedure, non per sostituire la spiegazione.
- Callout esclusivamente `> 📌`, `> ⚠️`, `> 💡`, `> ✅`. I callout `[!important]` di alcuni campioni non sostituiscono le istruzioni correnti.
- Formule inline `$...$`, formule isolate `$$...$$`, sistemi con `\begin{cases}`. Definire variabili, ipotesi, unità e limiti. Controllo KaTeX con lo script esistente; ignorare lint cosmetico.
- Codice in fence con linguaggio. Distinguere sorgente del docente, correzione proposta ed esempio aggiuntivo. Non alterare gli allegati.
- Tabelle Markdown per confronti e tabelle semplici fedeli (autorizzazione esplicita del master task §13); niente diagrammi ASCII, tabelle o codice usati per imitare figure.
- Numeri/versioni e modalità d’esame datate vanno riferiti al PDF acquisito, senza farne regole correnti o aggiornare arbitrariamente la lezione. Segnalare incoerenze, barrature e semplificazioni importanti.
- Una lezione consolidata per cartella LezioneNN, includendo tutti i suoi PDF e supplementi. Nome nuovo `LNN - Titolo italiano.md`; non rinominare le fonti inglesi. I moduli/UD non si inventano perché il corso ha già una struttura piatta per lezioni.

## Immagini e tracciabilità

Conservare esattamente il placeholder vuoto del repository. Per soddisfare anche i riferimenti precisi richiesti dal master task, anteporre un commento distinto:

```html
<!-- SOURCE_VISUAL id="IS01-V01" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="6" type="esempi visivi" description="Applicazioni di riconoscimento di immagini e analisi radiografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
```

Un placeholder può rappresentare la composizione di più visual della stessa pagina; elencarli nella descrizione. I duplicati esatti rimandano al primo ID nel registro. Ogni figura semantica va spiegata nel testo adiacente. Loghi, copertine ripetute, clipart e illustrazioni senza informazione aggiuntiva sono esclusi con motivazione nella copertura. Le schermate dimostrative vanno lette anche quando prive di testo nativo.

Le immagini già presenti altrove usano `imgs/` e URL relativi con spazi percent-encoded. Questo ciclo lascia placeholder intenzionali; i rendering eseguiti prima della correzione sono temporanei fuori dal corso, non nuovi asset didattici. Per il seguito non generare immagini di pagina salvo fallback motivato.

## Navigazione e metadati

`Intro_Corso.md` è un'introduzione originale immutabile e non contiene un indice; il frontend esclude i file Intro dal tree. Un solo nuovo `L0 - Indice del corso.md` fornisce navigazione alle lezioni effettivamente scritte e ai PDF delle altre. Non creare link a Markdown inesistenti.

Link interni relativi; spazi codificati con `%20`. Dettagli pagina-per-pagina in SOURCE_COVERAGE, senza appesantire ogni paragrafo. I metadati possono usare titoli e tabelle amministrative semplici.

Il frontend scansiona ricorsivamente anche `_meta/*.md`; non è una cartella nascosta nell'interfaccia attuale. Gli originali .py/.m/.ipynb/.dat sono disponibili nel repository ma esclusi dalla copia di produzione fuori da Programmazione. Documentare questa limitazione e risolverla prima del gate globale. Nel ciclo 01 è stata necessaria soltanto una correzione mirata del renderer: `remarkSourceComments` omette gli HTML node di SOURCE_VISUAL e del placeholder vuoto, senza toccare gli stessi testi nei blocchi di codice o inline code. I commenti restano nei Markdown e le fonti restano immutate.

## Ripresa e qualità

Leggere prima PROCESSING_POLICY e TEXT_FIRST_OVERRIDE, poi MASTER_TASK, COURSE_PROGRESS, SOURCE_COVERAGE e SOURCE_MANIFEST. Verificare gli hash delle fonti e il prossimo PDF. L'inventario non equivale alla lettura. Aggiornare lo stato per ogni batch; DONE solo dopo copertura semantica di ogni pagina, integrazione, placeholder e controlli. La visione è un fallback, non un requisito di DONE. Per il ciclo finale sincronizzare la sezione della materia in AGENTS.md e CLAUDE.md senza toccare altre materie.
