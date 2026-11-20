## **Lezione 12: Alberi di decisione, random forest e simulazione d’esame**

Gli alberi di decisione rappresentano regole esplicite che partizionano lo spazio delle feature. Profondità, impurità di Gini ed entropia descrivono aspetti distinti della struttura; validazione e potatura controllano il rischio di sovradattamento. La random forest riduce la varianza aggregando alberi addestrati su campioni bootstrap e sottoinsiemi di feature. Esempi didattici su frutti e il dataset UCI Wine mostrano come passare dalle misure a una regola e a una predizione, preservando provenienza e limiti dei dati. Dieci quesiti di esercitazione riprendono concetti fondamentali di preparazione e valutazione dei dati.


### **0. Titolo della lezione**



---

### **1. Alberi di decisione**


#### **1.1. Perché gli alberi di decisione**

**Alberi di decisione per sicurezza e privacy**. Gli alberi di decisione sono tra i modelli di ML **più usati nelle applicazioni di sicurezza**, grazie a **semplicità**, **interpretabilità** ed **efficienza**. Supportano in modo naturale il **ragionamento basato su regole**, un requisito chiave nei sistemi di sicurezza, privacy e conformità:

- **logica decisionale esplicita e interpretabile**;
- **inferenza veloce**, adatta a sistemi di sicurezza **in tempo reale**;
- facili da **verificare** (audit), da **correggere** e da **spiegare** agli analisti.

**Formato dei dati di addestramento: X, Y**. L’esempio classico è «**Andiamo a giocare a tennis?**»: ogni riga descrive una giornata con alcune feature ($X$) e la decisione presa ($Y$). Gli alberi di decisione sono **perfetti per i dati tabellari**.

<!-- SOURCE_VISUAL id="IS12-V01" source="L12-web.pdf" page="5" type="tabella" description="Dataset Play Tennis con feature X e decisione Y" reason="Tabella dei dati presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il dataset Play Tennis (nota didattica).** Nella versione classica di Quinlan ci sono **14 giornate** descritte da quattro feature categoriche: **Outlook** (soleggiato, nuvoloso, pioggia), **Temperature** (caldo, mite, fresco), **Humidity** (alta, normale), **Wind** (debole, forte); la classe è **PlayTennis** (sì/no), con 9 «sì» e 5 «no». Se la tabella della figura coincide con questa versione, valgono i calcoli del §1.4.

#### **1.2. Spazio delle ipotesi e spiegabilità**

Lo **spazio delle ipotesi** di un albero rappresenta uscite binarie (0/1) in funzione di due feature $x_1$ e $x_2$.

<!-- SOURCE_VISUAL id="IS12-V02" source="L12-web.pdf" page="6" type="diagramma" description="Spazio delle ipotesi di un albero di decisione" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V03" source="L12-web.pdf" page="7" type="grafico" description="Uscita 1/0 di un albero in funzione di x1 e x2" reason="Regioni di decisione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Spiegabilità degli alberi**. Gli alberi di decisione sono **intrinsecamente interpretabili**: la loro struttura rende esplicito **come e perché** si prende una decisione, organizzandola come una **sequenza di regole semplici e leggibili**.
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
> È la stessa forma «if-then» dell’apprendimento deduttivo, ma qui le regole sono **apprese dai dati**.

**Alberi binari e non binari**:

| | **Alberi binari** | **Alberi non binari** |
|---|---|---|
| Nodi | ogni nodo interno divide i dati in **due rami** | i nodi possono avere **più rami** |
| Caratteristiche | struttura più semplice, **potatura** più facile | usati spesso con feature **categoriche**; alberi **meno profondi** ma nodi **più complessi** |
| Compromesso | **profondità maggiore**, decisioni più semplici | **profondità minore**, nodi più complessi |

#### **1.3. Frontiere di decisione**


L’esempio dei **dati sulla frutta** mostra come un albero di decisione separa le classi.

