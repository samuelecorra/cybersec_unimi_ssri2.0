# **M9 UD1 Lezione 1 - Basi di dati attive e trigger**

### **1. Introduzione alle basi di dati attive**

Le **basi di dati attive** rappresentano un’evoluzione rispetto ai database tradizionali, in quanto **non si limitano a rispondere passivamente** alle interrogazioni, ma sono in grado di **reagire automaticamente a determinati eventi**.

Questa capacità reattiva è resa possibile grazie alle **regole attive**, note anche come **regole di produzione**, che seguono il paradigma **ECA (Evento–Condizione–Azione)**:

> “A seguito di un evento, se una condizione è soddisfatta, allora esegui un’azione.”

Questo modello consente di incorporare nel database una **conoscenza di tipo reattivo**, riducendo la necessità di implementarla nei programmi applicativi.  
In pratica, il database diventa **autonomo**, capace di monitorare e gestire da solo certe operazioni.

Quasi tutti i DBMS moderni supportano le **regole attive** attraverso i **trigger**.

---

### **2. Trigger: definizione e standardizzazione**

I **trigger** (o **attivatori**) sono meccanismi che permettono di eseguire automaticamente una sequenza di istruzioni SQL quando si verifica un certo evento.

Essi sono stati **standardizzati solo con SQL:1999 (SQL-3)**, ma molti DBMS commerciali li avevano già introdotti in precedenza.  
Per questo motivo, **le modalità di attivazione ed esecuzione dei trigger possono variare** tra sistemi diversi.

---

### **3. Struttura di un trigger**

La definizione di un trigger fa parte del **Data Definition Language (DDL)** e si compone di tre parti fondamentali:

1. **Evento** → specifica **quando** il trigger deve attivarsi.
    
    - Tipicamente corrisponde a un’operazione SQL:  
        `INSERT`, `DELETE`, `UPDATE` (anche su specifiche colonne).
    
1. **Condizione** → è un **predicato booleano** espresso in SQL.
    
    - Se la condizione risulta vera, viene eseguita l’azione.
    
2. **Azione** → rappresenta la **sequenza di comandi SQL** (o una procedura) da eseguire quando il trigger si attiva.

---

### **4. Livelli di granularità**

Un trigger può essere definito a due **livelli di granularità**:

- **Row-level trigger (per tupla)**  
    → si attiva **per ogni tupla** coinvolta nell’evento.
    
- **Statement-level trigger (per istruzione)**  
    → si attiva **una sola volta per l’intera istruzione SQL**, indipendentemente dal numero di tuple modificate.

---

### **5. Modalità di attivazione**

Le modalità di attivazione definiscono **quando** il trigger deve essere eseguito rispetto all’evento che lo genera:

- **Immediata**
    
    - **Before** → prima dell’esecuzione dell’evento.
        
    - **After** → subito dopo l’esecuzione dell’evento.
    
- **Differita**
    
    - L’attivazione avviene **al termine della transazione**, in corrispondenza del **commit**.

---

### **6. Sintassi generale di un trigger SQL**

```sql
CREATE TRIGGER NomeTrigger
  {BEFORE | AFTER}
  {INSERT | DELETE | UPDATE [OF Colonne]}
  ON NomeTabella
  [REFERENCING
     {[OLD_TABLE [AS] TabOld] | [NEW_TABLE [AS] TabNew]} |
     {[OLD_ROW [AS] TuplaOld] | [NEW_ROW [AS] TuplaNew]}]
  [FOR EACH {ROW | STATEMENT}]
  [WHEN (PredicatoSQL)]
  ComandiSQL;
```

#### Note:

- La modalità **statement-level** è il default.
    
- La clausola **REFERENCING** introduce variabili speciali:
    
    - `OLD` e `NEW` si riferiscono allo stato **precedente** e **successivo** della tupla.
        
    - `OLD_TABLE` e `NEW_TABLE` si riferiscono invece allo stato **precedente** e **successivo** dell’intera tabella.

