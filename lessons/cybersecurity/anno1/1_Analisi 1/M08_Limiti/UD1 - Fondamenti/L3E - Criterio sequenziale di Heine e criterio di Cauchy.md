# **M8 UD1 Lezione 3E - Criterio sequenziale di Heine e criterio di Cauchy**

### **1. Obiettivo della lezione**

In M05 abbiamo costruito una teoria completa dei limiti di **successioni**. Qui gettiamo il ponte annunciato in M05 (UD3, L1): il **criterio sequenziale di Heine** dice che il limite di funzione si può leggere interamente attraverso le successioni, e viceversa. Il criterio ha due usi micidiali: *importare* in blocco i teoremi di M05, e *confutare* limiti esibendo due successioni con esiti diversi. Chiudiamo con il **criterio di Cauchy** per i limiti di funzione, che caratterizza l'esistenza del limite senza conoscerne il valore.

---
### **2. Il criterio sequenziale di Heine**

**Teorema (Heine).** Sia $f:A\to\mathbb{R}$, sia $x_0$ un punto di accumulazione di $A$ e sia $L\in\mathbb{R}$. Sono equivalenti:

1. $\displaystyle\lim_{x\to x_0}f(x)=L$;
2. per **ogni** successione $(x_n)$ con $x_n\in A$, $x_n\neq x_0$ per ogni $n$, e $x_n\to x_0$, si ha $f(x_n)\to L$.

*Dimostrazione.*

**(1 $\Rightarrow$ 2).** Sia $(x_n)$ come in (2) e fissiamo $\varepsilon>0$. Per l'ipotesi (1) esiste $\delta>0$ tale che

$$
x\in A,\ 0<|x-x_0|<\delta \;\Rightarrow\; |f(x)-L|<\varepsilon
$$

Poiché $x_n\to x_0$, per la definizione $\varepsilon$–$N$ di M05 (applicata con "$\varepsilon$" $=\delta$) esiste $N$ tale che $|x_n-x_0|<\delta$ per ogni $n\ge N$; inoltre $x_n\neq x_0$, quindi $0<|x_n-x_0|<\delta$. Ne segue $|f(x_n)-L|<\varepsilon$ per ogni $n\ge N$: esattamente $f(x_n)\to L$.

**(2 $\Rightarrow$ 1).** Dimostriamo la contronominale: supponiamo che (1) sia **falsa** e costruiamo una successione che viola (2). La negazione della definizione (Lezione 3B, punto 6) fornisce $\varepsilon_0>0$ tale che

$$
\forall\delta>0\ \exists x\in A:\quad 0<|x-x_0|<\delta \ \text{ e } \ |f(x)-L|\ge\varepsilon_0
$$

Applichiamola con $\delta=\dfrac{1}{n}$ per ciascun $n\ge 1$: otteniamo punti $x_n\in A$ con

$$
0<|x_n-x_0|<\frac{1}{n} \qquad\text{e}\qquad |f(x_n)-L|\ge\varepsilon_0
$$

La successione $(x_n)$ così costruita sta in $A$, evita $x_0$ e converge a $x_0$ (per i carabinieri di M05, da $|x_n-x_0|<\frac 1n\to 0$). Ma $f(x_n)\not\to L$, perché tutti i valori distano da $L$ almeno $\varepsilon_0$. Quindi (2) è falsa. $\square$

> 📌 Il verso difficile è il secondo, e il motore è la **successione testimone** estratta dalla negazione quantificata con i raggi $\frac{1}{n}$. È lo stesso schema che riuseremo per Weierstrass e Heine-Cantor (UD5).

Il criterio si estende parola per parola:

- ai **limiti laterali** (successioni con $x_n>x_0$, rispettivamente $x_n<x_0$);
- ai **limiti estesi** ($L=\pm\infty$: si chiede $f(x_n)\to\pm\infty$);
- ai **limiti all'infinito**: $\lim_{x\to+\infty}f(x)=L$ se e solo se $f(x_n)\to L$ per ogni successione $x_n\to+\infty$ in $A$ (qui il vincolo $x_n\neq x_0$ scompare, sostituito dal fatto che $x_n$ supera definitivamente ogni soglia). La prova del verso difficile usa i raggi "$R=n$": si scelgono $x_n>n$ con $|f(x_n)-L|\ge\varepsilon_0$.

---
### **3. Primo uso: importare i teoremi di M05**

Con Heine, ogni teorema sui limiti di successioni si **trasferisce** ai limiti di funzione senza rifare le stime. Esempio: l'unicità del limite di funzione segue dall'unicità per successioni (M05): se $f\to L$ e $f\to M$, una qualunque successione testimone $x_n\to x_0$ dà $f(x_n)\to L$ e $f(x_n)\to M$, quindi $L=M$. Lo stesso vale per algebra dei limiti, confronto, carabinieri: le versioni funzionali (Lezioni 3D e 3F) si possono dimostrare o direttamente in $\varepsilon$–$\delta$, come abbiamo fatto, o via Heine da M05 — due strade legittime, e all'orale conviene saperle entrambe.

È anche il ponte annunciato in M05/UD3/L1 sull'**aritmetizzazione**: le regole di calcolo con $\pm\infty$ dimostrate per le successioni valgono per le funzioni.

---
### **4. Secondo uso: confutare limiti**

Per negare l'esistenza del limite basta **una coppia** di successioni ben scelte:

> se esistono $(x_n)$ e $(y_n)$, entrambe in $A\setminus\{x_0\}$ e tendenti a $x_0$, con $f(x_n)\to\ell_1$ e $f(y_n)\to\ell_2$ e $\ell_1\neq\ell_2$, allora $\lim_{x\to x_0}f(x)$ non esiste (nemmeno esteso).

Infatti se il limite fosse $\ell$, per Heine ogni successione darebbe $f\to\ell$, e l'unicità del limite di successione forzerebbe $\ell_1=\ell=\ell_2$.

#### **4.1 L'esempio centrale: $\sin\frac{1}{x}$ per $x\to 0$**

Costruiamo due successioni esplicite che tendono a $0^+$:

$$
x_n=\frac{1}{2n\pi} \qquad\text{e}\qquad y_n=\frac{1}{\frac{\pi}{2}+2n\pi}
$$

Allora:

$$
\sin\frac{1}{x_n} = \sin(2n\pi) = 0 \to 0,
\qquad
\sin\frac{1}{y_n} = \sin\left(\frac{\pi}{2}+2n\pi\right) = 1 \to 1
$$

Due esiti diversi ($0\neq 1$): $\displaystyle\lim_{x\to 0}\sin\frac{1}{x}$ **non esiste**, e non esistono nemmeno i laterali (le successioni usate sono tutte a destra di $0$; specularmente a sinistra). Con la scelta $z_n=\frac{1}{-\frac{\pi}{2}+2n\pi}$ si ottiene il valore $-1$: in effetti ogni $\ell\in[-1,1]$ è raggiungibile lungo una successione opportuna.

<!-- TODO FIGURA:
Grafico di sin(1/x) vicino a 0 con evidenziati i punti delle due successioni: x_n = 1/(2 n pi) sui passaggi per lo zero (pallini su y=0) e y_n = 1/(pi/2 + 2 n pi) sulle creste (pallini su y=1), con frecce che mostrano le due sottofamiglie che si accumulano in 0 a quote diverse.
Obiettivo didattico: visualizzare il criterio sequenziale come selezione di cammini discreti dentro il grafico: due cammini, due limiti diversi, quindi nessun limite di funzione.
-->

#### **4.2 Altri usi rapidi**

