## **Lezione 7: Dataset in Python, numero di parametri e data leakage**

Il dataset **Iris** offre un caso concreto per imparare a caricare e controllare dati tabellari. Da qui si passa a due questioni centrali della progettazione: quanti parametri ha un modello e quali dati servono per stimarli; come il **data leakage** può rendere le prestazioni misurate molto più alte di quelle ottenute dopo il rilascio.


### **0. Struttura della lezione**


La trattazione si articola nei seguenti temi:

1. **laboratorio** in Python/Colab sulla gestione dei dati di addestramento;
2. **numero di gradi di libertà / parametri**;
3. **data leakage**;
4. punti principali.

---

### **1. Laboratorio: caricare e gestire il dataset Iris**


#### **1.1. Il laboratorio e l’esame**

Il laboratorio usa il dataset **Iris** e presenta due ambienti, **Colab** e **MATLAB**. I passaggi iniziali comprendono preparazione dell’ambiente, caricamento dei dati e analisi preliminare; la stessa procedura prepara alla visualizzazione, all’addestramento e al confronto dei modelli.

> 📌 **Che cosa c’è all’esame**.
> - All’esame **non si scrive codice** (esatto).
> - È però importante studiare l’**applicazione pratica** dei concetti teorici, per capirli meglio.
> - Si può essere chiamati a **commentare** o **rispondere su esempi di codice**.

**Colab**. Un file **IPYNB** è un **notebook** di Jupyter: un ambiente di calcolo interattivo per lavorare con Python e con i propri dati. Il notebook si carica su Colab; poi si **leggono i commenti** e si esegue ogni cella con il pulsante **[ ]**, oppure con «**Run all**».

Lo stesso esercizio si può svolgere con il notebook preparato, uno script Python 3, un agente di coding oppure una chat che esegue codice in un ambiente remoto temporaneo. Cambia l’interfaccia, non la logica: leggere il file, assegnare le colonne, ispezionare il DataFrame, separare $X$ e $y$, controllare le forme e salvare il grafico. Un agente può scrivere ed eseguire i passaggi in risposta a istruzioni in linguaggio naturale; vanno comunque letti il codice e i risultati, verificando che la classe non sia finita fra le feature e che non ci siano valori mancanti inattesi.

<!-- SOURCE_VISUAL id="IS07-V26" source="L07-web.pdf" page="9" type="schermata" description="Esempio di richiesta a un agente di coding per caricare Iris, controllare colonne e salvare un grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS07-V01" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="8" type="schermata" description="Esecuzione delle celle di un notebook in Colab: pulsante della cella e Run all" reason="Interfaccia visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Ambiente e caricamento dei dati**

**Preparazione dell’ambiente**:

| Libreria | Ruolo |
|---|---|
| **pandas** | pre-elaborazione dei dataset |
| **NumPy** | manipolazione di **array e matrici** multidimensionali, con una vasta raccolta di funzioni matematiche di alto livello |
| **matplotlib** (`pyplot`) | esplorazione e visualizzazione dei dati |

**Caricamento**: si legge il file **CSV** di Iris con pandas, si ottiene un riepilogo organizzato con `dataset.info()` e si guardano i primi $n$ campioni con `dataset.head(n)`.

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

**Prima di tutto si separano le feature dalle classi**. Feature e classi richiedono **pre-elaborazioni diverse**. È davvero necessario? **Sì, per evitare il data leakage** (§3). La proprietà `shape` corrisponde a `size()` in MATLAB.

```python
y = dataset['iris_class']
x = dataset.drop(['iris_class'], axis=1)

print("dataset : ", dataset.shape)   # (150, 5)
print("x : ", x.shape)               # (150, 4)
print("y : ", y.shape)               # (150,)
```

La **matrice delle feature** ha **4 colonne**, il **vettore dei target** ha **1 colonna**. Poi si convertono i dati in **matrici NumPy**, per accedere facilmente a porzioni dei dati:

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

| Operazione | Sintassi | Risultato nel notebook |
|---|---|---|
| leggere **una feature** | `xNumpy[:, j]` | colonna di 150 valori; con `j = 1` è la **larghezza del sepalo** (3.5, 3.0, 3.2, …) |
| leggere **un campione** | `xNumpy[i, :]` | riga di 4 valori; con `i = 1` è il **secondo** campione, `[4.9 3.0 1.4 0.2]` |

Il **debugging** parte da controlli semplici: stampare porzioni di matrice permette di verificare che i dati siano quelli attesi.

