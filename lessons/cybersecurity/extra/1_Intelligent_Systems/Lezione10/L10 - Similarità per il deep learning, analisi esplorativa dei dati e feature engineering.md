## **Lezione 10: Similarità per il deep learning, analisi esplorativa dei dati e feature engineering**

La decima lezione del corso **IS4** chiude il discorso sulla cross-correlazione e apre il lavoro statistico sui dati. Le soluzioni dei compiti mostrano l’**autocorrelazione** e perché i massimi della correlazione vanno **normalizzati**. L’esempio dei gelati mostra che un piccolo **kernel** che «cerca» un pattern è esattamente ciò che fa uno strato di una **rete convoluzionale**. La seconda parte è dedicata all’**analisi esplorativa dei dati** (EDA): contesto dei dati, segnali nel tempo e in frequenza, statistica descrittiva, boxplot e outlier. Seguono la **feature engineering**, la **normalizzazione** delle feature (scalatura, clipping, logaritmo, z-score) e l’idea di analizzare statisticamente non solo gli input di una rete, ma anche **pesi**, **attivazioni** e **uscite**.

Fonte: [PDF della Lezione 10](Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf), 76 pagine, nessun supplemento. Il codice citato alle pp. 25 e 46 è riportato nel testo; quello dei gelati coincide con il file `code/lesson_09_code_STEP2.m` della Lezione 09. Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–3)_

Titolo originale: *Similarity and Deep Learning (with coding), Exploratory Data Analysis, Feature engineering*.

**Nota sulla simulazione d’esame** (p. 2): avvicinandosi a **metà del corso**, nella Lezione 13 o 14 si farà una **simulazione dell’esame** per verificare preparazione e metodo di studio.

L’indice (p. 3) elenca:

1. la **similarità come strumento** per il deep learning;
2. **analisi esplorativa dei dati**;
3. **feature engineering**;
4. **pre-elaborazione dei dati** da un punto di vista statistico;
5. **applicazione dell’EDA alle reti neurali**: input, pesi, attivazioni, uscite;
6. punti principali.

> ⚠️ **Numerazione della slide.** La p. 3 è intitolata «Lesson 11: Outline», ma il contenuto è quello di questa **Lezione 10**, come confermano il titolo del PDF e i punti principali (p. 76).

---

### **1. Soluzioni dei compiti: autocorrelazione e normalizzazione**

_(slide pp. 4–10)_

Le soluzioni riguardano i compiti MATLAB della Lezione 09, §4.4.

**Compito 1** (p. 5): come cambia la correlazione se si usa come sottoimmagine **lo stesso template**, cioè $\text{CRR}(x, x)$?

> 📌 **Autocorrelazione** (p. 5). La correlazione di un’immagine con se stessa si chiama **autocorrelazione**. Il picco è **stretto** e il massimo è **netto e ben separato**.

**Compito 2** (p. 6): come cambia se si usano **due immagini completamente diverse**? Le pp. 7–8 mostrano i risultati in forma grafica.

<!-- SOURCE_VISUAL id="IS10-V01" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="5" type="grafico" description="Autocorrelazione del template: picco stretto con massimo netto e separato" reason="Forma della superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V02" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="7" type="grafico" description="Correlazione tra due immagini completamente diverse" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V03" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="8" type="grafico" description="Confronto delle superfici di correlazione per immagini uguali e diverse" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Confrontare i massimi richiede una normalizzazione** (pp. 9–10). Per confrontare i **valori massimi** di correlazioni diverse bisogna **normalizzare rispetto al numero di pixel**; in MATLAB, moltiplicando per $1/n$.

> 💡 **Perché (nota didattica).** `xcorr2` calcola una **somma** di prodotti, non una media. Due immagini grandi producono somme grandi anche se si somigliano poco, mentre due immagini piccole e identiche producono somme piccole. Dividendo per il numero $n$ di pixel sovrapposti si ottiene una **media**, confrontabile tra immagini di dimensioni diverse:
> $$\text{CRR}_{\text{norm}}(i, j) = \frac{1}{n} \sum_{x, y} f(x+i, y+j)\, t(x, y)$$
> La NCC della Lezione 09, §2, fa un passo in più: divide anche per le deviazioni standard.

<!-- SOURCE_VISUAL id="IS10-V04" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="10" type="schermata" description="Confronto dei massimi di correlazione con normalizzazione 1/n in MATLAB" reason="Codice e valori visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. La similarità come strumento del deep learning**

_(slide pp. 11–25)_

#### **2.1. Correlazione e convoluzione**

