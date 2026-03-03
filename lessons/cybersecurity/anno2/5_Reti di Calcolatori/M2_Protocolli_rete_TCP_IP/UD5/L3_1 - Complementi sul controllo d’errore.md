# **Lezione 3: Complementi sul controllo d’errore (Parte I)**

---

### **1. Introduzione**

Lo schema di gestione d’errore **Idle RQ (Stop & Wait)** garantisce che i pacchetti:

- arrivino **non corrotti** al ricevente, e
    
- vengano ricevuti **nell’ordine corretto**.
    

Quando il ricevente (**R**) non riceve un pacchetto o lo riceve corrotto, **richiede al mittente (M)** la **ripetizione dell’invio**.

In questo modello:

- sia M che R devono disporre di **buffer minimi**,
    
- sufficienti a **memorizzare un solo pacchetto** alla volta.
    

Questo schema è molto semplice e sicuro, ma, come vedremo, presenta forti limiti di **efficienza**.

---

### **2. Limiti del protocollo Idle RQ**

Nel protocollo **Idle RQ**, il mittente invia un solo pacchetto per volta:

- mentre **attende l’ACK**, non può trasmettere altri pacchetti;
    
- quindi il canale rimane **inattivo** durante l’attesa.
    

Questo approccio risulta poco efficiente soprattutto quando:

- la **velocità di trasmissione (bit rate)** è molto alta, oppure
    
- la **distanza tra mittente e ricevente** è grande, e quindi il tempo di propagazione ($T_p$) è elevato.
    

> In entrambi i casi, la rete rimane inutilizzata per lunghi intervalli di tempo, riducendo l’efficienza globale.

---

### **3. Dal modello Idle RQ al Continuous RQ**

Per superare questo limite nasce lo schema **Continuous RQ (Continuous Request)**, che mira ad **aumentare l’efficienza di utilizzo del canale** a costo di una maggiore **complessità nei buffer**.

#### **Idea di base**

Il mittente **trasmette pacchetti in modo continuo**, senza attendere l’ACK di ciascuno prima di inviare il successivo.

Da qui il nome:

- “**continuous**” = continuo, flusso ininterrotto,
    
- “**idle**” = inattivo, fermo in attesa.
    

Perché ciò sia possibile:

- il mittente deve mantenere un **buffer di ritrasmissione (retransmission list)** con i pacchetti inviati ma non ancora confermati;
    
- il ricevente deve disporre di un **buffer di ricezione (receive list)** per memorizzare pacchetti arrivati correttamente in attesa di consegna all’applicazione.
    

	---

### **4. Schema generale senza errori di trasmissione**

![](imgs/Pasted%20image%2020260225122714.png)

#### **4.1 Il comportamento del mittente (M)**

Il mittente:

1. **Invia pacchetti in sequenza**, ciascuno contraddistinto da un numero progressivo di trasmissione $I(N)$.
    
2. **Memorizza ogni pacchetto** nella **lista di ritrasmissione** (retransmission list).
    
3. Quando riceve un **ACK[N]**, rimuove il pacchetto $N$ corrispondente dalla lista.
    
4. Attiva un **timer** per ciascun pacchetto trasmesso:
    
    - se il timer scade e non è arrivato l’ACK, il pacchetto viene **ritrasmesso**.
        
5. Mantiene una variabile $V(S)$ che indica il **numero del prossimo pacchetto da inviare**.
    

---

#### **4.2 Il comportamento del ricevente (R)**

Il ricevente:

1. Quando riceve correttamente il pacchetto $N$, invia un **ACK(N)** per confermare la ricezione.
    
2. Inserisce il pacchetto nella **lista di ricezione (receive list)**.
    
3. La variabile $V(R)$ contiene il numero del **prossimo pacchetto atteso** da trasferire al livello applicativo superiore.
    

> Il ricevente può quindi ricevere pacchetti anche **fuori ordine**, ma li rilascerà al livello superiore solo quando tutti quelli precedenti sono stati ricevuti.

---

### **5. Analisi temporale (senza errori)**

Durante il funzionamento corretto, il mittente e il ricevente scambiano pacchetti e ACK in modo regolare.

Il tempo medio che intercorre tra l’invio di un pacchetto $N$ e la ricezione del suo ACK è detto **RTT (Round Trip Time)**:

$$  
RTT = 2T_p  
$$

dove $T_p$ è il **tempo di propagazione** (solo andata).

---

### **6. Numero massimo di pacchetti in volo**

Durante un intervallo pari a $RTT$, il mittente può trasmettere **più di un pacchetto**, purché non superi la capacità del canale.

Il **numero massimo di pacchetti** che M può inviare prima di ricevere il primo ACK è:

$$  
K = \frac{T_{ix} + 2T_p}{T_{ix}} = 1 + 2\frac{T_p}{T_{ix}} = 1 + 2\alpha  
$$

dove:

- $T_{ix}$ = tempo di trasmissione del pacchetto
    
- $T_p$ = tempo di propagazione del segnale
    
- $\alpha = \dfrac{T_p}{T_{ix}}$
    

---

### **7. Interpretazione del parametro K**

- $K$ indica **quanti pacchetti possono essere contemporaneamente “in volo”** sul canale (cioè inviati ma non ancora confermati).
    
- In altre parole, rappresenta la **profondità minima** che deve avere la lista di ritrasmissione del mittente.
    
- Un buffer troppo piccolo limiterebbe la capacità del mittente di saturare il canale, rendendo inutile l’aumento di banda.
    

---

### **8. Relazione tra parametri**

Riassumendo:

$$  
T_p = \frac{L}{v}  
$$

dove:

- $L$ = lunghezza fisica del canale;
    
- $v$ = velocità di propagazione del segnale (≈ $2 \times 10^8$ m/s per rame o fibra).
    

e

$$  
T_{ix} = \frac{D}{R}  
$$

dove:

- $D$ = dimensione del pacchetto (bit);
    
- $R$ = bit rate della linea (bps).
    

Sostituendo, otteniamo:

$$  
K = 1 + 2\frac{L R}{v D}  
$$

> Maggiore è la **lunghezza del collegamento (L)** o la **velocità del canale (R)**, maggiore sarà il numero di pacchetti che il mittente può inviare prima di ricevere un ACK.

---

### **9. Implicazioni pratiche**

Perché il protocollo **Continuous RQ** funzioni correttamente, è necessario che:

- il mittente disponga di una **retransmission list** con almeno $K$ posizioni;
    
- il ricevente abbia un **buffer di ricezione** di dimensione adeguata;
    
- il sistema di ACK sia **sincrono e affidabile** per evitare duplicazioni o perdite.
    

---

### **10. Conclusione**

Il passaggio da Idle RQ a Continuous RQ rappresenta un miglioramento radicale:

- elimina i lunghi tempi morti del canale;
    
- mantiene elevata l’efficienza anche su connessioni a grande distanza;
    
- richiede però **maggiore memoria** e una **gestione più complessa** delle finestre di trasmissione.
    

La prossima parte analizzerà i due principali schemi di controllo basati su questo principio: **Go-Back-N** e **Selective Repeat**.