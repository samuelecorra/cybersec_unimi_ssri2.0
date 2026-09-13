## **Lezione 8: Errori nei dataset e similarità tra dati e immagini**

L’ottava lezione del corso **IS4** prosegue la progettazione del dataset con due temi. Il primo sono gli **errori nelle etichette**: supervisori che sbagliano, diagnosi che cambiano nel tempo, conversioni automatiche che producono etichette assurde, duplicati con etichette opposte. Il secondo è la **similarità**: come si misura quanto due dati o due immagini si somigliano, perché la stessa misura sta alla base di quasi tutto il pattern recognition, e quali strumenti esistono per le immagini, dai **keypoint** (SIFT, SURF) agli **istogrammi** fino agli **hash percettivi** (aHash, pHash, dHash).

Fonti: [PDF della Lezione 08](Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf), 69 pagine, e il supplemento [python_image_hash.py](python_image_hash.py), letto integralmente. Le formule delle distanze, presenti nelle slide solo come immagini, sono riportate nella forma standard e segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–3)_

Titolo originale: *Errors in Dataset, Similarity in Datasets and Images*. L’indice (p. 2) elenca:

1. **errori di etichettatura**: errori dei supervisori, cambiamenti nel tempo, controlli;
2. **similarità e distanza**: il quadro generale del pattern recognition;
3. **metriche nei dataset**: euclidea, Manhattan, coseno, Chebyshev, Minkowski;
4. **metriche per le immagini**: istogrammi, SIFT, SURF, aHash, dHash, pHash.

**Il punto della sequenza** (p. 3). Le Lezioni 6–9 formano un unico percorso, **dai dati grezzi alla progettazione di un dataset intelligente**:

| Lezione | Tema |
|---|---|
| 6 | raccolta, pre-elaborazione e armonizzazione dei dati |
| 7 | numero di parametri, da bilanciare con i dati disponibili, e data leakage |
| **8** | **individuare e correggere gli errori di etichettatura**, rimuovere i duplicati, **scegliere i campioni più utili**, soprattutto nelle immagini |
| 9 | similarità e codifica per sistemi di ricerca di immagini, base per creare i propri dataset intelligenti |

---

### **1. Etichette ed errori di etichettatura**

_(slide pp. 4–21)_

#### **1.1. A che cosa servono le etichette**

La preparazione dei dati è il **passo 2** del flusso di lavoro del ML (p. 5). È **troppo spesso sottovalutata**, e se fatta male provoca una **perdita rilevante di prestazioni**.

> 📌 **Ground truth** (p. 6). Le etichette sono le **fondamenta** per addestrare e valutare i modelli, soprattutto nell’apprendimento supervisionato. La *ground truth* è l’insieme delle **etichette che il modello dovrebbe imparare a predire** o approssimare. Le etichette sono spesso **codificate**: 1 = «auto», 2 = «camion», …

| Uso delle etichette | Perché conta |
|---|---|
| **addestramento** (p. 7) | essenziali nell’apprendimento supervisionato e semi-supervisionato; **più etichette** aiutano a **ridurre la complessità** necessaria del modello (sullo spettro dati-conoscenza, reti tradizionali e profonde) |
| **valutazione e validazione** (p. 8) | permettono di misurare le prestazioni di un modello; nell’apprendimento **non supervisionato** è difficile dire se l’output è corretto, mentre con le etichette l’accuratezza si **misura con precisione** |
| **benchmarking e classifiche** (p. 8) | dalle etichette si calcolano **figure di merito** come l’accuratezza; i dataset etichettati sono il **banco di prova comune** per confrontare modelli e approcci. Per costruire l’asse dell’accuratezza nel grafico «accuratezza contro operazioni» servono etichette |
| **interpretabilità** (p. 9) | le etichette aiutano a **spiegare** le decisioni del modello: un dataset supervisionato favorisce la spiegabilità |
| **transfer learning** (p. 9) | dati etichettati di un dominio servono a **pre-addestrare** modelli per compiti o domini **correlati**, risparmiando tempo e risorse quando le etichette del dominio di destinazione sono **scarse**; si possono anche **aggiungere immagini specifiche** in base alle etichette presenti in due database DB1 e DB2 |

<!-- SOURCE_VISUAL id="IS08-V01" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="7" type="grafico" description="Più etichette riducono la complessità necessaria del modello sullo spettro dati-conoscenza" reason="Posizione dei modelli sullo spettro visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V02" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="8" type="grafico" description="Grafico accuratezza contro operazioni per confrontare modelli, costruibile solo con le etichette" reason="Modelli e valori del grafico visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V03" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="9" type="schema" description="Transfer learning tra due database DB1 e DB2 con etichette car" reason="Schema del trasferimento visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Errori nelle etichette**

_(slide pp. 10–13)_

**Esempi già visti** (p. 10):

- il **campione è corretto, ma l’etichetta è sbagliata**: un’immagine etichettata «CAT» che non è un gatto;
- il **campione è fuorviante o incompleto** (data leakage): il frammento audio etichettato «yes» che contiene anche «you», la cellula ritagliata male (Lezione 07, §3.6).

**Errori diretti del supervisore** (p. 11). Le etichette dei dataset vengono tipicamente da **supervisori umani**, che possono sbagliare. Esempio: una **diagnosi medica** con input radiografie ed esami del sangue e output la diagnosi, per esempio «sano».

