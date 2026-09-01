# **L3 - Formulario M3: Somme di variabili, campionamento e inferenza**

> 📌 Copre [M3 UD1-UD4](../M3_Argomenti_avanzati/). Argomenti: somma di variabili aleatorie e convoluzione, covarianza, funzioni generatrici, Teorema del Limite Centrale, distribuzioni campionarie, **statistica descrittiva su campione** (l'argomento più frequente in assoluto: 7 appelli su 8), stima e intervalli di confidenza, CV/covarianza/Pearson/skewness.

---

### **1. Media e varianza della somma (le due regole base)**

$$\boxed{\;E[X+Y]=E[X]+E[Y]\;}\qquad\text{sempre, anche per variabili dipendenti}$$

$$\boxed{\;\mathrm{Var}(X+Y)=\mathrm{Var}(X)+\mathrm{Var}(Y)\;}\qquad\text{solo se } X,Y \text{ indipendenti}$$

Con costanti: $\;E[aX+b]=aE[X]+b\;$ e $\;\mathrm{Var}(aX+b)=a^2\,\mathrm{Var}(X)$.

Per $n$ variabili i.i.d. con media $\mu$ e varianza $\sigma^2$:

$$E\!\left[\sum_{i=1}^n X_i\right]=n\mu \qquad \mathrm{Var}\!\left(\sum_{i=1}^n X_i\right)=n\sigma^2 \qquad \sigma_{\text{somma}}=\sigma\sqrt n$$

> ⚠️ $\sigma$ della somma cresce come $\sqrt n$, **non** come $n$: si sommano le **varianze**, non le deviazioni standard.

---

### **2. Convoluzione**

#### **2.1. Quando usarla**

Il testo descrive **due durate/quantità che si sommano**: *"due fasi sequenziali e indipendenti, durata totale"*, *"componente in stand-by che parte quando il primo si guasta"*, somma di due dadi. Si chiede la **densità** (non solo la media) della somma.

#### **2.2. Formule**

$$Z=X+Y \qquad\Longrightarrow\qquad f_Z = f_X * f_Y$$

$$\text{continuo:}\ \ f_Z(z)=\int_{-\infty}^{+\infty} f_X(x)\,f_Y(z-x)\,dx \qquad\qquad \text{discreto:}\ \ P_Z(z)=\sum_{x} P_X(x)\,P_Y(z-x)$$

#### **2.3. Procedura d'esame (caso continuo)**

1. Scrivere $Z=X+Y$ e determinare il **supporto** di $Z$ (es. $[0,2]$ se entrambe stanno in $[0,1]$).
2. Imporre **entrambe** le condizioni di esistenza: $x$ nel dominio di $X$ **e** $z-x$ nel dominio di $Y$.
3. Integrare sull'intersezione $[\max(0,z-1),\ \min(1,z)]$, **spezzando in casi** sui valori di $z$.
4. Verificare la normalizzazione (area $=1$).
5. La media si ottiene **sempre** dalla linearità, senza usare la densità: $E[Z]=E[X]+E[Y]$.

#### **2.4. Casi notevoli da riconoscere a memoria**

| Addendi indipendenti | Somma | Parametri |
|---|---|---|
| Uniforme$[0,1]$ + Uniforme$[0,1]$ | **triangolare** su $[0,2]$ | $f_Z(z)=z$ per $0\le z\le1$; $=2-z$ per $1\le z\le2$ |
| Esponenziale$(\lambda)$ + Esponenziale$(\lambda)$ | **Erlang** $(r=2,\lambda)$ | $f_Z(z)=\lambda^2 z e^{-\lambda z}$ |
| $r$ esponenziali $(\lambda)$ | **Erlang** $(r,\lambda)$ | $f(z)=\frac{\lambda^r z^{r-1}e^{-\lambda z}}{(r-1)!}$, $\mu=\frac r\lambda$, $\sigma^2=\frac{r}{\lambda^2}$ |
| Erlang$(r,\lambda)$ + Erlang$(s,\lambda)$ | Erlang$(r+s,\lambda)$ | |
| $n$ Bernoulli$(p)$ | **Binomiale**$(n,p)$ | |
| Binomiale$(n,p)$ + Binomiale$(m,p)$ | Binomiale$(n+m,p)$ | stesso $p$ |
| Poisson$(\mu)$ + Poisson$(\nu)$ | Poisson$(\mu+\nu)$ | |
| $\mathcal N(\mu_1,\sigma_1^2)+\mathcal N(\mu_2,\sigma_2^2)$ | **Normale** | $\mathcal N(\mu_1+\mu_2,\ \sigma_1^2+\sigma_2^2)$ |

#### **2.5. Mini esempio completo (16/06/2025 D4)**

Due fasi di durata $\sim U[0,1]$ indipendenti.

(a) $Z=X+Y$, con $Z\in[0,2]$.

(b) L'integrando vale $1$ quando $0\le x\le1$ **e** $0\le z-x\le1$, cioè $z-1\le x\le z$; l'integrale è la **lunghezza** dell'intervallo $[\max(0,z-1),\min(1,z)]$:

$$f_Z(z)=\begin{cases} z & 0\le z\le 1\\ 2-z & 1\le z\le 2\\ 0 & \text{altrove}\end{cases}$$

Verifica: area del triangolo $=\frac{2\cdot 1}{2}=1$ ✓

(c) $E[Z]=E[X]+E[Y]=\frac12+\frac12=1$ ora (coerente con la simmetria attorno a $z=1$).

> 💡 La somma di due uniformi **non è uniforme**: i valori centrali sono più probabili perché realizzabili in più modi. È il primo passo visivo verso il TLC.

---

### **3. Covarianza (v.a. teoriche)**

$$\mathrm{Cov}(X,Y)=E\big[(X-\mu_X)(Y-\mu_Y)\big]=E[XY]-E[X]E[Y]$$

$$X,Y \text{ indipendenti} \Rightarrow \mathrm{Cov}(X,Y)=0 \qquad\qquad \mathrm{Var}(X+Y)=\sigma_X^2+2\,\mathrm{Cov}(X,Y)+\sigma_Y^2$$

> ⚠️ L'implicazione **non si inverte**: $\mathrm{Cov}=0$ non garantisce l'indipendenza (indica solo assenza di relazione **lineare**).

#### **3.1. Dimostrazione richiesta all'esame (12/02/2025 D1a, 4 punti)**

Siano $X,Y$ indipendenti, $Z=X+Y$, $\mu_Z=\mu_X+\mu_Y$:

$$\sigma_Z^2=E\big[(Z-\mu_Z)^2\big]=E\big[((X-\mu_X)+(Y-\mu_Y))^2\big]$$
$$=E\big[(X-\mu_X)^2\big]+2E\big[(X-\mu_X)(Y-\mu_Y)\big]+E\big[(Y-\mu_Y)^2\big]$$
$$=\sigma_X^2+2\,\mathrm{Cov}(X,Y)+\sigma_Y^2=\sigma_X^2+0+\sigma_Y^2 \qquad \square$$

Il passaggio chiave da esplicitare è che l'indipendenza annulla il doppio prodotto. Il seguito (legge debole dei grandi numeri con Chebyshev) è in [L1 §8.4](L1%20-%20M1%20Formulario%20-%20Probabilit%C3%A0%20elementare.md).

---

### **4. Funzioni generatrici (strumento alternativo alla convoluzione)**

| | Definizione | Somma di v.a. indipendenti |
|---|---|---|
| **PGF** (discrete) | $G_X(u)=\sum_k P(k)\,u^k$ | $G_Z(u)=G_X(u)\cdot G_Y(u)$ |
| **MGF** (qualsiasi) | $M_X(w)=E[e^{wX}]$ | $M_Z(w)=M_X(w)\cdot M_Y(w)$ |

Estrazione delle probabilità: $P(k)=\frac{1}{k!}\,\dfrac{d^kG_X}{du^k}\Big|_{u=0}$. Momenti: $E[X]=M'_X(0)$, $E[X^2]=M''_X(0)$, $\sigma^2=M''(0)-[M'(0)]^2$.

