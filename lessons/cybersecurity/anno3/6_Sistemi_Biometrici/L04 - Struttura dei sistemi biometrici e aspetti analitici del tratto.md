# **Lezione 4: Struttura dei sistemi biometrici e aspetti analitici del tratto**

---

### **0. Panoramica della lezione**

Questa lezione ha due blocchi:

1. **Struttura dei sistemi biometrici (Parte A)**
    
    - pipeline interna di un sistema biometrico (enrollment, verification, identification);
        
    - varianti architetturali:
        
        - sistemi per documenti biometrici,
            
        - sistemi multimodali,
            
        - sistemi distribuiti,
            
        - sistemi on card,
            
        - match-on-card e match-on-sensor.
            
2. **Aspetti analitici del tratto e regole di progettazione (Parte B)**
    
    - variabilità del tratto nel tempo (temporalità, intra-classe, inter-classe);
        
    - differenza tra riconoscimento umano e automatico;
        
    - fasi critiche di progettazione:
        
        - acquisizione,
            
        - controllo qualità,
            
        - enhancement,
            
        - segmentazione.
            

L’idea di fondo: **vedere la biometria come un sistema ingegneristico completo** — dal sensore alla decisione finale — e capire dove si gioca davvero la qualità del risultato.

---

## **Parte A – Struttura dei sistemi biometrici**

---

### **1. Architettura generale di un sistema biometrico**

![](imgs/Pasted%20image%2020260209185247.png)

#### **1.1 I moduli fondamentali**

Il diagramma di pagina 3 mostra la **struttura base** di qualunque sistema biometrico:

![](imgs/Pasted%20image%2020260209185316.png)

- **Trait**
    
    - il tratto fisico/comportamentale (dito, volto, iride, voce, …).
        
- **Acquisition Module**
    
    - il **sensore** + la logica di acquisizione, che produce il **sample** (immagine, segnale audio, ecc.).
        
- **Quality Checker**
    
    - valuta se il sample è abbastanza buono:
        
        - se sì → passa allo step successivo;
            
        - se no → si chiede una nuova acquisizione.
            
- **Feature Extraction Module**
    
    - prende il sample e ne estrae le **feature**: minuzie, descrittori del volto, parametri vocali, ecc.;
        
    - nello schema del corso, in questo modulo sono compresi anche gli algoritmi che codificano le feature nel **template**.
        
- **Template**
    
    - rappresentazione digitale compatta e numerica delle feature.
        
- **DataBase**
    
    - memorizza i template.
        

Questa è la **pipeline canonica**. Tutto il resto (modalità di deployment, multimodalità, distribuzione in rete) è una variazione di questa pipeline.

---

#### **1.2 Enrollment: registrare l’utente nel sistema**

Nello schema di pagina 4 si vede la **fase di enrollment**:

![](imgs/Pasted%20image%2020260209185402.png)

- l’utente fornisce il tratto → sensore → sample;
    
- il sample passa il **quality check** e viene codificato in template;
    
- il template viene associato ad un’**identità dichiarata** (nome, PIN, ID utente);
    
- la coppia _(template, identity)_ viene memorizzata nel **DB**.
    

Questa è la fase “ti presento il sistema: questo sono io”.  
Se l’enrollment è fatto male (sample sporchi, pochi campioni, procedure confuse) **tutte le fasi successive soffriranno**.

---

#### **1.3 Verification (autenticazione 1:1)**

Schema pagina 5.

![](imgs/Pasted%20image%2020260209185441.png)

Domanda: **“Sei davvero l’identità X che dici di essere?”**

1. L’utente:
    
    - dichiara una **identità** (username, numero tessera, PIN);
        
    - fornisce il tratto (impronta, volto, …).
        
2. Il sistema:
    
    - acquisisce il sample,
        
    - controlla la qualità,
        
    - estrae le feature e genera un template “di prova”.
        
3. **Matching Module**:
    
    - prende **un solo template** dal DB (quello associato all’identità dichiarata),
        
    - confronta il template di prova con quello in DB,
        
    - produce uno **score**,
        
    - lo confronta con una **soglia**,
        
    - restituisce **Yes/No**.
        

