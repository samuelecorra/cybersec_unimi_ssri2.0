# Esame del 5 luglio 2025 — Soluzione completa

> 📌 **Informazioni appello:** 05/07/2025 — 5 domande, 32 punti totali.
> Argomenti: Statistica descrittiva, Densità continua, Distribuzione ipergeometrica, Gaussiana, Bayes con sensitivity/specificity.

---

## Domanda 1 — Statistica descrittiva: punteggi maturità (6 punti)

### Traccia originale

> Un gruppo di amici affronta l'esame di maturità, conseguendo i punteggi 78, 85, 72, 90, 88, 75, 92, 80, 85, 91. Calcolare le seguenti statistiche descrittive per questo set di dati, mostrando i passaggi chiave:
>
> (a) (2 punti) Mediana
>
> (b) (2 punti) Varianza campionaria
>
> (c) (2 punti) Terzo quartile

### Soluzione

**Dati in ordine crescente ($n = 10$):** $72, 75, 78, 80, 85, 85, 88, 90, 91, 92$.

---

#### Parte (a) — Mediana

Con $n = 10$ (pari), la mediana è la media degli elementi in posizione 5 e 6:

$$\tilde{x} = \frac{x_{(5)} + x_{(6)}}{2} = \frac{85 + 85}{2} = \boxed{85}$$

---

#### Parte (b) — Varianza campionaria

**Media campionaria:**

$$\bar{x} = \frac{78+85+72+90+88+75+92+80+85+91}{10} = \frac{836}{10} = 83.6$$

**Scarti al quadrato** $(x_i - \bar{x})^2$:

| $x_i$ | $x_i - 83.6$ | $(x_i - 83.6)^2$ |
|--------|--------------|------------------|
| 78 | $-5.6$ | $31.36$ |
| 85 | $+1.4$ | $1.96$ |
| 72 | $-11.6$ | $134.56$ |
| 90 | $+6.4$ | $40.96$ |
| 88 | $+4.4$ | $19.36$ |
| 75 | $-8.6$ | $73.96$ |
| 92 | $+8.4$ | $70.56$ |
| 80 | $-3.6$ | $12.96$ |
| 85 | $+1.4$ | $1.96$ |
| 91 | $+7.4$ | $54.76$ |
| **Σ** | | **$442.40$** |

$$s^2 = \frac{442.40}{n-1} = \frac{442.40}{9} \approx \boxed{49.16}$$

---

#### Parte (c) — Terzo quartile $Q_3$

Con $n = 10$:
- **Metà inferiore** (pos. 1–5): $72, 75, 78, 80, 85$ → $Q_1 = 78$
- **Metà superiore** (pos. 6–10): $85, 88, 90, 91, 92$ → $Q_3 = 90$

$$\boxed{Q_3 = 90}$$

---

## Domanda 2 — Densità di probabilità continua (6 punti)

### Traccia originale

> Una variabile aleatoria X ha densità di probabilità f(x) = Cx² per 0 ≤ x ≤ 3, f(x) = 0 altrove, dove C è una costante reale.
>
> (a) (2 punti) Si determini il valore della costante C, tale da produrre una densità di probabilità valida
>
> (b) (2 punti) Si trovi la funzione di distribuzione cumulativa F(x), per la variabile aleatoria X
>
> (c) (2 punti) Si calcoli la probabilità P(1 < X ≤ 2) usando la cumulativa.

### Soluzione

#### Parte (a) — Determinare la costante $C$

$$\int_0^3 C x^2\,dx = 1 \quad \Rightarrow \quad C \left[\frac{x^3}{3}\right]_0^3 = C \cdot 9 = 1 \quad \Rightarrow \quad \boxed{C = \frac{1}{9}}$$

Quindi $f(x) = \dfrac{x^2}{9}$ per $0 \leq x \leq 3$.

---

#### Parte (b) — Funzione di distribuzione cumulativa $F(x)$

$$F(x) = \begin{cases} 0 & \text{se } x < 0 \\ \displaystyle\int_0^x \frac{t^2}{9}\,dt = \frac{x^3}{27} & \text{se } 0 \leq x \leq 3 \\ 1 & \text{se } x > 3 \end{cases}$$

**Verifica:** $F(3) = 27/27 = 1$. ✓

---

#### Parte (c) — $P(1 < X \leq 2)$

