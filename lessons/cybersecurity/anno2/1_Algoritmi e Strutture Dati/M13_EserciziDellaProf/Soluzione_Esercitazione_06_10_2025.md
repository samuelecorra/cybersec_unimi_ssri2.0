# Soluzione dell'esercitazione — 6 ottobre 2025

Esercitazione di **Algoritmi e Strutture Dati** dedicata interamente all'**analisi di complessità**: costo di cicli annidati con progressioni non lineari, ricorrenze risolte con l'albero di ricorsione e applicazione del metodo esperto (Master Theorem).

> 📌 Gli appunti originali della docente sono manoscritti e usano `m` per la dimensione dell'input, che qui viene resa uniformemente con $n$. Tutti i logaritmi sono in base 2 quando la base non è indicata, come è consuetudine nell'analisi di algoritmi.

> **Riferimenti di teoria:** [M01 — Efficienza e complessità](../M01_Elementi_Fondamentali_DS_e_Algo/UD1/L2_1_Efficienza_e_complessita.md), [M01 — Notazione asintotica](../M01_Elementi_Fondamentali_DS_e_Algo/UD1/L2_2_Efficienza_e_complessita.md), [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md).

---

### **1. Esercizio 1 — Ricorsione randomizzata: caso migliore e caso peggiore**

#### **1.1. Traccia**

```
algo(A, l, h)                    /* chiamata iniziale: l = 0, h = n */
{
    for (i = l; i < h; ++i)
        istruzione;              /* costo costante */

    if (h - l > 1) then
    {
        c = Math.random();       /* genera un numero casuale in tempo costante */

        if (c > 0.5) then
            algo(A, l, l + (h-l)/2);
        else
        {
            algo(A, l, l + (h-l)/2);
            algo(A, l + (h-l)/2, h);
        }
    }
}
```

Determinare la complessità nel caso migliore e nel caso peggiore.

#### **1.2. Costo non ricorsivo**

Il ciclo `for` scandisce l'intervallo $[l,h)$ eseguendo un'istruzione a costo costante: se poniamo $n = h - l$, il suo costo è $\Theta(n)$. Il test `h - l > 1` e la generazione del numero casuale costano $\Theta(1)$ e non alterano questo ordine di grandezza. Il **lavoro locale** di ogni chiamata è dunque

$$
f(n) = \Theta(n).
$$

L'intervallo viene sempre spezzato a metà, perché $l + (h-l)/2$ è esattamente il punto medio: entrambe le chiamate ricorsive lavorano su $n/2$ elementi.

#### **1.3. Caso migliore: una sola chiamata ricorsiva**

Il caso migliore si verifica quando il numero casuale soddisfa $c > 0.5$ a **ogni** livello di ricorsione: si esegue un solo `algo` sulla metà sinistra. La ricorrenza è

$$
T(n) = 1 \cdot T\!\left(\frac{n}{2}\right) + n .
$$

Sviluppandola con l'albero di ricorsione si ottiene una catena lineare di nodi (un nodo per livello):

| Livello $i$ | Numero di nodi | Costo di ciascun nodo | Costo del livello |
|---|---:|---:|---:|
| $0$ | $1$ | $n$ | $n/2^0$ |
| $1$ | $1$ | $n/2$ | $n/2^1$ |
| $2$ | $1$ | $n/4$ | $n/2^2$ |
| $\vdots$ | $\vdots$ | $\vdots$ | $\vdots$ |
| $k$ | $1$ | $n/2^k$ | $n/2^k$ |

La ricorsione si arresta quando la dimensione arriva a $1$:

$$
\frac{n}{2^k} = 1 \iff 2^k = n \iff k = \log n .
$$

Il costo totale è la somma dei costi di livello, cioè una **serie geometrica di ragione $1/2$**:

$$
T(n) = \sum_{i=0}^{\log n} \frac{n}{2^i} = n \sum_{i=0}^{\log n} \left(\frac{1}{2}\right)^{i} < n \sum_{i=0}^{\infty} \left(\frac{1}{2}\right)^{i} = 2n .
$$

$$
\boxed{T(n) = \Theta(n)}
$$

> 💡 La costante $2$ è la firma inconfondibile della serie geometrica di ragione $1/2$: quando il lavoro si dimezza a ogni livello, la somma di tutti i livelli vale al più il doppio del costo della radice. È la stessa ragione per cui la ricerca binaria non paga il costo dei livelli inferiori.

