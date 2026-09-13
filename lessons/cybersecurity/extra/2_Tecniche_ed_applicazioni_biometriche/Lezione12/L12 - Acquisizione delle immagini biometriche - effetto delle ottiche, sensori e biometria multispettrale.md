# **Lezione 12: Acquisizione delle immagini biometriche — effetto delle ottiche, sensori e biometria multispettrale**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

La Lezione 12 completa lo studio dell'acquisizione iniziato nella Lezione 11. È organizzata in quattro blocchi:

1. **Parte A** — l'**effetto delle ottiche** sul riconoscimento del volto, misurato con DeepFace;
2. **Parte B** — i **sensori**: immagine digitale, bit per pixel, CCD e CMOS, global e rolling shutter, aliasing, sensori ad alta risoluzione;
3. **Parte C** — **radiazione e ottica**: filtri, polarizzazione e **biometria multispettrale**;
4. **Parte D** — **sistemi di acquisizione** reali e casi d'uso.

> ⚠️ **Numerazione interna delle slide.** Il PDF riunisce parti provenienti da versioni precedenti del corso: alcune sintesi interne sono etichettate «L12b», «L13» e «L14». Appartengono tutte a questa lezione.

---

## **Parte A — Effetto delle ottiche sul riconoscimento del volto**

### **1. Il metodo**

_(slide pp. 3–5)_

