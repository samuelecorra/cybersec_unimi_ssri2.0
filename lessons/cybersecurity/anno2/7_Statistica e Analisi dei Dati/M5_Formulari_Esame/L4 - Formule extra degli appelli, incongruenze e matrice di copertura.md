# **L4 - Formule extra degli appelli, incongruenze delle fonti e matrice di copertura**

> 📌 Foglio di **audit**, da stampare insieme ai formulari. Risponde a tre domande: (1) quali formule servono all'esame ma **non** sono spiegate nelle videolezioni; (2) dove le fonti si **contraddicono** e quale convenzione adottare; (3) ogni esercizio dei 8 appelli disponibili è **coperto** dai formulari?

---

### **1. Formule comparse negli appelli ma non presenti o non evidenziate nelle lezioni**

#### **1.1. Distribuzione ipergeometrica**

$$P(X=k)=\frac{\binom Kk\binom{N-K}{n-k}}{\binom Nn} \qquad E[X]=n\frac KN \qquad \mathrm{Var}(X)=np(1-p)\frac{N-n}{N-1}$$

- **Significato:** campionamento senza reinserimento da popolazione finita dicotomica.
- **Dove compare:** 15/01/2025 D1, 05/07/2025 D3, 19/09/2025 D1, 16/01/2026 D1 — **4 appelli su 8**.
- **Quando usarla:** parole "senza reinserimento", "lotto di $N$ pezzi di cui $K$ difettosi".
- **Formula simile nelle lezioni:** la **Binomiale** ([M2/UD3/L1](../M2_Variabili_aleatorie/UD3/L1%20-%20Distribuzione%20Binomiale.md)), che ha la stessa media ma varianza **maggiore** (manca il fattore di correzione).
- **Stato:** lacuna già colmata dalla lezione integrativa [M2/UD5/L1](../M2_Variabili_aleatorie/UD5_Approfondimenti_Esame/L1%20-%20Distribuzione%20Ipergeometrica.md). Formulario: [L2 §10](L2%20-%20M2%20Formulario%20-%20Variabili%20aleatorie%20e%20distribuzioni.md).

> ⚠️ È la formula più redditizia da portare: 4 appelli su 8, sempre per 7-8 punti.

#### **1.2. Skewness campionaria di Fisher**

$$g_1=\frac{n}{(n-1)(n-2)}\sum_{i=1}^n\left(\frac{x_i-\bar x}{s}\right)^3$$

- **Significato:** misura numerica dell'asimmetria di un campione.
- **Dove compare:** 23/07/2025 D2d (2 punti), con richiesta esplicita di *"calcolare la skewness campionaria"*.
- **Quando usarla:** se la traccia dice **"si calcoli"**. Se dice solo *"si commenti lo sbilanciamento"* (16/01/2026 D2d) basta il confronto $\bar x$ vs mediana.
- **Formula simile nelle lezioni:** solo il momento centrale teorico di ordine 3, $\gamma_1=E[(X-\mu)^3]/\sigma^3$, citato di passaggio in [M2/UD1/L4 §15](../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md).
- **Stato:** **lacuna residua nelle lezioni** — la formula di Fisher non compare in nessun file di teoria, nemmeno nell'unità integrativa [M3/UD4/L1](../M3_Argomenti_avanzati/UD4_Approfondimenti_Esame/L1%20-%20CV,%20covarianza,%20correlazione%20di%20Pearson%20e%20skewness.md), che dichiara richiesta la sola lettura qualitativa. Formulario: [L3 §10.2](L3%20-%20M3%20Formulario%20-%20Somme,%20campionamento%20e%20inferenza.md), con esempio numerico completo.

#### **1.3. Coefficiente di variazione**

$$CV=\frac{s}{\bar x}$$

