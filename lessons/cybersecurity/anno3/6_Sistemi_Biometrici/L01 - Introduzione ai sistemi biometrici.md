# **Lezione 1: Introduzione ai sistemi biometrici**

---

### **1. Panoramica della lezione**

Questa prima lezione ha un obiettivo fondante:

1. **Introdurre i sistemi biometrici**
    
    - che cos’è la biometria e perché è diversa da password e chiavi,
        
    - che differenza c’è tra autenticazione e identificazione,
        
    - quali sono i vantaggi/svantaggi dei tratti biometrici,
        
    - come è strutturato un sistema biometrico “dentro”.

---

### **2. Introduzione alla biometria (Parte A)**

#### **2.1 Un nuovo modo di identificare e autenticare**

Tradizionalmente l’identificazione/autenticazione si basa su tre categorie:

1. **Qualcosa che sai**
    
    - password, PIN, risposta a una domanda segreta.
    
2. **Qualcosa che hai**
    
    - chiavi fisiche,
        
    - badge o smart card,
        
    - carta di credito, bancomat.
    
3. **Qualcosa che sei / qualcosa che fai**
    
    - **tratti fisici** (iride, impronta, volto, geometria della mano, …),
        
    - **tratti comportamentali** (firma, voce, modo di camminare, ritmo di digitazione, …).

La biometria appartiene a questa terza categoria: **sfrutta direttamente il corpo e il comportamento** come credenziali.

---

#### **2.2 Definizione di biometria**

> **Biometria** = insieme di tecniche automatiche per il riconoscimento degli individui basato sulle loro **caratteristiche fisiche** e **comportamentali**.

Parole chiave:

- **automatiche** → sistemi informatici che acquisiscono, estraggono caratteristiche, confrontano e decidono.
    
- **caratteristiche fisiche/comportamentali** → il corpo e il comportamento dell’utente diventano la “password”.

![](imgs/Pasted%20image%2020260209152331.png)

---

#### **2.3 Riconoscimento, autenticazione, identificazione**

**Riconoscimento dell’identità** = operazione con cui si associa una **identità** a un individuo.

Ci sono due modalità principali:

1. **Verifica dell’identità (Autenticazione)**
    
    - domanda: **“Sono chi dico di essere?”**
        
    - l’utente dichiara una identità (es. username, numero tessera, ID),
        
    - il sistema verifica se il tratto biometrico **corrisponde** a quell’identità.
        
    - tecnicamente: **metodi 1:1 (one-to-one)**.
        
2. **Ricerca dell’identità (Identificazione)**
    
    - domanda: **“Chi sono?”**
        
    - l’utente non dichiara nulla (o la dichiarazione è irrilevante),
        
    - il sistema cerca la sua identità **all’interno di un insieme di identità note**.
        
    - tecnicamente: **metodi 1:N (one-to-many)**.
        

Nell’identificazione possiamo distinguere:

- **problema di identificazione chiuso**:  
    l’individuo è **sicuramente presente** tra le identità note nel database;
    
- **problema di identificazione aperto**:  
    l’individuo **potrebbe non essere presente** nel database.

![](imgs/Pasted%20image%2020260209152419.png)

---

#### **2.4 Autenticazione/Identificazione positiva e negativa**

Possiamo guardare autenticazione e identificazione in modo “positivo” o “negativo”:

- **Autenticazione/Identificazione positiva**
    
    - obiettivo: **confermare** che l’utente sia proprio chi dice di essere.
        
    - es.: sistema che evita che **più persone usino la stessa identità** (un solo utente per un certo badge).
        
- **Autenticazione/Identificazione negativa**
    
    - obiettivo: **escludere** che una persona usi **identità multiple**.
        
    - es.: sistema usato per impedire che una stessa persona si registri con più identità diverse (ad esempio in sistemi governativi o forensi).
        

Esempi dal materiale:

- Telecamere che cercano in tempo reale se chi passa **non è** un terrorista presente in lista → **identificazione negativa**.
    
- Sistema di accesso con iride a un sito militare che verifica che tu sia nella lista degli abilitati → **identificazione positiva**.
    
- Bancomat che verifica se chi usa la carta è il reale proprietario → **autenticazione positiva**.
    

---

#### **2.5 Terminologia attuale**

Sotto l’etichetta generica di **riconoscimento biometrico** si considerano entrambe le funzioni:

- **Autenticazione (1:1)** – verifica.
    
