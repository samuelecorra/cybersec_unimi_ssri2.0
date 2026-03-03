# **Lezione 4: Ethernet – Frame e CSMA/CD**

### **1. Il concetto di frame**

Nelle reti Ethernet, i dati non vengono trasmessi come flussi continui di bit, ma in **unità discrete** chiamate **frame** (trame).  
Ogni frame rappresenta un **pacchetto a livello Data Link**, con struttura fissa e campi ben definiti che garantiscono identificazione, integrità e instradamento del messaggio.

Un frame Ethernet contiene tutto ciò che serve per far arrivare il messaggio al destinatario corretto, anche in una rete con molti nodi che condividono lo stesso canale.

---

### **2. Struttura del frame Ethernet**

Un frame Ethernet è composto dai seguenti campi principali:

![](imgs/Pasted%20image%2020260212145020.png)

| Campo                                | Dimensione   | Descrizione                                                                                                |
| ------------------------------------ | ------------ | ---------------------------------------------------------------------------------------------------------- |
| **Preambolo**                        | 7 byte       | Sequenza di sincronizzazione “10101010” ripetuta per consentire al ricevente di allinearsi con il segnale. |
| **Start Frame Delimiter (SFD)**      | 1 byte       | Indica l’inizio effettivo del frame (`10101011`).                                                          |
| **Indirizzo Destinatario**           | 6 byte       | Indirizzo MAC del dispositivo a cui il frame è destinato.                                                  |
| **Indirizzo Mittente**               | 6 byte       | Indirizzo MAC del dispositivo che invia il frame.                                                          |
| **Tipo / Lunghezza**                 | 2 byte       | Specifica il protocollo di livello superiore (es. IP, ARP) o la lunghezza del payload.                     |
| **Dati (Payload)**                   | 46–1500 byte | Contiene i dati effettivi del messaggio.                                                                   |
| **CRC (Cyclic Redundancy CheckSum)** | 4 byte       | Somma di controllo per verificare la correttezza dei dati ricevuti.                                        |

---

### **3. Il preambolo e la sincronizzazione**

Il **preambolo** serve per **sincronizzare il ricevente**: i 7 byte alternati “1–0” (cioè il pattern `10101010`) permettono alla scheda di rete di allineare il proprio clock con quello del mittente.  
Segue l’**SFD (Start Frame Delimiter)**, che termina con due “1” consecutivi (`10101011`), segnalando l’inizio effettivo del frame.

Questa sincronizzazione è essenziale perché il segnale Ethernet è una sequenza continua di impulsi elettrici o ottici: il ricevente deve sapere **dove inizia** la trasmissione utile.

---

### **4. Gli indirizzi MAC**

Poiché tutti i frame inviati su una rete Ethernet raggiungono **tutti i nodi collegati**, è indispensabile includere nel frame due indirizzi:

- **mittente (source)**
    
- **destinatario (destination)**
    

Ogni scheda di rete possiede un **indirizzo fisico univoco di 48 bit**, detto **MAC address** (_Media Access Control address_).  
Viene assegnato dal produttore e scritto in **esadecimale**, spesso separato da trattini o due punti, ad esempio:

```
00-06-18-D5-B1-B2
```

Le prime tre coppie di byte identificano il produttore (OUI – _Organizationally Unique Identifier_), le ultime tre l’interfaccia specifica.

Per inviare un messaggio **a tutti i dispositivi** della rete, si usa l’indirizzo di **broadcast**:

```
FF-FF-FF-FF-FF-FF
```

(tutti i bit impostati a 1).

---

### **5. Il campo tipo**

Il campo **Tipo** (o **Type/Length**) serve a dire al ricevente **che tipo di protocollo** contiene il pacchetto, cioè **a quale livello superiore** (es. rete o trasporto) deve essere consegnato.

Esempi comuni:

|Protocollo|Valore del campo tipo|
|---|---|
|IPv4|0x0800|
|ARP|0x0806|
|IPv6|0x86DD|

Questo campo funge da **chiave di demultiplexing**: il frame Ethernet sa quindi “a chi” deve essere passato nei livelli superiori dello stack.

---

### **6. Il campo dati e la lunghezza massima**

