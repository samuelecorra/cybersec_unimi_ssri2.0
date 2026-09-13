## **Lezione 19: Classificatore Bayes-ottimo, k-NN, clustering k-means e sistemi ibridi**

La diciannovesima lezione del corso **IS4** parte da una domanda di fondo: **qual è l’errore minimo raggiungibile?** La risposta è il **classificatore Bayes-ottimo**, un limite teorico che può essere maggiore di zero quando le classi si sovrappongono. Da qui la lezione passa ai **modelli classici**, non neurali, e alla distinzione tra apprendimento **eager** e **lazy**, per arrivare al **k-Nearest Neighbors**: semplice, deterministico, spiegabile, e asintoticamente vicino al classificatore ottimo. Segue il **clustering k-means**, con l’algoritmo, i suoi limiti e i criteri per scegliere il numero di cluster. Chiude una panoramica degli altri approcci dell’intelligenza computazionale: **algoritmi genetici**, **logica fuzzy**, **swarm intelligence** e **sistemi ibridi**, con un esempio di ottimizzazione genetica dei parametri.

Fonti: [PDF della Lezione 19](Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf), 122 pagine, e lo script [Lesson_19_laboratory_MATLAB_Knn.m](Lesson_19_laboratory_MATLAB_Knn.m), letto integralmente. Le formule che nelle slide sono immagini sono riportate in forma standard; le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–3)_

Titolo originale: *Bayes Optimal Classification, Nearest Neighbor Classifiers (kNN), Clustering (k means), Main intelligent systems approaches: Neural Networks, Fuzzy systems, Evolutionary, Hybrid systems*. L’indice (pp. 2–3) elenca:

1. **classificazione Bayes-ottima**;
2. importanza dei **modelli classici** (non neurali);
3. metodi di apprendimento **eager** e **lazy**;
4. **k-NN**: importanza nel pattern recognition, definizione, problemi (velocità, maledizione della dimensionalità);
5. **clustering k-means**;
6. principali tipi di sistemi di **intelligenza computazionale**: sistemi fuzzy, evolutivi, agenti, sistemi ibridi;
7. esempio di **ottimizzazione genetica dei parametri**.

---

### **1. Il limite: la classificazione Bayes-ottima**

_(slide pp. 4–15)_

#### **1.1. Esiste un errore minimo**

Si insegue l’errore minimo, ma **qual è il limite** (p. 4)?

> 📌 **Il limite Bayes-ottimo** (p. 5). Su un problema e un dataset specifici, con il tempo, più tentativi e nuove tecniche (per esempio il deep learning), gli algoritmi di ML possono **superare gli esseri umani**… ma **fino a un limite**. **Inseguire sempre lo 0% di errori può essere un errore!**

**L’errore Bayes-ottimo** (p. 6). Quando le distribuzioni delle classi (per esempio «buono» e «cattivo» lungo una feature $F_1$) **non sono separate**, anche il **miglior classificatore** produce un errore legato alle **zone di sovrapposizione**, in 2D come in 3D.

<!-- SOURCE_VISUAL id="IS19-V01" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="6" type="grafico" description="Distribuzioni sovrapposte delle classi buono e cattivo lungo F1, in 2D e 3D" reason="Sovrapposizione delle distribuzioni visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Il classificatore Bayes-ottimo**

**Esempio** (pp. 7–10). Si distinguono **avocado** e **arance** usando la feature $D$ = rosso − verde. Le ipotesi sono $h_0$ / $C_0$ = «l’input è un avocado» e $h_1$ / $C_1$ = «l’input è un’arancia»; le predizioni sono $v_0$ = «credo sia un avocado» e $v_1$ = «credo sia un’arancia». Dai dati si **stima la densità di probabilità** di $D$ per ciascuna classe. Intuitivamente, osservando le feature $D$ (per esempio colore e peso), si classifica l’oggetto nella classe a cui appartiene la **maggior parte dei campioni con valori simili**: si sceglie «arancia» se

$$P(C = \text{arancia} \mid x) > P(C = \text{avocado} \mid x)$$

Nella slide della p. 8 il testo dice per errore «classifichiamo l’oggetto come una **mela**», mentre le classi dell’esempio sono avocado e arancia.

> 📌 **Classificatore Bayes-ottimo** (pp. 9, 11). Assegna la classe **più probabile** dato l’input. In forma standard:
> $$\hat{c}(x) = \arg\max_{c} P(c \mid x) = \arg\max_{c} \, p(x \mid c)\, P(c)$$
> - Nel mondo reale calcolare le probabilità può essere **molto inefficiente o impossibile**, soprattutto quando $x$ vive in uno spazio **ad alta dimensionalità**.
> - Si possono calcolare le probabilità solo **conoscendo le distribuzioni**; la slide aggiunge anche feature **indipendenti** (ipotesi che però serve al Naive Bayes, §1.3).
> - È un **concetto teorico**: **nessun altro metodo** di classificazione può fare **meglio in media**, a parità di spazio delle ipotesi e conoscenza a priori.
> - **L’errore minimo possibile può essere maggiore di zero!**

> 💡 **L’errore di Bayes in formula (nota didattica).** Per due classi, in ogni punto $x$ il classificatore ottimo sbaglia con probabilità pari alla classe **meno** probabile. L’errore minimo complessivo è
> $$P_e^{*} = \int \min\big( P(c_0)\, p(x \mid c_0),\; P(c_1)\, p(x \mid c_1) \big)\, dx$$
> cioè l’**area della sovrapposizione** delle due curve pesate. Se le curve non si toccano, $P_e^* = 0$; se sono identiche e le classi equiprobabili, $P_e^* = 0{,}5$. Un modello che su un problema così dichiara lo 0% di errore sta quasi certamente **memorizzando** il training set.

<!-- SOURCE_VISUAL id="IS19-V02" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="7" type="grafico" description="Densità stimate di D = rosso - verde per avocado e arance" reason="Curve di densità visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V03" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="10" type="grafico" description="Frontiera del classificatore Bayes-ottimo tra C0 e C1" reason="Frontiera e regioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Naive Bayes**

_(slide pp. 12–15)_

La p. 13 illustra l’**ipotesi «naive»** sull’esempio avocado/arancia e sul dataset Iris.

> 📌 **Classificatore Naive Bayes** (p. 14).
> - Parte dalla teoria del classificatore Bayes-ottimo e **usa i dati di training** per **stimare le distribuzioni**, e quindi trovare una separazione adeguata tra le classi.
> - **Ipotesi**: **forte indipendenza** delle feature, raramente soddisfatta, da cui il nome «**naive**» (ingenuo).
> - Richiede un numero di parametri **lineare** nel numero di variabili del problema.
> - È adatto a input **ad alta dimensionalità**.
> - In generale l’**accuratezza è buona** rispetto ad altri classificatori classici.

