## **Lezione 11: Rappresentazione e visualizzazione dei dati nel machine learning**

La visualizzazione sostiene la comprensione dei dati, del modello, delle prestazioni e del sistema in esercizio. La scelta del grafico parte dalla domanda e dal messaggio; le codifiche visive hanno precisioni diverse e possono rivelare o nascondere gruppi, incertezza e confondenti. Gli esempi passano dalle proiezioni dei dati e dai grafici classici al monitoraggio industriale, alla diagnosi di reti neurali e alla produzione assistita di figure tramite agenti. L’automazione accelera il disegno, mentre la formulazione della domanda e la verifica dei dati restano responsabilità di chi progetta.


### **0. Titolo della lezione**



---

### **1. La visualizzazione nel flusso di lavoro del ML**


La visualizzazione dei dati è un **sottopasso del passo 2**, tra EDA e feature engineering. Ma non si usa solo lì: nel flusso di lavoro dal passo 2 al passo 5 è impiegata **soprattutto in tre momenti**:

| Momento | Passi del flusso | Scopo |
|---|---|---|
| **I** | passo 2, preparazione dei dati | capire i dati **prima** di addestrare |
| **II** | passi 3–4, modello e valutazione | capire il modello **dopo** l’addestramento (§4–§5) |
| **III** | passo 5, rilascio ed esercizio | **monitorare** il sistema in produzione (§6) |

<!-- SOURCE_VISUAL id="IS11-V01" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="4" type="diagramma" description="Passi 2-5 del flusso di lavoro del ML con i tre momenti in cui si usa la visualizzazione" reason="Collocazione dei tre momenti visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché fare EDA prima del ML?**:

- **verificare il numero** di campioni e di feature;
- individuare **valori mancanti**, **outlier** e **intervalli sbagliati**;
- capire **distribuzioni** e **scale** delle feature;
- preparare **feature engineering** e **riduzione della dimensionalità**;
- individuare **cluster** o **bias** visibili **prima** dell’addestramento;
- **evitare di addestrare** un modello su dati **fraintesi**.

Le stesse analisi possono essere svolte **con l’aiuto del ML**, per esempio tramite clustering (§2.2).

---

### **2. EDA in Python: un esempio**


#### **2.1. I comandi di base**

Un esempio semplice per rivedere le tecniche appena studiate, in **Google Colab** con **pandas**:

| Passo | Comando | A che cosa serve |
|---|---|---|
| importare i dati e **selezionare le feature** | `pd.read_csv(...)`, selezione delle colonne | caricare solo le colonne utili |
| esplorazione 1 | `.head()` | vedere le **prime righe** |
| esplorazione 2 | `.shape`, `.describe()` | **dimensioni** del dataset e **statistiche descrittive** per colonna (conteggio, media, deviazione standard, minimo, quartili, massimo) |
| esplorazione 3 | `.info()` | **tipi** delle colonne e valori **non nulli** |
| esplorazione 4 | `.isnull()` | individuare i **valori mancanti** |
| esplorazione 5 | `plt.hist(...)` | **istogrammi** delle distribuzioni |
| grafici | scatter plot delle feature | vedere relazioni e **raggruppamenti** |

> 💡 **Un idioma utile (nota didattica).** `dataset.isnull().sum()` restituisce, per ogni colonna, **quanti** valori mancano: è il «passo 0» della gestione dei dati mancanti. Il controllo di `.describe()` serve anche a scovare **intervalli impossibili**, come un’età minima negativa.

<!-- SOURCE_VISUAL id="IS11-V02" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="13" type="schermata" description="Output di .shape e .describe() in Colab con un controllo evidenziato" reason="Tabella di output visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V03" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="16" type="schermata" description="Istogrammi delle feature con plt.hist" reason="Istogrammi visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V04" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="17" type="grafico" description="Grafico delle feature del dataset dell’esempio" reason="Grafico visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. I cluster suggeriscono, non spiegano**

**Il clustering può dare indicazioni**. Nel grafico dell’esempio si individua un **cluster** di clienti con **età giovane** e **punteggio di spesa alto**. Azione suggerita: inviare **spesso coupon e offerte dedicate**.

<!-- SOURCE_VISUAL id="IS11-V05" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="18" type="grafico" description="Cluster di clienti giovani con punteggio di spesa alto" reason="Cluster visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Sui cluster**.
> - Un cluster **non è ancora una spiegazione causale**: suggerisce solo un **segmento** con comportamento simile.
> - Le azioni vanno **verificate** con test o **valutazioni controllate**: gli individui del cluster vanno **confrontati** con una **selezione casuale**, per confrontare i risultati.
> - Esempio di azione: **offerte personalizzate**, con controlli di **privacy** ed **equità**.
> - I metodi di clustering individuano gruppi di campioni con caratteristiche simili.

> 💡 **Il confronto controllato (nota didattica).** Se si mandano coupon solo ai giovani «alti spendenti» e questi comprano, non si sa se è merito del coupon: comprerebbero comunque molto. Si divide invece il cluster a caso in due gruppi, uno riceve il coupon e l’altro no, e si confrontano gli acquisti. È la stessa distinzione tra **correlazione e causalità** della

---

### **3. Che cosa si visualizza nel ML**


**Usi della visualizzazione nel ML**:

- **dati di addestramento**;
- **prestazioni del modello**;
- **interpretabilità** e **ispezione del modello**;
- **dati ad alta dimensionalità**;
- **didattica e comunicazione**.

**Dal problema $Y = \text{FUNC}(X)$** la difficoltà cresce con il numero di dimensioni coinvolte:

| Che cosa si esplora | Strumenti | La domanda |
|---|---|---|
| **una feature alla volta** | statistica descrittiva, **istogrammi**, **boxplot**, elaborazione e normalizzazione semplici, ricerca di outlier ed errori | — |
| **tutte le feature insieme** | — | come capire che cosa succede **in molte dimensioni**? |
| **tutte le feature e le etichette** | — | come capirlo in molte dimensioni **e con le etichette**? |

**E le immagini?**. Un esempio è la visualizzazione del dataset **CIFAR-10**.

> 💡 **CIFAR-10 (nota didattica).** È un dataset di **60 000 immagini a colori 32 × 32** pixel divise in **10 classi** (aereo, automobile, uccello, gatto, cervo, cane, rana, cavallo, nave, camion). Visualizzarlo significa di solito mostrare una griglia di esempi per classe, oppure proiettare le immagini in 2D (per esempio con t-SNE) per vedere quali classi si mescolano.

**Facets**: uno strumento **open source** per visualizzare i **dati di addestramento** del machine learning.

<!-- SOURCE_VISUAL id="IS11-V06" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="24" type="immagine" description="Visualizzazione del dataset CIFAR-10" reason="Visualizzazione presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V07" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="25" type="schermata" description="Facets, strumento open source per visualizzare i dati di addestramento" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Dopo l’addestramento: prestazioni e drift**


#### **4.1. Perché fare EDA dopo il ML**

Riguarda i **passi 3–4** del flusso di lavoro. Serve per:

- **analisi degli errori** e **interpretabilità**;
- **valutazione delle prestazioni** del modello;
- **confronto** tra modelli e iperparametri;
- analisi di **confidenza**, **calibrazione** e **incertezza**;
- **dashboard** di rilascio e **monitoraggio del drift**.

**Dashboard di rilascio e monitoraggio del drift**:

- **monitorare dati e prestazioni** del modello **in produzione**;
- rilevare il **data drift** (cambiamento della distribuzione degli input) e il **concept drift** (cambiamento della relazione tra input e risultato);
- **attivare allarmi** e intervenire **presto**.

> 💡 **Data drift e concept drift (nota didattica).** Con il **data drift** cambia la distribuzione degli **input** $P(X)$: per esempio arrivano clienti più anziani di quelli visti in addestramento. Con il **concept drift** cambia la **relazione** tra input e output $P(Y \mid X)$: gli stessi clienti, a parità di caratteristiche, iniziano a comportarsi diversamente. Il primo si vede guardando solo gli input; il secondo richiede le etichette vere, o almeno un segnale sulle uscite.

<!-- SOURCE_VISUAL id="IS11-V08" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="28" type="schermata" description="Dashboard di monitoraggio del drift con il PSI per feature" reason="Dashboard visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Il punto di vista dell’AI Act**

> 📌 **Rilevare il drift dopo il rilascio**. Il **monitoraggio post-rilascio** è richiesto per i **sistemi di AI ad alto rischio**. Si fa tipicamente analizzando:
> - l’**input** del modello, per esempio con il **Population Stability Index**;
> - l’**output** del modello, per esempio con il **drift della distribuzione delle predizioni**.

> ⚠️ **Alto rischio e GPAI.** Il monitoraggio post-rilascio riguarda un monitoraggio continuo e rigoroso delle prestazioni per i sistemi «ad alto rischio» e per i modelli di **AI per finalità generali** (GPAI). Nel regolamento il **sistema di monitoraggio post-commercializzazione** è un obbligo specifico dei fornitori di sistemi **ad alto rischio**; per i modelli GPAI gli obblighi riguardano soprattutto documentazione e, per quelli **a rischio sistemico**, valutazioni, gestione dei rischi e segnalazione degli incidenti.

Le date di applicazione dell’AI Act sono state modificate dal Regolamento (UE) 2026/1744: gli obblighi per i sistemi ad alto rischio dell’Allegato III decorrono dal **2 dicembre 2027** e quelli per i prodotti regolamentati dell’Allegato I dal **2 agosto 2028**. Queste scadenze non trasformano il monitoraggio post-rilascio in un obbligo uniforme per ogni modello: si applicano in base alla classificazione e al ruolo dell’operatore. [Testo del Regolamento 2026/1744](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX%3A32026R1744).

#### **4.3. Il Population Stability Index**


> 📌 **Population Stability Index** (PSI). Un indice numerico che confronta la **distribuzione dei dati di addestramento** (riferimento) con la **distribuzione attuale dei dati in produzione**. Aiuta a rilevare il **drift degli input**, che può portare a **bias**, **degrado dell’accuratezza** o necessità di **ricalibrare e riaddestrare**: verifica se il modello rilasciato riceve ancora dati **familiari**.
>
> Si divide la feature in intervalli (bin); se $p_i$ è la **percentuale** di campioni nel bin $i$ nei dati di riferimento e $q_i$ la percentuale nello stesso bin nei dati di produzione (per esempio dell’ultimo mese):
> $$\text{PSI} = \sum_{i} (q_i - p_i) \ln\frac{q_i}{p_i}$$

La formula standard del PSI usa le proporzioni $p_i$ e $q_i$ nei dati di riferimento e in quelli attuali. L’esempio applica l’indice a una **feature di input**.

