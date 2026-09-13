## **Lezione 18: Riduzione della dimensionalità, selezione ed estrazione delle feature**

La diciottesima lezione del corso **IS4** è la seconda parte della **feature engineering** e tratta la **riduzione della dimensionalità**. Le strade sono due. La **selezione delle feature** sceglie un sottoinsieme delle feature originali, con metodi **filtro**, **wrapper** o **embedded** e con strategie di ricerca in avanti e all’indietro. L’**estrazione delle feature** ne costruisce di nuove combinando quelle esistenti: la **PCA**, il metodo non supervisionato più importante, l’**LDA**, che usa le etichette, la **t-SNE** non lineare per la visualizzazione e l’**ICA** per separare sorgenti indipendenti. Il laboratorio in Colab applica PCA, t-SNE ed eliminazione ricorsiva delle feature al dataset di **cifre scritte a mano** di scikit-learn.

Fonti: [PDF della Lezione 18](Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf), 50 pagine, e il notebook [Lesson_18_Laboratory_COLAB_DimensionalityReduction.ipynb](Lesson_18_Laboratory_COLAB_DimensionalityReduction.ipynb), letto integralmente (le immagini dei grafici incorporate nel notebook non sono state visualizzate). Le formule che nelle slide sono immagini sono riportate in forma standard; le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–5)_

Titolo originale: *Feature engineering Part 2: Dimensionality reduction. Feature Selection and Extraction*. L’indice (p. 2) elenca:

1. **selezione delle feature**: metodi **filtro**, **wrapper**, **embedded**;
2. **estrazione delle feature**: **PCA**, **analisi discriminante lineare**, **t-SNE**, **analisi delle componenti indipendenti**;
3. **codice** per la feature engineering;
4. punti principali.

> 📌 **Estrazione e selezione** (p. 3).
> - **Estrazione**: ricavare **feature utili** dai dati esistenti, creando nuove feature (PCA; le CNN nelle lezioni successive).
> - **Selezione**: scegliere un **sottoinsieme** dell’insieme originale di feature (per esempio con i wrapper).

**La pipeline della feature engineering** (p. 4):

$$\text{omogeneizzazione} \to \text{valori non validi} \to \text{valori mancanti} \to \text{coerenza} \to \text{codifica categorica} \to \text{rimozione outlier} \to$$
$$\to \text{scomposizione} \to \text{aggregazione} \to \text{trasformazione} \to \text{normalizzazione} \to \text{riduzione della dimensionalità} \to \text{classificatore / regressore}$$

In un’applicazione tipica (p. 5) i metodi di feature engineering trasformano l’input $\mathbf{x}$ in $\mathbf{x}_{\text{engineered}}$, che poi entra nel classificatore o nel regressore.

> 💡 **La pipeline riassume il corso (nota didattica).** I primi passi sono quelli delle Lezioni 06 e 16 (armonizzazione, dati mancanti, codifica delle categorie), la normalizzazione è quella della Lezione 10, §5. La riduzione della dimensionalità viene **per ultima**: selezionare o combinare feature ha senso solo dopo che i dati sono puliti e confrontabili.

<!-- SOURCE_VISUAL id="IS18-V01" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="3" type="grafico" description="Estrazione contro selezione delle feature (quantdare.com)" reason="Schema grafico dei due approcci" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V02" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="5" type="diagramma" description="Metodi di feature engineering tra input x e classificatore o regressore" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **1. Selezione delle feature**

_(slide pp. 6–15)_

#### **1.1. Perché selezionare**

La domanda (p. 6): **quanto è importante ciascuna feature?**

> 📌 **L’idea generale** (p. 7). Feature **irrilevanti** o solo parzialmente rilevanti possono **peggiorare** le prestazioni del modello. Selezionarle:
> - **riduce l’overfitting** dovuto al rumore;
> - **può migliorare l’accuratezza**;
> - **riduce il tempo di addestramento**, perché la matrice $X$ è più piccola.

> 💡 **Il legame con i gradi di libertà (nota didattica).** Ogni feature in più aggiunge parametri al modello: in una rete, un peso per ogni neurone del primo strato. Togliere feature inutili abbassa i gradi di libertà a parità di dati (Lezione 07, §2), e quindi il rischio di overfitting.

#### **1.2. Filtri, wrapper, metodi embedded**

_(slide pp. 8–12)_

