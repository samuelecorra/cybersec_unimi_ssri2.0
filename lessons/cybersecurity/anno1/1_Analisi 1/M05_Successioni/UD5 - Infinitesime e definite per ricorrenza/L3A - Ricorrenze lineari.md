# **M5 UD5 Lezione 3A - Ricorrenze lineari**

### **1. Ricorrenze affini del primo ordine**

Consideriamo

$$
a_{n+1}=qa_n+b,
\qquad a_0\in\mathbb{R},
$$

con $q,b\in\mathbb{R}$. Se $q\neq1$, il punto fisso è

$$
L=\frac{b}{1-q}.
$$

Sottraendolo dalla ricorrenza,

$$
a_{n+1}-L=q(a_n-L).
$$

Per induzione,

$$
a_n-L=q^n(a_0-L),
$$

cioè

$$
a_n=\frac{b}{1-q}+q^n\left(a_0-\frac{b}{1-q}\right).
$$

Questa formula esatta risolve completamente la ricorrenza.

### **2. Classificazione completa**

Per $q\neq1$:

- se $|q|<1$, allora $a_n\to L$;
- se $q=-1$, la successione alterna tra due valori e converge solo se $a_0=L$;
- se $q>1$, il modulo diverge salvo il caso costante $a_0=L$;
- se $q<-1$, il modulo diverge con segno alterno salvo il caso costante;
- se $q=0$, da $n=1$ in poi vale $a_n=b=L$.

Se $q=1$, la ricorrenza è

$$
a_{n+1}=a_n+b,
$$

e quindi

$$
a_n=a_0+nb.
$$

È costante per $b=0$, tende a $+\infty$ per $b>0$ e a $-\infty$ per $b<0$.

> ⚠️ L'affermazione «$|q|>1$ implica divergenza» ha l'eccezione $a_0=L$, che produce la successione costante.

### **3. Ricorrenze lineari omogenee del secondo ordine**

Una ricorrenza

$$
a_{n+2}=pa_{n+1}+ra_n
$$

con $a_0,a_1$ assegnati si studia cercando soluzioni $a_n=\lambda^n$. Si ottiene l'equazione caratteristica

$$
\lambda^2-p\lambda-r=0.
$$

Se le radici reali sono distinte, $\lambda_1\neq\lambda_2$, la soluzione generale è

$$
a_n=A\lambda_1^n+B\lambda_2^n,
$$

dove $A$ e $B$ sono determinati dai dati iniziali. Se la radice è doppia, la forma diventa

$$
a_n=(A+Bn)\lambda^n.
$$

La verifica si effettua sostituendo la formula nella ricorrenza; l'unicità segue dal fatto che due dati iniziali determinano tutti i termini successivi.

### **4. Successione di Fibonacci**

La successione

$$
F_0=0,
\qquad F_1=1,
\qquad F_{n+2}=F_{n+1}+F_n
$$

ha equazione caratteristica

$$
\lambda^2-\lambda-1=0,
$$

con radici

$$
\varphi=\frac{1+\sqrt5}{2},
\qquad
\psi=\frac{1-\sqrt5}{2}.
$$

Imponendo i dati iniziali si ottiene la formula di Binet

$$
F_n=\frac{\varphi^n-\psi^n}{\sqrt5}.
$$

Poiché $|\psi|<1<\varphi$,

$$
F_n\sim\frac{\varphi^n}{\sqrt5}
$$

e, per $n\geq1$,

$$
\frac{F_{n+1}}{F_n}\to\varphi.
$$

### **5. Applicazioni e limiti del modello**

Le ricorrenze descrivono costi di algoritmi, sistemi iterativi, conteggi combinatori e aggiornamenti di stato. In informatica, una relazione ricorsiva sul costo non è necessariamente una ricorrenza affine a coefficienti costanti: per esempio $T(n)=2T(n/2)+n$ richiede metodi specifici.

Generatori pseudocasuali lineari usano aggiornamenti modulari come

$$
x_{n+1}\equiv ax_n+c\pmod m.
$$

Sono successioni periodiche in un insieme finito e non devono essere confuse con sorgenti crittograficamente sicure: la linearità può renderne prevedibile lo stato.

Nell'implementazione numerica, la formula chiusa e l'iterazione possono avere costi ed errori diversi. La convergenza matematica non elimina overflow, underflow e arrotondamento.

### **6. Errori frequenti**

- Dimenticare il termine particolare $L=b/(1-q)$ nella ricorrenza affine.
- Applicare la formula con $q\neq1$ al caso $q=1$.
- Ignorare l'eccezione iniziale $a_0=L$ nei casi instabili.
- Scrivere la soluzione caratteristica senza imporre i dati iniziali.
- Usare la formula con due radici distinte quando la radice è doppia.
- Considerare un generatore lineare congruenziale adeguato alla crittografia solo perché ha periodo lungo.

### **7. Esercizi**

1. Risolvere $a_{n+1}=\frac12a_n+3$ con $a_0=0$ e calcolarne il limite.
2. Classificare $a_{n+1}=-2a_n+3$ per ogni valore iniziale.
3. Risolvere il caso $a_{n+1}=a_n-4$.
4. Dimostrare per induzione la formula chiusa della ricorrenza affine.
5. Risolvere $a_{n+2}=3a_{n+1}-2a_n$ con $a_0=1$, $a_1=3$.
6. Verificare per sostituzione la formula di Binet e i due dati iniziali.
7. Dimostrare $F_n\sim\varphi^n/\sqrt5$ e dedurne il limite di $F_{n+1}/F_n$.
8. Spiegare perché una successione in un insieme finito definita da una regola deterministica deve diventare periodica.
9. Confrontare iterazione e formula chiusa per una ricorrenza affine dal punto di vista del numero di operazioni e dell'errore numerico.

### **8. Riepilogo**

> ✅ Le ricorrenze affini del primo ordine si riducono a una progressione geometrica rispetto al punto fisso. Le ricorrenze lineari del secondo ordine si risolvono mediante l'equazione caratteristica; Fibonacci mostra come la radice dominante determini la crescita asintotica.
