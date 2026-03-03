## **Lezione 3: Mfset**

---

### **1. Introduzione**

In questa lezione vengono illustrate le caratteristiche di una particolare realizzazione del tipo di dato **insieme**, chiamata **Mfset** (_merge–find set_).

#### **Definizione**

- **Mfset** rappresenta una **partizione di un insieme finito** in sottoinsiemi disgiunti, detti **componenti** o **parti**.
    
- Le operazioni ammesse permettono di:
    
    - determinare **a quale componente appartiene un elemento**;
        
    - **unire** due componenti distinte.
        

---

### **2. Specifica del tipo di dato**

#### **Sintassi**

```text
creamfset: (insieme) → mfset
trova: (tipoelem, mfset) → componente
fondi: (tipoelem, tipoelem, mfset) → mfset
```

#### **Semantica**

$$ \begin{cases} creamfset(A) = S, & post: S \text{ è partizione di } A \text{ in } C_i, \; i = 1, \dots, n = |A| \\[6pt] trova(x, S) = C, & pre: \exists i : x \in C_i, \quad post: C = C_i \\[6pt] fondi(x, y, S) = S', & pre: trova(x, S) \neq trova(y, S), \\[2pt] & post: S' = S - \{C_x, C_y\} \cup \{C_x \cup C_y\} \end{cases} $$ 
#### **Osservazioni**

- Non sono previste **inserzioni** o **cancellazioni** di elementi.
    
- L’unica fase di inserimento avviene nella creazione iniziale con `creamfset`.
    
- Un’implementazione efficiente rappresenta ogni componente ( $C_i$ ) come un **albero radicato**.
    

---

### **3. Esempio: Mfset con alberi**

#### **Situazione iniziale**

Un Mfset con 5 elementi:

```
1   2   3   4   5
```

#### **Operazioni di fusione**

1. `fondi(1, 2, S)`
    
2. `fondi(3, 4, S)`
    
3. `fondi(1, 5, S)`
    
4. `fondi(5, 4, S)`
    

La **rappresentazione ad albero** implica che l’intera partizione venga modellata come una **foresta di alberi radicati**.

- L’operatore **trova** restituisce la **radice dell’albero** a cui appartiene l’elemento.
    
- L’operatore **fondi** unisce due alberi in un unico albero, collegando una radice all’altra.
    

---

### **4. Realizzazione degli operatori**

#### **Ipotesi**

Si assume di poter **accedere direttamente ai nodi** di ogni albero/componente.

#### **Implementazione concettuale**

- `trova(x, S)` risale dal nodo `x` fino alla radice percorrendo la catena dei padri.
    
- `fondi(x, y, S)` unisce i due alberi, rendendo **una radice figlia dell’altra**.
    

---

### **5. Complessità e analisi dell’operatore “fondi”**

Entrambi gli operatori `trova` e `fondi` prevedono di **risalire il cammino nodo → radice**.

Una misura utile per la complessità è il **livello massimo delle foglie** di ciascun albero.

#### **Problema**

Come realizzare l’operatore `fondi` in modo ottimale?

#### **Soluzione**

Per ridurre la profondità massima delle foglie:

- si sceglie come **radice** quella della componente con **più nodi**;
    
- la radice dell’albero **più piccolo** diventa figlia della radice di quello **più grande**.
    

#### **Complessità (1)**

Analizziamo la sequenza di fusioni in cui un generico elemento ( x ) è coinvolto.

- **Caso 1:** se ( x ) appartiene all’albero più grande → il livello di ( x ) **non varia**.
    
- **Caso 2:** se ( x ) appartiene all’albero più piccolo → il livello di ( x ) **aumenta di 1**, ma il nuovo albero avrà almeno **il doppio dei nodi** rispetto a quello precedente.
    

Poiché inizialmente esistono $n = |A|$ componenti singole, il numero di fusioni che possono raddoppiare la dimensione è al più $O(\log n)$.

---

### **6. Complessità (2): esempio illustrativo**

Consideriamo un Mfset con $n = 2^3 = 8$ elementi.

Ad ogni fusione, si uniscono le componenti minori a quelle maggiori.  
Nel **caso pessimo**, un nodo viene sempre unito come figlio della componente più grande (es. il nodo 1).

Questo può accadere circa:

$$  
\log_2 n  
$$

volte, quindi 3 iterazioni per ( n = 8 ).

---

### **7. Complessità (3): risultato finale**

Poiché ad ogni fusione il livello delle foglie aumenta di al massimo 1, e inizialmente ogni elemento si trova al livello 0:

$$  
\text{livello}(x) = O(\log n)  
$$

Pertanto, la **complessità degli operatori `trova` e `fondi` è $O(log n)$**.

---

### **8. Realizzazione pratica di Mfset**

#### **Ipotesi**

- L’insieme è formato dagli interi da 1 a $n$.
    
- L’insieme $A$ indica anche la sua cardinalità.
    
- La foresta è rappresentata tramite:
    
    - un **vettore dei padri**,
        
    - un **vettore delle dimensioni** (`dim`) che tiene traccia del numero di nodi di ogni componente.
        

#### **Codice C**

```c
#define maxlung 100
typedef int tipoelem;
typedef tipoelem insieme, componente, cardinalita;

typedef struct _mfset {
    tipoelem padre;
    cardinalita dim;
} mfset;

insieme A;
mfset S[maxlung];

void creamfset(insieme A, mfset *S) {
    tipoelem i;
    for (i = 0; i < A; i++) {
        S[i].padre = i;
        S[i].dim = 0;
    }
}

componente trova(tipoelem x, mfset *S) {
    componente y;
    if (S[x].padre == x)
        y = x;
    else
        y = trova(S[x].padre, S);
    return (y);
}

void fondi(tipoelem x, tipoelem y, mfset *S) {
    componente i = trova(x, S);
    componente j = trova(y, S);

    if (i != j) {
        if (S[i].dim < S[j].dim) {
            S[i].padre = j;
            S[j].dim += S[i].dim;
        } else {
            S[j].padre = i;
            S[i].dim += S[j].dim;
        }
    }
}
```

---

### **9. Compressione dei percorsi**

#### **Tecnica**

La **compressione dei percorsi** serve a ridurre ulteriormente la complessità media di `trova`.

Consiste nel rendere **figlio diretto della radice** ogni nodo incontrato durante la risalita dal nodo x alla radice.

#### **Modifica nel codice**

Nel corpo della funzione `trova`, la riga `else` diventa:

```c
y = S[x].padre = trova(S[x].padre, S);
```

Così ogni chiamata ricorsiva **riaggancia il nodo direttamente alla radice**, riducendo la profondità dell’albero.

---

### **10. Sintesi finale**

- È stata introdotta una **realizzazione specializzata del tipo di dato “insieme”**.
    
- È stata svolta un’**analisi di complessità dettagliata**, con individuazione del caso pessimo.
    
- È stata illustrata la **tecnica di compressione dei percorsi**, un _escamotage_ per migliorare ulteriormente l’efficienza.