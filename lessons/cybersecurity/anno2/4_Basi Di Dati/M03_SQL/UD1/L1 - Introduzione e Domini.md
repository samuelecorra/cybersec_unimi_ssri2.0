# **M3 UD1 Lezione 1 - Introduzione e Domini**

### **1. Introduzione a SQL**

Il linguaggio **SQL (Structured Query Language)** è il linguaggio di riferimento per le **basi di dati relazionali**.  
Originariamente concepito come linguaggio di interrogazione nel progetto **System R** dell’IBM (1974), venne successivamente adottato come standard industriale e divenne il linguaggio universale per la gestione dei dati.

**Cenni storici:**

- 1974 – Proposta iniziale in _System R_ (IBM Research).
    
- 1981 – Prima implementazione commerciale: **SQL/DS** (IBM).
    
- 1983 – SQL diventa **standard de facto**.
    
- 1986 – Prima versione ufficiale (**SQL-1**).
    
- 1989 – Revisione: **SQL-89**.
    
- 1992 – Seconda versione (**SQL-2** o **SQL-92**).
    
- 1999 – Terza versione (**SQL-3** o **SQL:1999**).

Nel corso delle lezioni si farà riferimento a **SQL-2**, lo standard ancora oggi più diffuso e supportato dai principali DBMS.

---

### **2. Livelli di conformità in SQL-92**

SQL-92 distingue tre livelli di implementazione:

1. **Entry SQL** → equivalente a SQL-89, include le funzionalità di base.
    
2. **Intermediate SQL** → aggiunge le caratteristiche più richieste dal mercato; è il livello supportato dalla maggior parte dei DBMS.
    
3. **Full SQL** → comprende tutte le funzioni avanzate, introdotte progressivamente dai sistemi più recenti.

La maggior parte dei DBMS moderni è conforme a **Entry SQL** e fornisce **estensioni proprietarie** per le funzionalità più avanzate.

---

### **3. DDL e DML**

All’interno di SQL distinguiamo due categorie principali di comandi:

- **DDL (Data Definition Language)**  
    È il linguaggio di definizione, che permette di creare e modificare:
    
    - domini
        
    - tabelle
        
    - indici
        
    - viste
        
    - vincoli
        
    - autorizzazioni
        
    - procedure e trigger
    
- **DML (Data Manipulation Language)**  
    È il linguaggio di manipolazione, utilizzato per:
    
    - interrogare i dati (query)
        
    - modificarli (`INSERT`, `UPDATE`, `DELETE`)
        
    - gestire le transazioni (`COMMIT`, `ROLLBACK`)

---

### **4. Notazione utilizzata**

Nel corso della trattazione verrà adottata la seguente convenzione sintattica:

|Simbolo|Significato|
|:--|:--|
|_corsivo_|rappresenta una variabile|
|`courier`|rappresenta una parola chiave del linguaggio|
|`< >`|racchiudono un elemento della sintassi|
|`[ ]`|indica un termine **opzionale**|
|`{ }`|indica un termine che può ripetersi **0 o più volte**|
|`|`|

---

### **5. Domini**

I **domini** definiscono l’insieme dei **valori ammissibili** per un attributo di una tabella.

Possono essere:

- **Elementari (predefiniti)** – forniti dallo standard SQL:
    
    - caratteri (`CHAR`, `VARCHAR`)
        
    - numerici (`INTEGER`, `DECIMAL`, `FLOAT`)
        
    - temporali (`DATE`, `TIME`, `TIMESTAMP`)
        
    - intervalli temporali (`INTERVAL`)
        
    - booleani (`BOOLEAN`, da SQL:1999)
        
    - oggetti binari (`BLOB`, _Binary Large Object_)
        
    - oggetti testuali di grandi dimensioni (`CLOB`, _Character Large Object_)
    
- **Definiti dall’utente (user-defined)** – costruiti a partire da domini già esistenti.

---

### **6. Domini definiti dall’utente**

La sintassi generale per definire un nuovo dominio è:

```sql
CREATE DOMAIN NomeDominio AS TipoDiDato
    [DEFAULT valore_generico | USER | NULL]
    [VINCOLI];
```

- **NomeDominio** → nome scelto per il dominio.
    
- **TipoDiDato** → dominio base da cui il nuovo dominio eredita il tipo.
    
- **DEFAULT** → valore predefinito assunto in assenza di specificazioni.
    
- **VINCOLI** → eventuali condizioni che i valori del dominio devono rispettare.

---

### **7. Valore di default**

```sql
DEFAULT <ValoreGenerico | USER | NULL>
```

- **ValoreGenerico:** qualsiasi valore compatibile con il dominio.
    
- **USER:** assume come valore il login dell’utente che esegue il comando.
    
- **NULL:** rappresenta un valore non noto o non esistente; può avere tre interpretazioni:
    
    - _esiste ma non noto_ → es. data di nascita mancante.
        
    - _non esiste_ → es. numero di patente per un minorenne.
        
    - _non si sa se esiste_ → es. numero di patente per un maggiorenne.

---

### **8. Esempi di domini definiti dall’utente**

```sql
CREATE DOMAIN PrezzoQuotidiani AS DECIMAL(2)
    DEFAULT 1.00
    NOT NULL;
```

```sql
CREATE DOMAIN OreLezione AS SMALLINT
    DEFAULT 48;
```

---

### **9. In sintesi**

In questa lezione abbiamo introdotto:

- La **storia e standardizzazione** di SQL.
    
- La **distinzione** tra linguaggi **DDL** e **DML**.
    
- Il concetto di **dominio**, sia elementare che definito dall’utente.

👉 Ricorda: la maggior parte dei sistemi commerciali implementa solo **Entry SQL**, aggiungendo funzionalità avanzate tramite **estensioni proprietarie**.

![](imgs/Pasted%20image%2020251125043951.png)

![](imgs/Pasted%20image%2020251125044002.png)

