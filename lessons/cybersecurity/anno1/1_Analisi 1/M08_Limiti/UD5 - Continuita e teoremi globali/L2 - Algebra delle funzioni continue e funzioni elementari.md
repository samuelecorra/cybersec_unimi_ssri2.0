# **M8 UD5 Lezione 2 - Algebra delle funzioni continue e continuità delle funzioni elementari**

### **1. Obiettivo della lezione**

"Le funzioni elementari sono continue" è la frase più usata e meno dimostrata di Analisi 1. Qui la smontiamo in un programma preciso: (i) teoremi di stabilità (somma, prodotto, quoziente, modulo, massimo/minimo, **composizione**); (ii) continuità dei mattoni di base, ciascuno con la sua giustificazione; (iii) assemblaggio. Alla fine, la frase sarà un **teorema** con una dimostrazione distribuita ma completa.

---
### **2. Algebra delle funzioni continue**

**Teorema.** Siano $f,g:A\to\mathbb{R}$ continue in $x_0\in A$. Allora sono continue in $x_0$:

1. $f+g$ e $f-g$;
2. $\lambda f$ per ogni $\lambda\in\mathbb{R}$, e $f\cdot g$;
3. $\dfrac{f}{g}$, se $g(x_0)\neq 0$ (definita in un intorno di $x_0$ intersecato con $A$);
4. $|f|$;
5. $\max\{f,g\}$ e $\min\{f,g\}$.

*Dimostrazione.* I punti 1-2-3-4, nei punti di accumulazione, sono l'algebra dei limiti (UD1, Lezione 3F) applicata con $L=f(x_0)$ e $M=g(x_0)$: ad esempio $\lim (f+g)=L+M=(f+g)(x_0)$. Per il quoziente, la separazione da zero è garantita da $g\to g(x_0)\neq 0$ (lemma della Lezione 3F): $g$ non si annulla in un intorno e il limite del quoziente è $\frac{f(x_0)}{g(x_0)}$. Nei punti isolati non c'è nulla da dimostrare. Il punto 5 segue dai precedenti tramite le identità

$$
\max\{f,g\} = \frac{f+g+|f-g|}{2},
\qquad
\min\{f,g\} = \frac{f+g-|f-g|}{2}
$$

che riducono massimo e minimo a somma, differenza e modulo. $\square$

---
### **3. Composizione di funzioni continue**

**Teorema.** Se $g:A\to B$ è continua in $x_0\in A$ e $f:B\to\mathbb{R}$ è continua in $y_0=g(x_0)\in B$, allora $f\circ g$ è continua in $x_0$.

*Dimostrazione* (diretta, senza passare per il teorema di composizione dei limiti)*.* Fissato $\varepsilon>0$, la continuità di $f$ in $y_0$ dà $\eta>0$ tale che

$$
y\in B,\ |y-y_0|<\eta \;\Rightarrow\; |f(y)-f(y_0)|<\varepsilon
$$

La continuità di $g$ in $x_0$, usata con precisione $\eta$, dà $\delta>0$ tale che $x\in A,\ |x-x_0|<\delta \Rightarrow |g(x)-y_0|<\eta$. Componendo: $|x-x_0|<\delta \Rightarrow |f(g(x))-f(g(x_0))|<\varepsilon$. $\square$

> 📌 Nota la pulizia: nella versione "continua $\circ$ continua" **non serve nessuna condizione extra** — né $g\neq y_0$, né altro. Le patologie della composizione dei limiti (UD1, Lezione 3F, controesempio 6.1) nascevano dal confronto con il **limite** di $f$ anziché col suo **valore**; qui i valori sono quelli giusti per costruzione. Non c'è circolarità: questa prova usa solo le due definizioni di continuità.

---
### **4. I mattoni: continuità delle funzioni di base**

#### **4.1 Polinomi e funzioni razionali**

Costanti e identità sono continue ($\varepsilon$–$\delta$ in UD1/L3B). Un **polinomio** è somma di prodotti di costanti e identità: continuo su $\mathbb{R}$ per i punti 1-2 dell'algebra (induzione sul grado). Una **funzione razionale** $\frac{P}{Q}$ è continua in ogni punto del suo dominio $\{Q\neq 0\}$ per il punto 3.

#### **4.2 Radici**

