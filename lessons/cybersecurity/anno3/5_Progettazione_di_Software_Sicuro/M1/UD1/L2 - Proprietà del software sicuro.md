# **Lezione 2: Proprietà del software sicuro**

---

### **1. Panoramica della lezione**

Questa lezione introduce le **proprietà aggiuntive** che un software deve possedere per poter essere considerato **sicuro**.

Il punto di partenza è molto importante: la sicurezza non è una qualità “magica” che esiste in assoluto, ma una proprietà che dipende dal contesto e dalle minacce considerate. A partire da questo, la lezione elenca e spiega i principali **security goals** (obiettivi di sicurezza) che caratterizzano un software sicuro.

---

### **2. Sicurezza come proprietà relativa**

#### **2.1 La sicurezza non è assoluta**

La prima idea da fissare è che:

> **La sicurezza non è una proprietà assoluta di un sistema.**

Non esiste un software “sicuro in generale”: la sicurezza ha senso solo se riferita a:

- un **contesto di applicazione** (dove viene usato il software, per fare cosa, da chi, con quali vincoli),
    
- una certa famiglia di **minacce**,
    
- un certo modello di **attaccante**.
    

Per questo, il significato della sicurezza è sempre **relativo**.

#### **2.2 La domanda fondamentale: contro cosa e da chi?**

La sicurezza può essere definita rispondendo alla domanda:

> **Sicurezza contro cosa e da chi?**

Questa domanda obbliga a esplicitare:

- quali eventi consideriamo pericolosi (furto di dati, manomissione, abuso di privilegi, interruzione del servizio, ecc.),
    
- chi sono gli attori che possono causarli (utente malintenzionato, insider, attaccante esterno, malware, ecc.).
    

In pratica, non posso dire “il mio sistema è sicuro” se prima non ho definito:

- cosa sto cercando di proteggere,
    
- da quali aggressori,
    
- in quali scenari.
    

#### **2.3 Sicurezza e policy di sicurezza**

Dire che un sistema è sicuro implica anche un passaggio organizzativo e logico:

> **La sicurezza implica stabilire una policy di sicurezza.**

Una **policy di sicurezza** è l’insieme di regole e criteri che stabiliscono:

- cosa è permesso e cosa è vietato,
    
- quali utenti possono accedere a cosa,
    
- con quali modalità,
    
- quali vincoli devono essere rispettati.
    

In altre parole, la policy trasforma la sicurezza da “idea generica” a “insieme di requisiti concreti” che poi devono essere progettati, implementati e verificati.

---

### **3. Qualità del software sicuro (security goals)**

#### **3.1 Oltre le qualità generali del software**

Un software, in generale, viene valutato tramite qualità come correttezza, affidabilità, efficienza, usabilità, manutenzione, ecc. (come visto nella lezione precedente).

Tuttavia, un software **sicuro** deve possedere anche ulteriori qualità specifiche, chiamate:

> **security goals** (obiettivi di sicurezza).

Secondo la lezione, un software sicuro deve godere delle seguenti proprietà:

- prevenzione,
    
- tracciabilità e controllo (o auditing),
    
- monitoraggio,
    
- privatezza e confidenzialità,
    
- sicurezza a diversi livelli,
    
- anonimato,
    
- autenticazione,
    
- integrità.
    

Nei paragrafi successivi queste qualità vengono chiarite e motivate.

---

### **4. Prevenzione**

#### **4.1 Significato di prevenzione**

La **prevenzione** consiste nell’idea di:

> **anticipare possibili attacchi.**

La prevenzione è una mentalità progettuale: non si aspetta l’attacco per reagire, ma si cerca di ridurre la superficie di attacco e le possibilità di compromissione prima che l’attacco avvenga.

#### **4.2 Livelli a cui si applica la prevenzione**

La prevenzione può essere realizzata a diversi livelli:

- **a livello di progettazione**  
    cioè nella fase in cui si definiscono architettura, componenti, ruoli, fiducia, confini del sistema, flussi di dati e controlli;
    
- **a livello di implementazione**  
    cioè scrivendo codice con attenzione ai bug e alle vulnerabilità (input validation, gestione sicura della memoria, controllo degli errori, ecc.);
    
- **a livello d’uso**  
    cioè considerando anche come il software verrà effettivamente usato e configurato, e riducendo i rischi derivanti da comportamenti errati o impropri.
    

---

### **5. Tracciabilità e controllo (auditing)**

