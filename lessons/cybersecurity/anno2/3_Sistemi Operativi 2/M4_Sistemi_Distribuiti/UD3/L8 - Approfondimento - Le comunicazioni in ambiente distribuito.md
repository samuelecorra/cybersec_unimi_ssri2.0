# **M4 UD3 Lezione 8 - Approfondimento: Le comunicazioni in ambiente distribuito**

### **1. Introduzione generale**

La **comunicazione in ambiente distribuito** è l’insieme delle tecniche che consentono a processi e applicazioni residenti su **macchine diverse** di scambiare informazioni, cooperare ed eseguire elaborazioni coordinate.  
Le principali tecnologie sono:

- **Socket** (TCP e UDP)
    
- **Invocazione di metodi remoti (RMI)**
    
- **CORBA** (Common Object Request Broker Architecture)
    
- Sistemi di **registrazione e mediazione degli oggetti distribuiti**

Questi meccanismi permettono lo sviluppo di **applicazioni client-server eterogenee**, scalabili e interoperabili.

---

## **2. Socket**

### **2.1 Concetto base**

Un **socket** è un punto di comunicazione identificato da:

$$
(IP, \text{porta})  
$$

Una connessione tra due processi in rete è definita da una coppia di socket.  
Ogni lato della comunicazione (client e server) possiede il proprio socket.

### **2.2 Esempio: Echo server**

Il **server dell’eco** riceve un messaggio da un client e lo restituisce identico come risposta.  
Il flusso tipico è:

```java
while (true) {
    Socket client = sock.accept();
    // Riceve testo dal client e lo rimanda indietro
}
```

In un server a **singolo thread**, solo un client alla volta può essere servito; per renderlo **reattivo**, si usano thread multipli:

```java
while (true) {
    Socket client = sock.accept();
    EchoThread t = new EchoThread(client);
    t.start();
}
```

Ogni connessione è gestita da un thread dedicato.

---

### **2.3 Scalabilità e I/O non bloccante**

I moderni server devono gestire **centinaia o migliaia di connessioni** contemporanee, perciò l’approccio multithread tradizionale **non scala bene**:  
ogni thread occupa memoria e tempo CPU, anche se il client è inattivo.

La soluzione è l’uso dell’**I/O non bloccante (non-blocking I/O)**, dove le operazioni di input/output:

- non sospendono il processo in attesa dei dati;
    
- restituiscono immediatamente il controllo;
    
- sfruttano un **selettore** che segnala quando un canale è pronto.

---

### **2.4 Componenti del modello Java NIO**

1. **Charset**: insieme di caratteri e codifica (es. `ISO-8859-1`, `UTF-8`).
    
2. **Buffer**: area di memoria lineare che conserva i dati letti o da scrivere.
    
3. **Canale (Channel)**: condotto logico per operazioni di I/O (file, socket, rete).
    
4. **Selettore (Selector)**: oggetto che controlla molti canali contemporaneamente e notifica quali sono pronti per lettura o scrittura.

---

### **2.5 Esempio: Server dell’eco non bloccante**

Il server apre un `ServerSocketChannel`, lo configura come **non bloccante**, e registra il canale presso un `Selector`:

```java
server = ServerSocketChannel.open();
server.configureBlocking(false);
Selector selector = Selector.open();
server.register(selector, SelectionKey.OP_ACCEPT);
```

Durante l’esecuzione:

1. Il `Selector` attende che uno dei canali registrati sia pronto (`select()`).
    
2. Se arriva una richiesta di connessione (`isAcceptable()`):
    
    - accetta la connessione,
        
    - registra il nuovo canale per la lettura (`OP_READ`).
    
3. Se sono disponibili dati (`isReadable()`):
    
    - legge nel buffer,
        
    - decodifica e rimanda il messaggio al client.

Grazie a questa logica:

- il server può gestire **migliaia di connessioni attive**;
    
- le operazioni I/O vengono eseguite **solo quando pronte**;
    
- non è necessario un thread per ogni client.

---

## **3. Socket UDP**

### **3.1 Differenze rispetto a TCP**

|Aspetto|TCP|UDP|
|---|---|---|
|Tipo di connessione|Orientato alla connessione|Senza connessione|
|Affidabilità|Garantita (ack e ritrasmissione)|Non garantita|
|Flusso e congestione|Controllati|Non gestiti|
|Velocità|Più lenta|Più veloce|
|Uso tipico|HTTP, FTP, SMTP|Streaming, giochi online, VoIP|

UDP non stabilisce un canale stabile; ogni pacchetto (Datagram) è indipendente e può perdersi o arrivare fuori ordine.

---

### **3.2 Esempio: Client/Server della data (UDP)**

#### **Client**

- Crea un `DatagramSocket`.
    
- Invia un pacchetto vuoto al server.
    
- Attende una risposta con la data corrente.
    
- Se entro 5 secondi non arriva risposta (`setSoTimeout(5000)`), genera eccezione.

#### **Server**

- Ascolta sulla porta `6013`.
    
- Riceve i pacchetti in arrivo.
    
- Invia indietro un pacchetto con la data e ora corrente.

