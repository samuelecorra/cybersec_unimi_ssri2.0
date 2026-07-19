# **M2 UD3 Lezione 2 - Relazioni d'ordine**

### **1. Introduzione**

Una relazione d’ordine permette di confrontare elementi. L’esempio più familiare è:

$$
\le
$$

sui numeri reali.

Ma l’idea di ordine è più generale: anche l’inclusione tra insiemi è un ordine.

> 📌 Una relazione d’ordine formalizza il concetto di “stare prima”, “essere minore”, “essere contenuto”.

---

### **2. Ordine parziale**

Una relazione $R$ su un insieme $A$ è una **relazione d’ordine parziale** se è:

- riflessiva;
- antisimmetrica;
- transitiva.

Spesso una relazione d’ordine si indica con:

$$
\preceq
$$

Quindi:

$$
a\preceq b
$$

si legge “$a$ precede $b$” oppure “$a$ è minore o uguale a $b$” nel senso dell’ordine considerato.

---

### **3. Esempio: ordine usuale su $\mathbb{R}$**

La relazione:

$$
\le
$$

su $\mathbb{R}$ è un ordine.

È riflessiva:

$$
x\le x
$$

È antisimmetrica:

$$
x\le y \wedge y\le x \Rightarrow x=y
$$

È transitiva:

$$
x\le y \wedge y\le z \Rightarrow x\le z
$$

---

### **4. Esempio: inclusione tra insiemi**

Sia $X$ un insieme e consideriamo:

$$
\mathcal{P}(X)
$$

l’insieme delle parti di $X$.

La relazione:

$$
\subseteq
$$

su $\mathcal{P}(X)$ è un ordine parziale.

È riflessiva:

$$
A\subseteq A
$$

È antisimmetrica:

$$
A\subseteq B \wedge B\subseteq A \Rightarrow A=B
$$

È transitiva:

$$
A\subseteq B \wedge B\subseteq C \Rightarrow A\subseteq C
$$

---

### **5. Ordine totale**

Un ordine è **totale** se ogni coppia di elementi è confrontabile.

Formalmente, per ogni $a,b\in A$:

$$
a\preceq b \vee b\preceq a
$$

L’ordine usuale su $\mathbb{R}$ è totale: dati due numeri reali, uno è minore o uguale all’altro.

L’inclusione tra insiemi, invece, non è totale.

Esempio:

$$
\{1\}\nsubseteq\{2\}
$$

e:

$$
\{2\}\nsubseteq\{1\}
$$

Questi due insiemi non sono confrontabili per inclusione.

---

### **6. Ordini stretti e non stretti**

Un ordine non stretto, come $\le$ o $\subseteq$, è riflessivo: ogni elemento è confrontabile con se stesso.

Da un ordine non stretto si ottiene spesso un ordine stretto definendo:

$$
a\prec b \iff a\preceq b \text{ e } a\neq b
$$

L’ordine stretto è irriflessivo e transitivo. Per esempio:

$$
<
$$

su $\mathbb{R}$ è irriflessivo, asimmetrico e transitivo.

> 📌 $\le$ consente l’uguaglianza; $<$ la esclude.

---

### **7. Diagrammi di Hasse**

Per ordini parziali finiti si può usare un **diagramma di Hasse**: si disegnano gli elementi come nodi e si collega $a$ a $b$ quando $a\prec b$ e non esiste un elemento intermedio $c$ con $a\prec c\prec b$.

Esempio: in $\mathcal{P}(\{1,2\})$, l’insieme vuoto sta in basso, $\{1\}$ e $\{2\}$ stanno al livello intermedio, $\{1,2\}$ sta in alto.

<!-- TODO FIGURA: diagramma di Hasse di P({1,2}) con ∅ in basso, {1} e {2} al centro, {1,2} in alto; obiettivo didattico: mostrare un ordine parziale con elementi non confrontabili. -->

---

### **8. Elementi minimali, massimali, minimo e massimo**

In un ordine parziale, un elemento $m$ è **minimale** se non esiste un elemento strettamente più piccolo:

$$
\nexists x\in A:\ x\prec m
$$

Un elemento $M$ è **massimale** se non esiste un elemento strettamente più grande:

$$
\nexists x\in A:\ M\prec x
$$

Un **minimo**, invece, deve essere minore o uguale di tutti:

$$
\forall x\in A,\ m\preceq x
$$

Un **massimo** deve essere maggiore o uguale di tutti:

$$
\forall x\in A,\ x\preceq M
$$

Quindi un minimo, se esiste, è minimale; ma un minimale non è necessariamente minimo.

Esempio: nell’insieme $\{\{1\},\{2\}\}$ ordinato per inclusione, sia $\{1\}$ sia $\{2\}$ sono minimali e massimali, ma non esistono né minimo né massimo.

> ⚠️ “Minimale” significa che nessuno sta sotto; “minimo” significa che sta sotto tutti.

---

### **9. Maggioranti e minoranti**

Sia $A\subseteq\mathbb{R}$.

Un numero $M$ è un **maggiorante** di $A$ se:

$$
\forall x\in A,\ x\le M
$$

Un numero $m$ è un **minorante** di $A$ se:

$$
\forall x\in A,\ m\le x
$$

Questi concetti preparano minimo, massimo, supremo e infimo.

---

### **10. Esempio**

Consideriamo:

$$
A=(0,1)
$$

Il numero $2$ è un maggiorante, perché:

$$
x\le 2
$$

per ogni $x\in(0,1)$.

Anche $1$ è un maggiorante.

Il numero $0$ è un minorante, perché:

$$
0\le x
$$

per ogni $x\in(0,1)$.

Anche $-5$ è un minorante.

---

### **11. Esercizi rappresentativi**

1. Consideriamo:

$$
A=\{\{1\},\{2\},\{1,2\}\}
$$

ordinato per inclusione. Gli elementi minimali sono:

$$
\{1\},\qquad \{2\}
$$

Non esiste minimo, perché né $\{1\}\subseteq\{2\}$ né $\{2\}\subseteq\{1\}$. Il massimo invece esiste:

$$
\{1,2\}
$$

2. In $\mathcal{P}(\{1,2,3\})$ ordinato per inclusione, l’insieme:

$$
B=\{\{1\},\{1,2\},\{1,3\}\}
$$

ha minimo $\{1\}$, perché:

$$
\{1\}\subseteq\{1,2\},\qquad \{1\}\subseteq\{1,3\}
$$

ma non ha massimo: $\{1,2\}$ e $\{1,3\}$ sono massimali e non confrontabili.

3. Nel diagramma di Hasse di $\mathcal{P}(\{1,2\})$, i livelli sono:

- $\varnothing$ al livello più basso;
- $\{1\}$ e $\{2\}$ al livello intermedio;
- $\{1,2\}$ al livello più alto.

Questo esercizio allena a distinguere ordine parziale e ordine totale.

---

### **12. Riepilogo**

> ✅ Una relazione d’ordine è riflessiva, antisimmetrica e transitiva.

Punti essenziali:

- $\le$ su $\mathbb{R}$ è un ordine totale;
- $\subseteq$ su $\mathcal{P}(X)$ è un ordine parziale;
- ordine totale significa che ogni coppia è confrontabile;
- gli ordini stretti escludono l’uguaglianza;
- in un ordine parziale minimale e minimo non coincidono in generale;
- un maggiorante sta sopra tutti gli elementi di un insieme;
- un minorante sta sotto tutti gli elementi di un insieme.
