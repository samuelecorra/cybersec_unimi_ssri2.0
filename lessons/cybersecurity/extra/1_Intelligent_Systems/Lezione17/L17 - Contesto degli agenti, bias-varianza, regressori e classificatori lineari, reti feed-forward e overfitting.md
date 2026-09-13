## **Lezione 17: Contesto degli agenti, bias-varianza, regressori e classificatori lineari, reti feed-forward e overfitting**

La diciassettesima lezione del corso **IS4** unisce tre filoni. Il primo riguarda gli **agenti**: in quali fasi del flusso di lavoro del ML aiutano e che cosa resta da validare a una persona, e soprattutto come si gestisce il **contesto**, che si ricostruisce a ogni turno, cresce e «marcisce» se non viene curato. Il secondo è il **dilemma bias-varianza**, mostrato sperimentalmente con alberi di decisione sempre più profondi sul dataset Wine e con la cross-validation. Il terzo è il primo **modello completo** del corso: il **regressore lineare**, con la sua teoria (minimi quadrati, gradiente, $R^2$) e il laboratorio in MATLAB e Python, fino alle prime **reti feed-forward** e a un overfitting costruito apposta con 100 neuroni e 51 punti.

Fonti: [PDF della Lezione 17](Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf), 112 pagine, e tre supplementi letti integralmente, con il prefisso «Lesson_16» nel nome: lo script [Lesson_16_decision_tree_wine_confusionmatrix_crossvalidation.py](Lesson_16_decision_tree_wine_confusionmatrix_crossvalidation.py), il notebook [Lesson_16_laboratory_COLAB_linear_regressors.ipynb](Lesson_16_laboratory_COLAB_linear_regressors.ipynb) e lo script [Lesson_16_laboratory_MATLAB_linear_and_neural.m](Lesson_16_laboratory_MATLAB_linear_and_neural.m). Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *Using new models in your practice, Linear regressors and classifiers (with coding), Feed Forward Neural and Overfitting!* La copertina riporta «LESSON 16», ma il PDF è quello della Lezione 17. L’indice (p. 2) elenca:

1. **memoria e gestione del contesto** degli agenti;
2. caricamento, **EDA** e grafici in MATLAB;
3. **nuovo modello?** Che cosa sapere, che cosa fare;
4. il **dilemma bias-varianza**;
5. creazione e uso, con codice, dei primi **modelli di ML completi**: regressori lineari, classificatori lineari, prime reti neurali;
6. **overfitting!**;
7. punti principali.

---

### **1. Agenti nel flusso di lavoro e gestione del contesto**

_(slide pp. 3–23)_

#### **1.1. Dove aiutano gli agenti e che cosa validare**

**Agenti per il ML: dove possono essere utili e che cosa possono fare** (p. 3).

| Fase del flusso | Che cosa può fare l’agente | Che cosa deve validare una persona |
|---|---|---|
| **definizione del problema** | ricostruire il **contesto**, estrarre i **vincoli** | **definizione del compito**, **variabile target** |
| **dati** | ispezionare i file, individuare **valori mancanti**, proporre la **pulizia** | **leakage**, **unità di misura**, **etichette** |
| **rappresentazione** | suggerire **feature**, codificare le variabili **categoriche** | **significato** delle feature, rischio di **causalità** spuria |
| **modello** | proporre **baseline** e modelli candidati | **scelta del modello** e sue **ipotesi** |
| **valutazione** | calcolare le **metriche**, confrontare i modelli | **validità della divisione** di test, **significato statistico** |
| **reportistica** | generare **tabelle, grafici, spiegazioni** | **affermazioni** e **conclusioni** |
| **monitoraggio** | preparare bozze di **controlli del drift** e **dashboard** | **rischio del rilascio** e **soglie** |

> 💡 **Lo schema dietro la tabella (nota didattica).** La colonna di destra raccoglie proprio gli errori più costosi del corso: data leakage (Lezione 07), unità non armonizzate (Lezione 06), correlazione scambiata per causalità (Lezione 05), stime ottimistiche per divisioni sbagliate (Lezione 14). L’agente accelera il lavoro meccanico, ma **le decisioni che invalidano un progetto restano umane**.

#### **1.2. Il contesto e la sua dimensione**

**Memoria degli agenti e gestione del contesto** (p. 4): controllare, gestire, comprimere, ricominciare.

> 📌 **Contesto** (p. 5). Negli LLM e negli agenti il contesto è lo **stato attivo di informazioni** passato al modello **al momento dell’inferenza**: istruzioni dell’utente, storia della conversazione, documenti recuperati, risultati degli strumenti, regole di sistema, **riassunti della memoria**, **definizioni degli strumenti** disponibili (Lezione 15, §3.1).

**La dimensione del contesto sta crescendo** (pp. 6–7): c’è più spazio per dire qualcosa di significativo. Secondo la slide, a metà 2026 una finestra da **1 milione di token** è lo **standard dello stato dell’arte** per i grandi modelli cloud di fascia alta, e permette di elaborare interi **codebase** o **centinaia di documenti** in un solo prompt:

- 1 milione di token corrispondono a circa **750 000 parole**, oltre **2000 pagine** di testo;
- i test «**ago nel pagliaio**» (*needle in a haystack*) mostrano **alta accuratezza**;
- alcuni utenti segnalano però **latenza maggiore** e possibile **degrado delle prestazioni** vicino al limite di 1 milione.

> ⚠️ **Finestra più grande ≠ comprensione migliore** (p. 8). Il confronto tra modelli locali e cloud mostra che una finestra più ampia non garantisce di usare bene tutto ciò che contiene: trovare un’informazione isolata (l’ago) è più facile che **ragionare** su molte informazioni sparse (Lezione 15, §3.2, «lost in the middle»).

<!-- SOURCE_VISUAL id="IS17-V01" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="6" type="grafico" description="Crescita della dimensione della finestra di contesto dei modelli" reason="Valori del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V02" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="8" type="grafico" description="Dimensione del contesto di modelli locali e cloud: finestra più grande non significa comprensione migliore" reason="Confronto visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Il contesto si ricostruisce a ogni turno** (pp. 9–10). Una chiamata a un LLM è **senza stato** (*stateless*): dopo aver generato la risposta, il contesto attivo interno viene **scartato**. Ciò che persiste **non sta dentro l’LLM**, ma nell’**applicazione** che lo circonda: ChatGPT, Claude Code, Codex, Cursor o un framework di agenti personalizzato.

<!-- SOURCE_VISUAL id="IS17-V03" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="10" type="diagramma" description="Ricostruzione del contesto a ogni turno di chat da parte dell’applicazione" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Il contesto cresce**

_(slide pp. 11–13)_

Nelle chat e con gli agenti il contesto **cresce** (p. 11).

