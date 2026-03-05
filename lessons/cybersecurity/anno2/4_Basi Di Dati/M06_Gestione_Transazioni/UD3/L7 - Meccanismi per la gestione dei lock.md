# **M6 UD3 Lezione 7 - Meccanismi per la gestione dei lock**

### **1. Introduzione**

Il **gestore dei lock** è un modulo fondamentale del **DBMS**, responsabile del coordinamento degli accessi concorrenti ai dati.  
Viene **invocato da tutti i processi** che intendono accedere alla base di dati, e si occupa di concedere o negare i lock in base alla situazione corrente del sistema.

---

### **2. Richieste al gestore dei lock**

Ogni transazione che intende accedere a un oggetto della base di dati può inviare una delle seguenti richieste:

- `r_lock(T, x, errcode, timeout)` → richiesta di lock in **lettura condivisa**
    
- `w_lock(T, x, errcode, timeout)` → richiesta di lock in **scrittura esclusiva**
    
- `unlock(T, x)` → rilascio del lock su `x`

Se il **timeout** scade prima che la richiesta venga soddisfatta, il gestore segnala un **errore** tramite `errcode`.  
In tal caso, di norma la **transazione effettua un rollback** e viene **riavviata**.

---

### **3. Tabelle dei lock**

Il gestore dei lock utilizza **tabelle dedicate** per mantenere le informazioni relative allo stato dei blocchi.  
Queste tabelle, molto frequentemente consultate, vengono mantenute **in memoria centrale** per garantire prestazioni elevate.

Ogni voce della tabella contiene:

- **Due bit di stato** → rappresentano i tre possibili stati del lock (unlocked, r_locked, w_locked)
    
- **Contatore di lock condivisi** → indica quante transazioni hanno richiesto un `r_lock` sullo stesso oggetto
    
- **Contatore dei processi in attesa** → segnala quante transazioni attendono di ottenere il lock

---

### **4. Lock gerarchico (1)**

Nei sistemi reali, i lock possono essere richiesti su **oggetti di diversa granularità** (database, tabelle, tuple, frammenti, ecc.).  
Per gestire questa complessità, si utilizza una **gerarchia di oggetti** organizzata in livelli:

```
Database
│
├── Tabella 1
│   ├── Frammento 1
│   │   ├── Tupla 1
│   │   └── Tupla 2
│   └── Frammento 2
│       ├── Tupla 3
│       └── Tupla 4
│
├── Tabella 2
│   └── ...
│
└── Tabella n
```

Questa struttura consente di applicare **lock più o meno granulari** a seconda delle necessità.

---

### **5. Tipi di lock gerarchici**

I principali tipi di lock in un sistema gerarchico sono:

- **XL (Exclusive Lock)** → corrisponde al `write lock` del protocollo normale.
    
- **SL (Shared Lock)** → corrisponde al `read lock` del protocollo normale.
    
- **ISL (Intentional Shared Lock)** → indica l’intenzione di bloccare in modo condiviso uno dei nodi **discendenti**.
    
- **IXL (Intentional Exclusive Lock)** → indica l’intenzione di bloccare in modo esclusivo uno dei nodi **discendenti**.
    
- **SIXL (Shared Intentional Exclusive Lock)** → chiede un **lock condiviso** sul nodo corrente e, contemporaneamente, indica l’intenzione di bloccare **in modo esclusivo** un nodo discendente.

---

### **6. Protocollo di lock gerarchico**

Le regole fondamentali del **protocollo di lock gerarchico** sono:

1. I lock si **richiedono dalla radice verso il basso** (top-down).
    
2. I lock si **rilasciano dal basso verso l’alto** (bottom-up).
    
3. Una transazione può chiedere un **lock SL o ISL** su un nodo **solo se** possiede un **lock ISL o IXL** sul **genitore**.
    
4. Una transazione può chiedere un **lock IXL, XL o SIXL** su un nodo **solo se** possiede un **lock SIXL o IXL** sul **genitore**.

Queste regole garantiscono la **coerenza dei lock** lungo la gerarchia e prevengono conflitti tra operazioni su livelli diversi.

---

### **7. Esempio di lock gerarchico**

Per richiedere un **lock esclusivo XL** su una **tupla specifica**, una transazione deve:

1. Ottenere un **IXL** sul **database**
    
2. Ottenere un **IXL** sulla **tabella** contenente la tupla
    
3. Ottenere un **IXL** sul **frammento** (partizione) in cui risiede la tupla
    
4. Ottenere infine l’**XL** sulla **tupla stessa**

Dopo l’esecuzione, i lock vengono **rilasciati in ordine inverso**, risalendo la gerarchia.

---

### **8. Scelta della granularità**

La **granularità** alla quale richiedere i lock è una **decisione progettuale** che comporta un **trade-off** tra **parallelismo** e **costo di gestione**:

- **Granularità troppo grande:**
    
    - blocca molte risorse contemporaneamente;
        
    - riduce il parallelismo delle transazioni.
    
- **Granularità troppo fine:**
    
    - richiede troppi lock;
        
    - aumenta il carico del lock manager;
        
    - aumenta il rischio di fallimenti dopo l’acquisizione di numerose risorse.

In generale, si cerca un **compromesso ottimale** tra efficienza e grado di concorrenza.

---

### **9. Lock in SQL:1999**

Nel linguaggio **SQL:1999**, il comportamento delle transazioni rispetto ai lock è formalizzato in due aspetti principali:

#### **a. Tipologia di transazione**

- **Read Only:**
    
    - Non può modificare né il contenuto della base di dati né il suo schema.
        
    - Non può richiedere lock esclusivi.
    
- **Read Write:**
    
    - È il comportamento **predefinito (default)**.
        
    - Può richiedere sia lock condivisi sia esclusivi.

---

#### **b. Livelli di isolamento**

SQL:1999 definisce quattro livelli di **isolamento** che determinano la visibilità dei dati modificati da altre transazioni:

1. **Read Uncommitted**
    
2. **Read Committed**
    
3. **Repeatable Read**
    
4. **Serializable**

Tutti i livelli utilizzano un **2PL stretto per le scritture**, evitando così **perdite di aggiornamento**.  
Le differenze riguardano principalmente il **trattamento delle letture**.

---

### **10. Livelli di isolamento in dettaglio**

#### **a. Read Uncommitted**

- Nessun vincolo.
    
- Le transazioni **non richiedono lock** per leggere.
    
- **Ignorano i lock esclusivi** di altre transazioni.
    
- Possono verificarsi **tutte le anomalie**:
    
    - letture sporche,
        
    - letture inconsistenti,
        
    - aggiornamenti fantasma,
        
    - perdita di aggiornamenti.

---

#### **b. Read Committed**

- Richiede **lock condivisi** per le letture.
    
- Non legge **dati non confermati (non committed)**.
    
- Evita le **letture sporche**, ma non garantisce la **serializzabilità**.
    
- Può ancora verificarsi l’anomalia dell’**aggiornamento fantasma**.

---

#### **c. Repeatable Read**

- Utilizza **2PL stretto anche per le letture**.
    
- Blocca le tuple lette per evitare modifiche concorrenti.
    
- Elimina **tutte le anomalie**, tranne quella dell’**inserimento fantasma**.

---

#### **d. Serializable**

- È il livello di isolamento **più restrittivo** e **predefinito**.
    
- Usa **2PL stretto anche per le letture**, ma con **lock di predicato** (cioè su insiemi di tuple definite da condizioni logiche).
    
- Previene **tutte le anomalie**, incluse le **inserzioni fantasma**.
    
- Garantisce la **serializzabilità completa**.

---

### **11. In sintesi**

In questa lezione abbiamo analizzato i **meccanismi di gestione dei lock** nei DBMS, con particolare attenzione agli aspetti pratici e normativi.

**Abbiamo visto:**

- Il funzionamento del **gestore dei lock** e delle sue richieste (`r_lock`, `w_lock`, `unlock`).
    
- La **struttura delle tabelle di lock** e le informazioni memorizzate.
    
- Il concetto di **lock gerarchico**, con i relativi protocolli e tipi di lock (XL, SL, ISL, IXL, SIXL).
    
- La **scelta della granularità** come equilibrio tra parallelismo e overhead.
    
- L’implementazione dei **lock in SQL:1999**, con i quattro livelli di isolamento e le loro differenze.

---


![](imgs/Pasted%20image%2020251125052152.png)

