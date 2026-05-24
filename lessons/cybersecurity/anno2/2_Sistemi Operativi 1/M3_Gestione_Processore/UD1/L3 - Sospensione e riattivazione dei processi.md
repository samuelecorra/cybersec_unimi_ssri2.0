# **M3 UD1 Lezione 3 - Sospensione e riattivazione dei processi**

---

### **1. Introduzione**

Il **multitasking** è la capacità del sistema operativo di **gestire più processi in modo concorrente** condividendo un solo processore.  
L’obiettivo è **massimizzare l’uso della CPU** e fornire all’utente l’illusione che più programmi si stiano eseguendo contemporaneamente.

In questa lezione studiamo come il sistema operativo **sospende, riattiva e alterna** i processi, analizzando anche il meccanismo di **time-sharing**, che permette di distribuire equamente il tempo di CPU tra più processi attivi.

---
### **2. Classificazione dei processi**

I processi possono essere classificati in base all’uso prevalente delle risorse fisiche:

$$  
\begin{cases}  
\textbf{Processi I/O-bound:}~ & \text{eseguono molte operazioni di input/output rispetto alle manipolazioni in CPU.} \\\\  
\textbf{Processi CPU-bound:}~ & \text{eseguono pochissime operazioni di I/O e prevalentemente operazioni} \\\\
& \text{aritmetico-logiche e spostamenti memoria centrale ↔ processore.}  
\end{cases}  
$$

#### **2.1. Applicazioni miste vs applicazioni ben separate**

Nella pratica osserviamo due situazioni:

- alcune applicazioni hanno **porzioni miste**: una parte si comporta come fortemente I/O-bound, un'altra come fortemente CPU-bound;
- molto più spesso, le applicazioni sono **ben separate**: o eseguono fortemente operazioni di I/O durante tutta la loro vita, o eseguono fortemente operazioni aritmetico-logiche in memoria centrale per tutto il loro corso, **eccetto** il caricamento iniziale dei dati su cui operare e il salvataggio finale dei risultati.

#### **2.2. Importanza della classificazione**

È fondamentale ricordare queste due tipologie di uso del processore: su queste **caratteristiche di comportamento** verranno basati gli aspetti di **gestione** del nostro sistema e quindi di **virtualizzazione del processore**.

Un sistema bilanciato deve alternare entrambi i tipi di processi per **mantenere alta l'efficienza globale**, evitando che la CPU resti inattiva o che l'I/O diventi un collo di bottiglia.

---
### **3. Realizzazione del multitasking**

#### **3.1. Obiettivo**

Massimizzare lo sfruttamento del processore garantendo che, mentre un processo è sospeso in attesa di I/O, un altro possa usare la CPU.
#### **3.2. Metodologia**

$$  
\begin{cases}  
\textbf{1.}~ & \text{Sospendere il processo in esecuzione.} \\\\  
\textbf{2.}~ & \text{Ordinare i processi pronti secondo una politica di scheduling.} \\\\  
\textbf{3.}~ & \text{Selezionare (dispatching) il prossimo processo da eseguire.} \\\\  
\textbf{4.}~ & \text{Riattivare il processo selezionato, ripristinandone il contesto.}  
\end{cases}  
$$

In questo modo la CPU viene continuamente riassegnata, e il sistema dà l’impressione di **parallelismo logico** anche in presenza di un solo processore fisico.

---
### **4. Politiche e meccanismi del multitasking**

#### **4.1. Politiche**

Le **politiche** determinano *quando* e *perché* un processo può essere sospeso e *come* ordinare i processi pronti:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Dopo una richiesta di I/O (rilascio implicito: inutile attendere in CPU tempi lunghi).} \\\\  
\textbf{2.}~ & \text{Dopo la creazione di un sottoprocesso, attendendone la terminazione (rilascio implicito).} \\\\  
\textbf{3.}~ & \text{Quando rilascia volontariamente la CPU (rilascio esplicito tramite syscall).}  
\end{cases}  
$$

Queste sospensioni sono dette **sincrone** perché avvengono in corrispondenza di eventi previsti dal programma stesso (es. una `read()` o una `wait()`).

##### **Effetto delle politiche: cambia l'apparenza, non i risultati**

Un punto fondamentale: **al variare delle politiche scelte**, i **risultati prodotti** dai processi (cosa essi fanno e cosa calcolano) **non cambiano**. Cambia però l'**ordine** con cui le attività vengono eseguite nel sistema, e quindi cambia l'**apparenza di evoluzione percepita dagli utenti**.