---

### **7. Livelli e modalità dei trigger SQL**

- I trigger sono **immediati** (before o after).
    
- Possono avere **granularità di tupla o di istruzione**.
    
- Le quattro combinazioni possibili sono:
    
    - `BEFORE ROW`
        
    - `BEFORE STATEMENT`
        
    - `AFTER ROW`
        
    - `AFTER STATEMENT`

#### Caratteristiche particolari dei **before-trigger**:

- Sono usati principalmente per **verificare errori** o **modificare i valori** delle variabili `NEW`.
    
- **Non possono contenere comandi DML** (`INSERT`, `UPDATE`, `DELETE`).
    
- **Non attivano altri trigger**.

---

### **8. Esempi di trigger**

#### **Esempio 1 – Monitoraggio dei versamenti**

```sql
CREATE TRIGGER MonitoraConti
AFTER UPDATE ON Conto
FOR EACH ROW
WHEN (old.NomeConto = new.NomeConto AND new.Totale > old.Totale)
BEGIN
  INSERT INTO SingoliVersamenti
  VALUES (new.NomeConto, new.Totale - old.Totale);
END;
```

→ Quando viene aggiornato un conto e il nuovo totale è maggiore del precedente,  
il trigger registra automaticamente la differenza nella tabella `SingoliVersamenti`.

---

#### **Esempio 2 – Archiviazione delle fatture cancellate**

```sql
CREATE TRIGGER ArchiviaFattureCanc
AFTER DELETE ON Fattura
REFERENCING OLD_TABLE AS FattureVecchie
BEGIN
  INSERT INTO FattureCancellate
  (SELECT * FROM FattureVecchie);
END;
```

→ Dopo l’eliminazione di una o più fatture, il trigger **copia i dati eliminati** nella tabella `FattureCancellate`.

---

### **9. Esecuzione dei trigger**

L’esecuzione dei trigger segue una **sequenza ordinata di fasi**:

1. Esecuzione dei trigger **BEFORE STATEMENT**
    
2. Esecuzione dei trigger **BEFORE ROW**
    
3. Esecuzione dei **test di integrità** sulla tabella
    
4. Esecuzione dei trigger **AFTER ROW**
    
5. Esecuzione dei trigger **AFTER STATEMENT**

Se esistono più trigger della stessa categoria, l’ordine di esecuzione **dipende dal DBMS** (non è deterministico).

---

### **10. Trigger Execution Context (TEC)**

I trigger vengono gestiti all’interno di un **Trigger Execution Context (TEC)**, che rappresenta l’ambiente di valutazione e attivazione.

- L’esecuzione dell’azione di un trigger può generare **nuovi eventi**, attivando **altri trigger**.
    
- Ogni nuova attivazione viene gestita in un **nuovo TEC interno**, in una **struttura a pila (stack)**.
    
- In ogni momento, solo **un TEC può essere attivo**, mentre gli altri restano sospesi.

Per i **row-level trigger**, il TEC tiene traccia di:

- quali tuple sono già state processate;
    
- e quali restano da valutare.

---

### **11. Sintesi finale**

In questa lezione abbiamo visto:

- le **basi di dati attive** e il paradigma **ECA (Evento-Condizione-Azione)**;
    
- la **struttura dei trigger** e la loro standardizzazione in **SQL:1999**;
    
- i diversi **livelli di granularità** e le **modalità di attivazione**;
    
- esempi pratici di trigger `AFTER UPDATE` e `AFTER DELETE`;
    
- e infine il **meccanismo di esecuzione e annidamento** dei trigger attraverso il **Trigger Execution Context**.

**In sintesi:** i trigger consentono al database di **reagire automaticamente** a determinati eventi, integrando logica applicativa direttamente nel sistema di gestione dei dati.

---


![](imgs/Pasted%20image%2020251125054603.png)