- **Dove compare:** 19/09/2025 D4d (2 punti).
- **Formula simile nelle lezioni:** nessuna; le lezioni trattano solo $\sigma$ e IQR come indicatori di ampiezza **assoluti**.
- **Stato:** coperta da [M3/UD4/L1](../M3_Argomenti_avanzati/UD4_Approfondimenti_Esame/L1%20-%20CV,%20covarianza,%20correlazione%20di%20Pearson%20e%20skewness.md). Formulario: L3 §11.1.

#### **1.4. Covarianza e correlazione di Pearson campionarie**

$$s_{xy}=\frac{1}{n-1}\sum(x_i-\bar x)(y_i-\bar y) \qquad r=\frac{s_{xy}}{s_x s_y}$$

- **Dove compare:** 19/09/2025 D4e (3 punti).
- **Formula simile nelle lezioni:** la **covarianza teorica** $\mathrm{Cov}(X,Y)=E[(X-\mu_X)(Y-\mu_Y)]$, introdotta in [M3/UD1/L1](../M3_Argomenti_avanzati/UD1/L1%20-%20Introduzione%20alla%20somma%20di%20variabili%20aleatorie%20indipendenti.md) **solo** per dimostrare $\mathrm{Var}(X+Y)=\sigma_X^2+\sigma_Y^2$; la versione campionaria e $r$ non ci sono.
- **Stato:** coperta da M3/UD4/L1. Formulario: L3 §11.2-11.3.

#### **1.5. Approssimazione normale della binomiale e correzione di continuità**

$$X\approx\mathcal N(np,\ np(1-p)) \quad\text{se}\quad np\ge10 \ \text{ e } \ n(1-p)\ge10; \qquad P(a\le X\le b)\to P(a-0.5<X_{\mathcal N}<b+0.5)$$

- **Dove compare:** 19/09/2025 D2c (4 punti), con richiesta esplicita di verificare le condizioni.
- **Formula simile nelle lezioni:** [M2/UD4/L1](../M2_Variabili_aleatorie/UD4/L1%20-%20Densit%C3%A0%20Gaussiana%20o%20Normale.md) dice che la binomiale "tende a una curva a campana" e usa la regola dei tre sigma, ma **non dà né le condizioni operative né la correzione $\pm0.5$**.
- **Stato:** coperta da [M2/UD5/L2](../M2_Variabili_aleatorie/UD5_Approfondimenti_Esame/L2%20-%20Approssimazione%20Normale%20della%20Binomiale%20e%20correzione%20di%20continuit%C3%A0.md). Formulario: L2 §13.

#### **1.6. Regola dei $3\sigma$ come criterio di individuazione degli outlier**

$$x \text{ è outlier se } x\notin[\bar x-3s,\ \bar x+3s]$$

- **Dove compare:** 12/02/2025 D2d — *"si identifichino eventuali outlier sulla base della regola dei $3\sigma$"*.
- **Formula simile nelle lezioni:** la regola dei tre sigma esiste ([M2/UD4/L1 §10](../M2_Variabili_aleatorie/UD4/L1%20-%20Densit%C3%A0%20Gaussiana%20o%20Normale.md)) ma è presentata come **copertura di probabilità** (99.73%), non come criterio diagnostico su un campione.
- **Stato:** lacuna colmata solo qui. Formulario: L3 §9.4.

#### **1.7. Baffi del boxplot a $1.5\times$ IQR**

$$\text{limiti}=Q_1-1.5\,\text{IQR} \quad\text{e}\quad Q_3+1.5\,\text{IQR}$$

- **Dove compare:** 12/02/2025 D2e (2 punti, *"si disegni il boxplot"*).
- **Formula simile nelle lezioni:** [M3/UD2/L1 §14](../M3_Argomenti_avanzati/UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md) descrive il boxplot ma **non quantifica i baffi** (dice solo "fino ai valori estremi non anomali").
- **Stato:** lacuna colmata solo qui. Formulario: L3 §9.4.

#### **1.8. Intervallo di confidenza con valore critico $z_{\alpha/2}$**

