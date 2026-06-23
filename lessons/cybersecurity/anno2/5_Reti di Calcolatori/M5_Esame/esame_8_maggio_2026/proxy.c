// ============================================================
// PROXY TCP AUTENTICATO — Wi-Fi di bordo (esame 8/05/2026)
// ============================================================
// Questo file è SOLO Linux/POSIX: le header sotto NON esistono
// su Windows nativo. Compilare sotto WSL o Linux:
//   gcc proxy.c -o proxy
// ============================================================

// --- Standard C library ---
#include <stdio.h>    // printf, snprintf, FILE*, fopen, fgets, fclose (usati implicitamente nelle funzioni "di alto livello" ip_in_file / append_ip)
#include <stdlib.h>   // exit() — per terminare il processo figlio dopo aver servito il client
#include <string.h>   // strlen(), strcpy(), strncpy(), memset() — manipolazione stringhe/buffer
#include <strings.h>  // bzero() — NON è in <string.h> (C standard) ma in <strings.h> (POSIX). Azzera un blocco di memoria. Su Linux entrambi i .h coesistono; senza questo include si ottiene un warning "implicit declaration".

// --- Socket API POSIX (Linux-only) ---
#include <unistd.h>      // close(), fork(), getpid() — close() chiude file descriptor (inclusi i socket); fork() crea processi figli
#include <arpa/inet.h>   // inet_ntoa() (uint32_t → stringa "a.b.c.d"), inet_addr() (stringa → uint32_t), htons()/htonl() per conversione byte order
#include <netinet/in.h>  // struct sockaddr_in, INADDR_ANY, costanti AF_INET, IPPROTO_TCP, tipi uint16_t/uint32_t degli indirizzi
#include <sys/socket.h>  // socket(), bind(), listen(), accept(), connect(), send(), recv() — l'intera API socket BSD

// ============================================================
// COSTANTI DI CONFIGURAZIONE
// ============================================================
// È buona norma raccogliere tutti i "magic number" in #define:
// così se dobbiamo cambiare un valore lo cambiamo in un posto solo.

#define PROXY_PORT     8080
// La porta su cui il proxy ascolta connessioni dai client Wi-Fi.
// Usiamo 8080 (porta non privilegiata, > 1024) per non richiedere
// privilegi di root per aprire la porta (su Linux le porte < 1024
// richiedono CAP_NET_BIND_SERVICE o root).

#define FEDELTA_IP     "10.10.10.1"
// IP del server remoto del programma fedeltà della compagnia aerea.
// La traccia non specifica il valore: usiamo un IP privato di classe A
// (10.x.x.x) a titolo di esempio. In uno scenario reale sarebbe
// un IP pubblico o un indirizzo raggiungibile tramite la rete satellite
// dell'aeromobile.

#define FEDELTA_PORT   443
// Porta del server fedeltà. Scegliamo 443 (HTTPS) perché supponiamo
// che la comunicazione col server fedeltà sia cifrata con TLS.
// Nella realtà, il codice C di basso livello che scriviamo qui aprirebbe
// una TCP "nuda" (senza TLS); TLS richiederebbe OpenSSL o simili.
// Per l'esame è sufficiente indicare la porta e motivare la scelta.

#define CONNECTED_FILE "/var/lib/proxy/CONNECTED"
// Path del file che funge da "lista bianca" degli IP autorizzati.
// Il file contiene un IP per riga, es.:
//   10.0.0.42
//   10.0.0.17
// Il path /var/lib/ è la directory standard Linux per dati persistenti
// di applicazioni di sistema (FHS — Filesystem Hierarchy Standard).
// In un esercizio d'esame il path specifico è irrilevante.

#define BUF            4096
// Dimensione del buffer generico di I/O (4 KiB).
// Usato sia per leggere dati dai socket (username, password, token)
// sia per costruire il messaggio "AUTH user pass\n" da inviare al
// server fedeltà. 4096 byte è un valore classico (un "page" di memoria).

// ============================================================
// FUNZIONI "DI ALTO LIVELLO" (stub per l'esame)
// ============================================================
// Le tre funzioni qui sotto NON sono implementate in questo file:
// vengono fornite come "chiamate di libreria" secondo la traccia.
// In un progetto reale sarebbero in un file .c separato con il .h.
// Le dichiariamo (forward declaration) per evitare warning del compilatore.

int  ip_in_file(const char *path, const char *ip);
// Apre il file 'path', legge riga per riga, e ritorna 1 se trova 'ip',
// 0 altrimenti. Usa fopen/fgets/fclose (I/O di alto livello standard C).

void append_ip(const char *path, const char *ip);
// Apre il file 'path' in modalità append ("a"), scrive ip + '\n',
// chiude il file. La modalità "a" garantisce che scrivi in coda
// senza sovrascrivere le righe già presenti.

