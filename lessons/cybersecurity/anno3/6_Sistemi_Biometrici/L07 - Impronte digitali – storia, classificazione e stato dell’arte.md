# **Lezione 7: Impronte digitali – storia, classificazione e stato dell’arte**

---

### **1. Come studieremo i tratti biometrici**

Per ogni tratto biometrico (qui: **impronte digitali**) seguiremo sempre lo stesso schema di analisi:

1. **Biologia del tratto, storia, privacy**
    
    - come nasce biologicamente il tratto,
        
    - come è stato usato storicamente,
        
    - che impatti ha su privacy e società.
        
2. **Sensore**
    
    - come viene acquisita l’informazione (tipi di sensori, risoluzione, contatto vs contactless).
        
3. **Feature / Template, unicità del tratto**
    
    - quali caratteristiche usiamo come feature,
        
    - come rappresentiamo il template,
        
    - in che senso il tratto è “unico”.
        
4. **Matching**
    
    - come confrontiamo due impronte (metrica, allineamento, deformazioni).
        
5. **Indicizzazione**
    
    - come organizziamo grandi database (AFIS/ABIS).
        
6. **Spoofing e anti-spoofing**
    
    - come si possono falsificare le impronte,
        
    - come i sistemi cercano di difendersi.
        

In questa lezione ci concentriamo soprattutto su **storia, biologia e classificazione + stato dell’arte AFIS**.

---

### **2. Excursus storico: dalle incisioni al primo uso “biometrico”**

Sulle _slide di pagina 3_ vedi una serie di immagini che mostrano che le impronte digitali compaiono **molto prima** dell’invenzione della biometria:

![](imgs/Pasted%20image%2020260215171845.png)

- **4000 a.C. – incisioni del neolitico**  
    Motivi che somigliano chiaramente a creste e vortici di impronte, incisi su pietra.
    
- **2000 a.C. – menhir dell’età del bronzo**  
    Ancora motivi “a cresta” incisi.
    
- **400 a.C. – impressione su lampada di terracotta palestinese**
    
- **300 a.C. – impressione su cera cinese**
    
- **intorno al 1800 – atto di vendita in Cina**  
    Documento legale firmato **con un’impronta digitale**: già qui si intuisce l’idea di usare il tratto come segno personale.
    
- **1858 – India (dominio britannico)**  
    Primo vero utilizzo **biometrico**: impronte su documenti per riscossione, controllo criminali e gestione amministrativa.
    

Quindi: l’**uso spontaneo** delle impronte nasce come “firma” fisica, molto prima della formalizzazione scientifica.

---

### **3. Diffusione moderna dei sistemi a impronte**

Sulle _slide di pagina 4_ trovi le tappe principali:

![](imgs/Pasted%20image%2020260215171930.png)

![](imgs/Pasted%20image%2020260215172027.png)

- **1858 – India (dominio UK)**  
    Uso sistematico per documenti e controllo criminali.
    
- **1880 – Regno Unito, lettera di H. Faulds su _Nature_**  
    Proposta esplicita di usare le impronte per identificare criminali:
    
    > “Nature-copy of the forever unchangeable finger furrows…”
    
- **1901 – UK**  
    Nasce il **Fingerprint Bureau** per la criminal identification.
    
- **1901 – USA**  
    Primo uso ufficiale delle impronte da parte della _New York City Civil Service Commission_.
    
- **1905 – USA / New Scotland Yard**  
    L’esercito inizia a usare le impronte per schedare il personale.
    
- **1930 – USA**  
    L’FBI installa il **National Fingerprint File**, enorme archivio centralizzato.
    
![](imgs/Pasted%20image%2020260215172108.png)

Messaggio chiave:  
già a inizio ’900 le impronte sono lo **standard** per l’identificazione criminale e amministrativa.

---

### **4. Cosa sono le impronte digitali (biologia del tratto)**

> “Creste e valli della pelle (dermatoglyphics) sui palmi e sulle dita di molti animali.”

Quindi:

- non è solo una “texture” della superficie;
    
- è una **struttura anatomica** con funzione meccanica (presa, attrito) e sensoriale.
    

#### **4.1 Stabilità nel tempo**

Le impronte sono:

- **stabili dall’ottavo mese di gestazione**  
    e rimangono tali per tutta la vita,
    
