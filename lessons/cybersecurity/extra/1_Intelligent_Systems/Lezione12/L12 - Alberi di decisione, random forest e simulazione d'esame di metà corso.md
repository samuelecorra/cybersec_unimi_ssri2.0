## **Lezione 12: Alberi di decisione, random forest e simulazione d’esame di metà corso**

La dodicesima lezione del corso **IS4** presenta il primo modello di machine learning vero e proprio del corso: l’**albero di decisione**. È un modello classico, veloce e soprattutto **spiegabile**, perché ogni decisione è una sequenza di domande leggibili; per questo è molto usato in sicurezza, privacy e conformità. Si vedono le sue **frontiere di decisione**, come si misura la «purezza» dei nodi con **indice di Gini** ed **entropia**, i suoi limiti (overfitting, instabilità) e come la **random forest** li attenua combinando molti alberi. Un laboratorio Python con il dataset **Wine** mostra addestramento e uso di un albero. La seconda metà della lezione è la **simulazione d’esame di metà corso**, con venti domande sulle Lezioni 4–12.

Fonti: [PDF della Lezione 12](Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf), 112 pagine, e la [scheda punteggi della simulazione](Lesson_12_Exam_Simulation_Scores.pdf), 1 pagina. Le righe `_(scores …)_` rimandano alla scheda. Le risposte corrette sono quelle **evidenziate** nel PDF, individuate senza rendering dalle aree di evidenziazione sotto il testo. Il codice Python del laboratorio è nelle slide solo come immagine: gli esempi di codice qui sono ricostruzioni indicative e sono segnalati. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Titolo della lezione**

_(slide p. 1)_

Titolo originale: *Decision Tree and Random Forest, Exam simulation of the first half of the course*.

---

### **1. Alberi di decisione**

_(slide pp. 2–18)_

#### **1.1. Perché gli alberi di decisione**

**Alberi di decisione per sicurezza e privacy** (p. 2). Gli alberi di decisione sono tra i modelli di ML **più usati nelle applicazioni di sicurezza**, grazie a **semplicità**, **interpretabilità** ed **efficienza**. Supportano in modo naturale il **ragionamento basato su regole**, un requisito chiave nei sistemi di sicurezza, privacy e conformità:

- **logica decisionale esplicita e interpretabile**;
- **inferenza veloce**, adatta a sistemi di sicurezza **in tempo reale**;
- facili da **verificare** (audit), da **correggere** e da **spiegare** agli analisti.

**Formato dei dati di addestramento: X, Y** (p. 3). L’esempio classico è «**Andiamo a giocare a tennis?**»: ogni riga descrive una giornata con alcune feature ($X$) e la decisione presa ($Y$). Gli alberi di decisione sono **perfetti per i dati tabellari**.

<!-- SOURCE_VISUAL id="IS12-V01" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="3" type="tabella" description="Dataset Play Tennis con feature X e decisione Y" reason="Tabella dei dati presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il dataset Play Tennis (nota didattica).** Nella versione classica di Quinlan ci sono **14 giornate** descritte da quattro feature categoriche: **Outlook** (soleggiato, nuvoloso, pioggia), **Temperature** (caldo, mite, fresco), **Humidity** (alta, normale), **Wind** (debole, forte); la classe è **PlayTennis** (sì/no), con 9 «sì» e 5 «no». Se la tabella della slide coincide con questa versione, valgono i calcoli del §1.4.

#### **1.2. Spazio delle ipotesi e spiegabilità**

Le pp. 4–5 mostrano lo **spazio delle ipotesi** di un albero: un’uscita binaria (1/0) in funzione di due feature $x_1$, $x_2$.

<!-- SOURCE_VISUAL id="IS12-V02" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="4" type="diagramma" description="Spazio delle ipotesi di un albero di decisione" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V03" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="5" type="grafico" description="Uscita 1/0 di un albero in funzione di x1 e x2" reason="Regioni di decisione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Spiegabilità degli alberi** (p. 6). Gli alberi di decisione sono **intrinsecamente interpretabili**: la loro struttura rende esplicito **come e perché** si prende una decisione, organizzandola come una **sequenza di regole semplici e leggibili**.
> - Ogni **nodo interno** corrisponde a una **decisione su una feature**.
> - Ogni **percorso dalla radice a una foglia** rappresenta una **regola di decisione**.
> - Le feature che **non riducono l’incertezza** vengono **scartate automaticamente**.
>
> Nell’esempio Play Tennis la feature **Temperature** non migliora la qualità delle decisioni, e infatti **non compare nell’albero**.

