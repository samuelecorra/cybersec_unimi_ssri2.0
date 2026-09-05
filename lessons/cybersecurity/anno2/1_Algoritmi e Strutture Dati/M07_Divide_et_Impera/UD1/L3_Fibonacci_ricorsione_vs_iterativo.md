## **Lezione 3: Fibonacci — ricorsione vs. iterativo**

---

### **1. Introduzione e motivazioni**

L'obiettivo di questa lezione è illustrare la **differenza di complessità fra un algoritmo ricorsivo e uno iterativo**, prendendo come spunto la soluzione del problema di calcolare i numeri di Fibonacci.

Il percorso è il seguente: dopo aver richiamato la relazione ricorsiva che definisce i numeri di Fibonacci, si mostrano un algoritmo iterativo e uno ricorsivo per risolvere il problema. Calcolata la complessità di ciascuno dei due, si effettua un confronto per determinare quale sia il migliore, cercando soprattutto di **individuarne i motivi**.

Quest'ultimo punto è il vero cuore della lezione. Che l'algoritmo iterativo sia più veloce è un fatto; capire *perché* lo sia — cioè quale lavoro l'algoritmo ricorsivo compia inutilmente — è ciò che permette di riconoscere la stessa situazione in problemi diversi e di sapere quando la ricorsione va evitata o corretta.

> [!important]
> Il caso di Fibonacci è particolarmente istruttivo perché la definizione matematica del problema è **essa stessa ricorsiva**. La traduzione diretta della definizione in codice sembra quindi la scelta naturale e persino elegante, eppure produce l'algoritmo peggiore possibile. È il controesempio che smonta l'idea che "seguire la definizione" sia sempre una buona strategia di progetto.

---

### **2. Definizione**

Si denota con $F(n)$ l'**$n$-esimo numero di Fibonacci**. La relazione di ricorrenza che li definisce è la seguente:

$$
F(n) =
\begin{cases}
0, & n = 0\\[4pt]
1, & n = 1\\[4pt]
F(n-1) + F(n-2), & n \ge 2
\end{cases}
\tag{1}
$$

I primi valori della successione sono:

| $n$ | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $F(n)$ | 0 | 1 | 1 | 2 | 3 | 5 | 8 | 13 | 21 | 34 | 55 |

La definizione ha due **casi base** ($n=0$ e $n=1$) e un **caso ricorsivo** che, per calcolare un termine, richiede i **due** termini immediatamente precedenti. È proprio questa doppia dipendenza a rendere il problema interessante: se il caso ricorsivo richiedesse un solo termine precedente, ricorsione e iterazione avrebbero lo stesso costo.

---

### **3. Un algoritmo iterativo**

L'algoritmo iterativo fa uso di un **vettore di appoggio** nel quale raccogliere i numeri di Fibonacci necessari al calcolo di $F(n)$.

```c
long F_i( long n ) {
    long *F, i;

    F = (long *) malloc( (n+1) * sizeof(long) );

    F[0] = 0;  F[1] = 1;

    for( i=2; i<=n; i++ )
        F[i] = F[i-1] + F[i-2];

    return( F[n] );
}
```

#### **Come funziona**

Il vettore `F` viene allocato con $n+1$ posizioni, così da poter indicizzare da $F[0]$ a $F[n]$. Si inizializzano le prime due celle con i valori dei casi base, poi il ciclo riempie **una posizione alla volta, in ordine crescente**, leggendo le due celle immediatamente precedenti — che a quel punto sono già state calcolate. Al termine, $F[n]$ contiene il risultato.

La direzione del calcolo è essenziale: si procede **dal basso verso l'alto** (*bottom-up*), dai casi base verso il valore richiesto. Ogni numero di Fibonacci viene così calcolato **esattamente una volta** e memorizzato per gli usi successivi.

#### **Conteggio delle operazioni elementari**

Il ciclo `for( i=2; i<=n; i++ )` esegue il corpo per $i = 2, 3, \dots, n$, cioè **$n-1$ volte**. A ogni iterazione si eseguono $4$ operazioni elementari:

1. la **somma** `F[i-1] + F[i-2]`;
2. l'**assegnamento** della somma a `F[i]`;
3. l'**incremento** di `i`;
4. il **confronto** `i<=n`.

