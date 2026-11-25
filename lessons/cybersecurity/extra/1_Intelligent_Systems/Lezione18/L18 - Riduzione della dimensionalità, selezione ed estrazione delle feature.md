## **Lezione 18: Feature engineering, parte 2 — riduzione della dimensionalità: selezione ed estrazione delle feature, embedding e modelli «System 1»**

Dopo la pulizia dei dati (Lezioni 6, 10 e 16) resta una domanda: **quali feature contano davvero, e quante se ne possono tenere?** La lezione risponde in due modi. La **selezione** conserva un sottoinsieme delle feature originali (filtri, wrapper, metodi embedded, ricerche greedy SFS e SBS, RFE in laboratorio). L’**estrazione** costruisce poche feature nuove a partire da molte: prima la **PCA**, poi **LDA**, **t-SNE**, **ICA** e cinque metodi per visualizzare dati ad alta dimensionalità, fino agli **embedding** appresi da un encoder pre-addestrato. L’ultima parte teorica collega embedding e classificatori veloci al «**System 1**» di Kahneman: i modelli decisionali tipizzati (Choice, Score, Noul), la **calibrazione** delle probabilità e la **cascata** in cui il sistema veloce propone e quello lento valida.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L18-web.pdf` (93 pagine). I segnaposto per le figure indicano la pagina del PDF. Il notebook della cartella (`Lesson_18_Laboratory_COLAB_DimensionalityReduction.ipynb`) e il PDF dell’edizione precedente sono commentati nel §11. Nel PDF alcuni numeri sono marcati «REVIEW»: provengono dalle cifre sintetiche generate dall’autore e vanno letti come valori indicativi.


### **0. Panoramica**


- **la domanda**: dopo aver pulito i dati, quali feature contano davvero, e quante se ne possono tenere?
- **selezionare un sottoinsieme delle feature originali**: filtri, wrapper, metodi embedded e le ricerche greedy SFS e SBS;
- **estrarre feature nuove**: prima la PCA, poi LDA, t-SNE, ICA, cinque modi di visualizzare;
- **embedding**, poi **System 1** (ultima parte teorica): feature apprese, modelli decisionali veloci, calibrazione, cascata;
- **laboratorio dopo ogni blocco teorico** (RFE, PCA, t-SNE, Iris/Wine/MNIST, embedding) in Python/Colab;
- punti principali.

![](imgs/Pasted%20image%2020261125010653.png)


<!-- SOURCE_VISUAL id="IS18-V01" source="L18-web.pdf" page="2" type="grafico" description="Mappe PCA e t-SNE delle cifre che accompagnano la panoramica" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **0.1. Estrazione e selezione delle feature**

- **Estrazione**: costruire **poche feature nuove combinando** quelle originali.
- **Selezione**: tenere un **sottoinsieme** delle feature originali e **scartare il resto**.

> 📌 **Significato fisico.** La selezione **conserva il significato fisico** di ogni feature (un sensore, un pixel); l’estrazione **no**: ogni feature nuova mescola più feature vecchie. **Prima viene la selezione, poi l’estrazione** (qui la PCA, in altre lezioni le CNN).

<!-- SOURCE_VISUAL id="IS18-V02" source="L18-web.pdf" page="3" type="schema" description="A sinistra estrazione di feature nuove, a destra selezione di un sottoinsieme con le feature scartate barrate (da quantdare.com)" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010913.png)

#### **0.2. La feature engineering: dove si colloca la riduzione**

La catena completa della feature engineering, già incontrata nelle lezioni precedenti, è:

omogeneizzazione → valori non validi → valori mancanti → coerenza → codifica delle variabili categoriche → rimozione degli outlier → scomposizione → aggregazione → trasformazione → normalizzazione → **riduzione della dimensionalità (selezione o estrazione delle feature)**.

<!-- SOURCE_VISUAL id="IS18-V03" source="L18-web.pdf" page="4" type="schema" description="La catena della feature engineering che termina con la riduzione della dimensionalità" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010924.png)

**Un’applicazione tipica.** Un classificatore o regressore deve riconoscere una **vibrazione pericolosa**: l’ingresso $x$ è fatto di **6 segnali di sensori rumorosi**; i metodi di feature engineering li riducono a **3 feature più pulite** ($x_{\text{engineered}}$: per esempio livello filtrato, RMS, picco); l’uscita è una classe (o un valore). I segnali della figura sono sintetici e illustrativi.

<!-- SOURCE_VISUAL id="IS18-V04" source="L18-web.pdf" page="5" type="figura" description="Sei canali grezzi rumorosi trasformati in tre feature ingegnerizzate e poi in una classe" reason="Segnali visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010933.png)

> 💡 **RMS (nota didattica).** Il valore efficace (*Root Mean Square*) di una finestra di $n$ campioni è $\text{RMS}=\sqrt{\frac1n\sum_i s_i^2}$: misura l’energia del segnale e cresce con l’ampiezza della vibrazione. È un esempio di feature del livello (A) della Lezione 10, calcolata dentro ogni frame.

#### **0.3. Le tecniche in un colpo d’occhio: estrarre o selezionare**

| **Estrazione delle feature** | **Selezione delle feature** |
|---|---|
| **PCA**$(X)$: non supervisionata | **Filtri**: ordinano le feature con una statistica; di solito supervisionati |
| **LDA**$(X,Y)$: supervisionata | **Wrapper** (supervisionati): Sequential Forward Selection (SFS), Sequential Backward Selection (SBS), RFE |
| **ICA**$(X)$: non supervisionata | **Embedded** (supervisionati): selezione durante l’addestramento |
| **t-SNE**$(X,\dots)$, **UMAP**$(X,\dots)$, **PaCMAP**$(X,\dots)$, **TriMAP**$(X,\dots)$: non supervisionati, per la visualizzazione | |
| **embedding()**: pre-addestrato, per esempio *embeddinggemma-2*, CLIP | |

<!-- SOURCE_VISUAL id="IS18-V05" source="L18-web.pdf" page="6" type="schema" description="Mappa delle tecniche divise tra estrazione e selezione delle feature" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010956.png)

> 📌 **Embedding come estrazione.** L’embedding di un’immagine o di un testo produce in uscita un **vettore di feature ingegnerizzate**. Appartiene alla classe dei **sistemi di estrazione dell’informazione**: trasformano i dati grezzi (pixel, parole) in un vettore compatto di feature.

---

### **1. Parte 1: la selezione delle feature**


**Quali feature contano?** Tenere un sottoinsieme delle feature originali, poi provarlo in laboratorio (RFE).

#### **1.1. Perché selezionare le feature?**

Le feature **irrilevanti** (nessuna informazione sull’uscita) e quelle **ridondanti** (ripetono un’altra feature) possono avere un **impatto negativo** sulle prestazioni del modello. Rimuoverle:

- **riduce l’overfitting** dovuto al rumore;
- **può migliorare l’accuratezza**;
- **riduce il tempo di addestramento** (la matrice $X$ è più piccola).

<!-- SOURCE_VISUAL id="IS18-V06" source="L18-web.pdf" page="8" type="schema" description="Imbuto da tutte le feature alle feature finali che entrano nel sistema intelligente" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125011540.png)

> 💡 **Il legame con la maledizione della dimensionalità (nota didattica).** Ogni feature in più aggiunge una dimensione allo spazio in cui il modello deve stimare le frontiere: con un numero fisso di esempi lo spazio diventa sempre più vuoto e le distanze sempre meno informative (Lezioni 19 e 20). Una feature irrilevante non solo non aiuta, ma aggiunge rumore alle distanze e gradi di libertà al modello.

#### **1.2. Filtri, wrapper, metodi embedded**

Tre modi di selezionare le feature. **Filtro**: ordinare, poi tenere le prime. **Wrapper**: cercare sottoinsiemi con un modello dentro un ciclo. **Embedded**: il modello seleziona mentre si addestra.

<!-- SOURCE_VISUAL id="IS18-V07" source="L18-web.pdf" page="9" type="schema" description="I tre approcci di selezione: filtro, wrapper, embedded (da quantdare.com)" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125011549.png)

##### **1.2.1. Filtri**

> 📌 **Definizione.** Un **filtro** ordina le feature con un punteggio calcolato **dai soli dati** (varianza, correlazione, informazione mutua), **indipendentemente da qualsiasi algoritmo di apprendimento**, **prima** che il modello venga addestrato.

**Metodi**:

- **criterio**: misurare la «**rilevanza**» della feature o del sottoinsieme;
- **ricerca**: di solito **ordinare** le feature (*ranking* delle singole feature o sottoinsiemi annidati);
- **valutazione**: **statistiche** (nessun modello viene addestrato).

**Risultati**: sono (relativamente) **robusti contro l’overfitting**; **possono non selezionare** le feature più «utili».

![](imgs/Pasted%20image%2020261125011603.png)

**Esempi**: preferire le feature con **varianza alta** (confrontate **sulla stessa scala**); preferire feature con **correlazione bassa** tra loro (similarità → cross-correlazione, Lezioni 8–10). **Più feature molto correlate → tenerne una.**

> ⚠️ **Perché un filtro può sbagliare (nota didattica).** Un filtro guarda le feature una alla volta (o a coppie). Due feature possono essere inutili da sole e decisive insieme: nello XOR (Lezione 20) né $x_1$ né $x_2$ sono correlate con l’uscita, ma la coppia la determina completamente. E «varianza alta» non significa «utile»: un sensore rumoroso ha molta varianza e nessuna informazione (è lo stesso equivoco della PCA, §3.4).

##### **1.2.2. Wrapper**

> 📌 **Definizione.** Un **wrapper** usa l’algoritmo di apprendimento come una **scatola nera**: ogni sottoinsieme candidato viene valutato con le **prestazioni del modello addestrato su di esso** (per esempio l’accuratezza in validazione incrociata), e una **procedura di ricerca** cerca il sottoinsieme migliore.

**Metodi**:

- **criterio**: misurare l’«**utilità**» del sottoinsieme;
- **ricerca**: esplorare lo **spazio di tutti i sottoinsiemi**;
- **valutazione**: **modello + validazione incrociata**.

**Risultati**:

- **in linea di principio** possono trovare le feature più «utili», **ma**
- sono **soggetti a overfitting**;
- sono **costosi**: un addestramento del modello (con validazione incrociata) **per ogni sottoinsieme**.

![](imgs/Pasted%20image%2020261125011621.png)

I modelli tipici sono **k-NN** o **reti neurali**.

> ⚠️ **Perché il wrapper fa overfitting (nota didattica).** Provare migliaia di sottoinsiemi e tenere quello con il punteggio di CV più alto è una forma di **progettazione sul test** (Lezione 14, §4.4): con abbastanza tentativi, uno risulterà buono per caso. Il punteggio del sottoinsieme vincitore è quindi ottimistico; la stima onesta richiede una partizione esterna mai usata dalla ricerca (*nested CV*). Il laboratorio del §2.6 applica questa regola: ranking e accuratezza calcolati dentro ogni fold.

##### **1.2.3. Sequential Forward Selection (SFS, wrapper): i candidati**

> 📌 **Definizione.** La **Sequential Forward Selection (SFS)** è una ricerca wrapper **greedy**: parte dall’**insieme vuoto**, aggiunge a ogni passo la feature con il **miglior punteggio in validazione incrociata**, e tiene il **sottoinsieme migliore lungo il percorso**.

Ogni nodo del reticolo è una **maschera**: 0 = feature non attiva, 1 = feature attiva; $m$ = numero di feature attive. Con $d=4$:

| $m$ | Maschere |
|---:|---|
| 0 | 0000 (partenza) |
| 1 | 1000, 0100, 0010, 0001 |
| 2 | 1100, 1010, 0110, 1001, 0101, 0011 |
| 3 | 1110, 1101, 1011, 0111 |
| 4 | 1111 |

<!-- SOURCE_VISUAL id="IS18-V08" source="L18-web.pdf" page="12" type="schema" description="Reticolo delle maschere di feature attive per d = 4, da m = 0 a m = 4" reason="Struttura del reticolo visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125011637.png)

**Tutti i sottoinsiemi con $j$ feature** sono

$$C(d,j)=\binom{d}{j}=\frac{d!}{j!\,(d-j)!}\qquad(d=\text{numero di feature},\ j=1,\dots,d).$$

Per $d=4$: $4+6+4+1=15$ sottoinsiemi; per $d=20$: **1 048 575**. La SFS ne prova solo $d+(d-1)+\dots+1$: $4+3+2+1=10$ per $d=4$, **210** per $d=20$. Poi seleziona il sottoinsieme migliore lungo il percorso con la validazione incrociata.

> 💡 **I due conteggi (nota didattica).** La somma $\sum_{j=1}^{d}\binom{d}{j}=2^d-1$ (tutti i sottoinsiemi tranne quello vuoto): con $d=20$, $2^{20}-1=1\,048\,575$. La SFS al primo passo prova $d$ feature, al secondo le $d-1$ rimaste, e così via: $\sum_{i=1}^{d}i=\frac{d(d+1)}{2}$, cioè $\frac{20\cdot 21}{2}=210$. Il costo passa da esponenziale a quadratico in $d$.

##### **1.2.4. SFS: il percorso**

**Ricerca guidata**: a ogni passo si tiene la **singola feature che dà il miglior punteggio** in validazione incrociata (i nodi gialli della figura) e **non si considerano percorsi alternativi**. Alla fine si seleziona il **sottoinsieme migliore lungo il percorso**.

<!-- SOURCE_VISUAL id="IS18-V09" source="L18-web.pdf" page="13" type="schema" description="Percorso greedy della SFS con i nodi scelti evidenziati in giallo" reason="Il percorso evidenziato è visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125011659.png)

> ⚠️ **Il limite della ricerca greedy.** Una scelta presa al primo passo non viene più rimessa in discussione: se la coppia migliore è $\{x_2,x_3\}$ ma la feature migliore da sola è $x_1$, la SFS parte da $x_1$ e può non arrivare mai a $\{x_2,x_3\}$. Si scambia l’ottimalità con il costo.

##### **1.2.5. Sequential Backward Selection (SBS, wrapper)**

> 📌 **Definizione.** La **Sequential Backward Selection (SBS)** è una ricerca wrapper greedy: parte da **tutte le feature** e **rimuove**, a ogni passo, **la meno utile**.

Circa $4+3+2$ valutazioni per $d=4$ se ci si ferma a una feature. Si seleziona il sottoinsieme migliore lungo il percorso con la validazione incrociata. Il reticolo è lo stesso, percorso dall’alto (1111) verso il basso.

<!-- SOURCE_VISUAL id="IS18-V10" source="L18-web.pdf" page="14" type="schema" description="Percorso della SBS dal nodo 1111 verso il basso" reason="Il percorso è visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125011713.png)

> 💡 **SFS o SBS? (nota didattica).** La SBS parte dal modello con tutte le feature, quindi vede subito le interazioni tra feature che la SFS scopre solo tardi; in compenso i primi addestramenti sono i più costosi (tante feature). Si sceglie la SFS quando ci si aspetta di tenere poche feature, la SBS quando se ne vogliono togliere poche.

##### **1.2.6. Metodi embedded**

> 📌 **Definizione.** Un **metodo embedded** esegue la selezione delle feature **durante l’addestramento** del modello, come parte dell’algoritmo di apprendimento (per esempio la **regolarizzazione L1** porta a 0 i pesi di alcune feature; l’**importanza** negli alberi di decisione).

**Metodi**: criterio = «utilità» del sottoinsieme; ricerca **guidata dal processo di apprendimento**; valutazione con la validazione incrociata.

**Risultati**: **simili ai wrapper**, **meno costosi**, **meno soggetti a overfitting**.

![](imgs/Pasted%20image%2020261125011724.png)

I metodi embedded sono **specifici di una data macchina di apprendimento** e selezionano le variabili (implicitamente) durante l’addestramento. Esempi: **LASSO** (la regolarizzazione L1 porta pesi a 0), **importanza delle feature negli alberi**, **pesi d’ingresso delle reti neurali**.

> 💡 **Perché L1 azzera e L2 no (nota didattica).** La penalità L1 $\lambda\sum_j|w_j|$ ha la stessa «spinta» verso zero qualunque sia la grandezza del peso, quindi i pesi poco utili arrivano esattamente a 0; la L2 $\lambda\sum_j w_j^2$ spinge in proporzione al peso, e i pesi diventano piccoli ma raramente nulli (Lezione 10, §8). Negli alberi l’importanza di una feature è la somma della riduzione di impurità (Gini o entropia, Lezione 12) dei nodi che la usano.

##### **1.2.7. Selezione embedded: l’esempio della rete neurale**

Con **input normalizzati** (stesso intervallo), se durante l’addestramento i pesi di un input vanno a 0 (un termine di regolarizzazione, per esempio L1, lo incoraggia), la rete considera quella feature **poco rilevante** per l’uscita.

- **Esempio con un neurone** (somma pesata seguita da una soglia a gradino): $w_4\to 0$, il peso dell’input $x_4$ → la feature «**non lavora**».
- **Esempio con una rete**: $W_{5i}\to 0$, i **pesi che escono dall’unità d’ingresso 5** (in rosso) → la feature «non lavora».

<!-- SOURCE_VISUAL id="IS18-V11" source="L18-web.pdf" page="16" type="schema" description="Neurone con w4 che tende a zero e rete con i pesi uscenti dall'input 5 che tendono a zero" reason="Schema visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125011735.png)

> ⚠️ **Perché «input normalizzati».** Se $x_4$ è misurato in millimetri e gli altri in metri, un peso piccolo su $x_4$ può avere lo stesso effetto di un peso grande sugli altri: la grandezza del peso dipende dall’unità di misura. Solo con input sulla stessa scala il peso misura l’importanza.

---

### **2. Laboratorio 1: selezione delle feature con RFE**


Dataset, wrapper, ranking dei pixel, quanti pixel servono.

#### **2.1. Il dataset usato negli script**

- **Un sostituto sintetico** di `sklearn.datasets.load_digits`: la funzione `load_digits_like()`.
- Ogni dato è un’**immagine 8×8 di una cifra** (prototipo disegnato a mano, spostamento casuale, sfocatura e rumore).

| | |
|---|---|
| Classi | 10 |
| Campioni per classe | 180 |
| Campioni totali | 1800 |
| Dimensionalità | 64 |
| Feature | interi 0–16 |

La figura mostra 40 dei 1800 campioni (4 per classe). Il dataset ha la **stessa dimensione** di `load_digits`, quindi il codice gira **senza modifiche sul dataset reale** (con le cifre reali i valori stampati sono diversi).

<!-- SOURCE_VISUAL id="IS18-V12" source="L18-web.pdf" page="18" type="figura" description="40 cifre sintetiche 8x8, quattro per classe" reason="Immagini delle cifre visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013022.png)

> 💡 **Il dataset reale (nota didattica).** Il vero `load_digits` di scikit-learn ha **1797** immagini (non esattamente 180 per classe): è il motivo per cui il laboratorio t-SNE del §5.2, eseguito sulle cifre reali, riporta 1797 campioni invece di 1800.

#### **2.2. Riduzione della dimensionalità: selezione con RFE**

**Recursive Feature Elimination (RFE)**, wrapper: una **SVM** (si può provare con k-NN, LDA, reti neurali, ecc.). **Quale pixel è più significativo per indurre la classe?**

<!-- SOURCE_VISUAL id="IS18-V13" source="L18-web.pdf" page="19" type="figura" description="Le cifre sintetiche usate per la RFE" reason="Immagini visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013606.png)

> 📌 **Che cosa fa la RFE (nota didattica).** Si addestra il modello su tutte le feature, si elimina la feature con il peso (o l’importanza) più piccolo, si riaddestra sulle rimanenti, e si ripete. L’ordine di eliminazione dà un **ranking**: l’ultima feature sopravvissuta ha rango 1. È una SBS in cui il criterio non è la CV di ogni candidato, ma il peso che il modello assegna alle feature: molto più economica.

#### **2.3. RFE: preparare i dati**

```python
from sklearn.svm import SVC
from sklearn.feature_selection import RFE

