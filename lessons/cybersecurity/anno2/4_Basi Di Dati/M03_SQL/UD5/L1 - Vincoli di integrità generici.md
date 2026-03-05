# **M3 UD5 Lezione 1 - Vincoli di integrità generici**

### **1. Introduzione**

In questa lezione vengono analizzati i **vincoli di integrità generici**, strumenti che consentono di specificare condizioni logiche complesse per mantenere la **coerenza e validità dei dati** all’interno di una base di dati.
Essi possono essere **associati a una singola tabella o attributo**, oppure **definiti a livello di intera base di dati**, e vengono verificati automaticamente dal DBMS.

---

### **2. Vincoli associati a una tabella o attributo**

Si definiscono tramite la clausola `CHECK`, che consente di imporre condizioni sui valori assunti dagli attributi di una tabella.

#### **Sintassi**

```sql
CHECK (Condizione)
```

#### **Esempio**

```sql
CREATE TABLE Corsi (
    Codice CHAR(6) PRIMARY KEY
        CHECK (Codice LIKE 'F%'),
    Titolo VARCHAR(30) NOT NULL UNIQUE,
    Cod_docente VARCHAR(20)
        CHECK (
            3 > (SELECT COUNT(*)
                 FROM Corsi AS C
                 WHERE Cod_docente = C.Cod_docente)
        )
);
```

**Significato del vincolo:**

- il codice dei corsi deve iniziare con la lettera “F”;
    
- ogni docente può essere associato **a non più di due corsi**.

---

### **3. Vincoli associati alla base di dati**

A livello globale, è possibile definire vincoli che coinvolgono **più relazioni** mediante le **asserzioni**.

#### **Sintassi**

```sql
CREATE ASSERTION NomeAsserzione
CHECK (Condizione)
```

#### **Esempio**

Consideriamo le relazioni:

- **STUDENTI(Matr, Cognome, Nome, CdL)**
    
- **ESAMI(Matr, Cod_corso, Data, Voto)**

Vogliamo imporre che **ogni studente abbia almeno un esame registrato**.

```sql
CREATE ASSERTION almeno_un_esame
CHECK (
    0 = (SELECT COUNT(*)
         FROM Studenti
         WHERE Matr NOT IN (SELECT Matr FROM Esami))
);
```

---

### **4. Controllo dei vincoli**

Il DBMS può controllare i vincoli in due modalità:

#### **a. Controllo immediato (IMMEDIATE)**

- La verifica avviene **dopo ogni singola operazione**.
    
- In caso di violazione, l’operazione viene **annullata** (rollback parziale).

#### **b. Controllo differito (DEFERRED)**

- La verifica avviene **al termine della transazione**.
    
- Se un vincolo risulta violato, **l’intera transazione** viene annullata (rollback completo).

---

### **5. Vincoli predefiniti e modificabili**

- I vincoli standard (`NOT NULL`, `UNIQUE`, `PRIMARY KEY`, `FOREIGN KEY`) vengono sempre controllati in **modo immediato**.
    
- I vincoli definiti tramite `CHECK` o `ASSERTION` possono invece essere **configurati**.

#### **Comando per modificare il tipo di controllo**

```sql
SET CONSTRAINTS NomeVincolo [IMMEDIATE | DEFERRED];
```

#### **Esempio**

```sql
SET CONSTRAINTS almeno_un_esame IMMEDIATE;
```

---

### **6. In sintesi**

Abbiamo visto:

- la distinzione tra **vincoli di tabella** e **asserzioni globali**;
    
- la definizione di vincoli tramite `CHECK` e `CREATE ASSERTION`;
    
- le modalità di **controllo dei vincoli** (`IMMEDIATE` e `DEFERRED`).

Questi strumenti consentono di definire regole di consistenza **complesse e automatiche**, garantendo l’integrità logica dei dati a livello sia locale che globale.

---

![](imgs/Pasted%20image%2020251125045552.png)

![](imgs/Pasted%20image%2020251125045602.png)

![](imgs/Pasted%20image%2020251125045614.png)
