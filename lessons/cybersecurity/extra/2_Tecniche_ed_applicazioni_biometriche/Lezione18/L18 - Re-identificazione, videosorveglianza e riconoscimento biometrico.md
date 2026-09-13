# **Lezione 18: Re-identificazione, videosorveglianza e riconoscimento biometrico**

---

### **0. Panoramica della lezione**

_(slide pp. 1–3)_

**Outline** (p. 3):

1. **re-identificazione**;
2. **videosorveglianza (VS) classica**;
3. **moduli avanzati** per la videosorveglianza, hardware e software;
4. **standard H.264, H.265, H.266**;
5. **sorveglianza comportamentale**;
6. **riconoscimento biometrico in videosorveglianza**.

La lezione riprende molti concetti delle lezioni sull'acquisizione (Lezioni 11 e 12) e sulla sicurezza fisica (Lezione 14), applicandoli ai sistemi di sorveglianza reali.

<!-- SOURCE_VISUAL id="TAB18-V01" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="2" type="copertina" description="Re-identificazione e videosorveglianza" reason="Pagina grafica di titolo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte A — Re-identificazione**

### **1. Che cos'è la re-identificazione**

_(slide pp. 4–10)_

> 📌 **Re-identificazione** (Re-ID, p. 5). Il processo che permette di **riconoscere la stessa persona** in **momenti, camere o contesti diversi**, **senza necessariamente** avere un'identità dichiarata. Non significa per forza scoprire **nome e cognome**, ma individuare la **presenza** della stessa persona in tempi diversi e da inquadrature diverse.

> 💡 **Re-ID e identificazione.** L'identificazione classica confronta un campione con una gallery di **identità note** (Lezione 1). La Re-ID confronta un campione con i campioni **visti prima** da altre camere: la «gallery» è fatta di tracce anonime. Si può quindi seguire il percorso di una persona in un centro commerciale senza sapere chi sia; se però una di quelle tracce viene collegata a un nome, tutto il percorso diventa **identificato** (Lezione 13).

**Problemi tipici** (p. 6):

- variazioni di **posa**;
- **illuminazione**;
- **occlusioni**;
- **qualità** bassa;
- **risoluzione** bassa;
- **look-alike** (persone somiglianti);
- **invecchiamento** (meno importante, perché la Re-ID lavora di solito su intervalli brevi).

**Due livelli** (pp. 7–9):

| | **Livello 1: corpo** (Person Re-ID) | **Livello 2: volto** (Face Recognition) |
|---|---|---|
| Che cosa usa | **statico**: aspetto della figura completa; **dinamico**: come cammina (Lezione 16) | volto **frontale**, **laterale**, **dall'alto** |
| Quando si usa | volto non visibile, risoluzione bassa, persona distante, angolo non frontale | volto visibile, qualità sufficiente, frontalità accettabile |
| Caratteristiche | **silhouette**, **abbigliamento**, **proporzioni**, pattern globali | tratti biometrici **stabili**, **maggiore discriminatività** |
| Vantaggi | funziona anche in **scena larga**, robusto a posa e distanza | **precisione più alta**, identificazione più forte |

> ⚠️ **Il limite del livello 1.** L'abbigliamento è un tratto **soft** che cambia da un giorno all'altro: la Re-ID basata sul corpo funziona bene **nella stessa giornata** o nello stesso evento, molto meno su intervalli lunghi. Per questo i due livelli si combinano: il corpo aggancia la traccia a distanza, il volto la conferma quando la qualità lo permette.

**Evoluzioni: cross-modal person Re-ID** (p. 10). La re-identificazione integra modalità diverse per riconoscere la stessa persona in condizioni eterogenee:

- **RGB**;
- **infrarosso**;
- **profondità**;
- **testo** (una descrizione come «giacca rossa, zaino nero»);
- **disegno** (un identikit);
- **gait** (camminata).

<!-- SOURCE_VISUAL id="TAB18-V02" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="7" type="immagini" description="Re-ID a due livelli: corpo statico e dinamico, volto frontale, laterale e dall'alto" reason="Esempi visivi dei due livelli" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V03" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="10" type="schema" description="Cross-modal person Re-ID con RGB, infrarosso, profondità, testo, disegno e gait" reason="Modalità mostrate nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Metriche della Re-ID**

_(slide pp. 11–14)_

#### **2.1 CMC e Rank-k**

Nella re-identificazione si usa tipicamente la **CMC** (*Cumulative Matching Characteristic*, Lezione 2), che misura la **probabilità che l'identità corretta compaia tra le prime $k$ posizioni** del ranking (p. 11). **Rank-1** e **Rank-10** indicano se il soggetto corretto è al **primo posto** o **entro i primi dieci**.

Con un insieme di query $Q$, se $r_q$ è la posizione del primo match corretto per la query $q$:

$$CMC(k) = \frac{1}{|Q|} \sum_{q \in Q} \mathbb{1}\left[ r_q \leq k \right]$$

**La curva completa** (p. 12): sull'asse orizzontale il **rank**, su quello verticale la **percentuale di re-identificazione**. Ogni curva rappresenta un sistema; **più alta è, meglio è**.

> ⚠️ **Servono le etichette** (p. 12). Per calcolare la CMC i ritagli (*crop*) devono essere **etichettati correttamente** con l'identità vera (*ground truth*). Senza una label per ogni immagine non si può stabilire se il match al Rank-$k$ sia corretto: la metrica **non è calcolabile in modo attendibile** e non si può ricorrere all'**auto-labelling**, perché si valuterebbe il sistema con etichette prodotte da un sistema.

#### **2.2 mAP**

La **mAP** (*mean Average Precision*) è la metrica standard nei benchmark moderni come **Market-1501** e **DukeMTMC** (p. 13). Per ogni query si calcola l'**Average Precision**, poi si fa la **media** su tutte le query.

Con $G_q$ match corretti nella gallery per la query $q$, $P_q(k)$ la precisione tra i primi $k$ risultati e $\text{rel}_q(k) = 1$ se il risultato in posizione $k$ è corretto:

$$AP_q = \frac{1}{G_q} \sum_{k=1}^{n} P_q(k) \cdot \text{rel}_q(k) \qquad mAP = \frac{1}{|Q|} \sum_{q \in Q} AP_q$$

> ⚠️ **Formule ricostruite.** Il livello testo della p. 13 conserva solo «per ogni query si calcola:» e «poi si fa la media su tutte le query:»; le formule sono le definizioni standard e vanno confrontate con la slide.

**CMC contro mAP** (p. 14):

| Metrica | Che cosa valuta |
|---|---|
| **CMC** | la probabilità che l'identità corretta compaia **entro il Rank-$k$**: **se il sistema trova** la persona |
| **mAP** | la **qualità complessiva dell'ordinamento** di tutti i match corretti: **quanto bene ordina tutte le occorrenze** corrette |

