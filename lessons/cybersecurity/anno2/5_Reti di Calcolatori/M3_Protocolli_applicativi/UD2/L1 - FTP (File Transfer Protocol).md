# **Lezione 1: FTP (File Transfer Protocol)**

---

### **1. Introduzione generale**

L'**FTP (File Transfer Protocol)** è uno dei **più antichi e importanti protocolli applicativi di Internet**, nato originariamente su **ARPANET** negli anni '70 come una delle prime applicazioni di trasferimento file da una macchina all'altra.

FTP si basa su **TCP** come protocollo di trasporto, quindi garantisce un trasferimento **affidabile** e **orientato alla connessione**. Apre un certo numero di connessioni TCP (vedremo quante e quali) e consente anche di impartire comandi oltre che di trasferire file.

L'accesso a un servizio FTP è protetto da una combinazione **username e password**: presso la macchina server sono definiti degli utenti FTP con le loro credenziali.

---

### **2. Accesso anonimo**

È possibile — e in realtà molto frequente — definire una modalità di accesso di tipo **anonymous**, in cui l'utente deve semplicemente digitare la propria email (o comunque una stringa convenzionale) come password.

Questa modalità fornisce un accesso **limitato**, che in genere consente solo di **leggere e prelevare file** da una libreria pubblica, senza possibilità di caricare o modificare contenuti.

---

### **3. Architettura client/server di FTP**

Si tratta di un sistema **client/server**: il client FTP esegue le connessioni, mentre la macchina remota deve avere un servizio FTP in ascolto sulla **well-known port** tipica di FTP.

Il **server FTP** non è un singolo processo, ma un **insieme di due processi cooperanti**:

1. **Processo di controllo (control process)**
    - Gestisce la comunicazione tra il client e il server, riceve i suoi comandi.
    - Funziona sulla **porta nota** (porta 21).
    - Scambia comandi e risposte testuali, comprese le informazioni sulle porte usate per i dati.

2. **Processo di trasferimento dati (data transfer process)**
    - Si occupa del **trasferimento effettivo** dei file (upload o download).
    - Utilizza **connessioni TCP separate** rispetto a quella di controllo.
    - È possibile continuare a dare comandi mentre sono in corso alcuni trasferimenti.

Questa separazione consente di mantenere un **canale stabile per i comandi** anche mentre il trasferimento dei dati è in corso.

---

### **4. Meccanismo di connessione**

Quando si digita un comando come il seguente al prompt del sistema operativo:

```
ftp pollo.crema.unimi.it
```

Si crea un **processo di controllo client** che cerca di connettersi sulla porta nota al processo di controllo del server.

Il client attiva anche un **processo di trasferimento dati** in grado di **ricevere connessioni** su una porta locale del client. Il numero di questa porta viene comunicato al server tramite il canale di controllo, in modo che sia il **server a potersi collegare al client** per avviare il trasferimento.

> 💡 Questo meccanismo consente, tra l'altro, di superare eventuali controlli di firewall che impediscono al client di creare connessioni verso porte esterne non note: siccome è il server a iniziare la connessione dati verso il client, il firewall del client non la blocca.

---

### **5. Trasferimento dei file**

I comandi principali per attivare trasferimenti file sono:

- **`get`** → scarica un file dal server al client (download);
- **`put`** → carica un file dal client al server (upload).

Quando arriva uno di questi comandi, il server apre una **nuova connessione TCP** verso la porta di trasferimento dati del client (comunicata in precedenza) per effettuare il trasferimento.

In questo modo il **canale di controllo** (porta 21) rimane libero per altri comandi, mentre il **canale dati** (porta 20) gestisce il flusso dei file.

---

### **6. Sintassi dei comandi e risposte**

I comandi FTP sono inviati in un formato di **caratteri alfanumerici** molto simile all'ASCII, chiamato **NVT (Network Virtual Terminal)**: un formato comune anche ad altri protocolli per il trasferimento a distanza di comandi, come Telnet.

Le **risposte** del server sono codificate con **codici a tre cifre**, che indicano il grado di successo del comando appena eseguito. Il codice a tre cifre facilita anche la comprensione automatica (parsing) di quello che sta succedendo.

| Codice | Significato                                              |
| :----- | :------------------------------------------------------- |
| **1xx** | OK, lo farò — comando accettato, inizia l'esecuzione    |
| **2xx** | OK, fatto — comando completato con successo             |
| **3xx** | OK, finora — operazione parziale, servono altri dati    |
| **4xx** | NO, temporaneamente — errore temporaneo, riprovare      |
| **5xx** | Azione richiesta — errore permanente, non eseguibile    |

---

### **7. Esempio di sessione FTP**

Di seguito un esempio di connessione FTP con un server reale (`pollo.crema.unimi.it`), che illustra i codici di risposta in azione:

![](imgs/Pasted%20image%2020260622212820.png)

```
% ftp pollo.crema.unimi.it
Connected to pollo.crema.unimi.it.
220 pollo.crema.unimi.it FTP server (Version 6.8) ready.
Name (pollo.crema.unimi.it:user_A) : anonymous
331 Guest login ok, send e-mail address as password.
Password: guest  <------ (in realtà non visualizzata)
230 Guest login ok, access restrictions apply.
ftp> get utenti/studenti/tcpbook.tar bookfile
200 PORT command okay.
150 Opening ASCII mode data connection for tcpbook.tar (9895469 bytes).
226 Transfer complete.
9895469 bytes received in 22.76 seconds (4.3e+02 Kbites/s)
ftp> close
221 Goodbye.
ftp> quit
```

Lettura dei codici:

- **220** — il server conferma che il processo di controllo lato server è attivo e pronto (famiglia 2xx: azione completata).
- **331** — dopo il login anonymous: "OK finora, hai dato `anonymous` come username; per completare la procedura invia la tua password (= email address)" (famiglia 3xx: operazione parziale).
- **230** — autenticazione completata, accesso concesso con restrizioni (famiglia 2xx).
- **200** — comando PORT accettato (il client ha comunicato la porta dati).
- **150** — apertura della connessione dati in corso (famiglia 1xx: avvio esecuzione).
- **226** — trasferimento completato (famiglia 2xx).
- **221** — connessione chiusa (famiglia 2xx).

---

### **8. Considerazioni finali**

FTP è stato per anni **lo standard di fatto** per il trasferimento di file tra host remoti. La sua architettura a **due connessioni TCP** (controllo e dati) è un modello classico ancora studiato.

Tuttavia, **FTP trasmette credenziali e dati in chiaro**, senza protezione contro intercettazioni. Per questo motivo oggi viene spesso sostituito da varianti più sicure:

- **FTPS** (FTP con SSL/TLS);
- **SFTP** (FTP su SSH).
