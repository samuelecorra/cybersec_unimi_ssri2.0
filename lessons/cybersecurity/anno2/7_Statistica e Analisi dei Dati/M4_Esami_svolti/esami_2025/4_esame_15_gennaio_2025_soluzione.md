# Esame del 15 gennaio 2025 — Soluzione completa

> 📌 **Informazioni appello:** 15/01/2025 — 5 domande, 33 punti totali.
> Argomenti: Probabilità condizionata + Bayes (palline), Esponenziale e sistemi, Densità continua, Lotto, Teorema del Limite Centrale.

---

## Domanda 1 — Urna: probabilità condizionata e Bayes (6 punti)

### Traccia originale

> Un'urna contiene 20 palline, di cui 10 sono rosse e 10 sono blu. Vengono estratte 6 palline senza reinserimento. Successivamente, viene effettuata un'altra estrazione di 3 palline, sempre senza reinserimento, dalle 6 estratte precedentemente.
>
> Evento A: nella prima estrazione di 6 palline, esattamente 4 palline sono rosse.
> Evento B: nella seconda estrazione di 3 palline, esattamente 2 palline sono rosse.
>
> (a) (2 punti) Calcolare la probabilità dell'evento A.
>
> (b) (2 punti) Calcolare la probabilità condizionata $P(B|A)$.
>
> (c) (2 punti) Supponendo di non conoscere il risultato della prima estrazione, ma sapendo che alla fine della seconda estrazione ci sono 2 palline rosse su 3 estratte (evento B), impostare il calcolo della probabilità che nella prima estrazione ci fossero esattamente 4 palline rosse: $P(A|B)$ (non è richiesto di sviluppare interamente il calcolo numerico).

### Soluzione

#### Parte (a) — $P(A)$

Prima estrazione ipergeometrica: $N=20$, $K=10$ (rosse), $n=6$.

$$P(A) = \frac{\binom{10}{4}\binom{10}{2}}{\binom{20}{6}} = \frac{210 \times 45}{38760} = \frac{9450}{38760} = \frac{315}{1292} \approx \boxed{0.2438}$$

---

#### Parte (b) — $P(B \mid A)$

Dato $A$: nelle 6 palline estratte ci sono esattamente **4R e 2B**.
Seconda estrazione ipergeometrica: $N=6$, $K=4$, $n=3$.

$$P(B \mid A) = \frac{\binom{4}{2}\binom{2}{1}}{\binom{6}{3}} = \frac{6 \times 2}{20} = \frac{12}{20} = \boxed{0.60}$$

---

#### Parte (c) — Impostazione di $P(A \mid B)$

Per Bayes:

$$P(A \mid B) = \frac{P(B \mid A)\cdot P(A)}{P(B)}$$

dove $P(B)$ si calcola con la probabilità totale su tutti i possibili valori $k$ di rosse nella prima estrazione ($k = 2,3,4,5$; serve $k \geq 2$ e $6-k \geq 1$ per avere 2R e 1B nella seconda):

$$P(B) = \sum_{k=2}^{5} P(B \mid X_1 = k)\cdot P(X_1 = k)$$

con:
$$P(X_1=k) = \frac{\binom{10}{k}\binom{10}{6-k}}{\binom{20}{6}}, \qquad P(B \mid X_1=k) = \frac{\binom{k}{2}(6-k)}{20}$$

Sostituendo i valori noti $P(B\mid A) = 0.60$ e $P(A) \approx 0.2438$:

$$P(A \mid B) = \frac{0.60 \times 0.2438}{P(B)}$$

> 📌 Non è richiesto il calcolo numerico di $P(B)$. L'impostazione mostra: prior $P(A)$ dalla (a), verosimiglianza $P(B\mid A)$ dalla (b), normalizzazione su tutti gli scenari compatibili con $B$.

---

## Domanda 2 — Distribuzione esponenziale e sistemi (6 punti)

### Traccia originale

> Un'azienda produce componenti elettronici. Il tempo di vita di questi componenti segue una distribuzione esponenziale con un valore atteso di 1000 ore.
>
> (a) (2 punti) Qual è la probabilità che un componente duri più di 1500 ore?
>
> (b) (2 punti) Se l'azienda utilizza due di questi componenti in un sistema, e il sistema funziona fintanto che almeno uno dei due componenti è operativo, qual è la probabilità che il sistema funzioni per più di 1500 ore? Assumere che i componenti si guastino in modo indipendente.
>
> (c) (2 punti) Se invece il sistema smette di funzionare quando si guasta il primo componente, qual è la probabilità che il sistema funzioni per più di 1500 ore?

### Soluzione

