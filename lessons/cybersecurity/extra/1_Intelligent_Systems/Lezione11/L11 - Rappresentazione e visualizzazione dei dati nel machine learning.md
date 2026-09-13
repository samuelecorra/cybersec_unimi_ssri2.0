## **Lezione 11: Rappresentazione e visualizzazione dei dati nel machine learning**

L’undicesima lezione del corso **IS4** tratta la **visualizzazione** come strumento di lavoro lungo tutto il ciclo di vita di un sistema intelligente. **Prima** dell’addestramento serve a capire i dati (EDA in Python, cluster, dataset di immagini). **Dopo** l’addestramento serve a valutare il modello, a controllare **che cosa guarda** (mappe di salienza, kernel delle CNN) e, una volta in esercizio, a sorvegliare il **drift** con indici come il **Population Stability Index**, anche per rispondere agli obblighi di monitoraggio dell’AI Act. La parte teorica riprende i principi della visualizzazione (Bertin, le variabili visive, le regole per grafici efficaci) e la parte pratica mostra grafici 2D e 3D in MATLAB, R e strumenti online.

Fonte: [PDF della Lezione 11](Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf), 100 pagine, nessun supplemento. Il codice MATLAB citato nelle slide è riportato nel testo; all’esame non si scrive codice. Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Titolo della lezione**

_(slide p. 1)_

Titolo originale: *Data Representation and Visualization for Machine Learning*.

---

### **1. La visualizzazione nel flusso di lavoro del ML**

_(slide pp. 2–8)_

La visualizzazione dei dati è un **sottopasso del passo 2**, tra EDA e feature engineering (p. 3; Lezione 10, §3.1). Ma non si usa solo lì: nel flusso di lavoro dal passo 2 al passo 5 è impiegata **soprattutto in tre momenti** (pp. 4–6):

| Momento | Passi del flusso | Scopo |
|---|---|---|
| **I** | passo 2, preparazione dei dati | capire i dati **prima** di addestrare |
| **II** | passi 3–4, modello e valutazione | capire il modello **dopo** l’addestramento (§4–§5) |
| **III** | passo 5, rilascio ed esercizio | **monitorare** il sistema in produzione (§6) |

<!-- SOURCE_VISUAL id="IS11-V01" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="4" type="diagramma" description="Passi 2-5 del flusso di lavoro del ML con i tre momenti in cui si usa la visualizzazione" reason="Collocazione dei tre momenti visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché fare EDA prima del ML?** (pp. 7–8):

- **verificare il numero** di campioni e di feature;
- individuare **valori mancanti**, **outlier** e **intervalli sbagliati**;
- capire **distribuzioni** e **scale** delle feature;
- preparare **feature engineering** e **riduzione della dimensionalità**;
- individuare **cluster** o **bias** visibili **prima** dell’addestramento;
- **evitare di addestrare** un modello su dati **fraintesi**.

La p. 8 aggiunge che le stesse analisi si possono fare **con l’aiuto del ML** stesso (per esempio un clustering, §2.2).

---

### **2. EDA in Python: un esempio**

_(slide pp. 9–19)_

#### **2.1. I comandi di base**

Un esempio semplice per rivedere le tecniche appena studiate (p. 9), in **Google Colab** con **pandas** (p. 10):

| Passo | Comando | A che cosa serve |
|---|---|---|
| importare i dati e **selezionare le feature** (p. 11) | `pd.read_csv(...)`, selezione delle colonne | caricare solo le colonne utili |
| esplorazione 1 (p. 12) | `.head()` | vedere le **prime righe** |
| esplorazione 2 (p. 13) | `.shape`, `.describe()` | **dimensioni** del dataset e **statistiche descrittive** per colonna (conteggio, media, deviazione standard, minimo, quartili, massimo) |
| esplorazione 3 (p. 14) | `.info()` | **tipi** delle colonne e valori **non nulli** |
| esplorazione 4 (p. 15) | `.isnull()` | individuare i **valori mancanti** |
| esplorazione 5 (p. 16) | `plt.hist(...)` | **istogrammi** delle distribuzioni |
| grafici (p. 17) | scatter plot delle feature | vedere relazioni e **raggruppamenti** |

> 💡 **Un idioma utile (nota didattica).** `dataset.isnull().sum()` restituisce, per ogni colonna, **quanti** valori mancano: è il «passo 0» della gestione dei dati mancanti (Lezione 06, §7.3). Il controllo della p. 13 su `.describe()` serve anche a scovare **intervalli impossibili**, come un’età minima negativa.

<!-- SOURCE_VISUAL id="IS11-V02" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="13" type="schermata" description="Output di .shape e .describe() in Colab con un controllo evidenziato" reason="Tabella di output visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V03" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="16" type="schermata" description="Istogrammi delle feature con plt.hist" reason="Istogrammi visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V04" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="17" type="grafico" description="Grafico delle feature del dataset dell’esempio" reason="Grafico visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. I cluster suggeriscono, non spiegano**

**Il clustering può dare indicazioni** (p. 18). Nel grafico dell’esempio si individua un **cluster** di clienti con **età giovane** e **punteggio di spesa alto**. Azione suggerita: inviare **spesso coupon e offerte dedicate**.

