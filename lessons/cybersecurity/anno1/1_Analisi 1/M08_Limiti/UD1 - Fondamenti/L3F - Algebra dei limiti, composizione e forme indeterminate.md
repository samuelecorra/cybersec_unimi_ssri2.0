# **M8 UD1 Lezione 3F - Algebra dei limiti, composizione e forme indeterminate**

### **1. Obiettivo della lezione**

Tutte le tecniche di calcolo delle UD2-UD3 poggiano su tre pilastri che qui enunciamo e dimostriamo con precisione: l'**algebra dei limiti** (somma, prodotto, quoziente con le loro ipotesi), il **teorema di composizione** (con le condizioni spesso taciute e il controesempio che le rende necessarie), e la mappa delle **forme indeterminate**, cioè dei casi in cui l'algebra si ferma.

In tutta la lezione: $f,g:A\to\mathbb{R}$, $x_0$ punto di accumulazione di $A$ (o $x_0=\pm\infty$ con $A$ illimitato nella direzione giusta: gli enunciati valgono identici).

---
### **2. Algebra dei limiti finiti**

**Teorema.** Se $\displaystyle\lim_{x\to x_0}f(x)=L\in\mathbb{R}$ e $\displaystyle\lim_{x\to x_0}g(x)=M\in\mathbb{R}$, allora:

1. $f(x)+g(x)\to L+M$;
2. $f(x)-g(x)\to L-M$;
3. $\lambda f(x)\to\lambda L$ per ogni $\lambda\in\mathbb{R}$;
4. $f(x)\,g(x)\to LM$;
5. $|f(x)|\to|L|$.

#### **2.1 Dimostrazione della somma**

Fissato $\varepsilon>0$, applichiamo le due ipotesi con $\dfrac{\varepsilon}{2}$: esistono $\delta_1,\delta_2>0$ con

$$
0<|x-x_0|<\delta_1 \Rightarrow |f(x)-L|<\frac{\varepsilon}{2},
\qquad
0<|x-x_0|<\delta_2 \Rightarrow |g(x)-M|<\frac{\varepsilon}{2}
$$

Per $\delta=\min\{\delta_1,\delta_2\}$ e $0<|x-x_0|<\delta$, la disuguaglianza triangolare dà:

$$
|(f(x)+g(x))-(L+M)| \le |f(x)-L|+|g(x)-M| < \frac{\varepsilon}{2}+\frac{\varepsilon}{2} = \varepsilon \qquad\square
$$

La differenza è la somma con $(-1)g$, e il multiplo $\lambda f$ si tratta come la funzione affine della Lezione 3B (se $\lambda\neq 0$, si usa $\frac{\varepsilon}{|\lambda|}$).

#### **2.2 Dimostrazione del prodotto**

Il trucco standard: aggiungere e togliere il termine misto.

$$
|f(x)g(x)-LM| = |f(x)g(x)-f(x)M+f(x)M-LM|
\le |f(x)|\,|g(x)-M| + |M|\,|f(x)-L|
$$

Il fattore $|f(x)|$ dipende da $x$: lo blocchiamo con la **limitatezza locale** (Lezione 3D, punto 4): esistono $\delta_0$ e $C>0$ con $|f(x)|\le C$ nell'intorno puntato di raggio $\delta_0$. Fissato $\varepsilon>0$, scegliamo $\delta_1,\delta_2$ per le due ipotesi con precisioni $\dfrac{\varepsilon}{2C}$ e $\dfrac{\varepsilon}{2(|M|+1)}$ rispettivamente. Per $\delta=\min\{\delta_0,\delta_1,\delta_2\}$:

$$
|f(x)g(x)-LM| < C\cdot\frac{\varepsilon}{2C} + |M|\cdot\frac{\varepsilon}{2(|M|+1)} < \frac{\varepsilon}{2}+\frac{\varepsilon}{2} = \varepsilon \qquad\square
$$

(il $+1$ nel denominatore evita la divisione per zero quando $M=0$).

