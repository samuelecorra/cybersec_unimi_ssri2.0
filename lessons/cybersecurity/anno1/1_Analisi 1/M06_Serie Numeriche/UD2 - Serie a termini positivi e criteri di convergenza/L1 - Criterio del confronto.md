# **M6 UD2 Lezione 1 - Serie positive e criteri di confronto**

### **1. Serie a termini non negativi**

Una serie è a termini non negativi se $a_n\geq0$ per ogni indice, oppure definitivamente: eventuali eccezioni finite non ne cambiano la natura.

Le somme parziali soddisfano

$$
S_{N+1}-S_N=a_{N+1}\geq0,
$$

quindi sono crescenti.

> **Teorema.** Una serie a termini non negativi converge se e solo se le sue somme parziali sono limitate superiormente.

**Dimostrazione.** Se converge, $(S_N)$ è limitata. Viceversa, se $(S_N)$ è crescente e limitata superiormente, il teorema di convergenza monotona di M05 dà

$$
S_N\to\sup_N S_N<+\infty.
$$

$\square$

Se le somme parziali non sono limitate, la monotonia implica $S_N\to+\infty$. Una serie non negativa non può divergere per oscillazione.

### **2. Confronto diretto**

> **Teorema.** Siano $0\leq a_n\leq b_n$ definitivamente. Allora:

1. se $\sum b_n$ converge, converge $\sum a_n$;
2. se $\sum a_n$ diverge, diverge $\sum b_n$.

**Dimostrazione.** Eliminati i primi termini, le somme parziali soddisfano

$$
0\leq A_N\leq B_N.
$$

Se $(B_N)$ è limitata superiormente, lo è $(A_N)$ e la serie minore converge. La seconda implicazione è la contronominale della prima, oppure segue dal fatto che somme parziali illimitate della serie minore rendono illimitate quelle della maggiore. $\square$

<!-- TODO FIGURA: due successioni di somme parziali A_N e B_N per termini 0<=a_n<=b_n, con A_N sotto B_N e B_N limitata da una quota M; in un secondo pannello mostrare A_N illimitata che forza B_N. Obiettivo didattico: visualizzare le due sole direzioni valide del confronto diretto. -->

Le direzioni opposte sono false. Da $a_n\leq b_n$ e dalla divergenza di $\sum b_n$ non segue nulla su $\sum a_n$: si confrontino $a_n=1/n^2$ e $b_n=1/n$.

### **3. Confronto sui valori assoluti**

Per termini di segno qualunque, se

$$
|a_n|\leq b_n,
\qquad b_n\geq0,
$$

e $\sum b_n$ converge, allora $\sum|a_n|$ converge per confronto e $\sum a_n$ converge assolutamente. Il confronto diretto sui termini con segno non autorizza invece conclusioni sulle cancellazioni.

### **4. Confronto asintotico**

Siano $a_n,b_n>0$ definitivamente e

$$
\frac{a_n}{b_n}\to L,
\qquad 0<L<+\infty.
$$

Scelto $\varepsilon=L/2$, definitivamente

$$
\frac L2 b_n\leq a_n\leq\frac{3L}{2}b_n.
$$

I due confronti diretti mostrano che $\sum a_n$ e $\sum b_n$ hanno la stessa natura. In particolare,

$$
a_n\sim b_n
\quad\Longrightarrow\quad
\sum a_n\text{ e }\sum b_n\text{ hanno la stessa natura},
$$

sempre sotto positività definitiva.

### **5. Casi $L=0$ e $L=+\infty$**

Se $a_n/b_n\to0$, allora definitivamente $a_n\leq b_n$ a meno di una costante. Ne seguono soltanto:

- $\sum b_n$ convergente $\Rightarrow\sum a_n$ convergente;
- $\sum a_n$ divergente $\Rightarrow\sum b_n$ divergente.

La divergenza di $\sum b_n$ non decide $\sum a_n$.

