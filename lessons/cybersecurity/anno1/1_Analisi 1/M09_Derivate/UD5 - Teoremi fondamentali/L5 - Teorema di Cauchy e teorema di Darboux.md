# **M9 UD5 Lezione 5 - Teorema di Cauchy e teorema di Darboux**

### **1. Obiettivo della lezione**

Due teoremi che completano il quadro: il **valor medio generalizzato di Cauchy** (il motore della futura regola di de l'Hôpital, M10) e il **teorema di Darboux**, la proprietà nascosta di ogni derivata — i valori intermedi senza continuità. Il secondo è il tipo di risultato che separa una preparazione da 30 e lode.

---
### **2. Il teorema di Cauchy**

**Teorema (Cauchy, o del valor medio generalizzato).** Siano $f,g:[a,b]\to\mathbb{R}$ continue su $[a,b]$ e derivabili su $(a,b)$. Allora esiste $c\in(a,b)$ tale che

$$
\big[f(b)-f(a)\big]\,g'(c) = \big[g(b)-g(a)\big]\,f'(c)
$$

Questa è la **forma simmetrica**, che vale sempre. La più nota **forma con quoziente**

$$
\frac{f(b)-f(a)}{g(b)-g(a)} = \frac{f'(c)}{g'(c)}
$$

richiede ipotesi in più: $g'(x)\neq 0$ su $(a,b)$ — che garantisce sia $g(b)\neq g(a)$ (altrimenti Rolle su $g$ darebbe uno zero di $g'$) sia $g'(c)\neq 0$. Non vanno confuse: la simmetrica è il teorema, la quoziente è il corollario con le sue condizioni.

*Dimostrazione.* Funzione ausiliaria alla Lagrange, ma "incrociata":

$$
h(x) = \big[f(b)-f(a)\big]\,g(x) - \big[g(b)-g(a)\big]\,f(x)
$$

$h$ è continua su $[a,b]$ e derivabile su $(a,b)$ (algebra, UD2). Ai bordi:

$$
h(a) = f(b)g(a)-f(a)g(b) = h(b)
$$

(sviluppando: $h(a)=\big[f(b)-f(a)\big]g(a)-\big[g(b)-g(a)\big]f(a) = f(b)g(a)-g(b)f(a)$, e il conto per $h(b)$ dà lo stesso valore). **Rolle** fornisce $c\in(a,b)$ con $h'(c)=0$, cioè

$$
\big[f(b)-f(a)\big]g'(c) - \big[g(b)-g(a)\big]f'(c) = 0 \qquad\square
$$

**Lagrange è il caso $g(x)=x$**: $g'=1$, $g(b)-g(a)=b-a$, e la formula si riduce a $f(b)-f(a)=(b-a)f'(c)$. La gerarchia è quindi: Fermat $\to$ Rolle $\to$ Lagrange e Cauchy (entrambi da Rolle con ausiliarie diverse).

