Con il Modulo 5 si apre una nuova prospettiva nello studio delle strutture dati: quella **orizzontale**.  
Dopo aver analizzato strutture lineari, gerarchiche e reticolari, ora ci concentriamo su modelli in cui i dati vengono **disposti su più dimensioni**, come in **tabelle, matrici, o array bidimensionali**.

L’obiettivo è comprendere come organizzare e manipolare **informazioni parallele o correlate**, dove le relazioni non seguono più una gerarchia o una sequenza, ma si estendono **su più direzioni simultanee**.  
Queste strutture sono alla base della rappresentazione di **immagini, mappe, dataset, e sistemi di calcolo numerico**.

> In breve, il Modulo 5 ci insegna a ragionare “in larghezza”: a costruire e gestire strutture che non scorrono soltanto in linea, ma che si estendono sul piano, dove ogni dato esiste in relazione con altri secondo **coordinate e intersezioni**.

---

## **Lezione 1: Insiemi – specifica e realizzazioni**

---

### **1. Introduzione**

Negli algoritmi analizzati finora è stato spesso necessario rappresentare un gruppo di elementi accomunati da una stessa proprietà.  
Un esempio tipico è la distinzione tra **nodi visitati** e **nodi non visitati** durante l’esplorazione di un grafo.

In questa lezione viene introdotto il **tipo di dato astratto "insieme"**, fondamentale per la rappresentazione di collezioni di elementi.

---

### **2. Definizione di insieme**

Un **insieme** è una collezione di **elementi distinti dello stesso tipo**.  
Rappresenta la **struttura matematica fondamentale** per la gestione di gruppi di dati.

Un insieme può essere descritto in due modi:

1. **Elencando tutti gli elementi**, ad esempio:  
    ( A = {1, 3, 5} )
    
2. **Definendo una proprietà comune**, ad esempio:  
    ( A = { x \mid x \text{ è un numero dispari minore di 6} } )
    

---

### **3. Insiemi vs. Liste**

La differenza principale tra **insiemi** e **liste** riguarda l’ordine e la molteplicità degli elementi:

- Negli insiemi **non esiste un ordine** tra gli elementi:  
    ( A = {1, 3, 5} ) e ( B = {5, 3, 1} ) sono lo **stesso insieme**.
    
- Nelle liste invece **l’ordine conta**:  
    ( L_1 = [1, 3, 5] ) e ( L_2 = [5, 3, 1] ) sono **liste diverse**.
    
- In un insieme **non sono ammesse ripetizioni**:  
    Alla lista ( L = [1, 1, 2, 2] ) corrisponde l’insieme ( A = {1, 2} ).
    

---

### **4. Operatori matematici sugli insiemi**

Gli operatori principali che agiscono sugli insiemi sono:

|**Operatore**|**Significato**|**Simbolo matematico**|
|---|---|---|
|Unione|( A \cup B )|unisce gli elementi dei due insiemi|
|Intersezione|( A \cap B )|restituisce gli elementi comuni|
|Differenza|( A \setminus B )|restituisce gli elementi di A non in B|
|Inclusione|( A \subseteq B )|tutti gli elementi di A appartengono a B|
|Appartenenza|( x \in A )|l’elemento x è contenuto in A|

---

### **5. Specifica sintattica degli operatori**

```text
creainsieme: () → insieme
insiemevuoto: (insieme) → booleano
appartiene: (tipoelem, insieme) → booleano
unione: (insieme, insieme) → insieme
intersezione: (insieme, insieme) → insieme
differenza: (insieme, insieme) → insieme
inserisci: (tipoelem, insieme) → insieme
cancella: (tipoelem, insieme) → insieme
```

---

### **6. Specifica semantica degli operatori**

$$  
\begin{cases}  
creainsieme = A, & post: A = \emptyset \  
insiemevuoto(A) = b, & post: b = vero \text{ sse } A = \emptyset \  
appartiene(x,A) = b, & post: b = vero \text{ sse } x \in A \  
unione(A,B) = C, & post: C = A \cup B \  
intersezione(A,B) = C, & post: C = A \cap B \  
differenza(A,B) = C, & post: C = A \setminus B \  
inserisci(x,A) = A', & post: A' = A \cup {x} \  
cancella(x,A) = A', & post: A' = A \setminus {x}  
\end{cases}  
$$

---

### **7. Realizzazione con vettore booleano**

#### **Ipotesi**

- Gli elementi dell’insieme sono identificabili da numeri interi tra 0 e ( n-1 ).
    
- L’insieme ( A ) è rappresentato da un **vettore booleano** di ( n ) posizioni, dove:
    
    $$  
    A[k] =  
    \begin{cases}  
    1 & \text{se } k \in A \  
    0 & \text{se } k \notin A  
    \end{cases}  
    $$
    

#### **Implementazione in C**

```c
#define TRUE 1
#define FALSE 0
#define DIM 100
typedef long insieme;
insieme A[DIM];

boolean appartiene(tipoelem x, insieme *A) {
    return (A[x]);
}

void inserisci(tipoelem x, insieme *A) {
    A[x] = TRUE;
}

void unione(insieme A, insieme B, insieme *C) {
    long i;
    for (i = 0; i < DIM; i++)
        C[i] = A[i] || B[i];
}

void intersezione(insieme A, insieme B, insieme *C) {
    long i;
    for (i = 0; i < DIM; i++)
        C[i] = A[i] && B[i];
}
```

#### **Osservazioni sulla complessità**

- `appartiene`, `inserisci`, `cancella` → **O(1)**
    
- `unione`, `intersezione`, `differenza` → **O(n)**
    

Questa rappresentazione è **semplice da implementare**, ma:

- spreca memoria (un vettore grande quanto l’universo degli elementi);
    
- è adatta solo per **strutture statiche** (insiemi di dimensione fissa);
    
- risulta inefficiente per insiemi molto grandi o dinamici.
    

---

### **8. Realizzazione con liste non ordinate**

Si può rappresentare un insieme anche con una **lista** (struttura dinamica).

#### **Vantaggi**

- Nessuno spreco di memoria.
    
- Gli elementi possono essere di **tipo qualsiasi**.
    

#### **Svantaggi**

- Inefficienza negli operatori fondamentali.
    

#### **Complessità**

Se gli operatori della lista sono in ( O(1) ):

|**Operatore**|**Complessità**|
|---|---|
|creainsieme, insiemevuoto|O(1)|
|appartiene, inserisci, cancella|O(n)|
|unione, intersezione, differenza|O(n·m) con (|

Questo perché per ogni elemento di A è necessario **scandire tutta la lista B**.

---

### **9. Realizzazione con liste ordinate**

Se sugli elementi dell’insieme è definita una **relazione d’ordine totale** (es. ≤), allora si può usare una **lista ordinata**.

#### **Vantaggi**

- La complessità degli operatori `unione`, `intersezione`, `differenza` migliora a **O(n + m)**.  
    Questo è il limite ottimale, poiché la **fusione di due sequenze ordinate** ha complessità ( \Omega(n + m) ).
    

#### **Svantaggi**

- Gli altri operatori (come `appartiene` o `inserisci`) restano comunque **lineari**.
    

---

### **10. Sintesi finale**

- È stato introdotto il **tipo di dato astratto “insieme”**.
    
- È stata formalizzata una **struttura dati** già utilizzata in altri algoritmi (es. grafi).
    
- La principale difficoltà riguarda la **realizzazione efficiente**.
    
- Le prossime lezioni introdurranno la **realizzazione Mfset**, più ottimizzata e scalabile.