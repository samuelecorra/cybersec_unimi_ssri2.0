# **Lezione 3: Impronte digitali a contatto — sensori, classificazione, elaborazione e matching**

---

### **0. Panoramica della lezione**

_(slide pp. 1–3)_

La Lezione 3 apre la sequenza dei **principali tratti monomodali** con l'**impronta digitale acquisita a contatto** (le impronte **senza contatto** sono presentate più avanti nel corso). Le slide sono marcate «+ Nuovi Argomenti»: al ripasso del corso precedente si aggiungono sensori recenti, prestazioni aggiornate e sistemi commerciali.

Il percorso della lezione:

1. biologia e caratteristiche dell'impronta;
2. sensori a stato solido e a ultrasuoni, sistemi commerciali e prestazioni;
3. classificazione delle impronte;
4. analisi sui tre livelli;
5. estrazione delle minuzie (metodo classico e *direct gray scale*);
6. matching;
7. sistemi AFIS nazionali e trend.

> **Richiamo — Sistemi Biometrici.** Le impronte sono trattate estesamente in [L07 — Storia e classificazione](<../../../anno3/6_Sistemi_Biometrici/L07 - Impronte digitali – storia, classificazione e stato dell’arte.md>), [L08 — Sensori, rappresentazione e non-unicità](<../../../anno3/6_Sistemi_Biometrici/L08 - Impronte Digitali, Sensori, Rappresentazione, Compressione e Non-Unicità.md>), [L09 — Prefiltraggio, enhancement e feature extraction](<../../../anno3/6_Sistemi_Biometrici/L09 - Prefiltraggio, Enhancement e Feature Extraction nelle impronte digitali.md>), [L10A — Matching e tassi di errore](<../../../anno3/6_Sistemi_Biometrici/L10A - Algoritmi di Matching, Tassi di Errore e Stato dell’Arte.md>) e [L10B — Spoofing](<../../../anno3/6_Sistemi_Biometrici/L10B - Spoofing dei sensori di impronte - attacchi cooperativi e non-cooperativi.md>). Qui ci si concentra sugli elementi richiamati o aggiornati dal docente.

---

### **1. Il tratto: che cosa sono le impronte digitali**

_(slide pp. 4–7)_

#### **1.1 Biologia**

_(slide p. 4)_