**Esempio** (p. 12). Prompt di sistema di **200 token**, ogni messaggio dell’utente circa **50 token**, ogni risposta circa **300 token**. Poiché a ogni turno si rimanda tutta la conversazione:

| Turno | Token inviati all’LLM | Risposta | Dimensione del log dopo il turno |
|---|---|---|---|
| 1 | $200 + 50 = 250$ | 300 | 350 |
| 2 | $200 + 50 + 300 + 50 = 600$ | 300 | 700 |
| 3 | $200 + 50 + 300 + 50 + 300 + 50 = 950$ | 300 | — |
| $N$ | $\approx 200 + 50N + 300(N-1) \approx 350N$ | 300 | — |

> 💡 **Il costo cresce col quadrato (nota didattica).** Il contesto del turno $k$ vale esattamente $350k - 100$ token. Sommando su tutti i turni, i token **inviati in totale** sono
> $$\sum_{k=1}^{N} (350k - 100) = 175\,N(N+1) - 100\,N$$
> che cresce come $N^2$: 10 turni costano circa 18 250 token in ingresso, 100 turni circa 1,76 milioni. Ecco perché, nell’economia dei token (Lezione 13, §3.3), una conversazione lunga diventa rapidamente costosa e lenta.

**A ogni nuovo turno** (p. 13) l’applicazione costruisce un nuovo contesto attivo **selezionando** informazioni da fonti esterne diverse: il messaggio corrente, la **storia recente**, le istruzioni di sistema e dello sviluppatore, i **riassunti** rilevanti, le **memorie** salvate, i **file del progetto**, i documenti recuperati, i risultati degli strumenti, porzioni scelte di log o di codice.

#### **1.4. Il context rot e le tecniche di gestione**

_(slide pp. 14–21)_

> 📌 **Context rot** (p. 15). **Non usare mai tutto il contesto dall’inizio.** La finestra di contesto **non** va trattata solo come un limite rigido di memoria: man mano che la conversazione cresce, la **qualità delle risposte può peggiorare prima** che il contesto sia tecnicamente pieno. Le informazioni rilevanti diventano più difficili da **prioritizzare**, le istruzioni **competono** con contenuti più vecchi, e il modello può iniziare a **ignorare o usare male** le evidenze. I sistemi pratici hanno quindi bisogno di **strategie di gestione** (recupero, riassunto, rotazione, compattazione automatica) **prima** che la qualità crolli.

**Context engineering** (p. 14): un insieme di meccanismi per **prevenire il context rot** gestendo **che cosa il modello può vedere**.

| Tecnica | Come funziona | Attenzione a |
|---|---|---|
| **1. Offloading** (p. 16) | conservare informazioni grandi o persistenti **fuori dal prompt attivo** (file, archivi di memoria, scratchpad, database) e far recuperare all’agente **solo ciò che serve** | — |
| **2. RAG** (p. 17) | **recuperare** la conoscenza esterna al momento dell’inferenza invece di mettere tutti i documenti nel prompt; utile per ancoraggio, scalabilità e riduzione del sovraccarico | qualità del recupero (Lezione 15, §4) |
| **3. Selezione dinamica degli strumenti** (*tool loadout*, p. 18) | esporre **solo gli strumenti rilevanti** per il compito corrente | troppe definizioni di strumenti **confondono** il modello, aumentano i token e causano **chiamate sbagliate** |
| **4. Pruning** (p. 19) | **rimuovere** il contesto irrilevante, obsoleto, duplicato o di poco valore prima della chiamata successiva | — |
| **5. Riassunto** (p. 20) | **comprimere** la conversazione o i risultati degli strumenti più vecchi in un **riassunto di lavoro** compatto | **rischioso** se il riassunto perde **decisioni, vincoli, numeri o evidenze** |
| **6. Quarantena** (p. 21) | **isolare** i sottocompiti complessi in **thread** o **sotto-agenti** separati, così il loro ragionamento intermedio rumoroso, i risultati degli strumenti e i fallimenti non **inquinano** il contesto principale | — |

> 💡 **Le stesse idee del corso (nota didattica).** Il pruning è una **selezione delle feature** applicata al contesto, il riassunto è una **riduzione della dimensionalità** con perdita di informazione, la quarantena è il **divide et impera**. E come con i dati (GIGO), un contesto pieno di materiale obsoleto produce risposte peggiori anche con un modello eccellente.

#### **1.5. Igiene operativa del contesto**

_(slide pp. 22–23)_

**Gestire il contesto durante un flusso agentico** (p. 22):

- **monitorare la crescita** del contesto: chat lunghe, file grandi, output ripetuti e risultati degli strumenti consumano la finestra;
- **ridurre** il contesto eliminando file irrilevanti, **riassumendo il lavoro completato** e tenendo solo le **decisioni correnti**;
- usare **RAG** o una **memoria di progetto** per la conoscenza stabile, invece di incollare ripetutamente lo stesso materiale;
- **compattare o ricominciare** quando la conversazione contiene **ipotesi superate**, rami abbandonati o troppi sottocompiti conclusi.

> ⚠️ **Segnale di rischio** (p. 22). L’agente inizia a **dimenticare i vincoli**, a **mescolare decisioni vecchie** o a **leggere le evidenze sbagliate**.

**Pratiche operative** (p. 23):

- chiedere un **riassunto di passaggio** (*handoff*): obiettivi, decisioni, file toccati, questioni aperte, test, prossimi passi;
- **aprire una nuova conversazione** a partire da quel riassunto quando la precedente diventa troppo lunga o dispersiva;
- in **Claude Code**, usare **`/compact`** quando il contesto diventa grande e **`/clear`** quando si cambia compito;
- dare istruzioni di compattazione **mirate**, per esempio: conservare modifiche al codice, risultati dei test, decisioni di progetto e rischi irrisolti;
- usare **sotto-agenti** per sottocompiti delimitati, quando finestre di contesto separate aiutano a preservare la conversazione principale.

---

### **2. Errori di classificazione: l’albero Wine in Python**

_(slide pp. 24–37)_

#### **2.1. Albero poco profondo e albero «perfetto»**

Si riprende l’**albero di decisione sul dataset Wine** della Lezione 12, §3, per misurarne gli errori (pp. 24–25). Lo script [Lesson_16_decision_tree_wine_confusionmatrix_crossvalidation.py](Lesson_16_decision_tree_wine_confusionmatrix_crossvalidation.py) usa i nomi reali dei tre cultivar:

```python
from sklearn.datasets import load_wine
from sklearn.tree import DecisionTreeClassifier
from sklearn import tree
from sklearn.metrics import confusion_matrix, ConfusionMatrixDisplay
from sklearn.model_selection import cross_val_score

wine = load_wine()
X, y = wine.data, wine.target
wine_cultivars = ['Barolo', 'Grignolino', 'Barbera']

# Albero poco profondo
clf_shallow = DecisionTreeClassifier(max_depth=3)
clf_shallow.fit(X, y)
y_pred_shallow = clf_shallow.predict(X)
cm_shallow = confusion_matrix(y, y_pred_shallow)
ConfusionMatrixDisplay(confusion_matrix=cm_shallow, display_labels=wine_cultivars).plot(cmap='Blues', values_format='d')

# Albero più profondo
clf_deep = DecisionTreeClassifier(max_depth=5)
clf_deep.fit(X, y)
cm_deep = confusion_matrix(y, clf_deep.predict(X))
```

Lo script classifica anche un campione esistente (`X[0]`) e un **nuovo vino plausibile**, costruito con valori coerenti con gli intervalli del dataset (alcol 13,5; acido malico 2,0; …; prolina 1000).

| Passo (slide) | Che cosa si osserva |
|---|---|
| **albero poco profondo** (pp. 26–30) | **non è un learner perfetto**: la matrice di confusione mostra dove sbaglia. Esempio: l’albero ha risposto «**Grignolino**» ma il cultivar vero era «**Barolo**»: «che errore imperdonabile!» |
| **aggiungere livelli** (pp. 31–33) | con più profondità si ottiene un **learner perfetto**: nessun errore sui dati di addestramento |

<!-- SOURCE_VISUAL id="IS17-V04" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="26" type="diagramma" description="Albero poco profondo sul dataset Wine, non perfetto" reason="Nodi e soglie visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V05" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="28" type="tabella" description="Matrice di confusione dell’albero poco profondo con gli errori evidenziati" reason="Valori della matrice presenti solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V06" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="29" type="tabella" description="Errore dell’albero: Grignolino predetto al posto di Barolo" reason="Cella evidenziata visibile solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V07" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="32" type="tabella" description="Matrice di confusione del learner perfetto dopo l’aggiunta di livelli" reason="Valori della matrice presenti solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Le matrici dello script sono sui dati di addestramento.** Entrambe le matrici di confusione usano `predict(X)` sugli stessi dati di `fit(X, y)`: misurano quanto l’albero ha **memorizzato**, non quanto **generalizza** (Lezione 14, §3.1).

#### **2.2. È una buona idea creare un learner perfetto?**

> 📌 **No** (p. 34).
> - **Errore di training nullo ≠ errore di generalizzazione nullo.**
> - **Memorizzazione e cattura del rumore** (overfitting): i modelli ad alta capacità memorizzano **regolarità spurie** e **rumore di misura**, rendendo le predizioni **fragili** quando la distribuzione cambia (nuove annate, deriva dei sensori, …).
> - **L’overfitting va sospettato ogni volta che l’errore di training è zero.**
>
> **Rischi pratici**:
> - **predizioni instabili**: piccole perturbazioni delle feature portano a foglie e percorsi diversi;
> - **manutenzione difficile**: il modello va riaddestrato spesso perché il drift rompe le regole memorizzate;
> - **stakeholder ingannati**: una metrica apparente del 100% **nasconde l’errore reale**.

**Verificare la generalizzazione con la cross-validation** (pp. 35–37). Con una **10-fold CV** l’albero **non è così perfetto**:

```python
train_accuracy = clf_deep.score(X, y)
cv_scores = cross_val_score(clf_deep, X, y, cv=10)
cv_mean = cv_scores.mean()

plt.bar(range(1, 11), cv_scores)
plt.axhline(cv_mean, linestyle='--')

print("Training accuracy:", round(train_accuracy, 3))
print("Cross-validation accuracies:", np.round(cv_scores, 3))
print("Mean CV accuracy:", round(cv_mean, 3))
print("Variance of CV accuracies:", round(np.var(cv_scores), 3))
```

> 💡 **Che cosa fa `cross_val_score` (nota didattica).** Non usa l’albero già addestrato: per ciascuno dei 10 fold ne addestra una **copia nuova** sui 9 fold restanti e la valuta sul fold escluso. L’accuratezza di training vicina a 1 e la media CV più bassa sono le due curve del §3: la distanza tra le due è la misura dell’overfitting.

<!-- SOURCE_VISUAL id="IS17-V08" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="37" type="grafico" description="Accuratezze della 10-fold cross-validation con la media" reason="Valori delle barre visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Il dilemma bias-varianza**

_(slide pp. 38–46)_

«**Una cosa da sapere per tutta la vita da esperti di AI**» (p. 38).

**Illustrazione empirica sul dataset Wine** (pp. 39–41, $n = 178$). Si calcolano non solo l’**errore medio** ma anche la **varianza degli errori** nei 10 test della cross-validation.

> 📌 **Esperimento: alberi da profondità 1 a 10, 10-fold CV** (p. 40).
> - L’**accuratezza di training cresce** in modo monotono.
> - L’**accuratezza di validazione** raggiunge un **picco** e poi si **appiattisce o cala**: la classica forma a **U** dell’errore.
> - Dalla **profondità 5** in su l’albero **memorizza** (bias circa zero) ma **non guadagna** generalizzazione.

<!-- SOURCE_VISUAL id="IS17-V09" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="39" type="grafico" description="Media e varianza dell’errore di cross-validation sul dataset Wine" reason="Valori del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V10" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="41" type="grafico" description="Illustrazione bias-varianza su Wine: accuratezza di training e di validazione al variare della profondità" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le pp. 42–43 mostrano il dilemma per un **classificatore** e per un **regressore**.

<!-- SOURCE_VISUAL id="IS17-V11" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="42" type="grafico" description="Il dilemma bias-varianza per un classificatore" reason="Frontiere di decisione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V12" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="43" type="grafico" description="Il dilemma bias-varianza per un regressore" reason="Curve di regressione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **La teoria: scomposizione dell’errore di predizione** (p. 44). L’errore atteso si scompone in **bias al quadrato**, **varianza** e **rumore** (formula nella Lezione 14, §5.5), dove $\mathbb{E}[\cdot]$ è il **valore atteso**: la media che si otterrebbe **ripetendo l’esperimento molte volte**.
> - **Bias alto → underfitting**: il modello è **troppo semplice**, e la sua predizione media resta **lontana** dalla funzione vera.
> - **Varianza alta → overfitting**: il modello è **troppo complesso**, e le sue predizioni **oscillano molto** tra un dataset e l’altro.
> - Il **punto ottimale** bilancia i due termini per **minimizzare l’errore atteso**.

**Media e deviazione standard della k-FCV** (p. 45, **non d’esame**). È più intuitivo monitorare l’**errore medio** e la **deviazione standard** nella k-FCV: la k-FCV è una **simulazione Monte Carlo** dell’errore di predizione, in cui $D$ indica le diverse **partizioni del dataset**.