Qui si parla di **1:1** (one-to-one) perché si confrontano **due template**: quello dell’utente e quello memorizzato.

---

#### **1.4 Identification (ricerca 1:N)**

Schema pagina 6.

![](imgs/Pasted%20image%2020260209185518.png)

Domanda: **“Chi è questa persona tra tutte quelle registrate?”**

1. L’utente fornisce il tratto, ma **non dichiara l’identità**.
    
2. Il sistema:
    
    - acquisisce il sample,
        
    - controlla la qualità,
        
    - estrae le feature → template di prova.
        
3. Il **Matching Module**:
    
    - confronta il template di prova con **tutti** i template nel DB (o con un sottoinsieme selezionato),
        
    - cerca il template che massimizza lo score,
        
    - decide se:
        
        - l’utente corrisponde ad uno dei template → restituisce l’**identità trovata**;
            
        - oppure non c’è corrispondenza sufficiente → **User not identified**.
            

Questa è la tipica situazione dei **grandi sistemi di polizia**, dei **controlli frontiera** o delle **ricerche forensi**.

---

### **2. Sistemi “per documenti biometrici”**

#### **2.1 Cos’è un documento biometrico?**

Schema pagina 7–8.

![](imgs/Pasted%20image%2020260209185537.png)

Un **documento biometrico** è un documento (fisico o smart card) che **contiene il template** biometrico dell’utente:

- passaporto elettronico,
    
- carta d’identità elettronica,
    
- badge con chip contactless, ecc.
    

##### **Enrollment per documenti biometrici**

- pipeline tradizionale: Trait → Sample → Quality → Feature Extraction → Template;
    
- **output**: invece di mandare il template al DB, lo si memorizza **sul documento** (chip).
    

##### **Verification con documento biometrico**

![](imgs/Pasted%20image%2020260209185605.png)

- l’utente presenta il documento;
    
- il sistema:
    
    - acquisisce il tratto (es. volto o impronta),
        
    - legge il **template dal documento**,
        
    - esegue il **matching** tra template live e template nel documento,
        
    - produce Yes/No.
        

Qui il DB centrale può non esserci, oppure può fornire solo **info anagrafiche**, mentre il dato strettamente biometrico rimane sul documento.

---

### **3. Sistemi biometrici multimodali**

#### **3.1 Definizione**

![](imgs/Pasted%20image%2020260209185624.png)

> “Multimodal biometric systems are those which utilize, or are capable of utilizing, more than one physiological or behavioral characteristic for enrollment, verification, or identification.”

In pratica:  
**multimodale** ≠ “più impronte nello stesso dito”;  
**multimodale** = “più _tratti diversi_ o più _canali diversi_ (es. volto + impronta + voce)”.

Sul lato destro della slide si vedono esempi:

- volto + mano,
    
- impronta + iride,
    
- interi apparati sperimentali con molti sensori contemporanei.
    

---

#### **3.2 Strutture monomodale vs multimodale**

Diagramma pagina 10.

##### **Verifica monomodale (classica)**

![](imgs/Pasted%20image%2020260209185707.png)

- un solo **Acquisition Module**;
    
- un solo **Feature Extraction Module**;
    
- un solo **Matching Module**;
    
- DB con template di quel tratto;
    
- output: Yes/No o score.
    

##### **Verifica multimodale**

![](imgs/Pasted%20image%2020260209185716.png)

- **Acquisition Module 1, 2, …** (es. sensore di impronta + camera per volto);
    
- per ogni tratto:
    
    - Feature Extraction Module,
        
    - Matching Module,
        
    - DB dedicato,
        
    - percentuale di matching/score.
        
- **Decision Module** finale:
    
    - prende gli score di tutti i matcher,
        
    - li fonde (es. media pesata, AND logico, OR logico, regole più sofisticate),
        
    - produce decisione complessiva Yes/No.
        

Idea chiave:

> Non basta avere più sensori: serve una **regola di decisione** ben progettata per combinarli.

---

### **4. Sistemi biometrici distribuiti**

#### **4.1 Cosa significa “distribuito”**

Slide 11.

![](imgs/Pasted%20image%2020260209185740.png)

Un sistema biometrico è **distribuito** quando i suoi moduli sono **fisicamente separati** e collegati tramite rete:

- terminali locali (sensore + pre-elaborazione),
    
- server centrali con grandi DB,
    
- canali di comunicazione tra le parti.
    

È **raro** nei sistemi puramente di autenticazione locale (es. sblocco del PC),  
ma è **molto comune** nei sistemi di:

- identificazione in aeroporto,
    
- controllo delle frontiere (passaporti biometrici + gate ABC),
    
- sistemi investigativi nazionali (es. IAFIS),
    
- grandi sistemi di videosorveglianza.
    

Di solito ad essere dislocato è il **modulo DB dei template**:

- per motivi di **sicurezza** (DB in data center protetti),
    
- per collegare DB distribuiti su LAN/WAN (polizia, agenzie diverse).
    

---

#### **4.2 Esempio di architettura distribuita**

Il diagramma di pagina 12 mostra una pipeline più dettagliata:

![](imgs/Pasted%20image%2020260209185758.png)

- **Blocchi T1–T3**: sensori multipli che producono sample (occhio, mano, volto, voce…);
    
- **Blocchi T4–T5**: filtri e moduli di Feature Extraction;
    
- **Blocchi T6–T7**: matcher che producono score;
    
- **Blocco T8**: DB singoli o multipli distribuiti in rete;
    
- **Blocco T9**: decision algorithm centrale;
    
- output finale: **Authentication YES/NO**.
    

L’idea è vedere il sistema come una catena di moduli con **sample, features, templates, scores** che viaggiano su rete.

---

#### **4.3 Modello di Mansfield & Wayman (2002)**

Slide 13.

![](imgs/Pasted%20image%2020260209185824.png)

Qui la catena viene vista in 4 macro-fasi:

1. **Data Collection**
    
    - presentazione del tratto,
        
    - sensore,
        
    - generazione dei sample.
        
2. **Data Storage**
    
    - memorizzazione di templates e immagini.
        
3. **Signal Processing**
    
    - segmentazione,
        
    - feature extraction,
        
    - quality control,
        
    - pattern matching.
        
4. **Decision**
    
    - decisione finale di accept / reject in funzione di criteri di accuratezza.
        

È una rappresentazione concettuale che useremo implicitamente per tutto il corso.

---

### **5. Sistemi biometrici on card e biometric-on-card**

#### **5.1 On card (template su smart card)**

Slide 14.

**On card** = il template biometrico è **memorizzato sulla smart card** dell’utente.

Caratteristiche tipiche:

- la smart card **non ha sensore** → il tratto è acquisito da un dispositivo esterno (lettore di impronte, camera, ecc.);
    
- la smart card, nelle versioni base:
    
    - **non effettua il matching** (poca potenza di calcolo),
        
    - si limita a **conservare** e magari **cifrare** il template.
        

Di conseguenza:

- il dato biometrico (immagine o template) **esce dalla carta** per essere elaborato esternamente;
    
- questo aumenta il rischio di **intercettazione** (meno “privacy by design” rispetto a GDPR), anche se in pratica è molto diffuso.

![](imgs/Pasted%20image%2020260209190132.png)

Sono citati esempi commerciali (Precise Biometrics, Veridicom, Siemens Matcher on Card).

---

#### **5.2 Biometric on Card (BoC)**

Slide 15.

Qui si fa un passo in più:

> **Biometric on Card** = la smart card integra _tutto_:
> 
> - sensore,
>     
> - template,
>     
> - algoritmo di matching.
>     

Esempi:

- **Zwipe ID** (contactless card con sensore di impronte integrato),
    
- soluzioni Samsung, Idemia, Thales.
    

Vantaggi:

- il template **non esce mai** dalla carta → forte punto di privacy;
    
- la decisione Yes/No viene presa **localmente sulla card**.
    

Limiti:

- risorse hardware limitate → algoritmi di matching **semplificati**;
    
- costi più elevati per card e lettori.

![](imgs/Pasted%20image%2020260209190023.png)

![](imgs/Pasted%20image%2020260209190035.png)

---

### **6. Match-on-sensor**

Slide 16.

Un ulteriore passo di integrazione: **Match on sensor**.

Esempio: sensori Synaptics SentryPoint, con:

- **Encryption** nativa,
    
