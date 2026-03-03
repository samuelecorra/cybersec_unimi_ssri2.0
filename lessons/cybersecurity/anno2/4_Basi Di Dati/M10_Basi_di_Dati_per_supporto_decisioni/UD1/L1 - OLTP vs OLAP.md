# **M10 UD1 Lezione 1 - OLTP vs OLAP**

### **1. Introduzione**

Le basi di dati tradizionali sono state progettate per la **gestione efficiente dei dati operativi** (modello **OLTP**, _On-Line Transaction Processing_), ma risultano **poco adatte alle analisi complesse** tipiche delle attività decisionali (_OLAP_, _On-Line Analytical Processing_).

Il linguaggio **SQL**, pur essendo potente per operazioni di gestione, è **limitato dal punto di vista analitico**:  
gli analisti hanno bisogno di strumenti capaci di aggregare grandi volumi di dati storici e di fornire rapidamente **informazioni strategiche**.

Nasce così la necessità di creare **soluzioni dedicate** al supporto delle decisioni, in grado di **trasformare i dati operativi** prodotti dai sistemi OLTP in **informazioni utili** per la pianificazione e l’analisi.

---

### **2. OLTP: On-Line Transaction Processing**

Il sistema **OLTP** gestisce i **processi operativi quotidiani** dell’organizzazione.  
Le sue caratteristiche principali sono:

- Esegue **operazioni predefinite e semplici** (es. inserimento, aggiornamento, cancellazione).
    
- Ogni operazione coinvolge **poche tuple** e agisce su **dati di dettaglio**, sempre **aggiornati**.
    
- Le transazioni devono rispettare le **proprietà ACID** (Atomicità, Consistenza, Isolamento, Durabilità).
    
- L’obiettivo è massimizzare il **throughput** (numero di transazioni completate al secondo).

Esempi: gestione vendite, prenotazioni, contabilità, pagamenti elettronici.

---

### **3. OLAP: On-Line Analytical Processing**

Il sistema **OLAP** è progettato per **analizzare grandi quantità di dati** con lo scopo di supportare le **decisioni strategiche**.

Caratteristiche principali:

- Esegue **operazioni complesse e non predefinite**, formulate liberamente dagli analisti.
    
- Le query coinvolgono **molti dati**, spesso **aggregati o storici**, non necessariamente aggiornati.
    
- Le proprietà ACID non sono rilevanti, poiché si tratta quasi sempre di **operazioni di sola lettura**.
    
- L’obiettivo è ottenere **risposte rapide** a query multidimensionali complesse.

Esempi: analisi delle vendite annuali, confronto di trend, simulazioni di scenari aziendali.

---

### **4. Differenze principali tra OLTP e OLAP**

|**Caratteristica**|**OLTP**|**OLAP**|
|---|---|---|
|**Utente tipico**|Impiegato|Dirigente / Analista|
|**Funzione**|Operazioni giornaliere|Supporto alle decisioni|
|**Progettazione**|Orientata all’applicazione|Orientata ai dati|
|**Dati**|Correnti, aggiornati, dettagliati, relazionali, omogenei|Storici, aggregati, multidimensionali, eterogenei|
|**Uso**|Ripetitivo|Casuale|
|**Accesso**|Read-write, indicizzato|Read-only, sequenziale|
|**Unità di lavoro**|Transazione breve|Interrogazione complessa|
|**Volume di dati**|100 MB – 1 GB|100 GB – 1 TB|
|**Numero utenti**|Migliaia|Centinaia|
|**Metrica di efficienza**|Throughput|Tempo di risposta|

---

### **5. Conflitto tra OLTP e OLAP**

OLTP e OLAP hanno **requisiti tecnici profondamente diversi**, che rendono difficile farli convivere nello stesso ambiente.  
I principali conflitti riguardano:

#### **a) Gestione dei lock**

- OLTP utilizza **molti lock esclusivi** per garantire la consistenza durante transazioni rapide.
    
- OLAP esegue **pochi lock condivisi**, ma su grandi quantità di dati.
    
- Se si tenta di far convivere i due sistemi:
    
    - le transazioni OLTP **rallentano**,
        
    - o le query OLAP **non riescono a eseguire** per mancanza di risorse.

#### **b) Uso degli indici**

- OLTP: utilizza **pochi indici**, solo se strettamente necessari per velocizzare operazioni specifiche.
    
- OLAP: richiede **molti indici** per coprire varie analisi.  
    → se coesistono, o gli OLTP si rallentano, o gli OLAP non dispongono degli indici adeguati.

#### **c) Precomputazione delle query**

- OLTP: raramente pre-calcola query, per evitare problemi di consistenza e carico.
    
- OLAP: **precomputazione essenziale** per ridurre i tempi di risposta, ad esempio tramite **aggregazioni predefinite**.

#### **d) Modello logico**

- OLTP: molte tabelle **normalizzate**, altamente frammentate.
    
- OLAP: **poche tabelle denormalizzate**, pensate per analisi multidimensionali.

---

### **6. Soluzione: separazione degli ambienti**

Poiché i due sistemi hanno **finalità incompatibili**, la soluzione è **separarli** in ambienti distinti:

- **Ambiente OLTP** → dedicato alla gestione operativa.
    
- **Ambiente OLAP (Data Warehouse)** → dedicato all’analisi dei dati storici e aggregati.

Schema semplificato:

```
UTENTI FINALI (Transazioni)       ANALISTI (Query complesse)
        │                                  │
        ▼                                  ▼
     [OLTP]  ───►  [Data Warehouse]  ◄───  [OLAP]
```

Il **data warehouse** riceve i dati dal sistema OLTP, li **integra, pulisce e aggrega**, rendendoli disponibili per le analisi decisionali.

---

### **7. Sintesi finale**

In questa lezione abbiamo visto:

- I **limiti** delle basi di dati relazionali rispetto all’analisi dei dati.
    
- Le differenze fondamentali tra **OLTP** (gestione operativa) e **OLAP** (analisi decisionale).
    
- Le ragioni per cui è necessario **separare** i due ambienti e introdurre i **data warehouse** come ponte tra gestione e strategia.

**In sintesi:**  
OLTP e OLAP rispondono a esigenze opposte — l’uno garantisce **efficienza transazionale**, l’altro **capacità analitica** — e solo la loro **integrazione tramite data warehouse** consente di trasformare i dati aziendali in **conoscenza utile per le decisioni strategiche**.

---


![](imgs/Pasted%20image%2020251125055259.png)

