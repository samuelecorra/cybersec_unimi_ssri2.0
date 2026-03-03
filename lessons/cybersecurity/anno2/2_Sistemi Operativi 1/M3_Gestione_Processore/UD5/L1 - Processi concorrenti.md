# **M3 UD5 Lezione 1 - Processi concorrenti**

### **1. Introduzione**

Questa lezione introduce uno dei concetti più delicati dei sistemi operativi: la **concorrenza**.  
Nei sistemi moderni, più processi (o thread) possono essere **attivi nello stesso intervallo di tempo**, condividendo risorse comuni come **variabili, file o periferiche**.

Quando due o più processi accedono **simultaneamente a una risorsa condivisa**, senza un adeguato controllo, possono verificarsi **incoerenze nei dati** o comportamenti imprevedibili.  
È per questo che la **sincronizzazione** diventa essenziale.

---
### **2. Concorrenza e risorse condivise**

Il concetto di **concorrenza** indica la possibilità che più processi vengano eseguiti _apparentemente_ nello stesso momento (in realtà, spesso alternandosi rapidamente sulla CPU).

Durante l’esecuzione concorrente, i processi possono condividere:

$$  
\begin{cases}  
\textbf{Risorse fisiche:}~ & \text{come stampanti, dischi, CPU o dispositivi di I/O.} \\\\  
\textbf{Risorse informative:}~ & \text{come variabili in memoria, file o buffer.}  
\end{cases}  
$$

Poiché queste risorse non possono essere usate da più processi _allo stesso tempo_ senza rischi, il sistema operativo deve garantire un **accesso controllato e in mutua esclusione**.

---
### **3. Il problema del produttore–consumatore**

Per capire i rischi della concorrenza, consideriamo l’esempio classico del **problema del produttore–consumatore**.

#### **3.1. Descrizione**

- Il **produttore** genera dati e li inserisce in un buffer.
    
- Il **consumatore** preleva i dati dallo stesso buffer per elaborarli.

Se entrambi accedono contemporaneamente alla **variabile `count`** (che rappresenta il numero di elementi nel buffer), senza un’adeguata sincronizzazione, il valore di `count` può diventare incoerente.

---
#### **3.2. Codice esemplificativo**

**Produttore:**

```c
while (1) {
    while (count == BUFFER_SIZE); // attesa se buffer pieno
    buffer[in] = nextProduced;
    in = (in + 1) % BUFFER_SIZE;
    count++; // incremento del contatore
}
```

**Consumatore:**

```c
while (1) {
    while (count == 0); // attesa se buffer vuoto
    nextConsumed = buffer[out];
    out = (out + 1) % BUFFER_SIZE;
    count--; // decremento del contatore
}
```

---
#### **3.3. Condizione di race (corse critiche)**

Il problema nasce quando entrambi i processi tentano di aggiornare `count` nello stesso momento.

Esecuzione possibile:

|Passo|Operazione|Effetto|
|:--|:--|:--|
|S0|Produttore legge `count = 5`|`register1 = 5`|
|S1|Produttore incrementa `register1`|`register1 = 6`|
|S2|Consumatore legge `count = 5`|`register2 = 5`|
|S3|Consumatore decrementa `register2`|`register2 = 4`|
|S4|Produttore scrive `count = register1`|`count = 6`|
|S5|Consumatore scrive `count = register2`|`count = 4`|

Il risultato finale (`count = 4`) è **errato**:  
entrambi i processi hanno aggiornato la stessa variabile senza coordinarsi.

Questo è un classico esempio di **race condition**, o _condizione di corsa_:  
il risultato dipende **dall’ordine imprevedibile di esecuzione** delle istruzioni concorrenti.

---
### **4. Sezione critica**

Una **sezione critica** è la parte di codice in cui un processo accede a una risorsa condivisa.  
Se due processi eseguono simultaneamente la loro sezione critica sulla stessa risorsa, possono verificarsi errori o incoerenze.

#### **4.1. Requisiti di correttezza**

Ogni soluzione al problema della concorrenza deve garantire tre condizioni fondamentali:

$$  
\begin{cases}  
\textbf{Mutua esclusione:}~ & \text{solo un processo per volta può eseguire la sezione critica.} \\\\  
\textbf{Progresso:}~ & \text{se nessun processo è nella sezione critica, uno dei candidati deve poter entrare.} \\\\  
\textbf{Attesa limitata:}~ & \text{un processo non deve essere rimandato indefinitamente.}  
\end{cases}  
$$

Questi principi costituiscono la base per ogni **algoritmo di sincronizzazione**.

---
### **5. Sincronizzazione e cooperazione**

La sincronizzazione non serve solo per evitare conflitti, ma anche per **coordinare processi cooperanti** che lavorano insieme per uno stesso obiettivo.

$$  
\text{Processi cooperanti} \Rightarrow \text{necessità di sincronizzare l’evoluzione della loro computazione.}  
$$

Un esempio tipico è proprio il **produttore–consumatore**, dove la sincronizzazione è indispensabile per mantenere il corretto equilibrio tra produzione e consumo.

---
### **6. Sintesi finale**

$$  
\begin{cases}  
\textbf{Concorrenza:}~ & \text{esecuzione simultanea o alternata di processi.} \\\\  
\textbf{Rischio:}~ & \text{corse critiche dovute all’accesso simultaneo alle risorse condivise.} \\\\  
\textbf{Sezione critica:}~ & \text{porzione di codice che deve essere eseguita in mutua esclusione.} \\\\  
\textbf{Soluzione:}~ & \text{tecniche di sincronizzazione basate su mutua esclusione, progresso e attesa limitata.}  
\end{cases}  
$$

---
### **7. Conclusione**

La concorrenza è inevitabile nei sistemi multitasking, ma anche potenzialmente pericolosa.  
Senza un adeguato controllo, i processi possono interferire tra loro, corrompendo i dati o bloccando il sistema.

La **sincronizzazione** è dunque lo strumento che permette di mantenere **ordine, coerenza e correttezza logica** nell’esecuzione concorrente.  
Nelle lezioni successive analizzeremo i **meccanismi concreti** che realizzano la mutua esclusione: **semafori, monitor e sezioni critiche hardware**.