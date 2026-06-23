# **Lezione 4: Funzioni di comunicazione via socket**

---

### **1. Introduzione**

Dopo aver visto come si crea un socket e come si stabilisce una connessione, vediamo ora **come avviene lo scambio effettivo di dati** tra due processi. Lo scambio può essere:

- **con connessione** → tramite **TCP** (`SOCK_STREAM`): funzioni `send()` e `recv()`;
- **senza connessione** → tramite **UDP** (`SOCK_DGRAM`): funzioni `sendto()` e `recvfrom()`.

---

### **2. La funzione `connect()`**

La chiamata `connect()` serve al **client** per stabilire la connessione con il server (parte passiva). È la prima operazione della parte attiva nella comunicazione TCP.

```c
int status = connect(sock, &name, nameLen);
```

| Parametro | Descrizione |
|---|---|
| `sock` | Descrittore del socket da connettere (normalmente di tipo `SOCK_STREAM`) |
| `&name` | Struttura `struct sockaddr` con **indirizzo IP e porta del server** (partecipante passivo) |
| `nameLen` | Dimensione della struttura `name` in byte |
| Valore di ritorno | `0` se ok, `-1` se errore |

> ⚠️ `connect()` è **bloccante**: il client rimane in attesa bloccato fino a quando il server non ha completato il three-way handshake. Uscire dalla `connect()` significa che l'handshake è andato a buon fine e si è pronti per inviare dati.

#### **`connect()` con socket UDP**

`connect()` può essere chiamata anche su un socket UDP. In questo caso però la sua semantica è **completamente diversa**: non viene eseguito alcun handshake né viene stabilita alcuna connessione reale. L'unico effetto è **memorizzare localmente** l'indirizzo IP e il numero di porta del server, che verranno poi utilizzati automaticamente al momento dell'invio dei dati. La vera comunicazione avviene quando si chiama `send()` o `sendto()`.

---

### **3. Invio e ricezione dati TCP: `send()` e `recv()`**

Una volta che lato client si è usciti da `connect()` e lato server si è usciti da `accept()`, si è pronti per scambiare dati sul flusso bidirezionale.

#### **`send()` — invio dati su connessione TCP**

```c
int count = send(sock, &buf, len, flags);
```

| Parametro | Descrizione |
|---|---|
| `sock` | Socket TCP **connesso** (lato client) oppure **socket di servizio** restituito da `accept()` (lato server) |
| `&buf` | Buffer contenente i dati da inviare |
| `len` | Lunghezza del buffer in byte |
| `flags` | Opzioni speciali — nella maggior parte dei casi `0` |
| Valore di ritorno | Numero di byte effettivamente trasmessi; `-1` in caso di errore |

#### **`recv()` — ricezione dati su connessione TCP**

```c
int count = recv(sock, &buf, len, flags);
```

| Parametro | Descrizione |
|---|---|
| `sock` | **Socket di servizio** restituito da `accept()` (lato server), oppure socket connesso (lato client) |
| `&buf` | Buffer in cui verranno memorizzati i dati ricevuti |
| `len` | Lunghezza del buffer di ricezione in byte |
| `flags` | Opzioni speciali — normalmente `0` |
| Valore di ritorno | Numero di byte ricevuti; `0` se il peer ha chiuso la connessione; `-1` in caso di errore |

> 📌 `send()` e `recv()` sono **bloccanti**: il programma non prosegue finché non è completato rispettivamente l'invio o la ricezione dei dati. Questo comportamento è perfettamente coerente con la **semantica di TCP**: il protocollo continua a provare a trasmettere finché non si è esaurito l'invio di tutti i dati, ritrasmettendo in caso di perdita. Le chiamate bloccano semplicemente il processo nel frattempo.

---

### **4. Invio e ricezione dati UDP: `sendto()` e `recvfrom()`**

Per i socket UDP (`SOCK_DGRAM`) si usano le funzioni `sendto()` e `recvfrom()`, che specificano **esplicitamente** l'indirizzo del destinatario o rilevano quello del mittente ad ogni chiamata.

#### **`sendto()` — invio di un datagramma UDP**

```c
int count = sendto(sock, &buf, len, flags, &addr, addrLen);
```

| Parametro | Descrizione |
|---|---|
| `sock` | Socket UDP inizializzato con i parametri della connessione (la quaterna) |
| `&buf` | Buffer contenente i dati da inviare |
| `len` | Lunghezza del buffer in byte |
| `flags` | Opzioni speciali — normalmente `0` |
| `&addr` | Struttura con **indirizzo IP e porta del destinatario** (i due parametri relativi al destinatario della quaterna) |
| `addrLen` | Dimensione della struttura `addr` |
| Valore di ritorno | Numero di byte trasmessi; `-1` in caso di errore |