**La diagnosi cambia nel tempo** (p. 12). Una prima radiografia viene etichettata «**polmonite batterica**»; una **seconda radiografia**, fatta con una **macchina migliore**, porta alla diagnosi di «**covid-19**». Se l’etichetta della prima immagine **non viene aggiornata**, il modello impara un’associazione sbagliata, e questo **limita la capacità** del modello di fare **diagnosi precoce**: proprio le immagini iniziali, le più utili per anticipare la malattia, hanno l’etichetta errata. Sulla slide «pneumonia» è scritto «penumonia».

<!-- SOURCE_VISUAL id="IS08-V04" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="12" type="immagini" description="Radiografia etichettata polmonite batterica e seconda radiografia con diagnosi covid-19" reason="Radiografie visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Errori di interscambio e conversione automatica** (p. 13). Un’etichetta viene creata **automaticamente** con una regola sul BMI:

$$\text{BMI} = \frac{\text{massa [kg]}}{\text{altezza [m]}^2}, \qquad \text{se } 25{,}0 < \text{BMI} < 30 \;\Rightarrow\; y = \text{sovrappeso}$$

Applicata a **Yuri Chechi** (sulla slide «Jury»), campione olimpico agli **anelli** nel **1996**, fotografato nel 2022 a oltre 50 anni, dopo una **conversione automatica di formato** la regola produce l’etichetta «**grasso**».

> 💡 **Perché la regola sbaglia (nota didattica).** Il BMI non distingue **massa muscolare** da massa grassa: un atleta molto muscoloso può superare 25 senza essere sovrappeso. Un’etichetta generata da una regola automatica eredita tutti i **limiti della regola**, e una conversione di unità o di formato sbagliata può spostare i valori in una fascia diversa senza che nessuno se ne accorga.

<!-- SOURCE_VISUAL id="IS08-V05" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="13" type="immagine" description="Atleta olimpico etichettato automaticamente come sovrappeso dalla regola sul BMI" reason="Immagine dell’esempio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Controlli di base: i duplicati**

_(slide pp. 14–17)_

**Vettori di input identici** (p. 14):

| Caso | Conseguenza |
|---|---|
| duplicati con **la stessa etichetta** | **nessun problema di addestramento**, ma spreco di spazio, memoria e tempo di elaborazione |
| duplicati con **etichette opposte** ($x_1 = x_2$, ma $y_1$ = «gatto» e $y_2$ = «cane») | **problemi di addestramento**: il modello riceve due risposte contraddittorie per lo stesso input |

**Il costo del controllo** (p. 15). Il controllo va fatto sul database di addestramento **e** su quello di validazione, e non è facile con database grandi (oltre un milione di immagini). Un semplice confronto **uno contro N** richiede

$$\text{iterazioni} = N\,(N-1)$$

**Esempio: il dataset Serengeti** (p. 16, pubblico):

- **non etichettato**: **3,2 milioni** di immagini, corrispondenti a **1,2 milioni** di eventi di cattura (sequenze di immagini; in totale 7,1 milioni di immagini). Il non supervisionato **non è da buttare**: serve per la messa a punto, l’estrazione delle feature, altri studi;
- **test set etichettato**: etichettato da **volontari**, **17 400** eventi di cattura;
- un controllo di base richiede, secondo la slide, $N(N-1) = 285$ milioni di confronti.

> ⚠️ **Verifica del numero.** Con $N = 17\,400$ si ottiene $N(N-1) = 17\,400 \times 17\,399 \approx 302{,}7$ milioni, non 285 milioni. Inoltre ogni coppia viene contata due volte: le coppie **distinte** da confrontare sono $N(N-1)/2 \approx 151{,}4$ milioni. L’ordine di grandezza, centinaia di milioni di confronti, resta il messaggio della slide.

**Una procedura molto semplice** (p. 17), solo come primo passo dell’analisi:

1. **caricare** $x_i$ e $x_j$;
2. **controllare** se sono duplicati;
3. **le etichette sono diverse?**
   - **sì** (caso cattivo), scegliere un’opzione:
     - **opzione 1**: rimuovere il campione;
     - **opzione 2**: chiedere assistenza a un supervisore (**seconda lettura**), poi **correggere l’etichetta** e unire il campione, oppure scartarlo;
   - **no** (caso buono): **scartare uno dei due** campioni, aggiornare gli indici $i, j$ e **tornare al passo 1**.

Quale opzione scegliere dipende dal contesto: il dataset è **enorme**? I dati sono **costosi**?

> 💡 **Come scegliere (nota didattica).** Con milioni di immagini economiche, rimuovere i campioni contraddittori costa poco. In un dataset medico in cui ogni esame è costoso e raro, la seconda lettura da parte di un esperto vale il tempo speso (Lezione 06, §7.3).

#### **1.4. Hash per trovare i duplicati**

_(slide pp. 18–21)_

**Il problema** (p. 18). Con dati o vettori grandi, un confronto diretto `if (x_i == x_j)` richiede di confrontare **elemento per elemento** o **pixel per pixel**: richiede tempo, anche se è perfettamente **parallelizzabile** (adatto alle GPU con CUDA). Alternative:

- usare un **hash del file** standard, se già disponibile;
- gli algoritmi classici **MD5** e **SHA-1**;
- calcolare le informazioni di **hash delle immagini offline**;
- usare **funzioni di hash specifiche per immagini** (§5.4).

**Il codice** (pp. 19–20, file [python_image_hash.py](python_image_hash.py)):

```python
# MD5 and SHA1 hashes for image comparison
import hashlib

def compute_hashes(image_path):
    with open(image_path, 'rb') as f:
        data = f.read()
        md5_hash = hashlib.md5(data).hexdigest()
        sha1_hash = hashlib.sha1(data).hexdigest()
    return md5_hash, sha1_hash

# Example usage
img1_md5, img1_sha1 = compute_hashes("image1.jpg")
img2_md5, img2_sha1 = compute_hashes("image2.jpg")

print("MD5 Equal:", img1_md5 == img2_md5)
print("SHA1 Equal:", img1_sha1 == img2_sha1)
```

