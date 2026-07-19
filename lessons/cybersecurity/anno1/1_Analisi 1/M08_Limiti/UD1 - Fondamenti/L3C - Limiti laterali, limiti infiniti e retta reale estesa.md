# **M8 UD1 Lezione 3C - Limiti laterali, limiti infiniti e retta reale estesa**

### **1. Obiettivo della lezione**

Nelle Lezioni 1-3 abbiamo parlato più volte di "avvicinarsi da destra o da sinistra" e di limiti che "esplodono": qui rendiamo rigorose tutte queste nozioni. Formalizziamo i **limiti laterali**, i **limiti infiniti** in un punto finito, i **limiti all'infinito** (finiti e infiniti), il quadro unificante della **retta reale estesa**, e la distinzione tra divergenza e **oscillazione**.

---
### **2. Limiti laterali: definizioni**

Sia $f:A\to\mathbb{R}$ e sia $x_0$ un punto di **accumulazione da destra** per $A$ (Lezione 3A, punto 5). Si dice che

$$
\lim_{x\to x_0^+}f(x)=L
$$

se:

$$
\forall\varepsilon>0 \;\; \exists\delta>0 \;\; \forall x\in A:\quad 0<x-x_0<\delta \;\Rightarrow\; |f(x)-L|<\varepsilon
$$

Analogamente, se $x_0$ è di accumulazione da sinistra, $\displaystyle\lim_{x\to x_0^-}f(x)=L$ significa:

$$
\forall\varepsilon>0 \;\; \exists\delta>0 \;\; \forall x\in A:\quad 0<x_0-x<\delta \;\Rightarrow\; |f(x)-L|<\varepsilon
$$

La sola differenza rispetto al limite ordinario è la finestra: $0<x-x_0<\delta$ seleziona l'intorno **destro** $(x_0,\ x_0+\delta)$, $0<x_0-x<\delta$ quello **sinistro** $(x_0-\delta,\ x_0)$. Tutto il resto (arbitrarietà di $\varepsilon$, dipendenza $\delta(\varepsilon)$, esclusione di $x_0$) è identico.

