# **M3 UD4 Lezione 2 - Comunicazione tra processi**

---

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

> ⚠️ **Nota terminologica.** Il "canale di comunicazione" inteso a livello di **procedure di sistema per l'IPC** non va confuso con il canale di comunicazione visto in precedenza per la connessione delle **periferiche**: stesso termine, contesti diversi.

##### **Direzionalità del canale**

Il canale stabilisce il **flusso dei dati** e definisce la **direzione** della comunicazione:

- **Monodirezionale**: i dati fluiscono in **una sola direzione**, dal mittente al ricevente;
- **Bidirezionale**: i dati possono fluire in entrambe le direzioni sullo stesso canale.

Anche se il sistema supportasse **solo** canali monodirezionali, è comunque possibile realizzare uno scambio bidirezionale **combinando due canali monodirezionali** orientati in versi opposti.

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

I meccanismi concreti per realizzare la comunicazione tra processi si dividono in due grandi famiglie a seconda che mittente e ricevente debbano **conoscersi reciprocamente**.

#### **6.1. Comunicazione diretta**

Nella comunicazione **diretta**, ciascuno dei due processi conosce esplicitamente l'altro: serve un'**identificazione univoca** del mittente e del ricevente.

##### **Memoria condivisa nello spazio dei processi**

Due processi P e Q che vogliono comunicare condividono una **porzione di memoria** posta nel loro **spazio di indirizzamento**: il processo che vuole scrivere ha accesso in scrittura, l'altro ha accesso in lettura. È la tecnica più semplice e veloce.

##### **Buffer nel sistema operativo (message passing)**

Senza usare memoria nello spazio dei processi, P e Q comunicano attraverso **funzioni di sistema operativo** che usano **buffer interni al SO**: il mittente deposita il messaggio in un buffer, il ricevente lo estrae. Anche qui mittente e ricevente sono noti.

#### **6.2. Comunicazione indiretta**

A volte è utile **disaccoppiare** mittente e ricevente, ad esempio per:

- consentire a **più mittenti** di essere gestiti da un unico ricevente;
- consentire a un **messaggio di un mittente** di essere ricevuto da **più possibili riceventi** (broadcast);
- **riattivare** un processo coinvolto nella comunicazione dopo una sua terminazione anticipata per errore, senza perdere lo stato della comunicazione.

In questi casi i due processi **non si conoscono a priori**: vengono usate strutture dati intermedie gestite dal SO.

##### **Mailbox**

Mittente e ricevente si scambiano messaggi depositandoli e prelevandoli da una **struttura "casella postale"** nel sistema operativo. Né il mittente conosce direttamente il destinatario, né viceversa: ciascuno vede solo la mailbox.

##### **File su disco**

Estensione del concetto di memoria condivisa: anziché usare memoria centrale, il processo P scrive le informazioni in un **file su memoria di massa**, e il processo Q le legge da quel file. Indipendenza temporale tra le due operazioni.

##### **Pipe**

Variante con **buffer ordinato sequenzialmente FIFO** in memoria centrale del SO: P **accoda** i messaggi a un estremo, Q li **estrae** dall'estremo opposto in ordine first-in-first-out.

##### **Socket**

Generalizzazione delle pipe per **sistemi distribuiti**: P e Q possono risiedere su **macchine diverse** ed eventualmente con **sistemi operativi diversi**. P deposita il messaggio in un estremo della socket, Q lo estrae dall'estremo opposto sull'altra macchina; la **rete** garantisce in modo **trasparente** il trasferimento.

> 💡 I socket funzionano **sia in ambiente distribuito sia sulla stessa macchina**: nel secondo caso i due tronconi vivono nello stesso host, ma l'interfaccia di programmazione resta identica.

$$  
\begin{cases}  
\textbf{Dirette (mittente e ricevente noti):}~ & \text{memoria condivisa, buffer di sistema (message passing).} \\\\  
\textbf{Indirette (disaccoppiate):}~ & \text{mailbox, file su disco, pipe, socket.}  
\end{cases}  
$$

#### **6.3. Scelta del meccanismo**

La scelta tra questi metodi dipende dal **contesto applicativo**:

- Se i processi operano sullo stesso sistema → **memoria condivisa o pipe**.
- Se operano su macchine diverse → **socket o messaggistica remota**.
- Se serve una comunicazione asincrona o disaccoppiata → **mailbox** o **code di messaggi**.

#### **6.4. Validità anche per i thread**

Tutti i meccanismi visti per la comunicazione tra processi **valgono anche per la comunicazione tra thread**, con un vantaggio rilevante: i thread di uno stesso processo **condividono nativamente lo spazio di memoria**, quindi la memoria condivisa è di fatto gratuita (non richiede setup esplicito tramite il SO).

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