# **M9 UD2 Lezione 6 - Derivata della funzione inversa**

### **1. Obiettivo della lezione**

Ultima regola strutturale: come si deriva $f^{-1}$ conoscendo $f'$. Il teorema ha ipotesi delicate (l'inversa deve esistere ed essere continua, e serve $f'\neq 0$) e una dimostrazione non circolare; da esso discenderanno le derivate di tutte le funzioni inverse del catalogo (UD3/L3).

---
### **2. Enunciato**

**Teorema (derivata dell'inversa).** Sia $f:I\to J$ continua e strettamente monotona sull'intervallo $I$ (quindi biiettiva su $J=f(I)$, con inversa continua: M08/UD5/L5). Sia $x_0$ interno a $I$, $y_0=f(x_0)$. Se $f$ è derivabile in $x_0$ con

$$
f'(x_0)\neq 0
$$

allora $f^{-1}$ è derivabile in $y_0$ e

$$
(f^{-1})'(y_0) = \frac{1}{f'(x_0)} = \frac{1}{f'\big(f^{-1}(y_0)\big)}
$$

Lettura geometrica: il grafico di $f^{-1}$ è il simmetrico di quello di $f$ rispetto alla bisettrice $y=x$ (M07); la simmetria scambia "orizzontale" e "verticale", quindi le pendenze delle tangenti nei punti corrispondenti sono **reciproche**. In notazione di Leibniz: $\frac{dx}{dy} = 1\big/\frac{dy}{dx}$ — formula mnemonica resa legittima proprio da questo teorema.

<!-- TODO FIGURA:
Grafici di f (es. e^x) e della sua inversa (ln y) simmetrici rispetto alla bisettrice y=x tratteggiata; punti corrispondenti P=(x_0, y_0) su f e P'=(y_0, x_0) sull'inversa con le rispettive tangenti, di pendenze m e 1/m; angolo tra tangente e bisettrice uguale nei due punti.
Obiettivo didattico: visualizzare la simmetria rispetto a y=x e la reciprocita' delle pendenze delle tangenti nei punti corrispondenti.
-->

---
### **3. Dimostrazione (senza circolarità)**

*Dimostrazione con il rapporto incrementale.* Dobbiamo calcolare

$$
\lim_{y\to y_0}\frac{f^{-1}(y)-f^{-1}(y_0)}{y-y_0}
$$

Poniamo $x=f^{-1}(y)$ (quindi $y=f(x)$, $x_0=f^{-1}(y_0)$). Il rapporto diventa

$$
\frac{x-x_0}{f(x)-f(x_0)} = \frac{1}{\dfrac{f(x)-f(x_0)}{x-x_0}}
$$

dove la divisione è lecita: $f$ è iniettiva, quindi $y\neq y_0 \iff x\neq x_0$ e il denominatore $f(x)-f(x_0)$ non si annulla mai per $y\neq y_0$.

Resta da passare al limite. La sostituzione $x=f^{-1}(y)$ è legittima per il teorema di composizione dei limiti (M08, UD1/L3F, condizione (a)): $f^{-1}$ è **continua** in $y_0$ (qui si usa il teorema dell'inversa continua di M08 — è l'ingrediente che evita ogni circolo, perché è stato dimostrato senza derivate), quindi $x=f^{-1}(y)\to x_0$ per $y\to y_0$, e $x\neq x_0$ per $y\neq y_0$. Allora il rapporto incrementale interno tende a $f'(x_0)$, e per il quoziente (con denominatore di limite $f'(x_0)\neq 0$, M08):

$$
(f^{-1})'(y_0) = \frac{1}{f'(x_0)} \qquad\square
$$

> 📌 Perché **non** si può dimostrare "con la catena": derivando $f(f^{-1}(y))=y$ si ottiene $f'(f^{-1}(y))\cdot(f^{-1})'(y)=1$, da cui la formula. Ma questo calcolo **presuppone che $f^{-1}$ sia derivabile** — che è proprio la tesi. La catena fornisce solo la *forma* della derivata una volta nota l'esistenza; l'esistenza va guadagnata col rapporto incrementale come sopra. Distinzione classica da orale.

---
### **4. L'ipotesi $f'(x_0)\neq 0$ è essenziale**

**Controesempio.** $f(x)=x^3$ è continua, strettamente crescente e derivabile su $\mathbb{R}$, con inversa $f^{-1}(y)=\sqrt[3]{y}$ (continua). Ma $f'(0)=0$, e infatti l'inversa **non è derivabile** in $y_0=f(0)=0$: il suo rapporto incrementale $\frac{\sqrt[3]{y}}{y}=y^{-2/3}\to+\infty$ (tangente verticale, UD1/L2). La simmetria rispetto a $y=x$ trasforma la tangente **orizzontale** di $x^3$ in $0$ nella tangente **verticale** di $\sqrt[3]{y}$: dove $f'$ si annulla, l'inversa esiste ma perde la derivabilità. In generale: inversa continua sempre (sotto le ipotesi di monotonia), inversa derivabile **solo** dove $f'\neq 0$.

