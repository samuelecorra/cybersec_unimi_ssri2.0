# **M9 UD1 Lezione 4 - Applicazioni ed esempi delle regole attive**

### **1. Introduzione**

Le **regole attive** rappresentano uno strumento estremamente versatile nelle basi di dati moderne.  
Possono essere utilizzate sia per gestire automaticamente **vincoli interni al sistema** (come l’integrità referenziale), sia per implementare **regole aziendali** di livello applicativo, automatizzando operazioni e controlli complessi.

Si distinguono quindi due categorie principali:

- **Regole interne alla base di dati**, generate dal sistema e non visibili all’utente.
    
- **Regole esterne (o aziendali)**, definite per modellare conoscenza applicativa a livello di schema.

---

### **2. Regole interne alla base di dati**

Le regole interne sono **create automaticamente dal DBMS** per supportare:

- la **gestione dell’integrità referenziale**,
    
- la **derivazione o replicazione dei dati**,
    
- la **gestione di versioni e sicurezza**,
    
- il **logging delle azioni** e la **registrazione di eventi di sistema**.

Queste regole operano in modo **trasparente per l’utente**, assicurando che i vincoli di integrità e le politiche del database siano rispettati senza intervento manuale.

---

### **3. Regole esterne (regole aziendali)**

Le regole esterne esprimono **conoscenza applicativa** e modellano **vincoli o politiche aziendali** che non sono intrinsecamente gestiti dal DBMS.  
Implementarle tramite trigger permette di:

- **centralizzare la logica aziendale** direttamente nel database;
    
- garantire la **consistenza delle regole** su tutte le applicazioni che accedono ai dati;
    
- mantenere una maggiore **indipendenza della conoscenza** rispetto al software esterno.

---

### **4. Esempi di regole interne: gestione dell’integrità referenziale**

#### **a) Esempio 1 – Inserimento in `Impiegato`**

Quando si inserisce un nuovo impiegato, occorre verificare che il suo dipartimento esista.

```sql
CREATE TRIGGER ControllaInsDipImpiegato
BEFORE INSERT ON Impiegato
FOR EACH ROW
WHEN (NOT EXISTS (
      SELECT * FROM Dipartimento
      WHERE NroDip = NEW.NDip))
BEGIN
  SIGNAL SQLSTATE '70006' ('Dipartimento non valido');
END;
```

➡️ Impedisce l’inserimento di un impiegato con un dipartimento inesistente.

---

#### **b) Esempio 2 – Modifica dell’attributo `NDip`**

Se viene aggiornato il numero di dipartimento di un impiegato, il nuovo valore deve esistere nella tabella `Dipartimento`.

```sql
CREATE TRIGGER ControllaModDipImpiegato
BEFORE UPDATE OF NDip ON Impiegato
FOR EACH ROW
WHEN (NOT EXISTS (
      SELECT * FROM Dipartimento
      WHERE NroDip = NEW.NDip))
BEGIN
  SIGNAL SQLSTATE '70006' ('Dipartimento non valido');
END;
```

---

#### **c) Esempio 3 – Cancellazione in `Dipartimento`**

Se un dipartimento viene eliminato, è necessario aggiornare i riferimenti corrispondenti negli impiegati.

```sql
CREATE TRIGGER ControllaCancDipartimento
AFTER DELETE ON Dipartimento
FOR EACH ROW
WHEN (EXISTS (
      SELECT * FROM Impiegato
      WHERE NDip = OLD.NroDip))
BEGIN
  UPDATE Impiegato
  SET NDip = NULL
  WHERE NDip = OLD.NroDip;
END;
```

➡️ Tutti gli impiegati associati al dipartimento cancellato vengono aggiornati con `NDip = NULL`.

---

#### **d) Esempio 4 – Modifica dell’attributo `NroDip`**

Quando cambia il numero di un dipartimento, l’aggiornamento deve propagarsi anche agli impiegati associati.

