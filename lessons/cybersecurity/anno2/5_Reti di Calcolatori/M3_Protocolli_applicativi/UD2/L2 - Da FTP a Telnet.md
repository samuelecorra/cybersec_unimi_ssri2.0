# **Lezione 2: Da FTP a Telnet**

---

### **1. Tipica sessione FTP**

Per comprendere il funzionamento interno di FTP, è utile analizzare una **sessione tipica** tra client e server. FTP utilizza **due connessioni TCP parallele**:

- una per il **canale di controllo** (porta 21);
- una per il **canale dati** (porta 20 lato server, porta alta lato client).

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il server è in ascolto sulla porta 21. Il client, usando una porta alta (es. **1025**, non well-known), stabilisce la connessione al processo di controllo lato server.

A questo punto la sessione procede così:

1. Il client invia un comando `LIST` dalla sua porta 1025 alla porta 21 del server. Il comando include il numero di porta locale su cui il client attenderà la risposta (es. **1493**).
2. Il server conferma il `PORT OK` e dalla sua porta **20** invia la lista dei file alla porta **1493** del client.
3. Il client invia poi un comando `RETR gnutella.zip` dalla porta 1025 alla porta 21, comunicando questa volta la porta **1522** per ricevere il file.
4. Il server risponde con `PORT OK` e invia i dati dalla porta **20** alla porta **1522** del client.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Il client non comunica mai con una **porta non privilegiata** di un server non considerato sicuro. Le porte well-known (21 per il controllo, 20 per i dati) sono riservate ai servizi di sistema, riducendo il rischio di accessi non autorizzati.

---

### **2. La modalità passiva (Passive FTP)**

In alcune configurazioni di firewall, il server non può **iniziare connessioni verso il client**: dall'esterno possono entrare connessioni verso l'interno ma non viceversa, oppure il contrario.

Quando la connessione dal server verso il client non è abilitata, si usa la **modalità passiva**.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In modalità passiva è il **server** a comunicare un proprio numero di porta al client, e sarà il **client** ad attivare la connessione TCP verso il server per ricevere i risultati del comando.

Il meccanismo è:

1. Il client invia il comando `PASV` al server (prima del comando `LIST` o `RETR`).
2. Il server risponde confermando la modalità passiva e indicando una propria porta libera (es. **4107**).
3. Il client apre la connessione dati verso quella porta del server e riceve la risposta.

---

### **3. Introduzione a Telnet**

FTP ha un **protocollo fratello** che consente di inviare comandi da una macchina all'altra senza il meccanismo delle porte dati separate per i trasferimenti file: si tratta di **Telnet** (da _Terminale di rete_).

Telnet consente di **collegarsi a un host remoto** e di interagire direttamente con la sua shell, come se si fosse fisicamente connessi a un terminale locale o a una console della macchina.

Caratteristiche principali:

- Basato su **TCP**, quindi offre comunicazioni affidabili.
- Trasmette i dati in formato **NVT (Network Virtual Terminal)**: un formato a 8 bit che è un'estensione del classico ASCII, storicamante usato per codificare i caratteri digitati dall'utente sulla tastiera.
- Richiede **autenticazione** tramite nome utente e password.
- Come per FTP, **username e password viaggiano in chiaro**: non c'è una procedura di challenge o autenticazione cifrata.
- Per funzionare, sul sistema remoto deve essere attivo un **server Telnet** (demone **telnetd** in Unix/Linux), in ascolto sulla **porta 23**.

---

### **4. Funzionamento del protocollo Telnet**

Il server Telnet riceve dal client i comandi tramite la connessione TCP e li **passa a una shell locale del sistema operativo**, esattamente come se l'utente fosse collegato direttamente a un terminale locale. Il client vede l'output come se fosse sulla console della macchina remota.

Telnet fornisce due servizi principali:

1. **Terminale virtuale di rete (NVT)** – un'interfaccia standard per impartire da lontano comandi di shell al sistema operativo remoto, in modo uniforme tra sistemi eterogenei.

2. **Gestione delle opzioni di comunicazione** – oltre ai comandi da inoltrare alla shell remota, è possibile inviare dei comandi che **non vengono interpretati come comandi shell**, ma come opzioni che caratterizzano le modalità di comunicazione del server (es. eco, modalità linea, ecc.).

Ogni nuova connessione sulla porta 23 genera un **nuovo processo** lato server, consentendo la gestione di sessioni multiple contemporaneamente.

---

### **5. Limiti e problemi di sicurezza**

Sia FTP che Telnet, in particolare Telnet, rappresentano potenzialmente dei **buchi di sicurezza clamorosi**, perché:

- Trasmettono **in chiaro** sia i comandi che le **credenziali** (username e password).
- Chi riuscisse ad ascoltare il traffico di rete e catturare la password potrebbe poi **replicare la connessione** e impartire comandi alla macchina remota con le stesse autorizzazioni dell'utente originale.
- Il rischio è massimo se l'utente ha privilegi amministrativi.

> ⚠️ Per questo motivo Telnet è in genere **disattivato** sui server esposti. Quando è attivo, lo è solo per macchine sulla **stessa rete locale**, facendo affidamento sulla sicurezza fisica della rete (impossibilità di collegamento fisico da parte di intrusi). Non appena le porte Telnet sono accessibili da macchine remote su Internet, il sistema è indifeso.

Per questi motivi, sia FTP che Telnet sono stati progressivamente sostituiti da varianti sicure: **FTPS/SFTP** e **SSH (Secure Shell)**.

---

### **6. Importanza storica di Telnet**

Nonostante i problemi di sicurezza, Telnet ha avuto un **impatto enorme** sulla storia delle reti. Negli anni '70 e '80, il modello informatico dominante era quello basato su **minicomputer con terminali**: i terminali erano connessi fisicamente alla macchina centrale tramite cavi o concentratori.

Con Telnet è diventato possibile collegare un numero **a priori illimitato** di terminali virtuali a qualunque macchina, semplicemente collegando tutte le macchine a una rete IP locale e usando Telnet. Questo ha consentito di:

- utilizzare la **rete locale come sostituto del collegamento fisico** tra terminale e mainframe;
- eliminare la necessità di cavi dedicati per ogni terminale;
- connettere personal computer alle reti locali IP come terminali virtuali verso server centrali.

> 💡 L'NVT costituisce la naturale estensione del formato ASCII storicamente usato per codificare i caratteri premuti sulla tastiera e inviati verso la console: l'NVT porta questo paradigma sulla rete IP.

---

### **7. Prestazioni: il problema dei tinygram**

Il paradigma di Telnet impone che **ogni carattere digitato dall'utente venga inviato immediatamente al server**, perché il successivo comando che l'utente impartirà dipende dall'output di quello precedente (es. la risposta di `ls` determina il prossimo comando).

Questo genera un flusso continuo di **pacchetti molto piccoli** (tinygram), che appesantiscono la rete.

> 📌 L'**algoritmo di Nagle** è nato proprio per gestire questa situazione: combina più caratteri in un singolo pacchetto per ridurre il traffico e migliorare l'efficienza di TCP in presenza di applicazioni interattive come Telnet.