<!-- SOURCE_VISUAL id="IS11-V05" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="18" type="grafico" description="Cluster di clienti giovani con punteggio di spesa alto" reason="Cluster visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Sui cluster** (p. 19).
> - Un cluster **non è ancora una spiegazione causale**: suggerisce solo un **segmento** con comportamento simile.
> - Le azioni vanno **verificate** con test o **valutazioni controllate**: gli individui del cluster vanno **confrontati** con una **selezione casuale**, per confrontare i risultati.
> - Esempio di azione: **offerte personalizzate**, con controlli di **privacy** ed **equità**.
> - I principali metodi di clustering saranno trattati nelle lezioni successive.

> 💡 **Il confronto controllato (nota didattica).** Se si mandano coupon solo ai giovani «alti spendenti» e questi comprano, non si sa se è merito del coupon: comprerebbero comunque molto. Si divide invece il cluster a caso in due gruppi, uno riceve il coupon e l’altro no, e si confrontano gli acquisti. È la stessa distinzione tra **correlazione e causalità** della Lezione 05, §1.5.

---

### **3. Che cosa si visualizza nel ML**

_(slide pp. 20–25)_

**Usi della visualizzazione nel ML** (p. 20):

- **dati di addestramento**;
- **prestazioni del modello**;
- **interpretabilità** e **ispezione del modello**;
- **dati ad alta dimensionalità**;
- **didattica e comunicazione**.

**Dal problema $Y = \text{FUNC}(X)$** (pp. 21–23; Lezione 10, §4.2) la difficoltà cresce con il numero di dimensioni coinvolte:

| Che cosa si esplora | Strumenti | La domanda |
|---|---|---|
| **una feature alla volta** (p. 21) | statistica descrittiva, **istogrammi**, **boxplot**, elaborazione e normalizzazione semplici, ricerca di outlier ed errori | — |
| **tutte le feature insieme** (p. 22) | — | come capire che cosa succede **in molte dimensioni**? |
| **tutte le feature e le etichette** (p. 23) | — | come capirlo in molte dimensioni **e con le etichette**? |

**E le immagini?** (p. 24). Un esempio è la visualizzazione del dataset **CIFAR-10**.

> 💡 **CIFAR-10 (nota didattica).** È un dataset di **60 000 immagini a colori 32 × 32** pixel divise in **10 classi** (aereo, automobile, uccello, gatto, cervo, cane, rana, cavallo, nave, camion). Visualizzarlo significa di solito mostrare una griglia di esempi per classe, oppure proiettare le immagini in 2D (per esempio con t-SNE) per vedere quali classi si mescolano.

**Facets** (p. 25): uno strumento **open source** per visualizzare i **dati di addestramento** del machine learning.

<!-- SOURCE_VISUAL id="IS11-V06" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="24" type="immagine" description="Visualizzazione del dataset CIFAR-10" reason="Visualizzazione presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V07" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="25" type="schermata" description="Facets, strumento open source per visualizzare i dati di addestramento" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Dopo l’addestramento: prestazioni e drift**

_(slide pp. 26–33)_

#### **4.1. Perché fare EDA dopo il ML**

Riguarda i **passi 3–4** del flusso di lavoro (p. 26). Serve per (p. 27):

- **analisi degli errori** e **interpretabilità**;
- **valutazione delle prestazioni** del modello;
- **confronto** tra modelli e iperparametri;
- analisi di **confidenza**, **calibrazione** e **incertezza**;
- **dashboard** di rilascio e **monitoraggio del drift**.

**Dashboard di rilascio e monitoraggio del drift** (p. 28):

- **monitorare dati e prestazioni** del modello **in produzione**;
- rilevare il **data drift** e il **concept drift**;
- **attivare allarmi** e intervenire **presto**.

> 💡 **Data drift e concept drift (nota didattica).** Con il **data drift** cambia la distribuzione degli **input** $P(X)$: per esempio arrivano clienti più anziani di quelli visti in addestramento. Con il **concept drift** cambia la **relazione** tra input e output $P(Y \mid X)$: gli stessi clienti, a parità di caratteristiche, iniziano a comportarsi diversamente. Il primo si vede guardando solo gli input; il secondo richiede le etichette vere, o almeno un segnale sulle uscite.

<!-- SOURCE_VISUAL id="IS11-V08" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="28" type="schermata" description="Dashboard di monitoraggio del drift con il PSI per feature" reason="Dashboard visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Il punto di vista dell’AI Act**

> 📌 **Rilevare il drift dopo il rilascio** (p. 29). Il **monitoraggio post-rilascio** è richiesto per i **sistemi di AI ad alto rischio** (Lezione 02, §1.4). Si fa tipicamente analizzando:
> - l’**input** del modello, per esempio con il **Population Stability Index**;
> - l’**output** del modello, per esempio con il **drift della distribuzione delle predizioni**.

> ⚠️ **Alto rischio e GPAI.** La slide afferma che l’AI Act impone un monitoraggio continuo e rigoroso delle prestazioni per i sistemi «ad alto rischio» e per i modelli di **AI per finalità generali** (GPAI). Nel regolamento il **sistema di monitoraggio post-commercializzazione** è un obbligo specifico dei fornitori di sistemi **ad alto rischio**; per i modelli GPAI gli obblighi riguardano soprattutto documentazione e, per quelli **a rischio sistemico**, valutazioni, gestione dei rischi e segnalazione degli incidenti.

