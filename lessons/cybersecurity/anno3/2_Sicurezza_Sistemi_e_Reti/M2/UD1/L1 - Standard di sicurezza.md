Questo secondo modulo introduce i **fondamenti teorici e pratici della sicurezza informatica**, fornendo una visione strutturata dei **principali standard di riferimento**, delle **tipologie di minaccia** e delle **strategie di difesa**.  
Si analizzano i **principi di progettazione sicura** e si approfondisce il concetto di **malware**, nelle sue diverse forme e finalità.

L’obiettivo è permettere allo studente di comprendere **come nasce un attacco**, **quali vulnerabilità sfrutta** e **quali misure di sicurezza** possono essere adottate per prevenirlo o mitigarne gli effetti.


---

# **UD1 – Standard di sicurezza e concetti di base**

Questa unità introduce le **nozioni fondamentali della sicurezza informatica**, fornendo un linguaggio comune per descrivere **minacce, attacchi e risorse da proteggere**.  
Vengono analizzate le diverse **tipologie di attaccanti e tecniche di aggressione**, insieme ai **principi di valutazione e gestione del rischio** che guidano ogni strategia di difesa.  
L’obiettivo è comprendere come identificare i **punti critici di un sistema** e come applicare **standard e metodologie** per ridurre l’esposizione alle vulnerabilità.


---

## **Lezione 1: Standard di sicurezza**

### **1. Introduzione**

La sicurezza informatica si fonda su un insieme di **standard internazionali** che definiscono le migliori pratiche, i requisiti tecnici e i criteri di gestione del rischio.  
Questi standard sono sviluppati da **organismi riconosciuti a livello mondiale** e costituiscono la base per costruire **sistemi affidabili e certificabili**.

---

### **2. Il ruolo del NIST**

Il **National Institute of Standards and Technology (NIST)** è un’agenzia federale statunitense che si occupa di misure, standard e tecnologie per l’innovazione e la sicurezza.  
Le sue pubblicazioni sono tra le più influenti nel panorama internazionale.

#### **Principali standard e linee guida NIST**

- **FIPS (Federal Information Processing Standards):**
    
    - **FIPS 199:** classificazione della sicurezza delle informazioni e dei sistemi informatici.
        
    - **FIPS 200:** requisiti minimi di sicurezza per i sistemi federali.
        
- **Special Publications (SP):**
    
    - **SP 800-12** – _Computer Security Handbook_
        
    - **SP 800-14** – _Generally Accepted Principles and Practices for Securing Information Technology_
        
    - **SP 800-26** – _Security Self-Assessment Guide for IT Systems_
        

Le serie **NIST SP 800** e **FIPS** rappresentano oggi una **riferimento globale** per la definizione delle politiche di sicurezza e la gestione del rischio.

---

### **3. Standard e organizzazioni Internet**

La sicurezza della rete globale è promossa da enti che definiscono gli standard tecnici dell’infrastruttura di Internet:

- **ISOC (Internet Society):** organizzazione internazionale di professionisti e aziende che si occupa del futuro di Internet.
    
- **IETF (Internet Engineering Task Force):** sviluppa protocolli e standard Internet pubblicati come **RFC (Request for Comments)**.
    
- **IAB (Internet Architecture Board):** organo di supervisione architetturale dell’IETF.
    

📘 **Esempio:**

- _RFC 4949 – Internet Security Glossary (Version 2, 2007)_  
    Contiene la terminologia di riferimento usata nel dominio della sicurezza Internet.
    

---

### **4. Organismi di standardizzazione internazionali**

Oltre al NIST e all’IETF, altri due enti fondamentali operano nella definizione di standard globali:

#### **ITU-T (International Telecommunication Union – Telecommunication Standardization Sector)**

- Agenzia delle Nazioni Unite che coordina reti e servizi di telecomunicazioni a livello mondiale.
    
- Produce standard per tutti i settori delle telecomunicazioni, inclusa la **sicurezza delle reti**.
    

#### **ISO (International Organization for Standardization)**

- Federazione mondiale che riunisce gli enti nazionali di normazione.
    
- In collaborazione con **IEC (International Electrotechnical Commission)**, sviluppa la **famiglia ISO/IEC 27000**, dedicata ai **sistemi di gestione della sicurezza delle informazioni (ISMS)**.
    
- Gli standard ISO sono interconnessi e forniscono un quadro completo di linee guida e requisiti per la gestione della sicurezza.
    

---

### **5. La famiglia ISO/IEC 27000**

La **serie ISO/IEC 27000** rappresenta il principale riferimento internazionale per la **gestione della sicurezza delle informazioni (ISMS)**.  
Essa comprende vari documenti, tra cui:

- **ISO 27001:** specifica i **requisiti per istituire, implementare e migliorare un sistema di gestione della sicurezza delle informazioni** (ISMS). È **certificabile** da enti terzi.
    
