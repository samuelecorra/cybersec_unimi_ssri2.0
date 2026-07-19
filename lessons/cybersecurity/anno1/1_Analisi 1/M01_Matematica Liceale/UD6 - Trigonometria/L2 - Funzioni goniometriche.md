# **M1 UD6 Lezione 2 - Funzioni goniometriche**

### **1. Introduzione**

Dopo aver definito seno e coseno sulla circonferenza goniometrica, possiamo considerarli come vere funzioni reali:

$$
\sin:\mathbb{R}\to[-1,1]
$$

$$
\cos:\mathbb{R}\to[-1,1]
$$

A ogni numero reale $x$ associamo un angolo di $x$ radianti e quindi un punto sulla circonferenza. Le coordinate di quel punto sono $\cos x$ e $\sin x$.

> 📌 Le funzioni goniometriche trasformano un numero reale in una grandezza periodica.

---

### **2. Seno**

La funzione seno è:

$$
f(x)=\sin x
$$

Dominio:

$$
D_f=\mathbb{R}
$$

Immagine:

$$
\operatorname{Im}(f)=[-1,1]
$$

Il seno è l’ordinata del punto sulla circonferenza goniometrica. Per questo non può superare $1$ e non può scendere sotto $-1$.

Valori fondamentali:

$$
\sin 0=0,\quad \sin\frac{\pi}{2}=1,\quad \sin\pi=0,\quad \sin\frac{3\pi}{2}=-1,\quad \sin 2\pi=0
$$

Il seno è periodico di periodo $2\pi$:

$$
\sin(x+2k\pi)=\sin x,\quad k\in\mathbb{Z}
$$

---

### **3. Coseno**

La funzione coseno è:

$$
f(x)=\cos x
$$

Dominio:

$$
D_f=\mathbb{R}
$$

Immagine:

$$
\operatorname{Im}(f)=[-1,1]
$$

Il coseno è l’ascissa del punto sulla circonferenza goniometrica.

Valori fondamentali:

$$
\cos 0=1,\quad \cos\frac{\pi}{2}=0,\quad \cos\pi=-1,\quad \cos\frac{3\pi}{2}=0,\quad \cos 2\pi=1
$$

Anche il coseno è periodico di periodo $2\pi$:

$$
\cos(x+2k\pi)=\cos x,\quad k\in\mathbb{Z}
$$

---

### **4. Grafici qualitativi di seno e coseno**

Il grafico del seno oscilla tra $-1$ e $1$, passando per l’origine.

Il grafico del coseno ha la stessa forma, ma parte da $1$ quando $x=0$.

<!-- TODO FIGURA:
Grafici di y=sin x e y=cos x sullo stesso sistema di assi per x in [-2pi,2pi]. Marcare zeri, massimi, minimi e lo sfasamento di pi/2. Obiettivo didattico: leggere periodicità, parità e relazione tra i due grafici.
-->

La relazione tra i due grafici è uno sfasamento:

$$
\cos x=\sin\left(x+\frac{\pi}{2}\right)
$$

e:

$$
\sin x=\cos\left(x-\frac{\pi}{2}\right)
$$

---

### **5. Tangente**

La tangente è definita da:

$$
\tan x=\frac{\sin x}{\cos x}
$$

Quindi esiste solo quando:

$$
\cos x\neq 0
$$

Gli zeri del coseno sono:

$$
x=\frac{\pi}{2}+k\pi,\quad k\in\mathbb{Z}
$$

Perciò il dominio della tangente è:

$$
D_{\tan}=\mathbb{R}\setminus\left\{\frac{\pi}{2}+k\pi:k\in\mathbb{Z}\right\}
$$

La tangente è periodica di periodo $\pi$:

$$
\tan(x+k\pi)=\tan x,\quad k\in\mathbb{Z}
$$

La sua immagine è:

$$
\mathbb{R}
$$

---

### **6. Cotangente**

La cotangente è definita da:

$$
\cot x=\frac{\cos x}{\sin x}
$$

Quindi esiste solo quando:

$$
\sin x\neq 0
$$

Gli zeri del seno sono:

$$
x=k\pi,\quad k\in\mathbb{Z}
$$

Il dominio è:

$$
D_{\cot}=\mathbb{R}\setminus\{k\pi:k\in\mathbb{Z}\}
$$

Anche la cotangente ha periodo $\pi$:

$$
\cot(x+k\pi)=\cot x
$$

---

### **7. Secante e cosecante**

In alcuni testi compaiono anche secante e cosecante:

$$
\sec x=\frac{1}{\cos x}
$$

$$
\csc x=\frac{1}{\sin x}
$$

Queste funzioni sono utili soprattutto in manipolazioni algebriche e in alcune integrazioni, ma nel percorso base di Analisi 1 sono meno centrali di seno, coseno e tangente.

I loro domini derivano direttamente dai denominatori:

$$
\sec x \text{ è definita per } \cos x\neq 0
$$

$$
\csc x \text{ è definita per } \sin x\neq 0
$$

Entrambe hanno immagine

$$
(-\infty,-1]\cup[1,+\infty),
$$

perché $|\sin x|\leq 1$ e $|\cos x|\leq 1$. La secante è pari e ha periodo $2\pi$; la cosecante è dispari e ha periodo $2\pi$.

Dall'identità fondamentale seguono, nei rispettivi domini,

$$
\sec^2x=1+\tan^2x,
$$

$$
\csc^2x=1+\cot^2x.
$$

---

### **8. Periodo**

Una funzione $f$ si dice **periodica** di periodo $T>0$ se:

$$
f(x+T)=f(x)
$$

per ogni $x$ del dominio.

Per seno e coseno:

$$
T=2\pi
$$

Per tangente e cotangente:

$$
T=\pi
$$

Il periodo indica dopo quanto il comportamento della funzione si ripete.

---

### **9. Zeri e valori estremi**

Gli zeri del seno sono:

$$
\sin x=0 \iff x=k\pi,\quad k\in\mathbb{Z}
$$

Gli zeri del coseno sono:

$$
\cos x=0 \iff x=\frac{\pi}{2}+k\pi,\quad k\in\mathbb{Z}
$$

Il seno vale $1$ per:

$$
x=\frac{\pi}{2}+2k\pi
$$

e vale $-1$ per:

$$
x=\frac{3\pi}{2}+2k\pi
$$

Il coseno vale $1$ per:

$$
x=2k\pi
$$

e vale $-1$ per:

$$
x=\pi+2k\pi
$$

### **10. Parità, segno e comportamento qualitativo**

Le simmetrie della circonferenza danno

$$
\sin(-x)=-\sin x,\qquad \cos(-x)=\cos x.
$$

Il seno è dispari e il coseno è pari. Di conseguenza tangente e cotangente sono dispari, mentre la secante è pari e la cosecante è dispari.

Su ogni intervallo

$$
\left(-\frac{\pi}{2}+k\pi,\frac{\pi}{2}+k\pi\right)
$$

la tangente percorre tutti i valori reali. Avvicinandosi agli estremi, il suo valore cresce o decresce senza limite; gli estremi non appartengono al dominio. La cotangente presenta lo stesso tipo di comportamento vicino ai punti $k\pi$, ma su $(0,\pi)$ decresce da valori positivi illimitati a valori negativi illimitati.

<!-- TODO FIGURA:
Due pannelli: grafico di y=tan x su tre rami con rette verticali x=pi/2+kpi, e grafico di y=cot x con rette verticali x=kpi. Indicare zeri, periodo pi, segni e versi di percorrenza. Obiettivo didattico: distinguere dominio, discontinuità e monotonia qualitative.
-->

<!-- TODO FIGURA:
Grafici di y=sec x e y=csc x per x in [-2pi,2pi], insieme ai grafici tratteggiati di y=cos x e y=sin x. Evidenziare immagine (-infinito,-1] unita [1,+infinito), punti esclusi e rami. Obiettivo didattico: interpretare geometricamente i reciproci.
-->

### **11. Trasformazioni di seno e coseno**

Consideriamo

$$
f(x)=A\sin(\omega x+\varphi)+k,
$$

con $A\neq 0$ e $\omega\neq 0$. La stessa analisi vale per il coseno.

- l'**ampiezza** è $|A|$;
- l'immagine è $[k-|A|,k+|A|]$;
- il **periodo** è $T=2\pi/|\omega|$;
- la traslazione verticale è $k$;
- lo sfasamento orizzontale è $-\varphi/\omega$;
- se $A<0$, il grafico è riflesso rispetto alla propria linea media $y=k$.

La quantità $\omega$ è detta pulsazione. Non va confusa con il periodo: maggiore è $|\omega|$, minore è il periodo.

#### **11.1 Esempio completo**

Per

$$
f(x)=-3\sin\left(2x-\frac{\pi}{2}\right)+1
$$

si ha

$$
A=-3,\qquad \omega=2,\qquad \varphi=-\frac{\pi}{2},\qquad k=1.
$$

Pertanto l'ampiezza è $3$, il periodo è

$$
T=\frac{2\pi}{2}=\pi,
$$

lo sfasamento è

$$
-\frac{\varphi}{\omega}=\frac{\pi}{4},
$$

e l'immagine è $[-2,4]$.

<!-- TODO FIGURA:
Costruzione per trasformazioni successive del grafico di y=sin x fino a y=-3sin(2x-pi/2)+1. Mostrare compressione orizzontale, traslazione di pi/4, riflessione, ampiezza 3 e linea media y=1. Obiettivo didattico: distinguere ampiezza, periodo, fase e traslazione.
-->

### **12. Collegamenti con l'Analisi**

La periodicità spiega perché seno e coseno non possiedono limite per $x\to+\infty$: continuano a oscillare. Nei moduli successivi saranno dimostrate la loro continuità, le derivate e gli sviluppi di Taylor. Le identità di questa unità saranno inoltre usate per trasformare integrali trigonometrici.

---

### **13. Errori tipici**

#### **13.1 Dimenticare il dominio della tangente**

L’espressione:

$$
\tan\frac{\pi}{2}
$$

non è definita, perché:

$$
\cos\frac{\pi}{2}=0
$$

#### **13.2 Confondere periodo di seno e tangente**

Il seno ha periodo $2\pi$, mentre la tangente ha periodo $\pi$.

Infatti:

$$
\tan(x+\pi)=\tan x
$$

ma:

$$
\sin(x+\pi)=-\sin x
$$

#### **13.3 Confondere $\sin^2x$ e $\sin(x^2)$**

Per convenzione,

$$
\sin^2x=(\sin x)^2,
$$

mentre $\sin(x^2)$ è il seno dell'argomento $x^2$: sono funzioni differenti.

---

### **14. Riepilogo**

> ✅ Seno e coseno sono funzioni definite su tutta la retta reale e limitate tra $-1$ e $1$; tangente e cotangente sono rapporti e hanno punti esclusi dal dominio.

Punti essenziali:

- $\sin x$ e $\cos x$ hanno periodo $2\pi$;
- $\tan x$ e $\cot x$ hanno periodo $\pi$;
- $\tan x$ non è definita dove $\cos x=0$;
- $\cot x$ non è definita dove $\sin x=0$;
- seno e coseno sono limitati, tangente e cotangente no.
