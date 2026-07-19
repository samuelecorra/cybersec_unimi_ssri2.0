# **M7 UD1 Lezione 3A - Trasformazioni dei grafici**

### **1. Dalla formula ai punti del grafico**

Le trasformazioni permettono di ricavare il grafico di una funzione da un grafico noto senza ricalcolarlo punto per punto. Se

$$
(u,f(u))\in G_f,
$$

la funzione

$$
g(x)=A f(B(x-h))+k,
\qquad A\neq0,
\qquad B\neq0,
$$

si valuta ponendo

$$
u=B(x-h).
$$

Da cui

$$
x=h+\frac{u}{B},
\qquad
g(x)=Af(u)+k.
$$

Pertanto la trasformazione dei punti è

$$
(u,f(u))longmapsto
\left(h+\frac{u}{B},Af(u)+k\right).
$$

> 📌 Le trasformazioni orizzontali si leggono tramite la variabile interna e hanno fattore reciproco $1/B$.

### **2. Traslazioni**

Per $h,k\in\mathbb{R}$:

$$
g(x)=f(x-h)+k
$$

trasla il grafico di $h$ verso destra e di $k$ verso l'alto. Il dominio e l'immagine diventano

$$
D_g=h+D_f=\{h+u:u\in D_f\},
$$

$$
\operatorname{Im}(g)=k+\operatorname{Im}(f).
$$

L'errore tipico è leggere $x-h$ come spostamento a sinistra. Il punto con ascissa originaria $u$ compare quando $x-h=u$, cioè $x=u+h$.

### **3. Dilatazioni e riflessioni**

Per $g(x)=Af(x)$:

- $|A|>1$ dilata verticalmente;
- $0<|A|<1$ comprime verticalmente;
- $A<0$ aggiunge la riflessione rispetto all'asse $x$.

Per $g(x)=f(Bx)$:

- $|B|>1$ comprime orizzontalmente di fattore $|B|$;
- $0<|B|<1$ dilata orizzontalmente di fattore $1/|B|$;
- $B<0$ aggiunge la riflessione rispetto all'asse $y$.

Se $T$ è un periodo di $f$, allora $T/|B|$ è un periodo di $f(Bx)$. Se $T$ è fondamentale e non intervengono degenerazioni, il periodo fondamentale diventa $T/|B|$.

### **4. Trasformazione generale**

Per

$$
g(x)=A f(B(x-h))+k
$$

valgono:

$$
D_g=h+\frac{1}{B}D_f
=\left\{h+\frac{u}{B}:u\in D_f\right\},
$$

$$
\operatorname{Im}(g)=A\operatorname{Im}(f)+k.
$$

Gli zeri soddisfano

$$
f(B(x-h))=-\frac{k}{A}.
$$

Quindi non sono, in generale, le sole trasformazioni degli zeri di $f$ quando $k\neq0$.

Se $u_0$ è punto di massimo di $f$, allora $h+u_0/B$ è punto di massimo di $g$ quando $A>0$ e di minimo quando $A<0$. I valori estremi sono trasformati da $y\mapsto Ay+k$.

#### **4.1 Esempio completo**

Partiamo da $f(u)=u^2$ e poniamo

$$
g(x)=-2(3(x-1))^2+4.
$$

Il vertice $(0,0)$ diventa

$$
(1,4).
$$

Il punto $(1,1)$ diventa

$$
\left(1+\frac13,2\right),
$$

e $(-1,1)$ diventa

$$
\left(1-\frac13,2\right).
$$

La funzione ha dominio $\mathbb{R}$, immagine $(-\infty,4]$ e zeri dati da

$$
-18(x-1)^2+4=0,
$$

cioè

$$
x=1\pm\frac{\sqrt2}{3}.
$$

<!-- TODO FIGURA:
Grafico di base y=x^2 e grafico trasformato y=-2(3(x-1))^2+4 su assi con stessa scala. Evidenziare il percorso dei punti (0,0), (1,1), (-1,1), le compressioni, la riflessione e la traslazione.
Obiettivo didattico: leggere nell'ordine corretto la trasformazione A f(B(x-h))+k e verificare la mappa dei punti.
-->

### **5. Valore assoluto esterno e interno**

Le funzioni

$$
|f(x)|
$$

