# **M2 UD1 Lezione 2 - Realizzazione del sottosistema di I/O (parte 2)**

### **1. Introduzione**

Questa seconda parte approfondisce gli aspetti **interni al kernel** relativi alla realizzazione del sottosistema di ingresso/uscita.  
Dopo aver esaminato le funzioni di gestione logica (schedulazione, buffer, cache, spooling, ecc.), ci concentriamo ora su:

- le **strutture dati del kernel** usate per rappresentare le operazioni di I/O,
    
- e sulle **tecniche per ottimizzare le prestazioni complessive** del sistema di ingresso/uscita.

---

### **2. Strutture dati del kernel**

Il kernel mantiene specifiche **strutture di controllo** che permettono di coordinare e monitorare le richieste di I/O.  
Esse costituiscono il collegamento tra i **processi utente**, i **driver dei dispositivi** e i **meccanismi hardware di trasferimento**.

#### **Principali strutture**

1. **Code di richieste I/O**
    
    - Contengono le operazioni pendenti per ciascun dispositivo.
        
    - Sono gestite secondo la politica di schedulazione scelta (FIFO, priorità, deadline...).
    
2. **Descrittori dei dispositivi**
    
    - Rappresentano l’interfaccia logica verso ciascuna periferica.
        
    - Contengono informazioni su stato, tipo, driver associato e permessi di accesso.
    
3. **Buffer e strutture di caching**
    
    - Utilizzati per immagazzinare temporaneamente dati in transito.
        
    - Migliorano l’efficienza riducendo la necessità di accessi diretti al dispositivo.
    
4. **Tabelle di controllo dei driver**
    
    - Collegano le richieste logiche del sistema operativo con le funzioni hardware effettive.
        
    - Ogni driver registra in queste tabelle le proprie funzioni operative (open, read, write, close, ecc.).

---

### **3. Realizzazione di una richiesta di I/O**

Il ciclo di vita di una richiesta di I/O attraversa vari livelli del sistema operativo, dal processo utente fino al dispositivo fisico.

#### **Fasi operative**

1. Il **processo utente** invoca un’operazione di I/O (es. lettura da file, scrittura su dispositivo).
    
2. La richiesta attraversa i **livelli di astrazione del kernel**:
    
    - livello **indipendente dal dispositivo**,
        
    - livello **dipendente dal dispositivo**,
        
    - e infine il **driver specifico**.
        
3. La richiesta viene **inserita nella coda del dispositivo** corrispondente.
    
4. Il driver **traduce la richiesta** in comandi hardware e avvia il trasferimento (via polling, interrupt o DMA).
    
5. Al completamento dell’operazione, il **dispositivo segnala un interrupt** alla CPU.
    
6. Il kernel **gestisce l’interrupt**, aggiorna le strutture dati e **notifica il processo richiedente**.

#### **Sintesi del flusso**

$$  
\text{Processo utente} \longrightarrow \text{Kernel} \longrightarrow \text{Driver} \longrightarrow \text{Dispositivo} \longrightarrow \text{Interrupt di completamento}  
$$

Ogni passaggio introduce **overhead** di sistema (context switch, copia dati, gestione interrupt), che va minimizzato per migliorare le prestazioni.

---

### **4. Prestazioni dell’I/O (1)**

La gestione dell’I/O è uno dei fattori più determinanti per le **prestazioni complessive di un sistema operativo**.  
Ogni operazione di I/O implica:

- **esecuzione dei driver**,
    
- **schedulazione dei processi in attesa**,
    
- **cambi di contesto** dovuti agli interrupt,
    
- **copia dei dati** tra aree utente, kernel e periferiche.

Questi passaggi possono generare rallentamenti significativi se non gestiti in modo ottimale.

---

### **5. Prestazioni dell’I/O (2) – Strategie di ottimizzazione**

Per migliorare le prestazioni del sottosistema I/O, il sistema operativo può adottare varie strategie di ottimizzazione.

#### **a. Ridurre i cambi di contesto**

- Minimizzare le transizioni tra modalità **utente/kernel** e i passaggi di CPU tra processi.
    
- Utilizzare **interrupt differiti** o **coalescing** per gestire più eventi con un’unica attivazione.

#### **b. Ridurre le copie di dati**

- Implementare meccanismi di **zero-copy I/O**, che evitano duplicazioni inutili tra buffer utente e kernel.
    
- Utilizzare **DMA (Direct Memory Access)** per trasferire dati direttamente tra memoria e dispositivo, bypassando la CPU.

#### **c. Ridurre la frequenza degli interrupt**

- Accorpare più eventi in un’unica notifica (“**interrupt batching**”).
    
- Usare **polling temporizzato** nei casi in cui la frequenza di eventi è molto alta, riducendo l’overhead da interrupt.

#### **d. Aumentare la concorrenza**

- Permettere l’esecuzione parallela di più richieste su dispositivi diversi o su canali multipli dello stesso dispositivo.
    
- Implementare **code multiple di I/O** e **pipeline di trasferimento** per massimizzare il throughput.

#### **e. Gestione delle periferiche a livello più basso**

- Eseguire parte delle funzioni di I/O **direttamente nei driver** o **nel microcodice hardware**, riducendo il carico del kernel.
    
- Introdurre controllori **intelligenti** in grado di gestire autonomamente il flusso dei dati.

#### **f. Bilanciamento complessivo delle prestazioni**

- L’obiettivo finale è **equilibrare il carico** tra CPU, memoria e I/O, evitando che un componente diventi il collo di bottiglia del sistema.

---

### **6. Sintesi finale**

- Il **sottosistema di I/O** è composto da strutture dati interne al kernel che gestiscono code, buffer, driver e dispositivi.
    
- Una richiesta di I/O attraversa più livelli software, generando inevitabilmente overhead.
    
- Le **prestazioni complessive** dipendono da come il sistema minimizza:
    
    - i cambi di contesto,
        
    - le copie di dati,
        
    - la frequenza degli interrupt.
    
- Le strategie di ottimizzazione mirano ad aumentare la **concorrenza**, migliorare la **distribuzione del carico** e **ridurre la latenza** complessiva delle operazioni di I/O.

In conclusione, un sistema operativo efficiente deve saper **coordinare CPU, memoria e dispositivi** come un’unica architettura integrata, bilanciando continuamente il rapporto tra **elaborazione, trasferimento e reattività**.