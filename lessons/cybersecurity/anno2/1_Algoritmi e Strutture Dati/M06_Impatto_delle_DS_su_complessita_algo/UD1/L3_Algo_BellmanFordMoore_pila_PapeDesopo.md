## **Lezione 3: Algoritmi di Bellman-Ford-Moore, con pila e di Pape-D’Esopo**

### **1. Introduzione**

La complessità della procedura **SPT** (Shortest Path Tree) dipende fortemente dalla **realizzazione dell’insieme S**, cioè da come vengono gestiti i nodi da visitare.  
In questa lezione analizziamo tre nuove varianti della procedura, ottenute implementando $S$ rispettivamente come:

- **coda** → algoritmo di **Bellman-Ford-Moore (BFM)**
    
- **pila** → algoritmo **con pila**
    
- **dequeue** → algoritmo di **Pape-D’Esopo**
    

Ciascuna scelta cambia radicalmente la **complessità computazionale** e il **comportamento pratico** dell’algoritmo.

---

### **2. Algoritmo di Bellman-Ford-Moore (BFM)**

#### **2.1. Realizzazione**

L’algoritmo BFM deriva direttamente dalla procedura generale **SPT**, ma sostituisce le operazioni sull’insieme $S$ con le primitive tipiche di una **coda FIFO** (First In, First Out).

Gli operatori principali sono:

- `creacoda`
    
- `incoda`
    
- `leggicoda`
    
- `fuoricoda`
    
- `codavuota`
    

Inoltre, per verificare se un nodo è già presente in $S$, si utilizza un **vettore booleano** (`appartiene[u]`).

---

#### **2.2. Ipotesi di validità**

Se l’insieme $S$ è una **coda**, l’algoritmo di Bellman-Ford-Moore mantiene **complessità polinomiale anche in presenza di archi con peso negativo**, cioè anche se:

$$  
c_{uv} < 0  
$$

a condizione che **non esistano cicli di costo negativo**.

---

#### **2.3. Analogia con la BFS**

L’algoritmo BFM può essere interpretato come una **visita in ampiezza (BFS)**, nella quale la “marcatura” di un nodo $u$ non consiste nell’indicarlo come visitato, ma nel **diminuire la sua etichetta $d_u$** ogni volta che viene trovato un cammino più conveniente.  
In pratica, ogni miglioramento dell’etichetta di un nodo comporta il suo reinserimento nella coda per propagare il cambiamento ai nodi adiacenti.

---

#### **2.4. Complessità**

Ogni nodo può essere estratto dalla coda **al più $(n - 1)$ volte**, dove $n$ è il numero di nodi del grafo.  
Questo risultato si può dimostrare formalmente per induzione.

Le operazioni interne al ciclo `while` hanno costo $O(1)$ e vengono eseguite per ogni arco, cioè $m$ volte.

La **complessità complessiva** risulta quindi:

$$  
O(n , m)  
$$

---

### **3. Algoritmo con pila**

#### **3.1. Osservazione generale**

Se invece l’insieme $S$ viene realizzato come **pila** (LIFO), la complessità dell’algoritmo cresce drasticamente fino a diventare **super-polinomiale**.  
Il comportamento peggiore si verifica su particolari **grafi aciclici** nei quali l’estrazione di un nodo $u$ dalla pila e il successivo aggiornamento della sua etichetta determinano l’inserimento di **tutti** i nodi con indice maggiore di $u$.

---

#### **3.2. Caso pessimo**

In questa situazione estrema, ogni nodo può essere inserito nella pila un **numero di volte esponenziale**, perché il miglioramento di un’etichetta si propaga continuamente all’indietro, generando una crescita combinatoria del numero di aggiornamenti.

Ne consegue che l’algoritmo **non è praticabile** per grafi di grandi dimensioni, pur mantenendo la correttezza logica.

---

### **4. Algoritmo di Pape-D’Esopo**

#### **4.1. Struttura dell’insieme S**

L’algoritmo di **Pape-D’Esopo** (1974) utilizza come struttura dati una **dequeue** (_double-ended queue_), cioè una **coda a doppia estremità** che consente sia l’inserimento in coda che in testa.

Funzionamento:

- un nodo $u$ viene inserito **la prima volta in coda**,
    
- ma **nelle volte successive viene inserito in testa**.
    

---

#### **4.2. Motivazione**

L’idea è di sfruttare **subito** i miglioramenti dell’etichetta: se un nodo ottiene un valore di distanza migliore, lo si rimette in testa alla dequeue così da propagare immediatamente la variazione ai nodi vicini.  
Questo comportamento rende l’algoritmo **molto reattivo** ai cambiamenti locali e spesso più efficiente in pratica.

---

#### **4.3. Complessità**

Nel **caso pessimo**, anche l’algoritmo di Pape-D’Esopo può raggiungere una **complessità super-polinomiale**, poiché un nodo può essere rimosso e reinserito molte volte.

Tuttavia, **sperimentalmente** è risultato uno degli algoritmi **più efficienti** per grafi che modellano **reti di trasporto o comunicazione**, cioè per **grafi sparsi e planari**.

Un **grafo planare** è un grafo che può essere disegnato su un piano senza che due archi distinti si incrocino.

---

### **5. Conclusione**

In questa lezione abbiamo introdotto tre varianti della procedura SPT, che differiscono solo nella **gestione dell’insieme $S$**, ma che producono effetti molto diversi sulla **complessità**:

|Algoritmo|Struttura dati di S|Complessità|Note principali|
|---|---|---|---|
|Bellman-Ford-Moore|Coda|$O(nm)$|Gestisce anche archi con peso negativo|
|Con pila|Pila (LIFO)|Super-polinomiale|Può degenerare su grafi aciclici|
|Pape-D’Esopo|Dequeue|Super-polinomiale (ma efficiente su grafi sparsi)|Ottimo per reti stradali o planari|

In sintesi:

- il **Bellman-Ford-Moore** garantisce robustezza teorica e correttezza generale;
    
- la **versione con pila** è inefficiente;
    
- il **Pape-D’Esopo** è molto efficace in contesti pratici specifici, grazie all’inserimento “in testa” dei nodi migliorati.