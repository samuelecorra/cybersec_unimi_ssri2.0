# **M9 UD1 Lezione 2 - Caratteristiche evolute delle regole attive**

### **1. Introduzione**

Con l’evoluzione dei sistemi di gestione delle basi di dati, le **regole attive** (o trigger) hanno acquisito nel tempo **nuove funzionalità** che ne ampliano la **potenza espressiva** e la **flessibilità operativa**.

Rispetto ai trigger relazionali tradizionali, alcuni sistemi e prototipi avanzati permettono di:

- definire **nuovi tipi di eventi** (anche temporali e applicativi),
    
- controllare la **modalità di attivazione** e la possibilità di **disattivare o raggruppare** regole,
    
- specificare la **priorità di esecuzione** tra più regole attive.

Questi miglioramenti rendono le basi di dati attive strumenti potenti non solo per il controllo dell’integrità, ma anche per l’automazione di processi complessi.

---

### **2. Evoluzione delle regole: eventi**

Le versioni più recenti dei DBMS introducono **tipologie di eventi più ricche** rispetto ai soli eventi di manipolazione dei dati (`INSERT`, `UPDATE`, `DELETE`):

#### **a) Eventi temporali**

Sono eventi **legati al tempo**, che permettono di eseguire regole a intervalli regolari o in momenti precisi.  
Esempi:

- “21/03/2006 alle ore 12:00”
    
- “ogni giorno alle 17:00”

Questa funzione consente di automatizzare attività **periodiche o pianificate**, come aggiornamenti giornalieri o controlli programmati.

#### **b) Eventi applicativi**

Si tratta di **eventi definiti dall’utente** o dal livello applicativo, utili per modellare situazioni di alto livello.  
Esempio:

- `TemperaturaTroppoAlta` → attiva una regola di allarme o una procedura di emergenza.

#### **c) Combinazioni booleane di eventi**

SQL:1999 consente di specificare **più eventi per un singolo trigger in disgiunzione**: la regola si attiva se **almeno uno degli eventi** si verifica.  
Inoltre, in letteratura e nei prototipi più avanzati sono stati proposti **modelli di composizione più sofisticati**, capaci di combinare eventi in modo complesso (AND, OR, SEQUENCE, ecc.), anche se questi approcci risultano **computazionalmente costosi** e non diffusi negli ambienti commerciali.

---

### **3. Evoluzione delle regole: attivazione (1)**

Oltre ai nuovi tipi di eventi, i sistemi moderni ampliano le possibilità di **attivazione e controllo** delle regole.

#### **a) Regole attivabili/disattivabili**

Molti DBMS consentono di **attivare o disattivare dinamicamente** una regola, senza doverla cancellare o ridefinire.  
Questa funzionalità **non è prevista dallo standard SQL**, ma è supportata da diversi sistemi (come Oracle e DB2).

#### **b) Gruppi di regole**

Alcuni sistemi permettono di **definire gruppi di regole** che possono essere attivati o disattivati **insieme**.  
Ciò consente di gestire **insiemi di comportamenti coordinati**, utile in contesti applicativi complessi o temporaneamente sospesi.

---

### **4. Evoluzione delle regole: attivazione (2)**

#### **c) Clausola `INSTEAD OF`**

Questa modalità di attivazione fa sì che **l’azione della regola venga eseguita al posto dell’operazione che l’ha attivata**, invece che prima o dopo di essa.

- È una **terza modalità** alternativa alle classiche `BEFORE` e `AFTER`.
    
- Viene implementata in diversi DBMS, ma con **limitazioni**:
    
    In **Oracle**, ad esempio, è utilizzabile **solo per trigger su viste**, per gestire l’aggiornamento di viste non direttamente modificabili.

Esempio:

```sql
CREATE TRIGGER ModificaVista
INSTEAD OF UPDATE ON VistaVendite
BEGIN
  UPDATE Vendite
  SET Totale = Totale + 100
  WHERE IDCliente = :NEW.IDCliente;
END;
```

→ L’operazione di `UPDATE` sulla vista viene “sostituita” dal codice del trigger.

---

### **5. Evoluzione delle regole: attivazione (3)**

#### **d) Modalità `DETACHED`**

Questa modalità prevede che la **regola venga gestita in una transazione separata** rispetto a quella che l’ha attivata.

- È utile in situazioni che richiedono **elevata efficienza**, ad esempio per la gestione di **numerosi aggiornamenti in rapida successione**.
    
- Esempio pratico: aggiornamento dei valori di **indici di borsa** in seguito a molti scambi di titoli.

La modalità `DETACHED` si aggiunge alle due modalità classiche:

|Modalità|Descrizione|
|---|---|
|**Immediata (IMMEDIATE)**|Il trigger viene considerato ed eseguito **insieme** all’evento che lo ha attivato.|
|**Differita (DEFERRED)**|Il trigger viene gestito **al termine della transazione**, in corrispondenza del `COMMIT`.|
|**Detached**|Il trigger viene eseguito **in una transazione separata**, indipendente da quella originaria.|

---

### **6. Evoluzione delle regole: priorità**

Quando più regole vengono **attivate contemporaneamente**, è necessario stabilire **l’ordine di esecuzione**.

- Lo **standard SQL:1999** prevede un ordine **basato sul tipo di esecuzione** (`BEFORE` o `AFTER`) e sulla **granularità** (`ROW` o `STATEMENT`).  
    A parità di modalità, **l’ordine non è specificato** dallo standard e dipende dall’implementazione del DBMS.
    
- In molti sistemi commerciali si adotta semplicemente **l’ordine di definizione** dei trigger come criterio di priorità.

Questo meccanismo è importante per **evitare conflitti o comportamenti imprevisti**, soprattutto quando più regole intervengono sugli stessi dati.

---

### **7. Sintesi finale**

In questa lezione abbiamo analizzato le **caratteristiche evolute delle regole attive**, che estendono le funzionalità dei trigger tradizionali.  
Abbiamo visto:

- nuovi **tipi di eventi** (temporali, applicativi e combinati);
    
- **modalità avanzate di attivazione**, come l’attivazione/disattivazione dinamica, la clausola `INSTEAD OF` e la modalità `DETACHED`;
    
- e infine i meccanismi di **priorità di esecuzione** tra regole concorrenti.

**In sintesi:**  
Le basi di dati attive di nuova generazione consentono di **gestire eventi complessi, processi asincroni e comportamenti intelligenti**, rendendo il database un vero **motore reattivo** capace di adattarsi e rispondere autonomamente alle condizioni operative.

---


![](imgs/Pasted%20image%2020251125054812.png)

