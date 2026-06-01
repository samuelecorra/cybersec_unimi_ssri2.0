# **M3 UD5 Lezione 1 - Processi concorrenti**

---

### **1. Introduzione**

Questa lezione introduce uno dei concetti più delicati dei sistemi operativi: la **concorrenza**.  
Nei sistemi moderni, più processi (o thread) possono essere **attivi nello stesso intervallo di tempo**, condividendo risorse comuni come **variabili, file o periferiche**.

Quando due o più processi accedono **simultaneamente a una risorsa condivisa**, senza un adeguato controllo, possono verificarsi **incoerenze nei dati** o comportamenti imprevedibili.  
È per questo che la **sincronizzazione** diventa essenziale.

---

### **2. Concorrenza e risorse condivise**

Il concetto di **concorrenza** indica la possibilità che più processi vengano eseguiti _apparentemente_ nello stesso momento (in realtà, spesso alternandosi rapidamente sulla CPU).

Durante l'esecuzione concorrente, i processi possono condividere:

$$
\begin{cases}
\textbf{Risorse fisiche:}~ & \text{come stampanti, dischi, CPU o dispositivi di I/O.} \\\\
\textbf{Risorse informative:}~ & \text{come variabili in memoria, file o buffer.}
\end{cases}
$$

Poiché queste risorse non possono essere usate da più processi _allo stesso tempo_ senza rischi, il sistema operativo deve garantire un **accesso controllato e in mutua esclusione**.

#### **2.1. Esempio intuitivo: la stampante condivisa**

Immaginiamo due processi P e Q che vogliono stampare contemporaneamente sulla stessa stampante. Senza alcuna sincronizzazione, il tabulato potrebbe diventare un'**alternanza caotica** di porzioni di stampa di P e di Q:

```text
[riga 1 di P]
[riga 1 di Q]
[riga 2 di P]
[riga 2 di Q]
...
```

Il risultato sarebbe **illeggibile per entrambi**. La soluzione corretta è **sincronizzare** i due processi così che la risorsa condivisa venga utilizzata in modo da produrre **prima tutta l'uscita di P** e **poi tutta l'uscita di Q** (o viceversa), mantenendo le due stampe **chiaramente separate**.

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
// Definiamo le variabili su cui operiamo nell'esempio, altrimenti
// non si capisce nada....
#define BUFFER_SIZE 10
int buffer[BUFFER_SIZE]; // buffer condiviso
// Supponiamo che nextProduced sia una variabile che contiene il prossimo elemento da produrre

int count = 0; // numero di elementi nel buffer
int in = 0;   // indice di inserimento
int out = 0;  // indice di estrazione


