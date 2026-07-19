# **M5 UD2 Lezione 2 - Teoremi fondamentali sui limiti**

### **1. Convergenza implica limitatezza**

> **Teorema.** Ogni successione reale convergente è limitata.

**Dimostrazione.** Se $a_n\to L$, con $\varepsilon=1$ esiste $N$ tale che per $n\ge N$

$$
|a_n-L|<1.
$$

Quindi, per la coda,

$$
|a_n|\le|a_n-L|+|L|<1+|L|.
$$

Poniamo

$$
C=\max\{|a_0|,\ldots,|a_{N-1}|,1+|L|\}.
$$

Allora $|a_n|\le C$ per ogni $n$. $\square$

Il converso è falso: $(-1)^n$ è limitata ma non converge.

---

### **2. Permanenza del segno e separazione**

Se $a_n\to L>c$, allora $a_n>c$ definitivamente.

**Dimostrazione.** Scegliamo

$$
\varepsilon=\frac{L-c}{2}.
$$

Definitivamente $|a_n-L|<\varepsilon$, dunque

$$
a_n>L-\varepsilon=\frac{L+c}{2}>c.
$$

Analogamente, $L<c$ implica $a_n<c$ definitivamente. In particolare:

- $L>0$ implica positività definitiva;
- $L<0$ implica negatività definitiva;
- $L\ne0$ implica $|a_n|\ge|L|/2$ definitivamente.

Se $L=0$, il segno non è determinato: $(-1)^n/(n+1)\to0$.

---

### **3. Teorema del confronto**

> **Teorema.** Se $a_n\le b_n$ definitivamente, $a_n\to a$ e $b_n\to b$, allora $a\le b$.

**Dimostrazione.** Se per assurdo $a>b$, poniamo $\varepsilon=(a-b)/3$. Definitivamente


$$
a_n>a-\varepsilon,
\qquad
b_n<b+\varepsilon.
$$

Poiché $a-\varepsilon>b+\varepsilon$, si avrebbe $a_n>b_n$, contro l'ipotesi definitiva. $\square$

Da $a_n<b_n$ non segue $a<b$: per $a_n=0$, $b_n=1/(n+1)$ entrambi i limiti sono $0$.

---

### **4. Teorema dei carabinieri**

> **Teorema.** Se definitivamente $a_n\le b_n\le c_n$ e $a_n,c_n\to L$, allora $b_n\to L$.

**Dimostrazione.** Dato $\varepsilon>0$, esiste una soglia comune oltre la quale

$$
L-\varepsilon<a_n\le b_n\le c_n<L+\varepsilon.
$$

Pertanto $|b_n-L|<\varepsilon$ definitivamente. $\square$

Equivalentemente, se $|b_n-L|\le u_n$ definitivamente e $u_n\to0$, allora $b_n\to L$.

Esempi:

$$
\left|\frac{\sin n}{n+1}\right|\le\frac1{n+1}\to0,
$$

$$
\left|\frac{(-1)^n\ln(n+1)}{n+1}\right|
\le\frac{\ln(n+1)}{n+1}\to0,
$$

con l'ultimo limite dimostrato nella sezione sui confronti di crescita.

---

### **5. Convergenza monotona**

> **Teorema.** Una successione crescente e limitata superiormente converge al supremo della propria immagine. Una decrescente e limitata inferiormente converge all'infimo.

**Dimostrazione del caso crescente.** Sia

$$
L=\sup\{a_n:n\in\mathbb N\}.
$$

Dato $\varepsilon>0$, $L-\varepsilon$ non è un maggiorante, altrimenti contraddirebbe la minimalità di $L$. Esiste quindi $N$ con

$$
a_N>L-\varepsilon.
$$

Per monotonia, se $n\ge N$,

$$
L-\varepsilon<a_N\le a_n\le L<L+\varepsilon.
$$

Quindi $|a_n-L|<\varepsilon$ e $a_n\to L$. Il caso decrescente si ottiene con l'infimo. $\square$

La completezza di $\mathbb R$, tramite l'esistenza del supremo, è essenziale.

<!-- TODO FIGURA: successione crescente limitata con punti sotto L=sup A, fascia L-epsilon e primo termine a_N sopra L-epsilon, tutti i successivi intrappolati; obiettivo didattico: visualizzare la prova del teorema di convergenza monotona. -->

---

### **6. Monotone non limitate**

> **Teorema.** Una successione crescente non limitata superiormente tende a $+\infty$.

**Dimostrazione.** Dato $M\in\mathbb R$, la non limitatezza fornisce un indice $N$ con $a_N>M$. Per monotonia, $a_n\ge a_N>M$ per ogni $n\ge N$. Questa è la definizione di limite $+\infty$. $\square$

Analogamente, una successione decrescente non limitata inferiormente tende a $-\infty$.

<!-- TODO FIGURA: successione crescente non limitata, quota arbitraria M, primo indice N che la supera e coda interamente sopra M; obiettivo didattico: tradurre non limitatezza più monotonia nella definizione di +infinito. -->

---

### **7. Confronti con limiti infiniti**

Se $a_n\le b_n$ definitivamente e $a_n\to+\infty$, allora anche $b_n\to+\infty$. Se $a_n\le b_n$ e $b_n\to-\infty$, allora $a_n\to-\infty$.

Le prove seguono direttamente dalle definizioni usando la stessa soglia.

---

### **8. Errori frequenti**

1. Omettere i termini iniziali nella prova della limitatezza.
2. Deducere $a<b$ da disuguaglianze strette tra i termini.
3. Usare permanenza del segno quando il limite è $0$.
4. Applicare i carabinieri con limiti esterni diversi.
5. Usare il teorema delle monotone senza limitatezza.
6. Citare il supremo senza la completezza dei reali.

---

### **9. Esercizi**

1. Riscrivi con tutti i quantificatori la permanenza di $a_n>c$.
2. Dimostra il caso decrescente del teorema di convergenza monotona.
3. Mostra con i carabinieri che $\cos(n^2)/(n+1)^2\to0$.
4. Prova che se $a_n\to L$ e $a_n\ge0$ definitivamente, allora $L\ge0$.
5. Costruisci successioni strettamente ordinate con lo stesso limite.
6. Dimostra i due confronti con limiti infiniti della sezione 7.
7. Studia la convergenza di $a_n=\sum_{k=1}^n2^{-k}$ usando monotonia e limitatezza, senza usare ancora la teoria delle serie.
8. Spiega dove fallisce il teorema delle monotone in $\mathbb Q$ per approssimazioni crescenti di $\sqrt2$.

---

### **10. Riepilogo**

> ✅ Limitatezza delle convergenti, permanenza, confronto, carabinieri e convergenza monotona derivano da quantificatori, ordine e completezza; ciascun teorema richiede tutte le proprie ipotesi.
