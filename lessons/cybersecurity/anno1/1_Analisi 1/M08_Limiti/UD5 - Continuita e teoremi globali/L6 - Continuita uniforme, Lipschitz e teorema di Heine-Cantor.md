# **M8 UD5 Lezione 6 - Continuità uniforme, funzioni Lipschitz e teorema di Heine-Cantor**

### **1. Obiettivo della lezione**

Nella continuità puntuale il $\delta$ può cambiare da punto a punto. Quando un **unico** $\delta$ funziona per tutto il dominio si parla di **continuità uniforme**: una proprietà globale, più forte, che sarà indispensabile per la teoria dell'integrale (M12). Studiamo la definizione, il confronto con la continuità puntuale, le classi Lipschitz e Hölder, e il teorema di **Heine-Cantor** che chiude il cerchio con gli intervalli chiusi e limitati.

---
### **2. Definizione e confronto dei quantificatori**

**Definizione.** $f:A\to\mathbb{R}$ è **uniformemente continua** su $A$ se

$$
\forall\varepsilon>0\ \exists\delta>0\ \forall x,y\in A:\quad |x-y|<\delta \;\Rightarrow\; |f(x)-f(y)|<\varepsilon
$$

Confrontiamo con la continuità **in ogni punto** di $A$:

$$
\forall x_0\in A\ \forall\varepsilon>0\ \exists\delta>0\ \forall x\in A:\quad |x-x_0|<\delta \;\Rightarrow\; |f(x)-f(x_0)|<\varepsilon
$$

La differenza è **solo la posizione del quantificatore sul punto**: nella versione puntuale $\delta=\delta(\varepsilon,x_0)$ può dipendere dal punto; in quella uniforme il punto viene **dopo** $\exists\delta$, quindi $\delta=\delta(\varepsilon)$ deve funzionare per **tutte le coppie** contemporaneamente. Ne segue subito:

$$
\text{uniformemente continua} \;\Longrightarrow\; \text{continua in ogni punto}
$$

(basta fissare $y=x_0$). Il viceversa è **falso**, come mostrano i prossimi esempi.

---
### **3. Esempi e controesempi fondamentali**

#### **3.1 $x^2$ su $\mathbb{R}$: continua ma non uniformemente**

Su tutto $\mathbb{R}$, $x^2$ non è uniformemente continua. Neghiamo la definizione con $\varepsilon_0=1$: per ogni $\delta>0$ scegliamo

$$
x=\frac{1}{\delta},\qquad y=\frac{1}{\delta}+\frac{\delta}{2}
$$

Allora $|x-y|=\frac{\delta}{2}<\delta$, ma

$$
|x^2-y^2| = |x+y|\,|x-y| = \left(\frac{2}{\delta}+\frac{\delta}{2}\right)\frac{\delta}{2} = 1+\frac{\delta^2}{4} > 1 = \varepsilon_0
$$

Il motivo geometrico: la pendenza di $x^2$ **cresce senza limite**, e coppie vicine ad ascisse grandi hanno immagini lontane. Nessun $\delta$ universale sopravvive.

#### **3.2 $x^2$ su un intervallo limitato: uniformemente continua**

Su $[-M,M]$:

$$
|x^2-y^2| = |x+y|\,|x-y| \le 2M\,|x-y|
$$

quindi $\delta=\frac{\varepsilon}{2M}$ funziona per tutte le coppie: uniformità ripristinata (è un caso Lipschitz, punto 4). La stessa funzione può essere uniformemente continua o no **a seconda del dominio**: l'uniformità è una proprietà della coppia (funzione, insieme).

#### **3.3 $\frac{1}{x}$ su $(0,1)$: continua, limitato il dominio, ma non uniforme**

Con $\varepsilon_0=1$: per ogni $\delta\in(0,1)$ i punti $x=\delta$, $y=\frac{\delta}{2}$ distano $\frac{\delta}{2}<\delta$ ma

$$
\left|\frac{1}{x}-\frac{1}{y}\right| = \frac{1}{\delta} \ge 1
$$

