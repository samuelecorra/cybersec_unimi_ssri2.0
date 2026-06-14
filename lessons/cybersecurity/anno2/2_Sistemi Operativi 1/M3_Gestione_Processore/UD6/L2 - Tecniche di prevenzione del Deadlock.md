# **M3 UD6 Lezione 2 - Tecniche di prevenzione del Deadlock**

---

### **1. Contesto: i quattro approcci e l'algoritmo dello struzzo**

#### **1.1. Panoramica dei quattro metodi**

L1 ha enumerato quattro strategie con cui un sistema operativo può gestire il deadlock:

$$
\begin{cases}
\textbf{1.}~ & \text{Ignorarlo (Ostrich Algorithm)} \\\\
\textbf{2.}~ & \text{Prevenzione — impedire a priori le condizioni di Coffman} \quad \leftarrow \text{questa lezione} \\\\
\textbf{3.}~ & \text{Evitamento — controllo dinamico delle richieste (Algoritmo del Banchiere)} \\\\
\textbf{4.}~ & \text{Rilevazione e recupero — individuare e rompere deadlock attivi}
\end{cases}
$$

#### **1.2. Metodo 1: ignorare il deadlock (Ostrich Algorithm)**

Il nome viene dal mito — privo di basi scientifiche — che lo struzzo nasconda la testa sotto la sabbia di fronte al pericolo. In OS theory indica la scelta deliberata di **non fare nulla**: il sistema non previene, non rileva, non risolve i deadlock.

##### **Perché era razionale nei sistemi storici**

Nei sistemi Unix degli anni '70–'90, prevalentemente monoutente e time-sharing, il rapporto costo/beneficio sfavoriva l'intervento attivo:

- I deadlock erano **eventi rari**: le risorse condivise erano poche (stampante, nastro, un handful di file), e i processi di un singolo utente raramente si bloccavano a vicenda.
- Il **costo di prevenzione** — ordinare le richieste, controllare ogni allocazione, tenere il grafo di attesa — era percepito come overhead permanente su ogni operazione di I/O.
- La **soluzione pratica** era semplice e sufficiente: l'utente si accorgeva dell'hang, uccideva il processo (`kill -9`) o riavviava la macchina.

> 📌 Unix, e per molti anni anche Windows, hanno adottato esplicitamente l'Ostrich Algorithm per i processi utente. Silberschatz lo cita come la scelta pragmatica dei sistemi operativi general-purpose.

##### **Situazione nei sistemi moderni (2026)**

L'approccio non è scomparso, ma è stato delimitato con precisione:

| Contesto | Approccio attuale |
|---|---|
| **Linux user-space / Windows desktop** | Ostrich Algorithm ancora predominante: "Program not responding" → l'utente chiude o riavvia |
| **Linux kernel** | `lockdep` — strumento di analisi dinamica che, in build di debug/testing, rileva dipendenze tra lock potenzialmente cicliche *prima* che causino un deadlock in produzione |
| **Database (PostgreSQL, MySQL, Oracle)** | Rilevazione attiva: ogni pochi secondi viene eseguito un ciclo di detection sul grafo di attesa dei lock transazionali; la vittima viene selezionata e abortita |
| **Real-time OS (FreeRTOS, Zephyr, POSIX RT)** | Priority Inheritance e Priority Ceiling Protocol prevengono deadlock a livello di scheduler |
| **Sistemi safety-critical (automotive, aerospace, medicale)** | Standard come MISRA-C, DO-178C o ISO 26262 vietano esplicitamente l'Ostrich Algorithm; la prevenzione formale è obbligatoria |

> ⚠️ Il trend del decennio 2015–2025 è stato l'erosione dello spazio in cui l'Ostrich Algorithm è accettabile: microservizi, container e IoT hanno portato sistemi che non possono permettersi un riavvio umano-supervisionato, spostando molti ambienti verso rilevazione o prevenzione attiva.

> ✅ In sintesi: ignorare il deadlock resta la scelta di default per applicazioni desktop general-purpose, ma è esplicitamente inadeguato per kernel, database, real-time e sistemi embedded critici.

---

### **2. Introduzione alla prevenzione**

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

##### **Requisito di ripristinabilità**

Il pre-rilascio è applicabile solo a risorse il cui **stato di uso al momento del rilascio anticipato è ripristinabile**. Detto in modo più diretto: la risorsa, quando le verrà restituita al processo originario, deve poter essere riportata **esattamente nello stato in cui era stata tolta** — come se la pre-emption non fosse mai avvenuta dal punto di vista logico della computazione.

Solo a queste condizioni si può rilassare il vincolo "no pre-emption" senza compromettere la consistenza:

- ✅ **Applicabile**: CPU (stato salvato nel PCB), pagine di memoria (swap), buffer ripopolabili.
- ❌ **NON applicabile**: stampanti a metà di un job (perderebbero parte dell'output), connessioni di rete con stato transazionale aperto, dispositivi che modificano fisicamente il mondo esterno.

$$  
\text{Pre-emption possibile solo per risorse il cui stato è ripristinabile (es. CPU, memoria).}  
$$

---

Il caso banale è lo scenario in cui il processo detiene alcune risorse e ne chiede altre disponibili, ottenendole subito. Ma se così non fosse, ci sono due tipi di tecniche principali che possono venire adottate:

#### **4.2. Tecniche operative (1)**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Se un processo detiene alcune risorse e ne chiede altre non disponibili al momento della richiesta e che quindi non possono essere assegnate immediatamente:} \\\\  
& \text{→ tutte le risorse possedute vengono rilasciate anticipatamente.} \\\\  
& \text{→ le risorse rilasciate si aggiungono a quelle richieste.} \\\\  
& \text{→ il processo riparte solo quando può ottenere tutte le risorse (vecchie + nuove).}  
\end{cases}  
$$

#### **4.3. Tecniche operative (2)**

Sia **P2** il processo che ha appena richiesto nuove risorse, e sia **P1** un processo che trattiene alcune o tutte le risorse che P2 vorrebbe.

$$
\begin{cases}
\textbf{Caso A — risorse tenute da P1 (bloccato):} \\\\
\quad \text{P1 è già in attesa di altre risorse, quindi non sta usando attivamente le sue.} \\\\
\quad \text{→ il sistema le preleva forzatamente da P1 e le assegna a P2.} \\
\quad \text{→ quelle risorse vengono aggiunte alla lista di attesa di P1 (come se non le avesse mai avute).} \\
\quad \text{→ P1 riprende solo quando riesce ad ottenerle di nuovo insieme a tutte le altre.} \\\\
\textbf{Caso B — risorse non disponibili e non tenute da nessun processo bloccato:} \\\\
\quad \text{Nessun P1 sul quale fare preemption: le risorse sono in uso attivo da un processo in esecuzione.} \\\\
\quad \text{→ P2 deve attendere che si liberino.} \\
\quad \text{→ mentre P2 aspetta, le risorse che P2 già detiene possono essere preemptate da un terzo processo P3 che le richiede.} \\
\quad \text{→ P2 riprende solo quando ottiene le nuove risorse richieste E recupera quelle eventualmente tolte durante l'attesa.}
\end{cases}
$$

> 💡 La differenza chiave: nel **Caso A** la preemption è sicura perché P1 è già fermo — togliergli risorse non interrompe lavoro in corso. Nel **Caso B** non c'è nessun candidato bloccato da cui prelevare, quindi P2 si mette in coda come una normale attesa.

> ⚠️ P1 e P2 attendono entrambi, ma per ragioni diverse: **P1** attendeva già prima (da più tempo, per risorse diverse), **P2** attende perché la sua richiesta appena emessa non può essere soddisfatta immediatamente.

Queste soluzioni richiedono **complessità di gestione elevata**, ma sono molto efficaci per evitare stalli persistenti.

---
### **5. Attesa circolare (Circular Wait)**

#### **5.1. Principio**

Per evitare l'attesa circolare, è sufficiente **imporre un ordinamento globale e univoco** su tutti i tipi di risorse del sistema.  
Ogni processo deve richiedere le risorse **seguendo strettamente quell'ordine**, senza mai "tornare indietro".

$$  
R_1 < R_2 < R_3 < ... < R_n  
$$

##### **Perché funziona**

Se ogni processo richiede risorse solo in **ordine crescente** dell'indice, è **impossibile** che si formi una catena ciclica di attese: in un ipotetico ciclo $P_1 \to R_a \to P_2 \to R_b \to \dots \to P_1$, avremmo necessariamente sia $a < b$ sia $b < a$ — una contraddizione. L'ordinamento totale **rompe per costruzione** la quarta condizione di Coffman.

#### **5.2. Tecnica operativa (1)**

$$  
\begin{cases}  
\text{Un processo può chiedere risorse } R_j \text{ solo se detiene risorse } R_i \text{ con } i < j. \\\\  
\text{Se la risorsa } R_j \text{ non è disponibile, il processo attende.}  
\end{cases}  
$$

#### **5.3. Tecnica operativa (2): riordino forzato**

Se un processo si trova a voler richiedere una risorsa **con indice più basso** di altre già possedute (violando l'ordinamento), deve eseguire un riordino:

$$  
\begin{cases}  
\text{Se un processo detiene risorse } R_i \text{ con } i \ge j, \text{ allora deve:} \\\\  
\text{1. rilasciare tutte le istanze di } R_i \text{ con } i \ge j \\\\
\text{2. richiedere tutte le istanze di } R_j \text{ (vecchie possedute + nuove)} \\\\  
\text{3. richiedere poi le risorse } R_i \text{ con } i > j \text{ che servono ancora}  
\end{cases}  
$$

> ⚠️ **Il ruolo di $j$ si inverte tra step 1 e step 3 — e il cambio di segno ($\geq$ → $>$) lo marca esplicitamente.**
> Nella premessa e in step 1, $j$ è il *bersaglio verso il basso*: il processo vuole scendere a $j$, quindi rilascia tutto ciò che ha con indice $\geq j$ (incluso $j$ stesso, se già posseduto).
> In step 2, $j$ viene acquisito e diventa il *pavimento già garantito*.
> In step 3, $j$ è il *punto di partenza verso l'alto*: si richiedono le risorse con $i > j$ (strettamente, perché $j$ è già in mano dopo step 2) riportando il processo nell'ordine crescente canonico.
> La variabile $i$ indica sempre "le risorse ad indice alto"; è la sua relazione con $j$ che cambia da "tutto ciò che supera la soglia da rilasciare" a "tutto ciò che supera il pavimento da ri-richiedere".

In pratica, il processo si "ripiega" temporaneamente all'indietro, ma poi riprende a chiedere le risorse **sempre nell'ordine crescente** previsto dal protocollo — preservando l'invariante che impedisce l'attesa circolare.

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