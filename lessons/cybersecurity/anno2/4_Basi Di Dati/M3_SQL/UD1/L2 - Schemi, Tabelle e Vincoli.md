# **M3 UD1 Lezione 2 - Schemi, Tabelle e Vincoli**

### **1. Introduzione**

Questa lezione approfondisce l’uso dei comandi **DDL (Data Definition Language)** in SQL per la **creazione e modifica di schemi e tabelle**, nonché la **definizione dei vincoli di integrità** che garantiscono la coerenza logica dei dati nel database.  
Si introducono inoltre le **politiche di reazione** ai vincoli di integrità referenziale e i **cataloghi relazionali**, che costituiscono la struttura interna di descrizione del database.

---

### **2. Creazione di uno schema**

```sql
CREATE SCHEMA [NomeSchema]
    [AUTHORIZATION Utente]
    { DefElementoSchema };
```

- **NomeSchema:** nome dello schema.
    
- **Autorizzazione:** specifica l’utente proprietario dello schema.
    
- **DefElementoSchema:** definizioni di elementi come domini, tabelle, indici, viste, asserzioni o privilegi.

Lo schema rappresenta il **contenitore logico** che raccoglie tutti gli oggetti del database appartenenti a un determinato utente o progetto.

---

### **3. Creazione di una tabella**

```sql
CREATE TABLE NomeTabella
( NomeAttributo Dominio [ValoreDiDefault] [Vincoli]
{ , NomeAttributo Dominio [ValoreDiDefault] [Vincoli] }
{ AltriVincoli } );
```

All’interno della definizione di tabella possono essere inseriti **vincoli intra-relazionali** (riferiti alla singola relazione) e **vincoli inter-relazionali** (che coinvolgono più tabelle).

---

### **4. Vincoli intra-relazionali**

I vincoli intra-relazionali devono essere soddisfatti **da ogni istanza della tabella**.  
Possono riguardare uno o più attributi della stessa relazione.

Principali vincoli intra-relazionali:

- `NOT NULL`: impedisce che l’attributo assuma valori nulli.
    
- `UNIQUE`: garantisce l’unicità dei valori dell’attributo o di una combinazione di attributi.
    
    - Per un singolo attributo: `UNIQUE` posto dopo il dominio.
        
    - Per più attributi: `UNIQUE(Attr1, Attr2, …)`.
    
- `PRIMARY KEY`: identifica la chiave primaria della tabella (implica `UNIQUE` e `NOT NULL`).
    
- `CHECK`: consente di definire vincoli generici su valori o relazioni tra attributi.

---

### **5. Esempi di vincoli intra-relazionali**

**Esempio 1 – Coppia di attributi univoca:**

```sql
Nome CHARACTER(20) NOT NULL,
Cognome CHARACTER(20) NOT NULL,
UNIQUE(Nome, Cognome)
```

**Esempio 2 – Attributi singolarmente univoci:**

```sql
Nome CHARACTER(20) NOT NULL UNIQUE,
Cognome CHARACTER(20) NOT NULL UNIQUE
```

**Esempio 3 – Tabelle complete:**

```sql
CREATE TABLE Studenti (
    Matr CHAR(6) PRIMARY KEY,
    Cognome VARCHAR(30) NOT NULL,
    Nome VARCHAR(30) NOT NULL,
    CdL CHAR(5) NOT NULL
);

CREATE TABLE Corsi (
    Codice CHAR(6) PRIMARY KEY,
    Titolo VARCHAR(30) NOT NULL UNIQUE,
    Cognome VARCHAR(20),
    Nome VARCHAR(20)
);
```

---

### **6. Vincoli inter-relazionali**

I vincoli inter-relazionali coinvolgono **più relazioni** e devono essere verificati da **ogni istanza** del database.  
Possono essere:

- `CHECK`: vincoli generici che possono riferirsi a più relazioni.
    
- `REFERENCES` / `FOREIGN KEY`: vincoli di **integrità referenziale** che collegano una tabella interna (referenziante) a una esterna (referenziata).

**Sintassi:**

- Per un solo attributo:
    
    ```sql
    Attributo Dominio REFERENCES TabellaEsterna(AttributoEsterna)
    ```
    
- Per più attributi:
    
    ```sql
    FOREIGN KEY (Attr1, Attr2, …)
        REFERENCES TabellaEsterna(Attr1, Attr2, …)
    ```

---

### **7. Esempi di vincoli inter-relazionali**

```sql
CREATE TABLE Docenti (
    Cognome VARCHAR(20),
    Nome VARCHAR(20),
    Dip VARCHAR(30),
    PRIMARY KEY (Cognome, Nome)
);

CREATE TABLE Corsi (
    Codice CHAR(6) PRIMARY KEY,
    Titolo VARCHAR(30) NOT NULL UNIQUE,
    Cognome VARCHAR(20),
    Nome VARCHAR(20),
    FOREIGN KEY (Cognome, Nome)
        REFERENCES Docenti(Cognome, Nome)
);
```

