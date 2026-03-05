# **M5 UD1 Lezione 6 - Progettazione fisica e gestione delle interrogazioni**

### **1. Introduzione**

Questa lezione conclude il modulo dedicato all’**organizzazione fisica delle basi di dati**, illustrando due aspetti fondamentali del funzionamento di un DBMS:

- la **progettazione fisica**, cioè la scelta delle strutture di accesso e degli indici più adatti alle caratteristiche del sistema;
    
- la **gestione delle interrogazioni**, cioè il processo di analisi, ottimizzazione e compilazione delle query SQL.

---

### **2. Progettazione fisica**

#### **2.1 Obiettivi e input**

La **progettazione fisica** rappresenta la fase finale della progettazione di una base di dati e traduce lo **schema logico** in uno **schema fisico**.  
Essa tiene conto delle caratteristiche del DBMS, del carico previsto e di informazioni operative come la crescita dei file o la distribuzione dei dati.

**Input:**

- schema logico del database;
    
- caratteristiche del DBMS scelto;
    
- previsioni sul carico applicativo;
    
- dimensioni iniziali e possibilità di espansione.

**Output:**

- schema fisico della base di dati,
    
- insieme delle **strutture fisiche di accesso** con relativi parametri,
    
- in particolare, **individuazione degli indici**.

---

#### **2.2 Ruolo degli indici**

Gli **indici** sono strutture fisiche che permettono un **accesso diretto e veloce** ai dati.  
Vengono costruiti su **uno o più attributi** e migliorano notevolmente le prestazioni delle interrogazioni.

- Ogni indice si traduce in una **struttura di accesso** (tipicamente un albero B+ o una tabella hash).
    
- Ottimizzano l’accesso alle tuple in base ai valori degli attributi indicizzati.
    
- Possono essere definiti sia **manualmente** dal progettista sia **automaticamente** dal DBMS durante le fasi di tuning.

---

#### **2.3 Tipologie di indici**

- **Indice primario**
    
    - Ha struttura **key-sequenced**, cioè le tuple sono fisicamente ordinate secondo la chiave.
        
    - Generalmente è **unico** (spesso coincide con la chiave primaria della tabella).
        
    - Fornisce un accesso sequenziale e diretto.
    
- **Indici secondari**
    
    - Sono basati su **alberi indiretti** (B+ tree o simili).
        
    - Non modificano l’ordine fisico delle tuple, ma creano una **mappatura logica** tra chiavi e posizioni dei record.
        
    - Sono utili per:
        
        - attributi spesso usati nei **predicati** delle interrogazioni;
            
        - attributi per cui è richiesto **ordinamento in output**.

---

#### **2.4 Individuazione degli indici**

L’individuazione degli indici avviene spesso in modo **empirico**, attraverso una fase di **tuning** delle prestazioni.

- Durante il tuning si possono **aggiungere, modificare o rimuovere** indici per migliorare i tempi di risposta.
    
- È importante monitorare **come gli indici vengono effettivamente utilizzati** dal DBMS, tramite strumenti di analisi come il comando:

```sql
SHOW PLAN;
```

Questo comando mostra il **piano di esecuzione** della query, consentendo di verificare se il DBMS sta realmente sfruttando l’indice.

---

#### **2.5 Definizione degli indici in SQL**

Sebbene non facciano parte dello **standard SQL**, i comandi per la gestione degli indici sono abbastanza simili in tutti i DBMS.

**Sintassi generale:**

```sql
CREATE [UNIQUE] INDEX IndexName
ON TableName (AttributeList);

DROP INDEX IndexName;
```

**Esempi:**

```sql
CREATE INDEX CoNoIdx
ON Impiegato (Cognome, Nome);

DROP INDEX CoNoIdx;
```

> L’opzione `UNIQUE` assicura che non esistano valori duplicati per l’attributo o la combinazione di attributi indicizzati.

---

### **3. Gestione delle interrogazioni**

#### **3.1 Processo di gestione**

Quando una **query SQL** viene inviata al DBMS, essa attraversa diverse fasi prima dell’esecuzione effettiva.

**Input:**

- la query in linguaggio SQL.

**Output:**

