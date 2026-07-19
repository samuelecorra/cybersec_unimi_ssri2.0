# **M7 UD3 Lezione 3 - Calcolo del segno**

### **1. Introduzione**

Studiare il segno di una funzione significa determinare dove la funzione è positiva, negativa o nulla.

Formalmente si cercano gli insiemi:

$$
\{x\in D_f:f(x)>0\}
$$

$$
\{x\in D_f:f(x)<0\}
$$

e:

$$
\{x\in D_f:f(x)=0\}
$$

> 📌 Lo studio del segno collega algebra, dominio e grafico.

---

### **2. Metodo generale**

Per studiare il segno di $f$:

1. si determina il dominio;
2. si trovano gli zeri;
3. si individuano eventuali punti esclusi dal dominio;
4. si ordinano zeri e punti esclusi sulla retta reale;
5. si studia il segno su ogni intervallo;
6. si riportano correttamente inclusioni ed esclusioni.

---

### **3. Polinomi fattorizzati**

Consideriamo:

$$
f(x)=(x-1)(x+2)
$$

Gli zeri sono:

$$
x=-2,\quad x=1
$$

Gli intervalli sono:

$$
(-\infty,-2),\quad (-2,1),\quad (1,+\infty)
$$

Il prodotto è positivo fuori dagli zeri e negativo tra gli zeri:

$$
f(x)>0 \iff x<-2 \text{ oppure } x>1
$$

$$
f(x)<0 \iff -2<x<1
$$

---

### **4. Molteplicità degli zeri**

Se uno zero ha molteplicità pari, il segno non cambia attraversandolo.

Esempio:

$$
f(x)=(x-1)^2(x+2)
$$

Lo zero $x=1$ ha molteplicità pari. Il fattore $(x-1)^2$ è sempre non negativo e non cambia segno.

Lo zero $x=-2$ ha molteplicità dispari e fa cambiare segno.

---

### **5. Funzioni razionali**

Per:

$$
f(x)=\frac{x-1}{x+2}
$$

il dominio è:

$$
\mathbb{R}\setminus\{-2\}
$$

Lo zero è:

$$
x=1
$$

Il punto $x=-2$ non è uno zero: è un punto escluso.

Studiamo i segni su:

$$
(-\infty,-2),\quad (-2,1),\quad (1,+\infty)
$$

Si ottiene:

$$
f(x)>0 \iff x<-2 \text{ oppure } x>1
$$

e:

$$
f(x)<0 \iff -2<x<1
$$

Il punto $-2$ resta escluso.

---

### **6. Funzioni con radici**

Consideriamo:

$$
f(x)=\sqrt{x-1}(x-3)
$$

Il dominio è:

$$
[1,+\infty)
$$

La radice è sempre non negativa. Gli zeri sono:

$$
x=1,\quad x=3
$$

Per $1<x<3$, $\sqrt{x-1}>0$ e $x-3<0$, quindi:

$$
f(x)<0
$$

Per $x>3$, entrambi i fattori sono positivi, quindi:

$$
f(x)>0
$$

---

### **7. Funzioni con modulo**

Il modulo è sempre non negativo:

$$
|g(x)|\ge 0
$$

Quindi, per:

$$
f(x)=|x-2|
$$

si ha:

$$
f(x)\ge 0
$$

per ogni $x$, e:

$$
f(x)=0 \iff x=2
$$

Se compare un prodotto con moduli, bisogna ricordare che il modulo non cambia segno, ma può annullarsi.

---

### **8. Segno e grafico**

Nel grafico:

- $f(x)>0$ dove il grafico sta sopra l’asse $x$;
- $f(x)<0$ dove il grafico sta sotto l’asse $x$;
- $f(x)=0$ dove il grafico interseca o tocca l’asse $x$.

Uno zero di molteplicità pari spesso corrisponde a un punto in cui il grafico tocca l’asse senza attraversarlo.

<!-- TODO FIGURA:
Grafico di un polinomio con uno zero semplice e uno doppio, più tabella dei segni allineata sotto l'asse x. Evidenziare attraversamento, tangenza e intervalli positivi/negativi.
Obiettivo didattico: collegare fattorizzazione, molteplicità, segno e comportamento del grafico negli zeri.
-->

---

### **9. Segno di composizioni e trasformazioni**

Per $h\circ g$, si determina prima dove il valore $g(x)$ appartiene agli intervalli di positività di $h$:

$$
\{x:(h\circ g)(x)>0\}=g^{-1}(\{t:h(t)>0\}).
$$

Esempio: se $h(t)=t^2-4$ e $g(x)=x-1$, allora $h(t)>0$ per $t<-2$ oppure $t>2$. Quindi

