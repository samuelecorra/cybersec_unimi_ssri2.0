# **M3 UD4 Lezione 1 - Coefficiente di variazione, covarianza, correlazione di Pearson e skewness**

> ⚠️ **Unità integrativa d'esame.** Questi indicatori sono richiesti negli appelli del [19/09/2025 (D4d-e)](../../M4_Esami_svolti/esami_2025/7_esame_19_settembre_2025_soluzione.md) e del [16/01/2026 (D2d)](../../M4_Esami_svolti/esami_2025/8_esame_16_gennaio_2026_soluzione.md), ma **non hanno una lezione dedicata nel corso ufficiale** (la skewness è solo accennata come momento di ordine 3 in [M2/UD1/L4](../../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md)). Questa lezione completa la statistica descrittiva a livello d'esame.

### **1. Coefficiente di variazione (CV)**

##### **1.1. Definizione e motivazione**

La deviazione standard $s$ ha le stesse **unità di misura** dei dati: dire "$s = 17.34$ kg" non dice, da solo, se la dispersione è tanta o poca. Il **coefficiente di variazione** normalizza la dispersione rispetto alla media, producendo un numero **adimensionale**:

$$
CV = \frac{s}{\bar{x}} \qquad (\text{spesso espresso in percentuale: } CV \times 100\%)
$$

##### **1.2. A cosa serve**

- **Confrontare la variabilità di grandezze con unità diverse** (es. pesi in kg vs altezze in cm) o con **scale diverse** (stipendi di due paesi con valute differenti).
- Dare un senso relativo alla dispersione: $CV = 0.28$ significa "la deviazione standard è il 28% della media".

> ⚠️ Il CV **perde significato quando $\bar{x} \approx 0$** (esplode) e in generale per dati che possono cambiare segno: si usa per grandezze positive (pesi, tempi, importi).

##### **1.3. Esempio (appello 19/09/2025, D4d)**

Pesi: $\bar{x} \approx 62.86$ kg, $s \approx 17.34$ kg:

$$
CV = \frac{17.34}{62.86} \approx 0.276 \;\; (\approx 27.6\%)
$$

### **2. Covarianza campionaria**

##### **2.1. Definizione**

Dati $n$ **coppie** $(x_i, y_i)$, la covarianza campionaria misura quanto le due variabili "si muovono insieme":

$$
s_{xy} = \frac{1}{n-1}\sum_{i=1}^{n} (x_i - \bar{x})(y_i - \bar{y})
$$

Il segno si legge dal prodotto degli scarti: se quando $x_i$ è sopra la sua media anche $y_i$ tende a esserlo (e viceversa), i prodotti sono per lo più positivi e $s_{xy} > 0$; se le variabili si muovono in direzioni opposte, $s_{xy} < 0$.

> 📌 Come per la varianza, al denominatore va $n-1$ (stimatore campionario). Coerenza: $s_{xx} = s_x^2$, la covarianza di una variabile con sé stessa è la sua varianza.

##### **2.2. Il limite della covarianza**

