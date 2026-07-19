# **M1 UD5 Lezione 4 - Equazioni e disequazioni esponenziali**

### **1. Introduzione**

Un’equazione esponenziale è un’equazione in cui l’incognita compare all’esponente.

Esempi:

$$
2^x=16
$$

$$
3^{2x-1}=9^{x+2}
$$

$$
4^x-5\cdot 2^x+4=0
$$

Le disequazioni esponenziali sono analoghe, ma richiedono attenzione alla monotonia della base:

$$
2^x>8
$$

$$
\left(\frac{1}{3}\right)^x\le 9
$$

> 📌 Nelle disequazioni esponenziali il verso dipende dalla base: resta uguale se la base è maggiore di $1$, si inverte se la base è compresa tra $0$ e $1$.

---

### **2. Equazioni con stessa base**

Se:

$$
a>0,\quad a\neq 1
$$

allora la funzione:

$$
x\mapsto a^x
$$

è iniettiva. Quindi:

$$
a^{A(x)}=a^{B(x)}
$$

è equivalente a:

$$
A(x)=B(x)
$$

Questa è la forma più semplice e più importante.

---

### **3. Esempio con stessa base**

Risolviamo:

$$
2^{3x-1}=2^{x+5}
$$

Poiché le basi sono uguali e valide, uguagliamo gli esponenti:

$$
3x-1=x+5
$$

quindi:

$$
2x=6
$$

e:

$$
x=3
$$

La soluzione è:

$$
x=3
$$

---

### **4. Ricondurre alla stessa base**

Spesso le basi non sono uguali, ma possono essere riscritte come potenze della stessa base.

Esempio:

$$
4^{x+1}=8^{x-2}
$$

Riscriviamo:

$$
4=2^2,\quad 8=2^3
$$

Allora:

$$
(2^2)^{x+1}=(2^3)^{x-2}
$$

cioè:

$$
2^{2x+2}=2^{3x-6}
$$

Uguagliamo gli esponenti:

$$
2x+2=3x-6
$$

quindi:

$$
x=8
$$

---

### **5. Equazioni con base reciproca**

Consideriamo:

$$
\left(\frac{1}{2}\right)^{x-1}=4
$$

Riscriviamo tutto in base $2$:

$$
\left(\frac{1}{2}\right)^{x-1}=2^{-(x-1)}
$$

e:

$$
4=2^2
$$

Quindi:

$$
2^{-x+1}=2^2
$$

Uguagliamo gli esponenti:

$$
-x+1=2
$$

da cui:

$$
x=-1
$$

---

### **6. Equazioni risolte con i logaritmi**

Non sempre si riesce a ricondurre tutto alla stessa base.

Per esempio:

$$
3^x=7
$$

non ha una soluzione intera o razionale evidente. Usiamo il logaritmo:

$$
x=\log_3 7
$$

Oppure, con il logaritmo naturale:

$$
x=\frac{\ln 7}{\ln 3}
$$

In generale:

$$
a^{A(x)}=b
$$

con:

$$
a>0,\quad a\neq 1,\quad b>0
$$

è equivalente a:

$$
A(x)=\log_a b
$$

---

### **7. Equazioni esponenziali con sostituzione**

Alcune equazioni contengono più potenze collegate tra loro.

Esempio:

$$
4^x-5\cdot 2^x+4=0
$$

Osserviamo che:

$$
4^x=(2^2)^x=2^{2x}=(2^x)^2
$$

Poniamo:

$$
t=2^x
$$

Poiché:

$$
2^x>0
$$

abbiamo:

$$
t>0
$$

L’equazione diventa:

$$
t^2-5t+4=0
$$

Fattorizziamo:

$$
(t-1)(t-4)=0
$$

Quindi:

$$
t=1\quad \text{oppure}\quad t=4
$$

Torniamo a $x$:

$$
2^x=1 \Rightarrow x=0
$$

$$
2^x=4 \Rightarrow x=2
$$

La soluzione è:

$$
x\in\{0,2\}
$$

---

### **8. Disequazioni con base maggiore di 1**

Se:

$$
a>1
$$

allora $a^x$ è crescente. Quindi:

$$
a^{A(x)}<a^{B(x)}
$$

è equivalente a:

$$
A(x)<B(x)
$$

Il verso resta uguale.

Esempio:

$$
3^{2x-1}\ge 3^{x+4}
$$

Poiché $3>1$:

$$
2x-1\ge x+4
$$

quindi:

$$
x\ge 5
$$

La soluzione è:

$$
[5,+\infty)
$$

---

### **9. Disequazioni con base tra 0 e 1**

Se:

$$
0<a<1
$$

allora $a^x$ è decrescente. Quindi:

$$
a^{A(x)}<a^{B(x)}
$$

è equivalente a:

$$
A(x)>B(x)
$$

Il verso si inverte.

Esempio:

$$
\left(\frac{1}{2}\right)^{x+1}\le \left(\frac{1}{2}\right)^{3x-5}
$$

Poiché:

$$
0<\frac{1}{2}<1
$$

invertiamo il verso:

$$
x+1\ge 3x-5
$$

quindi:

$$
6\ge 2x
$$

e:

$$
x\le 3
$$

La soluzione è:

$$
(-\infty,3]
$$

---

### **10. Disequazioni ricondotte alla stessa base**

Risolviamo:

$$
4^{x-1}<8^{2x}
$$

Riscriviamo in base $2$:

$$
(2^2)^{x-1}<(2^3)^{2x}
$$

quindi:

$$
2^{2x-2}<2^{6x}
$$

La base $2$ è maggiore di $1$, quindi:

$$
2x-2<6x
$$

Portiamo tutto a destra:

$$
-2<4x
$$

e:

$$
x>-\frac{1}{2}
$$

La soluzione è:

$$
\left(-\frac{1}{2},+\infty\right)
$$

---

### **11. Disequazioni con logaritmi come strumento**

Consideriamo:

$$
2^x>5
$$

Poiché $2>1$, possiamo applicare il logaritmo in base $2$:

$$
x>\log_2 5
$$

Con il logaritmo naturale:

$$
x>\frac{\ln 5}{\ln 2}
$$

Se invece:

$$
\left(\frac{1}{3}\right)^x>5
$$

applicando il logaritmo in base $\frac{1}{3}$ il verso si conserva nella notazione logaritmica:

$$
x>\log_{\frac{1}{3}}5
$$

ma bisogna ricordare che:

$$
\log_{\frac{1}{3}}5<0
$$

In alternativa si riscrive:

$$
\left(\frac{1}{3}\right)^x=3^{-x}
$$

e:

$$
3^{-x}>5
$$

Poiché $3>1$:

$$
-x>\log_3 5
$$

quindi:

$$
x<-\log_3 5
$$

Le due forme sono equivalenti, perché:

$$
\log_{\frac{1}{3}}5=-\log_3 5
$$

---

### **12. Disequazioni con sostituzione**

Risolviamo:

$$
4^x-3\cdot 2^x+2\le 0
$$

Poniamo:

$$
t=2^x
$$

con:

$$
t>0
$$

Poiché:

$$
4^x=(2^x)^2=t^2
$$

la disequazione diventa:

$$
t^2-3t+2\le 0
$$

Fattorizziamo:

$$
(t-1)(t-2)\le 0
$$

Il prodotto è non positivo tra le radici:

$$
1\le t\le 2
$$

Torniamo a $x$:

$$
1\le 2^x\le 2
$$

Scriviamo:

$$
1=2^0,\quad 2=2^1
$$

Poiché la base $2$ è crescente:

$$
0\le x\le 1
$$

La soluzione è:

$$
[0,1]
$$

---

### **13. Equazioni impossibili**

Poiché:

$$
a^x>0
$$

per ogni $x\in\mathbb{R}$, alcune equazioni non hanno soluzioni.

Esempio:

$$
5^x=-1
$$

non ha soluzioni reali.

Anche:

$$
2^x=0
$$

non ha soluzioni reali: l’esponenziale può avvicinarsi a $0$, ma non lo raggiunge mai.

> ⚠️ L’esponenziale reale è sempre positivo: non vale mai zero e non diventa mai negativo.

#### **13.1 Equazione parametrica fondamentale**

Consideriamo, con $a>0$ e $a\ne1$:

$$
a^x=m
$$

Se $m\le0$, non esistono soluzioni reali perché $a^x>0$. Se $m>0$, l'esponenziale è biiettivo da $\mathbb{R}$ a $(0,+\infty)$ e quindi esiste un'unica soluzione:

$$
x=\log_a m
$$

Pertanto:

$$
S_m=
\begin{cases}
\varnothing & m\le0 \\
\{\log_a m\} & m>0
\end{cases}
$$

---

### **14. Errori tipici**

#### **14.1 Uguagliare esponenti con basi diverse**

Da:

$$
2^x=3^x
$$

non si può concludere:

$$
2=3
$$

oppure cancellare $x$ in modo meccanico. In questo caso:

$$
\left(\frac{2}{3}\right)^x=1
$$

e quindi:

$$
x=0
$$

#### **14.2 Dimenticare il vincolo $t>0$ nella sostituzione**

Se si pone:

$$
t=2^x
$$

allora:

$$
t>0
$$

Eventuali soluzioni $t\le 0$ dell’equazione ausiliaria vanno scartate.

#### **14.3 Non invertire il verso per basi frazionarie**

Per:

$$
0<a<1
$$

la funzione $a^x$ è decrescente, quindi il verso si inverte passando dagli esponenziali agli esponenti.

---

### **15. Riepilogo**

> ✅ Le equazioni e disequazioni esponenziali si risolvono riconducendo le espressioni alla stessa base, usando logaritmi o introducendo sostituzioni.

Schema operativo:

- verificare se le basi possono essere riscritte come potenze della stessa base;
- usare l’iniettività di $a^x$ per uguagliare gli esponenti;
- nelle disequazioni conservare il verso se $a>1$;
- invertire il verso se $0<a<1$;
- usare i logaritmi quando la stessa base non è disponibile;
- usare sostituzioni come $t=a^x$ quando compaiono potenze collegate;
- ricordare sempre che $a^x>0$.

Con questa unità si chiude il blocco su logaritmi ed esponenziali elementari, che sarà riutilizzato nei domini, nei limiti e nello studio di funzione.
