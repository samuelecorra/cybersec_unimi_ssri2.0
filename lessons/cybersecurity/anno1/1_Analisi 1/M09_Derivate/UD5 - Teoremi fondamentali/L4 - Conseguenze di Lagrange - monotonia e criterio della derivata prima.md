# **M9 UD5 Lezione 4 - Conseguenze di Lagrange: monotonia e criterio della derivata prima**

### **1. Obiettivo della lezione**

Da Lagrange discende il dizionario completo tra **segno della derivata** e **andamento della funzione**: derivata nulla $=$ costante, segno $=$ monotonia, cambio di segno $=$ estremo. Con i conversi precisi e i controesempi che delimitano ogni implicazione. In tutta la lezione $I$ è un **intervallo**, $f$ continua su $I$ e derivabile nei punti interni.

---
### **2. Derivata nulla su un intervallo: funzione costante**

**Teorema.** Se $f'(x)=0$ per ogni $x$ interno a $I$, allora $f$ è **costante** su $I$.

*Dimostrazione.* Siano $x<y$ in $I$. Su $[x,y]$ valgono le ipotesi di Lagrange: esiste $c\in(x,y)$ con

$$
f(y)-f(x) = f'(c)\,(y-x) = 0\cdot(y-x) = 0
$$

quindi $f(y)=f(x)$ per ogni coppia: $f$ è costante. $\square$

**Corollario.** Se $f'=g'$ su un intervallo, allora $f=g+\text{costante}$ (si applica il teorema a $f-g$). È il risultato che renderà **quasi unica** la primitiva in M12.

> ⚠️ L'ipotesi "**intervallo**" è essenziale. Su $A=(-\infty,0)\cup(0,+\infty)$ la funzione $f(x)=\operatorname{sgn}(x)$... non è derivabile in tutto $A$; esempio pulito: $f(x)=\arctan x+\arctan\frac 1x$ ha derivata nulla su ciascuna semiretta (UD3/L3, esercizio 1) ma vale $\frac{\pi}{2}$ per $x>0$ e $-\frac{\pi}{2}$ per $x<0$: costante **su ciascun intervallo**, non globalmente. Lagrange lavora dentro un intervallo alla volta.

---
### **3. Segno della derivata e monotonia**

**Teorema (monotonia).** Su un intervallo $I$ (derivabilità nei punti interni):

1. $f'\ge 0$ all'interno $\;\Longrightarrow\;$ $f$ **crescente** (debole) su $I$;
2. $f'>0$ all'interno $\;\Longrightarrow\;$ $f$ **strettamente crescente** su $I$;
3. simmetricamente con $\le,\ <$ per la decrescenza.

*Dimostrazione (1 e 2 insieme).* Per $x<y$ in $I$, Lagrange dà $c\in(x,y)$ con $f(y)-f(x)=f'(c)(y-x)$. Il fattore $(y-x)$ è positivo; quindi il segno dell'incremento è il segno di $f'(c)$: se $f'\ge 0$ ovunque, $f(y)\ge f(x)$; se $f'>0$ ovunque, $f(y)>f(x)$. $\square$

