## **Lezione 7: Dataset in Python, numero di parametri e data leakage**

La settima lezione del corso **IS4** unisce un primo **laboratorio** in Python/Colab sul dataset **Iris** a due temi teorici decisivi per ogni progetto. Il primo: quanti **parametri** (gradi di libertà) ha un modello, e quindi **quanti dati** servono per configurarlo. Il secondo: il **data leakage**, cioè le informazioni che «trapelano» dove non dovrebbero e rendono le prestazioni misurate in laboratorio **troppo ottimistiche** rispetto a quelle che il sistema avrà dopo il rilascio.

Fonti: [PDF della Lezione 07](Lesson_07_NumberOfParameters_DataLeakage.pdf), 73 pagine, e tre supplementi letti integralmente: il notebook [laboratory_COLAB_IRIS_Dataset.ipynb](laboratory_COLAB_IRIS_Dataset.ipynb), la sua esportazione [Lesson_07__laboratory_colab_iris_dataset.py](Lesson_07__laboratory_colab_iris_dataset.py) e il notebook [laboratory_colab_numparam_nn.ipynb](laboratory_colab_numparam_nn.ipynb) sul conteggio dei parametri. Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *Managing a small datasets in Python, Degrees of freedom/parameters, Data Leakage*. L’indice (p. 2) elenca:

1. **laboratorio** in Python/Colab sulla gestione dei dati di addestramento;
2. **numero di gradi di libertà / parametri**;
3. **data leakage**;
4. punti principali.

---

### **1. Laboratorio: caricare e gestire il dataset Iris**

_(slide pp. 3–22)_

#### **1.1. Il laboratorio e l’esame**

Il laboratorio (pp. 3–4) è in un file separato, con un esempio **Colab** e uno **MATLAB**, e usa il dataset **Iris**, descritto nelle lezioni successive. Contenuti: **preparazione dell’ambiente**, **caricamento dei dati**, **analisi preliminare**; nelle lezioni successive si passerà a grafici, addestramento e confronto dei modelli (p. 6).

> 📌 **Che cosa c’è all’esame** (p. 5).
> - All’esame **non si scrive codice** (esatto).
> - È però importante studiare l’**applicazione pratica** dei concetti teorici, per capirli meglio.
> - Si può essere chiamati a **commentare** o **rispondere su esempi di codice**.

**Colab** (pp. 7–8). Un file **IPYNB** è un **notebook** di Jupyter: un ambiente di calcolo interattivo per lavorare con Python e con i propri dati. Il notebook si carica su Colab; poi si **leggono i commenti** e si esegue ogni cella con il pulsante **[ ]**, oppure con «**Run all**».

<!-- SOURCE_VISUAL id="IS07-V01" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="8" type="schermata" description="Esecuzione delle celle di un notebook in Colab: pulsante della cella e Run all" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Ambiente e caricamento dei dati**

**Preparazione dell’ambiente** (p. 10):

| Libreria | Ruolo |
|---|---|
| **pandas** | pre-elaborazione dei dataset |
| **NumPy** | manipolazione di **array e matrici** multidimensionali, con una vasta raccolta di funzioni matematiche di alto livello |
| **matplotlib** (`pyplot`) | grafici, usati nelle lezioni successive |

**Caricamento** (pp. 11–13): si legge il file **CSV** di Iris con pandas, si ottiene un riepilogo organizzato con `dataset.info()` e si guardano i primi $n$ campioni con `dataset.head(n)`.

Il codice del notebook:

```python
import pandas as pd  # Python Data Analysis Library
import numpy as np   # Python Scientific Library
from matplotlib import pyplot as plt

url = "https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data"
new_names = ['sepal_length', 'sepal_width', 'petal_length', 'petal_width', 'iris_class']
dataset = pd.read_csv(url, names=new_names, skiprows=0, delimiter=',')
dataset.info()

dataset.head(10)
```

Output di `dataset.info()` salvato nel notebook: **150 righe** (indici da 0 a 149) e **5 colonne**; le quattro misure sono `float64` con 150 valori non nulli, la classe `iris_class` è di tipo `object` (stringa). I primi campioni di `head(10)` sono tutti *Iris-setosa*; il primo è 5.1, 3.5, 1.4, 0.2.

> 💡 **Perché si passano i nomi delle colonne.** Il file `iris.data` non ha una riga di intestazione: con `names=new_names` si assegnano i nomi alle cinque colonne. Senza, pandas userebbe il primo campione come intestazione e il dataset avrebbe **149** righe invece di 150: un errore silenzioso che `info()` permette di scoprire.

