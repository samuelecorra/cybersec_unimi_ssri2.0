
# **Lezione 1: Data Link e Modello ISO/OSI**

### **1. Introduzione al modello ISO/OSI**

Il **modello ISO/OSI (Open Systems Interconnection)** è un modello concettuale a **sette livelli**, sviluppato dall’**ISO (International Organization for Standardization)** per descrivere **come due sistemi informatici comunicano** attraverso una rete.

L’idea fondamentale è quella di **scomporre la comunicazione in livelli indipendenti**, ognuno con un proprio insieme di funzioni e protocolli.  
Ogni livello fornisce **servizi al livello superiore** e **utilizza quelli del livello inferiore**.

![](imgs/Pasted%20image%2020260213010041.png)

---

### **2. Il livello fisico**

Il **livello fisico** definisce **l’interfaccia tra il dispositivo e il mezzo di trasmissione**, cioè la parte più vicina all’hardware.

Ha il compito di **trasformare i bit in segnali elettrici, ottici o radio** e di specificare:

- la **connessione fisica** (cavi, connettori, fibre, onde radio);
    
- i **livelli del segnale**;
    
- la **velocità di trasmissione** (bit rate);
    
- la **sincronizzazione** tra trasmettitore e ricevitore.
    

La definizione di questo livello può essere descritta in quattro aspetti principali:

1. **Meccanico** → tipo di connettori e cablaggi.
    
2. **Elettrico** → livelli di tensione, modulazioni, impedenze.
    
3. **Funzionale** → ruolo dei segnali (clock, dati, controllo).
    
4. **Procedurale** → modalità operative (inizio, trasmissione, fine del frame).
    

In sintesi, il livello fisico **stabilisce il canale di comunicazione materiale** tra due dispositivi.

---

### **3. Il livello Data Link**

Il **livello di collegamento dati (Data Link Layer)** si trova subito sopra al livello fisico e ha il compito di **rendere affidabile una connessione fisica potenzialmente inaffidabile**.

In pratica:

- riceve i bit grezzi dal livello fisico;
    
- li organizza in **frame** (unità di trasmissione con indirizzi e controllo d’errore);
    
- gestisce la **rilevazione e correzione degli errori**;
    
- controlla l’**accesso al canale condiviso**, specialmente nelle reti **broadcast** come Ethernet.
    

Le sue **tre responsabilità principali** corrispondono ai **sottolivelli** definiti dallo standard IEEE 802:

1. **LLC (Logical Link Control)** – definisce la struttura logica dei frame e i servizi di connessione verso i livelli superiori.
    
2. **MAC (Medium Access Control)** – controlla chi può trasmettere e quando, gestendo l’accesso al mezzo condiviso.
    
3. **Controllo d’errore** – rileva e corregge eventuali bit alterati durante la trasmissione.
    

In sostanza, il Data Link trasforma un canale fisico “nudo” in una **linea di comunicazione affidabile**, su cui i livelli superiori possono costruire servizi più complessi.

---

### **4. Il limite del Data Link: collegamento tra reti**

Il livello Data Link garantisce comunicazioni **solo all’interno di una singola rete fisica**.  
Se vogliamo inviare dati da un computer situato su una rete A a un altro su una rete B, il Data Link **non basta**: serve un livello capace di gestire **l’instradamento dei pacchetti tra reti diverse**.

---

### **5. Il livello di rete**

Il **livello di rete (Network Layer)** si occupa di stabilire **il percorso logico** che un pacchetto deve seguire **dalla sorgente alla destinazione**, anche attraverso reti diverse.

Funzioni principali:

- **Instradamento (routing)** → scelta del percorso migliore.
    
- **Indirizzamento logico** → assegna indirizzi (es. IP) univoci ai nodi.
    
- **Frammentazione dei pacchetti** → adattamento alla dimensione massima di trasmissione della rete sottostante.
    

Il percorso può essere:

