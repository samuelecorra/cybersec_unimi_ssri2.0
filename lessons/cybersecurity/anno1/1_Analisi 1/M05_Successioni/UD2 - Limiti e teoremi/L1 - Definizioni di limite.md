# **M5 UD2 Lezione 1 - Definizioni di limite**

### **1. Limite finito**

Sia $(a_n)$ una successione reale. Si dice che $a_n$ converge a $L\in\mathbb R$ se

$$
\forall\varepsilon>0\ \exists N\in\mathbb N\ \forall n\ge N:
|a_n-L|<\varepsilon.
$$

Si scrive

$$
a_n\to L
$$

oppure

$$
\lim_{n\to\infty}a_n=L.
$$

Il quantificatore $\forall\varepsilon$ richiede ogni precisione; $N=N(\varepsilon)$ può dipendere da $\varepsilon$, ma non dal singolo $n\ge N$.

Equivalentemente,

$$
a_n\in(L-\varepsilon,L+\varepsilon)
$$

definitivamente.

<!-- TODO FIGURA: grafico discreto convergente a L con fascia orizzontale (L-epsilon,L+epsilon), soglia verticale N e tutti i punti successivi interni; obiettivo didattico: tradurre la definizione epsilon-N in una proprietà della coda. -->

<!-- TODO FIGURA: stesso grafico con due fasce epsilon_1 ed epsilon_2 più stretta e soglie N_1<N_2, evidenziando che N dipende dalla precisione; obiettivo didattico: visualizzare la dipendenza N(epsilon) e l'indipendenza dai singoli indici successivi. -->

---

### **2. Una verifica completa: 1/(n+1) -> 0**

Dato $\varepsilon>0$, vogliamo

$$
\frac1{n+1}<\varepsilon.
$$

È sufficiente $n+1>1/\varepsilon$. Scegliamo

$$
N(\varepsilon)=\left\lceil\frac1\varepsilon\right\rceil.
$$

Se $n\ge N$, allora $n+1>1/\varepsilon$, quindi

$$
\left|\frac1{n+1}-0\right|<\varepsilon.
$$

Pertanto $1/(n+1)\to0$. Il soffitto produce un indice intero che supera la soglia reale richiesta.

---

### **3. Potenze reciproche**

Per $\alpha>0$,

$$
\frac1{(n+1)^\alpha}\to0.
$$

Dato $\varepsilon>0$, basta imporre

$$
n+1>\varepsilon^{-1/\alpha}.
$$

La scelta

$$
N=\left\lceil\varepsilon^{-1/\alpha}\right\rceil
$$

garantisce la tesi. La dipendenza da $\alpha$ e $\varepsilon$ è esplicita.

---

### **4. Successioni razionali lineari**

Siano $a,b,c,d\in\mathbb R$ con $c\ne0$. Per $n$ abbastanza grande il denominatore $cn+d$ non è nullo e

$$
\frac{an+b}{cn+d}-\frac ac
=
\frac{bc-ad}{c(cn+d)}.
$$

Se $bc-ad=0$, l'uguaglianza con $a/c$ è esatta. Altrimenti, per $n\ge2|d/c|$,

$$
|cn+d|\ge |c|n-|d|\ge\frac{|c|n}{2}.
$$

Quindi

$$
\left|\frac{an+b}{cn+d}-\frac ac\right|
\le
\frac{2|bc-ad|}{|c|^2n}.
$$

Scegliendo $N$ maggiore sia di $2|d/c|$ sia di $2|bc-ad|/(|c|^2\varepsilon)$ si ottiene la convergenza a $a/c$.

---

### **5. Negazione della convergenza**

La negazione corretta di $a_n\to L$ è

$$
\exists\varepsilon_0>0\ \forall N\in\mathbb N\ \exists n\ge N:
|a_n-L|\ge\varepsilon_0.
$$

Esiste quindi una precisione fissa che viene violata arbitrariamente avanti nella successione.

