# **M6 UD1 Lezione 1 - Fondamenti delle serie numeriche**

### **1. Dalla successione alla serie**

Sia $(a_n)_{n\geq n_0}$ una successione reale o complessa. La scrittura formale

$$
\sum_{n=n_0}^{\infty}a_n
$$

indica la **serie associata** alla successione. Non si attribuisce direttamente un valore a una somma con infiniti addendi: si introducono le somme finite

$$
S_N:=\sum_{n=n_0}^{N}a_n,
\qquad N\geq n_0,
$$

e si studia la successione $(S_N)_{N\geq n_0}$.

> 📌 La serie è un oggetto definito attraverso una successione: converge esattamente quando converge la successione delle somme parziali.

Il prerequisito formale è la [definizione di successione](../../M05_Successioni/UD1%20-%20Definizioni%20ed%20esempi/L1%20-%20Intro%20alle%20successioni.md) sviluppata in M05; qui la si applica alla particolare successione $(S_N)$.

Occorre distinguere:

- $(a_n)$, successione dei termini;
- $S_N$, singola somma parziale;
- $(S_N)$, successione delle somme parziali;
- $\sum a_n$, serie formale;
- $S$, eventuale somma della serie.

<!-- TODO FIGURA: piano con indice N sull'asse orizzontale e somme parziali S_N sull'asse verticale; rappresentare una successione convergente di somme parziali, una crescente verso +infinito e una oscillante. Evidenziare che i punti sono discreti e che il limite appartiene alla successione S_N, non ai termini a_n. Obiettivo didattico: definire la natura di una serie tramite il comportamento delle somme parziali. -->

### **2. Convergenza, somma e divergenza**

La serie converge a $S\in\mathbb R$ o $S\in\mathbb C$ se

$$
S_N\to S.
$$

Soltanto dopo aver stabilito la convergenza si scrive

$$
\sum_{n=n_0}^{\infty}a_n=S.
$$

Se $(S_N)$ non converge a un valore finito, la serie diverge. Nei reali può accadere che $S_N\to+\infty$, che $S_N\to-\infty$ oppure che le somme parziali non abbiano limite neppure esteso.

Per esempio, per $a_n=1$ e $n_0=1$,

$$
S_N=N\to+\infty;
$$

per $a_n=(-1)^n$, le somme parziali oscillano tra due valori.

<!-- TODO FIGURA: due righe coordinate sullo stesso asse degli indici: in alto i termini a_n di una serie geometrica con segno alterno, in basso le corrispondenti somme parziali S_N che convergono; collegare ogni termine all'incremento S_N-S_{N-1}. Obiettivo didattico: impedire la confusione tra termini della serie e somme parziali. -->

### **3. Indice iniziale e cambi di indice**

L'indice iniziale fa parte della definizione. Le serie

$$
\sum_{n=0}^{\infty}a_n
\quad\text{e}\quad
\sum_{n=1}^{\infty}a_n
$$

differiscono del termine $a_0$, quando entrambe sono definite.

Un cambio di indice deve trasformare insieme termine ed estremi. Ponendo $k=n-n_0$,

$$
\sum_{n=n_0}^{N}a_n
=
\sum_{k=0}^{N-n_0}a_{k+n_0}.
$$

Per esempio,

$$
\sum_{n=3}^{N}\frac1{n(n+1)}
=
\sum_{k=0}^{N-3}\frac1{(k+3)(k+4)}.
$$

### **4. Modifiche finite e code**

> **Teorema.** Modificare, aggiungere o eliminare un numero finito di termini non cambia la convergenza o la divergenza di una serie.

**Dimostrazione.** Supponiamo che due serie abbiano termini $a_n$ e $b_n$ coincidenti per $n\geq N_0$. Per $N\geq N_0$ le somme parziali soddisfano

$$
S_N-T_N=C,
$$

dove $C$ è la differenza fissa accumulata nei primi termini. Dunque $(S_N)$ converge se e solo se converge $(T_N)$; se convergono, le somme differiscono di $C$. La stessa identità preserva divergenza a infinito e oscillazione. $\square$

Applicando il teorema a $|a_n|$ e $|b_n|$, anche la convergenza assoluta è invariata. Di conseguenza resta invariata la convergenza condizionata: la serie continua a convergere, mentre la serie dei valori assoluti continua a divergere.

La **coda** a partire da $m$ è la serie

$$
\sum_{n=m}^{\infty}a_n.
$$

La serie completa converge se e solo se converge una sua qualunque coda.

### **5. Condizione necessaria sui termini**

> **Teorema.** Se $\sum_{n=n_0}^{\infty}a_n$ converge, allora $a_n\to0$.

**Dimostrazione.** Per $n>n_0$,