Qui il guasto non è all'infinito ma **vicino al bordo** $0$, dove la funzione esplode: il dominio limitato non basta, serve anche la chiusura (e $\frac 1x$ su $(0,1]$ non è nemmeno prolungabile con continuità a $0$).

#### **3.4 $\sqrt{x}$ su $[0,+\infty)$: uniforme senza Lipschitz**

Dalla disuguaglianza $\left|\sqrt{x}-\sqrt{y}\right|\le\sqrt{|x-y|}$ (si verifica elevando al quadrato: $|x-y| = \left|\sqrt x-\sqrt y\right|\left(\sqrt x+\sqrt y\right)\ge\left|\sqrt x-\sqrt y\right|^2$), dato $\varepsilon$ basta $\delta=\varepsilon^2$: uniforme su tutto il dominio, pur avendo pendenza illimitata vicino a $0$ (non Lipschitz: punto 4). È l'esempio canonico di funzione **Hölder di esponente $\frac 12$**.

<!-- TODO FIGURA:
Due pannelli. Sinistra: grafico di x^2 con due coppie di punti alla stessa distanza orizzontale h, una vicino all'origine (immagini vicine) e una ad ascissa grande (immagini lontanissime), con le fasce verticali uguali e le fasce orizzontali di ampiezza molto diversa. Destra: grafico di 1/x su (0,1) con la stessa costruzione vicino al bordo 0.
Obiettivo didattico: mostrare perché lo stesso delta non può funzionare ovunque quando la pendenza esplode: all'infinito per x^2, sul bordo per 1/x.
-->

---
### **4. Funzioni Lipschitz e Hölder**

**Definizione.** $f:A\to\mathbb{R}$ è **Lipschitz** (di costante $L\ge 0$) se

$$
|f(x)-f(y)| \le L\,|x-y| \qquad \forall x,y\in A
$$

**Proposizione.** Ogni funzione Lipschitz è uniformemente continua.

*Dimostrazione.* Se $L=0$, $f$ è costante e ogni $\delta$ va bene. Se $L>0$: dato $\varepsilon$, la scelta $\delta=\frac{\varepsilon}{L}$ dà $|f(x)-f(y)|\le L|x-y|<L\delta=\varepsilon$. $\square$

Esempi già incontrati: le affini ($L=|m|$), $\sin$ e $\cos$ ($L=1$: stima di prostaferesi, Lezione 2), $|x|$ ($L=1$, triangolare inversa), $x^2$ **su un limitato** ($L=2M$).

**Definizione (approfondimento).** $f$ è **Hölder di esponente $\alpha\in(0,1]$** se esiste $C>0$ con

$$
|f(x)-f(y)| \le C\,|x-y|^{\alpha} \qquad \forall x,y\in A
$$

Per $\alpha=1$ si ritrova Lipschitz; per ogni $\alpha$, la scelta $\delta=\left(\frac{\varepsilon}{C}\right)^{1/\alpha}$ mostra che Hölder $\Rightarrow$ uniformemente continua. La scala è strettamente ordinata:

$$
\text{Lipschitz} \subsetneq \text{Hölder} \subsetneq \text{uniformemente continua} \subsetneq \text{continua}
$$

con separatori: $\sqrt x$ su $[0,1]$ (Hölder $\frac 12$, non Lipschitz: se fosse $|\sqrt x-0|\le L|x-0|$ si avrebbe $\frac{1}{\sqrt x}\le L$ per $x\to 0^+$, assurdo); $x^2$ su $\mathbb{R}$ (continua, non uniforme). Un separatore tra Hölder e uniforme, come $\frac{1}{\ln x}$ vicino a $0$, è fuori dal nostro perimetro: basti sapere che l'inclusione è stretta.

> ⚠️ Non anticipiamo il criterio "derivata limitata $\Rightarrow$ Lipschitz": richiede il teorema di Lagrange (M10). Qui le costanti di Lipschitz si trovano con stime algebriche dirette, come fatto per $\sin$ e per $x^2$ sul limitato.

