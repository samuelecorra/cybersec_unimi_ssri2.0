## **Lezione 9: Programmare la similarità tra immagini, cross-correlazione e image retrieval**

La nona lezione del corso **IS4** porta nel codice i concetti della Lezione 08. In **Colab** si confrontano immagini con gli **hash percettivi** della libreria `imagehash`. In **MATLAB** si usa la **cross-correlazione** per due compiti: trovare una piccola immagine (una foglia) dentro una grande, e **risolvere automaticamente** il gioco «trova le differenze» allineando e sottraendo due disegni. La cross-correlazione è anche l’operazione alla base delle **reti neurali convoluzionali**. La lezione chiude mostrando come un **sistema di image retrieval** aiuti a costruire un dataset di immagini per una nuova applicazione.

Fonti: [PDF della Lezione 09](Lesson_09_CodingWithImages_CodingSimilarity.pdf), 69 pagine, e i supplementi letti integralmente nella cartella `code/`: [lesson_09_code_STEP1.m](code/lesson_09_code_STEP1.m), [lesson_09_code_STEP2.m](code/lesson_09_code_STEP2.m), la variante [\_\_no\_\_lesson_09_code_STEP2_finding_similarity.m](code/__no__lesson_09_code_STEP2_finding_similarity.m) e il notebook [Lesson_09_SimilarityInImages_imagehash.ipynb](code/Lesson_09_SimilarityInImages_imagehash.ipynb). Le 10 immagini usate dal codice sono nella cartella `images/`. Le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura e obiettivi della lezione**

_(slide pp. 1–4)_

Titolo originale: *Similarity in Artificial Intelligence, Coding with Similarity*. L’indice (p. 2) elenca:

1. **hash delle immagini** in Colab;
2. il concetto di **cross-correlazione**;
3. **primi passi con le immagini** in MATLAB;
4. **programmare un esempio di similarità**, in MATLAB e in Python (libreria `imagehash`);
5. **sistemi di image retrieval** per creare database di immagini specifici;
6. *scaling laws* per i modelli linguistici neurali;
7. punti principali.

> ⚠️ **Un argomento senza slide.** Le *scaling laws for neural language models* compaiono nell’indice, ma nel PDF non ci sono pagine dedicate e i punti principali (p. 69) non le citano.

**Che cosa imparare da una lezione di codice** (pp. 3–4):

- i **primi passi** con le immagini in MATLAB e Colab;
- la **struttura e i passi** dell’esempio di similarità;
- **all’esame non si scrive codice**, ma bisogna capire le **procedure principali**, con i loro **input e output**, per esempio l’hashing delle immagini e la cross-correlazione.

> 📌 **Non è solo codice** (p. 4). Si impara a **trovare e identificare oggetti**, o semplicemente **porzioni simili**, in un’immagine. Per farlo bisogna:
> - **capire il formato dell’input**: tipo di immagine, canali, valori dei pixel. È RGB o in scala di grigi? `uint8` o `float`?
> - **sapere che cosa implementano davvero le librerie**: come calcolano la correlazione (o la correlazione normalizzata) OpenCV, SciPy o MATLAB? **Normalizzano?** **Sottraggono la media?**
> - distinguere **segnale e sfondo**: accorgersi quando il filtro (template) sta corrispondendo allo **sfondo** invece che all’oggetto;
> - **usare i grafici per validare i risultati**: **visualizzare sempre**.

---

### **1. Hash delle immagini in Colab**

_(slide pp. 5–10)_

Un esempio molto semplice (p. 6) con una libreria potente e facile da usare. I passi sono: **scaricare** le immagini (p. 7), **caricarle** convertendole nello spazio colore corretto (p. 8), **visualizzarle** (p. 9), calcolare l’**aHash** e la **distanza di Hamming** bit a bit (p. 10; Lezione 08, §5.4).

**Il notebook** installa la libreria, usa OpenCV per caricare le immagini e scarica quattro foto di un cane e una di un panda:

```python
!pip install imagehash
import cv2                      # OpenCV per caricare e gestire le immagini
import requests
with open('dog1.jpg', 'wb') as handler:
    handler.write(requests.get('https://scotti.di.unimi.it/img2/dog1.jpg').content)
# ... lo stesso per dog2.jpg, dog3.jpg, dog4.jpg e panda.jpg

from matplotlib import pyplot as plt
img_color1 = cv2.imread('dog1.jpg')
img_color1 = cv2.cvtColor(img_color1, cv2.COLOR_BGR2RGB)
plt.imshow(img_color1)
plt.show()
```

