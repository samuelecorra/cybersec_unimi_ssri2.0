# **M3 UD3 Lezione 3 - Politiche di schedulazione**

### **1. Introduzione**

Dopo aver analizzato i criteri di valutazione della schedulazione, questa lezione descrive le **principali politiche e algoritmi** che i sistemi operativi adottano per assegnare la CPU ai processi.

L’obiettivo è comprendere **come le diverse strategie influenzano l’efficienza, la giustizia e la reattività** del sistema.  
Vedremo i principali algoritmi classici e la loro applicazione anche nei **sistemi multiprocessore**.

---
### **2. Politiche e algoritmi di schedulazione**

Le politiche di schedulazione possono essere classificate in base alla loro **pre-emption**, cioè alla possibilità o meno di interrompere un processo in esecuzione per assegnare la CPU a un altro processo.

$$  
\begin{cases}  
\textbf{Non pre-emptive:}~ & \text{il processo mantiene la CPU finché non termina o entra in attesa.} \\\\  
\textbf{Pre-emptive:}~ & \text{la CPU può essere sottratta al processo attivo allo scadere del quanto di tempo o per priorità.}  
\end{cases}  
$$

---
### **3. First Come, First Served (FCFS)**

#### **3.1. Descrizione**

È la politica più semplice: **il primo processo che arriva è il primo a essere servito**.  
L’ordine di esecuzione coincide con l’ordine di arrivo nella coda dei processi _Ready_.

#### **3.2. Caratteristiche**

- È una politica **non pre-emptive**.
    
- Favorisce i processi che arrivano presto, ma può penalizzare quelli brevi che arrivano dopo.
    
- Può generare lunghi tempi di attesa medi.

#### **3.3. Esempio**

Supponiamo tre processi con i seguenti tempi di CPU:

|Processo|Tempo CPU|Ordine di arrivo|
|---|---|---|
|P1|24|1|
|P2|3|2|
|P3|3|3|

![](imgs/Pasted%20image%2020260319161308.png)

Con ordine di arrivo P1 → P2 → P3:  
$$  
\text{Tempo medio di attesa} = \frac{(0 + 24 + 27)}{3} = 17  
$$

Invertendo l’ordine (P2, P3, P1):  
$$  
\text{Tempo medio di attesa} = \frac{(0 + 3 + 6)}{3} = 3  
$$

👉 Il tempo medio di attesa dipende fortemente dall’ordine di arrivo.

---
### **4. Shortest Job First (SJF)**

#### **4.1. Descrizione**

Seleziona il processo con **minor tempo di esecuzione stimato**.  
Minimizza il tempo medio di attesa, ma richiede di conoscere o stimare la durata dei processi.

#### **4.2. Varianti**

$$  
\begin{cases}  
\textbf{Non pre-emptive:}~ & \text{il processo in esecuzione non viene interrotto.} \\\\  
\textbf{Pre-emptive (Shortest Remaining Time First):}~ & \text{il processo più breve tra quelli pronti viene eseguito subito.}  
\end{cases}  
$$

#### **4.3. Predizione del tempo di CPU**

Il tempo di esecuzione futuro può essere stimato con una **media esponenziale** basata sui tempi passati:

$$  
\tau_{n+1} = \alpha \cdot t_n + (1 - \alpha) \cdot \tau_n  
$$

dove:

- $t_n$ è il tempo di esecuzione effettivo più recente,
    
- $\tau_n$ è la stima precedente,
    
- $\alpha \in [0, 1]$ è un coefficiente di adattamento.

#### **4.4. Vantaggi e limiti**

- Garantisce **tempo medio minimo di attesa** teorico.
    
- Può causare **starvation**: processi lunghi restano in attesa indefinitamente se ne arrivano sempre di brevi.

---
### **5. Schedulazione a priorità**

#### **5.1. Descrizione**

Ogni processo è associato a un **livello di priorità**.  
Il processo con priorità più alta ottiene la CPU per primo.

$$  
\begin{cases}  
\textbf{Logica diretta:}~ & \text{numero alto = priorità alta.} \\\\  
\textbf{Logica inversa:}~ & \text{numero basso = priorità alta.}  
\end{cases}  
$$

#### **5.2. Tipologie**

$$  
\begin{cases}  
\textbf{Non pre-emptive:}~ & \text{il processo con priorità maggiore attende la fine di quello corrente.} \\\\  
\textbf{Pre-emptive:}~ & \text{un processo con priorità superiore può interrompere quello in corso.}  
\end{cases}  
$$

#### **5.3. Problema della starvation**

I processi con priorità bassa rischiano di **non essere mai eseguiti** se arrivano processi di priorità più alta in continuazione.

**Soluzione:** applicare una politica di **aging (invecchiamento)** che aumenta progressivamente la priorità dei processi che attendono da molto tempo.

---
### **6. Round Robin (RR)**

#### **6.1. Descrizione**

