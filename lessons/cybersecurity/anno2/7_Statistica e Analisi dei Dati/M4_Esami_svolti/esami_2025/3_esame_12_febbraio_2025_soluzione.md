# Esame del 12 febbraio 2025 — Soluzione completa

> 📌 **Informazioni appello:** 12/02/2025 — 5 domande, 32 punti totali.
> Argomenti: Dim. varianza somma + WLLN, Statistica descrittiva con frequenze e boxplot, Bayes (macchine), Probabilità su carte, Approssimazione di Poisson.

---

## Domanda 1 — Varianza della somma e legge dei grandi numeri (8 punti)

### Traccia originale

> (a) (4 punti) Date due variabili aleatorie indipendenti, $X$ e $Y$, di valore atteso rispettivamente $\mu_X$ e $\mu_Y$, e di varianza rispettivamente $\sigma_X^2$ e $\sigma_Y^2$, dimostrare, usando la nozione di covarianza, che la varianza della variabile aleatoria $Z = X + Y$ è $\sigma_Z^2 = \sigma_X^2 + \sigma_Y^2$.
>
> (b) (4 punti) Data la disuguaglianza di Chebyshev
>
> $$P(|X - \mu| \geq r) \leq \frac{\sigma^2}{r^2}, \forall r > 0,$$
>
> e la somma di $n$ variabili aleatorie indipendenti e identicamente distribuite, si usi il risultato (a) per dimostrare la legge debole dei grandi numeri.

### Soluzione

#### Parte (a) — $\text{Var}(X + Y) = \sigma_X^2 + \sigma_Y^2$

La **covarianza** tra $X$ e $Y$ è:

$$\text{Cov}(X, Y) = E\bigl[(X - \mu_X)(Y - \mu_Y)\bigr]$$

Per $X, Y$ **indipendenti**: $\text{Cov}(X,Y) = 0$.

Sia $Z = X + Y$, con $\mu_Z = \mu_X + \mu_Y$.

$$\sigma_Z^2 = E\bigl[(Z - \mu_Z)^2\bigr] = E\bigl[((X-\mu_X)+(Y-\mu_Y))^2\bigr]$$

Sviluppando il quadrato:

$$= E\bigl[(X-\mu_X)^2\bigr] + 2E\bigl[(X-\mu_X)(Y-\mu_Y)\bigr] + E\bigl[(Y-\mu_Y)^2\bigr]$$

$$= \sigma_X^2 + 2\,\text{Cov}(X,Y) + \sigma_Y^2 = \sigma_X^2 + 0 + \sigma_Y^2$$

$$\boxed{\sigma_Z^2 = \sigma_X^2 + \sigma_Y^2} \qquad \square$$

---

#### Parte (b) — Legge debole dei grandi numeri

**Enunciato:** Siano $X_1,\ldots,X_n$ i.i.d. con $E[X_i]=\mu$ e $\text{Var}(X_i)=\sigma^2<\infty$. Sia $\bar{X}_n = \frac{1}{n}\sum_{i=1}^n X_i$. Allora per ogni $\varepsilon > 0$:

$$\lim_{n\to\infty} P\bigl(|\bar{X}_n - \mu| \geq \varepsilon\bigr) = 0$$

**Dimostrazione:**

**Passo 1 — Valore atteso di $\bar{X}_n$:**

$$E[\bar{X}_n] = \frac{1}{n}\sum_{i=1}^n E[X_i] = \mu$$

**Passo 2 — Varianza di $\bar{X}_n$ (usando il risultato (a) esteso a $n$ variabili):**

Poiché le $X_i$ sono indipendenti, per estensione di (a):
$$\text{Var}\!\left(\sum_{i=1}^n X_i\right) = n\sigma^2$$

$$\text{Var}(\bar{X}_n) = \frac{1}{n^2} \cdot n\sigma^2 = \frac{\sigma^2}{n}$$

**Passo 3 — Chebyshev applicata a $\bar{X}_n$ con $r = \varepsilon$:**

$$0 \leq P\bigl(|\bar{X}_n - \mu| \geq \varepsilon\bigr) \leq \frac{\text{Var}(\bar{X}_n)}{\varepsilon^2} = \frac{\sigma^2}{n\varepsilon^2} \xrightarrow{n\to\infty} 0$$

Per il teorema del confronto: $\displaystyle\lim_{n\to\infty} P\bigl(|\bar{X}_n - \mu| \geq \varepsilon\bigr) = 0$. $\square$

> 💡 La WLLN dice che $\bar{X}_n$ converge in probabilità a $\mu$: al crescere di $n$, la media campionaria diventa arbitrariamente vicina alla media teorica con probabilità tendente a 1.

---

## Domanda 2 — Statistica descrittiva: età calciatori (8 punti)

### Traccia originale