- a meno di:
    
    - abrasioni profonde,
        
    - malattie dermatologiche gravi,
        
    - incidenti (ustioni, cicatrici).
        

Sulla slide c’è un esempio di **koala**: anche lui ha impronte molto simili a quelle umane → il tratto non è solo nostro, ma i **pattern statistici** sì.

---

### **5. Crescita delle impronte e variabilità temporale**

Le _pagine 8–9_ mostrano come le impronte evolvono nei primi anni di vita.

- **Il dito cresce**, quindi:
    
    - le distanze tra le creste aumentano,
        
    - la geometria globale rimane (pattern, minuzie),
        
    - ma la metrica cambia.
        

Confronto tipico:

- impronta di un bambino a 2 mesi,
    
- impronta dello stesso dito 3 mesi dopo:
    

le **stesse minuzie** (terminazioni, biforcazioni) ci sono ancora, ma sono **traslate e “stretchate”**.

#### **5.1 Effetto sui sistemi biometrici**

Per sistemi **a contatto “metrici”** (che assumono righe in posizioni precise):

- la crescita induce **False Non-Match** (FNM):  
    la distanza tra minuzie non è più quella attesa.
    

Per sistemi **contactless** (che lavorano più in modo topologico):

- possono gestire meglio la crescita,
    
- ma richiedono sensori e algoritmi più sofisticati.
    

Il docente la definisce “un ottimo esempio di elevata variabilità temporale che poi rallenta”:  
nei primi anni le impronte cambiano molto, poi diventano sostanzialmente stabili.

---

### **6. Struttura delle friction ridges**

Sulla _pagina 10_ trovi la sezione “Friction ridges”:

Caratteristiche principali delle zone con impronte:

- **senza peli**;
    
- **moltissimi pori sudoripari**;
    
- **assenza di ghiandole sebacee**;
    
- **molte connessioni nervose** (altissima sensibilità tattile);
    
- **non si pigmenta** (non cambia colore con il sole come il resto della pelle).

![](imgs/Pasted%20image%2020260216120536.png)

Strutturalmente:

- sopra c’è l’**epidermide**,
    
- sotto il **derma**,
    
- le creste non sono solo a livello superficiale: hanno una “radice” nel derma → motivo della stabilità nel tempo.
    

---

### **7. Sistema di classificazione moderno: Arch, Loop, Whorl**

Sulla _pagina 11_ viene presentato lo schema base, usato tuttora:

![](imgs/Pasted%20image%2020260216120555.png)

- **Arch** (Arcate) – Plain Arch, Tented Arch
    
- **Loop** (Anse) – Left Loop, Right Loop
    
- **Whorl** (Vortici) – Plain Whorl, Central Pocket, Double Loop, Accidental
    

La classificazione usa:

1. **Punti singolari**
    
    - **Core**: zona centrale del pattern (apice del flusso).
        
    - **Delta**: punto in cui i ridge “si aprono” a forma di triangolo.
        
2. **Flusso dei ridge**
    
    - come le linee entrano ed escono dal “campo” dell’impronta.
        

#### **7.1 Arches (pagina 12)**

- **Plain Arch**
    
    - le creste entrano da un lato e escono dall’altro con una “onda” centrale;
        
    - nessun delta definito.
        
- **Tented Arch**
    
    - simile alla plain, ma con un **upthrust** centrale:  
        si crea quasi un piccolo triangolo → viene identificato un delta.

![](imgs/Pasted%20image%2020260216120708.png)

#### **7.2 Loops (pagina 13)**

Definizione sintetica:

> Una loop è un pattern in cui alcune creste entrano da un lato,  
> si incurvano (recurve), attraversano una linea immaginaria delta–core e tornano verso lo **stesso lato** da cui sono entrate.

Essenziali tre elementi:

![](imgs/Pasted%20image%2020260216120908.png)

1. **Sufficient recurve** (curvatura sufficiente);
    
2. **Un delta**;
    
3. **Ridge count** (conteggio delle creste che attraversano una certa linea).
    

Due varianti:

- **Left Loop** – l’ansa “apre” verso sinistra;
    
- **Right Loop** – l’ansa “apre” verso destra.
    

#### **7.3 Whorls (pagine 14–15)**

Definizione generale:

- almeno **due delta**,
    
