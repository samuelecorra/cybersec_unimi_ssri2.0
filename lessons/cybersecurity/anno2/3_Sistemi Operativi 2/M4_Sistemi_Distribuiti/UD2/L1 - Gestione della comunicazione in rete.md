# **M4 UD2 Lezione 1 - Gestione della comunicazione in rete**

### **1. Introduzione**

Nei **sistemi distribuiti**, la comunicazione tra nodi è un elemento fondamentale per garantire la **cooperazione e la condivisione delle risorse**.  
Il sistema operativo deve quindi gestire in modo efficiente l’**indirizzamento, l’instradamento, la trasmissione e la sincronizzazione** dei messaggi.

Gli argomenti principali di questa lezione sono:

- nomi e risoluzione dei nomi,
    
- strategie di instradamento,
    
- strategie di pacchetto,
    
- strategie di connessione,
    
- gestione dei conflitti.

---

### **2. Nomi e risoluzione dei nomi**

Ogni risorsa o processo all’interno della rete deve poter essere identificato in modo **univoco**.

#### **2.1 Identificazione**

Un’entità (host o processo) è identificata da una coppia:

$$  
\langle \text{nome host}, \text{identificatore} \rangle  
$$

Esempi:

- **Nome host numerico:** `128.148.31.100`
    
- **Nome host logico:** `dti.unimi.it`

#### **2.2 Risoluzione dei nomi**

Il processo di **risoluzione dei nomi** traduce un identificatore logico in un indirizzo fisico o numerico, in modo analogo alla fase di **compilazione** nel software.

Esistono due modalità principali:

1. Ogni host mantiene un file con i **nomi e indirizzi** di tutti gli altri.
    
2. Le informazioni sono **distribuite** tra più computer e la risoluzione avviene tramite un **protocollo dedicato**, come il **DNS (Domain Name System)**.

#### **2.3 Ottimizzazione delle prestazioni**

Per velocizzare la risoluzione:

- si utilizza un **caching** delle tabelle dei nomi,
    
- con **aggiornamenti e rinfreschi periodici**,
    
- spesso organizzato in modo **gerarchico**.

---

### **3. Strategie di instradamento**

L’**instradamento (routing)** consiste nell’identificare il percorso che un messaggio deve seguire da un nodo sorgente $( A )$ a un nodo destinazione $( B )$.

#### **3.1 Strumenti principali**

- **Percorso fisico:** insieme di collegamenti tra $A$ e $B$.
    
- **Tabelle di instradamento:** contengono informazioni su velocità, costo e aggiornamenti dei percorsi.

---

#### **3.2 Schemi di instradamento**

|Tipo|Caratteristiche principali|Vantaggi|Svantaggi|
|---|---|---|---|
|**Statico (fisso)**|Percorso predefinito e immutabile.|Semplice, non richiede ricerca.|Non si adatta a guasti o variazioni di carico.|
|**Virtuale**|Percorso definito per l’intera sessione.|Ricerca solo all’inizio della sessione.|Non si adatta durante la sessione.|
|**Dinamico**|Percorso definito per ogni messaggio.|Adattabile a guasti e carichi.|Maggior overhead, possibile disordine dei pacchetti.|

---

#### **3.3 Gateway**

- Collega una rete locale ad altre reti.
    
- Può gestire **conversioni di protocollo**.
    
- Di norma:
    
    - Host → Gateway: **instradamento statico**
        
    - Gateway → Host: **instradamento dinamico**
    
- Può essere implementato come **software** o **dispositivo hardware dedicato**.

#### **3.4 Router**

- Dispositivo dedicato all’**instradamento tra reti**.
    
- Può anch’esso essere software o hardware.
    
- Determina il percorso migliore per ciascun pacchetto in transito.

---

### **4. Strategie di pacchetto**

Per trasmettere un messaggio in rete, lo si divide in **unità più piccole di lunghezza fissa**, dette **pacchetti** (packet) o **frame** (datagram).