> 💡 **Esempio numerico (nota didattica).** Una persona compare **3 volte** nella gallery e, per una query, i match corretti escono in posizione **1, 3 e 6**. Il Rank-1 è perfetto, quindi la CMC dice che la persona è stata «trovata». La precisione nei punti corretti vale $1/1$, $2/3$ e $3/6$:
> $$AP = \frac{1}{3} \left( 1 + \frac{2}{3} + \frac{1}{2} \right) \approx 0{,}72$$
> La mAP rivela che le altre due apparizioni sono mescolate a errori. In videosorveglianza, dove si vuole ricostruire **tutto** il percorso, conta proprio questo. L'esempio della slide riporta una mAP di 0,98.

<!-- SOURCE_VISUAL id="TAB18-V04" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="12" type="curve CMC" description="CMC completa di un esperimento con rank sull'asse orizzontale e percentuale di re-identificazione su quello verticale" reason="Curve dei sistemi confrontati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V05" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="13" type="formule" description="Definizione di Average Precision per query e media sulle query (mAP)" reason="Formule non presenti nel livello testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V06" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="14" type="esempio di ranking" description="Confronto CMC contro mAP con esempio di ranking e mAP = 0,98" reason="Ranking mostrato come immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. La pipeline di Re-ID e l'esercitazione**

_(slide pp. 15–22)_

**Due stadi** (pp. 15–16):

1. **person detection**: si individuano le persone con **bounding box**;
2. **estrazione delle feature e matching** sui ritagli.

**Esercitazione** (pp. 17–22):

| Passo | Strumento |
|---|---|
| rilevamento delle persone | **YOLOv8n**: YOLO versione 8, variante **n = nano**, la più piccola (p. 18) |
| estrazione dei bounding box delle persone | YOLO, versione semplificata (p. 19) |
| embedding **di base** | feature ricavate da YOLO stesso (p. 20) |
| embedding **migliore** | **OSNet** (p. 21) |
| confronto | Re-ID tra i ritagli (p. 22) |

> 📌 **OSNet** (*Omni-Scale Network*, p. 21). CNN progettata specificamente per la **Person Re-ID**, capace di catturare informazioni a **scale multiple**, globali e locali, nella stessa architettura. Produce un **embedding** $\mathbf{e} \in \mathbb{R}^d$ che rappresenta l'identità in uno **spazio metrico**, dove la **similarità** (per esempio il coseno) misura la probabilità di corrispondenza.

> 💡 **Perché un modello dedicato batte YOLO.** YOLO è addestrato a riconoscere la **classe** «persona»: le sue feature servono a dire che c'è una persona, non **quale**. OSNet è addestrato come le reti di identificazione della Lezione 8, per rendere vicini i ritagli della stessa persona e lontani quelli di persone diverse. Le scale multiple servono perché un dettaglio piccolo (un logo, una borsa) può essere l'unico elemento distintivo.

<!-- SOURCE_VISUAL id="TAB18-V07" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="15" type="immagine annotata" description="Pipeline: person detection con bounding box" reason="Bounding box visibili solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V08" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="16" type="schema" description="Pipeline: estrazione delle feature e matching" reason="Schema del flusso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V09" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="19" type="codice" description="YOLO: estrazione dei bounding box delle persone (versione semplificata)" reason="Codice mostrato come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V10" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="21" type="codice e schema" description="Estrazione dell'embedding con il modello Re-ID OSNet" reason="Codice e architettura mostrati come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V11" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="22" type="immagini" description="Risultato della Re-ID tra ritagli di persone" reason="Risultati visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. La control room**

_(slide pp. 23–25)_

Esempi di **control room** (p. 23): Comune di **Pescara**, Comune di **Palermo**, gestione di privati, Comune di **Roma** (viabilità).

> 📌 **Control room = identità + comportamento** (p. 24). Una sala di controllo risponde a due domande: **chi sono?** e **che cosa fanno?**

**Funzioni** (p. 25):

| Elemento | Contenuto (slide) |
|---|---|
| **Scenario** | ambiente controllato (ingressi, tornelli, sportelli, aree sensibili) con sorveglianza e/o controllo accessi |
| **Obiettivo operativo** | **ridurre il rischio** (intrusione, impersonation, social engineering) anche con segnali **deboli o indiretti**; verifica biometrica; controllo dei **comportamenti** (taccheggio, aggressioni); individuare l'**inizio di situazioni rischiose** (folla, assembramenti, incendi) |
| **Ingressi** | **video RGB** (fps), opzionale **IR/NIR**, **audio** opzionale; **flusso di eventi** (badge e varco, timestamp, posizione della camera); **database**: template del volto per la verifica **1:1**, **watchlist** per l'identificazione **1:N**, **policy** con le soglie $\tau$ |
| **Uscite** | **ID candidato** con **score** di similarità; **quality score** (volto, posa, sfocatura); **alert** basso, medio o alto; **explanation tags** come «occlusione», «liveness low», «multi-face», «mismatch badge» |

> 💡 **Perché servono gli explanation tag.** Un operatore che riceve centinaia di allarmi al giorno deve decidere in pochi secondi. «Alert alto» da solo non dice se fidarsi; «mismatch badge» indica che il volto non corrisponde al titolare del badge appena usato, «liveness low» un possibile attacco con foto, «occlusione» un punteggio inaffidabile. È la fusione con la qualità della Lezione 15, §14, resa leggibile all'essere umano.

<!-- SOURCE_VISUAL id="TAB18-V12" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="23" type="fotografie" description="Control room dei Comuni di Pescara, Palermo e Roma e di gestori privati" reason="Pagina con sole fotografie" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte B — La videosorveglianza classica**

### **5. Applicazioni e mercato**

_(slide pp. 26–29)_

La videosorveglianza classica comprende **hardware**, **software** e **infrastrutture**, ed evolve verso la videosorveglianza **biometrica** (p. 26). La p. 27 confronta le caratteristiche dei sistemi biometrici in **scenari eterogenei**.

**Applicazioni tipiche** (p. 28):

- **educazione**;
- **sorveglianza biometrica** nel settore privato e governativo;
- **banche**: controllo accessi di dipendenti e clienti;
- **controllo del traffico**, della **velocità** e delle **targhe**.

**Il mercato** (p. 29) continua a crescere grazie a:

- nuove esigenze di **sicurezza**;
- applicazioni nelle **smart city**;
- nuove tecnologie: telecamere **intelligenti**, reti performanti, **IoT**, **VSaaS** (*Video Surveillance as a Service*).

