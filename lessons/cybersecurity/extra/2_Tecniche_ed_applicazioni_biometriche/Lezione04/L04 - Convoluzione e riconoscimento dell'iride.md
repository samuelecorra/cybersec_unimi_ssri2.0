# **Lezione 4: Convoluzione e riconoscimento dell'iride**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

La Lezione 4 ha tre parti:

1. **Teoria — la convoluzione**, una delle basi del *signal & image processing* e del *deep learning*;
2. **l'iride** come tratto monomodale, con i «nuovi argomenti» rispetto al corso precedente (iride nel visibile, privacy, attacchi);
3. una **prima breve simulazione d'esame** sul crash course.

> **Richiamo — Sistemi Biometrici.** L'iride è trattata in [L11 — Fisiologia, sensori e IrisCode](<../../../anno3/6_Sistemi_Biometrici/L11 - Sistemi biometrici basati sull’iride - fisiologia, sensori e IrisCode.md>), [L12 — Enhancement, prefiltraggio e matching](<../../../anno3/6_Sistemi_Biometrici/L12 - Algoritmi di enhancement, prefiltraggio e matching nei sistemi biometrici basati sull’iride.md>), [L13A — Prestazioni e spoofing](<../../../anno3/6_Sistemi_Biometrici/L13A - Iride, prestazioni, ottimizzazione e spoofing.md>), [L13B — Come funziona l'Iris Recognition (Daugman)](<../../../anno3/6_Sistemi_Biometrici/L13B - Come funziona l’Iris Recognition (Daugman).md>) e [L13C — Iride nel visibile](<../../../anno3/6_Sistemi_Biometrici/L13C - Iride nel visibile, scenari operativi, spoofing e antispoofing.md>).

---

## **Parte A — Teoria: la convoluzione**

### **1. Perché la convoluzione è ovunque**

_(slide pp. 3–5)_

La convoluzione è:

- uno dei **metodi di miglioramento delle immagini** più diffusi (p. 4): sfocatura, riduzione del rumore, esaltazione dei bordi e i filtri contestuali delle impronte visti nella Lezione 3 sono tutte convoluzioni;
- il cuore delle **CNN** (*Convolutional Neural Networks*, p. 5), le reti di deep learning più famose: la «C» sta proprio per **convoluzionale**. **Ogni strato convoluzionale elabora l'immagine al suo ingresso** per estrarre informazioni utili agli strati successivi, cioè esegue una **feature extraction**. Nelle lezioni successive (5–9) si vedrà come applicarle alla biometria.

<!-- SOURCE_VISUAL id="TAB04-V01" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="4" type="esempio di immagini" description="Esempio di miglioramento di un'immagine ottenuto con convoluzione" reason="Mostra l'effetto pratico di un filtro" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Definizione e formula**

_(slide pp. 6–8)_

#### **2.1 Filtrare un'immagine significa convolverla**

_(slide p. 6)_

> 📌 **Filtraggio = convoluzione.** Data un'immagine di ingresso $I(x, y)$ e un **kernel** (maschera, filtro) $k(x, y)$, l'immagine filtrata è
>
> $$O(x, y) = I(x, y) * k(x, y)$$
>
> dove $*$ indica la convoluzione.

<!-- SOURCE_VISUAL id="TAB04-V02" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="6" type="schema" description="Immagine di ingresso I(x,y), kernel k(x,y) e immagine di uscita O(x,y) = I * k" reason="Schema visivo dell'operazione di filtraggio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2 La convoluzione digitale**

_(slide p. 7)_

Per un'immagine digitale di dimensione $N \times M$ e un kernel di dimensione $N_k \times M_k$, la slide scrive:

$$O[x, y] = \sum_{r=1}^{N_k} \sum_{s=1}^{M_k} I[r, s]\; k[x - r,\, y - s]$$

| Elemento | Significato |
|---|---|
| $I[r, s]$ | valore del pixel dell'immagine di ingresso |
| $k[\cdot, \cdot]$ | peso del kernel, valutato in posizione relativa $(x - r, y - s)$ |
| $O[x, y]$ | valore del pixel di uscita in $(x, y)$ |
| parentesi $(\ )$ contro $[\ ]$ | versione **analogica** (continua) contro **digitale**, ottenuta per **campionamento** |

In pratica: si **sovrappone** il kernel all'intorno del pixel $(x, y)$, si **moltiplicano** tra loro i valori corrispondenti e si **sommano** i prodotti. Ripetendo per ogni pixel si ottiene l'immagine di uscita.

> 📌 **Operazione lineare.** La convoluzione è **lineare**: convolvere la somma di due immagini equivale a sommare le due convoluzioni, $(aI_1 + bI_2) * k = a(I_1 * k) + b(I_2 * k)$. È anche **invariante alla traslazione**: spostare l'immagine sposta il risultato, senza cambiarlo. Queste proprietà la rendono analizzabile e componibile a strati.

