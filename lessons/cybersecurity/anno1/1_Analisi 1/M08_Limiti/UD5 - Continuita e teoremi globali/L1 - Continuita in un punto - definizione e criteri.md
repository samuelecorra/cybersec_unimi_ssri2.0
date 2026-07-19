# **M8 UD5 Lezione 1 - Continuità in un punto: definizione e criteri**

### **1. Obiettivo dell'unità e della lezione**

La Lezione 4 della UD1 ha introdotto la continuità come strumento di calcolo ("se è continua, sostituisci"). Questa unità la promuove a **teoria**: definizioni precise, criteri equivalenti, algebra, continuità delle funzioni elementari, classificazione delle discontinuità e i grandi teoremi globali (zeri, valori intermedi, Weierstrass, inversa, Heine-Cantor) che sono il fondamento di tutto ciò che seguirà in M9-M12.

In questa prima lezione: definizione puntuale, continuità laterale e sui bordi, criterio sequenziale.

---
### **2. Definizione di continuità in un punto**

**Definizione.** Sia $f:A\subseteq\mathbb{R}\to\mathbb{R}$ e sia $x_0\in A$ (il punto **deve appartenere al dominio**).

- Se $x_0$ è un punto di accumulazione di $A$: $f$ è **continua in $x_0$** se

$$
\lim_{x\to x_0}f(x)=f(x_0)
$$

- Se $x_0$ è un punto isolato di $A$: $f$ si dice continua in $x_0$ **per convenzione** (la condizione $\varepsilon$–$\delta$ qui sotto è automaticamente vera).

Forma $\varepsilon$–$\delta$ equivalente (unifica i due casi):

$$
\forall\varepsilon>0\ \exists\delta>0\ \forall x\in A:\quad |x-x_0|<\delta \;\Rightarrow\; |f(x)-f(x_0)|<\varepsilon
$$

> 📌 Confronta con la definizione di limite: è **sparita la condizione $0<|x-x_0|$**. Nel limite il punto $x_0$ è escluso (e $f(x_0)$ può non esistere); nella continuità il punto è incluso e il test "in $x_0$" è banalmente vero ($|f(x_0)-f(x_0)|=0$). La continuità in un punto di accumulazione impacchetta **tre condizioni**: $f(x_0)$ esiste, il limite esiste finito, i due coincidono. Se ne fallisce una, $f$ è **discontinua** in $x_0$ (classificazione nella Lezione 3).

$f$ si dice **continua su un insieme** $E\subseteq A$ se è continua in ogni punto di $E$; si dice semplicemente "continua" se lo è in ogni punto del dominio.

> ⚠️ Sottigliezza da orale: la continuità è una proprietà **relativa al dominio**. La funzione $f(x)=\frac{1}{x}$ è **continua** (in ogni punto del suo dominio $\mathbb{R}\setminus\{0\}$): in $0$ non è "discontinua", perché $0$ non è un punto del dominio — lì semplicemente la funzione non c'è, e la retta $x=0$ è un asintoto. Parlare di discontinuità in $x_0$ ha senso pieno quando $x_0\in A$, oppure — con la terminologia di "discontinuità eliminabile" estesa — quando $x_0$ è di accumulazione con limite finito (Lezione 3).

---
### **3. Continuità laterale e continuità sui bordi**

**Definizione.** $f$ è **continua da destra** in $x_0\in A$ se

$$
\lim_{x\to x_0^+}f(x)=f(x_0)
$$

e **continua da sinistra** se $\displaystyle\lim_{x\to x_0^-}f(x)=f(x_0)$ (quando i rispettivi lati hanno senso, cioè $x_0$ accumula da quel lato).

Se $x_0$ accumula da entrambi i lati, per il teorema sui laterali (UD1, Lezione 3C):

$$
f \text{ continua in } x_0 \iff f \text{ continua da destra e da sinistra in } x_0
$$

