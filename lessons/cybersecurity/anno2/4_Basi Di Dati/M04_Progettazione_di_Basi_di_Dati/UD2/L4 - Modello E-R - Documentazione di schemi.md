# **M4 UD2 Lezione 4 - Modello E-R - Documentazione di schemi**

---

### **1. Introduzione**

Uno **schema E–R**, per quanto completo dal punto di vista strutturale, **non basta da solo** a rappresentare in modo esaustivo tutte le informazioni e i vincoli di un’applicazione reale.  
Per ottenere una descrizione **chiara, precisa e condivisibile**, è necessario **accompagnare ogni schema E–R con una documentazione di supporto** che:

- ne faciliti **l’interpretazione**;
    
- descriva **proprietà e regole** non rappresentabili graficamente nel modello.

Questa documentazione integra lo schema con informazioni testuali, logiche e organizzative che completano la progettazione concettuale.

---

### **2. Limiti degli schemi E–R**

Gli schemi E–R, pur essendo potenti strumenti di modellazione, presentano alcune **limitazioni**:

1. **Ambiguità semantiche:**  
    Il nome di un’entità o di una relazione può non essere sufficiente per comprenderne il significato.  
    Esempio:  
    L’entità _Progetto_ rappresenta progetti interni all’azienda o anche esterni?
    
2. **Problemi di leggibilità:**  
    Non sempre è possibile rappresentare **tutti gli attributi** senza rendere lo schema eccessivamente complesso o illeggibile.
    
3. **Vincoli non rappresentabili:**  
    Alcune **regole aziendali** (business rules) non possono essere espresse con i costrutti del modello E–R.  
    Esempio:  
    “Un impiegato non può avere uno stipendio maggiore del suo manager.”

---

### **3. Le regole aziendali (business rules)**

#### **Definizione**

Le **regole aziendali** descrivono le **informazioni che definiscono o vincolano** determinati aspetti di un’applicazione.  
Esse possono riguardare:

- **Descrizioni di concetti** → definizioni precise di entità, relazioni o attributi;
    
- **Vincoli di integrità** → restrizioni sui valori ammissibili dei dati;
    
- **Derivazioni** → concetti calcolabili o inferibili da altri oggetti dello schema (es. _totale = costo + IVA_).

---

### **4. Modi di esprimere le regole**

Le regole aziendali possono essere formulate in due modi:

- In **linguaggio naturale**, utile per la documentazione e la comprensione;
    
- In **forma formale o semi–formale**, quando serve un linguaggio più rigoroso per i vincoli e le derivazioni.

Non esiste uno **standard universale** per la rappresentazione formale delle regole; l’obiettivo è mantenere la chiarezza, anche quando si usano frasi strutturate.

---

### **5. Regole per i vincoli di integrità**

I **vincoli di integrità** sono condizioni che devono essere **sempre vere** nella base di dati.  
Si esprimono come **asserzioni**, ovvero frasi dichiarative che specificano le proprietà che devono essere rispettate.

#### **Caratteristiche delle asserzioni**

- **Atomiche:** non devono contenere altre asserzioni al loro interno;
    
- **Dichiarative:** descrivono _cosa_ deve essere vero, non _come_ realizzarlo.

La gestione di eventuali violazioni (es. tramite trigger o controlli applicativi) è un problema **di implementazione**, non concettuale.

#### **Struttura generale**

$$  
\langle \text{concetto} \rangle  \text{deve / non deve}  \langle \text{espressione su concetti} \rangle  
$$

#### **Esempi**

- “Un dipartimento con sede a Roma **deve** essere diretto da un impiegato con più di dieci anni di anzianità.”
    
- “Un impiegato **non deve** avere uno stipendio maggiore del direttore del proprio dipartimento.”

---

### **6. Regole per le derivazioni**

Le **regole di derivazione** descrivono **come ottenere un concetto derivato** da altri concetti presenti nello schema.  
Sono importanti per definire informazioni calcolate automaticamente.

#### **Struttura generale**

$$  
\langle \text{concetto derivato} \rangle  \text{si ottiene da} \langle \text{operazione su altri concetti} \rangle  
$$

#### **Esempio**

> “Il budget di un progetto si ottiene moltiplicando per 3 la somma degli stipendi degli impiegati che vi partecipano.”

---

### **7. Implementazione delle regole aziendali**

Durante la **traduzione dello schema concettuale** in una base di dati reale, le regole aziendali devono essere **codificate** per garantire la **consistenza dei dati**.

#### **Possibili approcci:**