X = digits.images.reshape((len(digits.images), -1))  # (1800, 64)
y = digits.target                                     # (1800,)
print(X[1]); print(y[1])                              # 64 livelli di grigio 0-16, poi 5
```

**Input**: 1800 immagini di 8×8 pixel. **Output**: $X$, $1800\times 64$ (un pixel per colonna); $y$, 1800 etichette. **Nota**: `X[1]` è la seconda immagine «srotolata»; la sua etichetta è 5. `reshape((n, -1))` lascia a NumPy il calcolo della seconda dimensione: $8\cdot 8=64$.

#### **2.4. RFE: definire il wrapper**

```python
# Creare l'oggetto RFE e dare un rango a ogni pixel
svc = SVC(kernel="linear", C=1)
rfe = RFE(estimator=svc, n_features_to_select=1, step=1)
rfe.fit(X, y)
ranking = rfe.ranking_.reshape(digits.images[0].shape)   # (8, 8)
```

**Input**: $X$ ($1800\times 64$) e $y$ (1800). **Output**: `ranking`, una matrice $8\times 8$ con un rango per pixel. **Nota**: la SVM lineare è il modello del wrapper. `n_features_to_select=1, step=1`: **un pixel rimosso alla volta**, quindi ogni pixel riceve un rango (1 = ultimo sopravvissuto = il più importante).

> 💡 **Il costo (nota didattica).** Con `step=1` e 64 feature la SVM viene addestrata 63 volte, su 64, 63, …, 2 feature. Su un dataset più grande si usa `step` maggiore di 1 (per esempio il 10% delle feature per passo) per ridurre il costo.

#### **2.5. RFE: il ranking dei pixel**

```python
# Disegnare i risultati
print("pixel ranking (1 = most important)")
print(ranking)
```

La matrice stampata (riga del pixel, colonna):

| riga \ colonna | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| **0** | 4 | 48 | 63 | 62 | **64** | 61 | 26 | 42 |
| **1** | 28 | 15 | 55 | 60 | 44 | 33 | 22 | 46 |
| **2** | 11 | 39 | 10 | 36 | 9 | 24 | 12 | 2 |
| **3** | **1** | 27 | 19 | 29 | 20 | 49 | 56 | 37 |
| **4** | 31 | 8 | 34 | 47 | 7 | 45 | 17 | 13 |
| **5** | 3 | 25 | 54 | 43 | 51 | 38 | 21 | 6 |
| **6** | 40 | 16 | 52 | 18 | 59 | 32 | 53 | 14 |
| **7** | 5 | 23 | 30 | 41 | 57 | 58 | 35 | 50 |

![](imgs/Pasted%20image%2020261125013701.png)

**Nota**: la SVM classifica la cifra; rango 1 = **pixel più importante (riga 3, colonna 0)**; rango 64 = **meno importante (riga 0, colonna 4)**.

> 💡 **Leggere la matrice (nota didattica).** I ranghi bassi stanno spesso ai bordi sinistro e destro (colonne 0 e 7): in cifre sintetiche con spostamenti casuali, la presenza o assenza di inchiostro in quelle zone distingue bene alcune cifre (per esempio 1 contro 0). Ranghi molto alti nella riga 0 (63, 62, 64, 61) indicano pixel quasi sempre vuoti o uguali in tutte le classi: un filtro a varianza li avrebbe scartati subito. Il ranking dipende dal modello: con un k-NN o una LDA cambierebbe.

#### **2.6. RFE: disegnare il ranking dei pixel**

```python
# Disegnare il ranking dei pixel
plt.matshow(ranking, cmap=plt.cm.Blues)
plt.colorbar()
plt.title("Ranking of pixels with RFE")
plt.show()
```

**Input**: la matrice $8\times 8$ dei ranghi. **Output**: una **mappa dell’importanza dei pixel**. **Nota**: le celle chiare (rango basso) sono i pixel **più informativi**, quelle scure (rango vicino a 64) i meno: **la selezione delle feature vista come un’immagine**.

<!-- SOURCE_VISUAL id="IS18-V14" source="L18-web.pdf" page="23" type="grafico" description="Mappa del ranking RFE dei pixel con scala di colori Blues" reason="Mappa di colori visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013710.png)

#### **2.7. RFE: di quanti pixel abbiamo bisogno?**

**Input**: i 64 pixel ordinati dalla RFE. **Output**: l’**accuratezza 5-NN** (5-fold CV) usando **solo i primi $n$ pixel**, $n=2,5,10,20,64$.

| Pixel tenuti (su 64) | 2 | 5 | 10 | 20 | 64 |
|---|---:|---:|---:|---:|---:|
| Accuratezza 5-NN (5-fold CV) | 45,8% | 83,4% | **96,3%** | 98,2% | 98,6% |

<!-- SOURCE_VISUAL id="IS18-V15" source="L18-web.pdf" page="24" type="grafico" description="Barre dell'accuratezza 5-NN al variare del numero di pixel scelti dalla RFE dentro ogni fold" reason="Grafico a barre visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013720.png)

**Nota**: ranking e accuratezza sono calcolati **dentro ogni fold** della validazione incrociata: **i pixel di test non influenzano mai la selezione**. **Risultato**: con **10 pixel** l’accuratezza è già **96%**, contro il 98,6% con tutti i 64: **la maggior parte dell’input viene rimossa a un costo piccolo**. (Cifre sintetiche, valori da rivedere.)

> 💡 **Il compromesso in numeri (nota didattica).** Con 10 pixel su 64 si tiene il 15,6% dell’input e si perdono 2,3 punti di accuratezza; da 10 a 20 pixel si recuperano 1,9 punti, da 20 a 64 solo 0,4. È una curva a rendimenti decrescenti: il «gomito» è tra 10 e 20 pixel. Il codice della slide del §2.4 (`rfe.fit(X, y)` su tutti i dati) va bene per **disegnare** il ranking, ma per **valutare** l’accuratezza la RFE va rifatta dentro ogni fold, come in questo esperimento: altrimenti la selezione ha già visto i dati di test (data leakage di tipo B1, Lezione 7).

---

### **3. Parte 2: l’estrazione delle feature — la PCA**


Costruire poche feature nuove da molte: PCA, LDA, ICA, mappe per la visualizzazione, embedding.

#### **3.1. Visualizzare dati non supervisionati ad alta dimensionalità**

- L’apprendimento non supervisionato opera tipicamente in **spazi di feature ad alta dimensionalità**.
- I **dataset industriali reali** (sensori, log) contengono **decine di feature numeriche e categoriche**; quelle categoriche vanno **codificate in numeri prima della PCA** (Lezione 16, §5).
- La **visualizzazione umana è limitata a 1D, 2D, 3D**.
- **Distanze e struttura dei cluster vivono nello spazio originale ad alta dimensionalità**, che non si può visualizzare direttamente.

<!-- SOURCE_VISUAL id="IS18-V16" source="L18-web.pdf" page="26" type="figura" description="Esempi di dati industriali ad alta dimensionalità" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013735.png)

#### **3.2. L’analisi delle componenti principali (PCA)**

- **Spazio originale: 3 geni**. Input: 3 feature = **attivazione del gene 1, del gene 2 e del gene 3** (sintetiche, unità arbitrarie), **240 cellule in 4 stati cellulari**. Le cellule stanno vicino a un **piano inclinato** (ombreggiato).
- **Spazio delle componenti: 2 feature**. Output: 2 feature = le **componenti principali PC1 e PC2** (66% e 33% della varianza). La freccia arancione in 3D è la PC1.

<!-- SOURCE_VISUAL id="IS18-V17" source="L18-web.pdf" page="27" type="grafico" description="240 cellule nello spazio dei 3 geni vicino a un piano inclinato e la loro proiezione su PC1 e PC2" reason="Grafici 3D e 2D visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013744.png)

> 📌 **L’idea.** Si tratta di **trovare un sottospazio opportunamente traslato e ruotato che descriva i dati**. Se le cellule stanno quasi su un piano, due coordinate su quel piano bastano: la terza (la distanza dal piano) è quasi rumore.

#### **3.3. La PCA vista sulla matrice dei dati (3 geni, 20 cellule)**

- **Input** $X_c$: **3 geni × 20 cellule** mostrate (di $N=240$), valori **meno la media del gene**, colori *jet*.
- **Output** $Z$: **3 componenti × le stesse 20 cellule**, stessa scala di colori.

| Varianza | riga 1 | riga 2 | riga 3 | totale |
|---|---:|---:|---:|---:|
| $X_c$ (gene 1, 2, 3) | 27,9 | 20,3 | 12,4 | 60,6 |
| $Z$ (PC1, PC2, PC3) | 40,0 | 19,7 | 0,8 | 60,5 |

<!-- SOURCE_VISUAL id="IS18-V18" source="L18-web.pdf" page="28" type="figura" description="Strisce di colore della matrice centrata dei 3 geni e della matrice delle 3 componenti per 20 cellule" reason="Mappa di colori visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013758.png)

**Nota**: il cambiamento di colore attorno alla media è la varianza. In $X_c$ la variazione è **distribuita su tre righe**; in $Z$ sta in **PC1 (40,0) e PC2 (19,7)**, mentre **PC3 (0,8) è quasi un verde piatto**: eliminarla **perde poco**. **Controllo**: la **varianza totale è conservata**: $27{,}9+20{,}3+12{,}4=60{,}6=40{,}0+19{,}7+0{,}8$ (varianze su tutte le 240 cellule; $60{,}5$ per arrotondamento).

> 💡 **Perché la varianza totale si conserva (nota didattica).** La PCA è una **rotazione** degli assi: le distanze dal centro non cambiano, quindi nemmeno la loro somma dei quadrati, cioè la varianza totale. In termini algebrici, la traccia della matrice di covarianza (somma delle varianze delle feature) è uguale alla somma degli autovalori, e la rotazione non cambia la traccia. La PCA non crea né distrugge varianza: la **concentra** nelle prime componenti.

#### **3.4. L’idea della PCA**

- È **non supervisionata** → **non usa etichette**.
- Dati dei punti in uno spazio a $d$ dimensioni, **proiettarli in uno spazio a dimensione minore preservando quanta più informazione possibile**:
  - per esempio trovare la **migliore approssimazione planare** di dati 3D;
  - per esempio trovare la **migliore approssimazione a 12 dimensioni** di dati a 10 000 dimensioni.
- In particolare, si sceglie la proiezione che **minimizza l’errore quadratico nella ricostruzione** dei dati originali. Questo è **equivalente a massimizzare la varianza** dei dati proiettati (slide successiva).

<!-- SOURCE_VISUAL id="IS18-V19" source="L18-web.pdf" page="29" type="figura" description="Approssimazione planare di una nuvola 3D" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013812.png)

#### **3.5. L’idea della PCA (2): massima varianza = minimo errore**

**Proiezione ortogonale** dei dati su uno spazio lineare di dimensione minore che:

- **massimizza la varianza** dei dati proiettati (linea viola);
- **minimizza la distanza quadratica media** tra i punti e le loro proiezioni (somma delle linee blu);
- **i due criteri sono equivalenti**: per dati centrati, $(\text{distanza dal centro})^2=(\text{proiezione})^2+(\text{errore})^2$, e il primo termine è fisso.

<!-- SOURCE_VISUAL id="IS18-V20" source="L18-web.pdf" page="30" type="grafico" description="Direzione a varianza più alta e più bassa con le proiezioni e gli errori dei punti" reason="Geometria visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125013819.png)

> 💡 **Pitagora (nota didattica).** Per un punto centrato $x$ e una direzione unitaria $u$, la proiezione ha lunghezza $u^\top x$ e l’errore è il vettore ortogonale $x-(u^\top x)u$. Il teorema di Pitagora dà $\|x\|^2=(u^\top x)^2+\|x-(u^\top x)u\|^2$. Sommando su tutti i punti, $\sum\|x_i\|^2$ non dipende da $u$: quindi rendere grande la somma delle proiezioni al quadrato (varianza) e rendere piccola la somma degli errori al quadrato sono **lo stesso problema**.

#### **3.6. L’idea della PCA (3): le componenti successive**

- I vettori **partono dal centro di massa** dei dati.
- La **componente principale #1 (PC1)** punta nella **direzione della varianza massima**.
- Ogni componente principale successiva:
  - è **ortogonale alle precedenti**, e
  - punta nella **direzione della varianza massima del sottospazio residuo**.

<!-- SOURCE_VISUAL id="IS18-V21" source="L18-web.pdf" page="31" type="grafico" description="Nuvola di punti con PC1 e PC2 che partono dal centro di massa" reason="Direzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014046.png)

#### **3.7. La PCA: definizione**

- La PCA è una tecnica di riduzione della dimensionalità **lineare e non supervisionata**.
- **Proietta i dati su un nuovo insieme di assi ortogonali.**
- Ogni asse (componente principale) è una **combinazione lineare delle feature originali** e cattura la **varianza massima possibile**.
- Le **prime due componenti** (PC1, PC2) trattengono la frazione più grande della varianza e permettono la **visualizzazione 2D**.

$$z=W^\top(x-\bar{x}),$$

con $x\in\mathbb{R}^d$ un dato con $d$ feature (input); $\bar{x}$ la media di tutti i dati (centro di massa); $W\in\mathbb{R}^{d\times k}$ le **direzioni principali** (autovettori unitari, uno per colonna); $z\in\mathbb{R}^k$ la **rappresentazione ridotta**, $k<d$ numeri (output).

> ⚠️ **NOTA**: la PCA **non fa clustering** e **non usa le etichette**. I colori nei grafici vengono da etichette (o cluster) **calcolate altrove** e servono solo all’occhio.

#### **3.8. La PCA come blocco**

![](imgs/Pasted%20image%2020261125014058.png)

$$X\ (N\times d,\ \text{es. }240\times 3)\ \xrightarrow{\ \text{PCA: autovettori di }C\ }\ Z\ (N\times k,\ \text{es. }240\times 2),$$

con $k$ = numero di componenti da tenere, **scelto dall’utente** ($k<d$), per esempio 2.

- $X$: la matrice dei dati, **una riga per dato**: $N$ = numero di punti (240 cellule nell’esempio), $d$ = numero di feature (3 geni), **come in scikit-learn**.
- $Z$: gli **stessi $N$ punti** (ancora 240) descritti da soli **$k$ numeri ciascuno** (2) (riga $i$: $z_i^\top=(x_i-\bar{x})^\top W$).
- **Nessuna etichetta entra nel blocco**: la PCA è non supervisionata.

**Esempio (3 geni)**: $d=3$ geni, $N=240$ cellule, $k=2$. **Input** $X$: $240\times 3$ numeri (attivazione, unità arbitrarie). **Output** $Z$: $240\times 2$ numeri (PC1, PC2). **I calcoli matriciali non sono d’esame.**

#### **3.9. Il blocco PCA con i numeri veri (3 geni)**

$X_c$ ($240\times 3$) → PCA, $W^\top$ ($3\times 3$) → $Y$ ($240\times 3$, tutte le PC) → si selezionano le prime $k$ PC → $Z$ ($240\times k$), con $k=2$ scelto dall’utente.

<!-- SOURCE_VISUAL id="IS18-V22" source="L18-web.pdf" page="34" type="figura" description="Strisce di colore di Xc, Y e Z per 20 cellule nel blocco PCA" reason="Mappe di colori visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014134.png)

**Una cellula** (colonna 1 delle strisce), unità arbitrarie:

$$x=\begin{bmatrix}3{,}3\\1{,}1\\-0{,}7\end{bmatrix},\quad \bar{x}=\begin{bmatrix}5{,}9\\5{,}2\\4{,}1\end{bmatrix},\quad x-\bar{x}=\begin{bmatrix}-2{,}6\\-4{,}1\\-4{,}9\end{bmatrix}.$$

$$\text{PCA: }\ y=W^\top(x-\bar{x})=\begin{bmatrix}0{,}64&0{,}59&0{,}49\\0{,}76&-0{,}56&-0{,}33\\-0{,}08&-0{,}59&0{,}81\end{bmatrix}\begin{bmatrix}-2{,}6\\-4{,}1\\-4{,}9\end{bmatrix}=\begin{bmatrix}-6{,}5\\1{,}9\\-1{,}3\end{bmatrix}$$

(3 numeri, assi ruotati). **Selezione $k=2$**: $z=\begin{bmatrix}y_1\\y_2\end{bmatrix}=\begin{bmatrix}-6{,}5\\1{,}9\end{bmatrix}$; $y_3=-1{,}3$ viene **scartato** (PC3 tiene circa l’1% della varianza).

> 💡 **Verifica del prodotto (nota didattica).** $y_1=0{,}64(-2{,}6)+0{,}59(-4{,}1)+0{,}49(-4{,}9)=-1{,}66-2{,}42-2{,}40=-6{,}48$; $y_2=0{,}76(-2{,}6)-0{,}56(-4{,}1)-0{,}33(-4{,}9)=-1{,}98+2{,}30+1{,}62=1{,}94$; $y_3=-0{,}08(-2{,}6)-0{,}59(-4{,}1)+0{,}81(-4{,}9)=0{,}21+2{,}42-3{,}97=-1{,}34$. La terza coordinata di $x-\bar{x}$ calcolata a mano è $-0{,}7-4{,}1=-4{,}8$; la slide riporta $-4{,}9$ per arrotondamento dei valori mostrati. Le righe di $W^\top$ hanno norma 1: $0{,}64^2+0{,}59^2+0{,}49^2=0{,}41+0{,}35+0{,}24=1{,}00$.

#### **3.10. La PCA: i passi**

0. **Dati**: $X\in\mathbb{R}^{N\times d}$ (riga $i$ = punto $i$, $x_i^\top$), **nessuna etichetta**; scegliere $k<d$.
1. **Centratura**: $\bar{x}=\frac1N\sum_{i=1}^{N}x_i$, $X_c=X-\mathbf{1}\bar{x}^\top$ (sottrarre $\bar{x}$ da ogni riga).
2. **Covarianza**: $C=\frac{1}{N-1}X_c^\top X_c\in\mathbb{R}^{d\times d}$.
3. **Autocoppie**: $Cu_j=\lambda_ju_j$, con $\lambda_1\ge\lambda_2\ge\dots\ge\lambda_d$.
4. **Selezione**: $W=[u_1\ \cdots\ u_k]\in\mathbb{R}^{d\times k}$ (i primi $k$ autovettori).
5. **Proiezione**: $Z=X_cW\in\mathbb{R}^{N\times k}$; la riga $i$ è il punto ridotto del campione $i$.

$\mathbf{1}$ è una colonna di $N$ uni; $\lambda_j$ è la **varianza lungo $u_j$**; $u_j$ è la **direzione unitaria** della componente $j$. Dividere per $N-1$ (o per $N$) **riscala solo $C$**: gli autovettori non cambiano. **Esempio (3 geni)**: $X_c$ $240\times 3$, $C$ $3\times 3$, $W$ $3\times 2$, $Z$ $240\times 2$; varianza tenuta $=\frac{\lambda_1+\lambda_2}{\lambda_1+\lambda_2+\lambda_3}\approx 99\%$.

> 💡 **Perché gli autovettori della covarianza (nota didattica).** La varianza dei dati proiettati su una direzione unitaria $u$ è $u^\top Cu$. Massimizzarla con il vincolo $\|u\|=1$ (moltiplicatori di Lagrange) porta a $Cu=\lambda u$: le direzioni stazionarie sono gli autovettori, e la varianza lungo ciascuno è proprio $\lambda$. Il massimo è l’autovettore con l’autovalore più grande. Il calcolo è nell’appendice (§10).

#### **3.11. Esempio: da 10 a 2 dimensioni**

**Input**: **400 punti × 10 feature** ($400\times 10$, sintetici, unità arbitrarie): **4 cluster rumorosi e sovrapposti nascosti nei numeri**. $X_c$ ($400\times 10$) → PCA → $Y$ ($400\times 10$) → prime $k=2$ PC → $Z$ ($400\times 2$). **10 componenti: 2 portano quasi tutto.**

<!-- SOURCE_VISUAL id="IS18-V23" source="L18-web.pdf" page="36" type="grafico" description="Matrici Xc e Y di 400 punti, varianza spiegata per componente e mappa PC1-PC2 con quattro cluster" reason="Strisce, barre e scatter visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014149.png)

**Output**: $Z$, $400\times 2$. **{PC1, PC2} tengono il 60% della varianza**; nella mappa **i cluster appaiono**. Le etichette colorano solo i punti.

#### **3.12. Autovalori: quante componenti tenere?**

- L’autovalore $\lambda_j$ di $C$ = **varianza lungo PC$_j$**, ordinati $\lambda_1\ge\lambda_2\ge\dots$ Qui:

| $j$ | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $\lambda_j$ (u.a.) | 8,7 | 7,3 | 1,6 | 1,5 | 1,4 | 1,4 | 1,3 | 1,2 | 1,1 | 1,0 |

con $\sum_j\lambda_j=26{,}5$.

- **La curva** $=\sum_{j\le k}\lambda_j\big/\sum_j\lambda_j$: **quanta varianza dell’input possono ricostruire $k$ componenti**.
- **Gomito**: dopo PC2 le barre sono **piatte** (≈ 1, il **livello del rumore**): **$k=2$ tiene il 60%**.
- **Regola del bersaglio**: il **$k$ più piccolo che raggiunge** per esempio il 90% (qui **$k=8$**: il rumore è sparso su tutti gli assi).
- **Le componenti scartate costano** $\sum_{j>k}\lambda_j$ (**errore di ricostruzione**).
- **La riga $j$ di $W^\top$ è l’autovettore $u_j$** (10 pesi, uno per feature); le righe 1–2 si tengono per $k=2$.

<!-- SOURCE_VISUAL id="IS18-V24" source="L18-web.pdf" page="37" type="grafico" description="Autovalori a barre e curva della varianza cumulativa con gomito a k = 2" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014216.png)

Le prime righe di $W^\top$ riportate dalla slide (le righe centrali sono omesse con «…»):

| | $f_1$ | $f_2$ | $f_3$ | $f_4$ | $f_5$ | $f_6$ | $f_7$ | $f_8$ | $f_9$ | $f_{10}$ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| $u_1$ | 0,11 | −0,10 | −0,01 | 0,34 | 0,09 | −0,14 | −0,33 | 0,22 | 0,55 | 0,61 |
| $u_2$ | 0,09 | −0,36 | −0,38 | 0,49 | −0,26 | 0,10 | −0,33 | 0,26 | −0,11 | −0,46 |
| $u_3$ | −0,19 | −0,30 | −0,35 | 0,02 | 0,38 | 0,21 | 0,49 | 0,42 | −0,25 | 0,29 |
| … | … | … | … | … | … | … | … | … | … | … |
| $u_{10}$ | −0,14 | 0,24 | −0,09 | 0,51 | 0,62 | −0,14 | −0,14 | −0,42 | −0,23 | −0,06 |

> 💡 **I conti (nota didattica).** Varianza cumulativa: $k=1$: $8{,}7/26{,}5=32,8\%$; $k=2$: $16{,}0/26{,}5=60{,}4\%$; $k=3$: 66,4%; $k=7$: $23{,}2/26{,}5=87{,}5\%$; $k=8$: $24{,}4/26{,}5=92{,}1\%$. Il 90% richiede quindi 8 componenti, anche se 6 di esse portano solo rumore: quando il rumore è isotropo (uguale in tutte le direzioni), la regola del 90% «compra» rumore. Il gomito è il criterio giusto per vedere la struttura, il bersaglio di varianza per ricostruire i dati. Errore di ricostruzione con $k=2$: $26{,}5-16{,}0=10{,}5$.

#### **3.13. La PCA in pratica: una pipeline per un classificatore**

**Obiettivo**: classificare le immagini delle cifre usando **poche feature decorrelate** invece di tutti i 64 pixel. La PCA è **un passo di una catena**; va stimata **solo sui dati di training**.

$X$ ($1800\times 64$) → **1. scalare** (`StandardScaler`) → **2. ridurre** (PCA($k$): da 64 a $k$) → **3. classificare** (5-NN) → cifra prevista.

<!-- SOURCE_VISUAL id="IS18-V25" source="L18-web.pdf" page="38" type="schema" description="Pipeline scaler, PCA e 5-NN per classificare le cifre" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014224.png)

```python
model = make_pipeline(StandardScaler(), PCA(10), KNeighborsClassifier(5))
model.fit(X_train, y_train)          # scaler e PCA vedono solo il training set
print(model.score(X_test, y_test))   # 0.98 con k=10 (0.67 con k=2)
```

- **Prima scalare**: la PCA segue la varianza, quindi una feature con **unità grandi dominerebbe**.
- **Stimare solo sul training set** (70% training / 30% test): **stimare la PCA su tutti i dati fa trapelare informazione del test**.
- **Scegliere $k$** dalla varianza cumulativa (90%: **20 componenti** qui) o dall’**accuratezza del classificatore**.

(Valori dalle cifre sintetiche, da rivedere.)

> 💡 **Perché `make_pipeline` protegge dal leakage (nota didattica).** La pipeline concatena i passi e li tratta come un unico modello: `fit` stima media e deviazione standard dello scaler e gli autovettori della PCA **solo** su `X_train`, poi `score` applica le stesse trasformazioni già stimate a `X_test`. Dentro una `cross_val_score` la pipeline viene riaddestrata in ogni fold, e il test di ogni fold non entra mai nella PCA. È la versione in codice della regola «fit sul training, transform sul test» (Lezione 10, §7).

#### **3.14. Applicazioni della PCA**

- visualizzazione dei dati;
- compressione / riduzione della dimensionalità;
- **riduzione del rumore**;
- classificazione dei dati;
- analisi delle tendenze;
- analisi fattoriale;
- estrazione delle feature;
- **dentro le reti neurali**: modelli di deep learning, **creazione automatica delle feature!** (un autoencoder lineare con collo di bottiglia impara lo stesso sottospazio della PCA, Lezione 22).

> 💡 **Riduzione del rumore (nota didattica).** Si proiettano i dati sulle prime $k$ componenti e li si ricostruisce con $\hat{x}=\bar{x}+Wz$: le componenti scartate, che nell’esempio del §3.12 erano al livello del rumore, vengono eliminate dalla ricostruzione.

---

### **4. Laboratorio 2: la PCA sulle cifre**


Caricare, ispezionare, proiettare, disegnare le componenti.

#### **4.1. Caricare i dati**

```python
import numpy as np
import matplotlib.pyplot as plt
from synth_digits import load_digits_like   # sostituto di sklearn.datasets.load_digits