Per $a_n=(-1)^n$, nessun $L$ può essere limite. Se $L\ge0$, per ogni $N$ scegliamo un indice dispari $n\ge N$; allora

$$
|a_n-L|=|-1-L|\ge1.
$$

Se $L<0$, scegliamo un indice pari e otteniamo $|1-L|>1$. In entrambi i casi la negazione vale con $\varepsilon_0=1$.

<!-- TODO FIGURA: punti di (-1)^n su due quote -1 e 1, un candidato L arbitrario e una fascia di raggio epsilon_0 che non può contenere entrambe le code pari e dispari; obiettivo didattico: visualizzare la negazione quantificata della convergenza. -->

---

### **6. Unicità del limite**

> **Teorema.** Una successione reale convergente ha un unico limite.

**Dimostrazione.** Supponiamo $a_n\to L$ e $a_n\to M$ con $L\ne M$. Poniamo

$$
\varepsilon=\frac{|L-M|}{3}>0.
$$

Esistono $N_L,N_M$ tali che, per $n\ge N=\max\{N_L,N_M\}$,

$$
|a_n-L|<\varepsilon,
\qquad
|a_n-M|<\varepsilon.
$$

Per la disuguaglianza triangolare,

$$
|L-M|
\le|L-a_n|+|a_n-M|
<2\varepsilon
=\frac23|L-M|,
$$

contraddizione. Quindi $L=M$. $\square$

---

### **7. Limiti infiniti**

Si dice

$$
a_n\to+\infty
$$

se

$$
\forall M\in\mathbb R\ \exists N\ \forall n\ge N: a_n>M.
$$

Analogamente,

$$
a_n\to-\infty
$$

se

$$
\forall M\in\mathbb R\ \exists N\ \forall n\ge N: a_n<M.
$$

I simboli $\pm\infty$ non sono reali. Quando li si aggiunge si lavora in

$$
\overline{\mathbb R}=\mathbb R\cup\{-\infty,+\infty\}.
$$

Una successione può convergere a un reale, divergere a $+\infty$ o $-\infty$, oscillare limitatamente oppure non avere limite neppure esteso, come $(-1)^nn$.

---

### **8. Termini iniziali e traslazione dell'indice**

Cambiare, eliminare o aggiungere un numero finito di termini non modifica il limite. Infatti, data una soglia per una successione, basta prendere il massimo con l'ultimo indice modificato.

Anche $(a_{n+p})$ ha lo stesso limite di $(a_n)$ per ogni $p$ fissato.

---

### **9. Errori frequenti**

1. Scrivere $\exists N\ \forall\varepsilon$ invertendo i quantificatori.
2. Scegliere $N$ dipendente dal successivo indice $n$.
3. Confondere l'indice $N$ con il termine $a_N$.
4. Verificare la disuguaglianza per un solo $n$.
5. Chiamare $+\infty$ numero reale.
6. Usare "diverge" soltanto per i limiti infiniti.

---

### **10. Esercizi**

1. Dimostra dalla definizione $3/(n+1)\to0$ con una soglia esplicita.
2. Verifica $(2n-1)/(3n+4)\to2/3$ senza usare l'algebra dei limiti.
3. Scrivi la negazione di $a_n\to+\infty$.
4. Dimostra formalmente che $n^2\to+\infty$.
5. Mostra che $(-1)^n$ non converge usando la negazione e usando le sottosuccessioni.
6. Dimostra che una successione definitivamente uguale a $L$ converge a $L$.
7. Prova che modificare un numero finito di termini non altera il limite.
8. Trova un $N(\varepsilon)$ per $|n/(n+1)-1|<\varepsilon$.

---

### **11. Riepilogo**

> ✅ La definizione $\varepsilon$-$N$ controlla tutta una coda; la sua negazione fissa una precisione violata arbitrariamente avanti e l'unicità deriva dalla disuguaglianza triangolare.