> ⚠️ **OpenCV legge in BGR.** `cv2.imread` restituisce i canali nell’ordine **blu, verde, rosso**, mentre matplotlib si aspetta **RGB**: senza la conversione `COLOR_BGR2RGB` le immagini appaiono con rossi e blu scambiati. È il «colorspace corretto» della p. 8.

**Calcolo degli hash e delle distanze**: con `imagehash` la sottrazione tra due hash restituisce direttamente la **distanza di Hamming**.

```python
import imagehash
from PIL import Image

hash1 = imagehash.average_hash(Image.open('dog1.jpg'))
hash2 = imagehash.average_hash(Image.open('dog2.jpg'))
print(hash1)            # 0000d0e8ffffffff
print(hash1 - hash2)    # distanza di Hamming tra i due hash
```

Il notebook ripete il calcolo con `imagehash.phash`. Risultati salvati negli output, con l’immagine originale `dog1.jpg` confrontata con le altre:

| Confronto con l’originale | Immagine | aHash | pHash |
|---|---|---|---|
| **ribaltata** | `dog2.jpg` | **14** | **26** |
| **saturata** | `dog3.jpg` | **2** | **4** |
| **ritagliata** | `dog4.jpg` | **7** | **26** |
| **panda** (immagine diversa) | `panda.jpg` | **40** | **30** |

Gli hash a 64 bit dell’originale sono `0000d0e8ffffffff` (aHash) e `d94d2c351d51dccc` (pHash).

> 💡 **Leggere la tabella (nota didattica).** Su 64 bit, due immagini senza relazione differiscono in media in circa **32** bit.
> - La **saturazione** cambia i colori ma non la struttura: entrambi gli hash restano quasi uguali (2 e 4 bit).
> - Il **ribaltamento** e il **ritaglio** spostano il contenuto: per il pHash la distanza (26) è quasi quella del panda (30), quindi con una soglia tipica di una decina di bit **non** riconoscerebbe l’immagine come la stessa.
> - L’aHash, in questo esempio, separa meglio le varianti (7–14) dal panda (40).
>
> Nessun hash è invariante a tutto: la scelta dipende da **quali trasformazioni** si vogliono considerare «stessa immagine».

<!-- SOURCE_VISUAL id="IS09-V01" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="7" type="schermata" description="Download delle immagini in Colab" reason="Schermata del notebook" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V02" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="9" type="schermata" description="Immagini del cane originale, ribaltato, saturato, ritagliato e del panda visualizzate in Colab" reason="Immagini visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V03" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="10" type="schermata" description="Calcolo dell’aHash e della distanza di Hamming in Colab" reason="Schermata del notebook" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Similarità con la cross-correlazione**

_(slide pp. 11–16)_

> 📌 **Cross-correlazione** (p. 12). Misura la **similarità di due vettori o matrici** in funzione dello **spostamento** dell’uno rispetto all’altro. Restituisce:
> - il **valore di similarità**;
> - la **posizione** di **miglior allineamento** tra le due immagini.
>
> Schematicamente: `[similarity, displacement] = image_xcorr(I1, I2)`, dove una delle due immagini è il **template** e l’altra la **sottoimmagine** da cercare. È **la base delle reti neurali convoluzionali**.

**Il nucleo della cross-correlazione** (p. 13). Nelle applicazioni di elaborazione delle immagini, luminosità di immagine e template possono variare per **illuminazione** ed **esposizione**. Per questo le immagini si possono prima **normalizzare**, di solito **a ogni passo**, **sottraendo la media** e **dividendo per la deviazione standard**. Si ripete poi il calcolo **spostando** la sottoimmagine $f(x+i, y+j)$ e si cerca dove la correlazione normalizzata è **massima**.

> 📌 **Correlazione incrociata normalizzata** (NCC, formula della slide resa in forma standard). Con template $t(x, y)$ e sottoimmagine $f$ di $n$ pixel, medie $\bar{t}$, $\bar{f}$ e deviazioni standard $\sigma_t$, $\sigma_f$:
> $$\text{NCC}(i, j) = \frac{1}{n} \sum_{x, y} \frac{\big(f(x+i, y+j) - \bar{f}\big)\,\big(t(x, y) - \bar{t}\big)}{\sigma_f \, \sigma_t}$$
> Vale **1** per una corrispondenza perfetta, **0** in assenza di correlazione, **−1** per un’immagine «in negativo». Il miglior allineamento è $(i^*, j^*) = \arg\max_{i,j} \text{NCC}(i, j)$.