digits = load_digits_like(n_per_class=180, seed=0)
digits.data.shape   # (n_samples, n_features) = (1800, 64)
```

**Input**: nessuno: un generatore sintetico con **seme fisso**, quindi ogni esecuzione dà gli stessi dati. **Output**: `digits.data`, matrice $1800\times 64$ (immagini × pixel), livelli di grigio 0–16. **Nota**: con il dataset reale si usa `from sklearn.datasets import load_digits` e `load_digits()`; il resto è identico. Il codice completo sarà pubblicato più avanti sul sito del corso.

#### **4.2. Ispezionare un campione**

```python
print(digits.data[0].shape)   # (64,)
print(digits.target[0])       # 8
plt.matshow(digits.images[0], cmap="gray_r")
plt.show()
```

**Input**: `digits.data[0]`, il primo campione. **Output**: un vettore di **64 interi** (gli 8×8 pixel srotolati); la sua etichetta `target[0]` è la **cifra 8**. **Nota**: la figura stampa anche il valore di ogni pixel.

| riga \ col. | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 0 | 0 | 5 | 12 | 14 | 15 | 13 | 5 | 0 |
| 1 | 3 | 13 | 12 | 5 | 3 | 16 | 13 | 2 |
| 2 | 6 | 11 | 14 | 4 | 3 | 16 | 11 | 0 |
| 3 | 1 | 4 | 12 | 11 | 13 | 12 | 4 | 1 |
| 4 | 3 | 10 | 13 | 4 | 2 | 11 | 12 | 4 |
| 5 | 11 | 14 | 4 | 1 | 0 | 5 | 14 | 11 |
| 6 | 1 | 15 | 12 | 4 | 6 | 11 | 11 | 3 |
| 7 | 0 | 4 | 14 | 16 | 12 | 14 | 6 | 0 |

<!-- SOURCE_VISUAL id="IS18-V26" source="L18-web.pdf" page="42" type="figura" description="Immagine 8x8 della cifra 8 in scala di grigi con i valori dei pixel" reason="Immagine visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014251.png)

> 💡 **Leggere la tabella (nota didattica).** Le righe 1–2 e 4–6 hanno due «picchi» (per esempio 13 e 16 nella riga 1): sono i due tratti laterali dei due anelli dell’8; la riga 3, con valori alti e continui al centro, è la strozzatura tra i due anelli. I valori vanno da 0 (bianco) a 16 (nero), con la mappa `gray_r` (scala di grigi invertita).

#### **4.3. Due feature grezze: scatter plot**

```python
f1, f2 = digits.data[:, 1], digits.data[:, 2]
sc = plt.scatter(f1, f2, c=digits.target,
                 cmap='tab10', alpha=0.65)