#### **1.4. Caso peggiore: due chiamate ricorsive**

Il caso peggiore si ha quando $c \le 0.5$ a ogni livello: si eseguono **entrambe** le chiamate, ciascuna su metà dell'intervallo.

$$
T(n) = 2 \cdot T\!\left(\frac{n}{2}\right) + n .
$$

L'albero di ricorsione diventa binario completo:

| Livello $i$ | Numero di nodi | Costo di ciascun nodo | Costo del livello |
|---|---:|---:|---:|
| $0$ | $1$ | $n$ | $n$ |
| $1$ | $2$ | $n/2$ | $n$ |
| $2$ | $4$ | $n/4$ | $n$ |
| $\vdots$ | $\vdots$ | $\vdots$ | $\vdots$ |
| $k$ | $2^k$ | $n/2^k$ | $n$ |

Il numero di nodi raddoppia esattamente alla stessa velocità con cui il costo di ciascuno si dimezza: **ogni livello costa $n$**. Poiché i livelli sono $\log n$,

$$
T(n) = \sum_{i=0}^{\log n} n = n \log n .
$$

$$
\boxed{T(n) = \Theta(n \log n)}
$$

> ✅ Stessa struttura di divisione, esito diverso: con una sola chiamata ricorsiva domina la radice ($\Theta(n)$), con due chiamate tutti i livelli pesano ugualmente ($\Theta(n\log n)$). È esattamente la differenza fra la ricerca binaria e il MergeSort.

---

### **2. Esercizio 2 — Tre cicli annidati con crescita doppiamente esponenziale**

#### **2.1. Traccia**

```
Algo(n)
{
    a = 0; j = 1; k = 0;

    while (j < n)
    {
        k++;
        for (i = 1; i <= k; i++)
        {
            h = 2;
            while (h < 2^n)
            {
                a++;
                h = h * h;
            }
        }
        j = 2 * j;
    }
}
```

#### **2.2. Ciclo esterno: quante volte si ripete?**

La variabile $j$ parte da $1$ e **raddoppia** a ogni iterazione:

$$
j = 1,\, 2,\, 4,\, 8,\, 16,\, \dots = 2^0,\, 2^1,\, 2^2,\, 2^3,\, 2^4,\, \dots
$$

Dopo $x$ iterazioni si ha $j = 2^x$; la condizione $j < n$ diventa

$$
2^{x} < n \iff \log(2^{x}) < \log n \iff x < \log n .
$$

Il ciclo esterno esegue quindi $\Theta(\log n)$ iterazioni. Poiché `k++` viene eseguito una volta per iterazione, il contatore $k$ assume in successione i valori $1, 2, 3, \dots, \log n$.

#### **2.3. Ciclo interno più profondo: la variabile $h$ si eleva al quadrato**

Il `while` più interno parte da $h = 2$ e a ogni passo esegue $h = h \cdot h$, cioè **eleva al quadrato**:

$$
h = 2,\; 4,\; 16,\; 256,\; 65536,\; \dots
$$

Riscrivendo le potenze in base 2 si vede il vero andamento:

$$
h = 2^{1},\; 2^{2},\; 2^{4},\; 2^{8},\; 2^{16},\; \dots
$$

cioè, dopo $x$ esecuzioni del corpo, $h = 2^{2^{x}}$. La crescita è **doppiamente esponenziale**: non è l'esponente a raddoppiare il valore, è il valore dell'esponente stesso a raddoppiare.

La condizione di permanenza $h < 2^{n}$ si risolve applicando due volte il logaritmo:

$$
2^{2^{x}} < 2^{n} \;\iff\; 2^{x} < n \;\iff\; x < \log n .
$$

> ⚠️ Il passaggio da tenere sotto controllo è il primo: da $2^{2^x} < 2^n$ si passa a $2^x < n$ **confrontando gli esponenti**, cosa lecita perché la funzione $2^{(\cdot)}$ è strettamente crescente. Solo al secondo passaggio si applica di nuovo il logaritmo, ottenendo $x < \log n$. Un errore frequente è fermarsi al primo logaritmo e concludere $\Theta(\log\log n)$: la doppia esponenziale sta nella **base** $2^n$ del confronto, non nella variabile.

Il `while` più interno costa quindi $\Theta(\log n)$.

#### **2.4. Ciclo `for` intermedio e costo complessivo**

