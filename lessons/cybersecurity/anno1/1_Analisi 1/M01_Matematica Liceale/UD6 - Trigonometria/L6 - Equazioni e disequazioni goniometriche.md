# **M1 UD6 Lezione 6 - Equazioni e disequazioni goniometriche**

### **1. Introduzione**

Le equazioni goniometriche sono equazioni in cui l’incognita compare dentro funzioni come seno, coseno o tangente.

Esempi:

$$
\sin x=\frac{1}{2}
$$

$$
\cos(2x)=0
$$

$$
\tan x\ge 1
$$

La caratteristica nuova rispetto alle equazioni algebriche è la periodicità: se un angolo è soluzione, spesso lo sono anche infiniti angoli ottenuti aggiungendo multipli del periodo.

> 📌 Nelle equazioni goniometriche non basta trovare un angolo: bisogna descrivere tutte le soluzioni periodiche.

---

### **2. Equazione $\sin x=a$**

L’equazione:

$$
\sin x=a
$$

ha soluzioni reali solo se:

$$
-1\le a\le 1
$$

Se $a\in[-1,1]$, poniamo:

$$
\alpha=\arcsin a
$$

con:

$$
\alpha\in\left[-\frac{\pi}{2},\frac{\pi}{2}\right]
$$

Le soluzioni sono:

$$
x=\alpha+2k\pi
$$

oppure:

$$
x=\pi-\alpha+2k\pi
$$

con:

$$
k\in\mathbb{Z}
$$

---

### **3. Esempio con il seno**

Risolviamo:

$$
\sin x=\frac{1}{2}
$$

Nel primo giro le soluzioni sono:

$$
x=\frac{\pi}{6}
$$

e:

$$
x=\frac{5\pi}{6}
$$

Tenendo conto della periodicità:

$$
x=\frac{\pi}{6}+2k\pi
$$

oppure:

$$
x=\frac{5\pi}{6}+2k\pi
$$

con $k\in\mathbb{Z}$.

---

### **4. Equazione $\cos x=a$**

L’equazione:

$$
\cos x=a
$$

ha soluzioni reali solo se:

$$
-1\le a\le 1
$$

Se:

$$
\alpha=\arccos a
$$

con $\alpha\in[0,\pi]$, allora le soluzioni sono:

$$
x=\alpha+2k\pi
$$

oppure:

$$
x=-\alpha+2k\pi
$$

con $k\in\mathbb{Z}$.

Equivalentemente:

$$
x=\pm\alpha+2k\pi
$$

---

### **5. Equazione $\tan x=a$**

La tangente ha periodo $\pi$ e immagine $\mathbb{R}$.

L’equazione:

$$
\tan x=a
$$

ha sempre soluzioni reali.

Se:

$$
\alpha=\arctan a
$$

allora:

$$
x=\alpha+k\pi,\quad k\in\mathbb{Z}
$$

Esempio:

$$
\tan x=1
$$

dà:

$$
x=\frac{\pi}{4}+k\pi
$$

Per la cotangente, adottando la funzione principale descritta nella lezione sulle inverse,

$$
\cot x=a
\iff
x=\operatorname{arccot}(a)+k\pi,\qquad k\in\mathbb{Z}.
$$

Se $a=0$, si ottiene $x=\pi/2+k\pi$. I punti $x=k\pi$ sono sempre esclusi dal dominio della cotangente.

---

### **6. Equazioni con argomento composto**

Risolviamo:

$$
\sin(2x)=\frac{\sqrt{3}}{2}
$$

Poniamo:

$$
t=2x
$$

Allora:

$$
\sin t=\frac{\sqrt{3}}{2}
$$

Le soluzioni in $t$ sono:

$$
t=\frac{\pi}{3}+2k\pi
$$

oppure:

$$
t=\frac{2\pi}{3}+2k\pi
$$

Poiché $t=2x$, otteniamo:

$$
2x=\frac{\pi}{3}+2k\pi
$$

oppure:

$$
2x=\frac{2\pi}{3}+2k\pi
$$

Dividendo per $2$:

$$
x=\frac{\pi}{6}+k\pi
$$

oppure:

$$
x=\frac{\pi}{3}+k\pi
$$

con $k\in\mathbb{Z}$.

---

### **7. Equazioni riconducibili con formule**

Risolviamo:

$$
2\sin x\cos x=\sin x
$$

Portiamo tutto a sinistra:

$$
2\sin x\cos x-\sin x=0
$$

Raccogliamo:

$$
\sin x(2\cos x-1)=0
$$

Un prodotto è zero se almeno un fattore è zero:

$$
\sin x=0
$$

oppure:

$$
2\cos x-1=0
$$

Dal primo:

$$
x=k\pi
$$

Dal secondo:

$$
\cos x=\frac{1}{2}
$$

quindi:

$$
x=\pm\frac{\pi}{3}+2k\pi
$$

con $k\in\mathbb{Z}$.

---

### **8. Disequazioni goniometriche elementari**

Per risolvere disequazioni come:

$$
\sin x\ge a
$$

si ragiona prima su un periodo, di solito $[0,2\pi)$, poi si aggiunge la periodicità.

Esempio:

$$
\sin x\ge \frac{1}{2}
$$

Nel periodo $[0,2\pi)$ il seno vale $\frac{1}{2}$ in:

$$
\frac{\pi}{6},\quad \frac{5\pi}{6}
$$

Tra questi due angoli il seno è maggiore o uguale a $\frac{1}{2}$. Quindi:

$$
x\in\left[\frac{\pi}{6},\frac{5\pi}{6}\right]
$$

nel periodo principale.

La soluzione completa è:

$$
x\in\left[\frac{\pi}{6}+2k\pi,\frac{5\pi}{6}+2k\pi\right],\quad k\in\mathbb{Z}
$$

<!-- TODO FIGURA:
Circonferenza goniometrica con le rette orizzontali y=1/2 e gli archi per cui sin x>1/2. Evidenziare gli estremi pi/6 e 5pi/6, distinguendo inclusione ed esclusione. Obiettivo didattico: passare dalla disequazione all'arco e poi agli intervalli periodici.
-->

---

### **9. Disequazione con il coseno**

Risolviamo:

$$
\cos x<0
$$

Nel periodo $[0,2\pi)$ il coseno è negativo nel secondo e nel terzo quadrante:

$$
\frac{\pi}{2}<x<\frac{3\pi}{2}
$$

Quindi la soluzione completa è:

$$
x\in\left(\frac{\pi}{2}+2k\pi,\frac{3\pi}{2}+2k\pi\right),\quad k\in\mathbb{Z}
$$

Un secondo esempio è

$$
\cos x\leq-\frac{\sqrt{2}}{2}.
$$

Nel periodo $[0,2\pi)$ il coseno raggiunge il valore assegnato in $3\pi/4$ e $5\pi/4$ ed è minore tra questi angoli. Quindi

$$
x\in\left[\frac{3\pi}{4}+2k\pi,\frac{5\pi}{4}+2k\pi\right],\qquad k\in\mathbb{Z}.
$$

---

### **10. Disequazione con la tangente**

Risolviamo:

$$
\tan x\ge 1
$$

La tangente ha periodo $\pi$. Nel periodo:

$$
\left(-\frac{\pi}{2},\frac{\pi}{2}\right)
$$

è crescente e vale $1$ per:

$$
x=\frac{\pi}{4}
$$

Quindi nel ramo principale:

$$
x\in\left[\frac{\pi}{4},\frac{\pi}{2}\right)
$$

L’estremo $\frac{\pi}{2}$ è escluso perché la tangente non è definita.

La soluzione completa è:

$$
x\in\left[\frac{\pi}{4}+k\pi,\frac{\pi}{2}+k\pi\right),\quad k\in\mathbb{Z}
$$

Per

$$
\tan x<1,
$$

si lavora su ogni ramo $(-\pi/2+k\pi,\pi/2+k\pi)$, sul quale la tangente è crescente. La soluzione è

$$
x\in\left(-\frac{\pi}{2}+k\pi,\frac{\pi}{4}+k\pi\right),\qquad k\in\mathbb{Z}.
$$

Non è corretto descrivere la soluzione come un unico intervallo illimitato: tra due rami esiste sempre un punto escluso.

### **11. Disequazioni fattorizzate e razionali**

Risolviamo

$$
\frac{\sin x}{1+\cos x}\geq 0.
$$

Il denominatore si annulla per

$$
x=\pi+2k\pi,
$$

