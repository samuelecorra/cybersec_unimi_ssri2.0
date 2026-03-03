# **Lezione 4: Controllo di flusso TCP**

---

### **1. Introduzione**

Il **protocollo TCP (Transmission Control Protocol)** fornisce un servizio di **trasporto affidabile** e **orientato alla connessione** tra due host della rete.  
A differenza di UDP, che invia datagrammi indipendenti, TCP trasmette un **flusso continuo di byte**, gestendo la comunicazione come una **pipeline controllata**.

Il controllo di flusso serve a **sincronizzare la velocità** di trasmissione del mittente con la capacità di ricezione del destinatario, evitando **overflow dei buffer** e perdite di dati.

---

### **2. Elementi base del flusso TCP**

#### **2.1 Comunicazione punto-punto**

- Una connessione TCP coinvolge sempre **due soli estremi**: un mittente e un destinatario.
    
- La comunicazione è quindi **un flusso bidirezionale di byte** tra due socket.
    

#### **2.2 Flusso di byte affidabile**

- I dati vengono trasmessi come un **flusso continuo** e non come messaggi separati.
    
- TCP segmenta il flusso in **segmenti**, ciascuno con un’intestazione (header) contenente i campi di controllo.
    

#### **2.3 Pipeline controllata**

- La **finestra TCP** definisce **quanti byte** il mittente può inviare **senza ricevere ACK**.
    
- La dimensione della finestra viene **negoziata e aggiornata dinamicamente** durante la connessione.
    

#### **2.4 Buffer di invio e ricezione**

- Ogni host mantiene due buffer:
    
    - **Buffer di invio** → conserva i dati trasmessi in attesa di ACK.
        
    - **Buffer di ricezione** → memorizza i dati ricevuti fino alla consegna all’applicazione.

![](imgs/Pasted%20image%2020260225143154.png)

---

### **3. Caratteristiche fondamentali di TCP**

#### **3.1 Full-duplex**

- La connessione è **bidirezionale**: entrambe le parti possono inviare e ricevere dati simultaneamente sullo stesso canale logico.
    

#### **3.2 Orientato alla connessione**

- Prima di trasmettere i dati, TCP effettua un **handshake a tre vie** (3-way handshake) per stabilire la connessione.
    
- Solo dopo l’avvenuta sincronizzazione dei numeri di sequenza i dati vengono inviati.
    

#### **3.3 Controllo del flusso**

- TCP adatta la velocità di trasmissione del mittente alla capacità di ricezione del destinatario.
    
- Il destinatario comunica costantemente al mittente **la dimensione della finestra** disponibile nel proprio buffer.

---

### **4. Struttura generale di un segmento TCP**

Ogni segmento TCP contiene:

![](imgs/Pasted%20image%2020260225143506.png)

|Campo|Lunghezza (bit)|Descrizione|
|---|---|---|
|**Porta sorgente**|16|Identifica il processo mittente|
|**Porta destinazione**|16|Identifica il processo destinatario|
|**Numero di sequenza**|32|Indica il numero del **primo byte** del segmento|
|**Numero di acknowledgment**|32|Specifica il **byte successivo atteso** dal ricevente|
|**Lunghezza header (HLEN)**|4|Lunghezza dell’intestazione in parole da 32 bit|
|**Flag di controllo**|vari|SYN, ACK, FIN, RST, PSH, URG|
|**Dimensione finestra (window size)**|16|Numero massimo di byte che il ricevente è disposto ad accettare|
|**Checksum**|16|Verifica l’integrità del segmento|
|**Puntatore urgente**|16|Identifica i dati urgenti (oggi raramente usato)|
|**Opzioni e padding**|variabile|Parametri aggiuntivi (es. scaling, timestamp)|
|**Dati applicativi**|variabile|Contenuto utile trasportato dal segmento|

> Il TCP usa un **checksum Internet** simile a quello di UDP per verificare l’integrità del segmento.

![](imgs/Pasted%20image%2020260225143635.png)

---

### **5. Intestazione TCP: campi chiave**

#### **5.1 Numero di sequenza**

- Ogni byte del flusso TCP viene numerato.
    
