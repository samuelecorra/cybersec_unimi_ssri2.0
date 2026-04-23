In questa lezione approfondiremo le ragioni per cui si modellizzano i processi e vedremo alcuni esempi di grafi di modellizzazione.


---
### Perché modellizzare un processo?

![Pasted image 20250912150227.png](../../imgs/Pasted%20image%2020250912150227.png)

La **modellizzazione dei processi** consiste nell’utilizzare un **modello grafico standardizzato** per rappresentare in modo chiaro e condivisibile come un processo viene svolto. L’idea è quella di trasformare un insieme di attività astratte in un **diagramma leggibile**, che possa essere compreso da persone diverse: manager, tecnici, operatori o osservatori esterni.

Gli **obiettivi principali** della modellizzazione sono:

1. **Fornire una rappresentazione comprensibile a terzi**  
    Un osservatore esterno, che non conosce nei dettagli il funzionamento del processo, può comprenderne rapidamente la sequenza di attività e la logica operativa.
    
2. **Descrivere lo stato attuale (as-is)**  
    Permette di fotografare la situazione così com’è: quali attività vengono svolte, in che ordine, da chi e con quali strumenti. Questa descrizione è fondamentale come punto di partenza per ogni analisi.
    
3. **Descrivere lo stato desiderato (to-be)**  
    Si possono progettare processi futuri, più efficienti o innovativi, e confrontarli con quelli attuali per valutare gli interventi necessari al cambiamento.
    
4. **Creare una base per l’innovazione di processo**  
    Avere un modello chiaro e condiviso facilita l’analisi critica, l’individuazione di colli di bottiglia o inefficienze e l’introduzione di nuove pratiche. In altre parole, la modellizzazione è la premessa per il **miglioramento continuo**.
    

---

👉 In sintesi, modellizzare un processo non significa solo disegnarlo, ma **comunicare, analizzare e innovare**: è un ponte tra chi esegue le attività e chi le progetta o le deve comprendere dall’esterno.

---
### Elementi fondamentali di un processo

Per modellizzare un processo in maniera efficace bisogna prima **identificarne e descriverne i componenti essenziali**:

![Pasted image 20250912150359.png](../../imgs/Pasted%20image%2020250912150359.png)
- **Input**: le risorse, informazioni o materiali in ingresso che attivano il processo.
    
- **Attività/Interazioni**: le operazioni svolte lungo il percorso, spesso articolate in fasi intermedie.
    
- **Output**: i risultati finali prodotti dal processo e destinati ai fruitori (clienti, altri reparti, utenti finali).
    

La descrizione deve essere **precisa, non ambigua e comprensibile** anche a chi non partecipa direttamente al processo, inclusi i **futuri utilizzatori del sistema**. Questo garantisce chiarezza nella progettazione e comunicazione.

---

### Tipologie di processi da modellizzare

1. **Processi fisici**
    
    - Riguardano attività concrete che trasformano o elaborano oggetti fisici.
        
    - Esempio: il flusso di materiali in una catena di montaggio, la produzione industriale, la movimentazione di merci.
        
2. **Processi informativi**
    
    - Coinvolgono la creazione, la gestione, l’elaborazione e la distribuzione di informazioni.
        
    - Esempio: la gestione degli ordini di acquisto in un’azienda, la registrazione di dati in un database, l’elaborazione di report contabili.
        
3. **Processi aziendali (o business process)**
    
    - Rappresentano l’attività complessiva dell’organizzazione o dell’impresa, integrando sia i processi fisici sia quelli informativi.
        
    - Esempi: la produzione di un bene industriale, la pubblicazione di un libro, la gestione di una compagnia aerea, il servizio postale, l’insegnamento agli studenti.
        

---

### Commento alla figura

Il diagramma mostra un processo come un **blocco centrale** che riceve input dagli **attivatori** (a sinistra), li trasforma attraverso una serie di **attività e interazioni intermedie** (in basso) e produce un **output** destinato a uno o più utilizzatori (a destra).  
Questo schema evidenzia che ogni processo è un **flusso dinamico** che parte da risorse iniziali, attraversa azioni organizzate e genera un risultato finale tangibile o intangibile.