Si usa **DeepFace** per avere un'idea dell'impatto della **scelta delle ottiche** (e dell'**estrattore**) sul riconoscimento:

- si prendono **volti della stessa persona** ripresi con **focali diverse**;
- si studia la **distanza intraclasse**, cioè tra immagini dello stesso individuo.

> ⚠️ **È un esempio del metodo, non uno studio completo.** Per uno studio vero servono **molti più dati**, che simulino quello che accade nella propria applicazione.

Si caricano le librerie `deepface`, `opencv-python`, `numpy`, `matplotlib` e la funzione `distance` di SciPy.

---

### **2. Embedding e matrice delle distanze**

_(slide pp. 6–13)_

La procedura:

1. si **estraggono gli embedding** di tutte le immagini con DeepFace (pp. 6–7) e se ne costruisce la **lista** (p. 8);
2. si **calcola la distanza** tra ogni coppia di immagini (p. 9);
3. si traccia la **matrice delle distanze** come mappa di calore (p. 10);
4. si esamina la **prima riga** della matrice, cioè la distanza della prima immagine da tutte le altre (p. 11);
5. si esamina l'**angolo in basso** della matrice (p. 12).

**Il materiale di laboratorio** (cartella della lezione): lo script `effetto_ottiche.py`, export di un notebook Colab intestato «Corso di Complementi di biometria — Acquisizioni di immagini biometriche: ottiche e sensori», calcola gli embedding **VGG-Face** di sei immagini, costruisce la **matrice 6×6 delle distanze euclidee** e la disegna come **heatmap annotata**.

| Immagine | Soggetto | Focale |
|---|---|---|
| `girl2_f24.jpg` | ragazza | 24 mm |
| `girl2_f50.jpg` | ragazza | 50 mm |
| `girl2_f85.jpg` | ragazza | 85 mm |
| `girl2_f200.jpg` | ragazza | 200 mm |
| `man_f16mm.jpg` | uomo | 16 mm |
| `man_f200mm.jpg` | uomo | 200 mm |

Nella cartella ci sono anche `girl_f14mm.jpg` e `girl_f300mm.jpg`, non usate dallo script ma utili per estendere l'esperimento. Con quest'ordine, la **prima riga** confronta la foto a 24 mm con tutte le altre, e l'**angolo in basso a destra** contiene la distanza tra le due foto dell'uomo a **16 mm** e **200 mm**, le focali più diverse.

**Risultati** (p. 13). Le distanze annotate sulla slide sono **0,43**, **0,83**, **0,35** e **0,24**; le etichette leggibili associano le coppie **f200–f24** e **f24–f50**.

> 💡 **Come leggerle.** La distanza più alta (0,83) corrisponde al confronto tra focali molto diverse (24 mm contro 200 mm), mentre focali vicine (24 mm e 50 mm) danno una distanza molto più bassa (0,35). L'abbinamento esatto di ogni valore alla coppia va verificato sulla slide: il livello testo non lo preserva.

> ⚠️ **Perché la focale cambia l'embedding.** Come visto nella Lezione 11 (§8.3), a focale corta la camera è vicina e la prospettiva ingrandisce il naso e rimpicciolisce le orecchie; a focale lunga le proporzioni cambiano in modo opposto. Per l'estrattore sono **due volti geometricamente diversi**, anche se è la stessa persona.

<!-- SOURCE_VISUAL id="TAB12-V01" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="10" type="heatmap" description="Matrice delle distanze tra gli embedding dei volti ripresi con focali diverse" reason="La matrice numerica è mostrata come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V02" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="12" type="heatmap" description="Angolo in basso della matrice delle distanze con i confronti tra le foto dell'uomo a focali diverse" reason="Verificare i valori del confronto 16 mm contro 200 mm" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V03" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="13" type="coppie di volti con distanze" description="Coppie di volti con distanze 0,43, 0,83 (f200-f24), 0,35 (f24-f50) e 0,24" reason="Associare ogni distanza alla coppia di focali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Estendere lo studio: distribuzioni e DET**

_(slide pp. 14–16)_

Che cosa ci si aspetta nel **proprio database** rispetto a un database con foto **standard a norma ICAO**? Si creano due DB di tipo **scenario**:

- **volti ICAO** (foto standard);
- **volti ripresi con focali diverse**.

I due DB devono avere **cardinalità simile** in numero di immagini. Ci si aspetta che:

1. le distanze dei **genuini peggiorino**, mentre quelle degli **impostori probabilmente no**;
2. di conseguenza la **DET peggiori**.

> 💡 **Perché gli impostori non cambiano molto.** Due persone diverse sono già lontane nello spazio degli embedding: la distorsione prospettica aggiunge poco a una distanza già grande. I genuini, invece, partono vicini, e la distorsione li allontana verso la soglia: cresce il FNMR a parità di soglia.

> 📌 **L'effetto delle ottiche** (p. 15). Anche usando l'estrattore più potente della libreria, **VGG-Face**, che contiene tecniche di **frontalizzazione e compensazione**, l'uso di **ottiche troppo diverse** può **aumentare più del doppio** la distanza tra genuini. (Richiamo: VGG-Face raggiunge il 97,78% su LFW, Lezione 9.)

**Compito «Effetto ottiche»** (p. 16): eseguire un'analisi comparativa tra gli estrattori **VGG-Face** e **DeepID**, quest'ultimo noto per gestire bene angolazioni, illuminazione ed espressioni. Il compito segue le regole delle Lezioni 9 e 11 (non è progetto d'esame e non dà punti).

<!-- SOURCE_VISUAL id="TAB12-V04" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="14" type="grafici" description="DB ICAO contro DB con focali diverse: distribuzioni dei genuini peggiorate e DET peggiorata" reason="Mostra l'effetto atteso sulle distribuzioni e sulla DET" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Sensori e acquisizione biometrica**

### **4. Il setup di acquisizione**

_(slide pp. 17–24)_

L'outline della parte: introduzione alle applicazioni basate su immagini, l'immagine digitale, bande ottiche e applicazioni, principi di ottica e distorsioni, applicazioni biometriche. Nella mappa delle conoscenze l'attenzione è su **ottica e dispositivi** (p. 19).

**Tratti acquisiti con immagini** (p. 21) — un **tema fondamentale**:

| Tratti fisiologici | Tratti comportamentali |
|---|---|
| iride, regione perioculare, volto | camminata |
| impronte e palmo **senza contatto** | espressioni, emozioni |
| soft biometrics (età, genere, classe) | labbra, comportamento |

**Elementi principali di un setup** (p. 22):

| # | Elemento |
|---:|---|
| 1 | **camera** |
| 2 | **ottica** |
| 3 | **cavo** |
| 4 | **interfaccia** |
| 5 | **PC o DSP** |
| 6 | **illuminatore** |
| 7 | **oggetto** (il tratto) |

Un setup «semplice» (p. 23) ha **un sensore con ottica** e **un illuminatore**, spesso a **LED infrarossi** non visibili.

L'**acquisizione dell'immagine** è il **primo passo** della catena di elaborazione (p. 24), seguita da miglioramento, restauro, elaborazione del colore, compressione, elaborazione morfologica, segmentazione, rappresentazione e descrizione, riconoscimento: un errore qui si propaga a tutti i passi successivi.

<!-- SOURCE_VISUAL id="TAB12-V05" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="22" type="schema" description="Setup di acquisizione: camera, ottica, cavo, interfaccia, PC/DSP, illuminatore, oggetto" reason="Schema dei sette elementi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. L'immagine digitale: pixel, bit e colore**

_(slide pp. 25–39)_

#### **5.1 Pixel e profondità**

_(slide pp. 25–27)_

> 📌 **Immagine digitale.** Rappresentazione di un'immagine bidimensionale come insieme **finito** di valori digitali chiamati **pixel**.

- Un'immagine a **8 bit** per canale usa il tipo **unsigned char** (valori da 0 a 255): una rappresentazione **compatta ma potente** per un singolo canale, realizzata per esempio da un sensore **CMOS o CCD**.
- Con **4 bit** (16 livelli) la **quantizzazione uniforme** produce false bande; una **quantizzazione ottimale** (livelli scelti in base alla distribuzione dei grigi) va meglio; con **8 bit** l'immagine è fluida.

#### **5.2 Spazi colore e numero di colori**

_(slide pp. 28–29)_

- Nel **1931** la **CIE** (*Commission Internationale de l'Éclairage*) ha definito uno spazio colore che comprende **tutte le tinte visibili** dall'occhio umano, indipendentemente dalla luminanza, da circa **700 nm** (rosso vicino all'infrarosso) a **380 nm** (vicino all'ultravioletto), con **3 colori primari**.
- L'occhio umano riconosce al massimo circa **10 milioni di colori**.
- Schermi e TV tradizionali usano **8 bit per componente** (256 livelli): in totale $256^3 = 2^{24} \approx 16{,}7$ milioni di colori.
- Pannelli a **10 bit** (per esempio TV QLED) arrivano a $2^{30} \approx 1{,}07$ miliardi di colori, molto oltre la percezione umana.

> 💡 **Ma che cosa serve per la biometria?** La domanda della slide è centrale. Più colori di quelli che l'occhio distingue non servono a **vedere** meglio, ma possono servire all'**algoritmo**: le differenze di tono troppo fini per l'occhio contengono informazione utile ai filtri di miglioramento (§5.3).

#### **5.3 Quantizzazione e rumore**

_(slide pp. 30–32)_

- Gli **effetti della quantizzazione** sono visibili anche a 8 bit in alcune condizioni (p. 30).
- **Rumore da quantizzazione** (p. 31): **molti algoritmi di enhancement biometrici esaltano le differenze** tra pixel vicini; se il rumore è presente, l'algoritmo lo **amplifica**: rumore → algoritmo → **più rumore**.
- Il confronto tra immagini a **8 e 10 bit per pixel** (p. 32) mostra l'effetto sul rumore.

> 💡 **Esempio numerico (nota didattica).** Una zona in cui la luminosità varia dolcemente da 100,0 a 100,6 viene quantizzata a 8 bit tutta a 100: un filtro che esalta le differenze non vede nulla. Con 10 bit la stessa zona ha 4 livelli distinti (400, 401, 402) e la variazione diventa sfruttabile.

<!-- SOURCE_VISUAL id="TAB12-V06" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="31" type="confronto di immagini" description="Rumore da quantizzazione amplificato dagli algoritmi di enhancement" reason="Evidenza visiva dell'amplificazione del rumore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4 Confronto tra sensori**

_(slide pp. 33–37)_

L'esempio delle pp. 33–35 confronta una **webcam** e un **sensore avanzato a 10 bit** sulla stessa mano:

| Sensore | Risultato |
|---|---|
| webcam: **minore risoluzione**, **8 bit** in grigio | si vedono solo i **tratti più macroscopici** |
| sensore avanzato a **10 bit** | dopo il **filtraggio** emerge il **pattern delle vene** |

**Sensori a contatto** per impronte e palmi (p. 36): le feature importanti del sensore sono **livello di rumore**, **numero di bit per pixel** e **frame rate**.

**Scanner per impronte latenti** (p. 37): le latenti hanno **qualità bassa**, quindi **gamma** e **bit per pixel** dello scanner diventano critici. Esempio: scanner **Epson Perfection V600 Photo** con profondità di scansione del colore di **48 bit**.

<!-- SOURCE_VISUAL id="TAB12-V07" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="35" type="confronto di immagini" description="Webcam a 8 bit contro sensore a 10 bit: pattern delle vene visibile solo dopo il filtraggio dell'immagine a 10 bit" reason="Mostra il vantaggio dei bit per pixel" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.5 Pick two: il compromesso dei sensori**

_(slide pp. 38–39)_

Quattro caratteristiche **molto utili** per la biometria, ma **spesso contrapposte**:

| Caratteristica | Unità |
|---|---|
| **risoluzione** | pixel × pixel |
| **bit di colore per pixel** | bit |
| **frequenza di campionamento** (frame rate) | Hz |
| **costo** | euro |

La slide le riassume con il motto **«pick two»**: se ne possono ottimizzare due, raramente tutte.

| Scelta | Beneficio |
|---|---|
| immagini più **larghe e risolute** | si acquisiscono **più tratti** nella stessa scena |
| immagini con **più bit per pixel** | **migliori filtraggi** |
| immagini con **più risoluzione** | analisi su **più scale** |

<!-- SOURCE_VISUAL id="TAB12-V08" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="38" type="schema" description="Pick two: risoluzione, bit per pixel, frame rate e costo come caratteristiche contrapposte" reason="Schema riassuntivo del compromesso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Tecnologie dei sensori: CCD e CMOS**

_(slide pp. 40–52)_

#### **6.1 Principi di funzionamento**

_(slide pp. 40–43)_

| | **CCD** (*Charge-Coupled Device*) | **CMOS** (*Complementary Metal-Oxide Semiconductor*) |
|---|---|---|
| Principio | funzionamento simile a un **fotodiodo**: la carica generata dai fotoni viene **trasferita** alla circuiteria tramite **pochi nodi di uscita**, in **analogico** | **ogni fotodiodo** è accoppiato al proprio **convertitore**, **riduttore di rumore** e circuiti di **digitalizzazione** |
| Amplificatore | **unico** amplificatore: **collo di bottiglia**, ma **grande uniformità** | uno per pixel: si **gestisce meglio il singolo pixel** |
| Rumore | uniforme | **più localizzato**, quindi **filtrabile meglio** |

<!-- SOURCE_VISUAL id="TAB12-V09" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="42" type="schema" description="Struttura del sensore CCD con trasferimento di carica e unico amplificatore" reason="Schema dell'architettura CCD" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V10" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="43" type="schema" description="Struttura del sensore CMOS con convertitore, riduttore di rumore e digitalizzazione per ogni pixel" reason="Schema dell'architettura CMOS" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Global shutter e rolling shutter**

_(slide p. 44)_

| | **Global shutter** (CCD e alcuni CMOS) | **Rolling shutter** (CMOS) |
|---|---|---|
| Come espone | **tutti i pixel** vengono esposti e letti **simultaneamente**: l'immagine è catturata in **un singolo istante** | i pixel vengono letti **in sequenza**, in genere dall'alto al basso |
| Oggetti in movimento | **nessuna distorsione**: ideale per oggetti rapidi | **distorsioni** (effetto jello, *skewing*) con soggetti o camera in movimento veloce |
| Costo e consumo | **più costosi**, maggiore complessità | **meno costosi**, **meno energia** |

#### **6.3 Smear, blooming e rumore**

_(slide pp. 45–47)_

| Effetto | Descrizione (slide) | Chi vince |
|---|---|---|
| **Smear** | una **striscia di luce** compare sull'immagine per una fonte luminosa forte, come se la luce «scorresse» verticalmente | **CMOS** (architettura e lettura diverse) |
| **Blooming** | la luce **satura** i pixel oltre la capacità massima e l'eccesso di carica **si diffonde ai pixel vicini**: aree bianche senza dettagli | **CMOS** (barriere di isolamento) |
| **Rumore di pattern** | nei CMOS produce **bande orizzontali e verticali** nelle zone scure e **pixel singoli** con intensità diversa; nei sistemi di fascia alta esistono algoritmi di compensazione | **CCD** |
| **Sensibilità in bassa luce** | per il **basso fill factor** (frazione di area del pixel sensibile alla luce) i CMOS sono poco fotosensibili al buio | **CCD** |

<!-- SOURCE_VISUAL id="TAB12-V11" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="45" type="tabella" description="Pro e contro dei sensori CMOS" reason="La tabella dei pro e contro è solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4 CCD o CMOS?**

_(slide pp. 48–49)_

- Il **CMOS** sta diventando sempre più popolare, ma il **CCD** è lontano dall'essere obsoleto.
- Finora i **CCD** hanno garantito **qualità superiore** e **meno rumore**.
- I **CMOS** si distinguono per **consumo energetico** e per **smear e blooming**.
- Con la maggiore disponibilità di CMOS a **otturatore globale**, il CMOS continua a recuperare terreno.
- La slide riporta una stima di **Point Grey**: mercato diviso in circa **75% CCD** e **25% CMOS**. La slide successiva mostra però che il **trend dei CMOS è in costante ascesa**.

> ⚠️ **Dato storico.** La ripartizione 75/25 di Point Grey è una fotografia di diversi anni fa. Oggi il mercato delle camere, inclusi dispositivi mobili, sorveglianza e visione industriale, è **dominato dal CMOS**, come anticipa la slide p. 49; il CCD resta in nicchie scientifiche.

<!-- SOURCE_VISUAL id="TAB12-V12" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="49" type="grafico" description="Trend di mercato dei sensori CMOS in costante ascesa" reason="Valori del trend presenti solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.5 Microlenti, filtri RGB e canale verde**

_(slide pp. 50–51)_

- Sopra ogni fotodetettore sono poste **microlenti** (condensatori di luce) che **aumentano la luce in ingresso**, altrimenti in parte persa sugli elettrodi.
- I **filtri colore** sono disposti a mosaico con **due pixel dedicati al verde** ogni quattro.

> 📌 **Il canale verde è molto interessante per le applicazioni biometriche.** Ha **doppia risoluzione** rispetto a rosso e blu, e la luce verde restituisce un buon **contrasto superficiale** (si ricordi la luce verde del sistema di impronte on-the-fly della Lezione 11, §11.4, e il segnale rPPG, più forte nel verde).

<!-- SOURCE_VISUAL id="TAB12-V13" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="51" type="schema" description="Struttura di un sensore CMOS con filtri RGB e due pixel dedicati al verde" reason="Mostra la disposizione dei filtri colore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.6 Sensori estremi**

_(slide p. 52)_

| Sensore | Caratteristiche (slide) |
|---|---|
| **Sony IMX661** | uno dei più grandi sensori **global shutter** al mondo: **127,68 MP**, pixel da **3,45 µm**, diagonale **56,73 mm**, largo **10 volte** un classico sensore industriale per ottiche C-mount; frame rate **12,9 fps a 14 bit**, **19,6 fps a 12 bit**, **21,8 fps a 10 bit**; costoso |
| **ELP con Sony IMX291** | camera **1080p a 50 fps**, grandangolare, attacco C-mount, economica |

> 💡 **Pick two in numeri.** Il sensore IMX661 sceglie **risoluzione** e **bit per pixel** a scapito di **frame rate** e **costo**; la camera ELP sceglie **frame rate** e **costo** a scapito della risoluzione. Anche dentro lo stesso sensore, meno bit per pixel permettono più frame al secondo, perché la banda di trasmissione è limitata.

---

### **7. Gli effetti del rolling shutter**

_(slide pp. 53–63)_

#### **7.1 Come funziona e dove si trova**

_(slide pp. 53–55)_

Nel rolling shutter l'esposizione avviene **riga dopo riga**, non simultaneamente: **nel frattempo l'oggetto può spostarsi**. È tipico dei **CMOS grandi o economici**; i CMOS **piccoli** (sotto 2–4 megapixel) o molto costosi possono avere global shutter.

> 📌 **Memo.** Il rolling shutter è impiegato nei **CMOS**. Esistono **pochi CMOS con global shutter**; **non esistono CCD con rolling shutter**.

<!-- SOURCE_VISUAL id="TAB12-V14" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="54" type="confronto di immagini" description="Global shutter contro rolling shutter lento e molto veloce su oggetti in movimento; CMOS grandi e piccoli" reason="Evidenza visiva delle distorsioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 I quattro effetti negativi**

_(slide pp. 55–59)_

| Effetto | Causa e aspetto (slide) |
|---|---|
| **Wobble / jello** | la **camera vibra** (aliasing temporale): l'immagine ondeggia come gelatina |
| **Skew** | l'immagine **si piega in diagonale** mentre camera o soggetto si spostano |
| **Aliasing spaziale** (CCD e CMOS) | i pixel sono campionati **violando il teorema del campionamento**, tipicamente inquadrando un pattern con **frequenze spaziali simili al passo dei pixel** |
| **Aliasing temporale** | movimenti significativi degli oggetti in **tempi simili all'esposizione** |
| **Esposizione parziale** (caso di aliasing temporale) | un **flash** acceso solo per parte dell'esposizione illumina **solo alcune righe**; succede con **neon**, effetti **stroboscopici**, **fulmini**, lampi e movimenti molto veloci |

> 💡 **Il rischio per la biometria.** Un volto in movimento ripreso con rolling shutter risulta **inclinato** o deformato: la geometria cambia e gli embedding peggiorano, esattamente come con la distorsione prospettica della Parte A. Per impronte contactless acquisite «al volo» o per la camminata serve un global shutter o un rolling shutter molto veloce.

<!-- SOURCE_VISUAL id="TAB12-V15" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="57" type="esempio" description="Effetto skew del rolling shutter con immagine piegata in diagonale" reason="Esempio visivo dello skew" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V16" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="58" type="confronto di immagini" description="Aliasing spaziale: poco aliasing e molto aliasing su pattern con frequenze simili al passo dei pixel" reason="Evidenza visiva dell'aliasing" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V17" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="59" type="esempio" description="Aliasing temporale ed esposizione parziale con flash su alcune righe" reason="Esempio visivo dell'esposizione parziale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 Correzione e CMOS veloci**

_(slide pp. 60–63)_

- La deformazione del rolling shutter si può **correggere algoritmicamente** a partire da una **sequenza di immagini** (p. 60).
- Un **CMOS con shutter lento** mostra distorsioni evidenti (p. 61); i **CMOS veloci** stanno raggiungendo i CCD anche su questo aspetto: il risultato è **quasi, ma non del tutto**, equivalente a un global shutter (p. 62).
- **Sony a tre strati impilati** (*3-layer stacked*, sensore **Exmor RS**, p. 63): **120 fps su tutti i 19,3 MP**, video *super slow motion* a **960 fps in Full HD** (1920×1080); usato in Sony Xperia, Oppo, Huawei Honor, Xiaomi.

> 💡 **Perché lo stacking accelera.** Impilare fotodiodi, circuiti logici e **memoria** in strati diversi permette di leggere e salvare le righe molto più rapidamente: il ritardo tra la prima e l'ultima riga si riduce e le distorsioni del rolling shutter diventano trascurabili.

<!-- SOURCE_VISUAL id="TAB12-V18" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="61" type="schema e immagini" description="CMOS image sensor con shutter lento e relative distorsioni" reason="Contrasto con i CMOS veloci" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Dimensioni, filtri, stabilizzazione e altissime risoluzioni**

_(slide pp. 64–72)_

#### **8.1 Dimensione del sensore e ottica**

_(slide pp. 64–66)_

La dimensione del sensore determina l'**ottica** da usare (Lezione 11, §7.6). Esempi della tabella:

| Formato | Diagonale (mm) | Larghezza (mm) |
|---|---:|---:|
| Canon EF-S, APS-C | 26,82 | 22,30 |
| fotogramma cinematografico standard 35 mm | 27,20 | 22,0 |
| Blackmagic URSA / URSA Mini 4.6 | 28,20 | 25,34 |
| APS-C (Sony α e E, Nikon DX, Pentax K, Samsung NX, Fuji X) | 28,2–28,4 | 23,6–23,7 |

- **Casing** (p. 65): nella Canon 600D il sensore è protetto e dotato di **pulizia piezoelettrica** della polvere.
- **Filtro passa-basso** (p. 66), detto anche **filtro anti-aliasing**: progettato per eliminare l'**effetto moiré** «sfocando» ciò che raggiunge il sensore. Il **pattern di moiré** si verifica quando la scena contiene **dettagli ripetitivi** (linee, puntini) che **superano o eguagliano la risoluzione** del sensore.

> 💡 **Moiré e anti-spoofing.** La sintesi della slide p. 72 cita il moiré tra gli argomenti utili all'**anti-spoofing**. Chi riprende un volto mostrato su uno **schermo** sovrappone la griglia di pixel dello schermo a quella del sensore: nascono frange di moiré che un volto reale non produce. Rilevarle aiuta a riconoscere i **replay attack** (Lezioni 19–20).

<!-- SOURCE_VISUAL id="TAB12-V19" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="66" type="esempio" description="Effetto moiré su dettagli ripetitivi e filtro passa-basso anti-aliasing" reason="Evidenza visiva del moiré" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2 Stabilizzazione ottica**

_(slide p. 67)_

La tecnologia **Oppo** di **stabilizzazione ottica a cinque assi** sposta su **due assi sia le ottiche sia il CMOS**, oltre alla **rotazione**, emulando uno stabilizzatore professionale: compensa angoli di **3°** con passi di soli **2 µm**.

#### **8.3 Sensori oltre i 100 megapixel**

_(slide pp. 68–71)_

**Samsung ISOCELL Bright HMX** (con Xiaomi, p. 68):

| Caratteristica | Valore |
|---|---|
| risoluzione | **12032 × 9024** a **30 fps** |
| video | **6K** (6016 × 3384) a 30 fps |
| dimensione | **1/1,33″** = 12 × 9,6 mm (più grande dei classici sensori mobile) |
| pixel | **0,8 µm**, tecnologia **ISOCELL Plus** (isolamento avanzato dei pixel) |
| ADC | **10 bit** |
| prezzo di vendita | circa **5,84 €** |

**Significato biometrico a 108 MP** (p. 69):

$$30\ \text{fps} \times 108\ \text{MP} \times 8\ \text{bit} = 25{,}92\ \text{Gb/s} = 3{,}24\ \text{GB/s}$$

Con un flusso così grande serve quasi certamente elaborazione dedicata (**DSP, CUDA, ASIC**). In compenso, su 9024 pixel di altezza, nella scena della slide un **volto** occupa **oltre 5000 pixel** e un'**iride oltre 350 pixel**.

> 💡 **Il collegamento con la Lezione 4.** Oltre 350 pixel sull'iride significa più di 175 pixel sul raggio, ben oltre i 70 richiesti per l'IrisCode: con un sensore da smartphone ad altissima risoluzione l'iride diventa acquisibile anche in una foto di gruppo. È lo stesso rischio di privacy del caso Sharbat Gula.

**Il mercato** (p. 70), per esempio Samsung S20 Ultra, Xiaomi Mi Note 10 e 11, Mi Mix 4, S22: camera principale da **108 MP** 1/1,33″ con *super pixel*, **OIS**, **f/1.69**, **FOV 82°**; camera **12 MP** con zoom ottico **2×**; camera **5 MP** con zoom ibrido **10×**; camera **macro 2 MP**.

**ISOCELL 600 MP** (p. 71): **in sviluppo**, con diagonale maggiore del 108 MP e **spessore di 22 mm**: **non** per i cellulari.

<!-- SOURCE_VISUAL id="TAB12-V20" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="69" type="immagine annotata" description="Immagine a 108 MP (12032x9024) con volto oltre 5000 pixel e iride oltre 350 pixel" reason="Mostra le dimensioni in pixel dei tratti nella scena" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V21" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="71" type="fotografia prodotto" description="Sensore Samsung ISOCELL 600 MP in sviluppo" reason="Confronto dimensionale con il sensore da 108 MP" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.4 Sintesi della Parte B**

_(slide p. 72)_

Acquisizione delle immagini; risoluzione, spazi colore e bit per pixel con le implicazioni biometriche; **CCD e CMOS**; **rolling e global shutter**; **aliasing spaziale e temporale**; CMOS veloci (oltre 120 fps); **moiré** (anti-spoofing); sensori di grande formato e loro significato biometrico.

---

## **Parte C — Radiazione, filtri e biometria multispettrale**

### **9. Spettro e filtri ottici**

_(slide pp. 73–76)_

La biometria può usare **bande diverse** dello **spettro elettromagnetico** (p. 74): la slide mostra la stessa scena ai **raggi X**, nell'**ultravioletto**, nel **visibile**, nell'**infrarosso**, nelle **onde radio** e in un'immagine **composita** (p. 75).

> 📌 **Filtro ottico.** Strumento che **trasmette selettivamente** la luce con particolari proprietà: una certa **polarizzazione**, una o più **lunghezze d'onda** (colori), oppure un'**attenuazione** dell'intensità.

<!-- SOURCE_VISUAL id="TAB12-V22" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="75" type="confronto di immagini" description="Stessa scena in banda X-Ray, UV, visibile, infrarosso, radio e composita" reason="Mostra l'informazione diversa per banda" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Il filtro polarizzatore**

_(slide pp. 77–81)_

- Nella **riflessione** la luce **si polarizza**: un filtro polarizzatore, ruotato opportunamente, **attenua la componente riflessa**, mentre i raggi dal fondo, non polarizzati, passano (esempio dei riflessi sull'acqua).
- La **luce del cielo azzurro** è polarizzata, la **nebbia no** (p. 77).
- Il polarizzatore si usa nella fotografia normale ma, applicato alla biometria, dà **risultati interessanti**; può essere **esterno** (avvitato sull'obiettivo) o **interno**, e si usa molto nella **sorveglianza** (pp. 78–79), per esempio per vedere attraverso il vetro di un'auto.
- **Impronte senza contatto e latenti** (p. 80): con il polarizzatore le **creste** si vedono meglio che con l'acquisizione tradizionale.
- **Sensore polarizzato Sony IMX250MZR** (p. 81): integra i **polarizzatori direttamente sul sensore**, a confronto con lo stesso sensore senza polarizzatore.

> 💡 **Perché serve alla biometria.** I riflessi speculari (pelle lucida, cornea, vetro) coprono i dettagli. Eliminandoli si vedono le creste delle impronte e la tessitura dell'iride; inoltre la polarizzazione cambia con il **materiale** riflettente, come visto per le metalenti (Lezione 11, §6.4), e aiuta l'anti-spoofing.

<!-- SOURCE_VISUAL id="TAB12-V23" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="77" type="confronto di immagini" description="Scena con e senza polarizzatore: riflessi polarizzati sull'acqua attenuati, cielo polarizzato, nebbia non polarizzata" reason="Evidenza visiva dell'effetto del polarizzatore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V24" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="79" type="confronto di immagini" description="Filtro polarizzatore applicato alla sorveglianza" reason="Esempio applicativo in sorveglianza" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V25" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="80" type="confronto di immagini" description="Impronta senza contatto con polarizzatore e con acquisizione tradizionale" reason="Mostra il miglioramento delle creste" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V26" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="81" type="confronto di immagini" description="Sensore Sony IMX250MZR con polarizzatore interno contro sensore senza polarizzatore" reason="Esempio di sensore polarizzato" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Filtri multibanda e infrarosso**

_(slide pp. 82–91)_

#### **11.1 Visibile e NIR insieme o separati**

_(slide pp. 82–83)_

- Un **filtro multibanda** lascia passare **più bande** di interesse con un solo elemento, per esempio **visibile + NIR** per la sorveglianza.
- Acquisire **VIS e NIR separatamente** permette di cogliere **nuove feature biometriche**, rendere il sistema **più accurato** e rendere **più difficile lo spoofing**.

#### **11.2 Le bande dell'infrarosso**

_(slide pp. 84–86)_

La slide p. 86 mostra lo stesso volto in **visibile**, **Near IR** (NIR), **Short-Wave IR** (SWIR), **Mid-Wave IR** (MWIR), **Long-Wave IR** (LWIR, termico).

| Banda | Intervallo indicativo (nota didattica) | Che cosa si vede |
|---|---|---|
| **Visibile** | 380–750 nm | colore e tessitura della superficie |
| **NIR** | 0,75–1,4 µm | tessitura dell'iride, vene sotto la pelle; richiede illuminazione |
| **SWIR** | 1,4–3 µm | attraversa foschia; la pelle appare diversa dai materiali artificiali |
| **MWIR** | 3–8 µm | emissione termica e riflessione |
| **LWIR** (termico) | 8–15 µm | **calore emesso** dal corpo, senza illuminazione |

> 💡 **Nota sugli intervalli.** I confini delle bande IR variano leggermente tra le fonti; quelli della tabella sono i valori più comuni e non compaiono nelle slide.

- **Mid-Wave e Far IR** (p. 84, esempi del *Multispectral Imagery Lab* della West Virginia University): il **costo per pixel è altissimo**, quindi si impiegano sensori con **pochi pixel**.
- **Short-Wave IR** (p. 85): immagini del volto acquisite **senza filtro** e con **filtri passa-banda**.

<!-- SOURCE_VISUAL id="TAB12-V27" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="86" type="confronto di immagini" description="Stesso volto in visibile, Near IR, Short-Wave IR, Mid-Wave IR, Long-Wave IR e camera termica" reason="Confronto visivo delle bande infrarosse" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V28" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="85" type="griglia di immagini" description="Volti in Short-Wave Infrared senza filtro e con filtri passa-banda (MILab, West Virginia University)" reason="Esempi di imaging SWIR" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 I canali RGB come filtri e l'iride**

_(slide pp. 87–90)_

> 📌 **I canali della camera sono filtri.** Ogni canale RGB ha una **banda nota**. **Rimuovendo il filtro IR-cut** si ottiene un sensore utile anche nel **vicino infrarosso** (NIR).

Esempi con l'iride:

| Caso | Osservazione (slide) |
|---|---|
| **occhio scuro nel NIR** (p. 88) | la tessitura emerge nel NIR anche se nel visibile l'occhio appare quasi uniforme |
| **occhio chiaro** (pp. 89–90) | le **feature circolari** emergono dal **canale blu**, quelle **radiali** dal **canale rosso** |

> 💡 **Collegamento con la Lezione 4.** La melanina assorbe il visibile: negli occhi scuri il NIR è indispensabile, negli occhi chiari anche i singoli canali colore portano informazione complementare. Usare più canali è una forma semplice di biometria **multispettrale**.

<!-- SOURCE_VISUAL id="TAB12-V29" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="88" type="confronto di immagini" description="Iride di occhio scuro acquisita nel NIR" reason="Mostra la tessitura visibile nel NIR" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V30" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="90" type="confronto di canali" description="Iride di occhio chiaro: feature circolari dal canale blu e radiali dal canale rosso" reason="Confronto visivo dei canali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.4 Sorveglianza giorno/notte**

_(slide p. 91)_

Esempio: un **cambia-filtro IR-cut** integrato con l'attacco dell'obiettivo permette il funzionamento **True Day/Night** (TDN). Di **giorno** il filtro taglia l'IR per avere colori corretti; di **notte** il filtro viene tolto per sfruttare l'IR. Senza filtro, con poca luce, compaiono **falsi colori** nelle immagini.

---

### **12. Impronte multispettrali e usi della biometria multispettrale**

_(slide pp. 92–93)_

**Imaging multispettrale per le impronte** (p. 92): un sensore con funzioni di sicurezza avanzate **scandisce superficie e sottosuperficie** del dito con **diverse lunghezze d'onda** e colori, da **diverse angolazioni**, fino a una **profondità di 4 mm**.

> 💡 **Perché la sottosuperficie è preziosa.** Le creste esistono anche negli strati interni del derma. Un sensore che le legge in profondità funziona con **dita consumate, bagnate o sporche** e rende molto più difficile lo spoofing: un dito finto in gelatina o silicone riproduce la superficie, non la struttura interna.

**Applicazioni della biometria multispettrale** (p. 93):

- **rilevamento degli occhi** e biometria dell'**iride**;
- **riconoscimento del volto fuori dal visibile**;
- **live detection**;
- **anti-spoofing**;
- **sorveglianza giorno/notte**.

<!-- SOURCE_VISUAL id="TAB12-V31" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="92" type="schema e immagini" description="Sensore multispettrale per impronte che scandisce superficie e sottosuperficie fino a 4 mm con lunghezze d'onda e angoli diversi" reason="Schema del principio multispettrale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte D — Sistemi di acquisizione: esempi e casi d'uso**

### **13. Camere con zoom per applicazioni a basso costo**

_(slide pp. 94–100)_

| Esempio | Caratteristiche (slide) |
|---|---|
| **1–8 Mpixel zoom camera** (pp. 95–98) | sistema **general purpose** con **zoom ottico** per applicazioni **low cost**; circa **150 euro**; risoluzione fino a **2640 × 2448** |
| **Tamron MP3010M-EV** (pp. 99–100) | modulo compatto e leggero con **zoom ottico 10×** e funzione **Day/Night** per sorveglianza 24/7; **2 Mpixel**; **frame rate non documentato**; interfacce **HDMI**, uscita **analogica SD** e **LVDS** |

> ⚠️ **Il dato che manca.** La slide sottolinea che per il Tamron il **frame rate non è documentato**: per la biometria è un parametro essenziale (Lezione 11, §10.1). Quando un datasheet omette un dato, va misurato prima di scegliere il componente.

<!-- SOURCE_VISUAL id="TAB12-V32" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="96" type="fotografia prodotto" description="Camera zoom 1-8 Mpixel da 150 euro" reason="Esempio di sistema low cost" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V33" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="100" type="fotografia prodotto" description="Modulo camera Tamron MP3010M-EV con zoom ottico 10x" reason="Esempio di modulo zoom per sorveglianza" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Sistemi multicamera**

_(slide pp. 101–104)_

| Sistema | Soluzione (slide) |
|---|---|
| **iPhone X** (p. 102) | ottiche **panoramiche** e **tele** (per **iride e volto**), **illuminatori IR e bianchi**: un design ottimale non solo per le foto personali ma anche per fare **biometria su terminale mobile** |
| **Huawei P20 Pro, Galaxy A7** (p. 103) | tre camere: sensore **24 MP** con autofocus, **5 MP** «depth lens» per l'effetto **bokeh**, **8 MP** grandangolare con **FOV 120°** |
| **BioEnable EF-45** (p. 104), sistema per l'iride | ottica e illuminatore **RGB per il volto**, usato per il **centraggio**, e **IR per le iridi**, usato per il riconoscimento. Il sistema localizza e mostra il volto fino a **1 metro** su un display a colori; il soggetto **si avvicina naturalmente** fino alla distanza di acquisizione. Ottiche di alta qualità e tempi di esposizione molto brevi superano la specifica **ISO/IEC 19794-6:2011** per le immagini dell'iride; localizza **entrambi gli occhi in 3D in tempo reale** |

> 💡 **Il principio della camera ausiliaria.** Una camera grandangolare **trova** il soggetto e guida il posizionamento, una seconda camera stretta e ad alta qualità **acquisisce** il tratto. È la stessa logica dei due stadi dell'identificazione gerarchica (Lezione 9): uno veloce e ampio, uno preciso e selettivo.

<!-- SOURCE_VISUAL id="TAB12-V34" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="102" type="spaccato" description="Ottiche e illuminatori dell'iPhone X per panoramica, tele, IR e luce bianca" reason="Mostra i componenti multicamera" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB12-V35" source="Lezione_12_Biometric_Image_Acquisition_Ottica_e_Sensori.pdf" page="104" type="fotografia prodotto" description="BioEnable EF-45: camera RGB per il centraggio del volto e camera IR per le iridi" reason="Esempio di sistema iride multicamera" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **15. Conclusioni, approfondimenti e simulazione**

_(slide pp. 105–109)_

**Tante opzioni per nuove applicazioni** (p. 106): dispositivi **CMOS ad alta risoluzione e frame rate**, obiettivi **compatti e velocissimi** anche in autofocus e zoom, **lenti liquide**, **amplissimi range di focali**: un'infinita gamma di nuove applicazioni da realizzare.

**Non all'esame ma interessante** (p. 107): un video di **Derek Muller** sulla migliore tecnologia al mondo per la **visione notturna**, utile per ripassare i parametri di queste lezioni (tecnologie attive e passive, frame rate, risoluzione, lunghezze d'onda, illuminatori, rumore elettronico, global e rolling shutter, immagini IR e termiche, visione nella nebbia, focali, tempi di risposta, funzionamento del CMOS, sensibilità ISO, lenti, costi) e per vedere come una tecnologia nata in un settore diventi utile in applicazioni civili e di sicurezza.

**Breve simulazione d'esame** (p. 108) su ottiche e sensori, con due esempi. Le simulazioni parziali **non vengono rilasciate** in PDF; la simulazione integrale, con correzione e PDF pubblicati, si svolgerà nell'ultima lezione (Lezione 21). La slide introduttiva della simulazione compare all'inizio del PDF della Lezione 13.

---

### **16. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Distanza intraclasse** / **interclasse** | tra immagini della stessa persona / tra persone diverse |
| **CCD** / **CMOS** | trasferimento di carica, unico amplificatore, poco rumore / conversione per pixel, meno consumo, niente smear e blooming |
| **Global** / **Rolling shutter** | tutti i pixel nello stesso istante / righe in sequenza, jello e skew |
| **Smear** / **Blooming** | striscia verticale da luce forte / saturazione che si espande ai pixel vicini |
| **Aliasing spaziale** / **temporale** | dettagli più fini del passo dei pixel / movimenti o luci rapidi rispetto all'esposizione |
| **Filtro passa-basso** / **polarizzatore** | elimina il moiré sfocando / elimina i riflessi polarizzati |
| **Filtro IR-cut** / **nessun filtro** | colori corretti di giorno / sensibilità NIR di notte, falsi colori |
| **NIR** / **LWIR termico** | luce riflessa, serve illuminazione / calore emesso, nessuna illuminazione |
| **Risoluzione, bit, frame rate, costo** | «pick two»: raramente si ottimizzano tutte |

---

### **17. Sintesi della lezione**

_(slide p. 109)_

> ✅ **In sintesi.**
> - **Effetto delle ottiche**: con DeepFace, volti della stessa persona a focali diverse mostrano distanze fino a più del doppio; in un DB con focali variabili peggiorano i genuini e la DET.
> - **Immagine digitale**: pixel, bit per pixel, spazio CIE 1931, rumore di quantizzazione amplificato dagli algoritmi; più bit per pixel rivelano dettagli come le vene.
> - **Sensori**: CCD contro CMOS, smear, blooming, rumore e fill factor; global e rolling shutter con jello, skew, aliasing spaziale e temporale ed esposizione parziale; CMOS impilati veloci; sensori da 108 MP con volti oltre 5000 pixel e iridi oltre 350 pixel; «pick two».
> - **Multispettrale**: filtri ottici, polarizzatore per riflessi e impronte, VIS e NIR, bande SWIR/MWIR/LWIR, canali RGB come filtri per l'iride, day/night, impronte multispettrali fino a 4 mm per live detection e anti-spoofing.
> - **Sistemi reali**: zoom economici, moduli Day/Night, multicamera mobile e sistemi per l'iride con camera di centraggio.
