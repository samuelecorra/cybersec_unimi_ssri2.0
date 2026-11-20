## **Lezione 16: Valutazione dell’accuratezza, dataset Iris, codifica delle variabili categoriche e laboratorio**

La valutazione di un sistema di apprendimento combina metriche numeriche, prove di robustezza e revisioni umane. Il percorso applica questi strumenti a regressori e classificatori, estende la valutazione ai foundation model, usa Iris per esplorare i dati e affronta la codifica delle variabili categoriche. Il laboratorio costruisce e analizza matrici di input e target in Colab e MATLAB.

### **0. Struttura della lezione**

__

Il filo conduttore va dalle metriche alla validazione dei modelli con output aperti, poi usa Iris per controllare rappresentazioni e preprocessing prima del laboratorio:

1. **valutazione dell’accuratezza e della confidenza** di classificatori e regressori: $R^2$, MSE, RMSE, matrice di confusione, ROC, analisi di sensibilità;
2. **gestione di base dei dataset**, codice in MATLAB e Colab;
3. uso di un **dataset di riferimento**: Iris;
4. **codifica** (*hard encoding*);
5. caricamento e gestione di base, esempio MATLAB, esempio Colab;
6. **analisi esplorativa dei dati** (EDA).

---

### **1. Valutare l’accuratezza**

__

#### **1.1. In quale passo si usa**

Valutazione di accuratezza e **confidenza** per classificatori e regressori. Serve in tre passi del flusso di lavoro:

| Passo | Chi la usa | Su quali dati |
|---|---|---|
| **#3** | il **metodo di apprendimento**, per ottimizzare i parametri | **training set** |
| **#4** | **il progettista**, per valutare la generalizzazione | **test set** |
| **#5** | **il progettista**, per migliorare il sistema (nuovi modelli, nuovi metodi di apprendimento) | — |

Espressione generale: $Y = \text{function}(X, \text{params})$, con metriche diverse per i **regressori** ($Y \in \mathbb{R}$, numeri reali) e per i **classificatori** ($Y \in \mathbb{Z}$, numeri interi).

#### **1.2. Regressori**

__

**Esempio**. Per Amazon si costruisce un regressore che, dai dati dell’utente (età, città, livello di istruzione, ora del giorno, importo dell’ordine precedente, giorni dall’ultimo ordine), **predice l’importo in euro del prossimo ordine** entro una settimana:

$$\text{euro\_prossimo\_ordine} = \text{function}(\text{età}, \text{città}, \text{istruzione}, \text{ora}, \text{importo\_precedente}, \text{giorni\_dall’ultimo})$$

Come dimostrare che il regressore è **buono**? Con quali **figure di merito** migliorarlo?

> 📌 **Le metriche dei regressori** (pp. 6–8, formule standard). Con valori veri $y_i$, predetti $\hat{y}_i$, media $\bar{y}$ e $n$ esempi:
> $$R^2 = 1 - \frac{\sum_{i} (y_i - \hat{y}_i)^2}{\sum_{i} (y_i - \bar{y})^2} = 1 - \frac{\text{somma dei quadrati dei residui}}{\text{somma totale dei quadrati}}$$
> $$\text{MSE} = \frac{1}{n} \sum_{i=1}^{n} (y_i - \hat{y}_i)^2 \qquad \text{RMSE} = \sqrt{\text{MSE}} \qquad \text{MAE} = \frac{1}{n} \sum_{i=1}^{n} |y_i - \hat{y}_i|$$

| Metrica | Significato | Meglio se | Intervallo | Unità |
|---|---|---|---|---|
| **$R^2$** | **proporzione della variabilità** dell’uscita **spiegata** dalle variabili predittive: variabilità non spiegata (rispetto all’uscita del modello) su variabilità totale (rispetto alla media) | **più alto** | $(-\infty, 1]$ per dataset particolari o modelli cattivi; di solito $[0, 1]$ | **adimensionale** |
| **MSE** | errore quadratico medio | **più basso** | $[0, +\infty)$ | **quadrato** dell’unità di $y$ (per esempio €²) |
| **RMSE** | **errore medio** del modello nel predire l’uscita di un’osservazione | **più basso** | $[0, +\infty)$ | **stessa unità** di $y$ (per esempio €) |
| **MAE** | media dei valori assoluti degli errori | **più basso** | $[0, +\infty)$ | stessa unità di $y$ |

> ⚠️ **Outlier**. MSE e RMSE sono **sensibili agli outlier**; il MAE lo è **meno**.

> 💡 **Esempio numerico (nota didattica).** Con valori veri $y = (10, 20, 30)$ € e predizioni $\hat{y} = (12, 18, 40)$ €, gli errori sono $(-2, 2, -10)$:
> - $\text{MAE} = (2 + 2 + 10)/3 \approx 4{,}67$ €;
> - $\text{MSE} = (4 + 4 + 100)/3 = 36$ €², $\text{RMSE} = 6$ €;
> - con $\bar{y} = 20$, $R^2 = 1 - 108/200 = 0{,}46$.
>
> L’unico errore grande (10 €) pesa 100 su 108 nel MSE: è per questo che RMSE (6 €) supera il MAE (4,67 €). Un $R^2$ negativo significa che il modello fa **peggio** che predire sempre la media.

#### **1.3. Classificatori: l’accuratezza e i suoi riferimenti**

__

**Esempio**. Per un ospedale si costruisce un classificatore che **diagnostica precocemente il prediabete** dai dati del paziente e dagli esami del sangue (sesso, età, peso, emoglobina glicata, …): $\text{presenza\_diabete} = \text{function}(\text{sesso}, \text{età}, \text{GL}, \dots)$. Come dimostrare che è buono? Una figura di merito tipica è l’**accuratezza**.

> 📌 **Accuratezza**. Il **rapporto** (percentuale) tra risultati predetti che **coincidono** con quelli attesi. Con 1000 risultati e 850 corretti, l’accuratezza è **0,85**, cioè **85%**. Vale $\text{accuratezza} = 1 - \text{errore}$ (a volte si parla di accuratezza intendendo l’errore).

