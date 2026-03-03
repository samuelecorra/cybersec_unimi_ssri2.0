# **Lezione 2: Tratti biometrici e evoluzione della biometria**

---

### **0. Panoramica della lezione**

Questa lezione ha due obiettivi:

1. **Parte A – Tratti biometrici e caratteristiche**
    
    - Passiamo in rassegna i tratti biometrici più usati (impronta, volto, mano, iride, firma, voce).
        
    - Per ciascuno vediamo:
        
        - come è fatto il tratto,
            
        - quali sensori si usano,
            
        - come avviene il riconoscimento (feature e template),
            
        - punti di forza e debolezza.
            
2. **Parte B – Evoluzione storica della biometria**
    
    - Dall’uso istintivo di “segnali biometrici” negli animali,
        
    - alle impronte su argilla in Mesopotamia e Cina,
        
    - al sistema antropometrico di Bertillon,
        
    - fino al dominio delle **impronte digitali** e alla nascita dei sistemi automatici moderni.
        

Tieni sempre a mente il flusso generale: **tratto → campione → caratteristiche → template → matching** (ripreso dalla Lezione 1) e applicato, di volta in volta, ai diversi tratti.

---

## **Parte A – Tratti biometrici: caratteristiche**

### **1. I tratti biometrici più diffusi**

I tratti maggiormente usati nei sistemi biometrici reali sono:

- **Impronte digitali**
    
- **Volto** (face, 2D e 3D)
    
- **Iride**
    
- **Mano / vene della mano**
    
- **Voce**
    
- **Firma**
    
- **Sistemi multimodali** (combinazioni di più tratti o più sensori)
    

In questa lezione iniziamo ad analizzare **a grandi linee** come funzionano questi tratti. Più avanti nel corso alcuni di essi (impronta, volto, iride) verranno approfonditi in modo dettagliato.

---

### **2. Impronte digitali**

#### **2.1 Cosa sono**

![](imgs/Pasted%20image%2020260209155335.png)

- Sono il **tratto biometrico più antico e diffuso** al mondo.
    
- Un’impronta digitale è un **pattern di creste e valli** che si forma in modo sostanzialmente casuale già nello stadio embrionale.
    
- Le impronte si trovano:
    
    - sulla punta delle dita,
        
    - nei palmi della mano,
        
    - sulla pianta dei piedi.
        
- A oggi si considera che siano **uniche**: due persone diverse non hanno la stessa configurazione di creste/minuzie.
    
- Il pattern, a parte traumi importanti, **non cambia nel tempo**: la stessa persona mantiene le stesse impronte nel corso della vita.

![](imgs/Pasted%20image%2020260209155045.png)

Nel **diagramma** si vedono:

- due impronte della **stessa persona**, molto simili nelle creste;
    
- due impronte di **persone diverse**, chiaramente differenti nel reticolo di creste.
    

---

#### **2.2 Sensori per impronte**

I sensori più usati sono:

- **Termici**
    
- **A ultrasuoni**
    
- **Capacitivi** (tipici degli smartphone)
    
- **Ottici** (scanner ottici dedicati)
    
- **Scanner tradizionali** (tipo scanner da tavolo, per acquisizioni forensi)

![](imgs/Pasted%20image%2020260209155402.png)

**Problemi ingegneristici principali** (pagina 5):

- Far funzionare il sistema con **condizioni della pelle diverse**:
    
    - dita secche, umide, sudate, sporche;
        
    - tagli, abrasioni, calli.
        
- Gestire **overlap parziali**:
    
    - l’utente appoggia il dito “male”, solo metà area è coperta → il sistema deve comunque riconoscere.
        
- Far funzionare il sistema con **sensori diversi**:
    
    - registrazione su uno scanner ottico, verifica su un sensore capacitivo di uno smartphone, ecc.

![](imgs/Pasted%20image%2020260209155415.png)

---

#### **2.3 Approcci di riconoscimento**

Nel **diagramma** sono mostrati tre grandi approcci:

![](imgs/Pasted%20image%2020260209155506.png)

1. **Correlation-based (Pixel-by-pixel)**
    
    - Si confrontano direttamente i **pixel** di due immagini di impronta.
        
    - È come sovrapporre le due immagini e vedere quanto “coincidono”.
        
    - Sensibile a:
        
        - rotazioni,
            
        - traslazioni,
            
        - deformazioni locali.
            
