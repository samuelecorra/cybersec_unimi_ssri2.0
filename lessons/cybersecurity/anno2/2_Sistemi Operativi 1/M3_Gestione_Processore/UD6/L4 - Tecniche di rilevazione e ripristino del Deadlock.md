# **M3 UD6 Lezione 4 - Tecniche di rilevazione e ripristino del Deadlock**

### **1. Introduzione**

Dopo aver studiato la **prevenzione** e l’**evitamento** del deadlock, concludiamo l’analisi affrontando il caso in cui **il deadlock si sia già verificato**.  
In questi scenari, il sistema operativo deve:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Rilevare la presenza del deadlock.} \\\\  
\textbf{2.}~ & \text{Ripristinare una situazione di corretto funzionamento.}  
\end{cases}  
$$

L’obiettivo è **identificare i processi coinvolti** nello stallo e **liberare le risorse bloccate**,  
in modo da riportare il sistema a uno stato operativo coerente.

---
### **2. Principio di rilevazione e ripristino**

Senza algoritmi di prevenzione o evitamento, il deadlock può **effettivamente verificarsi**.  
Il sistema operativo deve dunque essere in grado di:

- **rilevare** la presenza di un deadlock, dopo che esso si è manifestato;
    
- **intervenire** per ripristinare la normalità, interrompendo o sbloccando i processi coinvolti.

A seconda del **tipo di risorse** presenti nel sistema, la strategia di rilevazione cambia:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Rilevazione per sistemi con istanze singole delle risorse.} \\\\  
\textbf{2.}~ & \text{Rilevazione per sistemi con istanze multiple.}  
\end{cases}  
$$

---
### **3. Rilevazione per sistemi con istanze singole**

#### **3.1. Il grafo di attesa (Wait-for Graph)**

Quando ogni risorsa ha **una sola istanza**, la rilevazione può essere effettuata tramite il **grafo di attesa** (_wait-for graph_).

Questo grafo è derivato dal grafo di allocazione delle risorse, ma **contiene solo processi** come nodi.

$$  
P_i \rightarrow P_j \quad \text{se } P_i \text{ è in attesa di una risorsa posseduta da } P_j  
$$

#### **3.2. Regola di rilevazione**

$$  
\text{Se il grafo di attesa contiene un ciclo} \Rightarrow \text{si è verificato un deadlock.}  
$$

- I processi coinvolti nel ciclo sono **bloccati reciprocamente**.
    
- L’analisi del grafo è quindi sufficiente per individuare i **processi in stallo**.

---
### **4. Rilevazione per sistemi con istanze multiple**

Quando le risorse hanno **più istanze**, il grafo non è più sufficiente.  
Serve un **algoritmo di rilevazione completo**, analogo all’algoritmo del banchiere, ma più semplice e focalizzato sull’identificazione dello stallo.

#### **4.1. Strutture dati utilizzate**

$$  
\begin{cases}  
\textbf{Available}[1..m]~ & \text{vettore delle risorse disponibili.} \\\\  
\textbf{Allocation}[1..n,1..m]~ & \text{matrice delle risorse attualmente assegnate.} \\\\  
\textbf{Request}[1..n,1..m]~ & \text{matrice delle risorse attualmente richieste.}  
\end{cases}  
$$

Dove:

- $m$ è il numero di tipi di risorse,
    
- $n$ è il numero di processi del sistema.

---
#### **4.2. Algoritmo di rilevazione**

$$  
\begin{cases}  
\textbf{1. Inizializzazione:} & Work = Available; \\\\  
& \text{per ogni } i,~ Finish[i] =  
\begin{cases}  
false, & \text{se } Allocation[i] \neq 0 \\\\  
true, & \text{se } Allocation[i] = 0  
\end{cases} \\\\\\  
\textbf{2. Ricerca:} & \text{trova un processo } i \text{ tale che } Finish[i] = false \text{ e } Request[i] \le Work. \\\\\\  
\textbf{3. Simulazione:} & \text{se trovato, imposta } Work = Work + Allocation[i],~ Finish[i] = true. \\\  
& \text{ripeti dal passo 2.} \\\\\\  
\textbf{4. Rilevazione:} & \text{se esiste un } i \text{ con } Finish[i] = false, \text{ allora } P_i \text{ è in deadlock.}  
\end{cases}  
$$

L’algoritmo è simile a quello del banchiere, ma non verifica la sicurezza:  
**identifica solo gli stati già bloccati**.

---
### **5. Frequenza di rilevazione**

La rilevazione del deadlock può essere eseguita in diversi momenti, a seconda delle politiche di sistema.

$$  
\begin{cases}  
\textbf{1. Rilevazione immediata:}~ & \text{ogni volta che una richiesta non può essere soddisfatta.} \\  
& \text{→ più precisa ma molto costosa in termini di calcolo.} \\\\  
\textbf{2. Rilevazione periodica:}~ & \text{a intervalli di tempo prestabiliti.} \\  
& \text{→ meno precisa ma con minor sovraccarico.}  
\end{cases}  
$$