- Il campo “**Sequence Number**” (32 bit) rappresenta il **numero del primo byte** trasmesso nel segmento.
    

#### **5.2 Numero di acknowledgment**

- Il campo “**Acknowledgment Number**” (32 bit) indica il **numero del prossimo byte atteso**.
    
- Serve per confermare in modo cumulativo la ricezione di tutti i byte precedenti.
    

#### **5.3 Dimensione della finestra (Advertised Window)**

- Campo a **16 bit**, espresso in **byte** (non in segmenti).
    
- Indica la quantità di spazio libera nel buffer di ricezione.
    
- Il mittente non può inviare più byte di quanti indicati da questo valore.
    

#### **5.4 Lunghezza dell’intestazione**

- Specificata in multipli di **4 byte**.
    
- L’intestazione standard (senza opzioni) è di **20 byte**, ma può arrivare fino a **64 byte** (se si includono opzioni).
    

---

### **6. Controllo di flusso TCP**

Il **controllo di flusso** TCP utilizza un meccanismo basato sulla **finestra scorrevole (sliding window)**.  
Questo schema consente di mantenere più segmenti “in volo” e di regolare dinamicamente la quantità di dati inviabili.

#### **Funzionamento**

- Il mittente può inviare fino a `window size` byte senza attendere ulteriori ACK.
    
- Ogni ACK ricevuto “fa scorrere” la finestra in avanti.
    
- Se il buffer del ricevente si riempie, esso comunica **una finestra pari a 0**, inducendo il mittente a sospendere temporaneamente la trasmissione.
    

---

### **7. Esempio di flusso TCP**

Un **server di eco** può essere utilizzato per visualizzare il comportamento di TCP:

1. L’host A invia il carattere `'C'` a B.
    
2. L’host B riceve il byte, invia un **ACK** e rispedisce `'C'` indietro (eco).
    
3. A riceve l’ACK e il carattere di ritorno.
    

> Se un segmento arriva **fuori ordine**, la gestione dipende dall’implementazione: alcuni stack TCP memorizzano il segmento, altri lo scartano.

---

### **8. Dimensione della finestra e numeri di sequenza**

#### **8.1 Relazione tra i campi**

Ogni segmento TCP include:

- un numero di **sequenza** a 32 bit, e
    
- una **finestra di ricezione** a 16 bit.
    

Per evitare ambiguità, deve valere la condizione:

$$  
\text{Dimensione finestra} \le \frac{1}{2} \times \text{Spazio numeri di sequenza}  
$$

Poiché lo spazio dei numeri di sequenza è $2^{32}$, la condizione è ampiamente soddisfatta.

---

#### **8.2 Dimensione effettiva della finestra**

Essendo il campo “Window” di 16 bit, la finestra massima teorica è:

$$  
2^{16} = 65,536\ \text{byte} \approx 64\ \text{KB}  
$$

Tuttavia, questa dimensione può **non bastare** a saturare il canale, soprattutto su connessioni ad alta velocità o con alta latenza.

---

### **9. Il problema del Bandwidth-Delay Product**

Per saturare la pipeline, la **finestra di trasmissione** deve contenere abbastanza dati da riempire il canale per l’intera durata del **round trip**.

$$  
\text{Lunghezza pipeline} = \text{Banda} \times \text{Ritardo (RTT)}  
$$

#### **Esempio**

Se il collegamento ha:

- banda = $100\ \text{Mbps}$
    
- round-trip = $100\ \text{ms}$
    

allora:

$$  
\text{Lunghezza pipeline} = 100 \times 10^6 \times 0.1 = 10^7\ \text{bit} = 1.19\ \text{MB}  
$$

Quindi una finestra di **64 KB non è sufficiente**: il canale non può mai essere pienamente utilizzato.

---

### **10. Wrap-around dei numeri di sequenza**

Il **numero di sequenza TCP** è a 32 bit, quindi può rappresentare fino a $2^{32} = 4\ \text{GB}$ di dati.  
Tuttavia, poiché il **tempo di vita massimo (TTL)** di un segmento è circa **120 secondi**, si può verificare un “**wrap-around**” — cioè il riutilizzo prematuro dei numeri di sequenza — se la banda è troppo elevata.