---
### **5. Primi frutti**

**Esempio 1 ($\ln$ dall'esponenziale).** $f(x)=e^x$, $f'(x)=e^x\neq 0$ ovunque, inversa $\ln y$ su $(0,+\infty)$:

$$
(\ln y)' = \frac{1}{f'(\ln y)} = \frac{1}{e^{\ln y}} = \frac{1}{y}
$$

coerente con la derivazione diretta di UD2/L2 — due strade indipendenti, stesso risultato: buon segno.

**Esempio 2 (radice $n$-esima).** $f(x)=x^n$ su $(0,+\infty)$, $f'(x)=nx^{\,n-1}\neq 0$:

$$
\left(y^{1/n}\right)' = \frac{1}{n\,(y^{1/n})^{\,n-1}} = \frac{1}{n}\,y^{\frac{1}{n}-1}
$$

la formula delle potenze per esponente $\frac 1n$, stavolta **dimostrata** e non annunciata.

**Esempio 3 (tangente in un punto).** $f(x)=x^5+2x+1$ è strettamente crescente (somma di crescenti); $f(1)=4$. Senza saper scrivere $f^{-1}$:

$$
(f^{-1})'(4) = \frac{1}{f'(1)} = \frac{1}{5+2} = \frac{1}{7}
$$

Il teorema permette di derivare inverse **non esprimibili elementarmente**: è il suo uso d'esame più frequente.

Il raccolto sistematico (arcoseno, arcocoseno, arcotangente, inverse iperboliche) è nella UD3, Lezione 3.

---
### **6. Errori frequenti**

- ❌ Applicare la formula dove $f'(x_0)=0$ (controesempio $x^3$): lì l'inversa non è derivabile.
- ❌ Valutare $f'$ nel punto sbagliato: il denominatore è $f'(f^{-1}(y_0))$ — la derivata di $f$ nel punto **del dominio di $f$** — non $f'(y_0)$.
- ❌ "Dimostrare" il teorema con la catena (circolo vizioso, punto 3).
- ❌ Dimenticare le ipotesi di esistenza dell'inversa (stretta monotonia su un intervallo): senza, la scrittura $f^{-1}$ non designa una funzione.
- ❌ Confondere $(f^{-1})'$ con $(f')^{-1}$ o con $\frac{1}{f'}$ come funzioni: $\left(f^{-1}\right)'(y)=\frac{1}{f'(f^{-1}(y))}$, la composizione è parte della formula.

---
### **7. Esercizi**

**Esercizio 1.** $f(x)=x^3+x$. Calcola $(f^{-1})'(2)$ e $(f^{-1})'(10)$.

*Soluzione.* $f$ strettamente crescente; $f(1)=2$, $f'(x)=3x^2+1$: $(f^{-1})'(2)=\frac{1}{4}$. $f(2)=10$: $(f^{-1})'(10)=\frac{1}{13}$.

**Esercizio 2.** Scrivi l'equazione della tangente al grafico di $f^{-1}$ nel punto di ascissa $y_0=2$, con $f$ dell'esercizio 1.

*Soluzione.* Punto $(2,1)$, pendenza $\frac 14$: $y=1+\frac{1}{4}(y_{\text{ascissa}}-2)$, cioè, chiamando $t$ la variabile: $y=1+\frac{t-2}{4}$.

**Esercizio 3.** Per quali $y_0$ l'inversa di $f(x)=x+\sin x$ (crescente su $\mathbb{R}$, perché $f'\ge 0$ con zeri isolati) non è derivabile?

*Soluzione.* $f'(x)=1+\cos x$ si annulla nei punti $x_k=\pi+2k\pi$: nei corrispondenti $y_k=f(x_k)=\pi+2k\pi$ l'inversa ha tangente verticale e non è derivabile; altrove $(f^{-1})'(y)=\frac{1}{1+\cos(f^{-1}(y))}$.

---
### **8. Riepilogo**

> ✅ Sotto le ipotesi "inversa esistente e continua" (M08/UD5) e $f'(x_0)\neq 0$: $(f^{-1})'(y_0)=\frac{1}{f'(f^{-1}(y_0))}$, dimostrata col rapporto incrementale + sostituzione legittimata dalla continuità dell'inversa — mai con la catena, che presupporrebbe la tesi. Dove $f'=0$ l'inversa perde la derivabilità (tangente verticale: $x^3\mapsto\sqrt[3]{y}$). Usi: derivate delle inverse elementari (UD3/L3) e di inverse non esprimibili.