<!-- SOURCE_VISUAL id="IS09-V04" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="13" type="formula" description="Correlazione incrociata normalizzata tra template e sottoimmagine con lo spostamento f(x+i,y+j)" reason="Formula resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché la similarità è così importante?** (p. 15). Perché è una delle **primitive** del pattern recognition e del machine learning: un sistema intelligente riconosce un pattern, un oggetto, un vettore o un’immagine **perché è simile** a uno già etichettato visto in apprendimento. Nelle **reti convoluzionali** (p. 16) ogni strato applica ripetutamente questa operazione: **convoluzione → correlazione**.

> 💡 **Convoluzione e correlazione (nota didattica).** Le due operazioni differiscono solo per il **ribaltamento** del kernel: la convoluzione ruota il filtro di 180° prima di farlo scorrere, la correlazione no.
> $$(f \star t)(i, j) = \sum_{x, y} f(x+i, y+j)\, t(x, y) \qquad (f * t)(i, j) = \sum_{x, y} f(i-x, j-y)\, t(x, y)$$
> Nelle CNN i filtri vengono **appresi**, quindi il ribaltamento è irrilevante, e le librerie calcolano di fatto una correlazione. Ogni filtro «cerca» nell’immagine le zone **simili** a sé.

<!-- SOURCE_VISUAL id="IS09-V05" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="16" type="schema" description="Convoluzione e correlazione come base delle reti neurali convoluzionali" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. MATLAB, passo 1: trovare una foglia**

_(slide pp. 17–37)_

#### **3.1. Il gioco e il materiale**

**Il gioco** (p. 17): due disegni quasi identici in cui trovare **tutte le differenze**. Siete sicuri di averle trovate tutte? **Come automatizzare il compito** (p. 18)? Servono solo il **concetto di cross-correlazione** e un po’ di **codice MATLAB**.

**Materiale** (pp. 19–20): MATLAB (ultima versione) e i file della lezione, dal sito Ariel, in una cartella locale. Il consiglio: **non saltare alla soluzione**, ma seguire il processo, che insegna **formato e rappresentazione** delle immagini, **manipolazione**, **visualizzazione** e **similarità**.

| Passo (p. 21) | Obiettivo | File di codice | Immagini |
|---|---|---|---|
| **1** | capire come usare correlazione e convoluzione per trovare similarità: **trovare una foglia** | `lesson_09_code_STEP1.m` | template `find_difference_image_1.jpg`, sottoimmagine `find_difference_image_3.jpg` (la foglia) |
| **2** | risolvere il gioco con un approccio **completamente automatico**: **trovare le differenze** (DELTA) | `lesson_09_code_STEP2.m` | template `find_difference_image_1.jpg`, sottoimmagine `find_difference_image_2.jpg` |

<!-- SOURCE_VISUAL id="IS09-V06" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="17" type="immagini" description="Gioco trova le differenze con due disegni quasi identici" reason="Disegni visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V07" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="21" type="schema" description="I due passi: trovare la foglia e trovare le differenze (DELTA)" reason="Schema dei passi con immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Caricare e visualizzare le immagini**

_(slide pp. 22–26)_

```matlab
close all
clear all  % do not use it in case of debug...

img_template = imread('find_difference_image_1.jpg');
img_subimage = imread('find_difference_image_3.jpg');
whos

h1 = figure;
subplot(1,4,1)
imshow(img_template, [])
axis on
title('template')
```

| Istruzione | Che cosa mostra la slide |
|---|---|
| `imread` (p. 23) | immagine **a colori RGB** in **interi senza segno a 8 bit**: `uint8`, intervallo **da 0 a 255** |
| `whos` (p. 24) | le variabili del **workspace**, con dimensioni e tipo |
| `h1 = figure` (p. 25) | l’**handle** della figura, per riaprirla e aggiungere grafici |
| `imshow(img, [])` (p. 26) | le **parentesi quadre** attivano la **scala di colori automatica**, tra il minimo e il massimo dell’immagine |

<!-- SOURCE_VISUAL id="IS09-V08" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="23" type="schermata" description="Caricamento dell’immagine RGB uint8 in MATLAB" reason="Schermata del workspace" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V09" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="26" type="schermata" description="Visualizzazione delle immagini con handle e scala automatica" reason="Schermata delle figure" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. Invertire e normalizzare**

_(slide pp. 27–31)_