Le stime indicano un mercato globale di circa **60 miliardi di dollari** nei prossimi quattro anni; per confronto, il PIL dell'Ucraina nel 2019 era di **126 miliardi**. Oltre ai grandi produttori ci sono produttori di medie dimensioni e per la **smart home** (D-Link, Eufy, EZVIZ, Xiaomi).

<!-- SOURCE_VISUAL id="TAB18-V13" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="27" type="tabella grafica" description="Caratteristiche dei sistemi biometrici in scenari eterogenei" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. La telecamera e le ottiche**

_(slide pp. 30–39)_

#### **6.1 Struttura della telecamera e zoom**

**Componenti** (p. 30):

- **sensore** CMOS o CCD;
- **chip di comunicazione**;
- **accoppiamento** con l'ottica;
- **case impermeabile**, con eventuale motorizzazione **Pan, Tilt, Zoom**.

**Zoom semplice e motorizzato** (p. 31): telecamere con **solo zoom** e telecamere **PTZ** (*Pan, Tilt, Zoom*), che ruotano orizzontalmente, si inclinano e zoomano.

<!-- SOURCE_VISUAL id="TAB18-V14" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="31" type="fotografie prodotto" description="Telecamera con solo zoom e telecamera PTZ" reason="Confronto visivo dei due tipi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Lenti per la videosorveglianza**

**Caratteristiche** (p. 32):

- campo visivo **grandangolare**, da **74°** fino ai **fisheye** da **190°**;
- ottiche di alta qualità adatte a sensori **megapixel**;
- lenti **ottimizzate per HDR**;
- design meccanico molto **compatto**;
- attacco **CS**: alternativa **economica** al **C-mount**.

> 💡 **C e CS (nota didattica).** I due attacchi hanno la stessa filettatura ma una diversa distanza tra la battuta dell'obiettivo e il sensore: circa **17,5 mm** per il C-mount e **12,5 mm** per il CS. Un obiettivo C si monta su una camera CS con un **anello distanziale da 5 mm**; un obiettivo CS non si mette a fuoco su una camera C (Lezione 11, §7.6).

**Ottiche super-grandangolari**, con $f \leq 20$ mm (p. 33). In videosorveglianza permettono di vedere **tre corridoi** con una sola camera; con focali molto basse si raggiungono FOV di **180°**. Conviene preferire lenti **rettilineari** per ridurre la **distorsione a barilotto**.

**Dal FOV alla focale** (p. 34). Per campi visivi molto ampi servono focali minime, anche **$f = 1{,}2$ mm**. In questo caso bisogna **aumentare anche la risoluzione** della telecamera, altrimenti i dettagli **sgranano**: gli stessi pixel si distribuiscono su una scena più larga.

> 💡 **Il calcolo (richiamo della Lezione 11).** Con il modello a foro stenopeico il campo visivo orizzontale vale $FOV = 2 \arctan\left( \dfrac{w}{2f} \right)$, dove $w$ è la larghezza del sensore. A parità di sensore, dimezzare la focale allarga il campo, e ogni pixel copre una porzione di scena più grande: i **pixel per metro** sul soggetto calano.

<!-- SOURCE_VISUAL id="TAB18-V15" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="33" type="immagine" description="Ottica super-grandangolare che inquadra tre corridoi con FOV fino a 180 gradi" reason="Effetto visibile solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V16" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="34" type="immagine" description="Scelta del FOV e focale necessaria di 1,2 mm con dettagli sgranati" reason="Effetto visibile solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Zoom a lunga distanza**

**Focali da record** (p. 35, ripasso): si arriva a **660 mm**. Con lo stesso dispositivo si possono osservare dettagli di una persona e fare sorveglianza **a chilometri** di distanza.

**FUJIFILM SX800** (pp. 36–37):

| Caratteristica | Valore |
|---|---|
| focale | **20–800 mm** |
| stabilizzazione | rilevamento delle **vibrazioni** con giroscopi a basso rumore |
| meccanica | attuazione lineare su **guide ceramiche** |
| autofocus | ad alta velocità, **0,3 s**, anche su soggetti in movimento |
| sensibilità | da **ISO 400 a 800.000** (notte) |
| elaborazione | **compensazione automatica** delle deformazioni dell'immagine dovute a **calore** e **nebbia** |

Le pp. 38–39 mostrano applicazioni delle camere a lungo raggio. Nell'esempio della p. 39 si nota un **contrasto luce/ombra eccessivo** rispetto alla gamma dinamica della camera.

<!-- SOURCE_VISUAL id="TAB18-V17" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="37" type="fotografie prodotto" description="Telecamera di sorveglianza FUJIFILM SX800 con zoom 20-800 mm" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V18" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="39" type="sequenza di immagini" description="Zoom in sorveglianza con contrasto luce/ombra eccessivo rispetto alla gamma della camera" reason="Effetto visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Gamma dinamica, sensori e SoC**

_(slide pp. 40–49)_

#### **7.1 I decibel**

> 📌 **Decibel** (p. 40). Unità nata in acustica per l'intensità del suono (dal nome di Alexander Graham Bell) che confronta livelli di potenza, tensione, corrente o intensità su una **scala logaritmica** («deci» = un decimo di bel).

**Esempio della slide**: se la tensione minima rilevabile da un pixel (a 8 bit «00000000») e quella massima («11111111», cioè 255) corrispondono a luminosità in rapporto **10.000** ($10^4$):

$$DR_{\text{dB}} = 20 \log_{10}(10\,000) = 20 \cdot 4 = 80\ \text{dB}$$

**Perché 20 e non 10?** La potenza elettrica è $P = V \cdot I = V^2 / R$. Il logaritmo del **quadrato** raddoppia:

$$10 \log_{10} \frac{P}{P_0} = 10 \log_{10} \frac{V^2}{V_0^2} = 20 \log_{10} \frac{V}{V_0}$$

Si usa quindi $10 \log_{10}$ per le **potenze** e $20 \log_{10}$ per i **segnali** come la tensione.

> 💡 **Conti utili (nota didattica).** Invertendo, un rapporto vale $10^{DR/20}$:
>
> | Gamma dinamica | Rapporto massimo/minimo |
> |---:|---:|
> | 48 dB | circa 255: ciò che codifica **linearmente** un canale a 8 bit, $20 \log_{10} 255 \approx 48$ |
> | 80 dB | $10^4$ |
> | 120 dB | $10^6$ |
> | 144 dB | $10^{7{,}2} \approx 1{,}6 \cdot 10^7$ |
>
> Un'uscita a 8 bit **lineare** non può rappresentare 80 dB: la scena va compressa con una curva non lineare (tone mapping), ed è il lavoro del WDR (§7.3).