$$IC_{1-\alpha}(p)=\hat p\pm z_{\alpha/2}\sqrt{\frac{\hat p(1-\hat p)}{n}}, \qquad z_{0.025}=1.96$$

- **Dove compare:** 16/06/2025 D3b, 19/09/2025 D3b.
- **Formula nelle lezioni:** diversa — [M3/UD3/L2](../M3_Argomenti_avanzati/UD3/L2%20-%20Stima%20della%20media.md) dà $p_{\text{stima}}=\frac kn\pm\frac{\sqrt k}{n}$ (approssimando $p(1-p)\approx p$) e usa i multipli interi di $\sigma$ per i livelli 68/95/99.7%; il simbolo $z_{\alpha/2}$ e il valore $1.96$ **non compaiono** in nessuna lezione, solo la "legge dei percentili" ($\mu\pm1.96\sigma$) in [M2/UD4/L2 §6](../M2_Variabili_aleatorie/UD4/L2%20-%20Altre%20considerazioni%20sulla%20Gaussiana.md).
- **Stato:** entrambe le versioni nel formulario, con confronto numerico. Formulario: L3 §7.3.

#### **1.9. Terminologia sensitivity / specificity / PPV / NPV**

$$P(T^+\mid M)=\text{sensibilità}\quad P(T^-\mid M^c)=\text{specificità}\quad P(T^+\mid M^c)=1-\text{specificità}$$

