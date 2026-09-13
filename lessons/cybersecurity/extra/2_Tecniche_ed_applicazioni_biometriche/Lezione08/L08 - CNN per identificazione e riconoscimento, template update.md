# **Lezione 8: CNN per identificazione e riconoscimento, template update**

---

### **0. Panoramica della lezione**

_(slide pp. 1–3)_

La lezione è divisa in due parti:

- **Parte A — Deep learning: hardware e software per applicazioni biometriche**: HW e SW per il deep learning, esempi di CNN pubbliche utili, CNN in applicazioni biometriche (esempi, estrazione automatica di feature, identificazione, autenticazione, uso interno della PCA);
- **Parte B — Metodi avanzati per la progettazione e il miglioramento dei sistemi biometrici**: aggiornamento (*update*) e selezione dei template.

---

## **Parte A — Hardware e software per il deep learning**

### **1. L'hardware**

_(slide pp. 4–15)_

#### **1.1 Perché il machine learning è difficile**

_(slide pp. 4–6)_

Il deep learning richiede risorse in tre direzioni:

- **archiviazione di massa**: servono **dati**, e oggi ce ne sono di più (dischi da terabyte, data center);
- **prestazioni più elevate**: più **memoria** per gestire i dati;
- **maggiore potenza di calcolo**, anche per l'**apprendimento online**.

La slide cita la **Tensor Processing Unit (TPU)**, le GPU NVIDIA di fascia alta con interconnessioni **InfiniBand**, e la presentazione di **NVIDIA Cosmos** e delle **GPU Blackwell RTX 50** da parte di Jensen Huang.

**GPU contro CPU** (p. 6): per i progetti impegnativi serve una **GPU**, con un'accelerazione **media di 10–20×**, ma il valore dipende dal contesto e non dalla singola applicazione.

<!-- SOURCE_VISUAL id="TAB08-V01" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="6" type="grafico di confronto" description="Accelerazione media GPU contro CPU (10-20x) su carichi di deep learning" reason="Il confronto numerico è nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2 CPU, GPU, TPU, NPU, VPU**

_(slide pp. 7–10)_

| Processore | Caratteristiche (slide) | Uso in biometria |
|---|---|---|
| **CPU** (*Central Processing Unit*) | general purpose, **elevata flessibilità**, **basso parallelismo** | prototipi, pre-processing, controllo del sistema; **non adatta** a training deep o inference real-time complessa |
| **GPU** (*Graphics Processing Unit*) | **parallelismo massivo**, elevata banda di memoria | **standard per il training** di CNN biometriche; inference batch o real-time su desktop e server |
| **TPU** (*Tensor Processing Unit*) | acceleratore specializzato per **operazioni tensoriali** | efficiente per training e inference su **modelli standardizzati**; flessibilità limitata, uso tipicamente **cloud** |
| **NPU** (*Neural Processing Unit*) | acceleratore per **inference neurale a bassa potenza** | ideale per **biometria embedded e real-time**; ottimizzata per **latenza, consumo e privacy on-device** |
| **VPU** (*Vision Processing Unit*) | acceleratore per **elaborazione visiva** e computer vision | pre-processing, feature extraction e inference su video/immagini; ideale per **biometria visiva embedded** (volto, gesti, conteggio persone); **compromesso tra GPU e NPU** |

<!-- SOURCE_VISUAL id="TAB08-V02" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="7" type="immagini di processori" description="CPU, GPU, TPU, NPU e VPU" reason="Riconoscere visivamente le famiglie di acceleratori" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3 Quale hardware serve davvero**

_(slide p. 11)_

| Fase | Descrizione | Hardware |
|---|---|---|
| **Training** di CNN biometriche (offline) | addestramento su grandi dataset di immagini o video | **GPU** desktop/server o **cloud**: molta memoria, alto parallelismo, tempi lunghi |
| **Inference batch** (offline o quasi) | analisi di grandi volumi senza vincoli stringenti di latenza | **GPU entry-level** o **CPU high-end** |
| **Inference real-time** | riconoscimento durante l'acquisizione, con latenze di **millisecondi** | **GPU embedded** o **NPU** (ottimizzazione energetica e temporale) |
| **On-sensor / on-device** | matching ed estrazione **direttamente sul dispositivo o sensore** | **ASIC / SoC dedicati**: massima privacy, minima latenza, scarsa flessibilità |

> 💡 **Il legame con la Lezione 1.** L'ultima riga è l'hardware del **match on sensor**: più l'elaborazione si avvicina al sensore, più aumentano privacy e velocità e diminuisce la flessibilità.

#### **1.4 Server farm e alternative economiche**

_(slide pp. 12–15)_

- **Server farm per ML** (p. 12): Amazon (AWS), Google, Facebook (server *Big Sur* nel data center di Prineville), IBM, … Il corso *Intelligent Systems for Industry, Supply Chain and Environment* approfondisce l'uso delle reti deep dai sistemi custom fino al cloud.