In altre parole: la politica influenza la *qualità del servizio* (reattività, equità, throughput), non la *correttezza* della computazione.

#### **4.2. Meccanismi**

I meccanismi implementano materialmente le politiche:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Salvataggio del contesto del processo sospeso (registri, stack, contatori).} \\\\  
\textbf{2.}~ & \text{Dispatching del nuovo processo selezionato dallo scheduler.} \\\\  
\textbf{3.}~ & \text{Ripristino del contesto del processo riattivato.}  
\end{cases}  
$$

Questa sequenza costituisce il **context switch**, ovvero il cambio di processo in esecuzione.

---
### **5. Il Time-Sharing**

#### **5.1. Definizione e motivazione**

Il **time-sharing** è una forma di multitasking basata sulla **condivisione del tempo di CPU** tra i processi.  
Serve a garantire **reattività** nei sistemi interattivi, dove l’utente si aspetta risposte rapide.

##### **Perché funziona l'illusione**

Ovviamente, con un solo processore, esso esegue **una sola istruzione di un solo processo alla volta**. Tuttavia, a livello macroscopico, gli utenti vedono i processi evolvere **in parallelo**: questo è possibile perché i **tempi elettronici** del processore sono **enormemente più veloci** dei **tempi di percezione degli esseri umani**. La rapidissima turnazione del processore tra un processo e l'altro fa credere agli utenti che i processi evolvano simultaneamente.

##### **Il problema dei processi CPU-bound**

Senza un meccanismo di forzatura, i processi **CPU-bound** tenderebbero a **non rilasciare** il processore con sufficiente frequenza. La conseguenza è che, quando uno di questi prende il controllo, **tutti gli altri processi sembrano rallentare o fermarsi**, finché il CPU-bound non rilascia spontaneamente la CPU.

Questa **esecuzione "a singhiozzo"** — invece di un fluire parallelo armonioso — è **mal percepita dagli utenti**, perché fa apparire la CPU come non omogeneamente distribuita.

$$  
\begin{cases}  
\textbf{Obiettivo:}~ & \text{creare l’illusione che tutti i processi evolvano contemporaneamente.} \\\\  
\textbf{Problema:}~ & \text{i processi CPU-bound monopolizzerebbero il processore.} \\\\  
\textbf{Soluzione:}~ & \text{forzare il rilascio del processore tramite pre-emption.}  
\end{cases}  
$$

#### **5.2. Quanto di tempo (Time Slice)**

Ogni processo ha un **intervallo massimo di uso della CPU**, chiamato *quanto di tempo* o *time slice* (Δ).  
Quando il tempo scade, il processo viene **sospeso forzatamente** (preemption), e la CPU viene assegnata a un altro processo pronto.

##### **Esempio narrativo: turnazione di P1, P2, P3**

Supponiamo di avere tre processi pronti e un quanto di tempo Δ:

1. **P1** ottiene il processore e lavora per un tempo massimo Δ; arrivato a Δ, viene **sottoposto a preemption** e rilascia anticipatamente il processore.
2. Il controllo passa a **P2**, che lavora a sua volta per Δ; anche P2 subisce preemption allo scadere del quanto.
3. Il processore viene dato a **P3**, che lavora per Δ; preemption.
4. P1 e P2 sono ancora **pronti** (la loro computazione non era completata): il sistema riconcede il processore a **P1**, che lavora per un altro Δ e poi viene di nuovo soggetto a preemption.
5. Il sistema decide di dare il processore a **P3**, che però — dopo un certo tempo — **esegue un'operazione di I/O**: questo implica il **rilascio implicito** del processore (caso sincrono).
6. Tra P1 e P2 pronti, il SO sceglie P1, che continua per un altro Δ; poi tocca a P2, che però anch'esso **esegue un I/O** e rilascia implicitamente la CPU.
7. A questo punto P3 e P2 sono in attesa di I/O e nel sistema rimane solo **P1**, che prosegue per un altro Δ.
8. Mentre P1 lavora, l'I/O di **P3 si completa**: P3 torna nello stato **Ready** e potrà rientrare in competizione al prossimo scadere del quanto.

##### **Equa ripartizione della CPU**

