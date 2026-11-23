## **Lezione 8: Errori nei dataset e similarità tra dati e immagini**

Gli errori nei dati possono nascere dalle etichette, dalla loro provenienza, dal tempo o da duplicati. La seconda parte sviluppa la nozione di similarità: il confronto dipende dalla rappresentazione scelta e dallo scopo, dalla verifica di file identici alla ricerca semantica e alla corrispondenza di punti fisici fra immagini.


### **0. Panoramica**


La trattazione si articola nei seguenti temi:

1. **errori di etichettatura**: errori dei supervisori, cambiamenti nel tempo, controlli;
2. **similarità e distanza**: il quadro generale del pattern recognition;
3. **metriche nei dataset**: euclidea, Manhattan, coseno, Chebyshev, Minkowski;
4. **metriche per le immagini**: istogrammi, SIFT (*Scale-Invariant Feature Transform*, trasformata di caratteristiche invarianti alla scala), SURF (*Speeded-Up Robust Features*, caratteristiche robuste accelerate), aHash, dHash, pHash.

**Dalla qualità dei dati alla ricerca.** Un dataset affidabile richiede una sequenza di decisioni: raccolta e armonizzazione, prevenzione del leakage, controllo di etichette e duplicati, scelta delle rappresentazioni e ricerca di elementi simili.



---

### **1. Etichette ed errori di etichettatura**


#### **1.1. A che cosa servono le etichette**

La preparazione dei dati è il **passo 2** del flusso di lavoro del ML. È **troppo spesso sottovalutata**, e se fatta male provoca una **perdita rilevante di prestazioni**.

![](imgs/Pasted%20image%2020261123113612.png)

> 📌 **Ground truth**. Le etichette sono le **fondamenta** per addestrare e valutare i modelli, soprattutto nell’apprendimento supervisionato. La *ground truth* è l’insieme delle **etichette che il modello dovrebbe imparare a predire** o approssimare. Le etichette sono spesso **codificate**: 1 = «auto», 2 = «camion», …

![](imgs/Pasted%20image%2020261123113702.png)

| Uso delle etichette | Perché conta |
|---|---|
| **addestramento** | essenziali nell’apprendimento supervisionato e semi-supervisionato; **più etichette** aiutano a **ridurre la complessità** necessaria del modello (sullo spettro dati-conoscenza, reti tradizionali e profonde) |
| **valutazione e validazione** | permettono di misurare le prestazioni di un modello; nell’apprendimento **non supervisionato** è difficile dire se l’output è corretto, mentre con le etichette l’accuratezza si **misura con precisione** |
| **benchmarking e classifiche** | dalle etichette si calcolano **figure di merito** come l’accuratezza; i dataset etichettati sono il **banco di prova comune** per confrontare modelli e approcci. Per costruire l’asse dell’accuratezza nel grafico «accuratezza contro operazioni» servono etichette |
| **interpretabilità** | le etichette aiutano a **spiegare** le decisioni del modello: un dataset supervisionato favorisce la spiegabilità |
| **transfer learning** | dati etichettati di un dominio servono a **pre-addestrare** modelli per compiti o domini **correlati**, risparmiando tempo e risorse quando le etichette del dominio di destinazione sono **scarse**; si possono anche **aggiungere immagini specifiche** in base alle etichette presenti in due database DB1 e DB2 |

![](imgs/Pasted%20image%2020261123113722.png)

![](imgs/Pasted%20image%2020261123113831.png)

![](imgs/Pasted%20image%2020261123114109.png)

![](imgs/Pasted%20image%2020261123114119.png)

#### **1.2. Errori nelle etichette**


**Esempi già visti**:

- il **campione è corretto, ma l’etichetta è sbagliata**: un’immagine etichettata «CAT» che non è un gatto;
- il **campione è fuorviante o incompleto** (data leakage): il frammento audio etichettato «love» che contiene anche «you», la cellula ritagliata male.

![](imgs/Pasted%20image%2020261123114224.png)

**Errori diretti del supervisore**. Le etichette dei dataset vengono tipicamente da **supervisori umani**, che possono sbagliare. Esempio: una **diagnosi medica** con input radiografie ed esami del sangue e output la diagnosi, per esempio «sano».

![](imgs/Pasted%20image%2020261123114250.png)

#### **1.3. Provenienza, incertezza e cambiamento delle etichette**

Ogni etichetta dovrebbe essere accompagnata dai metadati che ne permettono la verifica: **chi** l’ha assegnata (esperto, annotatore, regola o modello), **quando** è stata assegnata e quale **versione** delle linee guida, dello strumento o del software è stata usata. Conservare queste informazioni accanto a $y$ rende le etichette tracciabili e confrontabili; valori prodotti da procedure diverse non sono automaticamente equivalenti.

![](imgs/Pasted%20image%2020261123114308.png)

Anche due esperti possono dissentire: la *ground truth* è una decisione operativa documentata, non sempre una verità priva d’incertezza. Il coefficiente **κ di Cohen** corregge l’accordo osservato $p_o$ per l’accordo atteso per caso $p_e$:

$$\kappa=\frac{p_o-p_e}{1-p_e}$$

Con 100 esempi, 85 accordi osservati e un accordo casuale atteso pari a 0,5, si ottiene 

$$\kappa=(0{,}85-0{,}5)/(1-0{,}5)=0{,}70$$

 $\kappa=1$ indica accordo perfetto, $\kappa=0$ un accordo pari al caso. I disaccordi possono richiedere una terza valutazione, un voto di maggioranza, etichette probabilistiche oppure un flag di incertezza. La qualità delle annotazioni pone un limite alla prestazione misurabile: se gli annotatori non concordano oltre una certa soglia, aumentare il numero di esempi senza migliorare linee guida o informazioni non risolve il problema.

![](imgs/Pasted%20image%2020261123114738.png)