> ⚠️ **Precisazione sulle slide.** La slide scrive «IBM (AzureML)»: **Azure Machine Learning** è un servizio di **Microsoft**, non di IBM (la piattaforma di IBM è Watson).

- **Le GPU non servono per forza** (pp. 13–14). Una **Raspberry Pi**, CPU general purpose a basso consumo, non ha la potenza per eseguire in tempo reale CNN complesse come quelle per il riconoscimento facciale. Si può però aggiungere un acceleratore esterno: la «chiavetta» **Intel Movidius** (*Neural Compute Stick 2*, chip **Myriad X**) è una **VPU** che offre fino a **1 TOPS** di inference neurale dedicata e oltre **4 TOPS** di capacità complessiva (TOPS = *Trillion Operations Per Second*).
- **Raspberry Pi AI HAT+** (p. 15): integra un acceleratore per reti neurali che trasforma la **Raspberry Pi 5** in una piattaforma AI efficiente; disponibile con **13 o 26 TOPS**, gestisce più modelli in parallelo, supporta **TensorFlow e PyTorch**, costa **70–120 EUR**.

<!-- SOURCE_VISUAL id="TAB08-V03" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="14" type="fotografia" description="Raspberry Pi con Intel Movidius Neural Compute Stick" reason="Esempio concreto di acceleratore VPU economico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V04" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="15" type="fotografia prodotto" description="Raspberry Pi AI HAT+ da 13 o 26 TOPS" reason="Esempio di acceleratore per Raspberry Pi 5" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Il software e i modelli pre-allenati**

_(slide pp. 16–29)_

#### **2.1 Model Zoo e fonti di modelli**

_(slide pp. 16–19)_

Le grandi CNN richiedono **settimane** di addestramento su più GPU con dataset come ImageNet. Molti ricercatori **rilasciano la rete finale** a beneficio di altri, che possono usarla per il **fine tuning**. Esistono **repository pubblici** (*Model Zoo*) per il riuso diretto: i modelli includono **pesi, architettura e protocolli d'uso**, spesso con script accessori.

| Fonte | Contenuto |
|---|---|
| **Hugging Face Hub** | hub centralizzato con modelli, pesi, documentazione e versioni |
| **Repository ufficiali** | modelli rilasciati da università, centri di ricerca, aziende |
| **GitHub** di ricercatori e gruppi | codice, pesi e implementazioni legati a specifici articoli |
| **Ecosistemi dei framework** | modelli integrati nelle librerie: in **PyTorch/Torchvision** ci sono già ResNet, VGG, MobileNet, EfficientNet |

Su Hugging Face non ci sono solo modelli generici: molti sono **pronti per compiti biometrici** (volto, gruppi, iride), utilizzabili per **embedding**, **matching** e come base di pipeline reali. Esempi della slide: `biometric-ai-lab/Face_Recognition` (riconoscimento ed estrazione di feature), `AI-Solutions-KK/face_recognition` (pronto all'uso), `Aldey/tripleS-Member-Recognition` (riconoscimento di membri di un gruppo, esperimento molto specifico), `Worldcoin/iris-semantic-segmentation` (pre-processing per l'iride).

#### **2.2 L'approccio corretto: divide et impera**

_(slide p. 20)_

> 📌 **Non è quasi mai conveniente chiedere a una rete deep di elaborare in un colpo solo una grande immagine.**

Esempio: «prendi l'immagine e **sfoca i volti dei bambini sotto i 13 anni**». Farlo in un solo passaggio è ancora oggi **praticamente impossibile**. L'approccio corretto è ***divide et impera***:

1. immagine iniziale → **face detector** → **bounding box** dei volti;
2. **ritaglio** di ogni volto → **stimatore dell'età** → età stimata;
3. **sfocatura** normale (filtro passa-basso o mediano) dei volti selezionati.

> 💡 **Perché funziona.** Ogni modulo risolve un problema **ben definito** con un modello specializzato e verificabile separatamente. È lo stesso principio del «cervello del progettista» della Lezione 6; il compito «BLUR MINORI» della Lezione 9 lo mette in pratica.

<!-- SOURCE_VISUAL id="TAB08-V05" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="20" type="esempio" description="Immagine con volti di bambini da sfocare tramite pipeline detector, age estimator, blur" reason="Esempio visivo dell'approccio divide et impera" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3 Reti famose**

_(slide pp. 21–24)_

Queste reti sono impiegate in **centinaia di applicazioni**, anche dopo re-training.