#### **4.3. Il Population Stability Index**

_(slide pp. 30–31)_

> 📌 **Population Stability Index** (PSI, pp. 28, 30). Un indice numerico che confronta la **distribuzione dei dati di addestramento** (riferimento) con la **distribuzione attuale dei dati in produzione**. Aiuta a rilevare il **drift degli input**, che può portare a **bias**, **degrado dell’accuratezza** o necessità di **ricalibrare e riaddestrare**: verifica se il modello rilasciato riceve ancora dati **familiari**.
>
> Si divide la feature in intervalli (bin); se $p_i$ è la **percentuale** di campioni nel bin $i$ nei dati di riferimento e $q_i$ la percentuale nello stesso bin nei dati di produzione (per esempio dell’ultimo mese):
> $$\text{PSI} = \sum_{i} (q_i - p_i) \ln\frac{q_i}{p_i}$$

La formula nella slide è un’immagine; qui è riportata nella forma standard, coerente con le definizioni di $p_i$ e $q_i$ del testo. La p. 31 applica il PSI a una **feature di input**.

> 💡 **Esempio numerico e soglie (nota didattica).** Con tre bin, riferimento $p = (0{,}5;\ 0{,}3;\ 0{,}2)$ e produzione $q = (0{,}3;\ 0{,}3;\ 0{,}4)$:
> $$\text{PSI} = (-0{,}2)\ln\tfrac{0{,}3}{0{,}5} + 0 + (0{,}2)\ln\tfrac{0{,}4}{0{,}2} \approx 0{,}102 + 0 + 0{,}139 = 0{,}241$$
> Ogni termine è **sempre non negativo**, perché $(q_i - p_i)$ e $\ln(q_i/p_i)$ hanno lo stesso segno. Una regola pratica diffusa nell’industria: PSI < 0,1 nessuno spostamento rilevante; 0,1–0,25 spostamento **moderato** da osservare; > 0,25 spostamento **significativo**, da indagare. Il valore 0,241 dell’esempio è quindi al limite. Se un bin è vuoto ($q_i = 0$) il logaritmo non è definito: si usa una piccola costante al posto dello zero.

<!-- SOURCE_VISUAL id="IS11-V09" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="30" type="formula" description="Formula del PSI con p_i e q_i e confronto delle distribuzioni su un mese" reason="Formula resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V10" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="31" type="grafico" description="PSI applicato a una feature di input: distribuzione di riferimento contro produzione" reason="Istogrammi a confronto visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.4. Il drift della distribuzione delle predizioni**

_(slide pp. 32–33)_

> 📌 **Prediction drift non è feature drift** (p. 32).
> - Il **feature drift** riguarda la distribuzione dei **dati di input**.
> - Il **prediction drift** riguarda la distribuzione delle **uscite** del modello.
> - Il prediction drift è un **segnale compatto**, dopo il rilascio, che il **comportamento del modello** potrebbe cambiare, **anche prima** di avere tutte le **etichette vere**.

**Come si misura** (p. 33). In un tipico sistema di classificazione si monitora la **distribuzione dell’uscita** (il punteggio **prima** della decisione con la soglia) su tutti gli input $X$ di un intervallo di tempo, e le si applica lo **stesso PSI**: qui il metodo è applicato al **valore di uscita** invece che a una feature.

<!-- SOURCE_VISUAL id="IS11-V11" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="33" type="grafico" description="PSI applicato alla distribuzione delle uscite di un classificatore" reason="Distribuzioni delle uscite visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché guardare le uscite.** In un sistema di concessione del credito le etichette vere (il cliente ha restituito il prestito?) arrivano dopo mesi. Se nel frattempo la quota di punteggi alti passa dal 20% al 45%, qualcosa è cambiato: negli input, nel modello o nel mondo. È un allarme precoce che non richiede di aspettare la ground truth.

---

### **5. Controllare che cosa guarda il modello**

_(slide pp. 34–41)_

#### **5.1. Mappe di salienza**

> 📌 **Mappe di salienza** (p. 35).
> - **Evidenziano le regioni dell’input** che influenzano la predizione.
> - Aiutano a scoprire **correlazioni spurie** o **bias** indesiderati.
> - Supportano l’**analisi degli errori** e l’**interpretabilità** del modello.
> - Sono uno **strumento diagnostico**, **non spiegazioni definitive**.

**Come si costruiscono** (pp. 36–37, esempio PAIR). Si vuole capire quali feature di input (i **pixel**, per le immagini) sono state importanti per la predizione. La mappa si costruisce con i **gradienti dell’uscita rispetto all’input**: si prende il **gradiente del neurone di predizione di una classe** rispetto ai pixel. Il gradiente del punteggio della classe misura **quanto cambia** il punteggio quando **ogni pixel** varia leggermente. Le aree con gradiente grande sono quelle rilevanti per la classificazione (dettagli nelle lezioni successive).

