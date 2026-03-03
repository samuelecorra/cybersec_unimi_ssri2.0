## **Lezione 2: Grafi – Specifica e realizzazioni**

---

### **1. Introduzione**

In questa lezione analizziamo il **tipo di dato astratto “grafo”** dal punto di vista **formale e implementativo**.  
Dopo averne compreso la natura teorica nella lezione precedente, ora passiamo a studiarne:

- la **specifica sintattica e semantica**,
    
- e le **principali modalità di realizzazione**, come matrici e insiemi di adiacenza.
    

> Tutti gli esempi e le definizioni faranno riferimento a **grafi orientati**, salvo diversa indicazione.  
> Un grafo non orientato può sempre essere rappresentato come un grafo orientato con **archi doppi**: $(i, j)$ e $(j, i)$.

---

### **2. Specifica sintattica**

Gli operatori fondamentali del tipo di dato **grafo** sono:

|Operatore|Tipo|Descrizione|
|---|---|---|
|`creagrafo()`|→ grafo|Crea un grafo vuoto|
|`grafovuoto(G)`|grafo → booleano|Verifica se il grafo è vuoto|
|`insnodo(u, G)`|nodo × grafo → grafo|Inserisce un nodo nel grafo|
|`insarco(u, v, G)`|nodo × nodo × grafo → grafo|Inserisce un arco tra due nodi|
|`cancnodo(u, G)`|nodo × grafo → grafo|Cancella un nodo dal grafo|
|`cancarco(u, v, G)`|nodo × nodo × grafo → grafo|Cancella un arco dal grafo|
|`adiacenti(u, G)`|nodo × grafo → insieme|Restituisce l’insieme dei nodi adiacenti a `u`|

---

### **3. Specifica semantica**

$$  
\text{creagrafo()} = G  
\quad \text{post: } G = (N, A), ; N = A = \varnothing  
$$

$$  
\text{grafovuoto}(G) = b  
\quad \text{post: } b = \text{vero sse } N = A = \varnothing  
$$

$$  
\text{insnodo}(u, G) = G'  
\quad \text{pre: } u \notin N  
\quad \text{post: } G' = (N', A), ; N' = N \cup {u}  
$$

$$  
\text{insarco}(u, v, G) = G'  
\quad \text{pre: } u, v \in N \land (u, v) \notin A  
\quad \text{post: } G' = (N, A'), ; A' = A \cup {(u, v)}  
$$

$$  
\text{cancnodo}(u, G) = G'  
\quad \text{pre: } u \in N  
\quad \text{post: } G' = (N', A), ; N' = N \setminus {u}  
$$

$$  
\text{cancarco}(u, v, G) = G'  
\quad \text{pre: } (u, v) \in A  
\quad \text{post: } G' = (N, A'), ; A' = A \setminus {(u, v)}  
$$

$$  
\text{adiacenti}(u, G) = A(u)  
\quad \text{pre: } u \in N  
\quad \text{post: } A(u) = {v \in N \mid (u, v) \in A}  
$$

---

### **4. Estensioni con pesi ed etichette**

In molte applicazioni, ai nodi e/o agli archi possono essere associate **informazioni aggiuntive**, dette **etichette** o **pesi**.

Esempio di inserimento di un nodo con peso $p$:

$$  
\text{insnodo}(p, u, G) = G'  
\quad \text{pre: } u \notin N  
\quad \text{post: } G' = (N', A), ; N' = N \cup {u}, ; peso(u) = p  
$$

---

### **5. Realizzazioni di un grafo**

Le due principali realizzazioni di un grafo sono:

1. **Matrici** → rappresentano relazioni tra nodi e/o archi.
    
2. **Insiemi di adiacenza** → rappresentano direttamente i vicini di ciascun nodo.
    

---

### **6. Matrice di incidenza nodi–archi**

È una matrice rettangolare $B = [b_{ik}]$ di dimensione $n \times m$, dove:

- ogni **riga** rappresenta un nodo,
    
- ogni **colonna** rappresenta un arco.
    

Per ogni arco $(i, j)$:

- $b_{i,k} = -1$ indica che l’arco **esce** dal nodo $i$;
    
- $b_{j,k} = +1$ indica che l’arco **entra** nel nodo $j$;
    