> 💡 **L’albero come regole (nota didattica).** L’albero classico di Play Tennis si legge come tre regole:
> - se Outlook = nuvoloso → **gioca**;
> - se Outlook = soleggiato → gioca **solo se** Humidity = normale;
> - se Outlook = pioggia → gioca **solo se** Wind = debole.
>
> È la stessa forma «if-then» dell’apprendimento deduttivo (Lezione 03, §4), ma qui le regole sono **apprese dai dati**.

**Alberi binari e non binari** (p. 7):

| | **Alberi binari** | **Alberi non binari** |
|---|---|---|
| Nodi | ogni nodo interno divide i dati in **due rami** | i nodi possono avere **più rami** |
| Caratteristiche | struttura più semplice, **potatura** più facile | usati spesso con feature **categoriche**; alberi **meno profondi** ma nodi **più complessi** |
| Compromesso | **profondità maggiore**, decisioni più semplici | **profondità minore**, nodi più complessi |

#### **1.3. Frontiere di decisione**

_(slide pp. 8–10)_

La p. 8 riprende il caso dei **dati sulla frutta** con un albero di decisione.

> 📌 **Frontiere allineate agli assi** (p. 9). Le frontiere di decisione di un albero sono **allineate agli assi**: ogni nodo confronta **una sola feature** con una soglia ($x_j \le s$), quindi taglia lo spazio con un piano **perpendicolare** a quell’asse. Il risultato è una partizione dello spazio in **rettangoli** (iper-rettangoli in più dimensioni).

> 💡 **Conseguenza pratica (nota didattica).** Una frontiera **obliqua**, come $x_1 = x_2$, un albero la può solo **approssimare a scalini**, con molti nodi. Una retta di un modello lineare la rappresenterebbe con due parametri. Il confronto con le frontiere di altri modelli (p. 10) sarà approfondito nelle lezioni successive.

<!-- SOURCE_VISUAL id="IS12-V04" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="8" type="diagramma" description="Albero di decisione sul caso dei dati sulla frutta" reason="Albero presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V05" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="9" type="grafico" description="Frontiere di decisione allineate agli assi di un albero" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V06" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="10" type="grafico" description="Confronto tra le frontiere di decisione di modelli diversi" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.4. Che cosa sa un albero: profondità, Gini, entropia**

_(slide pp. 11–17)_

Per controllare la conoscenza contenuta in un albero si guardano (p. 11):

1. la **profondità** dell’albero;
2. l’**informazione nei nodi**: **impurità di Gini** ed **entropia**.

**1) Profondità** (p. 12). Dice **quante domande** l’albero deve fare dalla radice a una risposta finale (una foglia).

| Albero | Pro | Contro |
|---|---|---|
| **profondo** | logica più complessa, cattura più dettagli | rischio di **overfitting** |
| **poco profondo** | più semplice, veloce e interpretabile | può **perdere pattern** importanti |

La profondità è come il numero di passi con cui il «piccolo detective» arriva alla decisione. Gli alberi di decisione sono **molto veloci**: pochi `if`.

**2) Impurità di Gini** (p. 13). Misura la **purezza dei nodi**: la **probabilità** che un elemento scelto a caso sia **classificato in modo sbagliato** se lo si etichettasse secondo la distribuzione delle classi nel nodo. Si usa per valutare le divisioni in algoritmi come **CART**.

**Entropia** (pp. 14–15). Misura l’**incertezza** in un nodo: quanto sono **mescolate** le classi; entropia più alta significa più confusione. Si usa negli algoritmi **ID3** e **C4.5**. Viene dalla **teoria dell’informazione** (Shannon, 1948), e la **base del logaritmo** definisce l’unità di informazione. In questo contesto l’«informazione» è una cosa **negativa**: significa che il nodo ha **troppa incertezza**.

- **Entropia minima** (zero): **nessuna incertezza**, tutti i campioni appartengono a **una sola classe**.
- **Entropia massima**: tutte le classi sono **ugualmente probabili**; nel caso **binario** $H_{\max} = 1$.
- Perché $\log_2$? Perché l’entropia si misura **in bit**: con $p = 50\%$, $H = 1$ bit.

> 📌 **Le formule (forma standard; nelle slide sono immagini).** Per un nodo con $C$ classi e proporzioni $p_1, \dots, p_C$:
> $$\text{Gini} = 1 - \sum_{i=1}^{C} p_i^2 \qquad\qquad H = -\sum_{i=1}^{C} p_i \log_2 p_i$$
> Con la convenzione $0 \cdot \log_2 0 = 0$. Entrambe valgono **0** per un nodo puro e sono **massime** quando le classi sono equiprobabili.