<!-- SOURCE_VISUAL id="IS07-V02" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="12" type="schermata" description="Output di dataset.info() in Colab" reason="Schermata dell’output" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Separare feature e classi, e indicizzare le matrici**

**Prima di tutto si separano le feature dalle classi** (p. 14). Feature e classi richiedono **pre-elaborazioni diverse**. È davvero necessario? **Sì, per evitare il data leakage** (§3). La proprietà `shape` corrisponde a `size()` in MATLAB.

```python
y = dataset['iris_class']
x = dataset.drop(['iris_class'], axis=1)

print("dataset : ", dataset.shape)   # (150, 5)
print("x : ", x.shape)               # (150, 4)
print("y : ", y.shape)               # (150,)
```

La **matrice delle feature** ha **4 colonne**, il **vettore dei target** ha **1 colonna** (p. 15). Poi si convertono i dati in **matrici NumPy**, per accedere facilmente a porzioni dei dati (p. 16):

```python
xNumpy = x.to_numpy()
yNumpy = y.to_numpy()

feature_to_extract = 1
feature = xNumpy[:, feature_to_extract]   # una feature: slicing per colonna
print(feature)

sample_to_extract = 1
sample = xNumpy[sample_to_extract, :]     # un campione: slicing per riga
print(sample)                             # [4.9 3.  1.4 0.2]
```

| Operazione (pp. 17–22) | Sintassi | Risultato nel notebook |
|---|---|---|
| leggere **una feature** | `xNumpy[:, j]` | colonna di 150 valori; con `j = 1` è la **larghezza del sepalo** (3.5, 3.0, 3.2, …) |
| leggere **un campione** | `xNumpy[i, :]` | riga di 4 valori; con `i = 1` è il **secondo** campione, `[4.9 3.0 1.4 0.2]` |

Le slide segnalano più volte il **debugging**: stampare porzioni di matrice è il modo più semplice per verificare che i dati siano quelli attesi.

> ⚠️ **Gli indici partono da 0.** In Python `feature_to_extract = 1` è la **seconda** colonna (`sepal_width`) e `sample_to_extract = 1` è il **secondo** campione. In MATLAB gli indici partono da 1: lo stesso numero indica elementi diversi. È un errore tipico quando si passa da un ambiente all’altro.

> ⚠️ **Lo script esportato non gira in Python 3.** Il file `Lesson_07__laboratory_colab_iris_dataset.py` termina con `raw_input('Waiting a key...')`, funzione che esiste solo in Python 2. In Python 3 si usa `input(...)`; il notebook, che non contiene quella riga, funziona regolarmente.

<!-- SOURCE_VISUAL id="IS07-V03" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="19" type="schermata" description="Slicing per colonna in Colab con indicazione di debugging" reason="Schermata dell’output" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Gradi di libertà e numero di parametri**

_(slide pp. 23–43)_

#### **2.1. Dati e modello: che cosa sappiamo davvero?**

Le domande della sezione (p. 23): **quanto è complesso** il modello? **Quanti dati** servono per configurarlo? Il tema riguarda i **passi 1 e 3** del flusso di lavoro del ML: raccolta dei dati e modello (p. 24).

Il principio **GIGO** resta valido (p. 25; Lezione 06, §3.1). **Ancora peggio** (p. 26) se ai dati sbagliati si aggiungono pre-filtraggio o ingegneria delle feature **sbagliati**, una **scelta del modello sbagliata**, reti **addestrate male** o **in overfitting**.

<!-- SOURCE_VISUAL id="IS07-V04" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="26" type="illustrazione" description="Ancora peggio: pre-filtraggio sbagliato, modello sbagliato, reti addestrate male o in overfitting" reason="Illustrazione della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Lo spettro dati-conoscenza** (p. 27, da *Machine Learning Refined* di Watt, Borhani e Katsaggelos). Le reti neurali tradizionali e le **reti profonde** si collocano verso l’estremo in cui si sa **poco** del processo e servono **molti dati**. La domanda da porsi: che cosa sai davvero

1. dei **dati** che hai e della tua **applicazione**?
2. del **modello di AI** che vuoi applicare?

<!-- SOURCE_VISUAL id="IS07-V05" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="27" type="grafico" description="Spettro dati-conoscenza con la posizione delle reti tradizionali e profonde (Watt, Borhani, Katsaggelos)" reason="Posizione dei modelli sullo spettro visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. L’analogia meccanica**

> 📌 **Gradi di libertà** (p. 28). In fisica, i **gradi di libertà** (DoF, *Degrees of Freedom*) di un sistema meccanico sono il **numero di parametri indipendenti** che ne definiscono la configurazione. Un piccolo componente meccanico nel piano ha **3 gradi di libertà**: le coordinate $x$, $y$ del centro di massa e l’angolo $\alpha$.

