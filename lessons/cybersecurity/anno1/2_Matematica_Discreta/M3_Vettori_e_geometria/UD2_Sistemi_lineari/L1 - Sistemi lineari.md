# **M3 UD2 Lezione 1 - Sistemi lineari**

### **1. Obiettivi e prerequisiti**

Questa lezione traduce un sistema lineare in forma matriciale e presenta il metodo di Gauss-Jordan. Sono richieste le operazioni tra elementi di un campo e il prodotto tra matrici e vettori.

Al termine sapremo:

- costruire matrice dei coefficienti, vettore delle incognite, vettore dei termini noti e matrice completa;
- applicare operazioni elementari di riga senza cambiare le soluzioni;
- distinguere sistemi determinati, impossibili e indeterminati;
- interpretare le variabili libere come parametri.

---

### **2. Sistema lineare generale**

Sia $K$ un campo, per esempio $\mathbb Q$, $\mathbb R$ oppure $\mathbb Z_p$ con $p$ primo. Un sistema lineare di $m$ equazioni in $n$ incognite ha la forma

$$
\begin{cases}
a_{11}x_1+a_{12}x_2+\cdots+a_{1n}x_n=b_1,\\
a_{21}x_1+a_{22}x_2+\cdots+a_{2n}x_n=b_2,\\
\hspace{3.5cm}\vdots\\
a_{m1}x_1+a_{m2}x_2+\cdots+a_{mn}x_n=b_m,
\end{cases}
$$

con $a_{ij},b_i\in K$ e incognite $x_j\in K$.

I coefficienti $a_{ij}$ sono indicizzati dalla riga $i$, cioè dall'equazione, e dalla colonna $j$, cioè dall'incognita.

---

### **3. Scrittura matriciale**

Definiamo:

$$
A=
\begin{bmatrix}
a_{11}&a_{12}&\cdots&a_{1n}\\
a_{21}&a_{22}&\cdots&a_{2n}\\
\vdots&\vdots&\ddots&\vdots\\
a_{m1}&a_{m2}&\cdots&a_{mn}
\end{bmatrix}
$$

$$
\mathbf x=
\begin{bmatrix}
x_1\\x_2\\\vdots\\x_n
\end{bmatrix},
\qquad
\mathbf b=
\begin{bmatrix}
b_1\\b_2\\\vdots\\b_m
\end{bmatrix}
$$

Il sistema equivale all'unica equazione matriciale

$$
A\mathbf x=\mathbf b
$$

I nomi usati sono:

- $A$: **matrice dei coefficienti**, di formato $m\times n$;
- $\mathbf x$: **vettore delle incognite**, di formato $n\times1$;
- $\mathbf b$: **vettore dei termini noti**, di formato $m\times1$;
- $[A\mid\mathbf b]$: **matrice completa**, o aumentata, di formato $m\times(n+1)$:

$$
[A\mid\mathbf b]=
\left[
\begin{array}{cccc|c}
a_{11}&a_{12}&\cdots&a_{1n}&b_1\\
a_{21}&a_{22}&\cdots&a_{2n}&b_2\\
\vdots&\vdots&\ddots&\vdots&\vdots\\
a_{m1}&a_{m2}&\cdots&a_{mn}&b_m
\end{array}
\right]
$$

> 📌 Il prodotto è dimensionalmente coerente: $(m\times n)(n\times1)=m\times1$.

---

### **4. Operazioni elementari di riga**

Le seguenti trasformazioni producono un sistema equivalente, cioè con lo stesso insieme delle soluzioni:

1. scambiare due righe:

$$
R_i\leftrightarrow R_j
$$

2. moltiplicare una riga per uno scalare non nullo $\lambda\in K$:

$$
R_i\leftarrow\lambda R_i,
\qquad \lambda\neq0
$$

3. sommare a una riga un multiplo di un'altra:

$$
R_i\leftarrow R_i+\lambda R_j
$$

4. eliminare una riga interamente nulla.

**Perché conservano le soluzioni.** Lo scambio modifica soltanto l'ordine delle equazioni. La moltiplicazione per $\lambda\neq0$ è reversibile moltiplicando per $\lambda^{-1}$. La sostituzione $R_i\leftarrow R_i+\lambda R_j$ è reversibile con $R_i\leftarrow R_i-\lambda R_j$. Una riga nulla corrisponde all'identità $0=0$ e non impone vincoli.

> ⚠️ Moltiplicare una riga per zero non è ammesso: cancellerebbe un'equazione e potrebbe aggiungere soluzioni.

---

### **5. Metodo di Gauss-Jordan**

Il metodo ricerca successivamente i **pivot**, cioè coefficienti non nulli usati per eliminare gli altri elementi della stessa colonna.

Nella forma descritta dal PDF:

1. se $a_{11}=0$, si scambia la prima riga con una riga successiva che abbia primo coefficiente non nullo;
2. mediante multipli della prima riga si annullano tutti gli altri coefficienti della prima colonna;
3. si cerca poi un pivot nella seconda colonna e si annullano gli altri coefficienti di quella colonna;
4. si continua nello stesso modo;
5. le righe interamente nulle possono essere rimosse.

