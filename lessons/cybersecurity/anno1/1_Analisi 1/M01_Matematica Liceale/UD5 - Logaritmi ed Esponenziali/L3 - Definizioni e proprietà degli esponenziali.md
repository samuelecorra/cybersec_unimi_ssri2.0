# **M1 UD5 Lezione 3 - Definizioni e proprietà degli esponenziali**

### **1. Introduzione**

Una funzione esponenziale è una funzione in cui la variabile compare all’esponente:

$$
f(x)=a^x
$$

con:

$$
a>0,\quad a\neq 1
$$

Le funzioni esponenziali descrivono fenomeni in cui la variazione è proporzionale alla quantità presente: crescita di popolazioni, decadimento radioattivo, capitalizzazione composta, diffusione di segnali, complessità algoritmica.

In Analisi 1 sono fondamentali perché:

- sono sempre positive;
- sono monotone;
- hanno crescita molto diversa da quella polinomiale;
- sono invertite dai logaritmi.

---

### **2. Definizione di funzione esponenziale**

Sia:

$$
a>0,\quad a\neq 1
$$

La funzione:

$$
f:\mathbb{R}\to(0,+\infty)
$$

definita da:

$$
f(x)=a^x
$$

si chiama **funzione esponenziale di base $a$**.

In questo modulo preliminare l'esponenziale reale è assunto come funzione elementare che estende con continuità le potenze razionali. La costruzione rigorosa mediante completezza dei reali e successioni, insieme alla dimostrazione delle proprietà di continuità, appartiene ai moduli successivi. Questa distinzione evita di usare implicitamente risultati non ancora dimostrati.

Il dominio è:

$$
\mathbb{R}
$$

L’immagine è:

$$
(0,+\infty)
$$

Infatti:

$$
a^x>0
$$

per ogni $x\in\mathbb{R}$.

> 📌 Una funzione esponenziale reale non assume mai valore zero e non assume mai valori negativi.

---

### **3. Perché si richiede $a>0$ e $a\neq 1$**

La base deve essere positiva perché vogliamo che:

$$
a^x
$$

sia definita per ogni esponente reale $x$.

Se $a<0$, espressioni come:

$$
a^{1/2}
$$

non sono reali.

La base non deve essere $1$ perché:

$$
1^x=1
$$

per ogni $x$, quindi non si avrebbe una vera crescita né una vera decrescita. Inoltre la funzione non sarebbe invertibile.

---

### **4. Proprietà delle potenze reali**

Per:

$$
a>0,\quad x,y\in\mathbb{R}
$$

valgono:

#### **4.1 Prodotto**

$$
a^x\cdot a^y=a^{x+y}
$$

#### **4.2 Quoziente**

$$
\frac{a^x}{a^y}=a^{x-y}
$$

#### **4.3 Potenza di potenza**

$$
(a^x)^y=a^{xy}
$$

#### **4.4 Prodotto di basi**

Se $a>0$ e $b>0$:

$$
a^x b^x=(ab)^x
$$

#### **4.5 Quoziente di basi**

Se $a>0$ e $b>0$:

$$
\frac{a^x}{b^x}=\left(\frac{a}{b}\right)^x
$$

Queste proprietà permettono di semplificare equazioni e disequazioni esponenziali.

---

### **5. Valori notevoli**

Per ogni $a>0$:

$$
a^0=1
$$

e:

$$
a^1=a
$$

Inoltre:

$$
a^{-x}=\frac{1}{a^x}
$$

Per esempio:

$$
2^{-3}=\frac{1}{2^3}=\frac{1}{8}
$$

Questo spiega perché, quando $a>1$, l’esponenziale tende a $0$ andando verso $-\infty$.

---

### **6. Monotonia**

La monotonia dipende dalla base.

#### **6.1 Base maggiore di 1**

Se:

$$
a>1
$$

allora:

$$
f(x)=a^x
$$

è strettamente crescente.

Quindi:

$$
x_1<x_2 \Rightarrow a^{x_1}<a^{x_2}
$$

#### **6.2 Base compresa tra 0 e 1**

Se:

$$
0<a<1
$$

allora:

$$
f(x)=a^x
$$

è strettamente decrescente.

Quindi:

$$
x_1<x_2 \Rightarrow a^{x_1}>a^{x_2}
$$

Questo fatto è essenziale nelle disequazioni esponenziali.

---

### **7. Collegamento con i logaritmi**

Il logaritmo in base $a$ è la funzione inversa dell’esponenziale in base $a$.

Per:

$$
a>0,\quad a\neq 1
$$

valgono:

$$
\log_a(a^x)=x
$$

per ogni $x\in\mathbb{R}$, e:

$$
a^{\log_a x}=x
$$

per ogni $x>0$.

Quindi:

$$
a^x=b
$$

è equivalente a:

$$
x=\log_a b
$$