- **Identificazione (1:N)** – ricerca dell’identità.
    

Quando leggi “sistemi di riconoscimento biometrico” devi sempre chiederti: in questo contesto stanno facendo 1:1 o 1:N?

---

![](imgs/Pasted%20image%2020260209152820.png)

---

![](imgs/Pasted%20image%2020260209152758.png)

---

#### **2.6 Perché serve un’autenticazione accurata**

Ogni anno aumentano le applicazioni che richiedono **alta accuratezza** nell’identificazione/autenticazione, ad esempio:

- sblocco del **cellulare**,
    
- uso del **bancomat**,
    
- **logon** al terminale o alla rete,
    
- **accesso** a email, intranet, servizi online,
    
- **operazioni bancarie** allo sportello e online,
    
- accesso a **dati sanitari** o documenti riservati,
    
- **viaggi** (aerei, navali),
    
- accesso allo **stadio**,
    
- uso della **carta di credito**, ecc.

![](imgs/Pasted%20image%2020260209152925.png)

Il numero di situazioni quotidiane in cui **dimostrare chi sei** è critico è in continua crescita. La biometria nasce per rispondere a questa esigenza in modo più robusto.

![](imgs/Pasted%20image%2020260209152948.png)

---

#### **2.7 Metodi classici di autenticazione**

Prima (e ancora oggi) si usano due grandi famiglie di metodi “tradizionali”:

1. **Metodi basati sul possesso** (“qualcosa che hai”) – token based:
    
    - chiave fisica per entrare in laboratorio,
        
    - tessera/badge,
        
    - assegno circolare per ritirare soldi,
        
    - smart card, carta bancomat, ecc.
        
2. **Metodi basati sulla conoscenza** (“qualcosa che sai”):
    
    - login + password,
        
    - PIN,
        
    - risposta a una domanda segreta.
        

Spesso i sistemi sono **ibridi**:

- es.: bancomat → devi **possedere** la carta (token) e **conoscere** il PIN (conoscenza).
    

---

#### **2.8 Problemi dei metodi tradizionali**

##### **2.8.1 Problemi dei metodi basati sul possesso**

Il token (carta, chiave, badge…)

- può essere **perso o rubato**,
    
- può essere **prestato** a qualcuno che non dovrebbe usarlo,
    
- può essere **usato fuori contesto** (es. la carta aziendale passata a un amico).
    

##### **2.8.2 Problemi dei metodi basati sulla conoscenza**

La password:

- può essere **dimenticata**,
    
- può essere **comunicata ad altri** (volontariamente o per social engineering),
    
- può essere **indovinata** con tentativi, soprattutto se è semplice (nome del cane, data di nascita…).

In più:

- una persona media deve ricordare **decine di password/codici** (lo studio citato indica >20),
    
- per comodità si tende a **riutilizzare la stessa password** in più servizi → se uno viene compromesso, **si aprono tutti**.

![](imgs/Pasted%20image%2020260209153013.png)

---

#### **2.9 Metodi biometrici: tratti fisici e comportamentali**

La biometria sposta il focus da “possedere qualcosa” o “sapere qualcosa” a **“essere/fare qualcosa”**.

Possiamo distinguere:

- **Tratti comportamentali**:
    
    - voce,
        
    - firma,
        
    - camminata (gait),
        
    - modo di digitare sulla tastiera,
        
    - gestualità, ecc.
    
- **Tratti fisici (fisiologici)**:
    
    - impronta digitale,
        
    - iride,
        
    - volto (2D/3D),
        
    - geometria della mano,
        
    - vene del dito o della mano, ecc.

Il sistema biometrico acquisisce questi tratti, ne estrae **caratteristiche numeriche** e li usa per riconoscere l’utente.

---

#### **2.10 Confronto qualitativo tra le tre modalità**

Se confrontiamo, in modo qualitativo, il livello di sicurezza delle tre categorie:

- qualcosa che **sai** (password),
    
- qualcosa che **hai** (token),
    
- qualcosa che **sei/fai** (biometria),

![](imgs/Pasted%20image%2020260209153130.png)

si vede che, in generale, i **tratti biometrici** offrono un potenziale livello di sicurezza più alto, soprattutto se combinati a token o password (es. **multi-factor authentication**).

---

#### **2.11 Vantaggi dei sistemi biometrici**

Alcuni dei principali vantaggi:

1. **Il tratto sei tu**:
    
    - non può essere **dimenticato** (non lo lasci a casa come una chiave),
        
    - è più difficile da **prestare** volontariamente.
        
2. **Maggiore resistenza alla falsificazione**:
    
    - è in genere più complesso falsificare un’impronta o un’iride rispetto a una chiave o un documento.
        
3. **Maggiore accuratezza potenziale**:
    
    - l’accuratezza nell’identificazione può essere molto elevata, se il sistema è ben progettato.
        
4. **Possibilità di combinazione con metodi tradizionali**:
    
    - es. impronta + PIN, volto + badge.
        
5. **Possibilità di identificazione negativa**:
    
    - solo i sistemi biometrici permettono in modo naturale di dire “il sistema dimostra che io **non sono** quell’altra persona”.
        
6. **Riduzione della ripudiabilità**:
    
    - diventa difficile sostenere: “qualcun altro ha usato il mio PIN”, perché il tratto biometrico è direttamente legato al corpo dell’utente.
        

---

#### **2.12 Svantaggi dei sistemi biometrici**

Non esiste tecnologia magica, e i sistemi biometrici hanno importanti limiti:

1. **Costo maggiore**:
    
    - sensori specifici (scanner impronte, videocamere IR, etc.),
        
    - software di elaborazione e infrastruttura.
        
2. **Risposta probabilistica**:
    
    - il sistema non “sa” mai con certezza assoluta,
        
    - produce un **matching score** (un grado di somiglianza) e poi decide confrontandolo con una soglia,
        
    - quindi le decisioni non sono perfettamente binarie, ma **probabilistiche**.
        
3. **Questioni di privacy e percezione sociale**:
    
    - alcune persone vivono la biometria come “schedatura”,
        
    - c’è il timore di uso improprio dei dati biometrici (sorveglianza di massa, furto di identità biometrica).
        
4. **Non si possono cambiare facilmente**:
    
    - se ti rubano una password, la cambi;
        
    - se ti rubano un template di impronta, **non puoi cambiare impronte**.
        
5. **Non tutti possiedono tutti i tratti**:
    
    - persone senza alcune dita, con impronte molto usurate, problemi all’iride, assenza di voce, ecc.,
        
    - questi casi devono essere gestiti (esenzioni, metodi alternativi).
        

---

#### **2.13 “Biometrics” vs “Biometry” (inglese)**

In inglese esistono due termini diversi:

- **Biometrics**
    
    - quello che studiamo nel corso,
        
    - metodi di **identificazione automatica** basati su tratti fisici e comportamentali.
        
- **Biometry**
    
    - campo più ampio: applicazione di **statistica** e metodi quantitativi alla **biologia** e alla **medicina** (es. studi statistici su popolazioni, valutazioni di rischio medico, ecc.).
        

In italiano spesso entrambi si traducono come “biometria”, ma nel contesto del corso si usa quasi sempre il significato di **biometrics**.

---

#### **2.14 Le 7 proprietà di un buon tratto biometrico**

Perché una caratteristica sia un **buon tratto biometrico**, deve avere (almeno in buona misura) queste 7 qualità:

1. **Universalità**
    
    - quasi tutti devono possedere il tratto,
        
    - es.: impronta digitale è universalmente presente; l’iride pure, mentre “voce” può mancare in casi particolari.
        
2. **Unicità**
    
    - due persone diverse non devono avere lo stesso tratto (o devono averlo con probabilità trascurabile),
        
    - es.: forma e posizione delle minuzie in un’impronta, pattern dell’iride.
        
3. **Permanenza**
    
    - il tratto non deve cambiare troppo nel tempo,
        
    - alcuni tratti variano poco (iride), altri di più (volto, voce).
        
4. **Misurabilità**
    
    - il tratto deve poter essere **misurato quantitativamente** con sensori e strumenti precisi,
        
    - non basta che esista: bisogna poterlo acquisire in modo affidabile.
        
5. **Performabilità**
    
    - il tratto deve permettere di ottenere un’**accuratezza adeguata**,
        
    - e questa accuratezza deve essere raggiungibile **senza condizioni operative impossibili** (es. non puoi pretendere che l’utente stia perfettamente fermo per 30 secondi ogni volta).
        
6. **Accettabilità**
    
    - misura quanto le persone sono **disposte ad usare** quel tipo di biometria,
        
    - es.: la scansione dell’iride è più “invasiva” di un’impronta sul telefono; il DNA ancora di più.
        
