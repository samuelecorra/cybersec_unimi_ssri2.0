
---

### **Lezione 1: User Datagram Protocol (UDP)**

### **1. Introduzione**

L’**UDP** (_User Datagram Protocol_) è uno dei protocolli di **trasporto** del gruppo **TCP/IP**.  
A differenza di TCP, **non è orientato alla connessione** e **non garantisce affidabilità**:  
i pacchetti possono andare **persi**, **arrivare duplicati** o **fuori ordine**.

> UDP si limita a trasmettere datagrammi indipendenti tra mittente e destinatario, senza preoccuparsi del loro destino.

---

### **2. Caratteristiche del servizio UDP**

UDP fornisce un servizio di **recapito non affidabile**, con tre proprietà fondamentali:

1. **Senza connessione:** non viene instaurato alcun canale logico prima della trasmissione.
    
2. **Senza buffering:** i dati vengono accettati e **inviati immediatamente**, senza memorizzazione preventiva.
    
3. **Full duplex:** il traffico può avvenire in **entrambe le direzioni contemporaneamente**.
    

> In pratica, UDP si comporta come una “posta rapida” senza ricevuta di ritorno: spedisce e non controlla se il messaggio arriva.

---

### **3. Quando la mancanza di affidabilità non è un problema**

In molte applicazioni, **una lieve perdita di pacchetti** non compromette la qualità del servizio.  
Un esempio classico è la **trasmissione audio o video in tempo reale**:

- Se un pacchetto si perde, l’utente **non se ne accorge**, perché il flusso continua comunque.
    
- Ritrasmettere quel pacchetto servirebbe a poco, poiché arriverebbe **fuori tempo**.
    

> UDP è quindi ideale per **applicazioni multimediali, DNS, streaming, gaming online o VoIP**, dove la **latenza** è più critica dell’affidabilità.

---

### **4. Formato dei datagrammi UDP**

Un **datagramma UDP** è composto da **un’intestazione (header)** e **dati (payload)**.  
L’intestazione ha **lunghezza fissa di 8 byte**, suddivisa in quattro campi da 16 bit ciascuno.

#### **Campi dell’intestazione UDP**

|Campo|Descrizione|
|---|---|
|**Porta sorgente**|Identifica il processo che invia i dati. È **opzionale**: può valere `0` se non serve risposta.|
|**Porta di destinazione**|Identifica il processo destinatario del datagramma.|
|**Lunghezza del messaggio**|Indica la **lunghezza totale** del datagramma (intestazione + dati).|
|**Checksum**|Controllo di integrità su header e dati (16 bit). È **opzionale** e può valere `0`.|

---

### **5. Sintesi finale**

UDP è un protocollo **semplice, veloce e leggero**, progettato per situazioni in cui:

- **non serve garantire** la consegna dei dati,
    
- oppure **la tempestività** è più importante della precisione.
    

> In sintesi: **TCP garantisce, UDP accelera**.  
> L’applicazione sceglie in base al tipo di servizio di cui ha bisogno.

![](imgs/Pasted%20image%2020251125072450.png)