> ⚠️ **Gli indici partono da 0.** In Python `feature_to_extract = 1` è la **seconda** colonna (`sepal_width`) e `sample_to_extract = 1` è il **secondo** campione. In MATLAB gli indici partono da 1: lo stesso numero indica elementi diversi. È un errore tipico quando si passa da un ambiente all’altro.

> ⚠️ **Lo script esportato non gira in Python 3.** Il file `Lesson_07__laboratory_colab_iris_dataset.py` termina con `raw_input('Waiting a key...')`, funzione che esiste solo in Python 2. In Python 3 si usa `input(...)`; il notebook, che non contiene quella riga, funziona regolarmente.

<!-- SOURCE_VISUAL id="IS07-V03" source="Lesson_07_NumberOfParameters_DataLeakage.pdf" page="19" type="schermata" description="Slicing per colonna in Colab con indicazione di debugging" reason="Schermata dell’output" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Gradi di libertà e numero di parametri**


#### **2.1. Dati e modello: che cosa sappiamo davvero?**

Le domande della sezione: **quanto è complesso** il modello? **Quanti dati** servono per configurarlo? Il tema riguarda i **passi 1 e 3** del flusso di lavoro del ML: raccolta dei dati e modello.

Il principio **GIGO** resta valido: dati di bassa qualità compromettono l’apprendimento. Il problema si aggrava se si aggiungono pre-filtraggio o ingegneria delle feature inadeguati, una scelta sbagliata del modello, un addestramento scorretto o overfitting.

![](imgs/Pasted%20image%2020261122133842.png)

**Lo spettro dati-conoscenza**. Le reti neurali tradizionali e le **reti profonde** si collocano verso l’estremo in cui si sa **poco** del processo e servono **molti dati**. La domanda da porsi: che cosa sai davvero

1. dei **dati** che hai e della tua **applicazione**?
2. del **modello di AI** che vuoi applicare?

![](imgs/Pasted%20image%2020261122133834.png)

#### **2.2. L’analogia meccanica**

> 📌 **Gradi di libertà**. In fisica, i **gradi di libertà** (DoF, *Degrees of Freedom*) di un sistema meccanico sono il **numero di parametri indipendenti** che ne definiscono la configurazione. Un piccolo componente meccanico nel piano ha **3 gradi di libertà**: le coordinate $x$, $y$ del centro di massa e l’angolo $\alpha$.

> ⚠️ **DoF e #Par non sono la stessa cosa**. Per sistemi complessi i gradi di libertà **non coincidono esattamente** con il numero di parametri (#Par), ma sono **fortemente legati**.

**Un insieme di componenti**. Un modello meccanico, **come una rete neurale**, è un insieme di **elementi interconnessi**, ciascuno con i propri parametri:

$$\text{PZ}_1 = \{x_1, y_1, \alpha_1\}, \quad \text{PZ}_2 = \{x_2, y_2, \alpha_2\}, \quad \text{PZ}_3 = \{x_3, y_3, \alpha_3\}, \quad \dots$$

Il modello è **completo** quando tutti gli elementi sono **posizionati**, cioè quando i loro parametri sono **fissati**; per questo conteggio si trascurano le differenze tra tipi di vincolo.

![](imgs/Pasted%20image%2020261122133908.png)

**Similitudine 1: pesi e parametri**. **Prima dell’addestramento** i pesi della rete **non sono configurati**, come componenti non ancora posizionati; **dopo l’addestramento** sono **configurati**.

**Similitudine 2: input, output, comportamento**. Una struttura meccanica riceve quattro input: $x_1$ è uno **spostamento** (m), $x_2, x_3, x_4$ sono **forze** (N); l’output è una **deformazione** (mm).

| | **Problema 1: rete neurale** | **Problema 2: struttura meccanica** |
|---|---|---|
| Che cosa si configura | i **pesi** della rete | i **parametri** degli elementi |
| Obiettivo | classificare correttamente in uscita gli input $[x_1, \dots, x_4]$ | ottenere una **deformazione minima** in uscita per tutti gli input (la figura cita $x_1$ e $x_2$) |
| Criterio | **errore minimo** in apprendimento | **deformazione minima** in addestramento |

![](imgs/Pasted%20image%2020261122134253.png)

![](imgs/Pasted%20image%2020261122134330.png)

#### **2.3. Contare i parametri di una rete neurale**

**Esempio**. Una rete con **4 input**, **un solo strato nascosto di 5 neuroni** e **1 neurone di uscita**:

| Strato | Conteggio | Parametri |
|---|---|---|
| nascosto | $4 \times 5$ pesi + 5 bias ($b_1, \dots, b_5$) | **25** |
| uscita | 5 connessioni + 1 bias ($b_6$) | **6** |
| **totale** | | **31** |

