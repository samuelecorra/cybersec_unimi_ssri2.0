# **M7 UD2 Lezione 3 - Funzioni trigonometriche**

### **1. Introduzione**

Le funzioni trigonometriche hanno domini diversi a seconda che siano funzioni fondamentali, come seno e coseno, oppure rapporti, come tangente e cotangente.

Per determinare il dominio bisogna ricordare dove le funzioni sono definite e dove eventuali denominatori si annullano.

> 📌 Seno e coseno sono definiti ovunque; tangente e cotangente hanno punti esclusi.

---

### **2. Seno e coseno**

Le funzioni:

$$
\sin x
$$

e:

$$
\cos x
$$

sono definite per ogni numero reale.

Quindi:

$$
D_{\sin}=\mathbb{R}
$$

e:

$$
D_{\cos}=\mathbb{R}
$$

Esempio:

$$
f(x)=\sin(x^2+1)
$$

ha dominio:

$$
\mathbb{R}
$$

perché $x^2+1$ è sempre definito e il seno accetta ogni input reale.

---

### **3. Tangente**

La tangente è:

$$
\tan x=\frac{\sin x}{\cos x}
$$

Quindi è definita quando:

$$
\cos x\neq 0
$$

Gli zeri del coseno sono:

$$
x=\frac{\pi}{2}+k\pi,\quad k\in\mathbb{Z}
$$

Perciò:

$$
D_{\tan}=\mathbb{R}\setminus\left\{\frac{\pi}{2}+k\pi:k\in\mathbb{Z}\right\}
$$

---

### **4. Cotangente**

La cotangente è:

$$
\cot x=\frac{\cos x}{\sin x}
$$

Quindi è definita quando:

$$
\sin x\neq 0
$$

Gli zeri del seno sono:

$$
x=k\pi,\quad k\in\mathbb{Z}
$$

Quindi:

$$
D_{\cot}=\mathbb{R}\setminus\{k\pi:k\in\mathbb{Z}\}
$$

---

### **5. Secante e cosecante**

La secante è:

$$
\sec x=\frac{1}{\cos x}
$$

quindi richiede:

$$
\cos x\neq 0
$$

La cosecante è:

$$
\csc x=\frac{1}{\sin x}
$$

quindi richiede:

$$
\sin x\neq 0
$$

---

### **6. Composizioni con tangente**

Consideriamo:

$$
f(x)=\tan(2x)
$$

Serve:

$$
\cos(2x)\neq 0
$$

Quindi:

$$
2x\neq \frac{\pi}{2}+k\pi
$$

Dividendo per $2$:

$$
x\neq \frac{\pi}{4}+\frac{k\pi}{2}
$$

Il dominio è:

$$
\mathbb{R}\setminus\left\{\frac{\pi}{4}+\frac{k\pi}{2}:k\in\mathbb{Z}\right\}
$$

---

### **7. Funzioni inverse trigonometriche**

Le inverse principali hanno domini:

$$
D_{\arcsin}=[-1,1]
$$

$$
D_{\arccos}=[-1,1]
$$

$$
D_{\arctan}=\mathbb{R}
$$

Esempio:

$$
f(x)=\arcsin(2x-1)
$$

richiede:

$$
-1\le 2x-1\le 1
$$

Da cui:

$$
0\le x\le 1
$$

Le immagini dei rami principali sono:

$$
\operatorname{Im}(\arcsin)=\left[-\frac\pi2,\frac\pi2\right],
$$

$$
\operatorname{Im}(\arccos)=[0,\pi],
$$

$$
\operatorname{Im}(\arctan)=\left(-\frac\pi2,\frac\pi2\right).
$$

Le restrizioni dei domini di seno, coseno e tangente sono ciò che rende queste inverse funzioni, non relazioni multivalore.

### **8. Immagini e trasformazioni**

Seno e coseno hanno immagine $[-1,1]$. Quindi

$$
f(x)=A\sin(Bx+h)+k
$$

con $A\neq0$ ha immagine

$$
[k-|A|,k+|A|]
$$

e un periodo $2\pi/|B|$ per $B\neq0$.

Tangente e cotangente hanno immagine $\mathbb{R}$ sui loro domini. Secante e cosecante hanno immagine

$$
(-\infty,-1]\cup[1,+\infty).
$$

### **9. Composizioni inverse: attenzione ai rami**

Per ogni $x\in[-1,1]$,

$$
\sin(\arcsin x)=x.
$$

Invece

$$
\arcsin(\sin x)=x
$$

solo per

$$
x\in\left[-\frac\pi2,\frac\pi2\right].
$$

Fuori dal ramo principale, il risultato è l'unico angolo nello stesso intervallo con il medesimo seno.

<!-- TODO FIGURA:
Grafico del seno con il ramo [-pi/2,pi/2] evidenziato, riflessione rispetto a y=x che produce arcsin, e linee tratteggiate sui domini e immagini scambiati.
Obiettivo didattico: mostrare come la restrizione del seno renda possibile una funzione inversa univoca.
-->

### **10. Esercizi**

1. Determina il dominio di $1/\sqrt{\cos x}$.
2. Determina il dominio di $\arccos(2\sin x)$.
3. Calcola dominio, immagine e periodo di $3\cos(2x-\pi)-1$.
4. Determina il dominio di $\tan(\sqrt{x})$.
5. Risolvi $\arcsin(\sin x)$ per $x=5\pi/6$, $-3\pi/4$ e $7\pi/3$.
6. Spiega perché $\tan$ è invertibile su $(-\pi/2,\pi/2)$ ma non sul suo intero dominio.
7. Determina l'immagine di $1/(2+\sin x)$ senza usare derivate.

Definizioni, identità e inverse goniometriche sono richiamate da [M01 UD6 L2](../../M01_Matematica Liceale/UD6 - Trigonometria/L2 - Funzioni goniometriche.md) e [M01 UD6 L4](../../M01_Matematica Liceale/UD6 - Trigonometria/L4 - Funzioni goniometriche inverse.md). M07 ne sistematizza dominio, immagine, periodicità e invertibilità.

---

### **11. Riepilogo**

> ✅ Il dominio delle funzioni trigonometriche si determina ricordando quali funzioni sono rapporti e quali inverse hanno argomento limitato.

Punti essenziali:

- seno e coseno: dominio $\mathbb{R}$;
- tangente: esclude $\frac{\pi}{2}+k\pi$;
- cotangente: esclude $k\pi$;
- $\arcsin$ e $\arccos$ richiedono argomento in $[-1,1]$;
- nelle composizioni si impone la condizione sull’argomento interno.
- le inverse principali dipendono dalla scelta di rami iniettivi;
- dominio e immagine si scambiano tra una biiezione e la sua inversa.
