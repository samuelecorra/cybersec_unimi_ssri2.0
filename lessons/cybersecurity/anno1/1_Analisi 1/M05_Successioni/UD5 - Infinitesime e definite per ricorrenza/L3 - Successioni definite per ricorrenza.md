# **M5 UD5 Lezione 3 - Successioni definite per ricorrenza**

### **1. Dalla regola locale al comportamento globale**

Una ricorrenza del primo ordine ha la forma

$$
a_0\in I,
\qquad
a_{n+1}=f(a_n).
$$

Perché definisca davvero una successione reale, ogni termine prodotto deve appartenere al dominio di $f$. Conoscere alcuni valori iniziali può suggerire il comportamento, ma non dimostra né limitatezza né convergenza.

> 📌 L'equazione $L=f(L)$ individua soltanto i possibili limiti. Prima occorre dimostrare che la successione esiste per ogni indice e converge.

### **2. Strategia rigorosa**

Un'analisi completa segue normalmente questi passaggi:

1. **Buona definizione:** trovare un insieme $I$ che contenga $a_0$ e sia invariante, cioè $f(I)\subseteq I$.
2. **Limitatezza:** dedurla dall'invarianza o provarla separatamente.
3. **Monotonia:** studiare il segno di $a_{n+1}-a_n=f(a_n)-a_n$.
4. **Esistenza del limite:** applicare il teorema di convergenza monotona, Cauchy o un altro risultato appropriato.
5. **Identificazione:** passare al limite solo dopo aver giustificato che $f(a_n)\to f(L)$.
6. **Scelta del punto fisso:** eliminare le soluzioni incompatibili con l'insieme invariante o con il segno dei termini.

Per funzioni razionali o radicali semplici, il passaggio al limite può essere giustificato direttamente con l'aritmetica dei limiti. Il teorema generale «$a_n\to L$ implica $f(a_n)\to f(L)$» è precisamente la continuità di $f$ e sarà studiato più avanti.

### **3. Esempio affine elementare**

Consideriamo

$$
a_0=0,
\qquad
a_{n+1}=\frac{a_n+2}{2}.
$$

L'intervallo $[0,2]$ è invariante: se $0\leq a_n\leq2$, allora

$$
1\leq a_{n+1}=\frac{a_n+2}{2}\leq2.
$$

Inoltre

$$
a_{n+1}-a_n=\frac{2-a_n}{2}\geq0.
$$

La successione è crescente e limitata superiormente, dunque converge a un limite $L\in[0,2]$. Per l'aritmetica dei limiti,

$$
L=\frac{L+2}{2},
$$

da cui $L=2$.

In questo caso si può anche ottenere la formula esatta

$$
a_n=2-\frac{2}{2^n},
$$

che conferma e quantifica la convergenza.

### **4. Diagramma a ragnatela**

Il grafico di $y=f(x)$ insieme alla bisettrice $y=x$ permette di visualizzare l'iterazione: dal valore $a_n$ si sale verticalmente fino al grafico di $f$ e si torna orizzontalmente sulla bisettrice per leggere $a_{n+1}$. È uno strumento esplorativo, non una dimostrazione.

<!-- TODO FIGURA: diagramma a ragnatela per f(x)=(x+2)/2 con bisettrice y=x, punto fisso 2 e iterazioni da a_0=0; indicare con frecce verticali e orizzontali a_1,a_2,a_3. Obiettivo didattico: mostrare la convergenza verso un punto fisso distinguendo l'intuizione grafica dalla prova monotona. -->

### **5. Metodo babilonese per la radice quadrata**

Siano $c>0$ e $a_0>\sqrt c$. Definiamo

$$
a_{n+1}=\frac12\left(a_n+\frac{c}{a_n}\right).
$$

Dimostriamo che la successione è ben definita, decrescente e convergente a $\sqrt c$.

#### **5.1 Positività e minorazione**

Se $a_n>0$, allora $a_{n+1}>0$. Inoltre

$$
a_{n+1}-\sqrt c
=\frac{a_n^2-2a_n\sqrt c+c}{2a_n}
=\frac{(a_n-\sqrt c)^2}{2a_n}\geq0.
$$

Per induzione, $a_n\geq\sqrt c>0$ per ogni $n$, quindi nessun denominatore si annulla.

#### **5.2 Monotonia**

Poiché $a_n\geq\sqrt c$,