La similarità tramite convoluzione è la **base delle reti convoluzionali** (p. 11). Bisogna capire molto bene l’analisi di similarità tramite **cross-correlazione** (convoluzione), perché è quella usata nelle **CNN**, uno dei modelli di deep learning più famosi (p. 12).

> 📌 **«Basta il segno meno!»** (pp. 12–13). Tra correlazione e convoluzione cambia **solo il segno** degli indici del kernel:
> $$(f \star t)(i, j) = \sum_{x, y} f(i + x, j + y)\, t(x, y) \qquad (f * t)(i, j) = \sum_{x, y} f(i - x, j - y)\, t(x, y)$$
> Il segno meno equivale a **ruotare il kernel di 180°** prima di farlo scorrere (Lezione 09, §2). La p. 13 lo illustra graficamente.

<!-- SOURCE_VISUAL id="IS10-V05" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="13" type="schema" description="Correlazione e convoluzione differiscono solo per il segno meno: kernel ribaltato" reason="Schema del ribaltamento visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. Similarità interna: l’autocorrelazione dei gelati**

**Similarità interna** (pp. 14–16). Applicando l’**autocorrelazione** a un’immagine con molti **gelati** simili, la superficie mostra **picchi alti e separati**: la correlazione rivela quanto l’immagine sia simile a se stessa **spostata**, e quindi la presenza di **pattern ripetuti**.

<!-- SOURCE_VISUAL id="IS10-V06" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="14" type="immagini" description="Autocorrelazione come similarità interna dell’immagine dei gelati" reason="Immagine e superficie visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V07" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="16" type="grafico" description="Autocorrelazione dei gelati con picchi alti e separati" reason="Picchi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. Cercare un pattern con un kernel**

_(slide pp. 17–22)_

**Similarità generale tra pattern, non solo nelle immagini: nei dati** (pp. 17–18). Si prende un **singolo gelato** come «**kernel**» della ricerca. Le immagini sono **diverse, ma simili**: il gelato del kernel non è ritagliato dall’immagine grande.

**Preparare un pattern simile** (p. 19): pensare in termini di **pattern**, non solo di immagini, e usare un kernel di **dimensione simile** agli oggetti da cercare.

**Il pattern è stato trovato** (pp. 20–21): la correlazione mostra **picchi** in corrispondenza dei gelati. L’immagine della slide è ingrandita per la visualizzazione.

**La controprova con il panda** (p. 22). Usando come kernel un **panda**, la correlazione diventa **rumorosa**. Si vedono comunque dei **picchi**, anche se rumorosi, perché il panda si comporta da **rilevatore di blob**: le sue macchie scure rispondono alle zone scure dell’immagine.

> 💡 **Che cosa insegna la controprova (nota didattica).** Un picco della correlazione non significa «ho trovato l’oggetto», ma «qui c’è qualcosa che **somiglia al kernel** secondo questa misura». Un kernel generico risponde a strutture generiche (macchie, bordi). Per questo servono **soglie** e **confronti normalizzati** (§1), e per questo le CNN usano **molti filtri** e più strati.

<!-- SOURCE_VISUAL id="IS10-V08" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="17" type="immagini" description="Singolo gelato usato come kernel di ricerca nell’immagine dei gelati" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V09" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="20" type="immagine" description="Pattern simile trovato nell’immagine (ingrandita)" reason="Posizione del pattern visibile solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V10" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="21" type="grafico" description="Picchi della correlazione in corrispondenza dei gelati" reason="Picchi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V11" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="22" type="grafico" description="Controprova con il panda come kernel: correlazione rumorosa con picchi da rilevatore di blob" reason="Superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.4. Dalla correlazione alle CNN e ai difetti**

**Convoluzione nel deep learning** (p. 23): la slide mostra un modello **CNN**, in cui ogni strato applica molti filtri convolutivi all’input.

> 📌 **Uno strato convolutivo (nota didattica).** Ogni filtro $k$ di uno strato produce una **mappa di attivazione** facendo scorrere il kernel $W_k$ sull’input $X$, aggiungendo un bias e applicando una funzione di attivazione $\varphi$ (per esempio ReLU):
> $$A_k(i, j) = \varphi\!\left( \sum_{x, y} X(i + x, j + y)\, W_k(x, y) + b_k \right)$$
> È la correlazione del §2.3, con due differenze: i kernel $W_k$ non sono scelti a mano ma **appresi** dai dati, e sono molti, uno per ogni pattern utile.

