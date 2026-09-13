## **Lezione 23: Sistemi di visione intelligenti, rilevamento, segmentazione e hardware per il ML**

La ventitreesima lezione del corso **IS4** porta il deep learning nelle **applicazioni di visione**. Distingue i quattro compiti fondamentali:

- **classificazione** dell’immagine;
- **rilevamento** degli oggetti (*object detection*);
- **segmentazione semantica**;
- **segmentazione per istanze**.

Per ciascuno spiega **che cosa restituisce** e **che cosa permette di misurare**. Seguono le soluzioni **classiche, profonde e ibride**, i modelli **SAM** e **SAM 2**, le reti **completamente convolutive** con upsampling e un **caso d’uso con i droni**. La seconda parte è dedicata all’**hardware per il ML** (CPU, GPU, TPU, NPU, VPU), all’**edge** e agli strumenti di ottimizzazione come **OpenVINO** ed **Edge Impulse**.

Fonte: [PDF della Lezione 23](Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine%20Learning_Hardware.pdf), 95 pagine, senza supplementi. Diverse immagini sono tratte dal corso di Fei-Fei Li, Justin Johnson e Serena Yeung (Stanford). Le note didattiche aggiunte e i frammenti di codice ricostruiti sono segnalati. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–3)_

Titolo originale: *Image classification, Intelligent vision System, Object detection, Segmentation and Recognition, Machine Learning Hardware*. L’indice (p. 2) elenca:

1. **classificazione delle immagini** e **sistemi di visione intelligenti**;
2. **rilevamento**, **segmentazione**, **riconoscimento** di oggetti: soluzioni **classiche**, **profonde** e **ibride**; **casi d’uso**;
3. **hardware per il machine learning**: GPU contro CPU, TPU, strumenti di ottimizzazione (l’esempio di **OpenVINO**), piattaforma generica basata su Python (**Edge Impulse**).

La p. 3 consiglia il breve **ebook di MATLAB sul deep learning**, che riassume molti concetti della lezione con esempi di codice. **Non è materia d’esame.**

---

### **1. Classificazione e compiti della visione intelligente**

_(slide pp. 4–18)_

#### **1.1. I quattro compiti**

_(slide pp. 4–13)_

La p. 4 riprende la **classificazione** (cane o gatto); la p. 5 presenta i **compiti della visione intelligente**. Le pp. 6–13 sono quattro **quiz** sulla stessa scena di pecore con un cane: «che compito di visione intelligente è questo?».

| Quiz | Risposta | Che cosa si vede nell’uscita |
|---|---|---|
| **1** (pp. 6–7) | **segmentazione per istanze** (*instance segmentation*) | ogni pecora ha la **sua** maschera, distinta dalle altre |
| **2** (pp. 8–9) | **segmentazione semantica** | tutti i pixel delle pecore hanno **la stessa classe** («tutte pecore»), e c’è un’area «cane» |
| **3** (pp. 10–11) | **rilevamento di oggetti** | solo i **bounding box** delle pecore |
| **4** (pp. 12–13) | **classificazione dell’immagine** | **una sola classe** in uscita: «pecora» |

<!-- SOURCE_VISUAL id="IS23-V01" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="5" type="schema" description="I compiti della visione intelligente: classificazione, rilevamento, segmentazione semantica e per istanze" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V02" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="7" type="immagine" description="Quiz 1: segmentazione per istanze delle pecore" reason="Maschere visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V03" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="9" type="immagine" description="Quiz 2: segmentazione semantica con area pecore e area cane" reason="Maschere visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V04" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="11" type="immagine" description="Quiz 3: rilevamento delle pecore con soli bounding box" reason="Box visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V05" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="13" type="immagine" description="Quiz 4: classificazione con una sola classe in uscita" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Quanta informazione restituisce ciascun compito (nota didattica).** Per un’immagine $H \times W$ con $C$ classi:
> - la **classificazione** restituisce **un’etichetta** (o $C$ punteggi);
> - il **rilevamento** restituisce, per ciascuno degli oggetti trovati, **4 coordinate + classe + punteggio**;
> - la **segmentazione semantica** restituisce **un’etichetta per pixel**, cioè $H \times W$ etichette;
> - la **segmentazione per istanze** restituisce una **maschera per oggetto**, e quindi distingue due pecore che si toccano, cosa che la semantica non fa.
>
> Il costo di etichettatura dei dati di addestramento cresce nello stesso ordine: un’etichetta per immagine, pochi clic per un box, un contorno preciso per ogni oggetto.

#### **1.2. Classificazione diretta e rilevamento**

_(slide pp. 14–18)_

> 📌 **Classificazione diretta** (pp. 14, 16). Un **classificatore di immagini** restituisce solo «**cane**»: **nessun’altra informazione** sull’oggetto (dove si trova, quanto è grande, quanti ce ne sono).
>
> **Rilevamento di oggetti** (p. 15). Un **rilevatore** restituisce il **bounding box** $\{x_1, y_1, x_2, y_2\}$, cioè gli angoli $(x_1, y_1)$ in alto a sinistra e $(x_2, y_2)$ in basso a destra. I lati del box sono **paralleli** agli assi dell’immagine: con un box così è **quasi impossibile ottenere misure** accurate dell’oggetto.

<!-- SOURCE_VISUAL id="IS23-V06" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="14" type="schema" description="Classificatore di immagini che restituisce solo l’etichetta cane" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V07" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="15" type="schema" description="Rilevatore che restituisce il bounding box con gli angoli (x1, y1) e (x2, y2)" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Esempio con AlexNet** (p. 17). L’immagine viene **ridimensionata** a $256 \times 256 \times 3$ e passa per **5 strati convolutivi e 3 completamente connessi**; l’ultimo strato è una **softmax** che produce «cane» (Lezione 21, §6.1).

> 📌 **ImageNet: set facile e set difficile** (p. 18). La *ImageNet Large-Scale Visual Recognition Challenge* (oltre 14 milioni di immagini, 1000 classi) mostra esempi **facili** e **difficili**.
> - È **ottima per Google**, **non per un’applicazione industriale**: le classi e le condizioni di ripresa non sono quelle di una linea di produzione.
> - È però **ottima per addestrare** reti che **estraggono feature** (Lezione 22, §5).
> - È buona per **addestramento e fine-tuning**, **dopo una selezione** delle immagini utili.

---

### **2. Rilevamento di oggetti**

_(slide pp. 19–29)_

#### **2.1. Rilevamento, segmentazione, scene complesse**

