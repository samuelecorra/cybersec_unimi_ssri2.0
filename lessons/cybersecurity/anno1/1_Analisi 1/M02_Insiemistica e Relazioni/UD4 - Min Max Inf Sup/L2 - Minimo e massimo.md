# **M2 UD4 Lezione 2 - Minimo e massimo**

### **1. Introduzione**

Minimo e massimo descrivono il più piccolo e il più grande elemento di un insieme, quando esistono.

L’idea sembra semplice, ma in Analisi bisogna distinguere tra:

- essere un limite inferiore o superiore;
- appartenere davvero all’insieme;
- essere solo un estremo “avvicinabile”.

> 📌 Minimo e massimo devono appartenere all’insieme.

---

### **2. Massimo**

Sia $A\subseteq\mathbb{R}$. Un numero $M$ è il **massimo** di $A$ se:

1. $M\in A$;
2. per ogni $x\in A$, vale $x\le M$.

Si scrive:

$$
M=\max A
$$

Quindi:

$$
\max A\in A
$$

ed è un maggiorante di $A$.

---

### **3. Minimo**

Un numero $m$ è il **minimo** di $A$ se:

1. $m\in A$;
2. per ogni $x\in A$, vale $m\le x$.

Si scrive:

$$
m=\min A
$$

Quindi:

$$
\min A\in A
$$

ed è un minorante di $A$.

---

### **4. Esempio con intervallo chiuso**

Consideriamo:

$$
A=[0,1]
$$

Il minimo è:

$$
\min A=0
$$

perché $0\in A$ e $0\le x$ per ogni $x\in A$.

Il massimo è:

$$
\max A=1
$$

perché $1\in A$ e $x\le 1$ per ogni $x\in A$.

---

### **5. Esempio con intervallo aperto**

Consideriamo:

$$
B=(0,1)
$$

Il numero $0$ è un minorante, ma:

$$
0\notin B
$$

Quindi $0$ non è minimo.

Il numero $1$ è un maggiorante, ma:

$$
1\notin B
$$

Quindi $1$ non è massimo.

L’insieme $(0,1)$ non ha né minimo né massimo.

> ⚠️ Un estremo non è minimo o massimo se non appartiene all’insieme.

---

### **6. Unicità**

Se il massimo esiste, è unico.

Infatti, supponiamo che $M_1$ e $M_2$ siano entrambi massimi di $A$.

Poiché $M_1$ è massimo:

$$
M_2\le M_1
$$

Poiché $M_2$ è massimo:

$$
M_1\le M_2
$$

Quindi:

$$
M_1=M_2
$$

Lo stesso ragionamento vale per il minimo.

---

### **7. Insiemi finiti**

Ogni insieme finito non vuoto di numeri reali ha minimo e massimo.

Esempio:

$$
A=\{-3,5,2,10\}
$$

ha:

$$
\min A=-3
$$

e:

$$
\max A=10
$$

Questa proprietà non vale automaticamente per insiemi infiniti.

---

### **8. Insiemi illimitati**

Consideriamo:

$$
A=(0,+\infty)
$$

Non ha massimo, perché per ogni $x\in A$ esiste un elemento più grande, ad esempio:

$$
x+1\in A
$$

Non ha nemmeno minimo, perché $0$ non appartiene all’insieme.

---

### **9. Casi vuoti e insiemi con parametri**

L’insieme vuoto non ha minimo né massimo: non esiste alcun elemento che possa appartenere all’insieme e dominare tutti gli altri.

Per una famiglia parametrica, gli estremi possono cambiare al variare del parametro. Consideriamo:

$$
A_t=[0,t]\subseteq\mathbb{R}
$$

Se $t>0$, allora:

$$
\min A_t=0,\qquad \max A_t=t
$$

Se $t=0$, allora:

$$
A_t=\{0\}
$$

e minimo e massimo coincidono:

$$
\min A_t=\max A_t=0
$$

Se $t<0$, la scrittura $[0,t]$ non descrive un intervallo reale nel senso usuale con estremo sinistro minore dell’estremo destro; bisogna riscrivere il problema o dichiarare l’insieme vuoto.

> ⚠️ Prima di cercare minimo e massimo, controlla sempre se l’insieme è vuoto e se gli estremi appartengono davvero all’insieme.

---

### **10. Collegamento con ottimizzazione**

Nei problemi di ottimizzazione si cerca spesso il massimo o il minimo di un insieme di valori:

$$
f(A)=\{f(x):x\in A\}
$$

Dire che $f$ ha massimo in $x_0\in A$ significa:

$$
f(x_0)=\max f(A)
$$

Questa formulazione separa due aspetti:

- $x_0$ è il punto del dominio in cui l’estremo viene raggiunto;
- $f(x_0)$ è il valore massimo o minimo.

La distinzione diventerà essenziale con continuità, derivate e problemi di massimo/minimo.

---

### **11. Riepilogo**

> ✅ Minimo e massimo sono estremi che appartengono all’insieme.

Punti essenziali:

- $M=\max A$ significa $M\in A$ e $x\le M$ per ogni $x\in A$;
- $m=\min A$ significa $m\in A$ e $m\le x$ per ogni $x\in A$;
- minimo e massimo, se esistono, sono unici;
- un insieme aperto può non avere minimo o massimo;
- l’insieme vuoto non ha minimo né massimo;
- un insieme finito non vuoto di reali ha sempre minimo e massimo.
