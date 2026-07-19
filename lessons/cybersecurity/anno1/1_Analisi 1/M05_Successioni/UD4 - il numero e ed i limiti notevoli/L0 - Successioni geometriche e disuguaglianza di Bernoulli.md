# **M5 UD4 Lezione 0 - Successioni geometriche e disuguaglianza di Bernoulli**

### **1. Disuguaglianza di Bernoulli**

> **Teorema.** Per $x\ge-1$ e $n\in\mathbb N$,

$$
(1+x)^n\ge1+nx.
$$

**Dimostrazione per induzione.** Per $n=0$ entrambi i membri valgono $1$. Se la tesi vale per $n$, poiché $1+x\ge0$,

$$
(1+x)^{n+1}
\ge(1+nx)(1+x)
=1+(n+1)x+nx^2
\ge1+(n+1)x.
$$

Quindi vale per ogni $n$. $\square$

Se $x>-1$, $x\ne0$ e $n\ge2$, l'ultimo termine $nx^2$ rende stretta la disuguaglianza al passo opportuno.

> 📌 Il principio di induzione è richiamato da M01; qui viene applicato a una stima analitica.

---

### **2. Basi maggiori di 1**

Sia $q>1$ e scriviamo $q=1+h$ con $h>0$. Bernoulli dà

$$
q^n=(1+h)^n\ge1+nh.
$$

Dato $M\in\mathbb R$, scegliendo $n>(M-1)/h$ si ottiene $q^n>M$. Quindi

$$
q^n\to+\infty.
$$

---

### **3. Basi di modulo minore di 1**

Se $0<q<1$, poniamo $r=1/q>1$. Poiché $r^n\to+\infty$ e resta positivo,

$$
q^n=\frac1{r^n}\to0.
$$

Se $-1<q<0$,

$$
|q^n|=|q|^n\to0,
$$

quindi $q^n\to0$ per i carabinieri, pur alternando segno.

---

### **4. Classificazione completa di q^n**

Con $n\in\mathbb N$ e la convenzione $q^0=1$:

| Base | Comportamento |
|---|---|
| $q>1$ | $q^n\to+\infty$ |
| $q=1$ | costante $1$ |
| $0<q<1$ | tende a $0^+$ |
| $q=0$ | $1,0,0,\ldots$, tende a $0$ |
| $-1<q<0$ | tende a $0$ alternando segno |
| $q=-1$ | oscilla tra $\pm1$ |
| $q<-1$ | oscilla con modulo verso $+\infty$, senza limite esteso |

Per $a_n=a_0q^n$, il caso $a_0=0$ produce sempre la successione nulla; altrimenti il segno di $a_0$ modifica soltanto orientamento e segno.

---

### **5. Somme geometriche finite**

Per $q\ne1$,

$$
1+q+\cdots+q^n=\frac{1-q^{n+1}}{1-q}.
$$

Se $|q|<1$, la successione delle somme parziali converge a

$$
\frac1{1-q}.
$$

Questo è un risultato su una successione di somme finite. In M06 verrà reinterpretato come convergenza della serie geometrica.

---

### **6. Conseguenze di Bernoulli**

Per $\alpha>0$ e $n\ge1$,

$$
\left(1+\frac\alpha n\right)^n\ge1+\alpha.
$$

Se $h_n\ge0$ e $(1+h_n)^n\le C$, Bernoulli implica

$$
0\le h_n\le\frac{C-1}{n},
$$

stima utile per radici $n$-esime.

Bernoulli mostra anche che potenze con base appena superiore a $1$ possono avere crescita non banale quando l'esponente cresce insieme all'indice.

---

### **7. Errori frequenti**

1. Applicare Bernoulli con $x<-1$ senza controllare segni.
2. Dire $q^n\to+\infty$ per $q<-1$ ignorando l'oscillazione.
3. Confondere $q=-1$ con $|q|<1$.
4. Usare il rapporto geometrico senza separare $a_0=0$.
5. Chiamare serie la somma finita prima di M06.

---

### **8. Esercizi**

1. Dimostra la forma stretta di Bernoulli.
2. Trova una soglia esplicita per $1.01^n>M$.
3. Classifica $(-3/2)^n$, $(-2/3)^n$ e $(-1)^n$.
4. Calcola il limite delle somme $\sum_{k=0}^n(-1/2)^k$.
5. Usa Bernoulli per dimostrare nuovamente $\sqrt[n]{5}\to1$.
6. Mostra che $nq^n\to0$ per $|q|<1$ usando un confronto o il rapporto.
7. Determina limsup e liminf di $q^n$ in tutti i casi reali.

---

### **9. Riepilogo**

> ✅ Bernoulli fornisce una crescita lineare minima per $(1+x)^n$ e permette una classificazione rigorosa di tutte le successioni geometriche reali.