> 💡 **Il collegamento con la formula (nota didattica).** Nella scomposizione, $\mathbb{E}$ è una media su **molti training set** possibili, che nella realtà non si hanno. La k-FCV li simula: ogni fold dà un modello addestrato su dati leggermente diversi. La **media** degli errori stima l’errore atteso; la **deviazione standard** dà un’idea di quanto il modello dipende dai dati, cioè della **varianza**.

> 📌 **Visualizzare il compromesso** (p. 46). Sull’asse orizzontale **non c’è il tempo né le epoche: c’è la complessità** del modello.

<!-- SOURCE_VISUAL id="IS17-V13" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="46" type="grafico" description="Compromesso bias-varianza in funzione della complessità del modello" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. MATLAB: EDA e analisi discriminante su Iris**

_(slide pp. 47–65)_

#### **4.1. Caricamento e strutture dati**

**Caricare il dataset** (pp. 48–49), come nello script della Lezione 16, §7.3:

```matlab
url = 'https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data';
filename = 'iris.dat';
outfilename = websave(filename,url);
fid = fopen(outfilename, 'r');
S = textscan(fid,'%f%f%f%f%s','delimiter',',');
fclose(fid);
meas = [S{1}, S{2}, S{3}, S{4}];   % meas = measurements
species = S{5};
% Equivalent to ">>load fisheriris"
```

**Creare le strutture dati** (p. 50): la matrice del **problema** `P` e il vettore dei **target** `T`.

```matlab
P = meas;                          % Problem (X nel corso)
T = zeros(size(species));          % Targets (Y nel corso)
for i = 1 : size(T,1)
    switch species{i}
        case 'setosa'
            T(i) = 0;
        case 'versicolor'
            T(i) = 1;
        case 'virginica'
            T(i) = 2;
    end
end
figure; bar(T); xlabel('Sample'); ylabel('Class')
```

> ⚠️ **Nomi delle specie diversi (nota didattica).** Il file UCI scaricato con `websave` scrive le specie come `'Iris-setosa'`, `'Iris-versicolor'`, `'Iris-virginica'`; i nomi `'setosa'`, `'versicolor'`, `'virginica'` sono quelli di `load fisheriris`. Con i dati scaricati, i `case` della slide **non corrispondono mai** e `T` resta tutto a zero, senza alcun messaggio d’errore. Lo script della Lezione 16 usa infatti `'Iris-setosa'` e simili. Un `bar(T)` dopo il ciclo, come fa la slide, è proprio il controllo che rivela il problema.

#### **4.2. Grafici: supervisionato e non supervisionato**

**`plotmatrix`: feature $i$ contro feature $j$** (p. 51):

```matlab
figure
plotmatrix(P)
title('scatter plot of the features')
```

> 📌 **`plotmatrix` contro `pairplot`** (pp. 52–53). `plotmatrix` disegna le coppie di feature **senza etichette**: è un’analisi **non supervisionata**. Il `pairplot` di seaborn colorato per specie (Lezione 16, §4.2) usa le **etichette**: è un’analisi **supervisionata**.

**Cluster nell’analisi non supervisionata** (p. 54). Nel sottospazio **F3–F4** (lunghezza e larghezza del petalo) si vede un **cluster di Iris setosa ben separato**, anche senza conoscere le etichette.

<!-- SOURCE_VISUAL id="IS17-V14" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="52" type="grafico" description="plotmatrix senza etichette contro pairplot colorato per classe" reason="Grafici a confronto visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V15" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="54" type="grafico" description="Cluster di Iris setosa ben separato nel sottospazio lunghezza-larghezza del petalo" reason="Cluster visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Analisi preliminare** (p. 55): media, deviazione standard, minimo e massimo per colonna.

```matlab
statisticsP = zeros(4, size(P,2));
statisticsP(1,:) = mean(P);
statisticsP(2,:) = std(P);
statisticsP(3,:) = min(P);
statisticsP(4,:) = max(P);
```

**MATLAB lavora nativamente per colonne**: `mean(P)` restituisce il vettore delle medie delle quattro feature, $[5{,}843;\ 3{,}057;\ 3{,}758;\ 1{,}199]$ cm.

**Grafici per coppie di feature** (pp. 56–57), con `gscatter(P(:,i), P(:,j), species, 'rgb', 'osd')` in una griglia $4 \times 4$ e le etichette in centimetri. Si possono creare **i propri grafici e dashboard**: è una **parte preziosa del lavoro**.

<!-- SOURCE_VISUAL id="IS17-V16" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="57" type="grafico" description="Griglia gscatter delle coppie di feature di Iris colorate per specie" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **EDA: un’applicazione** (p. 58). Dal grafico parallelo si era già verificata la **separazione almeno della classe setosa** usando **solo la lunghezza o la larghezza del petalo**. Questo approccio dà **SPIEGABILITÀ** ai modelli.

> 💡 **Una regola spiegabile (nota didattica).** Nel dataset la lunghezza del petalo di setosa non supera 1,9 cm, quella di versicolor e virginica parte da 3,0 cm. La regola «**se lunghezza del petalo < 2,5 cm allora setosa**» separa perfettamente la prima classe, e chiunque può capirla e verificarla: è l’albero di decisione più semplice possibile, suggerito dall’EDA prima ancora di addestrare un modello.

#### **4.3. Feature engineering con le etichette: l’analisi discriminante lineare**

_(slide pp. 59–65)_

Si richiama la **feature engineering con le etichette** (p. 60; Lezione 10, §4.2, livello C). Esempio: l’**analisi discriminante lineare** (LDA) di **Fisher** (1936).

> 📌 **LDA** (p. 61). Il metodo **ingegnerizza le feature** usando non solo le **distribuzioni** dei dati, ma anche le **etichette**: proietta i dati su una direzione in cui le classi sono il più possibile **separate**. La slide illustra l’idea in 2D, prima e dopo la proiezione; in 4 dimensioni lo stesso principio si applica a $P$ e $T$ (p. 62). L’argomento sarà trattato più avanti nel corso.

> 💡 **Il criterio di Fisher (nota didattica).** Per due classi con medie $\boldsymbol{\mu}_1$, $\boldsymbol{\mu}_2$ e matrice di dispersione intra-classe $S_W$, si cerca la direzione $\mathbf{w}$ che massimizza
> $$J(\mathbf{w}) = \frac{\big(\mathbf{w}^{\top}(\boldsymbol{\mu}_1 - \boldsymbol{\mu}_2)\big)^2}{\mathbf{w}^{\top} S_W\, \mathbf{w}}$$
> cioè la distanza tra le medie proiettate divisa per la dispersione dentro le classi. La PCA (Lezione 10, §4.2) cerca invece la massima varianza **senza** guardare le etichette.