| | **Filtri** (p. 9) | **Wrapper** (p. 10) | **Metodi embedded** (p. 11) |
|---|---|---|---|
| **Criterio** | misurare la **rilevanza** di una feature o di un sottoinsieme | misurare l’**utilità** di un sottoinsieme | misurare l’**utilità** di un sottoinsieme |
| **Ricerca** | di solito **ordinare** le feature (classifica delle singole feature o sottoinsiemi annidati) | esplorare lo **spazio di tutti i sottoinsiemi** | ricerca **guidata dal processo di apprendimento** |
| **Valutazione** | **test statistici** | **modello + cross-validation** | **cross-validation** |
| **Risultati** | relativamente **robusti all’overfitting**; possono **non scegliere** le feature più «utili» | in principio trovano le feature **più utili**, ma sono **soggetti a overfitting** | **simili ai wrapper**, ma **meno costosi** e **meno soggetti a overfitting** |
| **Esempi** | preferire feature con **alta varianza**; preferire feature **poco correlate** (similarità → cross-correlazione): tra $N$ feature molto correlate, **tenerne una** | modelli tipici: **k-NN** o **reti neurali** | **specifici** di una certa macchina di apprendimento: la selezione avviene **implicitamente durante l’addestramento** |

<!-- SOURCE_VISUAL id="IS18-V03" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="8" type="schema" description="Metodi filtro, wrapper ed embedded a confronto (quantdare.com)" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Perché i wrapper vanno in overfitting (nota didattica).** Un wrapper prova moltissimi sottoinsiemi e tiene quello con il miglior risultato in cross-validation. Con tanti tentativi, qualche sottoinsieme «vince» per caso sui dati disponibili: è la stessa trappola del provare dieci architetture e scegliere la migliore sul test (Lezione 14, §4.3). Per una stima onesta la selezione va fatta **dentro** ogni fold, e il risultato finale verificato su dati mai usati.

**Esempio di metodo embedded con le reti neurali** (p. 12). Con input **normalizzati** (stesso intervallo), se un **peso dello strato di ingresso** tende a zero, l’algoritmo di addestramento ha considerato quella feature **poco rilevante** per l’uscita: se $W_4 \to 0$, o se tutti i pesi $W_{5i}$ che partono dalla feature 5 tendono a zero, quella feature «**non sta lavorando**».

> ⚠️ **La normalizzazione è indispensabile.** Se una feature è in millimetri e un’altra in chilometri, i pesi hanno scale diverse per compensare le unità: un peso piccolo può moltiplicare valori enormi. Solo con input sulla stessa scala il modulo del peso dice qualcosa sull’importanza (Lezione 10, §5).

<!-- SOURCE_VISUAL id="IS18-V04" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="12" type="diagramma" description="Rete neurale in cui pesi di ingresso tendenti a zero indicano feature poco rilevanti" reason="Pesi della rete visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3. Selezione in avanti e all’indietro**

_(slide pp. 13–15)_

**Selezione in avanti con un wrapper** (p. 13), detta anche **SFS** (*Sequential Forward Selection*). Si rappresenta ogni configurazione come una stringa di bit (0 = feature non attiva, 1 = attiva):

1. si prova ogni feature **da sola** e si sceglie la **più saliente**;
2. si aggiunge a quella, una alla volta, ciascuna delle feature rimaste, e si sceglie il **sottogruppo più saliente**;
3. si continua finché si aggiungono feature.

Con 4 feature si provano $4 + 3 + 2 + 1 = 10$ configurazioni. Il numero di tutte le configurazioni con $m$ feature attive su $n$ è il coefficiente binomiale

$$C(n, m) = \binom{n}{m} = \frac{n!}{m!\,(n - m)!}$$

(nella slide scritto «(n!)/k!(n-k!)», con variabili e parentesi non coerenti).

> 💡 **SFS contro ricerca esaustiva (nota didattica).** Provare **tutti** i sottoinsiemi non vuoti di $n$ feature richiede
> $$\sum_{m=1}^{n} \binom{n}{m} = 2^n - 1$$
> configurazioni: 15 con 4 feature, ma oltre un milione con 20 feature e circa $10^{19}$ con 64 (i pixel del laboratorio). La SFS ne prova solo $n + (n-1) + \dots + 1 = n(n+1)/2$: 210 con 20 feature. Il prezzo è che è **greedy**: una feature scelta all’inizio non viene più tolta, e due feature utili solo **insieme** possono non essere mai scelte.

**Selezione in avanti con un metodo embedded** (p. 14). La ricerca è **guidata**: **non si considerano percorsi alternativi**, e con 4 feature si provano solo **4 configurazioni**, tra cui si sceglie la migliore.

