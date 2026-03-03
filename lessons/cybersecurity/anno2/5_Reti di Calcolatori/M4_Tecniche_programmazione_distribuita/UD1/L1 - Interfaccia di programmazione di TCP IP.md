Introduciamo ora i **fondamenti pratici della comunicazione tra processi su macchine diverse**, cioè la base della **programmazione di rete**.

Dopo aver studiato i protocolli applicativi e di gestione, in questo modulo si passa al **livello software**, imparando **come scrivere programmi che dialogano realmente attraverso la rete**.

L’obiettivo è comprendere:

- **come è strutturato un servizio applicativo distribuito**, basato su modelli client/server;
    
- **come utilizzare la libreria Socket** per creare connessioni TCP o UDP tra processi;
    
- e come usare **RPC (Remote Procedure Call)** per realizzare comunicazioni più astratte, dove un programma può “chiamare” funzioni remote come se fossero locali.
    

In sintesi, questo modulo traduce la teoria delle reti in **codice eseguibile**, mostrando **come nascono concretamente i servizi di Internet**.


---

## **Lezione 1: Interfaccia di programmazione di TCP/IP**

### **1. Introduzione**

La **programmazione di rete** si basa sulla capacità di due applicazioni — spesso su computer diversi — di **scambiarsi dati attraverso la rete**.  
Il modello di riferimento è la **pila TCP/IP**, composta da più livelli, ciascuno con un compito specifico:

- **Livello Applicazione** → dove operano i protocolli come **HTTP**, **FTP**, **Telnet**, o le applicazioni utente sviluppate con la **Socket Library**.
    
- **Livello Trasporto** → dove agiscono i protocolli **TCP** (orientato alla connessione) e **UDP** (senza connessione).
    

La **Socket Library** costituisce l’interfaccia che permette ai programmi di accedere ai servizi offerti dal livello Trasporto, rendendo possibile la comunicazione tra processi in modo standard e indipendente dal sistema operativo.

---

### **2. Le porte in TCP e UDP**

I protocolli **TCP** e **UDP** utilizzano il concetto di **porta** per stabilire **a quale processo** debbano essere consegnati i dati ricevuti su una macchina.

Puoi immaginare la porta come una **“porta logica”** che collega la rete a un processo specifico:

- Ogni **porta** è rappresentata da un **numero intero positivo a 16 bit** (da 0 a 65535).
    
- Quando un pacchetto arriva su una macchina, il numero di porta serve al livello Trasporto per **inoltrarlo al processo corretto**.
    

#### **Esempio**

Se un client invia dati al **server web**, essi arriveranno alla **porta 80**, che corrisponde al servizio **HTTP**.

---

### **3. Porte note (Well-Known Ports)**

Alcuni servizi fondamentali di Internet utilizzano **porte riservate**, chiamate **well-known ports**, comprese nell’intervallo **0–1023**.

|Servizio|Protocollo|Porta|
|---|---|---|
|**FTP**|TCP|21|
|**Telnet**|TCP|23|
|**SMTP** (posta elettronica)|TCP|25|
|**Login remoto**|TCP|513|

Tutti gli altri servizi o programmi definiti dall’utente utilizzano **porte con numero ≥ 1024**, che non richiedono privilegi amministrativi per essere aperte.

---

### **4. Cosa sono i Socket**

Un **socket** è il punto terminale di una **connessione bidirezionale** tra due programmi che comunicano attraverso la rete.  
Puoi pensarci come a un **canale di comunicazione virtuale**, simile a un **file** con cui un processo può leggere o scrivere dati.

#### **Caratteristiche principali**

- Ogni socket è **associato a una porta**, così che TCP o UDP sappiano **a quale applicazione** devono consegnare i dati.
    
- La comunicazione attraverso socket è gestita **come l’I/O su file**:
    
    - le stesse funzioni di **lettura (`read`)** e **scrittura (`write`)** possono essere applicate;
        
    - ciò rende la comunicazione **semplice e uniforme** per il programmatore.
        

Esempio in pseudocodice:

```c
socket → crea canale di comunicazione
bind → associa socket a una porta locale
listen → attende richieste (lato server)
accept → accetta la connessione
read/write → scambia dati
close → chiude la connessione
```

---

### **5. Indipendenza dal linguaggio**

Uno dei grandi vantaggi delle socket è la loro **indipendenza dal linguaggio di programmazione**.  
Significa che:

- un programma scritto in **Java** può comunicare con uno scritto in **C** o **Python**,
    
- purché entrambi utilizzino la **Socket Library** e rispettino i protocolli di rete (TCP o UDP).
    

Questo garantisce **interoperabilità totale** tra applicazioni sviluppate su piattaforme e sistemi diversi — un principio fondamentale dell’architettura Internet.

---

### **6. Sintesi finale**

|Concetto|Descrizione|
|---|---|
|**Pila TCP/IP**|Struttura logica che separa applicazione e trasporto.|
|**Porte**|Canali numerici (0–65535) che collegano la rete ai processi.|
|**Well-known ports**|Porte riservate a servizi standard (es. HTTP, FTP, SMTP).|
|**Socket**|Endpoint logico della comunicazione; permette I/O di rete come su file.|
|**Indipendenza linguistica**|Programmi in linguaggi diversi possono comunicare via socket.|

---

### **7. Conclusione**

Le **socket** rappresentano il **ponte tra software e rete**.  
Sono l’interfaccia che permette alle applicazioni di “parlare” con TCP/IP e quindi con il mondo esterno.  
Imparare a usarle significa capire **come un programma locale diventa un servizio di rete**, aprendo la strada alla realizzazione di **server, client e applicazioni distribuite reali**.