> ⚠️ **Buoni riferimenti per l’accuratezza**. Se training e test sono **sbilanciati** verso una classe, il modello può predire **tutto** come quella classe.
> - **Classificatore casuale**: `out = rand()`. Che accuratezza ha? A volte il 50%.
> - **Classificatore «non così stupido»**: `out = classe_più_presente(dataset)`.
> - Esempio: nei dati di training del **Titanic** il 68% delle persone è morto; un modello che predice «tutti morti» ha accuratezza **68%**.
>
> **Dire «1% di errore» può essere molto buono, o molto cattivo!**

> 💡 **Quanto vale il caso (nota didattica).** Il 50% del classificatore casuale vale per **due classi bilanciate**; con $C$ classi bilanciate l’accuratezza attesa è $1/C$ (33% su Iris). Il classificatore della classe maggioritaria è il **riferimento minimo** da battere: un modello al 70% sul Titanic è appena 2 punti sopra il «tutti morti» Il confronto con la classe maggioritaria costituisce il riferimento minimo da superare.

#### **1.4. La matrice di confusione**

__

> 📌 **Matrice di confusione**. Una misura a **quattro quadranti** per un problema binario (per esempio «cane» contro «non cane»):
>
> | | **Predetto falso** | **Predetto vero** |
> |---|---|---|
> | **Reale falso** | **TN** (veri negativi): correttamente predetti come **non** della classe | **FP** (falsi positivi): predetti come della classe quando **non** lo sono |
> | **Reale vero** | **FN** (falsi negativi): predetti come **non** della classe quando lo sono | **TP** (veri positivi): correttamente predetti come della classe |
>
> Misure che se ne ricavano, con $N$ esempi totali:
> $$\text{Accuratezza} = \frac{TP + TN}{N} \qquad \text{Errore di classificazione} = \frac{FP + FN}{N} \qquad \text{Precisione} = \frac{TP}{TP + FP}$$
> $$\text{Sensibilità (recall, hit rate, TPR)} = \frac{TP}{TP + FN} \qquad \text{Specificità (selettività, TNR)} = \frac{TN}{TN + FP}$$
> **IMPORTANTE**: la matrice di confusione dà **molte più informazioni sugli errori** della sola accuratezza.

> 💡 **Il prediabete in numeri (nota didattica).** Su 1000 pazienti, 100 con prediabete. Un modello trova 60 malati (TP = 60, FN = 40) e dà 30 falsi allarmi (FP = 30, TN = 870):
> - accuratezza $= 930/1000 = 93\%$;
> - sensibilità $= 60/100 = 60\%$: **4 malati su 10 sfuggono**;
> - specificità $= 870/900 \approx 96{,}7\%$;
> - precisione $= 60/90 \approx 66{,}7\%$.
>
> Il 93% sembra ottimo, ma per uno screening medico la sensibilità del 60% è il vero problema. Per confronto, il modello «tutti sani» avrebbe accuratezza 90% e sensibilità 0%.

**Matrice di confusione multiclasse**, sul dataset **CIFAR-10**. Come leggerla: i valori **sulla diagonale** sono le predizioni **corrette**; **fuori dalla diagonale** ci sono gli **errori**. Esempi nel confronto: **26 uccelli** reali sono stati confusi con **aeroplani**; **111 cani** reali sono stati confusi con **gatti**. Servono più esempi?

> 💡 **Che cosa se ne ricava (nota didattica).** Gli errori non sono distribuiti a caso: cani e gatti si confondono tra loro molto più che con i camion. Questo dice **dove** intervenire, per esempio raccogliere più immagini che distinguano cani e gatti o aggiungere feature utili a separarli, mentre l’accuratezza complessiva non lo mostrerebbe.

<!-- SOURCE_VISUAL id="IS16-V01" source="L16-web.pdf" page="12" type="tabella" description="Matrice di confusione a quattro quadranti con TN, FP, FN, TP" reason="Tabella resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V02" source="L16-web.pdf" page="14" type="tabella" description="Matrice di confusione multiclasse su CIFAR-10" reason="Valori della matrice presenti solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V03" source="L16-web.pdf" page="16" type="tabella" description="Lettura della matrice multiclasse: 26 uccelli confusi con aeroplani, 111 cani confusi con gatti" reason="Valori evidenziati presenti solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.5. La curva ROC**

> 📌 **Receiver Operating Characteristic** (ROC, p. 17). **Non un singolo numero**, ma un **grafico** degli errori di un classificatore **binario** al variare della **soglia di discriminazione**. La curva si ottiene tracciando il **tasso di veri positivi** (TPR, probabilità di rilevamento) contro il **tasso di falsi positivi** (FPR) per diversi valori della soglia. Si applica a qualsiasi «rilevatore» generale che confronta un punteggio con una soglia per decidere sì o no.

> 💡 **Come si costruisce (nota didattica).** Il classificatore produce un punteggio $s$ e decide «sì» se $s \ge \theta$. Per ogni soglia $\theta$ si calcolano
> $$\text{TPR}(\theta) = \frac{TP}{TP + FN} \qquad \text{FPR}(\theta) = \frac{FP}{FP + TN} = 1 - \text{specificità}$$
> Abbassando la soglia si trovano più positivi veri ma anche più falsi allarmi: il punto si sposta verso l’alto a destra. Una curva che passa **vicino all’angolo in alto a sinistra** indica un buon classificatore; la **diagonale** corrisponde al caso. L’area sotto la curva (**AUC**) riassume la curva in un numero tra 0,5 (caso) e 1 (perfetto).

<!-- SOURCE_VISUAL id="IS16-V04" source="L16-web.pdf" page="18" type="grafico" description="Curva ROC di un rilevatore con soglia variabile" reason="Curva visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Foundation model: zero-shot e few-shot**

__

#### **2.1. Un nuovo approccio all’addestramento**

**Dalla validazione guidata dai dati all’adattamento guidato dalla rappresentazione**.

| Pipeline | Passi |
|---|---|
| **tradizionale** | raccolta dei dati → feature engineering → addestramento → cross-validation → rilascio |
| **nuova (aggiuntiva)** | analisi dei dati supervisionati e non → **foundation model pre-addestrati** → **ancoraggio semantico** → adattamento **zero/few-shot** → **validazione con l’uomo nel ciclo** |

Il **collo di bottiglia** della validazione si sposta dalla **disponibilità dei dati** alla **qualità della rappresentazione** e all’**ancoraggio semantico**.