#### **5.1 Tracciabilità**

La **tracciabilità** è definita come:

> il meccanismo che consente di stabilire in modo inequivocabile la relazione di causa/effetto tra elementi, eventi o processi.

In pratica, un sistema tracciabile permette di ricostruire:

- cosa è successo,
    
- in che ordine,
    
- a causa di quali azioni o eventi,
    
- con quali conseguenze.
    

È una proprietà fondamentale quando si deve capire **come** è avvenuta una violazione e dove intervenire.

La lezione sottolinea che la tracciabilità è:

- **utile per riparare ad un attacco**, perché rende possibile l’analisi post-evento e la comprensione delle responsabilità e dei punti deboli.
    

#### **5.2 Controllo (auditing)**

Con **auditing** si intende un processo di controllo del sistema basato sul confronto tra:

- le attività svolte sul sistema,
    
- le politiche e le procedure stabilite,
    

con l’obiettivo di:

- determinare se tali attività sono conformi,
    
- e, se necessario, suggerire l’opportunità di introdurre migliorie.
    

Un punto importante della lezione è che:

> l’auditing non è una tecnica di prevenzione, ma è utile per dissuadere da potenziali attacchi.

Questo perché sapere che il sistema registra e controlla le azioni può:

- aumentare la probabilità di scoperta dell’attacco,
    
- rendere più rischioso per l’attaccante agire,
    
- e quindi avere un effetto deterrente.
    

---

### **6. Monitoraggio**

#### **6.1 Monitoraggio come auditing in tempo reale**

Il **monitoraggio** viene definito come:

> auditing in tempo reale.

Quindi non si limita a controllare a posteriori, ma osserva il sistema mentre funziona, cercando di rilevare eventi anomali o attacchi in corso.

#### **6.2 Problema dei falsi allarmi**

La lezione evidenzia una criticità pratica:

> sistemi per il monitoraggio possono causare un elevato numero di falsi allarmi.

Questo è un punto tipico dei sistemi di detection: se la sensibilità è alta, il sistema può segnalare come attacco anche eventi legittimi. Se invece si riducono i falsi allarmi, si rischia di non rilevare veri attacchi.

#### **6.3 Livelli di monitoraggio e approcci**

È possibile monitorare un programma a diversi livelli.

La lezione distingue tra:

- **approcci semplici**  
    basati sul controllo di **segnature note** che identificano un attacco in corso.  
    L’idea è simile a quella degli antivirus: si riconosce ciò che è già noto.
    
- **approcci complessi**  
    basati sul monitoraggio del codice mediante **asserzioni**.  
    Qui il software contiene condizioni attese sul comportamento, e il sistema verifica che tali condizioni non vengano violate.
    

---

### **7. Privatezza e confidenzialità**

Questa parte introduce due concetti vicini ma non identici: **privatezza** e **confidenzialità**.

#### **7.1 Privatezza**

La privatezza è definita come:

> il diritto di un individuo di stabilire se, come, quando e a chi l’informazione che lo riguarda può essere rilasciata.

Quindi riguarda:

- controllo da parte della persona,
    
- gestione del ciclo di vita dei dati personali,
    
- decisione sui destinatari e sui tempi del rilascio.
    

È un concetto fortemente legato a diritti e norme, ma si traduce anche in requisiti software: il sistema deve essere progettato per rispettare questo controllo.

#### **7.2 Confidenzialità**

La confidenzialità è definita come:

> la proprietà che assicura che certi servizi e informazioni siano accessibili solo ad utenti autorizzati.

Quindi è più legata all’access control:

- chi può vedere cosa,
    
- chi può ottenere un dato,
    
- quali permessi sono richiesti.
    

In sintesi: la privatezza è centrata sul diritto dell’individuo, la confidenzialità sull’accesso controllato alle informazioni.

---

### **8. Sicurezza a più livelli**

#### **8.1 Dati con diverso grado di segretezza**

Non tutte le informazioni hanno lo stesso livello di sensibilità. Alcuni dati sono più segreti di altri.

La lezione cita esempi di classificazione:

- pubbliche,
    
- confidenziali,
    
- top-secret,
    
- ecc.
    

#### **8.2 Necessità di sistemi multi-livello**

Da questo deriva un requisito:

> è necessario disporre di sistemi in grado di gestire la segretezza di dati e informazioni a più livelli.

Quindi un software sicuro deve saper:

- distinguere categorie di dati,
    
- applicare regole differenti,
    