**La diagnosi cambia nel tempo**. Una prima radiografia viene etichettata «**polmonite batterica**»; una **seconda radiografia**, fatta con una **macchina migliore**, porta alla diagnosi di «**covid-19**». Se l’etichetta della prima immagine **non viene aggiornata**, il modello impara un’associazione sbagliata, e questo **limita la capacità** del modello di fare **diagnosi precoce**: proprio le immagini iniziali, le più utili per anticipare la malattia, hanno l’etichetta errata. Sulla figura «pneumonia» è scritto «penumonia».

![](imgs/Pasted%20image%2020261123114957.png)

La stessa dinamica si presenta fuori dalla medicina: un difetto di produzione può essere confermato solo nel collaudo finale o dopo un reso; un guasto può verificarsi settimane dopo la misura; una transazione può essere riconosciuta come frode solo dopo il chargeback; una misura ambientale può essere corretta quando si completa la calibrazione. Il campione può quindi essere disponibile al tempo $t_0$, mentre l’etichetta arriva più tardi o viene revisionata. Occorre registrare la data della misura e quella dell’etichetta e usare suddivisioni temporali coerenti: un addestramento non può usare in anticipo un esito che in produzione non sarebbe ancora noto.

![](imgs/Pasted%20image%2020261123115105.png)

**Errori di interscambio e conversione automatica**. Un’etichetta viene creata **automaticamente** con una regola sul BMI (*Body Mass Index*, indice di massa corporea, IMC):

$$\text{BMI} = \frac{\text{massa [kg]}}{\text{altezza [m]}^2}, \qquad \text{se } 25{,}0 < \text{BMI} < 30 \;\Rightarrow\; y = \text{sovrappeso}$$

Applicata a **Yuri Chechi**, campione olimpico agli **anelli** nel **1996**, fotografato nel 2022 a oltre 50 anni, dopo una **conversione automatica di formato** la regola produce l’etichetta «**grasso**».

![](imgs/Pasted%20image%2020261123115237.png)

> 💡 **Perché la regola sbaglia (nota didattica).** Il BMI non distingue **massa muscolare** da massa grassa: un atleta molto muscoloso può superare 25 senza essere sovrappeso. Un’etichetta generata da una regola automatica eredita tutti i **limiti della regola**, e una conversione di unità o di formato sbagliata può spostare i valori in una fascia diversa senza che nessuno se ne accorga.

#### **1.4. Controlli di base: i duplicati**


**Vettori di input identici**:

| Caso | Conseguenza |
|---|---|
| duplicati con **la stessa etichetta** | **nessun problema di addestramento**, ma spreco di spazio, memoria e tempo di elaborazione |
| duplicati con **etichette opposte** ($x_1 = x_2$, ma $y_1$ = «gatto» e $y_2$ = «cane») | **problemi di addestramento**: il modello riceve due risposte contraddittorie per lo stesso input |

![](imgs/Pasted%20image%2020261123115802.png)

**Il costo del controllo**. Il controllo va fatto sul database di addestramento **e** su quello di validazione, e non è facile con database grandi (oltre un milione di immagini). Un semplice confronto **uno contro N** richiede

$$\text{iterazioni} = N\,(N-1)$$

**Esempio: il dataset Serengeti**:

![](imgs/Pasted%20image%2020261123120010.png)

- **non etichettato**: **3,2 milioni** di immagini, corrispondenti a **1,2 milioni** di eventi di cattura (sequenze di immagini; in totale 7,1 milioni di immagini). Il non supervisionato **non è da buttare**: serve per la messa a punto, l’estrazione delle feature, altri studi;
- **test set etichettato**: etichettato da **volontari**, **17 400** eventi di cattura;
- un controllo di base richiede, si possono arrivare a centinaia di milioni di confronti; con $N$ elementi, confrontare tutte le coppie ordinate richiede $N(N-1)$ operazioni.

> ⚠️ **Verifica del numero.** Con $N = 17\,400$ si ottiene $N(N-1) = 17\,400 \times 17\,399 \approx 302{,}7$ milioni, non 285 milioni. Inoltre ogni coppia viene contata due volte: le coppie **distinte** da confrontare sono $N(N-1)/2 \approx 151{,}4$ milioni. L’ordine di grandezza, centinaia di milioni di confronti, resta il messaggio della figura.

![](imgs/Pasted%20image%2020261123120134.png)

---

**Una procedura molto semplice**, solo come primo passo dell’analisi:

1. **caricare** $x_i$ e $x_j$;
2. **controllare** se sono duplicati;
3. **le etichette sono diverse?**
   - **sì** (caso cattivo), scegliere un’opzione:
     - **opzione 1**: rimuovere il campione;
     - **opzione 2**: chiedere assistenza a un supervisore (**seconda lettura**), poi **correggere l’etichetta** e unire il campione, oppure scartarlo;
   - **no** (caso buono): **scartare uno dei due** campioni, aggiornare gli indici $i, j$ e **tornare al passo 1**.

![](imgs/Pasted%20image%2020261123120150.png)

Quale opzione scegliere dipende dal contesto: il dataset è **enorme**? I dati sono **costosi**?

> 💡 **Come scegliere (nota didattica).** Con milioni di immagini economiche, rimuovere i campioni contraddittori costa poco. In un dataset medico in cui ogni esame è costoso e raro, la seconda lettura da parte di un esperto vale il tempo speso.

#### **1.5. Hash per trovare i duplicati**


**Il problema**. Con dati o vettori grandi, un confronto diretto `if (x_i == x_j)` richiede di confrontare **elemento per elemento** o **pixel per pixel**: richiede tempo, anche se è perfettamente **parallelizzabile** (adatto alle GPU con CUDA). Alternative:

- usare un **hash del file** standard, se già disponibile;
- gli algoritmi classici MD5 (*Message-Digest Algorithm 5*, algoritmo di digest dei messaggi versione 5) e SHA-1 (*Secure Hash Algorithm 1*, algoritmo di hash sicuro versione 1);
- calcolare le informazioni di **hash delle immagini offline**;
- usare **funzioni di hash specifiche per immagini** (§5.4).

**Il codice**):

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

> 📌 **Che cosa verifica l’hash del file**.
> - Cambiare **anche un solo pixel** produce un hash **completamente diverso**.
> - Modificare i metadati EXIF (*Exchangeable Image File Format*, formato di file immagine scambiabile) (posizione, data) cambia l’hash, **anche se l’immagine appare identica**.
> - Il metodo **non verifica la similarità visiva**: controlla solo l’**identità binaria esatta**.
> - MD5 e SHA-1 hanno numeri di bit diversi, ma per questo scopo vanno **bene entrambi**; **MD5 è più veloce**.
> - Per prestazioni migliori conviene **calcolare e memorizzare prima** gli hash di tutte le immagini, e confrontarli poi in un **ciclo separato e ottimizzato**.

> 💡 **Perché memorizzare gli hash (nota didattica).** Confrontare due stringhe di 32 caratteri (MD5 = 128 bit) è molto più rapido che confrontare due immagini da milioni di pixel. Anzi, inserendo gli hash in un **dizionario** i duplicati esatti si trovano in **una sola passata**, in tempo proporzionale a $N$, invece dei $N(N-1)/2$ confronti a coppie. MD5 e SHA-1 non sono più sicuri contro chi **fabbrica** collisioni di proposito, ma per trovare duplicati accidentali restano adatti.

Per immagini, vettori e altri dati non strutturati, la ridondanza si valuta con una misura di similarità, per esempio:

$$\text{se } \operatorname{similarità}(x_i, x_j) > \text{soglia fissata}$$

perché due campioni «uguali» possono differire per **rumore** o **correzioni di colore**, **punto di vista**, **momento o luogo** di acquisizione.

![](imgs/Pasted%20image%2020261123120411.png)

La quantità di confronti cresce quadraticamente: il numero di coppie distinte è $\binom{N}{2}=N(N-1)/2$. Con 17 400 campioni si ottengono circa 151 milioni di coppie; su milioni di immagini il confronto esaustivo diventa impraticabile, quindi si usano indici o una generazione preliminare di candidati. Per decidere se due elementi sono duplicati serve una soglia $\tau$ calibrata sul compito e su coppie etichettate.

Le nozioni di similarità si dispongono su livelli diversi. L’**identità esatta** confronta i byte; la similarità **percettiva** riconosce immagini che appaiono quasi uguali dopo ricompressione o ridimensionamento; la similarità **semantica** collega contenuti con lo stesso significato anche quando pixel e parole differiscono. Un hash del file, un hash percettivo e un embedding rispondono quindi a domande diverse.

| Domanda | Rappresentazione e confronto |
|---|---|
| I file sono copie identiche? | Byte e hash di contenuto, poi uguaglianza. |
| Le immagini sono quasi duplicate? | Hash percettivo e distanza di Hamming con soglia. |
| Ritraggono lo stesso punto fisico? | Descrittori locali, corrispondenze e verifica geometrica. |
| Hanno significato simile? | Embedding appresi e distanza L2 o coseno; anche embedding multimodali per immagine e testo. |


---

### **2. Similarità nei dataset**


La domanda della sezione: che cosa è **rilevante** e che cosa è **inutile**?

**Esempio: riconoscimento facciale delle mucche**. Alcune startup usano il riconoscimento facciale per aumentare la **produttività delle mucche da latte**: **tracciamento dell’attività**, **distribuzione automatica del cibo**, **somministrazione dei farmaci**. I moduli principali sono **due reti neurali**: una **segmenta il muso**, l’altra **identifica la mucca**. Le feature possono essere le **macchie del muso** o le **macchie del corpo**. Sulla figura «cow» è scritto «caw».

> 📌 **La metrica dipende dall’applicazione**. La metrica di similarità da scegliere **dipende dal tipo di applicazione**.

![](imgs/Pasted%20image%2020261123120525.png)

**Perché controllare la similarità**:

- dati o immagini **troppo simili** forniscono **poca informazione in più**;
- rendono il dataset **più pesante da gestire**: memorizzazione, caricamento, addestramento, ecc.;
- la metrica di similarità va **regolata sull’applicazione**.

Esempio: due cani **molto simili** sono **troppo simili** per insegnare a un modello a riconoscere la **razza**, ma sono **necessari** per identificare **il singolo cane**.

**Regolare il livello di similarità**. Con la regola `se similarità(x_i, x_j) > soglia`:

| Compito | Due immagini molto simili dello stesso animale sono… |
|---|---|
| **identificazione** del singolo individuo | **ottimi campioni** |
| **rilevamento** del volto o del muso | **ridondanti**: meglio aggiungere **altre immagini** |

![](imgs/Pasted%20image%2020261123120621.png)

**Similarità e data augmentation**. **Troppa similarità** spreca spazio e tempo. **Ma** la **data augmentation**, che crea copie modificate delle stesse immagini (per esempio lo stesso «hotdog» ruotato o ritagliato), **migliora la generalizzazione**: la capacità del modello di imparare dai dati disponibili e applicare ciò che ha imparato altrove. La data augmentation sarà trattata nella parte di deep learning.

> 💡 **Nessuna contraddizione.** I duplicati **accidentali** non aggiungono informazione; le varianti **controllate** della data augmentation insegnano al modello che rotazione, luminosità o ritaglio **non cambiano la classe**. La differenza sta nell’intenzione: la seconda introduce proprio le variazioni che il modello deve imparare a ignorare.

![](imgs/Pasted%20image%2020261123120727.png)

**Similarità nei dataset in generale**. La lezione si concentra sulle immagini, ma l’approccio vale per dati di ogni tipo:

- con **dati non strutturati** conviene **estrarre feature** per poter usare le tecniche dei dati strutturati;
- **dati strutturati → vettori di feature → metriche**:
  - norma **euclidea** o distanza di **Manhattan**;
  - distanza di **Mahalanobis**;
  - **coefficiente di correlazione di Pearson**;
  - misure di **complessità, coerenza, struttura, entropia**;
  - …

![](imgs/Pasted%20image%2020261123121332.png)

> 📌 **Mahalanobis e Pearson (nota didattica).** La distanza di Mahalanobis tiene conto della **covarianza** $S$ dei dati: pesa meno le direzioni in cui i dati variano molto. Il coefficiente di Pearson misura la **correlazione lineare** tra due vettori:
> $$d_M(\mathbf{x}, \mathbf{y}) = \sqrt{(\mathbf{x} - \mathbf{y})^{\top} S^{-1} (\mathbf{x} - \mathbf{y})} \qquad \rho(\mathbf{x}, \mathbf{y}) = \frac{\sum_i (x_i - \bar{x})(y_i - \bar{y})}{\sqrt{\sum_i (x_i - \bar{x})^2}\,\sqrt{\sum_i (y_i - \bar{y})^2}}$$
> Se $S$ è la matrice identità, Mahalanobis coincide con la distanza euclidea.

---

### **3. Similarità e pattern recognition**


#### **3.1. La similarità è la base del pattern recognition**

La similarità è **la base della maggior parte dei metodi di machine learning**. Lo schema del sistema di pattern recognition ha un ramo di **addestramento** (acquisizione → database → estrazione delle feature → training) e uno di **test** (acquisizione → estrazione delle feature → **matching** → esito, per esempio «mela»).

![](imgs/Pasted%20image%2020261123121418.png)

La similarità **non serve solo a pulire il dataset**: il blocco di **matching** confronta le feature del nuovo campione con quelle del database usando proprio una **misura di similarità o distanza**, e produce l’esito «classe $x$».

![](imgs/Pasted%20image%2020261123121559.png)

**Lo spazio di input**. Estrarre un **vettore di feature** da dati strutturati o non strutturati permette di ragionare **in un quadro comune**: oggetti come un panda, un volto, un fungo diventano **punti**.

![](imgs/Pasted%20image%2020261123121636.png)

**Dallo spazio di input allo spazio delle feature**. L’estrazione delle feature trasforma

$$[\,\text{input}_1, \text{input}_2, \dots, \text{input}_N\,] \;\longrightarrow\; [\,\text{feature}_1, \text{feature}_2, \dots, \text{feature}_M\,]$$

> 📌 **Ingegneria delle feature**. Un buon progetto delle feature dà **meno dimensioni** e **cluster migliori**.

![](imgs/Pasted%20image%2020261123121856.png)

---

#### **3.2. Encoding e Representation Space**

![](imgs/Pasted%20image%2020261123123935.png)

![](imgs/Pasted%20image%2020261123123946.png)

![](imgs/Pasted%20image%2020261123123954.png)

#### **3.3. Dove agisce la rete neurale**

![](imgs/Pasted%20image%2020261123124024.png)

Il pattern recognition si può organizzare in modi diversi, secondo **quale parte** dello schema viene affidata a una rete neurale:

| Schema | Che cosa fa la rete | Complessità per la rete |
|---|---|---|
| **rete come classificatore** | la rete **memorizza** il database e svolge il **matching** | **alta** |
| **rete come funzione di similarità** | la rete svolge **solo il matching**; database ed estrazione delle feature restano fuori | **più bassa** |
| **rete profonda** | **tutti i passi**, estrazione delle feature compresa, sono **dentro** il modello | tutta nella rete |

![](imgs/Pasted%20image%2020261123124138.png)

![](imgs/Pasted%20image%2020261123124311.png)

**Estrazione banale contro ingegneria delle feature**:

![](imgs/Pasted%20image%2020261123124330.png)

| Estrazione delle feature | Rete necessaria | Commento |
|---|---|---|
| **banale** | **rete complessa** | si estraggono **molte feature rumorose** |
| **ingegnerizzata** | **rete più semplice** | **poche feature potenti** |
| **neurale** | **rete più semplice** | si usa una rete per estrarre le feature dai suoi **strati nascosti** |
| **neurale + distanza classica** | **nessuna rete** nel matching: basta una **distanza euclidea** | investire calcolo **nel primo passo** permette **decisioni semplici** e **migliora la spiegabilità** |

![](imgs/Pasted%20image%2020261123124426.png)

![](imgs/Pasted%20image%2020261123124451.png)

> ⚠️ **Stesso estrattore per database e test**. Il database deve contenere **lo stesso tipo di feature** prodotto dall’estrattore usato in test: se si cambia estrattore, le feature del database vanno **ricalcolate**.

![](imgs/Pasted%20image%2020261123124525.png)

> 💡 **Il senso dell’ultimo schema (nota didattica).** È l’idea degli **embedding**: una rete pre-addestrata trasforma ogni immagine in un vettore, e due immagini simili hanno vettori vicini. A quel punto riconoscere, deduplicare o cercare immagini è solo una questione di **distanze**, e la decisione è spiegabile: «questa immagine è la più vicina a quella».

Una rete neurale può realizzare tre funzioni diverse: classificare $x$ direttamente in una classe $y$; codificare $x$ in un vettore $z=f_\theta(x)$ (*embedding*); oppure apprendere una funzione di similarità $s(z_i,z_j)$ fra coppie, come nelle reti siamesi. La scelta della rappresentazione precede quella della metrica e determina che cosa il confronto può rilevare. Dati tabellari si codificano con colonne normalizzate e variabili one-hot; testo, immagini e audio possono essere trasformati da encoder appresi. In entrambi i casi si confrontano vettori nello stesso spazio.

