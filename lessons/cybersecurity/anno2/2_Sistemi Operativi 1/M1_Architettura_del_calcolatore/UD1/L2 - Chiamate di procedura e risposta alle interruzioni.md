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

Durante una **chiamata di procedura**, il processore e il sistema operativo collaborano per preparare il nuovo contesto di esecuzione.  
Tutte le informazioni necessarie vengono gestite tramite una **struttura dati fondamentale: lo stack (pila)**.

Lo stack è una memoria gestita in modalità **LIFO (Last In, First Out)**: l’ultimo elemento inserito è il primo a essere rimosso.

All'atto pratico, non è altro che una PORZIONE di memoria centrale, per la quale viene identificata una **BASE** a partire dalla quale verranno accumulate le varie informazioni necessarie a supportare le varie chiamate.

La cima dello stack così raggiunta è individuata nel registro STACK POINTER (SP) in modo da supportare efficacemente all'interno del processore l'accesso alla memoria stessa gestito in tale modalità.

![](imgs/Pasted%20image%2020260523175359.png)

Quando il chiamante decide di iniziare la chiamata della nuova procedura disporrà sulla cima dello stack spazio sufficiente a conservare i valori attuali dei parametri formali, ovvero i parametri su cui la procedura chiamata dovrà operare; la cima dello stack CRESCE.

![](imgs/Pasted%20image%2020260523181238.png)

Dopodiché, per conservare il riferimento al vecchio ambiente di lavoro dove operava il chiamante, sulla nuova cima deponiamo il valore old di BP; tale cima diventa il nuovo valore di BP costituendo dunque il riferimento rispetto al quale verranno valutate le posizioni dei parametri formali, dei valori attuali di essi e la posizione delle variabili locali.

Finalmente la procedura viene attivata con l'istruzione macchina di CALL. Tale operazione pone in cima allo stack l'indirizzo di ritorno, ovvero quella immediatamente successiva a quella di chiamata.

![](imgs/Pasted%20image%2020260523181632.png)

Inoltre si pone nel program counter il valore della prima istruzione della procedura chiamata; il program counter forza così la CPU a saltare all'esecuzione della prima istruzione della subroutine.

Tale prima istruzione provvede a completare la creazione dell'ambiente di lavoro, riservando spazio per le variabili locali sulla nuova cima dello stack.

Finalmente, si può apporre in cima il nuovo puntatore SP.

![](imgs/Pasted%20image%2020260523182023.png)

Nella loro interezza, questi layer costituiscono il contesto della procedura chiamata.

La procedura chiamata così procede nelle sue operazioni andando a utilizzare tutte le informazioni poste sullo stack nel contesto di attivazione relativo. In particolare, andrà ad utilizzare i valori attuali dei parametri formali, andrà ad utilizzare il riferimento al vecchio base pointer, andrà ad utilizzare il riferimento al nuovo base pointer - per calcolare le posizioni dei valori attuali dei parametri formali, partendo dal base pointer e andando verso indirizzi crescenti di memoria -, andrà ad utilizzare l'indirizzo di ritorno quando servirà e infine andrà a utilizzare le variabili locali durante le varie operazioni.  
  
La posizione delle variabili locali sarà calcolata sempre utilizzando come riferimento il base pointer del contesto corrente.  

A questo punto, eseguite tutte le operazioni della procedura chiamata, si deve terminare la chiamata stessa.  
  
Per fare questo si comincia a rimuovere le variabili locali, perché ormai non servono più; si mette in mostra a questo punto l'indirizzo di ritorno e quindi l'esecuzione dell'istruzione di end della chiamata di procedura prende il contenuto della cima dello stack, cioè l'indirizzo di ritorno e lo ripristina nel programma counter, tornando così ad eseguire la prima istruzione successiva alla chiamata di procedura all'interno della procedura chiamante. (foto qui sotto)

![](imgs/Pasted%20image%2020260523183014.png)

Queste istruzione ripristina il contesto di attivazione della procedura chiamante scaricando nel base pointer il vecchio valore del base pointer. (foto sotto)

![](imgs/Pasted%20image%2020260523183143.png)
  
I valori attuali dei parametri formali a questo punto non servono più perché la procedura è stata chiamata e completata e quindi possono essere semplicemente rimossi. Si torna così alla posizione dello stack pointer prima della chiamata. (foto sotto)

![](imgs/Pasted%20image%2020260523183356.png)
  