> 📌 **Frontiere allineate agli assi**. Le frontiere di decisione di un albero sono **allineate agli assi**: ogni nodo confronta **una sola feature** con una soglia ($x_j \le s$), quindi taglia lo spazio con un piano **perpendicolare** a quell’asse. Il risultato è una partizione dello spazio in **rettangoli** (iper-rettangoli in più dimensioni).

> 💡 **Conseguenza pratica (nota didattica).** Una frontiera **obliqua**, come $x_1 = x_2$, un albero la può solo **approssimare a scalini**, con molti nodi. Una retta di un modello lineare la rappresenterebbe con due parametri. Il confronto con le frontiere di altri modelli sarà approfondito nelle.

<!-- SOURCE_VISUAL id="IS12-V04" source="L12-web.pdf" page="13" type="diagramma" description="Albero di decisione sul caso dei dati sulla frutta" reason="Albero presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V05" source="L12-web.pdf" page="13" type="grafico" description="Frontiere di decisione allineate agli assi di un albero" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V06" source="L12-web.pdf" page="15" type="grafico" description="Confronto tra le frontiere di decisione di modelli diversi" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->



**Un esempio sintetico di frontiere**. Trentadue frutti simulati sono descritti da larghezza e altezza in centimetri, con etichette mela, arancia e limone. Ogni nodo confronta una feature con una soglia; cinque test producono regioni rettangolari e regole if–then equivalenti all’albero. La figura delle regioni descrive il modello addestrato su quei punti: da sola non misura l’accuratezza su frutti nuovi. Il confronto con k-NN e SVM mostra che classificatori diversi inducono frontiere geometriche differenti sugli stessi dati.

<!-- SOURCE_VISUAL id="IS12-27-V01" source="L12-web.pdf" page="12" type="grafico" description="32 osservazioni sintetiche, larghezza e altezza, colorate per classe" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS12-27-V02" source="L12-web.pdf" page="14" type="albero e regioni" description="Cinque soglie che collegano le regole dell’albero alle regioni decisionali sui frutti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.4. Che cosa sa un albero: profondità, Gini, entropia**


Per controllare la conoscenza contenuta in un albero si guardano:

1. la **profondità** dell’albero;
2. l’**informazione nei nodi**: **impurità di Gini** ed **entropia**.

**1) Profondità**. Dice **quante domande** l’albero deve fare dalla radice a una risposta finale (una foglia).

| Albero | Pro | Contro |
|---|---|---|
| **profondo** | logica più complessa, cattura più dettagli | rischio di **overfitting** |
| **poco profondo** | più semplice, veloce e interpretabile | può **perdere pattern** importanti |

La profondità è come il numero di passi con cui il «piccolo detective» arriva alla decisione. Gli alberi di decisione sono **molto veloci**: pochi `if`.

**2) Impurità di Gini**. Misura la **purezza dei nodi**: la **probabilità** che un elemento scelto a caso sia **classificato in modo sbagliato** se lo si etichettasse secondo la distribuzione delle classi nel nodo. Si usa per valutare le divisioni in algoritmi come **CART**.

**Entropia**. Misura l’**incertezza** in un nodo: quanto sono **mescolate** le classi; entropia più alta significa più confusione. Si usa negli algoritmi **ID3** e **C4.5**. Viene dalla **teoria dell’informazione** (Shannon, 1948), e la **base del logaritmo** definisce l’unità di informazione. In questo contesto l’«informazione» è una cosa **negativa**: significa che il nodo ha **troppa incertezza**.

- **Entropia minima** (zero): **nessuna incertezza**, tutti i campioni appartengono a **una sola classe**.
- **Entropia massima**: tutte le classi sono **ugualmente probabili**; nel caso **binario** $H_{\max} = 1$.
- Perché $\log_2$? Perché l’entropia si misura **in bit**: con $p = 50\%$, $H = 1$ bit.

