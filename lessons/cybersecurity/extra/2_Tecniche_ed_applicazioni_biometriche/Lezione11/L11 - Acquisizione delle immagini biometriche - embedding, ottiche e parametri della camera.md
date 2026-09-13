# **Lezione 11: Acquisizione delle immagini biometriche — embedding, ottiche e parametri della camera**

---

### **0. Panoramica della lezione**

_(slide p. 1)_

La lezione ha due parti:

- **Parte A** (pp. 2–23): si continua a usare **DeepFace**, passando dalle funzioni pronte all'uso diretto dei **vettori di embedding**, delle **distanze** e dei **grafici delle distanze tra genuini**;
- **Parte B** (pp. 24–135, indicata come L11b): le **ottiche** e i **parametri della camera** per l'acquisizione biometrica: ingrandimento, messa a fuoco, aberrazioni, lenti asferiche, liquide e metalenti, zoom, regolazioni ottiche, scelta della focale e del campo visivo, camere di sorveglianza, regolazioni elettroniche (risoluzione, frame rate, ROI, esposizione, WDR, HDR), macro ed esempi per impronte e iride.

---

## **Parte A — Embedding e distanze con DeepFace**

### **1. Dal risultato pronto al vettore di embedding**

_(slide pp. 2–12)_

#### **1.1 Obiettivo**

_(slide pp. 2–5)_

DeepFace è una delle librerie migliori e più aggiornate, **con i suoi limiti**. L'obiettivo ora è usare **direttamente i vettori delle caratteristiche** (*embedding*) per applicazioni più avanzate e flessibili, implementando alcuni dei metodi teorici delle lezioni precedenti. Si importano le librerie `numpy`, `cv2`, `matplotlib`, `deepface` e le patch di Colab per visualizzare le immagini.

#### **1.2 Ripasso: analisi del volto**

_(slide pp. 6–8)_

L'analisi di età e genere su due foto di **Cindy Crawford**:

| Immagine | Età vera | Età stimata | Genere stimato | Regione del volto |
|---|---:|---:|---|---|
| `Cindy24.jpg` | 24 | **23** | Woman (91,6%) | $x = 276$, $y = 430$, $w = h = 704$ |
| `Cindy57.jpg` | 57 | **35** | Woman (99,9%) | $x = 92$, $y = 70$, $w = h = 227$ |

> ⚠️ **Una stima di età sbagliata di 22 anni.** Su `Cindy57.jpg` il modello stima 35 anni invece di 57. La slide commenta: «ok che è Cindy Crawford, ma forse serve del **tuning**», e suggerisce di **provare altri age detector**. Il modello di età è allenato su dati la cui distribuzione può non rappresentare bene persone di età avanzata o volti con trattamenti estetici e trucco professionale.

**Il face detector dentro DeepFace** (p. 8): dal risultato dell'analisi si ricava la **regione del volto** ($x, y, w, h$) e la si disegna come rettangolo sull'immagine. Nelle lezioni precedenti si usava **direttamente** Haar Cascade di OpenCV; qui si usa il `detector_backend` **predefinito** di DeepFace, che nella versione usata è **ancora OpenCV Haar Cascade**. Per cambiarlo si passa, per esempio, `detector_backend='ssd'` (*Single Shot MultiBox Detector*).

> 📌 **Controllare sempre ogni singolo passaggio della catena di elaborazione.** Detector, allineamento, estrattore e metrica sono moduli diversi, e un errore in uno qualsiasi si propaga a tutti i successivi.

<!-- SOURCE_VISUAL id="TAB11-V01" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="7" type="risultato del laboratorio" description="Cindy57.jpg con età stimata 35 contro età reale 57" reason="Evidenza visiva dell'errore di stima dell'età" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3 Estrarre l'embedding**

_(slide pp. 9–12)_

La funzione `represent` restituisce, per ogni volto rilevato, un dizionario che contiene il campo `embedding`. Con il modello predefinito **VGG-Face** il vettore ha:

- **tipo**: double;
- **lunghezza**: **2622**.

**Altri estrattori** (pp. 10–11): si provano in ciclo i modelli `VGG-Face`, `Facenet`, `OpenFace`, `DeepFace`, `DeepID`, `Dlib`, `ArcFace`, con `enforce_detection=False` (non si interrompe se il volto non viene rilevato). Si osservano:

- **dimensioni dei modelli molto diverse**, **da 1,6 milioni a 551 milioni di parametri**;
- **lunghezze diverse** dell'embedding;
- **tempi di inferenza diversi**.

> 💡 **Nota didattica (non presente nelle slide): lunghezze tipiche.** Nella documentazione della libreria le dimensioni sono dell'ordine di: Facenet **128**, OpenFace **128**, Dlib **128**, DeepID **160**, ArcFace **512**, DeepFace **4096**; per VGG-Face la versione usata nelle slide restituisce **2622** (versioni più recenti possono restituire un vettore diverso). Vanno sempre verificate stampando la lunghezza, come fa il codice della slide.

**Come visualizzare un embedding** (p. 12): è un **vettore di double**; di solito lo si mostra come **immagine $1 \times D$** (una riga di pixel colorati) o come **grafico** dei valori.

<!-- SOURCE_VISUAL id="TAB11-V02" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="12" type="grafici" description="Visualizzazione di un vettore di embedding come immagine 1xD e come plot" reason="Mostra le due rappresentazioni del vettore" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Distanze e similitudini tra embedding**

_(slide pp. 13–20)_

#### **2.1 Uniformare distanza e similitudine**

_(slide pp. 13–14)_

Per confrontare due embedding $\mathbf{a}$ e $\mathbf{b}$ si usano tre grandezze:

| Grandezza | Formula | Range | Significato |
|---|---|---|---|
| **Distanza euclidea** | $d_E = \lVert \mathbf{a} - \mathbf{b} \rVert = \sqrt{\sum_i (a_i - b_i)^2}$ | $[0, +\infty)$ | **spostamento assoluto** nello spazio delle feature |
| **Similarità coseno** | $\cos\theta = \frac{\mathbf{a} \cdot \mathbf{b}}{\lVert\mathbf{a}\rVert\,\lVert\mathbf{b}\rVert}$ | $[-1, 1]$ | 1 = stessa direzione; più alto = più simile |
| **Distanza coseno** | $d_{cos} = 1 - \cos\theta$ | $[0, 2]$ | 0 = stessa direzione; **ignora la norma** del vettore |

> ⚠️ **Errore nel codice della slide p. 14, corretto dal docente.** Il codice originale assegnava il risultato di `distance.cosine` di SciPy alla variabile della **similarità** e calcolava la «distanza» come $1 - $ quel valore, **invertendo** le due grandezze. SciPy però restituisce una **distanza** (0 per direzioni identiche). L'**erratum del 09/02/2026** (file `aggiornamento.md` nella cartella della lezione) corregge la funzione `calculate_metrics`: prima si calcola `cosine_dist = distance.cosine(...)`, poi `cosine_sim = 1 - cosine_dist`. All'esame conta la definizione corretta della tabella.

#### **2.2 La dashboard della distanza**