Quanti input $[x_1, x_2, x_3, x_4]$ servono in addestramento per **fissare correttamente** 31 pesi?

> 📌 **Parametri di uno strato denso (nota didattica).** Uno strato completamente connesso con $n_{\text{in}}$ ingressi e $n_{\text{out}}$ neuroni ha
> $$\#\text{Par} = n_{\text{in}} \cdot n_{\text{out}} + n_{\text{out}} = (n_{\text{in}} + 1)\, n_{\text{out}}$$
> Per la rete 4-5-1: $(4+1) \cdot 5 + (5+1) \cdot 1 = 25 + 6 = 31$.

**Verifica in Colab**:

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

![](imgs/Pasted%20image%2020261122143908.png)

#### **2.4. Quanti dati? I modelli lineari**


| Modello | Forma | Parametri | Dati minimi |
|---|---|---|---|
| **lineare 1D** | $z = w_1 x + b$ (cioè $z = \alpha x + \beta$) | $w_1, b$ → **2** | **2 punti** |
| **lineare 2D** | $z = \mathbf{w} \cdot \mathbf{x} + b$ con $\mathbf{x} = (x_1, x_2)$ | $w_1, w_2, b$ → **3** | **3 punti** |
| **lineare 3D** | $z = \mathbf{w} \cdot \mathbf{x} + b$ con $\mathbf{x} = (x_1, x_2, x_3)$ | $w_1, w_2, w_3, b$ → **4** | **4 punti** |

![](imgs/Pasted%20image%2020261122174233.png)

![](imgs/Pasted%20image%2020261122174243.png)

![](imgs/Pasted%20image%2020261122174253.png)

Per descrivere completamente il modello bisogna **fissare tutti i parametri**, e ogni punto dato fornisce **un’equazione**. Il modello lineare 3D è anche l’**uscita lineare di un neurone**. La stessa approssimazione può essere utile anche per sistemi **non lineari**, pur senza valere in modo esatto.

> 💡 **Perché servono esattamente quei punti (nota didattica).** Con due punti $(x^{(1)}, z^{(1)})$ e $(x^{(2)}, z^{(2)})$, con $x^{(1)} \neq x^{(2)}$, il sistema
> $$\begin{cases} z^{(1)} = w_1 x^{(1)} + b \\ z^{(2)} = w_1 x^{(2)} + b \end{cases}$$
> ha una sola soluzione: la retta passa per i due punti. Con un punto solo ci sono infinite rette possibili; con molti punti rumorosi non esiste una retta che li attraversi tutti, e si cerca quella che **minimizza l’errore**.

> 📌 **Gradi di libertà in generale**. I gradi di libertà di un problema sono il **numero di variabili indipendenti** da specificare per determinare **univocamente** una soluzione:
> $$\text{DoF} = \#\text{variabili} - \#\text{equazioni} \;\approx\; \#\text{Par} - \#\text{Data}$$
> dove $\#\text{Data}$ è il **numero di vettori** nel database. Nei sistemi non lineari complessi non è così semplice, ma è una **regola pratica**: quando i parametri addestrabili sono **troppi** rispetto ai dati **indipendenti e informativi**, il modello ha **flessibilità eccessiva** e può andare in **overfitting**.

Un esempio considera un database con **#Data = 10** vettori e **#Inputs = 5**.

> 💡 **Applicare la regola all’esempio (nota didattica).** Con 5 input un modello lineare ha $5 + 1 = 6$ parametri: con 10 vettori $\text{DoF} \approx 6 - 10 < 0$, i dati bastano. Una rete 5-2-1 ha invece $(5+1) \cdot 2 + (2+1) \cdot 1 = 15$ parametri: $\text{DoF} \approx 15 - 10 = 5 > 0$, e la rete può «ricordare» i 10 esempi invece di imparare la regola.

![](imgs/Pasted%20image%2020261122174412.png)

#### **2.5. Troppi parametri**


**Se i parametri sono eccessivi**, il metodo di apprendimento **non riesce a gestire** tutti gli elementi inseriti nel modello con il dataset disponibile. Il modello ottenuto **non è ottimale**: alcune parti sono **inutili**, o si ottiene perfino un **comportamento scorretto**. Il confronto tra un modello con un numero adeguato di parametri e uno sovradimensionato mostra come il secondo possa adattarsi male ai dati disponibili.

> 📌 **Errore di generalizzazione e gradi di libertà**. Se nella rete restano troppi pesi «liberi», cioè **pochi dati rispetto ai parametri** e quindi DoF alto, la **capacità di generalizzazione** tende a essere **scarsa**: l’**errore di cross-validation aumenta**. Anche modelli profondi valutati su dataset standard possono mostrare errori di generalizzazione quando la capacità supera l’informazione disponibile.