- **Dove compare:** 05/07/2025 D5, 05/09/2025 D4, 16/01/2026 D5 — **3 appelli su 8**.
- **Formula simile nelle lezioni:** il calcolo bayesiano c'è ([M1/UD5/L3](../M1_Probabilita_elementare/UD5/L3%20-%20Prior%20e%20Verosimiglianza.md), esempio del test medico), ma **la terminologia inglese non viene mai introdotta**, e non viene spiegato che il falso positivo è il complemento della specificità.
- **Stato:** lacuna colmata solo qui. Formulario: [L0 §5.14](L0%20-%20Guida%20rapida%20d'esame%20e%20tavole%20statistiche.md) e L1 §6.

> ⚠️ È l'errore che azzera più domande: se la traccia dà la **specificità** ($95\%$) va usato $0.05$ in Bayes; se dà già *"produce un falso positivo nel 5% dei casi"* si usa $0.05$ direttamente, senza complementare.

#### **1.10. Formula computazionale della devianza**

$$\sum_i (x_i-\bar x)^2=\sum_i x_i^2-n\bar x^2 \qquad\qquad \sum_i f_i(x_i-\bar x)^2=\sum_i f_i x_i^2-n\bar x^2$$

- **Dove compare:** usata nelle soluzioni di 19/09/2025 D4b e 16/01/2026 D2c per accorciare i calcoli.
- **Formula simile nelle lezioni:** la versione teorica $\sigma^2=\langle x^2\rangle-\langle x\rangle^2$ ([M2/UD1/L4 §12](../M2_Variabili_aleatorie/UD1/L4%20-%20Indicatori%20d%E2%80%99ampiezza.md)); la trasposizione campionaria (con il $n\bar x^2$ da sottrarre **prima** di dividere per $n-1$) non è esplicitata.
- **Stato:** formulario L3 §9.2.

#### **1.11. Quantile generale dell'esponenziale**

$$x_p=-\frac{\ln(1-p)}{\lambda}$$

- **Dove compare:** implicitamente al 16/01/2026 D3c (tempo mediano).
- **Formula simile nelle lezioni:** [M2/UD2/L3 §9-10](../M2_Variabili_aleatorie/UD2/L3%20-%20Caratteristiche%20dell%E2%80%99esponenziale.md) dà la mediana $\frac{\ln2}{\lambda}$ e alcuni percentili **arrotondati** ($t_{80}\approx\frac53\tau$, $t_{90}\approx\frac73\tau$, $t_{95}\approx3\tau$), ma non la formula generale.
- **Nota di precisione:** i valori esatti sono $t_{80}=\tau\ln5=1.609\,\tau$ (la lezione arrotonda a $1.667$) e $t_{90}=\tau\ln10=2.303\,\tau$ (la lezione arrotonda a $2.333$); $t_{95}=\tau\ln20=2.996\,\tau\approx3\tau$ è invece accurato. **All'esame usare la formula esatta.**
- **Stato:** formulario L2 §11.5.

#### **1.12. Probabilità che $n$ individui indipendenti cadano tutti nello stesso intervallo**

$$P(\text{tutti gli } n)=\left[P(a<X<b)\right]^n$$

- **Dove compare:** 05/07/2025 D4c (dieci individui tutti con $92<\text{QI}<108$ ⇒ $0.383^{10}\approx6.8\times10^{-5}$).
- **Formula nelle lezioni:** è semplicemente la legge del prodotto per eventi indipendenti; non è però mai applicata a un evento gaussiano.
- **Trappola:** scrivere $10\,p$ invece di $p^{10}$.
- **Stato:** formulario L2 §12.8 e [L0 §3](L0%20-%20Guida%20rapida%20d'esame%20e%20tavole%20statistiche.md).

#### **1.13. Nomenclatura "biased / unbiased"**

- **Dove compare:** 12/02/2025 D2c — *"si calcoli la deviazione standard e si dica se è biased o unbiased"*.
- **Nelle lezioni:** [M3/UD3/L2 §10](../M3_Argomenti_avanzati/UD3/L2%20-%20Stima%20della%20media.md) menziona che $n-1$ "serve a rendere la stima non distorta", ma dichiara esplicitamente che la spiegazione **esula dagli obiettivi della lezione**; i termini inglesi non compaiono.
- **Risposta pronta:** con $n-1$ (correzione di Bessel) lo stimatore è **unbiased**, cioè $E[s^2]=\sigma^2$; con $n$ è **biased** e sottostima sistematicamente la varianza vera.
- **Stato:** formulario L3 §9.2 e [L0 §5.1](L0%20-%20Guida%20rapida%20d'esame%20e%20tavole%20statistiche.md).

---

### **2. Incongruenze e convenzioni divergenti fra le fonti**

| # | Punto controverso | Fonti in disaccordo | Cosa fare all'esame |
|---|---|---|---|
| 1 | **Denominatore della varianza campionaria** | [M3/UD2/L1 §10,12](../M3_Argomenti_avanzati/UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md) usa $\frac1n$ ($s^2=\frac1n\sum x_j^2-m^2$); **tutti** gli 8 appelli usano $\frac{1}{n-1}$ | **Usare $n-1$** e dichiararlo ("varianza campionaria non distorta") |
| 2 | **Formula dell'IC per una proporzione** | Lezioni ed esercizi del docente: $\frac kn\pm\frac{\sqrt k}{n}$ al 68%. Soluzioni d'appello: $\hat p\pm z_{\alpha/2}\sqrt{\hat p(1-\hat p)/n}$ | Usare la versione con $z_{\alpha/2}$ (chiesta esplicitamente al 95%); menzionare l'equivalenza numerica |
| 3 | **Convenzione per i quartili** | $\lceil q\,n\rceil$ nella soluzione ufficiale del 19/09/2025; metodo delle metà nelle altre soluzioni; $\frac{k(n+1)}{4}$ in [M3/UD2/L1 §13](../M3_Argomenti_avanzati/UD2/L1%20-%20Dalla%20distribuzione%20al%20campione%20%E2%80%93%20Statistica%20descrittiva.md) | Coincidono quasi sempre (divergono solo se $q\,n$ è intero): **dichiarare la convenzione usata** |
| 4 | **Interpretazione dell'intervallo di confidenza** | La soluzione del 16/06/2025 impone la lettura **frequentista** e vieta "95% di probabilità che $p$ sia dentro"; l'esercizio del docente `PRO_M3_U3_L2_E.pdf` scrive testualmente *"con il 68% circa di probabilità il valore vero di $p$ si trova all'interno di questo intervallo"* | Scrivere la formulazione frequentista **e** aggiungere una riga sull'impostazione bayesiana del corso (vedi L3 §7.5) |
| 5 | **Percentili dell'esponenziale** | [M2/UD2/L3 §10](../M2_Variabili_aleatorie/UD2/L3%20-%20Caratteristiche%20dell%E2%80%99esponenziale.md) dà $t_{80}=\frac53\tau$ e $t_{90}=\frac73\tau$ (arrotondamenti); i valori esatti sono $1.609\,\tau$ e $2.303\,\tau$ | Usare $x_p=-\frac{\ln(1-p)}{\lambda}$ |
| 6 | **Quale tavola normale** | Le tre immagini in [M2/UD4/imgs](../M2_Variabili_aleatorie/UD4/) tabulano funzioni **diverse** ($A(z)$, $\Phi(z)$, $1-\Phi(z)$) | Controllare il valore in $z=0$ prima di usarla; le soluzioni d'appello usano $\Phi(z)$ |
| 7 | **Regola dei tre sigma vs percentili** | $\mu\pm2\sigma$ dà il $95.45\%$, ma il $95\%$ esatto è $\mu\pm1.96\sigma$ | Per gli IC usare $1.96$; per stime rapide e outlier va bene il $2\sigma$/$3\sigma$ |
| 8 | **Numerazione dei moduli in tre intestazioni** | [M2/UD2/L1](../M2_Variabili_aleatorie/UD2/L1%20-%20Distribuzione%20Geometrica.md) e [L2](../M2_Variabili_aleatorie/UD2/L2%20-%20Densit%C3%A0%20Esponenziale%20Negativa.md) recavano "**M1** UD2 Lezione..." e [M2/UD3/L3](../M2_Variabili_aleatorie/UD3/L3%20-%20Processi%20Bernoulliani%20e%20Processi%20Poissoniani.md) recava "M2 **UD2** Lezione 3" | Refusi **corretti** in questa sessione; nessun impatto sui contenuti |
| 9 | **Titolo del file di Poisson** | Il file è `L2 - orma analitica...` (manca la "F" iniziale di "Forma"); il titolo interno è corretto | Refuso di nome file, contenuto integro |

> 💡 **Regola generale per gli esercizi ambigui:** dichiarare in una riga la convenzione adottata *prima* di calcolare. Una risposta con metodo esplicito e convenzione dichiarata non viene penalizzata anche se il correttore ne preferisce un'altra.

---

### **3. Argomenti presenti nelle lezioni ma mai comparsi negli 8 appelli**

Restano nel formulario perché il programma li include e potrebbero comparire; sono però a **bassa priorità di ripasso**.

| Argomento | Lezione | Perché tenerlo comunque |
|---|---|---|
| **Distribuzione geometrica** | [M2/UD2/L1](../M2_Variabili_aleatorie/UD2/L1%20-%20Distribuzione%20Geometrica.md) | È la controparte discreta dell'esponenziale (che compare in 3 appelli): un esercizio "quanti tentativi fino al primo successo" è pienamente in programma |
| Funzioni generatrici PGF e MGF | M3/UD1/L3, L5 | Giustificano i casi notevoli di somma; una domanda teorica è possibile |
| Erlang / Gamma | M3/UD1/L2 | È la risposta al caso "componenti in **stand-by**", variante naturale di serie/parallelo già chiesti |
| Merging e splitting dei processi di Poisson | M2/UD3/L3 | Estensione diretta della Poisson |
| Densità di minimo e massimo campionario in forma esplicita | M3/UD2/L2 | Usate implicitamente ogni volta che si risolve serie/parallelo |
| Paradosso del compleanno | M1/UD2/L6 | Applicazione tipica della legge del prodotto per $n$ eventi |
| Simulazione Monte Carlo, stima di $\pi$ | M1/UD4/L1 | Possibile domanda teorica sulla legge dei grandi numeri |
| Momenti di ordine superiore e curtosi | M2/UD1/L4 | Solo la skewness è stata chiesta finora |
| Contrazione della densità $f_M(m)=n f_Z(nm)$ | M3/UD2/L3 | Passaggio interno alla derivazione della media campionaria |
| Teorema di Chebyshev in forma autonoma | M2/UD1/L4 | Chiesto solo dentro la dimostrazione della WLLN (12/02/2025), dove peraltro la traccia lo **forniva** |
| Normalizzazione da pesi/odds | M1/UD1/L3 | Compare negli esercizi del docente (dado truccato), non ancora negli appelli |
| TLC generalizzato, distribuzioni a coda pesante (Cauchy) | M3/UD1/L6 | Rilevante per la domanda teorica sul TLC (15/01/2025), dove va citata la condizione $\sigma^2<\infty$ |

> 💡 **Formule rare che conviene comunque stampare** (basso costo, alto rischio se mancano): skewness di Fisher $g_1$, correzione di continuità, rete a ponte, quantile generale dell'esponenziale, densità di min/max campionario, Erlang, baffi del boxplot a $1.5\,$IQR. Sono tutte già nei formulari.

---

### **4. Matrice di copertura: ogni esercizio degli 8 appelli → sezione del formulario**

Legenda dei formulari: **L0** = guida rapida e tavole, **L1** = M1, **L2** = M2, **L3** = M3.

#### **4.1. Appello 15/01/2025 (33 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | Ipergeometrica $P(X=4)$, $N=20,K=10,n=6$ | L2 §10 |
| D1b | Ipergeometrica condizionata sul sottocampione | L2 §10 + L1 §3 |
| D1c | Impostazione di $P(A\mid B)$ con probabilità totale | L1 §5-6 |
| D2a | Esponenziale $P(X>t)$ da valore atteso | L2 §11.2-11.3 |
| D2b | Sistema parallelo (max) | L1 §7.2, L2 §11.6 |
| D2c | Sistema serie (min) | L1 §7.2, L2 §11.6 |
| D3a-d | Densità $f=cx$: costante, probabilità, mediana, varianza | L2 §3.3-3.4, §4 |
| D4a | Combinatoria: terno al Lotto | L1 §2.2, §2.4 |
| D4b | "Almeno un terno" su 10 combinazioni | L1 §1.2, §2.4 |
| D5 | TLC: enunciato, condizioni, implicazioni | L3 §5 |

#### **4.2. Appello 12/02/2025 (32 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | Dimostrazione $\mathrm{Var}(X+Y)=\sigma_X^2+\sigma_Y^2$ via covarianza | L3 §3.1 |
| D1b | Dimostrazione WLLN con Chebyshev | L1 §8.3-8.4 |
| D2a | Tabella delle frequenze | L3 §8.1 |
| D2b | Media da frequenze | L3 §8.1 |
| D2c | Deviazione standard + biased/unbiased | L3 §9.2, L0 §5.1 |
| D2d | Outlier con la regola dei $3\sigma$ | L3 §9.4 |
| D2e | Boxplot con baffi e outlier | L3 §9.4 |
| D3 | Bayes con tre macchine | L1 §5.3, §6 |
| D4a-c | Carte: combinazioni, "almeno un asso", "cuori o figura" | L1 §1.2, §2.2 |
| D5a | Formula binomiale esatta | L2 §7.2 |
| D5b-c | Approssimazione di Poisson, $P(X=3)$, $P(X\le2)$ | L2 §9.4-9.5 |

#### **4.3. Appello 16/06/2025 (33 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | Rete a ponte: metodo del condizionamento | **L1 §7.5** |
| D1b | Valutazione numerica con $p=0.9$ | L1 §7.5 |
| D1c | Fallibilità con linea centrale degradata | L1 §7.5 |
| D2a-e | Media, mediana, moda, range, dev. std campionaria | L3 §8, §9.2-9.3 |
| D3a | Stima puntuale di una proporzione | L3 §7.3 |
| D3b | IC al 95% per la proporzione | L3 §7.3 |
| D3c | **Interpretazione** dell'intervallo | **L3 §7.5** |
| D4a-b | Convoluzione di due uniformi ⇒ triangolare | L3 §2.3-2.5 |
| D4c | $E[Z]$ per linearità | L3 §1 |
| D5a | Probabilità totale (urne + moneta) | L1 §5 |
| D5b | Bayes | L1 §6 |
| D5c | Giustificazione di $P(U_1\mid R)<P(U_1)$ | L1 §3.3 |

#### **4.4. Appello 05/07/2025 (32 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | Mediana con $n$ pari | L3 §8.2 |
| D1b | Varianza campionaria | L3 §9.2 |
| D1c | Terzo quartile | L3 §8.4 |
| D2a | Costante $C$ di normalizzazione | L2 §3.3 |
| D2b | Cumulativa $F(x)$ a tratti | L2 §3.3 |
| D2c | $P(1<X\le2)$ via cumulativa | L2 §3.3 |
| D3a-b | Ipergeometrica: puntuale e $P(X\ge3)$ | L2 §10.2 |
| D3c-d | $E[X]$ e $\mathrm{Var}(X)$ con correzione | L2 §10.4 |
| D4a-b | Normale: code inferiore e superiore | L2 §12.3 |
| D4c | $p^{10}$ per 10 individui | L2 §12.8, L4 §1.12 |
| D5a | $P(T^+)$ con sensitivity/specificity | L1 §6.4, L0 §5.14 |
| D5b-c | PPV e NPV | L1 §6, L0 §5.14 |

#### **4.5. Appello 23/07/2025 (32 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | Binomiale $P(X=2)$ | L2 §7.2 |
| D1b | $E[X]$ e $\sigma$ della binomiale | L2 §7.3 |
| D1c | $P(X>3)$ per complemento | L2 §7.4 |
| D2a | Mediana | L3 §8.2 |
| D2b | Deviazione standard campionaria | L3 §9.2 |
| D2c | $Q_1$ e $Q_3$ | L3 §8.4 |
| D2d | **Skewness campionaria $g_1$** | **L3 §10.2** |
| D3a-c | Bayes su malattia rara | L1 §6 |
| D4a | Modello di Poisson e significato di $\lambda$ | L2 §9.3 |
| D4b-c | $P(X=3)$, $P(X\le2)$ | L2 §9.4 |
| D5a | Modello normale con parametri | L2 §12.2 |
| D5b | $P(70\le X\le80)$ e perché $P(X=75)=0$ | L2 §12.3, L0 §5.6 |
| D5c | Code | L2 §12.3 |
| D5d | $90^\circ$ percentile | L2 §12.5 |

#### **4.6. Appello 05/09/2025 (33 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | Esponenziale $P(X>12)$ | L2 §11.2 |
| D1b | Tre sensori in serie | L2 §11.6, L1 §7.4 |
| D1c | **Memoryless** con spiegazione | L2 §11.4 |
| D2a | Media e moda da frequenze | L3 §8.1, §8.3 |
| D2b | Varianza e dev. std da frequenze | L3 §9.2 |
| D2c | Mediana e $Q_3$ da frequenze cumulate | L3 §8.4-8.5 |
| D3a | Costante $C$ | L2 §3.3 |
| D3b | Cumulativa | L2 §3.3 |
| D3c | $P(X>1)=1-F(1)$ | L2 §3.3 |
| D4a | Prior | L1 §6.3 |
| D4b | $P(T^+)$ con specificità | L1 §6.4, L0 §5.14 |
| D4c | Posterior + commento | L1 §6.6 |
| D4d | **Bayes iterativo** (secondo test) | **L1 §6.5** |
| D5a | Normale $P(X>120)$ | L2 §12.3 |

#### **4.7. Appello 19/09/2025 (32 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | Ipergeometrica puntuale | L2 §10.2 |
| D1b | $P(1<X<4)$ — estremi esclusi | L2 §10.7, L0 §3 |
| D1c | $E[X]=nK/N$ | L2 §10.4 |
| D2a | Binomiale puntuale | L2 §7.2 |
| D2b | $E[X]$ e $\sigma$ | L2 §7.3 |
| D2c | **Approssimazione normale + correzione di continuità** | **L2 §13** |
| D3a | Bayes con tre monete (prior uniforme) | L1 §6 |
| D3b | Stima puntuale + IC 95% | L3 §7.3 |
| D4a | Media, mediana, moda | L3 §8 |
| D4b | Range, varianza e dev. std campionarie | L3 §9.2 |
| D4c | Quartili e IQR (convenzione $\lceil qn\rceil$) | L3 §8.4 |
| D4d | **Coefficiente di variazione** | **L3 §11.1** |
| D4e | **Correlazione di Pearson** + interpretazione | **L3 §11.3** |

#### **4.8. Appello 16/01/2026 (33 punti)**

| Dom. | Argomento | Copertura |
|---|---|---|
| D1a | **Identificazione giustificata** dell'ipergeometrica | L2 §10.1 |
| D1b | $P(X=2)$ | L2 §10.2 |
| D1c | $P(X\ge1)$ per complemento | L2 §10.6 |
| D1d | $E[X]$ e $\mathrm{Var}(X)$ con correzione | L2 §10.4 |
| D2a | Bar plot | L3 §9.5 |
| D2b | Media, mediana, moda da frequenze | L3 §8.1-8.5 |
| D2c | Varianza e dev. std (formula computazionale) | L3 §9.2 |
| D2d | Skewness qualitativa (media vs mediana) | L3 §10.1 |
| D3a | Esponenziale $P(T>15)$ | L2 §11.2 |
| D3b | Memoryless con cumulativa | L2 §11.4 |
| D3c | Tempo mediano $\tau\ln2$ | L2 §11.5 |
| D3d | Normale $P(X<350)$ | L2 §12.3 |
| D4a | Diagramma ad albero condizionato | L1 §4.2-4.3 |
| D4b | Entrambe cuori (legge del prodotto) | L1 §4.1 |
| D4c | Almeno una (complemento) | L1 §1.2, §4.3 |
| D5a | Bayes con falso positivo dato direttamente | L1 §6.6, L0 §5.14 |

---

### **5. Esito del controllo di completezza**

> ✅ **Tutte le 39 domande (con le rispettive sotto-domande, oltre 100 richieste elementari) degli 8 appelli disponibili risultano coperte da almeno una sezione dei formulari L0-L3.** Non è rimasta alcuna richiesta priva di formula, procedura o esempio di riferimento.

Le uniche integrazioni **non deducibili dalle sole lezioni canoniche**, e quindi introdotte espressamente nei formulari, sono le nove voci del §1 (ipergeometrica, $g_1$ di Fisher, CV, covarianza/Pearson campionarie, approssimazione normale con correzione di continuità, regola dei $3\sigma$ per gli outlier, baffi a $1.5\,$IQR, $z_{\alpha/2}$ negli IC, terminologia sensitivity/specificity), più le tre voci minori §1.10-1.13.

**Materiale da portare in aula:**

1. **L0** — guida rapida, tabelle di riconoscimento, tavola $\Phi(z)$ e valori critici (sostituisce le tavole cartacee).
2. **L1, L2, L3** — i tre formulari.
3. **L4** — questo foglio (convenzioni e trappole).
4. *Opzionale:* la tavola $\Phi$ originale del docente, `M2_Variabili_aleatorie/UD4/imgs/Pasted image 20260705025828.png`, se si preferisce il formato cartaceo originale.

> ⚠️ **Non serve** alcuna tavola $t$ di Student, $\chi^2$ o $F$: non compaiono in nessun appello né in alcuna lezione del corso.
