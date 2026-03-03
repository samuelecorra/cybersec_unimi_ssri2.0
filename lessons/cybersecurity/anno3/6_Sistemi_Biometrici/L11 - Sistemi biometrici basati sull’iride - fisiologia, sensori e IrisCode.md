# **Lezione 11 – Sistemi biometrici basati sull’iride: fisiologia, sensori e IrisCode**

---

## Parte A – Caratteristiche dell’iride e sensori

L’iride è considerata il tratto biometrico più accurato in assoluto dopo il DNA. Possiede un numero elevatissimo di caratteristiche localmente casuali, stabili nel tempo e difficilissime da falsificare. A differenza delle impronte digitali, l’acquisizione dell’iride avviene senza contatto e l’organo è interno, protetto dalla cornea.

### Accuratezza e percezione

- L’informazione iridea si stabilizza completamente dal **secondo anno di vita** e rimane stabile per decenni.
    
- È percepito dagli utenti come invasivo solo perché “riguarda l’occhio”, ma il sistema opera interamente nel vicino IR, non pericoloso.
    
- I sistemi sono relativamente costosi e richiedono hardware sofisticato, ma forniscono **velocità e precisione altissime**.

#### Vantaggi

- Elevata unicità del pattern.
    
- Acquisizione _touchless_.
    
- Pattern ricco di tessiture, solchi, pieghe, anelli, criptae.
    
- Alta robustezza allo spoofing.
    

#### Svantaggi

- Difficoltà di acquisizione: l’occhio si muove, è piccolo (1 cm) e l’iride è parzialmente occlusa da ciglia e palpebre.
    
- L’iride si deforma elasticamente con la variazione della pupilla → occorre compensare la deformazione.
    
- Riflessioni della cornea.
    
- Pigmentazioni e macchie possono comparire con l’età.
    

---

### Anatomia essenziale dell’occhio utile alla biometria

![](imgs/Pasted%20image%2020260218154716.png)

![](imgs/Pasted%20image%2020260218154740.png)

L’iride si trova tra **cornea** e **cristallino** ed è costituita da fibre muscolari che controllano la dimensione della pupilla. È divisa in:

- **zona pupillare** (interna)
    
- **zona ciliare** (esterna)
    
- separate dal **collaretto**, una linea frastagliata riconoscibile.

![](imgs/Pasted%20image%2020260218154832.png)

Struttura multilayer:

- **Epitelio pigmentato interno**, molto scuro → determina la tonalità generale.
    
- **Stroma**, strato vascolarizzato: la sua pigmentazione e la diffusione della luce determinano i colori (blu, verde, nocciola, ecc.).
    

L’iride misura mediamente **12 mm di diametro** e **0.3 mm di spessore**, e la pupilla può variare la sua dimensione dal 10% all’80% del raggio irideo.

---

### Unicità del pattern

Come per le impronte digitali, **non esistono due iridi uguali**.  

![](imgs/Pasted%20image%2020260218154911.png)

Le differenze derivano da processi casuali nella formazione embrionale: pieghe, solchi radiali, criptae, anelli di contrazione, e micro-pattern che rendono ogni iride unica, anche nei gemelli omozigoti.

Le caratteristiche leggibili includono:

- trame locali
    
- pattern radiali e circolari
    
- pieghe e anelli
    
- criptae
    
- striature e linee di tensione
    

---

### Illuminazione per l’acquisizione

La **luce visibile** non è ottimale, perché la melanina la assorbe.  
Le caratteristiche utili emergono invece con la **luce IR (700–900 nm)**:

- la melanina riflette molto meglio l’IR;
    
- i dettagli della tessitura emergono con maggior contrasto;
    
- la cornea diventa più trasparente all’elaborazione.

![](imgs/Pasted%20image%2020260218154940.png)

Di conseguenza, i sistemi biometrici per iride lavorano con illuminatori IR e camere monocromatiche sensibili al NIR.

---

### Sensori per l’acquisizione dell’iride

L’acquisizione corretta richiede:

- almeno **70 pixel** sul raggio dell’iride (meglio 100–140);
    
- **CCD monocromatici** da almeno 640×480 capaci nel vicino IR;
    
- ottiche variabili (zoom) o doppia camera;
    
- illuminazione IR non percepita dall’occhio.
    

Commercialmente esistono:

![](imgs/Pasted%20image%2020260218155019.png)

- dispositivi da muro o da palmo (IrisGuard, Panasonic BM-ET100/200)
    
- scanner professionali (Aratek, Hikvision MinMoe)
    
- sistemi embedded come EyeLock
    
- scanner per smartphone (es. Galaxy Note 7, S8–S9)

![](imgs/Pasted%20image%2020260218155139.png)

![](imgs/Pasted%20image%2020260218155201.png)

![](imgs/Pasted%20image%2020260218155053.png)

![](imgs/Pasted%20image%2020260218155108.png)

---

### Applicazioni nel mondo reale

L’impiego più celebre: **frontiere degli Emirati Arabi**, con:

![](imgs/Pasted%20image%2020260218155223.png)

- 17 aeroporti e porti integrati
    
- oltre **3.8 milioni di match al giorno**
    
- identificazioni in frazioni di secondo
    

Applicazioni in altri Paesi:

- Schiphol (Paesi Bassi)
    
- Heathrow (UK)
    
- Francoforte (Germania)
    
- sistemi ONU in Afghanistan (350.000 profughi registrati)
    

Altre applicazioni citate:

- login PC
    
- controlli di frontiera
    
- bancomat e ATM contactless
    
- anti-terrorismo e sicurezza aeroportuale
    
- autenticazione finanziaria
    
- criptografia biometrica (“biometric-key cryptography”)
    

---

### Esempio di integrazione

