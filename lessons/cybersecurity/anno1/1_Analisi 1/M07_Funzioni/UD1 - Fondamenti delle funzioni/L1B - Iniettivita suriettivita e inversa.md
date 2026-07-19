# **M7 UD1 Lezione 1B - Iniettività, suriettività e inversa**

### **1. Tre classificazioni dipendenti da dominio e codominio**

Sia $f:A\to B$.

- $f$ è **iniettiva** se

$$
\forall x_1,x_2\in A,
\qquad
f(x_1)=f(x_2)\Rightarrow x_1=x_2;
$$

- $f$ è **suriettiva** su $B$ se

$$
\forall y\in B\ \exists x\in A:f(x)=y;
$$

- $f$ è **biiettiva** se è sia iniettiva sia suriettiva.

L'iniettività impedisce collisioni; la suriettività richiede

$$
f(A)=B.
$$

> ⚠️ Non ha senso dichiarare una funzione suriettiva senza fissarne il codominio.

Per la stessa formula $x^2$:

$$
f:\mathbb{R}\to\mathbb{R}
$$

non è né iniettiva né suriettiva;

$$
g:\mathbb{R}\to[0,+\infty)
$$

è suriettiva ma non iniettiva;

$$
h:[0,+\infty)\to[0,+\infty)
$$

è biiettiva.

### **2. Test della retta orizzontale**

Una funzione è iniettiva se e solo se ogni retta orizzontale $y=c$ incontra il suo grafico al più una volta. Infatti due intersezioni distinte avrebbero ascisse $x_1\neq x_2$ ma la stessa ordinata:

$$
f(x_1)=c=f(x_2).
$$

Per la suriettività su $B$, ogni livello $c\in B$ deve essere incontrato almeno una volta.

<!-- TODO FIGURA:
Tre pannelli con la parabola su R, la stessa parabola con codominio [0,+infinito) e il solo ramo x>=0. Tracciare alcune rette orizzontali e contare le intersezioni; indicare in ogni pannello iniettività e suriettività.
Obiettivo didattico: mostrare graficamente che entrambe le proprietà dipendono dagli insiemi dichiarati, non soltanto dalla formula.
-->

### **3. Funzione inversa**

Una funzione $f:A\to B$ è invertibile se esiste $g:B\to A$ tale che

$$
g\circ f=\operatorname{id}_A
$$

e

$$
f\circ g=\operatorname{id}_B.
$$

In tal caso $g$ è unica e si indica con $f^{-1}$.

#### **3.1 Teorema: invertibilità se e solo se biiettività**

**Ipotesi.** $f:A\to B$.

**Tesi.** $f$ ammette inversa bilatera se e solo se è biiettiva.

**Dimostrazione.** Supponiamo che esista $g:B\to A$ con $g\circ f=\operatorname{id}_A$ e $f\circ g=\operatorname{id}_B$. Se $f(x_1)=f(x_2)$, applicando $g$ segue

$$
x_1=g(f(x_1))=g(f(x_2))=x_2,
$$

quindi $f$ è iniettiva. Per ogni $y\in B$, posto $x=g(y)$, si ha $f(x)=f(g(y))=y$, quindi $f$ è suriettiva.

Viceversa, supponiamo $f$ biiettiva. Per ogni $y\in B$, la suriettività garantisce almeno un $x\in A$ con $f(x)=y$ e l'iniettività ne garantisce l'unicità. Definiamo $g(y)$ come quell'unico $x$. Allora $g(f(x))=x$ e $f(g(y))=y$, perciò $g=f^{-1}$. $\square$

### **4. Inverse sinistre e destre**

Una funzione $\ell:B\to A$ è un'inversa sinistra di $f$ se

$$
\ell\circ f=\operatorname{id}_A.
$$

La sua esistenza implica che $f$ è iniettiva. Una funzione $r:B\to A$ è un'inversa destra se

$$
f\circ r=\operatorname{id}_B,
$$

e la sua esistenza implica che $f$ è suriettiva.

Gli implicazioni si dimostrano come nei due versi del teorema precedente. In generale inverse unilaterali non sono uniche.

Esempio di inversa sinistra: per

$$
f:\mathbb{R}\to\mathbb{R}^2,
\qquad
f(x)=(x,0),
$$

la proiezione $\ell(u,v)=u$ soddisfa $\ell\circ f=\operatorname{id}_{\mathbb{R}}$, ma $f$ non è suriettiva.

Esempio di inversa destra: per

