# **M10 UD1 Lezione 2 - Esercizi di ripasso su regole, inversa e monotonia**

### **1. Obiettivo della lezione**

Un eserciziario di riscaldamento che ripercorre le tecniche di M09 sugli esempi che serviranno nel resto del modulo. Ogni esercizio dichiara il teorema che lo governa: se la citazione non ti dice nulla, il puntatore in parentesi è il tuo prossimo ripasso.

---
### **2. Composta con dominio da studiare**

Deriva $f(x)=\ln\left(\dfrac{1+x}{1-x}\right)$ specificando il dominio.

*Soluzione.* Dominio: $\frac{1+x}{1-x}>0 \iff -1<x<1$. Con catena e quoziente (M09/UD2):

$$
f'(x)=\frac{1-x}{1+x}\cdot\frac{(1-x)+(1+x)}{(1-x)^2}=\frac{2}{(1-x)(1+x)}=\frac{2}{1-x^2}
$$

Osservazione utile per M12: $f$ è il doppio di $\operatorname{artanh} x$ (M01), e infatti la derivata è $\frac{2}{1-x^2}$, coerente con M09/UD3/L3.

---
### **3. Composizione a tre livelli**

Deriva $g(x)=e^{\sqrt{1+x^2}}$.

*Soluzione.* Catena due volte (esterna $e^u$, media $\sqrt{v}$, interna $1+x^2$):

$$
g'(x)=e^{\sqrt{1+x^2}}\cdot\frac{1}{2\sqrt{1+x^2}}\cdot 2x=\frac{x\,e^{\sqrt{1+x^2}}}{\sqrt{1+x^2}}
$$

Dominio $\mathbb{R}$ ($1+x^2>0$ sempre). Il fattore $\frac{x}{\sqrt{1+x^2}}=\tanh(\operatorname{arsinh}x)$ è limitato in $(-1,1)$: la derivata cresce come l'esponenziale, informazione che tornerà negli studi asintotici.

---
### **4. Inversa senza formula esplicita**

$f(x)=x^3+x$: calcola $(f^{-1})'(2)$ e scrivi la tangente al grafico di $f^{-1}$ in quel punto.

*Soluzione.* $f'(x)=3x^2+1>0$: strettamente crescente, invertibile su $\mathbb{R}$ (M09/UD2/L6). $f(1)=2$, quindi $(f^{-1})'(2)=\frac{1}{f'(1)}=\frac 14$; tangente in $(2,1)$: $y=1+\frac{1}{4}(t-2)$. La derivata dell'inversa non richiede mai di scrivere $f^{-1}$: serve solo il punto corrispondente.

---
### **5. Inversa trigonometrica composta, con i bordi**

Studia dominio e derivabilità di $h(x)=\arcsin(2x-1)$.

*Soluzione.* Dominio: $-1\le 2x-1\le 1 \iff 0\le x\le 1$. Per $x\in(0,1)$, catena con M09/UD3/L3:

$$
h'(x)=\frac{2}{\sqrt{1-(2x-1)^2}}=\frac{2}{2\sqrt{x(1-x)}}=\frac{1}{\sqrt{x(1-x)}}
$$

Ai bordi $x=0,1$ il denominatore si annulla: derivate laterali infinite, **tangenti verticali** — l'eredità del controesempio $f'=0$ della derivata dell'inversa.

---
### **6. Monotonia con funzione razionale**

Studia monotonia ed estremi di $f(x)=\dfrac{x^2+1}{x}$.

*Soluzione.* Dominio $x\neq 0$; $f(x)=x+\frac 1x$, $f'(x)=1-\frac{1}{x^2}=\frac{x^2-1}{x^2}$. Segno: positivo per $|x|>1$, negativo per $0<|x|<1$. Quattro intervalli (il punto $0$ spezza): crescente su $(-\infty,-1)$, massimo locale in $-1$ ($f=-2$), decrescente su $(-1,0)$ e $(0,1)$, minimo locale in $1$ ($f=2$), crescente su $(1,+\infty)$. Nota il paradosso apparente "il massimo locale vale $-2$, meno del minimo locale $2$": nessun assurdo, i due punti vivono in rami separati — e la funzione, riscritta come $x+\frac 1x$, anticipa gli **asintoti obliqui** dello studio di funzione (M11).

---
### **7. Radicale con bordo di dominio**

Studia monotonia ed estremi di $g(x)=x\sqrt{x+1}$.

*Soluzione.* Dominio $x\ge -1$. Per $x>-1$:

$$
g'(x)=\sqrt{x+1}+\frac{x}{2\sqrt{x+1}}=\frac{3x+2}{2\sqrt{x+1}}
$$

Segno di $3x+2$: minimo locale (e globale) in $x=-\frac 23$ con $g=-\frac{2}{3}\sqrt{\frac 13}=-\frac{2\sqrt3}{9}$; in $x=-1$ (bordo) derivata laterale infinita (tangente verticale) e massimo locale **di bordo** con $g(-1)=0$: i bordi si classificano col confronto dei valori, non col cambio di segno bilaterale (M09/UD5/L1).

---
### **8. Valore assoluto: raccordi multipli**

Classifica i punti critici di $h(x)=|x^2-4|$.

*Soluzione.* Rami: $x^2-4$ per $|x|\ge 2$, $4-x^2$ per $|x|<2$; $h'=2x$ e $-2x$ rispettivamente. In $x=\pm 2$: zeri semplici dell'argomento con derivata non nulla $\Rightarrow$ **punti angolosi** (M09/UD3/L4), laterali $(\mp 4,\pm 4)$, e il segno di $h'$ vi cambia da $-$ a $+$: **minimi** (globali, $h=0$) in punti non derivabili. In $x=0$: stazionario del ramo interno con cambio $+\to-$: **massimo locale**, $h(0)=4$. Il profilo a "W" con due spigoli è l'anteprima perfetta degli studi di funzione con modulo di M11.

---
### **9. Riepilogo**

> ✅ Otto riflessi da tenere caldi per M10-M11: dominio prima della derivata; catena a strati dall'esterno; inversa senza esplicitare ($\frac{1}{f'(x_0)}$ nel punto corrispondente); bordi con derivate laterali infinite; segno di $f'$ su **ciascun** intervallo del dominio; classificazione dei bordi per confronto di valori; moduli spezzati ramo per ramo con angoli nei raccordi; riscritture furbe ($x+\frac 1x$) che anticipano gli asintoti.