- un **recurve** davanti a ciascun delta.

	![](imgs/Pasted%20image%2020260216121204.png)

Tipi:

1. **Plain Whorl**
    
    - due delta, almeno un ridge che fa un circuito completo  
        (circolare / ovale / spirale).
        
    - una linea tra i delta **tocca** un ridge ricurvo.
        
2. **Central Pocket Loop**
    
    - ancora due delta, circuito completo,
        
    - ma la linea tra i delta **non deve toccare** i ridge ricurvi interni.

![](imgs/Pasted%20image%2020260216121328.png)

3. **Double Loop**
    
    - due loop distinti nella stessa impronta, con due delta.
        
4. **Accidental**
    
    - combinazione di due pattern diversi (escluso plain arch),
        
    - due o più delta,
        
    - o pattern che non si adatta a nessuna definizione standard.
        

---

### **8. Distribuzione statistica delle classi**

La _tabella in pagina 16_ (studio su 5000 persone, Cummins 1943) mostra che:

> **Le classi non sono distribuite uniformemente**.

![](imgs/Pasted%20image%2020260216121448.png)

In media (Galton types in fondo tabella):

- **Whorls** ≈ 25–29%
    
- **Loops** ≈ 67–73%
    
- **Arches** ≈ 4–5%
    

Quindi:

- le **loops** sono nettamente le più frequenti,
    
- le **arches** sono rare,
    
- i **whorls** stanno nel mezzo.
    

Inoltre la distribuzione varia:

- da dito a dito (pollice, indice, medio, anulare, mignolo),
    
- tra mano destra e mano sinistra.
    

Queste statistiche sono fondamentali per la **classificazione automatica** e per il **binning** nei sistemi AFIS.

---

### **9. Esercizio di classificazione personale**

Sulla _pagina 17_ il docente propone un esercizio pratico (ottimo per fissare i concetti):

1. **Esercizio 1**
    
    - Osserva le tue 10 impronte (puoi usare inchiostro o uno scanner).
        
    - Classifica ogni dito come:
        
        - Arch,
            
        - Loop,
            
        - Whorl.
            
    - Nota la **parziale simmetria** tra mano sinistra e destra.
        
2. **Esercizio 2**
    
    - Prova ad arrivare alle **sottoclassi**:
        
        - Plain / Tented Arch,
            
        - Left / Right Loop,
            
        - Plain Whorl / Central Pocket / Double Loop / Accidental.
            

È il modo migliore per “allenare l’occhio” prima di passare a sistemi automatici.

---

### **10. Scale applicative dei sistemi a impronte**

La _pagina 18_ mostra i diversi “livelli di scala” dei sistemi biometrici a impronte:

![](imgs/Pasted%20image%2020260216121628.png)

- **Dispositivi integrati / embedded**
    
    - serrature elettroniche, smartphone, piccoli lettori.
        
- **Smartcard biometriche**
    
    - il template è memorizzato sulla carta,
        
    - il match può essere fatto on-card (match-on-card).
        
- **Sistemi per PC**
    
    - logon locale, login ad applicazioni, single sign-on.
        
- **Sistemi stand-alone**
    
    - varchi di accesso con database locale.
        
- **Sistemi distribuiti AFIS/ABIS**
    
    - grandi database centrali,
        
    - accesso da molte stazioni (via LAN / rete).
        

Più sali di scala, più devi preoccuparti di:

- indicizzazione,
    
- latenza,
    
- robustezza del matching.
    

---

### **11. Applicazioni: forensi, governative, commerciali**

La _pagina 19_ sintetizza le principali **macro-categorie di applicazioni**:

![](imgs/Pasted%20image%2020260216121707.png)

#### **11.1 Forensi**

- identificazione di corpi, persone, terroristi;
    
- ricerca di impronte latenti sulla scena del crimine;
    
- database storici (schedature precedenti, recidivi).
    

#### **11.2 Governative**

- carte d’identità e passaporti elettronici;
    
- patenti di guida;
    
- controllo accessi in edifici pubblici;
    
- controllo delle frontiere;
    
- SPID / sistemi di identità digitale.
    

#### **11.3 Commerciali**

- bancomat, ATM, POS;
    
- login a PC e servizi online;
    
- sblocco smartphone;
    
- controllo accessi aziendali.
    

Le impronte sono una delle poche biometrie che trovano applicazione **in tutti e tre** i domini.

