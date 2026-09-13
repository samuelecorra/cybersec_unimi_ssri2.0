## **Lezione 20: Maledizione della dimensionalità, perceptron e reti neurali feed-forward**

La ventesima lezione del corso **IS4** comincia con un avvertimento: **aggiungere feature non è sempre utile**, perché al crescere delle dimensioni lo spazio diventa quasi vuoto e i dati necessari crescono in modo esponenziale (la **maledizione della dimensionalità**). Poi entra nelle **reti neurali**: dal neurone biologico al neurone artificiale, dal **perceptron** di Rosenblatt con la sua regola di apprendimento, fino alle **reti multistrato** addestrate con la **backpropagation** e ai suoi problemi, il **gradiente che svanisce** o **esplode**. Un allegato non d’esame svolge a mano un passo di backpropagation sulla rete che risolve lo **XOR**.

Fonte: [PDF della Lezione 20](Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf), 84 pagine, nessun supplemento. Molte slide sono di Ruggero Donida Labati e Fabio Scotti. Le formule che nelle slide sono rese in modo illeggibile dal testo estratto sono riportate in forma standard; i calcoli numerici dell’allegato sono stati ricontrollati. Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *Curse of dimensionality, Neurons, Perceptrons, Feedforward neural networks and their Learning Methods*. L’indice (p. 2) elenca:

1. **maledizione della dimensionalità**;
2. **neuroni**: naturali e artificiali;
3. il **perceptron**;
4. **reti neurali feed-forward multistrato**;
5. altri tipi di reti neurali;
6. esempio in MATLAB.

---

### **1. La maledizione della dimensionalità**

_(slide pp. 3–13)_

#### **1.1. Aggiungere feature non è sempre una buona idea**

Ogni volta che si vuole aggiungere una nuova feature, conviene pensarci (p. 3).

> 📌 **Feature irrilevanti** (p. 4). Aggiungere feature **irrilevanti o rumorose** aumenta la dimensionalità **senza aggiungere informazione utile**, e in certi casi può **disgregare un cluster denso**. Nell’esempio della slide due cluster sono **ben separati** nello spazio $x_1$–$x_2$; aggiungendo una feature **casuale** $x_3$, i punti si **disperdono**, le **distanze** tra campioni diventano meno significative e classificare o raggruppare diventa **più difficile**.

<!-- SOURCE_VISUAL id="IS20-V01" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="4" type="grafico" description="Due cluster separati in x1-x2 che si disperdono aggiungendo una feature casuale x3" reason="Nuvole di punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

C’è anche un’altra ragione (p. 5): creare o aggiungere feature **può essere utile**, ma con **svantaggi rilevanti**.

> 📌 **Maledizione della dimensionalità** (p. 6). Aggiungere attributi aiuta sempre chi apprende? Più informazione può **far male**? **A volte sì.** Il termine indica vari fenomeni che si presentano analizzando e organizzando dati in **spazi ad alta dimensionalità**, spesso con centinaia o migliaia di dimensioni. Esempio: un’immagine **Full HD** $1920 \times 1080$ elaborata da una rete è formata da **2 073 600 pixel**: uno spazio a **2 milioni di dimensioni**.

#### **1.2. L’esempio dei «bin»**

**L’algoritmo dei bin** (p. 7):

1. si **divide** lo spazio delle feature **uniformemente** in intervalli (bin);
2. per classificare un nuovo esempio si trova **il bin in cui cade** e gli si assegna la **classe prevalente** in quel bin.

Con **una feature** divisa in **3 bin** le classi si sovrappongono; si aggiunge allora **un’altra feature**…

> 📌 **Il problema** (pp. 8–9). All’aumentare della dimensionalità, il **numero di bin** necessari a coprire lo spazio cresce **esponenzialmente**, e **non ci sono abbastanza dati** per popolarli. Trovare la classe prevalente in ogni bin, o le **probabilità condizionate** $p(x \mid C)$, diventa **molto difficile** in alta dimensionalità. Al crescere di $D$ la **quantità di dati necessaria cresce esponenzialmente** con $D$: si costruisce uno **spazio quasi vuoto**.

<!-- SOURCE_VISUAL id="IS20-V02" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="7" type="grafico" description="Algoritmo dei bin con una feature divisa in 3 intervalli e classi sovrapposte" reason="Bin e punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V03" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="8" type="grafico" description="Bin in 1D, 2D e 3D con dati sempre più sparsi" reason="Griglie dei bin visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I numeri** (p. 10). Con **10 bin per dimensione**:

$$\#\text{bin} = 10^{d}$$

| Dimensioni $d$ | Numero di bin |
|---|---|
| 1 | 10 |
| 2 | $10 \times 10 = 100$ |
| 3 | 1000 |
| 1000 | $10^{1000}$ |

Il numero stimato di **atomi nell’universo** è tra $10^{78}$ e $10^{82}$: con 1000 dimensioni ci sono **enormemente più intorni** che atomi.

> 💡 **Quanti dati servirebbero (nota didattica).** Per avere in media anche solo **un campione per bin** servono $10^d$ campioni: 100 in 2D, un milione in 6D, $10^{10}$ in 10D. Un dataset industriale di 100 000 campioni con 10 feature lascia quindi vuoti 99 999 bin su 100 000. È per questo che nessun metodo può «riempire» lo spazio: deve **sfruttare una struttura** dei dati.

<!-- SOURCE_VISUAL id="IS20-V04" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="10" type="schema" description="Numero di bin 10 alla d confrontato con il numero di atomi nell’universo" reason="Formule e figure rese come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Come affrontarla**

> 📌 **Come affrontare la maledizione della dimensionalità** (p. 11).
> - Esistono comunque **tecniche efficaci** in spazi ad alta dimensionalità.
> - I **dati reali** sono spesso **confinati** in una regione dello spazio con una **dimensionalità effettiva più bassa**.
> - I dati reali presentano tipicamente proprietà di **regolarità** (*smoothness*).
> - Quando è possibile: **selezione delle feature**, **riduzione della dimensionalità** (Lezione 18), **controllo della complessità** del modello.
> - Molti classificatori possono essere **molto influenzati** dalla maledizione della dimensionalità, altri no: **bisogna conoscere il proprio classificatore!**

