# Fibonacci iterativo, ricorsivo e dinamico

### **1. Definizione**

La successione di Fibonacci è definita da

$$
F_0=0,\qquad F_1=1,\qquad F_n=F_{n-1}+F_{n-2}\quad(n\ge2).
$$

La definizione matematica suggerisce immediatamente una procedura ricorsiva, ma non garantisce che essa sia computazionalmente conveniente.

### **2. Ricorsione ingenua**

```text
FIB-RIC(n)
    se n <= 1
        restituisci n
    restituisci FIB-RIC(n-1) + FIB-RIC(n-2)
```

La ricorrenza temporale è

$$
T(n)=T(n-1)+T(n-2)+\Theta(1).
$$

L'albero delle chiamate ricalcola più volte gli stessi valori: per ottenere $F_5$, per esempio, $F_3$ viene calcolato sia nel ramo di $F_4$ sia direttamente nel ramo destro. Il numero di chiamate cresce come la successione stessa:

$$
T(n)=\Theta(\varphi^n),\qquad \varphi=\frac{1+\sqrt5}{2},
$$

e quindi è esponenziale. La profondità dello stack è $\Theta(n)$.

### **3. Versione iterativa**

```text
FIB-ITER(n)
    se n = 0
        restituisci 0
    precedente <- 0
    corrente <- 1
    per i da 2 a n
        prossimo <- precedente + corrente
        precedente <- corrente
        corrente <- prossimo
    restituisci corrente
```

Il ciclo esegue $n-1$ iterazioni, ciascuna a costo costante nel modello RAM con interi di dimensione fissata. Dunque il tempo è $\Theta(n)$ e lo spazio ausiliario $\Theta(1)$.

> ⚠️ Se si misura il costo bit per bit, i numeri $F_n$ hanno $\Theta(n)$ bit e l'addizione non è realmente costante. Negli esercizi del corso si usa normalmente il modello RAM e si dichiara $\Theta(n)$.

### **4. Programmazione dinamica**

La ricorsione ingenua possiede due proprietà tipiche:

- **sottoproblemi sovrapposti**, perché gli stessi $F_i$ vengono richiesti ripetutamente;
- **sottostruttura ottima/componibile**, perché $F_n$ si determina dalle soluzioni dei due sottoproblemi immediatamente precedenti.

La memoizzazione conserva i risultati della versione top-down; la tabulazione li calcola bottom-up. Entrambe riducono il numero di sottoproblemi distinti a $n+1$ e raggiungono $\Theta(n)$ tempo. La versione iterativa è una tabulazione con memoria ottimizzata, poiché servono soltanto gli ultimi due valori.

### **5. Quale versione scegliere**

- La ricorsione ingenua è fedele alla definizione ma ripete lavoro e ha costo esponenziale.
- La memoizzazione conserva la struttura ricorsiva e costa $\Theta(n)$ tempo e $\Theta(n)$ spazio.
- La tabulazione completa costa $\Theta(n)$ tempo e $\Theta(n)$ spazio.
- L'iterazione con due variabili costa $\Theta(n)$ tempo e $\Theta(1)$ spazio.

Per questa ragione, tra le due implementazioni tipicamente chieste all'esame, la versione iterativa è la scelta computazionalmente migliore.

> 💡 La programmazione dinamica non “rende veloce la ricorsione” per magia: evita di risolvere più volte lo stesso sottoproblema.

> ✅ Nel modello adottato dal corso, Fibonacci iterativo è lineare, mentre la ricorsione ingenua è esponenziale.
