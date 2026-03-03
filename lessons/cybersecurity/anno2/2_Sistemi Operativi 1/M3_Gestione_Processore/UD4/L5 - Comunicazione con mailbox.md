# **M3 UD4 Lezione 5 - Comunicazione con mailbox**

### **1. Introduzione**

La **comunicazione tramite mailbox** rappresenta un’evoluzione del modello a **scambio di messaggi**.  
Mentre in quest’ultimo i processi comunicano **direttamente** tra loro, nel modello con mailbox la comunicazione è **indiretta**:  
i messaggi vengono depositati in un contenitore intermedio, la **mailbox**, dalla quale i processi interessati possono successivamente leggerli.

Questo approccio introduce maggiore **flessibilità**, **sicurezza** e **modularità**, poiché i processi non devono conoscersi reciprocamente:  
basta che condividano l’identificativo della stessa mailbox.

---
### **2. Modello della comunicazione con mailbox**

Una **mailbox** (o _casella postale_) è un’area logica gestita dal sistema operativo che permette di **depositare** e **recuperare messaggi**.

Ogni messaggio contiene:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Identità del processo mittente.} \\\\  
\textbf{2.}~ & \text{Identificatore della mailbox destinataria.} \\\\  
\textbf{3.}~ & \text{Informazioni o dati da trasmettere.} \\\\  
\textbf{4.}~ & \text{Eventuali metadati di controllo (timestamp, priorità, ecc.).}  
\end{cases}  
$$

#### **2.1. Dimensione dei messaggi**

I messaggi possono essere:

- **A dimensione fissa:** struttura uniforme, più semplice da gestire.
    
- **A dimensione variabile:** più flessibili, ma con gestione più complessa.

---
### **3. Capacità della mailbox**

Ogni mailbox ha una **capacità**, cioè un numero massimo di messaggi che può contenere contemporaneamente.

$$  
\begin{cases}  
\textbf{Illimitata:}~ & \text{può contenere un numero indefinito di messaggi (comunicazione asincrona).} \\\\  
\textbf{Limitata:}~ & \text{può contenere un numero finito di messaggi; il mittente si blocca se la mailbox è piena.} \\\\  
\textbf{Nulla:}~ & \text{non può contenere messaggi; la comunicazione avviene solo se il destinatario è pronto (sincrona).}  
\end{cases}  
$$

---
### **4. Funzioni fondamentali**

Il sistema operativo fornisce diverse **primitive** per gestire le mailbox e per inviare o ricevere messaggi.

#### **4.1. Creazione e cancellazione**

```plaintext
create(M)
delete(M)
```

- `create(M)`: crea una nuova mailbox con identificativo `M`.
    
- `delete(M)`: elimina la mailbox `M` e tutti i messaggi in essa contenuti.

---
#### **4.2. Invio di un messaggio**

```plaintext
send(M, messaggio)
```

- Deposita un messaggio nella mailbox `M`.
    
- Se la capacità è:
    
    - **Illimitata:** l’invio è immediato e non bloccante.
        
    - **Limitata:** il processo si blocca se la mailbox è piena.
        
    - **Nulla:** il mittente si blocca fino a quando non c’è un processo pronto a ricevere.

---
#### **4.3. Ricezione di un messaggio**

```plaintext
receive(M, messaggio)
```

- Riceve un messaggio dalla mailbox `M`.
    
- Se non ci sono messaggi disponibili, il processo si blocca fino all’arrivo di un nuovo messaggio.

---
#### **4.4. Invio condizionale**

```plaintext
cond_send(M, messaggio): error_status
```

- Invia un messaggio solo **se la comunicazione può essere completata**.
    
- Se la mailbox è piena e l’invio bloccherebbe il mittente, la funzione **ritorna un errore** e **non deposita il messaggio**.

---
#### **4.5. Ricezione condizionale**

```plaintext
cond_receive(M, messaggio): error_status
```

- Riceve un messaggio solo se **è presente almeno un messaggio nella mailbox**.
    
- Se la mailbox è vuota, la funzione restituisce un **errore** e non blocca il processo.

---
### **5. Sincronizzazione e comportamento**

