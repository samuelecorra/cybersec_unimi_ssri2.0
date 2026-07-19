# **M5 UD5 Lezione 1 - Successioni infinitesime**

### **1. Definizione e significato**

Una successione $(a_n)$ è **infinitesima** se

$$
a_n\to0.
$$

Equivalentemente, per ogni $\varepsilon>0$ esiste $N\in\mathbb{N}$ tale che

$$
n\geq N\quad\Longrightarrow\quad |a_n|<\varepsilon.
$$

Il termine «infinitesimo» descrive un comportamento al limite: i termini non devono essere nulli. Per esempio $1/(n+1)$ è sempre positivo, ma è infinitesimo.

> 📌 Ogni successione infinitesima è limitata, perché ogni successione convergente è limitata.

### **2. Algebra degli infinitesimi**

Se $a_n\to0$, $b_n\to0$ e $(c_n)$ è limitata, allora

$$
a_n+b_n\to0,
\qquad
a_nb_n\to0,
\qquad
a_nc_n\to0.
$$

L'ultima proprietà segue dal teorema dei carabinieri: se $|c_n|\leq M$ definitivamente, allora

$$
|a_nc_n|\leq M|a_n|\to0.
$$

Il quoziente di due infinitesimi non ha invece un comportamento determinato:

$$
\frac{1/n}{1/n}\to1,
\qquad
\frac{1/n^2}{1/n}\to0,
\qquad
\frac{1/n}{1/n^2}\to+\infty.
$$

### **3. Reciproco e successioni infinite**

Se $a_n\neq0$ definitivamente e $a_n\to0$, allora $|1/a_n|\to+\infty$. Per concludere il segno del limite occorre conoscere il segno definitivo di $a_n$:

$$
a_n>0\text{ definitivamente}\Longrightarrow \frac1{a_n}\to+\infty,
$$

$$
a_n<0\text{ definitivamente}\Longrightarrow \frac1{a_n}\to-\infty.
$$

Se $a_n=(-1)^n/(n+1)$, il reciproco ha modulo divergente ma cambia segno, quindi non tende né a $+\infty$ né a $-\infty$.

### **4. Il simbolo $o$ piccolo**

Siano $(a_n)$ e $(b_n)$ successioni reali con $b_n\neq0$ definitivamente. Si scrive

$$
a_n=o(b_n)
$$

se

$$
\frac{a_n}{b_n}\to0.
$$

Ciò significa che $a_n$ è trascurabile rispetto a $b_n$ alla scala considerata. Non è necessario che entrambe siano infinitesime: per esempio

$$
n=o(n^2).
$$

Proprietà immediate:

$$
a_n=o(b_n),\ c_n=o(b_n)
\Longrightarrow
a_n+c_n=o(b_n),
$$

e, se $d_n=O(1)$,

$$
a_n=o(b_n)\Longrightarrow d_na_n=o(b_n).
$$

Inoltre $a_n=o(b_n)$ implica $a_n=O(b_n)$, ma non vale il contrario.

### **5. Equivalenza asintotica**

Se $b_n\neq0$ definitivamente, si scrive

$$
a_n\sim b_n
$$

quando

$$
\frac{a_n}{b_n}\to1.
$$

La relazione è equivalente a

$$
a_n=b_n+o(b_n).
$$

Infatti

$$
\frac{a_n-b_n}{b_n}=\frac{a_n}{b_n}-1\to0.
$$

L'equivalenza asintotica è riflessiva, simmetrica e transitiva nel dominio delle successioni definitivamente non nulle; è dunque una relazione di equivalenza.

Esempi provenienti dai limiti notevoli sono

$$
\sin\frac1n\sim\frac1n,
\qquad
1-\cos\frac1n\sim\frac{1}{2n^2}.
$$

### **6. Sostituzione degli equivalenti**

Se $a_n\sim b_n$ e $c_n\sim d_n$, con denominatori definitivamente non nulli, allora

$$
a_nc_n\sim b_nd_n,
\qquad
\frac{a_n}{c_n}\sim\frac{b_n}{d_n}.
$$

La prova usa il prodotto dei rapporti:

$$
\frac{a_nc_n}{b_nd_n}=\frac{a_n}{b_n}\frac{c_n}{d_n}\to1.
$$

Nelle somme e differenze la sostituzione non è automatica, perché può cancellare il termine dominante. Per esempio $n+1\sim n$, ma sottraendo $n$ si ottengono

$$
(n+1)-n=1,
\qquad
n-n=0,
$$

che non sono equivalenti. Prima di sostituire occorre fattorizzare o conoscere un termine asintotico di ordine successivo.

### **7. Ordine di infinitesimo**

Rispetto alla scala $1/n$, la successione $1/n^p$ con $p>1$ è di ordine superiore, perché

$$
\frac{1/n^p}{1/n}=\frac{1}{n^{p-1}}\to0.
$$

L'espressione «ordine superiore» per un infinitesimo significa quindi che tende a zero più rapidamente. È importante specificare sempre il termine di confronto.

### **8. Errori frequenti**

- Confondere «infinitesimo» con «termine uguale a zero».
- Scrivere $1/a_n\to+\infty$ senza controllare il segno di $a_n$.
- Definire $a_n=o(b_n)$ senza richiedere $b_n\neq0$ definitivamente.
- Sostituire equivalenti dentro una differenza soggetta a cancellazione.
- Interpretare $o(b_n)$ come una successione particolare: indica una classe di resti rispetto a $b_n$.

### **9. Esercizi**

1. Verificare con la definizione che $(-1)^n/(n+1)$ è infinitesima.
2. Dimostrare direttamente che il prodotto di una successione infinitesima e una limitata è infinitesimo.
3. Classificare il reciproco di $1/(n+1)$, $-1/(n+1)$ e $(-1)^n/(n+1)$.
4. Stabilire quali relazioni sono vere tra $1/n^3$, $1/n^2$ e $1/n$ usando $o$.
5. Dimostrare l'equivalenza $a_n\sim b_n\Longleftrightarrow a_n=b_n+o(b_n)$.
6. Calcolare $\lim \frac{\sin(1/n)}{1/n+1/n^2}$ mediante equivalenti e poi con un raccoglimento esatto.
7. Costruire un controesempio alla sostituzione di equivalenti in una differenza usando successioni infinitesime.
8. Stabilire se $a_n=o(b_n)$ implica sempre $a_n\to0$; motivare con un esempio.

### **10. Riepilogo**

> ✅ Gli infinitesimi si confrontano mediante rapporti: $o$ esprime trascurabilità, mentre $\sim$ esprime uguaglianza del termine principale. La sostituzione degli equivalenti è affidabile in prodotti e quozienti, non automaticamente nelle differenze.
