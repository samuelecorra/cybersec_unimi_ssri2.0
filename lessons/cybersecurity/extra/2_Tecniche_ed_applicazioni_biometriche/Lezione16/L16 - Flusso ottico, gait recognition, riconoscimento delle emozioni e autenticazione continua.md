# **Lezione 16: Flusso ottico, gait recognition, riconoscimento delle emozioni e autenticazione continua**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

La lezione affronta quattro temi collegati dall'analisi del **movimento** e del **comportamento** nel tempo:

1. **flusso ottico** e sue applicazioni in biometria, compresa la **gait recognition** (riconoscimento dalla camminata);
2. **riconoscimento delle emozioni**;
3. **autenticazione silente** e **autenticazione continua**;
4. **tracciamento dei comportamenti** a partire dai dati di processo.

---

## **Parte A — Flusso ottico e gait recognition**

### **1. Il flusso ottico**

#### **1.1 Che cosa misura**

_(slide pp. 3–7)_

Il flusso ottico è una tecnica di **elaborazione di segnale e immagini** molto utile per la biometria (p. 3). Il problema di partenza (p. 4): guardando due frame consecutivi, **che cosa è successo**? Come interpretare il cambiamento? **Chi si è mosso**?

> 📌 **Flusso ottico** (p. 5). Mappa, calcolata dalla **differenza delle posizioni** degli oggetti tra due frame vicini, che presenta le **aree** (di solito oggetti) che manifestano **velocità**.

Con **soli due frame** permette due funzioni molto utili:

1. aiuta il **rilevamento degli oggetti in movimento** indipendentemente da **forma e colore** (*blob detection*);
2. permette di ottenere **indirettamente la velocità** del blob in **pixel/s** e, conoscendo la **distanza**, in **m/s**.

> 💡 **Da pixel/s a m/s (nota didattica).** Con il modello a foro stenopeico (Lezione 11), un oggetto a distanza $Z$ ripreso con focale $f$ e pixel di passo $p$ (Lezione 12) si sposta nella scena di
> $$v_{\text{m/s}} = v_{\text{px/s}} \cdot \frac{p \cdot Z}{f}$$
> Con $p = 3{,}45$ µm, $f = 8$ mm, $Z = 5$ m e $v_{\text{px/s}} = 600$ px/s si ottiene $600 \cdot \dfrac{3{,}45 \cdot 10^{-6} \cdot 5}{8 \cdot 10^{-3}} \approx 1{,}29$ m/s, una camminata normale.

**Servono punti di riferimento** (p. 6). Per calcolare i vettori di spostamento servono **dettagli**: in una **zona senza dettagli** (un muro uniforme) non si capisce se e come i pixel si siano spostati; in una **zona con molti dettagli** i vettori si calcolano bene.

**Il concetto principale** (p. 7). Se un **drone** si muove in avanti, **in modo relativo** sono gli oggetti della scena a muoversi nell'immagine: il flusso ottico misura il movimento **apparente**, che può dipendere dalla camera, dagli oggetti o da entrambi.

<!-- SOURCE_VISUAL id="TAB16-V01" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="5" type="immagini" description="Esempi di flusso ottico: blob detection e misura della velocità da due frame vicini" reason="Mappa di flusso visibile solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V02" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="6" type="immagine annotata" description="Zone senza dettagli e zone con molti dettagli per il calcolo dei vettori di flusso" reason="Zone indicate sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2 Definizione e interpretazione**

_(slide pp. 8–9)_

Le slide pp. 8–9 presentano graficamente la **definizione** del flusso ottico e la sua **interpretazione del movimento**; il livello testo non ne conserva il contenuto.

> 💡 **La definizione classica (nota didattica).** L'ipotesi di base è la **costanza della luminosità**: un punto che si sposta di $(\Delta x, \Delta y)$ nel tempo $\Delta t$ mantiene la stessa intensità,
> $$I(x, y, t) = I(x + \Delta x,\ y + \Delta y,\ t + \Delta t)$$
> Sviluppando al primo ordine e dividendo per $\Delta t$ si ottiene l'**equazione del vincolo del flusso ottico**
> $$I_x\, u + I_y\, v + I_t = 0$$
> dove $(u, v) = (\Delta x / \Delta t,\ \Delta y / \Delta t)$ è il vettore di flusso e $I_x, I_y, I_t$ sono le derivate parziali dell'immagine. È **una** equazione in **due** incognite (**problema dell'apertura**): serve un'ipotesi aggiuntiva. Il metodo di **Lucas-Kanade** assume il flusso costante in una piccola finestra e risolve ai minimi quadrati; per questo nelle zone senza gradienti ($I_x \approx I_y \approx 0$) il sistema è mal condizionato, esattamente come osservato nella p. 6.

<!-- SOURCE_VISUAL id="TAB16-V03" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="8" type="formula e schema" description="Definizione del flusso ottico" reason="Pagina senza testo estraibile, contenuto solo grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V04" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="9" type="immagini" description="Flusso ottico e interpretazione del movimento" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3 Applicazioni biometriche**

_(slide p. 10)_

- **videosorveglianza**;
- **movimenti facciali**, micro e macro;
- **face tracking** e modellazione del volto;
- **antispoofing**.

> 💡 **Perché serve all'antispoofing.** Una foto stampata mossa davanti alla camera produce un flusso **rigido e planare**: tutti i punti si muovono insieme. Un volto vero produce un flusso **non rigido** (bocca, palpebre, espressioni) e con **parallasse** tra naso e orecchie. Questa differenza tornerà nelle Lezioni 19–20.

---

### **2. Dal flusso ottico alle feature: HOOF**

_(slide pp. 11–12)_

**Passi principali** (p. 11):

1. si calcolano i **vettori di flusso** $(x, y)$;
2. si visualizzano tutti i vettori con il loro **angolo** $\theta$ e la loro **velocità** $v$ nel piano $(\theta, v)$;
3. si **quantizzano** tutti i vettori con un **istogramma**.

> 📌 **HOOF, Histogram of Oriented Optical Flow** (p. 12). Ogni vettore di flusso viene inserito in un **bin** in base al suo **angolo principale rispetto all'orizzontale**; il contributo a ciascun bin è **pesato dalla magnitudine** del vettore. Si ottiene, **in ogni frame**, una caratteristica **invariante alla scala** e alla **direzione fronto-parallela**.