| Caso | Gini massimo | Entropia massima |
|---|---|---|
| **2 classi** (p. 17) | $1 - 2 \cdot 0{,}5^2 = 0{,}5$ | $1$ bit |
| **3 classi** (mela, pera, banana, p. 15) | $1 - 3 \cdot (1/3)^2 \approx 0{,}667$ | $\log_2 3 \approx 1{,}585$ bit |

> 📌 **Guadagno di informazione (nota didattica).** Una divisione dell’insieme $S$ secondo la feature $A$ produce i sottoinsiemi $S_v$. Il guadagno è la riduzione di entropia:
> $$\text{IG}(S, A) = H(S) - \sum_{v} \frac{|S_v|}{|S|}\, H(S_v)$$
> ID3 sceglie a ogni nodo la feature con il **guadagno massimo**.

> 💡 **Calcolo su Play Tennis (nota didattica, versione classica).** Alla radice ci sono 9 «sì» e 5 «no»:
> $$H(S) = -\tfrac{9}{14}\log_2 \tfrac{9}{14} - \tfrac{5}{14}\log_2 \tfrac{5}{14} \approx 0{,}940 \qquad \text{Gini}(S) = 1 - \left(\tfrac{9}{14}\right)^2 - \left(\tfrac{5}{14}\right)^2 \approx 0{,}459$$
> Dividendo per Outlook: soleggiato (2 sì, 3 no) e pioggia (3 sì, 2 no) hanno $H \approx 0{,}971$, nuvoloso (4 sì) ha $H = 0$. Quindi
> $$\text{IG}(S, \text{Outlook}) \approx 0{,}940 - \tfrac{5}{14}(0{,}971) - \tfrac{4}{14}(0) - \tfrac{5}{14}(0{,}971) \approx 0{,}247$$
> È il guadagno più alto tra le quattro feature: per questo Outlook è la radice. Temperature ha il guadagno più basso e non viene mai scelta.

<!-- SOURCE_VISUAL id="IS12-V07" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="15" type="formule" description="Entropia minima e massima con l’esempio a tre classi mela, pera, banana" reason="Formule ed esempio resi come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V08" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="17" type="grafico" description="Entropia e Gini in funzione della proporzione per un problema binario" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’entropia nel machine learning** (p. 16) è un concetto molto importante, usato in:

| Ambito | Uso |
|---|---|
| **alberi di decisione** | scegliere la **feature migliore** per dividere i dati (ID3, C4.5) |
| **guadagno di informazione** | calcolare **quanta incertezza** si riduce con una divisione |
| **selezione delle feature** | **ordinare** le feature per quanta informazione forniscono |
| **apprendimento non supervisionato** | misurare quanto bene i cluster separano i dati (per esempio con la **mutua informazione**) |
| **funzioni di perdita** | **cross-entropy loss** |
| **regolarizzazione** | **label smoothing**, per evitare l’**eccessiva confidenza** |

> 💡 **Cross-entropy (nota didattica).** Con la distribuzione vera $y$ (per esempio one-hot) e quella predetta $\hat{y}$:
> $$L_{CE} = -\sum_{i=1}^{C} y_i \log \hat{y}_i$$
> Se la classe vera è la $k$, la perdita si riduce a $-\log \hat{y}_k$: è grande quando il modello dà poca probabilità alla classe giusta.

#### **1.5. L’albero di decisione in breve**

_(slide p. 18)_

**Algoritmi di addestramento comuni**: **ID3**, **C4.5**, **CART**.

| **Vantaggi** | **Svantaggi** | **Messa a punto** |
|---|---|---|
| **interpretabilità**: semplice da capire e visualizzare | **overfitting**: alberi complessi modellano il rumore e generalizzano peggio | **potatura** (*pruning*): riduce la dimensione dell’albero contro l’overfitting |
| **versatilità**: gestisce dati **numerici e categorici** | **instabilità**: piccoli cambiamenti nei dati possono dare un albero **completamente diverso** | **selezione delle feature**: migliora le prestazioni |
| **preparazione minima dei dati**: poca o nessuna normalizzazione | **bias con dati sbilanciati**: può favorire le classi dominanti | **metodi ensemble**: combinare molti alberi (random forest) migliora l’accuratezza |
| **non parametrico**: nessuna ipotesi sulla distribuzione dei dati (per esempio gaussiana) | | |

> 💡 **Perché non serve normalizzare.** Ogni nodo confronta una feature con una soglia: moltiplicare la feature per 1000 sposta la soglia ma non cambia **quali** campioni vanno a destra o a sinistra. Per un albero, la normalizzazione della Lezione 10, §5, è quasi irrilevante.

---