> 💡 **La formula e il perché dei parametri lineari (nota didattica).** Con l’ipotesi di indipendenza delle $d$ feature data la classe:
> $$P(c \mid x_1, \dots, x_d) \propto P(c) \prod_{j=1}^{d} p(x_j \mid c)$$
> Invece di stimare una distribuzione congiunta in $d$ dimensioni, che richiederebbe dati in quantità esponenziale, si stimano $d$ distribuzioni **monodimensionali** per classe: con feature gaussiane bastano una media e una varianza per feature e per classe, cioè $2 \cdot d \cdot C$ parametri. Anche quando l’indipendenza è falsa, spesso l’`argmax` resta corretto, per questo funziona sorprendentemente bene (per esempio nei filtri antispam).

La p. 15 mostra il **codice** del classificatore Naive Bayes.

<!-- SOURCE_VISUAL id="IS19-V04" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="13" type="grafico" description="Ipotesi naive sull’esempio avocado/arancia e sul dataset Iris" reason="Distribuzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V05" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="15" type="schermata" description="Codice del classificatore Naive Bayes" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Modelli classici e apprendimento eager o lazy**

_(slide pp. 16–24)_

#### **2.1. Perché i modelli classici contano**

**I metodi classici (non neurali) sono importanti** (pp. 16–17):

- **alcuni sono semplici**: rasoio di Occam;
- i metodi di apprendimento sono **molto noti** e presenti in **tutti gli strumenti** di ML;
- danno un **riferimento solido** per l’accuratezza;
- la **spiegabilità è maggiore**: nei modelli di deep learning è molto difficile.

**Esempi di metodi classici** (p. 18):

| Famiglia | Esempi |
|---|---|
| **basati su distanze** | k-NN |
| **basati su alberi** | albero di decisione |
| **lineari / parametrici** | classificatori lineari, quadratici, logistici |
| **probabilistici / basati su distribuzioni** | classificatore Bayes-ottimo, Naive Bayes |
| **metodi kernel** | Support Vector Machine, Radial Basis Functions |

Questi modelli sono **preferiti** quando **interpretabilità**, **semplicità** o **pochi dati** rendono il deep learning inadatto.

#### **2.2. Eager e lazy**

> 📌 **Due modi di apprendere** (pp. 19–20).
> - **Apprendimento eager** («avido»): costruisce una **descrizione esplicita** della funzione obiettivo su tutto il training set. Durante l’addestramento il sistema cerca di creare una funzione **generale, indipendente dall’input**: generalizzazioni e concetti si producono **durante l’apprendimento**.
> - **Apprendimento basato su istanze** (**lazy**, «pigro»): **apprendere = memorizzare** tutti gli esempi di training; **classificare** = assegnare la funzione obiettivo a una **nuova istanza**. La generalizzazione oltre i dati di training è **rinviata** fino a quando arriva una **richiesta**.

Le immagini mnemoniche delle slide: l’eager learner vede «qualsiasi movimento casuale ⇒ è un topo!», cioè ha già costruito il concetto; il lazy learner ha memorizzato «tutto» e, davanti a un nuovo input, lo confronta con ciò che ha visto: «è molto simile a un computer da tavolo!» (pp. 21–22).

<!-- SOURCE_VISUAL id="IS19-V06" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="21" type="illustrazione" description="Apprendimento eager: la regola generale costruita in addestramento (è un topo)" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V07" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="22" type="illustrazione" description="Apprendimento lazy: tutto memorizzato, confronto al momento della richiesta" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Compromessi** (p. 23):

| | **Addestramento** | **Uso (recall, rilascio)** |
|---|---|---|
| **Eager** | **lungo**; #DoF limitati (non per le reti profonde) | **veloce**, **poca memoria** |
| **Basato su istanze** | **veloce**; i #DoF crescono con i dati | **lungo**, **molta memoria** |

**Esempi** (p. 24):

| | **Modelli classici (non neurali)** | **Metodi neurali** |
|---|---|---|
| **Eager** | alberi di decisione; sistemi a induzione e a regole; Support Vector Machine | reti feed-forward; reti convoluzionali (CNN) |
| **Basati su istanze** | k-Nearest Neighbors; regressione pesata; case-based reasoning | macchine kernel; reti a funzioni di base radiale (RBFNN) |

---

### **3. Il classificatore k-Nearest Neighbors**

_(slide pp. 25–51)_

#### **3.1. Perché il k-NN è indispensabile**

**Un classificatore «must-have»** (pp. 25–27):

- è un classificatore **classico**, non basato su tecniche neurali;
- è **deterministico**: **nessuna inizializzazione casuale** (come nelle reti neurali o negli algoritmi evolutivi), **ripetibilità perfetta**;
- servono **pochissimi parametri**;
- l’**apprendimento è semplicissimo**;
- ha una **spiegabilità perfetta**, sia su **come funziona** sia sul **perché** un campione è stato classificato «cattivo»;
- è presente in **tutte le librerie**;
- partendo dal k-NN si capiscono **concetti chiave** validi anche per i modelli grandi come le reti profonde.

> 📌 **Perché studiarlo** (p. 28).
> - È un classificatore **molto potente per capire i dati**, se il dataset non è troppo grande (altrimenti la memoria non basta).
> - Si può usare come **strumento di debug** per controllare i dati e l’accuratezza **degli altri classificatori**, anche neurali.
> - Infine, **tende al classificatore Bayes-ottimo**: il classificatore «divino», il migliore che si possa costruire anche **conoscendo esattamente** le distribuzioni di probabilità delle classi.

> 💡 **Quanto è vicino all’ottimo (nota didattica).** Un risultato classico di Cover e Hart (1967) dice che, con infiniti dati, l’errore del **1-NN** non supera il **doppio** dell’errore di Bayes: $P_e^* \le P_{1\text{NN}} \le 2\,P_e^*$ (per due classi, a meno di un termine trascurabile). Facendo crescere $k$ insieme ai dati, ma più lentamente ($k \to \infty$, $k/N \to 0$), il k-NN **converge** proprio all’errore di Bayes. È il senso del «tende al classificatore divino».

#### **3.2. Definizione e frontiere di Voronoi**

_(slide pp. 29–37)_

> 📌 **L’idea di base** (p. 29). «Se cammina come un’anatra e fa qua qua come un’anatra, allora probabilmente è un’anatra.» Si calcola la **distanza** del record di test da **tutti** i campioni di training e si scelgono i $k$ record **più vicini**.

**Servono tre cose** (p. 30):