e

$$
f(|x|)
$$

sono diverse.

Per $|f(x)|$, la parte del grafico sotto l'asse $x$ viene riflessa sopra l'asse; dominio e zeri restano invariati e l'immagine diventa

$$
\{|y|:y\in\operatorname{Im}(f)\}.
$$

Per $f(|x|)$ si usa il ramo di $f$ con ingresso non negativo e lo si riflette rispetto all'asse $y$. Il dominio è

$$
D_{f(|\cdot|)}=\{x\in\mathbb{R}:|x|\in D_f\}
$$

e la funzione risultante è pari.

Con $f(x)=x-1$:

$$
|f(x)|=|x-1|,
\qquad
f(|x|)=|x|-1.
$$

La prima ha un solo zero $x=1$, la seconda due zeri $x=\pm1$.

<!-- TODO FIGURA:
Tre pannelli coordinati: grafico di f(x)=x-1, grafico di |f(x)| e grafico di f(|x|). Evidenziare in colori diversi la riflessione verticale delle ordinate negative e la duplicazione orizzontale del ramo x>=0.
Obiettivo didattico: impedire la confusione tra valore assoluto esterno e interno confrontando zeri, simmetrie e trasformazioni.
-->

### **6. Trasformazioni e proprietà**

- La traslazione verticale può cambiare zeri e segno, ma non la monotonia.
- Una traslazione orizzontale conserva immagine, monotonia relativa e ampiezza delle oscillazioni.
- Moltiplicare per $A<0$ scambia crescita e decrescita, massimi e minimi, positività e negatività.
- La sostituzione $x\mapsto B(x-h)$ conserva iniettività se il dominio viene trasformato coerentemente.
- Parità e disparità possono essere distrutte da una traslazione orizzontale non nulla.

Queste conclusioni derivano dall'ordine e dall'algebra. Asintoti, regolarità nei punti di raccordo e pendenza richiedono limiti o derivate e sono rinviati a M08-M10.

### **7. Famiglie dipendenti da parametri**

Una famiglia

$$
f_a(x)=f(x-a)
$$

non è una singola funzione ma un insieme di funzioni indicizzato dal parametro $a$. Il parametro può cambiare dominio, immagine, zeri o classificazione.

Per

$$
f_a(x)=\sqrt{x-a},
$$

si ha

$$
D_{f_a}=[a,+\infty),
\qquad
\operatorname{Im}(f_a)=[0,+\infty).
$$

Per

$$
g_a(x)=x^2-2ax+a^2-1=(x-a)^2-1,
$$

il minimo è $-1$ in $x=a$ e gli zeri sono $a\pm1$.

<!-- TODO FIGURA:
Famiglia di quattro parabole y=(x-a)^2-1 per valori a=-2,0,1,3, con vertici e zeri etichettati e frecce orizzontali al variare di a.
Obiettivo didattico: leggere un parametro come trasformazione controllata di dominio, punti notevoli e grafico.
-->

### **8. Esercizi**

1. Da $f(x)=\sqrt{x}$ ricava dominio, immagine e punti notevoli di $g(x)=-3\sqrt{2(x+1)}+4$.
2. Descrivi la trasformazione che porta $y=\sin x$ in $y=2\sin(3x-\pi)-1$ e calcola un periodo.
3. Per $f(x)=x^2-4$, confronta dominio, immagine e zeri di $|f(x)|$ e $f(|x|)$.
4. Ricava la formula della funzione ottenuta riflettendo $y=\log x$ rispetto all'asse $y$ e poi traslandola di $2$ verso l'alto.
5. Determina $A,B,h,k$ sapendo che $(0,1)$, $(1,3)$ sul grafico di $f$ diventano $(2,-1)$, $(5,-5)$ sul grafico di $g(x)=Af(B(x-h))+k$.
6. Studia in funzione di $a$ dominio e zeri di $f_a(x)=\sqrt{x-a}/(x+a)$.
7. Spiega quali conclusioni sul grafico trasformato non possono essere giustificate senza continuità o derivate.

### **9. Riepilogo**

> ✅ La formula generale trasforma $(u,f(u))$ in $(h+u/B,Af(u)+k)$; da questa corrispondenza seguono dominio, immagine e punti notevoli.