> 💡 **La formula (Chaudhry et al., CVPR 2009, nota didattica).** Per un vettore $\mathbf{v} = [x, y]^T$ con angolo $\theta = \arctan(y / x)$ e $B$ bin, il vettore contribuisce al bin $b$ ($1 \leq b \leq B$) se
> $$-\frac{\pi}{2} + \pi\,\frac{b-1}{B} \leq \theta < -\frac{\pi}{2} + \pi\,\frac{b}{B}$$
> con peso $\sqrt{x^2 + y^2}$. Poiché l'angolo è misurato rispetto all'orizzontale e ripiegato in $[-\pi/2, \pi/2)$, un movimento verso destra e lo stesso movimento verso sinistra finiscono nello stesso bin: da qui l'invarianza alla direzione fronto-parallela. Infine l'istogramma si **normalizza** a somma 1, e questo lo rende indipendente dalla scala (una persona più lontana produce vettori più corti ma con la stessa distribuzione di angoli).

<!-- SOURCE_VISUAL id="TAB16-V05" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="11" type="grafici" description="Distribuzione dei vettori di flusso nel piano (theta, v) e quantizzazione con istogramma" reason="Grafici non estraibili" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V06" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="12" type="schema" description="Bin angolari con cui è diviso il piano degli angoli per l'istogramma HOOF" reason="Suddivisione dei bin mostrata nel disegno" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Gait recognition e flusso ottico del volto**

_(slide pp. 13–14)_

**Passi principali per la gait recognition** (p. 13). Si segue **la stessa sequenza** degli altri sistemi biometrici (acquisizione, feature, template, matching), ma **cambiano feature e matcher**.

Esempio: **Space-Time Interest Points** (STIP), punti di interesse **spazio-temporali** che rappresentano un punto di interesse di un modello di camminata dominante e descrivono le caratteristiche di ogni **specifica andatura**.

- **Non** richiedono pre-elaborazione del video: niente **sottrazione dello sfondo**, rilevamento dei bordi o **silhouette**.
- Con queste tecniche si può fare **re-identificazione** di una persona in videosorveglianza **tra telecamere diverse**.

> 💡 **Perché la camminata funziona a distanza.** Non richiede collaborazione né alta risoluzione: il ritmo, l'ampiezza del passo e l'oscillazione delle braccia si vedono anche quando il volto è troppo piccolo o nascosto. È però un tratto **comportamentale** con variabilità intraclasse alta (scarpe, borse, stanchezza, superficie).

**Flusso ottico del volto** (p. 14). Permette di catturare anche movimenti **impercettibili**, spesso legati all'**attenzione**, allo **stato mentale** e alle **espressioni**: è il ponte verso il riconoscimento delle emozioni.

<!-- SOURCE_VISUAL id="TAB16-V07" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="13" type="sequenza di immagini" description="Space-Time Interest Points su una sequenza di camminata" reason="Punti di interesse visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V08" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="14" type="sequenza di immagini" description="Flusso ottico del volto in quattro frame con movimenti impercettibili" reason="Vettori di flusso visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Riconoscimento automatico delle emozioni**

### **4. Che cosa sono le emozioni**

_(slide pp. 15–18)_

#### **4.1 Ekman: sette emozioni universali**

Secondo **Paul Ekman** le emozioni principali dell'uomo sono **sei più la neutrale** (p. 16):

| Emozione | Inglese |
|---|---|
| rabbia | anger |
| disgusto | disgust |
| tristezza | sadness |
| gioia | happiness |
| paura | fear |
| sorpresa | surprise |
| (neutrale) | neutral |

- Secondo Ekman espressioni ed emozioni **non** sono determinate dalla **cultura** o dalle tradizioni: sono **universali**.
- **Moltissimi** studi e software di riconoscimento si basano su questo set.
- Ekman ha studiato anche le **micro-espressioni** facciali e il loro legame con la **menzogna**, tema della serie TV *Lie to Me* con Tim Roth.

#### **4.2 Feldman Barrett: le emozioni sono costruite**

**È tutto vero?** (p. 17). **Lisa Feldman Barrett**, tra i massimi esperti del cervello e delle emozioni, sostiene che l'idea di emozioni **cablate dalla nascita** o **puramente reattive** sia un **mito**, e sottolinea che gli scienziati **non hanno una definizione unificata** di emozione. Ekman, sulla generalità e leggibilità delle emozioni, ha costruito una carriera e il **Paul Ekman Group**, società di formazione e strumenti anche per la sicurezza.

**Due eminenti scienziati dicono di aver provato l'esatto contrario** (p. 18):

| Paul Ekman | Lisa Feldman Barrett |
|---|---|
| emozioni **«hard-wired»**, **generali** e **leggibili** | emozioni **costruite dal cervello**, non innate né universali; persone diverse le **esprimono diversamente** |

> ⚠️ **Che cosa si classifica davvero** (p. 18). Con il machine learning si classificano **posizioni e movimenti facciali** che spesso vengono **interpretati** come una specifica emozione, ma **non si può garantire** di classificare una **vera emozione**. Nel seguito si parla di «emozioni» per brevità.

<!-- SOURCE_VISUAL id="TAB16-V09" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="16" type="fotografie" description="Le emozioni base di Ekman illustrate con espressioni facciali e riferimento a Lie to Me" reason="Espressioni visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Il riconoscimento come classificazione**

#### **5.1 Il problema, il mercato e gli input**

_(slide pp. 19–23)_

Il **Facial Expression Recognition** (FER) è un problema di **classificazione** nelle sette classi anger, disgust, fear, happy, neutral, sad, surprise (p. 19).

**Il mercato** (pp. 20–21):

- il mercato dell'**affective computing** vale circa **80 miliardi di dollari** (2025), con crescita prevista del **24% annuo** (CAGR);
- esempio: **Realeyes** (adverteyes) misura gli effetti emotivi degli spot dei principali marchi:
  - testa gli spot su **panel opt-in** in ambienti simulati (YouTube, TikTok, TV);
  - misura **attenzione** ed **espressioni facciali** via **webcam** con face detection, landmark tracking, posa della testa, eye tracking, **secondo per secondo**;
  - raccoglie dati di **brand lift** con questionari;
  - fornisce benchmark, **heatmap di attenzione** e suggerimenti scena per scena (**GO / FIX / NO GO**).

> 💡 **Leggere un CAGR (nota didattica).** Con un tasso di crescita annuo composto $r$, il valore dopo $n$ anni è $V_n = V_0 (1 + r)^n$. Con $V_0 = 80$ miliardi e $r = 0{,}24$, in cinque anni $V_5 = 80 \cdot 1{,}24^5 \approx 80 \cdot 2{,}93 \approx 235$ miliardi: quasi il triplo.

**Tipi di input** (p. 22). La lezione si concentra su **immagini e video**, ma le emozioni si classificano anche da **audio**, combinazioni di audio e altri formati, **EEG, ECG** e dispositivi **wearable**, e **testi**.