> 📌 **Gradiente e SmoothGrad (formule standard, nota didattica).** Per un’immagine $x$ e il punteggio $S_c(x)$ della classe $c$, la mappa di salienza è
> $$M_c(x) = \left| \frac{\partial S_c(x)}{\partial x} \right|$$
> La tecnica **SmoothGrad** (p. 36) spesso **riduce molto il rumore** della mappa: aggiunge **rumore gaussiano** pixel per pixel a **molte copie** dell’immagine e fa semplicemente la **media** dei gradienti risultanti:
> $$\hat{M}_c(x) = \frac{1}{n} \sum_{k=1}^{n} M_c\big(x + \varepsilon_k\big), \qquad \varepsilon_k \sim \mathcal{N}(0, \sigma^2)$$

<!-- SOURCE_VISUAL id="IS11-V12" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="36" type="immagini" description="Esempio di mappe di salienza con gradiente semplice e SmoothGrad (PAIR)" reason="Mappe visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Come usarle** (pp. 38–39). Due reti classificano la stessa immagine:

| Rete | Predizione | Mappa di salienza |
|---|---|---|
| **NN1** | cane = **0,95** | evidenzia pixel **sbagliati**, non il cane |
| **NN2** | cane = **0,92** | evidenzia il cane |

Qual è la rete migliore? NN1 ha la confidenza più alta, ma **la predizione è corretta guardando i pixel sbagliati**. Ci si può fidare di questo modello, **anche se sembra accurato**?

> 💡 **Il collegamento con il dilemma M1/M2.** È lo stesso problema del rilevatore di pedoni della Lezione 05, §1.5: un modello può avere prestazioni migliori sfruttando lo sfondo o un artefatto del dataset (data leakage, Lezione 07, §3). La mappa di salienza è uno dei modi per accorgersene prima del rilascio.

<!-- SOURCE_VISUAL id="IS11-V13" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="38" type="immagini" description="Due reti che riconoscono il cane con confidenza 0,95 e 0,92" reason="Immagini di input visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V14" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="39" type="immagini" description="Mappe di salienza delle due reti: NN1 guarda i pixel sbagliati" reason="Mappe di salienza visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Kernel delle CNN e visual analytics**

**Visualizzare i kernel delle CNN: quali pattern rilevano?** (p. 40). Nella figura i **quadrati grigi** sono i **kernel**; i **quadrati colorati** sono gli **esempi** di un dataset di immagini che producono l’**uscita più alta** di ciascun kernel. È la versione appresa del «kernel che cerca un pattern» della Lezione 10, §2.3.

**Visualizzazioni dei modelli** (p. 41): forse **troppo dense**? Esempio: **DQNViz**, un approccio di *visual analytics* per capire le **Deep Q-Networks** (DQN), un tipo di modello di **apprendimento per rinforzo profondo** (Lezione 03, §3.4).

<!-- SOURCE_VISUAL id="IS11-V15" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="40" type="immagini" description="Kernel di una CNN (quadrati grigi) ed esempi che li attivano al massimo (quadrati colorati)" reason="Kernel ed esempi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V16" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="41" type="schermata" description="DQNViz: visual analytics per le Deep Q-Networks" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Passo 5: dashboard di monitoraggio**

_(slide pp. 42–46)_

**Dashboard di BI e monitoraggio con assistenti AI** (pp. 42–43).

> 📌 **Osservabilità** (p. 43). Monitorare un sistema rilasciato attraverso **segnali misurabili**, per capirne il **comportamento**, **rilevare guasti** e supportare **azioni correttive**.

| Strumento | Produttore | Caratteristiche (slide) |
|---|---|---|
| **TensorBoard** (p. 44) | Google | una suite di strumenti di visualizzazione per **capire, fare debug e ottimizzare** i modelli: visualizzare il **grafo** TensorFlow, tracciare **metriche quantitative** sull’esecuzione e mostrare dati aggiuntivi come le **immagini** che lo attraversano |
| **Visdom** (p. 45) | Meta AI | genera visualizzazioni ricche di **dati in tempo reale**, per seguire esperimenti eseguiti su **server remoti**; si vedono **nel browser** e si condividono facilmente |
| **Microsoft Power BI** (p. 46) | Microsoft | piattaforma unificata e scalabile di **business intelligence** self-service e aziendale per collegarsi a qualsiasi dato e visualizzarlo |

<!-- SOURCE_VISUAL id="IS11-V17" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="44" type="schermata" description="TensorBoard con metriche di addestramento" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Principi della visualizzazione dei dati**

_(slide pp. 47–68)_

#### **7.1. Perché quasi sempre 2D o 3D**

Gli argomenti della sezione (p. 48): che cos’è la visualizzazione e come funziona, la teoria dell’immagine, la definizione di rappresentazione per immagini, gli usi attuali, i benefici e alcuni esempi.

**Perché (quasi) solo grafici 2D e 3D?** (p. 49):

- viviamo nello **spazio-tempo**, un modello matematico che fonde **tre dimensioni spaziali** e **una temporale** in un continuo quadridimensionale;
- ma la nostra **percezione è limitata**: solo i grafici **2D** e (raramente) **3D** si valutano e si capiscono facilmente;
- per questo è così importante la **riduzione della dimensionalità**: per esempio la **PCA** trasforma 4 feature $F_1, \dots, F_4$ in 4 nuove feature $F_1', \dots, F_4'$, di cui **2 salienti** si possono disegnare;
- e conviene aggiungere informazione **oltre i 2–3 assi** (colore, forma, dimensione: §7.2).

