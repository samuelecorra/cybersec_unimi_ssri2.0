# **M11 Lezione 14 - Volumi dei solidi di rotazione**

### **1. Dal grafico al solido**

Consideriamo una funzione $y=f(x)$ definita su un intervallo $[a,b]$. Se facciamo ruotare attorno all'asse $x$ la regione compresa tra il grafico di $f$, l'asse delle ascisse e le rette verticali $x=a$ e $x=b$, otteniamo un **solido di rotazione**.

L'idea è simile a quella con cui l'integrale definito calcola un'area come limite di somme di rettangoli. Qui però le sezioni perpendicolari all'asse $x$ non sono rettangoli, ma dischi.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Se fissiamo un punto $x$, il raggio del disco generato dalla rotazione è

$$
r=f(x),
$$

almeno quando $f(x)\ge 0$. L'area della sezione circolare è quindi

$$
\pi r^2=\pi f(x)^2.
$$

### **2. Approssimazione con cilindri**

Dividiamo l'intervallo $[a,b]$ in tanti sottointervalli di ampiezza $\Delta x$. In ciascun sottointervallo scegliamo un punto $x_i$ e approssimiamo il pezzo di solido con un cilindro di raggio $f(x_i)$ e altezza $\Delta x$.

Il volume del cilindro è

$$
\pi f(x_i)^2\Delta x.
$$

Sommando tutti i cilindri si ottiene una stima del volume:

$$
\widetilde{V}=\sum_{i=1}^{n}\pi f(x_i)^2\Delta x.
$$

Questa stima è tanto migliore quanto più sottile è la suddivisione dell'intervallo. Facendo tendere a zero l'ampiezza dei sottointervalli, la somma diventa un integrale.

### **3. Formula del metodo dei dischi**

Il volume del solido generato dalla rotazione di $f$ attorno all'asse $x$ nell'intervallo $[a,b]$ è

$$
V=\pi\int_a^b f(x)^2\,dx.
$$

La formula si chiama **metodo dei dischi**, perché ogni sezione perpendicolare all'asse di rotazione è un disco pieno.

> 📌 La funzione viene elevata al quadrato perché il volume dipende dall'area del disco, e l'area di un cerchio è $\pi r^2$.

Anche se $f(x)$ assume valori negativi, il raggio geometrico è $|f(x)|$; nella formula compare comunque $f(x)^2$, quindi il segno non cambia il volume.

---
### **4. Esempio 1: rotazione di $y=\sqrt{x}$ su $[0,4]$**

Calcoliamo il volume del solido ottenuto dalla rotazione attorno all'asse $x$ della curva

$$
y=\sqrt{x}
$$

nell'intervallo

$$
[0,4].
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Applicando il metodo dei dischi:

$$
V=\pi\int_0^4(\sqrt{x})^2\,dx.
$$

Poiché

$$
(\sqrt{x})^2=x,
$$

otteniamo:

$$
V=\pi\int_0^4 x\,dx.
$$

Una primitiva di $x$ è

$$
\frac{x^2}{2}.
$$

Quindi:

$$
V=\pi\left[\frac{x^2}{2}\right]_0^4.
$$

Valutiamo:

$$
V=\pi\left(\frac{4^2}{2}-\frac{0^2}{2}\right)
=\pi\cdot 8
=8\pi.
$$

#### **4.1 Risultato**

$$
V=8\pi.
$$

---
### **5. Esempio 2: volume della sfera**

Dimostriamo che il volume di una sfera di raggio $R$ è

$$
V=\frac{4}{3}\pi R^3.
$$

Una sfera di raggio $R$ si ottiene facendo ruotare attorno all'asse $x$ la semicirconferenza superiore

$$
y=\sqrt{R^2-x^2}
$$

nell'intervallo

$$
[-R,R].
$$

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Applicando il metodo dei dischi:

$$
V=\pi\int_{-R}^{R}\left(\sqrt{R^2-x^2}\right)^2\,dx.
$$

Semplificando il quadrato:

$$
V=\pi\int_{-R}^{R}(R^2-x^2)\,dx.
$$

Una primitiva è

$$
R^2x-\frac{x^3}{3}.
$$

Quindi:

$$
V=\pi\left[R^2x-\frac{x^3}{3}\right]_{-R}^{R}.
$$

Valutiamo in $R$:

$$
R^2\cdot R-\frac{R^3}{3}
=R^3-\frac{R^3}{3}
=\frac{2}{3}R^3.
$$

Valutiamo in $-R$:

$$
R^2(-R)-\frac{(-R)^3}{3}
=-R^3+\frac{R^3}{3}
=-\frac{2}{3}R^3.
$$

Sottraendo:

$$
\frac{2}{3}R^3-\left(-\frac{2}{3}R^3\right)
=\frac{4}{3}R^3.
$$

Moltiplicando per $\pi$:

$$
V=\frac{4}{3}\pi R^3.
$$

#### **5.1 Risultato**

La formula del volume della sfera è

$$
V=\frac{4}{3}\pi R^3.
$$

---
### **6. Rotazione della regione compresa tra due curve**

Supponiamo ora di avere due funzioni $f$ e $g$ e di far ruotare attorno all'asse $x$ la regione compresa tra i loro grafici nell'intervallo $[a,b]$.

Se, su tutto l'intervallo, il grafico di $f$ sta sopra quello di $g$ e le due curve sono sopra l'asse $x$, la sezione del solido non è più un disco pieno, ma una **rondella**: un disco grande da cui viene tolto un disco più piccolo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il raggio esterno è

$$
R_{\text{est}}=f(x),
$$

mentre il raggio interno è

$$
R_{\text{int}}=g(x).
$$

L'area della sezione è:

$$
\pi R_{\text{est}}^2-\pi R_{\text{int}}^2.
$$

Quindi:

$$
V=\pi\int_a^b\left[f(x)^2-g(x)^2\right]\,dx.
$$

### **7. Osservazioni importanti**

La formula va letta come

$$
V=\pi\int_a^b\left[(\text{raggio esterno})^2-(\text{raggio interno})^2\right]\,dx.
$$

Negli screenshot questo viene riassunto come "quella sopra al quadrato meno quella sotto al quadrato". Questa frase funziona quando entrambe le curve stanno dalla stessa parte dell'asse di rotazione e quella sopra è anche più lontana dall'asse $x$.

Bisogna invece evitare l'errore:

$$
\pi\int_a^b [f(x)-g(x)]^2\,dx.
$$

Questa formula è sbagliata per le rondelle: la differenza va fatta tra le aree dei dischi, non tra i raggi prima di elevare al quadrato.

> ⚠️ Errore tipico: usare $\pi\int_a^b [f(x)-g(x)]^2\,dx$. Il volume corretto è $\pi\int_a^b\left[R_{\text{est}}(x)^2-R_{\text{int}}(x)^2\right]\,dx$.

### **8. Esempio guidato tra due curve**

Supponiamo che una regione sia delimitata, su $[a,b]$, da una curva superiore $y=f(x)$ e da una curva inferiore $y=g(x)$, entrambe non negative, e che venga ruotata attorno all'asse $x$.

Il volume si calcola come:

$$
V=\pi\int_a^b\left[f(x)^2-g(x)^2\right]\,dx.
$$

Se ad esempio

$$
f(x)=2,
\qquad
g(x)=x,
\qquad
0\le x\le 1,
$$

allora:

$$
V=\pi\int_0^1(2^2-x^2)\,dx
=\pi\int_0^1(4-x^2)\,dx.
$$

Calcoliamo:

$$
V=\pi\left[4x-\frac{x^3}{3}\right]_0^1
=\pi\left(4-\frac{1}{3}\right)
=\frac{11}{3}\pi.
$$

### **9. Riepilogo**

Per i solidi di rotazione attorno all'asse $x$:

- se ruota una regione tra una curva e l'asse $x$, si usa il metodo dei dischi:

$$
V=\pi\int_a^b f(x)^2\,dx;
$$

- se ruota una regione tra due curve, si usa il metodo delle rondelle:

$$
V=\pi\int_a^b\left[R_{\text{est}}(x)^2-R_{\text{int}}(x)^2\right]\,dx.
$$

Il passaggio decisivo è capire qual è il raggio della sezione circolare in ogni punto: nei dischi c'è un solo raggio, nelle rondelle ci sono un raggio esterno e un raggio interno.
