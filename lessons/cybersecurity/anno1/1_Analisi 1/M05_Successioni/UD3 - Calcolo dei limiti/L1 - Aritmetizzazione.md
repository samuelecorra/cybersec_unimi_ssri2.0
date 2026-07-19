# **M5 UD3 Lezione 1 - Algebra dei limiti**

### **1. Somma, differenza e scalare**

Siano $a_n\to a$ e $b_n\to b$.

Per la somma,

$$
|(a_n+b_n)-(a+b)|
\le|a_n-a|+|b_n-b|.
$$

Dato $\varepsilon>0$, scegliamo una soglia comune oltre la quale ciascun addendo è minore di $\varepsilon/2$. Segue

$$
a_n+b_n\to a+b.
$$

Applicando il risultato a $-b_n$ si ottiene la differenza. Per $\lambda\in\mathbb R$,

$$
|\lambda a_n-\lambda a|=|\lambda|\,|a_n-a|,
$$

quindi $\lambda a_n\to\lambda a$; il caso $\lambda=0$ è immediato.

---

### **2. Prodotto**

> **Teorema.** Se $a_n\to a$ e $b_n\to b$, allora $a_nb_n\to ab$.

**Dimostrazione.** La successione $(a_n)$ è limitata: esiste $C$ con $|a_n|\le C$. Inoltre

$$
|a_nb_n-ab|
=|a_n(b_n-b)+b(a_n-a)|
$$

$$
\le C|b_n-b|+|b|\,|a_n-a|.
$$

Scegliendo soglie che rendano i due termini minori di $\varepsilon/2$ si ottiene la tesi. Se un coefficiente è nullo, la relativa condizione è superflua. $\square$

Per induzione segue l'algebra di ogni prodotto finito e

$$
a_n^k\to a^k,\qquad k\in\mathbb N.
$$

---

### **3. Reciproco e quoziente**

Se $b_n\to b\ne0$, la permanenza del segno fornisce

$$
|b_n|\ge\frac{|b|}{2}
$$

definitivamente. Quindi $b_n\ne0$ e

$$
\left|\frac1{b_n}-\frac1b\right|
=
\frac{|b_n-b|}{|b_n|\,|b|}
\le
\frac{2}{|b|^2}|b_n-b|\to0.
$$

Pertanto

$$
\frac1{b_n}\to\frac1b
$$

e, usando il prodotto,

$$
\frac{a_n}{b_n}\to\frac ab.
$$

> ⚠️ L'ipotesi $b\ne0$ non è decorativa: se $b_n\to0$, segno e velocità determinano comportamenti diversi.

---

### **4. Valore assoluto**

La disuguaglianza triangolare inversa dà

$$
\bigl||a_n|-|a|\bigr|\le|a_n-a|.
$$

Per i carabinieri,

$$
|a_n|\to|a|.
$$

Il converso è falso: $|(-1)^n|=1$ converge, ma $(-1)^n$ no.

---

### **5. Radici**

Se $a_n\ge0$ e $a_n\to a\ge0$, allora

$$
\sqrt{a_n}\to\sqrt a.
$$

Se $a>0$, si usa

$$
|\sqrt{a_n}-\sqrt a|
=
\frac{|a_n-a|}{\sqrt{a_n}+\sqrt a}
\le\frac{|a_n-a|}{\sqrt a}.
$$

Se $a=0$, allora $(\sqrt{a_n})^2=a_n\to0$ e, dato $\varepsilon>0$, definitivamente $a_n<\varepsilon^2$, quindi $\sqrt{a_n}<\varepsilon$.

Per radici di indice pari servono non negatività definitiva e limite non negativo; per indice dispari non vi è restrizione di segno.

---

### **6. Polinomi e razionali**

Da somma e prodotto segue

$$
P(a_n)\to P(a)
$$

per ogni polinomio $P$. Se $Q(a)\ne0$,

$$
\frac{P(a_n)}{Q(a_n)}\to\frac{P(a)}{Q(a)}.
$$

Esempio:

$$
\frac{2+1/(n+1)}{5-3/(n+1)}\to\frac25.
$$

---

### **7. Composizione e continuità**

Il principio

$$
a_n\to a\implies f(a_n)\to f(a)
$$

caratterizza la continuità sequenziale di $f$. In M05 è stato dimostrato direttamente per polinomi, razionali ammesse e radici; il teorema generale e il ponte con i limiti di funzione appartengono a M08.

Non è lecito "passare al limite" attraverso una funzione discontinua. Per esempio $a_n=(-1)^n/(n+1)\to0$, ma

$$
\lfloor a_n\rfloor
$$

oscilla tra $-1$ e $0$.

---

### **8. Successioni complesse**

Le prove di somma e prodotto restano valide in $\mathbb C$ usando il modulo e la sua disuguaglianza triangolare. Per $z_n\to z$ e $w_n\to w$,

$$
z_n+w_n\to z+w,
\qquad
z_nw_n\to zw,
$$

e il quoziente vale se $w\ne0$. Questo richiamo prepara le serie complesse senza ampliare M05 in analisi complessa.

---

### **9. Errori frequenti**

1. Applicare il quoziente quando il limite del denominatore è $0$.
2. Dimenticare la limitatezza nella prova del prodotto.
3. Confondere $|a_n|\to|a|$ con il converso.
4. Estrarre radici pari senza controllare segno e dominio.
5. Usare continuità generale prima di averla dichiarata.

---

### **10. Esercizi**

1. Dimostra la regola dello scalare con una scelta esplicita di $N(\varepsilon)$.
2. Completa la prova del prodotto gestendo $b=0$.
3. Dimostra la convergenza di $(3n^2+n)/(2n^2-1)$ con l'algebra dei limiti.
4. Se $a_n\to2$, calcola il limite di $(a_n^2-1)/(a_n+3)$.
5. Trova un controesempio al quoziente quando $b=0$.
6. Prova il risultato per la radice cubica.
7. Studia $\lfloor1/(n+1)\rfloor$ e $\lfloor-1/(n+1)\rfloor$.
8. Estendi il prodotto a tre successioni complesse.

---

### **11. Riepilogo**

> ✅ Le regole algebriche sono teoremi derivati dalla definizione; prodotto e quoziente richiedono rispettivamente limitatezza e separazione definitiva da zero.