$$
a_n=S_n-S_{n-1}.
$$

Se $S_n\to S$, anche la successione traslata $S_{n-1}\to S$. Per l'algebra dei limiti,

$$
a_n\to S-S=0.
$$

$\square$

La contronominale è il **test di divergenza del termine generale**:

$$
a_n\not\to0
\quad\Longrightarrow\quad
\sum a_n\text{ diverge}.
$$

Il converso è falso: $1/n\to0$, ma la serie armonica diverge. Il test decide la divergenza quando fallisce il limite nullo; quando $a_n\to0$ non fornisce alcuna conclusione.

### **6. Resto ed errore di troncamento**

Se la serie converge a $S$, il **resto dopo l'indice $N$** è

$$
R_N:=S-S_N
=
\sum_{n=N+1}^{\infty}a_n.
$$

Per definizione $R_N\to0$. Il numero $|R_N|$ è l'errore assoluto commesso sostituendo $S$ con $S_N$. Se $S\neq0$, l'errore relativo è

$$
\frac{|R_N|}{|S|};
$$

nelle applicazioni $S$ può essere ignoto, perciò si usa spesso una maggiorazione calcolabile

$$
|R_N|\leq E_N.
$$

Una stima non è necessariamente un'uguaglianza e un termine piccolo non garantisce da solo un resto piccolo.

### **7. Algebra delle serie convergenti**

Siano

$$
\sum a_n=A,
\qquad
\sum b_n=B,
$$

con lo stesso indice iniziale dopo eventuali modifiche finite. Per $\alpha,\beta$ scalari,

$$
\sum(\alpha a_n+\beta b_n)=\alpha A+\beta B.
$$

Infatti le somme parziali della combinazione lineare sono esattamente

$$
\sum_{n=n_0}^{N}(\alpha a_n+\beta b_n)
=\alpha S_N+\beta T_N,
$$

e il risultato segue dall'algebra dei limiti. La proprietà si estende per induzione a ogni combinazione lineare finita.

Questa algebra non autorizza a moltiplicare due serie termine a termine. Il prodotto di serie richiede il prodotto di Cauchy e ipotesi specifiche.

### **8. Applicazioni discrete**

Le serie compaiono quando si accumulano costi o errori. Se un algoritmo svolge al passo $k$ un lavoro $n/2^k$, il costo fino al passo $m$ è

$$
n\sum_{k=0}^{m}\frac1{2^k}<2n.
$$

La stima è una somma geometrica finita; il limite della serie spiega il controllo uniforme al crescere del numero dei livelli. In calcolo numerico, invece, una serie convergente può convergere lentamente e l'arrotondamento può accumularsi: convergenza matematica e affidabilità floating point sono problemi distinti.

### **9. Errori frequenti**

1. Confondere $a_n$ con $S_n$.
2. Chiamare «somma» una serie prima di averne provato la convergenza.
3. Dimenticare l'indice iniziale in un cambio di variabile.
4. Concludere convergenza dalla sola condizione $a_n\to0$.
5. Scrivere $R_N=\sum_{n=N}^{\infty}a_n$: il primo termine trascurato è $a_{N+1}$.
6. Credere che modifiche finite lascino invariata anche la somma.
7. Confondere una stima del resto con il valore esatto del resto.

### **10. Esercizi**

1. Per $a_n=2^{-n}$, calcolare $S_0,S_1,S_2,S_3$ e distinguere termini e somme parziali.
2. Riscrivere $\sum_{n=5}^{\infty}1/n^2$ con indice iniziale $0$.
3. Dimostrare formalmente che una coda converge se e solo se converge la serie completa.
4. Stabilire come cambia la somma sostituendo $a_0,a_1$ con due valori assegnati.
5. Applicare il test del termine generale a $\sum n/(n+1)$, $\sum\sin n$ e $\sum1/n$.
6. Dimostrare l'algebra per una combinazione lineare di tre serie.
7. Costruire due serie che differiscano in tre termini, abbiano la stessa natura e somme diverse.
8. Spiegare perché $|a_{N+1}|$ non è in generale uguale a $|R_N|$.
9. Determinare quanti livelli servono affinché $n\sum_{k=0}^{m}2^{-k}$ differisca da $2n$ per meno di $n/1000$.
10. Classificare come vero o falso: «se $a_n\to0$, allora le somme parziali sono di Cauchy».

### **11. Riepilogo**

> ✅ Una serie converge tramite la successione $(S_N)$; indice iniziale e termini finiti modificano eventualmente la somma, non la natura. Il limite nullo dei termini è necessario ma non sufficiente, mentre il resto quantifica l'errore di troncamento soltanto dopo che la convergenza è nota.