| Distribuzione | PGF | MGF |
|---|---|---|
| Bernoulli$(p)$ | $q+pu$ | $q+pe^w$ |
| Binomiale$(n,p)$ | $(q+pu)^n$ | $(q+pe^w)^n$ |
| Poisson$(\mu)$ | $e^{\mu(u-1)}$ | $e^{\mu(e^w-1)}$ |
| Esponenziale$(\lambda)$ | — | $\dfrac{\lambda}{\lambda-w}$ ($w<\lambda$) |
| Erlang$(r,\lambda)$ | — | $\left(\dfrac{\lambda}{\lambda-w}\right)^{r}$ |
| Normale$(\mu,\sigma^2)$ | — | $\exp\!\left(\mu w+\tfrac12\sigma^2w^2\right)$ |

> 📌 Argomento **mai richiesto direttamente** negli 8 appelli disponibili. Serve però a giustificare in una riga i casi notevoli di §2.4: *"il prodotto delle generatrici conserva la forma funzionale, quindi la somma appartiene alla stessa famiglia"*.

---

### **5. Teorema del Limite Centrale (TLC)**

#### **5.1. Enunciato**

Siano $X_1,\dots,X_n$ **i.i.d.** con media $\mu$ e varianza $\sigma^2<\infty$. Allora:

$$Z_n=\frac{\bar X_n-\mu}{\sigma/\sqrt n}\ \xrightarrow{\ d\ }\ \mathcal N(0,1) \qquad\Longleftrightarrow\qquad \boxed{\;\bar X_n \approx \mathcal N\!\left(\mu,\ \frac{\sigma^2}{n}\right)\;}$$

