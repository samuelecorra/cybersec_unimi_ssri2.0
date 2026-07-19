# **M10 UD4 Lezione 3 - Esercizi su concavità e flessi**

### **1. Esercizio completo con polinomio**

Studiamo monotonia, concavità e flessi di

$$
f(x)=x^3-3x^2.
$$

Il dominio è tutto $\mathbb{R}$. La prima derivata è

$$
f'(x)=3x^2-6x=3x(x-2).
$$

Gli zeri sono

$$
x=0,\qquad x=2.
$$

Il segno di $f'$ è positivo su $(-\infty,0)$, negativo su $(0,2)$ e positivo su $(2,+\infty)$. Quindi $x=0$ è massimo locale e $x=2$ è minimo locale.

La seconda derivata è

$$
f''(x)=6x-6=6(x-1).
$$

Essa è negativa per $x<1$ e positiva per $x>1$. Quindi la funzione è concava su $(-\infty,1)$ e convessa su $(1,+\infty)$. Il punto

$$
x=1
$$

è un flesso. Il valore corrispondente è

$$
f(1)=1-3=-2.
$$

Il flesso è quindi

$$
(1,-2).
$$

### **2. Esercizio con funzione razionale**

Studiamo la concavità di

$$
g(x)=\frac{1}{x}.
$$

Il dominio è

$$
\mathbb{R}\setminus\{0\}.
$$

La prima derivata è

$$
g'(x)=-\frac{1}{x^2}.
$$

La seconda derivata è

$$
g''(x)=\frac{2}{x^3}.
$$

Il segno di $g''$ è negativo per $x<0$ e positivo per $x>0$. Quindi $g$ è concava su $(-\infty,0)$ e convessa su $(0,+\infty)$.

Non c'è un flesso in $0$, perché $0$ non appartiene al dominio. Il cambio di concavità avviene tra due rami separati da una discontinuità.

### **3. Esercizio con esponenziale**

Consideriamo

$$
h(x)=xe^{-x}.
$$

Il dominio è $\mathbb{R}$. Deriviamo:

$$
h'(x)=e^{-x}+x(-e^{-x})
=e^{-x}(1-x).
$$

La seconda derivata è

$$
h''(x)=-e^{-x}(1-x)-e^{-x}
=e^{-x}(x-2).
$$

Poiché $e^{-x}>0$ per ogni $x$, il segno di $h''$ dipende da $x-2$. Quindi $h$ è concava per $x<2$ e convessa per $x>2$. Il punto $x=2$ è un flesso.

Il valore è

$$
h(2)=2e^{-2}.
$$

Il flesso è

$$
(2,2e^{-2}).
$$

### **4. Collegamento con lo studio di funzione**

Le informazioni su monotonia e concavità vanno combinate:

- $f'$ determina intervalli di crescita, decrescita ed estremi;
- $f''$ determina concavità, convessità e flessi;
- dominio e discontinuità separano i rami del grafico.

La rappresentazione qualitativa di una funzione nasce dall'incrocio di questi dati, non da un singolo calcolo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **4bis. La convessità che dimostra disuguaglianze (approfondimento)**

La definizione con le corde è essa stessa una macchina da disuguaglianze. Il logaritmo è **concavo** su $(0,+\infty)$ ($(\ln)''=-\frac{1}{x^2}<0$), quindi il grafico sta **sopra** le corde: per $a,b>0$ e $t=\frac 12$,

$$
\ln\left(\frac{a+b}{2}\right) \;\ge\; \frac{\ln a+\ln b}{2} = \ln\sqrt{ab}
$$

e passando all'esponenziale (crescente):

$$
\frac{a+b}{2} \;\ge\; \sqrt{ab}
$$

la disuguaglianza tra **media aritmetica e geometrica**, in due righe. Con pesi $t$ generici e più punti si ottiene la disuguaglianza di **Jensen**, che qui solo nominiamo: è il seme convesso di mezza matematica applicata (dalla teoria dell'informazione alle disuguaglianze di ottimizzazione).

### **5. Riepilogo**

Negli esercizi su concavità bisogna evitare due errori: confondere uno zero di $f''$ con un flesso automatico, e dichiarare flessi in punti fuori dal dominio. Un flesso richiede un punto del grafico e un cambio effettivo di concavità. E ricordare che la concavità non è solo estetica del grafico: produce disuguaglianze (punto 4bis) e, con Taylor, criteri di classificazione (UD3/L5).