plt.xlabel('Feature 1')
plt.ylabel('Feature 2')
plt.legend(*sc.legend_elements(), title='digit')
```

**Input**: $1800\times 2$ livelli di grigio: **due pixel arbitrari** (riga 0, colonne 1 e 2). **Output**: uno scatter plot colorato per cifra. **Nota**: i valori sono **quantizzati** (0–16) e **non si vede alcuna struttura di classe**.

<!-- SOURCE_VISUAL id="IS18-V27" source="L18-web.pdf" page="43" type="grafico" description="Scatter di due pixel arbitrari colorato per cifra senza struttura di classe" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014306.png)

#### **4.4. Riduzione della dimensionalità con la PCA**

```python
from sklearn.decomposition import PCA

pca = PCA(2)                                # proiettare da 64 a 2 dimensioni
projected = pca.fit_transform(digits.data)
print(digits.data.shape)                    # (1800, 64)
print(projected.shape)                      # (1800, 2)
print(pca.explained_variance_ratio_)        # [0.19 0.15] -> 34% della varianza
```

**Input**: `digits.data`, $1800\times 64$ (campioni × feature). **Output**: `projected`, $1800\times 2$ componenti. **Estrazione delle feature**: $X\to x_{\text{engineered}}$. **Nota**: le **etichette non vengono passate** alla PCA (non supervisionata). Le prime due componenti tengono **circa un terzo della varianza (34%)**: è attesa una certa **sovrapposizione**.

#### **4.5. Le componenti PCA: scatter plot**

```python
p1, p2 = projected[:, 0], projected[:, 1]
sc = plt.scatter(p1, p2, c=digits.target,
                 cmap='tab10', alpha=0.65)
plt.xlabel('component 1')
plt.ylabel('component 2')
plt.legend(*sc.legend_elements(), title='digit')
```

**Input**: `projected` $1800\times 2$, più le etichette. **Output**: la mappa PCA (componente 1: 19%, componente 2: 15%). **Nota**: le etichette servono **solo a colorare**. Rispetto a due feature grezze, **le classi sono parzialmente separate**.

<!-- SOURCE_VISUAL id="IS18-V28" source="L18-web.pdf" page="45" type="grafico" description="Mappa PCA delle 1800 cifre colorata per classe" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014317.png)

> 💡 **Un confronto con il §3.13 (nota didattica).** Con 2 componenti il 5-NN arriva al 67%, con 10 al 98%: 34% di varianza bastano a «vedere» la struttura, non a classificare bene. La mappa serve a esplorare; per decidere si usa un $k$ scelto sull’accuratezza.

---

### **5. Teoria: riduzione della dimensionalità con LDA, t-SNE, ICA**


Supervisionata, non lineare, separazione cieca delle sorgenti.

#### **5.1. L’Analisi Discriminante Lineare (LDA)**

- Trova una **combinazione lineare delle feature che caratterizza o separa le classi**.
- La combinazione risultante può essere usata come **classificatore lineare** o come **riduzione della dimensionalità** prima di un classificatore.
- La LDA è **supervisionata**: usa le etichette (la PCA no).

![](imgs/Pasted%20image%2020261125014340.png)


<!-- SOURCE_VISUAL id="IS18-V29" source="L18-web.pdf" page="47" type="grafico" description="Rotazione sbagliata con classi sovrapposte e rotazione migliore della LDA" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014351.png)

A sinistra: rotazione sbagliata, **sovrapposizione!** A destra: **la rotazione migliore**. Il codice della LDA di Fisher è in un’altra lezione del corso (Lezione 17, §4).

| | **PCA** | **LDA** |
|---|---|---|
| Usa le etichette? | no (non supervisionata) | sì (supervisionata) |
| Cerca | direzioni di **varianza massima** | direzioni che **separano meglio le classi** |
| Quante componenti? | qualsiasi $k$, fino a $d$ (numero di feature): **lo sceglie l’utente** | al più $\min(d,C-1)$, con $C$ = numero di classi: **le classi fissano il massimo** |

**Esempio**: 10 cifre, 64 pixel → PCA fino a 64 componenti, **LDA al più 9**. Motivo: le **$C$ medie di classe generano al più $C-1$ direzioni**.

> 💡 **Perché $C-1$ (nota didattica).** La matrice di dispersione tra le classi $S_B=\sum_kN_k(\mu_k-\mu)(\mu_k-\mu)^\top$ è una somma di $C$ matrici di rango 1, ma i vettori $\mu_k-\mu$ sono legati da un vincolo ($\sum_kN_k(\mu_k-\mu)=0$): ne restano $C-1$ indipendenti. Con 2 classi le medie definiscono una sola direzione, quella di Fisher della Lezione 17.

#### **5.2. LDA: la rotazione di Fisher usa le classi**

![](imgs/Pasted%20image%2020261125014426.png)

$y$: etichette di classe (4 stati cellulari), 240 valori. $X_c$ ($240\times 3$) → LDA: $z=W_F^\top(x-\bar{x})$ → $Z$ ($240\times 2$; al più $C-1=3$).

$$W_F^\top=\begin{bmatrix}0{,}66&0{,}58&0{,}48\\0{,}76&-0{,}57&-0{,}32\end{bmatrix}$$

($2\times 3$, righe di lunghezza unitaria, **calcolate con le classi**). **Una cellula**:

$$x-\bar{x}=\begin{bmatrix}-4{,}1\\-15{,}1\\10{,}5\end{bmatrix},\qquad z=W_F^\top(x-\bar{x})=\begin{bmatrix}-6{,}4\\2{,}3\end{bmatrix}.$$

<!-- SOURCE_VISUAL id="IS18-V30" source="L18-web.pdf" page="48" type="grafico" description="Strisce di Xc e Z con la LDA e mappe PCA e LDA dei quattro stati cellulari" reason="Mappe e scatter visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014438.png)

**Come si calcola $W_F$ (Fisher)**: (1) medie di classe $\mu_k$, dispersione intra-classe $S_W$, dispersione tra classi $S_B$; (2) autovettori di $S_W^{-1}S_B$: l’autovalore $J$ = **varianza tra / varianza dentro** lungo quella direzione, qui $J=9{,}5;\ 4{,}1;\ 0{,}0$ (la **terza direzione non porta nulla**); (3) $W_F$: le prime due, **normalizzate a lunghezza 1**.

**Perché le etichette contano**: i 3 geni contengono, oltre agli stati, un **forte effetto di dimensione cellulare comune a tutti gli stati**. La **PCA segue la varianza** (PC1: il 74%, soprattutto quell’effetto); la **LDA segue le classi**. **Accuratezza 5-NN sulla mappa 2D: PCA 79%, LDA 98%.**

> 💡 **Due osservazioni (nota didattica).** 1) Ricalcolando con i pesi arrotondati: $z_1=0{,}66(-4{,}1)+0{,}58(-15{,}1)+0{,}48(10{,}5)=-2{,}71-8{,}76+5{,}04=-6{,}43$, coerente; $z_2=0{,}76(-4{,}1)-0{,}57(-15{,}1)-0{,}32(10{,}5)=-3{,}12+8{,}61-3{,}36=2{,}13$, contro il 2,3 della slide: la differenza viene dall’arrotondamento dei pesi a due cifre, amplificato da componenti grandi come $-15{,}1$. 2) Con 4 classi il massimo è $C-1=3$ direzioni, ma con 3 feature il limite effettivo è $\min(d,C-1)=3$; il terzo autovalore nullo dice che qui ne bastano 2. Attenzione: l’accuratezza «98%» sulla mappa LDA è calcolata dopo aver usato le etichette per costruire la mappa, quindi è ottimistica come ogni valutazione sui dati usati per progettare.

#### **5.3. Riduzione della dimensionalità: t-SNE**

- La **t-Distributed Stochastic Neighbor Embedding** è una **mappa non lineare** usata **soprattutto per la visualizzazione** di dataset ad alta dimensionalità.
- **Non ha `transform()` per campioni nuovi**, e le **distanze tra cluster o le dimensioni dei cluster nella mappa non hanno significato**: **usarla per guardare, non per alimentare un classificatore**.

$$Z=\text{tSNE}(X,k)\qquad(k=2\text{ o }3\text{ al massimo};\ X:N\times d,\ Z:N\times k).$$

<!-- SOURCE_VISUAL id="IS18-V31" source="L18-web.pdf" page="49" type="grafico" description="Confronto tra mappa PCA e mappa t-SNE delle stesse 1800 cifre" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014452.png)

A sinistra la PCA, a destra la t-SNE, con una **separazione migliore delle classi** (stesse 1800 immagini). Cifre sintetiche 8×8: 1800 immagini, 64 feature, 10 classi (**MNIST**: 60 000 immagini, 784 feature).

> 💡 **Che cosa ottimizza la t-SNE (nota didattica).** Per ogni coppia di punti calcola una probabilità di essere vicini nello spazio originale ($p_{ij}$, con una gaussiana la cui larghezza dipende dalla *perplexity*, circa il numero di vicini efficaci) e una nella mappa ($q_{ij}$, con una distribuzione t di Student a code pesanti); poi sposta i punti della mappa per minimizzare la divergenza KL tra le due (§6.2). Le code pesanti permettono di allontanare molto i gruppi diversi: per questo i cluster appaiono ben separati, ma le distanze tra cluster non sono quelle originali.

#### **5.4. ICA: separare sorgenti mescolate (esempi)**

Spesso usata nell’**elaborazione dei segnali** e nelle **applicazioni industriali**.

- **Esempio 1**: 3 parlanti, 4 microfoni.
- **Esempio 2**: 2 parlanti + rumore, 3 microfoni (**rumore forte**: un martello pneumatico).

<!-- SOURCE_VISUAL id="IS18-V32" source="L18-web.pdf" page="50" type="figura" description="Due scenari ICA: tre parlanti con quattro microfoni e due parlanti più martello pneumatico con tre microfoni" reason="Illustrazioni visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014500.png)

#### **5.5. ICA: osservazioni, sorgenti e rumore**

**Osservazioni (segnali mescolati) → ICA → sorgenti stimate.** **Ipotesi**: sorgenti **indipendenti e non gaussiane** e **miscelazione lineare**. Nell’esempio, **3 feature/segnali provengono da 2 sorgenti indipendenti + rumore**: la **componente di rumore si riconosce per ispezione**; poi si può **eliminare il terzo segnale trasformato**.

<!-- SOURCE_VISUAL id="IS18-V33" source="L18-web.pdf" page="51" type="grafico" description="Tre segnali osservati mescolati e le tre sorgenti stimate dalla ICA, una delle quali è rumore" reason="Forme d'onda visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014507.png)

> 📌 **Il modello dell’ICA (nota didattica).** Le osservazioni sono $x=As$, con $s$ le sorgenti indipendenti e $A$ la **matrice di miscelazione** (ignota): ogni microfono registra una somma pesata delle voci. L’ICA stima una matrice $W\approx A^{-1}$ tale che le componenti di $\hat{s}=Wx$ siano il più possibile **indipendenti**. Rispetto alla PCA, che rende le componenti solo **scorrelate** e le ordina per varianza, l’ICA chiede di più (l’indipendenza statistica) e non dà un ordine naturale: le sorgenti stimate escono in ordine e scala arbitrari, ed è per questo che il rumore va riconosciuto per ispezione.

> 💡 **Perché «non gaussiane» (nota didattica).** Una combinazione lineare di variabili indipendenti è «più gaussiana» delle variabili di partenza (teorema del limite centrale): l’ICA cerca le direzioni in cui i dati sono **massimamente non gaussiani**, e quelle sono le sorgenti. Se le sorgenti fossero gaussiane, ogni rotazione darebbe componenti indipendenti e il problema non avrebbe soluzione unica.

---

### **6. Laboratorio 3: la t-SNE sulle cifre**


Mappa t-SNE e confronto con la PCA e con due pixel grezzi.

#### **6.1. Il codice**

```python
from sklearn.manifold import TSNE

