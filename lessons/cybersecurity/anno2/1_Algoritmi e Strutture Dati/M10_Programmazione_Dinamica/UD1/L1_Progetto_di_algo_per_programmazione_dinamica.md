La **Programmazione Dinamica (PD)** è una delle tecniche di progettazione algoritmica più potenti e sistematiche, utilizzata per risolvere problemi **ottimizzativi** che possono essere **scomposti in sottoproblemi parzialmente sovrapposti**.  
È una strategia che unisce **analisi matematica e ricorsione controllata**, permettendo di evitare ricalcoli inutili e di ottenere soluzioni ottimali in modo efficiente.

L’idea di base è semplice ma profonda:

> quando un problema può essere diviso in sottoproblemi più piccoli e indipendenti, la soluzione complessiva può essere costruita **riutilizzando** le soluzioni già calcolate.

Questa tecnica si distingue quindi per due caratteristiche fondamentali:

1. **Sottostruttura ottima** – la soluzione del problema può essere ottenuta combinando soluzioni ottimali dei sottoproblemi.
    
2. **Sovrapposizione dei sottoproblemi** – alcuni sottoproblemi si ripetono più volte durante la computazione e quindi **conviene memorizzarne i risultati** (principio del _memoization_ o _tabulation_).
    

La programmazione dinamica può essere vista come una **versione ottimizzata della ricorsione**, in cui:

- la **ricorsione top-down** viene migliorata tramite _memoization_;
    
- oppure si utilizza un approccio **bottom-up**, costruendo progressivamente la soluzione tramite una **tabella**.
    

Il modulo ha come obiettivi principali:

- comprendere **lo schema generale** di progettazione di un algoritmo dinamico;
    
- saper **identificare i componenti essenziali** (sottoproblemi, relazioni di ricorrenza, tabella di memorizzazione);
    
- riconoscere i **campi di applicazione tipici** (come zaino, cammini minimi, sequenze, editing, ecc.);
    
- applicare la tecnica **passo per passo** a un problema concreto;
    
- e infine **valutarne la complessità computazionale**, confrontandola con quella delle soluzioni ricorsive pure o greedy.
    

La **programmazione dinamica** rappresenta così l’evoluzione naturale del pensiero algoritmico: un ponte tra la **ricorsione esplicita del backtrack** e la **selettività del greedy**, orientata a **riutilizzare l’intelligenza dei calcoli già svolti** per raggiungere l’efficienza ottimale.

---

## **Lezione 1: Progetto di algoritmi di Programmazione Dinamica**

### **1. Introduzione**

In questa lezione viene introdotta la **tecnica di Programmazione Dinamica (PD)** come strumento generale per la progettazione di algoritmi efficienti.  
La programmazione dinamica può essere vista come **un’estensione della tecnica Divide et Impera**, ma con un obiettivo specifico: **evitare di risolvere più volte gli stessi sottoproblemi**.

L’idea centrale è quella di **sfruttare le soluzioni dei sottoproblemi più piccoli** per costruire **soluzioni a problemi di dimensione maggiore**, memorizzando i risultati intermedi in modo da poterli riutilizzare.  
In questo modo si riduce notevolmente il numero di operazioni necessarie.

---

### **2. Differenze con Divide et Impera**

Un algoritmo di **Programmazione Dinamica** presenta tre caratteristiche fondamentali:

1. È **iterativo**, non puramente ricorsivo.
    
2. Risolve i sottoproblemi **in ordine di dimensione crescente**, così da poter utilizzare i risultati già noti.
    
3. **Memorizza i risultati intermedi** in una **tabella** (o matrice), per evitare ricalcoli.
    

#### **Confronto con Divide et Impera**

|Divide et Impera|Programmazione Dinamica|
|---|---|
|I sottoproblemi sono **indipendenti**|I sottoproblemi sono **dipendenti**|
|Ogni sottoproblema viene **ricalcolato** ogni volta che serve|Ogni sottoproblema viene **risolto una sola volta** e il risultato **riutilizzato**|
|Esempio tipico: **quicksort**|Esempio tipico: **numeri di Fibonacci**, **cammini minimi**, **zaino**|

Nel caso dei numeri di Fibonacci, ad esempio, Divide et Impera ricalcola più volte gli stessi valori, mentre la Programmazione Dinamica li memorizza, ottenendo un notevole miglioramento di efficienza.

---

