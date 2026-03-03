
## **Lezione 1: Coda di priorità**

---

### **1. Introduzione**

In questa lezione introduciamo un nuovo tipo di struttura dati: la **coda di priorità**, concetto chiave per comprendere il funzionamento degli **heap**.  
Una coda di priorità è un insieme di elementi su cui è definita una **relazione d’ordine totale** (indicata con “≤”), che consente di **inserire** nuovi elementi e **estrarre** l’elemento di **priorità minima** (o massima, a seconda della convenzione).

In altre parole, non si tratta di una coda “classica” FIFO, ma di una struttura in cui l’ordine di estrazione **dipende dal valore** degli elementi, non dal momento dell’inserimento.

---

### **2. Specifica sintattica e semantica**

#### **Operatori fondamentali**

|Operatore|Tipo|Descrizione|
|---|---|---|
|`creaprioricoda()`|→ prioricoda|Crea una nuova coda di priorità vuota|
|`inserisci(x, C)`|tipoelem × prioricoda → prioricoda|Inserisce l’elemento `x` nella coda `C`|
|`min(C)`|prioricoda → tipoelem|Restituisce il valore minimo della coda|
|`cancellamin(C)`|prioricoda → prioricoda|Rimuove l’elemento minimo dalla coda|

---

#### **Significato operativo**

```text
creaprioricoda() = C
post: C = Λ, coda vuota

inserisci(x, C) = C'
post: C' = C ∪ {x}

min(C) = x
pre: C ≠ Λ
post: x è il valore minimo in C

cancellamin(C) = C'
pre: C ≠ Λ
post: C' = C \ {x}, con x = min(C)
```

---

### **3. Realizzazione con liste**

Una coda di priorità con ( n ) elementi può essere realizzata anche tramite **liste**, in due varianti:

#### **a) Lista ordinata**

- L’inserimento è costoso, perché deve mantenere l’ordine:  
    $$ O(n) $$
    
- Gli operatori `min` e `cancellamin` sono invece immediati:  
    $$ O(1) $$
    

#### **b) Lista non ordinata**

- L’inserimento è veloce:  
    $$ O(1) $$
    
- Ma la ricerca e rimozione del minimo richiedono una scansione completa:  
    $$ O(n) $$
    

In entrambi i casi, la complessità **non è ottimale** per insiemi di grandi dimensioni.

---

### **4. Verso una soluzione efficiente – l’Heap**

Per migliorare l’efficienza, possiamo rappresentare la coda di priorità con un **vettore Heap**.  
In questo modo, l’insieme degli elementi può essere interpretato come un **albero binario quasi completo**, dove ogni nodo segue la **proprietà di ordinamento**:

> Ogni nodo contiene un valore **maggiore o uguale** di quello del suo **padre** (per uno heap min, vale l’opposto).

---

### **5. Proprietà strutturali dello Heap**

Un vettore ( H ) rappresenta uno heap se, e solo se, l’albero ( B ) da esso derivato soddisfa le seguenti condizioni:

1. Se ( h ) è il livello massimo, ci sono esattamente ( 2^{h-1} ) nodi ai livelli inferiori.
    
2. Tutte le foglie al livello massimo sono **addossate a sinistra**.
    
3. Ogni nodo contiene un valore **maggiore** di quello del padre.
    

---

### **6. Esempio illustrativo**

Sia la coda:

$$  
C = {5, 10, 8, 11, 13, 12, 9, 18, 3, 6}  
$$

L’albero binario associato ( B ) (heap) verifica tutte le proprietà:

![[Pasted image 20251018183441.png]]
L’albero è quasi completo, con tutte le foglie a sinistra e ogni nodo maggiore del suo padre.

---

### **7. Correlazione tra vettore e albero**

Dalle proprietà 1 e 2, si ricava che i **nodi di livello k** occupano le **prime n posizioni** del vettore ( H ), con le seguenti relazioni:

$$  
\text{figliosinistro}(i) = 2i  
$$

$$  
\text{figliodestro}(i) = 2i + 1  
$$

$$  
\text{padre}(i) = \left\lfloor \frac{i}{2} \right\rfloor  
$$

(Il vettore è indicizzato da 1.)

---

### **8. Operatori fondamentali nello Heap**

#### **a) min(C)**

Restituisce il valore minimo della coda:  
→ corrisponde alla **radice** dell’albero.  
**Complessità:** $O(1)$

---

#### **b) cancellamin(C)**

1. Sostituisce la radice con la foglia più a destra del livello massimo (per mantenere le proprietà 1 e 2).
    
2. Fa “scendere” la nuova radice tramite **scambi padre–figlio** fino a ristabilire la proprietà 3.
    

**Complessità:** $O(\log n)$

![[Pasted image 20251018183727.png]]

---

#### **c) inserisci(x, C)**

1. Inserisce l’elemento `x` come **foglia più a destra**.
    
2. Lo fa “salire” tramite **scambi padre–figlio** fino a rispettare la proprietà 3.
    

**Complessità:** $O(\log n)$

![[Pasted image 20251018183747.png]]

---

### **9. Sintesi operativa**

|Operatore|Descrizione|Complessità|
|---|---|---|
|`min`|Lettura della radice|O(1)|
|`inserisci`|Inserimento + risalita|O(log n)|
|`cancellamin`|Sostituzione + discesa|O(log n)|

---

### **10. Conclusioni**

Le **code di priorità** rappresentano il ponte concettuale tra le **liste** e gli **heap**:  
offrono un modo efficiente per gestire insiemi ordinati di dati, e la loro **implementazione con heap** permette di raggiungere la **complessità ottimale logaritmica** nelle operazioni di inserimento e cancellazione.

> 🧠 Nella prossima lezione vedremo come lo heap diventa **motore dell’algoritmo di ordinamento HeapSort**, un metodo semplice ma asintoticamente ottimo.