# Esame del 23 luglio 2025 — Soluzione completa

> 📌 **Informazioni appello:** 23/07/2025 — 5 domande, 32 punti totali.
> Argomenti: Binomiale, Statistica descrittiva, Teorema di Bayes, Poisson, Gaussiana.

---

## Domanda 1 — Distribuzione Binomiale (6 punti)

### Traccia originale

> Un'azienda produce componenti meccanici e si sa che, in media, il 5% dei componenti prodotti sono difettosi. Un ispettore raccoglie un campione casuale di 20 elementi da un grande lotto. Assumendo che la produzione di ciascun componente sia indipendente da quella di ciascun altro, si calcolino
>
> (a) (2 punti) la probabilità che esattamente due componenti del campione siano difettosi;
>
> (b) (2 punti) il numero atteso di componenti difettosi nel campione, e la deviazione standard del numero di componenti difettosi nel campione;
>
> (c) (2 punti) la probabilità che ci siano più di tre componenti difettosi nel campione.

### Soluzione

#### Modello

La variabile aleatoria $X$ = numero di componenti difettosi nel campione segue la distribuzione **Binomiale**:

$$X \sim B(n, p) = B(20, 0.05)$$

$$P(X = k) = \binom{20}{k} (0.05)^k (0.95)^{20-k}$$

---

#### Parte (a) — $P(X = 2)$

$$P(X = 2) = \binom{20}{2} (0.05)^2 (0.95)^{18}$$

Calcolo passo per passo:

$$\binom{20}{2} = \frac{20 \cdot 19}{2} = 190$$

$$0.95^{18}: \quad 0.95^2 = 0.9025, \quad 0.95^4 = 0.8145, \quad 0.95^8 = 0.6634, \quad 0.95^{16} = 0.4401$$
$$0.95^{18} = 0.95^{16} \times 0.95^2 = 0.4401 \times 0.9025 = 0.3972$$

$$P(X = 2) = 190 \times 0.0025 \times 0.3972 = 0.475 \times 0.3972 \approx \boxed{0.1887}$$

---

#### Parte (b) — Valore atteso e deviazione standard

$$E[X] = np = 20 \times 0.05 = \boxed{1}$$

$$\text{Var}(X) = np(1-p) = 20 \times 0.05 \times 0.95 = 0.95$$

$$\sigma_X = \sqrt{0.95} \approx \boxed{0.975}$$

> 💡 In media ci si aspetta 1 componente difettoso su 20; la deviazione standard di ~0.975 indica che la variabilità è comparabile al valore atteso, tipico di distribuzioni con $p$ piccolo.

---

#### Parte (c) — $P(X > 3)$

$$P(X > 3) = 1 - P(X \leq 3) = 1 - \bigl[P(X=0) + P(X=1) + P(X=2) + P(X=3)\bigr]$$

Valori delle potenze: $0.95^{17} = 0.4181$, $0.95^{18} = 0.3972$, $0.95^{19} = 0.3773$, $0.95^{20} = 0.3585$.

$$P(X=0) = 0.95^{20} = 0.3585$$

$$P(X=1) = 20 \times 0.05 \times 0.95^{19} = 0.3773$$

$$P(X=2) = 0.1887 \quad \text{(calcolato sopra)}$$

$$P(X=3) = \binom{20}{3}(0.05)^3(0.95)^{17} = 1140 \times 0.000125 \times 0.4181 \approx 0.0596$$

$$P(X \leq 3) = 0.3585 + 0.3773 + 0.1887 + 0.0596 = 0.9841$$

$$P(X > 3) = 1 - 0.9841 = \boxed{0.0159}$$

> ✅ La probabilità di avere più di 3 difettosi su 20 è circa l'1.6%, coerentemente con un parametro $p = 5\%$ basso.

---

## Domanda 2 — Statistica descrittiva (8 punti)

### Traccia originale

> Una grande centro fitness raccoglie i pesi (in kg) di sette individui raccolti a caso tra i suoi utenti: 65, 70, 80, 85, 90, 72, 75. Si calcolino
>
> (a) (2 punti) il peso mediano del campione
>
> (b) (2 punti) la deviazione standard campionaria
>
> (c) (2 punti) il primo e il terzo quartile della distribuzione dei pesi
>
> (d) (2 punti) la skewness campionaria, descrivendo la asimmetria della distribuzione

### Soluzione

**Dati ordinati in senso crescente:** $65, 70, 72, 75, 80, 85, 90$ — $n = 7$.

---

#### Parte (a) — Mediana

Con $n = 7$ (dispari), la mediana è il valore in posizione $\frac{n+1}{2} = 4$:

$$\tilde{x} = \boxed{75 \text{ kg}}$$

---

#### Parte (b) — Deviazione standard campionaria

**Media campionaria:**

$$\bar{x} = \frac{65 + 70 + 72 + 75 + 80 + 85 + 90}{7} = \frac{537}{7} \approx 76.71 \text{ kg}$$

**Scarti al quadrato:**