tsne = TSNE(n_components=2, verbose=1, perplexity=40, max_iter=1000, random_state=0)
tsne_results = tsne.fit_transform(digits.data)   # nessuna etichetta y
```

**Input**: `digits.data`, $1797\times 64$ (il **vero** `load_digits`); **nessuna etichetta** $y$. **Output**: `tsne_results`, $1797\times 2$. **Nota**: il log riporta la **divergenza di Kullback–Leibler finale (0,71)**: **più è bassa, meglio la mappa 2D conserva i vicinati**. (In scikit-learn ≥ 1.5 il parametro si chiama `max_iter`; nelle versioni precedenti `n_iter`.)

#### **6.2. La divergenza di Kullback–Leibler**

Già vista in altre lezioni del corso (Lezione 4):

$$D_{KL}(P\,\|\,Q)=\sum_{i\neq j}p_{ij}\log\frac{p_{ij}}{q_{ij}}\ \ge 0,\qquad =0\ \text{solo se}\ P=Q.$$

$p_{ij}$: similarità dei punti $i,j$ nello **spazio a 64 dimensioni**; $q_{ij}$: similarità degli stessi punti nella **mappa 2D**. **Esempio**: $p_{ij}=0{,}5$, $q_{ij}=0{,}25$ contribuisce $0{,}5\ln 2\approx 0{,}35$; la t-SNE **sposta i punti 2D per minimizzare la somma**.

> 💡 **L’asimmetria che spiega le mappe (nota didattica).** Il termine pesa con $p_{ij}$: se due punti sono vicini nello spazio originale ($p_{ij}$ grande) ma lontani nella mappa ($q_{ij}$ piccolo), il contributo è grande e la t-SNE li avvicina; se sono lontani nell’originale ($p_{ij}\approx 0$), il contributo è quasi nullo qualunque sia la loro distanza nella mappa. La t-SNE quindi **conserva i vicinati locali** e **non si cura delle distanze grandi**: è la ragione formale dell’avvertenza «distanze tra cluster senza significato».

#### **6.3. Le componenti t-SNE: scatter plot**

```python
t1, t2 = tsne_results[:, 0], tsne_results[:, 1]
sc = plt.scatter(t1, t2, c=digits.target,
                 cmap='tab10', alpha=0.65)
plt.xlabel('tsne-2d-one')
plt.ylabel('tsne-2d-two')
plt.legend(*sc.legend_elements(), title='digit')
```

**Input**: `tsne_results` $1797\times 2$, più le etichette. **Output**: la mappa t-SNE. **Nota**: cifre reali: **dieci cluster, uno per cifra**, con il nome scritto nella figura alla **mediana del cluster**; **alcuni punti di 1, 2 e 8 stanno in disparte**. Le etichette **colorano e nominano** soltanto i cluster.

<!-- SOURCE_VISUAL id="IS18-V34" source="L18-web.pdf" page="54" type="grafico" description="Mappa t-SNE delle 1797 cifre reali con dieci cluster etichettati" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014528.png)

#### **6.4. Confronto: due pixel arbitrari, PCA, t-SNE**

**Input**: le **stesse 1800 immagini**, sempre ridotte a **2 feature**.

| Mappa 2D | Due pixel arbitrari | PCA (estrazione) | t-SNE (estrazione) |
|---|---:|---:|---:|
| Accuratezza 5-NN (5-fold CV) | 26% | 75% | **98%** |

<!-- SOURCE_VISUAL id="IS18-V35" source="L18-web.pdf" page="55" type="grafico" description="Tre mappe 2D delle stesse cifre: due pixel, PCA e t-SNE" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014540.png)

**Nota**: PCA e t-SNE sono **stimate su tutte le 1800 immagini**, quindi i numeri **indicano solo quanto sono separate le classi**. **La t-SNE non può mappare campioni nuovi.**

> ⚠️ **Perché il 98% della t-SNE non è un’accuratezza utilizzabile (nota didattica).** Per classificare un’immagine nuova bisognerebbe rifare l’intera mappa con quell’immagine dentro, e la nuova mappa avrebbe coordinate diverse dalla precedente: il 5-NN addestrato sulla vecchia mappa non si può applicare. Inoltre la mappa è stata costruita guardando anche le immagini di test (un leakage strutturale). Il 98% misura la separabilità visiva, non le prestazioni di un sistema rilasciabile. La PCA (75%) invece ha un `transform()` e può diventare un passo di pipeline. Il 26% dei due pixel arbitrari è circa 2,6 volte il caso (10%).

#### **6.5. Cinque modi di visualizzare dati ad alta dimensionalità**

**Stesso input**: $X\in\mathbb{R}^{N\times d}$; **stesso output**: $Z\in\mathbb{R}^{N\times 2}$. I cinque algoritmi **ottimizzano relazioni diverse** nella mappa 2D.

| Metodo | Meccanismo principale | Che cosa enfatizza | Chiamata (senza $Y$) |
|---|---|---|---|
| **PCA** | assi lineari, autovettori / SVD | **massima varianza trattenuta** | PCA($X$) |
| **t-SNE** | far corrispondere le probabilità di vicinato in 2D | **vicinati locali** | t-SNE($X,\dots$) |
| **UMAP** | costruire un grafo fuzzy dei vicini e ottimizzarne l’embedding | **topologia locale / relazioni di vicinato** | UMAP($X,\dots$) |
| **PaCMAP** | ottimizzare coppie vicine, mediamente vicine e lontane | **equilibrio tra struttura locale e più ampia** | PaCMAP($X,\dots$) |
| **TriMAP** | ottimizzare vincoli relativi tra terne | **relazioni relative, spesso struttura più ampia/globale** | TriMAP($X,\dots$) |

> 📌 **Concetto d’esame.** Tutti e cinque si chiamano **senza le etichette $Y$** (UMAP ha anche un’opzione supervisionata). **Colori visivamente separati non dimostrano accuratezza predittiva, generalizzazione, né conservazione di tutte le distanze originali.**

> 💡 **SVD (nota didattica).** La decomposizione ai valori singolari $X_c=U\Sigma V^\top$ dà direttamente gli autovettori di $X_c^\top X_c$ (le colonne di $V$) senza formare la matrice di covarianza: è il modo numericamente stabile con cui le librerie calcolano la PCA. Gli autovalori sono $\lambda_j=\sigma_j^2/(N-1)$.

#### **6.6. La proiezione cambia la rappresentazione**

Ogni dato $x_i\in\mathbb{R}^d$ (feature originali) passa per la riduzione della dimensionalità $f$ e diventa $z_i=f(x_i)\in\mathbb{R}^2$ (mappa 2D). **Un $f$ diverso conserva relazioni diverse.** Ogni punto è un’osservazione; i colori si aggiungono **dopo** la proiezione. Stesse 1800 cifre sintetiche, stesse 64 feature d’ingresso, **due viste 2D diverse** (PCA e t-SNE).

<!-- SOURCE_VISUAL id="IS18-V36" source="L18-web.pdf" page="57" type="grafico" description="Schema della funzione di riduzione f e le due mappe PCA e t-SNE delle stesse cifre" reason="Grafici visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014626.png)

> ⚠️ **Assi, scala e distanze globali** delle proiezioni **non sono direttamente confrontabili**. Una vista 2D conserva **relazioni selezionate**, **non la geometria completa**.

#### **6.7. Immagini: visualizzare le feature, non necessariamente i pixel**

Il flusso tipico di **embedding delle immagini** usato nella visione artificiale e nell’ispezione industriale:

**immagini** (256×256 o più piccole) → **estrazione delle feature** (classica / CNN / autoencoder / foundation model) → **vettore di feature** $z_i\in\mathbb{R}^d$ → **proiezione 2D** (PCA / t-SNE / UMAP / PaCMAP / TriMAP) → **mappa 2D** $u_i\in\mathbb{R}^2$.

<!-- SOURCE_VISUAL id="IS18-V37" source="L18-web.pdf" page="58" type="schema" description="Flusso immagini, estrazione delle feature, vettore e proiezione 2D" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014639.png)

- **Feature classiche**: descrittori progettati a mano come **tessitura, intensità o gradienti dell’immagine** (Lezioni 8 e 21).
- **Embedding appresi**: vettori d’uscita di **CNN, autoencoder, encoder di foundation model**.
- **Perché non sempre i pixel grezzi?** Un’immagine RGB 256×256 ha **196 608 valori d’ingresso**. La distanza tra pixel è spesso **dominata da allineamento, sfondo e illuminazione** (Lezione 8).
- **Eccezione**: le immagini piccole e allineate delle cifre sono **utili per la didattica**.

> 💡 **Il conto (nota didattica).** $256\cdot 256\cdot 3=196\,608$. Spostare un’immagine di un pixel cambia quasi tutti i valori, e la distanza euclidea tra l’immagine e la sua copia spostata può essere più grande di quella tra due oggetti diversi sullo stesso sfondo: per questo si confrontano le feature, invarianti a queste trasformazioni.

---

### **7. Laboratorio 4: cinque mappe su Iris, Wine, MNIST**


PCA, t-SNE, UMAP, PaCMAP, TriMAP su dati reali.

#### **7.1. Iris: cinque proiezioni 2D a confronto**

**IRIS** | $N=150$ campioni | input $d=4$ | 3 classi. $X$: $150\times 4$. Colori di classe (**etichette NON fornite alle proiezioni non supervisionate**). **StandardScaler sull’intero dataset solo per la visualizzazione** (nessuna metrica di training/test) | assi indipendenti; valori delle coordinate non confrontabili.

<!-- SOURCE_VISUAL id="IS18-V38" source="L18-web.pdf" page="60" type="grafico" description="Iris proiettato con PCA, t-SNE, UMAP, PaCMAP e TriMAP" reason="Cinque mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014651.png)

Iris: $N=150$ fiori, $d=4$ misure standardizzate, $C=3$ specie. **Stesse osservazioni in ogni pannello**; etichette solo per colorare, **non per imparare la proiezione**. **Regola di lettura**: gli assi sono indipendenti; **la separazione 2D non è accuratezza di classificazione**.

#### **7.2. Wine: stesso input, cinque obiettivi diversi**

**WINE** | $N=178$ campioni | input $d=13$ | 3 classi. $X$: $178\times 13$. Stesse convenzioni di Iris.

<!-- SOURCE_VISUAL id="IS18-V39" source="L18-web.pdf" page="61" type="grafico" description="Wine proiettato con PCA, t-SNE, UMAP, PaCMAP e TriMAP" reason="Cinque mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014703.png)

Wine: $N=178$ vini, $d=13$ misure chimiche standardizzate, $C=3$ cultivar. **Stessa distribuzione delle classi in ogni pannello.** **Domanda**: gruppi 2D compatti garantiscono prestazioni predittive migliori? **No.**

> 💡 **Perché qui standardizzare è indispensabile (nota didattica).** Nelle 13 misure del Wine la prolina vale centinaia di unità e altre feature valori sotto 1: senza standardizzazione la PCA sceglierebbe come PC1 quasi soltanto la prolina (è l’«ancoraggio all’unità più grande» del §8.6). Lo scaler stimato su tutto il dataset va bene qui perché si sta solo guardando; in una pipeline predittiva va stimato sul training (§3.13).

#### **7.3. Un sottoinsieme di MNIST: le immagini diventano vettori di feature**

**MNIST** | $N=1000$ campioni | input $d=50$ | 10 classi. $X$: $1000\times 784$. Pixel divisi per 255; **una pre-riduzione PCA(50) condivisa da tutti i 5 metodi** | assi indipendenti; coordinate non confrontabili.

<!-- SOURCE_VISUAL id="IS18-V40" source="L18-web.pdf" page="62" type="grafico" description="1000 immagini MNIST proiettate con cinque metodi dopo PCA(50), con l'8 del laboratorio 1 evidenziato" reason="Cinque mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014716.png)

MNIST: $N=1000$ **immagini reali** (100 per cifra), $28\times 28=784$ pixel → **PCA(50) condivisa** → cinque proiezioni 2D. Stessi campioni, **nessuna etichetta usata nella stima**. **A differenza delle cifre sintetiche 8×8 precedenti, queste sono osservazioni reali scritte a mano.**

> 💡 **Perché PCA(50) prima (nota didattica).** t-SNE e UMAP calcolano distanze tra tutte le coppie di punti; in 784 dimensioni sono lente e dominate dal rumore dei pixel. Una PCA a 50 componenti tiene la gran parte della varianza utile, elimina il rumore e accelera il calcolo: è la combinazione standard «PCA per comprimere, metodo non lineare per visualizzare». Per questo l’intestazione dice «input $d=50$» anche se le immagini hanno 784 pixel.

#### **7.4. EmbeddingGemma 2: un encoder multimodale**

**Testo** (token), **immagine** (pixel), **video** (frame), **audio** (forma d’onda) → **EmbeddingGemma 2**, encoder $f_\theta$ (addestrato) → **embedding** $z\in\mathbb{R}^d$, con $d=768$ valori. $x_{\text{engineered}}=\text{embedding}(x)$.

<!-- SOURCE_VISUAL id="IS18-V41" source="L18-web.pdf" page="63" type="schema" description="Encoder multimodale EmbeddingGemma 2 con quattro modalità d'ingresso e un vettore da 768 valori" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014727.png)

- **Che cosa**: un **encoder pre-addestrato** (circa **740M parametri**) che mappa **media diversi in un unico spazio vettoriale**: **contenuti simili finiscono vicini**.
- **Input**: testo, immagine, video, audio (**un elemento alla volta**).
- **Output**: **un vettore** $z\in\mathbb{R}^d$ per elemento; $d=768$ qui.
- **Nessuna etichetta**: un passo di **estrazione delle feature come la PCA**, ma **appreso su dati enormi**.

Modalità, dimensioni e contesto sono quelli **riportati dal produttore**; l’ingresso immagine e $d=768$ sono stati **verificati localmente** dall’autore (valori da rivedere).

#### **7.5. Da un’immagine MNIST a un punto della mappa**

Immagine $x$ ($28\times 28\to 224\times 224$ RGB) → **EmbeddingGemma 2**, encoder $f_\theta$ → embedding $z\in\mathbb{R}^d$ ($d=768$ valori, mostrati con colori *jet*) → **proiezione** (PCA, t-SNE, UMAP, PaCMAP, TriMAP) → mappa 2D (**2 valori per immagine**).

<!-- SOURCE_VISUAL id="IS18-V42" source="L18-web.pdf" page="64" type="schema" description="Un'immagine MNIST trasformata in un embedding da 768 valori e poi in un punto della mappa t-SNE" reason="Schema e mappa visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014741.png)

- **Encoder (estrazione delle feature)**: ognuna delle $N=1000$ immagini diventa un vettore $z_i\in\mathbb{R}^{768}$; **le etichette non vengono usate**.
- **Proiezione (visualizzazione)**: la matrice $Z$ $1000\times 768$ diventa $1000\times 2$; l’immagine d’esempio (un **8**, cerchiato) è **un punto della mappa**, con $z=(-0{,}05;\ -0{,}05;\ 0{,}05;\ \dots)$ nei suoi primi tre valori.
- **Gli stessi due passi di prima**, con lo $z$ **appreso** al posto della PCA(50) sui pixel.

> 💡 **Perché 224×224 RGB (nota didattica).** Gli encoder di immagini pre-addestrati si aspettano un formato fisso, spesso 224×224 a tre canali (lo stesso di molte CNN, Lezione 21): l’immagine MNIST in scala di grigi viene ingrandita e il suo canale replicato tre volte. L’encoder non «vede» informazione nuova, ma la rappresenta nello spazio che ha imparato su milioni di immagini.

#### **7.6. MNIST: cinque proiezioni degli embedding appresi**

**MNIST IMAGE EMBEDDINGS** | $N=1000$ | vettore dell’encoder $d=768$ | 10 classi. MNIST originale 28×28 → 224×224 RGB → vettore diretto dell’immagine di *embeddinggemma-2* → proiezione 2D. **Le etichette delle cifre non sono state fornite né all’encoder né alle proiezioni**; assi indipendenti; coordinate non confrontabili.

<!-- SOURCE_VISUAL id="IS18-V43" source="L18-web.pdf" page="65" type="grafico" description="Cinque proiezioni 2D degli embedding da 768 valori di 1000 immagini MNIST" reason="Cinque mappe visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014752.png)

**Rappresentazione d’ingresso**: $N=1000$ immagini MNIST reali (100 per cifra). 28×28 in scala di grigi → 224×224 RGB → un **encoder locale di immagini** *embeddinggemma-2* → **vettori a 768 dimensioni**. I cinque metodi di proiezione usano **questi vettori, non i pixel grezzi**; le etichette servono solo a colorare.

#### **7.7. Quale strumento, quando?**

| Metodo | Usa le etichette? | Output | Uso tipico |
|---|---|---|---|
| **filtri, wrapper, embedded** (SFS, SBS, RFE) | dipende dal modello | **sottoinsieme delle feature originali** | tenere feature interpretabili, ridurre il costo |
| **PCA** | no | qualsiasi $k\le d$ combinazioni lineari (**si sceglie $k$**) | compressione, pre-elaborazione, vista 2D |
| **LDA** | sì | al più $\min(d,C-1)$ combinazioni lineari | separazione delle classi |
| **t-SNE** | no | mappa 2D / 3D (**senza `transform()`**) | **solo visualizzazione** |
| **UMAP, PaCMAP, TriMAP** | no | mappa 2D (grafo dei vicini, coppie, terne) | visualizzazione; **confrontare, non fidarsi di una sola mappa** |
| **ICA** | no | sorgenti indipendenti | separazione dei segnali |

**Nota**: $d$ = numero di feature, $C$ = numero di classi. A differenza della PCA, **la LDA non può ridurre a un numero arbitrario di feature**: con 10 classi ne dà al più 9.

#### **7.8. Python: le cinque mappe 2D in poche righe**

```python
from sklearn.decomposition import PCA
from sklearn.manifold import TSNE
import umap, pacmap, trimap      # pip install umap-learn pacmap trimap

