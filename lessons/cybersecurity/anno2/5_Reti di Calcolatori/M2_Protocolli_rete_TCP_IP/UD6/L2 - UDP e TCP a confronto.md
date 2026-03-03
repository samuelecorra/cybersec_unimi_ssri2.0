# **Lezione 2: UDP e TCP a confronto**

---

### **1. Introduzione**

UDP e TCP appartengono entrambi al **livello di trasporto** della pila TCP/IP, ma offrono **servizi radicalmente diversi**.
La scelta tra i due dipende da **funzionalità**, **prestazioni** e **requisiti applicativi**.

> In sintesi: **TCP privilegia l’affidabilità**, mentre **UDP punta alla rapidità e alla leggerezza.**

---

### **2. Prestazioni a confronto**

Il protocollo **TCP** utilizza un **meccanismo di controllo del flusso e della congestione** basato su **finestre scorrevoli (Sliding Window)**.  
Questo approccio produce un **trasferimento “a ondate” (bulk transfer)**: il mittente invia un gruppo di segmenti, poi attende gli ACK, quindi riprende a inviare.

- L’algoritmo di **Slow Start** può ridurre il **throughput** nelle prime fasi della connessione.
    
- TCP ha un **overhead più alto**: ogni segmento include un **header complesso e più esteso** (minimo 20 byte).
    
- Al contrario, **UDP** ha un **overhead minimo (8 byte)** ma invia **datagrammi indipendenti**, con efficienza variabile.
	

> In generale, TCP è più stabile ma più lento; UDP è più rapido ma meno efficiente nei datagram piccoli.

![](imgs/Pasted%20image%2020260225151735.png)

---

### **3. Affidabilità del trasferimento**

|**Proprietà**|**TCP**|**UDP**|
|---|---|---|
|Ordinamento dei pacchetti|Garantito|Non garantito|
|Ritrasmissione automatica|Sì|No|
|Controllo d’errore|Completo|Solo checksum|
|Controllo di flusso|Sì|No|

- **TCP** garantisce un trasferimento **affidabile e ordinato**: i dati arrivano nell’esatta sequenza in cui sono stati inviati.
    
- **UDP** fornisce un servizio **inaffidabile**:
    
    - i pacchetti possono **perdersi** (overflow o errori di rete),
        
    - o **arrivare fuori ordine**.  
        È l’**applicazione** a dover gestire queste eventualità.
        

> UDP sacrifica l’affidabilità per ridurre il ritardo: “meglio perdere un pacchetto che bloccare il flusso”.

---

### **4. Multicast e Broadcast**

UDP supporta nativamente la **trasmissione multipla (multicast)** e la **trasmissione a tutti (broadcast)**.  
TCP, invece, **non può farlo**, poiché la sua logica di connessione 1-a-1 è incompatibile con la comunicazione multipla.

> Inoltre, il sistema di controllo errori di TCP **non si adatta al multicast affidabile**, che richiederebbe ACK multipli da destinatari diversi.

---

### **5. Dimensione dei dati**

- I datagrammi UDP sono **limitati alla dimensione massima del pacchetto IP (MTU, Maximum Transmission Unit)**, che può raggiungere **64 KB** (65 535 byte, inclusi header).
    
- TCP può invece gestire **stream di dati teoricamente illimitati**, frammentandoli in più segmenti gestiti in sequenza.
    

> In pratica, TCP gestisce grandi flussi, mentre UDP è adatto a pacchetti brevi e frequenti.

---

### **6. Complessità delle applicazioni**

L’uso di **TCP** può rendere più complesso il **framing** (cioè la separazione dei messaggi a livello applicativo), a causa di due fattori:

1. **Algoritmo di Nagle**, che ritarda l’invio di segmenti troppo piccoli per ottimizzare l’uso del canale IP.
    
2. La **lettura a stream continuo**: i dati arrivano come flusso unico, non come pacchetti discreti.
    

> Di conseguenza, un messaggio inviato come singolo blocco può essere ricevuto frammentato o accorpato, rendendo necessario un parsing applicativo.

UDP, invece, **mantiene la struttura dei messaggi**: ogni datagramma inviato corrisponde a un datagramma ricevuto.

![](imgs/Pasted%20image%2020260225151903.png)

---

### **7. Quale protocollo per quale servizio**

|**Servizio / Applicazione**|**Protocollo**|
|---|---|
|HTTP (Web)|TCP|
|FTP (File Transfer)|TCP|
|Telnet / SSH|TCP|
|POP (Posta elettronica)|TCP|
|rwho (servizi remoti semplici)|UDP|
|MBONE (audio/video multicast)|UDP|
|RealPlayer / Streaming|UDP|
|NFS (Network File System)|UDP (nelle prime versioni)|

> In generale, TCP è usato per servizi **affidabili e sequenziali**, UDP per **servizi rapidi o in tempo reale**.

---

### **8. UDP e Middleware**

Spesso UDP viene utilizzato come base per protocolli **di livello superiore** che aggiungono affidabilità, sincronizzazione o gestione distribuita.  
Questo strato intermedio è detto **middleware**.

Esempi di funzioni implementabili nel middleware:

- **Trasmissione di messaggi** (messaging system).
    
- **Servizi RPC (Remote Procedure Call)**.
    
- **Servizi di directory e gestione oggetti distribuiti**.
    
- **Tipi di dati personalizzati o astrazioni di comunicazione avanzate**.

![](imgs/Pasted%20image%2020260225151925.png)

> Il middleware trasforma il servizio “grezzo” di UDP in una piattaforma più strutturata e controllata, adattandolo alle esigenze dell’applicazione.

---

### **9. Conclusione**

La scelta tra UDP e TCP dipende dall’equilibrio tra **affidabilità e velocità**:

- Se serve **ordine, controllo e ritrasmissione**, si usa **TCP**.
    
- Se serve **bassa latenza e semplicità**, si preferisce **UDP**.
    
- In molti casi moderni (streaming, VoIP, giochi online), UDP viene **affiancato da middleware** che ne compensa le mancanze.
    

> In breve: **TCP pensa, UDP corre.**  
> La bravura del progettista sta nel scegliere quando è meglio pensare e quando è meglio correre.

![](imgs/Pasted%20image%2020251125072516.png)

