# Soluzione dell'esercitazione — 15 dicembre 2025

Esercitazione di **Algoritmi e Strutture Dati** su analisi di pseudocodice tratto da appelli d'esame, i **limiti di applicabilità del metodo esperto**, **B-alberi**, **Dijkstra** e un esercizio applicativo di **albero ricoprente minimo**.

> **Riferimenti di teoria:** [M07/UD1 — Complessità di algoritmi ricorsivi](../M07_Divide_et_Impera/UD1/L1_Complessita_algo_ricorsivi.md), [M05/UD3 — Alberi bilanciati di ricerca](../M05_DS_Orizzontali/UD3/L1_Alberi_bilanciati_di_ricerca.md), [M05/UD2 — Dizionari con vettori ordinati](../M05_DS_Orizzontali/UD2/L1_Dizionari_con_vettori_ordinati.md), [M06/UD1 — Dijkstra](../M06_Impatto_delle_DS_su_complessita_algo/UD1/L2_Algo_Dijkstra_Johnson.md), [M08/UD2 — Algoritmo di Kruskal](../M08_Greedy/UD2/L1_Algoritmo_di_Kruskal.md), [M12 — Algoritmo di Prim e proprietà del taglio](../M12_Esami_passati/Approfondimenti_per_Esame/L2%20-%20Algoritmo%20di%20Prim%20e%20propriet%C3%A0%20del%20taglio.md).

---

### **1. Appello 25/07/2023, domanda 8 — Pseudocodice con ricerca binaria**

#### **1.1. Traccia**

```
Algoritmo(A)
{
    X = ∅;
    "ordina A in ordine decrescente";
    for i = 1 to A.length - 1
    {
        for j = i+1 to A.length
        {
            l = Binary-Search(A[j+1 ... A.length], A[i] + A[j]);
            if l > 0
                then X = X ∪ {A[l]};
        }
    }
    return X;
}
```

#### **1.2. Analisi delle componenti**

| Riga | Costo | Motivazione |
|---|---|---|
| `X = ∅` | $\Theta(1)$ | inizializzazione |
| ordinamento decrescente | $O(n\log n)$ | algoritmo ottimo basato su confronti |
| `for i` | $n-1$ iterazioni | — |
| `for j` | $n-i$ iterazioni | dipende da $i$ |
| `Binary-Search` | $O(\log n)$ | ricerca binaria su un sottovettore ordinato |
| `X = X ∪ {A[l]}` | $\Theta(1)$ | inserimento in un insieme realizzato con hash |

#### **1.3. Costo dei due cicli annidati**

Il ciclo interno non compie $n$ iterazioni ma $n-i$, quindi va **sommato**, non moltiplicato:

$$
\sum_{i=1}^{n-1}(n-i) = (n-1) + (n-2) + \dots + 2 + 1 = \frac{n(n-1)}{2} = \Theta(n^{2}).
$$

Ogni iterazione paga una ricerca binaria, $O(\log n)$:

$$
\Theta(n^{2}) \cdot O(\log n) = O(n^{2}\log n).
$$

#### **1.4. Costo complessivo**

L'ordinamento e il doppio ciclo sono **in sequenza**, quindi i costi si sommano:

$$
T(n) = O(n\log n) + O(n^{2}\log n) = O(n^{2}\log n),
$$

perché $n\log n = O(n^{2}\log n)$.

$$
\boxed{T(n) = \Theta(n^{2}\log n)}
$$

> 💡 L'ordinamento preliminare, che a prima vista sembra il passo costoso, è in realtà **trascurabile**: viene eseguito una sola volta e paga $n\log n$, contro gli $n^{2}\log n$ del doppio ciclo. Serve però a rendere lecita la ricerca binaria, che è ciò che tiene il fattore logaritmico anziché lineare.

> ⚠️ La ricerca binaria opera su $A[j+1 \dots n]$, un sottovettore di dimensione $n-j \le n$: il suo costo è $O(\log(n-j)) = O(\log n)$. Maggiorare con $\log n$ è corretto per un limite superiore; per un $\Theta$ stretto occorrerebbe sommare $\log(n-j)$, ma il risultato asintotico non cambia.