Equivalentemente per la somma: $\;S_n\approx\mathcal N(n\mu,\ n\sigma^2)$.

#### **5.2. Condizioni (da elencare se la domanda è teorica)**

1. Variabili **indipendenti** e **identicamente distribuite**;
2. media $\mu$ **finita**;
3. varianza $\sigma^2$ **finita** — condizione essenziale: la Cauchy non la soddisfa e il TLC non si applica;
4. la distribuzione di partenza può essere **qualunque** (non serve normalità);
5. regola pratica: $n\ge 30$ è di solito sufficiente; con distribuzioni molto asimmetriche serve $n$ maggiore.

#### **5.3. Implicazioni per l'inferenza (le tre da citare)**

1. **Intervalli di confidenza** per la media: $\bar X_n\pm z_{\alpha/2}\frac{\sigma}{\sqrt n}$, validi *indipendentemente dalla distribuzione originaria* purché $n$ sia grande.
2. **Test/statistiche standardizzate**: $Z=\frac{\bar X_n-\mu_0}{\sigma/\sqrt n}\approx\mathcal N(0,1)$, quindi si può usare la tavola normale anche con dati non gaussiani.
3. **Approssimazione di distribuzioni discrete**: binomiale (se $np,\,n(1-p)\ge10$) e Poisson per $\mu$ grande, essendo somme di i.i.d.

> ⚠️ Il TLC riguarda la distribuzione della **media campionaria**, non delle singole $X_i$: anche con dati molto non normali, la loro media su $n$ osservazioni è approssimativamente gaussiana.
>
> 💡 Distinzione da dichiarare per il punteggio pieno: la **legge dei grandi numeri** dice *dove* converge $\bar X_n$ (a $\mu$); il **TLC** dice *con quale forma e a quale velocità* ($\mathcal N$, con dispersione $\sigma/\sqrt n$).

#### **5.4. Distribuzioni a coda pesante**

Se $\sigma^2$ è infinita (Cauchy/Lorentz, $f(x)=\frac{1}{\pi(1+x^2)}$) il limite non è gaussiano ma una **stabile non gaussiana**. Per queste distribuzioni si usano **mediana** e **IQR** al posto di media e varianza.

---

### **6. Distribuzioni campionarie**

#### **6.1. Minimo e massimo campionario**

Data una popolazione con densità $f$, cumulativa $F$ e sopravvivenza $S=1-F$, per un campione di $n$ elementi:

| | Cumulativa/sopravvivenza | Densità | Analogia |
|---|---|---|---|
| **Minimo** | $S_{\min}(t)=[S(t)]^n$ | $f_{\min}(t)=n\,f(t)[S(t)]^{n-1}$ | sistema in **serie** |
| **Massimo** | $F_{\max}(t)=[F(t)]^n$ | $f_{\max}(t)=n\,f(t)[F(t)]^{n-1}$ | sistema in **parallelo** |

**Casi notevoli:**

- Popolazione uniforme $[0,1]$: $f_{\min}=n(1-x)^{n-1}$, $f_{\max}=nx^{n-1}$.
- Popolazione esponenziale$(\lambda)$: $\;S_{\min}(t)=e^{-n\lambda t}$ ⇒ il minimo è **ancora esponenziale** con parametro $n\lambda$; il massimo **non** lo è: $F_{\max}(t)=(1-e^{-\lambda t})^n$.

#### **6.2. Media campionaria**

$$m=\bar X=\frac1n\sum_{i=1}^n X_i$$

$$\boxed{\;E[\bar X]=\mu\;}\qquad \boxed{\;\mathrm{Var}(\bar X)=\frac{\sigma^2}{n}\;}\qquad \boxed{\;\sigma_{\bar X}=\frac{\sigma}{\sqrt n}\ \ (\textbf{errore standard})\;}$$

Per $n$ moderatamente grande: $\;\bar X \approx \mathcal N(\mu,\ \sigma^2/n)$ (TLC).

> 💡 **Significato pratico:** per dimezzare l'incertezza serve un campione **4 volte** più grande; per ridurla di 10 volte, 100 volte più grande. L'errore scala come $1/\sqrt n$.

#### **6.3. Contrazione della densità**

Se $m=z/n$ allora $f_M(m)=n\,f_Z(nm)$: la densità si **contrae** orizzontalmente di un fattore $n$ e si **alza** dello stesso fattore, mantenendo area $1$.

---

### **7. Stima e intervalli di confidenza**

#### **7.1. Quando usarli**

La traccia chiede una **stima puntuale**, un **intervallo di confidenza** o l'**interpretazione** di un intervallo. Compare in 2 appelli su 8 (16/06 e 19/09/2025), sempre sulla **proporzione**.

#### **7.2. Stima della media**