#### **2.3 Modulo, potenze e radici**

Per il modulo, la disuguaglianza triangolare inversa chiude in una riga: $\big||f(x)|-|L|\big|\le|f(x)-L|$, quindi basta lo stesso $\delta$ di $f$. Attenzione al viceversa: da $|f|\to|L|$ **non** segue $f\to L$ se $L\neq 0$ (esempio: $f(x)=\frac{|x|}{x}$, $|f|\to 1$ ma $f$ senza limite in $0$); l'unico caso in cui il modulo controlla la funzione è $L=0$.

Dal prodotto seguono per induzione le **potenze intere**: $f(x)^n\to L^n$ per $n\in\mathbb{N}$. Per le **radici** vale: se $f\ge 0$ e $f\to L\ (\ge 0)$, allora $\sqrt[n]{f(x)}\to\sqrt[n]{L}$; per $L>0$ si usa la fattorizzazione

$$
\left|\sqrt[n]{f}-\sqrt[n]{L}\right| = \frac{|f-L|}{\sqrt[n]{f^{\,n-1}}+\sqrt[n]{f^{\,n-2}L}+\dots+\sqrt[n]{L^{\,n-1}}} \le \frac{|f-L|}{\sqrt[n]{L^{\,n-1}}}
$$

mentre per $L=0$ si applica direttamente la definizione con $\varepsilon^n$. (Il caso generale delle potenze reali passerà per la continuità di esponenziale e logaritmo: UD5, Lezione 2.)

---
### **3. Il quoziente e la separazione del denominatore da zero**

**Lemma (separazione da zero).** Se $g(x)\to M\neq 0$, allora esiste un intorno puntato in cui

$$
|g(x)| > \frac{|M|}{2}
$$

*Dimostrazione.* Applichiamo la definizione con $\varepsilon=\dfrac{|M|}{2}$: definitivamente $|g(x)-M|<\frac{|M|}{2}$, e per la triangolare inversa $|g(x)|\ge|M|-|g(x)-M|>\frac{|M|}{2}$. $\square$ (È la permanenza del segno della Lezione 3D in versione "modulo"; in particolare $g(x)\neq 0$ definitivamente, quindi $\frac{f}{g}$ è ben definita vicino a $x_0$.)

**Teorema (quoziente).** Se $f\to L$ e $g\to M$ con $M\neq 0$, allora

$$
\frac{f(x)}{g(x)} \to \frac{L}{M}
$$

*Dimostrazione.* Basta il caso $\frac{1}{g}\to\frac{1}{M}$ (poi si moltiplica per $f$ col teorema del prodotto). Nell'intorno del lemma:

$$
\left|\frac{1}{g(x)}-\frac{1}{M}\right| = \frac{|g(x)-M|}{|g(x)|\,|M|} < \frac{2}{M^2}\,|g(x)-M|
$$

Fissato $\varepsilon>0$, scegliendo il raggio di $g$ relativo a $\dfrac{M^2}{2}\varepsilon$ (e intersecando col raggio del lemma) si conclude. $\square$

> ⚠️ Se $M=0$ il teorema **non si applica**, e non esiste una regola unica:
>
> - $L\neq 0$, $M=0$: il quoziente **non ha limite finito**; se $g$ ha segno definitivamente costante il quoziente diverge a $\pm\infty$ (segno da studiare, spesso lato per lato: UD2, Lezione 1, caso 3), se il segno oscilla il limite non esiste nemmeno esteso;
> - $L=0$, $M=0$: forma indeterminata $\frac{0}{0}$, tutto può accadere (punto 5).

---
### **4. Estensione ai limiti estesi**

Quando uno dei limiti è $\pm\infty$ valgono le regole di **aritmetizzazione parziale** già dimostrate per le successioni in M05 e trasferite alle funzioni dal criterio di Heine (Lezione 3E): ad esempio

