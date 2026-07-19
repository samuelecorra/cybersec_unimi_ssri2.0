# **M4 UD2 Lezione 7 - Determinanti**

### **1. Definizione assiomatica**

Fissata una base ordinata di uno spazio $V$ di dimensione $n$, il **determinante** è l'unica applicazione

$$
\det:V^n\longrightarrow K
$$

che è:

- multilineare nelle $n$ variabili;
- alternante, cioè vale $0$ quando due argomenti coincidono;
- normalizzata a $1$ sulla base ordinata fissata.

Per una matrice quadrata $A$, $\det A$ è il determinante delle sue colonne nella base canonica.

> 📌 La normalizzazione riguarda una base di riferimento ordinata. Il determinante di una base arbitraria è in generale non nullo, ma non necessariamente uguale a $1$.

---

### **2. Proprietà operative**

Dalla multilinearità e dall'alternanza seguono:

- scambiare due righe o due colonne cambia il segno;
- moltiplicare una riga o colonna per $\lambda$ moltiplica il determinante per $\lambda$;
- aggiungere a una riga o colonna un multiplo di un'altra non cambia il determinante;
- una riga o colonna nulla rende il determinante nullo;
- righe o colonne dipendenti rendono il determinante nullo;
- per una matrice triangolare, il determinante è il prodotto degli elementi diagonali;
- $\det A=\det A^T$.

Inoltre:

$$
\det(AB)=\det A\,\det B
$$

è il **teorema di Binet**. Se $A$ è invertibile,

$$
\det(A^{-1})=(\det A)^{-1}.
$$

Per l'inversione valgono anche

$$
(A^{-1})^{-1}=A,
\qquad
(AB)^{-1}=B^{-1}A^{-1}.
$$

L'ordine dell'ultimo prodotto si inverte, come si verifica da

$$
(B^{-1}A^{-1})(AB)=B^{-1}(A^{-1}A)B=I.
$$

Una matrice quadrata è invertibile se e solo se

$$
\det A\neq0.
$$

> ⚠️ Non esiste una formula analoga $\det(A+B)=\det A+\det B$.

---

### **3. Sviluppo di Laplace**

Eliminando la riga $i$ e la colonna $j$ da $A$ si ottiene il minore $M_{ij}$. Il **cofattore** è

$$
C_{ij}=(-1)^{i+j}\det M_{ij}.
$$

Lo sviluppo lungo la riga $i$ è

$$
\det A=\sum_{j=1}^na_{ij}C_{ij},
$$

e lungo la colonna $j$ è

$$
\det A=\sum_{i=1}^na_{ij}C_{ij}.
$$

Conviene scegliere una riga o colonna ricca di zeri.

---

### **4. Esercizio di calcolo**

Calcoliamo

$$
D=\det
\begin{bmatrix}
1&3&1&0\\
0&2&3&1\\
1&1&4&-1\\
2&-1&0&3
\end{bmatrix}.
$$

Applichiamo operazioni che non cambiano il determinante:

$$
R_3\leftarrow R_3-R_1,
\qquad
R_4\leftarrow R_4-2R_1.
$$

Otteniamo

$$
D=\det
\begin{bmatrix}
1&3&1&0\\
0&2&3&1\\
0&-2&3&-1\\
0&-7&-2&3
\end{bmatrix}.
$$

Sviluppando lungo la prima colonna e riducendo il determinante $3\times3$:

$$
D=\det
\begin{bmatrix}
2&3&1\\
-2&3&-1\\
-7&-2&3
\end{bmatrix}.
$$

Ora applichiamo

$$
R_2\leftarrow R_2+R_1,
\qquad
R_3\leftarrow2R_3+7R_1.
$$

La seconda operazione moltiplica prima la terza riga per $2$, quindi il nuovo determinante è $2D$. Si ottiene

$$
2D=det
\begin{bmatrix}
2&3&1\\
0&6&0\\
0&17&13
\end{bmatrix}
=6\det\begin{bmatrix}2&1\\0&13\end{bmatrix}
=156.
$$

Pertanto

$$
\boxed{\det A=78}.
$$

> ⚠️ L'annotazione manoscritta della pagina sorgente conclude $8(6+7)=104$, ma durante la riduzione altera alcuni elementi della matrice. Il ricalcolo sulla matrice stampata, verificato sia con Gauss sia con Laplace, dà $78$.

---

### **5. Strategia di calcolo**

1. Cercare righe o colonne con zeri.
2. Usare somme di multipli per creare altri zeri.
3. Registrare scambi e fattori estratti.
4. Usare Laplace soltanto dopo aver semplificato.
5. Controllare il risultato con una seconda riduzione triangolare.

---

> ✅ Il determinante rileva l'indipendenza delle colonne e l'invertibilità. Le sue proprietà trasformano un calcolo combinatorio in una riduzione controllata.