> ⚠️ **DoF e #Par non sono la stessa cosa** (p. 28). Per sistemi complessi i gradi di libertà **non coincidono esattamente** con il numero di parametri (#Par), ma sono **fortemente legati**.

**Un insieme di componenti** (pp. 29–30). Un modello meccanico, **come una rete neurale**, è un insieme di **elementi interconnessi**, ciascuno con i propri parametri:

$$\text{PZ}_1 = \{x_1, y_1, \alpha_1\}, \quad \text{PZ}_2 = \{x_2, y_2, \alpha_2\}, \quad \text{PZ}_3 = \{x_3, y_3, \alpha_3\}, \quad \dots$$

Il modello è **completo** quando tutti gli elementi sono **posizionati**, cioè quando i loro parametri sono **fissati** (la slide invita a trascurare i diversi tipi di vincolo).

<!-- SOURCE_VISUAL id="IS07-V06" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="29" type="illustrazione" description="Insieme di componenti meccanici, ciascuno con x, y e alpha" reason="Disposizione dei componenti visibile solo nell’illustrazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Similitudine 1: pesi e parametri** (p. 31). **Prima dell’addestramento** i pesi della rete **non sono configurati**, come componenti non ancora posizionati; **dopo l’addestramento** sono **configurati**.

**Similitudine 2: input, output, comportamento** (p. 32). Una struttura meccanica riceve quattro input: $x_1$ è uno **spostamento** (m), $x_2, x_3, x_4$ sono **forze** (N); l’output è una **deformazione** (mm).

| | **Problema 1: rete neurale** | **Problema 2: struttura meccanica** |
|---|---|---|
| Che cosa si configura | i **pesi** della rete | i **parametri** degli elementi |
| Obiettivo | classificare correttamente in uscita gli input $[x_1, \dots, x_4]$ | ottenere una **deformazione minima** in uscita per tutti gli input (la slide cita $x_1$ e $x_2$) |
| Criterio | **errore minimo** in apprendimento | **deformazione minima** in addestramento |

<!-- SOURCE_VISUAL id="IS07-V07" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="31" type="illustrazione" description="Pesi non configurati prima dell’addestramento e configurati dopo" reason="Analogia visibile solo nell’illustrazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS07-V08" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="32" type="illustrazione" description="Struttura meccanica con spostamento, forze e deformazione confrontata con una rete neurale" reason="Collegamento tra input e struttura visibile solo nell’illustrazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. Contare i parametri di una rete neurale**

**Esempio** (p. 33). Una rete con **4 input**, **un solo strato nascosto di 5 neuroni** e **1 neurone di uscita**:

| Strato | Conteggio | Parametri |
|---|---|---|
| nascosto | $4 \times 5$ pesi + 5 bias ($b_1, \dots, b_5$) | **25** |
| uscita | 5 connessioni + 1 bias ($b_6$) | **6** |
| **totale** | | **31** |

Quanti input $[x_1, x_2, x_3, x_4]$ servono in addestramento per **fissare correttamente** 31 pesi?

> 📌 **Parametri di uno strato denso (nota didattica).** Uno strato completamente connesso con $n_{\text{in}}$ ingressi e $n_{\text{out}}$ neuroni ha
> $$\#\text{Par} = n_{\text{in}} \cdot n_{\text{out}} + n_{\text{out}} = (n_{\text{in}} + 1)\, n_{\text{out}}$$
> Per la rete 4-5-1: $(4+1) \cdot 5 + (5+1) \cdot 1 = 25 + 6 = 31$.

**Verifica in Colab** (p. 34, notebook `laboratory_colab_numparam_nn.ipynb`; il codice sarà spiegato nelle lezioni successive):

```python
import tensorflow as tf
from tensorflow import keras
from keras import layers

model = keras.Sequential()
model.add(layers.Dense(5, input_shape=(4,), activation="linear"))
model.add(layers.Dense(1, activation="linear"))

model.summary()
```

Output salvato di `model.summary()`:

| Strato | Forma dell’output | Parametri |
|---|---|---|
| `dense` (Dense) | (None, 5) | 25 |
| `dense_1` (Dense) | (None, 1) | 6 |
| **Totale** | | **31** (tutti addestrabili) |

> ⚠️ **Commento sbagliato nel notebook.** La prima cella contiene il commento `# define the 784-256-128-10 architecture using Keras`, residuo di un altro esempio: la rete definita è **4-5-1**, come confermano i 31 parametri.