_(slide pp. 19–24)_

La p. 20 riprende la definizione del rilevamento con il bounding box $\{x_1, y_1, x_2, y_2\}$ e il suo limite sulle misure.

> ⚠️ **Il rilevamento non è segmentazione!** (p. 21). Il confronto tra **rilevamento del volto** e **segmentazione del volto**, e tra **rilevamento** e **segmentazione di un oggetto**, mostra che solo la segmentazione fornisce **misure reali** degli oggetti: il contorno esatto, non un rettangolo che lo contiene.

<!-- SOURCE_VISUAL id="IS23-V08" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="21" type="immagini" description="Rilevamento contro segmentazione per volti e oggetti" reason="Confronto visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Rilevamento di più oggetti** (p. 22): **non è un compito facile** nelle scene complesse, con **oggetti sovrapposti**.

**Esempio: rilevamento di persone** (p. 23) nei **siti industriali** e nella **videosorveglianza**.

<!-- SOURCE_VISUAL id="IS23-V09" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="22" type="immagine" description="Rilevamento di più oggetti sovrapposti in una scena complessa" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V10" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="23" type="immagini" description="Rilevamento di persone in siti industriali e videosorveglianza" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Oggetti sovrapposti e box duplicati (nota didattica).** Un rilevatore produce di solito **molti box candidati** attorno allo stesso oggetto. La **Non-Maximum Suppression** li riduce:
> 1. si ordina per punteggio;
> 2. si tiene il box migliore;
> 3. si scartano quelli che hanno con esso un’IoU (Lezione 21, §6.3) sopra una soglia, per esempio 0,5;
> 4. si ripete con i box rimasti.
>
> Con oggetti **davvero sovrapposti**, come due persone una davanti all’altra, i loro box hanno IoU alta e la NMS rischia di **eliminarne uno**: è una delle ragioni per cui la slide parla di scene complesse.

**Stima della posa del corpo articolato: OpenPose** (p. 24). Un sistema **open source** e **in tempo reale** per la stima **2D della posa di più persone**, che trova i punti chiave (*keypoint*) del **corpo**, dei **piedi**, di **polsi e mani** e del **volto**.

#### **2.2. Soluzioni classiche, profonde e ibride**

_(slide pp. 25–26)_

La p. 25 confronta la **classificazione/rilevamento** di oggetti con il **ML classico** e con il **deep learning**.

<!-- SOURCE_VISUAL id="IS23-V11" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="25" type="schema" description="Classificazione e rilevamento: ML classico contro deep learning" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Una terza via: le soluzioni ibride** (p. 26). Si parte da un’**estrazione di feature classica**:
> - **rilevatori di blob**;
> - segmentazione;
> - elaborazione a blocchi.
>
> I **rilevatori di blob** trovano i punti in cui l’immagine **risponde a una specifica convoluzione con un kernel** (le lezioni sulla similarità, Lezione 10, §2.3). Le regioni candidate trovate così vengono poi classificate con un modello appreso. Il caso d’uso del §5 è costruito proprio in questo modo.

<!-- SOURCE_VISUAL id="IS23-V12" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="26" type="schema" description="Soluzione ibrida con estrazione classica di blob e classificatore appreso" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. Misurare gli oggetti**

_(slide pp. 27–29)_

> 📌 **Misurare un oggetto: un processo in tre passi** (p. 27).
> 1. **Rilevamento** dell’oggetto → **bounding box**.
> 2. **Segmentazione** dell’oggetto.
> 3. **Misure** basate sulla **segmentazione**.

<!-- SOURCE_VISUAL id="IS23-V13" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="27" type="schema" description="Misura in tre passi: rilevamento, segmentazione, misure" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Misure nelle applicazioni industriali** (p. 28). Con il **pattern matching** tradizionale si ottengono **rilevamento e orientazione**: il risultato è un **bounding box ruotato** $\{x_1, y_1, x_2, y_2, x_3, y_3, x_4, y_4\}$, con quattro angoli.

> 💡 **Box ruotato e misure in millimetri (nota didattica).** Un rettangolo ruotato è definito da **5 numeri**: centro $(c_x, c_y)$, larghezza $w$, altezza $h$ e angolo $\theta$. Gli 8 valori della slide sono i 4 angoli, ridondanti ma comodi da disegnare. Per passare dai **pixel** ai **millimetri** serve una **calibrazione**: se il campo inquadrato è largo 200 mm e il sensore ha 2000 pixel in orizzontale, ogni pixel vale
> $$\frac{200\ \text{mm}}{2000\ \text{px}} = 0{,}1\ \text{mm/px}$$
> e un pezzo lungo 350 pixel misura 35 mm. L’errore di segmentazione di un solo pixel sul bordo diventa quindi un errore di 0,1 mm sulla misura: è per questo che servono la segmentazione e non il box.

<!-- SOURCE_VISUAL id="IS23-V14" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="28" type="immagini" description="Pattern matching industriale con bounding box ruotato a quattro angoli" reason="Esempio visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Segmentazione e misura: il bin picking** (p. 29). Un sistema di visione intelligente calcola la **posizione esatta di presa** di pezzi alla rinfusa in un contenitore. L’ambiente industriale ha **peculiarità non banali**:

- serve un sistema **2D + 3D**;
- la segmentazione **2D** è **quasi impossibile** per la **complessità dello sfondo** (pezzi uguali ammucchiati);
- le **misure 2D** possono servire comunque per la **valutazione della qualità** e per la presa.

<!-- SOURCE_VISUAL id="IS23-V15" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="29" type="immagini" description="Bin picking con sistema di visione 2D e 3D" reason="Esempio visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Segmentazione degli oggetti**

_(slide pp. 30–47)_

#### **3.1. Applicazioni**

_(slide pp. 30–35)_

**Segmentazioni generali** (p. 31): dai **dati 3D** si possono segmentare i **volumi** degli oggetti.

<!-- SOURCE_VISUAL id="IS23-V16" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="31" type="immagine" description="Segmentazione di volumi di oggetti da dati 3D" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| Applicazione (slide) | Che cosa si segmenta e si misura |
|---|---|
| **Droni in agricoltura** (p. 32) | un focolaio di **diabrotica del mais** (*corn rootworm*): immagini e video aiutano l’agricoltore o il fornitore di servizi a capire **dove** si concentra il danno; integrazione con i **GIS** |
| **Satelliti e riprese aeree** (p. 33) | sistemi di visione intelligenti **multibanda** per segmentazione e misure |
| **Veicoli autonomi** (p. 34) | **segmentazione semantica**: pedoni → area grande → sono vicini → **frenare** |

