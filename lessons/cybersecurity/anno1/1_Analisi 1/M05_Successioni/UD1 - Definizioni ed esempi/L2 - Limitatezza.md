# **M5 UD1 Lezione 2 - Limitatezza**

### **1. Successione e insieme immagine**

La limitatezza di $(a_n)$ è, formalmente, la limitatezza dell'insieme

$$
A=\{a_n:n\in\mathbb N\}\subseteq\mathbb R.
$$

Una successione è limitata superiormente se

$$
\exists M\in\mathbb R\ \forall n\in\mathbb N:\ a_n\le M;
$$

$M$ è un maggiorante. È limitata inferiormente se

$$
\exists m\in\mathbb R\ \forall n\in\mathbb N:\ m\le a_n;
$$

$m$ è un minorante.

---

### **2. Limitatezza bilaterale**

Le seguenti condizioni sono equivalenti:

1. $(a_n)$ è limitata superiormente e inferiormente;
2. esiste $C>0$ tale che $|a_n|\le C$ per ogni $n$;
3. esistono $m,M\in\mathbb R$ con $m\le a_n\le M$ per ogni $n$.

**Dimostrazione.** Da $|a_n|\le C$ segue $-C\le a_n\le C$. Viceversa, da $m\le a_n\le M$ segue

$$
|a_n|\le\max\{|m|,|M|\}.
$$

> 📌 "Limitata" significa limitata da una costante indipendente dall'indice.

---

### **3. Supremo, infimo, massimo e minimo**

Se $A$ è non vuoto e limitato superiormente, $\sup A$ è il minimo dei maggioranti. Se è limitato inferiormente, $\inf A$ è il massimo dei minoranti.

Il massimo deve essere assunto:

$$
\max A\in A,
$$

mentre $\sup A$ può non appartenere ad $A$. Analogamente per minimo e infimo.

Per

$$
a_n=1-\frac1{n+1},
$$

si ha

$$
\sup A=1,\qquad \min A=0,
$$

ma non esiste $\max A$.

Per $a_n=(-1)^n$,

$$
\sup A=\max A=1,\qquad
\inf A=\min A=-1.
$$

<!-- TODO FIGURA: asse reale con i valori 1-1/(n+1) che si addensano sotto 1, evidenziando minimo 0, supremo 1 non assunto e maggioranti; obiettivo didattico: distinguere limite, supremo e massimo dell'immagine. -->

---

### **4. Esempi e controesempi**

1. $a_n=n$ è limitata inferiormente da $0$ ma non superiormente.
2. $a_n=-n$ è limitata superiormente da $0$ ma non inferiormente.
3. $a_n=(-1)^n$ è limitata ma non monotona.
4. $a_n=n/(n+1)$ è limitata e crescente.
5. $a_n=(-1)^nn$ non è limitata in alcun verso.

Per dimostrare che $n$ non è limitata superiormente, si nega la definizione:

$$
\forall M\in\mathbb R\ \exists n\in\mathbb N:\ n>M.
$$

La proprietà archimedea dei reali garantisce tale $n$.

---

### **5. Limitatezza definitiva**

Se esiste $N$ tale che $|a_n|\le C$ per ogni $n\ge N$, allora l'intera successione è limitata: basta porre

$$
K=\max\{|a_0|,\ldots,|a_{N-1}|,C\}.
$$

Questa osservazione giustifica perché un numero finito di termini iniziali non altera limitatezza o convergenza.

---

### **6. Parametri**

Consideriamo

$$
a_n=\frac{n+\alpha}{n+1},\qquad n\ge0.
$$

Poiché

$$
a_n=1+\frac{\alpha-1}{n+1},
$$

se $\alpha\ge1$ la successione è compresa tra $1$ e $\alpha$; se $\alpha<1$ è compresa tra $\alpha$ e $1$. In ogni caso è limitata e

$$
\inf A=\min\{1,\alpha\},\qquad
\sup A=\max\{1,\alpha\},
$$

con attenzione: il valore $1$ non è assunto se $\alpha\ne1$.

---

### **7. Errori frequenti**

1. Scegliere un maggiorante dipendente da $n$.
2. Confondere $a_n<M$ con $\sup A<M$.
3. Affermare che il supremo è sempre un termine.
4. Concludere convergenza dalla sola limitatezza.
5. Dimenticare i termini iniziali dopo una stima definitiva.

---

### **8. Esercizi**

1. Trova maggioranti, minoranti, supremo e infimo di $a_n=1/(n+1)$.
2. Stabilisci se massimo e minimo esistono per $a_n=(-1)^n/(n+1)$.
3. Dimostra l'equivalenza delle tre definizioni di limitatezza.
4. Nega formalmente "$(a_n)$ è limitata superiormente".
5. Studia la limitatezza di $(n^2-1)/(n^2+1)$.
6. Determina per quali $\alpha$ la successione $a_n=n/(n+\alpha)$ è ben definita e limitata per ogni $n\ge0$.
7. Costruisci una successione con supremo $2$ non assunto e infimo $-1$ assunto infinite volte.
8. Dimostra che una successione definitivamente limitata è limitata.

---

### **9. Riepilogo**

> ✅ Limitatezza, massimo e supremo riguardano l'immagine della successione; il supremo controlla tutti i termini ma non deve essere assunto.
