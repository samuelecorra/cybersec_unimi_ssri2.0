## **Lezione 21: Deep learning e reti neurali convoluzionali**

La ventunesima lezione del corso **IS4** entra nel **deep learning**. Parte dalla storia (gli inverni dell’AI, il lavoro di Hinton, AlexNet) e dalla differenza di fondo con il machine learning classico: le **feature non vengono più progettate** a mano, ma **apprese** in una gerarchia di strati, dai bordi alle parti degli oggetti fino agli oggetti. Il cuore della lezione sono le **reti neurali convoluzionali** (CNN): la convoluzione come rilevatore di pattern che «si sposta» sull’immagine, i **pesi condivisi**, la **ReLU**, il **pooling**, il flattening e la **softmax** finale. Seguono **Keras**, l’esempio di **AlphaGo**, le CNN applicate a **segnali e testi**, le architetture note (AlexNet, LeNet, VGG16, GoogLeNet, Inception-v3, YOLO) e due laboratori: **GoogLeNet** in MATLAB e **Inception-v3** in Colab.

Fonti: [PDF della Lezione 21](Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf), 95 pagine, e il notebook [Image_classification_Inception_v3_COLAB.ipynb](Image_classification_Inception_v3_COLAB.ipynb), letto integralmente. Le immagini `Truck.jpg` e `Truck299.jpg` sono l’input del notebook e sono state solo inventariate, non visualizzate. Molte slide sono adattate da materiali di James Hays, R. Fergus, Tony Martinez e dal corso di deep learning di NVIDIA; i valori numerici delle matrici d’esempio sono nelle immagini. Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *Models and Methods for Deep Learning, Convolutional Neural Networks (CNNs), Coding CNNs in Keras, Examples of pretrained CNNs and applications*. L’indice (p. 2) elenca:

1. **deep learning** contro reti neurali **classiche**;
2. **reti neurali convoluzionali**: configurazione, test, codice in Keras;
3. esempi di **CNN grandi e pubbliche** per le applicazioni.

---

### **1. Il deep learning**

_(slide pp. 3–26)_

#### **1.1. Dove si colloca e da dove viene**

Il deep learning è un sottoinsieme del machine learning, che a sua volta è un sottoinsieme dell’AI: **DL ⊂ ML ⊂ AI** (p. 4). Le pp. 5–6 ne mostrano la **cronologia**.

<!-- SOURCE_VISUAL id="IS21-V01" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="4" type="schema" description="Deep learning come sottoinsieme del machine learning e dell’AI" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V02" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="5" type="cronologia" description="Cronologia del deep learning" reason="Date ed eventi presenti solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**La strada verso il deep learning** (pp. 7–9):

- tra il **2006 e il 2012** il gruppo di **Geoffrey Hinton** (University of Toronto) portò gli algoritmi delle reti neurali su **architetture parallele**;
- aumentarono il numero di **strati**, di **neuroni** e di **parametri** (anche oltre **10 milioni**), permettendo di far passare nel sistema **enormi quantità di dati** per addestrarlo;
- il risultato fu **AlexNet**, nel **2012**;
- il contributo più trasformativo di Hinton era stato **diffondere e perfezionare la backpropagation** negli anni ’80, in particolare con l’articolo del **1986** scritto con **David E. Rumelhart** e **Ronald J. Williams**, «Learning representations by back-propagating errors», pubblicato su *Nature* (Lezione 20, §6);
- **Demis Hassabis** e **John Jumper** hanno contribuito ad **AlphaFold**, un sistema di AI che ha risolto il problema del **ripiegamento delle proteine**, una grande sfida della biologia rimasta aperta per decenni.

> 💡 **I premi Nobel del 2024 (nota didattica).** Le due slide alludono ai riconoscimenti del 2024: Geoffrey Hinton ha ricevuto il **Nobel per la Fisica** insieme a John Hopfield per le scoperte fondamentali sulle reti neurali artificiali; Demis Hassabis e John Jumper il **Nobel per la Chimica**, insieme a David Baker, per la predizione della struttura delle proteine.

**Il deep learning ha bisogno di risorse** (p. 12). Nella storia dell’AI ci sono stati due grandi **«inverni»**, periodi di **riduzione dei finanziamenti e dell’interesse** per la ricerca: **1974–1980** e **1987–1993**.

**La tendenza** (pp. 13–14): l’indice di ricerca di **Google Trends** per «deep learning» dal 2004 e dal 2011, confrontato con l’andamento dell’**hype cycle** di Gartner (Lezione 02, §8.2) e con quello della **blockchain**.

<!-- SOURCE_VISUAL id="IS21-V03" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="14" type="grafico" description="Google Trends per deep learning e blockchain confrontato con l’hype cycle" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Che cos’è il deep learning**

> 📌 **Deep learning** (pp. 10–11). Una **classe di algoritmi** che:
> - usa diversi **strati gerarchici non lineari** per l’**estrazione** e la **trasformazione** delle feature;
> - può essere **supervisionata** (classificatori, regressori) oppure **non supervisionata** (analisi di pattern).
>
> Le funzioni degli strati gerarchici sono **create automaticamente** dal metodo di apprendimento: le feature di **alto livello** vengono derivate da quelle di **basso livello**, costruendo una **descrizione gerarchica** dell’input.

#### **1.3. Dalle feature progettate alle feature apprese**

_(slide pp. 15–24)_

La p. 15 confronta **ML classico e deep learning**.

**Nel ML classico l’estrazione delle feature la progetta il designer** (pp. 16–17), su tre livelli: feature di **basso livello**, feature di **medio livello**, **parti di oggetti**. Esempi di feature progettate a mano: **SIFT**, **spin image**, **texton**, SURF, MSER, LBP, Color-SIFT, istogrammi di colore, GLOH, **HoG** e molte altre (Lezione 08, §5.2).

**Le rappresentazioni di medio livello** (p. 18): indizi come **continuità**, **parallelismo**, **giunzioni**, **angoli** (i «token» del libro *Vision* di D. Marr). Ma le **parti degli oggetti** sono **difficili da progettare a mano**. E allora: perché non **imparare direttamente** gli estrattori di feature?

> 📌 **Imparare la gerarchia delle feature** (pp. 19–20).
> - Si impara una **gerarchia**, **dai pixel fino al classificatore**.
> - Ogni strato **estrae feature dall’uscita dello strato precedente**: immagine o video → strato 1 → strato 2 → strato 3 → classificatore semplice.
> - **Tutti gli strati si addestrano insieme.**
>
> Esempio: dai **pixel**, il 1° strato impara i **bordi**, il 2° le **parti degli oggetti**, il 3° gli **oggetti**. Un rilevatore di volti risponde alla domanda «c’è un volto nell’input?».