Come si può effettuare il ritorno di un valore da parte della procedura chiamata alla procedura chiamante?

Possono essere usati i registri se questo è sufficiente -- oppure si può utilizzare un'opportuna zona di memoria sulla cima dello stack.

Ad esempio, prima dei valori dei parametri formali viene riservato uno spazio per il valore ritornato dalla procedura chiamata, poi verrà lasciato lo spazio per il valore attuale dei parametri formali, poi verrà posto il base pointer vecchio in memoria sullo stack e verrà posizionato il nuovo valore di base pointer della procedura chiamante - verrà messo l'indirizzo di ritorno - verranno messe le variabili locali.

![](imgs/Pasted%20image%2020260523183808.png)
  
Questo sarà il nuovo ambiente di lavoro della procedura chiamata; verranno eseguite tutte le operazioni e il risultato verrà deposto in questa zona di memoria per il valore ritornato.

Quando vengono scaricate tutte le informazioni previste dal contesto della procedura chiamata per ritornare alla procedura chiamante, il valore ritornato occuperà il primo spazio al di sopra dell'ambiente di attivazione della procedura chiamante, la quale saprà recuperare tale valore e porlo nelle proprie variabili.

---
### **4. Gestione del contesto e dello stack**

#### **4.1. Il concetto di contesto**

Il **contesto** rappresenta l’insieme delle informazioni che descrivono lo stato del processore in un dato momento:  
registri, contatore di programma $PC$, puntatore di stack $SP$, puntatore di base $BP$, flag e così via.  
Vive concettualmente **dentro la CPU**, ed è in sostanza una *fotografia istantanea* dei suoi registri.

Durante una chiamata di procedura:

- il contesto del chiamante viene salvato sullo stack;
    
- la procedura chiamata crea il proprio contesto;
    
- al termine, il contesto precedente viene ripristinato.

#### **4.2. Stack frame**

Ogni procedura attiva genera uno **stack frame**, ossia un **blocco di memoria sulla pila** che raccoglie tutte le informazioni relative a una singola attivazione della procedura:

1. i parametri di ingresso,
    
2. il valore di ritorno,
    
3. il puntatore di base al frame precedente (vecchio $BP$),
    
4. l’indirizzo di ritorno,
    
5. le variabili locali.

Lo stack cresce o decresce (a seconda dell’architettura) a ogni chiamata o ritorno di procedura, mantenendo un ordine perfetto tra le attivazioni.

#### **4.3. Contesto e stack frame: due entità distinte**

I termini *contesto* e *stack frame* vengono spesso usati insieme, ma indicano cose diverse:

| Entità | Dove vive | Cosa rappresenta |
| --- | --- | --- |
| **Contesto** | nei registri della CPU | stato istantaneo del processore |
| **Stack frame** | in memoria centrale, sullo stack | ambiente di una specifica chiamata di procedura |

Il legame fra i due è puramente **operativo**: quando il contesto viene "salvato", i valori dei registri vengono scritti sullo stack, spesso proprio all’interno di uno stack frame. Ma le due entità restano logicamente distinte — il contesto è uno *stato*, lo stack frame è una *struttura dati*.

#### **4.4. Stack frame e "record di attivazione"**

Il termine italiano **record di attivazione** è di fatto sinonimo di *stack frame*: indicano la stessa struttura dati. La sfumatura è solo terminologica:

- *record di attivazione* è il termine **astratto**, proveniente dalla teoria dei compilatori e dei linguaggi di programmazione;
    
- *stack frame* è il termine **implementativo**, che sottolinea la realizzazione concreta sullo stack.

È utile chiarire il significato dei due vocaboli inglesi, perché entrambi possono trarre in inganno:

- **Frame** non va inteso come il *frame* di una trama di rete (es. Ethernet), ma nel significato di **"cornice / blocco strutturato che delimita una regione"** — la stessa accezione di *picture frame* o di *page frame* nella gestione della memoria: un’area delimitata che racchiude un insieme coerente di dati.
    
- **Record** non va inteso come *primato* o *bestscore*, ma nel significato informatico classico di **struttura dati aggregata con campi eterogenei**, analoga a uno `struct` in C o a un `record` in Pascal. I suoi "campi" sono esattamente quelli elencati al §4.2: parametri, valore di ritorno, vecchio $BP$, indirizzo di ritorno, variabili locali.

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

![](imgs/Pasted%20image%2020260523185723.png)

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

![](imgs/Pasted%20image%2020260523190118.png)

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