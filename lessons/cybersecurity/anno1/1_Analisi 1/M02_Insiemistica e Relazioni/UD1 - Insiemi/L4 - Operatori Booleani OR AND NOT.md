# **M2 UD1 Lezione 4 - Operatori Booleani OR AND NOT**

### **1. Introduzione**

Gli operatori booleani sono operatori logici. Servono a combinare proposizioni vere o false.

In matematica compaiono continuamente:

- nelle definizioni;
- nei domini;
- nelle dimostrazioni;
- nelle condizioni di esistenza;
- nelle disequazioni con più casi.

Gli operatori fondamentali sono:

- AND, cioè “e”;
- OR, cioè “oppure”;
- NOT, cioè “non”.

> 📌 La logica booleana è il lato proposizionale delle operazioni insiemistiche.

---

### **2. Proposizioni**

Una proposizione è una frase matematica che può essere vera o falsa.

Esempi:

$$
2<5
$$

è vera, mentre:

$$
3^2=8
$$

è falsa.

Una proposizione con variabile, come:

$$
x>0
$$

non è vera o falsa finché non si specifica $x$.

---

### **3. Operatore AND**

L’operatore AND si indica spesso con:

$$
\wedge
$$

La proposizione:

$$
P\wedge Q
$$

è vera se e solo se sono vere sia $P$ sia $Q$.

Esempio:

$$
x>0 \wedge x<3
$$

significa:

$$
0<x<3
$$

In termini di insiemi, AND corrisponde all’intersezione:

$$
\{x:P(x)\}\cap\{x:Q(x)\}
$$

---

### **4. Operatore OR**

L’operatore OR si indica spesso con:

$$
\vee
$$

La proposizione:

$$
P\vee Q
$$

è vera se è vera almeno una tra $P$ e $Q$.

In matematica, salvo indicazione diversa, OR è inclusivo: può essere vera anche quando sono vere entrambe.

Esempio:

$$
x<0 \vee x>2
$$

descrive:

$$
(-\infty,0)\cup(2,+\infty)
$$

In termini di insiemi, OR corrisponde all’unione.

---

### **5. Operatore NOT**

L’operatore NOT si indica con:

$$
\neg
$$

La proposizione:

$$
\neg P
$$

è vera quando $P$ è falsa.

Esempio:

Se:

$$
P: x>0
$$

allora:

$$
\neg P: x\le 0
$$

In termini di insiemi, NOT corrisponde al complementare.

#### **5.1 Tavole di verità essenziali**

Una tavola di verità mostra tutti i casi possibili per il valore di verità delle proposizioni coinvolte.

Per AND e OR:

| $P$ | $Q$ | $P\wedge Q$ | $P\vee Q$ |
|---|---|---|---|
| V | V | V | V |
| V | F | F | V |
| F | V | F | V |
| F | F | F | F |

Per NOT:

| $P$ | $\neg P$ |
|---|---|
| V | F |
| F | V |

Per l’implicazione:

| $P$ | $Q$ | $P\Rightarrow Q$ |
|---|---|---|
| V | V | V |
| V | F | F |
| F | V | V |
| F | F | V |

L’unico caso in cui $P\Rightarrow Q$ è falsa è quello in cui $P$ è vera e $Q$ è falsa.

---

### **6. Negare una congiunzione**

La negazione di:

$$
P\wedge Q
$$

è:

$$
\neg P\vee \neg Q
$$

Questa è una legge di De Morgan:

$$
\neg(P\wedge Q)=(\neg P)\vee(\neg Q)
$$

Esempio:

La negazione di:

$$
x>0 \wedge x<1
$$

è:

$$
x\le 0 \vee x\ge 1
$$

---

### **7. Negare una disgiunzione**

La negazione di:

$$
P\vee Q
$$

è:

$$
\neg P\wedge \neg Q
$$

Cioè:

$$
\neg(P\vee Q)=(\neg P)\wedge(\neg Q)
$$

Esempio:

La negazione di:

$$
x<2 \vee x>5
$$

è:

$$
x\ge 2 \wedge x\le 5
$$

cioè:

$$
2\le x\le 5
$$

---

### **8. Implicazione**

Un’altra forma logica fondamentale è l’implicazione:

$$
P\Rightarrow Q
$$

Si legge: “se $P$, allora $Q$”.

Esempio:

$$
x>2\Rightarrow x>0
$$

è vera per ogni $x\in\mathbb{R}$.

L’implicazione è alla base delle dimostrazioni di inclusione:

$$
A\subseteq B
$$

significa:

$$
x\in A\Rightarrow x\in B
$$

---

### **9. Doppia implicazione**

La doppia implicazione:

$$
P\iff Q
$$

significa che $P$ e $Q$ sono equivalenti: hanno lo stesso valore di verità.

Per dimostrare:

$$
P\iff Q
$$

si dimostrano due implicazioni:

$$
P\Rightarrow Q
$$

e:

$$
Q\Rightarrow P
$$

Questa struttura è analoga alla doppia inclusione tra insiemi.

---

### **10. Condizioni necessarie e sufficienti**

L’implicazione permette di distinguere due espressioni molto usate.

Dire che $P$ è una **condizione sufficiente** per $Q$ significa:

$$
P\Rightarrow Q
$$

Se $P$ è vera, allora $Q$ è garantita.

Dire che $P$ è una **condizione necessaria** per $Q$ significa:

$$
Q\Rightarrow P
$$

Se $Q$ è vera, allora $P$ deve essere vera.

Esempio:

$$
x>2\Rightarrow x>0
$$

Quindi $x>2$ è sufficiente per $x>0$, mentre $x>0$ è necessaria per $x>2$.

Quando valgono entrambe le implicazioni:

$$
P\iff Q
$$

si dice che $P$ è **necessaria e sufficiente** per $Q$.

> ⚠️ Confondere necessario e sufficiente porta a dimostrazioni nel verso sbagliato.

---

### **11. Tecniche di dimostrazione e controesempi**

Una dimostrazione diretta di $P\Rightarrow Q$ parte da $P$ e arriva a $Q$ con passaggi giustificati.

Una dimostrazione per contronominale usa l’equivalenza:

$$
(P\Rightarrow Q)\iff(\neg Q\Rightarrow \neg P)
$$

Per esempio, per mostrare che se $n^2$ è pari allora $n$ è pari, si dimostra la contronominale: se $n$ è dispari, allora $n^2$ è dispari.

Una dimostrazione per assurdo suppone vera la negazione della tesi e arriva a una contraddizione. Per dimostrare una tesi $Q$, si assume $\neg Q$ e si deduce una proposizione impossibile, ad esempio $0=1$ oppure una violazione di un’ipotesi già fissata.

Esempio schematico: per dimostrare che non esiste un massimo di $(0,1)$, si suppone per assurdo che $M=\max(0,1)$. Allora $M\in(0,1)$, quindi:

$$
0<M<1
$$

ma il numero:

$$
\frac{M+1}{2}
$$

appartiene ancora a $(0,1)$ ed è maggiore di $M$, contraddicendo il fatto che $M$ fosse massimo.

Per smentire un’affermazione universale basta un **controesempio**. L’enunciato:

$$
\forall x\in\mathbb{R},\ x^2>x
$$

è falso perché $x=0$ dà:

$$
0^2=0
$$

e quindi non vale $0^2>0$.

> 📌 Un controesempio non dimostra una regola generale: serve a distruggerla.

---

### **12. Quantificatori**

I due quantificatori più comuni sono:

$$
\forall
$$

che significa “per ogni”, e:

$$
\exists
$$

che significa “esiste”.

Esempio:

$$
\forall x\in\mathbb{R},\ x^2\ge 0
$$

è vero.

Invece:

$$
\exists x\in\mathbb{R}: x^2=2
$$

è vero, perché esistono $x=\sqrt{2}$ e $x=-\sqrt{2}$.

#### **12.1 Ordine dei quantificatori**

L’ordine dei quantificatori è parte del significato dell’enunciato.

L’enunciato:

$$
\forall x\in\mathbb{R}\ \exists y\in\mathbb{R}: y>x
$$

è vero: per ogni $x$ possiamo scegliere, per esempio, $y=x+1$.

L’enunciato con i quantificatori scambiati:

$$
\exists y\in\mathbb{R}\ \forall x\in\mathbb{R}: y>x
$$

è falso: chiederebbe un numero reale maggiore di tutti i reali.

> ⚠️ Scambiare $\forall$ ed $\exists$ cambia spesso completamente il problema.

---

### **13. Negare i quantificatori**

La negazione di “per ogni” è “esiste almeno uno che non”.

$$
\neg(\forall x,\ P(x))\iff \exists x:\neg P(x)
$$

La negazione di “esiste” è “per ogni, non”.

$$
\neg(\exists x,\ P(x))\iff \forall x:\neg P(x)
$$

Esempio:

La negazione di:

$$
\forall x\in\mathbb{R},\ x>0
$$

è:

$$
\exists x\in\mathbb{R}: x\le 0
$$

---

### **14. Esercizi rappresentativi**

1. Negare formalmente:

$$
\forall x\in\mathbb{R},\ x>0\Rightarrow x^2>0
$$

Soluzione guidata:

$$
\exists x\in\mathbb{R}: x>0 \wedge x^2\le 0
$$

L’enunciato originale è vero, quindi la negazione è falsa.

2. Stabilire se la condizione $x>3$ è necessaria, sufficiente o necessaria e sufficiente per $x^2>9$.

È sufficiente, perché $x>3\Rightarrow x^2>9$. Non è necessaria, perché anche $x<-3$ implica $x^2>9$.

3. Tradurre in insieme soluzione:

$$
(x\le -1 \vee x>2)\wedge x\neq 0
$$

Poiché $0$ non appartiene né a $(-\infty,-1]$ né a $(2,+\infty)$, la condizione $x\neq 0$ non cambia nulla:

$$
(-\infty,-1]\cup(2,+\infty)
$$

---

### **15. Riepilogo**

> ✅ AND, OR e NOT traducono in logica le operazioni di intersezione, unione e complementare.

Punti essenziali:

- $P\wedge Q$ è vero quando sono vere entrambe;
- $P\vee Q$ è vero quando è vera almeno una delle due;
- $\neg P$ inverte il valore di verità;
- De Morgan governa la negazione di AND e OR;
- necessario e sufficiente indicano il verso corretto dell’implicazione;
- $\forall$ significa “per ogni”;
- $\exists$ significa “esiste”;
- l’ordine dei quantificatori è significativo;
- negare un quantificatore lo trasforma nell’altro.