**Altri tipi di output: open vocabulary** (p. 23). La ricerca si sposta dalle 7 classi di Ekman verso l'**Open-Vocabulary Multimodal Emotion Recognition** (OV-MER): invece di classi fisse si usano gli **LLM** per descrivere l'emozione in **linguaggio naturale**, catturando sfumature che un'etichetta non esprime. Formalmente:

$$f : X \rightarrow \text{embedding} \in \mathbb{R}^d \qquad g : \text{text prompt} \rightarrow \text{embedding} \in \mathbb{R}^d$$

e si confrontano gli embedding con una **similarità**, per esempio il **coseno**:

$$\cos\big(f(x), g(p)\big) = \frac{f(x) \cdot g(p)}{\lVert f(x) \rVert\, \lVert g(p) \rVert}$$

> 💡 **È lo stesso schema del matching biometrico.** Immagine e descrizione testuale vengono proiettate nello **stesso spazio** di embedding e confrontate come due template (Lezione 11). Il «template» dell'emozione è il testo, quindi si possono interrogare emozioni mai viste in addestramento, come «nostalgia».

<!-- SOURCE_VISUAL id="TAB16-V10" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="19" type="griglia di immagini" description="Facial Expression Recognition come classificazione in sette classi" reason="Esempi di volti per classe" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V11" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="21" type="schermate" description="Sistema Realeyes adverteyes con misura dell'attenzione, heatmap e raccomandazioni GO/FIX/NO GO" reason="Esempio visivo del prodotto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Tecnologie e schema di calcolo**

_(slide pp. 24–32)_

**Tre famiglie di tecnologie** (p. 25):

| Famiglia | Input |
|---|---|
| **static-based** | un **singolo frame** |
| **dynamic-based** | una **sequenza di frame** o un video |
| **multimodal** | segnali **audio e video** |

**Lo schema computazionale tradizionale** (p. 26): (1) **face detection** → (2) **preprocessing** → (3) **classificazione** (per esempio classe «Happy»).

| Passo | Tecniche |
|---|---|
| **1. Face detection** (p. 27) | **algoritmiche**: Viola-Jones con wavelet di Haar, istogramma dei gradienti orientati (HOG); **CNN**: region-based, a finestra scorrevole, *single shot detector* |
| **2. Preprocessing** (opzionale, p. 28) | **compensazione dell'illuminazione**: diffusione isotropa, trasformata discreta del coseno, filtraggio omomorfico; **normalizzazione della posa**: rilevamento dei landmark (Viola-Jones, deep learning), geometria epipolare |
| **3a. Classificazione statica classica** (pp. 29–30) | feature estratte e **normalizzate**, poi classificatori **kNN**, **alberi di decisione**, reti **feed-forward**, **SVM**. Feature più usate: **LBP**, **HOG**, local gradient code, local directional pattern, **filtri di Gabor**, patched geodesic texture, curvelet, fattorizzazione di matrici non negative |
| **3b. Classificazione statica deep** (p. 31) | architetture: **CNN**, **ensemble** di CNN, classificatori **a cascata** basati su CNN; miglioramenti: **transfer learning** (da un dominio o database a un altro), **data augmentation** |
| **3c. Metodi dinamici** (p. 32) | $N$ frame in ingresso: **traiettorie dei landmark**, **CNN 3D**, **reti ricorrenti** combinate con CNN 3D |

> ⚠️ **Refuso.** La p. 29 scrive «decision three»: si tratta dei **decision tree**, alberi di decisione.

<!-- SOURCE_VISUAL id="TAB16-V12" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="26" type="schema a blocchi" description="Schema computazionale tradizionale: face detection, preprocessing, classificazione" reason="Schema con immagini di esempio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V13" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="30" type="immagini" description="Feature per metodi statici: LBP, filtri di Gabor, HOG applicati a un volto" reason="Esempi visivi delle feature" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3 Multimodalità e architetture**

_(slide pp. 33–36)_

**Fusione multimodale a livello di punteggio** (p. 33). Un riconoscitore sul **volto nel video** e uno sull'**audio** producono ciascuno le probabilità $p(\text{Anger}), p(\text{Disgust}), \dots, p(\text{Surprise})$; la **score fusion** le combina e produce la classe, per esempio «Neutral» (Lezione 15, §5).

> ⚠️ **Refusi.** La slide scrive «Funsione» per **fusione** e «Disust» per **Disgust**.

**Speech Emotion Recognition** (p. 34). Compito dell'elaborazione del parlato e della **paralinguistica computazionale** che riconosce e categorizza le emozioni espresse nel linguaggio parlato (felicità, rabbia, tristezza, frustrazione) a partire dagli schemi vocali: **prosodia**, **tono** e **ritmo**.

**Multimodale con deep learning** (p. 35). Si estraggono vettori di feature dall'**audio**, tramite **spettrogramma**, e da **un frame**, con **due backbone separate** (VGG); si **fondono in modo neurale** e si arriva al layer **softmax** di classificazione. È la fusione a livello di feature deep della Lezione 15, §11.

**Implementazioni** (p. 36):

| | **Client-side** | **Client-server** |
|---|---|---|
| Dove si calcola | detection, preprocessing e classificazione **sul client** | il client acquisisce, il **server** elabora |
| Comunicazione | nessuna | **canale cifrato** |

<!-- SOURCE_VISUAL id="TAB16-V14" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="33" type="schema" description="Fusione multimodale a livello di score tra riconoscimento delle emozioni dal volto nel video e dall'audio" reason="Schema a blocchi con distribuzioni di probabilità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V15" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="35" type="schema di rete" description="Multimodale deep: spettrogramma audio e frame con due backbone VGG, fusione neurale e softmax" reason="Architettura mostrata nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V16" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="36" type="schema" description="Implementazioni client-side e client-server con canale cifrato" reason="Confronto grafico delle due architetture" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Dataset, prodotti e accuratezza**

#### **6.1 Allenamento e dataset**

_(slide pp. 37–38)_

Esistono **dataset di immagini etichettate** per l'uso biometrico, usati per l'allenamento o il **fine-tuning** di reti deep e per confrontare l'accuratezza dei software commerciali (p. 37). È **molto importante** usare la validazione **L(N)PO**, *Leave (N) Persons Out* (Lezione 6): le stesse persone non devono comparire in addestramento e in test.

| Dataset | Contenuto |
|---|---|
| **ADFES** (Amsterdam Dynamic Facial Expression Set) | **22 attori** recitano **9 espressioni** in video: anger, disgust, fear, joy, sadness, surprise, contempt, pride, embarrassment |
| **BU-4DFE** | **78 individui** in **posa**, **467 video** |
| **UT-Dallas** | **292 individui** con espressioni **spontanee**, **961 video** |
| **CASME** | **micro-espressioni** |

