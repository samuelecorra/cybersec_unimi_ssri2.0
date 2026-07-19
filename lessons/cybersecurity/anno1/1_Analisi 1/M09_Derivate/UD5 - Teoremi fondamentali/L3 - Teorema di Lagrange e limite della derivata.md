# **M9 UD5 Lezione 3 - Teorema di Lagrange e teorema del limite della derivata**

### **1. Obiettivo della lezione**

Il teorema di Lagrange (o **del valor medio**) è Rolle "inclinato": collega l'incremento globale della funzione al valore della derivata in un punto intermedio. È il teorema più usato di tutto il calcolo differenziale — da esso discendono monotonia, stime, disuguaglianze (Lezioni 4 e 6) — e qui salda anche il debito della UD4: il **teorema del limite della derivata**, che giustifica la strategia "$\lim f'$" per i raccordi.

---
### **2. Enunciato**

**Teorema (Lagrange).** Sia $f:[a,b]\to\mathbb{R}$:

1. continua su $[a,b]$;
2. derivabile su $(a,b)$.

Allora esiste almeno un $c\in(a,b)$ tale che

$$
f'(c) = \frac{f(b)-f(a)}{b-a}
$$

Il membro destro è la pendenza della **secante** (corda) per gli estremi del grafico: il teorema afferma che in almeno un punto interno la **tangente è parallela alla corda**. Lettura cinematica: in un viaggio a velocità media $v_m$, in almeno un istante il tachimetro segna esattamente $v_m$. Lettura "valor medio": $f'(c)$ realizza il tasso medio di variazione — da cui il nome (da non confondere col futuro teorema della **media integrale** di M12, che mediará i *valori* di una funzione, non il suo incremento).

Rolle è il caso particolare $f(a)=f(b)$ (corda orizzontale).

---
### **3. Dimostrazione**

*Dimostrazione.* Riconduciamoci a Rolle sottraendo la corda. Definiamo la funzione ausiliaria

$$
g(x) = f(x) - \left[f(a) + \frac{f(b)-f(a)}{b-a}(x-a)\right]
$$

(differenza tra $f$ e la retta per i due estremi). Allora:

- $g$ è continua su $[a,b]$ e derivabile su $(a,b)$ (differenza di $f$ e di una funzione affine: algebra della UD2);
- $g(a) = f(a)-f(a) = 0$ e $g(b) = f(b)-f(b) = 0$: quota comune.

Le tre ipotesi di **Rolle** (Lezione 2) sono verificate: esiste $c\in(a,b)$ con $g'(c)=0$. Ma

$$
g'(x) = f'(x) - \frac{f(b)-f(a)}{b-a}
$$

quindi $g'(c)=0$ significa esattamente $f'(c)=\dfrac{f(b)-f(a)}{b-a}$. $\square$

<!-- TODO FIGURA:
Grafico di una funzione su [a,b] con la corda per (a,f(a)) e (b,f(b)) tratteggiata e, in un punto interno c, la tangente parallela alla corda; evidenziare con doppia freccia la distanza verticale g(x) tra curva e corda, massima proprio vicino a c.
Obiettivo didattico: visualizzare Lagrange come Rolle applicato alla distanza dalla corda: tangente parallela alla secante nel punto dove lo scarto e' stazionario.
-->

---
### **4. La forma "incremento finito" e la stima fondamentale**

Riscrittura equivalente (con $x,y\in[a,b]$, $x\neq y$, e $c$ strettamente compreso):

$$
f(x)-f(y) = f'(c)\,(x-y)
$$

Da cui la **stima dell'incremento**: se $|f'(t)|\le M$ per ogni $t$ interno,

$$
|f(x)-f(y)| \le M\,|x-y| \qquad \forall x,y\in[a,b]
$$

cioè: **derivata limitata $\Rightarrow$ funzione Lipschitz** (M08/UD5/L6 — è il criterio annunciato lì e allora vietato: ora è un teorema). Le applicazioni sistematiche sono nelle Lezioni 4 e 6.

---
### **5. Il teorema del limite della derivata**

Il debito della UD4: perché il criterio "calcolo $f'$ nei rami e ne faccio i limiti laterali" funziona?

**Teorema.** Sia $f$ **continua in $x_0$** e derivabile in un intorno destro puntato $(x_0,x_0+\delta)$. Se esiste

$$
\lim_{x\to x_0^+} f'(x) = L \in \overline{\mathbb{R}}
$$

allora il rapporto incrementale destro tende allo stesso $L$; in particolare, se $L$ è **finito**, $f'_+(x_0)$ esiste e vale $L$. (Enunciato simmetrico a sinistra; se i due limiti esistono finiti e uguali, $f$ è derivabile in $x_0$ con $f'(x_0)=L$.)

*Dimostrazione.* Sia $h>0$ piccolo. Su $[x_0,x_0+h]$ la funzione è continua (in $x_0$ per ipotesi, altrove perché derivabile) e derivabile all'interno: **Lagrange** fornisce $c_h\in(x_0,x_0+h)$ con

$$
\frac{f(x_0+h)-f(x_0)}{h} = f'(c_h)
$$

