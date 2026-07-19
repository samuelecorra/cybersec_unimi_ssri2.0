# **M4 UD1 Lezione 3 - Basi**

### **1. Definizione e significato**

Una **base** di uno spazio vettoriale $V$ è un sistema ordinato di vettori che è contemporaneamente:

- un sistema di generatori di $V$;
- linearmente indipendente.

Di conseguenza, per ogni $v\in V$ esiste un'unica $n$-upla $(\lambda_1,\ldots,\lambda_n)$ tale che

$$
v=\lambda_1v_1+\cdots+\lambda_nv_n.
$$

Questi scalari sono le componenti di $v$ nella base $B=(v_1,\ldots,v_n)$ e si scrive

$$
[v]_B=\begin{bmatrix}\lambda_1\\\vdots\\\lambda_n\end{bmatrix}.
$$

---

### **2. Basi canoniche degli spazi coordinati**

Negli spazi con coordinate naturali si usano basi canoniche.

In $\mathbb R^2$:

$$
e_1=(1,0),\qquad e_2=(0,1).
$$

In $\mathbb R_2[x]$, seguendo l'ordine adottato nelle dispense:

$$
B=(x^2,x,1).
$$

In $M_{2\times2}(\mathbb R)$:

$$
E_{11}=\begin{bmatrix}1&0\\0&0\end{bmatrix},\quad
E_{12}=\begin{bmatrix}0&1\\0&0\end{bmatrix},\quad
E_{21}=\begin{bmatrix}0&0\\1&0\end{bmatrix},\quad
E_{22}=\begin{bmatrix}0&0\\0&1\end{bmatrix}.
$$

Per il sottospazio

$$
S=\{(a,b,a+b):a,b\in\mathbb R\}\subseteq\mathbb R^3
$$

una base è

$$
\bigl((1,0,1),(0,1,1)\bigr),
$$

poiché

$$
(a,b,a+b)=a(1,0,1)+b(0,1,1).
$$

> 💡 “Canonica” non significa che ogni spazio astratto possieda una base privilegiata: il termine si usa quando la struttura coordinata ne fornisce una naturale. Ogni spazio finitamente generato non nullo ammette comunque almeno una base.

---

### **3. Teorema di estrazione**

Se un sistema finito $S=(v_1,\ldots,v_m)$ è linearmente dipendente, almeno un vettore è combinazione lineare degli altri. Più precisamente, dopo un eventuale riordinamento, se

$$
\lambda_1v_1+\cdots+\lambda_m v_m=0,
\qquad \lambda_m\neq0,
$$

allora

$$
v_m=-\lambda_m^{-1}\sum_{i=1}^{m-1}\lambda_iv_i.
$$

Rimuovere $v_m$ non cambia lo span. Ripetendo l'operazione, da ogni sistema finito di generatori si può estrarre una base.

> 📌 **Teorema di estrazione.** Ogni sistema finito di generatori di $V$ contiene una base di $V$.

---

### **4. Dimensione**

Tutte le basi di uno spazio vettoriale finitamente generato hanno lo stesso numero di elementi. Questo numero si chiama **dimensione**:

$$
\dim_KV=n.
$$

In particolare,

$$
\dim\mathbb R^2=2,\qquad
\dim\mathbb R_2[x]=3,\qquad
\dim M_{2\times2}(\mathbb R)=4.
$$

Il sottospazio $S$ dell'esempio precedente ha dimensione $2$.

Se $\dim V=n$:

- ogni sistema indipendente contiene al più $n$ vettori;
- ogni sistema generatore contiene almeno $n$ vettori;
- un sistema di esattamente $n$ vettori è base non appena si verifica una delle due proprietà, indipendenza oppure generazione.

---

### **5. Teorema di completamento**

> 📌 **Teorema di completamento.** Ogni sistema linearmente indipendente di uno spazio finitamente generato può essere completato a una base.

Sia $(v_1,\ldots,v_r)$ indipendente e sia $(w_1,\ldots,w_m)$ un sistema generatore di $V$. Si aggiungono progressivamente i $w_j$ che non appartengono allo span dei vettori già scelti. L'indipendenza si conserva e, al termine, lo span è tutto $V$.

Da estrazione, completamento e dimensione segue una regola pratica molto utile:

> ✅ In uno spazio di dimensione $n$, tra le tre proprietà “$n$ vettori”, “generatori” e “indipendenti”, due implicano la terza.

---

### **6. Procedura per trovare una base**

Per un sottospazio descritto da parametri:

1. scrivere il vettore generico come combinazione dei parametri;
2. raccogliere i vettori moltiplicati dai parametri;
3. eliminare eventuali dipendenze;
4. verificare generazione e indipendenza.

Per un sistema dato, si può invece ridurre la matrice che ha i vettori come colonne e conservare le colonne originali corrispondenti ai pivot.

---

> ✅ Una base è un sistema di coordinate senza ridondanze. La dimensione è il numero, indipendente dalla scelta, di vettori di ogni base.