#### **4.1 Fasi principali**

1. **Divisione del messaggio** in pacchetti di dimensione fissa.
    
2. **Trasmissione dei pacchetti** sulla rete.
    
3. **Riassemblaggio** del messaggio alla destinazione.
    
4. **Gestione degli errori** e dei pacchetti persi.

Questo approccio consente di ottimizzare l’uso del canale di comunicazione e di gestire in modo efficiente i flussi multipli di dati.

---

### **5. Strategie di connessione**

Le **sessioni di comunicazione** possono essere stabilite secondo tre principali modalità:

#### **5.1 Commutazione di circuito (Circuit Switching)**

- Si stabilisce un **collegamento fisico dedicato** tra i due processi per tutta la durata della sessione.
    
- Nessun altro può usare tale canale durante la comunicazione.
    
- **Tempo di attivazione elevato**, ma **basso overhead di gestione**.
    
- Esempio: rete telefonica tradizionale.

#### **5.2 Commutazione di messaggi (Message Switching)**

- Il collegamento è **temporaneo**, attivo solo per l’invio del messaggio.
    
- Il percorso è stabilito **dinamicamente** a ogni invio.
    
- Ha un **overhead di gestione medio**.
    
- Esempio: posta elettronica.

#### **5.3 Commutazione di pacchetti (Packet Switching)**

- Ogni pacchetto del messaggio è inviato **indipendentemente**.
    
- Il canale è **condiviso** e usato dinamicamente da più comunicazioni.
    
- Offre **migliore utilizzo della banda**, ma richiede **ricostruzione** e **controllo di ordine** dei pacchetti.
    
- Esempio: Internet.

---

### **6. Gestione dei conflitti**

Quando più nodi accedono simultaneamente allo stesso canale di comunicazione, si può verificare un **conflitto** o **collisione**.

#### **6.1 Tipi di rete**

- **Bus multi-accesso**
    
- **Rete ad anello**

---

#### **6.2 Reti a bus multi-accesso**

**Rilevamento delle collisioni:**

- Analisi della portante a accesso multiplo (Carrier Sense).
    
- Se i dati trasmessi **differiscono da quelli rilevati**, si è verificata una collisione.

**Gestione delle collisioni:**

- I nodi ritrasmettono dopo un **tempo casuale differente** per evitare ulteriori conflitti.

**Prevenzione delle collisioni:**

- Limitare il **numero di nodi** collegati al bus.

Esempio: protocollo **CSMA/CD** (Ethernet).

---

#### **6.3 Reti ad anello**

In una **rete ad anello**, i messaggi circolano in un’unica direzione.

**Rilevamento perdita del token:**

- Si misura il **tempo massimo di attesa** del token (pacchetto speciale che abilita la trasmissione).

**Gestione della perdita del token:**

- Si attiva un processo di **elezione** per la rigenerazione di un nuovo token.

Esempio: **Token Ring** di IBM.

---

### **7. Sintesi finale**

|Aspetto|Descrizione sintetica|
|---|---|
|**Risoluzione dei nomi**|Traduzione da nome logico a indirizzo numerico (DNS).|
|**Instradamento**|Determinazione del percorso dei messaggi.|
|**Strategie di pacchetto**|Suddivisione e riassemblaggio dei messaggi in pacchetti.|
|**Connessioni**|Circuito, messaggio o pacchetto.|
|**Gestione dei conflitti**|Rilevamento e prevenzione nelle reti condivise.|

---

### **8. Conclusione**

La **gestione della comunicazione in rete** è il cuore dei sistemi distribuiti:  
garantisce che i messaggi raggiungano la destinazione corretta in modo **affidabile, efficiente e coordinato**, anche in presenza di guasti o traffico elevato.  
I meccanismi di **risoluzione dei nomi, instradamento e controllo dei conflitti** costituiscono la base per tutti i protocolli di rete moderni.