#### **Esempio**

$$  
\frac{4\ \text{GB}}{120\ \text{s}} \approx 273\ \text{Mbps}  
$$

> A velocità superiori (come **Gigabit Ethernet** o linee **STS-12 a 622 Mbps**), il wrap-around può avvenire **prima della scadenza del TTL**, causando ambiguità nella gestione dei pacchetti.

---

### **11. Conclusione**

Il **controllo di flusso TCP** è un equilibrio dinamico tra efficienza e sicurezza del flusso di byte:

- utilizza numeri di sequenza e ACK per garantire l’**ordinamento** dei dati;
    
- regola la finestra in funzione della capacità del ricevente;
    
- e adatta la velocità di trasmissione per evitare congestioni.
    

La successiva evoluzione del protocollo (finestra scalabile, controllo di congestione, slow start) nasce proprio dall’esigenza di gestire canali sempre più veloci senza compromettere l’affidabilità.

---

## **Approfondimento: Controllo di flusso TCP**

### **1. Il protocollo TCP**

Il **TCP (Transmission Control Protocol)** è un protocollo **orientato alla connessione**, che garantisce un **trasporto affidabile** dei dati tra due host.  
Questo significa che, prima di iniziare lo scambio di informazioni, TCP deve assicurarsi che:

1. Il programma applicativo **destinatario sia attivo**.
    
2. Tutti i pacchetti **inviati dal mittente raggiungano la destinazione** correttamente.
    

Per realizzare queste garanzie, TCP aggiunge ai pacchetti IP un’**intestazione supplementare** — detta **header TCP** — contenente informazioni di controllo.

---

### **2. L’intestazione TCP**

L’**header TCP** fornisce i parametri fondamentali per gestire e verificare la trasmissione.  
Contiene i seguenti campi principali:

- **Numero di porta sorgente** (16 bit)
    
- **Numero di porta di destinazione** (16 bit)
    
- **Numero di sequenza** (32 bit)
    
- **Numero di acknowledgment (ACK)** (32 bit)
    
- **Checksum** (16 bit)
    
- **Dimensione della finestra** (16 bit)
    
- **Bit di segnalazione (flag)** come SYN, ACK, FIN, URG, PSH, RST
    

Questi campi servono a:

- identificare i **processi applicativi** che comunicano;
    
- stabilire **l’ordine** dei pacchetti;
    
- confermare **la corretta ricezione**;
    
- e regolare **il flusso di trasmissione** in base alle capacità del ricevente.
    

---

### **3. Struttura dell’intestazione TCP**

$$  
\begin{array}{l}  
\text{Porta sorgente (16)} \quad \text{Porta destinazione (16)} \\  
\text{Numero di sequenza (32)} \\  
\text{Numero di acknowledgment (32)} \\  
\text{HLEN (4)} ; \text{Riservato (6)} ; \text{Flag (6)} ; \text{Window (16)} \\  
\text{Checksum (16)} \quad \text{Urgent Pointer (16)} \\ 
\text{Opzioni} \quad \text{Padding} \\  
\text{Dati}  
\end{array}  
$$

> Ogni campo ha una funzione precisa nel controllo della connessione e del flusso di dati.

---

### **4. Funzione dei principali campi**

- **Numero di sequenza** → identifica l’ordine dei byte nel flusso TCP, così il ricevente può **ricomporre i dati correttamente**.
    
- **Numero di acknowledgment (ACK)** → indica il **prossimo byte atteso**, e conferma implicitamente la ricezione di tutti i byte precedenti.
    
- **Checksum** → calcolato sul contenuto del pacchetto, verifica l’integrità dei dati.
    
    - Se la somma non coincide, il pacchetto è **rifiutato** e il mittente non riceve alcun ACK.
        
- **Finestra TCP (window size)** → comunica la capacità del buffer del ricevente.
    
    - Più grande è la finestra, più segmenti possono “volare” in rete prima dell’ACK.
        

---

### **5. Meccanismo di ritrasmissione**