<!-- SOURCE_VISUAL id="IS10-V12" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="23" type="diagramma" description="Modello CNN di deep learning con strati convolutivi" reason="Architettura visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Analisi di similarità per trovare difetti** (p. 24). Nelle applicazioni i **difetti** si possono trovare come **differenze** o **mancanze** nel pattern di similarità: in un prodotto con struttura regolare, un difetto «rompe» la ripetizione e il picco atteso si abbassa o scompare.

<!-- SOURCE_VISUAL id="IS10-V13" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="24" type="immagini" description="Difetti individuati come mancanze nel pattern di similarità" reason="Esempio visivo dei difetti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il codice usato** (p. 25) è quello degli esperimenti sui gelati del file `lesson_09_code_STEP2.m`:

```matlab
%% similarity
img_cream = double(rgb2gray( imread( 'ice_creams.jpg')));
img_cream_norm = img_cream - mean(mean(img_cream));
crr5 = xcorr2( double( img_cream_norm) , double(img_cream_norm) ) ;   % same image
surf(crr5); shading interp;

% similarity in CNN...
img_single = double(rgb2gray( imread( 'icecream2.jpg')));
img_single_norm = img_single - mean(mean(img_single));
crr6 = xcorr2( double( img_cream_norm) , double(img_single_norm) ) ;  % single ice cream as kernel

img_single = double(rgb2gray( imread( 'panda.jpg')));
img_single_norm = img_single - mean(mean(img_single));
crr6 = xcorr2( double( img_cream_norm) , double(img_single_norm) ) ;  % a panda as kernel
```

> ⚠️ **Semplificazioni** (p. 25). Nelle sessioni di codice alcune parti della teoria sono state **semplificate**, per cogliere i concetti principali senza perdersi in dettagli tecnici fuori dall’ambito del corso. Qui, a differenza della Lezione 09, la conversione in `double` avviene **prima** della sottrazione della media, che quindi è corretta.

---

### **3. Analisi esplorativa dei dati (EDA)**

_(slide pp. 26–46)_

#### **3.1. Dove si colloca l’EDA**

Obiettivo (p. 26): **capire che cosa c’è dentro i dati**. Richiami al flusso di lavoro del ML: il **passo 1** raccoglie i dati da query a database, file, formati personalizzati, flussi IoT/AIoT e piattaforme online (p. 27; Lezione 06, §3.1); il **passo 2** li prepara (p. 28).

> 📌 **I sottopassi del passo 2** (pp. 29–30).
> - **2a)** *data munging* / **data wrangling**: pulire e strutturare (Lezione 06, §6.2);
> - **2b)** **analisi esplorativa dei dati** (EDA);
> - **2c)** **feature engineering** (§4).

Le pp. 31–32 mostrano lo schema dell’EDA per il ML e alcuni **strumenti MATLAB** per l’EDA.

<!-- SOURCE_VISUAL id="IS10-V14" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="31" type="schema" description="Analisi esplorativa dei dati per il ML con i riferimenti alle lezioni" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V15" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="32" type="schermata" description="Strumenti di analisi esplorativa dei dati in MATLAB" reason="Strumenti visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Segnali nel tempo e dati non strutturati**

_(slide pp. 33–39)_

> 📌 **Estrazione delle feature** (p. 34). Il processo di trasformare i **dati grezzi** in un insieme di **feature utilizzabili** da un algoritmo di ML per addestrare un modello. È cruciale, perché **qualità e quantità** delle feature influenzano in modo significativo le prestazioni del modello.

**Il contesto dei dati** (p. 35):

- **fonte**: sapere da dove vengono i dati aiuta a valutarne **qualità, affidabilità e pertinenza**;
- **contesto temporale e spaziale**: per serie temporali e dati geografici è cruciale, perché i pattern **cambiano nel tempo** o **tra luoghi diversi**;
- **primo passo**: che cos’è il dataset? Una **tabella** o un **flusso** di dati non ancora strutturato?
- il tipo di sorgente IoT: **passiva** (dati solo su richiesta), **attiva** (flusso da assorbire quasi in tempo reale), **dinamica** (formato, frequenza e aggiornamenti *over the air* modificabili) (Lezione 05, §6.2).

**Segnali nel tempo: quali informazioni estrarre?** (p. 36). Per i segnali **continui**, per ogni **frame** (finestra) di ogni segnale si estrae un insieme di feature, che rappresentano **caratteristiche diverse** del segnale in quella finestra. Si fa così perché è più facile analizzare e classificare **porzioni discrete** di dati che un segnale continuo.

