## **Lezione 2: Progetto di algoritmi Divide et Impera**

### **1. Introduzione**

La tecnica di progettazione **Divide et Impera** rappresenta uno dei principi cardine della progettazione algoritmica.  
L’idea di fondo è quella di **suddividere un problema complesso in sottoproblemi più semplici dello stesso tipo**, risolverli separatamente e poi **ricombinare le soluzioni parziali** per ottenere la soluzione complessiva.

In termini generali, l’approccio si basa su tre fasi:

1. **Divisione** – Il problema originale $P$ di dimensione $n$ viene suddiviso in più sottoproblemi $P_1, P_2, \dots, P_h$, ciascuno di dimensione minore $n_1, n_2, \dots, n_h$.
    
2. **Risoluzione** – Ogni sottoproblema viene risolto, spesso ricorsivamente, applicando la stessa tecnica.
    
3. **Combinazione** – Le soluzioni dei sottoproblemi vengono poi unite per ottenere la soluzione del problema originale.
    

Questa strategia, apparentemente semplice, è la base concettuale di molti algoritmi fondamentali — come _MergeSort_, _QuickSort_, _Binary Search_, e _Strassen Matrix Multiplication_ — e consente di ottenere notevoli vantaggi in termini di efficienza e modularità del codice.

---

### **2. Schema generale dell’algoritmo**

Il comportamento generale di un algoritmo **Divide et Impera** può essere rappresentato con il seguente pseudocodice:

```c
void DivideEtImpera(P, n) {
    if (n <= k) {
        // caso base: risolvi P direttamente
    }
    else {
        // fase di divisione
        dividi P in P1, ..., Ph di dimensione n1, ..., nh;

        // fase di conquista
        for (i = 1; i <= h; i++)
            DivideEtImpera(Pi, ni);

        // fase di combinazione
        combina le soluzioni di P1, ..., Ph per ottenere la soluzione di P;
    }
}
```

Qui $k$ rappresenta una soglia al di sotto della quale il problema è sufficientemente piccolo da poter essere risolto in modo diretto (senza ricorsione).

---

### **3. Relazione di ricorrenza**

Ogni algoritmo basato su “Divide et Impera” può essere formalizzato mediante una **relazione di ricorrenza** che descrive il numero di operazioni necessarie per risolvere un problema di dimensione $n$.

La forma generale è:

$$
T(n) =
\begin{cases}
c, & \text{se } n \le k \\[6pt]
\displaystyle\sum_{i=1}^{h} T(n_i) + D(n) + C(n), & \text{se } n > k
\end{cases}
$$


dove:

- $c$ è una costante che rappresenta il costo della risoluzione diretta del problema base,
    
- $D(n)$ è il numero di operazioni necessarie per **dividere** il problema in sottoproblemi,
    
- $C(n)$ è il numero di operazioni necessarie per **combinare** le soluzioni parziali.
    

Da questa relazione è possibile, tramite i teoremi introdotti nella lezione precedente, **determinare la complessità asintotica** dell’algoritmo in base alla dimensione dei sottoproblemi e ai costi di divisione e combinazione.

---

### **4. Esempio: la ricerca binaria**

Consideriamo l’algoritmo classico della **ricerca binaria**, che è un perfetto esempio di applicazione del paradigma “Divide et Impera”.

```c
boolean ricbin(dizionario *D, chiave k, int i, int j) {
    int m;
    if (i > j)
        return FALSE;
    else {
        m = (i + j) / 2;
        if (k == D->chiavi[m])
            return TRUE;
        else if (k < D->chiavi[m])
            return ricbin(D, k, i, m - 1);
        else
            return ricbin(D, k, m + 1, j);
    }
}
```

In questo caso:

- l’input (un vettore ordinato) viene **diviso in due parti**,
    
- la ricerca prosegue **solo su una metà** a ogni passo,
    
- e la combinazione dei risultati non ha praticamente costo.
    

Possiamo dunque scrivere la relazione di ricorrenza:

$$  
T(n) = T!\left(\frac{n}{2}\right) + c  
$$

con:

- $h = 2$,
    
- $n_2 \le n_1 \le n_2 + 1$,
    
- $C(n)$ e $D(n)$ costanti.
    

Applicando il teorema visto nella lezione precedente, si ottiene:

$$  
T(n) = O(\log n)  
$$

La **ricerca binaria** rientra quindi pienamente nella classe degli algoritmi _Divide et Impera_, mostrando come il bilanciamento dei sottoproblemi determini direttamente l’efficienza.

---

### **5. Efficienza e bilanciamento**

L’efficienza di un algoritmo “Divide et Impera” dipende in modo critico da **come vengono suddivisi i sottoproblemi**.

Osservando la relazione di ricorrenza, si nota che:

- se i sottoproblemi sono **bilanciati** (hanno dimensione simile), la profondità della ricorsione è logaritmica e la complessità può risultare ottima (ad esempio $O(n \log n)$ o anche $O(\log n)$);
    
- se i sottoproblemi sono **sbilanciati**, la ricorsione può degenerare fino a complessità lineari o quadratiche.
    

Il **bilanciamento della partizione** è quindi la chiave per ottenere algoritmi efficienti: ogni volta che si progetta un algoritmo _Divide et Impera_, occorre valutare attentamente il rapporto tra il numero dei sottoproblemi e la loro dimensione.

---

### **6. Sintesi finale**

In questa lezione abbiamo:

- introdotto la **tecnica di progettazione Divide et Impera**;
    
- definito il suo **schema generale** e la **relazione di ricorrenza** associata;
    
- discusso l’importanza del **bilanciamento dei sottoproblemi** per l’efficienza complessiva;
    
- visto un **esempio pratico**, la ricerca binaria, che mostra l’efficacia del metodo.
    

Nelle prossime lezioni applicheremo questa tecnica alla progettazione di **due algoritmi fondamentali di ordinamento**: _MergeSort_ e _QuickSort_, due classici esempi di “Divide et Impera” applicati in modo differente ma basati sullo stesso schema teorico.