| Rete | Parametri | Perché è importante (slide) |
|---|---:|---|
| **LeNet-5** (Yann LeCun) | 60 mila | una delle prime CNN, per il riconoscimento della **scrittura** (cifre); introduce **convoluzione a pesi condivisi**, **pooling** e classificazione **end-to-end** robusta alle variazioni di forma. Un'immagine piccola o in toni di grigio **non significa** che la rete sia poco potente |
| **AlexNet** | 60 milioni | uscita: vettore di **1000 probabilità**, una per classe; allenata su **ImageNet**; la **prima** rete a risolvere ImageNet sfruttando **CNN profonde addestrate su GPU**, cambiando definitivamente la computer vision |
| **VGG16** (Visual Geometry Group, Oxford; Simonyan e Zisserman) | 138 milioni | **92,7%** di accuratezza *top-5* su ImageNet (oltre **14 milioni** di immagini, 1000 classi); ha mostrato che la **profondità da sola** è molto efficace e che architetture **semplici e molto profonde** (16 strati allenabili) apprendono rappresentazioni straordinariamente complesse |

<!-- SOURCE_VISUAL id="TAB08-V06" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="22" type="schema architettura" description="Architettura di LeNet-5 con 60k parametri" reason="Schema della prima CNN" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V07" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="23" type="schema architettura" description="Architettura di AlexNet con uscita a 1000 classi e 60M parametri" reason="Schema di AlexNet" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V08" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="24" type="schema architettura" description="Architettura di VGG16 con 138M parametri" reason="Schema di VGG16" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.4 ResNet: lo standard di fatto**

_(slide pp. 25–28)_

**ResNet** (*Residual Neural Network*) è lo standard di fatto per: **face recognition**, **re-identificazione** con metric learning, **PAD/liveness** (rilevamento degli attacchi al sensore). Quasi tutti i **backbone** moderni ne derivano concettualmente.

> 📌 **Backbone.** La parte di una rete profonda che **estrae rappresentazioni generali** dai dati, riutilizzabile come base per diversi compiti applicativi.

**L'idea: le skip connection** (p. 26):

- gli strati apprendono **funzioni residue** rispetto al proprio ingresso: un layer ResNet non impara una rappresentazione completa, ma **come correggere e migliorare** una rappresentazione esistente;
- le **connessioni di salto** permettono agli strati di concentrarsi su **ciò che cambia** rispetto all'ingresso;
- le connessioni residue permettono al **gradiente di fluire all'indietro** più facilmente, riducendo il **vanishing gradient** (Lezione 7, §9.2);
- dentro una CNN, e in particolare una ResNet, **non si lavora più su immagini** ma su **mappe di feature astratte**.

> 💡 **Nota didattica (non presente nelle slide): il blocco residuo.** Se $x$ è l'ingresso del blocco e $F(x)$ la trasformazione appresa dagli strati interni, l'uscita è
>
> $$y = F(x) + x$$
>
> Se lo strato non ha nulla da aggiungere, gli basta imparare $F(x) \approx 0$ e l'informazione passa inalterata. Nel calcolo del gradiente il termine $+x$ contribuisce con una derivata pari a 1, che crea una «scorciatoia» per il gradiente attraverso molti strati.

**La famiglia** (p. 27): **ResNet-34, ResNet-50, ResNet-101, ResNet-152**. Cambiano il numero di strati e di blocchi residui; si bilanciano **accuratezza e costo computazionale**; la scelta dipende dai vincoli del sistema. Il numero indica gli **strati con pesi addestrabili**.

**Come si usa** (p. 28): prima dello strato finale fully connected arrivano **512 matrici di feature 7×7**. Il **Global Average Pooling** calcola la **media** di ciascuna matrice e produce un **vettore di embedding a 512 dimensioni**. Questo vettore si usa in due modi:

1. **per confrontare gli individui** (è il template);
2. come ingresso di un nuovo strato fully connected, allenando **solo gli ultimi neuroni** per la classificazione desiderata (per esempio: occhio, volto o mano).

<!-- SOURCE_VISUAL id="TAB08-V09" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="26" type="schema" description="Blocco residuo ResNet con skip connection" reason="Rappresentazione visiva della connessione residua" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V10" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="28" type="schema" description="ResNet: 512 mappe 7x7, global average pooling, vettore di embedding a 512 dimensioni e uscite" reason="Mostra dove si estrae l'embedding" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.5 Librerie e toolbox**

_(slide p. 29)_

Sono disponibili **centinaia di toolbox**: Apache Singa, Amazon Machine Learning, Azure ML Studio, **Caffe**, H2O, Massive Online Analysis (MOA), MLlib (Spark), mlpack, **MATLAB toolboxes**, Pattern, **Scikit-Learn**, Shogun, **Theano**, **Torch**, Veles, e altri.

---

### **3. Deep learning per l'identificazione: l'approccio «pericoloso»**

_(slide pp. 30–34)_

La slide p. 31 rimanda alla tabella dei metodi deep per modalità del survey di Sundararajan e Woodard (Lezione 7, Parte C).

