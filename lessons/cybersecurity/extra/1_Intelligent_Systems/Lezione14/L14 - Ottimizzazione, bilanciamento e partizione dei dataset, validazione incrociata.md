## **Lezione 14: Ottimizzazione, bilanciamento e partizione dei dataset, validazione incrociata**

La quattordicesima lezione del corso **IS4** affronta la parte del lavoro che, secondo il docente, decide **la qualità di un progetto**: come si usano i dati per **addestrare** e per **valutare** un modello senza ingannarsi. Dopo un richiamo a rappresentazione, valutazione e ottimizzazione, la lezione tratta i **dataset sbilanciati** (più dati, etichette diverse, campionamento con SMOTE e dataset condensati, pesi nella funzione di perdita). Poi spiega perché una **partizione sbagliata** produce stime ottimistiche e nasconde underfitting e overfitting, e presenta gli schemi di validazione: **training/validation/test**, **k-fold**, **5×2**, **leave-one-out**, **leave-one-person-out**, **Monte Carlo** e le validazioni per **serie temporali**. Due regole attraversano tutta la lezione: **non si progetta mai sul test set**, e **un errore dell’1% può essere buono o cattivo**.

Fonte: [PDF della Lezione 14](Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf), 80 pagine, nessun supplemento. La valutazione dell’accuratezza di regressori e classificatori è annunciata per la Lezione 15. Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *Workflow: optimization. Dataset balancing. Dataset Partitioning. Accuracy assessment.* L’indice (p. 2) elenca:

1. **flusso di lavoro: ottimizzazione** (Lezione 14);
2. **partizione del dataset** (Lezione 14);
3. **valutazione dell’accuratezza** di regressori e classificatori (Lezione 15).

Il docente sottolinea che questi argomenti sono **molto importanti per la qualità del lavoro**.

---

### **1. Richiamo: rappresentazione, valutazione, ottimizzazione**

_(slide pp. 3–12)_

#### **1.1. I tre componenti nel flusso di lavoro**

Ogni algoritmo di ML ha **tre componenti** (p. 4; Lezione 04, §4.4), che corrispondono a passi precisi del flusso di lavoro:

| Componente | Passi del flusso |
|---|---|
| **rappresentazione** | #3 (modello) |
| **valutazione** | #3 e #4 (modello e test) |
| **ottimizzazione** | #3 e #5 (modello e miglioramento del progetto) |

È un **quadro** che vale per tutti i sistemi intelligenti, dai modelli classici al deep learning.

**Rappresentazione** (p. 5): $Y = \text{function}(X, \text{params})$. Le famiglie sono le solite (reti neurali, alberi di decisione, regole, istanze, modelli grafici, SVM, ensemble); lo schema vale per un modello **generale**, anche per un «nuovo incredibile modello».

<!-- SOURCE_VISUAL id="IS14-V01" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="5" type="schema" description="Rappresentazione come Y = function(X, params) per diversi modelli" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Valutazione** (pp. 6–8): accuratezza, precisione, recall e generalizzazione, errore quadratico, verosimiglianza, probabilità a posteriori, entropia, costo e utilità, divergenza di Kullback-Leibler, margine.

> 📌 **L’obiettivo vero dell’addestramento** (p. 6). Non è solo **ridurre le classificazioni sbagliate**, ma **affinare la stima delle probabilità a posteriori** $P(c \mid x)$ del modello: sono il **vero fondamento** delle decisioni intelligenti.

La p. 7 illustra l’**entropia** bassa e alta delle uscite e un vettore di **errori pesati** sulle uscite, del tipo [ok ok ok SBAGLIATO SBAGLIATO ok ok]; la p. 8 ripete il confronto **accuratezza contro margine** ($H_2$ e $H_3$ entrambe al 100%, si preferisce $H_3$ per il margine maggiore; Lezione 04, §4.4).

> 💡 **Perché le probabilità contano più delle etichette (nota didattica).** Due modelli possono sbagliare gli stessi esempi, ma uno dà 0,51 alla classe giusta e l’altro 0,99. Il secondo è molto più utile: permette di **cambiare la soglia** in base ai costi degli errori (§2.2), di **astenersi** quando è incerto (Lezione 05, §1.6) e di combinare il modello con altre informazioni. Una perdita come la cross-entropy premia proprio le probabilità ben stimate.

<!-- SOURCE_VISUAL id="IS14-V02" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="7" type="schema" description="Entropia bassa e alta e vettore degli errori pesati sulle uscite" reason="Formule e schema resi come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Ottimizzazione** (p. 9): **combinatoria** (ricerca greedy), **convessa** (discesa del gradiente), **vincolata** (programmazione lineare).

#### **1.2. Notazione e visualizzazione di regressori e classificatori**

**Notazione** (p. 10). $Y = \text{function}(X, \text{params})$, dove $X$ è il **vettore delle feature** ($x_1, x_2, \dots$), l’uscita $y$ può essere un **singolo valore** o un **vettore** (più uscite), e anche i parametri formano un vettore.