- le altre posizioni valgono $0$.
    

#### **Esempio**

|Nodo|(1,2)|(2,4)|(3,2)|(4,2)|(4,3)|(1,3)|
|---|---|---|---|---|---|---|
|**1**|-1|0|0|0|0|-1|
|**2**|+1|-1|0|0|0|0|
|**3**|0|0|-1|0|0|+1|
|**4**|0|+1|+1|-1|-1|0|

> Le posizioni vuote o nulle rappresentano l’assenza di connessione.

#### **Osservazioni**

- Spazio di memoria: $O(nm)$
    
- Calcolo di $A(u)$: $O(nm)$ (bisogna scandire righe e colonne)
    
- Poco efficiente per grafi grandi e sparsi.
    

---

### **7. Matrice di adiacenza nodi–nodi**

È una matrice quadrata $E = [e_{ij}]$ di dimensione $n \times n$, dove:

$$

e_{ij} =
\begin{cases}
1, & \text{se } (i, j) \in A \\
0, & \text{altrimenti}
\end{cases}

$$

#### **Osservazioni**

- Se il grafo è **non orientato**, la matrice è **simmetrica**.
    
- Spazio di memoria: $O(n^2)$
    
- Calcolo di $A(u)$: basta scandire la riga $u$.
    
- Scansione di tutti gli archi: $O(n^2)$.
    

---

### **8. Liste e vettori di adiacenza**

Per migliorare efficienza e memoria, si usano **liste di adiacenza**:  
ogni nodo $u$ mantiene una lista con tutti i nodi $v$ tali che $(u, v) \in A$.

#### **Esempio**

|Nodo|Lista di adiacenza|
|---|---|
|1|2, 3|
|2|4|
|3|2|
|4|2, 3|

---

#### **Vettori di adiacenza**

Quando il numero di nodi è fisso e non si prevedono inserimenti o cancellazioni, si possono usare **vettori** invece di liste.  
Si utilizzano due vettori:

- `nodi[]` → contiene i riferimenti (o indici) di inizio delle liste di adiacenza.
    
- `archi[]` → contiene le destinazioni, una dopo l’altra, per ogni nodo.
    

---

### **9. Analisi delle realizzazioni**

| Realizzazione                    | Spazio     | Scansione $A(u)$ | Appartenenza $(u,v)$ | Note                                |
| -------------------------------- | ---------- | ---------------- | -------------------- | ----------------------------------- |
| **Matrice di incidenza**         | $O(nm)$    | $O(nm)$          | —                    | Inefficiente, utile per grafi densi |
| **Matrice di adiacenza**         | $O(n^2)$   | $O(n)$           | $O(1)$               | Accesso diretto ma memoria elevata  |
| **Liste / vettori di adiacenza** | $O(n + m)$ | $O(              | A(u)                 | $O(\|A(u)\|)$                       |

---

### **10. Costrutto di scansione**

Nei linguaggi algoritmici, la scansione dei nodi adiacenti a $u$ si effettua con un costrutto del tipo:

```c
for each v ∈ A(u) {
    /* elaborazione su nodo v */
}
```

> Se non specificato diversamente, si assume che la scansione di $A(u)$ avvenga in ordine **crescente di etichetta**.

---

### **11. Sintesi finale**

- È stata introdotta la **specifica formale** del tipo di dato grafo.
    
- Sono state esaminate **diverse realizzazioni** e i rispettivi trade-off.
    
- In sintesi:
    

| Rappresentazione                 | Vantaggi                    | Svantaggi                         |
| -------------------------------- | --------------------------- | --------------------------------- |
| **Matrici**                      | Appartenenza arco in $O(1)$ | Memoria elevata                   |
| **Liste / vettori di adiacenza** | Scansione $A(u)$ lineare    | Verifica $(u,v)$ in $O(\|A(u)\|)$ |

---

> 🧠 **In sintesi:**  
> Ogni rappresentazione del grafo è un compromesso tra **spazio** e **tempo di accesso**.  
> La scelta dipende dal contesto: grafi densi favoriscono le matrici, grafi sparsi preferiscono le liste.  
> Capire **quando usare cosa** è il primo passo per progettare algoritmi efficienti sulle reti.