Alla $k$-esima iterazione del ciclo esterno, il `for` esegue $k$ iterazioni, ciascuna delle quali paga il costo $\Theta(\log n)$ del `while` interno. Sommando su tutte le iterazioni del ciclo esterno, cioè per $k = 1, 2, \dots, \log n$:

$$
T(n) = \sum_{k=1}^{\log n} k \cdot \Theta(\log n) = \Theta(\log n) \cdot \sum_{k=1}^{\log n} k .
$$

La sommatoria è la somma dei primi $\log n$ numeri naturali, cioè una serie aritmetica:

$$
\sum_{k=1}^{\log n} k = \frac{\log n \,(\log n + 1)}{2} \sim \frac{\log^{2} n}{2} .
$$

Sostituendo:

$$
T(n) = \Theta(\log n) \cdot \Theta(\log^{2} n) = \Theta(\log^{3} n).
$$

$$
\boxed{T(n) = \Theta(\log^{3} n)}
$$

> 💡 Un modo alternativo di leggere il risultato: la somma $1 + 2 + 3 + \dots + \log n$ conta il numero totale di esecuzioni del corpo del `for` lungo tutta la vita del programma; ognuna di queste costa $\log n$. Il fattore quadratico nasce dalla serie aritmetica, il terzo fattore dal ciclo più interno.

---

### **3. Esercizio 3 — Tre `for` con indice raddoppiato**

#### **3.1. Traccia**

```
for (i = n; i > 0; i--)
    for (j = 1; j < n; j = 2*j)
        for (k = 0; k < j; k++)
            operazione di costo costante;
```

#### **3.2. Analisi dall'interno verso l'esterno**

Il `for` più interno esegue **esattamente $j$ iterazioni** a costo costante, quindi costa $\Theta(j)$. Il punto delicato è che $j$ non è costante: cambia a ogni iterazione del ciclo intermedio.

Il ciclo su $j$ assume i valori

$$
j = 1,\, 2,\, 4,\, 8,\, 16,\, \dots,\, 2^{x} \quad \text{con} \quad 2^{x} < n \;\Rightarrow\; x < \log n .
$$

Il costo dei due cicli più interni presi insieme è quindi la somma dei valori assunti da $j$, che è una **serie geometrica di ragione 2**:

$$
\sum_{x=0}^{\lfloor \log n\rfloor} 2^{x} = 2^{\lfloor \log n\rfloor + 1} - 1 \;\approx\; 2n = \Theta(n).
$$

> 📌 Nella serie geometrica di ragione $2$ **domina l'ultimo addendo**: $1+2+4+\dots+2^m = 2^{m+1}-1$, cioè poco più del doppio del termine finale. È l'immagine speculare della serie di ragione $1/2$, dove invece dominava il primo addendo.