Questo meccanismo realizza una **equa ripartizione del processore** tra tutti i processi, **indipendentemente** dal loro comportamento: anche un processo CPU-bound come P1 viene **interrotto sufficientemente di frequente** da lasciare evolvere anche gli altri, mantenendo così l'illusione di **esecuzione parallela** di tutti i processi.

---
### **6. Real-time clock e pre-emption**

Per realizzare il time-sharing si usa un **real-time clock (RTC)**, un dispositivo hardware presente nel sistema di elaborazione che **genera interruzioni periodiche**. Quando scade il tempo previsto, il RTC genera **automaticamente un'interruzione**: nella routine di risposta a tale interruzione, il SO può accorgersi che il periodo è scaduto e decidere se attivare il **cambiamento del processo in esecuzione**.

#### **6.1. Problema: periodo RTC troppo breve**

Il problema è che usualmente il **periodo del RTC è troppo breve**: se la routine di risposta dovesse attivarsi a ogni tick, sarebbe invocata **troppo frequentemente** con un **sovraccarico di gestione** che peggiorerebbe le prestazioni del sistema.

#### **6.2. Soluzione: quanto = multiplo del periodo RTC**

La soluzione è scegliere come quanto di tempo un **multiplo intero K** del periodo RTC, sufficientemente lungo da non sovraccaricare il sistema ma abbastanza breve da garantire l'illusione di turnazione frequente:

$$  
\begin{cases}  
\textbf{Periodo RTC:}~ & p_{RTC} \\\\
\textbf{Quanto di tempo:}~ & \Delta = K \cdot p_{RTC}  
\end{cases}  
$$

Operativamente, la **routine di risposta** all'interruzione del RTC **conta K periodi**: solo quando ne raggiunge K dichiara terminato il quanto di tempo per il processo in esecuzione e attiva il cambiamento di processo.

Un periodo troppo breve (K piccolo) comporta **sovraccarico di interruzioni**, mentre uno troppo lungo (K grande) riduce la **reattività del sistema**: la scelta di K è quindi un equilibrio fondamentale.

---
### **7. Politiche di sospensione nel time-sharing**

Nel modello time-sharing un processo può essere sospeso in quattro casi:

$$  
\begin{cases}  
1.~ & \text{Dopo una richiesta di I/O;} \\  
2.~ & \text{Dopo la creazione di un sottoprocesso;} \\  
3.~ & \text{Quando rilascia volontariamente la CPU;} \\  
4.~ & \text{Quando scade il quanto di tempo assegnato.}  
\end{cases}  
$$

Analizzando le sospensioni dal punto di vista logico:

$$  
\begin{cases}  
\textbf{Rispetto all’evoluzione della computazione:} &  
\begin{cases}  
\text{Sincrona:}~ & 1, 2, 3 \\  
\text{Asincrona:}~ & 4  
\end{cases} \\\\  
\textbf{Rispetto alla scrittura del programma:} &  
\begin{cases}  
\text{Esplicita:}~ & 3 \\  
\text{Implicita:}~ & 1, 2, 4  
\end{cases}  
\end{cases}  
$$

---
### **8. Sospensione di un processo**

La **sospensione** consiste in due fasi:

$$  
\begin{cases}  
\textbf{1. Attivazione della procedura di sospensione:}~ &  
\begin{cases}  
\text{Sincrona (kernel mode):}~ & \text{durante operazioni di I/O o creazione processi.} \\\\  
\text{Sincrona (user mode):}~ & \text{rilascio volontario del processore.} \\\\  
\text{Asincrona:}~ & \text{allo scadere del quanto di tempo.}  
\end{cases} \\\\\\  
\textbf{2. Salvataggio del contesto di esecuzione:}~ &  
\begin{cases}  
\text{Salvataggio di tutti i registri sullo stack;} \\\\  
\text{Memorizzazione dello stack pointer nel PCB.}  
\end{cases}  
\end{cases}  
$$

> 💡 **Analogia importante.** Il salvataggio del contesto avviene **esattamente come per una qualunque risposta alle interruzioni**: il meccanismo è lo stesso adottato dall'hardware quando si gestisce un interrupt da periferica. Questo permette al SO di **riutilizzare lo stesso schema** per gestire I/O, syscall, errori e scadenza del quanto di tempo.

---
### **9. Riattivazione di un processo**

