# **Lezione 3: Setup di connessione**

---

### **1. UDP non richiede setup esplicito**

Per quanto riguarda i **socket UDP (`SOCK_DGRAM`)**, non c'è bisogno di alcun setup esplicito di connessione. Come abbiamo già visto, non è nemmeno necessario eseguire esplicitamente il `bind()`. Tutto quello che bisogna fare è:

1. Creare il socket.
2. Attribuire l'indirizzo del destinatario.
3. Cominciare a spedire dati.

> 📌 UDP non ha uno stato di connessione: ogni pacchetto è indipendente. Il setup di connessione è un concetto esclusivo di **TCP**.

---

### **2. TCP: partecipante passivo e partecipante attivo**

Per i **socket TCP (`SOCK_STREAM`)** esistono due tipi di partecipanti con ruoli ben distinti:

| Tipo | Ruolo | Comportamento |
|---|---|---|
| **Passivo** | Server | Rimane in ascolto, attende che arrivi una richiesta di connessione |
| **Attivo** | Client | Invia la richiesta di connessione verso il lato passivo |

Una volta stabilita la connessione, si crea un **flusso TCP bidirezionale in piggyback**: non esiste più distinzione tra mittente e destinatario. Solo nella **fase di attivazione** si può distinguere chi ha richiesto la connessione (attivo) da chi l'ha fornita (passivo).

---

### **3. Le quattro fasi del setup TCP**

Il setup della connessione si articola in **quattro fasi**:
- **2** spettano al partecipante passivo (server),
- **1** spetta al solo partecipante attivo (client),
- **1** è condivisa (scambio dati).

#### **Lato passivo (server) — fasi 1, 3 e 4**

**Fase 1 — Listen (creazione + bind + ascolto)**

Il partecipante passivo crea il socket e gli attribuisce i parametri. Tuttavia, **non conosce ancora chi si collegherà**, quindi può impostare solo **due dei quattro parametri** della quaterna che identifica la connessione:

$$(\text{IP server},\ \text{porta server},\ \overbrace{\text{IP client}}^{\text{asterisco}},\ \overbrace{\text{porta client}}^{\text{asterisco}})$$

I due parametri del client rimangono **wildcard** finché non arriva la richiesta. Poi ci si mette in attesa dell'arrivo di richieste di connessione in ingresso con `listen()`.

**Fase 2 — (del partecipante attivo, vedi sotto)**

**Fase 3 — Accept (accettazione della richiesta)**

È arrivata la richiesta di connessione e si esegue `accept()`. Questo comporta lo spostamento sul nuovo **socket di servizio**: il socket di connessione rimane libero per ricevere nuove richieste da altri client.

Passando ad `accept()` la struttura con i due parametri noti e i due asterischi, il **sistema operativo riempie i parametri mancanti**: si saprà ora sia l'indirizzo IP che il numero di porta del client che si sta connettendo.