<!-- SOURCE_VISUAL id="IS23-V17" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="32" type="immagini" description="Drone che individua un focolaio di diabrotica del mais" reason="Esempio visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V18" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="33" type="immagini" description="Segmentazione multibanda da satellite e riprese aeree" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V19" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="34" type="immagine" description="Segmentazione semantica per un veicolo autonomo con pedoni vicini" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché «area grande» vuol dire «vicino» (nota didattica).** Con una telecamera a lunghezza focale fissa, un oggetto di altezza reale $H$ a distanza $d$ occupa sull’immagine un’altezza proporzionale a $H/d$. Una persona alta 1,75 m che occupa 350 pixel, se si avvicina a metà distanza, ne occupa circa 700, e l’**area** della sua maschera quadruplica. La segmentazione semantica fornisce questa area pixel per pixel, cosa che una semplice classificazione non può fare.

**Altre applicazioni della segmentazione** (p. 35):

- **imaging medico**: delineare tumori, organi o vasi sanguigni per la diagnosi o la pianificazione chirurgica;
- **riconoscimento di oggetti**: separando l’immagine nelle sue parti si riconoscono e classificano meglio gli oggetti;
- **riconoscimento del volto**: isolare il volto dallo sfondo migliora l’accuratezza;
- **videosorveglianza**: seguire oggetti o persone nei fotogrammi successivi;
- **analisi di documenti**: separare il testo dallo sfondo nelle scansioni o nell’OCR;
- **biometria**: isolare le feature rilevanti nel riconoscimento dell’impronta o dell’iride;
- **realtà aumentata**: separare in tempo reale gli oggetti in primo piano per sovrapporre contenuti digitali;
- **computer grafica**: editing di immagini e video, per esempio per cambiare lo sfondo;
- **monitoraggio della fauna**: identificare e seguire animali per ricerca e conservazione;
- **ispezione industriale**: trovare i difetti nei prodotti durante il controllo qualità;
- **moda**: prova virtuale dei capi, segmentati e sovrapposti all’immagine dell’utente;
- **robotica**: riconoscere gli oggetti dell’ambiente e interagire con essi.

#### **3.2. Definizione e metodi classici**

_(slide pp. 36–40)_

> 📌 **Segmentazione: la «definizione»** (p. 36).
> - **Dividere** o **separare** un’immagine in **regioni**.
> - Serve a facilitare il **riconoscimento**, la **comprensione** e l’elaborazione delle **regioni di interesse** (ROI).
> - È un **problema mal posto**: la **definizione di regione dipende dal contesto**.

> 💡 **Mal posto in pratica (nota didattica).** In una foto di una facciata, la «regione» giusta può essere l’intero edificio (per una mappa urbana), ogni finestra (per un conteggio) o ogni mattone (per un’ispezione dei danni). Nessun algoritmo può scegliere senza sapere **a che cosa serve** la segmentazione: è lo stesso principio della Lezione 08 sulle soglie di similarità che dipendono dall’applicazione.

**Due compiti vicini** (p. 37): **segmentazione** ed **estrazione delle feature**. Nell’esempio di una tessitura (*texture*), per separare le regioni bisogna prima calcolare feature che le distinguano.

**Tassonomia di base** (p. 38). I metodi di segmentazione **tradizionali** si dividono in:

- metodi a **conoscenza globale**, che usano per esempio l’**istogramma** dell’immagine;
- segmentazioni **basate sui bordi** (*edge-based*);
- segmentazioni **basate sulle regioni** (*region-based*), su **luminosità**, **tessitura**, **campo di velocità**, **colore**, …

<!-- SOURCE_VISUAL id="IS23-V20" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="38" type="schema" description="Tassonomia dei metodi tradizionali di segmentazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Metodi classici e altri** (p. 39), come riferimento in caso di necessità:

| Metodi classici | Altri metodi |
|---|---|
| **conoscenza globale**: soglia, metodi basati sull’istogramma, metodi basati sulla compressione | metodi **split-and-merge** |
| **rilevamento dei bordi** | **clustering** (colore, intensità, tessitura, a volte coordinate spaziali): **k-means**, **Gaussian Mixture Model**, **Mean Shift** |
| **accrescimento di regioni** (*region growing*): modelli a contorno attivo («**snake**»), **trasformata watershed** | metodi basati su **equazioni alle derivate parziali** |
| segmentazione **basata su modelli**: modelli deformabili (snake) | metodi di **partizione di grafi** |
| | segmentazione **multi-scala**: segmentazione gerarchica di segnali monodimensionali, *primal sketch* |
| | segmentazione **semi-automatica** |
| | segmentazione con **reti neurali**: deep learning (slide successive) |

La slide mostra anche un esempio di metodo a contorno attivo (snake).

> 💡 **La soglia di Otsu (nota didattica).** Il più semplice metodo a «conoscenza globale» sceglie una soglia $t$ sull’istogramma dei livelli di grigio e divide i pixel in due classi. Il metodo di Otsu sceglie la $t$ che **massimizza la varianza tra le classi**:
> $$\sigma_B^2(t) = \omega_0(t)\, \omega_1(t) \left[\mu_0(t) - \mu_1(t)\right]^2$$
> dove $\omega_0, \omega_1$ sono le frazioni di pixel sotto e sopra la soglia e $\mu_0, \mu_1$ le loro medie. Funziona bene quando l’istogramma ha **due picchi** separati (pezzo scuro su nastro chiaro), male con illuminazione non uniforme.

**Segmentazione con il clustering k-means** (p. 40, non supervisionata). Il k-means (Lezione 19) può segmentare le immagini **raggruppando i pixel per similarità di colore**, ottenendo **regioni distinte per colore**. È **molto semplice**, ma può produrre **sovrasegmentazione** o **sottosegmentazione**.

> 💡 **Il k-means sui pixel (nota didattica).** Ogni pixel diventa un punto nello spazio $(R, G, B)$, e i $k$ centroidi sono i $k$ «colori rappresentativi». Un’immagine $640 \times 480$ produce 307 200 punti. Scegliere $k$ troppo alto divide un oggetto in sfumature diverse (**sovrasegmentazione**); troppo basso fonde oggetti di colore simile (**sottosegmentazione**). Aggiungere le coordinate $(x, y)$ come feature, opportunamente scalate (Lezione 10, §5), rende le regioni più compatte.

