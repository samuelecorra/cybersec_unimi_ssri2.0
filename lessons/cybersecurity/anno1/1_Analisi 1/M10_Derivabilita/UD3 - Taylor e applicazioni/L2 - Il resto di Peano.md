# **M10 UD3 Lezione 2 - Il resto di Peano**

### **1. Il significato del piccolo-o**

Il resto di Peano descrive l'errore di Taylor in termini asintotici. Dire che

$$
R_n(x)=o((x-x_0)^n)
$$

per $x\to x_0$ significa che

$$
\lim_{x\to x_0}\frac{R_n(x)}{(x-x_0)^n}=0.
$$

Quindi il resto è trascurabile rispetto a $(x-x_0)^n$ vicino al centro dello sviluppo.

### **2. Formula di Taylor con resto di Peano: enunciato e dimostrazione**

**Teorema (Taylor con resto di Peano).** Sia $f$ derivabile $n-1$ volte in un intorno di $x_0$ ed esista $f^{(n)}(x_0)$. Allora

$$
f(x)=\sum_{k=0}^{n}\frac{f^{(k)}(x_0)}{k!}(x-x_0)^k+o((x-x_0)^n)
$$

per $x\to x_0$.

*Dimostrazione (per induzione su $n$).* **Base $n=1$**: l'enunciato è $f(x)=f(x_0)+f'(x_0)(x-x_0)+o(x-x_0)$, cioè la caratterizzazione della derivabilità con il piccolo-o già dimostrata in M09 (UD1/L5).

**Passo induttivo.** Supponiamo il teorema vero all'ordine $n-1$ per ogni funzione ammissibile, e sia $f$ come nelle ipotesi. Dobbiamo mostrare che

$$
R(x) = f(x)-T_n(x) = o((x-x_0)^n)
\qquad\text{cioè}\qquad
\lim_{x\to x_0}\frac{f(x)-T_n(x)}{(x-x_0)^n}=0
$$

Il quoziente è una forma $\frac 00$ (numeratore e denominatore si annullano in $x_0$ con le loro prime derivate). Applichiamo **de l'Hôpital** (UD2/L1 — le ipotesi sono verificate: numeratore e denominatore derivabili nell'intorno puntato, $\bigl((x-x_0)^n\bigr)'=n(x-x_0)^{n-1}\neq 0$ per $x\neq x_0$, e il limite del rapporto delle derivate esisterà per quanto segue):

$$
\lim_{x\to x_0}\frac{f(x)-T_n(x)}{(x-x_0)^n}
\;\overset{\text{H}}{=}\;
\lim_{x\to x_0}\frac{f'(x)-T_n'(x)}{n(x-x_0)^{n-1}}
$$

Ora il colpo di scena: $T_n'$ è esattamente il polinomio di Taylor **di ordine $n-1$ della funzione $f'$** centrato in $x_0$ (si verifica derivando termine a termine: i coefficienti diventano $\frac{f^{(k)}(x_0)}{(k-1)!}$, che sono i coefficienti di Taylor di $f'$). E $f'$ soddisfa le ipotesi del teorema all'ordine $n-1$: per **ipotesi induttiva**

$$
f'(x)-T_n'(x) = o((x-x_0)^{n-1})
$$

quindi il rapporto tende a $\frac{1}{n}\cdot 0 = 0$. La catena di l'Hôpital è legittimata a posteriori (il limite del rapporto derivato esiste) e il passo è concluso. $\square$

> 📌 Da notare la finezza sulle ipotesi: servono $n-1$ derivate **nell'intorno** ma solo la derivata $n$-esima **nel punto**. Ed è per questo che la dimostrazione scala l'ordine con l'Hôpital fino a ridursi alla caratterizzazione del primo ordine di M09, senza mai chiedere più regolarità del necessario. Il resto di Peano fornisce anche il complemento di unicità della Lezione 1: se $f(x)=P(x)+o((x-x_0)^n)$ con $P$ di grado $\le n$, allora $P=T_n$ (la differenza dei due sviluppi darebbe un polinomio di grado $\le n$ che è $o((x-x_0)^n)$: impossibile se non nullo, guardando il suo monomio più basso).

Questa forma è particolarmente utile nel calcolo dei limiti, perché permette di sostituire una funzione con la sua parte principale fino all'ordine necessario.

### **3. Sviluppi fondamentali in $0$**

Gli sviluppi di Maclaurin con resto di Peano più usati sono:

$$
e^x=1+x+\frac{x^2}{2}+o(x^2),
$$

$$
\sin x=x-\frac{x^3}{6}+o(x^3),
$$

$$
\cos x=1-\frac{x^2}{2}+o(x^2),
$$

$$
\ln(1+x)=x-\frac{x^2}{2}+o(x^2),
$$

$$
(1+x)^\alpha=1+\alpha x+\frac{\alpha(\alpha-1)}{2}x^2+o(x^2).
$$

### **4. Esempio di limite**

Calcoliamo

$$
\lim_{x\to 0}\frac{e^x-1-x}{x^2}.
$$

Usiamo

$$
e^x=1+x+\frac{x^2}{2}+o(x^2).
$$

Allora

$$
e^x-1-x=\frac{x^2}{2}+o(x^2).
$$

Quindi

$$
\frac{e^x-1-x}{x^2}
=\frac{\frac{x^2}{2}+o(x^2)}{x^2}
=\frac{1}{2}+\frac{o(x^2)}{x^2}.
$$

Poiché

$$
\frac{o(x^2)}{x^2}\to 0,
$$

il limite è

$$
\frac{1}{2}.
$$

### **5. Scelta dell'ordine**

L'ordine dello sviluppo deve essere sufficiente a catturare il primo termine non nullo dopo le cancellazioni. Se nel numeratore i termini fino al primo ordine si cancellano, bisogna sviluppare almeno al secondo ordine; se si cancellano anche quelli, bisogna andare oltre.

> ⚠️ Fermarsi troppo presto in uno sviluppo di Taylor può produrre una forma $0/0$ residua o una conclusione sbagliata.

### **6. Riepilogo**

Il resto di Peano è lo strumento più comodo per i limiti locali. Non dà una stima numerica esplicita dell'errore, ma dice qual è l'ordine di grandezza dell'errore rispetto alla potenza dominante. Per questo è ideale quando interessa il limite, non un'approssimazione con errore controllato.