**Lumen, lux, candele** (p. 41). Per studiare e leggere si consigliano **300–500 lux**, mentre il **sole** su un tavolo dà **da 32.000 a 100.000 lux**: diversi **ordini di grandezza**, ecco perché si usano i dB.

**Gamme dinamiche** (p. 42): bisogna capire **quale gamma dinamica serve** per la propria applicazione, **70 dB** o **140 dB**.

<!-- SOURCE_VISUAL id="TAB18-V19" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="42" type="grafico" description="Gamme dinamiche di scene e sensori da 70 a 140 dB" reason="Valori per scena visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 Sensori e System on Chip**

- **CCD contro CMOS** in sorveglianza (p. 43): i CCD hanno un **ottimo WDR** e **non soffrono** di effetti jello o vibrazioni (Lezione 12, §7).
- **Sony Starlight IMX226** (p. 44): camera IP **8 MP / 4K UHD** con SoC **Hi3519** e **video notturno a colori**. Rispetto al sensore, **stretching dell'istogramma** e **correzione gamma** via software **non producono miglioramenti significativi**: la sensibilità in bassa luce si ottiene nel sensore, non dopo.
- **SoC dedicati** (p. 45). Attraverso **System on Chip** come l'Hi3519 (4K) avviene l'interfacciamento con I/O, storage, **codifica** e **trasmissione**. **UHD** (*Ultra-High-Definition*) indica risoluzioni da **2160p** in su.

**Una telecamera PTZ completa** (p. 46), UHD 12 MP 4K Starlight con Sony IMX226:

| Componente | Funzione |
|---|---|
| **CMOS** | acquisizione |
| **zoom ottico** 3× (3,6–11 mm) con **motore di zoom e autofocus** | inquadratura e fuoco |
| **driver dei motori PTZ** | pan e tilt, comandati via **RS485** |
| **board principale** con codec ed Ethernet | codifica **H.265/H.264**, conformità **ONVIF**, streaming **RTSP** (*Real Time Streaming Protocol*) |
| **SD** | registrazione locale |
| **dissipatore** | smaltire il calore dell'elaborazione |

<!-- SOURCE_VISUAL id="TAB18-V20" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="43" type="confronto di immagini" description="CCD contro CMOS in sorveglianza: WDR e assenza di jello nei CCD" reason="Confronto visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V21" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="46" type="spaccato annotato" description="Componenti di una telecamera PTZ UHD 12MP con Sony IMX226: CMOS, zoom, motori, driver PTZ, SD, board, dissipatore" reason="Posizione dei componenti indicata sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 HDR e WDR**

**HDR multiframe** (p. 47): si combinano un'esposizione **lunga** e una **corta** in un'immagine HDR.

| | **HDR** (*High Dynamic Range*) | **WDR** (*Wide Dynamic Range*) |
|---|---|---|
| Obiettivo | migliorare la qualità catturando dettagli **sia nelle zone scure sia in quelle luminose** | gestire scene con **forti contrasti**, come ingressi in **controluce** |
| Come | **più esposizioni** unite in un'immagine a gamma estesa | **bilancia l'esposizione** in una singola immagine perché siano visibili zone chiare e scure |
| Elaborazione | **complessa** | in **hardware o software**, progettata per il **tempo reale** senza ritardi significativi |
| Dove | applicazioni che tollerano **tempi di elaborazione più lunghi** | tecnologia **specifica della videosorveglianza** |

La p. 49 mostra l'elaborazione WDR sull'Hi3519 e ribadisce che **WDR ≠ HDR**.

> 💡 **Il problema del controluce all'ingresso.** Una persona che entra da una porta vetrata ha dietro di sé il sole (decine di migliaia di lux) e davanti l'atrio (poche centinaia): il volto finisce in ombra nera o lo sfondo diventa bianco. Un sistema biometrico in quel punto **non riconosce nessuno** se la camera non gestisce oltre 100 dB. Per questo i prodotti per il riconoscimento facciale dichiarano WDR molto alti (§9.1).

> ⚠️ **HDR multiframe e movimento.** Unire esposizioni prese in istanti diversi crea **artefatti** sui soggetti in movimento (bordi doppi, «fantasmi»): è lo stesso problema del rolling shutter (Lezione 12). Per volti in movimento è preferibile un WDR a singola esposizione o un sensore che acquisisca le due esposizioni quasi simultaneamente.

<!-- SOURCE_VISUAL id="TAB18-V22" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="47" type="confronto di immagini" description="HDR multiframe: esposizione lunga, esposizione corta e risultato HDR" reason="Confronto visivo delle esposizioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V23" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="49" type="schema" description="Elaborazione WDR sulla camera con SoC Hi3519" reason="Schema della pipeline di elaborazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4 Telecamere particolari**

_(slide pp. 50–51)_

| Tipo | Caratteristiche (slide) |
|---|---|
| **Dome da esterno** (p. 50) | copertura **multidirezionale**, fino a **32 MP**, resistente alle condizioni atmosferiche, **antivandalo** |
| **Pelco ExSite Enhanced Bispectral PTZ 2** (p. 51) | **anti-esplosione** con certificazioni globali; combina sensori **visivi e termici**; fino a **4 flussi** simultanei; camera visiva **2 MP** con **zoom 30×**; camera termica **VGA** o **QVGA** con varie lenti fisse |

---

### **8. Infrastrutture e protocolli**

_(slide pp. 52–57)_

#### **8.1 Dall'analogico all'IP**

| | **Passato** (ancora installato, p. 52) | **Attuale** (p. 53) |
|---|---|---|
| Camere | **analogiche** NTSC o PAL | **IP intelligenti** (*edge*) con video analytics, multi-stream, risoluzione megapixel |
| Cavi | **coassiale** con i suoi connettori | **Ethernet CAT 5/6**: **UTP** (base) o **STP** (schermato, migliore) |
| Connettori | coassiali | **RJ45**, oppure **M12** maschio a 8 poli per ambienti industriali |
| Registrazione | nastro **VHS** o **DVR** (*Digital Video Recorder*) | server e storage di rete |

#### **8.2 Vantaggi delle camere IP**

**Primo gruppo** (p. 54):

- **calcolo distribuito**, con l'intelligenza **all'edge**, cioè sulla telecamera: codifica, compressione, **analisi video**, notifiche e-mail, **rilevamento del movimento**, aggiornamenti e report sul campo;
- capacità **multi-stream**, per esempio **MJPEG** e **H.264** a utenti o applicazioni diversi;
- uso di server **COTS** (*Commercial Off-The-Shelf*);
- gestione semplice di un'infrastruttura **simile alla rete aziendale**.

**Secondo gruppo** (p. 55):