- un **programma di accesso in formato interno**, cioè la sequenza ottimizzata di operazioni fisiche che il DBMS eseguirà.

---

#### **3.2 Fasi di compilazione di una query**

1. **Analisi lessicale, sintattica e semantica**
    
    - verifica la correttezza formale e la coerenza semantica della query rispetto allo schema.
    
2. **Traduzione in forma algebrica interna**
    
    - la query SQL è convertita in un’espressione di **algebra relazionale**.
    
3. **Ottimizzazione algebrica**
    
    - si applicano trasformazioni equivalenti che migliorano le prestazioni, ad esempio:
        
        - _push down selection_ (anticipare le selezioni più restrittive per ridurre il volume di dati).
    
4. **Ottimizzazione basata sui costi di esecuzione**
    
    - il DBMS valuta diversi **piani di accesso** e sceglie quello con **costo minimo**, considerando:
        
        - i **metodi di accesso disponibili**,
            
        - le **dimensioni delle tabelle**,
            
        - i **profili statistici** degli attributi.
    
5. **Traduzione finale**
    
    - l’espressione algebrica ottimizzata è trasformata in **operazioni fisiche concrete** supportate dal DBMS (scansioni, join, accessi tramite indici, ecc.).

---

#### **3.3 Schema riassuntivo del processo**

$$  
\text{Query SQL} \rightarrow  
\begin{cases}  
\text{Analisi lessicale, sintattica, semantica} \\\\  
\text{Ottimizzazione algebrica} \\\\  
\text{Ottimizzazione basata sui costi} \\\\  
\text{Generazione del programma di accesso}  
\end{cases}  
\rightarrow \text{Esecuzione}  
$$

---

### **4. Profili delle tabelle**

I **profili delle tabelle** (table statistics) forniscono informazioni quantitative necessarie all’ottimizzatore per stimare i costi delle operazioni.

#### **4.1 Contenuto dei profili**

Per ogni tabella $T$, il DBMS memorizza:

- la **cardinalità** (numero di tuple);
    
- la **dimensione media** delle tuple (in byte);
    
- la **dimensione di ciascun attributo**;
    
- il **numero di valori distinti** di ogni attributo $A_j$;
    
- il **valore minimo e massimo** di ogni attributo numerico.

#### **4.2 Aggiornamento**

Questi dati vengono **calcolati periodicamente** tramite primitive di sistema, ad esempio:

```sql
UPDATE STATISTICS;
```

e vengono **salvati nel dizionario dei dati** (catalogo del DBMS), così da essere utilizzabili nelle ottimizzazioni future.

---

### **5. Strategie di compilazione delle query**

#### **5.1 Compile-and-store**

- La query viene **compilata una sola volta** e il codice interno prodotto viene **memorizzato** nella base di dati.
    
- Vengono registrate anche le **dipendenze** tra il codice e le versioni delle tabelle o degli indici utilizzati.
    
- Se la struttura dei dati cambia, la query compilata viene **invalidata e ricompilata**.

#### **5.2 Compile-and-go**

- La query viene **compilata ed eseguita immediatamente**.
    
- Il codice interno **non è salvato** nel database, quindi la compilazione deve essere ripetuta ad ogni esecuzione.

> I DBMS commerciali spesso adottano una combinazione dei due approcci, memorizzando le query più usate in una **cache delle interrogazioni** per ridurre i tempi di compilazione.

---

### **6. Sintesi finale**

**Abbiamo visto:**

- la **progettazione fisica** del database e il ruolo degli **indici**;
    
- la loro **definizione in SQL** e l’importanza della fase di **tuning**;
    
- il processo di **gestione e ottimizzazione delle interrogazioni**;
    
- i **profili delle tabelle** come base per le scelte dell’ottimizzatore;
    
- le due strategie principali di **compilazione delle query**.

> In conclusione, la progettazione fisica e la gestione delle interrogazioni rappresentano l’anello di congiunzione tra la **struttura logica del database** e la sua **effettiva efficienza operativa**, determinando le prestazioni reali del sistema.

---


![](imgs/Pasted%20image%2020251125051257.png)

![](imgs/Pasted%20image%2020251125051341.png)