> 📌 **Indici di purezza.** Per un nodo con $C$ classi e proporzioni $p_1, \dots, p_C$:
> $$\text{Gini} = 1 - \sum_{i=1}^{C} p_i^2 \qquad\qquad H = -\sum_{i=1}^{C} p_i \log_2 p_i$$
> Con la convenzione $0 \cdot \log_2 0 = 0$. Entrambe valgono **0** per un nodo puro e sono **massime** quando le classi sono equiprobabili.

| Caso | Gini massimo | Entropia massima |
|---|---|---|
| **2 classi** | $1 - 2 \cdot 0{,}5^2 = 0{,}5$ | $1$ bit |
| **3 classi** (mela, pera, banana) | $1 - 3 \cdot (1/3)^2 \approx 0{,}667$ | $\log_2 3 \approx 1{,}585$ bit |

> 📌 **Guadagno di informazione (nota didattica).** Una divisione dell’insieme $S$ secondo la feature $A$ produce i sottoinsiemi $S_v$. Il guadagno è la riduzione di entropia:
> $$\text{IG}(S, A) = H(S) - \sum_{v} \frac{|S_v|}{|S|}\, H(S_v)$$
> ID3 sceglie a ogni nodo la feature con il **guadagno massimo**.

> 💡 **Calcolo su Play Tennis (nota didattica, versione classica).** Alla radice ci sono 9 «sì» e 5 «no»:
> $$H(S) = -\tfrac{9}{14}\log_2 \tfrac{9}{14} - \tfrac{5}{14}\log_2 \tfrac{5}{14} \approx 0{,}940 \qquad \text{Gini}(S) = 1 - \left(\tfrac{9}{14}\right)^2 - \left(\tfrac{5}{14}\right)^2 \approx 0{,}459$$
> Dividendo per Outlook: soleggiato (2 sì, 3 no) e pioggia (3 sì, 2 no) hanno $H \approx 0{,}971$, nuvoloso (4 sì) ha $H = 0$. Quindi
> $$\text{IG}(S, \text{Outlook}) \approx 0{,}940 - \tfrac{5}{14}(0{,}971) - \tfrac{4}{14}(0) - \tfrac{5}{14}(0{,}971) \approx 0{,}247$$
> È il guadagno più alto tra le quattro feature: per questo Outlook è la radice. Temperature ha il guadagno più basso e non viene mai scelta.

<!-- SOURCE_VISUAL id="IS12-V07" source="L12-web.pdf" page="24" type="formule" description="Entropia minima e massima con l’esempio a tre classi mela, pera, banana" reason="Formule ed esempio resi come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V08" source="L12-web.pdf" page="26" type="grafico" description="Entropia e Gini in funzione della proporzione per un problema binario" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’entropia nel machine learning** è un concetto molto importante, usato in:

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


**Algoritmi di addestramento comuni**: **ID3**, **C4.5**, **CART**.

| **Vantaggi** | **Svantaggi** | **Messa a punto** |
|---|---|---|
| **interpretabilità**: semplice da capire e visualizzare | **overfitting**: alberi complessi modellano il rumore e generalizzano peggio | **potatura** (*pruning*): riduce la dimensione dell’albero contro l’overfitting |
| **versatilità**: gestisce dati **numerici e categorici** | **instabilità**: piccoli cambiamenti nei dati possono dare un albero **completamente diverso** | **selezione delle feature**: migliora le prestazioni |
| **preparazione minima dei dati**: poca o nessuna normalizzazione | **bias con dati sbilanciati**: può favorire le classi dominanti | **metodi ensemble**: combinare molti alberi (random forest) migliora l’accuratezza |
| **non parametrico**: nessuna ipotesi sulla distribuzione dei dati (per esempio gaussiana) | | |

> 💡 **Perché non serve normalizzare.** Ogni nodo confronta una feature con una soglia: moltiplicare la feature per 1000 sposta la soglia ma non cambia **quali** campioni vanno a destra o a sinistra. Per un albero, la normalizzazione delle feature è in genere irrilevante.

