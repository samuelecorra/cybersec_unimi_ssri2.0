# **M6 UD2 Lezione 2 - Criterio del rapporto**

### **1. Enunciato con limite**

Sia $a_n\neq0$ definitivamente e supponiamo che esista

$$
L=\lim_{n\to\infty}\left|\frac{a_{n+1}}{a_n}\right|.
$$

Allora:

- se $L<1$, $\sum a_n$ converge assolutamente;
- se $L>1$ oppure $L=+\infty$, la serie diverge;
- se $L=1$, il criterio è inconcludente.

Il valore assoluto consente di trattare serie reali o complesse di segno o argomento variabile.

### **2. Dimostrazione della convergenza**

Se $L<1$, scegliamo $q$ con

$$
L<q<1.
$$

Per definizione di limite esiste $N$ tale che per $n\geq N$

$$
|a_{n+1}|\leq q|a_n|.
$$

Iterando,

$$
|a_{N+k}|\leq |a_N|q^k.
$$

La coda di $\sum|a_n|$ è dominata da una geometrica convergente; il confronto prova la convergenza assoluta. $\square$

### **3. Dimostrazione della divergenza**

Se $L>1$, scegliamo $q$ con $1<q<L$. Definitivamente

$$
|a_{n+1}|\geq q|a_n|,
$$

quindi $|a_n|$ non tende a zero, anzi cresce almeno geometricamente dalla soglia in poi. Il test del termine generale implica divergenza. Lo stesso vale per $L=+\infty$. $\square$

> ⚠️ Il caso $L=1$ non significa divergenza: $\sum1/n$ diverge e $\sum1/n^2$ converge, ma in entrambi i casi il rapporto tende a $1$.

### **4. Formulazione mediante limite superiore**

È sufficiente che

$$
\limsup_{n\to\infty}\left|\frac{a_{n+1}}{a_n}\right|<1
$$

per ottenere convergenza assoluta: si sceglie $q<1$ maggiore del limite superiore e si ripete la maggiorazione geometrica.

Per la divergenza, la sola condizione $\limsup>1$ non basta, perché rapporti grandi possono alternarsi con rapporti molto piccoli. Una condizione sufficiente è

$$
\liminf_{n\to\infty}\left|\frac{a_{n+1}}{a_n}\right|>1.
$$

Se i termini sono nulli infinite volte, il rapporto può non essere definito. In tal caso si usa direttamente un confronto, il criterio della radice o si separa una sottoserie appropriata; non si assegnano valori artificiali ai rapporti $0/0$.

### **5. Esempi con fattoriali**

Per

$$
a_n=\frac{n!}{n^n},
$$

si ha

$$
\frac{a_{n+1}}{a_n}
=\left(\frac{n}{n+1}\right)^n\to e^{-1}<1.
$$

La serie converge.

Per

$$
a_n=\frac{3^n}{n!},
$$

$$
\frac{a_{n+1}}{a_n}=\frac3{n+1}\to0,
$$

quindi converge.

Infine,

$$
a_n=\frac{(n!)^2}{(2n)!}
$$

soddisfa

$$
\frac{a_{n+1}}{a_n}
=\frac{(n+1)^2}{(2n+2)(2n+1)}\to\frac14,
$$

perciò la relativa serie converge.

### **6. Esempio parametrico e caso di frontiera**

Sia $c>0$ e

$$
a_n=\frac{n^n}{n!c^n}.
$$

Allora

$$
\frac{a_{n+1}}{a_n}
=\frac1c\left(1+\frac1n\right)^n\to\frac ec.
$$

La serie converge per $c>e$ e diverge per $0<c<e$. Per $c=e$ il rapporto tende a $1$ e il criterio non decide.

La formula di Stirling, registrata come approfondimento,

$$
n!\sim\sqrt{2\pi n}\left(\frac ne\right)^n,
$$

fornisce nel caso $c=e$

$$
a_n\sim\frac1{\sqrt{2\pi n}},
$$

quindi divergenza. La formula è enunciata con precisione, ma la sua dimostrazione richiede strumenti ulteriori e non viene simulata in M06.

### **7. Rapporto e stima della coda**

Se si dimostra che per $n\geq N$

$$
|a_{n+1}|\leq q|a_n|,
\qquad q<1,
$$

allora, oltre alla convergenza,

$$
\left|\sum_{n=N+1}^{\infty}a_n\right|
\leq\frac{|a_{N+1}|}{1-q}.
$$

Il rapporto può dunque produrre una stima pratica del resto, purché il medesimo $q$ valga per tutta la coda.

### **8. Errori frequenti**

1. Omettere il valore assoluto per serie a segno variabile.
2. Dividere per termini nulli.
3. Concludere quando $L=1$.
4. Credere che $\limsup>1$ implichi divergenza.
5. Usare un rapporto minore di $1$ soltanto per pochi indici.
6. Confondere il limite del rapporto con la ragione esatta di una geometrica.
7. Usare Stirling come se fosse stato dimostrato nel modulo.

### **9. Esercizi**

1. Ricostruire la prova geometrica del caso $L<1$.
2. Studiare $\sum n!/5^n$.
3. Studiare $\sum 5^n/n!$ e fornire una stima della coda.
4. Classificare $\sum(n!)^2/(3n)!$.
5. Studiare $\sum n^4/2^n$ al variare dell'esponente $4$ sostituito da $\alpha\in\mathbb R$.
6. Costruire una successione di termini positivi con $\limsup$ del rapporto maggiore di $1$ ma serie convergente.
7. Spiegare perché il rapporto non è applicabile direttamente a una serie con infiniti termini nulli.
8. Classificare $\sum n^n/(n!c^n)$ per $c\neq e$ e discutere separatamente $c=e$.
9. Verificare la conseguenza di Stirling per $n!/n^n$ senza usarla nella prova principale.
10. Confrontare la stima di resto ottenuta dal rapporto con quella geometrica esatta in un caso semplice.

### **10. Riepilogo**

> ✅ Il rapporto rileva decadimento geometrico consecutivo. La convergenza richiede un controllo definitivamente sotto $1$; la divergenza richiede crescita definitivamente sopra $1$ o il limite ordinario maggiore di $1$. Il valore di frontiera resta realmente inconcludente.
