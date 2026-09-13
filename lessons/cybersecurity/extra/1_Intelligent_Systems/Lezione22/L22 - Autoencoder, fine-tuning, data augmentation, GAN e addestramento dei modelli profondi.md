## **Lezione 22: Autoencoder, fine-tuning, data augmentation, GAN e addestramento dei modelli profondi**

La ventiduesima lezione del corso **IS4** affronta il problema pratico dei modelli profondi: **come si addestrano** reti con decine o centinaia di milioni di parametri quando le etichette sono poche. Le risposte sono complementari:

- gli **autoencoder**, che imparano feature **senza etichette** ricostruendo l’input;
- il **transfer learning** e il **fine-tuning**, che riusano reti pre-addestrate, con il rischio dell’**oblio catastrofico**;
- la **data augmentation**, che allarga il dataset;
- le CNN come **estrattori automatici di feature**;
- l’**addestramento greedy strato per strato**, la soluzione storica ai problemi di addestramento delle reti profonde.

La seconda parte è dedicata ai **modelli generativi**: GAN, **modelli di diffusione** e il rischio del **Model Autophagy Disorder**. Chiudono la lezione i **limiti attuali** (scaling laws) e l’uso di **LLM e agenti** nella progettazione del ML.

Fonte: [PDF della Lezione 22](Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf), 91 pagine, senza supplementi. Molte slide sono adattate da materiali di Tony Martinez; la sezione sulle GAN è di Ruggero Donida Labati e Fabio Scotti. Le note didattiche aggiunte e i frammenti di codice ricostruiti sono segnalati. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *Autoencoders, Fine Tuning and Transfer Knowledge, Automatic Feature Extraction, Training of Deep Learning Models*. L’indice (p. 2) elenca:

1. **autoencoder**;
2. **addestramento e progetto** dei modelli di deep learning: **fine-tuning**, **transfer knowledge**, **data augmentation**, **oblio catastrofico**;
3. **estrazione automatica delle feature**;
4. **problemi dell’addestramento** dei modelli profondi e una soluzione: il **greedy layer-wise training**;
5. **Generative Adversarial Networks**: generazione di immagini sintetiche realistiche e *Model Autophagy Disorder* dei modelli generativi;
6. **LLM e progettazione**.

---

### **1. Autoencoder: deep learning non supervisionato**

_(slide pp. 3–17)_

#### **1.1. Perché l’apprendimento non supervisionato**

_(slide pp. 3–6)_

> 📌 **Apprendimento non supervisionato** (p. 4). Si addestra **senza etichette**. È utile perché:
> - può usare **dati non etichettati**, disponibili in quantità praticamente **illimitata**;
> - il modello si può poi **rifinire** con le tecniche supervisionate standard, per esempio la backpropagation come in una normale rete feed-forward;
> - serve quando le **etichette sono poche**: un dataset **parzialmente etichettato**, cioè **semi-supervisionato**.

**L’idea di fondo** (p. 5). Senza etichette, i metodi cercano di **modellare la distribuzione dei dati di input**, per esempio i cluster. Si ottimizza:

- l’**errore di ricostruzione**, oppure
- la **log-verosimiglianza** dei dati.

| Modelli non supervisionati (p. 5) | Dove li abbiamo visti |
|---|---|
| **PCA**, **k-means** (di base) | Lezione 18, §2; Lezione 19 |
| **Independent Component Analysis** | Lezione 18, §3.3 |
| **denoising autoencoder**, **sparse autoencoder** | questa lezione (evidenziati nella slide come argomento di oggi) |
| **Restricted Boltzmann Machine**, **sparse coding**, … | citati soltanto |

**L’obiettivo** (p. 6) è **addestrare efficacemente gli strati nascosti** di una rete profonda. È un compito difficile (§6), e la slide annuncia la soluzione: **autoencoder** e **greedy layer-wise training** (§7).

<!-- SOURCE_VISUAL id="IS22-V01" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="6" type="diagramma" description="Rete profonda con strati nascosti da addestrare: il compito difficile risolto con gli autoencoder" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Che cos’è un autoencoder**

_(slide pp. 7–13)_

> 📌 **Autoencoder (AE)** (p. 7). Una rete che:
> - cerca di creare **feature generiche** dai dati (**codifica**, *encoding*);
> - impara la **funzione identità** (uscita = ingresso) imparando le **sotto-feature importanti**;
> - si addestra perché $X' = X$, con la **backpropagation** come una normale rete feed-forward.
>
> Il passaggio stretto al centro è il **collo di bottiglia** (*bottleneck*): costringe la rete a **comprimere** l’informazione. Le nuove feature si possono usare **al posto** di quelle originali nel nuovo training set, oppure **concatenate** a esse.

> 💡 **L’autoencoder in formule (nota didattica).** Con un encoder e un decoder a uno strato:
> $$\mathbf{h} = \sigma(W\mathbf{x} + \mathbf{b}), \qquad \hat{\mathbf{x}} = \sigma'(W'\mathbf{h} + \mathbf{b}')$$
> e la funzione di costo è l’errore di ricostruzione sugli $N$ esempi:
> $$\mathcal{L} = \frac{1}{N} \sum_{i=1}^{N} \lVert \mathbf{x}_i - \hat{\mathbf{x}}_i \rVert^2$$
> Le **etichette non servono**: il «target» di ogni esempio è l’esempio stesso. Se $\mathbf{h}$ ha meno componenti di $\mathbf{x}$, la rete non può copiare l’input e deve trovare una rappresentazione compatta. Due varianti citate a p. 5:
> - il **denoising autoencoder** riceve un input corrotto $\tilde{\mathbf{x}} = \mathbf{x} + \boldsymbol{\varepsilon}$ ma deve ricostruire $\mathbf{x}$ pulito;
> - lo **sparse autoencoder** aggiunge al costo una penalità come $\lambda \sum_j |h_j|$, che tiene attive poche unità alla volta.

> 💡 **Autoencoder e PCA (nota didattica).** Un autoencoder **lineare** (senza funzioni di attivazione) addestrato con l’errore quadratico impara lo **stesso sottospazio** delle prime $k$ componenti principali. Le non linearità permettono all’autoencoder di catturare strutture **curve** che la PCA non vede. Anche la slide p. 14 lo ricorda: la PCA è un metodo non supervisionato **come** gli autoencoder.

<!-- SOURCE_VISUAL id="IS22-V02" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="7" type="diagramma" description="Autoencoder con ingresso X, collo di bottiglia e uscita X’" reason="Architettura presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Autoencoder impilati** (*stacked auto-encoders*, pp. 8–12). Si impilano **molti autoencoder** (anche sparsi) in successione e li si addestra con il **greedy layer-wise training**:

1. si addestra il primo autoencoder: **uscita = ingresso**, la stessa immagine; ogni neurone d’uscita ricostruisce, per esempio, il **livello di grigio di un pixel** (p. 8);
2. si **elimina lo strato d’uscita del decoder** e si **tengono i parametri** dell’encoder (p. 9); l’uscita nascosta diventa l’ingresso del secondo autoencoder, che si addestra allo stesso modo;
3. la **regola d’oro** (p. 10): **ridurre il numero di neuroni** procedendo verso le uscite;
4. si fa un addestramento **supervisionato sull’ultimo strato** (p. 11). È possibile perché si hanno gli **ingressi $X$ e i pesi degli AE**, quindi si possono calcolare gli ingressi dello strato finale ($h_1, h_2, \dots$), e si hanno le **etichette** nel vettore $Y$;
5. infine si fa un addestramento **supervisionato di tutta la rete** per **rifinire tutti i pesi** (*fine-tuning*, p. 12).

<!-- SOURCE_VISUAL id="IS22-V03" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="8" type="diagramma" description="Primo autoencoder impilato: si addestra con uscita uguale all’ingresso" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V04" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="10" type="diagramma" description="Autoencoder impilati con numero di neuroni decrescente verso le uscite" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V05" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="12" type="diagramma" description="Fine-tuning supervisionato dell’intera rete dopo il pre-addestramento" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Lo stato dell’arte** (p. 13).
> - Il greedy layer-wise training con autoencoder è stato **storicamente molto importante** per **inizializzare** le reti profonde, prima che le ricette di addestramento moderne diventassero robuste.
> - Oggi è **meno comune** per CNN standard e transformer, perché si usano:
>   - **inizializzazioni** e **normalizzazioni** migliori;
>   - **connessioni residue** (Lezione 21, §1.4);
>   - **grandi dataset**;
>   - **modelli pre-addestrati** e **transfer learning**.
> - Nell’addestramento moderno delle CNN i primi strati **non** si addestrano da zero con autoencoder: si imparano **end-to-end** con architetture robuste, oppure si **riusano** da modelli pre-addestrati, **congelati** o **leggermente rifiniti**.
>
> All’esame conviene quindi presentare gli autoencoder impilati come **soluzione storica**, e gli autoencoder in generale come strumento ancora vivo per compressione, denoising e rilevamento di anomalie.

#### **1.3. Applicazioni degli autoencoder**

_(slide pp. 14–17)_

**Riduzione della dimensionalità** (p. 14). Esempio sul dataset **MNIST**: immagini $28 \times 28$ in bianco e nero di singole cifre, ridotte dalle **784 dimensioni** originali a **2**. Nello spazio 2D le cifre formano gruppi distinti. (Nel testo della slide «28×28» compare duplicato.)

**Usare solo il decoder** (p. 15). Dopo l’addestramento si può usare **solo il decoder**, dandogli in ingresso vettori 2D scelti a mano: per esempio l’ingresso $[20, 20]$ produce un’immagine di cifra. La slide invita a notare l’interessante **continuità dello spazio latente**: punti vicini producono immagini simili.

> 💡 **Codifica come compressione (nota didattica).** Da 784 valori a 2 il fattore di compressione è $784 / 2 = 392$. La ricostruzione non sarà perfetta, ma conserva ciò che distingue le cifre.
>
> ⚠️ In un autoencoder **semplice** la continuità dello spazio latente **non è garantita**: tra due gruppi possono esserci zone vuote, e il decoder produce immagini senza senso. Gli **autoencoder variazionali** (VAE) aggiungono un termine che regolarizza lo spazio latente proprio per renderlo continuo e campionabile, e sono il ponte verso i modelli generativi del §8.

<!-- SOURCE_VISUAL id="IS22-V06" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="14" type="grafico" description="Cifre MNIST proiettate da 784 a 2 dimensioni con un autoencoder" reason="Nuvola di punti visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V07" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="15" type="immagini" description="Decoder usato da solo: il vettore latente [20, 20] genera un’immagine di cifra" reason="Immagini generate visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Riduzione del rumore** (p. 16): un autoencoder di denoising ripara immagini con **pixel mancanti**, **rumore** e **barre**.

**Colorazione automatica** (p. 17): un autoencoder riceve l’immagine in scala di grigi e ricostruisce i colori.

<!-- SOURCE_VISUAL id="IS22-V08" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="16" type="immagini" description="Denoising con autoencoder: pixel mancanti, rumore e barre" reason="Esempi visivi presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V09" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="17" type="immagini" description="Colorazione automatica con autoencoder" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Transfer learning e fine-tuning**

_(slide pp. 18–26)_

**Sfruttare addestramento e conoscenza precedenti** (p. 18). I metodi di ML **tradizionali** devono **ricominciare da capo** l’apprendimento ogni volta che cambiano il **dominio applicativo** o il dataset (p. 19). Il **transfer learning** riusa ciò che un modello ha già imparato, per **evitare l’overfitting** e **migliorare l’apprendimento**. La p. 20 mostra l’idea come percorso: un primo passo su scene **senza sfondo** porta a un apprendimento migliore, che nel secondo passo permette di affrontare **scene più complesse**.

<!-- SOURCE_VISUAL id="IS22-V10" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="19" type="diagramma" description="ML tradizionale che riparte da zero contro transfer learning" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**I modelli pre-addestrati sono disponibili online** (p. 21):

- le **CNN grandi** richiedono **settimane** di addestramento su **più GPU** con dataset come ImageNet;
- molti ricercatori **pubblicano** la CNN finale perché altri la usino per il **fine-tuning**;
- fonti principali: **Hugging Face Hub**, **Keras Applications / KerasHub**, i modelli di **PyTorch TorchVision**, **TensorFlow Hub**, **ONNX Model Zoo**.

> 📌 **Fine-tuning a partire da una CNN pubblica** (p. 22). Una CNN addestrata per il **problema 1** (classificare volto, corpo, cane, cavallo, …) si riusa per il **problema 2** (volto sì/no):
> - gli strati **convolutivi** vengono **congelati** (*FREEZE*): i loro pesi non cambiano;
> - la parte **completamente connessa** viene **rifinita** (*TUNE*) sul nuovo problema.

<!-- SOURCE_VISUAL id="IS22-V11" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="22" type="diagramma" description="Fine-tuning: strati convolutivi congelati e rete completamente connessa rifinita per il nuovo problema" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Il fine-tuning è molto efficace** (p. 23). È oggi uno dei **flussi di lavoro pratici dominanti** nel deep learning, e la stessa idea vale non solo per le CNN ma anche per **vision transformer**, **modelli multimodali**, **modelli vocali** e **LLM**.

> 📌 **Tre opzioni di fine-tuning** (pp. 24–26, sull’esempio di AlexNet).
> 1. **Sostituire e riaddestrare il classificatore** in cima alla rete convolutiva, sul nuovo dataset.
> 2. **Tenere fissi alcuni dei primi strati**, per timore di overfitting, e rifinire solo una **porzione di livello più alto** della rete. La motivazione: le feature dei **primi strati** sono **più generiche** (rilevatori di bordi, di macchie di colore) e utili a molti compiti, mentre gli **strati successivi** diventano **sempre più specifici** per le classi del dataset originale.
> 3. **Rifinire tutti i pesi** della rete pre-addestrata, continuando la backpropagation.