- architettura **Match-in-Sensor**:
    
    - il sensore stesso esegue matching tra sample e template memorizzati al suo interno,
        
- tecnologie **anti-spoof** integrate.

![](imgs/Pasted%20image%2020260209190158.png)

In più, la tecnologia **“under the glass”**:

- il sensore è posto sotto il vetro (es. sotto lo schermo di uno smartphone),
    
- risulta:
    
    - meno visibile,
        
    - meno invasivo,
        
    - più integrato nell’interfaccia.
        

Questa è una forma di forte **privacy by design**: riduci superfici d’attacco e centralizzazione.

---

### **Sintesi Parte A**

La parte A si chiude (pagina 17) ricordando che abbiamo visto:

- struttura generale nelle tre fasi:
    
    - **enrollment**,
        
    - **verification**,
        
    - **identification**;
        
- architetture:
    
    - sistemi **monomodali**,
        
    - sistemi **multimodali**,
        
    - sistemi **distribuiti**,
        
    - sistemi **on card** e **biometric-on-card**,
        
    - tecnologie **match-on-sensor**.
        

Questa mappa è la base per ragionare, nelle prossime lezioni, su **prestazioni** e **progettazione concreta** dei sistemi.

---

## **Parte B – Tratto biometrico: aspetti analitici e regole di progettazione**

---

### **7. Variabilità temporale del tratto**

#### **7.1 Impronte nel tempo**

![](imgs/Pasted%20image%2020260209190537.png)

- serie di impronte dello **stesso individuo** acquisite nel tempo (mesi);
    
- sulla destra, un grafico **quality vs age**:
    
    - un soggetto di 22 anni,
        
    - un soggetto di 81 anni.
        

Osservazioni:

- anche lo stesso dito, nel corso dei mesi, cambia per micro-usura, secchezza, piccoli tagli;
    
- negli anziani la **qualità media delle impronte cala** (pelle più sottile, secca, rugosa) → più difficile da acquisire bene.
    

---

#### **7.2 Volto nel tempo**

![](imgs/Pasted%20image%2020260209190635.png)

immagini note di **Sharbat Gula** (foto icona del National Geographic):

- una foto da adolescente,
    
- una dopo decenni.
    

Il volto è lo stesso, ma:

- struttura ossea invariata,
    
- linee del viso cambiate (rughe, espressioni, peso).
    

Conclusione:

> Anche tratti che consideriamo “stabili” hanno una **variabilità temporale**:  
> il sistema deve essere progettato per tollerarla.

---

#### **7.3 Limiti della percezione umana**

Slide 21–22: test con foto segnaletiche di personaggi famosi:

![](imgs/Pasted%20image%2020260209190854.png)

- Frank Sinatra, Elvis Presley, Johnny Cash,
    
- Jimi Hendrix, Jim Morrison, David Bowie, Mick Jagger, Janis Joplin, Kurt Cobain.
    

Se li guardi in sequenza, **non è banale riconoscerli tutti**, soprattutto in foto vecchie, con illuminazioni diverse.

Messaggio:

- **anche il sistema visivo umano** fa fatica quando cambiano:
    
    - tempo,
        
    - contesto,
        
    - hairstyle, barba, abbigliamento,
        
    - condizioni di scatto.
        

Questo giustifica quanto sia complesso progettare sistemi automatici robusti.

---

### **8. Variabilità intraclasse e similitudine interclasse**

#### **8.1 Variabilità intraclasse**

Slide 23.

![](imgs/Pasted%20image%2020260209190931.png)

**Variabilità intraclasse** = variazione del sample/feature **dello stesso individuo** in acquisizioni diverse.

Cause principali:

- **rumore del dispositivo** (sensore, elettronica),
    
- cambiamenti di **sfondo**, posizione, illuminazione, distanza,
    
- cambiamenti del tratto:
    
    - invecchiamento,
        
    - posizione del dito o della testa,
        
    - espressione facciale,
        
    - usura delle impronte,
        
- **occlusioni parziali** (ciglia, capelli, occhiali, mani che coprono il volto).
    

Un sistema ben progettato deve **accettare** questa variabilità senza generare troppi falsi rifiuti.

---