<!-- SOURCE_VISUAL id="IS11-V18" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="49" type="schema" description="PCA da 4 feature a 2 feature salienti per la visualizzazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Bertin e le variabili visive**

> 📌 **Visualizzazione dei dati** (p. 50). Il processo di **convertire dati grezzi** in **immagini di informazione facilmente comprensibili**, che permettono **decisioni rapide ed efficaci**. All’inizio del Novecento gli psicologi della **Gestalt** osservarono che, quando degli elementi si riuniscono in una figura, la figura acquista una **salienza percettiva** che **supera la somma delle parti** (Lezione 02, §6).

**Dai dati alle immagini** (p. 51). **Jacques Bertin**, autore del classico *Semiologia della grafica*, afferma che la «trasformazione dai numeri all’intuizione richiede **due fasi**»:

$$\text{dati / processi} \;\xrightarrow{\;\text{algoritmo}\;}\; \text{immagine} \;\xrightarrow{\;\text{percezione}\;}\; \text{intuizione}$$

> 📌 **Le 7 variabili visive di Bertin** (p. 52). **Posizione**, **forma**, **orientamento**, **colore**, **texture**, **valore** (chiaro-scuro), **dimensione**; combinate con una **semantica visiva** che collega gli **attributi dei dati** agli **elementi visivi**.

Le pp. 53–54 mostrano come si **codificano i dati con indizi visivi**, e che **non tutte le caratteristiche visive sono dello stesso tipo**.

> 💡 **Non tutte le variabili sono uguali (nota didattica).** Bertin distingue le variabili per ciò che permettono di leggere: la **posizione** e la **dimensione** mostrano **quantità** e **ordine**; il **valore** mostra un ordine ma non quantità precise; **colore** (tinta), **forma** e **texture** distinguono **categorie** ma non ordinano. Gli studi di Cleveland e McGill confermano che confrontiamo con più precisione **posizioni su una scala comune** che lunghezze, angoli, aree o colori. Per questo un grafico a barre si legge meglio di un grafico a torta.

<!-- SOURCE_VISUAL id="IS11-V19" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="53" type="infografica" description="Codifica dei dati con indizi visivi" reason="Esempi di codifica visibili solo nell’infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V20" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="54" type="infografica" description="Le caratteristiche visive non sono tutte dello stesso tipo" reason="Classificazione delle variabili visibile solo nell’infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3. La teoria dell’immagine**

**L’elaborazione visiva avviene in tre passi** (p. 55):

1. **formazione dell’immagine sulla retina**;
2. **scomposizione** dell’informazione retinica in un insieme di **rappresentazioni specializzate**;
3. **ricomposizione** dell’informazione nella **percezione degli oggetti**.

> 📌 **L’immagine come unità percettiva** (p. 56). Il concetto chiave di Bertin, da cui la teoria prende il nome, è l’**immagine**: semplificando, l’**unità percettiva fondamentale** di una visualizzazione. Una visualizzazione **ideale contiene una sola immagine**, per ottimizzare l’**efficienza**, cioè la velocità con cui l’osservatore estrae l’informazione. La domanda della slide: **dove stai perdendo energia?**

**Una fusione di discipline** (p. 57). La visualizzazione dell’informazione unisce **grafica**, **visualizzazione scientifica**, **gestione dei database** e **interazione uomo-macchina**. Nelle proprie applicazioni va usata per comunicare risultati complessi come **immagini comprensibili**, migliorando l’**interfaccia uomo-macchina** (HMI).

**Importante per utenti e progettisti** (p. 58): la visualizzazione fornisce **interfacce grafiche intuitive** e aiuta gli utenti a ricavare informazioni utili per decisioni **più rapide e informate**. Serve agli utenti, **ma anche al progettista**.

**Quale grafico?** (pp. 59–60): una guida alla scelta del tipo di grafico.

<!-- SOURCE_VISUAL id="IS11-V21" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="59" type="infografica" description="Guida alla scelta del tipo di grafico" reason="Guida presente solo come infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V22" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="60" type="infografica" description="Guida alla scelta del tipo di grafico (continua)" reason="Guida presente solo come infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4. Benefici e buone regole**

_(slide pp. 61–68)_

**Benefici della visualizzazione** (pp. 61, 66):

- vedere i dati da **prospettive diverse**;
- **interpretare grandi quantità** di dati;
- **individuare eccezioni**;
- analizzare **pattern visivi**, esplorare **tendenze** navigando i dati;
- tradurre i pattern in **intuizioni**: uno strumento decisionale molto efficace, che **riduce tempo e difficoltà** dal dato alla decisione;
- vedere **influenze** (uscite contro ingressi) altrimenti difficili da trovare;
- con tutti i dati disponibili è difficile trovare le **sfumature** che fanno la differenza.

«**Un’immagine vale più di mille parole**» (p. 62, modo di dire inglese).

**Mostrare la confidenza** (pp. 63–64). Le slide mostrano grafici con popolazioni **uniformi** e **non uniformi**, e gli **intervalli di confidenza** di una rete addestrata: dove **mancano dati**, gli intervalli sono **più ampi**.