> 💡 **Esempio numerico e soglie (nota didattica).** Con tre bin, riferimento $p = (0{,}5;\ 0{,}3;\ 0{,}2)$ e produzione $q = (0{,}3;\ 0{,}3;\ 0{,}4)$:
> $$\text{PSI} = (-0{,}2)\ln\tfrac{0{,}3}{0{,}5} + 0 + (0{,}2)\ln\tfrac{0{,}4}{0{,}2} \approx 0{,}102 + 0 + 0{,}139 = 0{,}241$$
> Ogni termine è **sempre non negativo**, perché $(q_i - p_i)$ e $\ln(q_i/p_i)$ hanno lo stesso segno. Una regola pratica diffusa nell’industria: PSI < 0,1 nessuno spostamento rilevante; 0,1–0,25 spostamento **moderato** da osservare; > 0,25 spostamento **significativo**, da indagare. Il valore 0,241 dell’esempio è quindi al limite. Se un bin è vuoto ($q_i = 0$) il logaritmo non è definito: si usa una piccola costante al posto dello zero.

<!-- SOURCE_VISUAL id="IS11-V09" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="30" type="formula" description="Formula del PSI con p_i e q_i e confronto delle distribuzioni su un mese" reason="Formula resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V10" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="31" type="grafico" description="PSI applicato a una feature di input: distribuzione di riferimento contro produzione" reason="Istogrammi a confronto visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.4. Il drift della distribuzione delle predizioni**


> 📌 **Prediction drift non è feature drift**.
> - Il **feature drift** riguarda la distribuzione dei **dati di input**.
> - Il **prediction drift** riguarda la distribuzione delle **uscite** del modello.
> - Il prediction drift è un **segnale compatto**, dopo il rilascio, che il **comportamento del modello** potrebbe cambiare, **anche prima** di avere tutte le **etichette vere**.

**Come si misura**. In un tipico sistema di classificazione si monitora la **distribuzione dell’uscita** (il punteggio **prima** della decisione con la soglia) su tutti gli input $X$ di un intervallo di tempo, e le si applica lo **stesso PSI**: qui il metodo è applicato al **valore di uscita** invece che a una feature.

<!-- SOURCE_VISUAL id="IS11-V11" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="33" type="grafico" description="PSI applicato alla distribuzione delle uscite di un classificatore" reason="Distribuzioni delle uscite visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché guardare le uscite.** In un sistema di concessione del credito le etichette vere (il cliente ha restituito il prestito?) arrivano dopo mesi. Se nel frattempo la quota di punteggi alti passa dal 20% al 45%, qualcosa è cambiato: negli input, nel modello o nel mondo. È un allarme precoce che non richiede di aspettare la ground truth.

---

### **5. Controllare che cosa guarda il modello**


#### **5.1. Mappe di salienza**

> 📌 **Mappe di salienza**.
> - **Evidenziano le regioni dell’input** che influenzano la predizione.
> - Aiutano a scoprire **correlazioni spurie** o **bias** indesiderati.
> - Supportano l’**analisi degli errori** e l’**interpretabilità** del modello.
> - Sono uno **strumento diagnostico**, **non spiegazioni definitive**.

**Come si costruiscono**. Si vuole capire quali feature di input (i **pixel**, per le immagini) sono state importanti per la predizione. La mappa si costruisce con i **gradienti dell’uscita rispetto all’input**: si prende il **gradiente del neurone di predizione di una classe** rispetto ai pixel. Il gradiente del punteggio della classe misura **quanto cambia** il punteggio quando **ogni pixel** varia leggermente. Le aree con gradiente grande sono quelle rilevanti per la classificazione (dettagli nelle).

> 📌 **Gradiente e SmoothGrad (formule standard, nota didattica).** Per un’immagine $x$ e il punteggio $S_c(x)$ della classe $c$, la mappa di salienza è
> $$M_c(x) = \left| \frac{\partial S_c(x)}{\partial x} \right|$$
> La tecnica **SmoothGrad** spesso **riduce molto il rumore** della mappa: aggiunge **rumore gaussiano** pixel per pixel a **molte copie** dell’immagine e fa semplicemente la **media** dei gradienti risultanti:
> $$\hat{M}_c(x) = \frac{1}{n} \sum_{k=1}^{n} M_c\big(x + \varepsilon_k\big), \qquad \varepsilon_k \sim \mathcal{N}(0, \sigma^2)$$

<!-- SOURCE_VISUAL id="IS11-V12" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="36" type="immagini" description="Esempio di mappe di salienza con gradiente semplice e SmoothGrad (PAIR)" reason="Mappe visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Come usarle**. Due reti classificano la stessa immagine:

| Rete | Predizione | Mappa di salienza |
|---|---|---|
| **NN1** | cane = **0,95** | evidenzia pixel **sbagliati**, non il cane |
| **NN2** | cane = **0,92** | evidenzia il cane |

Qual è la rete migliore? NN1 ha la confidenza più alta, ma **la predizione è corretta guardando i pixel sbagliati**. Ci si può fidare di questo modello, **anche se sembra accurato**?

> 💡 **Il collegamento con il dilemma M1/M2.** Un modello può apparire accurato perché sfrutta lo sfondo o un artefatto del dataset, anziché le caratteristiche rilevanti dell’oggetto. Questo è un caso di data leakage. La mappa di salienza è uno dei modi per accorgersene prima del rilascio.

<!-- SOURCE_VISUAL id="IS11-V13" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="38" type="immagini" description="Due reti che riconoscono il cane con confidenza 0,95 e 0,92" reason="Immagini di input visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V14" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="39" type="immagini" description="Mappe di salienza delle due reti: NN1 guarda i pixel sbagliati" reason="Mappe di salienza visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Kernel delle CNN e visual analytics**