---

👉 In sintesi: modellizzare significa saper distinguere **cosa entra, cosa accade dentro e cosa esce** da un processo, adattandolo a processi fisici, informativi o aziendali complessi.


---
### Tipi di modellizzazione dei processi

Per rappresentare un processo in maniera chiara e analizzabile, esistono diversi approcci di modellizzazione, ciascuno con un focus specifico. I principali sono tre:

1. **Modellizzazione basata sui dati**
    
    - Si concentra sul **flusso di dati e documenti** che scorrono nel processo.
        
    - L’obiettivo è rappresentare come le informazioni vengono generate, trasformate e trasferite tra le diverse entità.
        
    - Strumento tipico: **DFD (Data Flow Diagram)**, che mostra i flussi di dati tra attori, archivi e processi.

        ![Pasted image 20250912150708.png](../../imgs/Pasted%20image%2020250912150708.png)
        
2. **Modellizzazione basata sulle attività**
    
    - Si focalizza sulla **sequenza delle attività da svolgere** e sulle loro dipendenze logiche.
        
    - Rappresenta in modo dettagliato il **flusso di controllo**: cosa avviene prima, cosa dopo, e in quali condizioni.
        
    - Esempio: il modello **WIDE**, molto diffuso per descrivere workflow aziendali.

        ![Pasted image 20250912150633.png](../../imgs/Pasted%20image%2020250912150633.png)
        
3. **Modellizzazione basata sulla comunicazione**
    
    - Qui il centro non è il dato né la sequenza, ma l’**interazione (o negoziazione)** tra i soggetti che partecipano al processo.
        
    - È particolarmente utile nei processi in cui le **decisioni** derivano da scambi comunicativi tra più attori.
        
    - Esempio: **Action Workflow**, che rappresenta le fasi di impegno, negoziazione e coordinamento tra operatori.

        ![Pasted image 20250912150618.png](../../imgs/Pasted%20image%2020250912150618.png)
        

---

### Commento alla figura

- **DFD (a sinistra)**: mostra entità, archivi e processi collegati da frecce che rappresentano flussi di dati. È utile per capire _cosa entra, cosa esce e come i dati vengono trasformati_.
    
- **WIDE (al centro)**: illustra le attività come blocchi collegati da frecce, con condizioni e ramificazioni logiche. È il modello che verrà approfondito nel corso, perché ampiamente utilizzato in azienda.
    
- **Action Workflow (a destra)**: organizza le attività come scambi tra operatori, mettendo in evidenza i passaggi di responsabilità e decisione. Mostra bene la dimensione comunicativa e collaborativa.

	
    

---

👉 In sintesi, la scelta del tipo di modello dipende dall’obiettivo:

- Se voglio capire **dove scorrono i dati** → uso DFD.
    
- Se voglio chiarire **cosa si fa e in che ordine** → uso WIDE.
    
- Se voglio descrivere **come le persone interagiscono per decidere** → uso Action Workflow.


---
### Il modello WIDE

Il modello **WIDE (Workflows on an Intelligent and Distributed database Environment)** è uno dei più diffusi sistemi di rappresentazione dei processi aziendali. Si basa su una descrizione **formale del flusso di controllo delle attività**: in altre parole, rappresenta l’ordine in cui i vari compiti (task) devono essere eseguiti e le condizioni che regolano il passaggio da uno all’altro.

Un workflow WIDE è formato principalmente da:

- **Task**: le attività da eseguire.
    
- **Connettori**: gli elementi logici che collegano i task e determinano sequenze, parallelismi o condizioni.
    

A questi si aggiungono:

- **Unità modulari e transazionali** → permettono di isolare parti del processo considerate indivisibili (dal punto di vista organizzativo o transazionale).
    
- **Eccezioni** → descrivono scenari anomali che richiedono trattamenti speciali (attività straordinarie, aggiornamento dati, deviazioni dal flusso normale).
    

---

### Task nel modello WIDE

I **task** sono le attività elementari. Sono caratterizzati da:

- **Nome** (univoco all’interno del processo).
    
- **Descrizione** in linguaggio naturale (scopo dell’attività).
    