> 💡 **Perché gli intervalli si allargano (nota didattica).** Una rete addestrata su dati concentrati in una zona dello spazio di input è «sicura» lì e **incerta** altrove: nelle regioni con pochi esempi molte funzioni diverse spiegano ugualmente bene i dati. Mostrare l’intervallo, e non solo la curva media, avverte l’utente di non fidarsi delle predizioni nelle zone scoperte (Lezione 06, §7.2, il buco topologico).

<!-- SOURCE_VISUAL id="IS11-V23" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="63" type="grafico" description="Confidenza con popolazione uniforme e non uniforme" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V24" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="64" type="grafico" description="Intervalli di confidenza di una rete addestrata, più ampi dove mancano i dati" reason="Intervalli visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La p. 65 mostra esempi di **annotazioni**, **mappe di colore** e **limiti** degli assi dalla galleria di grafici MATLAB.

> 📌 **Conclusioni: un grafico dovrebbe…** (pp. 67–68).
> - **mostrare i dati**;
> - indurre a pensare alla **sostanza**, non alla metodologia, al design grafico o alla tecnologia di produzione;
> - **evitare di distorcere** ciò che i dati dicono;
> - presentare **molti numeri in poco spazio**;
> - rendere **coerenti** grandi insiemi di dati;
> - supportare l’**armonizzazione** e il **confronto** dei dati;
> - incoraggiare l’occhio a **confrontare** dati diversi;
> - rivelare i dati a **più livelli di dettaglio**, dalla panoramica alla struttura fine;
> - avere uno **scopo ragionevolmente chiaro**: descrizione, esplorazione, tabulazione o decorazione;
> - essere **integrato** con le descrizioni **statistiche e verbali** del dataset.
>
> Il nemico è il **troppo design grafico**.

> 💡 **La fonte (nota didattica).** L’elenco riprende quasi alla lettera i principi di **Edward Tufte**, *The Visual Display of Quantitative Information* (1983).

---

### **8. Strumenti per la visualizzazione**

_(slide pp. 69–81)_

**La struttura di un grafico semplice ma completo** (p. 70): la slide ne mostra gli elementi.

> 💡 **Gli elementi di un grafico completo (nota didattica).** Titolo; assi con **etichetta e unità di misura**; scala adatta ai dati; legenda quando ci sono più serie; eventuali annotazioni sui punti importanti; indicazione dell’incertezza (barre d’errore o bande) quando disponibile.

<!-- SOURCE_VISUAL id="IS11-V25" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="70" type="grafico" description="Struttura di un grafico semplice ma completo" reason="Elementi del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempi** (pp. 71–75):

| Pagina | Esempio |
|---|---|
| p. 71 | **distribuzioni nel tempo**: assenza di dati, vincitori, eventi |
| p. 72 | **fenomeni complessi** |
| p. 73 | **vaccinazione contro il morbillo** (dati OMS) |
| p. 74 | grafico nel grafico, vettori in linee 3D (galleria MATLAB) |
| p. 75 | strumenti online per la visualizzazione **interattiva**, per esempio **observablehq.com** |
| pp. 76–78 | esempi grafici senza testo |

**Vaccino sì o no? Il morbillo** (p. 73). Secondo l’**Organizzazione Mondiale della Sanità**, pur essendo disponibile un vaccino **sicuro ed economico**, nel **2018** ci sono stati oltre **140 000 morti** per morbillo nel mondo, per lo più **bambini sotto i cinque anni**. La vaccinazione ha prodotto un **calo del 73%** delle morti per morbillo tra il **2000** e il **2018**. Il grafico evidenzia che, tra i contagiati, **una percentuale morirà**.

<!-- SOURCE_VISUAL id="IS11-V26" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="71" type="grafico" description="Distribuzioni nel tempo con assenza di dati, vincitori ed eventi" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V27" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="72" type="grafico" description="Visualizzazione di fenomeni complessi" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V28" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="73" type="infografica" description="Vaccinazione contro il morbillo: dati OMS 2000-2018" reason="Infografica visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V29" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="76" type="grafico" description="Esempio di visualizzazione senza testo (1)" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V30" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="77" type="grafico" description="Esempio di visualizzazione senza testo (2)" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V31" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="78" type="grafico" description="Esempio di visualizzazione senza testo (3)" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Grafici bellissimi, dati sbagliati** (p. 79). La potenza grafica di grafici e diagrammi è **inutile** se i dati:
> - **non sono rappresentativi**;
> - sono **corrotti**;
> - **non sono pre-elaborati** correttamente;
> - sono **FALSI**, per esempio per una data augmentation **eccessiva o sbagliata**.

**La libreria ggplot2 di R** (pp. 80–81). **R** è un linguaggio di programmazione e un ambiente software **libero** per il **calcolo statistico** e la **grafica**, sostenuto dalla R Foundation for Statistical Computing; **ggplot2** è la sua libreria grafica più diffusa.

---

### **9. Visualizzazione 3D in MATLAB**

_(slide pp. 82–96)_

#### **9.1. Superfici da matrici 2D**

«Il meglio che si può fare in 3D» (p. 82). La p. 83 mostra una **moneta scansionata in 3D** con dispositivi di acquisizione 3D.

**Quattro grafici della stessa moneta** (p. 84):

