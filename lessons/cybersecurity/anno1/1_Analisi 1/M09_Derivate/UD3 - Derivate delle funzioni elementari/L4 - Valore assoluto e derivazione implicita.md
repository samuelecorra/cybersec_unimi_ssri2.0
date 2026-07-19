# **M9 UD3 Lezione 4 - Valore assoluto e derivazione implicita**

### **1. Obiettivo della lezione**

Due tecniche che completano la cassetta degli attrezzi: la derivazione di $|g(x)|$ (con la trappola degli zeri di $g$) e la **derivazione implicita** elementare per relazioni $F(x,y)=0$, con le sue ipotesi dichiarate e i suoi limiti.

---
### **2. Derivata di $|x|$ e della composizione $|g(x)|$**

Per il valore assoluto puro (UD1/L2):

$$
\frac{d}{dx}|x| = \begin{cases} 1, & x>0\\ -1, & x<0\end{cases} = \operatorname{sgn}(x) \qquad (x\neq 0)
$$

e in $0$ la derivata non esiste (angolo). Per la composizione, con la catena:

**Teorema.** Se $g$ è derivabile in $x_0$ e $g(x_0)\neq 0$, allora $|g|$ è derivabile in $x_0$ e

$$
\big(|g|\big)'(x_0) = \operatorname{sgn}\big(g(x_0)\big)\cdot g'(x_0)
$$

*Dimostrazione.* In un intorno di $x_0$ la funzione $g$ mantiene il segno (continuità + permanenza del segno, M08), quindi $|g|=g$ oppure $|g|=-g$ localmente, e si deriva il ramo. $\square$

**Nei punti in cui $g(x_0)=0$** la formula non si applica e la situazione va analizzata con le derivate laterali del rapporto incrementale di $|g|$:

- **zero con cambio di segno e $g'(x_0)\neq 0$** (zero semplice): $|g|$ ha un **punto angoloso** — laterali $\pm|g'(x_0)|$. Esempio: $|x^2-1|$ in $x=\pm 1$ (i "rimbalzi" sul grafico di UD6/L3);
- **zero senza perdita di derivabilità**: se $g'(x_0)=0$ e lo zero è "di ordine alto", $|g|$ può restare derivabile. Esempio: $|x^2|=x^2$, derivabilissima in $0$; oppure $|x|^3=|x^3|$, derivabile in $0$ con derivata $0$ (rapporto $\frac{|h|^3}{h}=|h|^2\operatorname{sgn}(h)\to 0$);
- il caso intermedio $|x|^{2/3}$: rapporto $\frac{|h|^{2/3}}{h}=\frac{\operatorname{sgn}(h)}{|h|^{1/3}}$, laterali $\pm\infty$: **cuspide** (UD4/L1).

> ⚠️ La ricetta meccanica "$(|g|)'=\operatorname{sgn}(g)\,g'$ sempre" è quindi falsa negli zeri di $g$: lì può esserci angolo, cuspide o piena derivabilità, e decide **solo** il rapporto incrementale. Negli esercizi: spezza il dominio secondo il segno di $g$, deriva i rami, esamina gli zeri a parte.

---
### **3. Derivazione implicita: l'idea**

