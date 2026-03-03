# **M2 UD1 Lezione 1 - Struttura e funzioni dei sottosistemi di ingresso/uscita**

### **1. Introduzione**

Il sottosistema di **ingresso/uscita (I/O)** costituisce la parte del sistema operativo che consente la comunicazione tra la CPU, la memoria e i dispositivi esterni.  
Il suo obiettivo è fornire un’interfaccia **standard e uniforme** ai processi, nascondendo le differenze fisiche e funzionali tra le periferiche.

In questa lezione vengono analizzate:

- le **tipologie di periferiche**,
    
- le loro **caratteristiche principali**,
    
- e la **struttura software** che ne gestisce il funzionamento.

---

### **2. Tipologie di periferiche**

Le periferiche di un sistema informatico si distinguono in base alla loro funzione e modalità di interazione con l’utente o con il sistema.  
Esempi principali:

- **Tastiera**
    
- **Mouse** e dispositivi di puntamento
    
- **Video / Monitor**
    
- **Stampanti**
    
- **Dischi magnetici e ottici**
    
- **Nastri magnetici**
    
- **Dispositivi di rete**
    
- **Orologi e temporizzatori**

Ogni categoria presenta modalità differenti di trasferimento, accesso e condivisione, che richiedono soluzioni software dedicate.

---

### **3. Caratteristiche delle periferiche**

#### **3.1 Direzione di I/O**

- **Sola lettura:** dispositivi che forniscono solo dati in ingresso  
    (es. CD-ROM).
    
- **Sola scrittura:** dispositivi che ricevono solo dati in uscita  
    (es. controller grafico).
    
- **Lettura e scrittura:** dispositivi che supportano entrambe le operazioni  
    (es. disco magnetico).

#### **3.2 Condivisione**

- **Dedicati:** utilizzabili da un solo processo alla volta  
    (es. nastro magnetico, stampante).
    
- **Condivisibili:** accessibili contemporaneamente da più processi  
    (es. disco, rete).

#### **3.3 Metodo di accesso**

- **Sequenziale:** i dati vengono letti o scritti in ordine fisso  
    (es. nastro magnetico, modem).
    
- **Diretto (casuale):** accesso a qualunque posizione del supporto  
    (es. disco, CD-ROM).

---

### **4. Ulteriori caratteristiche delle periferiche**

#### **4.1 Modo di trasferimento dei dati**

- **A carattere:** trasferimento continuo e di piccole dimensioni  
    (es. terminale, tastiera).
    
- **A blocchi:** trasferimento di grandi quantità di dati in un’unica operazione  
    (es. disco, nastro).

#### **4.2 Schedulazione del trasferimento**

- **Sincrono (bloccante):** il processo resta in attesa fino al completamento dell’operazione  
    (es. nastro magnetico).
    
- **Asincrono (non bloccante):** il processo prosegue mentre il trasferimento avviene in background  
    (es. tastiera, rete).

#### **4.3 Velocità del dispositivo**

Ogni periferica presenta parametri specifici di temporizzazione:

- **Latenza** (tempo di risposta iniziale)
    
- **Tempo di ricerca** (per i dispositivi a testina mobile, come i dischi)
    
- **Tempo di trasferimento** (durata dell’operazione)
    
- **Ritardo tra operazioni consecutive**

---

### **5. Software di gestione delle periferiche**

Il software di I/O ha il compito di fornire **un’interfaccia omogenea** e di **semplificare la programmazione**, mascherando le differenze hardware.

#### **Obiettivi**

- Standardizzare e uniformare la gestione delle periferiche.
    
- Offrire un’interfaccia software comune e modulare.
    
- Separare i livelli di gestione in componenti indipendenti e cooperanti.

#### **Livelli di gestione**

1. **Gestione del canale di comunicazione**
    
2. **Driver dipendente dal dispositivo (Device Dependent Driver)**
    
3. **Driver indipendente dal dispositivo (Device Independent Driver)**

---

### **6. Gestione del canale di comunicazione**

Questa componente si occupa di **controllare il flusso dei dati** tra CPU e periferiche, garantendo la trasparenza del collegamento fisico.

#### **Modalità di connessione**

- **Mappata in memoria (Memory-Mapped I/O):** le porte dei dispositivi sono indirizzate come celle di memoria.
    
- **Attesa attiva (Polling):** la CPU interroga periodicamente la periferica per verificare lo stato.
    
- **Interruzioni (Interrupt):** la periferica notifica alla CPU la disponibilità di nuovi dati.
    
- **DMA (Direct Memory Access):** il dispositivo comunica direttamente con la memoria, senza intervento diretto della CPU.

---

### **7. Driver dipendente dal dispositivo (Device Dependent Driver)**

È il livello software **più vicino all’hardware**, specifico per ogni tipo e modello di periferica.

#### **Funzioni principali**

- Rendere trasparenti le **differenze tra dispositivi dello stesso tipo** (diversi modelli o produttori).
    
- Gestire le **caratteristiche specifiche** del dispositivo: comandi, protocolli, segnalazioni di errore.
    
- Uniformare il linguaggio di comunicazione tra periferiche simili (es. diverse stampanti o dischi).

Questo livello permette di sostituire o aggiornare un dispositivo senza modificare il resto del sistema operativo.

---

### **8. Driver indipendente dal dispositivo (Device Independent Driver)**

Si colloca sopra i driver specifici e si occupa delle **funzioni comuni** a tutte le periferiche.

#### **Compiti principali**

- Gestione degli **errori generici**.
    
- **Bufferizzazione** dei dati per ottimizzare il trasferimento.
    
- Implementazione di **caching** (mantenimento temporaneo dei dati più usati).
    
- Gestione dello **spooling** (accodamento delle richieste, es. per stampanti).
    
- Fornitura di un’**interfaccia standardizzata** verso i processi utente.

---

### **9. Sintesi finale**

- Le **periferiche** si distinguono per modalità di accesso, direzione dei dati, velocità e tipo di condivisione.
    
- Il **sottosistema di I/O** deve nascondere la complessità fisica dei dispositivi e garantire un funzionamento uniforme.
    
- La gestione software è **strutturata a livelli**:
    
    1. **Canale di comunicazione** → controllo fisico del trasferimento.
        
    2. **Driver dipendente** → gestione specifica del dispositivo.
        
    3. **Driver indipendente** → gestione comune e ottimizzazione.
    
- Questa stratificazione consente di ottenere **modularità, standardizzazione e portabilità** del sistema di I/O.