**Invertire o non invertire?** (p. 27). Dov’è l’informazione in un’immagine: nei colori, nei pattern, nei tratti bianchi o in quelli neri? Nella convoluzione **contribuiscono alla somma i valori alti** delle matrici. Qui i valori alti sono quelli dello **sfondo bianco**, che è la parte **meno utile**: quindi **conviene invertire** l’immagine.

**Da RGB a grigio, poi inversione** (p. 28): dopo l’inversione il **disegno diventa bianco** e fornisce più segnale, mentre lo **sfondo diventa nero**.

```matlab
%% color 2 gray conversion
img_template_gray = rgb2gray( img_template );
img_subimage_gray = rgb2gray( img_subimage );

%% inversion
img_template_gray = max(img_template_gray ) - img_template_gray  ;
img_subimage_gray = max(img_subimage_gray ) - img_subimage_gray  ;
```

> ⚠️ **`max` su una matrice (nota didattica).** Applicata a una matrice, `max(A)` restituisce il **massimo di ogni colonna**, e la sottrazione avviene colonna per colonna. Con uno sfondo bianco uniforme il risultato è quasi identico a un’inversione globale, ma la forma sicura è `max(A(:)) - A` oppure, per `uint8`, `255 - A`.

**Normalizzazione: solo sottrazione della media** (pp. 30–31). Si sottrae la media, **senza dividere per la deviazione standard**:

```matlab
img_template_gray_norm = img_template_gray - mean(mean(img_template_gray)) ;
img_subimage_gray_norm = img_subimage_gray - mean(mean(img_subimage_gray)) ;

mean_val = mean(img_template_gray_norm(:))   % 20.2077
min_val  = min(img_template_gray_norm(:))    % 0
max_val  = max(img_template_gray_norm(:))    % 229
```

`mean(mean(A))` calcola prima la media di ogni colonna, poi la media di queste medie: la **media globale**. L’immagine risultante, osserva la slide, è «**piuttosto scura**»: in `uint8` il bianco vale 255, e dopo la sottrazione i valori sono **media 20,2**, **minimo 0**, **massimo 229**.

> ⚠️ **Perché la media non è zero (nota didattica).** Il tipo `uint8` **non può contenere valori negativi**: tutti i pixel sotto la media vengono **troncati a 0** (saturazione). Per questo il minimo è 0 e la media resta 20,2 invece di 0. Per una vera sottrazione della media bisogna prima convertire in `double`, come farà il passo 2 per l’immagine delta (§4.2).

<!-- SOURCE_VISUAL id="IS09-V10" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="28" type="schermata" description="Immagini convertite in grigio e invertite con il disegno bianco su sfondo nero" reason="Immagini visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V11" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="31" type="schermata" description="Immagine dopo la sottrazione della media: scura, con media 20.2077, minimo 0, massimo 229" reason="Immagine e valori visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.4. Cross-correlazione 2D e ricerca del massimo**

_(slide pp. 32–37)_

**Correlazione 2D** (pp. 32–33). La formula generale della NCC (§2) normalizza; la funzione MATLAB **`xcorr2`** calcola invece la cross-correlazione **diretta, senza scalatura**. Si ripete il calcolo spostando la sottoimmagine e si cerca il **massimo**.

```matlab
% xcorr2(A,B) computes the crosscorrelation of matrices A and B.
crr = xcorr2(img_template_gray_norm , img_subimage_gray_norm);

% find the maximum
[ssr,snd] = max( crr(:) );   % operator (:) unfold the elements of the matrix in a 1D vector
[ij,ji] = ind2sub(size(crr),snd);

figure
surf(crr);
shading interp;              % just to remove black line around the tiles
hold on
plot3( ji, ij, ssr,'go', 'MarkerSize', 15, 'LineWidth',3)
```

| Istruzione | Ruolo |
|---|---|
| `xcorr2(A, B)` | matrice di correlazione per **ogni spostamento** di B rispetto ad A |
| `surf(crr)` + `shading interp` (p. 34) | superficie 3D della correlazione; `shading interp` toglie solo le **linee nere** tra le tessere |
| `crr(:)` | **srotola** la matrice in un vettore 1D |
| `max(crr(:))` | valore massimo `ssr` e **indice lineare** `snd` |
| `ind2sub(size(crr), snd)` | converte l’indice lineare in **riga** `ij` e **colonna** `ji` |

> 📌 **Il picco** (p. 35). Il massimo della correlazione indica **dove** si trova la sottoimmagine. Con immagini **simili** tra loro il picco è **meno appuntito**. Lo stesso concetto si usa nelle **reti convoluzionali** per cercare feature con **pattern specifici** nell’input.