---

### **2. Dall’albero alla foresta**


**L’instabilità degli alberi**. Gli alberi sono **molto sensibili** ai dati di addestramento: piccole modifiche al dataset possono produrre alberi molto diversi. Il motivo è che un albero si costruisce con un’**ottimizzazione greedy e locale**: a ogni nodo si sceglie la divisione migliore **in quel momento**, e una scelta diversa in alto cambia tutto ciò che sta sotto. Campionando **sottoinsiemi diversi** del dataset si possono addestrare **più alberi**, ciascuno con una **visione diversa** dei dati.

> 📌 **Random forest**.
> - Lo stesso esempio viene valutato da **più alberi di decisione**, ciascuno con una **predizione indipendente**.
> - Gli alberi sono addestrati su **campioni bootstrap** diversi dei dati di addestramento.
> - Per la **casualità dei dati e delle feature**, gli alberi prendono **decisioni diverse**.
> - Il **bagging** (*Bootstrap Aggregating*) combina le predizioni di tutti gli alberi: **voto di maggioranza** per la classificazione, **media** per la regressione.
> - Vince la classe con **più voti**: gli **errori dei singoli alberi tendono a compensarsi**.

> 📌 **In formula (nota didattica).** Con $B$ alberi $h_1, \dots, h_B$:
> $$\hat{y}_{\text{classificazione}} = \operatorname{moda}\{h_1(x), \dots, h_B(x)\} \qquad \hat{y}_{\text{regressione}} = \frac{1}{B} \sum_{b=1}^{B} h_b(x)$$
> Un campione bootstrap si ottiene estraendo $N$ esempi **con reinserimento** da $N$: in media contiene circa il **63,2%** di esempi distinti, perché la probabilità che un esempio non sia mai estratto è $\left(1 - \tfrac{1}{N}\right)^N \to e^{-1} \approx 0{,}368$. La «casualità delle feature» consiste nel considerare, a ogni nodo, solo un **sottoinsieme casuale** delle feature.

<!-- SOURCE_VISUAL id="IS12-V09" source="L12-web.pdf" page="32" type="diagramma" description="Random forest: alberi su campioni bootstrap e voto di maggioranza" reason="Schema della foresta visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Albero o foresta: quando usare quale**. Offrono **compromessi diversi** tra interpretabilità e prestazioni:

| **Albero di decisione** quando… | **Random forest** quando… |
|---|---|
| l’**interpretabilità** è critica | **accuratezza e robustezza** sono la priorità |
| le decisioni vanno **spiegate o verificate** | il dataset è **rumoroso o complesso** |
| il dataset è **piccolo o semplice** | bisogna **ridurre l’overfitting** |
| le regole vanno **ispezionate o validate** da persone | una **perdita di interpretabilità** è accettabile |

> ✅ **In una frase**: **un albero per spiegare le decisioni, una foresta per migliorare le prestazioni.**

---

### **3. Laboratorio: l’ispettore del vino**


#### **3.1. Il problema**

**Laboratorio Python: controllo qualità del vino con alberi di decisione.**

Nelle grandi cantine la **composizione** di un vino è una questione di tradizione, scienza e precisione: una bottiglia di **Sassicaia** può costare oltre 400 €. La miscela dei giusti **cultivar** (vitigni) determina non solo il gusto, ma anche l’**identità** e la **certificazione** del vino. Per esempio, il **Chianti** deve contenere per disciplinare **almeno il 70% di Sangiovese**, e può essere miscelato con Canaiolo, Colorino o anche Cabernet Sauvignon.

**Perché serve un sistema intelligente che riconosca il cultivar?**

- il **controllo manuale** della composizione è **lento e soggettivo**;
- **etichette sbagliate** o **sofisticazioni** danneggiano marchio, prezzo e legalità;
- le cantine di alta gamma vogliono sistemi **automatici** per rilevare e classificare vini o cultivar da **dati chimici o di sensori**.