---

### **2. Appello 13/01/2020 — Pseudocodice con procedura di costo logaritmico**

#### **2.1. Traccia**

```
P(n)
{
    sum = 0;
    for i = 1 to log n
    {
        j = 2;
        while (j < 2^n)
        {
            sum = sum + F(i);
            j = j * 2;
        }
    }
}
```

sapendo che la procedura $F$ ha complessità $T(F(n)) = \Theta(\log n)$.

#### **2.2. Analisi**

**Ciclo esterno.** $i$ va da $1$ a $\log n$: $\Theta(\log n)$ iterazioni.

**Ciclo interno.** $j$ parte da $2$ e raddoppia; la condizione $j < 2^{n}$ diventa $2^{x} < 2^{n}$, cioè $x < n$: **$\Theta(n)$ iterazioni**.

**Corpo.** La chiamata è `F(i)`, non `F(n)`: il suo costo è $\Theta(\log i)$, e dipende dall'indice del ciclo esterno.

> ⚠️ Il punto cruciale è distinguere `F(i)` da `F(n)`. Se il corpo chiamasse `F(n)` il costo sarebbe $\Theta(\log n)$ costante rispetto al ciclo, e il totale $\Theta(\log n \cdot n \cdot \log n) = \Theta(n\log^{2}n)$. Con `F(i)` il costo varia e serve una sommatoria.

#### **2.3. Somma sui valori di $i$**

$$
T(n) = \sum_{i=1}^{\log n} \bigl(n \cdot \Theta(\log i)\bigr) = n \sum_{i=1}^{\log n} \log i .
$$

La sommatoria dei logaritmi è il logaritmo del fattoriale:

$$
\sum_{i=1}^{m} \log i = \log(m!) .
$$

Con $m = \log n$ e per l'approssimazione di Stirling ($\log(m!) = \Theta(m\log m)$):

$$
\sum_{i=1}^{\log n} \log i = \Theta\bigl(\log n \cdot \log\log n\bigr).
$$

Sostituendo:

$$
\boxed{T(n) = \Theta\!\left(n \cdot \log n \cdot \log\log n\right)}
$$

> 💡 Il termine $\log\log n$ cresce **estremamente** lentamente: per $n = 10^{9}$ vale circa $4.9$. Comparire in una complessità significa in pratica un fattore costante piccolo, ma dal punto di vista asintotico distingue $n\log n\log\log n$ da $n\log n$, e va riportato.

---

### **3. I limiti del metodo esperto**

Questa sezione raccoglie tre ricorrenze in cui il Master Theorem **non si applica**: è uno degli argomenti più insidiosi del corso, perché la tentazione di forzarne l'uso è forte.

#### **3.1. $T(n) = 2T(n/2) + \dfrac{n}{\log n}$ — il "buco" fra caso 1 e caso 2**

$$
a = 2, \qquad b = 2, \qquad n^{\log_2 2} = n, \qquad f(n) = \frac{n}{\log n}.
$$

La funzione $f(n)$ è **minore** di $n$, quindi non siamo nel caso 2. Si prova il caso 1, che richiede

$$
\frac{n}{\log n} = O\!\left(n^{1-\varepsilon}\right) \quad \text{per qualche } \varepsilon > 0 .
$$

Sviluppando la definizione di $O$:

$$
\frac{n}{\log n} \le c\, n^{1-\varepsilon}
\quad\Longleftrightarrow\quad
\frac{n}{\log n \cdot n^{1-\varepsilon}} \le c
\quad\Longleftrightarrow\quad
\frac{n^{\varepsilon}}{\log n} \le c .
$$

Ma il rapporto $\dfrac{n^{\varepsilon}}{\log n}$ **tende a infinito** per ogni $\varepsilon > 0$, perché qualunque potenza positiva di $n$ batte definitivamente il logaritmo. Non esiste quindi alcuna costante $c$ che limiti il rapporto:

$$
\frac{n}{\log n} \ne O\!\left(n^{1-\varepsilon}\right) \quad \text{per ogni } \varepsilon > 0 .
$$

