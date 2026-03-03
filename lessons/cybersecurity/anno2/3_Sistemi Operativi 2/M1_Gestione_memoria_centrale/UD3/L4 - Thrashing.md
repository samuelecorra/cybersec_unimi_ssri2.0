# **M1 UD3 Lezione 4 - Thrashing**

### **1. Introduzione**

Il **thrashing** è una condizione di grave degrado delle prestazioni in un sistema con **memoria virtuale**.  
Si verifica quando un processo dispone di **troppi pochi frame** e genera _page fault_ così frequenti da passare **più tempo a caricare e scaricare pagine** che a eseguire istruzioni utili.

In pratica, il sistema entra in un circolo vizioso di **paginazione continua**, consumando gran parte del tempo di CPU in operazioni di I/O anziché in elaborazione effettiva.

---

### **2. Definizione del fenomeno**

Il termine _thrashing_ (letteralmente “paginazione spazzatura”) indica una **situazione di sovraccarico da paginazione**.

#### **Condizione tipica**

- Il numero di frame assegnati a un processo è **insufficiente** a contenere le pagine attivamente utilizzate.
    
- Ogni volta che una nuova pagina è richiesta, un’altra deve essere rimossa → continua alternanza tra caricamenti e scaricamenti.
    
- Il sistema operativo trascorre più tempo nella **gestione delle mancanze di pagina** che nell’esecuzione dei processi.

#### **Effetto**

- Grave **perdita di prestazioni**.
    
- Diminuzione del **grado di multiprogrammazione efficace**.
    
- Aumento del **tempo medio di risposta** per tutti i processi.

---

### **3. Cause principali del thrashing**

1. **Multiprogrammazione eccessiva**
    
    - Troppi processi caricati in memoria contemporaneamente riducono il numero di frame disponibili per ciascuno.
    
2. **Schedulazione a lungo termine non controllata**
    
    - L’algoritmo di schedulazione può introdurre nuovi processi per aumentare lo sfruttamento della CPU,  
	    ma ciò provoca un **sovraccarico della memoria** e un aumento dei _page fault_.
    
1. **Politiche di allocazione inefficaci**
    
    - Una distribuzione errata dei frame (ad esempio troppo equa o troppo rigida) può ridurre la capacità dei processi di mantenere le proprie “zone di lavoro”.

---

### **4. Strategie per evitare il thrashing**

Per prevenire o gestire il thrashing, il sistema operativo può intervenire su due piani:

#### **a. Politiche di schedulazione**

- Limitare il numero di processi attivi in memoria.
    
- Evitare di caricare nuovi processi quando i frame allocati ai processi esistenti diventano troppo pochi.
    
- In caso di sovraccarico, **sospendere o rimuovere temporaneamente** alcuni processi dalla memoria (swapping out).

#### **b. Politiche di allocazione**

- Adottare **allocazioni locali** (anziché globali) per evitare che un processo sottragga frame ad altri.
    
- Restringere l’insieme dei frame eleggibili per la sostituzione, preservando la stabilità dei processi già in esecuzione.

---

### **5. Modello di località**

Il concetto chiave per comprendere e prevenire il thrashing è quello di **località di riferimento**.

Un processo, durante la sua esecuzione, tende a riferirsi ripetutamente a un insieme ristretto di pagine: variabili, istruzioni e strutture dati vicine nel codice.  
Questo insieme prende il nome di **località**.

#### **Definizione**

Durante l’esecuzione, ogni processo attraversa una sequenza di _località_:

- un gruppo di pagine utilizzate intensivamente per un certo periodo;
    
- successivamente, un altro gruppo per una fase diversa del programma.

#### **Obiettivo del sistema operativo**

Garantire che, in ogni momento, il processo disponga di **abbastanza frame** per contenere **tutta la località attiva**, riducendo così le mancanze di pagina.

---

### **6. Prevenzione del thrashing**

Per prevenire il thrashing, il sistema deve **identificare e mantenere** per ogni processo il numero di frame necessario a contenere la sua località corrente.

Poiché non è possibile conoscere esattamente il comportamento futuro dei processi, si utilizzano **approssimazioni dinamiche**, tra cui:

1. **Working Set Model** (insieme di pagine di lavoro)
    
2. **Page-Fault Frequency (PFF)** (frequenza delle mancanze di pagina)

---

### **7. Working Set Model**

#### **Definizione**

Il **Working Set (WS)** di un processo rappresenta l’insieme delle pagine che sono state utilizzate **più recentemente** durante una finestra temporale di ampiezza $( \Delta )$.

- $( \Delta )$: finestra temporale (in numero di riferimenti o intervalli di tempo).
    
- $( WS )$: insieme delle $( \Delta )$ pagine più recentemente referenziate.

#### **Comportamento**

- Se una pagina è in uso attivo, **rimane nel WS**.
    
- Se una pagina non viene più usata, **esce dal WS** dopo $( \Delta )$ riferimenti dall’ultimo utilizzo.

#### **Interpretazione**

Il WS rappresenta la **località di lavoro** del processo.  
Per evitare thrashing, il sistema deve assegnare a ogni processo **almeno** tanti frame quanti sono necessari a contenere il suo working set.

---

### **8. Algoritmo di allocazione basato sul Working Set**

Sia:

- $( m )$: numero totale di frame fisici disponibili;
    
- $( f_i )$: frame allocati al processo $( P_i )$;
    
- $( WS_i(t) )$: working set del processo $( P_i )$ all’istante $( t )$.

Ad ogni nuovo processo da caricare, il sistema calcola:

$$  
f_i = \max_t(WS_i(t))  
$$

Se la somma dei frame richiesti eccede la memoria disponibile:

$$  
\sum_{k=1}^{i} f_k > m  
$$

→ viene scelto un **processo vittima ( P_v )** da sospendere o scaricare in swap,  
per liberare frame e prevenire il thrashing globale.

---

### **9. Page-Fault Frequency (PFF)**

La **frequenza dei page fault** è un altro parametro utile per il controllo dinamico del thrashing.

#### **Principio**

- Se la frequenza dei _page fault_ per un processo è **troppo alta**, significa che ha **troppi pochi frame** → il sistema deve **allocargliene di più**.
    
- Se la frequenza è **troppo bassa**, il processo sta utilizzando **più frame del necessario** → il sistema può **deallocarne alcuni**.
    
- Se mancano frame liberi, il sistema **sospende un processo** per liberarne.

#### **Vantaggio**

Il PFF consente un **adattamento continuo** e dinamico, in base al comportamento effettivo dei processi.

---

### **10. Sintesi finale**

- Il **thrashing** si verifica quando il sistema passa più tempo nella gestione della paginazione che nell’esecuzione utile.
    
- È causato da **allocazione insufficiente di frame** e **multiprogrammazione eccessiva**.
    
- Le strategie di prevenzione si basano sul concetto di **località di riferimento**.
    
- Le principali tecniche di controllo sono:
    
    - il **Working Set Model**, che calcola il numero minimo di frame necessari per contenere la località;
        
    - la **Page-Fault Frequency**, che regola dinamicamente l’allocazione in base alla frequenza delle mancanze di pagina.
        
- Entrambe le tecniche permettono al sistema operativo di **stabilizzare l’uso della memoria**, evitando la degradazione dovuta al thrashing.