**Con un albero di decisione si può**: analizzare le feature del vino (**alcol**, **flavonoidi**, **prolina**…), **predire** il probabile cultivar o la miscela, supportare **etichettatura automatica**, **rilevamento delle frodi** e **monitoraggio della produzione**.

**Procedura**:

1. caricare un **dataset** adeguato;
2. **addestrare** l’albero;
3. usare l’albero per **nuove decisioni**.

#### **3.2. Il dataset Wine**

Il dataset è il **Wine** dell’archivio UCI. La mostra le **classi**.

> 💡 **Il dataset (nota didattica).** Contiene **178 vini** della stessa regione italiana, provenienti da **tre cultivar**, descritti da **13 feature chimiche** (alcol, acido malico, ceneri, alcalinità delle ceneri, magnesio, fenoli totali, flavonoidi, fenoli non flavonoidi, proantocianine, intensità del colore, tonalità, rapporto OD280/OD315, prolina). Le classi hanno 59, 71 e 48 esempi. Le tre classi sono trattate come etichette anonime: i nomi di cultivar e i marchi raffigurati non sono una base per attribuire l’identità commerciale a una classe.

**Caricamento**. In scikit-learn la variabile `wine` è un oggetto **`Bunch`**: una struttura dati speciale che funziona **come un dizionario** ma permette anche l’**accesso per attributo** (per esempio `wine.data` oltre a `wine["data"]`). La controlla le variabili.

<!-- SOURCE_VISUAL id="IS12-V10" source="L12-web.pdf" page="42" type="schermata" description="Caricamento del dataset Wine UCI" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V11" source="L12-web.pdf" page="41" type="schermata" description="Classi del dataset Wine" reason="Contenuto presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V12" source="L12-web.pdf" page="42" type="schermata" description="Oggetto Bunch di scikit-learn con il dataset Wine" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V13" source="L12-web.pdf" page="43" type="schermata" description="Controllo delle variabili del dataset" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. Addestrare e usare l’albero**

**Inizializzazione e addestramento**. **L’albero addestrato con 3 livelli**. **Con 5 livelli** l’albero **impara perfettamente** il dataset di addestramento: **Gini = 0 in ogni foglia**.

> ⚠️ **Gini = 0 ovunque non è una buona notizia.** Foglie tutte pure sul training set significano che l’albero ha **memorizzato** gli esempi, compresi eventuali errori ed esempi anomali: è il rischio di overfitting del §1.4. Le prestazioni vere si misurano su dati **mai visti**.

**Usare l’albero**. Un campione di training verifica che la pipeline funzioni; non misura la generalizzazione. Per un campione nuovo vanno misurate le stesse tredici variabili, nelle unità e nell’ordine attesi. La classe prevista resta una delle tre etichette anonime: non certifica cultivar, marchio né autenticità. Il risultato va validato su campioni indipendenti e fuori distribuzione.

<!-- SOURCE_VISUAL id="IS12-V14" source="L12-web.pdf" page="44" type="schermata" description="Inizializzazione e addestramento dell’albero in Python" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V15" source="L12-web.pdf" page="45" type="diagramma" description="Albero addestrato a 3 livelli sul dataset Wine" reason="Nodi, soglie e valori di Gini visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V16" source="L12-web.pdf" page="46" type="diagramma" description="Albero addestrato a 3 livelli (dettaglio)" reason="Nodi e soglie visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V17" source="L12-web.pdf" page="47" type="diagramma" description="Albero a 5 livelli con Gini = 0 in ogni foglia" reason="Nodi e soglie visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V18" source="L12-web.pdf" page="48" type="schermata" description="Uso dell’albero su un campione di addestramento" reason="Codice e output presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V19" source="L12-web.pdf" page="50" type="schermata" description="Uso dell’albero su un nuovo campione classificato in una delle classi anonime del dataset" reason="Codice e output presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Gli esempi in Python mostrano il dataset, l’addestramento e l’interpretazione dell’albero e della foresta. Il train set illustra le regole apprese; per stimare la generalizzazione servono validation o cross-validation e un test indipendente.