$$
x-1<-2
\quad\text{oppure}\quad
x-1>2,
$$

ossia

$$
x<-1
\quad\text{oppure}\quad
x>3.
$$

Per $A\neq0$, il segno di $Af(x)$ coincide con quello di $f$ se $A>0$ e si inverte se $A<0$. Una traslazione verticale $f(x)+k$ richiede invece di risolvere $f(x)>-k$.

### **10. Problema trasversale d'esame**

Considera

$$
f(x)=\sqrt{x+1}-1
$$

e la funzione dichiarata

$$
f:[-1,+\infty)\to[-1,+\infty).
$$

1. Determina il dominio naturale e confrontalo con quello dichiarato.
2. Distingui codominio e immagine.
3. Calcola l'immagine senza derivate.
4. Studia zeri e segno.
5. Classifica monotonia, parità e periodicità.
6. Verifica l'iniettività e la suriettività sul codominio dichiarato.
7. Scegli, se necessario, una restrizione o una modifica del codominio che renda la funzione biiettiva.
8. Costruisci l'inversa e verifica entrambe le composizioni.
9. Descrivi il grafico come trasformazione di $y=\sqrt{x}$.
10. Indica quali ulteriori proprietà richiederebbero limiti, continuità o derivate.

#### **10.1 Soluzione guidata**

La radice richiede $x+1\ge0$, quindi il dominio naturale coincide con $[-1,+\infty)$. Ponendo $t=\sqrt{x+1}\ge0$ si ha $f(x)=t-1$, perciò

$$
\operatorname{Im}(f)=[-1,+\infty),
$$

uguale al codominio: $f$ è suriettiva. Lo zero è

$$
\sqrt{x+1}=1
\iff x=0.
$$

Pertanto $f(x)<0$ per $-1\le x<0$ e $f(x)>0$ per $x>0$.

Se $x_1<x_2$, allora $x_1+1<x_2+1$ e la radice quadrata conserva strettamente l'ordine; dunque $f$ è strettamente crescente e quindi iniettiva. Non è pari né dispari perché il dominio non è simmetrico, e non è periodica perché è strettamente crescente. È già biiettiva sul dominio e codominio dichiarati.

Da

$$
y=\sqrt{x+1}-1
$$

segue

$$
y+1=\sqrt{x+1},
$$

con $y\ge-1$, e quindi

$$
x=(y+1)^2-1.
$$

L'inversa è

$$
f^{-1}(y)=(y+1)^2-1,
\qquad y\in[-1,+\infty).
$$

Le verifiche sono

$$
f^{-1}(f(x))=(\sqrt{x+1})^2-1=x
$$

per $x\ge-1$, e

$$
f(f^{-1}(y))=\sqrt{(y+1)^2}-1=|y+1|-1=y
$$

perché $y\ge-1$. Il grafico di $\sqrt{x}$ è traslato di $1$ a sinistra e di $1$ verso il basso. Limiti agli estremi del dominio, continuità formale, pendenze, concavità e tangenti sono rinviati a M08-M10.

> ⚠️ Nell'ultima composizione la rimozione della radice produce $|y+1|$, non automaticamente $y+1$; il dominio dell'inversa rende lecita la semplificazione finale.

### **11. Altri esercizi integrati**

1. Studia dominio, immagine, zeri, segno e iniettività di $(x-1)/(x+1)$ su ciascuna componente del dominio.
2. Per $f_a(x)=x^2-2ax+a^2-4$, determina zeri, segno, minimo e una restrizione invertibile.
3. Confronta $f(x)=|x^2-1|$ e $g(x)=x^2-1$ rispetto a immagine, zeri, parità e iniettività.
4. Determina il segno di $\ln(x^2-1)$ rispettando prima il dominio.
5. Studia il segno di $\sin x-1/2$ su un periodo e propaga il risultato per periodicità.
6. Costruisci una funzione a tratti che sia iniettiva, limitata e priva di massimo.
7. Data $h(x)=2f(3(x-1))-4$, esprimi zeri e intervalli di positività di $h$ in termini dei livelli di $f$.

---

### **12. Riepilogo**

> ✅ Il segno di una funzione si studia combinando dominio, zeri e fattori.

Punti essenziali:

- prima si determina il dominio;
- poi si trovano zeri e punti esclusi;
- i punti critici dividono la retta in intervalli;
- negli zeri di molteplicità dispari il segno cambia;
- negli zeri di molteplicità pari il segno non cambia;
- i punti esclusi non vanno mai inclusi nelle soluzioni.
