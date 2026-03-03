## **Lezione 5: Attacchi e modelli di trust**

### **1. Introduzione**

In questa lezione si approfondiscono due aspetti fondamentali della sicurezza dei sistemi informatici:

1. la **superficie di attacco**, ossia l’insieme dei punti vulnerabili attraverso cui un aggressore può colpire un sistema;
    
2. i **modelli di fiducia (trust)**, che determinano quanto e in quali condizioni possiamo considerare sicuri gli attori coinvolti (utenti, organizzazioni, dispositivi).
    

L’obiettivo è comprendere come individuare le vulnerabilità e costruire una **strategia di difesa multilivello**, basata su prevenzione, rilevamento e ripristino.

---

### **2. Superficie di attacco**

La **superficie di attacco** è costituita da **tutte le vulnerabilità raggiungibili e sfruttabili** da un attaccante all’interno o all’esterno di un sistema.

Rientrano nella superficie di attacco:

- **Porte e servizi esposti al Web o alla rete** (HTTP, SSH, API, ecc.);
    
- **Servizi interni accessibili tramite firewall**;
    
- **Codice che elabora dati in ingresso** (email, XML, documenti Office, formati specifici di settore);
    
- **Interfacce di input come moduli web e query SQL**;
    
- **Fattori umani**, come dipendenti con accesso a dati sensibili soggetti a errori o manipolazioni di _social engineering_.
    

In sintesi, ogni punto di interazione con il sistema rappresenta **una potenziale porta d’ingresso per l’attaccante**.

---

### **3. Categorie di superficie di attacco**

La superficie di attacco può essere suddivisa in tre categorie principali:

#### **a. Superficie di attacco di rete**

Comprende vulnerabilità in:

- protocolli di rete (TCP/IP, DNS, HTTP);
    
- servizi esposti su WAN o Internet;
    
- collegamenti di comunicazione.
    

Esempi:  
attacchi **DDoS**, **spoofing**, **packet injection** o **interruzione delle connessioni**.

#### **b. Superficie di attacco software**

Coinvolge errori o debolezze nel codice:

- applicazioni e utility di sistema;
    
- software di sistema operativo;
    
- servizi web vulnerabili (es. exploit in moduli PHP o CMS).
    

Esempi: **SQL injection**, **buffer overflow**, **remote code execution**.

#### **c. Superficie di attacco umano**

Riguarda le vulnerabilità generate da persone interne o esterne:

- **ingegneria sociale**,
    
- **errore umano**,
    
- **abuso di privilegi** da parte di insider o ex dipendenti.
    

È la componente più difficile da mitigare, poiché coinvolge fattori psicologici e comportamentali.

---

### **4. Attack Tree**

Un **albero di attacco** (_Attack Tree_) è una struttura gerarchica che rappresenta **tutte le possibili tecniche per sfruttare le vulnerabilità di un sistema**.

- Ogni **nodo** dell’albero descrive un obiettivo dell’attaccante.
    
- I **rami** rappresentano i diversi modi per raggiungerlo.
    

Questa rappresentazione fornisce un **modello formale e metodico** per:

- descrivere le minacce,
    
- valutare i rischi,
    
- pianificare le contromisure.
    

Gli attack tree sono strumenti fondamentali nell’**analisi della sicurezza basata sul rischio**, poiché consentono di visualizzare la relazione tra vulnerabilità e obiettivi dell’attacco.

---

### **5. Strategia di sicurezza**

Una **strategia di sicurezza completa** si fonda su tre aspetti complementari:

1. **Specifiche / Politiche di sicurezza**
    
    - Definiscono _cosa_ deve fare il sistema per proteggere le risorse.
        
    - Stabilite tramite regole, policy e pratiche organizzative.
        
2. **Implementazione / Meccanismi di sicurezza**
    
    - Indicano _come_ il sistema realizza le protezioni previste.
        
    - Comprendono controlli di accesso, crittografia, autenticazione, audit.
        
3. **Correttezza / Sicurezza effettiva**
    
    - Verifica che le misure adottate _funzionino davvero_.
        
    - Si basa su testing, validazione e analisi formale.
        

---

### **6. Implementazione della sicurezza**

L’implementazione di una strategia di sicurezza efficace si articola in **quattro fasi complementari**:

#### **a. Prevenzione**

L’obiettivo ideale è **impedire che un attacco abbia successo**.  
Esempi: configurazione sicura dei sistemi, patch management, segmentazione della rete.

#### **b. Rilevamento (Detection)**

Sistemi di **Intrusion Detection (IDS)** individuano attività sospette, come accessi non autorizzati o traffico anomalo.

#### **c. Risposta (Response)**

Se viene rilevato un attacco, il sistema deve **rispondere rapidamente** per limitare i danni, ad esempio:

- bloccare le connessioni malevole,
    
- isolare i nodi compromessi,
    
- attivare procedure di emergenza.
    

#### **d. Ripristino (Recovery)**

Dopo l’incidente, è necessario **riportare il sistema a uno stato integro e operativo**.  
Esempio: utilizzare **backup** per ripristinare dati corrotti o cancellati.

---

### **7. Assurance e valutazione di sicurezza**

#### **Assurance (Garanzia di sicurezza)**

È il livello di fiducia che un’entità (sistema, software, organizzazione) soddisfi realmente i requisiti di sicurezza dichiarati.  
Si basa su **evidenze concrete** ottenute tramite:

- metodologie di sviluppo sicuro,
    
- progettazione formale,
    
- analisi statica e dinamica,
    
- testing accurato.
    

#### **Valutazione (Evaluation)**

È il processo di **verifica e validazione** delle misure di sicurezza adottate rispetto a criteri predefiniti.  
Include:

- test funzionali e di penetrazione,
    
- ispezioni di codice,
    
- analisi matematiche o formali.
    

📘 **Definizione (NIST Computer Security Handbook, 1995):**

> L’assurance è il grado di fiducia che le misure di sicurezza, tecniche e operative, funzionino come previsto per proteggere il sistema e le informazioni che esso elabora.

---

### **8. Trust e sicurezza informatica**

La **fiducia (trust)** rappresenta la **certezza che un’entità si comporti in modo coerente con la politica di sicurezza**, senza pregiudicare la protezione del sistema.

- È sempre **limitata** a specifiche funzioni o contesti.
    
- Si applica sia a **persone** che a **sistemi o organizzazioni**.
    

Un’entità A **si fida** di un’entità B se:

> A presume che B agisca esattamente come A si aspetta, secondo regole definite.

Il termine _entità_ può riferirsi a:

- componenti hardware o software,
    
- moduli o apparati,
    
- siti, reti o organizzazioni.
    

---

### **9. Fiducia negli individui**

Le organizzazioni devono gestire la fiducia sia per gli **utenti interni** (dipendenti, collaboratori) sia per quelli **esterni** (clienti, fornitori).

Due aree fondamentali:

#### **a. Sicurezza delle risorse umane**

- I requisiti di sicurezza devono essere **integrati in ogni fase del ciclo lavorativo**: assunzione, gestione, cessazione.
    
- Ogni dipendente deve essere consapevole delle **responsabilità legate alla protezione delle informazioni**.
    
- Va assegnata la **proprietà dei dati** a persone competenti, con verifica della loro comprensione e accettazione.
    

#### **b. Sensibilizzazione e formazione**

- Tutto il personale deve ricevere **formazione periodica sulla sicurezza**.
    
- La consapevolezza dei rischi è una difesa tanto importante quanto le contromisure tecniche.
    
- Una forza lavoro consapevole riduce drasticamente la superficie di attacco umana.
    

---

### **10. Fiducia nelle organizzazioni**

Le organizzazioni moderne si basano su **rapporti di fiducia reciproca** con altre entità — ad esempio:

- fornitori di servizi cloud,
    
- partner di filiera,
    
- enti pubblici e privati interconnessi.
    

Secondo la **NIST SP 800-39 (Managing Information Security Risk, 2011)**, tali relazioni possono essere:

- **Formalizzate**, tramite contratti, SLA, accordi di servizio o memorandum d’intesa;
    
- **Scalabili**, a livello intra- o inter-organizzativo;
    
- **Semplici (bilaterali)** o **complesse (molti-a-molti)**, in reti di cooperazione estese.
    

La gestione della fiducia dipende da:

- criticità delle informazioni condivise,
    
- storia e reputazione dei partner,
    
- livello di rischio accettabile per ciascuna organizzazione.
    

---

### **11. Stato dell’arte**

**Cosa sappiamo fare bene:**

- Proteggere efficacemente sistemi **semplici e isolati**.
    
- Difendere la complessità **tramite isolamento e sanificazione** dei componenti.
    

**Cosa ancora non sappiamo fare:**

- Rendere **sicuro un sistema complesso** nel suo insieme.
    
- Proteggere infrastrutture **grandi e interconnesse** senza segmentarle.
    
- Mantenere la sicurezza **durante cambiamenti continui** di tecnologia, utenti e processi.
    

---

### **12. Conclusione**

L’efficacia della sicurezza dipende da **quanto conosciamo la nostra superficie di attacco** e **quanto possiamo fidarci** dei soggetti e delle organizzazioni coinvolte.  
Solo integrando controlli tecnici, consapevolezza umana e governance del trust è possibile costruire sistemi realmente resilienti.

> La sicurezza non è un muro, ma una rete di fiducia consapevolmente gestita.

---