![](imgs/Pasted%20image%2020261123020506.png)

![](imgs/Pasted%20image%2020261123020534.png)

**Una prospettiva intuitiva: 1 parametro = 1 pixel**.

| Modello | Parametri | Come immagine a 1 pixel per parametro | Come superficie |
|---|---|---|---|
| **VGGNet** (2014) | **138 milioni** | circa **11 700 × 11 700** pixel | un **campo da beach volley** |
| **rete feed-forward tradizionale** | **meno di 300** | circa **17 × 17** pixel | un **francobollo** |

![](imgs/Pasted%20image%2020261123020648.png)

Verifica: $\sqrt{138 \times 10^6} \approx 11\,747$ e $17^2 = 289 < 300$. La aggiunge che un’immagine da 10 000 × 10 000 pixel ha 100 milioni di pixel, meno dei parametri di VGGNet.

> 💡 **Il confronto è intuitivo, non una formula di campionamento.** I parametri non corrispondono letteralmente a pixel né determinano da soli il numero di immagini necessario: contano anche struttura, regolarizzazione, diversità, qualità ed effettiva informatività dei dati. L’analogia rende visibile la scala dei pesi fra una rete piccola e VGGNet.

![](imgs/Pasted%20image%2020261123020735.png)

Il confronto con un backbone visivo più recente chiarisce che le dimensioni continuano a crescere: **DINOv3 ViT-7B** (ViT, *Vision Transformer*, trasformatore per immagini) (2025) è riportato con circa 7 miliardi di parametri e pesi aperti; a 16 bit i pesi occupano circa 14 GB, quindi l’esecuzione locale richiede una GPU di fascia alta. La dimensione dei pesi da sola non descrive i requisiti completi di inferenza: contano anche attivazioni, risoluzione, batch, contesto, precisione numerica e implementazione. Rimane il criterio progettuale: scegliere la capacità in rapporto alla quantità e complessità dei dati e ai vincoli del deployment.

![](imgs/Pasted%20image%2020261123020830.png)

> ✅ **In breve**. Il numero di parametri del modello va **regolato con cura** in base a:
> - la **dimensione dei dataset**: numero di vettori e numero di input;
> - la loro **complessità**: immagini simili o esempi molto diversi?
>
> «**Andare in profondità** solo se è davvero necessario»: scegliere un modello più complesso quando il compito e i dati lo giustificano è un’applicazione del **rasoio di Occam**.

---

### **3. Data leakage**

![](imgs/Pasted%20image%2020261123020903.png)

#### **3.1. Che cos’è**

Il data leakage è **uno dei fattori più rilevanti e limitanti** e riguarda il **passo 2** del flusso di lavoro, la preparazione dei dati.

![](imgs/Pasted%20image%2020261123021053.png)

> 📌 **Data leakage**. È la causa di modelli di ML o deep learning **non validi**, per un’**ottimizzazione eccessiva** del modello applicato. La vignetta riassume il rischio: un modello può apparire eccellente durante l’addestramento e rivelare l’overfitting soltanto dopo il rilascio.

![](imgs/Pasted%20image%2020261123021113.png)

**Due problemi principali**:

| | Problema | Idea |
|---|---|---|
| **A** | **problemi di qualità dei dati**: mancano feature rilevanti | ai dati **manca** qualcosa |
| **B** | **data leakage** in senso stretto | ai dati si **aggiunge** qualcosa che non dovrebbe esserci |

L’esempio confronta due file di dati: un campione sano `Health_0001.dat` e uno malato `Ill_0001.dat`, con valori quasi identici.

> 💡 **Un indizio nel nome del file (nota didattica).** Se l’etichetta è scritta nel **nome del file** («Health» o «Ill») e quel nome, o un identificativo derivato, finisce tra le feature, il modello impara a leggere il nome invece dei valori clinici: prestazioni perfette in laboratorio, inutilità totale su pazienti nuovi.

![](imgs/Pasted%20image%2020261123021124.png)

#### **3.2. A) Mancano feature rilevanti**


Se si vuole usare una feature per l’analisi predittiva, ma quella feature **non è presente** al momento dell’addestramento, si introduce leakage nel modello. **Esempio**: si vuole aggiungere al dataset la concentrazione dell’**ormone X** per predire il **tumore Z**, ma l’ormone X **non è (quasi) presente** nel dataset di addestramento dei soggetti sani.

![](imgs/Pasted%20image%2020261123021204.png)

**Esempio 1: cani e gatti**. Nel dataset di **addestramento**:

- tutti i **cani** sono **gialli**;
- tutti i **gatti** hanno le **orecchie dritte**.

