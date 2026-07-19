# **M1 UD6 Lezione 4 - Funzioni goniometriche inverse**

### **1. Introduzione**

Le funzioni seno, coseno e tangente non sono invertibili su tutto il loro dominio naturale, perché sono periodiche.

Per esempio:

$$
\sin 0=0
$$

ma anche:

$$
\sin\pi=0,\quad \sin 2\pi=0
$$

Una funzione che assume lo stesso valore in più punti non può avere inversa globale.

Per costruire le funzioni goniometriche inverse bisogna quindi restringere il dominio a un intervallo in cui la funzione sia monotona e assuma tutti i valori desiderati.

> 📌 Le inverse goniometriche non invertiscono seno, coseno e tangente su tutta la retta, ma solo su intervalli scelti come rami principali.

---

### **2. Arcoseno**

La funzione seno viene ristretta all’intervallo:

$$
\left[-\frac{\pi}{2},\frac{\pi}{2}\right]
$$

Su questo intervallo è strettamente crescente e assume tutti i valori tra $-1$ e $1$.

Si definisce:

$$
\arcsin:[-1,1]\to\left[-\frac{\pi}{2},\frac{\pi}{2}\right]
$$

La scrittura:

$$
y=\arcsin x
$$

significa:

$$
\sin y=x
$$

con:

$$
y\in\left[-\frac{\pi}{2},\frac{\pi}{2}\right]
$$

Esempi:

$$
\arcsin 0=0
$$

$$
\arcsin\frac{1}{2}=\frac{\pi}{6}
$$

$$
\arcsin(-1)=-\frac{\pi}{2}
$$

---

### **3. Arcocoseno**

La funzione coseno viene ristretta all’intervallo:

$$
[0,\pi]
$$

Su questo intervallo è strettamente decrescente e assume tutti i valori tra $1$ e $-1$.

Si definisce:

$$
\arccos:[-1,1]\to[0,\pi]
$$

La scrittura:

$$
y=\arccos x
$$

significa:

$$
\cos y=x
$$

con:

$$
y\in[0,\pi]
$$

Esempi:

$$
\arccos 1=0
$$

$$
\arccos 0=\frac{\pi}{2}
$$

$$
\arccos\left(-\frac{1}{2}\right)=\frac{2\pi}{3}
$$

---

### **4. Arcotangente**

La tangente viene ristretta all’intervallo:

$$
\left(-\frac{\pi}{2},\frac{\pi}{2}\right)
$$

Su questo intervallo è strettamente crescente e assume tutti i valori reali.

Si definisce:

$$
\arctan:\mathbb{R}\to\left(-\frac{\pi}{2},\frac{\pi}{2}\right)
$$

La scrittura:

$$
y=\arctan x
$$

significa:

$$
\tan y=x
$$

con:

$$
y\in\left(-\frac{\pi}{2},\frac{\pi}{2}\right)
$$

Esempi:

$$
\arctan 0=0
$$

$$
\arctan 1=\frac{\pi}{4}
$$

$$
\arctan(-1)=-\frac{\pi}{4}
$$

---

### **5. Dominio e immagine**

Riassumiamo:

$$
\arcsin x
$$

ha dominio $[-1,1]$ e immagine $\left[-\frac{\pi}{2},\frac{\pi}{2}\right]$.

$$
\arccos x
$$

ha dominio $[-1,1]$ e immagine $[0,\pi]$.

$$
\arctan x
$$

ha dominio $\mathbb{R}$ e immagine $\left(-\frac{\pi}{2},\frac{\pi}{2}\right)$.

> ⚠️ $\arcsin x$ e $\arccos x$ sono definiti solo per $-1\le x\le 1$.

La notazione $\sin^{-1}x$ è talvolta usata per $\arcsin x$, ma è ambigua: non indica $1/\sin x$. In questa repository si preferiscono $\arcsin$, $\arccos$ e $\arctan$; il reciproco del seno è scritto $\csc x$.

---

### **6. Composizioni corrette**

Per ogni $x\in[-1,1]$:

$$
\sin(\arcsin x)=x
$$

e:

$$
\cos(\arccos x)=x
$$

Per ogni $x\in\mathbb{R}$:

$$
\tan(\arctan x)=x
$$

Queste composizioni sono sempre vere perché si parte da un valore nel dominio dell’inversa.

---

### **7. Composizioni da trattare con attenzione**

La composizione:

$$
\arcsin(\sin x)
$$

non è sempre uguale a $x$.

È uguale a $x$ solo se:

$$
x\in\left[-\frac{\pi}{2},\frac{\pi}{2}\right]
$$

Per esempio:

$$
\arcsin\left(\sin\frac{5\pi}{6}\right)=\arcsin\frac{1}{2}=\frac{\pi}{6}
$$

non:

$$
\frac{5\pi}{6}
$$

Analogamente:

$$
\arccos(\cos x)=x
$$

solo per:

$$
x\in[0,\pi]
$$

e:

$$
\arctan(\tan x)=x
$$

solo per:

$$
x\in\left(-\frac{\pi}{2},\frac{\pi}{2}\right)
$$

---

### **8. Uso nelle equazioni**

L’equazione:

$$
\sin x=a
$$

con $a\in[-1,1]$ non ha una sola soluzione, ma infinite soluzioni periodiche.

L’arcoseno restituisce solo la soluzione principale:

$$
\alpha=\arcsin a
$$

Le soluzioni complete saranno:

$$
x=\alpha+2k\pi
$$

oppure:

$$
x=\pi-\alpha+2k\pi
$$

con $k\in\mathbb{Z}$.

Quindi le inverse goniometriche aiutano a trovare un angolo, ma non sostituiscono la gestione della periodicità.

---

### **9. Grafici qualitativi**

I grafici delle inverse si ottengono riflettendo i rami principali rispetto alla retta $y=x$.

<!-- TODO FIGURA:
Tre pannelli con i grafici di arcsin, arccos e arctan. Indicare dominio, immagine, monotonia, punti notevoli e, per arctan, le rette orizzontali y=pi/2 e y=-pi/2 come valori non raggiunti. Obiettivo didattico: leggere gli intervalli principali delle inverse.
-->

Questa interpretazione grafica chiarisce perché:

- $\arcsin$ ha valori solo tra $-\frac{\pi}{2}$ e $\frac{\pi}{2}$;
- $\arccos$ ha valori solo tra $0$ e $\pi$;
- $\arctan$ ha valori solo tra $-\frac{\pi}{2}$ e $\frac{\pi}{2}$.

<!-- TODO FIGURA:
In ciascuno di tre pannelli mostrare il ramo ristretto di seno, coseno o tangente e il grafico della rispettiva inversa, simmetrici rispetto alla retta y=x. Evidenziare gli intervalli su cui la funzione originaria è biiettiva. Obiettivo didattico: motivare le restrizioni e distinguere inversa globale da ramo inverso.
-->

### **10. Riduzione delle composizioni all'intervallo principale**

Per $\arcsin(\sin x)$ si cerca l'unico angolo in $[-\pi/2,\pi/2]$ con lo stesso seno. Riducendo prima $x$ nell'intervallo di lunghezza $2\pi$, $[-\pi/2,3\pi/2]$, si ottiene

$$
\arcsin(\sin x)=
\begin{cases}
x & -\frac{\pi}{2}\leq x\leq\frac{\pi}{2} \\
\pi-x & \frac{\pi}{2}\leq x\leq\frac{3\pi}{2}
\end{cases}
$$

dopo avere eventualmente aggiunto o sottratto multipli di $2\pi$. La formula ai bordi è coerente, perché le due espressioni coincidono. Se si preferisce ridurre in $[0,2\pi)$, per $x\in[3\pi/2,2\pi)$ occorre usare $x-2\pi$.

Per $\arccos(\cos x)$ si cerca invece il rappresentante in $[0,\pi]$. Per esempio,

$$
\arccos\left(\cos\frac{7\pi}{4}\right)=\arccos\frac{\sqrt{2}}{2}=\frac{\pi}{4}.
$$

Per $\arctan(\tan x)$ si sottrae un multiplo di $\pi$ fino a entrare in $(-\pi/2,\pi/2)$:

$$
\arctan\left(\tan\frac{5\pi}{6}\right)=-\frac{\pi}{6}.
$$

### **11. Relazioni e identità utili**

Poiché $\arcsin x\in[-\pi/2,\pi/2]$, il suo coseno è non negativo. Dall'identità fondamentale,

$$
\cos(\arcsin x)=\sqrt{1-x^2},\qquad x\in[-1,1].
$$

Analogamente,

$$
\sin(\arccos x)=\sqrt{1-x^2},\qquad x\in[-1,1].
$$

Se $t=\arctan x$, allora $t\in(-\pi/2,\pi/2)$ e $\cos t>0$. Da $1+\tan^2t=1/\cos^2t$ segue

$$
\cos(\arctan x)=\frac{1}{\sqrt{1+x^2}},
$$

$$
\sin(\arctan x)=\frac{x}{\sqrt{1+x^2}}.
$$

Inoltre,

$$
\arcsin x+\arccos x=\frac{\pi}{2},\qquad x\in[-1,1],
$$

perché i due angoli sono complementari e appartengono agli intervalli principali prescritti.

### **12. Arcocotangente: una convenzione non universale**

L'inversa della cotangente dipende dall'intervallo scelto. In questa repository, quando necessaria, si adotta

$$
\operatorname{arccot}:\mathbb{R}\to(0,\pi),
$$

come inversa della cotangente ristretta a $(0,\pi)$, dove è strettamente decrescente. Altri testi scelgono intervalli diversi: prima di usare formule con $\operatorname{arccot}$ bisogna quindi verificare la convenzione.

### **13. Errori frequenti**

> ⚠️ Le inverse restituiscono un solo valore principale. Usarle in un'equazione senza aggiungere la periodicità perde soluzioni.

Non sono identità globali

$$
\arcsin(\sin x)=x,
$$

$$
\arccos(\cos x)=x,
$$

$$
\arctan(\tan x)=x.
$$

Ognuna vale senza correzioni soltanto sull'intervallo principale della funzione corrispondente.

---

### **14. Riepilogo**

> ✅ Le funzioni goniometriche inverse sono inverse dei rami principali di seno, coseno e tangente.

Punti essenziali:

- $\arcsin:[-1,1]\to\left[-\frac{\pi}{2},\frac{\pi}{2}\right]$;
- $\arccos:[-1,1]\to[0,\pi]$;
- $\arctan:\mathbb{R}\to\left(-\frac{\pi}{2},\frac{\pi}{2}\right)$;
- $\sin(\arcsin x)=x$, ma $\arcsin(\sin x)$ non è sempre $x$;
- nelle equazioni bisogna aggiungere la periodicità.