Fuori dal ciclo si eseguono $3$ operazioni elementari: l'inizializzazione delle prime due posizioni di `F[.]` e il `return`, che può essere visto come un assegnamento.

La funzione del tempo di calcolo di `F_i` è dunque

$$
T(n) = 4(n-1) + 3 = 4n - 1 .
$$

Di conseguenza:

$$
\boxed{O\bigl(T(n)\bigr) = O(n)}
$$

> [!note]
> La dispensa riporta la formula nella forma $T(n) = 3(n-2)+3$. Il valore della costante moltiplicativa e del numero esatto di iterazioni dipende da come si contano le operazioni elementari — se si includa o meno il confronto del ciclo, se il `return` valga o no un'operazione — e da come si contino le iterazioni di un `for` con estremi inclusivi. **Nessuna di queste scelte cambia il risultato**: qualunque conteggio ragionevole produce una funzione della forma $an + b$ con $a$ e $b$ costanti, e quindi $O(n)$. È esattamente il motivo per cui si adotta la notazione asintotica: rende irrilevanti le convenzioni di conteggio.

> [!warning]
> Il codice alloca il vettore con `malloc` ma non lo libera con `free` prima del `return`: in un programma reale questo è un **memory leak**. La correzione richiede di salvare il risultato in una variabile locale, liberare il vettore e restituire la variabile. Nella lezione il dettaglio è omesso perché irrilevante ai fini dell'analisi di complessità, ma va tenuto presente scrivendo codice vero.

#### **Occupazione di memoria**

L'algoritmo usa $\Theta(n)$ celle di memoria per il vettore di appoggio. Si tratta di uno spreco evitabile: per calcolare $F[i]$ servono soltanto le **due** celle precedenti, quindi bastano due variabili e la memoria scende a $\Theta(1)$.

```c
long F_i2( long n ) {
    long precedente = 0, corrente = 1, prossimo, i;

    if ( n <= 1 ) return( n );

    for( i=2; i<=n; i++ ) {
        prossimo  = precedente + corrente;
        precedente = corrente;
        corrente   = prossimo;
    }
    return( corrente );
}
```

Questa variante mantiene il tempo $\Theta(n)$ e riduce lo spazio ausiliario a $\Theta(1)$. Il vettore resta però utile quando servono **tutti** i numeri della successione fino a $n$, non solo l'ultimo.

---

### **4. Un algoritmo ricorsivo**

La procedura ricorsiva si basa **direttamente** sulla relazione descritta nell'equazione $(1)$: è la traduzione letterale della definizione matematica in codice.

```c
long F_r( long n ) {
    if ( n<=1 )
        return( (n==0) ? 0 : 1 );
    else
        return( F_r(n-1) + F_r(n-2) );
}
```

Il caso base copre $n \le 1$ e restituisce $0$ oppure $1$ tramite l'operatore condizionale; il caso ricorsivo somma i risultati delle due chiamate su $n-1$ e $n-2$.

#### **La relazione di ricorrenza**

Il tempo di calcolo è descritto da

$$
T(n) =
\begin{cases}
d, & n \le 1\\[4pt]
T(n-1) + T(n-2) + c, & n > 1
\end{cases}
\tag{2}
$$

dove $d$ è il costo costante del caso base (un confronto e un `return`) e $c$ il costo costante del lavoro locale nel caso ricorsivo (il confronto, la somma dei due risultati e il `return`), **esclusi** i tempi delle due chiamate.

#### **Applicazione del teorema**

Si applica il **teorema delle ricorrenze lineari di ordine costante**, introdotto nella [Lezione 1](L1_Complessita_algo_ricorsivi.md). La relazione $(2)$ ha la forma canonica

$$
T(n) = \sum_{i=1}^{h} a_i\,T(n-i) + c\,n^{\beta}
$$

con $h = 2$, $a_1 = 1$ (la chiamata su $n-1$), $a_2 = 1$ (la chiamata su $n-2$) e $c\,n^{\beta} = c$, quindi $\beta = 0$. La somma dei coefficienti ricorsivi vale

$$
a = a_1 + a_2 = 1 + 1 = 2 .
$$

Poiché $a = 2 \ge 2$, il teorema colloca la ricorrenza nel **caso esponenziale**:

$$
T(n) = O\!\left(a^{n} n^{\beta}\right) = O\!\left(2^{n} \cdot n^{0}\right)
$$