<!-- SOURCE_VISUAL id="IS21-V04" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="17" type="immagini" description="Feature progettate a mano: SIFT, spin image, texton, HoG" reason="Esempi visivi delle feature" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V05" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="19" type="diagramma" description="Gerarchia di feature appresa dai pixel al classificatore, strati addestrati insieme" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V06" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="20" type="immagini" description="Feature apprese: bordi, parti di oggetti, oggetti (volti)" reason="Visualizzazioni dei filtri presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il legame con le lezioni precedenti (nota didattica).** È il terzo schema della Lezione 08, §3.2: nel ML classico la catena è «estrazione delle feature progettata → classificatore»; nel deep learning **tutta la catena** è dentro la rete e viene ottimizzata insieme. Il prezzo è la fame di dati (Lezione 07, §2.5) e la minore spiegabilità (Lezione 04, §2).

Le pp. 21–23, dal corso di deep learning di NVIDIA, presentano **tassonomia**, **approccio** del deep learning e **che cosa lo rende «profondo»**.

<!-- SOURCE_VISUAL id="IS21-V07" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="21" type="schema" description="Tassonomia del deep learning (corso NVIDIA)" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V08" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="23" type="schema" description="Che cosa rende profondo il deep learning (corso NVIDIA)" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Spiegabilità** (p. 24). Esempio di «**vista**» di uno strato di feature visive appreso: ogni quadrato mostra l’**immagine di input** che **attiva al massimo** una delle 100 unità (Lezione 11, §5.2).

<!-- SOURCE_VISUAL id="IS21-V09" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="24" type="immagini" description="Immagini che attivano al massimo 100 unità di uno strato appreso" reason="Griglia di immagini visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.4. Perché profondo**

_(slide pp. 25–26)_

> 📌 **Perché il deep learning** (p. 25).
> - **Plausibilità biologica**, per esempio la **corteccia visiva**.
> - **Dimostrazione di Håstad**: problemi rappresentabili con un numero **polinomiale** di nodi usando $k$ strati possono richiederne un numero **esponenziale** con $k - 1$ strati (per esempio la funzione **parità** di una stringa di bit). **Molti strati è meglio.**
> - Funzioni **molto variabili** si rappresentano **efficientemente** con architetture profonde.
> - **Meno pesi** da aggiornare rispetto a una rappresentazione «piatta» meno efficiente.
> - Le **sotto-feature** create in un’architettura profonda si possono **condividere** tra più compiti: una forma di **transfer learning** e **multi-task learning**.

> 💡 **L’esempio della parità (nota didattica).** La parità di $n$ bit vale 1 se il numero di bit a 1 è dispari. Con una catena di porte XOR a due ingressi bastano $n - 1$ porte, disposte in circa $\log_2 n$ livelli. Con soli **due livelli** (un OR di AND) servono invece $2^{n-1}$ termini, uno per ogni combinazione con un numero dispari di 1: con $n = 20$ sono 524 288. La profondità permette di **riusare** risultati intermedi.

**I primi lavori** (p. 26):

- **Fukushima** (1980): il **Neocognitron**;
- **LeCun** (1998): le **reti neurali convoluzionali**, con somiglianze al Neocognitron;
- i **MLP con molti strati** e backpropagation erano stati tentati presto, **senza molto successo**: erano **molto lenti** e soffrivano della **diffusione del gradiente** (il vanishing gradient, Lezione 20, §6.3);
- lavori recenti hanno mostrato **grandi miglioramenti**:
  - addestrando «**con pazienza**» MLP più profondi con la backpropagation su **macchine veloci** (GPU);
  - adottando la funzione di attivazione **ReLU**;
  - con le **reti residue** (ResNet), che offrono al gradiente un **percorso diretto**, saltando alcuni strati tramite **mappature identità**.

> 📌 **Il blocco residuo (nota didattica).** Un blocco residuo non impara direttamente l’uscita $H(\mathbf{x})$, ma la **differenza** $F(\mathbf{x}) = H(\mathbf{x}) - \mathbf{x}$:
> $$\mathbf{y} = F(\mathbf{x}) + \mathbf{x}$$
> Derivando, $\partial \mathbf{y} / \partial \mathbf{x} = \partial F / \partial \mathbf{x} + I$: il termine identità $I$ fa passare il gradiente all’indietro anche quando $\partial F / \partial \mathbf{x}$ è piccolo. È così che le ResNet addestrano reti con centinaia di strati senza che il gradiente svanisca.

<!-- SOURCE_VISUAL id="IS21-V10" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="26" type="diagramma" description="Blocco residuo con percorso diretto per il gradiente" reason="Schema del blocco presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Reti neurali convoluzionali**

_(slide pp. 27–53)_

#### **2.1. L’idea**

**Partendo dalle reti feed-forward** (p. 28). Sappiamo che è bene imparare un modello **piccolo**. In un modello **completamente connesso** servono davvero **tutti i collegamenti**? Alcuni si possono **condividere**?

**Imparare un’immagine** (p. 29). Alcuni **pattern** sono **molto più piccoli** dell’intera immagine: un rilevatore di **«becco»** guarda solo una piccola regione, che si può rappresentare con **meno parametri**.

**Lo stesso pattern compare in punti diversi** (p. 30): un becco in alto a sinistra e uno al centro. Invece di un rilevatore per ogni posizione, si addestrano tanti **piccoli rilevatori** che devono **«spostarsi»** sull’immagine: i rilevatori del becco in posizioni diverse si possono **comprimere negli stessi parametri**.

> 📌 **Strato convolutivo** (p. 31). L’idea di «spostarsi» è lo **strato convolutivo**. Una **CNN** è una rete neurale con alcuni **strati convolutivi** (e alcuni altri strati). Uno strato convolutivo ha un certo numero di **filtri** che eseguono l’operazione di **convoluzione**. L’uscita della convoluzione è **alta** dove l’immagine di input è **simile al pattern del kernel**.

> 💡 **Lo abbiamo già visto (nota didattica).** È esattamente la cross-correlazione delle Lezioni 09 e 10: il singolo gelato usato come kernel produceva picchi dove l’immagine conteneva gelati. In una CNN i kernel non si scelgono a mano ma vengono **appresi**, e ce ne sono molti per ogni strato.

<!-- SOURCE_VISUAL id="IS21-V11" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="29" type="immagine" description="Rilevatore di becco su una piccola regione dell’immagine" reason="Immagine dell’esempio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V12" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="31" type="schema" description="Strato convolutivo: filtro rilevatore di becco che si sposta sull’immagine" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. La convoluzione passo per passo**