Il corpo del frame (payload) può contenere **fino a 1500 byte** di dati.  
Se i dati sono meno di 46 byte, il frame viene **riempito con padding** per garantire una lunghezza minima sufficiente al corretto rilevamento delle collisioni.

---

### **7. Il CRC: controllo d’errore**

L’ultimo campo, il **CRC (Cyclic Redundancy Check)**, è una somma di controllo calcolata con un algoritmo polinomiale.  
Serve per **rilevare errori di trasmissione** dovuti a disturbi elettrici, interferenze o collisioni non correttamente gestite.

Il ricevente ricalcola il CRC sul frame ricevuto e lo confronta con quello trasmesso:  
$$  
\text{Errore} \iff CRC_{ricevuto} \ne CRC_{calcolato}  
$$

Se i valori differiscono, il frame viene **scartato**.

Non è una somma aritmetica, bensì il resto di una divisione polinomiale. Si considera l'intero frame come un polinomio a coefficienti binari quindi il primo bit sarà il coefficiente della incognita (omessa virtualmente) con esponente più alto e viene diviso per un polinomio generatore che è uno standard su tutte le schede. Il resto viene memorizzato.

---

### **8. Il funzionamento CSMA/CD**

Ethernet adotta il protocollo **CSMA/CD (Carrier Sense Multiple Access with Collision Detection)** per gestire l’accesso al canale condiviso.

#### **a) Carrier Sense (CS)**

Ogni nodo **ascolta il canale** prima di trasmettere:

- se il canale è libero → trasmette subito;
    
- se il canale è occupato → aspetta che diventi libero.
    

#### **b) Multiple Access (MA)**

Più nodi possono condividere lo stesso mezzo.  
Tuttavia, se due iniziano a trasmettere nello stesso istante, si verifica una **collisione**.

#### **c) Collision Detection (CD)**

Durante la trasmissione, il nodo **continua a leggere il segnale sul cavo**:

- se il segnale letto differisce da quello inviato → è avvenuta una **collisione**.  
    In tal caso:
    
    1. il nodo **interrompe subito** la trasmissione;
        
    2. invia un **“segnale di disturbo”** (jam signal) per informare gli altri nodi;
        
    3. **attende un tempo casuale** (backoff) prima di ritentare.
        

Il tempo di attesa viene scelto con un **algoritmo esponenziale binario**:  
dopo ogni collisione, l’intervallo casuale massimo raddoppia, fino a un limite massimo.

---

### **9. Prestazioni e back-to-back delay**

Anche in assenza di collisioni, una connessione Ethernet non raggiunge mai la **banda nominale** (ad esempio 10 Mbps per la Ethernet storica).  
Ciò accade per due motivi:

1. ogni frame deve essere separato dal successivo da un intervallo minimo di **9,6 μs (microsecondi)**, detto **back-to-back delay**;
    
2. il frame ha una **lunghezza minima** (512 bit), per garantire che le collisioni vengano rilevate in tempo utile.
    

Questi vincoli temporali assicurano il corretto funzionamento del protocollo CSMA/CD, ma riducono la **banda effettiva** disponibile.

---

### **10. Implementazione hardware**

La gestione di tutto il protocollo Ethernet (CSMA/CD, generazione del frame, CRC, temporizzazioni, ecc.) **non è affidata alla CPU**, ma a un **coprocessore dedicato**, integrato:

- in una **scheda di rete (NIC)**, oppure
    
- direttamente nella **scheda madre**.

![](imgs/Pasted%20image%2020260212150015.png)

Questo consente di ridurre il carico della CPU e garantire tempi di reazione molto precisi, fondamentali per il rispetto dei vincoli temporali del protocollo.

---

### **11. Sintesi concettuale**

- Un **frame Ethernet** è l’unità base di comunicazione del livello Data Link.
    
- Contiene indirizzi MAC, tipo, dati e un controllo d’errore (CRC).
    
- Il protocollo **CSMA/CD** gestisce le collisioni in reti a bus o con hub.
    
- La **banda effettiva** è inferiore a quella nominale per via di intervalli e overhead.
    
- L’interfaccia di rete (**NIC**) gestisce autonomamente la logica di trasmissione.