# **L0 - Guida rapida d'esame e tavole statistiche**

> 📌 **Foglio da tenere in cima alla pila.** Contiene solo strumenti di *riconoscimento* e *navigazione*: quale distribuzione usare, come tradurre il testo italiano in simboli, dove sta la formula, e le tavole della normale standard. Le formule vere stanno in [L1 (M1)](L1%20-%20M1%20Formulario%20-%20Probabilit%C3%A0%20elementare.md), [L2 (M2)](L2%20-%20M2%20Formulario%20-%20Variabili%20aleatorie%20e%20distribuzioni.md), [L3 (M3)](L3%20-%20M3%20Formulario%20-%20Somme,%20campionamento%20e%20inferenza.md).

> ✅ **La consultazione è ammessa.** I temi d'esame riportano testualmente: *"È ammessa la consultazione del formulario e delle tavole statistiche, nonché l'uso della calcolatrice. Qualsiasi altro strumento non è ammesso."* Durata: **2 ore**, tipicamente **4-5 domande per 32-33 punti**.

---

### **1. Metodo dei cinque passi (vale per ogni esercizio)**

1. **Discreto o continuo?** Si conta qualcosa ⇒ discreto. Si misura un tempo/una lunghezza/un peso ⇒ continuo.
2. **Con o senza reinserimento? Tasso costante?** È la domanda che separa Binomiale da Ipergeometrica e Geometrica da Esponenziale (§2).
3. **Scrivere il modello per esteso**, con i parametri numerici: `X ~ Ipergeom(N=24, K=6, n=4)`. Vale punti da sola: quasi tutte le tracce assegnano 1-2 punti alla sola *identificazione giustificata*.
4. **Tradurre la domanda in simboli** con la tabella §3 (`almeno` ⇒ complemento, `più di 1 ma meno di 4` ⇒ $\{2,3\}$).
5. **Calcolare, poi commentare in una riga.** Le tracce chiedono quasi sempre "si spieghi/si commenti": una frase di interpretazione vale punti tanto quanto il numero.

> ⚠️ Sui punteggi alti (4 punti o più) la traccia premia il **metodo esplicito**: verifica delle condizioni di applicabilità, correzione di continuità, nome della proprietà usata (memoryless, correzione per popolazione finita, legge delle alternative). Il numero giusto senza il metodo perde punti.

---

### **2. Tabella "come riconosco la distribuzione"**

| Testo dell'esercizio (parole spia) | Distribuzione | Dove |
|---|---|---|
| Un solo esperimento con esito successo/fallimento | **Bernoulli**$(p)$ | [L2 §5](L2%20-%20M2%20Formulario%20-%20Variabili%20aleatorie%20e%20distribuzioni.md) |
| Tutti gli esiti equiprobabili su un intervallo/insieme | **Uniforme** | L2 §6 |
| *"numero di successi in $n$ prove indipendenti"*, $p$ costante, **con** reinserimento / grande lotto | **Binomiale**$(n,p)$ | L2 §7 |
| *"numero di tentativi fino al primo successo"*, *"al quale tentativo"* | **Geometrica**$(p)$ | L2 §8 |
| *"numero di eventi in un intervallo di tempo/spazio"*, tasso medio costante, eventi rari e indipendenti | **Poisson**$(\mu=\lambda\Delta t)$ | L2 §9 |
| *"senza reinserimento"*, *"lotto di $N$ pezzi di cui $K$ difettosi"*, popolazione **finita** | **Ipergeometrica**$(N,K,n)$ | L2 §10 |
| *"tempo di attesa"*, *"durata di vita"*, *"valore atteso pari a ..."*, tasso costante, componente che non invecchia | **Esponenziale**$(\lambda)$ | L2 §11 |
| Fenomeno continuo, simmetrico attorno a una media, dati $\mu$ e $\sigma$ | **Normale**$(\mu,\sigma^2)$ | L2 §12 |
| Densità assegnata nel testo, con costante $C$ da determinare | **Densità generica**: normalizzare | L2 §3 |
| Somma di due durate/fasi **sequenziali** indipendenti | **Convoluzione** (uniformi ⇒ triangolare; esponenziali ⇒ Erlang) | [L3 §2](L3%20-%20M3%20Formulario%20-%20Somme,%20campionamento%20e%20inferenza.md) |
| Media di un campione di $n$ osservazioni, $n$ grande | **Normale**$(\mu,\sigma^2/n)$ per il TLC | L3 §4-5 |

