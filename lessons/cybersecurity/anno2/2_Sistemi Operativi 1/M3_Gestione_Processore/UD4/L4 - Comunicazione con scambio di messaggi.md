# **M3 UD4 Lezione 4 - Comunicazione con scambio di messaggi**

---

### **1. Introduzione**

La **comunicazione con scambio di messaggi** rappresenta un’alternativa diretta alla **memoria condivisa**:  
invece di condividere aree di memoria, i processi **si scambiano esplicitamente informazioni** attraverso **messaggi strutturati**.

È una forma di comunicazione **più sicura** (perché non richiede accessi simultanei alla stessa memoria) e più adatta ai **sistemi distribuiti**, dove i processi possono risiedere su macchine diverse.

Il sistema operativo si occupa di **mediare** lo scambio dei messaggi e di **garantirne la sincronizzazione**.

---
### **2. Modello della comunicazione a messaggi**

Ogni comunicazione avviene attraverso il trasferimento di **unità logiche di informazione** chiamate _messaggi_.  
Ciascun messaggio contiene almeno:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Identità del processo mittente.} \\\\  
\textbf{2.}~ & \text{Identità del processo destinatario.} \\\\  
\textbf{3.}~ & \text{Dati o informazioni da trasmettere.} \\\\  
\textbf{4.}~ & \text{Eventuali metadati di gestione (es. priorità, timestamp, stato).}  
\end{cases}  
$$

#### **2.1. Dimensione dei messaggi**

I messaggi possono avere:

- **Dimensione fissa**, se il sistema impone una struttura predefinita (più semplice ma meno flessibile).
    
- **Dimensione variabile**, se il sistema consente di inviare messaggi di lunghezza arbitraria (più flessibile ma più complessa da gestire).

---
### **3. Buffer di comunicazione**

Per gestire i messaggi, il sistema operativo utilizza aree di memoria dette **buffer**.  
Ogni buffer può contenere uno o più messaggi in attesa di essere letti o inviati.

#### **3.1. Assegnazione dei buffer**

$$  
\begin{cases}  
\textbf{1. Buffer dedicato:}~ & \text{riservato a una coppia specifica di processi (mittente–destinatario).} \\\\  
\textbf{2. Buffer generale:}~ & \text{utilizzabile da più processi o gruppi.}  
\end{cases}  
$$

#### **3.2. Quantità di buffer disponibili**