---

### **4. Similarità e distanza nello spazio delle feature**


#### **4.1. Dalla similarità alla distanza**

Si ragiona in termini di **similarità**, ma spesso la metrica calcolata è una **distanza**. In un certo senso la similarità è l’**inverso** della distanza:

- distanza → 0, allora similarità → **infinito** (massima);
- distanza → infinito, allora similarità → **0**.

La conversione giusta dipende dall’**applicazione** e dalle **proprietà matematiche** desiderate. Un esempio:

$$\text{similarità} = \frac{1}{1 + \text{distanza}}$$

dove l’«$1+$» evita la divisione per zero quando la distanza tende a 0. **Spesso non serve alcuna conversione**: basta usare la distanza e trovare le **soglie** adatte.

> 💡 **Due scale diverse.** Con la formula $\frac{1}{1+d}$ la similarità non va all’infinito ma resta **tra 0 e 1**: vale 1 per $d = 0$ e tende a 0 per $d \to \infty$. È spesso preferibile proprio perché è limitata.

#### **4.2. Le metriche di base**

Le metriche principali descrivono similarità e distanza attraverso formule e rappresentazioni grafiche. Per due vettori $\mathbf{p}, \mathbf{q} \in \mathbb{R}^N$ le **definizioni standard** (nota didattica) sono:

| Metrica | Formula | Sinonimi |
|---|---|---|
| **euclidea** | $d_2(\mathbf{p}, \mathbf{q}) = \sqrt{\sum_{i=1}^{N} (p_i - q_i)^2}$ | norma L2, distanza «del righello» |
| **Manhattan** | $d_1(\mathbf{p}, \mathbf{q}) = \sum_{i=1}^{N} \lvert p_i - q_i \rvert$ | norma L1, taxicab, city-block |
| **coseno** | $\cos\theta = \dfrac{\mathbf{p} \cdot \mathbf{q}}{\lVert \mathbf{p} \rVert \, \lVert \mathbf{q} \rVert}$, distanza coseno $= 1 - \cos\theta$ | — |
| **Chebyshev** | $d_\infty(\mathbf{p}, \mathbf{q}) = \max_{i} \lvert p_i - q_i \rvert$ | L-infinito, Lmax, distanza «della scacchiera» |
| **Minkowski** | $d_p(\mathbf{p}, \mathbf{q}) = \left( \sum_{i=1}^{N} \lvert p_i - q_i \rvert^{p} \right)^{1/p}$ | generalizza le precedenti |

![](imgs/Pasted%20image%2020261123125054.png)

**La metrica coseno**. Misura l’**angolo** tra i due vettori, non la loro lunghezza. Lo schema della indica tre casi: vettori con la **stessa direzione** (valore 1), **ortogonali** (valore 0), **opposti** (valore −1).

![](imgs/Pasted%20image%2020261123125114.png)

> ⚠️ **Similarità o distanza coseno?** Questa quantità è talvolta chiamata «distanza» che va da 1 a −1, ma quei valori sono della **similarità** coseno $\cos\theta$: vale 1 quando i vettori sono allineati. La **distanza** coseno usata nelle librerie è di solito $1 - \cos\theta$, che vale 0 per vettori allineati e 2 per vettori opposti.

![](imgs/Pasted%20image%2020261123125124.png)

**Distanza di Chebyshev**. Dà la **componente di modulo massimo** del vettore differenza. Esempio: per $X = [-6, 4, 2]$ la norma L-infinito rispetto all’origine è

$$\lVert X \rVert_\infty = \max(\lvert -6 \rvert, \lvert 4 \rvert, \lvert 2 \rvert) = 6$$

> 💡 **Lo stesso esempio con le altre norme (nota didattica).** Per $X = [-6, 4, 2]$: $\lVert X \rVert_1 = 6 + 4 + 2 = 12$ e $\lVert X \rVert_2 = \sqrt{36 + 16 + 4} = \sqrt{56} \approx 7{,}48$. Vale sempre $\lVert X \rVert_\infty \le \lVert X \rVert_2 \le \lVert X \rVert_1$.

La **confronta** graficamente le metriche.

![](imgs/Pasted%20image%2020261123125224.png)

![](imgs/Pasted%20image%2020261123125330.png)

**Distanza di Minkowski**. Il parametro $p$ la trasforma nelle altre:

| $p$ | Distanza |
|---|---|
| $p = 1$ | **Manhattan** (L1, taxicab, city-block) |
| $p = 2$ | **euclidea** (L2, righello) |
| $p \to \infty$ | **Chebyshev** (L-infinito, Lmax, scacchiera) |

Con due dimensioni ($N = 2$), la mostra l’insieme dei punti a **distanza unitaria** dal centro (il «cerchio unitario») per vari valori di $p$.

> 💡 **Com’è fatto il «cerchio» (nota didattica).** Per $p = 1$ è un **rombo** con i vertici sugli assi; per $p = 2$ è il **cerchio** usuale; per $p \to \infty$ diventa un **quadrato** con i lati paralleli agli assi. Scegliere $p$ significa decidere **quali spostamenti** considerare «vicini».

> ⚠️ **«Adimensionale».** La definisce la metrica «adimensionale». La distanza di Minkowski ha in realtà la **stessa unità di misura delle feature**, ed è adimensionale solo se le feature sono state **normalizzate**. Per lo stesso motivo, feature con scale diverse vanno normalizzate prima di calcolarla.

![](imgs/Pasted%20image%2020261123125410.png)

![](imgs/Pasted%20image%2020261123125510.png)

---

### **5. Similarità tra immagini**

![](imgs/Pasted%20image%2020261123125554.png)

#### **5.1. Usi e approcci**