<!-- SOURCE_VISUAL id="IS07-V09" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="33" type="diagramma" description="Rete 4-5-1 con i pesi e i bias b1-b6" reason="Collegamenti della rete visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.4. Quanti dati? I modelli lineari**

_(slide pp. 35–38)_

| Modello | Forma | Parametri | Dati minimi |
|---|---|---|---|
| **lineare 1D** (p. 35) | $z = w_1 x + b$ (cioè $z = \alpha x + \beta$) | $w_1, b$ → **2** | **2 punti** |
| **lineare 2D** (p. 36) | $z = \mathbf{w} \cdot \mathbf{x} + b$ con $\mathbf{x} = (x_1, x_2)$ | $w_1, w_2, b$ → **3** | **3 punti** |
| **lineare 3D** (p. 37) | $z = \mathbf{w} \cdot \mathbf{x} + b$ con $\mathbf{x} = (x_1, x_2, x_3)$ | $w_1, w_2, w_3, b$ → **4** | **4 punti** |

Per descrivere completamente il modello bisogna **fissare tutti i parametri**, e ogni punto dato fornisce **un’equazione**. Il modello lineare 3D è anche l’**uscita lineare di un neurone**. Secondo la slide, la regola vale **(quasi)** anche per i sistemi **non lineari**.

> 💡 **Perché servono esattamente quei punti (nota didattica).** Con due punti $(x^{(1)}, z^{(1)})$ e $(x^{(2)}, z^{(2)})$, con $x^{(1)} \neq x^{(2)}$, il sistema
> $$\begin{cases} z^{(1)} = w_1 x^{(1)} + b \\ z^{(2)} = w_1 x^{(2)} + b \end{cases}$$
> ha una sola soluzione: la retta passa per i due punti. Con un punto solo ci sono infinite rette possibili; con molti punti rumorosi non esiste una retta che li attraversi tutti, e si cerca quella che **minimizza l’errore**.

> 📌 **Gradi di libertà in generale** (p. 38). I gradi di libertà di un problema sono il **numero di variabili indipendenti** da specificare per determinare **univocamente** una soluzione:
> $$\text{DoF} = \#\text{variabili} - \#\text{equazioni} \;\approx\; \#\text{Par} - \#\text{Data}$$
> dove $\#\text{Data}$ è il **numero di vettori** nel database. Nei sistemi non lineari complessi non è così semplice, ma è una **regola pratica**: quando i parametri addestrabili sono **troppi** rispetto ai dati **indipendenti e informativi**, il modello ha **flessibilità eccessiva** e può andare in **overfitting**.

La slide riporta l’esempio di un database con **#Data = 10** vettori e **#Inputs = 5**.

> 💡 **Applicare la regola all’esempio (nota didattica).** Con 5 input un modello lineare ha $5 + 1 = 6$ parametri: con 10 vettori $\text{DoF} \approx 6 - 10 < 0$, i dati bastano. Una rete 5-2-1 ha invece $(5+1) \cdot 2 + (2+1) \cdot 1 = 15$ parametri: $\text{DoF} \approx 15 - 10 = 5 > 0$, e la rete può «ricordare» i 10 esempi invece di imparare la regola.

<!-- SOURCE_VISUAL id="IS07-V10" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="38" type="schema" description="Gradi di libertà come parametri meno dati, con esempio di database da 10 vettori e 5 input" reason="Formula e tabella del database resi graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.5. Troppi parametri**

_(slide pp. 39–43)_

**Se i parametri sono eccessivi** (p. 39), il metodo di apprendimento **non riesce a gestire** tutti gli elementi inseriti nel modello con il dataset disponibile. Il modello ottenuto **non è ottimale**: alcune parti sono **inutili**, o si ottiene perfino un **comportamento scorretto**. La slide confronta un modello con #Par corretto e uno con #Par eccessivo dopo l’addestramento.

> 📌 **Errore di generalizzazione e gradi di libertà** (p. 40). Se nella rete restano troppi pesi «liberi», cioè **pochi dati rispetto ai parametri** e quindi DoF alto, la **capacità di generalizzazione** tende a essere **scarsa**: l’**errore di cross-validation aumenta**. La slide mostra esempi di errori di generalizzazione di modelli deep learning su grandi dataset standard.

<!-- SOURCE_VISUAL id="IS07-V11" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="39" type="illustrazione" description="Modello con numero di parametri corretto contro eccessivo dopo l’addestramento" reason="Confronto visibile solo nell’illustrazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS07-V12" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="40" type="grafico" description="Errore di cross-validation in funzione dei gradi di libertà ed esempi su grandi dataset" reason="Andamento dell’errore visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Una prospettiva intuitiva: 1 parametro = 1 pixel** (pp. 41–42).