**Visualizzare i kernel delle CNN: quali pattern rilevano?** I kernel appresi attivano risposte elevate in corrispondenza delle strutture per cui sono diventati sensibili. Si possono quindi associare a ciascun filtro gli esempi del dataset che producono l’attivazione più alta, rendendo più leggibile il comportamento dei filtri.

**Visualizzazioni dei modelli**: forse **troppo dense**? Esempio: **DQNViz**, un approccio di *visual analytics* per capire le **Deep Q-Networks** (DQN), un tipo di modello di **apprendimento per rinforzo profondo**.

<!-- SOURCE_VISUAL id="IS11-V15" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="40" type="immagini" description="Kernel di una CNN (quadrati grigi) ed esempi che li attivano al massimo (quadrati colorati)" reason="Kernel ed esempi visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V16" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="41" type="schermata" description="DQNViz: visual analytics per le Deep Q-Networks" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Passo 5: dashboard di monitoraggio**


**Dashboard di BI e monitoraggio con assistenti AI**.

> 📌 **Osservabilità**. Monitorare un sistema rilasciato attraverso **segnali misurabili**, per capirne il **comportamento**, **rilevare guasti** e supportare **azioni correttive**.

| Strumento | Produttore | Caratteristiche |
|---|---|---|
| **TensorBoard** | Google | una suite di strumenti di visualizzazione per **capire, fare debug e ottimizzare** i modelli: visualizzare il **grafo** TensorFlow, tracciare **metriche quantitative** sull’esecuzione e mostrare dati aggiuntivi come le **immagini** che lo attraversano |
| **Visdom** | Meta AI | genera visualizzazioni ricche di **dati in tempo reale**, per seguire esperimenti eseguiti su **server remoti**; si vedono **nel browser** e si condividono facilmente |
| **Microsoft Power BI** | Microsoft | piattaforma unificata e scalabile di **business intelligence** self-service e aziendale per collegarsi a qualsiasi dato e visualizzarlo |

<!-- SOURCE_VISUAL id="IS11-V17" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="44" type="schermata" description="TensorBoard con metriche di addestramento" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6.1. Visualizzare il sistema intelligente per componenti**

Una singola metrica non descrive il comportamento di un sistema. La diagnostica richiede viste diverse: qualità e bilanciamento del dataset, feature space, parametri e attivazioni della rete, errori, calibrazione, costi e condizioni operative. L’esempio usa 1.797 immagini di cifre 8×8 (64 feature, dieci classi), divise in 1.000 campioni di training, 400 di validation e 397 di test. Cinquanta etichette di training sono deliberatamente alterate. Una rete 64–16–10 con ReLU e 1.210 parametri raggiunge il 95,2% sul test: 378 previsioni corrette e 19 errori. Il numero complessivo non basta a descriverne la qualità.

Le proiezioni in PCA, t-SNE e UMAP riducono lo spazio a due dimensioni. PCA fornisce assi lineari legati alla varianza; t-SNE conserva soprattutto vicinati. Dimensioni e distanze tra gruppi di t-SNE cambiano con la perplexity, per cui cluster e distanze sulla mappa non vanno trattati come misure originarie. CIFAR-10 può essere esplorato anche con griglie casuali per classe: i campioni aiutano a verificare bilanciamento e definizioni operative delle etichette. Facets rende consultabili immagini e statistiche per classe e per feature.

Nei modelli, una matrice di pesi può essere resa come immagini: i 64 pesi che collegano gli input pixel a un neurone diventano una tessera 8×8; istogrammi e statistiche aiutano a riconoscere scale anomale e unità inattive. Le mappe delle attivazioni mostrano quali unità rispondono alle diverse classi e possono rivelare neuroni che non si attivano mai. I kernel di una CNN si interpretano confrontandoli con le porzioni di immagine che producono risposte elevate. Mappe di salienza e SmoothGrad evidenziano regioni che influenzano il punteggio, ma restano strumenti diagnostici e non spiegazioni definitive.

Per misurare le prestazioni, matrice di confusione e tassi per classe indicano dove si concentrano gli errori. Nel caso delle cifre, i 19 errori comprendono tre “3” classificati come “8”. L’accuratezza media va affiancata da analisi per slice: classe, quantità d’inchiostro, illuminazione o fornitore possono individuare gruppi deboli che scompaiono nella media. La reliability diagram confronta confidenza e frequenza osservata; un modello calibrato che assegna confidenza 0,8 risulta corretto circa nell’80% dei casi corrispondenti. L’ECE è una media pesata degli scarti per bin. Calibrazione e accuratezza sono proprietà diverse: un modello ben calibrato può comunque sbagliare spesso.

La permutation importance misura di quanto cala una metrica quando i valori di una feature vengono rimescolati. Indica quanto il modello dipende da quella variabile nel dataset valutato; non prova che la variabile sia una causa fisica. Feature correlate possono condividere l’importanza, rendendo instabile il confronto.

<!-- SOURCE_VISUAL id="IS11-27-V01" source="L11-web-v24.pdf" page="57" type="tabella e grafici" description="Dataset delle cifre, partizione, etichette corrotte e risultato della rete 64–16–10" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS11-27-V02" source="L11-web-v24.pdf" page="61" type="visualizzazioni" description="Pesi del primo layer resi come immagini e distribuzioni dei pesi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS11-27-V03" source="L11-web-v24.pdf" page="82" type="grafici per slice" description="Tassi di errore per classe e per quantità di inchiostro, confrontati con l’errore medio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS11-27-V04" source="L11-web-v24.pdf" page="87" type="grafico" description="Confronto tra modello calibrato e sovraconfidente con ECE" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **6.2. Prestazioni, costi e funzionamento operativo**

