# **M1 UD6 Lezione 1 - Intro alla goniometria**

### **1. Introduzione**

La goniometria studia gli angoli e le grandezze associate agli angoli: seno, coseno, tangente e le loro relazioni. In Analisi 1 queste funzioni non servono solo per risolvere triangoli: diventano funzioni reali, periodiche, continue, derivate e integrate.

Il passaggio concettuale è importante:

- nella geometria elementare un angolo è spesso legato a un triangolo;
- in Analisi un angolo diventa un numero reale;
- seno e coseno diventano funzioni definite su tutta la retta reale.

> 📌 La goniometria è il ponte tra geometria, funzioni periodiche e Analisi.

---

### **2. Angoli orientati**

Un angolo può essere visto come una rotazione di una semiretta attorno alla sua origine.

Si fissa una semiretta iniziale, di solito il semiasse positivo delle $x$, e si misura la rotazione:

- in senso antiorario: angolo positivo;
- in senso orario: angolo negativo.

Questa convenzione permette di considerare anche angoli come:

$$
-\frac{\pi}{3},\quad \frac{7\pi}{4},\quad 5\pi
$$

Non ci si limita più agli angoli tra $0^\circ$ e $180^\circ$ dei triangoli.

---

### **3. Gradi e radianti**

Gli angoli si possono misurare in gradi oppure in radianti.

La misura in gradi divide l’angolo giro in:

$$
360^\circ
$$

La misura in radianti è invece naturale per l’Analisi.

Un angolo di ampiezza $\theta$ radianti è definito dal rapporto:

$$
\theta=\frac{s}{r}
$$

dove:

- $s$ è la lunghezza dell’arco intercettato;
- $r$ è il raggio della circonferenza.

Poiché sia $s$ sia $r$ sono lunghezze, il radiante è una misura adimensionale.

> 📌 In Analisi si usano i radianti perché collegano direttamente angoli, archi e limiti.

---

### **4. Conversione tra gradi e radianti**

Un angolo giro misura:

$$
360^\circ=2\pi
$$

Quindi:

$$
180^\circ=\pi
$$

Da questa equivalenza si ricavano le conversioni:

$$
\alpha^\circ=\alpha\cdot\frac{\pi}{180}
$$

e:

$$
\theta\ \text{radianti}=\theta\cdot\frac{180}{\pi}^\circ
$$

Esempi:

$$
30^\circ=30\cdot\frac{\pi}{180}=\frac{\pi}{6}
$$

$$
45^\circ=\frac{\pi}{4}
$$

$$
60^\circ=\frac{\pi}{3}
$$

$$
90^\circ=\frac{\pi}{2}
$$

---

### **5. La circonferenza goniometrica**

La **circonferenza goniometrica** è la circonferenza di centro l’origine e raggio $1$ nel piano cartesiano.

La sua equazione è:

$$
x^2+y^2=1
$$

Dato un angolo orientato $\theta$, si parte dal punto $(1,0)$ e si ruota di ampiezza $\theta$. Il punto ottenuto sulla circonferenza ha coordinate:

$$
(\cos\theta,\sin\theta)
$$

Questa è la definizione fondamentale:

> 📌 Il coseno di un angolo è l’ascissa del punto sulla circonferenza goniometrica; il seno è l’ordinata.

<!-- TODO FIGURA:
Circonferenza unitaria nel piano cartesiano con i quattro quadranti, il punto P(theta)=(cos theta,sin theta), il raggio OP e le proiezioni di P sugli assi. Evidenziare verso positivo antiorario, verso negativo orario e segni delle coordinate in ogni quadrante. Obiettivo didattico: collegare angolo orientato, coordinate e definizioni di seno e coseno.
-->

---

### **6. Valori principali sulla circonferenza**

Sulla circonferenza goniometrica:

$$
\cos 0=1,\quad \sin 0=0
$$

Per l’angolo retto:

$$
\cos\frac{\pi}{2}=0,\quad \sin\frac{\pi}{2}=1
$$

