## **Lezione 2: Proprietà di sicurezza**

### **1. Introduzione**

Le **proprietà fondamentali della sicurezza informatica** descrivono gli obiettivi che un sistema deve perseguire per proteggere informazioni, risorse e servizi dagli attacchi.  
Queste proprietà sono sintetizzate nella celebre **triade CIA** (_Confidentiality, Integrity, Availability_), alla quale si affiancano concetti complementari come **autenticità** e **accountability**.

---

### **2. Riservatezza (Confidentiality)**

La **riservatezza** è la garanzia che le informazioni **non siano accessibili o divulgate a soggetti non autorizzati**.

Due aspetti principali:

- **Confidenzialità dei dati:** le informazioni riservate devono restare note solo agli utenti autorizzati.
    
- **Privacy:** ogni individuo deve poter controllare **quali dati personali possono essere raccolti**, da chi e con quali finalità.
    

Esempi di violazione della riservatezza:

- divulgazione di credenziali o dati sanitari;
    
- intercettazione di comunicazioni private;
    
- furto di database contenenti informazioni sensibili.
    

---

### **3. Integrità (Integrity)**

L’**integrità** garantisce che **dati e programmi siano modificati solo in modo autorizzato e tracciabile**.

Si distingue in:

- **Integrità dei dati:** i contenuti devono restare invariati o essere modificati solo da utenti legittimi.
    
- **Integrità del sistema:** il sistema deve eseguire le sue funzioni in modo corretto anche in presenza di errori o manipolazioni.
    

Concetti correlati:

- **Autenticità:** assicura che un oggetto digitale sia realmente ciò che dichiara di essere.
    
- **Non ripudio:** impedisce a mittenti o destinatari di negare la trasmissione o la ricezione dei dati.
    

Esempi:

- un file alterato da malware → perdita di integrità dei dati;
    
- un sistema compromesso da rootkit → perdita di integrità del sistema.
    

---

### **4. Disponibilità (Availability)**

La **disponibilità** assicura che **i sistemi e le informazioni siano accessibili e utilizzabili da utenti autorizzati** in modo tempestivo e affidabile.

- **Disponibilità del sistema:** il servizio deve restare operativo e accessibile.
    
- **Disponibilità dei dati:** deve essere sempre possibile accedere e utilizzare correttamente le informazioni.
    

Esempi:

- attacco **DDoS** che blocca un server web → violazione della disponibilità;
    
- guasto hardware o blackout senza backup → perdita di accesso ai dati.
    

---

### **5. Proprietà complementari**

#### **Autenticità**

È la capacità di verificare e garantire che un’entità (persona, dispositivo o messaggio) sia realmente quella che dichiara di essere.  
Riguarda la **fiducia** nella validità delle comunicazioni e nella provenienza dei dati.

#### **Accountability**

Indica la possibilità di **attribuire con certezza un’azione a un soggetto** e di tracciarne la responsabilità.  
Si collega al principio di **non ripudio**, in quanto permette di verificare chi ha eseguito una determinata operazione.

---

### **6. La triade CIA**

Le tre proprietà fondamentali — **Confidentiality**, **Integrity**, **Availability** — costituiscono la base di ogni politica di sicurezza.

- Formalizzate nello **standard NIST FIPS 199**  
    (_Standards for Security Categorization of Federal Information and Information Systems_, 2004).
    
- Definite anche nella **RFC 4949** (_Internet Security Glossary, Version 2_, 2007).
    

Queste proprietà definiscono gli **obiettivi di sicurezza** di ogni sistema informatico.

---

### **7. Le sfide della sicurezza delle informazioni**

1. **La sicurezza non è semplice**
    
    - I requisiti sono spesso chiari, ma i meccanismi per realizzarli sono complessi.
        
2. **Ogni meccanismo di sicurezza deve considerare gli attacchi**
    
    - Un sistema può essere violato sfruttando vulnerabilità impreviste.
        
3. **Le procedure di sicurezza possono essere controintuitive**
    
    - Le misure efficaci sono spesso complesse e meno “comode” per l’utente.
        
4. **Scelta del punto di applicazione della sicurezza**
    
    - Decisione su _dove_ applicare i controlli:
        
        - livello fisico (rete, host, perimetro)
            
        - livello logico (software, protocolli, applicazioni)
            

---

### **8. Ulteriori difficoltà operative**

5. **Complessità dei meccanismi di sicurezza**
    
    - Spesso richiedono più algoritmi e protocolli coordinati.
        
    - Dipendono dalla creazione, distribuzione e protezione di **informazioni segrete** (chiavi, certificati).
        
6. **Asimmetria tra attaccante e progettista**
    
    - L’attaccante deve trovare **una sola vulnerabilità**.
        
    - Il progettista deve **eliminarle tutte** per ottenere sicurezza completa.
        

---

### **9. Ostacoli gestionali e culturali**

7. **Scarsa percezione del valore della sicurezza**
    
    - Le organizzazioni investono solo dopo un incidente.
        
8. **Necessità di monitoraggio continuo**
    
    - La sicurezza non è un’attività una tantum, ma richiede controllo costante.
        
9. **Approccio reattivo**
    
    - Spesso la sicurezza viene aggiunta _dopo_ lo sviluppo del sistema.
        
10. **Percezione di “impedimento”**
    

- Le misure di sicurezza vengono viste come ostacolo all’efficienza o alla semplicità d’uso.
    

---

### **10. Livelli di impatto (NIST FIPS 199)**

Lo **standard FIPS 199** definisce tre livelli di impatto sulla sicurezza in caso di violazione di **confidenzialità**, **integrità** o **disponibilità**:

|**Livello**|**Descrizione**|
|---|---|
|**Low**|Impatto limitato, danni minori o temporanei.|
|**Moderate**|Impatto serio su processi o reputazione.|
|**Severe (High)**|Impatto grave o catastrofico sull’organizzazione o sull’individuo.|

---

### **11. Esempi pratici di classificazione**

|**Proprietà**|**Esempio**|**Livello di impatto**|
|---|---|---|
|**Confidenzialità**|Lista dei docenti universitari|Basso|
||File dei voti degli studenti|Alto|
|**Integrità**|Database ospedaliero|Molto alto|
||Forum pubblico online|Basso|
|**Disponibilità**|Sistema di autenticazione per servizi critici|Alto|
||Sito web informativo di una scuola|Basso|

---

### **12. Conclusione**

Le **proprietà di sicurezza** rappresentano la base di ogni architettura difensiva.  
La **triade CIA** fornisce il modello concettuale per comprendere la protezione dei dati e dei servizi.  
Tuttavia, la **complessità, i costi e la variabilità del rischio** rendono la sicurezza un obiettivo dinamico e in continua evoluzione.

> La sicurezza non è mai assoluta, ma il risultato di un equilibrio tra **protezione, usabilità e sostenibilità operativa**.