> 💡 **Codice indicativo con scikit-learn (ricostruzione didattica, non trascritta dalle figure).**
> ```python
> from sklearn.datasets import load_wine
> from sklearn.tree import DecisionTreeClassifier, plot_tree
> from sklearn.ensemble import RandomForestClassifier
>
> wine = load_wine(as_frame=True)     # oggetto Bunch
> X, y = wine.data, wine.target      # 178 x 13, classi anonime 0/1/2
>
> tree = DecisionTreeClassifier(max_depth=3)   # criterio Gini per default
> tree.fit(X, y)
> plot_tree(tree, feature_names=wine.feature_names, class_names=wine.target_names)
> print(tree.predict(X.iloc[[0]]))   # demo sul training, non test
>
> forest = RandomForestClassifier(n_estimators=100)
> forest.fit(X, y)
> print(forest.feature_importances_) # importanza media delle feature
> ```
> La versione del codice è indicativa; la valutazione richiede uno split indipendente e, se necessaria, una pipeline che includa il preprocessing.

<!-- SOURCE_VISUAL id="IS12-V20" source="L12-web.pdf" page="52" type="schermata" description="Riepilogo del codice dell’albero di decisione in Python" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V21" source="L12-web.pdf" page="53" type="schermata" description="Codice della random forest in Python" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS12-V22" source="L12-web.pdf" page="54" type="schermata" description="Risultati della random forest in Python" reason="Codice e output presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Alberi e foreste per capire i dati**


Un albero espone la gerarchia di test e le regioni in cui assegna classi. La lettura dei nodi aiuta a capire quali soglie separano gli esempi osservati; non dimostra che le feature siano cause del fenomeno. La random forest aggrega alberi con campioni bootstrap e sottoinsiemi casuali di feature: riduce la varianza quando gli errori dei singoli alberi non sono perfettamente correlati, al prezzo di decisioni meno leggibili come singoli percorsi. La scelta dipende da accuratezza, latenza, memoria, spiegabilità e incertezza della validazione. Per la classe maggioritaria o per una singola feature dominante, il risultato va confrontato con baseline e sottoinsiemi indipendenti.

<!-- SOURCE_VISUAL id="IS12-27-V03" source="L12-web.pdf" page="17" type="schema" description="Stesso albero Play Tennis mostrato come struttura, profondità e regole" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS12-27-V04" source="L12-web.pdf" page="31" type="schema" description="Campionamento bootstrap con ripetizioni e tre alberi differenti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

In un esempio sintetico, **32 frutti** sono descritti da larghezza e altezza e assegnati a tre classi. Ogni confine della mappa corrisponde a una regola su una feature; le regioni rettangolari sono l’effetto delle soglie parallele agli assi. k-NN produce frontiere determinate dai vicini, mentre una SVM RBF può generare confini curvi. Il confronto mostra la forma degli ipotizzatori, non quale modello generalizzi meglio senza una valutazione valida.

Alberi e random forest possono essere **strumenti per descrivere i dati**. Imparando una gerarchia di regole, un albero mostra:

- come i dati si possono separare in **gruppi omogenei**;
- **quali feature** servono a distinguere le classi;
- **dove emergono** in modo naturale le frontiere di decisione.

La random forest estende l’idea combinando molti alberi diversi:

- ogni albero coglie una **visione diversa** dei dati;
- l’insieme dà decisioni **più stabili e robuste**;
- l’**importanza delle feature** mostra quali variabili contano di più **in media**.

---

### **5. Simulazione d’esame**


La simulazione presenta dieci quesiti con risposta e motivazione. Serve a esercitare la lettura attenta, l’esclusione delle alternative incompatibili e la spiegazione dei principi fondamentali.

