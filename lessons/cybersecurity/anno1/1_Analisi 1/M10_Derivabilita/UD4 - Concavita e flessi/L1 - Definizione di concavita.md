# **M10 UD4 Lezione 1 - Definizione di concavità**

### **1. Che cosa descrive la concavità**

La monotonia dice se una funzione cresce o decresce. La concavità descrive invece **come** cresce o decresce: se le pendenze aumentano, diminuiscono, oppure cambiano regime.

Una funzione crescente può crescere sempre più rapidamente, come $e^x$, oppure sempre più lentamente, come $\ln x$. Entrambe crescono, ma hanno concavità diversa.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **2. Concavità verso l'alto e verso il basso**

Una funzione è **convessa** o concava verso l'alto su un intervallo se il suo grafico sta sotto le corde che uniscono due suoi punti. Intuitivamente ha forma "a coppa".

Una funzione è **concava** o concava verso il basso se il grafico sta sopra le corde. Intuitivamente ha forma "a volta".

**Definizione (rigorosa, con le corde).** $f$ è **convessa** sull'intervallo $I$ se per ogni $x_1,x_2\in I$ e ogni $t\in[0,1]$:

$$
f\bigl(t\,x_1+(1-t)\,x_2\bigr) \;\le\; t\,f(x_1)+(1-t)\,f(x_2)
$$

Il punto $t\,x_1+(1-t)\,x_2$ percorre il segmento $[x_1,x_2]$ e il membro destro è l'ordinata della **corda**: la definizione dice esattamente "grafico sotto la corda", punto per punto. Con $\ge$ si definisce la concavità ($f$ concava $\iff$ $-f$ convessa: ogni teorema si dimostra una volta sola), e con le disuguaglianze **strette** (per $x_1\neq x_2$, $t\in(0,1)$) la convessità/concavità stretta. Nota: la definizione **non richiede derivabilità** — $|x|$ è convessa su $\mathbb{R}$ pur non essendo derivabile in $0$.

**Caratterizzazione con la tangente** (per $f$ derivabile su $I$): $f$ è convessa se e solo se il grafico sta **sopra ogni sua tangente**:

$$
f(x)\;\ge\; f(x_0)+f'(x_0)(x-x_0) \qquad \forall x,x_0\in I
$$

Corde da sopra, tangenti da sotto: le due "gabbie" equivalenti della convessità. La disuguaglianza $e^x\ge 1+x$ (M09/UD5/L6) è esattamente questa proprietà per l'esponenziale in $x_0=0$.

<!-- TODO FIGURA:
Grafico di una funzione convessa (es. x^2 o e^x) con: una corda tra due punti del grafico che sta sopra la curva (segmento tratteggiato, regione tra corda e curva evidenziata) e una tangente in un terzo punto che sta sotto la curva (retta tratteggiata). Etichette "corda sopra" e "tangente sotto", con la formula f(tx1+(1-t)x2) <= t f(x1)+(1-t) f(x2) accanto alla corda.
Obiettivo didattico: visualizzare le due caratterizzazioni equivalenti della convessita': grafico sotto le corde e sopra le tangenti.
-->


In termini differenziali, se $f$ è derivabile, la concavità è collegata alla monotonia di $f'$:

$$
f' \text{ crescente} \quad\iff\quad f \text{ convessa},
$$

$$
f' \text{ decrescente} \quad\iff\quad f \text{ concava}.
$$

*Dimostrazione (il verso $f'$ crescente $\Rightarrow$ convessa, quello usato negli studi).* Siano $x_1<x<x_2$ in $I$. Per **Lagrange** (M09/UD5/L3) sui due sottointervalli esistono $c_1\in(x_1,x)$ e $c_2\in(x,x_2)$ con

$$
\frac{f(x)-f(x_1)}{x-x_1}=f'(c_1)
\;\le\; f'(c_2)=\frac{f(x_2)-f(x)}{x_2-x}
$$

(la disuguaglianza centrale è la crescenza di $f'$, essendo $c_1<c_2$). La condizione "pendenza a sinistra $\le$ pendenza a destra per ogni punto intermedio" è una riscrittura algebrica della disuguaglianza delle corde (si ottiene ponendo $x=t\,x_1+(1-t)\,x_2$ e riordinando): il grafico sta sotto la corda. Il viceversa usa il confronto delle pendenze delle corde al tendere degli estremi. $\square$

Se $f$ è due volte derivabile, questo si traduce nel segno della seconda derivata (via monotonia di $f'$, M09/UD5/L4).

### **3. Criterio della seconda derivata**

Se $f$ è due volte derivabile in un intervallo $I$, allora:

$$
f''(x)>0 \text{ per ogni } x\in I
\quad\Rightarrow\quad
f \text{ è convessa in } I,
$$

$$
f''(x)<0 \text{ per ogni } x\in I
\quad\Rightarrow\quad
f \text{ è concava in } I.
$$

La seconda derivata misura la variazione della pendenza: se è positiva, la pendenza cresce; se è negativa, la pendenza diminuisce.

> 📌 La prima derivata studia crescita e decrescita della funzione; la seconda derivata studia crescita e decrescita della pendenza.

### **4. Esempi**

Per

$$
f(x)=x^2
$$

si ha

$$
f'(x)=2x,\qquad f''(x)=2.
$$

Poiché $f''(x)>0$ per ogni $x$, la funzione è convessa su tutto $\mathbb{R}$.

Per

$$
g(x)=-x^2
$$

si ha

$$
g''(x)=-2.
$$

Quindi $g$ è concava su tutto $\mathbb{R}$.

Per

$$
h(x)=x^3
$$

si ha

$$
h''(x)=6x.
$$

La seconda derivata è negativa per $x<0$ e positiva per $x>0$: la concavità cambia in $0$.

### **5. Punto di flesso**

Un punto $x_0$ è un **punto di flesso** se la funzione cambia concavità passando attraverso $x_0$. Se la funzione è due volte derivabile, i candidati flessi si cercano tra i punti in cui

$$
f''(x)=0
$$

o in cui $f''$ non esiste.

Però, come per i punti stazionari, annullare la seconda derivata non basta: serve verificare il cambio di segno di $f''$.

### **6. Riepilogo**

La concavità completa lo studio del grafico: dopo aver capito dove la funzione cresce o decresce, si studia come varia la pendenza. La seconda derivata è lo strumento principale: segno positivo per convessità, segno negativo per concavità, cambio di segno per un flesso.