> 📌 **Foundation model**. Modelli su **larga scala** pre-addestrati su dataset **enormi e diversificati**, che imparano **rappresentazioni generali** trasferibili tra compiti, adattabili con **prompting**, **fine-tuning** o apprendimento **zero/few-shot**.
>
> Perché sono utili nei sistemi applicativi:
> - forniscono una forte **comprensione semantica** di codice, log e testo;
> - permettono di **ragionare** su minacce o situazioni **mai viste** senza addestramento specifico;
> - possono fornire una base per prototipi industriali zero-shot e few-shot; prima di un impiego operativo servono validazione sul dominio, misure di latenza e controlli di sicurezza.
>
> I foundation model spostano il ML dall’**addestramento specifico** all’**adattamento guidato dalla conoscenza**.

<!-- SOURCE_VISUAL id="IS16-V05" source="L16-web.pdf" page="21" type="diagramma" description="Pipeline tradizionale e nuova pipeline con foundation model e adattamento zero/few-shot" reason="Confronto delle pipeline reso graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. Zero-shot e few-shot**

> 📌 **Che cosa sono e che cosa non sono**. Zero-shot e few-shot **non** sono modelli specifici, **non** sono algoritmi di addestramento, **non** sono architetture. Sono **paradigmi di inferenza e adattamento**: modi di adattare in inferenza un modello pre-addestrato a un compito o a esempi nuovi.
>
> | | **Zero-Shot Learning** (ZSL) | **Few-Shot Learning** (FSL) |
> |---|---|---|
> | Esempi etichettati | **nessuno** | **pochissimi** (per esempio 1–10) |
> | Su che cosa si basa | istruzioni, descrizioni, **conoscenza semantica** | **rappresentazioni forti** apprese nel pre-addestramento |
>
> Zero-shot e few-shot indicano la quantità di esempi forniti al momento dell’inferenza. Nel riconoscimento zero-shot di classi mai viste, la decisione può basarsi su descrizioni semantiche delle classi.

**Esempio zero-shot**. Nessun esempio fornito; decisione basata solo sulla conoscenza semantica e sul riconoscimento di pattern.

- **Prompt**: «Sei un analista di manutenzione industriale. Questo comportamento della macchina è anomalo? *Il motore mostra vibrazioni crescenti dopo l’avvio, la temperatura sale di 12 °C in 20 minuti e l’allarme A17 compare in modo intermittente.*»
- **Output del modello**: «Probabile comportamento anomalo. Il pattern suggerisce un possibile problema meccanico o termico, come usura dei cuscinetti, disallineamento o lubrificazione insufficiente. Si raccomanda un’ispezione.»

**Esempio few-shot**. Il modello **impara il pattern** da pochi esempi etichettati e adatta la decisione, con **maggiore coerenza** sui campioni simili.

- **Prompt**: tre esempi etichettati, poi il caso da analizzare.
  - Esempio 1 (**anomalo**): vibrazioni in aumento dopo l’avvio, temperatura +15 °C, allarme A17 intermittente; motivo: possibile usura dei cuscinetti o problema di lubrificazione.
  - Esempio 2 (**normale**): temperatura stabile, vibrazioni in tolleranza, nessun allarme nel ciclo; motivo: funzionamento normale.
  - Esempio 3 (**anomalo**): assorbimento di corrente in aumento a carico costante, vibrazioni sopra soglia, rumore anomalo; motivo: possibile attrito meccanico o disallineamento.
  - Caso da analizzare: lo stesso dell’esempio zero-shot.
- **Output del modello**: «Probabile caso anomalo. I sintomi sono vicini all’esempio 1: vibrazioni crescenti, deriva di temperatura e allarme A17 intermittente. Possibile causa: usura dei cuscinetti, disallineamento o lubrificazione insufficiente.»

<!-- SOURCE_VISUAL id="IS16-V06" source="L16-web.pdf" page="27" type="esempio" description="Prompt few-shot con tre esempi di manutenzione e risposta del modello" reason="Impaginazione di prompt e risposta resa graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Usare un LLM generico** come ChatGPT o Gemini: passare dall’estrazione delle feature e dalla classificazione a «**parlare con l’LLM**». Questa modalità può produrre ipotesi linguistiche, ma non sostituisce un classificatore validato su soglie, storico e casi specifici.

<!-- SOURCE_VISUAL id="IS16-V07" source="L16-web.pdf" page="32" type="schema" description="Dall’estrazione delle feature e classificazione al dialogo con un LLM generico" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. Valutare i foundation model**

__

**Perché la valutazione non è banale**:

- le predizioni sono spesso **semantiche ed esplicative**, non semplici etichette di classe;
- i modelli zero/few-shot lavorano con **pochi dati** e con **spostamenti della distribuzione**;
- le ipotesi della **cross-validation classica** possono non valere.

**Strategie di valutazione**:

| Strategia | Come |
|---|---|
| **metriche basate sulle etichette** (quando possibile) | accuratezza, precisione, recall, F1, usate quando ci sono etichette vere, anche poche |
| **valutazione per scenari** | test su **famiglie di casi mai viste** o su dati **divisi nel tempo** (addestrare sul passato, valutare sui casi più recenti) |
| **validazione con l’uomo nel ciclo** | gli esperti rivedono predizioni e spiegazioni; si misurano il **tasso di accordo** e i **falsi positivi** |
| **analisi di confidenza e coerenza** | valutare la **confidenza** e la **stabilità** delle predizioni: «questa attività è malevola (alta confidenza)» contro «potrebbe essere malevola (bassa confidenza)» |

Per i foundation model la valutazione si sposta dalla **pura accuratezza statistica** all’**affidabilità operativa in condizioni di incertezza**.

---

### **3. Altre verifiche: input modificati, salienza, rumore, sensibilità**

__

La sezione presenta altri modi di valutare un modello: **mappe di salienza**, **test di iniezione**, **sensibilità**.

**Modificare gli input**. Due reti riconoscono lo stesso cane con confidenza diversa (NN1: 0,95; NN2: 0,89): conviene provare a **modificare l’input** e osservare come cambiano le uscite.

**Controllare anche con le mappe di salienza** (controllare anche che la rete usi le regioni pertinenti dell’immagine). La predizione di NN1 (cane = 0,95) è corretta, ma **guarda i pixel sbagliati**: ci si può fidare anche se sembra accurata?

