# **M7 UD2 Lezione 1 - Mergesort**

## **1. Introduzione**

**Mergesort** è un algoritmo di ordinamento progettato con la tecnica **Divide et Impera**: divide il vettore in due metà, le ordina ricorsivamente, poi **fonde** le due metà ordinate in un'unica sequenza ordinata.

- **Divide:** una sequenza di $n$ elementi viene divisa in due sottosequenze di dimensione circa $\dfrac{n}{2}$ (se $n$ è dispari, una parte ha un elemento in più).
- **Impera:** ciascuna metà viene ordinata applicando ricorsivamente lo stesso procedimento, finché una sottosequenza contiene un solo elemento — **caso base**, già ordinato per definizione.
- **Ricomposizione:** le due metà ordinate vengono **fuse** (funzione `merge`) in tempo lineare, sfruttando l'osservazione chiave: *due sequenze già ordinate si fondono in $\Theta(n)$*.

Per esempio, fondere $[7,21,33,48]$ e $[13,17,28,65]$ produce $[7,13,17,21,28,33,48,65]$.

| Fase | Operazione |
|---|---|
| **Separazione** | Partizionamento della sequenza in due metà — $\Theta(1)$, sono solo indici |
| **Ricomposizione** | Fusione di due sequenze già ordinate — $\Theta(n)$ |

---

## **2. Intervallo del vettore ed elementi coinvolti**

La funzione riceve tre parametri:

```c
int *A
int first
int last
```

e ordina la porzione $A[\text{first},\text{last}]$, di $n=\text{last}-\text{first}+1$ elementi. L'indice centrale che separa le due metà è:

$$
m=\left\lfloor\frac{\text{first}+\text{last}}{2}\right\rfloor,
$$

così che le due sottosequenze siano $A[\text{first},m]$ (che include $m$) e $A[m+1,\text{last}]$.

---

## **3. Codice della funzione `mergesort`**

```c
void mergesort(int *A, int first, int last)
{
    int m;

    if (first < last) {
        m = (first + last) / 2;

        mergesort(A, first, m);
        mergesort(A, m + 1, last);

        merge(A, first, last, m);
    }
}
```

La funzione modifica direttamente `A`, non restituisce un nuovo vettore.

#### **Analisi**

- `if (first < last)`: se $\text{first}=\text{last}$ l'intervallo ha un solo elemento ed è già ordinato, nessuna operazione necessaria; il caso $\text{first}>\text{last}$ corrisponde a un intervallo vuoto.
- `m = (first + last) / 2`: divisione intera; con `first=0, last=7` si ottiene $m=3$ (parti $A[0,3]$ e $A[4,7]$); con `last=6`, $m=\lfloor 6/2\rfloor=3$ (parti da 4 e 3 elementi). Una forma più robusta contro l'overflow è `m = first + (last - first) / 2;`.
- Le due chiamate ricorsive ordinano rispettivamente $A[\text{first},m]$ e $A[m+1,\text{last}]$; la seconda parte solo dopo che la prima è completamente terminata.
- `merge` **deve** essere chiamata dopo entrambe le ricorsioni, perché presuppone che le due metà siano già ordinate.

---

## **4. Esempio completo: separazione e ricomposizione**

Consideriamo $A=[33,21,7,48,28,13,65,17]$ (otto elementi). La separazione ricorsiva produce l'albero:

```text
[33, 21, 7, 48, 28, 13, 65, 17]
├── [33, 21, 7, 48]
│   ├── [33, 21] → [33] [21]
│   └── [7, 48]  → [7] [48]
└── [28, 13, 65, 17]
    ├── [28, 13] → [28] [13]
    └── [65, 17] → [65] [17]
```

La ricomposizione avviene quando le chiamate ricorsive terminano e risale l'albero fondendo a coppie:

**Primo livello** (elementi singoli → coppie ordinate):

$$
[33],[21]\to[21,33]
\qquad
[7],[48]\to[7,48]
\qquad
[28],[13]\to[13,28]
\qquad
[65],[17]\to[17,65]
$$

**Secondo livello** (coppie → quadruple ordinate): fondendo $[21,33]$ con $[7,48]$ si confrontano $21/7$ (esce $7$), poi $21/48$ (esce $21$), poi $33/48$ (esce $33$), infine il resto $48$: risultato $[7,21,33,48]$. Analogamente $[13,28]$ e $[17,65]$ danno $[13,17,28,65]$.

**Fusione finale** di $[7,21,33,48]$ e $[13,17,28,65]$:

| Confronto | Scelto | Parziale |
|---|---:|---|
| $7$ e $13$ | $7$ | $[7]$ |
| $21$ e $13$ | $13$ | $[7,13]$ |
| $21$ e $17$ | $17$ | $[7,13,17]$ |
| $21$ e $28$ | $21$ | $[7,13,17,21]$ |
| $33$ e $28$ | $28$ | $[7,13,17,21,28]$ |
| $33$ e $65$ | $33$ | $[7,13,17,21,28,33]$ |
| $48$ e $65$ | $48$ | $[7,13,17,21,28,33,48]$ |

Il $65$ residuo si aggiunge in fondo: $[7,13,17,21,28,33,48,65]$.

---

## **5. La funzione `merge`**

Al termine delle due chiamate ricorsive il vettore contiene due intervalli già ordinati, $A[f,m]$ e $A[m+1,l]$, che `merge` deve fondere in un'unica sequenza $A[f,l]$ usando un vettore esterno di appoggio `B` (dichiarato in un punto accessibile, es. `int B[MAX];`).

Tre indici governano la fusione:

| Indice | Significato |
|---|---|
| `i` | primo elemento non ancora esaminato della metà sinistra |
| `j` | primo elemento non ancora esaminato della metà destra |
| `k` | prossima posizione libera in `B` |

### **Codice mostrato nella lezione**

```c
void merge(int *A, int f, int l, int m)
{
    int i, j, k, h;

    i = k = f;
    j = m + 1;

    while (i <= m && j <= l) {
        if (A[i] < A[j])
            B[k] = A[i++];
        else
            B[k] = A[j++];

        k++;
    }

    j = k;

    for (h = i; h <= m; h++)
        A[j++] = A[h];

    for (j = f; j <= k - 1; j++)
        A[j] = B[j];
}
```

**Invariante di ciclo:** all'inizio di ogni iterazione del `while`, $B[f,k-1]$ contiene, in ordine crescente, i più piccoli elementi già estratti dalle due sottosequenze, `i` è il primo elemento non ancora usato a sinistra e `j` il primo a destra. A ogni passo viene scelto il minore fra `A[i]` e `A[j]` e aggiunto in coda a `B`: la porzione costruita resta quindi sempre ordinata, il che spiega la correttezza della fusione.

Il ciclo termina quando una delle due metà è esaurita ($i>m$ oppure $j>l$): gli elementi restanti dell'altra metà sono già nella loro posizione relativa corretta, quindi vengono semplicemente accodati (`for (h = i; h <= m; h++) A[j++] = A[h];`) senza ulteriori confronti. Infine il prefisso ordinato in `B[f,k-1]` viene ricopiato in `A[f,l]`.

### **Versione standard e più robusta**

La versione di slide copia in `B` solo il prefisso e gestisce a parte il resto; una forma equivalente, più facile da verificare (anche con sottovettori di lunghezza dispari), copia esplicitamente entrambi i resti:

```c
#include <stdio.h>

void merge(int *A, int *B, int f, int m, int l)
{
    int i = f, j = m + 1, k = f;

    while (i <= m && j <= l) {
        if (A[i] <= A[j])
            B[k++] = A[i++];
        else
            B[k++] = A[j++];
    }
    while (i <= m) B[k++] = A[i++];
    while (j <= l) B[k++] = A[j++];

    for (k = f; k <= l; k++)
        A[k] = B[k];
}

void mergesort(int *A, int *B, int first, int last)
{
    if (first < last) {
        int m = first + (last - first) / 2;

        mergesort(A, B, first, m);
        mergesort(A, B, m + 1, last);

        merge(A, B, first, m, last);
    }
}

int main(void)
{
    int A[] = {33, 21, 7, 48, 28, 13, 65, 17};
    int n = sizeof(A) / sizeof(A[0]);
    int B[n];

    mergesort(A, B, 0, n - 1);

    for (int i = 0; i < n; i++)
        printf("%d ", A[i]);
    printf("\n");

    return 0;
}
```

Output: `7 13 17 21 28 33 48 65`.

> [!note]
> `A[i] <= A[j]` (invece di `<`) fa sì che, a parità di chiave, venga scelto prima l'elemento della metà sinistra: questa scelta rende Mergesort **stabile** (vedi §9). Nel codice di slide compare `<`, che non garantisce la stabilità.

---

## **6. Complessità**

**Fusione — $\Theta(n)$:** ogni elemento viene letto, copiato in `B` e ricopiato in `A` un numero costante di volte; il ciclo principale compie al più $n-1$ confronti. È tempo ottimo, perché produrre una sequenza di $n$ elementi richiede comunque di scriverne $n$.

**Separazione — $\Theta(1)$:** `m = (first + last) / 2` calcola solo due indici, non copia fisicamente il vettore.

**Relazione di ricorrenza:**

$$
T(n)=2T\left(\frac{n}{2}\right)+dn,\qquad n\ge2,\qquad T(1)=d_0.
$$