Il `for` esterno ripete tutto questo $n$ volte (l'indice $i$ scende da $n$ a $1$ con decremento unitario):

$$
T(n) = n \cdot \Theta(n) = \Theta(n^{2}).
$$

$$
\boxed{T(n) = \Theta(n^{2})}
$$

> ⚠️ Errore tipico: moltiplicare meccanicamente "$n$ (per $i$) $\times \log n$ (per $j$) $\times n$ (per $k$)" ottenendo $n^2\log n$. Il ciclo su $k$ **non** compie $n$ iterazioni: ne compie $j$, e $j$ è quasi sempre molto minore di $n$. Sostituire il valore massimo di un indice al posto della sua somma effettiva sovrastima il costo.

---

### **4. Esercizio 4 — Doppio ciclo con istruzione di costo lineare**

#### **4.1. Traccia**

```
for (i = n; i > 0; i--)
    for (j = 1; j < n; j = 2*j)
        istruzione di costo n;
```

#### **4.2. Svolgimento**

Il ciclo su $j$ esegue $\log n$ iterazioni (stessa progressione dell'esercizio precedente), ognuna delle quali paga un'istruzione di costo $n$. Il costo dei due cicli interni è quindi

$$
\sum_{x=1}^{\log n} n = \underbrace{n + n + n + \dots + n}_{\log n \text{ volte}} = n \log n .
$$

Il `for` esterno su $i$ ripete la scansione $n$ volte:

$$
T(n) = n \cdot n \log n = \Theta(n^{2} \log n).
$$

$$
\boxed{T(n) = \Theta(n^{2} \log n)}
$$

> 💡 Il confronto con l'esercizio 3 è istruttivo: lì il corpo costava $\Theta(1)$ ma il numero di iterazioni interne cresceva come $j$; qui il numero di iterazioni interne è fisso a $\log n$ ma ogni iterazione costa $n$. I due effetti si compensano diversamente e il risultato cambia da $n^2$ a $n^2\log n$.

---

### **5. Esercizio 5 — Doppio ciclo con indice raddoppiato**

#### **5.1. Traccia**

```
for (j = 1; j < n; j = 2*j)
    for (k = 0; k < j; ++k)
        istruzione di costo costante;
```

#### **5.2. Svolgimento**

Per ogni valore di $j$ il ciclo interno esegue $j$ iterazioni; sommando sui valori assunti da $j$:

| $j$ | $1$ | $2$ | $4$ | $8$ | $16$ | $\dots$ |
|---|---:|---:|---:|---:|---:|---:|
| iterazioni del ciclo interno | $1$ | $2$ | $4$ | $8$ | $16$ | $\dots$ |

$$
T(n) = \sum_{x=0}^{\lfloor\log n\rfloor} 2^{x} \approx 2^{\log n + 1} \approx 2n = \Theta(n).
$$

$$
\boxed{T(n) = \Theta(n)}
$$

> ⚠️ Se invece l'istruzione interna avesse costo $j$ anziché costo costante, ogni valore di $j$ contribuirebbe con $j^2$ e la somma diventerebbe $1 + 4 + 16 + \dots + n^2 \approx \tfrac{4}{3}n^{2} = \Theta(n^{2})$. È bene esplicitare sempre il costo del corpo prima di sommare: è la differenza fra un algoritmo lineare e uno quadratico.

---

### **6. Esercizio 6 — Tre `while` annidati con incrementi diversi**

#### **6.1. Traccia**

```
i = n;
while (i > 1)
{
    j = 1;
    while (j < n)
    {
        k = 0;
        while (k < n)
            k = k + 2;
        j = 2 * j;
    }
    i = i / 2;
}
```

#### **6.2. Analisi dei tre cicli**

**Ciclo più interno ($k$).** L'indice parte da $0$ e cresce **di due in due**:

$$
k = 0,\, 2,\, 4,\, 6,\, 8,\, 10,\, \dots,\, n .
$$

Per raggiungere $n$ servono $n/2$ passi, quindi il costo è $\Theta(n)$. Il fattore $1/2$ è una costante moltiplicativa e non cambia l'ordine di grandezza, ma va riportato nel calcolo esatto.

**Ciclo intermedio ($j$).** L'indice raddoppia: $j = 1, 2, 4, \dots$ finché $j < n$, cioè $2^{y} = n \Rightarrow y = \log n$ iterazioni.

**Ciclo esterno ($i$).** L'indice si **dimezza** partendo da $n$:

$$
i = n,\; \frac{n}{2},\; \frac{n}{4},\; \frac{n}{8},\; \dots
$$

Dopo $x$ iterazioni $i = n/2^{x}$; il ciclo termina quando

$$
\frac{n}{2^{x}} = 1 \iff x = \log n .
$$

#### **6.3. Costo complessivo**

I tre cicli sono indipendenti fra loro (nessuno dei limiti dipende dagli indici esterni), quindi i costi si moltiplicano:

$$
T(n) = \underbrace{\log n}_{\text{ciclo } i} \cdot \underbrace{\log n}_{\text{ciclo } j} \cdot \underbrace{\frac{n}{2}}_{\text{ciclo } k} = \Theta\!\left(n \log^{2} n\right).
$$

$$
\boxed{T(n) = \Theta(n \log^{2} n)}
$$

---

### **7. Esercizio 7 — Metodo esperto su $T(n) = T(9n/10) + n$**

#### **7.1. Traccia**

Risolvere la ricorrenza

$$
T(n) = T\!\left(\frac{9n}{10}\right) + \Theta(n).
$$

#### **7.2. Richiamo del metodo esperto**

Il **teorema fondamentale delle ricorrenze** (Master Theorem) si applica alle ricorrenze della forma

$$
T(n) = a\,T\!\left(\frac{n}{b}\right) + f(n), \qquad a \ge 1,\quad b > 1,
$$

confrontando $f(n)$ con la funzione **spartiacque** $n^{\log_b a}$:

| Caso | Condizione su $f(n)$ | Risultato |
|---|---|---|
| 1 | $f(n) = O\!\left(n^{\log_b a - \varepsilon}\right)$ | $T(n)=\Theta\!\left(n^{\log_b a}\right)$ |
| 2 | $f(n) = \Theta\!\left(n^{\log_b a}\right)$ | $T(n)=\Theta\!\left(n^{\log_b a}\log n\right)$ |
| 3 | $f(n) = \Omega\!\left(n^{\log_b a + \varepsilon}\right)$ **e** $a\,f(n/b) \le c\,f(n)$ con $c<1$ | $T(n)=\Theta\!\left(f(n)\right)$ |

#### **7.3. Identificazione dei parametri**

Il termine ricorsivo è $T(9n/10)$: la dimensione viene moltiplicata per $9/10$, cioè **divisa** per $10/9$. Quindi

$$
a = 1, \qquad b = \frac{10}{9}, \qquad f(n) = n .
$$

La funzione spartiacque è

$$
n^{\log_b a} = n^{\log_{10/9} 1} = n^{0} = 1 .
$$

#### **7.4. Applicazione del caso 3**

Occorre verificare che $f(n)$ sia polinomialmente **maggiore** di $n^{0}=1$:

$$
f(n) = n = n^{1} = \Omega\!\left(n^{0 + \varepsilon}\right) \quad \text{con, ad esempio, } \varepsilon = 0.1 .
$$

La condizione è soddisfatta. Resta da controllare la **condizione di regolarità** $a\,f(n/b) \le c\,f(n)$ per qualche $c < 1$ e $n$ sufficientemente grande:

$$
1 \cdot f\!\left(\frac{n}{10/9}\right) = \frac{n}{10/9} = \frac{9}{10}\,n \;\le\; c \cdot n .
$$

La disuguaglianza è verificata scegliendo

$$
c = \frac{9}{10} < 1 .
$$

Entrambe le ipotesi del caso 3 sono soddisfatte, dunque il costo è dominato dal lavoro fatto alla radice:

$$
\boxed{T(n) = \Theta(f(n)) = \Theta(n)}
$$

> 📌 Il caso 3 è l'unico che richiede **due** verifiche: non basta che $f(n)$ superi la spartiacque, deve superarla *polinomialmente* (di un fattore $n^{\varepsilon}$) e il lavoro deve **decrescere geometricamente** scendendo nell'albero. La seconda condizione è quella che garantisce che la somma dei livelli resti proporzionale al solo costo della radice.

> 💡 Intuizione: il sottoproblema è grande $9/10$ dell'originale, quindi il costo del secondo livello è $9/10$ di quello del primo, il terzo è $(9/10)^2$, e così via. La somma è una serie geometrica di ragione $9/10 < 1$, che converge a $\frac{1}{1-9/10}\,n = 10n = \Theta(n)$.

---

### **8. Sintesi dei risultati**

| # | Struttura analizzata | Tecnica risolutiva | Risultato |
|---|---|---|---|
| 1 | ricorsione randomizzata, una chiamata | albero di ricorsione, serie geometrica $1/2$ | $\Theta(n)$ |
| 1 | ricorsione randomizzata, due chiamate | albero di ricorsione, livelli di costo uniforme | $\Theta(n\log n)$ |
| 2 | `while` + `for` + `while` con $h=h^2$ | serie aritmetica $\times$ doppio logaritmo | $\Theta(\log^{3} n)$ |
| 3 | tre `for` con $j$ raddoppiato, corpo $\Theta(1)$ | serie geometrica ragione $2$ | $\Theta(n^{2})$ |
| 4 | due `for`, istruzione di costo $n$ | somma di $\log n$ termini pari a $n$ | $\Theta(n^{2}\log n)$ |
| 5 | due `for`, corpo $\Theta(1)$ | serie geometrica ragione $2$ | $\Theta(n)$ |
| 6 | tre `while` indipendenti | prodotto dei costi | $\Theta(n\log^{2} n)$ |
| 7 | $T(n)=T(9n/10)+n$ | metodo esperto, caso 3 | $\Theta(n)$ |

> ✅ Le tre situazioni ricorrenti da riconoscere a colpo d'occhio sono: **indice che raddoppia** ($\Rightarrow \log n$ iterazioni), **somma dei valori dell'indice che raddoppia** ($\Rightarrow$ serie geometrica dominata dall'ultimo termine, quindi $\Theta(n)$), **variabile elevata al quadrato** ($\Rightarrow$ crescita doppiamente esponenziale, quindi ancora $\log$ del logaritmo dell'estremo).