> 💡 **La dimensionalità effettiva (nota didattica).** Le immagini $28 \times 28$ di cifre scritte a mano vivono in uno spazio a 784 dimensioni, ma una configurazione casuale di pixel non sembra quasi mai una cifra: le cifre vere occupano una «superficie» di poche decine di dimensioni (spessore del tratto, inclinazione, forma). PCA e t-SNE funzionano proprio perché trovano questa struttura (Lezione 18, §2–§3).

**Adattamento polinomiale** (pp. 12–13). La p. 12 mostra l’adattamento di polinomi di grado crescente ai dati.

- In **MATLAB** la funzione `fitlm` può **espandere automaticamente** le feature originali con i termini polinomiali, comprese le **interazioni**, specificando l’opzione `'polyN'`.
- In **Python** scikit-learn segue un progetto **più modulare**: bisogna **creare esplicitamente** le feature polinomiali con `PolynomialFeatures` prima di addestrare un `LinearRegression`. Il modello è **lineare nei parametri**, anche se l’equazione ovviamente **non è lineare** nelle feature.

> 💡 **Le feature polinomiali esplodono (nota didattica).** Con $d$ feature, un polinomio di grado $p$ con tutte le interazioni ha
> $$\binom{d + p}{p}$$
> termini, costante compresa. Con $d = 10$ e $p = 3$ sono $\binom{13}{3} = 286$ coefficienti; con $d = 100$ e $p = 3$ sono $\binom{103}{3} = 176\,851$. Espandere le feature per catturare non linearità fa crescere rapidamente i parametri, e con loro la fame di dati: è un altro volto della maledizione.

<!-- SOURCE_VISUAL id="IS20-V05" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="12" type="grafico" description="Adattamento di polinomi di grado crescente ai dati" reason="Curve di adattamento visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Dal neurone biologico al neurone artificiale**

_(slide pp. 14–22)_

#### **2.1. Il cervello e i neuroni**

> 📌 **Rete neurale** (p. 15). Si può definire come un **modello di ragionamento** basato sul **cervello umano**. Il cervello è formato da un insieme **densamente interconnesso** di cellule nervose, unità elementari di elaborazione dell’informazione chiamate **neuroni**. Secondo la slide contiene circa **10 miliardi di neuroni** e **60 000 miliardi di connessioni** (sinapsi); usando molti neuroni **contemporaneamente**, svolge le sue funzioni molto più velocemente dei computer più veloci. E consuma **solo 20 W** (p. 16).

> ⚠️ **Stime diverse (nota didattica).** Le stime più citate oggi indicano circa **86 miliardi** di neuroni e un numero di sinapsi dell’ordine di $10^{14}$–$10^{15}$; la Lezione 04, §3.1, parlava infatti di circa 100 000 miliardi di «parametri». Le cifre della slide, riprese da testi classici, sono più basse, ma il messaggio non cambia: un sistema di scala enorme che funziona con la potenza di una lampadina.

<!-- SOURCE_VISUAL id="IS20-V06" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="16" type="immagine" description="Il cervello: miliardi di neuroni e sinapsi con soli 20 W di consumo" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il neurone** (p. 17). Ogni neurone ha una **struttura molto semplice**, ma l’**insieme** di questi elementi ha una **potenza di elaborazione enorme**. Un neurone è formato da un **corpo cellulare** (soma), da un certo numero di fibre, i **dendriti**, e da una singola fibra lunga, l’**assone**.

<!-- SOURCE_VISUAL id="IS20-V07" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="17" type="illustrazione" description="Struttura del neurone: soma, dendriti, assone e connessioni" reason="Illustrazione anatomica della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. L’analogia**

**Analogia tra reti neurali biologiche e artificiali** (pp. 18–19):

| Rete neurale biologica | Rete neurale artificiale |
|---|---|
| **soma** | **neurone** |
| **dendrite** | **ingresso** |
| **assone** | **uscita** |
| **sinapsi** | **peso** |

Una rete artificiale (p. 20) ha uno **strato di ingresso** che riceve i **segnali di input**, uno o più **strati intermedi** e uno **strato di uscita** che produce i **segnali di output**.

<!-- SOURCE_VISUAL id="IS20-V08" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="18" type="illustrazione" description="Somiglianza tra neurone biologico e neurone artificiale" reason="Pagina con sola illustrazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V09" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="20" type="diagramma" description="Rete neurale artificiale con strato di ingresso, intermedio e di uscita" reason="Struttura della rete visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. Caratteristiche delle reti neurali artificiali**

_(slide pp. 21–22)_

| Caratteristica | Significato (slide) |
|---|---|
| **apprendere dall’esperienza** | risolvere problemi **complessi**, difficili da formalizzare, ma con **molti dati** che li descrivono |
| **generalizzare dagli esempi** | **interpolare** a partire dall’apprendimento precedente e dare la risposta corretta su dati **mai visti** |
| **sviluppo rapido di applicazioni** | le reti sono **macchine generiche**, abbastanza **indipendenti dalla conoscenza del dominio** |
| **adattabilità** | si adattano a un **ambiente che cambia**, se progettate correttamente |
| **efficienza computazionale** | l’**addestramento** richiede molta potenza di calcolo, ma una rete addestrata **non chiede quasi nulla** in fase di uso (recall) |
| **non linearità** | non si basano su **ipotesi lineari** sul mondo reale: elaborazione avanzata degli input |

> 💡 **«Generalizzare» con giudizio.** L’interpolazione funziona **dentro** la regione coperta dagli esempi di training; fuori, la rete **estrapola** senza garanzie (Lezione 06, §7.2, il buco topologico). E l’«indipendenza dal dominio» non vuol dire che la conoscenza del problema sia inutile: è il quarto driver della Lezione 02, §9.

---

### **3. Reti neurali artificiali: una panoramica**