**Bordi e intervalli chiusi.** Per $f:[a,b]\to\mathbb{R}$, dire che $f$ è continua su $[a,b]$ significa: continua in ogni punto interno, continua **da destra** in $a$ e **da sinistra** in $b$ (dagli altri lati il dominio non esiste, e la definizione generale — con gli intorni intersecati con $A$ — produce da sola questa lettura). Esempio: $\sqrt{x}$ è continua su $[0,+\infty)$, e in $0$ la continuità è quella destra: $\lim_{x\to 0^+}\sqrt{x}=0=\sqrt{0}$.

**Esempio con un solo lato buono.** La funzione **parte intera** $f(x)=\lfloor x\rfloor$ è continua da destra in ogni intero $n$ ($\lim_{x\to n^+}\lfloor x\rfloor = n = \lfloor n\rfloor$) ma non da sinistra ($\lim_{x\to n^-}\lfloor x\rfloor = n-1$): è l'archetipo della discontinuità a salto, con la funzione "attaccata" a un solo ramo.

<!-- TODO FIGURA:
Grafico della funzione parte intera floor(x) su [-2, 3]: gradini orizzontali con pallino pieno all'estremo sinistro di ogni gradino e pallino vuoto all'estremo destro; in un intero n evidenziare le due frecce dei limiti laterali con quote n (destro) e n-1 (sinistro).
Obiettivo didattico: mostrare continuità da destra senza continuità da sinistra e legare i pallini pieni/vuoti ai limiti laterali.
-->

---
### **4. Criterio sequenziale di continuità**

**Teorema.** $f:A\to\mathbb{R}$ è continua in $x_0\in A$ **se e solo se** per ogni successione $(x_n)$ con $x_n\in A$ e $x_n\to x_0$ si ha

$$
f(x_n)\to f(x_0)
$$