#### **8.2 Similitudine interclasse**

Slide 24.

**Similitudine interclasse** = vicinanza (in spazio delle feature) di sample di **individui diversi**.

Esempi:

- **gemelli monozigoti**,
    
- **sosia** (persone molto simili).
    

Le foto in slide mostrano coppie di gemelli o sosia quasi indistinguibili ad occhio umano.

Impatto:

- aumenta il rischio di **falsi accettati** (false match),
    
- rende necessario progettare feature e soglie con maggiore attenzione.

![](imgs/Pasted%20image%2020260209191051.png)

---

### **9. Uomo vs sistema automatico**

Slide 25–26.

Esperimento mentale:

- ci viene mostrata una foto con **Bill Clinton** e a fianco un volto che _percepiamo_ come Al Gore (sinistra),
    
- in realtà entrambi i volti sono **lo stesso volto di Clinton**, con contesto diverso.

![](imgs/Pasted%20image%2020260209191318.png)

La nostra percezione viene ingannata dal **contesto** (quello a sinistra è vestito e posizionato come ci aspetteremmo per Gore).

Conclusione:

> Un sistema biometrico **non deve ragionare come un umano**.  
> Spesso è un bene che lavori sul **pattern numerico** e ignori elementi di contesto che a noi confondono.

---

### **10. Regole generali di progettazione**

Slide 27–28 riprendono la pipeline:

![](imgs/Pasted%20image%2020260209191359.png)

- Acquisition Module
    
- Quality Checker
    
- Feature Extraction
    
- DataBase
    

e definiscono le **fasi critiche** da analizzare:

1. **Acquisizione del tratto**
    
    - scelta dei sensori,
        
    - condizioni ambientali,
        
    - controllo qualità.
        
2. **Rappresentazione**
    
    - del sample (formato, risoluzione, profondità colore),
        
    - delle feature (che tipo di descrittori),
        
    - del template (dimensione, codifica, robustezza).
        
3. **Matching**
    
    - definizione della metrica di distanza/similarità,
        
    - scelta delle soglie,
        
    - ottimizzazione di velocità e accuratezza.
        
4. **Ricerca e organizzazione del DB**
    
    - strutture dati e indici,
        
    - scalabilità con il numero di utenti,
        
    - integrazione con sistemi distribuiti.
        

Queste sono le **leve ingegneristiche** su cui puoi intervenire progettando un sistema.

---

### **11. Migliorare l’acquisizione**

#### **11.1 Importanza dell’acquisizione**

Slide 29–30.

![](imgs/Pasted%20image%2020260209191434.png)

- L’acquisizione è spesso **sottovalutata**,
    
- ma la sua **qualità** domina le prestazioni finali del sistema.
    

Viene proposta una scomposizione dell’acquisizione in due sottofasi:

1. **Valutazione della qualità (quality assessment)**
    
    - controllare automaticamente se i dati in ingresso sono coerenti con le elaborazioni successive;
        
    - decidere se accettare il sample o richiederne un altro.
        
2. **Segmentazione (segmentation)**
    
    - separare _foreground_ (oggetto di interesse, es. volto) da _background_ (sfondo, rumore).
        

---

#### **11.2 Strategia 1 – Acquisire maggiori informazioni (contesto)**

Slide 31.

![](imgs/Pasted%20image%2020260209191500.png)

Buona pratica: **acquisire anche lo sfondo** e più contesto possibile.

Esempio:

- per il volto:
    
    - registrare anche lo sfondo permette di:
        
        - sottrarre frame,
            
        - localizzare meglio dove si trova il vero volto,
            
    - senza dover usare algoritmi semantici troppo pesanti sul dispositivo di acquisizione.
        

Più informazioni contestuali raccogli, **più semplice** sarà la segmentazione e la rimozione del rumore.

---

#### **11.3 Strategia 2 – Evitare a monte le cattive acquisizioni**

![](imgs/Pasted%20image%2020260209191527.png)

L’idea è **bloccare subito** le acquisizioni inutilizzabili:

- verificare se il soggetto:
    
    - è a distanza corretta dal sensore,
        
    - è fermo abbastanza,
        
    - è a fuoco.
        

Per farlo:

- si elabora il **flusso video** in (quasi) tempo reale (es. 50 fps),
    
- si valuta la nitidezza, il movimento, ecc.,
    
- solo quando le condizioni sono accettabili si usa il frame per l’estrazione di feature.
    

Grazie a:

- potenze computazionali moderne,
    
- reti a larga banda,
    

è possibile trasferire ed elaborare **grandi quantità di dati** per migliorare questa fase.

---

#### **11.4 Esempio: migliorare l’hardware per le impronte**

Slide 33.

![](imgs/Pasted%20image%2020260209191547.png)

Normalmente:

- si acquisisce una **proiezione 2D** dell’impronta su una superficie piana.
    

Possibili miglioramenti:

- acquisire un’immagine **3D** (mappa di profondità delle creste),
    
- acquisire **immagini a colori** per sfruttare informazioni aggiuntive,
    
- usare sensori di **active sensing**:
    
    - sensori che si adattano automaticamente alle condizioni ambientali (luce, pressione, ecc.),
        
- combinare più tecniche per ottenere una proiezione 2D “classica” ma **di qualità molto migliore**.
    

---

### **12. Controllo della qualità del sample**

#### **12.1 Concetto generale**

![](imgs/Pasted%20image%2020260209191617.png)

Dopo l’acquisizione, **molti sistemi** applicano un **controllo automatico della qualità** per:

- evitare di inserire nel DB template derivati da sample scadenti,
    
- ridurre il numero di fallimenti nelle fasi successive.
    

Funzionamento base:

- si calcola un **indice di qualità** $Q$ del sample;
    
- se $Q$ è alto → si procede;
    
- se $Q$ è basso → si chiede all’utente di ripetere l’acquisizione.
    

---

#### **12.2 Difficoltà di progettazione della qualità**

Slide 36–37.

Sembra semplice, ma non lo è:

![](imgs/Pasted%20image%2020260209191820.png)

1. Non sempre esiste un **modello “ottimale”** di come dovrebbe essere il sample:
    
    - es.: definire esattamente come deve essere un’impronta perfetta per tutte le dita e tutti i sensori è difficile.
        
2. Anche se avessimo l’immagine ottimale, non sempre esistono metriche robuste per misurare la **distanza** tra sample reale e ideale.
    

Esempio sul volto:

- lo standard ICAO definisce **regole qualitative** (sfondo uniforme, volto frontale, niente occhiali scuri, ecc.),
    
- ma tradurle in un singolo indice numerico $Q(I)$ (es. da 0 a 10) è un problema non banale.
    

L’immagine a fondo pagina 36 mostra esempi di foto accettate/scartate secondo **norme ICAO** (posizione del volto, copertura del viso, velo, ecc.).

---

#### **12.3 Tassonomia dei metodi di quality assessment**

Slide 38.

I metodi si dividono in:

1. **Metodi locali**
    
    - analizzano piccole regioni dell’immagine (es. blocchi di $8 \times 8$ pixel),
        
    - guardano orientazione locale dei ridge, contrasto, intensità.
        
2. **Metodi globali**
    
    - guardano l’immagine nel suo complesso:
        
        - power spectrum,
            
        - campo di orientazione globalmente.
            
3. **Metodi basati su classificatori**
    
    - usano reti neurali o altri classifieri per mappare direttamente il sample in una classe di qualità.
        
4. **Metodi misti**
    
    - combinano più approcci.
        

L’esempio della slide è centrato sulle **impronte digitali**:  
“Fingerprint Image Quality Computation Methods”.

---

#### **12.4 Esempi di indice di qualità**

Slide 39.

![](imgs/Pasted%20image%2020260209191911.png)

Vediamo 4 immagini di impronte con qualità:

- 0.93
    
- 0.63
    
- 0.35
    
- 0.19
    

Il sistema valuta:

- **contrasto** tra creste e valli,
    
- **continuità** delle creste,
    
- presenza di zone bruciate o troppo chiare/scure.
    

Più il pattern è leggibile e continuo, più l’indice di qualità è alto.

---

### **13. Enhancement del segnale/immagine**

#### **13.1 Quando serve l’enhancement**

Slide 40.

In certi contesti (es. **database giudiziari**):