> 💡 **Finestre e feature (nota didattica).** Un segnale campionato $s[n]$ si divide in frame di $L$ campioni, spesso sovrapposti. Da ogni frame si calcolano feature come media, energia o numero di passaggi per lo zero:
> $$\mu_m = \frac{1}{L} \sum_{n=0}^{L-1} s[mH + n] \qquad E_m = \sum_{n=0}^{L-1} s[mH + n]^2$$
> dove $H$ è il passo tra un frame e il successivo. Un’ora di segnale diventa così una **tabella**: una riga per frame, una colonna per feature.

**Dominio del tempo o della frequenza?** (pp. 37–39):

| Rappresentazione | Assi | Che cosa mostra |
|---|---|---|
| **dominio del tempo** | tempo / ampiezza | le **dinamiche temporali** del segnale |
| **dominio della frequenza** (spettro) | frequenza / ampiezza (o potenza), con anche la **fase** | ogni barra è l’ampiezza di una **componente in frequenza**: la **distribuzione dell’energia** tra le frequenze, cioè il contenuto **armonico** (vibrazioni, altezza, note musicali) |
| **spettrogramma** | tempo / frequenza, con l’energia come colore | l’**ampiezza o energia** di una certa frequenza **in un certo istante** |

Nelle applicazioni **audio** (riconoscimento vocale, classificazione musicale, rilevamento di eventi sonori) conviene analizzare il segnale **in entrambi i domini** (p. 38).

> 📌 **Esempio della slide** (p. 38). Un movimento ripetuto **12 volte in 10 secondi** produce energia alla frequenza
> $$f = \frac{12\ \text{ripetizioni}}{10\ \text{s}} = 1{,}2\ \text{Hz}$$
> Nel dominio della frequenza compare un picco a 1,2 Hz, anche se nel dominio del tempo il segnale può apparire irregolare.

> 💡 **Trasformata e spettrogramma (nota didattica).** Lo spettro di un frame di $N$ campioni, alla frequenza di campionamento $f_s$, si calcola con la trasformata di Fourier discreta; la componente $k$ corrisponde alla frequenza $f_k = k\, f_s / N$:
> $$S(k) = \sum_{n=0}^{N-1} s[n]\, e^{-j 2\pi k n / N}$$
> Lo spettrogramma è $|S_m(k)|^2$ calcolato frame per frame: una colonna di spettro per ogni istante.

<!-- SOURCE_VISUAL id="IS10-V16" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="37" type="grafico" description="Segnale nel dominio del tempo e sua rappresentazione in frequenza" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V17" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="38" type="grafico" description="Dominio del tempo, modulo e fase in frequenza: 12 ripetizioni in 10 s con energia a 1,2 Hz" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V18" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="39" type="grafico" description="Spettrogramma con energia per frequenza e tempo" reason="Spettrogramma visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. Statistica descrittiva, distribuzioni e boxplot**

_(slide pp. 40–46)_

**Funzioni MATLAB di base per la statistica descrittiva** (p. 41): comandi **molto semplici ed efficaci** per l’EDA, i cui valori si usano **anche nella feature engineering**.

> 💡 **Le misure tipiche (nota didattica).** Le funzioni di base di MATLAB per la statistica descrittiva sono `mean`, `median`, `mode`, `std`, `var`, `min`, `max`, `range`, `quantile`/`prctile`, `iqr`, `skewness` e `kurtosis`. L’elenco esatto della slide va verificato sulla figura.

<!-- SOURCE_VISUAL id="IS10-V19" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="41" type="tabella" description="Funzioni MATLAB di base per la statistica descrittiva" reason="Elenco delle funzioni presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**La forma delle distribuzioni** (p. 42). Esempio: nell’**errore di una regressione** contano molto **posizione e ampiezza delle code**, perché un errore **positivo** e uno **negativo** possono avere conseguenze diverse (sovrastimare o sottostimare una domanda, una scorta, un consumo).

**Le distribuzioni dicono di più** (p. 43). Due distribuzioni possono avere quasi la **stessa media** e lo **stesso IQR** (*InterQuartile Range*), ma **forma** e **intervallo dei valori** molto diversi.

> 📌 **Quartili e IQR (nota didattica).** $Q_1$ e $Q_3$ sono i valori sotto cui cade rispettivamente il 25% e il 75% dei dati; la mediana è $Q_2$.
> $$\text{IQR} = Q_3 - Q_1$$
> L’IQR misura la dispersione del **50% centrale** dei dati ed è **robusto agli outlier**, a differenza della deviazione standard. Proprio per questo non vede le code: servono grafici come istogrammi e boxplot.

<!-- SOURCE_VISUAL id="IS10-V20" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="42" type="grafico" description="Distribuzione dell’errore di regressione con code positive e negative" reason="Forma della distribuzione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V21" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="43" type="grafico" description="Distribuzioni con stessa media e IQR ma forma e intervallo diversi" reason="Forme delle distribuzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il boxplot** (pp. 44–45). Conviene scegliere **boxplot che mostrano gli outlier**. Gli **outlier** possono influenzare **moltissimo la normalizzazione** (§5).

