# **M1 UD1 Lezione 1 - Gestione dei dati - introduzione**

### **1. Introduzione al corso**

Uno dei compiti principali dei **sistemi informatici** è la **raccolta, organizzazione e conservazione dei dati**.  
Questo corso è dedicato proprio alla **gestione dei dati tramite sistemi informatici**, concentrandosi su tre aspetti fondamentali:

- **Modelli di rappresentazione** dei dati
    
- **Linguaggi di interrogazione** per l’aggiornamento e il ritrovamento delle informazioni
    
- **Metodologie di progettazione** delle basi di dati

L’obiettivo è imparare a costruire e gestire sistemi che consentano di **archiviare e utilizzare le informazioni in modo efficiente, coerente e sicuro**.

---

### **2. Concetti introduttivi**

In questa prima lezione vengono introdotti i concetti fondamentali di:

- **Dato**
    
- **Informazione**
    
- **Base di dati**
    
- **DBMS (DataBase Management System)**

Prima di arrivare al DBMS, è utile comprendere come un’organizzazione gestisce le proprie informazioni e risorse.

---

### **3. Sistema organizzativo**

Un **sistema organizzativo** è l’insieme di **risorse e regole** che permettono di svolgere in modo **coordinato** le attività necessarie al raggiungimento degli obiettivi di un’organizzazione.

Le risorse di un’organizzazione (ad esempio un’azienda) comprendono:

- **Persone**
    
- **Denaro**
    
- **Materiali**
    
- **Informazioni**

Le informazioni sono una risorsa cruciale: la capacità di **gestirle in modo efficace** è fondamentale per il buon funzionamento del sistema.

---

### **4. Sistema informativo e sistema informatico**

#### **Sistema informativo**

È la **componente del sistema organizzativo** dedicata a:

- **Raccogliere e acquisire** le informazioni
    
- **Archiviare** i dati
    
- **Elaborare** e **distribuire** l’informazione
    
- Favorire **scambio e condivisione** tra le diverse parti dell’organizzazione

#### **Sistema informatico**

È la **parte tecnologica del sistema informativo**, cioè quella che **gestisce le informazioni tramite strumenti informatici** (hardware, software, reti, database).

---

### **5. Gestione delle informazioni**

Nelle attività umane, le informazioni possono essere espresse e memorizzate in diverse forme:

- Idee o concetti informali
    
- Linguaggio naturale
    
- Disegni, grafici, schemi
    
- Codici, anche cifrati o simbolici

I supporti su cui sono memorizzate vanno dalla **memoria umana** alla **carta**, fino ai **supporti digitali**.

Nei **sistemi informatici**, le informazioni vengono rappresentate attraverso **dati**, la cui **interpretazione** produce l’informazione stessa.

---

### **6. Informazione e dato**

#### **Informazione**

È una **notizia o elemento** che permette di acquisire **conoscenza** su fatti, situazioni o stati.

#### **Dato**

È un **elemento simbolico** che rappresenta un’informazione in forma elaborabile da un calcolatore.

**Esempio:**

- “Mario Rossi” e “dipartimento 2” → **dati**
    
- “Mario Rossi è il responsabile del dipartimento 2” → **informazione**, cioè interpretazione dei dati

---

### **7. Base di dati**

Una **base di dati** (database) è una **collezione di dati organizzati** per rappresentare le **informazioni di interesse** di un sistema informativo.  
Essa è **gestita da un DBMS**, che ne cura l’organizzazione, la coerenza e la sicurezza.

In sintesi:

> **La base di dati è il cuore informativo di un sistema informatico.**

---

### **8. DBMS – DataBase Management System**

Un **DBMS** è un **sistema software** che permette di gestire collezioni di dati con le seguenti proprietà:

- **Grandi dimensioni:** molto superiori alla memoria centrale
    
- **Condivisi:** accessibili da più utenti e applicazioni
    
- **Persistenti:** i dati sopravvivono alle singole esecuzioni dei programmi

#### **Funzionalità e vantaggi del DBMS**

Il DBMS garantisce:

- **Affidabilità** → resistenza a guasti hardware/software
    
- **Privatezza dei dati** → accesso controllato e selettivo
    
- **Efficienza** → uso ottimale delle risorse di tempo e spazio
    
- **Efficacia** → supporto produttivo alle attività degli utenti

---

### **9. DBMS vs File System**

Anche un **file system** può gestire dati grandi e persistenti, ma in modo più limitato e meno integrato.

I **DBMS** si differenziano perché:

- **Estendono** le funzionalità del file system, aggiungendo servizi avanzati (integrità, concorrenza, sicurezza, indipendenza)
    
- **Utilizzano i file** solo come **mezzo fisico di memorizzazione**, operando **sopra il file system**
    
- Offrono **interfacce di alto livello** per la manipolazione e l’interrogazione dei dati

In pratica, il file system gestisce **file isolati**, mentre il DBMS gestisce **strutture logiche integrate** che rappresentano un insieme coerente di dati.

---

### **10. Sintesi finale**

In questa lezione abbiamo introdotto i concetti fondamentali:

- **Sistema organizzativo, informativo e informatico**
    
- **Informazione e dato**
    
- **Base di dati**
    
- **DBMS** e i suoi vantaggi rispetto ai file system

Questi concetti costituiscono la base teorica necessaria per comprendere **come i dati vengono organizzati, rappresentati e gestiti nei sistemi informatici moderni**.

![](imgs/Pasted%20image%2020251125042746.png)