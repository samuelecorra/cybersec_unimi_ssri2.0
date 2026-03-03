# **Lezione 14: Sistemi biometrici basati sul volto - introduzione, sensori 2D e algoritmi di prefiltraggio**

---

## **Parte A — Introduzione alla biometria del volto**

### **Sistemi biometrici basati sul volto**

I sistemi biometrici basati sul volto rappresentano una delle tecnologie più diffuse nel panorama biometrico moderno. Essi possono essere utilizzati sia per la **verifica dell’identità** (confronto 1-a-1 tra un campione acquisito e un template registrato) sia per l’**identificazione** (confronto 1-a-N rispetto a una base dati di individui).

Dal punto di vista tecnologico è possibile classificare i sistemi di acquisizione in due grandi categorie:

**Sistemi 2D**

- immagini statiche
    
- sequenze video o collezioni di immagini
    
- immagini a colori
    
- immagini in scala di grigi

**Sistemi 3D**

- scansione laser della geometria del volto
    
- acquisizione con illuminazione controllata

La distinzione è fondamentale perché le informazioni disponibili nei due casi sono molto diverse: nei sistemi 2D si lavora principalmente con texture e intensità luminosa, mentre nei sistemi 3D si acquisiscono anche informazioni geometriche tridimensionali.

---

### **Vantaggi dei sistemi basati sul volto**

I sistemi biometrici facciali presentano diversi vantaggi operativi:

- rappresentano un **ottimo compromesso tra accettabilità da parte dell’utente e accuratezza**
    
- i dispositivi di acquisizione sono **facili da posizionare** e utilizzabili in molte condizioni operative
    
- i risultati sono **direttamente verificabili da un operatore umano**
    
- permettono l’acquisizione anche **non consenziente**, ad esempio in contesti di videosorveglianza

Quest’ultimo aspetto è particolarmente rilevante rispetto ad altre biometrie (come impronta o iride), che richiedono cooperazione dell’utente.

---

### **Svantaggi e criticità**

I sistemi facciali soffrono però di una **elevata variabilità intraclasse**, cioè differenze anche molto marcate tra campioni della stessa persona. I principali fattori che introducono variabilità sono:

- illuminazione
    
- posa della testa
    
- variazioni dell’aspetto nel tempo (invecchiamento, dimagrimento, ecc.)
    
- occlusioni (occhiali, barba, cappelli, veli)
    
- differenze tra sensori

Un ulteriore problema riguarda la **vulnerabilità agli attacchi di spoofing**, in particolare nei sistemi non dotati di meccanismi di liveness detection.

Storicamente l’accuratezza dei sistemi facciali è stata inferiore rispetto ad altre biometrie; solo recentemente le prestazioni sono diventate adeguate per applicazioni su larga scala. I sensori possono essere considerati maturi, mentre gli algoritmi sono ancora in continua evoluzione.

---

### **Applicazioni oltre identificazione e verifica**

Molti algoritmi sviluppati per il riconoscimento facciale trovano applicazione anche in altri ambiti:

- riconoscimento delle espressioni facciali
    
- analisi del movimento delle labbra (utile anche per multimodalità e antispoofing)
    
- computer grafica (sintesi e animazione del volto)
    
- pianificazione chirurgica e creazione di protesi pre-intervento

Questo dimostra che la biometria facciale è strettamente collegata alla computer vision e alla grafica computazionale.

![](imgs/Pasted%20image%2020260220134641.png)

---

### **Fasi principali della catena biometrica facciale**

La pipeline tipica di un sistema di riconoscimento facciale comprende le seguenti fasi:

1. **Face Detection** — individuazione dei volti nella scena
    
2. **Face Segmentation** — separazione del volto dallo sfondo
    
3. **Face Tracking** — inseguimento nel tempo in caso di video
    
4. **Face Normalization** — normalizzazione geometrica e fotometrica
    
5. **Feature Extraction** — estrazione delle caratteristiche distintive
    
6. **Matching** — confronto con i template registrati

![](imgs/Pasted%20image%2020260220134704.png)

Questa catena è analoga a quella di altri sistemi biometrici ma presenta peculiarità legate alla complessità del segnale visivo.

---

### **Stato dell’arte e accelerazione tecnologica**

Negli ultimi decenni si è osservata una **accelerazione impressionante delle prestazioni** dei sistemi di riconoscimento facciale, documentata da competizioni indipendenti come FRVT e FRGC.

I progressi sono stati particolarmente evidenti nei seguenti scenari:

- immagini multiple della stessa persona
    
- immagini ad alta risoluzione
    
- sistemi tridimensionali