| Caso | Stima | Errore standard |
|---|---|---|
| $\sigma$ **nota** | $\hat\mu=\bar x$ | $SE=\dfrac{\sigma}{\sqrt n}$ |
| $\sigma$ **ignota** ($n$ grande) | $\hat\mu=\bar x$ | $SE=\dfrac{s}{\sqrt n}$ con $s^2=\frac{\sum(x_i-\bar x)^2}{n-1}$ |

$$\boxed{\;IC_{1-\alpha}(\mu)=\bar x \pm z_{\alpha/2}\,\frac{\sigma}{\sqrt n}\;}$$

Versione "a sigma" usata nelle lezioni ([M3/UD3/L2](../M3_Argomenti_avanzati/UD3/L2%20-%20Stima%20della%20media.md)):

| Confidenza | Intervallo |
|---|---|
| $68\%$ | $\bar x \pm \sigma/\sqrt n$ |
| $95\%$ | $\bar x \pm 2\sigma/\sqrt n$ (esatto: $1.96$) |
| $99.7\%$ | $\bar x \pm 3\sigma/\sqrt n$ |

**Mini esempio (esercizio del docente, M3/UD3/L2):** $\sigma^2=6400$, $n=100$, $m=80$ ⇒ $\hat\mu=80\pm\frac{80}{10}=80\pm8$ ⇒ $IC_{68\%}=[72,88]$.

#### **7.3. Stima di una proporzione**

$$\hat p=\frac{k}{n} \qquad\qquad SE_{\hat p}=\sqrt{\frac{\hat p(1-\hat p)}{n}} \qquad\qquad \boxed{\;IC_{1-\alpha}(p)=\hat p\pm z_{\alpha/2}\sqrt{\frac{\hat p(1-\hat p)}{n}}\;}$$

**Livelli:** $1-\alpha$ è la confidenza; per il $95\%$ ⇒ $\alpha=0.05$ ⇒ $\alpha/2=0.025$ ⇒ $z_{\alpha/2}=1.96$. Per il $90\%$: $1.645$. Per il $99\%$: $2.576$.

> 📌 **Due formule equivalenti in circolazione, entrambe accettate.** Le lezioni e gli esercizi del docente usano la versione semplificata $\;p_{\text{stima}}=\frac kn \pm \frac{\sqrt k}{n}\;$ (larghezza al **68%**, ottenuta approssimando $\sigma^2=p(1-p)\approx p$); le soluzioni degli appelli usano $\hat p\pm z_{\alpha/2}\sqrt{\hat p(1-\hat p)/n}$. Sui dati d'esame danno praticamente lo stesso intervallo.
>
> **Esempio (16/06/2025 D3):** $k=20$, $n=200$, $\hat p=0.10$. Formula degli appelli: $SE=\sqrt{\frac{0.1\cdot0.9}{200}}=0.0212$, $IC_{95\%}=0.10\pm1.96(0.0212)=[0.058,\ 0.142]$. Formula del docente: mezza ampiezza al 68% $=\frac{\sqrt{20}}{200}=0.0224$, raddoppiata per il 95% ⇒ $[0.055,\ 0.145]$. Numericamente quasi identiche.
>
> ⚠️ Se si usa la versione semplificata, **dichiararlo**: "usando $\sigma\approx\sqrt k/n$ come da lezione, e $2\sigma$ per il 95%".

**Mini esempio (19/09/2025 D3b):** $k=65$, $n=100$ ⇒ $\hat p=0.65$, $SE=\sqrt{\frac{0.65\cdot0.35}{100}}=0.0477$, $IC_{95\%}=0.65\pm1.96(0.0477)=[0.5565,\ 0.7435]$.

#### **7.4. Procedura d'esame**

1. Stima puntuale ($\bar x$ o $\hat p=k/n$).
2. Errore standard (attenzione: $\sqrt{\hat p(1-\hat p)/n}$, **non** $\hat p(1-\hat p)/n$).
3. Valore critico $z_{\alpha/2}$ dal livello richiesto.
4. Intervallo $\pm$, scritto anche in forma di coppia $[L,U]$.
5. **Interpretazione** (§7.5): vale sempre punti a sé.

#### **7.5. Come interpretare correttamente l'intervallo**

**Formulazione frequentista (quella richiesta al 16/06/2025 D3c — da usare come risposta principale):**

> Se ripetessimo molte volte il campionamento — estraendo ogni volta un campione di $n$ unità e costruendo l'intervallo con questa stessa procedura — circa il 95% degli intervalli ottenuti conterrebbe il vero valore del parametro. Nel contesto: con confidenza del 95%, la vera proporzione di lampadine difettose è compresa fra circa il 5.8% e il 14.2%.