Le figure di performance devono rendere esplicite le conseguenze della decisione. Un forecast mostra gli intervalli predittivi oltre alla linea centrale; un intervallo al 95% descrive una copertura attesa e va verificato, non assunto corretto per definizione. Nella classificazione dei difetti, falsi positivi e falsi negativi possono avere costi molto diversi: l’esempio assegna 5 euro a un falso allarme e 100 euro a un difetto mancato, così la matrice dei costi mostra perché un’accuratezza elevata può lasciare perdite importanti. Spostare la soglia cambia recall e precisione; la curva ROC e quella precision–recall visualizzano il compromesso lungo le soglie, ma la scelta operativa dipende dai costi e dalla prevalenza.

Per la manutenzione predittiva, carte di controllo confrontano misure con limiti stimati su un periodo sano. Il criterio ±3σ presuppone condizioni statistiche che i segnali reali possono violare; soglie e allarmi vanno quindi validati sul processo. Le curve di degradazione e la Remaining Useful Life descrivono l’avvicinamento a una soglia di guasto. I macchinari ancora funzionanti sono osservazioni censurate: non vanno trattati come se non dovessero mai guastarsi.

La frontiera di Pareto rappresenta compromessi tra, per esempio, qualità e latenza. Un punto è dominato se esiste una soluzione almeno altrettanto buona in tutti gli obiettivi e migliore in almeno uno. Diagrammi Sankey e Gantt descrivono rispettivamente bilanci di flusso e pianificazioni: in un Sankey quantità e unità devono bilanciarsi ai nodi; in un Gantt va dichiarata la funzione obiettivo, come durata totale, ritardi o utilizzo delle macchine.

### **6.3. Agenti per la visual analytics**

Un agente di visualizzazione riceve un dataset, un messaggio da comunicare e vincoli su pubblico, colori e formato; genera codice, esegue il grafico e può iterare dopo un controllo umano. L’agente vede soltanto la domanda e i dati forniti, mentre istruzioni di sistema, descrizioni degli strumenti e regole configurate influenzano il codice prodotto. La traccia dell’esecuzione mostra pianificazione, chiamate a strumenti, controlli, attese e punti di approvazione; aiuta a capire cosa è stato fatto, ma non dimostra che l’esito sia corretto. Se strumenti o permessi possono cambiare durante una sessione, il log deve registrare la configurazione effettiva al momento di ciascuna chiamata.

Il caso di due linee produttive mostra quattro grafici costruiti dagli stessi otto conteggi. Per i prodotti facili, A passa 19/20 (95%) e B 90/100 (90%); per quelli difficili, A passa 72/100 (72%) e B 14/20 (70%). Nel totale A ha 91/120 (75,8%), B 104/120 (86,7%), dunque il confronto aggregato premia B. Stratificando per difficoltà, A ha un tasso superiore in entrambi i gruppi. La composizione dei prodotti spiega l’inversione: l’83% della produzione B è facile, mentre per A la quota è il 17%. Il grafico per gruppi risponde a “quale linea è più efficace a parità di difficoltà?”; quello di composizione mostra il motivo della differenza aggregata; l’area dei punti può combinare tasso e numerosità, con minore precisione percettiva.

Le figure possono essere tutte corrette e tuttavia rispondere a domande differenti. Prima di chiedere un grafico, fissare il messaggio; poi ricalcolare alcuni rapporti, stratificare per i confondenti noti, chiedere una vista alternativa, controllare assi e omissioni e conservare dati e codice. La verifica deve considerare anche rappresentatività, corruzione e preprocessing dei dati. I dashboard BI e di osservabilità affiancano strumenti per esperimenti ML (TensorBoard, Weights & Biases, MLflow), BI (Tableau, Power BI) e telemetria di produzione (Grafana, Datadog, Prometheus, Evidently AI, WhyLabs).

<!-- SOURCE_VISUAL id="IS11-27-V05" source="L11-web-v24.pdf" page="99" type="traccia" description="Traccia di un agente: pianificazione, strumenti, verifica, approvazione umana e azione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS11-27-V06" source="L11-web-v24.pdf" page="103" type="quattro grafici" description="Quattro viste dei medesimi conteggi di prodotto: totale, per gruppo, mix e tasso con numerosità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS11-27-V07" source="L11-web-v24.pdf" page="104" type="tabella comparativa" description="Correttezza, informazione e possibili bias delle quattro visualizzazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **7. Principi della visualizzazione dei dati**


#### **7.1. Perché quasi sempre 2D o 3D**

Gli argomenti della sezione: che cos’è la visualizzazione e come funziona, la teoria dell’immagine, la definizione di rappresentazione per immagini, gli usi attuali, i benefici e alcuni esempi.

**Perché (quasi) solo grafici 2D e 3D?**:

- viviamo nello **spazio-tempo**, un modello matematico che fonde **tre dimensioni spaziali** e **una temporale** in un continuo quadridimensionale;
- ma la nostra **percezione è limitata**: solo i grafici **2D** e (raramente) **3D** si valutano e si capiscono facilmente;
- per questo è così importante la **riduzione della dimensionalità**: per esempio la **PCA** trasforma 4 feature $F_1, \dots, F_4$ in 4 nuove feature $F_1', \dots, F_4'$, di cui **2 salienti** si possono disegnare;
- e conviene aggiungere informazione **oltre i 2–3 assi** (colore, forma, dimensione: §7.2).

<!-- SOURCE_VISUAL id="IS11-V18" source="L11-web-v24.pdf" page="8" type="schema" description="PCA da 4 feature a 2 feature salienti per la visualizzazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Bertin e le variabili visive**

