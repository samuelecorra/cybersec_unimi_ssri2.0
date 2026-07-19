# **M7 UD1 Lezione 2A - Monotonia e limitatezza**

### **1. Monotonia su un insieme ordinato**

Sia $f:D\to\mathbb{R}$, con $D\subseteq\mathbb{R}$. La funzione è:

- crescente su $D$ se

$$
x_1<x_2\Rightarrow f(x_1)\le f(x_2);
$$

- strettamente crescente se

$$
x_1<x_2\Rightarrow f(x_1)<f(x_2);
$$

- decrescente se

$$
x_1<x_2\Rightarrow f(x_1)\ge f(x_2);
$$

- strettamente decrescente se

$$
x_1<x_2\Rightarrow f(x_1)>f(x_2).
$$

Le definizioni confrontano ogni coppia ordinata di punti del dominio. Non richiedono che $D$ sia un intervallo.

<!-- TODO FIGURA:
Quattro piccoli grafici sugli stessi assi: crescente non stretta con tratto orizzontale, strettamente crescente, decrescente non stretta e strettamente decrescente. Evidenziare due ascisse x1<x2 e le corrispondenti ordinate.
Obiettivo didattico: distinguere monotonia larga e stretta mediante il confronto tra coppie di valori.
-->

### **2. Stretta monotonia e iniettività**

#### **2.1 Teorema**

Ogni funzione strettamente monotona è iniettiva.

**Dimostrazione.** Supponiamo $f$ strettamente crescente. Se $x_1\neq x_2$, allora $x_1<x_2$ oppure $x_2<x_1$. Nel primo caso $f(x_1)<f(x_2)$; nel secondo $f(x_2)<f(x_1)$. Dunque $f(x_1)\neq f(x_2)$, che è la contronominale della definizione di iniettività. Il caso strettamente decrescente è analogo. $\square$

Il converso è falso su domini arbitrari. Sia

$$
D=\{0,1,2\}
$$

e definiamo

$$
f(0)=0,
\qquad
f(1)=2,
\qquad
f(2)=1.
$$

I tre valori sono distinti, quindi $f$ è iniettiva, ma non è né crescente né decrescente. Su un intervallo, il converso diventerà vero sotto l'ipotesi aggiuntiva di continuità, trattata in M08.

### **3. Monotonia dell'inversa**

#### **3.1 Teorema**

Sia $f:A\to B$ biiettiva e strettamente crescente. Allora $f^{-1}:B\to A$ è strettamente crescente. Se $f$ è strettamente decrescente, anche $f^{-1}$ è strettamente decrescente.

**Dimostrazione nel caso crescente.** Presi $y_1<y_2$ in $B$, poniamo $x_i=f^{-1}(y_i)$. Se fosse $x_1\ge x_2$, dalla crescita di $f$ seguirebbe $y_1=f(x_1)\ge f(x_2)=y_2$, assurdo. Quindi $x_1<x_2$, cioè $f^{-1}(y_1)<f^{-1}(y_2)$. $\square$

### **4. Composizione di funzioni monotone**

Siano $f:A\to B$ e $g:B\to\mathbb{R}$ monotone sui rispettivi insiemi. Il verso della composizione segue la regola dei segni:

| $f$ | $g$ | $g\circ f$ |
|---|---|---|
| crescente | crescente | crescente |
| crescente | decrescente | decrescente |
| decrescente | crescente | decrescente |
| decrescente | decrescente | crescente |

Per esempio, se $x_1<x_2$, $f$ decrescente dà $f(x_1)\ge f(x_2)$; applicando $g$ decrescente, l'ordine si inverte ancora e

$$
g(f(x_1))\le g(f(x_2)).
$$

Se entrambe le monotonicità sono strette, anche la conclusione è stretta. Somme di funzioni crescenti sono crescenti, ma per prodotti e quozienti servono ipotesi ulteriori sui segni. Per esempio, $f(x)=g(x)=x$ sono crescenti su $\mathbb{R}$, mentre $f(x)g(x)=x^2$ non lo è su tutto $\mathbb{R}$.