_(slide pp. 32–40)_

**Kernel di convoluzione** (p. 32). L’esempio usa un’**immagine $6 \times 6$** con valori 0 e 1 e **filtri $3 \times 3$** con valori 1 e −1 (Filtro 1, Filtro 2, …). **I filtri sono i parametri della rete da imparare**: ogni filtro rileva un **piccolo pattern** $3 \times 3$. Nelle reti tradizionali ci sono **solo pesi**.

**Il passo della convoluzione** (pp. 33–35). Si appoggia il filtro sull’angolo in alto a sinistra e si calcola il **prodotto scalare** tra il filtro e la porzione $3 \times 3$ dell’immagine: è un valore della nuova mappa. Poi si **sposta** il filtro di un **passo** (*stride*) e si ripete, riga per riga.

- Con **stride = 1** su un’immagine $6 \times 6$ si ottiene una mappa $4 \times 4$ (p. 35).
- Con **stride = 2** la mappa è più piccola (p. 34).

> 📌 **Dimensione dell’uscita (nota didattica).** Con un’immagine $N \times N$, un filtro $F \times F$, stride $S$ e senza padding:
> $$N_{\text{out}} = \left\lfloor \frac{N - F}{S} \right\rfloor + 1$$
> Con $N = 6$, $F = 3$: stride 1 dà $\frac{3}{1} + 1 = 4$; stride 2 dà $\lfloor 1{,}5 \rfloor + 1 = 2$.

> 💡 **Un prodotto scalare per esteso (nota didattica).** Nella versione classica dell’esempio il Filtro 1 ha 1 sulla diagonale principale e −1 altrove, e l’angolo in alto a sinistra dell’immagine ha 1 sulla stessa diagonale e 0 altrove:
> $$\begin{bmatrix} 1 & -1 & -1 \\ -1 & 1 & -1 \\ -1 & -1 & 1 \end{bmatrix} \cdot \begin{bmatrix} 1 & 0 & 0 \\ 0 & 1 & 0 \\ 0 & 0 & 1 \end{bmatrix} = 1 + 1 + 1 = 3$$
> (somma dei prodotti elemento per elemento). Il valore è **massimo** perché la porzione di immagine contiene **esattamente** la diagonale che il filtro cerca; dove l’immagine ha pixel a 1 fuori dalla diagonale, i −1 del filtro abbassano il risultato. I valori completi della slide vanno verificati sulle immagini.

<!-- SOURCE_VISUAL id="IS21-V13" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="32" type="schema" description="Immagine 6x6 binaria e filtri 3x3 con valori 1 e -1" reason="Valori delle matrici presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V14" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="35" type="schema" description="Mappa 4x4 ottenuta dal Filtro 1 con stride 1" reason="Valori della mappa presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Uno strato di CNN** (p. 36). Si ripete la convoluzione **per ogni filtro**: con due filtri si ottengono **due immagini $4 \times 4$**, che formano una matrice $2 \times 4 \times 4$, la **feature map**.

**Immagine a colori** (p. 37). Un’immagine RGB ha **3 canali**: ogni filtro diventa un «cubo» $3 \times 3 \times 3$, che copre contemporaneamente i tre canali.

<!-- SOURCE_VISUAL id="IS21-V15" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="36" type="schema" description="Due filtri producono due mappe 4x4: feature map 2x4x4" reason="Mappe presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V16" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="37" type="schema" description="Convoluzione su immagine RGB a 3 canali con filtri tridimensionali" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Convoluzione contro strato completamente connesso** (pp. 38–40). Srotolando l’immagine $6 \times 6$ in 36 ingressi:
> - un neurone **completamente connesso** è collegato a **tutti i 36 ingressi**;
> - un «neurone» della convoluzione è collegato **solo a 9 ingressi**, quelli coperti dal filtro: **meno parametri**;
> - i neuroni che corrispondono a posizioni diverse dello **stesso filtro** usano **gli stessi 9 pesi**: **pesi condivisi**, cioè **ancora meno parametri** grazie al riuso.

> 💡 **Quanti parametri si risparmiano (nota didattica).** Un’immagine $224 \times 224 \times 3$ ha 150 528 valori. Un primo strato completamente connesso con 1000 neuroni richiede circa $150\,528 \times 1000 \approx 1{,}5 \times 10^8$ pesi. Uno strato convolutivo con 64 filtri $3 \times 3 \times 3$ ne richiede $64 \times (27 + 1) = 1792$, indipendentemente dalla dimensione dell’immagine. È il conteggio dei gradi di libertà della Lezione 07 applicato alle immagini.

<!-- SOURCE_VISUAL id="IS21-V17" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="39" type="schema" description="Neurone della convoluzione collegato a 9 ingressi invece che a tutti i 36" reason="Collegamenti visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V18" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="40" type="schema" description="Pesi condivisi tra posizioni diverse dello stesso filtro" reason="Collegamenti visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. ReLU, pooling, flattening, softmax**

_(slide pp. 41–53)_

> 📌 **ReLU** (*Rectified Linear Unit*, p. 41). È **semplice** ed **efficiente** da calcolare: $\text{ReLU}(x) = \max(0, x)$. Per ingressi **positivi** la derivata vale **1**, il che aiuta a ridurre la **saturazione del gradiente**; per ingressi **negativi** la derivata vale **0**, e questo può produrre **neuroni inattivi**.

**L’architettura di una CNN** (p. 42): **convoluzione → max pooling → convoluzione → max pooling**, ripetuti **molte volte**; poi **flattening** e una **rete feed-forward completamente connessa** che produce le classi (volto, corpo, cane, cavallo, …).

<!-- SOURCE_VISUAL id="IS21-V19" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="42" type="diagramma" description="Architettura di una CNN: convoluzione, max pooling ripetuti, flattening, rete completamente connessa" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Convoluzione contro reti completamente connesse** (p. 43). CNN e reti completamente connesse possono risolvere **gli stessi problemi**, **ma** le CNN:

- **riducono il numero di connessioni**;
- **condividono i calcoli** precedenti tra i neuroni;
- possono usare il **pooling** per **ridurre la complessità** di calcolo.

**Perché il pooling** (pp. 44–45). **Sottocampionare i pixel non cambia l’oggetto**: un uccello rimpicciolito resta un uccello. Si può sottocampionare per rendere l’immagine **più piccola**, con **meno parametri** per descriverla.