Per l’angolo piatto:

$$
\cos\pi=-1,\quad \sin\pi=0
$$

Per l’angolo giro:

$$
\cos 2\pi=1,\quad \sin 2\pi=0
$$

Questi valori non vanno imparati come una tabella isolata: corrispondono ai punti fondamentali della circonferenza.

---

### **7. Periodicità degli angoli**

Ruotare di un angolo $\theta$ oppure di un angolo:

$$
\theta+2k\pi
$$

con $k\in\mathbb{Z}$ porta allo stesso punto sulla circonferenza.

Quindi:

$$
\sin(\theta+2k\pi)=\sin\theta
$$

e:

$$
\cos(\theta+2k\pi)=\cos\theta
$$

per ogni $k\in\mathbb{Z}$.

Questa proprietà si chiama **periodicità**.

---

### **8. Angoli notevoli**

Gli angoli fondamentali da conoscere sono:

$$
0,\quad \frac{\pi}{6},\quad \frac{\pi}{4},\quad \frac{\pi}{3},\quad \frac{\pi}{2},\quad \pi,\quad \frac{3\pi}{2},\quad 2\pi
$$

I valori nel primo quadrante sono:

$$
\sin\frac{\pi}{6}=\frac{1}{2},\quad \cos\frac{\pi}{6}=\frac{\sqrt{3}}{2}
$$

$$
\sin\frac{\pi}{4}=\frac{\sqrt{2}}{2},\quad \cos\frac{\pi}{4}=\frac{\sqrt{2}}{2}
$$

$$
\sin\frac{\pi}{3}=\frac{\sqrt{3}}{2},\quad \cos\frac{\pi}{3}=\frac{1}{2}
$$

Gli altri valori si ricavano usando segni e simmetrie, non imparando infinite tabelle.

---

### **9. Quadranti e segni**

Il segno di seno e coseno dipende dal quadrante:

- primo quadrante: $\sin x>0$, $\cos x>0$;
- secondo quadrante: $\sin x>0$, $\cos x<0$;
- terzo quadrante: $\sin x<0$, $\cos x<0$;
- quarto quadrante: $\sin x<0$, $\cos x>0$.

La tangente, quando definita, è:

$$
\tan x=\frac{\sin x}{\cos x}
$$

quindi è positiva quando seno e coseno hanno lo stesso segno, negativa quando hanno segno opposto.

---

### **10. Identità fondamentale**

Ogni punto della circonferenza goniometrica soddisfa:

$$
x^2+y^2=1
$$

Poiché:

$$
x=\cos\theta,\quad y=\sin\theta
$$

otteniamo:

$$
\cos^2\theta+\sin^2\theta=1
$$

Questa è la prima identità fondamentale della trigonometria.

> 📌 L’identità $\sin^2\theta+\cos^2\theta=1$ non è una formula da memorizzare a caso: è l’equazione della circonferenza goniometrica.

---

### **11. Perché il radiante è la misura naturale**

Su una circonferenza di raggio $r$, un angolo di ampiezza $\theta$ radianti intercetta un arco di lunghezza

$$
s=r\theta.
$$

Questa formula è diretta proprio perché $\theta=s/r$. Se l'angolo fosse espresso in gradi comparirebbe invece il fattore $\pi/180$:

$$
s=r\frac{\pi}{180}\theta_{\mathrm{gradi}}.
$$

La stessa differenza ricompare nel calcolo infinitesimale. I risultati

$$
\lim_{x\to 0}\frac{\sin x}{x}=1
$$

e

$$
\frac{d}{dx}\sin x=\cos x
$$

hanno questa forma soltanto se $x$ è misurato in radianti. In gradi comparirebbe il fattore $\pi/180$. Le dimostrazioni saranno sviluppate nei moduli sui limiti e sulle derivate; qui importa riconoscere che il radiante non è una convenzione cosmetica, ma la misura compatibile con la struttura analitica delle funzioni trigonometriche.

### **12. Derivazione dei valori notevoli**