<!-- SOURCE_VISUAL id="IS23-V21" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="40" type="immagini" description="Segmentazione con k-means per similarità di colore" reason="Risultato visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. Segment Anything: SAM e SAM 2**

_(slide pp. 41–47)_

> 📌 **Segment Anything Model (SAM)** (p. 41), un metodo di deep learning di **Meta AI**.
> - È un sistema di segmentazione **guidato da prompt** (*promptable*).
> - Ha **generalizzazione zero-shot** a oggetti e immagini sconosciuti, **senza addestramento aggiuntivo**; è stato addestrato su **11 milioni di immagini** e **oltre un miliardo di maschere**.
> - SAM ha imparato una **nozione generale di che cosa sia un oggetto**, ed è questa comprensione che permette la generalizzazione zero-shot.
> - Genera la segmentazione di un fotogramma in **circa 50 ms** su un PC.

**Segmentazione guidata da prompt** (p. 42). I ricercatori di Meta AI hanno addestrato SAM a restituire una **maschera valida per qualsiasi prompt**: punti di **primo piano o sfondo**, un **box** approssimativo o una **maschera**, **testo libero** o, in generale, qualsiasi informazione che indichi che cosa segmentare. Nell’esempio della slide il prompt è un **bounding box**.

La p. 43 mostra la divisione tra **lato server** e **lato client** (per esempio un cellulare).

<!-- SOURCE_VISUAL id="IS23-V22" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="43" type="diagramma" description="Architettura di SAM divisa tra lato server e lato client" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché server e client (nota didattica).** SAM ha tre parti:
> - un **encoder d’immagine** pesante (un vision transformer), che si esegue **una volta per immagine**;
> - un **encoder del prompt** leggero;
> - un **decoder delle maschere** leggero.
>
> L’embedding dell’immagine si calcola sul **server**, mentre prompt e decoder girano sul **client**. Ogni nuovo clic dell’utente costa così solo pochi millisecondi: i circa 50 ms della slide si riferiscono a questa parte interattiva.

Le pp. 44–45 mostrano **SAM in Colab** con la libreria **metaseg**.

<!-- SOURCE_VISUAL id="IS23-V23" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="44" type="schermata" description="SAM in Colab con la libreria metaseg: codice" reason="Codice presente solo come schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V24" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="45" type="schermata" description="SAM in Colab con la libreria metaseg: maschere risultanti" reason="Risultato presente solo come schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **SAM 2 (video e immagini)** (pp. 46–47). È un **miglioramento significativo** rispetto al modello originale: il **primo modello unificato** per la segmentazione **in tempo reale** di **immagini e video**.
> - **Velocità**: circa **44 fotogrammi al secondo**, adatta ad applicazioni in tempo reale.
> - **Generalizzazione zero-shot**: segmenta oggetti mai visti in addestramento senza personalizzazione.
> - **Memoria video**: un meccanismo di **memoria** segue gli oggetti **da un fotogramma all’altro**, gestendo **occlusioni** e movimenti complessi.
> - Sulle immagini è **6 volte più veloce** e più accurato del SAM originale, e aggiunge la segmentazione video che SAM non aveva.
> - È **open source** (licenza **Apache 2.0**).

> ⚠️ **Numero di parametri (nota didattica).** La slide indica «100–300M parametri». Le versioni pubblicate di SAM 2 vanno invece da circa **39 milioni** (tiny) a circa **224 milioni** (large): la dimensione dipende dalla variante scelta, ed è proprio questa scelta a determinare se il modello gira in tempo reale su un dispositivo edge.

<!-- SOURCE_VISUAL id="IS23-V25" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="46" type="immagini" description="SAM 2 che segmenta e segue oggetti in un video" reason="Esempio visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Segmentazione semantica con il deep learning**

_(slide pp. 48–57)_

La p. 49 richiama la **segmentazione semantica**.

<!-- SOURCE_VISUAL id="IS23-V26" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="49" type="immagine" description="Richiamo della segmentazione semantica" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Classificare ogni pixel: buona idea, ma non praticabile così** (p. 50). Immaginiamo di classificare **ogni singolo pixel** dell’immagine d’uscita con una **softmax**, usando la stessa rete di classificazione su una finestra centrata su quel pixel. L’idea è corretta, ma il costo è enorme.

> 💡 **Quanto costa (nota didattica).** Un’immagine $224 \times 224$ ha 50 176 pixel: con una finestra per pixel servono 50 176 passaggi in avanti della rete per **una sola immagine**, e finestre vicine ripetono quasi gli stessi calcoli. Una rete **completamente convolutiva** risolve il problema con **un solo passaggio**, condividendo i calcoli tra tutti i pixel.

> 📌 **Una soluzione completamente convolutiva** (p. 51). La **Fully Convolutional Network** (FCN) con **downsampling e upsampling** ha una struttura **encoder/decoder**:
> - l’**encoder** riduce la risoluzione ed estrae feature sempre più astratte;
> - il **decoder** riporta le mappe alla **risoluzione dell’immagine** con operazioni di upsampling («**deconvoluzioni**»);
> - l’uscita ha un **vettore di punteggi di classe per ogni pixel**.

<!-- SOURCE_VISUAL id="IS23-V27" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="50" type="diagramma" description="Classificazione di ogni pixel con una softmax: idea non praticabile" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V28" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="51" type="diagramma" description="Rete completamente convolutiva con downsampling e upsampling" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Upscaling** (p. 52): in PyTorch `nn.ConvTranspose2d()`.

- Si usa nelle **sezioni decoder** delle reti profonde, come gli **autoencoder** (Lezione 22, §1) o la **U-Net**, per **aumentare la risoluzione**.
- **Aumenta la risoluzione spaziale** delle feature map **imparando** come ricostruire uscite ad alta risoluzione da ingressi a bassa risoluzione.

> 💡 **Dimensione dell’uscita della convoluzione trasposta (nota didattica).** Con ingresso $N$, kernel $K$, stride $S$ e padding $P$:
> $$N_{\text{out}} = (N - 1)\,S - 2P + K$$
> È la formula della convoluzione (Lezione 21, §2.2) «letta al contrario». Con $N = 7$, $K = 2$, $S = 2$, $P = 0$: $(7 - 1) \cdot 2 + 2 = 14$, la risoluzione raddoppia.
> ```python
> import torch
> from torch import nn
>
> up = nn.ConvTranspose2d(in_channels=2048, out_channels=256, kernel_size=2, stride=2)
> x = torch.randn(1, 2048, 7, 7)     # mappe finali di ResNet-50 per un'immagine 224x224
> print(up(x).shape)                 # torch.Size([1, 256, 14, 14])
> ```
> (Frammento ricostruito, non trascritto dalle slide.)

