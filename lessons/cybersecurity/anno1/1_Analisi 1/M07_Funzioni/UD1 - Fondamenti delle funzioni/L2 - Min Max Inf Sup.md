# **M7 UD1 Lezione 2 - Min Max Inf Sup**

### **1. Introduzione**

Per una funzione reale, minimo, massimo, infimo e supremo descrivono i valori estremi che la funzione assume, o a cui si avvicina.

Non si studiano più solo insiemi numerici astratti, ma l’insieme dei valori:

$$
f(D_f)
$$

cioè l’immagine della funzione.

> 📌 Gli estremi di una funzione sono estremi dell’insieme dei suoi valori.

---

### **2. Massimo assoluto**

Sia:

$$
f:D\to\mathbb{R}
$$

Un punto $x_M\in D$ è punto di **massimo assoluto** se:

$$
f(x)\le f(x_M)
$$

per ogni $x\in D$.

Il valore:

$$
f(x_M)
$$

si chiama massimo della funzione.

---

### **3. Minimo assoluto**

Un punto $x_m\in D$ è punto di **minimo assoluto** se:

$$
f(x_m)\le f(x)
$$

per ogni $x\in D$.

Il valore:

$$
f(x_m)
$$

si chiama minimo della funzione.

> ⚠️ Il punto di massimo è $x_M$; il massimo è il valore $f(x_M)$.

---

### **4. Esempio**

Consideriamo:

$$
f(x)=x^2
$$

con dominio:

$$
\mathbb{R}
$$

Poiché:

$$
x^2\ge 0
$$

per ogni $x$, e:

$$
f(0)=0
$$

la funzione ha minimo assoluto:

$$
\min f=0
$$

raggiunto in:

$$
x=0
$$

Non ha massimo assoluto, perché per ogni $M\in\mathbb{R}$ si può scegliere $x$ con $x^2>M$. Per esempio, se $M\ge0$ basta porre:

$$
x=\sqrt{M+1}.
$$

Allora $f(x)=M+1>M$. Questa è una prova di illimitatezza e non usa ancora i limiti.

---

### **5. Infimo e supremo della funzione**

L’infimo della funzione è:

$$
\inf f(D)
$$

Il supremo della funzione è:

$$
\sup f(D)
$$

Se il supremo appartiene all’immagine, allora è anche massimo. Se l’infimo appartiene all’immagine, allora è anche minimo.

Esempio:

$$
f(x)=x
$$

su:

$$
D=(0,1)
$$

ha:

$$
\inf f(D)=0,\quad \sup f(D)=1
$$

ma non ha né minimo né massimo, perché $0$ e $1$ non appartengono al dominio e quindi non sono valori assunti.

---

### **6. Estremi locali**

Un punto $x_0\in D$ è punto di **massimo locale** se esiste un intorno di $x_0$ in cui:

$$
f(x)\le f(x_0)
$$

per tutti gli $x$ del dominio vicini a $x_0$. Formalmente, esiste $\delta>0$ tale che

$$
f(x)\le f(x_0)
$$

per ogni

$$
x\in D\cap(x_0-\delta,x_0+\delta).
$$

Analogamente, $x_0$ è punto di **minimo locale** se localmente:

$$
f(x_0)\le f(x)
$$

Gli estremi locali descrivono picchi o valli rispetto ai punti vicini, non necessariamente rispetto a tutto il dominio.

Esempio: per $f(x)=x^3-3x$ sul dominio $[-2,3]$, il valore $f(-1)=2$ supera quello dei punti sufficientemente vicini, ma non è il massimo assoluto perché $f(3)=18$. Il confronto locale in $-1$ non dipende dai valori lontani.

<!-- TODO FIGURA:
Grafico qualitativo di una funzione con un massimo locale, un minimo locale e un massimo globale distinto. Indicare ascisse dei punti e valori estremi con linee tratteggiate verso gli assi.
Obiettivo didattico: distinguere punto di estremo, valore estremo e carattere locale o globale.
-->

---

### **7. Estremi e intervalli**

Una funzione continua su un intervallo chiuso e limitato:

$$
[a,b]
$$

assume massimo e minimo assoluti.

Questo risultato, chiamato teorema di Weierstrass, sarà formalizzato più avanti. Qui è importante coglierne il senso: chiusura, limitatezza e continuità impediscono alla funzione di “scappare” o di avvicinarsi a un estremo senza raggiungerlo.

Non va usato prima di M08 come giustificazione automatica. Le tre ipotesi sono essenziali: $f(x)=1/x$ su $(0,1]$ non è limitata superiormente; $f(x)=x$ su $(0,1)$ è limitata ma non raggiunge infimo e supremo.

### **8. Unicità dei valori estremi**

Se esiste un massimo, il suo **valore** è unico, anche se può essere raggiunto in più punti. Infatti, se $M_1$ e $M_2$ sono entrambi massimi dell'immagine, allora $M_1\le M_2$ e $M_2\le M_1$, dunque $M_1=M_2$. Lo stesso argomento vale per il minimo, il supremo e l'infimo.

La funzione costante $f(x)=3$ su $\mathbb{R}$ raggiunge lo stesso massimo e minimo $3$ in ogni punto del dominio.

### **9. Esercizi**

1. Determina immagine, supremo, infimo, massimo e minimo di $f(x)=x^2$ sui domini $(-2,1)$, $[-2,1]$ e $\{-2\}\cup(0,1)$.
2. Dimostra che $f(x)=x/(1+|x|)$ è limitata tra $-1$ e $1$ e stabilisci se raggiunge gli estremi.
3. Costruisci una funzione con due punti di massimo assoluto e un solo punto di minimo assoluto.
4. Fornisci un esempio di funzione con massimo locale ma nessun massimo assoluto.
5. Per $f_a(x)=(x-a)^2+1$ determina minimo, punti di minimo e limitatezza al variare di $a$.
6. Spiega perché il dominio limitato non implica la limitatezza della funzione.

---

### **10. Riepilogo**

> ✅ Massimo e minimo devono essere valori effettivamente assunti; supremo e infimo possono essere solo valori limite dell’immagine.

Punti essenziali:

- massimo assoluto: $f(x)\le f(x_M)$ per ogni $x$;
- minimo assoluto: $f(x_m)\le f(x)$ per ogni $x$;
- supremo e infimo riguardano l’immagine;
- massimo e minimo sono supremo e infimo raggiunti;
- gli estremi locali valgono solo in un intorno del punto.