- garantire controlli differenti in base al livello di classificazione.
    

---

### **9. Anonimato**

#### **9.1 Definizione**

L’anonimato va inteso come:

> la proprietà di mantenere segreta (o non accessibile pubblicamente) l’origine di certi dati.

Quindi non riguarda solo proteggere il contenuto, ma anche:

- chi ha prodotto l’informazione,
    
- da dove arriva un dato,
    
- quale identità o origine è collegata a un’azione.
    

#### **9.2 Proprietà a doppio risvolto**

La lezione sottolinea che:

> l’anonimato è una proprietà con doppio risvolto.

Questo significa che può essere:

- una tutela (privacy, libertà, protezione in contesti sensibili),
    
- ma anche un rischio (copertura per attività illecite o abusive).
    

#### **9.3 Decisioni “non anticipate” del software**

Un punto critico: il software può prendere decisioni sull’anonimato in modi non previsti e non programmati esplicitamente.

La lezione fornisce esempi:

- il **Global Identifier** di Microsoft,
    
- il sistema **Carnivore** dell’FBI.
    

Il messaggio è che sistemi reali possono raccogliere e correlare dati in modo tale da ridurre l’anonimato senza che l’utente ne sia consapevole.

#### **9.4 Responsabilità di architetti e sviluppatori**

La lezione conclude questa sezione con un richiamo forte:

> architetti e sviluppatori debbono pensare attentamente a cosa avviene dei dati collezionati dai propri programmi.

Domanda guida esplicita:

- i dati potrebbero essere usati in cattivo modo? e come?
    

Questo introduce una responsabilità progettuale: non basta raccogliere dati per “funzionare meglio”, bisogna prevedere abusi, riusi e rischi.

---

### **10. Autenticazione**

#### **10.1 Significato**

L’autenticazione è definita come:

> la proprietà di conoscere l’identità di chi accede ad un servizio.

Serve quindi a stabilire “chi è” l’entità che richiede una risorsa.

#### **10.2 Proprietà critica**

La lezione sottolinea che:

> è una proprietà critica per la sicurezza.

Infatti:

- un software sicuro quasi sempre include elementi di autenticazione.
    

Senza autenticazione, è difficile imporre qualunque policy, perché non è possibile distinguere utenti autorizzati da non autorizzati.

#### **10.3 Debolezza dell’autenticazione sul Web**

La lezione evidenzia che:

> scarsa è l’autenticazione su Web.

E porta un esempio specifico:

- la tecnologia **SSL** assicura la protezione dei dati,
    
- ma **non garantisce l’identità del server** a cui ci si è connessi.
    

Questo punto enfatizza che:

- la cifratura del canale non basta se non si è sicuri dell’identità della controparte.
    

#### **10.4 Molte modalità possibili**

Infine viene osservato che:

- esistono parecchi modi per garantire l’autenticazione,
    
- e la scelta dipende dal software di applicazione.
    

Quindi l’autenticazione non è unica: può essere progettata in varie forme, coerenti con requisiti e contesto.

---

### **11. Integrità**

#### **11.1 Integrità come “rimanere lo stesso”**

Nel contesto della sicurezza, l’integrità è intesa come:

> “rimanere lo stesso”, ossia non modificato da quando creato.

Si tratta quindi di garantire che dati e risorse non vengano alterati.

#### **11.2 Vincolo nei sistemi sicuri**

In un sistema sicuro:

- informazioni e risorse non devono essere modificate,
    
- cancellate,
    
- o distrutte
    

in modo:

- non autorizzato,
    
- o improprio.
    

L’integrità riguarda quindi la protezione contro manipolazioni e corruzioni, volontarie o accidentali, che compromettono la correttezza e l’affidabilità del sistema.

---

### **12. Sintesi finale**

In questa lezione abbiamo visto:

- cosa significa **sicurezza del software**,
    
- quali sono gli **obiettivi della sicurezza nel software** (security goals).
    

Concetti da ricordare:

- la sicurezza è una **proprietà relativa**,
    
- si definisce rispondendo alla domanda: **sicurezza contro cosa e da chi?**
    
- implica la definizione di una **policy di sicurezza**.
    

---

### **13. Prossimi passi**

Nelle prossime lezioni il corso affronterà:

- **analisi dei rischi** e pianificazione della sicurezza del software,
    
- **tipi di attacchi** al software,
    
- modalità con cui lo sviluppatore può difendersi **prevenendo l’attacco**.