**ResNet-50 per la classificazione** (p. 53). La rete nativa è progettata per la **classificazione**: l’uscita è un **intero** (l’indice della classe). La versione pre-addestrata classifica le immagini in **1000 categorie** di oggetti (tastiera, mouse, matita, molti animali). In Keras ha circa **25 milioni di parametri**.

> ⚠️ La slide scrive `model.param_count()`; in Keras il metodo è `model.count_params()`, che per ResNet-50 con lo strato finale restituisce 25 636 712.

**ResNet-50 come estrattore di feature** (p. 54): si usa ResNet-50 per calcolare le feature e poi un **proprio classificatore** per un nuovo compito (Lezione 22, §5).

> 📌 **ResNet-50 + upscaling = segmentazione semantica addestrabile** (pp. 55–56).
> - Aggiungendo a ResNet-50 un **decoder di upsampling** si ottiene un’architettura di segmentazione semantica addestrabile.
> - La **convoluzione trasposta** è un’operazione di **upsampling appresa**; in generale **non** è l’inversa matematica esatta della convoluzione.
> - ResNet-50 con upscaling **per la classificazione? No!** Dopo aver aggiunto il decoder, la rete diventa un’**architettura di segmentazione**: produce un’**uscita simile a un’immagine**, non l’identificativo di una classe.

<!-- SOURCE_VISUAL id="IS23-V29" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="55" type="diagramma" description="ResNet-50 con decoder di upsampling per la segmentazione semantica" reason="Architettura presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Addestrare e valutare la segmentazione (nota didattica).**
> - La funzione di costo è la **cross-entropy calcolata pixel per pixel** e mediata su tutti i pixel.
> - La metrica tipica è l’**IoU per classe**, mediata sulle classi (**mIoU**): per ogni classe si confrontano i pixel predetti con quelli veri.
> - L’accuratezza per pixel inganna quando lo sfondo domina, per la stessa ragione dei dataset sbilanciati (Lezione 14, §2): un modello che dice sempre «sfondo» su un’immagine con il 95% di sfondo ha il 95% di accuratezza.
> - La **U-Net** aggiunge connessioni dirette (*skip connection*) tra encoder e decoder alla stessa risoluzione, per recuperare i **dettagli dei bordi** persi nel downsampling.

La p. 57 mostra esempi di **classificazione di immagini con il deep learning**.

<!-- SOURCE_VISUAL id="IS23-V30" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="57" type="immagini" description="Esempi di classificazione di immagini con il deep learning" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Caso d’uso: riconoscimento di oggetti da drone**

_(slide pp. 58–65)_

**Riconoscimento di oggetti da UAV** (p. 58). Un **UAV** (*Unmanned Aerial Vehicle*) è un velivolo senza pilota, un drone; la p. 59 ne mostra gli usi applicativi.

<!-- SOURCE_VISUAL id="IS23-V31" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="59" type="immagini" description="Droni negli usi applicativi" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Amazon Prime Air** (p. 60), secondo la slide:

- è in uso **dal 2022 negli USA**;
- consegna pacchi che pesano **meno di 5 libbre** (2,26 kg) in **meno di un’ora**;
- Amazon ha ricevuto dalla **Federal Aviation Administration** la certificazione di **vettore aereo Part 135**;
- il servizio è operativo in alcune città statunitensi, come **Lockeford** (California) e **College Station** (Texas);
- nel Regno Unito è partito nel **2025**.

> ⚠️ **Da leggere con cautela (nota didattica).** Le 5 libbre sono il **limite del pacco**, non il peso del drone. Le città servite sono cambiate più volte negli anni: sono un esempio del **tipo** di servizio, non un dato stabile.

> 📌 **Progettare un drone per il controllo del traffico in tempo reale** (pp. 61–64). Il flusso ha tre fasi: **segmentazione tradizionale** → **addestramento** → **rilascio** (*deployment*), con elementi come il **segnale di atterraggio**.
>
> **Passo 1: segmentazione** (p. 62). Una **segmentazione tradizionale** trova i **candidati**. Nell’esempio si usa la **segmentazione watershed** sul **livello di grigio di un canale**.
>
> **Passo 2: progetto della CNN** (p. 63). I candidati trovati dalla segmentazione vanno a un classificatore. Si può usare un classificatore **tradizionale** come una **SVM** oppure un modello **profondo**. La slide chiede se si riconosce la rete: è **VGG16** (Lezione 21, §6.1).
>
> **Addestramento della CNN** (p. 64). Si crea un **dataset** con due classi, **auto** e **non auto** (la slide ne mostra una parte). Poi si fa il **fine-tuning**: strati convolutivi **congelati**, rete completamente connessa **rifinita** (Lezione 22, §2).
>
> La p. 65 mostra i **risultati**.

<!-- SOURCE_VISUAL id="IS23-V32" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="61" type="schema" description="Drone per il controllo del traffico: segmentazione tradizionale, addestramento, rilascio" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V33" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="62" type="immagini" description="Segmentazione watershed dei candidati sul livello di grigio" reason="Risultato visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V34" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="64" type="immagini" description="Dataset auto e non auto e fine-tuning della CNN" reason="Esempi del dataset visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V35" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="65" type="immagini" description="Risultati del riconoscimento di auto da drone" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **La watershed e il perché dell’ibrido (nota didattica).**
> - La **watershed** tratta l’immagine in scala di grigi come un **rilievo**: i pixel scuri sono valli, quelli chiari creste. «Allagando» il rilievo a partire dai minimi, i bacini crescono, e dove due bacini si incontrano si costruisce una «diga»: sono i **confini delle regioni**. Da sola tende a **sovrasegmentare**, perché ogni piccolo minimo del rumore genera un bacino. Per questo si usa con **marcatori** o dopo un filtraggio.
> - **Perché l’ibrido.** Un fotogramma $1920 \times 1080$ esaminato a finestre scorrevoli di $64 \times 64$ pixel con passo 16 richiede circa
> $$\left(\frac{1920 - 64}{16} + 1\right) \times \left(\frac{1080 - 64}{16} + 1\right) = 117 \times 64 = 7488$$
> classificazioni con VGG16, per una sola scala. Se la segmentazione propone qualche decina di candidati, le chiamate alla CNN scendono di due ordini di grandezza. È ciò che rende possibile il **tempo reale** a bordo di un drone.
> - Il **segnale di atterraggio** citato a p. 61 è un altro esempio di compito facile per la visione classica: un marker noto si trova con il pattern matching, senza deep learning.