**Aggiungere una discreta quantità di rumore**. Con un’immagine rumorosa la stessa rete NN1 passa da «cane = 0,95» a «**camion = 0,78**»: è un esempio di **analisi di sensibilità** del modello.

<!-- SOURCE_VISUAL id="IS16-V08" source="L16-web.pdf" page="40" type="immagini" description="Due reti che riconoscono il cane con confidenza 0,95 e 0,89 su input modificati" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V09" source="L16-web.pdf" page="42" type="immagini" description="Rumore aggiunto all’immagine: NN1 passa da cane 0,95 a camion 0,78" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Analisi di sensibilità**. Un insieme di metodi per **quantificare** come l’**incertezza sull’uscita** di un modello dipende dall’incertezza sui suoi **input** o sulla sua **rappresentazione interna**. Valuta quanto il modello è «sensibile» a:
> - **fluttuazioni dei parametri**. Esempio: eseguendo il modello su una **NPU veloce a 8 bit**, i risultati sono **diversi** da quelli di TensorFlow sul PC;
> - **i dati su cui è costruito**. Esempio: rimescolando i dati tra training e validation, **l’accuratezza cambia molto**.

> 💡 **Due esempi, due cause (nota didattica).** Il primo è l’effetto della **quantizzazione** (la quantizzazione riduce la precisione dei pesi): passare da pesi a 32 bit a interi a 8 bit introduce piccoli errori che possono spostare un punto oltre la frontiera di decisione. Il secondo è la **varianza** della variabilità tra campioni di addestramento e verifica: se una nuova divisione dei dati cambia molto l’accuratezza, la stima non è affidabile e il modello è instabile.

---

### **4. Il dataset Iris come strumento di debug**

__

#### **4.1. La regola d’oro e il dataset**

La lezione è **teoria + laboratorio**. A questo punto si è al passo 2, pronti per l’addestramento.

> 📌 **Regola d’oro n. 1: iniziare con un dataset semplice e noto**. Per verificare **conoscenze**, **strumenti** di elaborazione dei dati, **modelli** e **metodi di apprendimento**:
> - **non** iniziare con il dataset reale della propria applicazione;
> - iniziare con un dataset **noto e facile**, con **uscite note**;
> - grafici, accuratezza e risultati sono **ben documentati**, e si possono **confrontare** i propri risultati con la letteratura.
>
> Ogni volta che si prova un nuovo strumento, **partire da qualcosa di noto**.

**Il dataset Iris**:

| Aspetto | Contenuto |
|---|---|
| **compito** | classificare i fiori di iris in **3 classi**: **setosa**, **versicolor**, **virginica** |
| **feature** | **4**: lunghezza e larghezza del **sepalo**, lunghezza e larghezza del **petalo**, in centimetri |
| **campioni** | **150** (50 per classe) |
| **origine** | introdotto dallo statistico e biologo britannico **Ronald Fisher** nell’articolo del **1936** «The use of multiple measurements in taxonomic problems» (*Annals of Eugenics* 7, 179–188), per quantificare la variazione morfologica di tre specie affini di iris |
| **uso storico** | Fisher sviluppò un modello per **distinguere le specie** combinando le quattro feature: ne fece l’esempio classico dell’**analisi discriminante** |
| **scheda** | 6 variabili, 150 osservazioni, uso libero: codice della specie, nome della specie, larghezza e lunghezza del petalo, larghezza e lunghezza del sepalo |

Fisher è definito «un genio che creò quasi da solo le **fondamenta della statistica moderna**». La p. 42 mostra i **dati grezzi** e il significato delle tre classi. Nella scheda della p. 41 il titolo dell’articolo è scritto «Axonomic» per «Taxonomic».

<!-- SOURCE_VISUAL id="IS16-V10" source="L16-web.pdf" page="49" type="immagini" description="Le tre specie di iris: setosa, versicolor, virginica" reason="Foto dei fiori visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V11" source="L16-web.pdf" page="52" type="tabella" description="Dati grezzi di Iris e significato delle tre classi" reason="Tabella resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il file [Lesson_16_iris.dat](Lesson_16_iris.dat) contiene le 150 righe del dataset nel formato UCI: quattro valori numerici separati da virgole e il nome della specie, per esempio `5.1,3.5,1.4,0.2,Iris-setosa`; le righe sono ordinate per classe (50 setosa, 50 versicolor, 50 virginica).

**$Y = \text{FUNC}(X)$, tutto qui**. Per ogni dataset si tratta di creare $X$ (le 4 feature per campione) e $Y$ (l’identificativo della classe, per esempio 1 = «setosa»).

#### **4.2. EDA e visualizzazione su Iris**

__

| Pagina | Grafico | Che cosa insegna |
|---|---|---|
| p. 44 | **boxplot** delle feature di Fisher | distribuzione e outlier di ogni feature |
| p. 45 | **grafici paralleli** (*parallel plots*) | ogni campione è una linea che attraversa gli assi delle feature: **la visualizzazione è la chiave della comprensione** |
| p. 46 | grafico parallelo con **un altro toolbox** | servono **le stesse unità** o **scale simili** sugli assi |
| p. 47 | **armonizzazione** con lo **z-score** | usando **media e deviazione standard** si armonizzano i valori: media 0 e deviazione standard 1 |
| pp. 48–50 | grafico parallelo di un **caso più complesso** (il Titanic) | più difficile capire quale feature aiuta la classificazione; il grafico mostra anche i **rapporti** tra i valori categorici; per semplice osservazione si vedono informazioni utili: **nessun bambino nell’equipaggio**, equipaggio **quasi tutto maschile**, **quasi nessun bambino in prima classe** |
| p. 51 | **grafici di correlazione** | relazioni tra coppie di feature |
| p. 52 | **PCA** su Iris | riduce la dimensionalità conservando il più possibile le **caratteristiche spaziali**, **senza bisogno di etichette**: da $F_1, \dots, F_4$ a $F_1', \dots, F_4'$ |