> 📌 **Similarità tra immagini**. Confronta due immagini e restituisce un valore che indica **quanto sono visivamente simili**:
> $$\text{score} = \operatorname{similarity}(\text{image}_1, \text{image}_2)$$
> - se lo score **tende a 0**, le immagini sono **simili nel contenuto**;
> - se lo score **vale 0**, le immagini sono **identiche**.

> ⚠️ **Uno score che si comporta da distanza.** Con questa convenzione lo score è in realtà una **distanza**: più è piccolo, più le immagini si somigliano (§4.1).

**Tre scenari d’uso principali**:

| Scenario                    | Descrizione                                                                                                       |
| --------------------------- | ----------------------------------------------------------------------------------------------------------------- |
| **pulire i dataset**        | i dataset disordinati sono critici per il progettista, ed eliminare i duplicati a mano è un processo **faticoso** |
| **ricerca per similarità**  | data un’immagine, trovare immagini **visivamente simili** nella libreria multimediale o nei dati degli utenti     |
| **tracciare i cambiamenti** | individuare **modifiche** difficili da vedere in immagini di un progetto o di un’area monitorata                  |

![](imgs/Pasted%20image%2020261123125629.png)

**Tre approcci principali**:

1. **corrispondenza di keypoint**: SIFT, SURF;
2. **istogrammi**;
3. **hash delle immagini**: aHash, dHash, pHash.


![](imgs/Pasted%20image%2020261123125727.png)

---

![](imgs/Pasted%20image%2020261123130713.png)

![](imgs/Pasted%20image%2020261123130722.png)

![](imgs/Pasted%20image%2020261123130742.png)

---

#### **5.2. Corrispondenza di keypoint: SIFT e SURF**

![](imgs/Pasted%20image%2020261123132510.png)

![](imgs/Pasted%20image%2020261123132559.png)

![](imgs/Pasted%20image%2020261123132625.png)


**Procedura**:

1. calcolare un’**astrazione** dell’informazione dell’immagine e decidere **localmente, in ogni punto**, se lì esiste una feature del tipo cercato;
2. **confrontare** le feature rilevate tra le due immagini.


| **Pro** | **Contro** |
|---|---|
| confronta immagini con **scale, rotazioni e illuminazione diverse** | un’implementazione ingenua costa $O(n^2 m)$, con $n$ numero di keypoint per immagine e $m$ numero di immagini nel database |

> 💡 **Da dove viene $O(n^2 m)$ (nota didattica).** Per confrontare due immagini con $n$ keypoint ciascuna, ogni keypoint della prima si confronta con tutti quelli della seconda: $n \times n = n^2$ confronti. Ripetuto per $m$ immagini del database, il costo è $n^2 m$. Con 1000 keypoint e un milione di immagini sono $10^{12}$ confronti: per questo servono indici e strutture di ricerca approssimata.

**SIFT e SURF**:

- **SIFT** = *Scale Invariant Feature Transform*; **SURF** = *Speeded Up Robust Features*;
- entrambi sono **potenti**, con molte librerie pubbliche, e servono per similarità tra immagini, **riconoscimento di oggetti**, **registrazione** di immagini, classificazione, **ricostruzione 3D**.

| Aspetto | Precisazione |
|---|---|
| **SIFT** | descrittore locale robusto ai cambi di scala e orientamento; rileva keypoint nello spazio delle scale e ne descrive il gradiente locale |
| **SURF** | progettato come alternativa più rapida, con filtri a scatola, risposta Hessiana e descrittori basati su wavelet |
| Prestazioni relative | velocità e accuratezza dipendono da implementazione, immagini e condizioni; non esiste una superiorità universale |

**SIFT in tre passi**:

1. stima degli **estremi nello spazio delle scale** con la **Differenza di Gaussiane** (DoG);
2. **localizzazione** dei keypoint: i candidati vengono localizzati e **raffinati**, eliminando i punti a **basso contrasto**;
3. assegnazione dell’**orientazione** a ogni keypoint in base al **gradiente locale**, e generazione del **descrittore** locale da **modulo e orientazione** del gradiente.

![](imgs/Pasted%20image%2020261123132751.png)

> 📌 **Differenza di Gaussiane (nota didattica).** Si sfoca l’immagine $I$ con due gaussiane di scala $\sigma$ e $k\sigma$ e si sottraggono i risultati:
> $$D(x, y, \sigma) = \big(G(x, y, k\sigma) - G(x, y, \sigma)\big) * I(x, y)$$
> I massimi e i minimi di $D$ nello spazio e nella scala sono i candidati keypoint: punti che restano «notevoli» anche ingrandendo o rimpicciolendo l’immagine. Il descrittore SIFT standard è un vettore di **128** valori.

![](imgs/Pasted%20image%2020261123132816.png)

**Come si usa SIFT**:

- i keypoint degli oggetti vengono **estratti da immagini di riferimento** e memorizzati in un **database**;
- un’immagine simile, o un oggetto grande al suo interno, viene **riconosciuta** in una nuova immagine confrontando **ogni feature** con il database e trovando le corrispondenze candidate in base alla **distanza euclidea** tra i vettori di feature;
- seguono altri raffinamenti, **richiedono approfondimenti specialistici**; SURF si usa in modo simile.

SIFT è soprattutto una tecnica di **corrispondenza di feature locali**, non un metodo per decidere se due immagini hanno lo stesso significato. È adatto a registrare immagini della stessa scena, unire panorami, localizzare un oggetto che cambia scala o orientamento, stimare struttura 3D e movimento della camera, supportare visual localization e SLAM o allineare un riferimento per ispezionare un difetto. Si rilevano keypoint, se ne descrivono i dintorni, si confrontano i descrittori e si verificano geometricamente le corrispondenze. Un embedding globale può dire che due immagini sono simili; i keypoint aiutano a stabilire quali parti rappresentano lo stesso punto fisico.

![](imgs/Pasted%20image%2020261123132913.png)

**Esempi d’uso**:

- **trovare un oggetto** in una scena complessa anche con **scala e rotazione diverse**: la potenza della tecnica è notevole;
- **stima robusta**: in visione artificiale serve a ottenere risultati affidabili anche con dati **rumorosi o imperfetti**. Identificando gli **stessi punti** dell’oggetto in **viste diverse** si può **ricostruire il modello 3D** dell’oggetto e della scena;
- **robot con telecamere** ricostruiscono la scena davanti a loro **durante il movimento**: SIFT abilita molte funzionalità avanzate dei sistemi intelligenti.

**![](imgs/Pasted%20image%2020261123132928.png)

![](imgs/Pasted%20image%2020261123132943.png)

**SURF in tre passi**:

1. **approssima la DoG con filtri a scatola** (*box filter*), molto più veloci: filtri a scatola di dimensioni diverse, che approssimano il **Laplaciano di Gaussiana** (LoG), vengono convoluti con l’**immagine integrale**;
2. un **rilevatore di blob** basato sulla **matrice Hessiana** trova i punti di interesse;
3. per la **descrizione** delle feature usa le **risposte di wavelet**.

> 💡 **Perché l’immagine integrale accelera (nota didattica).** Nell’immagine integrale ogni pixel contiene la somma di tutti i pixel sopra e a sinistra. La somma su un rettangolo qualsiasi si ottiene allora con **4 accessi**, indipendentemente dalla sua dimensione: un filtro a scatola grande costa quanto uno piccolo.

![](imgs/Pasted%20image%2020261123133007.png)

#### **5.3. Confronto di istogrammi**


**Procedura**:

1. calcolare l’**istogramma** dell’immagine, o dei quadranti, o di blocchi;
2. creare un **vettore di feature**;
3. usare una **metrica** per confrontare le immagini, per esempio la **metrica di Wasserstein**.

| **Pro** | **Contro** |
|---|---|
| **molto veloce**: il confronto avviene su un **breve vettore 1D** | **forme e pattern non contano** |
| compatibile con **scala e rotazione** | **troppo semplicistico**: una **banana** e una **spiaggia** risultano simili |

> 💡 **Perché banana e spiaggia si somigliano.** L’istogramma conta **quanti pixel** hanno ciascun colore, ma perde **dove** si trovano. Una banana gialla su sfondo chiaro e una spiaggia di sabbia hanno distribuzioni di colore simili. Calcolare istogrammi **per blocchi** recupera un po’ di informazione spaziale.

![](imgs/Pasted%20image%2020261123133056.png)

#### **5.4. Hash delle immagini: aHash, pHash, dHash**

![](imgs/Pasted%20image%2020261123130831.png)

> 📌 **Hash di un’immagine**. L’immagine viene ridotta a un piccolo **codice hash**, una specie di **impronta digitale**, identificandone le **feature salienti** e calcolando l’hash di una loro **rappresentazione compatta**, invece che dei dati grezzi del file. Due immagini si confrontano **contando le posizioni di bit diverse**: la **distanza di Hamming**.
> $$d_H(\mathbf{h}_1, \mathbf{h}_2) = \sum_{i=1}^{64} \big[\, h_{1,i} \neq h_{2,i} \,\big]$$

![](imgs/Pasted%20image%2020261123131116.png)

![](imgs/Pasted%20image%2020261123131829.png)

> ⚠️ **Hash del file contro hash percettivo.** MD5 e SHA-1 (§1.4) cambiano completamente per un solo pixel diverso: controllano l’**identità binaria**. Gli hash percettivi sono progettati al contrario: immagini **visivamente simili** hanno hash **uguali o con pochi bit diversi**, quindi una piccola distanza di Hamming.

![](imgs/Pasted%20image%2020261123131917.png)

I tre metodi hanno **passi iniziali simili**: guardano rispettivamente la **media**, la **percezione** (frequenze) e le **differenze** (gradiente), e producono una feature **binaria** a 64 bit.

| Passo | **aHash** (*average*) | **pHash** (*perceptive*) | **dHash** (*difference*) |
|---|---|---|---|
| 1. ridurre la dimensione | **8 × 8** (R, G, B) | **32 × 32** (R, G, B) | **9 × 8** (R, G, B) |
| 2. ridurre a grigio | 8 × 8 = 64 pixel | 32 × 32 | **72** pixel grigi |
| 3. trasformare | — | calcolare la DCT (*Discrete Cosine Transform*, trasformata discreta del coseno), che separa l’immagine in un insieme di **frequenze**, come fanno JPEG (*Joint Photographic Experts Group*, gruppo congiunto di esperti di fotografia) e molti altri formati | calcolare le **differenze tra pixel adiacenti** (direzioni relative del gradiente): 9 pixel per riga danno **8 differenze**, e **8 righe × 8 differenze = 64 bit** |
| 4. ridurre | — | tenere solo la parte **in alto a sinistra 8 × 8** della DCT (le basse frequenze) | — |
| 5. soglia | la **media dei grigi** | la **media $T$** dei valori DCT, calcolata sui **63 valori** esclusa la prima componente | confronto tra pixel vicini |
| 6. ridurre a bit | un bit per pixel in base al confronto con la media | un bit per coefficiente in base al confronto con $T$ | ogni bit indica se il pixel **a sinistra** è **più luminoso** di quello a destra |
| 7. unire | i bit in un **intero a 64 bit**: l’hash | i bit in un **intero a 64 bit** | i bit in un **intero a 64 bit** |

> 📌 **La regola del bit (nota didattica).** Nel testo delle figure la soglia è scritta in modo contratto («0 if gray > below the mean gray», «0 if DCT > below T»). Nella formulazione standard, per aHash
> $$h_i = \begin{cases} 1 & \text{se } g_i > \bar{g} \\\\ 0 & \text{altrimenti} \end{cases} \qquad \bar{g} = \frac{1}{64} \sum_{i=1}^{64} g_i$$
> e analogamente per pHash con $T$ al posto di $\bar{g}$. La convenzione opposta (1 sotto la media) funziona allo stesso modo, purché sia usata per tutte le immagini.

