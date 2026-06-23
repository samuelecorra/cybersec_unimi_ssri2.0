Introduciamo ora i **fondamenti pratici della comunicazione tra processi su macchine diverse**, cioè la base della **programmazione di rete**.

Dopo aver studiato i protocolli applicativi e di gestione, in questo modulo si passa al **livello software**, imparando **come scrivere programmi che dialogano realmente attraverso la rete**.

---

## **Lezione 1: Interfaccia di programmazione di TCP/IP**

### **1. Il problema: come accedere al trasporto TCP da un programma**

Riprendiamo in esame la pila di protocolli TCP/IP studiata nei moduli precedenti. C'è un livello di trasporto con TCP (orientato alla connessione) e UDP (non orientato alla connessione); sopra ci sono le applicazioni utente che implementano i protocolli applicativi del Modulo 3.

![](imgs/Pasted%20image%2020260623153655.png)

Se vogliamo scrivere un programma che manda e riceve dati con una determinata struttura, la domanda è: **come possiamo accedere al servizio di recapito TCP dall'interno del nostro programma?** Con quali strumenti di programmazione creiamo le connessioni per trasportare i dati applicativi?

Esistono tantissime **API (Application Programming Interface)** per la comunicazione in rete. La prima storica, messa a disposizione all'interno del sistema operativo **Unix BSD** e ancora oggi supportata da tutti i sistemi operativi — incluso Windows — è la **Socket Library**: una libreria di funzioni (originariamente in C) che consente all'applicazione utente di accedere al livello trasporto.

---

### **2. Le porte in TCP e UDP**

Tutti i protocolli della famiglia TCP/IP utilizzano il concetto di **porta**: l'identificativo di un particolare terminale di una connessione, usato da un server per parlare con un client o viceversa.

- In **TCP**, la porta identifica un endpoint di una connessione orientata alla connessione.
- In **UDP**, la porta non identifica una connessione, ma una **specifica entry di multiplexing applicativo** — identifica quale applicazione deve ricevere i dati.

![](imgs/Pasted%20image%2020260623153726.png)

Le porte sono rappresentate da valori **interi positivi**:

- **Well-known ports** (0–1023): riservate a protocolli e servizi standard (HTTP → 80, FTP, SMTP, ecc.). Richiedono privilegi amministrativi per essere aperte.
- **Porte alte** (≥ 1024): disponibili per servizi e programmi definiti dall'utente.

![](imgs/Pasted%20image%2020260623153749.png)

---

### **3. Cos'è un socket**

L'idea del **socket** (letteralmente "presa nel muro" in inglese, da cui si inserisce la spina/plug) è quella di essere l'**identificativo di un canale di comunicazione**. Un socket corrisponde a una **quaterna**:

$$(\text{IP sorgente},\ \text{porta sorgente},\ \text{IP destinazione},\ \text{porta destinazione})$$

Questa quaterna identifica univocamente una comunicazione tra due endpoint. Il socket è il **descrittore** di tale comunicazione.

> 💡 L'idea di avere un descrittore come quello dei **file** nasce dal voler riutilizzare le primitive già note (`read`/`write`) — usate per scrivere e leggere file su disco — anche per scrivere e leggere sulle connessioni di rete. Si mantiene così un'**uniformità tra I/O verso la memoria di massa e I/O verso la rete**.

---

### **4. Una interfaccia a basso livello**

La Socket Library è un'interfaccia **molto a basso livello**: non dà una semantica forte come la scrittura su un file di testo. In particolare, **non specifica il tipo di servizio né il protocollo applicativo** che si sta utilizzando — questo è tutto lasciato al programmatore che implementa l'applicazione.

Nelle prossime lezioni vedremo:
1. come **creare** questi descrittori (socket);
2. come **riempirli dei dati giusti** (porte, indirizzi, servizio da usare);
3. come **leggere e scrivere** da questi descrittori.

---

### **5. Perché studiamo la Socket Library**

La Socket Library fu la **prima API di programmazione di rete** storicamente definita, per il linguaggio C. Oggi è disponibile in **tutti i linguaggi di programmazione**, incluso Java, che è quello con cui lavoreremo in questo modulo.

Rappresenta il modo più **diretto, a basso livello e spesso più veloce** per utilizzare il trasporto TCP — e per questo è ancora il fondamento di ogni comunicazione di rete a livello applicativo.

---

### **6. Porte note (Well-Known Ports) — riepilogo**

| Servizio | Protocollo | Porta |
|---|---|---|
| **HTTP** | TCP | 80 |
| **FTP** | TCP | 21 |
| **Telnet** | TCP | 23 |
| **SMTP** | TCP | 25 |

Tutti gli altri servizi o programmi definiti dall'utente utilizzano **porte ≥ 1024**.

---

### **7. Conclusione**

Le **socket** rappresentano il ponte tra software e rete: l'interfaccia che permette alle applicazioni di accedere a TCP/IP e quindi al mondo esterno. Nate su Unix BSD e adottate universalmente, sono ancora oggi — nelle loro varianti C, Java e in tutti i linguaggi moderni — il fondamento di ogni servizio di rete, dai più semplici ai più complessi.
