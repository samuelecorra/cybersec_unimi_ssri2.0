# **M2 UD4 Lezione 3 - Sup e Inf**

### **1. Introduzione**

Supremo e infimo estendono le idee di massimo e minimo. Servono quando un insieme ha un estremo superiore o inferiore, ma quell’estremo non appartiene all’insieme.

Esempio:

$$
(0,1)
$$

non ha massimo, ma ha un più piccolo maggiorante: $1$.

Questo numero si chiama **supremo**.

> 📌 Il supremo può non appartenere all’insieme; il massimo deve appartenere.

---

### **2. Maggioranti e minoranti**

Sia $A\subseteq\mathbb{R}$.

Un numero $M$ è un maggiorante di $A$ se:

$$
\forall x\in A,\ x\le M
$$

L’insieme $A$ è limitato superiormente se ha almeno un maggiorante.

Un numero $m$ è un minorante di $A$ se:

$$
\forall x\in A,\ m\le x
$$

L’insieme $A$ è limitato inferiormente se ha almeno un minorante.

---

### **3. Supremo**

Il **supremo** di $A$ è il più piccolo dei maggioranti di $A$.

Si indica con:

$$
\sup A
$$

Formalmente, $s=\sup A$ se:

1. $s$ è un maggiorante di $A$;
2. ogni maggiorante $M$ di $A$ soddisfa $s\le M$.

In simboli:

$$
\forall x\in A,\ x\le s
$$

e:

$$
\forall M,\ [(\forall x\in A,\ x\le M)\Rightarrow s\le M]
$$

---

### **4. Infimo**

L’**infimo** di $A$ è il più grande dei minoranti di $A$.

Si indica con:

$$
\inf A
$$

Formalmente, $i=\inf A$ se:

1. $i$ è un minorante di $A$;
2. ogni minorante $m$ di $A$ soddisfa $m\le i$.

In simboli:

$$
\forall x\in A,\ i\le x
$$

e:

$$
\forall m,\ [(\forall x\in A,\ m\le x)\Rightarrow m\le i]
$$

---

### **5. Esempi**

Per:

$$
A=(0,1)
$$

si ha:

$$
\inf A=0
$$

e:

$$
\sup A=1
$$

ma:

$$
\min A
$$

e:

$$
\max A
$$

non esistono.

Per:

$$
B=[0,1]
$$

si ha:

$$
\inf B=\min B=0
$$

e:

$$
\sup B=\max B=1
$$

Per:

$$
C=(0,+\infty)
$$

si ha:

$$
\inf C=0
$$

ma $C$ non ha supremo reale, perché non è limitato superiormente.

---

### **6. Caratterizzazione con $\varepsilon$**

Il numero $s$ è il supremo di $A$ se:

1. $s$ è un maggiorante;
2. per ogni $\varepsilon>0$ esiste $x\in A$ tale che:

$$
s-\varepsilon<x\le s
$$

La seconda condizione dice che si possono trovare elementi di $A$ arbitrariamente vicini a $s$ da sinistra.

Analogamente, $i=\inf A$ se:

1. $i$ è un minorante;
2. per ogni $\varepsilon>0$ esiste $x\in A$ tale che:

$$
i\le x<i+\varepsilon
$$

---

### **7. Assioma di completezza**

La proprietà fondamentale dei reali è:

> ogni sottoinsieme non vuoto di $\mathbb{R}$ limitato superiormente ammette supremo reale.

Questa proprietà si chiama **completezza** di $\mathbb{R}$.

È uno dei pilastri dell’Analisi: permette di costruire limiti, continuità, integrali e molti teoremi fondamentali.

I razionali $\mathbb{Q}$ non hanno la stessa completezza.

---

### **8. Unicità di supremo e infimo**

Il supremo, se esiste, è unico.

Supponiamo che $s_1$ e $s_2$ siano entrambi supremi di $A$. Poiché $s_1$ è il più piccolo maggiorante e $s_2$ è un maggiorante, vale:

$$
s_1\le s_2
$$

