# **M10 UD1 Lezione 2 - Il Data Warehouse**

### **1. Introduzione**

Il **data warehouse** è una **base di dati progettata per il supporto alle decisioni (OLAP)**.  
A differenza dei sistemi operazionali (OLTP), non serve per gestire transazioni quotidiane, ma per **analizzare grandi quantità di dati aggregati e storici**, fornendo una **visione integrata e strategica** delle informazioni aziendali.

---

### **2. Caratteristiche principali di un Data Warehouse**

Un data warehouse possiede cinque caratteristiche fondamentali:

#### **a) Integrato**

Riunisce i dati provenienti da **più sorgenti informative preesistenti** (ad esempio, vendite, magazzino, contabilità).  
Per farlo, deve **riconciliare le eterogeneità** tra le varie basi di dati, unificando:

- **nomi** (es. “CodCliente” vs “ID_Cliente”),
    
- **strutture** (formati diversi),
    
- **codifiche** (es. M/F ↔ 0/1).

In questo modo, le informazioni risultano **coerenti e uniformi**.

---

#### **b) In forma aggregata**

I dati operazionali vengono **aggregati** secondo opportune **coordinate di analisi**, come:

- tempo,
    
- area geografica,
    
- tipologia di prodotto,
    
- fascia di mercato, ecc.

Il data warehouse è quindi **orientato ai dati**, non alle applicazioni.  
Mentre le basi OLTP supportano i **processi operativi**, il data warehouse è costruito attorno alle **principali entità informative aziendali** (vendite, clienti, prodotti, ecc.).

---

#### **c) Storico o temporale**

Il data warehouse mantiene le **evoluzioni storiche** dei dati, con un orizzonte temporale di **anni**, non di mesi.  
Questo consente analisi di tipo:

- **comparativo** (es. confronto fra anni diversi),
    
- **previsionale** (trend e andamenti),
    
- **retrospettivo** (analisi delle performance passate).

Al contrario, i sistemi OLTP conservano solo i **valori correnti**, quindi non sono adatti per studi longitudinali.

---

#### **d) Fuori linea e non volatile**

Il caricamento dei dati nel data warehouse avviene **in modo asincrono e periodico** rispetto ai sistemi sorgente:

- durante il giorno vengono eseguite le **operazioni di interrogazione e analisi**;
    
- durante la notte avvengono le **operazioni di caricamento e aggiornamento**.

Questo approccio evita conflitti di accesso con i sistemi operativi e consente di gestire **milioni di record per volta**, mentre nei sistemi OLTP si agisce su poche tuple per operazione.

---

#### **e) Autonoma**

Il data warehouse è **fisicamente separato** dalle basi di dati operative.  
Le ragioni di questa separazione sono:

- motivi **tecnici** (diverse strutture e prestazioni richieste);
    
- **necessità di integrazione** di dati provenienti da più sorgenti;
    
- **conservazione dei dati storici e aggregati**;
    
- **organizzazioni dei dati e metodi di accesso specifici** per l’analisi.

Senza questa separazione, si avrebbe un **degrado generale delle prestazioni**, poiché le operazioni OLAP richiedono elaborazioni molto più pesanti di quelle OLTP.

---

### **3. OLAP e controllo di gestione**

Le applicazioni per il **controllo di gestione** condividono alcune caratteristiche con i sistemi OLAP:

- eseguono **principalmente operazioni di lettura**;
    
- richiedono **aggregazioni** e **analisi su serie storiche**.

Tuttavia, non sono la stessa cosa:

- le applicazioni di controllo di gestione si basano su **report statici e predefiniti**;
    
- l’OLAP consente **interrogazioni dinamiche e ad hoc**;
    
- il controllo di gestione deve **mantenere il dettaglio operativo**, mentre l’OLAP lavora su **livelli di astrazione e sintesi**.

I due sistemi possono **condividere parte delle informazioni**, ma devono essere **progettati separatamente**.

---

### **4. Architettura di un Data Warehouse**

Un’architettura tipica di data warehouse comprende:

1. **Sorgenti dei dati**
    
    - basi di dati operative, file esterni, archivi storici, fogli di calcolo;
    
2. **Sistema di alimentazione (ETL)**
    
    - **Estrazione** dei dati dalle sorgenti;
        
    - **Pulizia e trasformazione** (eliminazione di errori, duplicati e incoerenze);
        
    - **Caricamento** nel data warehouse;
    
3. **Data Warehouse Server**
    
    - server dedicato all’elaborazione OLAP;
        
    - può gestire **viste materializzate** o **data mart** (sottoinsiemi tematici del warehouse);
        
    - contiene **metadati**, cioè informazioni sulla struttura e sul significato dei dati;
    
4. **Strumenti di analisi**
    
    - **analisi multidimensionale** (operazioni interattive di aggregazione e disaggregazione);
        
    - **data mining** (ricerca automatica di correlazioni, pattern e regole dai dati).

---

### **5. Schema architetturale**

Rappresentazione semplificata dell’architettura di un sistema di data warehousing:

```
     +----------------------------------------------+
     |                 Sorgenti dati                |
     |----------------------------------------------|
     |  Basi di dati operative   |  Sorgenti esterne|
     +----------------------------------------------+
                 │ Estrazione / Pulitura / Caricamento
                 ▼
     +----------------------------------------------+
     |              Data Warehouse Server            |
     |   +------------------+  +------------------+  |
     |   |   Data Warehouse |  |     Data Mart    |  |
     +----------------------------------------------+
                 │
                 ▼
     +----------------------------------------------+
     |          Analisi multidimensionale            |
     |              e Data Mining                    |
     +----------------------------------------------+
```

---

### **6. Importanza della qualità dei dati**

Il **successo di un data warehouse** dipende in larga parte dalla **qualità dei dati caricati**.  
Per questo motivo, la fase di **pulizia e trasformazione (ETL)** è cruciale:

- rimuove **inconsistenze**, **duplicati** e **valori anomali**;
    
- uniforma i **formati e le codifiche**;
    
- garantisce **integrità e affidabilità** delle informazioni analitiche.

---

### **7. Sintesi finale**

In questa lezione abbiamo visto:

- la **definizione e le caratteristiche** del data warehouse;
    
- la sua **architettura** e i **principali componenti** (sorgenti, ETL, DW server, strumenti di analisi);
    
- la **differenza** tra sistemi **OLAP** e **controllo di gestione**;
    
- e l’importanza della **qualità dei dati** come condizione indispensabile per ottenere risultati affidabili.

**In sintesi:**  
il data warehouse è il cuore dell’analisi decisionale, una piattaforma che raccoglie, integra e struttura i dati aziendali per permettere una **visione globale e strategica** dell’organizzazione.

---


![](imgs/Pasted%20image%2020251125055337.png)