La formulazione generale tiene conto del fatto che un pivot potrebbe trovarsi in una colonna successiva: si procede da sinistra verso destra e dall'alto verso il basso, senza richiedere che il pivot $i$-esimo sia necessariamente in posizione $(i,i)$.

Poiché ogni scalare non nullo di un campo possiede inverso, il metodo funziona in qualunque campo. In $\mathbb Z_p$ tutte le operazioni, incluse le divisioni per i pivot, sono eseguite modulo $p$.

---

### **6. I tre possibili esiti**

#### **6.1. Una sola soluzione**

Se dopo la riduzione ogni incognita possiede un pivot, il sistema può assumere la forma

$$
\begin{cases}
c_{11}x_1=d_1,\\
c_{22}x_2=d_2,\\
\hspace{1.5cm}\vdots\\
c_{nn}x_n=d_n,
\end{cases}
$$

con $c_{ii}\neq0$. Si ottiene allora l'unica soluzione

$$
x_i=c_{ii}^{-1}d_i,
\qquad i=1,\ldots,n
$$

Nel campo reale questa scrittura coincide con $x_i=d_i/c_{ii}$; in un campo finito $c_{ii}^{-1}$ è l'inverso moltiplicativo modulo $p$.

#### **6.2. Nessuna soluzione**

Se compare una riga

$$
\left[
\begin{array}{cccc|c}
0&0&\cdots&0&d
\end{array}
\right],
\qquad d\neq0
$$

essa rappresenta l'equazione assurda $0=d$. Il sistema è quindi **impossibile** e non ha soluzioni.

> ⚠️ Questa riga non è una riga nulla e non può essere eliminata.

#### **6.3. Infinite soluzioni**

Se il sistema è compatibile ma i pivot sono soltanto $k<n$, restano $n-k$ **variabili libere**. Le $k$ variabili pivot si esprimono in funzione delle altre.

Una forma tipica è

$$
\left[
\begin{array}{cccc|cccc|c}
c_{11}&0&\cdots&0&c_{1,k+1}&\cdots&c_{1n}& &d_1\\
0&c_{22}&\cdots&0&c_{2,k+1}&\cdots&c_{2n}& &d_2\\
\vdots&\vdots&\ddots&\vdots&\vdots&&\vdots&&\vdots\\
0&0&\cdots&c_{kk}&c_{k,k+1}&\cdots&c_{kn}& &d_k
\end{array}
\right]
$$

Per esempio, la prima equazione dà

$$
x_1=c_{11}^{-1}
\left(d_1-c_{1,k+1}x_{k+1}-\cdots-c_{1n}x_n\right)
$$

Assegnando liberamente i valori di $x_{k+1},\ldots,x_n$ si ottengono tutte le soluzioni. Il PDF indica simbolicamente $\infty^{n-k}$ soluzioni: il significato didattico corretto è “infinitamente molte soluzioni dipendenti da $n-k$ parametri” quando il campo è infinito. Su un campo finito $K$ con $|K|=q$, le soluzioni sono invece esattamente $q^{n-k}$.

---

### **7. Esempio completo**

Consideriamo su $\mathbb R$ il sistema

$$
\begin{cases}
x+y+z=2,\\
2x+2y+2z=4,\\
x-y+z=0.
\end{cases}
$$

La matrice completa è

$$
\left[
\begin{array}{ccc|c}
1&1&1&2\\
2&2&2&4\\
1&-1&1&0
\end{array}
\right].
$$

Applichiamo

$$
R_2\leftarrow R_2-2R_1,
\qquad
R_3\leftarrow R_3-R_1
$$

ottenendo

$$
\left[
\begin{array}{ccc|c}
1&1&1&2\\
0&0&0&0\\
0&-2&0&-2
\end{array}
\right].
$$

Scambiamo le ultime due righe e dividiamo la seconda per $-2$:

$$
\left[
\begin{array}{ccc|c}
1&1&1&2\\
0&1&0&1
\end{array}
\right].
$$

Infine $R_1\leftarrow R_1-R_2$:

$$
\left[
\begin{array}{ccc|c}
1&0&1&1\\
0&1&0&1
\end{array}
\right].
$$

La variabile $z$ è libera. Posto $z=t$,

$$
x=1-t,
\qquad y=1,
\qquad z=t,
\qquad t\in\mathbb R
$$

Il sistema ha dunque infinite soluzioni dipendenti da un parametro.

---

### **8. Errori comuni**

- Eseguire un'operazione soltanto sui coefficienti e non sul termine noto.
- Dividere per un pivot nullo invece di cercare uno scambio di righe.
- Eliminare una riga del tipo $[0\ \cdots\ 0\mid d]$ con $d\neq0$.
- Confondere il numero di equazioni con il numero di pivot.
- Dimenticare che le divisioni in $\mathbb Z_p$ richiedono inversi modulari.

---

> ✅ Il sistema $A\mathbf x=\mathbf b$ si studia riducendo la matrice completa $[A\mid\mathbf b]$ mediante operazioni elementari di riga. Una riga assurda produce incompatibilità; un pivot per ogni incognita produce una soluzione unica; variabili libere producono famiglie di soluzioni parametrizzate.