2. **Ridge feature-based (“Solo i ridge”)**
    
    - Si analizzano le **creste** come entità, studiandone:
        
        - orientamento,
            
        - frequenza,
            
        - continuità.
            
    - Ci si astrae un po’ dal livello di pixel, si ragiona più “a livello di texture”.
        
3. **Minutia-based**
    
    - È l’approccio **più usato in pratica**.
        
    - Le **minuzie** sono punti caratteristici:
        
        - terminazioni di cresta,
            
        - biforcazioni, ecc.
            
    - Il template diventa una **lista di minuzie**:
        
        - posizione (x,y),
            
        - direzione,
            
        - tipo di minuzia.
            
    - Il matching confronta **le configurazioni di minuzie** tra due impronte.
        

---

### **3. Volto (face)**

#### **3.1 Perché è importante**

![](imgs/Pasted%20image%2020260209155547.png)

- È uno dei tratti **meno intrusivi**:
    
    - non devi toccare nulla, basta una telecamera.
        
- È il tratto che **usiamo naturalmente** per riconoscerci tra persone.
    
- È usato in **tantissime applicazioni**:
    
    - fotosegnaletica (“mug shot”),
        
    - videosorveglianza,
        
    - sblocco smartphone,
        
    - sistemi ABC, social network, ecc.
        

Nel **collage di immagini** si vedono esempi di volti in contesti forensi e una rappresentazione 3D del volto.

---

#### **3.2 Sensori e difficoltà**

**Sensori tipici** (pagina 8):

- Telecamere (CCTV),
    
- Macchine fotografiche digitali,
    
- Webcam,
    
- Fotocamere di smartphone e cellulari,
    
- Scanner 3D per il volto.
    

**Problemi da affrontare:**

- **Invecchiamento** del volto,
    
- **Cambiamenti di luce** e di sfondo,
    
- **Espressioni** diverse (sorriso, serietà, ecc.),
    
- **Variazioni di posa** (testa ruotata, inclinata),
    
- Occhiali, barba, trucco, mascherine, ecc.
    

Questi fattori rendono il riconoscimento facciale **molto più variabile** rispetto a impronte e iride.

---

#### **3.3 Approcci di riconoscimento del volto**

Due macro-approcci (pagina 9):

![](imgs/Pasted%20image%2020260209155726.png)

1. **Approcci per trasformazione (subspace, es. Eigenfaces)**
    
    - Si costruisce una **“base di immagini”** (ad es. tramite PCA).
        
    - Qualsiasi volto viene rappresentato come **somma pesata** di queste immagini base (eigenfaces).
        
    - In pratica: si proietta l’immagine del volto in uno **spazio di dimensione ridotta** e si confrontano i vettori di proiezione.
        
2. **Approcci per attributi (geometrici e locali)**
    
    - Si localizzano punti caratteristici del volto:
        
        - occhi, naso, bocca, mento, ecc.
            
    - Si misurano **distanze e angoli**:
        
        - distanza fra gli occhi,
            
        - lunghezza del naso,
            
        - larghezza della bocca, ecc.
            
    - Il template è un vettore di **misure geometriche**.
        

Nei sistemi moderni subentrano anche:

- descrittori locali (LBP, SIFT-like),
    
- reti neurali profonde (deep learning, CNN),  
    ma il concetto base rimane: trasformare il volto in un **insieme di numeri** stabili e discriminativi.
    

---

### **4. Mano e vene della mano**

#### **4.1 Caratteristiche della mano**

- Tratto **ben accettato** dagli utenti:
    
    - percepito come **poco invasivo**.
        
- Offre un **discreto livello di accuratezza**.
    
- Estremamente adatto a sistemi **multimodali**:
    
    - si può studiare contemporaneamente:
        
        - **forma** della mano,
            
        - **geometria delle dita**,
            
        - **texture della pelle**,
            
        - **pattern delle vene** (con infrarosso),
            
        - eventuale **temperatura** (per liveness).
            

Tipicamente si lavora su tre viste (pagina 10):

- **Palmare** (palmo),
    
- **Laterale**,
    
- **Dorsale** (dorso della mano).

---

#### **4.2 Sensori e algoritmi**

**Sensori** (pagina 11):

- Scanner a bassa/moderata risoluzione (< 180 dpi),
    
- Telecamere CCD:
    
    - in luce visibile,
        
    - in infrarosso (IR).
        