**I conversi, con precisione** (qui si annidano gli errori d'esame):

- se $f$ è crescente e derivabile, allora $f'\ge 0$ (permanenza del segno sul rapporto incrementale, che è $\ge 0$);
- ma **stretta crescenza NON implica $f'>0$ ovunque**: $f(x)=x^3$ è strettamente crescente con $f'(0)=0$. Il converso della 2 è falso;
- criterio esatto (utile a sapersi): $f$ derivabile su $I$ è strettamente crescente $\iff$ $f'\ge 0$ e $f'$ non è identicamente nulla su alcun sottointervallo non banale.

**Corollario (unicità degli zeri).** Se $f$ è strettamente monotona su $I$, ha al più uno zero in $I$: combinato col teorema degli zeri (M08), è lo schema standard "esistenza + unicità" (UD5/L2, esercizio 3, ora con la monotonia via segno di $f'$).

<!-- TODO FIGURA:
Grafico di una funzione con derivata positiva su (a,c), nulla in c, negativa su (c,b): funzione che sale, culmina con tangente orizzontale e scende; sotto, la "tabella dei segni" di f' con frecce di crescenza/decrescenza allineate al grafico. Riquadro laterale: x^3 con tangente orizzontale in 0 ma crescenza stretta, come monito sul converso.
Obiettivo didattico: visualizzare il dizionario segno di f' / monotonia e il criterio della derivata prima, con il controesempio x^3 sul converso.
-->

---
### **4. Il criterio della derivata prima**

**Teorema (criterio della derivata prima).** Sia $f$ continua in $x_0$ e derivabile in un intorno puntato $(x_0-\delta,x_0)\cup(x_0,x_0+\delta)$.

1. Se $f'>0$ a sinistra di $x_0$ e $f'<0$ a destra, allora $x_0$ è un punto di **massimo locale**;
2. se $f'<0$ a sinistra e $f'>0$ a destra, **minimo locale**;
3. se $f'$ ha lo **stesso segno** (stretto) dai due lati, $x_0$ **non** è un estremo (la funzione vi transita monotona).

*Dimostrazione (caso 1).* Per il teorema di monotonia applicato a $[x_0-\delta',x_0]$ (dove $f'>0$ all'interno e $f$ è continua fino a $x_0$): $f$ è crescente, quindi $f(x)\le f(x_0)$ a sinistra. Analogamente $f$ è decrescente su $[x_0,x_0+\delta']$: $f(x)\le f(x_0)$ a destra. Le due disuguaglianze insieme dicono che $x_0$ è massimo locale. $\square$

Osservazioni che valgono punti:

- il criterio **non richiede la derivabilità in $x_0$**: solo la continuità lì e la derivabilità attorno. Per questo cattura anche gli estremi nei punti angolosi: $|x|$ ha $f'<0$ a sinistra e $f'>0$ a destra di $0$ $\Rightarrow$ minimo, senza che $f'(0)$ esista;
- è una condizione **sufficiente**: esistono estremi senza cambio di segno "pulito" (funzioni oscillanti come $x^2(2+\sin\frac 1x)$ hanno minimo in $0$ ma $f'$ cambia segno infinite volte in ogni intorno: il criterio non si applica e decide la definizione);
- il caso "segno costante" (3) è il verdetto su $x^3$: stazionario, nessun estremo, transito crescente.

**Cenno al criterio della derivata seconda** (anticipazione dichiarata): se $f'(x_0)=0$ e $f''(x_0)>0$ allora $x_0$ è minimo locale ($f''(x_0)<0$: massimo). Giustificazione rapida: $f''(x_0)>0$ significa che il rapporto $\frac{f'(x)-f'(x_0)}{x-x_0}=\frac{f'(x)}{x-x_0}$ ha limite positivo, quindi per permanenza del segno $f'$ è negativa a sinistra e positiva a destra di $x_0$: criterio della derivata prima, caso 2. Con $f''(x_0)=0$ il criterio è **inconcludente** ($x^3$, $x^4$, $-x^4$: tutti con $f'(0)=f''(0)=0$ ed esiti diversi); la classificazione sistematica con le derivate successive appartiene a Taylor (M10/UD3).

---
### **5. Derivata limitata e Lipschitz**

Dalla stima dell'incremento (Lezione 3, punto 4): se $|f'|\le M$ all'interno di $I$, allora $f$ è **Lipschitz** su $I$ con costante $M$ — quindi uniformemente continua (M08/UD5/L6: il cerchio si chiude). Esempi: $\sin$, $\cos$ ($M=1$); $\arctan$ ($M=1$); su intervalli limitati, ogni funzione $C^1$ (la derivata continua su un chiuso è limitata per Weierstrass).

---
### **6. Errori frequenti**

- ❌ "Derivata nulla $\Rightarrow$ costante" su domini **non connessi** ($\arctan x+\arctan\frac 1x$).
- ❌ "$f$ strettamente crescente $\Rightarrow$ $f'>0$ ovunque" ($x^3$).
- ❌ "$f'(x_0)>0$ $\Rightarrow$ $f$ crescente in un intorno di $x_0$": falso in generale se $f'$ non è continua — il segno **in un punto** non basta, serve il segno **su un intervallo** (controesempio fine: $f(x)=x+2x^2\sin\frac 1x$ ha $f'(0)=1>0$ ma non è monotona in alcun intorno di $0$, perché $f'$ oscilla tra valori positivi e negativi arbitrariamente vicino a $0$).
- ❌ Applicare il criterio della derivata prima senza continuità in $x_0$ (con un salto, il cambio di segno di $f'$ non dice nulla).
- ❌ Concludere estremi dal solo $f'(x_0)=0$ (caso 3 del criterio).

---
### **7. Esercizi**

**Esercizio 1.** Studia la monotonia di $f(x)=\frac{x}{1+x^2}$ su $\mathbb{R}$ e classifica gli estremi.

*Soluzione.* $f'(x)=\frac{1-x^2}{(1+x^2)^2}$: negativa per $|x|>1$, positiva per $|x|<1$. Decresce, minimo locale in $-1$ ($f=-\frac 12$), cresce, massimo locale in $1$ ($f=\frac 12$), decresce. Con i limiti $f\to 0$ a $\pm\infty$: sono anche estremi globali.

**Esercizio 2.** Dimostra che $e^x = 2-x$ ha esattamente una soluzione reale.

*Soluzione.* $g(x)=e^x+x-2$: $g'=e^x+1>0$ (strettamente crescente), $g(0)=-1<0<g(1)=e-1$: esistenza (zeri, M08) + unicità (stretta monotonia).

**Esercizio 3.** Determina gli estremi di $f(x)=x^{2/3}(x-5)$ su $\mathbb{R}$.

*Soluzione.* $f=x^{5/3}-5x^{2/3}$, $f'=\frac{5}{3}x^{2/3}-\frac{10}{3}x^{-1/3}=\frac{5}{3}\,\frac{x-2}{x^{1/3}}$ per $x\neq 0$. Segno: positiva per $x<0$ ($\frac{-}{-}$), negativa su $(0,2)$, positiva per $x>2$. Quindi: massimo locale in $0$ (punto **non derivabile**: $f'\to\mp\infty$... precisamente $f'\to+\infty$ per $x\to 0^-$ e $\to-\infty$ per $x\to 0^+$, cuspide) con $f(0)=0$; minimo locale in $2$ (stazionario) con $f(2)=2^{2/3}\cdot(-3)=-3\sqrt[3]{4}$.

**Esercizio 4.** Dimostra che $f(x)=\tan x - x$ è strettamente crescente su $\left(-\frac{\pi}{2},\frac{\pi}{2}\right)$ e deducine $\tan x>x$ per $x\in\left(0,\frac{\pi}{2}\right)$.

*Soluzione.* $f'=\tan^2 x\ge 0$, nulla solo in $0$ (punto isolato di annullamento: criterio esatto del punto 3, $f$ strettamente crescente). Da $f(0)=0$: $f(x)>0$ per $x>0$, cioè $\tan x>x$ — la disuguaglianza geometrica di M08/UD3 ridimostrata analiticamente; il metodo sistematico è nella Lezione 6.

---
### **8. Riepilogo**

> ✅ Da Lagrange: $f'=0$ su un intervallo $\Rightarrow$ costante (e primitive uguali a meno di costante, per M12); $f'\ge 0$/$>0$ $\Rightarrow$ crescenza debole/stretta, con conversi asimmetrici ($x^3$); criterio della derivata prima con la continuità nel punto ma **senza** derivabilità lì (estremi negli angoli inclusi), sufficiente e non necessario; segno in un punto $\neq$ segno in un intorno ($x+2x^2\sin\frac 1x$); derivata seconda come scorciatoia annunciata (prova completa in M10); $|f'|\le M$ $\Rightarrow$ Lipschitz. Sempre su **intervalli**: i buchi nel dominio rompono tutto.
