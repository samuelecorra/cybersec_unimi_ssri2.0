# **M5 UD4 Lezione 2 - Limiti notevoli**

### **1. Ruolo dei limiti notevoli**

Un limite notevole è un risultato già dimostrato che può essere riconosciuto dentro espressioni più complesse. Non sostituisce il controllo delle ipotesi: occorre identificare la variabile infinitesima, verificare il dominio e giustificare ogni sostituzione.

> 📌 In questo modulo sono già fondati i limiti puramente sequenziali. I limiti trigonometrici, logaritmici ed esponenziali vengono formulati anche nella loro versione per successioni, ma la loro dimostrazione organica appartiene ai limiti di funzione e alla continuità.

### **2. Catalogo dei risultati già dimostrati**

Per $q\in\mathbb{R}$,

$$
q^n\to 0\quad\Longleftrightarrow\quad |q|<1.
$$

Inoltre,

$$
\sqrt[n]{a}\to1\quad(a>0),
\qquad
\sqrt[n]{n}\to1,
$$

e, per ogni $\alpha>0$ e $a>1$,

$$
\frac{n^\alpha}{a^n}\to0,
\qquad
\frac{a^n}{n!}\to0,
\qquad
\frac{n!}{n^n}\to0.
$$

La successione che definisce $e$ soddisfa

$$
\left(1+\frac{1}{n}\right)^n\to e.
$$

Questi risultati consentono già di trattare numerosi rapporti tra potenze, esponenziali e fattoriali senza usare limiti di funzione.

### **3. Forma esponenziale**

La forma più comune è

$$
\left(1+\frac{a}{n}\right)^n\to e^a.
$$

Per esempio,

$$
\left(1-\frac{2}{n}\right)^n\to e^{-2},
\qquad
\left(1+\frac{3}{n}\right)^{2n}\to e^6.
$$

Una formulazione generale, da usare dopo aver verificato la positività definitiva della base, è:

$$
u_n\to0,\quad v_n\to\pm\infty,\quad u_nv_n\to L
\quad\Longrightarrow\quad
(1+u_n)^{v_n}\to e^L.
$$

La sola informazione $u_n\to0$ e $v_n\to\infty$ non basta. Per esempio,

$$
\left(1+\frac{1}{n}\right)^{n^2}\to+\infty,
\qquad
\left(1+\frac{1}{n^2}\right)^n\to1.
$$

La giustificazione completa della formulazione generale usa $v_n\ln(1+u_n)$ e sarà disponibile dopo lo studio del logaritmo come funzione continua.

### **4. Limiti trigonometrici in forma sequenziale**

Se $u_n\to0$ e $u_n\neq0$ definitivamente, allora

$$
\frac{\sin u_n}{u_n}\to1.
$$

Ne segue

$$
n\sin\frac{1}{n}
=\frac{\sin(1/n)}{1/n}\to1.
$$

Dall'identità $1-\cos x=2\sin^2(x/2)$ si ricava

$$
\frac{1-\cos u_n}{u_n^2}
=\frac12\left(\frac{\sin(u_n/2)}{u_n/2}\right)^2\to\frac12.
$$

Quindi

$$
n^2\left(1-\cos\frac{1}{n}\right)\to\frac12.
$$

La dimostrazione geometrica della disuguaglianza

$$
\cos x\leq\frac{\sin x}{x}\leq1
$$

per $0<|x|<\pi/2$, e il conseguente teorema dei carabinieri, saranno coordinati con il modulo sui limiti di funzione.

### **5. Limiti logaritmici ed esponenziali**

Se $u_n\to0$, $u_n>-1$ definitivamente e $u_n\neq0$ definitivamente, allora

$$
\frac{\ln(1+u_n)}{u_n}\to1.
$$

Se invece $u_n\to0$ e $u_n\neq0$ definitivamente,

$$
\frac{e^{u_n}-1}{u_n}\to1.
$$

Le due equivalenze sono inverse l'una dell'altra e dipendono dalla continuità e invertibilità locale di esponenziale e logaritmo. Applicandole,

$$
n\ln\left(1+\frac{1}{n}\right)\to1
$$

e

$$
n\left(e^{2/n}-1\right)
=2\frac{e^{2/n}-1}{2/n}\to2.
$$

> ⚠️ Questi risultati sono anticipazioni dichiarate, non dimostrazioni nascoste basate sulla derivata. In M05 si possono usare come limiti notevoli esplicitamente richiamati; la loro prova sarà data nel modulo appropriato.

### **6. Metodo di riconoscimento**

Per applicare un limite notevole:

1. isolare una successione $u_n\to0$;
2. riscrivere l'espressione esattamente nella forma standard;
3. compensare i fattori introdotti;
4. controllare dominio e denominatori non nulli;
5. applicare l'aritmetica dei limiti.

Per esempio,

$$
n^2\sin\frac{3}{n^2}
=3\frac{\sin(3/n^2)}{3/n^2}\to3.
$$

Non si è effettuata una sostituzione approssimata: l'uguaglianza è esatta e il limite notevole viene applicato al rapporto.

### **7. Errori frequenti**

- Trattare $1^\infty$ come un valore invece che come una forma indeterminata.
- Usare $\sin u_n\sim u_n$ senza aver verificato $u_n\to0$.
- Dimenticare la condizione $u_n>-1$ per $\ln(1+u_n)$.
- Confondere una formula anticipata con un risultato già dimostrato nel percorso.
- Sostituire equivalenti dentro una differenza in cui i termini principali si cancellano.

### **8. Esercizi**

1. Calcolare $\lim_{n\to\infty}\left(1+\frac{4}{n}\right)^{3n}$.
2. Confrontare i limiti di $\left(1+1/n\right)^n$, $\left(1+1/n\right)^{\sqrt n}$ e $\left(1+1/n\right)^{n^2}$.
3. Calcolare $\lim n\sin(5/n)$ e giustificare ogni passaggio.
4. Calcolare $\lim n^2(1-\cos(2/n))$.
5. Calcolare $\lim n\ln(1-1/(2n))$, verificando il dominio.
6. Calcolare $\lim n(e^{a/n}-1)$ per $a\in\mathbb{R}$.
7. Stabilire quali limiti del catalogo sono stati dimostrati interamente in M05 e quali sono anticipazioni.
8. Trovare due successioni della forma $(1+u_n)^{v_n}$, entrambe di tipo $1^\infty$, con limiti diversi.
9. Correggere il ragionamento falso: «$\sin(1/n)\to0$ e $1/n\to0$, dunque il loro rapporto tende a $0/0=1$».

### **9. Riepilogo**

> ✅ I limiti notevoli sono teoremi con ipotesi precise. In M05 sono pienamente fondati i risultati geometrici, algebrici e combinatori sulle successioni; i limiti di seno, coseno, logaritmo ed esponenziale sono coordinati esplicitamente con i successivi moduli sui limiti di funzione.
