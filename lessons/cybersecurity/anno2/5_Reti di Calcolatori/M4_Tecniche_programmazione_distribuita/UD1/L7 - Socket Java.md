## **Lezione 7: Socket Java**

### **1. Cos’è un socket**

Un **socket** è la combinazione di un **indirizzo IP** e di un **numero di porta**.  
In termini più semplici, è il **punto terminale di una connessione di rete**.

Nel mondo Java (come nella specifica originaria **RFC 793** per TCP), il termine “socket” indica:

1. l’interfaccia per **creare e gestire connessioni TCP/IP**;
    
2. la **libreria di funzioni** (API Berkeley) che permettono a due applicazioni di comunicare.
    

#### **Tipi di socket**

- **Socket di flusso** → connessioni affidabili, orientate al collegamento, basate su **TCP**.
    
- **Socket di datagram** → connessioni non affidabili, senza collegamento, basate su **UDP**.
    

Ogni connessione TCP è definita univocamente da una **quadrupla**:

$$(\text{IP}_{client}, \text{porta}_{client}, \text{IP}_{server}, \text{porta}_{server})$$

Questa quadrupla rappresenta i **due punti terminali** della comunicazione _full-duplex_ (cioè bidirezionale).

---

### **2. Applicazioni client-server**

I programmi che usano socket possono essere di due tipi:

#### **a. Applicazioni basate su standard (RFC)**

- Implementano protocolli ufficiali come **FTP**, **HTTP**, **SMTP**, ecc.
    
- Devono rispettare le **regole** imposte dalle RFC corrispondenti.
    
- Devono usare le **porte ufficiali** assegnate dall’**IANA (Internet Assigned Numbers Authority)**.
    

#### **b. Applicazioni proprietarie**

- Create da uno sviluppatore o un team, che scrive **sia il client che il server**.
    
- Non devono rispettare uno standard formale.
    
- Possono usare **porte libere (≥ 1024)** per evitare conflitti con i protocolli ufficiali.
    

---

### **3. Socket lato server e lato client**

|Ruolo|Tipo di socket|Funzione|
|---|---|---|
|**Server**|_Socket di benvenuto_|Resta in ascolto per accettare nuove connessioni.|
|**Server**|_Socket di connessione_|Creato dopo l’accettazione di un client; gestisce la comunicazione con esso.|
|**Client**|_Socket client_|Inizia la connessione con il server, specificando IP e porta.|

> Quando un client crea un socket TCP, viene automaticamente avviato il **three-way handshake**, la sincronizzazione a tre vie che stabilisce la connessione.

---

### **4. Chiamate socket fondamentali**

#### **Operazioni di base**

|Funzione|Descrizione|
|---|---|
|`socket()`|Crea un nuovo socket.|
|`bind()`|Associa il socket a un indirizzo IP e a una porta locali.|
|`listen()`|Mette il socket in ascolto per nuove connessioni.|
|`connect()`|Avvia la connessione con un altro socket remoto.|
|`accept()`|Accetta una connessione in arrivo (server).|

#### **Operazioni di I/O**

|Funzione|Descrizione|
|---|---|
|`write()`|Scrive dati sul socket.|
|`read()`|Legge dati dal socket.|
|`sendto()`|Invia un datagram (UDP) a un altro socket.|
|`recvfrom()`|Riceve un datagram (UDP) da un socket remoto.|
|`close()`|Chiude la connessione o il socket.|

---

### **5. Tipi di socket e uso pratico**

In TCP, chi scrive l’applicazione può **configurare parametri specifici**, come:

- la **dimensione del buffer** di invio e ricezione;
    
- la **lunghezza massima dei segmenti**.
    

In questo modo è possibile ottimizzare il comportamento della comunicazione in base al tipo di traffico e all’infrastruttura di rete.

---

### **6. Esempio di applicazione TCP client/server**

#### **a. Funzionamento**

1. Il **client** legge una riga di testo da tastiera (stream `inFromUser`).
    
2. La invia al **server** attraverso il socket (`outToServer`).
    
3. Il **server** riceve la stringa (`inFromClient`), la converte in maiuscolo,  
    e la rispedisce al client.
    
