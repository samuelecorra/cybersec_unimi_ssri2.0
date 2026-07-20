# MergeSort e QuickSort per gli esercizi d'esame

### **1. Obiettivi e prerequisiti**

Questa lezione completa il Modulo 7 con i due algoritmi di ordinamento *divide et impera* richiesti negli appelli. Al termine si deve saper:

- descrivere le fasi di MergeSort e QuickSort;
- ricavare e risolvere le rispettive ricorrenze;
- eseguire a mano tutte le partizioni di QuickSort, dichiarando la convenzione usata;
- distinguere caso migliore, medio e peggiore;
- riconoscere stabilità, uso di memoria e dipendenza dalla scelta del pivot.

Sono prerequisiti la ricorsione e il metodo delle ricorrenze trattati in [M07/UD1/L1](../../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md) e lo schema divide et impera di [M07/UD1/L2](../../M07_Divide_et_Impera/UD1/L2_Progetto_di_algo_Divide_et_Impera.md).

### **2. MergeSort**

MergeSort ordina un vettore $A[p..r]$ applicando tre passi:

1. **divide** l'intervallo nel punto $q=\lfloor(p+r)/2\rfloor$;
2. **risolve** ricorsivamente $A[p..q]$ e $A[q+1..r]$;
3. **combina** le due metà già ordinate mediante una fusione lineare.

```text
MERGESORT(A, p, r)
    se p < r
        q <- floor((p + r) / 2)
        MERGESORT(A, p, q)
        MERGESORT(A, q + 1, r)
        MERGE(A, p, q, r)
```

`MERGE` mantiene due indici, uno per metà, e trasferisce ogni volta il minore dei due elementi correnti in un vettore ausiliario. Quando una metà termina, copia gli elementi rimasti dell'altra. La fusione visita complessivamente $n=r-p+1$ elementi e costa $\Theta(n)$.

#### **2.1. Esempio completo**

Per $A=[8,3,6,2,7,1,5,4]$ le divisioni arrivano ai sottovettori di un elemento. La ricomposizione è:

- $[8]$ e $[3] \rightarrow [3,8]$;
- $[6]$ e $[2] \rightarrow [2,6]$;
- $[3,8]$ e $[2,6] \rightarrow [2,3,6,8]$;
- $[7]$ e $[1] \rightarrow [1,7]$;
- $[5]$ e $[4] \rightarrow [4,5]$;
- $[1,7]$ e $[4,5] \rightarrow [1,4,5,7]$;
- fusione finale $[2,3,6,8]$ e $[1,4,5,7] \rightarrow [1,2,3,4,5,6,7,8]$.

#### **2.2. Ricorrenza e proprietà**

Per $n$ potenza di due:

$$
T(n)=2T\left(\frac n2\right)+cn, \qquad T(1)=\Theta(1).
$$

Nel teorema fondamentale delle ricorrenze $a=2$, $b=2$ e $f(n)=\Theta(n)=\Theta(n^{\log_b a})$. Si è nel caso bilanciato, dunque:

$$
T(n)=\Theta(n\log n).
$$

La stessa complessità vale nel caso migliore, medio e peggiore: la procedura divide comunque il vettore e fonde tutti gli elementi. La versione classica è stabile se, a parità, `MERGE` preleva prima dalla metà sinistra; richiede però $\Theta(n)$ memoria ausiliaria.

### **3. QuickSort**

QuickSort sceglie un **pivot**, partiziona gli elementi tra quelli non maggiori e quelli maggiori del pivot, quindi ordina ricorsivamente le due parti. Dopo la partizione il pivot occupa già la propria posizione definitiva.

```text
QUICKSORT(A, p, r)
    se p < r
        q <- PARTITION(A, p, r)
        QUICKSORT(A, p, q - 1)
        QUICKSORT(A, q + 1, r)
```

> 📌 Negli esercizi bisogna dichiarare sia **quale elemento è il pivot** sia la **procedura di partizione**. Partizioni in-place diverse possono produrre stati intermedi diversi pur arrivando allo stesso ordinamento.

#### **3.1. Convenzione trasparente per le esecuzioni a mano**

Nelle soluzioni di M12 si usa, salvo diversa richiesta, una partizione concettuale stabile:

$$
[\text{elementi }\le p]\;[p]\;[\text{elementi }>p],
$$

preservando l'ordine relativo degli elementi dentro i due gruppi. Se esistono duplicati uguali al pivot, si specifica quale occorrenza è il pivot; gli altri elementi uguali possono essere posti nel gruppo sinistro. Questa convenzione rende verificabile ogni passaggio, ma una comune partizione di Hoare o Lomuto può mostrare configurazioni intermedie differenti.

#### **3.2. Esempio con pivot iniziale**

Con $A=[5,8,2,7,1,4]$ e primo elemento come pivot:

1. pivot $5$: $[2,1,4,\mathbf 5,8,7]$;
2. nel sottovettore $[2,1,4]$, pivot $2$: $[1,\mathbf 2,4]$;
3. nel sottovettore $[8,7]$, pivot $8$: $[7,\mathbf 8]$.

Il risultato è $[1,2,4,5,7,8]$.

#### **3.3. Complessità**

La partizione costa $\Theta(n)$. Se il pivot divide il vettore in parti di dimensione $k$ e $n-k-1$:

$$
T(n)=T(k)+T(n-k-1)+\Theta(n).
$$

- **Caso migliore:** partizioni bilanciate, $T(n)=2T(n/2)+\Theta(n)=\Theta(n\log n)$.
- **Caso medio:** con pivot sufficientemente vari, $\Theta(n\log n)$.
- **Caso peggiore:** una parte ha sempre dimensione $n-1$ e l'altra $0$, quindi $T(n)=T(n-1)+\Theta(n)=\Theta(n^2)$.

Con pivot sempre primo o ultimo, un vettore già ordinato può generare il caso peggiore. La randomizzazione del pivot rende molto improbabile una lunga sequenza di partizioni estreme. QuickSort è normalmente in-place, con stack ricorsivo $\Theta(\log n)$ nel caso bilanciato e $\Theta(n)$ nel peggiore, ma non è stabile nella versione in-place usuale.

> ⚠️ Dire soltanto “QuickSort è $O(n\log n)$” è incompleto: bisogna distinguere media/migliore da peggiore e motivare l'effetto delle partizioni.

### **4. Confronto essenziale**

| Proprietà | MergeSort | QuickSort |
|---|---:|---:|
| Migliore | $\Theta(n\log n)$ | $\Theta(n\log n)$ |
| Medio | $\Theta(n\log n)$ | $\Theta(n\log n)$ |
| Peggiore | $\Theta(n\log n)$ | $\Theta(n^2)$ |
| Memoria tipica | $\Theta(n)$ | stack $\Theta(\log n)$ medio |
| Stabilità tipica | sì | no |
| Passo dominante | fusione | partizione |

> ✅ MergeSort garantisce $\Theta(n\log n)$ grazie a divisioni sempre bilanciate; QuickSort dipende dalla qualità dei pivot, ma è spesso molto efficiente in pratica.