Se il mittente **non riceve un ACK** entro un intervallo di tempo prestabilito (timeout), **ritrasmette** il pacchetto.  
Questo assicura l’affidabilità del collegamento anche in presenza di perdite o errori di rete.

---

### **6. La sincronia trilaterale (Three-Way Handshake)**

Prima che due host possano scambiarsi dati, TCP deve **stabilire una connessione affidabile**.  
Questo avviene attraverso un processo in **tre fasi** chiamato _three-way handshake_.

![](imgs/Pasted%20image%2020260225143908.png)

#### **6.1 Descrizione concettuale**

1. **SYN** → Il client chiede al server di iniziare la connessione.  
    “Iniziamo una connessione, fammi sapere se sei disponibile e hai ricevuto questa richiesta.”
    
2. **SYN + ACK** → Il server accetta la richiesta.  
    “Sì, ho ricevuto la tua richiesta e sono pronto a stabilire il collegamento.”
    
3. **ACK** → Il client conferma la risposta.  
    “Perfetto, ho ricevuto la tua conferma; ecco i primi dati per te.”
    

Dopo queste tre fasi, la connessione TCP è **attiva e sincronizzata** su entrambi i lati.

---

#### **6.2 Esempio numerico**

|Passo|Host|Numero di sequenza (SEQ)|Numero di ACK|Flag impostati|Descrizione|
|---|---|---|---|---|---|
|1|Client → Server|SEQ = 3245|ACK = 0|SYN=1, ACK=0, FIN=0|Richiesta di connessione|
|2|Server → Client|SEQ = 7654|ACK = 3246|SYN=1, ACK=1, FIN=0|Conferma e sincronizzazione|
|3|Client → Server|SEQ = 3246|ACK = 7655|SYN=0, ACK=1, FIN=0|Conferma finale, connessione stabilita|

---

#### **6.3 Note tecniche**

- I **numeri di sequenza iniziali** (ISN) non partono da 0, ma vengono scelti **casualmente** per evitare collisioni con altre connessioni attive.
    
- I **bit di flag**:
    
    - **SYN (synchronize)** → segnala richiesta di apertura connessione.
        
    - **ACK (acknowledgment)** → conferma ricezione o sincronizzazione.
        
    - **FIN (finish)** → indica chiusura della connessione.
        
- La **connessione è full-duplex**: dopo il handshake, entrambi i lati possono inviare e ricevere dati.
    

---

### **7. Relazione tra ACK e sequenza**

Quando un pacchetto con `SEQ = n` viene ricevuto, il destinatario invia un ACK con valore:

$$  
ACK = SEQ + 1  
$$

Questo significa che il ricevente **ha ricevuto correttamente tutti i byte fino a n** e si aspetta il successivo.

> Se l’ACK non viene ricevuto, il mittente ritrasmette il pacchetto corrispondente.

---

### **8. Significato pratico della finestra TCP**

Il campo “**Window**” indica al mittente **quanti byte** può ancora inviare senza ricevere nuovi ACK.  
Questo permette di **regolare dinamicamente il flusso di dati**, adattandosi:

- alla capacità del buffer del ricevente,
    
- alle condizioni di congestione della rete.
    

> Se il buffer del ricevente si riempie, esso comunica una **finestra pari a 0**, costringendo il mittente a **mettersi in attesa** fino al prossimo aggiornamento della finestra.

---

### **9. Conclusione**

Il protocollo TCP realizza un sistema di comunicazione estremamente robusto grazie a:

- **numerazione dei byte** e **ACK cumulativi** per l’ordinamento dei dati;
    
- **checksum** per il controllo d’integrità;
    
- **finestra scorrevole** per il controllo di flusso;
    
- **three-way handshake** per la sincronizzazione iniziale.
    

Questi meccanismi permettono a TCP di fornire un servizio **affidabile e adattivo**, in grado di gestire dinamicamente il ritmo della comunicazione e garantire l’integrità del flusso di byte end-to-end.


---

![](imgs/Pasted%20image%2020251125070341.png)

![](imgs/Pasted%20image%2020251125070351.png)

![](imgs/Pasted%20image%2020251125070401.png)

---

PDF della dispensa:

![](imgs/DAMNO_M2_U5_L4_D.pdf)