> ⚠️ **Convoluzione e correlazione.** Nella definizione matematica il kernel è **ribaltato** (compare $x - r$, non $x + r$). Molte librerie e le CNN usano in realtà la **correlazione**, che non ribalta il kernel. Per kernel simmetrici (per esempio la media) le due operazioni coincidono; nelle CNN la differenza è irrilevante, perché i pesi del kernel vengono **appresi**.

<!-- SOURCE_VISUAL id="TAB04-V03" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="7" type="formula con schema" description="Equazione della convoluzione digitale con somma su r e s, immagine NxM, passaggio da versione analogica a digitale" reason="Verificare indici e limiti della sommatoria" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3 Esempi di kernel 3×3**

_(slide p. 8)_

La slide mostra l'effetto di alcuni semplici kernel 3×3 su un'immagine originale.

> 💡 **Nota didattica (non presente nelle slide): kernel classici.**
>
> | Kernel | Matrice | Effetto |
> |---|---|---|
> | Media (*box blur*) | $\frac{1}{9}\begin{bmatrix}1&1&1\\1&1&1\\1&1&1\end{bmatrix}$ | sfoca e riduce il rumore |
> | Laplaciano | $\begin{bmatrix}0&1&0\\1&-4&1\\0&1&0\end{bmatrix}$ | evidenzia i bordi in tutte le direzioni |
> | Sobel orizzontale | $\begin{bmatrix}-1&0&1\\-2&0&2\\-1&0&1\end{bmatrix}$ | evidenzia i bordi verticali |
> | Sharpen | $\begin{bmatrix}0&-1&0\\-1&5&-1\\0&-1&0\end{bmatrix}$ | aumenta la nitidezza |
>
> **Esempio numerico.** Su un intorno 3×3 con valori $\begin{bmatrix}10&10&10\\10&100&10\\10&10&10\end{bmatrix}$ (un pixel di rumore al centro), il kernel media produce $\frac{1}{9}(8 \cdot 10 + 100) = 20$: il picco da 100 viene attenuato a 20. Il kernel sharpen produce invece $5 \cdot 100 - 4 \cdot 10 = 460$: il picco viene esaltato.

<!-- SOURCE_VISUAL id="TAB04-V04" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="8" type="esempi di filtraggio" description="Immagine originale filtrata con alcuni semplici kernel 3x3" reason="I kernel usati e i loro effetti sono mostrati solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — L'iride**

### **3. Panoramica del tratto**

_(slide pp. 9–16)_

#### **3.1 Caratteristiche generali**

_(slide pp. 9–12)_

- È considerato il **tratto biometrico più accurato in assoluto dopo il DNA**.
- È **poco gradito** dagli utenti per la sua **invasività percepita**.
- Presenta **caratteristiche numerosissime e stabili nel tempo**.
- **Formazione**: inizia dal **terzo mese** di vita fetale, si completa al **settimo mese**, ma è **stabile dal secondo anno** in poi.
- Il sistema è **piuttosto complesso e costoso**, ma **difficile da frodare**.
- È **potente** e impiegabile **dove altri tratti non si possono usare**, perché è **contactless** (p. 11).

La slide p. 12 propone il filmato di **IrisGuard**, uno dei migliori produttori, da commentare **tenendo conto che non è una valutazione indipendente**.

<!-- SOURCE_VISUAL id="TAB04-V05" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="11" type="fotografie" description="Scenari contactless in cui l'iride è impiegabile dove altri tratti non si possono usare" reason="Contesto applicativo del riconoscimento senza contatto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2 Vantaggi e svantaggi**

_(slide pp. 13–14)_

| Vantaggi | Svantaggi |
|---|---|
| acquisizione **senza contatto** | difficile acquisire un bersaglio **sempre in movimento**, largo **1 cm**, da circa **1 m** |
| **molte caratteristiche casuali e distintive** | l'iride è **piatta** ma si trova **dietro una superficie curva e bagnata** (la cornea) |
| è un **organo interno**, sempre protetto e presente in quasi tutta la popolazione | una buona parte è **nascosta da ciglia e palpebre** |
| esistono sistemi di acquisizione, elaborazione e matching **molto veloci** | il pattern si **deforma elasticamente** con la dimensione della **pupilla** |
| | invecchiando possono comparire **pigmentazioni** prima assenti |

#### **3.3 Unicità e variabilità intraclasse**

_(slide pp. 15–16)_

- Come per le impronte, **non esistono due iridi uguali**.
- Durante la formazione intervengono **componenti casuali** che producono un pattern di **righe, tagli e pieghe** (le feature iridee) **assolutamente unico**: la **tessitura** di ogni area è distintiva.
- Anche i **gemelli omozigoti** hanno iridi **diverse**.

> 💡 **Genotipo e fenotipo.** Il colore dell'iride è ereditario, ma la tessitura fine non lo è: si forma per processi casuali durante lo sviluppo. Per questo i gemelli identici, con lo stesso DNA, hanno iridi diverse, e persino l'occhio sinistro e destro della stessa persona sono diversi.