Alcune curve non sono grafici di funzioni ($x^2+y^2=1$ è una circonferenza), ma **localmente** — vicino a un punto della curva con tangente non verticale — si lasciano descrivere come $y=y(x)$. La derivazione implicita calcola $y'(x_0)$ **senza** esplicitare $y(x)$: si deriva l'identità $F(x,y(x))=0$ rispetto a $x$, trattando $y$ come funzione di $x$ (quindi ogni $y$ produce un fattore $y'$ per la catena), e si risolve in $y'$.

**Ipotesi dichiarate** (onestà intellettuale prima della tecnica): assumiamo che in un intorno del punto $(x_0,y_0)$ sulla curva **esista** una funzione derivabile $y(x)$ con $y(x_0)=y_0$ e $F(x,y(x))=0$. Il teorema che garantisce questa esistenza — il **teorema delle funzioni implicite (Dini)** — richiede le derivate parziali e appartiene all'analisi multivariata: qui lo **assumiamo** e lo dichiariamo, senza spacciare la procedura simbolica per universalmente valida. La formula generale $y'=-\frac{F_x}{F_y}$, che ne discende, è citata solo come anticipazione.

---
### **4. Esempio 1: la circonferenza**

$x^2+y^2=1$, punto $\left(\frac{3}{5},\frac{4}{5}\right)$. Derivando l'identità $x^2+y(x)^2=1$:

$$
2x + 2y\,y' = 0
\qquad\Longrightarrow\qquad
y' = -\frac{x}{y} \qquad (y\neq 0)
$$

Nel punto: $y'=-\frac{3/5}{4/5}=-\frac{3}{4}$. Verifica indipendente: sul ramo superiore $y=\sqrt{1-x^2}$, $y'=-\frac{x}{\sqrt{1-x^2}}=-\frac{x}{y}$ ✓. La condizione $y\neq 0$ esclude i punti $(\pm 1,0)$: lì la tangente è **verticale** e nessuna $y(x)$ derivabile esiste — il fallimento della procedura segnala il fallimento dell'ipotesi, non un difetto di calcolo.

**Bonus geometrico**: $y'=-\frac{x}{y}$ dice che la tangente è perpendicolare al raggio (il raggio ha pendenza $\frac{y}{x}$, prodotto $=-1$): la derivazione implicita ridimostra un teorema di geometria classica in due righe.

---
### **5. Esempio 2: il folium di Cartesio**

$x^3+y^3=3xy$ (curva non esprimibile globalmente come grafico), punto $\left(\frac{3}{2},\frac{3}{2}\right)$. Derivando (catena su $y^3$, prodotto su $3xy$):

$$
3x^2 + 3y^2 y' = 3y + 3x y'
\qquad\Longrightarrow\qquad
y'\,(y^2-x) = y - x^2
\qquad\Longrightarrow\qquad
y' = \frac{y-x^2}{y^2-x} \qquad (y^2\neq x)
$$

Nel punto: $y'=\frac{\frac{3}{2}-\frac{9}{4}}{\frac{9}{4}-\frac{3}{2}}=\frac{-3/4}{3/4}=-1$ — coerente con la simmetria della curva rispetto a $y=x$ (il punto sta sulla bisettrice, la tangente deve essere perpendicolare ad essa).

<!-- TODO FIGURA:
Circonferenza unitaria con punto (3/5,4/5), raggio dal centro al punto e tangente perpendicolare al raggio con pendenza -3/4; evidenziare anche i punti (±1,0) con tangenti verticali dove y'=-x/y non e' definita. Accanto, schizzo del folium x^3+y^3=3xy con il cappio nel primo quadrante, la bisettrice y=x tratteggiata e la tangente di pendenza -1 nel punto (3/2,3/2).
Obiettivo didattico: mostrare la derivazione implicita come calcolo di tangenti a curve che non sono grafici, e i punti critici dove l'ipotesi di esplicitabilita' cade.
-->

---
### **6. Procedura operativa (con i controlli)**

1. verifica che il punto stia **sulla curva** ($F(x_0,y_0)=0$: dimenticarlo è l'errore n.1);
2. deriva l'identità membro a membro rispetto a $x$, con fattore $y'$ su ogni termine contenente $y$ (catena) e regola del prodotto sui termini misti;
3. raccogli $y'$ e risolvi;
4. controlla che il denominatore ottenuto non si annulli nel punto (se si annulla: tangente verticale o punto singolare — la procedura non si applica);
5. valuta nel punto.

---
### **7. Errori frequenti**

- ❌ Dimenticare il fattore $y'$ derivando i termini in $y$ (scrivere $2y$ invece di $2y\,y'$).
- ❌ Applicare la formula $\left(|g|\right)'=\operatorname{sgn}(g)g'$ negli zeri di $g$.
- ❌ Usare la derivazione implicita in punti non appartenenti alla curva, o dove il denominatore si annulla.
- ❌ Credere che la procedura simbolica dimostri l'esistenza di $y(x)$: l'esistenza è un'ipotesi (Dini), la procedura calcola solo il valore.
- ❌ Nel prodotto $3xy$: derivare come se $y$ fosse costante.

---
### **8. Esercizi**

**Esercizio 1.** Studia la derivabilità di $f(x)=|x^2-4|$ su $\mathbb{R}$.

*Soluzione.* Per $|x|>2$: $f=x^2-4$, $f'=2x$; per $|x|<2$: $f=4-x^2$, $f'=-2x$; in $x=\pm 2$: zeri semplici di $g=x^2-4$ con $g'(\pm2)=\pm4\neq0$: punti angolosi con laterali $\mp 4,\pm 4$ (in $2$: sinistra $-4$, destra $4$).

**Esercizio 2.** Trova la tangente alla curva $x^2+xy+y^2=7$ nel punto $(1,2)$.

*Soluzione.* Verifica: $1+2+4=7$ ✓. Derivando: $2x+y+xy'+2yy'=0$, $y'=-\frac{2x+y}{x+2y}=-\frac{4}{5}$. Tangente: $y=2-\frac{4}{5}(x-1)$.

**Esercizio 3.** Per la circonferenza $x^2+y^2=25$, trova i punti a tangente orizzontale e verticale usando $y'=-\frac{x}{y}$.

*Soluzione.* Orizzontale: $x=0$ → $(0,\pm5)$. Verticale: $y=0$ → $(\pm5,0)$ (dove la formula perde senso, coerentemente).

**Esercizio 4.** $f(x)=x\,|x-1|$: classifica l'eventuale punto critico di derivabilità.

*Soluzione.* Per $x>1$: $f=x^2-x$, $f'=2x-1\to 3$ in $1^+$; per $x<1$: $f=x-x^2$, $f'=1-2x\to -1$ in $1^-$. Continua in $1$, laterali $3\neq-1$: punto angoloso.

---
### **9. Riepilogo**

> ✅ $|g|$ si deriva col segno ($\operatorname{sgn}(g)\,g'$) **solo dove $g\neq 0$**; negli zeri decide il rapporto incrementale: angolo (zero semplice), cuspide ($|x|^{2/3}$) o piena derivabilità ($x^2$, $|x|^3$). Derivazione implicita: assumendo (e dichiarando) l'esistenza locale di $y(x)$ — teorema di Dini rinviato all'analisi multivariata — si deriva $F(x,y(x))=0$ con la catena e si risolve in $y'$, controllando appartenenza del punto e denominatore; i fallimenti della formula segnalano tangenti verticali o punti singolari.