Per $h\to 0^+$ si ha $c_h\to x_0^+$ (è schiacciato: $x_0<c_h<x_0+h$, carabinieri). Poiché $\lim_{x\to x_0^+}f'(x)=L$, dal confronto — per ogni intorno $V$ di $L$, definitivamente $f'(x)\in V$ per $x$ nell'intorno destro, e $c_h$ vi cade definitivamente — segue $f'(c_h)\to L$. Quindi il rapporto incrementale destro tende a $L$. $\square$

> 📌 Tre conseguenze pratiche, che sistemano retroattivamente la UD4:
>
> 1. per i **raccordi** (UD4/L3): continuità prima, poi uguaglianza dei limiti laterali di $f'$ — ora è un teorema, non una ricetta;
> 2. la **classificazione** di angoli/cuspidi/tangenti verticali con i limiti di $f'$ (UD4/L1) è giustificata: laterali finiti diversi $\Rightarrow$ derivate laterali diverse (angolo); limiti infiniti $\Rightarrow$ rapporto incrementale divergente (tangente verticale/cuspide);
> 3. il teorema **non è un se e solo se**: se $\lim f'$ non esiste, il rapporto incrementale può comunque convergere ($x^2\sin\frac 1x$, UD4/L3) — il teorema tace e si torna alla definizione. L'ipotesi di **continuità in $x_0$** è irrinunciabile: per la funzione segno, $\lim_{x\to 0^\pm}f'(x)=0$ ma la funzione non è nemmeno continua in $0$, altro che derivabile.

---
### **6. Errori frequenti**

- ❌ Applicare Lagrange senza continuità **agli estremi** (le ipotesi sono le stesse di Rolle, controesempi inclusi).
- ❌ Trattare $c$ come noto o unico: è "almeno uno", e la sua posizione di solito non si calcola — si usa la sua **esistenza**.
- ❌ Confondere il teorema di Cauchy (valor medio generalizzato, Lezione 5) con il criterio di Cauchy sulle successioni (M05) o sui limiti (M08).
- ❌ Usare il criterio del limite della derivata **senza continuità** nel punto, o concludere "non derivabile" quando $\lim f'$ non esiste.
- ❌ Dedurre da $f'(c)=\frac{f(b)-f(a)}{b-a}$ proprietà per **ogni** $c$.

---
### **7. Esercizi**

**Esercizio 1.** Verifica Lagrange per $f(x)=x^2$ su $[1,3]$ e trova $c$.

*Soluzione.* Pendenza della corda: $\frac{9-1}{2}=4$; $f'(x)=2x=4$ dà $c=2\in(1,3)$ (per la parabola il punto di Lagrange è il punto medio: peculiarità delle quadratiche).

**Esercizio 2.** Usando la stima dell'incremento, dimostra che $|\sin x-\sin y|\le|x-y|$ per ogni $x,y\in\mathbb{R}$.

*Soluzione.* $|\!\cos t|\le 1$: Lagrange su $[y,x]$ dà $|\sin x-\sin y|=|\cos c|\,|x-y|\le|x-y|$. (Confronta con la prova per prostaferesi di M08/UD5/L2: due strade, stessa Lipschitzianità.)

**Esercizio 3.** Un'auto percorre 180 km in 2 ore. Dimostra che in almeno un istante la velocità è stata esattamente 90 km/h.

*Soluzione.* $s$ continua su $[0,2]$, derivabile all'interno (modello): Lagrange dà $s'(c)=\frac{180}{2}=90$.

**Esercizio 4.** Sia $f(x)=\begin{cases}x^2+1, & x\ge 1\\ 2x, & x<1\end{cases}$. Studia la derivabilità in $1$ col teorema del limite della derivata.

*Soluzione.* Continuità: laterali $2$ e $2$, $f(1)=2$ ✓. $f'(x)=2x\to 2$ da destra, $f'=2\to 2$ da sinistra: limiti uguali e finiti $\Rightarrow$ derivabile con $f'(1)=2$.

**Esercizio 5.** Dimostra: se $f$ è derivabile su $\mathbb{R}$ e $f'(x)\to L$ finito per $x\to+\infty$, allora $\frac{f(x+1)-f(x)}{1}\to L$.

*Soluzione.* Lagrange su $[x,x+1]$: $f(x+1)-f(x)=f'(c_x)$ con $c_x\in(x,x+1)\to+\infty$; per composizione/confronto $f'(c_x)\to L$.

---
### **8. Riepilogo**

> ✅ Lagrange: continua su $[a,b]$, derivabile su $(a,b)$ $\Rightarrow$ tangente parallela alla corda in un $c$ interno; prova con l'ausiliaria "funzione meno corda" e Rolle. Forma incremento finito $f(x)-f(y)=f'(c)(x-y)$ e stima $|f(x)-f(y)|\le M|x-y|$: derivata limitata $\Rightarrow$ Lipschitz. Teorema del limite della derivata (via Lagrange + carabinieri sui $c_h$): con continuità nel punto, $\lim_{x\to x_0^\pm}f'=L$ finito $\Rightarrow$ derivata laterale $=L$ — la giustificazione dei metodi della UD4, con i suoi limiti ($x^2\sin\frac 1x$). Prossima lezione: le conseguenze sistematiche (monotonia).