Fattori che **aumentano la variabilità intraclasse** (p. 16): **rotazioni della testa**, **variazioni dello zoom** delle ottiche, **dilatazione della pupilla**.

<!-- SOURCE_VISUAL id="TAB04-V06" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="16" type="confronto di immagini" description="Fattori di variabilità intraclasse: rotazione della testa, zoom delle ottiche, dilatazione della pupilla" reason="Mostra le tre deformazioni tipiche dell'iride acquisita" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Acquisizione dell'iride**

_(slide pp. 17–22)_

#### **4.1 Quale luce: infrarosso o visibile**

_(slide p. 17)_

Le feature più interessanti per il sistema biometrico **si vedono meglio con luce IR** che con luce visibile.

| | **Luce visibile** | **Luce infrarossa (IR)** |
|---|---|---|
| Che cosa si vede | gli **strati** che compongono l'iride; **meno** la tessitura utile | meglio i **pattern casuali e distintivi** tipici dell'iride |
| Melanina | **assorbe** la luce visibile: se non riflette, non la vediamo | **riflette molto meglio** la luce IR |

#### **4.2 Quale sensore**

_(slide p. 18)_

| Requisito | Valore (slide) |
|---|---|
| risoluzione sul **raggio dell'iride** | almeno **70 pixel** (di solito **100–140 pixel**) |
| sensore | **CCD monocromatici**, almeno **640×480**, sensibili al **vicino infrarosso (NIR)** |
| banda di illuminazione | **700–900 nm**, non visibile all'occhio |
| ottiche | **variabili**: prima si trova l'occhio nel volto, poi si **zooma** per acquisirlo alla massima risoluzione; in alcuni casi si usano **due telecamere** con ottiche diverse invece di uno zoom elettronico |

> 💡 **Dimensionamento.** L'iride ha un diametro di circa 11–12 mm (la slide dice «largo 1 cm»). Con 100 pixel sul raggio, cioè 200 pixel sul diametro, ogni pixel corrisponde a circa $10\ \text{mm} / 200 = 0{,}05\ \text{mm}$. Il requisito minimo di 70 pixel sul raggio fissa la soglia sotto cui la tessitura non è più risolvibile.

#### **4.3 Sensori commerciali, mobile e ATM**

_(slide pp. 19–22)_

- **Sensori commerciali** (pp. 19–20): sul mercato ci sono sistemi con **grandi differenze di costo e accuratezza**, **da palmo** e **da muro** (LG Iris, OKI, Panasonic, IrisGuard EyeTrust II).
- **Mobile** (p. 21): **Galaxy Note7** e **Galaxy S8–S9** con iris scanner. La tecnologia sviluppata per il mobile è ora impiegata anche nei **dispositivi di fascia media**.
- **ATM** (p. 22): il gruppo bancario **Citigroup**, con **Diebold**, usa un ATM con riconoscimento dell'iride (soluzione **IrisGuard**); il tempo di prelievo previsto passa **da 45 s a 15 s**. Esiste anche una versione **senza schermo e tastiera** che usa lo **smartphone del cliente** per l'input e l'output.

<!-- SOURCE_VISUAL id="TAB04-V07" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="20" type="fotografie prodotto" description="Altri dispositivi commerciali per l'acquisizione dell'iride" reason="La slide contiene solo immagini di dispositivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Il sistema di Daugman: dall'occhio all'IrisCode**

_(slide pp. 23–35)_

#### **5.1 Struttura dei moduli**

_(slide p. 23)_

La struttura secondo **Daugman** segue lo schema classico di identificazione:

- **enrollment**: *Acquisition Module* (immagine **480×640**) → **Quality Checker** → *Feature Extraction Module* → **iriscode** nel DataBase;
- **identification**: acquisizione → quality checker → estrazione dell'iriscode → *Matching Module* contro **N iriscode** → *User Identity* oppure *User not identified*.

<!-- SOURCE_VISUAL id="TAB04-V08" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="23" type="schema a blocchi" description="Struttura dei moduli secondo Daugman: enrollment e identification con immagini 480x640, quality checker, iriscode e matching con N iriscode" reason="Schema di riferimento del sistema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 La sequenza dei passi**

_(slide p. 24)_

Per passare da un'immagine dell'occhio a un **IrisCode**:

1. **individuazione di centri e raggi** della pupilla e dell'iride;
2. **rimozione della parte non utile**, occupata da palpebre e ciglia;
3. **linearizzazione** dell'iride;
4. trasformazione dell'iride linearizzata con le **2D Gabor wavelet**;
5. trasformazione delle **fasi** della trasformata wavelet in **bit**: l'**IrisCode**.

#### **5.3 Trovare pupilla e iride: l'operatore integro-differenziale**

_(slide pp. 25–26)_