$X \sim \text{Exp}(\lambda)$ con $E[X]=1/\lambda=1000$ ore, quindi $\lambda=1/1000$.

Legge di sopravvivenza: $P(X>t) = e^{-t/1000}$.

---

#### Parte (a) — Singolo componente

$$P(X > 1500) = e^{-1500/1000} = e^{-1.5} \approx \boxed{0.2231}$$

---

#### Parte (b) — Sistema parallelo (funziona se almeno uno funziona)

$$P(\max(X_1,X_2) > 1500) = 1 - P(X_1\leq 1500)\cdot P(X_2\leq 1500) = 1-(1-e^{-1.5})^2$$

$$= 1 - (0.7769)^2 = 1 - 0.6036 = \boxed{0.3964}$$

> 💡 La ridondanza parallela aumenta l'affidabilità da 22.3% a 39.6%.

---

#### Parte (c) — Sistema serie (si guasta al primo guasto)

Il minimo di due esponenziali i.i.d. con parametro $\lambda$ è $\text{Exp}(2\lambda)$:

$$P(\min(X_1,X_2) > 1500) = P(X_1>1500)\cdot P(X_2>1500) = e^{-1.5}\cdot e^{-1.5} = e^{-3} \approx \boxed{0.0498}$$

> ⚠️ Il sistema serie ha affidabilità ~5%, molto peggiore del singolo componente (22.3%): basta un guasto per fermare tutto.

---

## Domanda 3 — Densità continua $f(x) = cx$ (9 punti)

### Traccia originale

> Sia data una variabile aleatoria $X$ con densità di probabilità $f(x) = cx$ per $0 \leq x \leq 2$, $f(x) = 0$ altrove.
>
> (a) (2 punti) Trovare il valore di $c$ che rende $f(x)$ una densità di probabilità valida.
>
> (b) (2 punti) Calcolare la probabilità che $X$ sia compresa tra 1 e 1.5.
>
> (c) (2 punti) Calcolare la mediana di $X$.
>
> (d) (3 punti) Calcolare la varianza di $X$.

### Soluzione

#### Parte (a) — Valore di $c$

$$\int_0^2 cx\,dx = c\left[\frac{x^2}{2}\right]_0^2 = 2c = 1 \quad \Rightarrow \quad \boxed{c = \tfrac{1}{2}}$$

Quindi $f(x) = x/2$ per $0\leq x\leq 2$.

---

#### Parte (b) — $P(1 \leq X \leq 1.5)$

$$\int_1^{1.5}\frac{x}{2}\,dx = \frac{1}{4}\bigl[(1.5)^2 - 1^2\bigr] = \frac{2.25-1}{4} = \frac{1.25}{4} = \boxed{0.3125}$$

---

#### Parte (c) — Mediana

La mediana $m$ soddisfa $F(m) = \int_0^m \frac{x}{2}\,dx = \frac{m^2}{4} = 0.5$:

$$m^2 = 2 \quad \Rightarrow \quad m = \sqrt{2} \approx \boxed{1.414}$$

---

#### Parte (d) — Varianza

$$E[X] = \int_0^2 x\cdot\frac{x}{2}\,dx = \int_0^2\frac{x^2}{2}\,dx = \frac{1}{2}\cdot\frac{8}{3} = \frac{4}{3}$$

$$E[X^2] = \int_0^2 x^2\cdot\frac{x}{2}\,dx = \int_0^2\frac{x^3}{2}\,dx = \frac{1}{2}\cdot\frac{16}{4} = 2$$

$$\text{Var}(X) = E[X^2] - (E[X])^2 = 2 - \frac{16}{9} = \frac{18-16}{9} = \frac{2}{9} \approx \boxed{0.222}$$

---

## Domanda 4 — Gioco del Lotto (5 punti)

### Traccia originale

> Nel gioco del Lotto, vengono estratti 5 numeri senza reinserimento da un'urna contenente 90 numeri.
>
> (a) (2 punti) Qual è la probabilità di indovinare un terno (tre numeri corretti) giocando una singola combinazione di tre numeri?
>
> (b) (3 punti) Se un giocatore gioca 10 combinazioni diverse di tre numeri, qual è la probabilità di indovinare almeno un terno?

### Soluzione

#### Parte (a) — Probabilità di un terno

Il giocatore sceglie 3 numeri specifici. Sono "vinti" se tutti e 3 sono tra i 5 estratti.

$$P(\text{terno}) = \frac{\binom{87}{2}}{\binom{90}{5}} = \frac{3741}{43{,}949{,}268} = \frac{1}{11748} \approx \boxed{8.51\times 10^{-5}}$$