<!-- SOURCE_VISUAL id="IS16-V12" source="L16-web.pdf" page="54" type="grafico" description="Boxplot delle feature di Iris" reason="Boxplot visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V13" source="L16-web.pdf" page="55" type="grafico" description="Grafico parallelo del dataset Iris" reason="Linee dei campioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V14" source="L16-web.pdf" page="56" type="grafico" description="Grafico parallelo con un altro toolbox: servono unità o scale simili" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V15" source="L16-web.pdf" page="57" type="grafico" description="Grafico parallelo dopo l’armonizzazione con z-score" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V16" source="L16-web.pdf" page="59" type="grafico" description="Grafico parallelo del Titanic con osservazioni su equipaggio, sesso e bambini in prima classe" reason="Flussi del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V17" source="L16-web.pdf" page="61" type="grafico" description="Grafici di correlazione delle feature di Iris" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V18" source="L16-web.pdf" page="62" type="grafico" description="PCA sul dataset Iris" reason="Proiezione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Perché Iris è un buon strumento di debug**. **Nessun dato mancante**, **nessun errore di etichettatura**, **nessuna scalatura necessaria** (tutte le feature sono in centimetri e su scale simili). Per questo il dataset di Fisher è utile **come strumento di debug**. Negli esempi successivi si userà la **notazione di TensorFlow**, la libreria software libera e open source di Google per la programmazione a flusso di dati e differenziabile.

**Il pairplot**, con seaborn:

```python
# Plot the dataset
seaborn.pairplot(dataset, hue="species", size=2, diag_kind="kde")
plt.show()
```

Il grafico mostra tutte le coppie di feature come scatter plot colorati per specie (`hue`), con sulla diagonale la **stima della densità** di ogni feature (`diag_kind="kde"`).

> ⚠️ **Parametro rinominato (nota didattica).** Nelle versioni recenti di seaborn il parametro `size` di `pairplot` si chiama `height`.

#### **4.3. Prepararsi all’apprendimento**

**I passi**:

1. **codifica**: codifica **intera** e codifica **one-hot** (§5);
2. **preparare le feature di input** (§6);
3. **dividere training e test** (§6);
4. **rete neurale** con TensorFlow.

---

### **5. Dati categorici e codifiche**

__

#### **5.1. Il problema**

**Clustering e classificazione con dati categorici**: come applicare le tecniche supervisionate e non supervisionate quando un elemento del vettore di input **non è un valore numerico**? **L’apprendimento può essere influenzato dal modo in cui si elencano le classi**.

**Feature categoriche in IS4**. I dataset industriali **raramente** contengono solo valori numerici: **log** ed **eventi** includono spesso attributi categorici da gestire con attenzione.

#### **5.2. Codifica intera per le feature ordinali**

> 📌 **Codifica intera** (*integer encoding*, pp. 59–60). Una trasformazione che associa ai valori categorici **numeri interi**, **preservando un ordine esplicito** quando le categorie rappresentano una **scala ordinale**.
>
> Le **feature ordinali** hanno un **ordine naturale**: le differenze riflettono un’intensità crescente o decrescente, e i valori interi **conservano le relazioni semantiche**. Esempi:
> - **livello di priorità**: basso → 1, medio → 2, alto → 3;
> - **gravità o criticità**: informativo → 1, avviso → 2, critico → 3;
> - **livello di fiducia o rischio**: fidato < sconosciuto < sospetto.
>
> Qui la codifica intera è valida perché l’**ordine ha significato**, valori più alti rappresentano un **effetto** o un’**importanza** maggiore, e la **distanza** tra i valori ha un significato interpretabile.

#### **5.3. Label encoding e i suoi limiti**

**Label encoding**. Assegna un **intero unico** a ogni valore categorico distinto. Esempio con gli indirizzi IP di destinazione di una rete mesh IoT:

| `dst_ip` | Codice |
|---|---|
| 192.168.1.10 | 0 |
| 10.0.0.5 | 1 |
| 172.16.0.3 | 2 |

Che cosa fa: assegna un **identificativo** a ogni IP; conserva l’**identità**, **non la semantica**; introduce un **ordine numerico artificiale**, che **può essere dannoso** per alcuni metodi di addestramento.

**Quando si può usare**:

| **Scenari accettabili** | **Sconsigliato per** |
|---|---|
| modelli ad **albero** (alberi di decisione, random forest) | **K-Means** |
| **hashing** o indicizzazione | **DBSCAN** |
| come **passo intermedio** prima degli **embedding** | qualsiasi modello **basato su distanze** o **lineare** |

> ⚠️ **«Codifica pigra»: l’esempio degli IP**. Il label encoding è **arbitrario** e **nasconde la natura gerarchica** degli indirizzi IP. «La difficoltà di fondo è che gli IP funzionano come **identificativi categorici**, ma seguono una **logica gerarchica** (rete, sottorete, host) che la codifica standard spesso non cattura.» Il modello deve lavorare di più, con più divisioni, per «riscoprire» che 192.168.1.1 e 192.168.1.2 sono **correlati**. **BISOGNA CODIFICARE CON GIUDIZIO!**

> 💡 **Codificare con giudizio (nota didattica).** Per gli IP si possono estrarre feature che riflettono la gerarchia: i quattro ottetti separati, un indicatore «rete privata / pubblica», il prefisso di sottorete (per esempio 192.168.1.0/24). Così due host della stessa sottorete condividono gran parte delle feature, e la loro vicinanza diventa visibile al modello.

#### **5.4. One-hot e dummy encoding**

> 📌 **One-hot encoding**. **Perché serve una codifica**: i valori categorici **non hanno un ordine naturale**; una mappatura numerica diretta (per esempio tcp = 1, udp = 2) introduce **distanze artificiali**, e i calcoli di distanza diventano **senza senso**.
>
> Ogni feature categorica con $C$ valori possibili viene trasformata in **$C$ dimensioni binarie**, di cui **esattamente una è attiva** (valore 1). Esempio con `protocol` ∈ {tcp, udp, icmp}, da 1 feature a 3:
> $$\text{tcp} = [1, 0, 0] \qquad \text{udp} = [0, 1, 0] \qquad \text{icmp} = [0, 0, 1]$$

> 💡 **Perché le distanze tornano sensate (nota didattica).** Con la codifica intera tcp = 1, udp = 2, icmp = 3, la distanza tcp-icmp (2) è doppia di tcp-udp (1), senza alcun motivo. Con il one-hot la distanza euclidea tra due protocolli diversi è sempre
> $$\lVert [1,0,0] - [0,1,0] \rVert = \sqrt{2}$$
> uguale per ogni coppia: nessuna categoria è «più vicina» di un’altra.

