# **M7 UD2 Lezione 2 - Quicksort**

## **1. Introduzione**

**Quicksort** ordina un vettore di $n$ elementi applicando anch'esso la tecnica **Divide et Impera**, già vista in Mergesort — ma con un ruolo invertito fra le due fasi: in Mergesort la separazione è banale e la ricomposizione richiede una fusione lineare; in Quicksort è la **separazione** a fare il lavoro vero (il partizionamento), mentre la **ricomposizione è automatica**.

L'idea è scegliere un elemento del vettore, detto **pivot** (in italiano talvolta "perno"; in queste note si usa sempre il termine inglese, come preferito a lezione), e riordinare tutti gli altri elementi attorno ad esso:

- gli elementi minori del pivot vanno prima di esso;
- gli elementi maggiori o uguali vanno dopo;
- il pivot viene collocato nella posizione $j$ che occuperà anche a ordinamento completato.

Se il valore del pivot è $x$, il partizionamento produce

$$
[\text{elementi}<x]\quad x\quad[\text{elementi}\ge x],
$$

cioè $A[i]<x$ per $i<j$ e $A[i]\ge x$ per $i>j$ (nell'implementazione mostrata a lezione la disuguaglianza a sinistra è stretta, perché il codice verifica `A[i] < x` e non `A[i] <= x`).

---

## **2. Le fasi di Quicksort**

1. **Scelta del pivot:** nell'implementazione di base si sceglie sempre il primo elemento, $x=A[\text{first}]$ — semplice, ma può produrre partizioni molto sbilanciate (§8).
2. **Separazione:** tutti gli altri elementi vengono riordinati attorno al pivot, che al termine occupa la propria posizione definitiva $j$; il vettore risulta diviso in $A[\text{first},j-1]$, il pivot $A[j]$, e $A[j+1,\text{last}]$.
3. **Soluzione dei sottoproblemi:** le due parti vengono ordinate ricorsivamente, **escludendo** il pivot (già in posizione finale):
   ```c
   quicksort(A, first, j - 1);
   quicksort(A, j + 1, last);
   ```
4. **Ricomposizione automatica:** una volta che le due parti sono ordinate, l'intero vettore $[\text{sinistra ordinata}]\,[\text{pivot}]\,[\text{destra ordinata}]$ è automaticamente ordinato — non serve alcuna `merge`.

> [!important]
> In Mergesort la divisione è banale e la ricomposizione richiede una fusione lineare; in Quicksort è la separazione a richiedere il vero lavoro di riordinamento, mentre la ricomposizione è automatica.

---

## **3. Codice della funzione `quicksort`**

La funzione riceve, come Mergesort, il vettore e l'intervallo $A[\text{first},\text{last}]$ di $n=\text{last}-\text{first}+1$ elementi:

```c
void quicksort(int *A, int first, int last)
{
    int j;

    if (first < last) {
        j = pivot(A, first, last);

        quicksort(A, first, j - 1);
        quicksort(A, j + 1, last);
    }
}
```

Come in Mergesort, la funzione modifica `A` sul posto. Il caso base è identico: se $\text{first}=\text{last}$ l'intervallo ha un elemento ed è già ordinato; se $\text{first}>\text{last}$ è vuoto.

---

## **4. La procedura `pivot`**

`pivot` deve scegliere un elemento $x$ e collocarlo in una posizione $j$ tale che $A[i]\le x$ per $i<j$ e $A[i]\ge x$ per $i>j$, con $A[j]=x$. Nella versione presentata dal professore si sceglie sempre il primo elemento: `x = A[first];`.

```c
int pivot(int *A, int first, int last)
{
    int i, j, x, temp;

    x = A[first];
    j = first;

    for (i = first; i <= last; i++) {
        if (A[i] < x) {
            temp = A[++j];
            A[j] = A[i];
            A[i] = temp;
        }
    }

    A[first] = A[j];
    A[j] = x;

    return j;
}
```

| Variabile | Significato |
|---|---|
| `x` | valore del pivot |
| `i` | cursore che scandisce l'intervallo |
| `j` | confine fra elementi $<x$ e elementi $\ge x$ già esaminati |
| `temp` | appoggio per lo scambio |

**Come funziona:** `x` salva il valore del pivot prima che venga spostato dagli scambi; `j` parte da `first` (nessun elemento $<x$ trovato ancora) e avanza di una posizione ogni volta che `A[i] < x`, scambiando `A[i]` con il nuovo `A[j]` — così l'elemento minore del pivot si accoda subito dopo gli altri già raccolti a sinistra. Quando `i == first` si confronta il pivot con sé stesso (`A[first] < x` è falso): innocuo ma superfluo, si può partire da `i = first + 1`. Al termine della scansione, `j` è l'ultima posizione occupata da un elemento $<x$; lo scambio finale `A[first] = A[j]; A[j] = x;` porta il pivot nella sua posizione definitiva.

Una forma equivalente e leggermente più leggibile, che evita il confronto superfluo:

```c
int pivot(int *A, int first, int last)
{
    int x = A[first];
    int j = first;

    for (int i = first + 1; i <= last; i++) {
        if (A[i] < x) {
            j++;
            int temp = A[j];
            A[j] = A[i];
            A[i] = temp;
        }
    }

    A[first] = A[j];
    A[j] = x;

    return j;
}
```

**Invariante di ciclo** (durante la scansione): $A[\text{first}+1,j]$ contiene solo elementi $<x$; $A[j+1,i-1]$ contiene solo elementi $\ge x$; $A[i,\text{last}]$ non è ancora stato esaminato. Ogni iterazione inserisce `A[i]` nella regione corretta preservando l'invariante; alla terminazione essa garantisce $A[i]<x$ per $\text{first}\le i<j$ e $A[i]\ge x$ per $j<i\le\text{last}$, cioè che `pivot` restituisce una posizione valida.

---

## **5. Esempio: partizionamento con pivot 9**

Vettore $A=[9,12,8,18,6,13,11,3,5,10]$, pivot $x=9=A[\text{first}]$, indici da zero.

| Passo | Confronto | Esito | Vettore dopo il passo |
|---|---|---|---|
| 12 | $12<9$? no | nessuno scambio | `[9,12,8,18,6,13,11,3,5,10]` |
| 8 | $8<9$? sì | `j: 0→1`, scambia $12\leftrightarrow8$ | `[9,8,12,18,6,13,11,3,5,10]` |
| 18 | $18<9$? no | nessuno scambio | invariato |
| 6 | $6<9$? sì | `j: 1→2`, scambia $12\leftrightarrow6$ | `[9,8,6,18,12,13,11,3,5,10]` |
| 13, 11 | $\ge9$ | nessuno scambio | invariato |
| 3 | $3<9$? sì | `j: 2→3`, scambia $18\leftrightarrow3$ | `[9,8,6,3,12,13,11,18,5,10]` |
| 5 | $5<9$? sì | `j: 3→4`, scambia $12\leftrightarrow5$ | `[9,8,6,3,5,13,11,18,12,10]` |
| 10 | $10<9$? no | nessuno scambio | invariato, `j=4` |

Scambio finale del pivot: `A[first]=A[j]=5`, `A[j]=9` → $[5,8,6,3,\mathbf9,13,11,18,12,10]$. Il pivot $9$ è in posizione definitiva; a sinistra $[5,8,6,3]$ (tutti $<9$, non ancora ordinati fra loro), a destra $[13,11,18,12,10]$ (tutti $>9$).

**Ricorsione:** su $[5,8,6,3]$ con pivot $5$ si ottiene $[3,5,6,8]$ (sinistra `[3]`, destra `[6,8]`, poi pivot $6$ separa `[8]`). Su $[13,11,18,12,10]$ con pivot $13$: minori $[10,11,12]$, maggiori $[18]$ → $[10,11,12,13,18]$ (con pivot $10$ poi $11$ che sistemano il resto). L'albero ricorsivo completo, con il pivot di ogni passo tra parentesi:

```text
[9, 12, 8, 18, 6, 13, 11, 3, 5, 10]
                       (9)
                 /             \
       [5, 8, 6, 3]       [13, 11, 18, 12, 10]
             (5)                     (13)
          /       \                 /     \
        [3]      [6, 8]      [10, 11, 12] [18]
                    (6)             (10)
                      \                \
                      [8]             [11, 12]
                                          (11)
```

I pivot non compaiono nei sottoalberi perché, dopo il partizionamento, sono già nella loro posizione definitiva. Accostando le tre componenti $[3,5,6,8]+[9]+[10,11,12,13,18]$ si ottiene il vettore ordinato $\boxed{[3,5,6,8,9,10,11,12,13,18]}$ — senza alcuna funzione di fusione.

---

## **6. Correttezza**

**Di `pivot`:** segue direttamente dall'invariante di ciclo (§4): all'inizializzazione ($j=\text{first}$, $i=\text{first}+1$) le due regioni sono vuote e l'invariante è banalmente vera; a ogni iterazione l'elemento esaminato è inserito nella regione corretta (con o senza scambio) mantenendo l'invariante; alla terminazione essa implica che lo scambio finale colloca il pivot in una posizione $j$ valida.

**Di `quicksort`** (per induzione su $n$): base $n\le1$, vettore già ordinato, nessuna ricorsione. Ipotesi: Quicksort ordina correttamente ogni intervallo con meno di $n$ elementi. Passo: `pivot` produce una parte sinistra (elementi $<x$) e una destra (elementi $\ge x$), entrambe con meno di $n$ elementi; per ipotesi induttiva le due chiamate ricorsive le ordinano correttamente, e poiché ogni elemento a sinistra è minore di ogni elemento a destra, l'intero intervallo risulta ordinato.

---

## **7. Complessità**

**Costo di `pivot`:** una sola scansione di $n$ elementi, $n-1$ confronti, lavoro costante per iterazione ⟹ $T_{\text{pivot}}(n)=\Theta(n)$, $S_{\text{pivot}}(n)=\Theta(1)$.

**Ricorrenza generale:** se il pivot finisce in posizione relativa $j$ (parte sinistra $j-1$ elementi, destra $n-j$):

$$
T(n)=T(j-1)+T(n-j)+\Theta(n).
$$

### **Caso pessimo**

Si verifica quando il pivot scelto è sempre il minimo o il massimo dell'intervallo (per esempio con il primo elemento come pivot su un vettore già ordinato, crescente o decrescente): una parte è vuota, l'altra ha $n-1$ elementi, $T(n)=T(n-1)+cn$. Sviluppando per sostituzioni successive:

$$
T(n)=T(1)+c\sum_{k=2}^{n}k=T(1)+c\left(\frac{n(n+1)}{2}-1\right)
\quad\Longrightarrow\quad
\boxed{T(n)=\Theta(n^2)}.
$$

(Coerentemente, il teorema delle ricorrenze lineari di ordine costante con $a_1=1$, $\beta=1$ dà $T(n)=O(n^{\beta+1})=O(n^2)$.) L'albero di ricorsione degenera in una catena di $n$ chiamate, di profondità $\Theta(n)$.

### **Caso migliore e caso medio**

Se il pivot divide sempre il vettore in due parti di dimensione circa uguale: $T(n)=2T(n/2)+cn$, cioè partizione bilanciata con $a=b=2$, $\beta=1$, $\alpha=\log_2 2=1=\beta$, quindi $\boxed{T(n)=\Theta(n\log n)}$ — stesso argomento dell'albero di ricorsione usato per Mergesort (ogni livello costa $\Theta(n)$, ci sono $\Theta(\log n)$ livelli).

Il caso **medio** ha la stessa complessità $\Theta(n\log n)$: non serve che il pivot divida esattamente a metà, basta che le partizioni non siano sistematicamente estreme — anche proporzioni fisse come $\tfrac14 n$ / $\tfrac34 n$ mantengono una profondità logaritmica.

| Caso | Tempo | Profondità ricorsiva |
|---|---:|---:|
| Migliore | $\Theta(n\log n)$ | $\Theta(\log n)$ |
| Medio | $\Theta(n\log n)$ | $\Theta(\log n)$ |
| Pessimo | $\Theta(n^2)$ | $\Theta(n)$ |

**Spazio:** il partizionamento usa $\Theta(1)$ variabili aggiuntive; considerando anche lo stack ricorsivo, lo spazio è $\Theta(\log n)$ nel caso medio/migliore e $\Theta(n)$ nel pessimo.

**In-place:** sì (escluso lo stack) — Quicksort scambia gli elementi direttamente nel vettore, senza vettore ausiliario di dimensione $n$ come Mergesort.

**Stabilità:** generalmente **no** — gli scambi durante il partizionamento possono invertire l'ordine relativo di elementi con chiave uguale (es. $(5,A),(3,X),(5,B)$ può uscire con $(5,B)$ prima di $(5,A)$).

---

## **8. Perché Quicksort è comunque veloce nella pratica**

Nonostante il caso pessimo quadratico, Quicksort è storicamente fra gli algoritmi di ordinamento più usati perché: opera direttamente sul vettore senza ausiliario di dimensione $n$; accede alla memoria in modo prevalentemente sequenziale (buon comportamento con la cache); ha costanti nascoste contenute; e nel caso medio richiede solo $\Theta(n\log n)$.

Il rischio del caso pessimo dipende però fortemente dalla **scelta del pivot**:

- **Mediana di tre:** si considerano primo, centrale e ultimo elemento e si sceglie come pivot il valore mediano dei tre — riduce la probabilità di scegliere un estremo, senza garantire un bilanciamento perfetto. Esempio: `first=5, middle=20, last=12` → ordinando idealmente $5,12,20$, la mediana è $12$.
- **Pivot casuale:** si estrae un indice casuale in $[\text{first},\text{last}]$ (`pivot_index = first + rand() % (last - first + 1);`), si scambia con `A[first]` e si procede come di consueto. Rende impraticabile costruire ad arte un input che causi sistematicamente il caso pessimo; la complessità attesa torna $\Theta(n\log n)$ (il caso pessimo resta matematicamente possibile, solo molto improbabile).

### **Partizionamento con due cursori**

Un'alternativa più efficiente usa un cursore `i` che avanza da sinistra e uno `j` che retrocede da destra, scambiando ogni coppia di elementi trovati fuori posto:

```c
int pivot_due_cursori(int *A, int first, int last)
{
    int x = A[first];
    int i = first + 1;
    int j = last;

    while (1) {
        while (i <= last && A[i] <= x)
            i++;

        while (A[j] > x)
            j--;

        if (i >= j)
            break;

        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    A[first] = A[j];
    A[j] = x;

    return j;
}
```

Al termine: gli elementi fino a `j - 1` sono $\le$ pivot, `A[j]` è il pivot, quelli dopo `j` sono $>$ pivot.

### **Elementi uguali al pivot**

Con `if (A[i] < x)` gli elementi **uguali** al pivot finiscono tutti a destra: con un vettore come $[5,5,5,5,5]$ nessuno soddisfa $A[i]<5$, e la partizione resta totalmente sbilanciata. Una variante più avanzata, il **partizionamento a tre vie**, divide il vettore in $[\text{elementi}<\text{pivot}]\,[\text{elementi}=\text{pivot}]\,[\text{elementi}>\text{pivot}]$ ed è molto più efficace in presenza di molti duplicati.

> [!note]
> Non essendoci un'unica "esecuzione a mano" corretta, negli esercizi va sempre dichiarato **quale elemento è il pivot** e **quale convenzione di partizionamento** si adotta (es. $[\le\text{pivot}]\,[\text{pivot}]\,[>\text{pivot}]$, preservando l'ordine relativo, con i duplicati uguali al pivot collocati a sinistra). Partizioni in-place diverse (es. Hoare o Lomuto) possono produrre stati intermedi differenti pur giungendo allo stesso ordinamento finale: ciò che va verificato è che ogni passo rispetti il pivot dichiarato e produca sottoproblemi validi.

