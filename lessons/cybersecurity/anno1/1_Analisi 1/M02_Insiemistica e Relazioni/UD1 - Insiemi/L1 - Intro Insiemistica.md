# **M2 UD1 Lezione 1 - Intro Insiemistica**

### **1. Introduzione**

L’insiemistica fornisce il linguaggio di base dell’Analisi. Quando diciamo che una funzione è definita su un dominio, che una successione è una applicazione da $\mathbb{N}$ in $\mathbb{R}$, che un limite vale per ogni $\varepsilon>0$, stiamo usando il linguaggio degli insiemi.

Un **insieme** è una collezione di oggetti, chiamati **elementi**. Gli elementi possono essere numeri, punti, funzioni, coppie ordinate o altri insiemi.

> 📌 In Analisi un insieme serve a delimitare con precisione “di quali oggetti stiamo parlando”.

---

### **2. Appartenenza**

Se $a$ è un elemento dell’insieme $A$, si scrive:

$$
a\in A
$$

Se invece $a$ non appartiene ad $A$, si scrive:

$$
a\notin A
$$

Esempio:

$$
2\in\mathbb{N}
$$

ma:

$$
-1\notin\mathbb{N}
$$

se $\mathbb{N}$ indica l’insieme dei numeri naturali.

---

### **3. Descrivere un insieme per elencazione**

Un insieme finito può essere descritto elencando i suoi elementi:

$$
A=\{1,2,3,4\}
$$

L’ordine non conta:

$$
\{1,2,3\}=\{3,2,1\}
$$

e le ripetizioni non contano:

$$
\{1,1,2,3\}=\{1,2,3\}
$$

Questo distingue gli insiemi dalle liste ordinate.

---

### **4. Descrivere un insieme per proprietà**

Molti insiemi si descrivono tramite una proprietà:

$$
A=\{x\in\mathbb{R}: x>0\}
$$

Si legge: “l’insieme degli $x$ reali tali che $x>0$”.

La parte prima dei due punti indica l’universo in cui stiamo cercando gli elementi; la parte dopo i due punti indica la condizione.

Esempio:

$$
B=\{n\in\mathbb{N}: n \text{ è pari}\}
$$

è l’insieme dei numeri naturali pari.

---

### **5. Insieme vuoto**

L’insieme che non contiene alcun elemento si chiama **insieme vuoto** e si indica con:

$$
\varnothing
$$

Per esempio:

$$
\{x\in\mathbb{R}: x^2+1=0\}=\varnothing
$$

perché nessun numero reale ha quadrato uguale a $-1$.

> ⚠️ L’insieme vuoto non è la stessa cosa del numero $0$: $0$ è un numero, $\varnothing$ è un insieme senza elementi.

---

### **6. Insiemi numerici fondamentali**

Gli insiemi numerici principali sono:

$$
\mathbb{N}
$$

numeri naturali;

$$
\mathbb{Z}
$$

numeri interi;

$$
\mathbb{Q}
$$

numeri razionali;

$$
\mathbb{R}
$$

numeri reali.

$$
\mathbb{C}
$$

numeri complessi.

Essi sono inclusi uno nell’altro:

$$
\mathbb{N}\subseteq\mathbb{Z}\subseteq\mathbb{Q}\subseteq\mathbb{R}\subseteq\mathbb{C}
$$

Questa catena sarà usata continuamente.

---

### **7. Intervalli reali**

In Analisi gli insiemi più frequenti sono gli intervalli.

Intervallo aperto:

$$
(a,b)=\{x\in\mathbb{R}: a<x<b\}
$$

Intervallo chiuso:

$$
[a,b]=\{x\in\mathbb{R}: a\le x\le b\}
$$

Intervalli illimitati:

$$
(a,+\infty)=\{x\in\mathbb{R}: x>a\}
$$

$$
(-\infty,b]=\{x\in\mathbb{R}: x\le b\}
$$

Il simbolo $+\infty$ non è un numero reale: indica che l’intervallo non ha estremo superiore finito.

---

### **8. Uguaglianza tra insiemi**

Due insiemi $A$ e $B$ sono uguali se hanno esattamente gli stessi elementi:

$$
A=B
$$

significa:

$$
x\in A \iff x\in B
$$

Per dimostrare che due insiemi sono uguali si procede spesso in due passaggi:

1. si mostra che ogni elemento di $A$ appartiene a $B$;
2. si mostra che ogni elemento di $B$ appartiene ad $A$.

Questo metodo diventerà naturale nello studio di inclusioni, immagini e preimmagini.

---

### **9. Riepilogo**

> ✅ Un insieme è una collezione di elementi descritta per elencazione o tramite una proprietà.

Punti essenziali:

- $a\in A$ significa che $a$ appartiene ad $A$;
- $\varnothing$ è l’insieme senza elementi;
- gli insiemi non tengono conto dell’ordine né delle ripetizioni;
- gli intervalli sono insiemi di numeri reali;
- $\mathbb{N}\subseteq\mathbb{Z}\subseteq\mathbb{Q}\subseteq\mathbb{R}\subseteq\mathbb{C}$;
- due insiemi sono uguali quando hanno gli stessi elementi.