che sono punti esclusi. Inoltre $1+\cos x\geq 0$ per ogni $x$ ed è positivo nel dominio. Il segno del rapporto coincide quindi con quello di $\sin x$. Su $[0,2\pi)$ si ottiene

$$
x\in[0,\pi),
$$

dove $0$ è incluso perché annulla il numeratore, mentre $\pi$ è escluso perché annulla il denominatore. La soluzione completa è

$$
x\in[2k\pi,\pi+2k\pi),\qquad k\in\mathbb{Z}.
$$

La trasformazione

$$
\frac{\sin x}{1+\cos x}=\tan\frac{x}{2}
$$

è utile, ma resta valida soltanto dove il membro sinistro è definito; non consente di reinserire i punti esclusi.

### **12. Restrizione a un intervallo assegnato**

Se un problema richiede le soluzioni in $[0,2\pi]$, prima si determina la soluzione generale, poi si selezionano gli interi $k$ compatibili con l'intervallo. Per esempio,

$$
\cos(2x)=0
\iff
2x=\frac{\pi}{2}+k\pi
\iff
x=\frac{\pi}{4}+\frac{k\pi}{2}.
$$

In $[0,2\pi]$ sono ammessi $k=0,1,2,3$, quindi

$$
x\in\left\{\frac{\pi}{4},\frac{3\pi}{4},\frac{5\pi}{4},\frac{7\pi}{4}\right\}.
$$

#### **12.1 Disequazione riducibile con la duplicazione**

Risolviamo

$$
2\sin x\cos x>0.
$$

Poiché $2\sin x\cos x=\sin(2x)$, la disequazione equivale a

$$
\sin(2x)>0.
$$

Il seno è positivo quando il suo argomento appartiene a $(2k\pi,(2k+1)\pi)$. Pertanto

$$
2k\pi<2x<(2k+1)\pi,
$$

e dividendo per $2$,

$$
x\in\left(k\pi,k\pi+\frac{\pi}{2}\right),\qquad k\in\mathbb{Z}.
$$

Gli estremi sono esclusi perché la disequazione è stretta.

### **13. Metodo generale per le disequazioni**

1. determinare il dominio;
2. ridurre l'espressione a una forma elementare o fattorizzata;
3. risolvere su un periodo fondamentale;
4. controllare estremi inclusi, esclusi e punti di non definizione;
5. estendere mediante il periodo corretto;
6. intersecare con eventuali intervalli assegnati.

La circonferenza è particolarmente efficace per seno e coseno; il grafico a rami è più sicuro per tangente e cotangente.

---

### **14. Errori tipici**

#### **14.1 Perdere una famiglia di soluzioni**

Per:

$$
\sin x=\frac{1}{2}
$$

scrivere solo:

$$
x=\frac{\pi}{6}+2k\pi
$$

è incompleto: manca la famiglia:

$$
x=\frac{5\pi}{6}+2k\pi
$$

#### **14.2 Dividere per una funzione goniometrica**

Da:

$$
\sin x\cos x=\sin x
$$

non si deve dividere subito per $\sin x$, perché si perderebbero le soluzioni con:

$$
\sin x=0
$$

Bisogna portare tutto a sinistra e raccogliere.

#### **14.3 Dimenticare i punti esclusi**

Nelle disequazioni con tangente bisogna escludere:

$$
x=\frac{\pi}{2}+k\pi
$$

#### **14.4 Usare il periodo sbagliato**

Una funzione come $\sin(2x)$ ha periodo $\pi$, non $2\pi$. Il periodo cambia quando cambia la pulsazione dell'argomento.

#### **14.5 Elencare solo le soluzioni del primo giro**

Un elenco finito è completo soltanto se il problema impone un intervallo limitato. Su $\mathbb{R}$ bisogna introdurre $k\in\mathbb{Z}$.

---

### **15. Riepilogo**

> ✅ Equazioni e disequazioni goniometriche richiedono sempre periodicità, dominio e attenzione ai rami.

Regole base:

- $\sin x=a$ ha soluzioni solo se $a\in[-1,1]$;
- $\cos x=a$ ha soluzioni solo se $a\in[-1,1]$;
- $\tan x=a$ ha sempre soluzioni reali;
- seno e coseno hanno periodo $2\pi$;
- tangente ha periodo $\pi$;
- nelle disequazioni si risolve su un periodo e poi si ripete la soluzione.