| Modello | Parametri | Come immagine a 1 pixel per parametro | Come superficie |
|---|---|---|---|
| **VGGNet** (2014) | **138 milioni** | circa **11 700 × 11 700** pixel | un **campo da beach volley** |
| **rete feed-forward tradizionale** | **meno di 300** | circa **17 × 17** pixel | un **francobollo** |

Verifica: $\sqrt{138 \times 10^6} \approx 11\,747$ e $17^2 = 289 < 300$. La p. 42 aggiunge che un’immagine da 10 000 × 10 000 pixel ha 100 milioni di pixel, meno dei parametri di VGGNet.

> 💡 **Che cosa insegna il paragone.** Addestrare VGGNet significa «dipingere» correttamente ogni pixel di un’immagine gigantesca usando i dati: servono **milioni di immagini** etichettate, come quelle di ImageNet. Un’applicazione industriale con qualche centinaio di campioni ha dati appena sufficienti per il «francobollo».

<!-- SOURCE_VISUAL id="IS07-V13" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="41" type="illustrazione" description="VGGNet con 138 milioni di parametri come campo da beach volley contro rete tradizionale come francobollo" reason="Confronto visivo delle superfici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS07-V14" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="42" type="illustrazione" description="Un parametro per pixel: immagine di VGGNet contro immagine 17x17" reason="Confronto visivo delle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ **In breve** (p. 43). Il numero di parametri del modello va **regolato con cura** in base a:
> - la **dimensione dei dataset**: numero di vettori e numero di input;
> - la loro **complessità**: immagini simili o esempi molto diversi?
>
> «**Andare in profondità** solo se è davvero necessario»: è un’applicazione del **rasoio di Occam** (Lezione 05, §3.2).

---

### **3. Data leakage**

_(slide pp. 44–72)_

#### **3.1. Che cos’è**

Il data leakage è **uno dei fattori più rilevanti e limitanti** (p. 44) e riguarda il **passo 2** del flusso di lavoro, la preparazione dei dati (p. 45).

> 📌 **Data leakage** (p. 46). È la causa di modelli di ML o deep learning **non validi**, per un’**ottimizzazione eccessiva** del modello applicato. La vignetta della slide: «Ho fatto un ottimo apprendimento!» → **dopo il rilascio** → «Accidenti, era in overfitting!».

<!-- SOURCE_VISUAL id="IS07-V15" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="46" type="vignetta" description="Modello soddisfatto dell’apprendimento che scopre l’overfitting dopo il rilascio" reason="Vignetta della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Due problemi principali** (p. 47):

| | Problema | Idea |
|---|---|---|
| **A** | **problemi di qualità dei dati**: mancano feature rilevanti | ai dati **manca** qualcosa |
| **B** | **data leakage** in senso stretto | ai dati si **aggiunge** qualcosa che non dovrebbe esserci |

La slide mostra due file di dati: un campione sano `Health_0001.dat` e uno malato `Ill_0001.dat`, con valori quasi identici.

> 💡 **Un indizio nel nome del file (nota didattica).** Se l’etichetta è scritta nel **nome del file** («Health» o «Ill») e quel nome, o un identificativo derivato, finisce tra le feature, il modello impara a leggere il nome invece dei valori clinici: prestazioni perfette in laboratorio, inutilità totale su pazienti nuovi.

<!-- SOURCE_VISUAL id="IS07-V16" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="47" type="esempio" description="File Health_0001.dat e Ill_0001.dat con valori simili e classi diverse" reason="Contenuto dei file mostrato solo graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. A) Mancano feature rilevanti**

_(slide pp. 48–56)_

Se si vuole usare una feature per l’analisi predittiva, ma quella feature **non è presente** al momento dell’addestramento, si introduce leakage nel modello (p. 48). **Esempio**: si vuole aggiungere al dataset la concentrazione dell’**ormone X** per predire il **tumore Z**, ma l’ormone X **non è (quasi) presente** nel dataset di addestramento dei soggetti sani.

**Esempio 1: cani e gatti** (pp. 49–56). Nel dataset di **addestramento**:

- tutti i **cani** sono **gialli**;
- tutti i **gatti** hanno le **orecchie dritte**.

Che cosa c’è che non va? È probabile l’**overfitting**: il sistema **non impara le vere differenze** tra cani e gatti, ma **regole semplificate**: «i cani sono gialli», «i gatti hanno le orecchie dritte» (p. 50).