---
### **5. Teorema di Heine-Cantor**

**Teorema (Heine-Cantor).** Ogni funzione continua su un intervallo **chiuso e limitato** $[a,b]$ è uniformemente continua su $[a,b]$.

*Dimostrazione (per assurdo, con successioni).* Supponiamo $f$ continua ma non uniformemente continua: negando la definizione, esiste $\varepsilon_0>0$ tale che per ogni $\delta>0$ c'è una coppia che viola la conclusione. Applichiamo con $\delta=\frac 1n$: esistono $x_n,y_n\in[a,b]$ con

$$
|x_n-y_n|<\frac{1}{n} \qquad\text{e}\qquad |f(x_n)-f(y_n)|\ge\varepsilon_0
$$

La successione $(x_n)$ è limitata: per **Bolzano-Weierstrass** (M05) esiste $x_{n_k}\to c\in[a,b]$ (chiusura). Anche $y_{n_k}\to c$: infatti $|y_{n_k}-c|\le|y_{n_k}-x_{n_k}|+|x_{n_k}-c|<\frac{1}{n_k}+|x_{n_k}-c|\to 0$.

Per il criterio sequenziale di continuità in $c$ (Lezione 1):

$$
f(x_{n_k})\to f(c) \qquad\text{e}\qquad f(y_{n_k})\to f(c)
$$

quindi $|f(x_{n_k})-f(y_{n_k})|\to 0$, in contraddizione con $|f(x_{n_k})-f(y_{n_k})|\ge\varepsilon_0>0$ per ogni $k$. $\square$

Il ruolo delle ipotesi è lo stesso di Weierstrass: la **limitatezza** di $[a,b]$ consente l'estrazione di Bolzano-Weierstrass, la **chiusura** trattiene il punto limite $c$ dentro il dominio, dove la continuità è disponibile. I controesempi 3.1 e 3.3 mostrano che senza chiusura o senza limitatezza il teorema cade.