int  loyalty_check_token(const char *token);
// Valida il token ricevuto dal server fedeltà. Restituisce un valore
// non-zero se il token è valido/non scaduto, 0 se invalido o scaduto.

// ============================================================
// MAIN
// ============================================================
// Ci basta un singolo main: in un esercizio d'esame non serve
// spezzare il codice in funzioni ausiliarie. L'obiettivo è mostrare
// la sequenza socket() → bind() → listen() → accept() → fork()
// richiesta dal modulo M4/UD1 del corso.

int main(void) {

    // ----------------------------------------------------------
    // PASSO 1: creiamo il socket di ascolto del proxy (lato server)
    // ----------------------------------------------------------
    int sock_srv = socket(AF_INET, SOCK_STREAM, 0);
    // socket() chiede al kernel di creare un nuovo "endpoint" di
    // comunicazione e restituisce un file descriptor (intero ≥ 0).
    // Il kernel crea le strutture interne (PCB, buffer TX/RX) ma
    // non ancora collega il socket ad alcun indirizzo/porta.
    //
    // Parametri:
    //   AF_INET      = Address Family Internet → IPv4
    //                  (usare AF_INET6 per IPv6)
    //   SOCK_STREAM  = tipo stream → garantisce:
    //                  * consegna ordinata (i byte arrivano nell'ordine
    //                    in cui sono stati mandati)
    //                  * affidabile (ritrasmissioni automatiche in caso
    //                    di perdita o corruzione)
    //                  * orientato alla connessione (three-way handshake)
    //                  In pratica: SOCK_STREAM ≡ TCP
    //   0            = protocollo di default per il tipo scelto.
    //                  Per SOCK_STREAM + AF_INET il default è TCP
    //                  (IPPROTO_TCP = 6). Il valore 0 delega la scelta
    //                  al kernel invece di specificare esplicitamente.
    //
    // Valore di ritorno: fd ≥ 0 in caso di successo, -1 in caso di errore.
    // (In pseudocodice d'esame omettiamo il controllo degli errori.)

    // ----------------------------------------------------------
    // PASSO 2: prepariamo la struttura di indirizzo del server
    // ----------------------------------------------------------
    struct sockaddr_in srv_addr;
    // sockaddr_in è la struttura C che rappresenta un indirizzo IPv4. in sta per "internet"
    // È definita in <netinet/in.h> e ha questi campi principali:
    //   sin_family : tipo sa_family_t (uint16_t) — famiglia di indirizzi
    //   sin_port   : tipo in_port_t  (uint16_t) — porta in network byte order
    //   sin_addr   : tipo struct in_addr         — indirizzo IPv4 a 32 bit
    //     └─ s_addr: tipo in_addr_t (uint32_t)  — il valore effettivo
    // La struttura è allocata sullo stack (non usiamo malloc):
    // per questo tipo di dato è sufficiente.

    bzero((char*)&srv_addr, sizeof(srv_addr));
    // bzero() azzera sizeof(srv_addr) byte a partire dall'indirizzo di
    // srv_addr. È FONDAMENTALE farlo PRIMA di impostare i campi:
    // le variabili locali in C non sono zero-inizializzate automaticamente,
    // quindi i byte di srv_addr potrebbero contenere qualsiasi "spazzatura"
    // lasciata da chiamate precedenti sullo stesso stack frame.
    // Un campo sin_family errato o byte spuri in sin_zero (il padding
    // della struttura) potrebbero far fallire bind() in modo misterioso.
    //
    // Alternativa moderna equivalente: memset(&srv_addr, 0, sizeof(srv_addr))
    // Il cast a (char*) è necessario perché bzero prende void* / char*.

    // Ora impostiamo i tre campi che ci interessano:
    srv_addr.sin_family = AF_INET;
    // Indica che questa struttura descrive un indirizzo IPv4.
    // Il kernel usa questo campo per sapere come interpretare
    // gli altri byte della struttura (sin_port, sin_addr).
    // Deve corrispondere al primo argomento di socket() (AF_INET).

    srv_addr.sin_port = htons(PROXY_PORT);
    // htons() = "Host TO Network Short": converte il numero di porta
    // dal "byte order dell'host" (su x86/x64: little-endian, il byte
    // meno significativo per primo) al "network byte order" (big-endian,
    // il byte più significativo per primo, standard Internet per i numeri
    // multi-byte nei protocolli di rete).
    //
    // Esempio: PROXY_PORT = 8080 = 0x1F90
    //   little-endian (x86):  memoria → [0x90] [0x1F]  ← SBAGLIATO per rete
    //   big-endian (network): memoria → [0x1F] [0x90]  ← CORRETTO
    //
    // Se NON usassimo htons(), il kernel leggerebbe 0x9001 = 36865 invece
    // di 0x1F90 = 8080, e il server ascolterebbe sulla porta sbagliata.
    // La regola pratica: porta e indirizzo vanno SEMPRE convertiti
    // con htons()/htonl() prima di metterli in sockaddr_in.

    srv_addr.sin_addr.s_addr = INADDR_ANY;
    // INADDR_ANY = 0 (la macro è definita in <netinet/in.h>).
    // Indica al kernel di accettare connessioni su TUTTE le interfacce
    // di rete disponibili nel sistema: Wi-Fi (wlan0), Ethernet (eth0),
    // loopback (127.0.0.1), ecc.
    // In alternativa potremmo specificare un IP preciso con inet_addr(),
    // ma per il proxy di bordo vogliamo restare in ascolto su qualsiasi
    // interfaccia da cui arrivino i client.
    // Nota: INADDR_ANY è 0, che in network byte order è ancora 0,
    // quindi NON serve htonl(INADDR_ANY) — ma scriverlo non è un errore.

    // ----------------------------------------------------------
    // PASSO 3: bind — associa il socket all'indirizzo/porta
    // ----------------------------------------------------------
    bind(sock_srv, (struct sockaddr*)&srv_addr, sizeof(srv_addr));
    // bind() dice al kernel: "associa il file descriptor sock_srv
    // all'indirizzo IPv4/porta descritti da srv_addr".
    // Dopo bind(), il kernel sa che i pacchetti TCP destinati alla
    // porta 8080 di questo host devono essere consegnati a sock_srv.
    //
    // Parametri:
    //   sock_srv                   : fd del socket creato con socket()
    //   (struct sockaddr*)&srv_addr: puntatore alla struttura di indirizzo.
    //     Il cast a (struct sockaddr*) è necessario perché bind() è una
    //     funzione generica (gestisce sia IPv4 con sockaddr_in, sia IPv6
    //     con sockaddr_in6, sia Unix domain socket con sockaddr_un, ecc.).
    //     La firma di bind() prende il tipo base sockaddr*, e noi
    //     passiamo il tipo specifico sockaddr_in* tramite cast.
    //   sizeof(srv_addr)           : dimensione in byte della struttura.
    //     Il kernel usa questo valore per sapere quanti byte leggere
    //     dall'indirizzo puntato (importante per IPv6, dove sockaddr_in6
    //     è più grande di sockaddr_in).
    //
    // Valore di ritorno: 0 = successo, -1 = errore (errori tipici:
    //   EADDRINUSE se la porta 8080 è già occupata da un altro processo,
    //   EACCES se la porta è < 1024 e non siamo root).

    // ----------------------------------------------------------
    // PASSO 4: listen — metti il socket in modalità "ascolto passivo"
    // ----------------------------------------------------------
    listen(sock_srv, 64);
    // listen() NON blocca: ritorna immediatamente.
    // Serve a "marcare" sock_srv come socket passivo (che aspetta
    // connessioni in entrata) anziché attivo (che avvia connessioni).
    //
    // Internamente, il kernel crea una CODA di connessioni pendenti
    // (chiamata "backlog queue") per sock_srv:
    //   - quando arriva un SYN da un client, il kernel fa il
    //     three-way handshake autonomamente (SYN → SYN+ACK → ACK)
    //     e mette la connessione completata nella coda
    //   - accept() (passo successivo) preleva la prima connessione
    //     dalla coda e la restituisce al processo
    //
    // Parametro 64 = dimensione massima della coda (backlog).
    //   Se 64 connessioni sono in coda e ne arriva una 65ª prima che
    //   accept() le estragga, il kernel scarta il SYN della 65ª
    //   (il client riproverà dopo il timeout di ritrasmissione).
    //   64 è abbondante per un proxy di bordo aereo (tipicamente
    //   < 300 passeggeri, che non si connettono tutti simultaneamente).

    // ----------------------------------------------------------
    // LOOP PRINCIPALE: il proxy non termina mai spontaneamente
    // ----------------------------------------------------------
    while (1) {
        // while(1) = loop infinito: il proxy resta in ascolto per tutta
        // la durata del volo. In produzione si gestirebbe SIGTERM/SIGINT
        // con un signal handler per chiudere ordinatamente (cfr. M4/UD1/L6,
        // cleanExit). Per l'esame è sufficiente il loop semplice.

        // ----------------------------------------------------------
        // PASSO 5: prepariamo lo spazio per l'indirizzo del client
        //          (PRIMA di accept — accept lo riempirà)
        // ----------------------------------------------------------
        struct sockaddr_in cli_addr;
        // Struttura che accept() riempirà con l'IP e la porta del
        // client che si sta connettendo. La allochiamo sullo stack
        // PRIMA di chiamare accept() perché accept() vuole un puntatore
        // a memoria già allocata dove scrivere — non alloca lei stessa.

        int cli_len = sizeof(cli_addr);
        // accept() usa questo parametro in modo "value-result":
        //   IN:  cli_len dice ad accept() quanta memoria ha a disposizione
        //        per scrivere l'indirizzo del client (= sizeof(cli_addr))
        //   OUT: accept() aggiorna cli_len con quanti byte ha scritto
        //        effettivamente (può essere ≤ sizeof(cli_addr))
        // Nota tecnica: la firma corretta sarebbe socklen_t cli_len
        // (tipo senza segno), ma int funziona in pratica su quasi tutti
        // i sistemi a 32/64 bit. In codice di produzione usare socklen_t.

        // ----------------------------------------------------------
        // PASSO 6: accept — blocca finché non arriva un client
        // ----------------------------------------------------------
        int sock_cli = accept(sock_srv, (struct sockaddr*)&cli_addr, &cli_len);
        // accept() è BLOCCANTE: il processo si ferma qui (nessuna CPU
        // consumata — il kernel mette il processo in stato "sleeping")
        // finché un client non completa il three-way handshake.
        //
        // Quando un client si connette, accept():
        //   1. estrae la connessione completata dalla coda del backlog
        //   2. crea un NUOVO socket file descriptor (sock_cli) dedicato
        //      esclusivamente a questa singola connessione client
        //   3. riempie cli_addr con IP e porta del client (lato kernel:
        //      vengono copiati direttamente dagli header TCP/IP del pacchetto
        //      SYN ricevuto — il client non può falsificarli dall'interno
        //      della LAN DHCP di bordo)
        //   4. ritorna sock_cli ≥ 0 (success) o -1 (error)
        //
        // ATTENZIONE: sock_srv continua ad ascoltare nuove connessioni
        // (rimane "aperto"). sock_cli è il canale privato con IL client.
        // Questa è la distinzione fondamentale "welcome socket" vs
        // "connection socket" (cfr. M4/UD1/L7 — Socket Java, §2).
        //
        // Il cast (struct sockaddr*) ha lo stesso motivo di bind():
        // accept() è generica e prende il tipo base sockaddr*.

        // ----------------------------------------------------------
        // PASSO 7: leggiamo l'IP del client dalla struttura cli_addr
        // ----------------------------------------------------------
        char client_ip[16];
        // Array di 16 byte per contenere l'IP del client come stringa
        // dotted-decimal null-terminated.
        // Il massimo indirizzo IPv4 è "255.255.255.255" = 15 caratteri
        // + 1 byte per '\0' (terminatore di stringa C) = 16 byte totali.

        strcpy(client_ip, inet_ntoa(cli_addr.sin_addr));
        // inet_ntoa() ("inet Number TO Ascii") converte il valore binario
        // a 32 bit di cli_addr.sin_addr (in network byte order) nella
        // stringa dotted-decimal leggibile (es. "10.0.0.42").
        //
        // ⚠️ ATTENZIONE CRITICA: inet_ntoa() restituisce un puntatore a un
        // buffer STATICO INTERNO (non allocato da noi, non sul nostro stack,
        // non su heap). Questo significa che:
        //   - la prossima chiamata a inet_ntoa() SOVRASCRIVERÀ quel buffer
        //   - il puntatore diventa invalido dopo la prossima chiamata
        //   - in ambienti multi-thread, due thread che chiamano inet_ntoa()
        //     contemporaneamente si correggono a vicenda (NOT thread-safe)
        //
        // Quindi usiamo strcpy() per copiare SUBITO la stringa nel nostro
        // array client_ip (sullo stack, sicuro) prima che inet_ntoa()
        // venga chiamata di nuovo.
        //
        // Nota: inet_ntoa() prende struct in_addr (non sockaddr_in!),
        // quindi passiamo cli_addr.sin_addr (il sotto-campo specifico).
        //
        // La traccia dice: "È vostra responsabilità leggere dal socket
        // l'indirizzo privato dei client." — questa riga risponde
        // esattamente a quel requisito: l'IP è letto DALL'ACCEPT (kernel),
        // non da header applicativi che il client potrebbe falsificare.

        // ----------------------------------------------------------
        // PASSO 8: fork — crea un processo figlio per gestire il client
        // ----------------------------------------------------------
        if (fork() == 0) {
            // fork() crea una copia identica del processo corrente.
            // La copia ("figlio") ha:
            //   - la stessa memoria (stack, heap, variabili globali)
            //     COPIATA al momento del fork (copy-on-write in Linux)
            //   - gli stessi file descriptor aperti (inclusi sock_srv
            //     e sock_cli)
            //   - un PID diverso dal padre
            //
            // Valore di ritorno di fork():
            //   = 0   → siamo nel processo FIGLIO (questo ramo if)
            //   > 0   → siamo nel processo PADRE (il valore è il PID figlio)
            //   = -1  → errore (ENOMEM, EAGAIN — troppi processi)
            //
            // Il pattern fork() == 0 è il modo standard in C per
            // "eseguire questo blocco solo nel figlio".
            // Il padre NON entra nell'if, salta direttamente alla riga
            // dopo la chiusura della graffa if/else, cioè close(sock_cli).
            //
            // Perché fork() per la concorrenza?
            //   Senza fork(), accept() estrae UN client alla volta e il proxy
            //   non potrebbe servire il secondo finché non ha finito con il primo.
            //   Con fork(), il padre torna subito ad accept() mentre il figlio
            //   gestisce il client corrente (cfr. M4/UD1/L6, §1 — strategie
            //   per connessioni multiple).

            close(sock_srv);
            // Il figlio ha ereditato una COPIA del fd sock_srv (il socket
            // di ascolto). Il figlio non ne ha bisogno: deve solo parlare
            // con sock_cli. Se il figlio non lo chiudesse:
            //   - quando il padre fa close(sock_srv) (ad es. per SIGTERM),
            //     il kernel NON chiude effettivamente il socket perché c'è
            //     ancora il figlio che lo tiene "aperto" (reference count > 1)
            //   - il socket di ascolto non verrebbe mai liberato
            //     finché il figlio è vivo
            // Chiudendolo subito, il figlio "cede" il suo riferimento
            // e il reference count scende: il padre potrà chiuderlo
            // quando vorrà.

            char buf[BUF];
            // Buffer generico di 4096 byte allocato sullo STACK del figlio.
            // Ogni processo figlio ha il proprio stack indipendente
            // (eredita una copia dal padre al momento del fork).
            // Useremo buf per:
            //   - ricevere username dal client (recv)
            //   - ricevere password dal client (recv)
            //   - costruire il messaggio "AUTH user pass\n" (snprintf)
            //   - ricevere il token dal server fedeltà (recv)

            // ----------------------------------------------------------
            // PASSO 9 (figlio): controllo se il client è già autorizzato
            // ----------------------------------------------------------
            if (ip_in_file(CONNECTED_FILE, client_ip)) {
                // ip_in_file() è la funzione "di alto livello" della libreria:
                // apre CONNECTED_FILE, scorre le righe, confronta ciascuna
                // con client_ip. Ritorna 1 (true) se trovato.
                //
                // Se il client è già nel file CONNECTED, non dobbiamo
                // riautenticarlo: gli rispondiamo subito e chiudiamo.
                // Questo ottimizza il flusso per i client che si
                // disconnettono e riconnettono (es. modo aereo disattivato
                // e riattivato): non devono reinserire le credenziali.

                send(sock_cli, "ALREADY_AUTHORIZED\n", 19, 0);
                // send() manda dati su un socket TCP (stream).
                // Parametri:
                //   sock_cli                  : fd del socket verso il client
                //   "ALREADY_AUTHORIZED\n"    : puntatore al buffer da inviare
                //   19                        : numero di byte da inviare
                //                               (A-L-R-E-A-D-Y-_-A-U-T-H-O-R-I-Z-E-D-\n
                //                               = 19 byte; in produzione: strlen())
                //   0                         : flags (0 = nessuna opzione speciale;
                //                               alternativa: MSG_NOSIGNAL per non
                //                               ricevere SIGPIPE se il client ha chiuso)
                //
                // In TCP, send() non garantisce che tutti i byte siano inviati
                // in una singola chiamata (può restituire n < 19): in produzione
                // si chiama in loop finché tutti i byte sono stati inviati.

                close(sock_cli);
                // Chiude la connessione col client (FIN TCP).
                // Libera il file descriptor.

                exit(0);
                // Termina il processo figlio con codice di uscita 0 (successo).
                // Il kernel libera memoria, fd aperti e risorse del figlio.
                // ⚠️ Nota: il padre dovrebbe chiamare wait()/waitpid() per
                // "raccogliere" lo stato del figlio ed evitare "zombie process"
                // (figlio terminato ma non ancora rimosso dalla tabella processi).
                // In questo pseudocodice d'esame omettiamo wait() per brevità.
            }

            // ----------------------------------------------------------
            // PASSO 10 (figlio): raccolta credenziali dal client
            // ----------------------------------------------------------
            send(sock_cli, "USERNAME?\n", 10, 0);
            // Mandiamo al client un "prompt" testuale che richiede l'username.
            // È il nostro semplice "protocollo applicativo" a livello testo.
            // "USERNAME?\n" = 10 byte (U-S-E-R-N-A-M-E-?-\n).

            recv(sock_cli, buf, sizeof(buf), 0);
            // recv() è BLOCCANTE: il figlio aspetta finché il client
            // non invia dati (l'username che l'utente ha digitato).
            //
            // Parametri:
            //   sock_cli       : fd del socket verso il client
            //   buf            : buffer dove copiare i dati ricevuti
            //   sizeof(buf)    : dimensione massima da leggere (= BUF = 4096)
            //                    evita di scrivere oltre la fine del buffer
            //   0              : flags (0 = comportamento di default)
            //
            // Valore di ritorno: n ≥ 0 byte letti.
            //   n = 0  → il client ha chiuso la connessione (FIN ricevuto)
            //   n > 0  → n byte di dati nell'intervallo buf[0..n-1]
            //   n = -1 → errore
            //
            // ⚠️ TCP è uno STREAM di byte, NON un protocollo a messaggi:
            // recv() può restituire meno byte di quelli che il client ha
            // inviato (se sono ancora "in viaggio") o più (se il client ha
            // inviato username + password insieme). In produzione si legge
            // in loop finché si incontra il delimitatore '\n'.
            //
            // Dopo questa recv(), buf contiene l'username seguito da '\n'
            // (es. "mario.rossi\n") e il byte successivo non è determinato.

            char username[256];
            // Buffer locale per l'username estratto da buf.
            // 256 byte = lunghezza massima ragionevole per un username.
            //
            // ⚠️ NOTA PSEUDOCODICE: in un'implementazione reale qui si farebbe:
            //   strncpy(username, buf, sizeof(username) - 1);
            //   username[sizeof(username) - 1] = '\0';  // null-terminator sicuro
            //   // rimuovi il '\n' finale se presente:
            //   username[strcspn(username, "\n")] = '\0';
            //
            // In questo pseudocodice d'esame la variabile è dichiarata per
            // chiarezza concettuale (rappresenta "l'username estratto") ma
            // la copia è omessa. Il compilatore segnalerà "unused variable"
            // per un esercizio didattico è accettabile.

            send(sock_cli, "PASSWORD?\n", 10, 0);
            // Stessa logica di "USERNAME?\n": inviamo il prompt per la password.
            // "PASSWORD?\n" = 10 byte.

            recv(sock_cli, buf, sizeof(buf), 0);
            // Riceviamo la password nella stessa variabile buf
            // (sovrascriviamo l'username — per questo in produzione
            // si userebbero due buffer separati o si copia prima).

            char password[256];
            // Stesso ragionamento di username: buffer per la password.
            // In produzione: strncpy + rimozione '\n' + azzeramento buf
            // per non lasciare la password in chiaro in memoria
            // (principio "zeroize after use" per dati sensibili).

            // ----------------------------------------------------------
            // PASSO 11 (figlio): connessione TCP al server fedeltà
            //                    (lato ATTIVO — il figlio fa da client)
            // ----------------------------------------------------------
            struct sockaddr_in fed_addr;
            // Struttura per l'indirizzo del server fedeltà (destinazione).
            // Stesso tipo di srv_addr, ma per la connessione USCENTE.

            bzero((char*)&fed_addr, sizeof(fed_addr));
            // Azzeriamo come sempre prima di impostare i campi.

            fed_addr.sin_family = AF_INET;
            // Famiglia IPv4 — stessa motivazione di srv_addr.sin_family.

            fed_addr.sin_port = htons(FEDELTA_PORT);
            // Porta 443 in network byte order.
            // Stessa motivazione di srv_addr.sin_port.

            fed_addr.sin_addr.s_addr = inet_addr(FEDELTA_IP);
            // inet_addr() fa l'OPPOSTO di inet_ntoa():
            //   stringa dotted-decimal → uint32_t in network byte order
            // inet_addr("10.10.10.1") → 0x0101010A (in memoria, big-endian)
            //
            // ⚠️ IMPORTANTE: inet_addr() restituisce il valore GIÀ in
            // network byte order (a differenza dei numeri di porta che
            // vanno convertiti con htons). Quindi NON scriviamo:
            //   fed_addr.sin_addr.s_addr = htonl(inet_addr(FEDELTA_IP)); // SBAGLIATO
            //
            // inet_addr() ritorna INADDR_NONE (= 0xFFFFFFFF) se la
            // stringa non è un indirizzo IPv4 valido.

            int sock_fed = socket(AF_INET, SOCK_STREAM, 0);
            // Creiamo un TERZO socket file descriptor per la connessione
            // verso il server fedeltà. Abbiamo ora tre fd distinti:
            //   sock_srv : il socket di ascolto (chiuso nel figlio al passo 8)
            //   sock_cli : il canale col client che vuole autenticarsi
            //   sock_fed : il canale col server fedeltà per verificare le credenziali
            // Ogni connessione TCP richiede un fd separato.
            // Gli stessi parametri di prima: AF_INET, SOCK_STREAM, 0 → TCP/IPv4.

            connect(sock_fed, (struct sockaddr*)&fed_addr, sizeof(fed_addr));
            // connect() è la chiamata del lato ATTIVO di una connessione TCP.
            // Avvia il three-way handshake verso fed_addr:
            //   → SYN (figlio invia al server fedeltà)
            //   ← SYN+ACK (server fedeltà risponde)
            //   → ACK (figlio conferma; connessione stabilita)
            //
            // connect() è BLOCCANTE: il figlio si ferma finché l'handshake
            // non è completato (o scade il timeout — tipicamente 75 secondi
            // se il server non risponde).
            //
            // Dopo connect(), sock_fed è un socket TCP full-duplex pronto
            // per send()/recv() in entrambe le direzioni.
            //
            // Nota: a differenza del server (che chiama bind+listen+accept),
            // il client (lato attivo) non chiama bind(): il kernel sceglie
            // automaticamente una porta sorgente effimera (> 1024, non in uso)
            // e l'IP sorgente corrispondente all'interfaccia di uscita
            // (bind implicita "per la durata di questa singola connessione",
            // cfr. M4/UD1/L2, §4 — bind implicita UDP/TCP lato client).
            //
            // Il cast (struct sockaddr*) ha la stessa motivazione di bind().

            // ----------------------------------------------------------
            // PASSO 12 (figlio): invia credenziali al server fedeltà
            //                    e ricevi il token
            // ----------------------------------------------------------
            snprintf(buf, sizeof(buf), "AUTH %s %s\n", username, password);
            // snprintf() formatta una stringa e la scrive in buf.
            // Parametri:
            //   buf           : buffer di destinazione (stack del figlio)
            //   sizeof(buf)   : dimensione massima da scrivere, incluso '\0'
            //   "AUTH %s %s\n": formato del "protocollo" verso il server fedeltà
            //   username, password: i valori da inserire nei %s
            //
            // Il risultato in buf sarà es.: "AUTH mario.rossi secret123\n"
            //
            // ⚠️ Usiamo snprintf (NON sprintf) per sicurezza:
            //   sprintf non ha limite di lunghezza e può scrivere OLTRE la fine
            //   di buf (buffer overflow) se username o password fossero molto
            //   lunghi (classica vulnerabilità di sicurezza in C).
            //   snprintf garantisce di non scrivere più di sizeof(buf) byte
            //   (tronca la stringa se necessario).

            send(sock_fed, buf, strlen(buf), 0);
            // Inviamo il messaggio di autenticazione al server fedeltà.
            //
            // Usiamo strlen(buf) (lunghezza effettiva della stringa formattata)
            // e NON sizeof(buf) (che è sempre 4096):
            //   - strlen(buf) = es. 28 per "AUTH mario.rossi secret123\n"
            //   - sizeof(buf) = 4096
            //   Mandare 4096 byte significherebbe inviare 4068 byte di spazzatura
            //   dopo il '\0': spreco di banda e potenziale problema di sicurezza
            //   (i byte successivi al '\0' potrebbero contenere dati precedenti
            //   rimasti in buf, es. parte della password del client precedente).

            recv(sock_fed, buf, sizeof(buf), 0);
            // Riceviamo la risposta del server fedeltà: il TOKEN di autenticazione
            // (o un messaggio di errore se le credenziali sono sbagliate).
            // Il token viene scritto in buf, sovrascrivendo il messaggio AUTH
            // che avevamo appena inviato (buf è riutilizzato).
            // Il formato del token è proprietario del server fedeltà
            // (es. una stringa JWT, un UUID, un codice numerico…).

            close(sock_fed);
            // Chiudiamo la connessione con il server fedeltà: abbiamo
            // ricevuto il token, non ci serve più questa connessione.
            // TCP invierà un FIN al server fedeltà (chiusura ordinata).
            // Il token è già al sicuro in buf[], la chiusura non lo cancella.

            // ----------------------------------------------------------
            // PASSO 13 (figlio): valida il token e rispondi al client
            // ----------------------------------------------------------
            if (!loyalty_check_token(buf)) {
                // loyalty_check_token() è la funzione di libreria che valida
                // il token in buf[]. Restituisce:
                //   valore non-zero (es. 1) → token VALIDO
                //   0                       → token INVALIDO o SCADUTO
                //
                // L'operatore ! inverte il valore:
                //   !0   = 1 (true)  → entriamo qui se token INVALIDO
                //   !non-zero = 0 (false) → andiamo all'else se token VALIDO
                //
                // In questo ramo: autenticazione FALLITA.

                send(sock_cli, "AUTH_FAIL\n", 10, 0);
                // Informiamo il client che le credenziali non sono valide
                // (o il token restituito dal server fedeltà è invalido).
                // Il client dovrebbe mostrare un messaggio di errore e
                // permettere all'utente di riprovare.
                // "AUTH_FAIL\n" = 10 byte.

            } else {
                // Ramo else: token VALIDO → autenticazione RIUSCITA.

                append_ip(CONNECTED_FILE, client_ip);
                // Registriamo l'IP del client nel file CONNECTED.
                // append_ip() apre il file in modalità append ("a"),
                // scrive client_ip + '\n', chiude il file.
                //
                // La modalità append è importante: garantisce che l'IP
                // venga AGGIUNTO in coda e non sovrascriva gli IP degli
                // altri passeggeri già autorizzati.
                //
                // Da questo momento, il "processo separato del proxy"
                // (menzionato nella traccia ma non implementato qui) leggerà
                // CONNECTED_FILE, troverà l'IP del client, e inizierà a
                // eseguire connessioni verso Internet per conto suo
                // (NAT/proxy applicativo).

                send(sock_cli, "AUTH_OK\n", 8, 0);
                // Informiamo il client che l'autenticazione è riuscita
                // e che ora ha accesso alla rete. "AUTH_OK\n" = 8 byte.
            }

            // ----------------------------------------------------------
            // PASSO 14 (figlio): cleanup e terminazione del processo figlio
            // ----------------------------------------------------------
            close(sock_cli);
            // Chiudiamo il socket verso il client: il dialogo di
            // autenticazione è concluso (AUTH_OK o AUTH_FAIL).
            // Il kernel invia un FIN TCP al client (chiusura ordinata).
            // In un proxy "completo" qui non chiuderemmo ma passeremmo
            // a fare da relay tra client e Internet — ma la traccia dice
            // che "un processo separato" se ne occupa.

            exit(0);
            // Il processo figlio termina normalmente.
            // Il kernel esegue la cleanup: chiude tutti i fd ancora aperti
            // (in questo caso nessuno — abbiamo chiuso tutto esplicitamente),
            // libera la memoria, e mette il processo in stato "zombie"
            // finché il padre non chiama wait()/waitpid().
            //
            // In questo pseudocodice d'esame, il padre non chiama wait()
            // e i figli diventano zombie breve periodo, finché init/systemd
            // li adotta e li raccoglie. In produzione: installare un handler
            // SIGCHLD { waitpid(-1, NULL, WNOHANG); } per raccoglierli.

        } // fine blocco figlio (fork() == 0)

        // ----------------------------------------------------------
        // PASSO 15 (PADRE): chiudi la copia di sock_cli e torna ad accept()
        // ----------------------------------------------------------
        close(sock_cli);
        // ⚠️ QUESTA RIGA È FONDAMENTALE — spesso dimenticata nei compiti.
        //
        // Dopo fork(), PADRE e FIGLIO hanno entrambi una copia del fd sock_cli.
        // Il kernel mantiene un "reference count" (contatore di riferimenti)
        // per ogni socket: aumenta di 1 per ogni fork(), diminuisce di 1
        // per ogni close().
        //
        // Se il PADRE non chiamasse close(sock_cli):
        //   - il ref count di sock_cli sarebbe 2 (padre + figlio)
        //   - quando il figlio fa close(sock_cli) + exit(0),
        //     il ref count scende a 1 (solo il padre)
        //   - il kernel NON invia il FIN TCP al client
        //     perché c'è ancora il padre che "tiene aperto" il socket
        //   - il client non riceverebbe mai il segnale di fine connessione
        //   - sock_cli rimarrebbe nel processo padre per tutta la vita
        //     del proxy → RESOURCE LEAK (perdita di file descriptor)
        //     Dopo abbastanza connessioni, il processo esaurirebbe i fd
        //     disponibili e non potrebbe aprire nuovi socket
        //
        // Con questo close():
        //   - il ref count del padre su sock_cli → 0
        //   - (il figlio lo chiuderà per conto suo alla fine)
        //   - il padre "dimentica" sock_cli e può tornare ad accept()
        //     pronto per il prossimo client

    } // fine while(1)

    // Il return di main non viene mai raggiunto (while infinito).
    // Il compilatore può generare un warning "control reaches end of non-void function":
    // in un esercizio didattico è accettabile. In produzione: return 0;
}