Che cosa c’è che non va? È probabile l’**overfitting**: il sistema **non impara le vere differenze** tra cani e gatti, ma **regole semplificate**: «i cani sono gialli», «i gatti hanno le orecchie dritte».

![](imgs/Pasted%20image%2020261123021305.png)

![](imgs/Pasted%20image%2020261123021315.png)

| Fase di test | Esito |
|---|---|
| cani gialli e gatti con le orecchie dritte, come in addestramento | **buona generalizzazione**… apparente |
| un **cane piccolo e grigio** con le **orecchie dritte** | **errore di generalizzazione** |
| un **gatto giallo** | **errore di generalizzazione** |
| un gatto con le **orecchie abbassate** | **errore di generalizzazione** |

> 📌 **Soluzione**. Servono **più immagini «salienti»** perché il sistema capisca quali sono le **feature importanti** per distinguere un gatto da un cane: così il leakage si riduce.

> 💡 **Scorciatoie spurie.** L’esempio rientra nella famiglia delle feature che «dicono troppo»: più precisamente, il modello sfrutta una scorciatoia (*shortcut learning*), come il colore, correlata per caso con la classe nei dati di addestramento. Servono esempi più vari e rappresentativi, oltre a controlli che verifichino quali segnali usa il modello.

![](imgs/Pasted%20image%2020261123021414.png)

---

#### **3.3. B) Si aggiunge qualcosa in più**


> 📌 **Leakage di tipo B**. Si ha quando per creare il modello si usano **informazioni esterne** a quelle «attese» per l’addestramento.
> - L’informazione in più permette al modello di **imparare qualcosa che altrimenti non saprebbe**, e **invalida la stima delle prestazioni**.
> - La stima delle prestazioni **dopo il rilascio** risulta **troppo ottimistica**.
>
> È **uno dei casi più subdoli** di data leakage: la correlazione fra ospedale e classe può far sembrare diagnostico un semplice indizio del centro di acquisizione.

![](imgs/Pasted%20image%2020261123021451.png)

**Esempio 2: cellule sane e leucemiche**. Immagini di globuli bianchi per addestrare un classificatore **sano / leucemia**:

- le cellule **sane** vengono dall’**ospedale A**;
- le cellule **leucemiche** vengono dall’**ospedale B**.

Le immagini dell’ospedale B sono **leggermente più grandi**, di **colore più chiaro** e con un **livello di rumore diverso**. Il modello può imparare a controllare **solo dimensione, colore e rumore** («ospedale B = tumore») invece della **forma** delle cellule.

> 💡 **Come si evita (nota didattica).** Ogni ospedale deve fornire **entrambe** le classi, e le immagini vanno **armonizzate** (dimensione, colore, rumore) prima dell’addestramento. Un test utile: addestrare un modello a riconoscere **l’ospedale** di provenienza; se ci riesce facilmente, anche il classificatore di malattia può sfruttare quell’informazione.

**Come può avvenire il leakage**:

**B1) Dal test all’addestramento**. Dati pensati **solo per verificare la generalità** del modello finiscono nel **dataset di addestramento**. Con porzioni di addestramento $T$ e di validazione/test $V$, a partire da $[T_1, T_2, T_3] + [V_1, V_2, V_3]$:

| Gravità | Dopo il leakage | Che cosa è successo |
|---|---|---|
| **leggermente grave** | $[T_1, T_2, T_3] + [V_1, V_2, V_3, T_3]$ | una porzione di **addestramento** è finita nel **test** |
| **molto grave** | $[T_1, T_2, T_3, V_1] + [V_1, V_2, V_3]$ | una porzione di **validazione** è finita nell’**addestramento**: $V_1$ **non è più un dato mai visto** |

Il caso inverso (addestramento nel test) è «leggermente migliore, ma comunque non ideale».

> 💡 **Perché uno è peggio dell’altro (nota didattica).** Se $V_1$ entra nell’addestramento, il modello ha già visto una parte del test: l’errore misurato su $V_1$ è falsamente basso e **tutta la stima** risulta ottimistica. Se $T_3$ entra nel test, il test contiene dati già visti e l’errore è ottimistico su quella porzione, ma l’addestramento resta pulito.

**B2) Dati futuri nei dati passati**: informazioni che nella realtà sarebbero disponibili solo **dopo** l’istante della predizione entrano nei dati usati per addestrare (§3.5).

![](imgs/Pasted%20image%2020261123022050.png)

**B3) Uso dei dati fuori dall’ambito dell’algoritmo**. Esempio: un classificatore per la diagnosi addestrato su un dataset di **adulti** e usato anche per i **bambini**.

