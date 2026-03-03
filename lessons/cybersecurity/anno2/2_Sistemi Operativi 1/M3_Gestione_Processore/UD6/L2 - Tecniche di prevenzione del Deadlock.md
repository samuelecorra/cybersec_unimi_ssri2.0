# **M3 UD6 Lezione 2 - Tecniche di prevenzione del Deadlock**

### **1. Introduzione**

La **prevenzione del deadlock** consiste nel progettare il sistema in modo che **almeno una** delle quattro condizioni di Coffman **non possa mai verificarsi**.  
In altre parole, il sistema operativo impedisce a priori che si crei una situazione di stallo.

$$  
\text{Deadlock evitato se almeno una tra le seguenti è falsa:}  
$$

$$  
\begin{cases}  
\textbf{1.}~ \text{Mutua esclusione} \\\\  
\textbf{2.}~ \text{Possesso e attesa} \\\\  
\textbf{3.}~ \text{Nessun rilascio anticipato} \\\\  
\textbf{4.}~ \text{Attesa circolare}  
\end{cases}  
$$

L’obiettivo è quindi **rompere il ciclo delle dipendenze** tra processi, modificando le regole di gestione delle risorse.

---
### **2. Mutua esclusione**

#### **2.1. Principio**

La condizione di **mutua esclusione** è necessaria solo per le risorse che **non possono essere condivise** (come stampanti o dispositivi di I/O).

$$  
\begin{cases}  
\textbf{Risorse condivisibili:}~ & \text{rimuovere la mutua esclusione → nessun problema.} \\\\  
\textbf{Risorse non condivisibili:}~ & \text{la condizione deve essere mantenuta.}  
\end{cases}  
$$

#### **2.2. Applicazione pratica**

- Per le risorse **intrinsecamente condivisibili** (es. file in sola lettura, segmenti di memoria condivisa),  
    il sistema può permettere l’uso simultaneo da parte di più processi.
    
- Per le risorse **non condivisibili**, la condizione di mutua esclusione **non può essere rimossa**  
    — si dovranno quindi modificare altre condizioni per prevenire il deadlock.

---
### **3. Possesso e attesa (Hold and Wait)**

#### **3.1. Principio**

Questa condizione può essere eliminata **impedendo che un processo possieda risorse mentre ne richiede altre**.

$$  
\text{Un processo può chiedere risorse solo se non ne possiede già.}  
$$

#### **3.2. Tecniche operative**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Ogni processo richiede tutte le risorse necessarie prima di iniziare l’esecuzione.} \\\\  
\textbf{2.}~ & \text{Se un processo deve richiedere nuove risorse, deve prima rilasciare quelle già possedute.} \\\\  
\textbf{3.}~ & \text{Dopo il rilascio, può richiedere l’intero insieme (vecchie + nuove).}  
\end{cases}  
$$

#### **3.3. Problemi**

Queste tecniche, seppur efficaci, introducono due problemi:

- **Scarso utilizzo delle risorse:** risorse assegnate ma inutilizzate in attesa di altre richieste.
    
- **Possibile starvation:** un processo può essere rimandato indefinitamente perché non riesce ad ottenere tutte le risorse contemporaneamente.

---
### **4. Nessun rilascio anticipato (No Pre-emption)**

#### **4.1. Principio**

Si può eliminare questa condizione **consentendo al sistema di rilasciare forzatamente** le risorse di un processo,  
a patto che il loro stato possa essere **ripristinato successivamente**.

$$  
\text{Pre-emption possibile solo per risorse ripristinabili (es. CPU, memoria).}  
$$

#### **4.2. Tecniche operative (1)**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Se un processo detiene alcune risorse e ne chiede altre non disponibili:} \\  
& \text{→ tutte le risorse possedute vengono rilasciate anticipatamente.} \\  
& \text{→ le risorse rilasciate si aggiungono a quelle richieste.} \\  
& \text{→ il processo riparte solo quando può ottenere tutte le risorse (vecchie + nuove).}  
\end{cases}  
$$

#### **4.3. Tecniche operative (2)**

$$  
\begin{cases}  
\textbf{2.}~ & \text{Se le risorse richieste sono assegnate a processi che attendono altre risorse,} \\  
& \text{→ le risorse vengono rilasciate e assegnate al processo richiedente.} \\\\  
\textbf{3.}~ & \text{Se le risorse richieste non sono disponibili e non sono in attesa,} \\  
& \text{→ il processo richiedente deve attendere la loro liberazione.}  
\end{cases}  
$$

Queste soluzioni richiedono **complessità di gestione elevata**, ma sono molto efficaci per evitare stalli persistenti.

---
### **5. Attesa circolare (Circular Wait)**

#### **5.1. Principio**

Per evitare l’attesa circolare, è sufficiente **imporre un ordine totale sulle risorse**.  
Ogni processo deve richiedere le risorse **seguendo quell’ordine**, senza mai “tornare indietro”.

$$  
R_1 < R_2 < R_3 < ... < R_n  
$$

#### **5.2. Tecnica operativa (1)**

$$  
\begin{cases}  
\text{Un processo può chiedere risorse } R_j \text{ solo se detiene risorse } R_i \text{ con } i < j. \\\\  
\text{Se la risorsa } R_j \text{ non è disponibile, il processo attende.}  
\end{cases}  
$$

#### **5.3. Tecnica operativa (2)**

$$  
\begin{cases}  
\text{Se un processo detiene risorse } R_i \text{ con } i \ge j, \text{ allora deve:} \\\\  
\text{1. rilasciare tutte le risorse } R_i \\  
\text{2. richiedere tutte le istanze di } R_j \text{ (vecchie + nuove)} \\\\  
\text{3. richiedere poi le risorse } R_i \text{ con } i > j  
\end{cases}  
$$

Questo metodo **rompe la catena circolare delle attese**, eliminando la possibilità di deadlock.

---
### **6. Sintesi finale**

$$  
\begin{cases}  
\textbf{Mutua esclusione:}~ & \text{eliminabile solo per risorse condivisibili.} \\\\  
\textbf{Possesso e attesa:}~ & \text{evitata richiedendo tutte le risorse in anticipo.} \\\\  
\textbf{Nessun rilascio anticipato:}~ & \text{rimosso tramite pre-emption controllata.} \\\\  
\textbf{Attesa circolare:}~ & \text{evitata imponendo un ordinamento globale sulle risorse.}  
\end{cases}  
$$

---
### **7. Conclusione**

La **prevenzione del deadlock** agisce in fase di **progettazione del sistema**,  
garantendo che le condizioni necessarie al suo verificarsi non possano mai coesistere.

Tuttavia, queste tecniche comportano spesso **un compromesso tra sicurezza ed efficienza**:  
un sistema troppo rigido può diventare **sottoutilizzato** o introdurre **starvation**.

Per questo, nella pratica, la prevenzione è spesso integrata con tecniche di **evitamento dinamico**,  
che analizzeremo nella prossima lezione.