- **meno costi di cablaggio**, perché l'UTP è molto più comune e diffuso del coassiale;
- alimentazione semplificata con **PoE** (*Power over Ethernet*): per esempio un **iniettore PoE** per singola camera, oppure uno **switch PoE** gestito a 24 porte 10G/Multi-Gigabit impilabile (circa **3.700 euro**).

**Terzo gruppo** (p. 56):

- comandi **PTZ** e **allarmi** sullo **stesso cavo** (con i controller PTZ tradizionali servivano linee RS232 o RS485);
- **risoluzione più elevata**: HD e megapixel (4K, 5 MP, 12 MP). Per esempio $1920 \times 2592 = 4{,}98$ MP;
- **telecamere meno costose**.

**Svantaggi** (p. 57). Il principale è la **mancanza di standard**: implementazioni diverse da fornitore a fornitore per **streaming**, **configurazione** e **notifica dello stato**, e servono **competenze di rete**. Nel **2008** nascono due gruppi industriali:

| Consorzio | Fondatori |
|---|---|
| **ONVIF** (*Open Network Video Interface Forum*) | Axis Communications, Bosch, Sony, Panasonic |
| **PSIA** (*Physical Security Interoperability Alliance*) | 20 aziende, tra cui Honeywell, GE Security, Cisco |

> ⚠️ **Il rovescio della camera in rete.** Una camera IP è a tutti gli effetti un **computer connesso**: password di default, firmware non aggiornati e interfacce web esposte ne fanno un bersaglio per botnet e accessi abusivi ai flussi video. In un sistema biometrico il flusso contiene **dati personali**: va segmentato in una rete dedicata e cifrato (Lezione 13).

---

### **9. Gli standard H.264, H.265 e H.266**

_(slide pp. 58–65)_

#### **9.1 Perché contano**

La rilevanza di questi standard in videosorveglianza è tale che, esagerando, si potrebbe dire (p. 59):

$$\text{HTML5} : \text{Web} = \text{H.265} : \text{Videosorveglianza}$$

| Standard | Nomi | Note (pp. 60–61) |
|---|---|---|
| **H.264** | **MPEG-4 Part 10**, **AVC** (*Advanced Video Coding*) | uno dei codec più diffusi per compressione e trasmissione, per efficienza e qualità |
| **H.265** | **HEVC** (*High Efficiency Video Coding*) | migliora la compressione a un **costo computazionale maggiore** |
| **H.266** | **VVC** (*Versatile Video Coding*) | migliora ulteriormente, con costo ancora maggiore |

Sono adatti a **videosorveglianza, streaming e registrazione**. Le versioni hanno migliorato la **compressione** senza particolari perdite di qualità, aumentato i **bit di quantizzazione** e le dimensioni del formato, fino all'**8K** e oltre. La p. 61 mostra il confronto su un flusso **1080p a 30 fps**.

> 💡 **Quanto comprimono (nota didattica, valori indicativi).** Un flusso 1080p a 30 fps non compresso, a 24 bit per pixel, richiede
> $$1920 \cdot 1080 \cdot 24 \cdot 30 \approx 1{,}49\ \text{Gb/s}$$
> In videosorveglianza un flusso H.264 di questo tipo occupa tipicamente pochi Mb/s: un rapporto di compressione di diverse centinaia. A parità di qualità percepita, H.265 dimezza circa il bitrate di H.264, e H.266 riduce ancora in misura simile rispetto a H.265. Su centinaia di camere registrate per settimane, questo significa dimezzare banda e storage.

#### **9.2 Le funzioni utili per la videosorveglianza**

_(slide pp. 62–65)_

- **supporto HDR**;
- **codifica per regioni di interesse** (*ROI encoding*): compressioni diverse secondo la **rilevanza** delle aree dell'immagine;
- **maggiore profondità di colore**: H.264 fino a **8 bit**, H.265 fino a **10/12 bit**, H.266 **oltre 12 bit**. Permette filtraggi migliori sul singolo frame e gamme di colore molto più profonde (Lezione 12, §5);
- **compressione ottimizzata e granulare** nella singola immagine, ancora più fine in H.265 e H.266: si può ottimizzare la qualità di aree specifiche, utile per realtà aumentata, realtà virtuale e sorveglianza ad altissima risoluzione;
- **miglioramento della blocchettizzazione** (p. 63): meno artefatti a blocchi;
- **Group of Pictures** (GoP).

> 📌 **Group of Pictures** (p. 64). Il flusso H.264 è composto da **I-frame** (*intra-coded*), che registrano **tutta** l'informazione dell'immagine, e da frame che rappresentano solo le **differenze**, come i **P-frame** (*predicted*). La p. 65 confronta le prestazioni (dati di telecamere Panasonic i-PRO EXTREME).

> 💡 **GoP e biometria (nota didattica).** Esistono anche i **B-frame** (*bidirectional*), predetti da frame precedenti e successivi. Con un GoP lungo il bitrate scende perché la maggior parte dei frame sono differenze; ma un **P-frame** di una scena in movimento è ricostruito per predizione e ha più **artefatti** di un I-frame. Per estrarre un volto da un video registrato conviene, quando possibile, usare gli **I-frame** o i frame con quantizzazione più fine. Una compressione aggressiva è anche un nemico dell'rPPG (Lezione 17), perché cancella le minime variazioni di colore della pelle.

<!-- SOURCE_VISUAL id="TAB18-V24" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="63" type="confronto di immagini" description="Miglioramento della blocchettizzazione tra H.264 e H.265" reason="Artefatti a blocchi visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V25" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="65" type="grafico" description="Comparazione delle prestazioni di compressione (dati Panasonic i-PRO EXTREME)" reason="Valori visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Una telecamera avanzata: Panasonic i-PRO EXTREME**

_(slide pp. 66–78)_

Le slide analizzano le funzioni avanzate delle telecamere **Panasonic i-PRO EXTREME**, di prezzo inferiore a circa **800 euro più IVA** (pp. 66–68):

| Funzione | Descrizione (slide) |
|---|---|
| **Gamma dinamica estrema** (p. 69) | **144 dB**, per vedere i volti in sempre più condizioni di luce |
| **Intelligent Auto Mode** (p. 70) | oltre al WDR, **controllo dei riflessi** e **riduzione del blur da movimento** con regolazione automatica di **tempo di esposizione** e **guadagno** dei pixel |
| **Color Night Vision** (p. 71) | colori anche sotto **1 lux** |
| **Auto-VIQS** (p. 72) | *Variable Image Quality on Specified Area*: individua automaticamente le **aree in movimento** e **riduce la qualità** nelle aree con meno movimento per risparmiare banda |
| **FDF** (pp. 73–74) | *Frequency Divided Filter* per la **riduzione del rumore** |
| **Intelligent Face Compression** (p. 75) | la camera decide quali parti del filmato sono importanti (oggetti in movimento, **volti**) e mantiene i dettagli dei volti con **qualità superiore** al resto |
| **Esposizione per i volti** (p. 76) | rileva **fino a 8 volti**, regola l'esposizione di conseguenza e la **riaggiusta seguendo** il volto mentre la persona si muove |
| **RainWash / ClearSight coating** (p. 77) | rivestimento della cupola contro l'aderenza di **gocce e sporco** |