**Algoritmi tipici (pagina 11):**

- **Rilevamento dei contorni** della mano,
    
- **Allineamento** (normalizzazione della posizione) per poter confrontare mani diverse in condizioni coerenti,
    
- **Analisi termica / IR**:
    
    - per controllare la **liveness** (mano viva vs mano finta),
        
    - per misurare e usare il **pattern delle vene** come tratto aggiuntivo.

![](imgs/Pasted%20image%2020260209155837.png)

---

### **4.3 Approcci di riconoscimento**

Nel **diagramma di pagina 12** compaiono tre approcci:

1. **(a) Misura delle lunghezze**
    
    - Si misurano lunghezze e larghezze di dita, palmo, ecc.
        
    - Il template è un vettore di **misure antropometriche** della mano.
        
2. **(b) Confronto delle immagini delle parti (Eigenfinger)**
    
    - La mano viene suddivisa in **regioni** (zone delle dita, palmo).
        
    - Si costruiscono descrizioni tipo “eigenfinger” (analogo alle eigenfaces).
        
    - Le immagini delle parti vengono confrontate con modelli base.
        
3. **(c) Studio delle linee**
    
    - Si analizzano **linee e pieghe** del palmo,
        
    - pattern di rughe, pieghe principali, ecc.

![](imgs/Pasted%20image%2020260209155854.png)

---

### **5. Iride**

#### **5.1 Perché è così forte come tratto**

- È considerata uno dei tratti biometrici **più accurati**.
    
- È però meno gradita agli utenti, perché percepita come **invasiva**:
    
    - richiede che una telecamera si concentri da vicino sull’occhio.
        
- L’iride presenta:
    
    - **moltissime caratteristiche locali** (trama complessa di anelli, macchie, cripto),
        
    - **alta stabilità nel tempo**:
        
        - morfologicamente stabili già dall’ottavo mese di vita;
            
        - si considerano **biometricamente utilizzabili** almeno dal 2° anno di età.
            

Lo svantaggio: i sistemi sono **complessi e costosi**, ma risultano anche **molto difficili da frodare**.

---

#### **5.2 Sensori e pre-processing**

**Sensori** (pagina 14):

- Telecamere CCD ad **alta definizione**, fino a migliaia di euro,
    
- Ottiche speciali,
    
- Operatività sia in:
    
    - visibile,
        
    - infrarosso (IR), utile per ridurre i riflessi e vedere meglio la trama dell’iride.
        

**Algoritmi fondamentali:**

- **Localizzare l’occhio nel volto**,
    
- Acquisire **più frame** e confrontarli per controllare la **liveness** (es. piccoli movimenti, riflessi naturali),
    
- **Selezionare la sola regione di iride**, escludendo:
    
    - sclera,
        
    - pupilla,
        
    - palpebre e ciglia,
        
- **Rimuovere riflessi** e occlusioni,
    
- **Compensare le deformazioni**:
    
    - l’iride è un tessuto **elastico**, si dilata e restringe con l’illuminazione → l’algoritmo deve tenerne conto.

![](imgs/Pasted%20image%2020260209160006.png)

---

#### **5.3 IrisCode e matching**

Nel **diagramma di pagina 15** è schematizzato il processo:

![](imgs/Pasted%20image%2020260209160025.png)

1. **Segmentazione**:
    
    - individuazione di:
        
        - pupilla,
            
        - contorno dell’iride,
            
        - palpebre, ciglia, riflessi.
            
2. **Linearizzazione (unwrapping)**:
    
    - l’anello dell’iride (in coordinate polari) viene “srotolato” in un **rettangolo** (coordinate cartesiane).
        
    - È una trasformazione che rende più semplici le elaborazioni successive.
        
3. **Creazione dell’**`IrisCode`**:**
    
    - si estraggono caratteristiche (ad es. tramite filtri di Gabor),
        
    - si codificano in una **stringa binaria compatta** (il template).
        
4. **Matching**:
    
    - confronto tra IrisCode,
        
    - misurando il numero di **bit diversi** (distanza di Hamming),
        
    - ottenendo un **matching score** da confrontare con una soglia.
        

---

### **6. Firma**

#### **6.1 Firma statica e dinamica**

- È un metodo **molto diffuso e semplice da spiegare** agli utenti.
    
- Ha però **bassa accuratezza** (molto variabile nel tempo; facile imitare una firma semplice).
    