---

### **12. AFIS e ABIS: definizioni e caratteristiche**

#### **12.1 AFIS – Automated Fingerprint Identification System** (pagina 20)

Un AFIS è un sistema **hardware + software** per:

- acquisire e classificare cartellini decadattilari (10 dita);
    
- cercare impronte sconosciute in un **unico grande database**;
    
- supportare ricerche:
    
    - su set completi (10 dita),
        
    - su frammenti (impronte latenti da scena del crimine).
        

Gli AFIS moderni rispettano standard di qualità molto rigidi (es. FBI – link nelle slide).

#### **12.2 Da AFIS a ABIS**

In molti Stati il sistema di impronte viene integrato con altre biometrie:

- **voce**,
    
- **iride**,
    
- **volto**,
    
- **DNA**.
    

Si parla allora di **ABIS – Automated Biometric Identification System**.

![](imgs/Pasted%20image%2020260216122006.png)

---

### **13. Punti di forza e debolezze delle impronte**

#### **13.1 Punti di forza**

- tecnologia **matura**, molto studiata;
    
- **alta accuratezza** per applicazioni operative reali;
    
- acquisizione relativamente **semplice ed ergonomica**;
    
- possibilità di usare **più dita** → si abbassa drasticamente il tasso di errore.
    

#### **13.2 Debolezze**

- una frazione non trascurabile della popolazione (~**4%**) ha impronte:
    
    - quasi assenti,
        
    - consumate,
        
    - non leggibili (lavori manuali, malattie della pelle, età avanzata);
        
- l’accuratezza **tende a degradare nel tempo** per riduzione della qualità della pelle;
    
- forte associazione nell’immaginario collettivo a **applicazioni forensi** → alcune persone provano disagio nel “dare le impronte”.
    

---

### **14. I tre livelli di analisi delle impronte**

Da _pagina 24_ in avanti inizia la **Parte B**: i tre livelli di analisi.

> Un’impronta può essere studiata a livello:
> 
> - I: **globale** (pattern complessivo),
>     
> - II: **locale** (minutiae),
>     
> - III: **ultra-fine** (pori, piccole deformazioni, precisione di 60micron).

![](imgs/Pasted%20image%2020260216122337.png)

#### **14.1 Livello I – pattern globale (pagina 26)**

Osserviamo:

![](imgs/Pasted%20image%2020260216122409.png)

1. **Flusso delle linee** (arch, loop, whorl, sottoclassi);
    
2. **Punti singolari** (core e delta);
    
3. **Forma dell’impronta** (contorno, area coperta);
    
4. **Orientamento complessivo**;
    
5. **Frequenza spaziale** delle creste (distanza media tra ridge).
    

Queste informazioni sono usate per:

- classificazione grossolana,
    
- indicizzazione nei database,
    
- pre-allineamento in fase di matching.
    

### **14.2 Livello II – caratteristiche locali (minutiae, pagina 27–28)**

Al livello locale si osservano circa **150 possibili tipi** di dettagli, chiamati **minuzie**.  
Le più importanti:

- **Termination** – fine di una cresta;
    
- **Bifurcation** – una cresta che si divide in due.

![](imgs/Pasted%20image%2020260216122619.png)

L’FBI, nei sistemi automatici (AFIS), usa **solo terminazioni e biforcazioni** come feature di II livello, perché:

- sono relativamente facili da estrarre automaticamente;
    
- sono molto discriminative.
    

Altri esempi di minutiae (pagina 28):

- **Lake** (lago),
    
- **Point/Island** (isolotti),
    
- **Independent ridge**,
    
- **Spur**,
    
- **Crossover**.
    

#### **14.3 Livello III – dettagli ultra-fini (pagina 29–30)**

Dettagli osservabili solo ad alta risoluzione (≥ **1000 dpi**):

![](imgs/Pasted%20image%2020260216122650.png)

- **Intra-creste**:
    
    - forma, dimensione, posizione dei **pori** sudoripari (60–250 μm);
        
- **Inter-creste**:
    
    - **incipient ridges** (ridge “incompleti”),
        
    - micro-deformazioni;
        
- **Cicatrici permanenti**;
    
- **Pieghe cutanee permanenti**;
    
- Deformazioni transitorie (verruche).
    