$$
p:\mathbb{R}^2\to\mathbb{R},
\qquad
p(u,v)=u,
$$

la funzione $r(t)=(t,0)$ soddisfa $p\circ r=\operatorname{id}_{\mathbb{R}}$, ma $p$ non è iniettiva.

### **5. Costruire l'inversa**

Per una funzione biiettiva data da una formula:

1. si scrive $y=f(x)$;
2. si risolve rispetto a $x$;
3. si scambiano i ruoli delle variabili;
4. si dichiarano dominio e codominio dell'inversa;
5. si verificano entrambe le composizioni.

Per

$$
f:\mathbb{R}\to\mathbb{R},
\qquad
f(x)=3x-2,
$$

da $y=3x-2$ segue

$$
x=\frac{y+2}{3}.
$$

Quindi

$$
f^{-1}(y)=\frac{y+2}{3}.
$$

La verifica è

$$
f^{-1}(f(x))=\frac{3x-2+2}{3}=x,
$$

$$
f(f^{-1}(y))=3\frac{y+2}{3}-2=y.
$$

Per $x^2$ occorre invece restringere il dominio. La biiezione

$$
q:[0,+\infty)\to[0,+\infty),
\qquad
q(x)=x^2,
$$

ha inversa $q^{-1}(y)=\sqrt y$. Sul dominio $(-\infty,0]$ l'inversa sarebbe $-\sqrt y$.

<!-- TODO FIGURA:
Parabola y=x^2 con i due rami evidenziati separatamente; mostrare la restrizione x>=0 e la sua riflessione y=sqrt(x) rispetto alla retta y=x. Segnare coppie corrispondenti (a,a^2) e (a^2,a).
Obiettivo didattico: visualizzare perché la restrizione è necessaria e come dominio e immagine si scambiano nell'inversa.
-->

### **6. Grafico dell'inversa**

Se $f$ è biiettiva,

$$
(x,y)\in G_f
\iff y=f(x)
\iff x=f^{-1}(y)
\iff (y,x)\in G_{f^{-1}}.
$$

Pertanto i grafici di $f$ e $f^{-1}$ sono simmetrici rispetto alla retta $y=x$.

<!-- TODO FIGURA:
Piano cartesiano con y=x, il grafico di una biiezione crescente non lineare e il grafico della sua inversa in colori distinti. Collegare con segmenti perpendicolari a y=x tre coppie (x,f(x)) e (f(x),x).
Obiettivo didattico: derivare geometricamente lo scambio delle coordinate tra funzione e inversa.
-->

> ⚠️ $f^{-1}$ non è il reciproco $1/f$. Per $f(x)=2x$, si ha $f^{-1}(x)=x/2$, mentre $1/f(x)=1/(2x)$.

### **7. Applicazioni informatiche e di sicurezza**

Una funzione hash ideale riduce un insieme enorme di messaggi a un insieme finito di digest. Non può essere iniettiva: per il principio dei cassetti esistono collisioni. La resistenza alle collisioni è una proprietà **computazionale**, non l'assenza matematica di collisioni.

Una codifica decodificabile deve essere iniettiva sul dominio dei messaggi ammessi. Una permutazione di un insieme finito è una biiezione e ammette inversa. Una funzione detta one-way può essere biiettiva e dunque matematicamente invertibile, ma progettata perché calcolare l'inversa senza informazione segreta sia impraticabile.

### **8. Esercizi**

1. Classifica $f:\mathbb{R}\to\mathbb{R}$, $f(x)=x^3-x$, rispetto a iniettività e suriettività senza usare derivate.
2. Per la formula $e^x$, scegli tre codomini diversi e stabilisci in quali casi la funzione è suriettiva.
3. Trova una restrizione invertibile di $x\mapsto|x-2|$ e costruiscine l'inversa.
4. Dimostra direttamente che una inversa bilatera è unica.
5. Verifica che $f(x)=(1-x)/(1+x)$ è una involuzione sul dominio corretto.
6. Costruisci due inverse destre diverse della proiezione $p(u,v)=u$.
7. Spiega perché una funzione hash $H:\{0,1\}^{512}\to\{0,1\}^{256}$ non può essere iniettiva.
8. Determina se la relazione $y^2=x$ permette di ottenere una funzione inversa del quadrato senza restrizioni.

### **9. Riepilogo**

> ✅ L'iniettività riguarda l'unicità delle controimmagini, la suriettività la copertura del codominio; una funzione è invertibile esattamente quando entrambe valgono.