Z = {}                           # X: (N, d), nessuna etichetta Y
Z["PCA"]    = PCA(2).fit_transform(X)                                            # lineare: varianza massima
Z["t-SNE"]  = TSNE(2, perplexity=30, init="pca").fit_transform(X)                # vicinati locali
Z["UMAP"]   = umap.UMAP(n_components=2, n_neighbors=15, min_dist=0.1).fit_transform(X)  # grafo
Z["PaCMAP"] = pacmap.PaCMAP(n_components=2).fit_transform(X, init="pca")         # coppie vicine/medie/lontane
Z["TriMAP"] = trimap.TRIMAP(n_dims=2).fit_transform(X)                           # terne: struttura globale
```

**Input**: $X$, $N\times d$ feature standardizzate; **le etichette $Y$ non vengono mai passate**. **Output**: `Z[m]`, **cinque array $N\times 2$**, uno per metodo; le etichette si usano solo per colorare i grafici. **Nota**: è consigliabile lo **stesso `random_state`** per mappe ripetibili; il codice completo sarà pubblicato più avanti sul sito del corso.

> 💡 **I parametri che cambiano la figura (nota didattica).** `perplexity` (t-SNE) e `n_neighbors` (UMAP) fissano quanti vicini contano: valori piccoli frammentano i dati in tanti gruppi, valori grandi mostrano più struttura globale. `min_dist` (UMAP) regola quanto i punti possono ammassarsi. `init="pca"` parte dalla mappa PCA invece che da posizioni casuali: rende i risultati più stabili e conserva meglio la struttura globale. Cambiare questi parametri o il seme e ottenere una mappa diversa è normale: è il motivo della regola «confrontare, non fidarsi di una sola mappa».

---

### **8. Teoria: gli embedding e il «System 1» nell’AI**


#### **8.1. Embedding: feature apprese, poi guardare o decidere**

Immagine $x$ ($8\times 8=64$ valori) → **encoder $f_\theta$** (addestrato) → **embedding** $z$ ($d=128$ valori) → due strade:

- **per guardare**: PCA / t-SNE → **mappa 2D** (2 valori);
- **per decidere**: **classificatore** → etichetta + probabilità.

<!-- SOURCE_VISUAL id="IS18-V44" source="L18-web.pdf" page="69" type="schema" description="Encoder che produce un embedding usato per una mappa 2D o per un classificatore" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014809.png)

- Un **embedding** è un **estrattore di feature appreso dai dati**: $z=f_\theta(x)\in\mathbb{R}^d$. **Input simili finiscono vicini.**
- **Per guardare**: PCA (lineare, ha un `transform`) o t-SNE (vicinati, **solo un’immagine**).
- **Per decidere**: una **piccola «testa»** su $z$. **Embedding + testa è la classica macchina System 1.**
- $x$: input; $f_\theta$: rete con pesi $\theta$; $d$: dimensione dell’embedding. **Esempio**: 64 numeri → 128 → 2 per il grafico.

> 💡 **Un embedding più grande dell’input (nota didattica).** Qui l’embedding ha 128 valori per un’immagine da 64: la dimensione non è il punto. Ciò che conta è che nello spazio di $z$ le immagini della stessa cifra siano vicine e quelle diverse lontane, cioè che una frontiera semplice (lineare) basti a separarle. È la stessa idea delle feature polinomiali della Lezione 20: aumentare le dimensioni per rendere il problema lineare.

#### **8.2. Embedding: la similarità, e che cosa una mappa non dice**

La **vicinanza di due embedding** si misura con la **similarità coseno**:

$$\cos(z_a,z_b)=\frac{z_a\cdot z_b}{\|z_a\|\,\|z_b\|}\in[-1,1].$$

$z_a,z_b\in\mathbb{R}^d$: due embedding; numeratore: **prodotto scalare** (somma di $d$ prodotti); denominatore: **prodotto delle due lunghezze** (norme), quindi **conta solo la direzione**.

**Esempio** ($d=2$): $z_a=(1,2)$, $z_b=(2,1)$: prodotto scalare $=4$, norme $=\sqrt5$ ciascuna, $\cos=4/5=0{,}8$ (**simili**). **Distanza coseno** $=1-\cos\in[0,2]$: 0 stessa direzione, 1 **ortogonali** (per esempio $(1,0,0)$ e $(0,1,0)$: prodotto scalare 0), 2 **opposti**.

**Leggere correttamente una mappa 2D**:

- **PCA**: lineare; **distanze e assi mantengono un significato**; un campione nuovo si può proiettare.
- **t-SNE**: conserva i vicinati; **distanze tra cluster, dimensioni dei cluster e assi non hanno significato**; **non si possono proiettare campioni nuovi**.
- **Gli assi di un embedding** (le $d$ coordinate) **non sono interpretabili uno per uno**.

> 📌 **Regola.** **Usare la mappa per ispezionare** (cluster, outlier, leakage); **decidere con l’embedding stesso o con un classificatore validato, mai dall’immagine.**

> 💡 **Ispezionare il leakage con una mappa (nota didattica).** Se nella mappa degli embedding le immagini di test si sovrappongono quasi esattamente a immagini di training (punti doppi), è un indizio di duplicati tra le partizioni (Lezione 8); se i cluster si dividono per ospedale o per macchina invece che per classe, è un indizio di un confondente (Lezione 7, ospedali A/B). È l’uso più prezioso di una mappa.

#### **8.3. Pensieri lenti e veloci: i due sistemi**

| **SYSTEM 1** veloce | **SYSTEM 2** lento |
|---|---|
| **automatico, senza sforzo** | **deliberato, faticoso** |
| **intuitivo, associativo** | **basato su regole, analitico** |
| **sempre attivo**, nessuna sensazione di controllo | **capacità limitata**, «pigro» |
| **efficiente**, ma **soggetto a bias** | **può controllare System 1**, se se ne prende la briga |
| esempi: $2+2=?$; riconoscere un volto arrabbiato | esempi: $17\times 24=?$; compilare una dichiarazione dei redditi |

<!-- SOURCE_VISUAL id="IS18-V45" source="L18-web.pdf" page="72" type="figura" description="Copertina di Thinking, Fast and Slow di Daniel Kahneman" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014933.png)

**Daniel Kahneman**, premio Nobel per l’Economia (2002): *Thinking, Fast and Slow* (2011); copertina usata con permesso.

> 💡 **Analogia per questa lezione** (sviluppata nelle slide successive): le feature **scelte e provate a mano** (wrapper, RFE) ≈ **System 2**, lento ed esplicito; una **rete addestrata che riconosce a colpo d’occhio** ≈ **System 1**, veloce, con feature apprese.

#### **8.4. La biologia di Kahneman: un ingresso largo, un’uscita stretta**

- I System 1/2 di Kahneman sono **personaggi fittizi**: **due modi di funzionamento**, non due regioni del cervello.
- **System 1**: abilità **innate** (sobbalzo, paura di un serpente) e **apprese** (leggere, la mossa di un maestro di scacchi). **Parallelo, sempre attivo.**
- **System 2**: **seriale, faticoso, a capacità limitata**; può **controllare** System 1, ma spesso **si limita ad approvarlo**.
- **Collo di bottiglia** (Zheng & Meister, 2024): il comportamento procede a **≈ 10 bit/s**, i sensi a **≈ $10^9$ bit/s** (tutto il corpo), rapporto **≈ $10^8$**.

<!-- SOURCE_VISUAL id="IS18-V46" source="L18-web.pdf" page="73" type="schema" description="Imbuto dall'ingresso sensoriale a 10^9 bit/s attraverso System 1 e System 2 fino al comportamento a 10 bit/s" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125014957.png)

> ⚠️ **La teoria del doppio processo è dibattuta** (molti vi vedono un continuo): qui è una **metafora di progetto, non neuroscienza**.

> 💡 **Il parallelo con la riduzione della dimensionalità (nota didattica).** Un ingresso enorme che diventa una decisione minuscola è esattamente ciò che fa una pipeline di ML: 196 608 pixel → un embedding da 768 → una classe tra 10. L’ingresso largo viene compresso (feature, embedding) prima di arrivare alla parte «stretta» e costosa che decide.

#### **8.5. Dal System 1 umano al System 1 dell’AI**

| | **Umano** | **Controparte AI** |
|---|---|---|
| **System 1** | intuizione costruita dall’esperienza; risponde a colpo d’occhio; **non sa spiegare come** | un **modello addestrato** che risponde in **un passo in avanti** (*forward pass*): classificatore, embedding + classificatore, modello decisionale «**System One**» |
| **System 2** | ragionamento lento, passo per passo; può controllare e correggere | **LLM che ragiona token per token**, ricerca, strumenti, un **revisore umano** |
| **Dove sta il costo** | anni di pratica, poi **quasi gratis per decisione** | costo di **addestramento/stima pagato una volta**; **ammortizzato** su milioni di usi veloci |
| **Errore tipico** | **sicuro, coerente, sbagliato** (bias) | **fluente o ben formato, sicuro, sbagliato** (shift, feature spurie) |

> 📌 **Ammortizzazione.** Un passo costoso (addestramento, stima della PCA, selezione delle feature) **compra molti passi economici** (proiettare, classificare). **Veloce = deciso in anticipo**; **ciò che non è deciso in anticipo si paga al momento dell’uso.**

#### **8.6. Le nostre tecniche sull’asse veloce–lento**

| Tecnica | Costo pagato una volta (progettazione) | Costo per campione nuovo (uso) | Campione nuovo? |
|---|---|---|---|
| **Filtri** | statistiche di ogni feature, una volta | leggere le $k$ feature tenute | sì |
| **PCA** | stima: $O(Nd^2+d^3)$ | proiezione: $O(dk)$ | sì |
| **t-SNE** | nessuno, ma iterativa | **nessun `transform()`**: ricalcolare la mappa ($\approx O(N\log N)$ per iterazione) | **no** |
| **Wrapper (SFS)** | $d(d+1)/2$ addestramenti × $K$ fold per ordinare tutte le $d$ feature | modello su $k$ feature | sì |
| **CNN addestrata** | **addestramento pesante** | **un passo in avanti** | sì |

$N$ = numero di campioni; $d$ = numero di feature originali; $k$ = numero di feature/componenti tenute; $K$ = fold della validazione incrociata. **Esempio**: $d=64$ pixel, $k=10$: la PCA proietta un’immagine nuova con $64\times 10=640$ moltiplicazioni-somme; la SFS su tutte le 64 feature richiede $64\cdot 65/2=2080$ addestramenti per fold.

> 📌 **Filtri, PCA e reti addestrate sono simili al System 1 al momento dell’uso; wrapper e t-SNE sono simili al System 2: lenti, espliciti, ripetuti.**

> 💡 **Da dove vengono i costi della PCA (nota didattica).** Formare $C=\frac{1}{N-1}X_c^\top X_c$ costa $O(Nd^2)$ (prodotti tra le $d$ colonne su $N$ righe); la decomposizione in autovalori di una matrice $d\times d$ costa $O(d^3)$. La proiezione di un punto è un prodotto vettore-matrice $d\times k$: $O(dk)$.

#### **8.7. I bias umani e i loro analoghi nella nostra pipeline**

| Bias di Kahneman | Analogo nel lavoro sulle feature | Contromisura |
|---|---|---|
| **What you see is all there is** (ciò che si vede è tutto ciò che c’è) | un sottoinsieme selezionato **nasconde le feature scartate** che contavano **insieme** | **wrapper con validazione incrociata**, controllare l’insieme scartato |
| **Sostituzione** (rispondere a una domanda più facile) | scegliere le feature **per correlazione invece che per la metrica del compito**; leggere la **varianza come rilevanza** | valutare sulla **metrica reale del compito**; ricordare che **la PCA è non supervisionata** |
| **Ancoraggio** | un’immagine t-SNE **dipende da perplexity e seme**; feature non scalate **ancorano la PCA all’unità più grande** | **prima scalare**; confrontare **più perplexity e più semi** |
| **Eccesso di fiducia** | la probabilità di un classificatore **letta come certezza dopo uno shift dei dati** | **calibrazione, soglie di astensione** (slide successive) |

> 📌 **Un sistema veloce è eccellente dentro la distribuzione su cui è stato addestrato. È il System 2 (validazione, dati tenuti da parte, una persona) che si accorge quando se ne è usciti.**

#### **8.8. Come funzionano i modelli System One: il quadro complessivo**

**Stato** (testo, JSON, immagine) + **domande tipizzate** (Noul, Choice, Score) → **modello System One** (le domande vengono valutate **in parallelo, sullo stesso stato**) → **risposte tipizzate** (Noul: probabilità $p$; Choice: opzione e probabilità; Score: livello e probabilità) → **il proprio codice** (ramificare, ordinare, instradare).

<!-- SOURCE_VISUAL id="IS18-V47" source="L18-web.pdf" page="77" type="schema" description="Stato e domande tipizzate valutate in parallelo da un modello System One, con risposte tipizzate consumate dal codice" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015013.png)

Si legge da sinistra a destra: **una richiesta porta lo stato e le domande**; ogni domanda è valutata **in parallelo** sullo stesso stato; **una risposta restituisce risposte tipizzate**; **il proprio codice, non il modello, agisce** su di esse. Choice e Score danno anche una confidenza; un Noul è una singola probabilità. Schema ispirato a zbrain.ai/system-one-models (ridisegnato).

#### **8.9. La struttura di un modello System One**

**Stato** $x$ (testo, JSON, immagine) + **domanda tipizzata** $q$ → **modello System One** (**un solo passo in avanti parallelo**: valuta tutte le opzioni insieme) → $p_1,\dots,p_K$ → **valore tipizzato + probabilità**:

$$p_c=\frac{e^{z_c}}{\sum_{j=1}^{K}e^{z_j}},$$

con $z_c$ il **punteggio (logit) dell’opzione $c$**, $K$ il numero di opzioni ($\le 255$ per Choice), $p_c$ la probabilità, $\sum_cp_c=1$. **Esempio**: $z=(2{,}0;\ 0{,}5;\ -1{,}0)\Rightarrow p=(0{,}79;\ 0{,}17;\ 0{,}04)$.

<!-- SOURCE_VISUAL id="IS18-V48" source="L18-web.pdf" page="78" type="schema" description="Un passo in avanti del modello System One contro T passi sequenziali di un LLM che genera testo libero" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015028.png)

**Stesso input**, a confronto: un **LLM** genera **testo libero** con **$T$ token = $T$ passi sequenziali**. **Forme**: in ingresso $\le N$ token; in uscita $K$ numeri (Choice), 1 (Noul), $L$ livelli (Score).

> 💡 **Il conto della softmax (nota didattica).** $e^{2}=7{,}389$, $e^{0{,}5}=1{,}649$, $e^{-1}=0{,}368$, somma $9{,}406$: $p=(0{,}786;\ 0{,}175;\ 0{,}039)$. È la stessa softmax dello strato d’uscita di una CNN (Lezione 21) e dell’attenzione (Lezione 13). Il vantaggio di velocità sta nel numero di passi: un LLM che scrive «Sì, perché…» in 50 token fa 50 passi sequenziali; un modello System One fa un passo solo e restituisce la distribuzione.

#### **8.10. Tre primitive: Choice, Score, Noul**

| Primitiva | Domanda illustrativa | Uscita | Lettura |
|---|---|---|---|
| **Choice** | «Instrada questa e-mail: fatturazione / tecnico / vendite / altro» | $p=(0{,}07;\ 0{,}88;\ 0{,}03;\ 0{,}02)$ | argmax = **tecnico**, $p=0{,}88$ |
| **Score** | «Urgenza di questo ticket, 1–5?» | $p=(0;\ 0{,}1;\ 0{,}2;\ 0{,}5;\ 0{,}2)$ | valore atteso $=2(0{,}1)+3(0{,}2)+4(0{,}5)+5(0{,}2)=3{,}8$ |
| **Noul** (sì/no) | «La foto mostra un pacco danneggiato?» (immagine) | $p(\text{sì})=0{,}93$ | agire se $\max(p,1-p)\ge\tau$ |

Gli esempi sono **illustrativi**, non l’uscita di un prodotto specifico. **Choice**: fino a 255 opzioni; **Score**: 2–10 livelli ordinali; **Noul**: una probabilità sì/no (OpenAI la chiama *predicate*, il Vercel SDK «*Boolean*»). **Ogni risposta è una distribuzione, mai una frase.**

> 📌 **Le tre primitive si compongono**: un **gate Noul** decide **se**, una **Choice** decide **dove**, uno **Score** decide **quanto**.

> 💡 **Valore atteso o livello più probabile? (nota didattica).** Nello Score il livello più probabile è 4 (probabilità 0,5), il valore atteso è 3,8. Il primo è adatto per una decisione discreta («urgenza 4»), il secondo per ordinare i ticket in modo continuo: due ticket con lo stesso livello modale ma valori attesi 3,8 e 4,3 non sono equivalenti.

#### **8.11. Che cosa fa un Noul**

> 📌 **Definizione.** Un **Noul** è una **domanda tipizzata sì/no** la cui risposta è **una probabilità**:
> $$\text{Noul}(x,q)=p=P(q\text{ è vera}\mid x)\in[0,1],$$
> dove $x$ è lo stato (testo, JSON, immagine) e $q$ un’affermazione su di esso. **Tipo d’uscita**: un singolo numero reale (la probabilità stimata del «sì»); **non un’etichetta, non una frase**.

- **L’uscita è la probabilità stimata del Sì.** La **confidenza della risposta selezionata** è $\max(p,1-p)$ ($p=0{,}03$: 97% sul No). **La calibrazione va verificata su dati rappresentativi.**
- **Agire agli estremi.** Due soglie: agire se $p>0{,}9$ o $p<0{,}1$; **mandare il centro a un fallback o a una persona**.
- **Come un `if`.** Noul ≈ `if`, Choice ≈ `match`, Score ≈ una **chiave di ordinamento pesata**.

<!-- SOURCE_VISUAL id="IS18-V49" source="L18-web.pdf" page="80" type="grafico" description="Asse della probabilità da 0 a 1 con le zone sì, no e incerto" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015044.png)

**Esempio**: $x$ = e-mail, $q$ = «chiede un rimborso?» $\Rightarrow p=0{,}97$. «**Calibrato» è un obiettivo di progetto, non una garanzia sui propri dati** (vedi la slide sulla calibrazione).

#### **8.12. Il Noul in pratica: una probabilità e due soglie**

**Domanda**: «la richiesta di risarcimento è fraudolenta?» **Risposta del modello**: $p(\text{sì})=0{,}93$. Con le soglie $\tau_{lo}=0{,}1$ e $\tau_{hi}=0{,}9$: sotto $\tau_{lo}$ → **no**, sopra $\tau_{hi}$ → **sì**, in mezzo → **escalation**. **Le soglie sono una politica**: le decide **il costo di un’azione sbagliata**, non il modello.

<!-- SOURCE_VISUAL id="IS18-V50" source="L18-web.pdf" page="81" type="grafico" description="Probabilità 0,93 sull'asse con le soglie 0,1 e 0,9 e la zona di escalation" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015100.png)

**Supporto delle tre primitive nei modelli dei produttori** (lettura della tabella dal testo estratto):

| Modello (produttore) | Noul | Choice | Score |
|---|:---:|:---:|:---:|
| Jev (TypeSafe) | ✓ | ✓ | ✓ |
| Decisions (OpenAI) | ✓ (a) | ✓ | ✓ |
| Decision-1 (Microsoft) | ✓ | n/s | n/s |
| pplx-decider (Perplexity) | ✓ | n/s | n/s |
| Clef (Cloudflare) | ✓ | n/s | n/s |
| Decider (AWS Strands) | ✓ | ✓ | ✓ |

(a) chiamato «*predicate*». n/s: non dichiarato nelle fonti. **Come riportato il 27 ottobre 2026 da fonti secondarie; da verificare sulle pagine dei produttori.**

#### **8.13. Perché veloce? Tempo e costo per decisione**

| Sistema | Tempo per compito (s) | Costo per 1M di compiti ($) |
|---|---:|---:|
| **Jev 1.13** (modello decisionale) | **0,13** | **42** |
| GPT-6 Luna (Low) (LLM) | 1,50 | 131 |
| Claude Haiku 5.5 (Low) (LLM) | 1,40 | 184 |

<!-- SOURCE_VISUAL id="IS18-V51" source="L18-web.pdf" page="82" type="grafico" description="Barre di tempo per compito e costo per milione di compiti per un modello decisionale e due LLM" reason="Grafico a barre visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015139.png)

**Misura indipendente** (Artificial Analysis): Jev 1.13 è circa $1{,}4/0{,}13\approx 11\times$ **più veloce** e $184/42\approx 4{,}4\times$ **più economico** di Claude Haiku 5.5 (Low), con un **indice di 58 contro 61**.

**Latenze riportate dai produttori**: d1-3B 8 ms (RTX 4090); Clef-flash 39 ms (mediana); Microsoft 85 ms; OpenAI 100–180 ms; Jev 70–500 ms. **Non confrontabili**: hardware, prompt e reti diversi.

> 💡 **Il compromesso in numeri (nota didattica).** Il modello decisionale perde 3 punti di indice (58 contro 61) e guadagna un ordine di grandezza in latenza: per un agente che prende migliaia di micro-decisioni (instradare, autorizzare uno strumento, filtrare) la latenza si somma lungo la catena, ed è quella a dominare. Per una sola risposta complessa conta il contrario.

#### **8.14. «Nessuna allucinazione» non significa «corretto»**

- Un modello System One **può emettere solo una delle opzioni ammesse**: la sua uscita è **sempre ben formata**. È una **proprietà del formato**.
- **Non rende vera l’opzione.** Una risposta ben formata e sbagliata **resta sbagliata**, e arriva **con una probabilità sicura di sé**.
- **Test indipendente** (Artificial Analysis, domande di conoscenza difficili, 5 opzioni compreso «non lo so»): i modelli decisionali **rispondono quando dovrebbero astenersi**; un modello ottiene **41 in accuratezza ma solo 2 nel tasso di non-allucinazione**. **Le probabilità dichiarate erano più alte dell’accuratezza reale.**
- **La lezione di Kahneman è la stessa**: la **sensazione di fiducia è prodotta dalla coerenza, non dalle evidenze**. Un sistema veloce è sicuro perché **la storia torna**.

> 📌 **Regola: uscita valida ≠ uscita corretta ≠ probabilità calibrata.** Tre proprietà diverse, **da testare separatamente**.

#### **8.15. Calibrazione: un 90% di confidenza è giusto il 90% delle volte?**

**Diagramma di affidabilità** (540 immagini di test): sull’asse orizzontale la **confidenza dichiarata** (media per intervallo), su quello verticale l’**accuratezza osservata** (per intervallo). **In distribuzione**: accuratezza 98%, **ECE = 0,008**; **dopo uno shift**: accuratezza 40%, **ECE = 0,466**. A destra: la **percentuale di casi accettati** al variare della **soglia di confidenza $\tau$**.

![](imgs/Pasted%20image%2020261125015208.png)


<!-- SOURCE_VISUAL id="IS18-V52" source="L18-web.pdf" page="84" type="grafico" description="Diagramma di affidabilità in distribuzione e dopo lo shift, curva dei casi accettati in funzione della soglia e due esempi di 8 originale e spostato" reason="Grafici e immagini visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015216.png)

**Shift**: i dati incontrati nell’uso **differiscono dai dati di addestramento**. Qui: le **stesse 540 cifre di test**, **spostate di 1 pixel più rumore aggiuntivo**; **nessun riaddestramento**. In distribuzione: accuratezza 98%, ECE 0,008. **Dopo lo shift: confidenza 86%, accuratezza 40%, ECE 0,47**; **anche $\tau=0{,}9$ lascia passare il 48%** dei casi. L’**8** dell’esempio (previsto 8 con confidenza 1,00, poi previsto 8 con confidenza 0,93 dopo lo shift) è ancora letto correttamente, **ma molti altri no, con confidenza simile**.

$$\text{ECE}=\sum_{b=1}^{B}\frac{n_b}{N}\,\big|\text{acc}_b-\text{conf}_b\big|,$$

con $B$ intervalli di confidenza; $n_b$ campioni nell’intervallo $b$; $N$ totale; $\text{acc}_b$ accuratezza osservata, $\text{conf}_b$ confidenza media. **Esempio**: un intervallo con confidenza 0,9, accuratezza 0,7, $n_b=100$ su $N=540$: contributo $0{,}185\times 0{,}2=0{,}037$.

> 💡 **Leggere i numeri (nota didattica).** $100/540=0{,}185$ e $|0{,}7-0{,}9|=0{,}2$. L’ECE è una media pesata della distanza del diagramma dalla diagonale: 0 = calibrazione perfetta. Dopo lo shift il modello dice «86% di confidenza» e ha ragione il 40% delle volte: lo scarto medio di 46 punti è proprio l’ECE di 0,466. Il risultato più importante è l’ultimo: una soglia alta **non protegge** dallo shift, perché il modello è sicuro anche quando sbaglia. Serve un controllo del drift (Lezione 11) o un System 2.

#### **8.16. La cascata: System 1 propone, System 2 valida**

Caso $x$ → **System 1** (veloce, economico): etichetta e $p_{sel}$ → $p_{sel}\ge\tau$? **Sì** → agire; **no** → **System 2 / persona** (lento, accurato) → agire (controllato).

<!-- SOURCE_VISUAL id="IS18-V53" source="L18-web.pdf" page="85" type="schema" description="Cascata con System 1, soglia sulla confidenza ed escalation a System 2 o a una persona" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015223.png)

Qui $p_{sel}$ è la **confidenza della risposta selezionata** ($=\max(p,1-p)$ per un Noul). **Escalation quando**

$$(1-p_{sel})\,C_{err}>C_2,$$

con $C_{err}$ il **costo di un’azione sbagliata** e $C_2$ il **costo del controllo lento**. **Esempio**: $C_{err}=100$, $C_2=5$: escalation se $1-p_{sel}>0{,}05$, cioè $p_{sel}<0{,}95$.

Il **test di Cloudflare** sulle richieste di risarcimento assicurativo (**dichiarato dal produttore**): **19,3 s contro 61,7 s** e **6032 $ contro 11 740 $ per milione di richieste** per la cascata contro il solo sistema lento.

> 💡 **Da dove viene la regola (nota didattica).** Se si agisce subito, l’errore atteso costa $(1-p_{sel})C_{err}$ (probabilità di sbagliare per il costo dell’errore); se si fa l’escalation, si paga $C_2$ (assumendo che il controllo lento non sbagli). Conviene l’escalation quando il primo costo supera il secondo: la soglia $\tau=1-C_2/C_{err}$ si ricava dai **costi**, non dal modello. Con errori più costosi ($C_{err}=1000$) la soglia sale a 0,995. Attenzione: la regola presuppone che $p_{sel}$ sia **calibrata**; dopo uno shift (§8.15) la cascata lascia passare errori sicuri di sé.

#### **8.17. Uno strato decisionale per l’azienda**

**Il codice calcola, il System One giudica, il System Two ragiona.**

Evento / stato (ticket, richiesta di risarcimento, sensore, immagine) → **piano decisionale (System One)**: classificare, assegnare un punteggio, instradare, fare da gate → se $p$ è alta → **piano di esecuzione** (workflow, API, transazioni, policy) → azione, log di audit; se $p$ è bassa o la domanda è aperta → **piano del ragionamento (System Two)**: pianificare, sintetizzare, generare.

<!-- SOURCE_VISUAL id="IS18-V54" source="L18-web.pdf" page="86" type="schema" description="Strato decisionale aziendale con piano decisionale, piano del ragionamento e piano di esecuzione" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125015234.png)

**Densità decisionale**: le **decisioni semantiche necessarie per risultato di business**; è **alta per gli agenti**, quindi un modello lento per ogni decisione è costoso. **Concetto del lato produttori, non uno standard accademico.**

#### **8.18. È un lavoro da System One? Sei domande**

1. La decisione è **frequente**?
2. Lo **spazio delle risposte è limitato**?
3. Lo **stato d’ingresso è conoscibile in anticipo**?
4. Il **risultato è misurabile**?
5. L’uscita **controlla un’azione successiva chiara**?
6. Si può **governare**: soglie, permessi, audit, fallback, controllo umano?

**Per lo più sì ⇒ candidato.** Uscita aperta ⇒ **LLM**. Regole esatte ⇒ **codice semplice**.

**Costo per decisione governata**:

$$\bar{c}=c_1+f_2\,c_2+f_e\,C_{err},$$

con $c_1$ il costo del System 1 per decisione; $f_2$ la frazione inviata in escalation; $c_2$ il costo del controllo lento; $f_e$ la frazione di azioni sbagliate; $C_{err}$ il costo di un errore. **Esempio illustrativo**: $c_1=0{,}001$, $f_2=0{,}06$, $c_2=0{,}05$, $f_e=0{,}01$, $C_{err}=20$: $\bar{c}=0{,}001+0{,}003+0{,}2=0{,}204$. **Dominano gli errori, non i token**: la metrica è il **costo per decisione, non per token**.

> 💡 **Lettura (nota didattica).** $f_2c_2=0{,}06\cdot 0{,}05=0{,}003$ e $f_eC_{err}=0{,}01\cdot 20=0{,}2$: il 98% del costo viene dall’1% di errori. Abbassare $c_1$ di dieci volte (un modello ancora più economico) ridurrebbe $\bar{c}$ di meno dello 0,5%; dimezzare gli errori lo ridurrebbe di quasi il 50%. È la stessa conclusione della cascata: investire in accuratezza e calibrazione rende più che risparmiare sul calcolo.

#### **8.19. Applicazioni e limiti dei modelli System One**

| **Dove si adattano** | **Dove no** |
|---|---|
| **instradamento**: ticket, e-mail, richieste di manutenzione | **nessuna spiegazione del perché** |
| **gate e guardrail**: «l’agente può chiamare questo strumento?» | **deboli in aritmetica, conteggio, date** ⇒ usare il codice |
| **punteggio e ranking**: urgenza, rilevanza, rischio | l’**input è un dato**, ma le **difese contro l’injection non sono garantite** |
| **controlli visivi**: pacco danneggiato, pezzo mancante (modelli con immagini) | l’**accuratezza crolla fuori dalla distribuzione** di addestramento (slide precedenti) |
| **supply chain**: classificare un avviso di ritardo, segnalare un’anomalia di un fornitore | **benchmark per lo più dichiarati dai produttori**; alcuni modelli chiusi e ospitati |

> ⚠️ **Che cosa manca per una valutazione solida**: le **proprie decisioni etichettate**, eseguite in **modalità ombra** (*shadow mode*: il modello decide in parallelo al processo attuale senza agire, e si confrontano le decisioni); la **calibrazione sui propri dati**; la **latenza misurata nella propria rete**; **benchmark indipendenti** oltre al primo disponibile.

> 💡 **Il gate contro la prompt injection (nota didattica).** Un Noul «l’agente può chiamare questo strumento?» è un guardrail utile (Lezione 15, §3.12), ma il suo input è proprio il contenuto che un attaccante controlla: un testo ostile può spingere anche un classificatore a dire «sì». Il gate riduce il rischio, non sostituisce i permessi minimi e il controllo umano sulle azioni irreversibili.

#### **8.20. I prodotti a fine ottobre 2026 (OUTLOOK)**

| Modello | Uscita | Input | Accesso | $ / 1M token d’ingresso | Latenza (produttore) |
|---|---|---|---|---:|---|
| **Jev 1.13/1.14**, TypeSafe | 15 set (1.14: 14 ott) | testo; immagini in anteprima 1.14 | chiuso, ospitato | 0,042 | 70–500 ms |
| **OpenAI Decisions** (gpt-6-luna) | beta 6 ott, tutti gli utenti 22 ott | testo, immagini | chiuso, ospitato | 0,10 | 100–180 ms |
| **Microsoft-Decision-1** | 9 ott | testo (32K) | chiuso, anteprima Foundry | 0,042 | 85 ms (mediana) |
| **Liquid d1-ultra / d1-3B** | 15 ott | testo, immagini | ultra ospitato; 3B aperto | 0,042 (ultra) | 3B: 8 ms (RTX 4090) |
| **pplx-decider v1.2**, Perplexity | 15 ott | testo, immagini | aperto, Apache-2.0 | 0,02 (27B), 0,01 (flash) | n/s |
| **Clef / flash / omni**, Cloudflare | 1 ott (omni 9 ott) | testo, immagini; omni + audio, video | aperto, Apache-2.0 | 0,24 / 0,038 (Workers AI) | flash 39 ms; Clef 209 ms |
| **Decider 2B**, AWS Strands Labs | 1 ott | testo | aperto, self-hosted | — | 280 ms (M3 Pro) |

27 ottobre 2026, fonti secondarie e dichiarazioni dei produttori (n/s: non dichiarato); **non confrontabili, in rapido cambiamento**. Indice Artificial Analysis: Jev 1.13 = 58, Microsoft-Decision-1 = 54. **Verificare sulle pagine dei produttori.**

##### **8.20.1. Aggiornamento dei modelli decisionali (20 novembre 2026)**

La rassegna del materiale fotografa prodotti e versioni di fine ottobre 2026. Alcuni riferimenti sono già superati: OpenAI ha portato la Decisions API in disponibilità generale il 22 ottobre e indica `gpt-6-luna` come modello supportato; Perplexity ha annunciato la famiglia `pplx-decider` 1.3 il 12 novembre; Cloudflare ha aggiunto Clef-omni con input audio e video il 9 ottobre. TypeSafe ha pubblicato Jev 1.14 il 14 ottobre. Microsoft documenta Microsoft-Decision-1 nel catalogo Foundry, mentre AWS descrive Strands Decider 2B come modello sperimentale open source. I numeri di latenza, prezzo e benchmark della tabella restano quelli dichiarati nelle fonti e nelle condizioni dell’istantanea, non risultati direttamente comparabili. ([OpenAI](https://developers.openai.com/api/docs/guides/decisions), [Perplexity](https://community.perplexity.ai/c/announcements/9), [Cloudflare](https://developers.cloudflare.com/changelog/post/2026-10-09-clef-omni-workers-ai/), [TypeSafe](https://typesafe.ai/blog/jev-1-14), [Microsoft](https://learn.microsoft.com/en-us/azure/foundry/foundry-models/how-to/use-foundry-models-microsoft-decision), [AWS](https://aws.amazon.com/blogs/machine-learning/cut-generative-ai-costs-code-for-rules-models-for-judgment/))

---

### **9. Punti principali**


> ✅ **Punti principali della lezione.**
> - Le **feature irrilevanti o ridondanti danneggiano i modelli**. La **selezione** tiene un sottoinsieme delle feature originali; l’**estrazione** ne costruisce di nuove.
> - I **filtri** ordinano con statistiche (robusti, possono perdere feature utili); i **wrapper** cercano sottoinsiemi con modello e CV (accurati, soggetti a overfitting); i **metodi embedded** selezionano durante l’addestramento.
> - **SFS e SBS sono ricerche greedy**: provano circa $d(d+1)/2$ sottoinsiemi invece dei $2^d-1$ possibili.
> - **PCA**: non supervisionata; **direzioni ortogonali di varianza massima = autovettori della matrice di covarianza**.
> - La **LDA usa le etichette**; la **t-SNE** (come UMAP, PaCMAP, TriMAP) serve a **guardare i dati**: **colori separati non dimostrano accuratezza**; l’**ICA separa le sorgenti**.
> - **In pratica**: scalare; **stimare PCA e ogni selezione solo sul training set**.
> - **System 1 decide veloce, System 2 valida**: **valido ≠ corretto ≠ calibrato**; soglia, poi escalation.

---

### **10. Appendice: l’algoritmo della PCA**


#### **10.1. La matrice di covarianza campionaria**

Date le righe $x_i^\top$ di $X\in\mathbb{R}^{N\times d}$, si calcola la matrice di covarianza $C$:

$$C=\frac{1}{N-1}\sum_{i=1}^{N}(x_i-\bar{x})(x_i-\bar{x})^\top,\qquad \bar{x}=\frac1N\sum_{i=1}^{N}x_i.$$

- $N$ = numero di dati; $x_i\in\mathbb{R}^d$ = $i$-esimo dato ($d$ feature); $\bar{x}$ = la loro media (centro di massa); $C\in\mathbb{R}^{d\times d}$; $^\top$ = trasposta.
- **I vettori della base PCA sono gli autovettori di $C$.** Un autovalore più grande ⇒ un autovettore **più importante**.
- $Z=\text{PCA}(X,k)$ — input: $N\times d$ numeri; output: $N\times k$ numeri (per esempio $k=3$).

#### **10.2. I passi principali dell’algoritmo**

**Algoritmo PCA$(X,k)$**: restituisce i dati ridotti $Z$ ($X$ = matrice $N\times d$ dei dati; ogni dato $i$ = riga $i$, $i=1..N$).

1. $X_c\leftarrow$ sottrarre la media $\bar{x}$ da ogni riga di $X$;
2. $C\leftarrow\frac{1}{N-1}X_c^\top X_c$ (matrice di covarianza, $d\times d$);
3. $\{\lambda_j,u_j\}_{j=1..d}$ = autovalori/autovettori di $C$, con $\lambda_1\ge\lambda_2\ge\dots\ge\lambda_d$;
4. $W\leftarrow[u_1\ \cdots\ u_k]$ (le prime $k$ direzioni principali, $d\times k$);
5. restituire $Z=X_cW$ ($N\times k$).

Gli stessi passi della parte principale, scritti come algoritmo. Il fattore $\frac{1}{N-1}$ **riscala solo $C$**: gli autovettori non cambiano.

> 💡 **Le due forme di $C$ coincidono (nota didattica).** $X_c^\top X_c=\sum_i(x_i-\bar{x})(x_i-\bar{x})^\top$: il prodotto della matrice trasposta per sé stessa è la somma dei prodotti esterni delle sue righe. L’elemento $(i,l)$ di $C$ è la covarianza campionaria tra le feature $i$ e $l$; sulla diagonale ci sono le varianze.

#### **10.3. Pesi e varianza di una componente**

Ogni colonna di $W$ ha **lunghezza unitaria**: i pesi dicono solo **quanto di ogni feature entra nella componente**. La varianza della nuova variabile è

$$\text{Var}(\text{PC}_j)=u_j^\top C\,u_j=\lambda_j=\sum_{i,l}w_{ij}\,w_{lj}\,C_{il}.$$

- $w_{ij}$ = peso della feature $i$ nella componente $j$ (elemento $i$ di $u_j$); $C_{il}$ = covarianza delle feature $i$ e $l$ (elemento di $C$).
- **Pesi grandi su feature che variano molto e si muovono insieme danno un $\lambda_j$ grande.** La PC1 è la scelta di pesi di lunghezza unitaria che **rende questa somma la più grande possibile**; la PC2 è la migliore **ortogonale** ad essa.
- **Esempio (3 geni)**: $\text{PC1}=0{,}64\,g_1+0{,}59\,g_2+0{,}49\,g_3$, con $0{,}64^2+0{,}59^2+0{,}49^2=1$.

> 💡 **Il passaggio di Lagrange (nota didattica).** Si massimizza $u^\top Cu$ con il vincolo $u^\top u=1$: la lagrangiana $L=u^\top Cu-\lambda(u^\top u-1)$ ha gradiente $2Cu-2\lambda u$, che si annulla per $Cu=\lambda u$. Moltiplicando a sinistra per $u^\top$ si ottiene $u^\top Cu=\lambda u^\top u=\lambda$: la varianza lungo un autovettore è il suo autovalore, e il massimo è $\lambda_1$.

---

### **11. Approfondimento — il notebook Colab e l’edizione precedente**


Il notebook della cartella, `Lesson_18_Laboratory_COLAB_DimensionalityReduction.ipynb` («*Dimensionality Reduction via PCA, t-SNE*»), contiene la versione precedente dei laboratori 1–3 sul **vero** `load_digits`. Le differenze rispetto alle slide 2026/27:

- **RFE**: stessa chiamata, `RFE(estimator=svc, n_features_to_select=1, step=1)`, con il ranking disegnato con `matshow`.
- **t-SNE**: `TSNE(n_components=2, verbose=1, perplexity=40, n_iter=300)`. Il log salvato riporta «*KL divergence after 250 iterations with early exaggeration: 61.485268*» e «*KL divergence after 300 iterations: 0.960238*».
- **Colori**: `plt.cm.get_cmap('jet', 10)` per dieci classi.

> ⚠️ **Parametri e funzioni datati (nota didattica).** 1) `n_iter` è stato rinominato `max_iter` in scikit-learn 1.5 e poi rimosso: con le versioni attuali il notebook va aggiornato, come fa la slide del §6.1. 2) Con 300 iterazioni (di cui 250 di *early exaggeration*, la fase iniziale in cui le attrazioni sono amplificate per formare i gruppi) restano solo 50 iterazioni di ottimizzazione vera: la KL finale di 0,96 è più alta dello 0,71 ottenuto con 1000 iterazioni, e la mappa è meno rifinita. 3) Il valore 61,49 durante l’early exaggeration non è confrontabile con quello finale, perché in quella fase le $p_{ij}$ sono moltiplicate per un fattore. 4) `plt.cm.get_cmap` è deprecata in Matplotlib recente: si usa `matplotlib.colormaps['jet'].resampled(10)` oppure direttamente `cmap='tab10'`, come nelle slide (una mappa categoriale è anche più adatta di *jet* per classi senza ordine, Lezione 11).

Il PDF dell’edizione precedente (`Lesson_18_FeatureEngineering…CodingExamples.pdf`) presentava gli stessi contenuti di selezione, PCA, LDA, t-SNE e ICA, senza la parte sugli embedding e sui modelli System One.

---

### **12. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **selezione** / **estrazione** | sottoinsieme delle feature originali, significato fisico conservato / feature nuove che mescolano le originali |
| **feature irrilevante** / **ridondante** | nessuna informazione sull’uscita / ripete un’altra feature |
| **filtro** / **wrapper** / **embedded** | statistiche senza modello / modello + CV per ogni sottoinsieme / selezione durante l’addestramento |
| **SFS** / **SBS** | parte dal vuoto e aggiunge / parte da tutte e toglie |
| **ricerca esaustiva** / **greedy** | $2^d-1$ sottoinsiemi / circa $d(d+1)/2$, senza ritorni |
| **RFE per disegnare** / **RFE per valutare** | su tutti i dati / dentro ogni fold |
| **PCA** / **LDA** | varianza massima, senza etichette, $k\le d$ / separazione massima, con etichette, $\le\min(d,C-1)$ |
| **PCA** / **ICA** | componenti scorrelate ordinate per varianza / componenti indipendenti, ordine e scala arbitrari |
| **PCA** / **t-SNE** | lineare, con `transform()`, distanze significative / non lineare, senza `transform()`, solo vicinati |
| **gomito** / **bersaglio di varianza** | numero di componenti strutturali / numero per ricostruire, compreso il rumore |
| **varianza** / **rilevanza** | quanto varia / quanto informa sull’uscita |
| **separazione visiva** / **accuratezza** | colori distinti in una mappa / prestazioni validate su dati nuovi |
| **similarità coseno** / **distanza coseno** | in $[-1,1]$, 1 = stessa direzione / $1-\cos$, in $[0,2]$, 0 = stessa direzione |
| **embedding per guardare** / **per decidere** | proiezione 2D / classificatore validato sul vettore completo |
| **System 1** / **System 2** | un passo, costo ammortizzato, errori sicuri di sé / passi multipli, lento, controlla |
| **Choice** / **Score** / **Noul** | dove (opzione) / quanto (livello ordinale) / se (probabilità del sì) |
| **uscita valida** / **corretta** / **calibrata** | formato ammesso / risposta vera / probabilità uguale alla frequenza di successo |
| **soglia del modello** / **soglia di policy** | non esiste / ricavata dai costi $C_{err}$ e $C_2$ |
| **costo per token** / **costo per decisione** | spesa di calcolo / include escalation ed errori, dominata dagli errori |