Questo modello è **stateless**, adatto a richieste brevi e non critiche.

---

## **4. Invocazione di Metodi Remoti (RMI)**

### **4.1 Concetto**

L’**RMI (Remote Method Invocation)** consente a un programma Java di invocare un metodo su un **oggetto remoto**, come se fosse locale.

Ogni oggetto remoto:

- risiede su una **JVM differente**, anche su un altro host;
    
- è accessibile tramite **stub e skeleton**:
    
    - **stub**: proxy locale che rappresenta l’oggetto remoto;
        
    - **skeleton**: controparte sul server che riceve le invocazioni.

---

### **4.2 Esempio: Problema produttore-consumatore distribuito**

#### **1. Interfaccia remota**

L’interfaccia estende `java.rmi.Remote`:

```java
public interface RemoteChannel extends Remote {
    void send(Object item) throws RemoteException;
    Object receive() throws RemoteException;
}
```

#### **2. Implementazione dell’oggetto remoto**

La classe `MessageQueue` implementa l’interfaccia e gestisce una `Vector` come buffer:

```java
public class MessageQueue extends UnicastRemoteObject implements RemoteChannel {
    private Vector queue = new Vector();

    public synchronized void send(Object item) { queue.addElement(item); }
    public synchronized Object receive() {
        return queue.isEmpty() ? null : queue.remove(0);
    }
}
```

#### **3. Registrazione nel registro RMI**

Sul server:

```java
Naming.rebind("MessageQueue", new MessageQueue());
```

#### **4. Accesso dal client**

Il client ottiene un riferimento all’oggetto remoto:

```java
RemoteChannel mailbox = (RemoteChannel)
    Naming.lookup("rmi://127.0.0.1/MessageQueue");
```

e crea thread **Producer** e **Consumer** che invocano `send()` e `receive()` in remoto.

---

### **4.3 Esecuzione passo-passo**

1. **Compilazione** di tutte le classi.
    
2. **Generazione stub/skeleton** con:
    
    ```
    rmic MessageQueue
    ```
    
3. **Avvio registro RMI**:
    
    ```
    start rmiregistry
    ```
    
4. **Esecuzione server**:
    
    ```
    java MessageQueue
    ```
    
5. **Esecuzione client**:
    
    ```
    java Factory
    ```

---

## **5. CORBA**

### **5.1 Cos’è**

**CORBA (Common Object Request Broker Architecture)** è un middleware che consente a programmi scritti in linguaggi diversi (Java, C++, COBOL, Ada…) di comunicare.

CORBA utilizza:

- **IDL (Interface Definition Language)** per definire le interfacce in modo indipendente dal linguaggio;
    
- **ORB (Object Request Broker)** come intermediario che instrada le richieste;
    
- **IIOP (Internet Inter-ORB Protocol)** come protocollo standard per la comunicazione.

### **5.2 Funzionamento**

1. Il client chiama un metodo su un oggetto remoto tramite **stub**.
    
2. L’**ORB client** invia la richiesta all’**ORB server**.
    
3. Lo **skeleton** invoca il metodo reale sull’oggetto.
    
4. Il valore di ritorno viaggia nel percorso inverso.

### **5.3 Differenze rispetto a RMI**

|Aspetto|RMI|CORBA|
|---|---|---|
|Linguaggio|Solo Java|Multilinguaggio|
|Middleware|Integrato nella JVM|Separato (ORB)|
|Definizione interfacce|Interfacce Java|IDL|
|Comunicazione|Java-to-Java|Eterogenea|
|Portabilità|Limitata|Alta|

---

## **6. Registrazione degli oggetti distribuiti**

Entrambi i sistemi, RMI e CORBA, usano un **servizio di registrazione**:

- **RMI:** `rmiregistry` + metodi `Naming.rebind()` e `Naming.lookup()`.
    
- **CORBA:** il registro è gestito dall’**ORB server**, che associa i nomi agli oggetti.

Questo servizio consente ai client di **scoprire e invocare dinamicamente** oggetti remoti.

---

## **7. Sintesi generale**

|Tecnica|Scopo principale|Livello di astrazione|Linguaggio|Affidabilità|
|---|---|---|---|---|
|**Socket TCP/UDP**|Comunicazione diretta tra processi|Basso|Qualsiasi|Dipende dal protocollo|
|**RMI**|Invocazione di metodi su oggetti remoti Java|Medio|Solo Java|Alta|
|**CORBA**|Interoperabilità tra linguaggi diversi|Alto|Multi|Alta|

---

## **8. Conclusione**

Le **comunicazioni distribuite** sono il cuore dei sistemi moderni.  
Dal semplice scambio di messaggi ai paradigmi orientati agli oggetti (RMI, CORBA), ogni tecnologia si colloca su un diverso **livello di astrazione** e offre un diverso compromesso tra:

- **efficienza**
    
- **scalabilità**
    
- **portabilità**
    
- **tolleranza ai guasti**

In sintesi:

> L’obiettivo ultimo è rendere **trasparente** la distanza fisica tra processi e risorse, permettendo all’utente e al programmatore di percepire il sistema distribuito come un’unica entità coerente.