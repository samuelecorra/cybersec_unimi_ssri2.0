## **Lezione 23: Sistemi di visione intelligenti e hardware per il machine learning — classificazione, rilevamento, segmentazione; CPU, GPU, TPU, NPU, VPU**

La domanda di partenza è **che cosa può restituire un sistema di visione intelligente**: un’**etichetta**, un **box** o una **maschera**. La lezione definisce i quattro compiti della visione (classificazione, rilevamento, segmentazione semantica e per istanze) **a partire dall’uscita**, mostra perché un bounding box non basta per misurare un oggetto e come soluzioni classiche, profonde e ibride convivano. Segue la **segmentazione**: metodi classici, k-means sui pixel, il **Segment Anything Model** (SAM, SAM 2, SAM 3), le **reti completamente convolutive** con la convoluzione trasposta e **ResNet-50** come backbone per classificazione, estrazione di feature e segmentazione. Un caso d’uso completo: il **riconoscimento di auto da un drone**. L’ultima parte è dedicata all’**hardware**: CPU, GPU, TPU, NPU, VPU, la scelta tra **inferenza cloud e locale**, il ruolo di **memoria e quantizzazione**, la catena che porta un modello addestrato su un **dispositivo edge** (ONNX, TensorRT, OpenVINO) e come **misurare** latenza, throughput ed energia.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L23-web.pdf` (112 pagine, di cui 109–112 di appendice non d’esame). I segnaposto per le figure indicano la pagina del PDF. Il PDF dell’edizione precedente è conservato nella cartella. Le parti marcate «Outlook» descrivono prodotti e versioni che cambiano rapidamente: non sono d’esame, conta il principio.


### **0. Panoramica**


- **Domanda**: che cosa può restituire un sistema di visione intelligente? Un’**etichetta, un box o una maschera**.
- **Teoria**: classificazione, rilevamento, segmentazione; soluzioni **classiche, profonde e ibride**.
- **Metodo**: segmentazione (classica, k-means, SAM) e **reti completamente convolutive**.
- **Caso d’uso**: riconoscimento di oggetti da **UAV/droni**.
- **Tecnologia**: CPU, GPU, TPU, NPU, VPU; inferenza **cloud o locale**, **memoria e quantizzazione**.
- **Rilascio**: dal modello addestrato al **dispositivo edge**; OpenVINO, Edge Impulse.

![](imgs/Pasted%20image%2020261125175054.png)

---

### **1. Teoria: la classificazione delle immagini**


Da un’immagine a un’etichetta.

#### **1.1. La classificazione delle immagini**

Tre esempi: **ancora** (cane / gatto); **controllo qualità** (OK / difettoso); **linea di riciclaggio** (plastica, metallo, vetro).

**Input**: un’immagine $I(x,y)$, $H\times W\times 3$, dalla telecamera sopra la linea ⇒ **Output**: **un’etichetta per immagine** (OK/difettoso; o uno di $C=3$ materiali), **nessuna posizione**.

![](imgs/Pasted%20image%2020261125175112.png)

#### **1.2. I compiti della visione intelligente**

![](imgs/Pasted%20image%2020261125174934.png)

#### **1.3. Quattro test veloci**

**Test 1**: che compito di visione intelligente è questo? → **SEGMENTAZIONE PER ISTANZE!** Ogni oggetto (1, 2, 3) ha **la propria maschera**.

![](imgs/Pasted%20image%2020261125175302.png)

**Test 2**: a quale compito corrisponde? → **SEGMENTAZIONE SEMANTICA!** Tutte pecore qui… (**una classe, un colore**); l’area del cane (un’altra classe).

![](imgs/Pasted%20image%2020261125175318.png)

**Test 3**: che compito è? → **RILEVAMENTO DI OGGETTI** (*object detection*). Ci sono solo i cosiddetti **bounding box**: solo box.

![](imgs/Pasted%20image%2020261125175334.png)

**Test 4**: che compito è? → **CLASSIFICAZIONE DELL’IMMAGINE**. In uscita c’è **una sola classe = pecora**.

![](imgs/Pasted%20image%2020261125175407.png)

#### **1.4. Il compito è definito dalla sua uscita**

**Ingresso comune a tutti i compiti**: un’immagine RGB $I(x,y)$ di dimensione $H\times W\times 3$ (per esempio $224\times 224\times 3$). **Ciò che cambia è l’uscita**:

| Compito | Uscita | Tipo / forma | Test |
|---|---|---|---|
| **classificazione** | **un’etichetta di classe** per l’intera immagine (con punteggi) | $\hat{y}\in\{1,\dots,C\}$, $p\in[0,1]^C$ | #4: «pecora» (0,6) |
| **rilevamento** | **un box, una classe e un punteggio per oggetto** | $\{(c_i,b_i,s_i)\}_{i=1}^N$, $b_i=(x_1,y_1,x_2,y_2)$ | #3: box |
| **segmentazione semantica** | **una classe per pixel**; istanze non separate | mappa di etichette $L(x,y)\in\{1,\dots,C\}$, $H\times W$ | #2: tutte le pecore blu |
| **segmentazione per istanze** | **una maschera binaria e una classe per oggetto** | $\{(c_i,M_i)\}_{i=1}^N$, $M_i\in\{0,1\}^{H\times W}$ | #1: pecora 1, 2, 3 |

$C$ = numero di classi; $N$ = numero di oggetti trovati (**cambia da immagine a immagine**); $s_i\in[0,1]$ = punteggio di confidenza.

> 💡 **Quanta informazione per ogni uscita (nota didattica).** Con $C=10$ classi su un’immagine $224\times 224$: la classificazione restituisce 10 numeri; il rilevamento $6$ numeri per oggetto (4 coordinate, classe, punteggio); la segmentazione semantica $224\cdot 224=50\,176$ etichette; quella per istanze una maschera di 50 176 bit per oggetto. Più ricca è l’uscita, più costose sono le etichette di training: annotare un box richiede secondi, una maschera minuti. È il motivo dell’importanza di SAM (§3.8).

#### **1.5. La classificazione diretta dell’immagine**

**Input**: un’immagine $I(x,y)$, $224\times 224\times 3$ (RGB) → **classificatore d’immagini** $f(I)$, per esempio una CNN → **punteggi**: cane 0,92, gatto 0,05, volpe 0,02 → `out = rank(...)` = «**Cane**».

![](imgs/Pasted%20image%2020261125175434.png)

**Nessun’altra informazione sull’oggetto**: dove si trova? quanto è grande? **Input**: un’immagine $H\times W\times 3$ ⇒ **Output**: vettore di punteggi $p\in[0,1]^C$ ($C$ classi) e **un’etichetta**: la classe con il punteggio più alto.

#### **1.6. Classificazione diretta: l’esempio di AlexNet**

**Input**: immagine RGB ridimensionata a $256\times 256\times 3$ (la rete legge un ritaglio $224\times 224$) ⇒ **Output**: vettore softmax $p\in[0,1]^{1000}$, $\sum_kp_k=1$; l’etichetta è $\arg\max_kp_k$. **AlexNet**: CNN con **5 strati convolutivi + 3 completamente connessi**, **≈ 60 M parametri** (Lezione 21, §5.1).

![](imgs/Pasted%20image%2020261125175629.png)

> ⚠️ **227 o 224? (nota didattica).** La Lezione 21 indica un ingresso $227\times 227$: l’articolo originale scrive 224, ma con il passo 4 e il filtro $11\times 11$ la formula $O=\lfloor(N-k)/S\rfloor+1$ dà 55 solo con $N=227$. È un’incongruenza nota dell’articolo; le implementazioni usano 227 (o 224 con padding).

#### **1.7. Come si addestra un classificatore?**

Lo si addestra su **dataset pubblici molto grandi**, soprattutto per i sistemi con molti parametri. **ImageNet Large-Scale Visual Recognition Challenge** (> 14 M immagini, 1000 classi diverse) — **insieme facile**:

![](imgs/Pasted%20image%2020261125175649.png)

> ⚠️ **14 milioni o 1,28 milioni? (nota didattica).** ImageNet completo ha oltre 14 milioni di immagini in circa 21 000 categorie; la *challenge* ILSVRC ne usa un sottoinsieme di **1000 classi e 1,28 milioni di immagini di training** (le cifre delle Lezioni 21 e 22). La slide combina le due cifre.

#### **1.8. ImageNet — insieme difficile**

**Insieme difficile** (termine usato in questa lezione, **non una partizione ufficiale** di ImageNet): classi la cui etichetta è **ambigua o sottile anche per una persona**.

**Perché sono difficili**:

- l’etichetta nomina **un materiale o una parte** (velluto, muso);
- oggetti **piccoli, parziali o in mezzo ad altri**;
- **classi simili** (*fine-grained*);
- **più oggetti, una sola etichetta**.

![](imgs/Pasted%20image%2020261125175753.png)

- **Utile per**: pre-addestramento e fine-tuning (dopo aver selezionato le classi che servono) e per imparare **feature generali** (bordi, tessiture, forme) che **si trasferiscono** ad altri compiti.
- **Non per l’uso industriale diretto**: categorie web generiche, **non i pezzi o i difetti di una fabbrica**.

---

### **2. Teoria: il rilevamento di oggetti**


Dov’è l’oggetto? Il bounding box.

#### **2.1. Il rilevamento di oggetti complesso**

**Oggetti sovrapposti**; **molti oggetti di classi diverse**. Negli ambienti reali gli oggetti **possono sovrapporsi**: il modello deve **riconoscere una forma anche da una sua parte** (si vedano le tecniche *masked* della lezione precedente, Lezione 22, §1.14).

![](imgs/Pasted%20image%2020261125175837.png)

> 💡 **Il limite della NMS (nota didattica).** La NMS (*Non-Maximum Suppression*, Lezione 21, §5.10) elimina i box che si sovrappongono troppo al box con punteggio più alto. Con oggetti **davvero sovrapposti**, come due persone una davanti all’altra, i loro box hanno IoU alta e la NMS rischia di **eliminarne uno**: in scene affollate questa soppressione può cancellare un oggetto reale. Varianti come la Soft-NMS abbassano il punteggio invece di eliminare il box.

#### **2.2. Il rilevamento di oggetti: il bounding box**

**Input** $I(x,y)$ → **rilevatore di oggetti** (per esempio un rilevatore CNN) → **output**: $\{x_1,y_1,x_2,y_2\}$ + classe «cane» + punteggio. Il box ha **lati paralleli agli assi dell’immagine**, con vertici $(x_1,y_1)$ e $(x_2,y_2)$.

![](imgs/Pasted%20image%2020261125175902.png)

> ⚠️ **Il box contiene il cane e molto sfondo**: è **quasi impossibile ricavare misure** (lunghezza, area, contorno) da un box.

#### **2.3. Il rilevamento non è la segmentazione!**

![](imgs/Pasted%20image%2020261125175911.png)

#### **2.4. Un esempio di rilevamento: persone nelle immagini**

- **Cantieri industriali**: **box gialli**: persone che indossano **gilet ad alta visibilità**.
- **Sorveglianza**: **box verde**: rilevamento; **maschera rossa**: segmentazione.

![](imgs/Pasted%20image%2020261125175927.png)

> ⚠️ **Sorveglianza e AI Act (nota didattica).** Rilevare **persone** (contarle, verificare il gilet) è diverso dall’**identificarle**: il riconoscimento biometrico in tempo reale in spazi accessibili al pubblico è vietato dall’AI Act salvo eccezioni strette, e la videosorveglianza sul luogo di lavoro richiede accordi e garanzie per i lavoratori (Lezione 2; Tecniche biometriche, Lezione 18). Un sistema «gilet sì/no» si progetta senza memorizzare volti.

#### **2.5. Stima della posa articolata: OpenPose**

**OpenPose** è un sistema **open source in tempo reale** per la **stima della posa 2D di più persone**, compresi i punti chiave di **corpo, piedi, polsi/mani e volto**. Cantiere industriale: **box e scheletro**.

**Input**: un’immagine RGB con diverse persone ⇒ **Output**: per persona, **$K$ punti chiave** $(x_k,y_k,\text{confidenza})$, per esempio $K=25$. github.com/CMU-Perceptual-Computing-Lab/openpose

![](imgs/Pasted%20image%2020261125180002.png)

> 💡 **Un uso di sicurezza (nota didattica).** Con 25 punti chiave per persona si può riconoscere una postura a rischio (schiena piegata mentre si solleva un carico) o una persona a terra, senza bisogno di identità: è un’uscita più ricca del box ma ancora molto compatta (75 numeri per persona).

#### **2.6. Classificazione/rilevamento di oggetti: ML classico contro deep learning**

![](imgs/Pasted%20image%2020261125180053.png)

#### **2.7. Una terza opzione: le soluzioni ibride**

1. **Candidati classici**: i **rilevatori di blob** propongono **regioni d’interesse** (box gialli). I rilevatori di blob indicano **dove l’immagine risponde a un kernel specifico** (si vedano le lezioni sulla «similarità», Lezioni 8–10).
2. **Ogni candidato → un classificatore.**

**EdgeAI molto compatta** (ibrida) contro **EdgeAI di medie dimensioni** (**solo la parte profonda**: la CNN impara da sé le feature).

![](imgs/Pasted%20image%2020261125180109.png)

> 💡 **Perché l’ibrido è compatto (nota didattica).** Un rilevatore di blob è una convoluzione con un filtro fisso (per esempio un Laplaciano di gaussiana) seguita da una ricerca di massimi: costa poco e non ha pesi da imparare. La CNN, che è la parte costosa, guarda solo le poche regioni candidate invece di tutta l’immagine. Lo stesso principio torna nel caso d’uso del drone (§6).

#### **2.8. La misura degli oggetti: un processo in tre passi**

**1. Rilevamento dell’oggetto** (immagine $I(x,y)$ → box $(x_1,y_1,x_2,y_2)$) → **2. Segmentazione dell’oggetto** (maschera $M\in\{0,1\}^{H\times W}$) → **3. Misure** (lunghezza in mm).

![](imgs/Pasted%20image%2020261125180153.png)

**La penna giace in diagonale**: il suo box è **molto più grande della penna**, quindi **i lati del box non danno la sua lunghezza**. **La lunghezza viene dalla regione segmentata**: numero di pixel lungo l’oggetto × dimensione del pixel, **dopo la calibrazione della telecamera**. Per esempio $400\ \text{px}\times 0{,}25\ \text{mm/px}=100$ mm. Giallo: bounding box; verde: regione segmentata; blu: lunghezza misurata (2,9 in; 1,0 in).

> 💡 **Box ruotato e calibrazione (nota didattica).** Dalla maschera si può calcolare il **rettangolo di area minima ruotato** (per esempio con `cv2.minAreaRect`), il cui lato lungo è la lunghezza della penna in pixel. La calibrazione (Lezione 24, visione industriale) dà i mm per pixel nel piano dell’oggetto, misurando un riferimento noto; vale solo se l’oggetto sta a quella distanza e la lente non distorce. 2,9 in e 1,0 in ≈ 74 mm e 25 mm.

#### **2.9. Misure degli oggetti nelle applicazioni industriali**

**Quando i pezzi non si possono collocare su uno sfondo adatto** (controllato, a contrasto) e **si toccano o si sovrappongono**, il **pattern matching tradizionale non basta**. Servono sistemi, **anche 3D**, che funzionino **anche da una parte di un’etichetta o da un modello dell’oggetto**.

![](imgs/Pasted%20image%2020261125180233.png)

#### **2.10. Segmentazione e misura: il bin picking**

L’ambiente industriale ha **peculiarità non banali**… Tra i compiti **più difficili** della visione industriale moderna: il **bin picking di oggetti sovrapposti** per un robot: **deve capire la visibilità parziale e la forma 3D dell’oggetto per afferrarlo**.

![](imgs/Pasted%20image%2020261125180246.png)

> 💡 **Perché 2D + 3D (nota didattica).** L’immagine 2D dice **che cosa** c’è (classificazione e segmentazione del pezzo visibile); un sensore 3D (luce strutturata, stereo, tempo di volo) dice **a che profondità** e con quale **orientamento**. Il robot ha bisogno della posa completa (6 gradi di libertà) per scegliere il punto di presa senza urtare gli altri pezzi.

---

### **3. Teoria: la segmentazione degli oggetti**


Dal box alla regione: misure reali.

#### **3.1. Segmentazioni generali**

![](imgs/Pasted%20image%2020261125180325.png)

#### **3.2. Esempi di applicazioni ambientali e veicolari**

**Droni**: fotogrammi da UAV (input) → **segmentazione e misure**. **Diabrotica del mais** (larva e coleottero); **mappa della vegetazione: aree danneggiate**. Esempio di **un’infestazione di diabrotica del mais**: immagini statiche e video aiutano l’agricoltore o il fornitore di servizi per le colture a **individuare dove si verifica il danno**; i risultati vengono integrati nei **sistemi informativi geografici (GIS)**.

![](imgs/Pasted%20image%2020261125180607.png)

**Satelliti e aerei**: immagine satellitare **multibanda** $H\times W\times B$ (per esempio **Sentinel-2**: $B=13$ bande, 443–2190 nm, 10–60 m/pixel) → **sistema di visione intelligente** (segmentazione e misure) → **mappa al suolo**: campi segmentati e misurati (colore = valore misurato). **Sentinel-2 (ESA)**: 13 bande spettrali, larghezza dell’andata 290 km, rivisita ogni 5 giorni con due satelliti. Uscite tipiche: mappe di copertura del suolo e delle colture, confini e aree dei campi, mappe di alluvioni o di aree bruciate (prospettiva, non d’esame).

![](imgs/Pasted%20image%2020261125180631.png)

> 💡 **Il canale è una dimensione (nota didattica).** Una CNN per immagini multibanda è identica a quella per RGB, con $C=13$ canali d’ingresso invece di 3: il primo strato ha filtri $3\times 3\times 13$ (Lezione 21, §3.12). Le bande nell’infrarosso vicino distinguono la vegetazione sana (che lo riflette molto) da quella danneggiata meglio di qualsiasi canale visibile.

**Veicoli autonomi: segmentazione semantica**: un attraversamento pedonale; persone, auto e strada ricevono **classi diverse**; **una classe per pixel**: strada, marciapiede, edificio, veicolo…

![](imgs/Pasted%20image%2020261125180714.png)

#### **3.3. Il veicolo autonomo: dall’immagine al freno**

**Immagine della telecamera** $I(x,y)$ → **segmentazione semantica** (mappa $L(x,y)$: una classe per pixel) → **analisi delle aree** (area in pixel di ogni regione, per esempio i pedoni) → **riconoscimento di pedoni a distanza ravvicinata** (grande area di pedone ⇒ sono vicini) → **Frena!**

![](imgs/Pasted%20image%2020261125180729.png)

> ⚠️ **Un’euristica didattica (nota didattica).** «Area grande = vicino» funziona per oggetti di dimensione nota (una persona), ma un bambino vicino e un adulto lontano possono avere la stessa area. I sistemi reali stimano la distanza con radar, LiDAR o stereo e la combinano con la segmentazione: è la fusione multisensore.

#### **3.4. Applicazioni della segmentazione: esempi**

**Magazzino industriale**: scaffale, scatola, pallet, carrello elevatore…

![](imgs/Pasted%20image%2020261125184745.png)

**Altri esempi di applicazione**:

- **imaging medico**: delimitare strutture (tumori, organi, vasi sanguigni) per diagnosi o pianificazione chirurgica;
- **riconoscimento di oggetti**: segmentando un’immagine nelle sue parti costitutive, gli algoritmi possono **riconoscere e classificare meglio** gli oggetti;
- **riconoscimento facciale**: isolare i volti dallo sfondo, migliorandone l’accuratezza;
- **videosorveglianza**: seguire oggetti o persone nei fotogrammi successivi;
- **analisi di documenti**: separare il testo dallo sfondo nei documenti scansionati o nelle applicazioni OCR;
- **biometria**: nel riconoscimento di impronte o iride, isolare le feature rilevanti;
- **realtà aumentata**: separare gli oggetti in primo piano dallo sfondo in tempo reale, per sovrapporre contenuti digitali al mondo reale;
- **computer grafica**: modificare immagini o video, per esempio cambiando lo sfondo;
- **monitoraggio della fauna**: identificare e seguire animali in immagini o video per ricerca e conservazione;
- **ispezione industriale**: sistemi di controllo qualità che identificano difetti nei prodotti;
- **moda**: prova virtuale, in cui i capi vengono segmentati e sovrapposti all’immagine dell’utente;
- **robotica**: riconoscere oggetti dell’ambiente e interagire con essi.

#### **3.5. La «definizione» di segmentazione**

- **Segmentazione**:
  - **dividere o separare un’immagine in regioni**;
  - per facilitare il **riconoscimento**, la **comprensione** e l’elaborazione delle **regioni d’interesse** (ROI).
- **Problema mal posto**: **la definizione di regione dipende dal contesto**.

**Input**: un’immagine $I(x,y)$ ⇒ **Output**: una **partizione in regioni**: una mappa di etichette $L(x,y)\in\{1,\dots,R\}$ o un insieme di maschere.

![](imgs/Pasted%20image%2020261125184828.png)

![](imgs/Pasted%20image%2020261125184837.png)

> 📌 **Definizione.** La **segmentazione suddivide un’immagine nelle sue regioni o oggetti costitutivi**; **il livello di dettaglio dipende dal problema**.

> 💡 **«Mal posto» in pratica (nota didattica).** Un’auto è una regione, o lo sono carrozzeria, ruote e finestrini? Per contare le auto basta la prima; per un’officina serve la seconda. Non esiste una segmentazione «giusta» indipendente dal compito, come non esiste un clustering giusto (Lezione 19, §7.3). È ciò che rende utili i modelli *promptable*: il prompt dice al modello quale livello di dettaglio si vuole.

**Due compiti vicini**; **tassonomia di base**:

![](imgs/Pasted%20image%2020261125184848.png)

![](imgs/Pasted%20image%2020261125184856.png)

#### **3.6. Metodi classici e altri metodi (per riferimento)**

| **Metodi classici** | **Altri metodi** |
|---|---|
| **conoscenza globale**: sogliatura; metodi basati sull’istogramma; metodi basati sulla compressione | metodi **split-and-merge** |
| **rilevamento dei bordi** | **metodi di clustering** (colore, intensità, tessitura, talvolta coordinate spaziali): **k-means**; **misture gaussiane (GMM)**; **Mean Shift** |
| **region growing**: modelli a contorno attivo («**Snakes**»); **trasformata watershed** | metodi basati su **equazioni alle derivate parziali** |
| **segmentazione basata su modelli**: modelli deformabili (Snakes) | metodi di **partizione di grafi** |
| **segmentazione multiscala**: segmentazione gerarchica di segnali 1D; *primal sketch* | **segmentazione semi-automatica** |
| | **segmentazione con reti neurali**: deep learning (slide successive) |

Un esempio di metodo region growing (Snake).

![](imgs/Pasted%20image%2020261125184932.png)

> 💡 **La soglia di Otsu (nota didattica).** Il più semplice metodo a «conoscenza globale» sceglie una soglia $t$ sull’istogramma dei livelli di grigio e divide i pixel in due classi. Il metodo di Otsu sceglie la $t$ che **massimizza la varianza tra le classi**:
> $$\sigma_B^2(t)=\omega_0(t)\,\omega_1(t)\,\big[\mu_0(t)-\mu_1(t)\big]^2,$$
> dove $\omega_0,\omega_1$ sono le frazioni di pixel sotto e sopra la soglia e $\mu_0,\mu_1$ le loro medie. Funziona bene quando l’istogramma ha **due picchi** separati (pezzo scuro su nastro chiaro), male con illuminazione non uniforme. È il criterio di Fisher della LDA (Lezione 17) in una dimensione.

#### **3.7. Segmentazione delle immagini con il k-means (non supervisionata)**

- Il **clustering k-means** si può usare per segmentare le immagini **raggruppando i pixel per similarità di colore**, ottenendo **regioni distinte basate sul colore**.
- **Molto semplice**, ma possono verificarsi **sovra-segmentazione o sotto-segmentazione**.

**Algoritmo (Lloyd)**: scegliere $K$ centroidi di colore iniziali; **assegnare ogni pixel al centroide più vicino**; **ricalcolare ogni centroide come media dei suoi pixel**; ripetere finché l’assegnazione non cambia più (Lezione 19, §7.8).

**Input**: $N=H\cdot W$ vettori di pixel $x_i\in\mathbb{R}^3$ (R, G, B) e il numero di cluster $K$ (per esempio $K=5$) ⇒ **Output**: mappa di etichette $L(x,y)\in\{1,\dots,K\}$ e **$K$ colori centroide**.

![](imgs/Pasted%20image%2020261125185004.png)

> ⚠️ **Il k-means ignora la posizione (nota didattica).** Due pixel dello stesso colore ai capi opposti dell’immagine finiscono nello stesso cluster: la «regione» può essere fatta di frammenti sparsi (sovra-segmentazione in senso spaziale). Aggiungere le coordinate $(x,y)$, scalate, al vettore di ogni pixel ($\mathbb{R}^5$) favorisce regioni compatte; è l’idea di Mean Shift e dei superpixel. Con $H=W=1000$ il k-means lavora su un milione di punti: il costo $O(tNKd)$ resta lineare (Lezione 19, §7.10).

#### **3.8. Miglioramenti recenti: il Segment Anything Model (SAM)**

**Un metodo di deep learning, di Meta AI.**

- **Caratteristiche principali**:
  - un sistema di segmentazione **promptable** (guidato da un prompt);
  - con **generalizzazione zero-shot** a oggetti e immagini non familiari, **senza bisogno di addestramento aggiuntivo** (**11 M immagini, oltre 1 miliardo di maschere**).
- SAM ha imparato **una nozione generale di che cosa sono gli oggetti** → questa comprensione permette la generalizzazione zero-shot.
- Può generare un segmento di un fotogramma **in circa 50 ms su un PC**.

**Input**: un’immagine e un **prompt** (punto, box, maschera o testo) ⇒ **Output**: una o più **maschere valide** $M_j\in\{0,1\}^{H\times W}$ con un **punteggio di confidenza** ciascuna.

![](imgs/Pasted%20image%2020261125185019.png)

#### **3.9. SAM: la segmentazione promptable**

I ricercatori di Meta AI hanno addestrato SAM a **restituire una maschera di segmentazione valida per qualsiasi prompt**: **punti di primo piano/sfondo**, un **box** o una **maschera approssimativi**, **testo libero**, o in generale qualsiasi informazione che indichi **che cosa segmentare** in un’immagine. Qui il prompt d’ingresso è il **bounding box**.

![](imgs/Pasted%20image%2020261125185044.png)

> 💡 **Rilevamento + SAM = segmentazione per istanze (nota didattica).** Un rilevatore come YOLO dà i box; passando ogni box a SAM come prompt si ottiene una maschera per oggetto, cioè una segmentazione per istanze, senza addestrare un modello di segmentazione sui propri dati. È la catena «rilevamento → segmentazione → misura» del §2.8 resa automatica.

**SAM: l’architettura della segmentazione promptable.**

![](imgs/Pasted%20image%2020261125185116.png)

#### **3.10. SAM = backbone pre-addestrato + testa leggera**

Richiamo dalle Lezioni 21 e 22: **un backbone pre-addestrato trasforma l’ingresso in una rappresentazione $z$; una piccola testa per il compito trasforma $z$ nell’uscita del compito.**

Immagine $I(x,y)$ ($1024\times 1024\times 3$) → **encoder dell’immagine (ViT)**, **pesante, una volta per immagine** → **embedding $z$** ($64\times 64\times 256$) → **encoder del prompt + decoder delle maschere** (**leggero**) → maschere $M_j$ + punteggi.

- **Il prompt** (punti, box, maschera, testo) **entra solo nella testa leggera**: **un’esecuzione dell’encoder, poi molti prompt in tempo reale** (circa **50 ms** ciascuno, anche **in un browser web**).
- **Stessa divisione** dello schema dell’architettura: **encoder pesante sul server, decoder leggero sul client**.

SAM: encoder dell’immagine **ViT-H pre-addestrato con MAE** (Lezione 22, §1.14); encoder del prompt e decoder delle maschere ≈ 50 ms in un browser.

> 💡 **La compressione dell’embedding (nota didattica).** $1024/64=16$: ogni posizione dell’embedding riassume una patch $16\times 16$ dell’immagine (il ViT lavora a patch di 16 pixel). $64\cdot 64\cdot 256=1\,048\,576$ valori, circa un terzo dei $1024\cdot 1024\cdot 3=3\,145\,728$ dell’immagine: l’embedding si può trasmettere una volta al client, e ogni nuovo clic dell’utente costa solo il decoder.

#### **3.11. SAM in Colab con metaseg**

**Input**: un file d’immagine (`puppies.jpg`), il tipo di modello (`vit_l`) e la **griglia di punti di prompt per lato** ⇒ **Output**: **una maschera per oggetto trovato**, salvata come immagine codificata a colori (`output.png`): un colore per maschera.

![](imgs/Pasted%20image%2020261125185138.png)

![](imgs/Pasted%20image%2020261125185150.png)

> 💡 **La «griglia di punti» (nota didattica).** In modalità automatica SAM non riceve un prompt dall’utente: lo si interroga con una griglia regolare di punti (per esempio $32\times 32=1024$ punti), e per ognuno restituisce le maschere candidate; poi si eliminano i duplicati (con una NMS sulle maschere) e quelle con punteggio basso. `vit_l` è l’encoder di dimensione intermedia, tra `vit_b` e `vit_h`.

#### **3.12. SAM 2 (video e immagini)**

**SAM 2** è un **miglioramento significativo** rispetto al modello originale. È il **primo modello unificato per la segmentazione in tempo reale di immagini e video**.

**Input**: un video (sequenza di fotogrammi) e un prompt su un fotogramma ⇒ **Output**: una ***masklet***: **la maschera dell’oggetto in ogni fotogramma**. Ravi et al., *SAM 2: Segment Anything in Images and Videos*, ICLR 2025 (arXiv:2408.00714).

![](imgs/Pasted%20image%2020261125185225.png)

> 💡 **La memoria (nota didattica).** SAM 2 aggiunge a SAM una **memoria** dei fotogrammi precedenti: la maschera del fotogramma $t$ si calcola tenendo conto delle maschere già prodotte, così l’oggetto viene seguito anche se si gira, si sposta o viene coperto per un momento. È ciò che distingue il tracking (stesso oggetto nel tempo) dal rilevamento ripetuto fotogramma per fotogramma.

#### **3.13. Lo stato dell’arte: SAM 3, segment anything con concetti**

- **Un prompt di concetto, non un clic**: una **breve espressione nominale** («autobus scolastico giallo»), **box d’esempio**, o entrambi ⇒ **ogni istanza corrispondente riceve la sua maschera**, seguita nei fotogrammi del video (i prompt di punto, box e maschera funzionano ancora).
- **Addestrato su SA-Co**: **5,2 M immagini annotate da persone** con **4 M espressioni nominali distinte**, più dati sintetici.
- **AP delle maschere zero-shot su LVIS: 47,0** (miglior risultato precedente 38,5).
- **Pesante**: circa **474 M parametri (3,4 GB)**: un **modello per server con GPU**, non un modello compatto per l’edge.

**Input**: immagine o video + prompt (espressione nominale, box, punto, maschera) ⇒ **Output**: **una maschera per ogni istanza corrispondente**. Carion et al., SAM 3, arXiv:2511.16719, 2025; docs.ultralytics.com/models/sam-3. **Prospettiva**: le versioni cambiano in fretta, **l’idea durevole è prompt → maschera**.

![](imgs/Pasted%20image%2020261125185247.png)

> 💡 **I numeri (nota didattica).** Con la regola del §7.10 i soli pesi occupano $474\cdot 10^6\times 4$ byte $\approx 1{,}9$ GB in FP32 (0,95 GB in FP16); i 3,4 GB dichiarati sono la dimensione del pacchetto distribuito, che può contenere più dei soli pesi. Con la regola del §7.10 un modello così non sta su una NPU da telefono. L’AP (*average precision*) di LVIS è una media su oltre 1000 categorie, molte rare: da 38,5 a 47,0 è un salto del 22% relativo.

> 💡 **Aggiornamento di versione (dall’audit del 20 novembre 2026).** Meta ha pubblicato **SAM 3.1** il 27 marzo 2026. È un aggiornamento per il tracking video multi-oggetto: introduce il multiplexing e Meta dichiara fino a 16 oggetti per passaggio, con un throughput di 32 fps su una singola H100 in una configurazione specifica. Questi risultati del produttore non vanno generalizzati a hardware o video diversi. La descrizione di SAM 3 resta utile per il principio dei prompt concettuali; quando si parla della versione aggiornata, si usa SAM 3.1. ([Documentazione Meta SAM 3](https://ai.meta.com/sam3/), [annuncio Meta SAM 3.1](https://ai.meta.com/blog/segment-anything-model-3/))

---

### **4. Teoria: la segmentazione semantica**


Ogni pixel riceve un’etichetta di classe.

#### **4.1. Segmentazione semantica (richiamo)**

- **Etichettare ogni pixel** dell’immagine con un’**etichetta di categoria**.
- **Non distinguere le istanze**, interessano solo i pixel.

![](imgs/Pasted%20image%2020261125190306.png)

#### **4.2. Classificare ogni pixel è una buona idea ma… non è praticabile**

![](imgs/Pasted%20image%2020261125190332.png)

**Perché non è praticabile**: ogni convoluzione lavora **alla risoluzione piena $H\times W$ con $D$ canali**, quindi **memoria e calcolo crescono come $D\cdot H\cdot W$ in ogni strato**. Immagini da Fei-Fei Li, Justin Johnson e Serena Yeung (Stanford).

> 💡 **Un conto (nota didattica).** Con $H=W=512$ e $D=256$ canali, una sola feature map occupa $512\cdot 512\cdot 256=67$ milioni di valori (268 MB in FP32); una rete di 20 strati così, con le attivazioni da conservare per la backpropagation, non sta in una GPU. Dimezzando la risoluzione a ogni blocco (il pooling della Lezione 21) il costo crolla: è il motivo dell’encoder/decoder.

#### **4.3. Segmentazione semantica: una soluzione completamente convolutiva**

**Input**: $I(x,y)$, $3\times H\times W$ ⇒ **Output**: punteggi $C\times H\times W$; l’$\arg\max$ sulle $C$ classi dà la mappa di etichette $L(x,y)$, $H\times W$.

![](imgs/Pasted%20image%2020261125222758.png)

> 📌 **Rete completamente convolutiva (FCN).** Solo strati di **convoluzione, pooling e upsampling**, così **l’uscita conserva la disposizione spaziale dell’ingresso**.

> 💡 **Addestrare e valutare la segmentazione (nota didattica).**
> - La funzione di costo è la **cross-entropy calcolata pixel per pixel** e mediata su tutti i pixel.
> - La metrica tipica è l’**IoU per classe**, mediata sulle classi (**mIoU**): per ogni classe si confrontano i pixel predetti con quelli veri, $\text{IoU}_c=\frac{|P_c\cap G_c|}{|P_c\cup G_c|}$.
> - L’accuratezza per pixel inganna quando lo sfondo domina, per la stessa ragione dei dataset sbilanciati (Lezione 14, §2): un modello che dice sempre «sfondo» su un’immagine con il 95% di sfondo ha il 95% di accuratezza.
> - La **U-Net** aggiunge connessioni dirette (*skip connection*) tra encoder e decoder alla stessa risoluzione, per recuperare i **dettagli dei bordi** persi nel downsampling.

#### **4.4. Upscaling: `nn.ConvTranspose2d()`, che cosa calcola**

La **convoluzione trasposta** = **upsampling apprendibile** usato nei decoder (autoencoder, U-Net, FCN). **Non annulla una convoluzione**; va nella direzione opposta **nella forma**: mappa piccola → mappa più grande.

1. **Scalare**: ogni pixel d’ingresso **moltiplica l’intero kernel $K$** (una matrice $k\times k$ di pesi appresi).
2. **Collocare**: la copia scalata viene messa nell’uscita **nella posizione del pixel**, spostata del passo $s$ per ogni passo dell’ingresso.
3. **Sommare**: **dove le copie si sovrappongono, i valori si sommano**.

**Esempio**: ingresso $2\times 2$, kernel $2\times 2$, passo $s=1$ ⇒ uscita $3\times 3$:

$$\text{ingresso}=\begin{bmatrix}0&1\\2&3\end{bmatrix},\quad K=\begin{bmatrix}4&1\\2&3\end{bmatrix}:\quad \underbrace{\begin{bmatrix}0&0&0\\0&0&0\\0&0&0\end{bmatrix}}_{0\cdot K}+\underbrace{\begin{bmatrix}0&4&1\\0&2&3\\0&0&0\end{bmatrix}}_{1\cdot K}+\underbrace{\begin{bmatrix}0&0&0\\8&2&0\\4&6&0\end{bmatrix}}_{2\cdot K}+\underbrace{\begin{bmatrix}0&0&0\\0&12&3\\0&6&9\end{bmatrix}}_{3\cdot K}=\begin{bmatrix}0&4&1\\8&16&6\\4&12&9\end{bmatrix}.$$

**Input**: feature map $D_{\text{in}}\times H_{\text{in}}\times W_{\text{in}}$ e kernel appresi $K$ ⇒ **Output**: mappa più grande $D_{\text{out}}\times H_{\text{out}}\times W_{\text{out}}$.

![](imgs/Pasted%20image%2020261125222820.png)

> 💡 **Verifica del valore centrale (nota didattica).** La cella centrale è coperta da tutte e quattro le copie: da $0\cdot K$ arriva 0; da $1\cdot K$ (collocata in alto a destra) l’elemento in basso a sinistra del kernel, $1\cdot 2=2$; da $2\cdot K$ (in basso a sinistra) l’elemento in alto a destra, $2\cdot 1=2$; da $3\cdot K$ (in basso a destra) l’elemento in alto a sinistra, $3\cdot 4=12$. Totale $0+2+2+12=16$.

#### **4.5. `nn.ConvTranspose2d()`: il passo fissa la dimensione d’uscita**

**Stesso ingresso e stesso kernel, passo $s=2$**: le copie ora sono **a due pixel di distanza**, quindi **non si sovrappongono** e l’uscita è più grande:

$$\begin{bmatrix}0&0&4&1\\0&0&2&3\\8&2&12&3\\4&6&6&9\end{bmatrix}.$$

$$H_{\text{out}}=(H_{\text{in}}-1)\,s-2p+k$$

(lo stesso per la larghezza, con $W$). $H_{\text{in}}$: dimensione d’ingresso (pixel); $s$: **passo**, la distanza tra le copie; $p$: **padding**, righe/colonne tolte da ogni bordo dell’uscita; $k$: dimensione del kernel. Nessuna dilatazione né *output padding*.

**Esempio**: $H_{\text{in}}=2$, $k=2$, $p=0$: con $s=1$, $H_{\text{out}}=(2-1)\cdot 1+2=3$; con $s=2$, $H_{\text{out}}=(2-1)\cdot 2+2=4$. **In un decoder, $k=2$, $s=2$ raddoppia la dimensione.**

![](imgs/Pasted%20image%2020261125223153.png)

![](imgs/Pasted%20image%2020261125223201.png)

> 💡 **In PyTorch e il caso $k=4,s=2,p=1$ (nota didattica).** `nn.ConvTranspose2d(in_channels=64, out_channels=32, kernel_size=2, stride=2)` porta una mappa $64\times 28\times 28$ a $32\times 56\times 56$. Con $k=4$, $s=2$, $p=1$: $H_{\text{out}}=(H_{\text{in}}-1)\cdot 2-2+4=2H_{\text{in}}$: raddoppia anche questa, ma con copie che si sovrappongono (meno artefatti «a scacchiera» di $k=2$). È la formula della convoluzione della Lezione 21 (§3.10) invertita: $H_{\text{in}}=\frac{H_{\text{out}}+2p-k}{s}+1$.

---

### **5. Teoria: ResNet come backbone generale**


Una rete molto usata, molte funzioni.

#### **5.1. ResNet-50 per la classificazione**

![](imgs/Pasted%20image%2020261125223253.png)

La rete pre-addestrata può classificare le immagini in **1000 categorie di oggetti**, come tastiera, mouse, matita e molti animali. **50 strati con pesi (49 conv + 1 FC)**, **25,6 M parametri**, **$3{,}8\times 10^9$ FLOP per immagine**. Feature map $H\times W\times C$ (ingresso $224\times 224\times 3$):

| conv1 | stadio 2 | stadio 3 | stadio 4 | stadio 5 | avg pool | FC |
|---|---|---|---|---|---|---|
| $112\times 112\times 64$ | $56\times 56\times 256$ | $28\times 28\times 512$ | $14\times 14\times 1024$ | $7\times 7\times 2048$ | $1\times 1\times 2048$ | 1000 |
| | ×3 blocchi | ×4 | ×6 | ×3 | | |

![](imgs/Pasted%20image%2020261125223244.png)

**Blocco residuo** (come nella Lezione 22): $y=x+F(x)$, **il gradiente ha un percorso diretto attraverso la scorciatoia**.

> ⚠️ **Nota**: in Keras il numero di parametri si ottiene con `model.count_params()`; **la figura di origine riporta `model.param_count()`**, che non esiste. Per ResNet-50 con lo strato finale `count_params()` restituisce 25 636 712.

#### **5.2. ResNet-50: dove sono le connessioni residue**

- **Ogni arco è una scorciatoia** attorno a un **blocco bottleneck** (conv $1\times 1$, $3\times 3$, $1\times 1$): $y=x+F(x)$. Sono disegnati due blocchi per stadio; le etichette danno il conteggio vero: $3+4+6+3=16$.
- **Arco continuo**: **scorciatoia identità, nessun peso**. **Arco tratteggiato**: **proiezione** (conv $1\times 1$, di solito con passo 2) perché $x$ e $F(x)$ abbiano **la stessa dimensione e si possano sommare**.
- **16 blocchi × 3 conv = 48, più la prima conv e la FC: 50 strati** (FC: 1000 uscite per ImageNet).

![](imgs/Pasted%20image%2020261125223402.png)

> 💡 **Il bottleneck (nota didattica).** Il primo $1\times 1$ riduce i canali (per esempio da 256 a 64), il $3\times 3$ lavora sui 64 canali, l’ultimo $1\times 1$ li riporta a 256: con 256 canali ovunque il $3\times 3$ costerebbe $9\cdot 256\cdot 256=589\,824$ pesi, con il bottleneck $256\cdot 64+9\cdot 64\cdot 64+64\cdot 256=69\,632$. È la stessa idea delle convoluzioni leggere di MobileNet (Lezione 21, §5.4).

#### **5.3. ResNet-50 per l’estrazione delle feature**

Si può usare ResNet-50 per **elaborare le feature** e poi **il proprio classificatore** per creare un compito nuovo.

Immagine $I(x,y)$ ($224\times 224\times 3$) → **backbone ResNet-50 senza l’ultimo strato FC** (pre-addestrato, pesi congelati) → **vettore di feature** $z\in\mathbb{R}^{2048}$ → **nuovo classificatore** (per esempio SVM o piccolo FC, addestrato sulle proprie classi) → etichetta $\hat{y}$ (le proprie $C$ classi). **Dentro il backbone**: stadi 1–5, poi average pool e flatten danno il vettore di feature a 2048 dimensioni.

![](imgs/Pasted%20image%2020261125223419.png)

#### **5.4. ResNet-50 → ResNet-50 + upscaling: la segmentazione**

![](imgs/Pasted%20image%2020261125223438.png)

**La convoluzione trasposta è un’operazione di upsampling apprendibile, non l’esatto inverso della convoluzione.** Con il **decoder** la rete diventa un’**architettura di segmentazione**: l’uscita è **una mappa simile a un’immagine, non un ID di classe**. «ResNet-50 con upscaling per la classificazione?» **NO!**

![](imgs/Pasted%20image%2020261125223452.png)

> 💡 **Lo stesso backbone, tre usi (nota didattica).** Con la testa FC: classificazione (1000 classi). Senza testa: estrazione di feature (2048 numeri, Lezione 22, §4.8). Con un decoder: segmentazione ($C\times H\times W$). L’encoder porta $224\to 7$ (fattore 32); il decoder deve risalire con 5 raddoppi ($7\to 14\to 28\to 56\to 112\to 224$), possibilmente con le skip connection della U-Net per recuperare i dettagli.

#### **5.5. La classificazione delle immagini con il DL**

| Modello | Anno | # Parametri | Top-1 (ImageNet) | Note |
|---|---|---|---|---|
| VGG-16 / VGG-19 | 2014 | 138M / 144M | ∼71,5% | pesante; senza skip connection |
| ResNet-50 | 2015 | 25,6M | ∼76,0% | blocchi residui |
| Inception-v3 | 2015 | 23,8M | ∼78,8% | blocchi conv. multi-percorso |
| EfficientNet B0–B7 | 2019 | 5,3M – 66M | 77,1% – 84,4% | *compound scaling* |
| EfficientNetV2 S–L | 2021 | 24M – 120M | 84,0% – 87,3% | addestramento più veloce |
| Vision Transformer (ViT-B) | 2020 | 86M | ∼85,0% | patch embedding |
| ConvNeXt (Base) | 2022 | 89M | ∼85,8% | CNN tipo transformer |
| ConvNeXt V2 | 2023 | 3,7M – 650M | 76,7% – 88,9% | masked autoencoding |
| MobileNetV3-L / V4-Conv-L | 2019 / 2024 | ∼5,4M / 31M | ∼75% / 82,9% | dispositivi mobili ed edge |
| SigLIP 2 (B/16, 224 px) | 2025 | 86M | 78,2%* | pre-addestramento immagine–testo |
| SigLIP 2 (So400m/14) | 2025 | 400M | 83,2%* | stesso, modello più grande |
| DINOv3 (ViT-7B) | 2025 | 7B | — | self-supervised; feature congelate + testa leggera |

**Righe del 2025**: *zero-shot (nessuna etichetta ImageNet nell’addestramento), **non confrontabili** con i valori supervisionati sopra. **Tendenza**: **un backbone pre-addestrato riusato con una testa leggera**. Prospettiva, valori illustrativi: il messaggio durevole è il **compromesso tra parametri (memoria, velocità) e accuratezza**. SigLIP 2 (arXiv:2502.14786); DINOv3 (arXiv:2508.10104).

> 💡 **Leggere la tabella (nota didattica).** VGG-16 ha 5,4 volte i parametri di ResNet-50 e 4,5 punti in meno: la dimensione non garantisce l’accuratezza, conta l’architettura. MobileNetV4-Conv-L (31M, 82,9%) supera ViT-B (86M, 85%) per efficienza ma non per accuratezza. Per scegliere un backbone industriale si guarda l’accuratezza **per unità di memoria e di latenza** sull’hardware di destinazione (§7).

---

### **6. Caso d’uso: il riconoscimento di oggetti da UAV/droni**


*Unmanned Aerial Vehicle* (UAV), aeromobile senza pilota.

#### **6.1. I droni nelle applicazioni**

![](imgs/Pasted%20image%2020261125223616.png)

![](imgs/Pasted%20image%2020261125223627.png)

#### **6.2. Amazon Prime Air**

- **USA**: certificazione **FAA Part 135** come vettore aereo (2020); servizio in **Texas e Arizona** (da novembre 2024), **Lockeford (California) chiuso nel 2024**.
- **Drone MK30**: pacchi fino a **5 lb (≈ 2,3 kg)**, consegna **in meno di due ore**.
- **Regno Unito**: voli dal centro di distribuzione di **Darlington** con un **permesso di prova** (luce diurna, bel tempo, volumi limitati); **il servizio ai clienti non è partito nel 2025** come indicato nella slide precedente.
- **Piano annunciato nel 2026**: consegna con droni in circa **500 città e paesi degli USA**.
- https://www.youtube.com/watch?v=ahkeyw-FUlQ
- **Prospettiva, non d’esame**: il punto durevole è la catena **rilevamento → visione → decisione su una piattaforma volante**.

aboutamazon.co.uk (12 gennaio 2026); thenextweb.com (19 agosto 2026).

![](imgs/Pasted%20image%2020261125223637.png)

#### **6.3. Il riconoscimento di oggetti da UAV: tre esempi**

**Dalla telecamera del drone il bersaglio cambia, il compito è lo stesso: trovare oggetti in un fotogramma aereo.**

1. **Segno d’atterraggio**: trovare il tappetino (esagono, «H») sotto il drone: restituire **posizione e orientamento**, per atterrare in sicurezza.
2. **Veicoli**: **rilevare ogni auto** a un incrocio (classe «Car»); seguirla fotogramma per fotogramma dà la sua **traiettoria**.
3. **Folle**: **segmentare le regioni coperte da persone** (crowd_1, crowd_2): **una maschera per folla**, poi **stimarne la dimensione**.

![](imgs/Pasted%20image%2020261125223653.png)

**Input**: un fotogramma aereo $I(x,y)$ dalla telecamera del drone ⇒ **Output**: per oggetto d’interesse, **una classe, una posizione** (box o maschera) e, nel tempo, **una traiettoria**.

> 💡 **Il segno d’atterraggio è visione classica (nota didattica).** Un marker noto, ad alto contrasto, si trova con il pattern matching o con i marker fiduciali (tipo ArUco), senza deep learning: è il caso in cui la soluzione classica è la più robusta e la più leggera (§2.7).

#### **6.4. Il riconoscimento di oggetti da UAV: progettiamo un drone per il controllo del traffico in tempo reale**

**Input**: fotogramma aereo $I(x,y)$ dalla telecamera del drone ⇒ **Output**: **le auto nel fotogramma**: **una regione (maschera) per auto rilevata**, pronta per essere **contata**.

![](imgs/Pasted%20image%2020261125223710.png)

#### **6.5. Passo 1: la segmentazione**

**Fotogramma aereo** → **segmentazione dell’immagine (mean shift)** → **regioni colorate** → **rimuovere le regioni grandi** → **auto candidate**.

![](imgs/Pasted%20image%2020261125223718.png)

Si è già fatta una segmentazione simile nelle lezioni precedenti: il **mean shift** raggruppa i pixel **per colore e posizione** in regioni. Qui **le regioni grandi** (strada, tetti, erba) **vengono scartate** e **quelle piccole restano come auto candidate**.

**Input**: fotogramma RGB aereo $I(x,y)$ dal drone ⇒ **Output**: mappa di etichette delle **regioni candidate** (solo regioni piccole), **una per possibile auto**.

#### **6.6. Passo 2: il progetto della CNN**

Si può usare un **classificatore tradizionale come una SVM** o **modelli profondi**.

**Input**: regione candidata ridimensionata a $224\times 224\times 3$ ⇒ **Output**: **vettore di feature della CNN** (per esempio **4096 valori**) → **classificatore** → «auto» / «non auto». **SVM** (*support vector machine*): classificatore lineare a **margine massimo** sul vettore di feature. **VGG16**: CNN a 16 strati.

![](imgs/Pasted%20image%2020261125223801.png)

> 💡 **Perché l’ibrido (nota didattica).** Un fotogramma $1920\times 1080$ esaminato a finestre scorrevoli di $64\times 64$ pixel con passo 16 richiederebbe
> $$\left(\frac{1920-64}{16}+1\right)\times\left(\frac{1080-64}{16}+1\right)=117\times 64=7488$$
> classificazioni con VGG16, per una sola scala. Se la segmentazione propone qualche decina di candidati, le chiamate alla CNN scendono di due ordini di grandezza. È ciò che rende possibile il **tempo reale** a bordo di un drone.

#### **6.7. La creazione del dataset di apprendimento della CNN**

**AUTO** / **NON AUTO**: una parte delle patch etichettate. **Fine-tuning**: ogni regione della segmentazione viene etichettata **AUTO** o **NON AUTO**. **La CNN pre-addestrata tiene congelati i suoi strati convolutivi** e **si affina solo la parte completamente connessa** su queste patch (Lezione 22, §4.5, opzione 1–2).

![](imgs/Pasted%20image%2020261125223853.png)

**Input**: patch etichettate $224\times 224\times 3$ (AUTO / NON AUTO) ⇒ **Output**: una rete che **classifica una patch nuova** come AUTO o NON AUTO.

> 💡 **Un vantaggio della segmentazione (nota didattica).** La segmentazione produce automaticamente anche le patch per il dataset: una persona deve solo dire «auto» o «non auto» su ciascuna, invece di disegnare box. Le patch «non auto» (cespugli, macchie d’ombra, cassonetti) sono gli **esempi negativi difficili**: proprio quelli che il classificatore deve imparare a scartare.

#### **6.8. I risultati**

**Regioni classificate come AUTO**; **auto marcate in rosso**; zoom. **Contorno rosso**: regione classificata come AUTO (il numero è **quante auto contiene**); **box gialli**: i pochi **errori residui**.

**Input**: fotogramma aereo $I(x,y)$ dal drone ⇒ **Output**: auto marcate nel fotogramma, **pronte per essere contate**.

![](imgs/Pasted%20image%2020261125223908.png)

> ⚠️ **Regioni con più auto (nota didattica).** Due auto parcheggiate a contatto possono finire nella stessa regione del mean shift: per contarle si stima il numero dall’area della regione divisa per l’area tipica di un’auto (a quella quota di volo), oppure si passa la regione a un rilevatore o a SAM (§3.9). Il numero dentro il contorno rosso è questa stima.

---

### **7. Teoria: l’hardware per il machine learning**


Dove gira il calcolo: CPU, GPU, TPU, NPU, VPU.

#### **7.1. Perché il machine learning è possibile?**

- **Memorie di massa**: **più dati disponibili** (dischi da TB, data center…).
- **Prestazioni più alte dei computer**: **memorie più grandi** per gestire i dati; **maggiore potenza di calcolo** per addestrare e perfino per l’**apprendimento online**.
- **Esempi**: NVIDIA RTX / A100 / H100 / Jetson / B200; Apple Neural Engine / NPU degli Intel Core Ultra.

![](imgs/Pasted%20image%2020261125223936.png)

#### **7.2. L’hardware: quando e dove è più rilevante?**

![](imgs/Pasted%20image%2020261125224000.png)

#### **7.3. CPU, GPU, TPU, NPU, VPU**

- **CPU**: *Central Processing Unit*, unità centrale di elaborazione;
- **GPU**: *Graphics Processing Unit*, unità di elaborazione grafica;
- **TPU**: *Tensor Processing Unit*, unità di elaborazione tensoriale;
- **NPU**: *Neural Processing Unit*, unità di elaborazione neurale;
- **VPU**: *Vision Processing Unit*, unità di elaborazione della visione.

![](imgs/Pasted%20image%2020261125224018.png)

| Unità | Caratteristiche | Uso tipico |
|---|---|---|
| **CPU** | processore **general-purpose**, **alta flessibilità, basso parallelismo** | **prototipi, pre-elaborazione, controllo del sistema**; **non adatta** all’addestramento del deep learning né all’inferenza biometrica complessa in tempo reale |
| **GPU** | **parallelismo massiccio**, **alta banda di memoria** | **scelta standard per addestrare le CNN** biometriche; anche inferenza a batch o in tempo reale su sistemi desktop/server (data center: NVIDIA **A100** (2020), **H100** (2022), **B200** (2024)) |
| **TPU** | acceleratore **specializzato per le operazioni tensoriali** | efficiente per addestramento/inferenza su **modelli standardizzati**; **flessibilità limitata**, tipicamente **orientata al cloud**; Google TPU v5e/v5p, Trillium (v6e), Ironwood (v7) in Google Cloud |
| **NPU** | acceleratore per l’**inferenza neurale a bassa potenza** | ideale per la **biometria embedded e in tempo reale**; ottimizzata per **latenza, consumo e privacy sul dispositivo**; nei chip dei telefoni: Apple Neural Engine (iPhone), Qualcomm Hexagon (Snapdragon), Google Tensor (Pixel), Samsung Exynos, Huawei Kirin |
| **VPU** | acceleratore **specializzato per l’elaborazione visiva** e la visione artificiale | ottimizzata per **pre-elaborazione, estrazione di feature e inferenza su video/immagini**; ideale per la **biometria visiva embedded**: volto, gesti, conteggio di persone; **compromesso tra GPU e NPU**: buona efficienza energetica e **pipeline video dedicate** |

> ⚠️ **Gli esempi biometrici (nota didattica).** Le slide su CPU, GPU, NPU e VPU usano come esempio la **biometria** (riconoscimento di volti e impronte), un residuo del materiale condiviso con il corso di Tecniche biometriche: valgono per qualsiasi applicazione di visione (ispezione industriale, conteggio, sicurezza).

#### **7.4. GPU contro CPU**

**Tempo di addestramento su ImageNet: AlexNet e ResNet-50.**

| Rete | Addestramento | Hardware | Tempo |
|---|---|---|---|
| AlexNet | end-to-end, 100 epoche | 1 GPU (K20) + CPU | ≈ 144 h |
| AlexNet | end-to-end, 100 epoche | cluster di 1024 CPU | 11 min |
| ResNet-50 | end-to-end, 90 epoche | 1 × V100 (FP32) | ≈ 92 h |
| ResNet-50 | end-to-end, 90 epoche | 1 × A100 (misto) | ≈ 14 h |
| ResNet-50 | end-to-end, 90 epoche | 8 × A100 (misto) | ≈ 3 h |
| ResNet-50 | fine-tuning, 10 epoche | 1 × A100 (misto) | ≈ 40 s (stima) |

- **End-to-end**: tutti i pesi imparati da valori casuali sulle **1,28 M immagini di ImageNet**. **Fine-tuning**: partire dalla ResNet-50 addestrata, addestrare su un dataset piccolo; stima = **10 epoche su 10 000 immagini ($10^5$ immagini) a 2470 immagini/s** (A100). **Misto**: aritmetica a 16 bit dove è sicuro.
- **In breve… per i progetti grandi serve una GPU!**
- **Prestazioni (\*)**: circa **5–30 volte più veloce** (10 volte tipico).
- **Efficienza energetica (\*\*)**: circa **3–10 volte migliore** (5 volte tipico).

AlexNet: arXiv 1709.05011, Tabella 8. ResNet-50: NVIDIA Deep Learning Examples (PyTorch), tempi di NVIDIA.

![](imgs/Pasted%20image%2020261125224357.png)

(\*) **Prestazioni**: lo speed-up delle GPU sui carichi AI molto paralleli va da poche volte a oltre 10 volte, a seconda di modello, dimensione del batch, precisione e trasferimento dei dati.

(\*\*) **Efficienza energetica**: **meno energia per modello addestrato o per inferenza perché le GPU finiscono prima**, ma la **potenza assoluta è più alta**: **misurare le prestazioni per watt**.

> ⚠️ **AlexNet in 11 minuti su CPU (nota didattica).** La riga sembra contraddire il messaggio «serve una GPU»: 1024 CPU (un supercalcolatore) con un batch enorme battono una GPU del 2012. Il confronto onesto è per costo e per energia, non solo per tempo: 1024 processori server contro una scheda. È il punto della slide successiva.

> 💡 **Potenza più alta, energia più bassa (nota didattica).** L’energia è potenza per tempo, $E=P\cdot t$. Supponiamo che una CPU da 65 W impieghi 20 ms per un’inferenza e una GPU da 300 W ne impieghi 2:
> $$E_{\text{CPU}}=65\ \text{W}\times 0{,}020\ \text{s}=1{,}3\ \text{J},\qquad E_{\text{GPU}}=300\ \text{W}\times 0{,}002\ \text{s}=0{,}6\ \text{J}.$$
> La GPU assorbe quasi 5 volte la potenza, ma consuma meno della metà dell’energia per inferenza. Se però il carico è piccolo e la GPU resta per lo più inattiva, o se i dati impiegano più tempo a viaggiare verso la GPU che a essere elaborati, il vantaggio sparisce. La stima del fine-tuning: $10^5/2470=40{,}5$ s.

#### **7.5. Altri dati: lo speed-up CPU contro GPU**

**NVIDIA HGX, 8 GPU A100**: **> 100 k€**; **server CPU**: **qualche k€**. Speed-up relativo a un server CPU (1,0×).

![](imgs/Pasted%20image%2020261125224425.png)

**Lo stesso lavoro gira circa 10–20 volte più veloce sui server GPU che sui server CPU, ma il server GPU costa molto di più**: **confrontare le prestazioni per euro e per watt, non solo la velocità**.

> 💡 **Il conto per euro (nota didattica).** Con un server CPU da 5 k€ e un HGX da 100 k€ (20 volte il prezzo), uno speed-up di 15 volte dà una velocità per euro **inferiore** sul server GPU. Il GPU vince quando il tempo ha un valore (un modello pronto in ore invece che in giorni) o quando il lavoro è continuo e la macchina sempre carica. Per un’inferenza saltuaria, il cloud (§7.8) evita di comprare la macchina.

#### **7.6. Le server farm del ML…**

Amazon (AWS), Google, Facebook, Microsoft (AzureML), IBM… Server **Big Sur** nel data center di Facebook a Prineville.

![](imgs/Pasted%20image%2020261125224511.png)

#### **7.7. Le GPU: non sempre necessarie**

![](imgs/Pasted%20image%2020261125224526.png)

**Il ML sulle CPU** offre vantaggi:

- **facilità di portabilità e flessibilità dei casi d’uso**: lo **stesso codice, applicazioni diverse**;
- **disponibilità sul mercato** a **prestazioni** e **prezzi** diversi;
- **rilascio su un ampio spettro di dispositivi**, dall’edge al calcolo edge e ai server cloud.

#### **7.8. Dove gira l’inferenza? Cloud o locale**

**Inferenza** = usare un modello addestrato su un **ingresso nuovo** (nessun apprendimento): sui **server di un fornitore** (cloud) o su una **macchina dell’azienda** (locale).

- **Locale**: **i dati restano in azienda** (segreto industriale, GDPR), **nessuna latenza di rete**, **funziona offline**; hardware **comprato in anticipo**, **dimensione del modello limitata dalla memoria locale**.
- **Cloud**: **i modelli più grandi**, **pagamento a consumo**, nessun hardware; **i dati lasciano l’azienda**, **latenza e costo crescono con il carico**.
- **La domanda è quale vincolo domina, non «quale è meglio».**

**Macchine d’esempio per l’inferenza locale** (prezzi indicativi da annunci al dettaglio, novembre 2026): Apple Mac mini, mini PC AMD; ThinkStation P5; **NVIDIA DGX Spark, 128 GB** (B0FWJ16CCH, ≈ 9400 €); **ThinkStation PGX, 128 GB** (30KL000FMW, ≈ 9350 €). Prezzi: amazon.it e un rivenditore Lenovo, 9 novembre 2026.

![](imgs/Pasted%20image%2020261125225255.png)

> 💡 **Il legame con la sovranità dei dati (nota didattica).** È la discussione della Lezione 15 (§2) vista dal lato dell’hardware: per i segreti industriali e i dati personali particolari l’inferenza locale elimina il trasferimento verso un fornitore esterno (e i problemi di giurisdizione del CLOUD Act). Il prezzo è l’investimento iniziale e la responsabilità di aggiornare e proteggere la macchina.

#### **7.9. Memoria unificata contro GPU discreta**

| | **Memoria unificata (condivisa)** | **GPU discreta** |
|---|---|---|
| struttura | CPU, GPU e NPU condividono **128 GB** | CPU con RAM di sistema 64 GB, collegata via **PCIe** alla GPU con **VRAM 24 GB** |
| banda | **256–546 GB/s** | **≈ 1000 GB/s** (VRAM) |
| modello 70B a 4 bit (≈ 35–40 GB) | **il modello ci sta** | **non ci sta nella VRAM** |

![](imgs/Pasted%20image%2020261125225353.png)

1. **Capacità: il modello gira?** I pesi devono **stare nella memoria che il processore può leggere**. Un modello da **70 miliardi di parametri a 4 bit** richiede **≈ 35–40 GB**: **non sta in 24 GB di VRAM, sta in 128 GB di memoria unificata**.
2. **Banda: quanto veloce?** Per generare **un token** il modello **legge tutti i suoi pesi una volta**:
   $$\text{token/s}\lesssim\frac{B}{\text{Mem}},$$
   con $B$ la **banda di memoria** [GB/s] e Mem la **dimensione dei pesi** [GB]. Con Mem = 40: $256/40\approx 6$ e $546/40\approx 14$ token/s.

**Fuori dalla GPU**: un collegamento **PCIe 5.0 x16** trasporta **≈ 64 GB/s per direzione** (PCIe 4.0: ≈ 32 GB/s), **15–30 volte meno della VRAM**: i pesi «traboccati» nella RAM di sistema danno **al più $64/40\approx 1{,}6$ token/s**.

**Esempi (prospettiva)**: mini PC **AMD Ryzen AI Max+ 395**, 128 GB a 256 GB/s; **Apple M4 Max**, fino a 128 GB a 546 GB/s; una GPU discreta da 24 GB è **più veloce** (≈ 1000 GB/s) ma **troppo piccola** per questo modello.

> 💡 **Perché la banda e non i FLOPS (nota didattica).** Generare un token con un modello da 70 miliardi di parametri richiede circa $2\cdot 70\cdot 10^9=1{,}4\cdot 10^{11}$ operazioni: una GPU da 100 TFLOPS lo farebbe in 1,4 ms. Ma deve anche **leggere** 40 GB di pesi: a 1000 GB/s servono 40 ms. La generazione di testo è quindi **limitata dalla memoria**, non dal calcolo, ed è il motivo per cui la quantizzazione (che riduce Mem) accelera anche, non solo «fa stare» il modello.

#### **7.10. Memoria e quantizzazione: meno bit per peso**

**Memoria di un modello**:

$$\text{Mem}\approx N_{\text{par}}\cdot\frac{b}{8}\ \text{[byte]},$$

con $N_{\text{par}}$ il numero di pesi; $b$ i **bit per peso** (32 = FP32, 16 = FP16, 8 = INT8, 4 = INT4); $/8$: da bit a byte; 1 GB = $10^9$ byte (più la cache del contesto di un modello linguistico, Lezione 17, §1.3).

| Pesi, GB | $N_{\text{par}}=8\cdot 10^9$ | $N_{\text{par}}=70\cdot 10^9$ |
|---|---:|---:|
| $b=32$ | 32 | 280 |
| $b=16$ | 16 | 140 |
| $b=8$ | 8 | 70 |
| $b=4$ | 4 | 35 |

**Quantizzazione**: ogni peso $w$ diventa un **intero a $b$ bit** $q$:

$$w\approx S\,(q-Z),\qquad q=\text{round}\left(\frac{w}{S}\right)+Z,$$

con $S$ (reale) la **dimensione di un passo intero**; $Z$ (intero) il **codice di $w=0$**; $q\in\{0,\dots,2^b-1\}$.

- **Esempio**, $b=8$, $w\in[-1,1]$: $S=2/255\approx 0{,}0078$, $Z=128$; $w=0{,}5\Rightarrow q=192$, **errore $\le S/2\approx 0{,}004$**.
- **Dimezzare $b$ dimezza la memoria** (INT8: **4 volte meno di FP32**); la **matematica intera è anche più veloce**. **Prezzo**: una **piccola perdita di accuratezza, da misurare di nuovo**.

**Input**: modello addestrato FP32, numero di bit $b$ (e qualche campione di calibrazione) ⇒ **Output**: modello con **pesi interi a $b$ bit** più $(S,Z)$ **per tensore o per canale**.

> 💡 **I conti (nota didattica).** $0{,}5/0{,}00784=63{,}75$, arrotondato a 64; $64+128=192$. Dequantizzando: $0{,}00784\cdot(192-128)=0{,}502$, errore 0,002. Con INT4 ($2^4=16$ livelli) lo stesso intervallo ha $S=2/15=0{,}133$: l’errore massimo sale a 0,067, ed è il motivo per cui INT4 si usa con $S$ per gruppi piccoli di pesi (per esempio ogni 32 o 128 pesi). I «campioni di calibrazione» servono a stimare l’intervallo delle **attivazioni**, che dipende dai dati.

#### **7.11. Strumenti per i modelli locali (prospettiva, non d’esame)**

**Rete aziendale: nulla esce.** Prompt + documenti locali → **runtime locale** (llama.cpp / Ollama), che esegue un **modello quantizzato** (file **GGUF**, per esempio a 4 bit) → risposta.

![](imgs/Pasted%20image%2020261125225432.png)

- **llama.cpp**: motore **open source in C/C++** che esegue modelli linguistici su CPU e GPU. **Input**: file del modello + prompt; **output**: testo generato.
- **GGUF**: **formato a file unico** di llama.cpp che memorizza i pesi (quantizzati) insieme ai metadati del modello.
- **Ollama**: strumento che **scarica i modelli e li serve sulla macchina locale** attraverso un’**API locale**.
- **OpenVINO** (in questa lezione) può anche eseguire modelli linguistici in locale su CPU, GPU e NPU Intel.
- **Le versioni cambiano ogni poche settimane**: controllare le pagine dei progetti prima di un laboratorio.

github.com/ggml-org/llama.cpp; ollama.com; docs.openvino.ai (verificati l’8 novembre 2026). Gli embedding EmbeddingGemma 2 delle Lezioni 18–19 sono stati calcolati con Ollama.

#### **7.12. Portare il ML all’«edge»**

![](imgs/Pasted%20image%2020261125225500.png)

**Esempio: il ML su un processore ARM.**

- **Arm AI Platform**: una piattaforma di calcolo completa che comprende **CPU Arm Cortex e GPU Mali** e una nuova linea di processori molto scalabili, versatile al punto da scalare **a qualsiasi dispositivo, dall’IoT alle auto connesse ai server**.
- Insieme di funzioni per framework di ML come TensorFlow di Google; progetti di imaging e visione; codice **portabile** su diverse configurazioni di sistemi Arm.
- **L’addestramento delle reti neurali non richiede spesso dati ad altissima precisione**: i calcoli si possono fare su **dati a 16 o perfino 8 bit**, invece che a 32 o 64 bit.
- **La maggior parte dell’elaborazione delle reti neurali usa la moltiplicazione matriciale a virgola fissa a 8 bit.**
  - L’architettura **Armv8.2-A** ha introdotto il supporto per la **mezza precisione (FP16)** e i **prodotti scalari interi (INT8)** nelle istruzioni SIMD (*single instruction multiple data*) **NEON**, per accelerare l’elaborazione delle reti neurali.

> ⚠️ **Addestramento o inferenza? (nota didattica).** La frase sugli 8 bit vale soprattutto per l’**inferenza**: l’addestramento usa di solito almeno 16 bit (FP16 o BF16, «misto» nel §7.4), perché i piccoli aggiornamenti dei gradienti si perderebbero con soli 256 livelli. La quantizzazione a 8 bit si applica al modello già addestrato (§7.10).

#### **7.13. L’hardware per il ML: l’esempio della famiglia Arm**

Applicazioni di complessità crescente, prestazioni (operazioni/s) crescenti, potenza e costo (silicio) crescenti:

| Famiglia | Applicazioni |
|---|---|
| **Cortex-M + Ethos-U85** (NPU 0,25–4 TOPS, transformer) | voce e audio, dispositivi indossabili, sensori della casa intelligente |
| **Cortex-A (Armv9) + Ethos-U85** | telecamere intelligenti, visione industriale, automazione di fabbrica, robot consumer |
| **Lumex**: CPU C1 + SME2, GPU Mali G1 (fino a 5× AI sul dispositivo) | telefoni, tablet, PC: LLM sul dispositivo, sintesi vocale, denoising di telecamere 4K |
| **Neoverse V3; Arm AGI CPU** (> 2× prestazioni per rack rispetto a x86) | AI nei data center: orchestrazione di agenti, gestione degli acceleratori |

![](imgs/Pasted%20image%2020261125225531.png)

**Non solo CPU contro GPU**: i sistemi AI Arm sono **eterogenei**: **CPU per il controllo**, **GPU per i carichi paralleli**, **NPU (Ethos-U) per l’inferenza a bassa potenza**, **librerie ottimizzate** per mappare il modello sull’hardware. I numeri sono dichiarazioni di Arm. Arm Newsroom (Ethos-U85; Arm AGI CPU, 24 marzo 2026); Arm Lumex via Hackster.io.

> 💡 **TOPS (nota didattica).** I TOPS (*tera operations per second*, $10^{12}$ operazioni al secondo, di solito INT8) misurano gli acceleratori di inferenza come i FLOPS misurano quelli in virgola mobile. Una NPU da 1 TOPS esegue una MobileNetV2 (circa $0{,}3\cdot 10^9$ moltiplicazioni-somme, cioè $0{,}6\cdot 10^9$ operazioni) in teoria in 0,6 ms; in pratica la memoria e l’utilizzo parziale riducono di molto questo valore.

#### **7.14. Google TPU: dal primo chip a Ironwood**

- Una **TPU** (*tensor processing unit*) è un **ASIC progettato da Google per le reti neurali**: grandi **moltiplicazioni matriciali su un array sistolico** di unità di moltiplicazione-accumulazione (primo chip, **2015: 8 bit**).
- **Generazioni**: v2–v4 (addestramento nel cloud) → **v5p** (2023, pod con raffreddamento a liquido) → **Trillium** (v6e, 2024) → **Ironwood** (v7, 2025): **la prima TPU progettata per l’inferenza**, **192 GB di memoria HBM per chip**, **pod fino a 9216 chip**.
- **Uso**: Google Cloud, **gratis in Colab** («Hardware accelerator: TPU»), con JAX, PyTorch/XLA, Keras 3.
- **Per i modelli grandi capacità e banda di memoria contano quanto i FLOPS grezzi** (§7.9).

**Input**: tensori (attivazioni, pesi) ⇒ **Output**: risultati di grandi moltiplicazioni matriciali. blog.google, Ironwood (9 aprile 2025). Prospettiva, verificata l’8 novembre 2026.

![](imgs/Pasted%20image%2020261125225618.png)

> 💡 **L’array sistolico (nota didattica).** Una griglia di unità moltiplica-e-somma in cui i dati «pulsano» da una cella alla vicina a ogni ciclo, come il sangue nel sistema circolatorio (da cui il nome): ogni peso viene letto dalla memoria una sola volta e riusato da tutta una riga di celle. È il modo più efficiente di fare il prodotto matriciale di un im2col (Lezione 21, §9.6), ed è il motivo per cui le TPU sono veloci su reti standard e meno flessibili sulle operazioni insolite. «Gratis in Colab» indica la disponibilità di runtime TPU nel livello gratuito, con limiti di tempo e disponibilità.

> 💡 **Aggiornamento hardware (dall’audit del 20 novembre 2026).** Google ha annunciato due sistemi aggiuntivi della sua ottava generazione TPU: **TPU 8i Max**, per finestre di contesto molto ampie e alta densità di calcolo, e **TPU 8tx**, per inferenza riservata e confidenziale; completano le configurazioni TPU 8t per training e TPU 8i per reasoning annunciate ad aprile. NVIDIA ha comunicato il 17 novembre che i sistemi **Vera Rubin NVL72** e le CPU Vera hanno iniziato le spedizioni in volume nel terzo trimestre 2026. Sono annunci e disponibilità riportati dai produttori; le prestazioni dichiarate vanno confrontate soltanto a parità di carico, precisione, memoria, rete e metrica. ([Google Cloud TPU 8tx/8i Max](https://cloud.google.com/blog/topics/ai-infrastructure/tpu-8tx-and-tpu-8i-max), [NVIDIA Vera Rubin](https://nvidianews.nvidia.com/news/nvidia-expands-commitments-with-supply-chain-partners-to-support-ai-infrastructure-buildout))

#### **7.15. Portare il ML all’«edge» (2)**

- **Supercalcolatori su scala di exaflop**: Pichai presenta il supercalcolatore Google da **9 exaflop**. Le TPU sono collegate in supercalcolatori, detti **pod**, con **oltre 4000 chip**.
- **Acceleratori neurali edge**: **Google Edge TPU**, un **ASIC progettato per eseguire l’AI all’edge**. Offre **alte prestazioni in un ingombro fisico e di potenza ridotto**, permettendo il rilascio di AI ad alta accuratezza all’edge (**matematica Int8**; esegue una **MobileNetV2 a 400 fotogrammi/s**).

![](imgs/Pasted%20image%2020261125225639.png)

> 💡 **400 fotogrammi/s (nota didattica).** 2,5 ms per immagine. Con un Edge TPU da 4 TOPS dichiarati e 2 W tipici, l’energia per inferenza è circa $2\ \text{W}\times 0{,}0025\ \text{s}=5$ mJ: trenta volte meno dei 0,15 J dell’esempio del §8.1, con un modello più piccolo. Il confronto vale solo a parità di rete e precisione (§8.1).

#### **7.16. Far stare una rete all’edge: tre strumenti**

| Strumento          | Idea                                                                               | Input ⇒ output                                                         | Guadagno / prezzo                                                                          |
| ------------------ | ---------------------------------------------------------------------------------- | ---------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| **quantizzazione** | **meno bit per peso e attivazione**, $w\approx S(q-Z)$                             | modello FP32 ⇒ modello INT8 (o INT4)                                   | memoria ÷4 (÷8), **matematica intera più veloce** / piccola perdita di accuratezza         |
| **pruning**        | **rimuovere i pesi o i canali che contribuiscono meno**                            | modello addestrato ⇒ modello più piccolo (più sparso), poi fine-tuning | meno operazioni / **richiede riaddestramento**; speed-up **solo se l’hardware lo sfrutta** |
| **distillazione**  | un **piccolo studente impara a imitare le uscite morbide di un grande insegnante** | insegnante + dati ⇒ piccolo modello studente                           | modello piccolo e veloce / addestramento extra                                             |

Richiamo dalla Lezione 22: la **knowledge distillation** addestra lo studente sulla softmax dell’insegnante a **temperatura $T>1$**, che conserva l’informazione sulle classi simili. **Dopo uno qualsiasi dei tre: misurare di nuovo l’accuratezza sul validation set e sull’hardware di destinazione.**

> 💡 **«Solo se l’hardware lo sfrutta» (nota didattica).** Azzerare il 50% dei pesi sparsi qua e là non accelera una GPU, che moltiplica comunque matrici dense (gli zeri si moltiplicano come gli altri numeri). Serve un pruning **strutturato** (togliere canali interi, che rimpicciolisce le matrici) o hardware con supporto alla sparsità (per esempio la sparsità 2:4 delle GPU NVIDIA recenti).

#### **7.17. Da un modello addestrato a un motore edge**

**Modello addestrato** (PyTorch, FP32) → **esportare in ONNX** → **builder TensorRT** (fusione di strati, **calibrazione INT8** con qualche centinaio di immagini di calibrazione) → **file del motore** (INT8) → **runtime sulla scheda** → rilevamenti + latenza (ms).

![](imgs/Pasted%20image%2020261125225732.png)

- **ONNX**: **formato di file aperto** che descrive una rete come un **grafo di operatori standard**, così che un modello addestrato in un framework possa essere ottimizzato da un altro strumento.
- **TensorRT**: SDK NVIDIA che prende il grafo ONNX e **costruisce un motore per una GPU specifica**: **fonde gli strati**, **sceglie i kernel più veloci** e, con le immagini di calibrazione, **fissa le scale INT8 $S$ di ogni tensore**.
- **La stessa catena esiste per altro hardware**: **OpenVINO** per CPU/GPU/NPU Intel (questa lezione), **TFLite/LiteRT** per Edge TPU e telefoni.

onnx.ai; NVIDIA TensorRT Developer Guide (docs.nvidia.com/deeplearning/tensorrt), sezioni sulla calibrazione INT8.

> 💡 **La fusione di strati (nota didattica).** Conv → BatchNorm → ReLU sono tre passaggi in memoria; in inferenza la BatchNorm è una moltiplicazione e una somma fisse (Lezione 22, §2.5) che si possono incorporare nei pesi e nel bias della conv, e la ReLU si applica nello stesso kernel. Un solo passaggio invece di tre: meno letture di memoria, che è il vero collo di bottiglia (§7.9).

---

### **8. Misurare e rilasciare**


#### **8.1. Misurare l’inferenza edge: latenza, throughput, energia**

- **Latenza** (ms): tempo **da un ingresso alla sua uscita**, batch 1. **Decide il tempo reale.**
- **Throughput** (immagini/s): **quanti ingressi al secondo** con **batch grandi**.
- **Energia per inferenza** (J) = **potenza (W) × tempo (s)**. **Esempio**: $15\ \text{W}\times 0{,}010\ \text{s}=0{,}15$ J.
- **Un confronto equo CPU / GPU / NPU** usa **la stessa rete, la stessa dimensione d’ingresso e la stessa precisione**, e riporta **fps e W**.

![](imgs/Pasted%20image%2020261125225751.png)

**MLPerf Inference**: un **benchmark industriale** con **modelli, dataset, obiettivi di accuratezza e scenari fissati** (*single-stream* = latenza, *offline* = throughput). **Jetson AGX Orin, ResNet-50 (v3.1)**:

| Scenario | Valore |
|---|---|
| latenza single-stream | **0,64 ms** |
| throughput offline | **≈ 6400 immagini/s** |

**Stesso compito, due scenari, due numeri molto diversi: dire sempre quale metrica si riporta.** developer.nvidia.com/embedded/jetson-benchmarks (risultati MLPerf Inference v3.1).

> 💡 **Perché i due numeri non sono uno l’inverso dell’altro (nota didattica).** $1/0{,}64$ ms $=1560$ immagini/s, ma il throughput offline è 6400: con batch grandi la GPU lavora su molte immagini in parallelo, e il tempo per immagine scende a $1/6400=0{,}16$ ms, mentre ogni singola immagine aspetta che il batch sia completo. Una telecamera di sicurezza vuole la latenza bassa; l’elaborazione notturna di un archivio vuole il throughput alto.

#### **8.2. Movidius Neural Compute Stick di Intel (esempio storico)**

- L’**Intel Movidius Neural Compute Stick** era un **acceleratore VPU USB compatto** usato per imparare e prototipare l’inferenza edge-AI (fino a 1–4 TOPS).
- **È ora una piattaforma legacy**: Intel **ha interrotto il prodotto**, e le versioni recenti di OpenVINO **non supportano più i dispositivi Movidius/Myriad X**.
- **L’edge AI moderna** si basa di solito su **NPU integrate, GPU embedded o acceleratori edge dedicati**.

![](imgs/Pasted%20image%2020261125225834.png)

![](imgs/Pasted%20image%2020261125225845.png)

#### **8.3. L’ambiente MATLAB (HW + SW)**

![](imgs/Pasted%20image%2020261125225907.png)

---

### **9. Strumenti: OpenVINO**


Una potente piattaforma di ottimizzazione per i modelli.

#### **9.1. OpenVINO: introduzione e dispositivi supportati**

![](imgs/Pasted%20image%2020261125225942.png)

![](imgs/Pasted%20image%2020261125230009.png)

#### **9.2. Un flusso di lavoro tipico con OpenVINO**

| **MODELLO** (uno o più) | **OTTIMIZZAZIONE** (sceglierne una) | **RILASCIO** (sceglierne una) |
|---|---|---|
| addestrare il modello da sé | **riaddestrare con ottimizzazione (NNCF)** | **eseguire l’inferenza in locale** |
| prendere un modello pre-addestrato da un hub pubblico | **quantizzare con NNCF** (per esempio INT8) | **costruire un pacchetto / un’immagine** |
| partire da un modello PyTorch, TensorFlow o ONNX | **convertire con `ovc` in OpenVINO IR** | **servire con OpenVINO Model Server** |

**OpenVINO IR** (*Intermediate Representation*): la rete ottimizzata salvata come **due file**, `.xml` (grafo) e `.bin` (pesi).

**Input**: una rete addestrata (PyTorch, TensorFlow, ONNX…) ⇒ **Output**: un **modello ottimizzato** che gira su **CPU, GPU o NPU Intel**, **in locale o come servizio di rete**. Documentazione OpenVINO, docs.openvino.ai.

#### **9.3. OpenVINO oggi: runtime e strumenti**

- **OpenVINO Runtime** ottimizza la pipeline **automaticamente** (fusione del grafo, riuso della memoria, bilanciamento del carico, inferenza parallela) su CPU, GPU e VPU/NPU.
- **NNCF** (*Neural Network Compression Framework*): prende un modello addestrato e ne restituisce uno **compresso**, per esempio **quantizzato a INT8**, con **poca perdita di accuratezza**.
- **`ovc`** (convertitore di modelli OpenVINO, anche `openvino.convert_model`): prende un modello PyTorch, TensorFlow o ONNX e restituisce l’**OpenVINO IR** (`.xml` + `.bin`).
- **I modelli pre-addestrati si prendono di solito da hub pubblici e si convertono**: **non c’è uno strumento di download separato** nel flusso di lavoro.
- **Prospettiva**: le versioni continuano (2026.x); controllare la documentazione della versione usata.

OpenVINO release notes 2024.0–2026.0 (github.com/openvinotoolkit/openvino/releases); docs.openvino.ai.

> ⚠️ **Che cosa è cambiato (nota didattica).** Le edizioni precedenti di questa lezione citavano il *Model Optimizer* (`mo`), il *Post-training Optimization Tool* (POT) e l’*Open Model Zoo* con il suo downloader: sono stati sostituiti rispettivamente da `ovc`, da NNCF e dagli hub pubblici. La «VPU» nell’elenco dei dispositivi si riferisce alle NPU integrate dei processori recenti, non alle Movidius ormai non supportate (§8.2): è un’incoerenza di nomenclatura della slide.

```python
# Esempio indicativo (ricostruzione, non nelle slide): da ONNX a INT8 con OpenVINO e NNCF
import openvino as ov
import nncf