La **grande variabilità delle immagini** fa fallire la maggior parte degli approcci classici di filtraggio, binarizzazione e segmentazione. **Daugman** propone invece di cercare nell'immagine, come **estrazione di un massimo**, i **centri dei contorni circolari** di variazione del grigio: l'**integrale lungo un cammino circolare** della **derivata radiale**.

> ⚠️ **Formula ricostruita.** Nel PDF l'operatore è un'immagine. La forma pubblicata da Daugman, coerente con i simboli spiegati nella slide, è:
>
> $$\max_{(r,\, x_0,\, y_0)} \left| G_\sigma(r) * \frac{\partial}{\partial r} \oint_{r,\, x_0,\, y_0} \frac{I(x, y)}{2\pi r}\, ds \right|$$

| Simbolo | Significato (slide) |
|---|---|
| $(x_0, y_0)$, $r$ | centro e raggio della circonferenza esplorata |
| $I(x, y)$ | immagine **raw** dell'iride |
| $\oint \frac{I}{2\pi r} ds$ | **media del grigio** lungo la circonferenza |
| $\frac{\partial}{\partial r}$ | **variazione** di quella media al crescere del raggio |
| $G_\sigma(r)$ | **prefiltraggio passa-basso gaussiano** di raggio $\sigma$: elimina dalla ricerca i **cerchi dei piccoli riflessi** più piccoli di $\sigma$ |

> 📌 **Interpretazione.** L'operatore si comporta come un **rilevatore di bordi circolari** (*circular edge detection*): produce valori **alti** quando la circonferenza esplorata con centro $(x_0, y_0)$ e raggio $r$ passa vicino a un **cerchio reale** dell'immagine, dove il grigio cambia bruscamente (confine pupilla-iride, confine iride-sclera). Il massimo su tutti i centri e raggi individua i due confini.

<!-- SOURCE_VISUAL id="TAB04-V09" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="25" type="formula con schema" description="Operatore integro-differenziale di Daugman con centro x0, y0 e raggio r" reason="Verificare la formula, presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4 Rimozione di palpebre e ciglia**

_(slide p. 27)_

- Solo **una parte** dell'iride è utile al riconoscimento.
- Si segmenta la parte utile **marcando** le zone con **ciglia**, **palpebre**, **riflessi esterni**, zone a **basso contrasto** o **sfocate**.
- Se **manca più del 50%** dell'iride occorre **riacquisire**.
- Casi difficili: **pupilla non circolare**, **palpebre sovrapposte**, **ciglia sovrapposte**.

<!-- SOURCE_VISUAL id="TAB04-V10" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="27" type="esempi di occhi" description="Casi difficili: pupilla non circolare, palpebre sovrapposte, ciglia sovrapposte" reason="Mostra le occlusioni da marcare" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.5 Attacco al sensore dell'iride del Samsung S8**

_(slide p. 28)_

La slide descrive un attacco che riuscì con estrema semplicità: si **stampa l'immagine dell'iride**, si appoggia una **lente a contatto** sul foglio per simulare la curvatura dell'occhio e si **mostra il foglio al telefono**: «done!».

> 💡 **Perché funziona.** Il sensore del telefono acquisiva nel vicino infrarosso e verificava soprattutto la **tessitura**. Una stampa di buona qualità ripropone la tessitura; la lente aggiunge il riflesso curvo della cornea. Mancando controlli di **vitalità** (per esempio la reazione della pupilla alla luce), il sensore non distingueva l'occhio vero dalla replica. Le contromisure sono trattate nella Lezione 20.

<!-- SOURCE_VISUAL id="TAB04-V11" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="28" type="fotografie" description="Attacco al sensore iride del Samsung S8 con stampa dell'iride e lente a contatto" reason="Documenta visivamente l'attacco" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.6 Linearizzazione (*unwrapping*)**

_(slide p. 30)_

Individuati raggi e centri, si procede alla **linearizzazione**: la **corona circolare** dell'iride viene trasformata in un **rettangolo** in coordinate polari (raggio $\rho$, angolo $\theta$).

- Si fissano le dimensioni dei **settori**: **numero di corone** e **delta dell'angolo di scansione**.
- Ogni pixel dell'iride linearizzata nasce da un'**interpolazione** del settore corrispondente nell'iride originale.

Esempi della slide: **(A)** 180 passi angolari × 73 corone; **(B)** 128 passi × 8 corone.

> 💡 **Verifica sulla dimensione dell'IrisCode.** Con la configurazione (B) si ottengono $128 \times 8 = 1024$ posizioni; ogni posizione produce **2 bit** (§5.7), quindi $1024 \times 2 = 2048$ bit $= 256$ byte, esattamente la dimensione dell'IrisCode.

> 📌 **Perché si linearizza.** La pupilla si dilata e si contrae e la distanza dall'obiettivo cambia. Il cambio di coordinate normalizza queste variazioni: qualunque sia la dimensione dell'iride nell'immagine, il rettangolo ha sempre le stesse dimensioni. È il **modello a foglio di gomma** (*rubber sheet model*) di Daugman.