> 💡 **Da dove vengono le 4 configurazioni (nota didattica).** Se il modello fornisce già un **ordine di importanza** delle feature (per esempio dai pesi, p. 12), basta aggiungerle in quell’ordine: {1}, {1, 2}, {1, 2, 3}, {1, 2, 3, 4}. Si valuta ogni configurazione una sola volta, invece di confrontare a ogni passo tutte le feature rimaste.

**Eliminazione all’indietro con un wrapper** (p. 15), detta anche **SBS** (*Sequential Backward Selection*): si parte da **tutte** le feature e si **toglie**, a ogni passo, quella la cui assenza peggiora meno il modello.

<!-- SOURCE_VISUAL id="IS18-V05" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="13" type="diagramma" description="Selezione sequenziale in avanti con wrapper: configurazioni di bit provate a ogni passo" reason="Configurazioni visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V06" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="14" type="diagramma" description="Selezione in avanti guidata (embedded) con 4 configurazioni" reason="Configurazioni visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V07" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="15" type="diagramma" description="Eliminazione sequenziale all’indietro (SBS) con wrapper" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Estrazione delle feature: la PCA**

_(slide pp. 16–29)_

#### **2.1. L’idea**

> 📌 **Principal Component Analysis** (p. 16). La **più importante** tecnica di riduzione della dimensionalità **non supervisionata**, cioè che **non usa etichette**.

**Esempio** (p. 17): dati descritti da **3 feature** (Gene1, Gene2, Gene3) con 4 classi vengono rappresentati da **2 feature**, le **componenti principali** PC1 e PC2, conservando la separazione. **Si tratta di trovare un sottospazio opportunamente traslato e ruotato** che descriva i dati.

<!-- SOURCE_VISUAL id="IS18-V08" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="17" type="grafico" description="PCA da tre geni a due componenti principali con quattro classi" reason="Nuvole di punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Applicazioni della PCA** (p. 18): **visualizzazione** dei dati; **compressione** e riduzione della dimensionalità; **riduzione del rumore**; **classificazione**; analisi delle **tendenze**; **analisi fattoriale**; **estrazione delle feature**; **dentro le reti neurali** e i modelli di **deep learning**, per la **creazione automatica di feature**.

> 📌 **L’idea della PCA** (pp. 19–21).
> - È **NON SUPERVISIONATA**: non usa etichette.
> - Dati punti in uno spazio a $d$ dimensioni, li **proietta** in uno spazio a **meno dimensioni** conservando **quanta più informazione possibile**: per esempio la migliore approssimazione **planare** di dati 3D, o la migliore approssimazione a **12 dimensioni** di dati a 10 000.
> - In particolare sceglie la proiezione che **minimizza l’errore quadratico** nella **ricostruzione** dei dati originali.
> - È la **proiezione ortogonale** su uno spazio lineare di dimensione più bassa che **massimizza la varianza** dei dati proiettati e, equivalentemente, **minimizza la distanza quadratica media** tra i punti e le loro proiezioni.
> - I vettori partono dal **centro di massa** dei dati. La **prima componente principale** (PC1) punta nella direzione di **massima varianza**; ogni componente successiva è **ortogonale** alle precedenti e punta nella direzione di massima varianza del **sottospazio residuo**.

> 💡 **Perché massimizzare la varianza equivale a minimizzare l’errore (nota didattica).** Per ogni punto centrato $\mathbf{x}$ e direzione unitaria $\mathbf{u}$, il teorema di Pitagora dà
> $$\lVert \mathbf{x} \rVert^2 = (\mathbf{u}^\top \mathbf{x})^2 + \lVert \mathbf{x} - (\mathbf{u}^\top \mathbf{x})\,\mathbf{u} \rVert^2$$
> La lunghezza del punto è fissa: aumentare la parte proiettata (la varianza lungo $\mathbf{u}$) fa diminuire la parte perpendicolare (l’errore di ricostruzione). I due criteri della slide sono lo stesso problema visto da due lati.

<!-- SOURCE_VISUAL id="IS18-V09" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="20" type="grafico" description="Proiezione su u1 che massimizza la varianza e minimizza le distanze dei punti" reason="Proiezioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V10" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="21" type="grafico" description="Componenti principali PC1 e PC2 ortogonali dal centro di massa" reason="Direzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. Visualizzare dati non supervisionati**

_(slide pp. 22–27)_