#### **5.1. I dieci quesiti**

1. **Dispositivi IoT come fonti di dati**: sono utilizzabili dispositivi passivi, attivi e dinamici. La risposta comprende tutte le categorie; il termine “passivo” descrive la fonte, mentre l’applicazione esterna deve comunque gestire sensori e dati.
2. **Progettazione di reti neurali**: si parte da modelli semplici e si aumenta la complessità quando i dati e la validazione lo giustificano. È un’applicazione del principio di parsimonia.
3. **Imputazione dei mancanti**: media, mediana o moda sono metodi semplici e usati quando l’algoritmo non gestisce i mancanti; la scelta va fatta sul training set e può alterare le distribuzioni.
4. **Data leakage tra test e training**: usare informazioni del test durante l’addestramento rende la stima ottimistica, perché il modello ha accesso a dati destinati a misurare la generalizzazione.
5. **Definizione di data leakage**: informazione aggiuntiva che rende il modello consapevole di elementi che non dovrebbe conoscere e invalida la stima delle prestazioni.
6. **Gradi di libertà**: descrivono il numero di variabili indipendenti necessarie a determinare una soluzione. Collegarli alla dimensione del dataset e al rischio di overfitting è una regola progettuale utile, non un teorema universale.
7. **Similarità coseno**: vettori paralleli hanno similarità 1; vettori ortogonali hanno similarità 0. Entrambe le affermazioni sono corrette, perciò si sceglie “tutte le precedenti”.
8. **Confronti rapidi di immagini**: aHash e pHash trasformano immagini in firme compatte confrontabili con distanza di Hamming; anche gli istogrammi hanno rappresentazione breve. La cross-correlazione confronta mappe di pixel e non appartiene alla stessa classe di rappresentazioni compatte.
9. **Similarità nel dataset**: confrontare elementi può richiedere più spazio e tempo, ma anche aiutare la generalizzazione. I duplicati accidentali e la data augmentation creano esigenze diverse; la partizione deve mantenere indipendenza tra training e test.
10. **Ricerca di duplicati tra 1.100 immagini**: una procedura automatica è preferibile alla verifica manuale. Il confronto di tutte le coppie ordinate richiede $N(N-1)=1.208.900$; se si confronta una sola volta ciascuna coppia non ordinata, il numero è $N(N-1)/2=604.450$.

La distinzione tra confronti ordinati e coppie non ordinate chiarisce quale formula usare nel calcolo combinatorio.

<!-- SOURCE_VISUAL id="IS12-27-V03" source="L12-web.pdf" page="62" type="domanda" description="Quesito IoT e categorie di fonti dati" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->
<!-- SOURCE_VISUAL id="IS12-27-V04" source="L12-web.pdf" page="68" type="domanda" description="Quesito sull’imputazione dei valori mancanti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

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


> ✅ **Punti principali: alberi e foreste**.
> - **Albero di decisione**: un modello classico e **spiegabile**, veloce, con frontiere **allineate agli assi**.
> - La conoscenza nei nodi si misura con **impurità di Gini** ed **entropia**; profondità e potatura controllano l’overfitting.
> - **Addestrare, disegnare e usare** un albero in Python sul dataset Wine.
> - **Random forest**: meno spiegabile, ma decisioni **più robuste** grazie a bootstrap e voto di maggioranza.

> ✅ **Punti principali: la simulazione**.
> - Dieci quesiti svolti esercitano il ragionamento su fonti IoT, semplicità dei modelli, dati mancanti, leakage, gradi di libertà, coseno, similarità, duplicati e cardinalità dei confronti.
> - Il documento riserva altri dieci quesiti all’esercitazione in aula; tutti gli argomenti restano rilevanti.
> - Prima di scegliere, controllare definizioni, unità, numeratori e denominatori; distinguere le coppie ordinate dalle non ordinate nel conteggio dei confronti.