1. l’insieme dei **record memorizzati**;
2. una **metrica di distanza** tra record;
3. il valore di **$k$**, il numero di vicini da recuperare.

**Per classificare un record sconosciuto**: calcolare la distanza dagli altri record di training, individuare i $k$ vicini più prossimi, usare le **etichette** dei vicini per decidere la classe, per esempio con il **voto di maggioranza**.

> 📌 **Definizione** (p. 31). I **$k$ vicini più prossimi** di un record $x$ sono i punti con le $k$ **distanze più piccole** da $x$. La slide mostra i casi con 1, 2 e 3 vicini.

**Voto di maggioranza** (p. 32). Lo stesso nuovo punto può ricevere classi diverse: con $k = 1$ **blu**, con $k = 3$ **verde**.

> 💡 **Il pareggio con $k$ pari (nota didattica).** Con 2 vicini di classi diverse il voto è in parità: per due classi si sceglie di solito $k$ **dispari**; in alternativa si risolve il pareggio con il vicino più vicino o con i pesi sulla distanza.

<!-- SOURCE_VISUAL id="IS19-V08" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="31" type="grafico" description="Uno, due e tre vicini più prossimi di un record x" reason="Cerchi dei vicini visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V09" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="32" type="grafico" description="Voto di maggioranza: blu con k = 1, verde con k = 3" reason="Punti e cerchi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Le frontiere del 1-NN: il diagramma di Voronoi** (p. 33). Il **diagramma di Voronoi** è una **partizione del piano** in regioni, ciascuna formata dai punti **più vicini** a uno dei punti di un certo insieme. Le frontiere di decisione del 1-NN sono i **bordi** delle celle di Voronoi dei campioni di training che separano classi diverse. Il diagramma di Voronoi di un insieme di punti è il **duale** della sua **triangolazione di Delaunay**.

La p. 34 mostra un diagramma di Voronoi in **3D**; le pp. 35–36 gli usi: **scienze naturali e biologia** (la tassellazione di Voronoi nasce dalla crescita radiale a partire da semi), **salute** (correlare le fonti di infezione nelle epidemie), **ingegneria** (volumi liberi dei polimeri), **geometria**, **informatica**, **pianificazione urbana**, e un diagramma di Voronoi degli **aeroporti del mondo** proiettato su un globo 3D (Jason Davies).

> 💡 **Un uso storico (nota didattica).** Nel 1854 John Snow mappò i morti di colera a Londra e mostrò che quasi tutti vivevano più vicini a una certa pompa d’acqua di Broad Street che a qualsiasi altra: in pratica disegnò una cella di Voronoi. È l’esempio classico dell’uso «epidemie» citato dalla slide.

<!-- SOURCE_VISUAL id="IS19-V10" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="33" type="grafico" description="Diagramma di Voronoi come frontiere di decisione del 1-NN" reason="Celle del diagramma visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V11" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="36" type="immagine" description="Diagramma di Voronoi degli aeroporti del mondo su un globo 3D (Jason Davies)" reason="Immagine della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Calcolo** (p. 37): la distanza tra due punti, per esempio **euclidea**,

$$d(\mathbf{p}, \mathbf{q}) = \sqrt{\sum_{i} (p_i - q_i)^2}$$

poi il **voto di maggioranza** tra le etichette dei $k$ vicini; in alternativa si **pesa il voto** in base alla distanza, con peso $w = 1/d^2$.

#### **3.3. Scelta di $k$, scala delle feature, dimensionalità**

_(slide pp. 38–43)_

**Scegliere $k$** (pp. 38–40):

| $k$ | Effetto |
|---|---|
| **troppo piccolo** | **sensibile ai punti rumorosi** |
| **troppo grande** | l’intorno può includere punti **di altre classi** |
| **alto** | agisce come **regolarizzazione** (frontiere più lisce), ma serve **più tempo** per elaborare i dati memorizzati |

La p. 40 mostra come la **complessità** del modello dipenda da $k$.

> 💡 **Complessità e $k$ (nota didattica).** Con $k = 1$ la frontiera segue ogni singolo punto (massima complessità, rischio di overfitting); con $k = N$ il modello risponde sempre con la classe maggioritaria (minima complessità, underfitting). Un’indicazione intuitiva è che il k-NN ha circa $N/k$ «gradi di libertà efficaci»: aumentare $k$ equivale a semplificare il modello. Il $k$ giusto si sceglie con la cross-validation (Lezione 14).

<!-- SOURCE_VISUAL id="IS19-V12" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="39" type="grafico" description="Frontiere più lisce con k alto: effetto di regolarizzazione" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V13" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="40" type="grafico" description="Complessità del modello in funzione di k" reason="Pagina con solo grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Problemi di scala** (p. 41). Gli attributi possono dover essere **scalati** perché la distanza non sia **dominata** da uno di essi. Esempio:
> - l’**altezza** di una persona varia da 1,5 m a 1,8 m;
> - il **peso** da 90 a 300 libbre;
> - il **reddito** da 10 000 a 1 000 000 di dollari.
>
> Senza scalatura, nella distanza euclidea conta **solo il reddito**: una differenza di 1000 dollari pesa migliaia di volte più dell’intera variazione di altezza.

**Pro e contro** (p. 42):

- (+) **robusto ai dati rumorosi**, perché fa la media sui $k$ vicini;
- (−) **maledizione della dimensionalità**: la distanza tra i vicini può essere **dominata da attributi irrilevanti**. Rimedi: **allungare gli assi** (pesare le feature) oppure **eliminare gli attributi meno rilevanti** (selezione delle feature, Lezione 18).

> 💡 **La maledizione della dimensionalità in numeri (nota didattica).** In un ipercubo unitario a $d$ dimensioni con dati uniformi, per catturare il 10% dei punti serve un cubetto di lato $0{,}1^{1/d}$: in 2D il lato è circa 0,32, ma in 10D è circa **0,79**, quasi tutto il cubo. I «vicini» non sono più vicini, e le distanze tra punti diversi diventano quasi tutte simili. È il motivo per cui il k-NN funziona bene con poche feature ben scelte e male con centinaia di feature grezze.

**k-NN pesato sulla distanza** (p. 43). Si assegnano ai vicini **pesi** in base alla loro distanza dal punto di interrogazione, per esempio l’**inverso del quadrato** della distanza. In questo modo **tutti** i punti di training possono influenzare un’istanza (**metodo di Shepard**), e le partizioni passano da **lisce** a quelle a celle di **Voronoi**.