*Dimostrazione.* Se $x_0$ è isolato: ogni successione in $A$ convergente a $x_0$ è definitivamente uguale a $x_0$ (l'intorno che isola $x_0$ cattura definitivamente la successione), quindi $f(x_n)$ è definitivamente $f(x_0)$ e converge; il criterio è vero, coerentemente con la convenzione. Sia dunque $x_0$ di accumulazione.

($\Rightarrow$) Come per Heine (UD1, Lezione 3E), con una differenza: la stima $\varepsilon$–$\delta$ della continuità vale anche in $x_0$ (niente condizione puntata), quindi copre pure i termini con $x_n=x_0$. Fissato $\varepsilon$, il $\delta$ della continuità e la definizione di $x_n\to x_0$ danno $|f(x_n)-f(x_0)|<\varepsilon$ definitivamente.

($\Leftarrow$) Contronominale, identica a Heine: se $f$ non è continua in $x_0$, esiste $\varepsilon_0$ tale che per ogni $n$ si trova $x_n\in A$ con $|x_n-x_0|<\frac{1}{n}$ e $|f(x_n)-f(x_0)|\ge\varepsilon_0$; la successione testimone converge a $x_0$ ma le immagini no. $\square$

> 📌 **Differenza cruciale rispetto a Heine per i limiti**: qui le successioni **possono toccare $x_0$** (niente vincolo $x_n\neq x_0$), perché la continuità confronta con $f(x_0)$, che esiste ed è "quello giusto". È questa libertà che rende il criterio perfetto per lavorare con i teoremi di M05 (Bolzano-Weierstrass in testa) nelle Lezioni 5 e 6.

**Uso negativo immediato.** $f(x)=\begin{cases}1, & x\in\mathbb{Q}\\ 0, & x\notin\mathbb{Q}\end{cases}$ (Dirichlet) non è continua in alcun punto: per ogni $x_0$, una successione razionale e una irrazionale convergenti a $x_0$ (densità, M02) danno immagini $1$ e $0$, e almeno una delle due tradisce $f(x_0)$.

---
### **5. Prime funzioni continue "a mano"**

Dalle dimostrazioni $\varepsilon$–$\delta$ della UD1 (Lezione 3B) sappiamo già, gratis, che sono continue su tutto il loro dominio:

- le **costanti** e l'**identità** $f(x)=x$ (caso $m=1,q=0$ della funzione affine);
- ogni **funzione affine** $mx+q$;
- $x^2$ (e, con lo stesso schema, ogni potenza intera);
- $|x|$;
- $\dfrac{1}{x}$ su $\mathbb{R}\setminus\{0\}$.

In ciascun caso avevamo dimostrato $\lim_{x\to x_0}f(x)=f(x_0)$ per ogni $x_0$ del dominio: è esattamente la definizione di continuità. Il catalogo completo delle funzioni elementari è il tema della prossima lezione, insieme all'algebra che permette di combinarle.

---
### **6. Errori frequenti**

- ❌ Definire la continuità senza richiedere $x_0\in A$ (il confronto con $f(x_0)$ richiede che $f(x_0)$ esista).
- ❌ Scrivere la condizione $\varepsilon$–$\delta$ della continuità con $0<|x-x_0|$: quella è la definizione di limite; per la continuità il punto è incluso.
- ❌ Dire che $\frac{1}{x}$ "è discontinua in $0$": in $0$ non è definita; sul suo dominio è continua.
- ❌ Nel criterio sequenziale della continuità, imporre $x_n\neq x_0$: il vincolo appartiene al criterio di Heine per i **limiti**.
- ❌ Confondere "il limite esiste" con "è continua": serve anche che il limite coincida con il valore.

---
### **7. Esercizi**

**Esercizio 1.** Stabilisci in quali punti è continua $f(x)=\begin{cases} x^2, & x\le 1 \\ 2x-1, & x>1\end{cases}$.

*Soluzione.* Nei punti $x\neq 1$ ogni ramo è polinomiale, continua. In $1$: sinistro $1$, destro $1$, $f(1)=1$: continua. Quindi continua su $\mathbb{R}$ (i due rami si saldano con lo stesso valore).

**Esercizio 2.** Dimostra con il criterio sequenziale che $g(x)=\begin{cases} \sin\frac{1}{x}, & x\neq 0 \\ 0, & x=0\end{cases}$ non è continua in $0$.

*Soluzione.* $x_n=\frac{1}{\pi/2+2n\pi}\to 0$ ma $g(x_n)=1\not\to 0=g(0)$.

**Esercizio 3.** Dimostra che $h(x)=\begin{cases} x\sin\frac{1}{x}, & x\neq 0 \\ 0, & x=0\end{cases}$ è continua in $0$.

*Soluzione.* $|h(x)-h(0)|=|x\sin\frac 1x|\le|x|$: dato $\varepsilon$, basta $\delta=\varepsilon$ (oppure: carabinieri sul limite, che vale $0=h(0)$).

**Esercizio 4.** Sia $f$ continua in $x_0$ con $f(x_0)>0$. Dimostra che $f$ è positiva in tutto un intorno di $x_0$ (non solo puntato).

*Soluzione.* Permanenza del segno (UD1, Lezione 3D) sul limite, che dà il segno nell'intorno puntato; nel punto $x_0$ vale $f(x_0)>0$ per ipotesi: l'intorno pieno è coperto.

---
### **8. Riepilogo**

> ✅ Continuità in $x_0$: $x_0\in A$ e (se di accumulazione) $\lim_{x\to x_0}f=f(x_0)$; nei punti isolati è automatica; in $\varepsilon$–$\delta$ sparisce la condizione puntata. Laterale: limite laterale $=$ valore; sui bordi degli intervalli conta il lato interno. Criterio sequenziale: continuità $\iff$ $f(x_n)\to f(x_0)$ per **ogni** successione del dominio convergente a $x_0$, incluse quelle che toccano $x_0$. La continuità è relativa al dominio: $\frac 1x$ è continua.