core = ov.Core()
model = ov.convert_model("model.onnx")                  # come ovc: -> modello OpenVINO
calib = nncf.Dataset(calibration_images)                # qualche centinaio di immagini
model_int8 = nncf.quantize(model, calib)                # quantizzazione post-addestramento
ov.save_model(model_int8, "model_int8.xml")             # IR: .xml + .bin
compiled = core.compile_model(model_int8, "CPU")        # oppure "GPU", "NPU"
result = compiled(input_tensor)
```

---

### **10. Strumenti: Edge Impulse (non d’esame)**


Raccolta dei dati, addestramento, ottimizzazione e rilascio per l’edge AI.

- Una **piattaforma per raccolta dei dati, addestramento, ottimizzazione e rilascio** dell’edge AI.
- **Qualcomm ha acquisito Edge Impulse** (annunciato il 10 marzo 2025). La piattaforma resta uno strumento di edge AI dalla raccolta dei dati al rilascio e **continua a supportare dispositivi di più produttori**.
- **Flusso di lavoro**: dati → feature → modello → **ottimizzazione consapevole del dispositivo** (per esempio quantizzazione INT8) → rilascio.
- **Prospettiva, non d’esame.**

The Next Web, *Qualcomm acquires Edge Impulse* (10 marzo 2025); EdgeIR, 17 marzo 2025.

![](imgs/Pasted%20image%2020261125230122.png)

![](imgs/Pasted%20image%2020261125230130.png)

![](imgs/Pasted%20image%2020261125230144.png)

---

### **11. Punti principali**


> ✅ **Punti principali della lezione.**
> - **Il compito è definito dall’uscita**: un’etichetta, un box $\{x_1,y_1,x_2,y_2\}$ per oggetto, una classe per pixel, o una maschera per oggetto.
> - **Un bounding box non è una segmentazione**: le misure reali di un oggetto richiedono **la sua maschera**.
> - **Pipeline classiche, profonde e ibride convivono**: la segmentazione classica può **proporre candidati a una CNN**.
> - **La segmentazione è mal posta** (dipende dal contesto); i modelli **promptable** come SAM restituiscono **una maschera per qualsiasi prompt**.
> - **Segmentazione semantica = encoder/decoder con upsampling appreso.** Le **GPU accelerano l’addestramento**; **CPU, NPU, VPU, Edge TPU servono il rilascio**.
> - **L’inferenza locale tiene i dati in azienda.** **Memoria ≈ $N_{\text{par}}\cdot b/8$ byte**: **la quantizzazione decide che cosa ci sta, la banda quanto è veloce**.
> - **Il rilascio si misura, non si presume**: latenza, throughput ed energia **sulla stessa rete e alla stessa precisione**.

---

### **12. Appendice (non d’esame)**


Materiale di supporto: suggerimento di lettura, video e link a tutorial della lezione originale.

- **Leggere il breve ebook MATLAB sul deep learning** (non d’esame): riassume molti concetti discussi nella lezione e dà alcuni esempi di codice.

![](imgs/Pasted%20image%2020261125230224.png)

- **Video** (non d’esame): https://www.youtube.com/watch?v=kY9nZbX1DWM e https://www.youtube.com/watch?v=oK7Bum1x_dI (Vision Processing Units).

![](imgs/Pasted%20image%2020261125230236.png)

- **Piattaforma Edge Impulse** (non d’esame). Tutorial: https://www.youtube.com/watch?v=0Uga3GVXgMw

---

### **13. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **classificazione** / **rilevamento** | un’etichetta per immagine / box, classe e punteggio per oggetto |
| **segmentazione semantica** / **per istanze** | una classe per pixel, istanze fuse / una maschera per oggetto |
| **bounding box** / **maschera** | rettangolo allineato agli assi con sfondo / pixel esatti dell’oggetto, misure reali |
| **rilevamento** / **stima della posa** | box / punti chiave dello scheletro |
| **classico** / **profondo** / **ibrido** | feature progettate / feature apprese / candidati classici + CNN |
| **NMS** / **Soft-NMS** | elimina i box sovrapposti / ne abbassa il punteggio |
| **soglia di Otsu** / **k-means sui pixel** | due classi dall’istogramma / $K$ classi di colore |
| **SAM** / **SAM 2** / **SAM 3** | prompt visivi su immagini / video con memoria / concetti testuali o visivi, tutte le istanze |
| **encoder di SAM** / **decoder di SAM** | pesante, una volta per immagine / leggero, un prompt alla volta |
| **convoluzione** / **convoluzione trasposta** | riduce la dimensione / la aumenta (upsampling appreso), non la inverte |
| **accuratezza per pixel** / **mIoU** | ingannevole se lo sfondo domina / media dell’IoU per classe |
| **ResNet + FC** / **senza FC** / **+ decoder** | classificazione / estrazione di feature / segmentazione |
| **GPU** / **TPU** | flessibile, parallela / ASIC tensoriale, meno flessibile |
| **NPU** / **VPU** | inferenza neurale a bassa potenza / pipeline visive dedicate |
| **potenza** / **energia** | watt assorbiti / joule per inferenza ($P\cdot t$) |
| **capacità di memoria** / **banda** | se il modello ci sta / quanto veloce genera (token/s ≲ $B$/Mem) |
| **memoria unificata** / **VRAM discreta** | grande, banda media / piccola, banda alta |
| **FP32** / **INT8** / **INT4** | 4 byte per peso / 1 byte / mezzo byte |
| **quantizzazione** / **pruning** / **distillazione** | meno bit / meno pesi / modello più piccolo che imita il grande |
| **latenza** / **throughput** | batch 1, tempo reale / batch grandi, immagini al secondo |
| **inferenza locale** / **cloud** | dati in azienda, hardware comprato / modelli grandi, dati in uscita, pagamento a consumo |
| **ONNX** / **TensorRT** / **OpenVINO IR** | formato di scambio / motore NVIDIA / formato Intel (.xml + .bin) |
