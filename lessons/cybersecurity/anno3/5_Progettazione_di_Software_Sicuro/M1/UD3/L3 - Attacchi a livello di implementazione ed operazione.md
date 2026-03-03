# **Lezione 3: Attacchi a livello di implementazione ed operazione**

---

### **1. Panoramica della lezione**

Questa lezione conclude il Modulo 1 analizzando gli **attacchi che derivano dalle fasi di implementazione e di operazione** del software.

Dopo aver studiato:

- attacchi progettuali,
    
- ciclo di vulnerabilità,
    

vengono ora esaminati attacchi che nascono:

- da errori di programmazione,
    
- da configurazioni errate,
    
- da scelte operative deboli,
    
- da comportamenti degli utenti.
    

Gli obiettivi della lezione sono:

- comprendere il funzionamento dei principali attacchi,
    
- individuare le cause tecniche,
    
- analizzare le strategie difensive,
    
- riconoscere la responsabilità dello sviluppatore anche nelle fasi operative.
    

---

### **2. Possibili attacchi**

Gli attacchi vengono divisi in due categorie principali.

#### **2.1 Attacchi in fase di implementazione**

I più comuni sono:

- Buffer overflow
    
- Back door
    
- Parsing error
    

#### **2.2 Attacchi in fase di operazione**

I più comuni sono:

- Denial-of-service
    
- Default accounts
    
- Password cracking
    

---

# **3. Attacchi in fase di implementazione**

---

### **3.1 Buffer overflow attack**

#### **3.1.1 Condizione di buffer overflow**

Una condizione di **buffer overflow** si verifica quando:

> un’applicazione non effettua un adeguato controllo dei limiti (bounds checking) su una stringa e accetta più caratteri di quanti il buffer possa contenere.

Il buffer è una porzione di memoria di dimensione fissa.

Se vengono scritti più dati del previsto:

- la memoria adiacente viene sovrascritta,
    
- il comportamento del programma diventa imprevedibile.
    

#### **3.1.2 Buffer overflow come attacco**

Un **buffer overflow attack** avviene quando:

> un attaccante provoca intenzionalmente un overflow per forzare il programma ad eseguire comandi o azioni non autorizzate.

Questo tipo di attacco è storicamente tra i più pericolosi perché può portare a:

- esecuzione di codice arbitrario,
    
- escalation di privilegi,
    
- compromissione completa del sistema.
    

---

#### **3.1.3 Strategie di difesa**

Le difese includono:

- uso di linguaggi che effettuano automaticamente bounds checking:
    
    - ad esempio Java;
        
- evitare letture di stringhe di lunghezza indeterminata in buffer di lunghezza fissa.
    

Il principio fondamentale è:

> non fidarsi mai della dimensione degli input.

---

### **3.2 Back door attack**

#### **3.2.1 Definizione**

Un **back door attack** si verifica quando:

> un programmatore introduce volontariamente nel software codice speciale che consente di bypassare i controlli di accesso.

Questi punti di accesso nascosti sono chiamati:

> **back door**.

Le back door possono essere inserite per:

- test,
    
- manutenzione,
    
- accesso privilegiato.
    

Ma rappresentano una grave vulnerabilità se rimangono nel sistema finale.

---

#### **3.2.2 Strategia di difesa**

La difesa principale consiste nel:

> controllare attentamente il codice per verificare l’assenza di back door.

Questo implica:

- code review,
    
- auditing del codice,
    
- controlli di sicurezza indipendenti.
    

---

### **3.3 Parsing error attack**

#### **3.3.1 Definizione**

Un **parsing error attack** si verifica quando:

> un’applicazione accetta input da utenti remoti senza effettuare adeguati controlli sintattici e semantici sui dati ricevuti.

Il problema nasce dal mancato controllo dell’input.

#### **3.3.2 Esempio storico**

Un esempio famoso riguarda server web che:

- non controllavano richieste contenenti sequenze `"../"`,
    

permettendo l’accesso a directory non autorizzate.

Questo tipo di vulnerabilità è noto oggi come:

- directory traversal.
    

---

#### **3.3.3 Strategie di difesa**

Le difese includono:

- riuso di codice già controllato e verificato,
    
- test accurato dei comandi e dei dati di input.
    

Il principio fondamentale è:

> validare sempre l’input.

---

# **4. Attacchi in fase di operazione**

---

### **4.1 Denial-of-Service attack**

#### **4.1.1 Definizione**

Un **denial-of-service (DoS) attack** si verifica quando:

> un sistema o un’applicazione diventa inutilizzabile a causa di un flusso eccessivo di richieste o input, negando il servizio agli utenti legittimi.

L’obiettivo non è rubare dati, ma:

- impedire l’uso del sistema.
    

---

#### **4.1.2 Strategie di difesa**

Le difese includono:

- progettare l’allocazione delle risorse in modo efficiente,
    
- evitare consumo eccessivo di risorse,
    
- monitorare l’utilizzo delle risorse,
    
- prevedere meccanismi di gestione del sovraccarico.
    

Questo introduce concetti moderni come:

- rate limiting,
    
- load balancing,
    
- autoscaling.
    

---

### **4.2 Default accounts attack**

#### **4.2.1 Definizione**

Molti sistemi vengono installati con:

- username,
    
- password,
    

di default.

Esempi:

- guest/guest,
    
- field/service.
    

Un **default accounts attack** si verifica quando:

> un attaccante utilizza credenziali di default per accedere al sistema.

---

#### **4.2.2 Strategie di difesa**

Le difese includono:

- rimuovere gli account di default,
    
- controllare il sistema dopo installazioni o aggiornamenti.
    

Questo evidenzia l’importanza della configurazione sicura.

---

### **4.3 Password cracking attack**

#### **4.3.1 Definizione**

Un **password cracking attack** si verifica quando:

> un attaccante indovina password utilizzando programmi automatici di cracking.

Questi programmi:

- utilizzano algoritmi,
    
- usano dizionari di parole comuni,
    
- testano centinaia di migliaia di combinazioni.
    

Password deboli possono essere scoperte:

> in frazioni di secondo.

---

#### **4.3.2 Strategie di difesa**

Le difese dipendono dal ruolo.

##### **Come utente**

- scegliere password intelligenti.
    

##### **Come programmatore**

- utilizzare strumenti che richiedano password robuste.
    

Una **password robusta** è:

- facile da ricordare,
    
- difficile da indovinare.
    

Esempi di strategie:

- offuscare parole in lingue diverse,
    
- usare iniziali di frasi memorizzabili,
    
- includere numeri e simboli.
    

È importante anche:

- evitare il riuso di password già in fase di progettazione,
    
- utilizzare metodi alternativi di autenticazione:
    
    - biometria,
        
    - smart card.
        

---

### **5. Sintesi della lezione**

In questa lezione abbiamo analizzato:

- attacchi a livello di implementazione,
    
- attacchi a livello operativo.
    

Concetti fondamentali:

- molti degli attacchi descritti rappresentano le vulnerabilità più comuni associate alla sicurezza informatica,
    
- la lista presentata non è esaustiva,
    
- molti attacchi avvengono dopo il rilascio del software,
    
- numerosi problemi derivano da decisioni errate prese prima dello sviluppo mentre il software è già in produzione.
    

---

### **6. Conclusione del modulo**

Il Modulo 1 ha introdotto:

- sicurezza del software,
    
- ciclo di vita,
    
- vulnerabilità,
    
- attacchi nelle diverse fasi.
    

Il messaggio finale è:

> la sicurezza deve essere considerata lungo tutto il ciclo di vita del software, dalla progettazione all’operazione.