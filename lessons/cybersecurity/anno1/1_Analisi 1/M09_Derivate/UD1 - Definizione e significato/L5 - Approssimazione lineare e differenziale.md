# **M9 UD1 Lezione 5 - Approssimazione lineare e differenziale**

### **1. Obiettivo della lezione**

Riformuliamo la derivabilità in un modo che sembra solo un cambio di veste ma è in realtà la chiave di volta del calcolo: **derivabile $=$ approssimabile con una retta a meno di un errore trascurabile**. Da qui nascono il differenziale, le stime di errore, la dimostrazione pulita della catena (UD2/L5) e, in prospettiva, la formula di Taylor (M10).

---
### **2. La caratterizzazione con il piccolo-o**

**Teorema.** $f$ è derivabile in $x_0$ con derivata $f'(x_0)=m$ **se e solo se** vale lo sviluppo

$$
f(x_0+h) = f(x_0) + m\,h + o(h) \qquad (h\to 0)
$$

cioè, nella variabile $x$: $f(x)=f(x_0)+m\,(x-x_0)+o(x-x_0)$.

*Dimostrazione.* ($\Rightarrow$) Definiamo il resto $R(h)=f(x_0+h)-f(x_0)-m h$. Allora

$$
\frac{R(h)}{h} = \frac{f(x_0+h)-f(x_0)}{h} - m \xrightarrow[h\to 0]{} m-m = 0
$$

che è esattamente la definizione di $R(h)=o(h)$ (M08, UD4/L3). ($\Leftarrow$) Viceversa, se $f(x_0+h)=f(x_0)+mh+o(h)$, dividendo per $h$: $\frac{f(x_0+h)-f(x_0)}{h} = m + \frac{o(h)}{h}\to m$: la derivata esiste e vale $m$. $\square$