> 📌 **Max pooling** (pp. 46–48). Si divide ogni mappa in blocchi (per esempio $2 \times 2$) e si tiene il **valore massimo** di ogni blocco: da una mappa $4 \times 4$ si ottiene una mappa $2 \times 2$. **Ogni filtro è un canale**: il risultato è una **nuova immagine, più piccola**.
> - Il sottocampionamento (pooling) **riduce il numero di feature**, con blocchi **non sovrapposti**.
> - **Riduce la risoluzione spaziale** e quindi l’importanza di **dove esattamente** è stata trovata una feature, conservandone solo la **posizione approssimativa**.
> - Si fa con la **media** o con il **massimo**.
> - Un pooling $2 \times 2$ comprime **4:1**, un $3 \times 3$ **9:1**, e così via.
> - **Leviga** i dati e li rende **invarianti a piccole traslazioni**.
> - Dopo il primo strato ci sono sempre **più feature map**; quali mappe precedenti colleghi ciascuna mappa è una **decisione di progetto** presa a priori.

> 💡 **Max pooling in numeri (nota didattica).** Una mappa
> $$\begin{bmatrix} 3 & -1 & -3 & -1 \\ -3 & 1 & 0 & -3 \\ -3 & -3 & 0 & 1 \\ 3 & -2 & -2 & -1 \end{bmatrix} \;\xrightarrow{\text{max pooling } 2\times 2}\; \begin{bmatrix} 3 & 0 \\ 3 & 1 \end{bmatrix}$$
> Il blocco in alto a sinistra ($3, -1, -3, 1$) dà 3, quello in alto a destra ($-3, -1, 0, -3$) dà 0, e così via. Il valore 3 dice «qui c’è la diagonale cercata», senza più dire in quale dei 4 pixel.

<!-- SOURCE_VISUAL id="IS21-V20" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="44" type="immagini" description="Sottocampionamento dell’immagine di un uccello che resta riconoscibile" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V21" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="46" type="schema" description="Max pooling da mappe 4x4 a immagini 2x2 per ogni filtro" reason="Valori delle mappe presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V22" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="48" type="immagine" description="Esempio di max pooling" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**La CNN completa** (pp. 49–51). Convoluzione e max pooling, ripetuti, producono **una nuova immagine**, **più piccola** dell’originale, con un **numero di canali pari al numero di filtri**. Il **flattening** srotola questa immagine in un **vettore**, che entra in una **rete feed-forward completamente connessa**, addestrata con la **backpropagation**, che produce le classi (gatto, cane, …).

<!-- SOURCE_VISUAL id="IS21-V23" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="50" type="diagramma" description="La CNN completa dalla convoluzione alla rete completamente connessa con le classi" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Lo strato finale softmax** (pp. 52–53). L’ultimo strato tipico di una CNN per la classificazione è la **softmax**, funzione di attivazione **esponenziale normalizzata** applicata alle somme pesate:
> $$p_i = \frac{e^{z_i}}{\sum_{j=1}^{C} e^{z_j}}$$
> Converte i **logit** $z_i$ in **punteggi normalizzati** che **sommano a 1**. Spesso vengono letti come **probabilità delle classi**, ma **non sono necessariamente probabilità ben calibrate** (Lezione 10, §6.3).

<!-- SOURCE_VISUAL id="IS21-V24" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="53" type="formula" description="Equazione della softmax come modulo finale della CNN" reason="Formula resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Deep learning con Keras**

_(slide pp. 54–62)_

> 📌 **Keras** (pp. 54–55). Una libreria **open source** di reti neurali scritta in Python, un’**API di alto livello** per il deep learning. Caratteristiche principali:
> - **facile da usare**, **modulare**, **estendibile**;
> - **sperimentazione rapida**;
> - **rilascio**: i modelli si possono esportare in **JavaScript** per il browser e in **TF Lite** per iOS, Android e dispositivi embedded;
> - **Keras 3** è **multi-backend**: gira su **TensorFlow**, **JAX**, **PyTorch** e **OpenVINO** (per l’inferenza);
> - utile per prototipazione, addestramento, **transfer learning** e rilascio.
>
> È usata dal **CERN**, dalla **NASA** e da molte altre organizzazioni scientifiche.

Keras offre (p. 56) implementazioni dei blocchi più comuni delle reti neurali (**strati**, **obiettivi**, **funzioni di attivazione**, **ottimizzatori**) e strumenti per **immagini** e **testo**.

**Con e senza Keras** (p. 57). Un esempio Colab: una rete feed-forward con **3 ingressi** e **10 neuroni nascosti**, scritta con Keras e direttamente in TensorFlow. Keras è ottimo per la **prototipazione** e per quasi tutto il lavoro; con **TensorFlow** si possono gestire **tutti i dettagli**.

<!-- SOURCE_VISUAL id="IS21-V25" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="57" type="schermata" description="Rete feed-forward 3-10 scritta con e senza Keras in Colab" reason="Codice presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Una CNN in Keras** (pp. 58–61). Input: immagini **$28 \times 28$ pixel** con **1 canale** (1 = grigio, 3 = RGB), `input_shape = (28, 28, 1)`.

| Strato | Uscita | Parametri per filtro |
|---|---|---|
| input | $1 \times 28 \times 28$ | — |
| **convoluzione**, 25 filtri $3 \times 3$ | $25 \times 26 \times 26$ | $3 \times 3 \times 1 = 9$ |
| **max pooling** $2 \times 2$ | $25 \times 13 \times 13$ | — |
| **convoluzione**, 50 filtri $3 \times 3$ | $50 \times 11 \times 11$ | $3 \times 3 \times 25 = 225$ |
| **max pooling** $2 \times 2$ | $50 \times 5 \times 5$ | — |
| **flattening** | $1250$ | — |
| rete **completamente connessa** | uscita | — |

> 💡 **Perché 225 parametri per filtro (p. 59).** Nel secondo strato convolutivo l’input non ha più 1 canale ma **25** (le 25 mappe del primo strato): ogni filtro $3 \times 3$ deve coprirle tutte, quindi ha $3 \times 3 \times 25 = 225$ pesi. Aggiungendo un bias per filtro (nota didattica), i due strati convolutivi hanno $25 \times (9 + 1) = 250$ e $50 \times (225 + 1) = 11\,300$ parametri. Le dimensioni seguono la formula del §2.2: $28 - 3 + 1 = 26$, poi $26 / 2 = 13$, $13 - 3 + 1 = 11$, $\lfloor 11 / 2 \rfloor = 5$, e $50 \times 5 \times 5 = 1250$.