#### **3.1 Il classificatore che memorizza le persone**

_(slide pp. 32–33)_

L'approccio **più usato**, ma **«pericoloso»**, è allenare un **classificatore** che riceve un campione e restituisce direttamente **nome o identificativo**.

- Si usano campioni di **ogni individuo da enrollare** durante l'apprendimento, che vengono **«memorizzati» nei pesi** della rete.
- È come chiedere alla rete di **approssimare un intero sistema di identificazione**: feature extraction, matching **e database** compresi.

> ⚠️ **Svantaggi molto importanti.**
> 1. **Impedisce di fatto la L(N)PO**: le persone da riconoscere devono stare nel training, quindi non si può validare su persone mai viste.
> 2. **Problemi di generalizzazione** durante il deployment.
> 3. Se arriva un **nuovo utente**, bisogna **rifare o ripartire con il training**.
> 4. Le CNN soffrono spesso di **catastrophic forgetting**: allenate su nuove persone, **dimenticano** quelle dei primi training, quindi serve riallenare da zero.

<!-- SOURCE_VISUAL id="TAB08-V11" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="32" type="schema" description="Trained classifier da sample a name/identifier che sostituisce l'intero sistema di identificazione con DB" reason="Confronto visivo con la pipeline classica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2 Identificazione come N autenticazioni**

_(slide p. 34)_

Si vuole invece un **autenticatore**, e implementare l'identificazione **1:N come autenticazione iterata N volte**:

```text
For each template i in Gallery
    M(i) = identity_verification(Fresh, Gallery(i))
end
IF (max(M) > Id_threshold)
    ID = argmax(M)
else
    ID = impostor
```

È la regola di identificazione con reiezione della Lezione 2 (§7.3): il campione fresco viene confrontato con ogni template della galleria, si prende il più simile e lo si accetta solo sopra soglia.

---

### **4. Creare un estrattore automatico di feature biometriche**

_(slide pp. 35–40)_

#### **4.1 Passo 1: allenare con l'identificazione su immagini pubbliche**

_(slide p. 35)_

Si allena una CNN (parte A convoluzionale + parte B feed-forward con **softmax**) a **identificare** gli utenti #1, #2, #3 di un dataset di **immagini pubbliche qualunque**, non degli utenti reali del sistema.

- Il compito di identificazione serve a rendere **salienti le feature**: **le feature si formano nella parte convoluzionale**.
- È un compito **molto difficile**; **non interessa che venga molto bene**, ma aiuta a creare le feature giuste da usare poi in verifica.

> 📌 **Razionale.** Si evita il problema di **memorizzare ogni individuo nella rete** (pericoloso). Si estraggono invece in modo neurale **feature utili al riconoscimento**, e poi si fa l'identificazione con **N autenticazioni**, tornando al metodo classico.

#### **4.2 Passo 2: uso biometrico**

_(slide p. 36)_

Si **tiene solo la parte A** (convoluzionale). Ogni campione produce un **template** (il vettore di feature «neurali»); il **matcher** confronta il template A con il template B del DB e produce un **matching score**, che può essere una semplice **norma euclidea**. Ora si possono **verificare o identificare** i veri utenti.

> 💡 **Il vantaggio decisivo.** Nessuna feature degli utenti reali da enrollare è stata usata nell'apprendimento: il metodo è **generale**, si possono aggiungere utenti senza riallenare, e il sistema è persino **rivendibile** ad altri clienti. È esattamente l'architettura di **DeepFace**, **FaceNet** e della libreria `deepface` della Lezione 9.

<!-- SOURCE_VISUAL id="TAB08-V12" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="35" type="schema" description="Passo 1: training con identificazione su immagini pubbliche, parte A convoluzionale e parte B feed-forward con softmax ID" reason="Mostra dove si formano le feature" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V13" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="36" type="schema" description="Passo 2: parte A come estrattore di template A e B, matcher con DB e matching score euclideo" reason="Mostra l'uso biometrico dell'estrattore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3 Allenare per la biometria: softmax o ArcFace**

_(slide pp. 37–40)_

Nell'uso biometrico la rete è un **estrattore di embedding**, e l'addestramento della parte fully connected **non è più una classificazione standard**: si usano **loss metriche** basate su **distanze o angoli**, con l'obiettivo di **separare le identità** nello spazio delle feature **aumentando il margine** tra loro.

Si **tiene il backbone convoluzionale**, che produce l'embedding $f$, e si cambia **solo la parte finale**. L'esempio didattico della slide usa **8 identità** descritte da **feature 2D** e **distanza coseno**.

| | **Softmax** (classificazione chiusa) | **ArcFace** (loss biometrica) |
|---|---|---|
| Come separa | gli embedding sono separati **solo quanto basta** per distinguere le classi note | introduce un **margine angolare** tra le identità |
| Training | buona accuratezza | embedding della stessa persona **più compatti** |
| Nuove identità | **scarsa generalizzazione** | identità diverse **più separate** nello spazio metrico |

