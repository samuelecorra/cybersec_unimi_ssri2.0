# **M3 UD5 Lezione 5 - Problemi della Starvation e del Deadlock**

### **1. Introduzione**

La sincronizzazione tra processi, pur essendo indispensabile per la correttezza di un sistema operativo, può generare **problemi gravi di blocco** se non è gestita correttamente.  
In questa lezione analizziamo due fenomeni fondamentali e potenzialmente pericolosi:

$$  
\begin{cases}  
\textbf{1.}~ & \text{Starvation (blocco indefinito)} \\\\  
\textbf{2.}~ & \text{Deadlock (stallo permanente)}  
\end{cases}  
$$

Entrambi riguardano situazioni in cui **uno o più processi non riescono più a progredire**, pur essendo teoricamente pronti all’esecuzione.

---
### **2. Starvation (blocco indefinito)**

#### **2.1. Definizione**

La **starvation** (letteralmente “fame”) si verifica quando un processo rimane **indefinitamente in attesa** di una risorsa,  
perché altri processi vengono **sistematicamente preferiti** dallo scheduler o dal meccanismo di sincronizzazione.

$$  
\text{Un processo attende indefinitamente perché non ottiene mai la risorsa richiesta.}  
$$

---
#### **2.2. Causa principale**

La causa della starvation è una **politica di gestione delle code di attesa** che **non garantisce equità**.  
In altre parole, il sistema consente che alcuni processi vengano **serviti sempre per primi**, impedendo ad altri di ottenere la risorsa.

$$  
\begin{cases}  
\textbf{Causa:}~ & \text{algoritmo di schedulazione non equo o mal progettato.} \\\\  
\textbf{Effetto:}~ & \text{alcuni processi restano sempre in attesa, senza mai progredire.}  
\end{cases}  
$$

---
#### **2.3. Esempio tipico**

Supponiamo una coda di processi che accedono a una risorsa condivisa, gestita secondo una **politica di priorità**.  
Se i processi ad alta priorità continuano ad arrivare, quelli a priorità più bassa **non verranno mai eseguiti**.

Esempio pratico (in pseudo-codice):

```c
while (true) {
    if (priority == HIGH)
        useResource();
    else
        waitInQueue(); // non viene mai scelto se continuano ad arrivare processi HIGH
}
```

---
#### **2.4. Soluzione alla starvation**

L’unico modo per evitare la starvation è **garantire che ogni processo ottenga la risorsa entro un tempo finito**.

$$  
\textbf{Soluzione:}~ \text{uso di politiche di scheduling eque (es. FIFO, aging).}  
$$

##### **Esempi di politiche di prevenzione**

- **FIFO (First In, First Out):** i processi vengono serviti in ordine di arrivo.
    
- **Aging:** la priorità di un processo aumenta progressivamente durante l’attesa, fino a superare quella dei nuovi arrivati.
    
- **Round Robin:** assegna ciclicamente un piccolo intervallo di CPU a ogni processo attivo.

Queste strategie bilanciano **efficienza** e **giustizia**, impedendo che un processo venga trascurato a tempo indefinito.

---
### **3. Deadlock (stallo permanente)**

#### **3.1. Definizione**

Il **deadlock** (in italiano: _stallo_) è una situazione in cui un gruppo di processi **rimane bloccato per sempre**,  
perché ciascuno di essi **attende una risorsa posseduta da un altro processo** del gruppo.

$$  
\text{Ogni processo attende una risorsa detenuta da un altro processo del gruppo.}  
$$

Di conseguenza, nessuno può rilasciare ciò che gli altri stanno aspettando — il sistema rimane **congelato** in una condizione di blocco circolare.

---
#### **3.2. Condizione tipica**

Un deadlock si verifica quando si formano **catene di attesa circolare** tra processi che non rilasciano le risorse.

Esempio:

$$  
\begin{cases}  
P_1~\text{attende una risorsa detenuta da}~P_2; \\\\  
P_2~\text{attende una risorsa detenuta da}~P_3; \\\\  
P_3~\text{attende una risorsa detenuta da}~P_1.  
\end{cases}  
$$

