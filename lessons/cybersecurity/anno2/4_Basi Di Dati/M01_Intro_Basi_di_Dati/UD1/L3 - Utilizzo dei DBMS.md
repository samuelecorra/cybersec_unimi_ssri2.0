# **M1 UD1 Lezione 3 - Utilizzo dei DBMS**

### **1. Introduzione**

In questa lezione vengono presentati gli aspetti principali legati all’**utilizzo pratico dei DBMS**.  
In particolare, si approfondiscono:

- I **linguaggi per basi di dati**
    
- Le **categorie di utenti** che interagiscono con il sistema
    
- I principali **vantaggi e svantaggi** derivanti dall’uso di un DBMS rispetto a soluzioni più semplici

---

### **2. Linguaggi per basi di dati**

#### **Categorie principali**

I linguaggi per basi di dati si dividono in due categorie fondamentali:

1. **DDL (Data Definition Language)**  
    Linguaggi utilizzati per **definire la struttura** dei dati.  
    Consentono di specificare:
    
    - Gli **schemi logici**, esterni e fisici
        
    - Le **autorizzazioni di accesso** e i **vincoli di integrità**
    
2. **DML (Data Manipulation Language)**  
    Linguaggi dedicati alla **manipolazione dei dati**, permettono di:
    
    - **Definire**, **interrogare** e **aggiornare** le istanze della base di dati
        
    - Gestire i dati già presenti secondo le regole definite dal DDL

Molti linguaggi, come **SQL**, integrano **entrambe le funzionalità**: definizione e manipolazione.

---

### **3. Tipologie di linguaggi per basi di dati**

I linguaggi utilizzati nei DBMS possono assumere forme diverse, a seconda dell’ambiente e del tipo di interazione:

- **Linguaggi testuali interattivi**  
    Consentono di scrivere comandi direttamente in linguaggio testuale (es. **SQL**).  
    Esempio: query scritte manualmente in una console o in un client SQL.
    
- **Comandi immersi in linguaggi ospiti**  
    Le istruzioni SQL possono essere integrate in **linguaggi di programmazione tradizionali** (es. **C**, **Java**, **Cobol**), per creare applicazioni che interagiscono con il database.
    
- **Comandi integrati in linguaggi ad hoc**  
    Linguaggi progettati per funzioni specifiche, come **reporting, grafici, stampe o maschere di input/output**.
    
- **Interfacce amichevoli (GUI)**  
    Sistemi come **Microsoft Access** permettono di costruire e interrogare basi di dati **senza scrivere codice**, grazie a **interfacce grafiche** che generano automaticamente le query.

---

### **4. Categorie di utenti**

In un sistema di basi di dati possono essere individuati diversi ruoli, ciascuno con funzioni e competenze specifiche:

#### **Amministratore della base di dati (DBA)**

- È la figura responsabile della **progettazione, controllo e amministrazione** della base di dati.
    
- Si occupa di:
    
    - Definire gli **schemi logici e fisici**
        
    - Gestire la **sicurezza** e i **privilegi di accesso**
        
    - Controllare **backup, recupero e integrità** dei dati

#### **Progettisti e programmatori delle applicazioni**

- Definiscono e realizzano i **programmi applicativi** che interagiscono con la base di dati.
    
- Collaborano con il DBA per integrare la logica dei dati all’interno dei sistemi software.

#### **Utenti finali**

Divisi in due sottocategorie:

- **Utenti finali terminalisti:**  
    Utilizzano **transazioni predefinite**, cioè programmi già pronti per svolgere compiti specifici (es. inserire ordini, registrare clienti).
    
- **Utenti casuali:**  
    Accedono alla base di dati in modo **non predefinito**, formulando query personalizzate tramite **linguaggi interattivi** o interfacce grafiche.

---

### **5. Vantaggi dei DBMS**

L’adozione di un DBMS comporta numerosi **vantaggi** rispetto a una gestione basata su semplici file system:

- I **dati diventano una risorsa comune**, condivisa da tutte le componenti del sistema informativo.
    
- La **base di dati rappresenta un modello unificato** del mondo reale, accessibile da applicazioni diverse.
    
- Consente una **gestione centralizzata**, favorendo **standardizzazione** e **economie di scala**.
    
- Riduce **ridondanze e inconsistenze** dei dati, migliorandone la qualità complessiva.
    
- Garantisce **indipendenza dei dati**, cioè la possibilità di sviluppare e aggiornare le applicazioni senza modificare la struttura logica della base.

---

### **6. Svantaggi dei DBMS**

Nonostante i vantaggi, l’uso di un DBMS comporta anche alcuni **svantaggi** o limitazioni:

- I DBMS sono **prodotti complessi e costosi**, che richiedono **investimenti elevati** sia diretti (software, hardware) sia indiretti (formazione, manutenzione).
    
- Forniscono una **serie di servizi integrati** non separabili (controllo della concorrenza, sicurezza, recovery, ottimizzazione, ecc.).  
    Quando alcuni di questi servizi non sono necessari, la loro presenza può **ridurre le prestazioni** rispetto a sistemi più semplici.

---

### **7. Sintesi finale**

In questa lezione abbiamo visto:

- I **linguaggi per basi di dati**, con le due categorie principali (**DDL** e **DML**) e le diverse modalità di interazione (testuali, integrate, grafiche).
    
- Le **categorie di utenti** che interagiscono con il DBMS: **DBA**, **programmatori** e **utenti finali**.
    
- I **vantaggi** (integrazione, consistenza, efficienza, indipendenza) e gli **svantaggi** (complessità e costi) legati all’uso di un DBMS.

Questa panoramica conclude l’Unità Didattica 1 e fornisce la base per comprendere, nel modulo successivo, **il modello relazionale e l’algebra relazionale**, cuore teorico e operativo della gestione dei dati nei sistemi moderni.

![](imgs/Pasted%20image%2020251125042829.png)