> 📌 **Visualizzazione dei dati**. Il processo di **convertire dati grezzi** in **immagini di informazione facilmente comprensibili**, che permettono **decisioni rapide ed efficaci**. All’inizio del Novecento gli psicologi della **Gestalt** osservarono che, quando degli elementi si riuniscono in una figura, la figura acquista una **salienza percettiva** che **supera la somma delle parti**.

**Dai dati alle immagini**. **Jacques Bertin**, autore del classico *Semiologia della grafica*, afferma che la «trasformazione dai numeri all’intuizione richiede **due fasi**»:

$$\text{dati / processi} \;\xrightarrow{\;\text{algoritmo}\;}\; \text{immagine} \;\xrightarrow{\;\text{percezione}\;}\; \text{intuizione}$$

> 📌 **Le 7 variabili visive di Bertin**. **Posizione**, **forma**, **orientamento**, **colore**, **texture**, **valore** (chiaro-scuro), **dimensione**; combinate con una **semantica visiva** che collega gli **attributi dei dati** agli **elementi visivi**.

La codifica visiva dei dati usa caratteristiche percettive differenti, ciascuna adatta a un tipo di informazione.

> 💡 **Non tutte le variabili sono uguali (nota didattica).** Bertin distingue le variabili per ciò che permettono di leggere: la **posizione** e la **dimensione** mostrano **quantità** e **ordine**; il **valore** mostra un ordine ma non quantità precise; **colore** (tinta), **forma** e **texture** distinguono **categorie** ma non ordinano. Gli studi di Cleveland e McGill confermano che confrontiamo con più precisione **posizioni su una scala comune** che lunghezze, angoli, aree o colori. Per questo un grafico a barre si legge meglio di un grafico a torta.

<!-- SOURCE_VISUAL id="IS11-V19" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="53" type="infografica" description="Codifica dei dati con indizi visivi" reason="Esempi di codifica visibili solo nell’infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V20" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="54" type="infografica" description="Le caratteristiche visive non sono tutte dello stesso tipo" reason="Classificazione delle variabili visibile solo nell’infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3. La teoria dell’immagine**

**L’elaborazione visiva avviene in tre passi**:

1. **formazione dell’immagine sulla retina**;
2. **scomposizione** dell’informazione retinica in un insieme di **rappresentazioni specializzate**;
3. **ricomposizione** dell’informazione nella **percezione degli oggetti**.

> 📌 **L’immagine come unità percettiva**. Il concetto chiave di Bertin, da cui la teoria prende il nome, è l’**immagine**: semplificando, l’**unità percettiva fondamentale** di una visualizzazione. Una visualizzazione **ideale contiene una sola immagine**, per ottimizzare l’**efficienza**, cioè la velocità con cui l’osservatore estrae l’informazione. La domanda della figura: **dove stai perdendo energia?**

**Una fusione di discipline**. La visualizzazione dell’informazione unisce **grafica**, **visualizzazione scientifica**, **gestione dei database** e **interazione uomo-macchina**. Nelle proprie applicazioni va usata per comunicare risultati complessi come **immagini comprensibili**, migliorando l’**interfaccia uomo-macchina** (HMI).

**Importante per utenti e progettisti**: la visualizzazione fornisce **interfacce grafiche intuitive** e aiuta gli utenti a ricavare informazioni utili per decisioni **più rapide e informate**. Serve agli utenti, **ma anche al progettista**.

**Quale grafico?**: una guida alla scelta del tipo di grafico.

<!-- SOURCE_VISUAL id="IS11-V21" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="59" type="infografica" description="Guida alla scelta del tipo di grafico" reason="Guida presente solo come infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V22" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="60" type="infografica" description="Guida alla scelta del tipo di grafico (continua)" reason="Guida presente solo come infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4. Benefici e buone regole**


**Benefici della visualizzazione**:

- vedere i dati da **prospettive diverse**;
- **interpretare grandi quantità** di dati;
- **individuare eccezioni**;
- analizzare **pattern visivi**, esplorare **tendenze** navigando i dati;
- tradurre i pattern in **intuizioni**: uno strumento decisionale molto efficace, che **riduce tempo e difficoltà** dal dato alla decisione;
- vedere **influenze** (uscite contro ingressi) altrimenti difficili da trovare;
- con tutti i dati disponibili è difficile trovare le **sfumature** che fanno la differenza.

«**Un’immagine vale più di mille parole**».

**Mostrare la confidenza**. Le figure mostrano grafici con popolazioni **uniformi** e **non uniformi**, e gli **intervalli di confidenza** di una rete addestrata: dove **mancano dati**, gli intervalli sono **più ampi**.

> 💡 **Perché gli intervalli si allargano (nota didattica).** Una rete addestrata su dati concentrati in una zona dello spazio di input è «sicura» lì e **incerta** altrove: nelle regioni con pochi esempi molte funzioni diverse spiegano ugualmente bene i dati. Mostrare l’intervallo, e non solo la curva media, avverte l’utente di non fidarsi delle predizioni nelle zone scoperte (, il buco topologico).

<!-- SOURCE_VISUAL id="IS11-V23" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="63" type="grafico" description="Confidenza con popolazione uniforme e non uniforme" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V24" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="64" type="grafico" description="Intervalli di confidenza di una rete addestrata, più ampi dove mancano i dati" reason="Intervalli visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La galleria MATLAB include esempi di **annotazioni**, **mappe di colore** e **limiti** degli assi.

> 📌 **Conclusioni: un grafico dovrebbe…**.
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