#### **12.1 Angoli di $\pi/4$**

Consideriamo un triangolo rettangolo isoscele con cateti di lunghezza $1$. Per Pitagora l'ipotenusa misura $\sqrt{2}$. Pertanto

$$
\sin\frac{\pi}{4}=\cos\frac{\pi}{4}=\frac{1}{\sqrt{2}}=\frac{\sqrt{2}}{2}.
$$

#### **12.2 Angoli di $\pi/6$ e $\pi/3$**

Dividiamo un triangolo equilatero di lato $2$ mediante un'altezza. Si ottengono due triangoli rettangoli con ipotenusa $2$, cateto minore $1$ e altro cateto $\sqrt{3}$. Di conseguenza

$$
\sin\frac{\pi}{6}=\frac{1}{2},\qquad \cos\frac{\pi}{6}=\frac{\sqrt{3}}{2},
$$

$$
\sin\frac{\pi}{3}=\frac{\sqrt{3}}{2},\qquad \cos\frac{\pi}{3}=\frac{1}{2}.
$$

I valori della tangente si ottengono dal rapporto $\tan x=\sin x/\cos x$, quando il coseno non è nullo.

<!-- TODO FIGURA:
Affiancare un triangolo rettangolo isoscele con cateti 1 e ipotenusa sqrt(2) e metà di un triangolo equilatero di lato 2 con lati 1, sqrt(3), 2. Indicare gli angoli pi/4, pi/6 e pi/3 e i rapporti che producono seno e coseno. Obiettivo didattico: derivare, non memorizzare, i valori notevoli.
-->

### **13. Tabella ragionata dei valori notevoli**

| $x$ | $0$ | $\frac{\pi}{6}$ | $\frac{\pi}{4}$ | $\frac{\pi}{3}$ | $\frac{\pi}{2}$ |
|---|---:|---:|---:|---:|---:|
| $\sin x$ | $0$ | $\frac12$ | $\frac{\sqrt2}{2}$ | $\frac{\sqrt3}{2}$ | $1$ |
| $\cos x$ | $1$ | $\frac{\sqrt3}{2}$ | $\frac{\sqrt2}{2}$ | $\frac12$ | $0$ |
| $\tan x$ | $0$ | $\frac{\sqrt3}{3}$ | $1$ | $\sqrt3$ | non definita |

La tabella riguarda il primo quadrante. Negli altri quadranti il valore assoluto dipende dall'angolo di riferimento, mentre il segno dipende dalle coordinate del punto sulla circonferenza.

### **14. Angoli congruenti e riduzione modulo $2\pi$**

Due numeri reali $\alpha$ e $\beta$ individuano lo stesso punto sulla circonferenza se e solo se differiscono di un numero intero di giri:

$$
\alpha\equiv\beta\pmod{2\pi}
\iff
\alpha-\beta=2k\pi
$$

per qualche $k\in\mathbb{Z}$. Per esempio,

$$
-\frac{7\pi}{6}+2\pi=\frac{5\pi}{6},
$$

quindi i due angoli sono congruenti modulo $2\pi$. Questa equivalenza riguarda il punto finale della rotazione, non il percorso compiuto: come angoli orientati possono descrivere rotazioni differenti.

> ⚠️ Per la tangente e la cotangente basta una traslazione di $k\pi$ per ripetere il valore, ma per il punto completo $(\cos x,\sin x)$ il periodo è $2\pi$.

### **15. Riepilogo**

> ✅ La goniometria in Analisi nasce dalla circonferenza goniometrica e dalla misura degli angoli in radianti.

Punti essenziali:

- gli angoli sono rotazioni orientate;
- $180^\circ=\pi$ radianti;
- sulla circonferenza unitaria il punto associato a $\theta$ è $(\cos\theta,\sin\theta)$;
- seno e coseno sono periodici di periodo $2\pi$;
- i segni dipendono dal quadrante;
- l’identità fondamentale è $\sin^2\theta+\cos^2\theta=1$.