```matlab
load penny ;
P = flipud(P);
subplot(2,2,1)
surf(P);          % superficie colorata
subplot(2,2,2)
mesh(P);          % reticolo
subplot(2,2,3)
pcolor(P);        % mappa di colore vista dall'alto
shading flat
subplot(2,2,4)
contour(P);       % curve di livello
```

`flipud` ribalta la matrice dall’alto in basso, perché le righe della matrice e l’asse $y$ del grafico sono ordinati in modo opposto.

<!-- SOURCE_VISUAL id="IS11-V32" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="84" type="grafici" description="Quattro rappresentazioni della moneta: surf, mesh, pcolor, contour" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Superfici e isosuperfici** (p. 85). Le **decisioni dei sistemi intelligenti** si possono rappresentare come **superfici** negli spazi di input interni: la frontiera di decisione di un classificatore con tre feature è una superficie nello spazio 3D.

**Dati 3D nel tempo** (p. 86): rappresentazioni del **monte Sant’Elena**, nello stato di Washington, **prima e dopo l’eruzione del 1980**.

<!-- SOURCE_VISUAL id="IS11-V33" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="85" type="grafico" description="Isosuperficie come rappresentazione delle decisioni di un sistema intelligente" reason="Superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V34" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="86" type="grafici" description="Monte Sant’Elena prima e dopo l’eruzione del 1980" reason="Superfici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Istogramma bivariato 3D** (p. 87), con i dati del **bike sharing di Boston**:

```matlab
% Create bivariate histogram plot using the histogram2 function
histogram2(rideData.Duration, rideData.birth_date, 'BinWidth', [2 2])
xlabel('Length of Ride')
ylabel('Birth Year')
zlabel('Number of Rides')
title('Ride counts based on ride length and the age of the rider')
view(17,30)   % Adjust view
```

#### **9.2. Ottimizzazione, vettori e gradienti**

**Compito: trovare il massimo di una funzione 2D** (p. 88). Si considera la funzione `peaks`, la si disegna e se ne trova il massimo:

```matlab
[Z] = peaks;                     % it creates a smooth curved surface
surf(Z); xlabel('x'); ylabel('y'); zlabel('z');
Zmax = max(max(Z))
[ymax, xmax] = find(Z == Zmax)
hold on;
plot3( xmax, ymax, Zmax, 'hg', 'Markersize', 15 );
hold off;
```

> 📌 **Apprendere significa ottimizzare** (p. 88): trovare un **minimo** o un **massimo** di una funzione (Lezione 04, §4.4).

> 💡 **Righe e colonne (nota didattica).** `find(Z == Zmax)` con due uscite restituisce **riga** e **colonna**: la riga corrisponde all’asse $y$, la colonna all’asse $x$. Per questo il codice scrive `[ymax, xmax]` e poi disegna `plot3(xmax, ymax, ...)`. È lo stesso scambio di convenzioni visto con `ind2sub` nella Lezione 09, §3.4.

**Vettori in 3D** (p. 89): le **normali** alla superficie $z = x\, e^{-x^2 - y^2}$, per mostrare una proprietà complessa di un singolo punto come un vettore.

```matlab
[x,y] = meshgrid(-2:.2:2,-1:.15:1);
z = x .* exp(-x.^2 - y.^2);
[u,v,w] = surfnorm(x,y,z);
quiver3(x,y,z,u,v,w);
hold on, surf(x,y,z), hold off
```

**Una proprietà mappata sul colore** (p. 90): il **colore** rappresenta la **pendenza**.

```matlab
[x,y] = meshgrid([-2:.1:2]);
Z = x.*exp(-x.^2-y.^2);
mesh(x,y,Z,abs(gradient(Z)))
colorbar; colormap jet;
```

> ⚠️ **Quale gradiente (nota didattica).** Con una sola uscita, `gradient(Z)` restituisce solo la derivata lungo $x$; il colore mostra quindi $\left|\partial Z / \partial x\right|$. Per colorare con il **modulo** del gradiente servono entrambe le componenti:
> $$\lVert \nabla Z \rVert = \sqrt{\left(\frac{\partial Z}{\partial x}\right)^2 + \left(\frac{\partial Z}{\partial y}\right)^2}$$
> in MATLAB `[gx, gy] = gradient(Z); mesh(x, y, Z, sqrt(gx.^2 + gy.^2))`.

**Ispezionare superfici di decisione con le luci** (p. 91). La funzione $\dfrac{\sin\sqrt{x^2 + y^2}}{\sqrt{x^2 + y^2}}$ disegnata con `ezsurf` sul dominio $[-6\pi, 6\pi]$, con `shading interp`, una sorgente di luce (`lightangle`) e l’illuminazione **Phong** (componenti ambientale, diffusa e speculare) per far risaltare la forma della superficie.

> ⚠️ **Funzioni datate.** Nelle versioni recenti di MATLAB `ezsurf` è sostituita da `fsurf`, e il renderer `'zbuffer'` usato in questi esempi non è più disponibile. I concetti (superficie, luce, riflessione) restano validi.

**Mostrare il processo di ottimizzazione** (p. 92): esempio di **discesa del gradiente stocastica** in un problema di ottimizzazione **non convesso**, dove il percorso può fermarsi in minimi locali.