$$P(1 < X \leq 2) = F(2) - F(1) = \frac{8}{27} - \frac{1}{27} = \frac{7}{27} \approx \boxed{0.2593}$$

---

## Domanda 3 — Distribuzione ipergeometrica (8 punti)

### Traccia originale

> Un fornitore ha spedito a un cliente una scatola contenente 20 componenti elettronici. Si sa che 5 di questi componenti sono nuovi, mentre i rimanenti 15 sono usati. Un tecnico fa un controllo casuale di qualità selezionando 4 componenti dalla scatola. Sia X la variabile aleatoria che rappresenta il numero di componenti nuovi selezionati nel campione casuale. Si calcolino:
>
> (a) (2 punti) la probabilità che esattamente 2 dei componenti siano nuovi;
>
> (b) (2 punti) la probabilità che almeno 3 dei componenti selezionati siano nuovi;
>
> (c) (2 punti) il numero atteso di componenti nuovi nel campione;
>
> (d) (2 punti) la varianza del numero di componenti nuovi nel campione.

### Soluzione

#### Modello

$X \sim \text{Ipergeometrica}(N=20,\; K=5,\; n=4)$

$$P(X = k) = \frac{\dbinom{5}{k}\dbinom{15}{4-k}}{\dbinom{20}{4}}, \qquad \binom{20}{4} = 4845$$

---

#### Parte (a) — $P(X = 2)$

$$P(X = 2) = \frac{\binom{5}{2}\binom{15}{2}}{4845} = \frac{10 \times 105}{4845} = \frac{1050}{4845} \approx \boxed{0.2167}$$

---

#### Parte (b) — $P(X \geq 3)$

$$P(X = 3) = \frac{\binom{5}{3}\binom{15}{1}}{4845} = \frac{10 \times 15}{4845} = \frac{150}{4845} \approx 0.03096$$

$$P(X = 4) = \frac{\binom{5}{4}\binom{15}{0}}{4845} = \frac{5}{4845} \approx 0.001032$$

$$P(X \geq 3) = \frac{155}{4845} = \frac{31}{969} \approx \boxed{0.0320}$$

---

#### Parte (c) — Valore atteso

$$E[X] = n \cdot \frac{K}{N} = 4 \times \frac{5}{20} = \boxed{1}$$

---

#### Parte (d) — Varianza

$$\text{Var}(X) = n \cdot \frac{K}{N} \cdot \frac{N-K}{N} \cdot \underbrace{\frac{N-n}{N-1}}_{\text{correzione pop. finita}} = 4 \times \frac{1}{4} \times \frac{3}{4} \times \frac{16}{19} = \frac{12}{19} \approx \boxed{0.6316}$$

> 💡 Il fattore $(N-n)/(N-1)$ è la **correzione per la popolazione finita**: poiché si estrae senza reinserimento da un insieme di soli 20 elementi, la varianza è minore di quella binomiale ($np(1-p) = 0.75$).

---

## Domanda 4 — Distribuzione Normale: QI (6 punti)

### Traccia originale

> Il Quoziente di Intelligenza (QI) è generalmente modellato secondo una distribuzione Normale, con media μ = 100.0 e deviazione standard σ = 16.0. Sia X la variabile aleatoria che rappresenta il QI individuale. Si calcolino
>
> (a) (2 punti) la probabilità che un individuo estratto a caso abbia QI < 84;
>
> (b) (2 punti) la probabilità che un individuo estratto a caso abbia IQ > 124;
>
> (c) (2 punti) la probabilità che dieci individui estratti a caso abbiano tutti 92 < IQ < 108.

### Soluzione

$X \sim \mathcal{N}(100, 256)$, $\sigma = 16$.

---

#### Parte (a) — $P(X < 84)$

$$z = \frac{84 - 100}{16} = -1 \quad \Rightarrow \quad P(X < 84) = \Phi(-1) = 1 - 0.8413 = \boxed{0.1587}$$

---

#### Parte (b) — $P(X > 124)$

$$z = \frac{124 - 100}{16} = 1.5 \quad \Rightarrow \quad P(X > 124) = 1 - \Phi(1.5) = 1 - 0.9332 = \boxed{0.0668}$$

---

#### Parte (c) — 10 individui con $92 < \text{QI} < 108$

**Probabilità per un singolo individuo:**

$$z_1 = \frac{92-100}{16} = -0.5, \quad z_2 = \frac{108-100}{16} = +0.5$$