<!-- SOURCE_VISUAL id="TAB04-V12" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="30" type="schema" description="Unwrapping dell'iride in coordinate polari con settori (A) 180 passi x 73 corone e (B) 128 passi x 8 corone" reason="La geometria dell'unwrapping è visiva" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.7 Calcolo dell'IrisCode con le Gabor wavelet 2D**

_(slide pp. 31–32)_

L'iride linearizzata $I(\rho, \theta)$ viene **convoluta** con **wavelet di Gabor 2D**, cioè funzioni **gaussiane** modulate da un'oscillazione complessa, che fungono da **basi della demodulazione**. Per ogni posizione si ottiene un numero complesso $h$, e si registrano **due bit**: il **segno della parte reale** (componente **pari**) e il **segno della parte immaginaria** (componente **dispari**).

> ⚠️ **Formula ricostruita.** La formula della slide è un'immagine. Nella forma pubblicata da Daugman:
>
> $$h_{\{Re,\,Im\}} = \operatorname{sgn}_{\{Re,\,Im\}} \iint_{\rho,\,\phi} I(\rho, \phi)\, e^{-i\omega(\theta_0 - \phi)}\, e^{-(r_0 - \rho)^2/\alpha^2}\, e^{-(\theta_0 - \phi)^2/\beta^2}\, \rho\, d\rho\, d\phi$$
>
> dove, come nella slide, $\omega$ è la **frequenza** della wavelet e $\alpha$, $\beta$ sono la **larghezza del filtro** in direzione radiale e angolare; $(r_0, \theta_0)$ è la posizione analizzata.

Daugman descrive il procedimento così (p. 32): il pattern dell'iride viene codificato in un IrisCode da **256 byte** **demodulandolo** con wavelet di Gabor 2D, che rappresentano la tessitura come **fasori nel piano complesso**. L'**angolo** di ogni fasore viene **quantizzato nel solo quadrante** in cui si trova, per ogni elemento locale, e l'operazione è ripetuta su tutta l'iride a **molte scale** di analisi.

| Quadrante del fasore | bit Re | bit Im |
|---|---|---|
| I | 1 | 1 |
| II | 0 | 1 |
| III | 0 | 0 |
| IV | 1 | 0 |

<!-- SOURCE_VISUAL id="TAB04-V13" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="31" type="formula e schema" description="Calcolo dell'iris code: iride linearizzata I(rho, theta) convoluta con wavelet gaussiane, segno della parte reale (pari) e immaginaria (dispari)" reason="Verificare la formula della demodulazione, presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB04-V14" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="32" type="diagramma del piano complesso" description="Quantizzazione dell'angolo del fasore nel quadrante: parte reale e parte immaginaria di h" reason="Mostra la codifica in 2 bit per quadrante" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.8 Proprietà dell'IrisCode**

_(slide p. 29)_

- L'unica informazione estratta dipende dalla **fase**: questo rende l'IrisCode **invariante** a **contrasto**, **livello medio di grigio** e **illuminazione**.
- La descrizione di fase è **molto compatta**: **256 byte** per l'iride, più **256 byte di controllo** (la **maschera**) per escludere i bit nati dove l'immagine conteneva **artefatti**: riflessi delle luci ambientali (in realtà sulla cornea), ciglia sovrapposte, palpebre, regioni con troppo poco contrasto e quindi con dati deboli.
- La probabilità che ogni bit valga 1 è del **50%**: l'IrisCode è un codice a **massima entropia**.

> 💡 **Perché la fase è invariante.** Moltiplicare l'immagine per una costante (più contrasto) o aggiungere una costante (più luce) cambia il **modulo** della risposta del filtro, ma non il suo **angolo**. Tenendo solo il quadrante dell'angolo, queste variazioni scompaiono.

#### **5.9 Matching con la distanza di Hamming**

_(slide pp. 33–35)_

La comparazione tra due IrisCode $A$ e $B$ usa la **distanza di Hamming**:

$$HD = \frac{1}{N} \sum_{j=1}^{N} A_j \oplus B_j$$

- $\oplus$ è lo **XOR**: vale 1 dove i bit **differiscono**;
- la distanza **conta i bit in disaccordo** e li **normalizza** sul totale $N$;
- normalmente $N = 2048$ bit, se non ci sono occlusioni.

$HD = 0$ significa codici identici; per due iridi **diverse** i bit sono indipendenti e concordano per caso metà delle volte, quindi $HD \approx 0{,}5$.

Se ci sono **occlusioni** (palpebre, riflessi, ciglia), si preparano **maschere di oscuramento** $maskA$ e $maskB$ delle zone senza informazione utile, e le si escludono dal calcolo:

$$HD = \frac{\big\| (A \oplus B) \cap maskA \cap maskB \big\|}{\big\| maskA \cap maskB \big\|}$$

dove $\|\cdot\|$ conta i bit a 1. Il numeratore conta i disaccordi **solo dove entrambe le iridi sono valide**; il denominatore normalizza sul numero di bit **effettivamente confrontati**.

> ⚠️ **Attenzione al denominatore.** Se due iridi molto occluse condividono pochi bit validi, la HD è calcolata su un campione piccolo e diventa **statisticamente poco affidabile**. È una delle ragioni della regola «se manca più del 50% dell'iride, riacquisire».

<!-- SOURCE_VISUAL id="TAB04-V15" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="34" type="diagramma a flusso" description="Creazione delle maschere: segmentazione occlusioni, linearizzazione, iriscode processing, iriscode A e maskA" reason="Mostra dove nasce la maschera nella pipeline" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Distribuzioni e bit significativi**

_(slide pp. 36–39)_

#### **6.1 Distribuzioni degli score**

_(slide pp. 36–37)_

La slide mostra la distribuzione tipica del match score degli IrisCode:

| Esperimento | Confronti | Condizioni |
|---|---:|---|
| sopra (*non-ideal imaging*) | **2,3 milioni** | diverse acquisizioni per occhio, **diversi sensori**, **7070 persone** |
| sotto (*ideal imaging*) | **482.600** | stesso sensore, **laboratorio**, condizioni ideali |

Sono distribuzioni **ideali** per un sistema biometrico: genuini e impostori sono **perfettamente separabili**.

**Perché la distribuzione degli impostori non è simmetrica** (p. 37): per contrastare le non idealità dell'acquisizione, il matching è stato eseguito con **7 shift** (rotazioni del codice) scegliendo lo **score minore**. Senza shift, la HD tra impostori seguirebbe una **binomiale**, che per $p = 0{,}5$ è **simmetrica**; prendere il minimo di 7 valori sposta la distribuzione verso sinistra e la rende asimmetrica.

> ⚠️ **Stime ottimistiche.** Le stime ricavate da queste distribuzioni sono **ottimistiche**: la vera probabilità di False Match **dipende dalla qualità delle ottiche**. Occorre **sempre un test sul campo**. Detto questo, i sistemi basati su IrisCode restano ad oggi i sistemi biometrici **«livescan» più accurati** esistenti.

> 💡 **Perché gli shift.** Una rotazione della testa sposta il pattern lungo l'asse angolare del rettangolo linearizzato: ruotare circolarmente l'IrisCode e prendere la distanza minima compensa la rotazione. Il prezzo è che anche due impostori ottengono il **migliore** di 7 tentativi, il che abbassa un po' le loro distanze.

<!-- SOURCE_VISUAL id="TAB04-V16" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="36" type="istogrammi" description="Distribuzioni della Hamming distance per genuini e impostori: 2,3 milioni di confronti non-ideal su 7070 persone e 482.600 confronti ideal" reason="Mostra la separazione delle distribuzioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB04-V17" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="37" type="istogramma" description="Distribuzione asimmetrica degli impostori dovuta ai 7 shift con score minimo, confronto con binomiale simmetrica per p=0,5" reason="Spiega visivamente l'asimmetria" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Quanti bit significativi?**

_(slide p. 39)_

- **Ipotesi di partenza**: confrontare IrisCode di impostori equivale a confrontare **stringhe casuali** di $N$ bit.
- I bit dell'IrisCode sono però **correlati** (pixel vicini producono bit simili): il numero di bit **effettivamente indipendenti**, cioè di **gradi di libertà**, è molto minore di 2048.
- Se cambiano le condizioni operative il **tipo** di distribuzione degli impostori di solito **non cambia**, ma ne cambiano i **parametri**, e quindi il **numero di bit significativi**.
- La slide riporta **249 bit** per l'infrarosso con utenti cooperativi e compara il caso nel visibile con utenti non cooperativi; in ogni caso **restano moltissimi** bit significativi.

> ⚠️ **Da verificare.** Il livello testo associa l'etichetta «249 bit» a entrambe le condizioni (IR cooperativo e VIS non cooperativo). Il valore di 249 gradi di libertà è quello classico di Daugman per l'IR; il valore per il visibile va letto sul grafico della slide.

<!-- SOURCE_VISUAL id="TAB04-V18" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="39" type="grafici di distribuzioni" description="Distribuzioni degli impostori e numero di bit significativi: IR con utenti cooperativi e VIS con utenti non cooperativi" reason="Verificare i gradi di libertà nelle due condizioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Iride nel visibile, watch list e privacy**

_(slide pp. 38, 40–50)_

#### **7.1 Iride nel visibile**

_(slide pp. 38, 40–42)_

L'estensione del riconoscimento dell'iride al **visibile**, unita alla capacità di ottenere un **FMR estremamente basso**, può provocare **grandi problemi di privacy** (p. 38).