while (1) {
    while (count == BUFFER_SIZE){
        do_nothing(); // attesa se buffer pieno
    }; // Quando il buffer non è pieno usciamo dal ciclo di attesa!
    buffer[in] = nextProduced; // inserimento del dato nel buffer
    in = (in + 1) % BUFFER_SIZE; // aggiornamento dell'indice di inserimento, il modulo serve a farlo tornare a 0 quando raggiunge BUFFER_SIZE così da mantenere il buffer circolare e inserire i nuovi dati al posto di quelli vecchi che sono stati consumati, evitando anche out of bounds
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

##### **Perché la sequenza non è atomica**

L'istruzione C `count++` (o `count--`) viene tradotta dal compilatore in **tre istruzioni macchina distinte**:

1. **Load**: carica il valore di `count` in un registro del processore;
2. **Inc/Dec**: incrementa o decrementa il registro;
3. **Store**: scrive il valore aggiornato del registro nella variabile `count`.

In codice simil-assembly, inc e dec potrebbero essere rappresentati così:

count++

```assembly
LOAD R1, count   ; R1 = count
INC R1           ; R1 = R1 + 1
STORE count, R1  ; count = R1
```

count--

```assembly
LOAD R2, count   ; R2 = count
DEC R2           ; R2 = R2 - 1
STORE count, R2  ; count = R2
```

Le singole istruzioni macchina sono atomiche, ma **la sequenza nel suo insieme non lo è**: in un sistema **time-sharing**, può capitare un'**interruzione** (es. scadenza del quanto di tempo) **tra le tre istruzioni**, sospendendo il processo a metà dell'operazione logica. Se nel frattempo viene schedulato l'altro processo che opera sulla stessa variabile, i registri dei due processi finiscono a riflettere uno **stato inconsistente** della memoria.

Proponiamo di seguito un'esecuzione possibile, supponendo che l'interrupt sopraggiunga tra INC R1 e STORE count, R1, ovvero nel momento in cui solo il registro è incrementato ma `count` non è ancora aggiornato. Essendo count letta anche dal consumatore, dal passaggio S2 in poi, il consumatore lavora con un valore di `count` che non riflette l'incremento del produttore, portando a un risultato errato.

Infatti prima o poi arriverà la rischedulazione, al che passiamo ad S4 ed S5, dove emerge l'incongruenza:

| Passo | Operazione                             | Effetto         |
| :---- | :------------------------------------- | :-------------- |
| S0    | Produttore legge `count = 5`           | `register1 = 5` |
| S1    | Produttore incrementa `register1`      | `register1 = 6` |
| S2    | Consumatore legge `count = 5`          | `register2 = 5` |
| S3    | Consumatore decrementa `register2`     | `register2 = 4` |
| S4    | Produttore scrive `count = register1`  | `count = 6`     |
| S5    | Consumatore scrive `count = register2` | `count = 4`     |

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

##### **Precisazioni sul progresso e sull'attesa limitata**

- **Progresso**: se nessun processo è attualmente nella sezione critica e più candidati vogliono entrare, la **decisione su chi entra spetta soltanto** a quei processi che **stanno cercando di entrare** — non possono interferire i processi che non stanno tentando l'accesso. Inoltre, la decisione non può essere rimandata indefinitamente.
- **Attesa limitata**: senza questa condizione, un processo potrebbe rimanere sempre escluso dall'ingresso nella sezione critica (entrando in **starvation**). Dopo un numero **finito di tentativi falliti**, un processo deve poter entrare nella propria sezione critica, garantendo così che **tutti i processi** abbiano prima o poi accesso alla risorsa.

Questi principi costituiscono la base per ogni **algoritmo di sincronizzazione**.

---

### **5. Sincronizzazione e cooperazione**

La sincronizzazione non serve solo per evitare conflitti, ma anche per **coordinare processi cooperanti** che lavorano insieme per uno stesso obiettivo.

$$
\text{Processi cooperanti} \Rightarrow \text{necessità di sincronizzare l'evoluzione della loro computazione.}
$$

#### **5.1. Due modi di sincronizzare, due obiettivi diversi**

Va distinto chiaramente:

$$
\begin{cases}
\textbf{Sincronizzazione tra processi concorrenti:}~ & \text{i processi competono per risorse condivise;} \\\\
& \text{obiettivo = garantire la consistenza delle risorse.} \\\\
\textbf{Sincronizzazione tra processi cooperanti:}~ & \text{i processi collaborano verso un obiettivo comune;} \\\\
& \text{obiettivo = coordinare l'evoluzione della loro computazione.}
\end{cases}
$$

#### **5.2. Esempio: segnalazione tra processi cooperanti**

Consideriamo due processi cooperanti P1 e P2 che, pur essendo logicamente concorrenti sul processore (quindi a priori indipendenti nell'ordine di esecuzione), devono sincronizzarsi a un certo punto della loro computazione:

- P1 esegue una porzione di computazione e, una volta completata, **invia una segnalazione di sincronizzazione** a P2;
- P2 esegue la propria porzione di computazione fino a un certo punto in cui, **prima di proseguire**, vuole essere certo che P1 abbia completato la propria parte;
- P2 quindi **attende il segnale di sincronizzazione** da P1;
- una volta ricevuto il segnale, P2 può proseguire con la **certezza** che P1 abbia già svolto le operazioni che lo precedono nel punto di sincronismo;
- P1, dopo aver inviato il segnale, prosegue normalmente con il resto della propria computazione.

![](imgs/Pasted%20image%2020260529163403.png)

Questo schema **vincola l'ordine relativo** di alcune sezioni dei due processi senza richiedere mutua esclusione su risorse condivise: serve a **stabilire una relazione di precedenza** ("P1 deve finire X prima che P2 possa iniziare Y").

Un esempio classico più strutturato è il **produttore–consumatore**, dove la sincronizzazione è indispensabile per mantenere il corretto equilibrio tra produzione e consumo.

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