> 💡 **Il bivio che decide più punti di tutti:** *"$n$ prove indipendenti con $p$ costante"* ⇒ **Binomiale**; *"estrazione senza reinserimento da popolazione finita"* ⇒ **Ipergeometrica**. Se $n/N \le 0.05$ le due coincidono di fatto, ma negli appelli $n/N$ è sempre grande apposta ($4/24$, $20/300$): la traccia vuole l'ipergeometrica.

---

### **3. Tabella "parole chiave → operazione"**

| Testo italiano | Simbolo | Come si calcola |
|---|---|---|
| esattamente $k$ | $P(X=k)$ | formula puntuale (PMF) |
| almeno $k$ | $P(X\ge k)$ | $1-P(X\le k-1)$ |
| **almeno uno** | $P(X\ge 1)$ | $1-P(X=0)$ ← quasi sempre la via rapida |
| al più $k$ / non superiore a $k$ / non più di $k$ | $P(X\le k)$ | somma da $0$ a $k$ |
| più di $k$ / superiore a $k$ / oltre $k$ | $P(X>k)$ | $1-P(X\le k)$ |
| meno di $k$ / inferiore a $k$ | $P(X<k)=P(X\le k-1)$ | (discreto) attenzione all'estremo |
| tra $a$ e $b$ **inclusi** | $P(a\le X\le b)$ | $F(b)-F(a-1)$ discreto; $F(b)-F(a)$ continuo |
| **più di $a$ ma meno di $b$** | $a<X<b$ ⇒ $\{a+1,\dots,b-1\}$ | estremi **esclusi** (trappola del 19/09/2025) |
| nessuno / mai | $P(X=0)$ | |
| tutti / tutti e $n$ | $p^n$ | $n$ eventi indipendenti tutti favorevoli |
| il sistema funziona se **tutti** funzionano | serie ⇒ $\prod R_i$ | [L1 §7](L1%20-%20M1%20Formulario%20-%20Probabilit%C3%A0%20elementare.md) |
| il sistema funziona se **almeno uno** funziona | parallelo ⇒ $1-\prod(1-R_i)$ | L1 §7 |
| dato che / sapendo che / se è già accaduto | $P(A\mid B)$ | condizionata, L1 §4 |
| *supponendo di aver osservato ..., qual è la probabilità che la causa fosse ...* | $P(H\mid D)$ | **Bayes**, L1 §6 |
| probabilità **totale** che ... | $P(B)=\sum_i P(A_i)P(B\mid A_i)$ | legge delle alternative, L1 §5 |
| a priori | $P(H)$ | prior |
| a posteriori / aggiornata | $P(H\mid D)$ | posterior |
| il tempo **mediano** | $t$ tale che $F(t)=0.5$ | L2 §4 |
| il $90^\circ$ percentile / il valore superato dal 10% | $x_{0.90}$: $F(x)=0.90$ | L2 §12.5 |
| stima puntuale | $\hat p = k/n$ oppure $\bar x$ | L3 §6 |
| intervallo di confidenza al 95% | $\pm z_{\alpha/2}\cdot SE$ | L3 §7 |

---

### **4. Mappa "tipo di esercizio → frequenza negli 8 appelli → formulario"**