_(slide pp. 15–17)_

Per capire **dove** due embedding differiscono si scompone la distanza euclidea:

1. differenza componente per componente: $\boldsymbol{\delta} = \mathbf{a} - \mathbf{b}$;
2. contributi quadratici: $\delta_i^2$ per ogni componente;
3. distanza totale: $d_E = \sqrt{\sum_i \delta_i^2}$.

Il **vettore dei contributi quadratici** si visualizza come una **mappa di calore**: le componenti più «calde» sono quelle che più allontanano i due volti. Il codice completo è nel file allegato.

> ⚠️ **Osservare la scala.** I valori dei singoli contributi sono **molto bassi**: con migliaia di componenti, ogni componente pesa poco e la distanza emerge dalla **somma**. Una mappa con la scala automatica può far sembrare enormi differenze che in realtà sono minime.

<!-- SOURCE_VISUAL id="TAB11-V03" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="16" type="dashboard" description="Dashboard della distanza: immagini, embedding e vettore dei contributi quadratici come mappa di calore" reason="Mostra la scomposizione della distanza euclidea" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V04" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="17" type="dashboard" description="Dashboard della distanza tra Cindy24.jpg e Cindy57.jpg con attenzione alla scala" reason="Esempio su un confronto genuino a 33 anni di distanza" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V05" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="18" type="dashboard" description="Altre comparazioni tra coppie di volti" reason="Confronti aggiuntivi mostrati solo come immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3 La distanza tra genuini: un ottimo modo di ragionare**

_(slide p. 20)_