La PCA è un metodo **semplice ma efficace** per trovare le $k$ componenti più salienti di dati non supervisionati (p. 22).

**Visualizzare dati ad alta dimensionalità** (p. 23):

- l’apprendimento **non supervisionato** lavora tipicamente in spazi di feature **ad alta dimensionalità**;
- i dataset reali, per esempio **NSL-KDD**, contengono **decine** di feature numeriche e categoriche;
- la visualizzazione umana è limitata a 1D, 2D e 3D (con efficacia limitata);
- **distanze**, **similarità** e **struttura dei cluster** sono definite nello spazio **originale**, che **non si può visualizzare direttamente**.

> 📌 **Che cosa fa e che cosa non fa la PCA** (pp. 24–25).
> - È una tecnica di riduzione **lineare** e **non supervisionata**: **non usa etichette** né informazioni sulle classi.
> - **Proietta** i dati su un nuovo insieme di **assi ortogonali**; ogni asse (componente principale) è una **combinazione lineare** delle feature originali e cattura la **massima varianza possibile**.
> - Le prime due componenti (PC1, PC2) conservano la **frazione più grande** della varianza e permettono una **visualizzazione in 2D**.
> - Si sceglie la **dimensione $K$** dell’uscita, con $K < M$ (dettaglio non d’esame).
>
> **NOTA!** La PCA **non fa clustering**, **non usa le etichette**, e **dopo** un clustering si applica **solo** per visualizzarlo.

**Esempi** (pp. 26–27):

| Caso | Che cosa mostra |
|---|---|
| da **3 a 2 dimensioni** (p. 26) | nello spazio originale struttura e separabilità sono difficili da interpretare; la PCA proietta sulle **due combinazioni lineari più informative** delle feature, **senza usare le etichette** |
| da **10 a 2 dimensioni** (p. 27) | in 10 dimensioni si possono usare solo **tabelle** o **sotto-proiezioni**; i dati contengono **4 cluster rumorosi sovrapposti**, e dopo la PCA le **etichette servono solo per colorare** il grafico |

> 💡 **NSL-KDD (nota didattica).** È un dataset classico di **rilevamento delle intrusioni** di rete: ogni connessione è descritta da 41 feature (durata, protocollo, servizio, byte scambiati, numero di errori, …) e marcata come normale o come un tipo di attacco. È un esempio tipico di dati in cui la PCA serve a **guardare** una struttura che a occhio nudo è invisibile.

<!-- SOURCE_VISUAL id="IS18-V11" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="26" type="grafico" description="PCA da 3 a 2 dimensioni senza etichette" reason="Grafici prima e dopo visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V12" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="27" type="grafico" description="PCA da 10 a 2 dimensioni con quattro cluster rumorosi sovrapposti" reason="Proiezione visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. L’algoritmo**

_(slide pp. 28–29)_

> 📌 **PCA con la matrice di covarianza campionaria** (p. 28, formule in forma standard). Dati $\{\mathbf{x}_1, \dots, \mathbf{x}_m\}$:
> $$\bar{\mathbf{x}} = \frac{1}{m} \sum_{i=1}^{m} \mathbf{x}_i \qquad \Sigma = \frac{1}{m} \sum_{i=1}^{m} (\mathbf{x}_i - \bar{\mathbf{x}})(\mathbf{x}_i - \bar{\mathbf{x}})^{\top}$$
> - i **vettori della base PCA** sono gli **autovettori** di $\Sigma$;
> - un **autovalore più grande** indica un autovettore **più importante**;
> - in pseudocodice: `x_reduced = PCA(x, k)`, per esempio con $k = 3$.

**I passi** (p. 29), con $X$ matrice $N \times m$ in cui ogni punto $\mathbf{x}_i$ è un **vettore colonna**:

1. **sottrarre la media** $\bar{\mathbf{x}}$ da ogni vettore colonna di $X$;
2. calcolare la **matrice di covarianza** $\Sigma \propto X X^{\top}$;
3. calcolare **autovalori e autovettori** $\{\lambda_i, \mathbf{u}_i\}$ di $\Sigma$, ordinati con $\lambda_1 \ge \lambda_2 \ge \dots \ge \lambda_N$;
4. restituire le prime $k$ coppie $\{\lambda_i, \mathbf{u}_i\}_{i=1..k}$: le **$k$ componenti principali**.

