# **M3 UD4 Lezione 6 - Comunicazione con file e pipe**

### **1. Introduzione**

In questa lezione concludiamo l’unità sulla **comunicazione tra processi**, analizzando due meccanismi fondamentali dei sistemi operativi:  
la **comunicazione tramite file condivisi** e quella tramite **pipe** (_condotte_).

Entrambi i metodi permettono a più processi di **scambiarsi informazioni** senza condividere direttamente la memoria, ma scrivendo e leggendo dati attraverso un intermediario gestito dal **file system** o dal **kernel**.

---
### **2. Comunicazione mediante file condivisi**

#### **2.1. Concetto**

Due o più processi possono comunicare **condividendo uno stesso file**.  
Uno scrive nel file (mittente), e l’altro legge da esso (destinatario).  
Il file diventa così un **mezzo persistente** di comunicazione.

$$  
\text{Processo A} \xrightarrow{\text{scrittura}} \text{File condiviso} \xrightarrow{\text{lettura}} \text{Processo B}  
$$

Questa forma di comunicazione è **asincrona** e **persistente**:

- asincrona perché i processi non devono essere attivi contemporaneamente;
    
- persistente perché i dati rimangono memorizzati anche dopo la terminazione dei processi.

---
#### **2.2. Caratteristiche principali**

$$  
\begin{cases}  
\textbf{Tipo di file:}~ & \text{in memoria centrale o su disco, gestito dal file system.} \\\\  
\textbf{Accesso:}~ & \text{scrittura solo in append e lettura sequenziale.} \\\\  
\textbf{Sincronizzazione:}~ & \text{gestita implicitamente dal file system.} \\\\  
\textbf{Persistenza:}~ & \text{il contenuto sopravvive alla fine dei processi.}  
\end{cases}  
$$

👉 L’uso di file condivisi è ideale per applicazioni che necessitano di **registrare dati comuni**, come log, code di messaggi, o database temporanei.

---
#### **2.3. Vantaggi e limiti**

**Vantaggi:**

- Permette comunicazione tra processi non simultanei.
    
- Mantiene uno storico permanente dei dati.
    
- Può essere utilizzato anche tra processi su macchine diverse (tramite file condivisi in rete).

**Limiti:**

- Operazioni di I/O più lente rispetto alla memoria o ai messaggi diretti.
    
- Mancanza di sincronizzazione fine-grained (gestita dal file system, non dal kernel).
    
- Possibili conflitti di accesso se non controllati.

---
### **3. Comunicazione mediante pipe**

#### **3.1. Concetto**

Una **pipe** (in italiano: condotta) è un **canale unidirezionale** che collega due processi,  
permettendo il flusso di dati **in modo continuo e ordinato**.

È pensata per la comunicazione tra **processi attivi contemporaneamente**, in cui uno scrive (mittente) e l’altro legge (destinatario).

$$  
\text{Processo produttore} \xrightarrow{\text{pipe}} \text{Processo consumatore}  
$$

---
#### **3.2. Proprietà delle pipe**

$$  
\begin{cases}  
\textbf{Comunicazione:}~ & \text{tra due processi, tipicamente padre e figlio.} \\\\  
\textbf{Direzione:}~ & \text{unidirezionale (scrittura → lettura).} \\\\  
\textbf{Accesso:}~ & \text{sequenziale (FIFO – First In, First Out).} \\\\  
\textbf{Gestione:}~ & \text{controllata dal kernel, non dal file system.}  
\end{cases}  
$$

👉 In UNIX, una pipe viene creata con la chiamata di sistema:

```c
pipe(fd);
```

dove `fd[0]` è l’estremità di lettura e `fd[1]` quella di scrittura.

---
#### **3.3. Tipologie di pipe**

$$  
\begin{cases}  
\textbf{Pipe anonime:}~ & \text{usate tra processi imparentati (es. padre e figlio).} \\\\  
\textbf{Pipe nominate (FIFO):}~ & \text{possono essere usate tra processi non imparentati, tramite un nome nel file system.}  
\end{cases}  
$$

Le **pipe nominate** vengono create con il comando:

```bash
mkfifo nomepipe
```

e si comportano come file speciali, accessibili tramite normali operazioni di lettura e scrittura (`read`, `write`).

---
### **4. Caratteristiche dei messaggi nelle pipe**

Ogni unità di informazione scritta in una pipe è trattata come un **messaggio** o **blocco di dati**.

$$  
\begin{cases}  
\textbf{Contenuto:}~ & \text{dati provenienti dal processo mittente.} \\\\  
\textbf{Dimensione:}~ & \text{fissa o variabile, in base alla scrittura.} \\\\  
\textbf{Gestione:}~ & \text{ordinamento FIFO e controllo automatico della sincronizzazione.}  
\end{cases}  
$$

La sincronizzazione avviene tramite **politiche del kernel**, che sospende il mittente se il buffer della pipe è pieno e sospende il destinatario se la pipe è vuota.

---
### **5. Funzioni fondamentali**

Le pipe e i file condivisi sono gestiti tramite **primitive di sistema operativo** analoghe:

$$  
\begin{cases}  
\textbf{Creazione:}~ & \text{apertura o inizializzazione della pipe/file.} \\\\  
\textbf{Scrittura:}~ & \text{invio di dati nel canale di comunicazione.} \\\\  
\textbf{Lettura:}~ & \text{ricezione dei dati scritti.} \\\\  
\textbf{Cancellazione:}~ & \text{chiusura o rimozione della pipe/file.}  
\end{cases}  
$$

Esempio in UNIX:

```bash
ls | grep ".c"
```

In questo caso:

- `ls` scrive sulla pipe;
    
- `grep` legge dalla pipe e filtra solo i file con estensione `.c`.

---
### **6. Caratteristiche e problemi**

$$  
\begin{cases}  
\textbf{Sincronizzazione:}~ & \text{gestita dal kernel (pipe) o dal file system (file condivisi).} \\\\  
\textbf{Ordinamento dei messaggi:}~ & \text{FIFO nelle pipe; dipendente dal processo scrivente nei file.} \\\\  
\textbf{Ordinamento dei processi in attesa:}~ & \text{gestito dal kernel o dal file system.}  
\end{cases}  
$$

Il principale problema comune a entrambi i modelli è la **necessità di coordinamento temporale**:

- Le pipe richiedono la presenza simultanea dei processi comunicanti.
    
- I file condivisi, invece, eliminano questo vincolo ma introducono **latenza e minore efficienza**.

---
### **7. Sintesi finale**

$$  
\begin{cases}  
\textbf{File condivisi:}~ & \text{comunicazione asincrona e persistente.} \\\\  
\textbf{Pipe:}~ & \text{comunicazione sincrona e transitoria.} \\\\  
\textbf{Ordinamento:}~ & \text{FIFO nelle pipe, dipendente dai processi nei file.} \\\\  
\textbf{Gestione:}~ & \text{kernel per pipe, file system per file condivisi.}  
\end{cases}  
$$

---
### **8. Conclusione**

I **file condivisi** e le **pipe** rappresentano due modalità complementari di comunicazione tra processi.  
Le pipe sono ideali per **processi concorrenti**, dove la comunicazione deve avvenire in tempo reale;  
i file condivisi, invece, sono perfetti per **comunicazioni differite** e **persistenti**.

Entrambi costituiscono pilastri fondamentali nella progettazione di sistemi **UNIX-like** e nei moderni **sistemi operativi multitasking**,  
dove la cooperazione tra processi è la chiave dell’efficienza complessiva del sistema.