#### **3.4. Esempio 3: il «déjà vu»**


Nel dataset di addestramento e in quello di test compaiono cani e gatti **apparentemente diversi**. Dov’è il problema? Il **«déjà vu»**: il **riuso degli stessi campioni**, o di **parti ritagliate** di essi, oppure il riuso degli **stessi utenti**, produce una **generalizzazione scarsa**. **L’indipendenza statistica è rilevante!**

![](imgs/Pasted%20image%2020261123022216.png)

> 💡 **Un esempio concreto (nota didattica).** In un sistema di riconoscimento vocale, se le registrazioni della stessa persona finiscono sia in addestramento sia in test, il modello può riconoscere **la voce** invece delle **parole**. La regola è dividere per **utente** (o per paziente, per macchina, per sessione), non per singolo campione.

> 📌 **In breve: due fonti principali di data leakage**.
> - **A. Attributi delle feature**: le variabili **dicono troppo**.
> - **B. Dataset di addestramento**: porzioni di dati usate **nella fase sbagliata**.

#### **3.5. Serie temporali: un caso speciale**


![](imgs/Pasted%20image%2020261123022409.png)

| | **Previsione di serie temporali** | **Classificazione di serie temporali** |
|---|---|---|
| Definizione | **stimare valori futuri** in base ai dati passati | **assegnare etichette** in base ai dati passati |
| Industria | predire **guasti delle macchine** (manutenzione predittiva) | rilevare **anomalie** nei dati dei sensori |
| Supply chain | prevedere la **domanda** di prodotto | individuare **pattern di domanda** per categorie di prodotto |
| Ambiente | **previsioni meteo**, previsione della temperatura | classificare la **qualità dell’aria** dai sensori |
| Finanza | previsione dei **prezzi delle azioni**, tendenze economiche | rilevamento di **frodi** nelle sequenze di transazioni |
| Sanità | previsione dei **parametri vitali** del paziente | classificazione dell’ECG (*Electrocardiogram*, elettrocardiogramma) per le cardiopatie |

![](imgs/Pasted%20image%2020261123024217.png)

![](imgs/Pasted%20image%2020261123024242.png)

> ⚠️ **Il problema**. In entrambi i casi il modello funziona solo se i dati passati sono **coerenti**, cioè **non mescolati**, sia in **addestramento** (input del metodo di apprendimento) sia in **inferenza** (input del modello).

**Controllare il leakage nelle serie temporali**. Dividere una serie temporale in addestramento e test è un problema **soggetto a errori**: bisogna fare **molta attenzione**. In questi schemi, con certe suddivisioni la **continuità temporale** è garantita solo «a breve termine»: nella prima suddivisione i dati di **validazione** sono **nel passato** rispetto all’addestramento, nella seconda l’addestramento ha una **discontinuità temporale**. In un altro schema la continuità temporale è **pienamente garantita**.

![](imgs/Pasted%20image%2020261123024330.png)

> 📌 **Validazione «in avanti» (nota didattica).** Lo schema sicuro addestra sempre sul passato e verifica sul futuro immediato, allargando progressivamente la finestra:
>
> | Iterazione | Addestramento | Test |
> |---|---|---|
> | 1 | $[t_1, t_k]$ | $[t_{k+1}, t_{2k}]$ |
> | 2 | $[t_1, t_{2k}]$ | $[t_{2k+1}, t_{3k}]$ |
> | 3 | $[t_1, t_{3k}]$ | $[t_{3k+1}, t_{4k}]$ |
>
> Una suddivisione **casuale** dei campioni, normale per dati indipendenti, qui mette **il futuro nell’addestramento** (leakage di tipo B2).

#### **3.6. Il problema del ritaglio (cropping)**


Il leakage si osserva anche in dataset **complessi legati al tempo**:

- osservazioni analogiche memorizzate come **audio e immagini** in file separati, con **dimensione e marca temporale** definite;
- il **campionamento** in un problema grafico, per esempio **ritagliare un’immagine**, è un compito complesso.

Esempi:

- **audio**: il frammento selezionato per addestrare la parola «**YES**» è **troppo lungo** e contiene anche «**YOU**» (dalla frase «yes, you are»);
- **immagini**: la **cellula** selezionata è **ritagliata male**, con **parti mancanti** o **altre cellule** nel riquadro.

![](imgs/Pasted%20image%2020261123024451.png)

> 📌 **Il problema del ritaglio**. È una forma di data leakage **molto diffusa**, che colpisce molti tipi di dataset e applicazioni. Si verifica quando un modello **impara pattern da dati ritagliati** o **distorti dal contesto**: prestazioni **ingannevolmente alte** e **generalizzazione scarsa**.

