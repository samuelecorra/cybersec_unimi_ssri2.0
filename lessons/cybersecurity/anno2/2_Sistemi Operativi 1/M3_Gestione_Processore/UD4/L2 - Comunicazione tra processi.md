# **M3 UD4 Lezione 2 - Comunicazione tra processi**

### **1. Introduzione**

Nei sistemi operativi moderni, la comunicazione tra processi (_InterProcess Communication_, o IPC) rappresenta il **cuore della cooperazione** tra attività concorrenti.  
Permette a processi distinti di **scambiarsi dati**, **sincronizzarsi** e **coordinare le proprie azioni** per raggiungere un obiettivo comune.

Senza meccanismi di comunicazione efficaci, i processi rimarrebbero isolati, incapaci di interagire o collaborare — e un sistema operativo sarebbe ridotto a una semplice somma di esecuzioni indipendenti.

---
### **2. Concetto di comunicazione**

La comunicazione tra processi comprende **insiemi di meccanismi e politiche** che consentono ai processi di scambiarsi informazioni in modo strutturato, sicuro e coerente.

In sintesi:

$$  
\text{Comunicazione tra processi} = \text{insieme di mezzi per trasmettere dati tra entità cooperanti.}  
$$

L’obiettivo è consentire a un processo mittente di **inviare informazioni** a un processo ricevente, in modo da **rendere possibile l’elaborazione cooperativa**.

---
### **3. Necessità della comunicazione**

La comunicazione tra processi è necessaria per due ragioni fondamentali:

$$  
\begin{cases}  
\textbf{1. Trasferimento di informazioni:}~ & \text{per inviare dati, comandi o risultati da un processo a un altro.} \\\\  
\textbf{2. Condivisione di informazioni:}~ & \text{per consentire l’uso comune di risorse o strutture di dati.}  
\end{cases}  
$$

Esempi pratici:

- Un **processo produttore** genera dati che un **processo consumatore** deve elaborare.
    
- Un **server** deve inviare risposte multiple a **client** che ne fanno richiesta.
    
- Più processi devono accedere alla stessa **tabella in memoria condivisa** o allo stesso **file di log**.

---

### **4. Entità coinvolte nella comunicazione**

Ogni comunicazione interprocesso coinvolge tre entità fondamentali:

$$  
\begin{cases}  
\textbf{1. Processo mittente (produttore):}~ & \text{genera o invia l’informazione.} \\\\  
\textbf{2. Processo destinatario (consumatore):}~ & \text{riceve e utilizza l’informazione.} \\\\  
\textbf{3. Canale di comunicazione:}~ & \text{mezzo logico o fisico attraverso cui i dati vengono trasmessi.}  
\end{cases}  
$$

#### **4.1. Il canale di comunicazione**

Può essere:

- **Fisico**, come un bus o una rete di trasmissione;
    
- **Logico**, come una pipe, un socket o una coda di messaggi.

Il canale stabilisce il **flusso dei dati** e definisce la **direzione** della comunicazione (unidirezionale o bidirezionale).

---
### **5. Caratteristiche della comunicazione**

Per essere efficace e utilizzabile, un sistema di comunicazione tra processi deve possedere alcune **caratteristiche fondamentali**.

$$  
\begin{cases}  
\textbf{Quantità di informazioni:}~ & \text{la capacità massima del canale e la dimensione dei messaggi.} \\\\  
\textbf{Velocità:}~ & \text{rapidità di trasmissione e latenza di comunicazione.} \\\\  
\textbf{Scalabilità:}~ & \text{capacità di gestire un numero crescente di processi e dati.} \\\\  
\textbf{Semplicità:}~ & \text{facilità di utilizzo per i programmatori e integrazione con le API del sistema.} \\\\  
\textbf{Omogeneità:}~ & \text{uniformità dei meccanismi di comunicazione tra processi locali e remoti.} \\\\  
\textbf{Integrazione:}~ & \text{possibilità di utilizzare le comunicazioni direttamente nei linguaggi di programmazione.} \\\\  
\textbf{Affidabilità:}~ & \text{capacità di garantire la consegna dei messaggi senza perdita o corruzione.} \\\\  
\textbf{Sicurezza:}~ & \text{protezione contro accessi non autorizzati o intercettazioni di dati.} \\\\  
\textbf{Protezione:}~ & \text{isolamento tra processi e controllo sui diritti di comunicazione.}  
\end{cases}  
$$

Tutte queste proprietà concorrono a determinare la **qualità complessiva dell’IPC** (InterProcess Communication).  
In particolare, un sistema operativo deve trovare un equilibrio tra **prestazioni**, **affidabilità** e **semplicità di implementazione**.

---
### **6. Implementazione della comunicazione**

I meccanismi concreti per realizzare la comunicazione tra processi possono essere diversi.  
I principali metodi implementativi sono:

$$  
\begin{cases}  
\textbf{1. Memoria condivisa (Shared Memory):}~ & \text{i processi leggono e scrivono in un’area comune della memoria.} \\\\  
\textbf{2. Messaggi (Message Passing):}~ & \text{i processi si scambiano messaggi attraverso primitive di invio/ricezione.} \\\\  
\textbf{3. Mailbox (Code di messaggi):}~ & \text{i messaggi vengono depositati in “caselle” gestite dal sistema operativo.} \\\\  
\textbf{4. File e Pipe:}~ & \text{i dati sono scritti e letti in un flusso continuo, utile per la comunicazione sequenziale.} \\\\  
\textbf{5. Socket:}~ & \text{per la comunicazione tra processi distribuiti su nodi di rete differenti.}  
\end{cases}  
$$

#### **6.1. Scelta del meccanismo**

La scelta tra questi metodi dipende dal **contesto applicativo**:

- Se i processi operano sullo stesso sistema → **memoria condivisa o pipe**.
    
- Se operano su macchine diverse → **socket o messaggistica remota**.
    
- Se serve una comunicazione asincrona → **mailbox** o **code di messaggi**.

---
### **7. Sintesi finale**

$$  
\begin{cases}  
\textbf{Scopo:}~ & \text{consentire la cooperazione tra processi tramite scambio di informazioni.} \\\\  
\textbf{Entità:}~ & \text{mittente, ricevente e canale di comunicazione.} \\\\  
\textbf{Caratteristiche:}~ & \text{efficienza, sicurezza, affidabilità, semplicità.} \\\\  
\textbf{Implementazione:}~ & \text{memoria condivisa, messaggi, mailbox, pipe, socket.}  
\end{cases}  
$$

---
### **8. Conclusione**

La comunicazione tra processi è uno degli aspetti più complessi e affascinanti dei sistemi operativi.  
È ciò che consente di **trasformare un insieme di processi isolati in un sistema cooperante**, in cui ogni componente contribuisce a un obiettivo comune.

Nelle lezioni successive analizzeremo **ciascun meccanismo di comunicazione** nel dettaglio, osservandone **architettura, vantaggi, limiti e casi d’uso** nei moderni sistemi operativi come **UNIX, Linux e Windows**.