**La struttura di un grafico semplice ma completo** comprende gli elementi seguenti.

> 💡 **Gli elementi di un grafico completo (nota didattica).** Titolo; assi con **etichetta e unità di misura**; scala adatta ai dati; legenda quando ci sono più serie; eventuali annotazioni sui punti importanti; indicazione dell’incertezza (barre d’errore o bande) quando disponibile.

<!-- SOURCE_VISUAL id="IS11-V25" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="70" type="grafico" description="Struttura di un grafico semplice ma completo" reason="Elementi del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempi**:

| Pagina | Esempio |
|---|---|
| | **distribuzioni nel tempo**: assenza di dati, vincitori, eventi |
| | **fenomeni complessi** |
| | **vaccinazione contro il morbillo** (dati OMS) |
| | grafico nel grafico, vettori in linee 3D (galleria MATLAB) |
| | strumenti online per la visualizzazione **interattiva**, per esempio **observablehq.com** |
| | esempi grafici senza testo |

**Vaccino sì o no? Il morbillo**. Secondo l’**Organizzazione Mondiale della Sanità**, pur essendo disponibile un vaccino **sicuro ed economico**, nel **2018** ci sono stati oltre **140 000 morti** per morbillo nel mondo, per lo più **bambini sotto i cinque anni**. La vaccinazione ha prodotto un **calo del 73%** delle morti per morbillo tra il **2000** e il **2018**. Il grafico evidenzia che, tra i contagiati, **una percentuale morirà**.

<!-- SOURCE_VISUAL id="IS11-V26" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="71" type="grafico" description="Distribuzioni nel tempo con assenza di dati, vincitori ed eventi" reason="Grafico visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V27" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="72" type="grafico" description="Visualizzazione di fenomeni complessi" reason="Grafico visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V28" source="L11-web-v24.pdf" page="39" type="infografica" description="Vaccinazione contro il morbillo: dati OMS 2000-2018" reason="Infografica visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V29" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="76" type="grafico" description="Esempio di visualizzazione senza testo (1)" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V30" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="77" type="grafico" description="Esempio di visualizzazione senza testo (2)" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V31" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="78" type="grafico" description="Esempio di visualizzazione senza testo (3)" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Grafici bellissimi, dati sbagliati**. La potenza grafica di grafici e diagrammi è **inutile** se i dati:
> - **non sono rappresentativi**;
> - sono **corrotti**;
> - **non sono pre-elaborati** correttamente;
> - sono **FALSI**, per esempio per una data augmentation **eccessiva o sbagliata**.

**La libreria ggplot2 di R**. **R** è un linguaggio di programmazione e un ambiente software **libero** per il **calcolo statistico** e la **grafica**, sostenuto dalla R Foundation for Statistical Computing; **ggplot2** è la sua libreria grafica più diffusa.

---

### **9. Visualizzazione 3D in MATLAB**


#### **9.1. Superfici da matrici 2D**

«Il meglio che si può fare in 3D». La mostra una **moneta scansionata in 3D** con dispositivi di acquisizione 3D.

**Quattro grafici della stessa moneta**:

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

<!-- SOURCE_VISUAL id="IS11-V32" source="L11-web-v24.pdf" page="47" type="grafici" description="Quattro rappresentazioni della moneta: surf, mesh, pcolor, contour" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Superfici e isosuperfici**. Le **decisioni dei sistemi intelligenti** si possono rappresentare come **superfici** negli spazi di input interni: la frontiera di decisione di un classificatore con tre feature è una superficie nello spazio 3D.

**Dati 3D nel tempo**: rappresentazioni del **monte Sant’Elena**, nello stato di Washington, **prima e dopo l’eruzione del 1980**.

<!-- SOURCE_VISUAL id="IS11-V33" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="85" type="grafico" description="Isosuperficie come rappresentazione delle decisioni di un sistema intelligente" reason="Superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V34" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="86" type="grafici" description="Monte Sant’Elena prima e dopo l’eruzione del 1980" reason="Superfici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Istogramma bivariato 3D**, con i dati del **bike sharing di Boston**:

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

**Compito: trovare il massimo di una funzione 2D**. Si considera la funzione `peaks`, la si disegna e se ne trova il massimo:

```matlab
[Z] = peaks;                     % it creates a smooth curved surface
surf(Z); xlabel('x'); ylabel('y'); zlabel('z');
Zmax = max(max(Z))
[ymax, xmax] = find(Z == Zmax)
hold on;
plot3( xmax, ymax, Zmax, 'hg', 'Markersize', 15 );
hold off;
```

> 📌 **Apprendere significa ottimizzare**: trovare un **minimo** o un **massimo** di una funzione.

> 💡 **Righe e colonne (nota didattica).** `find(Z == Zmax)` con due uscite restituisce **riga** e **colonna**: la riga corrisponde all’asse $y$, la colonna all’asse $x$. Per questo il codice scrive `[ymax, xmax]` e poi disegna `plot3(xmax, ymax, ...)`. È lo stesso scambio di convenzioni visto con `ind2sub` nella

**Vettori in 3D**: le **normali** alla superficie $z = x\, e^{-x^2 - y^2}$, per mostrare una proprietà complessa di un singolo punto come un vettore.

```matlab
[x,y] = meshgrid(-2:.2:2,-1:.15:1);
z = x .* exp(-x.^2 - y.^2);
[u,v,w] = surfnorm(x,y,z);
quiver3(x,y,z,u,v,w);
hold on, surf(x,y,z), hold off
```

**Una proprietà mappata sul colore**: il **colore** rappresenta la **pendenza**.