<!-- SOURCE_VISUAL id="IS22-V12" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="26" type="diagramma" description="Le tre opzioni di fine-tuning su AlexNet" reason="Architettura presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Quale opzione scegliere (nota didattica, regola pratica diffusa).** Dipende da quanti dati si hanno e da quanto il nuovo problema somiglia a quello originale:
>
> | | Nuovo problema **simile** | Nuovo problema **diverso** |
> |---|---|---|
> | **Pochi dati** | opzione 1: solo il classificatore, rischio di overfitting minimo | classificatore sulle feature di strati **intermedi**, più generiche |
> | **Molti dati** | opzione 2 o 3: rifinire gli strati alti o tutto | opzione 3: rifinire tutto (o addestrare da zero) |
>
> Nel fine-tuning si usa di solito un **learning rate piccolo**, perché i pesi di partenza sono già buoni e un passo grande li distruggerebbe (è anche un rimedio all’oblio catastrofico del §3).

> 💡 **Il fine-tuning in Keras (ricostruzione didattica, non trascritta dalle slide).** Keras non include AlexNet; l’esempio usa VGG16 per il problema «volto sì/no».
> ```python
> import keras
>
> base = keras.applications.VGG16(weights="imagenet", include_top=False,
>                                 input_shape=(224, 224, 3))
> base.trainable = False                          # opzione 1: strati convolutivi congelati
>
> x = keras.layers.GlobalAveragePooling2D()(base.output)
> out = keras.layers.Dense(1, activation="sigmoid")(x)   # volto sì/no
> model = keras.Model(base.input, out)
> model.compile(optimizer=keras.optimizers.Adam(1e-3),
>               loss="binary_crossentropy", metrics=["accuracy"])
> # model.fit(...)
>
> # opzione 2: si scongelano solo gli ultimi strati, con learning rate piccolo
> base.trainable = True
> for layer in base.layers[:-4]:
>     layer.trainable = False
> model.compile(optimizer=keras.optimizers.Adam(1e-5),
>               loss="binary_crossentropy", metrics=["accuracy"])
> # model.fit(...)
> ```
> Dopo aver cambiato `trainable` bisogna **ricompilare** il modello, altrimenti la modifica non ha effetto.

---

### **3. L’oblio catastrofico**

_(slide pp. 27–34)_

> 📌 **Oblio catastrofico** (*catastrophic forgetting*, detto anche *catastrophic interference*, pp. 28–29). Il fenomeno per cui una rete neurale **dimentica le informazioni già apprese** quando ne impara di nuove, con una **perdita significativa di conoscenza**. Si osserva quando una rete viene addestrata **in sequenza** su compiti diversi: mentre impara i nuovi compiti, le sue prestazioni su quelli precedenti **peggiorano in modo marcato**.

**Dove è rilevante oggi** (p. 30): **apprendimento continuo**, **personalizzazione**, **adattamento di dominio**, **fine-tuning di grandi modelli pre-addestrati**, anche LLM e LMM.

La p. 31 lo mostra con l’**accuratezza di validazione** sul vecchio compito che crolla durante l’addestramento sul nuovo. La p. 32 lo mostra con le **frontiere di decisione**: la nuova frontiera separa bene i **nuovi dati**, ma **dimentica** come separare i **vecchi**.

<!-- SOURCE_VISUAL id="IS22-V13" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="31" type="grafico" description="Oblio catastrofico misurato con l’accuratezza di validazione sui compiti precedenti" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V14" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="32" type="grafico" description="Frontiera di decisione vecchia e nuova: la nuova dimentica come separare i vecchi dati" reason="Frontiere visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché succede (nota didattica).** La backpropagation sposta **tutti** i pesi nella direzione che riduce l’errore **sui dati che vede in quel momento**. Se i dati vecchi non ci sono più, niente impedisce ai pesi di allontanarsi dai valori che servivano per il vecchio compito. È l’opposto di quello che accade nel cervello, dove la conoscenza precedente viene in larga parte conservata.

**Come mitigarlo** (pp. 33–34). È ancora un **problema aperto** della ricerca, **senza una soluzione completa**. Si può provare con:

| Tecnica | Idea (slide) | Costo o limite |
|---|---|---|
| **Rehearsal** | strategia semplice e intuitiva: si **mescolano i vecchi dati ai nuovi** durante l’addestramento, così il modello ricorda i vecchi compiti | richiede di **conservare i vecchi dati**, costoso in risorse |
| **Elastic Weight Consolidation** (EWC) | aggiunge un semplice **termine di regolarizzazione** alla funzione di costo che **preserva i pesi importanti** per i vecchi compiti | popolare perché semplice |
| **Knowledge distillation** | il nuovo modello (**studente**) si addestra a **riprodurre le uscite** del vecchio (**insegnante**), conservandone la conoscenza | serve il vecchio modello durante l’addestramento |
| **Architetture dinamiche** | metodi come **PathNet** o le **Progressive Neural Networks** **modificano l’architettura** per i nuovi compiti | più complessi, ma efficaci |

> 💡 **EWC e distillazione in formula (nota didattica).** EWC, dopo aver imparato il compito A con pesi $\theta^*_A$, addestra sul compito B con
> $$\mathcal{L}(\theta) = \mathcal{L}_B(\theta) + \sum_i \frac{\lambda}{2}\, F_i \left(\theta_i - \theta^*_{A,i}\right)^2$$
> dove $F_i$ (dalla matrice di informazione di Fisher) misura quanto il peso $i$ era importante per A. I pesi importanti sono «legati con un elastico» al loro vecchio valore, quelli poco importanti restano liberi.
>
> La distillazione usa una **softmax con temperatura** $T$, che rende più «morbide» le probabilità dell’insegnante:
> $$p_i^{(T)} = \frac{e^{z_i / T}}{\sum_j e^{z_j / T}}$$
> Con $T > 1$ lo studente impara non solo la classe giusta, ma anche **quali classi l’insegnante considera simili**.

---

### **4. Data augmentation**

_(slide pp. 35–43)_

**I modelli profondi hanno bisogno di grandi dataset** (p. 36). Il grafico confronta ML classico e deep learning: con pochi dati il ML classico può essere migliore; al crescere dei dati il deep learning continua a migliorare, mentre il classico si ferma.

<!-- SOURCE_VISUAL id="IS22-V15" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="36" type="grafico" description="Prestazioni al crescere dei dati: ML classico contro deep learning" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Data augmentation** (p. 37). È **l’alleato per superare l’overfitting**: bisogna ricordare che si possono avere **più di 100 milioni di parametri liberi** da fissare. **Ma** la data augmentation **non risolve da sola l’applicazione**: servono comunque **buoni dati reali**.

| Tipo (slide) | Esempi |
|---|---|
| **#1 rotazioni, traslazioni, scale** (p. 38) | la stessa immagine di una pallina da tennis presentata in **posizioni**, **scale** e **rotazioni** diverse, così la CNN impara a rilevarla in ogni caso |
| **#2 rumore** (p. 39) | rumore di tipi diversi (**gaussiano**, **additivo**, **moltiplicativo**), condizioni applicative, **rumore del sensore** (sui pixel), **risoluzioni**, **ambienti** (interni, esterni, sfondi) |
| **#3 condizioni diverse simulate** (p. 40), anche per grandi dataset | **perturbazione dei landmark** per l’allineamento del volto, **ribaltamento**, **ritaglio**, **dominanti di colore**, **sfocatura**; lo si fa **usando CNN pubbliche**; bisogna **riprodurre le condizioni reali** durante l’addestramento |
| **stato dell’arte** (p. 41) | **CutMix**, **MixUp**, **Cutout**, **RandAugment**, **AutoAugment**, generazione di **dati sintetici**, **domain randomization**, augmentation **generativa** |