| Tipo di esercizio | Appelli in cui compare | Frequenza | Sezione |
|---|---|:---:|---|
| **Bayes / probabilità totale** | tutti e 8 | **8/8** | L1 §5-6 |
| **Statistica descrittiva su campione** | 12/02, 16/06, 05/07, 23/07, 05/09, 19/09, 16/01 | **7/8** | L3 §8-11 |
| **Normale con tavole** | 05/07, 23/07, 05/09, 19/09, 16/01 | 5/8 | L2 §12 |
| **Ipergeometrica** | 15/01, 05/07, 19/09, 16/01 | 4/8 | L2 §10 |
| **Esponenziale** (memoryless, mediana, sopravvivenza) | 15/01, 05/09, 16/01 | 3/8 | L2 §11 |
| **Densità continua con costante $C$** (+ CDF, mediana, varianza) | 15/01, 05/07, 05/09 | 3/8 | L2 §3-4 |
| **Affidabilità serie/parallelo** | 15/01, 16/06, 05/09 | 3/8 | L1 §7 |
| **Combinatoria / estrazioni senza reinserimento** (carte, lotto, alberi) | 15/01, 12/02, 16/01 | 3/8 | L1 §2-3 |
| **Binomiale** | 12/02, 23/07, 19/09 | 3/8 | L2 §7 |
| **Poisson** | 12/02, 23/07 | 2/8 | L2 §9 |
| **Intervallo di confidenza** (proporzione) | 16/06, 19/09 | 2/8 | L3 §7 |
| Approssimazione normale della binomiale + continuità | 19/09 | 1/8 | L2 §13 |
| Convoluzione di due uniformi | 16/06 | 1/8 | L3 §2 |
| Rete a ponte (condizionamento) | 16/06 | 1/8 | L1 §7.5 |
| TLC come domanda teorica | 15/01 | 1/8 | L3 §5 |
| Dimostrazioni ($\mathrm{Var}$ della somma, WLLN con Chebyshev) | 12/02 | 1/8 | L3 §3, L1 §8 |
| Skewness campionaria di Fisher $g_1$ | 23/07 | 1/8 | L3 §10 |
| $CV$ e correlazione di Pearson | 19/09 | 1/8 | L3 §11 |
| Bayes iterativo (secondo test) | 05/09 | 1/8 | L1 §6.4 |
| Boxplot / regola $3\sigma$ per outlier | 12/02 | 1/8 | L3 §9 |
| Bar plot da tabella di frequenze | 16/01 | 1/8 | L3 §8 |

---

### **5. Box delle confusioni (da rileggere prima di consegnare)**

#### **5.1. Varianza di popolazione vs varianza campionaria**

$$\sigma^2 = \frac{1}{N}\sum (x_i-\mu)^2 \qquad\text{vs}\qquad s^2 = \frac{1}{n-1}\sum (x_i-\bar x)^2$$

> ⚠️ **Negli appelli si usa sempre $n-1$** quando la traccia dice "campione", "campionaria", "un gruppo di", "si è rilevato su $n$ individui". Il $\frac1N$ si usa solo se la traccia dichiara di avere **l'intera popolazione**. Se la traccia chiede *"si dica se è biased o unbiased"* (12/02/2025): $n-1$ ⇒ **unbiased**, $n$ ⇒ **biased**.

#### **5.2. Correzione $n-1$ (Bessel) vs fattore di correzione per popolazione finita**