> 💡 **Dimensioni dell’output (nota didattica).** Se il template è $M \times N$ e la sottoimmagine $P \times Q$, `xcorr2` produce una matrice $(M+P-1) \times (N+Q-1)$: una posizione per ogni sovrapposizione anche parziale. Il picco in $(ij, ji)$ corrisponde alla posizione dell’**angolo in basso a destra** della sottoimmagine, che occupa quindi le righe da $ij-P+1$ a $ij$ e le colonne da $ji-Q+1$ a $ji$.

<!-- SOURCE_VISUAL id="IS09-V12" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="34" type="grafico" description="Superficie 3D dell’output di xcorr2" reason="Forma della superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V13" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="35" type="grafico" description="Massimo della cross-correlazione come picco sulla superficie" reason="Posizione del picco visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Controllo finale** (pp. 36–37, codice più strutturato solo per la visualizzazione, **non d’esame**). Per verificare il risultato si crea un’**immagine di zeri** e si colloca la sottoimmagine nella posizione del **massimo**:

```matlab
img_diff = zeros(size(img_template_gray_norm)) ;
img_diff(ij:-1:ij-size(img_subimage_gray,1)+1, ji:-1:ji-size(img_subimage_gray,2)+1) = ...
    rot90(img_subimage_gray,2) ;
```

> 💡 **Due inversioni che si annullano (nota didattica).** Gli indici scorrono **all’indietro** (`ij:-1:...`) e la sottoimmagine è ruotata di **180°** (`rot90(..., 2)`): i due ribaltamenti si compensano e la foglia viene copiata **dritta** nelle righe da $ij-P+1$ a $ij$. Il file la chiama una notazione «non d’esame».

<!-- SOURCE_VISUAL id="IS09-V14" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="37" type="schermata" description="Visualizzazione finale: correlazione, template e foglia trovata nella posizione del massimo" reason="Figure visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. MATLAB, passo 2: trovare le differenze**

_(slide pp. 38–61)_

#### **4.1. Allineare le due immagini**

_(slide pp. 38–45)_

**Come vedere le differenze?** (p. 40). L’idea generale è **sottrarre** le due immagini, ma:

1. le immagini hanno **dimensioni diverse**, quindi non si possono sottrarre direttamente, in nessun linguaggio;
2. bisogna **posizionare correttamente** la seconda immagine, per evitare **false rilevazioni**.

La **correlazione** serve a trovare **dove** sottrarle.

<!-- SOURCE_VISUAL id="IS09-V15" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="40" type="immagini" description="Due immagini di dimensioni diverse da allineare prima della sottrazione" reason="Immagini visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Stesse operazioni, ma senza inversione** (pp. 41–43). Si convertono le immagini in grigio (con l’handle della figura) e si **ricomincia con le immagini bianche**: questa volta si prova a **non invertire**.

```matlab
img_template = imread('find_difference_image_1.jpg');
img_subimage = imread('find_difference_image_2.jpg');

img_template_gray = rgb2gray( img_template );
img_subimage_gray = rgb2gray( img_subimage );

img_template_gray_norm = img_template_gray - mean(mean(img_template_gray)) ;
img_subimage_gray_norm = img_subimage_gray - mean(mean(img_subimage_gray)) ;

crr = xcorr2(img_template_gray_norm , img_subimage_gray_norm);
[ssr,snd] = max( crr(:) );
[ij,ji] = ind2sub(size(crr),snd);
```

**Output di `xcorr2` con immagini bianche** (p. 44): la cross-correlazione funziona allo stesso modo, ma il picco si trova sopra una «**grande collina**». Il massimo è comunque un **picco netto** da localizzare con `crr(:)` (p. 45).

> 💡 **Perché la collina (nota didattica).** Con lo sfondo bianco quasi tutti i pixel hanno valori alti: per **qualsiasi** spostamento con buona sovrapposizione la somma dei prodotti è grande, e la superficie forma un rilievo largo. Il disegno aggiunge il picco sopra. Invertendo, lo sfondo vale circa 0 e contribuisce poco, per cui resta visibile quasi solo il picco.

<!-- SOURCE_VISUAL id="IS09-V16" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="44" type="grafico" description="Output di xcorr2 con immagini bianche: picco sopra una grande collina" reason="Forma della superficie visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. Sottrarre le immagini: l’immagine delta**

_(slide pp. 46–54)_

**L’idea** (p. 46): vedere le differenze sottraendo dal template originale un’immagine `img_diff` che contiene la sottoimmagine **ridimensionata e spostata** correttamente:

$$\text{img\_delta} = \text{template} - \text{img\_diff}$$

`img_diff` parte come **copia del template**, per due motivi: avere le **dimensioni giuste** per la sottrazione, e **copiare i bordi**, che mancano nella sottoimmagine, così il confronto è più leggibile.

```matlab
% copy the original image (**just for the EXTERNAL FRAME**)
img_diff = img_template_gray ;

% copy the SHIFTED SUBIMAGE (this notation is not in the exam!!!)
img_diff(ij:-1:ij-size(img_subimage_gray,1)+1, ji:-1:ji-size(img_subimage_gray,2)+1) = ...
    rot90(img_subimage_gray,2) ;

% we use double since we need negative values (not allowed in the uint8 format)
img_delta = double(img_template_gray) - double(img_diff);
```

La p. 47 sottolinea che **memoria e visualizzazione** ordinano i dati in modo diverso, e che la notazione dello spostamento **non è d’esame**. Le pp. 48–50 riaprono la figura tramite l’handle per **controllare** che la sottoimmagine sia dentro `img_diff` e **con gli spostamenti corretti**.

> 📌 **Serve un’immagine `double`, non `uint8`** (pp. 51–52). La differenza può essere **negativa**, e `uint8` non la può memorizzare. Bisogna fare un **cast** a `double` di **entrambi** gli operandi **prima** della sottrazione, per evitare problemi di conversione.

> 💡 **Che cosa succederebbe senza cast (nota didattica).** In `uint8`, $50 - 200$ non dà $-150$ ma **0**: tutti i pixel «cancellati» sparirebbero dall’immagine delta. È lo stesso troncamento visto nella normalizzazione del §3.3, ma qui farebbe perdere metà delle differenze.

<!-- SOURCE_VISUAL id="IS09-V17" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="46" type="schema" description="Template, img_diff con la sottoimmagine spostata e bordi copiati, e sottrazione" reason="Schema delle immagini visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V18" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="52" type="schermata" description="Cast a double per memorizzare i valori negativi dell’immagine delta" reason="Schermata del codice e del workspace" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V19" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="54" type="immagine" description="Controllo dell’immagine delta" reason="Immagine visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3. Istogramma e soglie: aggiunte e cancellazioni**

_(slide pp. 55–60)_

**Migliorare la visualizzazione** (p. 55): capire come usare l’**istogramma** dell’immagine nel proprio lavoro. A volte l’istogramma è **troppo «piatto»** per capire che cosa succede; con lo **stretching dell’istogramma** i valori zero appaiono **grigi**.

```matlab
figure
subplot(1,2,1)
hist( img_delta(:), [2*255]);
title('Histogram of the delta image');

subplot(1,2,2)
[h_y, h_x] = hist( img_delta(:), [2*255]);
semilogy(h_x, h_y);
title('Logaritmic histogram of the delta image');
```

L’istogramma usa $2 \times 255 = 510$ intervalli, perché la differenza tra due immagini `uint8` va da $-255$ a $+255$. La scala **logaritmica** (`semilogy`) rende visibili le poche barre lontane dallo zero, schiacciate dall’enorme barra centrale.

**Come leggere l’istogramma dell’immagine delta** (p. 56):

| Zona | Significato |
|---|---|
| **valori negativi** | pixel **cancellati** |
| **valore zero** | pixel **invariati**: differenza nulla, **la grande maggioranza** |
| **valori positivi** | pixel **aggiunti** |
| valori piccoli attorno a zero | **bordi** e **rumore** |

Servono quindi due **soglie**, una per le **cancellazioni** e una per le **aggiunte**, che escludano bordi e rumore.

```matlab
max_value = max(img_delta(:));
min_value = min(img_delta(:));

additions = (img_delta > 0.5 * max_value ) ;
deletions = (img_delta < 0.5 * min_value );
```

> 💡 **Le soglie del codice (nota didattica).** Un pixel è un’**aggiunta** se supera **metà del valore massimo**, una **cancellazione** se scende sotto **metà del valore minimo** (negativo). Il fattore 0,5 è empirico: abbastanza alto da scartare bordi e rumore, abbastanza basso da tenere le differenze vere. Il risultato sono due **immagini binarie** (maschere) che si visualizzano separatamente (pp. 57–59).