---

### **6. Hardware per il machine learning**

_(slide pp. 66–85)_

#### **6.1. Perché il ML è possibile e dove conta l’hardware**

_(slide pp. 66–69)_

**Perché oggi il machine learning è possibile?** (p. 67)

- **Memorie di massa**: **più dati disponibili** (dischi da terabyte, data center, …).
- **Prestazioni più alte dei computer**: **memorie più grandi** per gestire i dati e **maggiore potenza di calcolo** per addestrare e perfino per l’**apprendimento online**.
- Esempi: **NVIDIA RTX / A100 / H100 / Jetson / B200**; **Apple Neural Engine**; **NPU di Intel Core Ultra**; le **TPU** di Google. La slide mostra anche NVIDIA GTX Titan con InfiniBand e il sistema **NVIDIA HGX B200**.

**Hardware: quando e dove conta di più?** (p. 68). Il peso dell’hardware dipende soprattutto dal **rilascio** (*deployment*): dove girerà il modello.

<!-- SOURCE_VISUAL id="IS23-V36" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="68" type="schema" description="Hardware e rilascio: dove l’hardware conta di più" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2. CPU, GPU, TPU, NPU, VPU**

_(slide pp. 69–75)_

| Unità (pp. 69–72) | Che cos’è | Quando usarla |
|---|---|---|
| **CPU** (*Central Processing Unit*) | processore **generico**, **alta flessibilità**, **basso parallelismo** | adatta a **prototipi**, **pre-elaborazione**, **controllo del sistema**; **non** adatta all’addestramento di deep learning o all’inferenza complessa in tempo reale |
| **GPU** (*Graphics Processing Unit*) | **parallelismo massiccio**, alta **larghezza di banda della memoria** | scelta **standard per addestrare le CNN**; usata anche per l’inferenza a lotti o in tempo reale su desktop e server |
| **TPU** (*Tensor Processing Unit*) | acceleratore **specializzato** per le operazioni **tensoriali** | efficiente per addestramento e inferenza di **modelli standardizzati**; **flessibilità limitata**, uso tipicamente **in cloud** |
| **NPU** (*Neural Processing Unit*) | acceleratore per l’**inferenza neurale a bassa potenza** | ideale per sistemi **embedded** e **in tempo reale**; ottimizzata per **latenza**, **consumo energetico** e **privacy sul dispositivo** |
| **VPU** (*Vision Processing Unit*) | acceleratore **specializzato per l’elaborazione visiva** e la computer vision | ottimizzata per **pre-elaborazione**, **estrazione di feature** e **inferenza** su video e immagini; ideale per la biometria visiva embedded (volto, gesti, conteggio di persone); **compromesso tra GPU e NPU**, con buona efficienza e pipeline video dedicate |

Le slide pp. 70–72 esprimono diversi esempi in termini di **biometria** (CNN biometriche, inferenza biometrica): il ragionamento vale per qualsiasi modello di visione.

> 📌 **GPU contro CPU** (p. 73). In breve: **per i progetti grandi serve una GPU!**
> - **Prestazioni**: circa **5–30 volte più veloce**, con **10 volte** come media comune nell’industria.
> - **Efficienza energetica**: circa **3–10 volte più efficiente**, con **5 volte** come valore realistico comune.
>
> La slide usa l’esempio del **riaddestramento di AlexNet da zero** e aggiunge due avvertenze:
> - **prestazioni**: nei carichi di AI molto paralleli le GPU danno grandi accelerazioni, da poche volte a più di un ordine di grandezza. Il guadagno reale dipende da **architettura** del modello, **dimensione del batch**, **precisione** numerica, **banda della memoria** e **costo dei trasferimenti** di dati;
> - **efficienza energetica**: le GPU spesso **riducono l’energia** per modello addestrato o per batch di inferenza, perché **finiscono prima**. Però la **potenza assorbita** è più alta, e l’efficienza va misurata come **prestazioni per watt** o **energia per inferenza**, non come un moltiplicatore universale.

> 💡 **Potenza più alta, energia più bassa (nota didattica).** L’energia è potenza per tempo, $E = P \cdot t$. Supponiamo che una CPU da 65 W impieghi 20 ms per un’inferenza e una GPU da 300 W ne impieghi 2:
> $$E_{\text{CPU}} = 65\ \text{W} \times 0{,}020\ \text{s} = 1{,}3\ \text{J}, \qquad E_{\text{GPU}} = 300\ \text{W} \times 0{,}002\ \text{s} = 0{,}6\ \text{J}$$
> La GPU assorbe quasi 5 volte la potenza, ma consuma meno della metà dell’energia per inferenza. Se però il carico è piccolo e la GPU resta per lo più inattiva, o se i dati impiegano più tempo a viaggiare verso la GPU che a essere elaborati, il vantaggio sparisce.

<!-- SOURCE_VISUAL id="IS23-V37" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="73" type="grafico" description="GPU contro CPU nel riaddestramento di AlexNet" reason="Tempi e consumi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Altri dati** (p. 74). Un supercomputer **NVIDIA HGX AI A100** con 8 GPU costa **oltre 100 000 euro**, contro **qualche migliaio di euro** di una workstation.

**Server farm per il ML** (p. 75): **Amazon** (AWS), **Google**, **Facebook** (i server **Big Sur** nel data center di Prineville), **Microsoft** (AzureML), **IBM**, …

<!-- SOURCE_VISUAL id="IS23-V38" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="75" type="immagini" description="Server farm per il ML: data center di Amazon, Google e Facebook" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3. CPU ed edge**

_(slide pp. 76–80)_

**Le GPU non servono sempre** (p. 76).

> 📌 **ML sulle CPU** (p. 77). Offre vantaggi:
> - **portabilità** e **flessibilità** dei casi d’uso: **stesso codice**, applicazioni diverse;
> - **disponibilità sul mercato** a diversi livelli di **prestazioni** e di **prezzo**;
> - **rilascio** su un’ampia gamma di dispositivi, **dall’edge al cloud**.

**Portare il ML verso l’«edge»** (p. 78): **processori più piccoli** (Lezione 05, §5).