#### **Trade-off**

- Rilevazione frequente → risposta rapida ma costo elevato.
    
- Rilevazione rara → efficienza maggiore ma rischio di accumulare molti processi bloccati.

---
### **6. Ripristino del deadlock**

Una volta rilevato il deadlock, il sistema deve **ripristinare il corretto funzionamento** liberando le risorse coinvolte.  
Le principali strategie sono due:

$$  
\begin{cases}  
\textbf{1. Terminare i processi in deadlock.} \\\\  
\textbf{2. Rilasciare anticipatamente le risorse (pre-emption).}  
\end{cases}  
$$

---
### **7. Terminazione dei processi**

#### **7.1. Due modalità operative**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Terminare tutti i processi in deadlock.} \\  
& \text{→ Soluzione rapida ma molto costosa.} \\\\  
\textbf{2.}~ & \text{Terminare un processo alla volta fino a risolvere lo stallo.} \\  
& \text{→ Richiede più tempo, ma limita le perdite.}  
\end{cases}  
$$

#### **7.2. Criteri di selezione**

La scelta di quali processi terminare dipende da diversi criteri:

$$  
\begin{cases}  
\text{Priorità del processo;} \\\\  
\text{Tempo di esecuzione accumulato;} \\\\  
\text{Quantità di risorse già utilizzate;} \\\\  
\text{Numero di risorse ancora richieste;} \\\\  
\text{Impatto sul sistema (interattivo o batch).}  
\end{cases}  
$$

---
### **8. Rilascio anticipato delle risorse**

In alternativa alla terminazione dei processi, il sistema può **forzare il rilascio delle risorse**.

#### **8.1. Selezione della vittima**

Si identifica una **vittima** (processo o risorsa) da cui sottrarre temporaneamente la risorsa contesa.  
La scelta avviene in base al **costo minimo complessivo** per il sistema.

$$  
\text{Vittima} = \arg\min(\text{costo di rilascio e ripristino})  
$$

#### **8.2. Rollback**

Una volta scelta la vittima:

- si effettua un **rollback** (ripristino) all’ultimo stato coerente del processo;
    
- il rollback può essere **totale** (riavvio completo) o **parziale** (solo fino al punto sicuro).

#### **8.3. Problema della starvation**

Bisogna evitare che lo stesso processo venga selezionato come vittima più volte.  
Si introduce quindi un meccanismo di **rotazione o priorità dinamica** per evitare blocchi infiniti.

---
### **9. Sintesi finale**

$$  
\begin{cases}  
\textbf{Rilevazione:}~ & \text{analisi del sistema dopo l’avvenuto stallo.} \\\\  
\textbf{Istanze singole:}~ & \text{uso del grafo di attesa → presenza di cicli = deadlock.} \\\\  
\textbf{Istanze multiple:}~ & \text{uso dell’algoritmo con matrici (Available, Allocation, Request).} \\\\  
\textbf{Ripristino:}~ & \text{terminazione dei processi o rilascio forzato delle risorse.} \\\\  
\textbf{Trade-off:}~ & \text{velocità di intervento vs costo computazionale.}  
\end{cases}  
$$

---
### **10. Conclusione**

Il **rilevamento e ripristino del deadlock** costituiscono l’ultima linea di difesa di un sistema operativo.  
Quando prevenzione ed evitamento non bastano, il sistema deve essere capace di:

- **riconoscere lo stallo**,
    
- **identificare i processi coinvolti**,
    
- **intervenire selettivamente** per liberare le risorse.

Tuttavia, queste tecniche hanno un costo computazionale elevato e, nella pratica, vengono usate **solo in sistemi critici** o **in presenza di forti garanzie di sicurezza**.  
I sistemi operativi generici, come UNIX o Windows, preferiscono invece **ignorare il problema**, confidando nella **terminazione dei processi bloccati**.

---
## **Conclusione del Modulo 3 – Gestione del processore**

Con questa lezione si conclude il **Modulo 3**, dedicato ai **meccanismi fondamentali di gestione del processore**.  
Abbiamo esplorato il cuore del funzionamento del sistema operativo:

$$  
\begin{cases}  
\textbf{Processi e thread}~ & \text{come entità di esecuzione.} \\\\  
\textbf{Schedulazione}~ & \text{come politica di distribuzione della CPU.} \\\\  
\textbf{Comunicazione e sincronizzazione}~ & \text{come base della cooperazione tra processi.} \\\\  
\textbf{Deadlock}~ & \text{come limite estremo della concorrenza.}  
\end{cases}  
$$

Comprendere questi concetti significa **capire come il sistema operativo dà vita alla CPU**,  
trasformando il caos della concorrenza in **ordine, controllo e affidabilità**.

E non solo. Abbiamo finito l'intero Insegnamento di Sistemi Operativi 1!