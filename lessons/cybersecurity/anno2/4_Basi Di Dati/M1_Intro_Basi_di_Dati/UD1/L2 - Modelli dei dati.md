# **M1 UD1 Lezione 2 - Modelli dei dati**

### **1. Dati nei DBMS**

All’interno di un sistema di basi di dati esistono **diversi livelli di descrizione e rappresentazione** dei dati.  
Gli utenti e i programmi applicativi interagiscono con i dati attraverso una **struttura logica di alto livello**, detta **modello dei dati**, senza doversi preoccupare di come le informazioni siano fisicamente memorizzate nel file system.

Il **modello dei dati** è quindi il punto di riferimento concettuale che permette di **organizzare e comprendere** la struttura dell’informazione in un DBMS.

---

### **2. Modello dei dati**

Un **modello dei dati** è un insieme di **concetti e costrutti** utilizzato per **organizzare e rappresentare i dati di interesse**.  
Fornisce **meccanismi di strutturazione** (o costruttori di tipo) che consentono di definire **nuovi tipi di dati** a partire da **tipi elementari predefiniti**.

Il modello più diffuso è il **modello relazionale**, basato sul costruttore **relazione** (cioè tabella), che permette di definire **insiemi di record omogenei**.

**Esempio:**

CORSI

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

AULE

|Piano|Nome|
|:--|:--|
|Primo|Delta|
|Primo|Gamma|
|Terra|Beta|
|Terra|Alfa|

---

### **3. Altri modelli di dati**

Oltre al modello relazionale, esistono altri modelli sviluppati in epoche differenti:

- **Modello gerarchico** → basato su **strutture ad albero** (anni ’60)
    
- **Modello reticolare** → basato su **grafi** (anni ’70)
    
- **Modello a oggetti** → estende il modello relazionale introducendo i concetti della **programmazione a oggetti** (anni ’80)

Tutti questi modelli, incluso quello relazionale, sono **modelli logici**, ossia **astrazioni** che rappresentano l’organizzazione dei dati senza riferirsi direttamente alle strutture fisiche di memorizzazione.

---

### **4. Modelli logici e concettuali**

#### **Modelli logici**

- Utilizzati nei **DBMS** per l’organizzazione dei dati.
    
- Sono il riferimento per i **programmi e le query** che interagiscono con il database.
    
- Sono **indipendenti dalle strutture fisiche** di memorizzazione.

#### **Modelli concettuali**

- Utilizzati nelle **fasi preliminari di progettazione** di una base di dati.
    
- Rappresentano i **concetti del mondo reale** senza dipendere da una particolare organizzazione logica o fisica.
    
- Sono **completamente indipendenti da ogni sistema o DBMS**.

---

### **5. Schemi e istanze**

Ogni base di dati contiene due componenti fondamentali:

#### **Schema (o componente intensionale)**

- Descrive la **struttura dei dati**.
    
- È **stabile nel tempo**, poiché definisce la forma generale della base di dati.

#### **Istanza (o stato, o componente estensionale)**

- Rappresenta i **valori effettivi dei dati** in un dato momento.
    
- È **variabile nel tempo**, perché i dati possono essere inseriti, modificati o cancellati.

**Esempio nel modello relazionale**

- **Schema di una relazione:** intestazione (nome e attributi).
    
- **Istanza della relazione:** insieme delle righe della tabella.

CORSI

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

---

### **6. Livelli di astrazione nei DBMS**

L’**architettura standard ANSI/SPARC** definisce **tre livelli di astrazione**, ognuno con un proprio **schema**:

1. **Schema logico**  
    Descrive l’intera base di dati attraverso il **modello logico** adottato (es. relazionale).
    
2. **Schema interno**  
    Rappresenta lo schema logico mediante le **strutture fisiche di memorizzazione** (file, indici, pagine).
    
3. **Schema esterno**  
    Definisce, tramite il modello logico, una **vista parziale** della base di dati, corrispondente agli interessi di un particolare utente o gruppo di utenti.

Questa suddivisione permette di separare i diversi punti di vista e garantire una gestione più flessibile e modulare dei dati.

---

### **7. Viste e indipendenza dei dati**

Nei sistemi più moderni, il livello esterno non è sempre esplicitato come schema separato, ma si possono definire **relazioni derivate**, dette **viste**, ottenute da altre tabelle.

**Esempio:**

CORSI

|Aula|Docente|Corso|
|:--|:--|:--|
|Beta|Verdi|Sistemi|
|Gamma|Bianchi|Linguaggi|
|Beta|Neri|Basi di dati|
|Alfa|Rossi|Architetture|

AULE

|Piano|Nome|
|:--|:--|
|Primo|Delta|
|Primo|Gamma|
|Terra|Beta|
|Terra|Alfa|

LEZIONI

|Piano|Aula|Corso|
|:--|:--|:--|
|Terra|Beta|Sistemi|
|Primo|Gamma|Linguaggi|
|Terra|Beta|Basi di dati|
|Terra|Alfa|Architetture|

---

#### **Indipendenza dei dati**

L’articolazione su più livelli garantisce l’**indipendenza dei dati**, ossia la possibilità di modificare un livello senza influenzare gli altri.

- **Indipendenza fisica:**  
    Il livello logico (e quello esterno) è **indipendente dal livello fisico**.  
    Modifiche all’organizzazione fisica (es. struttura dei file, allocazione su disco) **non alterano le applicazioni** che accedono ai dati.
    
- **Indipendenza logica:**  
    Il livello esterno è **indipendente dal livello logico**.  
    È possibile **aggiungere o modificare schemi esterni** o **modificare lo schema logico** mantenendo inalterate le viste utente.

---

### **8. Sintesi finale**

In questa lezione abbiamo introdotto i concetti fondamentali:

- **Modello dei dati** e sue tipologie
    
- **Modelli logici e concettuali**
    
- **Schema e istanza**
    
- **Architettura a tre livelli (ANSI/SPARC)**
    
- **Indipendenza fisica e logica dei dati**

Questi concetti rappresentano la base per comprendere **come un DBMS astrae, organizza e separa i diversi livelli di rappresentazione dell’informazione**, garantendo flessibilità e coerenza nel tempo.

![](imgs/Pasted%20image%2020251125042810.png)