- **statico**, cioè prestabilito dall’amministratore;
    
- **dinamico**, calcolato in tempo reale tramite protocolli di routing (es. RIP, OSPF, BGP).
    

---

### **6. Il livello di trasporto**

Il **livello di trasporto (Transport Layer)** assicura che la comunicazione tra due host remoti avvenga **in modo affidabile e ordinato**, indipendentemente dai percorsi seguiti dai pacchetti.

Responsabilità principali:

- **Controllo dell’integrità** (rileva perdite o errori);
    
- **Ricomposizione e riordinamento dei pacchetti** che arrivano in ordine diverso;
    
- **Controllo di flusso** (regola la quantità di dati inviati per non sovraccaricare il destinatario);
    
- **Controllo di congestione** (evita saturazioni nella rete).
    

Un protocollo tipico a questo livello è **TCP (Transmission Control Protocol)**, che garantisce:

- trasmissione **senza errori**,
    
- **nessuna perdita o duplicazione** di pacchetti,
    
- **ordine corretto di ricezione**,
    
- e, se necessario, un certo livello di **qualità del servizio (QoS)**.
    

---

### **7. I livelli superiori: sessione, presentazione, applicazione**

I tre livelli superiori gestiscono aspetti **logici e semantici** della comunicazione.

#### **a) Livello di sessione**

Controlla **i dialoghi** tra due applicazioni:

- stabilisce, mantiene e chiude la sessione di comunicazione;
    
- gestisce la **sincronizzazione** (es. checkpoint, rollback);
    
- permette la **ripresa del dialogo** in caso di interruzione.
    

#### **b) Livello di presentazione**

Si occupa della **forma dei dati**:

- definisce **formati standard** di rappresentazione (es. ASCII, JPEG, MPEG);
    
- gestisce **conversioni di codifica** tra sistemi diversi;
    
- supporta **compressione** e **crittografia** dei dati.
    

#### **c) Livello di applicazione**

È il livello più vicino all’utente.  
Fornisce i servizi di rete direttamente alle applicazioni, come:

- **HTTP** (navigazione web);
    
- **SMTP/POP/IMAP** (posta elettronica);
    
- **FTP** (trasferimento file);
    
- **DNS** (risoluzione nomi).
    

Le applicazioni si basano su **librerie di accesso alla rete** che sfruttano i servizi dei livelli inferiori.

---

### **8. Visione d’insieme del modello ISO/OSI**

![](imgs/Pasted%20image%2020260213010504.png)

|Livello|Nome|Funzione principale|
|:-:|:--|:--|
|7|**Applicazione**|Servizi diretti all’utente e alle app (HTTP, FTP, DNS)|
|6|**Presentazione**|Codifica, compressione, crittografia|
|5|**Sessione**|Gestione e sincronizzazione del dialogo|
|4|**Trasporto**|Affidabilità e controllo del flusso (TCP/UDP)|
|3|**Rete**|Instradamento e indirizzamento logico (IP)|
|2|**Collegamento Dati (Data Link)**|Trasmissione affidabile su singola rete (Ethernet, Wi-Fi)|
|1|**Fisico**|Trasmissione dei bit sul mezzo fisico|

---

### **9. Sintesi concettuale**

- Il **livello fisico** stabilisce il contatto materiale e i segnali elettrici/ottici.
    
- Il **livello Data Link** costruisce connessioni affidabili e gestisce l’accesso al mezzo.
    
- Il **livello di rete** permette l’instradamento tra reti diverse.
    
- Il **livello di trasporto** assicura comunicazioni affidabili end-to-end.
    
- I **livelli superiori** (sessione, presentazione, applicazione) gestiscono la logica del dialogo e l’interfaccia con l’utente.
    
- L’intera architettura ISO/OSI fornisce una **visione gerarchica e modulare** della comunicazione, su cui si basano le reti moderne come **TCP/IP** e **IEEE 802**.