_(slide pp. 23–33)_

La p. 24 mostra **applicazioni tipiche** delle reti neurali.

**Reti neurali** (pp. 25–26):

- l’ispirazione **originale** era il cervello umano; oggi l’enfasi è sulla loro **utilità come strumento di calcolo**;
- esistono molti paradigmi utili, ma la discussione si limita alla **rete feed-forward**, il paradigma **più popolare**.

> 📌 **Rete feed-forward** (p. 26). Una struttura **a strati** formata da un certo numero di elementi di elaborazione **omogenei** e **semplici**, ma **non lineari**. Tutta l’elaborazione è **locale** a un elemento di elaborazione ed è **asincrona**.

<!-- SOURCE_VISUAL id="IS20-V10" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="24" type="immagini" description="Applicazioni tipiche delle reti neurali" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La p. 27 presenta le reti a **funzioni di base radiale** (RBF).

> 💡 **RBF in una riga (nota didattica).** Invece del prodotto scalare pesato, i neuroni nascosti di una rete RBF rispondono in base alla **distanza** dell’input da un centro, per esempio con $\varphi(\mathbf{x}) = \exp\big(-\lVert \mathbf{x} - \mathbf{c} \rVert^2 / 2\sigma^2\big)$: ogni neurone è acceso solo vicino al suo centro. Per questo la Lezione 19, §2.2, le classifica tra i metodi neurali **basati su istanze**.

<!-- SOURCE_VISUAL id="IS20-V11" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="27" type="diagramma" description="Rete neurale a funzioni di base radiale" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Addestramento** (pp. 28–29). Durante l’addestramento la rete è **costretta ad aggiustare i suoi parametri** in modo che la **risposta** ai dati di ingresso diventi **più vicina alla risposta desiderata**.

<!-- SOURCE_VISUAL id="IS20-V12" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="29" type="diagramma" description="Addestramento della rete: confronto tra uscita e risposta desiderata" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le pp. 30–33 introducono le **reti di deep learning**, il confronto tra **ML classico e deep learning** (il deep learning **ha bisogno di molti dati**) e le **reti convoluzionali**.

> 💡 **Il grafico «dati contro prestazioni» (nota didattica).** Il confronto classico mostra che con **pochi dati** i metodi classici sono competitivi o migliori, mentre con **moltissimi dati** le prestazioni del deep learning continuano a salire quando quelle dei metodi classici si appiattiscono. È coerente con la maledizione della dimensionalità e con il conteggio dei parametri (Lezione 07): più parametri richiedono più dati.

<!-- SOURCE_VISUAL id="IS20-V13" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="30" type="diagramma" description="Reti di deep learning" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V14" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="31" type="schema" description="Machine learning classico contro deep learning" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V15" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="32" type="grafico" description="Prestazioni del ML classico e del deep learning in funzione della quantità di dati" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V16" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="33" type="diagramma" description="Architettura di una rete neurale convoluzionale" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Il neurone come elemento di calcolo**

_(slide pp. 34–37)_

**Schema del neurone** (p. 35): i **segnali di input** $x_1, \dots, x_n$ arrivano con i **pesi** $w_1, \dots, w_n$; il neurone applica una funzione $f(\cdot)$ e produce il **segnale di output** $Y$, che può raggiungere più neuroni.

> 📌 **Il modello base del neurone** (p. 36). Il neurone calcola la **somma pesata** degli input e la **confronta con una soglia** $\theta$. Se l’ingresso netto è **minore** della soglia, l’uscita è $-1$; se è **maggiore o uguale**, il neurone si **attiva** e l’uscita vale $+1$. È la funzione di attivazione **segno** (*sign function*):
> $$X = \sum_{i=1}^{n} x_i w_i \qquad Y = \begin{cases} +1 & \text{se } X \ge \theta \\ -1 & \text{se } X < \theta \end{cases}$$
> Lo schema ha due blocchi: il **combinatore lineare** (la somma pesata) e il **limitatore rigido** (*hard limiter*).

<!-- SOURCE_VISUAL id="IS20-V17" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="35" type="diagramma" description="Neurone con input pesati, funzione di attivazione e uscite" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V18" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="36" type="diagramma" description="Modello base del neurone con combinatore lineare, soglia e limitatore rigido" reason="Formule e schema resi come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Funzioni di attivazione** (p. 37). La slide le confronta con alcune annotazioni: **non lineare**, **non «ben» derivabile**, **senza saturazione** (*non clipping*), **intervallo di ingresso limitato**, capacità di gestire gli **«spike»**.

> 📌 **Le funzioni più comuni (nota didattica).**
>
> | Funzione | Formula | Uscita | Note |
> |---|---|---|---|
> | **gradino** | $\text{step}(X) = 1$ se $X \ge 0$, altrimenti $0$ | $\{0, 1\}$ | non derivabile nello 0, derivata nulla altrove |
> | **segno** | $\text{sign}(X) = +1$ se $X \ge 0$, altrimenti $-1$ | $\{-1, +1\}$ | come il gradino |
> | **sigmoide** | $\sigma(X) = \dfrac{1}{1 + e^{-X}}$ | $(0, 1)$ | derivabile: $\sigma'(X) = \sigma(X)\,(1 - \sigma(X))$, al massimo 0,25 |
> | **tangente iperbolica** | $\tanh(X)$ | $(-1, 1)$ | derivata al massimo 1, satura agli estremi |
> | **ReLU** | $\max(0, X)$ | $[0, +\infty)$ | non satura per $X > 0$; non derivabile nello 0; «neuroni morti» per $X < 0$ |
>
> La derivabilità conta perché l’addestramento con la backpropagation (§6) usa la **derivata** della funzione di attivazione.

<!-- SOURCE_VISUAL id="IS20-V19" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="37" type="grafico" description="Funzioni di attivazione con annotazioni su non linearità, derivabilità e saturazione" reason="Grafici delle funzioni visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Il perceptron**

_(slide pp. 38–52)_

#### **5.1. Un solo neurone può imparare?**