> 📌 **Come si legge un boxplot (nota didattica).** La scatola va da $Q_1$ a $Q_3$, con una linea sulla mediana. I «baffi» arrivano ai valori più estremi entro
> $$\big[\, Q_1 - 1{,}5 \cdot \text{IQR},\; Q_3 + 1{,}5 \cdot \text{IQR} \,\big]$$
> e i punti fuori da questo intervallo sono disegnati singolarmente come **outlier**. È il criterio predefinito anche in MATLAB.

<!-- SOURCE_VISUAL id="IS10-V22" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="44" type="grafico" description="Struttura di un boxplot" reason="Elementi del boxplot visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS10-V23" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="45" type="grafico" description="Boxplot con outlier evidenziati" reason="Outlier visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il boxplot in MATLAB** (p. 46), con il dataset di esempio `carsmall`:

```matlab
load carsmall
boxplot(MPG,Origin)
title('Miles per Gallon by Vehicle Origin')
xlabel('Country of Origin')
ylabel('Miles per Gallon (MPG)')
```

Il comando disegna un boxplot dei **consumi** (miglia per gallone) **per ogni Paese di origine** dei veicoli: un modo immediato per confrontare distribuzioni di gruppi diversi.

---

### **4. Feature engineering**

_(slide pp. 47–56)_

#### **4.1. Il ciclo della feature engineering**

**Progettare le proprie feature** (p. 47) è il sottopasso **2c** del flusso di lavoro (p. 48). È un ciclo (p. 49):

1. fare **brainstorming** o provare feature;
2. **decidere** quali feature creare;
3. **creare** le feature;
4. **verificare** come funzionano con il modello;
5. **migliorarle** se serve;
6. tornare al brainstorming e alla creazione di altre feature **finché il lavoro non è finito**.

**Modello di AI → $Y = \text{FUNC}(X)$** (p. 50). Per ogni dataset tutto consiste nel creare $X$ (feature, una riga per campione) e $Y$ (classe o valore da apprendere) (Lezione 06, §6.1).

#### **4.2. Tre livelli di intervento**

_(slide pp. 51–56)_

| Livello | Che cosa si ottimizza | Usa le etichette? | Operazioni (slide) |
|---|---|---|---|
| **(A)** **una feature alla volta** (pp. 51–52) | una **colonna** di $X$ | no | **rimozione degli outlier**; **armonizzazione** (cm/mm/m → m; similarità → distanze); **normalizzazione/standardizzazione** (§5) |
| **(B)** **tutte le feature insieme** (pp. 53–54) | le **colonne** di $X$ nel loro insieme | **no** | **rimuovere feature fortemente correlate**; **creare combinazioni potenti**; **analisi delle componenti principali** (PCA) e altri metodi di **riduzione della dimensionalità** |
| **(C)** **tutto il dataset** (pp. 55–56) | $X$ **e** $Y$ | **sì** | **estrazione** delle feature; **selezione** delle feature; feature engineering guidata dall’obiettivo |

> 💡 **Esempi per livello (nota didattica).**
> - **(A)**: convertire tutte le lunghezze in metri, poi standardizzare la colonna.
> - **(B)**: se «altezza in cm» e «altezza in pollici» sono entrambe presenti, la correlazione è 1 e una va rimossa; oppure dal rapporto tra peso e altezza al quadrato si crea il BMI.
> - **(C)**: con le etichette si misura quanto ciascuna feature **aiuta a predire** $Y$, e si tengono solo le più utili.

> 📌 **Correlazione tra feature (nota didattica).** Due feature $x_a$ e $x_b$ con coefficiente di Pearson $|\rho_{ab}|$ vicino a 1 portano quasi la stessa informazione (Lezione 08, §2). La PCA trova invece le direzioni di **massima varianza** diagonalizzando la matrice di covarianza $\Sigma$:
> $$\Sigma\, \mathbf{v}_k = \lambda_k\, \mathbf{v}_k$$
> Proiettando i dati sui primi autovettori $\mathbf{v}_k$ (con gli autovalori $\lambda_k$ più grandi) si riduce la dimensione conservando gran parte della varianza.

---

### **5. Normalizzazione delle feature**

_(slide pp. 57–63)_

La pre-elaborazione **da un punto di vista statistico** (p. 57) sta **a metà tra EDA e feature engineering**. Per i dataset dei sistemi intelligenti conta la **forma** delle distribuzioni, **non la grandezza** dei valori (p. 58).