- I sensori hanno **costo moderato** (tablet grafici, schermi sensibili alla pressione).
    

Distinzione importante:

- **Firma statica**:
    
    - immagine finale del tratto grafico (come su carta).
        
- **Firma dinamica**:
    
    - si registra l’**intero movimento** della mano durante la firma.

![](imgs/Pasted%20image%2020260209160119.png)

---

### **6.2 Riconoscimento con firma dinamica**

Nel grafico di pagina 17 il sistema usa:

- andamento nel tempo delle **coordinate x, y** della penna,
    
- andamento della **pressione** esercitata,
    
- **azimut** (angolo orizzontale) e **inclinazione** dello stilo.

![](imgs/Pasted%20image%2020260209160132.png)

Il template è quindi una **traccia temporale multidimensionale**.  
Il matching confronta:

- forma globale del gesto,
    
- tempi relativi,
    
- profilo della pressione, ecc.

![](imgs/Pasted%20image%2020260209160146.png)

Questo rende la firma dinamica **più difficile da falsificare** rispetto alla sola immagine statica, ma rimane in generale meno accurata di altri tratti come impronta o iride.

---

### **7. Voce**

#### **7.1 Caratteristiche**

- La **voce** è un tratto **ben accettato** dagli utenti.
    
- I sistemi vocali, però, hanno:
    
    - **bassa accuratezza** (molto sensibili a rumore, microfono, stato di salute, emozioni),
        
    - **costo moderato** del sensore (basta un microfono),
        
    - **template di grandi dimensioni** (spettrogrammi, vettori di parametri vocali),
        
    - relativa facilità di **frode**:
        
        - registrazioni,
            
        - imitazioni,
            
        - sistemi di voice spoofing.
            

![](imgs/Pasted%20image%2020260209160239.png)

Nel grafico in pagina 18 si vede:

- un **segnale audio** con zone di parlato e non-parlato,

![](imgs/Pasted%20image%2020260209160248.png)

- un **spettrogramma** (distribuzione nel tempo-frequenza).

![](imgs/Pasted%20image%2020260209160258.png)

Il sistema estrae parametri (es. MFCC, formanti, pitch) che compongono il **template vocale**.

---

### **8. Sistemi multimodali e soft biometrics**

#### **8.1 Sistemi multimodali**

Definizione (pagina 19):

- Si parla di **sistemi multimodali** quando:
    
    - si uniscono **più tecnologie biometriche** in un unico sistema.
        

Attenzione:

> Non significa solo “usare più tratti” (es. volto + impronta),  
> ma anche combinare **più sensori**, **più algoritmi** o **più istanze** dello stesso tratto.

**Obiettivi principali:**

- **Aumentare l’accuratezza**,
    
- **Aumentare la robustezza alle frodi**:
    
    - un attacco deve ingannare più moduli contemporaneamente.
        

Esempi:

- impronta + volto,
    
- iride + volto 3D,
    
- mano geometria + vene.

![](imgs/Pasted%20image%2020260209160329.png)

---

#### **8.2 Soft biometrics**

Alcune caratteristiche **non rispettano le 7 qualità** classiche di un buon tratto biometrico (universalità, unicità, permanenza, ecc.), ma sono comunque utili se combinate con altri tratti.

Queste vengono chiamate **soft biometric traits**, e includono:

- **Genere**,
    
- **Colore della pelle**,
    
- **Colore dei capelli**,
    
- **Colore degli occhi**,
    
- **Peso, altezza**,
    
- Stile di abbigliamento, ecc.

Sono utili per:

- restringere il **gruppo di candidati** in un’identificazione,
    
- migliorare la **classe di probabilità** (es. “maschio, alto, capelli scuri, occhiali”).

Nel diagramma di pagina 20 è rappresentato un ambiente con telecamere che stimano altezza, colore, genere, ecc., per arricchire il riconoscimento.

![](imgs/Pasted%20image%2020260209160350.png)

---

#### **8.3 Sintesi Parte A**

In sintesi (pagina 21):

- Ogni tratto biometrico viene analizzato fino a estrarne un **insieme di caratteristiche**.
    
- Queste caratteristiche, una volta codificate, formano il **template**.
    
- Esempi di template:
    
    - `IrisCode` per l’iride,
        
    - lunghezze delle dita di una mano,
        
    - coordinate nel tempo di una firma,
        
    - posizioni delle minuzie in un’impronta.
        