**Perché ci interessa**: in M12 l'integrabilità delle funzioni continue su $[a,b]$ si dimostrerà proprio con l'uniformità (controllo **simultaneo** dell'oscillazione su tutti i sottointervalli di una suddivisione fine). Heine-Cantor è il ponte tra la teoria di questo modulo e quella dell'integrale.

---
### **6. Lettura applicativa**

- **Sensibilità e robustezza**: per una $f$ uniformemente continua, la precisione $\delta$ sull'input che garantisce precisione $\varepsilon$ sull'output è **la stessa ovunque**: si può fissare una tolleranza di misura una volta per tutte. Per una $f$ solo continua ($x^2$ su range non limitato), la tolleranza necessaria degrada al crescere dell'input;
- **Lipschitz come modulo di amplificazione**: la costante $L$ maggiora il fattore con cui $f$ amplifica gli errori: $|f(x+e)-f(x)|\le L|e|$. Nelle applicazioni numeriche una $L$ piccola significa problemi ben condizionati; funzioni a soglia (gradini) hanno $L=\infty$ nei salti e amplificano arbitrariamente errori piccoli attorno alla soglia — il motivo per cui i modelli digitali con discontinuità sono delicati da testare vicino ai bordi di decisione;
- il floating point aggiunge rumore limitato all'input: l'uniformità è esattamente la proprietà che trasforma "rumore piccolo" in "errore sull'output controllato uniformemente".

---
### **7. Errori frequenti**

- ❌ Scrivere la definizione uniforme con $\delta$ dopo i punti ($\forall x,y\ \exists\delta$): così si riottiene la continuità puntuale; l'ordine dei quantificatori **è** la definizione.
- ❌ Credere che continuità + dominio limitato bastino ($\frac 1x$ su $(0,1)$ smentisce: serve anche la chiusura).
- ❌ Credere che uniforme $\Rightarrow$ Lipschitz ($\sqrt x$ smentisce).
- ❌ Applicare Heine-Cantor su domini non chiusi o non limitati.
- ❌ Dire che $x^2$ "non è uniformemente continua" senza specificare il dominio: su ogni $[-M,M]$ lo è.
- ❌ Usare la derivata per stimare la costante di Lipschitz in questo modulo (strumento di M10).

---
### **8. Esercizi**

**Esercizio 1.** Stabilisci se $f(x)=\sin(x^2)$ è uniformemente continua su $\mathbb{R}$.

*Soluzione.* No. Con $\varepsilon_0=1$: i punti $x_n=\sqrt{\frac{\pi}{2}+2n\pi}$ e $y_n=\sqrt{2n\pi}$ soddisfano $f(x_n)-f(y_n)=1-0=1$, ma $x_n-y_n=\frac{\pi/2}{x_n+y_n}\to 0$: coppie arbitrariamente vicine con immagini a distanza $1$ (stessa struttura della negazione di 3.1). Nota: $f$ è limitata — la limitatezza non implica l'uniformità.

**Esercizio 2.** Dimostra che $f(x)=\dfrac{x}{1+x^2}$ è Lipschitz su $\mathbb{R}$ con una costante esplicita.

*Soluzione.* Un conto diretto dà $f(x)-f(y) = \dfrac{(x-y)(1-xy)}{(1+x^2)(1+y^2)}$. Stimiamo il secondo fattore: $|1-xy|\le 1+|x||y|\le 1+\frac{x^2+y^2}{2}$ (disuguaglianza $|x||y|\le\frac{x^2+y^2}{2}$, da $(|x|-|y|)^2\ge 0$), mentre il denominatore soddisfa $(1+x^2)(1+y^2)=1+x^2+y^2+x^2y^2\ge 1+x^2+y^2$. Poiché $1+\frac{x^2+y^2}{2}\le 1+x^2+y^2$, il quoziente è $\le 1$ e quindi $|f(x)-f(y)|\le|x-y|$: Lipschitz con $L=1$.

**Esercizio 3.** Dimostra che una funzione uniformemente continua su $(0,1]$ e con $\lim_{x\to 0^+}f(x)$ esistente finito si estende con continuità a $[0,1]$; deduci che $\sin\frac 1x$ non è uniformemente continua su $(0,1]$.

*Soluzione.* L'estensione col valore del limite è continua (Lezione 3). Se $\sin\frac 1x$ fosse uniformemente continua, per il criterio di Cauchy (UD1/L3E) le coppie vicine avrebbero immagini vicine e il limite in $0^+$ esisterebbe finito; ma non esiste (UD1/L3E): assurdo. (In generale: uniforme continuità su un limitato $\Rightarrow$ esistenza dei limiti finiti ai bordi.)

**Esercizio 4.** Vero o falso: la somma di due funzioni uniformemente continue su $A$ è uniformemente continua; il prodotto pure.

*Soluzione.* Somma: vero ($\frac\varepsilon2+\frac\varepsilon2$ col minimo dei $\delta$). Prodotto: falso in generale — $f(x)=g(x)=x$ sono uniformi su $\mathbb{R}$ (Lipschitz $L=1$) ma $x^2$ no (3.1). Su domini limitati, con fattori limitati, il prodotto torna uniforme.

---
### **9. Riepilogo**

> ✅ Uniforme: un solo $\delta(\varepsilon)$ per tutte le coppie — il quantificatore sul punto passa **dopo** $\exists\delta$. Implicazioni strette: Lipschitz $\Rightarrow$ Hölder $\Rightarrow$ uniforme $\Rightarrow$ continua, con separatori $\sqrt x$ (uniforme/Hölder $\frac 12$ ma non Lipschitz) e $x^2$ su $\mathbb{R}$, $\frac 1x$ su $(0,1)$ (continue non uniformi: pendenza che esplode all'infinito o al bordo). Heine-Cantor: su $[a,b]$ chiuso e limitato la continuità è automaticamente uniforme (assurdo + coppie $\frac 1n$-vicine + Bolzano-Weierstrass + criterio sequenziale); è il risultato che M12 userà per integrare le funzioni continue.