$\sqrt[n]{x}$ è continua sul suo dominio ($[0,+\infty)$ per $n$ pari, $\mathbb{R}$ per $n$ dispari): la stima con la fattorizzazione di $a^n-b^n$ è nella Lezione 3F della UD1 (punto 2.3); in $0$ si usa direttamente $\delta=\varepsilon^n$.

#### **4.3 Seno e coseno**

Dalle formule di prostaferesi (M01) e dalla disuguaglianza $|\sin t|\le|t|$ (dimostrata geometricamente in UD3/L1):

$$
|\sin x-\sin x_0| = 2\left|\cos\frac{x+x_0}{2}\right|\left|\sin\frac{x-x_0}{2}\right| \le 2\cdot 1\cdot\frac{|x-x_0|}{2} = |x-x_0|
$$

quindi $\delta=\varepsilon$ funziona in ogni punto: $\sin$ è continua su $\mathbb{R}$ (anzi, Lipschitz: Lezione 6). Identica stima per il coseno ($|\cos x-\cos x_0|\le|x-x_0|$), oppure $\cos x=\sin\left(\frac{\pi}{2}-x\right)$ e composizione. Di conseguenza $\tan=\frac{\sin}{\cos}$ è continua sul suo dominio (quoziente).

#### **4.4 Esponenziale**

Per $e^x$: la continuità in $0$ equivale a $e^h\to 1$ per $h\to 0$, che segue dal limite notevole $\frac{e^h-1}{h}\to 1$ (UD3/L1, dimostrato via $e$ di M05 senza usare la continuità: nessun circolo): infatti $e^h-1 = h\cdot\frac{e^h-1}{h}\to 0\cdot 1=0$. In un punto generico:

$$
|e^x-e^{x_0}| = e^{x_0}\,|e^{x-x_0}-1| \xrightarrow[x\to x_0]{} e^{x_0}\cdot 0 = 0
$$

(composizione con $h=x-x_0\to 0$, lecita perché la funzione esterna $h\mapsto e^h-1$ ha limite $0$ **uguale al valore** in $0$). Per $a^x=e^{x\ln a}$ ($a>0$): composizione di continua con affine.

#### **4.5 Logaritmo**

$\ln$ è l'inversa dell'esponenziale, continua e strettamente crescente da $\mathbb{R}$ su $(0,+\infty)$: la continuità dell'inversa di una funzione continua e strettamente monotona su un intervallo è il **teorema della Lezione 5** (punto 4), che dimostreremo senza usare questo paragrafo (nessuna circolarità). In alternativa diretta: la continuità di $\ln$ in $1$ equivale a $\ln(1+t)\to 0$ per $t\to 0$, che segue dal notevole $\frac{\ln(1+t)}{t}\to 1$; in un punto generico $\ln x-\ln x_0=\ln\left(1+\frac{x-x_0}{x_0}\right)\to 0$. Poi $\log_a x=\frac{\ln x}{\ln a}$.

#### **4.6 Potenze reali, iperboliche, inverse trigonometriche**

- $x^\alpha = e^{\alpha\ln x}$ su $(0,+\infty)$: composizione di continue;
- $\sinh,\cosh,\tanh$: combinazioni algebriche di $e^x$ ed $e^{-x}$;
- $\arcsin,\arccos,\arctan$: inverse di restrizioni continue e strettamente monotone di $\sin,\cos,\tan$ su intervalli — continue per il teorema dell'inversa (Lezione 5), sui rispettivi domini $[-1,1]$, $[-1,1]$, $\mathbb{R}$;
- analogamente le inverse iperboliche ($\operatorname{arcsinh}$ su $\mathbb{R}$, ecc.), che M01 esprime anche con formule logaritmiche: due strade indipendenti verso la stessa continuità.

#### **4.7 Il teorema riassuntivo**

**Teorema (continuità delle funzioni elementari).** Ogni funzione ottenuta da costanti, identità, radici, esponenziali, logaritmi, funzioni trigonometriche e iperboliche con le loro inverse, tramite un numero finito di operazioni algebriche (somma, prodotto, quoziente) e composizioni, è continua in ogni punto del suo dominio naturale.

*Dimostrazione*: induzione sulla struttura dell'espressione, con base i punti 4.1-4.6 e passo induttivo i teoremi dei punti 2-3. $\square$

È questo teorema che giustifica, una volta per tutte, il "metodo della sostituzione" della UD1/L4 e l'uso della condizione (b) nel teorema di composizione dei limiti (UD1/L3F).