> 💡 **Nota didattica (non presente nelle slide): la formula di ArcFace.** Con embedding e pesi normalizzati, $\theta_j$ è l'angolo tra l'embedding e il centro della classe $j$, $y$ la classe corretta, $m$ il margine angolare e $s$ un fattore di scala:
>
> $$L = -\log \frac{e^{s\cos(\theta_y + m)}}{e^{s\cos(\theta_y + m)} + \sum_{j \neq y} e^{s\cos\theta_j}}$$
>
> Aggiungere $m$ all'angolo della classe corretta rende il compito **più difficile** durante il training: la rete deve avvicinare ogni campione al proprio centro **più del necessario**, e questo margine resta disponibile quando arrivano persone mai viste.

**In deployment bisogna adattare la metrica** (p. 40): se il training ha usato **ArcFace**, conviene usare la **distanza coseno** e non quella euclidea.

$$\cos\theta = \frac{\mathbf{a} \cdot \mathbf{b}}{\lVert\mathbf{a}\rVert\,\lVert\mathbf{b}\rVert} \qquad d_{cos} = 1 - \cos\theta$$

> ⚠️ **Metrica coerente con il training.** ArcFace organizza lo spazio per **angoli**: due embedding della stessa persona hanno direzione simile, anche se possono avere norme diverse. La distanza euclidea mescolerebbe direzione e norma, perdendo parte del margine appreso.

<!-- SOURCE_VISUAL id="TAB08-V14" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="38" type="grafico 2D" description="8 identità con feature 2D separate da softmax e da ArcFace con distanza coseno" reason="Il confronto tra le due separazioni è visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Esempi di moduli deep in biometria**

_(slide pp. 41–60)_

#### **5.1 Volto: LFW, VGGFace e PCA**

_(slide pp. 41–43)_

Sul dataset **Labeled Faces in the Wild** (LFW: **13.323** immagini di **5.749** celebrità), le strategie deep hanno **superato l'essere umano**: circa **99%** di accuratezza in **verifica** e oltre **95%** in **identificazione a insieme chiuso**.

**VGGFace + PCA** (p. 43): un modulo aggiuntivo di **PCA** permette di **ridurre la cardinalità** dello spazio delle feature, tenendo le componenti **più distintive** e aumentando ulteriormente le prestazioni.

> 💡 **Perché la PCA dopo una CNN.** L'embedding di VGG-Face ha migliaia di dimensioni, molte correlate o poco utili. Proiettarlo sulle componenti principali riduce memoria e tempo di matching (utile in identificazione, Lezione 2) e attenua la curse of dimensionality (Lezione 6). È la stessa PCA delle eigenfaces della Lezione 1, applicata però a feature neurali.

<!-- SOURCE_VISUAL id="TAB08-V15" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="43" type="schema" description="Pipeline VGGFace seguita da PCA per ridurre la dimensione delle feature" reason="Mostra il modulo PCA aggiuntivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Voce, palmo, età, OpenFace**

_(slide pp. 44–49)_

- **CNN per lo speech recognition** (pp. 44–45): il segnale vocale viene trasformato in **spettrogramma** (tempo in ascissa, frequenza in ordinata); lo spettrogramma è trattato come un'**immagine** e dato a una CNN, che riconosce le parole («I'm very», «Happy»).
- **Palm recognition contactless** con deep learning (p. 46).
- **Robust age estimation** (p. 47): rete **pre-allenata** → **PCA** → strati finali feed-forward con **fine tuning**.
- **OpenFace 2.0** (Carnegie Mellon University, p. 48): toolkit per l'analisi del volto.
- **Deep learning per la voce** (p. 49): **speaker recognition**, **speech recognition**, **speech synthesis**.

> ⚠️ **Speech contro speaker, di nuovo.** L'esempio di p. 44–45 riconosce **che cosa** viene detto: è speech recognition, non biometria (Lezione 1, §13.4). La stessa tecnica sullo spettrogramma può però servire anche allo **speaker recognition** se la rete è allenata sulle identità.

<!-- SOURCE_VISUAL id="TAB08-V16" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="44" type="schema" description="Spettrogramma tempo-frequenza dato come immagine a una CNN che riconosce la frase" reason="Mostra la trasformazione audio in immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V17" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="46" type="immagini" description="Palm recognition contactless con approccio deep learning" reason="La slide contiene solo immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V18" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="47" type="schema" description="Robust age estimation: rete pretrained, PCA, strati finali feedforward con fine tuning" reason="Schema della pipeline di stima dell'età" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V19" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="48" type="screenshot" description="Tool OpenFace 2.0 della Carnegie Mellon University" reason="Interfaccia e output del tool" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3 DeepFace e FaceNet**

