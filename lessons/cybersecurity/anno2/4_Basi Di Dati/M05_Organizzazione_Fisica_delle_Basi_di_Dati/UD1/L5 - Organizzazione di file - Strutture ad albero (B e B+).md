# **M5 UD1 Lezione 5 - Organizzazione di file - Strutture ad albero (B e B+)**

### **1. Introduzione**

Le **strutture ad albero** rappresentano una delle forme più efficienti di **organizzazione fisica dei file** all’interno dei DBMS.  
A differenza delle strutture sequenziali e hash-based, esse permettono un **accesso bilanciato e dinamico** ai dati, mantenendo un ordinamento naturale basato sul valore di una chiave.  
Sono alla base degli **indici a struttura gerarchica**, come gli **alberi B** e **B+**, largamente utilizzati nei sistemi relazionali moderni.

---

### **2. Struttura generale di un albero**

Ogni **albero** è composto da:

- un **nodo radice**,
    
- un insieme di **nodi intermedi**,
    
- un insieme di **nodi foglia**.

Ogni nodo coincide fisicamente con una **pagina o blocco** gestito dal file system o dal **buffer manager** del DBMS.  
Le relazioni tra nodi sono definite da **puntatori**, e il **fan-out ($F$)** indica il **numero massimo di discendenti** che ogni nodo può avere.

---

### **3. Alberi B**

#### **3.1 Struttura dei nodi**

Ogni nodo $N$ contiene:

- $F_N \le F - 1$ **valori chiave** (ordinati);
    
- $F_N + 1$ **puntatori** ai sottoalberi.

Per convenzione:

- $K_1, K_2, \dots, K_{F_N}$ sono le chiavi nel nodo;
    
- $P_0, P_1, \dots, P_{F_N}$ sono i puntatori associati.

Le relazioni tra chiavi e puntatori sono:

$$  
\begin{cases}  
P_0 & \text{indirizza il sottoalbero con chiavi } K < K_1 \\\\  
P_{F_N} & \text{indirizza il sottoalbero con chiavi } K > K_{F_N} \\\\  
P_j & \text{indirizza il sottoalbero con chiavi } K_j < K < K_{j+1}, \quad 0 < j < F_N  
\end{cases}  
$$

Tutti i **nodi foglia** si trovano **allo stesso livello** dell’albero, garantendo un tempo di accesso uniforme.  
I loro puntatori sono impostati a _null_.

---

### **4. Alberi B+**

Gli **alberi B+** rappresentano una **variante migliorata** degli alberi B, e sono oggi la struttura più utilizzata nei DBMS relazionali.

Le differenze principali sono:

- i nodi interni contengono **solo chiavi e puntatori**, non tuple;
    
- i nodi foglia contengono **tutti i valori della chiave** e, se necessario, **puntatori ai record**;
    
- i nodi foglia sono **collegati tra loro in ordine crescente di chiave**, formando una **catena ordinata** che consente **accessi sequenziali efficienti**.

Le relazioni tra chiavi e puntatori nei nodi non foglia sono:

$$  
\begin{cases}  
P_0 & \text{indirizza il sottoalbero con chiavi } K < K_1 \\\\  
P_j & \text{indirizza il sottoalbero con chiavi } K_j \le K < K_{j+1}, \quad 0 < j < F_N \  
P_{F_N} & \text{indirizza il sottoalbero con chiavi } K \ge K_{F_N}  
\end{cases}  
$$

---

### **5. Tipologie di valori nei nodi**

Gli alberi B e B+ possono memorizzare:

1. **Chiave + tupla (indice primario)**  
    → ogni chiave è associata direttamente alla **tupla** corrispondente (file _key-sequenced_).
    
2. **Chiave + puntatore (indice secondario)**  
    → ogni chiave è associata a un **puntatore** alla tupla, memorizzata altrove secondo un’altra struttura (sequenziale o hash).

Un file può avere **un solo indice primario** ma **più indici secondari**, ciascuno su un attributo diverso.

---