> 📌 **Perceptron** (p. 39). Nel **1958** Frank **Rosenblatt** introdusse un algoritmo di addestramento che fu la **prima procedura** per addestrare una semplice rete neurale artificiale: il **perceptron**. È la **forma più semplice** di rete neurale: un **singolo neurone** con **pesi sinaptici regolabili** e un **limitatore rigido**.

La p. 40 mostra il **perceptron a un solo strato con due ingressi**.

> 📌 **Obiettivo: la classificazione binaria** (p. 41). Il perceptron classifica gli input $x_1, \dots, x_n$ in **una di due classi**, $A_1$ e $A_2$. Nel perceptron elementare lo spazio a $n$ dimensioni è diviso da un **iperpiano** in due regioni di decisione. L’iperpiano è definito dalla funzione **linearmente separabile**
> $$\sum_{i=1}^{n} x_i w_i - \theta = 0$$
> Con due ingressi è la **retta** $x_1 w_1 + x_2 w_2 - \theta = 0$, cioè la forma della p. 41 $x_2 = m\,x_1 + q$ con $m = -w_1/w_2$ e $q = \theta/w_2$; con tre ingressi è un **piano** $x_1 w_1 + x_2 w_2 + x_3 w_3 - \theta = 0$ (p. 42).

<!-- SOURCE_VISUAL id="IS20-V20" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="40" type="diagramma" description="Perceptron a un solo strato con due ingressi" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V21" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="42" type="grafico" description="Separabilità lineare: retta per il perceptron a due ingressi e piano per quello a tre" reason="Regioni di decisione visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Come impara**

_(slide pp. 43–47)_

> 📌 **Imparare significa…** (p. 43) fare **piccoli aggiustamenti dei pesi** per ridurre la **differenza** tra l’uscita **effettiva** e quella **desiderata** del perceptron. I pesi iniziali sono assegnati **a caso**, di solito nell’intervallo $[-0{,}5;\ 0{,}5]$, e poi aggiornati per ottenere uscite coerenti con gli esempi di training.

**L’errore** (p. 44). Se all’iterazione $p$ l’uscita effettiva è $Y(p)$ e quella desiderata $Y_d(p)$, l’errore è
$$e(p) = Y_d(p) - Y(p), \qquad p = 1, 2, 3, \dots$$
Qui l’iterazione $p$ è il **$p$-esimo esempio** di training presentato al perceptron. Se $e(p)$ è **positivo** bisogna **aumentare** l’uscita $Y(p)$; se è **negativo**, **diminuirla**.

> ⚠️ **Iterazione ed epoca** (p. 44). Un’**epoca** è quando l’**intero dataset** passa attraverso la rete **una volta**. Una singola iterazione $p$, con un solo esempio, **non è un’epoca**.

> 📌 **La regola di apprendimento del perceptron** (p. 45). Proposta da Rosenblatt (la slide la data al **1960**):
> $$w_i(p + 1) = w_i(p) + \alpha \cdot x_i(p) \cdot e(p)$$
> dove $\alpha$ è il **tasso di apprendimento**, una costante **positiva minore di 1**. Il peso **corrente** $w_i(p)$ diventa il peso della **prossima iterazione** $w_i(p+1)$.

> 💡 **Perché la regola ha senso (nota didattica).** Se l’uscita doveva essere 1 ma è 0 ($e = +1$), ogni peso con ingresso positivo **cresce** di $\alpha\, x_i$: la prossima volta la somma pesata sarà più grande e il neurone più vicino ad attivarsi. Se un ingresso vale 0, il suo peso **non cambia**: quell’ingresso non ha contribuito all’errore.

**I 4 passi dell’algoritmo di training del perceptron** (pp. 46–47):

1. **Inizializzazione**: pesi $w_1, \dots, w_n$ e soglia $\theta$ con numeri casuali in $[-0{,}5;\ 0{,}5]$.
2. **Attivazione**: applicare gli ingressi $x_1(p), \dots, x_n(p)$ con l’uscita desiderata $Y_d(p)$, e calcolare l’uscita effettiva
$$Y(p) = \text{step}\left[ \sum_{i=1}^{n} x_i(p)\, w_i(p) - \theta \right]$$
dove $n$ è il numero di ingressi e **step** la funzione a gradino.
3. **Aggiornamento dei pesi** con la **regola delta**:
$$\Delta w_i(p) = \alpha \cdot x_i(p) \cdot e(p) \qquad w_i(p + 1) = w_i(p) + \Delta w_i(p)$$
4. **Iterazione**: incrementare $p$ di uno, tornare al passo 2 e ripetere fino alla **convergenza**.

> 📌 **Molto importante** (p. 47). Questo passaggio, la correzione dei pesi proporzionale a **ingresso** ed **errore**, è **la base dei metodi di apprendimento delle reti neurali**.

#### **5.3. Esempio: l’operazione AND**

_(slide pp. 48–51)_

Il perceptron impara l’**AND logico** (pp. 48–51), con tasso di apprendimento $\alpha = 0{,}1$. Nel passo mostrato dalla slide i pesi correnti sono $w_1 = 0{,}3$ e $w_2 = -0{,}1$, l’ingresso è $(x_1, x_2) = (1, 0)$, l’uscita desiderata è 0 ma il perceptron risponde 1, quindi $e = -1$:

$$\Delta w_1 = \alpha \cdot x_1 \cdot e = 0{,}1 \cdot 1 \cdot (-1) = -0{,}1 \;\Rightarrow\; w_1 = 0{,}3 - 0{,}1 = 0{,}2$$
$$\Delta w_2 = \alpha \cdot x_2 \cdot e = 0{,}1 \cdot 0 \cdot (-1) = 0 \;\Rightarrow\; w_2 = -0{,}1 \;\text{(nessun cambiamento)}$$

L’ingresso $x_2 = 0$ **non produce correzione** (nella slide il secondo aggiornamento è scritto per errore «Final w1» invece di «Final w2»). Quando l’**errore è zero** su tutti gli esempi **non ci sono più aggiornamenti** (p. 51).

