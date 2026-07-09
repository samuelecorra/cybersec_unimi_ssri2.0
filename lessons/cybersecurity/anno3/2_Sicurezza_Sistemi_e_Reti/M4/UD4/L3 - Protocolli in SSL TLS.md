## **Lezione 3: Protocolli in SSL/TLS**

### **1. Introduzione generale**

SSL/TLS è una **suite di protocolli**. Non è composto da un unico meccanismo, ma da più protocolli coordinati che usano il **Record Protocol** per scambiarsi messaggi tra client e server.

I principali protocolli interni sono:

- **Change Cipher Spec Protocol**;

- **Alert Protocol**;

- **Handshake Protocol**;

- **Application Data Protocol**, cioè il trasporto dei dati applicativi dopo la negoziazione.

Questi protocolli gestiscono l’intero ciclo di vita di una connessione sicura:

- negoziazione iniziale;

- autenticazione;

- scelta degli algoritmi;

- generazione delle chiavi;

- attivazione della cifratura;

- segnalazione di errori;

- trasporto dei dati applicativi.

Il punto comune è che i messaggi vengono inseriti in record TLS: ciascun record ha un’intestazione e un payload, e il campo `Content Type` indica se quel payload appartiene a Handshake, Alert, Change Cipher Spec o Application Data.

> 📌 Per capire TLS bisogna distinguere il ruolo dei protocolli: Handshake negozia, Change Cipher Spec attiva i parametri, Alert segnala errori, Record Protocol protegge e trasporta i messaggi.

---

### **2. Change Cipher Spec Protocol**

#### **2.1. Funzione**

Il **Change Cipher Spec Protocol** è il protocollo più semplice della suite SSL/TLS. È costituito da un solo tipo di messaggio e serve a segnalare il passaggio ai parametri crittografici appena negoziati.

Durante l’handshake, client e server costruiscono uno stato crittografico in sospeso, detto _pending state_. Quando una parte invia `ChangeCipherSpec`, notifica all’altra che da quel momento quello stato deve diventare lo **stato corrente** della connessione.

#### **2.2. Struttura**

Il messaggio contiene **un singolo byte** di valore `1`.

Quando viene ricevuto, il destinatario deve:

- copiare lo stato “in sospeso” nello stato corrente;

- iniziare a usare le nuove chiavi;

- applicare la cipher suite negoziata;

- proteggere i messaggi successivi con i nuovi algoritmi.

> ⚠️ `ChangeCipherSpec` non negozia gli algoritmi: la negoziazione è compito dell’Handshake Protocol. Il suo unico ruolo è segnalare: “da ora in poi usiamo i parametri già concordati”.

---

### **3. Alert Protocol**

#### **3.1. Scopo**

L’**Alert Protocol** serve a trasmettere avvisi e allarmi quando qualcosa non sta avvenendo correttamente durante una comunicazione SSL/TLS.

Gli alert vengono inviati al peer, cioè all’altra entità della connessione, e sono a loro volta trasportati dentro record TLS. Quando lo stato crittografico è già attivo, anche gli alert vengono compressi, cifrati e autenticati secondo lo stato corrente.

#### **3.2. Struttura**

Ogni messaggio di alert è composto da **due byte**:

|Campo|Significato|
|---|---|
|Primo byte|Livello di gravità: `warning` oppure `fatal`|
|Secondo byte|Codice specifico dell’alert|

Il livello di gravità indica se il problema è recuperabile o no.

- Un alert **warning** segnala una condizione anomala ma non necessariamente irreversibile.

- Un alert **fatal** indica un errore grave: SSL/TLS interrompe immediatamente la connessione coinvolta.

Se l’alert è fatale, le altre connessioni già aperte nella stessa sessione possono continuare, ma non è più possibile aprire nuove connessioni all’interno di quella sessione.

**Esempio:** un alert `bad_record_mac` indica che il MAC ricevuto non coincide con quello atteso. Questo può indicare corruzione del messaggio, errore di chiave o tentativo di manomissione.

---

### **4. Handshake Protocol**

#### **4.1. Obiettivo**

L’**Handshake Protocol** è il protocollo di negoziazione di SSL/TLS. Serve a fissare i parametri della comunicazione sicura prima che inizino i dati applicativi protetti.

In particolare, l’handshake permette di:

- autenticare il server;

- autenticare opzionalmente il client;

- scegliere la versione del protocollo;

- scegliere la cipher suite;

- negoziare algoritmo di cifratura e meccanismo di MAC;

- scambiare i dati necessari alla generazione delle chiavi;

- verificare che entrambe le parti abbiano visto la stessa sequenza di messaggi.

#### **4.2. Struttura dei messaggi**

Ogni messaggio dell’handshake ha tre campi principali:

|Campo|Dimensione|Descrizione|
|---|---|---|
|**Type**|1 byte|Identifica il tipo di messaggio dell’handshake|
|**Length**|3 byte|Indica la lunghezza del contenuto|
|**Content**|Variabile|Contiene parametri, random, certificati o dati di scambio chiavi|

Il campo `Type` identifica uno dei messaggi previsti dal protocollo; `Length` permette al destinatario di sapere quanti byte leggere; `Content` contiene il materiale effettivo della negoziazione.

---

### **5. Sequenza dei messaggi di Handshake**

Il protocollo segue una sequenza logica di messaggi:

|Ordine|Messaggio|Contenuto / Funzione|
|---|---|---|
|1|**Hello Request**|Richiesta del server al client di iniziare un handshake.|
|2|**Client Hello**|Versione TLS, random del client, session ID, cipher suite e metodi di compressione supportati.|
|3|**Server Hello**|Versione scelta, random del server, session ID, cipher suite e metodo di compressione selezionati.|
|4|**Certificate**|Certificato o catena di certificati X.509v3 del server; eventualmente anche del client.|
|5|**Server Key Exchange**|Parametri del server per lo scambio delle chiavi, se necessari.|
|6|**Certificate Request**|Richiesta opzionale del certificato client.|
|7|**Server Done**|Fine dei messaggi iniziali del server.|
|8|**Client Certificate**|Certificato del client, se richiesto.|
|9|**Client Key Exchange**|Parametri del client per generare il segreto condiviso.|
|10|**Certificate Verify**|Prova del possesso della chiave privata associata al certificato client.|
|11|**Change Cipher Spec**|Passaggio ai parametri crittografici negoziati.|
|12|**Finished**|Conferma finale dell’integrità della negoziazione.|

![](imgs/Pasted%20image%2020260709012039.png)

#### **5.1. Fase 1: Hello**

La comunicazione viene avviata dal client con `Client Hello`; il server risponde con `Server Hello`.

Questi messaggi includono:

- versione del protocollo;

- identificatore di sessione;

- valori casuali del client e del server;

- cipher suite supportate dal client e scelta dal server;

- metodi di compressione disponibili o selezionati.

![](imgs/Pasted%20image%2020260709012102.png)

#### **5.2. Fase 2: messaggi del server**

Il server può inviare:

- il proprio certificato;

- parametri per lo scambio della chiave;

- richiesta di certificato client, se vuole autenticare anche il client.

Questa fase termina con `Server Done`.

#### **5.3. Fase 3: messaggi del client**

Il client risponde inviando:

- il proprio certificato, se richiesto;

- i parametri necessari allo scambio della chiave;

- eventualmente `Certificate Verify`, per dimostrare il possesso della chiave privata associata al certificato.

![](imgs/Pasted%20image%2020260709012117.png)

#### **5.4. Fase 4: attivazione e chiusura dell’handshake**

Le parti inviano `Change Cipher Spec` per attivare i parametri concordati e poi `Finished`, che conclude l’handshake.

Il messaggio `Finished` è fondamentale: verifica che l’intera sequenza dei messaggi di handshake sia stata vista nello stesso modo da entrambe le parti. Se un attaccante avesse manipolato la negoziazione, questa verifica dovrebbe fallire.

> 📌 Punto chiave: dopo `Finished`, la connessione è pienamente operativa e i dati applicativi possono essere trasmessi in modo cifrato e autenticato.

---

### **6. Costo computazionale dell’handshake e rischio DoS**

L’apertura di una sessione SSL/TLS richiede diverse operazioni lato client e lato server.

Il client deve:

- generare valori casuali;

- verificare la validità del certificato digitale del server;

- generare materiale per lo scambio della chiave;

- inviare valori cifrati o parametri crittografici;

- calcolare la chiave condivisa.

Il server deve:

- generare valori casuali;

- decifrare o elaborare i valori ricevuti dal client;

- verificare l’eventuale certificato client;

- verificare l’eventuale firma del client;

- calcolare la chiave condivisa.

Queste operazioni sono più costose del semplice trasferimento di dati già cifrati. Per questo un numero elevato di richieste di handshake può mettere sotto pressione il server e diventare una forma di **Denial of Service**.

> ⚠️ Qui il problema non è rompere la crittografia: è costringere il server a spendere CPU e risorse per molte negoziazioni, spesso iniziate e mai portate a termine.

---

### **7. Generazione e scambio delle chiavi**

La costruzione delle chiavi TLS avviene in tre fasi principali.

#### **7.1. Pre-Master Secret**

Nel caso classico basato su RSA, il client genera un valore casuale chiamato **pre-master secret**, lo cifra con la chiave pubblica del server contenuta nel certificato e lo invia al server.

Solo il server, possedendo la chiave privata corrispondente, può decifrare il valore ricevuto.