<!-- SOURCE_VISUAL id="IS11-V35" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="88" type="grafico" description="Funzione peaks con il massimo evidenziato" reason="Superficie e punto visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V36" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="92" type="grafico" description="Discesa del gradiente stocastica in un problema non convesso" reason="Percorso dell’ottimizzazione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.3. Dati volumetrici: fette e isosuperfici**

**Velocità in un getto di fluido** (p. 93). Con i dati di esempio `flow` (velocità in un getto d’acqua in un contenitore senza pareti) si generano **fette** del volume: una fetta inclinata di 45° ottenuta ruotando una superficie piana (`surf`, `rotate`), più tre fette sui piani $x_{\max}$, $y_{\max}$ e $z_{\min}$ (`slice`), colorate con interpolazione, con proporzioni reali (`daspect`), prospettiva, luce e mappa di colore.

> 📌 **In 3D meglio proiezioni e fette** (p. 94). È **molto difficile** disegnare una feature su **tre assi**, perché i **voxel si coprono a vicenda**. Spesso è meglio usare **proiezioni** e **fette**.

**Risonanza magnetica** (p. 95). A partire da una matrice 3D `D` di dimensione **128 × 128 × 27** (esempio `mri`):

| Grafico | Funzioni principali | Che cosa mostra |
|---|---|---|
| sinistra | `contourslice(D, [], [], [1,12,19,27], 8)` | **curve di livello** su quattro fette orizzontali |
| destra | `smooth3`, `isosurface`, `isocaps`, `isonormals`, `lighting phong` | **isosuperficie** del volume levigato, con le «coperture» sui tagli e illuminazione |

**La scelta del formato** (p. 96) dipende **molto** dall’**obiettivo** della visualizzazione.

<!-- SOURCE_VISUAL id="IS11-V37" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="94" type="grafico" description="Fette di un volume 3D per evitare l’occlusione tra voxel" reason="Fette visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Slide divertenti (non d’esame)**

_(slide pp. 97–99)_

Prima di chiudere, alcune slide umoristiche, **non oggetto d’esame** (p. 97): si possono confrontare i diversi 007? E stili di vita diversi? Siete creativi?

**Anche per «applicazioni» molto particolari** (p. 98), la scelta di uno **spazio di input** adeguato permette **confronti** e **intuizioni**.

**Come trovare la persona creativa più simile a te?** (p. 99):

1. decidere lo **spazio di input**;
2. decidere la **metrica** (distanza);
3. **scorrere** tutti i candidati per trovare il più vicino.

Così si trova il «**primo vicino più prossimo**» (*First Nearest Neighbor*), un metodo tipico di ML. **L’allineamento** delle figure è critico: senza, il confronto sarebbe impossibile.

<!-- SOURCE_VISUAL id="IS11-V38" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="98" type="illustrazione" description="Uso umoristico della visualizzazione con uno spazio di input per confronti" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **EDA prima del ML** / **dopo il ML** | capire i dati prima di addestrare / valutare, interpretare e monitorare il modello |
| **Cluster** / **causa** | segmento con comportamento simile / effetto dimostrato con un confronto controllato |
| **Data drift** / **concept drift** | cambia la distribuzione degli input / cambia la relazione tra input e output |
| **Feature drift** / **prediction drift** | distribuzione degli input / distribuzione delle uscite, segnale precoce senza etichette |
| **$p_i$** / **$q_i$** nel PSI | percentuale nel bin dei dati di riferimento / dei dati di produzione |
| **Mappa di salienza** / **spiegazione** | strumento diagnostico basato sul gradiente / giustificazione definitiva (che la mappa non è) |
| **Gradiente semplice** / **SmoothGrad** | mappa rumorosa / media dei gradienti su copie rumorose |
| **Confidenza alta** / **modello affidabile** | NN1 con 0,95 / NN2 che guarda i pixel giusti |
| **TensorBoard** / **Visdom** / **Power BI** | strumenti di Google per l’addestramento / dati live di Meta AI su server remoti / business intelligence di Microsoft |
| **Posizione e dimensione** / **colore e forma** | mostrano quantità e ordine / distinguono categorie |
| **`surf`** / **`mesh`** / **`pcolor`** / **`contour`** | superficie colorata / reticolo / mappa vista dall’alto / curve di livello |
| **Grafico 3D pieno** / **fette e proiezioni** | voxel che si coprono / parti interne visibili |

---

### **12. Sintesi della lezione**

_(slide p. 100)_

> ✅ **Punti principali** (p. 100).
> - **Come usare le visualizzazioni nel ML**, in tre momenti: prima dell’addestramento, dopo l’addestramento, in esercizio.
> - **Input e dataset di immagini**: EDA con pandas, istogrammi, cluster da verificare con confronti controllati, CIFAR-10 e Facets.
> - **Dashboard**: monitoraggio del drift con il PSI su input e uscite, anche per gli obblighi dell’AI Act; TensorBoard, Visdom, Power BI.
> - **Mappe di salienza**: controllare **che cosa guarda** il modello, anche quando è accurato.
> - **Kernel**: quali pattern rilevano i filtri di una CNN.
> - **Buone regole di visualizzazione**: le variabili visive di Bertin, i principi di Tufte, grafici 2D e 3D con fette e proiezioni.
> - **Evitare grafici «divertenti»** e scegliere con cura le caratteristiche visive: **un concetto alla volta, in una sola immagine efficace**.