**Esempi in domini diversi**:

| Dominio | Che cosa impara il modello invece del contenuto |
|---|---|
| **immagini** | i **bordi del ritaglio** o gli **artefatti** della pre-elaborazione, invece delle feature dell’oggetto |
| **audio** | il **rumore di fondo** dei campioni ritagliati, invece delle parole pronunciate |
| **dati strutturati** | in diagnosi medica, se l’addestramento contiene **solo i casi estremi**, il modello **fallisce sui casi intermedi** |
| **testo** | un modello di sentiment addestrato su post dei social si affida a **frasi troncate** invece del contesto completo |

![](imgs/Pasted%20image%2020261123024547.png)

![](imgs/Pasted%20image%2020261123024727.png)

**Evitare con cura le feature indesiderate**: il ritaglio può includere la scala in centimetri o millimetri come feature spuria.

> 💡 **Il righello nelle immagini dermatologiche (nota didattica).** In alcune fotografie di lesioni sospette compare un righello graduato. Un classificatore può apprendere l’associazione spuria «righello presente = lesione maligna» invece delle caratteristiche della lesione. La scala in centimetri e millimetri diventa così una feature indesiderata, da escludere con un ritaglio corretto.

![](imgs/Pasted%20image%2020261123024604.png)

![](imgs/Pasted%20image%2020261123024747.png)

Ci sono dei segnali d'avvertimento che possono indicare un plausibile data leakage:

1. Too good to be true 
2. One feature explains almost everything 
3. Features not available at prediction time 
4. Performance drops outside the experiment 
5. Shortcuts in the data

#### **3.7. Controlli pratici per scoprire il leakage**

![](imgs/Pasted%20image%2020261123025001.png)

Una buona verifica usa controlli indipendenti. Prestazioni insolitamente alte richiedono un confronto con baseline semplici e risultati plausibili per il problema; una feature dominante va esaminata per capire se è disponibile al momento della previsione. Per ogni colonna occorre quindi fissare il tempo $t_0$ della decisione e scartare valori che diventano noti dopo $t_0$, come una diagnosi finale o il motivo di una cancellazione.

![](imgs/Pasted%20image%2020261123111428.png)

Altri controlli cercano duplicati esatti e quasi duplicati fra training e test, sovrapposizioni di pazienti, utenti, sessioni, dispositivi o registrazioni e trasformazioni adattate sull’intero dataset. Normalizzazione, imputazione, selezione delle feature e augmentation devono essere apprese solo all’interno della partizione di training, preferibilmente in una pipeline riproducibile.

![](imgs/Pasted%20image%2020261123111539.png)

![](imgs/Pasted%20image%2020261123111608.png)

![](imgs/Pasted%20image%2020261123111645.png)

| Verifica | Procedura | Segnale da approfondire |
|---|---|---|
| Baseline e risultato plausibile | Confrontare il test con un predittore banale e con valori noti per il compito. | Risultato quasi perfetto con modello semplice e pochi dati. |
| Feature dominante | Permutation importance, modello per singola feature e ablation. | Una colonna da sola raggiunge quasi tutta la prestazione; verificare significato e disponibilità temporale. |
| Duplicati e gruppi | Hash esatti o percettivi, vicini nello spazio di embedding e identificativi di gruppo. | Stesso paziente, immagine, utente o sessione distribuito fra training e test. |
| Audit della pipeline | Controllare dove avviene ogni `fit` di preprocessing e feature selection. | Una trasformazione è stimata prima della suddivisione o usa il test. |
| Split alternativi | Confrontare suddivisione casuale, cronologica e per sito/dispositivo. | Calo molto ampio; può indicare leakage, scorciatoie o cambiamento di distribuzione. |
| Etichette permutate | Ripetere l’intera pipeline e la cross-validation con $y$ rimescolato. | Punteggi nulli ben sopra il livello casuale; possibile bug o flusso d’informazione dal target. |
| Validazione avversaria | Addestrare un classificatore temporaneo a distinguere training da test. | AUC molto sopra 0,5: le partizioni sono distinguibili, segnale di shift da investigare. |

Il test delle etichette permutate è un **controllo negativo**, non una prova definitiva: può non rilevare una feature che contenga già direttamente il target. Nel controllo avversario, invece, la nuova etichetta indica l’origine (training o test), non la classe applicativa. AUC circa 0,5 significa che il classificatore non distingue le distribuzioni; un valore alto indica differenze da spiegare e non dimostra da solo la presenza di leakage.

![](imgs/Pasted%20image%2020261123112426.png)

