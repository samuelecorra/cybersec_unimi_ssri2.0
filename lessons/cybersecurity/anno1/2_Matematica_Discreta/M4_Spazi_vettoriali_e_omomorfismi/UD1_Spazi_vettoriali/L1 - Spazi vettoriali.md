# **M4 UD1 Lezione 1 - Spazi vettoriali**

### **1. Obiettivi e prerequisiti**

La lezione introduce spazi vettoriali e sottospazi. Sono richieste le nozioni di campo, gruppo abeliano, matrici e polinomi.

Al termine sapremo verificare gli assiomi di spazio vettoriale, distinguere il campo degli scalari dall'insieme dei vettori e applicare il criterio di sottospazio.

---

### **2. Definizione di spazio vettoriale**

Sia $K$ un campo. Uno **spazio vettoriale su $K$** è un gruppo abeliano $(V,+)$ dotato di un prodotto per scalari

$$
K\times V\longrightarrow V,\qquad (\lambda,v)\longmapsto \lambda v,
$$

tale che, per ogni $\lambda,\mu\in K$ e $u,v\in V$,

$$
\lambda(u+v)=\lambda u+\lambda v,
$$

$$
(\lambda+\mu)v=\lambda v+\mu v,
$$

$$
(\lambda\mu)v=\lambda(\mu v),
$$

$$
1_Kv=v.
$$

Gli elementi di $V$ sono i **vettori**; quelli di $K$ sono gli **scalari**. Lo zero di $V$ è indicato con $0_V$.

> 📌 Specificare il campo è indispensabile: lo stesso insieme può essere spazio vettoriale su un campo e non esserlo su un altro.

Dagli assiomi seguono

$$
0_Kv=0_V,\qquad \lambda0_V=0_V,\qquad (-1_K)v=-v.
$$

Per esempio, $0_Kv=(0_K+0_K)v=0_Kv+0_Kv$ e la cancellazione nel gruppo additivo dà $0_Kv=0_V$.

---

### **3. Esempi fondamentali**

Sono spazi vettoriali, con le operazioni usuali:

- $\mathbb Q^2$ sul campo $\mathbb Q$;
- $\mathbb Q_2[x]$, polinomi di grado al più $2$ a coefficienti razionali, su $\mathbb Q$;
- $M_{2\times2}(\mathbb R)$ su $\mathbb R$.

In $\mathbb Q^2$,

$$
(a,b)+(c,d)=(a+c,b+d),\qquad q(a,b)=(qa,qb).
$$

In $M_{2\times2}(\mathbb R)$ i vettori sono matrici; il prodotto tra matrici non è il prodotto per scalari dello spazio: lo scalare è un numero reale.

#### **3.1. Cambiare campo**

$\mathbb Q^2$ non è uno spazio vettoriale su $\mathbb R$: per esempio

$$
\sqrt2(1,0)=(\sqrt2,0)\notin\mathbb Q^2.
$$

Al contrario, $\mathbb R^2$ è anche uno spazio vettoriale su $\mathbb Q$, perché moltiplicare coordinate reali per razionali non fa uscire da $\mathbb R^2$. Come spazio su $\mathbb Q$ ha però dimensione infinita.

Un gruppo come $\mathbb Z_5\times\mathbb Z_3$ non può essere reso spazio vettoriale sui campi naturali suggeriti dai due fattori: in uno spazio su $\mathbb Z_p$ il gruppo additivo ha esponente $p$, mentre qui compaiono elementi di ordini incompatibili, $5$ e $3$.

---

### **4. Sottospazi vettoriali**

Sia $V$ uno spazio vettoriale su $K$. Un sottoinsieme $W\subseteq V$ è un **sottospazio vettoriale** se, con le operazioni ereditate da $V$, è a sua volta uno spazio vettoriale su $K$.

Il controllo degli assiomi si riduce al seguente criterio.

> 📌 Un sottoinsieme non vuoto $W\subseteq V$ è sottospazio se e solo se
> $$
> \alpha u+\beta v\in W
> $$
> per ogni $u,v\in W$ e ogni $\alpha,\beta\in K$.

È equivalente richiedere $0_V\in W$, chiusura rispetto alla somma e chiusura rispetto al prodotto per scalari.

---

### **5. Esempi e non-esempi**

In $\mathbb R^2$,

$$
W_1=\{(a,2a):a\in\mathbb R\}
$$

è sottospazio, perché

$$
\alpha(a,2a)+\beta(b,2b)=(\alpha a+\beta b,2(\alpha a+\beta b)).
$$

Invece

$$
W_2=\{(a,3):a\in\mathbb R\}
$$

non lo è: $(0,0)\notin W_2$.

In $\mathbb R_2[x]$,

$$
W_3=\{ax^2+2ax+3a:a\in\mathbb R\}
$$

è sottospazio. I polinomi monici di grado $2$ non lo sono: il polinomio nullo non è monico e la moltiplicazione per uno scalare cambia il coefficiente direttivo.

Neppure l'insieme dei polinomi di grado **esattamente** $2$ è un sottospazio: non contiene $0$ e la somma può cancellare i termini quadratici.

In $\mathbb Z_5^2$,

$$
W_4=\{(a,4a):a\in\mathbb Z_5\}
$$

è sottospazio, mentre $\{(a,a^2):a\in\mathbb Z_5\}$ non è chiuso rispetto alle combinazioni lineari.

In $M_{2\times2}(\mathbb Z_7)$,

$$
W_5=\left\{\begin{bmatrix}a&a\\0&a\end{bmatrix}:a\in\mathbb Z_7\right\}
$$

è sottospazio. Non lo è

$$
W_6=\left\{\begin{bmatrix}a&2a\\0&1\end{bmatrix}:a\in\mathbb Z_7\right\},
$$

perché la matrice nulla non appartiene all'insieme.

---

### **6. Procedura di verifica**

1. Identificare campo $K$, spazio ambiente $V$ e operazioni.
2. Controllare che $0_V$ appartenga all'insieme.
3. Prendere due elementi generici e scalari generici.
4. Verificare che la loro combinazione lineare rispetti ancora i vincoli.

> ⚠️ Un vincolo lineare omogeneo tende a definire un sottospazio; un termine costante non nullo, una condizione di grado esatto o un vincolo non lineare come $b=a^2$ in genere no.

---

> ✅ Uno spazio vettoriale combina un gruppo abeliano di vettori con l'azione di un campo di scalari. Un sottospazio deve contenere lo zero ed essere chiuso rispetto a tutte le combinazioni lineari.