Se $a_n/b_n\to+\infty$, si scambiano i ruoli:

- $\sum b_n$ divergente $\Rightarrow\sum a_n$ divergente;
- $\sum a_n$ convergente $\Rightarrow\sum b_n$ convergente.

### **6. Scelta della serie modello**

Si identifica il termine dominante e lo si collega a una famiglia nota:

- rapporti di polinomi: $1/n^p$;
- esponenziali: geometriche;
- fattoriali e prodotti: rapporto;
- potenze $n$-esime: radice;
- logaritmi al denominatore: condensazione;
- differenze: telescopaggio o razionalizzazione;
- oscillazioni limitate: valori assoluti, Dirichlet o Abel.

Non basta una somiglianza grafica: occorre una disuguaglianza definitiva o un limite di rapporto.

### **7. Esempi completi**

Per

$$
a_n=\frac{3n^2+1}{n^4-2n},
$$

il denominatore è positivo definitivamente e

$$
a_n\sim\frac3{n^2}.
$$

La serie converge.

Per $p\in\mathbb R$,

$$
\sum_{n\geq2}\frac{\ln n}{n^p}
$$

converge se $p>1$: scelto $0<\delta<p-1$, M05 fornisce $\ln n=o(n^\delta)$, quindi il termine è dominato da $1/n^{p-\delta}$ con esponente maggiore di $1$. Se $p\leq1$, definitivamente $\ln n/n^p\geq1/n$, quindi diverge.

Dal limite notevole di M05,

$$
1-\cos\frac1n\sim\frac1{2n^2};
$$

la relativa serie converge. La giustificazione completa del limite trigonometrico è coordinata con M08, ma non si usa qui un'approssimazione priva di statuto.

### **8. Problema parametrico**

Consideriamo

$$
\sum_{n=1}^{\infty}\frac{n^\alpha}{2^n}.
$$

Per ogni $\alpha\in\mathbb R$, M05 stabilisce che ogni potenza è trascurabile rispetto all'esponenziale. Più direttamente, il criterio del rapporto darà limite $1/2$. La serie converge per ogni parametro reale.

Questo esempio mostra che la serie di confronto va scelta sulla crescita dominante, non sul solo fattore $n^\alpha$.

### **9. Errori frequenti**

1. Invertire le implicazioni del confronto.
2. Omettere la positività o il valore assoluto.
3. Usare $a_n\sim b_n$ quando $b_n$ cambia segno.
4. Concludere «stessa natura» quando il rapporto tende a $0$ o a $+\infty$.
5. Sostituire equivalenti dentro differenze con cancellazione.
6. Citare una serie modello senza verificarne indice, dominio e parametro di frontiera.

### **10. Esercizi**

1. Dimostrare il teorema delle somme parziali limitate per termini definitivamente non negativi.
2. Studiare $\sum1/(n^2+n)$ sia per confronto sia per telescopaggio.
3. Classificare $\sum1/(\sqrt n+1)$.
4. Studiare $\sum(5n^3-1)/(n^5+4)$ per confronto asintotico.
5. Fornire controesempi per entrambe le direzioni non valide del confronto.
6. Classificare $\sum(\ln n)^3/n^p$ al variare di $p$.
7. Studiare la convergenza assoluta di $\sum(-1)^n\sin(1/n)/n$.
8. Stabilire quali conclusioni sono possibili quando $a_n/b_n\to0$ e $\sum b_n$ diverge.
9. Confrontare $n!/n^n$ con una successione geometrica usando un rapporto consecutivo.
10. Progettare una serie positiva per cui il confronto diretto con $1/n$ sia vero ma inutile.

### **11. Riepilogo**

> ✅ Per serie non negative, convergenza equivale a limitatezza delle somme parziali. Il confronto diretto ha direzioni precise; il confronto asintotico dà la stessa natura soltanto quando il rapporto tende a una costante finita e positiva.