4. Il **client** riceve il risultato (`inFromServer`) e lo stampa a video.
    

#### **b. Logica della comunicazione**

```plaintext
CLIENT → invia messaggio → SERVER
SERVER → elabora → risponde → CLIENT
```

Questo semplice scambio mostra la **bidirezionalità** del canale TCP: entrambi possono leggere e scrivere in modo sincrono.

---

### **7. Socket TCP in Java**

Java fornisce una libreria nativa, il **package `java.net`**, che semplifica la programmazione socket-oriented.

#### **a. Classe `Socket` (client)**

Rappresenta l’estremità _client_ di una connessione TCP.

```java
Socket(String host, int port)
```

##### **Metodi principali**

- `InputStream getInputStream()` → per ricevere dati.
    
- `OutputStream getOutputStream()` → per inviare dati.
    
- `close()` → chiude la connessione.
    

Esempio:

```java
Socket s = new Socket("example.com", 80);
OutputStream out = s.getOutputStream();
InputStream in = s.getInputStream();
```

---

#### **b. Classe `ServerSocket` (server)**

Gestisce le connessioni in arrivo.

```java
ServerSocket(int port)
```

##### **Metodi principali**

- `Socket accept()` → attende (bloccante) una richiesta e crea un socket dedicato.
    
- `close()` → chiude il socket di ascolto.
    

Esempio:

```java
ServerSocket ss = new ServerSocket(6789);
Socket s = ss.accept(); // attende la connessione di un client
```

> ⚠️ Il metodo `accept()` è **bloccante**: il server resta fermo finché non riceve una richiesta.

---

### **8. Programmazione con UDP**

#### **Caratteristiche**

- Protocollo **senza connessione** e **non affidabile**.
    
- Nessuna fase di _handshaking_.
    
- Nessun flusso continuo: ogni pacchetto (datagram) è indipendente.
    
- I pacchetti possono arrivare **fuori ordine** o **perdersi**.
    

#### **Funzionamento**

- Il mittente costruisce un **pacchetto UDP**, includendo:
    
    - l’indirizzo IP di destinazione;
        
    - il numero di porta;
        
    - i dati da inviare.
        
- Il destinatario riceve il pacchetto e **estrae i dati manualmente**.
    

---

### **9. Socket UDP in Java**

Anche in questo caso, il package `java.net` fornisce classi dedicate.

#### **a. Classe `DatagramSocket`**

Gestisce i socket UDP per inviare e ricevere datagrammi.

```java
DatagramSocket(int port)
```

##### **Metodi principali**

- `void send(DatagramPacket p)` → invia un datagram.
    
- `void receive(DatagramPacket p)` → riceve un datagram.
    
- `void close()` → chiude il socket.
    

#### **b. Classe `DatagramPacket`**

Contiene i dati e l’indirizzo del destinatario o mittente.

```java
DatagramPacket(byte[] buf, int length, InetAddress address, int port)
```

---

### **10. Differenze tra TCP e UDP in Java**

|Aspetto|TCP (`Socket` / `ServerSocket`)|UDP (`DatagramSocket`)|
|---|---|---|
|**Connessione**|Orientata alla connessione|Senza connessione|
|**Affidabilità**|Garantita|Non garantita|
|**Ordine dei dati**|Garantito|Non garantito|
|**Flusso continuo**|Sì|No|
|**Uso tipico**|Applicazioni con dialogo persistente (HTTP, FTP, chat)|Applicazioni rapide e leggere (DNS, streaming, giochi)|

---

### **11. Conclusione**

Le **socket in Java** forniscono un’interfaccia potente e portabile per la **programmazione distribuita**.  
Con poche righe di codice si può creare:

- un **server TCP**, che accetta e gestisce più client;
    
- o un **client UDP**, che invia datagrammi a un server remoto.
    

Capire come funzionano queste classi significa padroneggiare **la base della comunicazione tra processi su Internet**, cioè la capacità di far “parlare” i programmi tra loro attraverso la rete.

> In breve: con le socket Java, il linguaggio diventa **una finestra diretta sulla rete**, capace di costruire server, client e servizi web reali.