Nessuno può procedere, poiché ogni processo blocca il successivo.  
Il sistema entra così in **stallo permanente**.

---
#### **3.3. Cause del deadlock**

Le **condizioni necessarie** perché si verifichi un deadlock sono quattro (individuate da Coffman nel 1971):

$$  
\begin{cases}  
\textbf{1. Mutua esclusione:}~ & \text{le risorse non possono essere condivise simultaneamente.} \\\\  
\textbf{2. Hold and wait:}~ & \text{un processo trattiene una risorsa mentre ne attende un’altra.} \\\\  
\textbf{3. Nessun prelievo forzato:}~ & \text{le risorse non possono essere sottratte ai processi.} \\\\  
\textbf{4. Attesa circolare:}~ & \text{esiste un ciclo di processi in attesa reciproca.}  
\end{cases}  
$$

Tutte e quattro devono verificarsi contemporaneamente perché si formi un deadlock.

---
#### **3.4. Soluzioni al problema del deadlock**

Esistono quattro approcci principali per gestire o evitare lo stallo:

$$  
\begin{cases}  
\textbf{1. Impedire} & \text{le condizioni di deadlock, rimuovendo una delle quattro cause.} \\\\  
\textbf{2. Prevenire} & \text{monitorando le richieste per evitare cicli di attesa.} \\\\  
\textbf{3. Rilevare e risolvere} & \text{identificando il deadlock e forzando la liberazione di risorse.} \\\\  
\textbf{4. Ignorare} & \text{lasciando che il deadlock si verifichi (approccio pragmatico, es. UNIX).}  
\end{cases}  
$$

---
#### **3.5. Strategie di gestione**

##### **a) Impedire il deadlock**

- Si progetta il sistema in modo che **una delle quattro condizioni di Coffman** non possa mai verificarsi.  
    Esempio: non consentire la _hold and wait_ (un processo deve richiedere tutte le risorse insieme).

##### **b) Prevenire il deadlock**

- Si usa un **algoritmo di controllo delle richieste**, come il _Banker’s Algorithm_, che garantisce che il sistema rimanga sempre in uno stato sicuro.

##### **c) Rilevare e risolvere**

- Si permette che il deadlock si verifichi, ma il sistema esegue **un controllo periodico** delle catene di attesa, e se ne trova una:
    
    - forza la terminazione di alcuni processi, oppure
        
    - rilascia forzatamente alcune risorse.

##### **d) Ignorare il problema**

- In molti sistemi generali (es. UNIX, Linux), il deadlock **non viene gestito esplicitamente**, poiché è raro e complesso da controllare.  
    Si preferisce affidarsi a **riavvii** o **terminazioni selettive** dei processi bloccati.

---
### **4. Sintesi finale**

$$  
\begin{cases}  
\textbf{Starvation:}~ & \text{blocco indefinito causato da politiche non eque.} \\\\  
\textbf{Deadlock:}~ & \text{stallo circolare tra processi che attendono risorse reciproche.} \\\\  
\textbf{Soluzione Starvation:}~ & \text{scheduling equo (FIFO, aging, round robin).} \\\\  
\textbf{Soluzione Deadlock:}~ & \text{impedire, prevenire, rilevare o ignorare la condizione.}  
\end{cases}  
$$

---
### **5. Conclusione**

I problemi di **starvation** e **deadlock** rappresentano i due **limiti strutturali** di ogni sistema di sincronizzazione.  
Nel primo caso si ha una **mancanza di equità**; nel secondo, una **dipendenza circolare** che blocca l’intero sistema.

Un buon sistema operativo deve quindi bilanciare due esigenze contrapposte:

- **massimizzare l’efficienza** dell’uso delle risorse,
    
- **garantire la giustizia** e la **continuità di esecuzione** dei processi.

Capire e prevenire questi problemi è essenziale per progettare **sistemi concorrenti affidabili e sicuri**.