I toolbox avanzati hanno funzioni apposite (p. 38). In MATLAB, per esempio, `imageDataAugmenter` **ribalta a caso** le immagini di addestramento lungo l’**asse verticale** e le **trasla** a caso.

> 💡 **Il codice MATLAB (ricostruzione didattica, non trascritta dalle slide).**
> ```matlab
> augmenter = imageDataAugmenter( ...
>     'RandXReflection', true, ...          % ribaltamento sinistra-destra
>     'RandXTranslation', [-3 3], ...       % traslazione orizzontale in pixel
>     'RandYTranslation', [-3 3]);          % traslazione verticale in pixel
> augimds = augmentedImageDatastore([28 28], XTrain, YTrain, ...
>     'DataAugmentation', augmenter);
> ```
> Il ribaltamento «lungo l’asse verticale» è lo scambio sinistra-destra. Le trasformazioni vengono applicate **a caso a ogni epoca**, senza salvare copie su disco: la rete non vede mai due volte esattamente la stessa immagine.

<!-- SOURCE_VISUAL id="IS22-V16" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="38" type="immagini" description="Data augmentation della pallina da tennis con posizioni, scale e rotazioni" reason="Esempi visivi presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V17" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="39" type="immagini" description="Data augmentation con rumore di tipi diversi" reason="Esempi visivi presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V18" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="40" type="immagini" description="Condizioni simulate: landmark perturbati, ribaltamento, ritaglio, dominanti di colore, sfocatura" reason="Esempi visivi presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V19" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="41" type="immagini" description="Tecniche moderne di augmentation: CutMix, MixUp, Cutout" reason="Esempi visivi presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **MixUp e CutMix (nota didattica).** MixUp crea un esempio nuovo **mescolando due immagini e le loro etichette**:
> $$\tilde{\mathbf{x}} = \lambda\, \mathbf{x}_i + (1 - \lambda)\, \mathbf{x}_j, \qquad \tilde{\mathbf{y}} = \lambda\, \mathbf{y}_i + (1 - \lambda)\, \mathbf{y}_j, \qquad \lambda \in [0, 1]$$
> con etichette one-hot. Con $\lambda = 0{,}7$, un’immagine di gatto e una di cane danno un’immagine sovrapposta con etichetta $[0{,}7;\ 0{,}3]$. CutMix incolla invece un **rettangolo** di un’immagine dentro l’altra, e pesa le etichette con la **frazione di area** incollata. Cutout si limita a **cancellare** un rettangolo.

> ⚠️ **L’augmentation deve conservare l’etichetta** (p. 42). Una rotazione è utile per un rilevatore di palline da tennis, ma può essere **sbagliata** per le cifre **6 e 9**, per i **segnali stradali**, per le **immagini mediche** o per i **difetti che dipendono dall’orientazione**.

> ⚠️ **Augmentation e data leakage (nota didattica).** Le copie aumentate di un’immagine sono quasi duplicati. Se si aumenta il dataset **prima** di dividerlo, versioni della stessa immagine finiscono sia nel training sia nel test, ed è il «déjà vu» della Lezione 07, §3.4. La regola è: **prima si divide**, poi si aumenta **solo il training set**.

La p. 43 mostra il deep learning **aiutato da un’augmentation adeguata**: con i dati aumentati la curva del deep learning supera quella del ML classico già con meno dati reali.

<!-- SOURCE_VISUAL id="IS22-V20" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="43" type="grafico" description="Deep learning con data augmentation confrontato con il ML classico" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Estrazione automatica delle feature con i modelli profondi**

_(slide pp. 44–47)_

La slide di apertura (p. 44) presenta l’idea come **miglioramento dell’approccio PCA** tramite il deep learning: invece di una proiezione lineare, si usano le attivazioni di una rete profonda.

> 📌 **CNN come estrattori di feature** (pp. 45–46).
> 1. In una CNN pre-addestrata si **rimuove l’ultimo strato completamente connesso**, per esempio quello che produce i **1000 punteggi delle classi** di ImageNet.
> 2. Si tratta il resto della CNN come un **estrattore di feature fisso** per il nuovo dataset. In AlexNet si ottiene un **vettore a 4096 dimensioni** per ogni immagine: le attivazioni dello strato nascosto **immediatamente prima del classificatore**.
> 3. Estratti i codici a 4096 dimensioni per tutte le immagini, si addestra un **classificatore lineare** (per esempio una **SVM lineare** o un **classificatore softmax**) sul nuovo dataset: le **mie immagini** e le **mie classi**, al posto delle classi di AlexNet.
>
> ⚠️ Nota della slide: è importante che le feature siano **«ReLU-ate»**, cioè **azzerate sotto lo zero**, se lo erano anche durante l’addestramento della CNN (come accade di solito).

<!-- SOURCE_VISUAL id="IS22-V21" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="46" type="diagramma" description="AlexNet come estrattore di feature a 4096 dimensioni seguito dal mio classificatore" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché funziona con pochi dati (nota didattica).** Il classificatore lineare sopra le 4096 feature, con $C$ classi, ha $4096\,C + C$ parametri: per un problema a 2 classi sono $8194$, contro i circa 60 milioni di AlexNet. È l’opzione 1 del §2 portata all’estremo, e ricollega le Lezioni 08, §3.2 («estrazione delle feature neurale + classificatore o distanza classica») e 18, §2 (PCA): cambia solo **chi** costruisce lo spazio delle feature.

**Lo stato dell’arte** (p. 47). Oggi lo stesso flusso si fa spesso con **ResNet**, **EfficientNet**, **ConvNeXt**, **vision transformer**, **encoder di tipo CLIP** o modelli pre-addestrati **specifici del dominio** (lezione successiva).

---

### **6. Progettare e addestrare i modelli profondi**

_(slide pp. 48–52)_

**Affrontare la complessità di oltre 100 milioni di parametri** (p. 48).

**Il progetto** (p. 49, dalla lezione precedente). Mentre **tutti i pesi vengono addestrati**, la **struttura** (topologia) della CNN è di solito **progettata a mano per tentativi**:

- numero totale di **strati**;
- **tipi** e **dimensioni** dei kernel;
- dimensione dei campi di **sottocampionamento** (pooling);
- andando dagli ingressi verso le uscite, tipicamente si **riduce la dimensione dei kernel** e si **aumenta il numero di feature map**.

