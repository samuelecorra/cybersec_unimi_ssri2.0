La **tecnica di progetto Backtrack** (o **ricerca con ritorno**) è una delle strategie fondamentali per la **risoluzione sistematica di problemi combinatori**, dove si devono esplorare molte possibili soluzioni per trovare quella corretta — o tutte quelle ammissibili.

L’idea di base consiste nel **costruire la soluzione passo dopo passo**, **verificando a ogni scelta parziale** se essa può ancora condurre a una soluzione valida.  
Se una scelta porta a una situazione impossibile o incoerente, l’algoritmo **“torna indietro” (backtrack)** al passo precedente per tentare una strada alternativa.

Questa tecnica è particolarmente potente nei problemi in cui:

- lo **spazio delle soluzioni è ampio ma strutturato** (ad esempio combinazioni, permutazioni, disposizioni);
    
- è possibile **verificare parzialmente** la validità di una soluzione prima che sia completa.
    

Il modulo ha quindi lo scopo di:

- **comprendere lo schema generale del backtracking**,
    
- **individuare i suoi elementi caratteristici** (stato, scelte, vincoli, test di validità e ricorsione),
    
- **riconoscere i problemi risolvibili** con questa tecnica (come Sudoku, N-Queens, labirinti, subset sum, ecc.),
    
- **applicare il metodo passo per passo** a casi concreti,
    
- e **valutarne la complessità**, spesso esponenziale, ma riducibile tramite pruning e ottimizzazioni.
    

Il **backtracking** rappresenta una strategia di ricerca **profonda e controllata**, che esplora lo spazio delle soluzioni in modo **intelligente e selettivo**, garantendo una visione completa ma ordinata del processo decisionale.


---

## **Lezione 1: Progetto di algoritmi Backtrack**

### **1. Introduzione**

In questa lezione viene introdotta la **tecnica di progetto Backtrack**, una strategia generale per risolvere problemi in cui è necessario **esplorare progressivamente le possibili soluzioni**, ma con la possibilità di **tornare indietro** quando un cammino scelto non porta a un risultato valido.

La tecnica si basa su **due fasi fondamentali**:

1. **Costruzione della soluzione**, durante la quale si compiono scelte successive per estendere una soluzione parziale.
    
2. **Distruzione della soluzione**, che consiste nell’annullare una o più scelte quando si scopre che non possono condurre a una soluzione completa.
    

Un algoritmo può essere definito **di tipo Backtrack** se al suo interno sono presenti strumenti sia per **costruire** la soluzione sia per **ritornare sui propri passi** eliminando decisioni non valide.  
In altre parole, il backtracking è una **ricerca con esplorazione e revisione controllata**: ogni scelta viene verificata e, se porta a un fallimento, l’algoritmo **“fa marcia indietro”** fino a un punto in cui può esplorare un’alternativa.

---

### **2. Esempi di algoritmi di tipo Backtrack**

Un primo esempio classico di backtracking si trova negli **algoritmi di visita di un albero**:

- **Previsita (preorder)**
    
- **Postvisita (postorder)**
    
- **Invisita (inorder)**
    

In ciascuno di essi, l’elaborazione di un nodo **dipende dai risultati delle visite dei sottoalberi**, e la procedura **ritorna** automaticamente al livello precedente dopo aver completato l’esplorazione di un ramo: questo comportamento è esattamente ciò che caratterizza il **backtrack**.

Anche la **visita in profondità di un grafo (DFS, Depth-First Search)** è un tipico esempio di algoritmo backtracking:  
la DFS esplora un nodo, visita ricorsivamente i vicini e, quando non ci sono più nodi raggiungibili, **torna indietro** al precedente nodo per esplorare nuove diramazioni.

---

### **3. Confronto: Backtrack vs Greedy**

Per comprendere meglio la natura del backtracking, è utile confrontarlo con la strategia **greedy**.

Supponiamo di dover selezionare un sottoinsieme $S$ da un insieme $M$, con $S \subseteq M$.

- Un **algoritmo greedy costruttivo** parte da $S = \emptyset$ e, ad ogni passo, **aggiunge** un elemento scelto secondo un criterio di ottimalità locale.  
    L’obiettivo è costruire la soluzione passo dopo passo, senza mai tornare indietro.
    
- Un **algoritmo greedy distruttivo**, invece, parte da $S = M$ e, ad ogni passo, **rimuove** un elemento secondo un criterio analogo, fino a ottenere la soluzione desiderata.
    

La **tecnica di backtrack** combina entrambe le logiche:  
essa **costruisce** la soluzione, ma, se necessario, **torna indietro** per **eliminare** scelte non compatibili.  
In questo modo esplora in maniera sistematica **tutte le configurazioni possibili**, fino a trovare quella (o quelle) valide.

---

### **4. Backtrack ed enumerazione**

La tecnica di backtrack è alla base degli **algoritmi di enumerazione**, ossia di quegli algoritmi che **provano tutte le combinazioni possibili** di scelte per determinare:

- una **soluzione valida** (ad esempio, trovare una disposizione che rispetta certi vincoli), oppure
    
- la **migliore soluzione** (ad esempio, quella che massimizza o minimizza una funzione obiettivo).
    

Dal punto di vista teorico e pratico, gli algoritmi di enumerazione rappresentano **l’applicazione più importante del backtracking**, in quanto permettono di esplorare in modo strutturato spazi di ricerca molto vasti, riducendo drasticamente il numero di tentativi grazie al **controllo dei vincoli** e all’**esclusione anticipata** delle strade non promettenti (_pruning_).

---

### **5. In sintesi**

- È stata introdotta la **tecnica di progetto Backtrack**, caratterizzata da una **costruzione iterativa** e da una **possibile distruzione parziale** della soluzione.
    
- Sono stati mostrati **esempi tipici**: le visite sugli alberi, la DFS sui grafi e gli algoritmi di enumerazione.
    
- La tecnica di backtrack rappresenta un **approccio sistematico alla ricerca di soluzioni**, alternativo al metodo greedy, con cui condivide la costruzione incrementale ma non la rigidità delle scelte.
    
- Nei moduli successivi verrà mostrato come questa tecnica possa essere applicata in contesti più complessi, come il **String Matching** e altri problemi combinatori fondamentali.