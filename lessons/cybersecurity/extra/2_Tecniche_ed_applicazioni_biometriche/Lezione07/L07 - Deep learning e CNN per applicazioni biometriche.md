# **Lezione 7: Deep learning e CNN per applicazioni biometriche**

---

### **0. Panoramica della lezione**

_(slide pp. 1–4)_

La Lezione 7 spiega **come sono fatte**, **come si configurano** e **come si allenano** le **reti neurali convoluzionali** (CNN), e introduce autoencoder e fine tuning. Outline:

1. deep learning contro reti neurali classiche;
2. **CNN**: configurazione, allenamento, tuning;
3. **autoencoder** e altri modelli deep;
4. esempi di applicazione.

La nota della slide p. 3 precisa che queste lezioni danno le **basi** per capire e configurare i modelli deep; la loro **vera applicazione** alla biometria sarà presentata nelle lezioni successive. Alla lezione è associata una **dispensa**: il survey *Deep Learning for Biometrics* di Sundararajan e Woodard, ricostruito nella Parte C.

> 📌 **Tre concetti fondamentali del ML per la biometria (p. 4).**
> 1. **$E_{train} \rightarrow 0$**: i modelli di ML, e specialmente le reti deep, possono apprendere **in modo esatto** i dati di allenamento, con errore nullo sul training set.
> 2. **Cross-validation**: per questo si crea **sempre** un insieme di **training** e uno di **validazione** (spesso chiamato *test set*). **Nessun dato di validazione deve essere duplicato nel training.**
> 3. **LNPO**: nei sistemi biometrici la semplice cross-validation **non basta**; si usa il **Leave N People Out** (Lezione 5, §9).

---

## **Parte A — Le reti neurali convoluzionali**

### **1. Come useremo le CNN nel corso**

_(slide pp. 5–6, 10)_

Il percorso è in due passi:

1. **capire** come sono fatte le CNN e come si allenano, per intero o in alcune parti;
2. **adattarle** a nuovi problemi singoli, per esempio la **face detection**.

Si useranno **modelli già addestrati** scaricabili e modelli specifici di **librerie pubbliche**. L'idea chiave è vedere le CNN come **estrattori di caratteristiche** da adattare alle diverse applicazioni biometriche.

La slide p. 10 segnala il corso *Intelligent Systems for Industry, Supply Chain and Environment* (IS4), tenuto dallo stesso docente con un approccio simile, per chi vuole approfondire l'AI in altri settori.

---

### **2. Dalla rete totalmente connessa alla convoluzione**

_(slide pp. 7–9)_

Si parte da una **piccola rete totalmente connessa**: sappiamo che è utile partire da un modello piccolo, e anche reti con pochi neuroni possono apprendere caratteristiche rilevanti. La domanda è: **servono davvero tutti i collegamenti? Alcuni si possono condividere?**

**Il caso di un'immagine** (p. 8). Elaborare un'immagine con una rete totalmente connessa significa imparare **tutte le combinazioni di pixel** in ingresso: un numero **astronomico**. Però molti **pattern sono più piccoli** dell'intera immagine: per capire che in un blocco c'è un **becco** basta un rilevatore (*beak detector*) che guarda una **piccola regione**, con molti meno parametri.

**Lo stesso pattern compare in posti diversi** (p. 9). Un becco può trovarsi in alto a sinistra o al centro. Invece di addestrare un rilevatore per ogni posizione, che richiederebbe un'enorme rete piena di rilevatori, i rilevatori per posizioni diverse possono **usare gli stessi parametri**: si addestra **un solo piccolo rilevatore** e lo si fa **scorrere** su tutta l'immagine.

> 📌 **Le due idee alla base delle CNN.** (1) **Connessioni locali**: un neurone guarda solo una piccola regione. (2) **Condivisione dei pesi**: lo stesso rilevatore si applica in ogni posizione. Far scorrere un filtro con pesi condivisi su un'immagine è esattamente la **convoluzione** della Lezione 4.

<!-- SOURCE_VISUAL id="TAB07-V01" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="8" type="illustrazione" description="Immagine di un uccello con beak detector applicato a una piccola regione" reason="Mostra perché basta guardare una regione ridotta" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V02" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="9" type="illustrazione" description="Upper-left beak detector e middle beak detector che possono condividere gli stessi parametri" reason="Motiva la condivisione dei pesi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Gli strati convoluzionali**

_(slide pp. 11–17)_

#### **3.1 Filtri come rilevatori**

_(slide pp. 11–12)_

> 📌 **CNN.** Una rete neurale con **alcuni livelli convoluzionali** (e alcuni altri livelli). Un livello convoluzionale ha un certo numero di **filtri** che eseguono operazioni di convoluzione. L'uscita della convoluzione **risponde forte dove l'immagine in ingresso è simile al kernel** usato.

Nell'esempio della slide un'immagine **6×6** viene elaborata da più filtri **3×3** (*Filter 1*, *Filter 2*, …), ognuno dei quali rileva un **piccolo pattern**. I valori dei kernel sono **i parametri da imparare**: nelle reti neurali tradizionali i parametri erano solo i pesi delle connessioni.

<!-- SOURCE_VISUAL id="TAB07-V03" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="12" type="matrici" description="Immagine 6x6 e filtri 3x3 (Filter 1, Filter 2) con valori da imparare" reason="I valori numerici di immagine e filtri sono nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2 Ripasso della convoluzione: stride e feature map**

_(slide pp. 13–16)_

Il filtro 3×3 si sovrappone all'immagine 6×6 a partire dall'angolo in alto a sinistra; si calcola il **prodotto scalare** (*dot product*) tra filtro e porzione di immagine, e si sposta il filtro.

- Con **stride = 1** il filtro si sposta di un pixel alla volta; con l'immagine 6×6 si ottiene un'uscita **4×4** (p. 15).
- Con **stride = 2** si sposta di due pixel (p. 14): l'uscita è più piccola.
- Ripetendo per **ogni filtro** (p. 16), con due filtri si ottengono **due immagini 4×4**, cioè una matrice **2×4×4**: la **feature map**.

> 💡 **Nota didattica (non presente nelle slide): dimensione dell'uscita.** Con ingresso $N \times N$, filtro $F \times F$, stride $S$ e senza padding:
>
> $$N_{out} = \left\lfloor \frac{N - F}{S} \right\rfloor + 1$$
>
> Con $N = 6$, $F = 3$: stride 1 dà $\frac{3}{1} + 1 = 4$; stride 2 dà $\lfloor 1{,}5 \rfloor + 1 = 2$.

<!-- SOURCE_VISUAL id="TAB07-V04" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="15" type="matrici" description="Convoluzione dell'immagine 6x6 con Filter 1 a stride 1 e matrice di uscita 4x4" reason="Verificare i valori della feature map" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V05" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="16" type="matrici" description="Feature map 2x4x4 ottenuta ripetendo la convoluzione per Filter 1 e Filter 2" reason="Mostra la costruzione della feature map" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3 Immagini a colori**

_(slide p. 17)_

Un'immagine a colori ha **3 canali** (RGB). I filtri diventano **3×3×3**: ogni filtro ha un kernel 3×3 **per ciascun canale**, e il prodotto scalare coinvolge tutti e tre i canali insieme.

<!-- SOURCE_VISUAL id="TAB07-V06" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="17" type="illustrazione 3D" description="Immagine a colori RGB a 3 canali con filtri 3x3x3" reason="La struttura tridimensionale del filtro è visiva" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Convoluzione contro strato totalmente connesso**

