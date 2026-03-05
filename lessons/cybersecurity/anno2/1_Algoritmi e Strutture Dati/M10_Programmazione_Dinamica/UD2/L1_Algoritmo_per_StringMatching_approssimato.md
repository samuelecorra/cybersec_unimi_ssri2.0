
## **Lezione 1: Algoritmo per lo String Matching Approssimato**

### **1. Introduzione**

In questa lezione viene progettato un **algoritmo di Programmazione Dinamica** per risolvere il problema dello **String Matching approssimato**, ossia la ricerca di un **pattern $P$** all’interno di un **testo $T$**, ammettendo la presenza di **errori o differenze** tra le due stringhe.

Gli **errori possibili** sono di tre tipi:

1. **Sostituzione** – i caratteri corrispondenti in $P$ e $T$ sono diversi.
    
2. **Cancellazione** – un carattere di $P$ non compare in $T$.
    
3. **Inserimento** – un carattere di $T$ non compare in $P$.
    

L’obiettivo è quindi trovare una **$k$-occorrenza approssimata** di $P$ in $T$, ossia tutte le posizioni nel testo in cui $P$ compare con **al più $k$ errori**.

---

### **2. Notazione**

Si assumono le seguenti convenzioni:

- Sia $P = p_0 p_1 \dots p_m$ il pattern.
    
- Sia $T = t_0 t_1 \dots t_n$ il testo.
    
- Entrambe le stringhe iniziano con un **carattere vuoto in posizione 0**, utile per semplificare gli indici.
    

---

### **3. Definizione della tabella**

Definiamo una **tabella $D[i, j]$** con $0 \le i \le m$ e $0 \le j \le n$ tale che:

$$  
D[i, j] = \text{minimo numero di errori tra } p_0 \dots p_i \text{ e un segmento di } T \text{ che termina in } t_j  
$$

#### **Inizializzazione:**

- $D[0, j] = 0$ per ogni $j$  
    (trasformare una stringa vuota nel prefisso $t_0 \dots t_j$ non comporta errori)
    
- $D[i, 0] = i$ per ogni $i$  
    (trasformare il prefisso $p_0 \dots p_i$ in una stringa vuota richiede $i$ cancellazioni)
    

---

### **4. Regola di transizione**

Il valore di $D[i, j]$ può assumere tre diverse forme in base all’operazione considerata:

$$  
D[i, j] = \min  
\begin{cases}  
D[i-1, j-1] & \text{se } p_i = t_j \ \  
D[i-1, j-1] + 1 & \text{(sostituzione se } p_i \neq t_j) \ \  
D[i-1, j] + 1 & \text{(cancellazione in } P) \ \  
D[i, j-1] + 1 & \text{(inserimento in } T)  
\end{cases}  
$$

In sintesi:

- **$D[i-1, j-1]$** valuta l’allineamento tra i due caratteri;
    
- **$D[i-1, j] + 1$** tiene conto di un carattere mancante nel testo;
    
- **$D[i, j-1] + 1$** tiene conto di un carattere in più nel testo.
    

---

### **5. Schema della tabella**

![[Pasted image 20251018223729.png]]

Ogni cella $D[i,j]$ dipende dalle tre celle adiacenti:

```
     D[i-1, j-1]   →   +0 / +1
     D[i-1, j]     →   +1
     D[i, j-1]     →   +1
```

Si procede **riempiendo la matrice da sinistra a destra e dall’alto verso il basso**, fino a ottenere la tabella completa.  
Il valore minimo nella **riga $m$-esima** ($D[m, j]$ per $j = 0, \dots, n$) indicherà la posizione del **match approssimato migliore** nel testo.

---

### **6. Implementazione in C**

```c
int StringMatchingApprox(char *P, char *T, int n, int m) {
    /* D è una matrice [0..m][0..n] */
    int i, j, min;

    for (j = 0; j <= n; j++)
        D[0][j] = 0;
    for (i = 0; i <= m; i++)
        D[i][0] = i;

    for (i = 1; i <= m; i++)
        for (j = 1; j <= n; j++) {
            min = D[i-1][j-1];
            min = (P[i] == T[j] ? min : min + 1);
            if (D[i-1][j] + 1 < min) min = D[i-1][j] + 1;
            if (D[i][j-1] + 1 < min) min = D[i][j-1] + 1;
            D[i][j] = min;
        }

    min = D[m][0];
    i = 0;
    for (j = 1; j <= n; j++)
        if (D[m][j] < min) {
            min = D[m][j];
            i = j;
        }
    return i;
}
```

---

### **7. Analisi della complessità**

- La matrice $D$ ha dimensione $m \times n$.
    
- Ogni cella viene calcolata in **tempo costante**.
    
- L’intera tabella richiede quindi **$O(nm)$ operazioni**.
    
- L’algoritmo può essere esteso per **ricostruire il match approssimato e gli errori** senza modificare l’ordine di complessità.
    

**Complessità complessiva:**

$$  
O(nm)  
$$

---

### **8. In sintesi**

- È stato progettato un **algoritmo di Programmazione Dinamica** per il **problema dello String Matching approssimato**.
    
- La soluzione utilizza una **matrice dei costi** per confrontare progressivamente pattern e testo, considerando inserimenti, cancellazioni e sostituzioni.
    
- La **regola di programmazione dinamica** sottostante consente di evitare ricalcoli, garantendo una complessità $O(nm)$.
    
- L’approccio può essere facilmente esteso per calcolare **la posizione del miglior allineamento** e **la distanza di edit** tra due stringhe.
    

In conclusione, questo algoritmo rappresenta **una potente applicazione della programmazione dinamica** al riconoscimento approssimato di stringhe, con ampia rilevanza in ambiti reali come la bioinformatica, la linguistica computazionale e la ricerca testuale avanzata.