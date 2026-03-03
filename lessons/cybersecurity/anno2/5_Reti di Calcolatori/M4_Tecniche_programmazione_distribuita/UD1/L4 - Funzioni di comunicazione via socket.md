## **Lezione 4: Funzioni di comunicazione via socket**

### **1. Introduzione**

Dopo aver visto come si crea un socket e come si stabilisce una connessione, vediamo ora **come avviene lo scambio effettivo di dati** tra due processi.  
Questo scambio può essere di due tipi:

- **con connessione** → tramite **TCP** (`SOCK_STREAM`);
    
- **senza connessione** → tramite **UDP** (`SOCK_DGRAM`).
    

In entrambi i casi, la comunicazione si basa su **chiamate di sistema specifiche**, che permettono di inviare e ricevere byte attraverso il canale aperto dal socket.

---

### **2. La funzione `connect()`**

La chiamata **`connect()`** serve al **client** per stabilire la connessione con il **server** (parte passiva).  
È la prima operazione della parte attiva nella comunicazione TCP.

#### **Sintassi**

```c
int status = connect(sock, &name, nameLen);
```

#### **Parametri**

- `status`: vale **0** se la connessione ha successo, **-1** in caso di errore.
    
- `sock`: è l’intero identificatore del socket da usare per la connessione.
    
- `name`: è una struttura `struct sockaddr` che contiene l’indirizzo del partecipante passivo (il server).
    
- `nameLen`: è la dimensione, in byte, della struttura `name` (di solito `sizeof(name)`).
    

> ⚠️ `connect()` è una **funzione bloccante**: il programma rimane in attesa fino a quando la connessione non viene completata (oppure fallisce).

---

### **3. Invio e ricezione di dati (TCP – con connessione)**

Quando il socket è di tipo **`SOCK_STREAM` (TCP)**, l’invio e la ricezione dei dati avviene attraverso le funzioni **`send()`** e **`recv()`**.

#### **Funzione `send()`**

Invia dati su una connessione TCP già stabilita.

```c
int count = send(socket, &buf, len, flags);
```

##### **Parametri**

- `count` → numero di byte effettivamente trasmessi; **-1** in caso di errore.
    
- `socket` → descrittore del socket TCP su cui avviene la trasmissione.
    
- `buf` → buffer di tipo `char[]` contenente i dati da inviare.
    
- `len` → lunghezza del buffer, in byte.
    
- `flags` → opzioni speciali (di solito **0**).
    

---

#### **Funzione `recv()`**

Riceve dati su una connessione TCP.

```c
int count = recv(socket, &buf, len, flags);
```

##### **Parametri**

- `count` → numero di byte ricevuti; **-1** in caso di errore.
    
- `socket` → descrittore del socket TCP.
    
- `buf` → buffer di tipo `void[]` dove verranno memorizzati i dati ricevuti.
    
- `len` → lunghezza del buffer di ricezione, in byte.
    
- `flags` → opzioni speciali, normalmente **0**.
    

> Le chiamate `send()` e `recv()` sono **bloccanti**:  
> il programma non prosegue fino a quando i dati non sono stati completamente scritti nel buffer di rete (per `send`) o ricevuti (per `recv`).

---

### **4. Invio e ricezione di dati (UDP – senza connessione)**

Per i socket **senza connessione** (`SOCK_DGRAM`, quindi con protocollo **UDP**), si utilizzano le funzioni **`sendto()`** e **`recvfrom()`**, che permettono di specificare **ogni volta** l’indirizzo del destinatario o del mittente.

---

#### **Funzione `sendto()`**

```c
int count = sendto(sock, &buf, len, flags, &addr, addrLen);
```

##### **Parametri**

- `count`, `sock`, `buf`, `len`, `flags` → identici ai parametri di `send()`.
    
- `addr` → struttura `struct sockaddr` contenente **l’indirizzo di destinazione**.
    
- `addrLen` → dimensione della struttura `addr` (di solito `sizeof(addr)`).
    

> Come per `send()`, la chiamata è **bloccante** finché i dati non sono stati inviati al livello di trasporto.

---

#### **Funzione `recvfrom()`**

```c
int count = recvfrom(sock, &buf, len, flags, &name, &nameLen);
```

##### **Parametri**

- `count`, `sock`, `buf`, `len`, `flags` → stessi significati di `recv()`.
    
- `name` → struttura `struct sockaddr` dove verrà memorizzato **l’indirizzo del mittente**.
    
- `nameLen` → dimensione della struttura `name` (inizializzata dal chiamante).
    

Anche in questo caso, la chiamata è **bloccante**: il programma rimane in attesa finché non riceve un datagramma.

---

### **5. La funzione `close()`**

Dopo che un socket ha completato la sua funzione (cioè dopo aver inviato o ricevuto i dati necessari), è **buona pratica chiuderlo** per liberare le risorse di sistema.

#### **Sintassi**

```c
int status = close(s);
```

#### **Parametri**

- `s` → descrittore del socket da chiudere.
    
- `status` → restituisce **0** se l’operazione ha avuto successo, **-1** in caso di errore.
    

#### **Effetti**

- Chiude la **connessione TCP** (nel caso di `SOCK_STREAM`), notificando al peer la fine della comunicazione.
    
- **Libera la porta** associata al socket, rendendola disponibile per altri processi.
    

> Chiudere correttamente un socket è fondamentale: lasciare connessioni aperte può causare **esaurimento di porte** o **blocco delle risorse** sul sistema.

---

### **6. Riepilogo delle principali funzioni**

|Tipo di comunicazione|Funzione|Scopo|Bloccante|
|---|---|---|---|
|TCP (client)|`connect()`|Stabilisce la connessione|✅|
|TCP|`send()`|Invia dati sulla connessione|✅|
|TCP|`recv()`|Riceve dati sulla connessione|✅|
|UDP|`sendto()`|Invia datagrammi a un indirizzo specifico|✅|
|UDP|`recvfrom()`|Riceve datagrammi da mittenti vari|✅|
|TCP/UDP|`close()`|Chiude il socket e libera la porta|—|

---

### **7. Conclusione**

La **Socket Library** fornisce un insieme di funzioni potenti ma semplici, che permettono di creare applicazioni in grado di comunicare su rete **a basso livello**.  
Ogni chiamata (`connect`, `send`, `recv`, `sendto`, `recvfrom`, `close`) rappresenta **un’astrazione diretta delle operazioni TCP/IP**, ma in forma di codice.

In sostanza:

- **TCP** offre un canale affidabile, orientato alla connessione.
    
- **UDP** offre scambio rapido di pacchetti, senza garanzia di consegna.
    

Con queste funzioni, un programmatore può scrivere da zero **client e server completi**, comprendendo finalmente **come i dati viaggiano realmente in rete**.