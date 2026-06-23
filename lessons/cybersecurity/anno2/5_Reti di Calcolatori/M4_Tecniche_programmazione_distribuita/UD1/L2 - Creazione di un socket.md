# **Lezione 2: Creazione di un socket**

---

### **1. Il ruolo diverso del socket lato client e lato server**

Abbiamo detto che il socket rappresenta un **descrittore** — un numero che identifica una particolare connessione. Tuttavia, una connessione TCP ha sempre **due lati**: un client e un server. Il ruolo del socket è quindi diverso sulle due parti.

![](imgs/Pasted%20image%2020260623153824.png)

- Il **server** crea un socket sul quale arriveranno le richieste di connessione da parte dei client. Questo è il **socket di connessione** (o socket d'ascolto).
- Il **client** crea un socket per eseguire la connessione verso il server.

![](imgs/Pasted%20image%2020260623153850.png)

Quando la richiesta di connessione del client arriva al socket del server, il sistema operativo crea **un secondo socket**: il **socket di servizio**. Perché questa distinzione?

Perché il socket di connessione — usato per ricevere le richieste — è meglio che rimanga **libero** per altri client che vogliano connettersi. Il server può così continuare ad ascoltare nuove connessioni sul socket d'ascolto, mentre comunica sul socket di servizio con il client già connesso.

> 📌 I due socket corrispondono a **quaterne diverse** e quindi a diversi numeri di porta: il socket d'ascolto ha la porta del server nota a tutti i client; il socket di servizio usa porte effimere assegnate dal SO per quella specifica sessione.

---

### **2. La metafora della posta**

Per capire meglio i ruoli dei diversi elementi, il professore usa la metafora del servizio postale:

| Elemento reale | Equivalente di rete |
|---|---|
| **Utente** | L'applicazione che usa la rete |
| **Indirizzo di casa** | L'indirizzo IP della macchina |
| **Casella postale nel condominio** | Il numero di porta |
| **Ufficio postale / parco di postini** | La rete |
| **Chiave della casella** | Il socket |

Il socket è la **chiave che dà accesso alla casella postale opportuna** — cioè alla porta. Quando un programma crea un socket o comunica tramite esso, scrive o legge dati dalla propria casella di posta: la rete recapita i pacchetti così come i postini recapitano le lettere.

> 💡 La posta in uscita viene inserita nella stessa casella: ogni socket gestisce sia l'invio sia la ricezione di dati attraverso la propria porta.

---

### **3. La chiamata `socket()`: creazione puramente nominale**

La prima funzione della Socket Library è `socket()`. Questa creazione è **puramente nominale**: è rivolta al **sistema operativo locale** e gli dice di riservare delle risorse — tipicamente memoria — che serviranno poi ad allocare i buffer di trasmissione e di ricezione associati alla comunicazione che avverrà tramite quel socket.

```c
int sockfd = socket(domain, type, protocol);
```

#### **I tre parametri di `socket()`**

**`domain`** — distingue i socket di rete dai socket IPC:

| Valore | Significato |
|---|---|
| `AF_INET` | Socket per comunicazioni **in rete** via IPv4 (quelli che ci interessano) |
| `AF_INET6` | Socket per comunicazioni in rete via IPv6 |
| `AF_UNIX` | Socket per comunicazione tra **processi sulla stessa macchina** (equivalente esteso delle Pipe studiate in Sistemi Operativi) |

> 💡 La Socket Library permette quindi anche di creare delle **Pipe estese** per la comunicazione tra processi sulla stessa macchina — ma noi ci concentriamo sui socket per la comunicazione in rete (`AF_INET`).

**`type`** — specifica la tipologia di socket:

| Valore | Protocollo | Uso |
|---|---|---|
| `SOCK_DGRAM` | **UDP** | Invia e riceve pacchetti datagram non connessi |
| `SOCK_STREAM` | **TCP** | Invia e riceve su connessioni affidabili orientate al flusso |
| `SOCK_RAW` | Livello di rete grezzo | Scrive direttamente frame al livello 2, formattando i propri header (uso avanzato) |

Noi ci occuperemo esclusivamente di `SOCK_DGRAM` e `SOCK_STREAM`.

**`protocol`** — specifica il protocollo esatto da usare. Nella pratica è **ridondante**: se omesso (o impostato a `0`), viene **dedotto automaticamente dal tipo** del socket (TCP per `SOCK_STREAM`, UDP per `SOCK_DGRAM`). Si passa quasi sempre `0`.

---

### **4. La chiamata `bind()`: associare la quaterna al socket**

Una volta creato un socket TCP o UDP, bisogna **precisare i quattro parametri** che caratterizzeranno la comunicazione che avverrà tramite quel socket. Questa operazione si chiama **binding**, perché associa al socket una serie di attributi che qualificano la connessione (TCP) o l'invio di pacchetti (UDP).

I quattro parametri della **quaterna**:

$$(\text{IP mittente},\ \text{porta mittente},\ \text{IP destinatario},\ \text{porta destinataria})$$

```c
int status = bind(sockfd, (struct sockaddr *) &addrport, sizeof(addrport));
```

#### **Parametri di `bind()`**

| Parametro | Tipo | Descrizione |
|---|---|---|
| `sockfd` | `int` | Descrittore del socket creato con `socket()` |
| `&addrport` | `struct sockaddr *` | Struttura con i dati della quaterna (IP + porta) |
| `sizeof(addrport)` | `int` | Dimensione della struttura in byte |
| Valore di ritorno | `int` | `-1` se la funzione fallisce, `0` se ha successo |

#### **Wildcard: cosa non si conosce a priori**

Un server non sa *a priori* chi si collegherà con lui. È quindi possibile usare **valori wildcard (asterisco)** per i campi che non si conoscono ancora. Il più usato è:

- **`INADDR_ANY`** per l'indirizzo IP del mittente (lato server): "accetto connessioni da qualunque indirizzo IP".

I campi dell'indirizzo del client (chi si collegherà) vengono comunicati dal sistema operativo al momento dell'arrivo della richiesta di connessione.

---

### **5. Quando eseguire `bind()`: dipende dal tipo di socket**

#### **a. Socket UDP (`SOCK_DGRAM`) — solo in invio**

Se il programma **invia soltanto** dati UDP, `bind()` non è strettamente necessaria. Il sistema operativo la esegue **implicitamente** ogni volta che viene inviato un pacchetto, assegnando automaticamente una porta libera.

> ⚠️ Questa bind implicita ha però uno **scope limitato a un singolo pacchetto**: è come se venissero fatte tante `bind()` distinte, ognuna valida per un solo invio. La porta scelta può essere diversa per ogni pacchetto.

#### **b. Socket UDP (`SOCK_DGRAM`) — in ricezione**

Se il programma deve **ricevere** dati UDP, `bind()` è **obbligatoria**. Chi invia deve poter specificare un numero di porta di destinazione fisso e noto — senza un `bind()` esplicito il socket non è raggiungibile in modo stabile.

#### **c. Socket TCP (`SOCK_STREAM`)**

Per TCP il binding è concettualmente sempre eseguito, ma il comportamento dipende dal ruolo:

- **Lato client**: non è indispensabile conoscere la porta sorgente (porta mittente). Si può lasciare in bianco o con asterisco: sarà il sistema operativo a comunicarla al momento della connessione, assegnando una porta effimera.
- **Lato server**: `bind()` è **fondamentale** per fissare la porta di ascolto, quella su cui i client si connetteranno. L'indirizzo IP sorgente del client (chi si collegherà) e la sua porta possono restare wildcard: verranno comunicati dal SO al momento dell'arrivo della richiesta.

---

### **6. Riepilogo: `bind()` per tipo di socket e ruolo**

| Ruolo | Protocollo | `bind()` necessaria? | Motivo |
|---|---|---|---|
| **Client UDP** (solo invio) | `SOCK_DGRAM` | No | Il SO assegna una porta per ogni pacchetto |
| **Server UDP** (ricezione) | `SOCK_DGRAM` | Sì, obbligatoria | Il mittente deve sapere su quale porta inviare |
| **Client TCP** | `SOCK_STREAM` | No (implicita) | La porta sorgente è assegnata dal SO alla `connect()` |
| **Server TCP** | `SOCK_STREAM` | Sì, obbligatoria | Fissa la porta di ascolto nota ai client |

---

### **7. Conclusione**

La creazione di un socket tramite `socket()` è un atto **puramente locale**: dice al sistema operativo di allocare le risorse necessarie per la futura comunicazione. Il `bind()` è il passo successivo che **qualifica** quella comunicazione associandole la quaterna (IP + porta, sia sorgente che destinazione).

Capire bene questa fase è essenziale: è qui che il codice del programmatore incontra l'infrastruttura TCP/IP, e un programma locale diventa **parte attiva della rete**.