| Fase di test (pp. 51–55) | Esito |
|---|---|
| cani gialli e gatti con le orecchie dritte, come in addestramento | **buona generalizzazione**… apparente |
| un **cane piccolo e grigio** con le **orecchie dritte** | **errore di generalizzazione** |
| un **gatto giallo** | **errore di generalizzazione** |
| un gatto con le **orecchie abbassate** | **errore di generalizzazione** |

> 📌 **Soluzione** (p. 56). Servono **più immagini «salienti»** perché il sistema capisca quali sono le **feature importanti** per distinguere un gatto da un cane: così il leakage si riduce.

> 💡 **Una precisazione terminologica (nota didattica).** Il docente fa rientrare l’esempio nel data leakage di tipo A, perché le feature «dicono troppo» (p. 64). In letteratura lo stesso fenomeno si chiama spesso **bias del dataset** o *shortcut learning*: il modello sfrutta una **scorciatoia** (il colore) che nei dati di addestramento è correlata con la classe per caso. Il rimedio è lo stesso: dati **più vari** e rappresentativi.

<!-- SOURCE_VISUAL id="IS07-V17" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="50" type="immagini" description="Dataset di addestramento con cani gialli e gatti con orecchie dritte" reason="Immagini del dataset visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS07-V18" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="53" type="immagini" description="Errori di generalizzazione in test: cane piccolo grigio con orecchie dritte" reason="Immagini del test visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS07-V19" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="54" type="immagini" description="Errore di generalizzazione in test: gatto giallo" reason="Immagini del test visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. B) Si aggiunge qualcosa in più**

_(slide pp. 57–61)_

> 📌 **Leakage di tipo B** (p. 57). Si ha quando per creare il modello si usano **informazioni esterne** a quelle «attese» per l’addestramento.
> - L’informazione in più permette al modello di **imparare qualcosa che altrimenti non saprebbe**, e **invalida la stima delle prestazioni**.
> - La stima delle prestazioni **dopo il rilascio** risulta **troppo ottimistica**.
>
> Secondo il docente è **uno dei casi più subdoli**, e probabilmente il primo caso di data leakage critico.

**Esempio 2: cellule sane e leucemiche** (pp. 58–59). Immagini di globuli bianchi per addestrare un classificatore **sano / leucemia**:

- le cellule **sane** vengono dall’**ospedale A**;
- le cellule **leucemiche** vengono dall’**ospedale B**.

Le immagini dell’ospedale B sono **leggermente più grandi**, di **colore più chiaro** e con un **livello di rumore diverso**. Il modello può imparare a controllare **solo dimensione, colore e rumore** («ospedale B = tumore») invece della **forma** delle cellule.

> 💡 **Come si evita (nota didattica).** Ogni ospedale deve fornire **entrambe** le classi, e le immagini vanno **armonizzate** (dimensione, colore, rumore) prima dell’addestramento. Un test utile: addestrare un modello a riconoscere **l’ospedale** di provenienza; se ci riesce facilmente, anche il classificatore di malattia può sfruttare quell’informazione.

<!-- SOURCE_VISUAL id="IS07-V20" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="59" type="immagini" description="Cellule sane dall’ospedale A e leucemiche dall’ospedale B con differenze di dimensione, colore e rumore" reason="Differenze tra le immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Come può avvenire il leakage** (pp. 60–61):

**B1) Dal test all’addestramento** (p. 60). Dati pensati **solo per verificare la generalità** del modello finiscono nel **dataset di addestramento**. Con porzioni di addestramento $T$ e di validazione/test $V$, a partire da $[T_1, T_2, T_3] + [V_1, V_2, V_3]$:

| Gravità | Dopo il leakage | Che cosa è successo |
|---|---|---|
| **leggermente grave** | $[T_1, T_2, T_3] + [V_1, V_2, V_3, T_3]$ | una porzione di **addestramento** è finita nel **test** |
| **molto grave** | $[T_1, T_2, T_3, V_1] + [V_1, V_2, V_3]$ | una porzione di **validazione** è finita nell’**addestramento**: $V_1$ **non è più un dato mai visto** |

Il caso inverso (addestramento nel test) è «leggermente migliore, ma comunque non ideale».

> 💡 **Perché uno è peggio dell’altro (nota didattica).** Se $V_1$ entra nell’addestramento, il modello ha già visto una parte del test: l’errore misurato su $V_1$ è falsamente basso e **tutta la stima** risulta ottimistica. Se $T_3$ entra nel test, il test contiene dati già visti e l’errore è ottimistico su quella porzione, ma l’addestramento resta pulito.