$$p = 2\Phi(0.5) - 1 = 2 \times 0.6915 - 1 = 0.3830$$

**Per 10 individui indipendenti:**

$$P^{10} = (0.3830)^{10} = (0.3830^5)^2 = (0.008243)^2 \approx \boxed{6.8 \times 10^{-5}}$$

> ⚠️ La probabilità che tutti e 10 rientrino nell'intervallo è ~0.007%: la coincidenza di 10 eventi indipendenti (ciascuno con $p \approx 38\%$) è molto rara.

---

## Domanda 5 — Bayes: sensitivity e specificity (6 punti)

### Traccia originale

> Un test diagnostico per una malattia è basato su dati epidemiologici, che indicano che l'1% della popolazione soffre di questa malattia. Il test ha le seguenti caratteristiche:
>
> Sensitivity: Se una persona ha la malattia, il test la identifica correttamente come malata nel 95% dei casi.
>
> Specificity: Se una persona non è malata, il test dà correttamente un risultato negativo nel 90% dei casi.
>
> Definiti gli eventi
>
> - D: l'individuo ha la malattia;
> - Dc: l'individuo è sano;
> - T+: il risultato del test è positivo;
> - T−: il risultato del test è negativo;
>
> si calcolino
>
> (a) (2 punti) la probabilità che un individuo sottoposto al test consegua un risultato positivo;
>
> (b) (2 punti) la probabilità che un individuo sia effettivamente malato, dato che il suo test ha risultato positivo;
>
> (c) (2 punti) la probabilità che un individuo sia effettivamente sano, dato che il risultato del suo test è negativo.

### Soluzione

**Dati:**
$$P(D) = 0.01,\quad P(D^c) = 0.99$$
$$P(T^+\mid D) = 0.95 \quad (\text{sensitivity}), \quad P(T^-\mid D^c) = 0.90 \quad (\text{specificity})$$
$$P(T^+\mid D^c) = 1 - 0.90 = 0.10 \quad (\text{falso positivo})$$

---

#### Parte (a) — $P(T^+)$

$$P(T^+) = P(T^+\mid D)\cdot P(D) + P(T^+\mid D^c)\cdot P(D^c) = 0.95\times 0.01 + 0.10\times 0.99$$
$$= 0.0095 + 0.0990 = \boxed{0.1085}$$

---

#### Parte (b) — $P(D \mid T^+)$ — Valore Predittivo Positivo (PPV)

$$P(D \mid T^+) = \frac{P(T^+\mid D)\cdot P(D)}{P(T^+)} = \frac{0.0095}{0.1085} \approx \boxed{0.0876 \approx 8.76\%}$$

> ⚠️ Specificità del 90% (10% falsi positivi) + bassa prevalenza (1%) → PPV basso. Circa il 91% dei positivi è in realtà sano: il test non è sufficiente da solo per diagnosticare, servono esami di conferma.

---

#### Parte (c) — $P(D^c \mid T^-)$ — Valore Predittivo Negativo (NPV)

$$P(T^-) = 1 - P(T^+) = 0.8915$$

$$P(D^c \mid T^-) = \frac{P(T^-\mid D^c)\cdot P(D^c)}{P(T^-)} = \frac{0.90 \times 0.99}{0.8915} = \frac{0.8910}{0.8915} \approx \boxed{0.9994 \approx 99.94\%}$$

> 💡 Un test negativo è molto rassicurante (99.94% di probabilità di essere sani): l'alta sensitivity garantisce che i malati vengano quasi sempre intercettati, lasciando pochissimi falsi negativi.

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1 | 6 | Mediana=85, $s^2\approx 49.16$, $Q_3=90$ |
| 2 | 6 | $C=1/9$; $F(x)=x^3/27$; $P(1<X\leq 2)=7/27\approx 0.259$ |
| 3 | 8 | Ipergeom.: $P(X=2)\approx 0.217$, $P(X\geq 3)\approx 0.032$, $E[X]=1$, $\text{Var}(X)=12/19\approx 0.632$ |
| 4 | 6 | $\mathcal{N}(100,256)$: $P(X<84)=0.159$, $P(X>124)=0.067$, $P(\text{tutti 10 in }[92,108])\approx 6.8\times10^{-5}$ |
| 5 | 6 | Bayes: $P(T^+)=0.1085$, PPV≈8.76%, NPV≈99.94% |