$$
\boxed{\text{Il metodo esperto NON è applicabile}}
$$

> 📌 La ricorrenza cade nel **buco** fra il caso 1 e il caso 2: $f(n)$ è inferiore alla spartiacque, ma solo di un fattore **logaritmico**, non polinomiale. Il teorema richiede una separazione polinomiale e qui non c'è.
>
> Il valore corretto si trova con l'albero di ricorsione. Al livello $i$ ci sono $2^{i}$ nodi di dimensione $n/2^{i}$, ciascuno di costo $\dfrac{n/2^{i}}{\log(n/2^{i})}$, quindi il livello costa $\dfrac{n}{\log n - i}$. Sommando:
> $$T(n) = \sum_{i=0}^{\log n - 1} \frac{n}{\log n - i} = n\sum_{k=1}^{\log n}\frac{1}{k} = n\,H_{\log n} = \Theta(n\log\log n),$$
> usando il fatto che la serie armonica $H_m$ vale $\Theta(\log m)$.

#### **3.2. $T(n) = 0.9\,T(n/2) + \dfrac{n}{\log n}$ — coefficiente minore di uno**

Il metodo esperto richiede $a \ge 1$: il numero di sottoproblemi deve essere almeno uno. Qui

$$
a = 0.9 < 1 ,
$$

il che non ha nemmeno senso algoritmico — non si può generare "nove decimi di chiamata ricorsiva".

$$
\boxed{\text{Il metodo esperto NON è applicabile}}
$$

> ⚠️ Sono **due** le ipotesi strutturali da controllare prima di ogni altra cosa: $a \ge 1$ e $b > 1$. Se $b \le 1$ il sottoproblema non è più piccolo e la ricorsione non termina; se $a < 1$ la ricorrenza non descrive un albero di chiamate. Solo dopo questi due controlli ha senso confrontare $f(n)$ con la spartiacque.

#### **3.3. Riepilogo dei casi di inapplicabilità**

| Situazione | Esempio | Perché il teorema fallisce |
|---|---|---|
| $a < 1$ | $0.9\,T(n/2) + n$ | violata l'ipotesi $a \ge 1$ |
| $b \le 1$ | $2T(n) + n$ | il sottoproblema non decresce |
| ricorrenza sottrattiva | $T(n-1) + \log n$ | non è nella forma $T(n/b)$ |
| sottoproblemi di dimensioni diverse | $T(n/5)+T(3n/4)+n$ | non è nella forma $a\,T(n/b)$ |
| separazione solo logaritmica | $2T(n/2) + n/\log n$ | manca il margine polinomiale $n^{\varepsilon}$ |
| caso 3 senza regolarità | $T(n/2) + n(2-\cos n)$ | $a f(n/b) \le c f(n)$ non vale |

---

### **4. Esercizio — B-albero di ordine 4**

#### **4.1. Traccia**

Inserire nell'ordine le chiavi

$$
A,\; B,\; C,\; D,\; G,\; H,\; K,\; M,\; R,\; W,\; Z
$$

in un B-albero di ordine $m = 4$, inizialmente vuoto.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Richiamo: proprietà dei B-alberi**

Un **B-albero di ordine $m$** è un albero di ricerca bilanciato in cui:

- ogni nodo contiene al più $m-1$ chiavi ordinate e al più $m$ figli;
- ogni nodo interno diverso dalla radice contiene almeno $\lceil m/2 \rceil - 1$ chiavi;
- la radice ha almeno una chiave (se non è foglia, almeno due figli);
- **tutte le foglie sono alla stessa profondità**;
- le chiavi di un nodo separano gli intervalli dei figli: le chiavi del figlio $i$-esimo stanno fra la chiave $i-1$ e la chiave $i$ del nodo.

Con $m = 4$: massimo **3 chiavi** e **4 figli** per nodo, minimo **1 chiave** nei nodi interni non radice.

**Inserimento.** Si scende fino alla foglia opportuna e vi si inserisce la chiave. Se la foglia va in **overflow** (4 chiavi con $m=4$), si esegue uno **split**: la chiave mediana **sale al padre** e il nodo si divide in due. Se anche il padre va in overflow, lo split si propaga verso l'alto; se raggiunge la radice, l'albero **cresce di un livello**.