<!-- SOURCE_VISUAL id="IS09-V20" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="55" type="grafico" description="Istogramma dell’immagine delta lineare e logaritmico, con stretching" reason="Forma dell’istogramma visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V21" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="56" type="grafico" description="Istogramma annotato: pixel cancellati, invariati e aggiunti, soglie, bordi e rumore" reason="Annotazioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V22" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="59" type="immagini" description="Immagine delta separata in aggiunte e cancellazioni" reason="Maschere visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS09-V23" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="60" type="immagini" description="Panoramica dell’intero processo per trovare le differenze" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ **Il processo in sei passi.** (1) Caricare e convertire in grigio; (2) sottrarre la media; (3) calcolare `xcorr2` e trovarne il massimo; (4) collocare la sottoimmagine spostata in una copia del template; (5) sottrarre in `double`; (6) separare aggiunte e cancellazioni con soglie scelte sull’istogramma.

#### **4.4. Compiti per casa ed esperimenti nel codice**

_(slide p. 61)_

**Compiti** (p. 61):

- come cambia la correlazione (CRR) se si passa come sottoimmagine **lo stesso template**? Che cosa succede al **massimo** e alla **forma** della CRR?
- come cambia se si passano **due immagini completamente diverse**? Che cosa succede al massimo e alla forma?

Il file `lesson_09_code_STEP2.m` contiene gli esperimenti per rispondere:

| Esperimento | Input di `xcorr2` | Che cosa mostra |
|---|---|---|
| `crr4` | template con **se stesso** | autocorrelazione |
| `crr2` | template contro l’immagine `moon.tif` (inclusa in MATLAB) | due immagini **diverse** |
| `crr3` | `moon.tif` contro un **ritaglio** di se stessa (50 pixel di bordo tolti) | una parte dentro il tutto; il codice stampa il rapporto `max3/max2` |
| `crr5` | `ice_creams.jpg` con se stessa | autocorrelazione di un’immagine con molti oggetti simili |
| `crr6` | `ice_creams.jpg` contro `icecream2.jpg` (un **singolo gelato** come kernel) | «similarità nelle CNN»: un kernel che cerca un oggetto ripetuto |
| `crr6` | `ice_creams.jpg` contro `panda.jpg` come kernel | un kernel che **non** corrisponde a nulla |

> 💡 **Le risposte attese (nota didattica).**
> - **Stessa immagine**: il massimo si trova esattamente al **centro** della matrice di correlazione, cioè a spostamento nullo, ed è il **valore più alto possibile** (l’energia dell’immagine). La superficie è **simmetrica**, con un picco netto.
> - **Immagini completamente diverse**: **nessun picco dominante**, una superficie bassa e irregolare. Il rapporto `max3/max2` quantifica quanto il picco di una vera corrispondenza superi quello tra immagini estranee.
> - **Gelati**: con il singolo gelato come kernel compaiono **più picchi**, uno per ciascun gelato simile. È esattamente ciò che fa un filtro di una CNN.

> ⚠️ **Il file con prefisso `__no__`.** `__no__lesson_09_code_STEP2_finding_similarity.m` è una **variante** del passo 2: inverte le immagini, contiene un ritaglio commentato e ripete alcune figure. Il prefisso indica che **non è la versione di riferimento**; i file da seguire sono `STEP1` e `STEP2`. Tutti e tre i file hanno «Matalb» nell’intestazione, e le immagini richieste vanno nella stessa cartella del codice (nel materiale sono in `images/`).

---

### **5. Sistemi di image retrieval**

_(slide pp. 62–68)_

Come **trovare immagini** per la propria applicazione di ML (p. 62)? Costruendo un **sistema di image retrieval** (p. 63).

**Perché** (p. 64). Si vuole creare una nuova **app che conta le calorie** dalla foto di un piatto. Bisogna **misurare le quantità** (grammi) e **classificare** cibi o ricette. Probabilmente si useranno reti neurali o deep learning, lavorando su **modelli**, **topologia** e **dataset**. Ma **come creare un dataset di immagini adeguato** per addestrare il modello?

**Il problema: trovare molte foto di risotto** (p. 65). Con un approccio classico si può:

- **scaricare** dataset di immagini già etichettati;
- **sfruttare i concorrenti**, creando un dataset nuovo e completo tramite image retrieval ed etichettatura adeguata;
- usare un repository di base come la **ricerca immagini di Google**.

Probabilmente **non basta**: bisogna **accedere a un dataset molto grande**, **selezionare** le immagini necessarie ed **etichettarle con cura**. Tutto questo si può fare **automaticamente** con un sistema di image retrieval, e lo stesso vale per le **mucche** (Lezione 08, §2) o per la prossima applicazione.