Poiché $s_2$ è il più piccolo maggiorante e $s_1$ è un maggiorante, vale:

$$
s_2\le s_1
$$

Quindi:

$$
s_1=s_2
$$

La dimostrazione per l’infimo è analoga, scambiando maggioranti con minoranti.

---

### **9. Casi vuoti e illimitati**

Nel corso di Analisi 1, quando si scrive $\sup A$ o $\inf A$ come numero reale, si sottintende che l’estremo esista in $\mathbb{R}$.

L’insieme vuoto è un caso speciale: ogni numero reale è sia maggiorante sia minorante di $\varnothing$, perché non ci sono elementi che violino le disuguaglianze. Però non esiste un più piccolo maggiorante reale né un più grande minorante reale:

$$
\sup\varnothing
$$

e:

$$
\inf\varnothing
$$

non sono definiti come numeri reali.

Se $A$ non è limitato superiormente, non ha supremo reale; se non è limitato inferiormente, non ha infimo reale. In alcuni contesti avanzati si usano $+\infty$ e $-\infty$ nella retta reale estesa, ma qui restiamo in $\mathbb{R}$ salvo avviso esplicito.

---

### **10. Esempio parametrico**

Consideriamo:

$$
A_\alpha=\left\{\frac{1}{n}:n\in\mathbb{N}^+,\ n\ge \alpha\right\}
$$

con $\alpha\in\mathbb{N}^+$.

Il massimo è:

$$
\max A_\alpha=\frac{1}{\alpha}
$$

perché la successione $\frac{1}{n}$ decresce al crescere di $n$.

L’infimo è:

$$
\inf A_\alpha=0
$$

ma il minimo non esiste, perché:

$$
0\notin A_\alpha
$$

e per ogni $n$ esiste $n+1$ con:

$$
0<\frac{1}{n+1}<\frac{1}{n}
$$

Questo esempio anticipa il ruolo degli estremi nello studio delle successioni.

---

### **11. Esempio nei razionali**

Consideriamo in $\mathbb{Q}$ l’insieme:

$$
A=\{q\in\mathbb{Q}: q^2<2,\ q>0\}
$$

In $\mathbb{R}$ il supremo è:

$$
\sqrt{2}
$$

ma:

$$
\sqrt{2}\notin\mathbb{Q}
$$

Quindi, se restiamo solo nei razionali, questo insieme non ha supremo razionale.

Questo mostra perché $\mathbb{R}$ è l’ambiente naturale dell’Analisi.

---

### **12. Esercizi rappresentativi**

1. Determinare estremi di:

$$
A=(2,5]
$$

Si ha:

$$
\inf A=2,\qquad \sup A=5,\qquad \max A=5
$$

ma il minimo non esiste perché $2\notin A$.

2. Per:

$$
B=\left\{1-\frac{1}{n}:n\in\mathbb{N}^+\right\}
$$

si ha:

$$
B=\left\{0,\frac{1}{2},\frac{2}{3},\frac{3}{4},\dots\right\}
$$

Quindi:

$$
\min B=0,\qquad \inf B=0,\qquad \sup B=1
$$

ma il massimo non esiste, perché $1\notin B$.

3. Per:

$$
C_a=(a,2a)
$$

con $a>0$, valgono:

$$
\inf C_a=a,\qquad \sup C_a=2a
$$

e non esistono minimo né massimo. Se $a=0$, invece:

$$
C_a=\varnothing
$$

nel senso usuale di intervallo aperto $(0,0)$.

---

### **13. Riepilogo**

> ✅ Supremo e infimo sono il più piccolo maggiorante e il più grande minorante.

Punti essenziali:

- $\sup A$ può non appartenere ad $A$;
- $\inf A$ può non appartenere ad $A$;
- se il massimo esiste, allora coincide con il supremo;
- se il minimo esiste, allora coincide con l’infimo;
- insiemi vuoti o illimitati richiedono attenzione perché l’estremo reale può non esistere;
- la completezza di $\mathbb{R}$ garantisce l’esistenza del supremo per insiemi non vuoti e limitati superiormente.