- **Clausole SQL** (es. vincoli `CHECK`, `UNIQUE`, `FOREIGN KEY`);
    
- **Trigger** (regole attive che reagiscono a modifiche dei dati);
    
- **Procedure applicative**, scritte in un linguaggio di programmazione esterno.

> I vincoli di integrità e le derivazioni diventano quindi **parte integrante del comportamento del sistema**.

---

### **8. Struttura della documentazione E–R**

Ogni schema E–R deve essere corredato da una documentazione che includa:

1. **Descrizione dei concetti**
    
    - Definizione precisa di **entità**, **relazioni** e **attributi**;
        
    - Specifica di **identificatori**, **cardinalità** e **ruoli semantici**.
    
2. **Regole di vincolo**
    
    - Tutte le **asserzioni** che descrivono vincoli aziendali (anche quelli già visibili nello schema).
    
3. **Regole di derivazione**
    
    - Tutte le **formule o regole** che definiscono concetti calcolati a partire da altri dati.

---

### **9. Dizionario dei dati**

Per rendere la documentazione completa e leggibile, si costruisce un **dizionario dei dati**, composto da due tabelle principali:

#### **a) Descrizione delle entità**

|**Nome**|**Definizione informale**|**Attributi (con descrizione)**|**Identificatore**|
|---|---|---|---|
|_Dipartimento_|Unità organizzativa dell’azienda|Nome, Sede, Telefono|Nome|
|_Impiegato_|Lavoratore dipendente dell’azienda|Matricola, Nome, Cognome, Stipendio|Matricola|

#### **b) Descrizione delle relazioni**

|**Nome**|**Definizione informale**|**Attributi**|**Entità coinvolte (con cardinalità)**|
|---|---|---|---|
|_Partecipazione_|Associa un impiegato ai progetti su cui lavora|DataInizio|Impiegato(0,1) – Progetto(1,n)|
|_Direzione_|Associa un dipartimento al suo direttore|—|Impiegato(0,1) – Dipartimento(1,1)|

---

### **10. Documentazione dei vincoli e delle derivazioni**

Oltre alle tabelle sulle entità e relazioni, si aggiungono due ulteriori sezioni:

#### **a) Vincoli di integrità**

|**Codice**|**Descrizione**|
|---|---|
|RV1|Il direttore di un dipartimento deve afferire a quel dipartimento.|
|RV2|Un impiegato non deve avere uno stipendio maggiore del direttore del proprio dipartimento.|
|RV3|Un dipartimento con sede a Roma deve essere diretto da un impiegato con almeno 10 anni di anzianità.|
|RV4|Un impiegato senza dipartimento non deve partecipare a progetti.|

#### **b) Regole di derivazione**

|**Codice**|**Descrizione**|
|---|---|
|RD1|Il budget di un progetto si ottiene moltiplicando per 3 la somma degli stipendi degli impiegati che vi partecipano.|

---

### **11. Esempio di schema documentato**

#### **Schema grafico**

```
IMPIEGATO ──< PARTECIPAZIONE >── PROGETTO
     │ (0,1)                          │ (1,n)
     │
     └─< AFFERENZA >── DIPARTIMENTO ──< DIREZIONE >── IMPIEGATO
```

#### **Esempio di tabelle descrittive**

|**Nome Entità**|**Identificatore**|**Attributi**|**Descrizione**|
|---|---|---|---|
|**Impiegato**|Codice|Cognome, Nome, Stipendio, Età|Lavoratore dell’azienda.|
|**Progetto**|Nome|Budget, DataConsegna|Progetto aziendale in corso.|
|**Dipartimento**|Nome|Sede, Telefono|Struttura organizzativa della società.|

---

### **12. Sintesi finale**

**Abbiamo visto:**

- l’importanza della **documentazione di supporto** al modello E–R;
    
- i limiti del modello grafico e come superarli tramite regole aziendali;
    
- la struttura di un **dizionario dei dati** e delle tabelle per vincoli e derivazioni;
    
- esempi concreti di **vincoli di integrità** e **regole di derivazione**.

**Ricorda:**

> Ogni schema E–R deve essere accompagnato da una documentazione chiara e completa, che includa:
> 
> - la **descrizione di concetti**,
>     
> - i **vincoli di integrità** (espliciti o impliciti),
>     
> - e le **regole di derivazione**.
>     
> 
> Questa documentazione è fondamentale per garantire **consistenza, comprensibilità e correttezza** dell’intero sistema informativo.

---


![](imgs/Pasted%20image%2020251125050508.png)