Se $n$ non è potenza di $2$: $T(n)=T\!\left(\lfloor n/2\rfloor\right)+T\!\left(\lceil n/2\rceil\right)+\Theta(n)$, senza cambiare la complessità asintotica.

**Teorema delle ricorrenze con partizione bilanciata** ($T(n)=aT(n/b)+cn^\beta$): qui $a=2$, $b=2$, $\beta=1$, quindi

$$
\alpha=\frac{\log a}{\log b}=\frac{\log 2}{\log 2}=1=\beta
\quad\Longrightarrow\quad
T(n)=\Theta\!\left(n^\alpha\log n\right)=\boxed{\Theta(n\log n)}.
$$

**Albero di ricorsione:** al livello $r$ ci sono $2^r$ sottoproblemi di dimensione $n/2^r$, e ognuno costa $c\cdot n/2^r$; il costo totale del livello è quindi sempre $2^r\cdot c\,n/2^r=cn=\Theta(n)$, **indipendentemente da $r$**. La ricorsione termina quando $n/2^r=1$, cioè dopo $r=\log_2 n$ livelli. Sommando: $T(n)=\Theta(n)\cdot\Theta(\log n)=\Theta(n\log n)$.

Poiché Mergesort divide sempre a metà **indipendentemente dai valori** contenuti nel vettore, la struttura delle chiamate non cambia con l'ordine iniziale:

| Caso | Complessità |
|---|---|
| Migliore | $\Theta(n\log n)$ |
| Medio | $\Theta(n\log n)$ |
| Peggiore | $\Theta(n\log n)$ |

**Spazio:** il vettore ausiliario `B` richiede $\Theta(n)$; la profondità della ricorsione è $\Theta(\log n)$. Lo spazio complessivo è dominato da `B`: $S(n)=\Theta(n)$. Mergesort, nella sua versione classica su vettori, **non** è quindi in-place.

---

## **7. Stabilità**

Un ordinamento è **stabile** se elementi con la stessa chiave mantengono l'ordine relativo originale: da $(5,A),(3,X),(5,B)$ un ordinamento stabile produce $(3,X),(5,A),(5,B)$. Mergesort è stabile se, in caso di parità durante la fusione, si sceglie sempre prima l'elemento della metà sinistra (`A[i] <= A[j]`, non `<`).

---

## **8. Correttezza (per induzione su $n$)**

- **Base:** $n=1$, un solo elemento, già ordinato.
- **Ipotesi:** Mergesort ordina correttamente ogni vettore di meno di $n$ elementi.
- **Passo:** un vettore di $n$ elementi viene diviso in due parti, entrambe di dimensione $<n$; per ipotesi induttiva le due chiamate ricorsive le ordinano correttamente, e `merge` fonde correttamente due sequenze ordinate scegliendo ogni volta il minimo residuo. Quindi l'intero vettore risulta ordinato per ogni $n\ge1$.

---

## **9. Proprietà ed errori comuni**

| Proprietà | Valore |
|---|---|
| Tecnica | Divide et Impera |
| Chiamate ricorsive | $2$, su circa $n/2$ elementi ciascuna |
| Costo fusione | $\Theta(n)$ |
| Tempo (tutti i casi) | $\Theta(n\log n)$ |
| Memoria ausiliaria | $\Theta(n)$ |
| Profondità ricorsione | $\Theta(\log n)$ |
| Stabile | Sì, con `<=` nella fusione |
| In-place | No |

Errori tipici:

- chiamare `merge` **prima** delle due ricorsioni (deve presupporre le due metà già ordinate);
- dimenticare il caso base `if (first < last)`, con ricorsione infinita o intervalli non validi;
- far coincidere `m` con entrambe le metà (`mergesort(A, m, last)` invece di `mergesort(A, m + 1, last)`), che impedisce la riduzione del problema;
- pensare che la separazione copi fisicamente il vettore, quando in realtà si muovono solo indici;
- dimenticare di dichiarare/dimensionare il vettore ausiliario `B`;
- applicare il teorema delle ricorrenze sbagliato: $T(n)=2T(n/2)+cn$ è **partizione bilanciata**, non una ricorrenza a decremento costante come $T(n)=T(n-1)+c$.

---

## **10. Sintesi finale**

Mergesort applica la tecnica Divide et Impera: separa il vettore in due metà, le ordina ricorsivamente, poi le fonde in tempo lineare con `merge`. La ricorrenza $T(n)=2T(n/2)+\Theta(n)$, con partizione bilanciata ($a=b=2$, $\alpha=\beta=1$), dà $T(n)=\Theta(n\log n)$ in ogni caso, con $\Theta(n)$ di memoria ausiliaria. È stabile se implementato con `<=`, ma non è in-place.
