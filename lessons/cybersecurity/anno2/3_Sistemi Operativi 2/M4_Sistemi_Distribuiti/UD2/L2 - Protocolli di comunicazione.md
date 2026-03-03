# **M4 UD2 Lezione 2 - Protocolli di comunicazione**

### **1. Introduzione**

Nei sistemi distribuiti, la comunicazione tra nodi avviene attraverso **protocolli di rete**, ossia insiemi di **regole e procedure** che definiscono il formato, l’ordine e la gestione dei messaggi trasmessi.  
L’obiettivo è garantire che la comunicazione sia **affidabile, efficiente e indipendente** dall’hardware o dal sistema operativo utilizzato.

Questa lezione analizza:

- i **problemi** tipici della comunicazione in rete,
    
- il **modello teorico ISO/OSI**,
    
- e i **modelli reali** TCP/IP e UDP/IP, fino ai protocolli applicativi.

---

### **2. Problemi della comunicazione in rete**

La comunicazione in rete è resa complessa da vari fattori:

- **Comunicazione asincrona:** i nodi non sono sincronizzati nel tempo.
    
- **Probabilità di errore:** pacchetti persi, duplicati o corrotti.
    
- **Eterogeneità degli ambienti:** differenze hardware, software, e di rappresentazione dei dati.

---

### **3. Obiettivi dei protocolli di comunicazione**

Per affrontare questi problemi, i protocolli perseguono alcuni obiettivi fondamentali:

1. **Semplificazione della progettazione:** astrazione del livello fisico e logico.
    
2. **Ambiente omogeneo di comunicazione:** interoperabilità tra sistemi diversi.
    
3. **Virtualizzazione delle comunicazioni:** visione unificata delle risorse di rete.
    
4. **Gestione efficiente dei canali e dei flussi.**
    
5. **Gestione degli errori e dei guasti.**

---

### **4. Soluzione generale**

La soluzione adottata dai sistemi operativi è la definizione di un **sottosistema di comunicazione stratificato**, composto da:

- **Protocolli di rete** che fungono da **driver di comunicazione**,
    
- Strati (layer) dedicati a **funzioni specifiche**,
    
- Comunicazione **tra strati equivalenti** nei nodi partecipanti,
    
- **Protocolli specifici** per ciascun livello.

---

### **5. Il modello teorico ISO/OSI**

Il modello **ISO/OSI (Open Systems Interconnection)**, proposto dall’**International Standards Organization**, rappresenta una **struttura teorica a sette strati**, ognuno con compiti ben definiti.

#### **5.1 Gli strati del modello ISO/OSI**

1. **Strato fisico (Physical layer)**
    
    - Gestisce i dettagli **meccanici ed elettrici** della trasmissione dei bit.
    
2. **Strato di collegamento dati (Data Link layer)**
    
    - Gestisce l’invio e la ricezione dei pacchetti.
        
    - Si occupa di **rilevazione e correzione degli errori**.
    
3. **Strato di rete (Network layer)**
    
    - Stabilisce e gestisce le **connessioni logiche** tra nodi.
        
    - Si occupa dell’**instradamento** dei pacchetti.
        
    - Gestisce **indirizzamento in uscita** e **decodifica in entrata**.
    
4. **Strato di trasporto (Transport layer)**
    
    - Suddivide i messaggi in pacchetti e mantiene il loro **ordine corretto**.
        
    - Effettua **controllo del flusso** e **gestione degli errori a livello di messaggio**.
    
5. **Strato di sessione (Session layer)**
    
    - Gestisce l’**apertura, mantenimento e chiusura** delle sessioni di comunicazione.
        
    - Coordina i protocolli di comunicazione tra processi.
    
6. **Strato di presentazione (Presentation layer)**
    
    - Risolve le differenze di **formato e rappresentazione** tra sistemi eterogenei.
        
    - Esegue la **conversione dei dati** e gestisce le modalità di trasmissione:
        
        - _semi-duplex_ (un solo flusso alla volta),
            
        - _full-duplex_ (flussi bidirezionali simultanei).
    
7. **Strato di applicazione (Application layer)**
    
    - Fornisce l’interfaccia per le applicazioni utente:
        
        - **trasferimento file**,
            
        - **connessioni remote**,
            
        - **posta elettronica**,
            
        - **basi di dati distribuite**.