**Lettura geometrica**: per la curva parametrica $t\mapsto(g(t),f(t))$, il quoziente $\frac{f(b)-f(a)}{g(b)-g(a)}$ è la pendenza della corda nel piano $(g,f)$ e $\frac{f'(c)}{g'(c)}$ quella della tangente (è la formula $\frac{dy}{dx}=\frac{dy/dt}{dx/dt}$ delle curve parametriche, per punti regolari $g'\neq 0$): Cauchy è Lagrange per curve parametriche.

> 📌 **Dove servirà**: la regola di **de l'Hôpital** — collocata in M10/UD2, dove la troverai enunciata e applicata — si dimostra applicando Cauchy sull'intervallo $[x_0,x]$ ai due infinitesimi $f,g$: il rapporto $\frac{f(x)}{g(x)}$ diventa $\frac{f'(c_x)}{g'(c_x)}$ con $c_x\to x_0$. M09 fornisce il motore, M10 la carrozzeria: rinvio documentato, nessuna duplicazione. (E attenzione al nome: il "criterio di Cauchy" di M05/M08 è tutt'altra cosa.)

---
### **3. Il teorema di Darboux**

Dalla UD3/L5 sappiamo che $f'$ può essere **discontinua** ($x^2\sin\frac 1x$). Eppure le derivate non sono funzioni qualunque:

**Teorema (Darboux).** Sia $f$ derivabile su $[a,b]$ (lateralmente ai bordi). Allora $f'$ ha la **proprietà dei valori intermedi**: per ogni $\lambda$ strettamente compreso tra $f'(a)$ e $f'(b)$ esiste $c\in(a,b)$ con

$$
f'(c)=\lambda
$$

*Dimostrazione.* Supponiamo $f'(a)<\lambda<f'(b)$ (l'altro verso è simmetrico) e consideriamo l'ausiliaria

$$
g(x) = f(x) - \lambda x
$$

derivabile su $[a,b]$ con $g'=f'-\lambda$; per costruzione $g'(a)<0<g'(b)$. La tesi equivale a trovare un punto stazionario interno di $g$.

$g$ è continua sul compatto $[a,b]$ (derivabile $\Rightarrow$ continua, UD1/L4): per **Weierstrass** ha un punto di minimo $c\in[a,b]$. Mostriamo che $c$ è interno escludendo i bordi:

- $g'(a)<0$ significa $\lim_{h\to 0^+}\frac{g(a+h)-g(a)}{h}<0$: per la permanenza del segno esiste $h>0$ con $g(a+h)<g(a)$, quindi $a$ **non** è punto di minimo;
- $g'(b)>0$ significa che il rapporto sinistro in $b$ è definitivamente positivo: esiste $h>0$ con $g(b-h)<g(b)$, quindi nemmeno $b$ è di minimo.

Dunque $c\in(a,b)$, interno, di minimo, con $g$ derivabile: **Fermat** dà $g'(c)=0$, cioè $f'(c)=\lambda$. $\square$

<!-- TODO FIGURA:
Pannello sinistro: grafico di g(x)=f(x)-lambda*x su [a,b] che parte scendendo (g'(a)<0), ha il minimo interno in c con tangente orizzontale, e arriva salendo (g'(b)>0); frecce sui bordi che mostrano perche' il minimo non puo' stare agli estremi. Pannello destro: grafico della derivata f'(x) di x^2 sin(1/x) vicino a 0, oscillante tra circa -1 e 1 con il valore f'(0)=0 marcato: discontinua ma senza salti, coerente con Darboux.
Obiettivo didattico: visualizzare la dimostrazione di Darboux (minimo interno forzato dai segni della derivata ai bordi + Fermat) e il tipo di discontinuita' che una derivata puo' avere.
-->

#### **3.1 Conseguenze strutturali**

1. **Una derivata non può avere discontinuità a salto** (né eliminabili): se $f'$ avesse limiti laterali finiti e diversi in $x_0$, i valori intermedi tra i due laterali verrebbero saltati in ogni intorno, contro Darboux. (Con il teorema del limite della derivata, Lezione 3, i laterali di $f'$, se esistono, coincidono con le derivate laterali: un salto di $f'$ renderebbe $f$ non derivabile in $x_0$.) Le uniche discontinuità possibili per una derivata sono **oscillatorie** — esattamente il tipo di $2x\sin\frac 1x-\cos\frac 1x$;
2. **non ogni funzione è una derivata**: la funzione segno, avendo un salto, non è la derivata di nessuna funzione su un intervallo contenente $0$ — un'informazione che in M12 delimiterà quali funzioni ammettono primitive;
3. $f'$ soddisfa i valori intermedi **senza essere continua**: Darboux è il secondo esempio, dopo $\sin\frac 1x$ completata (M08/UD5/L4), che la proprietà dei valori intermedi **non** caratterizza la continuità.

---
### **4. Errori frequenti**

- ❌ Usare la forma quoziente di Cauchy senza $g'\neq 0$ (o "dimostrarla" dividendo due applicazioni separate di Lagrange: i punti $c$ dei due teoremi sono **diversi**! L'ausiliaria incrociata serve proprio a ottenere lo **stesso** $c$).
- ❌ Confondere il teorema di Cauchy col criterio di Cauchy (successioni/limiti).
- ❌ Dedurre da Darboux che $f'$ è continua: la proprietà dei valori intermedi è più debole.
- ❌ Cercare una primitiva della funzione segno o della parte intera su un intervallo che contiene la discontinuità: Darboux lo vieta.
- ❌ Applicare de l'Hôpital in M09: la regola arriva in M10, qui c'è solo il suo motore.

---
### **5. Esercizi**

**Esercizio 1.** Applica Cauchy a $f(x)=x^3$, $g(x)=x^2$ su $[1,2]$ e trova $c$.

*Soluzione.* Quoziente lecito ($g'=2x\neq 0$ su $(1,2)$): $\frac{8-1}{4-1}=\frac{7}{3}$ e $\frac{f'(c)}{g'(c)}=\frac{3c^2}{2c}=\frac{3c}{2}$: $c=\frac{14}{9}\in(1,2)$ ✓.

**Esercizio 2.** Dimostra che non esiste alcuna $f$ derivabile su $\mathbb{R}$ con $f'(x)=\lfloor x\rfloor$.

*Soluzione.* $\lfloor x\rfloor$ ha discontinuità a salto negli interi: per la conseguenza 1 di Darboux non può essere una derivata su alcun intervallo contenente un intero.

**Esercizio 3.** Sia $f$ derivabile su $[0,1]$ con $f'(0)=-1$ e $f'(1)=2$. Dimostra che esiste $c$ con $f'(c)=0$ e che $f$ ha un minimo interno.

*Soluzione.* Darboux con $\lambda=0$ dà $c$ interno con $f'(c)=0$. Per il minimo: $f$ continua su $[0,1]$ ha minimo (Weierstrass); $f'(0)<0$ e $f'(1)>0$ escludono i bordi con l'argomento della permanenza del segno (come nella prova di Darboux): il minimo è interno.

**Esercizio 4.** (Curve parametriche) Per $x(t)=t^2$, $y(t)=t^3$ su $t\in(0,+\infty)$, calcola $\frac{dy}{dx}$ nel punto $t=2$ e commenta il caso $t=0$.

*Soluzione.* $\frac{dy}{dx}=\frac{y'(t)}{x'(t)}=\frac{3t^2}{2t}=\frac{3t}{2}$: in $t=2$ vale $3$. In $t=0$: $x'(0)=y'(0)=0$, punto **non regolare** — la formula non si applica; la curva (che è $y^2=x^3$) ha nell'origine una cuspide con tangente orizzontale nel piano $(x,y)$: il fallimento della formula segnala il punto singolare, da studiare con altri mezzi.

---
### **6. Riepilogo**

> ✅ Cauchy: forma simmetrica $[f(b)-f(a)]g'(c)=[g(b)-g(a)]f'(c)$ via Rolle sull'ausiliaria incrociata (stesso $c$ per entrambe: non due Lagrange divisi); forma quoziente solo con $g'\neq 0$; Lagrange come caso $g=x$; lettura parametrica $\frac{dy}{dx}=\frac{dy/dt}{dx/dt}$ nei punti regolari; è il motore di de l'Hôpital (M10/UD2, rinvio documentato). Darboux: ogni derivata ha i valori intermedi (Weierstrass + esclusione dei bordi con la permanenza del segno + Fermat); una derivata non ha mai salti — le sue sole discontinuità sono oscillatorie — e non ogni funzione ammette primitiva (ponte verso M12).