$$
a_{n+1}-a_n
=\frac{c-a_n^2}{2a_n}\leq0.
$$

La successione è decrescente e limitata inferiormente da $\sqrt c$; perciò converge a un $L\geq\sqrt c>0$.

#### **5.3 Identificazione del limite**

L'aritmetica dei limiti consente di passare al limite, perché $L\neq0$:

$$
L=\frac12\left(L+\frac{c}{L}\right).
$$

Segue $L^2=c$. La condizione $L>0$ seleziona

$$
L=\sqrt c.
$$

#### **5.4 Errore quadratico**

Ponendo $e_n=a_n-\sqrt c\geq0$, il calcolo precedente dà

$$
e_{n+1}=\frac{e_n^2}{2a_n}\leq\frac{e_n^2}{2\sqrt c}.
$$

Quando l'errore è già piccolo, il passo successivo è proporzionale al suo quadrato: il numero di cifre corrette tende approssimativamente a raddoppiare.

<!-- TODO FIGURA: per c=2, rappresentare la successione babilonese a_{n+1}=(a_n+2/a_n)/2 a partire da a_0=2, con valori a_0,a_1,a_2,a_3 e linea orizzontale y=sqrt(2); affiancare una scala logaritmica dell'errore e_n. Obiettivo didattico: collegare monotonia, limite e convergenza quadratica dell'algoritmo. -->

### **6. Punti fissi multipli e selezione**

La ricorrenza

$$
a_{n+1}=a_n^2,
\qquad a_0\in[0,1]
$$

ha due punti fissi, $0$ e $1$. Se $0\leq a_0<1$, allora $a_n=a_0^{2^n}\to0$; se $a_0=1$, la successione è costante. Risolvere $L=L^2$ non consente da solo di scegliere il limite.

Una ricorrenza può anche avere un unico punto fisso e non convergere. Per esempio

$$
a_{n+1}=-a_n,
\qquad a_0=1
$$

ha come unico punto fisso $0$, ma la successione alterna $1$ e $-1$.

### **7. Convergenza matematica e arresto numerico**

Un algoritmo reale non esegue infiniti passi. Si sceglie un criterio, per esempio

$$
|a_{n+1}-a_n|<\tau,
$$

con tolleranza $\tau>0$. Tale criterio non garantisce da solo che $|a_n-L|<\tau$: serve una stima dell'errore. Inoltre l'aritmetica finita può introdurre arrotondamenti, overflow o arresti apparenti.

### **8. Errori frequenti**

- Omettere la prova che la ricorrenza sia definita per ogni $n$.
- Risolvere $L=f(L)$ e dichiarare la convergenza.
- Usare l'induzione senza verificare il caso base.
- Passare al limite attraverso una funzione senza giustificarlo.
- Confondere una figura a ragnatela con una prova.
- Scambiare un criterio di arresto numerico con la definizione di limite.

### **9. Esercizi**

1. Studiare $a_0=3$, $a_{n+1}=(a_n+1)/2$ con invarianza, monotonia e limite.
2. Dimostrare per induzione la formula $a_n=2-2/2^n$ dell'esempio principale.
3. Studiare $a_{n+1}=a_n^2$ per $a_0<0$, $0<a_0<1$, $a_0=1$ e $a_0>1$.
4. Fornire una ricorrenza con due punti fissi e descrivere bacini iniziali con comportamenti diversi.
5. Per $c=2$ e $a_0=2$, calcolare tre passi del metodo babilonese e confrontare gli errori con la stima quadratica.
6. **Problema d'esame:** per $c>0$ e $a_0>\sqrt c$, ricostruire la dimostrazione completa di buona definizione, minorazione, monotonia, convergenza, identificazione del limite e formula dell'errore.
7. Spiegare perché $a_{n+1}=-a_n$ smentisce il principio «unico punto fisso implica convergenza».
8. Proporre un criterio di arresto affidabile per il metodo babilonese usando una maggiorazione di $e_n$.
9. Stabilire quali passaggi nello studio di $a_{n+1}=\sqrt{2+a_n}$ richiedono risultati sulla continuità e quali si possono giustificare algebricamente.

### **10. Riepilogo**

> ✅ Lo studio di una ricorrenza richiede prima buona definizione e controllo globale, poi esistenza del limite e soltanto alla fine l'equazione del punto fisso. Il metodo babilonese mostra come una prova qualitativa possa fornire anche una stima quantitativa dell'errore.