Il file viene letto **in binario** (`'rb'`), e dei byte si calcolano i due hash in esadecimale.

> 📌 **Che cosa verifica l’hash del file** (p. 19).
> - Cambiare **anche un solo pixel** produce un hash **completamente diverso**.
> - Modificare i **metadati EXIF** (posizione, data) cambia l’hash, **anche se l’immagine appare identica**.
> - Il metodo **non verifica la similarità visiva**: controlla solo l’**identità binaria esatta**.
> - MD5 e SHA-1 hanno numeri di bit diversi, ma per questo scopo vanno **bene entrambi**; **MD5 è più veloce**.
> - Per prestazioni migliori conviene **calcolare e memorizzare prima** gli hash di tutte le immagini, e confrontarli poi in un **ciclo separato e ottimizzato**.

> 💡 **Perché memorizzare gli hash (nota didattica).** Confrontare due stringhe di 32 caratteri (MD5 = 128 bit) è molto più rapido che confrontare due immagini da milioni di pixel. Anzi, inserendo gli hash in un **dizionario** i duplicati esatti si trovano in **una sola passata**, in tempo proporzionale a $N$, invece dei $N(N-1)/2$ confronti a coppie. MD5 e SHA-1 non sono più sicuri contro chi **fabbrica** collisioni di proposito, ma per trovare duplicati accidentali restano adatti.

<!-- SOURCE_VISUAL id="IS08-V06" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="19" type="schermata" description="Due immagini con hash MD5 e SHA-1 diversi dopo la modifica di un pixel o dei metadati EXIF" reason="Immagini e hash visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Non basta l’uguaglianza** (p. 21). Con immagini, vettori o dati non strutturati **non si tratta solo** di `if (x_i == x_j)`, ma di qualcosa come

$$\text{se } \operatorname{similarità}(x_i, x_j) > \text{soglia fissata}$$

perché due campioni «uguali» possono differire per **rumore** o **correzioni di colore**, **punto di vista**, **momento o luogo** di acquisizione.

---

### **2. Similarità nei dataset**

_(slide pp. 22–27)_

La domanda della sezione (p. 22): che cosa è **rilevante** e che cosa è **inutile**?

**Esempio: riconoscimento facciale delle mucche** (p. 23). Alcune startup usano il riconoscimento facciale per aumentare la **produttività delle mucche da latte**: **tracciamento dell’attività**, **distribuzione automatica del cibo**, **somministrazione dei farmaci**. I moduli principali sono **due reti neurali**: una **segmenta il muso**, l’altra **identifica la mucca**. Le feature possono essere le **macchie del muso** o le **macchie del corpo**. Sulla slide «cow» è scritto «caw».

> 📌 **La metrica dipende dall’applicazione** (p. 23). La metrica di similarità da scegliere **dipende dal tipo di applicazione**.

<!-- SOURCE_VISUAL id="IS08-V07" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="23" type="immagini" description="Riconoscimento facciale delle mucche con macchie del muso e del corpo come feature" reason="Immagini dell’esempio visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché controllare la similarità** (p. 24):

- dati o immagini **troppo simili** forniscono **poca informazione in più**;
- rendono il dataset **più pesante da gestire**: memorizzazione, caricamento, addestramento, ecc.;
- la metrica di similarità va **regolata sull’applicazione**.

Esempio: due cani **molto simili** sono **troppo simili** per insegnare a un modello a riconoscere la **razza**, ma sono **necessari** per identificare **il singolo cane**.

**Regolare il livello di similarità** (p. 25). Con la regola `se similarità(x_i, x_j) > soglia`:

| Compito | Due immagini molto simili dello stesso animale sono… |
|---|---|
| **identificazione** del singolo individuo | **ottimi campioni** |
| **rilevamento** del volto o del muso | **ridondanti**: meglio aggiungere **altre immagini** |

<!-- SOURCE_VISUAL id="IS08-V08" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="25" type="immagini" description="Coppie di immagini molto simili: ottime per l’identificazione, ridondanti per il rilevamento" reason="Immagini dell’esempio visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Similarità e data augmentation** (p. 26). **Troppa similarità** spreca spazio e tempo. **Ma** la **data augmentation**, che crea copie modificate delle stesse immagini (per esempio lo stesso «hotdog» ruotato o ritagliato), **migliora la generalizzazione**: la capacità del modello di imparare dai dati disponibili e applicare ciò che ha imparato altrove. La data augmentation sarà trattata nella parte di deep learning.

> 💡 **Nessuna contraddizione.** I duplicati **accidentali** non aggiungono informazione; le varianti **controllate** della data augmentation insegnano al modello che rotazione, luminosità o ritaglio **non cambiano la classe**. La differenza sta nell’intenzione: la seconda introduce proprio le variazioni che il modello deve imparare a ignorare.

<!-- SOURCE_VISUAL id="IS08-V09" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="26" type="immagini" description="Data augmentation: varianti della stessa immagine di hotdog" reason="Varianti visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Similarità nei dataset in generale** (p. 27). La lezione si concentra sulle immagini, ma l’approccio vale per dati di ogni tipo:

- con **dati non strutturati** conviene **estrarre feature** per poter usare le tecniche dei dati strutturati;
- **dati strutturati → vettori di feature → metriche**:
  - norma **euclidea** o distanza di **Manhattan**;
  - distanza di **Mahalanobis**;
  - **coefficiente di correlazione di Pearson**;
  - misure di **complessità, coerenza, struttura, entropia**;
  - …

