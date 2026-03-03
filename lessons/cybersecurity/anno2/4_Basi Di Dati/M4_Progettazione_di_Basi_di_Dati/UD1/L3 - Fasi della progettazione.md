# **M4 UD1 Lezione 3 - Fasi della progettazione**

### **1. Introduzione**

In questa lezione viene presentata la **metodologia di progettazione delle basi di dati**, ossia l’insieme di fasi, strategie e modelli che guidano il processo di realizzazione di un sistema informativo coerente, efficiente e ben strutturato.  
La progettazione di una base di dati non è un processo unico, ma una **sequenza di passi successivi**, ciascuno con obiettivi e prodotti specifici.

---

### **2. Metodologia di progettazione**

#### **Definizione**

Una metodologia di progettazione è l’insieme delle **regole, procedure e modelli di riferimento** che guidano il progettista nello sviluppo del sistema informativo.  
Essa prevede:

- la **decomposizione** dell’attività di progetto in **passi successivi**;
    
- la definizione delle **strategie da seguire** e dei **criteri di scelta** tra eventuali alternative;
    
- l’utilizzo di **modelli di riferimento** per descrivere i dati in ingresso e in uscita di ciascuna fase.

---

#### **Proprietà di una buona metodologia**

Una metodologia efficace deve garantire:

1. **Generalità**, ossia applicabilità a diversi tipi di applicazioni e sistemi.
    
2. **Qualità del prodotto**, intesa come correttezza, completezza e ottimizzazione delle risorse impiegate.
    
3. **Facilità d’uso**, con strategie e modelli comprensibili e pratici per chi progetta.

---

### **3. Le tre fasi della progettazione di una base di dati**

Il processo di progettazione si articola in **tre fasi principali**, disposte in sequenza logica (a cascata):

1. **Progettazione concettuale**
    
2. **Progettazione logica**
    
3. **Progettazione fisica**

---

### **4. Progettazione concettuale**

La **progettazione concettuale** è la fase che traduce i **requisiti informali** (raccolti e analizzati precedentemente) in una **rappresentazione formale e completa** del contenuto informativo, **indipendente dal DBMS** che verrà utilizzato.

Si basa su un **modello concettuale dei dati**, come il **modello Entità–Relazione (E–R)**, che permette di rappresentare le informazioni e le loro relazioni in modo chiaro e astratto.

**Obiettivi principali:**

- rappresentare in modo **completo e coerente** il contenuto informativo;
    
- **non preoccuparsi ancora** delle modalità di memorizzazione o efficienza del DBMS.

**Risultato:**

> Uno **schema concettuale**, ossia una descrizione ad alto livello dei dati e delle relazioni logiche tra essi.

---

### **5. Progettazione logica**

La **progettazione logica** traduce lo **schema concettuale** in una rappresentazione compatibile con il **modello logico del DBMS** scelto (ad esempio, relazionale, gerarchico o a oggetti).

Questa fase mira a ottenere una struttura che sia **efficiente nell’esecuzione delle operazioni** previste dagli utenti, ma ancora **indipendente dai dettagli fisici** di memorizzazione.

**Obiettivi principali:**

- rappresentare i dati in forma adatta al DBMS;
    
- considerare l’**ottimizzazione delle operazioni** di accesso e modifica dei dati;
    
- mantenere l’**indipendenza fisica** dal supporto di memorizzazione.

**Risultato:**

> Uno **schema logico**, che definisce tabelle, attributi, chiavi e relazioni secondo il modello del DBMS.

---

### **6. Progettazione fisica**

La **progettazione fisica** completa il lavoro traducendo lo **schema logico** in una **specifica dettagliata dei parametri fisici** necessari alla memorizzazione e all’accesso ai dati.  
In questa fase si tiene conto delle **caratteristiche concrete del DBMS** adottato e delle **risorse hardware** disponibili.

**Obiettivi principali:**

- definire la **struttura fisica dei file** e dei **metodi di accesso**;
    
- ottimizzare le **prestazioni del sistema** (tempi di risposta, spazio occupato, throughput).

**Risultato:**

> Uno **schema fisico**, che costituisce la base di dati effettivamente utilizzabile dal sistema.

---

### **7. Relazione tra le tre fasi**

Le tre fasi sono strettamente collegate:

$$  
\text{Requisiti} \rightarrow \text{Schema Concettuale} \rightarrow \text{Schema Logico} \rightarrow \text{Schema Fisico}  
$$

- La **progettazione concettuale** si concentra sul **contenuto informativo**.
    
- La **progettazione logica** si focalizza su **come rappresentare i dati nel DBMS**.
    
- La **progettazione fisica** definisce **come i dati saranno realmente memorizzati e gestiti**.

---

### **8. Utilizzo dei requisiti nelle tre fasi**

#### **Fase 1 – Progettazione concettuale**

- Utilizza principalmente le **specifiche sui dati** derivate dall’analisi dei requisiti.
    
- Le **specifiche sulle operazioni** vengono considerate solo per verificare che lo schema contenga tutte le informazioni necessarie alle funzioni richieste.

---

#### **Fase 2 – Progettazione logica**

- Lo **schema concettuale** fornisce il punto di partenza, riassumendo i requisiti sui dati.
    
- Le **specifiche sulle operazioni** e le **previsioni di carico applicativo** vengono integrate per ottimizzare le prestazioni delle query e delle operazioni più frequenti.

---

#### **Fase 3 – Progettazione fisica**

- Utilizza come input lo **schema logico** e le **specifiche operative** per ottimizzare tempi di accesso e memorizzazione.
    
- Le scelte riguardano indici, modalità di accesso ai dati, buffer e parametri di gestione del DBMS.

---

### **9. Relazione con il DBMS**

|**Fase**|**Dipendenza dal DBMS**|**Descrizione**|
|---|---|---|
|**Progettazione concettuale**|Indipendente|Non tiene conto di alcuna codifica o struttura del DBMS.|
|**Progettazione logica**|Dipende dalla **categoria** del DBMS (es. relazionale)|Tiene conto del modello logico dei dati, ma non del software specifico.|
|**Progettazione fisica**|Dipende dallo **specifico DBMS**|Si basa sui criteri fisici di organizzazione dei dati propri del sistema utilizzato.|

---

### **10. Prodotti della progettazione**

Al termine del processo di progettazione, si ottengono tre prodotti principali:

1. **Schema concettuale** → rappresentazione ad alto livello del contenuto informativo, utile per documentazione e verifica.
    
2. **Schema logico** → descrizione concreta dei dati e delle loro relazioni, usata per interrogazioni e aggiornamenti.
    
3. **Schema fisico** → base di dati reale, pronta all’uso operativo.

---

### **11. Sintesi finale**

**Abbiamo visto:**

- il **concetto di metodologia di progettazione**;
    
- le **tre fasi principali** della progettazione delle basi di dati:
    
    - **progettazione concettuale**;
        
    - **progettazione logica**;
        
    - **progettazione fisica**;
    
- il **ruolo dei requisiti** e la loro evoluzione nel processo di progettazione;
    
- la **dipendenza progressiva** dal DBMS attraverso le tre fasi.

**Ricorda:**

> I risultati di ogni fase non sono semplici passaggi intermedi, ma **parti integranti del prodotto finale**.  
> Ogni schema (concettuale, logico e fisico) costituisce un livello essenziale della rappresentazione del sistema informativo e ne garantisce la qualità complessiva.

---


![](imgs/Pasted%20image%2020251125050151.png)