**Dopo il one-hot**:

- si possono calcolare **distanze valide** e stimare la **covarianza** in modo significativo;
- l’**aumento della dimensionalità** è **atteso e voluto**;
- si può applicare la **PCA** dopo la codifica, per **ridurre** la dimensionalità, **eliminare ridondanze** e **visualizzare** i dati.

<!-- SOURCE_VISUAL id="IS16-V19" source="L16-web.pdf" page="75" type="schema" description="One-hot encoding del protocollo da 1 feature a 3 feature binarie" reason="Schema della trasformazione reso graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V20" source="L16-web.pdf" page="76" type="schema" description="One-hot seguito da PCA per ridurre la dimensionalità" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Dummy encoding**. Converte una variabile categorica con $C$ valori in **variabili binarie**: ogni categoria è rappresentata da un **indicatore** (1 se presente, 0 se assente). Per una feature con $C$ categorie produce **$C$ oppure $C - 1$** feature binarie.

> 💡 **Perché $C - 1$ (nota didattica).** Se si conoscono $C - 1$ indicatori, l’ultimo è determinato: vale 1 solo quando tutti gli altri valgono 0. Con $C$ indicatori le colonne sono **linearmente dipendenti** (la loro somma è sempre 1), e nei modelli lineari con intercetta questo rende i coefficienti non univoci. Eliminando una categoria di riferimento il problema scompare; per alberi e reti neurali si usano di solito tutte le $C$ colonne.

<!-- SOURCE_VISUAL id="IS16-V21" source="L16-web.pdf" page="77" type="schema" description="Dummy encoding con C o C-1 variabili binarie" reason="Esempio reso graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Codifica intera, di nuovo**. Ogni valore unico riceve un intero, per esempio «rosso» = 1, «verde» = 2, «blu» = 3: è la **label encoding** o **integer encoding**, facilmente **reversibile**, e per alcune variabili può bastare. Gli interi hanno una **relazione d’ordine naturale**, che gli algoritmi possono essere in grado di sfruttare.

> ⚠️ **Colori e ordine.** L’ordine degli interi è utile solo se la variabile è **ordinale** (§5.2). Per i colori non esiste un ordine naturale, quindi «rosso < verde < blu» è **artificiale**: è esattamente il problema che il one-hot risolve.

La p. 67 ricorda che esistono **molte altre tecniche** per codificare i dati categorici.

<!-- SOURCE_VISUAL id="IS16-V22" source="L16-web.pdf" page="78" type="infografica" description="Altre tecniche di codifica dei dati categorici" reason="Elenco presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Strumenti: one-hot, normalizzazione e divisione dei dati**

__

**One-hot encoding in MATLAB**:

```matlab
labels = ["red"; "blue"; "red"; "green"; "yellow"; "blue"];  % 6 elements
labels = categorical(labels);   % 4 only different labels
categories(labels)
% ans = 4x1 cell {'blue'} {'green'} {'red'} {'yellow'}
labels = onehotencode(labels,2)
% labels = 6x4
%  0  0  1  0
%  1  0  0  0
%  0  0  1  0
%  0  1  0  0
%  0  0  0  1
%  1  0  0  0
```

Le categorie vengono **ordinate alfabeticamente** (blue, green, red, yellow): la prima riga, «red», ha l’1 nella **terza** colonna; la quinta, «yellow», nella quarta.

**Preparare le feature di input**. Per migliorare la discesa del gradiente si normalizzano i valori con `normalize()`; `X_data` conterrà le feature normalizzate per addestrare la rete.

```python
from sklearn.preprocessing import normalize
FEATURES = dataset.columns[0:4]
X_data = dataset[FEATURES].as_matrix()   # for compatibility
X_data = normalize(X_data)
```

> ⚠️ **Due precisazioni sul codice (nota didattica).**
> - `sklearn.preprocessing.normalize` **non** fa una min-max per feature: porta **ogni campione** (ogni riga) a **norma unitaria** L2. Per portare ogni feature in $[0, 1]$ si usa `MinMaxScaler`, per lo z-score `StandardScaler`.
> - `DataFrame.as_matrix()` è stato rimosso da pandas: oggi si scrive `dataset[FEATURES].to_numpy()`.

**Dividere training e test**. I due vettori `X_data` e `y` contengono tutto ciò che serve per addestrare una rete (per esempio con TensorFlow). Si dividono in **training** e **test**, per **prevenire l’overfitting** e ottenere un **benchmark** migliore delle prestazioni:

```python
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X_data, Y, test_size=0.3, random_state=1)
```

Qui il 30% dei campioni va nel test; `random_state=1` rende la divisione **riproducibile**. Per le serie temporali una divisione casuale può mescolare passato e futuro: occorre usare una partizione cronologica.

> 💡 **Il perché dell’avvertenza (nota didattica).** `train_test_split` **mescola** i campioni prima di dividerli (`shuffle=True` per default): con una serie temporale mette il futuro nel training (Lezione 14, §6). Per le serie temporali si usa `shuffle=False` o uno schema a finestre.

<!-- SOURCE_VISUAL id="IS16-V23" source="L16-web.pdf" page="84" type="schema" description="Divisione della matrice X e del vettore Y in training e test" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il passo successivo è l’apprendimento**: con le esercitazioni in MATLAB e Colab si è pronti a creare i **primi classificatori**.

---

### **7. Parte B: il laboratorio**

__

#### **7.1. Organizzazione e metodo**

La p. 75 apre la parte B, intitolata per errore «LESSON 15 Part B». Il laboratorio è in file separati, con un esempio **Colab** e uno **MATLAB**, basati su **Iris**.

> 📌 **Che cosa c’è all’esame**. Non si scrive codice (esatto), ma è importante studiare l’applicazione pratica dei concetti. **Verrà chiesto di commentare esempi di codice.**

| **Colab** | **MATLAB** |
|---|---|
| preparazione dell’ambiente | caricamento del dataset |
| caricamento dei dati | creazione delle strutture dati $X$ e $Y$ |
| analisi preliminare, grafici, normalizzazione, esempio di classificazione | analisi preliminare, grafici, normalizzazione, esempio di classificazione |