Il **valore numerico** di $s_{xy}$ dipende dalle unità di misura (kg·cm, nell'esempio): non è confrontabile né interpretabile in assoluto. Serve normalizzarla — ed è esattamente ciò che fa la correlazione.

### **3. Correlazione campionaria di Pearson**

##### **3.1. Definizione**

$$
r = \frac{s_{xy}}{s_x \, s_y} \qquad \text{con} \qquad -1 \leq r \leq +1
$$

La normalizzazione con le due deviazioni standard rende $r$ **adimensionale** e confinato in $[-1, 1]$.

##### **3.2. Interpretazione**

| Valore di $r$ | Lettura |
|---|---|
| $r = +1$ | relazione lineare crescente perfetta |
| $0.7 \lesssim r < 1$ | correlazione lineare positiva forte |
| $0.3 \lesssim r \lesssim 0.7$ | correlazione positiva moderata |
| $r \approx 0$ | **assenza di relazione lineare** |
| valori negativi | analogo, con relazione decrescente |

> ⚠️ Due avvertenze da citare sempre in sede d'esame:
>
> 1. **Correlazione ≠ causalità**: $r$ alto non dimostra che una variabile causi l'altra (possibili variabili nascoste o coincidenze).
> 2. $r$ misura solo relazioni **lineari**: una relazione perfetta ma non lineare (es. $y = x^2$ su un dominio simmetrico) può dare $r \approx 0$.

##### **3.3. Esempio (appello 19/09/2025, D4e)**

Pesi/altezze di 7 amici: $s_{xy} = 139.82$, $s_x = 17.34$ kg, $s_y = 8.71$ cm:

$$
r = \frac{139.82}{17.34 \times 8.71} = \frac{139.82}{151.03} \approx 0.926
$$

Correlazione lineare **molto forte e positiva**: all'aumentare del peso l'altezza tende ad aumentare in modo quasi perfettamente lineare (senza implicazione causale).

### **4. Skewness (asimmetria)**

##### **4.1. La regola operativa media vs mediana**

La media è sensibile ai valori estremi, la mediana no: il loro confronto rivela la **direzione della coda** della distribuzione.

$$
\bar{x} > \text{mediana} \;\Rightarrow\; \text{asimmetria positiva (coda a destra)}
$$

$$
\bar{x} < \text{mediana} \;\Rightarrow\; \text{asimmetria negativa (coda a sinistra)}
$$

$$
\bar{x} \approx \text{mediana} \;\Rightarrow\; \text{distribuzione (quasi) simmetrica}
$$

> 💡 Intuizione: pochi valori molto grandi "trascinano" la media verso destra oltre la mediana (che guarda solo le posizioni). Esempio classico: i redditi — la mediana descrive il cittadino tipico, la media è gonfiata dai pochi ricchissimi.

##### **4.2. Collegamento con i momenti**

Formalmente l'asimmetria si misura col **momento centrale standardizzato di ordine 3**, $\gamma_1 = E[(X-\mu)^3]/\sigma^3$ (accennato in [M2/UD1/L4](../../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md)): positivo per coda destra, negativo per coda sinistra, nullo per distribuzioni simmetriche. All'esame è però richiesta la **lettura qualitativa** via media/mediana, non il calcolo di $\gamma_1$.

##### **4.3. Esempio (appello 16/01/2026, D2d)**

Tentativi di login falliti: $\bar{x} = 1.12 > \text{mediana} = 1$ ⇒ **asimmetria positiva**: la massa è su 0–1 tentativi, ma pochi dipendenti con 3–4 fallimenti allungano la coda destra (ed è proprio la coda la zona d'interesse in ottica security).

Confronto istruttivo con l'appello 19/09/2025 (pesi): lì $\bar{x} = 62.86 \gg \text{mediana} = 52$, asimmetria positiva marcata — e infatti la traccia si presta a commentare che la **mediana è l'indicatore più rappresentativo** in presenza di outlier.

### **5. Nota sui grafici richiesti negli appelli**

- **Bar plot** (16/01/2026, D2a): barre verticali con altezza pari alla **frequenza** di ciascun valore discreto — è la rappresentazione naturale di una tabella valore/frequenza.
- **Boxplot**: già trattato in [M3/UD2/L1](../UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md); si costruisce con minimo, $Q_1$, mediana, $Q_3$, massimo (e l'IQR $= Q_3 - Q_1$ come altezza della scatola).

### **6. Errori tipici da esame**

> ⚠️ In ordine di frequenza:
>
> 1. Riportare il CV **con unità di misura**: è adimensionale per costruzione.
> 2. Interpretare una covarianza "grande" come relazione forte: senza normalizzazione il numero non è confrontabile — la forza si legge solo da $r$.
> 3. Concludere causalità da $r$ alto.
> 4. Invertire la regola della skewness (media > mediana = coda a **destra**, non a sinistra: la media insegue la coda).
> 5. Usare $n$ invece di $n-1$ nella covarianza campionaria (incoerente con $s_x$, $s_y$: il rapporto $r$ verrebbe comunque giusto solo se si è coerenti ovunque).

### **7. Sintesi finale**

| **Indicatore** | **Formula** | **Cosa misura** | **Range** |
|---|---|---|---|
| **CV** | $s/\bar{x}$ | dispersione relativa (adimensionale) | $\geq 0$ |
| **Covarianza** | $\frac{1}{n-1}\sum(x_i-\bar{x})(y_i-\bar{y})$ | co-movimento (segno) | $\mathbb{R}$ |
| **Pearson $r$** | $s_{xy}/(s_x s_y)$ | forza e verso della relazione **lineare** | $[-1, 1]$ |
| **Skewness** | confronto $\bar{x}$ vs mediana ($\gamma_1$ formale) | direzione della coda | $\mathbb{R}$ |