### **6. Operazioni sugli alberi B+**

Le principali operazioni supportate da un albero B+ sono:

- **ricerca** di un valore di chiave,
    
- **inserimento** di una nuova chiave,
    
- **cancellazione** di una chiave,
    
- **modifica** (ottenuta come cancellazione seguita da inserimento).

---

#### **6.1 Ricerca**

Per cercare un valore $V$:

1. Si parte dalla **radice**.
    
2. In ogni **nodo non foglia**:
    
    - se $V < K_1$, si segue $P_0$;
        
    - se $V \ge K_{F_N}$, si segue $P_{F_N}$;
        
    - altrimenti, si segue il puntatore $P_j$ tale che $K_j \le V < K_{j+1}$.
    
3. Una volta raggiunta la **foglia**:
    
    - se l’indice è **primario**, si legge la tupla associata;
        
    - se è **secondario**, si segue il puntatore al record.

> Gli **alberi B+** sono particolarmente efficienti per le **ricerche su intervalli**, poiché le foglie sono collegate tra loro.

---

#### **6.2 Inserimento**

L’inserimento di un valore $V$ avviene in due fasi:

1. **Ricerca della posizione corretta** nella foglia corrispondente.
    
2. **Inserimento del valore**:
    
    - se la foglia **non è piena**, si inserisce semplicemente $V$;
        
    - se la foglia **è piena**, si esegue un’operazione di **split and promotion**.

##### **Split and promotion**

1. Si inserisce $V$ in ordine nel nodo foglia $N$.
    
2. Si divide il nodo in due:
    
    - $N$ contiene le chiavi $\le K_i$;
        
    - il nuovo nodo $N'$ contiene le chiavi $> K_i$.
    
3. La chiave $K_i$ viene **promossa al nodo padre** insieme a un puntatore a $N'$.

Se il **nodo padre** è pieno, lo **split** si propaga ricorsivamente verso l’alto,  
fino a creare una nuova radice e **incrementare l’altezza dell’albero**.

---

#### **6.3 Cancellazione**

Per cancellare un valore $V$:

1. Si cerca $V$ fino alla foglia corrispondente.
    
2. Si elimina $V$ dal nodo foglia.
    
3. Se il nodo diventa **troppo vuoto** (meno di $\lfloor F/2 \rfloor$ valori):
    
    - si tenta un **bilanciamento (redistribution)** con un nodo adiacente;
        
    - se non possibile, si esegue un **merge**, unendo i due nodi in uno solo.

##### **Merge**

1. Si uniscono $N$ e $N'$ (foglia o nodo intermedio) in un unico nodo.
    
2. Si elimina dal padre la chiave che li separava.
    
3. Se anche il padre risulta troppo vuoto, si ripete la procedura verso l’alto.

> In questo caso, l’altezza dell’albero **può diminuire**.

---

### **7. Vantaggi delle strutture ad albero**

- Forniscono **accesso associativo** basato sul valore della chiave,  
    senza dipendere dalla posizione fisica delle tuple.
    
- Sono **efficienti anche per le interrogazioni su intervallo**.
    
- Mantengono l’albero **bilanciato automaticamente**,  
    grazie alle operazioni di _split_ e _merge_.
    
- Costituiscono la **struttura di indicizzazione standard** nei DBMS relazionali moderni.

---

### **8. Sintesi finale**

**Abbiamo visto:**

- la struttura e i principi di funzionamento degli **alberi B e B+**;
    
- le **operazioni fondamentali** di ricerca, inserimento e cancellazione;
    
- i meccanismi di **split**, **promotion** e **merge** per mantenere l’albero bilanciato;
    
- le **ragioni dell’efficienza** delle strutture ad albero nei sistemi relazionali.

> In conclusione, gli **alberi B+** rappresentano la **struttura di indicizzazione più usata** nei DBMS:  
> consentono accessi veloci, ricerche su intervalli e mantenimento automatico dell’ordinamento delle chiavi.

---


![](imgs/Pasted%20image%2020251125051243.png)

