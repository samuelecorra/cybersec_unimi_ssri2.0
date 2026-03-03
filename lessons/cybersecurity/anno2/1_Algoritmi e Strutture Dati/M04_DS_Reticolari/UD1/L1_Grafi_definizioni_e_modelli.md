Con il Modulo 4 entriamo in una nuova dimensione della rappresentazione dei dati: quella **reticolare**.  
Dopo aver studiato strutture lineari (liste, pile, code) e gerarchiche (alberi, heap), ci troviamo ora davanti a un modello più generale e flessibile, capace di **rappresentare relazioni complesse e non unidirezionali** tra gli elementi: le **reti**.

Una **struttura reticolare** (o rete) consente di descrivere situazioni in cui un elemento può essere connesso a **più altri elementi in modo arbitrario**, non soltanto secondo una gerarchia o una sequenza.  
Questo approccio è alla base di moltissimi sistemi reali: dalle **reti informatiche** alle **mappe concettuali**, dai **grafi di connessione** nei social network agli **algoritmi di ricerca di percorsi ottimali**.

Nel corso di questo modulo impareremo a:

- riconoscere **quando** una rete è la struttura adatta a modellare un problema;
    
- conoscere gli **operatori fondamentali** che ne permettono la manipolazione;
    
- comprendere **come la scelta della realizzazione** (matrice, liste di adiacenza, ecc.) influenzi **l’efficienza** delle operazioni.
    

> In sintesi, il Modulo 4 ci insegnerà a pensare come **architetti delle connessioni**: a progettare strutture che non si limitano a contenere dati, ma li **collegano** in modo intelligente e strategico.


---

## **Lezione 1: Grafi – Definizioni e modelli**

---

### **1. Introduzione**

Questa lezione introduce il **modello matematico del grafo**, una delle strutture fondamentali dell’informatica teorica e applicata.  
Attraverso i grafi possiamo rappresentare **relazioni complesse tra oggetti**, visualizzare **reti di connessioni** e risolvere problemi come percorsi, turnazioni, assegnamenti e ottimizzazioni.

Gli obiettivi principali sono:

- definire formalmente cos’è un grafo e le sue varianti;
    
- comprendere la differenza tra **grafi orientati** e **non orientati**;
    
- introdurre esempi pratici di modellazione di problemi reali.
    

---

### **2. Definizione di grafo orientato**

Un **grafo orientato** è definito come una **coppia**:

$$  
G = (N, A)  
$$

dove:

- $N$ è un insieme finito di **nodi** o **vertici**;
    
- $A$ è un insieme finito di **coppie ordinate** di nodi, dette **archi**.
    

Se indichiamo con $n$ il numero di nodi e con $m$ il numero di archi, allora:

$$  
0 \leq m \leq n(n - 1)  
$$

poiché da ogni nodo possono uscire al più $(n - 1)$ archi distinti.

---

### **3. Esempio di grafo orientato**

Sia:

$$  
N = {1, 2, 3, 4}  
$$

$$  
A = { (1,2), (1,3), (2,4), (3,2), (4,2), (4,3) }  
$$

Il grafo può essere rappresentato visivamente come una rete di collegamenti diretti tra i nodi.

Esempio di applicazione: **porti e aeroporti**, dove i nodi rappresentano le città e gli archi le rotte disponibili.

---

### **4. Cammini in un grafo orientato**

Dato un grafo orientato $G$, un **cammino** è una sequenza di nodi:

$$  
u_0, u_1, \dots, u_k \quad \text{tale che } (u_i, u_{i+1}) \in A \text{ per } i = 0, \dots, k - 1  
$$

- Se non ci sono nodi ripetuti ($u_i \neq u_j$ per $0 \leq i < j \leq k$), il cammino è **semplice**.
    
- Se $u_0 = u_k$, il cammino è **chiuso**.
    
- Un **cammino semplice e chiuso**, con unica ripetizione $u_0 = u_k$, è detto **ciclo**.
    

#### **Esempi**

- Cammino: 1, 2, 4, 2
    
- Cammino semplice: 1, 3, 2, 4
    
- Ciclo: 1, 2, 4, 3, 1
    

---

### **5. Forte connessione**

Un grafo orientato è detto **fortemente connesso** se, per ogni coppia di nodi distinti $u$ e $v$, esistono **due cammini**:

- uno da $u$ a $v$
    
- e uno da $v$ a $u$
    

> Esempio: un grafo in cui esiste un cammino da 1 → 4 ma non da 4 → 1 **non è fortemente connesso**.

---

### **6. Grafi non orientati**

Un **grafo non orientato** è un caso particolare in cui l’insieme degli archi è composto da **coppie non ordinate**:

$$  
A = { [i, j] }  
$$

In questo caso, gli archi $[i, j]$ e $[j, i]$ rappresentano **lo stesso collegamento**.

Questa struttura modella **relazioni simmetriche**, come:

- collegamenti stradali bidirezionali,
    
- amicizie reciproche,
    
- connessioni di rete non direzionate.
    

---

### **7. Catene e connessione nei grafi non orientati**

Dato un grafo non orientato $G$, una **catena** è una sequenza di nodi:

$$  
u_0, u_1, \dots, u_k \quad \text{tale che } [u_i, u_{i+1}] \in A \text{ per } i = 0, \dots, k - 1  
$$

- Se non ci sono nodi ripetuti ($u_i \neq u_j$ per $0 \leq i < j \leq k$), la catena è **semplice**.
    
- Se $u_0 = u_k$, la catena è **chiusa**.
    
- Una catena semplice e chiusa con tutti archi distinti, e unica ripetizione $u_0 = u_k$, è detta **circuito**.
    

Un grafo non orientato è detto **connesso** se per ogni coppia di nodi distinti $u, v$ esiste una catena che li collega.

---

### **8. Grafi e alberi**

- Un **albero libero** è un grafo non orientato **connesso**, in cui per ogni coppia di nodi esiste **una sola catena semplice**.
    
- Un albero libero è un grafo connesso con il **minimo numero di archi**, cioè:
    

$$  
m = n - 1  
$$

> Gli alberi possono quindi essere visti come **grafi connessi minimali**, privi di cicli.

---

### **9. Modellazione con grafi**

I grafi sono strumenti potenti per modellare problemi reali complessi.

#### **Esempio 1 – Il problema del commesso viaggiatore (TSP)**

Siano date $n$ città e una distanza $d_{ij}$ tra la città $i$ e la città $j$.  
Dato un intero $k$, ci si chiede:

> È possibile partire da una città, visitare **tutte le altre esattamente una volta**, e tornare al punto di partenza percorrendo una distanza totale **non superiore a $k$**?

Questo problema è noto come **Circuito Hamiltoniano**, uno dei casi classici di problema NP-difficile.

---

#### **Esempio 2 – Turnazione del personale**

Dato un insieme di $n$ lavoratori, un orizzonte di $h$ giorni e $L$ turni giornalieri, si cerca un assegnamento tale che la **durata massima** del turno non superi un certo valore $k$.

Questo tipo di problema può essere modellato come un grafo in cui:

- i nodi rappresentano **le persone o i turni**,
    
- gli archi rappresentano **le relazioni di compatibilità o precedenza**.
    

---

### **10. Applicazione didattica – Grafi delle propedeuticità**

Nel corso di Algoritmi e Strutture Dati, anche le **relazioni tra argomenti** possono essere rappresentate tramite un grafo orientato aciclico (DAG):

```
Programmazione → Complessità → Liste → Alberi → Grafi → Ricorsione → Formalismo
```

Questo grafo mostra la **dipendenza logica** tra i vari argomenti: ogni concetto si costruisce sul precedente.

---

### **11. Sintesi finale**

|Concetto|Descrizione|
|---|---|
|**Grafo orientato**|Coppie ordinate di nodi (relazioni direzionali)|
|**Grafo non orientato**|Coppie non ordinate (relazioni simmetriche)|
|**Cammino / Catena**|Sequenza di nodi collegati da archi|
|**Ciclo / Circuito**|Cammino o catena chiusa con un’unica ripetizione|
|**Connessione**|Esistenza di un percorso tra due nodi|
|**Albero libero**|Grafo connesso e aciclico con $m = n - 1$|
|**Applicazioni**|TSP, turnazioni, reti, dipendenze logiche|

---

> 🧠 **In sintesi:**  
> I grafi uniscono logica e immaginazione.  
> Ci permettono di “vedere” i problemi come reti di relazioni, di percorrerli come algoritmi e di risolverli come architetti della complessità.


---