> 📌 **Obiettivo della normalizzazione** (p. 59). Trasformare le feature in modo che abbiano **scale simili**, migliorando **prestazioni** e **stabilità dell’addestramento** del modello.

**Quattro tecniche comuni** (p. 59):

| Tecnica | Formula | Quando usarla |
|---|---|---|
| **scalatura in un intervallo** (min-max) | $x' = \dfrac{x - x_{\min}}{x_{\max} - x_{\min}}$ | valori distribuiti in modo abbastanza uniforme, senza outlier estremi |
| **clipping** | $x' = \begin{cases} T & \text{se } x > T \\ x & \text{altrimenti} \end{cases}$ | **outlier estremi** da contenere (p. 62) |
| **scalatura logaritmica** | $x' = \log(x)$ | distribuzioni a **legge di potenza** (p. 63) |
| **z-score** | $x' = \dfrac{x - \mu}{\sigma}$ | distribuzioni senza outlier estremi; media 0 e deviazione standard 1 |

Le formule di min-max e z-score sono quelle standard (nota didattica); la regola del clipping è quella della slide.

**Una nota su z-score e min-max** (p. 60): la slide confronta graficamente le due trasformazioni sulla stessa distribuzione.

<!-- SOURCE_VISUAL id="IS10-V24" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="60" type="grafico" description="Confronto tra normalizzazione z-score e min-max sulla stessa distribuzione" reason="Distribuzioni trasformate visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempio: il prezzo delle auto** (p. 61). A **0 €** non c’è nessuna auto, a **12 000 €** ce ne sono alcune, a **250 000 €** solo pochissime. La distribuzione ha una **coda lunga** verso i prezzi alti.

> 💡 **Perché min-max fallisce qui (nota didattica).** Con $x_{\min} = 5000$ € e $x_{\max} = 250\,000$ €, un’auto da 20 000 € diventa $x' = 15\,000 / 245\,000 \approx 0{,}06$: quasi tutte le auto finiscono schiacciate vicino a 0, e un solo modello di lusso decide la scala. Il logaritmo o il clipping restituiscono **risoluzione** alla parte in cui stanno quasi tutti i dati.

<!-- SOURCE_VISUAL id="IS10-V25" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="61" type="grafico" description="Distribuzione dei prezzi delle auto con coda lunga verso i valori alti" reason="Distribuzione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Clipping delle feature** (p. 62). Se il dataset contiene **outlier estremi**, si **limitano** tutti i valori sopra (o sotto) una certa soglia a un valore fisso.

> 📌 **Consiglio: clipping sullo z-score** (p. 62). Limitare i valori a $\pm N\sigma$, per esempio a $\pm 3\sigma$, dove $\sigma$ è la **deviazione standard**:
> $$x' = \min\!\big(\max(x,\; \mu - 3\sigma),\; \mu + 3\sigma\big)$$

> 💡 **Perché $3\sigma$ (nota didattica).** Per una distribuzione normale circa il **99,7%** dei valori cade entro $\pm 3\sigma$ dalla media: il clipping tocca solo i casi davvero anomali. Attenzione però: $\mu$ e $\sigma$ sono essi stessi influenzati dagli outlier, quindi con outlier molto grandi conviene stimarli su dati già ripuliti o usare mediana e IQR.

**Scalatura logaritmica** (p. 63). Si usa quando **pochi valori** hanno **moltissimi punti** e la maggior parte dei valori ne ha **pochi**: è la **distribuzione a legge di potenza**. Il logaritmo comprime la coda e rende la distribuzione più equilibrata.

> ⚠️ **Logaritmo di zero (nota didattica).** $\log(0)$ non è definito: se la feature può valere 0 (per esempio un numero di acquisti) si usa $x' = \log(1 + x)$.

<!-- SOURCE_VISUAL id="IS10-V26" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="63" type="grafico" description="Distribuzione a legge di potenza prima e dopo la scalatura logaritmica" reason="Distribuzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Analisi statistica nelle reti neurali**

_(slide pp. 64–75)_

#### **6.1. Dove guardare dentro una rete**

**Dove analizzare** (p. 65): **non solo gli input**. In una rete ci sono quattro punti di osservazione:

1. **input** $X_{ij}$;
2. **pesi** $W_{ij}$;
3. **attivazioni** $Y_{ij}$;
4. **uscite ed errori** $O_{ij}$.

<!-- SOURCE_VISUAL id="IS10-V27" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="65" type="diagramma" description="Rete neurale con i quattro punti di analisi: input, pesi, attivazioni, uscite ed errori" reason="Posizione dei punti nella rete visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. Input e pesi**

