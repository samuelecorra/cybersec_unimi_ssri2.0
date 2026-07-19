# **M8 UD1 Lezione 3B - Prime dimostrazioni con la definizione e negazione del limite**

### **1. Obiettivo della lezione**

La definizione $\varepsilon$–$\delta$ non serve solo a "sapere cos'è" un limite: è uno strumento con cui si **dimostra** che un limite vale, e — tramite la sua negazione — che un limite **non** vale. In questa lezione impariamo entrambe le direzioni con dimostrazioni complete, che sono il prototipo di ogni domanda d'orale sul tema.

Lo schema di ogni dimostrazione diretta è sempre lo stesso:

1. si fissa un $\varepsilon>0$ **arbitrario** (non un valore particolare: deve funzionare per tutti);
2. si lavora sulla quantità $|f(x)-L|$ maggiorandola con un'espressione in $|x-x_0|$;
3. si esibisce un $\delta>0$, **in funzione di $\varepsilon$** (mai in funzione di $x$), tale che $0<|x-x_0|<\delta$ garantisca $|f(x)-L|<\varepsilon$;
4. si conclude.

---
### **2. Il limite di una funzione affine**

**Proposizione.** Per ogni $m,q,x_0\in\mathbb{R}$:

$$
\lim_{x\to x_0}(mx+q) = mx_0+q
$$

*Dimostrazione.* Se $m=0$ la funzione è costante: $|f(x)-L| = |q-q| = 0<\varepsilon$ per ogni $x$, e qualunque $\delta$ funziona. Sia $m\neq 0$. Fissato $\varepsilon>0$, stimiamo:

$$
\left|(mx+q)-(mx_0+q)\right| = |m|\,|x-x_0|
$$

Scegliamo

$$
\delta = \frac{\varepsilon}{|m|}
$$

Allora, se $0<|x-x_0|<\delta$:

$$
|f(x)-L| = |m|\,|x-x_0| < |m|\cdot\frac{\varepsilon}{|m|} = \varepsilon
$$

che è la tesi. $\square$

Nota il punto didattico: $\delta$ dipende da $\varepsilon$ (e dal parametro fisso $m$), **non** da $x$. Più ripida è la retta (grande $|m|$), più piccolo deve essere $\delta$ a parità di $\varepsilon$.

<!-- TODO FIGURA:
Grafico di una retta y = mx+q con m > 1. Fascia orizzontale L±epsilon attorno a L = m x_0 + q e fascia verticale x_0±delta con delta = epsilon/|m| visibilmente più stretta della fascia orizzontale. Un secondo pannello con una retta meno ripida dove delta risulta più largo.
Obiettivo didattico: visualizzare la dipendenza di delta da epsilon e dalla pendenza: più la funzione varia in fretta, più la precisione orizzontale deve essere severa.
-->

---
### **3. Il limite di $x^2$: quando serve controllare un fattore**

**Proposizione.** Per ogni $x_0\in\mathbb{R}$:

$$
\lim_{x\to x_0}x^2 = x_0^2
$$

*Dimostrazione.* Fissato $\varepsilon>0$, la quantità da controllare si fattorizza:

$$
|x^2-x_0^2| = |x-x_0|\,|x+x_0|
$$

Il primo fattore lo comanda $\delta$; il problema è il secondo, che dipende da $x$. L'idea standard è **controllarlo localmente**: imponiamo in partenza $\delta\le 1$. Allora da $|x-x_0|<1$ segue, per la disuguaglianza triangolare (M01),

$$
|x+x_0| = |(x-x_0)+2x_0| \le |x-x_0|+2|x_0| < 1+2|x_0|
$$

Quindi, finché $|x-x_0|<\delta\le 1$:

$$
|x^2-x_0^2| < (1+2|x_0|)\,|x-x_0|
$$

Basta allora scegliere

$$
\delta = \min\left\{1,\ \frac{\varepsilon}{1+2|x_0|}\right\}
$$

per ottenere $|x^2-x_0^2| < (1+2|x_0|)\cdot\dfrac{\varepsilon}{1+2|x_0|} = \varepsilon$. $\square$

> ⚠️ L'errore classico qui è "scegliere $\delta = \dfrac{\varepsilon}{|x+x_0|}$": **illegale**, perché $\delta$ dipenderebbe da $x$, cioè dal punto che stiamo testando. Il quantificatore $\exists\delta\ \forall x$ impone un $\delta$ unico per tutti gli $x$ dell'intorno. Il trucco $\delta\le 1$ serve esattamente a sostituire il fattore variabile $|x+x_0|$ con la costante $1+2|x_0|$.

---
### **4. Il limite del valore assoluto**

**Proposizione.** Per ogni $x_0\in\mathbb{R}$:

$$
\lim_{x\to x_0}|x| = |x_0|
$$

*Dimostrazione.* Per la disuguaglianza triangolare inversa (M01):

$$
\big|\,|x|-|x_0|\,\big| \le |x-x_0|
$$

