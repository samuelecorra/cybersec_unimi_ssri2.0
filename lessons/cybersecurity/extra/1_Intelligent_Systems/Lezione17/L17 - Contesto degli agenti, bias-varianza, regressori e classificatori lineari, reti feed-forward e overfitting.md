## **Lezione 17: Contesto degli agenti, bias-varianza, regressori e classificatori lineari, reti feed-forward e overfitting**

La gestione degli agenti e dei modelli predittivi richiede due competenze complementari: mantenere il contesto utile durante il lavoro e valutare la capacità di generalizzare dai dati osservati. Da queste basi emergono il dilemma bias-varianza, la classificazione lineare e la regressione, fino alle reti feed-forward e al loro rischio di overfitting.

### **0. Struttura della lezione**

La lezione procede dalla gestione del contesto alla valutazione dei modelli, poi sviluppa il compromesso bias-varianza, la classificazione LDA e la regressione fino alle prime reti neurali.

1. agenti, memoria e gestione del contesto;
2. errori di classificazione sul dataset Wine e cross-validation;
3. compromesso bias-varianza;
4. feature engineering, PCA e LDA;
5. criteri per valutare modelli nuovi;
6. regressione lineare, ottimizzazione e $R^2$;
7. laboratori MATLAB e Python, reti feed-forward e overfitting;
8. punti principali e approfondimento matematico.

---

### **1. Agenti nel flusso di lavoro e gestione del contesto**

#### **1.1. Dove aiutano gli agenti e che cosa validare**

**Agenti per il ML: dove possono essere utili e che cosa possono fare**.

| Fase del flusso | Che cosa può fare l’agente | Che cosa deve validare una persona |
|---|---|---|
| **definizione del problema** | ricostruire il **contesto**, estrarre i **vincoli** | **definizione del compito**, **variabile target** |
| **dati** | ispezionare i file, individuare **valori mancanti**, proporre la **pulizia** | **leakage**, **unità di misura**, **etichette** |
| **rappresentazione** | suggerire **feature**, codificare le variabili **categoriche** | **significato** delle feature, rischio di **causalità** spuria |
| **modello** | proporre **baseline** e modelli candidati | **scelta del modello** e sue **ipotesi** |
| **valutazione** | calcolare le **metriche**, confrontare i modelli | **validità della divisione** di test, **significato statistico** |
| **reportistica** | generare **tabelle, grafici, spiegazioni** | **affermazioni** e **conclusioni** |
| **monitoraggio** | preparare bozze di **controlli del drift** e **dashboard** | **rischio del rilascio** e **soglie** |

> 💡 **Lo schema dietro la tabella (nota didattica).** La colonna di destra raccoglie rischi ricorrenti: leakage, unità non armonizzate, correlazione scambiata per causalità e stime ottimistiche dovute a una divisione scorretta dei dati. L’agente accelera le operazioni, mentre **una persona valida le decisioni che possono compromettere il progetto**.

#### **1.2. Il contesto e la sua dimensione**

**Memoria degli agenti e gestione del contesto**: controllare, gestire, comprimere, ricominciare.

> 📌 **Contesto.** Negli LLM e negli agenti è lo **stato attivo di informazioni** passato al modello al momento dell’inferenza: istruzioni, storia della conversazione, documenti recuperati, risultati degli strumenti, regole, riassunti di memoria e definizioni degli strumenti disponibili.

**Le finestre di contesto si sono ampliate.** A metà 2026 diversi modelli cloud di fascia alta pubblicizzano finestre di circa **un milione di token**, sufficienti per inserire interi codebase o centinaia di documenti in un singolo prompt. Un milione di token equivale a circa **750 000 parole** o oltre **2 000 pagine** di testo. La capacità effettiva dipende dal prodotto, dal piano e dalle condizioni d’uso:

- i test «**ago nel pagliaio**» (*needle in a haystack*) misurano il recupero di informazioni lontane nel contesto;
- la latenza, i costi e la qualità del ragionamento possono peggiorare quando si sfrutta una finestra molto ampia.