$$\underbrace{\frac{1}{n-1}}_{\text{stima non distorta, statistica descrittiva}} \qquad \neq \qquad \underbrace{\frac{N-n}{N-1}}_{\text{varianza dell'ipergeometrica}}$$

> ⚠️ Sono due cose senza alcun rapporto. Il primo è un **denominatore** in una formula di varianza campionaria; il secondo è un **fattore moltiplicativo** $<1$ che riduce la varianza binomiale nell'ipergeometrica. Non si applicano mai insieme.

#### **5.3. Binomiale vs Ipergeometrica**

| | Binomiale | Ipergeometrica |
|---|---|---|
| Campionamento | con reinserimento / popolazione infinita | **senza** reinserimento, popolazione **finita** |
| Prove | indipendenti, $p$ costante | dipendenti, $p$ cambia a ogni estrazione |
| $P(X=k)$ | $\binom{n}{k}p^k q^{n-k}$ | $\dfrac{\binom{K}{k}\binom{N-K}{n-k}}{\binom{N}{n}}$ |
| $E[X]$ | $np$ | $n\frac{K}{N}$ ← **identica**, con $p=K/N$ |
| $\mathrm{Var}(X)$ | $np(1-p)$ | $np(1-p)\cdot\frac{N-n}{N-1}$ ← **minore** |

#### **5.4. Geometrica vs Esponenziale**

| | Geometrica | Esponenziale |
|---|---|---|
| Variabile | numero di **tentativi** (discreto, $i\ge1$) | **tempo** (continuo, $t\ge0$) |
| Legge | $P(i)=q^{i-1}p$ | $f(t)=\lambda e^{-\lambda t}$ |
| Sopravvivenza | $S(i)=q^i$ | $S(t)=e^{-\lambda t}$ |
| Media | $1/p$ | $1/\lambda$ |
| Varianza | $q/p^2$ | $1/\lambda^2$ |
| Memoryless | sì (discreto) | sì (continuo, **unica** continua a esserlo) |

> 💡 Sono la **stessa legge** in domini diversi: $q^i \leftrightarrow e^{-\lambda t}$ con $\lambda=-\ln q$.

#### **5.5. Geometrica vs Binomiale (che cosa conto?)**

- **Geometrica**: fisso il successo, conto i **tentativi** ⇒ "al quinto tentativo".
- **Binomiale**: fisso i tentativi, conto i **successi** ⇒ "5 successi su 20".

#### **5.6. PMF vs PDF vs CDF**

| | Discreto | Continuo |
|---|---|---|
| Legge puntuale | $P(k)$ è una **probabilità** ($\le 1$) | $f(x)$ è una **densità**: può valere $>1$, non è una probabilità |
| Cumulativa | $F(r)=\sum_{k\le r}P(k)$ (a gradini) | $F(x)=\int_{-\infty}^{x} f(t)\,dt$ (continua) |
| Probabilità di un punto | $P(X=k)$ può essere $>0$ | $P(X=x)=\mathbf{0}$ **sempre** |
| Probabilità di intervallo | somma | integrale, oppure $F(b)-F(a)$ |

> ⚠️ Domanda ricorrente (23/07/2025 D5b): *"si spieghi perché $P(X=75)=0$"*. Risposta: per una v.a. **continua** la probabilità è l'**area** sotto la densità, e l'area su un singolo punto è nulla; hanno senso solo le probabilità di intervalli. Conseguenza operativa: nel continuo $P(X\le a)=P(X<a)$, gli estremi non contano.

#### **5.7. $\sigma$ dei dati vs $s$ campionaria vs errore standard della media**

$$\underbrace{\sigma}_{\substack{\text{dispersione della}\\ \text{popolazione}}} \qquad \underbrace{s=\sqrt{\tfrac{1}{n-1}\textstyle\sum(x_i-\bar x)^2}}_{\substack{\text{dispersione stimata}\\ \text{dai dati del campione}}} \qquad \underbrace{SE=\frac{\sigma}{\sqrt n}\ \ \text{o}\ \ \frac{s}{\sqrt n}}_{\substack{\text{dispersione della MEDIA}\\ \text{campionaria}}}$$

> ⚠️ $\sigma$ e $s$ **non** diminuiscono al crescere di $n$ (descrivono i dati); $SE$ **sì**, come $1/\sqrt n$ (descrive la precisione della stima). Nell'intervallo di confidenza va sempre $SE$, mai $s$ da solo.

#### **5.8. Media della popolazione vs media campionaria**

$\mu$ è un **numero fisso e incognito**; $\bar x$ (o $m$) è una **variabile aleatoria** che cambia da campione a campione, con $E[\bar X]=\mu$ e $\mathrm{Var}(\bar X)=\sigma^2/n$.

#### **5.9. Percentile vs probabilità sotto una soglia (le due direzioni della tavola)**

| Domanda | Direzione | Procedura |
|---|---|---|
| *"probabilità che $X$ sia sotto 65"* | $x \to z \to$ tavola $\to p$ | $z=\frac{x-\mu}{\sigma}$, leggo $\Phi(z)$ |
| *"il $90^\circ$ percentile"* | $p \to$ tavola $\to z \to x$ | cerco $z$ con $\Phi(z)=0.90$, poi $x=\mu+z\sigma$ |

#### **5.10. Probabilità condizionata vs indipendenza**

$$P(A\mid B)=\frac{P(A\cap B)}{P(B)} \qquad\text{sempre};\qquad P(A\cap B)=P(A)P(B) \iff \text{indipendenti}$$

> ⚠️ Indipendenti $\Rightarrow P(A\mid B)=P(A)$. **Incompatibili** ($A\cap B=\varnothing$) è tutt'altro: due eventi incompatibili con probabilità non nulla sono fortemente **dipendenti** (se accade uno, l'altro è impossibile).

#### **5.11. Legge del prodotto vs convoluzione**

- **Legge del prodotto**: probabilità che **due eventi accadano insieme** ⇒ $P(A\cap B)=P(A)P(B\mid A)$.
- **Convoluzione**: distribuzione della **somma di due variabili aleatorie** ⇒ $f_Z=f_X * f_Y$, cioè $f_Z(z)=\int f_X(x)f_Y(z-x)\,dx$.

> 💡 Spia nel testo: *"due fasi sequenziali, durata totale"*, *"componente in stand-by"* ⇒ si somma il **tempo** ⇒ convoluzione. *"entrambi i componenti funzionano"* ⇒ si moltiplicano le **probabilità** ⇒ legge del prodotto.

#### **5.12. Serie/parallelo vs minimo/massimo campionario**

$$\text{serie} = \min(X_1,\dots,X_n) \Rightarrow S_{\min}(t)=[S(t)]^n \qquad \text{parallelo} = \max \Rightarrow F_{\max}(t)=[F(t)]^n$$

> ⚠️ Nel **serie** si moltiplicano le **affidabilità**; nel **parallelo** si moltiplicano le **fallibilità** e poi si complementa. Invertirli è l'errore classico.

#### **5.13. $\lambda$ (tasso) vs $\mu$ (media) nell'esponenziale**

Se la traccia dice *"valore atteso pari a 8 mesi"*, allora $\mu=8$ e $\boxed{\lambda=1/8}$, **non** $\lambda=8$. Controllo di sanità: $\lambda$ ha dimensione $[\text{tempo}]^{-1}$.

#### **5.14. Sensibilità, specificità e falsi positivi**

| Nome | Simbolo | Complemento |
|---|---|---|
| Sensibilità (recall) | $P(T^+\mid M)$ | falso negativo $P(T^-\mid M)$ |
| Specificità | $P(T^-\mid M^c)$ | **falso positivo** $P(T^+\mid M^c)=1-\text{specificità}$ |
| PPV (valore predittivo positivo) | $P(M\mid T^+)$ | ← è quello che chiede Bayes |
| NPV | $P(M^c\mid T^-)$ | |

> ⚠️ Se la traccia dà la **specificità** (es. 95%), il tasso di falsi positivi da usare in Bayes è $1-0.95=0.05$. Se invece la traccia dice già *"produce un falso positivo nel 5% dei casi"* (16/01/2026), quel 5% si usa **direttamente**, senza complementare. Sbagliare qui azzera la domanda.

---

### **6. Tavole statistiche: cosa portare e come leggerlo**

#### **6.1. Le tre tavole presenti nella repository**

Sono tutte in [`M2_Variabili_aleatorie/UD4/imgs/`](../M2_Variabili_aleatorie/UD4/) e sono richiamate in [M2/UD4/L2](../M2_Variabili_aleatorie/UD4/L2%20-%20Altre%20considerazioni%20sulla%20Gaussiana.md). **Danno numeri diversi per lo stesso $z$**: prima di usarne una, guardare che valore ha in $z=0$.

| File | Cosa tabula | Valore in $z=0$ | Valore in $z=1$ |
|---|---|:---:|:---:|
| `Pasted image 20260705025738.png` | **area centrale** $A(z)=P(0\le Z\le z)$ | $0.0000$ | $0.3413$ |
| `Pasted image 20260705025828.png` | **cumulativa** $\Phi(z)=P(Z\le z)$ | $0.5000$ | $0.8413$ |
| `Pasted image 20260705025805.png` | **coda destra** $1-\Phi(z)=P(Z>z)$ | $0.5000$ | $0.1587$ |

Conversioni: $\;\Phi(z) = 0.5 + A(z)\;$ e $\;A(z) = \Phi(z)-0.5\;$ e $\;P(Z>z)=1-\Phi(z)$.

> 📌 **Le soluzioni ufficiali degli appelli usano $\Phi(z)$** (la seconda tavola): l'appello del 19/09/2025 scrive "$\Phi(1.78)\approx 0.9625$, $\Phi(-0.81)\approx 0.2090$". Portare quella, o usare la tabella §6.3 qui sotto.

#### **6.2. Le tre regole d'uso della tavola**

$$\Phi(-z) = 1-\Phi(z) \qquad P(X>a)=1-\Phi(z_a) \qquad P(a<X<b)=\Phi(z_b)-\Phi(z_a)$$

Caso simmetrico ($a$ e $b$ equidistanti da $\mu$): $\;P(\mu-k\sigma<X<\mu+k\sigma)=2\Phi(k)-1$.

#### **6.3. Tavola della normale standard $\Phi(z)=P(Z\le z)$**

Riga = prima cifra decimale di $z$; colonna = seconda cifra. Esempio: $\Phi(1.78)$ ⇒ riga **1.7**, colonna `.08` ⇒ $0.9625$.

| $z$ | .00 | .01 | .02 | .03 | .04 | .05 | .06 | .07 | .08 | .09 |
|---|---|---|---|---|---|---|---|---|---|---|
| **0.0** | 0.5000 | 0.5040 | 0.5080 | 0.5120 | 0.5160 | 0.5199 | 0.5239 | 0.5279 | 0.5319 | 0.5359 |
| **0.1** | 0.5398 | 0.5438 | 0.5478 | 0.5517 | 0.5557 | 0.5596 | 0.5636 | 0.5675 | 0.5714 | 0.5753 |
| **0.2** | 0.5793 | 0.5832 | 0.5871 | 0.5910 | 0.5948 | 0.5987 | 0.6026 | 0.6064 | 0.6103 | 0.6141 |
| **0.3** | 0.6179 | 0.6217 | 0.6255 | 0.6293 | 0.6331 | 0.6368 | 0.6406 | 0.6443 | 0.6480 | 0.6517 |
| **0.4** | 0.6554 | 0.6591 | 0.6628 | 0.6664 | 0.6700 | 0.6736 | 0.6772 | 0.6808 | 0.6844 | 0.6879 |
| **0.5** | 0.6915 | 0.6950 | 0.6985 | 0.7019 | 0.7054 | 0.7088 | 0.7123 | 0.7157 | 0.7190 | 0.7224 |
| **0.6** | 0.7257 | 0.7291 | 0.7324 | 0.7357 | 0.7389 | 0.7422 | 0.7454 | 0.7486 | 0.7517 | 0.7549 |
| **0.7** | 0.7580 | 0.7611 | 0.7642 | 0.7673 | 0.7704 | 0.7734 | 0.7764 | 0.7794 | 0.7823 | 0.7852 |
| **0.8** | 0.7881 | 0.7910 | 0.7939 | 0.7967 | 0.7995 | 0.8023 | 0.8051 | 0.8078 | 0.8106 | 0.8133 |
| **0.9** | 0.8159 | 0.8186 | 0.8212 | 0.8238 | 0.8264 | 0.8289 | 0.8315 | 0.8340 | 0.8365 | 0.8389 |
| **1.0** | 0.8413 | 0.8438 | 0.8461 | 0.8485 | 0.8508 | 0.8531 | 0.8554 | 0.8577 | 0.8599 | 0.8621 |
| **1.1** | 0.8643 | 0.8665 | 0.8686 | 0.8708 | 0.8729 | 0.8749 | 0.8770 | 0.8790 | 0.8810 | 0.8830 |
| **1.2** | 0.8849 | 0.8869 | 0.8888 | 0.8907 | 0.8925 | 0.8944 | 0.8962 | 0.8980 | 0.8997 | 0.9015 |
| **1.3** | 0.9032 | 0.9049 | 0.9066 | 0.9082 | 0.9099 | 0.9115 | 0.9131 | 0.9147 | 0.9162 | 0.9177 |
| **1.4** | 0.9192 | 0.9207 | 0.9222 | 0.9236 | 0.9251 | 0.9265 | 0.9279 | 0.9292 | 0.9306 | 0.9319 |
| **1.5** | 0.9332 | 0.9345 | 0.9357 | 0.9370 | 0.9382 | 0.9394 | 0.9406 | 0.9418 | 0.9429 | 0.9441 |
| **1.6** | 0.9452 | 0.9463 | 0.9474 | 0.9484 | 0.9495 | 0.9505 | 0.9515 | 0.9525 | 0.9535 | 0.9545 |
| **1.7** | 0.9554 | 0.9564 | 0.9573 | 0.9582 | 0.9591 | 0.9599 | 0.9608 | 0.9616 | 0.9625 | 0.9633 |
| **1.8** | 0.9641 | 0.9649 | 0.9656 | 0.9664 | 0.9671 | 0.9678 | 0.9686 | 0.9693 | 0.9699 | 0.9706 |
| **1.9** | 0.9713 | 0.9719 | 0.9726 | 0.9732 | 0.9738 | 0.9744 | 0.9750 | 0.9756 | 0.9761 | 0.9767 |
| **2.0** | 0.9772 | 0.9778 | 0.9783 | 0.9788 | 0.9793 | 0.9798 | 0.9803 | 0.9808 | 0.9812 | 0.9817 |
| **2.1** | 0.9821 | 0.9826 | 0.9830 | 0.9834 | 0.9838 | 0.9842 | 0.9846 | 0.9850 | 0.9854 | 0.9857 |
| **2.2** | 0.9861 | 0.9864 | 0.9868 | 0.9871 | 0.9875 | 0.9878 | 0.9881 | 0.9884 | 0.9887 | 0.9890 |
| **2.3** | 0.9893 | 0.9896 | 0.9898 | 0.9901 | 0.9904 | 0.9906 | 0.9909 | 0.9911 | 0.9913 | 0.9916 |
| **2.4** | 0.9918 | 0.9920 | 0.9922 | 0.9925 | 0.9927 | 0.9929 | 0.9931 | 0.9932 | 0.9934 | 0.9936 |
| **2.5** | 0.9938 | 0.9940 | 0.9941 | 0.9943 | 0.9945 | 0.9946 | 0.9948 | 0.9949 | 0.9951 | 0.9952 |
| **2.6** | 0.9953 | 0.9955 | 0.9956 | 0.9957 | 0.9959 | 0.9960 | 0.9961 | 0.9962 | 0.9963 | 0.9964 |
| **2.7** | 0.9965 | 0.9966 | 0.9967 | 0.9968 | 0.9969 | 0.9970 | 0.9971 | 0.9972 | 0.9973 | 0.9974 |
| **2.8** | 0.9974 | 0.9975 | 0.9976 | 0.9977 | 0.9977 | 0.9978 | 0.9979 | 0.9979 | 0.9980 | 0.9981 |
| **2.9** | 0.9981 | 0.9982 | 0.9982 | 0.9983 | 0.9984 | 0.9984 | 0.9985 | 0.9985 | 0.9986 | 0.9986 |
| **3.0** | 0.9987 | 0.9987 | 0.9987 | 0.9988 | 0.9988 | 0.9989 | 0.9989 | 0.9989 | 0.9990 | 0.9990 |
| **3.1** | 0.9990 | 0.9991 | 0.9991 | 0.9991 | 0.9992 | 0.9992 | 0.9992 | 0.9992 | 0.9993 | 0.9993 |
| **3.2** | 0.9993 | 0.9993 | 0.9994 | 0.9994 | 0.9994 | 0.9994 | 0.9994 | 0.9995 | 0.9995 | 0.9995 |
| **3.3** | 0.9995 | 0.9995 | 0.9995 | 0.9996 | 0.9996 | 0.9996 | 0.9996 | 0.9996 | 0.9996 | 0.9997 |
| **3.4** | 0.9997 | 0.9997 | 0.9997 | 0.9997 | 0.9997 | 0.9997 | 0.9997 | 0.9997 | 0.9997 | 0.9998 |

Per $z<0$: $\Phi(-z)=1-\Phi(z)$. Per $z>3.5$: $\Phi(z)\approx 1$.

#### **6.4. Valori critici $z_{\alpha/2}$ (intervalli bilaterali)**

| Livello di confidenza $1-\alpha$ | $\alpha$ | $\alpha/2$ | $z_{\alpha/2}$ |
|---|---|---|---|
| 80% | 0.20 | 0.10 | 1.282 |
| **90%** | 0.10 | 0.05 | **1.645** |
| **95%** | 0.05 | 0.025 | **1.960** |
| 98% | 0.02 | 0.01 | 2.326 |
| **99%** | 0.01 | 0.005 | **2.576** |
| 99.7% | 0.003 | 0.0015 | 2.968 ($\approx 3$) |

Percentili unilaterali di uso frequente: $z_{0.90}=1.282$, $z_{0.95}=1.645$, $z_{0.975}=1.960$, $z_{0.99}=2.326$.

#### **6.5. Regola empirica dei tre sigma**

$$P(\mu-\sigma<X<\mu+\sigma)\approx 68.27\% \qquad P(\mu-2\sigma<X<\mu+2\sigma)\approx 95.45\% \qquad P(\mu-3\sigma<X<\mu+3\sigma)\approx 99.73\%$$

> 💡 **Quando usarla e quando no.** Si usa quando la traccia chiede un ordine di grandezza, un criterio di outlier, o un intervallo con $k$ intero. Si usa la **tavola** quando la soglia non è un multiplo intero di $\sigma$ (es. $z=1.25$, $z=0.625$) o quando serve una probabilità precisa. Da non confondere con i **percentili**: $2\sigma$ dà il 95.45%, ma il 95% **esatto** corrisponde a $1.96\sigma$.

#### **6.6. Altre tavole**

> ⚠️ **Non servono** $t$ di Student, $\chi^2$ o $F$: non compaiono in nessuno degli 8 appelli né in alcuna lezione. Il corso tratta la stima con $\sigma$ nota (o con $n$ grande, dove $t\to z$) e non affronta il test di ipotesi formale. Stampare solo la tavola $\Phi$ (§6.3) e i valori critici (§6.4).

---

### **7. Checklist finale (60 secondi prima di consegnare)**

1. Ogni distribuzione **nominata** per esteso con i suoi parametri numerici?
2. Varianza campionaria con **$n-1$** dove la traccia dice "campione"?
3. Nell'ipergeometrica ho messo il **fattore $\frac{N-n}{N-1}$** nella varianza?
4. Nelle disuguaglianze: `più di 1 e meno di 4` = $\{2,3\}$, estremi **esclusi**?
5. $\lambda = 1/\mu$ e non $\lambda=\mu$ nell'esponenziale?
6. Falso positivo $=1-$ specificità (solo se la traccia dà la specificità)?
7. Correzione di continuità $\pm 0.5$ se ho approssimato una binomiale con la normale?
8. Ogni "si spieghi / si commenti / si interpreti" ha almeno **una frase** di risposta?
9. Le probabilità sono tutte in $[0,1]$ e le posterior sommano a 1?
10. Unità di misura riportate (kg, mesi, °C) e risultati arrotondati a 4 cifre?