> 💡 **Tre funzioni pensate per la biometria.** Intelligent Face Compression è un **ROI encoding** automatico sul volto (§9.2); l'esposizione per i volti ottimizza la camera sulla regione che conta per il matcher, non sulla scena media; 144 dB risolvono il controluce dell'ingresso. La camera non riconosce nessuno, ma produce **campioni migliori** per il riconoscimento (Lezione 14, §14.1).

**Edge processing** (p. 78). La biometria non è più **«manda tutto al server»**, ma **«filtra e struttura già alla fonte»**:

| | **FacePRO** (vecchio) | **i-PRO Active Guard** |
|---|---|---|
| Architettura | server **monolitico** | **modulare** |
| Focus | identificazione **1:N** | **eventi + contesto** |
| Elaborazione | **centralizzata** | **edge + server** |
| Zero Trust | poco | **molto di più** |

> 💡 **Perché l'edge è più Zero Trust.** Se la camera invia al server solo metadati ed eventi (o template protetti) invece del video grezzo, meno dati personali viaggiano in rete e un attaccante che intercetta il canale ottiene molto meno. È la minimizzazione dei dati delle linee guida sulla privacy (Lezione 13, §4).

<!-- SOURCE_VISUAL id="TAB18-V26" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="68" type="fotografie prodotto" description="Piattaforma Panasonic i-PRO Extreme con le diverse feature" reason="Pagina con immagini di prodotto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V27" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="73" type="confronto di immagini" description="Riduzione del rumore con Frequency Divided Filter" reason="Confronto visivo con e senza filtro" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte C — Sorveglianza comportamentale**

### **11. Nuove applicazioni della videosorveglianza**

_(slide pp. 79–91)_

Si riconoscono **comportamenti**, non individui (p. 79): integrazione tramite **API**, funzioni avanzate, **antitaccheggio**, **incidenti**.

**Applicazioni abilitate** (p. 80):

- **monitoraggio delle folle**;
- **vandalismo**, attività sospette, **anomaly detection**;
- **retail avanzato**;
- **antitaccheggio**;
- videosorveglianza **biometrica**;
- **sicurezza dei lavoratori**: indossa i **dispositivi di protezione individuale**? passa nei corridoi e nelle aree previste?
- **antiterrorismo**.

**Crowd monitoring** (p. 81) e **rilevamento di attività sospette** (p. 82): analisi delle **differenze fra immagini**, delle **velocità**, dei **percorsi insoliti**, della **permanenza**. I produttori implementano queste funzioni in modi diversi. La p. 83 rimanda a un approfondimento sull'anomaly detection in computer vision.

**Piattaforma AZENA** (pp. 84–88):

- **FLOW Retail** nell'Azena Application Store (p. 84), piattaforma di app per le telecamere (p. 85), con focus sul **retail**, cioè vendita al dettaglio e diretta al consumatore (p. 86);
- **SAIMOS Intrusion Detection** (p. 87): app basata sull'IA per proteggere **perimetri e beni**; genera allarmi quando una persona entra in una **zona protetta**, all'interno o all'esterno. Analizza anche il **verso del movimento** (entra o esce) e offre un'elevata capacità di **auditing** con immagini tratte dalla scena (Lezione 14);
- **FLOW Security** (p. 88): trasforma la camera in un **sensore di sicurezza intelligente** con **reti neurali profonde** per il rilevamento e il **tracciamento** delle persone, con un nucleo analitico **configurabile visivamente**. Rileva incidenti di intrusione **complessi con più persone**, analizzati con **regole logiche** per identificare schemi e anomalie: **molto più robusto di un semplice sensore PIR** (*Passive InfraRed*).

> 💡 **Perché la camera batte il PIR.** Un sensore PIR rileva una variazione di calore nel suo campo: scatta con un gatto, una tenda mossa dal riscaldamento o il sole su un muro, e non sa distinguere una persona da un'altra né la direzione. Una rete che rileva e traccia **persone** può applicare regole come «due persone entrano insieme e una sola esce» o «qualcuno scavalca nel verso sbagliato».

**Antitaccheggio** (pp. 89–91):

- nella piattaforma si possono integrare **video analytics di altri fornitori**, con accesso via **API** a cataloghi di modelli per costruire le proprie pipeline, per esempio **Brinq Edge AI** contro il furto di pacchi;
- **Veesion** individua i potenziali furti analizzando i **movimenti** delle persone e segnalando atteggiamenti sospetti, con **notifiche in tempo reale**. Secondo il produttore riduce la **differenza inventariale** dovuta ai furti **dal 30% al 70%**, in oltre **2000 negozi** di **20 nazioni** (MD, Carrefour, Crai, Conad, Sigma…), e in Italia in oltre 100 punti vendita Carrefour Express e Carrefour Market.

> ⚠️ **Comportamento, ma di chi?** Questi sistemi dichiarano di riconoscere **comportamenti e non individui**. Tuttavia un allarme di taccheggio porta a fermare **una persona precisa**, spesso con il suo volto registrato: se l'evento viene collegato a un'identità, l'analisi comportamentale diventa trattamento di dati personali, con i rischi di **falsi positivi** e **discriminazione** della Lezione 16 (caso Amazon France) e i vincoli del Garante sul settore privato (Lezione 13, §17).

<!-- SOURCE_VISUAL id="TAB18-V28" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="81" type="immagine" description="Crowd monitoring con densità della folla" reason="Mappa di densità visibile solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V29" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="83" type="immagini" description="Esempi di anomaly detection in computer vision" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V30" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="85" type="schermate" description="Piattaforma AZENA con app per telecamere" reason="Pagina con sole schermate" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte D — Biometria applicata alla videosorveglianza**

### **12. Requisiti e dimensionamento**

_(slide pp. 92–96)_

La p. 93 confronta ancora le caratteristiche dei sistemi biometrici negli scenari eterogenei; le pp. 94–95 mostrano un esempio sul **volto** e l'**effetto della risoluzione**.

**Dimensionamento di una telecamera per il riconoscimento facciale** (p. 96):

