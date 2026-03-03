# **Lezione 1: Il ruolo del testing**

---

### 1. Definizione di testing del software

Il testing di un programma o di un sistema software consiste, nella sua forma più essenziale, nell’eseguire il software utilizzando un insieme di casi di test selezionati e verificare che il comportamento osservato sia conforme alle aspettative definite dai requisiti. In ambito italiano, il termine testing viene spesso tradotto con **collaudo del software**, evidenziando l’analogia con le pratiche di verifica tipiche delle discipline ingegneristiche tradizionali.

È importante comprendere che il testing non rappresenta semplicemente una fase tecnica finale, ma una pratica fondamentale per acquisire fiducia nel comportamento del sistema. Nel contesto della progettazione di software sicuro, il testing assume un ruolo ancora più critico, poiché errori non rilevati possono trasformarsi in vulnerabilità sfruttabili da attaccanti.

---

### 2. Il testing nel ciclo di vita del software

Nel ciclo di vita del software tradizionale, spesso rappresentato tramite il modello a cascata (waterfall), il testing compare come una delle fasi successive a:

1. Specifica dei requisiti
    
2. Progettazione
    
3. Implementazione
    
4. Testing
    
5. Manutenzione
    

Tuttavia, questa rappresentazione sequenziale può essere fuorviante se interpretata rigidamente. Sebbene il testing venga storicamente collocato verso la fine del processo di sviluppo, nella pratica moderna — e soprattutto nello sviluppo sicuro — esso deve essere distribuito lungo tutte le fasi del ciclo di vita.

Un aspetto particolarmente rilevante riguarda i **costi del testing**, che risultano normalmente superiori rispetto a quelli delle altre fasi di sviluppo. Le cause principali di questo fenomeno includono:

- insufficiente investimento nelle fasi iniziali (requisiti e progettazione),
    
- fretta nel rilascio del software per ragioni di mercato (time-to-market).
    

Questo porta spesso a un aumento dei difetti scoperti tardivamente, con conseguente incremento dei costi di correzione.

---

### 3. Questioni fondamentali sul testing e sulla qualità del software

La riflessione sul testing introduce alcune domande fondamentali dell’ingegneria del software:

- È possibile sviluppare software completamente privo di difetti?
    
- Fino a che punto possiamo fidarci del testing o di altre tecniche di verifica?
    
- Tutti i software devono essere corretti al 100%?
    

La risposta a queste domande dipende fortemente dal contesto applicativo. Nei **sistemi critici** (ad esempio sistemi aeronautici, medicali o nucleari) il livello di affidabilità richiesto è estremamente elevato. Nei software per la produttività individuale, invece, la tolleranza agli errori può essere maggiore.

Una considerazione ironica ma significativa sottolinea una peculiarità dell’industria del software: è l’unico settore in cui la vendita di prodotti difettosi è legalmente accettata, e dove spesso si paga anche per le correzioni successive.

---

### 4. Il testing nell’ingegneria tradizionale e nell’ingegneria del software

Il testing è una pratica comune in tutti i rami dell’ingegneria, ma esiste una differenza fondamentale tra sistemi fisici e sistemi software.

Negli oggetti fisici il comportamento è generalmente **continuo**: testare una struttura in un punto specifico consente di inferire con buona probabilità il comportamento nelle zone circostanti. Ad esempio, se un ponte resiste a un carico in un punto, è ragionevole assumere che si comporti correttamente anche nelle condizioni vicine.

Nel software, invece, il comportamento è fortemente **discontinuo**. Piccole variazioni nei dati di ingresso possono produrre risultati completamente diversi. Questo rende estremamente critica la selezione dei punti di test.

Un semplice esempio chiarisce il problema:

$$  
a := \frac{x}{x + 20}  
$$

La scelta del valore di $x$ è determinante per verificare il comportamento corretto del programma, poiché esistono valori problematici (ad esempio $x = -20$) che generano errori come divisioni per zero.

Un ulteriore elemento distintivo riguarda i requisiti: mentre per un ponte può essere sufficiente che non crolli, per un software occorre definire con precisione quale comportamento sia accettabile, andando oltre la semplice assenza di crash.

---

### 5. Limiti intrinseci del testing

Uno dei principi più importanti dell’ingegneria del software è che:

> Il testing è efficace per trovare errori, ma non è adeguato per dimostrare la loro assenza.

Questo significa che anche dopo migliaia di esecuzioni senza errori non si può garantire formalmente che il software sia privo di difetti. Potrebbero esistere condizioni di esecuzione non ancora esplorate in cui il comportamento risulta errato.

Di conseguenza, il testing non può sostituire una buona progettazione e una corretta implementazione. Esso rappresenta uno strumento complementare, non una soluzione universale.

---

### 6. Linee guida generali per il testing

Per essere efficace, il testing dovrebbe rispettare alcune linee guida fondamentali.

Innanzitutto, dovrebbe essere **automatizzato**, attraverso strumenti che supportino:

- la generazione dei casi di test,
    
- l’esecuzione automatica,
    
- la raccolta dei risultati,
    
- l’analisi degli output.
    

Inoltre, il testing dovrebbe riguardare **tutte le fasi dello sviluppo**, non solo il codice finale, ma anche requisiti, modelli e prototipi.

Un altro principio importante è l’estensione del testing a **tutti i componenti del sistema**, evitando zone non verificate che potrebbero introdurre vulnerabilità.

Infine, il testing deve essere **pianificato** mediante un test plan e dovrebbe seguire standard e metodologie consolidate quando possibile.

---

### 7. Economia del testing e curva di rimozione degli errori

Durante il processo di testing si osserva generalmente una curva caratteristica: inizialmente vengono individuati molti difetti in tempi relativamente brevi, ma con il passare del tempo il numero di nuovi errori scoperti diminuisce.

Arriva un momento in cui, nonostante l’impegno nel testing, non emergono più nuovi difetti. Questo non significa necessariamente che il software sia corretto, ma piuttosto che:

- i difetti rimanenti sono più difficili da individuare,
    
- potrebbero essere necessarie tecniche diverse.
    

---

### 8. Distribuzione dei difetti e criticità

Una tipica ipotesi nella pratica del testing è che esista una distribuzione dei difetti caratterizzata da:

- molti errori banali e poco critici,
    
- pochi errori critici e difficili da scoprire.
    

Il testing tende a individuare i difetti con probabilità proporzionale alla loro densità: più un difetto è comune, più è probabile che venga scoperto.

Questo implica che gli errori più critici — spesso meno frequenti — possono rimanere nascosti più a lungo.

---

### 9. Analisi statica e scoperta dei difetti critici

Tecniche più avanzate e costose, come l’**analisi statica**, hanno invece una probabilità di individuazione dei difetti proporzionale alla loro criticità.

Questo significa che tali tecniche sono particolarmente utili per identificare errori rari ma pericolosi, che il testing tradizionale potrebbe non rilevare.

Tuttavia, non sempre la distribuzione dei difetti segue il modello teorico: possono esistere difetti difficili da trovare ma non particolarmente critici.

---

### 10. Decisioni economiche nel testing

Le decisioni relative al testing devono considerare fattori economici fondamentali:

1. Il costo del testing.
    
2. Il costo associato alla presenza di un difetto nel software.
    

Quest’ultimo dipende dal prodotto tra:

- il danno provocato dal difetto,
    
- la probabilità che si verifichi.
    

Di conseguenza, non è sempre conveniente investire grandi risorse per trovare difetti che non producono danni significativi. Questo spiega perché software non critici vengano spesso rilasciati con numerosi difetti residui e perché tecniche più rigorose non vengano sempre adottate.

---

### Sintesi della lezione

Il testing del software consiste nell’esecuzione del sistema con casi di prova per verificarne il comportamento e rappresenta una fase fondamentale del ciclo di vita del software. Tuttavia, esso presenta limiti intrinseci: può individuare errori ma non garantire la loro assenza. Il testing è particolarmente complesso rispetto all’ingegneria tradizionale a causa della natura discreta e discontinua del software, che rende critica la selezione dei casi di prova.

Dal punto di vista economico, il testing comporta costi elevati e richiede decisioni basate sul rapporto tra costo della verifica e rischio associato ai difetti residui. Per questo motivo, il testing deve essere automatizzato, pianificato e distribuito lungo tutto il ciclo di vita dello sviluppo, integrandosi con altre tecniche di verifica come l’analisi statica.