_(slide pp. 18–20)_

Guardando **un solo strato**:

- in uno strato **fully connected** ogni neurone è collegato a **tutti** i 36 pixel dell'immagine 6×6;
- in uno strato **convoluzionale** ogni neurone di uscita è collegato **solo a 9 ingressi** (la finestra 3×3): **molti meno parametri** (p. 19);
- inoltre i neuroni corrispondenti a posizioni diverse **condividono gli stessi 9 pesi** (*shared weights*, p. 20): **ancora meno parametri**, e si **riusano alcuni precalcoli**.

> 💡 **Il conto dei parametri (esempio costruito sulla slide).** Per produrre 16 uscite (una mappa 4×4) da 36 ingressi, uno strato fully connected richiede $16 \times 36 = 576$ pesi. Uno strato convoluzionale con un filtro 3×3 condiviso ne richiede **9**: una riduzione di oltre 60 volte, per un solo filtro.

<!-- SOURCE_VISUAL id="TAB07-V07" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="19" type="schema di connessioni" description="Neuroni di uscita collegati solo a 9 ingressi invece che a tutti i 36 pixel" reason="Visualizza le connessioni locali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V08" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="20" type="schema di connessioni" description="Shared weights: neuroni di posizioni diverse che usano gli stessi pesi del filtro" reason="Visualizza la condivisione dei pesi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. ReLU**

_(slide p. 21)_

Dopo la convoluzione si applica la funzione di attivazione **ReLU** (*Rectified Linear Unit*):

$$\text{ReLU}(x) = \max(0, x)$$

La slide sottolinea che la ReLU crea **gradiente nullo** nella funzione di ottimizzazione dei pesi per gli ingressi negativi, **semplificando l'apprendimento**.

> 💡 **Precisazione sul perché aiuta.** La ReLU è semplicissima da calcolare, azzera i valori negativi (le unità inattive non contribuiscono) e, soprattutto, per gli ingressi positivi ha **derivata costante pari a 1**: il gradiente non si attenua attraversando molti strati, a differenza di sigmoide e tanh (Lezione 6, §11.5). La dispensa MATLAB lo riassume come «training più rapido ed efficace».

<!-- SOURCE_VISUAL id="TAB07-V09" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="21" type="grafico di funzione" description="Funzione ReLU" reason="Grafico della funzione di attivazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. La CNN completa: pooling e flattening**

_(slide pp. 22–31)_

#### **6.1 L'architettura**

_(slide pp. 22–23)_

$$\text{Convolution} \rightarrow \text{Max Pooling} \rightarrow \text{Convolution} \rightarrow \text{Max Pooling} \rightarrow \dots \rightarrow \text{Flattened} \rightarrow \text{Fully Connected} \rightarrow \text{classi}$$

Il blocco convoluzione + pooling **si può ripetere molte volte**; l'uscita finale classifica, per esempio, *face, body, dog, horse*.

> 📌 **CNN contro fully connected.** Una CNN fa **quello che fa una rete fully connected**, ma riesce a: **ridurre il numero di connessioni**; **condividere i precalcoli** tra neuroni; tramite il **pooling**, **ridurre la complessità** del problema.

<!-- SOURCE_VISUAL id="TAB07-V10" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="22" type="schema architettura" description="CNN completa: convolution e max pooling ripetuti, flattened, fully connected feedforward network con classi face, body, dog, horse" reason="Schema di riferimento della CNN" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Il pooling**

_(slide pp. 24–28)_

**Perché il pooling** (p. 24): **sottocampionare** i pixel **non cambia l'oggetto** (un uccello rimpicciolito resta un uccello). L'immagine diventa più piccola e servono **meno parametri** per elaborarla.

**Max pooling** (p. 26): la mappa 4×4 prodotta da ogni filtro viene divisa in blocchi 2×2 e di ogni blocco si tiene il **massimo**: si ottiene un'immagine **2×2**. **Ogni filtro è un canale**; il risultato è una nuova immagine, **più piccola**.

Caratteristiche del sottocampionamento (p. 27):

- **riduce il numero di caratteristiche**, senza sovrapposizioni;
- **riduce la risoluzione spaziale**, quindi diminuisce l'importanza di **dove esattamente** è stata trovata una caratteristica, mantenendo solo la **posizione approssimativa**;
- **media** o **max pooling** sono le tecniche più comuni;
- un pooling **2×2** comporta una compressione **4:1**, un **3×3** una **9:1**;
- **uniforma i dati** e li rende **invarianti a piccole traslazioni**;
- dopo il primo strato ci sono sempre più mappe da collegare allo strato successivo; da quali mappe precedenti ogni mappa riceve ingressi è una **decisione umana predefinita**.

> 💡 **Esempio numerico di max pooling (didattico).** Il blocco $\begin{bmatrix} 3 & -1 \\ -3 & -1 \end{bmatrix}$ diventa $3$; il blocco $\begin{bmatrix} -3 & 1 \\ 0 & -3 \end{bmatrix}$ diventa $1$. Anche se la caratteristica si sposta di un pixel dentro il blocco, il massimo non cambia: da qui l'invarianza a piccole traslazioni.

<!-- SOURCE_VISUAL id="TAB07-V11" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="26" type="matrici" description="Max pooling della feature map 4x4 in immagine 2x2 per ciascun filtro" reason="Valori del max pooling nell'esempio della slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V12" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="28" type="esempio di immagini" description="Esempio di max pooling su un'immagine reale" reason="La slide contiene solo l'esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Canali, flattening e rete finale**

_(slide pp. 29–31)_

Ripetendo più volte convoluzione e max pooling si ottiene una **nuova immagine più piccola dell'originale**, con **più canali**. Alla fine la mappa viene **appiattita** (*flattening*, p. 31): tutti i valori vengono messi in fila in un **vettore**, che entra nella **rete fully connected feedforward** per la classificazione (*cat, dog, …*).

> ⚠️ **Da verificare: numero di canali in uscita.** Il livello testo di p. 29 mette in relazione il numero di canali in uscita con il **numero di filtri dello strato** e con il **numero di canali in ingresso**, ma l'impaginazione non permette di ricostruire la frase esatta. La regola standard è: **canali in uscita = numero di filtri**, mentre il numero di canali in ingresso determina la **profondità di ciascun filtro** (e quindi il numero dei suoi pesi, come nell'esempio Keras del §7).

<!-- SOURCE_VISUAL id="TAB07-V13" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="29" type="schema" description="CNN completa con nota sul numero di canali in uscita in funzione del numero di filtri e dei canali in ingresso" reason="Verificare la regola sui canali riportata dalla slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V14" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="31" type="schema" description="Flattening della feature map in vettore verso la fully connected feedforward network" reason="Illustra l'appiattimento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Esempio: una CNN in Keras**

_(slide pp. 32–34)_

Rispetto a una rete tradizionale cambiano solo la **struttura** e il **formato dell'ingresso**: da vettore a **tensore 3D**. L'esempio usa immagini **28×28 pixel** in bianco e nero (`input_shape = (28, 28, 1)`; 1 = bianco e nero, 3 = RGB).

| Strato | Operazione | Dimensione dell'uscita |
|---|---|---|
| Input | immagine | $1 \times 28 \times 28$ |
| Convolution | **25** filtri 3×3 | $25 \times 26 \times 26$ |
| Max Pooling | 2×2 | $25 \times 13 \times 13$ |
| Convolution | **50** filtri 3×3 | $50 \times 11 \times 11$ |
| Max Pooling | 2×2 | $50 \times 5 \times 5$ |
| Flattened | vettore | $1250$ |
| Fully connected | rete feedforward | uscita |

