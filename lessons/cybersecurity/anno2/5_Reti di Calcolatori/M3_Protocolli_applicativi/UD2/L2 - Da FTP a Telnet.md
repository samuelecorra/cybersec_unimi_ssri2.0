# **Lezione 2: Da FTP a Telnet**

---

### **1. Tipica sessione FTP**

Per comprendere il funzionamento interno di FTP, è utile analizzare una **sessione tipica** tra client e server.  
FTP utilizza **due connessioni TCP parallele**:

- una per il **canale di controllo** (porta 21);
    
- una per il **canale dati** (porta 20 o altra dinamica).

![](imgs/Pasted%20image%2020260225161118.png)

Durante l’intera sessione:

1. Il **client** si connette al **server FTP** sulla porta **21** (canale di controllo).
    
2. Una volta stabilito il collegamento, vengono inviati **comandi** come `LIST` (per elencare i file) o `RETR` (per scaricarne uno).
    
3. Per trasferire effettivamente i dati, si apre una **seconda connessione TCP** tra il client e il server (canale dati).
    
4. I due canali operano in parallelo:
    
    - il **canale di controllo** serve per scambiarsi comandi e risposte testuali,
        
    - il **canale dati** trasporta il contenuto dei file o delle directory.
        

---

### **2. Sicurezza delle porte e modalità di connessione**

FTP adotta una regola importante:

> il client non comunica mai con una **porta non privilegiata** di un server che non sia considerato **sicuro**.

![](imgs/Pasted%20image%2020260225161133.png)

Le porte “note” (well-known ports) – come la **21** per il controllo e la **20** per i dati – sono riservate ai servizi di sistema, e ciò riduce il rischio di attacchi o accessi non autorizzati.

Durante un trasferimento, la sequenza tipica è la seguente:

1. Il client invia un comando `PORT` per comunicare al server la porta locale su cui riceverà i dati.
    
2. Il server risponde `PORT OK`.
    
3. Viene quindi stabilita la **connessione dati** e il file o la lista richiesta vengono trasferiti.
    

Esempio:

- Il comando `LIST` richiede la lista dei file della directory.
    
- Il comando `RETR gnutella.zip` scarica il file `gnutella.zip`.
    

---

### **3. La modalità passiva (Passive FTP)**

In alcune situazioni, il server non può **iniziare connessioni verso il client**, ad esempio se è presente un **firewall** che blocca connessioni in ingresso.  
In questi casi si usa la **modalità passiva**, che inverte il ruolo di chi apre la connessione dati.

![](imgs/Pasted%20image%2020260225161151.png)

Il meccanismo è questo:

1. Il client invia il comando `PASV` al server.
    
2. Il server risponde comunicando **una porta libera** sulla quale resterà in ascolto.
    
3. Sarà il **client** ad aprire la connessione dati verso quella porta.
    

Questa modalità è essenziale per attraversare firewall o NAT (Network Address Translation) che impediscono le connessioni server→client.

---

### **4. Introduzione a Telnet**

Dopo FTP, un altro protocollo fondamentale nato nei primi anni di Internet è **Telnet**.  
Telnet consente di **collegarsi a un host remoto** e **interagire direttamente con il suo terminale**, come se si stesse digitando sulla tastiera di quel computer.

Caratteristiche principali:

- È basato su **TCP**, quindi offre comunicazioni affidabili.
    
- Trasmette i dati in formato **NVT (Network Virtual Terminal)**, cioè **ASCII esteso**.
    
- Richiede **autenticazione** tramite nome utente e password.
    
- Per funzionare, sul sistema remoto deve essere attivo un **server Telnet**, chiamato **telnetd**, in ascolto sulla **porta 23**.
    

---

### **5. Funzionamento del protocollo Telnet**

Quando si stabilisce una connessione Telnet:

- Il **client Telnet** invia tutte le **sequenze di tasti** premuti dall’utente al sistema remoto.
    
- Il **server Telnet** esegue i comandi ricevuti e rimanda **l’output del terminale remoto** sullo schermo dell’utente.
    

Telnet fornisce due servizi principali:

1. **Terminale virtuale di rete (NVT)** – un’interfaccia standard che permette di comunicare con sistemi diversi in modo uniforme.
    
2. **Gestione delle opzioni di comunicazione** – un meccanismo di negoziazione che tratta **simmetricamente** le due estremità della connessione (client e server), decidendo dinamicamente le opzioni supportate (eco, modalità di linea, ecc.).
    

---

### **6. Struttura operativa di Telnet**

Il **client Telnet** si collega al server remoto con un comando come:

```
telnet crema.unimi.it
```

Il server Telnet:

- ascolta sulla **porta 23** le richieste in ingresso;
    
- per ogni nuova connessione, **crea un nuovo processo** (via `fork`) per gestire l’utente in modo indipendente.
    

Questo modello consente a molti utenti di accedere contemporaneamente allo stesso host.

---

### **7. Limiti e problemi di sicurezza**

Telnet è uno dei protocolli più **insicuri** tra quelli storici, perché:

- trasmette **in chiaro** sia i comandi che le **credenziali**;
    
- non offre alcuna forma di **cifratura** o **autenticazione forte**;
    
- è facilmente vulnerabile a **intercettazioni** (sniffing) e **spoofing**.
    

Per questi motivi, Telnet è stato progressivamente sostituito da **SSH (Secure Shell)**, che svolge la stessa funzione ma in modo **criptato e autenticato**.

---

### **8. Gestione dell’eterogeneità e prestazioni**

Uno dei compiti principali di Telnet è **gestire l’eterogeneità dei sistemi**:  
tradurre i caratteri di controllo, i codici ASCII e le funzioni terminali in modo uniforme, così che un computer UNIX possa comunicare senza problemi con un mainframe, un sistema Windows o un vecchio terminale.

Tuttavia, questa architettura produce un flusso continuo di **pacchetti molto piccoli**, chiamati **tinygram**, che possono appesantire la rete.  
Per limitare questo problema, Telnet (e TCP in generale) utilizza l’**algoritmo di Nagle**, che combina più caratteri in un singolo pacchetto per ridurre il traffico e migliorare l’efficienza.

---

### **9. Conclusione**

Telnet rappresenta uno dei primi esempi di **interazione remota in rete**, basato su TCP e progettato per garantire compatibilità tra sistemi diversi.  
Pur essendo oggi superato in ambito pratico, rimane un **modello concettuale essenziale** per comprendere:

- la **logica client/server**;
    
- la gestione dei **terminali virtuali**;
    
- e la necessità, oggi imprescindibile, di garantire **sicurezza e cifratura** nelle comunicazioni di rete.