```sql
CREATE TABLE Esami (
    Matr CHAR(6) REFERENCES Studenti(Matr),
    Cod_corso CHAR(6) REFERENCES Corsi(Codice),
    Data DATE NOT NULL,
    Voto SMALLINT NOT NULL,
    PRIMARY KEY (Matr, Cod_corso)
);
```

---

### **8. Integrità referenziale**

Un vincolo di integrità referenziale può essere violato da operazioni di:

- **Inserimento o modifica** nella tabella interna (referenziante)  
    → se si tenta di inserire un valore che non ha corrispondenza nella tabella esterna.
    
- **Cancellazione o modifica** nella tabella esterna (referenziata)  
    → se si elimina o cambia un valore ancora referenziato da altre tabelle.

Per questi casi, SQL consente di specificare **politiche di reazione** per gestire la violazione.

---

### **9. Politiche di reazione**

```sql
ON <DELETE | UPDATE>
    <CASCADE | SET NULL | SET DEFAULT | NO ACTION>
```

- **CASCADE:** propaga la modifica o la cancellazione alle tuple collegate.
    
- **SET NULL:** assegna `NULL` agli attributi che resterebbero senza riferimento.
    
- **SET DEFAULT:** assegna il valore di default.
    
- **NO ACTION:** impedisce la modifica o la cancellazione che causerebbe la violazione.

**Esempio:**

```sql
CREATE TABLE Esami (
    Matr CHAR(6) REFERENCES Studenti(Matr)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    Cod_corso CHAR(6) REFERENCES Corsi(Codice)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    Data DATE NOT NULL,
    Voto SMALLINT NOT NULL,
    PRIMARY KEY (Matr, Cod_corso)
);
```

---

### **10. Effetti delle politiche di reazione**

**Caso 1 – Cancellazione di uno studente:**

|Politica|Effetto|
|:--|:--|
|`CASCADE`|Cancella anche tutti i suoi esami.|
|`SET NULL`|Imposta a `NULL` la matricola (violerebbe la `NOT NULL` della chiave).|
|`SET DEFAULT`|Imposta la matricola al valore di default.|
|`NO ACTION`|Impedisce la cancellazione dello studente.|

**Caso 2 – Modifica della matricola di uno studente:**

|Politica|Effetto|
|:--|:--|
|`CASCADE`|Aggiorna la matricola anche nella tabella Esami.|
|`SET NULL`|Imposta la matricola a `NULL`.|
|`SET DEFAULT`|Imposta la matricola al valore di default.|
|`NO ACTION`|Impedisce la modifica della matricola.|

---

### **11. Modifiche agli schemi**

SQL consente di modificare elementi già definiti con il comando `ALTER` e di rimuoverli con `DROP`.

**ALTER DOMAIN**

```sql
ALTER DOMAIN NomeDominio
    [ SET DEFAULT ValoreDefault |
      DROP DEFAULT |
      ADD CONSTRAINT DefVincolo |
      DROP CONSTRAINT NomeVincolo ];
```

**ALTER TABLE**

```sql
ALTER TABLE NomeTabella
    [ ALTER COLUMN NomeAttributo
        ( SET DEFAULT ValoreDefault | DROP DEFAULT )
    | ADD CONSTRAINT DefVincolo
    | DROP CONSTRAINT NomeVincolo
    | ADD COLUMN DefAttributo
    | DROP COLUMN NomeAttributo ];
```

**Esempi:**

```sql
ALTER DOMAIN PrezzoQuotidiani
    SET DEFAULT 1.20;

ALTER TABLE Esami
    ADD COLUMN Sessione SMALLINT;
```

---

### **12. Rimozione di elementi – DROP**

```sql
DROP <SCHEMA | DOMAIN | TABLE | VIEW | ASSERTION> NomeElemento
    [RESTRICT | CASCADE];
```

- **RESTRICT:** impedisce la rimozione se l’oggetto è referenziato (comportamento di default).
    
- **CASCADE:** elimina anche tutti gli oggetti che dipendono da quello rimosso.

---

### **13. Cataloghi relazionali**

I **cataloghi relazionali** (o **dizionari dei dati**) descrivono la **struttura logica del database** e contengono informazioni su tabelle, attributi, vincoli, viste e privilegi.  
Sono a loro volta **implementati come relazioni**, consentendo la **riflessività** del modello relazionale.

Due livelli principali:

1. **Definition_Schema:** tabelle contenenti la descrizione di tutte le strutture del database (non vincolante).
    
2. **Information_Schema:** insieme di viste sul _Definition_Schema_, standardizzato e vincolante.

---

### **14. In sintesi**

In questa lezione abbiamo visto:

- La **definizione di schemi e tabelle** tramite comandi DDL.
    
- I **vincoli intra- e inter-relazionali** e le **politiche di reazione** alle violazioni.
    
- I comandi per **modificare** o **rimuovere** elementi dello schema (`ALTER`, `DROP`).
    
- I **cataloghi relazionali**, fondamentali per la gestione automatica della base di dati.

---

![](imgs/Pasted%20image%2020251125044018.png)

![](imgs/Pasted%20image%2020251125044026.png)

![](imgs/Pasted%20image%2020251125044034.png)