_(slide pp. 66–70)_

**1) Input $X_{ij}$** (pp. 66–67):

| Aspetto | Contenuto |
|---|---|
| **che cosa analizzare** | **distribuzione**, **outlier**, **correlazioni**, **dimensionalità** e **sparsità** delle feature di input |
| **perché** | *garbage in, garbage out*: input di scarsa qualità **limitano l’apprendimento**; individuare feature **ridondanti, rumorose o mancanti** migliora pre-elaborazione e generalizzazione |
| **come** | metriche statistiche (media, deviazione standard, minimo, massimo); **PCA** o **t-SNE** per la struttura; **matrici di correlazione**; controllo dello **sbilanciamento tra classi** |
| **visualizzazione** | istogrammi, scatter plot, boxplot; **heatmap** delle correlazioni a coppie; **t-SNE/UMAP** per embedding in 2D |

**La scalatura migliora l’apprendimento** (p. 68). Con due input $x_1$, $x_2$ e i pesi $w_1$, $w_2$, l’algoritmo di apprendimento **minimizza l’errore** $J(\mathbf{W})$ regolando **iterativamente** (discesa del gradiente) il vettore dei parametri $\mathbf{W}$. Con una **scalatura adeguata** servono **meno iterazioni** per raggiungere lo stesso minimo nello spazio dei parametri.

> 💡 **Perché la scalatura accorcia il percorso (nota didattica).** Se $x_1$ vale migliaia e $x_2$ decimi, le curve di livello di $J$ nello spazio $(w_1, w_2)$ sono **ellissi molto allungate**. Il gradiente
> $$\mathbf{W} \leftarrow \mathbf{W} - \eta\, \nabla J(\mathbf{W})$$
> punta quasi perpendicolare all’asse lungo e la discesa **zigzaga**; un passo $\eta$ adatto a una direzione è troppo grande o troppo piccolo per l’altra. Con feature sulla stessa scala le curve diventano quasi **circonferenze** e il gradiente punta verso il minimo.

<!-- SOURCE_VISUAL id="IS10-V28" source="Lesson_10_ExploratoryDataAnalysis_FeatureEngineering_DataStatisticalAnalysis.pdf" page="68" type="grafico" description="Curve di livello dell’errore J nello spazio dei pesi con e senza scalatura degli input" reason="Percorsi della discesa del gradiente visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**2) Pesi $W_{ij}$** (pp. 69–70):

| Aspetto | Contenuto |
|---|---|
| **che cosa analizzare** | **distribuzione di pesi e gradienti** nei diversi strati |
| **perché** | individuare **gradienti che svaniscono o esplodono**; rivelare **sovraparametrizzazione** o **addestramento insufficiente**; capire gli effetti della **regolarizzazione** (L1/L2) |
| **come** | media, deviazione standard, **norma massima** dei pesi; **istogramma dei pesi per strato**; **sparsità** dei pesi e potenziale di **pruning**; **entropia** o **curtosi** per rilevare il collasso dei pesi |
| **visualizzazione** | istogrammi dei pesi; **evoluzione della norma** per epoca; grafici del **flusso dei gradienti** (per esempio con TensorBoard) |

> 💡 **Regolarizzazione L1 e L2 (nota didattica).** Si aggiunge all’errore un termine che penalizza i pesi grandi:
> $$J_{L2}(\mathbf{W}) = J(\mathbf{W}) + \lambda \sum_{i} w_i^2 \qquad J_{L1}(\mathbf{W}) = J(\mathbf{W}) + \lambda \sum_{i} |w_i|$$
> L2 rimpicciolisce tutti i pesi; L1 ne porta molti **esattamente a zero**. Nell’istogramma dei pesi, L1 produce un grande picco a zero: è la **sparsità** che rende possibile il pruning.

#### **6.3. Attivazioni e uscite**

_(slide pp. 71–75)_

**3) Attivazioni $Y_{ij}$** (pp. 71–72): l’uscita di ogni neurone **dopo la funzione di attivazione** (per esempio ReLU o sigmoide), che combina **attivazione e pesi**.

| Aspetto | Contenuto |
|---|---|
| **perché** | individuare **neuroni morti** (una ReLU sempre a zero); **saturazione** (una sigmoide sempre vicina a 0 o a 1); capire la **dinamica degli strati** e la **propagazione dell’informazione** |
| **come** | **istogrammi delle attivazioni**; **entropia** della loro distribuzione; **sondare** la rete con input specifici; **attivazione media per strato** |
| **visualizzazione** | **heatmap**; **violin plot**; dinamica delle attivazioni nel tempo o per batch |