**Verifica alternativa** (prob. sequenziale): $\dfrac{5}{90}\times\dfrac{4}{89}\times\dfrac{3}{88} = \dfrac{60}{704880} = \dfrac{1}{11748}$. ✓

---

#### Parte (b) — Almeno un terno su 10 combinazioni

Con $p = 1/11748$, assumendo le 10 combinazioni come prove di Bernoulli indipendenti:

$$P(\text{almeno un terno}) = 1 - \left(1 - \frac{1}{11748}\right)^{10} \approx \frac{10}{11748} \approx \boxed{8.51\times 10^{-4}}$$

Giocare 10 combinazioni moltiplica la probabilità per ~10 (approssimazione valida per $p \ll 1$).

---

## Domanda 5 — Teorema del Limite Centrale (7 punti)

### Traccia originale

> (7 punti) Descrivere come il Teorema del Limite Centrale giustifica l'uso della distribuzione normale per approssimare la distribuzione della media campionaria, evidenziando le condizioni necessarie per l'applicazione del teorema e le implicazioni per l'inferenza statistica.

### Soluzione

**Enunciato del TLC:**

Siano $X_1,\ldots,X_n$ variabili aleatorie **i.i.d.** con media $\mu$ e varianza $\sigma^2 < \infty$. Allora:

$$Z_n = \frac{\bar{X}_n - \mu}{\sigma/\sqrt{n}} \xrightarrow{d} \mathcal{N}(0,1)$$

ovvero $\bar{X}_n \approx \mathcal{N}\!\left(\mu,\, \sigma^2/n\right)$ per $n$ sufficientemente grande.

---

**Condizioni necessarie:**

1. Variabili **indipendenti** e **identicamente distribuite** (i.i.d.)
2. Valore atteso $\mu$ **finito**
3. Varianza $\sigma^2$ **finita** (condizione essenziale; la distribuzione di Cauchy non soddisfa questo e il TLC non si applica)
4. La distribuzione di partenza può essere **qualsiasi** — non è richiesta la normalità iniziale

> 💡 La riduzione $\sigma/\sqrt{n}$ della deviazione standard di $\bar{X}_n$ (già ricavata dalla WLLN) descrive la velocità di convergenza; il TLC precisa in più la **forma gaussiana** di quella convergenza.

---

**Implicazioni per l'inferenza statistica:**

**1. Intervalli di confidenza per la media**

Poiché $\bar{X}_n \approx \mathcal{N}(\mu,\sigma^2/n)$, un IC al livello $1-\alpha$ è:

$$\bar{X}_n \pm z_{\alpha/2}\frac{\sigma}{\sqrt{n}}$$

Questo funziona **indipendentemente dalla distribuzione originale** dei dati, purché $n$ sia abbastanza grande.

**2. Test di ipotesi sulla media**

La statistica $Z_n = (\bar{X}_n - \mu_0)/(\sigma/\sqrt{n})$ ha distribuzione approssimativamente $\mathcal{N}(0,1)$, permettendo di usare la tavola normale standard anche con distribuzioni di partenza non gaussiane.

**3. Approssimazione di distribuzioni discrete**

Il TLC giustifica l'approssimazione normale per:
- Binomiale $B(n,p)$ quando $np \geq 5$ e $n(1-p) \geq 5$
- Poisson$(\lambda)$ per $\lambda$ grande

entrambe essendo somme di variabili i.i.d.

**4. Regola pratica:** $n \geq 30$ è spesso sufficiente; per distribuzioni molto asimmetriche può servire $n$ maggiore.

> ⚠️ Il TLC riguarda la distribuzione della **media campionaria** $\bar{X}_n$, non delle singole $X_i$. Anche se i singoli dati sono fortemente non normali, la loro media su $n$ osservazioni si distribuisce approssimativamente come una gaussiana.

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1 | 6 | $P(A)=315/1292\approx 0.244$; $P(B\mid A)=3/5$; $P(A\mid B)$ impostato via Bayes |
| 2 | 6 | $e^{-1.5}\approx 0.223$; parallelo $\approx 0.396$; serie $e^{-3}\approx 0.050$ |
| 3 | 9 | $c=1/2$; $P(1\leq X\leq 1.5)=0.3125$; mediana$=\sqrt{2}$; $\text{Var}(X)=2/9$ |
| 4 | 5 | $P(\text{terno})=1/11748\approx 8.51\times10^{-5}$; $P(\geq 1\text{ su 10})\approx 8.51\times10^{-4}$ |
| 5 | 7 | TLC: condizioni i.i.d.+$\sigma^2<\infty$; $\bar{X}_n\approx\mathcal{N}(\mu,\sigma^2/n)$; IC, test, appross. discrete |