$$
L+(+\infty)=+\infty,\qquad
L\cdot(+\infty)=\pm\infty \ (L\neq 0,\ \text{segno di } L),\qquad
\frac{L}{\pm\infty}=0,\qquad
\frac{1}{0^{\pm}}=\pm\infty
$$

dove l'ultima scrittura usa la convenzione $0^+$/$0^-$ (Lezione 4): il reciproco di un infinitesimo **di segno definitivamente costante** diverge con quel segno. Restano **escluse** dalle regole esattamente le combinazioni del punto 5.

---
### **5. Le forme indeterminate**

Quando le regole precedenti non coprono la combinazione dei limiti, si parla di **forma indeterminata**:

$$
\frac{0}{0},\qquad \frac{\infty}{\infty},\qquad 0\cdot\infty,\qquad \infty-\infty,\qquad 1^{\infty},\qquad 0^{0},\qquad \infty^{0}
$$

> 📌 "Indeterminata" **non** significa "il limite non esiste" e non è un risultato: significa che **la sola conoscenza dei due limiti non determina l'esito**, e serve un'analisi specifica della coppia di funzioni. Ogni forma ammette esiti finiti arbitrari, infiniti, e casi di non esistenza.

Dimostriamolo esibendo, per ciascuna forma, esempi con esiti diversi (le prime quattro righe per $x\to 0^+$, le forme esponenziali per $x\to+\infty$; ogni esempio è verificabile con le tecniche delle UD2-UD3):

| Forma | Esito finito | Esito infinito | Non esistenza |
|---|---|---|---|
| $\frac{0}{0}$ | $\frac{\sin x}{x}\to 1$ | $\frac{x}{x^3}\to+\infty$ | $\frac{x\sin(1/x)}{x}=\sin\frac 1x$ |
| $\frac{\infty}{\infty}$ | $\frac{2/x+1}{1/x}=2+x\to 2$ | $\frac{1/x^3}{1/x}=\frac{1}{x^2}\to+\infty$ | $\frac{(2+\sin\frac 1x)/x}{1/x}=2+\sin\frac 1x$ |
| $0\cdot\infty$ | $x\cdot\frac{3}{x}\to 3$ | $x\cdot\frac{1}{x^2}\to+\infty$ | $\frac 1x\cdot x\sin\frac 1x=\sin\frac 1x$ |
| $\infty-\infty$ | a $+\infty$: $\sqrt{x^2+x}-x\to\frac{1}{2}$ | $\frac{1}{x^2}-\frac{1}{x}\to+\infty$ | a $+\infty$: $(x+2\sin x)-x=2\sin x$ |
| $1^{\infty}$ | $\left(1+\frac{1}{x}\right)^x\to e$ | $\left(1+\frac{1}{x}\right)^{x^2}\to+\infty$ | $\left(1+\frac{\sin x}{x}\right)^{x}$ |
| $0^{0}$ | in $0^+$: $x^{1/\ln x}=e$ (e $x^{\,x}\to 1$) | in $0^+$: $x^{-1/\sqrt{-\ln x}}=e^{\sqrt{-\ln x}}\to+\infty$ | in $0^+$: $x^{\,\sin(1/x)/(-\ln x)}=e^{-\sin(1/x)}$ |
| $\infty^{0}$ | $x^{1/\ln x}=e$ | $x^{1/\sqrt{\ln x}}=e^{\sqrt{\ln x}}\to+\infty$ | $x^{\,\sin x/\ln x}=e^{\sin x}$ |

Le uguaglianze esatte nelle ultime righe seguono tutte da $x^{g(x)}=e^{\,g(x)\ln x}$: ad esempio $x^{\sin x/\ln x} = e^{\frac{\sin x}{\ln x}\ln x} = e^{\sin x}$, che oscilla tra $e^{-1}$ ed $e$ senza limite, con base $\to+\infty$ ed esponente $\to 0$.

Due esempi della tabella svolti per esteso, per fissare il metodo:

- ($0\cdot\infty$, non esistenza) per $x\to 0^+$: $\ \dfrac{1}{x}\cdot x\sin\dfrac{1}{x} = \sin\dfrac{1}{x}$, che non ha limite (Lezione 3E). Il primo fattore diverge, il secondo è infinitesimo, il prodotto oscilla;
- ($1^\infty$, esito infinito) per $x\to+\infty$: $\ \left(1+\frac{1}{x}\right)^{x^2} = \left[\left(1+\frac{1}{x}\right)^{x}\right]^{x}$; la base tende a $e>2$, quindi definitivamente supera $2$, e $2^x\to+\infty$: per confronto il tutto diverge;
- ($1^\infty$, non esistenza) per $x\to+\infty$: $\left(1+\frac{\sin x}{x}\right)^{x} = e^{\,x\ln\left(1+\frac{\sin x}{x}\right)}$, e poiché $\ln(1+t)\sim t$ per $t\to 0$ l'esponente si comporta come $x\cdot\frac{\sin x}{x}=\sin x$: l'espressione oscilla tra valori vicini a $e^{-1}$ ed $e$ senza limite (la base tende a $1$, l'esponente a $+\infty$).

Le forme **esponenziali** ($1^\infty$, $0^0$, $\infty^0$) si riconducono sempre alle prime tramite

$$
f(x)^{g(x)} = e^{\,g(x)\ln f(x)} \qquad (f>0)
$$

l'esponente diventa rispettivamente $\infty\cdot 0$, $0\cdot(-\infty)$, $0\cdot\infty$: **una sola** famiglia di problemi sotto tre travestimenti.

> ⚠️ Non tutto ciò che sembra strano è indeterminato: $0^{+\infty}=0$, $(+\infty)^{+\infty}=+\infty$, $\frac{0}{\infty}=0$, $\frac{\infty}{0^+}=+\infty$ sono **determinati** dalle regole del punto 4. Prima di dichiarare l'indeterminazione, controlla la tabella.

---
### **6. Il teorema di composizione dei limiti**

Vogliamo calcolare $\displaystyle\lim_{x\to x_0}f\big(g(x)\big)$ sapendo che $g(x)\to y_0$ per $x\to x_0$ e $f(y)\to L$ per $y\to y_0$. **Attenzione**: queste due ipotesi da sole **non bastano**.

#### **6.1 Il controesempio che mostra il problema**

Siano

$$
g(x) = x\sin\frac{1}{x} \quad (x\neq 0), \qquad
f(y)=\begin{cases} 0, & y\neq 0 \\ 1, & y=0 \end{cases}
$$

Allora $g(x)\to 0=y_0$ per $x\to 0$ (carabinieri) e $f(y)\to 0=L$ per $y\to 0$ (il valore $f(0)=1$ non conta per il limite). Ma la composizione **non** tende a $L=0$: nei punti $x_n=\frac{1}{n\pi}$ si ha $g(x_n)=0$, quindi $f(g(x_n))=f(0)=1$, mentre nei punti in cui $g(x)\neq 0$ vale $f(g(x))=0$. Lungo due successioni infinitesime la composizione dà $1$ e $0$: per il criterio sequenziale, $\lim_{x\to 0}f(g(x))$ **non esiste**.

Il guasto è chirurgico: $g$ continua a **toccare il valore limite** $y_0$ in punti arbitrariamente vicini a $x_0$, e proprio in $y_0$ la funzione esterna ha un valore "sbagliato" rispetto al suo limite.

#### **6.2 L'enunciato corretto**

**Teorema (composizione).** Siano $g:A\to B$, $f:B\to\mathbb{R}$, $x_0$ di accumulazione per $A$, $y_0$ di accumulazione per $B$, con

$$
\lim_{x\to x_0}g(x)=y_0, \qquad \lim_{y\to y_0}f(y)=L
$$

Se vale **almeno una** delle condizioni:

**(a)** $g(x)\neq y_0$ definitivamente per $x\to x_0$ (esiste un intorno puntato di $x_0$ in cui $g$ non assume il valore $y_0$), **oppure**

**(b)** $y_0\in B$ e $f$ è **continua** in $y_0$, cioè $f(y_0)=L$,

allora:

$$
\lim_{x\to x_0}f\big(g(x)\big)=L
$$

*Dimostrazione.* Fissato $\varepsilon>0$, l'ipotesi su $f$ dà $\eta>0$ tale che

$$
y\in B,\ 0<|y-y_0|<\eta \;\Rightarrow\; |f(y)-L|<\varepsilon \tag{$\ast$}
$$

L'ipotesi su $g$ (usata con precisione $\eta$) dà $\delta_1>0$ tale che $0<|x-x_0|<\delta_1\Rightarrow|g(x)-y_0|<\eta$.

Caso **(a)**: sia $\delta_2$ il raggio in cui $g\neq y_0$ e $\delta=\min\{\delta_1,\delta_2\}$. Per $0<|x-x_0|<\delta$, il punto $y=g(x)$ soddisfa $0<|y-y_0|<\eta$, e ($\ast$) dà $|f(g(x))-L|<\varepsilon$.

Caso **(b)**: qui ($\ast$) si rafforza: anche per $y=y_0$ si ha $|f(y_0)-L|=0<\varepsilon$, quindi la condizione "$0<$" si può togliere: $|y-y_0|<\eta\Rightarrow|f(y)-L|<\varepsilon$. Allora $\delta=\delta_1$ basta, che $g$ tocchi $y_0$ oppure no. $\square$

Nel controesempio del 6.1 fallivano entrambe: $g$ tocca $0$ infinite volte vicino a $0$, e $f$ non è continua in $0$ ($f(0)=1\neq 0=L$). Nei calcoli pratici la condizione che si invoca quasi sempre è la **(b)**: è ciò che rende lecito il metodo "prima l'interna, poi l'esterna" di UD2/L4 quando l'esterna è continua nel punto di arrivo — e la continuità delle funzioni elementari è il tema della UD5.

#### **6.3 Metodologia della sostituzione**

Il "cambio di variabile" $u=g(x)$ nei limiti (usato sistematicamente in UD3) è esattamente questo teorema. Checklist per usarlo senza incidenti:

1. calcola $y_0=\lim_{x\to x_0}g(x)$;
2. verifica che $y_0$ sia di accumulazione per il dominio della funzione esterna, e che l'esterna sia definita sui valori effettivamente assunti da $g$;
3. controlla la condizione **(a)** ($g\neq y_0$ vicino a $x_0$) **oppure** la **(b)** ($f$ continua in $y_0$);
4. stabilisci **come** $u$ si avvicina a $y_0$ se serve il lato ($u\to y_0^+$? $u\to y_0^-$?): se l'esterna ha laterali diversi, il lato dell'interna decide (qui aiuta la monotonia locale di $g$);
5. solo allora scrivi $\lim_{x\to x_0}f(g(x)) = \lim_{u\to y_0}f(u)$.

Il passo 4 in azione: $\displaystyle\lim_{x\to 0^-} e^{1/x}$ — l'interna $u=\frac 1x\to-\infty$ (non $+\infty$!), quindi il limite è $\lim_{u\to-\infty}e^u=0$; sul lato destro sarebbe $+\infty$. Chi salta il controllo del lato scambia i due esiti.

---
### **7. Errori frequenti**

- ❌ Applicare somma/prodotto/quoziente quando uno dei limiti **non esiste**: i teoremi hanno per ipotesi l'esistenza di entrambi. ($x\sin\frac 1x\to 0$ non si giustifica col prodotto, ma coi carabinieri.)
- ❌ Dividere per $g$ senza la separazione da zero ($M\neq 0$): il lemma del punto 3 è il biglietto d'ingresso del quoziente.
- ❌ Trattare una forma indeterminata come un valore ("$\frac{0}{0}=1$", "$\infty-\infty=0$").
- ❌ Dichiarare indeterminata una forma determinata ($\frac{0}{\infty}$, $0^{+\infty}$…).
- ❌ Sostituire $u=g(x)$ senza controllare né (a) né (b), o senza controllare il **lato** con cui $u$ arriva al suo limite.
- ❌ Dedurre $f\to L$ da $|f|\to|L|$ con $L\neq 0$.

---
### **8. Esercizi**

**Esercizio 1.** Dimostra, usando solo teoremi di questa lezione e i limiti delle funzioni elementari, che $\displaystyle\lim_{x\to 2}\frac{x^2+3x}{x-5}=-\frac{10}{3}$.

*Soluzione.* $x\to 2$ (identità), quindi $x^2\to 4$ (prodotto), $3x\to 6$ (multiplo), numeratore $\to 10$ (somma); denominatore $\to -3\neq 0$ (somma); quoziente $\to -\frac{10}{3}$.

**Esercizio 2.** Il limite $\displaystyle\lim_{x\to 0}\left[\left(\sin\frac{1}{x}\right)^2 + \left(\cos\frac{1}{x}\right)^2\right]$ esiste? L'algebra dei limiti è applicabile?

*Soluzione.* La funzione è identicamente $1$: il limite è $1$. Ma **non** per algebra dei limiti (i due addendi non hanno limite): prima si semplifica con l'identità fondamentale, poi si calcola. Morale: l'algebra è sufficiente, non necessaria.

**Esercizio 3.** Stabilisci se si può applicare il teorema di composizione a $\displaystyle\lim_{x\to 0}\ln\left(\frac{\sin x}{x}\right)$ e calcola.

*Soluzione.* Interna $\to 1$; esterna $\ln$ continua in $1$: condizione (b) soddisfatta. Limite $=\ln 1=0$.

**Esercizio 4.** Costruisci $f,g$ con $g\to 1$ per $x\to 0$ e $f\to 2$ per $y\to 1$, ma $f(g(x))\not\to 2$.

*Soluzione (una possibile).* $g(x)=1$ costante; $f(y)=2$ per $y\neq 1$, $f(1)=7$. Allora $f(g(x))=7$ costante $\to 7\neq 2$. (Qui fallisce (a) — $g$ vale sempre $y_0$ — e fallisce (b) — $f(1)\neq 2$.)

**Esercizio 5.** Classifica le forme e calcola: $\displaystyle\lim_{x\to+\infty}\frac{2^x+x^2}{2^x-x^3}$, $\displaystyle\lim_{x\to 0^+}x\ln x$ (usa la sostituzione $x=e^{-t}$), $\displaystyle\lim_{x\to+\infty}\left(1+\frac{3}{x}\right)^{2x}$.

*Soluzione.* (i) $\frac{\infty}{\infty}$: raccogliendo $2^x$, limite $1$. (ii) $0\cdot(-\infty)$: con $x=e^{-t}$, $t\to+\infty$ (interna monotona, condizione (a) ok), diventa $-t e^{-t}=-\frac{t}{e^t}\to 0$ per la scala di UD2/L3. (iii) $1^\infty$: per UD3/L2, $e^{6}$.

---
### **9. Riepilogo**

> ✅ Algebra dei limiti: somma con $\frac\varepsilon2+\frac\varepsilon2$, prodotto con limitatezza locale e termine misto, quoziente **solo** dopo la separazione dal denominatore nullo ($|g|>\frac{|M|}{2}$); modulo sempre, potenze e radici con le condizioni di dominio. Le sette forme indeterminate sono le combinazioni che l'aritmetizzazione non copre: non sono risultati e ognuna ammette ogni esito; le esponenziali si riconducono a $0\cdot\infty$ via $e^{g\ln f}$. La composizione richiede $g\neq y_0$ definitivamente **oppure** $f$ continua in $y_0$: senza, il controesempio $x\sin\frac 1x$ dentro una $f$ discontinua la smentisce. La sostituzione nei limiti è il teorema di composizione più una checklist su dominio e lati.