Il comportamento della comunicazione dipende dalla **capacità della mailbox**:

$$  
\begin{cases}  
\textbf{Illimitata:}~ & \text{comunicazione asincrona, il mittente non si blocca mai.} \\\\  
\textbf{Nulla:}~ & \text{comunicazione sincrona, mittente e destinatario devono essere entrambi pronti.} \\\\  
\textbf{Limitata:}~ & \text{comunicazione bufferizzata, asincrona ma con limiti di coda.}  
\end{cases}  
$$

---
### **6. Caratteristiche e problemi**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Comunicazione indiretta: i processi non si conoscono direttamente.} \\\\  
\textbf{2.}~ & \text{Memoria non condivisa tra processi (i messaggi sono gestiti dal SO).} \\\\  
\textbf{3.}~ & \text{Sincronizzazione implicita gestita dal sistema operativo.} \\\\  
\textbf{4.}~ & \text{Maggiore sicurezza e isolamento tra processi.}  
\end{cases}  
$$

---
### **7. Ordinamento dei messaggi e dei processi**

Il sistema operativo può gestire le **code di messaggi** e i **processi in attesa** secondo varie **politiche di ordinamento**:

$$  
\begin{cases}  
\textbf{1. FIFO (First In, First Out):}~ & \text{i messaggi vengono serviti in ordine di arrivo.} \\\\  
\textbf{2. Priorità:}~ & \text{i messaggi più urgenti vengono gestiti prima.} \\\\  
\textbf{3. Scadenza (Deadline):}~ & \text{i messaggi vengono ordinati in base alla loro scadenza temporale.}  
\end{cases}  
$$

---
### **8. Proprietà della mailbox**

A seconda di chi la gestisce, distinguiamo:

$$  
\begin{cases}  
\textbf{Mailbox del sistema operativo:}~ & \text{non associata a un processo specifico.} \\\\  
\textbf{Mailbox di processo:}~ &  
\begin{cases}  
\text{Solo il processo proprietario può ricevere dalla mailbox.} \\\\  
\text{Altri processi possono solo inviare.} \\\\  
\text{Quando il processo termina, la mailbox viene eliminata.}  
\end{cases}  
\end{cases}  
$$

---
### **9. Comunicazioni con molti mittenti o destinatari**

La comunicazione con mailbox consente **diversi schemi di interazione**, che ampliano la flessibilità del modello a messaggi.

$$  
\begin{cases}  
\textbf{Molti a uno:}~ & \text{più processi client inviano a un unico processo server.} \\\\  
\textbf{Uno a molti:}~ & \text{un processo invia a più processi di servizio.} \\\\  
\textbf{Molti a molti:}~ & \text{più client e più server comunicano tramite la stessa mailbox.}  
\end{cases}  
$$

In ogni caso, la comunicazione effettiva avviene **tra due processi per volta** (un mittente e un destinatario), anche se la mailbox è condivisa da molti.

---
### **10. Sintesi finale**

$$  
\begin{cases}  
\textbf{Modello:}~ & \text{comunicazione indiretta tramite caselle postali (mailbox).} \\\\  
\textbf{Primitive:}~ & \text{create, delete, send, receive, cond\_send, cond\_receive.} \\\\  
\textbf{Sincronizzazione:}~ & \text{gestita implicitamente dal sistema operativo.} \\\\  
\textbf{Ordinamento:}~ & \text{FIFO, priorità o scadenza.} \\\\  
\textbf{Applicazioni:}~ & \text{comunicazioni molti-a-uno, uno-a-molti, molti-a-molti.}  
\end{cases}  
$$

---
### **11. Conclusione**

Le mailbox rappresentano una forma **potente e modulare di comunicazione tra processi**, che migliora la separazione logica e la sicurezza del sistema.  
Sono largamente utilizzate nei **sistemi multitasking** e **distribuiti**, dove i processi devono comunicare in modo indipendente e asincrono.

Questo modello è anche alla base delle **architetture client–server** moderne e dei **sistemi a microservizi**, dove le mailbox trovano un equivalente nei **message queue** (come RabbitMQ o Kafka).