# **M9 UD3 Lezione 3 - Derivate delle inverse trigonometriche e iperboliche**

### **1. Obiettivo della lezione**

Applichiamo sistematicamente il teorema della derivata dell'inversa (UD2/L6) alle restrizioni principali delle funzioni trigonometriche e iperboliche. Ogni formula viene **ricavata**, con il suo dominio di validità e il comportamento agli estremi.

---
### **2. Arcoseno**

$\arcsin:[-1,1]\to\left[-\frac{\pi}{2},\frac{\pi}{2}\right]$ è l'inversa della restrizione principale di $\sin$ (M01/M07). Per $y_0\in(-1,1)$, il punto corrispondente $x_0=\arcsin y_0$ è interno e $\sin'(x_0)=\cos x_0\neq 0$; il teorema dell'inversa dà

$$
(\arcsin y)' = \frac{1}{\cos(\arcsin y)}
$$

Resta da esprimere $\cos(\arcsin y)$: da $\sin^2+\cos^2=1$, $\cos x_0=\pm\sqrt{1-y^2}$, e sull'intervallo $\left[-\frac{\pi}{2},\frac{\pi}{2}\right]$ il coseno è $\ge 0$: si sceglie il segno $+$. Quindi

$$
(\arcsin y)' = \frac{1}{\sqrt{1-y^2}} \qquad (|y|<1)
$$

**Estremi.** In $y=\pm 1$ il denominatore si annulla: la derivata non esiste e il grafico ha **tangenti verticali** ai bordi — l'immagine speculare delle tangenti orizzontali di $\sin$ in $\pm\frac{\pi}{2}$, in perfetto accordo col controesempio $f'=0$ della UD2/L6.

---
### **3. Arcocoseno e arcotangente**

Con lo stesso schema, $\arccos:[-1,1]\to[0,\pi]$ (dove $\sin x_0\ge 0$):

$$
(\arccos y)' = \frac{1}{-\sin(\arccos y)} = -\frac{1}{\sqrt{1-y^2}} \qquad (|y|<1)
$$

Nota il corollario elegante: $(\arcsin y)'+(\arccos y)'=0$ su $(-1,1)$, coerente con l'identità $\arcsin y+\arccos y=\frac{\pi}{2}$ (una funzione con derivata nulla su un intervallo è costante: sarà il primo corollario di Lagrange, UD5/L4 — e questa coppia ne è la verifica perfetta).

Per l'arcotangente, $\arctan:\mathbb{R}\to\left(-\frac{\pi}{2},\frac{\pi}{2}\right)$, con $\tan'(x_0)=1+\tan^2 x_0\neq 0$ sempre:

$$
(\arctan y)' = \frac{1}{1+\tan^2(\arctan y)} = \frac{1}{1+y^2} \qquad (y\in\mathbb{R})
$$

qui la forma "$1+\tan^2$" della Lezione 2 rende il conto immediato, senza radici né scelte di segno. La derivata è sempre positiva e tende a $0$ per $y\to\pm\infty$: il grafico cresce sempre più piatto verso gli asintoti $\pm\frac{\pi}{2}$ (M08/UD2/L5).