> 💡 **Neuroni morti e saturazione (nota didattica).** $\text{ReLU}(z) = \max(0, z)$ ha derivata nulla per $z < 0$: se un neurone riceve sempre input negativi, il suo gradiente è sempre zero e **non impara più**. La sigmoide $\sigma(z) = 1/(1 + e^{-z})$ ha derivata $\sigma(z)\,(1 - \sigma(z))$, che tende a 0 quando $\sigma(z)$ è vicina a 0 o a 1: il neurone **saturo** impara lentissimamente. Un istogramma delle attivazioni con tutti i valori a zero, o ammassati agli estremi, rivela subito il problema.

**4) Uscite ed errori $O_{ij}$** (pp. 73–74):

| Aspetto | Contenuto |
|---|---|
| **che cosa analizzare** | **predizioni finali** con i relativi **errori** o **livelli di confidenza** |
| **perché** | guida la **diagnosi** delle prestazioni; aiuta a individuare **bias sistematici** o **overfitting**; può guidare la **pesatura della perdita** o la **calibrazione** |
| **come** | **matrice di confusione**; accuratezza, precisione, recall e F1 **per classe**; **curve di calibrazione**; **istogrammi dell’errore** in funzione della confidenza |
| **visualizzazione** | curve **ROC** e **precision-recall**; heatmap degli errori di classificazione; distribuzione dell’uscita **softmax**; errori **positivi** contro **negativi** |

> 💡 **Calibrazione (nota didattica).** Un modello è **calibrato** se, tra tutte le predizioni fatte con confidenza 0,8, circa l’80% è corretto. Molte reti profonde sono **sovraconfidenti**: la curva di calibrazione confronta la confidenza dichiarata con l’accuratezza reale e mostra lo scarto (Lezione 05, §1.6).

**Compito per casa** (p. 75, **non d’esame**): l’esempio di pre-elaborazione dei dati della documentazione MathWorks, con **statistiche di base**, **gestione degli outlier** e **clipping**.

---

### **7. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Autocorrelazione** / **cross-correlazione** | un’immagine con se stessa, picco stretto e netto / due immagini diverse |
| **Massimo grezzo** / **massimo normalizzato** | somma che cresce con i pixel / media su $n$ pixel, confrontabile |
| **Correlazione** / **convoluzione** | indici $+x$ / indici $-x$, kernel ribaltato |
| **Kernel simile** / **kernel generico (panda)** | picchi sui pattern cercati / picchi rumorosi da rilevatore di blob |
| **Wrangling** / **EDA** / **feature engineering** | pulire e strutturare / capire che cosa c’è nei dati / progettare le feature |
| **Dominio del tempo** / **della frequenza** / **spettrogramma** | dinamica temporale / contenuto armonico / frequenze nel tempo |
| **Media e deviazione standard** / **mediana e IQR** | sensibili agli outlier / robuste agli outlier |
| **Feature engineering (A)** / **(B)** / **(C)** | una feature alla volta / tutte le feature senza etichette / tutto il dataset con le etichette |
| **Min-max** / **z-score** | intervallo $[0, 1]$, sensibile agli outlier / media 0 e deviazione 1 |
| **Clipping** / **scalatura logaritmica** | tagliare gli estremi / comprimere una coda a legge di potenza |
| **Neurone morto** / **neurone saturo** | ReLU sempre a zero / sigmoide sempre vicina a 0 o 1 |
| **Input** / **pesi** / **attivazioni** / **uscite** | qualità dei dati / gradienti e regolarizzazione / dinamica degli strati / errori e calibrazione |

---

### **8. Sintesi della lezione**

_(slide p. 76)_

> ✅ **Punti principali** (p. 76).
> - **La similarità come strumento per il deep learning**: autocorrelazione con picco netto, massimi da normalizzare per il numero di pixel, correlazione e convoluzione che differiscono solo per un segno, un kernel che cerca un pattern come fa un filtro di una CNN, difetti come mancanze nel pattern.
> - **Analisi esplorativa dei dati**: contesto dei dati, segnali in frame, dominio del tempo e della frequenza, statistica descrittiva, forma delle distribuzioni, boxplot e outlier.
> - **Feature engineering**: un ciclo iterativo su tre livelli, da una feature alla volta fino all’intero dataset con le etichette.
> - **Pre-elaborazione statistica**: scalatura in un intervallo, clipping (per esempio a $\pm 3\sigma$), scalatura logaritmica, z-score.
> - **EDA applicata alle reti neurali**: analizzare input, pesi, attivazioni e uscite, non solo gli input.