<!-- SOURCE_VISUAL id="IS23-V39" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="78" type="immagini" description="Processori più piccoli per portare il ML verso l’edge" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Esempio: ML su processori ARM** (pp. 79–80).
> - L’**Arm AI Platform** è una piattaforma di calcolo completa: CPU **Arm Cortex**, GPU **Mali** e una linea di processori molto scalabili, dall’**IoT** alle **auto connesse** fino ai **server**.
> - Offre funzioni per i framework di ML come **TensorFlow**, per progetti di **immagini e visione**, con **codice portabile** tra configurazioni Arm diverse.
> - Le reti neurali spesso **non richiedono dati ad altissima precisione**: i calcoli si possono fare su **16 o perfino 8 bit**, invece che su 32 o 64 bit.
> - Gran parte dell’elaborazione delle reti usa la **moltiplicazione di matrici in virgola fissa a 8 bit**. L’architettura **Armv8.2-A** ha introdotto istruzioni **NEON** SIMD (*single instruction, multiple data*) per la **mezza precisione** (FP16) e per il **prodotto scalare intero** (INT8), per accelerare le reti.
> - Oggi il punto non è solo CPU contro GPU: i sistemi Arm usano il **calcolo eterogeneo**:
>   - **CPU** per il controllo;
>   - **GPU** per i carichi paralleli;
>   - **NPU** per l’inferenza neurale a bassa potenza;
>   - **librerie ottimizzate** che distribuiscono il modello in modo efficiente sull’hardware disponibile.
>
> La slide mostra anche un processore **OD** (*Object Detection*) della famiglia ARM.

> 💡 **Che cosa vuol dire «8 bit» (nota didattica).** La **quantizzazione** rappresenta ogni valore reale $x$ con un intero $q$ a 8 bit, tramite una scala $s$ e uno zero $z$:
> $$q = \operatorname{round}\left(\frac{x}{s}\right) + z, \qquad x \approx s\,(q - z)$$
> Se i pesi di uno strato stanno in $[-1, 1]$, con 256 livelli la scala è $s = 2/255 \approx 0{,}0078$: l’errore massimo di arrotondamento è circa $0{,}004$. La memoria si riduce di 4 volte rispetto a FP32: ResNet-50, con circa 25,6 milioni di parametri, passa da circa 98 MB a circa 25 MB. Il prodotto di interi a 8 bit è inoltre molto più economico in silicio del prodotto in virgola mobile (Lezione 04, §3).

#### **6.4. TPU, edge TPU e acceleratori storici**

_(slide pp. 81–85)_

> 📌 **Google TPU** (p. 81). Una **Tensor Processing Unit** è un **acceleratore di AI** di tipo **ASIC** (circuito integrato specifico per l’applicazione) sviluppato da Google **specificamente per il machine learning con reti neurali**. La slide riporta un valore di picco di «275 TeraFlops» e aggiunge: «il tuo codice Colab gira (probabilmente) qui».

> ⚠️ **Colab e TPU (nota didattica).** In Colab il runtime predefinito è su **CPU**; GPU e TPU vanno scelte esplicitamente dal menu del tipo di runtime, e la loro disponibilità dipende dal piano. Il «probabilmente» della slide va letto come «se hai scelto il runtime TPU».

**Google TPU v5 e Trillium** (p. 82). **Trillium** è la **sesta generazione** di TPU e fa parte dell’**AI Hypercomputer** di Google Cloud, un’architettura di supercalcolo progettata per i carichi di AI più avanzati. Google sottolinea l’impegno verso librerie open source come **JAX**, **PyTorch/XLA** e **Keras 3** (Lezione 21, §3).

<!-- SOURCE_VISUAL id="IS23-V40" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="81" type="immagine" description="Google TPU e valore di picco in TeraFlops" reason="Immagine e dato presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Dai supercomputer all’edge** (p. 83).

| Scala | Esempio (slide) |
|---|---|
| **supercomputer a scala exaflop** | Sundar Pichai presenta il supercomputer Google da **9 exaflop**; le TPU sono collegate in **pod** con oltre **4000 chip** |
| **acceleratori neurali per l’edge** | **Google Edge TPU**: un **ASIC** per eseguire l’AI all’edge, ad alte prestazioni con **ingombro e consumo ridotti**; lavora in **aritmetica Int8** ed esegue **MobileNetV2 a 400 fotogrammi al secondo** |

<!-- SOURCE_VISUAL id="IS23-V41" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="83" type="immagini" description="Supercomputer TPU a scala exaflop e Google Edge TPU" reason="Immagini presenti solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **FLOPS e TOPS (nota didattica).**
> - I **FLOPS** (*floating-point operations per second*) misurano le operazioni in virgola mobile al secondo: si usano per l’addestramento su data center. Un exaflop vale $10^{18}$ FLOPS.
> - I **TOPS** (*trillions of operations per second*) misurano le operazioni, spesso intere, degli acceleratori per l’inferenza all’edge.
>
> Le due misure non sono confrontabili direttamente: dicono quanto è veloce il chip, non quanto è veloce **un certo modello** su quel chip, che dipende anche da memoria e software.

**Intel Movidius Neural Compute Stick** (p. 84, **esempio storico**). Era un acceleratore **VPU** compatto su **USB**, usato per imparare e prototipare l’inferenza all’edge (**da 1 a 4 TOPS**). Oggi è una **piattaforma superata**:

- Intel ha **interrotto** il prodotto;
- le versioni recenti di OpenVINO **non supportano più** i dispositivi Movidius/Myriad X;
- l’edge AI moderna si basa di solito su **NPU integrate**, **GPU embedded** o **acceleratori edge dedicati**.

<!-- SOURCE_VISUAL id="IS23-V42" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="84" type="immagine" description="Intel Movidius Neural Compute Stick" reason="Immagine presente solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’ambiente MATLAB** (p. 85): **hardware + software**, con **elaborazione parallela** e **cloud**.

<!-- SOURCE_VISUAL id="IS23-V43" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="85" type="schema" description="L’ambiente MATLAB tra hardware, software, elaborazione parallela e cloud" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. OpenVINO: ottimizzare i modelli**

_(slide pp. 86–90)_

**OpenVINO** (p. 86) è una **potente piattaforma di ottimizzazione** per i modelli; le pp. 87–88 ne presentano l’introduzione e i **dispositivi supportati**.

<!-- SOURCE_VISUAL id="IS23-V44" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="87" type="schema" description="Introduzione a OpenVINO" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V45" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="88" type="schema" description="Dispositivi supportati da OpenVINO" reason="Elenco presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Un flusso di lavoro tipico con OpenVINO** (p. 89).
> - L’**OpenVINO Runtime** ottimizza automaticamente le pipeline di deep learning con:
>   - **fusione aggressiva del grafo**: più operazioni accorpate in una;
>   - **riuso della memoria**;
>   - **bilanciamento del carico**;
>   - **parallelismo dell’inferenza** su **CPU, GPU e VPU**.
> - Strumenti di ottimizzazione: il **Post-Training Optimization Tool** e il **Neural Network Compression Framework** (NNCF).

