# Formulario Socket C — Reti di Calcolatori (M4)

**Prof. Ernesto Damiani — Esame scritto (2 ore)**
Legenda completa + tutorial progettuale per scrivere pseudocodice C corretto in tempo esame.

---

## Indice

- [PARTE 1 — Legenda](#parte-1--legenda)
  - [1.1 Include di sistema](#11-include-di-sistema)
  - [1.2 Macro di configurazione](#12-macro-di-configurazione)
  - [1.3 Costanti di sistema](#13-costanti-di-sistema)
  - [1.4 Setup indirizzo — boilerplate](#14-setup-indirizzo--boilerplate)
  - [1.5 Funzioni socket — firma completa](#15-funzioni-socket--firma-completa)
  - [1.6 Funzioni di conversione indirizzo](#16-funzioni-di-conversione-indirizzo)
  - [1.7 Funzioni ausiliarie utili](#17-funzioni-ausiliarie-utili)
- [PARTE 2 — Tutorial progettuale](#parte-2--tutorial-progettuale)
  - [2.1 Diagramma di scelta rapida](#21-diagramma-di-scelta-rapida)
  - [2.2 Template A — TCP Server + fork](#22-template-a--tcp-server--fork)
  - [2.3 Template B — TCP Client (lato attivo)](#23-template-b--tcp-client-lato-attivo)
  - [2.4 Template C — UDP Server request-response](#24-template-c--udp-server-request-response)
  - [2.5 Template D — UDP Peer + fork (chat P2P)](#25-template-d--udp-peer--fork-chat-p2p)
  - [2.6 Pattern "proxy"](#26-pattern-proxy--server-che-fa-da-client-verso-un-terzo)
  - [2.7 Pattern "risposta HTTP"](#27-pattern-risposta-http)
  - [2.8 Pattern "file system"](#28-pattern-file-system)
  - [2.9 Legge d'oro per l'esame](#29-leggi-doro-per-lesame)

---

## PARTE 1 — Legenda

### 1.1 Include di sistema

Copia-incolla **sempre tutte** — se ne hai di superflue non succede nulla, se ne manca una il compilatore protesta.

```c
/* Standard C */
#include <stdio.h>      /* snprintf, printf, fgets, fprintf, FILE*, fopen, fclose */
#include <stdlib.h>     /* exit(), atoi(), malloc() */
#include <string.h>     /* strlen(), strcpy(), strncpy(), memset(), strcmp(), strncmp() */
#include <strings.h>    /* bzero() — POSIX, NON è in <string.h> */

/* POSIX / Linux — danno errore di includePath su Windows, sono corrette su Linux */
#include <unistd.h>     /* close(), fork(), read(), write() */
#include <arpa/inet.h>  /* inet_ntoa(), inet_addr(), htons(), htonl(), ntohs(), ntohl() */
#include <netinet/in.h> /* struct sockaddr_in, INADDR_ANY, AF_INET, IPPROTO_TCP */
#include <sys/socket.h> /* socket(), bind(), listen(), accept(), connect(), send(), recv(),
                           sendto(), recvfrom(), SOCK_STREAM, SOCK_DGRAM */
/* Solo se servono mkdir / signal / errno */
#include <sys/stat.h>   /* mkdir() */
#include <sys/types.h>  /* mode_t (tipo del secondo parametro di mkdir) */
#include <signal.h>     /* signal(), SIGTERM, SIGINT */
#include <errno.h>      /* errno, EEXIST */
```

---

### 1.2 Macro di configurazione

```c
/* ── Porte ─────────────────────────────────────────────────────── */
#define PORT        8080        /* porta su cui il server ascolta (> 1024 = no root) */
#define PORT_HTTP   80          /* HTTP standard — richiede root o CAP_NET_BIND_SERVICE */
#define PORTA_UDP   12345       /* qualsiasi porta libera per UDP */

/* ── Indirizzi ──────────────────────────────────────────────────── */
#define SERVER_IP   "127.0.0.1" /* loopback (stesso host) */
#define REMOTE_IP   "10.0.0.1"  /* qualsiasi IP privato per un server di rete interna */

/* ── Buffer ─────────────────────────────────────────────────────── */
#define BUF         4096        /* buffer generico I/O — 4 KiB è il classico */
#define BUF_SMALL   256         /* per username, password, stringhe brevi */

/* ── File / directory ───────────────────────────────────────────── */
#define BASE_DIR    "./data"         /* directory base per salvataggio file */
#define LISTA_FILE  "/var/lib/app/CONNECTED" /* file di testo, un IP per riga */
```

---

### 1.3 Costanti di sistema

Queste vengono dalle librerie Linux — **non le devi definire tu**, le trovi già:

| Costante | Libreria | Valore | Significato |
|---|---|---|---|
| `AF_INET` | `<sys/socket.h>` | 2 | Famiglia indirizzi IPv4 |
| `AF_INET6` | `<sys/socket.h>` | 10 | Famiglia indirizzi IPv6 |
| `SOCK_STREAM` | `<sys/socket.h>` | 1 | Socket TCP (stream orientato alla connessione) |
| `SOCK_DGRAM` | `<sys/socket.h>` | 2 | Socket UDP (datagramma) |
| `INADDR_ANY` | `<netinet/in.h>` | 0 | Ascolta su tutte le interfacce |
| `IPPROTO_TCP` | `<netinet/in.h>` | 6 | Numero protocollo TCP (terzo param. di socket, si usa 0) |
| `IPPROTO_UDP` | `<netinet/in.h>` | 17 | Numero protocollo UDP (terzo param. di socket, si usa 0) |
| `EEXIST` | `<errno.h>` | 17 | errno: il file/directory esiste già |
| `SIGTERM` | `<signal.h>` | 15 | Segnale di terminazione (kill) |
| `SIGINT` | `<signal.h>` | 2 | Segnale Ctrl+C |

---

### 1.4 Setup indirizzo — boilerplate

Ogni socket ha bisogno di una `struct sockaddr_in`. Esiste un solo modo di farlo:

```c
struct sockaddr_in addr;
bzero((char*)&addr, sizeof(addr));  /* OBBLIGATORIO: azzera spazzatura stack */
addr.sin_family      = AF_INET;     /* sempre AF_INET per IPv4 */
addr.sin_port        = htons(PORT); /* sempre htons(): porta → network byte order */
/* poi UNO DEI DUE: */
addr.sin_addr.s_addr = INADDR_ANY;          /* lato SERVER: ascolta su tutto */
addr.sin_addr.s_addr = inet_addr(REMOTE_IP);/* lato CLIENT: indirizzo destinazione */
```

> **Regola mnemonica byte order**: le porte usano `htons()` ("short" = 16 bit), gli indirizzi numerici usano `htonl()` ("long" = 32 bit). `inet_addr()` restituisce già in network byte order, quindi non serve `htonl()` su di esso. `INADDR_ANY` è 0, che è uguale in qualsiasi byte order.

---

### 1.5 Funzioni socket — firma completa

#### `socket()` — crea il descrittore

```c
int fd = socket(int domain, int type, int protocol);
/*
 * domain   : AF_INET (IPv4)
 * type     : SOCK_STREAM (TCP) | SOCK_DGRAM (UDP)
 * protocol : 0 (default per il type scelto)
 * ritorno  : fd ≥ 0 (ok) | -1 (errore)
 */
```

#### `bind()` — lega fd a porta/IP

```c
int r = bind(int fd, const struct sockaddr *addr, socklen_t addrlen);
/*
 * fd      : il descrittore restituito da socket()
 * addr    : (struct sockaddr*)&mia_addr  ← cast SEMPRE necessario
 * addrlen : sizeof(mia_addr)
 * ritorno : 0 (ok) | -1 (EADDRINUSE se la porta è già in uso)
 */
```

#### `listen()` — passa in modalità ascolto passivo (solo TCP)

```c
int r = listen(int fd, int backlog);
/*
 * fd      : il descrittore
 * backlog : dimensione coda connessioni pendenti (tipico: 5, 64, 128)
 *           NON è il numero massimo di client contemporanei
 * ritorno : 0 (ok) | -1 (errore)
 * NON BLOCCA. Dopo listen() chiami accept().
 */
```

#### `accept()` — estrae una connessione dalla coda (solo TCP server)

```c
int sock_cli = accept(int fd, struct sockaddr *cli_addr, socklen_t *cli_addrlen);
/*
 * fd          : il socket di ascolto (da listen)
 * cli_addr    : (struct sockaddr*)&cli_struct  ← riempita dal kernel con IP/porta client
 * cli_addrlen : &cli_len  ← valore-risultato: IN=sizeof, OUT=bytes scritti
 * ritorno     : nuovo fd dedicato a questo client (≥ 0) | -1 (errore)
 * BLOCCA finché non arriva un client.
 * Il fd originale (ascolto) resta aperto per altri accept().
 */
```

#### `connect()` — avvia handshake TCP verso server (solo lato attivo)

```c
int r = connect(int fd, const struct sockaddr *addr, socklen_t addrlen);
/*
 * fd      : socket creato con socket() — NON chiamare bind() prima (il kernel sceglie porta sorgente)
 * addr    : (struct sockaddr*)&server_addr
 * addrlen : sizeof(server_addr)
 * ritorno : 0 (ok, connessione stabilita) | -1 (errore: host irraggiungibile, connessione rifiutata)
 * BLOCCA durante il three-way handshake (SYN → SYN+ACK → ACK)
 */
```

#### `send()` — invia dati su TCP (richiede connessione attiva)

```c
int n = send(int fd, const void *buf, size_t len, int flags);
/*
 * fd    : sock_cli o sock_connesso
 * buf   : puntatore ai dati
 * len   : quanti byte inviare (usa strlen(buf) per stringhe, NON sizeof(buf))
 * flags : 0 (default, nessuna opzione speciale)
 * ritorno : byte inviati (≥ 0) | -1 (errore)
 * TCP può inviare meno di len byte in una chiamata → in produzione loop su send()
 */
```

#### `recv()` — riceve dati da TCP

```c
int n = recv(int fd, void *buf, size_t len, int flags);
/*
 * fd    : socket della connessione
 * buf   : buffer di destinazione
 * len   : max byte da leggere = sizeof(buf) (evita overflow)
 * flags : 0 (default)
 * ritorno : n > 0 (byte ricevuti) | 0 (peer ha chiuso la connessione) | -1 (errore)
 * BLOCCA finché non arrivano dati
 * TCP è stream: n può essere < di quanti byte il peer ha inviato in una singola send()
 */
```

#### `sendto()` — invia datagramma UDP

```c
int n = sendto(int fd, const void *buf, size_t len, int flags,
               const struct sockaddr *dest_addr, socklen_t addrlen);
/*
 * fd        : socket UDP (SOCK_DGRAM)
 * buf       : dati da inviare
 * len       : strlen(buf) o sizeof(buf) — ogni sendto() invia UN datagramma
 * flags     : 0
 * dest_addr : (struct sockaddr*)&peer_addr  ← destinazione
 * addrlen   : sizeof(peer_addr)
 * Il mittente non deve aver chiamato bind() (il kernel assegna porta effimera)
 */
```

#### `recvfrom()` — riceve datagramma UDP + indirizzo mittente

```c
int n = recvfrom(int fd, void *buf, size_t len, int flags,
                 struct sockaddr *src_addr, socklen_t *addrlen);
/*
 * fd       : socket UDP
 * buf      : buffer ricezione
 * len      : sizeof(buf)
 * flags    : 0
 * src_addr : (struct sockaddr*)&from_addr  ← riempito dal kernel con IP/porta mittente
 * addrlen  : &from_len (valore-risultato come accept)
 * Se non ti serve l'indirizzo mittente: passa NULL, NULL
 * BLOCCA finché non arriva un datagramma
 */
```

#### `close()` — chiude fd e libera risorse

```c
close(int fd);
/*
 * Decrementa il reference count del socket.
 * Solo quando il ref count arriva a 0 il kernel invia FIN TCP e libera la struttura.
 * Dopo fork(), ENTRAMBI padre e figlio devono fare close() sui fd che non usano.
 */
```

#### `fork()` — duplica il processo

```c
pid_t pid = fork();
/*
 * ritorno nel FIGLIO  : 0
 * ritorno nel PADRE   : PID del figlio (> 0)
 * ritorno in caso errore: -1
 * Pattern esame:
 *   if (fork() == 0) { ...codice figlio... close(sock_srv); ... exit(0); }
 *   close(sock_cli);  // padre
 */
```

---

### 1.6 Funzioni di conversione indirizzo

```c
/* uint16_t host → network byte order (per porte) */
uint16_t htons(uint16_t host_port);   /* Host TO Network Short */
uint16_t ntohs(uint16_t net_port);    /* Network TO Host Short */

/* uint32_t host → network byte order (per indirizzi numerici) */
uint32_t htonl(uint32_t host_addr);   /* Host TO Network Long */
uint32_t ntohl(uint32_t net_addr);    /* Network TO Host Long */

/* stringa dotted-decimal → uint32_t in network byte order */
in_addr_t inet_addr(const char *cp);
/* es: inet_addr("10.0.0.1") → 0x0101000A (big-endian) */
/* NON usare htonl() su inet_addr: restituisce già in network byte order */

/* struct in_addr → stringa dotted-decimal  ⚠️ BUFFER STATICO: copia subito con strcpy */
char *inet_ntoa(struct in_addr in);
/* es: inet_ntoa(cli_addr.sin_addr) → "10.0.0.1" */
/* Uso corretto: strcpy(ip_buf, inet_ntoa(cli_addr.sin_addr)); */
```

---

### 1.7 Funzioni ausiliarie utili

```c
/* Leggi una riga da stdin (per il lato client interattivo) */
char *fgets(char *s, int n, FILE *stream);
/* es: fgets(line, sizeof(line), stdin); — legge fino a '\n' incluso */

/* Formatta stringa in buffer (versione sicura) */
int snprintf(char *buf, size_t size, const char *fmt, ...);
/* Scrive al massimo size byte in buf. Usa strlen(buf) per send(), NON sizeof(buf). */

/* Confronta N caratteri */
int strncmp(const char *s1, const char *s2, size_t n);
/* ritorna 0 se uguali; es: strncmp(line, "exit", 4) == 0 */

/* Copia stringa con limite */
char *strncpy(char *dst, const char *src, size_t n);
/* ATTENZIONE: non aggiunge '\0' se src è lungo ≥ n → aggiungi manualmente */

/* Converte stringa → intero (per porte da argv) */
int atoi(const char *nptr);
/* es: int port = atoi(argv[1]); */

/* Crea directory */
int mkdir(const char *path, mode_t mode);
/* mode: 0755 = rwxr-xr-x; ritorna -1 se esiste già (errno == EEXIST) */

/* Registra handler segnale */
void (*signal(int signum, void (*handler)(int)))(int);
/* es: signal(SIGINT, cleanExit);  dove  void cleanExit(int s) { exit(0); } */
```

---

## PARTE 2 — Tutorial progettuale

### 2.1 Diagramma di scelta rapida

Leggi la traccia e rispondi in sequenza:

```
1. Quanti "programmi" devo scrivere?
   ├── 1 (solo server)  → Template A (TCP) o C (UDP)
   ├── 2 (client + server) → Template B per il client, A o C per il server
   └── 1 bidirezionale (peer) → Template D (UDP P2P)

2. Qual è il protocollo?
   ├── "Connessione", "stream", "affidabile", "HTTP", "proxy"
   │   → TCP → SOCK_STREAM → usa send()/recv()
   └── "Datagramma", "senza connessione", "in tempo reale", "broadcast", "multicast"
       → UDP → SOCK_DGRAM → usa sendto()/recvfrom()

3. Il server deve servire più client contemporaneamente?
   ├── SÌ → fork() dopo accept()
   └── NO → loop diretto (spesso UDP request-response)

4. Il server deve a sua volta connettersi a un terzo servizio?
   └── SÌ → dentro il figlio: Template B (TCP client verso il terzo)
       Esempi: proxy che contatta server fedeltà, validatore di token

5. Il server deve rispondere con header HTTP?
   └── SÌ → aggiungi Pattern "risposta HTTP"

6. Il server deve salvare dati su file?
   └── SÌ → aggiungi Pattern "file system" (mkdir + fopen + fprintf)
```

---

### 2.2 Template A — TCP Server + fork

**Quando usarlo:** ogni volta che la traccia chiede un server che serve più client in TCP.
Esempi dal corso: proxy autenticato, server HTTP moderazione commenti, echo server.

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define PORT  8080
#define BUF   4096

int main(void) {

    /* ── FASE 1: crea socket di ascolto ── */
    int sock_srv = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in srv_addr;
    bzero((char*)&srv_addr, sizeof(srv_addr));
    srv_addr.sin_family      = AF_INET;
    srv_addr.sin_port        = htons(PORT);
    srv_addr.sin_addr.s_addr = INADDR_ANY;

    bind(sock_srv, (struct sockaddr*)&srv_addr, sizeof(srv_addr));
    listen(sock_srv, 64);

    /* ── FASE 2: loop di accettazione ── */
    while (1) {
        struct sockaddr_in cli_addr;
        int cli_len = sizeof(cli_addr);
        int sock_cli = accept(sock_srv, (struct sockaddr*)&cli_addr, &cli_len);

        /* leggi IP del client SUBITO — buffer statico di inet_ntoa */
        char client_ip[16];
        strcpy(client_ip, inet_ntoa(cli_addr.sin_addr));

        if (fork() == 0) {      /* ── FIGLIO: gestisce UN client ── */
            close(sock_srv);    /* figlio non ascolta nuove connessioni */
            char buf[BUF];

            /* === QUI VA TUTTA LA LOGICA APPLICATIVA === */

            /* lettura dal client */
            recv(sock_cli, buf, sizeof(buf), 0);

            /* elaborazione... */

            /* risposta al client */
            send(sock_cli, "OK\n", 3, 0);

            /* ── cleanup figlio ── */
            close(sock_cli);
            exit(0);
        }
        close(sock_cli);        /* PADRE: cede la copia e torna ad accept */
    }
}
```

**Regole da non dimenticare mai:**
1. `close(sock_srv)` nel figlio — altrimenti il socket di ascolto non viene mai liberato
2. `close(sock_cli)` nel padre — altrimenti il client non riceve il FIN TCP e il fd resta aperto per sempre
3. `exit(0)` nel figlio — il figlio non deve mai uscire dal `if` e finire nel loop del padre

---

### 2.3 Template B — TCP Client (lato attivo)

**Quando usarlo:** dentro un server (proxy) che deve connettersi a un terzo, o come programma standalone.

```c
/* Setup identico a un indirizzo server, ma con ip specifico */
struct sockaddr_in srv_addr;
bzero((char*)&srv_addr, sizeof(srv_addr));
srv_addr.sin_family      = AF_INET;
srv_addr.sin_port        = htons(PORTA_SERVER);
srv_addr.sin_addr.s_addr = inet_addr(IP_SERVER); /* NON INADDR_ANY */

/* Crea socket e connettiti — il kernel sceglie porta sorgente automaticamente */
int sock = socket(AF_INET, SOCK_STREAM, 0);
connect(sock, (struct sockaddr*)&srv_addr, sizeof(srv_addr)); /* BLOCCA */

/* Ora sock è pronto per send()/recv() */
snprintf(buf, sizeof(buf), "COMANDO %s\n", parametro);
send(sock, buf, strlen(buf), 0);        /* usa strlen, non sizeof! */
recv(sock, buf, sizeof(buf), 0);        /* risposta nel buf */

close(sock);
```

**Differenze chiave rispetto al server:**
- `inet_addr(IP)` invece di `INADDR_ANY`
- Nessun `bind()`, `listen()`, `accept()` — solo `socket()` + `connect()`
- `connect()` blocca durante il three-way handshake

---

### 2.4 Template C — UDP Server request-response

**Quando usarlo:** servizi semplici un-client-alla-volta (conversione ora, echo UDP, calcolo, …).
Non serve fork perché il datagramma arriva, viene elaborato, si risponde, poi si aspetta il successivo.

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>

#define PORT 12345
#define BUF  4096

int main(void) {

    int sockfd = socket(AF_INET, SOCK_DGRAM, 0);  /* SOCK_DGRAM = UDP */

    struct sockaddr_in srv_addr;
    bzero((char*)&srv_addr, sizeof(srv_addr));
    srv_addr.sin_family      = AF_INET;
    srv_addr.sin_port        = htons(PORT);
    srv_addr.sin_addr.s_addr = INADDR_ANY;

    bind(sockfd, (struct sockaddr*)&srv_addr, sizeof(srv_addr));
    /* NON listen(), NON accept() — UDP non ha handshake */

    while (1) {
        char buf[BUF];
        struct sockaddr_in cli_addr;
        int cli_len = sizeof(cli_addr);

        /* recvfrom BLOCCA finché arriva un datagramma; riempie cli_addr */
        int n = recvfrom(sockfd, buf, sizeof(buf)-1, 0,
                         (struct sockaddr*)&cli_addr, &cli_len);
        buf[n] = '\0';  /* null-terminate per usarlo come stringa C */

        /* === elaborazione del datagramma ricevuto in buf === */
        /* es: conversione orario, parsing, calcolo... */

        /* risposta verso il mittente (cli_addr riempita da recvfrom) */
        snprintf(buf, sizeof(buf), "RISPOSTA\n");
        sendto(sockfd, buf, strlen(buf), 0,
               (struct sockaddr*)&cli_addr, cli_len);
    }
    /* mai raggiunto */
    close(sockfd);
}
```

**UDP Client corrispondente (due righe chiave):**
```c
int sockfd = socket(AF_INET, SOCK_DGRAM, 0);
/* NON bind() — il kernel assegna porta effimera al momento di sendto */
sendto(sockfd, msg, strlen(msg), 0, (struct sockaddr*)&srv_addr, sizeof(srv_addr));
recvfrom(sockfd, buf, sizeof(buf)-1, 0, NULL, NULL); /* NULL,NULL = non ci interessa il mittente */
close(sockfd);
```

---

### 2.5 Template D — UDP Peer + fork (chat P2P)

**Quando usarlo:** due peer devono inviare e ricevere simultaneamente.
Il fork permette al figlio di restare bloccato su `recvfrom` mentre il padre legge da stdin e manda.

```c
#define BUF 4096

int main(int argc, char *argv[]) {
    int   local_port  = atoi(argv[1]);
    char *remote_ip   = argv[2];
    int   remote_port = atoi(argv[3]);

    /* socket UDP unico condiviso tra padre e figlio */
    int sockfd = socket(AF_INET, SOCK_DGRAM, 0);

    /* bind sulla porta locale per poter ricevere */
    struct sockaddr_in local_addr;
    bzero((char*)&local_addr, sizeof(local_addr));
    local_addr.sin_family      = AF_INET;
    local_addr.sin_port        = htons(local_port);
    local_addr.sin_addr.s_addr = INADDR_ANY;
    bind(sockfd, (struct sockaddr*)&local_addr, sizeof(local_addr));

    /* indirizzo del peer remoto */
    struct sockaddr_in peer_addr;
    bzero((char*)&peer_addr, sizeof(peer_addr));
    peer_addr.sin_family      = AF_INET;
    peer_addr.sin_port        = htons(remote_port);
    peer_addr.sin_addr.s_addr = inet_addr(remote_ip);

    if (fork() == 0) {          /* FIGLIO: solo ricezione */
        char buf[BUF];
        struct sockaddr_in from_addr;
        int from_len = sizeof(from_addr);
        while (1) {
            int n = recvfrom(sockfd, buf, sizeof(buf)-1, 0,
                             (struct sockaddr*)&from_addr, &from_len);
            buf[n] = '\0';
            printf("[%s] %s\n", inet_ntoa(from_addr.sin_addr), buf);
        }
    }
    /* PADRE: solo invio da stdin */
    char line[BUF];
    while (fgets(line, sizeof(line), stdin)) {
        if (strncmp(line, "exit", 4) == 0) { close(sockfd); exit(0); }
        sendto(sockfd, line, strlen(line), 0,
               (struct sockaddr*)&peer_addr, sizeof(peer_addr));
    }
    return 0;
}
```

**Perché funziona con un solo socket:** `recvfrom` e `sendto` operano in direzioni opposte sullo stesso fd UDP — non collidono.

---

### 2.6 Pattern "proxy" — server che fa da client verso un terzo

**Quando usarlo:** il server riceve credenziali, dati, richieste da un client, e le deve girare a un altro server per validarle o elaborarle.
Esempio: proxy aereo che contatta server fedeltà, server che interroga DB remoto.

```c
/* DENTRO IL BLOCCO FIGLIO (dopo fork), dopo aver raccolto username e password: */

/* === Connessione TCP verso il server terzo (lato attivo) === */
struct sockaddr_in third_addr;
bzero((char*)&third_addr, sizeof(third_addr));
third_addr.sin_family      = AF_INET;
third_addr.sin_port        = htons(TERZO_PORT);
third_addr.sin_addr.s_addr = inet_addr(TERZO_IP);

int sock_terzo = socket(AF_INET, SOCK_STREAM, 0);
connect(sock_terzo, (struct sockaddr*)&third_addr, sizeof(third_addr));

/* === dialogo col terzo === */
snprintf(buf, sizeof(buf), "VERIFICA %s %s\n", username, password);
send(sock_terzo, buf, strlen(buf), 0);
recv(sock_terzo, buf, sizeof(buf), 0);  /* risposta/token nel buf */
close(sock_terzo);                       /* chiudi subito dopo aver ricevuto */

/* === usa il risultato per rispondere al client originale === */
if (verifica_ok(buf)) {
    send(sock_cli, "OK\n", 3, 0);
} else {
    send(sock_cli, "FAIL\n", 5, 0);
}
```

**Schema mentale del proxy:**

```
Client (Wi-Fi/browser)
    │  TCP
    ▼
[Proxy server — Template A + fork]
    │  apre NUOVA connessione TCP (Template B)
    ▼
Server fedeltà / DB / validatore
```

---

### 2.7 Pattern "risposta HTTP"

**Quando usarlo:** la traccia dice "server sulla porta 80" o "scrivi l'intestazione HTTP corretta".

```c
/* Risposta 200 OK con body */
const char *body   = "Commento salvato";
int   body_len     = strlen(body);
char  http_resp[BUF];
snprintf(http_resp, sizeof(http_resp),
         "HTTP/1.1 200 OK\r\n"
         "Content-Type: text/plain; charset=utf-8\r\n"
         "Content-Length: %d\r\n"
         "Connection: close\r\n"
         "\r\n"          /* riga vuota obbligatoria tra header e body */
         "%s",
         body_len, body);
send(sock_cli, http_resp, strlen(http_resp), 0);

/* Risposta 201 Created (risorsa creata, body vuoto) */
send(sock_cli,
     "HTTP/1.1 201 Created\r\nContent-Length: 0\r\nConnection: close\r\n\r\n",
     64, 0);

/* Risposta 403 Forbidden */
send(sock_cli,
     "HTTP/1.1 403 Forbidden\r\nContent-Length: 0\r\nConnection: close\r\n\r\n",
     65, 0);

/* Risposta 400 Bad Request */
send(sock_cli,
     "HTTP/1.1 400 Bad Request\r\nContent-Length: 0\r\nConnection: close\r\n\r\n",
     66, 0);
```

**Struttura HTTP risposta — da ricordare a memoria:**
```
HTTP/1.1 <CODE> <REASON>\r\n
Content-Type: text/plain; charset=utf-8\r\n
Content-Length: <N>\r\n
Connection: close\r\n
\r\n
<body opzionale>
```

**Codici di stato più chiesti:**
| Codice | Reason | Quando |
|---|---|---|
| 200 | OK | successo generico GET |
| 201 | Created | risorsa creata (POST/PUT) |
| 400 | Bad Request | richiesta malformata |
| 403 | Forbidden | autenticazione ok ma non autorizzato |
| 404 | Not Found | risorsa inesistente |
| 500 | Internal Server Error | errore lato server |

---

### 2.8 Pattern "file system"

**Quando usarlo:** la traccia chiede di salvare dati su file/directory o di leggere una lista da file.

```c
#include <sys/stat.h>
#include <sys/types.h>
#include <errno.h>

/* Crea directory (gestisci il caso in cui esista già) */
if (mkdir(dir_path, 0755) == -1 && errno != EEXIST) {
    /* errore reale: directory non creata e non perché esisteva già */
    send(sock_cli, "HTTP/1.1 500 Internal Server Error\r\n\r\n", 38, 0);
    close(sock_cli); exit(1);
}

/* Crea directory annidata */
mkdir(BASE_DIR, 0755);              /* es: ./comments — ignora EEXIST */
snprintf(subdir, sizeof(subdir), "%s/%s", BASE_DIR, post_id);
mkdir(subdir, 0755);                /* es: ./comments/42 — ignora EEXIST */

/* Salva su file */
char filepath[512];
snprintf(filepath, sizeof(filepath), "%s/%s.txt", subdir, timestamp);
FILE *f = fopen(filepath, "w");     /* "w" = write, "a" = append */
fprintf(f, "%s\n", comment);
fclose(f);

/* Leggi un file riga per riga (es: cerca IP in CONNECTED) */
FILE *f = fopen(LISTA_FILE, "r");
char line[256];
int found = 0;
while (fgets(line, sizeof(line), f)) {
    line[strcspn(line, "\n")] = '\0';   /* rimuovi '\n' finale */
    if (strcmp(line, target_ip) == 0) { found = 1; break; }
}
fclose(f);

/* Aggiungi riga in append */
FILE *f = fopen(LISTA_FILE, "a");       /* "a" = append: non sovrascrive */
fprintf(f, "%s\n", nuovo_ip);
fclose(f);
```

---

### 2.9 Leggi d'oro per l'esame

**1. Scegli il tipo prima di scrivere una riga:**

| Ho TCP? | Ho UDP? |
|---|---|
| SOCK_STREAM | SOCK_DGRAM |
| send / recv | sendto / recvfrom |
| listen + accept | no listen, no accept |
| fork per multi-client | spesso no fork (loop diretto) |
| connect blocca (handshake) | sendto non blocca |

**2. Ordine fisso TCP server (non sbagliarlo mai):**

```
socket() → bind() → listen() → [while(1): accept() → fork() → figlio]
```

**3. Ordine fisso TCP client:**

```
socket() → connect() → send()/recv() → close()
```

**4. Ordine fisso UDP server:**

```
socket(SOCK_DGRAM) → bind() → [while(1): recvfrom() → elabora → sendto()]
```

**5. Tre close() critici da non dimenticare:**

```c
/* Nel figlio (fork): */
close(sock_srv);   // ← il figlio non ascolta nuove connessioni

/* Alla fine del figlio: */
close(sock_cli);   // ← manda FIN al client
exit(0);           // ← termina il figlio

/* Nel padre (subito dopo il fork): */
close(sock_cli);   // ← il padre cede la copia (senza: ref count > 0 → FIN mai inviato)
```

**6. strlen vs sizeof nel send:**

```c
send(fd, buf, strlen(buf), 0);  /* ← CORRETTO: manda solo i byte utili */
send(fd, buf, sizeof(buf), 0);  /* ← SBAGLIATO: manda 4096 byte con spazzatura */
```

**7. inet_ntoa è a buffer statico — copia subito:**

```c
strcpy(client_ip, inet_ntoa(cli_addr.sin_addr));  /* ← corretto */
char *p = inet_ntoa(cli_addr.sin_addr);            /* ← pericoloso: p invalido alla prossima chiamata */
```

**8. htons() per le porte, inet_addr() già in network byte order:**

```c
addr.sin_port        = htons(8080);          /* ← converti SEMPRE le porte */
addr.sin_addr.s_addr = inet_addr("10.0.0.1"); /* ← NON usare htonl() sopra inet_addr */
addr.sin_addr.s_addr = htonl(INADDR_ANY);     /* ← facoltativo: INADDR_ANY = 0 ovunque */
```

**9. buf[n] = '\0' dopo recvfrom (UDP) se vuoi usarlo come stringa:**

```c
int n = recvfrom(sockfd, buf, sizeof(buf)-1, 0, ...);
buf[n] = '\0';   /* ← UDP non aggiunge terminatore, TCP stream nemmeno */
```

**10. snprintf invece di sprintf — sempre:**

```c
snprintf(buf, sizeof(buf), "AUTH %s %s\n", user, pass);  /* ← sicuro */
sprintf(buf, "AUTH %s %s\n", user, pass);                 /* ← buffer overflow se user+pass lunghi */
```

---

## Schema tassonomia esercizi del prof (pattern osservati)

| Esame | Tipo esercizio socket | Template | Extra |
|---|---|---|---|
| 8/05/2026 | Proxy TCP autenticato con file CONNECTED | A + B (proxy) | file append |
| 23/05/2025 | Chat UDP P2P con fork | D | signal handler |
| 21/03/2025 | Server HTTP POST su porta 80 con mkdir | A | HTTP resp + filesystem |
| 20/03/2026 | Client-server UDP conversione orario | C | no fork |

**Esercizi ricorrenti accanto al socket:**
- Analisi richiesta HTTP (metodi, header, q-value, codici stato)
- Download pagina con N immagini HTTP/1.0 vs HTTP/1.1 (connessioni TCP, RTT)
- Query DNS (ricorsiva vs iterativa, flag AA, TTL)
- Regola firewall iptables (FORWARD chain, conntrack, DROP default)