Nelle versioni e configurazioni moderne, lo scambio può usare Diffie-Hellman o ECDHE, ma l’obiettivo resta lo stesso: far arrivare client e server a un segreto condiviso che l’attaccante non possa ricostruire.

#### **7.2. Master Secret**

Client e server calcolano poi il **master secret** combinando:

$$
\text{master\_secret} = f(\text{pre\_master\_secret}, \text{client\_random}, \text{server\_random})
$$

dove $f$ è una funzione di derivazione crittografica.

I byte casuali del client e del server vengono scambiati nei messaggi `Client Hello` e `Server Hello`. Servono a garantire che sessioni diverse producano chiavi diverse, anche se coinvolgono le stesse parti.

#### **7.3. Chiavi di sessione**

Dal master secret vengono derivate almeno quattro chiavi operative:

|Chiave|Uso|
|---|---|
|**Client MAC key**|MAC dei messaggi inviati dal client|
|**Server MAC key**|MAC dei messaggi inviati dal server|
|**Client encryption key**|Cifratura dei messaggi inviati dal client|
|**Server encryption key**|Cifratura dei messaggi inviati dal server|

Le chiavi sono quindi direzionali: il traffico client → server e il traffico server → client sono protetti con materiale crittografico distinto.

![](imgs/Pasted%20image%2020260709012205.png)

---

### **8. Invio e ricezione dei dati**

Dopo la conclusione dell’handshake, i dati applicativi vengono gestiti dal Record Protocol.

![](imgs/Pasted%20image%2020260709012238.png)

#### **8.1. Invio**

Quando il client o il server devono inviare dati:

1. i dati applicativi vengono frammentati;

2. i frammenti possono essere compressi;

3. viene calcolato e aggiunto il MAC;

4. si aggiunge eventuale padding;

5. il blocco viene cifrato;

6. viene aggiunta l’intestazione TLS;

7. il record viene passato al livello di trasporto, normalmente TCP.

I record TLS diventano quindi payload di segmenti TCP, con le relative intestazioni TCP/IP.

#### **8.2. Ricezione**

All’arrivo:

1. i record TLS vengono estratti dal payload TCP;

2. la sequenza di record viene ricostruita;

3. i record, che sono numerati, vengono riordinati;

4. il contenuto viene decifrato;

5. il MAC viene verificato;

6. i dati vengono decompressi, se necessario;

7. viene ricostruito il flusso di dati in chiaro originale.

Un attaccante può ancora osservare metadati come indirizzi, porte, dimensioni e tempi dei pacchetti, ma non dovrebbe poter leggere o modificare il contenuto applicativo senza essere rilevato.

> 📌 TLS protegge il contenuto trasportato, ma si appoggia comunque a TCP per consegna, ordinamento e affidabilità del flusso.

---

### **9. Differenze tra SSL/TLS e SSH**

SSL/TLS e **SSH** hanno una funzionalità concettualmente simile: entrambi creano un **canale sicuro** o tunnel di trasporto per dati, fornendo confidenzialità, integrità e autenticazione.

Tuttavia, differiscono in diversi aspetti di contorno.

|Aspetto|SSL/TLS|SSH|
|---|---|---|
|**Scopo principale**|Sicurezza generica per applicazioni come HTTP, SMTP, IMAP|Accesso remoto sicuro, terminale, tunneling e trasferimento file|
|**Certificati / chiavi**|Usa certificati X.509 e infrastrutture PKI|Usa un formato e un modello di chiavi proprio|
|**Autenticazione**|Tipicamente server autenticato da CA; client opzionale|Spesso basata su password o chiavi pubbliche registrate sul server|
|**Servizi integrati**|Fornisce il canale sicuro, poi l’applicazione costruisce sopra|Integra nativamente shell remota, SFTP, SCP e forwarding|
|**Interoperabilità concettuale**|Tunnel sicuro general-purpose|Tunnel sicuro orientato all’accesso remoto|

Dal punto di vista del “tunnel dati”, le tecniche sono simili; nella pratica non sono intercambiabili perché differiscono formato delle chiavi, gestione dei certificati, autenticazione e protocolli applicativi costruiti sopra.

---

### **10. Conclusione**

La suite SSL/TLS combina più protocolli specializzati:

- **Handshake Protocol** negozia parametri, autenticazione e chiavi;

- **Change Cipher Spec** attiva i parametri negoziati;

- **Alert Protocol** segnala errori e condizioni anomale;

- **Record Protocol** protegge e trasporta dati e messaggi di controllo.

Nel complesso, TLS costruisce un canale sicuro sopra il trasporto, normalmente TCP, e permette alle applicazioni di comunicare con confidenzialità, integrità e autenticazione.

> ✅ Per l’esame: la sequenza corretta è negoziazione tramite Handshake, attivazione tramite Change Cipher Spec, verifica finale con Finished, poi trasporto dei dati tramite Record Protocol.
