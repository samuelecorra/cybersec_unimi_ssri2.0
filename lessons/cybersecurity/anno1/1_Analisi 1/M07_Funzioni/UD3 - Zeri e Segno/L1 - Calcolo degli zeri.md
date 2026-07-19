# **M7 UD3 Lezione 1 - Calcolo degli zeri**

### **1. Introduzione**

Gli zeri di una funzione sono i punti in cui il grafico incontra l’asse $x$.

Algebricamente, sono le soluzioni dell’equazione:

$$
f(x)=0
$$

> 📌 Calcolare gli zeri significa risolvere l’equazione associata alla funzione.

---

### **2. Definizione**

Sia:

$$
f:D\to\mathbb{R}
$$

Un punto $x_0\in D$ è uno **zero** di $f$ se:

$$
f(x_0)=0
$$

L’insieme degli zeri è:

$$
Z_f=\{x\in D:f(x)=0\}
$$

---

### **3. Zeri di funzioni polinomiali**

Per una funzione polinomiale:

$$
f(x)=P(x)
$$

gli zeri sono le radici del polinomio.

Esempio:

$$
f(x)=x^2-5x+6
$$

Risolviamo:

$$
x^2-5x+6=0
$$

Fattorizziamo:

$$
(x-2)(x-3)=0
$$

Quindi:

$$
Z_f=\{2,3\}
$$

---

### **4. Zeri di funzioni razionali**

Per:

$$
f(x)=\frac{P(x)}{Q(x)}
$$

gli zeri sono dati da:

$$
P(x)=0
$$

con la condizione:

$$
Q(x)\neq 0
$$

> ⚠️ Uno zero del numeratore non è uno zero della funzione se annulla anche il denominatore.

---

### **5. Esempio razionale**

Consideriamo:

$$
f(x)=\frac{x^2-1}{x-1}
$$

Il dominio è:

$$
\mathbb{R}\setminus\{1\}
$$

Il numeratore si annulla per:

$$
x=-1,\quad x=1
$$

Ma $x=1$ non appartiene al dominio. Quindi l’unico zero è:

$$
Z_f=\{-1\}
$$

---

### **6. Zeri e composizione**

Per una funzione composta:

$$
f(x)=h(g(x))
$$

gli zeri si trovano risolvendo:

$$
h(g(x))=0
$$

Spesso si pone prima:

$$
g(x)=t
$$

e si risolve:

$$
h(t)=0
$$

poi si torna a $x$.

Più precisamente, se

$$
Z_h=\{t\in D_h:h(t)=0\},
$$

allora

$$
Z_{h\circ g}=g^{-1}(Z_h),
$$

dove si considera il dominio corretto della composizione.

---

### **7. Esempio con esponenziale**

Consideriamo:

$$
f(x)=e^x-3
$$

Gli zeri soddisfano:

$$
e^x-3=0
$$

quindi:

$$
e^x=3
$$

Passando al logaritmo:

$$
x=\ln 3
$$

Quindi:

$$
Z_f=\{\ln 3\}
$$

---

### **8. Funzioni senza zeri**

Non tutte le funzioni hanno zeri.

Esempio:

$$
f(x)=x^2+1
$$

Poiché:

$$
x^2+1>0
$$

per ogni $x\in\mathbb{R}$, non esistono zeri reali:

$$
Z_f=\varnothing
$$

L'insieme vuoto è un esito perfettamente valido. Non si deve introdurre uno zero solo perché una manipolazione produce un valore fuori dal dominio.

### **9. Equazioni e intersezioni di grafici**

Risolvere

$$
f(x)=g(x)
$$

equivale a cercare gli zeri di $h=f-g$ sul dominio

$$
D_h=D_f\cap D_g.
$$

Geometricamente le soluzioni sono le ascisse delle intersezioni dei grafici di $f$ e $g$. Per esempio,

$$
x^2=x+2
$$

equivale a

$$
x^2-x-2=0,
$$

quindi le intersezioni hanno ascisse $-1$ e $2$ e punti

$$
(-1,1),
\qquad
(2,4).
$$

Una disequazione $f(x)>g(x)$ individua invece le ascisse in cui il grafico di $f$ è sopra quello di $g$.

### **10. Molteplicità e attraversamento**

Per un polinomio fattorizzato, uno zero di molteplicità dispari cambia il segno del fattore; uno di molteplicità pari no. Il fatto geometrico che il grafico attraversi o tocchi l'asse può essere dedotto algebricamente dal segno locale, senza usare derivate.

La regola non si trasferisce automaticamente a funzioni arbitrarie: la nozione di molteplicità richiede una fattorizzazione adeguata o strumenti successivi.

### **11. Esercizi**

1. Trova gli zeri di $(x^2-4)/(x-2)$ rispettando il dominio.
2. Determina $Z_{h\circ g}$ per $h(t)=t^2-1$ e $g(x)=|x-2|$.
3. Calcola le intersezioni tra $y=x^2$ e $y=2x+3$.
4. Trova i valori di $a$ per cui $f_a(x)=x^2-2ax+a+2$ ha due, uno o nessuno zero reale.
5. Fornisci una funzione con insieme degli zeri infinito ma non identicamente nulla.
6. Dimostra che una funzione strettamente monotona ha al più uno zero.
7. Spiega perché $e^x=0$ non ha soluzioni reali usando l'immagine dell'esponenziale.

---

### **12. Riepilogo**

> ✅ Gli zeri di una funzione sono le soluzioni di $f(x)=0$ appartenenti al dominio.

Punti essenziali:

- uno zero deve appartenere al dominio;
- per frazioni si annulla il numeratore ma si controlla il denominatore;
- funzioni sempre positive o sempre negative non hanno zeri;
- gli zeri sono i punti di intersezione del grafico con l’asse $x$.
- le intersezioni tra due grafici sono zeri della differenza sul dominio comune.
