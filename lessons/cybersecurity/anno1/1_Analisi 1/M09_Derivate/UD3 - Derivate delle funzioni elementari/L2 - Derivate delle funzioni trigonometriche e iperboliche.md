# **M9 UD3 Lezione 2 - Derivate delle funzioni trigonometriche e iperboliche**

### **1. Obiettivo della lezione**

Completiamo il catalogo trigonometrico (tangente, cotangente, secante, cosecante) e deriviamo l'intera famiglia iperbolica dalle definizioni esponenziali. Tutto discende dalle regole della UD2 applicate a $\sin$, $\cos$, $e^x$: nessuna formula va memorizzata senza saperla ricavare in due righe.

---
### **2. Tangente e cotangente**

Dalla regola del quoziente (UD2/L4), come già visto per la tangente:

$$
(\tan x)' = \frac{1}{\cos^2 x} = 1+\tan^2 x
\qquad \left(x\neq\frac{\pi}{2}+k\pi\right)
$$

(la seconda forma segue dall'identità fondamentale dividendo per $\cos^2 x$; è la più comoda quando la derivata va riscritta in funzione di $\tan$, come nella derivata dell'arcotangente). Analogamente:

$$
(\cot x)' = \left(\frac{\cos x}{\sin x}\right)' = \frac{-\sin x\cdot\sin x - \cos x\cdot\cos x}{\sin^2 x} = -\frac{1}{\sin^2 x} = -\left(1+\cot^2 x\right)
\qquad (x\neq k\pi)
$$

---
### **3. Secante e cosecante**

Con la regola del reciproco (UD2/L4):

$$
(\sec x)' = \left(\frac{1}{\cos x}\right)' = \frac{\sin x}{\cos^2 x} = \sec x\tan x
\qquad \left(x\neq\frac{\pi}{2}+k\pi\right)
$$

$$
(\csc x)' = \left(\frac{1}{\sin x}\right)' = -\frac{\cos x}{\sin^2 x} = -\csc x\cot x
\qquad (x\neq k\pi)
$$

Le forme "$\sec x\tan x$" e "$-\csc x\cot x$" sono riscritture delle frazioni: convenzione anglosassone, utile da riconoscere nei testi internazionali.

---
### **4. Le funzioni iperboliche**

Dalle definizioni esponenziali (M01, richiamate in M08/UD2/L3):

$$
\sinh x = \frac{e^x-e^{-x}}{2}, \qquad \cosh x = \frac{e^x+e^{-x}}{2}
$$

Derivando termine a termine, con $(e^{-x})'=-e^{-x}$ (catena con interna $-x$):

$$
(\sinh x)' = \frac{e^x+e^{-x}}{2} = \cosh x,
\qquad
(\cosh x)' = \frac{e^x-e^{-x}}{2} = \sinh x
$$

Le due funzioni **si scambiano** sotto derivazione — come seno e coseno ma **senza segni meno**: la differenza cruciale tra mondo circolare e mondo iperbolico. In particolare $(\sinh)''=\sinh$ e $(\cosh)''=\cosh$: entrambe risolvono $y''=y$, mentre seno e coseno risolvono $y''=-y$ (anticipazione delle equazioni differenziali, punto 6).

Per la tangente iperbolica, quoziente + identità $\cosh^2 x-\sinh^2 x=1$ (M01):

$$
(\tanh x)' = \frac{\cosh^2 x-\sinh^2 x}{\cosh^2 x} = \frac{1}{\cosh^2 x} = \operatorname{sech}^2 x = 1-\tanh^2 x
$$

su tutto $\mathbb{R}$ ($\cosh x\ge 1$ non si annulla mai). E per la cotangente iperbolica, su $x\neq 0$:

$$
(\coth x)' = -\frac{1}{\sinh^2 x} = -\operatorname{csch}^2 x = 1-\coth^2 x
$$

---
### **5. Specchietto comparativo (con derivazioni alle spalle)**