> 📌 **Mahalanobis e Pearson (nota didattica).** La distanza di Mahalanobis tiene conto della **covarianza** $S$ dei dati: pesa meno le direzioni in cui i dati variano molto. Il coefficiente di Pearson misura la **correlazione lineare** tra due vettori:
> $$d_M(\mathbf{x}, \mathbf{y}) = \sqrt{(\mathbf{x} - \mathbf{y})^{\top} S^{-1} (\mathbf{x} - \mathbf{y})} \qquad \rho(\mathbf{x}, \mathbf{y}) = \frac{\sum_i (x_i - \bar{x})(y_i - \bar{y})}{\sqrt{\sum_i (x_i - \bar{x})^2}\,\sqrt{\sum_i (y_i - \bar{y})^2}}$$
> Se $S$ è la matrice identità, Mahalanobis coincide con la distanza euclidea.

---

### **3. Similarità e pattern recognition**

_(slide pp. 28–39)_

#### **3.1. La similarità è la base del pattern recognition**

La similarità è **la base della maggior parte dei metodi di machine learning** (p. 28). Lo schema del sistema di pattern recognition (p. 29; Lezione 04, §4.3) ha un ramo di **addestramento** (acquisizione → database → estrazione delle feature → training) e uno di **test** (acquisizione → estrazione delle feature → **matching** → esito, per esempio «mela»).

La similarità **non serve solo a pulire il dataset** (p. 30): il blocco di **matching** confronta le feature del nuovo campione con quelle del database usando proprio una **misura di similarità o distanza**, e produce l’esito «classe $x$».

**Lo spazio di input** (p. 31). Estrarre un **vettore di feature** da dati strutturati o non strutturati permette di ragionare **in un quadro comune**: oggetti come un panda, un volto, un fungo diventano **punti** (Lezione 02, §3.2).

**Dallo spazio di input allo spazio delle feature** (p. 32). L’estrazione delle feature trasforma

$$[\,\text{input}_1, \text{input}_2, \dots, \text{input}_N\,] \;\longrightarrow\; [\,\text{feature}_1, \text{feature}_2, \dots, \text{feature}_M\,]$$

> 📌 **Ingegneria delle feature** (p. 32). Un buon progetto delle feature dà **meno dimensioni** e **cluster migliori**.

<!-- SOURCE_VISUAL id="IS08-V10" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="32" type="schema" description="Dallo spazio di input a N dimensioni allo spazio delle feature a M dimensioni con cluster migliori" reason="Disposizione dei punti nei due spazi visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Dove agisce la rete neurale**

Il pattern recognition si può organizzare in modi diversi, secondo **quale parte** dello schema viene affidata a una rete neurale:

| Schema | Che cosa fa la rete | Complessità per la rete |
|---|---|---|
| **rete come classificatore** (p. 33) | la rete **memorizza** il database e svolge il **matching** | **alta** |
| **rete come funzione di similarità** (p. 34) | la rete svolge **solo il matching**; database ed estrazione delle feature restano fuori | **più bassa** |
| **rete profonda** (p. 35) | **tutti i passi**, estrazione delle feature compresa, sono **dentro** il modello | tutta nella rete |

**Estrazione banale contro ingegneria delle feature** (pp. 36–39):

| Estrazione delle feature | Rete necessaria | Commento |
|---|---|---|
| **banale** (p. 36) | **rete complessa** | si estraggono **molte feature rumorose** |
| **ingegnerizzata** (p. 37) | **rete più semplice** | **poche feature potenti** |
| **neurale** (p. 38) | **rete più semplice** | si usa una rete per estrarre le feature dai suoi **strati nascosti** |
| **neurale + distanza classica** (p. 39) | **nessuna rete** nel matching: basta una **distanza euclidea** | investire calcolo **nel primo passo** permette **decisioni semplici** e **migliora la spiegabilità** (tema ripreso più avanti nel corso) |

> ⚠️ **Stesso estrattore per database e test** (p. 36). Il database deve contenere **lo stesso tipo di feature** prodotto dall’estrattore usato in test: se si cambia estrattore, le feature del database vanno **ricalcolate**.

> 💡 **Il senso dell’ultimo schema (nota didattica).** È l’idea degli **embedding** (Lezione 06, §6.3): una rete pre-addestrata trasforma ogni immagine in un vettore, e due immagini simili hanno vettori vicini. A quel punto riconoscere, deduplicare o cercare immagini è solo una questione di **distanze**, e la decisione è spiegabile: «questa immagine è la più vicina a quella».

<!-- SOURCE_VISUAL id="IS08-V11" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="33" type="diagramma" description="Rete neurale che mappa memorizzazione e matching" reason="Blocchi affidati alla rete visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V12" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="34" type="diagramma" description="Rete neurale usata solo come funzione di similarità" reason="Blocchi affidati alla rete visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V13" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="35" type="diagramma" description="Rete profonda che contiene tutti i passi del pattern recognition" reason="Blocchi affidati alla rete visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V14" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="36" type="diagramma" description="Estrazione banale di molte feature rumorose con rete complessa" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V15" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="38" type="diagramma" description="Estrazione neurale delle feature dagli strati nascosti con rete più semplice" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V16" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="39" type="diagramma" description="Feature neurali confrontate con una distanza euclidea classica" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Similarità e distanza nello spazio delle feature**

_(slide pp. 40–49)_

#### **4.1. Dalla similarità alla distanza**

Si ragiona in termini di **similarità**, ma spesso la metrica calcolata è una **distanza** (p. 41). In un certo senso la similarità è l’**inverso** della distanza:

- distanza → 0, allora similarità → **infinito** (massima);
- distanza → infinito, allora similarità → **0**.

La conversione giusta dipende dall’**applicazione** e dalle **proprietà matematiche** desiderate. Un esempio:

$$\text{similarità} = \frac{1}{1 + \text{distanza}}$$

dove l’«$1+$» evita la divisione per zero quando la distanza tende a 0. **Spesso non serve alcuna conversione**: basta usare la distanza e trovare le **soglie** adatte.

> 💡 **Due scale diverse.** Con la formula $\frac{1}{1+d}$ la similarità non va all’infinito ma resta **tra 0 e 1**: vale 1 per $d = 0$ e tende a 0 per $d \to \infty$. È spesso preferibile proprio perché è limitata.

#### **4.2. Le metriche di base**

Le pp. 42–49 presentano le metriche principali, con formule e grafici resi come immagini. Per due vettori $\mathbf{p}, \mathbf{q} \in \mathbb{R}^N$ le **definizioni standard** (nota didattica) sono:

| Metrica | Formula | Sinonimi |
|---|---|---|
| **euclidea** (p. 42) | $d_2(\mathbf{p}, \mathbf{q}) = \sqrt{\sum_{i=1}^{N} (p_i - q_i)^2}$ | norma L2, distanza «del righello» |
| **Manhattan** (p. 42) | $d_1(\mathbf{p}, \mathbf{q}) = \sum_{i=1}^{N} \lvert p_i - q_i \rvert$ | norma L1, taxicab, city-block |
| **coseno** (pp. 42–44) | $\cos\theta = \dfrac{\mathbf{p} \cdot \mathbf{q}}{\lVert \mathbf{p} \rVert \, \lVert \mathbf{q} \rVert}$, distanza coseno $= 1 - \cos\theta$ | — |
| **Chebyshev** (p. 45) | $d_\infty(\mathbf{p}, \mathbf{q}) = \max_{i} \lvert p_i - q_i \rvert$ | L-infinito, Lmax, distanza «della scacchiera» |
| **Minkowski** (pp. 47–48) | $d_p(\mathbf{p}, \mathbf{q}) = \left( \sum_{i=1}^{N} \lvert p_i - q_i \rvert^{p} \right)^{1/p}$ | generalizza le precedenti |

<!-- SOURCE_VISUAL id="IS08-V17" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="42" type="formule" description="Formule e grafici delle distanze euclidea, Manhattan e coseno" reason="Formule presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**La metrica coseno** (pp. 43–44). Misura l’**angolo** tra i due vettori, non la loro lunghezza. Lo schema della p. 44 indica tre casi: vettori con la **stessa direzione** (valore 1), **ortogonali** (valore 0), **opposti** (valore −1).

> ⚠️ **Similarità o distanza coseno?** La slide chiama «distanza» la quantità che va da 1 a −1, ma quei valori sono della **similarità** coseno $\cos\theta$: vale 1 quando i vettori sono allineati. La **distanza** coseno usata nelle librerie è di solito $1 - \cos\theta$, che vale 0 per vettori allineati e 2 per vettori opposti.

<!-- SOURCE_VISUAL id="IS08-V18" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="44" type="schema" description="Idea della metrica coseno con vettori allineati, ortogonali e opposti" reason="Vettori dello schema visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Distanza di Chebyshev** (p. 45). Dà la **componente di modulo massimo** del vettore differenza. Esempio della slide: per $X = [-6, 4, 2]$ la norma L-infinito rispetto all’origine è

$$\lVert X \rVert_\infty = \max(\lvert -6 \rvert, \lvert 4 \rvert, \lvert 2 \rvert) = 6$$

> 💡 **Lo stesso esempio con le altre norme (nota didattica).** Per $X = [-6, 4, 2]$: $\lVert X \rVert_1 = 6 + 4 + 2 = 12$ e $\lVert X \rVert_2 = \sqrt{36 + 16 + 4} = \sqrt{56} \approx 7{,}48$. Vale sempre $\lVert X \rVert_\infty \le \lVert X \rVert_2 \le \lVert X \rVert_1$.

La p. 46 **confronta** graficamente le metriche.

<!-- SOURCE_VISUAL id="IS08-V19" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="46" type="grafico" description="Confronto grafico tra le metriche di distanza" reason="Confronto presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Distanza di Minkowski** (pp. 47–48). Il parametro $p$ la trasforma nelle altre:

| $p$ | Distanza |
|---|---|
| $p = 1$ | **Manhattan** (L1, taxicab, city-block) |
| $p = 2$ | **euclidea** (L2, righello) |
| $p \to \infty$ | **Chebyshev** (L-infinito, Lmax, scacchiera) |

Con due dimensioni ($N = 2$), la p. 48 mostra l’insieme dei punti a **distanza unitaria** dal centro (il «cerchio unitario») per vari valori di $p$.

> 💡 **Com’è fatto il «cerchio» (nota didattica).** Per $p = 1$ è un **rombo** con i vertici sugli assi; per $p = 2$ è il **cerchio** usuale; per $p \to \infty$ diventa un **quadrato** con i lati paralleli agli assi. Scegliere $p$ significa decidere **quali spostamenti** considerare «vicini».

> ⚠️ **«Adimensionale».** La p. 47 definisce la metrica «adimensionale». La distanza di Minkowski ha in realtà la **stessa unità di misura delle feature**, ed è adimensionale solo se le feature sono state **normalizzate**. Per lo stesso motivo, feature con scale diverse vanno normalizzate prima di calcolarla (Lezione 06, §6.2).

