# Esame del 16 gennaio 2026 — Soluzione completa

> 📌 **Informazioni appello:** 16/01/2026 — 5 domande, 33 punti totali.
> Argomenti: Ipergeometrica (identificazione + momenti), Statistica descrittiva con frequenze e skewness, Esponenziale (memoryless, mediana) + Normale, Estrazioni senza reinserimento (albero), Bayes con test diagnostico.
> ⚠️ Formulario, tavole statistiche e calcolatrice ammessi; nessun altro strumento.

---

## Domanda 1 — Controllo qualità: identificare e usare l'Ipergeometrica (7 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD5/L1 — Distribuzione Ipergeometrica](../../M2_Variabili_aleatorie/UD5_Approfondimenti_Esame/L1%20-%20Distribuzione%20Ipergeometrica.md) (lezione integrativa dedicata: identificazione, formula, momenti — svolge proprio questa domanda)
> - [M1/UD2/L2 — Esperimenti compositi](../../M1_Probabilita_elementare/UD2/L2%20-%20Esperimenti%20compositi.md) (estrazioni senza reinserimento, prove non indipendenti)
> - Esercizi analoghi già svolti: [Esame 19/09/2025 — Domanda 1](7_esame_19_settembre_2025_soluzione.md) e [Esame 05/07/2025 — Domanda 3](2_esame_5_luglio_2025_soluzione.md)

### Traccia originale

> Un controllo di qualità viene effettuato su un lotto di 24 componenti elettronici, tra i quali sono presenti 6 componenti difettosi. Un ispettore seleziona casualmente un campione di 4 componenti dal lotto senza reinserimento. Sia $X$ la variabile aleatoria che rappresenta il numero di componenti difettosi nel campione.
>
> (a) (2 punti) Si identifichi la distribuzione di probabilità della variabile $X$, giustificando brevemente la scelta basandosi sulle modalità di campionamento.
>
> (b) (2 punti) Si calcoli la probabilità che il campione contenga esattamente 2 componenti difettosi.
>
> (c) (1 punto) Si calcoli la probabilità che il campione contenga almeno un componente difettoso.
>
> (d) (2 punti) Si determinino il valore atteso $E[X]$ e la varianza $Var(X)$ del numero di componenti difettosi nel campione.

### Soluzione

#### Parte (a) — Identificazione della distribuzione

Il campionamento è **senza reinserimento** da una popolazione **finita** ($N = 24$) divisa in due categorie (6 difettosi, 18 funzionanti): le estrazioni **non sono indipendenti** (a ogni estrazione la composizione del lotto cambia), quindi la binomiale non è applicabile. $X$ segue la distribuzione **Ipergeometrica** con $N = 24$, $K = 6$, $n = 4$:

$$P(X = k) = \frac{\binom{6}{k}\binom{18}{4-k}}{\binom{24}{4}}, \qquad k = 0, 1, 2, 3, 4$$

> 📌 La giustificazione richiesta è proprio questa: *senza reinserimento ⇒ probabilità di successo non costante ⇒ prove dipendenti ⇒ Ipergeometrica* (con reinserimento sarebbe stata $B(4, 1/4)$).

#### Parte (b) — Esattamente 2 difettosi

Con $\binom{24}{4} = 10626$, $\binom{6}{2} = 15$, $\binom{18}{2} = 153$:

$$P(X = 2) = \frac{15 \times 153}{10626} = \frac{2295}{10626} \approx \boxed{0.216}$$

#### Parte (c) — Almeno un difettoso

Per complemento, con $\binom{18}{4} = 3060$:

$$P(X \geq 1) = 1 - P(X = 0) = 1 - \frac{\binom{18}{4}}{\binom{24}{4}} = 1 - \frac{3060}{10626} \approx 1 - 0.288 = \boxed{0.712}$$

#### Parte (d) — Valore atteso e varianza

Con $p = K/N = 6/24 = 1/4$:

$$E[X] = n\,\frac{K}{N} = 4 \times \frac{1}{4} = \boxed{1}$$

$$Var(X) = n\,\frac{K}{N}\left(1 - \frac{K}{N}\right)\frac{N-n}{N-1} = 4 \times \frac{1}{4} \times \frac{3}{4} \times \frac{20}{23} = \frac{15}{23} \approx \boxed{0.652}$$

> 💡 La varianza è quella binomiale ($npq = 0.75$) moltiplicata per il **fattore di correzione per popolazione finita** $\frac{N-n}{N-1} = \frac{20}{23} < 1$: campionare senza reinserimento riduce la variabilità.

---

## Domanda 2 — Tentativi di login falliti: descrittiva e skewness (8 punti)

