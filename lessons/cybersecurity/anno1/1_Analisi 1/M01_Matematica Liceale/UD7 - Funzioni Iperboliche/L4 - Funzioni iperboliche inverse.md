# **M1 UD7 Lezione 4 - Funzioni iperboliche inverse**

### **1. Inversa e reciproco**

Una funzione ammette inversa se è biiettiva tra il dominio e l'immagine considerati. L'inversa scambia input e output; il reciproco sostituisce invece il valore $f(x)$ con $1/f(x)$.

Per evitare ambiguità, in questa repository si usano

$$
\operatorname{arsinh},\qquad \operatorname{arcosh},\qquad \operatorname{artanh}
$$

per le inverse, e

$$
\operatorname{csch},\qquad \operatorname{sech},\qquad \coth
$$

per i reciproci. La notazione $\sinh^{-1}x$ non viene usata senza spiegazione.

### **2. Quali funzioni sono invertibili**

- $\sinh:\mathbb{R}\to\mathbb{R}$ è strettamente crescente, quindi è invertibile senza restrizioni;
- $\tanh:\mathbb{R}\to(-1,1)$ è strettamente crescente, quindi è invertibile;
- $\cosh$ è pari e non è iniettiva su $\mathbb{R}$, perché $\cosh(-x)=\cosh x$;
- restringendo $\cosh$ a $[0,+\infty)$ si ottiene una biiezione su $[1,+\infty)$.

La scelta $[0,+\infty)$ definisce il ramo principale dell'inversa del coseno iperbolico.

<!-- TODO FIGURA:
Tre pannelli: y=sinh x su R, y=cosh x con evidenziato solo il ramo x>=0, y=tanh x su R. Disegnare la retta y=x e le rispettive riflessioni inverse. Indicare domini e immagini sui bordi degli assi. Obiettivo didattico: mostrare perché sinh e tanh sono invertibili globalmente e cosh richiede una restrizione.
-->

### **3. Seno iperbolico inverso**

Si definisce

$$
\operatorname{arsinh}:\mathbb{R}\to\mathbb{R}
$$

come inversa di $\sinh$.

#### **3.1 Derivazione della formula logaritmica**

Poniamo

$$
y=\sinh x=\frac{e^x-e^{-x}}{2}.
$$

Introduciamo

$$
t=e^x>0.
$$

Allora $e^{-x}=1/t$ e

$$
2y=t-\frac1t.
$$

Moltiplicando per $t>0$,

$$
t^2-2yt-1=0.
$$

Le radici sono

$$
t=y\pm\sqrt{y^2+1}.
$$

Poiché $\sqrt{y^2+1}>|y|$, la radice $y-\sqrt{y^2+1}$ è negativa e non può coincidere con $e^x$. Resta

$$
t=y+\sqrt{y^2+1}>0.
$$

Applicando il logaritmo,

$$
x=\ln\left(y+\sqrt{y^2+1}\right).
$$

Pertanto

$$
\operatorname{arsinh}y
=\ln\left(y+\sqrt{y^2+1}\right),
\qquad y\in\mathbb{R}.
$$

La positività dell'argomento del logaritmo è garantita da $\sqrt{y^2+1}>|y|$.

#### **3.2 Proprietà**

$\operatorname{arsinh}$ ha dominio e immagine $\mathbb{R}$, è strettamente crescente e dispari. Per dimostrare la disparità, osserviamo che

$$
\left(\sqrt{x^2+1}-x\right)\left(\sqrt{x^2+1}+x\right)=1.
$$

Quindi

$$
\operatorname{arsinh}(-x)
=\ln\left(\sqrt{x^2+1}-x\right)
=-\ln\left(\sqrt{x^2+1}+x\right)
=-\operatorname{arsinh}x.
$$

<!-- TODO FIGURA:
Grafico di y=arsinh x per x in [-5,5], con origine, simmetria dispari e confronto tratteggiato con y=x. Affiancare il grafico di sinh riflesso rispetto a y=x. Obiettivo didattico: visualizzare dominio e immagine R e la relazione di inversione.
-->

### **4. Coseno iperbolico inverso**

Si definisce l'inversa principale

$$
\operatorname{arcosh}:[1,+\infty)\to[0,+\infty).
$$

#### **4.1 Derivazione della formula logaritmica**

Poniamo, con $x\geq0$,