- **Con luce ambientale** (p. 40): è **possibile**, ma con **maggiore variabilità**, che rende il problema più complesso. Nel visibile **non sempre** si possono usare flash e sorgenti luminose adeguate.
- **Visibile contro NIR** (p. 41): nel visibile ci sono **molte feature sfruttabili**; il NIR offre **meno dettagli** ma una migliore **indipendenza dal colore** dell'occhio.
- **Occhi scuri** (p. 42): nel visibile sono **più problematici** e offrono **meno dettagli** utili, perché la melanina assorbe la luce visibile (§4.1).

<!-- SOURCE_VISUAL id="TAB04-V19" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="41" type="confronto di immagini" description="Stessa iride acquisita nel visibile (molte feature) e nel NIR (meno dettagli, indipendenza dal colore)" reason="Confronto visivo tra le due bande" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 Watch list**

_(slide p. 43)_

- Le frodi con **carte di credito** e il **furto d'identità** possono essere affrontati efficacemente con le **impronte**; l'iride si presta meglio ad applicazioni con **livelli di sicurezza e dimensioni maggiori**.
- Il **bassissimo FMR** («dire che tu sei un altro») rende l'iride **perfetta per la scansione di enormi DB**, anche nazionali.
- Ogni anno **decine di migliaia** di «indesiderabili», persone **espulse** o che usano **alias** per nascondere la vera identità, attraversano le frontiere di paesi come Italia, Gran Bretagna e Stati Uniti.
- Attualmente l'iride è **l'unico sistema** che offre la **scansione in tempo reale** (pochi secondi) di una singola iride contro un DB di **milioni** di iridi o una **watch list centralizzata**.

#### **7.3 Il caso Sharbat Gula**

_(slide pp. 44–49)_

