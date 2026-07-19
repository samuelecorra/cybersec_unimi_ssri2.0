# **M10 UD4 Lezione 2 - Determinazione del verso della concavità**

### **0. Il criterio, con la sua dimostrazione**

**Teorema.** Se $f$ è due volte derivabile sull'intervallo $I$ e $f''\ge 0$ su $I$, allora $f$ è convessa su $I$ (con $f''>0$, strettamente); simmetricamente per la concavità.

*Dimostrazione.* $f''\ge 0$ su $I$ significa che $f'$ è crescente (criterio di monotonia applicato a $f'$: M09/UD5/L4), e "$f'$ crescente $\Rightarrow$ $f$ convessa" è il teorema della Lezione 1. Due ingranaggi già costruiti, nessun conto nuovo. $\square$

E per i **flessi** la condizione necessaria, analogo esatto di Fermat: se $x_0$ è un flesso interno e $f''(x_0)$ esiste, allora $f''(x_0)=0$ — perché nei due lati $f''$ ha segni opposti (definizione di cambio di concavità con il criterio) e per permanenza del segno un valore non nullo di $f''(x_0)$ ne fisserebbe il segno in tutto un intorno. Come per Fermat: **necessaria, non sufficiente** ($f(x)=x^4$ ha $f''(0)=0$ senza flesso). Nel punto di flesso con tangente, il grafico **attraversa la propria tangente**: sta da parti opposte nei due lati — la fotografia geometrica del cambio di concavità.

### **1. Procedura generale**

Per determinare il verso della concavità di una funzione si segue una procedura simile allo studio della monotonia:

1. si determina il dominio;
2. si calcola la prima derivata;
3. si calcola la seconda derivata;
4. si studia il segno di $f''$;
5. si interpretano gli intervalli ottenuti.

I punti che separano gli intervalli sono gli zeri di $f''$, i punti in cui $f''$ non esiste e i punti esclusi dal dominio.

### **2. Esempio svolto**

Studiamo la concavità di

$$
f(x)=x^4-2x^2.
$$

La funzione è definita su tutto $\mathbb{R}$. Deriviamo:

$$
f'(x)=4x^3-4x.
$$

Deriviamo ancora:

$$
f''(x)=12x^2-4=4(3x^2-1).
$$

Cerchiamo gli zeri:

$$
3x^2-1=0
\quad\Rightarrow\quad
x^2=\frac{1}{3}
\quad\Rightarrow\quad
x=\pm\frac{1}{\sqrt{3}}.
$$

Il segno di $f''$ è positivo quando

$$
|x|>\frac{1}{\sqrt{3}},
$$

e negativo quando

$$
|x|<\frac{1}{\sqrt{3}}.
$$

Quindi $f$ è convessa su

$$
\left(-\infty,-\frac{1}{\sqrt{3}}\right)
\cup
\left(\frac{1}{\sqrt{3}},+\infty\right)
$$

e concava su

$$
\left(-\frac{1}{\sqrt{3}},\frac{1}{\sqrt{3}}\right).
$$

Poiché la seconda derivata cambia segno in entrambi i punti, ci sono due flessi.

### **3. Flessi e tangente**

Un flesso può avere tangente orizzontale oppure no. Per

$$
f(x)=x^3
$$

il punto $0$ è un flesso e

$$
f'(0)=0.
$$

La tangente è orizzontale.

Per

$$
g(x)=x^3+x
$$

si ha

$$
g''(x)=6x,
$$

quindi $0$ è ancora un flesso, ma

$$
g'(0)=1.
$$

La tangente non è orizzontale.

### **4. Criterio del secondo ordine per estremi**

La seconda derivata aiuta anche a classificare punti stazionari. Se

$$
f'(x_0)=0
$$

e

$$
f''(x_0)>0,
$$

allora $x_0$ è punto di minimo locale. Se invece

$$
f''(x_0)<0,
$$

allora $x_0$ è punto di massimo locale.

Se

$$
f''(x_0)=0,
$$

il criterio non decide: bisogna usare il segno della derivata prima, derivate di ordine superiore o lo sviluppo di Taylor.

> ⚠️ La condizione $f''(x_0)=0$ non significa automaticamente flesso. Serve il cambio di concavità.

### **5. Riepilogo**

La concavità si determina studiando il segno della seconda derivata. Gli zeri di $f''$ sono candidati flessi, ma diventano flessi solo se il segno cambia. La seconda derivata fornisce anche un criterio rapido per massimi e minimi, purché il valore in $x_0$ sia diverso da zero.
