# **M9 UD1 Lezione 4 - Derivabilità implica continuità**

### **1. Obiettivo della lezione**

Il primo teorema strutturale sulle derivate: la derivabilità è una condizione **più forte** della continuità. Dimostrazione completa, lettura geometrica, e i controesempi che mostrano che l'implicazione inversa è falsa. Questo teorema è un ingranaggio silenzioso di quasi tutte le dimostrazioni della UD2 (prodotto, reciproco, catena): va saputo alla perfezione.

---
### **2. Il teorema**

**Teorema.** Se $f$ è derivabile in $x_0$, allora $f$ è continua in $x_0$.

*Dimostrazione.* Per $x\neq x_0$ (nel dominio, vicino a $x_0$) vale l'identità algebrica

$$
f(x)-f(x_0) = \frac{f(x)-f(x_0)}{x-x_0}\cdot(x-x_0)
$$

Passiamo al limite per $x\to x_0$: il primo fattore tende a $f'(x_0)$ (esiste finito per ipotesi), il secondo a $0$. Per l'algebra dei limiti (M08):

$$
\lim_{x\to x_0}\big[f(x)-f(x_0)\big] = f'(x_0)\cdot 0 = 0
\qquad\Longleftrightarrow\qquad
\lim_{x\to x_0}f(x)=f(x_0)
$$

che è la definizione di continuità in $x_0$. $\square$

Nella forma "con $h$": $f(x_0+h)-f(x_0) = h\cdot\frac{f(x_0+h)-f(x_0)}{h} \to 0\cdot f'(x_0)=0$.

> 📌 Dove serve la **finitezza** di $f'(x_0)$: nel prodotto "$f'(x_0)\cdot 0$". Se il rapporto incrementale divergesse, avremmo la forma indeterminata $\infty\cdot 0$ e la prova si fermerebbe — infatti il teorema resta vero anche con derivata infinita (es. $\sqrt[3]{x}$ è continua in $0$), ma la **dimostrazione** data richiede il limite finito, e la derivabilità per definizione lo esige.

**Contronominale operativa**: se $f$ **non** è continua in $x_0$, allora non è derivabile in $x_0$. Negli esercizi è il primo controllo da fare, prima di ogni calcolo (UD4/L2).

---
### **3. Il viceversa è falso: i controesempi**

#### **3.1 $|x|$ in $0$: continua, non derivabile (angolo)**

$|x|$ è continua su $\mathbb{R}$ (M08/UD5). In $0$ le derivate laterali valgono $1$ e $-1$ (Lezione 2): niente derivata. La continuità **non** implica la derivabilità.

#### **3.2 $x^{1/3}$ in $0$: continua, derivata infinita**

$\sqrt[3]{x}$ è continua in $0$, ma il rapporto incrementale diverge a $+\infty$: tangente verticale, nessuna derivata reale. Questo esempio mostra anche la distinzione **derivata infinita $\neq$ derivabilità**: il limite del rapporto esiste in $\overline{\mathbb{R}}$, ma non è un numero.

#### **3.3 $x\sin\frac 1x$ in $0$: continua, rapporto oscillante**

Con $f(0)=0$, la funzione è continua in $0$ (carabinieri, M08), ma il rapporto incrementale $\sin\frac 1h$ non ha limite né finito né infinito: non derivabile per **oscillazione** (UD4/L1). I tre controesempi coprono i tre modi in cui la continuità può non bastare: laterali diverse, limite infinito, limite inesistente.

> 💡 Esiste di peggio: si possono costruire funzioni **continue su tutto $\mathbb{R}$ e derivabili in nessun punto** (funzione di Weierstrass, una serie di coseni sempre più oscillanti). È un approfondimento fuori programma, ma spiega perché "continua quindi quasi ovunque derivabile" è un'intuizione sbagliata.

<!-- TODO FIGURA:
Tre pannelli: (a) grafico di |x| con lo spigolo in 0 e le due semitangenti; (b) grafico di x^(1/3) con tangente verticale in 0; (c) grafico di x*sin(1/x) vicino a 0 con inviluppo y=±x e alcune secanti dall'origine con pendenze diverse. Sotto ciascun pannello: "continua in 0? sì / derivabile in 0? no" con il motivo (laterali diverse, limite infinito, limite inesistente).
Obiettivo didattico: fissare che derivabile implica continua ma non viceversa, con i tre meccanismi distinti di fallimento.
-->

---
### **4. La gerarchia delle regolarità**

Mettendo in fila M08 e questa lezione:

$$
\{\text{derivabili in } x_0\} \subsetneq \{\text{continue in } x_0\} \subsetneq \{\text{definite in } x_0\}
$$

e tutte le inclusioni sono strette ($|x|$; la funzione segno). La scala proseguirà con $C^1\subsetneq\{\text{derivabili}\}$ (UD3/L5) e, in M10, con le classi superiori fino a $C^\infty$.

---
### **5. Errori frequenti**

- ❌ "È continua, quindi è derivabile": è l'inversione più comune del teorema; i tre controesempi del punto 3 la smontano.
- ❌ "Non è derivabile, quindi non è continua": stessa inversione, letta al contrario ($|x|$ è continuissima).
- ❌ Verificare la derivabilità in un punto di discontinuità: tempo perso, la contronominale chiude subito.
- ❌ Citare il teorema senza saper indicare **dove** si usa la finitezza della derivata nella prova.

---
### **6. Esercizi**

**Esercizio 1.** $f(x)=\begin{cases}x^2, & x\le 1\\ 2x, & x>1\end{cases}$: continuità e derivabilità in $1$?

*Soluzione.* Laterali della funzione: $1$ e $2$ — non continua in $1$ (salto), quindi **non derivabile**: nessun calcolo di derivate necessario.

**Esercizio 2.** $f(x)=\begin{cases}x^2, & x\le 1\\ 2x-1, & x>1\end{cases}$: continuità e derivabilità in $1$?

*Soluzione.* Laterali $1$ e $1$, $f(1)=1$: continua. Derivate laterali: $2x\to 2$ da sinistra, $2$ da destra: derivabile con $f'(1)=2$ (raccordo liscio: la retta $y=2x-1$ è proprio la tangente alla parabola in $(1,1)$).

**Esercizio 3.** Mostra che $f(x)=x\,|x|$ è derivabile su tutto $\mathbb{R}$ e calcola $f'$.

*Soluzione.* Per $x>0$, $f=x^2$, $f'=2x$; per $x<0$, $f=-x^2$, $f'=-2x$; in $0$ il rapporto $\frac{h|h|}{h}=|h|\to 0$: $f'(0)=0$. Quindi $f'(x)=2|x|$ — continua ma a sua volta non derivabile in $0$: derivare può **abbassare** la regolarità.

---
### **7. Riepilogo**

> ✅ Derivabile in $x_0$ $\Rightarrow$ continua in $x_0$ (prova in una riga: incremento $=$ rapporto $\times\,(x-x_0)\to f'(x_0)\cdot 0$, con la finitezza della derivata come ingrediente essenziale). Il viceversa è falso in tre modi distinti: angolo ($|x|$), tangente verticale ($x^{1/3}$), oscillazione ($x\sin\frac 1x$) — e in modo estremo con funzioni mai derivabili. Uso pratico: la continuità è il **primo filtro** di ogni esercizio di derivabilità.