> 📌 **Voto pesato (nota didattica).** Con i $k$ vicini $x_1, \dots, x_k$ di etichette $y_i$:
> $$\hat{c}(x) = \arg\max_{c} \sum_{i=1}^{k} w_i\, [\,y_i = c\,], \qquad w_i = \frac{1}{d(x, x_i)^2}$$
> Un vicino a distanza 1 vale quattro volte un vicino a distanza 2.

#### **3.4. Velocità, un esempio e bias induttivo**

_(slide pp. 44–51)_

**Problemi pratici** (p. 44): **velocità** e **maledizione della dimensionalità**.

**Velocità** (p. 45). Tempo del k-NN per $N$ punti in $D$ dimensioni:

| Operazione | Costo |
|---|---|
| calcolo delle distanze | $O(ND)$ |
| trovare i $k$ vicini con minimi ripetuti | $O(kN)$ |
| … con ordinamento | $O(N \log N)$ |
| … con un min-heap | $O(N + k \log N)$ |
| … con una mediana veloce | $O(N + k \log k)$ |

Il tempo totale è **dominato dal calcolo delle distanze**. Si può essere più veloci se si accetta di **rinunciare all’esattezza** (ricerca approssimata dei vicini).

> 💡 **Il conto per un’applicazione (nota didattica).** Con un milione di immagini descritte da 512 feature, ogni classificazione richiede circa $10^6 \times 512 \approx 5 \times 10^8$ operazioni solo per le distanze. È l’altra faccia del lazy learning (§2.2): addestramento istantaneo, uso costoso. Strutture come i k-d tree o gli indici approssimati dei database vettoriali (Lezione 06, §6.3) servono proprio a questo.

<!-- SOURCE_VISUAL id="IS19-V14" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="46" type="grafico" description="Esempio di classificazione k-NN" reason="Pagina con solo grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempio con i dati sulla frutta** (pp. 46–50). Dati di training con etichette {mele, arance, limoni} e attributi {larghezza, altezza}. Un punto di test $(a, b)$ viene classificato **limone**, un punto $(c, d)$ **mela**. Con il voto di maggioranza tra i $k$ vicini, il valore di $k$ cambia l’effetto degli **outlier**; la p. 50 mostra le frontiere di decisione del **1-NN**.

<!-- SOURCE_VISUAL id="IS19-V15" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="48" type="grafico" description="Punti di test classificati come limone e mela nel piano larghezza-altezza" reason="Punti di test visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V16" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="49" type="grafico" description="Effetto di k e di un outlier nel voto di maggioranza" reason="Outlier e vicini visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V17" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="50" type="grafico" description="Frontiere di decisione del 1-NN sui dati della frutta" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Il bias induttivo del k-NN** (p. 51). Ogni k-NN incorpora tre scelte:
> - **scelta delle feature**: si assume che **tutte le feature siano ugualmente importanti**. Che cosa succede se si **moltiplica una feature per 100**?
> - **scelta della funzione di distanza**: euclidea, similarità coseno (angolo), gaussiana, … Le coordinate dovrebbero essere **indipendenti**?
> - **scelta di $k$**.

---

### **4. Il k-NN in MATLAB**

_(slide pp. 52–57)_

Lo script [Lesson_19_laboratory_MATLAB_Knn.m](Lesson_19_laboratory_MATLAB_Knn.m) (pp. 52–55):

```matlab
load fisheriris
X = meas;
Y = species;
% X is a numeric matrix that contains four petal measurements for 150 irises.
% Y is a cell array of character vectors that contains the corresponding iris species.

plotmatrix(X)   % just a simple plot

% Train a 5-nearest neighbor classifier.
% Standardize the noncategorical predictor data
kNN_model = fitcknn(X,Y,'NumNeighbors',5,'Standardize',1)

% Let's try to input a single vector
x = X(1,:);
label = predict(kNN_model,x)

% Do a cross-validation test
% The function will create SUBCLASSIFIERS to do a correct CrossValidation
cvmdl_results = crossval(kNN_model, 'KFold',10)
kfoldLoss(cvmdl_results)
fprintf('KFOLD Validation with K = %d --> Error = %f \n', 10, kfoldLoss(cvmdl_results));

for K = [9:-1:3]
    cvmdl_results = crossval(kNN_model, 'KFold',K);
    fprintf('KFOLD Validation with K = %d --> Error = %f \n', K, kfoldLoss(cvmdl_results));
end
```

| Istruzione | Che cosa fa |
|---|---|
| `fitcknn(X, Y, 'NumNeighbors', 5, 'Standardize', 1)` | crea un **5-NN** e **standardizza** i predittori (z-score, §3.3) |
| `predict(kNN_model, x)` | classifica un singolo vettore, il primo fiore |
| `crossval(kNN_model, 'KFold', 10)` | crea **sotto-classificatori** addestrati sui fold, per una cross-validation corretta |
| `kfoldLoss(...)` | errore medio di classificazione sui fold |
| ciclo `for K = [9:-1:3]` | ripete la validazione con **meno fold**, da 10 a 3 |

> ⚠️ **Due $k$ diversi** (p. 55). La $K$ del ciclo è il **numero di fold della cross-validation**, **non** il $k = 5$ del k-NN. Riducendo i fold, ogni modello viene addestrato su **meno dati**, quindi in generale l’**accuratezza peggiora**; nell’esempio però il **rumore maschera** questo effetto.

> 💡 **Il rumore della stima (nota didattica).** Ogni chiamata a `crossval` sceglie una **nuova partizione casuale**, quindi l’errore stampato cambia a ogni esecuzione anche con lo stesso $K$. Su Iris gli errori del 5-NN sono di pochi punti percentuali, e le differenze tra $K = 10$ e $K = 3$ sono dello stesso ordine della variabilità dovuta al caso: per confrontarle servirebbe ripetere più volte ciascuna validazione (Lezione 14, §5.5).

> ⚠️ **Commenti da aggiornare.** Nello script la standardizzazione è accompagnata dal commento «vedi la lezione sulla codifica delle uscite», ma riguarda gli **input**; e il commento iniziale rimanda al codice LDA della «Lezione 14», che in questa edizione si trova nelle Lezioni 16 e 17.

<!-- SOURCE_VISUAL id="IS19-V18" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="57" type="schermata" description="Riepilogo dei classificatori programmati finora in MATLAB e Python" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Clustering con k-means**

_(slide pp. 58–87)_

#### **5.1. Perché il clustering**

Il k-means è un metodo **non supervisionato** (p. 58). **Compiti tipici** (p. 59): si riceve un dataset **non etichettato** e bisogna

- definire **nuovi tipi di clienti** dai dati dei clienti;
- capire dai **segnali e dalle vibrazioni** di un motore se ci sono **stati di guasto**;
- capire dai **log di un server** se ci sono **momenti critici** e **attacchi**.