- **ISO 27002:** fornisce **linee guida e buone pratiche** per l’applicazione dei controlli di sicurezza. Non è certificabile, ma serve da supporto alla 27001.
    
- **ISO/IEC 27017:2015:** codice di condotta per la sicurezza nei servizi **cloud** basato su ISO 27002.
    
- **ISO/IEC 27018:2014:** protezione dei **dati personali (PII)** nei servizi cloud pubblici.
    

---

### **6. La certificazione ISO 27001 e la guida ISO 27002**

#### **ISO 27001**

- Norma certificabile, utilizzata per implementare un **ISMS** verificabile da enti indipendenti.
    
- Definisce i requisiti organizzativi, procedurali e tecnici per la sicurezza informatica aziendale.
    

#### **ISO 27002**

- Raccolta di **“best practices”** per soddisfare i requisiti della 27001.
    
- Strutturata in **14 capitoli**, ciascuno contenente categorie e controlli specifici.
    
- Revisionata nel **2022**, insieme alla 27001, per allinearsi alle nuove esigenze del cloud e delle minacce emergenti.
    

---

### **7. Contenuti principali della ISO 27002:2013**

I capitoli della norma coprono l’intero ciclo di vita della sicurezza informatica:

1. **Security Policy**
    
2. **Organization of Information Security**
    
3. **Human Resources Security**
    
4. **Asset Management**
    
5. **Access Control**
    
6. **Cryptography**
    
7. **Physical and Environmental Security**
    
8. **Operations Security**
    
9. **Communications Security**
    
10. **Information Systems Acquisition, Development and Maintenance**
    
11. **Supplier Relationships**
    
12. **Incident Management**
    
13. **Business Continuity**
    
14. **Compliance**
    

---

### **8. Caso studio – Architettura di sicurezza in Google**

📗 **Esempio pratico:**  
Google documenta pubblicamente l’architettura di sicurezza della propria infrastruttura globale.  
Gli elementi principali includono:

- **Deployment sicuro dei servizi**;
    
- **Archiviazione cifrata dei dati** con protezione della privacy dell’utente;
    
- **Comunicazione sicura tra i servizi interni**;
    
- **Connessioni sicure con i clienti via Internet (TLS)**;
    
- **Gestione controllata delle operazioni** da parte dei tecnici Google.
    

La sicurezza è integrata su più livelli:  
**infrastruttura → deployment → archiviazione → comunicazione → operazioni.**

---

### **9. Il modello Reference Monitor**

Il **Reference Monitor Model** è un principio cardine dei sistemi di sicurezza.  
Si basa sull’idea di un componente di controllo che **media ogni accesso alle risorse**, garantendo isolamento e tracciabilità.

Funzioni principali (_Guard Tasks_):

1. **Authenticate:** verifica dell’identità.
    
2. **Authorize:** verifica dei permessi.
    
3. **Audit:** registrazione delle attività per garantire accountability.
    

Questi principi vengono implementati oggi in tecnologie come **VM**, **sandboxing**, **hypervisor** e sistemi di controllo accessi multilivello.

---

### **10. Definizioni di sicurezza informatica**

Secondo la **ITU-T Recommendation X.1205 (Overview of Cybersecurity, 2014)**:

> La sicurezza informatica è l’insieme di strumenti, politiche, concetti, linee guida, pratiche e tecnologie utilizzate per proteggere l’ambiente e le risorse dell’organizzazione e degli utenti del cyberspazio.

Le **risorse da proteggere** comprendono:

- dispositivi e infrastrutture informatiche;
    
- personale e processi;
    
- applicazioni, servizi e sistemi di telecomunicazione;
    
- informazioni archiviate o trasmesse.
    

---

### **11. Obiettivi della sicurezza informatica**

Gli obiettivi generali coincidono con le **proprietà fondamentali** del modello CIA:

- **Disponibilità (Availability)**
    
- **Integrità (Integrity)** – include autenticità e non ripudio
    
- **Riservatezza (Confidentiality)**
    

La sicurezza mira a garantire e mantenere queste proprietà nel tempo, nonostante le minacce esterne e interne.

---

### **12. Sottoinsiemi della sicurezza informatica**

1. **Sicurezza delle informazioni:**  
    tutela della riservatezza, integrità e disponibilità dei dati; include autenticità, affidabilità e non ripudio.
    
2. **Sicurezza delle reti:**  
    protezione delle infrastrutture di comunicazione da accessi non autorizzati, modifiche o distruzioni, garantendo il corretto funzionamento dei servizi critici.
    

---

### **13. Conclusione**

Gli **standard internazionali** rappresentano il fondamento della sicurezza informatica moderna.  
Essi forniscono:

- **terminologia unificata**,
    
- **modelli di gestione riconosciuti**,
    
- **procedure di valutazione e certificazione** condivise a livello globale.
    

In sintesi:

> La sicurezza non è improvvisazione, ma conformità a standard, metodologie e processi di miglioramento continuo.