---

## **9. La funzione `qsort` del linguaggio C**

La libreria standard fornisce `qsort` in `<stdlib.h>` (non `qsort.h`, come indicato per errore nella slide):

```c
void qsort(
    void *base,
    size_t nmemb,
    size_t size,
    int (*compar)(const void *, const void *)
);
```

| Parametro | Significato |
|---|---|
| `base` | indirizzo iniziale del vettore |
| `nmemb` | numero di elementi |
| `size` | dimensione in byte di ogni elemento |
| `compar` | funzione di confronto fra due elementi |

```c
#include <stdio.h>
#include <stdlib.h>

int confronta_interi(const void *a, const void *b)
{
    int x = *(const int *)a;
    int y = *(const int *)b;
    return (x > y) - (x < y);
}

int main(void)
{
    int A[] = {9, 12, 8, 18, 6, 13, 11, 3, 5, 10};
    size_t n = sizeof(A) / sizeof(A[0]);

    qsort(A, n, sizeof(A[0]), confronta_interi);

    for (size_t i = 0; i < n; i++)
        printf("%d ", A[i]);
    printf("\n");

    return 0;
}
```

Output: `3 5 6 8 9 10 11 12 13 18`.

> [!note]
> Nonostante il nome, lo standard C non obbliga `qsort` a usare esattamente l'algoritmo Quicksort: la funzione deve solo rispettare il contratto (ordinare secondo `compar`).