<!-- SOURCE_VISUAL id="IS08-V20" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="48" type="grafico" description="Cerchi unitari della distanza di Minkowski per diversi valori di p in due dimensioni" reason="Forme dei cerchi visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V21" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="49" type="tabella" description="Panoramica delle metriche di base" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Similarità tra immagini**

_(slide pp. 50–68)_

#### **5.1. Usi e approcci**

> 📌 **Similarità tra immagini** (p. 51). Confronta due immagini e restituisce un valore che indica **quanto sono visivamente simili**:
> $$\text{score} = \operatorname{similarity}(\text{image}_1, \text{image}_2)$$
> - se lo score **tende a 0**, le immagini sono **simili nel contenuto**;
> - se lo score **vale 0**, le immagini sono **identiche**.

> ⚠️ **Uno score che si comporta da distanza.** Con questa convenzione lo score è in realtà una **distanza**: più è piccolo, più le immagini si somigliano (§4.1).

**Tre scenari d’uso principali** (p. 52):

| Scenario | Descrizione |
|---|---|
| **pulire i dataset** | i dataset disordinati sono critici per il progettista, ed eliminare i duplicati a mano è un processo **faticoso** |
| **ricerca per similarità** | data un’immagine, trovare immagini **visivamente simili** nella libreria multimediale o nei dati degli utenti |
| **tracciare i cambiamenti** | individuare **modifiche** difficili da vedere in immagini di un progetto o di un’area monitorata |

**Tre approcci principali** (p. 53):

1. **corrispondenza di keypoint**: SIFT, SURF;
2. **istogrammi**;
3. **hash delle immagini**: aHash, dHash, pHash.

#### **5.2. Corrispondenza di keypoint: SIFT e SURF**

_(slide pp. 54–61)_

**Procedura** (p. 54):

1. calcolare un’**astrazione** dell’informazione dell’immagine e decidere **localmente, in ogni punto**, se lì esiste una feature del tipo cercato;
2. **confrontare** le feature rilevate tra le due immagini.

| **Pro** | **Contro** |
|---|---|
| confronta immagini con **scale, rotazioni e illuminazione diverse** | un’implementazione ingenua costa $O(n^2 m)$, con $n$ numero di keypoint per immagine e $m$ numero di immagini nel database |

> 💡 **Da dove viene $O(n^2 m)$ (nota didattica).** Per confrontare due immagini con $n$ keypoint ciascuna, ogni keypoint della prima si confronta con tutti quelli della seconda: $n \times n = n^2$ confronti. Ripetuto per $m$ immagini del database, il costo è $n^2 m$. Con 1000 keypoint e un milione di immagini sono $10^{12}$ confronti: per questo servono indici e strutture di ricerca approssimata.

**SIFT e SURF** (p. 55):

- **SIFT** = *Scale Invariant Feature Transform*; **SURF** = *Speeded Up Robust Features*;
- entrambi sono **potenti**, con molte librerie pubbliche, e servono per similarità tra immagini, **riconoscimento di oggetti**, **registrazione** di immagini, classificazione, **ricostruzione 3D**.

| Confronto (p. 55, «in generale, non sempre») | Migliore |
|---|---|
| invarianza alla **rotazione**, **sfocatura** e **deformazioni** | **SURF** |
| immagini a **scale diverse** | **SIFT** |
| **velocità** | **SURF**, circa **3 volte** più veloce |
| **cambi di illuminazione** | entrambi buoni |

**SIFT in tre passi** (p. 56):

1. stima degli **estremi nello spazio delle scale** con la **Differenza di Gaussiane** (DoG);
2. **localizzazione** dei keypoint: i candidati vengono localizzati e **raffinati**, eliminando i punti a **basso contrasto**;
3. assegnazione dell’**orientazione** a ogni keypoint in base al **gradiente locale**, e generazione del **descrittore** locale da **modulo e orientazione** del gradiente.

> 📌 **Differenza di Gaussiane (nota didattica).** Si sfoca l’immagine $I$ con due gaussiane di scala $\sigma$ e $k\sigma$ e si sottraggono i risultati:
> $$D(x, y, \sigma) = \big(G(x, y, k\sigma) - G(x, y, \sigma)\big) * I(x, y)$$
> I massimi e i minimi di $D$ nello spazio e nella scala sono i candidati keypoint: punti che restano «notevoli» anche ingrandendo o rimpicciolendo l’immagine. Il descrittore SIFT standard è un vettore di **128** valori.

<!-- SOURCE_VISUAL id="IS08-V22" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="56" type="immagine" description="Output tipico di SIFT con keypoint, scale e orientazioni" reason="Keypoint visibili solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Come si usa SIFT** (p. 57):

- i keypoint degli oggetti vengono **estratti da immagini di riferimento** e memorizzati in un **database**;
- un’immagine simile, o un oggetto grande al suo interno, viene **riconosciuta** in una nuova immagine confrontando **ogni feature** con il database e trovando le corrispondenze candidate in base alla **distanza euclidea** tra i vettori di feature;
- seguono altri raffinamenti, **fuori dall’ambito del corso**; SURF si usa in modo simile.

**Esempi d’uso** (pp. 58–60):

- **trovare un oggetto** in una scena complessa anche con **scala e rotazione diverse**: la potenza della tecnica è notevole;
- **stima robusta**: in visione artificiale serve a ottenere risultati affidabili anche con dati **rumorosi o imperfetti**. Identificando gli **stessi punti** dell’oggetto in **viste diverse** si può **ricostruire il modello 3D** dell’oggetto e della scena;
- **robot con telecamere** ricostruiscono la scena davanti a loro **durante il movimento**: SIFT abilita molte funzionalità avanzate dei sistemi intelligenti.

