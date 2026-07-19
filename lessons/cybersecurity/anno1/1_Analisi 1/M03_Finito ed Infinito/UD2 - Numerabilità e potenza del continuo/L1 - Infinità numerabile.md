# **M3 UD2 Lezione 1 - Infinità numerabile**

### **1. Convenzione terminologica**

La parola “numerabile” non è usata allo stesso modo in tutti i testi. In questo modulo adotteremo la convenzione seguente:

- **infinito numerabile:** equipotente a $\mathbb N$;
- **al più numerabile:** finito oppure infinito numerabile;
- **non numerabile:** non al più numerabile.

Quindi $A$ è infinito numerabile se esiste una biiezione:

$$
e:\mathbb N\to A.
$$

In tal caso:

$$
|A|=|\mathbb N|=\aleph_0.
$$

> 📌 Nel seguito “numerabile” da solo sarà usato come abbreviazione di “infinito numerabile”; quando sono ammessi anche insiemi finiti scriveremo “al più numerabile”.

### **2. Enumerazioni e liste**

Una scrittura:

$$
a_0,a_1,a_2,\ldots
$$

corrisponde a una funzione $e:\mathbb N\to A$ con $e(n)=a_n$.

- Se $e$ è **suriettiva**, ogni elemento compare almeno una volta, ma possono esserci ripetizioni. Questo basta per dimostrare che $A$ è al più numerabile.
- Se $e$ è **biiettiva**, ogni elemento compare esattamente una volta. Questa è un'enumerazione senza ripetizioni e dimostra che $A$ è infinito numerabile.

Una semplice lista intuitiva non è una prova finché non si dimostra che nessun elemento manca e, se si dichiara una biiezione, che non vi sono duplicati.

### **3. I naturali pari**

La funzione:

$$
d:\mathbb N\to2\mathbb N,
\qquad
d(n)=2n,
$$

è biiettiva. L'inversa è:

$$
d^{-1}(2n)=n.
$$

Pertanto:

$$
|2\mathbb N|=\aleph_0.
$$

### **4. Numerabilità di $\mathbb Z$**

L'elenco:

$$
0,1,-1,2,-2,3,-3,\ldots
$$

diventa la funzione esplicita $z:\mathbb N\to\mathbb Z$:

$$
z(0)=0,
$$

e, per $k\geq1$:

$$
z(2k-1)=k,
\qquad
z(2k)=-k.
$$

**Iniettività.** Gli indici dispari positivi producono interi positivi distinti; gli indici pari positivi producono interi negativi distinti; l'indice $0$ produce soltanto $0$. Le tre immagini sono disgiunte, quindi valori uguali provengono dallo stesso indice.

**Suriettività.** Se $m=0$, allora $m=z(0)$. Se $m>0$, allora $m=z(2m-1)$. Se $m<0$, allora:

$$
m=z(-2m).
$$

L'inversa è dunque:

$$
z^{-1}(m)=
\begin{cases}
0,&m=0,\\
2m-1,&m>0,\\
-2m,&m<0.
\end{cases}
$$

Concludiamo:

$$
|\mathbb Z|=\aleph_0.
$$

<!-- TODO FIGURA:
Disporre gli interi sulla retta con 0 al centro e mostrare il percorso 0,1,-1,2,-2,... mediante archi alternati verso destra e verso sinistra; accanto riportare gli indici naturali 0,1,2,3,4,... collegati ai valori della funzione z. Obiettivo didattico: trasformare l'elenco intuitivo degli interi in una corrispondenza biunivoca indicizzata da N.
-->

### **5. Numerabilità di $\mathbb N\times\mathbb N$**

Le coppie possono essere ordinate lungo diagonali di somma costante:

$$
m+n=0,1,2,\ldots
$$

La **funzione di accoppiamento di Cantor** è:

$$
\pi:\mathbb N\times\mathbb N\to\mathbb N,
\qquad
\pi(m,n)=\frac{(m+n)(m+n+1)}{2}+n.
$$

Poniamo:

$$
T_w=\frac{w(w+1)}{2}.
$$

Sulla diagonale $m+n=w$, i valori di $\pi$ sono:

$$
T_w,T_w+1,\ldots,T_w+w.
$$

Sono naturali perché uno tra $w$ e $w+1$ è pari.

**Suriettività e inversa.** Dato $r\in\mathbb N$, esiste un unico $w$ tale che:

$$
T_w\leq r<T_{w+1}.
$$

Definiamo:

$$
n=r-T_w,
\qquad
m=w-n.
$$

Poiché $0\leq n\leq w$, si ha $(m,n)\in\mathbb N^2$ e $\pi(m,n)=r$.

**Iniettività.** La procedura appena descritta ricostruisce da $r$ un'unica diagonale $w$ e un'unica coppia $(m,n)$. Due coppie con la stessa immagine devono quindi coincidere.

Pertanto $\pi$ è biiettiva e:

$$
|\mathbb N\times\mathbb N|=\aleph_0.
$$