_(slide pp. 50–53)_

**DeepFace** (ex Facebook, ora Meta, 2014):

- rete deep per il riconoscimento facciale con **9 strati** e **120 milioni di parametri**;
- accuratezza **97,35% ± 0,25%** su LFW, contro il **97,53%** degli esseri umani.

Pipeline (p. 51):

1. **ingresso**: immagine RGB del volto scalata a **152×152**;
2. **allineamento 2D** con **6 punti fiduciali**;
3. **allineamento 3D** con un **modello 3D standard** a **67 punti fiduciali**;
4. **frontalizzazione** in 2D, riempiendo le parti mancanti con le **simmetrie**;
5. estrazione di un vettore «deep» di **4096 elementi**.

**FaceNet** (Google, 2015):

- accuratezza **99,63%** su LFW, con **260 milioni di parametri** (valore della slide);
- esegue **solo il ritaglio** attorno al volto, senza allineamento 3D;
- usa la **triplet loss**.

> 💡 **Nota didattica (non presente nelle slide): la triplet loss.** Per ogni terna (*anchor* $a$, *positive* $p$ della stessa persona, *negative* $n$ di un'altra) con embedding $f$ e margine $\alpha$:
>
> $$L = \max\big(0,\ \lVert f(a) - f(p) \rVert^2 - \lVert f(a) - f(n) \rVert^2 + \alpha\big)$$
>
> La perdita è nulla solo quando il negativo è più lontano del positivo **di almeno $\alpha$**. È la stessa idea del margine di ArcFace, espressa con distanze invece che con angoli.

**Oltre LFW** (p. 53): alcuni modelli hanno segnato vere svolte metodologiche e costituiscono ancora la base dei sistemi moderni. Recentemente LFW è considerato **«risolto»**: si punta a benchmark più specifici come **IARPA Janus Benchmark-C (IJB-C)**, **MegaFace** e **NIST FRVT**.

<!-- SOURCE_VISUAL id="TAB08-V20" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="51" type="pipeline" description="DeepFace: input 152x152, allineamento 2D con 6 punti, allineamento 3D con 67 punti, frontalizzazione, vettore 4096" reason="Schema della pipeline di DeepFace" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V21" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="53" type="tabella o grafico" description="Migliori algoritmi su Labeled Faces in the Wild" reason="Classifica degli algoritmi presente solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4 GAN e volti sintetici**

_(slide pp. 54–60)_

Le **GAN** (*Generative Adversarial Networks*) hanno applicazioni biometriche in tre direzioni: **data augmentation**, **attacchi** e **deepfake**. La slide cita le **DeepMasterPrints** (impronte sintetiche capaci di corrispondere a molte impronte reali) e avverte che i **deepfake biometrici sono già disponibili**; come difendersi è il tema delle Lezioni 17 e 19–20.

- **È reale o creato da un'AI?** (p. 55): il sito *thispersondoesnotexist.com* mostra volti **inventati** da una GAN, **StyleGAN2** (Karras et al., NVIDIA, dicembre 2019). Risposta: **è falso**.
- **Esempi di volti falsi** (p. 56).
- **La «Gestalt» umana è ancora mancante** (p. 57): le immagini generate possono contenere incoerenze complessive che un osservatore umano coglie.
- **Nuovi strumenti arrivano continuamente** (p. 58), per esempio generatori di immagini integrati in Grok o Canva; se ne riparla nelle lezioni sui deepfake.
- Altri esempi di applicazioni con reti tradizionali e deep saranno presentati più avanti (p. 59); si consiglia la lettura del **survey** allegato alla Lezione 7 (p. 60).

> 💡 **Perché le DeepMasterPrints funzionano.** Molti sensori di smartphone acquisiscono solo una **porzione** del dito e salvano più template parziali. Un'impronta sintetica ottimizzata per contenere caratteristiche comuni a molte impronte può superare la soglia per una frazione non trascurabile di utenti: è un attacco di tipo «dizionario» contro l'FMR.

<!-- SOURCE_VISUAL id="TAB08-V22" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="54" type="immagini" description="GAN in biometria: DeepMasterPrints, data augmentation, attacchi, deepfake" reason="Esempi visivi delle applicazioni delle GAN" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V23" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="56" type="fotografie" description="Esempi di volti falsi generati da GAN" reason="La slide contiene solo immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V24" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="57" type="immagini" description="Esempi in cui manca la Gestalt umana nelle immagini generate" reason="Le incoerenze si vedono solo sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Metodi avanzati di progettazione: biometria adattativa**

### **6. Outline della Parte B**

_(slide pp. 61–62)_

La Parte B introduce i metodi avanzati per progettare e migliorare i sistemi biometrici:

1. **biometria adattativa**: **template update** (re-enrollment) e **template selection**;
2. **fusione delle informazioni** e tecniche avanzate di progetto: multimodalità e multibiometria, **soglie personalizzate**, fusione neurale con dati sulla qualità del tratto, **sistemi gerarchici**, deep learning per estrarre feature;
3. **multimodalità biometrica** ed esempi di sistemi multimodali.

In questa lezione le slide sviluppano il **punto 1**; la fusione e la multimodalità sono il tema della Lezione 15.

---

### **7. Template selection e template update**

_(slide pp. 63–73)_

#### **7.1 Due problemi diversi**

_(slide pp. 63–64)_

| | **Template selection** | **Template update** |
|---|---|---|
| Quando | durante la **registrazione** (enrollment) | durante l'**autenticazione**, nel tempo |
| Obiettivo | creare una **galleria rappresentativa** della popolazione di utenti, con **il minor numero possibile** di template | **adattare i template nel tempo**, per catturare variazioni del tratto **non presenti** al momento della registrazione |

Selezione e aggiornamento sono problemi diversi che condividono la nozione comune di **«migliori template rappresentativi»**.

#### **7.2 Esempi di selezione e aggiornamento**

_(slide pp. 65–67)_

- **Template selection con qualità** (p. 65): con un **frame rate elevatissimo** e un **indice di qualità per ogni frame**, si scartano i frame **KO** e si tengono gli **OK**. Vale **sia in enrollment sia in verifica**.
- **Update del volto 3D** (p. 66): le **viste 2,5D** ottenute da **due frame vicini** servono a **integrare il modello 3D** del volto, anche in momenti separati.
- **Update singolo dell'impronta** (p. 67): se si ottiene un **buon match**, si può **aggiungere una regione nuova** dell'impronta al template registrato (per esempio una porzione di dito non acquisita all'enrollment).

<!-- SOURCE_VISUAL id="TAB08-V25" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="65" type="sequenza di frame" description="Template selection: frame ad alto frame rate con indice di qualità KO o OK" reason="Mostra quali frame vengono scartati" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V26" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="66" type="immagini 3D" description="Update del volto 3D tramite viste 2,5D ottenute da due frame vicini" reason="Mostra l'integrazione del modello 3D" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V27" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="67" type="immagini di impronte" description="Update singolo: nuova regione dell'impronta aggiunta al template enrollato dopo un buon match" reason="Mostra la regione aggiunta" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 Stato dell'arte: modalità**

_(slide pp. 68–69)_

| Modalità | Descrizione (slide) |
|---|---|
| **Supervisionata** | richiede l'**intervento umano** per etichettare i dati |
| **Semi-supervisionata** | si usano le **query etichettate dal sistema** stesso |
| **Offline** | un insieme di dati semi-etichettati viene **memorizzato durante l'autenticazione** e usato per aggiornare i template **quando il sistema non è operativo** |
| **Online** | ogni query viene valutata durante l'autenticazione e l'adattamento avviene **subito** |

La slide p. 69 mostra un esempio di **template selection manuale**.

<!-- SOURCE_VISUAL id="TAB08-V28" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="69" type="immagini" description="Esempio di template selection manuale" reason="La slide contiene solo l'esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4 Aggiornamento automatico nel tempo**

_(slide pp. 70–72)_

**Algoritmo** (p. 70): se il campione nuovo ottiene un **buon match** con la galleria, lo si **aggiunge** come nuova foto enrollata. Stabilire la **soglia $t^*$** per l'aggiunta è **molto, molto critico**.

**La catena** (p. 71): **a piccoli passi** si possono **agganciare immagini come in una catena**, arrivando ad avere agli estremi immagini **molto diverse**, pur restando sicuri che si tratta della **stessa persona**. Collegare direttamente le due immagini estreme **sarebbe stato un salto troppo grande** per un singolo passo.

**Il grafo di similitudini** (p. 72): il processo si può immaginare come un **grafo di vicinanze e similitudini** che si **arricchisce nel tempo**. Il metodo permette di resistere meglio a diverse **pose**, **espressioni** e **condizioni di luce** (la slide evidenzia immagini sovrasature, con espressione o posa diverse).

> ⚠️ **Il rischio della soglia $t^*$ (nota didattica, non presente nelle slide).** Se $t^*$ è troppo **permissiva**, un **impostore** accettato per errore viene **aggiunto alla galleria**; da quel momento il suo template attira altri match e la catena **deriva** verso un'altra persona (*template poisoning*). Se è troppo **severa**, non si aggiunge quasi nulla e l'adattamento non avviene. Per questo $t^*$ di aggiornamento è in genere **più severa** della soglia di accesso.

<!-- SOURCE_VISUAL id="TAB08-V29" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="70" type="schema temporale" description="Template update automatico: volti nel DB per istanti di tempo e relazione di update con soglia t*" reason="Mostra l'aggiunta progressiva dei template" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V30" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="71" type="catena di immagini" description="Catena di piccoli passi che collega immagini molto diverse della stessa persona" reason="Visualizza il concetto di catena" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V31" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="72" type="grafo" description="Grafo di vicinanze e similitudini con immagini sovrasature, con espressione e posa diverse" reason="Rappresentazione del grafo che si arricchisce" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.5 Cancellazione dei template**

_(slide p. 73)_

Un meccanismo automatico di re-enrollment che inserisce nuovi template tende a **saturare** il numero di template: serve una **gestione della scalabilità** (esempi: una smart city, un grande dipartimento). Occorre un meccanismo per **dimenticare** i template non più aggiornati, controllando tra i genuini quali template hanno:

- **minore distanza spaziale**, cioè alta similitudine secondo la metrica del sistema: sono **troppo simili** e ridondanti;
- **maggiore distanza temporale**: sono **troppo vecchi**.

La scelta dipende dall'applicazione:

| Applicazione | Tenere i template vecchi? |
|---|---|
| identificare **bambini scomparsi** o **latitanti** | ha **meno senso** eliminarli: le foto vecchie possono essere le uniche disponibili |
| re-identificare un **pendolare** o un **dipendente** | le foto di **10 anni prima non servono** |

L'ottimizzazione si può eseguire **in background** nel sistema.

---

### **8. Pagine finali**

_(slide pp. 74–77)_

Le slide 74, 76 e 77 non contengono testo. La slide 75 riporta una richiesta del docente: **segnalare le cose da cambiare**, ma anche le **buone prassi**, per evitare che vengano modificate parti che funzionano correttamente.

<!-- SOURCE_VISUAL id="TAB08-V32" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="74" type="slide solo immagine" description="Slide senza testo dopo la cancellazione dei template; contenuto non ricavabile dal livello testo" reason="Verificare il contenuto dell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V33" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="76" type="slide solo immagine" description="Slide finale senza testo; contenuto non ricavabile dal livello testo" reason="Verificare il contenuto dell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB08-V34" source="Lezione_08_CNN_per_Identificazione_Riconoscimento__Template_Update.pdf" page="77" type="slide solo immagine" description="Slide finale senza testo; contenuto non ricavabile dal livello testo" reason="Verificare il contenuto dell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **GPU** / **NPU** / **VPU** | training e inference ad alto parallelismo / inference a basso consumo on-device / pipeline visive embedded |
| **Classificatore di identità** / **Estrattore di feature** | memorizza gli utenti nei pesi, va riallenato / produce template confrontabili, utenti aggiunti senza training |
| **Softmax** / **ArcFace** | separa solo le classi note / margine angolare, generalizza a nuove identità |
| **Distanza euclidea** / **coseno** | norma e direzione / solo direzione (coerente con ArcFace) |
| **Backbone** / **Testa fully connected** | estrae rappresentazioni generali / specializza il compito |
| **DeepFace** / **FaceNet** | allineamento 3D, vettore 4096, 97,35% / solo crop, triplet loss, 99,63% |
| **Template selection** / **Template update** | galleria minima e rappresentativa in enrollment / adattamento nel tempo durante l'uso |
| **Update offline** / **online** | dati accumulati e usati a sistema fermo / adattamento immediato a ogni query |
| **Catastrophic forgetting** / **Template update** | la rete dimentica utenti vecchi quando si riallena / la galleria cresce senza riallenare |

---

### **10. Sintesi della lezione**

> ✅ **In sintesi.**
> - **Hardware**: CPU per prototipi, GPU per il training, TPU in cloud, NPU e VPU per la biometria embedded, ASIC/SoC per l'on-sensor; acceleratori economici per Raspberry Pi (Movidius, AI HAT+).
> - **Software**: Model Zoo (Hugging Face, Torchvision), reti famose (LeNet-5, AlexNet, VGG16) e **ResNet** con skip connection come backbone, embedding a 512 dimensioni; approccio **divide et impera**.
> - **Identificazione**: evitare il classificatore che memorizza gli utenti (no L(N)PO, riallenamento, catastrophic forgetting); allenare un **estrattore di feature** su dati pubblici e identificare con **N autenticazioni**.
> - **Loss biometriche**: softmax contro **ArcFace** con margine angolare; in deployment **distanza coseno**.
> - **Esempi**: VGGFace + PCA, spettrogrammi per la voce, age estimation, **DeepFace** (152×152, allineamento 3D, 4096 feature, 97,35%) e **FaceNet** (triplet loss, 99,63%); **GAN** e volti sintetici.
> - **Biometria adattativa**: template **selection** e **update** (supervisionato, semi-supervisionato, offline, online), catena e grafo di similitudini, soglia $t^*$ critica, **cancellazione** dei template troppo simili o troppo vecchi.
