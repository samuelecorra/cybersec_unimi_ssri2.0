# **M3 UD6 Lezione 1 - Caratterizzazione del Deadlock**

### **1. Introduzione**

Dopo aver affrontato la sincronizzazione dei processi, analizziamo ora uno dei problemi più pericolosi dei sistemi operativi: il **deadlock**, chiamato anche **stallo**.  
Esso si verifica quando **più processi rimangono in attesa indefinitamente**, perché ciascuno sta aspettando una risorsa **occupata da un altro processo del gruppo**.

$$  
P_1 \rightarrow R_1,\ R_1 \text{ assegnata a } P_2,\ P_2 \text{ attende } R_2,\ R_2 \text{ assegnata a } P_1  
$$

In questa situazione nessuno dei processi può continuare, e il sistema resta bloccato.

---
### **2. Uso delle risorse condivise**

In un sistema multiprogrammato, i processi fanno uso di **risorse condivise** (stampanti, file, memoria, CPU, ecc.).  
Queste risorse possono essere:

$$  
\begin{cases}  
\textbf{Non esclusive:}~ & \text{utilizzabili da più processi contemporaneamente (es. file in sola lettura).} \\\\  
\textbf{Mutuamente esclusive:}~ & \text{utilizzabili da un solo processo alla volta (es. stampante, socket, buffer).}  
\end{cases}  
$$

Per le risorse ad uso esclusivo, è necessario un **protocollo di sincronizzazione** composto da tre fasi:

$$  
\begin{cases}  
1.~ \text{Richiesta della risorsa} \\\\  
2.~ \text{Uso della risorsa} \\\\  
3.~ \text{Rilascio della risorsa}  
\end{cases}  
$$

---
### **3. Il problema del deadlock**

Un **deadlock** si verifica quando un insieme di processi rimane **in attesa reciproca** di risorse detenute dagli altri.  
In questo caso, **nessun processo può progredire** e il sistema entra in **stallo permanente**.

Esempio intuitivo:

- Il processo $P_1$ ha la **risorsa R1** e richiede la **R2**.
    
- Il processo $P_2$ ha la **risorsa R2** e richiede la **R1**.

Né $P_1$ né $P_2$ possono procedere, e il sistema resta bloccato.

---
### **4. Condizioni necessarie per il verificarsi del deadlock**

Il deadlock si verifica **solo se tutte e quattro** le seguenti condizioni si verificano simultaneamente (Coffman, 1971):

$$  
\begin{cases}  
\textbf{1. Mutua esclusione:}~ & \text{le risorse non possono essere condivise tra più processi.} \\\\  
\textbf{2. Hold and Wait (possesso e attesa):}~ & \text{un processo trattiene una risorsa mentre ne attende un’altra.} \\\\  
\textbf{3. No Pre-emption (assenza di rilascio forzato):}~ & \text{le risorse non possono essere sottratte a un processo.} \\\\  
\textbf{4. Attesa circolare (circular wait):}~ & \text{esiste un ciclo chiuso di processi in attesa reciproca.}  
\end{cases}  
$$

👉 Tutte queste condizioni devono coesistere affinché si generi un deadlock.  
Se anche una sola viene infranta, il deadlock **non può verificarsi**.

---
### **5. Rappresentazione con il grafo di allocazione delle risorse**

Per comprendere e individuare i deadlock, si utilizza un **grafo di allocazione delle risorse**.

#### **5.1. Definizione del grafo**

$$  
G(V,E)  
$$

Dove:

- $V$ è l’insieme dei nodi, suddiviso in:
    
    - processi del sistema $P = {P_1, P_2, ..., P_n}$
        
    - risorse del sistema $R = {R_1, R_2, ..., R_m}$
        
- $E$ è l’insieme degli archi che rappresentano relazioni di **richiesta** e **assegnazione**.

#### **5.2. Tipi di archi**

$$  
\begin{cases}  
\textbf{Arco di richiesta:}~ & P_i \rightarrow R_j \quad \text{(il processo richiede la risorsa)} \\\\  
\textbf{Arco di assegnazione:}~ & R_j \rightarrow P_i \quad \text{(la risorsa è assegnata al processo)}  
\end{cases}  
$$

---
### **6. Esempi di grafo**

#### **6.1. Sistema senza deadlock**

$$  
P_1 \rightarrow R_1 \rightarrow P_2  
$$

Il grafo **non contiene cicli** → nessun deadlock.

#### **6.2. Sistema con deadlock**

$$  
P_1 \rightarrow R_1 \rightarrow P_2 \rightarrow R_2 \rightarrow P_1  
$$

È presente un **ciclo chiuso**: ogni processo attende una risorsa detenuta da un altro → **deadlock attivo**.

#### **6.3. Ciclo senza deadlock**

In alcuni casi, un grafo può contenere un **ciclo** ma non rappresentare un vero deadlock,  
ad esempio quando una risorsa ha **più istanze disponibili**.

---
### **7. Metodi di gestione del deadlock**

I sistemi operativi adottano diverse strategie per affrontare o evitare il deadlock:

$$  
\begin{cases}  
\textbf{1. Ignorare il deadlock:}~ & \text{non si interviene (es. UNIX).} \\\\  
\textbf{2. Prevenzione:}~ & \text{si impedisce a priori che si verifichino le condizioni di Coffman.} \\\\  
\textbf{3. Evitamento:}~ & \text{si controllano dinamicamente le richieste per evitare stati pericolosi.} \\\\  
\textbf{4. Rilevazione e recupero:}~ & \text{si individuano i deadlock e si tenta di risolverli (es. terminazione di processi).}  
\end{cases}  
$$

---
### **8. Sintesi finale**

$$
\begin{cases}
\textbf{Definizione:}~ & \text{situazione di blocco reciproco tra processi.} \\\\
\textbf{Condizioni:}~ & \text{mutua esclusione, hold \& wait, no pre-emption, attesa circolare.} \\\\
\textbf{Rappresentazione:}~ & \text{grafo di allocazione risorse (nodi: processi e risorse).} \\\\
\textbf{Gestione:}~ & \text{ignorare, prevenire, evitare, rilevare e recuperare.}
\end{cases}
$$


---
### **9. Conclusione**

Il deadlock rappresenta una **condizione di blocco sistemico** che può compromettere l’intero funzionamento di un sistema operativo.  
Comprendere le sue **cause** e saperlo **identificare** tramite modelli grafici è il primo passo per prevenirlo o risolverlo efficacemente.

Nelle prossime lezioni studieremo in dettaglio i **metodi di gestione del deadlock**,  
analizzando come i sistemi reali (monoprocessore e multiprocessore) prevengono, evitano o rilevano questa condizione critica.