# **Lezione 5: Valutare la copertura con Emma**

### 1. Strumenti per la valutazione della copertura del codice

Nel testing strutturale è fondamentale poter misurare in modo automatico il grado di copertura del codice raggiunto dai test. A questo scopo vengono utilizzati strumenti software specifici, detti **coverage tools**, che analizzano l’esecuzione del programma e determinano quali parti del codice sono state effettivamente eseguite.

Esistono diversi strumenti disponibili a seconda del linguaggio di programmazione utilizzato. Per il linguaggio C++ sono citati strumenti come **gcov** e **C2**, mentre per Java esistono diversi strumenti tra cui:

- Coverlipse, plugin per Eclipse;
    
- Clover, ambiente completo con plugin;
    
- Cobertura;
    
- Emma.
    

Ogni strumento presenta caratteristiche differenti in termini di funzionalità, prestazioni e capacità di analisi.

---

### 2. Introduzione al tool Emma

**Emma** è uno strumento per la valutazione della copertura del codice sviluppato interamente in Java. Il suo funzionamento si basa sulla tecnica di **instrumentation**, cioè sulla modifica del codice compilato per inserire istruzioni che registrano l’esecuzione delle parti del programma.

Questa instrumentazione può essere effettuata in due modalità principali:

- **offline**, cioè prima che le classi vengano caricate nella macchina virtuale;
    
- **on-the-fly**, cioè durante il caricamento delle classi.
    

Emma opera direttamente sui file compilati, cioè sui file `.class` o sugli archivi `.jar`, senza richiedere l’accesso al codice sorgente. Questo rappresenta un vantaggio importante in contesti in cui il sorgente non è disponibile o non può essere modificato.

---

### 3. Caratteristiche principali di Emma

Tra le caratteristiche principali del tool Emma si evidenziano:

- implementazione completamente in Java;
    
- elevata velocità di esecuzione, dovuta alla modifica diretta del bytecode;
    
- capacità di generare output in diversi formati, tra cui testo e HTML;
    
- possibilità di analizzare programmi senza accesso al codice sorgente;
    
- supporto all’instrumentazione sia offline sia dinamica.
    

Emma non fornisce direttamente la misura della copertura dei branch, ma fornisce una metrica chiamata **fractional line coverage**, che consente comunque di individuare situazioni in cui una decisione non è completamente coperta.

---

### 4. Funzionamento generale della valutazione della copertura

Il processo di valutazione della copertura mediante strumenti come Emma segue generalmente questi passaggi:

1. instrumentazione del codice compilato;
    
2. esecuzione dei test sul programma instrumentato;
    
3. raccolta dei dati di esecuzione;
    
4. generazione di report di copertura.
    

I report permettono di identificare le parti di codice non eseguite, facilitando la progettazione di nuovi casi di test per migliorare la copertura.

---

### 5. Importanza della misurazione della copertura nel testing

La misurazione automatica della copertura rappresenta uno strumento fondamentale per:

- valutare la qualità dei test;
    
- individuare parti di codice non testate;
    
- migliorare progressivamente il test set;
    
- supportare processi di sviluppo sicuro.
    

Nel contesto della sicurezza del software, la copertura del codice assume particolare importanza perché parti non testate del programma possono contenere vulnerabilità non rilevate.

---

### Sintesi della lezione

In questa lezione è stato introdotto il concetto di strumenti per la valutazione della copertura del codice e sono state presentate le caratteristiche principali del tool Emma. È stato spiegato come Emma operi mediante instrumentazione del bytecode Java, permettendo di misurare la copertura del programma senza accesso al codice sorgente e generando report utili per migliorare il testing.

La lezione evidenzia l’importanza degli strumenti automatici di misurazione della copertura come supporto essenziale alle tecniche di testing strutturale.