7. **Circonvenzione**
    
    - quanto è difficile **ingannare** il sistema con attacchi fraudolenti,
        
    - es.: foto del volto davanti alla camera, impronta finta in silicone, maschere 3D, deepfake della voce.
        

Queste proprietà saranno un **criterio fondamentale** quando confronteremo i vari tratti (impronta, volto, iride, voce, …).

---

#### **2.15 Sintesi Parte A**

In questa prima parte hai visto:

- che cos’è la **biometria** e come si differenzia dai metodi basati su password o token;
    
- le due modalità di riconoscimento:
    
    - **autenticazione (1:1)**,
        
    - **identificazione (1:N)**,
        
    - e le nozioni di **positivo/negativo**, **aperto/chiuso**;
        
- i **vantaggi** (non si dimentica, difficile da falsificare, possibile identificazione negativa…) e gli **svantaggi** (costo, privacy, irreversibilità del tratto) dei sistemi biometrici;
    
- le **7 caratteristiche** che un tratto deve avere per essere un buon candidato biometrico.

---

### **3. Aspetto e funzionamento dei sistemi biometrici (Parte B)**

#### **3.1 Biometria nell’immaginario collettivo**

La biometria è molto presente nel cinema e nelle serie TV, spesso in modo romanzato:

- scanner di **voce** in _2001: Odissea nello Spazio_,
    
- lettore di **retina**, **iride**, **palmo**, **volto** in diversi film d’azione (es. _Minority Report_, _Mission Impossible_, _X-Men 2_, _I, Robot_),
    
- impronte digitali e DNA in serie come _CSI_, _NCIS_, ecc.

Queste rappresentazioni hanno contribuito a creare l’idea di sistemi biometrici quasi “onnipotenti”, ma nella realtà i sistemi sono:

- più **limitati**,
    
- soggetti a **errori**,
    
- circondati da **vincoli legali** e tecnici.

![](imgs/Pasted%20image%2020260209153313.png)

---

#### **3.2 Sistemi reali in uso da decenni**

Diversi sistemi biometrici reali sono attivi da più di 20 anni:

- **Aeroporto di Schiphol (Paesi Bassi)** – riconoscimento dell’iride (programma Privium per frequent traveller, attivo dal 2001).
    
- **Aeroporto di Francoforte** – sistemi basati su iride (in passato).
    
- **Disney World** – sistemi con **impronte** per legare il biglietto alla persona (Ticket Tag system, utilizzato già dal 1996 e poi esteso a tutti gli ingressi).
    
- **Germania, Olimpiadi 2004** – uso di impronte per il controllo accessi.

Questi sono esempi di sistemi biometrici **deployati su larga scala** e usati quotidianamente.

![](imgs/Pasted%20image%2020260209153341.png)

---

#### **3.3 Automatic Border Control (ABC)**

Un’applicazione cruciale è il **controllo automatico delle frontiere**:

![](imgs/Pasted%20image%2020260209153642.png)

- **Gate ABC**: varchi automatici che combinano:
    
    - lettura di **documento elettronico** (es. passaporto con chip),
        
    - scansione del **volto** e/o delle **impronte**.
        

Esempi:

- sistemi come **VisionBox Face + Fingerprint**,
    
- **Smartgates** in Nuova Zelanda e in molti aeroporti internazionali.
    

Qui la biometria serve per:

- ridurre i tempi di controllo,
    
- aumentare la sicurezza,
    
- gestire grandi flussi di passeggeri.
    

---

#### **3.4 Applicazioni commerciali**

La biometria è sempre più presente in:

- **Sportelli bancomat (ATM)** → impronte o vene per autenticazione,
    
- **Videosorveglianza** con riconoscimento facciale,
    
- **Supermercati** e sistemi di pagamento rapido (link carta + biometria).

![](imgs/Pasted%20image%2020260209153705.png)

---

#### **3.5 Introduzione nel mondo mobile**

Dal 2006 in poi i sensori biometrici sono entrati nei telefoni e smartphone:

- primi telefoni con lettore di impronte,
    
- poi:
    
    - **Apple iPhone** con **Touch ID** (impronta),
        
    - **Samsung Galaxy S5**, **Honor 7**, **Nexus 5/6P** con sensori di impronte,
        
    - più tardi **Face ID** (riconoscimento facciale 3D) sugli iPhone recenti.
        