> ⚠️ **Kernel o feature map? (nota didattica).** La Lezione 21 (p. 65 di quella lezione) diceva di ridurre la **dimensione delle feature map**, non dei kernel. È la versione più aderente alla pratica: la risoluzione spaziale si riduce con pooling e stride, mentre i kernel sono spesso $3 \times 3$ in tutti gli strati (VGG16). Quando le reti usano kernel grandi, li usano semmai nei **primi** strati (AlexNet parte con $11 \times 11$).

> 📌 **Suggerimenti per l’addestramento** (p. 50).
> - Si addestra con la **backpropagation**.
> - Si usa la **condivisione dei pesi** in ogni feature map.
> - Si **inizializzano a caso** i pesi di tutta la rete, oppure si usano **modelli pre-addestrati**.
>
> **Weight sharing**: lo **stesso kernel convolutivo** si riusa in **posizioni spaziali diverse** dell’immagine. Questo **riduce fortemente** il numero di parametri addestrabili e rende la feature map **equivariante alle traslazioni**, aiutando così a ridurre l’overfitting.

Il testo della slide scrive «weight gg» dove si intende il *weight sharing*.

> 💡 **Equivarianza e invarianza (nota didattica).** Una funzione $f$ è **equivariante** a una traslazione $T$ se $f(T\mathbf{x}) = T f(\mathbf{x})$: spostando l’oggetto, la risposta nella feature map **si sposta allo stesso modo**. È **invariante** se $f(T\mathbf{x}) = f(\mathbf{x})$: la risposta **non cambia**. La convoluzione dà equivarianza, il pooling (Lezione 21, §2.3) aggiunge un po’ di invarianza.

**I problemi dell’addestramento delle reti profonde** (pp. 51–52):

1. i **primi strati** di un MLP **non si addestrano bene**:
   - **diffusione del gradiente**: l’errore si **attenua** propagandosi verso i primi strati, e l’addestramento diventa **molto lento**;
   - il problema è **aggravato** perché gli ultimi strati riescono di solito a imparare il compito «**abbastanza bene**» da soli: l’errore che arriva ai primi strati cala in fretta, e questi **non hanno mai l’occasione** di usare la loro capacità, restando **mappe di feature casuali**;
2. serve un modo perché i primi strati **lavorino davvero**;
3. **instabilità del gradiente**: **vanishing** o **exploding gradient**. Il gradiente è un **prodotto di molti termini** che, se non sono «bilanciati» nel modo giusto, è **instabile**: o i primi o gli ultimi strati restano bloccati mentre gli «opposti» imparano;
4. spesso **non ci sono abbastanza dati etichettati**, mentre ce ne sono molti **non etichettati**: si possono usare approcci **non supervisionati o semi-supervisionati** per sfruttarli?
5. le reti profonde tendono ad avere problemi di addestramento **più delicati** e più **minimi locali** delle reti poco profonde durante l’addestramento supervisionato.

> 💡 **Il prodotto instabile (nota didattica).** Con la sigmoide, la cui derivata vale al massimo $0{,}25$, dopo 10 strati il fattore è al più $0{,}25^{10} \approx 9{,}5 \times 10^{-7}$ (Lezione 20, §6.3). Se invece i pesi sono grandi e i fattori valgono, per esempio, $1{,}5$, dopo 10 strati si arriva a $1{,}5^{10} \approx 57{,}7$ e il gradiente **esplode**.

---

### **7. Greedy layer-wise training**

_(slide pp. 53–60)_

**Una soluzione molto efficiente** ai problemi del §6 (p. 53): usare l’apprendimento **non supervisionato come inizializzazione**, addestrando ogni strato **come un autoencoder** ed «esportandolo» nella rete (p. 54).

> 📌 **Greedy layer-wise training: riepilogo** (pp. 55–58).
> 1. Si **addestra il primo strato** con i dati **senza etichette** (non supervisionato).
> 2. Si **congelano** i parametri del primo strato e si addestra il **secondo**, usando l’**uscita del primo** come ingresso non supervisionato.
> 3. Si **ripete** per tutti gli strati desiderati.
> 4. Si usano le **uscite dell’ultimo strato** come ingressi di uno strato o modello **supervisionato**, e si addestrano gli ultimi strati supervisionati, **lasciando congelati** i pesi precedenti (nella slide, per esempio, un classificatore binario).
> 5. Si **scongelano tutti i pesi** e si **rifinisce l’intera rete** con un addestramento supervisionato, partendo dai pesi del pre-addestramento.

<!-- SOURCE_VISUAL id="IS22-V22" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="54" type="diagramma" description="Strato addestrato come autoencoder ed esportato nella rete profonda" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V23" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="59" type="diagramma" description="Rete profonda costruita con il greedy layer-wise training" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché funziona** (p. 60):

- evita molti dei problemi dell’addestramento supervisionato di una rete profonda;
- ogni strato riceve **tutta l’attenzione dell’apprendimento** a turno, perché in quel momento è l’unico strato «in cima»;
- può **sfruttare i dati non etichettati**;
- quando alla fine si rifinisce tutta la rete in modo supervisionato, i pesi sono **già sistemati** in un buon **bacino di errore** e serve solo un fine-tuning. Questo aiuta contro:
  - l’**apprendimento inefficace dei primi strati**;
  - i **minimi locali** delle reti profonde.

> ✅ **I problemi del §6 e le risposte.**
>
> | Problema | Risposta del greedy layer-wise training | Risposta moderna (p. 13) |
> |---|---|---|
> | primi strati che non imparano | ogni strato si addestra da solo, a turno | connessioni residue, normalizzazione |
> | gradiente instabile | la rete profonda si addestra solo alla fine, già inizializzata | inizializzazioni migliori, ReLU |
> | poche etichette | pre-addestramento non supervisionato | modelli pre-addestrati e transfer learning |
> | minimi locali | si parte da un buon bacino di errore | grandi dataset, pre-addestramento |

---

### **8. Modelli generativi: GAN e diffusione**

_(slide pp. 61–77)_

#### **8.1. Generative Adversarial Networks**

_(slide pp. 61–67)_

**Creare un pattern o un’immagine da un numero casuale** (p. 61): un **punto di vista diverso** per addestrare le reti profonde.

> 📌 **Come si usa una GAN** (p. 62).
> 1. Un **dataset noto** serve come dati di addestramento iniziali per il **discriminatore**.
> 2. Si esegue l’**addestramento della GAN**.
> 3. Si usa il **generatore** $G(\cdot)$ nella propria applicazione.
>
> **Due reti in competizione** (p. 64): il **generatore** crea immagini a partire da rumore casuale, il **discriminatore** cerca di distinguere le immagini vere da quelle generate. La slide invita a notare la **«forma» degli strati convolutivi**: nel generatore le mappe **crescono** da un vettore piccolo fino all’immagine, nel discriminatore **si riducono** fino alla decisione vero/falso.