---

## **10. Versione completa dell'algoritmo**

```c
#include <stdio.h>

static void scambia(int *a, int *b)
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

static int pivot(int *A, int first, int last)
{
    int x = A[first];
    int j = first;

    for (int i = first + 1; i <= last; i++) {
        if (A[i] < x) {
            j++;
            scambia(&A[j], &A[i]);
        }
    }

    scambia(&A[first], &A[j]);

    return j;
}

static void quicksort(int *A, int first, int last)
{
    if (first < last) {
        int j = pivot(A, first, last);

        quicksort(A, first, j - 1);
        quicksort(A, j + 1, last);
    }
}

int main(void)
{
    int A[] = {9, 12, 8, 18, 6, 13, 11, 3, 5, 10};
    int n = sizeof(A) / sizeof(A[0]);

    quicksort(A, 0, n - 1);

    for (int i = 0; i < n; i++)
        printf("%d ", A[i]);
    printf("\n");

    return 0;
}
```

Output: `3 5 6 8 9 10 11 12 13 18`.

---

## **11. Proprietà, errori comuni e confronto con Mergesort**

| Proprietà | Valore |
|---|---|
| Tecnica | Divide et Impera |
| Elemento centrale | Pivot |
| Lavoro principale | Partizionamento |
| Ricomposizione esplicita | Non necessaria |
| Migliore / medio | $\Theta(n\log n)$ |
| Peggiore | $\Theta(n^2)$ |
| Spazio (stack) | $\Theta(\log n)$ medio, $\Theta(n)$ pessimo |
| In-place | Sì (escluso lo stack) |
| Stabile | Generalmente no |
| Dipendenza dalla scelta del pivot | Sì |