Abbiamo visto le peculiarità di:

- impronte,
    
- volto,
    
- mano,
    
- iride,
    
- firma,
    
- voce,
    
- sistemi multimodali,
    
- soft biometrics.
    

---

## **Parte B – Evoluzione della biometria**

### **9. Biometria “naturale” negli animali**

Gli animali, da milioni di anni, si riconoscono con criteri di fatto **biometrici** (pagina 23):

- **Livrea, aspetto, forme** (pattern del piumaggio, pelliccia),
    
- **Verso** (richiami vocali specifici),
    
- **Odore**,
    
- **Comportamento** (modo di muoversi, atteggiamenti).

![](imgs/Pasted%20image%2020260209160427.png)

È una biometria **istintiva**, non automatizzata, ma concettualmente simile: riconoscere “chi è chi” tramite tratti stabili.

---

### **10. Prime tracce storiche (Mesopotamia e Cina)**

#### **10.1 Mesopotamia (2500–1700 a.C.)**

- Gli **Assiri** (circa 2500 a.C.) usavano **sigilli di argilla** con impressa anche l’**impronta digitale del funzionario** per firmare documenti legali.
    
- Sotto il regno di **Hammurabi** (1792–1750 a.C.):
    
    - le impronte dei **criminali** erano impresse in tavolette di argilla a fini identificativi,
        
    - nelle iscrizioni cuneiformi gli autori aggiungevano una loro impronta digitale per evitare contraffazioni (una primitiva forma di **autenticazione biometrica**).
        

---

#### **10.2 Cina (300 a.C., 400 d.C., 1839 d.C.)**

- Già nel **300 a.C.** si usavano sigilli in argilla con impronde digitali per:
    
    - firmare documenti legali,
        
    - registrare criminali.
        
- Durante la **dinastia Jin** (220–420 d.C.):
    
    - si passa a usare **seta e inchiostro** per firmare documenti con le impronte.
        
- Nel documento mostrato a pagina 25 si vede un atto di vendita di un terreno del **1839**, firmato con impronte digitali.
    

Quindi: l’idea di usare l’impronta come firma è **molto più antica** dei sistemi informatici.

---

### **11. Il sistema di Bertillon (1882) – antropometria**

#### **11.1 Idea di fondo**

**Alphonse Bertillon** (1853–1914) propose un sistema di identificazione dei criminali basato su misure **antropometriche** (pagina 26):

- lunghezza del braccio e delle dita,
    
- altezza e larghezza della testa,
    
- lunghezza dei piedi, ecc.
    

Due ipotesi chiave:

1. Lo **scheletro umano** non si modifica dopo i 20 anni,
    
2. Ogni scheletro è **diverso** → ergo, si può risalire a un individuo dalle sue misure.

![](imgs/Pasted%20image%2020260209160620.png)

---

#### **11.2 Schede, strumenti e funzionamento**

- Ogni persona veniva registrata tramite una **scheda** con:
    
    - fotografie (mug shot),
        
    - misure antropometriche,
        
    - descrizione fisica.
        
- Questo è l’equivalente dell’**enrollment** moderno.
    

Le ricerche (matching) erano:

- completamente **manuali**,
    
- basate su un sistema di classificazione in molte **categorie** (246 classi).
    

Gli strumenti di acquisizione (pagina 28) sono:

- righelli, compassi, misuratori di altezza, ecc.
    
- Il processo richiedeva circa **20 minuti per persona**.

![](imgs/Pasted%20image%2020260209160634.png)

---

#### **11.3 Successi iniziali**

Il sistema ebbe un notevole successo all’inizio (pagina 29):

- Si diffuse in molte parti del mondo:
    
    - Francia (dal 1882),
        
    - USA (dal 1887),
        
    - altri paesi.
        
- Nel 1893, in Francia, l’archivio del Servizio d’Identità giudiziaria contava **500.000 schede**.
    
- Negli USA, in due anni, si arrivò a **24.000 schede**.
    
- In due anni il Bureau of Identification scoprì che **131 criminali “incensurati”** avevano in realtà **precedenti**:
    
    - prova che il sistema funzionava **operativamente**.
        

---

#### **11.4 Limiti e fallimento**

Ma il sistema fu infine abbandonato per diversi motivi (pagina 29):

