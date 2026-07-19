# **M3 UD1 Lezione 1A - Principi di conteggio e principio dei cassetti**

### **1. Contare senza elencare**

Quando un insieme è costruito combinando scelte più semplici, una biiezione permette di tradurre la costruzione in una formula. I principi seguenti non sono regole mnemoniche: derivano da scomposizioni in parti disgiunte o da prodotti cartesiani.

### **2. Principio di addizione**

Se $A$ e $B$ sono finiti e disgiunti, allora:

$$
|A\cup B|=|A|+|B|.
$$

**Dimostrazione.** Se $|A|=m$ e $|B|=n$, enumeriamo prima gli elementi di $A$ con gli indici $0,\ldots,m-1$ e poi quelli di $B$ con gli indici $m,\ldots,m+n-1$. La disgiunzione garantisce che nessun elemento venga contato due volte. Si ottiene una biiezione $I_{m+n}\to A\cup B$. $\square$

Per una famiglia finita di insiemi a due a due disgiunti:

$$
\left|\bigcup_{j=1}^{r}A_j\right|
=
\sum_{j=1}^{r}|A_j|.
$$

### **3. Inclusione-esclusione**

Se $A$ e $B$ non sono disgiunti, la somma $|A|+|B|$ conta due volte gli elementi di $A\cap B$. Quindi:

$$
|A\cup B|=|A|+|B|-|A\cap B|.
$$

Una giustificazione formale usa la partizione disgiunta:

$$
A\cup B=(A\setminus B)\mathbin{\dot\cup}(A\cap B)\mathbin{\dot\cup}(B\setminus A).
$$

Per tre insiemi finiti:

$$
\begin{aligned}
|A\cup B\cup C|
={}&|A|+|B|+|C|\\
&-|A\cap B|-|A\cap C|-|B\cap C|\\
&+|A\cap B\cap C|.
\end{aligned}
$$

L'intersezione tripla viene aggiunta perché, dopo le sottrazioni delle tre intersezioni doppie, i suoi elementi sono stati eliminati una volta di troppo.

### **4. Principio di moltiplicazione**

Se una scelta può essere compiuta in $m$ modi e, per ciascuna prima scelta, una seconda scelta può essere compiuta in $n$ modi, le coppie di scelte sono $m\cdot n$.

Formalmente, per insiemi finiti $A$ e $B$:

$$
|A\times B|=|A|\cdot|B|.
$$

**Dimostrazione.** Se $A=\{a_0,\ldots,a_{m-1}\}$ e $B=\{b_0,\ldots,b_{n-1}\}$, la funzione:

$$
\Phi:I_m\times I_n\to A\times B,
\qquad
\Phi(i,j)=(a_i,b_j),
$$

è biiettiva. Le $m$ righe contengono ciascuna $n$ coppie, quindi le coppie sono $m\cdot n$. $\square$

Per $r$ insiemi finiti:

$$
|A_1\times\cdots\times A_r|
=
\prod_{j=1}^{r}|A_j|.
$$

### **5. Insieme delle parti e funzioni**

Sia $|A|=n$. Ogni sottoinsieme $S\subseteq A$ è determinato, per ciascun elemento di $A$, dalla scelta binaria “appartiene” oppure “non appartiene”. Perciò:

$$
|\mathcal P(A)|=2^n.
$$

La giustificazione rigorosa è la biiezione:

$$
S\longmapsto\chi_S,
$$

dove:

$$
\chi_S:A\to\{0,1\},
\qquad
\chi_S(a)=
\begin{cases}
1,&a\in S,\\
0,&a\notin S.
\end{cases}
$$

Più in generale, se $|A|=n$ e $|B|=m$, l'insieme $B^A$ di tutte le funzioni $A\to B$ ha cardinalità:

$$
|B^A|=m^n.
$$

Infatti, per ciascuno dei $n$ elementi del dominio si sceglie una delle $m$ immagini.

Se $n\leq m$, le funzioni iniettive $A\to B$ sono:

$$
m(m-1)\cdots(m-n+1)=\frac{m!}{(m-n)!}.
$$

Se $n=m$, le biiezioni $A\to B$ sono $n!$. Il conteggio delle suriezioni richiede inclusione-esclusione; per $n\geq m$ il loro numero è:

$$
\sum_{j=0}^{m}(-1)^j\binom{m}{j}(m-j)^n.
$$

Quest'ultima formula è un approfondimento: si sottraggono le funzioni che omettono almeno un valore del codominio, correggendo poi le sovrapposizioni.

> ⚠️ $2^{|A|}$ conta scelte indipendenti binarie; non significa $2\cdot|A|$.

### **6. Principio dei cassetti**

**Teorema, forma elementare.** Se $n+1$ oggetti sono distribuiti in $n$ cassetti, almeno un cassetto contiene almeno due oggetti.

