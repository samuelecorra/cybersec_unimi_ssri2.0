# **M4 UD2 Lezione 2 - Formula di De Moivre**

### **1. Enunciato**

Per ogni $\theta\in\mathbb R$ e ogni $n\in\mathbb Z$,

$$
(\cos\theta+i\sin\theta)^n
=
\cos(n\theta)+i\sin(n\theta).
$$

Se

$$
z=\rho(\cos\theta+i\sin\theta)\ne0,
$$

allora, per ogni $n\in\mathbb Z$,

$$
z^n=\rho^n[\cos(n\theta)+i\sin(n\theta)].
$$

Per $n\in\mathbb N$ la seconda formula vale anche per $z=0$, con la consueta eccezione $0^0$, che non viene definita qui.

> 📌 Elevare a una potenza intera significa elevare il modulo e moltiplicare l'argomento, sempre interpretato modulo $2\pi$.

---

### **2. Dimostrazione completa**

#### **2.1 Esponenti naturali positivi**

Procediamo per induzione su $n\ge1$.

Per $n=1$ la formula è immediata. Supponiamo che valga per un certo $n$:

$$
(\cos\theta+i\sin\theta)^n=\cos(n\theta)+i\sin(n\theta).
$$

Moltiplicando per $\cos\theta+i\sin\theta$ e usando la formula del prodotto polare,

$$
(\cos\theta+i\sin\theta)^{n+1}
=
\cos((n+1)\theta)+i\sin((n+1)\theta).
$$

La formula vale dunque per ogni $n\ge1$.

#### **2.2 Esponente zero**

Per $n=0$,

$$
(\cos\theta+i\sin\theta)^0=1
$$

e

$$
\cos0+i\sin0=1.
$$

#### **2.3 Esponenti negativi**

Sia $n=-m$ con $m>0$. Poiché il numero $\cos\theta+i\sin\theta$ ha modulo $1$, il suo inverso è

$$
\cos(-\theta)+i\sin(-\theta).
$$

Quindi

$$
(\cos\theta+i\sin\theta)^{-m}
=
(\cos(-\theta)+i\sin(-\theta))^m.
$$

Applicando il caso positivo,

$$
=\cos(-m\theta)+i\sin(-m\theta)
=\cos(n\theta)+i\sin(n\theta).
$$

La dimostrazione copre tutti gli interi. $\square$

---

### **3. Indipendenza dalla scelta dell'argomento**

Se sostituiamo $\theta$ con $\theta+2k\pi$, otteniamo

$$
n(\theta+2k\pi)=n\theta+2nk\pi.
$$

Poiché seno e coseno hanno periodo $2\pi$, il risultato non cambia. La formula è quindi ben definita anche se l'argomento di $z$ non è unico.

> ⚠️ Questa indipendenza usa in modo essenziale che $n$ sia intero. Per esponenti non interi le potenze complesse diventano in generale multivalore.

---

### **4. Calcolo di potenze**

#### **4.1 Esempio con conversione iniziale**

Calcoliamo $(1-i)^8$. Poiché

$$
1-i=\sqrt2\left(\cos\left(-\frac\pi4\right)+i\sin\left(-\frac\pi4\right)\right),
$$

si ha

$$
(1-i)^8=(\sqrt2)^8[\cos(-2\pi)+i\sin(-2\pi)]=16.
$$

#### **4.2 Esponente negativo**

Per

$$
z=2\left(\cos\frac\pi3+i\sin\frac\pi3\right),
$$

si ottiene

$$
z^{-3}=2^{-3}[\cos(-\pi)+i\sin(-\pi)]=-\frac18.
$$

#### **4.3 Potenza molto alta**

Se $u=\cos(2\pi/7)+i\sin(2\pi/7)$, allora

$$
u^{2026}=\cos\frac{4052\pi}{7}+i\sin\frac{4052\pi}{7}.
$$

Poiché $2026\equiv3\pmod7$,

$$
u^{2026}=u^3=\cos\frac{6\pi}{7}+i\sin\frac{6\pi}{7}.
$$

---

### **5. Derivazione di identità trigonometriche**

#### **5.1 Angolo triplo**

Da De Moivre,

$$
(\cos x+i\sin x)^3=\cos3x+i\sin3x.
$$

Sviluppando con il binomio,

