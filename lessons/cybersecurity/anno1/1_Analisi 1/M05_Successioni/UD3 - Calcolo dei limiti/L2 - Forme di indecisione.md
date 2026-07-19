# **M5 UD3 Lezione 2 - Forme indeterminate e limiti infiniti**

### **1. Che cosa significa indeterminata**

Le scritture

$$
\frac00,\quad \frac\infty\infty,\quad
0\cdot\infty,\quad \infty-\infty,
$$

$$
1^\infty,\quad0^0,\quad\infty^0
$$

non sono numeri né risultati: indicano che i soli limiti dei componenti non determinano il limite dell'espressione.

> 📌 Una forma indeterminata richiede informazioni sulle velocità relative, non una tabella mnemonica.

---

### **2. La stessa forma, esiti diversi**

Per $0/0$:

$$
\frac{1/(n+1)}{1/(n+1)}=1,
$$

$$
\frac{1/(n+1)^2}{1/(n+1)}\to0,
$$

$$
\frac{1/(n+1)}{1/(n+1)^2}=n+1\to+\infty.
$$

Per $\infty/\infty$:

$$
\frac n n=1,
\qquad
\frac n{n^2}\to0,
\qquad
\frac{n^2}{n}\to+\infty.
$$

Per $\infty-\infty$:

$$
(n+1)-n=1,
$$

$$
2n-n=n\to+\infty,
$$

$$
n-(n+(-1)^n)=-(-1)^n
$$

non converge.

---

### **3. Tecniche algebriche**

#### **3.1 Termine dominante**

Per rapporti di polinomi si divide per la potenza massima di $n$:

$$
\frac{3n^2+1}{2n^2-5n}
=
\frac{3+1/n^2}{2-5/n}\to\frac32.
$$

#### **3.2 Razionalizzazione**

$$
\sqrt{n^2+n}-n
=
\frac{n}{\sqrt{n^2+n}+n}
=
\frac1{\sqrt{1+1/n}+1}\to\frac12.
$$

#### **3.3 Fattorizzazione**

$$
\frac{n^3-n}{n^2-1}=n
$$

per $n\ge2$, dopo aver fattorizzato; il limite è $+\infty$.

---

### **4. Operazioni determinate con infiniti**

Sotto ipotesi esplicite:

- se $a_n\to+\infty$ e $b_n\to b\in\mathbb R$, allora $a_n+b_n\to+\infty$;
- se $a_n\to+\infty$ e $b_n\ge c>0$ definitivamente, allora $a_nb_n\to+\infty$;
- se $a_n>0$ e $a_n\to+\infty$, allora $1/a_n\to0^+$;
- se $a_n>0$ e $a_n\to0$, allora $1/a_n\to+\infty$.

Per esempio, nell'ultimo caso, dato $M>0$ scegliamo una soglia con

$$
0<a_n<\frac1M;
$$

allora $1/a_n>M$.

Senza segno il reciproco può non avere limite: $a_n=(-1)^n/(n+1)\to0$, ma $1/a_n=(-1)^n(n+1)$ oscilla senza limite esteso.

---

### **5. Prodotti e differenze indeterminate**

Un prodotto $0\cdot\infty$ si trasforma in quoziente. Per esempio,

$$
(n+1)\sin\frac1{n+1}
=
\frac{\sin(1/(n+1))}{1/(n+1)}\to1,
$$

usando il limite trigonometrico sequenziale dimostrato nella lezione successiva.

Le differenze di radicali si razionalizzano; differenze di quantità equivalenti richiedono termini di ordine successivo e non autorizzano sostituzioni dirette.

---

### **6. Forme esponenziali**

Per una potenza $u_n^{v_n}$ con $u_n>0$, la trasformazione rigorosa è

$$
u_n^{v_n}=\exp(v_n\ln u_n).
$$

Le forme $1^\infty$, $0^0$ e $\infty^0$ dipendono quindi dal limite del prodotto $v_n\ln u_n$. L'uso generale di logaritmo, esponenziale e continuità è anticipato; i casi fondamentali basati sulla successione che definisce $e$ sono dimostrati in UD4.

Esempi della forma $1^\infty$:

$$
\left(1+\frac1n\right)^n\to e,
$$

$$
\left(1+\frac1n\right)^{\sqrt n}\to1,
$$

$$
\left(1+\frac1n\right)^{n^2}\to+\infty.
$$

---

### **7. Nessun uso di l'Hopital**

Il teorema di l'Hôpital riguarda limiti di funzioni e derivate ed è collocato nei moduli successivi. Per successioni si usano fattorizzazione, razionalizzazione, confronto, equivalenze giustificate, Bernoulli, Stolz-Cesàro e limiti notevoli già dimostrati.

---

### **8. Errori frequenti**

1. Scrivere $0/0=0$ o $\infty/\infty=1$.
2. Cancellare termini in una somma invece che fattori.
3. Dividere per $n$ senza controllarne positività, qui garantita definitivamente.
4. Dimenticare il segno nelle operazioni con $\infty$.
5. Usare equivalenti dentro una differenza con cancellazione.
6. Applicare l'Hôpital a una successione senza costruire e giustificare un'estensione funzionale.

---

### **9. Esercizi**

1. Fornisci tre esiti diversi per la forma $0\cdot\infty$.
2. Calcola $\sqrt{n^2+3n}-n$.
3. Studia $(2n^3-n)/(n^2+4)$, incluso il segno.
4. Trasforma $(1+2/n)^{3n}$ mediante logaritmi, dichiarando il risultato notevole usato.
5. Mostra che $a_n\to0$ non implica $1/a_n\to+\infty$.
6. Costruisci una forma $\infty-\infty$ con limite $-3$.
7. Spiega perché sostituire $\sqrt{n^2+n}\sim n$ nella differenza $\sqrt{n^2+n}-n$ perde il termine principale.
8. Classifica come determinate o indeterminate dieci combinazioni assegnate di limiti finiti e infiniti.

---

### **10. Riepilogo**

> ✅ Le forme indeterminate classificano insufficienza di informazione; ogni trasformazione deve preservare dominio, segni e uguaglianza della successione.