<!-- SOURCE_VISUAL id="IS22-V24" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="62" type="diagramma" description="Schema d’uso di una GAN: dataset noto, addestramento, uso del generatore" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V25" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="64" type="diagramma" description="Generatore e discriminatore in competizione, con la forma degli strati convolutivi" reason="Architettura presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il gioco a due in formula (nota didattica).** Il discriminatore $D$ restituisce la probabilità che un’immagine sia vera; il generatore $G$ trasforma un rumore $\mathbf{z}$ in un’immagine. L’addestramento risolve
> $$\min_G \max_D \; \mathbb{E}_{\mathbf{x} \sim p_{\text{dati}}}\left[\log D(\mathbf{x})\right] + \mathbb{E}_{\mathbf{z} \sim p_{\mathbf{z}}}\left[\log\left(1 - D(G(\mathbf{z}))\right)\right]$$
> $D$ vuole questo valore **alto** (riconosce i veri, scarta i falsi); $G$ lo vuole **basso** (inganna $D$). All’equilibrio ideale le immagini generate sono indistinguibili dalle vere e $D$ risponde $0{,}5$ a tutto. In pratica l’addestramento è **instabile**: se $D$ vince troppo presto, $G$ non riceve gradiente utile; un altro guasto tipico è il **mode collapse**, in cui $G$ produce sempre poche immagini simili.

**Vera o creata da una GAN?** (p. 63): è **falsa**. Usi delle GAN:

- **data augmentation**;
- **attacchi**;
- **simulazione di condizioni diverse** (*style transfer*): **invecchiamento** del volto, **giorno/notte**, **inverno/estate**.

<!-- SOURCE_VISUAL id="IS22-V26" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="63" type="immagini" description="Usi delle GAN: invecchiamento, giorno e notte, inverno ed estate" reason="Esempi visivi presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Vera o generata da una rete?** (pp. 65–66). Il sito *thispersondoesnotexist.com* mostra volti **immaginati** da una GAN, **StyleGAN2** (dicembre 2019, Karras e altri, NVIDIA). La p. 66 mostra esempi di volti falsi.

<!-- SOURCE_VISUAL id="IS22-V27" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="66" type="immagini" description="Esempi di volti falsi generati da una GAN" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Manca ancora la «Gestalt»** (p. 67). L’**analisi del contesto** rivela gli errori della GAN: la **fusione delle feature di livelli diversi** non è **ancora perfettamente realistica** (orecchini diversi, sfondi incoerenti, dettagli che non stanno insieme). Con StyleGAN2 i risultati sono migliorati. È la Gestalt della Lezione 02, §6: il tutto è più della somma delle parti, e le parti generate bene non bastano a fare un tutto coerente.

<!-- SOURCE_VISUAL id="IS22-V28" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="67" type="immagini" description="Errori di contesto nei volti generati che rivelano la GAN" reason="Dettagli visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2. Modelli di diffusione e arte generata**

_(slide pp. 68–73)_

> 📌 **Modelli di diffusione** (pp. 68–69).
> - Generano dati **imparando a invertire un processo graduale di aggiunta di rumore**.
> - **In addestramento**, il modello osserva immagini **progressivamente corrotte dal rumore** e impara a **prevedere come togliere quel rumore**, passo dopo passo.
> - **In generazione**, si parte da **rumore casuale** e lo si **ripulisce iterativamente** fino a ottenere un’immagine realistica.
> - Nel **text-to-image**, un **encoder di testo** converte il prompt in **embedding** che **condizionano** il modello di diffusione, guidando ogni passo di pulizia verso un’immagine coerente con il prompt. **CLIP** è comune in modelli come **Stable Diffusion v1**, ma i sistemi moderni usano anche altri encoder, come **T5**.
> - Sono oggi uno degli **approcci dominanti** per la generazione di immagini di alta qualità, l’**editing**, l’**inpainting**, la **super-risoluzione** e il text-to-image. Si usano anche per la **data augmentation**.

> 💡 **La diffusione in formula (nota didattica).** Nella formulazione più diffusa (DDPM), l’immagine $\mathbf{x}_0$ corrotta al passo $t$ si ottiene in un colpo solo:
> $$\mathbf{x}_t = \sqrt{\bar{\alpha}_t}\, \mathbf{x}_0 + \sqrt{1 - \bar{\alpha}_t}\, \boldsymbol{\varepsilon}, \qquad \boldsymbol{\varepsilon} \sim \mathcal{N}(\mathbf{0}, I)$$
> dove $\bar{\alpha}_t$ scende da circa 1 (immagine pulita) a circa 0 (rumore puro). La rete $\boldsymbol{\varepsilon}_\theta$ impara a **indovinare il rumore** aggiunto:
> $$\mathcal{L} = \mathbb{E}\left[\lVert \boldsymbol{\varepsilon} - \boldsymbol{\varepsilon}_\theta(\mathbf{x}_t, t) \rVert^2\right]$$
> È un **denoising autoencoder** (§1.2) ripetuto su molti livelli di rumore. Rispetto alle GAN l’addestramento è **stabile** (non c’è un gioco a due), ma la generazione è più **lenta**, perché richiede molti passi.

<!-- SOURCE_VISUAL id="IS22-V29" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="68" type="diagramma" description="Processo di diffusione: aggiunta graduale di rumore e pulizia iterativa" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sony World Photography Awards 2023** (p. 70, BBC del 18/04/2023). L’opera dell’artista tedesco **Boris Eldagsen**, *Pseudomnesia: The Electrician*, vinse la categoria *creative open*. Il vincitore **rifiutò il premio** rivelando che l’immagine era stata **creata con l’AI**.

<!-- SOURCE_VISUAL id="IS22-V30" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="70" type="immagine" description="Pseudomnesia: The Electrician di Boris Eldagsen, Sony World Photography Awards 2023" reason="Immagine presente solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La p. 71 riprende il tema: **manca la Gestalt**… ma **oggi**?

<!-- SOURCE_VISUAL id="IS22-V31" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="71" type="immagini" description="Manca la Gestalt, ma che dire di oggi: immagini generate recenti" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Style transfer con CNN (GAN)** (p. 72): combinazioni di contenuto e stile da apprezzare con calma; la slide annuncia che nelle lezioni successive si vedrà come farlo.

<!-- SOURCE_VISUAL id="IS22-V32" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="72" type="immagini" description="Combinazioni di contenuto e stile ottenute con lo style transfer" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Arte con le GAN** (p. 73). Il **25 ottobre 2018** Christie’s vendette per **432 000 dollari** un ritratto **generato da una GAN**, basato su **codice open source** scritto da **Robbie Barrat** (Stanford).

> 💡 **Il contesto (nota didattica).** Il ritratto è *Edmond de Belamy*, presentato dal collettivo francese **Obvious**. La vendita aprì anche una discussione sul **merito** e sulla **paternità**: il codice di partenza era di Barrat, che non partecipò alla vendita.

<!-- SOURCE_VISUAL id="IS22-V33" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="73" type="immagine" description="Ritratto generato da una GAN venduto da Christie’s nel 2018" reason="Immagine presente solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.3. Usi delle GAN e Model Autophagy Disorder**

_(slide pp. 74–77)_

**Non solo volti** (pp. 74–75). Con le GAN si possono creare:

- **voci** (text-to-speech);
- **dati biometrici**, per esempio **impronte digitali**;
- **nuovi dataset**: **dati di addestramento realistici** sintetici, utili soprattutto quando problemi di **privacy** impediscono di usare i dati reali.