<!-- SOURCE_VISUAL id="IS08-V23" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="58" type="immagini" description="Oggetto trovato con SIFT in una scena complessa con scala e rotazione diverse" reason="Corrispondenze dei keypoint visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V24" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="59" type="immagini" description="Ricostruzione 3D da viste diverse con i punti SIFT corrispondenti" reason="Viste e ricostruzione visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**SURF in tre passi** (p. 61):

1. **approssima la DoG con filtri a scatola** (*box filter*), molto più veloci: filtri a scatola di dimensioni diverse, che approssimano il **Laplaciano di Gaussiana** (LoG), vengono convoluti con l’**immagine integrale**;
2. un **rilevatore di blob** basato sulla **matrice Hessiana** trova i punti di interesse;
3. per la **descrizione** delle feature usa le **risposte di wavelet**.

> 💡 **Perché l’immagine integrale accelera (nota didattica).** Nell’immagine integrale ogni pixel contiene la somma di tutti i pixel sopra e a sinistra. La somma su un rettangolo qualsiasi si ottiene allora con **4 accessi**, indipendentemente dalla sua dimensione: un filtro a scatola grande costa quanto uno piccolo.

<!-- SOURCE_VISUAL id="IS08-V25" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="61" type="schema" description="SURF: filtri a scatola che approssimano gaussiana e LoG" reason="Forma dei filtri visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3. Confronto di istogrammi**

_(slide p. 62)_

**Procedura**:

1. calcolare l’**istogramma** dell’immagine, o dei quadranti, o di blocchi;
2. creare un **vettore di feature**;
3. usare una **metrica** per confrontare le immagini, per esempio la **metrica di Wasserstein**.

| **Pro** | **Contro** |
|---|---|
| **molto veloce**: il confronto avviene su un **breve vettore 1D** | **forme e pattern non contano** |
| compatibile con **scala e rotazione** | **troppo semplicistico**: una **banana** e una **spiaggia** risultano simili |

> 💡 **Perché banana e spiaggia si somigliano.** L’istogramma conta **quanti pixel** hanno ciascun colore, ma perde **dove** si trovano. Una banana gialla su sfondo chiaro e una spiaggia di sabbia hanno distribuzioni di colore simili. Calcolare istogrammi **per blocchi** recupera un po’ di informazione spaziale.

<!-- SOURCE_VISUAL id="IS08-V26" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="62" type="immagini" description="Istogrammi simili di una banana e di una spiaggia" reason="Immagini e istogrammi visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4. Hash delle immagini: aHash, pHash, dHash**

_(slide pp. 63–67)_

> 📌 **Hash di un’immagine** (p. 63). L’immagine viene ridotta a un piccolo **codice hash**, una specie di **impronta digitale**, identificandone le **feature salienti** e calcolando l’hash di una loro **rappresentazione compatta**, invece che dei dati grezzi del file. Due immagini si confrontano **contando le posizioni di bit diverse**: la **distanza di Hamming**.
> $$d_H(\mathbf{h}_1, \mathbf{h}_2) = \sum_{i=1}^{64} \big[\, h_{1,i} \neq h_{2,i} \,\big]$$

> ⚠️ **Hash del file contro hash percettivo.** MD5 e SHA-1 (§1.4) cambiano completamente per un solo pixel diverso: controllano l’**identità binaria**. Gli hash percettivi sono progettati al contrario: immagini **visivamente simili** hanno hash **uguali o con pochi bit diversi**, quindi una piccola distanza di Hamming.

I tre metodi hanno **passi iniziali simili** (p. 64): guardano rispettivamente la **media**, la **percezione** (frequenze) e le **differenze** (gradiente), e producono una feature **binaria** a 64 bit.

| Passo | **aHash** (*average*, p. 64) | **pHash** (*perceptive*, p. 65) | **dHash** (*difference*, p. 67) |
|---|---|---|---|
| 1. ridurre la dimensione | **8 × 8** (R, G, B) | **32 × 32** (R, G, B) | **9 × 8** (R, G, B) |
| 2. ridurre a grigio | 8 × 8 = 64 pixel | 32 × 32 | **72** pixel grigi |
| 3. trasformare | — | calcolare la **DCT** (*Discrete Cosine Transform*), che separa l’immagine in un insieme di **frequenze**, come fanno JPEG e molti altri formati | calcolare le **differenze tra pixel adiacenti** (direzioni relative del gradiente): 9 pixel per riga danno **8 differenze**, e **8 righe × 8 differenze = 64 bit** |
| 4. ridurre | — | tenere solo la parte **in alto a sinistra 8 × 8** della DCT (le basse frequenze) | — |
| 5. soglia | la **media dei grigi** | la **media $T$** dei valori DCT, calcolata sui **63 valori** esclusa la prima componente | confronto tra pixel vicini |
| 6. ridurre a bit | un bit per pixel in base al confronto con la media | un bit per coefficiente in base al confronto con $T$ | ogni bit indica se il pixel **a sinistra** è **più luminoso** di quello a destra |
| 7. unire | i bit in un **intero a 64 bit**: l’hash | i bit in un **intero a 64 bit** | i bit in un **intero a 64 bit** |

> 📌 **La regola del bit (nota didattica).** Nel testo delle slide la soglia è scritta in modo contratto («0 if gray > below the mean gray», «0 if DCT > below T»). Nella formulazione standard, per aHash
> $$h_i = \begin{cases} 1 & \text{se } g_i > \bar{g} \\ 0 & \text{altrimenti} \end{cases} \qquad \bar{g} = \frac{1}{64} \sum_{i=1}^{64} g_i$$
> e analogamente per pHash con $T$ al posto di $\bar{g}$. La convenzione opposta (1 sotto la media) funziona allo stesso modo, purché sia usata per tutte le immagini.

