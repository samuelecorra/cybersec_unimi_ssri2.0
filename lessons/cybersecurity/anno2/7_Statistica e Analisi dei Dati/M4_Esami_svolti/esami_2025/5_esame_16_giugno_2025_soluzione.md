# Esame del 16 giugno 2025 — Soluzione completa

> 📌 **Informazioni appello:** 16/06/2025 — 5 domande, 33 punti totali.
> Argomenti: Affidabilità di una rete a ponte, Statistica descrittiva, Stima di proporzione con IC, Somma di uniformi (convoluzione), Probabilità totale + Bayes (urne).

---

## Domanda 1 — Affidabilità di una rete a ponte (8 punti)

> **Riferimenti di teoria**:
>
> - [M1/UD2/L5 — Legge del prodotto per eventi indipendenti e applicazioni all'affidabilità](../../M1_Probabilita_elementare/UD2/L5%20-%20Legge%20del%20prodotto%20per%20eventi%20indipendenti%20e%20applicazioni%20all%E2%80%99affidabilit%C3%A0.md) (affidabilità di componenti in serie)
> - [M1/UD3/L2 — Legge della Somma (o Legge delle Alternative)](../../M1_Probabilita_elementare/UD3/L2%20-%20Legge%20della%20Somma%20(o%20Legge%20delle%20Alternative).md) (condizionamento su casi mutuamente esclusivi — il cuore del metodo)
> - [M1/UD3/L4 — Applicazioni della legge della somma all'affidabilità dei sistemi](../../M1_Probabilita_elementare/UD3/L4%20-%20Applicazioni%20della%20legge%20della%20somma%20all%E2%80%99affidabilit%C3%A0%20dei%20sistemi.md) (parallelo: $1-q^2$)

### Traccia originale

> Si consideri un sistema di comunicazione da **i** a **o**, composto da cinque linee di trasmissione identiche, dove ciascuna linea ha una probabilità di funzionare correttamente $p$ e una probabilità di fallire $q = 1-p$. Tutte le linee funzionano indipendentemente l'una dall'altra.
>
> ![](imgs/Pasted%20image%2020260901130326.png)
>
> (a) (3 punti) Calcolare l'affidabilità complessiva dell'intero sistema. Spiegare il metodo utilizzato per affrontare questa configurazione.
>
> (b) (2 punti) Utilizzando la formula sopra derivata, determinare l'affidabilità complessiva del sistema se la probabilità di funzionamento di ogni singola linea è $p = 0.9$.
>
> (c) (3 punti) Si calcoli la fallibilità del sistema nel caso in cui la linea di trasmissione bidirezionale centrale abbia fallibilità 0.5, le altre linee rimanendo uguali e affidabili al 90%.

### Soluzione

La topologia è una **rete a ponte**: da $i$ partono due linee verso i nodi intermedi $A$ (sopra) e $B$ (sotto), da ciascun nodo intermedio parte una linea verso $o$, e i due nodi intermedi sono collegati dalla **linea centrale bidirezionale** $C$. Il ponte non è né una pura serie né un puro parallelo, quindi le formule elementari non bastano da sole.

#### Parte (a) — Metodo del condizionamento sulla linea centrale

Il metodo standard è **condizionare sullo stato della linea centrale** (decomposizione secondo la legge delle alternative): i due casi "C funziona" e "C guasta" sono mutuamente esclusivi ed esaustivi, e in ciascun caso la rete residua si riduce a combinazioni serie/parallelo elementari.

**Caso 1 — $C$ funziona** (probabilità $p$): i nodi $A$ e $B$ risultano cortocircuitati e diventano un unico nodo intermedio. Il sistema funziona se **almeno una** delle due linee d'ingresso funziona **e** **almeno una** delle due linee d'uscita funziona:

$$R_{\text{on}} = (1-q^2)\cdot(1-q^2) = (1-q^2)^2$$

**Caso 2 — $C$ guasta** (probabilità $q$): la linea centrale sparisce e restano due cammini indipendenti $i\!-\!A\!-\!o$ e $i\!-\!B\!-\!o$, ciascuno serie di due linee (affidabilità $p^2$), posti in parallelo:

$$R_{\text{off}} = 1-(1-p^2)^2$$

Per la legge della probabilità totale:

$$\boxed{R = p\,(1-q^2)^2 + q\,\left[1-(1-p^2)^2\right]}$$

> 💡 Il condizionamento trasforma un grafo "intrattabile" in due sotto-reti elementari. È la stessa tecnica di decomposizione vista per i sistemi complessi non riducibili a serie/parallelo.

#### Parte (b) — Valutazione con $p = 0.9$

Con $p = 0.9$, $q = 0.1$:

- $R_{\text{on}} = (1-0.01)^2 = 0.99^2 = 0.9801$
- $R_{\text{off}} = 1-(1-0.81)^2 = 1-0.19^2 = 1-0.0361 = 0.9639$

$$R = 0.9 \times 0.9801 + 0.1 \times 0.9639 = 0.88209 + 0.09639 = \boxed{0.97848 \approx 97.85\%}$$

#### Parte (c) — Linea centrale degradata

Ora la linea centrale ha fallibilità $q_C = 0.5$ (quindi $p_C = 0.5$), mentre le altre quattro restano a $p = 0.9$. La formula del condizionamento resta identica, cambia solo il peso dei due casi:

$$R' = p_C\,(1-q^2)^2 + q_C\,\left[1-(1-p^2)^2\right] = 0.5 \times 0.9801 + 0.5 \times 0.9639 = 0.49005 + 0.48195 = 0.972$$

La **fallibilità** richiesta è il complemento:

$$F' = 1 - R' = \boxed{0.028 = 2.8\%}$$

> ⚠️ Si noti quanto poco la degradazione della linea centrale peggiori il sistema (da 2.15% a 2.8% di fallibilità): il ponte è ridondante e la linea centrale serve solo negli scenari misti. È un classico spunto di discussione da punteggio pieno.

---

## Domanda 2 — Statistica descrittiva: temperature (7 punti)

> **Riferimenti di teoria**:
>
> - [M2/UD1/L3 — Indicatori di posizione](../../M2_Variabili_aleatorie/UD1/L3%20-%20Indicatori%20di%20posizione.md) (media, mediana, moda)
> - [M2/UD1/L4 — Indicatori d'ampiezza](../../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md) (range, varianza, deviazione standard)
> - [M3/UD2/L1 — Dalla distribuzione al campione – Statistica descrittiva](../../M3_Argomenti_avanzati/UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md) (indicatori campionari, denominatore $n-1$)

### Traccia originale

> Si consideri il seguente set di dati che rappresenta le temperature (°C) massime giornaliere registrate in una località durante una settimana: 18, 20, 19, 22, 20, 21, 20.
> Calcolare i seguenti indicatori di statistica descrittiva, mostrando i passaggi:
>
> (a) (1 punto) La media campionaria ($\bar{x}$)
> (b) (2 punti) La mediana
> (c) (1 punto) La moda
> (d) (1 punto) Il range
> (e) (2 punti) La deviazione standard campionaria.

### Soluzione

Dati ordinati ($n = 7$): $18,\ 19,\ 20,\ 20,\ 20,\ 21,\ 22$.

#### Parte (a) — Media campionaria

$$\bar{x} = \frac{18+20+19+22+20+21+20}{7} = \frac{140}{7} = \boxed{20\ °C}$$

#### Parte (b) — Mediana

Con $n = 7$ (dispari) la mediana è il valore in posizione $(n+1)/2 = 4$ nei dati ordinati:

$$\text{mediana} = \boxed{20\ °C}$$

#### Parte (c) — Moda

Il valore più frequente è 20 (compare 3 volte): $\text{moda} = \boxed{20\ °C}$.

#### Parte (d) — Range

$$\text{range} = x_{\max} - x_{\min} = 22 - 18 = \boxed{4\ °C}$$

#### Parte (e) — Deviazione standard campionaria

Scarti quadratici dalla media ($\bar{x} = 20$): $(-2)^2 + 0^2 + (-1)^2 + 2^2 + 0^2 + 1^2 + 0^2 = 4+0+1+4+0+1+0 = 10$.

$$s^2 = \frac{\sum (x_i - \bar{x})^2}{n-1} = \frac{10}{6} \approx 1.667 \qquad s = \sqrt{1.667} \approx \boxed{1.29\ °C}$$

> 📌 Al denominatore va $n-1$ (correzione di Bessel): è la **varianza campionaria**, stimatore non distorto. Usare $n$ è l'errore più frequente in questa domanda.

---

## Domanda 3 — Stima di una proporzione con intervallo di confidenza (6 punti)

> **Riferimenti di teoria**:
>
> - [M3/UD3/L2 — Stima della media](../../M3_Argomenti_avanzati/UD3/L2%20-%20Stima%20della%20media.md) (stima di una probabilità come caso particolare, intervalli di confidenza)
> - [M3/UD2/L3 — Distribuzione della media campionaria](../../M3_Argomenti_avanzati/UD2/L3%20-%20Distribuzione%20della%20media%20campionaria.md) (perché la frequenza empirica è approssimativamente gaussiana)
> - [M3/UD1/L6 — Il Teorema del Limite Centrale](../../M3_Argomenti_avanzati/UD1/L6%20-%20Il%20Teorema%20del%20Limite%20Centrale.md) (fondamento dell'approssimazione normale)

### Traccia originale

> Un'azienda produttrice di lampadine desidera stimare la vera proporzione ($p$) di lampadine difettose nella sua produzione. Per fare ciò, viene prelevato un campione casuale di 200 lampadine dalla linea di produzione. Dopo averle testate, si riscontrano 20 lampadine difettose. Basandosi sulle informazioni fornite,
>
> (a) (2 punti) Qual è la stima puntuale della probabilità ($p$) che una lampadina prodotta sia difettosa?
> (b) (2 punti) Calcola un intervallo di confidenza al 95% per la vera probabilità ($p$) di lampadine difettose.
> (c) (2 punti) Spiega il significato di questo intervallo di confidenza nel contesto del problema.

### Soluzione

#### Parte (a) — Stima puntuale

La stima puntuale è la frequenza empirica di difettose nel campione:

$$\hat{p} = \frac{k}{n} = \frac{20}{200} = \boxed{0.10}$$

#### Parte (b) — Intervallo di confidenza al 95%

Per $n$ grande, la proporzione campionaria è approssimativamente gaussiana con errore standard:

$$\sigma_{\hat{p}} = \sqrt{\frac{\hat{p}(1-\hat{p})}{n}} = \sqrt{\frac{0.10 \times 0.90}{200}} = \sqrt{0.00045} \approx 0.0212$$

Al 95% il quantile critico è $z_{\alpha/2} = 1.96$:

$$IC_{95\%} = \hat{p} \pm 1.96\,\sigma_{\hat{p}} = 0.10 \pm 1.96 \times 0.0212 = 0.10 \pm 0.0416$$

$$\boxed{IC_{95\%} \approx [0.058,\ 0.142]}$$

> 💡 Con la regola pratica del corso $\hat{p} \pm 2\sigma$ (e la semplificazione $\sigma \approx \sqrt{k}/n = \sqrt{20}/200 \approx 0.0224$) si ottiene $[0.055,\ 0.145]$: numericamente quasi identico, entrambi accettati.

#### Parte (c) — Interpretazione

L'interpretazione corretta è **frequentista**: se ripetessimo molte volte l'esperimento (campioni di 200 lampadine) e costruissimo ogni volta l'intervallo con questa procedura, circa il **95% degli intervalli conterrebbe la vera proporzione $p$**. Nel contesto: con confidenza del 95%, la vera percentuale di lampadine difettose della produzione è compresa tra circa il 5.8% e il 14.2%.

> ⚠️ Errore classico da evitare: dire "c'è il 95% di probabilità che $p$ stia nell'intervallo". La vera $p$ è un numero fisso, non una variabile aleatoria: è l'**intervallo** a essere casuale, non $p$.

---

## Domanda 4 — Somma di due uniformi: convoluzione (6 punti)

> **Riferimenti di teoria**:
>
> - [M3/UD1/L1 — Introduzione alla somma di variabili aleatorie indipendenti](../../M3_Argomenti_avanzati/UD1/L1%20-%20Introduzione%20alla%20somma%20di%20variabili%20aleatorie%20indipendenti.md) (perché la densità della somma è la convoluzione; esempio della triangolare)
> - [M3/UD1/L2 — Convoluzione di distribuzioni e densità](../../M3_Argomenti_avanzati/UD1/L2%20-%20Convoluzione%20di%20distribuzioni%20e%20densit%C3%A0.md) (calcolo esplicito dell'integrale di convoluzione)

### Traccia originale

> Un'azienda produce un apparato in due fasi sequenziali e indipendenti. La durata di ciascuna fase è una variabile aleatoria continua. La durata $X$ della Fase 1 è uniformemente distribuita tra 0 e 1 ore. La sua densità di probabilità è $f_X(x) = \begin{cases} 1 & \text{per } 0 \leq x \leq 1 \\ 0 & \text{altrimenti} \end{cases}$. La durata $Y$ della Fase 2 è identicamente distribuita rispetto a $X$. Dato che le durate delle due fasi sono indipendenti:
>
> (a) (2 punti) si definisca la durata totale di produzione $Z$ dell'apparato.
> (b) (2 punti) si calcoli la densità di probabilità di $Z$.
> (c) (2 punti) si calcoli il valore atteso di $Z$.

### Soluzione

#### Parte (a) — Definizione di $Z$

Le fasi sono sequenziali, quindi la durata totale è la **somma** delle due durate:

$$\boxed{Z = X + Y}, \qquad Z \in [0, 2]$$

#### Parte (b) — Densità di $Z$ (convoluzione)

Per variabili indipendenti la densità della somma è la convoluzione delle densità:

$$f_Z(z) = \int_{-\infty}^{+\infty} f_X(x)\, f_Y(z-x)\, dx$$

L'integrando vale 1 quando **entrambe** le condizioni $0 \leq x \leq 1$ e $0 \leq z-x \leq 1$ (cioè $z-1 \leq x \leq z$) sono soddisfatte; l'integrale è quindi la lunghezza dell'intervallo $[\max(0, z-1),\ \min(1, z)]$:

- **Per $0 \leq z \leq 1$**: l'intervallo è $[0, z]$, quindi $f_Z(z) = z$;
- **Per $1 \leq z \leq 2$**: l'intervallo è $[z-1, 1]$, quindi $f_Z(z) = 1-(z-1) = 2-z$;
- altrove $f_Z(z) = 0$.

$$\boxed{f_Z(z) = \begin{cases} z & 0 \leq z \leq 1 \\ 2-z & 1 \leq z \leq 2 \\ 0 & \text{altrove} \end{cases}}$$

> 💡 È la celebre **densità triangolare**: la somma di due uniformi non è uniforme, i valori centrali ($z \approx 1$) sono i più probabili perché realizzabili in più modi. Verifica di sanità: l'area del triangolo (base 2, altezza 1) vale 1. ✓

#### Parte (c) — Valore atteso

Per la linearità del valore atteso (vale sempre, indipendenza non necessaria):

$$E[Z] = E[X] + E[Y] = \frac{1}{2} + \frac{1}{2} = \boxed{1 \text{ ora}}$$

Coerente con la simmetria della triangolare attorno a $z=1$.

---

## Domanda 5 — Urne e moneta: probabilità totale e Bayes (6 punti)

> **Riferimenti di teoria**:
>
> - [M1/UD2/L4 — Probabilità di un evento condizionato](../../M1_Probabilita_elementare/UD2/L4%20-%20Probabilit%C3%A0%20di%20un%20evento%20condizionato.md) (probabilità condizionata e legge del prodotto)
> - [M1/UD5/L2 — Bayes – formulazione, terminologia ed esempi](../../M1_Probabilita_elementare/UD5/L2%20-%20Bayes%20%E2%80%93%20formulazione,%20terminologia%20ed%20esempi.md) (prior, likelihood, evidence, posterior)
> - [M1/UD5/L3 — Prior e Verosimiglianza](../../M1_Probabilita_elementare/UD5/L3%20-%20Prior%20e%20Verosimiglianza.md) (come la verosimiglianza sposta la fiducia tra ipotesi)

### Traccia originale

> Si considerino due urne con le seguenti caratteristiche:
>
> - U1 contiene 4 palline rosse e 6 palline blu.
> - U2 contiene 7 palline rosse e 3 palline blu.
>
> Si decide di scegliere un'urna lanciando una moneta equa: se esce Testa, si sceglie U1; se esce Croce, si sceglie U2. Una volta scelta l'urna, si estrae casualmente una pallina.
>
> (a) (2 punti) Qual è la probabilità totale che la pallina estratta sia rossa?
> (b) (3 punti) Supponendo di aver estratto una pallina rossa, qual è la probabilità che essa provenga dall'urna U1?
> (c) (1 punto) Si dia una giustificazione al fatto che $P(U1|R) < P(U1)$.

### Soluzione

Dati: $P(U_1) = P(U_2) = 0.5$ (moneta equa); $P(R \mid U_1) = 4/10 = 0.4$; $P(R \mid U_2) = 7/10 = 0.7$.

#### Parte (a) — Probabilità totale

$$P(R) = P(R \mid U_1)P(U_1) + P(R \mid U_2)P(U_2) = 0.4 \times 0.5 + 0.7 \times 0.5 = 0.20 + 0.35 = \boxed{0.55}$$

#### Parte (b) — Bayes

$$P(U_1 \mid R) = \frac{P(R \mid U_1)\,P(U_1)}{P(R)} = \frac{0.4 \times 0.5}{0.55} = \frac{0.20}{0.55} = \frac{4}{11} \approx \boxed{0.364}$$

#### Parte (c) — Perché la posterior scende sotto la prior

Prima dell'estrazione le due urne erano equiprobabili ($P(U_1) = 0.5$). Ma il rosso è un esito **più tipico di U2** ($0.7$) che di U1 ($0.4$): osservare una pallina rossa è un'evidenza che pesa a favore di U2. Formalmente, la verosimiglianza di U1 è inferiore alla media, $P(R \mid U_1) = 0.4 < P(R) = 0.55$, e per Bayes il rapporto $P(R \mid U_1)/P(R) = 0.4/0.55 < 1$ moltiplica (e quindi riduce) la prior. L'osservazione ha correttamente **aggiornato la fiducia** verso l'urna più ricca di rosse.

---

| Domanda | Punti | Risultato |
|---------|------:|-----------|
| 1 | 8 | $R = p(1-q^2)^2 + q[1-(1-p^2)^2]$; $R(0.9) \approx 0.9785$; fallibilità (c) $= 0.028$ |
| 2 | 7 | $\bar{x}=20$; mediana $=20$; moda $=20$; range $=4$; $s \approx 1.29$ |
| 3 | 6 | $\hat{p}=0.10$; $IC_{95\%} \approx [0.058, 0.142]$; interpretazione frequentista |
| 4 | 6 | $Z = X+Y$; densità triangolare su $[0,2]$; $E[Z] = 1$ |
| 5 | 6 | $P(R)=0.55$; $P(U_1\mid R)=4/11\approx 0.364$; likelihood del rosso più alta per U2 |