### **5. Limitatezza**

La funzione $f:D\to\mathbb{R}$ è limitata superiormente se esiste $M\in\mathbb{R}$ tale che

$$
f(x)\le M
$$

per ogni $x\in D$. È limitata inferiormente se esiste $m\in\mathbb{R}$ tale che

$$
m\le f(x)
$$

per ogni $x\in D$. È limitata se esistono $m,M\in\mathbb{R}$ con

$$
m\le f(x)\le M
$$

per ogni $x\in D$, equivalentemente se la sua immagine è un insieme limitato.

La funzione $\sin x$ è limitata su un dominio illimitato:

$$
-1\le\sin x\le1.
$$

La funzione

$$
f:(0,1)\to\mathbb{R},
\qquad
f(x)=\frac1x,
$$

ha dominio limitato ma non è limitata superiormente. Dominio limitato e funzione limitata sono concetti indipendenti.

### **6. Estremi senza derivate**

Massimo, minimo, supremo e infimo sono proprietà dell'immagine $f(D)$. Per dimostrare che $m$ è minimo occorrono entrambe le condizioni:

$$
f(x)\ge m\quad\forall x\in D
$$

e

$$
\exists x_0\in D:f(x_0)=m.
$$

Per

$$
f(x)=\frac{x}{1+x},
\qquad x\in(0,+\infty),
$$

si ha $0<f(x)<1$. Quindi

$$
\inf f(D)=0,
\qquad
\sup f(D)=1,
$$

ma nessuno dei due è raggiunto.

Su un dominio non intervallare una funzione può avere estremi locali isolati per ragioni puramente topologiche. Per evitare anticipazioni, in M07 useremo la definizione con intervalli aperti: $x_0$ è massimo locale se esiste $\delta>0$ tale che

$$
f(x)\le f(x_0)
$$

per ogni $x\in D\cap(x_0-\delta,x_0+\delta)$.

### **7. Esempi parametrici**

Per

$$
f_a(x)=ax+b,
$$

la funzione è strettamente crescente se $a>0$, strettamente decrescente se $a<0$ e costante se $a=0$.

Per

$$
g_a(x)=\frac{1}{x-a},
$$

il dominio è $\mathbb{R}\setminus\{a\}$. La funzione è strettamente decrescente su ciascuno dei due intervalli $(-\infty,a)$ e $(a,+\infty)$, ma non è decrescente sull'intero dominio: scegliendo $x_1<a<x_2$ si può avere $g_a(x_1)<g_a(x_2)$.

> ⚠️ Dire "decrescente su ogni componente del dominio" non equivale a dire "decrescente sul dominio".

### **8. Esercizi**

1. Dimostra dalla definizione che $x\mapsto x^3$ è strettamente crescente su $\mathbb{R}$.
2. Classifica la monotonia di $x\mapsto|x|$ su $\mathbb{R}$, $(-\infty,0]$ e $[0,+\infty)$.
3. Trova una funzione iniettiva su $\mathbb{Z}$ che non sia monotona.
4. Dimostra le altre tre regole di composizione della monotonia.
5. Stabilisci se $x/(1+|x|)$ è limitata e determina supremo e infimo della sua immagine senza derivate.
6. Per $f_a(x)=x^2+ax+1$, completa il quadrato e determina il minimo in funzione di $a$.
7. Fornisci una funzione limitata che non abbia né massimo né minimo e una che abbia entrambi su un dominio illimitato.
8. Spiega perché il teorema di Weierstrass non può essere usato prima di aver verificato continuità e compattezza dell'intervallo.

### **9. Riepilogo**

> ✅ Monotonia e limitatezza sono proprietà d'ordine dell'insieme dei valori; si definiscono e si dimostrano senza derivate.

