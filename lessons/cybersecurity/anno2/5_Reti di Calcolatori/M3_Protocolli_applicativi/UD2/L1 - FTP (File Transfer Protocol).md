# **Lezione 1: FTP (File Transfer Protocol)**

---

### **1. Introduzione generale**

L’**FTP (File Transfer Protocol)** è uno dei **più antichi e importanti protocolli applicativi di Internet**, nato originariamente su **ARPANET** come una delle prime applicazioni **client/server** della storia.  
Il suo scopo è permettere il **trasferimento di file** tra due sistemi remoti, ma anche offrire un **accesso interattivo** ai file presenti sul server.

FTP si basa su **TCP** come protocollo di trasporto, quindi garantisce un trasferimento **affidabile** e **orientato alla connessione**.  
Per accedere, l’utente deve autenticarsi tramite **nome utente e password** (login standard), anche se sono comuni anche i login pubblici come **guest** o **anonymous**, usati per i server aperti.

---

### **2. Architettura client/server di FTP**

Per funzionare, è necessario che sulla macchina remota venga **eseguito un server FTP** in ascolto su una **porta nota** (generalmente la **porta 21** per il controllo).
Il client, invece, stabilisce la connessione e invia i comandi al server.

Il **server FTP** non è un singolo processo, ma un **insieme di due processi cooperanti**:

1. **Processo di controllo (control process)**
    
    - Gestisce la comunicazione tra il client e il server.
        
    - Scambia comandi e risposte testuali.
        
    - Trasmette anche informazioni sulle **porte** che verranno usate per i dati.
        
2. **Processo di trasferimento dati (data transfer process)**
    
    - Si occupa del **trasferimento effettivo** dei file (upload o download).
        
    - Utilizza una **connessione TCP separata** da quella di controllo.
        

Questa separazione consente al protocollo di mantenere **un canale stabile per i comandi** anche mentre il **trasferimento dei dati** è in corso.

---

### **3. Meccanismo di connessione**

Il funzionamento può essere riassunto in tre fasi:

1. Il **client FTP** avvia la connessione al **processo di controllo** del server (porta 21).  
    Esempio di comando:
    
    ```
    ftp media.dti.unimi.it
    ```
    
2. Una volta stabilito il collegamento, il client **attiva un proprio processo di trasferimento dati**, che ascolta su una **porta locale temporanea** (diversa dalla 21).
    
3. Il client comunica il **numero di questa porta** al server, tramite il **canale di controllo**, in modo che il server sappia dove instaurare la connessione dati.
    

---

### **4. Trasferimento dei file**

Quando il client richiede un trasferimento (ad esempio un download o un upload), il **server FTP**:

- apre una **nuova connessione TCP** verso la **porta di trasferimento dati** del client;
    
- utilizza una **porta nota (20)** sul lato server per la connessione dati.
    

In questo modo, il **canale di controllo (porta 21)** rimane libero per ricevere comandi, mentre il **canale dati (porta 20)** gestisce il flusso dei file.

---

### **5. Sintassi dei comandi e risposte**

I comandi FTP sono scritti in **caratteri ASCII** e derivano dal **protocollo Telnet NVT (Network Virtual Terminal)**.  
Questa scelta storica facilita l’interoperabilità tra sistemi diversi, perché i messaggi sono testuali e leggibili.

Le **risposte** del server sono codificate con **numeri a tre cifre**, che indicano lo stato dell’operazione.  
Ogni cifra ha un significato specifico:

![](imgs/Pasted%20image%2020260225161038.png)

|Codice|Significato|
|:--|:--|
|**1xx**|OK, comando ricevuto: in corso di esecuzione|
|**2xx**|OK, comando completato con successo|
|**3xx**|OK, operazione parziale, servono ulteriori dati o comandi|
|**4xx**|Errore temporaneo: riprovare più tardi|
|**5xx**|Errore permanente: azione non eseguibile|

Esempio:

```
220 Service ready for new user
```

→ indica che il server FTP è pronto a ricevere un nuovo collegamento.

---

### **6. Considerazioni finali**

FTP è stato per anni **lo standard di fatto** per il trasferimento di file tra host remoti.  
La sua architettura a **due connessioni TCP** (controllo e dati) è un modello classico ancora oggi usato in molti protocolli moderni.

Tuttavia, **FTP trasmette credenziali e dati in chiaro**, quindi non offre protezione contro intercettazioni o manomissioni.  
Per questo motivo, oggi viene spesso sostituito da varianti più sicure, come:

- **FTPS** (FTP con SSL/TLS);
    
- **SFTP** (FTP su SSH).