**Arcocotangente.** Convenzione adottata: $\operatorname{arccot}:\mathbb{R}\to(0,\pi)$ (l'inversa della restrizione di $\cot$ a $(0,\pi)$; altri testi usano $\left(-\frac\pi2,\frac\pi2\right]\setminus\{0\}$ — convenzione da dichiarare sempre). Con la nostra:

$$
(\operatorname{arccot} y)' = -\frac{1}{1+y^2} \qquad (y\in\mathbb{R})
$$

coerente con $\arctan y+\operatorname{arccot} y=\frac{\pi}{2}$.

<!-- TODO FIGURA:
Grafico di arcsin su [-1,1] con tangenti verticali evidenziate in y=±1 e pendenza minima 1 in 0; accanto, grafico di arctan su un intervallo ampio con gli asintoti orizzontali ±pi/2 tratteggiati e tangenti sempre piu' piatte allontanandosi dall'origine.
Obiettivo didattico: collegare la formula della derivata al comportamento del grafico: denominatore che si annulla = tangente verticale ai bordi (arcsin), derivata infinitesima all'infinito = appiattimento verso gli asintoti (arctan).
-->

---
### **4. Inverse iperboliche**

$\operatorname{arsinh}:\mathbb{R}\to\mathbb{R}$, inversa di $\sinh$ (strettamente crescente, $\sinh'=\cosh\ge 1>0$):

$$
(\operatorname{arsinh} y)' = \frac{1}{\cosh(\operatorname{arsinh} y)} = \frac{1}{\sqrt{1+y^2}} \qquad (y\in\mathbb{R})
$$

(da $\cosh^2-\sinh^2=1$ con $\cosh>0$ sempre: nessuna ambiguità di segno). Per $\operatorname{arcosh}:[1,+\infty)\to[0,+\infty)$, inversa della restrizione di $\cosh$ a $[0,+\infty)$:

$$
(\operatorname{arcosh} y)' = \frac{1}{\sinh(\operatorname{arcosh} y)} = \frac{1}{\sqrt{y^2-1}} \qquad (y>1)
$$

(qui $\sinh x_0\ge 0$ sul ramo scelto; in $y=1$ tangente verticale, immagine del minimo di $\cosh$). E per $\operatorname{artanh}:(-1,1)\to\mathbb{R}$:

$$
(\operatorname{artanh} y)' = \frac{1}{1-\tanh^2(\operatorname{artanh} y)} = \frac{1}{1-y^2} \qquad (|y|<1)
$$

**Verifica incrociata con le formule logaritmiche** (M01): $\operatorname{arsinh} y=\ln\left(y+\sqrt{1+y^2}\right)$; derivandola con catena e quoziente si riottiene $\frac{1}{\sqrt{1+y^2}}$ (esercizio 3) — due strade indipendenti, stesso risultato.

---
### **5. Il quadro completo delle derivate "algebriche"**

Un fatto notevole: le derivate delle inverse trascendenti sono **funzioni algebriche** (razionali o con radici):

$$
\frac{1}{\sqrt{1-y^2}},\quad -\frac{1}{\sqrt{1-y^2}},\quad \frac{1}{1+y^2},\quad \frac{1}{\sqrt{1+y^2}},\quad \frac{1}{\sqrt{y^2-1}},\quad \frac{1}{1-y^2}
$$

È il fenomeno che in M12 renderà queste funzioni protagoniste dell'**integrazione**: primitive di funzioni algebriche semplicissime sono trascendenti. Anticipazione dichiarata, nulla di più.

---
### **6. Errori frequenti**

- ❌ Sbagliare il segno di $(\arccos)'$ o dimenticare quello di $(\operatorname{arccot})'$.
- ❌ Usare le formule in $y=\pm 1$ ($\arcsin$, $\arccos$, $\operatorname{artanh}$) o $y\le 1$ ($\operatorname{arcosh}$): fuori dominio o tangente verticale.
- ❌ Scegliere il segno sbagliato nella radice ($\cos(\arcsin y)=+\sqrt{1-y^2}$ **perché** l'immagine di $\arcsin$ sta dove il coseno è positivo: la scelta va motivata col ramo, non tirata a indovinare).
- ❌ Confondere $\frac{1}{1+y^2}$ ($\arctan$) con $\frac{1}{1-y^2}$ ($\operatorname{artanh}$): il segno racconta se gli asintoti sono orizzontali o verticali.
- ❌ Dimenticare la **derivata interna** nelle composizioni: $\left(\arcsin(3x)\right)'=\frac{3}{\sqrt{1-9x^2}}$.

---
### **7. Esercizi**

**Esercizio 1.** Deriva $f(x)=\arctan\frac{1}{x}$ per $x\neq 0$ e commenta il legame con $\operatorname{arccot}$.

*Soluzione.* Catena: $f'(x)=\frac{1}{1+\frac{1}{x^2}}\cdot\left(-\frac{1}{x^2}\right)=-\frac{1}{1+x^2}$. Stessa derivata di $\operatorname{arccot} x$: infatti su $x>0$ le due funzioni coincidono, su $x<0$ differiscono per la costante $\pi$ (derivata nulla della differenza $\Rightarrow$ costante su ciascun intervallo, ma **non** sulla loro unione: il dominio non è un intervallo — trappola classica, ripresa in UD5/L4).

**Esercizio 2.** Deriva $f(x)=\arcsin\sqrt{x}$ specificando il dominio della derivata.

*Soluzione.* $f'(x)=\frac{1}{\sqrt{1-x}}\cdot\frac{1}{2\sqrt{x}}=\frac{1}{2\sqrt{x(1-x)}}$ per $x\in(0,1)$; in $0$ e $1$ tangenti verticali.

**Esercizio 3.** Rideriva $\operatorname{arsinh} y$ dalla formula logaritmica $\ln\left(y+\sqrt{1+y^2}\right)$.

*Soluzione.* $\frac{1}{y+\sqrt{1+y^2}}\cdot\left(1+\frac{y}{\sqrt{1+y^2}}\right) = \frac{1}{y+\sqrt{1+y^2}}\cdot\frac{\sqrt{1+y^2}+y}{\sqrt{1+y^2}} = \frac{1}{\sqrt{1+y^2}}$ ✓.

**Esercizio 4.** Trova la tangente al grafico di $y=\arctan x$ in $x_0=1$.

*Soluzione.* $y_0=\frac{\pi}{4}$, pendenza $\frac{1}{2}$: $y=\frac{\pi}{4}+\frac{x-1}{2}$.

---
### **8. Riepilogo**

> ✅ Tutte le formule discendono dal teorema dell'inversa + identità fondamentali, con la scelta del segno **motivata dal ramo principale**: $\arcsin'=\frac{1}{\sqrt{1-y^2}}$, $\arccos'=-\frac{1}{\sqrt{1-y^2}}$ (somma costante $\frac\pi2$), $\arctan'=\frac{1}{1+y^2}$, $\operatorname{arccot}'=-\frac{1}{1+y^2}$ (convenzione $(0,\pi)$ dichiarata), $\operatorname{arsinh}'=\frac{1}{\sqrt{1+y^2}}$, $\operatorname{arcosh}'=\frac{1}{\sqrt{y^2-1}}$ ($y>1$), $\operatorname{artanh}'=\frac{1}{1-y^2}$; tangenti verticali dove la funzione diretta aveva derivata nulla. Derivate algebriche di funzioni trascendenti: il ponte verso l'integrazione di M12.