> 💡 **Proiezione e varianza spiegata (nota didattica).** Con $U_k = [\mathbf{u}_1, \dots, \mathbf{u}_k]$, un punto si riduce e si ricostruisce così:
> $$\mathbf{z} = U_k^{\top} (\mathbf{x} - \bar{\mathbf{x}}) \in \mathbb{R}^k \qquad \hat{\mathbf{x}} = \bar{\mathbf{x}} + U_k\, \mathbf{z}$$
> L’autovalore $\lambda_i$ è la varianza dei dati lungo $\mathbf{u}_i$, quindi la frazione di varianza conservata dalle prime $k$ componenti è
> $$\frac{\lambda_1 + \dots + \lambda_k}{\lambda_1 + \dots + \lambda_N}$$
> Esempio: con autovalori $(4;\ 1;\ 0{,}5;\ 0{,}5)$ le prime due componenti conservano $5/6 \approx 83\%$ della varianza. Si sceglie spesso $k$ come il più piccolo valore che supera una soglia, per esempio il 90% o il 95%.

> ⚠️ **Scale diverse (nota didattica).** La PCA cerca la varianza massima: una feature in euro con varianza $10^6$ dominerà una feature in metri con varianza 1, anche se è meno informativa. Prima della PCA le feature vanno di solito **standardizzate** con lo z-score, calcolando media e deviazione standard **sul solo training set** (Lezione 14, §4.3).

---

### **3. Altre tecniche di estrazione: LDA, t-SNE, ICA**

_(slide pp. 30–34)_

#### **3.1. Analisi discriminante lineare**

> 📌 **LDA** (p. 31). Trova una **combinazione lineare** delle feature che **caratterizza o separa le classi**. La combinazione ottenuta si può usare come **classificatore lineare** oppure per **ridurre la dimensionalità** prima di un classificatore. La slide confronta una **rotazione sbagliata**, in cui le classi proiettate si **sovrappongono**, con la **rotazione migliore**, in cui sono separate.

La slide rimanda alla «Lezione 14» per il codice del classificatore LDA di Fisher; in questa edizione del corso il codice è presentato nelle Lezioni 16 (§7.3) e 17 (§4.3), con il criterio di Fisher.

> ⚠️ **PCA e LDA non sono intercambiabili (nota didattica).** La PCA cerca la direzione di **massima varianza** senza guardare le classi; la LDA quella di **massima separazione** tra le classi. Se le classi differiscono lungo una direzione di varianza piccola, la PCA può scartarla proprio perché «poco varia», mentre la LDA la sceglie. In compenso la LDA richiede le etichette, e con $C$ classi produce al massimo $C - 1$ dimensioni.

<!-- SOURCE_VISUAL id="IS18-V13" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="31" type="grafico" description="LDA: rotazione sbagliata con classi sovrapposte contro rotazione migliore" reason="Proiezioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. t-SNE**

> 📌 **t-Distributed Stochastic Neighbor Embedding** (p. 32). Una tecnica **non lineare** di riduzione della dimensionalità, particolarmente adatta alla **visualizzazione** e all’elaborazione di dataset **ad alta dimensionalità**. Qui il metodo di embedding si usa per l’**estrazione** delle feature, non per la selezione. In pseudocodice: `x_reduced = tSNE(x, k)` con $k$ **al massimo 2 o 3**.

**Esempio su MNIST** (p. 32): **60 000** immagini di training, $28 \times 28 = 784$ feature, 10 classi (le cifre da 0 a 9). Rispetto alla PCA, la t-SNE mostra una **separazione migliore** delle classi.

> ⚠️ **Come leggere un grafico t-SNE (nota didattica).**
> - La t-SNE conserva soprattutto i **vicinati locali**: punti vicini nello spazio originale restano vicini, ma le **distanze tra cluster** e le **dimensioni** dei cluster nel grafico **non hanno un significato** affidabile.
> - Il risultato dipende da parametri come la **perplessità** e dal **seme casuale**: esecuzioni diverse danno disegni diversi.
> - Non fornisce una funzione per proiettare **nuovi punti**, a differenza della PCA: serve per **esplorare**, raramente come passo di una pipeline di produzione.

<!-- SOURCE_VISUAL id="IS18-V14" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="32" type="grafico" description="PCA contro t-SNE sul dataset MNIST: separazione migliore delle classi" reason="Nuvole colorate visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. Analisi delle componenti indipendenti**

> 📌 **ICA** (*Independent Component Analysis*, pp. 33–34). Usata spesso nell’**elaborazione dei segnali** e nelle **applicazioni industriali**. Esempi della slide:
> - **3 persone che parlano** registrate da **4 microfoni**;
> - **2 persone + rumore** forte registrate da **3 microfoni**: dai 3 segnali, provenienti da 2 sorgenti indipendenti più rumore, l’ICA ricava 3 segnali trasformati, e **il terzo si può scartare**.