Questi dettagli sono **altamente distintivi**, ma:

- richiedono sensori ad altissima risoluzione,
    
- condizioni di acquisizione ideali.

![](imgs/Pasted%20image%2020260216122719.png)

Per questo i sistemi commerciali standard (500 dpi) spesso **non sfruttano** il livello III, mentre in contesti forensi avanzati può essere decisivo.

---

### **15. Unicità delle impronte e gemelli monozigoti**

Sulle _pagine 31–32_ il docente sintetizza più di un secolo di studi:

- in oltre **100 anni** e **milioni di impronte** analizzate,  
    **non sono mai state trovate** due impronte identiche di individui diversi;
    
- anche **gemelli monozigoti** (stesso DNA) hanno impronte **diverse**:
    
    - l’impronta è **fenotipo**, non genotipo puro;
        
    - intervengono fattori:
        
        - ambientali,
            
        - casuali,
            
        - micro-variazioni nello sviluppo embrionale (immagine sotto)

![](imgs/Pasted%20image%2020260216123026.png)

![](imgs/Pasted%20image%2020260216122838.png)

Nei gemelli:

- la struttura globale può essere simile,
    
- ma a livello di minuzie e dettagli fini ci sono sempre **differenze sufficienti** per distinguerli.

![](imgs/Pasted%20image%2020260216123006.png)

---

### **16. Quante minuzie servono per dire “match”?**

La _pagina 33_ spiega che **non esiste una regola mondiale univoca**:

- **Paesi con soglia di 12 punti**:  
    Olanda, Belgio, Brasile, Portogallo, Svizzera, ecc.
    
- **Sud Africa**: bastano **7** punti caratteristici.
    
- Molti Stati (Canada, USA, UK, Australia)  
    **non fissano una soglia** rigida: è l’esperto che valuta caso per caso.
    

In generale:

- si possono usare anche **dettagli di III livello**,
    
- spesso **pochi mm²** contengono già un numero sufficiente di dettagli di tutti i livelli.
    

In Italia (vedi anche più avanti, pagina 48):

- storicamente si parlava di **16–17 punti**,
    
- oggi conta molto la **qualità** del ritrovamento e il **parere dell’esperto**.
    

---

### **17. Come lavora un esperto umano**

Sulla _pagina 34_ viene descritto il flusso operativo di un analista:

1. **Pattern globale**
    
    - verifica concordanza del **livello I** (stesso tipo: arch/loop/whorl, stessa forma).
        
2. **Concordanza qualitativa**
    
    - minutiae corrispondenti devono essere **identiche** come forma e contesto.
        
3. **Fattore quantitativo**
    
    - deve essere presente un **minimo numero di minutiae corrispondenti** (es. ≥ 12).
        
4. **Livello III**
    
    - quando disponibile, la corrispondenza dei dettagli ultra-fini deve essere coerente.
        

In pratica:  
l’esperto combina **tutte le informazioni** disponibili, non solo il conteggio dei punti.

---

### **18. Processo ACE-V (Analysis–Comparison–Evaluation–Verification)**

_Pagina 35–36._

È il protocollo standard usato in ambito forense:

1. **Analysis (A)**
    
    - si analizza l’impronta latente: qualità, distorsioni, zona utile;
        
    - si segnano (markup) i dettagli di I, II, III livello.
        
2. **Comparison (C)**
    
    - confronto sistematico con l’impronta di riferimento:  
        posizioni relative, orientamenti, contesto.
        
3. **Evaluation (E)**
    
    - decisione finale:
        
        - **Identificazione** (same source),
            
        - **Esclusione**,
            
        - **Inconcludente** (non si può decidere).
            
4. **Verification (V)**
    
    - un **secondo esperto indipendente** ripete il processo.
        

![](imgs/Pasted%20image%2020260216123610.png)

Alla fine, la decisione arriva **integrando tutte le metriche** e osservazioni, non solo il numero di minutiae.

---

### **19. Errori umani famosi: falsi positivi e scandali**

Le _pagine 37–38_ elencano casi clamorosi di **False Positive** in ambito forense:

- **Brandon Mayfield** (attentati di Madrid 2004)
    
    - impronta latente trovata su una borsa con detonatori;
        
    - l’AFIS USA individua 20 possibili candidati;
        
    - esperti dell’FBI dichiarano un “match assolutamente incontrovertibile”;
        
    - Mayfield, avvocato in Oregon, viene arrestato e detenuto;
        
    - la polizia spagnola non è convinta, trova il vero attentatore (Ouhnane Daoud);
        
    - Mayfield viene liberato → scandalo internazionale.
        
- **Shirley McKie**
    
    - agente di polizia scozzese,
        
    - viene accusata perché su una scena del crimine compare una presunta sua impronta;
        
    - in seguito si dimostra l’errore.
        
- **Stephan Cowans**
    
    - imprigionato per un match di impronta,
        
    - si finanzia da solo le analisi del DNA e viene scagionato.
        

Nella slide 38 si vede chiaramente che:

![](imgs/Pasted%20image%2020260216124150.png)

> le impronte coinvolte **non sono uguali**:  
> l’errore è dell’esaminatore, non della biologia.

Questo sottolinea:

- l’enorme **responsabilità legale** dell’uso delle impronte,
    
- il bisogno di **metodi spiegabili** (tema ripreso poi con il Deep Learning).
    

---

### **20. Quiz di confronto impronte**

Le _pagine 39–42_ presentano due quiz:

- **Quiz 1** – due impronte di persone diverse (impostori);
    
- **Quiz 2** – due impronte della stessa persona (genuino).
    

L’idea è allenare l’occhio a:

- riconoscere deformazioni dovute a diversi tipi di acquisizione (rotolata “rolled” vs plain);
    
- capire che **pattern globalmente simili possono essere impostori**,
    
- e **pattern apparentemente deformati possono essere genuini**.
    

---

### **21. Il sistema AFIS italiano**

_Pagine 43–46._

![](imgs/Pasted%20image%2020260216124456.png)

#### **21.1 Dati generali**

- **Progetto**: 1994
    
- **Inizio funzionamento**: 1995
    
- **Hardware**: Hewlett-Packard
    
- **Software AFIS**: Cogent Systems (oggi IDEMIA)
    
- **Statistiche**:
    
    - 21 milioni di cartellini,
        
    - circa 8.9 milioni di individui memorizzati.
        

Dal 2025 sono previsti anche **terminali mobili** per le forze dell’ordine.

#### **21.2 Architettura** (pagina 44)

Tre livelli:

![](imgs/Pasted%20image%2020260216124517.png)

1. **Livello 1** – stazioni periferiche (acquisizione, consultazione locale).
    
2. **Livello 2** – region server / message switch server.
    
3. **Livello 3** – server AFIS centrali (AFIS1, AFIS2).
    

È un classico sistema distribuito ad alta affidabilità.

#### **21.3 Verso ABIS: AFIS + SARI + palmi** (pagina 45–46)

- AFIS si integra con **SARI** (riconoscimento facciale, anche in real time – sospeso dal Garante Privacy).

![](imgs/Pasted%20image%2020260216124544.png)
    
- Obiettivo: integrare anche **palmi** → **APFIS** (Automated Palmprint & Fingerprint Identification System).

![](imgs/Pasted%20image%2020260216124636.png)
    
- Impronte acquisite con rapporto **1:1 metrico**, in scala di grigi,  
    risoluzione:
    
    - minima **500 ppi**,
        
    - raccomandata **1000 ppi**.

![](imgs/Pasted%20image%2020260216124658.png)

![](imgs/Pasted%20image%2020260216124742.png)

---

### **22. Criticità attuali nei sistemi AFIS**

_Pagina 47._

Punti critici:

1. **Qualità di acquisizione**
    
    - se la qualità è scarsa, precipitano le prestazioni del matching automatico.
        
2. **Correttezza dell’estrazione delle minuzie**
    
    - anche con buona immagine, un cattivo estrattore di minutiae produce errori;
        
    - serve un **supervisore umano** per i casi più delicati.
        
3. **Impronte latenti o di bassa qualità**
    
    - tipiche del forense: parziali, sporche, sovrapposte.
        
4. **Deep Learning non spiegabile (non-XAI)**
    
    - i nuovi algoritmi di DL hanno prestazioni eccellenti,
        
    - ma sono **black box**: non sappiamo _perché_ decidono “match”;
        
    - non è accettabile **in tribunale** dire “la rete neurale dice che è lui”.
        

