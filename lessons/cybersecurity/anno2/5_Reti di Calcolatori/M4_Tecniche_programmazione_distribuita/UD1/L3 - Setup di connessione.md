## **Lezione 3: Setup di connessione**

### **1. Introduzione**

Quando due processi devono comunicare tramite **socket TCP**, la prima cosa da fare è **stabilire una connessione**.  
Questo passaggio — chiamato _setup di connessione_ — è ciò che permette a due applicazioni di **sincronizzarsi** prima di scambiarsi dati.

> ⚠️ Nel caso di **socket UDP (`SOCK_DGRAM`)**, non c’è alcuna connessione da instaurare: i datagrammi vengono inviati direttamente, senza fase di setup.

Per i **socket TCP (`SOCK_STREAM`)**, invece, la connessione richiede una procedura precisa tra due partecipanti:

- uno **passivo**, che attende le richieste,
    
- e uno **attivo**, che le inizia.
    

---

### **2. Partecipanti nella connessione**

La comunicazione TCP avviene tra due ruoli complementari:

|Tipo di partecipante|Ruolo|Descrizione|
|---|---|---|
|**Passivo**|Server|Rimane in ascolto, in attesa che qualcuno richieda la connessione.|
|**Attivo**|Client|Avvia la connessione verso il server, indicando indirizzo IP e porta.|

Una volta stabilita la connessione, **entrambi diventano simmetrici**:

- possono **inviare e ricevere dati**;
    
- e possono **terminare la connessione** in qualunque momento.
    

---

### **3. Fasi di setup: visione generale**

#### **a. Lato passivo (server)**

1. **`listen()`** → mette il socket in ascolto, permettendo l’arrivo di richieste.
    
2. **`accept()`** → accetta la richiesta proveniente da un client e crea un nuovo socket dedicato.
    
3. **(Fase condivisa)** → inizia lo scambio di dati con il client.
    

Dopo l’`accept()`, il **socket originale** rimane disponibile per accogliere **nuove richieste**, mentre la connessione accettata viene **trasferita su un nuovo socket**.

#### **b. Lato attivo (client)**

1. **`connect()`** → invia la richiesta di connessione al server.
    
2. **(Fase condivisa)** → inizia lo scambio di dati dopo l’accettazione.
    

In sintesi:

```plaintext
CLIENT (attivo)            SERVER (passivo)
-------------------------------------------------
connect()   ───────────────→   listen() + accept()
       ↖────────────── dati ───────────────↙
```

---

### **4. La chiamata `listen()`**

La funzione **`listen()`** viene usata dal partecipante passivo (il server) per indicare al sistema che **vuole accettare connessioni in arrivo**.

#### **Sintassi**

```c
int status = listen(sock, queueLen);
```

#### **Parametri**

- `sock` → identificatore del socket (intero).
    
- `queueLen` → numero massimo di connessioni pendenti, cioè quanti client possono restare “in attesa” di essere accettati.
    
- `status` → valore di ritorno:
    
    - `0` se la chiamata ha successo,
        
    - `-1` in caso di errore.
        

#### **Comportamento**

- `listen()` **non è bloccante**: ritorna immediatamente, lasciando al sistema operativo la gestione della coda di attesa.
    
- Dopo la chiamata, il socket entra nello stato di **“passive open”** e si prepara a ricevere richieste.
    

---

### **5. La chiamata `accept()`**

Quando arriva una richiesta da un client, il server la gestisce tramite **`accept()`**.

#### **Sintassi**

```c
int s = accept(sock, &name, &nameLen);
```

#### **Parametri**

- `sock` → il socket originale, creato e messo in ascolto da `listen()`.
    
- `name` → struttura di tipo `struct sockaddr` che conterrà l’indirizzo del partecipante attivo (il client).
    
- `nameLen` → variabile che specifica la dimensione della struttura `name`;  
    deve essere inizializzata prima della chiamata, e viene **aggiornata dal sistema operativo** al ritorno.
    
- `s` → identificatore del **nuovo socket** dedicato alla comunicazione con quel client.
    

#### **Comportamento**

- `accept()` è una **chiamata bloccante**: il server **resta in attesa** finché non arriva una richiesta valida da un client.
    
- Quando la connessione è accettata, `accept()` ritorna e restituisce **un nuovo descrittore di socket**.  
    Questo nuovo socket sarà usato per **inviare e ricevere dati**, lasciando libero il socket principale per ulteriori `accept()`.
    

---

### **6. Il ruolo di `connect()` nel lato attivo**

Dal lato client, la connessione viene richiesta tramite la chiamata **`connect()`**, che ha il compito di:

- individuare l’host remoto (tramite indirizzo IP e porta);
    
- stabilire la connessione TCP (three-way handshake);
    
- e restituire un socket pronto per l’uso.
    

Dopo la `connect()`, il client può immediatamente **leggere e scrivere dati** sulla connessione.

---

### **7. Sintesi del setup**

|Fase|Partecipante|Funzione|Stato|
|---|---|---|---|
|1|Server|`listen()`|Attende richieste, non bloccante|
|2|Client|`connect()`|Richiede connessione, bloccante|
|3|Server|`accept()`|Accetta connessione, bloccante|
|4|Entrambi|Scambio dati|Connessione stabilita|

---

### **8. Schema visivo del processo**

```plaintext
        SERVER (passivo)                   CLIENT (attivo)
        ----------------                   ----------------
        socket()                           socket()
            ↓                                  ↓
        bind()                             (opzionale)
            ↓                                  ↓
        listen()                            connect()
            ↓                                  ↓
      ┌────────────────────┐         ┌─────────────────────────┐
      │  Attende richieste │ ←────→ │  Invia richiesta TCP/IP  │
      └────────────────────┘         └─────────────────────────┘
            ↓
        accept()
            ↓
     Nuovo socket dedicato
            ↓
     Scambio dati (read/write)
```

---

### **9. Conclusione**

Il **setup di connessione TCP** è il cuore della comunicazione client/server.  
È una sequenza ordinata di operazioni (`listen()`, `connect()`, `accept()`) che permette a due processi di stabilire un **canale bidirezionale affidabile**.

Una volta stabilita la connessione:

- entrambi i partecipanti diventano equivalenti (possono leggere e scrivere);
    
- e il sistema operativo si occupa di tutto ciò che riguarda ritrasmissioni, buffering e chiusura sicura della sessione.
    

> In breve: **il setup trasforma due socket indipendenti in una connessione TCP viva**, pronta a trasportare dati tra due applicazioni distribuite.