<!-- SOURCE_VISUAL id="IS23-V46" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="89" type="diagramma" description="Flusso di lavoro tipico con OpenVINO" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Coerenza tra le slide (nota didattica).**
> - La p. 89 elenca la **VPU** tra i dispositivi di OpenVINO, mentre la p. 84 dice che le versioni recenti non supportano più Movidius/Myriad X. Le due slide si riferiscono a versioni diverse del software; oggi i bersagli tipici sono **CPU**, **GPU** integrate o dedicate e **NPU** Intel.
> - Nelle versioni recenti di OpenVINO il Post-Training Optimization Tool è stato **sostituito** da **NNCF**, che copre sia la quantizzazione dopo l’addestramento sia quella durante l’addestramento.

> 💡 **Il flusso in codice (ricostruzione didattica, non trascritta dalle slide).**
> ```python
> import numpy as np
> import openvino as ov
>
> core = ov.Core()
> print(core.available_devices)               # per esempio ['CPU', 'GPU', 'NPU']
>
> model = ov.convert_model("resnet50.onnx")   # da ONNX, PyTorch, TensorFlow, ...
> compiled = core.compile_model(model, "AUTO")  # sceglie il dispositivo migliore
>
> x = np.random.rand(1, 3, 224, 224).astype(np.float32)
> scores = compiled(x)[compiled.output(0)]
> print(scores.argmax())
> ```
> Il punto didattico è la separazione tra **modello** e **dispositivo**: lo stesso modello convertito si compila per CPU, GPU o NPU cambiando una stringa. È la «portabilità» che la p. 77 attribuiva alle CPU, estesa agli acceleratori.

La p. 90 rimanda a due video di approfondimento, uno su OpenVINO e uno sulle **Vision Processing Unit**.

---

### **8. Edge Impulse (non d’esame)**

_(slide pp. 91–94)_

**Edge Impulse** (p. 91) è una piattaforma per l’**edge AI** che copre **raccolta dei dati**, **addestramento**, **ottimizzazione** e **rilascio**. Le pp. 92–94 ne mostrano l’interfaccia con esempi e un tutorial video, e sono esplicitamente segnate come **non oggetto d’esame**.

<!-- SOURCE_VISUAL id="IS23-V47" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="92" type="schermata" description="Piattaforma Edge Impulse: interfaccia" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS23-V48" source="Lesson_23_IntelligentVisionSystems_Applications_ObjectDetectionSegmentationRecognition_Machine Learning_Hardware.pdf" page="93" type="schermata" description="Piattaforma Edge Impulse: esempi" reason="Pagina con sole schermate" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Dove si colloca (nota didattica).** Edge Impulse copre tutto il flusso di lavoro del ML (Lezione 05, §2) per dispositivi piccoli, come microcontrollori e sensori: acquisizione dai sensori, estrazione delle feature, addestramento, quantizzazione e generazione del codice per la scheda. È il complemento «end-to-end» di strumenti come OpenVINO, che ottimizzano un modello già esistente.

---

### **9. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Classificazione** / **rilevamento** | una classe per l’immagine / classe e bounding box per ogni oggetto |
| **Rilevamento** / **segmentazione** | rettangolo che contiene l’oggetto, niente misure / contorno esatto, misure reali |
| **Segmentazione semantica** / **per istanze** | una classe per pixel, oggetti uguali fusi / una maschera per ogni oggetto |
| **Bounding box** / **box ruotato** | 4 valori, lati paralleli agli assi / orientazione dell’oggetto (8 coordinate o 5 parametri) |
| **Soluzione classica** / **profonda** / **ibrida** | feature progettate / feature apprese / candidati classici classificati da un modello appreso |
| **Soglia/istogramma** / **bordi** / **regioni** | conoscenza globale / discontinuità / omogeneità interna |
| **Sovrasegmentazione** / **sottosegmentazione** | un oggetto diviso in più regioni / più oggetti fusi |
| **SAM** / **SAM 2** | immagini, guidato da prompt / immagini e video in tempo reale, con memoria |
| **Convoluzione** / **convoluzione trasposta** | riduce o mantiene la risoluzione / aumenta la risoluzione in modo appreso, non è l’inversa esatta |
| **ResNet-50 classificatore** / **+ decoder** | restituisce una classe / restituisce un’immagine di etichette |
| **CPU** / **GPU** | flessibile, poco parallela / massicciamente parallela, standard per l’addestramento |
| **TPU** / **NPU** / **VPU** | ASIC tensoriale, cloud / inferenza a bassa potenza sul dispositivo / pipeline visive embedded |
| **Potenza** / **energia** | watt assorbiti / joule per inferenza o per addestramento |
| **FLOPS** / **TOPS** | operazioni in virgola mobile, addestramento / operazioni per l’inferenza all’edge |
| **OpenVINO** / **Edge Impulse** | ottimizza e distribuisce un modello su hardware diversi / piattaforma completa dai dati al dispositivo |

---

### **10. Sintesi della lezione**

_(slide p. 95)_

> ✅ **Punti principali** (p. 95).
> - **Sistemi di visione intelligenti**: classificazione, rilevamento, segmentazione semantica e per istanze, con quello che ciascuno restituisce.
> - **Rilevamento, segmentazione e riconoscimento** di oggetti:
>   - misure in tre passi (rilevamento → segmentazione → misure), box ruotati, bin picking;
>   - metodi classici (soglia, bordi, regioni, watershed, k-means) e SAM/SAM 2;
>   - segmentazione semantica con reti completamente convolutive e convoluzione trasposta.
> - **Soluzioni classiche, profonde e ibride**.
> - **Casi d’uso**: droni per il controllo del traffico con watershed + VGG16 in fine-tuning, veicoli autonomi, agricoltura, satelliti.
> - **Hardware per il machine learning**:
>   - **GPU contro CPU**: velocità ed energia per inferenza, non potenza;
>   - **TPU** dal cloud all’Edge TPU, NPU, VPU;
>   - quantizzazione a 8 bit e calcolo eterogeneo;
>   - strumenti di ottimizzazione: l’esempio di **OpenVINO**.
> - **Edge Impulse**: piattaforma generica per l’edge AI (non d’esame).
