## **Lezione 2: Pseudocodice e Problemi Computazionali**

### **1. Introduzione**

Questa lezione ha due obiettivi principali:

1. **Definire lo pseudocodice** e comprenderne le potenzialità attraverso esempi concreti.
    
2. **Introdurre il concetto di problema computazionale** e il modo in cui può essere rappresentato tramite strutture dati adeguate.
    

Lo pseudocodice è un linguaggio intermedio tra la descrizione informale e il codice vero e proprio.  
Serve a **esprimere chiaramente la logica** di un algoritmo senza preoccuparsi dei dettagli di sintassi di un linguaggio di programmazione reale.

La lezione è articolata in due parti:

- la prima tratta lo **pseudocodice** e le sue convenzioni,
    
- la seconda affronta la **rappresentazione dei problemi computazionali**, con esempi classici.
    

---

### **2. Pseudocodice**

I linguaggi di programmazione reali permettono di descrivere algoritmi in modo preciso ma spesso troppo dettagliato.  
Quando i problemi diventano complessi — come nel caso di grafi o algoritmi di ottimizzazione — il codice può diventare difficile da leggere e analizzare.

Per questo si introduce lo **pseudocodice**, cioè un linguaggio “semplificato” che mantiene:

- il **significato computazionale** dell’algoritmo,
    
- ma **omette** i dettagli di implementazione.
    

Lo pseudocodice non è compilabile, ma serve a **focalizzarsi sulla logica**, non sulla sintassi.

---

### **2.1 Accesso a strutture**

Quando lavoriamo con **strutture dati complesse** (come alberi o grafi), lo pseudocodice consente di semplificare l’accesso ai campi interni.

Esempio: calcolo della distanza di ogni nodo dalla radice in un **albero binario**.

```c
void calcolaDistanze(nodo u, binalbero T, int d) {
    if (binradice(T) == u)
        d[u] = 0;
    else
        d[u] = 1 + d[binpadre(u, T)];

    if (!sinistrovuoto(u, T))
        calcolaDistanze(figliosinistro(u, T), T, d);

    if (!destrovuoto(u, T))
        calcolaDistanze(figliodestro(u, T), T, d);
}
```

In questo esempio, lo pseudocodice permette di accedere al vettore `d` **direttamente tramite la variabile nodo `u`**, senza preoccuparsi di come siano effettivamente memorizzati i riferimenti nella struttura.

---

### **2.2 Operazioni matematiche**

Spesso, in uno pseudocodice, si introducono **funzioni matematiche** per rendere più compatta la scrittura.

Esempio: trovare l’indice dell’elemento minimo in un vettore `V` di lunghezza `n`.

Versione esplicita:

```c
int min = V[0], m = 0;
for (int i = 0; i < n; i++)
    if (V[i] < min) {
        min = V[i];
        m = i;
    }
```

Versione in pseudocodice matematico:

$$  
m = \arg\min { V[i] : i = 0, \ldots, n-1 }  
$$

Tuttavia, è fondamentale ricordare che anche se la scrittura è sintetica, **la complessità rimane $O(n)$**, perché la funzione `argmin` implica la scansione completa del vettore.

---

### **2.3 Scansione di insiemi**

Lo pseudocodice può sfruttare costrutti matematici per iterare sugli insiemi.  
Il più tipico è **for each**, che semplifica la scansione rispetto ai cicli `for`.

Esempi:

```c
for each v ∈ (A : v > λ) { ... }
```

oppure

```c
for each v ∈ (A ∪ B) { ... }
```

Queste scritture sono eleganti, ma è importante **considerare il costo reale** dell’operazione.  
Ad esempio, calcolare l’unione $A ∪ B$ può non essere lineare in tutti i casi.

---

### **2.4 Variabili di supporto e output multiplo**

In pseudocodice si può omettere la dichiarazione di **variabili ausiliarie** (come contatori o indici), per rendere più leggibile l’algoritmo.

Si possono inoltre scrivere **funzioni che restituiscono più di un valore** contemporaneamente.

Esempio:

```c
(int, int) minimoVettore(int *V) {
    int min = V[0], m = 0;
    for (int i = 0; i < n; i++)
        if (V[i] < min) {
            min = V[i];
            m = i;
        }
    return (min, m);
}

(minValue, indiceMinValue) = minimoVettore(V);
```

Questo stile rende la descrizione **più chiara e concisa**, e in fase di analisi **non richiede controlli** di correttezza sui dati (si assume che il vettore sia valido e di dimensione corretta).

---

### **2.5 Utilizzo di altri algoritmi**

Un’altra convenzione dello pseudocodice è **citare direttamente algoritmi già noti**, senza riscriverli.

Esempio: se un algoritmo richiede di ordinare un vettore, possiamo semplicemente scrivere:

```c
MergeSort(V);
```

invece di implementare tutto il codice di MergeSort.