1. **requisito**: servono **60 pixel** di distanza **tra gli occhi**;
2. **distanza interpupillare**: media **65 mm** negli uomini e **62 mm** nelle donne, massima misurata **74 mm**, minima circa **55 mm**. Per il dimensionamento si usa il valore **più piccolo**, 55 mm, il caso peggiore;
3. **densità di pixel** necessaria:
$$\frac{60\ \text{px}}{55\ \text{mm}} \approx 1{,}09\ \text{px/mm}$$
4. **campo visivo orizzontale** da controllare, cioè la larghezza del varco: **1500 mm**;
5. **pixel orizzontali** necessari:
$$1{,}09\ \text{px/mm} \cdot 1500\ \text{mm} \approx 1636\ \text{px}$$
6. **scelta**: una camera da **2 MP** ($1920 \times 1080$) **supera** il requisito; una da **1 MP** ($1280 \times 1024$) **non basta**. Per un'area più ampia serve più risoluzione.

> ⚠️ **1500 o 1524 mm?** Il testo della slide passa da 1500 mm a «1524 mm FOV» (cinque piedi) nella stessa riga di calcolo, residuo dell'esempio originale in unità imperiali. Con 1524 mm si ottengono circa 1662 pixel: la conclusione (2 MP sì, 1 MP no) non cambia.

> 💡 **Il passo successivo: la focale (nota didattica).** Con il modello a foro stenopeico (Lezione 11) la focale che inquadra una larghezza $W$ a distanza $Z$ con un sensore largo $w$ è
> $$f = \frac{w \cdot Z}{W}$$
> Per un sensore largo circa 5,6 mm, con il varco di 1,5 m ripreso a 5 m: $f \approx \dfrac{5{,}6 \cdot 5000}{1500} \approx 18{,}7$ mm. Se il varco va ripreso da 10 m, la focale raddoppia. Un grandangolo da 2,8 mm allo stesso sensore vedrebbe a 5 m una larghezza di 10 m: con 1920 pixel resterebbero 0,19 px/mm, circa 11 pixel tra gli occhi, del tutto insufficienti.

<!-- SOURCE_VISUAL id="TAB18-V31" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="95" type="griglia di immagini" description="Effetto della risoluzione sul volto" reason="Degrado visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **13. Sistemi integrati avanzati**

_(slide pp. 97–111)_

#### **13.1 Le funzioni da ricercare**

I software di VS avanzati hanno **pacchetti preinstallati** per i controlli (p. 98) e permettono di **riconoscere la persona** in altre location, ambienti, condizioni di luce e camere (p. 99). Le slide riportano le funzioni di una soluzione commerciale di **Enterprise Face Recognition Surveillance** (pp. 100–105):

| Gruppo | Funzioni |
|---|---|
| **Principali** (p. 100) | **allarmi in tempo reale** quando criminali o persone di interesse entrano in spazi pubblici o privati; **geofencing** di aree sensibili con autenticazione biometrica; **selezione automatica delle immagini** con punteggio IA su angolo, luce ed espressione per scegliere il miglior enrollment dal video |
| **Integrazione** (p. 101) | **API** per integrare il riconoscimento in software di terzi; integrazione **hardware** con camere, kiosk e terminali; **SDK** per sistemi personalizzati |
| **Velocità e scalabilità** (p. 102) | architettura **enterprise**; matching ad alte prestazioni su **database vastissimi** e location illimitate; posizionamento **discreto** con match accurati **a lunga distanza**; **identificazione di gruppo** di più visitatori simultanei; matching in tempo reale su database di **25 milioni** di immagini; **catalogazione anonima dei visitatori** (indicizza e poi elimina automaticamente i non riconosciuti); **ottimizzazione visiva** automatica; **accessory detection** (cappelli, cappucci, barba, occhiali, sciarpe, occlusioni parziali); **autenticazione** con il volto |
| **Mobile** (p. 103) | **allarmi** di match su telefoni e tablet Android e iOS; **enrollment** in una watchlist cloud o on-premise **da dispositivo mobile**; esempio di **occhiali** con riconoscimento (Xloong) |
| **Privacy e sicurezza** (p. 104) | **anti-profiling**, **cifratura** a riposo e in transito, **processo di approvazione** per le watchlist con audit trail (rilevante per il GDPR), **data purging** con tempistiche rigorose, **formazione** e documentazione sulle best practice (Lezione 13, §13) |
| **Reporting** (p. 105) | **storico** dei match per individui o gruppi su migliaia di location; **segmentazione** delle watchlist per gruppo, zona o tag; **report per location**; **ricerca** storica per immagine o parola chiave; API di riconoscimento |

> ⚠️ **Funzioni tecnicamente possibili, non automaticamente lecite.** Nell'Unione Europea l'AI Act vieta in linea di principio l'**identificazione biometrica remota in tempo reale** negli spazi pubblici a fini di contrasto, con eccezioni, e il GDPR tratta i dati biometrici come categoria particolare (Lezione 13, §5). In Italia il Garante considera il riconoscimento facciale dei privati **di fatto vietato** nella quasi totalità dei casi. Funzioni come la **catalogazione anonima dei visitatori** (creare template di chiunque passi, anche se poi cancellati), le **watchlist** alimentate da mobile o gli **occhiali** con riconoscimento vanno quindi valutate con le domande della Lezione 13, §3, prima di qualsiasi installazione.

<!-- SOURCE_VISUAL id="TAB18-V32" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="98" type="schermate" description="Software di VS avanzati con pacchetti preinstallati per i controlli" reason="Pagina con sole schermate" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.2 Hardware e server Panasonic**

**Panasonic i-PRO Extreme 4×4K, WV-X8570N** (p. 106): camera **multi-sensore** per **safe city** e traffico, con qualità d'immagine elevata anche in condizioni difficili: **4 camere × 4K**, circa **33 MP** in totale ($4 \times 3840 \times 2160 \approx 33{,}2$ MP), a **15 fps**, per acquisire **evidenze da ogni angolazione**.

**Server FacePRO WV-ASF950** (pp. 107–109):

| Caratteristica | Valore (slide) |
|---|---|
| tecnologia | **deep learning** |
| matching multi-camera | fino a **20 camere per server**, database fino a **10 milioni** di volti |
| enrollment | **1.000** «Known Faces» |
| robustezza alla posa | volti ripresi fino a **45°** a destra o sinistra e **30°** in alto o in basso |
| occlusioni | **90% di accuratezza** con volti parzialmente coperti da **occhiali da sole** o **mascherine** |
| acquisizione | tecnologia **best-shot** sulla camera: si invia al server solo il fotogramma migliore |
| scalabilità | da **singolo server** a **cluster** fino a **2000 telecamere** |