| Circolari | Iperboliche |
|---|---|
| $(\sin x)'=\cos x$ | $(\sinh x)'=\cosh x$ |
| $(\cos x)'=-\sin x$ | $(\cosh x)'=+\sinh x$ |
| $(\tan x)'=1+\tan^2 x$ | $(\tanh x)'=1-\tanh^2 x$ |
| $\sin^2+\cos^2=1$ | $\cosh^2-\sinh^2=1$ |

Il pattern dei segni ($-$ circolare, $+$ iperbolico) non è un caso: è l'ombra reale di $e^{ix}$ contro $e^{x}$ (M04), e riemergerà identico negli sviluppi di Taylor (M10).

---
### **6. Anticipazione controllata: equazioni differenziali**

Le derivate appena calcolate mostrano che certe funzioni sono caratterizzate da **equazioni sulle loro derivate**:

- $y=Ce^{kx}$ soddisfa $y'=ky$ (crescita/decadimento esponenziale: la funzione varia proporzionalmente a se stessa);
- $y=A\sin(\omega x)+B\cos(\omega x)$ soddisfa $y''+\omega^2 y=0$ (oscillatore armonico);
- $y=A\sinh x+B\cosh x$ soddisfa $y''-y=0$.

Lo studio sistematico delle equazioni differenziali appartiene a corsi successivi: qui registriamo solo che la derivata trasforma proprietà funzionali in equazioni — il motivo per cui comparirà ovunque nella modellistica.

---
### **7. Errori frequenti**

- ❌ Sbagliare il segno di $(\cos x)'$ o, all'opposto, mettere un segno meno a $(\cosh x)'$.
- ❌ Dimenticare i domini: $\tan,\sec$ fuori da $\frac{\pi}{2}+k\pi$; $\cot,\csc$ fuori da $k\pi$; $\coth$ fuori da $0$.
- ❌ Confondere $(\tan x)'=1+\tan^2 x$ con $\tan^2 x$ (il $+1$ è l'identità fondamentale).
- ❌ Scrivere $\operatorname{sech}$, $\operatorname{csch}$ senza `\operatorname` o trattarle come prodotti.

---
### **8. Esercizi**

**Esercizio 1.** Deriva $f(x)=\tan(3x)+\cot(x^2)$ specificando il dominio della derivata.

*Soluzione.* $f'(x)=\frac{3}{\cos^2(3x)}-\frac{2x}{\sin^2(x^2)}$, dove $3x\neq\frac{\pi}{2}+k\pi$ e $x^2\neq k\pi$.

**Esercizio 2.** Verifica che $y=\tanh x$ soddisfa $y'=1-y^2$ e usa l'equazione per calcolare $y''$ senza riderivare da capo.

*Soluzione.* $y'=1-\tanh^2x=1-y^2$ ✓; derivando l'equazione: $y''=-2yy'=-2\tanh x\,(1-\tanh^2 x)$.

**Esercizio 3.** Deriva $f(x)=\operatorname{sech} x = \frac{1}{\cosh x}$.

*Soluzione.* Reciproco: $f'(x)=-\frac{\sinh x}{\cosh^2 x}=-\operatorname{sech} x\tanh x$ (l'analogo iperbolico di $\sec'$, col segno meno).

**Esercizio 4.** Trova i punti in cui la tangente al grafico di $y=\sinh x$ ha pendenza $2$.

*Soluzione.* $\cosh x=2$: $x=\pm\operatorname{arcosh}2=\pm\ln(2+\sqrt3)$ (formula logaritmica, M01).

---
### **9. Riepilogo**

> ✅ Quoziente e reciproco chiudono il catalogo circolare: $\tan'=1+\tan^2=\frac{1}{\cos^2}$, $\cot'=-\frac{1}{\sin^2}$, $\sec'=\sec\tan$, $\csc'=-\csc\cot$, ciascuna col suo dominio. Le iperboliche si derivano dalle definizioni esponenziali: $\sinh\leftrightarrow\cosh$ senza segni meno, $\tanh'=1-\tanh^2=\frac{1}{\cosh^2}$; il confronto dei segni circolare/iperbolico riflette $e^{ix}$ vs $e^x$. Derivate che caratterizzano funzioni: prime equazioni differenziali come anticipazione dichiarata.