È una politica **pre-emptive** tipica dei sistemi _time-sharing_.  
Ogni processo riceve un **quanto di tempo (time slice)**, dopodiché viene sospeso e rimesso in coda.

#### **6.2. Caratteristiche**

$$  
\begin{cases}  
\textbf{Se il quanto è molto lungo:}~ & \text{RR ≈ FCFS (bassa reattività).} \\\\  
\textbf{Se il quanto è molto breve:}~ & \text{aumenta la concorrenza, ma anche il costo dei context switch.}  
\end{cases}  
$$

Un valore empirico accettabile è tale che **l’80% delle richieste CPU** si completi all’interno di un quanto di tempo.

---
### **7. Coda a più livelli (MLQ – Multilevel Queue)**

#### **7.1. Descrizione**

I processi vengono **raggruppati in categorie omogenee** (es. interattivi, batch, di sistema, di background).  
Ogni gruppo è assegnato a una **coda dedicata**, con **propria politica di schedulazione**.

#### **7.2. Esecuzione**

Le code stesse vengono gestite da un **algoritmo di schedulazione superiore**, spesso basato su **priorità fisse**.

$$  
\text{Esempio:}  
\begin{cases}  
\text{Coda 1: processi interattivi – Round Robin veloce;} \\\\  
\text{Coda 2: processi batch – FCFS;} \\\\  
\text{Coda 3: processi di background – priorità bassa.}  
\end{cases}  
$$

#### **7.3. Limite principale**

La divisione rigida impedisce la migrazione dei processi tra code: un processo resta vincolato al proprio livello.

---
### **8. Coda a più livelli con retroazione (MLFQ – Multilevel Feedback Queue)**

#### **8.1. Descrizione**

È un’estensione del modello precedente:  
i processi possono **migrare tra code di priorità diverse** in base al loro comportamento.

#### **8.2. Meccanismo**

$$  
\begin{cases}  
\textbf{Politica di promozione:}~ & \text{i processi che attendono troppo salgono di priorità.} \\\\  
\textbf{Politica di degradazione:}~ & \text{i processi che usano troppo la CPU scendono di priorità.} \\\\  
\textbf{Politica di allocazione:}~ & \text{definisce come distribuire la CPU tra le code.}  
\end{cases}  
$$

Questa struttura dinamica permette al sistema di adattarsi al **comportamento reale dei processi**, bilanciando reattività e produttività.

---
### **9. Schedulazione in sistemi multiprocessore**

#### **9.1. Fattori da considerare**

La schedulazione in sistemi multiprocessore deve tenere conto di:

- **omogeneità o eterogeneità** dei processori,
    
- **memoria condivisa o locale**,
    
- **accesso alle periferiche** (singolo o multiplo).

#### **9.2. Strategie possibili**

$$  
\begin{cases}  
\textbf{Coda unica:}~ & \text{tutti i processori attingono dalla stessa coda globale.} \\\\  
\textbf{Code per processore:}~ & \text{ogni CPU ha la propria coda locale.} \\\\  
\textbf{Load sharing:}~ & \text{i processi vengono distribuiti per bilanciare il carico.}  
\end{cases}  
$$

#### **9.3. Tipi di multiprocessamento**

$$  
\begin{cases}  
\textbf{Asimmetrico (AMP):}~ & \text{un processore master gestisce il SO, gli altri eseguono i processi.} \\  
\textbf{Simmetrico (SMP):}~ & \text{ogni processore esegue il SO e i processi assegnati.}  
\end{cases}  
$$

Il multiprocessamento simmetrico è lo standard moderno: distribuisce in modo bilanciato sia i processi sia la gestione del kernel, garantendo maggiore parallelismo.

---
### **10. Sintesi finale**

$$  
\begin{cases}  
\textbf{FCFS:}~ & \text{semplice ma poco equo, non pre-emptive.} \\\\  
\textbf{SJF:}~ & \text{ottimo tempo medio ma richiede stime accurate.} \\\\  
\textbf{Priorità:}~ & \text{flessibile ma può generare starvation.} \\\\  
\textbf{Round Robin:}~ & \text{reattivo ma sensibile al quanto di tempo.} \\\\  
\textbf{Coda a più livelli:}~ & \text{organizzazione rigida per tipo di processo.} \\\\  
\textbf{Coda con retroazione:}~ & \text{adattiva e dinamica.} \\\\  
\textbf{Multiprocessore:}~ & \text{schedulazione coordinata per load sharing.}  
\end{cases}  
$$

---
### **11. Conclusione**

Le politiche di schedulazione rappresentano il cuore decisionale del sistema operativo.  
Ogni algoritmo riflette un compromesso tra **efficienza**, **reattività** e **equità**.

Nei sistemi moderni, le politiche statiche sono spesso sostituite da **approcci ibridi e adattivi**, che combinano le strategie viste in questa lezione per ottenere **massime prestazioni su architetture multicore e multithread**.