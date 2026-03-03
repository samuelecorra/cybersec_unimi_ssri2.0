## **Lezione 2: Politiche di controllo degli accessi – MAC**

### **1. Introduzione**

Il controllo degli accessi può essere implementato secondo differenti **politiche di sicurezza**, ognuna con regole e finalità specifiche.  
Le principali sono:

- **DAC – Discretionary Access Control**  
    → L’accesso è controllato in base all’identità dell’utente e alle regole di autorizzazione definite dal proprietario della risorsa.
    
- **MAC – Mandatory Access Control**  
    → L’accesso è determinato da **etichette di sicurezza** e **autorizzazioni**; le regole sono imposte dal sistema e non possono essere modificate dagli utenti.
    
- **RBAC – Role-Based Access Control**  
    → L’accesso dipende dai **ruoli** assegnati agli utenti e dai privilegi associati a ciascun ruolo.
    
- **ABAC – Attribute-Based Access Control**  
    → L’accesso è deciso in base a un insieme di **attributi** (dell’utente, della risorsa e del contesto ambientale).
    

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

---

### **3. Implementazioni pratiche del MAC**

#### **Esempi reali**

- **SELinux (Security-Enhanced Linux)**
    
    - Progetto della **NSA (National Security Agency)**.
        
    - Aggiunge un’architettura MAC al kernel di Linux.
        
    - Integrato ufficialmente nel **kernel principale** da agosto 2003.
        
- **AppArmor (Ubuntu, SUSE Linux)**
    
    - Implementazione alternativa del modello MAC.
        
    - Utilizza **profili di sicurezza** per limitare le operazioni dei processi.
        
- **Microsoft Mandatory Integrity Control (MIC)**
    
    - Introdotto a partire da **Windows Vista** e **Windows Server 2008**.
        
    - Assegna un **livello di integrità** a ciascun processo e file: un processo a livello basso non può modificare oggetti a livello più alto.
        

---

### **4. Implementazione del MAC**

- I **diritti di accesso** vengono concessi da **amministratori di sistema**.
    
- Le autorizzazioni sono basate su una **conoscenza approfondita dei ruoli e delle mansioni** degli utenti.
    
- L’obiettivo è garantire che ciascun utente possa svolgere le proprie attività **senza violare le protezioni**.
    
- Le operazioni di aggiornamento e manutenzione del sistema di sicurezza sono spesso **automatizzate** dal sistema operativo o dal **kernel di sicurezza**.
    
- Quando un utente tenta di accedere a una risorsa, il sistema decide **automaticamente** se concedere o negare l’accesso, in base alle regole predefinite.
    

---

### **5. Varianti del modello MAC**

#### **a. Multilevel Security (MLS)**

- È la forma **originaria** e più semplice del MAC.
    
- Prevede una **gerarchia verticale di livelli di sicurezza** (es. _Non classificato → Riservato → Segreto → Top Secret_).
    
- Un utente può accedere **solo ai dati del proprio livello o inferiori**.
    
- Esempio: un soggetto con autorizzazione “Segreto” può leggere dati “Riservati”, ma non “Top Secret”.
    

#### **b. Multilateral Security**

- Modello più **complesso e orizzontale**.
    
- Le informazioni sono suddivise in **segmenti o compartimenti** (es. progetti, reparti, codici).
    
- Ogni gruppo combina **livelli verticali** con **parole in codice orizzontali**, formando una struttura a matrice.
    
- Garantisce che gli utenti possano accedere **solo ai segmenti per cui sono autorizzati**, anche se si trovano allo stesso livello di sicurezza.
    

---

### **6. Vantaggi e svantaggi del MAC**

#### **Vantaggi**

- **Altissimo livello di sicurezza**: quasi impossibile da manomettere.
    
- Gli utenti non possono modificare le proprie autorizzazioni.
    
- **Integrità dei dati garantita**: nessuna modifica senza permesso esplicito.
    
- Ideale per **ambienti governativi, militari o finanziari**, dove la riservatezza è prioritaria.
    

#### **Svantaggi**

- **Amministrazione complessa**: richiede una pianificazione accurata e aggiornamenti costanti.
    
- Elevato **carico di lavoro** per l’amministratore, che deve:
    
    - monitorare le autorizzazioni;
        
    - aggiungere nuovi utenti o oggetti;
        
    - mantenere aggiornata la classificazione delle risorse.
        
- **Rigidità** operativa: riduce la flessibilità per gli utenti, che non possono condividere liberamente risorse.
    

---

### **7. Sintesi finale**

Il **Mandatory Access Control (MAC)** rappresenta il modello di sicurezza più rigoroso e affidabile.  
Ogni decisione di accesso è **centralizzata**, basata su regole non modificabili e su etichette di sicurezza controllate dal sistema.

> Il MAC realizza un equilibrio perfetto tra **protezione dei dati e controllo centralizzato**, ma al prezzo di una minore flessibilità gestionale.  
> È il modello ideale quando la **riservatezza assoluta** e l’**integrità dei dati** sono priorità superiori all’usabilità.