<!-- SOURCE_VISUAL id="IS07-V21" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="60" type="schema" description="Leakage tra porzioni di addestramento T e di validazione V: casi leggermente e molto gravi" reason="Schema delle porzioni visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**B2) Dati futuri nei dati passati** (p. 61): informazioni che nella realtà sarebbero disponibili solo **dopo** l’istante della predizione entrano nei dati usati per addestrare (§3.5).

**B3) Uso dei dati fuori dall’ambito dell’algoritmo** (p. 61). Esempio: un classificatore per la diagnosi addestrato su un dataset di **adulti** e usato anche per i **bambini**.

#### **3.4. Esempio 3: il «déjà vu»**

_(slide pp. 62–64)_

Nel dataset di addestramento e in quello di test compaiono cani e gatti **apparentemente diversi**. Dov’è il problema (p. 62)? Il **«déjà vu»** (p. 63, solo un riferimento mnemonico): il **riuso degli stessi campioni**, o di **parti ritagliate** di essi, oppure il riuso degli **stessi utenti**, produce una **generalizzazione scarsa**. **L’indipendenza statistica è rilevante!**

> 💡 **Un esempio concreto (nota didattica).** In un sistema di riconoscimento vocale, se le registrazioni della stessa persona finiscono sia in addestramento sia in test, il modello può riconoscere **la voce** invece delle **parole**. La regola è dividere per **utente** (o per paziente, per macchina, per sessione), non per singolo campione.

<!-- SOURCE_VISUAL id="IS07-V22" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="63" type="immagini" description="Déjà vu: gli stessi campioni o loro ritagli in addestramento e test" reason="Campioni riusati visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **In breve: due fonti principali di data leakage** (p. 64).
> - **A. Attributi delle feature**: le variabili **dicono troppo**.
> - **B. Dataset di addestramento**: porzioni di dati usate **nella fase sbagliata**.

#### **3.5. Serie temporali: un caso speciale**

_(slide pp. 65–68)_

| | **Previsione di serie temporali** | **Classificazione di serie temporali** |
|---|---|---|
| Definizione (p. 65) | **stimare valori futuri** in base ai dati passati | **assegnare etichette** in base ai dati passati |
| Industria | predire **guasti delle macchine** (manutenzione predittiva) | rilevare **anomalie** nei dati dei sensori |
| Supply chain | prevedere la **domanda** di prodotto | individuare **pattern di domanda** per categorie di prodotto |
| Ambiente | **previsioni meteo**, previsione della temperatura | classificare la **qualità dell’aria** dai sensori |
| Finanza | previsione dei **prezzi delle azioni**, tendenze economiche | rilevamento di **frodi** nelle sequenze di transazioni |
| Sanità | previsione dei **parametri vitali** del paziente | classificazione dell’**ECG** per le cardiopatie |

> ⚠️ **Il problema** (p. 65). In entrambi i casi il modello funziona solo se i dati passati sono **coerenti**, cioè **non mescolati**, sia in **addestramento** (input del metodo di apprendimento) sia in **inferenza** (input del modello).

**Controllare il leakage nelle serie temporali** (p. 68). Dividere una serie temporale in addestramento e test è un problema **soggetto a errori**: bisogna fare **molta attenzione**. Negli schemi della slide, con certe suddivisioni la **continuità temporale** è garantita solo «a breve termine»: nella prima suddivisione i dati di **validazione** sono **nel passato** rispetto all’addestramento, nella seconda l’addestramento ha una **discontinuità temporale**. In un altro schema la continuità temporale è **pienamente garantita**.

> 📌 **Validazione «in avanti» (nota didattica).** Lo schema sicuro addestra sempre sul passato e verifica sul futuro immediato, allargando progressivamente la finestra:
>
> | Iterazione | Addestramento | Test |
> |---|---|---|
> | 1 | $[t_1, t_k]$ | $[t_{k+1}, t_{2k}]$ |
> | 2 | $[t_1, t_{2k}]$ | $[t_{2k+1}, t_{3k}]$ |
> | 3 | $[t_1, t_{3k}]$ | $[t_{3k+1}, t_{4k}]$ |
>
> Una suddivisione **casuale** dei campioni, normale per dati indipendenti, qui mette **il futuro nell’addestramento** (leakage di tipo B2).

<!-- SOURCE_VISUAL id="IS07-V23" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="68" type="schema" description="Suddivisioni di una serie temporale con continuità temporale parziale o pienamente garantita" reason="Posizione delle porzioni nel tempo visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.6. Il problema del ritaglio (cropping)**

_(slide pp. 69–72)_

Il leakage si osserva anche in dataset **complessi legati al tempo** (p. 69):

- osservazioni analogiche memorizzate come **audio e immagini** in file separati, con **dimensione e marca temporale** definite;
- il **campionamento** in un problema grafico, per esempio **ritagliare un’immagine**, è un compito complesso.