> 💡 **Il problema del «cocktail party» (nota didattica).** Ogni microfono registra una **miscela lineare** delle sorgenti:
> $$\mathbf{x} = A\,\mathbf{s}$$
> dove $\mathbf{s}$ sono le sorgenti (le voci), $A$ la matrice di miscelazione (che dipende dalle posizioni) e $\mathbf{x}$ i segnali registrati. L’ICA stima una matrice $W \approx A^{-1}$ tale che le componenti di $\mathbf{y} = W\mathbf{x}$ siano **statisticamente indipendenti**. La PCA cercherebbe invece componenti **scorrelate** e di varianza massima, che in generale **non** coincidono con le voci separate. In ambito industriale lo stesso schema separa, per esempio, le vibrazioni di due macchine vicine misurate da più accelerometri.

<!-- SOURCE_VISUAL id="IS18-V15" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="33" type="schema" description="ICA: tre persone e quattro microfoni, due persone più rumore e tre microfoni" reason="Schema delle sorgenti e dei microfoni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V16" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="34" type="grafico" description="Tre segnali da due sorgenti indipendenti più rumore, con il terzo segnale trasformato da scartare" reason="Forme d’onda visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Laboratorio: riduzione della dimensionalità in Colab**

_(slide pp. 35–49)_

#### **4.1. Il dataset delle cifre**

Il laboratorio (p. 35) usa **codice Python in Colab** per estrazione e selezione delle feature, con il dataset **`sklearn.datasets.load_digits`** (p. 36): ogni esempio è un’**immagine 8 × 8** di una cifra scritta a mano, con **1797 campioni**, molti meno di MNIST.

```python
import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import load_digits

digits = load_digits()
print(digits.data.shape)       # (1797, 64)
print(digits.data[0].shape)    # (64,)
print(digits.data[0])          # 64 livelli di grigio tra 0 e 16
print(digits.target[0])        # 0

plt.gray()
plt.matshow(digits.images[0])
plt.show()
```

`shape` è una **tupla** che indica il numero di dimensioni dell’array (p. 37): 1797 campioni con **64 feature**, i pixel dell’immagine 8 × 8 **srotolati** in un vettore. Il primo campione è uno **0**.

Si disegnano poi **due sole feature** per vedere la loro correlazione:

```python
plt.scatter(digits.data[:, 1], digits.data[:, 2],
            c=digits.target, edgecolor='none', alpha=0.5,
            cmap=plt.cm.get_cmap('jet', 10))
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.colorbar();
```

> 📌 **L’effetto della quantizzazione** (p. 39). I pixel hanno solo **17 livelli di grigio** (da 0 a 16): nello scatter plot di due feature i punti cadono su una **griglia** e si **sovrappongono**, e non si vedono **cluster**.

<!-- SOURCE_VISUAL id="IS18-V17" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="38" type="schermata" description="Caricamento del dataset delle cifre e visualizzazione di un’immagine 8x8" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V18" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="39" type="grafico" description="Scatter di due pixel: effetto della quantizzazione a 17 livelli e nessun cluster" reason="Griglia di punti visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. PCA e t-SNE**

_(slide pp. 40–44)_

**Riduzione con la PCA** (pp. 37, 40–41), estrazione delle feature da 64 a 2 dimensioni:

```python
from sklearn.decomposition import PCA

pca = PCA(2)                                  # project from 64 to 2 dimensions
projected = pca.fit_transform(digits.data)
print(digits.data.shape)                      # (1797, 64)
print(projected.shape)                        # (1797, 2)

# PCA is unsupervised --> no labels are used! (le etichette servono solo per il colore)
plt.scatter(projected[:, 0], projected[:, 1],
            c=digits.target, edgecolor='none', alpha=0.5,
            cmap=plt.cm.get_cmap('jet', 10))
plt.xlabel('component 1')
plt.ylabel('component 2')
plt.colorbar();
```

**Riduzione con la t-SNE** (pp. 42–44):

```python
from sklearn.manifold import TSNE

tsne = TSNE(n_components=2, verbose=1, perplexity=40, n_iter=300)
tsne_results = tsne.fit_transform(digits.data)   # no y labels

plt.scatter(tsne_results[:,0], tsne_results[:,1],
            c=digits.target, edgecolor='none', alpha=0.5,
            cmap=plt.cm.get_cmap('jet', 10))
plt.xlabel('tsne-2d-one')
plt.ylabel('tsne-2d-one')
plt.colorbar();
```