```sql
CREATE TRIGGER ControllaModAttribNDip
AFTER UPDATE OF NroDip ON Dipartimento
FOR EACH ROW
WHEN (EXISTS (
      SELECT * FROM Impiegato
      WHERE NDip = OLD.NroDip))
BEGIN
  UPDATE Impiegato
  SET NDip = NEW.NroDip
  WHERE NDip = OLD.NroDip;
END;
```

---

### **5. Regole aziendali: integrità e derivazione**

Oltre ai vincoli strutturali, i trigger possono implementare **regole di integrità aziendale** e **derivazione di dati** non esprimibili con i normali costrutti SQL.

---

#### **a) Regola di integrità: vincolo gerarchico tra salari**

> “Un impiegato deve avere uno stipendio inferiore al direttore del dipartimento a cui appartiene.”

Tabelle:

```
IMPIEGATO(ImpNum, Mgr, Salario, DipNum)
DIPARTIMENTO(DipNum, Direttore)
```

Trigger:

```sql
CREATE TRIGGER SalarioImpiegato
AFTER UPDATE OF Salario ON Impiegato
FOR EACH ROW
WHEN (NEW.Salario > (
      SELECT Salario
      FROM Impiegato
      WHERE ImpNum = NEW.Mgr
        AND ImpNum IN (
          SELECT Direttore
          FROM Dipartimento
          WHERE DipNum = NEW.DipNum)))
BEGIN
  SIGNAL SQLSTATE '70005' ('Salario troppo elevato');
END;
```

➡️ Impedisce che un impiegato guadagni più del proprio direttore.

---

#### **b) Regole di derivazione: gerarchia di prodotti**

Si consideri la tabella:

```
PRODOTTO(Codice, Nome, SuperProdotto, Livello)
```

Ogni prodotto può essere:

- **composto** da altri (relazione di contenimento),
    
- **inserito** con riferimento a un super-prodotto,
    
- oppure **radice della gerarchia**, con `SuperProdotto = NULL` e `Livello = 0`.

---

##### **Regola 1 – Cancellazione di un prodotto**

> Se un prodotto viene cancellato, anche tutti i suoi sottoprodotti devono essere eliminati.

```sql
CREATE TRIGGER CancellaProdotto
AFTER DELETE ON Prodotto
FOR EACH ROW
BEGIN
  DELETE FROM Prodotto
  WHERE SuperProdotto = OLD.Codice;
END;
```

---

##### **Regola 2 – Inserimento di un prodotto**

> Quando si inserisce un nuovo prodotto, viene calcolato automaticamente il suo livello nella gerarchia.

```sql
CREATE TRIGGER LivelloProdotto
AFTER INSERT ON Prodotto
FOR EACH ROW
BEGIN
  IF NEW.SuperProdotto IS NOT NULL THEN
    UPDATE Prodotto
    SET Livello = 1 + (
        SELECT Livello FROM Prodotto
        WHERE Codice = NEW.SuperProdotto)
    WHERE Codice = NEW.Codice;
  ELSE
    UPDATE Prodotto
    SET Livello = 0
    WHERE Codice = NEW.Codice;
  END IF;
END;
```

➡️ Il livello dei prodotti viene aggiornato dinamicamente in base alla posizione nella gerarchia.

---

### **6. Sintesi finale**

In questa lezione abbiamo visto:

- le **principali applicazioni delle regole attive**;
    
- la distinzione tra **regole interne** (legate alla struttura e all’integrità del database) e **regole aziendali** (legate alla logica applicativa);
    
- esempi pratici di trigger per:
    
    - **integrità referenziale**,
        
    - **vincoli di integrità aziendale**,
        
    - **derivazione di dati**.

**In sintesi:** le regole attive consentono di automatizzare controlli, derivazioni e comportamenti complessi, rendendo la base di dati **reattiva e intelligente**, capace di mantenere la coerenza e di eseguire azioni coerenti con le politiche applicative.

---


![](imgs/Pasted%20image%2020251125055027.png)

