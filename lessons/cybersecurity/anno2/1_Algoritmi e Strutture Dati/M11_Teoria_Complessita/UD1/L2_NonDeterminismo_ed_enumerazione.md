## **Lezione 2: Non determinismo ed Enumerazione**

### **1. Introduzione**

Nella lezione precedente abbiamo introdotto la distinzione tra **problemi facili e difficili** in base al tempo necessario per ottenere una soluzione.  
In questa lezione approfondiamo la differenza **dal punto di vista algoritmico**, introducendo due concetti centrali della teoria della complessità:

- il **non determinismo**, come modello teorico di calcolo ideale,
    
- e la sua **simulazione deterministica**, detta **enumerazione**.
    

---

### **2. Algoritmi non deterministici**

#### **Definizione**

Un **algoritmo non deterministico** è un algoritmo che, nel momento in cui deve prendere una decisione, **sceglie sempre la decisione giusta**, cioè quella che conduce verso la soluzione corretta.

Per formalizzare questo comportamento si utilizzano tre istruzioni speciali:

- `choice(I)`: sceglie arbitrariamente l’elemento “migliore” da un insieme finito `I`;
    
- `failure`: termina la computazione in uno stato di fallimento;
    
- `success`: termina la computazione in uno stato di successo.
    

---

### **3. Interpretazione del non determinismo**

L’istruzione `choice` può essere vista come una chiamata a un **oracolo**, cioè a un’entità teorica capace di _indovinare la scelta corretta_ a ogni passo.  
Un algoritmo non deterministico può quindi essere pensato come un sistema che:

- **si duplica in molte versioni parallele**, ciascuna che esplora una diversa scelta possibile;
    
- **mantiene solo le esecuzioni corrette**, cioè quelle che portano a una soluzione valida.
    

Quando almeno una di queste esecuzioni arriva al successo, l’algoritmo **termina positivamente**.

In questo contesto, il **certificato polinomiale** introdotto nella lezione precedente serve per **verificare** la correttezza della soluzione generata dalle scelte non deterministiche.

---

### **4. Esempio: problema della cricca**

Di seguito è mostrato un esempio di **algoritmo non deterministico** per il problema della **cricca** in un grafo non orientato $G = (N, A)$.

```c
void ndcricca( grafo G = (N, A), int n, int k ) {
    int i, j, h;
    boolean S[n];

    for (i = 0; i < n; i++)
        S[i] = choice({true, false});  // scelta non deterministica

    h = 0;
    for (i = 0; i < n; i++) {
        if (S[i]) {
            h++;
            for (j = 0; j < i - 1; j++)
                if (S[j] && (i, j) ∉ A)
                    failure;
        }
    }

    if (h >= k) success;
    else failure;
}
```

Questo algoritmo **crea non deterministicanente** una configurazione di nodi (insieme $S$) e verifica se essi formano una cricca di dimensione almeno $k$.  
L’uso di `choice`, `failure` e `success` mostra chiaramente la logica del non determinismo.

---

### **5. Albero delle scelte**

Un algoritmo non deterministico può essere rappresentato da un **albero delle scelte**, in cui:

- **ogni nodo interno** rappresenta una **scelta intermedia** (un parziale insieme di decisioni),
    
- **ogni foglia** rappresenta una **soluzione completa** o un fallimento.
    

Ogni ramo dell’albero corrisponde a una sequenza di scelte, e una foglia di successo rappresenta una soluzione valida.

---

### **6. Dalla teoria alla pratica: l’enumerazione**

Poiché il non determinismo è un concetto **puramente teorico**, nella pratica occorre **simularlo** con un **algoritmo deterministico**.  
Questa simulazione consiste nel **generare ed esplorare sistematicamente** lo spazio di tutte le possibili soluzioni — cioè **visitare l’albero delle scelte** in modo deterministico.

Tuttavia, l’albero può avere un **numero di nodi superpolinomiale**, quindi questa esplorazione comporta **tempi esponenziali**.

---

### **7. Algoritmi enumerativi**

#### **Definizione**

Un **algoritmo enumerativo** è un algoritmo deterministico che effettua una **visita intelligente dell’albero delle scelte**, cercando di evitare rami inutili o ridondanti.  
È il modo concreto per “emulare” il comportamento di un algoritmo non deterministico.

#### **Esempio: algoritmo enumerativo per la cricca**

```c
void enum_cricca(int i, int h) {
    int j;
    boolean cricca;

    if (i <= n) {
        // Escludo il nodo corrente
        S[i] = FALSE;
        enum_cricca(i + 1, h);

        // Includo il nodo corrente
        S[i] = cricca = TRUE;
        for (j = 0; j < i - 1; j++)
            if (S[j] && (i, j) ∉ A)
                cricca = FALSE;

        if (cricca)
            if (h + 1 == k)
                { /* successo: cricca trovata */ }
            else
                enum_cricca(i + 1, h + 1);
    }
}
```

Ogni **chiamata ricorsiva** genera un **sottoproblema**, cioè un nuovo nodo interno o una foglia dell’albero delle scelte.  
La ricerca termina quando viene trovata una cricca di dimensione $k$.

---

### **8. Ridurre l’esplorazione**

Per evitare esplorazioni inutili, si può limitare la ricorsione con una **condizione di pruning**:

$$  
k - h \leq n - i + 1  
$$

dove:

- $n - i + 1$ è il **numero di nodi ancora da considerare**,
    
- $k - h$ è il **numero di nodi che mancano per completare la cricca**.
    

Se questa condizione non è soddisfatta, non ha senso proseguire su quel ramo dell’albero.

---

### **9. Problemi facili e difficili**

La contrapposizione tra **non determinismo** ed **enumerazione** permette di visualizzare in modo chiaro il concetto di **facile** e **difficile**:

- Un **problema è facile** quando ammette un **algoritmo che percorre un solo cammino radice-foglia** dell’albero delle scelte (come l’algoritmo non deterministico).
    
- Un **problema è difficile** quando ogni algoritmo deterministico deve **esplorare interamente** o quasi interamente l’albero delle scelte.
    

In altre parole, la difficoltà nasce dalla **necessità di enumerare tutte le possibilità**, invece di poter “indovinare” la soluzione corretta.

---

### **10. In sintesi**

- È stato introdotto il **non determinismo** come modello teorico che sceglie sempre la strada giusta verso la soluzione.
    
- È stato definito l’**oracolo** come entità ideale che guida queste scelte.
    
- È stata presentata la **simulazione deterministica** tramite **algoritmi enumerativi**, che esplorano sistematicamente l’albero delle scelte.
    
- È stata fornita una **rappresentazione visiva** della differenza tra **problemi facili e difficili**, basata sulla struttura e sull’ampiezza dell’albero di esplorazione.
    

In conclusione, il non determinismo rappresenta un **modello teorico potente ma irrealizzabile**, mentre l’enumerazione ne è la **controparte pratica**: insieme costituiscono la chiave per comprendere i limiti della computazione e il significato profondo di “problema difficile”.