---

### **6. Pila ISO/OSI**

Il modello può essere visualizzato come una **pila di protocolli**, dove ogni strato offre servizi allo strato superiore e utilizza quelli dello strato inferiore:

```
+-----------------------+
| 7. Applicazione       |
| 6. Presentazione      |
| 5. Sessione           |
| 4. Trasporto          |
| 3. Rete               |
| 2. Collegamento dati  |
| 1. Fisico             |
+-----------------------+
```

Ogni messaggio, scendendo la pila, viene **incapsulato** con informazioni di controllo proprie di ciascun livello.

---

### **7. Modelli reali**

Nella pratica, il modello ISO/OSI è stato semplificato per creare protocolli più **efficienti e diffusi**: i modelli **TCP/IP** e **UDP/IP**.

#### **7.1 Caratteristiche generali**

- Obiettivo: maggiore **efficienza e semplicità**.
    
- Limite: minore astrazione e separazione logica tra i livelli.

---

### **8. Protocollo Internet (IP)**

- Appartiene allo **strato di rete**.
    
- Si occupa di:
    
    - **Gestione e instradamento dei pacchetti**,
        
    - **Indirizzamento logico** dei nodi.
    
- Non garantisce l’affidabilità: si limita a **trasmettere pacchetti best-effort** (senza controllo degli errori).

---

### **9. Protocolli di trasporto**

#### **9.1 UDP – User Datagram Protocol**

- **Non affidabile** e **senza connessione** (_connectionless_).
    
- Adatto a comunicazioni veloci dove la perdita di pacchetti è tollerabile (streaming, DNS, videoconferenze).

#### **9.2 TCP – Transmission Control Protocol**

- **Affidabile** e **orientato alla connessione** (_connection-oriented_).
    
- Garantisce:
    
    - consegna dei pacchetti in ordine,
        
    - controllo del flusso,
        
    - ritrasmissione in caso di errore.
    
- Utilizzato in applicazioni che richiedono **integrità e sequenzialità dei dati** (HTTP, FTP, e-mail).

---

### **10. Relazione tra ISO/OSI e TCP/IP**

Il modello TCP/IP **integra e semplifica** i sette strati ISO/OSI in quattro livelli principali:

|ISO/OSI|TCP/IP|Funzione principale|
|---|---|---|
|7. Applicazione|Applicazione|Servizi utente e protocolli applicativi|
|5–6|Presentazione / Sessione|Incorporate nel livello applicativo|
|4|Trasporto|TCP, UDP|
|3|Rete|IP|
|1–2|Accesso rete|Ethernet, Wi-Fi, driver fisici|

---

### **11. Protocolli applicativi**

Appartengono allo **strato di applicazione** e utilizzano TCP o UDP per la trasmissione:

|Protocollo|Funzione|Tipo di trasporto|
|---|---|---|
|**Telnet / SSH**|Accesso remoto ai sistemi|TCP|
|**FTP / SFTP**|Trasferimento di file|TCP|
|**HTTP / HTTPS**|Navigazione web|TCP|
|**SMTP**|Posta elettronica|TCP|
|**DNS**|Risoluzione dei nomi|UDP|

---

### **12. Sintesi finale**

|Aspetto|Descrizione sintetica|
|---|---|
|**Problemi di comunicazione**|Asincronia, errori, eterogeneità|
|**Obiettivi dei protocolli**|Efficienza, virtualizzazione, astrazione|
|**ISO/OSI**|Modello teorico a 7 strati|
|**TCP/IP – UDP/IP**|Implementazioni reali, più semplici e diffuse|
|**Protocolli applicativi**|Servizi di alto livello (FTP, HTTP, DNS, ecc.)|

---

### **13. Conclusione**

I **protocolli di comunicazione** sono la base del funzionamento delle reti distribuite:  
essi permettono di **astrarre la complessità dell’hardware** e di garantire comunicazioni **uniformi, affidabili e scalabili** tra sistemi eterogenei.  
Dal modello teorico ISO/OSI ai protocolli reali come TCP/IP e UDP, la loro evoluzione riflette il costante equilibrio tra **astrazione concettuale e efficienza pratica**.