- **Ruoli** (insieme di competenze richieste per eseguirlo). L’esecutore deve ricoprire almeno uno dei ruoli previsti.
    
- **Dati associati** (strutturati o meno) necessari per l’esecuzione.
    
- **Azioni possibili**: sospensione, ripresa, terminazione, delega.
    

Un task può essere **multitask**: cioè ripetuto più volte nello stesso caso, con istanze distinte numerate progressivamente.

Tipologie di task rappresentati nella legenda:

- **Task** (rettangolo singolo): attività elementare.
    
- **Sottoprocesso** (rettangolo doppio): gruppo di task considerato come unità autonoma.
    
- **Supertask** (rettangolo tratteggiato): task di livello superiore che raggruppa attività.
    
- **Business Transaction** (rettangolo marcato): attività transazionale critica.
    
- **Wait Task** (ellisse): attività di attesa, che sospende il flusso fino a una condizione.
    

---

### Connettori nel modello WIDE

I **connettori** modellano le interazioni tra task e la struttura complessiva del workflow.

- **Simbolo inizio/fine**: due barre parallele che marcano il punto di avvio o conclusione del processo.
    
- **Fork/Join Totale (cerchio vuoto)**:
    
    - _Fork_: il task precedente si divide in più rami che vengono attivati tutti.
        
    - _Join_: il task successivo parte solo quando tutti i rami precedenti sono completati.
        
- **Fork Condizionale (rombo)**: i rami successivi si attivano solo se le condizioni associate risultano vere.
    
- **Ciclico (cerchio pieno)**: il task successivo si attiva ripetutamente, ogni volta che il predecessore termina.
    
- **Join Parziale (cerchio con “K”)**: il task successivo parte appena un numero minimo (K) di predecessori è concluso.
    
- **Trigger (saetta)**: evento che innesca automaticamente un’attività o modifica il flusso al verificarsi di una condizione.
    

---

### Commento alla figura

![Pasted image 20250912150928.png](../../imgs/Pasted%20image%2020250912150928.png)

La slide mostra la **legenda dei simboli WIDE**, che costituiscono l’alfabeto grafico per rappresentare i workflow:

- A sinistra troviamo i **task**, ognuno con una specifica funzione (dal singolo task fino a sottoprocessi e transazioni).
    
- A destra i **connettori**, che stabiliscono la logica di collegamento: sequenziale, parallela, condizionale, ciclica o attivata da eventi.
    

Insieme, task e connettori consentono di rappresentare processi molto complessi in modo **strutturato, leggibile e non ambiguo**.

---

👉 In sintesi: WIDE permette di trasformare un processo in una vera e propria **mappa logica**, dove le attività (task) sono i nodi e i connettori sono le regole che ne determinano il percorso e il comportamento.


---
### Esempio di workflow WIDE: prenotazione viaggi

Il grafo rappresenta un flusso tipico che parte dalla **selezione del viaggio** e arriva alla **conferma e prenotazione**, includendo decisioni, condizioni e attività collegate.

---

#### **Fasi principali del processo**

![Pasted image 20250912151203.png](../../imgs/Pasted%20image%2020250912151203.png)

1. **Selezione viaggio (Supertask)**
    
    - È l’unità principale che racchiude varie attività di scelta.
        
    - All’interno troviamo tre **task** elementari:
        
        - _Scelta Albergo_
            
        - _Scelta tipo vettore_ (mezzo di trasporto)
            
        - _Calcolo costi_
            
2. **Valutazione dei costi (Fork Condizionale)**
    
    - Una volta calcolati i costi, entra in gioco un **connettore condizionale**:
        
        - Se i costi sono approvati → il flusso procede.
            
        - Se non approvati → si attiva un ciclo di revisione (con possibilità di cambiare le scelte).
            
3. **Ciclo di approvazione (Join Ciclico + Fork Condizionale)**
    
    - Il **Join Ciclico** consente di ripetere le fasi di scelta ogni volta che viene deciso un cambiamento.
        
    - Il **Fork Condizionale** stabilisce se modificare le scelte o confermare quelle già fatte.
        