```matlab
[x,y] = meshgrid([-2:.1:2]);
Z = x.*exp(-x.^2-y.^2);
mesh(x,y,Z,abs(gradient(Z)))
colorbar; colormap jet;
```

> ⚠️ **Quale gradiente (nota didattica).** Con una sola uscita, `gradient(Z)` restituisce solo la derivata lungo $x$; il colore mostra quindi $\left|\partial Z / \partial x\right|$. Per colorare con il **modulo** del gradiente servono entrambe le componenti:
> $$\lVert \nabla Z \rVert = \sqrt{\left(\frac{\partial Z}{\partial x}\right)^2 + \left(\frac{\partial Z}{\partial y}\right)^2}$$
> in MATLAB `[gx, gy] = gradient(Z); mesh(x, y, Z, sqrt(gx.^2 + gy.^2))`.

**Ispezionare superfici di decisione con le luci**. La funzione $\dfrac{\sin\sqrt{x^2 + y^2}}{\sqrt{x^2 + y^2}}$ disegnata con `ezsurf` sul dominio $[-6\pi, 6\pi]$, con `shading interp`, una sorgente di luce (`lightangle`) e l’illuminazione **Phong** (componenti ambientale, diffusa e speculare) per far risaltare la forma della superficie.

> ⚠️ **Funzioni datate.** Nelle versioni recenti di MATLAB `ezsurf` è sostituita da `fsurf`, e il renderer `'zbuffer'` usato in questi esempi non è più disponibile. I concetti (superficie, luce, riflessione) restano validi.

**Mostrare il processo di ottimizzazione**: esempio di **discesa del gradiente stocastica** in un problema di ottimizzazione **non convesso**, dove il percorso può fermarsi in minimi locali.

<!-- SOURCE_VISUAL id="IS11-V35" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="88" type="grafico" description="Funzione peaks con il massimo evidenziato" reason="Superficie e punto visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS11-V36" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="92" type="grafico" description="Discesa del gradiente stocastica in un problema non convesso" reason="Percorso dell’ottimizzazione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **9.3. Dati volumetrici: fette e isosuperfici**

**Velocità in un getto di fluido**. Con i dati di esempio `flow` (velocità in un getto d’acqua in un contenitore senza pareti) si generano **fette** del volume: una fetta inclinata di 45° ottenuta ruotando una superficie piana (`surf`, `rotate`), più tre fette sui piani $x_{\max}$, $y_{\max}$ e $z_{\min}$ (`slice`), colorate con interpolazione, con proporzioni reali (`daspect`), prospettiva, luce e mappa di colore.

> 📌 **In 3D meglio proiezioni e fette**. È **molto difficile** disegnare una feature su **tre assi**, perché i **voxel si coprono a vicenda**. Spesso è meglio usare **proiezioni** e **fette**.

**Risonanza magnetica**. A partire da una matrice 3D `D` di dimensione **128 × 128 × 27** (esempio `mri`):

| Grafico | Funzioni principali | Che cosa mostra |
|---|---|---|
| sinistra | `contourslice(D, [], [], [1,12,19,27], 8)` | **curve di livello** su quattro fette orizzontali |
| destra | `smooth3`, `isosurface`, `isocaps`, `isonormals`, `lighting phong` | **isosuperficie** del volume levigato, con le «coperture» sui tagli e illuminazione |

**La scelta del formato** dipende **molto** dall’**obiettivo** della visualizzazione.

<!-- SOURCE_VISUAL id="IS11-V37" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="94" type="grafico" description="Fette di un volume 3D per evitare l’occlusione tra voxel" reason="Fette visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Esempi ironici di visualizzazione**


Alcune visualizzazioni umoristiche invitano a confrontare personaggi, stili di vita e scelte di rappresentazione.

**Anche per «applicazioni» molto particolari**, la scelta di uno **spazio di input** adeguato permette **confronti** e **intuizioni**.

**Come trovare la persona creativa più simile a te?**:

1. decidere lo **spazio di input**;
2. decidere la **metrica** (distanza);
3. **scorrere** tutti i candidati per trovare il più vicino.

Così si trova il «**primo vicino più prossimo**» (*First Nearest Neighbor*), un metodo tipico di ML. **L’allineamento** delle figure è critico: senza, il confronto sarebbe impossibile.

<!-- SOURCE_VISUAL id="IS11-V38" source="Lesson_11_DataRapresentation_and_Visualization_in_ML.pdf" page="98" type="illustrazione" description="Uso umoristico della visualizzazione con uno spazio di input per confronti" reason="Illustrazione della figura" -->
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


> ✅ **Punti principali**.
> - **Visualizzare i sistemi in ogni fase**: dati, rappresentazioni, modello, performance, drift, funzionamento e decisioni.
> - **Input e dataset di immagini**: EDA con pandas, istogrammi, cluster da verificare con confronti controllati, CIFAR-10 e Facets.
> - **Dashboard e visual analytics**: drift, osservabilità, agenti che generano codice e figure, revisione umana obbligatoria.
> - **Diagnostica dei modelli**: pesi, attivazioni, salienza, matrici di confusione, slice, calibrazione, soglie e importanza delle feature; sono indizi da interpretare, non prove causali.
> - **Kernel**: quali pattern rilevano i filtri di una CNN.
> - **Grafici e decisioni**: partire dalla domanda, mostrare incertezza e composizione dei gruppi, non occultare costi o numerosità.
> - **Evitare grafici «divertenti»** e scegliere con cura le caratteristiche visive: **un concetto alla volta, in una sola immagine efficace**.
