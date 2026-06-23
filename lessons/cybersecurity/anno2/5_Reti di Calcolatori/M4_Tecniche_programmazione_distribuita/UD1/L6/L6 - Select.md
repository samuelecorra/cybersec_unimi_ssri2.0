# **Lezione 6: La chiamata `select()`**

---

### **1. Il problema delle chiamate bloccanti**

Le funzioni della Socket Library che abbiamo visto finora — `accept()`, `connect()`, `recv()`, `send()` — hanno la caratteristica comune di essere **bloccanti**: il programma che le esegue non prosegue finché la chiamata non è andata a buon fine.

- `accept()` → rimane bloccato finché non arriva una richiesta di connessione dall'esterno.
- `connect()` → rimane bloccato finché il server non completa il three-way handshake.
- `recv()` / `recvfrom()` → rimangono bloccati finché non arrivano dati.
- `send()` / `sendto()` → rimangono bloccati finché i dati non sono stati copiati nel buffer del kernel.

Per programmi semplici questo è comodo. Ma in programmi **strutturati su più connessioni dipendenti** — o che vogliono gestire un insieme di client simultaneamente — il blocco diventa uno svantaggio: un programma bloccato su `accept()` non può nel frattempo fare nient'altro.

---

### **2. Le tre strategie per gestire connessioni multiple**

Esistono fondamentalmente **tre opzioni** per scrivere programmi in grado di gestire più connessioni simultaneamente:

**Opzione 1 — Multi-processo (fork/exec)**

Si usano le chiamate `fork()` ed `exec()` — studiate in Sistemi Operativi — per strutturare il programma su più processi. La chiamata bloccante resta bloccante, ma solo per lo **specifico processo** che la esegue; gli altri processi dello stesso programma possono eseguire altre chiamate in parallelo.

**Opzione 2 — Chiamate non bloccanti**

Esistono funzioni di controllo che disattivano il blocco per una certa chiamata. Ad esempio si può fare `accept()`, proseguire a fare altre operazioni e tornare sull'`accept()` in un secondo momento per verificare se nel frattempo è arrivata una richiesta.

**Opzione 3 — `select()`**

È la chiamata specifica che consente di avere **più socket in attesa** e di gestire sistematicamente i dati che arrivano su uno qualsiasi di essi. È quella di cui ci occupiamo in questa lezione.

---

### **3. La chiamata `select()`**

`select()` permette di **controllare più socket contemporaneamente**, verificando quali sono pronti per essere letti, scritti o hanno generato un'eccezione.

#### **Caratteristiche principali**

- **Input**: la serie di socket su cui si vuole stare contemporaneamente in ascolto — adatto quando c'è un numero limitato di client fissi, noti a priori.
- **Output**: l'informazione su *quale* socket ha dati pronti in quel momento; `select()` identifica i socket pronti e li restituisce al chiamante.
- **Modalità**: può essere permanentemente bloccante, bloccante per un tempo limitato (timeout), oppure non bloccante del tutto.

---

### **4. La struttura `fd_set`: tre vettori di bit**

Per gestire i socket, `select()` usa la struttura **`fd_set`**, composta da **tre vettori di bit** — uno per ogni tipo di evento da monitorare:

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| Vettore | Evento monitorato |
|---|---|
| `readfds` | Socket pronti per la **lettura** |
| `writefds` | Socket pronti per la **scrittura** |
| `exceptfds` | Socket con **eccezioni** (es. errori) |

Se il bit $i$-esimo in uno di questi tre vettori è impostato a `1`, `select()` controllerà se il socket $i$ è pronto per l'operazione corrispondente (lettura, scrittura o eccezione). In questo modo, per ogni socket argomento, si possono definire esattamente gli eventi che `select()` dovrà attendere.

#### **Macro di gestione — prima di chiamare `select()`**

```c
FD_ZERO(&fdvar);    // Inizializza tutta la struttura a zero (obbligatorio prima di ogni select!)
FD_SET(i, &fdvar);  // Imposta a 1 il bit i-esimo: monitora il socket i
```

#### **Macro di gestione — dopo la chiamata a `select()`**

```c
int FD_ISSET(i, &fdvar);  // Booleano: restituisce TRUE se il socket i è "pronto"
```

> 📌 `FD_ZERO` va chiamato **prima di ogni invocazione** di `select()`: la struttura viene modificata da `select()` in output (lascia impostati solo i bit dei socket pronti), quindi va re-inizializzata a ogni iterazione.

---

### **5. Sintassi generale di `select()`**

```c
int status = select(nfds, &readfds, &writefds, &exceptfds, &timeout);
```

| Parametro | Descrizione |
|---|---|
| `status` | Numero di socket "pronti" tra quelli controllati; `-1` in caso di errore |
| `nfds` | Numero massimo di descrittori da controllare + 1 |
| `&readfds` | `fd_set` dei socket da controllare per **lettura** |
| `&writefds` | `fd_set` dei socket da controllare per **scrittura** |
| `&exceptfds` | `fd_set` dei socket da controllare per **eccezioni** |
| `&timeout` | Struttura `timeval` con il tempo massimo di attesa |

---

### **6. Il parametro `timeout`**

Il parametro `timeout` è quello che determina se `select()` è bloccante o meno:

| Valore di `timeout` | Effetto |
|---|---|
| Puntatore **NULL** | Attesa **infinita** — `select()` blocca il programma finché almeno uno dei socket specificati non è pronto |
| Struttura `timeval` con valore positivo | Attesa **limitata** — dopo il timeout `select()` ritorna anche se nessun socket è pronto |
| Struttura `timeval` con valore **0** | **Non bloccante** — `select()` ritorna immediatamente: si limita a inizializzare il controllo e poi lascia proseguire il programma |

