# **M7 UD2 Lezione 4A - Funzioni speciali e famiglie**

### **1. Funzioni definite a tratti**

Una funzione può usare leggi diverse su parti diverse del dominio:

$$
f(x)=
\begin{cases}
f_1(x) & x\in D_1,\\
f_2(x) & x\in D_2,\\
\vdots & \vdots
\end{cases}
$$

Per definire una funzione, i casi devono coprire il dominio e, dove si sovrappongono, assegnare lo stesso valore. È spesso più semplice scegliere insiemi $D_i$ disgiunti.

Esempio:

$$
f(x)=
\begin{cases}
x+1 & x<0,\\
x^2 & 0\le x\le2,\\
4 & x>2.
\end{cases}
$$

Il dominio è $\mathbb{R}$, $f(0)=0$ e $f(2)=4$. I simboli $<$ e $\le$ nei punti di raccordo determinano quale legge si usa.

<!-- TODO FIGURA:
Grafico della funzione a tratti dell'esempio con pallini pieni e vuoti in x=0 e x=2, colori diversi per i tre rami e proiezione dell'immagine sull'asse y.
Obiettivo didattico: leggere dominio, valori nei raccordi e immagine controllando l'inclusione degli estremi.
-->

### **2. Funzione segno**

La funzione segno è

$$
\operatorname{sgn}(x)=
\begin{cases}
-1 & x<0,\\
0 & x=0,\\
1 & x>0.
\end{cases}
$$

Ha dominio $\mathbb{R}$, immagine $\{-1,0,1\}$, è dispari e non è iniettiva. Per $x\neq0$,

$$
x=|x|\operatorname{sgn}(x).
$$

Non va confusa con $x/|x|$, che non è definita in $0$.

### **3. Parte intera, soffitto e parte frazionaria**

La parte intera inferiore è

$$
\lfloor x\rfloor=\max\{n\in\mathbb{Z}:n\le x\},
$$

mentre il soffitto è

$$
\lceil x\rceil=\min\{n\in\mathbb{Z}:x\le n\}.
$$

Equivale a

$$
\lfloor x\rfloor\le x<\lfloor x\rfloor+1,
$$

$$
\lceil x\rceil-1<x\le\lceil x\rceil.
$$

Per i negativi,

$$
\lfloor-1.2\rfloor=-2,
\qquad
\lceil-1.2\rceil=-1.
$$

La parte frazionaria è

$$
\{x\}=x-\lfloor x\rfloor,
$$

e soddisfa

$$
0\le\{x\}<1,
\qquad
\{x+n\}=\{x\}\quad(n\in\mathbb{Z}).
$$

Quindi è periodica di periodo $1$, ma non raggiunge il supremo $1$.

<!-- TODO FIGURA:
Sullo stesso sistema di assi, grafici a gradini di floor e ceiling tra -3 e 3, con pallini pieni/vuoti agli interi e colori distinti.
Obiettivo didattico: fissare le convenzioni agli estremi e il comportamento sui numeri negativi.
-->

<!-- TODO FIGURA:
Grafico a dente di sega della parte frazionaria {x} tra -3 e 3, con segmenti [n,n+1), valori 0 inclusi agli interi e valori 1 esclusi.
Obiettivo didattico: visualizzare immagine [0,1), periodicità e discontinuità ai punti interi senza anticiparne la teoria formale.
-->

### **4. Funzione indicatrice**

Per $E\subseteq X$, l'indicatrice di $E$ è

$$
\mathbf{1}_E:X\to\{0,1\},
\qquad
\mathbf{1}_E(x)=
\begin{cases}
1 & x\in E,\\
0 & x\notin E.
\end{cases}
$$

Traduce operazioni insiemistiche in identità algebriche:

$$
\mathbf{1}_{E\cap F}=\mathbf{1}_E\mathbf{1}_F,
$$

$$
\mathbf{1}_{X\setminus E}=1-\mathbf{1}_E,
$$

$$
\mathbf{1}_{E\cup F}=\mathbf{1}_E+\mathbf{1}_F-\mathbf{1}_E\mathbf{1}_F.
$$

La prima identità si verifica nei quattro casi di appartenenza a $E$ e $F$; le altre seguono analogamente.

### **5. Parte positiva e parte negativa**

Per una funzione reale $f$ si definiscono

$$
f^+(x)=\max\{f(x),0\}
=\frac{|f(x)|+f(x)}{2},
$$

$$
f^-(x)=\max\{-f(x),0\}
=\frac{|f(x)|-f(x)}{2}.
$$

Entrambe sono non negative e valgono le identità

$$
f=f^+-f^-,
$$

$$
|f|=f^++f^-,
$$

$$
f^+f^-=0.
$$

Le formule si dimostrano separando i casi $f(x)\ge0$ e $f(x)<0$.

### **6. Classificazione delle famiglie elementari**

Le principali famiglie si distinguono per le operazioni usate:

| Famiglia | Forma tipica | Vincolo caratteristico |
|---|---|---|
| affine | $ax+b$ | nessuno |
| polinomiale | $P(x)$ | nessuno |
| razionale | $P(x)/Q(x)$ | $Q(x)\neq0$ |
| potenza intera | $x^n$ | dipende dal segno di $n$ |
| radicale | $\sqrt[n]{R(x)}$ | radicando non negativo se $n$ pari |
| esponenziale | $a^{g(x)}$ | $a>0$, esponente definito |
| logaritmica | $\log_a(g(x))$ | argomento positivo |
| trigonometrica | $\sin g(x)$, $\tan g(x)$ | punti esclusi per rapporti |
| trigonometrica inversa | $\arcsin g(x)$ | argomento nell'immagine del ramo |
| iperbolica e inversa | $\sinh g(x)$, $\operatorname{artanh}g(x)$ | vincoli della funzione esterna |

Una funzione **elementare** si ottiene con un numero finito di operazioni algebriche e composizioni a partire da costanti, identità, esponenziali, logaritmi, funzioni trigonometriche e loro inverse. Questa è una chiusura operativa, non topologica: non autorizza limiti infiniti di funzioni, serie di funzioni o passaggi alla chiusura di insiemi.

### **7. Funzioni costanti, identità, involuzioni e idempotenti**

La funzione costante $c_A:A\to B$, $c_A(x)=c$, ha immagine $\{c\}$ ed è iniettiva solo se $A$ ha al più un elemento. È suriettiva solo se $B=\{c\}$.

L'identità soddisfa

$$
\operatorname{id}_A\circ f=f,
\qquad
g\circ\operatorname{id}_A=g
$$

quando le composizioni hanno senso.

Una funzione $f:A\to A$ è una **involuzione** se

$$
f\circ f=\operatorname{id}_A.
$$

Ogni involuzione è biiettiva e coincide con la propria inversa. Esempi sono $f(x)=-x$ su $\mathbb{R}$ e $f(x)=1/x$ su $\mathbb{R}\setminus\{0\}$.

È **idempotente** se

$$
f\circ f=f.
$$

In tal caso ogni elemento dell'immagine è un punto fisso: se $y=f(x)$, allora $f(y)=f(f(x))=f(x)=y$. Un esempio è $f(x)=\max\{x,0\}$.

### **8. Equazioni funzionali introduttive**

Un'equazione funzionale cerca funzioni, non numeri. Per esempio, se

$$
f(x+y)=f(x)+f(y)
$$

per ogni $x,y\in\mathbb{R}$, ponendo $x=y=0$ si ottiene $f(0)=0$ e ponendo $y=-x$ si ottiene $f(-x)=-f(x)$. Senza ipotesi di regolarità esistono soluzioni non elementari; la classificazione $f(x)=cx$ richiede ipotesi aggiuntive come continuità o monotonia.

Se invece

$$
f(xy)=f(x)f(y)
$$

e $f$ non è identicamente nulla, allora $f(1)=1$ perché

$$
f(1)=f(1)^2.
$$

Questi esempi allenano l'uso delle identità senza anticipare teoremi analitici.

### **9. Supporto e collegamenti discreti**

L'insieme in cui $f$ non è nulla è

$$
\{x\in D:f(x)\neq0\}.
$$

Per ora diciamo che una funzione ha **supporto finito** se tale insieme è finito. La definizione topologica di supporto come chiusura di questo insieme e il supporto compatto richiedono nozioni successive e sono correttamente rinviati.

Una successione è una funzione $a:\mathbb{N}\to\mathbb{R}$; una serie usa la successione delle somme parziali. Si vedano [M05 UD1 L1](../../M05_Successioni/UD1 - Definizioni ed esempi/L1 - Intro alle successioni.md) e [M06 UD1 L1](../../M06_Serie Numeriche/UD1 - Intro e Criterio di Cauchy/L1 - Intro serie numeriche.md).

### **10. Informatica e sicurezza**

- Una tabella di lookup implementa una funzione su un dominio finito solo se ogni chiave ha un unico valore.
- Una funzione indicatrice rappresenta una policy binaria: autorizzato/non autorizzato.
- Floor e modulo modellano bucket, paginazione e indicizzazione periodica.
- Le collisioni di hash mostrano la differenza tra funzione e codifica iniettiva.
- Una permutazione è una biiezione finita; cifrare e decifrare corrispondono a applicare una permutazione e la sua inversa.

### **11. Esercizi**

1. Determina dominio e immagine della funzione a tratti della sezione 1.
2. Risolvi $\lfloor x\rfloor=2$ e $\lceil2x\rceil=3$.
3. Dimostra $\lceil x\rceil=-\lfloor-x\rfloor$.
4. Calcola $\{-3.7\}$ e verifica direttamente la periodicità della parte frazionaria.
5. Dimostra le tre identità delle funzioni indicatrici mediante casi.
6. Verifica le identità di $f^+$ e $f^-$ per $f(x)=x^2-1$ e descrivine gli zeri.
7. Classifica $\sqrt{1+e^{2x}}/(1+\cos x)$ e determinane il dominio naturale.
8. Trova tutte le funzioni costanti che sono involuzioni su un insieme non vuoto.
9. Dimostra che un'idempotente è l'identità sulla propria immagine.
10. Per $f_a(x)=\lfloor x-a\rfloor+a$, studia l'effetto del parametro sugli intervalli di costanza.

### **12. Riepilogo**

> ✅ Funzioni a tratti, indicatori, parte intera e parti positiva/negativa sono strumenti strutturali; non sono eccezioni marginali al concetto di funzione.