> 💡 **La prima epoca completa (nota didattica).** Nella versione classica dell’esempio (testo di Negnevitsky) la soglia è $\theta = 0{,}2$. Con pesi iniziali $w_1 = 0{,}3$, $w_2 = -0{,}1$:
>
> | Ingresso | $Y_d$ | Somma $- \theta$ | $Y$ | $e$ | Pesi dopo |
> |---|---|---|---|---|---|
> | (0, 0) | 0 | $-0{,}2$ | 0 | 0 | 0,3; −0,1 |
> | (0, 1) | 0 | $-0{,}3$ | 0 | 0 | 0,3; −0,1 |
> | (1, 0) | 0 | $+0{,}1$ | 1 | −1 | 0,2; −0,1 |
> | (1, 1) | 1 | $-0{,}1$ | 0 | +1 | 0,3; 0,0 |
>
> La terza riga è quella della slide. Si ripetono le epoche finché un’intera epoca passa senza errori. La tabella completa delle slide va verificata sull’immagine.

<!-- SOURCE_VISUAL id="IS20-V22" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="48" type="tabella" description="Tabella dell’addestramento del perceptron sull’AND per epoche" reason="Valori della tabella presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4. Il limite: lo XOR**

> ⚠️ **AND e OR sì, XOR no** (p. 52). Nei grafici bidimensionali delle operazioni logiche, **AND** e **OR** si separano con una retta; lo **XOR** (OR esclusivo) no: i punti (0,1) e (1,0) valgono 1, i punti (0,0) e (1,1) valgono 0, e **nessuna retta** li divide. Un perceptron **impara AND e OR**, ma **non lo XOR**, perché la sua frontiera di decisione (una retta) **non è adatta** a quei punti.

> 💡 **Perché nessuna retta funziona (nota didattica).** Serve $w_1 \cdot 0 + w_2 \cdot 0 < \theta$, quindi $\theta > 0$; poi $w_1 \ge \theta$ e $w_2 \ge \theta$; ma allora $w_1 + w_2 \ge 2\theta > \theta$ e il punto (1,1) verrebbe classificato 1 invece di 0. Le condizioni sono **incompatibili**. È il limite reso celebre da Minsky e Papert (1969), superato solo con **più strati** (§6 e §7).

<!-- SOURCE_VISUAL id="IS20-V23" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="52" type="grafico" description="AND, OR e XOR nel piano: i primi due separabili con una retta, lo XOR no" reason="Disposizione dei punti visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Reti multistrato e backpropagation**

_(slide pp. 53–72)_

#### **6.1. Il perceptron multistrato**

> 📌 **Reti neurali multistrato** (p. 54). Un **perceptron multistrato** è una rete neurale feed-forward con **uno o più strati nascosti**. È formata da uno **strato di ingresso** di neuroni sorgente, **almeno uno strato intermedio (nascosto)** di neuroni di calcolo e uno **strato di uscita** di neuroni di calcolo. I segnali di ingresso si propagano **in avanti**, **strato per strato**.

**Che cosa nasconde lo strato intermedio?** (p. 55)

- Uno strato nascosto **nasconde la sua uscita desiderata**: i suoi neuroni **non si osservano** dal comportamento ingresso/uscita della rete.
- **Non esiste un modo ovvio** di sapere quale dovrebbe essere l’uscita desiderata dello strato nascosto.
- Le reti feed-forward **commerciali** hanno tipicamente **3–4 strati** con **1–2 strati nascosti**, ciascuno con **10–1000 neuroni**.
- Le reti **sperimentali** possono avere **5 o più strati**, con **3 o più strati nascosti**, e milioni di neuroni o più.

> ⚠️ **CNN** (p. 55). Le reti convoluzionali hanno **molti più strati**, ma quegli strati **funzionano in modo piuttosto diverso**.

<!-- SOURCE_VISUAL id="IS20-V24" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="54" type="diagramma" description="Perceptron multistrato con strato di ingresso, due strati nascosti e strato di uscita" reason="Struttura della rete visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Apprendimento** (p. 56). Procede come nel perceptron: si presenta un insieme di pattern di training, la rete calcola l’uscita, e se c’è un **errore** (una differenza tra uscita effettiva e desiderata) i pesi vengono **aggiustati** per ridurlo.

> 📌 **Le due fasi della backpropagation** (p. 57).
> 1. Un pattern di training viene presentato allo strato di ingresso e **propagato strato per strato** fino a produrre l’uscita.
> 2. Se l’uscita è diversa da quella desiderata, si calcola l’**errore** e lo si **propaga all’indietro**, dallo strato di uscita verso quello di ingresso; i **pesi vengono modificati** man mano che l’errore si propaga.

> 💡 **Il problema che la backpropagation risolve (nota didattica).** Per il perceptron l’errore è noto perché si conosce l’uscita desiderata. Per un neurone nascosto no (p. 55). La backpropagation **distribuisce** l’errore dell’uscita ai neuroni nascosti in proporzione a quanto ciascuno vi ha contribuito, cioè ai pesi che li collegano all’uscita.

<!-- SOURCE_VISUAL id="IS20-V25" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="58" type="diagramma" description="Rete a tre strati addestrata con la backpropagation" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. I 4 passi dell’algoritmo**

_(slide pp. 59–66)_

**Passo 1: inizializzazione** (p. 59), **neurone per neurone**. Tutti i pesi e le soglie della rete ricevono numeri casuali **uniformemente distribuiti** in un piccolo intervallo:
$$\left( -\frac{2{,}4}{F_i},\; +\frac{2{,}4}{F_i} \right)$$
dove $F_i$ è il **numero totale di ingressi** del neurone $i$ (nell’esempio della slide $F_i = 3$ per un neurone e $F_i = 4$ per un altro).