---
### **5. Dove la continuità NON arriva da sola**

L'algebra garantisce la continuità **sul dominio**, non l'esistenza di limiti fuori dal dominio né il comportamento agli estremi: $\frac{1}{x}$ è continua ma non ammette limite bilaterale in $0$; $e^{1/x}$ è continua su $\mathbb{R}\setminus\{0\}$ con laterali $0^+$ e $+\infty$ in $0$. Inoltre le funzioni **definite a tratti** non sono coperte dal teorema: nei punti di raccordo la continuità va verificata a mano con i laterali (UD2/L6, e Lezione 1, esercizio 1).

---
### **6. Errori frequenti**

- ❌ Citare "le elementari sono continue" senza saper indicare la catena (mattoni + algebra + composizione + inversa): all'orale è la domanda successiva garantita.
- ❌ Usare la continuità di $\ln$ per dimostrare il notevole $\frac{\ln(1+t)}{t}\to 1$ **e** il notevole per dimostrare la continuità, nello stesso discorso: scegliere una strada e dichiararla (qui: il notevole discende dal limite di $e$ di M05, e la continuità ne segue).
- ❌ Applicare il teorema del quoziente dove $g(x_0)=0$.
- ❌ Estendere l'algebra a composizioni infinite o serie: il teorema copre **un numero finito** di operazioni.
- ❌ Dedurre dalla continuità di $f$ su un dominio non chiuso proprietà globali (limitatezza, massimi): servono i teoremi delle Lezioni 4-5 con le loro ipotesi.

---
### **7. Esercizi**

**Esercizio 1.** Dimostra che $f(x)=\dfrac{e^{\sin x}+x^2}{1+\cos^2 x}$ è continua su $\mathbb{R}$, citando per ogni passaggio il teorema usato.

*Soluzione.* $\sin$ continua (4.3); $e^{\sin x}$ composizione (3); $x^2$ polinomio (4.1); numeratore somma (2.1). $\cos$ continua, $\cos^2$ prodotto, $1+\cos^2 x$ somma, mai nulla ($\ge 1$); quoziente (2.3).

**Esercizio 2.** La funzione $f(x)=x^{\,x}=e^{\,x\ln x}$ è continua su $(0,+\infty)$? Ammette limite per $x\to 0^+$?

*Soluzione.* Continua su $(0,+\infty)$ (composizioni e prodotti di continue). Per $x\to 0^+$: $x\ln x\to 0$ (UD1/L3F, esercizio 5), quindi $f\to e^0=1$: il limite esiste ma $0$ è fuori dal dominio (estensione continua: Lezione 3).

**Esercizio 3.** Trova il dominio di continuità di $f(x)=\ln\left(\frac{x-1}{x+2}\right)$.

*Soluzione.* Serve $\frac{x-1}{x+2}>0$: $x<-2$ o $x>1$. Su questo insieme $f$ è composizione di razionale continua e $\ln$ continua: continua ovunque sia definita.

**Esercizio 4.** Siano $f,g$ continue in $x_0$ con $f(x_0)=g(x_0)$. Dimostra che $h(x)=\begin{cases}f(x), & x\le x_0\\ g(x), & x>x_0\end{cases}$ è continua in $x_0$.

*Soluzione.* Laterale sinistro: $\lim_{x\to x_0^-}h=\lim_{x\to x_0^-}f=f(x_0)$ (continuità di $f$); destro: $=g(x_0)=f(x_0)=h(x_0)$. I laterali coincidono col valore: continua.

---
### **8. Riepilogo**

> ✅ Somma, prodotto, quoziente (denominatore non nullo nel punto), modulo, max/min di continue sono continue (via algebra dei limiti + identità per max/min); la composizione di continue è continua **senza ipotesi aggiuntive**. Mattoni dimostrati: polinomi e razionali, radici, $\sin$ e $\cos$ (prostaferesi + $|\sin t|\le|t|$), esponenziali (dal notevole, senza circoli), logaritmi (inversa monotona o notevole), potenze reali, iperboliche, inverse trigonometriche (teorema dell'inversa, Lezione 5). Assemblaggio per induzione strutturale: ogni funzione elementare è continua sul dominio naturale. Fuori dal dominio e nei raccordi a tratti, la continuità va guadagnata a mano.