### **2. Dall’albero alla foresta**

_(slide pp. 19–21)_

**L’instabilità degli alberi** (p. 19). Gli alberi sono **molto sensibili** ai dati di addestramento: piccole modifiche al dataset possono produrre alberi molto diversi. Il motivo è che un albero si costruisce con un’**ottimizzazione greedy e locale**: a ogni nodo si sceglie la divisione migliore **in quel momento**, e una scelta diversa in alto cambia tutto ciò che sta sotto. Campionando **sottoinsiemi diversi** del dataset si possono addestrare **più alberi**, ciascuno con una **visione diversa** dei dati.

> 📌 **Random forest** (p. 20).
> - Lo stesso esempio viene valutato da **più alberi di decisione**, ciascuno con una **predizione indipendente**.
> - Gli alberi sono addestrati su **campioni bootstrap** diversi dei dati di addestramento.
> - Per la **casualità dei dati e delle feature**, gli alberi prendono **decisioni diverse**.
> - Il **bagging** (*Bootstrap Aggregating*) combina le predizioni di tutti gli alberi: **voto di maggioranza** per la classificazione, **media** per la regressione.
> - Vince la classe con **più voti**: gli **errori dei singoli alberi tendono a compensarsi**.

> 📌 **In formula (nota didattica).** Con $B$ alberi $h_1, \dots, h_B$:
> $$\hat{y}_{\text{classificazione}} = \operatorname{moda}\{h_1(x), \dots, h_B(x)\} \qquad \hat{y}_{\text{regressione}} = \frac{1}{B} \sum_{b=1}^{B} h_b(x)$$
> Un campione bootstrap si ottiene estraendo $N$ esempi **con reinserimento** da $N$: in media contiene circa il **63,2%** di esempi distinti, perché la probabilità che un esempio non sia mai estratto è $\left(1 - \tfrac{1}{N}\right)^N \to e^{-1} \approx 0{,}368$. La «casualità delle feature» consiste nel considerare, a ogni nodo, solo un **sottoinsieme casuale** delle feature.

<!-- SOURCE_VISUAL id="IS12-V09" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="20" type="diagramma" description="Random forest: alberi su campioni bootstrap e voto di maggioranza" reason="Schema della foresta visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Albero o foresta: quando usare quale** (p. 21). Offrono **compromessi diversi** tra interpretabilità e prestazioni:

| **Albero di decisione** quando… | **Random forest** quando… |
|---|---|
| l’**interpretabilità** è critica | **accuratezza e robustezza** sono la priorità |
| le decisioni vanno **spiegate o verificate** | il dataset è **rumoroso o complesso** |
| il dataset è **piccolo o semplice** | bisogna **ridurre l’overfitting** |
| le regole vanno **ispezionate o validate** da persone | una **perdita di interpretabilità** è accettabile |

> ✅ **In una frase** (p. 21): **un albero per spiegare le decisioni, una foresta per migliorare le prestazioni.**

---

### **3. Laboratorio: l’ispettore del vino**

_(slide pp. 22–39)_

#### **3.1. Il problema**

**Il primo modello di ML del corso in Python** (p. 22). **Automatizzare il controllo qualità del vino con gli alberi di decisione** (p. 23, esempio didattico).

Nelle grandi cantine la **composizione** di un vino è una questione di tradizione, scienza e precisione: una bottiglia di **Sassicaia** può costare oltre 400 €. La miscela dei giusti **cultivar** (vitigni) determina non solo il gusto, ma anche l’**identità** e la **certificazione** del vino. Per esempio, il **Chianti** deve contenere per disciplinare **almeno il 70% di Sangiovese**, e può essere miscelato con Canaiolo, Colorino o anche Cabernet Sauvignon.

**Perché serve un sistema intelligente che riconosca il cultivar?**

- il **controllo manuale** della composizione è **lento e soggettivo**;
- **etichette sbagliate** o **sofisticazioni** danneggiano marchio, prezzo e legalità;
- le cantine di alta gamma vogliono sistemi **automatici** per rilevare e classificare vini o cultivar da **dati chimici o di sensori**.

**Con un albero di decisione si può** (p. 24): analizzare le feature del vino (**alcol**, **flavonoidi**, **prolina**…), **predire** il probabile cultivar o la miscela, supportare **etichettatura automatica**, **rilevamento delle frodi** e **monitoraggio della produzione**.

**Procedura**:

1. caricare un **dataset** adeguato;
2. **addestrare** l’albero;
3. usare l’albero per **nuove decisioni**.

#### **3.2. Il dataset Wine**

Il dataset è il **Wine** dell’archivio UCI (p. 25). La p. 26 mostra le **classi**.