Per verificare righe condivise si può usare un’intersezione fra tabelle, ad esempio `pd.merge(train, test, how="inner")`; un risultato non vuoto richiede un controllo. Nei dati reali, ID, provenienza e hash sono preferibili ai soli valori di colonna, perché osservazioni distinte possono coincidere sui campi registrati.

![](imgs/Pasted%20image%2020261123112435.png)

![](imgs/Pasted%20image%2020261123112505.png)

![](imgs/Pasted%20image%2020261123112611.png)

![](imgs/Pasted%20image%2020261123112622.png)

---



L’audit è particolarmente importante quando gli agenti contribuiscono alla creazione dei dati, delle feature o delle etichette. Un’inferenza errata dello schema, un’allucinazione o un contesto incompleto possono introdurre errori plausibili e difficili da notare. Conservare prompt, codice e log, controllare un campione manualmente e chiedersi per ogni feature quando diventa disponibile permette di ricostruire la provenienza e ripetere i controlli.

![](imgs/Pasted%20image%2020261123112657.png)

![](imgs/Pasted%20image%2020261123112707.png)

Due verifiche assistite rendono concreti questi controlli. Nel primo caso, un hash percettivo individua una foto di cane quasi duplicata tra le cartelle di training e test, con distanza di Hamming pari a 2; va rimossa da una partizione oppure si deve dividere per immagine originale, così che i ritagli della stessa foto restino insieme. Nel secondo, un classificatore che usa soltanto dimensioni e colore medio delle immagini di cellule raggiunge il 97% quando le immagini sane provengono dall’ospedale A e quelle leucemiche dall’ospedale B. Il modello riconosce il centro di acquisizione: è un confondimento di sorgente e una scorciatoia, non necessariamente leakage in senso stretto. I numeri sono un output simulato e vanno ricalcolati sui dati effettivi.

---

### **4. Distinzioni da non confondere**

| Coppia                                                   | Differenza chiave                                                                                               |
| -------------------------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| **`x`** / **`y`** nel laboratorio                        | matrice delle feature (150 × 4) / vettore delle classi (150)                                                    |
| **Slicing per colonna** / **per riga**                   | una feature `xNumpy[:, j]` / un campione `xNumpy[i, :]`                                                         |
| **Indice 1 in Python** / **in MATLAB**                   | secondo elemento / primo elemento                                                                               |
| **DoF** / **#Par**                                       | parametri indipendenti che definiscono la configurazione / numero di pesi del modello: legati, non identici     |
| **DoF < 0** / **DoF > 0**                                | più dati che parametri: modello determinato / più parametri che dati: flessibilità eccessiva e overfitting      |
| **Leakage A** / **leakage B**                            | le feature dicono troppo o manca una feature rilevante / dati usati nella fase sbagliata o informazioni esterne |
| **Addestramento nel test** / **test nell’addestramento** | leggermente grave / molto grave                                                                                 |
| **Leakage B1** / **B2** / **B3**                         | dal test all’addestramento / dal futuro al passato / fuori dall’ambito (adulti → bambini)                       |
| **Divisione per campione** / **per utente**              | può mettere la stessa persona in addestramento e test / garantisce l’indipendenza statistica                    |
| **Previsione** / **classificazione di serie temporali**  | valori futuri / etichette sulla sequenza                                                                        |
| **Suddivisione casuale** / **in avanti**                 | adatta a dati indipendenti / obbligatoria per le serie temporali                                                |

---

### **5. Sintesi della lezione**


> ✅ **Punti principali**.
> - Usare un dataset **senza attività preliminari** è **inutile e pericoloso**, e fa perdere tempo: caricare, controllare con `info()` e `head()`, separare feature e classi, verificare con lo slicing.
> - **Numero di gradi di libertà / parametri**: per un modello lineare con $n$ input si stimano $n+1$ parametri; in generale $\text{DoF} \approx \#\text{Par} - \#\text{Data}$ è solo una regola intuitiva. Capacità, complessità informativa e deployment contano insieme: una rete 4-5-1 ha 31 parametri, VGGNet 138 milioni e DINOv3 ViT-7B circa 7 miliardi.
> - **Data leakage**: feature non disponibili al momento della previsione, dati usati nella fase sbagliata, futuro nel passato, campioni o gruppi condivisi, scorciatoie e ritagli impropri possono gonfiare le prestazioni stimate.
> - **Diagnostica**: controllare baseline, feature dominanti, duplicati, gruppi, pipeline, split cronologici e per sito; usare etichette permutate come controllo negativo e adversarial validation per individuare differenze fra training e test.
> - Gli agenti possono aiutare nell’audit, ma codice, fonti, campioni e assunzioni restano da verificare.