> 📌 Notazione: scriviamo $x\to x_0^+$ e $x\to x_0^-$ (con l'apice), non "$x\to x_0+$". Il limite destro si indica anche $f(x_0^+)$ quando esiste finito.

**Bordi del dominio.** Se il dominio è ad esempio $[0,+\infty)$, in $x_0=0$ esiste solo l'accumulazione destra: l'unico limite sensato è quello destro, e per convenzione la scrittura $\lim_{x\to 0}\sqrt{x}$ **coincide** con $\lim_{x\to 0^+}\sqrt{x}$, perché la condizione $0<|x|<\delta$, intersecata col dominio, seleziona già solo i punti a destra.

---
### **3. Teorema: bilaterale $\iff$ i due laterali coincidono**

**Teorema.** Sia $x_0$ punto di accumulazione **da entrambi i lati** per $A$ e sia $L\in\mathbb{R}$. Allora:

$$
\lim_{x\to x_0}f(x)=L
\quad\Longleftrightarrow\quad
\lim_{x\to x_0^-}f(x)=L \ \text{ e } \ \lim_{x\to x_0^+}f(x)=L
$$

*Dimostrazione.*

($\Rightarrow$) Fissato $\varepsilon>0$, il limite bilaterale fornisce $\delta>0$ tale che $0<|x-x_0|<\delta\Rightarrow|f(x)-L|<\varepsilon$. Ma sia $0<x-x_0<\delta$ sia $0<x_0-x<\delta$ implicano $0<|x-x_0|<\delta$: lo **stesso** $\delta$ certifica entrambi i laterali.

($\Leftarrow$) Fissato $\varepsilon>0$, il limite destro fornisce $\delta_+$ e il sinistro $\delta_-$. Poniamo $\delta=\min\{\delta_+,\delta_-\}>0$. Se $0<|x-x_0|<\delta$, allora $x$ sta a destra oppure a sinistra di $x_0$: nel primo caso $0<x-x_0<\delta\le\delta_+$ e la stima destra dà $|f(x)-L|<\varepsilon$; nel secondo caso conclude la stima sinistra. In ogni caso $|f(x)-L|<\varepsilon$. $\square$

Il teorema vale identico con $L=\pm\infty$ (stesse due implicazioni, con le definizioni del punto 4).

**Conseguenza operativa**: per **negare** un limite bilaterale basta esibire laterali diversi. È il modo rapido di rileggere la funzione segno della Lezione 3B: $\lim_{x\to 0^+}\frac{|x|}{x}=1$ e $\lim_{x\to 0^-}\frac{|x|}{x}=-1$, quindi il bilaterale non esiste.

**Esempi.**

- $f(x)=\dfrac{|x|}{x}$: laterali $1$ e $-1$, bilaterale inesistente (salto);
- $f(x)=\sqrt{x}$: esiste solo il lato destro, $\lim_{x\to 0^+}\sqrt{x}=0$, e ciò basta a dire $\lim_{x\to 0}\sqrt{x}=0$ (accumulazione solo destra);
- $f(x)=\begin{cases} x+1, & x\ge 1 \\ 3x-1, & x<1 \end{cases}$: in $x_0=1$ entrambi i laterali valgono $2$, quindi $\lim_{x\to 1}f(x)=2=f(1)$;
- $f(x)=\dfrac{1}{x}$: laterali $+\infty$ e $-\infty$ in $0$, bilaterale inesistente anche in senso esteso.

<!-- TODO FIGURA:
Tre mini-grafici affiancati: (a) funzione a salto con limiti laterali diversi in x_0 (cerchietti vuoti a quote diverse, frecce da destra e da sinistra); (b) sqrt(x) con dominio [0,+inf) e solo la freccia destra verso 0; (c) 1/x con i due rami che divergono a +inf e -inf vicino a x=0 e la retta x=0 tratteggiata.
Obiettivo didattico: distinguere i tre scenari: laterali finiti diversi, limite solo da un lato per motivi di dominio, laterali infiniti di segno opposto.
-->

---
### **4. Limiti infiniti in un punto finito**

Sia $x_0$ di accumulazione per $A$. Si dice che

$$
\lim_{x\to x_0}f(x)=+\infty
$$

se:

$$
\forall M>0 \;\; \exists\delta>0 \;\; \forall x\in A:\quad 0<|x-x_0|<\delta \;\Rightarrow\; f(x)>M
$$

e $\displaystyle\lim_{x\to x_0}f(x)=-\infty$ se, nelle stesse condizioni, $f(x)<-M$.

La soglia verticale si chiama $M$ (coerentemente in tutto il modulo): "per ogni quota $M$, per quanto alta, la funzione la supera stabilmente vicino a $x_0$". Le **versioni laterali** si ottengono sostituendo la finestra bilaterale con $0<x-x_0<\delta$ oppure $0<x_0-x<\delta$; per $\frac{1}{x}$ in $0$: $\lim_{x\to 0^+}\frac 1x=+\infty$, $\lim_{x\to 0^-}\frac 1x=-\infty$.

> ⚠️ Tre avvertenze:
>
> 1. $+\infty$ e $-\infty$ **non sono numeri**: "limite $=+\infty$" è un modo compatto per dire che la definizione qui sopra è soddisfatta; il limite finito **non esiste**;
> 2. il **segno va controllato**: $\frac{1}{x^2}\to+\infty$ ma $\frac{1}{x}$ non ha limite bilaterale in $0$;
> 3. $|f(x)|\to+\infty$ **non** equivale a "$f(x)\to+\infty$ oppure $f(x)\to-\infty$": la funzione $f(x)=\frac{1}{x}$ ha modulo divergente in $0$ ma nessun limite esteso bilaterale, perché i segni si alternano nei due lati.

---
### **5. Limiti finiti all'infinito**

Sia $A$ **illimitato superiormente** (così $+\infty$ è "di accumulazione" per $A$: ogni semiretta $(R,+\infty)$ interseca $A$). Si dice che

$$
\lim_{x\to+\infty}f(x)=L
$$

se:

$$
\forall\varepsilon>0 \;\; \exists R>0 \;\; \forall x\in A:\quad x>R \;\Rightarrow\; |f(x)-L|<\varepsilon
$$

Analogamente per $x\to-\infty$ (dominio illimitato inferiormente, condizione $x<-R$). La soglia orizzontale si chiama $R$ (o $K$): il ruolo che al finito ha $\delta$ qui lo gioca "quanto lontano devo andare".

Esempio già dimostrabile: $\lim_{x\to+\infty}\dfrac{1}{x}=0$, con $R=\dfrac{1}{\varepsilon}$: se $x>R$ allora $\left|\frac 1x - 0\right| = \frac 1x < \varepsilon$.

---
### **6. Limiti infiniti all'infinito**

Combinando le due estensioni:

$$
\lim_{x\to+\infty}f(x)=+\infty
\quad\Longleftrightarrow\quad
\forall M>0 \;\; \exists R>0 \;\; \forall x\in A:\ x>R \Rightarrow f(x)>M
$$

Le quattro combinazioni per $x\to+\infty$ e le quattro per $x\to-\infty$ si costruiscono tutte con lo stesso schema:

| Comportamento | Condizione su $x$ | Conclusione richiesta |
|---|---|---|
| $x\to+\infty$, limite $L$ | $x>R$ | $\vert f(x)-L\vert<\varepsilon$ |
| $x\to+\infty$, limite $+\infty$ | $x>R$ | $f(x)>M$ |
| $x\to+\infty$, limite $-\infty$ | $x>R$ | $f(x)<-M$ |
| $x\to-\infty$, limite $L$ | $x<-R$ | $\vert f(x)-L\vert<\varepsilon$ |
| $x\to-\infty$, limite $\pm\infty$ | $x<-R$ | $f(x)\gtrless\pm M$ |

La tabella non è un formulario da imparare: è **una sola definizione** declinata — a sinistra il tipo di intorno del punto base ($U_\delta^\ast(x_0)$, $(R,+\infty)$ o $(-\infty,-R)$), a destra il tipo di intorno del valore limite ($V_\varepsilon(L)$, $(M,+\infty)$ o $(-\infty,-M)$).

---
### **7. La retta reale estesa**

Il quadro unificante è

$$
\overline{\mathbb{R}} = \mathbb{R}\cup\{-\infty,+\infty\}
$$

con l'ordine esteso $-\infty<x<+\infty$ per ogni $x\in\mathbb{R}$, e con la nozione di intorno estesa: intorni di $x_0\in\mathbb{R}$ come sempre, intorni di $+\infty$ le semirette $(K,+\infty)$, intorni di $-\infty$ le semirette $(-\infty,K)$.

Con questo linguaggio **tutte** le definizioni di questa lezione diventano un'unica frase:

> $\lim_{x\to c}f(x)=\ell$ (con $c,\ell\in\overline{\mathbb{R}}$) se per ogni intorno $V$ di $\ell$ esiste un intorno $U$ di $c$ tale che $f\big((U\setminus\{c\})\cap A\big)\subseteq V$.

Sulle operazioni, in $\overline{\mathbb{R}}$ valgono convenzioni parziali ($+\infty+\infty=+\infty$, $c\cdot(+\infty)=+\infty$ per $c>0$, $\frac{c}{\pm\infty}=0$, eccetera — sono i "teoremi di aritmetizzazione" già visti per le successioni in M05), ma restano **non definite** le scritture

$$
\infty-\infty,\qquad 0\cdot\infty,\qquad \frac{\infty}{\infty},\qquad \frac{0}{0}
$$

che sono precisamente le forme indeterminate (Lezione 3F). Distinguiamo con cura tre esiti per un limite:

1. esiste **finito** ($\ell\in\mathbb{R}$);
2. esiste **esteso ma non finito** ($\ell=\pm\infty$): si dice che $f$ **diverge** a $\pm\infty$;
3. **non esiste nemmeno in $\overline{\mathbb{R}}$**: nessun valore, finito o infinito, soddisfa la definizione.

---
### **8. Divergenza contro oscillazione**

Il terzo esito merita esempi, perché il linguaggio comune ("diverge") tende a schiacciarlo sul secondo.

- $\sin\left(\dfrac{1}{x}\right)$ per $x\to 0$: in ogni intorno puntato di $0$ la funzione assume **tutti** i valori di $[-1,1]$ infinite volte (basta scegliere $x=\frac{1}{t}$ con $t$ nei punti giusti): **oscillazione limitata**, nessun limite, nemmeno laterale. Lo dimostreremo pulitamente col criterio sequenziale (Lezione 3E).
- $\sin x$ per $x\to+\infty$: stessa situazione all'infinito — limitata, senza limite.
- $x\sin x$ per $x\to+\infty$: **oscillazione non limitata**. La funzione non è limitata (nei punti $x_k=\frac{\pi}{2}+2k\pi$ vale $x_k\to+\infty$) ma non diverge a $+\infty$ (nei punti $k\pi$ vale $0$): il limite non esiste nemmeno esteso.
- $\dfrac{1}{x^2}$ per $x\to 0$: **diverge** a $+\infty$ — questo sì.

> ⚠️ Uso della parola "diverge": in questo corso "$f$ diverge" significa "$f$ tende a $+\infty$ o a $-\infty$". Per $\sin x$ all'infinito si dice che il limite **non esiste** (o che la funzione **oscilla**), non che "diverge". Alcuni testi usano "divergente" per qualunque non-convergenza: convenzione diversa, da riconoscere e dichiarare.

<!-- TODO FIGURA:
Grafico di sin(1/x) su (0, 0.5] che mostra le oscillazioni sempre più fitte avvicinandosi a 0, con la fascia [-1,1] tratteggiata; accanto, grafico di x*sin(x) su [0, 30] con l'inviluppo y = ±x tratteggiato e i tocchi su y=0 nei multipli di pi.
Obiettivo didattico: contrapporre oscillazione limitata con frequenza che esplode (niente limite in un punto) e oscillazione con ampiezza che esplode (niente limite esteso all'infinito).
-->

---
### **9. Approfondimento: limiti di funzioni complesse**

La definizione di limite usa solo la **distanza** $|x-x_0|$: nulla vieta di rileggerla in $\mathbb{C}$, dove il modulo $|z-z_0|$ misura la distanza nel piano complesso (M04). Per $f:A\subseteq\mathbb{C}\to\mathbb{C}$ e $z_0$ di accumulazione per $A$:

$$
\lim_{z\to z_0}f(z)=L
\quad\Longleftrightarrow\quad
\forall\varepsilon>0\ \exists\delta>0\ \forall z\in A:\ 0<|z-z_0|<\delta \Rightarrow |f(z)-L|<\varepsilon
$$

La differenza cruciale è **geometrica**: sull'asse reale ci si avvicina a $x_0$ solo da due lati; nel piano ci si avvicina a $z_0$ lungo **infinite direzioni e traiettorie** (rette, spirali, curve qualunque), e la definizione impone lo stesso valore lungo tutte.

**Esempio/controesempio.** $f(z)=\dfrac{\bar z}{z}$ per $z\to 0$: lungo l'asse reale ($z=t$, $t\to 0$) il rapporto vale $1$; lungo l'asse immaginario ($z=it$) vale $\frac{-it}{it}=-1$. Due direzioni, due valori: il limite non esiste — l'analogo bidimensionale dei limiti laterali discordi. Non svilupperemo oltre l'analisi complessa: questa sezione serve solo a mostrare che il concetto di limite è più generale della retta.

---
### **10. Errori frequenti**

- ❌ Concludere un limite bilaterale calcolando **un solo** laterale (quando il dominio accumula da entrambi i lati).
- ❌ Ignorare il dominio: chiedersi il limite sinistro di $\sqrt{x}$ in $0$ non ha senso.
- ❌ Scrivere catene come $\lim f = \infty - \infty = 0$: le forme non definite non si "calcolano".
- ❌ Trattare $+\infty$ come numero: ad esempio "il limite esiste ed è $+\infty$, quindi posso sottrarlo da entrambi i membri".
- ❌ Dedurre da "$f$ non limitata vicino a $x_0$" che "$f\to\pm\infty$": controesempio $x\sin x$ (all'infinito) o $\frac{1}{x}\sin\frac{1}{x}$ (in $0$).

---
### **11. Esercizi**

**Esercizio 1.** Calcola i limiti laterali in $x_0=1$ di $f(x)=\dfrac{x+1}{x-1}$ e concludi sul bilaterale.

*Soluzione.* Numeratore $\to 2>0$; il denominatore tende a $0^+$ da destra e $0^-$ da sinistra: $\lim_{x\to 1^+}f=+\infty$, $\lim_{x\to 1^-}f=-\infty$; il bilaterale non esiste (nemmeno esteso).

**Esercizio 2.** Determina $a$ affinché $f(x)=\begin{cases} 2x+a, & x<0 \\ x^2-1, & x\ge 0\end{cases}$ ammetta limite per $x\to 0$.

*Soluzione.* Laterale sinistro $a$, destro $-1$: serve $a=-1$ (e in tal caso il limite vale $-1=f(0)$).

**Esercizio 3.** Dimostra con la definizione che $\lim_{x\to+\infty}\dfrac{x}{x+1}=1$.

*Soluzione.* $\left|\frac{x}{x+1}-1\right| = \frac{1}{x+1} < \frac{1}{x}$ per $x>0$; dato $\varepsilon$, basta $R=\frac{1}{\varepsilon}$.

**Esercizio 4.** Dimostra con la definizione che $\lim_{x\to-\infty}x^3=-\infty$.

*Soluzione.* Dato $M>0$, per $x<-R$ con $R=\sqrt[3]{M}$ si ha $x^3<-R^3=-M$.

---
### **12. Riepilogo**

> ✅ I limiti laterali sono la definizione ordinaria ristretta agli intorni destro/sinistro; se il punto accumula da entrambi i lati, il bilaterale esiste se e solo se i laterali esistono e coincidono (teorema dimostrato). Limiti infiniti e all'infinito si formulano sostituendo gli intorni: $M$ per le quote verticali, $R$ per le soglie orizzontali. $\overline{\mathbb{R}}$ unifica tutto ma non rende $\infty$ un numero: $\infty-\infty$, $0\cdot\infty$, $\frac{\infty}{\infty}$, $\frac{0}{0}$ restano non definite. Distingui: limite finito, divergenza a $\pm\infty$, non esistenza per oscillazione (limitata o meno).