$$
y=\cosh x=\frac{e^x+e^{-x}}{2}.
$$

Poiché l'immagine del ramo è $[1,+\infty)$, deve essere $y\geq1$. Con $t=e^x\geq1$,

$$
2y=t+\frac1t,
$$

da cui

$$
t^2-2yt+1=0.
$$

Le radici sono

$$
t=y\pm\sqrt{y^2-1}.
$$

Per $y>1$ entrambe sono positive e il loro prodotto è $1$. La radice con il segno più è maggiore di $1$, mentre quella con il segno meno è il suo reciproco e appartiene a $(0,1)$. Poiché $x\geq0$ implica $t=e^x\geq1$, si sceglie

$$
t=y+\sqrt{y^2-1}.
$$

Per $y=1$ entrambe le radici coincidono con $1$. Applicando il logaritmo,

$$
\operatorname{arcosh}y
=\ln\left(y+\sqrt{y^2-1}\right),
\qquad y\geq1.
$$

L'argomento del logaritmo è almeno $1$, quindi è positivo. Per $-1<y<1$ la radice $\sqrt{y^2-1}$ non è reale; per $y\leq-1$ la radice è reale, ma $y+\sqrt{y^2-1}<0$ e non è un argomento ammissibile per il logaritmo reale. Soprattutto, ogni $y<1$ è esterno all'immagine reale di $\cosh$: non esiste quindi un valore reale principale di $\operatorname{arcosh}y$.

#### **4.2 Proprietà**

$\operatorname{arcosh}$ è strettamente crescente, ha dominio $[1,+\infty)$ e immagine $[0,+\infty)$. Non è una funzione pari o dispari, perché il dominio non è simmetrico rispetto a zero.

<!-- TODO FIGURA:
Grafico di y=arcosh x per x in [1,6], con punto (1,0), crescita e riflessione del ramo x>=0 di y=cosh x rispetto alla retta y=x. Obiettivo didattico: evidenziare il dominio [1,+infinito) e l'effetto della scelta del ramo principale.
-->

### **5. Tangente iperbolica inversa**

Si definisce

$$
\operatorname{artanh}:(-1,1)\to\mathbb{R}.
$$

#### **5.1 Derivazione della formula logaritmica**

Poniamo

$$
y=\tanh x=\frac{e^{2x}-1}{e^{2x}+1}.
$$

Con $t=e^{2x}>0$,

$$
y=\frac{t-1}{t+1}.
$$

Moltiplicando per $t+1>0$,

$$
yt+y=t-1,
$$

$$
t(1-y)=1+y.
$$

Poiché $-1<y<1$, entrambi $1-y$ e $1+y$ sono positivi. Quindi

$$
t=\frac{1+y}{1-y}>0.
$$

Da $t=e^{2x}$ segue

$$
2x=\ln\left(\frac{1+y}{1-y}\right),
$$

e pertanto

$$
\operatorname{artanh}y
=\frac12\ln\left(\frac{1+y}{1-y}\right),
\qquad -1<y<1.
$$

#### **5.2 Proprietà**

$\operatorname{artanh}$ è strettamente crescente e dispari. Infatti,

$$
\operatorname{artanh}(-x)
=\frac12\ln\left(\frac{1-x}{1+x}\right)
=-\frac12\ln\left(\frac{1+x}{1-x}\right).
$$

Avvicinandosi a $1$ da sinistra, il rapporto $(1+x)/(1-x)$ cresce senza limite; avvicinandosi a $-1$ da destra tende a zero. In termini che saranno formalizzati con i limiti, il grafico ha asintoti verticali $x=\pm1$.

<!-- TODO FIGURA:
Grafico di y=artanh x per x in (-1,1), con origine, simmetria dispari e rette verticali tratteggiate x=-1 e x=1. Affiancare la riflessione del grafico di tanh rispetto a y=x. Obiettivo didattico: mostrare dominio limitato, immagine R e comportamento ai bordi.
-->

### **6. Composizioni dirette e inverse**

Per ogni $x\in\mathbb{R}$,

$$
\sinh(\operatorname{arsinh}x)=x,
$$

$$
\operatorname{arsinh}(\sinh x)=x.
$$

Per ogni $x\geq1$,

$$
\cosh(\operatorname{arcosh}x)=x.
$$

Per ogni $x\in\mathbb{R}$,