> Una squadra di calcio dispone di 20 giocatori che, disposti in ordine alfabetico per cognome, riportano le seguenti età: 22, 19, 19, 20, 18, 29, 20, 20, 20, 23, 20, 21, 21, 21, 19, 19, 21, 22, 22, 24.
>
> (a) (2 punti) Si crei una tabella delle frequenze per le età
>
> (b) (1 punto) Si calcoli l'età media dei giocatori
>
> (c) (2 punti) Si calcoli la deviazione standard dei giocatori e si dica se è biased o unbiased
>
> (d) (1 punto) Si identifichino eventuali outlier sulla base della regola dei $3\sigma$.
>
> (e) (2 punti) Si disegni il boxplot che riassume la distribuzione delle età

### Soluzione

#### Parte (a) — Tabella delle frequenze

| Età | Freq. ass. $n_i$ | Freq. rel. $f_i$ | % |
|-----|-----------------|------------------|---|
| 18 | 1 | 0.05 | 5% |
| 19 | 4 | 0.20 | 20% |
| 20 | 5 | 0.25 | 25% |
| 21 | 4 | 0.20 | 20% |
| 22 | 3 | 0.15 | 15% |
| 23 | 1 | 0.05 | 5% |
| 24 | 1 | 0.05 | 5% |
| 29 | 1 | 0.05 | 5% |
| **Tot.** | **20** | **1.00** | **100%** |

---

#### Parte (b) — Età media

$$\bar{x} = \frac{18{\cdot}1+19{\cdot}4+20{\cdot}5+21{\cdot}4+22{\cdot}3+23{\cdot}1+24{\cdot}1+29{\cdot}1}{20} = \frac{420}{20} = \boxed{21 \text{ anni}}$$

---

#### Parte (c) — Deviazione standard

| Età $x_i$ | $f_i$ | $(x_i-21)^2$ | $f_i(x_i-21)^2$ |
|-----------|--------|--------------|-----------------|
| 18 | 1 | 9 | 9 |
| 19 | 4 | 4 | 16 |
| 20 | 5 | 1 | 5 |
| 21 | 4 | 0 | 0 |
| 22 | 3 | 1 | 3 |
| 23 | 1 | 4 | 4 |
| 24 | 1 | 9 | 9 |
| 29 | 1 | 64 | 64 |
| **Σ** | | | **110** |

**Deviazione standard unbiased** (stimatore non distorto, denominatore $n-1=19$):

$$s = \sqrt{\frac{110}{19}} = \sqrt{5.789} \approx \boxed{2.406}$$

**Biased vs unbiased:** La formula con $n-1$ è **unbiased** (non distorta): $E[s^2] = \sigma^2$. La formula con $n$ è biased (distorta) e si usa solo per descrivere il campione in sé. In questo contesto il campione rappresenta la squadra intera, quindi si potrebbe usare $n$, ma la convenzione statistica standard è $n-1$.

---

#### Parte (d) — Outlier con la regola $3\sigma$

Intervallo $[\bar{x} - 3s,\; \bar{x} + 3s] = [21 - 7.22,\; 21 + 7.22] = [13.78,\; 28.22]$

**Il valore 29 > 28.22** → **outlier** secondo la regola dei $3\sigma$.

---

#### Parte (e) — Boxplot

**Dati ordinati:** 18, 19, 19, 19, 19, 20, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 23, 24, 29

Con $n=20$:
- **Mediana** = media pos. 10–11 = $(20+21)/2 = 20.5$
- **$Q_1$** = mediana pos. 1–10 = media pos. 5–6 = $(19+20)/2 = 19.5$
- **$Q_3$** = mediana pos. 11–20 = media pos. 15–16 = $(22+22)/2 = 22$
- **IQR** = $22 - 19.5 = 2.5$
- **Baffo inf.:** $Q_1 - 1.5\cdot\text{IQR} = 15.75$ → valore minimo interno: **18**
- **Baffo sup.:** $Q_3 + 1.5\cdot\text{IQR} = 25.75$ → valore massimo interno: **24**
- **Outlier:** **29** (punto isolato oltre il baffo superiore)

$$18 \;[\; 19.5 \mid 20.5 \mid 22 \;]\; 24 \quad \bullet\,29$$

---

## Domanda 3 — Bayes: macchine A, B, C (4 punti)

### Traccia originale

> Un'azienda produce componenti usando le macchine A, B, e C.
>
> - La macchina A produce il 50% dei componenti, e l'1% dei componenti che produce sono difettosi;
> - La macchina B produce il 30% dei componenti, e il 2% dei componenti che produce sono difettosi;
> - La macchina C produce il 20% dei componenti, e il 3% dei componenti che produce sono difettosi.
>
> Si supponga di prendere un componente a caso tra quelli prodotti, e di trovarlo difettoso.
>
> (a) (4 punti) Qual è la probabilità che tale componente sia stato prodotto dalla macchina A?

### Soluzione

**Dati:** $P(A)=0.5,\;P(D|A)=0.01$; $\;P(B)=0.3,\;P(D|B)=0.02$; $\;P(C)=0.2,\;P(D|C)=0.03$.

**Probabilità totale di un difetto:**

$$P(D) = 0.01\times 0.5 + 0.02\times 0.3 + 0.03\times 0.2 = 0.005 + 0.006 + 0.006 = 0.017$$

**Teorema di Bayes:**

