# **Lezione 3: Tipi di testing**

---

### 1. Introduzione ai tipi di testing

Nel dominio dell’ingegneria del software esistono numerose tipologie di testing, che possono essere classificate secondo diversi criteri. Le principali dimensioni di classificazione riguardano:

- gli **aspetti del software** che si desidera verificare,
    
- il **livello** del sistema a cui si applica il testing,
    
- il **tipo di accesso** disponibile all’unità sotto test.
    

Questa varietà riflette la complessità dei sistemi software moderni e la necessità di affrontare la qualità del software da prospettive multiple e complementari.

---

### 2. Aspetti del software da testare

Il testing può essere orientato alla verifica di differenti proprietà del software, tra cui:

- **Funzionalità**, cioè la correttezza rispetto ai requisiti.
    
- **Affidabilità (reliability)**, ossia la probabilità che il sistema funzioni correttamente per un determinato periodo e condizioni operative.
    
- **Robustezza**, ovvero la capacità di gestire input non previsti o condizioni anomale senza fallire.
    
- **Performance**, che riguarda tempi di risposta, consumo di risorse e scalabilità.
    
- **Usabilità**, cioè la facilità d’uso da parte degli utenti.
    

Queste proprietà sono strettamente connesse ai concetti di sicurezza del software, poiché un sistema non robusto o non affidabile può diventare vulnerabile ad attacchi o malfunzionamenti critici.

---

### 3. Accessibilità del testing: white box, black box e grey box

Una classificazione fondamentale dei metodi di testing riguarda il grado di accesso alla struttura interna del sistema.

Nel **white box testing**, detto anche structural testing o program-based testing, si assume che il codice sorgente sia disponibile. I casi di test vengono derivati dalla struttura interna del programma.

Nel **black box testing**, detto anche functional testing o specification-based testing, il codice non viene considerato. I casi di test sono derivati esclusivamente dai requisiti e dalla specifica del comportamento atteso.

Il **grey box testing** rappresenta una combinazione dei due approcci, in cui si possiede una conoscenza parziale della struttura interna.

Questa distinzione è essenziale perché influenza profondamente il modo in cui vengono selezionati i casi di test e i tipi di difetti individuabili.

---

### 4. White box testing

Il white box testing si basa sull’analisi della struttura interna del programma. I casi di test vengono derivati dal codice e l’esecuzione viene osservata per verificare il comportamento interno.

Un obiettivo tipico consiste nel misurare quanto codice è stato effettivamente eseguito, introducendo il concetto di **copertura** (coverage).

Tuttavia, questo approccio presenta un limite fondamentale: non garantisce che il programma soddisfi i requisiti funzionali. Un programma può avere copertura elevata ma comportamento non conforme alle aspettative.

Esistono numerose varianti di criteri di copertura, che verranno approfondite in lezioni successive.

---

### 5. Black box testing

Il black box testing ignora completamente la struttura interna del programma e considera soltanto la specifica dei requisiti e l’interfaccia esterna.

I casi di test vengono derivati dai requisiti e l’osservazione riguarda esclusivamente input e output.

Questo approccio misura in genere quanti input e output significativi sono stati utilizzati, cercando di individuare il maggior numero possibile di difetti, in particolare quelli più critici.

Il limite principale consiste nel fatto che non tutti gli input possibili possono essere esplorati, specialmente quando lo spazio degli input è molto ampio.

---

### 6. Program-based testing: processo operativo

Il program-based testing, cioè il white box testing, segue una sequenza di passi ben definita.

Innanzitutto si analizza la struttura del programma, individuando punti critici, decisioni e condizioni rilevanti. Successivamente si selezionano casi di test che soddisfano determinati criteri di copertura.

Gli input vengono quindi applicati al programma e si osservano gli output, verificando che non si manifestino errori e che i risultati siano quelli attesi.

Questo processo evidenzia che il testing strutturale è fortemente guidato dalla conoscenza del codice.

---

### 7. Valutazione del program-based testing

Il program-based testing presenta vantaggi importanti. Il codice sorgente costituisce infatti una fonte di informazioni ricca e direttamente disponibile, che consente di progettare test mirati.

Tuttavia esistono limitazioni significative.

Una delle principali riguarda l’incapacità di individuare **errori di omissione**. Se il programma non gestisce un caso particolare, tale caso potrebbe non emergere dall’analisi della struttura, poiché non esiste alcun ramo di codice associato.

Un’altra limitazione riguarda l’assenza di un **test oracle**, cioè un meccanismo che permetta di stabilire se l’output ottenuto sia corretto. Senza un riferimento esterno, può essere difficile determinare se il comportamento osservato sia conforme alle aspettative.

Il test oracle rappresenta quindi un concetto centrale nel testing: è il criterio con cui si decide se un test è passato o fallito.

---

### 8. Specification-based testing: processo operativo

Nel specification-based testing, cioè nel black box testing, il processo operativo è differente.

Si inizia analizzando la specifica dei requisiti del programma. Successivamente si seleziona un insieme di casi di test che soddisfano determinati criteri.

Gli input selezionati vengono applicati alla specifica per ottenere gli output attesi, che costituiscono il riferimento teorico. Gli stessi input vengono poi applicati al programma reale per ottenere gli output osservati.

Infine si confrontano gli output attesi con quelli osservati per verificare la correttezza del comportamento.

Questo approccio introduce esplicitamente il concetto di oracle attraverso la specifica.

---

### 9. Valutazione del specification-based testing

Il principale vantaggio del specification-based testing è che la specifica stessa funge da **oracolo**, permettendo di stabilire automaticamente la correttezza dei risultati.

Tuttavia esistono anche limiti rilevanti.

Le specifiche potrebbero non essere disponibili, specialmente nei sistemi legacy dove esiste solo il codice. Inoltre, per poter generare automaticamente i casi di test e gli output attesi, le specifiche devono essere sufficientemente formali.

Questo comporta uno sforzo maggiore rispetto al program-based testing, sia nella definizione delle specifiche sia nella progettazione dei test.

---

### Sintesi della lezione

In questa lezione sono stati analizzati i principali tipi di testing, distinguendo le classificazioni basate sugli aspetti del software, sui livelli del sistema e sull’accessibilità al codice. È stata approfondita in particolare la distinzione tra white box testing e black box testing, evidenziandone processi operativi, vantaggi e limiti.

Il white box testing sfrutta la conoscenza del codice per progettare test strutturali ma non garantisce la conformità ai requisiti. Il black box testing, invece, utilizza la specifica come riferimento e consente di verificare la correttezza funzionale, ma richiede specifiche formali e comporta costi maggiori.

Entrambi gli approcci sono complementari e devono essere utilizzati congiuntamente per ottenere un livello adeguato di qualità e sicurezza del software.