> 💡 Con `timeout = 0` si può usare `select()` come semplice interrogazione dello stato dei socket: si verifica cosa è pronto in quel momento senza aspettare. Con `timeout = NULL` il programma è bloccato finché qualcosa non succede.

---

### **7. Come funziona `select()` passo dopo passo**

1. Il programmatore prepara i tre `fd_set` (`readfds`, `writefds`, `exceptfds`) con `FD_ZERO` + `FD_SET`.
2. Imposta la struttura `timeval` col timeout desiderato.
3. Chiama `select()`, che blocca il programma fino a quando almeno un socket è pronto oppure scade il timeout.
4. Al ritorno, `select()` restituisce il numero di socket pronti (≥ 0) o -1 in caso di errore.
5. Il programma usa `FD_ISSET()` per scoprire **quali** socket sono pronti e agisce di conseguenza.

---

### **8. Esempio pratico**

```c
FD_ZERO(&readfds);
FD_SET(sock_cmd,  &readfds);
FD_SET(sock_data, &readfds);

status = select(max(sock_cmd, sock_data) + 1, &readfds, NULL, NULL, &timeout);

if (status > 0) {
    if (FD_ISSET(sock_cmd, &readfds)) {
        // Dati pronti sul canale dei comandi
    }
    if (FD_ISSET(sock_data, &readfds)) {
        // Dati pronti sul canale dati
    }
}
```

Il programma non resta bloccato su una singola connessione e può gestire più client in parallelo con un solo processo — senza bisogno di multithreading.

---

### **9. Altre funzioni utili della Socket Library**

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La Socket Library include molte altre funzioni di supporto che vengono usate negli esempi e negli esercizi di programmazione:

| Funzione | Firma | Descrizione |
|---|---|---|
| `bzero` | `bzero(char* c, int n)` | Copia `n` byte iniziali nulli (0) in `c`. Equivalente a `memset(c, 0, n)`. Usata per azzerare strutture prima di inizializzarle. |
| `gethostname` | `gethostname(char *name, int len)` | Restituisce il nome dell'host corrente (nome della macchina locale). |
| `gethostbyaddr` | `gethostbyaddr(char *addr, int len, int type)` | Restituisce l'indirizzo, formattato rispetto al tipo `type` (per Internet: `AF_INET`), dell'host specificato in `addr`. Accede al resolver locale del Name Service (DNS). |
| `inet_addr` | `inet_addr(const char *cp)` | Converte una stringa di caratteri in notazione decimale puntata (es. `"192.168.1.1"`) in un intero lungo (32 bit) in **network byte order**. |
| `inet_ntoa` | `inet_ntoa(const struct in_addr in)` | Operazione inversa: converte un `long` (indirizzo IP binario) in notazione decimale puntata (stringa leggibile). |

> 💡 `gethostbyname` e `gethostbyaddr` consentono di accedere al **resolver locale del Name Service** — lo stesso sistema DNS studiato nel Modulo 3 — direttamente dall'interno di un programma C, senza aprire connessioni manuali.

---

### **10. Porte libere: il problema della terminazione anomala**

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Un problema frequente quando si scrivono esempi di programmazione con i socket è la **terminazione anomala del processo**. Quando un processo che usa socket viene interrotto con **Ctrl-C** (o da qualunque altro evento esterno), il sistema operativo potrebbe **non liberare immediatamente la porta** associata al socket.

**Perché?** Il processo non ha eseguito `close()` sui socket aperti, quindi non ha notificato al SO che le risorse allocate (buffer TCP, file descriptor) possono essere rilasciate. Il SO si accorgerà prima o poi che quelle risorse appartengono a un processo defunto e le libererà, ma questo può richiedere **anche diversi minuti**, a seconda del carico del sistema.

**Conseguenza pratica**: se si riavvia il programma subito dopo un Ctrl-C, `bind()` fallisce con "Address already in use" — la porta è ancora occupata.

#### **Soluzione: gestire i segnali Unix**

Per ridurre le probabilità che questo accada, si intercettano i **segnali di interruzione** (che in ambiente Unix corrispondono agli eventi asincroni esterni) e si forza un'uscita ordinata (`exit()` chiude correttamente tutte le risorse):

```c
#include <signal.h>

void cleanExit() { exit(0); }

// Nel codice che usa il socket:
signal(SIGTERM, cleanExit);   // Segnale di terminazione (kill)
signal(SIGINT,  cleanExit);   // Segnale di interruzione (Ctrl-C)
```

In questo modo, quando l'utente preme Ctrl-C (che genera `SIGINT`), invece di terminare bruscamente il processo senza cleanup, viene invocata `cleanExit()` che chiama `exit(0)`: il sistema operativo esegue il cleanup ordinato del processo, chiudendo tutti i file descriptor — inclusi i socket — e liberando immediatamente le porte per altri processi.

> ⚠️ Senza questa gestione, altri processi devono attendere che il SO recuperi autonomamente le risorse del processo defunto — attesa che può durare minuti. Con `signal()`, la liberazione è **immediata**.

---

### **11. Conclusione**

`select()` è il meccanismo fondamentale per la gestione non bloccante di connessioni multiple con un singolo processo. Le strutture `fd_set` e le macro `FD_ZERO`/`FD_SET`/`FD_ISSET`, insieme al controllo del timeout, permettono di costruire server efficienti senza ricorrere al multithreading. La corretta gestione dei segnali Unix completa il quadro, garantendo che i socket vengano sempre chiusi ordinatamente anche in caso di terminazione anomala.