Conclusione:  
il DL va usato con cautela in contesti legali, integrato con metodi **spiegabili e verificabili**.

---

### **23. Normativa italiana su minuzie e prova in tribunale**

_Pagina 48._

- Sentenza **2559 del 14/11/1959** – Corte di Cassazione:  
    indica in **16–17 punti caratteristici** il minimo numero di segni uguali (forma, posizione, orientamento).
    
- Sentenza **01155 del 03/02/1971**:  
    introduce anche la presenza di **segni di cicatrice** identici.    

![](imgs/Pasted%20image%2020260216134227.png)

Situazione attuale:

- l’ordinamento **si affida al parere degli esperti**;
    
- conta molto di più la **qualità dei ritrovamenti** che il semplice numero di minuzie;
    
- le linee guida internazionali (es. Ulery et al. 2014, citato in slide) studiano cosa gli esperti considerano “sufficiente”.
    

---
### **24. Tecnologia di mercato e prestazioni**

_Pagine 49–52._

Il docente elenca alcuni **produttori di sistemi AFIS/ABIS** (solo come cultura generale):

- NEC (Giappone), Idemia (Francia, ex Cogent/3M), Dermalog (Germania),
    
- Innovatrics (Slovacchia), M2SYS, Neurotechnology, Aware, Bio-Metrica, Zetes/Panasonic, ecc.
    

Esempi di prestazioni:

- sistemi che cercano:
    
    - oltre **200 milioni di iridi** o **100 milioni di impronte al secondo** (M2SYS);
        
    - **3.6 miliardi** di confronti impronta/s al secondo su HW dedicato (Dermalog).
        

Molti sistemi sono:

- **multimodali** (impronte + volto + iride),
    
- compatibili con **standard** FBI / NIST / ISO,
    
- scalabili su architetture server, mobile, embedded, match-on-card (Innovatrics SDK).

![](imgs/Pasted%20image%2020260216134743.png)

---

### **25. Trend attuali e futuri nelle impronte e negli AFIS**

Sulle _ultime pagine (53–54)_ il docente riassume i principali **trend**:

![](imgs/Pasted%20image%2020260216134807.png)

1. **Dispositivi embedded e mobile**
    
    - lettori integrati in smartphone, varchi, IoT.
        
2. **Contactless fingerprint**
    
    - acquisizione da fotocamera, senza contatto fisico;
        
    - riduce problemi igienici e deformazioni da pressione.
        
3. **Aumento della risoluzione**
    
    - passaggio da 500 dpi a **1000 dpi** come nuovo standard per:
        
        - sfruttare anche il **livello III**,
            
        - migliorare l’interoperabilità con sistemi diversi.
            
4. **Migliore interoperabilità**
    
    - standard (ISO, ANSI), formati di scambio, modelli di qualità.
        
5. **Automazione e precisione nell’estrazione delle minutiae**
    
    - nuovi algoritmi più robusti a rumore e distorsioni.
        
6. **Estensione a sistemi multimodali**
    
    - transizione da AFIS a **ABIS**,
        
    - combinazione con volto, iride, palmi, ecc.

![](imgs/Pasted%20image%2020260216134847.png)

---

### **26. In sintesi**

In questa Lezione 7 hai costruito la base concettuale per tutti i sistemi biometrici a impronte:

- storia dell’uso delle impronte (da firma su argilla a AFIS nazionale);
    
- biologia delle **friction ridges** e stabilità delle impronte;
    
- crescita e variabilità temporale, con impatto sui False Non-Match;
    
- sistema di classificazione **Arch / Loop / Whorl** e sottoclassi;
    
- distribuzione statistica delle classi nella popolazione;
    
- tre livelli di analisi:
    
    - **I** (pattern globale),
        
    - **II** (minutiae),
        
    - **III** (pori, dettagli ultra-fini);
        
- unicità del tratto anche per gemelli monozigoti;
    
- lavoro dell’esperto umano (ACE-V) e casi reali di falsi positivi;
    
- struttura e funzionamento di un **AFIS nazionale** (nel dettaglio quello italiano, in evoluzione verso ABIS);
    
- criticità tecniche (qualità, estrazione minuzie, DL non spiegabile);
    
- quadro normativo sulle minuzie;
    
- panoramica delle tecnologie commerciali e dei **trend futuri** (1000 dpi, contactless, multimodalità).