# **M3 UD1 Lezione 2 - Creazione e terminazione dei processi**

---

### **1. Introduzione**

In questa lezione analizziamo **come i processi vengono generati e come terminano la loro esecuzione**.  
Ogni sistema operativo deve poter **creare nuovi processi**, **gestire la loro esecuzione concorrente** e **terminarli correttamente** liberando le risorse.

Capire come avvengono questi meccanismi significa comprendere **la dinamica della vita di un processo**: dalla nascita alla fine.

---

### **2. Processi come flussi di esecuzione**

Un **processo** è un **flusso di operazioni**: una sequenza ordinata di istruzioni che elabora dati nel tempo.  
Più processi rappresentano **flussi paralleli o concorrenti di computazione**, che possono **collaborare o evolvere indipendentemente**.

#### **2.1. Le quattro tipologie di scenari**

Il docente individua **quattro scenari tipici** in cui possono trovarsi i flussi di esecuzione presenti nel sistema:

$$  
\begin{cases}  
\textbf{1. Flussi indipendenti tra utenti:}~ & \text{utenti diversi con obiettivi propri, completamente scorrelati.} \\\\
\textbf{2. Flussi indipendenti dentro un'app:}~ & \text{una stessa applicazione con sotto-obiettivi affrontabili in modo autonomo.} \\\\
\textbf{3. Flussi cooperanti sincronizzati:}~ & \text{processi che si coordinano e comunicano per raggiungere un obiettivo globale.} \\\\
\textbf{4. Flussi separati con risorse condivise:}~ & \text{logicamente indipendenti, ma vincolati dalla mutua esclusione su una risorsa comune.}  
\end{cases}  
$$

##### **Sul caso 4 — mutua esclusione su risorse condivise**

Quest'ultimo caso merita un'osservazione: anche se **non c'è alcuna necessità logica** di svolgere un'attività prima o dopo un'altra dal punto di vista degli obiettivi applicativi, **esiste comunque la necessità di sincronizzarsi** perché è la **risorsa condivisa** a imporre il sincronismo per un suo uso corretto e consistente.

Esempio tipico: la **stampante**. Quando un processo la sta usando, altri processi non possono accedervi contemporaneamente, pena la corruzione dell'output.

#### **2.2. Implicazioni per il SO**

Un sistema operativo deve poter **gestire tutti questi scenari** in modo corretto, evitando conflitti, garantendo prestazioni ottimali e fornendo i meccanismi necessari per la comunicazione (caso 3) e per la sincronizzazione (casi 3 e 4).

---

### **3. Modelli di computazione e realizzazione**

Il comportamento dei processi può essere modellato in base al **modo in cui il programma e i suoi flussi sono organizzati**.

#### **3.1. Modelli di computazione**

$$  
\begin{cases}  
\textbf{Processo monolitico:}~ & \text{un solo flusso di esecuzione — un unico processo.} \\\\  
\textbf{Processi cooperanti:}~ & \text{più flussi sincronizzati o concorrenti che collaborano tra loro.}  
\end{cases}  
$$

#### **3.2. Modelli di realizzazione del codice eseguibile**

$$  
\begin{cases}  
\textbf{Programma monolitico:}~ & \text{un unico file eseguibile che rappresenta tutto il lavoro.} \\\\  
\textbf{Programmi separati:}~ & \text{più eseguibili distinti che cooperano o si comunicano.}  
\end{cases}  
$$

#### **3.3. Corrispondenza tra programmi e processi**

$$  
\begin{cases}  
\text{Programma monolitico} & \Rightarrow \text{Processo monolitico.} \\\\  
\text{Programma monolitico che genera altri flussi} & \Rightarrow \text{Processi cooperanti.} \\\\  
\text{Programmi separati} & \Rightarrow \text{Processi cooperanti (possono generare altri processi).}  
\end{cases}  
$$

Più in dettaglio:

- un **programma monolitico** può essere eseguito sia come **unico processo monolitico**, sia produrre al suo interno — durante la fase di esecuzione — **vari processi cooperanti**;
- se l'applicazione viene **pensata come un insieme di programmi separati**, ciascuno di questi verrà attivato come un **processo cooperante**, ed eventualmente potrà generare al suo interno **ulteriori processi**;
- in ogni caso, **tutti i programmi conoscono l'esistenza degli altri**, perché sanno che ci sono vari sotto-obiettivi e ognuno è conscio di quale sia il proprio.

##### **Vantaggio: modularità per il progettista**

Quest'ultimo approccio fornisce una **maggiore modularità**: permette al progettista e al programmatore di **focalizzare l'attenzione su un sotto-aspetto alla volta**, ottenendo la risoluzione dell'intero problema applicativo tramite il **coordinamento globale** delle varie parti realizzate dai vari programmi.

In sostanza, un programma può generare **uno o più processi**, e i processi stessi possono **creare altri processi**, costruendo una struttura gerarchica.

---

### **4. Generazione di un processo**

Quando un processo in esecuzione desidera creare un nuovo processo, **invoca una funzione del sistema operativo** che genera e attiva il processo figlio. Tale funzione, in UNIX e nei sistemi derivati, è chiamata **`fork()`**.

Quando il processo P in esecuzione raggiunge la chiamata a `fork()`, il SO **produce una copia del programma e attiva un nuovo processo**. Il processo neonato prende il nome di **processo figlio**, mentre P diventa il suo **processo padre**, e continuerà la propria computazione in modo indipendente o coordinato con il figlio.

$$  
\begin{cases}  
\textbf{Processo padre:}~ & \text{il processo che invoca la `fork()`.} \\\\  
\textbf{Processo figlio:}~ & \text{il nuovo processo creato dalla `fork()`.}  
\end{cases}  
$$

Dopo la `fork()` il sistema ha **due flussi di computazione** attivi.

#### **4.1. Condivisione delle risorse**

Alla nascita, il processo figlio può:  
$$  
\begin{cases}  
\textbf{Condividere le risorse del padre;} \\\\  
\textbf{Condividerle solo parzialmente}~ & \text{(una porzione passata al figlio, l'altra propria del padre);} \\\\  
\textbf{Essere completamente indipendente:}~ & \text{ottiene tutte le risorse direttamente dal SO, senza ereditare nulla.}  
\end{cases}  
$$

Oltre alle risorse, il padre può **passare dati di inizializzazione** al figlio: sequenze di dati che definiscono esattamente l'esecuzione del nuovo processo — parametri di partenza, informazioni di configurazione, argomenti di input.

---

### **5. Spazio di indirizzamento**

#### **5.1. Principio: separazione e protezione**

Il **processo figlio** ha **sempre** un proprio **spazio di indirizzamento separato** da quello del padre: codice, dati, heap e stack del figlio sono distinti da quelli del padre.

Questa separazione garantisce la **protezione** della memoria centrale e l'**evoluzione corretta e consistente** della computazione, impedendo che un processo possa — accidentalmente o malevolmente — modificare le informazioni su cui l'altro processo evolve.

Il contenuto iniziale dello spazio del figlio dipende però dal **metodo di creazione** usato.

#### **5.2. Caso A — Spazio duplicato dal padre (`fork()` classica)**

Il figlio riceve, all'atto della creazione, una **copia esatta** dello spazio del padre — codice, dati, heap e stack:

- il **codice** viene copiato e, nell'ipotesi di codice non modificabile, rimarrà sempre uguale per tutta l'esecuzione di entrambi;
- **dati, heap e stack** del padre vengono copiati nello spazio del figlio: sono **identici all'atto della creazione**, ma da quel momento in poi **evolveranno autonomamente** nei due processi, dato che padre e figlio sono ormai flussi di esecuzione indipendenti.

#### **5.3. Caso B — Spazio nuovo (`fork()` + `exec()`)**

Il padre ha il proprio spazio di indirizzamento; il figlio riceve uno **spazio completamente nuovo**, nel quale viene **caricato un nuovo programma eseguibile** tramite la funzione di sistema **`exec()`**.

`exec()`:

- sostituisce il contenuto del processo figlio;
- definisce nuovi spazi di **dati, heap e stack** per il nuovo programma;
- dopo la sua esecuzione, il programma del figlio è **diverso da quello del padre**.

#### **5.4. Esempio: il pattern fork + exec in C**

Il pattern classico in UNIX è:

```c
pid_t pid = fork();          // duplicazione del processo
if (pid == 0) {
    // codice eseguito dal FIGLIO (pid == 0)
    execlp("/bin/ls", "ls", "-l", NULL);
    // dopo exec, qui non si arriva più
} else {
    // codice eseguito dal PADRE (pid == pid del figlio)
    // ... il padre prosegue la sua computazione
}
```

Il figlio si "accorge di essere figlio" perché il valore di ritorno di `fork()` nel suo contesto è **0**; il padre invece riceve il PID del figlio. Il figlio chiama quindi `exec()` per **rivestirsi di un altro codice** e proseguire con un programma diverso da quello del padre.

#### **5.5. Riepilogo**

$$  
\begin{cases}  
\textbf{Spazio distinto (principio):}~ & \text{il figlio ha sempre un proprio spazio separato, per protezione.} \\\\  
\textbf{Spazio duplicato:}~ & \text{copia esatta del padre (`fork()`), poi evoluzione autonoma.} \\\\  
\textbf{Spazio nuovo:}~ & \text{caricato un nuovo eseguibile (`fork()` + `exec()`).}  
\end{cases}  
$$

#### **5.6. Condivisione esplicita di memoria**

Il modello a memoria separata può risultare **difficile da utilizzare** quando i processi devono condividere porzioni di memoria centrale (per esempio per scambiarsi grandi quantità di dati efficientemente).

In questi casi il sistema operativo mette a disposizione **funzioni specifiche** per garantire la condivisione di porzioni di memoria — in deroga controllata al principio generale di separazione totale degli spazi di indirizzamento. Approfondiremo questo aspetto nella sezione dedicata alla **comunicazione tra processi**.

---

### **6. Esecuzione dei processi padre e figlio**

Dopo la creazione, padre e figlio possono eseguire **concorrentemente**.  
Le modalità principali sono:

$$  
\begin{cases}  
\textbf{Esecuzione concorrente:}~ & \text{il padre continua subito l’esecuzione, in parallelo col figlio.} \\\\  
\textbf{Esecuzione sincronizzata:}~ & \text{il padre attende che il figlio (o tutti i figli) termini prima di proseguire.}  
\end{cases}  
$$

#### **6.1. Esecuzione sincronizzata: la `wait()`**

L'esecuzione sincronizzata si ottiene tramite la chiamata di sistema **`wait()`**. Dopo aver invocato `fork()`, il padre chiama `wait()` e si **sospende** in attesa che il figlio termini la propria computazione: **soltanto quando il figlio termina**, il padre riparte con la propria esecuzione.

#### **6.2. Albero dei processi**

Da queste relazioni nasce una struttura gerarchica detta **albero dei processi**, dove:

- ogni nodo rappresenta un processo;
  
- gli archi rappresentano relazioni padre–figlio.

![Albero dei processi](imgs/Pasted%20image%2020260319133910.png)

##### **Genesi dell'albero: dal bootstrap a `init`**

L'intero albero dei processi nasce in modo ordinato a partire dal **bootstrap del sistema operativo**:

1. Una volta completato il caricamento del SO e la sua fase di **inizializzazione**, il sistema genera un **processo radice** — il **padre di tutti i processi** del sistema.
2. Questo processo effettua tutte le operazioni di **inizializzazione di sistema** e provvede ad **attivare i processi di sistema** che gestiscono l'ambiente operativo: ad esempio i processi che caricano e scaricano i programmi dalla memoria di massa, quelli che gestiscono le stampanti, e così via.
3. In molti sistemi operativi UNIX-like viene generato il processo **`init`** (o successori come `systemd`, `launchd`), che è il **padre di tutti i processi di interfaccia utente**.
4. Quando un **terminale si accende**, viene lanciata un'**interruzione al SO** che segnala la volontà di un utente di lavorare: `init` provvede ad attivare un **processo di Shell** per quell'utente.
5. La Shell comincia ad **acquisire un comando dopo l'altro** dall'utente, mandandoli in esecuzione come **processi figli** — i quali a loro volta possono **lanciarne altri** al loro interno.

In questo modo si costruisce dinamicamente l'**intero albero dei processi attivi** sul sistema.

---

### **7. Terminazione dei processi**

Quando un processo termina, libera le proprie risorse e notifica il sistema operativo (e il padre) del suo completamento.  
Esistono due modalità di terminazione principali:

$$  
\begin{cases}  
\textbf{Terminazione normale:}~ & \text{il processo esegue l’ultima istruzione e restituisce un valore di stato.} \\\\  
\textbf{Terminazione anomala:}~ & \text{il processo viene interrotto per un errore o per richiesta esterna.}  
\end{cases}  
$$

#### **7.1. Terminazione normale — `exit()`**

Il processo svolge tutte le operazioni della propria computazione fino a raggiungere l'**ultima istruzione** del flusso previsto dall'applicazione. A quel punto invoca la funzione di sistema **`exit()`**, che si occupa di:

- restituire un'**informazione sullo stato di esecuzione** del processo all'**interfaccia utente** (o, in generale, al **processo chiamante**);
- **deallocare tutte le risorse** utilizzate dal processo.

Esempio: `exit(0)` segnala terminazione con successo; il padre può leggere questo valore (ad esempio tramite `wait()`) per sapere se l'esecuzione è andata a buon fine.

#### **7.2. Terminazione anomala — `abort()`**

Un processo può anche terminare in modo anomalo. Cause tipiche:

$$  
\begin{cases}  
\text{Uso eccessivo di risorse}~ & \text{(memoria, CPU);} \\\\  
\text{Compito non più necessario}~ & \text{(la richiesta che aveva generato il processo è stata revocata);} \\\\
\text{Errore nell'uso di una risorsa}~ & \text{(divisione per zero, accesso illecito alla memoria, ecc.);} \\\\  
\text{Interruzione esplicita}~ & \text{da parte dell'utente o del sistema;} \\\\  
\text{Terminazione a cascata:}~ & \text{quando un padre viene terminato e i suoi figli vengono chiusi forzatamente.}  
\end{cases}  
$$

In questi casi viene tipicamente invocata la funzione di sistema **`abort()`**, che gestisce la terminazione con anomalie del comportamento del processo, eventualmente attivando il **post-mortem debugging**.

##### **Terminazione a cascata**

Quando un processo padre termina, in alcuni sistemi è previsto che **tutti i suoi figli muoiano forzatamente** in cascata, in modo da non lasciare nel sistema processi "orfani" privi del proprio contesto di gestione. Questo comportamento può essere controllato dal SO e, in alcuni casi, sostituito da meccanismi di **riadozione** (es. i processi orfani in UNIX vengono ereditati da `init`).

---

### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Creazione:}~ & \text{un processo padre genera un figlio invocando una funzione del SO.} \\\\  
\textbf{Risorse:}~ & \text{possono essere condivise, parzialmente condivise o indipendenti.} \\\\  
\textbf{Spazio di indirizzamento:}~ & \text{può essere distinto, duplicato o nuovo.} \\\\  
\textbf{Esecuzione:}~ & \text{concorrente o sincronizzata tra padre e figlio.} \\\\  
\textbf{Terminazione:}~ & \text{può essere normale o anomala, con liberazione delle risorse.}  
\end{cases}  
$$

---

### **9. Conclusione**

La creazione e la terminazione dei processi rappresentano il **ciclo vitale del multitasking**.  
Il sistema operativo, come un direttore d’orchestra, **coordina la nascita, la cooperazione e la morte dei processi**, mantenendo sempre l’equilibrio tra efficienza e stabilità.

Ogni volta che un nuovo processo nasce, il sistema si arricchisce di un nuovo flusso di esecuzione;  
ogni volta che uno termina, le risorse tornano disponibili per altri.  
È un ecosistema dinamico e controllato: la base stessa del funzionamento moderno di ogni computer.