**Fase 4 — Scambio dati (condivisa con l'attivo)**

A questo punto si è creato il flusso bidirezionale e inizia l'invio/ricezione di dati.

---

#### **Lato attivo (client) — fasi 2, 3 e 4**

**Fase 1 — (del partecipante passivo: deve già essere in `listen()` per poter ricevere la richiesta)**

**Fase 2 — Connect (richiesta di connessione)**

Il client crea un socket e gli attribuisce **tutti e quattro i parametri** della quaterna — perché il client li conosce tutti:

$$(\underbrace{\text{IP client}}_{\text{noto}},\ \underbrace{\text{porta client}}_{\text{noto o assegnata}},\ \underbrace{\text{IP server}}_{\text{noto}},\ \underbrace{\text{porta server}}_{\text{noto}})$$

![](imgs/Pasted%20image%2020260623154132.png)

**Fase 3 — Attesa dell'accettazione**

Il client attende che il server approvi la richiesta (`accept()` lato server). È questo il momento in cui avviene il three-way handshake TCP.

**Fase 4 — Scambio dati (condivisa con il passivo)**

Una volta accettata la connessione, il client può iniziare a trasmettere e ricevere dati sul flusso bidirezionale.

---

### **4. Tabella riepilogativa delle fasi**

| Fase | Chi la esegue | Funzione | Bloccante? |
|---|---|---|---|
| 1 | Server (passivo) | `socket()` + `bind()` + `listen()` | No — `listen()` ritorna subito |
| 2 | Client (attivo) | `socket()` + `connect()` | Sì — attende il completamento del handshake |
| 3 | Server (passivo) | `accept()` | Sì — attende una richiesta in arrivo |
| 4 | Entrambi | `send()` / `recv()` | Dipende dalla modalità |

---

### **5. La chiamata `listen()`**

```c
int status = listen(sock, queueLen);
```

`listen()` riceve un socket i cui parametri sono già stati inizializzati con il `bind()`: l'IP e la porta del server, e due wildcard per IP e porta del client che si collegherà.

| Parametro | Tipo | Descrizione |
|---|---|---|
| `sock` | `int` | Descrittore del socket già creato e bindato |
| `queueLen` | `int` | Numero massimo di connessioni TCP **in sospeso** (backlog) accettate su questo socket |
| Valore di ritorno | `int` | `0` se ok, `-1` se errore |

**`queueLen`** è il parametro QL della dispensa su TCP: specifica quante richieste di connessione possono accodarsi in attesa di essere servite da `accept()`. Se la coda è piena, nuove richieste vengono scartate.

> 📌 `listen()` **non è bloccante**: ritorna immediatamente. Non c'è motivo di rimanere bloccati in questa fase — il blocco avverrà nella chiamata successiva, `accept()`, quando si attenderà davvero l'arrivo di una richiesta.

---

### **6. La chiamata `accept()`**

```c
int newSock = accept(sock, &clientAddr, &addrLen);
```

Si passa il socket di listen (`sock`) e si riceve in cambio un **nuovo socket** — il socket di servizio — che verrà usato per comunicare con quel client specifico.

| Parametro | Tipo | Descrizione |
|---|---|---|
| `sock` | `int` | Il socket di listen (non viene consumato — resta disponibile per altri `accept()`) |
| `&clientAddr` | `struct sockaddr *` | Struttura che verrà riempita dal SO con IP e porta del client |
| `&addrLen` | `int *` | Dimensione della struttura; inizializzata prima della chiamata, aggiornata in output |
| Valore di ritorno | `int` | Descrittore del **nuovo socket di servizio** per questa connessione |

**Il riempimento della quaterna**: nella fase di listen il socket aveva IP server + porta server + due wildcard. Passando quella struttura ad `accept()`, il sistema operativo **completa la quaterna** con l'IP e la porta del client. Al termine:

$$(\text{IP server},\ \text{porta server},\ \underbrace{\text{IP client}}_{\text{riempito dal SO}},\ \underbrace{\text{porta client}}_{\text{riempito dal SO}})$$

Il valore restituito (`newSock`) è il **socket di servizio**: quello che si userà con `read()` / `recv()` per leggere i dati che arrivano dal client.

> ⚠️ `accept()` è **bloccante**: se si chiama `listen()` e poi `accept()`, il programma resterà fermo finché una richiesta di connessione non è effettivamente arrivata sul socket.

---

### **7. La chiamata `connect()` lato client**

```c
int status = connect(sock, &serverAddr, addrLen);
```

Il client esegue `connect()` dopo aver creato il socket e aver popolato la struttura `serverAddr` con **tutti e quattro i parametri** della quaterna (IP + porta propri, IP + porta del server). `connect()` avvia il three-way handshake TCP e blocca finché la connessione non è stabilita o fallisce.

Dopo `connect()`, il client può immediatamente usare il socket per inviare e ricevere dati.

---

### **8. Schema visivo del processo**

```
        SERVER (passivo)                   CLIENT (attivo)
        ─────────────────                  ───────────────
        socket()
        bind()            ← Fase 1
        listen()          ← non bloccante
             │
             │                             socket()
             │                             connect() ←── Fase 2 (bloccante)
             │                                  │
        accept() ←────────────────────────── SYN / SYN-ACK / ACK
             │            ← Fase 3             │
             ↓            (bloccante)           ↓
        newSock          ←───────── Fase 4: flusso bidirezionale ──────────→
        send()/recv()                      send()/recv()
```

---

### **9. Conclusione**

Il setup della connessione TCP segue uno schema asimmetrico a quattro fasi: il server si predispone (listen), il client richiede (connect), il server accetta (accept), entrambi comunicano (send/recv). La distinzione fondamentale tra attivo e passivo esiste **solo durante l'attivazione**: una volta stabilito il flusso TCP bidirezionale, i due lati sono equivalenti.