> ⚠️ **Da evitare come formulazione principale:** *"c'è il 95% di probabilità che $p$ stia in questo specifico intervallo"*. Il parametro vero $p$ è un **numero fisso**, non una variabile aleatoria: è l'**intervallo** a essere casuale, non $p$.
>
> 💡 **Nota sulle fonti (utile se il docente contesta la rigidità).** Il corso è impostato in chiave **bayesiana**: in [M3/UD3/L2](../M3_Argomenti_avanzati/UD3/L2%20-%20Stima%20della%20media.md) la posterior è $f(h\mid m,\sigma^2,n)=\mathcal N(h\mid m,\sigma^2/n)$, e in quel quadro la frase *"con il 68% circa di probabilità il valore vero si trova all'interno dell'intervallo"* — usata testualmente nell'esercizio del docente `PRO_M3_U3_L2_E.pdf` — è **corretta**, perché lì è il parametro a essere trattato come distribuito. Strategia sicura: scrivere l'interpretazione frequentista e aggiungere una riga: *"nell'impostazione bayesiana del corso, in cui la posterior su $\mu$ è $\mathcal N(m,\sigma^2/n)$, l'intervallo si legge direttamente come regione di credibilità al 95%"*. Così si copre qualunque criterio di correzione.

---

### **8. Statistica descrittiva su campione — indicatori di posizione**

> 📌 **L'argomento più frequente in assoluto: 7 appelli su 8.** Vale in media 7-8 punti per appello.

#### **8.1. Media campionaria**

$$\bar x=\frac1n\sum_{i=1}^n x_i \qquad\qquad \text{dati raggruppati:}\quad \bar x=\frac{1}{n}\sum_i x_i f_i,\quad n=\sum_i f_i$$

#### **8.2. Mediana**

Ordinare i dati in senso crescente, poi:

| $n$ | Mediana |
|---|---|
| **dispari** | valore in posizione $\dfrac{n+1}{2}$ |
| **pari** | media dei valori in posizione $\dfrac n2$ e $\dfrac n2+1$ |

Con **frequenze**: costruire le **frequenze cumulate** e individuare in quale classe cade la posizione $\frac{n+1}{2}$ (o le due posizioni centrali).

#### **8.3. Moda**

Valore con la **frequenza massima**. Può non esistere (tutte le frequenze uguali) o essere multipla.

#### **8.4. Quartili e percentili**

> ⚠️ **Convenzioni diverse coesistono nelle fonti.** Dichiarare sempre quale si usa: è ciò che rende la risposta difendibile.

**Convenzione A — posizione $\lceil q\cdot n\rceil$** (usata nella **soluzione ufficiale del docente**, appello 19/09/2025):

$$Q_1 \to \text{posizione } \lceil 0.25\,n\rceil \qquad Q_3\to \text{posizione } \lceil 0.75\,n\rceil$$

**Convenzione B — metodo delle metà** (usata nelle altre soluzioni): si divide il campione ordinato in due metà (con $n$ dispari si **esclude** la mediana), $Q_1$ = mediana della metà inferiore, $Q_3$ = mediana della metà superiore.

**Convenzione C — posizione $\frac{k(n+1)}{4}$**: $Q_1$ in posizione $\frac{n+1}{4}$, $Q_3$ in posizione $\frac{3(n+1)}{4}$ (utile con dati raggruppati per frequenza).

> ✅ **Buona notizia: nella maggior parte dei casi coincidono.** Con $n=7$: entrambe A e B danno posizione 2 e posizione 6. Con $n=10$: entrambe danno posizione 3 e posizione 8. Divergono solo quando $q\cdot n$ è **intero** (es. $n=20$: A dà il 5° valore, B la media fra 5° e 6°). In quel caso o va bene, purché dichiarata.

$$\text{IQR}=Q_3-Q_1$$

#### **8.5. Esempi svolti (dagli appelli)**

**23/07/2025 D2** — pesi $65,70,72,75,80,85,90$ ($n=7$): mediana $=$ pos. 4 $=75$; $Q_1=$ pos. 2 $=70$; $Q_3=$ pos. 6 $=85$.

**19/09/2025 D4** — pesi ordinati $50,51,52,52,62,78,95$ ($n=7$): $\bar x=\frac{440}{7}\approx62.86$; mediana $=52$; moda $=52$; $Q_1=51$, $Q_2=52$, $Q_3=78$, IQR $=27$.

**05/07/2025 D1** — $n=10$: mediana $=$ media 5°-6° $=85$; $Q_1=78$ (pos. 3), $Q_3=90$ (pos. 8).

**05/09/2025 D2** — taglie con frequenze, $n=54$: cumulate $2,3,8,13,21,37,42,\dots$; posizione $\frac{n+1}{2}=27.5$ ⇒ cade nella taglia **43**; posizione $\frac{3(n+1)}{4}=41.25$ ⇒ cade nella taglia **44**.

**16/01/2026 D2** — tentativi di login, $n=50$: $\bar x=\frac{56}{50}=1.12$; cumulate $15,35,\dots$ ⇒ posizioni 25 e 26 cadono in $x=1$ ⇒ mediana $=1$; moda $=1$.