Una buona risposta è il **clustering**.

**Perché un metodo automatico** (p. 60). Nelle applicazioni è **raro** poter individuare i cluster **scrivendo regole a mano**, perché la **dimensionalità** è molto alta, non si è (ancora) **esperti** del campo (espressione genica in medicina, parametri complessi in finanza, …) e i dati sono **tantissimi**.

> 📌 **Clustering** (p. 61). Il compito di **raggruppare** un insieme di oggetti in modo che gli oggetti dello **stesso gruppo** (cluster) siano **più simili** tra loro (posizione, feature, forme, …) che a quelli degli **altri gruppi**. È ciò che fa naturalmente la **Gestalt** umana (Lezione 02, §6).

**Esempio** (p. 63): estrarre gruppi da un dataset non supervisionato di volti, secondo attributi come bambino/adulto, maschio/femmina, felice/neutro/triste, capelli lunghi/calvo, barba/rasato, occhiali da sole/occhiali/nessuno.

<!-- SOURCE_VISUAL id="IS19-V19" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="62" type="grafico" description="Esempio di clustering di punti" reason="Pagina con solo grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V20" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="63" type="immagini" description="Volti raggruppati per attributi: età, sesso, espressione, capelli, barba, occhiali" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché si fa clustering** (p. 64):

| Scopo | Esempi |
|---|---|
| **riassumere i dati** | guardare grandi quantità di dati; compressione o denoising a patch confrontando i **centroidi** con il dataset; rappresentare un vettore continuo con il **numero del suo cluster**; vedere le transizioni quando i dati sono molto densi |
| **contare** | **istogrammi** di texture, colori, vettori SIFT |
| **segmentazione** | separare un’immagine in **regioni** diverse |
| **predizione** | immagini dello stesso cluster possono avere la **stessa etichetta** |

#### **5.2. L’algoritmo**

_(slide pp. 65–73)_

Le pp. 65–70 mostrano l’algoritmo passo per passo, con distanza **euclidea** e $k = 3$ centri $k_1, k_2, k_3$:

1. **inizializzazione casuale** dei centri;
2. ogni punto viene **assegnato** al centro più vicino;
3. ogni centro viene **spostato** nella media dei punti assegnati;
4. si ripetono assegnazione e spostamento;
5. **condizione di uscita**: i centri **non si spostano più**.

<!-- SOURCE_VISUAL id="IS19-V21" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="65" type="grafico" description="k-means passo 1: inizializzazione casuale dei tre centri" reason="Posizione dei centri visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V22" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="70" type="grafico" description="k-means passo 5: i centri non si spostano più, condizione di uscita" reason="Cluster finali visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **L’obiettivo del k-means** (pp. 71–72, formula standard). Date $n$ osservazioni $(\mathbf{x}_1, \dots, \mathbf{x}_n)$, il k-means cerca di dividerle in $k \le n$ insiemi $S = \{S_1, \dots, S_k\}$ che **minimizzano la somma dei quadrati entro i cluster**:
> $$\arg\min_{S} \sum_{i=1}^{k} \sum_{\mathbf{x} \in S_i} \lVert \mathbf{x} - \boldsymbol{\mu}_i \rVert^2, \qquad \boldsymbol{\mu}_i = \frac{1}{|S_i|} \sum_{\mathbf{x} \in S_i} \mathbf{x}$$
> dove $\boldsymbol{\mu}_i$ è il **centro del cluster**. È **facile** calcolare le $\boldsymbol{\mu}$ date le $S$, e viceversa: l’algoritmo alterna i due calcoli.

**L’algoritmo di partizione** (p. 73):

1. decidere il valore di $k$;
2. inizializzare i $k$ centri (a caso, se necessario);
3. decidere l’appartenenza degli $N$ oggetti **assegnandoli al centro più vicino**;
4. **ristimare** i $k$ centri, assumendo corrette le appartenenze trovate;
5. se **nessun oggetto** ha cambiato appartenenza nell’ultima iterazione, **uscire**; altrimenti tornare al passo 3.

> 💡 **Un’iterazione a mano (nota didattica).** Punti 1D: 1, 2, 3, 10, 11, 12, con $k = 2$ e centri iniziali 1 e 2.
> - **Assegnazione**: {1} al centro 1; {2, 3, 10, 11, 12} al centro 2.
> - **Aggiornamento**: $\mu_1 = 1$, $\mu_2 = (2+3+10+11+12)/5 = 7{,}6$.
> - **Assegnazione**: {1, 2, 3} sono più vicini a 1, {10, 11, 12} a 7,6.
> - **Aggiornamento**: $\mu_1 = 2$, $\mu_2 = 11$.
> - Nessun punto cambia gruppo al giro successivo: **uscita**, con i due cluster naturali.

#### **5.3. Pregi e limiti**

_(slide pp. 74–79)_

> ⚠️ **Minimi locali e outlier** (pp. 74–75). Il k-means **converge a un minimo locale**, non necessariamente a quello **globale**: con un’inizializzazione sfortunata i centri possono fermarsi in una configurazione peggiore. Inoltre è sensibile agli **outlier**, che spostano i centri.

> 💡 **Il rimedio pratico (nota didattica).** Si esegue il k-means **più volte** con inizializzazioni diverse e si tiene la soluzione con la somma dei quadrati più bassa; l’inizializzazione **k-means++** sceglie i centri iniziali distanti tra loro e riduce i risultati cattivi. In scikit-learn il parametro `n_init` fa proprio questo.

<!-- SOURCE_VISUAL id="IS19-V23" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="74" type="grafico" description="k-means fermo in un minimo locale invece del minimo globale" reason="Configurazioni dei centri visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V24" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="75" type="grafico" description="Effetto degli outlier sui centri del k-means" reason="Outlier e centri visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| **Punti di forza** (p. 76) | **Debolezze** (p. 77) |
|---|---|
| **relativamente efficiente**: $O(tkn)$, con $n$ oggetti, $k$ cluster, $t$ iterazioni, e di solito $k, t \ll n$ | applicabile **solo se la media è definita**: che media hanno i dati **categorici** come («mela», 90 mm), («limone», 45 mm), («arancia», 55 mm)? |
| **converge sempre**, anche se a un minimo locale | bisogna **specificare $k$** in anticipo |
| si adatta facilmente a **nuovi esempi** | **non gestisce** bene dati **rumorosi** e **outlier** |
| secondo la slide, si generalizza a cluster di **forme e dimensioni diverse**, come quelli ellittici | **non adatto** a cluster di forma **non convessa** (la slide confronta $k = 3$ e $k = 5$) |