- non è possibile rifiutare il sample per bassa qualità:
    
    - è l’unica impronta del sospettato,
        
    - è una ripresa di videosorveglianza irripetibile.
        

In questi casi:

- si accetta il sample,
    
- si tenta di **estrarre il massimo** delle informazioni utili,
    
- si applicano algoritmi di **signal/image enhancement**.
    

Caratteristiche dell’enhancement:

- alta complessità computazionale,
    
- rischio di introdurre **artefatti**:
    
    - informazioni inesistenti nell’immagine originale,
        
    - ad es. minuzie che non c’erano.
        

---

#### **13.2 Esempi di enhancement**

Slide 41.

![](imgs/Pasted%20image%2020260209192003.png)

- Impronta originale rumorosa,
    
- impronta dopo enhancement:
    
    - le creste sembrano più nitide,
        
    - ma alcune regioni (es. quelle cerchiate in rosso) possono contenere **pattern inventati** dall’algoritmo.
        

Slide 42.

![](imgs/Pasted%20image%2020260209192014.png)

- Immagini di videosorveglianza:
    
    - frame originale molto rumoroso e granuloso, volto quasi invisibile;
        
    - frame dopo enhancement:
        
        - rumore ridotto,
            
        - contrasto migliorato,
            
        - frequenze spaziali tipiche del volto esaltate.
            

Ottimo per investigazione, ma:

> Va ricordato sempre che l’enhancement **non è neutro**:  
> migliora la leggibilità, ma rischia di introdurre errori se usato senza controllo.

---

### **14. Segmentazione**

#### **14.1 Cos’è la segmentazione**

Slide 43.

![](imgs/Pasted%20image%2020260209192033.png)

La **segmentazione** è la fase in cui, nell’immagine acquisita, si seleziona la **regione di interesse (ROI)**:

- volto nell’immagine della scena,
    
- impronta nel frame dello scanner,
    
- iride nel frame dell’occhio, ecc.
    

Per ogni applicazione esistono tecniche specifiche:

- segmentazione del volto (skin detection, forma del viso, bounding box),
    
- segmentazione dell’impronta (separazione area del dito dallo sfondo).
    

Nella slide si vede:

- un’immagine con volto su sfondo complesso,
    
- la versione segmentata con il solo volto,
    
- un’immagine di impronta e la relativa maschera binaria di segmentazione.
    

Una buona segmentazione:

- riduce rumore,
    
- diminuisce il tempo di elaborazione,
    
- aumenta l’accuratezza di feature extraction e matching.
    

---

## **15. Sintesi della Parte B**

La slide finale (pagina 44) riassume i punti visti:

- **variabilità temporale** del tratto (impronte, volto, ecc. lungo mesi/anni);
    
- **variabilità intraclasse** (stesso individuo in condizioni diverse);
    
- **similitudine interclasse** (gemelli, sosia);
    
- **acquisizione** come fase critica:
    
    - qualità del sample,
        
    - informazioni di contesto,
        
    - scelta del sensore;
        
- **misura della qualità** del tratto (quality index),
    
- **enhancement** di segnali/immagini e rischio di **artefatti**,
    
- **segmentazione** come selezione della regione di interesse.
    

---

## **16. Messaggio di fondo della lezione**

Se metti insieme tutta la lezione, il messaggio è:

1. Un sistema biometrico non è solo “un sensore + un algoritmo di matching”.  
    È un’**architettura completa** dove ogni modulo:
    
    - acquisizione,
        
    - controllo qualità,
        
    - feature extraction,
        
    - template management,
        
    - matching,
        
    - decisione,
        
    
    influisce sulla sicurezza globale.
    
2. Per progettare bene, devi:
    
    - capire **come il tratto varia nel tempo**,
        
    - stimare **entro quanto devono essere tollerate le variazioni intraclasse**,
        
    - minimizzare le confusioni da **similitudine interclasse**,
        
    - curare ossessivamente la **qualità dell’acquisizione**,
        
    - usare enhancement e segmentazione in modo consapevole.
        

È su queste basi che, nelle lezioni successive, si potrà parlare in modo serio di **prestazioni quantitative** (FAR, FRR, ROC, EER) e di **design ottimale** dei sistemi biometrici reali.