**Forma funzionale.** Se $A$ e $B$ sono finiti e $|A|>|B|$, nessuna funzione:

$$
f:A\to B
$$

può essere iniettiva.

**Dimostrazione.** Siano $|A|=N$ e $|B|=k$ con $N>k$. Se $f$ fosse iniettiva, componendola con le biiezioni $I_N\to A$ e $B\to I_k$ otterremmo un'iniezione $I_N\to I_k$. Il lemma finito dimostrato nella lezione precedente esclude tale iniezione quando $N>k$. $\square$

<!-- TODO FIGURA:
Rappresentare a sinistra n+1 oggetti distinti e a destra n cassetti etichettati; tracciare una freccia da ogni oggetto al cassetto assegnato e mettere in evidenza due frecce che terminano nello stesso cassetto. Indicare sotto il disegno la funzione f:A->B e l'uguaglianza f(a_1)=f(a_2) con a_1 diverso da a_2. Obiettivo didattico: visualizzare l'equivalenza tra distribuzione di oggetti e non iniettività di una funzione da un insieme più grande a uno più piccolo.
-->

### **7. Forma generalizzata**

**Teorema.** Distribuendo $N$ oggetti in $k\geq1$ cassetti, almeno un cassetto contiene almeno:

$$
\left\lceil\frac{N}{k}\right\rceil
$$

oggetti.

**Dimostrazione.** Poniamo $r=\left\lceil N/k\right\rceil$. Se ogni cassetto contenesse al più $r-1$ oggetti, in totale vi sarebbero al più:

$$
k(r-1)
$$

oggetti. Dalla proprietà del soffitto $r-1<N/k$, quindi:

$$
k(r-1)<N,
$$

contraddicendo il fatto che gli oggetti sono $N$. $\square$

### **8. Applicazioni**

#### **8.1 Mesi di nascita**

Tra $13$ persone almeno due sono nate nello stesso mese. Gli oggetti sono le persone, i cassetti i $12$ mesi e la funzione associa a ogni persona il proprio mese di nascita.

#### **8.2 Resti modulo $n$**

Scelti $n+1$ interi, almeno due hanno lo stesso resto nella divisione per $n$. I cassetti sono le classi di resto $0,1,\ldots,n-1$.

#### **8.3 Collisioni inevitabili**

Una funzione hash con dominio di $2^{256}+1$ messaggi e codominio di $2^{256}$ digest non può essere iniettiva. Il principio garantisce l'esistenza di una collisione, ma non fornisce un metodo efficiente per trovarla.

#### **8.4 Forma quantitativa**

Tra $100$ tentativi di autenticazione distribuiti su $24$ ore, almeno un'ora contiene almeno:

$$
\left\lceil\frac{100}{24}\right\rceil=5
$$

tentativi.

### **9. Errori frequenti**

- Non dichiarare quali siano oggetti, cassetti e funzione di assegnazione.
- Concludere che esista esattamente un cassetto doppio: il teorema garantisce “almeno uno”.
- Usare $\lfloor N/k\rfloor$ al posto di $\lceil N/k\rceil$ nella conclusione generalizzata.
- Credere che il principio costruisca la collisione: ne dimostra l'esistenza.

### **10. Esercizi**

1. Dimostrare che tra sei interi esistono due con la stessa parità e precisare perché la conclusione ottenibile è in realtà più forte.
2. In un archivio ci sono $73$ file creati in una settimana. Dimostrare che in almeno un giorno ne sono stati creati almeno $11$.
3. Scelti $11$ numeri distinti in $\{1,2,\ldots,20\}$, dimostrare che due sono consecutivi. Suggerimento: costruire dieci cassetti $\{1,2\},\{3,4\},\ldots,\{19,20\}$ e discutere il limite di questo raggruppamento.
4. Mostrare che tra $n+1$ interi esistono due la cui differenza è divisibile per $n$.
5. Per ciascuno dei problemi precedenti indicare esplicitamente oggetti, cassetti, funzione e conclusione garantita.
6. Calcolare il numero di password di lunghezza $8$ su un alfabeto di $62$ simboli e il numero di password che contengono almeno una cifra, usando il complemento.

### **11. Riepilogo**

> ✅ Addizione, moltiplicazione e inclusione-esclusione traducono decomposizioni insiemistiche; il principio dei cassetti traduce un confronto di cardinalità in una collisione inevitabile.

- $|A\times B|=|A|\cdot|B|$;
- $|\mathcal P(A)|=2^{|A|}$;
- $|B^A|=|B|^{|A|}$ nel caso finito;
- se $|A|>|B|$, ogni funzione $A\to B$ identifica almeno due elementi;
- con $N$ oggetti e $k$ cassetti, un cassetto ne contiene almeno $\lceil N/k\rceil$.
