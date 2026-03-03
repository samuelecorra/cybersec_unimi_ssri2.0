
## **Lezione 1: Progetto di algoritmi**

### **1. Introduzione**

Con lo studio del problema dei **cammini minimi**, abbiamo iniziato ad affrontare il tema generale del **progetto di algoritmi efficienti**.  
In informatica, infatti, non basta trovare un algoritmo che risolva un problema: è fondamentale che lo faccia **in modo efficiente**, cioè con tempi e risorse computazionali accettabili.

In generale, **non esiste una ricetta universale** per progettare algoritmi efficienti.  
Tuttavia, esistono **fasi e principi comuni** che possono guidare la progettazione e portare a risultati buoni sia dal punto di vista teorico che pratico.

In questa lezione delineeremo uno **schema generale di progettazione** che ci accompagnerà per tutto il corso.

---

### **2. Le quattro fasi della progettazione di un algoritmo**

Durante la progettazione di un algoritmo possiamo individuare **quattro fasi principali**, che spesso si intrecciano tra loro ma seguono un ordine logico preciso:

1. **Classificazione del problema**
    
2. **Caratterizzazione della soluzione**
    
3. **Tecnica di progetto**
    
4. **Scelta delle strutture dati**
    

---

### **3. Classificazione del problema**

La prima fase consiste nell’individuare **a quale classe appartiene il problema** che vogliamo risolvere.  
Ogni classe di problemi presenta **caratteristiche comuni** che ne influenzano direttamente la strategia di risoluzione.

Le principali **classi di problemi computazionali** sono tre:

- **Problemi decisionali**  
    → richiedono una risposta “sì” o “no”.  
    Esempio: “Il numero $p > 1$ è primo?”
    
- **Problemi di ricerca**  
    → richiedono di trovare un oggetto o una soluzione che soddisfi certe condizioni.  
    Esempio: “Trovare un percorso tra due nodi di un grafo.”
    
- **Problemi di ottimizzazione**  
    → richiedono la soluzione migliore tra tutte le possibili.  
    Esempio: “Trovare il cammino minimo in un grafo.”
    

Comprendere la **classe del problema** è essenziale perché orienta la **scelta della tecnica di progetto** più adatta.

---

### **4. Caratterizzazione della soluzione**

Dopo aver classificato il problema, occorre individuare **come può essere caratterizzata matematicamente la soluzione**.  
Quando una caratterizzazione formale è possibile, essa fornisce spesso **una guida diretta** alla costruzione dell’algoritmo.

#### **Esempio 1 – Numeri primi**

Problema: determinare se un intero $p > 1$ è primo.  
Caratterizzazione:

> $p$ è primo se e solo se è divisibile soltanto per 1 e per sé stesso.

Questa semplice proprietà logica permette di tradurre il problema in una sequenza di test divisionali.

#### **Esempio 2 – Cammini minimi su grafo**

Problema: trovare il cammino più breve da un nodo sorgente a tutti gli altri nodi.  
Caratterizzazione:

> Le **condizioni di Bellman** definiscono in modo matematico le relazioni che devono valere tra i costi dei cammini ottimi.

Come nel caso dei cammini minimi, una **caratterizzazione formale** può quindi suggerire direttamente la **struttura dell’algoritmo di soluzione**.

---

### **5. Tecniche di progetto**

Una volta identificata la natura del problema e la sua caratterizzazione, entra in gioco la **tecnica di progetto** vera e propria.  
Esistono diverse strategie generali che possono essere applicate a problemi differenti per migliorarne l’efficienza.

Le principali **tecniche di progettazione di algoritmi** sono:

- **Divide et impera**  
    Suddividere il problema in sottoproblemi più piccoli, risolverli separatamente e combinare le soluzioni.  
    Esempi: MergeSort, QuickSort.
    
- **Backtracking**  
    Esplorare lo spazio delle soluzioni costruendole passo per passo e scartando quelle non valide.  
    Esempi: Sudoku, problemi di regine su scacchiera.
    
- **Greedy (goloso)**  
    Prendere a ogni passo la scelta localmente migliore, sperando che porti a una soluzione globale ottima.  
    Esempi: Dijkstra, Kruskal.
    
- **Programmazione dinamica**  
    Risolvere sottoproblemi sovrapposti salvando i risultati intermedi per evitare ricalcoli.  
    Esempi: Fibonacci, cammini minimi in matrici.
    
- **Ricerca locale**  
    Partire da una soluzione iniziale e migliorarla iterativamente, esplorando le soluzioni “vicine”.  
    Esempi: Simulated Annealing, Hill Climbing.
    

---

### **6. Scelta delle strutture dati**

L’efficienza di un algoritmo non dipende solo dal suo schema logico, ma anche dalle **strutture dati** utilizzate per rappresentare le informazioni.

Una buona scelta può **ridurre drasticamente i tempi di esecuzione**, come mostrano gli esempi seguenti.

#### **Esempio 1 – SelectionSort migliorato**

L’uso di uno **heap** per selezionare rapidamente il minimo migliora le prestazioni di un algoritmo di ordinamento basato su confronti successivi.

#### **Esempio 2 – Insieme S nell’algoritmo SPT**

Nel problema dei cammini minimi, il modo in cui si modella l’insieme $S$ (come lista, heap, coda o pila) determina la **complessità complessiva** dell’algoritmo.

---

### **7. Interazione tra le fasi**

Le quattro fasi — **classificazione**, **caratterizzazione**, **tecnica di progetto** e **strutture dati** — non sono rigide né indipendenti.  
Spesso la progettazione procede **per cicli iterativi**, dove le decisioni prese in una fase influenzano o richiedono modifiche alle precedenti.

L’ordine tipico di sviluppo segue un **flusso orario**:

> **Classificazione → Caratterizzazione → Tecnica di progetto → Strutture dati**

Ma nella pratica reale, l’analisi e la progettazione si raffinano progressivamente, tornando più volte su ogni fase fino a ottenere la versione più efficiente dell’algoritmo.

---

### **8. Sintesi**

In questa lezione abbiamo:

- introdotto il tema generale del **progetto di algoritmi efficienti**;
    
- individuato **quattro fasi principali** che guidano la progettazione;
    
- anticipato lo studio delle **tecniche di progetto**, che verranno approfondite nelle lezioni successive.
    

Nelle prossime lezioni entreremo nel dettaglio di ciascuna tecnica, analizzandone **il principio logico**, **gli esempi applicativi** e **l’impatto sulla complessità computazionale**.

---