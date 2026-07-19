# **M10 UD2 Lezione 1 - I teoremi di l'Hôpital**

### **1. Il problema delle forme indeterminate**

I teoremi di l'Hôpital servono per calcolare limiti di rapporti quando la sostituzione diretta produce una forma indeterminata:

$$
\frac{0}{0}
\qquad\text{oppure}\qquad
\frac{\infty}{\infty}.
$$

L'idea è confrontare la velocità con cui numeratore e denominatore tendono a $0$ o all'infinito. Le derivate misurano proprio una velocità locale, perciò possono sostituire il rapporto originario con un rapporto più semplice.

### **2. Forma $0/0$: enunciato e dimostrazione**

**Teorema (de l'Hôpital, caso $\frac 00$).** Siano $f$ e $g$ derivabili in un intorno puntato di $x_0$, con

$$
\lim_{x\to x_0}f(x)=0,\qquad
\lim_{x\to x_0}g(x)=0,
$$

e con

$$
g'(x)\neq 0
$$

nell'intorno puntato. Se esiste (finito o infinito)

$$
\lim_{x\to x_0}\frac{f'(x)}{g'(x)}=L,
$$

allora

$$
\lim_{x\to x_0}\frac{f(x)}{g(x)}=L.
$$

*Dimostrazione* (lato destro; il sinistro è simmetrico e insieme danno il bilaterale)*.* Prolunghiamo $f$ e $g$ in $x_0$ ponendo $f(x_0)=g(x_0)=0$: con questa scelta le due funzioni diventano **continue** in $x_0$ (il loro limite è $0$ per ipotesi: è l'estensione per continuità di M08/UD5/L3). Per ogni $x>x_0$ nell'intorno, sull'intervallo $[x_0,x]$ le funzioni prolungate sono continue e derivabili all'interno, e $g'\neq 0$: si applica il **teorema di Cauchy** (M09/UD5/L5) nella forma con quoziente:

$$
\frac{f(x)}{g(x)} = \frac{f(x)-f(x_0)}{g(x)-g(x_0)} = \frac{f'(c_x)}{g'(c_x)}
\qquad\text{con } c_x\in(x_0,x)
$$

(il denominatore $g(x)-g(x_0)=g(x)$ non è nullo: se lo fosse, Rolle su $g$ produrrebbe uno zero di $g'$, escluso). Per $x\to x_0^+$ il punto $c_x$ è schiacciato tra $x_0$ e $x$, quindi $c_x\to x_0^+$ (carabinieri); poiché il rapporto $\frac{f'}{g'}$ tende a $L$, per il confronto sugli intorni (come nel teorema del limite della derivata, M09/UD5/L3) si conclude

$$
\frac{f(x)}{g(x)} = \frac{f'(c_x)}{g'(c_x)} \longrightarrow L \qquad\square
$$

> 📌 La dimostrazione rivela la vera natura della regola: **non** si "derivano numeratore e denominatore" per magia — si confrontano gli **incrementi** di $f$ e $g$ dallo stesso punto base, e Cauchy dice che quel confronto è un rapporto di derivate **nello stesso punto intermedio** $c_x$. È per questo che M09 ha costruito Cauchy con l'ausiliaria incrociata: due Lagrange separati darebbero due punti diversi e la prova crollerebbe. Il teorema vale identico per i limiti laterali e, con la sostituzione $x=\frac 1t$, per $x\to\pm\infty$.

### **3. Forma $\infty/\infty$**

Il risultato analogo vale quando

$$
\lim_{x\to x_0}f(x)=\pm\infty,\qquad
\lim_{x\to x_0}g(x)=\pm\infty.
$$

Se il limite del rapporto delle derivate esiste, allora coincide con il limite del rapporto delle funzioni:

$$
\lim_{x\to x_0}\frac{f(x)}{g(x)}
=
\lim_{x\to x_0}\frac{f'(x)}{g'(x)}.
$$

Le stesse idee valgono anche per $x\to+\infty$ o $x\to-\infty$, con le opportune ipotesi. La dimostrazione del caso $\frac{\infty}{\infty}$ è più delicata (serve un confronto di incrementi su $[x,y]$ con entrambi i punti mobili e una stima di "coda"): la assumiamo enunciata con le stesse cautele, senza riprodurla — il caso $\frac 00$ dimostrato sopra è quello richiesto a un orale. Curiosità utile: nel caso $\frac{\infty}{\infty}$ l'ipotesi che $f$ diverga è addirittura superflua (basta $|g|\to+\infty$), ma non conviene ricordare la versione raffinata: conviene ricordare le **ipotesi che si sanno verificare**.

> ⚠️ Quattro condizioni da verificare **ogni volta**, nell'ordine: (1) la forma è davvero $\frac 00$ o $\frac{\infty}{\infty}$; (2) $f,g$ derivabili nell'intorno puntato; (3) $g'\neq 0$ nell'intorno puntato; (4) il limite di $\frac{f'}{g'}$ **esiste** (finito o infinito). Se una manca — in particolare la (4) — il teorema non conclude nulla: il limite originario può benissimo esistere lo stesso (controesempio nella Lezione 4). E la regola è un'**implicazione**, non un'uguaglianza tra funzioni: scrivere $\frac fg=\frac{f'}{g'}$ è un errore concettuale.

### **4. Esempio: forma $0/0$**

Calcoliamo

$$
\lim_{x\to 0}\frac{\sin x}{x}.
$$

La sostituzione diretta dà $0/0$. Applicando l'Hôpital:

$$
\lim_{x\to 0}\frac{\sin x}{x}
=\lim_{x\to 0}\frac{\cos x}{1}
=1.
$$

Questo limite è anche un limite notevole; l'Hôpital lo conferma, ma nei fondamenti teorici il limite notevole viene di solito dimostrato prima e usato per costruire la derivata del seno.

### **5. Esempio: forma $\infty/\infty$**

Calcoliamo

$$
\lim_{x\to+\infty}\frac{\ln x}{x}.
$$

Numeratore e denominatore tendono a $+\infty$. Derivando:

$$
\lim_{x\to+\infty}\frac{\ln x}{x}
=\lim_{x\to+\infty}\frac{1/x}{1}
=\lim_{x\to+\infty}\frac{1}{x}=0.
$$

Quindi il logaritmo cresce più lentamente di una funzione lineare.

### **6. Applicazioni ripetute**

A volte una sola applicazione non basta. Per esempio:

$$
\lim_{x\to 0}\frac{1-\cos x}{x^2}.
$$

La forma è $0/0$. Applicando una prima volta:

$$
\lim_{x\to 0}\frac{\sin x}{2x}.
$$

La forma è ancora $0/0$. Applicando una seconda volta:

$$
\lim_{x\to 0}\frac{\cos x}{2}=\frac{1}{2}.
$$

### **7. Riepilogo**

I teoremi di l'Hôpital permettono di trasformare certi limiti di rapporti nel limite del rapporto delle derivate. Prima dell'applicazione vanno controllate forma indeterminata e ipotesi di derivabilità. Dopo ogni applicazione bisogna rivalutare il nuovo limite, senza procedere automaticamente.