> **Riferimenti di teoria**:
>
> - [M3/UD2/L1 — Dalla distribuzione al campione – Statistica descrittiva](../../M3_Argomenti_avanzati/UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md) (bar plot delle frequenze, indicatori campionari)
> - [M2/UD1/L3 — Indicatori di posizione](../../M2_Variabili_aleatorie/UD1/L3%20-%20Indicatori%20di%20posizione.md) (media/mediana/moda e loro confronto)
> - [M2/UD1/L4 — Indicatori d'ampiezza](../../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md) (varianza campionaria)
> - [M3/UD4/L1 — CV, covarianza, correlazione di Pearson e skewness](../../M3_Argomenti_avanzati/UD4_Approfondimenti_Esame/L1%20-%20CV,%20covarianza,%20correlazione%20di%20Pearson%20e%20skewness.md) (lezione integrativa: regola media vs mediana per la skewness — parte (d))

### Traccia originale

> Un'azienda analizza il numero di tentativi falliti di login effettuati da 50 dipendenti in una settimana. I dati sono i seguenti:
>
> | Tentativi Falliti | 0 | 1 | 2 | 3 | 4 |
> |-------------------|--:|--:|--:|--:|--:|
> | Frequenza | 15 | 20 | 10 | 4 | 1 |
>
> (a) (2 punti) Si costruisca un bar plot che rappresenti la distribuzione dei tentativi.
>
> (b) (2 punti) Si calcolino la media, la mediana e la moda dei tentativi falliti.
>
> (c) (2 punti) Si calcolino la varianza campionaria e la deviazione standard.
>
> (d) (2 punti) Si commenti lo sbilanciamento (skewness) della distribuzione basandosi sul confronto tra media e mediana.

### Soluzione

#### Parte (a) — Bar plot

Barre verticali di altezza pari alla frequenza, una per ciascun valore $x \in \{0,1,2,3,4\}$ sull'asse orizzontale: altezze $15, 20, 10, 4, 1$. Il profilo sale da 0 a 1 (picco in $x=1$) e poi decresce rapidamente: una **coda destra** evidente, che anticipa la risposta alla (d).

#### Parte (b) — Media, mediana, moda

$$\bar{x} = \frac{0 \cdot 15 + 1 \cdot 20 + 2 \cdot 10 + 3 \cdot 4 + 4 \cdot 1}{50} = \frac{56}{50} = \boxed{1.12}$$

Mediana: con $n = 50$ è tra la 25ª e la 26ª osservazione ordinata; le cumulate sono $15$ (per $x{=}0$) e $35$ (per $x{=}1$): entrambe le posizioni cadono su $x = 1$ → mediana $= \boxed{1}$.

Moda: frequenza massima 20 → moda $= \boxed{1}$.

#### Parte (c) — Varianza e deviazione standard campionarie

Con $\sum f_i x_i^2 = 0 + 20 + 40 + 36 + 16 = 112$:

$$\sum_i f_i(x_i - \bar{x})^2 = \sum f_i x_i^2 - n\bar{x}^2 = 112 - 50 \times 1.2544 = 112 - 62.72 = 49.28$$

$$s^2 = \frac{49.28}{49} \approx \boxed{1.006} \qquad s \approx \boxed{1.003}$$

#### Parte (d) — Skewness

$$\bar{x} = 1.12 > \text{mediana} = 1$$

Media maggiore della mediana ⇒ **asimmetria positiva** (coda a destra): la maggior parte dei dipendenti fallisce 0–1 tentativi, ma pochi casi con 3–4 fallimenti trascinano la media sopra la mediana. Interpretazione operativa (contesto security): la coda destra è la zona da monitorare — pochi account con molti login falliti sono i candidati per un'ispezione (possibili attacchi o password dimenticate).

---

## Domanda 3 — Email: Esponenziale e Normale (8 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD2/L2 — Densità Esponenziale Negativa](../../M2_Variabili_aleatorie/UD2/L2%20-%20Densit%C3%A0%20Esponenziale%20Negativa.md) (sopravvivenza, cumulativa)
> - [M2/UD2/L3 — Caratteristiche dell'esponenziale](../../M2_Variabili_aleatorie/UD2/L3%20-%20Caratteristiche%20dell%E2%80%99esponenziale.md) (memoryless, quantili e mediana)
> - [M2/UD4/L1 — Densità Gaussiana o Normale](../../M2_Variabili_aleatorie/UD4/L1%20-%20Densit%C3%A0%20Gaussiana%20o%20Normale.md) (standardizzazione, uso delle tavole)

### Traccia originale

> Il tempo di attesa per la ricezione di un'email di conferma su un server segue una distribuzione Esponenziale con un valore atteso (media) di 10 minuti.
>
> (a) (2 punti) Qual è la probabilità che l'email arrivi dopo più di 15 minuti?
>
> (b) (2 punti) Se sono già passati 5 minuti e l'email non è arrivata, qual è la probabilità che arrivi entro i prossimi 10 minuti?
>
> (c) (2 punti) Si determini il tempo mediano di attesa.
>
> (d) (2 punti) Si supponga ora che la dimensione dei file allegati alle email ricevute dal server segua una distribuzione Normale con media $\mu = 500$ kByte e deviazione standard $\sigma = 100$ kByte. Qual è la probabilità che un'email scelta a caso abbia una dimensione inferiore a 350 kByte?

### Soluzione

$T \sim \text{Exp}(\lambda)$ con $E[T] = 1/\lambda = 10$ min ⇒ $\lambda = 0.1$ min⁻¹. Sopravvivenza: $P(T > t) = e^{-t/10}$.

#### Parte (a) — Oltre 15 minuti

$$P(T > 15) = e^{-15/10} = e^{-1.5} \approx \boxed{0.2231}$$

#### Parte (b) — Memoryless

Si chiede $P(T \leq 15 \mid T > 5)$. Per la proprietà senza memoria, il tempo residuo di attesa si comporta come un'attesa nuova:

$$P(T \leq 15 \mid T > 5) = P(T \leq 10) = 1 - e^{-10/10} = 1 - e^{-1} \approx \boxed{0.6321}$$

#### Parte (c) — Mediana

La mediana $t_{med}$ risolve $P(T > t_{med}) = 0.5$:

$$e^{-t_{med}/10} = 0.5 \quad\Rightarrow\quad t_{med} = 10 \ln 2 \approx \boxed{6.93 \text{ minuti}}$$

> 📌 Nell'esponenziale la mediana ($\approx 6.93$) è sempre **minore della media** (10): la coda destra lunga trascina la media in alto. $\ln 2 \approx 0.693$ è una costante da ricordare.

#### Parte (d) — Dimensione allegati: Normale

$$z = \frac{350 - 500}{100} = -1.5 \qquad P(X < 350) = \Phi(-1.5) = 1 - \Phi(1.5) = 1 - 0.9332 = \boxed{0.0668 \ (\approx 6.7\%)}$$

---

## Domanda 4 — Due carte senza reinserimento: diagramma ad albero (7 punti)

> **Riferimenti di teoria**:
>
> - [M1/UD2/L1 — Il principio di moltiplicazione](../../M1_Probabilita_elementare/UD2/L1%20-%20Il%20principio%20di%20moltiplicazione.md) (cammini dell'albero delle probabilità)
> - [M1/UD2/L3 — Legge del prodotto per due eventi](../../M1_Probabilita_elementare/UD2/L3%20-%20Legge%20del%20prodotto%20per%20due%20eventi.md) (probabilità congiunta con condizionamento)
> - [M1/UD3/L2 — Legge della Somma (o Legge delle Alternative)](../../M1_Probabilita_elementare/UD3/L2%20-%20Legge%20della%20Somma%20(o%20Legge%20delle%20Alternative).md) (evento complementare / somma sui cammini)

### Traccia originale

> Un mazzo di 40 carte contiene 10 carte per ogni seme (Cuori, Quadri, Fiori, Picche). Si estraggono due carte senza reinserimento.
>
> (a) (2 punti) Si disegni un diagramma ad albero che rappresenti l'estrazione del seme (Cuori vs Non-Cuori) per le due carte.
>
> (b) (2 punti) Qual è la probabilità che entrambe le carte siano di Cuori?
>
> (c) (3 punti) Qual è la probabilità che almeno una delle due carte sia di Cuori?

### Soluzione

#### Parte (a) — Diagramma ad albero

Primo livello (prima estrazione): $P(C_1) = 10/40 = 1/4$, $P(\bar{C}_1) = 30/40 = 3/4$. Secondo livello (seconda estrazione, **condizionata** perché senza reinserimento):

- Ramo $C_1$: restano 9 cuori su 39 → $P(C_2 \mid C_1) = 9/39$, $P(\bar{C}_2 \mid C_1) = 30/39$
- Ramo $\bar{C}_1$: restano 10 cuori su 39 → $P(C_2 \mid \bar{C}_1) = 10/39$, $P(\bar{C}_2 \mid \bar{C}_1) = 29/39$

I quattro cammini foglia, con le probabilità ottenute per prodotto lungo i rami:

| Cammino | Probabilità | Valore |
|---------|-------------|-------:|
| $C_1 \cap C_2$ | $\frac{10}{40}\cdot\frac{9}{39}$ | $\frac{3}{52} \approx 0.0577$ |
| $C_1 \cap \bar{C}_2$ | $\frac{10}{40}\cdot\frac{30}{39}$ | $\frac{5}{26} \approx 0.1923$ |
| $\bar{C}_1 \cap C_2$ | $\frac{30}{40}\cdot\frac{10}{39}$ | $\frac{5}{26} \approx 0.1923$ |
| $\bar{C}_1 \cap \bar{C}_2$ | $\frac{30}{40}\cdot\frac{29}{39}$ | $\frac{29}{52} \approx 0.5577$ |

(Verifica: i quattro cammini sommano a 1. ✓)

#### Parte (b) — Entrambe di Cuori

$$P(C_1 \cap C_2) = \frac{10}{40} \times \frac{9}{39} = \frac{90}{1560} = \frac{3}{52} \approx \boxed{0.0577}$$

#### Parte (c) — Almeno una di Cuori

Per complemento (il cammino "nessun cuore" è uno solo):

$$P(\text{almeno un cuore}) = 1 - P(\bar{C}_1 \cap \bar{C}_2) = 1 - \frac{30}{40} \times \frac{29}{39} = 1 - \frac{29}{52} = \frac{23}{52} \approx \boxed{0.4423}$$

> 💡 In alternativa, sommando i tre cammini favorevoli: $\frac{3}{52} + \frac{5}{26} + \frac{5}{26} = \frac{3 + 10 + 10}{52} = \frac{23}{52}$ ✓ — il complemento resta la via più rapida e meno soggetta a errori.

---

## Domanda 5 — Test per malattia rara: Bayes (3 punti)

> **Riferimenti di teoria**:
>
> - [M1/UD5/L2 — Bayes – formulazione, terminologia ed esempi](../../M1_Probabilita_elementare/UD5/L2%20-%20Bayes%20%E2%80%93%20formulazione,%20terminologia%20ed%20esempi.md) (Bayes con probabilità totale)
> - [M1/UD5/L3 — Prior e Verosimiglianza](../../M1_Probabilita_elementare/UD5/L3%20-%20Prior%20e%20Verosimiglianza.md) (sensibilità e falsi positivi come verosimiglianze)
> - Esercizio quasi identico (con anche il secondo test iterato): [Esame 05/09/2025 — Domanda 4](6_esame_5_settembre_2025_soluzione.md)

### Traccia originale

> Un test medico per una malattia rara (incidenza nella popolazione dello 0.1%) ha una sensibilità (capacità di rilevare la malattia) del 99% e produce un falso positivo nel 5% dei casi.
>
> (a) (3 punti) Se un individuo risulta positivo al test, qual è la probabilità a posteriori che sia effettivamente malato? Si commenti il risultato.

### Soluzione

Dati: $P(M) = 0.001$, $P(T^+ \mid M) = 0.99$, $P(T^+ \mid M^c) = 0.05$.

Probabilità totale di un positivo:

$$P(T^+) = 0.99 \times 0.001 + 0.05 \times 0.999 = 0.00099 + 0.04995 = 0.05094$$

Bayes:

$$P(M \mid T^+) = \frac{0.99 \times 0.001}{0.05094} = \frac{0.00099}{0.05094} \approx \boxed{0.0194 \ (\approx 1.9\%)}$$

**Commento:** il risultato sorprende — un test "accurato al 99%" produce positivi che sono malati solo nel 2% dei casi. La causa è la **rarità della malattia**: su 100 000 persone ci si attendono $\approx 99$ veri positivi ma $\approx 4995$ falsi positivi; i falsi allarmi della popolazione sana dominano. È il classico **paradosso dei falsi positivi**, e la ragione per cui ai test di screening su malattie rare seguono sempre test di conferma (si veda l'aggiornamento iterativo nell'esame del 05/09/2025).

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1 | 7 | Ipergeometrica ($N{=}24, K{=}6, n{=}4$); $P(X{=}2)\approx 0.216$; $P(X\geq 1)\approx 0.712$; $E[X]=1$, $Var\approx 0.652$ |
| 2 | 8 | $\bar{x}=1.12$; mediana 1; moda 1; $s^2\approx 1.006$; asimmetria positiva (media > mediana) |
| 3 | 8 | $e^{-1.5}\approx 0.2231$; memoryless $1-e^{-1}\approx 0.6321$; mediana $10\ln 2\approx 6.93$ min; $\Phi(-1.5)\approx 0.0668$ |
| 4 | 7 | Albero condizionato; $P(2 \text{ cuori})=3/52\approx 0.0577$; $P(\geq 1)=23/52\approx 0.4423$ |
| 5 | 3 | $P(M\mid T^+)\approx 0.019$ — paradosso dei falsi positivi |
