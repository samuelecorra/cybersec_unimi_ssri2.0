# **M7 UD1 Lezione 1 - Architettura Client-Server**

### **1. Paradigmi per la distribuzione dei dati**

La distribuzione dei dati può essere realizzata attraverso diversi **paradigmi architetturali**, che si distinguono in base alla modalità con cui server e dispositivi cooperano per gestire ed elaborare le informazioni:

- **Architetture client-server**: prevedono la **separazione tra il database server e il client**. Il server gestisce i dati e le operazioni, mentre il client invia richieste e presenta i risultati.
    
- **Database distribuiti**: un’unica applicazione utilizza **più server di database** distribuiti in rete, che cooperano per fornire i dati richiesti.
    
- **Database paralleli**: sfruttano **più dispositivi di memorizzazione e processori** che lavorano simultaneamente per **aumentare le prestazioni**.
    
- **Database replicati**: memorizzano **copie identiche dei dati su più server**, garantendo **affidabilità e disponibilità**.
    
- **Data warehouse**: server specializzati nella **gestione di grandi volumi di dati storici** a supporto delle **analisi e delle decisioni aziendali**.

---

### **2. Tipologie di architetture**

Esistono due principali categorie di sistemi in ambito distribuito:

- **OLTP (On-Line Transaction Processing)**: sistemi orientati alla **gestione di transazioni affidabili** in tempo reale. Sono progettati per supportare **centinaia o migliaia di transazioni al secondo**, garantendo coerenza e rapidità.
    
- **OLAP (On-Line Analytical Processing)**: sistemi dedicati all’**analisi dei dati**, utilizzati principalmente nei **data warehouse** per attività di supporto decisionale (business intelligence).

In molti casi, un server può integrare **funzioni OLTP e OLAP**, permettendo sia la gestione operativa sia l’analisi dei dati.

---

### **3. Proprietà dei sistemi distribuiti**

Le architetture distribuite presentano due proprietà fondamentali:

- **Portabilità**  
    Capacità di **trasportare programmi tra ambienti diversi**.  
    È determinata **al momento della compilazione** e facilitata da **linguaggi standard** come SQL-2 e SQL-3.
    
- **Interoperabilità**  
    Capacità di **far interagire sistemi eterogenei**.  
    È stabilita **al momento dell’esecuzione** e resa possibile da **protocolli standard di accesso ai dati**, come:
    
    - **ODBC** (Open Database Connectivity)
        
    - **X-Open DTP** (Distributed Transaction Processing)

---

### **4. Architettura Client-Server: principi generali**

L’architettura client-server è basata sull’interazione tra **processi software distinti**:

- Il **client** ha un ruolo **attivo**, in quanto **richiede servizi**.
    
- Il **server** ha un ruolo **reattivo**, poiché **fornisce i servizi richiesti**.

È quindi necessario definire con precisione un’**interfaccia dei servizi**, che elenca i servizi offerti dal server e il modo in cui il client può accedervi.

In genere:

- un **processo client** richiede **pochi servizi in sequenza** a uno o più server;
    
- un **processo server** gestisce **molteplici richieste simultanee** provenienti da diversi client.

---

### **5. Caratteristiche hardware dei nodi**

- Il **client** deve essere adatto all’**interazione con l’utente** e supportare strumenti di produttività (posta elettronica, editor di testo, fogli elettronici, accesso Internet, workflow management).
    
- Il **server** deve disporre di:
    
    - **grande quantità di memoria**, per gestire il buffer;
        
    - **elevata capacità di memorizzazione su disco**, per contenere l’intera base di dati.

---

### **6. Architettura Client-Server nei DBMS**

Questo modello è molto diffuso nelle basi di dati perché:

- le funzioni di client e server sono **chiaramente distinte**;
    
- vi è una **separazione logica delle attività**:
    
    - il **client** si occupa dell’interfaccia e dell’interazione con l’utente;
        
    - il **server** gestisce i dati e le operazioni di elaborazione;
    
- il linguaggio **SQL** fornisce un **paradigma di programmazione ideale** per definire l’interfaccia tra client e server.

Le **interrogazioni SQL** sono:

- formulate dal **client** e **inviate al server**;
    
- **elaborate dal server**, che restituisce i risultati al client.

Grazie alla **standardizzazione di SQL**, è possibile sviluppare applicazioni client interoperabili con **diversi sistemi server**.

---

### **7. Struttura interna del server**

Nei sistemi client-server moderni, il server è spesso **multi-threaded**:

- funziona come **un singolo processo** che gestisce **più transazioni simultaneamente**;
    
- ogni **unità di esecuzione** associata a una transazione è chiamata **thread**.

Inoltre:

- il server mantiene **una coda di input** per le richieste e **una coda di output** per i risultati;
    
- un **processo dispatcher** può distribuire dinamicamente le richieste ai vari thread o processi server;
    
- se il numero di processi server attivi varia in base alle richieste ricevute, si parla di **server disponibili dinamicamente**.

---

### **8. Architetture Two-Tier e Three-Tier**

- **Architettura Two-Tier**:  
    Il client gestisce sia **l’interfaccia utente** sia **la logica applicativa**.  
    È detto **thick client**, poiché elabora parte significativa del carico di lavoro.
    
- **Architettura Three-Tier**:  
    Introduce un **application server** intermedio, responsabile della logica applicativa condivisa.  
    Il client si limita alla presentazione dei dati (**thin client**) e può essere realizzato tramite browser.

---

### **9. Sintesi finale**

In questa lezione sono stati presentati:

- i principali **paradigmi di distribuzione dei dati**;
    
- le **proprietà dei sistemi distribuiti** (portabilità e interoperabilità);
    
- la struttura e i vantaggi dell’**architettura client-server**, nelle sue varianti **two-tier** e **three-tier**.

L’architettura client-server rappresenta il **modello più diffuso** per la gestione delle basi di dati distribuite, grazie alla sua **flessibilità, modularità e scalabilità**.

---


![](imgs/Pasted%20image%2020251125052632.png)