<!-- TODO FIGURA:
Disegnare una griglia con asse orizzontale m e verticale n, entrambi a partire da 0. Evidenziare le diagonali m+n=w e numerare le coppie secondo la funzione di Cantor: (0,0)->0, (1,0)->1, (0,1)->2, (2,0)->3, (1,1)->4, (0,2)->5, proseguendo con frecce lungo ogni diagonale. Obiettivo didattico: mostrare che una griglia infinita bidimensionale può essere attraversata senza saltare coppie e collegare il percorso alla formula pi(m,n).
-->

### **6. Sottoinsiemi di insiemi al più numerabili**

**Teorema.** Se $A$ è al più numerabile e $S\subseteq A$, allora $S$ è al più numerabile.

**Dimostrazione.** Se $A$ è finito, il risultato è già noto. Supponiamo $A$ infinito numerabile e sia $e:\mathbb N\to A$ biiettiva. L'insieme degli indici:

$$
J=\{n\in\mathbb N:e(n)\in S\}
$$

è un sottoinsieme di $\mathbb N$. Se $J$ è finito, anche $S=e(J)$ è finito. Se $J$ è infinito, possiamo ordinarne gli elementi in modo strettamente crescente:

$$
j_0=\min J,
\qquad
j_{k+1}=\min\bigl(J\setminus\{j_0,\ldots,j_k\}\bigr).
$$

La funzione $k\mapsto e(j_k)$ è una biiezione $\mathbb N\to S$. $\square$

Quindi un sottoinsieme infinito di un insieme infinito numerabile è infinito numerabile. La frase “ogni sottoinsieme di un numerabile è numerabile” è ambigua se non si dichiara se gli insiemi finiti rientrino nella convenzione.

### **7. Immagini di insiemi numerabili**

Se $A$ è al più numerabile e $f:A\to B$ è una funzione qualsiasi, allora l'immagine $f(A)$ è al più numerabile.

Infatti, se $e:\mathbb N\to A$ è suriettiva, la composizione:

$$
f\circ e:\mathbb N\to f(A)
$$

è suriettiva. Le ripetizioni non impediscono l'al più numerabilità; si possono eliminare conservando la prima occorrenza di ogni valore.

### **8. Prodotti finiti**

Poiché $\mathbb N^2$ è numerabile, per induzione su $k$ si ottiene:

$$
|\mathbb N^k|=\aleph_0
$$

per ogni intero finito $k\geq1$.

Il passo induttivo usa:

$$
\mathbb N^{k+1}=\mathbb N^k\times\mathbb N
$$

e compone due enumerazioni con la funzione di accoppiamento. Se $A_1,\ldots,A_k$ sono al più numerabili, scegliendo iniezioni $A_j\to\mathbb N$ si ottiene un'iniezione:

$$
A_1\times\cdots\times A_k\to\mathbb N^k.
$$

Il prodotto è quindi al più numerabile.

### **9. Errori frequenti**

- Presentare $0,1,-1,2,-2,\ldots$ come prova completa senza mostrare che ogni intero compare una sola volta.
- Disegnare la griglia $\mathbb N^2$ senza definire un percorso che raggiunga ogni coppia.
- Confondere una suriezione con ripetizioni e una biiezione; la prima dimostra solo l'al più numerabilità.
- Dimenticare che un sottoinsieme finito di $\mathbb N$ non è equipotente a $\mathbb N$.
- Credere che $\mathbb N^2$ debba avere cardinalità maggiore perché è bidimensionale.

### **10. Esercizi**

1. Calcolare $\pi(m,n)$ per tutte le coppie con $m+n\leq3$ e applicare la procedura inversa a $r=17$.
2. Verificare direttamente l'inversa della biiezione $z:\mathbb N\to\mathbb Z$.
3. Dimostrare che l'insieme dei multipli interi di $7$ è infinito numerabile.
4. Dimostrare che $\mathbb Z^3$ è numerabile costruendo le funzioni necessarie.
5. Sia $S=\{n^2:n\in\mathbb N\}$. Stabilire se la funzione $n\mapsto n^2$ è soltanto suriettiva o anche biiettiva sul codominio dichiarato.
6. Mostrare che l'immagine di una successione reale $(a_n)$ è al più numerabile, anche quando la successione assume infinite volte lo stesso valore.

### **11. Riepilogo**

> ✅ Un insieme infinito numerabile ammette un elenco biiettivo indicizzato da $\mathbb N$; una suriezione da $\mathbb N$ basta per l'al più numerabilità.

- $|\mathbb Z|=\aleph_0$ mediante una formula esplicita;
- $|\mathbb N^2|=\aleph_0$ mediante l'accoppiamento di Cantor;
- sottoinsiemi e immagini di insiemi al più numerabili restano al più numerabili;
- ogni prodotto cartesiano finito di insiemi al più numerabili è al più numerabile;
- la convenzione terminologica resta esplicita in tutto il modulo.
