# **Lezione 20: Spoofing e antispoofing di iride, impronta, palmo e voce**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

La lezione completa lo studio degli attacchi di presentazione iniziato con il volto nella Lezione 19.

**Outline** (p. 2):

1. **iride**;
2. **impronta**;
3. **palmo** (accenni);
4. **voce**;
5. **conclusioni** sulle tecniche di spoofing e antispoofing.

> ⚠️ **Numerazione interna.** La copertina (p. 1) riporta l'etichetta «L22», residuo di una versione precedente del corso; il PDF corrisponde a questa lezione.

Le definizioni di base (PA, PAD, PAI, APCER, BPCER, liveness, impostore e identity concealer) sono quelle della Lezione 19, §5–6.

---

## **Parte A — Iride**

### **1. Richiami sul riconoscimento dell'iride**

_(slide pp. 3–7)_

**Metodi di riconoscimento** (p. 4):

| Fase | Opzioni |
|---|---|
| **acquisizione** | illuminazione nel **vicino infrarosso**; luce **naturale** |
| **segmentazione** | individuazione di pupilla, limbo e palpebre |
| **codifica e matching** | metodo di **Daugman** (IrisCode, Lezione 4); approcci **«Eigen-Iris»**; formulazioni con **filtri di texture**; **analisi della texture**; analisi dell'iride **per parti** |

**Setup di acquisizione** (p. 5):

- dispositivi di **fascia alta** con camere digitali di alta qualità;
- **corto raggio**: **25 cm**, **un occhio**;
- **medio raggio**: **100 cm**, **due occhi**;
- **illuminazione infrarossa**;
- camere **miniaturizzate** per i dispositivi mobili, come lo scanner dell'iride del **Galaxy S9**; scanner **Iritech**; varchi all'aeroporto di **Schiphol**; soluzioni **low cost**.

**Iride on-the-move** (p. 6), per esempio all'aeroporto di **Dubai** con **Princeton Identity**. Vantaggi: **meno vincoli**, **più usabilità**, maggiore **accettazione**. Sfide: variabilità della **posizione dell'iride** e dell'**occhio**, **occlusioni**, **sfocatura** e **fuori fuoco** (Lezione 14, §14.5).

**Qualità dell'immagine** (p. 7). I fattori sono **ambientali** e legati al **comportamento dell'utente**. Metodi di valutazione:

- punteggi di **segmentazione**;
- **interlacciamento**;
- **sfocatura**;
- **illuminazione** e **luce**;
- **occlusione**;
- **numero di pixel**;
- **dilatazione** della pupilla;
- sguardo **fuori asse** (*off-angle*).

La slide mostra esempi di immagini fuori asse, occluse, sfocate e di alta qualità.

<!-- SOURCE_VISUAL id="TAB20-V01" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="5" type="fotografie prodotto" description="Setup di acquisizione dell'iride: scanner del Galaxy S9, Iritech, Schiphol, dispositivi low cost" reason="Esempi visivi dei dispositivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V02" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="7" type="griglia di immagini" description="Qualità dell'immagine dell'iride: off-angle, occlusa, alta qualità, sfocata" reason="Esempi visivi dei fattori di qualità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Gli attacchi all'iride**

#### **2.1 Tipi di attacco e artefatti**

_(slide pp. 8–13)_

| Tipo (pp. 8–9) | Obiettivo | Esempi |
|---|---|---|
| **Impersonificazione** | essere riconosciuti come un altro | acquisire l'immagine dell'iride del bersaglio e **registrarne l'IrisCode**; ottenere un match con **qualsiasi** identità registrata; **artefatti**: **stampa** con inchiostro rilevabile in IR, **occhi di plastica**, **lenti cosmetiche** |
| **Occultamento** | non essere riconosciuti | **nascondere** l'informazione della texture; usare **colliri** che dilatano la pupilla |
| **Ibridi** | combinazioni | generare e **registrare un pattern sintetico**; acquisire l'iride con il **sensore capovolto** |

**Identità artificiali** (p. 10):

| Tecnica | Metodi |
|---|---|
| **sintesi** di immagini dell'iride | processo di **rendering**, **campi aleatori di Markov**, sintesi della texture **basata su modelli** |
| **alterazione** di immagini autentiche senza distruggere le feature | **ribaltamento verticale**, **sfocatura**, **scambio**, **sostituzione** |

**Tecnologie di attacco con artefatti** (pp. 11–13):

| Artefatto | Esempi (slide) |
|---|---|
| **stampe su carta** | foto stampata di un'iride autentica (dataset LivDet-Iris Warsaw 2017) |
| **lenti a contatto con texture** | occhio con lente stampata (dataset University of Notre Dame) |
| **display** | iride mostrata su **iPhone** o su un **e-reader Kindle** e fotografata dal sensore **AD 100** |
| **protesi oculari** | protesi in vetro inserita nell'orbita e fotografata dal sensore AD 100 |
| **texture sintetica** | immagine con texture d'iride sintetica incorporata (CASIA-Iris-Syn V4) |
| **iride di cadavere** | acquisita **5 ore dopo la morte** (Post-Mortem-Iris v1.0) |

> 💡 **Perché un e-reader può funzionare.** Molti display LCD emettono poco nel vicino infrarosso, ma gli e-reader a inchiostro elettronico **riflettono** la luce ambiente come la carta: sotto l'illuminatore IR del sensore l'immagine resta visibile. È un esempio di come la **risposta spettrale** del supporto decida se l'attacco passa (Lezione 12, §11).

<!-- SOURCE_VISUAL id="TAB20-V03" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="9" type="fotografie" description="Artefatti per l'iride: stampa con inchiostro rilevabile in IR, occhi di plastica, lenti cosmetiche" reason="Esempi visivi degli artefatti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V04" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="11" type="confronto di immagini" description="Iride autentica contro stampa e contro lente a contatto con texture (LivDet-Iris Warsaw 2017, Notre Dame)" reason="Confronto visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V05" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="12" type="confronto di immagini" description="Iride originale mostrata su iPhone e Kindle e acquisita dal sensore AD 100" reason="Confronto visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V06" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="13" type="immagini" description="Protesi oculare in vetro, texture sintetica CASIA-Iris-Syn V4 e iride di cadavere dopo 5 ore" reason="Esempi visivi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2 Dataset per l'antispoofing**