Il fotografo **Steve McCurry**, in Afghanistan per documentare la guerra civile, si trova casualmente in una scuola femminile del campo profughi di **Nasir Bagh** (Pakistan, al confine con l'Afghanistan) e scatta alcune foto: tra queste il ritratto di **Sharbat Gula** (**1985**). Nel **2002** una donna viene ritrovata e **John Daugman** confronta le iridi delle due fotografie.

Facendo lo **XOR** tra l'IrisCode vecchio e quello nuovo, la distanza di Hamming risulta:

| Occhio | $HD$ |
|---|---:|
| **sinistro** | **0,24** |
| **destro** | **0,31** |

Le statistiche di qualità calcolate sulle due immagini dell'occhio sinistro (p. 49):

| Misura | Immagine 1 | Immagine 2 |
|---|---:|---:|
| Focus quality | 97 | 83 |
| Area dell'iride visibile tra le palpebre | 86% | 56% |
| Primo quartile (energia della tessitura) | 4,50539 | 5,24182 |
| Centro della pupilla | (225, 422) | (208, 265) |
| Centro dell'iride | (225, 424) | (206, 274) |
| Contrasto del bordo esterno dell'iride | 1,44080 | 2,09098 |
| Nitidezza del bordo pupillare | 0,673407 | 0,377510 |
| Diametro della pupilla | 54 px | 98 px |
| Diametro dell'iride | 172 px | 358 px |
| Rapporto pupilla/iride | 0,313953 | 0,273743 |

> ⚠️ **Quale immagine è quale.** Il livello testo non indica quale colonna corrisponda al 1985 e quale al 2002: va verificato sulla slide. Si nota comunque che la seconda immagine ha una risoluzione sull'iride doppia (358 px contro 172 px) ma solo il 56% dell'iride visibile.

<!-- SOURCE_VISUAL id="TAB04-V20" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="45" type="fotografie" description="Sharbat Gula nel 1985 e nel 2002: dettaglio degli occhi usati da Daugman" reason="Immagini del caso di studio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB04-V21" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="46" type="fotografie con segmentazione" description="Riconoscimento di Sharbat Gula: segmentazione delle iridi e iris code" reason="Mostra la segmentazione eseguita sulle foto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB04-V22" source="Lezione_04_Convoluzione_Approfondimento_Iride.pdf" page="49" type="iris code con statistiche" description="Iris code delle due immagini dell'occhio sinistro con focus quality, area visibile, diametri e contrasti" reason="Associare ogni colonna di statistiche alla sua foto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4 Come interpretare $HD = 0{,}24$**

_(slide p. 48)_

La **corretta interpretazione** del risultato, con un solo occhio:

1. se tra due IrisCode si trova $HD = 0{,}24$ e, per quella distanza, la **binomiale** degli impostori corrisponde a una probabilità **$< 10^{-14}$**,
2. allora trovare **due persone diverse** che per caso differiscono solo nel 24% dei bit è un evento con probabilità $< 10^{-14}$;
3. cioè **1 individuo su più di $10^{14}$** (centomila miliardi, $10^5 \cdot 10^9$);
4. con **8 miliardi** di persone sulla Terra servirebbero $10^{14} / (8 \cdot 10^9) = 12.500$ **pianeti** popolati come la Terra per avere la stessa probabilità di trovare per caso un candidato così vicino.

**Oppure è proprio Sharbat Gula.**

> 💡 **Da dove viene $10^{-14}$ (ordine di grandezza).** Con $N = 249$ gradi di libertà e $p = 0{,}5$, la HD tra impostori ha media $0{,}5$ e deviazione standard $\sqrt{p(1-p)/N} = \sqrt{0{,}25/249} \approx 0{,}032$. Il valore $0{,}24$ dista dalla media $(0{,}5 - 0{,}24)/0{,}032 \approx 8{,}2$ deviazioni standard: una coda così lontana ha probabilità dell'ordine indicato dalla slide.

> ⚠️ **Non è la probabilità che sia Sharbat Gula.** $10^{-14}$ è la probabilità che **un impostore** ottenga una distanza così bassa. Il ragionamento dice che l'ipotesi «persona diversa» è estremamente improbabile, non assegna direttamente una probabilità all'identità.

#### **7.5 Problemi di privacy**

_(slide p. 50)_

L'exploit di Daugman, il **riconoscimento dell'iride da foto ad alta risoluzione nel visibile a 18 anni di distanza**, mostra il pericolo di uno **screening di massa** a partire dagli **archivi di foto** (governativi, social, …). I fattori chiave:

- **miglioramento** delle tecniche di segmentazione ed estrazione dell'IrisCode **nel visibile**;
- foto con **ottiche dedicate**, **risoluzione crescente** e **fuoco perfetto sul volto** (si pensi ai selfie);
- capacità dell'IrisCode di raggiungere **FMR anche di $10^{-7}$**.

> ⚠️ **Enorme problema di privacy nel futuro.** Chi pubblica una foto nitida del proprio volto potrebbe pubblicare, senza saperlo, un tratto biometrico sufficiente a identificarlo in un database di milioni di persone. La slide scrive «1885» sotto la foto giovanile: è un refuso per **1985**.

---

### **8. Altri tratti monomodali e simulazione d'esame**

_(slide pp. 51–53)_

Per la loro rilevanza e diffusione crescente, il corso approfondirà anche: **volto**, **palmo**, **soft biometrics**, **segnali biometrici cardiaci** e **videosorveglianza** (p. 51).

**Prima breve simulazione d'esame** (p. 52): durante il corso ci saranno altri momenti con esempi di domande e loro risoluzione. Le simulazioni parziali **non vengono rilasciate** in PDF; al termine dell'insegnamento, nell'ultima lezione, si svolgerà una **simulazione integrale** con correzione e discussione, e verrà distribuita solo la **griglia** di risposta.

---

### **9. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Convoluzione** / **Correlazione** | kernel ribaltato / kernel non ribaltato (uguali per kernel simmetrici) |
| **Luce visibile** / **NIR** | strati dell'iride, melanina assorbe / tessitura distintiva, indipendenza dal colore |
| **Integro-differenziale** / **Segmentazione classica** | massimo della derivata radiale su cerchi, robusto / soglie e binarizzazione, fragile |
| **IrisCode** / **Maschera** | 256 byte di bit di fase / 256 byte che escludono i bit su artefatti |
| **Modulo** / **Fase** della risposta di Gabor | dipende da contrasto e luce / invariante, usata nell'IrisCode |
| **$HD \approx 0$** / **$HD \approx 0{,}5$** | stessa iride (o replay) / iridi diverse |
| **Bit totali** / **Bit significativi** | 2048 / circa 249 gradi di libertà (bit correlati) |
| **P(impostore con HD ≤ 0,24)** / **P(identità)** | probabilità di un falso match / non è la probabilità che sia la stessa persona |

---

### **10. Sintesi della lezione**

_(slide p. 53)_

> ✅ **In sintesi.**
> - **Convoluzione**: $O = I * k$, somma pesata dell'intorno, operazione lineare alla base del filtraggio e delle CNN.
> - **Iride**: il tratto più accurato dopo il DNA, contactless, unico anche nei gemelli; difficile da acquisire (bersaglio piccolo, mobile, parzialmente occluso).
> - **Acquisizione**: NIR 700–900 nm, almeno 70 pixel sul raggio, CCD monocromatici almeno 640×480.
> - **Daugman**: operatore integro-differenziale, rimozione delle occlusioni, linearizzazione, Gabor wavelet 2D, IrisCode di 256 byte più maschera, distanza di Hamming con maschere e shift.
> - **Distribuzioni** perfettamente separabili in laboratorio ma stime ottimistiche: serve il test sul campo; circa 249 bit significativi.
> - **Attacco al Samsung S8** con stampa e lente a contatto.
> - **Iride nel visibile**, watch list e caso **Sharbat Gula** ($HD = 0{,}24$, probabilità $< 10^{-14}$): enorme problema di privacy futuro.