**La cosa migliore da fare**: **scaricare gli esempi e farli girare**. Sono commentati: esplorare il codice, modificarlo, aggiungere grafici. Per Colab: caricare il notebook su Google Drive, aprirlo con Colab (Chrome con login), leggere i commenti ed eseguire le celle una per una o con «Run all».

<!-- SOURCE_VISUAL id="IS16-V24" source="L16-web.pdf" page="100" type="schermata" description="Esecuzione delle celle del notebook in Colab" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Il notebook Colab**

__

Le pp. 83–96 ripetono i passi del laboratorio della Lezione 07, §1: import di pandas, NumPy e matplotlib, caricamento del CSV UCI con i nomi delle colonne, `info()` (150 righe, 4 colonne `float64` e la classe `object`), `head(n)`, separazione di `x` (150 × 4) e `y` (150), conversione in NumPy e slicing per colonna e per riga. La p. 88 aggiunge che in `drop` il parametro `axis` indica se eliminare **righe** (0 o `'index'`) o **colonne** (1 o `'columns'`).

Il notebook `Lesson_16_laboratory_COLAB_IRIS_Dataset.ipynb` prosegue così:

**Normalizzazione**: portare i dati nell’intervallo $[0, 1]$ con la min-max, $\dfrac{x - \min(x)}{\max(x) - \min(x)}$; il confronto tra istogrammi mostra dei dati **non normalizzati** e **normalizzati**.

```python
xNumpy_Norm = (xNumpy - np.min(xNumpy)) / (np.max(xNumpy) - np.min(xNumpy))

plt.subplots()
plt.hist(xNumpy[:,0])
plt.subplots()
plt.hist(xNumpy_Norm[:,0])
```

> ⚠️ **Una min-max globale, non per feature (nota didattica).** `np.min(xNumpy)` e `np.max(xNumpy)` sono il minimo e il massimo **dell’intera matrice** (0,1 e 7,9 su Iris), non di ogni colonna. Infatti l’output salvato mostra la prima feature tra **0,538 e 1**, non tra 0 e 1: $(4{,}3 - 0{,}1)/7{,}8 \approx 0{,}538$. Per normalizzare **ogni feature** si scrive `np.min(xNumpy, axis=0)` e `np.max(xNumpy, axis=0)`. Nel notebook la matrice normalizzata serve solo per i grafici: il classificatore è addestrato su `xNumpy`.

**Conversione della classe da categorica a numerica**:

```python
yNumpy, dummy = pd.factorize(yNumpy)
print(yNumpy)   # 50 zeri, 50 uno, 50 due
```

`pd.factorize` assegna un intero a ogni valore distinto nell’ordine in cui compare: setosa → 0, versicolor → 1, virginica → 2. È una **label encoding** (§5.3), accettabile qui perché serve solo come **etichetta di classe**, non come feature di input.

**Visualizzazione**. Visualizzare feature e classi dà un **primo controllo visivo** della capacità discriminante. Il limite: si vedono solo **2 o 3 dimensioni**, e anche un dataset semplice come Iris ha 4 feature. Si procede così: (1) scegliere le feature da disegnare; (2) assegnare il **colore** in base alla classe.

```python
feat0 = xNumpy_Norm[:,0]
feat1 = xNumpy_Norm[:,1]
fig1, ax1 = plt.subplots()
scatter1 = ax1.scatter(feat0, feat1, s=np.pi*3, c=yNumpy)
plt.title('Feat0 vs Feat1')
legend1 = ax1.legend(*scatter1.legend_elements(), loc="upper left", title="Classes")
ax1.add_artist(legend1)
plt.show()
# lo stesso con feat2 = xNumpy_Norm[:,2] e feat3 = xNumpy_Norm[:,3]
```

| Coppia di feature | Risultato |
|---|---|
| **Feat0 + Feat1** (lunghezza e larghezza del sepalo, p. 104) | **buona** discriminazione per la **classe 0**; **cattiva** per le classi 1 e 2, che sono **mescolate** |
| **Feat2 + Feat3** (lunghezza e larghezza del petalo, p. 105) | **discriminazione migliore** per **tutte** le classi |

<!-- SOURCE_VISUAL id="IS16-V25" source="L16-web.pdf" page="111" type="grafico" description="Istogramma della prima feature dopo la normalizzazione" reason="Istogramma visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V26" source="L16-web.pdf" page="115" type="grafico" description="Scatter Feat0 contro Feat1: classe 0 separata, classi 1 e 2 mescolate" reason="Punti colorati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS16-V27" source="L16-web.pdf" page="116" type="grafico" description="Scatter Feat2 contro Feat3: discriminazione migliore per tutte le classi" reason="Punti colorati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Classificazione con k-NN** (nel notebook, dopo il materiale). Divisione a metà, classificatore **1-NN** con distanza euclidea, matrice di confusione e accuratezza:

```python
from sklearn.model_selection import train_test_split
x_train, x_test, y_train, y_test = train_test_split(xNumpy, yNumpy, test_size=0.5)
# xTrain (75, 4), xTest (75, 4), yTrain (75,), yTest (75,)

from sklearn.neighbors import KNeighborsClassifier
classifier = KNeighborsClassifier(n_neighbors=1, metric='euclidean')
classifier.fit(x_train, y_train)
output_test = classifier.predict(x_test)

from sklearn.metrics import confusion_matrix
CM = confusion_matrix(y_test, output_test)
print(CM)

accuracy = CM.trace() / CM.sum()
print("Accuracy: {0}".format(accuracy*100))
```

Output salvato:

$$CM = \begin{bmatrix} 27 & 0 & 0 \\ 0 & 21 & 2 \\ 0 & 0 & 25 \end{bmatrix} \qquad \text{accuratezza} = \frac{27 + 21 + 25}{75} = \frac{73}{75} \approx 97{,}33\%$$

> 💡 **Leggere il risultato (nota didattica).** In scikit-learn le **righe** sono le classi **vere** e le **colonne** quelle **predette**: 2 versicolor (classe 1) sono state classificate come virginica (classe 2), mentre setosa è riconosciuta perfettamente, coerente con i grafici delle pp. 104–105. La divisione **non ha `random_state`**: rieseguendo il notebook si ottengono numeri leggermente diversi, ed è proprio la variabilità che la cross-validation della Lezione 14 serve a misurare.

#### **7.3. Lo script MATLAB**