con:

$$
b>0
$$

---

### **8. Il numero $e$**

In Analisi compare una base privilegiata:

$$
e
$$

Il numero $e$ è un numero reale irrazionale, approssimativamente:

$$
e\approx 2.71828
$$

La funzione:

$$
e^x
$$

si chiama **esponenziale naturale**.

Il suo inverso è il logaritmo naturale:

$$
\ln x
$$

cioè:

$$
\ln x=\log_e x
$$

Il motivo profondo dell’importanza di $e$ sarà chiarito con derivate e integrali; per ora basta sapere che è la base standard dell’Analisi.

---

### **9. Grafico qualitativo**

Se $a>1$, il grafico di $a^x$:

- passa per $(0,1)$;
- passa per $(1,a)$;
- è sempre sopra l’asse $x$;
- cresce al crescere di $x$;
- si avvicina all’asse $x$ per $x\to-\infty$.

Se $0<a<1$, il grafico:

- passa ancora per $(0,1)$;
- è sempre sopra l’asse $x$;
- decresce al crescere di $x$;
- si avvicina all’asse $x$ per $x\to+\infty$.

<!-- TODO FIGURA:
Grafici sullo stesso piano di y=2^x e y=(1/2)^x. Evidenziare il punto comune (0,1),
la positività, la monotonia opposta, la simmetria rispetto all'asse y e l'asintoto y=0.
Obiettivo didattico: confrontare basi maggiori di 1 e comprese tra 0 e 1.
-->

L’asse $x$ è un asintoto orizzontale intuitivo, perché l’esponenziale si avvicina a $0$ senza mai raggiungerlo.

---

### **10. Comportamento agli estremi**

Se $a>1$:

$$
\lim_{x\to+\infty}a^x=+\infty
$$

e:

$$
\lim_{x\to-\infty}a^x=0
$$

Se $0<a<1$:

$$
\lim_{x\to+\infty}a^x=0
$$

e:

$$
\lim_{x\to-\infty}a^x=+\infty
$$

Queste formule saranno dimostrate nel modulo sui limiti; qui servono come guida qualitativa.

---

### **11. Confronto tra basi reciproche**

Se:

$$
0<a<1
$$

allora possiamo scrivere:

$$
a=\frac{1}{b}
$$

con:

$$
b>1
$$

Quindi:

$$
a^x=\left(\frac{1}{b}\right)^x=b^{-x}
$$

Questo mostra che un esponenziale con base tra $0$ e $1$ è una crescita con l’esponente cambiato di segno.

Esempio:

$$
\left(\frac{1}{2}\right)^x=2^{-x}
$$

---

### **12. Esempi di semplificazione**

#### **12.1 Stessa base**

Semplifichiamo:

$$
2^{x+3}\cdot 2^{2x-1}
$$

Usiamo il prodotto:

$$
2^{x+3}\cdot 2^{2x-1}=2^{x+3+2x-1}
$$

quindi:

$$
2^{3x+2}
$$

#### **12.2 Quoziente**

Semplifichiamo:

$$
\frac{5^{2x}}{5^{x-4}}
$$

Otteniamo:

$$
5^{2x-(x-4)}=5^{x+4}
$$

#### **12.3 Basi diverse ma collegabili**

Semplifichiamo:

$$
4^x\cdot 2^{x+1}
$$

Poiché:

$$
4^x=(2^2)^x=2^{2x}
$$

si ha:

$$
4^x\cdot 2^{x+1}=2^{2x}\cdot 2^{x+1}=2^{3x+1}
$$

---

### **13. Errori tipici**

#### **13.1 Distribuire l’esponente su una somma**

In generale:

$$
a^{x+y}=a^x\cdot a^y
$$

ma:

$$
a^{x+y}\neq a^x+a^y
$$

#### **13.2 Confondere $a^{xy}$ con $a^x a^y$**

Vale:

$$
a^x a^y=a^{x+y}
$$

non:

$$
a^{xy}
$$

#### **13.3 Dimenticare che l’esponenziale è sempre positivo**

L’equazione:

$$
2^x=-5
$$

non ha soluzioni reali, perché:

$$
2^x>0
$$

per ogni $x$.

---

### **14. Riepilogo**

> ✅ La funzione esponenziale $a^x$ è definita per ogni $x\in\mathbb{R}$ quando $a>0$ e $a\neq 1$, ed è sempre positiva.

Punti essenziali:

- dominio: $\mathbb{R}$;
- immagine: $(0,+\infty)$;
- passa sempre per $(0,1)$;
- cresce se $a>1$;
- decresce se $0<a<1$;
- è invertita dal logaritmo $\log_a x$;
- le proprietà delle potenze sono lo strumento operativo per semplificare e risolvere esercizi.

La prossima lezione applica queste idee a equazioni e disequazioni esponenziali.