> 💡 **Con dati raggruppati per frequenza, disegnare sempre la riga delle cumulate**: risolve mediana e quartili in un colpo solo e serve anche a verificare che $\sum f_i=n$.

---

### **9. Statistica descrittiva — indicatori di ampiezza**

#### **9.1. Range**

$$\text{range}=x_{\max}-x_{\min}$$

#### **9.2. Varianza e deviazione standard campionarie**

$$\boxed{\;s^2=\frac{1}{n-1}\sum_{i=1}^n (x_i-\bar x)^2\;}\qquad s=\sqrt{s^2}$$

Con dati raggruppati: $\;s^2=\dfrac{\sum_i f_i(x_i-\bar x)^2}{n-1}$.

**Formula computazionale (risparmia tempo con molti dati):**

$$\sum_i (x_i-\bar x)^2 = \sum_i x_i^2 - n\bar x^2 \qquad\qquad \sum_i f_i(x_i-\bar x)^2=\sum_i f_i x_i^2 - n\bar x^2$$

>⚠️ **La distinzione che vale più punti di tutte.**

| Denominatore | Nome | Quando |
| --- | --- | --- |
| `n-1` | Varianza **campionaria**, stimatore **unbiased** (`E[s²] = σ²`) | La traccia dice "campione", "campionaria", "un gruppo di `n` persone" ⇒ **è il caso di tutti gli appelli** |
| `n` (o `N`) | Varianza di **popolazione**, stimatore **biased** | La traccia dichiara di avere l'intera popolazione |

Se la traccia chiede *"si dica se è biased o unbiased"* (12/02/2025 D2c): rispondere che con `n-1` (correzione di Bessel) lo stimatore è **non distorto**, con `n` è distorto; il risultato va dato con `n-1`.

⚠️ **Incongruenza nota nelle fonti:** M3/UD2/L1 definisce

$$
s^2 = \frac{1}{n}\sum x_j^2 - m^2
$$

(denominatore `n`, coerente con la varianza di una distribuzione).

**Tutti gli appelli usano `n-1`. All'esame usare `n-1`.**
#### **9.3. Esempi svolti**

| Appello | Dati | Calcolo | Risultato |
|---|---|---|---|
| 16/06/2025 | $18,20,19,22,20,21,20$ ($\bar x=20$) | $s^2=\frac{10}{6}=1.667$ | $s\approx1.29$ °C |
| 05/07/2025 | $n=10$, $\bar x=83.6$, $\sum(x_i-\bar x)^2=442.40$ | $s^2=\frac{442.40}{9}$ | $\approx49.16$ |
| 23/07/2025 | $n=7$, $\sum(x_i-\bar x)^2=463.44$ | $s^2=\frac{463.44}{6}=77.24$ | $s\approx8.79$ kg |
| 12/02/2025 | frequenze, $\sum f_i(x_i-21)^2=110$, $n=20$ | $s^2=\frac{110}{19}=5.789$ | $s\approx2.406$ |
| 19/09/2025 | $\sum x_i^2=29462$, $n\bar x^2=27657.14$ | $s^2=\frac{1804.86}{6}$ | $\approx300.81$, $s\approx17.34$ |
| 16/01/2026 | $\sum f x^2=112$, $n\bar x^2=62.72$ | $s^2=\frac{49.28}{49}$ | $\approx1.006$, $s\approx1.003$ |

#### **9.4. Outlier e boxplot**

**Due criteri, entrambi comparsi:**

| Criterio | Regola | Dove |
|---|---|---|
| **Regola dei $3\sigma$** | outlier se $x\notin[\bar x-3s,\ \bar x+3s]$ | richiesta esplicitamente al 12/02/2025 D2d |
| **Regola di Tukey (boxplot)** | outlier se $x<Q_1-1.5\,\text{IQR}$ oppure $x>Q_3+1.5\,\text{IQR}$ | standard per i baffi del boxplot |

**Costruzione del boxplot:**

1. Calcolare $Q_1$, mediana, $Q_3$ ⇒ la **scatola** va da $Q_1$ a $Q_3$, con la linea della mediana all'interno.
2. Calcolare i limiti $Q_1-1.5\,\text{IQR}$ e $Q_3+1.5\,\text{IQR}$.
3. I **baffi** arrivano fino al valore osservato **più estremo che rientra** in quei limiti (non ai limiti stessi).
4. I valori fuori si disegnano come **punti isolati** (outlier).

**Mini esempio (12/02/2025 D2e)** — età, $n=20$, dati ordinati $18,19,19,19,19,20,20,20,20,20,21,21,21,21,22,22,22,23,24,29$:

- Mediana $=\frac{20+21}{2}=20.5$; $Q_1=\frac{19+20}{2}=19.5$; $Q_3=\frac{22+22}{2}=22$; IQR $=2.5$
- Baffi: $Q_1-1.5(2.5)=15.75$ ⇒ valore interno minimo $=18$; $Q_3+1.5(2.5)=25.75$ ⇒ valore interno massimo $=24$
- **Outlier: 29** (fuori dal baffo superiore; e anche fuori da $[\bar x\pm3s]=[13.78,\ 28.22]$ con la regola dei $3\sigma$)