| $x_i$ | $x_i - \bar{x}$ | $(x_i - \bar{x})^2$ |
|--------|-----------------|----------------------|
| 65 | $-11.71$ | $137.22$ |
| 70 | $-6.71$ | $45.08$ |
| 72 | $-4.71$ | $22.22$ |
| 75 | $-1.71$ | $2.94$ |
| 80 | $+3.29$ | $10.80$ |
| 85 | $+8.29$ | $68.66$ |
| 90 | $+13.29$ | $176.52$ |
| **Σ** | | **463.44** |

**Varianza campionaria** (denominatore $n-1 = 6$, stimatore non distorto):

$$s^2 = \frac{463.44}{6} = 77.24, \qquad s = \sqrt{77.24} \approx \boxed{8.79 \text{ kg}}$$

---

#### Parte (c) — Primo e terzo quartile

Con $n = 7$, si dividono i dati escludendo la mediana:

- **Metà inferiore:** $65, 70, 72$ → $Q_1 = 70$
- **Metà superiore:** $80, 85, 90$ → $Q_3 = 85$

$$\boxed{Q_1 = 70 \text{ kg}, \quad Q_3 = 85 \text{ kg}}$$

---

#### Parte (d) — Skewness campionaria

La skewness di Fisher è:

$$g_1 = \frac{n}{(n-1)(n-2)} \sum_{i=1}^{n} \left(\frac{x_i - \bar{x}}{s}\right)^3$$

**Valori standardizzati** $z_i = (x_i - \bar{x})/s$ e relative potenze al cubo ($s \approx 8.789$):

| $x_i$ | $z_i$ | $z_i^3$ |
|--------|--------|---------|
| 65 | $-1.333$ | $-2.369$ |
| 70 | $-0.764$ | $-0.446$ |
| 72 | $-0.536$ | $-0.154$ |
| 75 | $-0.195$ | $-0.007$ |
| 80 | $+0.374$ | $+0.052$ |
| 85 | $+0.943$ | $+0.838$ |
| 90 | $+1.512$ | $+3.454$ |
| **Σ** | | **$+1.368$** |

$$g_1 = \frac{7}{6 \times 5} \times 1.368 = \frac{7}{30} \times 1.368 \approx \boxed{+0.319}$$

**Interpretazione:** La skewness è positiva ($g_1 > 0$), quindi la distribuzione è **asimmetrica a destra** (coda più lunga verso i valori elevati). Questo è coerente con $\bar{x} = 76.71 > \tilde{x} = 75$.

---

## Domanda 3 — Teorema di Bayes (4 punti)

### Traccia originale

> La malattia "Morbus Rarissimus" colpisce lo 0.1% della popolazione adulta. Esiste un test diagnostico per questa malattia che ha le seguenti caratteristiche:
>
> - Se una persona è malata, il test risulta positivo nel 98% dei casi.
> - Se una persona non è malata, il test risulta positivo solo nell'1% dei casi.
>
> Una persona adulta a caso si sottopone al test, e il risultato è positivo.
>
> (a) (1 punto) Qual è la probabilità a priori che una persona scelta a caso dalla popolazione sia affetta dal "Morbus Rarissimus"? Qual è la probabilità a priori che una persona scelta a caso dalla popolazione non sia affetta dal "Morbus Rarissimus"?
>
> (b) (1 punto) Si calcoli la probabilità che il test sia positivo se la persona è effettivamente malata. Si calcoli la probabilità che il test sia positivo se la persona non è malata.
>
> (c) (2 punti) Si calcoli la probabilità che la persona sia effettivamente affetta dal "Morbus Rarissimus", dato che il suo test è risultato positivo. Si spieghi il significato di questo risultato.

### Soluzione

**Definizione degli eventi:**
- $M$: la persona è malata; $M^c$: la persona è sana; $T^+$: il test è positivo

---

#### Parte (a) — Probabilità a priori

$$P(M) = 0.001 = 0.1\%, \qquad P(M^c) = 0.999 = 99.9\%$$

---

#### Parte (b) — Verosimiglianze

$$P(T^+ \mid M) = 0.98, \qquad P(T^+ \mid M^c) = 0.01$$

---

#### Parte (c) — Probabilità a posteriori (Bayes)

**Probabilità totale del test positivo:**

$$P(T^+) = 0.98 \times 0.001 + 0.01 \times 0.999 = 0.00098 + 0.00999 = 0.01097$$

**Teorema di Bayes:**

$$P(M \mid T^+) = \frac{P(T^+ \mid M) \cdot P(M)}{P(T^+)} = \frac{0.00098}{0.01097} \approx \boxed{8.93\%}$$

> ⚠️ **Paradosso del test diagnostico:** Anche con sensibilità 98%, la probabilità di essere malati dopo un positivo è solo ~9%. Questo è dovuto alla rarità estrema della malattia (0.1%): su ~1000 persone, ~10 sono false positive per ogni vera positiva.

---

## Domanda 4 — Distribuzione di Poisson (6 punti)

### Traccia originale