$$
\cos^3x+3i\cos^2x\sin x-3\cos x\sin^2x-i\sin^3x.
$$

Uguagliando le parti reali,

$$
\cos3x=\cos^3x-3\cos x\sin^2x=4\cos^3x-3\cos x.
$$

Uguagliando le parti immaginarie,

$$
\sin3x=3\cos^2x\sin x-\sin^3x=3\sin x-4\sin^3x.
$$

#### **5.2 Formula generale tramite binomio**

Per $n\in\mathbb N$,

$$
\cos(nx)+i\sin(nx)
=
\sum_{k=0}^n\binom nk\cos^{n-k}x\,(i\sin x)^k.
$$

I termini con $k$ pari contribuiscono alla parte reale, quelli con $k$ dispari alla parte immaginaria. Questo fornisce espressioni polinomiali di $\cos(nx)$ e $\sin(nx)$ in seno e coseno di $x$.

---

### **6. Equazioni della forma z^n=w: anticipazione**

Se

$$
z^n=w=\rho(\cos\theta+i\sin\theta),
$$

De Moivre impone

$$
|z|^n=\rho
$$

e

$$
n\arg z\equiv\theta\pmod{2\pi}.
$$

Dividere semplicemente $\theta$ per $n$ produce una sola soluzione e perde le altre. In UD3 si dimostrerà che, per $w\ne0$, le soluzioni distinte sono esattamente $n$.

---

### **7. Periodicità delle orbite di potenze**

Sia $u$ sulla circonferenza unitaria:

$$
u=\cos\theta+i\sin\theta.
$$

La successione delle potenze è

$$
u^n=\cos(n\theta)+i\sin(n\theta).
$$

- Se $\theta/(2\pi)\in\mathbb Q$, le potenze sono periodiche e assumono un numero finito di valori.
- Se $\theta/(2\pi)\notin\mathbb Q$, non si ripetono; la loro densità sulla circonferenza è un risultato ulteriore, non necessario in questo modulo.

<!-- TODO FIGURA: due circonferenze unitarie con orbita di u^n, a sinistra caso theta=2pi/5 con pentagono periodico, a destra primi punti di un angolo irrazionale senza chiusura anticipata; obiettivo didattico: collegare De Moivre, periodicità e radici dell'unità. -->

Questa osservazione è utile anche in informatica: rotazioni discrete, trasformata discreta di Fourier e codifica di fasi impiegano potenze di numeri di modulo $1$.

---

### **8. Errori frequenti**

1. Scrivere

   $$
   (\cos\theta+i\sin\theta)^n=\cos^n\theta+i\sin^n\theta.
   $$

2. Dimenticare di elevare anche il modulo.
3. Applicare la formula con esponente negativo a $z=0$.
4. Usare la dimostrazione per moltiplicazione ripetuta senza trattare $n=0$ e $n<0$.
5. Dividere l'argomento per $n$ e dichiarare unica una radice.
6. Ridurre l'esponente modulo $n$ senza aver verificato che la base soddisfi $u^n=1$.

---

### **9. Esercizi**

1. Calcola $(\sqrt3+i)^{12}$ e $(1+i)^{-4}$.
2. Determina la parte reale e immaginaria di $(\cos x+i\sin x)^5$.
3. Ricava con De Moivre le formule di $\cos4x$ e $\sin4x$.
4. Dimostra direttamente che la formula per $z^n$ è indipendente dalla scelta di $\theta$.
5. Sia $u=\cos(2\pi/9)+i\sin(2\pi/9)$. Riduci $u^{1000}$ a una delle potenze $u^0,\ldots,u^8$.
6. Trova tutti gli interi $n$ per cui $i^n=-i$.
7. Mostra che, se $|z|=1$, allora $\overline{z^n}=z^{-n}$ per ogni $n\in\mathbb Z$.
8. Individua l'errore nel ragionamento: "da $z^6=1$ segue $z=1$ prendendo la radice sesta".

---

### **10. Riepilogo**

> ✅ De Moivre è dimostrata per ogni esponente intero: gestisce potenze positive, zero e negative e rende trasparente l'effetto su modulo e argomento.

$$
z^n=|z|^n[\cos(n\theta)+i\sin(n\theta)].
$$

La formula prepara radici, radici dell'unità ed equazioni polinomiali, ma non autorizza a trattare gli esponenti non interi come se l'argomento fosse univoco.
