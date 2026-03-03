# **M3 UD4 Lezione 7 - Comunicazione con socket**

### **1. Introduzione**

Concludiamo l’unità dedicata alla **comunicazione tra processi** affrontando il meccanismo più potente e flessibile di tutti: la **comunicazione tramite socket**.  
I **socket** estendono il modello delle **pipe** a un contesto **distribuito**, consentendo a processi che risiedono su **macchine diverse** di scambiarsi dati attraverso la **rete**.

Possiamo quindi considerarli come una **generalizzazione in rete** delle pipe locali, utilizzate in precedenza.  
Il loro scopo è rendere possibile la **comunicazione bidirezionale** e **asincrona** tra processi, sia **sulla stessa macchina** sia **su nodi remoti** collegati via TCP/IP.

---
### **2. Modello della comunicazione mediante socket**

La comunicazione con socket si basa sul paradigma **client–server**:

$$  
\text{Client} \xleftrightarrow[\text{porta}]{\text{socket}} \text{Server}  
$$

#### **2.1. Architettura client–server**

$$  
\begin{cases}  
\textbf{Client:}~ & \text{inizia la comunicazione e invia richieste a una porta specifica.} \\\\  
\textbf{Server:}~ & \text{rimane in ascolto su una porta e risponde alle richieste.}  
\end{cases}  
$$

Ogni connessione tra client e server è identificata da due elementi:

- **indirizzo IP** della macchina ospite;
    
- **numero di porta** che individua il servizio specifico.

---
#### **2.2. Esempi di porte note**

$$  
\begin{cases}  
\text{Telnet:}~ & 23 \\\\  
\text{FTP:}~ & 21 \\\\  
\text{HTTP (Web):}~ & 80 \\\\  
\text{SMTP (Posta):}~ & 25  
\end{cases}  
$$

Le porte da `0` a `1024` sono considerate **porte riservate o note** (_well-known ports_),  
mentre le successive vengono assegnate dinamicamente per connessioni temporanee.

---
### **3. Architettura del socket**

Un socket è un **endpoint di comunicazione**: un’interfaccia logica che collega un processo al canale di rete.  
Ogni connessione è definita da una **coppia di socket**: uno sul lato client e uno sul lato server.

In pratica, un socket fornisce:

- un **canale di comunicazione bidirezionale**;
    
- un **indirizzo logico** (IP + porta);
    
- un **protocollo di trasporto** (es. TCP o UDP).

---
### **4. Caratteristiche dei messaggi**

I messaggi inviati tramite socket hanno le stesse proprietà generali viste nei modelli precedenti.

$$  
\begin{cases}  
\textbf{Contenuto:}~ & \text{dati o informazioni da trasmettere.} \\\\  
\textbf{Dimensione:}~ & \text{può essere fissa o variabile.} \\\\  
\textbf{Ordinamento:}~ & \text{dipende dal tipo di connessione (FIFO nei protocolli affidabili).}  
\end{cases}  
$$

Con i protocolli come **TCP**, i messaggi vengono consegnati **in ordine** e **senza perdita**.  
Con **UDP**, invece, la consegna non è garantita, ma è più veloce e leggera.

---
### **5. Funzioni principali**

Il sistema operativo fornisce diverse **primitive** per la gestione dei socket.  
Di seguito le operazioni principali, valide in ambienti UNIX, Linux o Windows.

$$  
\begin{cases}  
\textbf{Creazione:}~ & \texttt{socket()}~\text{– crea un endpoint di comunicazione.} \\\\  
\textbf{Connessione:}~ & \texttt{connect()}~\text{– collega un socket client a uno server.} \\\\  
\textbf{Ascolto:}~ & \texttt{listen()}~\text{– pone un socket server in attesa di richieste.} \\\\  
\textbf{Accettazione:}~ & \texttt{accept()}~\text{– accetta una connessione entrante.} \\\\  
\textbf{Scrittura:}~ & \texttt{send()}~\text{– invia un messaggio.} \\\\  
\textbf{Lettura:}~ & \texttt{recv()}~\text{– riceve un messaggio.} \\\\  
\textbf{Chiusura:}~ & \texttt{close()}~\text{– termina la connessione.}  
\end{cases}  
$$

Esempio in C (schematico):

```c
int s = socket(AF_INET, SOCK_STREAM, 0);
connect(s, (struct sockaddr *)&server, sizeof(server));
send(s, "Hello Server", 12, 0);
recv(s, buffer, sizeof(buffer), 0);
close(s);
```

---
### **6. Caratteristiche e problemi**

Le principali caratteristiche e problematiche legate alla comunicazione con socket possono essere riassunte così:

$$  
\begin{cases}  
\textbf{Ordinamento dei messaggi:}~ & \text{FIFO (First In, First Out).} \\\\  
\textbf{Ordinamento dei processi in attesa:}~ & \text{FIFO (gestito dal sistema operativo).} \\\\  
\textbf{Connessione:}~ &  
\begin{cases}  
\text{Con gestione della connessione (TCP).} \\\\  
\text{Senza gestione della connessione (UDP).} \\\\  
\text{Multicast (invio a più destinatari).}  
\end{cases}  
\end{cases}  
$$

#### **6.1. Socket orientati alla connessione (TCP)**

- Garantiscono **ordine, integrità e affidabilità** dei dati.
    
- Adatti per comunicazioni persistenti (es. web, FTP, e-mail).
    
- Hanno un overhead maggiore dovuto alla gestione dello stato della connessione.

#### **6.2. Socket non orientati alla connessione (UDP)**

- Nessuna garanzia di consegna o ordinamento.
    
- Comunicazione più **veloce ma meno sicura**.
    
- Utilizzati per streaming, DNS, giochi online, VoIP.

#### **6.3. Socket multicast**

- Permettono l’invio simultaneo dello stesso messaggio a **più destinatari**.
    
- Usati in sistemi distribuiti e applicazioni broadcast.

---
### **7. Sintesi finale**

$$  
\begin{cases}  
\textbf{Modello:}~ & \text{generalizzazione in rete della comunicazione tramite pipe.} \\\\  
\textbf{Architettura:}~ & \text{client–server basata su IP e porte.} \\\\  
\textbf{Primitive:}~ & \text{socket, connect, listen, accept, send, recv, close.} \\\\  
\textbf{Connessioni:}~ & \text{TCP (affidabile), UDP (non affidabile), multicast.} \\\\  
\textbf{Ordinamento:}~ & \text{FIFO per messaggi e processi in attesa.}  
\end{cases}  
$$

---
### **8. Conclusione**

La comunicazione con **socket** rappresenta la **forma più generale e potente di IPC**,  
poiché consente la cooperazione tra processi **su macchine differenti**, anche geograficamente distanti.

È il fondamento delle **reti moderne** e di tutti i servizi **client–server** (web, posta, streaming, database, ecc.),  
e costituisce il punto di incontro tra **sistemi operativi** e **reti di calcolatori**.

In altre parole: i socket sono le “porte logiche” attraverso cui i processi dialogano **nel mondo distribuito**.