<!-- SOURCE_VISUAL id="IS17-V17" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="61" type="grafico" description="Idea dell’analisi discriminante lineare di Fisher in 2D, prima e dopo la proiezione" reason="Proiezione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Classificazione lineare, errore e figure di merito** (pp. 62–65):

```matlab
% Linear Discriminant Analysis
lda = fitcdiscr(P,T);
% predict values using the predictor data stored in lda.X
ldaClass = resubPredict(lda);

% Classification error
errorVector = ldaClass ~= T;
figure; bar(errorVector);
xlabel('Sample'); ylabel('Classification Error')

% Figures of merit
totalError = sum(errorVector);
stdError = std(errorVector);
totalErrorPerc = mean(errorVector)*100;
stdErrorPerc = stdError * 100;

%Confusion Matrix
confusionMatrix = confusionmat(ldaClass,T)
```

La slide avverte che si tratta **solo di un esempio**: l’argomento sarà presentato più avanti. Valgono le due osservazioni della Lezione 16, §7.3: `resubPredict` misura l’errore **sui dati di addestramento** (stima ottimistica) e `confusionmat` riceve gli argomenti in **ordine inverso** rispetto a quello atteso.

<!-- SOURCE_VISUAL id="IS17-V18" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="64" type="grafico" description="Vettore degli errori di classificazione della LDA" reason="Barre degli errori visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Un nuovo modello: che cosa sapere e che cosa fare**

_(slide pp. 66–70)_

Ogni giorno escono **nuovi modelli** e nuove tecniche di apprendimento. Come tenere il passo (pp. 66–67)? La vignetta della slide: «**Usiamolo, non serve leggere la documentazione!**».

| **Che cosa sapere** (pp. 67–68) | |
|---|---|
| **documentazione ed esempi** | consultarli **prima** di perdere tempo |
| **argomento, tipo, contesto** | capire a che cosa serve il modello |
| **librerie** | su quali librerie si basa? è **generale** o **specifico**? |
| **complessità computazionale** | in **addestramento** e in **test/rilascio**; serve **CUDA**? |
| **memoria** | in addestramento e in test |
| **dimensione dei dati** | servono 1000 campioni o 10 milioni? |
| **parametri** | sono complessi da capire? il modello è **sensibile alla loro regolazione**? basta un **fine-tuning** o bisogna **riaddestrare**? |
| **portabilità** | funziona su librerie e ambienti diversi? è **aperto, GNU, libero**, o lo gestisce **una sola azienda**? |

**Che cosa fare** (p. 69):

- provare **demo ed esempi** della documentazione, verificando se il modello è adatto all’applicazione;
- provare il modello su un **insieme di dataset standard** e confrontare **a) accuratezza** e **b) tempi di calcolo**, **non solo su Iris**;
- ricordare il **rasoio di Occam**: stesse prestazioni, ma più complesso? **Ne vale la pena?**

> 📌 **Applicare un nuovo classificatore** (p. 70):
> $$\text{carica i dati} \to \text{feature engineering (+ visualizzazione)} \to \text{crea il classificatore} \to \text{addestramento e cross-validation} \to \text{valutazione dell’accuratezza (+ visualizzazione)}$$

---

### **6. Il regressore lineare: un esempio del flusso completo**

_(slide pp. 71–87)_

#### **6.1. Regressione e classificazione lineare**

Un esempio semplice per **ripercorrere tutti i passi** del flusso di progetto (pp. 71–72).

> 📌 **Regressione lineare** (p. 73). Trovare la **migliore funzione lineare** $y = f(x)$ che spiega i dati disponibili.

**Esempi** (p. 74): età, sesso e dieta → **altezza** del bambino; tipo e voti del college → **reddito annuo**; numero di **ore di studio** → **voto finale** di questo esame.

**Estendibile a $N$ feature** (p. 75). In forma vettoriale
$$z = \mathbf{w} \cdot \mathbf{x} + b$$
Con tre feature servono **4 parametri** ($w_1, w_2, w_3, b$), quindi bastano **4 punti**, ma **se ne useranno molti di più** (Lezione 07, §2.4). È la **rappresentazione** del modello.

> 📌 **Dal regressore al classificatore lineare** (pp. 76–77). Trovare una funzione lineare che **separa le classi**:
> $$f_1(\mathbf{x}) = \mathbf{w} \cdot \mathbf{x} + b \;\;\text{(regressore lineare)} \qquad f_2(\mathbf{x}) = \operatorname{sgn}(\mathbf{w} \cdot \mathbf{x} + b) \;\;\text{(classificatore lineare)}$$
> Nel caso 2D il regressore è un **piano** sopra lo spazio delle feature; il classificatore usa solo il **segno**, e la frontiera è la **retta** dove $\mathbf{w} \cdot \mathbf{x} + b = 0$.

<!-- SOURCE_VISUAL id="IS17-V19" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="77" type="grafico" description="Regressore lineare e classificatore lineare con la funzione segno in 2D" reason="Grafici 2D visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il valore predetto** (p. 78):
$$\hat{y} = \hat{\beta}_0 + \sum_{j=1}^{p} X_j \hat{\beta}_j$$
Il vettore dei coefficienti $\hat{\boldsymbol{\beta}}$ **è** il modello di regressione. **Nota di calcolo**: aggiungendo agli input un termine costante $X_0 = 1$, la formula diventa un semplice **prodotto matriciale**, con un’algebra più facile da gestire:
$$\hat{\mathbf{y}} = \mathbf{X} \hat{\boldsymbol{\beta}}$$

**Notazione** (p. 79). $\hat{y} = \hat{\beta}_0 + \sum_j X_j \hat{\beta}_j$ e $z = \mathbf{w} \cdot \mathbf{x} + b$ sono la stessa cosa: è anche l’**uscita lineare di un neurone**. I $w_j$ si chiamano **pesi** perché **moltiplicano** l’informazione in arrivo dagli input; $b$ si chiama **bias** perché è **sempre presente**, non modulato dall’input.

**Più feature** (p. 80). Tutti i campioni di addestramento si scrivono in un’unica matrice $\mathbf{X}$ e un vettore $\mathbf{y} = (y_1, \dots, y_n)$. Esempio: età, sesso, dieta, … di ogni individuo → **glicemia**. Qui $\mathbf{y}$ **non contiene classi**: si sta costruendo un **regressore**, quindi $\mathbf{y}$ contiene **numeri**.

> ⚠️ **Righe e colonne (nota didattica).** La slide scrive la matrice con elementi da $x_{11}$ a $x_{mn}$ e definisce $n$ = numero di campioni e $m$ = numero di feature. Nella convenzione usata dal corso per $X$ e $Y$ (Lezione 06, §6.1) e dalle formule che seguono, ogni **riga** è un **campione** e ogni **colonna** una **feature**: $\mathbf{X} \in \mathbb{R}^{n \times m}$, con $n$ righe.