> 📌 I B-alberi sono l'unica struttura del corso che cresce **dalla radice verso l'alto** anziché dalle foglie verso il basso. È questa proprietà a garantire che tutte le foglie restino alla stessa profondità, condizione essenziale per l'uso su memoria secondaria: ogni ricerca costa esattamente lo stesso numero di accessi a disco.

#### **4.3. Inserimenti passo per passo**

**A, B, C.** Entrano tutte nella radice, che diventa $[A\;B\;C]$: tre chiavi, il massimo consentito.

**D — primo split.** L'inserimento porterebbe a $[A\;B\;C\;D]$, quattro chiavi: **overflow**. Con un numero pari di chiavi ci sono due mediane possibili, $B$ (seconda) e $C$ (terza); gli appunti mostrano entrambe le alternative e scelgono **$B$**.

- **[B]** — radice
  - **[A]**
  - **[C D]**

> 💡 La scelta della mediana fra le due candidate è una **convenzione dell'implementazione**: entrambe producono B-alberi validi, ma alberi diversi. Negli esercizi conviene dichiarare quale si adotta e mantenerla per tutto lo svolgimento.

**G.** Scende nel figlio destro: $[C\;D\;G]$, tre chiavi, nessun overflow.

**H — secondo split.** Il figlio destro andrebbe a $[C\;D\;G\;H]$: overflow. Sale la mediana **$D$**.

- **[B D]** — radice
  - **[A]**
  - **[C]**
  - **[G H]**

**K.** Scende nel terzo figlio: $[G\;H\;K]$.

**M — terzo split.** Il terzo figlio andrebbe a $[G\;H\;K\;M]$: overflow. Sale la mediana **$H$**.

- **[B D H]** — radice (ora piena)
  - **[A]**, **[C]**, **[G]**, **[K M]**

**R.** Scende nell'ultimo figlio: $[K\;M\;R]$.

**W — split che si propaga alla radice.** L'ultimo figlio andrebbe a $[K\;M\;R\;W]$: overflow. Sale la mediana **$M$**, ma la radice era già piena con $[B\;D\;H]$ e diventa $[B\;D\;H\;M]$: **overflow anche nella radice**.

Si esegue allora lo split della radice: sale la mediana **$D$**, che diventa la nuova radice, e l'albero **guadagna un livello**.

- **[D]** — nuova radice
  - **[B]** → figli **[A]**, **[C]**
  - **[H M]** → figli **[G]**, **[K]**, **[R W]**

**Z.** Scende nell'ultimo figlio: $[R\;W\;Z]$, tre chiavi, nessun overflow.

#### **4.4. Albero finale**

- **[D]** — radice, profondità 0
  - **[B]** — profondità 1
    - **[A]** — profondità 2
    - **[C]** — profondità 2
  - **[H M]** — profondità 1
    - **[G]** — profondità 2
    - **[K]** — profondità 2
    - **[R W Z]** — profondità 2

**Verifica delle proprietà:**

- nessun nodo supera $3$ chiavi ✅
- tutte le foglie ($A$, $C$, $G$, $K$, $RWZ$) sono a **profondità 2** ✅
- ogni nodo interno non radice ha almeno $1$ chiave ✅
- l'ordine è rispettato: la visita simmetrica dà $A, B, C, D, G, H, K, M, R, W, Z$ ✅

$$
\text{Ricerca, inserimento e cancellazione: } O(\log_m n) \text{ accessi ai nodi}.
$$

> 💡 Con $m$ grande (nella pratica anche $m = 100$ o più, dimensionato sulla pagina di disco) l'altezza crolla: un milione di chiavi con $m=100$ sta in un albero di **tre** livelli. È il motivo per cui i B-alberi sono la struttura di indicizzazione standard di database e file system.

---

### **5. Esercizio — Algoritmo di Dijkstra**

#### **5.1. Traccia**

Applicare Dijkstra con sorgente $B$ al grafo **non orientato pesato** con vertici $\{A,\dots,H\}$ e archi

