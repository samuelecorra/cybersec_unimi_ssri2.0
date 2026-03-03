# **M1 UD1 Lezione 2 - Chiamate di procedura e risposta alle interruzioni**

### **1. Introduzione**

#### **1.1. Dalla macchina sequenziale al controllo del flusso**

Nella lezione precedente abbiamo visto che il processore segue un ciclo regolare composto da tre fasi: **fetch**, **decode** ed **execute**.  
Finché l’esecuzione procede in modo lineare, il comportamento del calcolatore è prevedibile e deterministico.

Ma nella realtà, i programmi non sono una semplice sequenza di istruzioni: devono **chiamare funzioni**, **reagire a eventi esterni** e **sospendere temporaneamente il proprio flusso**.  
Per questo entrano in gioco due meccanismi fondamentali:

- le **chiamate di procedura** (che rappresentano salti controllati e reversibili nel programma);
    
- le **interruzioni (interrupt)**, che rappresentano salti **asincroni**, imposti da eventi esterni o dal sistema.

Entrambi implicano un concetto chiave: **salvare e ripristinare il contesto** del processore.

---
### **2. Chiamata di procedura**

#### **2.1. Concetto di procedura**

Una **procedura** (o funzione, o subroutine) è un blocco di codice che realizza un compito specifico e che può essere invocato da altri punti del programma.  
Serve per:

- organizzare il codice in modo modulare,
    
- evitare ripetizioni,
    
- e migliorare la leggibilità e la manutenzione.
    

Quando una procedura viene chiamata, il processore deve:

1. **salvare lo stato corrente** del programma chiamante;
    
2. **passare i parametri** necessari alla procedura chiamata;
    
3. **eseguire il codice della procedura**;
    
4. **ritornare** al punto di partenza, ripristinando il contesto originale.

---
### **3. Realizzazione della chiamata di procedura**

#### **3.1. Fase di chiamata (Call)**

Durante una **chiamata di procedura**, il processore e il sistema operativo collaborano per preparare il nuovo contesto di esecuzione.  
Tutte le informazioni necessarie vengono gestite tramite una **struttura dati fondamentale: lo stack (pila)**.

Lo stack è una memoria gestita in modalità **LIFO (Last In, First Out)**: l’ultimo elemento inserito è il primo a essere rimosso.  
Ogni nuova procedura crea un proprio **record di attivazione** sullo stack, che contiene:

- i **parametri di ingresso**,
    
- le **variabili locali**,
    
- l’**indirizzo di ritorno** (cioè dove riprendere l’esecuzione),
    
- e il **puntatore al contesto precedente**.

Il flusso logico è il seguente:

$$
\begin{cases}
\textbf{1.}~ \text{Il programma chiamante carica sullo stack i parametri della procedura.} \\\\
\textbf{2.}~ \text{Salva il riferimento al contesto corrente (Push BP).} \\\\
\textbf{3.}~ \text{Aggiorna il puntatore di base (Move BP, SP).} \\\\
\textbf{4.}~ \text{Invoca la procedura (Call MIA\_PROCEDURA).} \\\\
\textbf{5.}~ \text{La procedura crea le variabili locali e inizia l’esecuzione.}
\end{cases}
$$

In pratica, ogni chiamata comporta un salto nel codice, ma un salto **controllato**, perché tutto lo stato precedente viene salvato sullo stack, permettendo al programma di tornare indietro con precisione.

---
#### **3.2. Fase di ritorno (Return)**

Quando la procedura termina, il processore deve **ripristinare il contesto del chiamante** e riprendere l’esecuzione da dove si era fermato.

$$  
\begin{cases}  
\textbf{1.} & \text{La procedura rimuove le variabili locali dallo stack.} \\\\  
\textbf{2.} & \text{Ripristina il contesto della procedura chiamante (Pop BP).} \\\\  
\textbf{3.} & \text{Rimuove i parametri formali e libera lo spazio sullo stack.} \\\\  
\textbf{4.} & \text{Ritorna al punto di chiamata (istruzione RET o equivalente).}  
\end{cases}  
$$

Il valore eventualmente calcolato dalla procedura viene restituito attraverso registri specifici (come $AX$ nei processori x86) o tramite lo stack stesso.

Questo meccanismo è perfettamente simmetrico: **ogni `CALL` ha un corrispondente `RET`**, e l’uso dello stack garantisce che i ritorni avvengano in ordine inverso rispetto alle chiamate.

---
### **4. Gestione del contesto e dello stack**

#### **4.1. Il concetto di contesto**

Il **contesto** rappresenta l’insieme delle informazioni che descrivono lo stato del processore in un dato momento:  
registri, contatore di programma $PC$, puntatore di stack $SP$, puntatore di base $BP$, flag e così via.

Durante una chiamata di procedura:

- il contesto del chiamante viene salvato sullo stack;
    
- la procedura chiamata crea il proprio contesto;
    
- al termine, il contesto precedente viene ripristinato.

#### **4.2. Stack frame**

Ogni procedura attiva genera uno **stack frame**, ossia un blocco logico sullo stack contenente:

1. i parametri di ingresso,
    
2. il valore di ritorno,
    
3. il puntatore di base al frame precedente,
    
4. le variabili locali.

Lo stack cresce o decresce (a seconda dell’architettura) a ogni chiamata o ritorno di procedura, mantenendo un ordine perfetto tra le attivazioni.

---
### **5. Risposta alle interruzioni**

#### **5.1. Analogia con la chiamata di procedura**

Il meccanismo di **interruzione (interrupt)** è concettualmente simile a una chiamata di procedura automatica, ma con alcune differenze essenziali:

- la chiamata di procedura è **volontaria e sincrona** (decisa dal programma),
    
- l’interruzione è **imposta e asincrona** (provocata da un evento esterno o interno).

In entrambi i casi, il processore deve:

1. **sospendere il contesto corrente**,
    
2. **salvare lo stato** del sistema,
    
3. **passare a una nuova routine** (procedura o gestore di interrupt),
    
4. **ripristinare il contesto** al termine.

---
#### **5.2. Fasi della risposta a un’interruzione**

Quando si verifica un interrupt, il processore segue questo schema logico:

$$  
\begin{cases}  
\textbf{1.} & \text{L'hardware salva automaticamente lo stato corrente del processore.} \\\\  
\textbf{2.} & \text{Le interruzioni vengono temporaneamente disabilitate per evitare conflitti.} \\\\  
\textbf{3.} & \text{Il sistema salva il contesto del programma in esecuzione (Push All).} \\\\  
\textbf{4.} & \text{Viene creata la nuova area di lavoro (variabili locali dell’interrupt handler).} \\\\  
\textbf{5.} & \text{Si esegue la routine di servizio (ISR – Interrupt Service Routine).} \\\\  
\textbf{6.} & \text{Terminata la routine, le variabili locali vengono rimosse.} \\\\  
\textbf{7.} & \text{Il contesto salvato viene ripristinato (Pop All).} \\\\  
\textbf{8.} & \text{Lo hardware ripristina lo stato del processore e riabilita le interruzioni.} \\\\  
\textbf{9.} & \text{Il programma interrotto riprende dal punto esatto in cui era stato sospeso.}  
\end{cases}  
$$

Il tutto avviene in pochissimi cicli macchina, senza intervento esplicito del programmatore.  
Il **sistema operativo** si occupa di fornire la logica di gestione e priorità delle varie interruzioni.

---
#### **5.3. Differenze operative**

| Aspetto           | Chiamata di procedura | Interruzione                   |
| ----------------- | --------------------- | ------------------------------ |
| Origine           | Interna al programma  | Esterna o asincrona            |
| Controllo         | Volontario            | Imposto dall’hardware o dal SO |
| Gestione stack    | Esplicita (CALL/RET)  | Automatica (hardware)          |
| Salvataggio stato | Parziale (BP, SP, PC) | Completo (tutti i registri)    |
| Scopo             | Esecuzione modulare   | Reazione a eventi o errori     |

---
### **6. Collegamento con il sistema operativo**

Le **chiamate di procedura** e le **interruzioni** costituiscono la base della **gestione del controllo** in un sistema operativo.  
Ogni volta che un processo invoca un servizio del sistema operativo, lo fa tramite una **system call**, che a livello hardware **genera un’interruzione software**:  
il processore passa temporaneamente dal **modo utente** al **modo supervisore (kernel mode)**, consentendo al SO di eseguire operazioni privilegiate.

Questo meccanismo permette di:

- isolare i processi dagli accessi diretti all’hardware;
    
- garantire la sicurezza e la protezione delle risorse;
    
- mantenere il controllo del sistema anche in presenza di errori o eventi imprevisti.

---
### **7. Sintesi finale**

$$  
\begin{cases}  
\textbf{Chiamata di procedura:} & \text{salvataggio parziale del contesto e salto controllato nello stack} \\\\  
\textbf{Risposta all’interruzione:} & \text{salvataggio completo del contesto e salto asincrono gestito dall’hardware} \\\\  
\textbf{In comune:} & \text{entrambi sospendono temporaneamente un flusso di esecuzione per eseguire un altro compito} \\\\ 
\textbf{Differenza principale:} & \text{una è volontaria, l’altra è imposta da eventi esterni o interni}  
\end{cases}  
$$

In sintesi, **chiamate di procedura e interruzioni** sono due facce dello stesso principio:  
la **gestione ordinata del flusso di controllo**, che consente al sistema operativo di coordinare più attività, mantenendo intatta la coerenza dello stato della macchina.