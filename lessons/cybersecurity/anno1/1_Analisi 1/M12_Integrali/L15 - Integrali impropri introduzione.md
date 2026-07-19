# **M11 Lezione 15 - Integrali impropri: introduzione**

### **1. Integrali propri e integrali impropri**

Negli integrali propri si lavora in una situazione controllata:

- la zona di integrazione è limitata;
- la funzione integranda è limitata sull'intervallo.

Per esempio, se $f$ è continua su $[a,b]$, allora

$$
\int_a^b f(x)\,dx
$$

è un integrale proprio.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Quando una di queste due condizioni viene meno, si parla di **integrale improprio** o **integrale generalizzato**. Le due cause principali sono:

- la funzione integranda diventa illimitata vicino a un estremo o in un punto interno;
- l'intervallo di integrazione è illimitato, per esempio $[a,+\infty)$ o $(-\infty,b]$.

> 📌 Un integrale improprio non si calcola sostituendo direttamente gli estremi problematici: si definisce sempre tramite un limite di integrali propri.

### **2. Integranda illimitata a sinistra di un estremo**

Supponiamo che

$$
f:[a,b)\to\mathbb{R}
$$

sia continua su $[a,b)$, ma illimitata a sinistra di $b$. Questo significa, ad esempio, che

$$
\lim_{x\to b^-}f(x)=\pm\infty.
$$

In questo caso non possiamo calcolare direttamente

$$
\int_a^b f(x)\,dx,
$$

perché la funzione non è limitata fino all'estremo $b$. Allora tagliamo l'intervallo prima del punto problematico e definiamo:

$$
\int_a^b f(x)\,dx
=
\lim_{\varepsilon\to 0^+}\int_a^{b-\varepsilon}f(x)\,dx.
$$

Per ogni $\varepsilon>0$, l'integrale

$$
\int_a^{b-\varepsilon}f(x)\,dx
$$

è proprio. L'integrale improprio è il limite di questi integrali propri quando $b-\varepsilon$ tende a $b$ da sinistra.

### **3. Integranda illimitata a destra di un estremo**

Supponiamo invece che

$$
f:(a,b]\to\mathbb{R}
$$

sia continua su $(a,b]$, ma illimitata a destra di $a$, cioè

$$
\lim_{x\to a^+}f(x)=\pm\infty.
$$

In questo caso si definisce:

$$
\int_a^b f(x)\,dx
=
\lim_{\varepsilon\to 0^+}\int_{a+\varepsilon}^{b}f(x)\,dx.
$$

Anche qui la logica è la stessa: si evita il punto in cui la funzione è illimitata, si calcola un integrale proprio e poi si manda il taglio verso il punto problematico.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **4. Convergenza, divergenza e non esistenza**

In entrambi i casi:

- se il limite esiste ed è finito, l'integrale improprio **converge**;
- se il limite è $+\infty$ oppure $-\infty$, l'integrale improprio **diverge**;
- se il limite non esiste, l'integrale improprio **non esiste** oppure è **indeterminato**.

In modo sintetico, dire che

$$
\int_a^b f(x)\,dx
$$

converge significa che la zona può avere un bordo verticale o una funzione illimitata, ma l'area orientata complessiva resta finita.

> ⚠️ Il fatto che $f(x)\to+\infty$ vicino a un estremo non implica automaticamente che l'integrale diverga. Bisogna calcolare il limite che definisce l'integrale improprio.

---
### **5. Esempio 1: integranda illimitata ma integrale convergente**

Calcoliamo

$$
\int_0^4 \frac{1}{2\sqrt{x}}\,dx.
$$

La funzione

$$
f(x)=\frac{1}{2\sqrt{x}}
$$

è illimitata a destra di $0$, perché

$$
\lim_{x\to 0^+}\frac{1}{2\sqrt{x}}=+\infty.
$$

Quindi l'integrale è improprio e va definito come:

$$
\int_0^4 \frac{1}{2\sqrt{x}}\,dx
=
\lim_{\varepsilon\to 0^+}\int_{\varepsilon}^{4}\frac{1}{2\sqrt{x}}\,dx.
$$

Osserviamo che

$$
\frac{1}{2\sqrt{x}}=\frac{1}{2}x^{-1/2}.
$$

Una primitiva è

$$
\sqrt{x},
$$

infatti

$$
\frac{d}{dx}\sqrt{x}=\frac{1}{2\sqrt{x}}.
$$

Quindi:

$$
\lim_{\varepsilon\to 0^+}\int_{\varepsilon}^{4}\frac{1}{2\sqrt{x}}\,dx
=
\lim_{\varepsilon\to 0^+}\left[\sqrt{x}\right]_{\varepsilon}^{4}.
$$

Sostituendo gli estremi:

$$
\lim_{\varepsilon\to 0^+}\left(\sqrt{4}-\sqrt{\varepsilon}\right)
=
\lim_{\varepsilon\to 0^+}\left(2-\sqrt{\varepsilon}\right)
=2.
$$

#### **5.1 Risultato**

L'integrale improprio converge e vale

$$
\int_0^4 \frac{1}{2\sqrt{x}}\,dx=2.
$$

Questo esempio è importante perché mostra che una funzione può diventare infinita in un estremo e avere comunque area finita.

---
### **6. Zona di integrazione illimitata**

Passiamo al secondo tipo di improprietà: l'intervallo non è limitato.

Sia

$$
f:[a,+\infty)\to\mathbb{R}
$$

una funzione continua. In questo caso si definisce:

$$
\int_a^{+\infty}f(x)\,dx
=
\lim_{M\to+\infty}\int_a^M f(x)\,dx.
$$

Analogamente, se

$$
f:(-\infty,b]\to\mathbb{R}
$$

è continua, si definisce:

$$
\int_{-\infty}^{b}f(x)\,dx
=
\lim_{M\to-\infty}\int_M^b f(x)\,dx.
$$

Anche qui l'integrale improprio viene costruito come limite di integrali propri su intervalli finiti.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **7. Esempio 2: integrale su intervallo illimitato**

Calcoliamo

$$
\int_4^{+\infty}\frac{1}{x^2}\,dx.
$$

L'intervallo $[4,+\infty)$ è illimitato, quindi l'integrale è improprio. Per definizione:

$$
\int_4^{+\infty}\frac{1}{x^2}\,dx
=
\lim_{M\to+\infty}\int_4^M\frac{1}{x^2}\,dx.
$$

Scriviamo

$$
\frac{1}{x^2}=x^{-2}.
$$

Una primitiva è

$$
-\frac{1}{x}.
$$

Quindi:

$$
\lim_{M\to+\infty}\int_4^M\frac{1}{x^2}\,dx
=
\lim_{M\to+\infty}\left[-\frac{1}{x}\right]_4^M.
$$

Valutiamo:

$$
\lim_{M\to+\infty}\left(-\frac{1}{M}+\frac{1}{4}\right).
$$

Poiché

$$
\lim_{M\to+\infty}\frac{1}{M}=0,
$$

otteniamo:

$$
\int_4^{+\infty}\frac{1}{x^2}\,dx=\frac{1}{4}.
$$

#### **7.1 Risultato**

L'integrale improprio converge e vale

$$
\int_4^{+\infty}\frac{1}{x^2}\,dx=\frac{1}{4}.
$$

### **8. Riepilogo**

Un integrale improprio compare quando la funzione integranda non è limitata oppure quando l'intervallo di integrazione non è limitato. In entrambi i casi il procedimento è sempre lo stesso:

1. si sostituisce il problema improprio con un integrale proprio;
2. si calcola l'integrale proprio;
3. si passa al limite verso il punto problematico o verso infinito.

Se il limite è finito, l'integrale converge. Se il limite è infinito, diverge. Se il limite non esiste, l'integrale improprio non è determinato.