> 💡 **Perché dividere per il numero di ingressi (nota didattica).** La somma pesata di un neurone con molti ingressi è grande anche con pesi piccoli: se i pesi fossero nello stesso intervallo per tutti, i neuroni con molti ingressi partirebbero **saturi**, con la sigmoide già vicina a 0 o 1 e derivata quasi nulla. Scalare l’intervallo con $1/F_i$ mantiene la somma in una zona in cui il neurone impara. Le inizializzazioni moderne (Xavier/Glorot, He) seguono la stessa idea.

**Passo 2: attivazione** (pp. 60–61). Si applicano gli ingressi $x_1(p), \dots, x_n(p)$ e le uscite desiderate $y_{d,1}(p), \dots, y_{d,l}(p)$.

(a) Uscite dei neuroni **nascosti**, con $n$ ingressi per il neurone $j$:
$$y_j(p) = \text{sigmoid}\left[ \sum_{i=1}^{n} x_i(p)\, w_{ij}(p) - \theta_j \right]$$

(b) Uscite dei neuroni **di uscita**, con $m$ ingressi per il neurone $k$ e $l$ uscite in totale:
$$y_k(p) = \text{sigmoid}\left[ \sum_{j=1}^{m} x_{jk}(p)\, w_{jk}(p) - \theta_k \right]$$

**Passo 3: aggiornamento dei pesi** (pp. 62–65), propagando **all’indietro** gli errori dei neuroni di uscita.

| | Strato di **uscita** | Strato **nascosto** |
|---|---|---|
| **errore** | $e_k(p) = y_{d,k}(p) - y_k(p)$ | — |
| **gradiente dell’errore** | $\delta_k(p) = y_k(p)\,\big[1 - y_k(p)\big]\, e_k(p)$ | $\delta_j(p) = y_j(p)\,\big[1 - y_j(p)\big] \displaystyle\sum_{k=1}^{l} \delta_k(p)\, w_{jk}(p)$ |
| **correzione** | $\Delta w_{jk}(p) = \alpha \cdot y_j(p) \cdot \delta_k(p)$ | $\Delta w_{ij}(p) = \alpha \cdot x_i(p) \cdot \delta_j(p)$ |
| **aggiornamento** | $w_{jk}(p+1) = w_{jk}(p) + \Delta w_{jk}(p)$ | $w_{ij}(p+1) = w_{ij}(p) + \Delta w_{ij}(p)$ |

Le formule dei gradienti $\delta$ sono **non d’esame** (pp. 64–65).

> 📌 **La stessa equazione del perceptron** (pp. 63–65). La correzione ha **la stessa forma** della regola delta, $\Delta w_i(p) = \alpha \cdot x_i(p) \cdot e(p)$: al posto dell’ingresso $x_i$ c’è l’uscita del neurone precedente $y_j$, e al posto dell’errore $e$ c’è il **gradiente dell’errore** $\delta$.

> 💡 **Da dove viene $y(1 - y)$ (nota didattica).** È la **derivata della sigmoide** (§4). Il gradiente $\delta$ è l’errore moltiplicato per quanto il neurone è sensibile in quel punto: se la sigmoide è saturata ($y \approx 0$ o $y \approx 1$), $y(1-y) \approx 0$ e il neurone quasi non impara. Il termine $\sum_k \delta_k w_{jk}$ è l’errore «restituito» al neurone nascosto dai neuroni a cui è collegato.

**Passo 4: iterazione** (p. 66). Si incrementa $p$, si torna al passo 2 e si ripete finché è soddisfatto il **criterio di errore** scelto:

- raggiunto il **numero massimo di epoche**;
- **errore** minore di una **soglia** fissata;
- **gradiente nullo**: nessun miglioramento nei pesi;
- …

#### **6.3. I problemi della backpropagation**

_(slide pp. 67–69)_

> ⚠️ **Instabilità del gradiente** (p. 67). I problemi principali sono il **gradiente che svanisce** e il **gradiente che esplode**. La **scelta della funzione di attivazione** è **cruciale** per il comportamento del gradiente durante l’apprendimento.

> 📌 **Vanishing gradient** (p. 68). Il **gradiente** della funzione di perdita rispetto ai pesi diventa **molto piccolo**, e la rete **smette di fatto di imparare**. Accade tipicamente nelle **reti profonde**, soprattutto con attivazioni come la **sigmoide** o la **tangente iperbolica**. Nell’espressione del gradiente del perceptron multistrato, $\delta_j = y_j(1 - y_j) \sum_k \delta_k w_{jk}$, compaiono i **pesi dello strato successivo**: è importante **controllare l’ampiezza** dei pesi.

> 📌 **Exploding gradient** (p. 69). Il caso opposto: i gradienti diventano **eccessivamente grandi**, portando a un apprendimento **instabile e inefficiente**: gradienti grandi producono **pesi eccessivi**, che a loro volta ingrandiscono i gradienti. La slide mostra anche una funzione di perdita semplice, di un solo peso $w$ e un bias $b$, con **discontinuità** del gradiente.

> 💡 **I numeri del problema (nota didattica).** La derivata della sigmoide vale **al massimo 0,25**. Propagando all’indietro attraverso $L$ strati, il gradiente si moltiplica per un fattore di questo tipo a ogni strato: con pesi dell’ordine di 1, dopo 10 strati si arriva a $0{,}25^{10} \approx 9{,}5 \times 10^{-7}$, e i primi strati non imparano più. Se invece i pesi sono grandi, per esempio 5, il fattore per strato può superare 1 e il prodotto **esplode**. Rimedi diffusi: attivazioni **ReLU**, **inizializzazione** accurata, **normalizzazione** degli strati, **clipping** del gradiente e connessioni residue.

<!-- SOURCE_VISUAL id="IS20-V26" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="68" type="grafico" description="Vanishing gradient con la sigmoide e dipendenza dai pesi dello strato precedente" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V27" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="69" type="grafico" description="Exploding gradient e funzione di perdita di un peso e un bias con discontinuità del gradiente" reason="Superficie della perdita visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4. Una rete feed-forward in MATLAB**

_(slide pp. 70–72)_

Le pp. 70–71 sono grafiche. **Il codice MATLAB** (p. 72):