$$
\boxed{O\bigl(T(n)\bigr) = O(2^{n})}
$$

> [!important]
> Il discrimine del teorema è **il valore di $a$**, cioè quante chiamate ricorsive vengono effettuate a ogni livello. Con $a = 1$ la crescita è polinomiale, con $a \ge 2$ è esponenziale. Fibonacci ricorsivo cade nel secondo caso per una ragione strutturale: dalla definizione discendono **due** chiamate, non una.
>
> Si confronti con l'esempio della funzione `min_r` della Lezione 1, dove $a = 1$ e la complessità è $O(n)$: la differenza fra lineare ed esponenziale sta tutta in quel coefficiente.

#### **Occupazione di memoria**

Lo spazio è determinato dalla **profondità massima della pila delle chiamate**, non dal loro numero: le chiamate non sono simultanee, ma si aprono e chiudono. Il ramo più profondo è quello che decrementa sempre di uno ($n, n-1, n-2, \dots$), quindi la profondità è $\Theta(n)$ e tale è lo spazio occupato.

---

### **5. Confronto**

Come si può notare, **l'algoritmo iterativo risulta essere di complessità lineare mentre quello ricorsivo risulta esponenziale**. Resta da capire da dove nasca una differenza così drammatica.

#### **Il grafo di Fibonacci**

Una spiegazione di questo comportamento può essere data nel modo seguente.

Si consideri il grafo che rappresenta le **relazioni fra i vari numeri di Fibonacci**: ogni nodo è un indice, e da ciascun nodo $u$ partono due archi entranti provenienti da $u-1$ e $u-2$. Ad esempio, sommando i numeri di indice $n-1$ ed $n-2$ si ottiene l'$n$-esimo numero di Fibonacci.

![](imgs/Pasted%20image%2020260905185121.png)

Generalizzando, il **grafo di Fibonacci** è un grafo $G = (N, A)$ tale che

$$
N = \{0, 1, \dots, n\}, \qquad
A = \bigcup_{u \in N \setminus \{0,1\}} A(u), \qquad
A(u) = \bigl\{(u-2,\,u),\; (u-1,\,u)\bigr\} .
$$

In parole: i nodi sono tutti gli indici da $0$ a $n$; per ogni nodo $u$ diverso dai due casi base entrano esattamente **due archi**, uno dal nodo che lo precede di una posizione e uno dal nodo che lo precede di due. I nodi $0$ e $1$ non hanno archi entranti, perché corrispondono ai casi base e non dipendono da nulla.

#### **L'algoritmo iterativo percorre un cammino**

L'algoritmo iterativo equivale a **percorrere un cammino che parte dal nodo $2$ e arriva al nodo $n$**, visitando ogni nodo una sola volta e in ordine crescente. La lunghezza di tale cammino è evidentemente $O(n)$.

Ogni nodo viene attraversato una volta perché il valore corrispondente, una volta calcolato, viene **memorizzato** in $F[i]$ e riutilizzato quando serve. Il vettore di appoggio è esattamente ciò che rende superflua ogni ripetizione.

#### **L'algoritmo ricorsivo enumera tutti i cammini**

Viceversa, come si può osservare dal codice della funzione, le due chiamate ricorsive con valori $n-1$ e $n-2$ equivalgono a percorrere, **partendo da un nodo, entrambi i due possibili cammini che arrivano nel nodo**. Lo stesso accade alle chiamate successive.

In pratica, la procedura ricorsiva è come se **enumerasse tutti i possibili cammini del grafo**. Poiché da ogni nodo si diramano due archi, che rappresentano due possibili scelte, i cammini sono dell'ordine di $2^{n}$.

> [!important]
> Ecco il punto centrale della lezione. Il grafo è lo stesso nei due casi; ciò che cambia è **come lo si attraversa**:
> - l'iterativo lo percorre **una volta sola**, in avanti, conservando i risultati parziali;
> - il ricorsivo lo percorre **all'indietro**, esplorando ogni volta tutte le diramazioni e **dimenticando** ciò che ha già calcolato.
>
> Il costo esponenziale non nasce dalla ricorsione in quanto tale, ma dal fatto che la ricorsione ingenua **ricalcola più volte gli stessi valori**.

#### **Il ricalcolo in un esempio concreto**