Il grafico delle **distanze calcolate per la stessa persona** (un'immagine di riferimento confrontata con molte altre) permette di:

- scegliere la **soglia** (nell'esempio **0,5**);
- studiare l'**invecchiamento**, la **sensibilità alle rotazioni**, agli **sfondi**, alle **luci**, e così via.

> 📌 **Anche con questi sistemi serve un dataset simile all'applicazione.** Per decidere al meglio la soglia bisogna creare dataset con immagini **simili a quelle reali** e confrontare la distribuzione delle distanze dei **genuini** con quella degli **impostori** (Lezione 9).

<!-- SOURCE_VISUAL id="TAB11-V06" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="20" type="grafico" description="Distanze tra un riferimento e altre immagini della stessa persona con soglia 0,5 e distribuzioni genuini/impostori" reason="Mostra come si sceglie la soglia dalle distanze" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Compiti facoltativi**

_(slide pp. 21–23)_

Le regole sono quelle della Lezione 9: **non sono progetti d'esame**, **non danno punti**; si inviano codice, immagini di ingresso e uscita e un PDF di una pagina.

| Compito | Richiesta |
|---|---|
| **Similitudine parentale** | cercare **similitudini tra genitori e figli** per studiare l'effetto della **genetica** sul volto (un figlio eredita il **50% del DNA** da ciascun genitore); la slide propone coppie da confrontare |
| **Esame della risoluzione** | studiare il **deterioramento** dei vari modelli al **ridursi della risoluzione**, partendo da due foto di un genuino con buon matching ad alta risoluzione e scalando a **0,5×**, **0,25×**, …, tenendo conto della **larghezza in pixel del volto** |

> ⚠️ **Nota della slide sul DNA.** Tutti gli esseri umani condividono circa il **99,9%** del loro DNA: il «50% ereditato» riguarda la parte che **varia** tra individui. Una somiglianza tra genitore e figlio nello spazio degli embedding non misura la parentela genetica, ma può diventare un fattore di **similitudine interclasse** (Lezione 2).

<!-- SOURCE_VISUAL id="TAB11-V07" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="22" type="coppie di volti" description="Compito similitudine parentale: coppie genitore-figlio da confrontare" reason="Le coppie proposte sono solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.1 Materiali di laboratorio**

I materiali forniti nella cartella della lezione sono:

| Cartella | Contenuto | Uso |
|---|---|---|
| `Cartella Codice e immagini` | `codice_L11.py`, export di un notebook **Colab** (righe `!pip`, `cv2_imshow`): analisi di età e genere, disegno della regione del volto, estrazione degli embedding, confronto tra 7 estrattori, distanze euclidea e coseno, grafici | laboratorio della Parte A |
| | `aggiornamento.md`: **erratum** del 09/02/2026 sulla funzione delle metriche coseno (§2.1) | correzione del codice |
| | immagini `Cindy24.jpg`, `Cindy50.jpg`, `Cindy57.jpg` (stessa persona a età diverse), `KaiaGerber_CindyCrawford.jpg`, `gerber_familiy.jpg`, `Rande.jpg`, `Finiteleferie.jpg` | ingressi alternativi, anche per la similitudine parentale |
| `File per lab e prove` | `face3.jpg`, `Mark1.jpg`, `Mark2.jpg` | immagini di prova (usate anche nella Lezione 9) |
| | `modelNweight/`: rete **Caffe** per la stima dell'**età** (`age_deploy.prototxt` + `age_net.caffemodel`), rete Caffe per il **genere** (`gender_deploy.prototxt` + `gender_net.caffemodel`), **face detector** DNN di OpenCV (`opencv_face_detector.pbtxt` + `opencv_face_detector_uint8.pb`, quantizzato a 8 bit) | modelli pre-allenati per sperimentare **age detector alternativi**, come suggerito a p. 7 |

> ⚠️ **L'export del codice ha alcuni difetti.** Il file chiama una funzione di plotting dell'embedding che non viene mai definita, definisce due volte la funzione di visualizzazione e contiene una stampa residua che riusa l'ultimo nome di modello del ciclo. Il codice **non è materia d'esame**, ma chi lo esegue deve correggerli.

---

## **Parte B — Ottiche e parametri della camera**

### **4. Perché le ottiche contano in biometria**

_(slide pp. 24–26)_

L'outline della Parte B: **ingrandimento**, **messa a fuoco**, **zoom** (ingrandimento variabile), **gruppi ottici** nelle applicazioni, **regolazioni ottiche**, **scelta della focale**.

La parte di visione è diventata **preponderante** nella biometria, e le ottiche sono ovunque:

- sensori per **impronta e palmo senza contatto**;
- **iride**;
- **volto**;
- **sorveglianza**, fissa o **autonoma** (robot e droni);
- **comportamento**;
- **soft biometrics**;
- sistemi di **liveness detection e anti-spoofing**.

> 📌 **Se la parte ottica non è scelta, progettata e regolata perfettamente, tutto il sistema non lavora bene.** La prima funzione di un'ottica è **raccogliere luce**, ma la sua vera funzione in biometria è **controllare quale informazione arriva al sensore e con quale qualità**.

> 💡 **Il collegamento con la Lezione 4.** Nessun algoritmo recupera un dettaglio che l'ottica non ha portato sul sensore: i 70 pixel sul raggio dell'iride o i 1000 dpi per i pori delle impronte sono vincoli **ottici** prima che algoritmici.

---

### **5. Ingrandimento e messa a fuoco con una lente**

_(slide pp. 27–32)_

#### **5.1 Ingrandimento con una lente singola**

_(slide pp. 27–29)_

Con una sola lente, l'**ingrandimento** (*magnificazione*) dipende dalla distanza dell'oggetto: un oggetto **vicino** alla lente produce un'immagine più grande, un oggetto **lontano** un'immagine più piccola.

> 💡 **Nota didattica (non presente nelle slide): l'equazione delle lenti sottili.** Con $f$ la focale, $o$ la distanza dell'oggetto e $i$ la distanza dell'immagine:
>
> $$\frac{1}{f} = \frac{1}{o} + \frac{1}{i} \qquad m = \frac{i}{o}$$
>
> Avvicinando l'oggetto ($o$ diminuisce), l'immagine si allontana ($i$ aumenta) e l'ingrandimento $m$ cresce.

<!-- SOURCE_VISUAL id="TAB11-V08" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="28" type="diagramma ottico" description="Ingrandimento con lente singola: oggetto vicino e oggetto lontano dalla lente" reason="I raggi e le immagini sono rappresentati graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Messa a fuoco**

_(slide pp. 30–31)_

- Se l'oggetto **non è a fuoco**, i raggi provenienti dallo **stesso punto** dell'oggetto raggiungono il sensore su **pixel diversi**: il punto diventa una macchia.
- **Non potendo spostare il sensore**, si **sposta la lente** per avere l'immagine a fuoco sul sensore.
- **Messa a fuoco mediante contrasto** (p. 31): si immagina un **punto luminoso**, si **esamina la luce lungo una colonna** del sensore e si **corregge la posizione della lente** finché il profilo diventa il più stretto e contrastato possibile.

> ⚠️ **L'oggetto deve avere dettagli.** L'autofocus a contrasto funziona solo se nell'immagine c'è qualcosa da mettere a fuoco: su una superficie uniforme non esiste un «contrasto massimo» da cercare.

<!-- SOURCE_VISUAL id="TAB11-V09" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="31" type="diagramma" description="Messa a fuoco a contrasto: punto luminoso, profilo di luce lungo una colonna del sensore, correzione della posizione della lente" reason="Mostra il principio dell'autofocus a contrasto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3 Ingrandimenti estremi**

_(slide p. 32)_

Esempio: ottica **Ricoh** con focale di arrivo di **660 mm**. Focali così lunghe permettono di acquisire dettagli a grande distanza (§7.5).

<!-- SOURCE_VISUAL id="TAB11-V10" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="32" type="fotografia prodotto" description="Ottica Ricoh H55ZC con focale di arrivo 660 mm" reason="Esempio visivo di teleobiettivo estremo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Aberrazioni e nuovi tipi di lenti**

_(slide pp. 33–54)_

#### **6.1 I problemi delle lenti reali**

_(slide pp. 33–40)_

Una **lente spessa** (*compound lens*) ha piani principali e punti nodali, e presenta diversi problemi:

| Problema | Descrizione (slide) |
|---|---|
| **Vignettatura** (*vignetting*) | arriva **più luce dal centro che dai bordi** dell'immagine: parte dei raggi obliqui viene bloccata dalla montatura; ne soffrono le ottiche per **microscopia** e i **grandangolari** |
| **Aberrazione cromatica** | la lente ha **indici di rifrazione diversi per lunghezze d'onda diverse**: i colori vanno a fuoco in punti diversi |
| **Distorsione radiale e tangenziale** | l'immagine reale si discosta dalla griglia ideale |
| **Aberrazione sferica** | le lenti **sferiche** sono l'unico formato **facile da produrre**, ma non sono adatte a una messa a fuoco perfetta |

> 📌 **Che cosa hanno in comune.** In tutti questi problemi **i raggi non passano dove dovrebbero**.

- **Nella sorveglianza** (p. 39): nel volto l'**aberrazione sferica** si **somma** all'**aberrazione prospettica** delle focali basse (§8.3).
- **Aberrazione cromatica e anti-spoofing** (p. 40): alcune tecniche di anti-spoofing si basano proprio sull'**uso dei colori**, e quindi risentono di questo difetto.

> 💡 **Nota didattica (non presente nelle slide): la vignettatura naturale.** Anche con un'ottica ideale l'illuminazione cala verso i bordi circa come $\cos^4\theta$, dove $\theta$ è l'angolo del raggio rispetto all'asse ottico: a $\theta = 30°$ arriva circa il 56% della luce del centro. Per questo i grandangolari, con angoli ampi, ne soffrono di più.

<!-- SOURCE_VISUAL id="TAB11-V11" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="34" type="diagrammi ottici" description="Problemi delle lenti: lente spessa con piani principali, vignetting, aberrazione cromatica, distorsione radiale e tangenziale" reason="I quattro difetti sono illustrati graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V12" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="36" type="diagramma con formula" description="Vignettatura: più luce da A che da B e rapporto I/I_bordo" reason="Verificare la formula della vignettatura presente come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V13" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="38" type="diagramma" description="Aberrazione sferica: raggi marginali focalizzati in un punto diverso" reason="Mostra la geometria dell'aberrazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Correggere l'aberrazione sferica e le lenti asferiche**

_(slide pp. 41–44)_

L'aberrazione sferica compare quando le lenti hanno curvatura sferica: i raggi che colpiscono le **zone marginali** vengono focalizzati in un punto leggermente diverso. Si può rimediare:

1. **chiudendo il diaframma**, usando solo la parte centrale dell'ottica;
2. **inserendo lenti correttive**;
3. **usando lenti asferiche**.

> 📌 **Lente asferica.** Lente il cui profilo **non è né una porzione di sfera né un cilindro a base circolare**.

Vantaggi (p. 43): tendono a essere **più piatte e leggere**; alcuni profili **eliminano l'aberrazione sferica** e riducono altre aberrazioni; **una singola lente asferica** può spesso **sostituire un sistema multi-lente** molto più complesso; il dispositivo risultante è **più piccolo, leggero** e a volte **meno costoso**. Appoggiando una lente sferica e una asferica su un **reticolo stampato** (p. 44) si vede la differenza di distorsione.

<!-- SOURCE_VISUAL id="TAB11-V14" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="44" type="fotografia comparativa" description="Lente sferica e lente asferica appoggiate su un reticolo stampato" reason="Il confronto di distorsione è visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Lenti liquide**

_(slide pp. 45–50)_

> 📌 **Lente liquida.** Obiettivo costituito da **un'unica piccola lente «liquida»**: **due fluidi isodensi** contenuti al suo interno ne **modificano la curvatura** in funzione della **tensione elettrica** applicata, risolvendo la messa a fuoco nelle ottiche piccole senza parti meccaniche in movimento.

- Nell'esempio macro della slide si notano **creste ben visibili** (p. 46).
- Rappresentano il **futuro dell'ottica** e aprono nuove applicazioni (p. 47).
- Esiste anche il **diaframma liquido**, che permette di regolare diaframma e ingrandimento a **30 FPS** (p. 49).

Esempio commerciale **Corning A-25H Variable Focus Lens** (p. 50): dimensioni **2,5 × 7,5 mm**, consumo **1 mW**; applicazioni in sistemi piatti a basso ingombro, camere industriali e biometriche miniaturizzate; progettazione facilitata, **risposta rapida (30 FPS)**, campo di fuoco da **5 cm a infinito**, facile integrazione, ideale per l'**autofocus**, ottimizzata per **visibile o NIR**.

**Impronte senza contatto** (p. 53): un dispositivo **compatto ed economico** con lente liquida ottiene una **grandissima velocità di messa a fuoco**.

<!-- SOURCE_VISUAL id="TAB11-V15" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="48" type="schema" description="Lenti liquide: realizzazione e funzionamento con due fluidi e tensione elettrica" reason="Il principio di funzionamento è illustrato graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V16" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="53" type="fotografia" description="Dispositivo per impronte senza contatto con lente liquida" reason="Esempio applicativo biometrico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4 Metalenti**

_(slide pp. 51–52)_

> 📌 **Metalenti.** Lenti **piatte a nanostruttura**, realizzate con le tecniche tradizionali dei **semiconduttori** costruendo nanostrutture su una superficie piatta. Usano l'**ottica delle metasuperfici** per dirigere e concentrare la luce e possono essere **estremamente sottili**: qualche centinaio di micrometri, circa il doppio dello spessore di un capello.

Esempio commerciale **Metalenz** (p. 52): si usano per creare **micro-proiettori** (come quelli dei cellulari) e riescono a catturare la **polarizzazione** della luce.

> 💡 **Perché interessano l'anti-spoofing.** La luce polarizzata si riflette con **angoli diversi** su una **foto 2D** rispetto a un **volto 3D**, e in modo diverso su una **maschera di silicone** rispetto alla **pelle**. Catturando la polarizzazione, le metasuperfici rivelano caratteristiche del **materiale** e informazioni sulla **profondità**: due indizi preziosi contro gli attacchi di presentazione (Lezioni 19–20).

#### **6.5 Sistemi a due lenti**

_(slide p. 54)_

> 📌 **Regola.** L'**immagine formata dalla prima lente diventa l'oggetto della seconda lente.**

Raggi principali per costruire l'immagine: i raggi passanti per il **fuoco** escono **paralleli** all'asse ottico; i raggi che attraversano il **centro** della lente escono **non deviati**. L'ingrandimento di ogni lente è $m = i/o$ e quello complessivo è il **prodotto** dei due:

$$m = m_1 \cdot m_2 = \frac{i_1}{o_1} \cdot \frac{i_2}{o_2}$$

Il setup è molto flessibile: si possono realizzare una normale **fotocamera**, un **telescopio**, un **microscopio**, … un **sistema biometrico**. Microscopi e telescopi combinano almeno due lenti per ottenere ingrandimenti maggiori di quelli di ciascuna lente singola.

<!-- SOURCE_VISUAL id="TAB11-V17" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="54" type="diagramma ottico" description="Sistema a due lenti: oggetto, lente 1, immagine intermedia, lente 2, immagine finale con distanze o1, i1, o2, i2 e fuochi f1, f2" reason="Costruzione grafica dell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Lo zoom**

_(slide pp. 55–80)_

#### **7.1 Zoom ottico e digitale**

_(slide pp. 55–58)_

> 📌 **Zoom.** Obiettivo complesso la cui **lunghezza focale può variare**: un ingrandimento **variabile**. Si usa in fotocamere, telecamere, binocoli, telescopi, **sistemi biometrici**; le telecamere **Pan-Tilt-Zoom** (PTZ) sono utilissime in tutte le applicazioni di sicurezza.

| **Zoom ottico** | **Zoom digitale** |
|---|---|
| le lenti si spostano e cambiano la focale | l'immagine viene **ritagliata e riscalata** |
| **ingrandisce veramente**: porta più dettagli sul sensore | **non aggiunge informazione** |

La slide confronta un'immagine con zoom **ottico 150×** e una con **150× ottico + 350× digitale**: il digitale rende l'immagine più grande ma non più dettagliata.

<!-- SOURCE_VISUAL id="TAB11-V18" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="58" type="confronto di immagini" description="Zoom ottico 150x contro 150x ottico + 350x digitale" reason="Evidenza visiva della differenza tra zoom ottico e digitale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 Come leggere i parametri di uno zoom**

_(slide pp. 59–60)_

Esempio: **Nikon AF-S DX Zoom-Nikkor 18–55 mm f/3.5–5.6**.

| Parametro | Significato |
|---|---|
| **18–55 mm** | **escursione focale**: da 18 a 55 millimetri |
| **f/3.5–5.6** | **luminosità** (f-number): rapporto tra **lunghezza focale** e **diametro di apertura massima**; vale 3,5 a 18 mm e 5,6 a 55 mm. Le ottiche professionali fanno molto meglio |
| **3×** | **fattore di ingrandimento**: quante volte si ingrandisce rispetto alla focale minima, $3 \times 18\ \text{mm} = 54\ \text{mm}$ |

$$N = \frac{f}{D}$$

dove $N$ è l'f-number, $f$ la focale e $D$ il diametro dell'apertura. Un $N$ più **piccolo** indica un'ottica più **luminosa**.

**Zoom motorizzati** (p. 60): esempio **Computar Motor Zoom Lens** **8,8–150 mm**, attacco **C-mount**, capacità ottica **3 MP**, con cavi di controllo e alimentazione dello zoom, circa **870 euro**, per telecamere industriali.

<!-- SOURCE_VISUAL id="TAB11-V19" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="60" type="fotografia prodotto" description="Computar Motor Zoom Lens 8,8-150 mm C-mount con cavi di controllo e alimentazione" reason="Esempio di zoom motorizzato industriale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3 Lo zoom «neurale»: il caso della luna del Galaxy S21**

_(slide pp. 61–67)_

Alcuni utenti notarono che sul **Galaxy S21** la luna risultava **incredibilmente dettagliata** dopo lo zoom: «cosa lo compro a fare un telescopio? Basta un cellulare!».

**Lo spoofing convincente** (p. 62). L'utente *ibreakphotos* fece questo test:

1. scaricò un'immagine della luna ad alta risoluzione;
2. la **ridusse a 170×170 pixel** e applicò una **sfocatura gaussiana**, così che **tutti i dettagli sparissero**: l'informazione non era più recuperabile;
3. la mostrò a schermo intero sul monitor, si spostò in fondo alla stanza e **spense le luci**;
4. zoomò sul monitor con il telefono;
5. ottenne un'immagine della luna **ricca di dettagli** che nell'immagine mostrata **non esistevano**.

**Test 2** (p. 64): una **mezza luna** modificata **non fu riconosciuta** e quindi **non venne «migliorata»**.

**L'effetto finale** (pp. 65–67): Samsung ha dovuto ammettere che le foto vengono **elaborate e modificate da reti neurali** che hanno imparato da esempi (e la luna era plausibilmente tra gli esempi più diffusi, quindi appresi meglio). Dal **Galaxy S10** la serie usa l'AI nelle fotocamere; per la luna le camere usano la **Super Resolution**, **sintetizzando più di 10 immagini** scattate a **zoom 25× o superiore**. La pipeline è:

1. **moon detector** (CNN);
2. regolazione della **luminosità**;
3. **aggiunta dei dettagli** (CNN).

> ⚠️ **Due domande per la biometria.** Le foto dei dispositivi mobili sono **ancora reali e usabili per la biometria**? Sono **ancora prove «fotografiche»**? Se una rete aggiunge dettagli plausibili ma **inventati**, un volto, un'iride o un'impronta ingranditi potrebbero contenere feature che **non appartengono** alla persona reale.

<!-- SOURCE_VISUAL id="TAB11-V20" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="63" type="confronto di immagini" description="Luna sfocata a 170x170 mostrata sul monitor e risultato dettagliato ottenuto con lo zoom del Galaxy S21" reason="Evidenza visiva dei dettagli inventati" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V21" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="64" type="confronto di immagini" description="Test 2: la mezza luna non viene riconosciuta né migliorata" reason="Mostra il limite del moon detector" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V22" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="67" type="pipeline" description="AI enhancing Samsung: moon detector CNN, regolazione luminosità, aggiunta dettagli con CNN" reason="Schema della pipeline di super resolution" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.4 Zoom e focali nella sorveglianza**

_(slide pp. 68–71)_

- **Zoom in sorveglianza** (p. 68): nell'esempio il volto, mascherato nella slide, **era visibile dopo lo zoom**.
- **Focali da record** (p. 69): con focali di arrivo di **660 mm** lo stesso dispositivo può elaborare **dettagli delle persone** ed effettuare **sorveglianza da grande distanza**; la lezione sulla sorveglianza biometrica (Lezione 18) mostra come ottenere questo risultato.
- **Valori comuni di focale** (p. 70): esempio $f = 5{,}7$–$205{,}2$ mm; focali **da sotto 10 mm a oltre 200 mm** sono tipiche.
- **Il vero sistema di lenti di un obiettivo** (p. 71): una buona ottica fotografica può contenere **anche 15 elementi** e costare **migliaia di dollari**; i sistemi moderni includono **lenti asferiche**.

<!-- SOURCE_VISUAL id="TAB11-V23" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="68" type="fotografie" description="Applicazione dello zoom in sorveglianza con volto reso visibile dopo lo zoom (mascherato nella slide)" reason="Esempio applicativo dello zoom" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V24" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="71" type="spaccato di obiettivo" description="Sistema reale di lenti in un obiettivo fotografico con fino a 15 elementi e lenti asferiche" reason="Mostra la complessità di un obiettivo reale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.5 Le ottiche nel mondo mobile**

_(slide pp. 72–78)_

| Dispositivo | Soluzione ottica (slide) |
|---|---|
| **Camera frontale tipica** (p. 72) | **lenti asferiche** per correggere le aberrazioni, **lente mobile** per la messa a fuoco, **filtro IR-cut**, **sensore CMOS** con connettore per circuito flessibile |
| **iPhone X – modulo TrueDepth** (p. 73) | **integrazione di dispositivi miniaturizzati** |
| **Mobile in generale** (p. 74) | niente zoom ottici (principalmente): **zoom software** che riscala algoritmicamente il particolare, anche con reti neurali, che **non aggiungono informazione**; si sfruttano **alta risoluzione** e **autofocus** del sensore per acquisire dettagli biometrici |
| **Oppo** (p. 75) | zoom «**lossless 10×**»: è un'**integrazione software tra ottiche fisse** (fino a una focale equivalente di 159 mm) |
| **Samsung Galaxy S25 Ultra** (p. 76) | zoom ottico **reale** con **ottica periscopica** (*folded optics*) a elementi fissi (circa **3,5×**); la tecnologia **ALoP** (*All Lenses on Prism*) mette le lenti **sopra il prisma** anziché tra prisma e sensore, riducendo lo spessore e aumentando la luminosità (**f/2.58**); è un teleobiettivo a **focale fissa** |
| **Galaxy S9 – iris scanner** (p. 77) | le lenti permettono di riconoscere gli utenti **anche con gli occhiali**, in **scene scure** o con **luce non uniforme**; riconoscimento **sotto 1 s**; sensore **CMOS 3 MP** con **filtri e autofocus** |
| **Drone SIMTOO XT-175** (p. 78) | obiettivo con **lenti asferiche**; numerose applicazioni biometriche per droni stanno arrivando sul mercato |

<!-- SOURCE_VISUAL id="TAB11-V25" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="72" type="spaccato" description="Camera frontale di smartphone: lenti asferiche, lente mobile per la messa a fuoco, filtro no-IR, sensore CMOS con connettore flessibile" reason="Mostra i componenti miniaturizzati" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V26" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="73" type="spaccato" description="Modulo TrueDepth dell'iPhone X" reason="Esempio di integrazione miniaturizzata per il volto 3D" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V27" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="78" type="fotografia" description="Obiettivo del drone SIMTOO XT-175 con lenti asferiche" reason="Esempio di ottica per droni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.6 Ottiche industriali: C-mount**

_(slide pp. 79–80)_

| Caratteristica | Valore (slide) |
|---|---|
| filetto | **maschio sulla lente**, **femmina sul corpo** macchina |
| diametro | **1 pollice (25,4 mm)**, **32 filetti per pollice** («1-32 UN 2A», ANSI B1.1) |
| distanza della flangia | **17,526 mm** |
| sensori | ottimizzato per **1/3″, 1/2″, 2/3″** |
| sorveglianza | si usa una versione compatta chiamata **CS** |

> 📌 **Ottiche e sensori proporzionati** (p. 80). L'ottica deve essere dimensionata per il **formato del sensore**: un'ottica pensata per un sensore piccolo montata su uno grande produce vignettatura, una pensata per un sensore grande su uno piccolo spreca parte del campo.

---

### **8. Regolazioni ottiche**

_(slide pp. 81–103)_

Le **tre regolazioni ottiche principali** sono **focale**, **diaframma** e **messa a fuoco** (p. 82). La slide p. 81 mostra una camera **Stenopeika** (*pinhole*, a foro stenopeico), «non per la biometria».

#### **8.1 Il diaframma e l'f-number**

_(slide pp. 83–85)_

La **quantità di luce** catturata è proporzionale all'**area dell'apertura**:

$$A = \pi r^2 = \pi \left(\frac{f}{2N}\right)^2$$

dove $f$ è la lunghezza focale e $N$ l'**f-number**. Si parla di solito di **rapporto focale** $f/N$ (esempi: f/4, f/5.6, …, f/22). Area e rapporto focale si modificano con il **diaframma**, ma sono comunque **limitati superiormente** dalla dimensione della lente.

**Esempio numerico della slide** con focale 50 mm:

| Rapporto focale | Area |
|---|---:|
| f/2.8 | $\pi \left(\frac{50}{2 \cdot 2{,}8}\right)^2 \approx 250\ \text{mm}^2$ |
| f/4 | $\pi \left(\frac{50}{2 \cdot 4}\right)^2 \approx 123\ \text{mm}^2$ |
| f/5.6 | $\pi \left(\frac{50}{2 \cdot 5{,}6}\right)^2 \approx 63\ \text{mm}^2$ |

> 📌 **1 stop = fattore 2 nell'area.** Chiudendo il diaframma l'**f-number aumenta**. La scala «full stop» (2,8; 4; 5,6; 8; …) moltiplica $N$ per circa $\sqrt{2}$ a ogni passo: poiché l'area dipende da $1/N^2$, ogni passo **dimezza** la luce ($250 \rightarrow 123 \rightarrow 63$).

<!-- SOURCE_VISUAL id="TAB11-V28" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="85" type="sequenza di immagini" description="Luminosità dell'immagine con stesso tempo di esposizione e aperture decrescenti per full stop" reason="Evidenza visiva del dimezzamento della luce" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2 Apertura e profondità di campo**

_(slide pp. 86–88)_

> 📌 **Profondità di campo.** La zona in cui gli oggetti dell'immagine **appaiono ancora nitidi**.

| Diaframma | Profondità di campo | Luce | Conseguenza |
|---|---|---|---|
| **chiuso** (f/22, f/32) | **ampia**: molti oggetti a fuoco | poca | serve **più tempo di esposizione** (rischio di mosso) o più guadagno (**rumore**) |
| **aperto** (f/2.8, f/4) | **ridotta** | molta | esposizioni brevi, ma pochi oggetti a fuoco |

**Lo «stop»** (p. 88): indica **raddoppiare o dimezzare** la quantità di luce che arriva sul sensore in un'esposizione. Si ottiene in tre modi: **tempo di esposizione** (*shutter speed*), **sensibilità ISO**, **diametro dell'apertura**.

> 💡 **Il compromesso in biometria.** Per un volto in movimento serve un tempo di esposizione breve (niente mosso), quindi un diaframma aperto e poca profondità di campo: l'utente deve stare nella zona di fuoco. Per un'iride acquisita a distanza variabile serve profondità di campo, quindi diaframma chiuso e molta illuminazione IR. Le lenti liquide (§6.3) aggirano il compromesso rimettendo a fuoco molto velocemente.

<!-- SOURCE_VISUAL id="TAB11-V29" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="86" type="sequenza di immagini" description="Apertura contro profondità di campo da f/2.8 a f/32" reason="Mostra come cambia la zona a fuoco" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.3 Messa a fuoco, focale e deformazioni del volto**

_(slide pp. 89–97)_

Le slide pp. 89–93 illustrano il **piano di messa a fuoco**, la **regolazione della focale** e la **scelta della focale**.

**Effetto della focale sulle proporzioni del volto** (pp. 94–96): la slide confronta lo stesso volto ripreso con **$f = 40$ mm** e **$f = 240$ mm**. È possibile **compensare la distanza con la focale** (avvicinarsi con una focale corta o allontanarsi con una lunga per inquadrare lo stesso volto), **ma si ottengono deformazioni diverse**. L'esercizio proposto: cercare le deformazioni **a partire dal naso**.

> 💡 **Perché la focale corta deforma.** Con una focale corta la camera deve stare **vicina** al volto: il naso è sensibilmente più vicino all'obiettivo delle orecchie, quindi appare ingrandito e le orecchie rimpicciolite (distorsione **prospettica**). Con una focale lunga la camera è lontana e tutte le parti del volto sono a distanze simili: le proporzioni risultano più fedeli ma il volto appare «schiacciato». Due foto della stessa persona a focali diverse producono quindi **embedding diversi**: è l'esperimento della Lezione 12.

**Scelta della focale per applicazione** (p. 97): dalle focali **corte** alle **lunghe** si passa da **sorveglianza** di scena, a **sorveglianza con volto**, a **volto**, fino all'**iride**.

<!-- SOURCE_VISUAL id="TAB11-V30" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="94" type="confronto di volti" description="Stesso volto con f=40 mm e f=240 mm: deformazioni diverse delle proporzioni" reason="L'effetto prospettico si vede solo sulle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V31" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="97" type="schema" description="Scelta della focale per sorveglianza, sorveglianza con volto, volto e iride" reason="Mappa focale-applicazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.4 Focale e campo visivo (FOV)**

_(slide pp. 98–103)_

Il **Field of View** (FOV) e la focale hanno una **relazione inversa**: una focale più **corta** dà un campo visivo più **ampio**, e viceversa.

> ⚠️ **Formula ricostruita.** Nel livello testo la formula è illeggibile. La forma standard, con $d$ la dimensione del sensore nella direzione considerata e $f$ la focale, è:
>
> $$FOV = 2 \arctan\left(\frac{d}{2f}\right)$$
>
> Esempio (nota didattica): con un sensore largo 36 mm e $f = 50$ mm, $FOV = 2\arctan(0{,}36) \approx 40°$; con $f = 18$ mm, $FOV = 2\arctan(1) = 90°$.

- **Visione umana binoculare** (p. 100): FOV di circa **120°**, ma con **risoluzione diversa** tra centro e periferia. Un sistema biometrico **non deve per forza vedere come un umano**.
- **Non confondere** (p. 101) la **focale** $f$, misurata in **mm**, con il **FOV**, misurato in **gradi**: nei **grandangolari** i due numeri sono simili. Nelle **applicazioni di sicurezza** si parte dall'**angolo della scena** da sorvegliare e se ne ricava la **focale** da installare.
- **Sorveglianza e grandangolari** (p. 102): con focali **fino a 20 mm** si possono vedere, per esempio, **3 corridoi**; con focali basse si raggiungono FOV di **180°**. FOV ampi servono anche per la **visione e la navigazione** di droni, robot e veicoli autonomi (il **flusso ottico** è trattato nella Lezione 16).
- **Iris recognition** (p. 103): richiede invece un FOV **stretto**.

<!-- SOURCE_VISUAL id="TAB11-V32" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="99" type="diagramma con formula" description="Legame fra FOV e focale con formula dell'arcotangente" reason="Verificare la formula del FOV" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V33" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="102" type="fotografia" description="Videosorveglianza grandangolare con 3 corridoi visibili e FOV fino a 180 gradi" reason="Esempio di scelta del FOV" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V34" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="103" type="immagini" description="Esempio di scelta del FOV per iris recognition" reason="Contrasto con il FOV della sorveglianza" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Lenti e camere per la videosorveglianza**

_(slide pp. 104–106)_

**Caratteristiche tipiche da datasheet** (p. 105):

- FOV grandangolare **da 74° a fisheye 190°**;
- alta qualità d'immagine adatta a sensori **CMOS/CCD megapixel**;
- lenti **ottimizzate per HDR** (niente *flare* e riflessi);
- design meccanico **estremamente compatto**;
- attacco **CS**, alternativa **economica** al C-mount.

**Struttura di una camera di sorveglianza** (p. 106), composizione **modulare**:

1. **sensore CMOS**;
2. **sistema di imaging video** embedded (esempio: Pixim D1400);
3. **ottiche CS** adeguate alla dimensione del sensore (di solito **1/3 di pollice**);
4. eventuale motorizzazione **Pan-Tilt-Zoom**;
5. **case protettivo**, con opzione **antivandalo**.

<!-- SOURCE_VISUAL id="TAB11-V35" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="106" type="spaccato" description="Struttura modulare di una surveillance camera: sensore CMOS, sistema di imaging, ottica CS, PTZ, case" reason="Mostra i moduli della camera" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Regolazioni elettroniche del sensore**

_(slide pp. 107–126)_

#### **10.1 Risoluzione e frame rate secondo l'applicazione**

_(slide pp. 107–112)_

La modalità video (risoluzione e frame rate) **deve essere adattata all'applicazione**:

| Applicazione | Risoluzione | Frame rate | Motivo |
|---|---|---|---|
| **leggere la targa** di un veicolo fermo | **alta** | **basso** | servono dettagli, la scena è statica |
| **contare i veicoli** | **minore** | **alto** | serve il **tracking** per un conteggio corretto |
| **Automatic Number Plate Recognition** (ANPR) su veicoli in moto | alta | **50–75 fps** fino a **250 km/h** (esempio Tattile SpA) | il veicolo si sposta molto tra due frame |
| **face recognition** | **alta** | può essere **basso** | serve risoluzione sul volto |
| **contare le persone**, tracking | può essere **bassa** | **alto** | serve continuità nel movimento |

> 💡 **Verifica numerica (nota didattica).** A 250 km/h un veicolo percorre $250 / 3{,}6 \approx 69{,}4$ m/s: a 50 fps si sposta di circa **1,4 m** tra un frame e l'altro, a 75 fps di circa **0,9 m**. Con frame rate più bassi la targa rischia di attraversare l'inquadratura in uno o due frame soltanto.

<!-- SOURCE_VISUAL id="TAB11-V36" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="110" type="esempio di immagini" description="Risoluzione per il face: stesso volto a risoluzioni diverse" reason="Mostra l'effetto della risoluzione sul volto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V37" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="111" type="fotografia" description="ANPR su veicoli in moto fino a 250 km/h con 50-75 fps (Tattile SpA)" reason="Esempio applicativo del frame rate" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2 Region Of Interest ed esposizione**

_(slide pp. 113–114)_

**ROI** (p. 113): si parte da un **sensore CMOS ad alta risoluzione** e si imposta la **ROI** in base a inquadratura e funzione. Vantaggi: **più frame rate**, **meno errori** di elaborazione, **algoritmi più veloci**.

**Tempo di esposizione** (p. 114), detto anche tempo di shutter o *exposure time*: talvolta indicato erroneamente come «shutter speed», ma **non è una velocità**. Come regolarlo? **Devono vedersi i dettagli che davvero servono**: in biometria **non interessano immagini fotograficamente belle**.

#### **10.3 CCD, CMOS e gamma dinamica**

_(slide pp. 115–121)_

- **CCD contro CMOS** (p. 115): le camere **CCD** hanno un'ottima **WDR** e non soffrono dell'**effetto jello** né delle vibrazioni; la recente tecnologia **CMOS** le sta però **superando** anche su questo.

> 📌 **Gamma dinamica** (*dynamic range*). Il **rapporto di contrasto** tra i toni più scuri e quelli più chiari che una camera può catturare **in una singola esposizione**: l'intervallo tra il valore massimo e minimo di una grandezza rilevabile nello stesso frame. **Wide Dynamic Range** (WDR) è il termine usato nell'industria della sorveglianza per l'imaging ad alto range dinamico.

**Quando serve la WDR** (p. 116): showroom, atrii e ingressi con **molte finestre**, dove il contrasto tra luce interna e naturale crea zone scure e chiare; ingressi dove la **luce inonda** l'area all'apertura della porta, rendendo irriconoscibile chi entra; **scene notturne** senza infrarosso, con punti sovraesposti e zone buie.

**I decibel** (p. 119): quando due grandezze differiscono di **diversi ordini di grandezza** il confronto si fa in **dB**; si applica a potenze, intensità, al **rapporto segnale/rumore** (SNR) e alla gamma dinamica:

$$DR = 20 \cdot \log_{10}\left(\frac{I_{max}}{I_{min}}\right)\ [\text{dB}]$$

**Esempio della slide**: se l'intensità di luce $I_{max}$ associata al grigio massimo (255) è **1000 volte** l'intensità minima $I_{min}$ associata al grigio minimo (0), la gamma dinamica vale $20 \cdot \log_{10}(1000) = 20 \cdot 3 = 60$ dB. Esistono sensori da **100, 120 e 144 dB** (esempio: Panasonic **144 dB** *Extreme Super Dynamic*, p. 118; Milesight speed dome H.265+ con sensore **1/1.8″**, zoom **36×** e **140 dB** di WDR, p. 120).

> 💡 **Che cosa significano 144 dB.** Invertendo la formula, $I_{max}/I_{min} = 10^{144/20} = 10^{7{,}2} \approx 16$ milioni: la camera distingue dettagli in zone la cui luminosità differisce di sedici milioni di volte, come un volto in ombra davanti a una finestra al sole.

**Sorveglianza sui treni** (p. 121): le **vibrazioni** producono l'**effetto jello** (deformazioni tipiche dei sensori a *rolling shutter*) e le variazioni di luce nel tempo producono **aliasing temporale**.

<!-- SOURCE_VISUAL id="TAB11-V38" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="118" type="confronto di immagini" description="Esempio per il volto con camera Panasonic 144 dB Extreme Super Dynamic" reason="Mostra il beneficio della WDR" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB11-V39" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="121" type="fotografie" description="Sorveglianza su treni: effetto jello da vibrazioni e aliasing temporale da variazioni di luce" reason="Esempi visivi dei due artefatti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.4 Esposizioni multiple e HDR**

_(slide pp. 122–126)_

| Tecnica | Come funziona (slide) | Quando |
|---|---|---|
| **Multiple exposure contemporanea** | due immagini acquisite **simultaneamente** (colore/monocromatico) con **esposizione o guadagno diversi**, poi trasmesse o **fuse** per migliorare la gamma dinamica | scene complesse con **sole e ombre**, **illuminazione notturna**, più soggetti |
| **High Dynamic Range imaging** | di solito da **frame diversi**; riproduce una gamma dinamica **maggiore** di quella del sensore nel singolo frame | per ottenere un range di luminanza simile a quello percepito dall'**occhio umano** e dati utili sia dalle regioni **chiare** sia da quelle **scure**, evitando **saturazione e rumore** |

> ⚠️ **HDR da frame diversi e movimento.** Se l'HDR fonde frame acquisiti in istanti diversi, un soggetto in movimento produce **fantasmi** (*ghosting*). L'esposizione multipla **contemporanea** evita il problema, a costo di un hardware più complesso.

<!-- SOURCE_VISUAL id="TAB11-V40" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="126" type="confronto di immagini" description="Multiframe HDR in sorveglianza contro immagine senza HDR" reason="Esempio visivo dell'HDR in sorveglianza" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Macro, frame rate ed esempi biometrici**

_(slide pp. 127–135)_

#### **11.1 Ottiche macro**

_(slide pp. 127–128)_

> 📌 **Ottica macro.** Sistema ottico progettato per acquisire soggetti **molto piccoli** riproducendoli sul sensore con un **rapporto di ingrandimento elevato (> 1)**. Anche **piccole variazioni** della posizione dell'oggetto rispetto al piano di fuoco possono renderlo **sfocato**.

**Esempio: ispezione di un circuito stampato** con lente macro **105 mm f/2.8**; il package del circuito integrato è a fuoco e la piastra dista 2,5 mm:

- a **f/32** ogni oggetto è nella **profondità di campo**, ma il testo del package inizia a sfocarsi per la **diffrazione** e servono **tempi di esposizione lunghi**;
- verso **f/5** sempre meno elementi sono a fuoco, e la polvere fuori fuoco forma macchie con la **forma del diaframma**.

È sempre un **compromesso**.

**Macro in biometria: impronte contactless** (p. 128). Un'immagine macro fotografica **non è molto utile** al riconoscimento. Un **sistema di contenimento** del dito, che minimizza lo spostamento rispetto al piano di fuoco, aumenta la **nitidezza** ma riduce l'**usabilità**.

<!-- SOURCE_VISUAL id="TAB11-V41" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="128" type="fotografie" description="Macro per impronte contactless: immagine macro poco utile e sistema di contenimento del dito" reason="Mostra il compromesso nitidezza-usabilità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.2 Il frame rate e il rPPG**

_(slide pp. 129–130)_

Il **frame rate** è **molto importante** per: **motion detection**, **segmentazione**, **anti-spoofing**.

Esempio: un frame rate elevato e molti **bit per pixel** permettono l'anti-spoofing con **remote PhotoPlethysmoGraphy** (rPPG).

> 💡 **Nota didattica (non presente nelle slide): che cos'è il rPPG.** A ogni battito cardiaco il volume di sangue nei capillari del volto varia leggermente, e con esso il colore della pelle. Le variazioni sono **minime** (servono molti bit per pixel) e **periodiche** alla frequenza cardiaca (serve un frame rate sufficiente a campionarle). Una foto o una maschera non presentano questo segnale: è una prova di **vitalità** senza contatto.

<!-- SOURCE_VISUAL id="TAB11-V42" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="130" type="schema" description="Anti-spoofing con remote PhotoPlethysmoGraph (rPPG) grazie a frame rate e bit per pixel elevati" reason="Illustra il segnale estratto dal volto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 Ottiche per l'iride**

_(slide pp. 131–133)_

- **Lenti liquide per l'iride** (p. 131): un documento tecnico del produttore (Corning) mostra che le lenti liquide permettono il riconoscimento **non più in uno stretto intervallo di distanza**, con un **grande miglioramento dell'usabilità**.
- **Iris recognition a basso costo** (pp. 132–133), budget **50 euro**: adattamento di una **webcam** con **LED IR**; **filtro a 850 nm ± 10 nm** con larghezza a metà altezza di **30 nm**; collegamento e alimentazione **USB**; focale **$f = 3{,}6$ mm**; sensore **CMOS da 1 Mpixel**.

**È davvero utilizzabile?** Con una **trasformazione gamma dell'istogramma** ottimizzata per i toni dell'iride **appaiono le prime feature**.

> 💡 **Il collegamento con la Lezione 4.** Il filtro a 850 nm realizza l'acquisizione **NIR** (700–900 nm) che rende visibile la tessitura dell'iride indipendentemente dalla melanina. Con focale 3,6 mm e 1 Mpixel il vero limite è la **risoluzione sul raggio dell'iride**: il requisito di almeno 70 pixel impone di stare molto vicini alla camera.

<!-- SOURCE_VISUAL id="TAB11-V43" source="Lezione_11_Biometric_Image_Acquisition_Tecniche_Esempi.pdf" page="133" type="confronto di immagini" description="Iride acquisita con sistema low cost prima e dopo la trasformazione gamma dell'istogramma" reason="Mostra la comparsa delle prime feature" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.4 Un sistema di accesso con impronte al volo**

_(slide p. 134)_

**BACS CrucialTrak Quattro** (*Biometric Access Control System*), informazioni dal datasheet:

- acquisisce **30 immagini micro-fingerprint al secondo**;
- riconoscimento **«on-the-fly»**: l'utente passa la mano senza fermarsi;
- esperienza **touchless**;
- acquisisce **4 impronte contemporaneamente**;
- riconoscimento **3D «organico»**; matching a **30 scatti al secondo**.

> 💡 **Perché la luce verde.** La slide nota l'uso di **luce verde** per una **maggiore nitidezza delle creste**: le lunghezze d'onda più corte penetrano meno nella pelle e restituiscono un contrasto superficiale più netto tra creste e valli.

---

### **12. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Distanza coseno** / **Similarità coseno** | 0 = identici, range $[0, 2]$ / 1 = identici, range $[-1, 1]$ |
| **Distanza euclidea** / **coseno** | spostamento assoluto / solo angolo, ignora la norma |
| **Zoom ottico** / **digitale** / **neurale** | porta più dettagli / riscala / può inventare dettagli plausibili |
| **Focale** (mm) / **FOV** (gradi) | proprietà dell'ottica / ampiezza della scena, relazione inversa |
| **f-number alto** / **basso** | poca luce, grande profondità di campo / molta luce, poca profondità di campo |
| **Aberrazione sferica** / **cromatica** | raggi marginali fuori fuoco / colori a fuoco in punti diversi |
| **Lente asferica** / **liquida** / **metalente** | profilo non sferico / curvatura variabile con la tensione / nanostrutture piatte, polarizzazione |
| **C-mount** / **CS-mount** | flangia 17,526 mm, industriale / versione compatta per sorveglianza |
| **WDR** / **HDR** | gamma dinamica in una singola esposizione / fusione di più esposizioni |
| **Alta risoluzione** / **alto frame rate** | riconoscere (targa, volto) / tracciare e contare |

---

### **13. Sintesi della lezione**

_(slide p. 135)_

> ✅ **In sintesi.**
> - **DeepFace**: embedding (VGG-Face a 2622 elementi), estrattori da 1,6 a 551 milioni di parametri, **distanza euclidea e coseno** (con l'erratum sulla loro inversione), dashboard dei contributi, **distanze tra genuini** per scegliere la soglia; compiti sulla similitudine parentale e sulla risoluzione.
> - **Ottiche**: ingrandimento e messa a fuoco a contrasto, aberrazioni (vignettatura, cromatica, sferica, distorsioni), lenti **asferiche**, **liquide** e **metalenti** (polarizzazione per l'anti-spoofing), sistemi a due lenti.
> - **Zoom** ottico, digitale e **neurale** (il caso della luna del Galaxy S21), focali da record, ottiche mobile, C-mount.
> - **Regolazioni**: diaframma e f-number ($A = \pi (f/2N)^2$, 1 stop = fattore 2), profondità di campo, focale e **deformazioni del volto**, **FOV**.
> - **Camere di sorveglianza** e parametri elettronici: risoluzione e frame rate per applicazione, ROI, esposizione, **gamma dinamica in dB**, WDR, esposizioni multiple, HDR.
> - **Macro**, frame rate per rPPG, iride low cost a 850 nm e impronte on-the-fly.