**Aggiornamento dei modelli (20 novembre 2026).** L’elenco di modelli e capacità cambia rapidamente: tra gli esempi cloud attuali, GPT‑6.2 Astra dichiara una finestra di 1,05 milioni di token, Claude Opus 5.5 e Sonnet 5.5 1 milione, Gemini 4 Argon 1 milione. I valori pubblicati descrivono limiti nominali e non garantiscono la stessa qualità di ragionamento su tutta la finestra; le capacità e i nomi vanno ricontrollati quando si sceglie un modello ([OpenAI](https://developers.openai.com/api/docs/models/compare), [Anthropic](https://www.anthropic.com/claude/opus), [Google](https://ai.google.dev/gemini-api/docs/pricing)).

Per i modelli a pesi aperti, alcune finestre pubblicizzate variano da **256 000 token a 1 milione**; l’uso pratico può essere molto inferiore (circa **8 000–128 000 token**) per limiti di RAM/VRAM e crescita della cache KV. Un contesto lungo richiede quindi memoria e velocità adeguate. I modelli locali offrono controllo dei dati e costi marginali contenuti; i servizi cloud offrono infrastruttura e strumenti, con costi che crescono con i token e dati elaborati all’esterno.

In locale si possono trovare finestre dichiarate tra 256K e 1M token, ma l’uso effettivo dipende dalla memoria: la cache KV cresce con la lunghezza del contesto. Per esempio, la guida usa `gpt-oss-20b` su circa 16 GB, `gpt-oss-120b` su una GPU da 80 GB, Qwen3.5 (262K), Gemma 4 (256K) e DeepSeek-V4 (1M su cluster). Queste cifre descrivono l’hardware e le versioni indicati, da verificare al momento del deployment.

Esempi citati per il deployment locale includono `gpt-oss-20b` su circa 16 GB di memoria, `gpt-oss-120b` su una GPU da 80 GB, Qwen3.5, Gemma 4 e DeepSeek-V4. Sono ordini di grandezza dipendenti da quantizzazione, librerie e hardware.

> ⚠️ **Una finestra più ampia non garantisce una comprensione migliore.** La capacità di recuperare un’informazione isolata non equivale alla capacità di ragionare correttamente su molte informazioni distribuite nel contesto (*lost in the middle*).

<!-- SOURCE_VISUAL id="IS17-V01" source="L17-web.pdf" page="7" type="grafico" description="Crescita della dimensione della finestra di contesto dei modelli" reason="Valori del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V02" source="L17-web.pdf" page="8" type="grafico" description="Dimensione del contesto di modelli locali e cloud: finestra più grande non significa comprensione migliore" reason="Confronto visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Il contesto si ricostruisce a ogni turno**. Una chiamata a un LLM è **senza stato** (*stateless*): dopo aver generato la risposta, il contesto attivo viene **scartato**. L’**applicazione** che lo circonda conserva conversazione e informazioni persistenti: per esempio un assistente conversazionale, un IDE o un framework di agenti personalizzato.

<!-- SOURCE_VISUAL id="IS17-V03" source="L17-web.pdf" page="9" type="diagramma" description="Ricostruzione del contesto a ogni turno di chat da parte dell’applicazione" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Il contesto cresce**

Nelle chat e nei flussi agentici il contesto **cresce**.

**Esempio**. Prompt di sistema di **200 token**, ogni messaggio dell’utente circa **50 token**, ogni risposta circa **300 token**. Poiché a ogni turno si rimanda tutta la conversazione:

| Turno | Token inviati all’LLM | Risposta | Dimensione del log dopo il turno |
|---|---|---|---|
| 1 | $200 + 50 = 250$ | 300 | 350 |
| 2 | $200 + 50 + 300 + 50 = 600$ | 300 | 700 |
| 3 | $200 + 50 + 300 + 50 + 300 + 50 = 950$ | 300 | — |
| $N$ | $\approx 200 + 50N + 300(N-1) \approx 350N$ | 300 | — |

> 💡 **Il costo cresce col quadrato (nota didattica).** Il contesto del turno $k$ vale esattamente $350k - 100$ token. Sommando su tutti i turni, i token **inviati in totale** sono
> $$\sum_{k=1}^{N} (350k - 100) = 175\,N(N+1) - 100\,N$$
> che cresce come $N^2$: 10 turni costano circa 18 250 token in ingresso, 100 turni circa 1,76 milioni. Ecco perché, nell’economia dei token (costo delle sequenze di token), una conversazione lunga diventa rapidamente costosa e lenta.

<!-- SOURCE_VISUAL id="IS17-V34" source="L17-web.pdf" page="11" type="grafico" description="Crescita del contesto delle conversazioni e degli agenti nel tempo" reason="Andamento del contesto rappresentato graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**A ogni nuovo turno** l’applicazione compone il contesto attivo selezionando informazioni da fonti diverse: messaggio corrente, storia recente, istruzioni di sistema e dello sviluppatore, riassunti, memorie salvate, file di progetto, documenti recuperati, risultati degli strumenti e porzioni pertinenti di log o codice. Una formulazione concisa riduce il numero di token aggiunti a ogni turno.

#### **1.4. Il context rot e le tecniche di gestione**

> 📌 **Context rot.** Evitare di accumulare indiscriminatamente tutto il contesto: la finestra misura la memoria disponibile, mentre la qualità delle risposte può peggiorare prima che sia piena: man mano che la conversazione cresce, la **qualità delle risposte può peggiorare prima** che il contesto sia tecnicamente pieno. Le informazioni rilevanti diventano più difficili da **prioritizzare**, le istruzioni **competono** con contenuti più vecchi, e il modello può iniziare a **ignorare o usare male** le evidenze. I sistemi pratici hanno quindi bisogno di **strategie di gestione** (recupero, riassunto, rotazione, compattazione automatica) **prima** che la qualità crolli.

**Context engineering**: un insieme di meccanismi per **prevenire il context rot** gestendo **che cosa il modello può vedere**.

| Tecnica | Come funziona | Attenzione a |
|---|---|---|
| **1. Offloading** | conservare informazioni grandi fuori dal prompt e rileggerle quando servono; un piano da 2 000 token occupa 20 000 token in 10 turni se reinserito sempre, contro circa 450 usando un riferimento di 30 token e recuperando 150 token al bisogno | — |
| **2. RAG** | indicizzare una volta una raccolta e recuperare solo i passaggi pertinenti; da un manuale di 200 pagine (~100 000 token) si possono inviare al modello tre segmenti (~1 500 token) | qualità del recupero e dei segmenti |
| **3. Selezione dinamica degli strumenti** (*tool loadout*) | esporre gli strumenti del compito: da 40 definizioni da 200 token (8 000 token) si può passare ai 4 strumenti necessari (800 token) | strumenti superflui occupano contesto e favoriscono chiamate inappropriate |
| **4. Pruning** | eliminare materiale irrilevante, obsoleto o duplicato; un log di 3 000 token può essere ridotto a 1 200 conservando l’informazione utile | annotare la lezione appresa prima di eliminare il tentativo fallito |
| **5. Riassunto** | comprimere 60 turni (~30 000 token) in un riepilogo di circa 1 500 token, mantenendo intatti gli ultimi turni | si possono perdere decisioni, vincoli, numeri o evidenze |
| **6. Quarantena** | isolare i sottocompiti in contesti separati; cinque agenti possono leggere cinque articoli da 20 000 token ciascuno e restituire al contesto principale cinque sintesi da 100 token | definire l’ambito e il formato del risultato da restituire |

<!-- SOURCE_VISUAL id="IS17-V35" source="L17-web.pdf" page="15" type="schema" description="Offloading di un piano in notes.md con recupero selettivo del contenuto" reason="Confronto quantitativo del contesto prima e dopo offloading" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V36" source="L17-web.pdf" page="16" type="schema" description="Confronto tra documenti interamente nel prompt e riassunti esterni recuperati al bisogno" reason="Architettura di confronto mostrata nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V37" source="L17-web.pdf" page="17" type="schema" description="Recupero RAG di tre segmenti pertinenti da un manuale indicizzato" reason="Flusso di retrieval rappresentato nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V38" source="L17-web.pdf" page="18" type="schema" description="Selezione dinamica dei quattro strumenti utili fra quaranta disponibili" reason="Tool loadout esemplificato nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V39" source="L17-web.pdf" page="19" type="schema" description="Pruning di tentativi falliti e duplicati conservando la lezione utile" reason="Esempio di riduzione del contesto rappresentato visivamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V40" source="L17-web.pdf" page="20" type="schema" description="Riassunto compatto di una lunga conversazione con i turni recenti intatti" reason="Compressione del contesto e relativo rischio raffigurati" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V41" source="L17-web.pdf" page="21" type="schema" description="Quarantena di cinque sotto-compiti e ritorno di risultati sintetici al contesto principale" reason="Separazione dei contesti visualizzata nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Un’analogia utile (nota didattica).** Il pruning è una **selezione delle feature** applicata al contesto, il riassunto è una **riduzione della dimensionalità** con perdita di informazione, la quarantena è il **divide et impera**. E come con i dati (GIGO), un contesto pieno di materiale obsoleto produce risposte peggiori anche con un modello eccellente.

#### **1.5. Igiene operativa del contesto**

**Gestire il contesto durante un flusso agentico**:

- **monitorare la crescita** del contesto: chat lunghe, file grandi, output ripetuti e risultati degli strumenti consumano la finestra;
- **ridurre** il contesto eliminando file irrilevanti, **riassumendo il lavoro completato** e tenendo solo le **decisioni correnti**;
- usare **RAG** o una **memoria di progetto** per la conoscenza stabile, invece di incollare ripetutamente lo stesso materiale;
- **compattare o ricominciare** quando la conversazione contiene **ipotesi superate**, rami abbandonati o troppi sottocompiti conclusi.

> ⚠️ **Segnale di rischio**. L’agente inizia a **dimenticare i vincoli**, a **mescolare decisioni vecchie** o a **leggere le evidenze sbagliate**.

**Pratiche operative**:

- chiedere un **riassunto di passaggio** (*handoff*): obiettivi, decisioni, file toccati, questioni aperte, test, prossimi passi;
- **aprire una nuova conversazione** a partire da quel riassunto quando la precedente diventa troppo lunga o dispersiva;
- in **Claude Code**, usare **`/compact`** quando il contesto diventa grande e **`/clear`** quando si cambia compito;
- dare istruzioni di compattazione **mirate**, per esempio: conservare modifiche al codice, risultati dei test, decisioni di progetto e rischi irrisolti;
- usare **sotto-agenti** per sottocompiti delimitati, quando finestre di contesto separate aiutano a preservare la conversazione principale.

---

### **2. Errori di classificazione: l’albero Wine in Python**

#### **2.1. Albero poco profondo e albero «perfetto»**

L’**albero di decisione sul dataset Wine** consente di osservare gli errori di classificazione e la differenza tra prestazione sul training e generalizzazione. Lo script [Lesson_16_decision_tree_wine_confusionmatrix_crossvalidation.py](Lesson_16_decision_tree_wine_confusionmatrix_crossvalidation.py) usa i nomi reali dei tre cultivar:

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

Ogni riga del dataset rappresenta un vino descritto da 13 misure chimiche; le tre classi sono i cultivar Barolo, Grignolino e Barbera. La matrice di confusione dell’albero con profondità 3 riporta 57, 70 e 47 classificazioni corrette, per un totale di 174 su 178: l’accuratezza sul training è $174/178 \approx 97{,}75\%$.

| Passo | Che cosa si osserva |
|---|---|
| **albero poco profondo** | classifica correttamente 174 dei 178 vini; la matrice di confusione individua i quattro errori. Esempio: l’albero ha risposto «**Grignolino**» ma il cultivar vero era «**Barolo**»: «che errore imperdonabile!» |
| **aumentare la profondità** | l’albero di profondità 5 raggiunge il 100% di accuratezza sui dati usati per il fit |

<!-- SOURCE_VISUAL id="IS17-V04" source="L17-web.pdf" page="24" type="diagramma" description="Albero poco profondo sul dataset Wine, non perfetto" reason="Nodi e soglie visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V05" source="L17-web.pdf" page="26" type="matrice di confusione" description="Risultati delle 178 predizioni dell’albero di profondità 3, righe per classe reale e colonne per classe predetta" reason="I conteggi delle singole celle sono presentati nella matrice" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V06" source="L17-web.pdf" page="25" type="albero" description="Esempio di foglia dell’albero shallow con cinque Grignolino e un Barbera" reason="Composizione dei nodi terminali visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V07" source="L17-web.pdf" page="30" type="matrice di confusione" description="Matrice senza errori dell’albero di profondità 5 sui dati di addestramento" reason="I conteggi della matrice sono presentati graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Le matrici dello script sono sui dati di addestramento.** Entrambe le matrici di confusione usano `predict(X)` sugli stessi dati di `fit(X, y)`: misurano quanto l’albero ha **memorizzato**, non quanto **generalizza** (validazione).

#### **2.2. È una buona idea creare un learner perfetto?**

> 📌 **Un errore di training nullo non assicura una generalizzazione perfetta.**
> - La **memorizzazione e la cattura del rumore** costituiscono overfitting: i modelli ad alta capacità memorizzano **regolarità spurie** e **rumore di misura**, rendendo le predizioni **fragili** quando la distribuzione cambia (nuove annate, deriva dei sensori, …).
> - Un errore di training nullo è un segnale che richiede una verifica su dati indipendenti.
>
> **Rischi pratici**:
> - **predizioni instabili**: piccole perturbazioni delle feature portano a foglie e percorsi diversi;
> - **manutenzione difficile**: il modello va riaddestrato spesso perché il drift rompe le regole memorizzate;
> - **stakeholder ingannati**: una metrica apparente del 100% **nasconde l’errore reale**.

**Verificare la generalizzazione con la cross-validation**. Con una **10-fold CV** l’albero **non è così perfetto**:

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

> 💡 **Che cosa fa `cross_val_score`.** Per ciascuno dei 10 fold addestra una copia dell’albero sui nove fold restanti e la valuta sul fold escluso. I punteggi sono $[0{,}889, 0{,}889, 0{,}722, 0{,}889, 0{,}833, 0{,}833, 1{,}000, 0{,}944, 0{,}941, 0{,}765]$; la loro media è circa $0{,}871$. La dispersione mostra quanto la stima varia tra le partizioni.

<!-- SOURCE_VISUAL id="IS17-V08" source="L17-web.pdf" page="35" type="grafico" description="Accuratezze della 10-fold cross-validation con la media" reason="Valori delle barre visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Il dilemma bias-varianza**

«**Una cosa da sapere per tutta la vita da esperti di AI**».

**Un modello troppo semplice o troppo complesso.** Un esempio usa sette punti di training e polinomi di grado 1, 2 e 6; la curva di grado 6 passa per ogni punto di training, ma ha un errore maggiore su 2 000 nuovi punti. La complessità del modello va quindi giudicata sulla generalizzazione.

<!-- SOURCE_VISUAL id="IS17-V45" source="L17-web.pdf" page="37" type="grafico" description="Polinomi di grado 1, 2 e 6 sugli stessi sette punti e confronto dell’errore su 2 000 nuovi punti" reason="Curve e punti di test sono essenziali per cogliere l’overfitting" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Illustrazione empirica sul dataset Wine**. Si calcolano l’**errore medio** e la **varianza degli errori** nelle dieci partizioni della cross-validation.

> 📌 **Esperimento: alberi da profondità 1 a 10, 10-fold CV**.
> - L’**accuratezza di training cresce** in modo monotono.
> - L’**accuratezza di validazione** raggiunge un **picco** e poi si **appiattisce o cala**: la classica forma a **U** dell’errore.
> - Dalla **profondità 5** in su l’albero memorizza i campioni di training (bias vicino a zero), mentre l’accuratezza di validazione si appiattisce o diminuisce.

<!-- SOURCE_VISUAL id="IS17-V09" source="L17-web.pdf" page="38" type="grafico" description="Media e varianza dell’errore di cross-validation sul dataset Wine" reason="Valori del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V10" source="L17-web.pdf" page="39" type="grafico" description="Illustrazione bias-varianza su Wine: accuratezza di training e di validazione al variare della profondità" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nell’esempio del controllo di volo, un bias elevato genera una traiettoria troppo liscia che non segue l’ampiezza necessaria: l’errore è sistematico e stabile. Una varianza elevata fa oscillare la traiettoria attorno al percorso medio, rendendola sensibile ai dati. Il controllo richiede bias e varianza bassi.

Il compromesso si manifesta sia nelle frontiere di un **classificatore** sia nelle curve di un **regressore**.

<!-- SOURCE_VISUAL id="IS17-V11" source="L17-web.pdf" page="40" type="grafico" description="Il dilemma bias-varianza per un classificatore" reason="Frontiere di decisione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V12" source="L17-web.pdf" page="41" type="grafico" description="Il dilemma bias-varianza per un regressore" reason="Curve di regressione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **La teoria: scomposizione dell’errore di predizione.** Mediando su molte possibili partizioni e sul rumore, si ottiene
> $$\mathbb{E}[(y-\hat y)^2] = \operatorname{Bias}[\hat y]^2 + \operatorname{Var}[\hat y] + \sigma^2$$
> Il rumore ha varianza $\sigma^2$ ed è irriducibile; $\mathbb{E}[\cdot]$ indica la media che si otterrebbe **ripetendo l’esperimento molte volte**.
> - **Bias alto → underfitting**: un modello troppo semplice produce predizioni sistematicamente lontane dalla funzione vera.
> - **Varianza alta → overfitting**: un modello troppo complesso cambia molto tra dataset e produce predizioni instabili.
> - Il **punto ottimale** bilancia i due termini per minimizzare l’errore atteso.

**Media e deviazione standard della k-FCV.** È utile monitorare l’**errore medio** e la **deviazione standard** tra i fold: la k-FCV approssima l’errore di predizione usando diverse **partizioni del dataset**.

> 💡 **Interpretare media e dispersione.** La scomposizione usa una media su molti training set possibili. La k-FCV li approssima: ogni fold produce un modello addestrato su una partizione diversa. La media degli errori stima l’errore atteso, mentre la loro deviazione standard descrive la sensibilità del modello ai dati.

> 📌 **Visualizzare il compromesso**. Sull’asse orizzontale **non c’è il tempo né le epoche: c’è la complessità** del modello.

<!-- SOURCE_VISUAL id="IS17-V13" source="L17-web.pdf" page="43" type="grafico" description="Compromesso bias-varianza in funzione della complessità del modello" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Feature engineering e classificazione lineare**

Il materiale aggiornato introduce PCA, LDA e una classificazione di Iris con divisione training/validation. L’esplorazione in MATLAB qui sotto resta un approfondimento operativo complementare.

#### **4.1. Approfondimento: caricamento ed esplorazione di Iris in MATLAB**

**Caricare il dataset**, come nello script dell’analisi di Iris:

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

**Creare le strutture dati**: la matrice delle **feature** `P` e il vettore delle **etichette** `T`.

```matlab
P = meas;                          % Problem (X in in seguito)
T = zeros(size(species));          % Targets (Y in in seguito)
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

> ⚠️ **Nomi delle specie diversi (nota didattica).** Nel file UCI scaricato con `websave` i nomi delle specie sono `'Iris-setosa'`, `'Iris-versicolor'`, `'Iris-virginica'`; mentre `load fisheriris` usa `'setosa'`, `'versicolor'`, `'virginica'`. Se si combinano il file UCI e i `case` senza prefisso, nessuna condizione corrisponde e $T$ resta interamente a zero; un grafico `bar(T)` dopo il ciclo permette di rilevare l’errore.

#### **4.2. Visualizzazione supervisionata e non supervisionata**

**`plotmatrix`: feature $i$ contro feature $j$**:

```matlab
figure
plotmatrix(P)
title('scatter plot of the features')
```

> 📌 **`plotmatrix` contro `pairplot`**. `plotmatrix` disegna le coppie di feature **senza etichette**: è un’analisi **non supervisionata**. Il `pairplot` di seaborn colorato per specie (pairplot supervisionato) usa le **etichette**: è un’analisi **supervisionata**.

**Cluster nell’analisi non supervisionata**. Nel sottospazio **F3–F4** (lunghezza e larghezza del petalo) si vede un **cluster di Iris setosa ben separato**, anche senza conoscere le etichette.

**Analisi preliminare**: media, deviazione standard, minimo e massimo per colonna.

```matlab
statisticsP = zeros(4, size(P,2));
statisticsP(1,:) = mean(P);
statisticsP(2,:) = std(P);
statisticsP(3,:) = min(P);
statisticsP(4,:) = max(P);
```

**MATLAB lavora nativamente per colonne**: `mean(P)` restituisce il vettore delle medie delle quattro feature, $[5{,}843;\ 3{,}057;\ 3{,}758;\ 1{,}199]$ cm.

**Grafici per coppie di feature**, con `gscatter(P(:,i), P(:,j), species, 'rgb', 'osd')` in una griglia $4 \times 4$ e le etichette in centimetri. Si possono creare **i propri grafici e dashboard**: è una **parte preziosa del lavoro**.

> 📌 **EDA: un’applicazione**. Dal grafico parallelo si era già verificata la **separazione almeno della classe setosa** usando **solo la lunghezza o la larghezza del petalo**. Questo approccio dà **SPIEGABILITÀ** ai modelli.

> 💡 **Una regola spiegabile (nota didattica).** Nel dataset la lunghezza del petalo di setosa non supera 1,9 cm, quella di versicolor e virginica parte da 3,0 cm. La regola «**se lunghezza del petalo < 2,5 cm allora setosa**» separa perfettamente la prima classe, e chiunque può capirla e verificarla: è l’albero di decisione più semplice possibile, suggerito dall’EDA prima ancora di addestrare un modello.

#### **4.3. LDA: proiezione guidata dalle etichette**

Si richiama la **feature engineering con le etichette**. Esempio: l’**analisi discriminante lineare** (LDA) di **Fisher** (1936).

> 📌 **LDA.** Il metodo combina le distribuzioni delle feature con le etichette e proietta i dati su direzioni che separano le classi. La stessa idea si applica a due o più dimensioni.

> 💡 **Criterio di Fisher (nota didattica).** Per due classi con medie $\boldsymbol{\mu}_1$, $\boldsymbol{\mu}_2$ e matrice di dispersione intra-classe $S_W$, si cerca la direzione $\mathbf{w}$ che massimizza
> $$J(\mathbf{w}) = \frac{\big(\mathbf{w}^{\top}(\boldsymbol{\mu}_1 - \boldsymbol{\mu}_2)\big)^2}{\mathbf{w}^{\top} S_W\, \mathbf{w}}$$
> cioè la distanza tra le medie proiettate divisa per la dispersione dentro le classi. La PCA cerca gli assi di massima varianza senza usare le etichette.

<!-- SOURCE_VISUAL id="IS17-V17" source="L17-web.pdf" page="48" type="grafico" description="Proiezione delle due classi sugli assi LDA, con separazione lungo la prima componente" reason="Distribuzione dei punti e assi LDA visibili nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Classificazione lineare, errore e figure di merito**:

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

Il codice è illustrativo e valuta il modello sugli stessi dati usati per addestrarlo: `resubPredict` misura l’errore **sui dati di addestramento** (stima ottimistica) e `confusionmat` riceve gli argomenti in **ordine inverso** rispetto a quello atteso.

#### **4.4. Dal confronto PCA/LDA alla classificazione con Iris**

La **PCA** trova gli assi di massima varianza usando le feature $X$; la **LDA** integra anche le etichette $y$ e cerca proiezioni che separino le classi. Per due classi, si calcolano le medie $\boldsymbol{\mu}_k$ e la dispersione intra-classe $S_W$, poi si trova la direzione di Fisher $\mathbf{w}=S_W^{-1}(\boldsymbol{\mu}_1-\boldsymbol{\mu}_0)$. Il campione si proietta con $z=\mathbf{w}^{\top}\mathbf{x}$ e si assegna la classe confrontando $z$ con una soglia $t$. Con $K$ classi si ottengono al più $K-1$ direzioni discriminanti.

<!-- SOURCE_VISUAL id="IS17-V31" source="L17-web.pdf" page="45" type="schema" description="PCA senza etichette e LDA supervisionata: input e obiettivi a confronto" reason="Confronto di assi e proiezioni visibile nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La dimostrazione geometrica usa **240 punti**, divisi in due gruppi di 120. Le classi si sovrappongono nelle due feature originali; la trasformazione $\mathbf{z}=W^{\top}\mathbf{x}$ le separa soprattutto lungo la prima direzione discriminante. Per $K$ classi, LDA può produrre al più $K-1$ direzioni discriminanti.

Nel dataset Iris, ogni fiore ha quattro feature in centimetri e appartiene a una delle tre specie. Un semplice controllo esplorativo mostra che i petali separano già bene *setosa*. Il caricamento restituisce una matrice $P$ di dimensione $150\times4$ e un vettore di etichette con 50 fiori per specie.

```python
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.metrics import confusion_matrix

iris = load_iris()
P, T = iris.data, iris.target
P_tr, P_va, T_tr, T_va = train_test_split(
    P, T, test_size=0.3, stratify=T, random_state=42
)
lda = LinearDiscriminantAnalysis()
lda.fit(P_tr, T_tr)
T_pred = lda.predict(P_va)
cm_train = confusion_matrix(T_tr, lda.predict(P_tr))
cm_validation = confusion_matrix(T_va, T_pred)
```

<!-- SOURCE_VISUAL id="IS17-V42" source="L17-web.pdf" page="50" type="grafico" description="Separazione esplorativa della specie Iris setosa usando lunghezza e larghezza del petalo" reason="Distribuzione delle classi visibile nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Per valutare il classificatore in modo realistico, si separano **105 campioni per il training** e **45 per la validation**, mantenendo 35 e 15 campioni per ciascuna classe (`stratify=T`, `random_state=42`). LDA viene addestrata solo sul training; le predizioni sui campioni di validation misurano esempi mai visti durante il fit.

Il classificatore riconosce correttamente 102 campioni su 105 nel training e 44 su 45 nella validation. La matrice di confusione della validation è la misura più informativa della generalizzazione; una singola divisione resta comunque soggetta a variabilità.

<!-- SOURCE_VISUAL id="IS17-V33" source="L17-web.pdf" page="52" type="matrici di confusione" description="Confronto tra matrici di confusione della classificazione Iris su training e validation" reason="Distribuzione degli errori nelle due partizioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Un nuovo modello: che cosa sapere e che cosa fare**

Modelli e tecniche di apprendimento evolvono rapidamente. Come tenere il passo? Una vignetta riassume il rischio con la frase: «**Usiamolo, non serve leggere la documentazione!**».

| **Che cosa sapere** | |
|---|---|
| **documentazione ed esempi** | consultarli **prima** di perdere tempo |
| **argomento, tipo, contesto** | capire a che cosa serve il modello |
| **librerie** | su quali librerie si basa? è **generale** o **specifico**? |
| **complessità computazionale** | in **addestramento** e in **test/rilascio**; serve **CUDA**? |
| **memoria** | in addestramento e in test |
| **dimensione dei dati** | servono 1000 campioni o 10 milioni? |
| **parametri** | sono complessi da capire? il modello è **sensibile alla loro regolazione**? basta un **fine-tuning** o bisogna **riaddestrare**? |
| **portabilità** | funziona su librerie e ambienti diversi? è **aperto, GNU, libero**, o lo gestisce **una sola azienda**? |

**Che cosa fare:** prova demo ed esempi della documentazione; confronta il modello su dataset standard diversi per accuratezza e tempi di calcolo; applica il rasoio di Occam e preferisci la soluzione più semplice a parità di prestazioni.

Il flusso di classificazione procede dalla raccolta e rappresentazione dei dati, alla scelta del modello, all’addestramento e validazione incrociata, fino alla valutazione e alla visualizzazione dei risultati.

<!-- SOURCE_VISUAL id="IS17-V44" source="L17-web.pdf" page="57" type="diagramma di flusso" description="Workflow per applicare un nuovo classificatore, dal caricamento dei dati alla valutazione" reason="Ordine delle fasi rappresentato come flusso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Il regressore lineare: un esempio del flusso completo**

#### **6.1. Regressione e classificazione lineare**

Un esempio semplice per **ripercorrere tutti i passi** del flusso di progetto.

> 📌 **Regressione lineare**. Trovare la **migliore funzione lineare** $y = f(x)$ che spiega i dati disponibili.

**Esempi**: età, sesso e dieta → **altezza** del bambino; tipo e voti del college → **reddito annuo**; numero di **ore di studio** → **voto finale** di questo esame.

**Estendibile a $N$ feature**. In forma vettoriale
$$z = \mathbf{w} \cdot \mathbf{x} + b$$
Con tre feature servono **4 parametri** ($w_1, w_2, w_3, b$), quindi occorrono almeno quattro osservazioni indipendenti per identificarli. In pratica si usano più dati per stimare i coefficienti in modo robusto. Per esempio, con $\mathbf{w}=(2,-1,0{,}5)$, $b=1$ e $\mathbf{x}=(1,3,4)$, l’uscita è $z=2-3+2+1=2$. Con $N$ feature servono $N+1$ coefficienti. Per $N=2$, $\mathbf{w}=(1,1)$, $b=-7$ e $\mathbf{x}=(5,5)$ producono $z=3$. È la **rappresentazione** del modello.

> 📌 **Dal regressore al classificatore lineare**. Trovare una funzione lineare che **separa le classi**:
> $$f_1(\mathbf{x}) = \mathbf{w} \cdot \mathbf{x} + b \;\;\text{(regressore lineare)} \qquad f_2(\mathbf{x}) = \operatorname{sgn}(\mathbf{w} \cdot \mathbf{x} + b) \;\;\text{(classificatore lineare)}$$
> Nel caso 2D il regressore è un **piano** sopra lo spazio delle feature; il classificatore usa solo il **segno**, e la frontiera è la **retta** dove $\mathbf{w} \cdot \mathbf{x} + b = 0$.

<!-- SOURCE_VISUAL id="IS17-V19" source="L17-web.pdf" page="63" type="schema" description="Uscita lineare continua del regressore e decisione binaria del classificatore" reason="Frontiere e valori della funzione segno visibili nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il valore predetto**:
$$\hat{y} = \hat{\beta}_0 + \sum_{j=1}^{p} X_j \hat{\beta}_j$$
Il vettore dei coefficienti $\hat{\boldsymbol{\beta}}$ **è** il modello di regressione. **Nota di calcolo**: aggiungendo agli input un termine costante $X_0 = 1$, la formula diventa un semplice **prodotto matriciale**, con un’algebra più facile da gestire:
$$\hat{\mathbf{y}} = \mathbf{X} \hat{\boldsymbol{\beta}}$$

**Notazione**. $\hat{y} = \hat{\beta}_0 + \sum_j X_j \hat{\beta}_j$ e $z = \mathbf{w} \cdot \mathbf{x} + b$ sono la stessa cosa: è anche l’**uscita lineare di un neurone**. I $w_j$ si chiamano **pesi** perché **moltiplicano** l’informazione in arrivo dagli input; $b$ si chiama **bias** perché è **sempre presente**, non modulato dall’input.

**Più feature**. Tutti i campioni di addestramento si scrivono in un’unica matrice $\mathbf{X}$ e un vettore $\mathbf{y} = (y_1, \dots, y_n)$. Esempio: età, sesso, dieta, … di ogni individuo → **glicemia**. Qui $\mathbf{y}$ **non contiene classi**: si sta costruendo un **regressore**, quindi $\mathbf{y}$ contiene **numeri**.

> ⚠️ **Righe e colonne (nota didattica).** Con $n$ campioni e $m$ feature, la matrice ha dimensione $\mathbf{X} \in \mathbb{R}^{n \times m}$: ogni **riga** è un campione e ogni **colonna** una feature.

#### **6.2. Minimi quadrati e gradiente**

> 📌 **Somma dei quadrati dei residui**. Per determinare i parametri $\hat{\boldsymbol{\beta}}$ si minimizza
> $$\text{RSS}(\boldsymbol{\beta}) = \sum_{i=1}^{N} (y_i - \mathbf{x}_i^{\top} \boldsymbol{\beta})^2 = (\mathbf{y} - \mathbf{X}\boldsymbol{\beta})^{\top} (\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$$
> Derivando rispetto a $\boldsymbol{\beta}$ e ponendo la derivata a zero si ottiene
> $$\mathbf{X}^{\top} (\mathbf{y} - \mathbf{X}\boldsymbol{\beta}) = 0$$
> e, se $\mathbf{X}^{\top}\mathbf{X}$ è **non singolare**, la soluzione unica è
> $$\hat{\boldsymbol{\beta}} = (\mathbf{X}^{\top}\mathbf{X})^{-1} \mathbf{X}^{\top} \mathbf{y}$$
> È l’**ottimizzazione** del modello.

**Soluzioni iterative**. Il metodo esatto richiede di **invertire** $\mathbf{X}^{\top}\mathbf{X}$, che spesso è **troppo grande**. Molti metodi **basati sul gradiente** riducono l’RSS usando la sua derivata rispetto a $\boldsymbol{\beta}$, descritta da
$$\nabla = \mathbf{X}^{\top} (\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$$
**Nota importante**: molti metodi neurali, anche nel deep learning, usano metodi basati sul gradiente; se il gradiente tende a zero, il metodo «**si blocca**».

> 💡 **Segno e fattore del gradiente (nota didattica).** La derivata esatta dell’RSS è $\nabla_{\boldsymbol{\beta}} \text{RSS} = -2\,\mathbf{X}^{\top}(\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$. La direzione di aggiornamento usata è proporzionale alla derivata esatta, a meno del fattore $-2$: indica già la **direzione in cui muoversi** per ridurre l’errore. L’aggiornamento è quindi
> $$\boldsymbol{\beta} \leftarrow \boldsymbol{\beta} + \eta\, \mathbf{X}^{\top}(\mathbf{y} - \mathbf{X}\boldsymbol{\beta})$$
> con un passo $\eta$ piccolo (il fattore 2 si assorbe in $\eta$).

> 📌 **Gradiente, gradiente, gradiente! (d’esame)**.
> - Gran parte del ML consiste nel **trovare i parametri** (i pesi, qui $\boldsymbol{\beta}$).
> - $\boldsymbol{\beta}$ si trova **attraverso il gradiente**: gradiente → aggiornamento → nuovo gradiente → nuovo aggiornamento → …

**Passi numerici.** Con due osservazioni e una colonna d’intercetta, si parte da $\beta^{(0)}=[0,0]^T$ e si usa $\eta=0{,}1$. Si ottengono $\nabla^{(0)}=[-8,-13]^T$, $\beta^{(1)}=[0{,}8,1{,}3]^T$, $\nabla^{(1)}=[-2{,}5,-4{,}1]^T$ e $\beta^{(2)}=[1{,}05,1{,}71]^T$, che si avvicina alla soluzione esatta $[1,2]^T$.

**Dipendenza del gradiente**. A ogni iterazione il gradiente dipende dai **dati** $\mathbf{X}$ e dai **parametri correnti** $\boldsymbol{\beta}$, e quindi, **implicitamente**, da tutti i passi precedenti che hanno portato $\boldsymbol{\beta}$ fin lì. Questa dipendenza ricorsiva **guida il percorso** dell’ottimizzazione: un percorso **insidioso**.

> 💡 **Perché «insidioso» (nota didattica).** Per la regressione lineare l’RSS è una funzione **convessa**, con un solo minimo: qualsiasi percorso ben regolato ci arriva. Nelle reti neurali la funzione di errore **non è convessa**: il punto di partenza e i passi precedenti decidono in quale minimo locale, o in quale zona piatta dove il gradiente è quasi nullo, l’ottimizzazione si fermerà (reti neurali).

#### **6.3. La regressione è buona? L’$R^2$**

Si può sempre adattare un modello lineare a qualsiasi dataset: come sapere se esiste davvero una **relazione lineare**?

> ⚠️ **Il quartetto di Anscombe**. Quattro dataset con statistiche descrittive **quasi identiche** e lo stesso $R^2 = 0{,}67$, ma forme completamente diverse. Le cause possibili di un fit ingannevole: **dispersione o rumore**, **modello sbagliato**, **outlier**, **errori**. Con più di 2 dimensioni è **difficile capire visivamente** la qualità del fit.

> 💡 **I quattro casi (nota didattica).** Nel quartetto di Anscombe i dataset condividono quasi la stessa media, varianza, retta $y \approx 3 + 0{,}5x$, correlazione $r \approx 0{,}816$ e $R^2 \approx 0{,}67$. La distribuzione grafica rivela però quattro strutture: andamento lineare con rumore, curva, retta con un outlier e punti quasi tutti sulla stessa ascissa. Statistiche descrittive e $R^2$ vanno quindi accompagnati dall’ispezione dei dati.

<!-- SOURCE_VISUAL id="IS17-V20" source="L17-web.pdf" page="68" type="grafici" description="Quartetto di Anscombe: quattro dataset con statistiche simili ma forme diverse" reason="Grafici dei quattro dataset visibili nella tavola" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **R-quadro**. Con $\hat{y}_i$ il valore predetto e $\bar{y}$ la media del campione:
> $$R^2 = 1 - \frac{\sum_{i} (y_i - \hat{y}_i)^2}{\sum_{i} (y_i - \bar{y})^2}$$
> Al numeratore la **somma dei quadrati dei residui** (distanze dalla retta dei $\hat{y}$), al denominatore la **somma totale dei quadrati** (distanze dalla retta della media $\bar{y}$, proporzionale alla varianza dei dati). $R^2 \to 1$ indica un **buon fit**. **L’$R^2$ va sempre riportato**, ed è particolarmente importante con più di 2 dimensioni, quando il fit non si può vedere.

> ⚠️ **Interpretazione di $R^2$.** La frazione di varianza spiegata dal modello è $R^2$; il rapporto $\sum (y_i - \hat{y}_i)^2 / \sum (y_i - \bar{y})^2$ è la frazione **non spiegata**, e $R^2 = 1 -$ quel rapporto è la frazione **spiegata** 

<!-- SOURCE_VISUAL id="IS17-V21" source="L17-web.pdf" page="68" type="grafico" description="R-quadro come confronto tra residui del modello e dispersione rispetto alla media" reason="Confronto geometrico mostrato nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Laboratorio: regressore lineare e prime reti feed-forward**

#### **7.1. MATLAB: il regressore lineare**

Su una stessa generazione di 51 punti rumorosi, la tabella comparativa riporta per il regressore lineare **MSE = 0,76** e **$R^2=0,94$**; risultati numerici variano con la realizzazione del rumore e con il campionamento.

**Script MATLAB:** eseguire lo script [Lesson_16_laboratory_MATLAB_linear_and_neural.m](Lesson_16_laboratory_MATLAB_linear_and_neural.m). Partendo dalla regressione si addestreranno e testeranno le **prime reti neurali**.

**Creare i dati**: **51 punti** della retta $t = 2x - 5$ con **rumore gaussiano**.

```matlab
x = [0:0.2:10];                 % 51 punti da 0 a 10
noise = randn(size(x));
t = 2 * x - 5 + noise;
plot(x,t, 'o');
title('Linear model plus noise');
```

**Due metodi per creare e addestrare il regressore**:

| Metodo | Istruzioni | Caratteristiche |
|---|---|---|
| **1 (diretto)** | `net1 = newlind(x,t)` | soluzione **ottimizzata** basata sull’**inversione** della matrice dei dati, cioè i minimi quadrati del §6.2 |
| **2** | `net1 = linearlayer; net1 = configure(net1,[0],0);` pesi iniziali `net1.IW{1,1} = 3.1; net1.b{1} = -3.1;` poi `[net1, tr] = train(net1,x,t)` | inizializzazione come **rete lineare** (1 input, 1 strato) e addestramento con il metodo generale delle reti non lineari (l’addestramento utilizza **Levenberg–Marquardt**): **generale, ma non ottimizzato**; lo script segnala che questa modalità non crea partizioni di training e validation. La valutazione sui dati d’addestramento resta quindi ottimistica e non misura la generalizzazione |

Nello script il metodo 2 si trova nel ramo `else` di un `if (1)`, quindi viene eseguito sempre il metodo 1.

**Il regressore creato**: $\text{output} = w \cdot \text{input} + b$. **Accedere ai parametri**:

```matlab
view(net1)
W = net1.IW{1,1};
b = net1.b{1};
fprintf('trained linear model W = %f \n', W);
fprintf('trained linear model b = %f \n', b);
```

**Elaborare gli input e disegnare i risultati**: `y1 = net1(x)` simula il modello sugli input, e la retta si sovrappone ai dati.

<!-- SOURCE_VISUAL id="IS17-V22" source="L17-web.pdf" page="73" type="grafico" description="51 punti della retta t = 2x - 5 con rumore gaussiano" reason="Punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V23" source="L17-web.pdf" page="74" type="schermata" description="Creazione del regressore come rete lineare con addestramento generale" reason="Schermata dell’addestramento visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V24" source="L17-web.pdf" page="77" type="grafico" description="Retta del regressore lineare sovrapposta ai dati" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Valutazione dell’accuratezza**:

```matlab
y1 = net1(x);                      % simulate the inputs
perf = perform(net1,y1,t)          % Error on **training**
net1.performFcn                    % the error metric (MSE for linear)
mse_check = sum((y1 - t).^2 ) / size(t,2);
mean_t = mean(t);
R2 = 1 - ( sum((t - y1).^2 ) / sum((t - mean_t).^2) );
```

Il confronto numerico tra modelli, eseguito con una replica Python su punti di test nuovi, riporta MSE 0,91/1,02 per il regressore lineare, 0,76/1,16 per la FFNN con 3 neuroni e 0,20/1,66 per quella con 100 neuroni, rispettivamente su training e test.

Il confronto numerico, ottenuto con una replica Python su campioni di test nuovi, riporta MSE 0,91/1,02 per il regressore lineare, 0,76/1,16 per la FFNN con 3 neuroni e 0,20/1,66 per quella con 100 neuroni, rispettivamente su training e test.

> ⚠️ **Le misure sul training non stimano la generalizzazione.** La rete da 100 neuroni riduce molto l’errore di training e aumenta quello di test: un segnale netto di overfitting.

#### **7.2. MATLAB: reti feed-forward e overfitting**

**Proviamo una rete feed-forward!**. Lo script richiama esplicitamente il **rasoio di Occam**:

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

Si **forza** l’uso di **tutti i dati nel training set**. La verifica consiste nel chiedersi se la rete stia **adattando il rumore**. Con 3 neuroni la risposta è **negativa**: la curva segue l’andamento lineare.

<!-- SOURCE_VISUAL id="IS17-V25" source="L17-web.pdf" page="80" type="grafico" description="Andamento del MSE di addestramento della rete con 3 neuroni e tutti i dati nel training" reason="Curva visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V26" source="L17-web.pdf" page="80" type="grafico" description="Uscita della rete con 3 neuroni sui dati: nessun adattamento al rumore" reason="Curva e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Progetto sbagliato: 100 neuroni con 51 punti!**, ancora **tutto in training**.

```matlab
net3 = feedforwardnet(100);
net3.divideParam.trainRatio = 1.0;
net3.divideParam.testRatio  = 0.0;
net3.divideParam.valRatio   = 0;
[net3, tr] = train(net3,x,t);
y3 = net3(x);
```

**Valutiamo l’uscita anche tra i campioni di training**: si genera una griglia fitta di valori $x$ per visualizzare l’andamento della rete. Questa griglia mostra le oscillazioni, mentre la stima quantitativa sul test riportata di seguito proviene da una replica Python.

```matlab
x_bis = [0:0.0005:10];     % 20 001 punti
y3_bis = net3(x_bis);
plot(x_bis, y3_bis, '.k');
```

**…e abbiamo fatto overfitting (moltissimo!).**

> 📌 **Da ricordare**. Con **#DoF ≫ #campioni**, il modello può raggiungere un fit quasi perfetto sul training e generalizzare male.

> 💡 **I conti dei parametri (nota didattica).** Con 1 input e 1 uscita, una rete con $h$ neuroni nascosti ha $(1 + 1)\,h + (h + 1) = 3h + 1$ parametri (conteggio dei parametri):
> - `feedforwardnet(3)`: $3 \cdot 3 + 1 = 10$ parametri per 51 punti → $\text{DoF} \approx 10 - 51 < 0$;
> - `feedforwardnet(100)`: $3 \cdot 100 + 1 = 301$ parametri per 51 punti → $\text{DoF} \approx 301 - 51 = 250 > 0$.
>
> Con 301 parametri la rete può passare esattamente per tutti i 51 punti rumorosi e, tra un punto e l’altro, fare qualsiasi cosa: è ciò che mostrano i 20 001 punti di `x_bis`. La retta vera ha solo **2** parametri.

<!-- SOURCE_VISUAL id="IS17-V27" source="L17-web.pdf" page="81" type="grafico" description="Rete con 100 neuroni addestrata su 51 punti: fit perfetto sul training" reason="Punti e uscita visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V28" source="L17-web.pdf" page="82" type="grafico" description="Rete con 100 neuroni valutata su 20 001 punti: forte overfitting tra i punti di training" reason="Oscillazioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V29" source="L17-web.pdf" page="83" type="grafico" description="Addestramento perfetto e generalizzazione pessima con #DoF molto maggiore dei campioni" reason="Confronto visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3. Python: il regressore lineare in Colab**

**Il file Python in Colab**: eseguire il notebook [Lesson_16_laboratory_COLAB_linear_regressors.ipynb](Lesson_16_laboratory_COLAB_linear_regressors.ipynb), che mostra come creare un regressore, controllarne l’accuratezza e disegnare i grafici.

**Librerie e dati**:

```python
import matplotlib.pyplot as plt
import numpy as np

rng = np.random.RandomState(1)
num_points = 50
x = 10 * rng.rand(num_points)
t = 2 * x - 5 + rng.randn(num_points)   # retta con pendenza 2 e intercetta -5, più rumore
plt.scatter(x, t);
```

**Creazione del regressore lineare**, con lo stimatore `LinearRegression` di scikit-learn:

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

`x[:, np.newaxis]` trasforma il vettore di 50 elementi in una **matrice** $50 \times 1$: scikit-learn si aspetta sempre una matrice con **una riga per campione** e **una colonna per feature**. Nel frammento di esempio va completata la parentesi di chiusura della chiamata a `predict`.

**Parametri del modello**:

```python
print("Model slope:    ", model.coef_[0])     # 2.0272088103606953
print("Model intercept:", model.intercept_)    # -4.998577085553204
```

Pendenza **2,027** e intercetta **−4,999**, molto vicine ai valori veri 2 e −5.

**Valutazione dell’accuratezza**:

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

> ⚠️ **ATTENZIONE**. Bisogna creare un **TEST SET** per ottenere la vera accuratezza di generalizzazione: questi valori sono calcolati sul **TRAINING SET**. I numeri sono **diversi da MATLAB** per la diversa inizializzazione dei dati (50 punti casuali con seme fisso in Python, 51 punti equispaziati con rumore non riproducibile in MATLAB).

> ⚠️ **Ordine degli argomenti di `r2_score` (nota didattica).** La firma è `r2_score(y_true, y_pred)`: il notebook passa `r2_score(y1, t)`, cioè le predizioni al posto dei valori veri. Il MSE è simmetrico e non cambia, ma l’$R^2$ **no**, perché il denominatore usa la varianza del primo argomento: la forma corretta è `r2_score(t, y1)`. Anche la cella di testo che precede usa per errore la sintassi di MATLAB (`.^`, `size(t,2)`).

<!-- SOURCE_VISUAL id="IS17-V30" source="L17-web.pdf" page="86" type="grafico" description="Retta di regressione di scikit-learn sovrapposta ai 50 punti" reason="Grafico visibile solo nella slide" -->
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

> ✅ **Punti principali**.
> - **Memoria degli agenti e gestione del contesto**: il contesto si ricostruisce a ogni turno, cresce e degrada (context rot); offloading, RAG, selezione degli strumenti, pruning, riassunto e quarantena; riassunti di passaggio, `/compact`, `/clear`, sotto-agenti. Le decisioni critiche restano da validare a una persona.
> - **Caricamento, EDA e grafici in MATLAB**: `plotmatrix`, `gscatter`, statistiche per colonna, setosa separata dal petalo, LDA come feature engineering con le etichette.
> - **Nuovo modello?** Leggere la documentazione, conoscerne complessità, memoria, dati e parametri, provarlo su dataset standard confrontando accuratezza e tempi, e applicare il rasoio di Occam.
> - **Il dilemma bias-varianza**: il training migliora con la complessità, mentre la validazione può peggiorare; media e deviazione standard della k-FCV descrivono errore e variabilità.
> - **Il primo modello completo**: regressore lineare con minimi quadrati, gradiente ed $R^2$ (da riportare sempre, e da non fidarsi senza guardare i dati).
> - **Prime reti neurali**: `newlind` e `feedforwardnet(3)` corretti, `feedforwardnet(100)` su 51 punti con training perfetto e generalizzazione pessima.
> - **Valutazione dell’accuratezza**: le misure sul training vanno sempre ripetute su un test set.

---

### **10. Approfondimento: scomposizione bias-varianza**

Sia $y=f(x)+\varepsilon$, con rumore a media zero e varianza $\sigma^2$, e sia $\hat y$ la predizione di un modello addestrato su un dataset casuale $D$. Mediando sul rumore e sulle possibili partizioni si ottiene

$$\mathbb{E}_{D,\varepsilon}[(y-\hat y)^2] = \sigma^2 + \bigl(\mathbb{E}_D[\hat y]-f(x)\bigr)^2 + \mathbb{E}_D\left[(\hat y-\mathbb{E}_D[\hat y])^2\right]$$

I tre termini sono rispettivamente **rumore irriducibile**, **bias al quadrato** e **varianza**. La cross-validation ripete l’addestramento su partizioni diverse e permette di osservare la media e la dispersione degli errori come stime pratiche della prestazione attesa.
