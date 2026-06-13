# **M3 UD4 Lezione 1 - Processi cooperanti**

---

### **1. Introduzione**

Dopo aver studiato la schedulazione, entriamo nel cuore della **comunicazione tra processi** (IPC, _InterProcess Communication_).  
In questa lezione vedremo cosa significa che due o più processi **cooperano** e come si coordinano per raggiungere **uno scopo applicativo comune**.

Comprendere la cooperazione è fondamentale, perché nella pratica **quasi nessun programma opera completamente in isolamento**: dal browser al sistema operativo stesso, tutto si basa su **processi che comunicano e collaborano** tra loro.

---
### **2. Coordinamento**

Il **coordinamento** tra processi è l'insieme dei meccanismi che permettono di:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Sincronizzare la loro computazione per l'accesso a risorse condivise.} \\\\  
\textbf{2.}~ & \text{Garantire che le operazioni si svolgano in modo coerente e ordinato.} \\\\  
\textbf{3.}~ & \text{Consentire un'evoluzione congiunta verso uno stesso obiettivo applicativo.}  
\end{cases}  
$$

Il coordinamento ha quindi un doppio scopo:

- **garantire un uso consistente** delle **risorse condivise** (memoria, file, periferiche): senza sincronizzazione un processo potrebbe scrivere dove un altro sta leggendo, corrompendo l'informazione;
- **assicurare un'evoluzione congiunta** dei processi: anche se ciascuno svolge un proprio compito, l'avanzamento dell'uno può dover **attendere** il completamento di una certa fase dell'altro per ottenere il risultato applicativo comune.

---
### **3. Cooperazione**

La **collaborazione** tra processi può però essere ancora **più intensa** del semplice coordinamento, fino a spingersi alla **cooperazione** vera e propria: lavoro **congiunto** dei processi.

La **cooperazione** tra processi si realizza quando essi **lavorano insieme per uno scopo comune**, condividendo informazioni e risorse.  
A differenza del semplice coordinamento (che riguarda la sequenza di esecuzione), la cooperazione implica un **vero e proprio scambio di dati e obiettivi**: un gruppo di processi opera **insieme**, **scambiandosi informazioni**, per raggiungere uno scopo applicativo unico.

$$  
\text{Cooperazione} = \text{Lavoro congiunto dei processi con condivisione e scambio di informazioni.}  
$$

Esempio:  
un processo che produce dati e un altro che li elabora successivamente formano una **catena cooperante**, come accade nei sistemi produttore-consumatore o nelle pipeline di compilazione.

---
### **4. Processi indipendenti e processi cooperanti**

I processi possono essere classificati in due grandi categorie:

$$  
\begin{cases}  
\textbf{Processi indipendenti:} & \text{non condividono dati né obiettivi con altri processi.} \\\\  
\textbf{Processi cooperanti:} & \text{hanno scopi comuni e comunicano tra loro.}  
\end{cases}  
$$

#### **4.1. Processi indipendenti**

- Non hanno informazioni condivise.
- Non sono influenzati né influenzano altri processi.
- Competono solo per le risorse del sistema (CPU, in ambiente multitasking, ed eventualmente **periferiche condivise**).
- Possono richiedere **sincronizzazione** solo per evitare conflitti di accesso — in particolare, le periferiche e le risorse condivise possono essere usate coerentemente **solo in mutua esclusione** (un processo per volta).

#### **4.2. Processi cooperanti**

- Hanno un **obiettivo applicativo comune**.
- Possono **scambiarsi informazioni** e **influenzarsi reciprocamente**.
- Necessitano sia di **comunicazione** (per condividere dati), sia di **sincronizzazione** (per coordinare i tempi di esecuzione).

In sintesi:

$$  
\begin{cases}  
\textbf{Coordinamento} \Rightarrow \text{Sincronizzazione} \\\\  
\textbf{Cooperazione} \Rightarrow \text{Comunicazione + Sincronizzazione}  
\end{cases}  
$$

---
### **5. Vantaggi della cooperazione**

La cooperazione non è solo una necessità funzionale, ma anche una **strategia di progettazione** che porta diversi vantaggi:

$$  
\begin{cases}  
\textbf{1. Modularità:}~ & \text{il sistema è suddiviso in componenti indipendenti e riutilizzabili.} \\\\  
\textbf{2. Parallelizzazione:}~ & \text{i compiti possono essere eseguiti in parallelo su più processori o core.} \\\\  
\textbf{3. Scalabilità:}~ & \text{nuovi processi possono essere aggiunti per gestire carichi maggiori.} \\\\  
\textbf{4. Tempi di risposta:}~ & \text{l'esecuzione parallela riduce drasticamente i tempi percepiti dall'utente.} \\\\
\textbf{5. Specializzazione:}~ & \text{ogni processo svolge un compito preciso e ottimizzato.} \\\\  
\textbf{6. Qualità del progetto:}~ & \text{il software diventa più chiaro, manutenibile e robusto.}  
\end{cases}  
$$

#### **5.1. Approfondimento: parallelizzazione su due piani**

La parallelizzazione si manifesta a **due livelli**:

- in **sistemi mono-processore** (time-sharing o multitasking): si ottiene un **parallelismo logico** — l'evoluzione dei processi appare concorrente all'utente, anche se la CPU esegue un solo processo alla volta;
- in **sistemi multiprocessore** o **distribuiti**: si ottiene un **parallelismo fisico reale** — i processi vengono allocati su processori diversi ed eseguiti effettivamente in contemporanea, con una forte **accelerazione** della computazione.

#### **5.2. Approfondimento: scalabilità con esempio**

Consideriamo un'applicazione che riceve **molte richieste da molti utenti** (es. un web server). Realizzarla con un **unico processo** porta a una **serializzazione** della gestione delle richieste — un utente alla volta — aumentando drasticamente i tempi di risposta sotto carico.

Pensando invece il sistema come **gruppo di processi cooperanti**, è facile **scalare**: si attivano dinamicamente **tanti processi quanti ne servono** a soddisfare il volume di richieste in arrivo, mantenendo bassi i tempi di risposta anche quando il carico cresce.

---
### **6. Esempi di cooperazione**

#### **6.1. Produttore - Consumatore**

Un processo produce dati (produttore) e un altro li elabora o li utilizza (consumatore).  
È il paradigma cooperativo più comune nei sistemi operativi e nei programmi concorrenti.

#### **6.2. Client - Server**

Un processo client richiede un servizio, e un server lo fornisce.  
È il modello alla base di Internet, dei database e dei sistemi distribuiti — l'esempio quotidiano più tipico sono i **siti web**, dove un browser-client interroga un web-server.

#### **6.3. Compilatore - Assemblatore - Loader**

Ogni componente di una catena di compilazione **trasforma l’output del precedente** in input per il successivo.  
Esempio: il compilatore genera codice assembly, l’assemblatore lo traduce in oggetto, e il loader lo carica in memoria.

---
### **7. Sintesi finale**

$$  
\begin{cases}  
\textbf{Coordinamento:}~ & \text{sincronizzare l’evoluzione dei processi.} \\  
\textbf{Cooperazione:}~ & \text{lavoro congiunto per uno scopo comune.} \\  
\textbf{Processi indipendenti:}~ & \text{non comunicano tra loro.} \\  
\textbf{Processi cooperanti:}~ & \text{si scambiano informazioni e si sincronizzano.} \\  
\textbf{Vantaggi:}~ & \text{modularità, parallelismo, scalabilità, specializzazione.}  
\end{cases}  
$$

---
### **8. Conclusione**

La cooperazione tra processi è il fondamento della **programmazione concorrente e distribuita**.  
Permette di scomporre sistemi complessi in parti autonome ma interconnesse, migliorando **prestazioni, chiarezza e affidabilità**.

Nelle prossime lezioni vedremo **come i processi cooperano concretamente**, attraverso meccanismi di **comunicazione diretta e indiretta**, **pipe**, **messaggi**, **memoria condivisa** e **socket di rete**.