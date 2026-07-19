# **M1 UD6 Lezione 3 - Simmetrie**

### **1. Introduzione**

Le simmetrie goniometriche permettono di ricondurre il calcolo di seno, coseno e tangente a pochi angoli fondamentali.

Invece di memorizzare valori in tutti i quadranti, si usa un metodo:

1. si identifica l’angolo di riferimento;
2. si determina il quadrante;
3. si assegna il segno corretto;
4. si usa il valore noto nel primo quadrante.

> 📌 Le simmetrie servono a ridurre angoli complicati ad angoli notevoli.

---

### **2. Parità e disparità**

Il coseno è una funzione pari:

$$
\cos(-x)=\cos x
$$

Il seno è una funzione dispari:

$$
\sin(-x)=-\sin x
$$

La tangente è dispari:

$$
\tan(-x)=-\tan x
$$

Queste identità si leggono direttamente sulla circonferenza goniometrica: cambiare $x$ in $-x$ riflette il punto rispetto all’asse delle ascisse.

---

### **3. Angoli supplementari**

Gli angoli $x$ e $\pi-x$ sono supplementari.

Sulla circonferenza goniometrica i punti corrispondenti hanno:

- stessa ordinata;
- ascissa opposta.

Quindi:

$$
\sin(\pi-x)=\sin x
$$

e:

$$
\cos(\pi-x)=-\cos x
$$

Per la tangente:

$$
\tan(\pi-x)=-\tan x
$$

Esempio:

$$
\sin\frac{5\pi}{6}=\sin\left(\pi-\frac{\pi}{6}\right)=\sin\frac{\pi}{6}=\frac{1}{2}
$$

$$
\cos\frac{5\pi}{6}=-\cos\frac{\pi}{6}=-\frac{\sqrt{3}}{2}
$$

---

### **4. Angoli che differiscono di $\pi$**

Gli angoli $x$ e $x+\pi$ individuano punti opposti sulla circonferenza.

Quindi:

$$
\sin(x+\pi)=-\sin x
$$

$$
\cos(x+\pi)=-\cos x
$$

Per la tangente:

$$
\tan(x+\pi)=\tan x
$$

perché il rapporto tra seno e coseno mantiene lo stesso segno.

Esempio:

$$
\sin\frac{7\pi}{6}=\sin\left(\pi+\frac{\pi}{6}\right)=-\frac{1}{2}
$$

$$
\cos\frac{7\pi}{6}=-\frac{\sqrt{3}}{2}
$$

---

### **5. Angoli esplementari**

Gli angoli $x$ e $2\pi-x$ sono simmetrici rispetto all’asse delle ascisse.

Quindi:

$$
\sin(2\pi-x)=-\sin x
$$

e:

$$
\cos(2\pi-x)=\cos x
$$

Per la tangente:

$$
\tan(2\pi-x)=-\tan x
$$

Esempio:

$$
\sin\frac{11\pi}{6}=\sin\left(2\pi-\frac{\pi}{6}\right)=-\frac{1}{2}
$$

$$
\cos\frac{11\pi}{6}=\frac{\sqrt{3}}{2}
$$

---

### **6. Angoli complementari**

Gli angoli $x$ e $\frac{\pi}{2}-x$ sono complementari.

Le coordinate si scambiano:

$$
\sin\left(\frac{\pi}{2}-x\right)=\cos x
$$

e:

$$
\cos\left(\frac{\pi}{2}-x\right)=\sin x
$$

Queste formule sono utili per trasformare seno in coseno e viceversa.

Esempio:

$$
\sin\frac{\pi}{3}=\sin\left(\frac{\pi}{2}-\frac{\pi}{6}\right)=\cos\frac{\pi}{6}=\frac{\sqrt{3}}{2}
$$

---

### **7. Traslazioni di $\frac{\pi}{2}$**

Le formule con $\frac{\pi}{2}$ sono:

$$
\sin\left(x+\frac{\pi}{2}\right)=\cos x
$$

$$
\cos\left(x+\frac{\pi}{2}\right)=-\sin x
$$

$$
\sin\left(x-\frac{\pi}{2}\right)=-\cos x
$$

$$
\cos\left(x-\frac{\pi}{2}\right)=\sin x
$$

Sono formule di sfasamento: spostano il grafico lungo l’asse $x$.

Per tangente e cotangente, quando i membri sono definiti,

$$
\tan\left(\frac{\pi}{2}-x\right)=\cot x,
$$

$$
\tan\left(\frac{\pi}{2}+x\right)=-\cot x.
$$

Le formule con $\pi/2$ scambiano seno e coseno, oppure tangente e cotangente. Il segno va poi determinato dal quadrante.