- **Troppe categorie** (246) → ricerche lunghe, fino a ore, per archivi di 50.000 schede,
    
- **Misure non stabili** nei criminali giovani:
    
    - l’ipotesi di “scheletro immutabile” non valeva prima dei 20 anni,
        
- **Arrivo delle impronte digitali** come alternativa più pratica,
    
- Il celebre **caso WEST** (1903).
    

---

### **12. Il caso West (1903)**

- Nel 1903, **Will West** viene imprigionato e schedato con il metodo Bertillon.
    
- Si scopre una scheda preesistente con **misure e foto praticamente identiche**.
    
- Will West dichiara:
    
    - di non essere mai stato schedato,
        
    - che la persona nella scheda non è lui.
        

Dopo indagini emerge che:

- esiste **William West**, suo fratello (o comunque individuo diverso) incarcerato nello stesso penitenziario dal 1901,
    
- le loro **misure antropometriche** sono quasi indistinguibili (vedi tabella di pagina 30),
    
- ma le loro **impronte digitali** risultano **differenti**.

![](imgs/Pasted%20image%2020260209160701.png)

Conclusione:

> L’ipotesi di **unicità del tratto antropometrico** cade.  
> Le **impronte digitali** risultano invece un tratto molto più discriminativo.

Uno studio dell’FBI (pagina 31) sancisce definitivamente il fallimento del metodo Bertillon, che viene **dismesso globalmente**.

![](imgs/Pasted%20image%2020260209160735.png)

---

### **13. Dalla scoperta delle impronte alla classificazione sistematica**

#### **13.1 Prime osservazioni sulle impronte**

- **Marcello Malpighi** (1628–1694), medico e anatomista italiano, già nel **1686** descrive:
    
    - gli strati del derma,
        
    - le figure sui polpastrelli.
        
- Nel **1858**, **Sir William Herschel** in Bengala:
    
    - usa le impronte del palmo per evitare **doppie riscossioni** di pensioni,
        
    - ottiene uno dei primi **documenti biometrici** ufficiali con impronta (vedi pagina 32).

![](imgs/Pasted%20image%2020260209160908.png)

---

#### **13.2 Francis Galton e la probabilità di coincidenza**

- Nel **1880**, **Francis Galton** (cugino di Darwin) inizia studi sistematici sulle impronte.
    
- Nel **1892** pubblica _Finger Prints_, dove:
    
    - calcola che la probabilità che **due individui** abbiano le **stesse impronte** è circa **1 su 64 miliardi**,
        
    - identifica 4 grandi classi di pattern:
        
        - arch (A delta),
            
        - monodelta,
            
        - bidelta,
            
        - composita.

![](imgs/Pasted%20image%2020260209160921.png)

La **classificazione** è cruciale per gestire archivi di grandi dimensioni (prima dell’informatica).

---

#### **13.3 Sir Edward Henry e la standardizzazione (1900)**

- Nel **1900**, **Sir Edward Henry** pubblica _Classification and Uses of Fingerprints_.
    
- Il suo metodo di classificazione diventa **lo standard** in Gran Bretagna fino all’avvento dei computer.
    
- Dal 1900 in poi, progressivamente, **tutto il mondo** inizia a usare le impronte digitali per l’identificazione.

![](imgs/Pasted%20image%2020260209160935.png)

In Italia:

- Congresso Penitenziario a Roma nel 1886 (ospite d’onore Bertillon),
    
- Nel 1892, a Napoli, viene creato un gabinetto antropometrico per confronti fotosegnaletici, e successivamente si integrano le impronte.
    

---

#### **13.4 Schede e classificazione negli USA**

- Dal **1903**, il **New York State Prison Department** inizia a schedare i detenuti con impronte.
    
- Nella **scheda di pagina 35** si vede il sistema di classificazione progettato da **Edward Parke**.
    
- Ogni detenuto ha:
    
    - fotografie,
        
    - set di impronte,
        
    - codice di classificazione.

![](imgs/Pasted%20image%2020260209160947.png)

---

Di seguito la timeline:

![](imgs/Pasted%20image%2020260209161014.png)

![](imgs/Pasted%20image%2020260209161026.png)

---

### **14. Espansione nel XX secolo**

#### **14.1 Gran Bretagna 1900–1960**

- Nel **1901**, Sir Edward Henry fonda il **Fingerprint Bureau** di Scotland Yard.
    
- La fama del bureau cresce a livello mondiale.
    
