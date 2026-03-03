## **Lezione 3: Puzzle di Merkle**

### **1. Introduzione**

In questa lezione viene presentato il **protocollo dei Puzzle di Merkle**, ideato da Ralph Merkle nel 1974.  
È un metodo di **accordo su chiavi** che, a differenza di Diffie-Hellman, **non si basa su ipotesi computazionali** (come il logaritmo discreto), ma su un approccio puramente probabilistico e di complessità di ricerca.  
L’idea è permettere a due interlocutori, **Alice** e **Bob**, di stabilire una chiave condivisa, rendendo però **impraticabile per un attaccante** la risoluzione simultanea di tutti i possibili puzzle.

---

### **2. Concetto di puzzle crittografico**

Un **puzzle** è un messaggio cifrato che contiene una piccola quantità di informazioni segrete.  
L’obiettivo è che:

- **la soluzione di un singolo puzzle** sia _ragionevolmente facile_ (tempo $t$),
    
- ma **risolvere tutti i puzzle** sia _computazionalmente proibitivo_ (tempo proporzionale a $t \cdot n$).
    

Ogni puzzle è costruito in modo da nascondere una chiave segreta all’interno di un cifrario che può essere rotto solo con una **ricerca esaustiva limitata**.

---

### **3. Struttura di un puzzle**

Un puzzle è definito come una terna:

$$  
\text{Puzzle}(x, ID, S)  
$$

dove:

- **$x$** è la _soluzione segreta_ del puzzle (la chiave da ricavare);
    
- **$ID$** è l’identificativo univoco del puzzle;
    
- **$S$** è un valore noto usato per garantire l’unicità della soluzione (ad esempio 32 bit nulli).
    

Per costruirlo:  
$$  
y \leftarrow \text{CBC-DES}_k(x, ID, S)  
$$  
e si restituisce $(y, \text{primi 20 bit di } k)$.

La chiave $k$ è scelta casualmente, e trovare $x$ implica testare in media $2^{35}$ chiavi DES — uno sforzo computazionale non banale, ma fattibile.

---

### **4. Protocollo dei Puzzle di Merkle**

1. **Generazione dei puzzle (Alice):**
    
    - Alice genera **$n$ chiavi casuali** $x_1, x_2, \dots, x_n$.
        
    - Per ciascuna, costruisce un puzzle:  
        $$  
        \text{Puzzle}_i = \text{Puzzle}(x_i, ID_i, S)  
        $$
        
    - Invia l’intero insieme di puzzle ${\text{Puzzle}_1, \dots, \text{Puzzle}_n}$ a Bob.
        
2. **Scelta e risoluzione (Bob):**
    
    - Bob sceglie casualmente un puzzle $\text{Puzzle}_j$ e lo risolve, trovando $(x_j, ID_j)$.
        
    - La coppia $(x_j, ID_j)$ diventa la **chiave condivisa segreta** con Alice.
        
3. **Accordo finale:**
    
    - Alice conosce tutte le chiavi $x_i$ e quindi riconosce quale puzzle Bob ha risolto tramite l’identificativo $ID_j$.
        
    - Entrambi ottengono la **stessa chiave segreta $x_j$**, sconosciuta a chiunque altro.
        

---

### **5. Complessità computazionale**

|Partecipante|Operazione|Complessità|
|---|---|---|
|**Alice**|Generazione di $n$ puzzle|$\Theta(n)$|
|**Bob**|Risoluzione di un puzzle|$\Theta(t)$ oppure $\Theta(n)$ se $t = n$|
|**Attaccante**|Risoluzione media di $n/2$ puzzle|$\Theta(t \cdot n)$ oppure $\Theta(n^2)$|

In pratica, se **$n = \Theta(t)$**, Bob impiega un tempo ragionevole per risolvere un puzzle, mentre l’attaccante dovrebbe risolverne circa $n/2$, rendendo l’attacco **quadratico** e quindi impraticabile per grandi valori di $n$.

---

### **6. Analisi di sicurezza**

- La **sicurezza** del sistema **non dipende** da alcuna ipotesi matematica complessa (come nel caso del logaritmo discreto).
    
- È invece basata sulla **disparità di sforzo computazionale**:  
    chi partecipa legittimamente risolve _un solo puzzle_, mentre un attaccante deve risolverne _molti_.
    
- Tuttavia, per rendere il sistema realmente sicuro, è necessario usare **valori di $n$ molto grandi**, rendendolo poco pratico per applicazioni moderne.
    

---

### **7. Sintesi finale**

- Il **Puzzle di Merkle** è un metodo di accordo su chiavi **non basato su presupposti computazionali**, ma su differenze di complessità tra partecipanti e attaccante.
    
- La **sicurezza cresce quadraticamente** con il numero di puzzle $n$, ma ciò lo rende **inefficiente nella pratica**.
    
- È un protocollo **storicamente fondamentale**, poiché ha introdotto per la prima volta l’idea di **scambio di chiavi senza canale sicuro** — base concettuale da cui nacque il successivo schema **Diffie-Hellman**.