| | **Regressione** | **Classificazione** |
|---|---|---|
| Rappresentazione di $y$ | **esplicita**: $y$ è un asse del grafico, una superficie sopra il piano $(x_1, x_2)$ | **implicita**: $y$ è il **colore** o il simbolo dei punti nel piano $(x_1, x_2)$, per esempio «cattivo» ($y = 1$) e «buono» ($y = 0$) |
| Visualizzazione (pp. 11–12) | da 3D a 1D con **selezione o ingegneria delle feature** ($X \to X'$) | stessa esigenza: ridurre le feature per vedere il comportamento del modello |

Per ottenere intuizioni utili e **percepire il comportamento** del modello servono **ingegneria delle feature** e le **migliori tecniche di visualizzazione** (Lezione 11).

<!-- SOURCE_VISUAL id="IS14-V03" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="10" type="schema" description="Notazione: regressione con y esplicita e classificazione con y implicita" reason="Grafici della notazione visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V04" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="11" type="grafico" description="Visualizzazione di un regressore con riduzione da 3D a 1D" reason="Superficie e riduzione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V05" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="12" type="grafico" description="Visualizzazione di un classificatore con classi buono e cattivo nel piano delle feature" reason="Punti e frontiera visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Bilanciamento dei dataset**

_(slide pp. 13–25)_

#### **2.1. Dataset accademici e dataset reali**

Il tema (p. 13): come gestire **distribuzioni sbilanciate** delle classi.

**Nel mondo accademico** i dataset sono per lo più **bilanciati** (p. 14): nei problemi di classificazione supervisionata ci sono di solito **tanti campioni per classe**.

- **MNIST**, il dataset di cifre scritte a mano, contiene **circa lo stesso numero** di campioni per ogni cifra;
- **Food-101** contiene **esattamente 1000 immagini** per ciascuna delle **101 categorie** di cibo.

**Nel mondo reale** i dataset sono per lo più **sbilanciati** (p. 15):

- **visione medica**: la maggior parte dei pazienti è **sana**, solo una piccola frazione ha una certa malattia;
- **credit scoring**: la maggior parte dei clienti **restituisce il prestito**, solo l’**1–2%** è **insolvente**.

(Le grafiche delle slide sono di Rasmus Rothe.)

> ⚠️ **L’accuratezza inganna con classi sbilanciate (nota didattica).** Con il 2% di insolventi, un classificatore che risponde **sempre** «restituirà il prestito» ha **accuratezza del 98%** e non trova **nessun** insolvente: recall zero sulla classe che interessa. È il motivo per cui la sintesi finale dice che «**un errore dell’1% può essere buono o cattivo**» (§9): dipende dalla distribuzione delle classi e dal costo degli errori.

<!-- SOURCE_VISUAL id="IS14-V06" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="14" type="grafico" description="Distribuzioni bilanciate nei dataset accademici MNIST e Food-101" reason="Istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V07" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="15" type="grafico" description="Distribuzioni sbilanciate nei dataset reali: visione medica e credit scoring" reason="Istogrammi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Costo degli errori non uniforme** (p. 17). A differenza di molti dataset accademici, in molti dataset reali sbilanciati **non è corretto** usare lo **stesso costo di errore** per ogni classe: non riconoscere un malato costa molto più che richiamare un sano per un secondo esame.

<!-- SOURCE_VISUAL id="IS14-V08" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="17" type="schema" description="Costi di errore diversi per le classi in un dataset sbilanciato" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. Quattro strategie complementari**

> 📌 **Gestire lo sbilanciamento: quattro strategie** (p. 16). Ognuna agisce su un livello diverso della pipeline di ML (dati, modello, addestramento), e si possono **applicare da sole o combinate**:
> 1. **più dati**: raccogliere altri campioni per le classi **sotto-rappresentate**;
> 2. **cambiare le etichette**: ridefinire, unire o organizzare **gerarchicamente** le classi;
> 3. **campionamento**: modificare la distribuzione delle classi con **sovracampionamento** o **sottocampionamento**;
> 4. **pesatura della perdita**: penalizzare **di più** gli errori sulle classi **rare** durante l’addestramento.

**Soluzione 1: più dati** (p. 18). Raccogliere più dati o immagini per le classi rare, e **controllare le distribuzioni** di feature **ed** etichette **sia nel training sia in validation e test**.

**Soluzione 2: cambiare le etichette** (p. 19). **Raggruppare e unire** i casi rari; *divide et impera*: **classificatori gerarchici**, in cui un primo classificatore separa i grandi gruppi e altri classificatori lavorano dentro ciascun gruppo.

<!-- SOURCE_VISUAL id="IS14-V09" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="19" type="diagramma" description="Classificatore gerarchico con un primo classificatore che raggruppa i casi rari" reason="Gerarchia visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Soluzione 3: campionamento** (p. 20). **Non usare** metodi di campionamento **uniformi**. Nel sovracampionamento il vantaggio rispetto al sottocampionamento è che **nessun campione viene ignorato**; nella slide l’esempio è «chiedere agli **stessi pazienti** di **ripetere gli esami**», cioè ottenere più campioni della classe rara.

<!-- SOURCE_VISUAL id="IS14-V10" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="20" type="grafico" description="Sovracampionamento e sottocampionamento delle classi" reason="Distribuzioni prima e dopo visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **SMOTE** (*Synthetic Minority Over-sampling Technique*, p. 21). Tecnica di campionamento a livello di dati che affronta lo sbilanciamento **generando campioni sintetici** della classe minoritaria, **invece di duplicare** quelli esistenti. Per ogni campione minoritario $x$:
> - si trovano i suoi $k$ **vicini più prossimi** della classe minoritaria;
> - si genera un nuovo campione **sul segmento** tra $x$ e uno dei vicini.
>
> In formula (nota didattica), con $x_{nn}$ il vicino scelto a caso e $\lambda$ estratto uniformemente in $[0, 1]$:
> $$x_{\text{nuovo}} = x + \lambda\, (x_{nn} - x)$$
> Così aumenta la **densità** della classe minoritaria nello spazio delle feature.

> ⚠️ **Limiti di SMOTE (nota didattica).** I campioni sintetici stanno sempre **tra** campioni esistenti: non creano varietà nuova, e se un vicino è rumoroso o si trova vicino all’altra classe si generano punti nella regione sbagliata. Va applicato **solo al training set**: generare sintetici prima di dividere i dati li farebbe finire anche nel test, un caso di data leakage (Lezione 07, §3).

<!-- SOURCE_VISUAL id="IS14-V11" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="21" type="grafico" description="SMOTE: campioni sintetici generati sul segmento tra un campione minoritario e un vicino" reason="Punti sintetici visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sottocampionamento con dataset condensato** (pp. 22–23). Riduce lo sbilanciamento **eliminando i campioni ridondanti** della classe maggioritaria, conservando i punti **più informativi**:

- la classe maggioritaria contiene spesso molti campioni **ridondanti e poco informativi**;
- solo i campioni **vicini alla frontiera di decisione** sono critici;
- un dataset condensato conserva **tutti** i campioni minoritari e un **sottoinsieme scelto con cura** di quelli maggioritari (la slide lo confronta con una **selezione casuale** nella classe maggioritaria);
- anche se molti campioni vengono rimossi, le **relazioni di vicinato locali** che definiscono la frontiera del **k-NN** sono **conservate**, e la superficie di decisione **non cambia in modo significativo**.

> 💡 **Condensed Nearest Neighbor (nota didattica).** L’algoritmo classico di Hart (1968) parte da un insieme ridotto e aggiunge un campione solo se il classificatore 1-NN costruito sull’insieme corrente lo **classifica male**. I campioni «facili», lontani dalla frontiera, non vengono aggiunti perché già classificati bene dai loro vicini.

<!-- SOURCE_VISUAL id="IS14-V12" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="22" type="grafico" description="Dataset condensato: tutti i minoritari e i maggioritari vicini alla frontiera contro selezione casuale" reason="Punti conservati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V13" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="23" type="grafico" description="Frontiera k-NN conservata dopo la condensazione del dataset" reason="Frontiera prima e dopo visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Soluzione 4: pesatura della perdita** (p. 24). **Aumentare il peso** della perdita dei campioni delle **classi rare**.

> 📌 **Cross-entropy pesata (nota didattica).** Con $n$ campioni, classe vera $y_i$ e probabilità predetta $\hat{p}_{i, y_i}$:
> $$L = -\frac{1}{n} \sum_{i=1}^{n} w_{y_i} \log \hat{p}_{i, y_i} \qquad w_c = \frac{n}{C \cdot n_c}$$
> dove $n_c$ è il numero di campioni della classe $c$ e $C$ il numero di classi. Con 980 clienti solventi e 20 insolventi: $w_{\text{solv}} = 1000/(2 \cdot 980) \approx 0{,}51$ e $w_{\text{insolv}} = 1000/(2 \cdot 20) = 25$. Sbagliare un insolvente «costa» circa **49 volte** più che sbagliare un solvente.

**Strategie e rischi** (p. 25):

| Strategia | Agisce su | Rischio tipico |
|---|---|---|
| **più dati** | dataset | **costo** e **fattibilità** |
| **cambiare le etichette** | definizione del problema | **perdita di granularità** |
| **campionamento** | dataset | **overfitting** (sovracampionamento) o **perdita di informazione** (sottocampionamento) |
| **pesatura della perdita** | modello | **instabilità** dell’addestramento |

In pratica le strategie **si combinano spesso**, in base a disponibilità dei dati, vincoli applicativi e stabilità dell’apprendimento.

---

### **3. Partizione del dataset: che cosa può andare storto**

_(slide pp. 26–39)_

#### **3.1. Dati noti e stime ingannevoli**

I dataset con coppie **note** $X \to Y$ (p. 27) servono a **valutare le prestazioni** di un modello su problemi standard. Ma i dati vanno **usati correttamente**, per **evitare bias** nei risultati.

> 📌 **Problema 1: bias ottimistico** (p. 28). Si verifica quando la valutazione è fatta su dati che hanno **influenzato il progetto o la scelta** del modello, portando a una stima **troppo ottimistica** del vero errore di generalizzazione. Se si usa **tutto il dataset** sia per costruire sia per validare il modello, **non si ha idea** di come si comporterà con casi **nuovi**. È il tipico bias ottimistico (fonte: *Data Preprocessing in Data Mining*, Springer).

> 📌 **Problema 2: underfitting e overfitting non rilevati** (p. 29). Usando gli stessi dati per addestrare e valutare, possono sorgere due problemi **senza che ce ne si accorga**:
> - **underfitting**: il modello è **poco adattato** ai dati, con **errore alto** sia in addestramento sia sui dati nuovi;
> - **overfitting**: il modello è **troppo adattato** ai dati, con **alta precisione** sui casi noti ma **cattivo comportamento** sui dati nuovi.
>
> Una partizione corretta **non risolve automaticamente** questi problemi, ma **aiuta a rilevarli** e a decidere come intervenire.

**Che cosa si può e non si può fare** (p. 30). L’obiettivo finale è applicare il sistema a **problemi reali**, ma provare una tecnica su **ogni** problema è impossibile: la procedura comune è valutarla su un insieme di **problemi e dataset standard** pubblici.

> ⚠️ **Un avvertimento critico** (p. 31). Per **controllare** le prestazioni, **evitare l’overfitting** e avere una stima **generalizzabile** della qualità del modello, in letteratura esistono vari schemi di partizione. **Come** si partizionano i dati **influenza moltissimo** le prestazioni misurate: una partizione sbagliata porta **sicuramente** a un comportamento **incompleto o distorto** del modello valutato.

<!-- SOURCE_VISUAL id="IS14-V14" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="32" type="immagine" description="Focus sulla partizione del dataset" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Overfitting e underfitting in pratica**

_(slide pp. 33–39)_

**Richiamo: troppi parametri** (p. 33; Lezione 07, §2.5). Se il metodo di apprendimento non riesce a gestire tutti i parametri inseriti con il dataset disponibile, il modello non è ottimale.

Le pp. 34–36 mostrano l’**overfitting in 1D** (un regressore che passa per tutti i punti oscillando) e **in 2D** (un classificatore con una frontiera tortuosa che circonda ogni punto).

<!-- SOURCE_VISUAL id="IS14-V15" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="34" type="grafico" description="Overfitting in 1D con un modello di regressione" reason="Curva e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V16" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="35" type="grafico" description="Overfitting in 2D con un modello di classificazione" reason="Frontiera e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V17" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="36" type="grafico" description="Overfitting in 2D, secondo esempio" reason="Frontiera e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I casi possibili** (pp. 37–38):

- l’**underfitting** si riconosce quando il modello ha **errore alto anche sui dati di addestramento**; usando tutti i dati ci si accorge dell’underfitting dalle prestazioni basse;
- anche la **mancanza di dati** causa underfitting: il problema **sembra più semplice** di quanto sia davvero;
- **adattare** il modello per seguire meglio i dati può portare all’**overfitting**, e **senza casi nuovi** è **impossibile accorgersene**;
- l’overfitting può nascere anche da altre cause, come il **rumore**: si sta adattando il modello al «**rumore nel segnale**», non ai dati rilevanti.

**Con input ad alta dimensionalità** (p. 39) lo spazio non si può disegnare facilmente: si studiano **gli errori** per rilevare l’overfitting. Nell’esperimento della slide si **ripete l’addestramento aumentando il numero di gradi di libertà** del modello (per esempio aggiungendo neuroni). L’asse orizzontale è il **#DoF**, **non il tempo né le epoche**.

> 📌 **Le due curve (nota didattica).** Al crescere dei gradi di libertà l’**errore di training** scende quasi sempre, mentre l’**errore di test** prima scende e poi **risale**:
> - a sinistra del minimo dell’errore di test il modello è in **underfitting** (entrambi gli errori alti);
> - a destra è in **overfitting** (training basso, test in crescita);
> - il **numero di parametri giusto** è vicino al minimo dell’errore di test.
>
> Senza un insieme di test separato si vedrebbe solo la curva di training, che suggerisce sempre di aumentare la complessità.

<!-- SOURCE_VISUAL id="IS14-V18" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="39" type="grafico" description="Errori di training e di test al crescere dei gradi di libertà del modello" reason="Andamento delle curve visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Training, validation e test**

_(slide pp. 40–47)_

#### **4.1. Il dilemma della divisione**

**Dividere i dati tra training e test** (p. 40) è un problema e una decisione **molto difficili**. L’immagine è il **giudizio di Salomone**: nella Bibbia ebraica il re Salomone deve decidere tra due donne che si dicono madri dello stesso bambino, e ordina di tagliarlo a metà. Anche il dataset va «tagliato», e ogni parte serve a qualcosa.

<!-- SOURCE_VISUAL id="IS14-V19" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="40" type="immagine" description="Il giudizio di Salomone come metafora della divisione tra training e test" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il dilemma** (pp. 41–43). I dati di test devono restare **materiale nuovo** per il modello.

| | **Molto training, poco test** (p. 41) | **Molto test, poco training** (p. 42) |
|---|---|---|
| Apprendimento | **buono** | **scarso**: rischio di **underfitting** e di pattern mancati; ci sono abbastanza dati per imparare tutti i casi? |
| Stima delle prestazioni | **poco affidabile**: risultato «**fortunato**»? vera generalità? confidenza statistica? | **affidabile**, con **forte confidenza** |

> 📌 **Il dilemma centrale** (p. 43). **Accuratezza dell’apprendimento** contro **affidabilità della valutazione**; **efficienza nell’uso dei dati** contro **confidenza statistica**. Sto davvero sfruttando il mio dataset?

> 💡 **Quanto è affidabile un test piccolo (nota didattica).** Se il test contiene $n$ esempi e il modello ha accuratezza vera $p$, l’accuratezza misurata ha deviazione standard circa
> $$\sigma \approx \sqrt{\frac{p\,(1-p)}{n}}$$
> Con $p = 0{,}9$ e $n = 50$: $\sigma \approx 0{,}042$, cioè un risultato tra circa 82% e 98% è compatibile con lo stesso modello. Con $n = 1000$: $\sigma \approx 0{,}009$. È il «test fortunato» della slide, in numeri.

#### **4.2. Tre insiemi, tre ruoli**

> 📌 **Training / validation / test** (p. 44).
> - **Training**: **imparare** i parametri del modello.
> - **Validation**: **regolare gli iperparametri** e **scegliere** il modello.
> - **Test**: **stimare la generalizzazione**.
>
> **Si addestra per imparare, si valida per scegliere, si testa per valutare.**

**La validazione fa parte del training** (p. 45). Usi della validation:

| Uso | Esempi |
|---|---|
| **regolazione degli iperparametri** | learning rate; profondità e larghezza della rete; forza della regolarizzazione (L2, tasso di dropout); dimensione del batch |
| **scelta del modello** | scegliere tra architetture diverse; selezionare la configurazione migliore; confrontare modelli alternativi |
| **early stopping** | monitorare la perdita o l’accuratezza di validazione; **fermare l’addestramento** quando le prestazioni di validazione peggiorano; prevenire l’overfitting |
| **diagnostica dell’addestramento** | distinguere underfitting e overfitting |

> ⚠️ **Validation non è test** (p. 45). Se si usa **tutto il dataset** per la validazione, la stima è affetta da **bias ottimistico**: i dati di validazione hanno guidato le scelte, quindi non sono più «nuovi».

#### **4.3. Il concetto fondamentale**

_(slide pp. 46–47)_

> 📌 **Tutte le decisioni si prendono solo sul training set** (pp. 46–47, «un concetto fondamentale»). Il **test set** si usa **SOLO** per stimare l’**errore di generalizzazione**: **NESSUNA ATTIVITÀ DI PROGETTO** su di esso, deve restare **materiale nuovo**. Le attività di progetto si fanno sui soli dati di training (compresa la loro parte di validazione):
> - **numero di neuroni** e **numero di strati** (topologia);
> - **regolarizzazione**;
> - **potatura** (pruning);
> - **normalizzazione**;
> - **PCA**;
> - **ingegneria delle feature**;
> - ecc.

> 💡 **Esempi di violazione (nota didattica).**
> - Calcolare media e deviazione standard per lo z-score su **tutto** il dataset: il test influenza la normalizzazione.
> - Scegliere le feature con la PCA calcolata su tutti i dati.
> - Provare dieci architetture e tenere quella con il miglior risultato **sul test**: il test diventa una validazione, e la stima finale è ottimistica.
>
> In tutti i casi il rimedio è lo stesso: calcolare trasformazioni e scelte sul training e **applicarle** poi al test.

<!-- SOURCE_VISUAL id="IS14-V20" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="47" type="schema" description="Tutte le decisioni sul training set, test usato solo per l’errore di generalizzazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Schemi di validazione incrociata**

_(slide pp. 48–74)_

#### **5.1. k-fold cross validation**

_(slide pp. 48–55)_

> 📌 **k-Fold Cross Validation** (k-FCV, p. 50). Lo schema **più comune**:
> 1. il dataset originale viene **diviso a caso** in $k$ **fold** (partizioni) di **uguale dimensione**;
> 2. dei $k$ fold, **uno** si tiene come dati di **validazione** per testare il modello, e i **restanti $k - 1$** servono a **costruirlo**;
> 3. il processo si **ripete $k$ volte**, usando **ogni fold esattamente una volta** come validazione.

<!-- SOURCE_VISUAL id="IS14-V21" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="49" type="diagramma" description="Approccio generale della validazione incrociata" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V22" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="51" type="diagramma" description="k-fold cross validation con i fold di training e di test a rotazione" reason="Rotazione dei fold visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Una nota importante** (pp. 52–53). Dalla k-FCV si ottengono $k$ **modelli diversi** (MODEL 1, …, MODEL $k$), ciascuno con il suo errore; se ne calcolano **media** e **deviazione standard**. Ma **quale modello rilasciare**?

$$\bar{e} = \frac{1}{k} \sum_{i=1}^{k} e_i \qquad s = \sqrt{\frac{1}{k-1} \sum_{i=1}^{k} (e_i - \bar{e})^2}$$

(forma standard, nota didattica).

> 💡 **Quale modello rilasciare (nota didattica).** La k-FCV non serve a produrre **il** modello, ma a stimare **quanto bene funziona la procedura** (architettura, iperparametri, pre-elaborazione) su dati nuovi, e quanto è **stabile** ($s$). La prassi comune è: scegliere la configurazione con la k-FCV, poi **riaddestrare** con quella configurazione su **tutti** i dati di training e verificare il modello finale sul test set tenuto da parte. Una $s$ grande avverte che il risultato dipende molto da come si dividono i dati.

<!-- SOURCE_VISUAL id="IS14-V23" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="53" type="diagramma" description="k modelli addestrati nella k-FCV con media e deviazione standard degli errori: quale rilasciare?" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Scegliere $k$** (p. 54):

- i valori più comuni secondo la slide sono **5 e 12**;
- $k$ va regolato per non creare fold di test **troppo piccoli** e poco popolati, che distorcono le misure;
- con **dataset grandi** si usa di solito la **10-FCV**, con **dataset piccoli** la **5-FCV**.

> ⚠️ **5 e 12?** Nella letteratura i valori più comuni sono **5 e 10**, come conferma la stessa slide poco dopo; il «12» è probabilmente un refuso per 10.

> 📌 **k-FCV stratificata** (p. 55). La k-FCV semplice può **alterare le proporzioni** delle classi nei fold di test. Il metodo più usato per evitarlo è la **k-FCV stratificata**: ogni fold contiene la **stessa proporzione** di campioni di ogni classe, così la distribuzione delle classi è uguale in tutte le partizioni. È essenziale con i dataset **sbilanciati** (§2): con il 2% di casi rari e fold da 50 esempi, un fold casuale potrebbe non contenerne nessuno.

#### **5.2. Validazione 5×2**

_(slide pp. 56–59)_

> 📌 **5×2 Cross Validation** (p. 57).
> - Il dataset viene diviso a caso in **due metà uguali** (50% training, 50% test).
> - Si addestra su una metà e si testa sull’altra.
> - Poi si **inverte**: l’altra metà diventa training e la prima test.
> - Questa divisione in due (2CV) si **ripete 5 volte**, per un totale di **10 valutazioni**, di cui si calcolano media e deviazione standard (p. 58).

La p. 58 la presenta come una partizione **più bilanciata** tra training e test.

**Vantaggi** (p. 59):

- **bias ridotto**: rispetto a una sola divisione training-test, **ogni esempio** finisce nell’insieme di validazione;
- **robustezza**: le due metà invertite aiutano a valutare la **stabilità** dei risultati e riducono l’influenza di una particolare divisione;
- **adatta ai dataset piccoli**: sfrutta al massimo i dati disponibili sia per il training sia per la validazione.

> 💡 **Il suo uso tipico (nota didattica).** La 5×2 CV è stata proposta da Dietterich (1998) soprattutto per **confrontare due classificatori** con un test statistico: le 10 misure permettono di stimare la variabilità della differenza tra i due, controllando il rischio di dichiarare migliore un modello solo per caso.

<!-- SOURCE_VISUAL id="IS14-V24" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="58" type="diagramma" description="5×2 CV: cinque ripetizioni di due metà scambiate con media e deviazione standard degli errori" reason="Schema delle partizioni visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3. Leave-one-out e leave-one-person-out**

_(slide pp. 60–64)_

**Leave-one-out** (LOO, pp. 60–62), da considerare **se si è in underfitting** con k-FCV e 5×2 CV:

- è il **caso estremo** della k-FCV: $k$ è uguale al **numero di esempi** del dataset;
- a ogni passo **un solo esempio** serve a **testare** il modello, e **tutti gli altri** a **addestrarlo**.

> 💡 **Costo e uso (nota didattica).** Con $N$ esempi servono $N$ addestramenti: con 1000 esempi, mille modelli. Ogni modello usa $N - 1$ esempi, quindi quasi tutti i dati: è la scelta per **dataset piccoli**, dove togliere un fold intero lascerebbe il modello senza abbastanza dati per imparare.

<!-- SOURCE_VISUAL id="IS14-V25" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="62" type="diagramma" description="Leave-one-out: un solo esempio di test a ogni passo" reason="Schema delle partizioni visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Leave-one-PERSON-out** (LOPO, pp. 63–64). Si lascia fuori, a ogni passo, **tutti i dati di una persona**. Si usa in **biometria**, **screening medico** e simili, ed è **critico per i modelli grandi e profondi**. La variante **L(N)PO** lascia fuori **N persone** alla volta.

La slide confronta la divisione per persona con una divisione **mescolata** (MIX): con LOPO «è **più difficile usare il colore dei capelli**».

> 💡 **Perché serve (nota didattica).** Se le foto della stessa persona stanno sia in training sia in test, un modello grande può riconoscere **la persona** (capelli, pelle, sfondo della sua sessione) invece di imparare il compito (per esempio l’emozione o la malattia). Il risultato è ottimo in validazione e pessimo su persone nuove: è il «déjà vu» della Lezione 07, §3.4. LOPO garantisce che il modello sia sempre testato su **persone mai viste**.

<!-- SOURCE_VISUAL id="IS14-V26" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="63" type="diagramma" description="Leave-one-person-out in biometria e screening medico" reason="Schema delle partizioni per persona visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V27" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="64" type="diagramma" description="LOPO e L(NP)O contro divisione mescolata: più difficile usare il colore dei capelli" reason="Confronto visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4. Validazione Monte Carlo**

_(slide pp. 65–69)_

**Il metodo Monte Carlo** (p. 66). I metodi Monte Carlo sono una vasta classe di algoritmi che usano il **campionamento casuale ripetuto** per ottenere risultati numerici; l’idea è **usare la casualità** per risolvere problemi che potrebbero essere **deterministici** in linea di principio.

> 📌 **Esempio: stimare π** (p. 66). Si distribuiscono a caso **10 000 punti** in un quadrato e si contano quelli dentro il cerchio inscritto. Il rapporto tra le aree è $\pi r^2 / (2r)^2 = \pi / 4$, quindi
> $$\pi \approx 4 \cdot \frac{\text{punti nel cerchio}}{\text{punti totali}} = 4 \cdot \frac{7891}{10\,000} = 3{,}1564$$
> contro il valore vero 3,1416.

<!-- SOURCE_VISUAL id="IS14-V28" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="66" type="grafico" description="Stima di π con 10 000 punti casuali, 7891 dentro il cerchio" reason="Punti del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Validazione Monte Carlo** (*Repeated Random Subsampling Validation*, pp. 65, 67–68), usata per esempio in finanza, AI medica ed elaborazione dei segnali. Il dataset viene **diviso a caso più volte** in training e test:
> 1. divisione casuale in training e test;
> 2. addestramento sul training e valutazione sul test;
> 3. **ripetizione** più volte, con una **nuova divisione casuale** a ogni iterazione;
> 4. stima finale delle prestazioni come **media** su tutte le iterazioni.

**Punti chiave** (p. 69):

- **rapporto di divisione flessibile**: a differenza della k-FCV, le dimensioni di training e test **non devono essere fisse** tra le iterazioni;
- **randomizzazione ripetuta**: ogni iterazione genera una **nuova partizione** casuale;
- **numero di ripetizioni** scelto dall’utente, in base alla **confidenza statistica** desiderata.

> ⚠️ **Una differenza con la k-FCV (nota didattica).** Nella k-FCV ogni esempio è testato **esattamente una volta**; nella Monte Carlo alcuni esempi possono finire nel test **molte volte** e altri **mai**. In compenso si possono fare quante ripetizioni si vuole, indipendentemente dalla dimensione del test.

<!-- SOURCE_VISUAL id="IS14-V29" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="67" type="diagramma" description="Validazione Monte Carlo con divisioni casuali ripetute" reason="Schema delle divisioni visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.5. Bias e varianza**

_(slide pp. 70–74)_

**Bias e varianza degli errori** (p. 70). Nei metodi di validazione incrociata (k-FCV, 5×2 CV, LOO, …) si testa **un modello per ogni partizione**, e ogni modello ha il suo errore: si raccolgono gli errori $e_1, e_2, \dots, e_k$. La slide immagina di confrontare **due classificatori** con una 5-fold CV.

> 💡 **Due classificatori, stessa media (nota didattica).** Con errori per fold A = (10%, 11%, 9%, 10%, 10%) e B = (2%, 18%, 5%, 20%, 5%), la media è **10%** per entrambi, ma B ha una deviazione standard molto più alta: il suo comportamento dipende da **quali dati** gli capitano. A parità di media, A è il modello più affidabile.

<!-- SOURCE_VISUAL id="IS14-V30" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="70" type="grafico" description="Errori per fold di due classificatori in una 5-fold CV" reason="Valori degli errori visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Il dilemma bias-varianza** (pp. 71–73). È un **compromesso fondamentale** del machine learning: un modello deve essere **abbastanza complesso** da imparare la **vera struttura** dei dati, ma **non così complesso** da imparare **rumore**, pattern accidentali o dettagli specifici del training set.
> - Un modello **troppo semplice** ha di solito **bias alto e varianza bassa**: **underfitting**.
> - Un modello **troppo complesso** ha di solito **bias basso e varianza alta**: **overfitting**.
> - Il **bias** misura l’**errore sistematico** dovuto a modelli o strategie di valutazione troppo semplici; la **varianza** misura la **sensibilità alle divisioni dei dati**, e cresce quando modelli o validazione sono instabili.
>
> Il dilemma consiste nello scegliere il modello che **minimizza l’errore atteso sui dati nuovi**, restando **stabile** tra divisioni diverse.

> 📌 **La scomposizione (nota didattica).** Per la regressione con errore quadratico, l’errore atteso su un nuovo punto $x$ si scompone in
> $$\mathbb{E}\big[(y - \hat{f}(x))^2\big] = \underbrace{\big(\mathbb{E}[\hat{f}(x)] - f(x)\big)^2}_{\text{bias}^2} + \underbrace{\mathbb{E}\big[(\hat{f}(x) - \mathbb{E}[\hat{f}(x)])^2\big]}_{\text{varianza}} + \underbrace{\sigma^2}_{\text{rumore}}$$
> dove l’attesa è sui possibili training set. Il rumore $\sigma^2$ non si può eliminare; il progettista sposta l’equilibrio tra gli altri due termini scegliendo la complessità del modello.

<!-- SOURCE_VISUAL id="IS14-V31" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="72" type="grafico" description="Il dilemma bias-varianza in funzione della complessità del modello" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Riepilogo dei metodi** (p. 74):

| Metodo | Caratteristica chiave |
|---|---|
| **Monte Carlo** | **molto flessibile**, ma può essere **costosa** in calcolo |
| **k-Fold CV** | **ben bilanciata** e molto usata (considerare anche la versione **stratificata**) |
| **5×2 CV** | utile per **confrontare modelli** e controllare la **varianza** |
| **LOO** | adatta ai **dataset piccoli**, ma **costosa** in calcolo |
| **LOPO** | per problemi di **generalizzazione su soggetti** diversi |

---

### **6. Validazione con serie temporali**

_(slide pp. 75–79)_

Le serie temporali sono tipiche di **biometria**, **screening medico**, **previsioni** e **mercati azionari** (p. 75). La p. 76 mostra il **problema classico** della previsione: nelle serie temporali una divisione casuale mette dati **futuri** nel training (Lezione 07, §3.5).

<!-- SOURCE_VISUAL id="IS14-V32" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="76" type="diagramma" description="Il problema classico della divisione nelle serie temporali" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Dividere una serie temporale in training e test è un problema **soggetto a errori**, in cui si può avere **data leakage**. Un sistema di base è **simulare il passare del tempo**:

| Schema | Come funziona | Svantaggio |
|---|---|---|
| **finestra mobile** (*rolling window*, p. 77) | una finestra di training di dimensione fissa scorre nel tempo, seguita dalla finestra di test | si **sprecano molti dati**: il passato più lontano non viene usato |
| **finestra espandibile in avanti** (*expanding window forward*, p. 78) | il training parte dall’inizio e si **allarga** a ogni passo, il test è sempre subito dopo | le partizioni di training hanno **dimensioni diverse** |

**Approcci più complessi** (p. 79). Si può anche **non rispettare** la relazione passato-presente-futuro **tra le partizioni** (dentro ogni partizione il tempo resta ordinato): il modello potrà **anche validare dati del passato** usando come training dati del **futuro**. Oppure si possono aggiungere **intervalli vuoti** (*gap*) tra training e test per rendere la previsione **più impegnativa** («gap leave p out»).

> 💡 **Perché il gap (nota didattica).** Campioni vicini nel tempo sono molto simili: se il test inizia subito dopo l’ultimo campione di training, il modello può «copiare» l’ultimo valore visto e sembrare bravo. Un intervallo vuoto tra i due insiemi obbliga il modello a prevedere davvero, e riduce il leakage dovuto all’autocorrelazione della serie.

<!-- SOURCE_VISUAL id="IS14-V33" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="77" type="diagramma" description="Validazione a finestra mobile nelle serie temporali" reason="Posizione delle finestre visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V34" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="78" type="diagramma" description="Validazione a finestra espandibile in avanti" reason="Posizione delle finestre visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS14-V35" source="Lesson_14_Workflow_Optimization_DatasetPartitioning_AccuracyAssessment.pdf" page="79" type="diagramma" description="Partizioni temporali non ordinate e con gap (gap leave p out)" reason="Disposizione delle partizioni visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Regressione** / **classificazione** nei grafici | $y$ esplicita come asse / $y$ implicita come colore dei punti |
| **Dataset accademico** / **reale** | classi bilanciate / classi sbilanciate con costi di errore diversi |
| **Sovracampionamento** / **sottocampionamento** | nessun campione ignorato, rischio di overfitting / perdita di informazione |
| **Duplicare** / **SMOTE** | copie identiche / campioni sintetici sul segmento tra vicini |
| **Sottocampionamento casuale** / **dataset condensato** | toglie maggioritari a caso / conserva i maggioritari vicini alla frontiera |
| **Bias ottimistico** / **overfitting** | stima troppo favorevole per dati usati nel progetto / modello troppo adattato ai dati noti |
| **Underfitting** / **overfitting** | errore alto anche in training / training basso e test alto |
| **Validation** / **test** | scegliere iperparametri e modello / stimare la generalizzazione, nessuna scelta di progetto |
| **k-FCV** / **k-FCV stratificata** | fold casuali / stesse proporzioni di classi in ogni fold |
| **k-FCV** / **5×2 CV** / **LOO** | $k$ fold, ogni esempio testato una volta / 5 ripetizioni di due metà, 10 misure / $k = N$ |
| **LOO** / **LOPO** | un esempio fuori / tutti i dati di una persona fuori |
| **k-FCV** / **Monte Carlo** | partizioni fisse e disgiunte / divisioni casuali ripetute, rapporto flessibile |
| **Bias** / **varianza** | errore sistematico, modello troppo semplice / sensibilità ai dati, modello troppo complesso |
| **Finestra mobile** / **finestra espandibile** | training di dimensione fissa, spreco di dati / training crescente, dimensioni diverse |

---

### **8. Sintesi della lezione**

_(slide p. 80)_

> ✅ **Punti principali** (p. 80).
> - **Flusso di lavoro e ottimizzazione**: rappresentazione, valutazione e ottimizzazione collocati nei passi del flusso; l’obiettivo è stimare bene le probabilità a posteriori.
> - **Bilanciamento del dataset**: più dati, etichette diverse, campionamento (SMOTE, dataset condensato), pesatura della perdita, ciascuno con i suoi rischi.
> - **Partizione del dataset**: underfitting, overfitting, bias ottimistico e progettazione del dataset; training per imparare, validation per scegliere, test per valutare.
> - **Stima dell’accuratezza**: k-FCV (anche stratificata), 5×2 CV, LOO, LOPO, Monte Carlo, con media e deviazione standard degli errori e il dilemma bias-varianza.
> - **Serie temporali**: finestre mobili ed espandibili, eventualmente con gap.
> - **Due punti fortissimi**:
>   - **NON PROGETTARE USANDO IL TEST SET**;
>   - **UN ERRORE DELL’1% PUÒ ESSERE BUONO O CATTIVO**: dipende da distribuzione delle classi, costi degli errori e confidenza della stima.