Lo script `Lesson_16_laboratory_MATLAB_IRIS_Dataset.m` segue gli stessi passi:

| Blocco | Istruzioni principali | Che cosa fa |
|---|---|---|
| **scaricare e leggere** | `websave('iris.dat', url)`, `textscan(fid,'%f%f%f%f%s','delimiter',',')` | salva il file UCI e legge quattro colonne numeriche e una di testo; il commento ricorda che equivale a `load fisheriris` |
| **matrice delle feature** | `P = measurements;` `plotmatrix(P)` | la matrice $X$ delle feature e il relativo scatter plot a coppie |
| **target** | ciclo con `switch species{i}` | il vettore target $Y$: setosa → 0, versicolor → 1, virginica → 2 |
| **statistiche descrittive** | `mean`, `std`, `min`, `max` per colonna, stampate con `fprintf` | primo controllo delle feature |
| **grafici** | `gscatter(P(:,i), P(:,j), species, 'rgb', 'osd')` in una griglia 4 × 4 | tutte le coppie di feature colorate per specie, con le etichette in centimetri |
| **classificatore** | `lda = fitcdiscr(P,T);` `ldaClass = resubPredict(lda);` | **analisi discriminante lineare**, lo stesso metodo di Fisher |
| **figure di merito** | `errorVector = ldaClass ~= T;` somma, media e deviazione standard dell’errore | numero e percentuale di errori |
| **matrice di confusione** | `confusionMatrix = confusionmat(ldaClass,T)` | tabella degli errori per classe |

```matlab
%% VERY EASY TO CREATE A CLASSIFIER NOW
% Linear Discriminant Analysis
lda = fitcdiscr(P,T);
ldaClass = resubPredict(lda);

% Classification error
errorVector = ldaClass ~= T;
totalErrorPerc = mean(errorVector)*100;
fprintf('Total classification error = %03.03f %% \n', totalErrorPerc);

%Confusion Matrix
confusionMatrix = confusionmat(ldaClass,T)
```

> ⚠️ **Due osservazioni sullo script (nota didattica).**
> - `resubPredict` predice **gli stessi dati usati per addestrare** (resubstitution): l’errore ottenuto è una stima con **bias ottimistico** (Lezione 14, §3.1), non un errore di generalizzazione. Per una stima corretta serve una divisione o una cross-validation, per esempio con `crossval` o `cvpartition`.
> - `confusionmat(group, grouphat)` si aspetta prima le etichette **vere** e poi quelle **predette**. Lo script le passa in ordine inverso (`ldaClass`, `T`), quindi nella matrice ottenuta le **righe** sono le classi **predette** e le colonne quelle vere: va letta di conseguenza.

> 💡 **Due linguaggi, due classificatori.** Il notebook usa un **1-NN** su una divisione a metà, lo script una **LDA** su tutti i dati. Il confronto è istruttivo: l’errore dello script è misurato sui dati di addestramento, quello del notebook su dati mai visti. Per confrontare davvero i due metodi bisognerebbe valutarli con lo **stesso** schema di validazione.

---

### **8. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **$R^2$** / **RMSE** | quota di variabilità spiegata, adimensionale, meglio se alto / errore medio nell’unità di $y$, meglio se basso |
| **MSE** / **MAE** | quadrato dell’unità, molto sensibile agli outlier / stessa unità, meno sensibile |
| **Accuratezza** / **matrice di confusione** | un numero / dove e come sbaglia il modello |
| **Classificatore casuale** / **classe maggioritaria** | $1/C$ con classi bilanciate / riferimento minimo con classi sbilanciate |
| **Sensibilità** / **specificità** | quota di positivi trovati / quota di negativi riconosciuti |
| **Precisione** / **sensibilità** | quanti allarmi sono veri / quanti casi veri sono trovati |
| **ROC** / **singola accuratezza** | curva TPR-FPR su tutte le soglie / valore a una sola soglia |
| **Zero-shot** / **few-shot** | nessun esempio, solo istruzioni / pochi esempi etichettati nel prompt |
| **Foundation model** / **modello specifico** | adattamento guidato dalla conoscenza / addestramento su un compito |
| **Iniezione di rumore** / **analisi di sensibilità** | un test specifico / la famiglia di metodi su input, parametri e dati |
| **Codifica intera** / **label encoding** | ordine con significato (feature ordinali) / identificativo arbitrario |
| **Label encoding** / **one-hot** | ordine artificiale, adatto agli alberi / nessun ordine, distanze uguali, adatto a modelli basati su distanze |
| **One-hot** / **dummy** | $C$ colonne binarie / $C$ o $C - 1$ colonne, per evitare dipendenze lineari |
| **`normalize`** / **`MinMaxScaler`** | ogni campione a norma unitaria / ogni feature in $[0, 1]$ |
| **Min-max globale** / **per feature** | stesso minimo e massimo per tutta la matrice / minimo e massimo di ogni colonna |
| **`resubPredict`** / **validazione su dati nuovi** | errore sul training, ottimistico / stima della generalizzazione |

---

### **9. Sintesi della lezione**

__

> ✅ **Punti principali**.
> - Il dataset di **Fisher Iris** come «**strumento di debug**»: nessun dato mancante, nessun errore di etichettatura, risultati noti in letteratura.
> - **One-hot encoding**, e più in generale la codifica dei dati categorici: intera per le feature ordinali, one-hot o dummy per quelle nominali, con giudizio per casi come gli indirizzi IP.
> - **Creare le matrici** $X$ e $Y$ per l’apprendimento.
> - **Analisi statistica** dei dati, necessaria **prima** di iniziare l’elaborazione.
> - **Grafici utili** per il ML: boxplot, grafici paralleli, pairplot, scatter plot per coppie di feature; la **visualizzazione dei dati** è molto utile.
> - **Valutazione dell’accuratezza**: $R^2$, MSE, RMSE e MAE per i regressori; accuratezza con i suoi riferimenti, matrice di confusione e ROC per i classificatori; valutazione operativa per i foundation model e analisi di sensibilità.

> ⚠️ **Nota redazionale.** La pagina conclusiva della parte B riporta accidentalmente i punti principali della **Lezione 10** (similarità per il deep learning, EDA, feature engineering, EDA applicata alle reti), non quelli di questo laboratorio. La sintesi qui sopra segue invece la p. 74 e i contenuti effettivi della lezione.