> 💡 **Il codice corrispondente (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> from keras import layers, models
>
> model = models.Sequential([
>     layers.Input(shape=(28, 28, 1)),
>     layers.Conv2D(25, (3, 3), activation="relu"),   # 26 x 26 x 25
>     layers.MaxPooling2D((2, 2)),                    # 13 x 13 x 25
>     layers.Conv2D(50, (3, 3), activation="relu"),   # 11 x 11 x 50
>     layers.MaxPooling2D((2, 2)),                    #  5 x  5 x 50
>     layers.Flatten(),                               # 1250
>     layers.Dense(100, activation="relu"),           # dimensione a scelta
>     layers.Dense(10, activation="softmax"),         # per esempio 10 cifre
> ])
> model.summary()
> ```
> Keras ordina le dimensioni come (altezza, larghezza, canali), mentre le slide le scrivono come (canali, altezza, larghezza).

<!-- SOURCE_VISUAL id="IS21-V26" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="58" type="diagramma" description="CNN in Keras con input 28x28x1 e 25 filtri 3x3" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V27" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="59" type="diagramma" description="Dimensioni delle mappe e parametri per filtro nella CNN in Keras" reason="Dimensioni annotate nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La p. 62 invita a provare un **CNN explainer** interattivo.

<!-- SOURCE_VISUAL id="IS21-V28" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="62" type="schermata" description="CNN explainer interattivo" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. AlphaGo e il progetto delle CNN**

_(slide pp. 63–67)_

**AlphaGo** (p. 63). La rete riceve la scacchiera del **go** come una matrice $19 \times 19$ (nero = 1, bianco = −1, vuoto = 0) e produce la **mossa successiva**, anch’essa come matrice $19 \times 19$. Si potrebbe usare una rete **completamente connessa**, **ma una CNN funziona molto meglio**. La slide mette a confronto la scacchiera, un’«immagine» di $19 \times 19$ pixel in bianco e nero, con una normale immagine di $300 \times 300$ pixel a 8 bit in scala di grigi: per la rete sono lo stesso tipo di dato, una griglia di valori con struttura locale.

> ⚠️ **AlphaGo non usa il max pooling** (p. 64). La slide riporta la citazione dall’articolo di *Nature* sulla rete di policy di AlphaGo.

> 💡 **Perché niente pooling (nota didattica).** Il pooling rende la rete **invariante a piccoli spostamenti** (§2.3): utile per riconoscere un uccello, dannoso nel go, dove spostare una pietra di una casella cambia completamente la posizione. È un esempio di scelta architetturale guidata dalla **conoscenza del dominio**.

<!-- SOURCE_VISUAL id="IS21-V29" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="63" type="schema" description="Scacchiera del go come matrice 19x19 in ingresso alla rete di AlphaGo" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V30" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="64" type="citazione" description="Rete di policy di AlphaGo senza max pooling (citazione da Nature)" reason="Citazione presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Progettare e addestrare una CNN** (p. 65):

- la maggior parte dei pesi si addestra con la **backpropagation**;
- la **struttura** della CNN è di solito **progettata a mano** per tentativi: numero totale di **strati**, **numero** e **dimensione** dei campi recettivi (i filtri), dimensione dei campi di **sottocampionamento** (pooling), **quali campi** dello strato precedente collegare;
- in genere si **riduce la dimensione** delle feature map e se ne **aumenta il numero** negli strati successivi.

**Oggi** (p. 66) molte scelte architetturali sono ancora umane, ma si usano anche **architetture pre-addestrate**, **transfer learning**, **ottimizzazione degli iperparametri**, **AutoML** e **Neural Architecture Search** (Lezione 05, §4). **Anche gli agenti stanno aiutando.**

> 📌 **AutoResearch** (p. 67). Il progetto **AutoResearch** di **Andrej Karpathy** mostra l’idea emergente di **ricerca ML agentica**: un agente AI **propone automaticamente modifiche al codice**, **esegue gli esperimenti**, **valuta le metriche di validazione** e **tiene solo i miglioramenti**.

> ⚠️ **Il rischio da tenere presente (nota didattica).** Un agente che prova centinaia di varianti e tiene quelle migliori **sulla validation** fa esattamente ciò che la Lezione 14, §4.3, indica come fonte di stime ottimistiche: la validation diventa parte del progetto. Serve un **test set** mai toccato dall’agente per misurare il risultato finale.

---

### **5. CNN per segnali e testi**

_(slide pp. 68–73)_

**Riconoscimento vocale** (pp. 69–70). Il segnale audio si trasforma in uno **spettrogramma** (tempo sull’asse orizzontale, frequenza su quello verticale, Lezione 10, §3.2), che viene trattato come un’**immagine** e dato a una CNN per riconoscere le parole («The», «Fox», …). **Spettrogramma + CNN** resta un’idea valida per la **classificazione audio** e l’estrazione di feature; il riconoscimento vocale **moderno** usa spesso architetture **transformer** o **ibride convoluzione-transformer**, come **Conformer**.

<!-- SOURCE_VISUAL id="IS21-V31" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="69" type="schema" description="Spettrogramma di un segnale vocale elaborato da una CNN" reason="Spettrogramma e schema presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Word embedding** (p. 71): le **parole convertite in numeri**.

1. **Creare il corpus** di documenti:
   - D1: «He is a lazy boy. She is also lazy.»
   - D2: «Rob is a lazy person.»
2. **Creare il dizionario**, per esempio la lista dei **token unici** del corpus (togliendo «is» per semplicità): `['He', 'She', 'lazy', 'boy', 'Rob', 'person']`.

Ogni documento diventa allora un vettore sulle parole del dizionario. Esistono modi **più complessi**, basati sulla **matrice di co-occorrenza**.

> 💡 **I vettori dei conteggi (nota didattica).** Contando quante volte ciascuna parola del dizionario compare in ogni documento:
>
> | | He | She | lazy | boy | Rob | person |
> |---|---|---|---|---|---|---|
> | **D1** | 1 | 1 | 2 | 1 | 0 | 0 |
> | **D2** | 0 | 0 | 1 | 0 | 1 | 1 |
>
> Le parole «a» e «also» non sono nel dizionario dell’esempio e non vengono contate. È una rappresentazione «a sacco di parole»: perde l’ordine e non sa che «boy» e «person» sono simili, limite superato dagli embedding appresi (Lezione 13, §3.2). La tabella esatta della slide va verificata sull’immagine.

**Word2Vec** (p. 72) crea vettori dalle parole con un’architettura **feed-forward completamente connessa**: un **estrattore neurale di feature** (Lezione 13, §3.4).

**CNN per la classificazione del testo** (p. 73). Esempio: **classificazione del sentiment di Twitter**. La frase («… I love my new phone …») diventa una matrice in cui ogni riga è l’embedding di una parola, come uno «**spettrogramma**» di word embedding, e una CNN la scorre con i suoi filtri. Le CNN si possono usare per il testo, ma l’**NLP moderno** è dominato dai modelli **transformer** (Lezione 13). L’esempio resta utile per capire che le convoluzioni elaborano **pattern locali strutturati** anche **fuori dalle immagini**.

<!-- SOURCE_VISUAL id="IS21-V32" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="73" type="diagramma" description="CNN per la classificazione del sentiment su una matrice di word embedding" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. CNN note**

_(slide pp. 74–83)_

#### **6.1. AlexNet, LeNet, VGG16**

| Rete | Caratteristiche (slide) |
|---|---|
| **AlexNet** (p. 75) | testata su **ImageNet**; uscita: vettore di probabilità $1000 \times 1$, uno per classe; **8 strati** (5 convolutivi + 3 completamente connessi); input $256 \times 256 \times 3$ |
| **LeNet-5** (p. 76), di Yann LeCun | **5 strati** (3 convolutivi + 2 completamente connessi) |
| **VGG16** (p. 77) | proposta da **K. Simonyan** e **A. Zisserman** (University of Oxford) nell’articolo «Very Deep Convolutional Networks for Large-Scale Image Recognition»; **92,7% di accuratezza top-5** su ImageNet; **16 strati** (13 convolutivi + 3 completamente connessi); per **classificazione e rilevamento** |

> ⚠️ **Precisazioni sui numeri (nota didattica).**
> - AlexNet parte da immagini ridimensionate a $256 \times 256$, ma la rete riceve **ritagli** di $224 \times 224$ (227 nell’implementazione originale).
> - Nel conteggio di LeNet-5 cambia il risultato a seconda che si contino gli strati di sottocampionamento: la descrizione originale elenca 7 strati (convoluzioni, sottocampionamenti e strati completamente connessi).
> - La slide descrive ImageNet come «oltre 14 milioni di immagini in 1000 classi». Il dataset completo ha circa 14 milioni di immagini in oltre 20 000 categorie; la **competizione ILSVRC**, su cui si misura il 92,7%, ne usa un sottoinsieme di circa **1,28 milioni** di immagini di training in **1000 classi**.

> 📌 **Accuratezza top-5 (nota didattica).** Una predizione conta come corretta se la classe vera è **tra le 5** con punteggio più alto. Con 1000 classi, molte simili tra loro (decine di razze di cani), la top-5 misura se il modello «ci va vicino»; la **top-1** chiede che la prima risposta sia esatta ed è sempre più bassa.

<!-- SOURCE_VISUAL id="IS21-V33" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="75" type="diagramma" description="Architettura di AlexNet con 5 strati convolutivi e 3 completamente connessi" reason="Architettura presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V34" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="76" type="diagramma" description="Architettura di LeNet-5 di Yann LeCun" reason="Architettura presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS21-V35" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="77" type="diagramma" description="Architettura di VGG16 con 13 strati convolutivi e 3 completamente connessi" reason="Architettura presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La p. 78 mostra un esempio con **VGG-Face CNN + PCA**: le feature estratte dalla rete addestrata sui volti, proiettate con la PCA (Lezione 18, §2).

<!-- SOURCE_VISUAL id="IS21-V36" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="78" type="grafico" description="Feature di VGG-Face proiettate con la PCA" reason="Proiezione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. GoogLeNet e Inception-v3**

> 📌 **GoogLeNet (Inception v1)** (p. 79). CNN **profonda 22 strati**, con input $224 \times 224 \times 3$ (RGB). Se ne può caricare una versione pre-addestrata su dataset diversi:
> - su **ImageNet**, come AlexNet: classifica le immagini in **1000 categorie** di oggetti (tastiera, mouse, matita, molti animali, …);
> - su **Places365**: classifica le immagini in **365 categorie di luoghi** (campo, parco, pista di atterraggio, atrio, …).
>
> In base all’applicazione si sceglie la rete pre-addestrata considerando **dimensioni della rete** e **tipo di dataset** di addestramento.

**Un uso di GoogLeNet** (p. 80). Si analizza la **galleria fotografica personale** di un utente con la versione pre-addestrata su **Places365**, per individuare i **tipi di ambiente** e ricavare **informazioni personali e abitudini**: tante foto di montagne → «appassionato di montagna»; tante foto di negozi → «appassionato di shopping». La slide aggiunge, ironicamente, «(e vendere queste informazioni…)». La rete **non è molto complessa**: estrarre queste informazioni **costa poco** in tempo e risorse.

> ⚠️ **Profilazione e GDPR (nota didattica).** Dedurre abitudini e interessi dalle foto personali è una **profilazione** di dati personali: per il GDPR richiede una base giuridica, trasparenza verso l’utente e, per una vendita a terzi, di norma il **consenso**. La facilità tecnica dell’operazione è proprio ciò che rende necessarie le regole viste nelle Lezioni 02 e 15.

<!-- SOURCE_VISUAL id="IS21-V37" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="80" type="schema" description="Analisi della galleria personale con GoogLeNet Places365: montagne e negozi" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Inception-v3** (p. 81). CNN **profonda 48 strati**; se ne può caricare una versione pre-addestrata su **oltre un milione di immagini** di ImageNet. La slide ricorda anche il **dropout**: si **disattivano** a caso delle unità (nascoste e visibili) durante l’addestramento; è un metodo di **regolarizzazione** che riduce l’apprendimento **interdipendente** tra i neuroni e l’**overfitting**.

> 💡 **Il dropout in pratica (nota didattica).** Con tasso di dropout $r$, a ogni passo di addestramento ogni unità viene azzerata con probabilità $r$ (per esempio 0,5), e le altre vengono scalate di $1/(1 - r)$ perché la somma attesa resti la stessa. In fase di uso il dropout è **spento**. Ogni neurone impara così a funzionare senza poter contare su specifici compagni, e la rete somiglia a una media di tante sotto-reti.

#### **6.3. Reti recenti e YOLO**

**Reti usate oggi nelle applicazioni** (p. 82). AlexNet, LeNet, VGG16, GoogLeNet/Inception e Inception-v3 sono **abbastanza semplici e gestibili**. I sistemi di produzione moderni usano anche **famiglie di modelli recenti** o i **vision transformer**, a seconda del compito (lezione successiva):

- **ResNet**;
- **EfficientNet**;
- **MobileNet**;
- **ConvNeXt**;
- la famiglia **YOLO**.

> 📌 **YOLO** (*You Only Look Once*, p. 83). Famiglia di modelli di **rilevamento di oggetti in tempo reale** che predice, **in un solo passaggio in avanti**:
> - **quali oggetti** sono presenti;
> - **dove** si trovano, con **bounding box** e **punteggi di classe**.
>
> È molto usata in applicazioni **industriali e ambientali** perché offre un buon **compromesso tra accuratezza, velocità e facilità di rilascio**: **telecamere**, **droni**, **dispositivi edge**, **ispezione della qualità**, **monitoraggio della sicurezza**, **sistemi autonomi**.

**Come sono fatti i rilevatori moderni** (p. 83):

| Parte | Funzione |
|---|---|
| **backbone** | **estrazione delle feature** |
| **neck** | **fusione** delle feature a **più scale** |
| **head** di predizione | **localizzazione** e **classificazione** degli oggetti |

I rilevatori **a uno stadio** come YOLO fanno una **predizione densa** direttamente sulle feature map; quelli **a due stadi** generano prima **regioni candidate** sparse e poi le **raffinano**, di solito con **accuratezza maggiore** a scapito della **velocità**.

> 💡 **Classificazione e rilevamento (nota didattica).** Un classificatore come GoogLeNet risponde «in questa immagine c’è un camion». Un rilevatore come YOLO risponde «c’è un camion in questo rettangolo, una persona in quest’altro e un cane qui». La qualità di un box si misura di solito con l’**intersezione su unione** tra box predetto $B_p$ e box vero $B_g$:
> $$\text{IoU} = \frac{|B_p \cap B_g|}{|B_p \cup B_g|}$$
> e un rilevamento conta come corretto se l’IoU supera una soglia, per esempio 0,5.

<!-- SOURCE_VISUAL id="IS21-V38" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="83" type="immagine" description="Rilevamento di oggetti con YOLO e schema backbone-neck-head" reason="Immagine e schema presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Codice: GoogLeNet in MATLAB**

_(slide pp. 84–87)_

**Classificare le immagini della webcam con GoogLeNet** (p. 85):

```matlab
% copy and paste this code in your matlab
camera = webcam;
net = googlenet;
% change size of the input image to network size
inputSize = net.Layers(1).InputSize(1:2)
figure
im = snapshot(camera);
image(im)
im = imresize(im,inputSize);
[label,score] = classify(net,im);
title( {char(label), num2str(max(score),2)} );
```

Se servono, **webcam** e **GoogLeNet** si installano dall’**Add-On Explorer**. Il titolo mostra la classe e il punteggio massimo con 2 cifre significative.

**L’uscita della softmax: le 5 classi più probabili** (p. 86):

```matlab
[label,score] = classify(net,im);
[~,idx] = sort(score,'descend');
idx = idx(5:-1:1);  %top 5 desceasing
classes = net.Layers(end).Classes;
classNamesTop = string(classes(idx));
scoreTop = score(idx);  %max score
h = figure; h.Position(3) = 2*h.Position(3);
ax1 = subplot(1,2,1); ax2 = subplot(1,2,2);
barh(ax2,scoreTop)
xlim(ax2,[0 1])
xlabel(ax2,'Probability')
yticklabels(ax2,classNamesTop)
ax2.YAxisLocation = 'right';
title(ax2,'Top 5')
```

`sort(score,'descend')` ordina gli indici delle 1000 classi dal punteggio più alto al più basso. `idx(5:-1:1)` prende i primi 5 **invertendone l’ordine**, dal quinto al primo. Non è un errore: `barh` disegna la prima barra in basso, quindi con l’ordine invertito la classe più probabile finisce **in alto**. Il commento della slide («top 5 desceasing») va letto in questo senso. La figura raddoppia la larghezza per affiancare l’immagine (`ax1`) e il grafico a barre (`ax2`), con le etichette delle classi sul lato destro.

**Provare un input casuale** (p. 87):

```matlab
im = rand(224,224,3);
imshow(im)
tic;
[label,score] = classify(net,im);
toc                    % 0.417492 seconds
label                  % categorical: spotlight
max(score)             % 0.4968
subplot(1,2,1); plot(sort(score));     xlabel('categories'); ylabel('score')
subplot(1,2,2); semilogy(sort(score)); xlabel('categories'); ylabel('score')
```

Un’immagine di **rumore casuale** viene classificata come «**spotlight**» (riflettore) con punteggio **0,497**, in circa **0,42 secondi**. Nella slide il commento è «nessun problema qui: c’è solo la classe spotlight»; i grafici dei punteggi ordinati, anche in scala logaritmica, mostrano che la softmax concentra la massa su **una sola classe**.

> ⚠️ **Il rumore ha una classe (nota didattica).** Dal punto di vista della rete «non c’è problema»: la softmax distribuisce sempre una massa pari a 1 tra le 1000 classi, e qui ne dà metà a «spotlight». Per un’applicazione, però, è proprio il problema della Lezione 01, §7.1, e della Lezione 05, §1.6: una rete di classificazione **non sa dire «nessuna delle classi»**. Servono una soglia di confidenza, una classe «nessuno» addestrata o un rilevatore di input fuori distribuzione.

<!-- SOURCE_VISUAL id="IS21-V39" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="87" type="grafico" description="Punteggi ordinati della softmax di GoogLeNet per un’immagine casuale classificata come spotlight" reason="Grafici dei punteggi visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Codice: Inception-v3 in Colab**

_(slide pp. 88–94)_

Il notebook [Image_classification_Inception_v3_COLAB.ipynb](Image_classification_Inception_v3_COLAB.ipynb) segue le slide pp. 89–94.

**Caricare la rete** (p. 89). In Colab i comandi di shell si scrivono preceduti da `!`, che dà controllo sulla macchina virtuale per installare pacchetti non predefiniti. Qualsiasi modello di riconoscimento di immagini disponibile in Keras si carica **con due righe**:

```python
!pip install -q keras

from keras.applications.inception_v3 import InceptionV3
model = InceptionV3(weights='imagenet', include_top=True)
```

`weights='imagenet'` carica i pesi pre-addestrati; `include_top=True` include lo strato finale che classifica nelle 1000 classi.

**Pre-elaborazione e predizione** (p. 90): una funzione `predict()` che riceve l’immagine e restituisce le **5 classi più probabili**.

```python
import numpy as np
from keras.applications.inception_v3 import preprocess_input, decode_predictions

def predict(model, img, top_n=5):
    x = np.expand_dims(img, axis=0)   # da (299, 299, 3) a (1, 299, 299, 3): un batch di una immagine
    x = preprocess_input(x)           # normalizzazione richiesta dalla rete
    preds = model.predict(x)          # HERE'S THE CLASS PREDICTION
    return decode_predictions(preds, top=top_n)[0]
```

**Caricare un esempio e ridimensionarlo** (pp. 91–92). Si carica un file dal proprio computer (nel notebook `Truck.jpg`, 593 821 byte) e lo si ridimensiona a **$299 \times 299 \times 3$**, la dimensione di ingresso della CNN:

```python
from google.colab import files
uploaded = files.upload()
for fn in uploaded.keys():
    print('User uploaded file "{name}" with length {length} bytes'.format(name=fn, length=len(uploaded[fn])))

import matplotlib.image as mpimg
import cv2
img = mpimg.imread(fn)
img = cv2.resize(img, dsize=(299, 299), interpolation=cv2.INTER_CUBIC)
plt.imshow(img)
```

**Eseguire la predizione** (p. 93) e stampare la corrispondenza più probabile:

```python
pred = predict(model, img)
print(pred[0])     # ('n03796401', 'moving_van', 0.43757406)
```

**Visualizzare le probabilità** della softmax finale (p. 94):

```python
plt.figure(figsize=(8, 2))
classes = [c[1] for c in pred]
probas = [c[2] for c in pred]
y_pos = np.arange(len(classes))
plt.barh(y_pos, probas, align='center')
plt.yticks(y_pos, classes)
plt.gca().invert_yaxis()
plt.xlabel('Probability')
plt.xlim(0, 1)
print(classes)
```

Risultato salvato nel notebook: la classe più probabile è **«moving_van»** (furgone per traslochi) con probabilità **0,438**; le 5 classi sono `moving_van`, `trailer_truck`, `mobile_home`, `recreational_vehicle`, `tow_truck`.

> 💡 **Leggere il risultato (nota didattica).** Le cinque classi sono tutte veicoli simili, e la prima ha meno della metà della massa: la rete «sa» che si tratta di un grande veicolo, ma è incerta sul tipo esatto, perché ImageNet non ha una classe «camion generico». È un buon esempio del perché si riporta la **top-5** (§6.1). Il codice che trasforma `n03796401` in «moving_van» è `decode_predictions`, che usa gli identificativi WordNet delle classi di ImageNet.

> ⚠️ **Dettagli del codice (nota didattica).**
> - `cv2.resize` a $299 \times 299$ **non conserva le proporzioni**: un’immagine rettangolare viene deformata. Spesso conviene ritagliare al centro prima di ridimensionare.
> - `mpimg.imread` restituisce valori `uint8` tra 0 e 255 per i JPEG, che è ciò che `preprocess_input` si aspetta (li porta tra −1 e 1). Con un PNG gli stessi valori arriverebbero già tra 0 e 1, e la normalizzazione sarebbe sbagliata senza alcun errore visibile.
> - Il commento del notebook su `preprocess_input` parla di «centrare i dati sottraendo la media dei canali del dataset di addestramento». Per **Inception-v3** non è così: la funzione scala i pixel come $x / 127{,}5 - 1$. La sottrazione delle medie per canale è la modalità di VGG16 e ResNet50. In pratica conta una sola regola: usare **la `preprocess_input` dello stesso modello** da cui si caricano i pesi.
> - Il notebook importa anche `from keras.preprocessing import image` e `from PIL import Image`, che non usa. Il primo nelle versioni recenti di Keras non esiste più in quella forma (si usa `keras.utils.load_img`).
> - L’output salvato mostra «Saving Truck.jpg to Truck (4).jpg»: il file era già stato caricato più volte, e Colab ha salvato la nuova copia con un suffisso. La chiave restituita da `files.upload()` resta però «Truck.jpg», e `mpimg.imread(fn)` legge la **prima copia** presente su disco. Qui è la stessa immagine e il risultato non cambia, ma caricando un’immagine diversa con lo stesso nome si classificherebbe quella vecchia senza accorgersene.
> - Il file `Truck299.jpg` presente nella cartella sembra una versione già ridimensionata, ma il notebook non lo usa.

<!-- SOURCE_VISUAL id="IS21-V40" source="Lesson_21_IntroducitonToDeepLearingModels_ConvolutionalNeuralNetworks.pdf" page="94" type="grafico" description="Probabilità delle 5 classi più probabili della softmax di Inception-v3" reason="Barre del grafico visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **ML classico** / **deep learning** | feature progettate dal designer / gerarchia di feature appresa dai pixel al classificatore |
| **Rete piatta** / **rete profonda** | può richiedere un numero esponenziale di nodi / riusa risultati intermedi (parità, Håstad) |
| **Strato completamente connesso** / **convolutivo** | ogni neurone vede tutti gli ingressi / pochi ingressi e pesi condivisi |
| **Filtro** / **feature map** | parametri appresi, piccolo pattern / risposta del filtro su tutta l’immagine |
| **Stride 1** / **stride 2** | mappa più grande / mappa sottocampionata |
| **Convoluzione** / **pooling** | trova i pattern / riduce la risoluzione, invarianza a piccoli spostamenti |
| **Max pooling** / **average pooling** | tiene il massimo del blocco / la media |
| **Logit** / **softmax** | punteggi grezzi / punteggi normalizzati a somma 1, non necessariamente calibrati |
| **Keras** / **TensorFlow** | API di alto livello, prototipazione rapida / controllo di tutti i dettagli |
| **Top-1** / **top-5** | classe vera al primo posto / classe vera tra le prime 5 |
| **GoogLeNet ImageNet** / **Places365** | 1000 categorie di oggetti / 365 categorie di luoghi |
| **Classificatore** / **rilevatore (YOLO)** | che cosa c’è nell’immagine / che cosa e dove, con bounding box |
| **Uno stadio** / **due stadi** | predizione densa, veloce / regioni candidate poi raffinate, più accurato |
| **Dropout** / **pooling** | regolarizzazione in addestramento / riduzione delle dimensioni in ogni uso |

---

### **10. Sintesi della lezione**

_(slide p. 95)_

> ✅ **Punti principali** (p. 95).
> - **Deep learning contro reti neurali classiche**: strati gerarchici non lineari che imparano automaticamente le feature, resi possibili da GPU, ReLU e reti residue.
> - **Reti neurali convoluzionali**:
>   - **configurazione**: convoluzione con filtri appresi e pesi condivisi, ReLU, max pooling, flattening, rete completamente connessa e softmax;
>   - **test**: punteggi della softmax, top-5, comportamento su input casuali;
>   - **codice in Keras**: dimensioni delle mappe e parametri per filtro.
> - **Esempi di CNN grandi e pubbliche** per le applicazioni: AlexNet, LeNet-5, VGG16, GoogLeNet, Inception-v3, e le famiglie recenti come ResNet, EfficientNet, MobileNet, ConvNeXt e YOLO.
> - **Classificazione di immagini**:
>   - in **MATLAB** con GoogLeNet e la webcam;
>   - in **Colab** con Inception-v3 pre-addestrata su ImageNet.