Oggi la biometria su smartphone è diventata:

- **standard** per sblocco e pagamenti,
    
- fortemente **accettata** dagli utenti.

![](imgs/Pasted%20image%2020260209153726.png)

---

#### **3.6 Applicazioni particolari**

Oltre ai casi “classici” ci sono prototipi e dispositivi specializzati:

- serrature intelligenti con impronte,
    
- lucchetti smart,
    
- dispositivi portatili militari con sensori biometrici,
    
- sistemi di autenticazione basati su **vene del dito** o della mano (es. tecnologie Hitachi).

Questi mostrano come i tratti biometrici possano essere integrati in molti oggetti quotidiani.

![](imgs/Pasted%20image%2020260209153802.png)

---

#### **3.7 Tipologie di applicazioni: forensi, governative, commerciali**

Possiamo classificare le applicazioni biometriche in tre macro-categorie:

1. **Forensi**
    
    - identificazione di **corpi**,
        
    - investigazione e **criminologia**,
        
    - determinazione di **parentela**,
        
    - gestione di grandi database di impronte:
        
        - es.: sistema **IAFIS** dell’FBI, con decine di milioni di impronte (citati ~47 milioni × 10 dita) e >50.000 accessi/giorno.
    
2. **Governative**
    
    - carte d’identità e **passaporti biometrici**,
        
    - patenti di guida,
        
    - controllo delle **frontiere** (programma US-VISIT con face + fingerprint, con centinaia di milioni di transiti l’anno),
        
    - votazioni elettroniche,
        
    - carte sanitarie con protezione di dati sensibili.
        
3. **Commerciali**
    
    - ATM, sistemi di pagamento,
        
    - controllo accessi ad aziende e edifici,
        
    - **telefoni cellulari**,
        
    - e-commerce, internet banking,
        
    - smart card.
        

Queste applicazioni hanno requisiti diversi in termini di:

- accuratezza,
    
- velocità,
    
- accettabilità sociale,
    
- costi.
    

---

#### **3.8 Trend di mercato**

Le statistiche mostrano che:

![](imgs/Pasted%20image%2020260209154246.png)

- il **mercato globale della biometria** è in forte crescita (valori progressivi in miliardi di dollari negli anni successivi),
    
- sia le biometrie **fisiologiche** sia quelle **comportamentali** stanno espandendosi:
    
    - le biometrie comportamentali (firma dinamica, pattern di digitazione, comportamento di navigazione) stanno diventando sempre più importanti.

![](imgs/Pasted%20image%2020260209154316.png)


In particolare, nel mondo **mobile**:

![](imgs/Pasted%20image%2020260209154341.png)

- la **percentuale di smartphone con sensore biometrico** è passata da circa il 60–70% a valori vicini all’80–80+%,
    
- secondo un report Cisco 2022, circa l’**81% degli smartphone** ha la biometria **abilitata**.
    

Questo conferma che la biometria è diventata una **tecnologia mainstream**.

---

### **4. Funzionamento interno di un sistema biometrico**

#### **4.1 Enrollment e riconoscimento**

Un sistema biometrico lavora tipicamente in due fasi:

##### **4.1.1 Fase di Enrollment (inserimento)**

![](imgs/Pasted%20image%2020260209154404.png)

- L’utente interagisce con il sistema per la **prima volta**.
    
- Il tratto biometrico (impronta, volto, iride…) viene:
    
    - **acquisito** tramite un sensore,
        
    - elaborato per estrarre caratteristiche,
        
    - memorizzato come **documento biometrico** o **template** in un database.
        

Questa è la fase in cui il sistema “impara” come sei.

##### **4.1.2 Fase di Riconoscimento (verifica/identificazione)**

![](imgs/Pasted%20image%2020260209154415.png)

- L’utente si presenta di nuovo:
    
    - il tratto biometrico viene **riacquisito**,
        
    - viene estratto un nuovo set di caratteristiche,
        
    - questo viene confrontato con il template (autenticazione 1:1) o con tanti template (identificazione 1:N).
        
- Se la somiglianza è **sufficientemente alta** (matching score sopra una soglia), l’accesso è consentito.
    

---

#### **4.2 Sistemi di Pattern Recognition: analogia generale**

Un sistema biometrico è un caso particolare di **sistema di riconoscimento di pattern**:

![](imgs/Pasted%20image%2020260209154537.png)