Esempi della slide:

- **audio**: il frammento selezionato per addestrare la parola «**YES**» è **troppo lungo** e contiene anche «**YOU**» (dalla frase «yes, you are»);
- **immagini**: la **cellula** selezionata è **ritagliata male**, con **parti mancanti** o **altre cellule** nel riquadro.

<!-- SOURCE_VISUAL id="IS07-V24" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="69" type="esempi" description="Ritaglio sbagliato: frammento audio YES che contiene YOU e cellula ritagliata male" reason="Forme d’onda e ritagli visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Il problema del ritaglio** (p. 70). È una forma di data leakage **molto diffusa**, che colpisce molti tipi di dataset e applicazioni. Si verifica quando un modello **impara pattern da dati ritagliati** o **distorti dal contesto**: prestazioni **ingannevolmente alte** e **generalizzazione scarsa**.

**Esempi in domini diversi** (p. 71):

| Dominio | Che cosa impara il modello invece del contenuto |
|---|---|
| **immagini** | i **bordi del ritaglio** o gli **artefatti** della pre-elaborazione, invece delle feature dell’oggetto |
| **audio** | il **rumore di fondo** dei campioni ritagliati, invece delle parole pronunciate |
| **dati strutturati** | in diagnosi medica, se l’addestramento contiene **solo i casi estremi**, il modello **fallisce sui casi intermedi** |
| **testo** | un modello di sentiment addestrato su post dei social si affida a **frasi troncate** invece del contesto completo |

**Evitare con cura le feature indesiderate** (p. 72): la slide cita **cm e mm** accanto al ritaglio.

> 💡 **Il righello nell’immagine (nota didattica).** Un caso noto in dermatologia: nelle foto delle lesioni sospette i medici appoggiavano spesso un **righello** graduato. Un classificatore di tumori della pelle ha imparato che «righello presente = lesione maligna». La scala in cm e mm è una **feature indesiderata** che il ritaglio doveva escludere. L’abbinamento esatto con la figura della slide va verificato sull’immagine.

<!-- SOURCE_VISUAL id="IS07-V25" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="72" type="immagini" description="Feature indesiderate da evitare: scale in cm e mm e ritaglio" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **`x`** / **`y`** nel laboratorio | matrice delle feature (150 × 4) / vettore delle classi (150) |
| **Slicing per colonna** / **per riga** | una feature `xNumpy[:, j]` / un campione `xNumpy[i, :]` |
| **Indice 1 in Python** / **in MATLAB** | secondo elemento / primo elemento |
| **DoF** / **#Par** | parametri indipendenti che definiscono la configurazione / numero di pesi del modello: legati, non identici |
| **DoF < 0** / **DoF > 0** | più dati che parametri: modello determinato / più parametri che dati: flessibilità eccessiva e overfitting |
| **Leakage A** / **leakage B** | le feature dicono troppo o manca una feature rilevante / dati usati nella fase sbagliata o informazioni esterne |
| **Addestramento nel test** / **test nell’addestramento** | leggermente grave / molto grave |
| **Leakage B1** / **B2** / **B3** | dal test all’addestramento / dal futuro al passato / fuori dall’ambito (adulti → bambini) |
| **Divisione per campione** / **per utente** | può mettere la stessa persona in addestramento e test / garantisce l’indipendenza statistica |
| **Previsione** / **classificazione di serie temporali** | valori futuri / etichette sulla sequenza |
| **Suddivisione casuale** / **in avanti** | adatta a dati indipendenti / obbligatoria per le serie temporali |

---

### **5. Sintesi della lezione**

_(slide p. 73)_

> ✅ **Punti principali** (p. 73).
> - Usare un dataset **senza attività preliminari** è **inutile e pericoloso**, e fa perdere tempo: caricare, controllare con `info()` e `head()`, separare feature e classi, verificare con lo slicing.
> - **Numero di gradi di libertà / parametri**: ogni parametro va «fissato» con i dati; per un modello lineare servono tanti punti quanti parametri, e in generale $\text{DoF} \approx \#\text{Par} - \#\text{Data}$. Troppi parametri rispetto ai dati → overfitting e generalizzazione scarsa. Una rete 4-5-1 ha già 31 parametri; VGGNet ne ha 138 milioni.
> - **Data leakage**: feature che dicono troppo (cani gialli, ospedale A e B), porzioni di test nell’addestramento, futuro nel passato, riuso di campioni e utenti, ritagli sbagliati. Il risultato è sempre lo stesso: ottime prestazioni in laboratorio, delusione **dopo il rilascio**.