> 💡 **Il dataset (nota didattica).** Contiene **178 vini** della stessa regione italiana, provenienti da **tre cultivar**, descritti da **13 feature chimiche** (alcol, acido malico, ceneri, alcalinità delle ceneri, magnesio, fenoli totali, flavonoidi, fenoli non flavonoidi, proantocianine, intensità del colore, tonalità, rapporto OD280/OD315, prolina). Le classi hanno 59, 71 e 48 esempi. Nelle fonti originali i tre vini sono Barolo, Grignolino e Barbera; la slide identifica infatti la `class_0` come **Barolo**.

**Caricamento** (p. 27). In scikit-learn la variabile `wine` è un oggetto **`Bunch`**: una struttura dati speciale che funziona **come un dizionario** ma permette anche l’**accesso per attributo** (per esempio `wine.data` oltre a `wine["data"]`). La p. 28 controlla le variabili.

<!-- SOURCE_VISUAL id="IS12-V10" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="25" type="schermata" description="Caricamento del dataset Wine UCI" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V11" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="26" type="schermata" description="Classi del dataset Wine" reason="Contenuto presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V12" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="27" type="schermata" description="Oggetto Bunch di scikit-learn con il dataset Wine" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V13" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="28" type="schermata" description="Controllo delle variabili del dataset" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. Addestrare e usare l’albero**

**Inizializzazione e addestramento** (p. 29). **L’albero addestrato con 3 livelli** (pp. 30–31). **Con 5 livelli** (p. 32) l’albero **impara perfettamente** il dataset di addestramento: **Gini = 0 in ogni foglia**.

> ⚠️ **Gini = 0 ovunque non è una buona notizia.** Foglie tutte pure sul training set significano che l’albero ha **memorizzato** gli esempi, compresi eventuali errori ed esempi anomali: è il rischio di overfitting del §1.4. Le prestazioni vere si misurano su dati **mai visti** (Lezione 07, §3).

**Usare l’albero** (pp. 33–36). Prima con un **campione di addestramento**, poi con un **nuovo campione**: l’input viene riconosciuto come **BAROLO** (`class_0`).

<!-- SOURCE_VISUAL id="IS12-V14" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="29" type="schermata" description="Inizializzazione e addestramento dell’albero in Python" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V15" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="30" type="diagramma" description="Albero addestrato a 3 livelli sul dataset Wine" reason="Nodi, soglie e valori di Gini visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V16" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="31" type="diagramma" description="Albero addestrato a 3 livelli (dettaglio)" reason="Nodi e soglie visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V17" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="32" type="diagramma" description="Albero a 5 livelli con Gini = 0 in ogni foglia" reason="Nodi e soglie visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V18" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="34" type="schermata" description="Uso dell’albero su un campione di addestramento" reason="Codice e output presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V19" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="36" type="schermata" description="Uso dell’albero su un nuovo campione riconosciuto come Barolo (class_0)" reason="Codice e output presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le pp. 37–39 riassumono il codice per l’**albero di decisione** e per la **random forest** in Python.

