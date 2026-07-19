# **M8 UD2 Lezione 6 - Valore assoluto, funzioni a tratti e limiti con parametri**

### **1. Obiettivo della lezione**

Tre famiglie di esercizi mettono alla prova la padronanza dei limiti laterali (Lezione 3C della UD1): funzioni con **valore assoluto**, funzioni **definite a tratti**, e limiti **dipendenti da parametri** in cui si chiede per quali valori il limite esiste o assume un valore assegnato. Il metodo è unico: individuare i punti in cui l'espressione cambia regime e lì lavorare **lato per lato**.

---
### **2. Metodo generale**

1. **Dominio**: dove è definita la funzione? Il punto in esame accumula da entrambi i lati o da uno solo?
2. **Segno degli argomenti dei moduli / rami della definizione**: in quali intervalli $|A(x)|=A(x)$ e in quali $=-A(x)$? Quale ramo della definizione a tratti governa ciascun lato?
3. **Limiti laterali**: su ciascun lato la funzione coincide con un'espressione senza modulo (carattere locale, Lezione 3D): si calcola con le tecniche ordinarie.
4. **Raccordo**: bilaterale $=$ laterali uguali (teorema della Lezione 3C). L'eventuale valore della funzione **nel** punto non c'entra con il limite.

---
### **3. Valore assoluto: gli esempi campione**

#### **3.1 $\dfrac{|x|}{x}$ e $\dfrac{|x-a|}{x-a}$**

Per $x>0$: $\frac{|x|}{x}=1$; per $x<0$: $=-1$. Laterali $1\neq -1$: niente limite in $0$ (è la funzione segno delle Lezioni 3B-3C). Traslando, $\dfrac{|x-a|}{x-a}$ ha laterali $1$ e $-1$ in $x=a$: il limite bilaterale non esiste in $a$, qualunque sia $a$.

#### **3.2 Il modulo si scioglie, poi si calcola**

$$
\lim_{x\to 2}\frac{|x^2-4|}{x-2}
$$

L'argomento $x^2-4=(x-2)(x+2)$ è negativo per $x\in(-2,2)$ e positivo per $x>2$. Quindi:

- lato destro ($x>2$): $\dfrac{x^2-4}{x-2}=x+2\to 4$;
- lato sinistro ($-2<x<2$): $\dfrac{-(x^2-4)}{x-2}=-(x+2)\to -4$.

Laterali diversi: il limite non esiste. Nota il flusso: **prima** si scioglie il modulo su ciascun lato, **poi** si semplifica (lecito per $x\neq 2$), poi si sostituisce.

#### **3.3 Quando il modulo non crea salti**

$$
\lim_{x\to 0}\frac{|x|\,\sin x}{x} = \lim_{x\to 0}\frac{|x|}{x}\,\sin x = 0
$$

Il fattore $\frac{|x|}{x}$ non ha limite, ma è **limitato** ($=\pm 1$) e $\sin x\to 0$: prodotto di limitata per infinitesima $\to 0$ (carabinieri, Lezione 3D). Il modulo non implica automaticamente la non esistenza: va guardata la struttura.

---
### **4. Funzioni definite a tratti**

$$
f(x)=\begin{cases} e^{x}, & x<0 \\ 5, & x=0 \\ 1+\sin x, & x>0 \end{cases}
$$

In $x_0=0$: lato sinistro $e^x\to 1$; lato destro $1+\sin x\to 1$. Quindi

$$
\lim_{x\to 0}f(x)=1
$$

**nonostante** $f(0)=5$: il valore nel punto è irrilevante per il limite (qui $f$ ha in $0$ una discontinuità eliminabile: UD5, Lezione 3). Nei punti interni di ciascun ramo, invece, il limite si calcola direttamente sul ramo, senza laterali: il raccordo va controllato **solo** dove la definizione cambia.

> 📌 Con più punti di raccordo si ripete l'analisi in ciascuno. E se il punto di raccordo è anche bordo del dominio (es. $f$ definita solo per $x\ge 0$), l'unico laterale esistente decide da solo.

---
### **5. Limiti con parametri**

Negli esercizi parametrici i coefficienti incogniti vanno determinati imponendo condizioni sui limiti. I casi tipici:

#### **5.1 Esistenza del limite in un punto di raccordo**

$$
f(x)=\begin{cases} ax+3, & x<1 \\ x^2+b, & x\ge 1 \end{cases}
$$

Il limite in $1$ esiste $\iff$ i laterali coincidono: $a+3 = 1+b$, cioè $b=a+2$: **una famiglia** di soluzioni, un grado di libertà. Se si chiede anche un valore specifico (es. limite $=5$) si aggiunge l'equazione $a+3=5$: $a=2$, $b=4$.

#### **5.2 Limite finito per una forma che rischia di esplodere**

$$
\lim_{x\to 1}\frac{x^2+ax+b}{x-1}
$$

Il denominatore tende a $0$: se il numeratore in $1$ non si annulla, i laterali sono infiniti (con segni opposti) e il limite non esiste. **Condizione necessaria** per un limite finito: numeratore nullo in $x=1$, cioè $1+a+b=0$, $b=-1-a$. Con questa condizione:

$$
x^2+ax-1-a = (x-1)(x+1+a)
\quad\Longrightarrow\quad
\lim_{x\to 1}\frac{(x-1)(x+1+a)}{x-1} = 2+a
$$