Lettura: tra **tutte** le rette per $(x_0,f(x_0))$, quella di coefficiente $f'(x_0)$ è l'unica il cui scarto dalla funzione è $o(h)$ — un infinitesimo **di ordine superiore** all'incremento. Ogni altra retta $y=f(x_0)+c\,h$ con $c\neq f'(x_0)$ lascia un errore $\sim (f'(x_0)-c)h$, dello stesso ordine di $h$: per questo la tangente è "la migliore approssimazione lineare", in senso preciso e non pubblicitario.

<!-- TODO FIGURA:
Zoom progressivo sul grafico di una funzione vicino a x_0 in tre riquadri: scala normale (curva e tangente distinguibili), zoom 10x (quasi sovrapposte), zoom 100x (indistinguibili); nel primo riquadro evidenziare Delta f (incremento vero, verticale fino alla curva), df = f'(x_0)h (incremento lineare, verticale fino alla tangente) e il resto o(h) come differenza tra i due.
Obiettivo didattico: mostrare che derivabilita' = linearita' locale, e dare il significato geometrico di differenziale e resto.
-->

---
### **3. Il differenziale**

**Definizione.** Se $f$ è derivabile in $x_0$, il **differenziale** di $f$ in $x_0$ è l'applicazione lineare che a ogni incremento $h$ (indicato tradizionalmente $dx$) associa la parte lineare dell'incremento di $f$:

$$
df = f'(x_0)\,dx
$$

Qui $dx$ è un **incremento libero** (una variabile indipendente, non un "numero infinitamente piccolo"), e $df$ è la corrispondente variazione **lungo la tangente**. La distinzione da tenere ferma:

$$
\Delta f = f(x_0+\Delta x)-f(x_0) \qquad\text{(incremento vero, lungo la curva)}
$$

$$
df = f'(x_0)\,\Delta x \qquad\text{(incremento lineare, lungo la tangente)}
$$

e il teorema del punto 2 dice esattamente $\Delta f = df + o(\Delta x)$: **il differenziale non è uguale all'incremento**, lo approssima con errore trascurabile rispetto a $\Delta x$. Con questa definizione la scrittura di Leibniz $\frac{df}{dx}=f'(x_0)$ diventa un vero quoziente di due numeri ($df$ e $dx$), il che spiega perché certe manipolazioni "da frazione" producono formule giuste.

---
### **4. Stime rapide e propagazione degli errori**

L'uso pratico è l'approssimazione $f(x_0+\Delta x)\approx f(x_0)+f'(x_0)\Delta x$ per $\Delta x$ piccolo.

**Esempio 1.** $\sqrt{4{,}1}$: con $f(x)=\sqrt x$, $x_0=4$, $f'(4)=\frac{1}{4}$:

$$
\sqrt{4{,}1} \approx 2 + \frac{1}{4}\cdot 0{,}1 = 2{,}025
$$

(valore vero $2{,}02485\ldots$: errore $\sim 1{,}5\cdot 10^{-4}$, coerente con un $o(h)$ di tipo quadratico).

**Esempio 2 (errore di misura).** Il lato di un quadrato è misurato $l=10$ cm con incertezza $\pm 0{,}1$ cm. L'area $A=l^2$ ha incertezza approssimata

$$
\Delta A \approx A'(l)\,\Delta l = 2l\,\Delta l = 2\cdot 10\cdot 0{,}1 = 2\ \text{cm}^2
$$

su $A=100$ cm²: errore relativo $2\%$, cioè il **doppio** dell'errore relativo sul lato ($1\%$) — coerente con l'elasticità $E=2$ delle potenze (Lezione 3). È lo schema generale della propagazione al primo ordine: $\frac{\Delta f}{f}\approx E(x)\cdot\frac{\Delta x}{x}$.

**Esempio 3.** $\sin(0{,}05)\approx 0{,}05$ (tangente in $0$): è l'equivalenza $\sin x\sim x$ di M08 riletta come approssimazione lineare — i limiti notevoli **sono** derivate in $0$ sotto mentite spoglie: $\lim_{x\to 0}\frac{\sin x}{x}=\sin'(0)$.

> ⚠️ L'approssimazione lineare degrada allontanandosi da $x_0$, e il "quanto" ($o(h)$) qui non è quantificato: le stime **precise** dell'errore richiedono la formula di Taylor con resto di Lagrange, che appartiene a M10 (UD3). In M09 il differenziale dà l'ordine di grandezza, non maggiorazioni certificate.

---
### **5. Anticipazioni dichiarate**

- **Taylor (M10/UD3)**: lo sviluppo $f(x_0+h)=f(x_0)+f'(x_0)h+o(h)$ è il caso $n=1$ della formula di Taylor con resto di Peano; i gradi successivi aggiungeranno curvatura ($h^2$), e il resto di Lagrange quantificherà l'errore;
- **integrazione (M12)**: la notazione $dx$ del differenziale riapparirà negli integrali, dove il legame derivazione$\leftrightarrow$integrazione (teorema fondamentale del calcolo) la renderà operativa. Qui basta sapere che non è una coincidenza tipografica.

---
### **6. Errori frequenti**

- ❌ Scrivere $\Delta f = f'(x_0)\Delta x$ con l'uguale: è un'approssimazione, l'uguaglianza vale solo a meno di $o(\Delta x)$.
- ❌ Trattare $dx$ come "numero infinitamente piccolo": è un incremento reale libero; l'infinitesimo attuale non esiste nell'analisi standard.
- ❌ Usare l'approssimazione lineare lontano da $x_0$ e fidarsi del risultato.
- ❌ Dedurre dal solo sviluppo $f(x_0+h)=a+bh+o(h)$ che $a=f(x_0)$ senza continuità: vero, ma perché lo sviluppo stesso forza $f(x_0+h)\to a$ e il confronto col valore dà $a=f(x_0)$ — da sapere argomentare.

---
### **7. Esercizi**

**Esercizio 1.** Stima $\ln(1{,}02)$ e $e^{0{,}03}$ col differenziale.

*Soluzione.* $\ln(1+x)\approx x$: $\ln(1{,}02)\approx 0{,}02$. $e^x\approx 1+x$: $e^{0{,}03}\approx 1{,}03$.

**Esercizio 2.** Il raggio di una sfera è $r=5{,}00\pm 0{,}05$ m. Stima l'errore assoluto e relativo sul volume $V=\frac{4}{3}\pi r^3$.

*Soluzione.* $\Delta V\approx V'(r)\Delta r = 4\pi r^2\Delta r = 4\pi\cdot 25\cdot 0{,}05=5\pi\approx 15{,}7$ m³; relativo $\frac{\Delta V}{V}\approx 3\frac{\Delta r}{r}=3\%$ (elasticità $3$ del cubo).

**Esercizio 3.** Usando la caratterizzazione col piccolo-o, ridimostra che la derivabilità implica la continuità.

*Soluzione.* $f(x_0+h)=f(x_0)+f'(x_0)h+o(h)\to f(x_0)$ per $h\to 0$ (i due termini variabili sono infinitesimi): è la continuità. Una riga — il vantaggio della formulazione con lo sviluppo.

---
### **8. Riepilogo**

> ✅ Derivabile $\iff$ $f(x_0+h)=f(x_0)+f'(x_0)h+o(h)$: la tangente è l'unica retta con errore di ordine superiore. Differenziale $df=f'(x_0)dx$ come parte lineare: $\Delta f=df+o(\Delta x)$, mai uguaglianza secca. Applicazioni: stime rapide, propagazione degli errori (assoluta e relativa via elasticità), limiti notevoli come derivate in $0$. Le maggiorazioni certificate dell'errore sono rinviate a Taylor (M10); il $dx$ tornerà in M12.