> ⚠️ **Cluster ellittici.** Il k-means **standard** usa la distanza euclidea dal centro e tende a produrre cluster **sferici** e di dimensioni simili; per cluster ellittici o di dimensioni molto diverse funziona male. La generalizzazione citata dalla slide richiede **varianti**, come la distanza di Mahalanobis (Lezione 08, §2) o i modelli a misture gaussiane.

La p. 78 confronta il **comportamento di diversi metodi di clustering** su diversi dataset.

<!-- SOURCE_VISUAL id="IS19-V25" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="78" type="griglia di grafici" description="Comportamento di diversi metodi di clustering su diversi dataset" reason="Griglia di risultati visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Clustering esclusivo e sovrapposto** (p. 79):

| **Esclusivo** | **Sovrapposto** |
|---|---|
| ogni punto appartiene a **esattamente un cluster**, senza ambiguità (per esempio **k-means**): partizioni **distinte e non sovrapposte** | i punti possono appartenere a **più cluster contemporaneamente** (per esempio **Fuzzy C-Means**), riflettendo scenari reali in cui le categorie **si intersecano** |
| frontiera **«rigida»** | appartenenza **«morbida»** o condivisa |

La scelta dipende dall’**applicazione**.

#### **5.4. Scegliere $k$ e usare i cluster**

_(slide pp. 80–87)_

**Come scegliere $k$** (pp. 80–81):

- una **conoscenza a priori**? **Usarla!**
- **$k$ troppo basso**: **under-clustering**, cluster meno numerosi dei raggruppamenti naturali;
- **$k$ troppo alto**: **over-clustering**, cluster più numerosi dei raggruppamenti naturali;
- nell’apprendimento non supervisionato il **vero $k$ non è noto**: si può fissare un $k$ piccolo per **studiare la struttura** dei dati e **confrontare rappresentazioni** di feature diverse;
- esistono tecniche avanzate: il **metodo del gomito** e il **silhouette score**.

<!-- SOURCE_VISUAL id="IS19-V26" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="80" type="grafico" description="Under-clustering e over-clustering" reason="Cluster visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Pipeline** (p. 82). Dati grezzi → **ispezione visiva** → **codifica** (categorico → numerico) → **scalatura** → **clustering** (k-means: metodo, metrica, numero $K$ di cluster) → **PCA** (**solo per la visualizzazione**, Lezione 18, §2.2).

> 📌 **Metodo del gomito** (p. 83). Si traccia la **somma dei quadrati entro i cluster** (WCSS) in funzione del numero di cluster $k$. Il $k$ ottimale è il «**punto di gomito**», dove la **diminuzione** della WCSS **rallenta**: aggiungere altri cluster dà rendimenti decrescenti. Il gomito si può individuare anche con una **regressione lineare** sugli ultimi punti.

> 💡 **Perché serve il gomito (nota didattica).** La WCSS **diminuisce sempre** all’aumentare di $k$, e vale 0 con $k = n$ (ogni punto è il suo centro): scegliere il $k$ con la WCSS minima porterebbe sempre all’over-clustering. Il gomito cerca invece il punto oltre il quale un cluster in più «spiega» poco.

**Metodi avanzati** (p. 84):

| Metodo | Idea |
|---|---|
| **silhouette score** | misura la **coerenza** dei cluster |
| **gap statistic** | confronta con una **distribuzione nulla** (dati senza struttura) |
| **indice di Davies-Bouldin** | misura **separazione** e **compattezza** dei cluster |
| **BIC/AIC** per i GMM | criteri di selezione per i **modelli a misture gaussiane**: bilanciano bontà del fit e complessità, **penalizzando** i parametri in più |
| **indice di Calinski-Harabasz** | rapporto tra varianza **tra** i cluster e **dentro** i cluster |

> 📌 **Silhouette (nota didattica).** Per un punto $i$, sia $a(i)$ la distanza media dagli altri punti del **suo** cluster e $b(i)$ la distanza media dai punti del cluster **più vicino** tra gli altri:
> $$s(i) = \frac{b(i) - a(i)}{\max\big(a(i),\, b(i)\big)} \in [-1, 1]$$
> Vicino a 1: il punto sta bene nel suo cluster; vicino a 0: sta sul confine; negativo: probabilmente è nel cluster sbagliato. Si sceglie il $k$ con la **silhouette media più alta**.

**Come gestire un nuovo campione?** (pp. 85–86). Gli algoritmi di clustering **non classificano** direttamente i nuovi campioni: scoprono una **struttura** che va **interpretata**.

- Un modello di clustering **non assegna etichette semantiche**: i nuovi campioni non vengono «classificati», ma **posizionati** nello spazio delle feature.
- Va applicata **la stessa pre-elaborazione** (feature e scalatura): **stesso spazio delle feature, stesse metriche**.
- Ogni cluster è rappresentato da un **centroide** (o prototipo); il nuovo campione si assegna al **cluster più vicino**:
$$k^{*} = \arg\min_{k} \lVert \mathbf{x}_{\text{new}} - \boldsymbol{\mu}_k \rVert$$
- Nel clustering non si chiede «**di che classe è?**», ma «**a quale gruppo appartiene?**».

Nell’esempio della slide le etichette «cluster normale» e «cluster di attacco» sono assegnate **a posteriori**, solo per interpretazione e visualizzazione: **l’algoritmo non le conosce** e non le usa.

> ⚠️ **Il clustering non è rilevamento di anomalie** (p. 87). Il clustering **trova una struttura**, ma **non definisce** le anomalie. Le anomalie sono piuttosto:
> - **regioni a bassa densità**;
> - punti **lontani dai centroidi**;
> - cluster **piccoli o isolati**.

<!-- SOURCE_VISUAL id="IS19-V27" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="83" type="grafico" description="Metodo del gomito: WCSS in funzione del numero di cluster" reason="Curva e gomito visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V28" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="85" type="grafico" description="Nuovo campione posizionato rispetto ai cluster normale e di attacco, etichettati a posteriori" reason="Posizione del campione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Esempi in Python: clienti e sensori di gas**

_(slide pp. 88–106)_

#### **6.1. Clienti: età e punteggio di spesa**

Un esempio semplice in Colab (pp. 88–98) che ripete i passi di EDA della Lezione 11, §2: **pandas** per caricare i dati, selezione delle feature (nel progetto ci si concentra su **età** e **punteggio di spesa**), `.head`, `.shape`, `.info`, `.isnull`, `plt.hist` e un grafico dei dati. Poi il **k-means** (p. 97), con la dimensione dei marker come ulteriore informazione: **in più di 3 dimensioni il clustering aiuta ancora di più**. Il risultato (p. 98) è lo stesso della Lezione 11: un cluster di clienti **giovani** con **alto punteggio di spesa**, a cui mandare **spesso coupon e offerte dedicate**, da verificare con un confronto controllato.