Errori tipici: includere di nuovo il pivot in una delle ricorsioni (`quicksort(A, first, j)` invece di `j - 1`); dimenticare il caso base `if (first < last)`; credere che la partizione ordini già le due parti (garantisce solo la relazione col pivot, non l'ordine interno); scegliere sistematicamente un pivot sfavorevole (primo/ultimo elemento su input già ordinato); confondere il valore del pivot (`x`) con il suo indice finale (`j`).

| Proprietà | Quicksort | Mergesort |
|---|---|---|
| Separazione | Partizionamento attorno al pivot | Divisione in due metà |
| Ricomposizione | Automatica | Richiede `merge` |
| Migliore / medio | $\Theta(n\log n)$ | $\Theta(n\log n)$ |
| Peggiore | $\Theta(n^2)$ | $\Theta(n\log n)$ |
| Memoria ausiliaria | Bassa (in-place) | $\Theta(n)$ |
| Stabile | Generalmente no | Sì, se implementato con `<=` |
| Dipendenza dal pivot | Sì | Nessuna |

Quicksort offre in pratica prestazioni spesso migliori di Mergesort, ma solo Mergesort garantisce $\Theta(n\log n)$ anche nel caso peggiore: la differenza si paga in memoria ausiliaria.

---

## **12. Sintesi finale**

Quicksort applica Divide et Impera scegliendo un **pivot**, partizionando il vettore attorno ad esso (`A[i]<pivot` a sinistra, `A[i]\ge pivot` a destra, pivot in posizione definitiva) e ordinando ricorsivamente le due parti — senza bisogno di fusione. La ricorrenza generale $T(n)=T(j-1)+T(n-j)+\Theta(n)$ dà $\Theta(n^2)$ nel caso pessimo (pivot sempre estremo) e $\Theta(n\log n)$ nel caso migliore e medio. È in-place ma generalmente non stabile; mediana di tre, pivot casuale e partizionamento a tre vie sono le contromisure standard contro le partizioni sbilanciate.