> 💡 **Perché si esclude il primo coefficiente DCT (nota didattica).** Il primo valore in alto a sinistra è la componente **continua** (DC): rappresenta la luminosità **media** dell’immagine ed è molto più grande degli altri. Includerlo sposterebbe la media $T$ e renderebbe l’hash sensibile alla semplice luminosità.

> ⚠️ **Descrizione di dHash.** La prima frase della dice che dHash confronta ogni differenza «con la media delle differenze», ma i passi successivi descrivono il confronto diretto tra **pixel adiacenti** («il pixel a sinistra è più luminoso di quello a destra?»): è questa la regola di dHash. Il metodo è **molto veloce** perché non richiede né medie né trasformate.

![](imgs/Pasted%20image%2020261123131535.png)

**Immagini con lo stesso hash**. Le immagini con hash uguale e l’uso della libreria Python **ImageHash**:

```python
>>> hash = imagehash.average_hash(Image.open('test.png'))
>>> print(hash)
d879f8f89b1bbf
```

> ⚠️ **aHash, non pHash.** La descrizione dell’esempio menziona immagini con lo stesso **pHash**, ma il codice usa `average_hash`, cioè **aHash**. Per il pHash la libreria offre `imagehash.phash`. L’esempio è quello della documentazione di ImageHash.

![](imgs/Pasted%20image%2020261123131552.png)

![](imgs/Pasted%20image%2020261123131617.png)

#### **5.5. Copyright e video fingerprinting**


- Con tecniche simili (**Content ID**), **YouTube** controlla la similarità dei **fotogrammi** dei video per verificare se si sono caricati fotogrammi **protetti da copyright**.
- Il metodo esatto **non è divulgato**, per non aiutare le **contromisure**.
- Nel **video fingerprinting** tecniche simili a **pHash e SIFT** vengono applicate a **suoni** e **gruppi di fotogrammi** per creare un **identificatore unico**.

![](imgs/Pasted%20image%2020261123131640.png)

#### **5.6. Embedding e ricerca dei vicini**

L’**iscrizione** (*enrollment*) inserisce gli elementi nell’indice: lo stesso encoder trasforma ogni immagine o testo in un vettore $z\in\mathbb{R}^d$, che viene memorizzato nel database. La rete è addestrata prima e non memorizza il database. In fase di ricerca si codifica la query con lo stesso encoder e si recuperano i $k$ vettori più vicini.

![](imgs/Pasted%20image%2020261123132325.png)

![](imgs/Pasted%20image%2020261123132340.png)

Con $N$ vettori di dimensione $d$, la scansione esaustiva costa $O(Nd)$ per query: per $N=10^8$ e $d=512$ comporta circa $5{,}1\cdot10^{10}$ operazioni. Gli indici ANN (*Approximate Nearest Neighbour*) esaminano solo una parte dei vettori. HNSW (*Hierarchical Navigable Small World*, piccolo mondo gerarchico navigabile) costruisce un grafo multilivello; FAISS (*Facebook AI Similarity Search*, ricerca della similarità con vettori sviluppata da Facebook AI Research) è una libreria per la ricerca vettoriale esatta e approssimata. La velocità si ottiene accettando che talvolta il vero vicino non venga recuperato. La metrica pratica è `recall@k`, frazione dei veri $k$ vicini che l’indice trova.

![](imgs/Pasted%20image%2020261123132350.png)

La stessa pipeline supporta il recupero di immagini, la ricerca di duplicati e volti, i database vettoriali e il RAG. L’encoder e i suoi vettori devono restare coerenti: per cercare nello stesso spazio, sia l’archivio sia la query passano per lo stesso modello.

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
| **SIFT** / **SURF** | descrittori locali con procedure diverse; il confronto di velocità e accuratezza dipende da implementazione e dati |
| **Keypoint** / **istogrammi** / **hash** | robusti a scala e rotazione ma costosi / velocissimi ma ignorano le forme / impronte compatte confrontate con Hamming |
| **Hash di contenuto** / **hash percettivo** / **embedding** | identità dei byte / quasi duplicato visivo / similarità semantica |
| **Embedding globale** / **feature locali** | somiglianza complessiva / corrispondenza di punti fisici e geometria |
| **aHash** / **pHash** / **dHash** | soglia sulla media dei grigi / soglia sulle basse frequenze DCT / confronto tra pixel adiacenti |

---

### **7. Sintesi della lezione**


> ✅ **Punti principali**.
> - **Errori di etichettatura**: supervisori che sbagliano, diagnosi che **cambiano nel tempo**, etichette prodotte da **conversioni automatiche**.
> - **Provenienza e incertezza delle etichette**: registrare annotatore o regola, data e versione; usare l’accordo fra esperti e la κ di Cohen per quantificare il disaccordo.
> - **Controlli**: duplicati con etichette uguali o opposte, costo combinatorio dei confronti, procedura di verifica con seconda lettura, **hash MD5/SHA-1** per l’identità esatta.
> - **Similarità e distanza**: la base del **pattern recognition**; la metrica e la soglia dipendono dall’**applicazione** (identificare il singolo o riconoscere la categoria).
> - **Metriche nei dataset**: euclidea, Manhattan, coseno, Chebyshev, Minkowski.
> - **Immagini e ricerca**: identità binaria con hash di contenuto, quasi duplicati con hash percettivi, corrispondenze fisiche con SIFT/SURF, similarità semantica con embedding e ricerca top-$k$ approssimata.
> - La rappresentazione, la metrica e il compito stabiliscono quale significato dare alla parola «simile».

![](imgs/Pasted%20image%2020261123133322.png)