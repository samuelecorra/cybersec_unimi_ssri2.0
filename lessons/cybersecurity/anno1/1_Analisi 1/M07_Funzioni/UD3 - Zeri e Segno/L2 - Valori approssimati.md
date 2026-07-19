# **M7 UD3 Lezione 2 - Valori approssimati**

### **1. Introduzione**

Non sempre gli zeri di una funzione si possono calcolare esattamente con formule elementari.

Equazioni come:

$$
\cos x=x
$$

oppure:

$$
e^x=x+2
$$

non hanno in generale soluzioni esprimibili con strumenti algebrici semplici. In questi casi si cercano valori approssimati.

> 📌 Un valore approssimato di uno zero è un numero vicino alla soluzione reale dell’equazione $f(x)=0$.

---

### **2. Localizzare uno zero**

Un primo metodo consiste nel trovare un intervallo:

$$
[a,b]
$$

tale che:

$$
f(a)f(b)<0
$$

cioè $f(a)$ e $f(b)$ hanno segni opposti.

Se $f$ è continua, allora esiste almeno uno zero in $(a,b)$.

Questo è il teorema degli zeri, che sarà formalizzato nel modulo sulla continuità.

La formulazione e la dimostrazione analitica appartengono a [M08 UD5 L4](../../M08_Limiti/UD5 - Continuita e teoremi globali/L4 - Teorema degli zeri e dei valori intermedi, metodo di bisezione.md); in questa lezione il risultato è usato soltanto con l'ipotesi di continuità dichiarata.

---

### **3. Esempio di localizzazione**

Consideriamo:

$$
f(x)=x^2-2
$$

Calcoliamo:

$$
f(1)=-1
$$

e:

$$
f(2)=2
$$

Poiché i segni sono opposti, esiste uno zero in:

$$
(1,2)
$$

Infatti lo zero positivo è:

$$
\sqrt{2}
$$

---

### **4. Metodo di bisezione**

Il metodo di bisezione restringe progressivamente un intervallo che contiene uno zero.

Si parte da una funzione continua su:

$$
[a,b]
$$

con:

$$
f(a)f(b)<0
$$

La continuità e il cambio di segno garantiscono che almeno uno zero resti nell'intervallo selezionato a ogni passo. Senza continuità la procedura può inseguire un salto e non uno zero: per esempio,

$$
f(x)=
\begin{cases}
-1 & x<0,\\
1 & x\ge0
\end{cases}
$$

cambia segno tra $-1$ e $1$ ma non si annulla.

Si calcola il punto medio:

$$
m=\frac{a+b}{2}
$$

Poi si guarda il segno di $f(m)$:

- se $f(m)=0$, abbiamo trovato lo zero;
- se $f(a)f(m)<0$, lo zero sta in $[a,m]$;
- altrimenti sta in $[m,b]$.

---

### **5. Errore di approssimazione**

Dopo $k$ passi di bisezione, la lunghezza dell’intervallo è:

$$
\frac{b-a}{2^k}
$$

Quindi l’errore è controllabile: più passi facciamo, più l’intervallo si restringe.

Se $\alpha$ è uno zero contenuto nell'intervallo finale $[a_k,b_k]$ e si usa il punto medio

$$
m_k=\frac{a_k+b_k}{2}
$$

come approssimazione, allora

$$
|m_k-\alpha|\le\frac{b_k-a_k}{2}
=\frac{b-a}{2^{k+1}}.
$$

La lunghezza dell'intervallo è dunque $(b-a)/2^k$, mentre l'errore massimo del punto medio è la metà. Per ottenere errore non superiore a $\varepsilon$ basta scegliere $k$ con

$$
\frac{b-a}{2^{k+1}}\le\varepsilon.
$$

#### **5.1 Esempio numerico**

Per $f(x)=x^2-2$ su $[1,2]$:

| Passo | Intervallo conservato | Punto medio | Segno nel punto medio |
|---:|---|---:|---:|
| 1 | $[1,1.5]$ | $1.5$ | positivo |
| 2 | $[1.25,1.5]$ | $1.25$ | negativo |
| 3 | $[1.375,1.5]$ | $1.375$ | negativo |
| 4 | $[1.375,1.4375]$ | $1.4375$ | positivo |

Dopo quattro passi la lunghezza è $1/16$ e il punto medio del nuovo intervallo approssima $\sqrt2$ con errore al più $1/32$.

Questo rende il metodo semplice ma affidabile.

---

### **6. Metodo grafico**

Un altro approccio è interpretare:

$$
f(x)=0
$$

come intersezione del grafico di $f$ con l’asse $x$.

Oppure, se l’equazione è:

$$
g(x)=h(x)
$$

si cercano le intersezioni tra i grafici di $g$ e $h$.

<!-- TODO FIGURA:
Grafico continuo che attraversa l'asse x tra a e b, con quattro intervalli annidati prodotti dalla bisezione e punti medi etichettati. Accanto, grafico a salto con cambio di segno ma nessuno zero.
Obiettivo didattico: mostrare il ruolo indispensabile della continuità e il dimezzamento controllato dell'intervallo.
-->

Il metodo grafico aiuta a capire quante soluzioni aspettarsi, ma non sostituisce una verifica numerica o teorica.

---

### **7. Approssimazioni e arrotondamenti**

Se uno zero è:

$$
\alpha\approx 1.4142
$$

significa che il valore numerico indicato è vicino allo zero, non necessariamente uguale.

Bisogna distinguere:

- valore esatto: $\sqrt{2}$;
- valore approssimato: $1.4142$;
- intervallo di confidenza: ad esempio $1.414<\alpha<1.415$.

È preferibile parlare di **intervallo certificato** anziché di intervallo di confidenza: qui non interviene un modello probabilistico. Se $\alpha\in[1.414,1.415]$, ogni approssimazione scelta nell'intervallo ha un errore assoluto controllabile.

### **8. Esistenza, unicità e algoritmo**

Il cambio di segno di una funzione continua garantisce almeno uno zero, non l'unicità. Per esempio, $f(x)=x(x-1)(x+1)$ cambia segno su $[-2,2]$ e ha tre zeri.

L'unicità può essere ottenuta con un'informazione aggiuntiva, come la stretta monotonia sull'intervallo. Se $f$ è strettamente monotona, non può assumere il valore $0$ in due punti distinti.

La bisezione è un algoritmo robusto ma non rapido come metodi che useranno informazioni differenziali. Questi ultimi appartengono ai moduli successivi.

### **9. Esercizi**

1. Esegui cinque bisezioni per $x^3-x-1=0$ partendo da $[1,2]$.
2. Determina il minimo numero di passi per ottenere errore al più $10^{-4}$ da un intervallo iniziale lungo $3$.
3. Costruisci una funzione non continua con valori di segno opposto agli estremi ma nessuno zero.
4. Fornisci una funzione continua con cambio di segno e almeno tre zeri nell'intervallo iniziale.
5. Spiega quale ipotesi assicura unicità per $x^3+x-1=0$ senza usare derivate.
6. Distingui errore assoluto, ampiezza dell'intervallo e cifre decimali stampate.

---

### **10. Riepilogo**

> ✅ Quando uno zero non si calcola esattamente, si localizza e si approssima.

Punti essenziali:

- un cambio di segno per una funzione continua segnala uno zero;
- la bisezione restringe un intervallo contenente lo zero;
- dopo $k$ passi la lunghezza è $(b-a)/2^k$ e l'errore del punto medio è al più $(b-a)/2^{k+1}$;
- il metodo grafico è utile per orientarsi;
- approssimato non significa esatto.