_(slide pp. 14–16)_

I dataset servono per **testare metodi** o **allenare reti deep**:

| Categoria | Dataset (slide) |
|---|---|
| principali | **CASIA-Iris-Fake**, **BERC-Iris-Fake**, **LivDet 2017** (unione di 4 dataset) |
| con campioni **falsi** | IIITD Iris Spoofing, Post-Mortem-Iris v1.0, CASIA-Iris-Syn V4, Synthetic Iris Textured Based, Synthetic Iris Model Based |
| con campioni **autentici** | Pupil-Dynamics v1.0, CAVE |
| **non uniformi** | LivDet-Iris Warsaw 2013, 2015 e 2017; LivDet-Iris Clarkson 2015 LG; ETPAD v1 |
| campioni **sintetici statici** | IIITD Combined Spoofing, CASIA-Iris-Fake |
| iridi **vive** | PAVID, GUC-LF-VIAr-DB e VSIA |

<!-- SOURCE_VISUAL id="TAB20-V07" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="14" type="tabella grafica" description="Principali dataset di antispoofing dell'iride: CASIA-Iris-Fake, BERC-Iris-Fake, LivDet 2017" reason="Caratteristiche dei dataset presenti solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3 Attacchi con l'occhio vero**

_(slide pp. 17–18)_

**Presentazione non conforme** (p. 17):

| Azione | Effetto |
|---|---|
| **chiusura eccessiva delle palpebre** | **poche feature** dell'iride, quindi **alta probabilità di match errato** |
| **guardare lontano dall'obiettivo** | la proiezione 2D dell'iride si **allontana dalla forma circolare** |
| **rotazione reciproca** tra sensore e occhio | **camera capovolta** o **testa ruotata** |
| **colliri** per una **midriasi eccessiva** | per **aggirare** un controllo di frontiera basato sull'iride |

> 💡 **Perché poche feature aumentano i match errati (nota didattica).** Con la distanza di Hamming normalizzata (Lezione 4) i bit di due iridi diverse coincidono con probabilità circa $p = 0{,}5$. Se il confronto usa $N$ bit indipendenti, la distanza tra impostori ha media $0{,}5$ e deviazione standard
> $$\sigma = \sqrt{\frac{p(1-p)}{N}}$$
> Daugman stima circa 249 gradi di libertà per un'iride intera, da cui $\sigma \approx 0{,}032$. Se le palpebre lasciano visibile un quarto dell'iride, $N$ scende a circa 62 e $\sigma$ raddoppia a circa $0{,}063$: la coda della distribuzione degli impostori si allarga verso la soglia e crescono i falsi match. Per questo i sistemi scartano i campioni con troppi bit mascherati o rinormalizzano il punteggio.

> 💡 **Camera capovolta e collirio.** L'IrisCode compensa la rotazione della testa con piccoli **shift circolari** dei bit (Lezione 4). Una rotazione di 180° va ben oltre gli shift provati: il genuino **non** si riconosce più, il che serve a **occultarsi** o, in un attacco ibrido, a **registrare lo stesso occhio due volte** con identità diverse. Una pupilla dilatata dal collirio lascia invece un anello d'iride sottile, e la normalizzazione a «foglio di gomma» stira pochissima texture: di nuovo meno feature affidabili.

**Occhio di cadavere e coercizione** (p. 18):

- attacco con **occhio di cadavere**: l'iride post-mortem risulta **accettabile fino a 11 giorni**; è **facile** ottenere un'immagine nitida; **nessun caso** reale trovato finora;
- **coercizione**: **letteratura insufficiente**; attacco **relativamente facile**; **potenziale vulnerabilità** futura.

---

### **3. Antispoofing dell'iride**

#### **3.1 Classificazione dei metodi PAD**

_(slide p. 19)_

Due domande classificano i metodi:

1. l'iride è trattata come oggetto **statico** o **dinamico**?
2. la stimolazione dell'iride è **passiva** o **attiva**?

| | **Acquisizione passiva** | **Acquisizione attiva** |
|---|---|---|
| **Iride statica** | analisi di una singola immagine (texture, stampa, lente) | stimolo esterno su un'immagine, per esempio **riflessi** degli illuminatori sulla cornea |
| **Iride dinamica** | analisi dei **movimenti** spontanei in una sequenza | risposta a uno stimolo, per esempio **dilatazione** della pupilla sotto un lampo di luce |

<!-- SOURCE_VISUAL id="TAB20-V08" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="19" type="schema" description="Classi di metodi PAD per l'iride: statica/dinamica, passiva/attiva, con riflessi, movimenti e dilatazioni" reason="Classificazione disegnata come schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2 Un attacco semplice: il Samsung Galaxy S8**

_(slide pp. 20–26)_

**Attacchi di presentazione di base** (p. 20). La stampa richiede una **correzione prospettica**, e **carta e inchiostro** vanno scelti in base alla **lunghezza d'onda** del sensore (IR o visibile).

**Il caso Galaxy S8** (pp. 21–25). Il **22 maggio 2017** il **Chaos Computer Club** (CCC) ha violato il riconoscimento dell'iride del Samsung Galaxy S8, meno di un mese dopo l'uscita («The Guardian», 23 maggio 2017). Le note del CCC:

1. usare una **fotocamera digitale** in modalità **night-shot** o **senza filtro infrarosso**: nello spettro IR i dettagli fini dell'iride, difficili da distinguere negli **occhi scuri**, diventano ben riconoscibili;
2. **Starbug** ha mostrato che basta una buona fotocamera con **obiettivo da 200 mm** a **fino a cinque metri**;
3. secondo la qualità della foto, regolare **luminosità e contrasto** (con **stretching dell'istogramma**);
4. se tutte le strutture sono visibili, **stampare** l'iride con una **stampante laser** (nella slide, una stampante Samsung);
5. per emulare la **curvatura** dell'occhio, appoggiare sulla stampa una **normale lente a contatto**: una lente **bagnata** inganna anche il **test di liveness**;
6. posizionare stampa e lente davanti al sensore.

> 💡 **Verifica dei numeri dell'attacco (nota didattica).** Con il modello a foro stenopeico (Lezione 11) l'ingrandimento vale circa $f / Z = 200 / 5000 = 0{,}04$. Un'iride di circa 12 mm di diametro produce sul sensore un'immagine di
> $$12\ \text{mm} \cdot 0{,}04 = 0{,}48\ \text{mm}$$
> che con pixel da 3,45 µm (Lezione 12) sono circa 139 pixel di diametro, cioè un raggio di circa 70 pixel: il minimo indicativo per l'IrisCode. L'attacco richiede quindi davvero un teleobiettivo e un sensore adeguato, ma nulla di più di un'attrezzatura fotografica amatoriale evoluta.

> 💡 **Collegamento con la Lezione 12.** Il trucco del filtro IR è lo stesso della biometria multispettrale: togliendo il filtro IR-cut una camera normale diventa sensibile al NIR, e gli occhi scuri, quasi uniformi nel visibile, mostrano la loro tessitura.

**Antispoofing di base contro le lenti stampate** (p. 26): schemi di rilevamento dello spoof dal *Handbook of Biometrics* (pp. 403–423).

<!-- SOURCE_VISUAL id="TAB20-V09" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="20" type="immagini" description="Presentation attack di base con stampa corretta prospetticamente e scelta di carta e inchiostro" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V10" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="22" type="fotografie" description="Attacco al Galaxy S8: acquisizione in night vision o senza filtro IR con focale 200 mm a circa 5 m" reason="Setup mostrato nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V11" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="23" type="immagini" description="Stampa dell'iride con stampante laser dopo lo stretching dell'istogramma" reason="Passaggi mostrati nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V12" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="24" type="fotografia" description="Lente a contatto bagnata appoggiata sulla stampa per ingannare il test di liveness" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V13" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="25" type="fotografia" description="Stampa e lente bagnata posizionate davanti al sensore dell'iride" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V14" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="26" type="schema" description="Antispoofing di base contro lenti a contatto stampate (Handbook of Biometrics)" reason="Schema del metodo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3 Approcci deep e liveness**

_(slide pp. 27–29)_

**Un approccio con CNN** (p. 27, Hoffman, Sharma, Ross, CVPR 2018):

- **tassellazione** dell'immagine (primo canale);
- **segmentazione** (secondo canale);
- **fusione** dei risultati della classificazione delle sotto-immagini;
- **cross-training**: addestramento su **LivDet-Iris 2015**, test su **CASIA-IF** e **BERC-IF**;
- risultati notevoli **tranne che per gli occhi di plastica**.

> 💡 **Perché il cross-dataset conta.** Un rilevatore testato sullo stesso dataset su cui è stato addestrato impara anche le peculiarità di quel sensore e di quei materiali. Allenare su un dataset e testare su altri misura la **generalizzazione** a sensori e attacchi nuovi, come il «leave one generator out» dei deepfake (Lezione 17, §10.2).

**Liveness: dilatazione della pupilla** (p. 28). La pupilla di un occhio vivo reagisce alla luce: la slide mostra la variazione **dopo 1 secondo** e durante un video. Una stampa o una protesi non reagiscono.

**Liveness: micro-saccadi** (p. 29):

- anche fissando un punto, gli occhi compiono continuamente minuscoli movimenti, i **micro-saccadi**, circa **due al secondo**;
- il motivo è **neurofisiologico**: i fotorecettori della retina si **adattano** a uno stimolo statico, e un'immagine completamente ferma sulla retina **svanirebbe** (*fading percettivo*); i micro-saccadi la **rinfrescano**.

| Parametro | Valore (slide) |
|---|---|
| ampiezza | circa **0,1–0,5°** di angolo visivo |
| spostamento della pupilla | circa **0,1–0,3 mm** |
| frequenza | circa **1–2 Hz** |
| durata | circa **10–25 ms** |

I micro-saccadi sono **promettenti** ma **non ancora applicati** alla liveness.

> ⚠️ **Spostamento da verificare.** Con un raggio di rotazione dell'occhio di circa 12 mm, un angolo $\theta$ sposta la pupilla di circa $r \theta$: $0{,}5° \approx 0{,}0087$ rad dà circa **0,1 mm**, e $0{,}1°$ circa **0,02 mm**. L'intervallo 0,1–0,3 mm della slide sembra quindi sovrastimato per ampiezze di 0,1–0,5°.

> 💡 **Perché non si usano ancora (nota didattica).** Un evento di 10–25 ms richiede almeno qualche frame per essere osservato: a 30 fps (33 ms per frame) si perde del tutto, mentre servono **centinaia di fps**. Lo spostamento di pochi centesimi di millimetro, su un'iride di circa 140 pixel di diametro, vale una frazione di pixel. Servono quindi i sensori ad **altissimo frame rate** e **alta risoluzione** della Lezione 12.

<!-- SOURCE_VISUAL id="TAB20-V15" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="27" type="schema di rete" description="CNN per il PAD dell'iride con tassellazione, segmentazione, fusione e cross-training" reason="Architettura mostrata nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V16" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="28" type="fotogrammi video" description="Liveness dell'iride: dilatazione della pupilla dopo 1 secondo" reason="Variazione visibile solo nei fotogrammi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — Impronta e palmo**

### **4. Gli attacchi all'impronta**

_(slide pp. 30–36)_

**Tassonomia dei metodi di spoofing** (p. 31, Marasco e Ross, *ACM Computing Surveys*, 2014):

| Categoria | Metodo | Cooperazione del proprietario |
|---|---|---|
| **duplicazione** dell'impronta | **stampo diretto** (*direct mold*) | **cooperativo**: il proprietario partecipa |
| **impronta artificiale** da una **latente** | ricostruzione da una traccia lasciata | **non cooperativo** |
| **riattivazione** | far «rileggere» al sensore la traccia rimasta | non cooperativo |
| **sintesi** | generazione dal template o artificiale | non cooperativo |
| **cadavere** | dito prelevato da un cadavere | non cooperativo |

**Occultamento dell'impronta** (p. 32): la slide mostra un esempio, con una figura lasciata **intenzionalmente piccola**.

**Stampo diretto** (p. 33): lo spoof è ricavato **direttamente da un dito vivo**. Si ottiene lo **stampo** in **plastica** e poi il **calco** in **gelatina**.

**Gummy finger senza la vittima** (p. 34):

1. **rilevare un'impronta latente** da una superficie liscia (vetro, custodia di CD) con **polvere di ferro** e **nastro adesivo**;
2. **scansione e post-elaborazione**: correzione degli errori di scansione, **chiusura delle creste** interrotte, **inversione** dell'immagine;
3. **stampa su lucido** → produzione **fotochimica** di un **circuito stampato** (le creste diventano rilievi) → **artefatto in silicone**, flessibile, umido, economico.

**Altri materiali** (p. 35). Se lo strato dello spoof che copre il dito dell'attaccante è **abbastanza sottile**, può ingannare perfino i lettori che rilevano **flusso sanguigno**, **pulsazioni** o **temperatura**: sotto lo strato c'è un dito vivo.

**Attacchi non cooperativi** (p. 36):

- **riattivazione** delle impronte dalla superficie del sensore:
  - un **sacchetto di plastica pieno d'acqua** premuto sul sensore fa riapparire l'impronta lasciata, simulando un dito;
  - **polvere di grafite** rende visibili i residui e induce il sensore a rilevarli come attivi;
- **dito di cadavere**;
- **sintesi**: **ricostruzione da un template** (minuzie) di un'impronta artificiale per ingannare il sensore (Lezione 13, §6.2).

> 💡 **Perché la riattivazione funziona sui sensori capacitivi.** Il grasso e il sudore di un dito lasciano sul sensore la forma delle creste. Un sacchetto d'acqua, conduttivo, preme uniformemente sulla superficie: dove c'è il residuo la capacità cambia diversamente, e il sensore «vede» di nuovo l'ultima impronta. Molti sensori evitano l'attacco rifiutando un'immagine troppo simile a quella dell'acquisizione precedente, un controllo simile a quello anti-replay.

<!-- SOURCE_VISUAL id="TAB20-V17" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="31" type="diagramma ad albero" description="Tassonomia dei metodi di spoofing dell'impronta: cooperativi e non cooperativi" reason="Albero disegnato come schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V18" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="32" type="immagine" description="Presentation attack con occultamento dell'impronta" reason="Figura lasciata intenzionalmente piccola nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V19" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="35" type="fotografie" description="Altri materiali per spoof di impronte e strato sottile sopra il dito dell'attaccante" reason="Esempi visivi dei materiali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Antispoofing dell'impronta**

_(slide pp. 37–46)_

#### **5.1 Tassonomia**

La tassonomia di Marasco e Ross (p. 37) divide i metodi in:

| Famiglia | Sottofamiglie |
|---|---|
| **hardware** | sensori aggiuntivi che misurano proprietà del dito vivo |
| **software statici** | su una **singola** impressione: **texture**, **pori**, tracce di **sudorazione**, elasticità |
| **software dinamici** | su **più frame**: **distorsione delle creste** (elasticità della pelle sotto pressione), evoluzione della **sudorazione** |

**Hardware** (p. 38):

- **caratteristiche della vitalità** sfruttate: **temperatura** del dito; **conduttività** o **impedenza** elettrica della pelle; **ossimetria** del polso; caratteristiche **interne** dei tessuti stratificati; **texture cromatica** della pelle;
- **sensori**: sensori a **stato solido**; rilevamento dell'**odore**, analizzando le molecole emesse dalla superficie a contatto; **tomografia ottica a coerenza** (OCT), che scansiona gli strati superiori dell'epidermide ed è ancora **prototipale**;
- **problemi**: **costo elevato**, sistemi **difficili da aggiornare**.

**Software** (p. 39):

| | **Dinamici** | **Statici** |
|---|---|---|
| Input | **più frame** della stessa impronta, per esempio immagini successive in **2–5 secondi** | **una** impressione |
| Caratteristiche | evoluzione nel tempo | tratti **testurali**, **elasticità** della pelle, **sudorazione** |
| Costo | maggiore | **più economici e veloci** |

> 💡 **La sudorazione nel tempo.** In un dito vivo i pori rilasciano sudore che si diffonde lungo le creste: in pochi secondi l'immagine delle creste cambia da «puntinata» a più continua. Silicone e gelatina non sudano, quindi la sequenza resta costante. È il principio dei metodi dinamici basati sulla perspirazione.

<!-- SOURCE_VISUAL id="TAB20-V20" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="37" type="diagramma ad albero" description="Tassonomia dei metodi di antispoofing dell'impronta: hardware e software statici e dinamici" reason="Albero disegnato come schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Sensori moderni**

_(slide pp. 40–46)_

| Sensore | Caratteristiche (slide) |
|---|---|
| **Qualcomm Snapdragon Sense ID** (p. 40) | sensore **3D a ultrasuoni** sotto il vetro; cattura **dettagli acustici tridimensionali** negli strati superficiali della pelle; antispoofing **superiore** ai sensori capacitivi |
| **Synaptics Clear ID FS9500** (p. 41) | sensore **CMOS** sotto il vetro con tecnologia antispoofing **PurePrint** basata su IA e **match-on-sensor** |
| **GANZ, sistema touchless multibanda** (p. 43) | **30 immagini** di impronte al secondo e **30 confronti** al secondo; riconoscimento **«on-the-fly»**; esperienza **touchless**; **4 impronte** in contemporanea; analisi **3D**; illuminazione a lunghezza d'onda **verde** per aumentare il contrasto tra creste e valli, anche su impronte secche |
| **GANZ, lettore 4-in-1** (p. 44) | lettore di controllo accessi **completamente touchless** con **impronta**, **volto** 2D/3D, **iride** e **vene del palmo**: l'antispoofing deriva dalla **multimodalità molto spinta** |
| **Dermalog ZF2** (p. 45) | per **forze dell'ordine**, **forense** e **banche** (POS); impronta **rollata** o due **piatte**; fino a **15 fps**; ottimizzato per impronte **secche**; conforme **FBI Appendix F**; sensore **ottico IR** resistente alla luce del giorno. Antispoofing dalla rilevazione del **battito cardiaco** |
| **Bosch Suprema BioEntry W2** (p. 46) | processore quad-core da **1,2 GHz**; custodia antivandalo in resina, **IP67**; **RFID** a doppia frequenza, LF **125 kHz** e HF **13,56 MHz**; rilevamento del dito vivo con analisi del pattern che cambia, feature di vitalità e di **innaturalità**, ora con **doppia sorgente di luce** (IR e bianca) che blocca impronte false in **argilla, gomma, silicone, colla, carta, pellicola** |

> ⚠️ **Nome del produttore.** Le slide scrivono «GANTZ», ma il link indicato (ganzsecurity.com) è quello di **GANZ**.

> 💡 **Il battito a 15 fps (nota didattica).** Il battito cardiaco a riposo è circa 1–2 Hz. Per il teorema del campionamento bastano più di due campioni per periodo: a 15 fps si osservano frequenze fino a 7,5 Hz, ampiamente sufficienti. Il sensore cerca la lieve variazione periodica di colore o intensità dovuta al flusso sanguigno nel polpastrello, come l'rPPG sul volto (Lezione 19, §8).

**MasterPrints** (p. 42). Impronte **reali o sintetiche** che possono **coincidere per caso** con **molte** impronte diverse: l'idea richiama il **grimaldello** che apre molte serrature. È un attacco segnalato dai ricercatori, generato con tecniche di *latent variable evolution* (Lezione 17, §6.2, DeepMasterPrints).

<!-- SOURCE_VISUAL id="TAB20-V21" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="40" type="schema" description="Qualcomm Sense ID: sensore di impronte 3D a ultrasuoni sotto il vetro" reason="Principio mostrato nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V22" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="42" type="immagini" description="MasterPrints come grimaldello che coincide con molte impronte" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V23" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="43" type="fotografia prodotto" description="Sistema touchless multibanda GANZ per 4 impronte con luce verde" reason="Esempio visivo del dispositivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Il palmo senza contatto**

_(slide pp. 47–50)_

**Autenticazione con il palmo senza contatto** (p. 47): **Amazon One**.

**Vene del palmo: Fujitsu PalmSecure** (p. 48). Il produttore dichiara un tasso di **falsa accettazione inferiore allo 0,00001%** (1 su 10 milioni) e un tasso di **falso rifiuto dello 0,01%** (1 su 10.000).

> 💡 **Quanti test servono per dirlo (nota didattica).** $0{,}00001\% = 10^{-7}$. Con la **regola del 3** (Lezione 9), per affermare con confidenza al 95% un FAR non superiore a $10^{-7}$ senza nessun errore osservato servono circa
> $$n \approx \frac{3}{10^{-7}} = 3 \cdot 10^7$$
> confronti impostori **indipendenti**. Senza sapere come è stato ottenuto il dato, va letto come un'affermazione commerciale (Lezione 10).

**Spoofing delle vene del palmo** (pp. 49–50). Le pubblicazioni sono **pochissime**, ma l'attacco sembra possibile:

- al **Chaos Communication Congress** del **2018**, **Jan Krissler** («starbug») e **Julian Albrecht** hanno attaccato con successo il dispositivo **PalmSecure** di Fujitsu con un **modello in cera** e **stampe**;
- l'acquisizione è stata fatta con una **fotocamera reflex convertita** a cui era stato **rimosso il filtro infrarosso**;
- l'attacco ha richiesto **2500 immagini** acquisite e **30 giorni di lavoro** per realizzare modelli in cera funzionanti.

> ⚠️ **Sigla dell'ente.** La slide dice che il dispositivo è usato «presso il BSD Servizio federale di intelligence tedesco»; il servizio federale di intelligence tedesco è il **BND** (*Bundesnachrichtendienst*).

> 💡 **Il costo dell'attacco come misura di sicurezza.** Le vene sono sotto la pelle, invisibili a occhio e difficili da fotografare di nascosto: lo spoofing è possibile, ma 2500 immagini e un mese di lavoro alzano molto il **potenziale d'attacco** rispetto a una foto del volto o a un'impronta latente (Lezione 19, §6.4).

<!-- SOURCE_VISUAL id="TAB20-V24" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="47" type="fotografia prodotto" description="Autenticazione contactless con il palmo Amazon One" reason="Esempio visivo del dispositivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V25" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="50" type="fotogrammi video" description="Spoofing di Fujitsu PalmSecure con modello in cera e fotocamera senza filtro IR" reason="Attacco mostrato nei fotogrammi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte C — Voce**

### **7. Richiami sul riconoscimento del parlatore**

_(slide pp. 51–54)_

**La voce** (p. 52, breve ripasso) ha una componente **fisiologica** e una **comportamentale**. La **prosodia** riguarda le proprietà delle sillabe e delle unità più grandi del parlato: **intonazione**, **tono**, **accento**, **ritmo**.

**Lo «spettro» delle feature** (p. 53). Dalle feature **apprese e comportamentali** a quelle **fisiologiche e organiche**:

| Livello | Feature | Esempi |
|---|---|---|
| **alto** (appreso, comportamentale) | **lessicali e sintattiche** | co-occorrenze di fonemi e parole, modelli di pronuncia |
| | **prosodiche** | contorni di pitch e intensità, dinamica prosodica, timing, microprosodia |
| | **fonetiche** | spettro dei fonemi, LTAS (spettro medio a lungo termine), qualità della voce, frequenze e larghezze di banda delle **formanti** |
| **basso** (fisiologico, organico) | **acustiche di basso livello** | elaborazione per sottobande, **cepstrum**, feature **LPC** e **PLP**, dinamica spettrale, spettro di modulazione |

| | **Feature di alto livello** | **Feature di basso livello** |
|---|---|---|
| Vantaggi | **robuste** a effetti del canale e rumore | **facili** da estrarre automaticamente, **pochi dati**, modelli **semplici**, **indipendenti dal testo** |
| Svantaggi | **difficili** da estrarre, **molti dati** di training, modelli **complicati**, **dipendenti dal testo** | facilmente **corrotte** da rumore e variabilità **tra sessioni** |

> ⚠️ **Ricostruzione della tabella.** Il livello testo della p. 53 duplica e mescola le etichette del grafico; l'associazione di vantaggi e svantaggi ai due estremi segue lo schema classico della letteratura sul riconoscimento del parlatore e va confrontata con la slide.

**Metodi dipendenti e indipendenti dal testo** (p. 54) per l'**Automatic Speaker Verification** (ASV):

| | **Text-dependent** | **Text-independent** |
|---|---|---|
| Frasi | **fisse** o richieste casuali con contenuto **noto** | **arbitrarie**, anche in lingue diverse |
| Cooperazione | **richiesta** | non necessaria |
| Durata | bastano frasi **brevi** (*utterance*, la più piccola unità del parlato) | più lunga |
| Usi | autenticazione vocale con passphrase | **call center**, banking telefonico |
| Vulnerabilità | se la vittima ha **registrazioni pubbliche** e il prompt è **noto** all'attaccante | — |

<!-- SOURCE_VISUAL id="TAB20-V26" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="53" type="diagramma" description="Spettro delle feature vocali da lessicali e prosodiche ad acustiche di basso livello con vantaggi e svantaggi" reason="Associazioni visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Gli attacchi alla verifica del parlatore**

_(slide pp. 55–67)_

La slide p. 55 elenca **cinque attacchi principali**:

| # | Attacco | Idea |
|:---:|---|---|
| 1 | **impersonificazione** | una persona imita la voce del bersaglio |
| 2 | **replay** | si riproduce una registrazione del genuino |
| 3 | **voice conversion** | un sistema trasforma la voce dell'attaccante in quella del bersaglio |
| 4 | **text-to-speech e sintesi** | si genera la voce del bersaglio da un testo |
| 5 | **toni artificiali non vocali** | un segnale avversario, non vocale, progettato contro il modello |

> ⚠️ **Sigla.** La slide p. 55 scrive «AVS»; la sigla usata nel resto della lezione per la verifica automatica del parlatore è **ASV**.

#### **8.1 Impersonificazione**

_(slide p. 56)_

**Impersonation** (*mimicry*): il parlante tenta di imitare la voce di un altro copiandone il comportamento **lessicale**, **prosodico** e **idiosincratico**.

- è un metodo **facile** da applicare;
- è **efficace solo** se la voce naturale dell'attaccante è **già simile** a quella del bersaglio (l'esempio della slide è l'attore Alec Baldwin nelle sue imitazioni);
- **contromisure**: proprio perché vengono ricreate le caratteristiche di **alto livello**, ciò che per il nostro cervello è una persona molto simile resta, per un sistema di riconoscimento del parlatore, **una persona diversa**. Se però all'imitazione si aggiungono i metodi successivi, come la voice conversion, l'attacco diventa **molto più difficile** da rilevare, perché si replicano **tutti i livelli**;
- la letteratura e i dataset sugli attacchi di impersonificazione sono **limitati**.

#### **8.2 Replay**

_(slide pp. 57–59)_

**L'attacco** (p. 57): si **registra** la voce del genuino, oppure si **uniscono piccoli spezzoni** per ottenere la frase voluta.

**Il rumore tipico** (p. 58):

| Rumore | Origine |
|---|---|
| **di quantizzazione** | introdotto nella **digitalizzazione**: convertendo un segnale continuo in digitale (campionamento e quantizzazione) si perdono informazioni, e resta un rumore caratteristico della **risoluzione** del dispositivo |
| **di riproduzione** | dovuto al dispositivo che **riproduce** la registrazione: limiti fisici dell'**altoparlante** (risposta in frequenza, non linearità, risonanze), **rumore ambientale**, artefatti della **compressione** (MP3, AAC) |

**Rilevamento**: metodi basati su **feature ad alta frequenza** (HFF) e **analisi spettrale**; tecniche di **deep learning**.

> 💡 **Quanto rumore aggiunge la quantizzazione (nota didattica).** Per un convertitore a $N$ bit con segnale sinusoidale a piena scala il rapporto segnale/rumore di quantizzazione vale circa
> $$SQNR \approx 6{,}02\, N + 1{,}76\ \text{dB}$$
> A 16 bit sono circa 98 dB, a 8 bit circa 50 dB (si confronti la gamma dinamica in dB della Lezione 18). Un replay attraversa **due** conversioni e un altoparlante: registrazione, riproduzione e nuova acquisizione. Ogni passaggio lascia la sua impronta, soprattutto alle **alte frequenze**, dove microfoni e altoparlanti economici hanno una risposta scarsa.

**Caratteristiche e contromisure** (p. 59):

- è un attacco a **bassa tecnologia**: bastano **registratori di alta qualità** e **software di editing** audio;
- è **efficace** e **difficile da rilevare**, specialmente con un **canale rumoroso** tra sensore e sistema, come le **linee telefoniche**, che mascherano gli artefatti;
- **contromisure**: metodi **dipendenti dal testo** o **challenge-response** (una frase casuale non si trova già registrata); rilevare le caratteristiche di rumore del dispositivo di **registrazione** e dell'**altoparlante**, per esempio con feature ad alta frequenza (Witkowski et al., INTERSPEECH 2017).

#### **8.3 Voice conversion**

_(slide pp. 60–62)_

La **voice conversion** (VC) è un insieme di moduli che **convertono la voce** di una persona in quella di un'altra (p. 60), agendo su:

- parametri di **eccitazione**, come la frequenza fondamentale;
- parametri di **risonanza**.

Oggi esistono anche approcci basati su **CNN**, per esempio con il dataset **VCC2018** (figure da T. Toda, *Advanced Voice Conversion*, Nagoya University, 2018).

> 💡 **Il modello sorgente-filtro (nota didattica).** La voce si descrive come una **sorgente** (le corde vocali, che vibrano alla frequenza fondamentale e ne determinano il tono) filtrata dal **tratto vocale** (gola, bocca, naso), le cui risonanze sono le **formanti**. L'eccitazione dipende soprattutto da come si parla; le risonanze dalla **forma fisica** del tratto vocale, quindi dall'identità. Una voice conversion efficace deve modificare entrambe.

**Attacchi e contromisure** (p. 61):

- **diversamente dall'orecchio umano**, i sistemi di riconoscimento vocale **non** riescono a rilevare gli **artefatti** di conversione dovuti a modelli di analisi-sintesi imperfetti e a funzioni di conversione poco addestrate;
- **CNN** e altri metodi di deep learning stanno migliorando molto la VC (per esempio la generazione della forma d'onda con WaveNet, Kobayashi et al., INTERSPEECH 2017);
- **contromisure**: ricerca delle **tracce di artefatti** dei sistemi di sintesi e conversione; approcci **statistici** e di **deep learning** per la liveness e il rilevamento degli attacchi, ancora in studio.

**Attacchi avversari ai modelli deep** (p. 62). Contro i sistemi di riconoscimento vocale basati su deep learning sono ancora **agli inizi**:

- **non** sono (ancora) generali né robusti;
- il rumore introdotto è **sensibile** a **trasmissione** e **compressione**;
- vanno **personalizzati** per ogni segmento audio, un processo che oggi **non** si esegue **in tempo reale**;
- i primi tentativi riguardano i sistemi di **riconoscimento del parlato** (esempi di H. Zhang, Stanford, 2018).

> ⚠️ **Attenzione alla distinzione** (p. 62). Un attacco avversario **non** è **voice cloning**: il primo aggiunge una perturbazione progettata contro un modello, il secondo genera una voce realistica del bersaglio.

<!-- SOURCE_VISUAL id="TAB20-V27" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="60" type="grafici" description="Voice conversion: parametri di eccitazione e di risonanza (T. Toda, 2018)" reason="Grafici non estraibili" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.4 Sintesi vocale**

_(slide pp. 63–66)_

**Text-to-speech** (TTS, p. 63): genera parlato artificiale **intelligibile** e **naturale** da qualsiasi testo.

- è un metodo di uso generale, ma **utile per attaccare** i sistemi **challenge-response**, perché genera qualunque frase richiesta;
- i modelli vanno dai **modelli di Markov nascosti** (HMM) ai sistemi di **deep learning**;
- assistenti vocali come **Siri** e **Alexa** possono essere **facilmente ingannati** da input sintetici;
- modelli pubblici recenti: **WaveNet**, modello generativo profondo di forme d'onda audio grezze; **Tacotron**, verso una sintesi end-to-end (Interspeech 2017);
- **contromisure**: ancora **agli inizi**.

> ⚠️ **Attribuzione.** La slide attribuisce WaveNet a Meta; WaveNet è stato sviluppato da **DeepMind** (Google), come Tacotron da Google.

**VALL-E di Microsoft** (p. 64):

- imita la voce umana da una **clip di 3 secondi**, riproducendo anche l'**ambiente acustico** e i **rumori di fondo**, con una sintesi TTS molto efficace;
- può **modificare l'emozione** del testo parlato;
- ascoltando gli esempi si sentono comunque **artefatti caratteristici**.

**Altri produttori** (pp. 65–66): **ElevenLabs** (voice design) e **Microsoft Azure** (custom neural voice).

<!-- SOURCE_VISUAL id="TAB20-V28" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="65" type="schermata" description="ElevenLabs voice design" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.5 Toni artificiali non vocali**

_(slide p. 67)_

- **Attacco avversario** in cui l'input **non è una voce reale**, ma un **segnale progettato** per ingannare il sistema sfruttando la **conoscenza del modello**; a volte è **sovrapposto a una voce** che non si vuole far identificare.
- La qualità può non essere «perfetta», ma basta a ingannare una **funzione**, un **servizio** o un **account** protetti dalla voce.
- **Contromisure**:
  - **valutazione della qualità del parlato**, per individuare anomalie;
  - analisi delle **feature di alto livello** (HLF): informazioni fonetiche, prosodiche e lessicali, anche come parametri di modelli ottimizzati sui dati;
  - **classificatori neurali** addestrati a riconoscere segnali falsificati o anomali.

> 💡 **Perché le feature di alto livello aiutano.** Un segnale ottimizzato contro il modello acustico può riprodurre lo spettro «giusto», ma difficilmente contiene fonemi sensati, una prosodia naturale e parole reali. Controllare che l'input **sia parlato** prima di chiedersi **di chi sia** blocca questa famiglia di attacchi.

---

### **9. Antispoofing multimodale e acustico**

_(slide pp. 68–72)_

**Sincronia dei movimenti delle labbra** (p. 68):

- il sistema chiede di **leggere una frase** davanti al sensore (**challenge-response**);
- misura le **variazioni del movimento delle labbra** nel tempo per rilevare falsi, maschere o immagini mostrate al sensore;
- si analizzano **regioni locali verticali** di **60 × 1 pixel** in ogni frame e la loro variazione nel tempo con il **flusso ottico**.

**Volto più labbra: BioID** (p. 69). Il sistema commerciale BioID ha migliorato l'accuratezza del riconoscimento facciale valutando il **flusso ottico del movimento delle labbra** tra due fotogrammi. Oggi i sistemi di liveness di BioID sono più orientati al flusso ottico di **tutto il volto** (Lezione 19, §9.4).

**VoiceGesture** (p. 70; Zhang, Tan, Yang, ACM CCS 2017), **prototipo**:

- rileva gli attacchi **replay** sugli smartphone **a livello di fonema**;
- usa lo smartphone come un **radar Doppler**: l'altoparlante emette un **tono a 20 kHz** e il **microfono** riceve le **riflessioni** mentre l'utente pronuncia la passphrase;
- un **HMM** allinea e classifica i gesti articolatori.

> 💡 **L'effetto Doppler in numeri (nota didattica).** Un'onda di frequenza $f_0$ riflessa da una superficie che si muove a velocità $v$ torna spostata di circa
> $$\Delta f \approx \frac{2 v}{c}\, f_0$$
> Con labbra che si muovono a $v = 0{,}1$ m/s, $f_0 = 20$ kHz e velocità del suono $c = 343$ m/s si ottiene $\Delta f \approx 11{,}7$ Hz. Un altoparlante che riproduce una registrazione non ha labbra in movimento: il microfono sente la voce, ma **non** lo spostamento Doppler corrispondente ai fonemi.

**LipPass** (p. 71; *IEEE/ACM Transactions on Networking*, 2019), **prototipo**:

- analizza le differenze sottili ma distintive nei **movimenti della bocca** mentre si parla, e rileva gli spoofing con un'accuratezza del **93,1%**;
- usa i **segnali acustici** che **rimbalzano sul volto** dell'utente;
- ogni persona ha comportamenti unici (protrusione e chiusura delle labbra, estensione e contrazione della lingua, cambi dell'angolo della mandibola), che creano un **profilo Doppler** unico: autenticazione e liveness insieme.

**Onfido e LifeProof** (p. 72). Mentre VoiceGesture e LipPass sono prototipi, la tecnologia brevettata **LifeProof**, creata da **EYN** (società acquisita da Onfido), è la **prima applicata** nel settore ed è integrata nella piattaforma **Real Identity** di Onfido. Usa **suoni udibili e ultrasonici** del dispositivo mobile per rilevare **attivamente** la presenza di una persona viva, come un **sonar**.

> ⚠️ **Nome del prodotto.** Il titolo della slide scrive «LiveProof», il testo «LifeProof»; il nome riportato nel comunicato citato è **LifeProof**.

<!-- SOURCE_VISUAL id="TAB20-V29" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="68" type="immagini e grafici" description="Sincronia del movimento delle labbra con regioni verticali 60x1 pixel e flusso ottico" reason="Regioni e segnali visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V30" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="70" type="schema" description="VoiceGesture: smartphone come radar Doppler con tono a 20 kHz e riflessioni al microfono" reason="Principio mostrato nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte D — Conclusioni**

### **10. Conclusioni sulle due lezioni di spoofing**

_(slide pp. 73–76)_

**Un'osservazione generale** (p. 74). Dispositivi **molto costosi** possono essere ingannati con attacchi **molto economici**, anche **pochi giorni** dopo la presentazione del prodotto sul mercato (la slide riporta l'esempio di un articolo del 5 novembre 2017).

> ⚠️ **Sigla.** La p. 74 chiama «PAD» i sistemi d'attacco economici; secondo la terminologia ISO sono **strumenti d'attacco** (PAI), mentre PAD indica il rilevamento (Lezione 19, §5.3).

**Linee guida dalla letteratura e dal mercato** (p. 75):

| Linea guida | Motivo |
|---|---|
| i sensori con **nuove tecnologie** (3D, ultrasuoni, Doppler, multispettrale, frame rate molto elevati) sono **più difficili da ingannare** | l'attaccante deve riprodurre proprietà fisiche, non solo l'aspetto |
| i metodi di antispoofing e liveness vanno **continuamente rivisti e aggiornati**, considerando i metodi di **deep learning** più recenti | gli attacchi evolvono (Lezione 17) |
| le tecniche **challenge-response** **non** sono contromisure **definitive**, ma restano **valide** | TTS e manipolazione in tempo reale possono rispondere alle sfide |
| la **multimodalità** e i metodi **eterogenei** sono più **robusti**, ma più **complessi**, **costosi** e **lenti** | è molto più difficile ingannare più sensori contemporaneamente (Lezione 15) |

**L'ultima slide** (p. 76) riassume l'antispoofing con un'equazione grafica il cui secondo termine è **«intelligence»**: la difesa è soprattutto un processo continuo di conoscenza degli attacchi.

<!-- SOURCE_VISUAL id="TAB20-V31" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="74" type="articolo di stampa" description="Dispositivi costosi ingannati da attacchi economici pochi giorni dopo il lancio (5 novembre 2017)" reason="Articolo mostrato come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB20-V32" source="Lezione_20_Spoofing_Antispoofing_Iride_Impronta_Voce_ConsiderazioniFinali.pdf" page="76" type="equazione grafica" description="Antispoofing = intelligence" reason="Primo termine dell'equazione presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Impersonificazione** / **occultamento** (iride) | registrare o imitare l'iride di un altro / nascondere la propria texture, colliri |
| **Stampa** / **lente cosmetica** / **protesi** | supporto piatto / artefatto sull'occhio vivo / occhio artificiale |
| **Iride statica** / **dinamica** | singola immagine / movimenti e dilatazione nel tempo |
| **Stimolazione passiva** / **attiva** | si osserva / si provoca una risposta (luce, riflessi) |
| **Poche feature** / **molte feature** | distanza di Hamming più variabile, più falsi match / distribuzione impostori stretta |
| **Spoof cooperativo** / **non cooperativo** | stampo diretto dal proprietario / latente, riattivazione, sintesi, cadavere |
| **Antispoofing hardware** / **software** | temperatura, impedenza, ossimetria, OCT; costoso / immagini; economico e aggiornabile |
| **Software statico** / **dinamico** | una impressione: texture, pori / più frame: sudorazione, distorsione |
| **Sensore capacitivo** / **a ultrasuoni** | superficie, più facile da ingannare / strati interni 3D, antispoofing migliore |
| **Feature vocali di alto livello** / **di basso livello** | robuste al canale ma difficili e dipendenti dal testo / facili ma sensibili al rumore |
| **Text-dependent** / **text-independent** | frase nota, cooperazione / frase libera, call center |
| **Replay** / **voice conversion** / **TTS** | registrazione del genuino / voce dell'attaccante trasformata / voce generata da testo |
| **Attacco avversario** / **voice cloning** | perturbazione contro un modello / voce realistica del bersaglio |
| **Challenge-response** / **contromisura definitiva** | alza il costo dell'attacco / non esiste, va aggiornata |

---

### **12. Sintesi della lezione**

> ✅ **In sintesi.**
> - **Iride**: attacchi di impersonificazione (stampe, lenti, display, protesi, iridi sintetiche e post-mortem), occultamento (palpebre, sguardo, rotazione, colliri) e ibridi; dataset come LivDet e CASIA-Iris-Fake; PAD statici o dinamici, passivi o attivi; il caso Galaxy S8 (NIR senza filtro, 200 mm a 5 m, stampa laser e lente bagnata); CNN cross-dataset; liveness con dilatazione della pupilla e, in prospettiva, micro-saccadi.
> - **Impronta**: stampo diretto, gummy finger da latente, riattivazione, sintesi da template e cadavere; antispoofing hardware (temperatura, impedenza, ossimetria, OCT) e software statico o dinamico (texture, pori, sudorazione, distorsione); sensori a ultrasuoni, IA sul sensore, touchless multibanda, battito cardiaco, doppia illuminazione; MasterPrints.
> - **Palmo**: Amazon One, PalmSecure con dichiarazioni da leggere con la regola del 3, spoofing con modelli in cera al congresso CCC 2018.
> - **Voce**: feature di alto e basso livello, ASV dipendente o indipendente dal testo; cinque attacchi (impersonificazione, replay, voice conversion, sintesi con WaveNet, Tacotron e VALL-E, toni avversari); contromisure con feature ad alta frequenza, challenge-response, sincronia labbra-voce, Doppler acustico (VoiceGesture, LipPass, LifeProof).
> - **Conclusioni**: attacchi economici battono dispositivi costosi; sensori con nuove tecnologie, aggiornamento continuo, challenge-response non definitivo e multimodalità sono le linee guida principali.