L’output salvato riporta, tra l’altro, i **121 vicini più prossimi** calcolati per i 1797 campioni, e una **divergenza KL** di 61,49 dopo 250 iterazioni con *early exaggeration* e di **0,96** dopo 300 iterazioni.

| Metodo | Risultato (slide) |
|---|---|
| **PCA** (pp. 40–41) | le 10 cifre formano gruppi **parzialmente sovrapposti** |
| **t-SNE** (pp. 43–44) | «per 1797 campioni **non è male**»; **buon risultato**: le cifre formano gruppi ben distinti, con lo stesso tipo di grafico della PCA |

> 💡 **Perché la t-SNE separa meglio (nota didattica).** La PCA può solo **ruotare e proiettare** linearmente: se le cifre stanno su superfici curve nello spazio a 64 dimensioni, una proiezione piana le sovrappone. La t-SNE costruisce invece una mappa **non lineare** che cerca di mantenere vicini i punti che erano vicini, e minimizza la divergenza KL tra le distribuzioni dei vicinati nei due spazi: è il valore 0,96 stampato dal notebook.

> ⚠️ **API cambiate (nota didattica).** Nelle versioni recenti di scikit-learn il parametro `n_iter` di `TSNE` si chiama `max_iter`, e in matplotlib `plt.cm.get_cmap` è stato sostituito da `plt.get_cmap` o `matplotlib.colormaps`. Nel grafico t-SNE entrambi gli assi sono etichettati «tsne-2d-one»: il secondo dovrebbe essere «tsne-2d-two».

<!-- SOURCE_VISUAL id="IS18-V19" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="40" type="grafico" description="Proiezione PCA delle cifre sulle prime due componenti" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V20" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="41" type="grafico" description="Proiezione PCA delle cifre colorata per classe" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V21" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="43" type="grafico" description="Proiezione t-SNE delle 1797 cifre" reason="Nuvole di punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V22" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="44" type="grafico" description="Confronto PCA e t-SNE sulle cifre" reason="Grafici a confronto visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3. Selezione con l’eliminazione ricorsiva delle feature**

_(slide pp. 45–49)_

**Selezione delle feature con la Recursive Feature Elimination** (RFE, p. 45), con un **wrapper SVM** (si può provare anche con k-NN, LDA, reti neurali, …). La domanda: **quale pixel è più significativo per riconoscere la classe?**

```python
from sklearn.svm import SVC
from sklearn.feature_selection import RFE

X = digits.images.reshape((len(digits.images), -1))   # immagini srotolate in 64 valori (0-16)
y = digits.target

# Create the RFE object and rank each pixel
svc = SVC(kernel="linear", C=1)
rfe = RFE(estimator=svc, n_features_to_select=1, step=1)
rfe.fit(X, y)
ranking = rfe.ranking_.reshape(digits.images[0].shape)   # di nuovo come immagine 8x8

print("ranking of the pixel w.r.t the classification capability")
print(ranking)

plt.matshow(ranking, cmap=plt.cm.Blues)
plt.colorbar()
plt.title("Ranking of pixels with RFE")
plt.show()
```

Le pp. 46–47 mostrano l’immagine **srotolata** di una cifra (livelli di grigio 0–16), la **classifica** delle feature con RFE, la definizione del **classificatore wrapper** (SVM) e il **reshape** dei risultati nella forma delle immagini delle cifre.

La classifica salvata nel notebook (1 = pixel più importante, 64 = meno importante):

$$\text{ranking} = \begin{bmatrix}
64 & 50 & 31 & 23 & 10 & 17 & 34 & 51 \\
57 & 37 & 30 & 43 & 14 & 32 & 44 & 52 \\
54 & 41 & 19 & 15 & 28 & 8 & 39 & 53 \\
55 & 45 & 9 & 18 & 20 & 38 & 1 & 59 \\
63 & 42 & 25 & 35 & 29 & 16 & 2 & 62 \\
61 & 40 & 5 & 11 & 13 & 6 & 4 & 58 \\
56 & 47 & 26 & 36 & 24 & 3 & 22 & 48 \\
60 & 49 & 7 & 27 & 33 & 21 & 12 & 46
\end{bmatrix}$$

