# **M6 UD1 Lezione 3A - Condensazione di Cauchy e serie logaritmiche**

### **1. Perché condensare**

Quando $(a_n)$ è non negativa e decrescente, un intero blocco di termini ha ordine confrontabile con il primo o l'ultimo termine del blocco. La condensazione sostituisce blocchi di lunghezza esponenziale con un solo termine pesato.

### **2. Criterio di condensazione di Cauchy**

> **Teorema.** Se $a_n\geq0$ ed $(a_n)$ è decrescente per $n\geq1$, allora

$$
\sum_{n=1}^{\infty}a_n
\quad\text{e}\quad
\sum_{k=0}^{\infty}2^k a_{2^k}
$$

hanno la stessa natura.

**Dimostrazione.** Nel blocco $2^k\leq n<2^{k+1}$ vi sono $2^k$ termini. Per monotonia,

$$
2^k a_{2^{k+1}}
\leq
\sum_{n=2^k}^{2^{k+1}-1}a_n
\leq
2^k a_{2^k}.
$$

Se la serie condensata converge, la disuguaglianza superiore e il confronto mostrano che la somma dei blocchi converge. Viceversa, la disuguaglianza inferiore contiene, a meno del fattore $1/2$ e di uno slittamento dell'indice, i termini

$$
2^{k+1}a_{2^{k+1}};
$$

se la serie originale converge, converge anche la condensata. Le modifiche finite non cambiano la natura. $\square$

<!-- TODO FIGURA: disporre i termini a_n in blocchi [1,2), [2,4), [4,8), [8,16); sopra ogni blocco indicare i maggioranti e minoranti 2^k a_{2^k} e 2^k a_{2^{k+1}}. Obiettivo didattico: visualizzare entrambe le implicazioni della condensazione di Cauchy. -->

> ⚠️ Non basta la positività: la monotonia è l'ipotesi che rende valide le stime uniformi all'interno di ogni blocco.

### **3. Nuova prova per le $p$-serie**

Per $a_n=1/n^p$ con $p>0$, la serie condensata è

$$
\sum_{k=0}^{\infty}2^k\frac1{(2^k)^p}
=
\sum_{k=0}^{\infty}2^{k(1-p)}.
$$

È geometrica e converge se e solo se $p>1$. Per $p\leq0$ il termine generale non tende a zero. Ritroviamo quindi la classificazione completa.

### **4. Serie di Bertrand**

Per $n\geq2$ consideriamo

$$
\sum_{n=2}^{\infty}\frac1{n(\ln n)^p}.
$$

Il termine è definitivamente decrescente e la condensata vale

$$
\sum_{k}\frac{2^k}{2^k(\ln 2^k)^p}
=
\frac1{(\ln2)^p}\sum_k\frac1{k^p}.
$$

Pertanto

$$
\sum_{n=2}^{\infty}\frac1{n(\ln n)^p}
\text{ converge se e solo se }p>1.
$$

I primi indici per cui $\ln n=0$ o l'espressione non è definita vengono esclusi esplicitamente; modificarli non cambia la natura.

### **5. Un secondo livello logaritmico**

Come approfondimento, per $n$ sufficientemente grande,

$$
\sum\frac1{n\ln n(\ln\ln n)^p}
$$

si condensa in una serie equivalente, a costanti moltiplicative vicino,

$$
\sum\frac1{k(\ln k)^p}.
$$

Di conseguenza converge per $p>1$ e diverge per $p\leq1$. Questo esempio mostra che ogni fattore logaritmico può creare un nuovo caso di frontiera; non serve sviluppare qui una gerarchia iterata più lunga.

### **6. Condensazione e criterio integrale**

La condensazione è interamente discreta e usa solo monotonia e confronto. Il criterio integrale conduce alle stesse classificazioni, ma la sua dimostrazione naturale appartiene agli integrali impropri di M11. Non viene usato come giustificazione nascosta in M06.

### **7. Scelta del metodo**

La condensazione è efficace quando:

- i termini sono non negativi e decrescenti;
- compaiono potenze di $n$ e logaritmi;
- rapporto e radice tendono a $1$;
- il raggruppamento dyadico semplifica il termine.

Non è appropriata per termini oscillanti senza passare ai valori assoluti, né per successioni non monotone senza una riduzione giustificata.

### **8. Errori frequenti**

1. Dimenticare la monotonia.
2. Confondere $2^ka_{2^k}$ con $a_{2^k}$.
3. Usare blocchi con estremi incoerenti e contare $2^k+1$ termini.
4. Applicare la classificazione logaritmica a partire da $n=1$.
5. Dedurre una somma numerica dalla condensazione: il criterio determina la natura, non il valore.
6. Usare il criterio integrale prima di averne dichiarato le ipotesi.

### **9. Esercizi**

1. Ricostruire entrambe le disuguaglianze sui blocchi dyadici.
2. Applicare la condensazione a $\sum1/\sqrt n$.
3. Classificare $\sum1/[n(\ln n)^2]$ e $\sum1/[n\sqrt{\ln n}]$.
4. Classificare $\sum1/[n\ln n(\ln\ln n)^3]$.
5. Spiegare perché $a_n=(2+(-1)^n)/n$ non soddisfa direttamente le ipotesi.
6. Costruire una successione positiva non monotona per cui la condensata lungo $2^k$ perde informazione essenziale.
7. Confrontare condensazione, rapporto e radice sulla $p$-serie.
8. Dimostrare la monotonia definitiva di $1/[n(\ln n)^p]$ per un valore fissato $p>0$ senza usare il criterio integrale.

### **10. Riepilogo**

> ✅ La condensazione di Cauchy converte blocchi dyadici in una serie pesata e decide $p$-serie e serie logaritmiche proprio nei casi in cui rapporto e radice sono inconcludenti.