$$18 \;[\; 19.5 \mid 20.5 \mid 22 \;]\; 24 \qquad \bullet\ 29$$

#### **9.5. Bar plot (16/01/2026 D2a)**

Barre verticali di altezza pari alla **frequenza**, una per ciascun valore discreto sull'asse orizzontale. Va accompagnato da una riga di commento sulla forma (picco, coda), che anticipa la risposta sulla skewness.

---

### **10. Skewness (asimmetria)**

#### **10.1. Lettura qualitativa: media vs mediana**

$$\bar x > \text{mediana} \Rightarrow \text{asimmetria \textbf{positiva}, coda a \textbf{destra}}$$
$$\bar x < \text{mediana} \Rightarrow \text{asimmetria \textbf{negativa}, coda a \textbf{sinistra}}$$
$$\bar x \approx \text{mediana} \Rightarrow \text{distribuzione (quasi) simmetrica}$$

> 💡 **Intuizione:** la media "insegue la coda" perché è sensibile ai valori estremi, la mediana no. Esempio classico: i redditi, dove la media è gonfiata dai pochi ricchissimi.
>
> ⚠️ Non invertire il verso: media **maggiore** della mediana ⇒ coda a **destra**.

**Esempi:** 16/01/2026 D2d: $\bar x=1.12>\text{mediana}=1$ ⇒ asimmetria positiva (pochi account con 3-4 login falliti allungano la coda destra — in ottica security è proprio la coda la zona da monitorare). 19/09/2025: $\bar x=62.86\gg\text{mediana}=52$ ⇒ asimmetria positiva marcata, e la **mediana è l'indicatore più rappresentativo** in presenza di outlier.

#### **10.2. Skewness campionaria di Fisher (calcolo numerico)**

Richiesta esplicitamente al **23/07/2025 D2d**:

$$\boxed{\;g_1=\frac{n}{(n-1)(n-2)}\sum_{i=1}^{n}\left(\frac{x_i-\bar x}{s}\right)^3\;}$$

con $s$ la **deviazione standard campionaria** ($n-1$). Segno: $g_1>0$ ⇒ coda destra; $g_1<0$ ⇒ coda sinistra; $g_1\approx0$ ⇒ simmetrica.

**Procedura:** (1) calcolare $\bar x$ e $s$; (2) standardizzare ogni dato $z_i=\frac{x_i-\bar x}{s}$; (3) elevare al cubo e sommare; (4) moltiplicare per $\frac{n}{(n-1)(n-2)}$.

**Mini esempio (23/07/2025):** pesi $65,70,72,75,80,85,90$, $\bar x\approx76.71$, $s\approx8.789$:

| $x_i$ | $z_i$ | $z_i^3$ |
|---|---|---|
| 65 | $-1.333$ | $-2.369$ |
| 70 | $-0.764$ | $-0.446$ |
| 72 | $-0.536$ | $-0.154$ |
| 75 | $-0.195$ | $-0.007$ |
| 80 | $+0.374$ | $+0.052$ |
| 85 | $+0.943$ | $+0.838$ |
| 90 | $+1.512$ | $+3.454$ |
| **Σ** | | $+1.368$ |

$$g_1=\frac{7}{6\cdot5}\times 1.368=\frac{7}{30}(1.368)\approx +0.319$$

**Interpretazione:** $g_1>0$ ⇒ asimmetria a destra, coerente con $\bar x=76.71>\text{mediana}=75$.

> 📌 **Formula del "programma ombra":** $g_1$ **non compare in nessuna lezione** (le lezioni citano solo il momento centrale di ordine 3, $\gamma_1=E[(X-\mu)^3]/\sigma^3$). Va portata scritta. Se la traccia dice solo *"si commenti lo sbilanciamento"*, basta il confronto media/mediana; se dice *"si calcoli la skewness campionaria"*, serve $g_1$.

#### **10.3. Curtosi**

Momento centrale standardizzato di ordine 4: misura la concentrazione attorno alla media. **Mai richiesta** negli appelli disponibili; citata solo come momento di ordine 4 in [M2/UD1/L4](../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md).

---

### **11. Associazione fra due variabili**

#### **11.1. Coefficiente di variazione (CV)**

$$\boxed{\;CV=\frac{s}{\bar x}\;}\qquad\text{(spesso in percentuale: } CV\times100\%\text{)}$$

**Quando usarlo:** confrontare la variabilità di grandezze con **unità** o **scale** diverse (pesi in kg vs altezze in cm). È **adimensionale**.

> ⚠️ È adimensionale per costruzione: riportarlo con un'unità di misura è un errore. Perde senso se $\bar x\approx0$ o se i dati cambiano segno: si usa per grandezze positive.

