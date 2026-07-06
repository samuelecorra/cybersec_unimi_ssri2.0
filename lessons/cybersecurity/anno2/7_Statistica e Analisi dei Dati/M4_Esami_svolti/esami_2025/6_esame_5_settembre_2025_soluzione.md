# Esame del 5 settembre 2025 — Soluzione completa

> 📌 **Informazioni appello:** 05/09/2025 — 5 domande, 33 punti totali.
> Argomenti: Esponenziale (memoryless, serie), Statistica descrittiva con frequenze, Densità continua (PDF e CDF), Bayes con test diagnostico iterato, Gaussiana (QI).

---

## Domanda 1 — Durata di un sensore: Esponenziale (6 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD2/L2 — Densità Esponenziale Negativa](../../M2_Variabili_aleatorie/UD2/L2%20-%20Densit%C3%A0%20Esponenziale%20Negativa.md) (legge di sopravvivenza $P(X>t)=e^{-\lambda t}$)
> - [M2/UD2/L3 — Caratteristiche dell'esponenziale](../../M2_Variabili_aleatorie/UD2/L3%20-%20Caratteristiche%20dell%E2%80%99esponenziale.md) (media $1/\lambda$, proprietà memoryless)
> - [M2/UD2/L4 — Applicazioni all'affidabilità dei sistemi](../../M2_Variabili_aleatorie/UD2/L4%20-%20Applicazioni%20all%E2%80%99affidabilit%C3%A0%20dei%20sistemi.md) (sistemi in serie di componenti esponenziali)

### Traccia originale

> Un'azienda produce un sensore di umidità la cui durata di vita (in mesi) segue una distribuzione esponenziale, con valore atteso pari a 8 mesi.
>
> (a) (2 punti) Si calcoli la probabilità che un singolo sensore duri più di 12 mesi.
>
> (b) (2 punti) Un sistema di monitoraggio usa tre sensori con una connessione in serie. Qual è la probabilità che il sistema funzioni per almeno 4 mesi?
>
> (c) (2 punti) Se un sensore installato ha già funzionato per 6 mesi, qual è la probabilità che questo stesso sensore continui a funzionare per altri 6 mesi (cioè, duri almeno 12 mesi totali dalla sua installazione iniziale)? Si spieghi brevemente la proprietà statistica alla base di questa risposta.

### Soluzione

$X \sim \text{Exp}(\lambda)$ con $E[X] = 1/\lambda = 8$ mesi, quindi $\lambda = 1/8$. Legge di sopravvivenza: $P(X > t) = e^{-t/8}$.

#### Parte (a) — Singolo sensore oltre 12 mesi

$$P(X > 12) = e^{-12/8} = e^{-1.5} \approx \boxed{0.2231}$$

#### Parte (b) — Tre sensori in serie per almeno 4 mesi

Un sistema in serie funziona finché funzionano **tutti** i componenti; per l'indipendenza:

$$P(\text{sistema} > 4) = \left[P(X > 4)\right]^3 = \left(e^{-4/8}\right)^3 = \left(e^{-0.5}\right)^3 = e^{-1.5} \approx \boxed{0.2231}$$

> 💡 Equivalentemente: il minimo di tre esponenziali indipendenti di parametro $\lambda$ è ancora esponenziale con parametro $3\lambda = 3/8$, e $P(\min > 4) = e^{-3 \cdot 4/8} = e^{-1.5}$. Che coincida con la (a) non è un caso: $3 \times 4 = 12$ "mesi-sensore" equivalenti.

#### Parte (c) — Proprietà senza memoria

Si chiede $P(X > 12 \mid X > 6)$. Per la **proprietà memoryless** dell'esponenziale, $P(X > s+t \mid X > s) = P(X > t)$, quindi:

$$P(X > 12 \mid X > 6) = P(X > 6) = e^{-6/8} = e^{-0.75} \approx \boxed{0.4724}$$

**Spiegazione:** l'esponenziale è l'unica distribuzione continua **senza memoria**: il componente "non invecchia", ossia la probabilità di sopravvivere altri 6 mesi è la stessa di un sensore appena installato, indipendentemente dal tempo già trascorso. I 6 mesi già superati non modificano l'aspettativa di vita residua.

---

## Domanda 2 — Taglie di scarpe: statistica descrittiva con frequenze (8 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD1/L3 — Indicatori di posizione](../../M2_Variabili_aleatorie/UD1/L3%20-%20Indicatori%20di%20posizione.md) (media, mediana, moda, quartili)
> - [M2/UD1/L4 — Indicatori d'ampiezza](../../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md) (varianza e deviazione standard)
> - [M3/UD2/L1 — Dalla distribuzione al campione – Statistica descrittiva](../../M3_Argomenti_avanzati/UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md) (calcoli con dati raggruppati per frequenza)

### Traccia originale

> Un'azienda di calzature ha raccolto i dati sulle taglie di scarpe di un campione di 54 individui, ottenendo le seguenti frequenze:
>
> | Taglia | 37 | 39 | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 | 48 | 49 |
> |--------|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
> | Frequenza | 2 | 1 | 5 | 5 | 8 | 16 | 5 | 5 | 2 | 1 | 2 | 2 |
>
> (a) (2 punti) Si calcolino la media campionaria e la moda delle taglie di scarpe per questo campione.
>
> (b) (2 punti) Si calcolino la varianza campionaria e la deviazione standard delle taglie di scarpe.
>
> (c) (4 punti) Si determinino la mediana e il terzo quartile delle taglie di scarpe del campione.

### Soluzione

Verifica preliminare: $n = 2+1+5+5+8+16+5+5+2+1+2+2 = 54$. ✓

#### Parte (a) — Media e moda

Per dati raggruppati, $\bar{x} = \frac{1}{n}\sum_i x_i f_i$:

$$\sum_i x_i f_i = 74 + 39 + 200 + 205 + 336 + 688 + 220 + 225 + 92 + 47 + 96 + 98 = 2320$$

$$\bar{x} = \frac{2320}{54} \approx \boxed{42.96} \qquad \text{moda} = \boxed{43} \ (\text{frequenza massima } 16)$$

#### Parte (b) — Varianza e deviazione standard campionarie

$$s^2 = \frac{\sum_i f_i (x_i - \bar{x})^2}{n-1}$$

Calcolando gli scarti quadratici pesati con $\bar{x} = 42.96$ (i contributi maggiori vengono dalle code: taglia 49 → $2 \times 6.04^2 = 72.96$, taglia 37 → $2 \times 5.96^2 = 71.04$):

$$\sum_i f_i (x_i-\bar{x})^2 \approx 346.93 \quad\Rightarrow\quad s^2 = \frac{346.93}{53} \approx \boxed{6.546} \qquad s = \sqrt{6.546} \approx \boxed{2.559}$$

#### Parte (c) — Mediana e terzo quartile

Servono le **frequenze cumulate**:

| Taglia | 37 | 39 | 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 | 48 | 49 |
|--------|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Cumulata | 2 | 3 | 8 | 13 | 21 | 37 | 42 | 47 | 49 | 50 | 52 | 54 |

- **Mediana**: posizione $(n+1)/2 = 27.5$, cioè tra la 27ª e la 28ª osservazione. La cumulata arriva a 21 con la taglia 42 e a 37 con la 43: entrambe le posizioni cadono sulla taglia 43. $\text{Mediana} = \boxed{43}$.
- **Terzo quartile**: posizione $3(n+1)/4 = 41.25$, tra la 41ª e la 42ª osservazione. La cumulata arriva a 37 con la 43 e a 42 con la 44: entrambe le posizioni cadono sulla taglia 44. $Q_3 = \boxed{44}$.

> ✅ Coerenza: media (42.96) < mediana (43) di pochissimo, distribuzione quasi simmetrica con leggera coda sinistra — plausibile guardando le frequenze.

---

## Domanda 3 — Densità continua: costante, CDF e probabilità (8 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD1/L1 — Distribuzioni e densità di variabili aleatorie](../../M2_Variabili_aleatorie/UD1/L1%20-%20Distribuzioni%20e%20densit%C3%A0%20di%20variabili%20aleatorie.md) (condizione di normalizzazione della densità)
> - [M2/UD1/L2 — Cumulativa e Anticumulativa](../../M2_Variabili_aleatorie/UD1/L2%20-%20Cumulativa%20e%20Anticumulativa.md) (definizione di $F(x)$, calcolo di $P(X>a) = 1-F(a)$)

### Traccia originale

> Sia $X$ una variabile aleatoria continua la cui densità di probabilità è definita come
>
> $$f(x) = \begin{cases} C(4x - 2x^2) & \text{per } 0 < x < 2 \\ 0 & \text{altrove} \end{cases}$$
>
> (a) (2 punti) Si determini il valore della costante $C$ affinché $f(x)$ sia una densità di probabilità valida.
>
> (b) (3 punti) Si calcoli la funzione di distribuzione cumulativa $F(x)$ per questa variabile aleatoria.
>
> (c) (3 punti) Si calcoli la probabilità che la variabile aleatoria $X$ assuma un valore $> 1$.

### Soluzione

#### Parte (a) — Normalizzazione

L'integrale della densità sul dominio deve valere 1:

$$\int_0^2 C(4x - 2x^2)\,dx = C\left[2x^2 - \frac{2}{3}x^3\right]_0^2 = C\left(8 - \frac{16}{3}\right) = C \cdot \frac{8}{3} = 1 \quad\Rightarrow\quad \boxed{C = \frac{3}{8}}$$

(Si verifica anche che $4x - 2x^2 = 2x(2-x) \geq 0$ su $(0,2)$: densità ben definita.)

#### Parte (b) — Funzione cumulativa

Per $0 \leq x < 2$:

$$F(x) = \int_0^x \frac{3}{8}(4t - 2t^2)\,dt = \frac{3}{8}\left(2x^2 - \frac{2}{3}x^3\right) = \frac{3x^2}{4} - \frac{x^3}{4} = \frac{x^2(3-x)}{4}$$

$$\boxed{F(x) = \begin{cases} 0 & x < 0 \\ \dfrac{x^2(3-x)}{4} & 0 \leq x < 2 \\ 1 & x \geq 2 \end{cases}}$$

Verifica agli estremi: $F(0) = 0$ ✓, $F(2) = \frac{4 \cdot 1}{4} = 1$ ✓.

#### Parte (c) — $P(X > 1)$

$$P(X > 1) = 1 - F(1) = 1 - \frac{1^2(3-1)}{4} = 1 - \frac{2}{4} = \boxed{\frac{1}{2}}$$

> 💡 Risultato elegante non casuale: la densità $2x(2-x)$ (riscalata) è simmetrica rispetto a $x=1$, quindi $x=1$ è esattamente la mediana.

---

## Domanda 4 — Test diagnostico: Bayes e aggiornamento iterativo (8 punti)

> **Riferimenti di teoria**:
>
> - [M1/UD5/L2 — Bayes – formulazione, terminologia ed esempi](../../M1_Probabilita_elementare/UD5/L2%20-%20Bayes%20%E2%80%93%20formulazione,%20terminologia%20ed%20esempi.md) (teorema di Bayes, probabilità totale)
> - [M1/UD5/L3 — Prior e Verosimiglianza](../../M1_Probabilita_elementare/UD5/L3%20-%20Prior%20e%20Verosimiglianza.md) (sensibilità/specificità come verosimiglianze)
> - [M1/UD5/L4 — Bayes iterativo e aggiornamento della conoscenza](../../M1_Probabilita_elementare/UD5/L4%20-%20Bayes%20iterativo%20e%20aggiornamento%20della%20conoscenza.md) (la posterior diventa prior del test successivo — esattamente la parte (d))

### Traccia originale

> Si immagini di dover interpretare i risultati di un test diagnostico per una malattia M. Si conoscono i seguenti fatti:
>
> - La prevalenza di M nella popolazione generale è dello 0.1%.
> - Il test diagnostico ha una sensibilità del 99%, cioè $P(T^+ \mid M) = 0.99$.
> - Il test ha una specificità del 95%, cioè $P(T^- \mid M^c) = 0.95$.
>
> (a) (2 punti) Qual è la probabilità a priori che una persona scelta a caso dalla popolazione generale abbia la malattia M prima di aver effettuato il test? E di non avere la malattia $M^c$?
>
> (b) (2 punti) Si calcoli la probabilità totale di ottenere un risultato positivo al test $P(T^+)$.
>
> (c) (2 punti) Si calcoli la probabilità a posteriori che la persona abbia effettivamente la malattia, dato che il test è risultato positivo $P(M \mid T^+)$. Si spieghi come questa probabilità aggiorna la conoscenza iniziale.
>
> (d) (2 punti) La stessa persona, dopo il primo test positivo, decide di sottoporsi a un secondo test indipendente, che risulta anch'esso positivo. Qual è la probabilità aggiornata che la persona abbia la malattia dopo due test positivi consecutivi?

### Soluzione

#### Parte (a) — Prior

$$P(M) = 0.001 \qquad P(M^c) = 1 - 0.001 = \boxed{0.999}$$

#### Parte (b) — Probabilità totale di un positivo

Il tasso di **falsi positivi** è il complemento della specificità: $P(T^+ \mid M^c) = 1 - 0.95 = 0.05$.

$$P(T^+) = P(T^+ \mid M)P(M) + P(T^+ \mid M^c)P(M^c) = 0.99 \times 0.001 + 0.05 \times 0.999 = 0.00099 + 0.04995 = \boxed{0.05094}$$

#### Parte (c) — Posterior dopo un positivo

$$P(M \mid T^+) = \frac{P(T^+ \mid M)\,P(M)}{P(T^+)} = \frac{0.00099}{0.05094} \approx \boxed{0.0194 \ (\approx 1.94\%)}$$

**Aggiornamento della conoscenza:** il test positivo ha moltiplicato per quasi 20 la probabilità iniziale (dallo 0.1% all'1.94%), ma il valore assoluto resta basso. Il motivo è la **bassissima prevalenza**: nella grande popolazione sana i falsi positivi ($0.05 \times 0.999 \approx 5\%$) sommergono i veri positivi ($0.99 \times 0.001 \approx 0.1\%$) — circa 50 positivi su 51 sono falsi allarmi.

#### Parte (d) — Secondo test positivo (Bayes iterativo)

La posterior del primo test diventa la **prior** del secondo (test condizionatamente indipendenti dato lo stato di malattia): $P'(M) = 0.0194$, $P'(M^c) = 0.9806$.

$$P(T_2^+) = 0.99 \times 0.0194 + 0.05 \times 0.9806 = 0.01924 + 0.04903 = 0.06827$$

$$P(M \mid T_1^+, T_2^+) = \frac{0.99 \times 0.0194}{0.06827} = \frac{0.01924}{0.06827} \approx \boxed{0.2818 \ (\approx 28.2\%)}$$

> 📌 Due positivi consecutivi portano la fiducia dall'iniziale 0.1% al 28%: ogni test moltiplica le evidenze, ma serve accumulare conferme prima che la diagnosi diventi probabile. È l'essenza dell'**apprendimento bayesiano iterativo**.

---

## Domanda 5 — QI: distribuzione Normale (3 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD4/L1 — Densità Gaussiana o Normale](../../M2_Variabili_aleatorie/UD4/L1%20-%20Densit%C3%A0%20Gaussiana%20o%20Normale.md) (standardizzazione $Z = (X-\mu)/\sigma$)
> - [M2/UD4/L2 — Altre considerazioni sulla Gaussiana](../../M2_Variabili_aleatorie/UD4/L2%20-%20Altre%20considerazioni%20sulla%20Gaussiana.md) (uso delle tavole della normale standard)

### Traccia originale

> I punteggi del quoziente intellettivo (QI) secondo la scala dell'intelligenza di Stanford-Binet tendono a essere normalmente distribuiti con una media $\mu = 100.0$ e una deviazione standard $\sigma = 16.0$. Un'università è interessata a identificare, in una popolazione di riferimento, gli studenti con QI nella fascia più alta per programmi avanzati.
>
> (a) (3 punti) Qual è la probabilità che uno studente scelto a caso da questa popolazione abbia un punteggio QI superiore a 120?

### Soluzione

Standardizzazione:

$$Z = \frac{X - \mu}{\sigma} = \frac{120 - 100}{16} = \frac{20}{16} = 1.25$$

Dalle tavole della normale standard, $\Phi(1.25) \approx 0.8944$:

$$P(X > 120) = P(Z > 1.25) = 1 - \Phi(1.25) = 1 - 0.8944 = \boxed{0.1056 \ (\approx 10.6\%)}$$

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1 | 6 | $e^{-1.5}\approx 0.2231$; serie $e^{-1.5}\approx 0.2231$; memoryless $e^{-0.75}\approx 0.4724$ |
| 2 | 8 | $\bar{x}\approx 42.96$; moda 43; $s^2\approx 6.546$, $s\approx 2.559$; mediana 43; $Q_3 = 44$ |
| 3 | 8 | $C=3/8$; $F(x)=x^2(3-x)/4$ su $[0,2)$; $P(X>1)=1/2$ |
| 4 | 8 | $P(T^+)=0.05094$; $P(M\mid T^+)\approx 0.0194$; dopo 2 test $\approx 0.2818$ |
| 5 | 3 | $z=1.25$; $P(X>120)\approx 0.1056$ |