E si risolvono applicazioni diverse: **sintesi di immagini**, **data augmentation**, **super-risoluzione**, **text-to-image**, **traduzione immagine-immagine**, **restauro** di immagini e video d’epoca (*de-oldifying*), **generazione e conversione della voce**.

> ⚠️ **Midjourney non è una GAN (nota didattica).** La p. 75 illustra l’elenco con una «colomba meccanica» creata con **Midjourney**, che è un sistema basato su **modelli di diffusione** (§8.2), non su GAN. Gli usi elencati valgono in generale per i **modelli generativi**. Anche i «dati sintetici per la privacy» vanno verificati: un generatore addestrato su pochi dati può **memorizzare** e riprodurre esempi reali.

> 📌 **L’effetto MAD** (*Model Autophagy Disorder*, pp. 76–77). **Il peggior nemico dell’AI potrebbe essere l’AI stessa.**
> - Uno studio della **Rice University** e di **Stanford** (arXiv 2307.01850) ha mostrato che addestrare i modelli su **dati sintetici** sembra causarne l’**erosione**, almeno sul lungo periodo.
> - MAD indica questa apparente «**autoallergia**» del software.
> - Molti modelli vengono così addestrati, **senza saperlo**, su quantità crescenti di **dati già sintetizzati**.
>
> **Conclusione** (p. 77): **senza abbastanza dati reali freschi** a ogni generazione di un **ciclo autofago**, i futuri modelli generativi sono destinati a veder **calare progressivamente** la loro **qualità** (precisione) o la loro **diversità** (recall). La slide mostra l’esempio con **riaddestramenti successivi di StyleGAN-2**. I dati sintetici richiedono **tracciabilità della provenienza**, **filtraggio**, **controllo della diversità** e **validazione**.

<!-- SOURCE_VISUAL id="IS22-V34" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="77" type="immagini" description="Degrado dei volti generati con riaddestramenti successivi di StyleGAN-2" reason="Sequenza di immagini visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **«Mucche che mangiano altre mucche» (nota didattica).** Il motto delle slide richiama la **encefalopatia spongiforme bovina** («mucca pazza», in inglese *mad cow*), diffusa dall’uso di farine animali nell’alimentazione dei bovini. Un modello che si nutre dei prodotti di altri modelli perde a ogni generazione le **code della distribuzione**, cioè i casi rari: prima scompare la diversità, poi arrivano gli artefatti. Il legame con il corso è la regola «dati reali, di qualità, controllati» della Lezione 06.

---

### **9. I limiti attuali del ML**

_(slide pp. 78–81)_

**Più dati? Più parametri? È la strada giusta per sistemi migliori?** (p. 78)

**La frontiera del calcolo efficiente** (p. 79). La slide mostra l’**aumento esponenziale del calcolo di addestramento** dei modelli GPT di OpenAI dal 2018 al 2023.

- Le **curve blu** (a) mostrano che modelli **sempre più grandi** richiedono **più calcolo** per raggiungere errori più bassi.
- La **linea rossa tratteggiata** (b) è la **frontiera del calcolo efficiente**: oltre di essa, altro calcolo produce **rendimenti decrescenti** nel miglioramento delle prestazioni.
- Nella zona sotto la frontiera **non ci sono modelli**: nessun modello riesce a fare meglio di quanto il calcolo investito consenta.

**Scaling laws per i modelli linguistici** (p. 80). Le prestazioni **migliorano** (l’errore scende) con diverse misure di calcolo: **addestramento più lungo**, **dataset più grandi**, **più parametri**. La tendenza è regolare.

**Limiti per i ViT** (p. 81). I **Vision Transformer** (ViT) sono reti profonde comuni per le immagini: usano l’architettura **transformer**, nata per il linguaggio (BERT, GPT, Lezione 13, §5), per elaborare dati visivi. Anche nei ViT l’errore di addestramento segue un andamento **molto simile** a quello dei LLM.

<!-- SOURCE_VISUAL id="IS22-V35" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="79" type="grafico" description="Frontiera del calcolo efficiente per i modelli GPT dal 2018 al 2023" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V36" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="80" type="grafico" description="Scaling laws: errore in funzione di calcolo, dati e parametri" reason="Curve in scala logaritmica visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS22-V37" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="81" type="grafico" description="Andamento dell’errore di addestramento nei Vision Transformer" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Leggere una scaling law (nota didattica).** Le leggi di Kaplan e altri (2020) hanno forma di **legge di potenza**. Per il numero di parametri $N$:
> $$L(N) \approx \left(\frac{N_c}{N}\right)^{\alpha_N}, \qquad \alpha_N \approx 0{,}076$$
> In scala doppio-logaritmica è una **retta**, e per questo le curve delle slide appaiono così regolari. Il **rendimento decrescente** si vede con un conto: **raddoppiare** i parametri moltiplica l’errore per $2^{-0{,}076} \approx 0{,}949$, cioè lo riduce di circa il **5%**. Per dimezzare l’errore bisogna moltiplicare i parametri per $2^{1/0{,}076} \approx 9 \times 10^3$. Da qui la domanda della p. 78 e il problema dell’oligopolio della Lezione 04, §3.2: solo pochi possono permettersi di scalare così.

---

### **10. LLM, agenti e progettazione del ML**

_(slide pp. 82–90)_

**Che cosa può fare uno strumento come ChatGPT per il progettista di ML** (p. 82).

> 📌 **Nemico o alleato?** (p. 83).
> - Come tutti i modelli linguistici, ChatGPT si può vedere come un **alleato** per chi analizza dati e progetta modelli, **non come un nemico**.
> - È **cruciale** esercitare il **pensiero critico**, **validare** i risultati e **verificare in modo indipendente** le informazioni fornite.
> - Come modello linguistico **non ha accesso** a dati **in tempo reale** o **specifici del dominio**: le risposte si basano su **pattern appresi** da informazioni preesistenti.

> ⚠️ **Precisazioni (nota didattica).** L’ultimo punto descrive il modello «nudo». Gli assistenti attuali possono cercare sul web, leggere file e usare una base di conoscenza (la RAG della Lezione 15, §4): l’accesso ai dati del dominio c’è, ma **non** rende le risposte automaticamente corrette. Resta poi il problema opposto: prima di incollare un dataset aziendale in un servizio esterno, valgono le regole su **privacy e riservatezza** della Lezione 15, §2.

**Dove può aiutare** (pp. 84–86):

| Compito | Che cosa può fare (slide) |
|---|---|
| 1. **Analisi esplorativa** e **visualizzazione** | analizzare i dataset e fornire indicazioni: distribuzione dei dati, pattern, **outlier**, relazioni tra variabili |
| 2. **Pre-elaborazione**, **selezione delle feature**, **riduzione della dimensionalità** | pulizia dei dati, **valori mancanti**, **scalatura** delle feature, **codifica delle variabili categoriche**, con indicazioni o esempi di codice |
| 3. **Scelta dell’algoritmo** | consigli basati sulle caratteristiche del dataset e sugli obiettivi; **pro e contro** dei diversi algoritmi |
| 4. **Sviluppo del modello**, deep learning, **valutazione** e **interpretazione** | per classificazione, regressione, clustering: implementazione, **ottimizzazione degli iperparametri**, valutazione delle prestazioni |
| 5. **Assistenza al codice**, risoluzione dei problemi, **debugging** | frammenti di codice e spiegazioni in **Python**, **R** o **MATLAB** |
| 6. **Ricerca e comprensione concettuale** | spiegazioni su concetti statistici, tecniche di ML, buone pratiche; rassegna della letteratura; ispirazione |
| anche | applicazioni specifiche come **serie temporali** e **NLP**, e altro |

