## **Lezione 14: Flusso di lavoro e ottimizzazione, bilanciamento e partizione dei dataset, valutazione dell’accuratezza**

La qualità di un sistema intelligente non dipende solo dal modello scelto, ma da **come lo si valuta**. La lezione riprende i tre componenti di ogni algoritmo di apprendimento — **rappresentazione**, **valutazione**, **ottimizzazione** — e li applica alle decisioni che più spesso rovinano un progetto: le **classi sbilanciate** (quattro strategie complementari, con SMOTE, dataset condensati e pesatura della perdita), la **partizione dei dati** (bias ottimistico, underfitting e overfitting non rilevati, il dilemma tra training e test, il ruolo distinto di validation e test), gli **schemi di validazione** (k-FCV e la sua versione stratificata, 5×2 CV, leave-one-out, leave-one-person-out, Monte Carlo, validazione delle serie temporali) e infine le **metriche** con cui riportare i risultati di classificatori e regressori. Due regole attraversano tutto: **non progettare mai usando il test set**, e ricordare che **un errore dell’1% può essere buono o cattivo**.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L14-web.pdf` (97 pagine). Molte figure compaiono due volte nel PDF, come immagine e come versione vettoriale ricostruita («VECT»): il segnaposto indica una sola delle due. Alcune grafiche sul bilanciamento sono di Rasmus Rothe.


### **0. Panoramica**


La trattazione si articola in sei temi:

1. **flusso di lavoro: ottimizzazione**;
2. **bilanciamento dei dataset**: classi sbilanciate e quattro modi per gestirle;
3. **partizione dei dataset**: underfitting, overfitting, training / validation / test;
4. **schemi di validazione**: k-FCV, 5×2 CV, LOO, LOPO, Monte Carlo, serie temporali;
5. **valutazione dell’accuratezza**: regressori e classificatori;
6. punti principali.

![](imgs/Pasted%20image%2020261123234513.png)

> 📌 **Molto importante per la qualità del proprio lavoro.** Le scelte di questa lezione decidono se il numero che si riporta alla fine descrive davvero il comportamento del sistema su dati nuovi.

---

### **1. Teoria: richiamo dei concetti principali**


#### **1.1. Il ML: i 3 componenti principali**

Come visto nelle lezioni precedenti (Lezione 4, Domingos), ogni algoritmo di machine learning ha **tre componenti**, collocati in passi precisi del flusso di lavoro:

| Componente | Passi del flusso |
|---|---|
| **rappresentazione** | #3 (il modello) |
| **valutazione** | #3 e #4 (modello e test) |
| **ottimizzazione** | #3 e #5 (modello e miglioramento) |

È un **quadro per capire tutti i sistemi intelligenti**, dai modelli classici di base al deep learning.

<!-- SOURCE_VISUAL id="IS14-V01" source="L14-web.pdf" page="4" type="schema" description="Flusso di lavoro del ML con i passi in cui intervengono rappresentazione, valutazione e ottimizzazione" reason="Schema del flusso visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123234628.png)

#### **1.2. Rappresentazione**

$$Y=\text{function}(X,\ \text{params}),$$

**per un modello generale**. Le famiglie di modelli sono: **reti neurali**, **alberi di decisione**, **insiemi di regole / programmi logici**, **istanze** (come il $k$-NN), **modelli grafici** (reti bayesiane e di Markov), **macchine a vettori di supporto**, **ensemble di modelli**, eccetera. Lo schema vale per un mapping $X\to Y$ continuo o $X\to y$ (sì/no), e vale anche per un «**nuovo incredibile modello**»: per esempio $X$ = temperatura, $Y$ = la previsione.

![](imgs/Pasted%20image%2020261123235148.png)

#### **1.3. Valutazione (1/3): regressori e classificatori**

| **Regressore**: l’uscita è un numero | **Classificatore**: l’uscita è una classe |
|---|---|
| errore $e_i=y_i-\hat{y}_i$, nelle **unità di $y$** (per esempio kWh) | matrice di confusione (positivo = malato) |
| **accuratezza** (*trueness*, giustezza): errore medio vicino a 0 → **nessun bias** (il centro del bersaglio) | **precisione** $=\dfrac{TP}{TP+FP}=\dfrac{8}{48}=0{,}17$: degli allarmi, quanti sono giusti |
| **precisione**: piccola **dispersione** degli errori, per esempio la loro deviazione standard (gruppo compatto) | **recall** $=\dfrac{TP}{TP+FN}=\dfrac{8}{10}=0{,}80$: dei malati, quanti vengono trovati |
| metriche: RMSE, MAE (§6) | **accuratezza** $=\dfrac{TP+TN}{N}=\dfrac{958}{1000}$ |

Nell’esempio del classificatore: **predetti malati 48**, **realmente malati 10**; $TP=8$, $FN=2$, $FP=40$, e $TN=950$ fuori da entrambi gli insiemi.

<!-- SOURCE_VISUAL id="IS14-V02" source="L14-web.pdf" page="6" type="schema" description="Bersagli per giustezza e precisione di un regressore e insiemi dei predetti malati e dei malati veri per un classificatore" reason="Bersagli e insiemi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261123235211.png)

> ⚠️ **Stessa parola, due significati.** La **precisione di un regressore** (misura) è la **dispersione degli errori**, nelle unità di $y$. La **precisione di un classificatore** è $TP/(TP+FP)$, una **frazione adimensionale** di allarmi corretti.

> 💡 **Il bersaglio (nota didattica).** È la stessa distinzione della metrologia: colpi raggruppati ma lontani dal centro sono **precisi ma non giusti** (errore sistematico, *bias*); colpi sparsi attorno al centro sono **giusti ma non precisi** (errore casuale). Un regressore con errore medio di +2 kWh e deviazione standard di 0,1 kWh è precisissimo ma sistematicamente sbagliato: lo si corregge sottraendo 2.

#### **1.4. Valutazione (2/3): i modelli probabilistici**

Un **modello probabilistico** restituisce una probabilità $q(c\mid x)$ per ogni classe $c$, per esempio una stima della probabilità a posteriori

$$P(c\mid x)=\frac{P(x\mid c)\,P(c)}{P(x)}.$$

**Modello migliore = più probabilità su ciò che accade davvero.**

**Log-loss** (cross-entropy):

$$L=-\frac1N\sum_{i=1}^{N}\ln q(y_i\mid x_i),$$

dove $y_i$ è la classe vera del campione $i$, $q(y_i\mid x_i)$ la probabilità assegnatale, $N$ il numero di campioni; l’unità è il **nat**, e **più basso è meglio**.

**Esempio.** Il paziente è malato: con $q=0{,}9$ (modello A) la perdita è $-\ln 0{,}9=0{,}11$; con $0{,}6$ (B) è $0{,}51$; con $0{,}1$ (C) è $2{,}30$. **Gli errori sicuri costano di più.** Con il punteggio di Brier $(q-o)^2$ (dove $o=1$ è l’esito) i valori sono 0,01; 0,16; 0,81.

![](imgs/Pasted%20image%2020261124002055.png)

**Divergenza di Kullback-Leibler**:

$$D_{KL}(P\Vert Q)=\sum_c P(c)\ln\frac{P(c)}{Q(c)}\ge 0,$$

dove $P$ è la distribuzione vera e $Q$ quella del modello; vale **0 solo se $Q=P$** e **non è simmetrica**.

**Esempio** (meteo: sole, nuvoloso, pioggia): $P=(0{,}7;\ 0{,}2;\ 0{,}1)$, $Q_1=(0{,}6;\ 0{,}3;\ 0{,}1)$, $Q_2=(0{,}2;\ 0{,}3;\ 0{,}5)$:

$$D_{KL}(P\Vert Q_1)=0{,}027\ \text{nat (vicina)},\qquad D_{KL}(P\Vert Q_2)=0{,}635\ \text{nat (lontana)}.$$

> 📌 **Minimizzare la cross-entropy equivale a minimizzare $D_{KL}$ dai dati.** Infatti $D_{KL}(P\Vert Q)=H(P,Q)-H(P)$: la cross-entropy $H(P,Q)$ meno l’entropia dei dati $H(P)$, che non dipende dal modello.

> 💡 **I conti (nota didattica).** $D_{KL}(P\Vert Q_1)=0{,}7\ln\frac{0{,}7}{0{,}6}+0{,}2\ln\frac{0{,}2}{0{,}3}+0{,}1\ln 1=0{,}108-0{,}081+0=0{,}027$. Per $Q_2$: $0{,}7\ln 3{,}5+0{,}2\ln\frac23+0{,}1\ln 0{,}2=0{,}877-0{,}081-0{,}161=0{,}635$. Il termine dominante è il primo: $Q_2$ assegna solo 0,2 all’evento che accade il 70% delle volte.

> 💡 **Perché le probabilità contano più delle etichette (nota didattica).** Due modelli possono sbagliare gli stessi esempi, ma uno dà 0,51 alla classe giusta e l’altro 0,99. Il secondo è molto più utile: permette di **cambiare la soglia** in base ai costi (Lezione 11, §6.3), di **astenersi** quando è incerto (la classe «nessuno» della Lezione 5) e di combinare il modello con altre informazioni.

#### **1.5. Valutazione (3/3): entropia, margine, costo**

- **Entropia**: misura la **purezza di un gruppo di campioni** (per esempio un nodo di un albero di decisione): **entropia bassa = quasi una sola classe**.
$$\text{Entropy}(t)=-\sum_{i=1}^{C}p_i\log_2 p_i,$$
con $C$ classi e $p_i$ frazione di campioni della classe $i$ nel nodo $t$ (Lezione 12).
- **Margine**: la **distanza tra la frontiera di decisione e i campioni più vicini**; si preferisce un **margine più ampio** (§1.6).
- **Costo / utilità**: **gli errori non costano tutti uguale**: ogni uscita sbagliata è pesata per il suo costo.
$$\text{costo totale}=\sum_i c(\text{errore}_i),$$
per esempio 1 paziente mancato (FN, 500 €) + 1 falso allarme (FP, 10 €) = **510 €**.

<!-- SOURCE_VISUAL id="IS14-V03" source="L14-web.pdf" page="8" type="figura" description="Entropia di un nodo, margine e vettore degli errori pesati dai costi" reason="Illustrazioni visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124002222.png)

#### **1.6. Valutazione: accuratezza contro margine**

Le frontiere di decisione $H_2$ e $H_3$ hanno **entrambe accuratezza 100%** (errore 0), ma **valori di margine diversi**. Si vuole **aumentare il margine**: **si preferisce $H_3$**. A sinistra $H_1$ commette errori, $H_2$ e $H_3$ no; a destra i campioni cerchiati sono i **più vicini** alla frontiera (**vettori di supporto**).

<!-- SOURCE_VISUAL id="IS14-V04" source="L14-web.pdf" page="9" type="grafico" description="Frontiere H1, H2, H3 tra classe A e classe B e margine di H3 con i vettori di supporto cerchiati" reason="Frontiere e margine visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124002439.png)

> 💡 **Perché il margine conta (nota didattica).** Una frontiera che passa vicinissima a un campione di training classifica correttamente quel campione, ma basta un piccolo rumore su un campione nuovo simile per farlo cadere dall’altra parte. Un margine ampio lascia una «zona cuscinetto» e rende la classificazione più robusta: è il principio delle SVM (*Support Vector Machine*), il cui nome viene proprio dai vettori di supporto che definiscono il margine.

#### **1.7. Ottimizzazione**

| Tipo | Che cosa si ottimizza | Metodo e proprietà |
|---|---|---|
| **(1) combinatoria** | parametri come **scelte discrete** (quale feature, quale divisione) | per esempio **ricerca greedy**: la miglior mossa locale a ogni passo; **veloce**, ma può fermarsi in un **ottimo locale** (gli alberi della Lezione 12) |
| **(2) convessa** | funzioni obiettivo **convesse** | **discesa del gradiente** $w\leftarrow w-\eta\,\nabla J(w)$ ($\eta$: learning rate); **ogni minimo locale è quello globale** |
| **(3) perdite non convesse** | le **reti neurali** | la discesa del gradiente funziona ancora, ma raggiunge un **minimo locale**, che **dipende dal punto di partenza** |
| **(4) vincolata** | per esempio **programmazione lineare**: obiettivo lineare, vincoli lineari | l’ottimo sta su un **vertice della regione ammissibile** |

<!-- SOURCE_VISUAL id="IS14-V05" source="L14-web.pdf" page="10" type="figura" description="Quattro illustrazioni di ottimizzazione: combinatoria, convessa, non convessa, vincolata con regione ammissibile" reason="Illustrazioni visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124002504.png)

> 💡 **Perché l’ottimo lineare sta su un vertice (nota didattica).** Una funzione lineare cresce sempre nella stessa direzione: spostandosi lungo quella direzione all’interno della regione ammissibile (un poligono, o poliedro) si arriva per forza al bordo, e poi lungo il bordo fino a un angolo. Per questo l’algoritmo del simplesso esplora solo i vertici.

#### **1.8. Notazione: regressione e classificazione**

$$Y=\text{function}(X,\ \text{params}),$$

con $Y$ **vettore delle uscite**, $X$ **vettore degli input** (feature) e params **vettore dei parametri appresi** in addestramento.

| | **Regressione**: $y$ esplicita | **Classificazione**: $y$ implicita |
|---|---|---|
| che cosa restituisce il modello | il **valore di $y$** stesso (per esempio kWh, °C): una **superficie** sopra le feature $x_1,x_2$ | una **classe** ($y=0$ buono, $y=1$ cattivo) |
| che cosa si vede | $y=f(x_1,x_2)$ come altezza | solo la **frontiera** dove la decisione cambia |

![](imgs/Pasted%20image%2020261124002517.png)


<!-- SOURCE_VISUAL id="IS14-V06" source="L14-web.pdf" page="12" type="grafico" description="Regressione come superficie y = f(x1, x2) e classificazione come frontiera tra punti buoni e cattivi" reason="Grafici visibili solo nella figura (la p. 11 ne è la versione raster)" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124002700.png)

**Visualizzare un regressore.** $y=\text{function}(X,\text{params})$, con $y$ singolo valore e $X=(x_1,x_2)$ vettore di feature, un asse per feature.

![](imgs/Pasted%20image%2020261124002712.png)


<!-- SOURCE_VISUAL id="IS14-V07" source="L14-web.pdf" page="13" type="grafico" description="Superficie di un regressore sopra il piano delle due feature (la p. 14 ne è la versione vettoriale)" reason="Superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124002732.png)

**Da 3D a 1D.** Servono **feature engineering** e le **migliori tecniche di visualizzazione** per ottenere intuizioni utili e **percepire il comportamento** del modello (Lezione 11).

<!-- SOURCE_VISUAL id="IS14-V08" source="L14-web.pdf" page="15" type="grafico" description="Riduzione della visualizzazione di un regressore da 3D a 1D" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124002803.png)

**Visualizzare un classificatore.** $y\in\{0,1\}$: buono ($y=0$) o cattivo ($y=1$); la **frontiera è dove cambia la decisione**.

![](imgs/Pasted%20image%2020261124002822.png)


<!-- SOURCE_VISUAL id="IS14-V09" source="L14-web.pdf" page="16" type="grafico" description="Classificatore nel piano delle feature con punti buoni e cattivi e frontiera di decisione (la p. 17 ne è la versione vettoriale)" reason="Punti e frontiera visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124002924.png)

**Esempio con riduzione delle feature.** Anche per un classificatore, con molte feature, servono riduzione e buone tecniche di visualizzazione.

<!-- SOURCE_VISUAL id="IS14-V10" source="L14-web.pdf" page="18" type="grafico" description="Visualizzazione di un classificatore dopo riduzione delle feature" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124003252.png)

---

### **2. Teoria: il bilanciamento dei dataset**


Come gestire distribuzioni sbilanciate delle classi.

#### **2.1. Dataset accademici e dataset reali**

**Nel mondo accademico** i dataset sono per lo più **bilanciati**: nei problemi di classificazione supervisionata ci sono di solito **tanti campioni per classe**. **MNIST**, il celebre dataset di cifre scritte a mano, contiene circa lo stesso numero di campioni per ogni cifra; **Food-101** contiene **esattamente 1000 immagini** per ciascuna delle **101 categorie** di cibo.

<!-- SOURCE_VISUAL id="IS14-V11" source="L14-web.pdf" page="20" type="grafico" description="Distribuzioni bilanciate delle classi in MNIST e Food-101" reason="Istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124003319.png)

**Molti dataset reali sono invece sbilanciati.** Esempi:

- **visione medica**: la maggior parte dei pazienti è **sana**, solo una piccola frazione soffre di una certa malattia;
- **credit scoring**: la maggior parte dei clienti **restituisce il prestito**, solo l’**1–2%** è insolvente.

<!-- SOURCE_VISUAL id="IS14-V12" source="L14-web.pdf" page="21" type="grafico" description="Distribuzioni sbilanciate delle etichette in visione medica e credit scoring" reason="Istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124003457.png)

> ⚠️ **L’accuratezza inganna con classi sbilanciate (nota didattica).** Con il 2% di insolventi, un classificatore che risponde **sempre** «restituirà il prestito» ha accuratezza del **98%** e non trova **nessun** insolvente. È il motivo della regola «**un errore dell’1% può essere buono o cattivo**» (§6.1).

#### **2.2. Gestire lo sbilanciamento: quattro strategie complementari**

Ogni strategia agisce su un **livello diverso** della pipeline di ML (dati, modello, addestramento):

| # | Strategia | Che cosa fa |
|---|---|---|
| **1** | **più dati** | raccogliere altri campioni per le classi **sotto-rappresentate** |
| **2** | **cambiare l’etichettatura** | **ridefinire, unire o organizzare gerarchicamente** le classi |
| **3** | **campionamento** | modificare la distribuzione delle classi con **sovracampionamento** o **sottocampionamento** |
| **4** | **pesatura della perdita** | **penalizzare di più** gli errori sulle classi rare durante l’addestramento |

Le strategie si possono applicare **da sole o combinate** nei casi d’uso industriali, di supply chain e ambientali.

#### **2.3. Il costo dell’errore non è uniforme**

A differenza di molti dataset accademici, in molti dataset reali sbilanciati **non è corretto usare lo stesso costo di errore per ogni classe**: non riconoscere un malato costa molto più che richiamare un sano per un secondo esame.

<!-- SOURCE_VISUAL id="IS14-V13" source="L14-web.pdf" page="23" type="schema" description="Costi di errore diversi per le classi di un dataset sbilanciato" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004301.png)

#### **2.4. Soluzione 1: più dati!**

- Bisogna **raccogliere più dati o immagini** per le **classi rare**.
- **Controllare le distribuzioni** (**feature ed etichette**!) nel training e in validation/test.

<!-- SOURCE_VISUAL id="IS14-V14" source="L14-web.pdf" page="24" type="grafico" description="Raccolta di più dati per le classi rare" reason="Distribuzione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004346.png)

#### **2.5. Soluzione 2: cambiare l’etichettatura**

- **Raggruppare e unire** i casi rari.
- ***Divide et impera!*** → **classificatori gerarchici**: un primo classificatore separa la **classe rara unita** dalla **classe frequente**; altri classificatori lavorano poi dentro il gruppo raro.

<!-- SOURCE_VISUAL id="IS14-V15" source="L14-web.pdf" page="25" type="schema" description="Classificatore gerarchico: un primo classificatore separa la classe rara unita da quella frequente" reason="Gerarchia visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004410.png)

> 💡 **Il prezzo della fusione (nota didattica).** Unire tre malattie rare in «patologico» rende la classe abbastanza numerosa da essere imparata, ma il primo classificatore non dice più *quale* malattia: si perde granularità (§2.10). Il secondo livello recupera il dettaglio, ma lavora con pochi esempi.

#### **2.6. Soluzione 3: il campionamento**

- Il **campionamento uniforme semplice mantiene lo sbilanciamento**: la classe rara resta rara in ogni batch.
- Il **sottocampionamento** rimuove (o salta) campioni della classe **maggioritaria** → **perdita di informazione**.
- Il **sovracampionamento** replica (o sintetizza, per esempio con **SMOTE**) campioni della classe **minoritaria** → **rischio di overfitting sulle copie**.
- Estrarre un **nuovo sottoinsieme della maggioritaria a ogni epoca** permette al modello di **vedere tutti i campioni** durante l’addestramento; un sottocampionamento **una tantum** li scarta per sempre.
- Applicare il campionamento **solo ai dati di training, dopo la divisione** (§2.11).

<!-- SOURCE_VISUAL id="IS14-V16" source="L14-web.pdf" page="26" type="grafico" description="Distribuzioni delle classi con campionamento uniforme, sottocampionamento e sovracampionamento" reason="Distribuzioni prima e dopo visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004429.png)

#### **2.7. SMOTE**

> 📌 **SMOTE** (*Synthetic Minority Over-sampling Technique*, tecnica di sovracampionamento sintetico della minoranza). È una tecnica di campionamento a livello di dati che affronta lo sbilanciamento **generando campioni sintetici** della classe minoritaria, **invece di duplicare** quelli esistenti. Per ogni campione minoritario $x$:
> 1. si trovano i suoi **$k$ vicini più prossimi della classe minoritaria**;
> 2. si genera un **nuovo campione sul segmento** tra $x$ e uno dei vicini.
>
> Così **aumenta la densità** della classe minoritaria nello spazio delle feature.

<!-- SOURCE_VISUAL id="IS14-V17" source="L14-web.pdf" page="27" type="grafico" description="SMOTE: campioni sintetici generati sul segmento tra un campione minoritario e i suoi vicini" reason="Punti sintetici visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004454.png)

> 💡 **In formula (nota didattica).** Con $x_{nn}$ uno dei $k$ vicini scelto a caso e $\lambda$ estratto uniformemente in $[0,1]$:
> $$x_{\text{nuovo}}=x+\lambda\,(x_{nn}-x).$$
> Con $x=(1,2)$, $x_{nn}=(3,4)$ e $\lambda=0{,}25$ si ottiene $(1{,}5;\ 2{,}5)$, un punto a un quarto del segmento.

> ⚠️ **Limiti di SMOTE (nota didattica).** I campioni sintetici stanno sempre **tra** campioni esistenti: non creano varietà nuova, e se un vicino è rumoroso o si trova vicino all’altra classe si generano punti nella regione sbagliata. Inoltre l’interpolazione presuppone feature numeriche continue: interpolare un codice categorico non ha senso. Va applicato **solo al training set** (§2.11).

#### **2.8. Sottocampionamento con dataset condensato**

Il sottocampionamento con **dataset condensato** riduce lo sbilanciamento **rimuovendo i campioni ridondanti della classe maggioritaria** e conservando i più **informativi**. La classe maggioritaria contiene spesso molti campioni ridondanti; **sono critici i campioni vicini alla frontiera di decisione**.

- **Sottocampionamento casuale**: i campioni maggioritari vengono eliminati **a caso** (semplice, ma può eliminare campioni della frontiera).
- **Dataset condensato**: una **selezione informata**, per esempio il ***Condensed Nearest Neighbour* di Hart**, conserva **tutti i campioni minoritari** e **solo i campioni maggioritari necessari** a classificare correttamente gli altri.

<!-- SOURCE_VISUAL id="IS14-V18" source="L14-web.pdf" page="28" type="grafico" description="Sottocampionamento casuale contro dataset condensato che conserva i campioni maggioritari vicini alla frontiera" reason="Punti conservati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004544.png)

Molti campioni vengono rimossi, ma **i vicinati locali che definiscono la frontiera del $k$-NN sono in gran parte conservati**. Una selezione informata dei campioni maggioritari può **preservare approssimativamente la frontiera di decisione**, a seconda del metodo di selezione e del classificatore. Confrontando le regioni corrispondenti, **piccole aree cambiano**.

<!-- SOURCE_VISUAL id="IS14-V19" source="L14-web.pdf" page="29" type="grafico" description="Frontiera k-NN prima e dopo la condensazione del dataset, con le piccole aree che cambiano" reason="Frontiere a confronto visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004603.png)

> 💡 **L’algoritmo di Hart (1968) (nota didattica).** Si parte da un insieme con tutti i minoritari e un maggioritario; si scorrono i campioni maggioritari e se ne aggiunge uno all’insieme **solo se il classificatore 1-NN costruito sull’insieme corrente lo classifica male**. I campioni «facili», lontani dalla frontiera, sono già classificati bene dai loro vicini e non vengono aggiunti. Si ripete finché non si aggiunge più nulla.

#### **2.9. Soluzione 4: la pesatura della perdita**

Si **aumenta il peso della perdita** dei campioni delle classi rare:

$$L=\frac1N\sum_{i=1}^{N}w_{y_i}\,\ell(\hat{y}_i,y_i),\qquad w_c=\frac{N}{C\,N_c}.$$

$N$: campioni; $y_i$: classe del campione $i$; $\hat{y}_i$: uscita del modello; $\ell$: perdita per campione (per esempio cross-entropy); $C$: numero di classi; $N_c$: campioni della classe $c$; $w_c$: peso della classe $c$ (adimensionale).

**Esempio**: $N=1000$, $C=2$, 990 sani, 10 malati:

$$w_{\text{sano}}=\frac{1000}{2\cdot 990}\approx 0{,}505,\qquad w_{\text{malato}}=\frac{1000}{2\cdot 10}=50,\qquad \frac{w_{\text{malato}}}{w_{\text{sano}}}=\frac{N_{\text{sano}}}{N_{\text{malato}}}=\frac{990}{10}=99.$$

Un campione malato riceve **circa 99 volte** il peso della perdita di uno sano.

<!-- SOURCE_VISUAL id="IS14-V20" source="L14-web.pdf" page="30" type="figura" description="Pesatura della perdita per le classi rare" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004640.png)

> ⚠️ **Frequenze, non costi.** Questi pesi riflettono le **frequenze delle classi**, **non i costi reali** degli errori: se un paziente mancato costa davvero di più, si fissano i $w_c$ **dalla matrice dei costi** (§1.5).

> 💡 **Perché quei pesi «bilanciano» (nota didattica).** Con $w_c=N/(C\,N_c)$, il peso totale di ogni classe è $w_c\cdot N_c=N/C$: ogni classe contribuisce alla perdita con la **stessa massa complessiva** (qui 500 per i sani e 500 per i malati), come se il dataset fosse bilanciato. Pesi così grandi rendono però molto rumoroso il gradiente dei pochi campioni rari: è il rischio di **instabilità** della tabella seguente.

#### **2.10. Lo sbilanciamento e i rischi relativi**

Le quattro strategie offrono modi complementari di affrontare lo sbilanciamento, ciascuno con **compromessi e rischi** diversi. In pratica **si combinano spesso**, in base a disponibilità dei dati, vincoli applicativi e stabilità dell’apprendimento.

| # | Strategia | Agisce su | Rischio tipico |
|---|---|---|---|
| 1 | più dati | dataset | **costo / fattibilità** |
| 2 | cambiare l’etichettatura | definizione del problema | **perdita di granularità** |
| 3 | campionamento | dataset | **overfitting / perdita di informazione** |
| 4 | pesatura della perdita | modello | **instabilità dell’addestramento** |

#### **2.11. Ordine delle operazioni: prima dividere, poi bilanciare**

La pipeline corretta è:

1. **dataset** $D$ con $N=1000$ (990 sani / 10 malati);
2. **divisione stratificata**: **training 800** (792 / 8), **test 200** (198 / 2), che resta **intatto**;
3. **bilanciamento del solo training** (ricampionamento, SMOTE o pesi della perdita), per esempio fino a 792 / 792;
4. **addestramento del modello** (con validazione incrociata per le scelte);
5. **valutazione una sola volta** sul test, con metriche come recall e F1.

<!-- SOURCE_VISUAL id="IS14-V21" source="L14-web.pdf" page="32" type="schema" description="Pipeline: divisione stratificata 800/200, bilanciamento del solo training, addestramento, valutazione unica sul test" reason="Schema a blocchi della pipeline" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124004804.png)

- **Prima si partiziona il dataset.** Ricampionamento e bilanciamento delle classi si applicano **solo ai dati di training**. Validation e test restano **indipendenti** e **rappresentativi** dell’applicazione reale (**proporzioni reali delle classi**).
- **Ordine sbagliato**: sovracampionamento o SMOTE **prima della divisione** mettono nel training **copie, o interpolazioni, dei campioni di test** → **data leakage** → **stima ottimistica**.
- Con la **validazione incrociata**, il bilanciamento si **ripete dentro ogni fold**, sulla sua sola parte di training.

> 💡 **Perché il test deve restare sbilanciato (nota didattica).** In produzione il sistema vedrà circa l’1% di malati. Un test bilanciato artificialmente al 50% darebbe una precisione molto più alta di quella reale: con lo stesso recall e lo stesso tasso di falsi allarmi, i falsi positivi sarebbero calcolati su 100 sani invece che su 9900. La precisione dipende dalla prevalenza, e il test deve avere quella vera.

---

### **3. Teoria: la partizione del dataset**


Come addestrare e testare i modelli in modo efficace.

#### **3.1. Il dataset di coppie note $X\to Y$**

- I **dataset di benchmark** si usano con un obiettivo: **valutare le prestazioni** di un modello su un insieme di **problemi standard ben noti**, usando dataset di coppie **note** $X\to Y$.
- I dati però vanno **usati correttamente**, per **evitare bias** nei risultati.

| Qualità del sonno | Stress | Peso (kg) | Emicrania |
|---|---|---:|---|
| scarsa | sì | 97 | sì |
| buona | no | 78 | no |
| scarsa | no | 47 | sì |
| … | … | … | … |
| moderata | no | 78 | sì |

![](imgs/Pasted%20image%2020261124005004.png)

**Una riga = un campione** (un paziente). Le coppie note $X\to Y$ servono ad **addestrare e testare** il modello.

#### **3.2. Partizione sbagliata 1: il bias ottimistico**

> 📌 **Bias ottimistico.** Si verifica quando la **valutazione è fatta su dati che hanno influenzato il progetto o la scelta del modello**, portando a una stima **troppo ottimistica** del vero errore di generalizzazione. Se si usa **l’intero dataset sia per costruire sia per validare** il modello, **non si ha idea** di come si comporterà con **casi nuovi**, mai visti. Questa stima è il tipico bias ottimistico (fonte: Springer, *Data Preprocessing in Data Mining*).

<!-- SOURCE_VISUAL id="IS14-V22" source="L14-web.pdf" page="35" type="figura" description="Bias ottimistico quando lo stesso dataset costruisce e valida il modello" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005014.png)

#### **3.3. Partizione sbagliata 2: underfitting e overfitting non rilevati**

Usando gli stessi dati per addestrare e valutare possono sorgere due problemi principali, **senza che ce ne si accorga**:

- **underfitting**: il modello è **poco adattato** ai dati, con **errore alto** sia sul training sia sui dati nuovi;
- **overfitting**: il modello è **troppo adattato** ai dati, con **alta precisione sui casi noti** ma **cattivo comportamento** sui dati nuovi.

> 📌 **Nota.** Un metodo di partizione corretto **non risolve automaticamente** questi problemi, ma **aiuta a rilevarli** e a **decidere come intervenire**.

<!-- SOURCE_VISUAL id="IS14-V23" source="L14-web.pdf" page="36" type="figura" description="Underfitting e overfitting non rilevati usando gli stessi dati" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005024.png)

#### **3.4. Dataset e partizioni: che cosa si può e non si può fare**

- L’obiettivo finale di qualunque sistema intelligente è essere **applicato a problemi reali**.
- **Provare una tecnica su ogni problema è impossibile!**
- La procedura comune è **valutarla su un insieme di problemi o dataset standard** disponibili pubblicamente.

<!-- SOURCE_VISUAL id="IS14-V24" source="L14-web.pdf" page="37" type="figura" description="Loghi di dataset e repository pubblici di benchmark" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005034.png)

#### **3.5. Partizione del dataset: un avvertimento critico!**

Per:

- **controllare** le prestazioni del modello,
- **evitare l’overfitting**,
- avere una **stima generalizzabile** della qualità del modello ottenuto,

in letteratura esistono **diversi schemi di partizione**. **Come partizionare i dati è una questione chiave**, perché **influenzerà molto le prestazioni** dei metodi. **Una partizione sbagliata porta sicuramente** a un comportamento **incompleto e/o distorto** del modello valutato.

<!-- SOURCE_VISUAL id="IS14-V25" source="L14-web.pdf" page="38" type="figura" description="Avvertimento sulla partizione dei dati" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V26" source="L14-web.pdf" page="39" type="figura" description="Slide di passaggio: concentrarsi ora su..." reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005101.png)

#### **3.6. E se… troppi parametri [richiamo]**

Confronto tra un modello con **#Par eccessivo** e uno con **#Par corretto** dopo l’addestramento: il metodo di apprendimento **non è in grado di gestire tutti gli elementi o parametri** inseriti nel modello con il dataset dato! Il modello ottenuto **non è ottimale**: alcune sue parti sono **inutili**, o si ottiene persino un **cattivo comportamento** (Lezione 7: gradi di libertà contro dati).

<!-- SOURCE_VISUAL id="IS14-V27" source="L14-web.pdf" page="40" type="figura" description="Modello con parametri eccessivi contro modello con parametri corretti dopo l'addestramento" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005149.png)

#### **3.7. L’overfitting in 1D e in 2D**

**Overfitting 1D** (modello di regressione): una curva che passa per tutti i punti oscillando. **Overfitting 2D** (modello di classificazione): una frontiera tortuosa che circonda ogni punto.

![](imgs/Pasted%20image%2020261124005201.png)
![](imgs/Pasted%20image%2020261124005514.png)


<!-- SOURCE_VISUAL id="IS14-V28" source="L14-web.pdf" page="41" type="grafico" description="Overfitting in 1D con un modello di regressione" reason="Curva e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V29" source="L14-web.pdf" page="42" type="grafico" description="Overfitting in 2D con un modello di classificazione" reason="Frontiera e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V30" source="L14-web.pdf" page="43" type="grafico" description="Overfitting in 2D, secondo esempio" reason="Frontiera e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005444.png)

#### **3.8. Partizione del dataset: tre casi**

| | **1. Underfitting** | **2. Mancanza di dati** | **3. Overfitting** |
|---|---|---|---|
| **che cosa accade** | il modello è **troppo semplice**: errore alto **anche sui dati di training** | **pochi campioni**: il problema **sembra più semplice** di quanto sia, e il modello va in underfitting rispetto alla tendenza reale | adattare troppo il modello: segue il «**rumore nel segnale**», errore di training **vicino a zero** |
| **visibilità** | **visibile usando tutti i dati** (errore di training) | **parzialmente visibile**: servono **più dati** | **invisibile senza casi nuovi** |

<!-- SOURCE_VISUAL id="IS14-V31" source="L14-web.pdf" page="44" type="grafico" description="Tre grafici: underfitting, mancanza di dati, overfitting" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005533.png)

> 📌 **L’errore di training rivela l’underfitting; solo i dati non usati per l’addestramento (validation/test) rivelano l’overfitting.**

#### **3.9. Spazio di input ad alta dimensionalità**

- Lo spazio di input **non si può disegnare facilmente**: si **studiano gli errori** per rilevare l’overfitting!
- Nell’esperimento si **ripete l’addestramento aumentando il #DoF** dei modelli (per esempio aggiungendo neuroni a una rete).
- L’asse orizzontale **non è il tempo né le epoche**.

<!-- SOURCE_VISUAL id="IS14-V32" source="L14-web.pdf" page="45" type="grafico" description="Errore di training e di test al crescere dei gradi di libertà del modello" reason="Andamento delle curve visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005741.png)

> 📌 **Le due curve (nota didattica).** Al crescere dei gradi di libertà l’**errore di training** scende quasi sempre, mentre l’**errore di test** prima scende e poi **risale**: a sinistra del minimo dell’errore di test il modello è in **underfitting** (entrambi gli errori alti), a destra in **overfitting** (training basso, test in crescita); la complessità giusta è vicino al minimo dell’errore di test. Senza un insieme separato si vedrebbe solo la curva di training, che suggerisce sempre di aumentare la complessità.

---

### **4. Training, validation e test**


#### **4.1. Dividere i dati in training e test**

**È un problema e una decisione molto difficili.** La slide lo illustra con il **giudizio di Salomone**: il re deve decidere tra due donne che si dicono madri dello stesso bambino, e ordina di tagliarlo a metà. Anche il dataset va «tagliato», e ogni parte serve a uno scopo diverso.

<!-- SOURCE_VISUAL id="IS14-V33" source="L14-web.pdf" page="46" type="figura" description="Il giudizio di Salomone come metafora della divisione tra training e test" reason="Illustrazione visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005750.png)

**Il dilemma training/test.** Il modello $Y=\text{function}(X,\text{params})$ si addestra sui dati di training e si valuta sui dati di test, che devono essere **materiale nuovo**. Con poco test ci si chiede: il risultato è **fortunato**? C’è **vera generalità**? C’è **confidenza statistica**? Con poco training ci si chiede: ci sono **abbastanza dati per imparare tutti i casi**? **Sto davvero sfruttando il mio dataset?**

![](imgs/Pasted%20image%2020261124005817.png)


<!-- SOURCE_VISUAL id="IS14-V34" source="L14-web.pdf" page="47" type="figura" description="Il dilemma: molti dati di training e pochi di test (risultato fortunato?)" reason="Illustrazione visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V35" source="L14-web.pdf" page="48" type="figura" description="Il dilemma: molti dati di test e pochi di training (abbastanza dati per imparare?)" reason="Illustrazione visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124005834.png)

#### **4.2. Due casi**

| | **Molto training, poco test** | **Molto test, poco training** |
|---|---|---|
| **apprendimento** | **buono** | **scarso**: rischio di **underfitting** e di **pattern mancati** |
| **valutazione** | stime **poco affidabili**, rischio di risultati «**fortunati**» | **affidabile** |

![](imgs/Pasted%20image%2020261124005936.png)

> 📌 **Il dilemma centrale**: **accuratezza dell’apprendimento** contro **affidabilità della valutazione**; **efficienza nell’uso dei dati** contro **confidenza statistica**.

**In numeri.** L’intervallo al 95% di un’accuratezza misurata è

$$\hat{a}\pm 1{,}96\sqrt{\frac{\hat{a}(1-\hat{a})}{n}},$$

dove $\hat{a}$ è l’accuratezza misurata sul test (adimensionale, 0–1), $n$ il numero di campioni di test e 1,96 il quantile della normale per il 95%.

| Divisione | Test | Risultato |
|---|---:|---|
| **A**: 900 / 100 | $n=100$ | $\hat{a}=0{,}90\pm 0{,}059$ |
| **B**: 300 / 700 | $n=700$ | $\hat{a}=0{,}90\pm 0{,}022$ |

**Esempio di calcolo**: $\hat{a}=0{,}90$, $n=100$ → $1{,}96\sqrt{0{,}09/100}=1{,}96\cdot 0{,}03=0{,}059$.

<!-- SOURCE_VISUAL id="IS14-V36" source="L14-web.pdf" page="50" type="grafico" description="Divisioni 900/100 e 300/700 con gli intervalli al 95% dell'accuratezza (la p. 49 ne è la versione raster)" reason="Barre e intervalli visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124010043.png)

> 💡 **Leggere l’intervallo (nota didattica).** Con 100 campioni di test, un modello che misura 90% potrebbe avere un’accuratezza vera tra circa 84% e 96%: due modelli che misurano 88% e 91% sullo stesso test piccolo non sono distinguibili. L’ampiezza scala come $1/\sqrt{n}$: per dimezzarla servono **quattro volte** più campioni di test. La formula è l’approssimazione normale della binomiale, valida quando $n\hat{a}$ e $n(1-\hat{a})$ non sono troppo piccoli.

#### **4.3. Training / validation / test**

> 📌 **Tre insiemi, tre ruoli.**
> - **Training**: **imparare** i parametri del modello.
> - **Validation**: **regolare gli iperparametri** e **scegliere** il modello.
> - **Test**: **stimare la generalizzazione**.
>
> **Si addestra per imparare, si valida per scegliere, si testa per valutare.**

![](imgs/Pasted%20image%2020261124010107.png)

Esempio con $N=1000$:

| Schema | Divisione |
|---|---|
| 2 insiemi | 800 / 200 |
| 3 insiemi | 600 / 200 / 200 (dati di sviluppo per il progetto + test finale) |

Le percentuali (60/20/20) sono **solo un esempio**: dipendono da $N$ e dalla **classe più rara**.

<!-- SOURCE_VISUAL id="IS14-V37" source="L14-web.pdf" page="52" type="schema" description="Divisione di 1000 campioni in 2 insiemi (800/200) e 3 insiemi (600/200/200) (la p. 51 ne è la versione raster)" reason="Barre delle divisioni visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124010117.png)

#### **4.4. La validation appartiene ai dati di sviluppo**

L’insieme di validation **appartiene ai dati di sviluppo del modello**, ma è **tenuto separato** dai dati usati per adattare il modello in ogni esperimento: *fit* (imparare i parametri), *valid.* (scegliere la configurazione), *test* (stimare **una volta**).

La validation si usa per:

| Uso | Esempi |
|---|---|
| **regolazione degli iperparametri** | learning rate; profondità e larghezza; forza della regolarizzazione (L2, dropout); dimensione del batch |
| **scelta del modello** | confrontare architetture e configurazioni |
| **early stopping** | fermarsi quando la perdita di validazione **smette di migliorare** |
| **diagnostica dell’addestramento** | underfitting contro overfitting |

<!-- SOURCE_VISUAL id="IS14-V38" source="L14-web.pdf" page="53" type="schema" description="Dati di sviluppo divisi in fit e validation, più il test usato una volta" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124010152.png)

> ⚠️ **Anche la validation è ottimistica.** Se si usa **l’intero dataset per la validazione**, si ottiene una stima affetta da **bias ottimistico**. Anche il **punteggio di validazione del modello scelto è ottimistico** (si è scelto il migliore): **si riporta il punteggio di test**.

> 💡 **Perché il migliore è ottimistico (nota didattica).** Se si provano 20 configurazioni equivalenti, ciascuna con accuratezza vera 85% ma misurata con un errore casuale di ±2%, la migliore misurerà per caso circa 88–89%. Scegliere il massimo seleziona anche la fortuna: il valore vero della configurazione scelta resta 85%, ed è il test, mai usato per scegliere, a rivelarlo.

---

### **5. Teoria: topologia, potatura, regolarizzazione — dove applicarle**


**Un concetto fondamentale.**

#### **5.1. Tutte le «decisioni» si prendono solo con il training set**

Il modello $Y=\text{function}(X,\text{params})$ si costruisce sui dati di training; il test set si usa **SOLO per l’errore di generalizzazione**: **NESSUNA ATTIVITÀ DI PROGETTO** su di esso, deve restare **materiale nuovo**!

**Attività di progetto** (da fare sul «vero» training, cioè i dati di sviluppo):

- **numero di neuroni**;
- **numero di strati**;
- **regolarizzazione**;
- **potatura** (*pruning*);
- **normalizzazione**;
- **PCA**;
- **feature engineering**;
- eccetera.

<!-- SOURCE_VISUAL id="IS14-V39" source="L14-web.pdf" page="55" type="schema" description="Tutte le attività di progetto sui dati di training, il test usato solo per l'errore di generalizzazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124010630.png)

#### **5.2. Ogni passo che impara dai dati si adatta solo sul training**

I passi che **imparano qualcosa dai dati** fanno **parte del modello**:

- **normalizzazione** (media, deviazione standard), **imputazione** dei valori mancanti;
- **PCA** (componenti principali), **selezione delle feature**;
- **ricampionamento**, **SMOTE**, **pesi delle classi**.

> 📌 **Regola.** Si **adatta** (*fit*) sui dati di training, poi si **applica la stessa trasformazione** (stessi parametri) a validation e test. Nella validazione incrociata la si **ripete dentro ogni fold** (una *pipeline*).

**Esempio** (z-score): $x'=(x-\mu_{\text{train}})/\sigma_{\text{train}}$, con $x$ valore grezzo (°C), $\mu_{\text{train}}$ media dei valori di training (°C), $\sigma_{\text{train}}$ loro deviazione standard (°C) e $x'$ valore normalizzato (adimensionale). Con $\mu_{\text{train}}=20$ °C, $\sigma_{\text{train}}=5$ °C e un campione di test $x=30$ °C: $x'=(30-20)/5=2$.

> ⚠️ **Sbagliato**: calcolare $\mu$, $\sigma$ (o la PCA, o SMOTE) **sull’intero dataset** fa **trapelare informazione del test** nel modello.

![](imgs/Pasted%20image%2020261124010653.png)

> 💡 **Esempi di violazione (nota didattica).** Calcolare lo z-score su tutto il dataset; scegliere le feature con la PCA calcolata su tutti i dati; provare dieci architetture e tenere quella con il miglior risultato **sul test** (il test diventa una validazione e la stima finale è ottimistica). In tutti i casi il rimedio è lo stesso: calcolare trasformazioni e scelte sul training e applicarle poi, invariate, al test. In scikit-learn l’oggetto `Pipeline` impone questa regola automaticamente dentro la validazione incrociata.

---

### **6. Schemi di validazione**


#### **6.1. k-fold cross validation (k-FCV)**

La validazione incrociata: l’approccio generale e la sua forma più comune.

![](imgs/Pasted%20image%2020261124010726.png)


**L’approccio generale.** In **un round**, i gruppi 1–3 addestrano il modello; il gruppo 4 viene **predetto** e confrontato con i valori veri (A → B). Per un regressore, i punti vicini alla diagonale $\hat{y}=y$ sono buone previsioni. La **validazione incrociata ripete il round** in modo che **ogni gruppo sia usato una volta come test**; gli errori di test di tutti i round vengono poi **aggregati**.

<!-- SOURCE_VISUAL id="IS14-V40" source="L14-web.pdf" page="58" type="schema" description="Approccio generale della validazione incrociata: gruppi di training e di test e grafico predetto contro vero (la p. 59 ne è la versione vettoriale)" reason="Schema e grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124010857.png)

> 📌 **k-FCV.** È lo schema più comune:
> 1. il dataset originale viene **diviso a caso in $k$ fold** (partizioni) di **uguale dimensione**;
> 2. dei $k$ fold, **uno** si tiene come dati di **validazione** per testare il modello, e i **restanti $k-1$** servono a **costruirlo**;
> 3. con $k$ partizioni, il processo si **ripete $k$ volte**, usando **ogni fold esattamente una volta** come validazione.

![](imgs/Pasted%20image%2020261124010930.png)


<!-- SOURCE_VISUAL id="IS14-V41" source="L14-web.pdf" page="62" type="schema" description="k-FCV con 5 fold: 5 modelli, i rispettivi errori e[1..5], media e deviazione standard (la p. 61 ne è la versione raster)" reason="Rotazione dei fold visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124010949.png)
![](imgs/Pasted%20image%2020261124010958.png)

- **Input**: $N$ campioni etichettati. **Output**: $k$ errori di test $e_1,\dots,e_k$ (ciascuno su circa $N/k$ campioni), riassunti da $\bar{e}$ e $s_e$.
- **Ogni campione è testato esattamente una volta**; si addestrano $k$ modelli, ciascuno su circa $(k-1)N/k$ campioni.

#### **6.2. Una nota importante: quale modello rilasciare?**

Dalla k-FCV si ottengono $k$ modelli diversi, ciascuno con il suo errore. Quale si rilascia?

<!-- SOURCE_VISUAL id="IS14-V42" source="L14-web.pdf" page="63" type="figura" description="k modelli della k-FCV con i loro errori: quale rilasciare?" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124011016.png)

> 📌 **Risposta: i modelli dei fold sono usa e getta.** La validazione incrociata **non sceglie uno di essi**: **stima la qualità di una procedura** (algoritmo + iperparametri) e permette di scegliere la **configurazione migliore**.

La procedura completa:

1. **k-FCV** per ogni **configurazione candidata**, sui dati di sviluppo;
2. **riaddestrare la configurazione migliore** su **tutti i dati di sviluppo**;
3. **valutare una volta** sul test set (la stima finale);
4. **rilasciare** questo modello.

| **A. Risposta standard** | **B. Alternativa: ensemble dei $k$ modelli** |
|---|---|
| riaddestrare su **tutto ciò che si è usato per lo sviluppo**: il modello rilasciato è **un modello nuovo**, addestrato su training + validation con la configurazione scelta. Ha visto più dati di ciascun modello dei fold, quindi la stima della CV è, semmai, **leggermente pessimistica** | tenere **tutti i $k$ modelli** e **fare la media delle loro uscite**. Nessun riaddestramento, spesso **leggermente più robusto**, ma **$k$ volte il costo** di inferenza e di memoria. Utile quando riaddestrare è costoso (per esempio reti grandi) |

**Esempio**: 3 configurazioni × $k=5$ = **15 addestramenti** per scegliere, **+1 riaddestramento**, **+1 punteggio di test** da riportare.

> ⚠️ **Mai rilasciare il modello del fold con il miglior test** (un’estrazione fortunata). Dopo il passo 3 si può riaddestrare un’ultima volta su **tutti i dati** (sviluppo + test) per usare ogni campione, ma allora **non resta alcuna stima indipendente**: si riporta il punteggio del passo 3.

#### **6.3. Scegliere $k$**

- Le scelte comuni sono la **5-fold** e la **10-fold** CV. La scelta dipende anche da **dimensione del campione**, **frequenze delle classi**, **vincoli di raggruppamento** (per esempio le persone) e **costo computazionale** ($k$ addestramenti).
- Evitare **fold di test poco popolati**, soprattutto della **classe rara**. Esempio: $N=1000$ con 10 pazienti malati → usare **fold stratificati** (§6.4).

| | 5-fold | 10-fold |
|---|---|---|
| addestramenti | 5 su 800 campioni | 10 su 900 campioni |
| fold di test | 200 campioni (circa 2 malati) | 100 campioni (circa 1 malato) |

![](imgs/Pasted%20image%2020261124011046.png)

> 💡 **Il compromesso su $k$ (nota didattica).** Con $k$ più grande ogni modello si addestra su più dati (stima meno pessimistica), ma i fold di test sono più piccoli e i training set si sovrappongono di più (le $k$ stime sono più correlate), e il costo cresce. Con 10 malati su 1000, un fold di 100 campioni ne contiene in media uno solo: il recall su quel fold può valere solo 0% o 100%.

#### **6.4. k-FCV stratificata**

- La k-FCV semplice può **alterare la proporzione** degli esempi di ciascuna classe nelle partizioni di test.
- La **k-fold stratificata** **conserva approssimativamente le proporzioni originali** delle classi in ogni fold. **Non rende le classi ugualmente numerose.**

**Esempio**: dataset con 990 sani + 10 malati, $k=5$: **ogni fold** contiene **198 + 2**, cioè mantiene l’**1% di malati**.

![](imgs/Pasted%20image%2020261124011101.png)

I **dati raggruppati** (più campioni per persona) richiedono **fold stratificati per gruppo**: si veda LOPO (§6.7).

#### **6.5. 5×2 cross validation (5×2 CV)**

Cinque ripetizioni di una divisione in due.

> 📌 **5×2 CV.**
> - Il dataset viene diviso **a caso in due metà uguali** (50% training, 50% test).
> - Si addestra un modello su una metà e lo si testa sull’altra.
> - Poi **si inverte**: l’altra metà diventa training e la prima test.
> - Questa divisione in due (2CV) si **ripete 5 volte**, per un totale di **10 punteggi** di valutazione.

![](imgs/Pasted%20image%2020261124011154.png)


<!-- SOURCE_VISUAL id="IS14-V43" source="L14-web.pdf" page="69" type="schema" description="5x2 CV: cinque replicazioni di due metà scambiate e 10 punteggi (la p. 70 ne è la versione vettoriale)" reason="Schema delle partizioni visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124011212.png)

In ogni replicazione c’è un **nuovo rimescolamento casuale** e due metà di $N/2$ campioni che si scambiano i ruoli (a, b): **10 modelli**, ciascuno addestrato su $N/2$ campioni, e 10 errori $e_{1,1},\dots,e_{5,2}$ riassunti da media e deviazione standard.

**A che cosa serve la 5×2 CV.**

- **Confrontare due algoritmi.** È stata proposta da **Dietterich (1998)** per un **test statistico** tra due algoritmi di apprendimento: i 10 punteggi accoppiati alimentano il **5×2 cv paired $t$-test**.
- **Metà ripetute e scambiate.** Dentro una replicazione le due metà sono **disgiunte**, quindi i due punteggi sono **meno dipendenti** dei fold sovrapposti della 10-FCV.
- **Robustezza.** Cinque rimescolamenti riducono l’influenza di una particolare divisione.
- **Non riduce il bias**: ogni modello si addestra solo sul **50% dei dati**, quindi l’errore stimato è di solito **più pessimistico** che con la 10-FCV. Con pochi dati, per **stimare le prestazioni** si preferisce la k-FCV (o la k-FCV ripetuta).

> 💡 **Perché serve un test apposito (nota didattica).** Nella 10-FCV due training set qualsiasi condividono 8 fold su 9: i 10 errori sono fortemente correlati, e un $t$-test classico che li tratta come indipendenti sottostima la varianza e dichiara «significative» differenze dovute al caso. Le metà disgiunte della 5×2 CV riducono questa dipendenza e permettono un test con un tasso di falsi allarmi controllato.

#### **6.6. Leave-one-out (LOO)**

**Quando i dati sono molto scarsi.**

- Ogni modello si addestra su **$N-1$ osservazioni**: **quasi tutti i dati**.
- Stima **quasi non distorta** rispetto all’addestramento su $N$.
- **Alta varianza** della stima, **$N$ addestramenti** (costoso).
- **Non cura l’underfitting**: quella è una **proprietà del modello**, non dello schema di validazione.

<!-- SOURCE_VISUAL id="IS14-V44" source="L14-web.pdf" page="72" type="figura" description="Leave-one-out quando i dati sono molto scarsi" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124011233.png)

> 📌 **LOO.** È il **caso estremo della k-FCV**: **$k$ è uguale al numero di esempi** del dataset. A ogni passo **un solo esempio** serve a **testare** il modello, mentre **tutti gli altri** servono a **impararlo**.

![](imgs/Pasted%20image%2020261124011421.png)


<!-- SOURCE_VISUAL id="IS14-V45" source="L14-web.pdf" page="73" type="figura" description="Leave-one-out come caso estremo della k-FCV" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V46" source="L14-web.pdf" page="74" type="schema" description="Leave-one-out: N iterazioni, ciascuna con un solo campione di test (la p. 75 ne è la versione vettoriale)" reason="Schema delle iterazioni visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124011432.png)
![](imgs/Pasted%20image%2020261124011456.png)

$$\bar{e}=\frac1N\sum_{i=1}^{N}e_i,$$

dove $e_i$ è l’errore sul **singolo campione escluso** (per un classificatore 0 = corretto, 1 = sbagliato; per un regressore per esempio $|y_i-\hat{y}_i|$ nelle unità di $y$). **Esempio**: $N=50$, 6 previsioni sbagliate → $\bar{e}=6/50=0{,}12$.

> 💡 **Perché alta varianza (nota didattica).** Gli $N$ modelli sono quasi identici (differiscono per un solo campione), quindi i loro errori sono fortemente correlati e la media non beneficia della compensazione tra stime indipendenti; inoltre ogni singolo $e_i$ è 0 o 1. La stima è quasi non distorta, ma da un dataset all’altro può variare molto.

#### **6.7. Leave-one-PERSON-out (LOPO)**

Si usa in **biometria**, **screening medico**, eccetera. **Critico per i modelli grandi e profondi.** A ogni passo si lasciano fuori **tutti i dati di una persona**; la variante **L(N)PO** lascia fuori **$N$ persone** alla volta.

<!-- SOURCE_VISUAL id="IS14-V47" source="L14-web.pdf" page="76" type="schema" description="Leave-one-person-out in biometria e screening medico" reason="Schema delle partizioni per persona visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124011613.png)

Confrontando LOPO e L(N)PO con una divisione mescolata: **ora è più difficile usare il colore dei capelli…**

<!-- SOURCE_VISUAL id="IS14-V48" source="L14-web.pdf" page="77" type="schema" description="LOPO e L(NP)O contro divisione mescolata: più difficile usare il colore dei capelli" reason="Confronto visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124011646.png)

> 💡 **Perché serve (nota didattica).** Se le foto della stessa persona stanno sia in training sia in test, un modello grande può riconoscere **la persona** (capelli, pelle, sfondo della sua sessione) invece di imparare il compito (per esempio l’emozione o la malattia). Il risultato è ottimo in validazione e pessimo su persone nuove: è il «déjà vu» della Lezione 7. LOPO garantisce che il modello sia sempre testato su **persone mai viste**.

#### **6.8. Validazione Monte Carlo**

(*Repeated Random Subsampling Validation*, validazione per sottocampionamento casuale ripetuto) — in finanza, AI medica, elaborazione dei segnali, eccetera.

**Il metodo Monte Carlo.**

- I metodi, o esperimenti, Monte Carlo sono una vasta classe di **algoritmi** che si basano sul **campionamento casuale ripetuto** per ottenere risultati numerici.
- L’idea è **usare la casualità** per risolvere problemi che potrebbero essere **deterministici in linea di principio**.

**Esempio**: stimare $\pi$ con **10 000 punti casuali** in un quadrato: **7891** cadono dentro il cerchio inscritto, quindi

$$\hat{\pi}=4\cdot\frac{7891}{10\,000}=3{,}1564\quad(\text{valore vero }3{,}1416).$$

<!-- SOURCE_VISUAL id="IS14-V49" source="L14-web.pdf" page="79" type="grafico" description="Stima di pi con 10000 punti casuali, 7891 dentro il cerchio inscritto" reason="Punti del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124011919.png)

> 💡 **Perché il fattore 4 (nota didattica).** Un cerchio di raggio $r$ inscritto in un quadrato di lato $2r$ occupa la frazione $\pi r^2/(2r)^2=\pi/4$ dell’area. La frazione di punti casuali che cade nel cerchio stima quindi $\pi/4$. L’errore della stima cala come $1/\sqrt{n}$: con 10 000 punti l’errore tipico è circa 0,016, coerente con lo scarto osservato di 0,015.

> 📌 **Validazione Monte Carlo.** Valuta i modelli **dividendo a caso il dataset più volte** in training e test:
> 1. il dataset viene **diviso a caso** in training e test;
> 2. il modello viene **addestrato** sul training e **valutato** sul test;
> 3. il processo si **ripete più volte**, con una **nuova divisione casuale** a ogni iterazione;
> 4. la prestazione finale è stimata come **media** dei risultati di tutte le iterazioni.

![](imgs/Pasted%20image%2020261124012025.png)


<!-- SOURCE_VISUAL id="IS14-V50" source="L14-web.pdf" page="80" type="schema" description="Validazione Monte Carlo: R iterazioni con divisione 80/20 nuova a ogni iterazione (la p. 81 ne è la versione vettoriale)" reason="Schema delle iterazioni visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124012219.png)

$$\bar{e}=\frac1R\sum_{r=1}^{R}e_r,$$

con $R$ numero di iterazioni scelto dall’utente (per esempio $R=100$) ed $e_r$ errore di test dell’iterazione $r$ (per esempio training 80%, test 20% con nuova estrazione ogni volta). **Alcuni campioni vengono testati molte volte, altri mai.**

**Punti chiave.**

- **Rapporto di divisione flessibile**: a differenza della k-FCV, le dimensioni di training e test **non devono essere fisse** tra le iterazioni.
- **Randomizzazione ripetuta**: ogni iterazione genera una **nuova partizione casuale**.
- **Numero di ripetizioni**: scelto dall’utente, in base alla **confidenza statistica** desiderata.

#### **6.9. Bias e varianza degli errori**

Nella validazione incrociata (k-FCV, 5×2 CV, LOO, …) **ogni partizione dà un errore di test**: $e_1,e_2,\dots,e_k$.

$$\bar{e}=\frac1k\sum_{i=1}^{k}e_i\quad(\text{errore medio di CV}),\qquad s_e=\sqrt{\frac{1}{k-1}\sum_{i=1}^{k}(e_i-\bar{e})^2}\quad(\text{deviazione standard di CV}).$$

$k$: numero di partizioni; $e_i$: errore sulla partizione di test $i$ (per esempio 0,31 = 31%). **Esempio**: con $e=0{,}31;\ 0{,}33;\ 0{,}32;\ 0{,}34;\ 0{,}30$ si ottiene $\bar{e}=0{,}320$ e $s_e^2=0{,}00025$.

<!-- SOURCE_VISUAL id="IS14-V51" source="L14-web.pdf" page="84" type="figura" description="Errori per fold di due classificatori ad albero con lettura illustrativa di bias e varianza" reason="Tabella e alberi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **I conti (nota didattica).** La somma è $1{,}60$, quindi $\bar{e}=0{,}32$. Gli scarti sono $-0{,}01;\,+0{,}01;\,0;\,+0{,}02;\,-0{,}02$, i quadrati $0{,}0001+0{,}0001+0+0{,}0004+0{,}0004=0{,}0010$; dividendo per $k-1=4$ si ottiene $0{,}00025$, cioè $s_e=0{,}0158$ (circa 1,6 punti percentuali).

> ⚠️ **I punteggi dei fold sono statisticamente dipendenti.** Con $k=5$ due training set qualsiasi condividono **3 dei loro 4 fold**. La loro deviazione standard **descrive la variabilità osservata**, ma **non è automaticamente un intervallo di confidenza**. La lettura «alto bias / alta varianza» della tabella è un’**interpretazione illustrativa** dei due alberi.

#### **6.10. Il dilemma bias-varianza**

> 📌 **Il dilemma bias-varianza.**
> - Descrive un **compromesso fondamentale** del machine learning.
> - Un modello deve essere **abbastanza complesso** da imparare la **vera struttura** dei dati, ma **non così complesso** da imparare **rumore**, pattern accidentali o dettagli specifici del training set.
>   - Un modello **troppo semplice** ha di solito **bias alto e varianza bassa** (porta all’**underfitting**).
>   - Un modello **troppo complesso** ha di solito **bias basso e varianza alta** (porta all’**overfitting**).
> - Il dilemma consiste nello **scegliere il modello che minimizza l’errore atteso sui dati nuovi**, restando **stabile tra divisioni diverse** dei dati.

<!-- SOURCE_VISUAL id="IS14-V52" source="L14-web.pdf" page="86" type="grafico" description="Bias, varianza ed errore totale in funzione della complessità del modello" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124013201.png)

> 📌 **La scomposizione (nota didattica).** Per la regressione con errore quadratico, l’errore atteso su un nuovo punto $x$ si scompone in
> $$\mathbb{E}\big[(y-\hat{f}(x))^2\big]=\underbrace{\big(\mathbb{E}[\hat{f}(x)]-f(x)\big)^2}_{\text{bias}^2}+\underbrace{\mathbb{E}\big[(\hat{f}(x)-\mathbb{E}[\hat{f}(x)])^2\big]}_{\text{varianza}}+\underbrace{\sigma^2}_{\text{rumore}},$$
> dove l’attesa è sui possibili training set. Il rumore $\sigma^2$ non si può eliminare; il progettista sposta l’equilibrio tra gli altri due termini scegliendo la complessità del modello (gli alberi profondi della Lezione 12 hanno varianza alta, e la random forest la riduce).

#### **6.11. Metodi di validazione a confronto**

| | **k-FCV** | **5×2 CV** | **LOO** | **LOPO / per gruppo** | **Monte Carlo** |
|---|---|---|---|---|---|
| **divisione** | $k$ fold, ciascuno testato una volta | 2 metà scambiate, 5 rimescolamenti | 1 campione fuori, $N$ volte | 1 persona (gruppo) fuori | divisione casuale, $R$ volte |
| **dimensione del training** | $\approx(k-1)N/k$ | $N/2$ | $N-1$ | $N$ meno i campioni di una persona | scelta (per esempio 80%) |
| **modelli** | $k$ | 10 | $N$ | numero di persone | $R$ |
| **costo** | moderato | basso–moderato | alto per $N$ grande | cresce con le persone | cresce con $R$ |
| **stima tipica** | leggermente pessimistica; buon default | più pessimistica (metà dei dati) | quasi non distorta ma molto variabile | realistica per persone nuove | dipende da rapporto e $R$ |
| **adatto a** | uso generale (stratificata per le classi) | confrontare due algoritmi | dataset molto piccoli | biometria, medicina, dati raggruppati | rapporti flessibili, dataset grandi |
| **attenzione a** | fold che differiscono di 1 campione | pochi dati di training | costoso; instabile | poche persone → instabile | campioni testati in modo non uniforme |

Bias e varianza si riferiscono alla **stima delle prestazioni**; le voci sono **tendenze tipiche, non garanzie**.

> ✅ **Punti chiave.**
> - La **validazione Monte Carlo** offre **grande flessibilità** ma può essere **costosa** in calcolo.
> - La **k-FCV** è un metodo **ben bilanciato e molto usato** (considerare anche la **k-FCV stratificata**).
> - La **5×2 CV** è utile per **confrontare modelli** e **controllare la varianza**.
> - Il **LOO** è adatto ai **dataset piccoli** ma può essere **costoso**.
> - Il **LOPO** si usa per problemi di **generalizzazione su soggetti** diversi.

#### **6.12. Validazione con serie temporali**

In biometria, screening medico, previsioni, mercati azionari, eccetera.

**Il problema classico della previsione nelle serie temporali**: una divisione casuale mette dati **futuri** nel training e dati **passati** nel test.

<!-- SOURCE_VISUAL id="IS14-V53" source="L14-web.pdf" page="90" type="schema" description="Il problema classico della divisione nelle serie temporali" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124013242.png)

**Un compito complesso: la finestra mobile.**

- Dividere una serie temporale in training e test è un problema **soggetto a errori**: si può osservare **data leakage**.
- Un sistema di base è **simulare il passare del tempo**… ma **si sprecano molti dati**!

<!-- SOURCE_VISUAL id="IS14-V54" source="L14-web.pdf" page="91" type="schema" description="Validazione a finestra mobile (rolling window)" reason="Posizione delle finestre visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124013258.png)

**Un compito complesso: la finestra espandibile in avanti.** Stessa simulazione del passare del tempo, ma la **dimensione delle partizioni di training è diversa**: **cresce a ogni divisione**.

<!-- SOURCE_VISUAL id="IS14-V55" source="L14-web.pdf" page="92" type="schema" description="Validazione a finestra espandibile in avanti (expanding window)" reason="Posizione delle finestre visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124013311.png)

**Dividere le serie temporali per la validazione incrociata.**

- **CV a blocchi** (figura originale): alcune divisioni **addestrano sul futuro e testano sul passato**. È accettabile **solo quando l’ordine temporale non conta** (per esempio serie stazionarie, nessuna previsione). Per la previsione **fa trapelare informazione futura**.
- **Forward chaining con un gap** (per la previsione): si addestra **solo sul passato**, si lascia un **intervallo vuoto** (i campioni vicini nel tempo sono **correlati**) e si testa sul **blocco successivo**. Le finestre mobili ed espandibili delle slide precedenti sono **entrambe forward chaining**.

<!-- SOURCE_VISUAL id="IS14-V56" source="L14-web.pdf" page="93" type="schema" description="CV a blocchi contro forward chaining con gap: tre divisioni con training, gap e test nel tempo" reason="Disposizione delle partizioni visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124013346.png)

> 💡 **Perché il gap (nota didattica).** Campioni vicini nel tempo sono molto simili: se il test inizia subito dopo l’ultimo campione di training, il modello può «copiare» l’ultimo valore visto e sembrare bravo. Un intervallo vuoto obbliga il modello a prevedere davvero, e riduce il leakage dovuto all’autocorrelazione della serie (si ricordino le finestre sovrapposte della Lezione 10 e l’ACF della Lezione 11).

| Schema | Come funziona | Pro / contro |
|---|---|---|
| **finestra mobile** | training di dimensione fissa che scorre nel tempo, test subito dopo | rispetta il tempo; **spreca** il passato lontano |
| **finestra espandibile** | training dall’inizio, che **si allarga** a ogni passo | usa tutto il passato; training di dimensioni diverse |
| **forward chaining con gap** | come le precedenti, con un intervallo vuoto prima del test | riduce il leakage da autocorrelazione |
| **CV a blocchi** | blocchi a rotazione, anche futuro → passato | solo se l’ordine non conta |

---

### **7. Valutazione dell’accuratezza: regressori e classificatori**


Quale numero riportare.

#### **7.1. Metriche di classificazione: l’accuratezza può ingannare**

Matrice di confusione (positivo = malato):

| | predetto malato | predetto sano |
|---|---|---|
| **malato** | $TP$ | $FN$ |
| **sano** | $FP$ | $TN$ |

$$\text{accuratezza}=\frac{TP+TN}{N},\quad \text{recall}=\frac{TP}{TP+FN},\quad \text{precisione}=\frac{TP}{TP+FP},\quad F_1=\frac{2PR}{P+R},\quad \text{balanced accuracy}=\frac12\left(\frac{TP}{TP+FN}+\frac{TN}{TN+FP}\right).$$

$FN$: malati mancati; $FP$: falsi allarmi; $N$: tutti i campioni; $P$, $R$: precisione e recall (0–1).

**Esempio**: 1000 pazienti, 990 sani, 10 malati (gli stessi dati della pesatura della perdita).

| | **A: sempre «sano»** | **B: classificatore reale** |
|---|---|---|
| TP / FN / FP / TN | 0 / 10 / 0 / 990 | 8 / 2 / 40 / 950 |
| **accuratezza** | **99,0%** | 95,8% |
| **recall (malati)** | **0%** | 80% |
| **precisione** | indefinita (0/0) | 16,7% |
| **F1** | 0 | 0,28 |
| **balanced accuracy** | 50% | 88,0% |

> 📌 **Un’accuratezza alta non implica necessariamente un classificatore utile.** Con dati sbilanciati si riportano **precisione, recall, F1, balanced accuracy** e, per i punteggi, la **curva precision-recall** (PR-AUC). **Ecco perché un errore dell’1% può essere buono o cattivo.**

> 💡 **I conti di B (nota didattica).** Accuratezza $(8+950)/1000=95{,}8\%$; recall $8/10=80\%$; precisione $8/48=16{,}7\%$; $F_1=2\cdot 0{,}167\cdot 0{,}8/(0{,}967)=0{,}28$; balanced accuracy $\frac12(0{,}80+950/990)=\frac12(0{,}80+0{,}96)=88\%$. A ha l’accuratezza più alta ma è inutile: la balanced accuracy, che dà lo stesso peso alle due classi, rivela che A vale quanto un lancio di moneta (50%).

#### **7.2. Metriche di regressione: errori con unità e una baseline**

$$e_i=y_i-\hat{y}_i\ \ (\text{errore del campione }i),\qquad \text{MAE}=\frac1n\sum_i|e_i|\ \ (\text{errore assoluto medio}),$$

$$\text{RMSE}=\sqrt{\frac1n\sum_i e_i^2}\ \ (\text{penalizza di più gli errori grandi}),\qquad R^2=1-\frac{\sum_i e_i^2}{\sum_i(y_i-\bar{y})^2}\ \ (\text{varianza spiegata rispetto alla baseline della media}).$$

$y_i$: valore vero; $\hat{y}_i$: previsione; $\bar{y}$: media dei valori veri; $n$: campioni di test. **MAE e RMSE hanno le unità di $y$**; $R^2$ è **adimensionale** ($\le 1$; $0$ = buono quanto prevedere $\bar{y}$).

**Esempio**: consumo energetico giornaliero di una macchina (kWh), 5 giorni di test.

| | | | | | |
|---|---:|---:|---:|---:|---:|
| $y$ | 20 | 25 | 30 | 35 | 40 |
| $\hat{y}$ | 18 | 26 | 27 | 37 | 30 |
| $e$ | 2 | −1 | 3 | −2 | **10** |

| | MAE | RMSE | $R^2$ |
|---|---:|---:|---:|
| **modello** | 3,6 kWh | 4,86 kWh | 0,53 |
| **baseline** $\hat{y}=\bar{y}=30$ | 6,0 kWh | 7,07 kWh | 0 |

**RMSE > MAE a causa del singolo errore da 10 kWh.**

> 📌 **Riportare le metriche d’errore con le loro unità fisiche** e confrontarle con una **baseline significativa**.

> 💡 **I conti (nota didattica).** MAE $=(2+1+3+2+10)/5=18/5=3{,}6$. RMSE $=\sqrt{(4+1+9+4+100)/5}=\sqrt{23{,}6}=4{,}86$: il solo errore da 10 contribuisce 100 su 118 alla somma dei quadrati. $R^2=1-118/250=0{,}53$, con $\sum(y_i-\bar{y})^2=100+25+0+25+100=250$. La baseline che prevede sempre 30 ha errori $-10,-5,0,5,10$: MAE $=30/5=6$, RMSE $=\sqrt{250/5}=7{,}07$ e, per definizione, $R^2=0$. Un $R^2$ negativo significherebbe un modello peggiore della media.

#### **7.3. Punti principali**

> ✅ **Punti principali della lezione.**
> - **Flusso di lavoro**: **prima partizionare, poi bilanciare solo i dati di training** (ricampionamento, SMOTE, pesi della perdita).
> - **Ogni passo che impara dai dati** (normalizzazione, PCA, selezione delle feature) **si adatta solo sul training**.
> - **Si addestra per imparare, si valida per scegliere, si testa una volta per valutare**; la CV sceglie una configurazione, poi **si riaddestra**.
> - **Schemi di validazione**: k-FCV (stratificata), 5×2 CV (confrontare algoritmi), LOO, LOPO (dati raggruppati), Monte Carlo; **forward chaining** per le serie temporali.
> - **Valutazione**: l’accuratezza **inganna** con dati sbilanciati; usare recall, precisione, F1; per i regressori MAE e RMSE **con unità**, rispetto a una baseline.
> - **Due punti fortissimi**:
>   - **NON PROGETTARE USANDO IL TEST SET**;
>   - **UN ERRORE DELL’1% PUÒ ESSERE BUONO O CATTIVO**.

---

### **8. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **precisione di un regressore** / **di un classificatore** | dispersione degli errori, in unità di $y$ / $TP/(TP+FP)$, adimensionale |
| **giustezza** / **precisione** (regressore) | errore medio vicino a 0 / errori poco dispersi |
| **log-loss** / **Brier** | $-\ln q$, punisce molto gli errori sicuri / $(q-o)^2$, limitato tra 0 e 1 |
| **$D_{KL}(P\Vert Q)$** / **$D_{KL}(Q\Vert P)$** | non sono uguali: la divergenza non è simmetrica |
| **accuratezza** / **margine** | quanti punti giusti / quanto spazio tra frontiera e punti più vicini |
| **ottimizzazione convessa** / **non convessa** | ogni minimo locale è globale / il minimo dipende dal punto di partenza |
| **regressione** / **classificazione** nei grafici | $y$ esplicita come superficie / $y$ implicita come frontiera |
| **dataset accademico** / **reale** | classi bilanciate / classi sbilanciate con costi diversi |
| **sovracampionamento** / **sottocampionamento** | rischio di overfitting sulle copie / perdita di informazione |
| **duplicare** / **SMOTE** | copie identiche / campioni sintetici sul segmento tra vicini |
| **sottocampionamento casuale** / **dataset condensato** | toglie maggioritari a caso / tiene quelli necessari vicino alla frontiera |
| **pesi da frequenze** / **pesi da costi** | $w_c=N/(C\,N_c)$ / dalla matrice dei costi reali |
| **bilanciare prima** / **dopo la divisione** | leakage, stima ottimistica / corretto, test con proporzioni reali |
| **bias ottimistico** / **overfitting** | stima troppo favorevole per dati usati nel progetto / modello troppo adattato |
| **underfitting** / **overfitting** | visibile dall’errore di training / visibile solo su dati nuovi |
| **validation** / **test** | scegliere iperparametri e modello / stimare una volta la generalizzazione |
| **modelli dei fold** / **modello rilasciato** | usa e getta, stimano la procedura / riaddestrato su tutti i dati di sviluppo |
| **k-FCV** / **k-FCV stratificata** | fold casuali / proporzioni delle classi conservate (non classi uguali) |
| **5×2 CV** / **10-FCV** | confronto statistico tra algoritmi, più pessimistica / stima delle prestazioni |
| **LOO** / **LOPO** | un campione fuori / tutti i dati di una persona fuori |
| **k-FCV** / **Monte Carlo** | ogni campione testato una volta / divisioni casuali ripetute, test non uniforme |
| **bias** / **varianza** | errore sistematico, modello troppo semplice / sensibilità ai dati, modello troppo complesso |
| **finestra mobile** / **espandibile** | training fisso, spreco di dati / training crescente |
| **forward chaining** / **CV a blocchi** | solo passato → futuro / anche futuro → passato, solo se l’ordine non conta |
| **accuratezza** / **balanced accuracy** | dominata dalla classe maggioritaria / stesso peso alle due classi |
| **MAE** / **RMSE** | errore medio assoluto / penalizza di più gli errori grandi |