Spesso è **necessario riallenare** i modelli su database **più vicini** alla propria applicazione (p. 38): la slide elenca molti dataset con emozioni ed espressioni diverse, e sottolinea il **numero molto diverso di immagini e soggetti**.

> 💡 **Posate contro spontanee.** Un attore che «recita» la rabbia produce un'espressione esagerata e prototipica, facile da classificare. Le espressioni spontanee sono deboli, miste e brevi. Un modello allenato solo su dati posati avrà prestazioni ottimistiche che crollano nell'applicazione reale.

<!-- SOURCE_VISUAL id="TAB16-V17" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="38" type="tabella" description="Elenco di dataset per emozioni ed espressioni con numero di immagini e soggetti, incluso CASME per le micro-espressioni" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Un prodotto commerciale e i confronti in letteratura**

_(slide pp. 39–41)_

**FaceReader di Noldus** (p. 39), sviluppato con la collaborazione di P. Ekman:

- stima **su un frame**, con possibilità di **calibrazione**: happy, sad, angry, surprised, scared, disgusted, neutral;
- inoltre **direzione dello sguardo**, occhi e bocca aperti o chiusi, **arousal** (attenzione, eccitazione);
- secondo la slide si appoggia al modulo **DeepFace** (Meta) per disambiguare i **frame difficili**.

**Risultati in letteratura** (p. 40): Dupré, Krumhuber, Küster, McKeown (2020), «A performance comparison of eight commercially available automatic classifiers for facial affect recognition», su **937 video** (467 BU-4DFE, 470 UT-Dallas). Le barre d'errore sono **intervalli di confidenza al 95%** calcolati con il metodo **bootstrap** (ricampionamento).

> 💡 **Il bootstrap in due righe (nota didattica).** Dai $n$ risultati del test si estraggono **con reinserimento** molti campioni di dimensione $n$, e su ciascuno si ricalcola l'accuratezza. Il 2,5° e il 97,5° percentile delle accuratezze ottenute delimitano l'intervallo di confidenza al 95%, senza ipotesi sulla distribuzione.

**Emozioni «facili» e «difficili»** (p. 41). Studi trasversali su database e metodi diversi mostrano che le emozioni si riconoscono con **accuratezze molto diverse** tra loro; il set di stati emotivi è stato **ampliato** oltre le sette classi.

<!-- SOURCE_VISUAL id="TAB16-V18" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="40" type="grafico a barre" description="Confronto di otto classificatori commerciali su 937 video con intervalli di confidenza bootstrap al 95%" reason="Valori leggibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V19" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="41" type="grafico" description="Accuratezza molto diversa tra emozioni facili e difficili da classificare su un set ampliato di stati emotivi" reason="Accuratezze per emozione presenti solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Benchmark statici e dinamici**

_(slide pp. 42–44)_

**Static Face Emotion Recognition (SFER)** (p. 42). **RAF-DB** (*Real-world Affective Faces Database*) contiene immagini **«in the wild»** annotate da **più valutatori umani** con le emozioni di base; è un benchmark di riferimento. La slide ricorda che il **disprezzo** (*contempt*) è stato **aggiunto in seguito** da Ekman.

**Dynamic Face Emotion Recognition (DFER)** (p. 43):

- **input**: video o sequenza di frame del volto $t_1, \dots, t_n$;
- **output**: un'emozione o una **curva di probabilità** emotiva nel tempo;
- **idea chiave**: l'emozione è un **processo dinamico**, **onset → apex → offset** (inizio, picco, fine).

**Dataset DFER** (p. 44):

| Dataset | Contenuto |
|---|---|
| **DFEW** (ACM MM 2020) | **16.372 clip** molto impegnative estratte da **film**, con illuminazione estrema, occlusioni e cambi di posa; ogni clip annotata indipendentemente **dieci volte** da **12 annotatori esperti** |
| **FERV39k** (CVPR 2022) | il più grande dataset DFER in-the-wild pubblico: **38.935 clip** da **4 macro-scenari** (crimini, vita quotidiana, discorsi, guerra) |

#### **6.4 Il paradosso dell'annotatore**

_(slide pp. 45–46)_

> 📌 **Inter-rater agreement** (p. 45). Indice di quanto **annotatori umani indipendenti** assegnano **la stessa etichetta** emotiva allo stesso stimolo (immagine, frame, clip audio). Gli umani spesso **non sono d'accordo** tra loro: **accordo < 70%**.

**Conseguenza**: se gli umani non concordano sull'etichetta, l'accuratezza dell'AI **non può superare scientificamente** quel limite. I lavori allo stato dell'arte non puntano più al «100% di accuratezza», ma a **imitare la media del giudizio umano** usando il **CCC** (*Concordance Correlation Coefficient*).

> 💡 **Il CCC (Lin, 1989, nota didattica).** Tra le previsioni $x$ e le etichette medie degli annotatori $y$:
> $$CCC = \frac{2 \rho\, \sigma_x \sigma_y}{\sigma_x^2 + \sigma_y^2 + (\mu_x - \mu_y)^2}$$
> dove $\rho$ è la correlazione di Pearson. Vale **1** solo se le previsioni coincidono con le etichette, penalizzando sia la scarsa correlazione sia gli spostamenti di media e scala.

> ⚠️ **Massimizzare, non minimizzare.** La slide dice che i lavori puntano a «minimizzare il CCC». Il CCC misura la **concordanza** e va **massimizzato** (valore ideale 1); ciò che si minimizza in addestramento è la loss $1 - CCC$.

**SOTA 2026 contro umani** (p. 46). Il confronto non è «umano perfetto contro AI», ma **AI contro etichette umane**, spesso **rumorose**. Su alcuni benchmark la «human-level accuracy» è **bassa** perché il dataset è ambiguo (bassa risoluzione, occlusioni, etichette incerte): su **FER2013** l'accuratezza umana riportata è circa **65–68%**. Esiste quindi un **«soffitto»** legato alla qualità delle etichette (errore di Bayes, rumore di annotazione): **«superare l'umano»** può voler dire **imitare meglio la media degli annotatori**.

#### **6.5 Lo stato dell'arte in numeri**

_(slide pp. 47–50)_

**Immagini statiche (SFER)** (p. 47):

| Dataset | Umani | Modelli |
|---|---:|---:|
| **FER2013** | ~65–68% | tipicamente ~75–77% |
| **RAF-DB** (più «pulito») | — | **92,21%** (POSTER++) |
| **AffectNet**, 7 classi (più «difficile») | — | **67,49%** (POSTER++) |

**Video (DFER)** (pp. 48–49). I dataset video hanno una **distribuzione a coda lunga**: alcune emozioni hanno pochissimi esempi e le classi sono **molto sbilanciate** (molte clip di happy e neutral, pochissime di disgust e fear). Per questo si usa la metrica **WAR**.