> 📌 **Pixel più e meno importanti** (pp. 48–49). Poiché il wrapper è una SVM che **classifica la cifra**, la classifica dice quali pixel servono di più a **distinguere le cifre**: il pixel con **rank = 1** è il più importante, i pixel con rank alto sono i meno importanti.

> 💡 **Leggere la matrice (nota didattica).** I pixel più importanti (1, 2, 3, 4) stanno nella parte **centrale-destra** dell’immagine, dove le diverse cifre differiscono di più. I meno importanti (64, 63, 62, 61, …) stanno sulle **colonne esterne**, soprattutto gli **angoli**: in quasi tutte le cifre quei pixel sono sempre 0, quindi non portano informazione. Con `n_features_to_select=1` e `step=1` la RFE addestra la SVM **63 volte**, eliminando a ogni giro il pixel con il peso minore: è una **eliminazione all’indietro** (§1.3) guidata dai pesi del modello.

> ⚠️ **Tutto il dataset.** Come negli esempi di laboratorio precedenti, la RFE è eseguita su **tutti** i 1797 campioni: per usare la classifica in un modello da valutare, la selezione andrebbe ripetuta **dentro** ogni fold di cross-validation, altrimenti la stima finale è ottimistica.

<!-- SOURCE_VISUAL id="IS18-V23" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="46" type="schermata" description="Immagine srotolata di una cifra e classifica delle feature con RFE" reason="Schermata del codice e dell’output" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V24" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="48" type="immagine" description="Mappa della classifica dei pixel: più importanti e meno importanti per la classificazione" reason="Mappa di colore visibile solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-V25" source="Lesson_18_FeatureEngineeringDimensionalityReductionFeatureSelectionFeatureExtractionCodingExamples.pdf" page="49" type="immagine" description="Pixel con rank 1 e pixel meno importanti nell’immagine 8x8" reason="Mappa di colore visibile solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Selezione** / **estrazione** | sottoinsieme delle feature originali, interpretabili / feature nuove come combinazioni delle originali |
| **Filtro** / **wrapper** / **embedded** | rilevanza con test statistici, robusto ma può mancare le feature utili / utilità con modello e CV, potente ma in overfitting / selezione durante l’addestramento, meno costosa |
| **SFS** / **SBS** | parte da nessuna feature e aggiunge / parte da tutte e toglie |
| **SFS** / **ricerca esaustiva** | $n(n+1)/2$ configurazioni, greedy / $2^n - 1$ configurazioni |
| **Wrapper in avanti** / **embedded in avanti** | confronta a ogni passo tutte le feature rimaste / segue l’ordine dato dal modello |
| **Massima varianza** / **minimo errore di ricostruzione** | due formulazioni equivalenti della PCA |
| **Autovettore** / **autovalore** | direzione della componente / varianza lungo quella direzione |
| **PCA** / **LDA** | non supervisionata, massima varianza / supervisionata, massima separazione, al più $C - 1$ dimensioni |
| **PCA** / **t-SNE** | lineare, proietta nuovi punti, distanze globali leggibili / non lineare, vicinati locali, solo esplorazione in 2-3D |
| **PCA** / **ICA** | componenti scorrelate a varianza massima / componenti statisticamente indipendenti (separazione delle sorgenti) |
| **PCA** / **clustering** | proietta i dati / raggruppa i dati; la PCA si usa dopo per visualizzare |
| **Rank 1** / **rank 64** in RFE | pixel più utile per distinguere le cifre / pixel quasi sempre nullo, ai bordi |

---

### **6. Sintesi della lezione**

_(slide p. 50)_

> ✅ **Punti principali** (p. 50).
> - **Feature engineering, parte 2: riduzione della dimensionalità**, ultimo passo della pipeline prima del modello.
> - **Selezione delle feature**: metodi **filtro** (test statistici, varianza, correlazione), **wrapper** (modello più cross-validation, rischio di overfitting), **embedded** (selezione durante l’addestramento, pesi che tendono a zero); ricerca in avanti (SFS) e all’indietro (SBS).
> - **Estrazione delle feature**:
>   - **PCA**: non supervisionata, lineare, autovettori della matrice di covarianza, varianza spiegata dagli autovalori;
>   - **LDA**: supervisionata, massima separazione delle classi;
>   - **t-SNE**: non lineare, per visualizzare in 2-3 dimensioni;
>   - **ICA**: separazione di sorgenti indipendenti.
> - **Codice** della riduzione della dimensionalità in Python-Colab: PCA e t-SNE sulle cifre scritte a mano, classifica dei pixel con RFE e SVM.