$$  
\begin{cases}  
\textbf{Illimitata:}~ & \text{il processo può inviare quanti messaggi vuole, senza mai bloccarsi sull'invio.} \\\\  
\textbf{Limitata:}~ & \text{il mittente deve attendere se il numero di messaggi pendenti raggiunge il limite.} \\\\  
\textbf{Nulla (rendezvous):}~ & \text{nessun buffer; il mittente deve sempre aspettare un ricevente pronto.}  
\end{cases}  
$$

Il caso **nullo** è particolarmente significativo: senza alcun buffer intermedio, il sistema **forza una sincronizzazione obbligatoria** tra mittente e destinatario — il messaggio passa solo nell'istante in cui entrambi i processi sono presenti contemporaneamente all'operazione di scambio (modello _rendezvous_).

---
### **4. Funzioni di comunicazione**

Il sistema operativo mette a disposizione **primitive** (funzioni) per l’invio e la ricezione dei messaggi.

#### **4.1. Invio bloccante**

```plaintext
send(Q, messaggio)
```

- Deposita il messaggio in un buffer libero.
- Se ci sono buffer liberi, il mittente prosegue **senza attendere** l'arrivo del ricevente.
- Se non ci sono buffer disponibili, **il mittente viene sospeso** finché non se ne libera uno.

> 💡 Il mittente **non specifica se stesso** come argomento: il SO sa che il mittente è il **processo in esecuzione** al momento della chiamata, cioè proprio chi sta eseguendo la `send`.

👉 È detta **primitiva bloccante**, perché il processo può rimanere in attesa del completamento dell'invio se mancano buffer.

---
#### **4.2. Ricezione bloccante**

```plaintext
receive(P, messaggio)
```

- Estrae un messaggio dal buffer (proveniente dal mittente `P`) e lo deposita nella variabile `messaggio` nello spazio di indirizzamento del ricevente.
- Se non ci sono messaggi disponibili, **il processo destinatario viene sospeso** finché non ne arriva uno.

> 💡 Anche qui il destinatario **non specifica se stesso**: il SO sa che il destinatario è il processo che sta eseguendo la `receive`.

👉 Anche questa è una **primitiva bloccante**, perché il destinatario attende un messaggio valido.

---
#### **4.3. Invio condizionale (non bloccante)**

```plaintext
cond_send(Q, messaggio): error_status
```

- Tenta di depositare il messaggio nel buffer.
    
- Se il buffer è pieno, **non blocca il mittente** ma restituisce un _errore di stato_ (ad esempio `BUFFER_FULL`).
    
- Nessun messaggio viene inviato fino a quando non sarà disponibile spazio.

---
#### **4.4. Ricezione condizionale (non bloccante)**

```plaintext
cond_receive(P, messaggio): error_status
```

- Tenta di ricevere un messaggio dal buffer.
    
- Se non ci sono messaggi disponibili, **non blocca il destinatario** ma restituisce un errore (`EMPTY_BUFFER`).

👉 Queste due primitive non bloccanti vengono utilizzate nei **sistemi asincroni**, dove i processi non devono attendersi reciprocamente.

---
### **5. Sincronizzazione dei processi comunicanti**

I processi che si scambiano messaggi devono sincronizzarsi in modo appropriato.  
Esistono due modalità principali di sincronizzazione:

$$  
\begin{cases}  
\textbf{Comunicazione asincrona:}~ & \text{le primitive bloccano solo se l’operazione è impossibile da completare.} \\\\  
\textbf{Comunicazione sincrona:}~ & \text{l’invio e la ricezione avvengono solo se entrambi i processi sono pronti.}  
\end{cases}  
$$

#### **5.1. Comunicazione asincrona**

- Il mittente può inviare messaggi senza attendere il destinatario.
    
- Il destinatario può riceverli in un secondo momento.
    
- Tipica dei sistemi multitasking o distribuiti.

#### **5.2. Comunicazione sincrona**

- La trasmissione avviene **solo quando mittente e destinatario sono contemporaneamente pronti**.
    
- Il mittente si blocca sempre fino a che il destinatario non riceve il messaggio.
    
- Offre una **sincronizzazione implicita** tra i due processi.

---
### **6. Identificazione dei processi comunicanti**

#### **6.1. Comunicazione simmetrica (diretta)**

$$  
\begin{cases}  
\textbf{Mittente e destinatario}~ & \text{sono entrambi identificati esplicitamente.} \\\\  
\textbf{Invio diretto:}~ & \text{il messaggio è inviato a un processo specifico.} \\\\  
\textbf{Ricezione diretta:}~ & \text{il messaggio è ricevuto solo dal mittente previsto.}  
\end{cases}  
$$

Questo modello richiede un **legame esplicito** tra i processi, utile per connessioni 1 a 1 (es. client–server).

---
#### **6.2. Comunicazione asimmetrica (indiretta)**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Il mittente o il destinatario possono non essere specificati univocamente.} \\\\  
\textbf{2.}~ & \text{Un processo può ricevere da qualunque mittente o inviare a un gruppo.} \\\\  
\textbf{3.}~ & \text{Il canale di comunicazione può essere una mailbox o una coda di sistema.}  
\end{cases}  
$$

Esempi:

- invio a un gruppo di processi (`broadcast`);
    
- ricezione dal primo processo disponibile (`any source`).

---
### **7. Caratteristiche e problemi**

La comunicazione a messaggi presenta vantaggi e svantaggi rispetto alla memoria condivisa.

$$  
\begin{cases}  
\textbf{Vantaggi:} &  
\begin{cases}  
\text{nessuna memoria condivisa (più sicuro);} \\\\  
\text{sincronizzazione implicita gestita dal SO;} \\\\  
\text{adatta a sistemi distribuiti.}  
\end{cases} \\\\  
\textbf{Svantaggi:} &  
\begin{cases}  
\text{maggior overhead di sistema;} \\\\  
\text{latenza di trasmissione superiore;} \\\\ 
\text{dipendenza dalle politiche di buffer.}  
\end{cases}  
\end{cases}  
$$

---
### **8. Implementazione**

Nell'implementazione reale:

- il **sistema operativo gestisce i buffer di messaggi**, allocandoli e liberandoli dinamicamente;
- i **messaggi vengono trasferiti tramite system call** (es. `send()`, `recv()`);
- nei sistemi distribuiti, lo scambio avviene su **canali di rete affidabili o non affidabili** (es. TCP o UDP).

#### **8.1. Struttura interna di un buffer**

Ogni buffer del sistema operativo è strutturato tipicamente in **tre campi**:

$$
\begin{cases}
\textbf{Mittente:} & \text{identificatore del processo che ha inviato il messaggio} \\\\
\textbf{Destinatario:} & \text{identificatore del processo a cui il messaggio è destinato} \\\\
\textbf{Messaggio:} & \text{il contenuto effettivo dei dati da trasmettere}
\end{cases}
$$

Esempio: se il processo P invia un messaggio al processo Q, nel buffer verranno memorizzati `(P, Q, informazione)`. Il SO scorre i buffer cercando quelli destinati al processo che esegue una `receive`, e gliene consegna uno secondo la politica di coda adottata.

#### **8.2. Fattori che influenzano l'efficienza**

L'efficienza dipende dalla **dimensione dei buffer**, dal **numero di processi concorrenti** e dalla **politica di gestione della coda** (FIFO, priorità, ecc.).

##### **Sincronizzazione gestita trasparentemente dal SO**

Un vantaggio importante rispetto alla memoria condivisa: **non c'è memoria visibile direttamente ai processi**. La sincronizzazione necessaria a garantire la **consistenza** dei messaggi — che un messaggio in lettura non venga alterato da una scrittura concorrente — è gestita **direttamente dal sistema operativo** in modo trasparente per i processi applicativi.

---
### **9. Sintesi finale**

$$
\begin{cases}
\textbf{Modello:}~ & \text{scambio esplicito di messaggi tra processi.} \\\\
\textbf{Primitive:}~ & \text{send, receive, cond\_send, cond\_receive.} \\\\
\textbf{Sincronizzazione:}~ & \text{sincrona o asincrona.} \\\\
\textbf{Identificazione:}~ & \text{comunicazione diretta o indiretta.} \\\\
\textbf{Implementazione:}~ & \text{buffer gestiti dal sistema operativo.}
\end{cases}
$$


---
### **10. Conclusione**

La comunicazione con scambio di messaggi fornisce un **modello robusto e modulare** di cooperazione tra processi,  
perfetto per i **sistemi multiprogrammati** e soprattutto per quelli **distribuiti**.

Il sistema operativo gestisce implicitamente **sincronizzazione e protezione**, riducendo gli errori e semplificando la progettazione delle applicazioni concorrenti.  
È il principio alla base di molte **architetture moderne**, dai sistemi client–server alle comunicazioni tramite **socket** in rete.