> 📌 **Impronte digitali.** Sono **creste e valli della pelle** (*dermatoglyphics*) presenti sui palmi e sulle dita di molti animali (la slide mostra anche l'impronta di un **koala**). Le creste di attrito si chiamano *friction ridges*; lungo le creste si trovano i **pori** (*pores*).

Sono tratti biometrici **stabili dall'ottavo mese di gestazione**, salvo **abrasioni, malattie o incidenti gravi**.

<!-- SOURCE_VISUAL id="TAB03-V01" source="Lezione_03_Approfondimento_Impronta.pdf" page="4" type="fotografie" description="Creste e valli della pelle, friction ridges e pori; impronta di un koala" reason="Illustra la struttura fisica dell'impronta" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2 Scale dei sistemi e applicazioni**

_(slide pp. 5–6)_

I sistemi basati su impronta esistono a **diverse scale**: sistemi **integrati**, **smartcard**, sistemi **per PC**, dispositivi **stand-alone**, fino ai sistemi **distribuiti** su rete (**AFIS**, *Automated Fingerprint Identification System*).

| Forensi | Governative | Commerciali |
|---|---|---|
| identificazione dei corpi, delle persone, di terroristi | carte d'identità e passaporti | ATM (bancomat) |
| bambini scomparsi | patenti di guida | logon su terminali |
| attività investigativa | controllo accessi e delle frontiere | e-commerce, servizi online |
| | controllo documenti | telefoni cellulari, PDA, controllo accessi |

<!-- SOURCE_VISUAL id="TAB03-V02" source="Lezione_03_Approfondimento_Impronta.pdf" page="5" type="fotografie" description="Sistemi a diverse scale: integrati, smartcard, per PC, stand-alone, distribuiti AFIS su LAN" reason="Mostra l'ampiezza di scala delle soluzioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3 Punti di forza e debolezze**

_(slide p. 7)_

| Punti di forza | Debolezze |
|---|---|
| tecnologia **matura**, ampiamente controllata, **altamente accurata**, funzionante in molti ambienti operativi | **nessun dispositivo** riesce ad acquisire le impronte di una frazione della popolazione, solitamente il **4%** |
| acquisizione **mediamente facile ed ergonomica** | l'**accuratezza tende a degradare nel tempo** |
| possibilità di **usare più dita**, aumentando notevolmente l'accuratezza | essendo associata ad **applicazioni forensi**, alcune persone provano **disagio** nel fornire il tratto |

> 💡 **Il 4% non acquisibile.** Corrisponde a un tasso di mancato enrollment (*Failure To Enrol*): lavoratori manuali con creste consumate, anziani con pelle poco elastica, persone con patologie cutanee. Un sistema per la popolazione generale deve prevedere un **metodo alternativo** per questi utenti, per esempio un secondo tratto.

---

### **2. Sensori e sistemi commerciali**

_(slide pp. 8–20)_

#### **2.1 Immagini e proprietà dei sensori**

_(slide pp. 8–10)_

I vari sensori producono immagini con **caratteristiche molto diverse** tra loro: ottici, capacitivi, **termici a scorrimento** (*thermal sweeping*), **tridimensionali**.

Nella scelta di un sensore si controllano:

- **risoluzione** (dpi);
- **area di acquisizione**;
- **numero di pixel** e **bit per pixel** (8, 16, 24 bit);
- **contrasto**;
- **distorsione geometrica**.

<!-- SOURCE_VISUAL id="TAB03-V03" source="Lezione_03_Approfondimento_Impronta.pdf" page="9" type="confronto di immagini" description="Immagini della stessa impronta prodotte da sensori diversi, tra cui termico a scorrimento e tridimensionale" reason="Mostra quanto cambia il sample a seconda del sensore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2 Sensori per smartphone: Synaptics**

_(slide pp. 11–14)_

| Sensore | Caratteristiche (slide) |
|---|---|
| **Synaptics SentryPoint** (*Ultra Slim Area Touch*) | **Encryption**: tutti i dati biometrici sono cifrati e firmati digitalmente con **AES a 256 bit** e **TLS**, contro intercettazione, manomissione e frode; architettura **Match-in-Sensor**; tecnologia **anti-spoof** |
| **Synaptics FS4500** (capacitivo, Natural ID) | sensore **6×6 mm**; rilevamento capacitivo **attraverso vetro**, pulsanti in **ceramica** e **vetro di copertura incassato fino a 300 µm**; oltre **300 milioni di unità**; clienti Lenovo, Huawei, … |
| **Synaptics FS7600** (capacitivo, match-in-sensor) | distanza di rilevamento fino a **0,2 mm**: può stare **sotto vetro**, sotto **mylar** o con un semplice strato protettivo; clienti Xiaomi, PQI |
| **Synaptics Clear ID FS9500** | sensore **ottico in-display**; funziona con dita **bagnate, asciutte e fredde**; facilmente interfacciabile con **Arduino, Raspberry Pi** e altri microcontrollori |

La tecnologia **«Under the glass»** rende il sensore **ancora meno invasivo** e «immerso» nel dispositivo.

> 💡 **Capacitivo e distanza.** Un sensore capacitivo misura la variazione di capacità tra la pelle e gli elettrodi: più spesso è lo strato che li separa, più il segnale si attenua. Per questo le specifiche indicano lo **spessore massimo attraversabile** (300 µm, 0,2 mm): è ciò che decide se il sensore può stare sotto il vetro.

<!-- SOURCE_VISUAL id="TAB03-V04" source="Lezione_03_Approfondimento_Impronta.pdf" page="12" type="fotografie prodotto" description="Synaptics FS4500 Natural ID sotto vetro, pulsanti ceramici e cover glass incassato" reason="Mostra l'integrazione under the glass" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V05" source="Lezione_03_Approfondimento_Impronta.pdf" page="14" type="fotografie prodotto" description="Synaptics Clear ID FS9500, sensore ottico in-display" reason="Esempio di sensore ottico sotto lo schermo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3 Sensori a ultrasuoni: Qualcomm 3D Sonic**

_(slide pp. 15–16)_

- **Qualcomm 3D Sonic Sensors**: i **primi lettori di impronta a ultrasuoni** su dispositivi mobili.
- **Qualcomm 3D Sonic Max**: area ampia che permette di acquisire **due impronte** contemporaneamente, aprendo moltissimi scenari (**multimodale**, **classificazione**, **anti-spoofing**); **resistenza ai contaminanti** classici.

> 💡 **Perché gli ultrasuoni.** L'onda acustica penetra lo strato superficiale e restituisce una mappa **tridimensionale** delle creste: è meno sensibile a sporco e umidità di un sensore ottico e rende più difficile l'uso di repliche piatte.

<!-- SOURCE_VISUAL id="TAB03-V06" source="Lezione_03_Approfondimento_Impronta.pdf" page="15" type="fotografie prodotto" description="Qualcomm 3D Sonic, primo lettore di impronte a ultrasuoni su dispositivi mobili" reason="Esempio di sensore a ultrasuoni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V07" source="Lezione_03_Approfondimento_Impronta.pdf" page="16" type="fotografie prodotto" description="Qualcomm 3D Sonic Max con acquisizione di due impronte e resistenza ai contaminanti" reason="Mostra l'area estesa per due dita" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.4 Sistemi per la sicurezza fisica di alta gamma**

_(slide pp. 17–18)_

**Bosch Suprema BioEntry W2** e i risultati di valutazioni indipendenti riportati dalla slide:

| Valutazione | Risultato |
|---|---|
| **FVC-onGoing** competition 2010, algoritmo Suprema **SFCore** | **EER 0,258%** |
| **MINEX** (*Minutiae Interoperability Exchange*) del **NIST**, 2008, template **INCITS 378**; FNMR a **FMR fisso = 0,01** su **125.000** confronti genuini | algoritmo *matcher* Suprema **0,23%**, algoritmo *extractor* **0,30%** |
| Benchmark **FMISO-STD-1.0** | prestazioni di matching su template **ISO 19794-2**, per l'interoperabilità |
| Stato dell'arte 2025 (nota della slide) | migliori sistemi con **EER < 0,06%** |

La sintesi della slide: **INCITS: FNMR = 0,23% @ FAR = 0,01**; **FVC-onGoing: EER 0,258%**. INCITS è l'*InterNational Committee for Information Technology Standards*.

> ⚠️ **Leggere «FNMR = 0,23% @ FMR = 0,01».** La notazione «@» indica il **punto di lavoro**: il FNMR è misurato alla soglia che produce un FMR dell'**1%**. Non è confrontabile direttamente con un EER, che è misurato in un altro punto della curva. Confrontare 0,23% con 0,258% come se fossero la stessa grandezza è un errore.

**IDEMIA linea SIGMA** (p. 18), esempio di terminale di fascia alta:

- **SIGMA Extreme**, sensore **23×23 mm**, **fake detection elettrica**;
- fino a **250.000 utenti registrati**;
- lettore di carte (**iCLASS**, **MIFARE**: tecnologie RFID con cifratura);
- temperatura di esercizio **da −20 °C a 60 °C**, protezione **IP65** (resiste alla pioggia);
- connettività **Ethernet, USB, Wi-Fi**; alimentazione **12–14 V, PoE**;
- IDEMIA ha installato **5 milioni** di dispositivi.

> 💡 **Il template ISO come chiave dell'interoperabilità.** Test come MINEX e FMISO misurano quanto bene un algoritmo confronta template **standard** (INCITS 378, ISO/IEC 19794-2) prodotti da estrattori di altri fornitori: è la condizione per poter cambiare sensore o fornitore senza ripetere l'enrollment di tutti gli utenti (Lezione 2, §13).

<!-- SOURCE_VISUAL id="TAB03-V08" source="Lezione_03_Approfondimento_Impronta.pdf" page="18" type="fotografie prodotto" description="Terminali IDEMIA SIGMA Extreme con sensore 23x23 mm, lettore di carte e specifiche ambientali" reason="Esempio concreto di terminale di alta gamma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.5 L'immagine dell'impronta**

_(slide p. 19)_

Il **sample** di un'impronta è un'**immagine in toni di grigio**: si controllano **risoluzione** e **bit per pixel**. Esempio: l'**FBI** digitalizza le impronte del DB nazionale a **500 dpi** con **8 bit per pixel**; una **cartella con 10 impronte occupa circa 10 MB**.

> 💡 **Perché servono standard di compressione.** Con archivi di decine di milioni di cartellini, 10 MB ciascuno significano centinaia di terabyte. Per questo l'FBI ha adottato la compressione **WSQ** (*Wavelet Scalar Quantization*), citata anche nell'SDK Innovatrics a p. 57. **Richiamo:** [L08 — Compressione](<../../../anno3/6_Sistemi_Biometrici/L08 - Impronte Digitali, Sensori, Rappresentazione, Compressione e Non-Unicità.md>).

#### **2.6 Spoofing: dito reale, silicone e gelatina**

_(slide p. 20)_

La slide confronta le immagini ottenute da un **sensore ottico** e da un **sensore capacitivo** con un **dito reale**, un **dito in silicone** e un **dito in gelatina**. Osservazione chiave: il **sensore capacitivo non riesce a rilevare bene il dito in silicone**, perché il silicone è un isolante e non ha le proprietà elettriche della pelle: è una forma di **auto-difesa** intrinseca del sensore.

> ⚠️ **Auto-difesa parziale.** La gelatina, che contiene acqua, ha proprietà elettriche più vicine a quelle della pelle: il sensore capacitivo che respinge il silicone può essere ingannato da altri materiali. Lo spoofing delle impronte è trattato nella Lezione 20.

<!-- SOURCE_VISUAL id="TAB03-V09" source="Lezione_03_Approfondimento_Impronta.pdf" page="20" type="griglia di immagini" description="Dito reale, in silicone e in gelatina acquisiti con sensore ottico e capacitivo; il capacitivo non rileva bene il silicone" reason="Il confronto tra materiali e sensori è puramente visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Unicità e classificazione**

_(slide pp. 21–27)_

#### **3.1 Unicità**

_(slide pp. 21–22)_

La slide sull'unicità ricorda che le stime della probabilità che due impronte coincidano diventano **ancora più conservative** se si considerano, oltre alle minuzie, **molti altri parametri** disponibili.

> **Richiamo — Sistemi Biometrici, L08.** I modelli probabilistici di unicità (da Galton ai modelli basati sulle minuzie) sono in [L08 — Non-unicità](<../../../anno3/6_Sistemi_Biometrici/L08 - Impronte Digitali, Sensori, Rappresentazione, Compressione e Non-Unicità.md>).

#### **3.2 Il sistema di classificazione: core e delta**

_(slide p. 23)_

Le impronte si dividono in tre grandi classi, con sottoclassi:

| Classe | Sottoclassi |
|---|---|
| **Arch** | plain, tented |
| **Loop** | left, right |
| **Whorl** | plain, twin loop (e varianti) |

La classe si determina tramite:

- l'individuazione degli eventuali punti singolari **core** (centro del disegno) e **delta** (punto dove le creste divergono formando una sorta di triangolo);
- lo studio degli **orientamenti delle creste** (ridge).

<!-- SOURCE_VISUAL id="TAB03-V10" source="Lezione_03_Approfondimento_Impronta.pdf" page="23" type="immagine annotata" description="Impronta con i punti singolari core e delta evidenziati" reason="Localizzazione visiva di core e delta" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3 Arches**

_(slide p. 24)_

| Tipo | Definizione (slide, in italiano) |
|---|---|
| **Plain arch** | le creste entrano da un lato dell'impronta e fluiscono, o tendono a fluire, fuori dall'altro lato, con un'**elevazione o un'onda al centro** |
| **Tented arch** | la maggior parte delle creste entra da un lato ed esce dall'altro, ma le creste **al centro no**: tendono a formare una **spinta verso l'alto o un angolo** al centro del disegno |

<!-- SOURCE_VISUAL id="TAB03-V11" source="Lezione_03_Approfondimento_Impronta.pdf" page="24" type="esempi di impronte" description="Plain arch e tented arch" reason="Riconoscere le due sottoclassi richiede l'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.4 Loops**

_(slide p. 25)_

> 📌 **Loop.** Disegno in cui una o più creste entrano da un lato dell'impronta, **ripiegano** (*recurve*), toccano o attraversano una linea immaginaria tracciata **dal delta al core**, e terminano (o tendono a terminare) **verso lo stesso lato** da cui sono entrate.

Elementi essenziali di un loop:

1. **ripiegamento sufficiente** (*sufficient recurve*);
2. **un solo delta**;
3. un **conteggio di creste** (*ridge count*) attraverso una cresta che forma l'ansa.

Sottoclassi: **left loop** e **right loop**, a seconda del lato verso cui si apre l'ansa.

<!-- SOURCE_VISUAL id="TAB03-V12" source="Lezione_03_Approfondimento_Impronta.pdf" page="25" type="esempi di impronte" description="Left loop e right loop" reason="Distinguere il verso dell'ansa richiede l'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.5 Whorls**

_(slide pp. 26–27)_

I whorl hanno **almeno due delta**, con un ripiegamento davanti a ciascuno.

| Tipo | Definizione (slide) |
|---|---|
| **Plain whorl** | due delta e almeno una cresta che compie un **circuito completo** (spirale, ovale, cerchio o variante); una linea immaginaria tra i due delta **deve toccare** una cresta ripiegata nell'area del disegno |
| **Central pocket loop** | due delta e almeno una cresta che compie un circuito completo; la linea tra i due delta **non deve toccare né attraversare** creste ripiegate nell'area interna |
| **Double loop** | **due formazioni ad ansa separate**, con due insiemi distinti di «spalle» e due delta |
| **Accidental** | **combinazione di due tipi diversi** di disegno (escluso il plain arch) con due o più delta, oppure disegno che soddisfa parzialmente più definizioni o **nessuna** |

> ⚠️ **Plain whorl contro central pocket loop.** La differenza sta tutta nella **linea tra i due delta**: se tocca una cresta ripiegata è un plain whorl, se non la tocca è un central pocket loop. È il tipo di dettaglio che distingue le risposte in una domanda a scelta multipla.

<!-- SOURCE_VISUAL id="TAB03-V13" source="Lezione_03_Approfondimento_Impronta.pdf" page="26" type="esempi di impronte" description="Plain whorl e central pocket loop con la linea tra i due delta" reason="La differenza tra le due sottoclassi è geometrica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V14" source="Lezione_03_Approfondimento_Impronta.pdf" page="27" type="esempi di impronte" description="Double loop e accidental" reason="Esempi visivi delle sottoclassi rimanenti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. I tre livelli di analisi**

_(slide pp. 28–32)_

Un'impronta si esamina su **tre livelli**:

| Livello | Scala | Che cosa si osserva (slide) | Risoluzione necessaria |
|---|---|---|---|
| **I — globale** | intera impronta | **flusso delle linee** (arch, loop, whorl e sottoclassi); **punti singolari** (core, delta); **forma** dell'impronta; **orientamento**; **frequenza** delle creste | anche bassa |
| **II — locale** | singole creste | fino a circa **150 caratteristiche locali** delle creste (**minuzie**); le due principali sono **terminazioni** e **biforcazioni** | standard (500 dpi) |
| **III — ultra-fine** | dettagli intra e inter-cresta | **pori** (forma e numero), **incipient ridges**, variazioni di forma delle linee, **cicatrici** e **pieghe** (permanenti), **deformazioni** e **verruche** (non permanenti) | almeno **1000 dpi**, condizioni ideali |

- L'**FBI**, per i sistemi automatici (**AFIS**), usa **solo terminazioni e biforcazioni**.
- I dettagli del livello III sono **altamente distintivi**, ma i **pori** hanno dimensione **60–250 µm** e si rilevano solo ad altissima risoluzione. A **800 dpi** si vedono solo alcuni pori (dipende anche dalla pressione); a **1000 dpi** i pori sono visibili.

> 💡 **Verifica dimensionale.** A 500 dpi un pixel misura $25{,}4\ \text{mm} / 500 \approx 51\ \mu\text{m}$: un poro da 60 µm occupa poco più di un pixel ed è di fatto invisibile. A 1000 dpi il pixel misura circa 25 µm e il poro copre almeno 2–10 pixel per lato. Da qui il requisito dei 1000 dpi.

<!-- SOURCE_VISUAL id="TAB03-V15" source="Lezione_03_Approfondimento_Impronta.pdf" page="29" type="immagine annotata" description="Livello I: flusso delle linee, core e delta, orientamento e frequenza" reason="Mostra le feature globali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V16" source="Lezione_03_Approfondimento_Impronta.pdf" page="30" type="immagine annotata" description="Livello II: minuzie, terminazioni e biforcazioni" reason="Mostra le feature locali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V17" source="Lezione_03_Approfondimento_Impronta.pdf" page="31" type="immagine annotata" description="Livello III: pori, incipient ridges, variazioni di forma, cicatrici, pieghe, deformazioni, verruche" reason="Mostra i dettagli ultra-fini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V18" source="Lezione_03_Approfondimento_Impronta.pdf" page="32" type="confronto di risoluzioni" description="Stessa impronta a 800 dpi (pochi pori visibili) e a 1000 dpi (pori visibili)" reason="Evidenza visiva del requisito di risoluzione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Elaborazione delle impronte**

_(slide pp. 33–37)_

#### **5.1 Sequenza tipica dei passi**

_(slide pp. 33–34)_

Nel modulo di **estrazione delle feature** si eseguono tipicamente, dall'immagine iniziale al template:

1. **filtraggio iniziale**;
2. **manipolazione dell'immagine** (*enhancement*);
3. **estrazione delle feature**;
4. **codifica** (talvolta rappresentata esternamente al modulo di feature extraction).

#### **5.2 Segmentazione**

_(slide p. 35)_

Gli algoritmi di segmentazione separano il **foreground** (l'impronta) dal **background** (lo sfondo), per concentrarsi solo sulle regioni utili.

> 📌 **Tecnica di base.** Si calcola la **varianza locale** dell'immagine (oppure il **modulo del gradiente**) su **blocchi 16×16** e si **scartano i blocchi** con valore **sotto una soglia** prefissata.

Il motivo: nelle regioni con impronta l'alternanza di creste scure e valli chiare produce **alta varianza**; lo sfondo è uniforme e ha varianza bassa.

<!-- SOURCE_VISUAL id="TAB03-V19" source="Lezione_03_Approfondimento_Impronta.pdf" page="35" type="prima e dopo" description="Segmentazione foreground e background tramite varianza locale su blocchi 16x16" reason="Mostra il risultato della segmentazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3 Filtri contestuali**

_(slide p. 36)_

I filtri contestuali lavorano sull'immagine tramite una **convoluzione con una maschera di filtraggio**. A seconda della maschera il filtro **aumenta o diminuisce** alcune caratteristiche. Una parte del filtro **controlla la porzione di immagine in esame** e **adatta i parametri della maschera** (per esempio l'orientamento locale delle creste): per questo il filtro è detto **contestuale**.

$$O(x, y) = I(x, y) * k(x, y)$$

dove $I$ è l'immagine in ingresso, $k$ la maschera (il cui orientamento dipende dal contesto locale), $*$ la convoluzione e $O$ l'immagine filtrata. La convoluzione è approfondita nella Lezione 4.

<!-- SOURCE_VISUAL id="TAB03-V20" source="Lezione_03_Approfondimento_Impronta.pdf" page="36" type="schema" description="Filtro contestuale: convoluzione con maschera e algoritmo che adatta i parametri della maschera alla porzione di immagine" reason="Schema del ciclo di adattamento della maschera" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4 Panoramica delle tecniche di estrazione**

_(slide p. 37)_

| Approccio | Catena |
|---|---|
| **Classico** | prefiltering → enhancement → **binarization** → **thinning** (scheletrizzazione) → minutiae extraction → postprocessing |
| **Direct gray scale** | minutiae extraction direttamente sull'immagine in toni di grigio → postprocessing |

> 💡 **Il compromesso.** L'approccio classico è semplice da capire, ma binarizzazione e thinning **perdono informazione** e introducono artefatti (spezzature, ponti spuri). Il direct gray scale evita questi passaggi a costo di un algoritmo di tracciamento più complesso.

<!-- SOURCE_VISUAL id="TAB03-V21" source="Lezione_03_Approfondimento_Impronta.pdf" page="37" type="diagramma a flusso" description="Approccio classico (prefiltering, enhancement, binarization, thinning, minutiae extraction, postprocessing) contro direct gray scale extraction" reason="Confronto visivo delle due pipeline" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Feature di livello I**

_(slide pp. 38–39)_

#### **6.1 Ridge counting**

_(slide p. 38)_

> 📌 **Ridge count.** Numero di **creste attraversate** da una linea immaginaria tra **due minuzie**.

Nell'esempio: tra $a$ e $b$ **4 creste**, tra $b$ e $c$ **0 creste**, tra $c$ e $a$ **3 creste**.

> ⚠️ **Livello I anche se parte dalle minuzie.** Il ridge count si calcola a partire da minuzie (livello II), ma è considerato di **livello I** perché non descrive l'intorno di un punto: descrive **che cosa accade nell'impronta tra punti anche lontani**.

<!-- SOURCE_VISUAL id="TAB03-V22" source="Lezione_03_Approfondimento_Impronta.pdf" page="38" type="immagine annotata" description="Ridge counting tra le minuzie a, b, c: 4, 0 e 3 creste" reason="Il conteggio va verificato sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Frequenza spaziale delle creste**

_(slide p. 39)_

La **ridge frequency** misura **quanto sono strette o larghe** le creste nelle varie regioni. Si prende una finestra (nell'esempio **50×50 pixel**), si tracciano i valori di intensità dei pixel lungo la direzione ortogonale alle creste, si approssima il profilo con un'**interpolazione sinusoidale** e si calcola:

$$f = \frac{1}{\bar{d}}$$

dove $\bar{d}$ è la **distanza media tra due picchi consecutivi** del profilo.

<!-- SOURCE_VISUAL id="TAB03-V23" source="Lezione_03_Approfondimento_Impronta.pdf" page="39" type="grafico" description="Profilo di intensità dei pixel in una finestra 50x50 con interpolazione sinusoidale e picchi consecutivi" reason="Mostra come si misura la distanza tra picchi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Estrazione delle minuzie (livello II)**

_(slide pp. 40–43)_

#### **7.1 Crossing number**

_(slide pp. 40–41)_

È l'**estrattore di minuzie più frequente** e lavora sull'**immagine scheletrizzata** (creste larghe un pixel, valori 0/1). Si esamina **un punto alla volta**: per ogni pixel di cresta $p$ si percorrono in ordine gli **8 vicini** $n_1, \dots, n_8$ e si conta il **numero di transizioni** lungo il percorso:

$$CN(p) = \frac{1}{2} \sum_{k=1}^{8} \left| n_k - n_{(k+1) \bmod 8} \right|$$

con la convenzione che l'indice ritorni a capo ($n_0 \equiv n_8$), così che il percorso sia chiuso. Ogni cresta che attraversa l'intorno genera due transizioni (0→1 e 1→0), da cui il fattore $\frac{1}{2}$.

| Valore | Interpretazione |
|---|---|
| $CN(p) = 1$ | **fine riga** (terminazione) |
| $CN(p) = 3$ | **biforcazione** |
| altrimenti | **nessuna** minuzia |

> 💡 **Nota didattica (non presente nelle slide).** $CN(p) = 2$ corrisponde a un normale punto interno di una cresta (una linea entra e una esce); valori maggiori di 3 indicano incroci, rari e spesso dovuti a rumore.

<!-- SOURCE_VISUAL id="TAB03-V24" source="Lezione_03_Approfondimento_Impronta.pdf" page="40" type="schema" description="Intorno a 8 pixel del punto p con formula del crossing number e casi fine riga e biforcazione" reason="La numerazione dei vicini è definita nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V25" source="Lezione_03_Approfondimento_Impronta.pdf" page="41" type="pattern di pixel" description="Esempi di pattern 3x3 associati a fine riga e biforcazioni nelle immagini scheletrizzate" reason="Riconoscere i pattern richiede l'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 Structural post processing**

_(slide p. 42)_

I moduli di **post processing strutturale** sono tipicamente **basati su regole**: controllano le caratteristiche dello **scheletro adiacente** alla minuzia candidata per decidere se **tenerla o scartarla**. Le regole maggiormente usate sono **8** (illustrate nella figura della slide): tipicamente eliminano minuzie spurie dovute a spezzature, ponti, piccoli rami e bordi dell'impronta.

<!-- SOURCE_VISUAL id="TAB03-V26" source="Lezione_03_Approfondimento_Impronta.pdf" page="42" type="schema delle regole" description="Le 8 regole più usate di structural post processing per tenere o scartare le minuzie candidate" reason="Le 8 regole sono descritte solo graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 Direct gray scale extraction**

_(slide p. 43)_

1. Si sceglie un **insieme di punti di partenza** disposti su una **griglia**.
2. Si **segue ogni cresta** dal punto di partenza, lavorando sui livelli di intensità, fino a trovare una **fine riga** o una **biforcazione**.
3. Si usa una strategia di **etichettatura** (*labellizzazione*) per **evitare di seguire più volte** lo stesso pixel di cresta.

<!-- SOURCE_VISUAL id="TAB03-V27" source="Lezione_03_Approfondimento_Impronta.pdf" page="43" type="schema" description="Tracciamento delle creste sull'immagine in toni di grigio a partire da punti su griglia" reason="Mostra il percorso di tracciamento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Matching delle impronte**

_(slide pp. 44–50)_

#### **8.1 Livello I: filtri di Gabor**

_(slide p. 44)_

Il matching di livello I usa **filtri di Gabor**: l'immagine viene convoluta con un filtro $k(x, y)$ ruotato di un **angolo parametrico**, $O(x, y) = I(x, y) * k(x, y)$. La slide cita il **filtro di Gabor a simmetria pari**, in cui $\Phi$ è l'**orientamento**, $f$ la **frequenza** dell'onda piana sinusoidale e $\sigma_x$, $\sigma_y$ le **costanti spaziali** dell'inviluppo gaussiano lungo $x$ e $y$.

> ⚠️ **Formula ricostruita.** Nel PDF l'espressione del filtro è un'immagine e non è presente nel livello testo. La forma standard (Hong, Wan e Jain, 1998), coerente con le variabili elencate nella slide, è:
>
> $$h(x, y; \Phi, f) = \exp\left\{-\frac{1}{2}\left[\frac{x_\Phi^2}{\sigma_x^2} + \frac{y_\Phi^2}{\sigma_y^2}\right]\right\} \cos(2\pi f\, x_\Phi)$$
>
> con $x_\Phi = x\cos\Phi + y\sin\Phi$ e $y_\Phi = -x\sin\Phi + y\cos\Phi$. Va verificata sulla slide.

L'interpretazione: il coseno è un'**onda** con la stessa frequenza delle creste, orientata come le creste; l'inviluppo gaussiano **limita** il filtro a un intorno locale. Il filtro risponde forte dove l'immagine contiene creste di quella frequenza e orientamento.

> **Richiamo — Sistemi Biometrici, L09.** I filtri di Gabor di Hong, Wan e Jain sono trattati in [L09](<../../../anno3/6_Sistemi_Biometrici/L09 - Prefiltraggio, Enhancement e Feature Extraction nelle impronte digitali.md>).

<!-- SOURCE_VISUAL id="TAB03-V28" source="Lezione_03_Approfondimento_Impronta.pdf" page="44" type="formula e immagini filtrate" description="Filtro di Gabor a simmetria pari con orientamento Phi, frequenza f e costanti sigma; convoluzione O = I * k con rotazione parametrica" reason="Verificare la formula del filtro, presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2 Livello I: FingerCode**

_(slide p. 45)_

Il template **FingerCode** è importante perché:

- è di **livello I**;
- **funziona anche con basse risoluzioni**;
- si confronta con una **metrica euclidea**;
- ha **bassa complessità** di matching.

> 💡 **Nota didattica (non presente nelle slide).** Nella proposta originale (Jain et al., 2000) si individua il core, si divide la regione attorno in **settori** concentrici, si filtra l'immagine con un **banco di filtri di Gabor** a più orientamenti e per ogni settore si calcola la deviazione media dei valori filtrati. Il vettore di queste grandezze ha **lunghezza fissa**: per questo basta una distanza euclidea, senza allineare minuzie.

<!-- SOURCE_VISUAL id="TAB03-V29" source="Lezione_03_Approfondimento_Impronta.pdf" page="45" type="schema" description="FingerCode: settori attorno al core, filtraggio di Gabor e vettore di feature di livello 1" reason="Mostra la tassellazione e il vettore risultante" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.3 Livello II: matching basato sulle minuzie**

_(slide pp. 46–48)_

> 📌 **Criterio di corrispondenza.** Due minuzie sono considerate **corrispondenti** se la loro **distanza spaziale** $sd$ e la loro **differenza di direzione** $dd$ sono **minori di soglie prefissate**.

> 💡 **Formalizzazione (non presente nelle slide).** Per una minuzia $m = (x, y, \theta)$ dell'input e $m' = (x', y', \theta')$ del template:
>
> $$sd = \sqrt{(x' - x)^2 + (y' - y)^2} \leq r_0 \qquad dd = \min\big(|\theta' - \theta|,\ 360^\circ - |\theta' - \theta|\big) \leq \theta_0$$
>
> Il $\min$ nella differenza angolare tiene conto della circolarità: 350° e 10° differiscono di 20°, non di 340°.

Si tratta di un **matching non esatto di pattern di punti** (*non-exact point pattern matching*), perché bisogna gestire:

- **roto-traslazioni** tra le due acquisizioni;
- **distorsioni non lineari** (la pelle è elastica e si deforma premendo sul sensore);
- **minuzie false** (spurie);
- **minuzie mancanti**;
- **numero non costante** di minuzie.

<!-- SOURCE_VISUAL id="TAB03-V30" source="Lezione_03_Approfondimento_Impronta.pdf" page="47" type="immagine esemplificativa" description="Matching di livello 2 classico con angoli delle minuzie" reason="Illustra la corrispondenza tra minuzie con orientamento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB03-V31" source="Lezione_03_Approfondimento_Impronta.pdf" page="48" type="immagine esemplificativa" description="Matching con minuzie classico su due impronte sovrapposte" reason="Mostra le coppie di minuzie corrispondenti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.4 Hybrid matching**

_(slide p. 49)_

Il **matching ibrido** confronta **sottoinsiemi** di minuzie tra *input* e *reference* usando insieme **tipi di minuzia**, **angoli** e **distanze**. Considerare strutture locali (una minuzia con le vicine) rende il confronto più robusto a roto-traslazioni e distorsioni rispetto al confronto di singoli punti.

<!-- SOURCE_VISUAL id="TAB03-V32" source="Lezione_03_Approfondimento_Impronta.pdf" page="49" type="schema" description="Hybrid matching: confronto di sottoinsiemi di minuzie tra input e reference con tipi, angoli e distanze" reason="Illustra le strutture locali confrontate" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.5 Esempio conclusivo sui tre livelli**

_(slide p. 50)_

Sulla stessa impronta si estraggono feature a tutti e tre i livelli: **livello I**, la mappa delle creste; **livello II**, le minuzie; **livello III**, i pori. Un sistema che combina più livelli è più accurato e più difficile da ingannare.

<!-- SOURCE_VISUAL id="TAB03-V33" source="Lezione_03_Approfondimento_Impronta.pdf" page="50" type="immagini affiancate" description="Feature estratte sulla stessa impronta ai tre livelli: mappa dei ridge, minuzie, pori" reason="Sintesi visiva dei tre livelli" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Anche gli esperti sbagliano**

_(slide p. 51)_

La slide «**Dove è l'errore?**» presenta casi in cui **ha fallito l'esaminatore umano**: le impronte erano **diverse**.

| Caso | Impronta latente | Esito |
|---|---|---|
| Attentati di **Madrid** (2004) | latente trovata sulla scena | l'esaminatore indica **Brandon Mayfield** (**falso positivo**); il vero attentatore era **Ouhnane Daoud** |
| Omicidio di **Marion Ross** (Scozia) | latente sulla scena dell'omicidio | attribuita a **Shirley McKie** (**falso positivo**) |

> ⚠️ **Il falso positivo forense.** Nell'uso forense il sistema restituisce candidati (Lezione 2, §10.2) e la decisione finale è di un esperto. Questi casi mostrano che anche la verifica umana commette **falsi match**, con conseguenze giudiziarie gravi: da qui l'importanza di procedure di verifica indipendente e di misure oggettive dell'errore.

<!-- SOURCE_VISUAL id="TAB03-V34" source="Lezione_03_Approfondimento_Impronta.pdf" page="51" type="confronto di impronte" description="Latente di Madrid confrontata con Daoud e Mayfield; latente dell'omicidio Marion Ross confrontata con Shirley McKie" reason="Il confronto tra le impronte è visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Sistemi AFIS e mercato**

_(slide pp. 52–58)_

#### **10.1 Il sistema AFIS italiano**

_(slide pp. 52–54)_

| Voce | Valore (slide) |
|---|---|
| Inizio progetto | **1994** |
| Inizio funzionamento | **1995** |
| Hardware | **Hewlett-Packard** |
| Software AFIS | **Cogent Systems** |
| Statistiche | **12,5 milioni** di cartellini, **7,5 milioni** di individui memorizzati |

Le impronte sono acquisite in **rapporto 1:1**, a **500 dpi**, in **scala di grigi**; per l'**identificazione giudiziaria** è necessaria la presenza di un **riferimento metrico**.

> 💡 **Perché cartellini più degli individui.** Una stessa persona può essere fotosegnalata più volte: 12,5 milioni di cartellini corrispondono a 7,5 milioni di individui distinti. Riconoscere che due cartellini appartengono alla stessa persona è esattamente un'**identificazione negativa 1:N** (Lezione 1, §7).

<!-- SOURCE_VISUAL id="TAB03-V35" source="Lezione_03_Approfondimento_Impronta.pdf" page="53" type="tabella con immagini" description="Il sistema AFIS italiano: progetto 1994, funzionamento 1995, HP, Cogent, 12,5 milioni di cartellini e 7,5 milioni di individui" reason="Scheda del sistema nazionale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2 Tecnologie sul mercato**

_(slide pp. 55–57)_

| Prodotto | Caratteristiche dichiarate (slide) |
|---|---|
| **M2SYS** (AFIS / **ABIS**, *Automated Biometric Identification System*) | archiviazione, modifica, elaborazione e ricerca di record e template; identificazione **multimodale** (incrocio impronta con volto o iride); certificazioni FBI, NIST, ISO, ANSI; architettura server per matching **1:N e 1:1**; ricerca su oltre **200 milioni di iridi** o **100 milioni di impronte al secondo** |
| **DERMALOG** (AFIS / ABIS) | «il matching più veloce al mondo»: **129 milioni di impronte in un secondo** su un blade Intel standard; DB con **tenprint** (TP), **palmprint** (PP) e **latent print** (LP); «FAR e FRR più bassi sul mercato»; DB standard come Oracle; tutte le piattaforme hardware e sistemi Windows/Unix/Linux |
| **Innovatrics** SDK | alte prestazioni, integrabilità, standard (**WSQ**), piattaforme multiple; **match on card**; mobile, PC, server, embedded; **99,98% di accuratezza**; **1.040.000.000 impronte al secondo** |

> ⚠️ **Dichiarazioni commerciali.** Questi numeri sono **affermazioni dei produttori**, non misure indipendenti. Un'«accuratezza del 99,98%» non dice a quale soglia, su quale database e con quale rapporto tra FMR e FNMR è stata misurata: per la Lezione 2 un solo numero non descrive un sistema. Anche le velocità dipendono dall'hardware e dal tipo di ricerca.

#### **10.3 Trend nelle applicazioni**

_(slide p. 58)_

Le applicazioni si muovono verso quattro direzioni: **dispositivi embedded**, **mobile**, **contactless** (senza contatto) e **server** per l'identificazione su larga scala.

---

### **11. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Sensore ottico** / **capacitivo** | immagine della luce riflessa / misura elettrica della capacità (non rileva bene il silicone) |
| **Arch** / **Loop** / **Whorl** | nessun delta tipico / un delta / almeno due delta |
| **Plain whorl** / **Central pocket loop** | la linea tra i delta tocca una cresta ripiegata / non la tocca |
| **Livello I** / **II** / **III** | disegno globale e punti singolari / minuzie / pori e dettagli fini (≥ 1000 dpi) |
| **Terminazione** / **Biforcazione** | $CN = 1$ / $CN = 3$ |
| **Approccio classico** / **Direct gray scale** | binarizzazione e thinning prima dell'estrazione / tracciamento sulle intensità |
| **FingerCode** / **Matching minuzie** | vettore a lunghezza fissa, distanza euclidea / insieme variabile da allineare |
| **EER** / **FNMR @ FMR fisso** | punto in cui gli errori coincidono / FNMR misurato a una soglia specifica |
| **AFIS** / **ABIS** | identificazione automatica di impronte / identificazione biometrica anche multimodale |

---

### **12. Sintesi della lezione**

_(slide p. 59)_

> ✅ **In sintesi.**
> - **Biologia**: creste e valli stabili dall'ottavo mese di gestazione; il 4% della popolazione non è acquisibile.
> - **Sensori**: capacitivi sotto vetro (Synaptics), ottici in-display, a ultrasuoni (Qualcomm 3D Sonic); terminali di fascia alta (IDEMIA, Suprema) con prestazioni FVC e MINEX.
> - **Classificazione**: arch, loop, whorl e sottoclassi tramite core, delta e orientamento delle creste.
> - **Tre livelli**: globale, minuzie, pori (almeno 1000 dpi).
> - **Elaborazione**: segmentazione per varianza su blocchi 16×16, filtri contestuali, approccio classico e direct gray scale; **crossing number** per le minuzie.
> - **Matching**: Gabor e FingerCode (livello I), minuzie con soglie su distanza e angolo (livello II), matching ibrido.
> - Anche gli esperti sbagliano (casi Mayfield e McKie); **AFIS italiano**, ABIS commerciali e trend verso embedded, mobile e contactless.