| Dataset | Modelli | WAR |
|---|---|---:|
| **DFEW** | UniLearn / S4D | **76,68%** |
| **FERV39K** | — | **53,65%** |
| **MAFW** | — | **58,44%** |

> 💡 **WAR e UAR (nota didattica).** La **WAR** (*Weighted Average Recall*) pesa il recall di ogni classe per la sua frequenza, $WAR = \sum_c \frac{n_c}{N} \cdot \text{recall}_c$, e coincide con l'accuratezza complessiva. La **UAR** (*Unweighted Average Recall*) fa la media semplice dei recall, $UAR = \frac{1}{C} \sum_c \text{recall}_c$. Con classi sbilanciate la WAR è dominata dalle classi frequenti, mentre la UAR dà lo stesso peso a disgust e a happy: nei benchmark DFER si riportano di norma **entrambe**, e il divario tra le due rivela quanto il modello trascura le classi rare.

**Perché l'uomo «vince» ancora spesso nel video** (p. 49):

- **integrazione del contesto** (ambiente, gesti, dinamica sociale), spesso assente nel ritaglio del volto;
- **smoothing cognitivo** naturale: l'uomo ignora glitch, blur e occlusioni;
- **ambiguità** delle emozioni **spontanee**: non prototipiche, di bassa intensità, composte.

> 📌 **Riassunto** (p. 50). **Immagini statiche**: problema relativamente **«chiuso»**, con modelli molto competitivi con l'umano su dataset ben annotati. **Video realistici**: problema **ancora aperto**, per variabilità temporale, contesto e sbilanciamento, con metriche più sofisticate (WAR) e prestazioni **significativamente più basse**.

<!-- SOURCE_VISUAL id="TAB16-V20" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="48" type="grafico a barre" description="Distribuzione long-tail delle clip per emozione nei dataset video: happy e neutral numerose, fear e disgust rarissime" reason="Barre disegnate con caratteri grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.6 ROC dei classificatori e confronto con gli umani**

_(slide pp. 51–52)_

- In uscita i classificatori danno **valori di intensità** dell'emozione, che si possono usare come **soglia**: si calcola quindi la **curva ROC** (p. 51).
- Il **consenso** delle etichette tra gli umani diventa la **certezza** del riconoscimento umano: si possono **aggiungere gli umani** nella stessa curva ROC.

**Stato dell'arte 2020 e oltre** (p. 52): curve ROC e **AUC** (TPR contro FPR) per **osservatori umani** e **classificatori automatici**, separatamente per espressioni **posate** (BU-4DFE) e **spontanee** (UT-Dallas); la diagonale tratteggiata indica le prestazioni **casuali**. La slide evidenzia i **sistemi recenti** su entrambi i dataset.

<!-- SOURCE_VISUAL id="TAB16-V21" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="52" type="curve ROC" description="ROC e AUC di osservatori umani e classificatori automatici per espressioni posate (BU-4DFE) e spontanee (UT-Dallas)" reason="Curve e valori di AUC presenti solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Emozioni nelle risorse umane ed etica**

_(slide pp. 53–57)_

**Il mercato** (p. 53): l'AI per il **reclutamento** vale circa **3,89 miliardi di dollari** e tra 5 anni dovrebbe **superare i 17 miliardi**.

> 💡 **Il tasso implicito (nota didattica).** Passare da 3,89 a 17 miliardi in 5 anni significa $r = (17 / 3{,}89)^{1/5} - 1 \approx 0{,}34$: una crescita di circa il **34% annuo**, più rapida dell'affective computing in generale.

**AI for HR** (p. 54):

- **chatbot** di reclutamento: HireVue promette di assumere «fino a **4 volte** più velocemente», seguendo i candidati «da "Hi" a "Hired"»;
- **analisi automatica** delle **videointerviste**, **senza** selezionatore umano.

**Accuse di discriminazione** (p. 55) e l'uso delle **micro-espressioni** per valutare **se il candidato mente** (p. 56).

**L'AI Act** (p. 57), approvato nel **marzo 2024**. Il riconoscimento delle emozioni è **permesso ma fortemente regolamentato**:

- **vietato** sul **luogo di lavoro** e negli **istituti di istruzione**;
- niente **social scoring** né **manipolazione cognitiva**;
- **obbligo di informare** le persone esposte al sistema;
- il regolamento si applica **due anni** dopo l'entrata in vigore, con **eccezioni** per disposizioni specifiche. Tra queste, i divieti dell'art. 5, applicabili già dal 2 febbraio 2025: si veda la nota sulle date nella Lezione 13, §5.2.

> ⚠️ **«Emotive imperialism».** Nasce un problema di **discriminazione** che Luke Stark (Information & Media Studies, University of Western Ontario) chiama **imperialismo emotivo**: le AI rischiano di essere progettate sui modi di esprimere le emozioni **tipici della cultura che le produce**, con risultati **errati** per persone di altre parti del mondo. È esattamente la tesi di Feldman Barrett (§4.2) applicata a un sistema che decide di un'assunzione.

<!-- SOURCE_VISUAL id="TAB16-V22" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="55" type="articoli di stampa" description="Accuse di discriminazione nell'uso delle emozioni facciali per le risorse umane" reason="Pagina con sole immagini di articoli" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V23" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="56" type="immagini" description="Micro-espressioni nelle videointerviste: il candidato mente?" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte C — Autenticazione silente e continua**

### **8. Il problema del tempo**

_(slide pp. 58–59)_

Le slide pp. 58–59 confrontano le **tecnologie** di autenticazione continua e sottolineano che **la gestione del tempo diventa critica**.

> 📌 **Autenticazione continua.** Invece di verificare l'identità **una volta** all'inizio della sessione, la si verifica **ripetutamente** (o in modo continuo) per tutta la sua durata. Protegge dal caso in cui l'utente legittimo si autentica e poi **lascia** il dispositivo o la postazione a qualcun altro.

> 💡 **Perché il tempo è critico.** Un'autenticazione ogni ora lascia a un intruso fino a un'ora di sessione; un controllo ogni pochi secondi richiede acquisizioni **non intrusive**, fatte mentre l'utente lavora, quindi campioni di **qualità variabile**. Il progetto è un compromesso tra **frequenza**, **usabilità** e **affidabilità** di ogni singolo controllo.

<!-- SOURCE_VISUAL id="TAB16-V24" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="58" type="tabella grafica" description="Comparazione delle tecnologie di continuous authentication" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V25" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="59" type="schema temporale" description="Continuous authentication: gestione del tempo" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Autenticazione silente**

_(slide pp. 60–62)_