### **3. Esempio: il coefficiente binomiale**

Il **coefficiente binomiale** rappresenta il numero di modi di scegliere $k$ oggetti da un insieme di $n$ oggetti, con $0 \le k \le n$.  
È definito ricorsivamente come:

$$C(n, k) =  
\begin{cases}  
1, & \text{se } k = 0 \text{ oppure } k = n \\  
C(n - 1, k - 1) + C(n - 1, k), & \text{altrimenti}  
\end{cases}
$$

---

#### **Algoritmo Divide et Impera**

```c
int C(int n, int k) {
    int c;
    if (n == k || k == 0)
        return 1;
    else {
        c = C(n-1, k-1) + C(n-1, k);
        return c;
    }
}
```

Questo algoritmo segue **direttamente la definizione ricorsiva**, ma ogni chiamata effettua **due nuove chiamate** ricorsive, generando un **numero esponenziale di operazioni**.

---

### **4. Complessità dell’approccio ricorsivo**

Il numero di chiamate ricorsive è pari al numero di nodi dell’albero delle chiamate, che coincide con il valore stesso del coefficiente binomiale.  
La complessità risulta quindi **esponenziale**, approssimativamente $O(2^n)$, e in casi più generali può arrivare fino a $O(n!)$.

Per migliorare l’efficienza, si applica la **Programmazione Dinamica**, memorizzando i risultati intermedi secondo lo **schema di Tartaglia** (Triangolo di Pascal).

---

### **5. Algoritmo di Programmazione Dinamica**

Il principio del **Triangolo di Tartaglia** permette di calcolare tutti i coefficienti binomiali $C(i, j)$ per $0 \le j \le i \le n$ in modo incrementale, riutilizzando i risultati già calcolati.

#### **Codice C – Schema di Tartaglia**

```c
void Tartaglia(int n, int **C) {
    /* C è una matrice [0..n][0..n] */
    int i, j;
    C[0][0] = 1;
    for (i = 1; i <= n; i++)
        C[i][0] = C[i][i] = 1;
    for (i = 2; i <= n; i++)
        for (j = 1; j <= i - 1; j++)
            C[i][j] = C[i-1][j-1] + C[i-1][j];
}
```

---

### **6. Analisi della complessità**

Il numero di operazioni è proporzionale al numero di elementi riempiti nella matrice $C$, cioè circa:

$$  
\frac{n(n+1)}{2}  
$$

La **complessità temporale** risulta quindi **quadratica**, ossia:

$$  
O(n^2)  
$$

mentre la **complessità spaziale** è anch’essa $O(n^2)$ per la tabella dei risultati.

---

### **7. Condizioni di applicabilità della Programmazione Dinamica**

La **tecnica della Programmazione Dinamica** è stata formalizzata da **Richard Bellman nel 1957**, in origine per la soluzione di **problemi di ottimizzazione**.

Per applicarla in modo corretto, devono essere soddisfatte alcune condizioni:

1. La soluzione del problema deve poter essere **ricombinata** a partire dalle soluzioni dei sottoproblemi più piccoli.
    
2. Le soluzioni dei sottoproblemi devono essere **invarianti** (cioè sempre uguali se il sottoproblema si ripete).
    
3. I sottoproblemi devono essere **dipendenti** tra loro (sovrapposti).
    
4. Il numero di sottoproblemi distinti da risolvere deve essere **polinomiale**.
    
5. Il tempo necessario per combinare le soluzioni deve anch’esso essere **polinomiale**.
    

Quando queste condizioni sono rispettate, la Programmazione Dinamica garantisce una **complessità al più polinomiale**, rendendo risolvibili problemi che, in forma ricorsiva pura, sarebbero **esponenziali**.

---

### **8. In sintesi**

- La **Programmazione Dinamica** è una **generalizzazione iterativa** della tecnica Divide et Impera.
    
- È **conveniente quando i sottoproblemi non sono indipendenti** e si ripetono più volte.
    
- L’esempio del **coefficiente binomiale** mostra come memorizzare i risultati in una tabella riduca la complessità da esponenziale a polinomiale.
    
- Le condizioni di Bellman definiscono il campo di applicazione della tecnica.
    

In sostanza, la Programmazione Dinamica rappresenta **un modo intelligente di risolvere problemi ricorsivi**, sostituendo la ridondanza dei calcoli con la **memoria delle soluzioni già trovate**.