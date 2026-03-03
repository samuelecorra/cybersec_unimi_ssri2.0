## **Lezione 1: Algoritmo di Kruskal**

### **1. Introduzione**

In questa lezione studiamo il **problema dell’albero di copertura di costo minimo**, noto anche come **MST (Minimum Spanning Tree)**.  
Si tratta di uno dei problemi più classici della teoria dei grafi e rappresenta perfettamente l’applicazione della **tecnica greedy**.

#### **Definizione del problema**

Dato un grafo non orientato e connesso  
$$  
G = (N, A)  
$$  
con un **peso $p_a \ge 0$** associato a ogni arco $a = [i, j]$, si vuole trovare un **albero di copertura $T$** che:

- **contenga tutti gli $n$ nodi del grafo**,
    
- **utilizzi solo $n - 1$ archi**,
    
- e abbia **somma totale dei pesi minima**.
    

In altre parole, tra tutti i modi possibili per collegare tutti i nodi senza formare cicli, cerchiamo quello **a costo minimo**.

---

### **2. Alberi di copertura: esempio intuitivo**

Immaginiamo di avere un grafo con pesi sugli archi.  
Un **albero di copertura** collega tutti i nodi senza cicli.  
Tra i molti alberi possibili, uno sarà quello **di costo minimo**.

Nel primo esempio, la soluzione ammissibile ha **costo 79**, ma esiste una configurazione alternativa, con archi diversi, che collega tutti i nodi con **costo totale 43**.  
Quest’ultima rappresenta il **minimo albero di copertura**.

---

### **3. Algoritmi per il MST**

Esistono diversi algoritmi capaci di risolvere il problema **esattamente** (non per approssimazione):

- **Kruskal (1956)**
    
- **Prim (1957)**
    

Entrambi si basano su **strategie greedy**, ma con logiche leggermente diverse.  
In questa lezione ci concentreremo sull’**algoritmo di Kruskal**.

---

### **4. Idea dell’algoritmo di Kruskal**

Come per ogni algoritmo greedy, la progettazione richiede di specificare:

1. **un metodo di ordinamento** (criterio di scelta);
    
2. **una regola di inserimento** (modo in cui costruiamo la soluzione).
    

#### **Ordinamento**

Gli **archi del grafo** vengono ordinati **in ordine crescente di peso**.

#### **Insieme S**

Procediamo poi ad aggiungere, uno alla volta, gli archi più leggeri all’insieme $T$ (che conterrà la soluzione finale), **a condizione che non si creino cicli**.

---

### **5. Schema base dell’algoritmo**

```c
void kruskal(grafo G) {
    T = ∅;
    {ordina a ∈ A per peso crescente};
    for each a ∈ A {
        if (a non forma circuito)
            T = T ∪ {a};
    }
}
```

Il processo termina quando abbiamo inserito **$n - 1$ archi**: a quel punto $T$ è un albero di copertura minimo.

---

### **6. Correttezza dell’algoritmo**

L’algoritmo costruisce la soluzione **unendo progressivamente componenti connesse**.

Sia $F_k$ una **foresta** (cioè un insieme di alberi disgiunti) con $k$ componenti connesse.  
All’inizio $F_n$ è costituita da $n$ nodi isolati e nessun arco.  
A ogni passo, l’algoritmo aggiunge un arco $[i,j]$ che **collega due componenti diverse**, riducendo così il numero di componenti di uno.

Formalmente:  
$$  
A_{k-1} = A_k \cup { [i, j] }  
$$  
dove $i$ e $j$ appartengono a **due componenti distinte** di $F_k$.  
Per induzione, si mostra che vengono calcolate, nell’ordine, $F_n, F_{n-1}, …, F_1$, fino a ottenere l’unico albero di copertura.

---

### **7. Implementazione efficiente**

Per implementare correttamente Kruskal è necessario curare due aspetti fondamentali:

1. **L’ordinamento degli archi** (in ordine crescente di peso)
    
2. **Il test per evitare la formazione di cicli**
    

#### **Test di verifica (assenza di cicli)**

Per verificare se l’aggiunta di un arco $[i,j]$ formerebbe un ciclo, si usa la **struttura dati Mfset (Merge-Find Set)**, che rappresenta i gruppi di nodi connessi.

Con Mfset:

- l’operazione di **ricerca dell’insieme** cui appartiene un nodo ($find$) costa $O(\log n)$;
    
- l’operazione di **fusione di due insiemi** ($union$) costa anch’essa $O(\log n)$.
    

Il test viene ripetuto per tutti gli $m$ archi, quindi il costo totale del controllo è:

$$  
O(m \log n)  
$$

---

### **8. Analisi della complessità**

Dato che $m \le n^2$ e quindi $\log m \le 2 \log n$,  
anche **l’ordinamento iniziale degli archi** costa $O(m \log n)$.

Il test con Mfset ha la stessa complessità.  
Pertanto, la complessità complessiva dell’algoritmo di Kruskal è:

$$  
O(m \log n)  
$$

---

### **9. Codice completo**

```c
typedef struct _arco {
    int i, j;
    long p;
} arco;

arco G[MAX_DIM];

void kruskal(grafo G, int n, int m, insieme *T) {
    int h, k;
    mfset S;

    creainsieme(T);
    creamfset(n, S);

    { ordina archi t.c. G[0].p ≤ ... ≤ G[m-1].p };
    h = k = 0;

    while (k < n - 1 && h < m) {
        if (trova(G[h].i, S) != trova(G[h].j, S)) {
            fondi(G[h].i, G[h].j, S);
            inserisci(G[h], T);
            k++;
        }
        h++;
    }
}
```

**Spiegazione:**

- `creainsieme(T)` inizializza l’albero come insieme vuoto.
    
- `creamfset(n, S)` crea la struttura Mfset per i nodi.
    
- Gli archi vengono esaminati in ordine di peso crescente.
    
- Ogni arco viene inserito in $T$ solo se collega due componenti distinte.
    
- Quando $T$ contiene $n-1$ archi, l’albero minimo è completo.
    

---

### **10. In sintesi**

- Abbiamo studiato l’**algoritmo di Kruskal**, un tipico esempio di **strategia greedy** applicata ai grafi.
    
- L’algoritmo costruisce il **minimo albero di copertura** unendo progressivamente insiemi disgiunti di nodi.
    
- L’efficienza è garantita dall’uso della struttura **Mfset**, che consente una gestione ottimale dei sottoinsiemi connessi.
    
- La **complessità complessiva** è pari a:
    

$$  
O(m \log n)  
$$

Un risultato elegante e potente, che mostra come una strategia locale (scegliere l’arco più leggero compatibile) porti, in questo caso, a una **soluzione globalmente ottima**.