- **Limiti laterali via successioni**: $\lim_{x\to 0^+}\frac{|x|}{x}=1$ perché ogni successione positiva infinitesima dà $f(x_n)=1$; il confronto con le successioni negative ($f=-1$) riprova il salto della Lezione 3C;
- **oscillazione all'infinito**: $\sin x$ per $x\to+\infty$ non ha limite: $x_n=n\pi$ dà $0$, $y_n=\frac{\pi}{2}+2n\pi$ dà $1$;
- **dimostrare** un limite con Heine è invece scomodo (bisogna controllare **tutte** le successioni): per l'esistenza si usano definizione e teoremi; Heine si usa per **negare** o per **trasferire**.

> ⚠️ Attenzione all'errore inverso: verificare $f(x_n)\to L$ lungo **una** successione (o cento) non dimostra il limite. $\sin\frac{1}{x}$ lungo $x_n=\frac{1}{2n\pi}$ tende a $0$, eppure il limite non esiste. Il quantificatore in (2) è "per ogni successione".

---
### **5. Il criterio di Cauchy per i limiti di funzione**

Come per le successioni (M05, UD2), esiste una caratterizzazione dell'esistenza del limite **senza menzionare il valore** $L$.

**Teorema (criterio di Cauchy).** Sia $f:A\to\mathbb{R}$ e $x_0$ di accumulazione per $A$. Il limite $\displaystyle\lim_{x\to x_0}f(x)$ esiste **finito** se e solo se:

$$
\forall\varepsilon>0\ \exists\delta>0\ \forall x,y\in A:\quad
0<|x-x_0|<\delta,\ 0<|y-x_0|<\delta
\;\Longrightarrow\;
|f(x)-f(y)|<\varepsilon
$$

*Dimostrazione.*

**(Necessità.)** Se $f\to L$, fissato $\varepsilon>0$ prendiamo il $\delta$ della definizione relativo a $\frac{\varepsilon}{2}$. Per $x,y$ entrambi nella finestra puntata:

$$
|f(x)-f(y)| \le |f(x)-L|+|L-f(y)| < \frac{\varepsilon}{2}+\frac{\varepsilon}{2}=\varepsilon
$$

**(Sufficienza.)** Sia $(x_n)$ una qualunque successione in $A\setminus\{x_0\}$ con $x_n\to x_0$ (esiste perché $x_0$ è di accumulazione: scegli $x_n\in U_{1/n}^\ast(x_0)\cap A$). Mostriamo che $(f(x_n))$ è una successione di Cauchy: dato $\varepsilon>0$, sia $\delta$ quello della condizione; poiché $x_n\to x_0$ con $x_n\neq x_0$, esiste $N$ tale che $0<|x_n-x_0|<\delta$ per ogni $n\ge N$; per $n,m\ge N$ la condizione dà $|f(x_n)-f(x_m)|<\varepsilon$. Per la **completezza di $\mathbb{R}$** (M05: ogni successione di Cauchy converge), esiste $L=\lim_n f(x_n)\in\mathbb{R}$.

Resta da vedere che il limite lungo **ogni altra** successione ammissibile $(y_n)$ è lo stesso $L$: la successione "intrecciata" $x_1,y_1,x_2,y_2,\dots$ è ancora ammissibile (sta in $A\setminus\{x_0\}$ e tende a $x_0$), quindi per l'argomento appena fatto $f$ lungo di essa è di Cauchy e converge; ma una successione convergente ha tutte le sottosuccessioni con lo stesso limite (M05), e le due sottosuccessioni $f(x_n)$, $f(y_n)$ devono condividerlo: $f(y_n)\to L$. Per il criterio di Heine (verso 2 $\Rightarrow$ 1), $\lim_{x\to x_0}f(x)=L$. $\square$

Osservazioni:

- la **completezza del codominio $\mathbb{R}$** è il cuore della sufficienza: in $\mathbb{Q}$ il criterio fallirebbe, esattamente come per le successioni;
- rispetto alla definizione ordinaria, la condizione di Cauchy confronta i valori **tra loro** anziché con un candidato $L$: utile quando il candidato non si sa indovinare;
- l'ipotesi che $x_0$ sia di accumulazione garantisce che le successioni test esistano;
- il criterio vale identico per $x\to\pm\infty$ (finestre $x,y>R$) e fornisce un test **negativo** comodo: per $\sin\frac 1x$ in $0$, i punti $x_n,y_n$ del punto 4.1 cadono definitivamente in ogni finestra e $|f(x_n)-f(y_m)|$ arriva a $1$: la condizione di Cauchy fallisce con $\varepsilon_0=\frac 12$, quindi nessun limite finito esiste.

---
### **6. Errori frequenti**

- ❌ Dimostrare un limite testando una sola successione (punto 4, avvertenza).
- ❌ Dimenticare il vincolo $x_n\neq x_0$ nel criterio puntato: con successioni che toccano $x_0$ si sta testando un'altra proprietà (la continuità: UD5, Lezione 1, dove il vincolo cade).
- ❌ Usare Heine per limiti estesi pretendendo $f(x_n)$ di Cauchy: il criterio di Cauchy caratterizza solo i limiti **finiti**.
- ❌ Costruire la successione testimone senza garantire che stia nel dominio: i punti vanno presi in $A$.

---
### **7. Esercizi**

**Esercizio 1.** Dimostra con il criterio sequenziale che $\displaystyle\lim_{x\to 0}\cos\frac{1}{x}$ non esiste.

*Soluzione.* $x_n=\frac{1}{2n\pi}$ dà $\cos\to 1$; $y_n=\frac{1}{(2n+1)\pi}$ dà $\cos\to -1$.

**Esercizio 2.** La funzione di Dirichlet $D(x)=\begin{cases}1, & x\in\mathbb{Q} \\ 0, & x\notin\mathbb{Q}\end{cases}$ ammette limite in qualche punto?

*Soluzione.* No, in nessun $x_0$: per densità di razionali e irrazionali (M02) esistono $x_n\to x_0$ razionali ($D\to 1$) e $y_n\to x_0$ irrazionali ($D\to 0$).

**Esercizio 3.** Usando Heine e M05, calcola $\displaystyle\lim_{x\to+\infty}\left(1+\frac{1}{x}\right)^{x}$ lungo la successione $x_n=n$ e spiega perché questo **non** basta a concludere il limite di funzione.

*Soluzione.* Lungo $x_n=n$ si ottiene la successione di M05 che definisce $e$. Non basta perché Heine richiede tutte le successioni divergenti, non solo gli interi: la prova completa (con parte intera e carabinieri) è nella UD3, Lezione 1.

**Esercizio 4.** Dimostra che $f(x)=\frac{1}{x}$ non soddisfa il criterio di Cauchy per $x\to 0^+$.

*Soluzione.* Con $x_n=\frac{1}{n}$, $y_n=\frac{1}{2n}$: entrambi in ogni finestra $(0,\delta)$ definitivamente, ma $|f(x_n)-f(y_n)|=n\to+\infty$: la condizione fallisce per qualunque $\varepsilon$. (Coerente: il limite destro è $+\infty$, non finito.)

---
### **8. Riepilogo**

> ✅ Heine: limite di funzione $\iff$ stesso limite lungo **ogni** successione ammissibile ($x_n\in A$, $x_n\neq x_0$, $x_n\to x_0$); il verso difficile costruisce la successione testimone con i raggi $\frac 1n$ dalla negazione. Usi: trasferire i teoremi di M05 e confutare limiti con due successioni discordi ($\sin\frac 1x$ ne è l'archetipo); mai per "dimostrare" con una sola successione. Cauchy: il limite finito esiste $\iff$ i valori si avvicinano **tra loro** nelle finestre puntate; la sufficienza usa la completezza di $\mathbb{R}$ via successioni. Nella UD5 il criterio sequenziale tornerà per la continuità, con la differenza chiave che le successioni potranno toccare $x_0$.