Se il testo chiede limite $=7$: $a=5$, $b=-6$. Il pattern "denominatore infinitesimo $\Rightarrow$ il numeratore deve annullarsi (ordine almeno pari) per avere limite finito" è tra i più ricorrenti negli scritti.

#### **5.3 Parametro dentro un limite notevole**

$$
\lim_{x\to 0}\frac{\sin(ax)}{3x} = \frac{a}{3} \qquad (a\neq 0 \text{ via } \tfrac{\sin(ax)}{ax}\cdot\tfrac a3;\ \text{per } a=0 \text{ il limite è } 0 = \tfrac a3 \text{ comunque})
$$

Imporre ad esempio valore $2$ dà $a=6$. Analogamente con $e^{ax}-1\sim ax$, $\ln(1+ax)\sim ax$: il parametro scala l'equivalenza.

#### **5.4 Parametro che decide l'asintoto**

Per $f(x)=\dfrac{ax^2+1}{x+1}$: se $a=0$, $f\to 0$ all'infinito con asintoto orizzontale $y=0$; se $a\neq 0$, $m=\lim\frac{f(x)}{x}=a$ e $q=\lim\left(f(x)-ax\right)=\lim\frac{-ax+1}{x+1}=-a$: asintoto obliquo $y=ax-a$. Chiedere "per quale $a$ l'asintoto obliquo è $y=2x-2$" dà $a=2$.

> ⚠️ Negli esercizi parametrici la **discussione dei casi** fa parte della risposta: il valore del parametro può cambiare la natura stessa del limite (finito / infinito / inesistente), e vanno dichiarati tutti i regimi, non solo quello richiesto.

<!-- TODO FIGURA:
Grafico di una funzione a tratti con parametro: ramo sinistro ax+3 disegnato per due valori diversi di a (due rette tratteggiate che arrivano in x=1 a quote diverse) e ramo destro x^2+b fisso; evidenziare con un pallino la quota del raccordo e il salto che si chiude solo per la scelta giusta del parametro.
Obiettivo didattico: visualizzare la condizione di esistenza del limite come chiusura del salto tra i due rami al variare del parametro.
-->

---
### **6. Errori frequenti**

- ❌ Sciogliere il modulo con un segno unico su tutto $\mathbb{R}$ (il segno dell'argomento va studiato intervallo per intervallo).
- ❌ Usare $f(x_0)$ nel calcolo del limite in un punto di raccordo (il valore nel punto non conta).
- ❌ Nei parametrici, imporre solo l'uguaglianza dei laterali quando il testo chiede anche il valore (o viceversa, aggiungere condizioni non richieste).
- ❌ Dimenticare la discussione dei casi degeneri del parametro ($a=0$ che abbassa il grado, coefficiente che annulla il termine dominante...).
- ❌ Concludere "non esiste" appena compare un modulo: serve il confronto effettivo dei laterali (punto 3.3).

---
### **7. Esercizi**

**Esercizio 1.** Calcola, se esiste, $\displaystyle\lim_{x\to 3}\frac{x^2-9}{|x-3|}$.

*Soluzione.* Destro: $\frac{(x-3)(x+3)}{x-3}=x+3\to 6$. Sinistro: $\frac{(x-3)(x+3)}{-(x-3)}=-(x+3)\to-6$. Non esiste.

**Esercizio 2.** Determina $a,b$ affinché $f(x)=\begin{cases}\dfrac{\sin(ax)}{x}, & x<0 \\ b, & x=0 \\ \dfrac{e^{2x}-1}{x}, & x>0\end{cases}$ abbia limite in $0$ uguale a $f(0)$.

*Soluzione.* Sinistro: $a$ (per $a\neq0$ via notevole; per $a=0$ vale $0=a$ comunque). Destro: $2$. Esistenza: $a=2$; uguaglianza col valore: $b=2$. (Così $f$ è anche continua in $0$: UD5.)

**Esercizio 3.** Per quali $k$ il limite $\displaystyle\lim_{x\to+\infty}\left(\sqrt{x^2+kx}-x\right)$ vale $3$?

*Soluzione.* Razionalizzando (UD2/L4, §7): limite $=\frac{k}{2}$; quindi $k=6$.

**Esercizio 4.** Discuti al variare di $a\in\mathbb{R}$ il limite $\displaystyle\lim_{x\to 0^+}\frac{1-\cos x}{x^{\,a}}$.

*Soluzione.* $1-\cos x\sim\frac{x^2}{2}$, quindi il rapporto $\sim\frac{1}{2}x^{\,2-a}$: limite $0$ se $a<2$; $\frac{1}{2}$ se $a=2$; $+\infty$ se $a>2$. Tre regimi, tutti da dichiarare.

---
### **8. Riepilogo**

> ✅ Moduli e tratti si trattano sciogliendo l'espressione lato per lato (studio del segno dell'argomento, ramo competente) e confrontando i laterali; il valore nel punto non entra mai. Nei parametrici: uguaglianza dei laterali per l'esistenza, equazioni aggiuntive per i valori richiesti, numeratore che deve annullarsi contro un denominatore infinitesimo, parametri dentro i notevoli che scalano le equivalenze; sempre con la discussione completa dei casi.