**Mini esempio (19/09/2025 D4d):** $s=17.34$ kg, $\bar x=62.86$ kg ⇒ $CV=\frac{17.34}{62.86}\approx0.276$ ($27.6\%$): la deviazione standard è il 27.6% della media.

#### **11.2. Covarianza campionaria**

$$s_{xy}=\frac{1}{n-1}\sum_{i=1}^n (x_i-\bar x)(y_i-\bar y)$$

Segno positivo ⇒ le variabili si muovono nella stessa direzione; negativo ⇒ direzioni opposte. Coerenza: $s_{xx}=s_x^2$.

> ⚠️ Il **valore numerico** dipende dalle unità (kg·cm): non è interpretabile in assoluto. Una covarianza "grande" non significa relazione forte — la forza si legge solo dopo la normalizzazione, cioè da $r$.

#### **11.3. Correlazione di Pearson**

$$\boxed{\;r=\frac{s_{xy}}{s_x\,s_y}=\frac{\sum_i(x_i-\bar x)(y_i-\bar y)}{\sqrt{\sum_i(x_i-\bar x)^2}\ \sqrt{\sum_i(y_i-\bar y)^2}}\;}\qquad -1\le r\le +1$$

> 💡 Le due forme sono **identiche**: nella seconda i fattori $\frac{1}{n-1}$ si semplificano fra numeratore e denominatore. Conviene la prima se si hanno già $s_x$, $s_y$ e $s_{xy}$ (com'è tipico all'esame, dove la traccia li fornisce).

| Valore di $r$ | Lettura |
|---|---|
| $r=+1$ | relazione lineare crescente **perfetta** |
| $0.7\lesssim r<1$ | correlazione lineare positiva **forte** |
| $0.3\lesssim r\lesssim0.7$ | positiva **moderata** |
| $r\approx0$ | **assenza di relazione lineare** |
| $r<0$ | analogo, con relazione decrescente |
| $r=-1$ | relazione lineare decrescente perfetta |

> ⚠️ **Le due avvertenze da scrivere sempre** (valgono punti):
>
> 1. **Correlazione $\neq$ causalità**: $r$ alto non prova che una variabile causi l'altra (possibili variabili nascoste).
> 2. $r$ misura solo relazioni **lineari**: $r\approx0$ significa assenza di correlazione *lineare*, **non** assenza di ogni relazione (es. $y=x^2$ su dominio simmetrico dà $r\approx0$ pur essendo una relazione perfetta).

**Mini esempio (19/09/2025 D4e):** pesi/altezze di 7 amici, $s_{xy}=139.82$, $s_x=17.34$, $s_y=8.71$:

$$r=\frac{139.82}{17.34\times8.71}=\frac{139.82}{151.03}\approx0.926$$

Correlazione lineare **molto forte e positiva**: all'aumentare del peso l'altezza tende ad aumentare in modo quasi perfettamente lineare, senza che ciò implichi un nesso causale.

---

### **12. Riepilogo formule M3**

| Formula | Nome | Quando |
|---|---|---|
| $E[X+Y]=E[X]+E[Y]$ | linearità | sempre |
| $\mathrm{Var}(X+Y)=\sigma_X^2+\sigma_Y^2$ | varianza della somma | solo se indipendenti |
| $\mathrm{Cov}(X,Y)=E[XY]-E[X]E[Y]$ | covarianza | dimostrazioni |
| $f_Z(z)=\int f_X(x)f_Y(z-x)dx$ | convoluzione | densità della somma |
| $\bar X\approx\mathcal N(\mu,\sigma^2/n)$ | **TLC** | $n$ grande |
| $S_{\min}=[S]^n$, $F_{\max}=[F]^n$ | min/max campionario | serie/parallelo |
| $\sigma_{\bar X}=\sigma/\sqrt n$ | errore standard | precisione della stima |
| $\bar x\pm z_{\alpha/2}\sigma/\sqrt n$ | IC per la media | $\sigma$ nota |
| $\hat p\pm z_{\alpha/2}\sqrt{\hat p(1-\hat p)/n}$ | IC per la proporzione | conteggi $k$ su $n$ |
| $s^2=\frac{1}{n-1}\sum(x_i-\bar x)^2$ | varianza **campionaria** | tutti gli appelli |
| $\sum(x_i-\bar x)^2=\sum x_i^2-n\bar x^2$ | forma computazionale | molti dati |
| $\text{IQR}=Q_3-Q_1$; outlier oltre $1.5\,$IQR o $3s$ | boxplot | grafici |
| $g_1=\frac{n}{(n-1)(n-2)}\sum z_i^3$ | skewness di Fisher | 23/07/2025 |
| $CV=s/\bar x$ | dispersione relativa | confronto fra scale |
| $r=s_{xy}/(s_xs_y)$ | Pearson | associazione lineare |
