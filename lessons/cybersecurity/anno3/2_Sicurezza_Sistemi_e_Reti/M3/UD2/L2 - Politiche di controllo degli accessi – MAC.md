## **Lezione 2: Politiche di controllo degli accessi – MAC**

### **1. Introduzione**

Il controllo degli accessi può essere implementato secondo differenti **politiche di sicurezza**, ognuna con regole e finalità specifiche.  
Le principali sono:

- **DAC – Discretionary Access Control**  
    → L’accesso è controllato in base all’identità dell’utente e alle regole di autorizzazione definite dal proprietario della risorsa.

    In questo modello chi possiede i privilegi su una risorsa può, entro i limiti previsti dal sistema, decidere discrezionalmente di concedere o delegare accessi ad altri soggetti.
	    
- **MAC – Mandatory Access Control**  
    → L’accesso è determinato da **etichette di sicurezza** e **autorizzazioni**; le regole sono imposte dal sistema e non possono essere modificate dagli utenti.

    L’utente autorizzato ad accedere a una risorsa non può cambiare autonomamente le etichette né trasferire il proprio diritto di accesso ad altre entità.
	    
- **RBAC – Role-Based Access Control**  
    → L’accesso dipende dai **ruoli** assegnati agli utenti e dai privilegi associati a ciascun ruolo.

    Ogni utente può avere uno o più ruoli, e ciascun ruolo aggrega un insieme di permessi: il controllo viene quindi effettuato verificando se il ruolo dell’utente include l’azione richiesta.
	    
- **ABAC – Attribute-Based Access Control**  
    → L’accesso è deciso in base a un insieme di **attributi** (dell’utente, della risorsa e del contesto ambientale).

    Rispetto ai ruoli, gli attributi permettono decisioni più elastiche: l’accesso può dipendere da proprietà dell’utente, dell’oggetto, dell’operazione e dal contesto dinamico dell’esecuzione.
    

Queste politiche non si escludono a vicenda: un sistema può utilizzare **più modelli contemporaneamente**, applicandoli a diverse categorie di risorse.

---

### **2. Mandatory Access Control (MAC)**

Il **controllo obbligatorio degli accessi (MAC)** stabilisce i permessi **confrontando le etichette di sicurezza** associate alle risorse con le **autorizzazioni di sicurezza** assegnate agli utenti o ai processi.

#### **Caratteristiche principali**

- Ogni **oggetto** (file, processo, risorsa) possiede un livello di sensibilità o classificazione (es. _riservato, confidenziale, segreto_).
    
- Ogni **soggetto** (utente o processo) possiede un’autorizzazione di sicurezza corrispondente.
    
- Il sistema consente l’accesso **solo se le etichette e le autorizzazioni sono compatibili**.
    
- Le regole sono **fissate dal sistema** e non possono essere cambiate dagli utenti.
    

> Il MAC è “obbligatorio” perché anche un utente con permessi elevati non può concedere, di propria iniziativa, l’accesso ad altri.

Le **etichette di sicurezza** rappresentano il grado di criticità o sensibilità delle risorse, mentre le **autorizzazioni di sicurezza** rappresentano il livello di fiducia attribuito ai soggetti. La decisione di accesso nasce dal confronto tra questi due elementi, non dalla scelta discrezionale del proprietario della risorsa.

> 📌 La differenza essenziale rispetto al DAC è la delega: nel DAC il proprietario può spesso condividere o modificare i permessi; nel MAC le regole sono centralizzate e non delegabili dagli utenti.

---

### **3. Implementazioni pratiche del MAC**

#### **Esempi reali**

- **SELinux (Security-Enhanced Linux)**
    
    - Progetto della **NSA (National Security Agency)**.
        
    - Aggiunge un’architettura MAC al kernel di Linux.
        
    - Integrato ufficialmente nel **kernel principale** da agosto 2003.

    - Implementa meccanismi di controllo obbligatorio direttamente a livello di sistema operativo, imponendo vincoli anche ai processi che normalmente avrebbero privilegi elevati.
        
- **AppArmor (Ubuntu, SUSE Linux)**
    
    - Implementazione alternativa del modello MAC.
        
    - Utilizza **profili di sicurezza** per limitare le operazioni dei processi.

    - È un esempio di implementazione MAC orientata alla restrizione delle capacità dei programmi secondo profili predefiniti.
        
- **Microsoft Mandatory Integrity Control (MIC)**
    
    - Introdotto a partire da **Windows Vista** e **Windows Server 2008**.
        
    - Assegna un **livello di integrità** a ciascun processo e file: un processo a livello basso non può modificare oggetti a livello più alto.

    - Il controllo avviene tramite etichette di integrità che il sistema rispetta nelle decisioni di accesso, impedendo modifiche non autorizzate a oggetti più sensibili.
        

---

### **4. Implementazione del MAC**

- I **diritti di accesso** vengono concessi da **amministratori di sistema**.
    
- Le autorizzazioni sono basate su una **conoscenza approfondita dei ruoli e delle mansioni** degli utenti.
    
