# **M5 UD1 Lezione 4 - Sottosuccessioni**

### **1. Definizione formale**

Una sottosuccessione di $(a_n)$ è

$$
(a_{\varphi(k)})_{k\in\mathbb N},
$$

dove

$$
\varphi:\mathbb N\to\mathbb N
$$

è strettamente crescente. Scrivendo $n_k=\varphi(k)$, si ha

$$
n_0<n_1<n_2<\cdots.
$$

La crescita stretta impedisce ripetizioni e ritorni all'indietro.

---

### **2. Proprietà degli indici**

Per ogni funzione strettamente crescente $\varphi:\mathbb N\to\mathbb N$,

$$
\varphi(k)\ge k.
$$

**Dimostrazione.** Per $k=0$ è ovvio. Se $\varphi(k)\ge k$, allora, essendo i valori interi,

$$
\varphi(k+1)\ge\varphi(k)+1\ge k+1.
$$

La tesi segue per induzione. In particolare, $\varphi(k)\to+\infty$.

---

### **3. Esempi**

- Indici pari: $a_{2k}$.
- Indici dispari: $a_{2k+1}$.
- Indici quadratici: $a_{k^2}$.
- Indici primi: $a_{p_k}$, dove $p_k$ è il $k$-esimo primo.
- Classe residua: $a_{pk+r}$ con $0\le r<p$.

Per $a_n=(-1)^n$,

$$
a_{2k}=1,\qquad a_{2k+1}=-1.
$$

<!-- TODO FIGURA: successione (-1)^n con punti pari evidenziati come sottosuccessione costante 1 e dispari come sottosuccessione costante -1, frecce dagli indici originali ai nuovi indici k; obiettivo didattico: distinguere successione, selezione degli indici e due limiti estratti. -->

---

### **4. Sottosuccessioni di una convergente**

**Teorema.** Se $a_n\to L$, allora

$$
a_{\varphi(k)}\to L
$$

per ogni sottosuccessione.

**Dimostrazione.** Dato $\varepsilon>0$, esiste $N$ tale che $n\ge N$ implica $|a_n-L|<\varepsilon$. Per $k\ge N$ vale $\varphi(k)\ge k\ge N$, dunque

$$
|a_{\varphi(k)}-L|<\varepsilon.
$$

Quindi l'estratta converge a $L$. $\square$

> 📌 Due sottosuccessioni con limiti diversi provano che la successione originaria non converge.

---

### **5. Periodiche e definitivamente costanti**

Una successione periodica di periodo $p$ possiede, per ogni resto $r$, la sottosuccessione costante $(a_{pk+r})$. Se la successione converge, tutte queste costanti devono avere lo stesso valore. Quindi:

> **Teorema.** Una successione periodica converge se e solo se è costante sul proprio periodo, cioè è costante.

Una successione definitivamente periodica converge se e solo se è definitivamente costante.

---

### **6. Successioni intere**

**Teorema.** Se $a_n\in\mathbb Z$ e $a_n\to L\in\mathbb R$, allora $L\in\mathbb Z$ e $(a_n)$ è definitivamente costante.

**Dimostrazione.** Con $\varepsilon=1/2$, da un certo indice tutti gli $a_n$ appartengono a $(L-1/2,L+1/2)$. Tale intervallo contiene al più un intero; quindi la coda è costante, uguale a un intero $m$. Necessariamente $L=m$. $\square$

Lo stesso vale per sottoinsiemi discreti di $\mathbb R$ con punti uniformemente separati.

---

### **7. Un unico valore estratto non basta sempre**

Una successione può avere un solo valore di aderenza finito e non convergere. Per esempio,

$$
a_n=
\begin{cases}
0, & n\text{ non è un quadrato},\\
n, & n\text{ è un quadrato}.
\end{cases}
$$

L'unico limite finito di sottosuccessioni è $0$, ma la sottosuccessione $a_{k^2}=k^2$ tende a $+\infty$. Servono limitatezza o controllo dei valori di aderenza estesi.

---

### **8. Errori frequenti**

1. Usare indici non strettamente crescenti.
2. Chiamare sottosuccessione una permutazione di tutti i termini.
3. Confondere $k$ con $\varphi(k)$.
4. Credere che il limite di una sottosuccessione sia sempre il limite dell'originale.
5. Dimenticare che una successione convergente impone lo stesso limite a tutte le estratte.

---

### **9. Esercizi**

1. Verifica che $a_{3k+2}$ è una sottosuccessione e che $a_{k+(-1)^k}$ non lo è.
2. Estrai da $\sin(\pi n/2)$ tutte le classi residue modulo $4$.
3. Dimostra formalmente la non convergenza di $(-1)^n$ mediante due estratte.
4. Classifica le sottosuccessioni $a_{k^2}$ e $a_{2^k}$ di $a_n=1/(n+1)$.
5. Dimostra il teorema sulle successioni periodiche.
6. Mostra che una successione razionale convergente non deve essere definitivamente costante.
7. Costruisci una successione illimitata con una sottosuccessione convergente.
8. Generalizza il teorema delle successioni intere a $h\mathbb Z$, $h>0$.

---

### **10. Riepilogo**

> ✅ Una sottosuccessione avanza lungo indici strettamente crescenti; ogni estratta di una convergente conserva il limite, mentre estratte incompatibili certificano la non convergenza.