Si consideri l'albero delle chiamate per $F_r(5)$:

- `F_r(5)` chiama `F_r(4)` e `F_r(3)`
- `F_r(4)` chiama `F_r(3)` e `F_r(2)`
- `F_r(3)` chiama `F_r(2)` e `F_r(1)`
- `F_r(2)` chiama `F_r(1)` e `F_r(0)`

Contando quante volte ciascun valore viene calcolato:

| Valore | $F(5)$ | $F(4)$ | $F(3)$ | $F(2)$ | $F(1)$ | $F(0)$ |
|---|---:|---:|---:|---:|---:|---:|
| numero di calcoli | 1 | 1 | **2** | **3** | **5** | **3** |

Il valore $F(1)$ viene calcolato **cinque volte** per ottenere $F(5)$; con $n$ più grande le ripetizioni esplodono. Si osservi inoltre che il numero di volte in cui viene calcolato $F(k)$ segue a sua volta la successione di Fibonacci — non è un caso, ed è la spiegazione più diretta della crescita esponenziale.

#### **Confronto numerico**

Detto $C(n)$ il numero totale di chiamate effettuate da `F_r(n)`, vale $C(n) = 2F(n+1) - 1$:

| $n$ | Iterazioni di `F_i` | Chiamate di `F_r` | Rapporto |
|---:|---:|---:|---:|
| 10 | 9 | 177 | $\approx 20$ |
| 20 | 19 | 21 891 | $\approx 1\,150$ |
| 30 | 29 | 2 692 537 | $\approx 93\,000$ |
| 40 | 39 | 331 160 281 | $\approx 8.5\cdot10^{6}$ |
| 50 | 49 | 40 730 022 147 | $\approx 8.3\cdot10^{8}$ |

Su una macchina che eseguisse cento milioni di chiamate al secondo, `F_r(50)` richiederebbe **oltre sei minuti**, mentre `F_i(50)` termina in poche decine di nanosecondi. Per $n = 100$ il calcolo ricorsivo diventa semplicemente impraticabile, mentre quello iterativo resta istantaneo.

#### **Tabella riassuntiva**

| | `F_i` (iterativo) | `F_r` (ricorsivo) |
|---|---|---|
| Relazione di ricorrenza | — (ciclo) | $T(n)=T(n-1)+T(n-2)+c$ |
| Coefficiente $a$ del teorema | — | $2$ |
| **Tempo** | $O(n)$ | $O(2^{n})$ |
| **Spazio ausiliario** | $\Theta(n)$ col vettore, $\Theta(1)$ con due variabili | $\Theta(n)$ di pila |
| Ogni $F(k)$ è calcolato | **una volta** | molte volte |
| Attraversamento del grafo | un cammino | tutti i cammini |
| Aderenza alla definizione | indiretta | letterale |

---

### **6. Approfondimenti**

#### **Il vero ordine di crescita**

Il teorema delle ricorrenze lineari fornisce il limite superiore $O(2^{n})$, che è **corretto ma non stretto**. Il numero esatto di chiamate, $C(n) = 2F(n+1)-1$, cresce come la successione di Fibonacci stessa, il cui andamento asintotico è

$$
F(n) \sim \frac{\varphi^{n}}{\sqrt 5}, \qquad \varphi = \frac{1+\sqrt 5}{2} \approx 1.618 ,
$$

dove $\varphi$ è la **sezione aurea**. Il costo reale è dunque $\Theta(\varphi^{n})$, non $\Theta(2^{n})$.

La maggiorazione con $2^{n}$ resta legittima ed è quella richiesta dal teorema, perché $\varphi < 2$ implica $\varphi^{n} = O(2^{n})$. La differenza è comunque notevole: per $n = 40$ si ha $\varphi^{40} \approx 2.3\cdot10^{8}$ contro $2^{40} \approx 1.1\cdot10^{12}$, un fattore superiore a $4\,000$.

> [!note]
> La ragione intuitiva per cui la base è $\varphi$ e non $2$: l'albero delle chiamate **non è un albero binario completo**, perché il ramo che decrementa di due si esaurisce prima di quello che decrementa di uno. Contare $2^{n}$ cammini equivale a supporre che entrambi i rami scendano fino alla stessa profondità, il che sovrastima.

#### **La correzione: memoizzazione e programmazione dinamica**