- Timeline:
    
    - dal 1913 i detective usano massicciamente le impronte nelle investigazioni,
        
    - dal 1924 le impronte iniziano a essere **telegrafate** in tutto il mondo,
        
    - nel 1929, grazie a un sistema di classificazione più efficiente, si identificano 360 criminali,
        
    - nel 1963 si iniziano a usare **computer** (ma il confronto è ancora manuale).

![](imgs/Pasted%20image%2020260209161059.png)

---

#### **14.2 Gran Bretagna 1960–2000**

- Nel **1984**, Scotland Yard adotta il primo sistema di **riconoscimento automatico delle impronte**.
    
- Nel **1988**:
    
    - il database contiene 4,5 milioni di **set di impronte**.
        
- Nel **2000**:
    
    - vengono effettuate circa **10.000 identificazioni** da impronte trovate sulla scena del crimine,
        
    - il database cresce di circa **120.000 nuovi set all’anno**.


---

#### **14.3 USA 1900–2000**

- **1905**: l’esercito USA inizia a usare impronte per schedare gli operativi.
    
- **1924**: nasce la **Identification Division dell’FBI**, centrata sulle impronte.
    
- **1946**: l’FBI ha **46 milioni** di set di impronte.
    
- **1971**: si arriva a **200 milioni** di set; l’FBI inizia a usare il sistema computerizzato **AFIS** (Automated Fingerprint Identification System).
    
- **1999**: l’FBI abbandona definitivamente la carta:
    
    - l’intero sistema diventa **digitale** (scan, memorizzazione, confronto).

![](imgs/Pasted%20image%2020260209161117.png)

---

#### **14.4 Mondo 1960–2000**

- A oggi sono registrati **dati biometrici di centinaia di milioni di individui** nel mondo.
    
- In circa **4 decenni** si è passati:
    
    - da **un solo metodo** (impronte)
        
    - a sistemi automatici per **circa 10 tratti biometrici** diversi.
        

Esempi di nuove modalità e anni di nascita:

- **Geometria della mano** – 1985, David Sidlauskas,
    
- **Iride** – 1994, John Daugman,
    
- **Vene della mano** – 1998, British Technology Group Ltd.

![](imgs/Pasted%20image%2020260209161150.png)

---

### **15. Biometria dal 2000 a oggi**

La slide finale (pagina 42) riassume i trend post-2000:

- **Grande accelerazione**:
    
    - nelle **ricerche**,
        
    - nel **mercato**.
        
- **Miniaturizzazione** dei dispositivi:
    
    - sensori biometrici integrati in smartphone, laptop, wearable, card, ecc.
        
- Sviluppo di:
    
    - **biometria continua**:
        
        - riconoscimento “in background” durante l’uso (comportamento di digitazione, camminata, pattern di utilizzo del device),
            
    - **biometria senza contatto**:
        
        - volto, iride, hand-vein contactless, ecc.
            
- Applicazioni oltre la sicurezza “classica”:
    
    - **ambient intelligence**,
        
    - **health & screening** (braccialetti, accelerometri, ECG),
        
    - **dispositivi mobili**.
        

Questi temi verranno approfonditi nel corso avanzato (**Complementi di biometria** / Tecniche e applicazioni biometriche).

---

### **16. Sintesi finale della Lezione 2**

In questa lezione abbiamo:

- analizzato i **principali tratti biometrici**:
    
    - impronte,
        
    - volto,
        
    - mano,
        
    - iride,
        
    - firma,
        
    - voce,
        
    - sistemi multimodali e soft biometrics;
        
- visto come da ogni tratto si ricavi:
    
    - un **campione**,
        
    - un insieme di **caratteristiche**,
        
    - un **template** (IrisCode, minuzie, vettori di misure, firme dinamiche…);
        
- ripercorso l’**evoluzione storica** della biometria:
    
    - dagli usi antichi di impronte su argilla,
        
    - al metodo antropometrico di Bertillon e al caso West,
        
    - alla progressiva affermazione delle **impronte digitali** e dei sistemi automatici,
        
    - fino alla diffusione moderna di molteplici tratti biometrici e all’esplosione dopo il 2000.
        

Questa lezione ti dà sia la **mappa pratica dei tratti** che useremo nel corso, sia il **contesto storico** che spiega perché oggi la biometria è ovunque – dal carcere di fine ’800 al tuo smartphone.