**Creare un dizionario dei dati** (pp. 87–88). Alla richiesta «*give me a data dictionary of the iris dataset*», il modello descrive i campi; per l’ultimo, «**Species**», spiega che è una **variabile categorica** con tre categorie distinte: **Setosa**, **Versicolor** e **Virginica**. Alla richiesta di un dizionario per **un proprio dataset**, il modello **usa il contesto** fornito (intestazioni, esempi) per costruirlo.

<!-- SOURCE_VISUAL id="IS22-V38" source="Lesson_22_Autoencoders_AutomaticFeatureExtraction_TransferKnowledge_TrainingDeepModels.pdf" page="87" type="schermata" description="Dizionario dei dati del dataset Iris generato da ChatGPT" reason="Risposta del modello presente solo come schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Com’è fatto il dizionario di Iris (nota didattica).** Serve a verificare la risposta del modello, invece di fidarsi:
>
> | Campo | Tipo | Unità | Contenuto |
> |---|---|---|---|
> | sepal length | numerico continuo | cm | lunghezza del sepalo |
> | sepal width | numerico continuo | cm | larghezza del sepalo |
> | petal length | numerico continuo | cm | lunghezza del petalo |
> | petal width | numerico continuo | cm | larghezza del petalo |
> | species | categorico, 3 valori | — | Setosa, Versicolor, Virginica |
>
> Il dataset ha 150 campioni, 50 per specie, senza valori mancanti (Lezioni 07 e 16).

> 📌 **Progettare il ML con gli agenti** (p. 89). I modelli linguistici **non sono solo assistenti di chat**. I **sistemi agentici** possono:
> - **ispezionare le cartelle** di un progetto e **leggere i dataset**;
> - **scrivere script** ed **eseguire esperimenti**;
> - **generare grafici**, **confrontare metriche** e **documentare** i risultati.
>
> Il **progettista resta responsabile** di **validazione**, **controlli sul data leakage**, **disegno sperimentale** e **interpretazione** (Lezione 15, §1).

**Gli agenti e la «corsa all’oro»** (p. 90). Durante le corse all’oro (le migrazioni di massa in cerca d’oro, soprattutto nel XIX secolo) **la maggior parte** dei partecipanti **non si arricchì**, e anzi affrontò difficoltà o perdite. **Alcuni** fecero grandi profitti **non estraendo oro**, ma con le **attività di contorno**. Questo richiamo storico aiuta a **riformulare** che cosa LLM e agenti possono fare nel proprio lavoro: pensare a **nuove attività** aiutate, basate o migliorate da questi strumenti, e **non** a qualcosa «**contro**» di loro o che ne viene «**sostituito**».

> 💡 **Picconi e pale (nota didattica).** Il detto americano è «*sell picks and shovels*»: nella corsa all’oro guadagnava con certezza chi vendeva gli attrezzi. Per un progettista di sistemi intelligenti il valore è nelle attività attorno al modello, che il corso ha coperto una per una: dati puliti e armonizzati, validazione corretta, monitoraggio, integrazione nel processo industriale.

---

### **11. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Autoencoder** / **PCA** | proiezione appresa, anche non lineare / proiezione lineare sulle componenti principali |
| **Denoising AE** / **sparse AE** | ricostruisce l’input pulito da uno corrotto / penalizza le unità attive |
| **Autoencoder semplice** / **VAE** | spazio latente non necessariamente continuo / spazio latente regolarizzato e campionabile |
| **Transfer learning** / **fine-tuning** | riusare la conoscenza di un altro compito / aggiornare (in parte o tutti) i pesi pre-addestrati sul nuovo compito |
| **Congelare** / **rifinire** | pesi fissi / pesi aggiornati, di solito con learning rate piccolo |
| **Primi strati** / **ultimi strati** | feature generiche (bordi, colori) / feature specifiche delle classi originali |
| **Oblio catastrofico** / **overfitting** | perde il vecchio compito imparando il nuovo / si adatta troppo ai dati di training dello stesso compito |
| **Rehearsal** / **EWC** | ripassa i vecchi dati / lega i pesi importanti con una regolarizzazione |
| **Augmentation classica** / **generativa** | trasformazioni geometriche, rumore, colore / dati sintetici da GAN o diffusione |
| **CNN come estrattore** / **fine-tuning** | rete fissa + classificatore lineare / rete aggiornata |
| **Equivarianza** / **invarianza** | la risposta si sposta con l’oggetto / la risposta non cambia |
| **Vanishing** / **exploding gradient** | prodotto di fattori piccoli, primi strati fermi / prodotto di fattori grandi, pesi instabili |
| **Greedy layer-wise** / **end-to-end** | uno strato alla volta, non supervisionato, poi fine-tuning / tutta la rete insieme dall’inizio |
| **GAN** / **diffusione** | generatore contro discriminatore, veloce ma instabile / pulizia iterativa del rumore, stabile ma lenta |
| **Qualità (precisione)** / **diversità (recall)** nel MAD | immagini sempre meno realistiche / immagini sempre meno varie |
| **Assistente LLM** / **agente** | risponde in chat / agisce su file, script ed esperimenti |

---

### **12. Sintesi della lezione**

_(slide p. 91)_

> ✅ **Punti principali** (p. 91).
> - **Autoencoder**: ricostruire l’input attraverso un collo di bottiglia per imparare feature senza etichette; autoencoder impilati; riduzione della dimensionalità, denoising, colorazione.
> - **Addestramento e progetto dei modelli profondi**:
>   - **fine-tuning** con strati congelati e rifiniti, nelle tre opzioni;
>   - **transfer knowledge** da modelli pre-addestrati;
>   - **data augmentation** che conserva l’etichetta, applicata dopo la divisione dei dati.
> - **Oblio catastrofico** e sue mitigazioni: rehearsal, EWC, distillazione, architetture dinamiche.
> - **Estrazione automatica delle feature**: CNN pre-addestrata senza l’ultimo strato + classificatore lineare.
> - **Problemi dell’addestramento** dei modelli profondi (gradiente che si diffonde o esplode, poche etichette, minimi locali) e la soluzione storica del **greedy layer-wise training**.
> - **Generative Adversarial Networks**: generazione di immagini sintetiche realistiche, modelli di diffusione e **Model Autophagy Disorder** dei modelli generativi.
> - **Scaling laws** dei modelli linguistici e rendimenti decrescenti.
> - **LLM** (per esempio ChatGPT) e **agenti** nella progettazione del machine learning, con la responsabilità della validazione che resta al progettista.