> ⚠️ **Che cosa significa «90% di accuratezza»?** Senza soglia, FMR corrispondente, dimensione della watchlist, dataset e popolazione di test, un'accuratezza dichiarata non si può confrontare né verificare (Lezione 10). In una watchlist da migliaia di volti con migliaia di passaggi al giorno, anche un FMR piccolissimo produce falsi allarmi quotidiani ($FPIR \approx N \cdot FMR$, Lezione 14, §16.2).

**Funzioni software tipiche** (p. 110):

- **Face Search**: cerca nel database dei volti e mostra i risultati di ogni camera in **ordine cronologico**, ricostruendo il **percorso** di una persona (è la Re-ID del §1);
- **Face Matching**: allarme **in tempo reale** con storico degli allarmi;
- **conteggio delle persone**, statistiche di **età e genere**.

La p. 111 mostra un esempio di **installazione completa**.

<!-- SOURCE_VISUAL id="TAB18-V33" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="109" type="schema" description="Scalabilità del sistema FacePRO da singolo server a cluster fino a 2000 telecamere" reason="Architettura mostrata nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB18-V34" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="111" type="schema di installazione" description="Esempio di installazione completa di videosorveglianza con riconoscimento facciale" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.3 Sistemi integrati e casi applicativi**

_(slide pp. 112–116)_

| Sistema | Descrizione (slide) |
|---|---|
| **Bosch BVMS 10.1** (p. 112) | sistema **modulare integrato** con dati da videocamere, **controllo accessi**, **antintrusione** e gestione dati; **Video Analytics** integrata con **Forensic Search** per recuperare subito le riprese giuste da ore di video e analizzare la scena con statistiche |
| **SPO-NX** (p. 113) | **screening** (*threat detection*) usato per un evento a **Londra**: sistema **passivo** che rileva le **emissioni del corpo umano** su diverse lunghezze d'onda, individuando **armi di grandi dimensioni**, **ordigni esplosivi improvvisati** (IED) e **contrabbando** non visibili |
| **RoboK** (p. 114) | rischi di sicurezza in un **porto**: individua i punti critici degli incidenti, si integra con altri dati operativi, misura l'efficacia delle politiche. La **Bristol Port Company** ha ridotto significativamente gli incidenti minori analizzando con il deep learning i flussi delle **telecamere CCTV esistenti**, anche per l'assenza dei **dispositivi di protezione individuale**: da **90 potenziali pericoli al giorno** a **pochi incidenti** |

> 💡 **Riusare le telecamere esistenti.** Il caso di Bristol mostra il valore del software rispetto all'hardware: le camere c'erano già, è cambiata l'analisi. È la stessa dinamica per cui un impianto di sorveglianza «classico» può diventare biometrico con un aggiornamento software, ed è per questo che la valutazione di privacy deve riguardare il **sistema**, non solo le telecamere installate.

**Memo** (p. 115): per le applicazioni di IA su immagini, video e segnali si rimanda al corso **Intelligent Systems for Industry, Supply Chain and Environment**.

<!-- SOURCE_VISUAL id="TAB18-V35" source="Lezione_18_Sistemi_di_VideoSorveglianza_e_Riconoscimento_Biometrico.pdf" page="114" type="schermate" description="RoboK: analisi dei rischi di sicurezza nel porto di Bristol con rilevamento dei DPI mancanti" reason="Esempi visivi dei rilevamenti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **14. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Re-identificazione** / **identificazione** | stessa persona tra camere e tempi, anche anonima / confronto con identità note |
| **Person Re-ID** / **face recognition** | corpo, abbigliamento, andatura, a distanza / volto, più discriminante, richiede qualità |
| **CMC (Rank-k)** / **mAP** | trova la persona entro $k$? / ordina bene tutte le sue occorrenze? |
| **Detector** (YOLO) / **estrattore Re-ID** (OSNet) | c'è una persona / quale persona |
| **Zoom** / **PTZ** | solo ingrandimento / anche rotazione e inclinazione |
| **C-mount** / **CS-mount** | 17,5 mm dalla battuta al sensore / 12,5 mm, più economico e compatto |
| **10 log** / **20 log** | rapporti di potenza / rapporti di segnale (tensione) |
| **HDR** / **WDR** | più esposizioni unite, elaborazione complessa / singola immagine bilanciata, tempo reale, specifico della VS |
| **Analogico coassiale** / **IP su Ethernet** | NTSC/PAL, DVR / edge analytics, PoE, multi-stream, megapixel |
| **ONVIF** / **PSIA** | consorzio Axis, Bosch, Sony, Panasonic / Honeywell, GE Security, Cisco |
| **H.264** / **H.265** / **H.266** | AVC, fino a 8 bit / HEVC, 10/12 bit, circa metà bitrate / VVC, oltre 12 bit, costo maggiore |
| **I-frame** / **P-frame** | immagine completa / solo differenze predette |
| **Server centralizzato** / **edge** | tutto il video al server, poco Zero Trust / filtro e struttura alla fonte |
| **Sensore PIR** / **analytics su camera** | variazione di calore / persone, tracce, versi e regole logiche |
| **Riconoscere comportamenti** / **riconoscere individui** | analisi di azioni / identificazione, ma i due si collegano facilmente |

---

### **15. Sintesi della lezione**

_(slide p. 116)_

> ✅ **In sintesi.**
> - **Re-identificazione**: riconoscere la stessa persona tra camere e tempi, a livello di corpo (statico e dinamico) o di volto, anche cross-modale; metriche CMC (Rank-k, richiede ground truth) e mAP (qualità dell'ordinamento); pipeline con YOLOv8n per il rilevamento e OSNet per l'embedding.
> - **Control room**: identità più comportamento; ingressi video, eventi e database; uscite con score, qualità, livelli di allarme ed explanation tag.
> - **Videosorveglianza classica**: camere zoom e PTZ, lenti grandangolari e fisheye, attacchi C e CS, focali fino a 800 mm; gamma dinamica in dB ($20 \log_{10}$, 80 dB per un rapporto $10^4$), HDR contro WDR; sensori Starlight e SoC; passaggio dall'analogico all'IP con PoE, ONVIF e PSIA.
> - **Standard video**: H.264, H.265, H.266 con ROI encoding, maggiore profondità di colore e GoP con I-frame e P-frame.
> - **Moduli avanzati**: 144 dB, compressione intelligente dei volti, esposizione sui volti, edge processing più Zero Trust; analytics per folle, intrusioni, antitaccheggio e sicurezza sul lavoro.
> - **Biometria in VS**: 60 pixel tra gli occhi, 1,09 px/mm, 1636 pixel per 1,5 m (2 MP sì, 1 MP no); sistemi enterprise con watchlist, ricerca e scalabilità, da valutare rispetto ad AI Act, GDPR e Garante; FacePRO, Bosch BVMS, screening passivo e sicurezza portuale.