| | **Silent authentication di base** (mobile, p. 60) | **Silent authentication avanzata** (Thales, pp. 61–62) |
|---|---|---|
| Principio | autenticazione sicura **senza** password, dati di verifica od **OTP**; l'utente è autenticato automaticamente dal suo **operatore di rete mobile** in base alla connessione dati dello smartphone (**SIM** e **IP**) | **biometria comportamentale passiva** e **machine learning** tramite un'app in esecuzione sullo smartphone durante le attività quotidiane |
| Dati | identità della SIM e connessione | **come l'utente usa il telefono**, **camminata**, **segnali WiFi** dell'ambiente, **geolocalizzazione** |
| Usi | creazione di account online, modifiche agli account, **reset delle password**, altre transazioni importanti | e-commerce di nuova generazione, **consegne con droni**, autenticazione senza interruzioni nelle app |
| Limite | se rubano il **cellulare sbloccato** e lo usano per accedere ai servizi, **non viene rilevato** | il parere degli utenti è già orientato verso questi obiettivi |

> 💡 **La differenza fondamentale.** L'autenticazione silente di base verifica il **dispositivo** (qualcosa che si possiede), non la **persona**. Quella avanzata aggiunge un segnale **comportamentale**: un ladro può avere il telefono, ma cammina e lo usa in modo diverso dal proprietario.

---

### **10. Autenticazione continua con sensori indossabili**

_(slide pp. 63–71)_

#### **10.1 Il cellulare e le IMU**

**Il cellulare** (p. 63) contiene una **IMU** (*Inertial Measuring Unit*).

**Esempi Bosch** (p. 64):

| Componente | Caratteristiche |
|---|---|
| **BMI085** | IMU **MEMS a 6 assi**: accelerometro a **3 assi da 16 bit** e giroscopio a **3 assi da 16 bit** in un solo package; per **realtà virtuale e aumentata**, navigazione, tracciamento dei movimenti del corpo e gaming. Rilevazione istantanea dei movimenti della testa con ritardo quasi impercettibile, giroscopio a **bassa deriva** e accelerometro a **basso rumore** per ridurre la **motion sickness** |
| **BHA260** | famiglia di **smart hub** a bassissimo consumo: microcontrollore programmabile a **32 bit** (Fuser2), accelerometro a 3 assi, software di **sensor fusion** preinstallato, package **LGA a 22 pad** |

> 💡 **Che cosa vede un'IMU della camminata.** L'accelerometro misura il ritmo dei passi (picchi periodici di accelerazione verticale) e l'andatura; il giroscopio misura le rotazioni del telefono in tasca o in mano. Da questi segnali si estraggono feature di **gait recognition inerziale**, senza nessuna camera.

<!-- SOURCE_VISUAL id="TAB16-V26" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="63" type="schema" description="Continuous authentication sul cellulare con Inertial Measuring Unit" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2 Braccialetti, volanti e orologi con ECG**

**Wrist band e smart watch** (p. 65). Il principio generale: **un solo punto di autenticazione** può **abilitare più servizi**; il segnale usato è l'**ECG**.

| Dispositivo | Caratteristiche (slide) |
|---|---|
| **Nymi Band** (p. 66) | wearable biometrico per il **luogo di lavoro**, **impronta + ECG**; **autenticazione multifattore**; **autenticazione continua** tramite **rilevamento sul corpo** (*on-body detection*). Adatto ad ambienti con **mascherine, guanti, protezioni**; spesso a protezione di rilevanti investimenti di **R&D** o sostanze **molto pericolose** (batteri, virus) |
| **CardioWheel** (p. 67) | **ECG sul volante** per l'automotive: rileva **anomalie del battito** ed esegue l'**autenticazione continua del conducente** |
| **Polar Vantage V** (p. 68) | dati sportivi, dati **ECG**, **variabilità della frequenza cardiaca** (HRV) |
| **Apple Watch** Series 4 e successivi (p. 69) | **ECG** simile a un elettrocardiogramma a **singola derivazione** e **fotopletismografo** (PPG). Apple dichiara che i dati possono indicare **fibrillazione atriale** (ritmo irregolare grave) o **ritmo sinusale** (normale). **ECG e PPG si possono usare per il riconoscimento biometrico** |
| **WISE** di Pontosense (p. 71) | **tecnologia a radiofrequenza** che misura i parametri vitali del conducente (**frequenza cardiaca e respiro**) per rilevare **fatica** ed emergenze mediche, **senza wearable** |

**Riferimento di ricerca** (p. 70): R. Donida Labati, E. Muñoz, V. Piuri, R. Sassi, F. Scotti, «Deep-ECG: Convolutional Neural Networks for ECG biometric recognition», *Pattern Recognition Letters*, 2018.

> 💡 **Perché l'ECG è adatto all'autenticazione continua.** È un segnale **interno**, difficile da acquisire a distanza e quindi da falsificare, e prova intrinsecamente la **vitalità** del soggetto. Un braccialetto che lo misura in continuo sa se è ancora **indossato** dalla stessa persona che si è autenticata. Il rovescio è la **privacy**: lo stesso segnale rivela lo stato di salute (Lezione 13, §18).

<!-- SOURCE_VISUAL id="TAB16-V27" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="65" type="schema" description="Wrist band e smart watch: un punto di autenticazione ECG abilita più servizi" reason="Funzionamento generale mostrato nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V28" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="69" type="fotografie e grafici" description="Apple Watch con tracciati ECG e PPG" reason="Segnali visibili solo nei grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Autenticazione continua multimodale**

_(slide pp. 72–79)_

#### **11.1 Fondere tratti con affidabilità variabile nel tempo**

La multimodalità serve a **irrobustire il funzionamento** e **migliorare l'accuratezza** (p. 72).

**Fusione multimodale continua** (p. 73). Nel tempo, mentre l'utente svolge le sue attività, il **livello di affidabilità** del riconoscimento (**trust**) di ogni tratto (volto, impronta, voce, iride, camminata) oscilla tra **L**, **M** e **H** (basso, medio, alto). Servono:

- approcci per gestire l'**indeterminazione dei tratti nel tempo**;
- fusione **asincrona** e **adattativa**;
- soluzioni **neurali**.

Serve un approccio capace di gestire le **incertezze** del riconoscimento al variare delle condizioni (p. 74), **partendo dal presupposto** che all'inizio l'utente sia un **genuino**.

**Esempio: il dataset MultiAmi** (p. 75), acquisizioni **meno vincolate** e **non cooperative**:

- occlusioni;
- rotazioni brusche;
- pose varie;
- utente seduto inattivo, al lavoro sulla console, al telefono.

