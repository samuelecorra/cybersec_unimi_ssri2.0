# **M4 UD4 Lezione 5 - Documentazione, verifica e progettazione fisica**

### **1. Introduzione**

Questa lezione conclude il processo di **progettazione logica** illustrando tre fasi fondamentali:

1. **Documentazione degli schemi logici**
    
2. **Verifica della qualità dello schema**
    
3. **Progettazione fisica**, ossia l’adattamento dello schema logico alle caratteristiche concrete del DBMS e dell’ambiente operativo.

Queste attività assicurano che la base di dati sia **ben documentata, corretta e ottimizzata** in termini di prestazioni.

---

### **2. Documentazione degli schemi logici**

#### **2.1 Risultati della progettazione logica**

Alla fine della progettazione logica, i principali risultati sono:

- Lo **schema logico** della base di dati.
    
- La **documentazione associata**, che:
    
    - deriva dallo **schema concettuale**, opportunamente adattata;
        
    - aggiunge la descrizione di **nuovi vincoli**, come quelli di **integrità referenziale**.

Entrambi - schema e vincoli - possono essere rappresentati anche **graficamente**.

---

#### **2.2 Notazione grafica per la documentazione**

Una **rappresentazione grafica** dello schema logico può seguire queste convenzioni:

- Ogni **relazione** viene rappresentata in un **rettangolo**.
    
- Le **chiavi primarie** sono indicate in **grassetto**.
    
- Gli attributi che possono assumere **valori nulli** sono contrassegnati da un **asterisco (`*`)**.
    
- Le **frecce** rappresentano i vincoli di **integrità referenziale**:
    
    - partono dalla relazione contenente la **chiave esterna**;
        
    - puntano verso la relazione contenente la **chiave primaria** corrispondente;
        
    - possono avere un’etichetta che specifica il vincolo.

---

#### **2.3 Esempio di documentazione**

Esempio di schema logico documentato:

$$  
\text{IMPIEGATO (matr, cognome, stipendio)}
$$
$$
\text{PROGETTO (codice, nome, budget)} \  
$$
$$
\text{PARTECIPAZIONE (impiegato, progetto, ruolo, benefit*)}  
$$

Nello schema:

- `impiegato` e `progetto` sono **chiavi esterne** che rimandano rispettivamente a `IMPIEGATO` e `PROGETTO`;
    
- gli attributi `ruolo` e `benefit*` appartengono alla relazione `PARTECIPAZIONE`;
    
- `benefit*` può assumere valori **nulli**;
    
- le **frecce** collegano `PARTECIPAZIONE` a `IMPIEGATO` e `PROGETTO`, rappresentando i vincoli di integrità referenziale.

---

### **3. Verifica di schemi logici**

#### **3.1 Obiettivo della verifica**

Dopo la progettazione logica, lo schema deve essere **verificato rispetto a criteri di qualità**.  
Il controllo serve a garantire che non vi siano:

- **ridondanze** nei dati,
    
- **anomalie di aggiornamento**,
    
- **inconsistenze** tra relazioni.

---

#### **3.2 Forme normali**

Le **forme normali** definiscono le **proprietà strutturali** che uno schema deve soddisfare.  
Uno schema è “in forma normale” se:

- non presenta **ripetizioni inutili** di informazioni;
    
- non genera **anomalie** durante inserimenti, cancellazioni o modifiche.

Se la progettazione logica è stata condotta correttamente, lo schema dovrebbe già essere in forma normale.  
Altrimenti si procede con un **processo di normalizzazione**, che consiste nel **decomporre** le relazioni complesse in relazioni più semplici, mantenendo la **stessa informazione complessiva**.

---

### **4. Strumenti CASE per la progettazione logica**

#### **4.1 Ruolo degli strumenti CASE**

La fase di progettazione logica è spesso supportata da **strumenti CASE (Computer-Aided Software Engineering)**, che offrono funzionalità per:

- la **traduzione automatica** dallo schema E–R ristrutturato al **modello relazionale**;
    
- la **verifica automatica** di vincoli e dipendenze;
    
- la **documentazione grafica** dello schema relazionale.

> Tuttavia, la fase di **ristrutturazione** deve essere effettuata **manualmente**,  
> poiché richiede valutazioni concettuali e scelte che gli strumenti automatici non possono compiere.

---

### **5. Progettazione fisica**

#### **5.1 Obiettivi e input**

La **progettazione fisica** consiste nel trasformare lo **schema logico** in uno **schema fisico**, tenendo conto delle caratteristiche del **DBMS** e delle **condizioni operative reali**.

**Input principali:**

- lo **schema logico**;
    
- le **caratteristiche del DBMS** scelto (ad esempio, gestione degli indici o delle viste materializzate);
    
- le **previsioni sul carico applicativo**, cioè il tipo e la frequenza delle operazioni (letture, scritture, aggiornamenti).

**Output:**

- lo **schema fisico** della base di dati, comprendente:
    
    - le **definizioni DDL** delle relazioni;
        
    - le **strutture fisiche di accesso** con i relativi parametri;
        
    - l’individuazione e configurazione degli **indici**.

---

#### **5.2 Gli indici**

Gli **indici** sono strutture fisiche di accesso definite su uno o più **attributi** delle relazioni.  
Servono a **ottimizzare le prestazioni**, permettendo un **accesso diretto** ai dati senza dover scandire l’intera tabella.

##### **Vantaggi:**

- riducono i tempi di ricerca e ordinamento;
    
- migliorano l’efficienza delle interrogazioni basate su clausole `WHERE`.

##### **Scelta degli indici:**

- di solito viene fatta in modo **empirico**, analizzando il comportamento reale del sistema;
    
- attraverso una fase detta di **tuning** (regolazione), in cui:
    
    - si **monitorano le prestazioni**;
        
    - si **introducono nuovi indici** o si **eliminano quelli inutili** per migliorare l’efficienza complessiva.

---

### **6. Sintesi finale**

**Abbiamo visto:**

- come documentare gli **schemi logici** con notazioni grafiche;
    
- come **verificarne la qualità** tramite le forme normali;
    
- come passare alla **progettazione fisica**, con particolare attenzione alla scelta degli **indici** e alle **strutture di accesso**.

**In sintesi:**

> La documentazione garantisce chiarezza,  
> la verifica assicura correttezza,  
> e la progettazione fisica ottimizza le prestazioni del sistema informativo.

---


![](imgs/Pasted%20image%2020251125051022.png)