4. **Conferma viaggio (Business Transaction)**
    
    - Comprende due task fondamentali:
        
        - _Prenotazione viaggio_
            
        - _Invio conferma_
            
    - Essendo un blocco critico dal punto di vista transazionale, è rappresentato con il simbolo **Business Transaction**, che assicura che queste operazioni siano viste come un’unica unità indivisibile.
        

---

### Elementi notevoli del grafo

- **Task**: attività elementari (es. scelta albergo).
    
- **Supertask**: raggruppa attività legate a una fase ampia (_Selezione viaggio_).
    
- **Business Transaction**: garantisce consistenza nelle operazioni di conferma.
    
- **Fork Condizionale**: decisioni su approvazione costi e possibilità di cambiare.
    
- **Join Ciclico**: permette di tornare indietro e ripetere le scelte in caso di modifica.
    

---

### Commento alla figura

La rappresentazione mostra chiaramente:

- Come un processo non sia lineare, ma includa **decisioni** e **cicli di revisione**.
    
- La distinzione tra **attività elementari** (task) e **blocchi logici più complessi** (supertask, business transaction).
    
- L’importanza di connettori come **fork e join**, che introducono la logica di scelta condizionale e di ripetizione ciclica.
    

Questo esempio evidenzia la potenza del modello WIDE: grazie ai suoi simboli, è possibile descrivere in maniera chiara e compatta anche processi complessi come la prenotazione di un viaggio con variabili, approvazioni e transazioni critiche.

---
### Esempio di workflow WIDE – Parte finale

Questa porzione del grafo completa il processo con attività collegate alla **cancellazione del viaggio, fatturazione e gestione dei pagamenti**.

---

#### **Elementi principali del grafo**

![Pasted image 20250912151324.png](../../imgs/Pasted%20image%2020250912151324.png)

1. **Fork Totale**
    
    - Dopo l’approvazione del viaggio, il flusso può biforcarsi attivando **simultaneamente** i task di _Cancellazione albergo_ e _Cancellazione volo_.
        
    - Questo rappresenta l’attivazione parallela di più attività, senza condizioni.
        
2. **Join Totale**
    
    - Il processo di _Cancellazione viaggio_ prosegue solo quando entrambi i task paralleli (_albergo_ e _volo_) sono stati completati.
        
    - Garantisce sincronizzazione: nessuna fase successiva parte prima della conclusione di tutte le precedenti.
        
3. **Multitask (controllo fattura)**
    
    - Qui il task di _Controllo fattura_ viene istanziato **tante volte quanti sono i revisori (numRev)**.
        
    - Il processo prosegue solo al raggiungimento del **quorum di approvazioni**, rendendo chiaro il legame tra il workflow e la logica organizzativa.
        
4. **Sottoprocesso**
    
    - Rappresenta blocchi autonomi del processo che possono essere trattati come unità a sé stanti (es. _Preparazione fattura_, _Gestione pagamento_).
        
    - Aiuta a rendere leggibile un processo complesso isolando sottoinsiemi coerenti di attività.
        
5. **Wait Task**
    
    - Task di attesa, che sospende l’avanzamento finché non si verifica una certa condizione (es. ritardo di pagamento oltre 10 giorni).
        
6. **Trigger**
    
    - Evento che innesca automaticamente un’azione specifica.
        
    - In questo caso, se il pagamento non viene effettuato entro il termine previsto, il trigger attiva il task _Invio promemoria_.
        

---

### Commento alla figura

Questa parte del grafo mostra bene come il modello WIDE consenta di gestire anche scenari complessi con:

- **Attività parallele** (fork e join).
    
- **Ripetizioni controllate** (multitask con quorum).
    
- **Eventi condizionati** (trigger).
    
- **Blocchi modulari** (sottoprocessi che isolano fasi specifiche).
    
- **Attese temporizzate** (wait task).
    

Il risultato è una rappresentazione estremamente precisa che non solo mostra _cosa succede_, ma anche _quando, in che condizioni e con quali regole_.

---

👉 Con questa slide si conclude l’esempio: abbiamo visto un processo di business reale (prenotazione viaggi) trasformato in un workflow WIDE, completo di task, decisioni, cicli, controlli e automazioni.


---