<!-- SOURCE_VISUAL id="TAB16-V29" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="73" type="grafico temporale" description="Livelli di affidabilità L/M/H di volto, impronta, voce, iride e camminata al variare delle attività nel tempo" reason="Andamento temporale visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.2 L'EER nel tempo**

**Test: $EER(t)$** (pp. 76–77). Si usa il **singolo frame** $I(t)$ per calcolare, **rispetto a tutti gli altri template** memorizzati, l'EER del singolo utente **in quell'istante**. Serve una **gallery** con confronti di **genuini e impostori**. Così si verifica **quando l'autenticazione non è affidabile**:

| Istante | $EER(t)$ | Interpretazione |
|---|---:|---|
| $t = 0$ | **3,3%** | frame di buona qualità: autenticazione affidabile |
| $t = 3$ s | **45%** | quasi il caso (50%): in quel frame il sistema non distingue genuino e impostori |

> 📌 **Domanda e risposta della slide** (p. 76). *Perché non usare solo il match score 1:1?* Perché **non direbbe quanto sono lontani dal genuino gli impostori**. Un punteggio di 0,6 può essere ottimo se tutti gli impostori stanno a 0,1 e pessimo se stanno a 0,55: l'$EER(t)$ misura la **separabilità** nell'istante $t$, non solo la somiglianza.

> 💡 **Il legame con la normalizzazione cohort.** È la stessa idea della Lezione 15, §9: confrontare il campione corrente con molti impostori per capire se il punteggio del genuino è davvero significativo.

<!-- SOURCE_VISUAL id="TAB16-V30" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="77" type="grafico e immagini" description="EER(t) da 3,3% a t=0 a 45% a t=3 s con la gallery di genuini e impostori" reason="Andamento dell'EER e frame visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 Architettura con fusione adattativa e qualità**

**Architettura** (p. 78):

- un **modulo video** elabora le sequenze di frame: estrazione delle feature, estrazione del template del **volto**, matching;
- un **modulo audio** elabora i segnali vocali: estrazione delle feature, template della **voce**, matching;
- i due punteggi entrano in una **fusione adattativa** a base **neurale**, che produce il punteggio fuso $s$, valutato con l'$EER$ nei diversi istanti.

**Voce + volto + qualità** (p. 79). Si aggiungono uno **stimatore di qualità della voce** ($q_1$) e uno **del volto** ($q_2$). La rete neurale fonde $mscore_1$, $mscore_2$, $q_1$, $q_2$ e **tiene conto in modo dinamico** della qualità del tratto in ingresso: è la fusione basata sulla qualità della Lezione 15, §14, applicata nel tempo.

> 💡 **Esempio di funzionamento.** Quando l'utente si gira verso la finestra il volto perde qualità ($q_2$ basso) e la rete si affida alla voce; quando smette di parlare la qualità della voce crolla e il volto torna a pesare. Il trust complessivo resta stabile anche se nessun tratto è sempre disponibile.

<!-- SOURCE_VISUAL id="TAB16-V31" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="78" type="schema a blocchi" description="Autenticazione continua multimodale: moduli video e audio, fusione adattativa neurale ed EER nel tempo" reason="Schema a blocchi dell'architettura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V32" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="79" type="schema a blocchi" description="Fusione voce, volto e qualità con stimatori q1 e q2 e rete neurale" reason="Schema con formula non estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Autenticazione continua da terminale**

_(slide pp. 80–87)_

**Tastiera e mouse: approccio multimodale** (pp. 80–81), con **fusione a livello di punteggio** tra dinamica di digitazione e movimenti del puntatore.

> 💡 **Le feature di base della dinamica di digitazione (nota didattica).** Per il tasto $i$ premuto all'istante $t^{down}_i$ e rilasciato a $t^{up}_i$:
> $$\text{dwell}_i = t^{up}_i - t^{down}_i \qquad \text{flight}_i = t^{down}_{i+1} - t^{up}_i$$
> Il **dwell time** è quanto a lungo si tiene premuto un tasto, il **flight time** quanto passa tra un tasto e il successivo. Le loro distribuzioni per coppie di tasti frequenti («th», «er») formano il template.

**Keystroke DNA** (p. 82):

- **non continuo**: parte da **8 caratteri**, via **API** e **SaaS**;
- usabile come **secondo o terzo fattore** di autenticazione;
- **«HOW you type, not WHAT you type»**: conta **come** si digita, non **che cosa**;
- qualsiasi dispositivo, **nessun hardware** speciale, integrazione semplice;
- secondo il produttore, riconosce schemi di digitazione **dinamici** che non si possono condividere, imitare o perdere.

**Plurilock DEFEND** (pp. 83–87):

| Aspetto | Descrizione (slide) |
|---|---|
| **Principio** | identità in tempo reale **per tutta la giornata**, **senza hardware** aggiuntivo, **invisibile** agli utenti; riconosce gli utenti da **come lavorano**, **mai da che cosa** stanno facendo |
| **Livelli di rischio** | movimenti e cadenza abituali → **rischio nullo o basso** nei log; comportamento insolito → eventi a **rischio medio**; superata una **soglia configurabile** → registra un **mismatch d'identità** o **chiude la sessione** |
| **Storia** | sviluppato dal **2012** con le forze armate statunitensi e **DARPA** (programma *Active Authentication*): prima applicazione diffusa di autenticazione con **biometria comportamentale** |
| **Template** (p. 84) | **pattern di micro-comportamento**: **come** sono stati premuti i tasti e mosso il mouse; **ciò che l'utente fa non viene memorizzato** |
| **Funzionamento** (p. 85) | **enrollment automatico**; riconoscimento continuo **ogni 3–5 secondi** |
| **Feature** (p. 86) | **tastiera**: velocità e durata dei tasti, variazioni per sequenze particolari, pattern per gruppi comuni come parole o sequenze di controllo. **Touchscreen**: swipe, tap, pinch, zoom, digitazione, pressione. **Cursore**: percorsi, velocità, cambi di direzione, clic e loro relazioni |
| **Prestazioni e prezzo** (p. 87) | **non sono disponibili EER, DET, ROC**; venduto come **SaaS** a **3–9 dollari per utente al mese** |

> ⚠️ **Occhio alle promesse.** La p. 86 annota che queste feature «sono sufficienti per garantire il riconoscimento», ma la p. 87 ricorda che il produttore **non pubblica** EER, DET né ROC. Secondo le best practices di testing (Lezione 10), un'affermazione di accuratezza senza metriche misurate su una popolazione dichiarata **non è verificabile**: prima di adottare il prodotto va testato sul proprio scenario.