Questo approccio migliora la leggibilità e concentra l’attenzione sul **flusso logico principale** dell’algoritmo, piuttosto che sui dettagli tecnici.

---

### **2.6 Rappresentazione dei problemi computazionali**

Quando si lavora con **problemi complessi**, è utile racchiudere i dati in **strutture astratte**:

- **P** rappresenta il **problema** (Problem): contiene i dati di input.
    
- **S** rappresenta la **soluzione** (Solution): contiene i risultati dell’algoritmo.
    

In questo modo, lo pseudocodice può riferirsi in modo conciso a:

```
P -> dati di input
S -> struttura della soluzione
```

Questa convenzione verrà utilizzata in tutti gli esempi seguenti.

---

### **3. Problemi computazionali classici**

Vediamo ora alcuni problemi computazionali fondamentali, ognuno rappresentato tramite le strutture `P` e `S`.

---

#### **3.1 Problema dello zaino (Knapsack)**

**Definizione:**  
Dati $n$ oggetti, ognuno con un **valore** $v_i$ e un **peso** $p_i$, e uno zaino con **capacità** $C$, selezionare un sottoinsieme $Z \subseteq {1, \ldots, n}$ tale che:

$$  
\sum_{i \in Z} p_i \le C \quad \text{e} \quad \sum_{i \in Z} v_i \ge k  
$$

**Rappresentazione:**

- **P:** contiene i vettori dei pesi $p_i$, dei valori $v_i$, la capacità $C$, il numero di oggetti $n$ e la soglia $k$.
    
- **S:** contiene il valore totale $z$, il peso totale $p$, e l’insieme $Z$ degli oggetti selezionati.
    

---

#### **3.2 Problema della cricca (Clique)**

**Definizione:**  
Dato un grafo non orientato $G = (N, A)$ con pesi $p_u$ sui nodi, trovare un sottoinsieme $C \subseteq N$ tale che:

- ogni coppia di nodi in $C$ sia **adiacente**,
    
- e la somma dei pesi sia $\ge k$.
    

**Rappresentazione:**

- **P:** contiene il grafo $G$ e il valore $k$.
    
- **S:** contiene l’insieme $C$ dei nodi della cricca e la somma totale dei pesi $z$.

![[Pasted image 20251018204450.png]]

---

#### **3.3 Assegnamento di lavori a macchine**

**Definizione:**  
Dati $n$ lavori e $m$ macchine, ogni lavoro $i$ richiede un tempo $d_i$.  
Il problema consiste nell’assegnare i lavori alle macchine in modo da completare tutto entro un tempo $k$.

**Rappresentazione:**

- **P:** vettore delle durate $d_i$ e soglia $k$.
    
- **S:**
    
    - $L[i]$ → macchina assegnata al lavoro $i$,
        
    - $D[h]$ → tempo totale dei lavori assegnati alla macchina $h$.
        

---

#### **3.4 Commesso viaggiatore (Traveling Salesman Problem, TSP)**

**Definizione:**  
Dato un grafo non orientato $G = (N, A)$ con pesi $p_{uv}$ sugli archi, trovare un **circuito Hamiltoniano** di costo minimo (o $\le k$).

$$  
\sum_{[u,v] \in C} p_{uv} \le k  
$$

**Rappresentazione:**

- **P:** contiene il grafo $G$ e il valore $k$.
    
- **S:** contiene la lista di nodi $C$ (il tour) e il costo totale $z$.
    
![[Pasted image 20251018204522.png]]

---

#### **3.5 Taglio di un grafo (Graph Cut)**

**Definizione:**  
Dato un grafo non orientato $G = (N, A)$ con pesi $p_{uv}$ e numero di nodi pari $n = |N|$, dividere $N$ in due insiemi $N_1$ e $N_2$ di uguale cardinalità, in modo che:

$$  
\sum_{(u,v) \in A, , u \in N_1, v \in N_2} p_{uv} \le k  
$$

**Rappresentazione:**

- **P:** grafo $G$ e valore $k$.
    
- **S:** insiemi $N_1$, $N_2$ e variabile $z$ (costo del taglio).
    
![[Pasted image 20251018204548.png]]

---

### **4. Conclusioni**

In questa lezione abbiamo:

- definito e motivato l’uso dello **pseudocodice**,
    
- mostrato come semplifica la descrizione e l’analisi degli algoritmi,
    
- introdotto alcuni **problemi computazionali fondamentali**, rappresentati con le strutture `P` e `S`.
    

Questo approccio consente di **unificare la descrizione dei problemi** e di **concentrarsi sulla logica algoritmica**, lasciando da parte i dettagli implementativi.

Con questa lezione si conclude l’**Unità Didattica 2** e l’intero **Modulo 6**, completando il percorso che collega:

- le **strutture dati**
    
- alla **complessità degli algoritmi**,
    
- fino alla **progettazione di soluzioni computazionali generali**.