![](imgs/Pasted%20image%2020260220134725.png)

---

### **Impatto del deep learning**

L’introduzione del **deep learning**, in particolare delle **Convolutional Neural Networks (CNN)**, ha portato miglioramenti significativi in tutte le fasi della pipeline:

- individuazione dei volti anche in ambienti non controllati
    
- segmentazione
    
- estrazione delle feature
    
- matching

Questi aspetti vengono trattati più approfonditamente in corsi avanzati di biometria.

![](imgs/Pasted%20image%2020260220134801.png)

---

### **Variabilità intraclasse del volto**

Molti fattori influenzano negativamente l’accuratezza aumentando la variabilità intraclasse:

- illuminazione
    
- espressioni facciali
    
- posa
    
- occlusioni
    
- sensori
    
- invecchiamento

Un aspetto particolarmente critico è la **variabilità temporale**, cioè il cambiamento del volto nel tempo, che può avvenire su scale di anni o decenni.

![](imgs/Pasted%20image%2020260220134833.png)

![](imgs/Pasted%20image%2020260220134905.png)

---

### **Similitudine interclasse**

Un problema opposto è la **similitudine interclasse**, cioè la somiglianza tra individui diversi. I casi più difficili includono:

- gemelli
    
- parenti stretti
    
- sosia

Questa problematica è legata anche alla genetica.

![](imgs/Pasted%20image%2020260220134925.png)

(notare Er Pupone)

---

### **Contributo della genetica**

Gli esseri umani condividono la maggior parte del patrimonio genetico: solo circa **0.1%–0.5% del DNA** differisce tra individui. Tra parenti stretti la differenza media è ulteriormente ridotta.

Circa il **50% del DNA è ereditato** dai genitori, ma la differenza di età può aiutare la discriminazione biometrica.

![](imgs/Pasted%20image%2020260220134958.png)

---

### **Sosia e analisi degli score**

Studi recenti hanno analizzato gli score di similarità tra coppie di sosia confrontandoli con:

- gemelli omozigoti
    
- individui non correlati

I risultati mostrano che i sistemi moderni distinguono generalmente i sosia, ma con maggiore difficoltà rispetto a individui non simili.

![](imgs/Pasted%20image%2020260220135114.png)

![](imgs/Pasted%20image%2020260220135223.png)

---

### **Fattori più negativi per le prestazioni**

Competizioni indipendenti hanno evidenziato che i fattori con maggiore impatto sulle performance sono:

- illuminazione (indoor vs outdoor)
    
- posa del volto

Questi fattori possono ridurre drasticamente l’accuratezza.

![](imgs/Pasted%20image%2020260220135248.png)

---

### **Procedure standard di acquisizione**

Organizzazioni internazionali come ICAO hanno definito standard per l’acquisizione delle immagini facciali nei documenti ufficiali.

Gli obiettivi includono:

- illuminazione uniforme
    
- esposizione corretta
    
- assenza di riflessi sugli occhiali
    
- volto frontale
    
- sfondo uniforme

Tali standard migliorano significativamente le prestazioni dei sistemi biometrici.

![](imgs/Pasted%20image%2020260220135358.png)

---

### **Standard dei template e interoperabilità**

Ogni produttore utilizza algoritmi proprietari per generare il template biometrico, spesso protetti da brevetto. Ciò comporta:

- difficoltà di interoperabilità tra sistemi diversi
    
- necessità, in alcuni casi, di scambiare immagini originali anziché template

L’ICAO definisce standard per le immagini dei documenti di viaggio elettronici, **M**achine Readable **T**ravel **D**ocument (MRTD) come gli ePassport, ad esempio:

- almeno 300 dpi
    
- almeno 90 pixel tra gli occhi
    
- dimensione circa 643 kB (riducibile con compressione)

![](imgs/Pasted%20image%2020260220135558.png)

---

### **Compressione delle immagini facciali**

Gli standard di compressione principali sono:

- JPEG
    
- JPEG2000

Con compressione adeguata è possibile ottenere immagini utilizzabili anche con dimensioni molto ridotte.

Valori tipici:

- volto ePassport: 15–20 kB (JPEG2000)
    
- impronte: ~10 kB (WSQ)
    
- iride: ~30 kB

Sotto determinate soglie la qualità diventa insufficiente per uso biometrico.

![](imgs/Pasted%20image%2020260220135622.png)

---

### **Trend nel face recognition**

Le principali direzioni di ricerca includono:

- ambienti non controllati (unconstrained)
    
- immagini non frontali
    
- modelli morfabili
    
- riconoscimento su grandi database
    
- analisi statistica delle performance
    
- studio dei fattori demografici
    