La ricorsione ingenua possiede le due proprietà che caratterizzano i problemi risolubili con la **programmazione dinamica**:

- **sottoproblemi sovrapposti**, perché gli stessi $F(k)$ vengono richiesti ripetutamente;
- **sottostruttura ottima**, perché $F(n)$ si determina dalle soluzioni dei due sottoproblemi immediatamente precedenti.

Ne discendono due tecniche:

- la **memoizzazione** (*top-down*) conserva in una tabella i risultati già calcolati e, prima di ricorrere, verifica se il valore sia già disponibile. Mantiene la struttura ricorsiva del codice e porta il tempo a $\Theta(n)$, con $\Theta(n)$ spazio;
- la **tabulazione** (*bottom-up*) calcola i valori in ordine crescente riempiendo una tabella, senza ricorsione.

```c
long F_m( long n, long *memo ) {
    if ( n <= 1 ) return( n );
    if ( memo[n] != -1 ) return( memo[n] );      /* già calcolato */
    memo[n] = F_m(n-1, memo) + F_m(n-2, memo);
    return( memo[n] );
}
```

> [!important]
> **L'algoritmo iterativo `F_i` è già una tabulazione**: il vettore di appoggio è la tabella, e il ciclo la riempie bottom-up. La versione con due variabili è la stessa tabulazione con la memoria ottimizzata, dato che servono solo le ultime due celle.
>
> La programmazione dinamica non "rende veloce la ricorsione" per magia: **evita di risolvere più volte lo stesso sottoproblema**. Con la memoizzazione i sottoproblemi distinti diventano $n+1$ e ciascuno viene risolto una volta sola — esattamente ciò che l'algoritmo iterativo faceva fin dall'inizio.

#### **Una nota sul modello di costo**

L'analisi svolta adotta il **modello RAM a costo uniforme**, in cui ogni operazione aritmetica su un intero costa $\Theta(1)$. È l'ipotesi standard del corso ed è quella che va usata negli esercizi.

Se si misurasse il costo **bit per bit**, l'analisi cambierebbe: $F(n)$ ha circa $n\log_2\varphi \approx 0.694\,n$ bit, quindi le addizioni non sono davvero a costo costante e l'algoritmo iterativo risulterebbe $\Theta(n^{2})$ nel numero di operazioni sui bit. La conclusione del confronto resta comunque immutata — polinomiale contro esponenziale.

---

### **7. Sintesi finale**

- I numeri di Fibonacci sono definiti dalla relazione di ricorrenza $(1)$, con due casi base e un caso ricorsivo che dipende dai **due** termini precedenti.
- L'**algoritmo iterativo** `F_i` usa un vettore di appoggio, riempie le celle dal basso verso l'alto e ha tempo $T(n) = O(n)$, con $\Theta(n)$ spazio riducibile a $\Theta(1)$.
- L'**algoritmo ricorsivo** `F_r` traduce letteralmente la definizione e ha ricorrenza $T(n)=T(n-1)+T(n-2)+c$; il teorema delle ricorrenze lineari di ordine costante, con $a = 2$ e $\beta = 0$, dà $O(T(n)) = O(2^{n})$.
- Il **grafo di Fibonacci** $G=(N,A)$ spiega la differenza: l'iterativo percorre **un solo cammino** dal nodo $2$ al nodo $n$, il ricorsivo **enumera tutti i cammini**, che sono dell'ordine di $2^{n}$.
- La causa profonda del costo esponenziale è il **ricalcolo ripetuto degli stessi valori**: la ricorsione ingenua dimentica ciò che ha già computato.
- **Memoizzazione** e **tabulazione** eliminano il ricalcolo e riportano il costo a $\Theta(n)$; l'algoritmo iterativo è di fatto già una tabulazione.

> [!important]
> **La lezione generale.** La ricorsione non è di per sé inefficiente — la ricerca binaria e MergeSort sono ricorsivi ed efficientissimi. Diventa inefficiente quando i sottoproblemi si **sovrappongono** e non si conservano i risultati intermedi. Il segnale d'allarme, riconoscibile a colpo d'occhio, è una ricorrenza con $a \ge 2$ in cui la dimensione **cala per sottrazione** anziché per divisione: $T(n-1)+T(n-2)$ è esponenziale, mentre $2T(n/2)$ è solo $\Theta(n\log n)$.
