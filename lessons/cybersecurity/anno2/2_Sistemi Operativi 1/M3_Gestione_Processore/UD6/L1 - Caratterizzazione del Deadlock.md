# **M3 UD6 Lezione 1 - Caratterizzazione del Deadlock**

---

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

#### **3.1. Analogia visiva: due auto nella strettoia**

Un'analogia efficace è quella di **due automobili** che, provenendo da direzioni opposte, vogliono attraversare contemporaneamente una **strada con una strettoia** (risorsa condivisa usabile solo in mutua esclusione). Senza una regola di precedenza che le sincronizzi:

- entrambe le auto **iniziano a occupare** la strettoia da estremi opposti;
- arrivate al punto di incrocio, **nessuna delle due** può proseguire perché l'altra le blocca il passaggio;
- nessuna delle due vuole (o può, per i propri vincoli) tornare indietro.

Il risultato è uno **stallo perfetto**: il sistema (in questo caso, il traffico stradale) **resta bloccato** finché un agente esterno non interviene per risolverlo.

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

- $V$ è l'insieme dei nodi, suddiviso in:
  - processi del sistema $P = \{P_1, P_2, \dots, P_n\}$
  - risorse del sistema $R = \{R_1, R_2, \dots, R_m\}$
- $E$ è l'insieme degli archi che rappresentano relazioni di **richiesta** e **assegnazione**.

> 💡 **Risorse con più istanze.** Una risorsa $R_j$ può esistere in **più istanze identiche** (es. 2 stampanti dello stesso modello). Anche in questo caso, viene rappresentata da un **unico nodo** $R_j$ nel grafo — eventualmente con dei "puntini" interni che indicano il numero di istanze disponibili. Le assegnazioni a processi diversi sono comunque archi distinti che escono dal medesimo nodo.

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

##### **Esempio: ciclo con risorsa a due istanze**

Supponiamo che la risorsa $R_2$ esista in **2 istanze**. Una delle due è coinvolta nel ciclo $P_1 \to R_1 \to P_3 \to R_2 \to P_1$. L'altra istanza è detenuta da un processo **$P_4$ esterno al ciclo**, che non è in attesa di nessuna risorsa.

In questa configurazione, anche se il grafo presenta un ciclo:

- Quando $P_4$ termina la propria computazione, **rilascia la sua istanza** di $R_2$;
- Tale istanza può essere **assegnata a $P_3$**, che era in attesa proprio di $R_2$;
- $P_3$ può procedere, rilasciare $R_1$, e a sua volta sbloccare $P_1$;
- Il ciclo si scioglie senza che si verifichi alcun deadlock.

##### **Conclusione utile**

- **Risorse a istanza singola**: la **presenza di un ciclo** nel grafo è **condizione necessaria e sufficiente** per il deadlock.
- **Risorse a più istanze**: la presenza di un ciclo è **condizione necessaria ma non sufficiente** — bisogna analizzare anche la distribuzione delle istanze fuori dal ciclo per determinare se il deadlock è effettivo.

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