#### **6.2. Minimi quadrati e gradiente**

> 📌 **Somma dei quadrati dei residui** (p. 81; appendice non d’esame). Per determinare i parametri $\hat{\boldsymbol{\beta}}$ si minimizza
> $$\text{RSS}(\boldsymbol{\beta}) = \sum_{i=1}^{N} (y_i - \mathbf{x}_i^{\top} \boldsymbol{\beta})^2 = (\mathbf{y} - \mathbf{X}\boldsymbol{\beta})^{\top} (\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$$
> Derivando rispetto a $\boldsymbol{\beta}$ e ponendo la derivata a zero si ottiene
> $$\mathbf{X}^{\top} (\mathbf{y} - \mathbf{X}\boldsymbol{\beta}) = 0$$
> e, se $\mathbf{X}^{\top}\mathbf{X}$ è **non singolare**, la soluzione unica è
> $$\hat{\boldsymbol{\beta}} = (\mathbf{X}^{\top}\mathbf{X})^{-1} \mathbf{X}^{\top} \mathbf{y}$$
> È l’**ottimizzazione** del modello.

**Soluzioni iterative** (p. 82; appendice non d’esame). Il metodo esatto richiede di **invertire** $\mathbf{X}^{\top}\mathbf{X}$, che spesso è **troppo grande**. Molti metodi **basati sul gradiente** riducono l’RSS usando la sua derivata rispetto a $\boldsymbol{\beta}$, che la slide scrive come
$$\nabla = \mathbf{X}^{\top} (\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$$
**Nota importante**: molti metodi neurali, anche nel deep learning, usano metodi basati sul gradiente; se il gradiente tende a zero, il metodo «**si blocca**».

> 💡 **Segno e fattore del gradiente (nota didattica).** La derivata esatta dell’RSS è $\nabla_{\boldsymbol{\beta}} \text{RSS} = -2\,\mathbf{X}^{\top}(\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$. L’espressione della slide è la stessa a meno del fattore $-2$: indica già la **direzione in cui muoversi** per ridurre l’errore. L’aggiornamento è quindi
> $$\boldsymbol{\beta} \leftarrow \boldsymbol{\beta} + \eta\, \mathbf{X}^{\top}(\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$$
> con un passo $\eta$ piccolo (il fattore 2 si assorbe in $\eta$).

> 📌 **Gradiente, gradiente, gradiente! (d’esame)** (p. 83).
> - Gran parte del ML consiste nel **trovare i parametri** (i pesi, qui $\boldsymbol{\beta}$).
> - $\boldsymbol{\beta}$ si trova **attraverso il gradiente**: gradiente → aggiornamento → nuovo gradiente → nuovo aggiornamento → …

**Il gradiente dipende da…** (p. 84). A ogni iterazione il gradiente dipende dai **dati** $\mathbf{X}$ e dai **parametri correnti** $\boldsymbol{\beta}$, e quindi, **implicitamente**, da tutti i passi precedenti che hanno portato $\boldsymbol{\beta}$ fin lì. Questa dipendenza ricorsiva **guida il percorso** dell’ottimizzazione: un percorso **insidioso**.

> 💡 **Perché «insidioso» (nota didattica).** Per la regressione lineare l’RSS è una funzione **convessa**, con un solo minimo: qualsiasi percorso ben regolato ci arriva. Nelle reti neurali la funzione di errore **non è convessa**: il punto di partenza e i passi precedenti decidono in quale minimo locale, o in quale zona piatta dove il gradiente è quasi nullo, l’ottimizzazione si fermerà (Lezione 11, §9.2).

#### **6.3. La regressione è buona? L’$R^2$**

_(slide pp. 85–87)_

Si può sempre adattare un modello lineare a qualsiasi dataset: come sapere se esiste davvero una **relazione lineare** (p. 85)?

> ⚠️ **Il quartetto di Anscombe** (p. 85). Quattro dataset con statistiche descrittive **quasi identiche** e lo stesso $R^2 = 0{,}67$, ma forme completamente diverse. Le cause possibili di un fit ingannevole: **dispersione o rumore**, **modello sbagliato**, **outlier**, **errori**. Con più di 2 dimensioni è **difficile capire visivamente** la qualità del fit.

> 💡 **I quattro casi (nota didattica).** Nel quartetto di Anscombe tutti i dataset hanno circa la stessa media e varianza di $x$ e $y$, la stessa retta di regressione $y \approx 3 + 0{,}5\,x$ e correlazione $r \approx 0{,}816$ ($R^2 \approx 0{,}67$). Ma uno è davvero lineare con rumore, uno è una **curva**, uno è una retta perfetta con **un solo outlier**, e uno ha tutti i punti sulla stessa $x$ tranne uno. La lezione: **i numeri non bastano, bisogna guardare i dati** (Lezione 11).

<!-- SOURCE_VISUAL id="IS17-V20" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="85" type="grafico" description="Quartetto di Anscombe: quattro dataset con stesso R2 = 0,67 e forme diverse" reason="Grafici dei quattro dataset visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **R-quadro** (pp. 86–87). Con $\hat{y}_i$ il valore predetto e $\bar{y}$ la media del campione:
> $$R^2 = 1 - \frac{\sum_{i} (y_i - \hat{y}_i)^2}{\sum_{i} (y_i - \bar{y})^2}$$
> Al numeratore la **somma dei quadrati dei residui** (distanze dalla retta dei $\hat{y}$), al denominatore la **somma totale dei quadrati** (distanze dalla retta della media $\bar{y}$, proporzionale alla varianza dei dati). $R^2 \to 1$ indica un **buon fit**. **L’$R^2$ va sempre riportato**, ed è particolarmente importante con più di 2 dimensioni, quando il fit non si può vedere.

> ⚠️ **Spiegata, non «non spiegata».** La p. 86 descrive l’$R^2$ come «la frazione della varianza totale **non** spiegata dal modello». È il contrario: il **rapporto** $\sum (y_i - \hat{y}_i)^2 / \sum (y_i - \bar{y})^2$ è la frazione **non spiegata**, e $R^2 = 1 -$ quel rapporto è la frazione **spiegata** (Lezione 16, §1.2).

<!-- SOURCE_VISUAL id="IS17-V21" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="87" type="grafico" description="R2 come confronto tra residui rispetto alla retta dei valori predetti e rispetto alla media" reason="Grafico con le due rette visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Laboratorio: regressore lineare e prime reti feed-forward**

_(slide pp. 88–111)_

#### **7.1. MATLAB: il regressore lineare**

**Il file MATLAB** (pp. 88–89): seguire le slide e poi lanciare lo script [Lesson_16_laboratory_MATLAB_linear_and_neural.m](Lesson_16_laboratory_MATLAB_linear_and_neural.m). Partendo dalla regressione si addestreranno e testeranno le **prime reti neurali**.

**Creare i dati** (p. 90): **51 punti** della retta $t = 2x - 5$ con **rumore gaussiano**.

```matlab
x = [0:0.2:10];                 % 51 punti da 0 a 10
noise = randn(size(x));
t = 2 * x - 5 + noise;
plot(x,t, 'o');
title('Linear model plus noise');
```

**Due metodi per creare e addestrare il regressore** (pp. 91–92):

| Metodo | Istruzioni | Caratteristiche |
|---|---|---|
| **1 (diretto)** | `net1 = newlind(x,t)` | soluzione **ottimizzata** basata sull’**inversione** della matrice dei dati, cioè i minimi quadrati del §6.2 |
| **2** | `net1 = linearlayer; net1 = configure(net1,[0],0);` pesi iniziali `net1.IW{1,1} = 3.1; net1.b{1} = -3.1;` poi `[net1, tr] = train(net1,x,t)` | inizializzazione come **rete lineare** (1 input, 1 strato) e addestramento con il metodo generale delle reti non lineari (la slide cita **Levenberg-Marquardt**): **generale, ma non ottimizzato**; lo script avverte che `train` crea **automaticamente** partizioni di training e validation e che serve «più messa a punto» |

Nello script il metodo 2 si trova nel ramo `else` di un `if (1)`, quindi viene eseguito sempre il metodo 1.

**Il regressore creato** (p. 93): $\text{output} = w \cdot \text{input} + b$. **Accedere ai parametri** (p. 94):

```matlab
view(net1)
W = net1.IW{1,1};
b = net1.b{1};
fprintf('trained linear model W = %f \n', W);
fprintf('trained linear model b = %f \n', b);
```

**Elaborare gli input e disegnare i risultati** (p. 95): `y1 = net1(x)` simula il modello sugli input, e la retta si sovrappone ai dati.

<!-- SOURCE_VISUAL id="IS17-V22" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="90" type="grafico" description="51 punti della retta t = 2x - 5 con rumore gaussiano" reason="Punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V23" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="92" type="schermata" description="Creazione del regressore come rete lineare con addestramento generale" reason="Schermata dell’addestramento visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V24" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="95" type="grafico" description="Retta del regressore lineare sovrapposta ai dati" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Valutazione dell’accuratezza** (p. 96):

```matlab
y1 = net1(x);                      % simulate the inputs
perf = perform(net1,y1,t)          % Error on **training**
net1.performFcn                    % the error metric (MSE for linear)
mse_check = sum((y1 - t).^2 ) / size(t,2);
mean_t = mean(t);
R2 = 1 - ( sum((t - y1).^2 ) / sum((t - mean_t).^2) );
```

> ⚠️ **ATTENZIONE** (p. 96). Queste misure riguardano il **TRAINING SET**: vanno **ripetute su un TEST SET**.

#### **7.2. MATLAB: reti feed-forward e overfitting**

_(slide pp. 97–104)_

**Proviamo una rete feed-forward!** (pp. 97–99). Lo script richiama esplicitamente il **rasoio di Occam**:

```matlab
net2 = feedforwardnet(3);            % inizializzazione con 3 neuroni
net2.divideParam.trainRatio = 1.0;   % tutti i dati nel TRAINING set
net2.divideParam.testRatio  = 0.0;
net2.divideParam.valRatio   = 0;
[net2, tr] = train(net2,x,t);        % addestramento
plotperform(tr)                      % andamento del MSE
y2 = net2(x);                        % esecuzione
perf = perform(net2,y2,t)            % Error on **training**
```

Si **forza** l’uso di **tutti i dati nel training set** (p. 98). La domanda della p. 99: la rete sta **adattando il rumore**? Con 3 neuroni la risposta della slide è **corretta**: la curva segue l’andamento lineare.

<!-- SOURCE_VISUAL id="IS17-V25" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="98" type="grafico" description="Andamento del MSE di addestramento della rete con 3 neuroni e tutti i dati nel training" reason="Curva visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V26" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="99" type="grafico" description="Uscita della rete con 3 neuroni sui dati: nessun adattamento al rumore" reason="Curva e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Progetto sbagliato: 100 neuroni con 51 punti!** (pp. 100–101), ancora **tutto in training**.

```matlab
net3 = feedforwardnet(100);
net3.divideParam.trainRatio = 1.0;
net3.divideParam.testRatio  = 0.0;
net3.divideParam.valRatio   = 0;
[net3, tr] = train(net3,x,t);
y3 = net3(x);
```

**Costruiamo un test set** (pp. 102–103): si **iniettano nella rete molti più punti** $x$, tra i punti di addestramento:

```matlab
x_bis = [0:0.0005:10];     % 20 001 punti
y3_bis = net3(x_bis);
plot(x_bis, y3_bis, '.k');
```

**…e abbiamo fatto overfitting (moltissimo!).**

> 📌 **Da ricordare** (p. 104). La prossima volta che si inizializza un modello con **#DoF ≫ #campioni**: **addestramento perfetto**, **generalizzazione pessima**.

> 💡 **I conti dei parametri (nota didattica).** Con 1 input e 1 uscita, una rete con $h$ neuroni nascosti ha $(1 + 1)\,h + (h + 1) = 3h + 1$ parametri (Lezione 07, §2.3):
> - `feedforwardnet(3)`: $3 \cdot 3 + 1 = 10$ parametri per 51 punti → $\text{DoF} \approx 10 - 51 < 0$;
> - `feedforwardnet(100)`: $3 \cdot 100 + 1 = 301$ parametri per 51 punti → $\text{DoF} \approx 301 - 51 = 250 > 0$.
>
> Con 301 parametri la rete può passare esattamente per tutti i 51 punti rumorosi e, tra un punto e l’altro, fare qualsiasi cosa: è ciò che mostrano i 20 001 punti di `x_bis`. La retta vera ha solo **2** parametri.

<!-- SOURCE_VISUAL id="IS17-V27" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="101" type="grafico" description="Rete con 100 neuroni addestrata su 51 punti: fit perfetto sul training" reason="Punti e uscita visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V28" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="103" type="grafico" description="Rete con 100 neuroni valutata su 20 001 punti: forte overfitting tra i punti di training" reason="Oscillazioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V29" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="104" type="grafico" description="Addestramento perfetto e generalizzazione pessima con #DoF molto maggiore dei campioni" reason="Confronto visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3. Python: il regressore lineare in Colab**

_(slide pp. 105–111)_

**Il file Python in Colab** (p. 105): seguire le slide e lanciare il notebook [Lesson_16_laboratory_COLAB_linear_regressors.ipynb](Lesson_16_laboratory_COLAB_linear_regressors.ipynb), che mostra come creare un regressore, controllarne l’accuratezza e disegnare i grafici.

**Librerie e dati** (pp. 106–107):

```python
import matplotlib.pyplot as plt
import numpy as np

rng = np.random.RandomState(1)
num_points = 50
x = 10 * rng.rand(num_points)
t = 2 * x - 5 + rng.randn(num_points)   # retta con pendenza 2 e intercetta -5, più rumore
plt.scatter(x, t);
```

**Creazione del regressore lineare** (p. 108), con lo stimatore `LinearRegression` di scikit-learn:

```python
from sklearn.linear_model import LinearRegression
model = LinearRegression(fit_intercept=True)

model.fit(x[:, np.newaxis], t)        # newaxis adds a dimension (needed from the function)
y1 = model.predict(x[:, np.newaxis])  # predicted values

xfit = np.linspace(0, 10, 1000)       # 1000 punti tra 0 e 10 per disegnare la retta
y1_test = model.predict(xfit[:, np.newaxis])
plt.scatter(x, t)
plt.plot(xfit, y1_test);
```

`x[:, np.newaxis]` trasforma il vettore di 50 elementi in una **matrice** $50 \times 1$: scikit-learn si aspetta sempre una matrice con **una riga per campione** e **una colonna per feature**. Nella slide la chiamata a `predict` è scritta senza la parentesi di chiusura.

**Parametri del modello** (p. 110):

```python
print("Model slope:    ", model.coef_[0])     # 2.0272088103606953
print("Model intercept:", model.intercept_)    # -4.998577085553204
```

Pendenza **2,027** e intercetta **−4,999**, molto vicine ai valori veri 2 e −5.

**Valutazione dell’accuratezza** (p. 111):

```python
from sklearn.metrics import mean_squared_error, r2_score
from math import sqrt

mse = mean_squared_error(y1, t)
rmse = sqrt(mse)
R2 = r2_score(y1, t)
# MSE:  0.8183388570266171
# RMSE: 0.9046208360559783
# R2:   0.9786330659856474
```

> ⚠️ **ATTENZIONE** (p. 111). Bisogna creare un **TEST SET** per ottenere la vera accuratezza di generalizzazione: questi valori sono calcolati sul **TRAINING SET**. I numeri sono **diversi da MATLAB** per la diversa inizializzazione dei dati (50 punti casuali con seme fisso in Python, 51 punti equispaziati con rumore non riproducibile in MATLAB).

> ⚠️ **Ordine degli argomenti di `r2_score` (nota didattica).** La firma è `r2_score(y_true, y_pred)`: il notebook passa `r2_score(y1, t)`, cioè le predizioni al posto dei valori veri. Il MSE è simmetrico e non cambia, ma l’$R^2$ **no**, perché il denominatore usa la varianza del primo argomento: la forma corretta è `r2_score(t, y1)`. Anche la cella di testo che precede usa per errore la sintassi di MATLAB (`.^`, `size(t,2)`).

<!-- SOURCE_VISUAL id="IS17-V30" source="Lesson_17_AgentContext_LinearRegressionAndClassification_Coding_FFNN_overfitting.pdf" page="109" type="grafico" description="Retta di regressione di scikit-learn sovrapposta ai 50 punti" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Compito dell’agente** / **validazione umana** | ispezionare, proporre, calcolare, generare / leakage, unità, causalità, validità della divisione, conclusioni |
| **Chiamata all’LLM** / **applicazione** | senza stato, contesto scartato / conserva memoria, file e storia |
| **Finestra grande** / **comprensione** | quanti token entrano / quanto bene il modello li usa |
| **Contesto per turno** / **token totali** | cresce linearmente ($\approx 350N$) / cresce col quadrato |
| **Pruning** / **riassunto** | elimina contenuto irrilevante / comprime, con rischio di perdere decisioni e numeri |
| **Offloading** / **quarantena** | informazioni fuori dal prompt, recuperate quando servono / sottocompiti in contesti separati |
| **`/compact`** / **`/clear`** | contesto troppo grande / cambio di compito |
| **Errore di training nullo** / **errore di generalizzazione** | memorizzazione / prestazione vera, stimata con la cross-validation |
| **Bias alto** / **varianza alta** | modello troppo semplice, underfitting / troppo complesso, overfitting |
| **`plotmatrix`** / **`pairplot` colorato** | analisi non supervisionata / analisi supervisionata |
| **PCA** / **LDA** | massima varianza senza etichette / massima separazione tra classi con le etichette |
| **Regressore lineare** / **classificatore lineare** | $\mathbf{w} \cdot \mathbf{x} + b$ / $\operatorname{sgn}(\mathbf{w} \cdot \mathbf{x} + b)$ |
| **Soluzione esatta** / **gradiente** | $(\mathbf{X}^\top \mathbf{X})^{-1}\mathbf{X}^\top \mathbf{y}$, costosa per matrici grandi / aggiornamenti iterativi, usati anche dalle reti |
| **$R^2$** / **rapporto RSS/TSS** | frazione di varianza spiegata / frazione non spiegata |
| **`newlind`** / **`linearlayer` + `train`** | minimi quadrati diretti / addestramento generale non ottimizzato |
| **3 neuroni** / **100 neuroni su 51 punti** | 10 parametri, fit corretto / 301 parametri, overfitting |

---

### **9. Sintesi della lezione**

_(slide p. 112)_

> ✅ **Punti principali** (p. 112).
> - **Memoria degli agenti e gestione del contesto**: il contesto si ricostruisce a ogni turno, cresce e degrada (context rot); offloading, RAG, selezione degli strumenti, pruning, riassunto e quarantena; riassunti di passaggio, `/compact`, `/clear`, sotto-agenti. Le decisioni critiche restano da validare a una persona.
> - **Caricamento, EDA e grafici in MATLAB**: `plotmatrix`, `gscatter`, statistiche per colonna, setosa separata dal petalo, LDA come feature engineering con le etichette.
> - **Nuovo modello?** Leggere la documentazione, conoscerne complessità, memoria, dati e parametri, provarlo su dataset standard confrontando accuratezza e tempi, e applicare il rasoio di Occam.
> - **Il dilemma bias-varianza**: training sempre migliore, validazione a U; media e deviazione standard della k-FCV come stima dell’errore e della sua variabilità.
> - **Il primo modello completo**: regressore lineare con minimi quadrati, gradiente ed $R^2$ (da riportare sempre, e da non fidarsi senza guardare i dati).
> - **Prime reti neurali**: `newlind` e `feedforwardnet(3)` corretti, `feedforwardnet(100)` su 51 punti con training perfetto e generalizzazione pessima.
> - **Valutazione dell’accuratezza**: le misure sul training vanno sempre ripetute su un test set.