---

### **8. Metodo pratico per un valore trigonometrico**

Calcoliamo:

$$
\cos\frac{4\pi}{3}
$$

L’angolo è:

$$
\frac{4\pi}{3}=\pi+\frac{\pi}{3}
$$

È nel terzo quadrante, dove il coseno è negativo. L’angolo di riferimento è $\frac{\pi}{3}$.

Quindi:

$$
\cos\frac{4\pi}{3}=-\cos\frac{\pi}{3}=-\frac{1}{2}
$$

Calcoliamo anche:

$$
\sin\frac{4\pi}{3}
$$

Nel terzo quadrante il seno è negativo:

$$
\sin\frac{4\pi}{3}=-\sin\frac{\pi}{3}=-\frac{\sqrt{3}}{2}
$$

---

### **9. Riduzione modulo $2\pi$**

Per seno e coseno si può sempre sottrarre o aggiungere un multiplo di $2\pi$:

$$
\sin(x+2k\pi)=\sin x
$$

$$
\cos(x+2k\pi)=\cos x
$$

Per esempio:

$$
\sin\frac{17\pi}{6}
$$

Poiché:

$$
\frac{17\pi}{6}-2\pi=\frac{17\pi}{6}-\frac{12\pi}{6}=\frac{5\pi}{6}
$$

si ha:

$$
\sin\frac{17\pi}{6}=\sin\frac{5\pi}{6}=\frac{1}{2}
$$

### **10. Metodo generale dell'angolo di riferimento**

Per calcolare una funzione trigonometrica di un angolo qualsiasi:

1. si riduce l'angolo in $[0,2\pi)$ aggiungendo o sottraendo multipli di $2\pi$;
2. si identifica il quadrante;
3. si trova l'angolo di riferimento $\alpha\in[0,\pi/2]$;
4. si usa il valore della funzione in $\alpha$;
5. si assegna il segno imposto dal quadrante.

Per esempio,

$$
\tan\left(-\frac{19\pi}{6}\right).
$$

Riducendo modulo $2\pi$,

$$
-\frac{19\pi}{6}+4\pi=\frac{5\pi}{6}.
$$

L'angolo è nel secondo quadrante, ha riferimento $\pi/6$ e la tangente è negativa:

$$
\tan\left(-\frac{19\pi}{6}\right)=-\tan\frac{\pi}{6}=-\frac{\sqrt{3}}{3}.
$$

<!-- TODO FIGURA:
Circonferenza goniometrica con un angolo di riferimento alpha ripetuto nei quattro quadranti. Collegare con segmenti o archi i punti corrispondenti e indicare i segni di seno, coseno e tangente. Obiettivo didattico: applicare un solo metodo a -theta, pi-theta, pi+theta e 2pi-theta.
-->

### **11. Formulario ricostruibile, non da memorizzare**

Le formule associate si possono ricostruire osservando le coordinate:

- la riflessione rispetto all'asse $x$ conserva l'ascissa e cambia il segno dell'ordinata;
- la riflessione rispetto all'asse $y$ cambia il segno dell'ascissa e conserva l'ordinata;
- la simmetria rispetto all'origine cambia entrambi i segni;
- una rotazione di $\pi/2$ scambia il ruolo delle coordinate, con il segno imposto dal quadrante.

Questo criterio è più affidabile di una lista mnemonica, soprattutto per angoli non notevoli.

---

### **12. Errori tipici**

#### **12.1 Confondere segno e valore assoluto**

Nel secondo quadrante:

$$
\sin x>0,\quad \cos x<0
$$

Quindi per un angolo con riferimento $\frac{\pi}{6}$, il seno resta $\frac{1}{2}$ ma il coseno diventa $-\frac{\sqrt{3}}{2}$.

#### **12.2 Usare sempre il periodo $2\pi$**

Per la tangente si può ridurre modulo $\pi$:

$$
\tan(x+k\pi)=\tan x
$$

Non serve aspettare un giro completo.

---

### **13. Riepilogo**

> ✅ Le simmetrie goniometriche permettono di calcolare valori e trasformare espressioni senza ricorrere a memorizzazioni meccaniche.

Identità fondamentali:

$$
\sin(-x)=-\sin x,\quad \cos(-x)=\cos x
$$

$$
\sin(\pi-x)=\sin x,\quad \cos(\pi-x)=-\cos x
$$

$$
\sin(x+\pi)=-\sin x,\quad \cos(x+\pi)=-\cos x
$$

$$
\sin(2\pi-x)=-\sin x,\quad \cos(2\pi-x)=\cos x
$$

Queste formule saranno usate sistematicamente nelle equazioni e disequazioni goniometriche.
