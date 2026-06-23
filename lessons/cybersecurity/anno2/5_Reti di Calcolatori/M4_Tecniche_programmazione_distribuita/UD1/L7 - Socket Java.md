# **Lezione 7: Socket Java**

---

### **1. I due significati del termine "socket"**

Il termine **socket** ha due significati storicamente distinti che convivono nella letteratura:

1. **RFC 793 (1981)**: un socket è la combinazione di un **indirizzo IP** e di un **numero di porta TCP** — cioè il punto di ingresso applicativo di una certa applicazione che comunica usando il protocollo TCP.

2. **Significato più recente**: il termine identifica l'intero **insieme di interfacce di programmazione** (Application Programming Interfaces, API) aggiunte al sistema operativo Unix nella variante BSD (Berkeley Software Distribution), che permettono al programmatore di scrivere applicazioni capaci di comunicare tra di loro usando TCP o UDP.

Entrambi i significati coesistono: si parla di "socket" sia come endpoint logico identificato da IP+porta, sia come libreria di funzioni per la programmazione di rete.

---

### **2. Tipi di socket**

Corrispondono ai due protocolli fondamentali della suite TCP/IP:

| Tipo | Protocollo | Caratteristiche |
|---|---|---|
| **Socket di flusso** (`SOCK_STREAM`) | **TCP** | Connessione affidabile, ordinata, full-duplex; dati trasmessi come flusso continuo di byte |
| **Socket di datagramma** (`SOCK_DGRAM`) | **UDP** | Senza connessione, non affidabile; ogni pacchetto è indipendente e include esplicitamente l'indirizzo destinatario |

Ogni coppia di endpoint di comunicazione è identificata univocamente da una **quadrupla**:

$$(\text{IP}_{client},\ \text{porta}_{client},\ \text{IP}_{server},\ \text{porta}_{server})$$

Questa quadrupla specifica in modo univoco la connessione TCP (o il canale UDP) tra i due partecipanti.

---

### **3. Applicazioni client-server: standard vs. proprietarie**

Esistono due categorie di applicazioni che usano la Socket Library:

**a. Applicazioni basate su standard (RFC)**

Implementano protocolli ufficiali come FTP, HTTP, SMTP. Il client e il server sono sviluppati da parti diverse ma si aspettano di comunicare rispettando le regole dell'RFC di riferimento. Devono usare le **porte well-known** assegnate dall'IANA (es. porta 80 per HTTP, porta 25 per SMTP).

**b. Applicazioni completamente proprietarie**

Client e server sono creati dallo stesso programmatore. Il programmatore ha il **controllo completo** su:
- il tipo e il formato dei messaggi scambiati;
- se usare TCP o UDP come protocollo di trasporto;
- i numeri di porta scelti (tipicamente ≥ 1024 per evitare conflitti con le porte well-known).

---

### **4. Socket di benvenuto e socket di connessione (lato server)**

Nel caso di TCP, il server deve gestire **due socket distinti**:

**Socket di benvenuto** (`welcomeSocket` / `ServerSocket`)
- È in ascolto su una porta nota a priori a tutti i client (es. porta 6789 nell'esempio del corso, oppure 80 per HTTP).
- Rimane **sempre aperto** in attesa di nuove richieste di connessione.
- Serve per completare il **three-way handshake** (sincronia trilaterale) con ogni nuovo client.
- Una volta completato l'handshake, **non è usato per lo scambio dati**: resta libero per accettare altri client.

**Socket di connessione** (`connectionSocket`)
- Viene **creato dinamicamente** da `accept()` ogni volta che un client completa il three-way handshake.
- È dedicato allo scambio di dati con quel singolo client specifico.
- Ha una propria quadrupla completa: include l'IP e la porta del client, ottenute durante l'handshake.
- Quando la sessione termina, viene chiuso.

**Lato client**: basta un solo socket (`clientSocket`), che viene usato sia per avviare la sincronia trilaterale (attraverso `connect()`) sia per lo scambio dati.

> 📌 La distinzione welcome socket / connection socket è la realizzazione concreta del concetto di "socket di connessione vs socket di servizio" discusso in L2 e L3. In Java i nomi diventano `ServerSocket` e `Socket`.

---

### **5. Le chiamate socket fondamentali**

Le chiamate di sistema della Socket Library, applicabili sia in C che — con astrazione orientata agli oggetti — in Java:

| Chiamata | Lato | Scopo |
|---|---|---|
| `socket()` | client + server | Crea un socket (riserva risorse nel SO) |
| `bind()` | client + server | Associa il socket a un indirizzo IP e una porta locale |
| `listen()` | server | Mette il socket in ascolto per richieste entranti (crea il welcome socket) |
| `connect()` | client | Avvia il three-way handshake verso il server |
| `accept()` | server | Accetta la connessione, crea il connection socket |
| `write()` / `send()` | entrambi | Scrive dati sul socket |
| `read()` / `recv()` | entrambi | Legge dati dal socket |
| `sendto()` | client UDP | Invia un datagramma UDP specificando l'indirizzo destinatario |
| `recvfrom()` | server UDP | Riceve un datagramma UDP estraendo IP+porta del mittente |
| `close()` | entrambi | Chiude il socket e avvia la chiusura della connessione TCP (FIN) |

---

### **6. Flusso completo della comunicazione TCP**

![](imgs/Pasted%20image%2020260623155159.png)

Nell'immagine qui sopra si può vedere in forma grafica il processo di attivazione di una comunicazione socket di tipo TCP.
In primo luogo il socket client inizia la richiesta di connessione sul socket di benvenuto del server: ci esegue la sincronia trilaterale, cioè il Three Way Handshake TCP; una volta andato a buon fine, si crea il socket di connessione su cui viene attivato un flusso bidirezionale full duplex di byte tra client e server.
Questo costituisce la connessione; il socket di benvenuto rimane libero per le richieste di connessione da parte di altri client.

Come programmatori noi possiamo creare all'interno di un nostro programma entrambi i socket:

![](imgs/Pasted%20image%2020260623155527.png)

> 💡 Chi sviluppa l'applicativo può configurare alcuni parametri TCP dall'interno del programma: tipicamente le **dimensioni massime dei buffer** (usati per la retransmission list) e le **dimensioni dei segmenti** iniziali trasmessi lungo la connessione. Il programma controlla la parte applicativa; il sistema operativo gestisce il TCP sottostante con i suoi buffer e variabili di stato.

---

Il diagramma del professore mostra il flusso completo lato server e lato client:

![](imgs/Pasted%20image%2020260623155621.png)

**Lato server** (colonna destra):
1. `socket()` → crea il socket
2. `bind()` → associa IP + porta di ascolto (porta well-known o nota solo al client)
3. `listen()` → mette il socket in attesa di connessioni (welcome socket)
4. `accept()` → bloccante; quando arriva un client, crea il connection socket
5. `recv()` → legge la richiesta del client sul connection socket
6. `send()` → scrive la risposta sul connection socket
7. (eventuale ciclo di più richieste)
8. `close()` → chiude il connection socket; il server torna in attesa su `accept()`

**Lato client** (colonna sinistra):
1. `socket()` → crea il socket
2. `bind()` → (spesso implicita) associa IP + porta sorgente
3. `connect()` → avvia il three-way handshake verso il welcome socket del server
4. `send()` → invia i dati sul connection socket creato da `connect()`
5. `recv()` → legge la risposta del server
6. (eventuale ciclo di più richieste)
7. `close()` → chiude il socket; il flag FIN viene inviato al server

---

### **7. Esempio: applicazione TCP client/server con capitalizzazione**

L'esempio di riferimento del corso è un'applicazione client/server in cui:

1. Il **client** legge una riga di testo digitata dall'utente sulla tastiera (flusso `inFromUser`).
2. La invia al server attraverso il socket usando il flusso `outToServer`.
3. Il **server** riceve la riga dal flusso `inFromClient`, la converte in **maiuscolo** con `toUpperCase()`.
4. La rimanda al client attraverso il flusso `outToClient`.
5. Il **client** riceve la risposta (`inFromServer`) e la stampa a video.

![](imgs/Pasted%20image%2020260623155647.png)

Questo esempio è volutamente semplice: l'elaborazione è banale (maiuscolo), ma la struttura è quella di qualunque applicazione client/server reale — lettura input → invio → elaborazione → risposta → stampa output.

---

### **8. Socket TCP in Java: le classi del package `java.net`**

Prima di vederne il codice, possiamo apprezzarne uno pseudocodice più intuitivo: 

Java semplifica la programmazione socket tramite il package **`java.net`**, che astrae le chiamate di sistema C in classi orientate agli oggetti.

#### **8.1. Classe `Socket` (lato client)**

Rappresenta l'estremità client di una connessione TCP. Il costruttore apre direttamente la connessione TCP (esegue internamente `socket()` + `connect()`):

```java
Socket clientSocket = new Socket("hostname", 6789);
```

Al costruttore si passa il nome host (o IP) e la porta del server. Java risolve il nome host attraverso il servizio DNS (`InetAddress.getByName()`) analogamente alla funzione C `gethostbyname()`.

Una volta creato il socket, si ottengono i flussi di I/O:

```java
DataOutputStream outToServer =
    new DataOutputStream(clientSocket.getOutputStream());

BufferedReader inFromServer =
    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
```

Per leggere dalla tastiera si crea un flusso separato:

```java
BufferedReader inFromUser =
    new BufferedReader(new InputStreamReader(System.in));
```

#### **8.2. Classe `ServerSocket` (lato server)**

Implementa il **welcome socket**: apre la porta di ascolto e attende connessioni.

```java
ServerSocket welcomeSocket = new ServerSocket(6789);
```

Per ogni client che si connette, `accept()` crea il **connection socket** dedicato:

```java
Socket connectionSocket = welcomeSocket.accept();
```

`accept()` è **bloccante**: il server rimane fermo finché un client non arriva.

Sul connection socket si creano i flussi:

```java
BufferedReader inFromClient =
    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

DataOutputStream outToClient =
    new DataOutputStream(connectionSocket.getOutputStream());
```

> 📌 La distinzione `welcomeSocket` (di tipo `ServerSocket`) / `connectionSocket` (di tipo `Socket`) è la trasposizione diretta in Java della distinzione C tra socket di benvenuto e socket di connessione.

---

### **9. Codice completo: TCPClient.java**

```java
import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;

        // Flusso di lettura dalla tastiera
        BufferedReader inFromUser =
            new BufferedReader(new InputStreamReader(System.in));

        // Crea il socket client e si connette al server sulla porta 6789
        Socket clientSocket = new Socket("hostname", 6789);

        // Flusso di uscita verso il server (scrittura sul socket)
        DataOutputStream outToServer =
            new DataOutputStream(clientSocket.getOutputStream());

        // Flusso di ingresso dal server (lettura dal socket)
        BufferedReader inFromServer =
            new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Legge la frase digitata dall'utente
        sentence = inFromUser.readLine();

        // Invia la frase al server (con newline finale come delimitatore di riga)
        outToServer.writeBytes(sentence + '\n');

        // Legge la risposta del server (frase in maiuscolo)
        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: " + modifiedSentence);

        // Chiude il socket: avvia il processo di chiusura TCP (FIN)
        clientSocket.close();
    }
}
```

**Punti chiave del client:**
- `new Socket("hostname", 6789)`: esegue internamente socket() + connect(); al termine dell'istruzione il three-way handshake è già completato.
- `writeBytes(sentence + '\n')`: il `'\n'` è il delimitatore di fine riga che il server usa con `readLine()` per capire quando la riga è finita.
- `readLine()` su `inFromServer`: bloccante — il client aspetta la risposta del server prima di proseguire.
- `clientSocket.close()`: rilascia il file descriptor e avvia la chiusura TCP.

---

### **10. Codice completo: TCPServer.java**

```java
import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;

        // Crea il welcome socket sulla porta 6789
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while (true) {
            // accept() è bloccante: aspetta una richiesta di connessione
            // Quando arriva, crea il connection socket
            Socket connectionSocket = welcomeSocket.accept();

            // Flusso di ingresso dal client (lettura dal connection socket)
            BufferedReader inFromClient =
                new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            // Flusso di uscita verso il client (scrittura sul connection socket)
            DataOutputStream outToClient =
                new DataOutputStream(connectionSocket.getOutputStream());

            // Legge la riga inviata dal client
            clientSentence = inFromClient.readLine();

            // Elaborazione: converte in maiuscolo e aggiunge newline
            capitalizedSentence = clientSentence.toUpperCase() + '\n';

            // Invia la risposta al client
            outToClient.writeBytes(capitalizedSentence);

        } // torna ad accept() per servire il prossimo client
    }
}
```

**Punti chiave del server:**
- `new ServerSocket(6789)`: crea il welcome socket sulla porta 6789; corrisponde a socket() + bind() + listen() in C.
- `welcomeSocket.accept()`: bloccante; crea un nuovo `Socket` (connection socket) per ogni client che completa il three-way handshake. Il welcome socket rimane aperto per altri client.
- `while(true)`: il server non si ferma mai — dopo aver servito un client (chiudendo il connection socket), torna subito in `accept()` per il successivo.
- Il welcome socket **non viene mai chiuso** nel corpo del loop (solo all'uscita del programma).

> ✅ Confronto C vs Java: in C si chiamano esplicitamente `socket()`, `bind()`, `listen()`, `accept()`. In Java `new ServerSocket(port)` racchiude le prime tre; `accept()` corrisponde esattamente all'omonima chiamata C.

---

### **11. Socket UDP in Java**

#### **11.1. Caratteristiche del servizio UDP**

A differenza di TCP:
- **Nessun three-way handshake**: non c'è alcun welcome socket sul server; il server legge direttamente sul suo socket di ascolto.
- **Nessun flusso**: ogni `send` invia un pacchetto indipendente; ogni `receive` riceve un pacchetto indipendente.
- **Nessuna garanzia di consegna né di ordinamento**: i pacchetti possono perdersi o arrivare fuori ordine.
- **Overhead minimo**: adatto per applicazioni real-time (streaming, DNS, gaming) dove la latenza conta più dell'affidabilità.

Dal punto di vista della programmazione:
- Il **mittente** costruisce un pacchetto (`DatagramPacket`) contenente i dati + IP destinatario + porta destinataria, e lo invia.
- Il **destinatario** riceve il pacchetto e ne **estrae manualmente** i dati e l'indirizzo del mittente.

Anche qui mostriamo prima uno pseudocodice molto intuitivo:

![](imgs/Pasted%20image%2020260623160100.png)

Qui sotto vediamo il client Java usando UDP, poi tra poco lo andremo a codificare per bene...

![](imgs/Pasted%20image%2020260623160248.png)

#### **11.2. Le classi Java per UDP**

**`DatagramSocket`**: gestisce un socket UDP per inviare e ricevere datagrammi.

```java
DatagramSocket clientSocket = new DatagramSocket();   // client: porta assegnata dal SO
DatagramSocket serverSocket = new DatagramSocket(9876); // server: porta fissa nota al client
```

Metodi principali:
- `send(DatagramPacket p)` → invia il pacchetto
- `receive(DatagramPacket p)` → riceve un pacchetto (bloccante)
- `close()` → chiude il socket

**`DatagramPacket`**: contiene i dati e l'indirizzo IP+porta del destinatario (in invio) o del mittente (in ricezione).

```java
// Per l'invio: specificare buffer, lunghezza, IP destinatario, porta
DatagramPacket sendPacket =
    new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

// Per la ricezione: solo buffer e lunghezza (IP+porta mittente vengono riempiti dal SO)
DatagramPacket receivePacket =
    new DatagramPacket(receiveData, receiveData.length);
```

> 📌 La `DatagramPacket` di ricezione è analoga alla struttura `name` di `recvfrom()` in C: viene passata vuota e il sistema operativo la riempie con l'indirizzo IP e la porta del mittente al completamento della ricezione.

---

### **12. Codice completo: UDPClient.java**

```java
import java.io.*;
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        // Flusso di lettura dalla tastiera
        BufferedReader inFromUser =
            new BufferedReader(new InputStreamReader(System.in));

        // Crea il socket UDP client (porta assegnata dal SO automaticamente)
        DatagramSocket clientSocket = new DatagramSocket();

        // Risolve il nome host → indirizzo IP (chiama il resolver DNS, come gethostbyname() in C)
        InetAddress IPAddress = InetAddress.getByName("hostname");

        byte[] sendData    = new byte[1024];
        byte[] receiveData = new byte[1024];

        // Legge la frase dall'utente
        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();

        // Costruisce il datagramma: dati + IP destinatario + porta destinataria
        DatagramPacket sendPacket =
            new DatagramPacket(sendData, sendData.length, IPAddress, 9876);

        // Invia il datagramma al server
        clientSocket.send(sendPacket);

        // Prepara il buffer di ricezione e attende la risposta (bloccante)
        DatagramPacket receivePacket =
            new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        // Estrae i dati ricevuti e li stampa
        String modifiedSentence = new String(receivePacket.getData());
        System.out.println("FROM SERVER: " + modifiedSentence);

        // Chiude il socket
        clientSocket.close();
    }
}
```

**Punti chiave del client UDP:**
- `new DatagramSocket()` senza argomenti: porta sorgente assegnata automaticamente dal SO (come `bind()` implicita in C).
- `InetAddress.getByName("hostname")`: accede al resolver DNS locale — equivalente a `gethostbyname()` in C.
- Il `DatagramPacket` di invio contiene tutta la quadrupla: IP+porta destinatario + IP+porta sorgente (questi ultimi gestiti dal SO).
- Non c'è `connect()`: ogni pacchetto specifica autonomamente la destinazione.

---

### **13. Codice completo: UDPServer.java**

```java
import java.io.*;
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        // Crea il server socket UDP sulla porta 9876 (nota al client)
        DatagramSocket serverSocket = new DatagramSocket(9876);

        byte[] receiveData = new byte[1024];
        byte[] sendData    = new byte[1024];

        while (true) {
            // Prepara il buffer di ricezione e aspetta un datagramma (bloccante)
            DatagramPacket receivePacket =
                new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            // Estrae i dati dal pacchetto ricevuto
            String sentence = new String(receivePacket.getData());

            // Estrae IP e porta del client mittente dal pacchetto ricevuto
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            // Elaborazione: converte in maiuscolo
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();

            // Costruisce il pacchetto di risposta verso il client (IP+porta estratti sopra)
            DatagramPacket sendPacket =
                new DatagramPacket(sendData, sendData.length, IPAddress, port);

            // Invia la risposta
            serverSocket.send(sendPacket);

        } // torna in receive per il prossimo datagramma
    }
}
```

**Punti chiave del server UDP:**
- `new DatagramSocket(9876)`: porta fissa nota al client — non serve welcome socket separato perché UDP è connectionless.
- `receivePacket.getAddress()` e `receivePacket.getPort()`: il server **estrae dal pacchetto ricevuto** l'IP e la porta del client mittente. In C questa informazione sarebbe stata riempita da `recvfrom()` nella struttura `name`.
- Il server usa **lo stesso socket** per ricevere e per rispondere — non viene creato nessun socket di connessione separato.
- `while(true)`: ciclo infinito di ricezione, identico al server TCP.

---

### **14. Confronto TCP vs UDP in Java**

| Aspetto | TCP (`Socket` / `ServerSocket`) | UDP (`DatagramSocket` / `DatagramPacket`) |
|---|---|---|
| Welcome socket | `ServerSocket` separato + `accept()` | Non esiste; si legge direttamente |
| Connessione | Three-way handshake (implicito nel costruttore `Socket`) | Nessuna |
| Flusso dati | Stream bidirezionale continuo | Pacchetti indipendenti |
| Affidabilità | Garantita (TCP ritrasmette) | Non garantita |
| Ordinamento | Garantito | Non garantito |
| Overhead | Maggiore (handshake, ACK, ritrasmissioni) | Minore |
| Estrazione IP mittente | Automatica (connection socket già conosce il client) | Manuale: `receivePacket.getAddress()` / `getPort()` |
| Uso tipico | HTTP, FTP, SMTP, chat | DNS, streaming, gaming, VoIP |

---

### **15. Conclusione**

Il package `java.net` offre un'astrazione orientata agli oggetti della Socket Library BSD, traducendo le chiamate C in costruttori e metodi:

- `new ServerSocket(port)` → `socket()` + `bind()` + `listen()`
- `welcomeSocket.accept()` → `accept()`
- `new Socket(host, port)` → `socket()` + `connect()`
- `getInputStream()` / `getOutputStream()` → `recv()` / `send()`
- `socket.close()` → `close()`

Le classi `DatagramSocket` e `DatagramPacket` implementano l'equivalente UDP, rispecchiando la logica di `sendto()` / `recvfrom()` in C.

> ✅ Con TCP Java si apre un canale bidirezionale affidabile (stream); con UDP Java si gestiscono pacchetti indipendenti con overhead minimo. La scelta dipende dalle esigenze dell'applicazione: affidabilità e ordine con TCP, velocità e leggerezza con UDP.