$$
\operatorname{arcosh}(\cosh x)=|x|.
$$

Infatti, se $x\geq0$, l'inversa restituisce $x$; se $x<0$, la parità dà $\cosh x=\cosh(-x)$ e il ramo principale restituisce $-x=|x|$. La restrizione a valori non negativi elimina l'informazione sul segno dell'argomento originario.

Infine,

$$
\tanh(\operatorname{artanh}x)=x,\qquad x\in(-1,1),
$$

$$
\operatorname{artanh}(\tanh x)=x,\qquad x\in\mathbb{R}.
$$

### **7. Esempi notevoli e verifiche**

Dai calcoli della prima lezione,

$$
\sinh(\ln2)=\frac34,\qquad
\cosh(\ln2)=\frac54,\qquad
\tanh(\ln2)=\frac35.
$$

Quindi

$$
\operatorname{arsinh}\frac34=\ln2,
$$

$$
\operatorname{arcosh}\frac54=\ln2,
$$

$$
\operatorname{artanh}\frac35=\ln2.
$$

Per esempio,

$$
\operatorname{arsinh}\frac34
=\ln\left(\frac34+\sqrt{\frac{9}{16}+1}\right)
=\ln\left(\frac34+\frac54\right)
=\ln2.
$$

### **8. Altre inverse iperboliche: approfondimento**

Le convenzioni sui rami secondari variano tra i testi. Qui si adottano le seguenti.

#### **8.1 Arcocotangente iperbolica**

Si definisce

$$
\operatorname{arcoth}:
(-\infty,-1)\cup(1,+\infty)
\to
\mathbb{R}\setminus\{0\}
$$

come inversa di $\coth$ sui suoi due rami. La formula è

$$
\operatorname{arcoth}x
=\frac12\ln\left(\frac{x+1}{x-1}\right),
\qquad |x|>1.
$$

Il rapporto è positivo su entrambe le componenti del dominio.

#### **8.2 Arcosecante iperbolica**

Si restringe $\operatorname{sech}$ a $[0,+\infty)$, dove è iniettiva, e si definisce

$$
\operatorname{arsech}:(0,1]\to[0,+\infty),
$$

$$
\operatorname{arsech}x
=\operatorname{arcosh}\frac1x
=\ln\left(\frac{1+\sqrt{1-x^2}}{x}\right).
$$

#### **8.3 Arcocosecante iperbolica**

Poiché $\operatorname{csch}$ è iniettiva su $\mathbb{R}\setminus\{0\}$, si definisce

$$
\operatorname{arcsch}:\mathbb{R}\setminus\{0\}
\to\mathbb{R}\setminus\{0\},
$$

$$
\operatorname{arcsch}x
=\operatorname{arsinh}\frac1x.
$$

Queste tre inverse sono meno standard delle principali: occorre sempre controllare notazione e ramo adottati dal testo di riferimento.

### **9. Errori frequenti**

- confondere $\operatorname{arsinh}$ con $\operatorname{csch}$;
- definire $\operatorname{arcosh}$ per valori reali minori di $1$;
- dimenticare la restrizione di $\cosh$ a $[0,+\infty)$;
- scrivere $\operatorname{arcosh}(\cosh x)=x$ anche per $x<0$;
- scegliere la radice negativa nell'equazione per $e^x$;
- dimenticare che $e^x>0$ quando si seleziona la radice;
- applicare il logaritmo senza dimostrare la positività dell'argomento;
- usare $\operatorname{artanh}x$ per $|x|\geq1$;
- confondere l'inversa con il reciproco indicato dall'esponente $-1$;
- applicare formule per inverse secondarie senza dichiarare la convenzione sui rami.

### **10. Collegamenti futuri**

Le formule logaritmiche permetteranno di derivare le inverse iperboliche e di calcolare primitive con radicali $\sqrt{x^2+1}$ e $\sqrt{x^2-1}$. Gli asintoti saranno formalizzati tramite limiti; gli sviluppi locali tramite Taylor; la catenaria e le combinazioni di $e^x$ ed $e^{-x}$ tramite equazioni differenziali.

### **11. Riepilogo**

> ✅ Le inverse principali sono determinate da monotonia e immagini. Le formule logaritmiche derivano dalla positività dell'esponenziale e dalla scelta del ramo compatibile con il dominio ristretto.