| Arco | Peso | | Arco | Peso | | Arco | Peso |
|---|---:|---|---|---:|---|---|---:|
| $A$–$B$ | 6 | | $B$–$H$ | 5 | | $H$–$G$ | 2 |
| $A$–$C$ | 11 | | $C$–$D$ | 9 | | $H$–$F$ | 5 |
| $A$–$E$ | 2 | | $C$–$G$ | 0.5 | | $E$–$F$ | 1 |
| $B$–$C$ | 8 | | $D$–$F$ | 2.5 | | $G$–$F$ | 2 |
| $B$–$D$ | 7 | | $D$–$E$ | 4 | | $H$–$E$ | 4 |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Tabella di esecuzione**

| Estratto | $A$ | $C$ | $D$ | $E$ | $F$ | $G$ | $H$ |
|---|---|---|---|---|---|---|---|
| *(inizio)* | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ | $\infty$ |
| **B** (0) | **6, B** | **8, B** | **7, B** | $\infty$ | $\infty$ | $\infty$ | **5, B** |
| **H** (5) | 6, B | 8, B | 7, B | **9, H** | **10, H** | **7, H** | — |
| **A** (6) | — | 8, B | 7, B | **8, A** | 10, H | 7, H | — |
| **D** (7) | — | 8, B | — | 8, A | **9.5, D** | 7, H | — |
| **G** (7) | — | **7.5, G** | — | 8, A | **9, G** | — | — |
| **C** (7.5) | — | — | — | 8, A | 9, G | — | — |
| **E** (8) | — | — | — | — | 9, G | — | — |
| **F** (9) | — | — | — | — | — | — | — |

#### **5.3. Commento ai passaggi**

**Estrazione di H.** Da $H$ ($d=5$) si raggiungono $E$ ($5+4=9$), $F$ ($5+5=10$) e $G$ ($5+2=7$).

**Estrazione di A.** L'arco $A$–$E$ di peso $2$ porta $E$ a $6+2 = 8 < 9$: il predecessore passa da $H$ ad $A$.

**Estrazione di D.** L'arco $D$–$F$ di peso $2.5$ porta $F$ a $7+2.5 = 9.5 < 10$.

**Estrazione di G — il passaggio più significativo.** Da $G$ ($d=7$):

- l'arco $G$–$C$ di peso $\mathbf{0.5}$ porta $C$ a $7+0.5 = \mathbf{7.5}$, migliorando l'$8$ che $C$ aveva **direttamente** da $B$;
- l'arco $G$–$F$ di peso $2$ porta $F$ a $7+2 = 9 < 9.5$.

> ⚠️ Il caso di $C$ merita attenzione: la stima $8$ proveniva dall'arco diretto $B$–$C$, eppure il cammino $B \to H \to G \to C$ costa meno ($5+2+0.5 = 7.5$). Un arco diretto **non** garantisce di essere il cammino minimo, e la stima resta migliorabile finché il vertice non viene estratto.

**Estrazione di E.** L'arco $E$–$F$ di peso $1$ darebbe $8+1 = 9$, uguale al valore corrente: nessun miglioramento stretto, il predecessore resta $G$.

#### **5.4. Risultato**

| Vertice | $B$ | $H$ | $A$ | $D$ | $G$ | $C$ | $E$ | $F$ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| distanza | 0 | 5 | 6 | 7 | 7 | 7.5 | 8 | 9 |
| predecessore | — | $B$ | $B$ | $B$ | $H$ | $G$ | $A$ | $G$ |

**Albero dei cammini minimi radicato in $B$:**

- **B**
  - **A** (6) → **E** (8)
  - **D** (7)
  - **H** (5) → **G** (7) → **C** (7.5), **F** (9)

---

### **6. Appello 20/01/2021 — Algoritmo con divisione in due metà**

#### **6.1. Traccia**

```
Algoritmo(A)
{
    n = A.length;
    if n == 1 then return A[0];

    for i = 0 to (n/2) - 1
    {
        A1[i] = A[i];
        A2[i] = A[n/2 + i];
    }

    for i = 0 to (n/2) - 1
        for j = i+1 to (n/2) - 1
            if (A1[i] == A2[j]) then A2[j] = 0;

    b1 = Algoritmo(A1);
    b2 = Algoritmo(A2);
    return max(b1, b2);
}
```