> Un servizio clienti effettua il monitoraggio delle chiamate in ingresso. Si osserva che, in media, il centro riceve cinque chiamate in ogni ora di servizio. Assumendo che le chiamate in arrivo siano indipendenti e che il tasso di arrivo sia costante,
>
> (a) (2 punti) si definisca la variabile aleatoria X che rappresenta il numero di chiamate ricevute in un'ora, se ne descriva la distribuzione di probabilità, e si spieghi cosa rappresenta il suo parametro, nel contesto del problema;
>
> (b) (2 punti) si calcoli la probabilità che il servizio riceva esattamente tre chiamate in una certa ora;
>
> (c) (2 punti) si calcoli la probabilità che il servizio riceva al più due chiamate in una certa ora, e si spieghi cosa ciò implica per il servizio fornito agli utenti.

### Soluzione

#### Parte (a) — Modello

$$X \sim \text{Poisson}(\lambda = 5), \qquad P(X = k) = \frac{e^{-5} \cdot 5^k}{k!}$$

Il parametro $\lambda = 5$ è il **tasso medio di arrivo** (5 chiamate/ora): è sia il valore atteso che la varianza di $X$.

---

#### Parte (b) — $P(X = 3)$

$$P(X = 3) = \frac{e^{-5} \cdot 125}{6} = 0.006738 \times 20.833 \approx \boxed{0.1404}$$

---

#### Parte (c) — $P(X \leq 2)$

$$P(X \leq 2) = e^{-5}(1 + 5 + 12.5) = 0.006738 \times 18.5 \approx \boxed{0.1247}$$

Con circa il 12.5% di probabilità il servizio riceve al più 2 chiamate in un'ora. Nella grande maggioranza delle ore il carico supera le 2 chiamate, quindi dimensionare il servizio per sole 2 unità/ora sarebbe inadeguato.

---

## Domanda 5 — Distribuzione Normale (8 punti)

### Traccia originale

> I punteggi ottenuti dagli studenti in un test standardizzato seguono una distribuzione normale. Si osservano un punteggio medio di 75 punti, e una deviazione standard di 8 punti.
>
> (a) (2 punti) Si definisca la variabile aleatoria X che rappresenta il punteggio di uno studente nel test, se ne identifichi la distribuzione di probabilità, e se ne evidenzino i parametri con i valori da essi assunti.
>
> (b) (2 punti) si calcoli la probabilità che uno studente scelto a caso realizzi un punteggio compreso tra 70 e 80 punti. Si spieghi perché la probabilità che uno studente ottenga esattamente 75 punti è zero per questo tipo di distribuzione.
>
> (c) (2 punti) Si calcoli la probabilità che uno studente ottenga un punteggio non superiore a 65 punti. Si calcoli la probabilità che uno studente ottenga un punteggio maggiore di 90 punti.
>
> (d) (2 punti) Si determini il punteggio corrispondente al novantesimo percentile.

### Soluzione

#### Parte (a) — Modello

$$X \sim \mathcal{N}(\mu, \sigma^2) = \mathcal{N}(75, 64)$$

Parametri: $\mu = 75$ (punteggio medio), $\sigma = 8$ (dispersione).

---

#### Parte (b) — $P(70 \leq X \leq 80)$

$$z_1 = \frac{70 - 75}{8} = -0.625, \qquad z_2 = \frac{80 - 75}{8} = +0.625$$

$$P(70 \leq X \leq 80) = 2\Phi(0.625) - 1 = 2 \times 0.7340 - 1 = \boxed{0.4680}$$

> 📌 Per una variabile aleatoria **continua**, $P(X = 75) = 0$: la misura di un singolo punto è nulla rispetto all'integrale della densità.

---

#### Parte (c) — Probabilità di coda

$$P(X \leq 65): \quad z = \frac{65-75}{8} = -1.25 \quad \Rightarrow \quad \Phi(-1.25) = 1 - 0.8944 = \boxed{0.1056}$$

$$P(X > 90): \quad z = \frac{90-75}{8} = 1.875 \quad \Rightarrow \quad 1 - \Phi(1.875) = 1 - 0.9696 = \boxed{0.0304}$$

---

#### Parte (d) — Novantesimo percentile

$$z_{0.90} = 1.282 \quad \Rightarrow \quad x_{0.90} = 75 + 1.282 \times 8 = \boxed{85.26 \text{ punti}}$$

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1 | 6 | $B(20,0.05)$: $P(X=2)\approx 0.189$, $E[X]=1$, $\sigma\approx 0.975$, $P(X>3)\approx 0.016$ |
| 2 | 8 | Mediana=75, $s\approx 8.79$, $Q_1=70$, $Q_3=85$, $g_1\approx+0.319$ (asimm. destra) |
| 3 | 4 | Bayes: $P(M\mid T^+)\approx 8.93\%$ |
| 4 | 6 | Poisson($\lambda=5$): $P(X=3)\approx 0.140$, $P(X\leq 2)\approx 0.125$ |
| 5 | 8 | $\mathcal{N}(75,64)$: $P(70\leq X\leq 80)\approx 0.468$, $P(X\leq 65)\approx 0.106$, $P(X>90)\approx 0.030$, $x_{0.90}\approx 85.26$ |