```matlab
[x,t] = simplefit_dataset;     % dataset di esempio: una funzione 1D da approssimare
net = feedforwardnet(10);      % rete feed-forward con 10 neuroni nascosti
net = train(net,x,t);          % addestramento (backpropagation e varianti)
view(net)                      % visualizza la struttura della rete
y = net(x);                    % uscite della rete
perf = perform(net,y,t)        % prestazioni (per default MSE)
```

In **Colab** questo **nascondere i dettagli** è **meno evidente**: il codice espone di più il funzionamento. Ci saranno **sessioni di laboratorio dedicate**.

> ⚠️ **Che cosa fa `train` di default (nota didattica).** A differenza degli esempi della Lezione 17, §7.2, qui non si impostano `divideParam`: `train` divide automaticamente i dati in training, validation e test (per default 70%, 15%, 15%) e usa la validation per l’**early stopping**. `perform(net, y, t)` calcolato su tutti i dati mescola quindi errori di training e di test.

<!-- SOURCE_VISUAL id="IS20-V28" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="70" type="immagine" description="Illustrazione introduttiva all’esempio pratico" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V29" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="71" type="immagine" description="Esempio pratico della lezione" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Allegato: backpropagation a mano sullo XOR (non d’esame)**

_(slide pp. 74–84)_

L’allegato (pp. 74–75) **non è oggetto d’esame**, ma è utile per **verificare la comprensione**: si calcola la backpropagation «**con la matita**».

#### **7.1. La rete e i valori iniziali**

**Rete a tre strati per lo XOR** (pp. 76–77). Due ingressi $x_1, x_2$, due neuroni nascosti (3 e 4) e un neurone di uscita (5), tutti con attivazione **sigmoide**. L’effetto della **soglia** di un neurone nascosto o di uscita è rappresentato dal suo peso $\theta$, collegato a un **ingresso fisso** pari a $-1$.

| Peso o soglia | Valore iniziale |
|---|---|
| $w_{13}$, $w_{14}$ | 0,5; 0,9 |
| $w_{23}$, $w_{24}$ | 0,4; 1,0 |
| $w_{35}$, $w_{45}$ | −1,2; 1,1 |
| $\theta_3$, $\theta_4$, $\theta_5$ | 0,8; −0,1; 0,3 |

<!-- SOURCE_VISUAL id="IS20-V30" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="76" type="diagramma" description="Rete a tre strati per lo XOR con neuroni 3, 4, 5, pesi e ingressi fissi -1" reason="Collegamenti della rete visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Passo in avanti**

_(slide pp. 78–79)_

Esempio di training: $x_1 = x_2 = 1$, uscita desiderata $y_{d,5} = 0$.

$$y_3 = \text{sigmoid}(x_1 w_{13} + x_2 w_{23} - \theta_3) = \frac{1}{1 + e^{-(1 \cdot 0{,}5 + 1 \cdot 0{,}4 - 1 \cdot 0{,}8)}} = \frac{1}{1 + e^{-0{,}1}} = 0{,}5250$$

$$y_4 = \text{sigmoid}(x_1 w_{14} + x_2 w_{24} - \theta_4) = \frac{1}{1 + e^{-(1 \cdot 0{,}9 + 1 \cdot 1{,}0 + 1 \cdot 0{,}1)}} = \frac{1}{1 + e^{-2{,}0}} = 0{,}8808$$

$$y_5 = \text{sigmoid}(y_3 w_{35} + y_4 w_{45} - \theta_5) = \frac{1}{1 + e^{-(-0{,}5250 \cdot 1{,}2 + 0{,}8808 \cdot 1{,}1 - 1 \cdot 0{,}3)}} = \frac{1}{1 + e^{-0{,}0389}} = 0{,}5097$$

Errore:
$$e = y_{d,5} - y_5 = 0 - 0{,}5097 = -0{,}5097$$

#### **7.3. Passo all’indietro**

_(slide pp. 80–81)_

**Gradiente del neurone di uscita** e correzioni, con $\alpha = 0{,}1$:

$$\delta_5 = y_5\,(1 - y_5)\, e = 0{,}5097 \cdot (1 - 0{,}5097) \cdot (-0{,}5097) = -0{,}1274$$

$$\Delta w_{35} = \alpha \cdot y_3 \cdot \delta_5 = 0{,}1 \cdot 0{,}5250 \cdot (-0{,}1274) = -0{,}0067$$
$$\Delta w_{45} = \alpha \cdot y_4 \cdot \delta_5 = 0{,}1 \cdot 0{,}8808 \cdot (-0{,}1274) = -0{,}0112$$
$$\Delta \theta_5 = \alpha \cdot (-1) \cdot \delta_5 = 0{,}1 \cdot (-1) \cdot (-0{,}1274) = +0{,}0127$$

> ⚠️ **Un segno sbagliato nella slide.** La p. 80 scrive $\Delta\theta_5 = -0{,}0127$, ma il prodotto $0{,}1 \cdot (-1) \cdot (-0{,}1274)$ è **positivo**; infatti la p. 82 aggiorna correttamente $\theta_5 = 0{,}3 + 0{,}0127 = 0{,}3127$.

**Gradienti dei neuroni nascosti** (p. 81), che ricevono all’indietro il gradiente $\delta_5$:

$$\delta_3 = y_3\,(1 - y_3)\, \delta_5\, w_{35} = 0{,}5250 \cdot (1 - 0{,}5250) \cdot (-0{,}1274) \cdot (-1{,}2) = 0{,}0381$$
$$\delta_4 = y_4\,(1 - y_4)\, \delta_5\, w_{45} = 0{,}8808 \cdot (1 - 0{,}8808) \cdot (-0{,}1274) \cdot 1{,}1 = -0{,}0147$$

Correzioni dei pesi nascosti:

| Correzione | Calcolo | Valore |
|---|---|---|
| $\Delta w_{13}$ | $\alpha \cdot x_1 \cdot \delta_3 = 0{,}1 \cdot 1 \cdot 0{,}0381$ | $+0{,}0038$ |
| $\Delta w_{23}$ | $\alpha \cdot x_2 \cdot \delta_3 = 0{,}1 \cdot 1 \cdot 0{,}0381$ | $+0{,}0038$ |
| $\Delta \theta_3$ | $\alpha \cdot (-1) \cdot \delta_3 = 0{,}1 \cdot (-1) \cdot 0{,}0381$ | $-0{,}0038$ |
| $\Delta w_{14}$ | $\alpha \cdot x_1 \cdot \delta_4 = 0{,}1 \cdot 1 \cdot (-0{,}0147)$ | $-0{,}0015$ |
| $\Delta w_{24}$ | $\alpha \cdot x_2 \cdot \delta_4 = 0{,}1 \cdot 1 \cdot (-0{,}0147)$ | $-0{,}0015$ |
| $\Delta \theta_4$ | $\alpha \cdot (-1) \cdot \delta_4 = 0{,}1 \cdot (-1) \cdot (-0{,}0147)$ | $+0{,}0015$ |

> 💡 **Leggere i segni (nota didattica).** L’uscita doveva essere 0 ed è 0,51: bisogna **abbassarla**. Il neurone 3 è collegato all’uscita con un peso **negativo** ($w_{35} = -1{,}2$), quindi per abbassare $y_5$ conviene **aumentare** $y_3$: $\delta_3 > 0$ e i pesi verso il neurone 3 crescono. Il neurone 4 ha un peso **positivo** verso l’uscita, quindi va **ridotto**: $\delta_4 < 0$.

#### **7.4. Aggiornamento e convergenza**

_(slide pp. 82–84)_

| Parametro | Aggiornamento | Nuovo valore |
|---|---|---|
| $w_{13}$ | $0{,}5 + 0{,}0038$ | 0,5038 |
| $w_{14}$ | $0{,}9 - 0{,}0015$ | 0,8985 |
| $w_{23}$ | $0{,}4 + 0{,}0038$ | 0,4038 |
| $w_{24}$ | $1{,}0 - 0{,}0015$ | 0,9985 |
| $w_{35}$ | $-1{,}2 - 0{,}0067$ | −1,2067 |
| $w_{45}$ | $1{,}1 - 0{,}0112$ | 1,0888 |
| $\theta_3$ | $0{,}8 - 0{,}0038$ | 0,7962 |
| $\theta_4$ | $-0{,}1 + 0{,}0015$ | −0,0985 |
| $\theta_5$ | $0{,}3 + 0{,}0127$ | 0,3127 |

Il processo di addestramento si **ripete** finché la **somma degli errori quadratici** è minore di **0,001**. La p. 83 mostra la **curva di apprendimento** per lo XOR e la p. 84 i **risultati finali** della rete a tre strati.

> 💡 **Perché due neuroni nascosti bastano (nota didattica).** Ciascun neurone nascosto traccia una **retta** nel piano degli ingressi, come un perceptron. Il neurone di uscita combina le due regioni: per esempio un neurone nascosto si attiva per «almeno un ingresso a 1» (OR) e l’altro per «entrambi a 1» (AND); l’uscita risponde 1 quando il primo è attivo e il secondo no, che è esattamente lo XOR. È il superamento del limite del §5.4.

<!-- SOURCE_VISUAL id="IS20-V31" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="83" type="grafico" description="Curva di apprendimento della rete per lo XOR: somma degli errori quadratici per epoca" reason="Andamento della curva visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS20-V32" source="Lesson_20_Course-of-dimensionality_FeedForwardNeuralNetwork_Learning.pdf" page="84" type="tabella" description="Risultati finali della rete a tre strati addestrata sullo XOR" reason="Pesi e uscite finali presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Più feature** / **più informazione** | più dimensioni, spazio più vuoto / non garantita se le feature sono irrilevanti o rumorose |
| **Dimensionalità nominale** / **effettiva** | numero di feature / dimensioni della regione in cui i dati vivono davvero |
| **Soma, dendrite, assone, sinapsi** / **neurone, ingresso, uscita, peso** | neurone biologico / neurone artificiale |
| **Combinatore lineare** / **limitatore rigido** | somma pesata / confronto con la soglia |
| **Segno** / **sigmoide** | uscita ±1, non derivabile / uscita continua in (0, 1), derivata $y(1-y)$ |
| **Iterazione** / **epoca** | un esempio / l’intero dataset una volta |
| **Perceptron** / **perceptron multistrato** | un neurone, frontiera lineare, niente XOR / strati nascosti, frontiere non lineari |
| **Regola delta del perceptron** / **backpropagation** | $\alpha \cdot x_i \cdot e$ / $\alpha \cdot y_j \cdot \delta$, con l’errore propagato all’indietro |
| **$\delta_k$ di uscita** / **$\delta_j$ nascosto** | derivata per errore osservato / derivata per somma dei gradienti successivi pesati |
| **Vanishing** / **exploding gradient** | gradiente quasi nullo, la rete smette di imparare / gradiente enorme, apprendimento instabile |

---

### **9. Sintesi della lezione**

_(slide p. 73)_

> ✅ **Punti principali** (p. 73).
> - **Maledizione della dimensionalità**: al crescere delle dimensioni i dati necessari crescono esponenzialmente ($10^d$ bin), lo spazio diventa quasi vuoto e le distanze perdono significato; rimedi: selezione delle feature, riduzione della dimensionalità, controllo della complessità.
> - **Il perceptron**: somma pesata, soglia e limitatore rigido; frontiera lineare, capace di AND e OR ma non di XOR.
> - **Le equazioni dell’apprendimento**: $\Delta w_i(p) = \alpha \cdot x_i(p) \cdot e(p)$, base di tutti i metodi di apprendimento delle reti.
> - **Reti neurali multistrato**: teoria della backpropagation in quattro passi, gradienti dell’errore, problemi del gradiente che svanisce o esplode, applicazione a un esempio semplice (lo XOR, nell’allegato).
> - **Reti feed-forward in MATLAB** (introduzione): `feedforwardnet`, `train`, `perform`.