> 💡 **Perché si esclude il primo coefficiente DCT (nota didattica).** Il primo valore in alto a sinistra è la componente **continua** (DC): rappresenta la luminosità **media** dell’immagine ed è molto più grande degli altri. Includerlo sposterebbe la media $T$ e renderebbe l’hash sensibile alla semplice luminosità.

> ⚠️ **Descrizione di dHash.** La prima frase della p. 67 dice che dHash confronta ogni differenza «con la media delle differenze», ma i passi successivi descrivono il confronto diretto tra **pixel adiacenti** («il pixel a sinistra è più luminoso di quello a destra?»): è questa la regola di dHash. Il metodo è **molto veloce** perché non richiede né medie né trasformate.

<!-- SOURCE_VISUAL id="IS08-V27" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="65" type="schema" description="pHash: riduzione 32x32, DCT e blocco 8x8 in alto a sinistra" reason="Passaggi grafici della trasformata visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Immagini con lo stesso hash** (p. 66). La slide mostra immagini con hash uguale e l’uso della libreria Python **ImageHash**:

```python
>>> hash = imagehash.average_hash(Image.open('test.png'))
>>> print(hash)
d879f8f89b1bbf
```

> ⚠️ **aHash, non pHash.** Il titolo della slide parla di immagini con lo stesso **pHash**, ma il codice usa `average_hash`, cioè **aHash**. Per il pHash la libreria offre `imagehash.phash`. L’esempio è quello della documentazione di ImageHash.

<!-- SOURCE_VISUAL id="IS08-V28" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="66" type="immagini" description="Immagini diverse con lo stesso hash percettivo e codice ImageHash" reason="Immagini con hash uguale visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS08-V29" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="67" type="schema" description="dHash: riduzione 9x8, differenze tra pixel adiacenti e hash a 64 bit" reason="Passaggi grafici visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.5. Copyright e video fingerprinting**

_(slide p. 68)_

- Con tecniche simili (**Content ID**), **YouTube** controlla la similarità dei **fotogrammi** dei video per verificare se si sono caricati fotogrammi **protetti da copyright**.
- Il metodo esatto **non è divulgato**, per non aiutare le **contromisure**.
- Nel **video fingerprinting** tecniche simili a **pHash e SIFT** vengono applicate a **suoni** e **gruppi di fotogrammi** per creare un **identificatore unico**.

<!-- SOURCE_VISUAL id="IS08-V30" source="Lesson_08_ErrorsInDatasets_SimilarityInDatasets_and_images.pdf" page="68" type="immagine" description="Content ID di YouTube e video fingerprinting" reason="Immagine della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Campione sbagliato** / **etichetta sbagliata** | il dato è fuorviante o incompleto / il dato è giusto ma l’etichetta no |
| **Errore del supervisore** / **errore di conversione** | diagnosi umana errata o superata nel tempo / etichetta prodotta da una regola o conversione automatica |
| **Duplicati con stessa etichetta** / **con etichette opposte** | spreco di risorse / problema di addestramento |
| **$N(N-1)$** / **$N(N-1)/2$** | confronti tra coppie ordinate / coppie distinte |
| **Hash del file** (MD5, SHA-1) / **hash percettivo** (aHash, pHash, dHash) | identità binaria esatta / similarità visiva con distanza di Hamming |
| **Duplicati accidentali** / **data augmentation** | nessuna informazione nuova / variazioni controllate che migliorano la generalizzazione |
| **Similarità** / **distanza** | grande per dati simili / piccola per dati simili |
| **Similarità coseno** / **distanza coseno** | $\cos\theta \in [-1, 1]$ / $1 - \cos\theta \in [0, 2]$ |
| **Manhattan** / **euclidea** / **Chebyshev** | Minkowski con $p = 1$ / $p = 2$ / $p \to \infty$ |
| **Estrazione banale** / **ingegnerizzata** / **neurale** | molte feature rumorose e rete complessa / poche feature potenti e rete semplice / feature dagli strati nascosti |
| **SIFT** / **SURF** | migliore con scale diverse / più veloce (circa 3×), migliore con rotazione e sfocatura |
| **Keypoint** / **istogrammi** / **hash** | robusti a scala e rotazione ma costosi / velocissimi ma ignorano le forme / impronte compatte confrontate con Hamming |
| **aHash** / **pHash** / **dHash** | soglia sulla media dei grigi / soglia sulle basse frequenze DCT / confronto tra pixel adiacenti |

---

### **7. Sintesi della lezione**

_(slide p. 69)_

> ✅ **Punti principali** (p. 69).
> - **Errori di etichettatura**: supervisori che sbagliano, diagnosi che **cambiano nel tempo**, etichette prodotte da **conversioni automatiche**.
> - **Controlli**: duplicati con etichette uguali o opposte, costo $N(N-1)$ dei confronti, procedura di verifica con seconda lettura, **hash MD5/SHA-1** per l’identità esatta.
> - **Similarità e distanza**: la base del **pattern recognition**; la metrica e la soglia dipendono dall’**applicazione** (identificare il singolo o riconoscere la categoria).
> - **Metriche nei dataset**: euclidea, Manhattan, coseno, Chebyshev, Minkowski.
> - **Metriche per le immagini**: istogrammi; keypoint **SIFT** e **SURF**; hash percettivi **aHash**, **dHash**, **pHash** confrontati con la distanza di Hamming.
