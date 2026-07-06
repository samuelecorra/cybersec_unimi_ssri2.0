# Esame del 19 settembre 2025 (online) — Soluzione completa

> 📌 **Informazioni appello:** 19/09/2025 (online) — 4 domande, 32 punti totali.
> Argomenti: Ipergeometrica, Binomiale + approssimazione normale con correzione di continuità, Bayes vs stima frequentista con IC, Statistica descrittiva completa (quartili, CV, correlazione di Pearson).
> ⚠️ Formulario, tavole statistiche e calcolatrice ammessi; nessun altro strumento.

---

## Domanda 1 — Lenti difettose: distribuzione Ipergeometrica (7 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD5/L1 — Distribuzione Ipergeometrica](../../M2_Variabili_aleatorie/UD5_Approfondimenti_Esame/L1%20-%20Distribuzione%20Ipergeometrica.md) (lezione integrativa dedicata: formula, momenti, correzione per popolazione finita)
> - [M1/UD2/L2 — Esperimenti compositi](../../M1_Probabilita_elementare/UD2/L2%20-%20Esperimenti%20compositi.md) (estrazioni senza reinserimento, conteggio combinatorio)
> - Esercizi analoghi già svolti: [Esame 05/07/2025 — Domanda 3](2_esame_5_luglio_2025_soluzione.md) e [Esame 15/01/2025 — Domanda 1](4_esame_15_gennaio_2025_soluzione.md) (l'ipergeometrica compare sistematicamente negli appelli)

### Traccia originale

> Un produttore di occhiali ha un lotto di 300 lenti in totale. Da un'ispezione precedente, si sa che 25 di queste lenti sono difettose. Un ispettore di qualità seleziona casualmente un campione di 20 lenti dal lotto per un'analisi dettagliata, senza reintrodurre le lenti già ispezionate nel lotto. Sia $X$ la variabile aleatoria che rappresenta il numero di lenti difettose trovate nel campione selezionato.
>
> (a) (2 punti) Qual è la probabilità che il campione contenga esattamente 2 lenti difettose?
>
> (b) (2 punti) Qual è la probabilità che il campione contenga più di una lente difettosa ma meno di quattro lenti difettose?
>
> (c) (3 punti) Qual è il numero atteso di lenti difettose che l'ispettore troverà nel campione?

### Soluzione

Campionamento **senza reinserimento** da popolazione finita: $X$ è **ipergeometrica** con $N = 300$, $K = 25$ difettose, $n = 20$ estratte.

$$P(X = k) = \frac{\binom{K}{k}\binom{N-K}{n-k}}{\binom{N}{n}} = \frac{\binom{25}{k}\binom{275}{20-k}}{\binom{300}{20}}$$

#### Parte (a) — Esattamente 2 difettose

$$P(X = 2) = \frac{\binom{25}{2}\binom{275}{18}}{\binom{300}{20}} \approx \boxed{0.2863}$$

#### Parte (b) — Più di una ma meno di quattro

"Più di 1 e meno di 4" significa $X \in \{2, 3\}$:

$$P(1 < X < 4) = P(X=2) + P(X=3) = 0.2863 + \frac{\binom{25}{3}\binom{275}{17}}{\binom{300}{20}} \approx 0.2863 + 0.1531 = \boxed{0.4394}$$

> ⚠️ Trappola di lettura: gli estremi sono **esclusi** ($X=2$ o $X=3$, non $X=1$ né $X=4$). Metà dei punti persi su questa domanda storicamente viene da qui.

#### Parte (c) — Valore atteso

Per l'ipergeometrica $E[X] = n \cdot \frac{K}{N}$ (stessa forma della binomiale con $p = K/N$):

$$E[X] = 20 \times \frac{25}{300} = 20 \times \frac{1}{12} = \frac{20}{12} = \frac{5}{3} \approx \boxed{1.67}$$

---

## Domanda 2 — Componenti difettosi: Binomiale e approssimazione normale (8 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD3/L1 — Distribuzione Binomiale](../../M2_Variabili_aleatorie/UD3/L1%20-%20Distribuzione%20Binomiale.md) (formula, media $np$, varianza $npq$)
> - [M2/UD4/L1 — Densità Gaussiana o Normale](../../M2_Variabili_aleatorie/UD4/L1%20-%20Densit%C3%A0%20Gaussiana%20o%20Normale.md) (standardizzazione e tavole)
> - [M2/UD5/L2 — Approssimazione Normale della Binomiale e correzione di continuità](../../M2_Variabili_aleatorie/UD5_Approfondimenti_Esame/L2%20-%20Approssimazione%20Normale%20della%20Binomiale%20e%20correzione%20di%20continuit%C3%A0.md) (lezione integrativa dedicata: condizioni $np, n(1-p) \geq 10$ e correzione $\pm 0.5$ — svolge proprio questa domanda)
> - [M3/UD1/L6 — Il Teorema del Limite Centrale](../../M3_Argomenti_avanzati/UD1/L6%20-%20Il%20Teorema%20del%20Limite%20Centrale.md) (perché la binomiale, somma di Bernoulli i.i.d., tende alla normale)

### Traccia originale

> Un'azienda produce un tipo specifico di componenti elettronici. Dati storici indicano che il 5% dei componenti prodotti sono difettosi. Un ispettore di qualità seleziona casualmente un lotto di 200 componenti per un test di verifica. Sia $X$ la variabile aleatoria che rappresenta il numero di componenti difettosi trovati in questo lotto.
>
> (a) (2 punti) Qual è la probabilità che il lotto selezionato contenga esattamente 12 componenti difettosi?
>
> (b) (2 punti) Quali sono il numero atteso e la deviazione standard dei componenti difettosi che l'ispettore troverà nel lotto?
>
> (c) (4 punti) Qual è la probabilità che il lotto selezionato contenga un numero di componenti difettosi compreso tra 8 e 15 (inclusi)? È possibile, se le condizioni sono soddisfatte, utilizzare un'approssimazione della distribuzione di probabilità per evitare un calcolo complesso.

### Soluzione

Qui il campionamento è assimilabile a prove indipendenti con $p$ costante: $X \sim B(n = 200,\ p = 0.05)$.

#### Parte (a) — Esattamente 12 difettosi

$$P(X = 12) = \binom{200}{12}(0.05)^{12}(0.95)^{188} \approx \boxed{0.0967}$$

#### Parte (b) — Media e deviazione standard

$$E[X] = np = 200 \times 0.05 = \boxed{10} \qquad \sigma = \sqrt{np(1-p)} = \sqrt{200 \times 0.05 \times 0.95} = \sqrt{9.5} \approx \boxed{3.082}$$

#### Parte (c) — $P(8 \leq X \leq 15)$ con approssimazione normale

Il calcolo esatto $\sum_{k=8}^{15}\binom{200}{k}(0.05)^k(0.95)^{200-k}$ è laborioso. Verifichiamo le **condizioni per l'approssimazione normale**:

- $np = 10 \geq 10$ ✓
- $n(1-p) = 190 \geq 10$ ✓

Approssimiamo $X$ con $\mathcal{N}(\mu = 10,\ \sigma = 3.082)$, applicando la **correzione di continuità** (da discreta a continua): $P(8 \leq X \leq 15) \to P(7.5 < X_{\text{norm}} < 15.5)$.

$$z_1 = \frac{7.5 - 10}{3.082} \approx -0.811 \qquad z_2 = \frac{15.5 - 10}{3.082} \approx 1.784$$

Dalle tavole: $\Phi(1.78) \approx 0.9625$, $\Phi(-0.81) \approx 0.2090$:

$$P(8 \leq X \leq 15) \approx \Phi(z_2) - \Phi(z_1) = 0.9625 - 0.2090 = \boxed{0.7535 \ (\approx 75.4\%)}$$

> 📌 I 4 punti premiano tre cose: la **verifica esplicita delle condizioni** ($np$ e $n(1-p) \geq 10$), la **correzione di continuità** ($\pm 0.5$) e l'uso corretto delle tavole. Ometterne una costa punti anche con il numero finale giusto.

---

## Domanda 3 — Tre monete: inferenza bayesiana vs stima frequentista (6 punti)

> **Riferimenti di teoria**:
>
> - [M1/UD5/L2 — Bayes – formulazione, terminologia ed esempi](../../M1_Probabilita_elementare/UD5/L2%20-%20Bayes%20%E2%80%93%20formulazione,%20terminologia%20ed%20esempi.md) (prior uniforme, likelihood, evidence)
> - [M1/UD5/L4 — Bayes iterativo e aggiornamento della conoscenza](../../M1_Probabilita_elementare/UD5/L4%20-%20Bayes%20iterativo%20e%20aggiornamento%20della%20conoscenza.md) (l'esempio delle tre monete è svolto proprio lì)
> - [M3/UD3/L2 — Stima della media](../../M3_Argomenti_avanzati/UD3/L2%20-%20Stima%20della%20media.md) (stima di una probabilità e intervalli di confidenza)

### Traccia originale

> Scelta a caso una moneta da un'urna contenente:
>
> - Moneta A: è una moneta truccata che dà sempre Testa ($P(\text{Testa}) = 1$).
> - Moneta B: è una moneta bilanciata che dà Testa con probabilità 0.5.
> - Moneta C: è una moneta truccata che dà sempre Croce ($P(\text{Testa}) = 0$).
>
> (a) (3 punti) **Inferenza Bayesiana**: si immagini di lanciare la moneta scelta una sola volta e di ottenere Testa. Qual è la probabilità aggiornata (a posteriori) che la moneta scelta sia la Moneta A, la Moneta B o la Moneta C?
>
> (b) (3 punti) **Stima Frequentista con Intervallo di Confidenza**: si supponga di aver scelto una moneta dall'urna (senza sapere quale) e di averla lanciata per 100 volte, ottenendo 65 Teste. Qual è la stima puntuale della probabilità di ottenere Testa per questa moneta specifica, e qual è il suo intervallo di confidenza al 95%?

### Soluzione

#### Parte (a) — Posterior dopo una Testa

**Prior** uniforme: $P(A) = P(B) = P(C) = 1/3$. **Likelihood**: $P(T \mid A) = 1$, $P(T \mid B) = 0.5$, $P(T \mid C) = 0$.

**Evidence** (probabilità totale):

$$P(T) = 1 \cdot \tfrac{1}{3} + 0.5 \cdot \tfrac{1}{3} + 0 \cdot \tfrac{1}{3} = \frac{1.5}{3} = 0.5$$

**Posterior** (Bayes):

$$P(A \mid T) = \frac{1 \cdot 1/3}{0.5} = \boxed{\frac{2}{3}} \qquad P(B \mid T) = \frac{0.5 \cdot 1/3}{0.5} = \boxed{\frac{1}{3}} \qquad P(C \mid T) = \boxed{0}$$

> 💡 Controllo immediato: le tre posterior sommano a 1 ✓, e la moneta C viene **esclusa con certezza** (una Testa è incompatibile con $P(T)=0$).

#### Parte (b) — Stima puntuale e IC al 95%

Stima puntuale (frequenza empirica):

$$\hat{p} = \frac{k}{n} = \frac{65}{100} = \boxed{0.65}$$

Errore standard della proporzione:

$$\sigma_{\hat{p}} = \sqrt{\frac{\hat{p}(1-\hat{p})}{n}} = \sqrt{\frac{0.65 \times 0.35}{100}} = \sqrt{0.002275} \approx 0.0477$$

Intervallo al 95% ($z_{\alpha/2} = 1.96$):

$$IC_{95\%} = 0.65 \pm 1.96 \times 0.0477 = 0.65 \pm 0.0935 = \boxed{[0.5565,\ 0.7435]}$$

> ⚠️ Osservazione da lode: l'intervallo **non contiene nessuno** dei tre valori "ufficiali" (0, 0.5, 1). Nel modello dell'urna un esito del genere (65 teste da una moneta bilanciata: $z \approx 3$) sarebbe estremamente improbabile — la domanda (b) va letta come esercizio frequentista a sé stante, ed è un ottimo spunto per commentare la differenza tra i due paradigmi: Bayes aggiorna la fiducia su ipotesi discrete note, il frequentismo stima un parametro incognito qualunque in $[0,1]$.

---

## Domanda 4 — Pesi e altezze: statistica descrittiva completa (11 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD1/L3 — Indicatori di posizione](../../M2_Variabili_aleatorie/UD1/L3%20-%20Indicatori%20di%20posizione.md) (media, mediana, moda, quartili)
> - [M2/UD1/L4 — Indicatori d'ampiezza](../../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md) (range, varianza, deviazione standard, IQR)
> - [M3/UD2/L1 — Dalla distribuzione al campione – Statistica descrittiva](../../M3_Argomenti_avanzati/UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md) (indicatori campionari)
> - [M3/UD4/L1 — CV, covarianza, correlazione di Pearson e skewness](../../M3_Argomenti_avanzati/UD4_Approfondimenti_Esame/L1%20-%20CV,%20covarianza,%20correlazione%20di%20Pearson%20e%20skewness.md) (lezione integrativa dedicata alle parti (d) ed (e))

### Traccia originale

> Un gruppo di 7 amici ha registrato i propri pesi in kilogrammi (kg): $\{51, 52, 50, 52, 62, 78, 95\}$
>
> (a) (2 punti) Si calcolino gli indicatori di tendenza centrale: la media, la mediana e la moda dei pesi.
>
> (b) (2 punti) Si calcolino gli indicatori di ampiezza: il range, la varianza campionaria e la deviazione standard campionaria dei pesi.
>
> (c) (2 punti) Si determinino i quartili $Q_1$, $Q_2$, $Q_3$ e l'intervallo interquartile (IQR).
>
> (d) (2 punti) Si calcoli il coefficiente di variazione per questi dati.
>
> (e) (3 punti) Se ai pesi vengono affiancate le rispettive altezze in cm, ottenendo le coppie $\{(51, 165), (52, 168), (50, 160), (52, 170), (62, 175), (78, 180), (95, 185)\}$, si calcoli il coefficiente di correlazione campionaria (di Pearson) tra i pesi ($X$) e le altezze ($Y$) e si interpreti il valore ottenuto. Si assuma che l'altezza media sia $\bar{y} \approx 171.86$ cm e che la deviazione standard di altezza sia $s_y = \sqrt{75.80} \approx 8.71$ cm.

### Soluzione

Dati ordinati ($n = 7$): $50,\ 51,\ 52,\ 52,\ 62,\ 78,\ 95$.

#### Parte (a) — Tendenza centrale

$$\bar{x} = \frac{50+51+52+52+62+78+95}{7} = \frac{440}{7} \approx \boxed{62.86 \text{ kg}}$$

Mediana: posizione $(7+1)/2 = 4$ → $\boxed{52 \text{ kg}}$. Moda: 52 kg (compare due volte) → $\boxed{52 \text{ kg}}$.

> 💡 Media (62.86) molto maggiore della mediana (52): i due valori estremi (78 e 95 kg) trascinano la media verso destra — distribuzione fortemente asimmetrica positiva. La mediana qui è l'indicatore più rappresentativo.

#### Parte (b) — Ampiezza

Range $= 95 - 50 = \boxed{45 \text{ kg}}$.

Somma degli scarti quadratici (calcolabile anche come $\sum x_i^2 - n\bar{x}^2 = 29462 - 27657.14$):

$$\sum_i (x_i - \bar{x})^2 \approx 1804.86 \quad\Rightarrow\quad s^2 = \frac{1804.86}{6} \approx \boxed{300.81 \text{ kg}^2} \qquad s \approx \boxed{17.34 \text{ kg}}$$

#### Parte (c) — Quartili e IQR

Con la convenzione della posizione $\lceil q \cdot n \rceil$ sui dati ordinati:

- $Q_1$: posizione $\lceil 0.25 \times 7 \rceil = \lceil 1.75 \rceil = 2$ → $Q_1 = \boxed{51 \text{ kg}}$
- $Q_2$ = mediana = $\boxed{52 \text{ kg}}$
- $Q_3$: posizione $\lceil 0.75 \times 7 \rceil = \lceil 5.25 \rceil = 6$ → $Q_3 = \boxed{78 \text{ kg}}$

$$IQR = Q_3 - Q_1 = 78 - 51 = \boxed{27 \text{ kg}}$$

#### Parte (d) — Coefficiente di variazione

Il CV misura la dispersione **relativa** (adimensionale, confrontabile tra grandezze diverse):

$$CV = \frac{s}{\bar{x}} = \frac{17.34}{62.86} \approx \boxed{0.276 \ (\approx 27.6\%)}$$

#### Parte (e) — Correlazione di Pearson

Con la covarianza campionaria $s_{xy} = \frac{1}{n-1}\sum_i (x_i-\bar{x})(y_i-\bar{y}) \approx 139.82$ (i dati forniti dal testo danno direttamente $\bar{y}$ e $s_y$):

$$r = \frac{s_{xy}}{s_x\, s_y} = \frac{139.82}{17.34 \times 8.71} = \frac{139.82}{151.03} \approx \boxed{0.926}$$

**Interpretazione:** $r \approx 0.93$ indica una correlazione lineare **molto forte e positiva** tra peso e altezza: all'aumentare del peso l'altezza tende ad aumentare in modo quasi perfettamente lineare. Attenzione a non spingersi oltre: la correlazione misura associazione lineare, **non implica causalità**.

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1 | 7 | Ipergeometrica: $P(X{=}2)\approx 0.2863$; $P(1<X<4)\approx 0.4394$; $E[X]=5/3\approx 1.67$ |
| 2 | 8 | $P(X{=}12)\approx 0.0967$; $E=10$, $\sigma\approx 3.082$; normale+continuità → $\approx 0.7535$ |
| 3 | 6 | Posterior $(2/3,\ 1/3,\ 0)$; $\hat{p}=0.65$, $IC_{95\%}=[0.5565, 0.7435]$ |
| 4 | 11 | $\bar{x}\approx 62.86$; mediana/moda 52; $s\approx 17.34$; $Q_1{=}51$, $Q_3{=}78$, IQR 27; $CV\approx 27.6\%$; $r\approx 0.926$ |