**Quanti parametri per ogni filtro?** (p. 33)

- nel **primo** strato convoluzionale ogni filtro ha **$3 \times 3 = 9$** parametri (l'ingresso ha 1 canale);
- nel **secondo** strato ogni filtro ha **$25 \times 9 = 225$** parametri, perché deve coprire i **25 canali** prodotti dal primo strato.

> 💡 **Verifica dei conti.** $28 - 3 + 1 = 26$; $26 / 2 = 13$; $13 - 3 + 1 = 11$; $\lfloor 11 / 2 \rfloor = 5$; $50 \times 5 \times 5 = 1250$. Contando anche un bias per filtro, il primo strato ha $25 \times (9 + 1) = 250$ parametri e il secondo $50 \times (225 + 1) = 11.300$.

<!-- SOURCE_VISUAL id="TAB07-V15" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="33" type="schema con dimensioni" description="CNN in Keras con dimensioni 1x28x28, 25x26x26, 25x13x13, 50x11x11, 50x5x5 e parametri per filtro 9 e 225" reason="Schema dimensionale di riferimento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. AlphaGo: pochi ingressi, problema difficile**

_(slide p. 35)_

La rete di **AlphaGo** riceve la scacchiera come matrice **19×19** (nero: 1, bianco: −1, vuoto: 0) e produce la **mossa successiva**. Una rete fully connected si potrebbe usare, ma la **CNN funziona molto meglio**, perché anche nel Go contano i **pattern locali** e questi si ripetono in posizioni diverse.

> ⚠️ **Avere «solo» 19×19 ingressi non significa affrontare un problema semplice.** La difficoltà dipende dalla struttura del problema, non dalla dimensione dell'ingresso.

<!-- SOURCE_VISUAL id="TAB07-V16" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="35" type="schema" description="AlphaGo: scacchiera 19x19 codificata con 1, -1, 0 in ingresso a una rete che predice la mossa successiva" reason="Esempio di CNN su input piccolo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Allenare le reti deep**

_(slide pp. 36–39)_

#### **9.1 Progettazione e backpropagation**

_(slide pp. 36–38)_

La **maggior parte dei pesi** si addestra con la **retropropagazione** (*backpropagation*). La **struttura** della CNN è invece di solito **costruita a mano** per tentativi ed errori:

- **numero totale di strati**;
- **numero di kernel**;
- **dimensione dei kernel**;
- **dimensione dei campi di sottocampionamento** (pooling);
- **a quali campi dello strato precedente** collegarsi.

Tipicamente, procedendo con gli strati, si **riduce la dimensione** delle mappe di caratteristiche e se ne **aumenta il numero**.

> 📌 **Backpropagation** (*backward propagation of errors*). Algoritmo fondamentale per addestrare in modo efficiente le reti neurali: aggiorna **pesi e bias** per **minimizzare la differenza** tra l'uscita della rete e quella desiderata, studiando il **gradiente dell'errore locale** rispetto ai pesi. Ha due fasi: un **passaggio in avanti** (*forward pass*), che calcola l'uscita, e un **passaggio all'indietro** (*backward pass*), che propaga l'errore e calcola i gradienti.

#### **9.2 I limiti del gradiente nei primi strati**

_(slide p. 39)_

Nei primi strati di una rete deep il gradiente può:

| Fenomeno | Effetto |
|---|---|
| **annullarsi** (*vanishing gradient*) | aggiornamento dei pesi **molto lento o inefficace** |
| **crescere in modo incontrollato** (*exploding gradient*) | aggiornamento **numericamente instabile** |

Di conseguenza i primi strati **apprendono poco o nulla** e faticano a catturare strutture significative. La difficoltà è che per gli strati intermedi **non ci sono etichette** o riferimenti da usare direttamente: per questo sono state introdotte **tecniche alternative e correttive**.

---

### **10. Autoencoder e allenamento greedy strato per strato**

_(slide pp. 40–46)_

La soluzione storica al problema dei primi strati è il **greedy layer-wise training**, realizzato con gli **autoencoder**.

> 📌 **Autoencoder** (*encode → decode*). Rete che cerca di scoprire **caratteristiche generiche** dei dati imparando a **riprodurre l'ingresso in uscita**. Impara la **funzione identità** attraverso un collo di bottiglia, quindi deve apprendere **sotto-caratteristiche importanti** e non semplicemente far passare i dati. Serve anche per la **compressione**. Le nuove caratteristiche si possono usare da sole come nuovo set di addestramento, oppure concatenare a quelle originali.

Il vantaggio: **non servono etichette**. L'uscita desiderata è l'ingresso stesso (nell'esempio: il livello di grigio del primo pixel in uscita deve uguagliare quello del primo pixel in ingresso), quindi si può allenare in modo **non supervisionato**.

**Procedura con stacked autoencoder** (pp. 42–46):

1. si allena un primo autoencoder sull'immagine: **uscita = ingresso**;
2. si **scarta lo strato di decodifica** e si **mantengono i parametri** dell'encoder;
3. si **impila** un nuovo autoencoder sull'uscita dell'encoder e lo si allena allo stesso modo; si ripete per più strati, **riducendo sempre il numero di neuroni** procedendo con gli strati;
4. si esegue un **allenamento supervisionato sull'ultimo strato** usando le caratteristiche finali;
5. infine si esegue un **allenamento supervisionato dell'intera rete** per il **fine tuning** di tutti i pesi.

> 💡 **Perché funziona.** Ogni strato viene inizializzato in modo sensato, con pesi che già estraggono strutture utili dei dati, invece che a caso. La backpropagation finale deve solo **rifinire** i pesi, e il problema del gradiente nei primi strati pesa molto meno. Il survey della Parte C formalizza la perdita di ricostruzione (§17.2).

<!-- SOURCE_VISUAL id="TAB07-V17" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="40" type="schema" description="Deep network con greedy layer-wise training" reason="Schema dell'allenamento strato per strato" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V18" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="42" type="schema" description="Stacked auto-encoders: allenamento con uscita uguale all'ingresso, rimozione del decoder, mantenimento dei parametri" reason="Illustra la procedura di impilamento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Fine tuning e riuso delle CNN**

_(slide pp. 47–50)_

#### **11.1 Riallenare la parte fully connected**

_(slide pp. 47–48)_

Una CNN allenata per un problema generico si adatta a un nuovo problema **riallenando con backpropagation la parte fully connected**, per cambiare l'uscita come serve.

> 📌 **Fine tuning partendo da una CNN pubblica.** Una rete allenata sul **problema PB1** (classificare *face, body, dog, horse, …*) viene riusata per il **problema PB2** (**FACE YES/NO**): gli strati di **convoluzione e pooling** vengono **congelati** (*FREEZE*), la parte **fully connected** viene **riallenata** (*TUNE*).

<!-- SOURCE_VISUAL id="TAB07-V19" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="48" type="schema" description="Fine tuning da CNN pubblica: strati convoluzionali FREEZE, parte fully connected TUNE, da classificazione PB1 a PB2 face yes/no" reason="Schema centrale del fine tuning" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.2 Dal modello generico all'applicazione biometrica**

_(slide pp. 49–50)_

- Le CNN sono nate per la **classificazione generica**, ma i dati biometrici hanno **caratteristiche diverse**.
- Serve **adattarne il funzionamento** senza reinventarne la struttura.
- Il **fine tuning** è la **chiave pratica** per uno sviluppatore o per un gruppo di ricerca medio-piccolo. Esempio della slide: **AlexNet** con fine tuning per il volto raggiunge il **97%**.

**Perché non si allena (sempre) tutto da zero?**

| Motivo | Spiegazione (slide) |
|---|---|
| dataset biometrici **limitati** | poche migliaia di immagini di persone non bastano per allenare da zero reti con **centinaia di milioni di parametri** |
| addestramento completo **costoso** | possono servire **settimane** su architetture dedicate molto costose |
| i **primi strati** apprendono **pattern generici** | sono veri **estrattori generici** che non serve riallenare per l'uso biometrico |
| riutilizzare è **più robusto ed efficace** | meno **overfitting**, dato che abbiamo molti meno esempi delle aziende (Google, Meta, …) che hanno prodotto le reti |

<!-- SOURCE_VISUAL id="TAB07-V20" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="49" type="esempio di risultato" description="AlexNet con fine tuning che riconosce un volto con confidenza 97%" reason="Esempio concreto del risultato del fine tuning" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Il ruolo delle CNN nel sistema biometrico e i rischi**

_(slide pp. 51–55)_

#### **12.1 La CNN non è il sistema biometrico**

_(slide p. 51)_

- La CNN **non è (tutto) il sistema biometrico**.
- Produce **rappresentazioni numeriche** usate per **prendere decisioni**, **misurare**, **confrontare**.
- Il **matching** avviene di solito **a valle**.
- Le CNN sono diventate un **componente molto importante**: dalle loro prestazioni dipende fortemente il funzionamento di tutto il sistema.

> 💡 **Collegamento con la Lezione 1.** Nella pipeline *acquisizione → estrazione → template → matching → decisione* la CNN occupa il posto dell'**estrattore di feature**: il suo vettore di uscita è il **template** (in DeepFace, un embedding). Soglia e decisione restano fuori dalla rete (Lezioni 8–9).

#### **12.2 Rischi dei modelli riallenati: i bias**

_(slide p. 52)_

I modelli riallenati possono contenere **bias sconosciuti ereditati dal dataset di allenamento iniziale**: per esempio funzionare **peggio con alcune etnie o fasce d'età**.

> 📌 **Soluzione.** Il progettista deve usare **dataset di test opportuni** per **confrontare le prestazioni tra i diversi gruppi di utenti** e **verificare diverse condizioni applicative**, così da individuare l'esistenza di bias.

#### **12.3 Altri rischi**

_(slide p. 53)_

- **scarsa separabilità inter-soggetto**: la rete non è stata progettata per distinguere persone;
- **sensibilità a fattori non biometrici** (sfondo, luce, accessori);
- **modelli non progettati per la sicurezza**;
- **possibile leakage informativo**: la rete può estrarre anche **informazioni mediche** non volute o sensibili;
- **vulnerabilità** a **presentation attack** e **attacchi avversari**: la slide mostra **maschere con disegni avversari** che eludono i sistemi di riconoscimento facciale.

<!-- SOURCE_VISUAL id="TAB07-V21" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="53" type="fotografie" description="Adversarial mask designs che eludono i sistemi di riconoscimento facciale" reason="Esempio visivo di attacco avversario" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.4 Leakage informativo e privacy dei vettori**

_(slide pp. 54–55)_

Esempio della slide: se nel file usato per farti entrare nell'area aziendale del tuo dipartimento trovassi scritto che «**sei in forte sovrappeso e a rischio di infarto**», saresti contento? I **vettori biometrici** possono nascondere informazioni **in modo meno esplicito**.

**Leakage informativo nei vettori biometrici**:

- il template contiene **più informazioni del necessario**;
- informazioni **non richieste né autorizzate**;
- **difficili da controllare a posteriori**;
- **rischio per privacy e sicurezza**.

> 📌 **Principio fondamentale.** «**Un buon template biometrico deve essere utile per il matching o la misurazione richiesta, ma inutile per qualsiasi altra cosa.**»

Le reti restituiscono vettori di caratteristiche che il sistema gestisce, ma **non si dovrebbero poter estrarre informazioni non autorizzate**: genere, orientamenti, predisposizioni a malattie, profilazione non autorizzata. È **vero in teoria, difficile da evitare nella pratica**.

> ⚠️ **Sembrano vettori casuali e anonimi, ma non lo sono.** Un embedding di 128 o 2622 numeri non è leggibile a occhio, ma un altro modello può stimarne età, genere o etnia. La protezione dei template è il tema della Lezione 13.

---

### **13. Classificazione diretta e object detection**

_(slide pp. 56–60)_

La slide p. 56 rimanda alla **dispensa introduttiva** sull'ambiente MATLAB (quella della Lezione 6) per approfondire.

| | **Direct image classification** | **Object detection** |
|---|---|---|
| Uscita | una **classe** («Dog») | una **bounding box** $\{x_1, y_1, x_2, y_2\}$ con la classe |
| Informazione sull'oggetto | **nessuna** oltre alla classe | **posizione** (rettangolo con lati paralleli agli assi) |
| Rete | più piccola, **molto veloce** | **più grande**, richiede **più potenza di calcolo** |
| Ingresso | un blocco | l'immagine **intera** o porzioni maggiori |

**Uso classico in biometria** della classificazione diretta (p. 58): è **molto veloce**, adatta ai **sistemi embedded**; si usa per la **segmentazione** tramite **block processing** di **volti e impronte**, e per gli **oggetti in scene di sorveglianza** tramite block processing o **blob detector**, con uscita **yes/no** per ogni blocco.

> ⚠️ **La bounding box non è una misura.** La slide nota che con una bounding box a lati paralleli è **quasi impossibile ottenere misure** precise: il rettangolo delimita l'oggetto ma non ne descrive forma, orientamento o dimensioni reali.

<!-- SOURCE_VISUAL id="TAB07-V22" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="57" type="schema" description="Direct image classification: image classifier che restituisce solo Dog senza altre informazioni sull'oggetto" reason="Contrasto con l'object detection" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V23" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="59" type="schema" description="Object detection con bounding box (x1, y1) e (x2, y2) a lati paralleli" reason="Mostra il formato dell'uscita del detector" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Laboratorio**

### **14. Gli strumenti**

_(slide pp. 61–72)_

Le note servono solo a **preparare** gli strumenti per le lezioni successive. Gli strumenti principali del corso offrono il miglior compromesso tra **usabilità e complessità**, una buona curva di apprendimento, supportano **Keras** e molti modelli pre-allenati.

| Strumento | Caratteristiche (slide) |
|---|---|
| **MATLAB** (Tool #1) | non solo ML: migliaia di funzioni documentate; **licenza gratuita per gli studenti UNIMI**; scaricare l'ultima versione perché i toolbox migliorano rapidamente. Toolbox consigliati: **Deep Learning**, **Fuzzy Logic**, **Statistics and Machine Learning**, **Image Processing**, **Signal Processing**, **Computer Vision** |
| **Google Colab** (Tool #2) | ambiente gratuito di **notebook Jupyter** nel cloud, senza configurazione: scrivere ed eseguire codice, salvare e condividere, accedere a **risorse di calcolo** (anche TPU) dal browser. Richiede un **account Google**, quindi non è obbligatorio: in alternativa MATLAB, strumenti locali (Jupyter, Anaconda, Python, probabilmente con GPU) o un account Google senza dati personali |
| **Anaconda** (Tool #3) | distribuzione **Python e R** con molte librerie preinstallate e un ottimo **gestore di pacchetti e dipendenze**; **Anaconda Navigator** gestisce applicazioni, pacchetti e ambienti senza riga di comando |
| **Jupyter Notebook**, IDE | notebook locale simile a Colab; in alternativa un ambiente di sviluppo integrato |
| **Keras** | libreria Python per il deep learning |

La slide p. 70 osserva che **Colab** è pronto all'uso e più **indipendente dall'hardware** (si può provare il codice anche su TPU), con limitazioni poco rilevanti per il corso. Il primo passo pratico è **caricare il file da elaborare** (p. 72).

<!-- SOURCE_VISUAL id="TAB07-V24" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="68" type="screenshot" description="Anaconda Navigator con applicazioni, notebook simile a Colab e IDE" reason="Interfaccia dello strumento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **15. Face detection con Haar Cascade**

_(slide pp. 73–82)_

#### **15.1 Il modello**

_(slide p. 73)_

> 📌 **Haar Cascade (OpenCV).** Algoritmo di **machine learning allenato, non deep**, per il rilevamento di oggetti in immagini e video. Nella rilevazione del viso individua le regioni dell'immagine che più probabilmente contengono un volto e le restituisce come coordinate di una **bounding box**.

**OpenCV** è una libreria (sviluppata originariamente da Intel) molto utile ed efficiente.

#### **15.2 Il codice in sette passi**

_(slide pp. 74–76, appendice p. 82)_

Il codice **non è richiesto all'esame** (appendice, p. 81); se ne riporta la struttura per capire che cosa fa.

```python
# Step 1: installare OpenCV (in Colab i comandi shell iniziano con !)
!pip install opencv-python
# Step 2: importare le librerie
import cv2
from google.colab.patches import cv2_imshow
# Step 3: caricare il classificatore pre-allenato per volti frontali
face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
# Step 4: leggere l'immagine
img = cv2.imread('./sample_data/face3.jpg')
# Step 5: face detection sul canale grigio
gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
faces = face_cascade.detectMultiScale(gray, 1.1, 4)
# Step 6: disegnare un rettangolo per ogni bounding box
for (x, y, w, h) in faces:
    cv2.rectangle(img, (x, y), (x+w, y+h), (255, 255, 0), 2)
# Step 7: mostrare l'immagine
cv2_imshow(img)
```

| Passo | Che cosa conta capire |
|---|---|
| 3 | il modello pre-allenato viene caricato nella versione **per volti frontali** |
| 5 | il detector lavora sul **canale grigio**; produce **più bounding box** se ci sono più volti |
| 6 | ogni box è $(x, y, w, h)$: angolo in alto a sinistra, larghezza, altezza |

> 💡 **Nota didattica (non presente nelle slide): i parametri 1.1 e 4.** In `detectMultiScale`, `1.1` è lo **scale factor**: l'immagine viene analizzata a scale successive ridotte del 10%, per trovare volti di dimensioni diverse. `4` è il numero minimo di **rilevamenti vicini** richiesti per confermare un volto: valori più alti riducono i falsi positivi ma possono perdere volti.

#### **15.3 I risultati**

_(slide pp. 77–79)_

Il modello viene provato su tre immagini: `face1.jpg`, `face2.jpg`, `face3.jpg`, presenti nella cartella della lezione.

- Su `face2.jpg`, nella semplicità del modello e **pur non essendo stato allenato con volti laterali**, si ottiene una **buona detection**.
- Compare però il **primo falso positivo**, che potrebbe essere **filtrato** successivamente con altre tecniche, per esempio misurando la **qualità** del rilevamento.

<!-- SOURCE_VISUAL id="TAB07-V25" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="77" type="risultato del laboratorio" description="Output della face detection Haar Cascade su face1.jpg con bounding box" reason="Risultato visivo del laboratorio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V26" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="78" type="risultato del laboratorio" description="Output su face2.jpg: buona detection anche su volti laterali e primo falso positivo" reason="Mostra il falso positivo commentato nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB07-V27" source="Lezione_07_DeepLearning_e_CNN_per_Applicazioni_Biometriche.pdf" page="79" type="risultato del laboratorio" description="Output della face detection su face3.jpg" reason="Risultato visivo del laboratorio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **16. Sintesi delle slide**

_(slide p. 80)_

> ✅ **In sintesi (slide).**
> - **Struttura** delle CNN e funzionamento degli **strati convoluzionali**: connessioni locali, pesi condivisi, ReLU, pooling, flattening.
> - **Apprendimento** non supervisionato (autoencoder, greedy layer-wise) e supervisionato (backpropagation), **fine tuning** congelando la parte convoluzionale.
> - **Rischi di privacy** associati ai vettori biometrici: bias, leakage informativo, vulnerabilità agli attacchi.
> - Laboratorio: strumenti (MATLAB, Colab, Anaconda) e face detection con Haar Cascade.

---

## **Parte C — Dispensa: *Deep Learning for Biometrics: A Survey***

### **17. Il survey e le architetture deep**

_(paper pp. 1–6)_

#### **17.1 Obiettivo e sfide della biometria**

_(paper pp. 1–2)_

**Kalaivani Sundararajan** e **Damon L. Woodard** (University of Florida), *ACM Computing Surveys* 51(3), articolo 65, **maggio 2018**. Il survey analizza **100 approcci** che usano il deep learning per riconoscere individui con diverse modalità biometriche, e conclude che la maggior parte della ricerca si concentra su **volto** e **speaker recognition**.

Il deep learning **impila più strati** di algoritmi di apprendimento per approssimare funzioni fortemente non lineari e imparare **rappresentazioni gerarchiche**, che hanno sostituito le feature progettate a mano in visione, parlato e linguaggio. La sua rinascita si deve a tecniche di ottimizzazione efficienti e a risorse di calcolo potenti; è stato accolto con riluttanza da parte dei ricercatori per lo **scarso supporto teorico**, ma adottato con entusiasmo dalle industrie con molti dati.

Le **sfide** dei sistemi biometrici reali secondo il survey:

| Sfida | Descrizione |
|---|---|
| **grande numero di identità** | distinguere potenzialmente **milioni** di individui con differenze sottili richiede modelli molto complessi |
| **variazioni intra-persona** | possono essere **più grandi** delle variazioni tra persone diverse |
| **input rumoroso e distorto** | sensori rumorosi; qualità peggiore con acquisizioni **nascoste** (*covert*) in condizioni non vincolate |
| **estrazione dell'informazione biometrica** | serve molto pre-processing (volto in uno sfondo complesso, voce in un ambiente rumoroso) |
| **permanenza** | i tratti fisiologici cambiano gradualmente; quelli comportamentali anche per fattori socio-ambientali |
| **unicità** | non è chiaro se un singolo tratto identifichi univocamente; i comportamentali si usano quasi solo in **verifica** |
| **attacchi** | i sistemi sono attaccabili a vari livelli, e gli attacchi vanno riconosciuti |

**Motivazione** (§1.1): addestrato in modo discriminativo, il deep learning può imparare **feature sottili** che distinguono moltissimi individui e, con abbastanza campioni, **separare i fattori** che influenzano il riconoscimento, gestendo variabilità intraclasse e rumore. Lo svantaggio è che servono modelli complessi e **molti dati**; per variazioni lente come l'invecchiamento si possono usare approcci **generativi** per sintetizzarle.

#### **17.2 Perché il deep learning funziona e le architetture principali**

_(paper pp. 2–6)_

**Fattori di successo** (§2.1): **feature learning** (feature imparate dai dati che generalizzano ad altri compiti); **rappresentazioni gerarchiche** (strati bassi semplici, strati alti complessi, proprietà locali e globali); **rappresentazioni distribuite** (un fattore è spiegato da molti neuroni e un neurone spiega molti fattori: rappresentazioni compatte che aiutano contro la **curse of dimensionality**); **risorse di calcolo** (GPU); **dataset su larga scala**.

| Architettura | Idea e formule (survey) |
|---|---|
| **Restricted Boltzmann Machine (RBM)** e **Deep Boltzmann Machine (DBM)** | modello grafico non orientato con uno strato visibile $v$ e uno nascosto $h$, basato sull'**energia** $E(v, h) = -b^\top v - c^\top h - v^\top W h$, con probabilità congiunta $P(v, h) = \frac{1}{Z}\exp(-E(v, h))$; $Z$ è la funzione di partizione, intrattabile, quindi si allena con metodi approssimati come la **Contrastive Divergence**. Una DBM si ottiene **impilando** più RBM |
| **Deep Belief Network (DBN)** | modello **generativo** con uno strato visibile e $n$ nascosti: archi orientati tra gli strati bassi (rete bayesiana) e non orientati tra i due più alti (RBM). Si allena **strato per strato**: prima una RBM, poi la successiva sui campioni nascosti della prima, e così via. Può **inizializzare un MLP** (*pretraining*), poi rifinito con dati etichettati (*discriminative fine-tuning*) |
| **Stacked autoencoder** | un **encoder** produce $h = f(x)$, un **decoder** ricostruisce $g(h) \approx x$; si allenano insieme minimizzando $L(x) = \lVert g(f(x)) - x \rVert_2^2$. Per evitare che imparino la funzione identità si impongono **regolarizzazioni** su $h$: autoencoder **sparsi**, **denoising**, **contrattivi** |
| **CNN** | ispirate alla **corteccia visiva** dei mammiferi; per un ingresso 1D $x$ e un kernel $k$: $y[n] = \sum_{m=-\infty}^{\infty} x[m]\, k[n - m]$. I kernel non sono predefiniti ma **appresi**; connessioni **sparse** e **pesi condivisi**; ogni unità vede un piccolo **campo recettivo**; il pooling dà invarianza a spostamento, scala e distorsione |
| **Recurrent Neural Network (RNN)** | per dati **sequenziali** (testo, voce, video): a ogni istante $m^{(t)} = U x^{(t)} + W h^{(t-1)} + b$, $h^{(t)} = \tanh(m^{(t)})$, $o^{(t)} = V h^{(t)} + c$, $y^{(t)} = \text{softmax}(o^{(t)})$. Hanno una **«memoria»**, ma non si possono srotolare per troppi passi per il **vanishing gradient**: da qui le **LSTM** |

<!-- SOURCE_VISUAL id="TAB07-V28" source="Lezione_07_DeepLearning_per_biometria_DISPENSA_PAPER.pdf" page="4" type="figura del paper" description="Fig. 1 del survey: architetture DBM, DBN, autoencoder, CNN e RNN srotolata" reason="Rappresentazione grafica delle cinque architetture" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **18. Deep learning per modalità biometrica**

_(paper pp. 6–16)_

Il survey esamina **quattro modalità fisiologiche** (volto, impronta, palmo, iride) e **quattro comportamentali** (voce, firma, andatura, digitazione), più la **regione perioculare**.

<!-- SOURCE_VISUAL id="TAB07-V29" source="Lezione_07_DeepLearning_per_biometria_DISPENSA_PAPER.pdf" page="6" type="figura del paper" description="Fig. 2 del survey: modalità biometriche e architetture deep corrispondenti" reason="Mappa modalità-architetture" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **18.1 Volto**

_(paper pp. 6–11)_

**Verifica e identificazione differiscono nella funzione di costo.** In **verifica** si confrontano **due volti**, quindi si usa il **metric learning** (Joint Bayesian, **triplet loss**); in **identificazione** si deve predire l'etichetta tra molte classi, quindi si usa la **cross-entropy** con strato **softmax**.

| Lavoro | Contributo e osservazioni (survey) |
|---|---|
| **DeepID** (Sun et al.) | CNN allenate con segnale congiunto di **identificazione e verifica**; più identità in training migliorano la verifica; rappresentazioni sovracomplete a più scale e canali colore; un solo segnale (solo identificazione o solo verifica) non è ottimale; le feature sono moderatamente sparse, selettive per attributi legati all'identità (etnia, età, capelli) e **robuste alle occlusioni** |
| **DeepFace** (Facebook) | prestazioni in verifica **al livello umano**; CNN a **9 strati** allenata su **4 milioni** di immagini di **4000** soggetti. Proprietà per la **trasferibilità**: lo strato collo di bottiglia deve essere **compatto** (fa da regolarizzatore); con più campioni le prestazioni saturano, ma migliorano scegliendo i campioni con *bootstrapping*; norma della rappresentazione correlata con la discriminabilità |
| **FaceNet** (Schroff et al., Google) | **embedding** diretto delle immagini con **triplet loss**: un'immagine di una persona deve essere **più vicina** a tutte le altre immagini della stessa persona che a qualunque immagine di un'altra; allenata con **100–200 milioni** di immagini di **8 milioni** di soggetti; usabile per verifica, identificazione e clustering |
| **Hu et al.** (studio controllato su LFW) | il **colore** non migliora molto le prestazioni; **normalizzare** le feature aiuta; ridurre da 160 a **16** dimensioni conserva abbastanza informazione; feature di strati diversi sono complementari; reti su **parti del volto** aiutano; il **metric learning** conta |
| **Zhu et al.** (5 milioni di immagini, 20.000 identità) | le prestazioni crescono linearmente con il **numero di identità**, ma più identità con **pochi campioni** non aiutano (distribuzione **a coda lunga** dei dataset web); con molti dati i «trucchi» contano poco; i dataset contengono **celebrità** giovani, sorridenti e truccate, lontane dagli scenari reali (in particolare dall'invecchiamento) |
| Altri | feature **FIP** per ricostruire volti in vista canonica frontale; **center loss** per compattare le classi; ensemble di CNN **consapevoli della posa**; reti **sparse** che con il **12%** dei parametri eguagliano DeepID2+; l'**etichettatura errata** danneggia più della riduzione dei dati; **GAN** per generare volti preservando l'identità |

**Attributi del volto** (§3.1.2): gli attributi **restringono i candidati** nel riconoscimento.

- **Età**: classificazione o regressione con CNN o ensemble; per l'**età apparente** i dataset sono piccoli, quindi si parte da modelli pre-allenati su ImageNet e si fa fine tuning (per esempio **VGG-16**, stimando l'età come **valore atteso** delle probabilità softmax sulle fasce d'età); modelli dedicati a bambini e anziani, sottorappresentati.
- **Genere**: patch locali ad alto contenuto informativo; robustezza a occlusioni e bassa risoluzione enfatizzando la **regione perioculare**; ensemble su *poselets*.
- **Multi-attributo**: CNN multitask per stimare attributi a supporto dell'**autenticazione continua su dispositivi mobili**; gli errori di **genere** avvengono soprattutto con **neonati e bambini**; reti che imparano implicitamente età, genere ed etnia anche se allenate per l'identità.

**Regione perioculare** (§3.2): la zona attorno all'occhio è utile quando la **metà inferiore del volto è coperta**; si usano RBM convoluzionali, autoencoder, CNN multitask.

> ⚠️ **Collegamento con il leakage (§12.4).** Il survey conferma che reti allenate **solo per l'identità** imparano implicitamente **età, genere ed etnia**: è la prova sperimentale del rischio di leakage informativo dei template.

#### **18.2 Impronta, palmo e iride**

_(paper pp. 11–13)_

| Modalità | Contributi deep (survey) |
|---|---|
| **Impronta** | stacked autoencoder per classificare in **arch, left/right loop, whorl**; due reti in cascata, **JudgeNet** (4 CNN a scale diverse per individuare patch con minuzie) e **LocateNet** (posizione delle minuzie e scarto delle patch spurie); CNN per estrarre i **pori**; CNN multiscala per le **impronte latenti**; **enhancement** con filtri appresi (RBM continue, DBN convoluzionali) al posto dei filtri contestuali; **segmentazione** delle latenti |
| **Palmo** | pelle simile a quella delle dita ma su area più grande (forma, linee principali, rughe, delta, minuzie); CNN a 4 strati **senza ROI**; RBM; **scattering network** (filtri wavelet predefiniti invece che appresi); AlexNet con distanza di Hausdorff; la **d-prime loss** basata sulle distribuzioni genuini/impostori funziona meglio della contrastive loss; CNN per estrarre la ROI |
| **Iride** | considerata il tratto **più distintivo**, ma con alto **failure-to-acquire**; l'approccio di **Daugman** usa filtri di Gabor; il deep learning sostituisce i Gabor con **filtri appresi**: VGG-Net + SVM, filtri specifici per sorgenti eterogenee (visibile e NIR), CNN molto profonde robuste a errori di **segmentazione e rotazione**; sparse autoencoder per immagini da **smartphone**; fusione di feature ordinali e CNN su mobile; classificazione delle **lenti a contatto**; riconoscimento di iridi **sinistre/destre** etichettate male |

#### **18.3 Voce, firma, andatura, digitazione**

_(paper pp. 13–16)_

| Modalità | Contributi deep (survey) |
|---|---|
| **Voce** | la modalità più accessibile (telefoni, VoIP); una parte **fisica** (articolazione) e una **comportamentale**; feature spettrali **MFCC** e **PLCC**; sistemi **text-dependent** e **text-independent** (più difficili); modelli **GMM-UBM** e **i-vector/PLDA**. Il deep learning serve soprattutto a calcolare statistiche al posto del GMM-UBM (DNN per ASR che allineano i frame); **d-vector** dagli strati collo di bottiglia, meno degradati dal rumore (la fusione con gli i-vector è la migliore); sistemi **end-to-end**: su un dataset privato «**Ok Google**» con **80.000** parlatori una **RNN** supera una DNN, con costo computazionale maggiore |
| **Firma** | il secondo tratto comportamentale più usato dopo la voce; online (posizioni, traiettorie, pressione) e offline (immagini statiche); variabilità intraclasse alta e bassa permanenza. CNN su coordinate, pressione, angoli e velocità; sparse autoencoder + SVM; **RNN** con descrittore **LNPS** invariante a scala e rotazione e triplet loss. Offline: approccio in due fasi, feature **writer-independent** apprese da una CNN e classificatori **writer-dependent** (SVM) per distinguere firme genuine da **falsi abili**; il modello separa firme molto diverse ma sbaglia con **falsi tracciati lentamente**; reti **siamesi** con AlexNet |
| **Andatura** (*gait*) | forma e movimento di chi cammina; sfide: abiti, calzature, oggetti trasportati, velocità; si usano **silhouette** (invarianti a colore e tessitura). Rappresentazioni **GEI** (*Gait Energy Image*) e **CGI**; reti **siamesi** con contrastive loss robuste al cambio di vista; le **CNN 3D** con informazione **temporale** migliorano molto; canali di **flusso ottico** in X e Y per rappresentazioni invarianti agli abiti |
| **Digitazione** (*keystroke*) | lo stile di battitura; l'unicità non è garantita, ma è utile per la **verifica continua** dopo il login; forte variabilità intraclasse (umore, posizione, tastiera). DBN a 2 strati su vettori di **31** feature temporali, migliore di un GMM; estensione a **sensori mobili** con 4–35 feature |

---

### **19. Dataset e risultati**

_(paper pp. 16–25)_

#### **19.1 Dataset di riferimento**

_(paper pp. 16–18)_

| Modalità | Dataset e dimensioni (survey) |
|---|---|
| Volto | **LFW**: 13.323 foto di 5.749 celebrità, 6.000 coppie per la verifica in 10 split; **YouTube Faces**: 3.425 video di 1.595 celebrità; **AR**: 4.000 immagini di 126 persone con variazioni di luce, occlusioni ed espressioni; **MORPH**: album 2 con 55.134 immagini di 13.000 individui con età, genere, etnia; **IJB-A**: 5.396 immagini e 20.412 frame di 500 persone; **Adience**: 26.580 immagini di 2.284 persone in 8 fasce d'età; **ChaLearn 2015**: età apparente, 2.476 immagini di training e 1.136 di validazione |
| Impronta | **FVC 2002**: 3 database da sensori diversi, set A con 100 soggetti × 8 impressioni, set B con 10 × 8; **NIST SD27**: 258 impronte latenti; **WVU DB**: 449 latenti |
| Palmo | **PolyU Hyperspectral**: 190 volontari, 69 bande spettrali, 5.240 immagini di 380 palmi |
| Iride | **VSSIRIS**: 560 immagini di 28 soggetti da iPhone 5S e Lumia 1020 nel visibile; **MICHE I**: oltre 3.732 immagini di 92 soggetti da 3 smartphone; **Q-FIRE**: acquisizioni a 5, 7 e 11 piedi; **LG2200** (116.564 immagini) e **LG4000** (29.986) di 676 soggetti |
| Voce | serie **NIST SRE** (dal 1996): SRE 2006, 2010, 2012 |
| Firma | **GPDS-960**: 24 firme genuine e 30 falsi per 960 individui; **SVC 2004**: 100 insiemi con 20 firme genuine e 20 falsi abili |
| Andatura | **CASIA-B**: 3.718 video di 101 soggetti, 6 orientamenti, camminata normale, con cappotto, con borsa; **OU-ISIR LP**: il più grande, 4.007 soggetti |
| Digitazione | **CMU**: 51 soggetti in 8 sessioni, con tempi di pressione e latenze tra tasti |

#### **19.2 Metriche e risultati principali**

_(paper pp. 18–25)_

**Metriche** usate: accuratezza di **verifica**; **rank-1** per l'identificazione chiusa; **Detection and Identification Rate** (DIR) a un **False Alarm Rate** tipicamente dell'1% per l'identificazione aperta; **EER**; per l'età il **Mean Absolute Error**:

$$MAE = \frac{1}{N}\sum_{k=1}^{N} \lvert \hat{y}_k - y_k \rvert$$

dove $y_k$ è l'età vera del campione $k$, $\hat{y}_k$ quella stimata e $N$ il numero di campioni; per il genere l'accuratezza $TP/N$.

| Modalità | Risultati salienti (survey) |
|---|---|
| **Volto (LFW)** | i metodi deep **superano** quelli precedenti; molti **superano le prestazioni umane** e si avvicinano al 100%: **FaceNet 99,63%**, DeepID2+ 99,47%, Liu et al. (Baidu) 99,41%, DeepFace 97,35%; un sistema commerciale COTS in un protocollo specifico 66,5%. Merito anche dei dataset di training da 0,2 a 200 milioni di immagini. Su **IJB-A** un approccio ibrido discriminativo-generativo (**DR-GAN**) supera le CNN discriminative in identificazione |
| **Età e genere** | su MORPH-II i metodi deep battono BIF + KCCA/KPLS (MAE fino a 2,89 anni); i migliori per l'età apparente usano reti **pre-allenate con fine tuning** ed ensemble; per genere ed etnia i deep sono **comparabili** ai metodi classici (circa 98%) |
| **Impronta** | su FVC 2002 i deep sono **comparabili** allo stato dell'arte (STFT leggermente migliore); sulle **latenti** (NIST SD27, WVU) pari o di poco migliori di un AFIS commerciale |
| **Palmo** | su PolyU i metodi precedenti erano già al 98–99,7%; i deep migliorano **di poco**, e con dataset piccoli l'efficacia resta **non conclusiva** |
| **Iride** | nel **visibile** e in condizioni non ideali i deep **superano anche Daugman**: su MICHE-I EER 8,35% (Gabor) contro 3,93% (sparse autoencoder); su LG2200 7,12% contro 2,40% (CNN); su LG4000 5,30% contro 1,82% |
| **Voce** | nessun lavoro segue il protocollo SRE ufficiale, quindi **non c'è un confronto comune**; i deep appaiono comparabili ai GMM |
| **Firma** | online (SVC 2004) prestazioni **comparabili**; offline (GPDS-300) le rappresentazioni **CNN** in due fasi sono nettamente migliori |
| **Andatura** | i deep superano gli altri metodi; l'informazione **temporale** (flusso ottico, ciclo di passo) dà i risultati migliori, fino al 97,35% su CASIA-B con CNN 3D |
| **Digitazione** | DBN con EER 3,5% contro 5,5% (GMM-UBM) e 8,4% (statistiche), ma su un dataset **limitato** |

> ⚠️ **Iride: nessuna contraddizione con la Lezione 4.** Le tabelle dove i metodi deep battono Daugman riguardano **smartphone, visibile e acquisizioni non ideali**. Con acquisizioni NIR controllate l'IrisCode resta un riferimento eccellente; il deep learning aiuta proprio dove le ipotesi di Daugman (buona segmentazione, NIR, cooperazione) cadono.

---

### **20. Discussione e conclusioni del survey**

_(paper pp. 25–27)_

**Impatto del deep learning** (§5.1):

1. **feature learning**: volto e voce richiedono feature locali e globali, adatte all'apprendimento gerarchico; per i tratti **comportamentali** progettare feature a mano è astratto, quindi impararle dai dati grezzi è utile;
2. **rappresentazioni invarianti** ai fattori di disturbo, preziose con dati reali rumorosi;
3. **capacità di generalizzazione** a dataset non visti e a compiti correlati (feature del riconoscimento facciale riusate per gli attributi); il pretraining sfrutta dati non etichettati;
4. **oltre le feature «bag-of-words»**: con il ritorno delle RNN si catturano gli aspetti **temporali** della biometria comportamentale.

**Limiti per l'applicabilità reale** (§5.2):

1. **oltre volto e voce** il deep learning è stato poco esplorato; iride e impronte, basate su feature locali di bordo, **potrebbero non trarne beneficio**;
2. **scalare l'identificazione**: la ricerca si è concentrata sulla verifica, relativamente facile; l'identificazione tra **milioni** di identità richiede architetture complesse e moltissimi dati;
3. **dataset su larga scala**: quelli esistenti non soddisfano i requisiti delle applicazioni **ad alta sicurezza**, e mancano per altre modalità;
4. **qualità dei dataset**: immagini di celebrità quasi frontali, ben illuminate, ad alta risoluzione, inadatte ad applicazioni **nascoste** reali;
5. **risorse di calcolo**: i dispositivi mobili potrebbero non avere la potenza necessaria, e spostare l'autenticazione nel **cloud** significa trasferire **dati sensibili** esposti ad attacchi;
6. **velocizzare l'addestramento**: la maggior parte dei ricercatori non ha le risorse dell'industria; servono algoritmi **efficienti nei dati**;
7. **biometria comportamentale**: utile per l'autenticazione nel cyberspazio, ma ancora poco esplorata.

**Potenziale futuro** (conclusioni): **identificazione su larga scala**; **biometria comportamentale** con RNN su serie temporali; **robustezza al rumore**; **modellare l'invecchiamento** biometrico con approcci **generativi** che sintetizzano dati invecchiati; **segmentazione** dei dati biometrici; **fusione di più modalità** allenando insieme architetture diverse.

> 💡 **Il survey e il resto del corso.** Diversi punti anticipano lezioni successive: aggiornamento del template e invecchiamento (Lezioni 8 e 15), autenticazione continua e comportamentale (Lezione 16), fusione multimodale (Lezione 15), rischi di trasferire dati nel cloud (Lezione 13).

---

### **21. Bibliografia del survey**

_(paper pp. 27–34)_

Le ultime pagine del survey contengono i **riferimenti bibliografici** (174 voci), dai lavori fondativi sul deep learning (Bengio, 2009) agli articoli specifici citati per ogni modalità. Non contengono contenuti aggiuntivi da studiare.

---

### **22. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Fully connected** / **Convoluzionale** | ogni neurone vede tutto l'ingresso / connessioni locali con pesi condivisi |
| **Stride** / **Pooling** | passo di spostamento del filtro / sottocampionamento della mappa (max o media) |
| **Feature map** / **Flattening** | tensore canali × altezza × larghezza / vettore per lo strato fully connected |
| **Vanishing** / **Exploding gradient** | aggiornamenti troppo piccoli / numericamente instabili |
| **Autoencoder** / **Classificatore** | ricostruisce l'ingresso senza etichette / predice un'etichetta |
| **Freeze** / **Tune** | strati convoluzionali congelati / strati finali riallenati |
| **Image classification** / **Object detection** | solo la classe / classe e bounding box |
| **Verifica deep** / **Identificazione deep** | metric learning, triplet loss / cross-entropy con softmax |
| **Haar Cascade** / **CNN** | ML allenato non deep, veloce / rete profonda con feature apprese |
| **Template utile** / **Leakage** | serve solo al matching / rivela informazioni non autorizzate |

---

### **23. Sintesi della lezione**

> ✅ **In sintesi.**
> - Tre regole: l'errore di training può andare a zero, quindi serve la **validazione separata**, e in biometria la **LNPO**.
> - **CNN**: filtri come rilevatori, connessioni locali, **pesi condivisi**, stride, feature map, canali RGB, **ReLU**, **max pooling** (invarianza a piccole traslazioni), flattening, rete fully connected; esempio Keras con 225 parametri per filtro nel secondo strato.
> - **Allenamento**: backpropagation, vanishing/exploding gradient, **autoencoder** e greedy layer-wise training, **fine tuning** congelando la parte convoluzionale.
> - La CNN è l'**estrattore di feature**, non tutto il sistema; rischi: **bias**, scarsa separabilità, attacchi avversari e **leakage informativo** («utile per il matching, inutile per tutto il resto»).
> - Classificazione diretta contro **object detection**; laboratorio con MATLAB, Colab, Anaconda e **Haar Cascade** di OpenCV.
> - **Survey 2018**: architetture (RBM/DBN, autoencoder, CNN, RNN), applicazioni a 8 modalità, dataset e risultati (volto oltre il livello umano su LFW, iride deep migliore nel visibile), limiti reali e direzioni future.