> 💡 **Codice indicativo con scikit-learn (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> from sklearn.datasets import load_wine
> from sklearn.tree import DecisionTreeClassifier, plot_tree
> from sklearn.ensemble import RandomForestClassifier
>
> wine = load_wine()                 # oggetto Bunch
> X, y = wine.data, wine.target      # 178 x 13, classi 0/1/2
>
> tree = DecisionTreeClassifier(max_depth=3)   # criterio Gini per default
> tree.fit(X, y)
> plot_tree(tree, feature_names=wine.feature_names, class_names=wine.target_names)
> print(tree.predict(X[:1]))         # un campione di addestramento
>
> forest = RandomForestClassifier(n_estimators=100)
> forest.fit(X, y)
> print(forest.feature_importances_) # importanza media delle feature
> ```
> Le istruzioni esatte usate dal docente sono visibili nelle figure delle pp. 29–39.

<!-- SOURCE_VISUAL id="IS12-V20" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="37" type="schermata" description="Riepilogo del codice dell’albero di decisione in Python" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V21" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="38" type="schermata" description="Codice della random forest in Python" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V22" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="39" type="schermata" description="Risultati della random forest in Python" reason="Codice e output presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Alberi e foreste per capire i dati**

_(slide pp. 40–41)_

Alberi e random forest non sono solo modelli di classificazione potenti, ma anche **strumenti per capire i dati** (p. 40). Imparando una **gerarchia di regole**, un albero mostra:

- come i dati si possono separare in **gruppi omogenei**;
- **quali feature** servono a distinguere le classi;
- **dove emergono** in modo naturale le frontiere di decisione.

La random forest estende l’idea combinando molti alberi diversi:

- ogni albero coglie una **visione diversa** dei dati;
- l’insieme dà decisioni **più stabili e robuste**;
- l’**importanza delle feature** mostra quali variabili contano di più **in media**.

---

### **5. Simulazione d’esame di metà corso**

_(slide pp. 42–112)_

#### **5.1. Regole e metodo**

_(slide pp. 42–47)_

La simulazione serve a **verificare la preparazione**, **non a studiare** (p. 42). Le simulazioni precedenti erano alla Lezione 4 (sulle prime 4 lezioni) e alla Lezione 7 (sulle prime 7); questa si concentra sulle **Lezioni 4–12** (p. 43).

**Regole** (p. 44): **1 risposta corretta** → peso 1; **N risposte sbagliate** → peso 0; **nessun peso negativo**. Non ci sono penalità se non si risponde, **ma è meglio rispondere**.

> 📌 **Come rispondere** (p. 45). **Non cercare la risposta giusta!**
> - **Passo 0**: leggere il testo facendo attenzione a **negazioni** e indicazioni («non crescente», «minore di», …).
> - **Passo 1**: **scartare** le risposte che sembrano sbagliate usando un **ragionamento**, un **controesempio**, una **nozione** o un **calcolo di dimensionamento**.
> - **Passo 2**: scegliere la risposta che **sembra meno sbagliata** delle altre.

**Preparazione** (p. 46): stampare la scheda con una riga per domanda (numero, corretta sì/no, 1/0, note) e il **totale**; ogni risposta corretta vale 1 punto. La stessa scheda è fornita come file separato.

_(scores p. 1)_

La **scheda punteggi** allegata (1 pagina) riproduce la tabella della p. 46: colonne «#», «Correct?», «1/0», «Notes» e riga finale «TOTAL».

<!-- SOURCE_VISUAL id="IS12-V23" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="46" type="tabella" description="Scheda punteggi della simulazione da stampare" reason="Tabella presente come modulo grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Le venti domande**

_(slide pp. 48–107)_

Ogni domanda è preceduta da una slide «On your mark, get set, …» (pp. 47, 50, 53, …, 105).

**D1 — Sorgenti di dati** (pp. 48–49). Considerando i dispositivi IoT come fonte di dati per sistemi intelligenti **esterni** (non integrati nel dispositivo), quali dispositivi si possono davvero usare? 1. passivi; 2. attivi; 3. dinamici; **4. tutti i precedenti**; 5. nessuno.
**Risposta: 4.** Promemoria della slide: «dati passivi» **non** significa applicazione passiva; poiché i sensori vanno gestiti, è l’applicazione a farsi carico della logica necessaria (Lezione 05, §6.2).

**D2 — Progetto** (pp. 51–52). La pratica corretta per le reti neurali è: 1. partire dal deep learning perché è la tecnologia più avanzata; 2. partire dal deep learning e usare i metodi classici come riferimento; **3. partire da reti semplici prima di considerare il deep learning**.
**Risposta: 3.** È un’applicazione del **rasoio di Occam** (Lezione 05, §3.2).

**D3 — Dati mancanti** (pp. 54–55). Riempire i valori mancanti con media, moda o mediana dei valori osservati: 1. è insolito e poco comune; **2. è una soluzione semplice ed efficace se il metodo di apprendimento non sa gestire i dati mancanti**; 3. non è possibile, perché sono solo statistiche descrittive.
**Risposta: 2** (Lezione 06, §7.3).

**D4 — Data leakage** (pp. 57–58). Qual è la situazione peggiore? **1. il passaggio indesiderato di dati dal test all’addestramento**; 2. dall’addestramento al test; 3. nessuna, è normale.
**Risposta: 1.** Commento: un modello grande può imparare perfettamente, fino all’overfitting, i dati di addestramento; se si **sottraggono dati al test di generalizzazione**, la stima diventa **più ottimistica** (Lezione 07, §3.3).

**D5 — Definizione** (pp. 60–61). Un’informazione aggiuntiva permette al modello di sapere qualcosa che altrimenti non saprebbe, invalidando la stima delle prestazioni. Si chiama: **1. data leakage**; 2. pre-elaborazione; 3. armonizzazione; 4. wrangling.
**Risposta: 1.**

**D6 — Gradi di libertà** (pp. 63–64). Il numero di gradi di libertà è importante: 1. per dimensionare il numero di vettori del dataset; 2. per evitare l’overfitting; **3. tutte le precedenti**; 4. nessuna.
**Risposta: 3** (Lezione 07, §2).

**D7 — Metrica coseno** (pp. 66–67). 1. due vettori con la stessa orientazione hanno similarità coseno 1; 2. due vettori a 90° hanno similarità 0; **3. tutte le precedenti**; 4. nessuna.
**Risposta: 3** (Lezione 08, §4.2).

**D8 — Similarità nelle immagini** (pp. 69–70). Quali feature permettono un **confronto veloce** basato su un **vettore breve** di elementi o bit? 1. pHash; 2. aHash; **3. tutte le precedenti**; 4. cross-correlazione.
**Risposta: 3.** La cross-correlazione è sbagliata (confronta intere immagini pixel per pixel); vale lo stesso per il confronto di **istogrammi**, anch’esso su un vettore breve (Lezione 08, §5).

**D9 — Similarità nel dataset** (pp. 72–73). 1. richiede più spazio e tempo; 2. può migliorare la generalizzazione; **3. entrambe**; 4. nessuna.
**Risposta: 3.** C’è un **dilemma** tra i due casi: duplicati accidentali contro data augmentation (Lezione 08, §2).

**D10 — Cardinalità dei controlli** (pp. 75–76). In un dataset di **1100** immagini etichettate la ricerca dei duplicati si fa: 1. esplorando a mano, perché il numero non è critico; **2. con iterazioni automatiche**.
**Risposta: 2.** Un controllo di base richiede $N(N-1) = 1100 \times 1099 = 1\,208\,900$, cioè **oltre 1,2 milioni** di confronti (Lezione 08, §1.3).

**D11 — Errori di etichettatura** (pp. 78–79). Qual è in genere il caso peggiore per l’accuratezza di generalizzazione? ERR1 = duplicati con la stessa etichetta; ERR2 = duplicati con etichette diverse. 1. ERR1; **2. ERR2**; 3. equivalenti.
**Risposta: 2.** (La slide scrive «EER2» ed «EE2» per ERR2.)

**D12 — Correlazione e convoluzione** (pp. 81–82). **1. sono molto simili nel significato e nell’espressione matematica**; 2. espressione simile, ma significato e uso completamente diversi; 3. nessuna relazione.
**Risposta: 1.** Differiscono solo per il segno degli indici, cioè per il ribaltamento del kernel (Lezione 10, §2.1).

**D13 — Autocorrelazione** (pp. 84–85). Che caratteristica ha la mappa `O = xcorr2(A, A)` di un’immagine generica? 1. un plateau centrale piatto e rumoroso; **2. un picco evidente al centro con un massimo ben definito**; 3. non si può fare con una sola immagine.
**Risposta: 2** (Lezione 10, §1).

**D14 — Outlier estremi** (pp. 87–88). Con outlier estremi conviene: **1. clipping delle feature**; 2. normalizzazione min-max; 3. norma z.
**Risposta: 1** (Lezione 10, §5).

**D15 — Scalatura logaritmica** (pp. 90–91). Si applica tipicamente in caso di: 1. outlier; 2. valori negativi; **3. un intervallo di valori molto ampio (>0)**.
**Risposta: 3.** In questo caso min-max e z-score **non sono efficaci**. I valori negativi escludono il logaritmo.

**D16 — Variabili visive** (pp. 93–94). Per disegnare molte figure di merito di una rete su un nuovo dataset, qual è l’ordine corretto degli attributi visivi, da **sinistra: bassa accuratezza** a **destra: ALTA ACCURATEZZA**? 1. intensità del colore > tinta > lunghezza; 2. area > lunghezza > tinta; 3. pendenza > angolo > volume; **4. tinta > area > lunghezza**.
**Risposta: 4.** L’ordine va dall’attributo letto con **meno precisione** (la tinta) a quello letto con **più precisione** (la lunghezza), coerente con la gerarchia percettiva della Lezione 11, §7.2.

**D17 — Intervalli di confidenza** (pp. 96–97). Si possono rappresentare nello stesso grafico delle figure di merito? 1. no, hanno unità e significato diversi; **2. sì, hanno le stesse unità e lo stesso significato**.
**Risposta: 2** (Lezione 11, §7.4).

**D18 — Passi con visualizzazione** (pp. 99–100). Nel flusso 1) prendere i dati, 2) pulire e manipolare, 3) addestrare, 4) testare, 5) migliorare il progetto, in quali passi entra principalmente la visualizzazione? A. #1; B. #5; C. #1 e #5; **D. #2, #3 e #5**.
**Risposta: D.** Corrisponde ai tre momenti della Lezione 11, §1.

**D19 — Autocorrelazione con pattern ripetuti** (pp. 102–104). Per un’immagine con **similarità interna** (pattern ripetuti), l’uscita di `xcorr2(A, A)`: A. non si può calcolare; B. è un plateau piatto con un picco centrale; **C. ha molti picchi e un massimo evidente**; D. ha molti picchi equivalenti con lo stesso massimo.
**Risposta: C.** I picchi secondari corrispondono agli spostamenti in cui i pattern si sovrappongono; il massimo assoluto resta al centro, a spostamento nullo. La p. 104 ricorda che i **difetti** si trovano come differenze o mancanze in questo pattern (Lezione 10, §2.4).

**D20 — Quadro normativo** (pp. 106–107). Il quadro normativo UE sull’AI è: A. centrato sui servizi pubblici; B. sulla sanità; C. sulla privacy dei dati; **D. nessuna delle altre opzioni**.
**Risposta: D**, «perché è molto più ampio» (Lezione 02, §1).

> 💡 **Il metodo applicato (nota didattica).** In molte domande la risposta giusta si trova **escludendo**: in D13 l’opzione 3 è falsa per definizione (l’autocorrelazione usa una sola immagine), in D15 l’opzione 2 è falsa perché il logaritmo non è definito per valori negativi, in D10 un calcolo di dimensionamento (1,2 milioni di confronti) esclude l’esplorazione manuale.

<!-- SOURCE_VISUAL id="IS12-V24" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="104" type="immagini" description="Difetti individuati come mancanze nel pattern di similarità, a commento della D19" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3. Dopo la simulazione**

_(slide pp. 108–112)_

La p. 108 contiene un’immagine di chiusura. **Com’è andata la tua accuratezza?** (p. 109). In caso di dubbi, **contattare il docente**.

<!-- SOURCE_VISUAL id="IS12-V25" source="Lesson_12_Decision_Tree_Random_Forests_Exam_Simulation.pdf" page="108" type="immagine" description="Immagine di chiusura della simulazione" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**E gli argomenti non presenti nella simulazione?** (p. 110). Sono **ugualmente importanti**, **possono uscire all’esame**. La simulazione non serve a studiare, ma a capire se si sta seguendo con profitto e, se serve, a **cambiare metodo**. Conviene **creare la propria simulazione** scorrendo tutte le slide e chiedendosi: **che cosa si può chiedere su questa slide?**

---

### **6. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Albero binario** / **non binario** | due rami, più profondo e nodi semplici / più rami, meno profondo e nodi complessi |
| **Albero profondo** / **poco profondo** | più dettagli, rischio di overfitting / più semplice, può perdere pattern |
| **Gini** / **entropia** | probabilità di errore di etichettatura, usato da CART / incertezza in bit, usata da ID3 e C4.5 |
| **Entropia 0** / **entropia massima** | nodo puro / classi equiprobabili (1 bit se binario) |
| **Frontiera di un albero** / **frontiera lineare** | a scalini, allineata agli assi / retta obliqua |
| **Albero** / **random forest** | spiegabile ma instabile / robusta e accurata, meno spiegabile |
| **Bootstrap** / **bagging** | campionamento con reinserimento / aggregazione delle predizioni (voto o media) |
| **Gini = 0 sul training** / **buon modello** | albero che ha memorizzato i dati / modello che generalizza su dati nuovi |
| **Leakage test → training** / **training → test** | caso peggiore / caso meno grave |
| **ERR1** / **ERR2** | duplicati con la stessa etichetta, spreco / duplicati con etichette diverse, errore grave |
| **Autocorrelazione generica** / **con pattern ripetuti** | un picco centrale netto / molti picchi con un massimo evidente |

---

### **7. Sintesi della lezione**

_(slide pp. 41, 111–112)_

> ✅ **Punti principali: alberi e foreste** (p. 41).
> - **Albero di decisione**: un modello classico e **spiegabile**, veloce, con frontiere **allineate agli assi**.
> - La conoscenza nei nodi si misura con **impurità di Gini** ed **entropia**; profondità e potatura controllano l’overfitting.
> - **Addestrare, disegnare e usare** un albero in Python sul dataset Wine.
> - **Random forest**: meno spiegabile, ma decisioni **più robuste** grazie a bootstrap e voto di maggioranza.

> ✅ **Punti principali: la simulazione** (pp. 111–112).
> - Le simulazioni **non sono materiale di studio**, ma servono a **verificare** la preparazione e il **metodo di studio**.
> - **Rivedere le slide** chiedendosi in modo attivo: «**che cosa si può chiedere qui?**».
> - **Usare la statistica a proprio vantaggio**: ragionare non per trovare la risposta giusta, ma per **escludere quelle sbagliate**.