- miglioramento teorico dei modelli

Il deep learning rimane uno dei filoni più promettenti.

---

### **Esempio di sistema commerciale: VeriLook SDK**

Un esempio di tecnologia commerciale offre:

- riconoscimento in modalità stand-alone o web
    
- riconoscimento simultaneo di più volti
    
- matching rapido 1-a-1 e 1-a-N
    
- supporto multipiattaforma
    
- template di dimensione 4–7 kB
    
- confronto fino a 40 000 volti al secondo
    
- riconoscimento emozioni
    
- stima età e genere
    
- live detection

![](imgs/Pasted%20image%2020260220140355.png)

![](imgs/Pasted%20image%2020260220140415.png)

---

### **Identificazione biometrica passiva**

È possibile effettuare identificazione biometrica passiva combinando:

- telecamere di videosorveglianza
    
- strumenti di tracking
    
- algoritmi di riconoscimento

![](imgs/Pasted%20image%2020260220140441.png)

---

## **Parte B — Sensori 2D e algoritmi di prefiltraggio**

### **Sensori per il volto 2D**

I sensori per la biometria facciale 2D sono generalmente basati su **CCD** e includono:

- fotocamere
    
- telecamere
    
- webcam
    
- scanner per acquisizione offline
    
- termografi
    
- dispositivi multispettrali

---

### **Near Infrared e sensori avanzati**

L’utilizzo del **near infrared (IR vicino)** permette acquisizioni più robuste in condizioni difficili:

- bassa illuminazione
    
- illuminazione laterale

Dispositivi come Kinect combinano informazioni visibili e IR per migliorare le prestazioni.

---

### **Face detection — primo passo della pipeline**

La face detection consiste nell’individuare uno o più volti in una scena senza prerequisiti.

Le condizioni possono variare notevolmente:

- intensità luminosa
    
- direzione della luce
    
- banda spettrale

Anche i volti presentano variabilità in:

- colore
    
- posizione
    
- scala
    
- posa
    
- espressione
    

---

### **Deep learning per face detection**

I metodi moderni basati su deep learning permettono di ottenere:

- bounding box del volto
    
- punti principali (landmarks)
    
- struttura facciale

Tuttavia non tutte le applicazioni possono utilizzare deep learning per motivi di costo computazionale.

---

### **Approcci classici alla face detection**

Molti metodi utilizzano modelli semplici del volto:

- modelli geometrici
    
- modelli di texture

L’obiettivo è individuare regioni dell’immagine che meglio corrispondono al modello e stimare parametri per normalizzare il volto:

- traslazione
    
- scala
    
- rotazione
    

---

### **Face detection basata sul colore**

Una pipeline tipica per immagini a colori comprende:

1. compensazione dell’illuminazione
    
2. rilevamento del tono della pelle
    
3. localizzazione delle feature facciali
    
4. aggregazione dei risultati

La rilevazione del tono della pelle utilizza spazi colore come:

- RGB
    
- YCrCb

I pixel appartenenti alla regione cromatica della pelle vengono selezionati tramite sogliatura.

---

### **Localizzazione di occhi e bocca**

La posizione degli occhi e della bocca può essere determinata sottraendo immagini in spazi colore opportuni (ad esempio YCrCb) che evidenziano le caratteristiche cromatiche specifiche, seguite da filtraggi morfologici.

---

### **Haar features e metodo Viola-Jones**

Le **Haar features** sono basate su differenze di intensità tra regioni rettangolari dell’immagine.

Caratteristiche:

- confronto tra 2 o 3 regioni
    
- diverse forme e orientamenti
    
- ricerca dei massimi della trasformata

Questo approccio è alla base del famoso algoritmo **Viola-Jones**, che utilizza una cascata di classificatori.

---

### **Face tracking**

Nel caso di video si utilizza il **face tracking**.

Differenze rispetto alla detection frame-per-frame:

- maggiore informazione temporale
    
- limitazioni sul movimento possibile tra frame
    
- possibilità di previsione della posizione futura
    

---

### **Tool basati su deep learning**

Strumenti moderni come OpenFace 2.0 permettono di ottenere risultati avanzati grazie a modelli deep learning.

---

## **Sintesi della lezione**

  

In questa lezione sono stati trattati:

- sistemi biometrici basati sul volto 2D e 3D
    
- vantaggi e svantaggi della biometria facciale
    
- variabilità intra- e interclasse
    
- standard ICAO e compressione delle immagini
    
- trend di ricerca e deep learning
    
- sensori 2D e near infrared
    
- algoritmi di face detection (color-based, Haar, deep learning)
    
- face tracking nei video