$$P(A \mid D) = \frac{P(D\mid A)\cdot P(A)}{P(D)} = \frac{0.005}{0.017} = \frac{5}{17} \approx \boxed{29.4\%}$$

> 💡 Pur producendo il 50% dei componenti, la macchina A ha il tasso di difettosità più basso (1%). Tra i difettosi solo ~29% viene da A — meno della sua quota produttiva — confermando che i difetti si concentrano nelle macchine B e C.

---

## Domanda 4 — Probabilità su mazzo di carte (6 punti)

### Traccia originale

> Si consideri un mazzo di 52 carte, con semi (cuori, quadri, picche, spade), ciascun seme con 13 valori (Asso, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Regina, Re). Estraendo due carte dal mazzo
>
> (a) (2 punti) qual è la probabilità di estrarre due cuori?
>
> (b) (2 punti) qual è la probabilità di estrarre almeno un Asso?
>
> (c) (2 punti) qual è la probabilità di estrarre almeno una carta di cuori o almeno una figura (Jack, Regina, o Re)?

### Soluzione

$|\Omega| = \binom{52}{2} = 1326$.

---

#### Parte (a) — Due cuori

$$P(\text{2 cuori}) = \frac{\binom{13}{2}}{\binom{52}{2}} = \frac{78}{1326} = \frac{1}{17} \approx \boxed{0.0588}$$

---

#### Parte (b) — Almeno un Asso (complemento)

$$P(\text{nessun Asso}) = \frac{\binom{48}{2}}{1326} = \frac{1128}{1326}$$

$$P(\text{almeno un Asso}) = 1 - \frac{1128}{1326} = \frac{198}{1326} = \frac{33}{221} \approx \boxed{0.1493}$$

---

#### Parte (c) — Almeno un cuore O almeno una figura

Figure (J, Q, K) non di cuori: $3\times 3 = 9$. Carte né cuori né figure: $52 - 13 - 9 = 30$.

$$P(\text{né cuori né figure}) = \frac{\binom{30}{2}}{1326} = \frac{435}{1326}$$

$$P(\text{almeno un cuore o figura}) = 1 - \frac{435}{1326} = \frac{891}{1326} = \frac{297}{442} \approx \boxed{0.6719}$$

---

## Domanda 5 — Approssimazione di Poisson: uova di cioccolata (6 punti)

### Traccia originale

> Un produttore di uova di cioccolata immette in commercio uova con sorpresa, e mediamente 1 uovo su 200 contiene una collanina d'argento. Se un negoziante acquista un lotto da 500 uova
>
> (a) (2 punti) qual è la probabilità che in questo lotto ci siano esattamente 3 collanine? (non serve dare un numero finale, basta la formulazione della soluzione)
>
> (b) (2 punti) qual è la probabilità approssimata che in questo lotto ci siano esattamente 3 collanine?
>
> (c) (2 punti) qual è la probabilità che vi siano nel lotto al più 2 collanine?

### Soluzione

$p = 1/200$, $n = 500$, $\lambda = np = 2.5$.

---

#### Parte (a) — Formula esatta Binomiale

$$P(X=3) = \binom{500}{3}\left(\frac{1}{200}\right)^3\left(\frac{199}{200}\right)^{497}$$

$$\binom{500}{3} = \frac{500\times 499\times 498}{6} = 20{,}708{,}500$$

> Non si richiede il valore numerico finale: la formula binomiale con $n=500$ è computazionalmente onerosa (si deve calcolare $(199/200)^{497}$). Si usa l'approssimazione di Poisson alla parte (b).

---

#### Parte (b) — Approssimazione di Poisson ($\lambda = 2.5$)

Con $n$ grande e $p$ piccolo, $X \approx \text{Poisson}(\lambda=2.5)$:

$$P(X=3) \approx \frac{e^{-2.5}\cdot 2.5^3}{3!} = \frac{0.08208 \times 15.625}{6} \approx \boxed{0.2138}$$

---

#### Parte (c) — $P(X \leq 2)$

$$P(X\leq 2) = e^{-2.5}\!\left(1 + 2.5 + \frac{6.25}{2}\right) = 0.08208\times 6.625 \approx \boxed{0.5438}$$

Più della metà dei lotti da 500 uova contiene al più 2 collanine.

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1a | 4 | $\text{Var}(X+Y)=\sigma_X^2+\sigma_Y^2$ via $\text{Cov}(X,Y)=0$ |
| 1b | 4 | WLLN: $\text{Var}(\bar{X}_n)=\sigma^2/n\to 0$ + Chebyshev |
| 2 | 8 | $\bar{x}=21$, $s\approx 2.406$ (unbiased), outlier: 29; boxplot: $Q_1=19.5$, med=20.5, $Q_3=22$ |
| 3 | 4 | Bayes: $P(A\mid D)=5/17\approx 29.4\%$ |
| 4 | 6 | $P(\text{2♥})=1/17$; $P(\geq\text{1A})=33/221$; $P(\text{♥ o figura})=297/442\approx 67.2\%$ |
| 5 | 6 | Poisson($\lambda=2.5$): formula esatta; $P(X=3)\approx 0.214$; $P(X\leq 2)\approx 0.544$ |