La **riattivazione** è l’operazione inversa alla sospensione, ed è importante eseguirla **nell'ordine corretto**:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Ripristino del \textbf{base pointer} dal PCB (sapere dove si trova lo stack del processo).} \\\\  
\textbf{2.}~ & \text{Ripristino dello \textbf{stack pointer} dal PCB (sapere qual è la cima dello stack).} \\\\  
\textbf{3.}~ & \text{Recupero dalla cima dello stack di tutti i \textbf{registri} del processore.} \\\\
\textbf{4.}~ & \text{Ripresa dell’esecuzione dal punto in cui era stata interrotta (riprendendo il PC).}  
\end{cases}  
$$

Senza il primo passo non sarebbe possibile localizzare lo stack del processo da riattivare, e quindi non si potrebbe recuperare nessuna delle informazioni di contesto: per questo il **base pointer dal PCB è la chiave** che apre l'intero meccanismo di ripristino.

Una volta completato il ripristino, il processo torna nello stato **Running** e continua la sua computazione.

---
### **10. Il context switch**

Il **cambiamento del processo in esecuzione** si realizza tramite il **context switch**:

$$  
\text{Context Switch} = \text{Sospensione del processo corrente} + \text{Riattivazione del nuovo processo}  
$$

#### **10.1. Sequenza dettagliata**

Supponiamo di avere un processo **P0** in esecuzione:

1. Arriva un'**interruzione** (es. scadenza del quanto) o una **chiamata supervisore** (es. richiesta di I/O) che innesca il cambio di processo.
2. Si **salva lo stato** di P0 — registri sullo stack, stack pointer e base pointer nel PCB — in modo da poterlo ripristinare in futuro.
3. Si **carica lo stato** del nuovo processo (es. P1) dal suo PCB; P1 entra in esecuzione e lavora fintanto che non è obbligato a rilasciare la CPU.
4. Quando il sistema decide di tornare a P0, si **salva lo stato di P1** e si **ripristina lo stato salvato di P0**, ricostruendo esattamente l'ambiente di esecuzione che P0 aveva al momento della sospensione.

#### **10.2. Metafora del "salto temporale"**

Per P0 è **come se saltasse direttamente** dal punto in cui era stato sospeso al punto in cui viene riattivato: **tutto l'intervallo di tempo trascorso nel frattempo "sparisce"** agli occhi dell'evoluzione della propria computazione. Il processo non si accorge minimamente di essere stato sospeso — il context switch è perfettamente **trasparente** rispetto alla logica della computazione.

#### **10.3. Vincolo di prestazioni**

Il context switch è uno dei meccanismi più delicati del sistema operativo, poiché avviene **centinaia o migliaia di volte al secondo**.  
Deve essere **rapidissimo** e **trasparente**: ogni microsecondo speso nel salvataggio/ripristino è sottratto al lavoro utile dei processi, e l'overhead riduce l'efficienza globale del sistema.

---
### **11. Dispatching**

Il **dispatcher** è il componente del sistema operativo che **seleziona** il processo successivo dalla **coda dei pronti** e lo **mette in esecuzione**.  
È il punto di collegamento tra lo **scheduler** (che decide *chi* eseguire) e il **meccanismo di riattivazione** (che effettivamente *lo esegue*).

Operativamente, il dispatching consiste semplicemente nel **prendere il primo processo** nell'elenco ordinato dallo scheduler — il **primo processo in stato di Ready** — e **porlo in esecuzione** invocando la procedura di riattivazione descritta nella sezione 9.

---
### **12. Sintesi finale**

$$  
\begin{cases}  
\textbf{Multitasking:}~ & \text{esecuzione concorrente di più processi per ottimizzare la CPU.} \\\\  
\textbf{Time-sharing:}~ & \text{distribuzione temporale equa della CPU tramite pre-emption.} \\\\  
\textbf{Sospensione:}~ & \text{salvataggio del contesto del processo corrente.} \\\\  
\textbf{Riattivazione:}~ & \text{ripristino del contesto e ripresa dell’esecuzione.} \\\\ 
\textbf{Context switch:}~ & \text{meccanismo che realizza il cambio di processo.}  
\end{cases}  
$$

---
### **13. Conclusione dell’Unità**

Con questa lezione si conclude l’**Unità Didattica 1 – Processi**.  
Abbiamo visto:

- come i processi vengono **creati, sospesi e riattivati**;
    
- come il sistema operativo implementa **il multitasking e il time-sharing**;
    
- e come, tramite il **context switch**, la CPU passi con efficienza da un processo all’altro.