Se non è stata eseguita una `connect()` preventiva, il puntatore `&addr` è obbligatorio e contiene i parametri del destinatario (IP + porta). I parametri del **mittente** (indirizzo IP e porta sorgente) vengono attribuiti automaticamente dal sistema operativo.

Se invece è stata eseguita `connect()` in precedenza (che memorizza localmente l'indirizzo del server), si può usare `send()` direttamente anche su UDP — l'indirizzo era già stato memorizzato.

#### **`recvfrom()` — ricezione di un datagramma UDP**

```c
int count = recvfrom(sock, &buf, len, flags, &name, &nameLen);
```

| Parametro | Descrizione |
|---|---|
| `sock` | Socket UDP **valorizzato parzialmente**: sono noti solo IP e porta del **ricevente** (i due parametri propri nella quaterna) |
| `&buf` | Buffer (inizialmente vuoto) in cui andrà memorizzato il dato che arriva dalla rete |
| `len` | Lunghezza del buffer in byte |
| `flags` | Opzioni speciali — normalmente `0` |
| `&name` | Struttura **inizialmente vuota**, che viene **valorizzata dal sistema operativo** con l'IP e la porta del mittente al completamento della ricezione |
| `&nameLen` | Dimensione della struttura `name`; aggiornata dal SO in output |
| Valore di ritorno | Numero di byte ricevuti; `-1` in caso di errore |

> 💡 La struttura `name` passata a `recvfrom()` completa la quaterna: il socket UDP conosce già i propri parametri (IP ricevente + porta ricevente); `recvfrom()` fa riempire al SO i due parametri mancanti (IP mittente + porta mittente) nel momento in cui arriva il datagramma.

> ⚠️ `sendto()` e `recvfrom()` sono **bloccanti**, ma **non hanno sotto la semantica di TCP**. Bloccano semplicemente l'esecuzione del processo finché il trasferimento non è completato — senza ritrasmissioni automatiche in caso di perdita, che in UDP non esistono.

---

### **5. La funzione `close()`**

Una volta terminato l'utilizzo del socket, è fondamentale chiuderlo esplicitamente.

```c
int status = close(sock);
```

| Parametro | Descrizione |
|---|---|
| `sock` | Descrittore del socket da chiudere |
| Valore di ritorno | `0` se ok, `-1` se errore |

**Perché è fondamentale chiudere il socket?** Il sistema operativo ha un **numero limitato di socket che può attivare**: ognuno di essi corrisponde alla gestione di buffer di trasmissione e ricezione (TCP gestisce buffer persistenti per la connessione stream). Chiudendo esplicitamente il socket si **restituiscono queste risorse al sistema**, rendendole disponibili per altri processi.

> ⚠️ Non chiudere i socket causa **resource leak**: in applicazioni a lunga durata (server) si esauriscono rapidamente i file descriptor disponibili, portando al fallimento delle successive chiamate `socket()`.

---

### **6. Riepilogo delle funzioni**

| Protocollo | Funzione | Scopo | Bloccante | Note |
|---|---|---|---|---|
| TCP (client) | `connect()` | Stabilisce la connessione (three-way handshake) | Sì | Uscita = handshake completato |
| UDP | `connect()` | Memorizza localmente IP+porta del server | No | Nessun handshake reale |
| TCP | `send()` | Invia dati sul canale connesso | Sì | Semantica TCP: ritrasmette fino a completamento |
| TCP | `recv()` | Riceve dati dal canale connesso | Sì | Usa il socket di servizio restituito da `accept()` |
| UDP | `sendto()` | Invia datagramma specificando il destinatario | Sì | SO assegna IP+porta mittente |
| UDP | `recvfrom()` | Riceve datagramma e scopre chi lo ha inviato | Sì | SO riempie la struttura `name` col mittente |
| TCP/UDP | `close()` | Chiude il socket e libera le risorse del SO | — | Fondamentale per evitare resource leak |

---

### **7. Conclusione**

La Socket Library fornisce un insieme di funzioni che astraggono le operazioni TCP/IP a livello di codice. La distinzione chiave tra TCP e UDP si riflette nelle chiamate:

- **TCP** — `send()`/`recv()`: bloccanti con semantica di ritrasmissione automatica, richiedono una connessione previa (connect + accept).
- **UDP** — `sendto()`/`recvfrom()`: bloccanti ma senza ritrasmissione, ogni chiamata specifica o scopre esplicitamente gli indirizzi dei partecipanti.

Con queste funzioni un programmatore può scrivere da zero client e server completi, capendo concretamente come i dati viaggiano in rete.