- L’obiettivo è garantire che ciascun utente possa svolgere le proprie attività **senza violare le protezioni**.
    
- Le operazioni di aggiornamento e manutenzione del sistema di sicurezza sono spesso **automatizzate** dal sistema operativo o dal **kernel di sicurezza**.
    
- Quando un utente tenta di accedere a una risorsa, il sistema decide **automaticamente** se concedere o negare l’accesso, in base alle regole predefinite.

Nel MAC il ruolo del **security manager** è centrale: deve attribuire etichette alle risorse, assegnare autorizzazioni ai soggetti e verificare che gli utenti possano svolgere i propri compiti senza violare le regole di sicurezza.

Anche modifiche e aggiornamenti di componenti protetti devono rispettare la politica MAC. Se un aggiornamento riguarda software di sistema, componenti critici o risorse etichettate, l’operazione deve essere autorizzata secondo le regole definite dal manager di sicurezza.

Il modulo di controllo degli accessi agisce quindi come punto decisionale: riceve una richiesta, confronta etichette e autorizzazioni, applica la politica e restituisce una decisione di concessione o diniego.
    

---

### **5. Varianti del modello MAC**

#### **a. Multilevel Security (MLS)**

- È la forma **originaria** e più semplice del MAC.
    
- Prevede una **gerarchia verticale di livelli di sicurezza** (es. _Non classificato → Riservato → Segreto → Top Secret_).
    
- Un utente può accedere **solo ai dati del proprio livello o inferiori**.
    
- Esempio: un soggetto con autorizzazione “Segreto” può leggere dati “Riservati”, ma non “Top Secret”.

Le politiche MLS organizzano la sicurezza lungo una dimensione verticale: le informazioni e i soggetti sono classificati per livello, e le regole determinano quali flussi siano ammessi tra livelli diversi.
    

#### **b. Multilateral Security**

- Modello più **complesso e orizzontale**.
    
- Le informazioni sono suddivise in **segmenti o compartimenti** (es. progetti, reparti, codici).
    
- Ogni gruppo combina **livelli verticali** con **parole in codice orizzontali**, formando una struttura a matrice.
    
- Garantisce che gli utenti possano accedere **solo ai segmenti per cui sono autorizzati**, anche se si trovano allo stesso livello di sicurezza.

Le politiche multilaterali introducono una separazione orizzontale tra gruppi, reparti, progetti o compartimenti. Due soggetti possono avere lo stesso livello verticale, ma non essere autorizzati agli stessi compartimenti informativi.

Nei sistemi più articolati, livelli verticali e compartimenti orizzontali possono essere combinati in strutture più generali, come reticoli di sicurezza, per esprimere regole di accesso più complesse.
    

---

### **6. Vantaggi e svantaggi del MAC**

#### **Vantaggi**

- **Altissimo livello di sicurezza**: quasi impossibile da manomettere.
    
- Gli utenti non possono modificare le proprie autorizzazioni.
    
- **Integrità dei dati garantita**: nessuna modifica senza permesso esplicito.
    
- Ideale per **ambienti governativi, militari o finanziari**, dove la riservatezza è prioritaria.

Il vantaggio principale è la rigidità controllata: una fotografia corretta del sistema, con etichette e autorizzazioni ben definite, impedisce agli utenti di modificare dati o permessi oltre quanto stabilito dalla politica centrale.
    

#### **Svantaggi**

- **Amministrazione complessa**: richiede una pianificazione accurata e aggiornamenti costanti.
    
- Elevato **carico di lavoro** per l’amministratore, che deve:
    
    - monitorare le autorizzazioni;
        
    - aggiungere nuovi utenti o oggetti;
        
    - mantenere aggiornata la classificazione delle risorse.
        
- **Rigidità** operativa: riduce la flessibilità per gli utenti, che non possono condividere liberamente risorse.

Il costo maggiore è la pianificazione: il security manager deve progettare regole sufficientemente restrittive da proteggere il sistema, ma non così rigide da impedire agli utenti o ai processi di completare il proprio lavoro. Le politiche devono inoltre essere controllate e aggiornate periodicamente, perché ruoli, mansioni, software e risorse cambiano nel tempo.

> ⚠️ Un MAC progettato male può essere sicuro solo in apparenza: se le regole bloccano attività legittime, gli utenti cercheranno scorciatoie operative o richiederanno eccezioni che indeboliscono la politica.
    

---

### **7. Sintesi finale**

Il **Mandatory Access Control (MAC)** rappresenta il modello di sicurezza più rigoroso e affidabile.  
Ogni decisione di accesso è **centralizzata**, basata su regole non modificabili e su etichette di sicurezza controllate dal sistema.

La sua efficacia dipende dalla qualità della classificazione iniziale e dalla capacità di mantenere coerenti etichette, autorizzazioni e mansioni operative.

> Il MAC realizza un equilibrio perfetto tra **protezione dei dati e controllo centralizzato**, ma al prezzo di una minore flessibilità gestionale.  
> È il modello ideale quando la **riservatezza assoluta** e l’**integrità dei dati** sono priorità superiori all’usabilità.