<!-- SOURCE_VISUAL id="TAB16-V33" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="81" type="schema" description="Autenticazione continua da terminale con tastiera e mouse e fusione a livello di score" reason="Schema a blocchi dell'approccio multimodale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB16-V34" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="85" type="schermata" description="Plurilock: enrollment automatico e riconoscimento continuo ogni 3-5 secondi" reason="Andamento del rischio mostrato nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **13. Biometria dai dati di processo**

_(slide pp. 88–92)_

**Quando la business intelligence diventa minaccia biometrica** (p. 88).

**Il caso Amazon France** (pp. 89–90):

- nel **2024** la **CNIL** (*Commission Nationale de l'Informatique et des Libertés*, l'autorità francese per la protezione dei dati) ha sanzionato Amazon France per il **monitoraggio dei magazzinieri**, considerato eccessivamente invasivo;
- è un caso emblematico di **biometria comportamentale** che emerge in sistemi **formalmente non progettati come biometrici**.

**Che cosa veniva raccolto**: i **terminali barcode scanner** dei dipendenti generavano flussi continui di dati su:

- **tempi di inattività** tra una scansione e la successiva;
- **velocità e ritmo** di lavoro (articoli al minuto);
- **errori** operativi e deviazioni dagli standard;
- **numero e durata delle pause** spontanee;
- **pattern di manipolazione** di oggetti e pacchi.

**Il punto critico per la CNIL**: queste metriche permettevano di **inferire stati personali** del lavoratore, come:

- **stanchezza** o cali cognitivi;
- **difficoltà fisiche**;
- **stress** momentaneo;
- **problemi di salute** o giornate non alla massima produttività;
- **capacità di resistenza** allo sforzo continuativo.

Sono a tutti gli effetti **informazioni sensibili** sul comportamento e sulla condizione psicofisica. La CNIL ha definito questa capacità di deduzione **«intrusiva e sproporzionata»**.

> ⚠️ **Importo da verificare.** La slide indica una sanzione di **34 milioni di euro**. La decisione della CNIL contro Amazon France Logistique, resa pubblica nel gennaio 2024, è stata riportata come **32 milioni di euro**: conviene controllare il valore sulla fonte ufficiale.

**Da normali dati di processo a tracciamento biometrico** (pp. 91–92):

| Stadio | Esempi | Rischio |
|---|---|---|
| **1. Normali dati di processo** | timestamp di un'azione, durata di un evento, numero di errori, velocità di esecuzione, latenze tra operazioni, uso di un dispositivo | nessuno: servono alla «business intelligence» |
| **2. Prime aggregazioni** | tempi di inattività, velocità e ritmo, errori operativi, numero e durata delle pause, sequenze di azioni | ancora non pericolose **se anonimizzate** |
| **3. Tracciamento biometrico** | dati **aggregati nel tempo**, **confrontati** con valori attesi, **estratti per il singolo individuo**, **trasformati in indicatori comportamentali** | **problema di privacy**: fuori legge, **sanzioni** |

> 📌 **La lezione per il progettista.** Un sistema diventa biometrico non per il sensore che usa, ma per **ciò che permette di inferire su una persona identificata**. Le stesse metriche, lasciate aggregate e anonime, sono lecite; estratte per il singolo lavoratore e trasformate in indicatori di stato, diventano una forma di **biometria comportamentale** (Lezione 13, §16.1).

<!-- SOURCE_VISUAL id="TAB16-V35" source="Lezione_16_Flusso_Ottico_Gait_Face_Emozioni_ContinousAuth.pdf" page="92" type="diagramma di flusso" description="Da normali dati di processo, tramite selezione e aggregazione, al tracciamento biometrico fuori legge" reason="Frecce e raggruppamenti visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Flusso ottico** / **differenza tra frame** | vettori di spostamento con direzione e velocità / sola variazione di intensità |
| **Zona con dettagli** / **senza dettagli** | vettori calcolabili / problema dell'apertura, vettori indeterminati |
| **HOOF** / **HOG** | istogramma degli angoli del **movimento** pesato dalla magnitudine / istogramma degli angoli dei **gradienti** spaziali |
| **Ekman** / **Feldman Barrett** | emozioni innate, universali, leggibili / emozioni costruite, espresse diversamente |
| **Espressione** / **emozione** | ciò che il classificatore vede / lo stato interno, non garantito |
| **Static-based** / **dynamic-based** | un frame / sequenza con onset, apex, offset |
| **Classi fisse** / **open vocabulary** | sette etichette / descrizioni testuali confrontate per similarità di embedding |
| **Espressioni posate** / **spontanee** | prototipiche, facili / deboli, miste, difficili |
| **Accuratezza** / **inter-rater agreement** | contro le etichette / tetto imposto dal disaccordo umano |
| **WAR** / **UAR** | recall pesato per frequenza (accuratezza) / media semplice dei recall per classe |
| **Autenticazione silente di base** / **avanzata** | SIM e IP, verifica il dispositivo / comportamento passivo, verifica la persona |
| **Autenticazione una tantum** / **continua** | all'inizio della sessione / ripetuta per tutta la sessione |
| **Match score** / **$EER(t)$** | somiglianza con il genuino / separabilità dagli impostori nell'istante $t$ |
| **Dati di processo aggregati** / **tracciamento individuale** | business intelligence lecita se anonima / biometria comportamentale sanzionabile |

---

### **15. Sintesi della lezione**

_(slide p. 93)_

> ✅ **In sintesi.**
> - **Flusso ottico**: mappa delle velocità apparenti da due frame, che richiede dettagli; serve per rilevare oggetti in movimento e misurarne la velocità; feature **HOOF** invarianti a scala e direzione; applicazioni in videosorveglianza, movimenti facciali, tracking e antispoofing; **gait recognition** con STIP e re-identificazione tra camere.
> - **Emozioni**: il set di Ekman contro la visione costruttivista di Feldman Barrett (si classificano espressioni, non emozioni certe); pipeline face detection → preprocessing → classificazione statica, dinamica o multimodale audio-video; dataset posati e spontanei con validazione L(N)PO; inter-rater agreement sotto il 70% come soffitto; immagini statiche quasi risolte (RAF-DB 92,21%), video realistici ancora aperti (FERV39K WAR 53,65%); ROC con gli umani.
> - **Etica**: uso nelle risorse umane, accuse di discriminazione, «emotive imperialism», AI Act con divieto sul lavoro e a scuola.
> - **Autenticazione**: silente di base (SIM e IP) e avanzata (comportamento passivo); continua con IMU, ECG e PPG (Nymi, CardioWheel, Apple Watch); multimodale con trust variabile, $EER(t)$ e fusione neurale adattativa con la qualità; da terminale con Keystroke DNA e Plurilock (senza metriche pubblicate).
> - **Dati di processo**: il caso Amazon France mostra come aggregazione e individualizzazione trasformino la business intelligence in biometria comportamentale illecita.