<!-- SOURCE_VISUAL id="IS19-V29" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="97" type="grafico" description="k-means su età e punteggio di spesa con dimensione dei marker" reason="Cluster colorati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. Segnali da sensori reali (non d’esame)**

_(slide pp. 99–106)_

Un esempio da un’**applicazione industriale** (p. 99), presentato come **ripasso**, **non d’esame**. Lo script `gas_clustering.py` usa il file `ethylene_methane2_red.txt` (p. 100), che contiene le **serie temporali** di **16 sensori chimici** esposti a **miscele di gas** a concentrazioni variabili. Colonne: **tempo** (s), concentrazione di **metano** (ppm), concentrazione di **etilene** (ppm), **letture dei 16 sensori**: **16 canali = spazio delle feature a 16 dimensioni**.

> ⚠️ **File non inclusi.** Lo script `gas_clustering.py` e il file di dati citati dalla slide non sono tra i materiali della cartella della lezione: il codice qui riportato è quello leggibile nelle slide.

| Passo (slide) | Contenuto |
|---|---|
| **EDA** (p. 101) | 5 righe × 19 colonne; il dataset è trattato come **non supervisionato**, senza variabili target esplicite; **nessun valore mancante** (0% per ogni colonna) |
| **eliminare le colonne che non sono sensori** (p. 102) | `features_to_drop = ['Time', 'Methane_conc', 'Ethylene_conc']`; `data = data.drop(columns=features_to_drop)`; grafico del primo sensore e scatter plot **S3 contro S6** |
| **distribuzioni** (p. 103) | è sempre utile controllare le distribuzioni: alcuni sensori sono **molto correlati**; distribuzioni **bimodali o multimodali** sono un buon indizio di **«stati»** del sistema; ma bisogna esplorare uno spazio a **16 dimensioni** |
| **t-SNE** (p. 104) | la visualizzazione t-SNE **conferma l’esistenza di stati** («che potente trasformazione di embedding non supervisionata!») |
| **k-means** (p. 105) | `scaler = StandardScaler()`; `data_scaled = scaler.fit_transform(data)`; `kmeans = KMeans(n_clusters=2, random_state=42)`; `kmeans_labels = kmeans.fit_predict(data_scaled)`; colorazione di S3 contro S6 e delle componenti t-SNE secondo i cluster |
| **clustering nelle componenti t-SNE** (p. 106) | naturalmente si può fare il clustering **direttamente** nelle componenti t-SNE |

> 💡 **Non supervisionato per scelta (nota didattica).** Il file contiene le concentrazioni dei gas, che potrebbero fare da target; l’esempio le **toglie** per vedere se i sensori da soli rivelano gli stati del sistema. Le concentrazioni restano utili **dopo**, per verificare che i cluster trovati corrispondano davvero a condizioni diverse. Il k-means lavora sui dati **standardizzati**, perché i 16 sensori hanno scale diverse (§3.3).

<!-- SOURCE_VISUAL id="IS19-V30" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="103" type="grafici" description="Distribuzioni dei 16 sensori con correlazioni e distribuzioni multimodali" reason="Istogrammi visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V31" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="104" type="grafico" description="t-SNE dei 16 sensori che conferma gli stati del sistema" reason="Proiezione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS19-V32" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="106" type="grafico" description="Clustering nelle componenti t-SNE dei segnali dei sensori" reason="Pagina con solo grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Altri approcci: evolutivi, fuzzy, swarm e sistemi ibridi**

_(slide pp. 107–120)_

#### **7.1. Algoritmi genetici**

> 📌 **Algoritmi genetici** (pp. 107–109). Si basano sul principio evolutivo darwiniano della **«sopravvivenza del più adatto»**. Richiedono di saper **riconoscere** una buona soluzione, **non** di sapere **come arrivarci**. Procedura:
> 1. un insieme iniziale di **soluzioni casuali** viene **ordinato** in base alla capacità di risolvere il problema;
> 2. le soluzioni migliori vengono **incrociate** (*crossover*) e **mutate** per formare un nuovo insieme;
> 3. ordinamento e formazione di nuove soluzioni **continuano** finché si trova una soluzione **abbastanza buona**, o finché si raggiunge un altro criterio di arresto.

#### **7.2. Logica fuzzy e swarm intelligence**

**Logica fuzzy contro logica booleana** (p. 110): la slide confronta graficamente i due approcci. La **base di conoscenza** di un sistema fuzzy è formata da **regole fuzzy** e **insiemi fuzzy** (p. 111).

> 📌 **Insiemi e regole fuzzy (nota didattica).** In logica booleana un elemento **appartiene o no** a un insieme. In logica fuzzy ha un **grado di appartenenza** $\mu(x) \in [0, 1]$. Esempio: per l’insieme «temperatura calda», $\mu(18\,°\text{C}) = 0$, $\mu(24\,°\text{C}) = 0{,}5$, $\mu(30\,°\text{C}) = 1$. Una **regola fuzzy** ha la forma «**se** la temperatura è calda **e** l’umidità è alta **allora** la ventola è veloce»: il grado con cui la regola si attiva dipende dai gradi di appartenenza (per esempio il minimo tra i due), e le uscite delle regole si combinano in un valore numerico finale. È lo stesso principio del Fuzzy C-Means del §5.3: appartenenze parziali invece di confini netti.

<!-- SOURCE_VISUAL id="IS19-V33" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="110" type="grafico" description="Logica fuzzy contro logica booleana" reason="Confronto grafico delle funzioni di appartenenza" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Swarm intelligence** (p. 112). Ispirata al comportamento degli **insetti sociali** come le formiche e ad altre società animali, come **stormi di uccelli** o **banchi di pesci**. È caratterizzata da comportamenti **decentralizzati** e **auto-organizzati**, capacità di **risolvere problemi complessi** e di **ottimizzazione**.

#### **7.3. Sistemi intelligenti ibridi**

_(slide pp. 113–120)_

> 📌 **Sistema intelligente ibrido** (p. 114). «Un sistema software che impiega, **in parallelo**, una **combinazione** di metodi e tecniche dell’intelligenza artificiale.»

**Perché ibridi** (p. 115). Calcolo neurale, machine learning, logica fuzzy, algoritmi evolutivi, swarm intelligence, metodi ad agenti e altri (il cosiddetto «**soft computing**») hanno mostrato **punti di forza e limiti**. Bisogna trovare la **combinazione migliore** di tecniche per **mitigare i limiti** nella propria applicazione. Fusioni possibili:

- soft computing / AI con **hard computing** (algoritmi classici);
- tecniche di soft computing / AI **diverse** tra loro;
- **incorporazione** di una tecnica nell’altra (per esempio i sistemi **neuro-fuzzy**).

La p. 116 mostra **ibridi semplici**, con i moduli **in parallelo**, non incorporati. **Esempi di combinazioni** (p. 117): sistemi **neuro-fuzzy**, **genetico-fuzzy**, **genetico-neurali**, **neuro-fuzzy-genetici**, **sistemi esperti ibridi o neurali**, sistemi di **swarm intelligence**, e molti altri.

<!-- SOURCE_VISUAL id="IS19-V34" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="116" type="diagramma" description="Esempi di sistemi ibridi semplici con moduli in parallelo" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4. Ottimizzazione genetica dei parametri**

**Il problema** (p. 118). Anche solo **4–5 parametri** di progetto, come in un sistema ibrido, possono rendere il progetto **impossibile da risolvere con la forza bruta**: gli algoritmi genetici possono essere una buona soluzione. L’esempio della slide:

| Modulo | Parametri |
|---|---|
| **modulo 1: k-NN** | PAR#1 = $k$ |
| **modulo 2: albero di decisione** | PAR#2 = numero di livelli |
| **modulo 3: rete feed-forward** | PAR#3, PAR#4, PAR#5 = numero di neuroni negli strati 1, 2, 3 |

Gli input $X_1, \dots, X_n$ passano per i moduli, che producono **pre-classificazioni** combinate nell’uscita finale.

> 💡 **Perché la forza bruta non basta (nota didattica).** Se ognuno dei 5 parametri può assumere 20 valori, le configurazioni sono $20^5 = 3{,}2$ milioni. Ognuna richiede di addestrare tre modelli e di valutarli con una cross-validation (Lezione 14): anche a un secondo per configurazione sarebbero più di un mese di calcolo. Un algoritmo genetico ne valuta qualche centinaio o migliaio, concentrandosi sulle zone promettenti.

<!-- SOURCE_VISUAL id="IS19-V35" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="118" type="diagramma" description="Sistema ibrido con k-NN, albero di decisione e rete feed-forward e i loro cinque parametri" reason="Collegamenti tra i moduli visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Popolazione e selezione** (p. 119). Ogni **individuo** è un vettore dei 5 parametri, con la sua **fitness**. Nella tabella della slide la colonna è intitolata «Fitness (Accuracy)» e contiene valori come **3,3%**, **3,1%** e **4,1%**; si seleziona il **più adatto**, o gli $N$ più adatti.

> ⚠️ **Accuratezza o errore?** Valori del 3–4% sono verosimilmente **errori** di classificazione, non accuratezze: in quel caso il «più adatto» è quello con il valore **più basso**, coerente con il figlio del passo successivo che scende al 2,8%.

**Crossover e mutazioni** (p. 120):

| Individuo | Ruolo | Valore |
|---|---|---|
| #1 | **madre** | 3,3% |
| #2 | **padre** | 3,1% |
| #12 | **figlio**: una parte dei parametri dalla madre e una dal padre, separate da un **punto di crossover** | 2,8% |
| #12 mutato | **figlio mutato**: **mutazione** in una posizione e con un’intensità casuali, qui $(\text{Par.}\#4, -5)$, cioè 5 neuroni in meno nel secondo strato | 2,8% |

<!-- SOURCE_VISUAL id="IS19-V36" source="Lesson_19_Bayes-Optimal_kNN_Clustering_Intelligent_systems_types_NN_Fuzzy_Evo_Others.pdf" page="120" type="tabella" description="Crossover tra madre e padre e mutazione del figlio sui cinque parametri" reason="Valori dei parametri presenti solo nella tabella" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Errore di Bayes** / **errore di un modello** | limite teorico dovuto alla sovrapposizione delle classi / errore reale, sempre maggiore o uguale |
| **Bayes-ottimo** / **Naive Bayes** | richiede le vere distribuzioni / le stima ipotizzando feature indipendenti, parametri lineari |
| **Eager** / **lazy** | generalizza in addestramento, uso veloce / memorizza tutto, generalizza alla richiesta, uso lento |
| **$k$ piccolo** / **$k$ grande** nel k-NN | frontiere complesse, sensibile al rumore / frontiere lisce, rischio di includere altre classi |
| **Voto semplice** / **voto pesato** | ogni vicino vale 1 / peso $1/d^2$ |
| **$k$ del k-NN** / **$K$ della cross-validation** | numero di vicini / numero di fold |
| **Classificazione** / **clustering** | etichette note, «di che classe è?» / nessuna etichetta, «a quale gruppo appartiene?» |
| **Minimo locale** / **minimo globale** | dove il k-means può fermarsi / la migliore partizione possibile |
| **Esclusivo** / **sovrapposto** | k-means, un solo cluster per punto / Fuzzy C-Means, appartenenza a più cluster |
| **Under-clustering** / **over-clustering** | $k$ troppo basso / $k$ troppo alto |
| **Gomito** / **silhouette** | rallentamento della WCSS / coerenza e separazione dei cluster |
| **Clustering** / **rilevamento di anomalie** | trova la struttura / cerca regioni a bassa densità e punti isolati |
| **Logica booleana** / **fuzzy** | appartenenza 0 o 1 / grado di appartenenza tra 0 e 1 |
| **Ibrido in parallelo** / **incorporato** | moduli affiancati / una tecnica dentro l’altra (neuro-fuzzy) |
| **Crossover** / **mutazione** | combina due genitori / modifica casuale di un parametro |

---

### **9. Sintesi della lezione**

_(slide pp. 121–122)_

> ✅ **Punti principali** (pp. 121–122).
> - **Classificazione Bayes-ottima**: il limite teorico dell’errore, che può essere maggiore di zero; il Naive Bayes come sua approssimazione pratica.
> - **Pro e contro** dei metodi **eager** e **lazy**.
> - **k-NN**: importanza nel pattern recognition, **approssimatore del classificatore Bayes-ottimo**, definizione e codice, problemi di **velocità** e di **dimensionalità**, uso come **strumento di debug**; la classificazione come **insieme di frontiere** nello spazio delle feature (diagramma di Voronoi).
> - **Clustering k-means**: obiettivo, algoritmo, minimi locali, scelta di $k$ (gomito, silhouette), assegnazione di nuovi campioni, differenza dal rilevamento di anomalie.
> - **Principali tipi di intelligenza computazionale**: sistemi **fuzzy**, **evolutivi**, **swarm**, **ibridi**.
> - **Ottimizzazione genetica dei parametri** di un sistema ibrido: popolazione, fitness, crossover e mutazioni.