Fissato $\varepsilon>0$, la scelta $\delta=\varepsilon$ funziona immediatamente: $0<|x-x_0|<\delta \Rightarrow \big||x|-|x_0|\big|<\varepsilon$. $\square$

---
### **5. Una funzione razionale elementare**

**Proposizione.** Per ogni $x_0\neq 0$:

$$
\lim_{x\to x_0}\frac{1}{x} = \frac{1}{x_0}
$$

*Dimostrazione.* Per $x\neq 0$:

$$
\left|\frac{1}{x}-\frac{1}{x_0}\right| = \frac{|x-x_0|}{|x|\,|x_0|}
$$

Di nuovo un fattore dipendente da $x$, stavolta a denominatore: dobbiamo impedire a $|x|$ di avvicinarsi a $0$. Imponiamo $\delta\le\dfrac{|x_0|}{2}$: da $|x-x_0|<\dfrac{|x_0|}{2}$ segue

$$
|x| \ge |x_0|-|x-x_0| > |x_0|-\frac{|x_0|}{2} = \frac{|x_0|}{2}
$$

(in particolare $x\neq 0$: l'intorno è tutto dentro il dominio). Quindi:

$$
\left|\frac{1}{x}-\frac{1}{x_0}\right| < \frac{|x-x_0|}{\frac{|x_0|}{2}\cdot|x_0|} = \frac{2}{x_0^2}\,|x-x_0|
$$

Fissato $\varepsilon>0$, la scelta

$$
\delta = \min\left\{\frac{|x_0|}{2},\ \frac{x_0^2}{2}\,\varepsilon\right\}
$$

dà $\left|\frac{1}{x}-\frac{1}{x_0}\right| < \dfrac{2}{x_0^2}\cdot\dfrac{x_0^2}{2}\varepsilon = \varepsilon$. $\square$

> 💡 Le tre dimostrazioni condividono la stessa architettura: maggiorare $|f(x)-L|$ con $C\cdot|x-x_0|$ dove $C$ è una **costante locale** ottenuta imprigionando i fattori variabili in un intorno prefissato. Questa idea ("stima locale + scelta di $\delta$ come minimo di due vincoli") tornerà identica nelle dimostrazioni dell'algebra dei limiti (Lezione 3F).

---
### **6. La negazione della definizione**

Che cosa significa, con i quantificatori, che $\lim_{x\to x_0}f(x)\neq L$? Si nega la definizione scambiando sistematicamente $\forall\leftrightarrow\exists$ e negando il predicato finale:

$$
\exists\,\varepsilon_0>0 \;\; \forall\delta>0 \;\; \exists\, x\in A:\quad 0<|x-x_0|<\delta \ \text{ e } \ |f(x)-L|\ge\varepsilon_0
$$

Lettura: esiste una **tolleranza critica** $\varepsilon_0$ che non si riesce mai a garantire; per quanto si stringa la finestra $\delta$, dentro c'è sempre almeno un punto del dominio la cui immagine dista da $L$ almeno $\varepsilon_0$.

Attenzione alle **tre** cose che cambiano:

1. $\forall\varepsilon\ \to\ \exists\varepsilon_0$: basta **un** cattivo $\varepsilon$;
2. $\exists\delta\ \to\ \forall\delta$: il fallimento deve avvenire per **ogni** $\delta$;
3. l'implicazione "$0<|x-x_0|<\delta \Rightarrow |f(x)-L|<\varepsilon$" si nega con "**esiste** $x$ che soddisfa l'ipotesi ma viola la conclusione" (una congiunzione, non un'implicazione).

Dire poi che "il limite di $f$ in $x_0$ **non esiste**" significa che la negazione qui sopra vale **per ogni** $L\in\mathbb{R}$ (ed eventualmente che non vale nemmeno il limite $\pm\infty$).

---
### **7. Una dimostrazione di non convergenza**

**Proposizione.** La funzione segno, $f(x)=\dfrac{|x|}{x}$ (definita per $x\neq 0$), non ammette limite per $x\to 0$: per **nessun** $L\in\mathbb{R}$ vale $\lim_{x\to 0}f(x)=L$.

*Dimostrazione.* Osserviamo che $f(x)=1$ per $x>0$ e $f(x)=-1$ per $x<0$. Fissiamo $L\in\mathbb{R}$ qualunque e mostriamo la negazione con $\varepsilon_0=1$.

Sia $\delta>0$ arbitrario. I punti $x_+=\dfrac{\delta}{2}$ e $x_-=-\dfrac{\delta}{2}$ soddisfano entrambi $0<|x|<\delta$, e:

$$
|f(x_+)-L| = |1-L|, \qquad |f(x_-)-L| = |-1-L| = |1+L|
$$

Se entrambe le distanze fossero minori di $1$, per la disuguaglianza triangolare avremmo

$$
2 = |(1-L)+(1+L)| \le |1-L|+|1+L| < 1+1 = 2
$$

assurdo. Quindi almeno uno dei due punti dista da $L$ **almeno** $1=\varepsilon_0$: per ogni $\delta$ esiste un $x$ con $0<|x|<\delta$ e $|f(x)-L|\ge\varepsilon_0$. La negazione della definizione è verificata per ogni $L$. $\square$

(Vedremo nella Lezione 3C il modo più rapido di dire la stessa cosa: i limiti laterali esistono ma valgono $1\neq -1$.)

<!-- TODO FIGURA:
Grafico della funzione segno |x|/x: semiretta y=1 per x>0 e y=-1 per x<0, cerchietti vuoti in (0,1) e (0,-1). Un candidato L generico segnato sull'asse y con la fascia L±1 tratteggiata: la fascia non può contenere contemporaneamente 1 e -1. Punti campione x_+=delta/2 e x_-=-delta/2 evidenziati dentro una finestra ±delta.
Obiettivo didattico: rendere visiva la dimostrazione di non esistenza: nessuna fascia orizzontale di semiampiezza 1 cattura entrambi i rami, qualunque sia delta.
-->

---
### **8. Verifica di un limite infinito con la definizione**

**Proposizione.** $\displaystyle\lim_{x\to 0}\frac{1}{x^2} = +\infty$.

*Dimostrazione.* Ricordiamo la definizione (Lezione 3C per il quadro completo): per ogni $M>0$ deve esistere $\delta>0$ tale che $0<|x|<\delta \Rightarrow \dfrac{1}{x^2}>M$. Fissato $M>0$, scegliamo

$$
\delta = \frac{1}{\sqrt{M}}
$$

Se $0<|x|<\delta$ allora $x^2<\delta^2=\dfrac{1}{M}$ e quindi $\dfrac{1}{x^2}>M$. $\square$

---
### **9. Errori frequenti**

- ❌ Fissare un $\varepsilon$ numerico ("prendo $\varepsilon=0{,}01$ e funziona") e dichiarare concluso: la definizione richiede **ogni** $\varepsilon>0$.
- ❌ Scegliere $\delta$ dipendente da $x$ (vedi punto 3): l'ordine dei quantificatori lo vieta.
- ❌ Dimenticare $0<|x-x_0|$: nei limiti il punto $x_0$ non si testa mai; includerlo cambia la natura dell'affermazione (diventa continuità, se $f(x_0)$ esiste).
- ❌ Nella negazione, scrivere "$\forall\varepsilon\ \forall\delta\ \exists x$": no, il $\varepsilon_0$ cattivo è **uno** ed è fissato prima di tutti i $\delta$.
- ❌ Concludere "il limite non esiste" mostrando il fallimento di **un solo** candidato $L$: la non esistenza richiede di escludere ogni $L$ (come al punto 7), oppure di usare criteri indiretti (laterali diversi, criterio sequenziale della Lezione 3E).

---
### **10. Esercizi**

**Esercizio 1.** Dimostra con la definizione che $\lim_{x\to 2}(3x-1)=5$.

*Soluzione.* $|3x-1-5|=3|x-2|$; dato $\varepsilon>0$ basta $\delta=\frac{\varepsilon}{3}$.

**Esercizio 2.** Dimostra con la definizione che $\lim_{x\to 1}\dfrac{x^2-1}{x-1}=2$.

*Soluzione.* Per $x\neq 1$ la funzione coincide con $x+1$, quindi $\left|\frac{x^2-1}{x-1}-2\right| = |x-1|$: basta $\delta=\varepsilon$. (Qui si vede il carattere locale: il valore in $1$ non esiste, ma il limite sì.)

**Esercizio 3.** Dimostra con la definizione che $\lim_{x\to 3}x^2=9$, esplicitando la scelta di $\delta$.

*Soluzione.* $|x^2-9|=|x-3||x+3|$; con $\delta\le 1$ si ha $|x+3|<7$, quindi $\delta=\min\left\{1,\frac{\varepsilon}{7}\right\}$.

**Esercizio 4.** Dimostra che $\lim_{x\to 0}\cos\dfrac{1}{x}$ non esiste, adattando la tecnica del punto 7.

*Soluzione (traccia).* Per ogni $\delta>0$ scegli $x_1=\frac{1}{2k\pi}$ e $x_2=\frac{1}{(2k+1)\pi}$ con $k$ abbastanza grande da avere $x_1,x_2\in(0,\delta)$: i valori sono $\cos(2k\pi)=1$ e $\cos((2k+1)\pi)=-1$, e l'argomento con $\varepsilon_0=1$ del punto 7 si ripete parola per parola.

---
### **11. Riepilogo**

> ✅ Dimostrare un limite $=$ dato $\varepsilon$ arbitrario, costruire $\delta(\varepsilon)$ maggiorando $|f(x)-L|$ con una costante locale per $|x-x_0|$; le scelte tipo $\delta=\min\{1,\cdot\}$ servono a bloccare i fattori dipendenti da $x$. Negare un limite $=$ esibire $\varepsilon_0$ tale che ogni finestra $\delta$ contenga un punto "ribelle". La non esistenza si dimostra escludendo tutti gli $L$ (o, più comodamente, con laterali o successioni: Lezioni 3C e 3E).