#### **6.2. Analisi del lavoro locale**

**Primo ciclo.** Copia gli elementi nelle due metà: $n/2$ iterazioni a costo costante, quindi $\Theta(n)$.

**Doppio ciclo.** Entrambi gli indici scorrono su $n/2$ valori:

$$
\sum_{i=0}^{n/2-1}\left(\frac{n}{2} - i - 1\right) = \Theta\!\left(\left(\frac{n}{2}\right)^{2}\right) = \Theta(n^{2}).
$$

Il corpo (un confronto e un'assegnazione) costa $\Theta(1)$.

**Chiamate ricorsive.** Sono **due**, ciascuna su un vettore di $n/2$ elementi.

#### **6.3. La ricorrenza**

Sommando lavoro locale lineare e quadratico, domina il quadratico:

$$
T(n) = 2\,T\!\left(\frac{n}{2}\right) + \Theta(n^{2}).
$$

#### **6.4. Risoluzione**

$$
a = 2, \qquad b = 2, \qquad n^{\log_2 2} = n, \qquad f(n) = n^{2}.
$$

**Confronto:** $n^{2} = \Omega(n^{1+\varepsilon})$ con $\varepsilon < 1$, ad esempio $\varepsilon = 0.5$. **Caso 3.**

**Condizione di regolarità:**

$$
2\left(\frac{n}{2}\right)^{2} = 2\cdot\frac{n^{2}}{4} = \frac{n^{2}}{2} \;\le\; c\,n^{2}
\quad\Longleftrightarrow\quad
\frac{1}{2} \le c ,
$$

soddisfatta con $c = \dfrac{1}{2} < 1$ ✅

$$
\boxed{T(n) = \Theta(n^{2})}
$$

> 💡 Il costo è dominato interamente dal doppio ciclo **della radice**: le due chiamate ricorsive lavorano su metà dei dati e pagano $2\cdot(n/2)^2 = n^2/2$, cioè metà del livello superiore. La serie geometrica di ragione $1/2$ dà $2n^2$, ancora $\Theta(n^{2})$.

---

### **7. Esercizio applicativo — Albero ricoprente minimo in una rete di calcolatori**

#### **7.1. Traccia**

Una rete collega sette router $\{A,\dots,G\}$; su $A$ è installato il **server**, mentre $C$, $D$, $E$ e $G$ ospitano i **client**. I costi dei collegamenti sono

| Collegamento | Costo | | Collegamento | Costo |
|---|---:|---|---|---:|
| $A$–$B$ | 2 | | $B$–$E$ | 4 |
| $A$–$C$ | 3 | | $D$–$E$ | 1 |
| $B$–$C$ | 1 | | $E$–$F$ | 1 |
| $B$–$D$ | 1 | | $C$–$F$ | 5 |
| | | | $F$–$G$ | 1 |

Determinare l'insieme di collegamenti di costo totale minimo che mantiene tutti i router comunicanti.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Modellazione**

Il problema richiede un sottoinsieme di archi che (i) connetta tutti i vertici e (ii) abbia peso totale minimo: è esattamente la definizione di **albero ricoprente minimo** (MST). La soluzione è necessariamente un albero, perché un ciclo conterrebbe un arco rimovibile senza perdere la connessione — e rimuoverlo ridurrebbe il costo.

> 📌 Si noti che il problema **non** chiede i cammini minimi da $A$: l'MST minimizza il **costo totale dell'infrastruttura**, non la distanza di ciascun client dal server. Sono due obiettivi diversi e in generale producono alberi diversi. Qui, ad esempio, nell'MST il client $C$ raggiunge il server via $C \to B \to A$ con costo $3$, mentre l'arco diretto $A$–$C$ costerebbe $3$: pari. Ma il client $G$ dista $2+1+1+1+1 = 6$ dal server nell'MST, mentre un albero dei cammini minimi potrebbe avvicinarlo.

#### **7.3. Risoluzione con Kruskal**

Kruskal ordina gli archi per peso crescente e li accetta uno a uno, scartando quelli che formerebbero un ciclo (verifica con una struttura MFSET).

| # | Arco | Peso | Componenti prima | Esito |
|---:|---|---:|---|---|
| 1 | $B$–$C$ | 1 | tutti separati | **accettato** |
| 2 | $B$–$D$ | 1 | $\{B,C\}$, $\{D\}$ | **accettato** |
| 3 | $D$–$E$ | 1 | $\{B,C,D\}$, $\{E\}$ | **accettato** |
| 4 | $E$–$F$ | 1 | $\{B,C,D,E\}$, $\{F\}$ | **accettato** |
| 5 | $F$–$G$ | 1 | $\{B,C,D,E,F\}$, $\{G\}$ | **accettato** |
| 6 | $A$–$B$ | 2 | $\{A\}$, $\{B,\dots,G\}$ | **accettato** |
| 7 | $A$–$C$ | 3 | $A$ e $C$ già connessi | scartato (ciclo) |
| 8 | $B$–$E$ | 4 | già connessi | scartato (ciclo) |
| 9 | $C$–$F$ | 5 | già connessi | scartato (ciclo) |

#### **7.4. Risultato**

$$
\text{MST} = \{\,B\text{–}C,\; B\text{–}D,\; D\text{–}E,\; E\text{–}F,\; F\text{–}G,\; A\text{–}B\,\}
$$

**Numero di archi:** $6 = |V| - 1 = 7-1$ ✅

**Costo totale:**

$$
1+1+1+1+1+2 = \boxed{7}
$$

**Struttura dell'albero** (radicandolo in $A$, dove sta il server):

- **A**
  - **B** (2)
    - **C** (1) — client
    - **D** (1) → **E** (1) — client → **F** (1) → **G** (1) — client

#### **7.5. Verifica con la proprietà del taglio**

Ogni arco selezionato è l'arco **più leggero** di un taglio che separa la componente costruita dal resto: $B$–$C$, $B$–$D$, $D$–$E$, $E$–$F$, $F$–$G$ hanno tutti peso $1$, il minimo possibile; $A$–$B$ con peso $2$ è l'arco più leggero fra i due che collegano $A$ al resto ($A$–$B$ da $2$ e $A$–$C$ da $3$). La correttezza è quindi verificabile arco per arco.

$$
\text{Complessità di Kruskal: } O(|E|\log|E|) \text{, dominata dall'ordinamento.}
$$

---

### **8. Sintesi**

| # | Oggetto | Tecnica | Risultato |
|---|---|---|---|
| 1 | pseudocodice con Binary-Search (25/07/2023) | sommatoria + prodotto | $\Theta(n^{2}\log n)$ |
| 2 | $P(n)$ con chiamate $F(i)$ (13/01/2020) | sommatoria di logaritmi, Stirling | $\Theta(n\log n\log\log n)$ |
| 3 | $T(n)=2T(n/2)+n/\log n$ | — | teorema **non applicabile**; vero valore $\Theta(n\log\log n)$ |
| 3 | $T(n)=0.9T(n/2)+n/\log n$ | — | teorema **non applicabile** ($a<1$) |
| 4 | B-albero di ordine 4, 11 chiavi | inserimento con split | 3 livelli, radice $[D]$ |
| 5 | Dijkstra da $B$ | rilassamento | $d[F]=9$; $C$ migliora da 8 a 7.5 via $G$ |
| 6 | algoritmo con due metà (20/01/2021) | metodo esperto, caso 3 | $\Theta(n^{2})$ |
| 7 | rete di router | Kruskal | MST di 6 archi, costo $7$ |

> ✅ Il punto da portare all'esame: **prima di applicare il metodo esperto, verificare le ipotesi**. $a \ge 1$, $b > 1$, forma $a\,T(n/b)+f(n)$, e — per i casi 1 e 3 — separazione **polinomiale** fra $f(n)$ e la spartiacque. Le ricorrenze del §3 sono progettate esattamente per far cadere chi salta questi controlli.