> 📌 **Probe e gallery** (p. 66). La **probe** è l’immagine di esempio (un risotto); la **gallery** è l’archivio in cui cercare (`00001.jpg`, …, `94283.jpg`). Si calcola l’**aHash** della probe e di ogni immagine della gallery e la **distanza** tra gli hash (per esempio **3**): le immagini più vicine sono le candidate. La p. 67 ricorda il calcolo dell’aHash e della distanza di Hamming visto in Colab.

<!-- SOURCE_VISUAL id="IS09-V24" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="66" type="schema" description="Ricerca di immagini di risotto: probe, gallery e distanza tra aHash" reason="Immagini di probe e gallery visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Classificare con più feature** (p. 68, esempio di base). Un metodo di **ranking** (una **policy**) basato su **feature spiegabili** mantiene il sistema **usabile e gestibile**. Per ogni immagine della gallery si calcolano più distanze dalla probe:

- **aHash**;
- **dHash**;
- **colorhash**;
- **distanza tra istogrammi**.

Per ogni feature le immagini vengono **ordinate per distanza**. Policy d’esempio: «**scegliere l’immagine che è 1ª, 2ª o 3ª in almeno 3 feature**». Nella tabella della slide la **vincitrice** del ranking per la probe è `00001.jpg`.

> 💡 **Perché combinare più feature (nota didattica).** Ogni hash è sensibile a trasformazioni diverse (§1): una foto ribaltata può sfuggire al pHash ma non all’istogramma dei colori, e viceversa due piatti con gli stessi colori possono avere forme diverse. Una regola di **voto** tra feature è più robusta di una sola distanza, e resta **spiegabile**: si può dire perché un’immagine è stata scelta.

<!-- SOURCE_VISUAL id="IS09-V25" source="Lesson_09_CodingWithImages_CodingSimilarity.pdf" page="68" type="tabella" description="Ranking delle immagini della gallery su aHash, dHash, colorhash e istogramma con vincitrice 00001.jpg" reason="Disposizione della tabella non ricostruibile dal testo estratto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Hash percettivo** / **cross-correlazione** | impronta compatta confrontata con Hamming, dice «quanto sono simili» / confronto pixel per pixel su ogni spostamento, dice anche «dove» |
| **aHash** / **pHash** nell’esperimento | più tollerante a ribaltamento e ritaglio in questo esempio / quasi come un’immagine diversa dopo ribaltamento o ritaglio |
| **BGR** / **RGB** | ordine dei canali di OpenCV / ordine atteso da matplotlib |
| **NCC** / **`xcorr2`** | media sottratta e divisione per le deviazioni standard / correlazione diretta, senza scalatura |
| **Convoluzione** / **correlazione** | kernel ribaltato di 180° / kernel non ribaltato; nelle CNN è indifferente |
| **Immagine invertita** / **non invertita** | disegno bianco su sfondo nero, picco netto / sfondo bianco, picco sopra una grande collina |
| **`uint8`** / **`double`** | 0–255, i negativi diventano 0 / ammette valori negativi, necessario per media e delta |
| **`imshow(A)`** / **`imshow(A, [])`** | scala fissa del tipo di dato / scala automatica tra minimo e massimo |
| **Valori negativi** / **positivi** nel delta | pixel cancellati / pixel aggiunti |
| **Autocorrelazione** / **correlazione tra immagini diverse** | picco centrale massimo e simmetrico / nessun picco dominante |
| **Probe** / **gallery** | immagine di esempio / archivio in cui cercare |

---

### **7. Sintesi della lezione**

_(slide p. 69)_

> ✅ **Punti principali** (p. 69).
> - **Cross-correlazione**: misura la similarità tra due immagini per ogni spostamento e trova la posizione di miglior allineamento; normalizzata (NCC) resiste ai cambi di luminosità; è l’operazione alla base delle CNN.
> - **Primi passi con le immagini in MATLAB**: `imread`, `whos`, `imshow` con scala automatica, conversione in grigio, inversione, attenzione ai tipi `uint8` e `double`.
> - **Programmare un esempio di similarità**: in **MATLAB**, trovare una foglia con `xcorr2` e il suo massimo, poi trovare le differenze allineando, sottraendo in `double` e separando aggiunte e cancellazioni con l’istogramma; in **Python**, confrontare immagini con aHash e pHash e la distanza di Hamming.
> - **Sistemi di image retrieval** per creare database di immagini specifici: probe, gallery, distanze tra hash e ranking spiegabile su più feature.