Sistema Safran Morpho IAD: riconoscimento **volto + iride** in meno di 1 secondo, utilizzato in tornelli di sicurezza.  

![](imgs/Pasted%20image%2020260218155257.png)

ATM sperimentali Diebold–Citigroup: prelievo in 15 secondi senza PIN.

![](imgs/Pasted%20image%2020260218155316.png)

---

## Parte B – Rappresentazione dell’iride e creazione dell’IrisCode

Un moderno sistema di riconoscimento si compone di:

- **Acquisition module**
    
- **Feature extraction**
    
- **Quality check**
    
- **Iriscode generation**
    
- **Matching module**
    
- **Database**

![](imgs/Pasted%20image%2020260218155350.png)

L’IrisCode è la rappresentazione più diffusa: una stringa binaria compatta e ad alta entropia.

![](imgs/Pasted%20image%2020260218155415.png)

---

### Dal frame dell’occhio al template (IrisCode)

La pipeline secondo Daugman:

1. **Localizzazione dell’occhio** e messa a fuoco sufficiente.
    
2. **Segmentazione:**
    
    - trovare centro e raggio della pupilla;
        
    - trovare il bordo esterno dell’iride;
        
    - rimuovere zone occluse (ciglia, palpebre, riflessi);
        
    - rifiutare la cattura se l’iride utile < 50%.
        
3. **Normalizzazione (“rubber sheet model”)**:
    
    - si passa da coordinate cartesiane (x, y) a polari (ρ, θ);
        
    - l’iride viene “spianata” in un rettangolo;
        
    - ogni pixel è ottenuto da media/interpolazione del settore sorgente;
        
    - tipicamente: 180 passi angolari × 73 corone radiali.
        
4. **Feature extraction tramite 2D Gabor wavelet**
    
5. **Quantizzazione delle fasi** → IrisCode
    
6. **Mask** per escludere parti corrotte (riflessi, occlusioni, ciglia)

---

### Segmentazione dei confini: raggi e centri

La fase più critica è stimare:

- centro pupilla
    
- raggio pupilla
    
- raggio iride
    
- forma reale (la pupilla non è perfettamente circolare)
    

Si usano operatori basati su gradienti e cercatori di contorni (integrazione circolare, edge detector, ottimizzazioni locali).

---

## Rimozione di ciglia e palpebre

Solo una porzione dell’iride è realmente utile.  
Occorre segmentare:

- ciglia (pattern sottili e scuri)
    
- palpebre (bordo curvo sopravanzante)
    
- riflessi speculari
    
- zone sfocate
    
- regioni di basso contrasto

![](imgs/Pasted%20image%2020260218155518.png)

Le regioni da escludere vengono marcate nella **mask**, che viene usata nel matching.

---

## Rubber Sheet Model

L’iride viene trasformata in una matrice rettangolare in coordinate:

- **ρ** (raggio)
    
- **θ** (angolo)
    

L’unwrapping compensa le variazioni elastiche della pupilla.  

![](imgs/Pasted%20image%2020260218155608.png)

Il modello è detto “rubber sheet” perché simula “aprire” l’iride come un palloncino.

Due esempi di sampling:

- 180 passi × 73 corone
    
- 128 passi × 8 corone (più compatto)

![](imgs/Pasted%20image%2020260218155718.png)

---

### Calcolo dell’IrisCode (Gabor Demodulation)

Una volta ottenuta l’iride linearizzata $I(\rho,\theta)$, si esegue la convoluzione con Gabor 2D:

- wavelet con parametri: frequenza ω, dimensioni α e β
    
- output: un numero complesso (phasor) per ogni posizione

![](imgs/Pasted%20image%2020260218155822.png)

Si codifica **solo la fase**:

- bit 1 = segno della parte reale positivo
    
- bit 0 = segno della parte reale negativo
    
- idem per la parte immaginaria → 2 bit per ogni posizione
    

Come dice Daugman:

> _L’intero dettaglio dell’iride è codificato in soli 256 byte, rappresentando la fase delle risposte Gabor in vari punti e scale._

![](imgs/Pasted%20image%2020260218155918.png)

![](imgs/Pasted%20image%2020260218160000.png)

---

### Proprietà dell’IrisCode

#### Invarianze

La rappresentazione è invariante rispetto a:

- dimensione dell’iride
    
- zoom della camera
    
- dilatazione della pupilla
    
- contrasto e livello medio dell’immagine
    
- illuminazione
    

#### Dimensione del template

- IrisCode ≈ **256 byte**
    
- Mask ≈ **256 byte**
    
- totale intorno ai 512 byte
    

#### Entropia

La probabilità che un bit dell’IrisCode sia 1 è **50%**  
→ massima entropia → minima correlazione tra iridi diverse.

---

### Altri metodi di rappresentazione

Oltre all’IrisCode di Daugman esistono:

- wavelet features (Lim, Lee, Byeon, Kim – 2001)
    
- rappresentazioni basate su multi-resolution analysis
    
- approcci moderni con **Deep Learning**:
    
    - segmentazione
        
    - template embedding
        
    - matching end-to-end (es. DeepIris su ResNet50)

![](imgs/Pasted%20image%2020260218160037.png)

Gli approcci DL sono molto performanti ma **poco adatti** a sistemi embedded o database enormi per via del costo computazionale.

![](imgs/Pasted%20image%2020260218160112.png)

---

### In sintesi

- L’iride è un tratto ricchissimo, stabile e unico.
    
- L’acquisizione richiede sensori IR specializzati e risoluzione elevata.
    
- La pipeline classica: segmentazione → normalizzazione → wavelet → IrisCode.
    
- L’IrisCode è una codifica binaria compatta, invariante, ad alta entropia.
    
- Esistono metodi alternativi, inclusi modelli Deep Learning.