- **Fase di training (addestramento)**:
    
    - acquisisci campioni (es. immagini di mele, pere, banane),
        
    - estrai caratteristiche (colore, forma, texture),
        
    - costruisci un modello o popolazione di riferimento,
        
    - memorizzi nel database.
        
- **Fase di testing (utilizzo)**:
    
    - acquisisci un nuovo campione,
        
    - estrai le caratteristiche,
        
    - confronti con il database,
        
    - ottieni un **outcome** (“è una mela”, “è Samuele”, “non è nessuno della galleria”).
        

Il sistema biometrico fa la stessa cosa, ma con **volti, impronte, iridi, voci…** anziché frutta.

---

#### **4.3 Fasi e terminologia biometrica**

Possiamo raffinare la pipeline con la terminologia specifica:

![](imgs/Pasted%20image%2020260209154555.png)

1. **Tratto (Trait)**
    
    - la caratteristica fisica o comportamentale “grezza” (es. il dito, il volto).
        
2. **Campione (Sample)**
    
    - il dato acquisito dal sensore:
        
        - immagine dell’impronta,
            
        - foto del volto,
            
        - segnale audio della voce, ecc.
            
3. **Caratteristiche (Features)**
    
    - informazioni numeriche estratte dal campione:
        
        - minuzie di un’impronta,
            
        - descrittori del volto,
            
        - pattern dell’iride, ecc.
            
4. **Template biometrico (Coding/Template)**
    
    - rappresentazione **compatta** e **codificata** delle caratteristiche,
        
    - è ciò che viene effettivamente memorizzato nel database,
        
    - non è (di solito) l’immagine grezza, ma un vettore di numeri o struttura di dati.
        

Le fasi principali sono:

- **Acquisition** → acquisizione del campione,
    
- **Feature Extraction** → estrazione delle features,
    
- **Coding** → costruzione del template,
    
- **Matching** → confronto tra template (quello dell’utente e quello/i salvato/i).
    

Durante:

- **Enrollment**:
    
    - si acquisisce, si estraggono le caratteristiche, si codifica e si **memorizza** il template.
        
- **Identification/Verification**:
    
    - si acquisisce, si estraggono le caratteristiche, si codifica, poi si fa **matching** con il database e si prende una **decisione Yes/No** (o “quale identità è più probabile”).
        

---

#### **4.4 Matching score e soglia**

Il cuore della decisione biometrica è il **matching score**:

![](imgs/Pasted%20image%2020260209154610.png)

- Il sistema confronta il template dell’utente con quello memorizzato e produce un **punteggio di similitudine**.
    
- Questo punteggio viene confrontato con una **soglia**.
    

Se:

- **matching score ≥ soglia** → la coppia è considerata **sufficientemente simile**, quindi **accettata**.
    
- **matching score < soglia** → la coppia è considerata **non abbastanza simile**, quindi **rifiutata**.
    

Esempio:

- soglia impostata al **87%**,
    
- se il matching score è 90% → accesso consentito,
    
- se è 80% → accesso negato.
    

Effetti della soglia:

- se **troppo bassa**:
    
    - “entrano tutti” → molti falsi accettati (**False Accepts**),
        
- se **troppo alta**:
    
    - “non entra nessuno” → molti falsi rifiutati (**False Rejects**).
        

Nel resto del corso studierai come **misurare** e **ottimizzare** questi errori (FAR, FRR, ROC, EER, ecc.).

---

#### **4.5 Sintesi Parte B**

In questa seconda parte hai visto:

- come la biometria è rappresentata nell’**immaginario** (film, serie TV),
    
- esempi di **sistemi reali** in aeroporti, parchi tematici, banche, dispositivi mobili,
    
- alcune categorie di **applicazioni biometriche** (forensi, governative, commerciali),
    
- il **trend di mercato**, con crescita forte e ruolo centrale della biometria nei dispositivi mobili,
    
- la **struttura interna** di un sistema biometrico:
    
    - fasi di **enrollment** e **riconoscimento**,
        
    - concetti di **tratto, campione, caratteristiche, template**,
        
    - pipeline di **pattern recognition**,
        
    - concetto di **matching score** e **soglia**.
        

Questa lezione “Lezione 1” è la base concettuale su cui si appoggerà tutto il resto del corso: d’ora in avanti, quando parleremo di impronta, volto, iride, database e performance, useremo esattamente i concetti introdotti qui.