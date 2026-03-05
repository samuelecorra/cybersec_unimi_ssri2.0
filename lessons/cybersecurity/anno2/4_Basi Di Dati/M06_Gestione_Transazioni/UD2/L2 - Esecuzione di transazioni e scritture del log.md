# **M6 UD2 Lezione 2 - Esecuzione di transazioni e scritture del log**

### **1. Scrittura dei record di log**

Durante l’esecuzione delle transazioni, il sistema deve **scrivere i record nel log** seguendo due regole fondamentali che assicurano la corretta gestione delle operazioni e la possibilità di recupero in caso di guasti:

- **Write Ahead Log (WAL)**
    
- **Commit-Precedenza**

Queste due regole definiscono **quando** i record devono essere scritti nel log in relazione alle operazioni effettive sulla base di dati e al momento del commit.

---

### **2. Regola del Write Ahead Log**

La regola del **Write Ahead Log** stabilisce che la **parte “before state” (BS)** dei record di log deve essere **scritta nel log prima** di eseguire la corrispondente operazione sulla base di dati.

In altri termini, **prima di modificare la base di dati**, il sistema deve registrare nel log lo stato precedente dell’oggetto coinvolto.

Questa regola è essenziale perché:

- Permette di eseguire l’**undo** delle operazioni effettuate da transazioni che **non hanno ancora fatto commit**.
    
- Consente il **ripristino** in caso di guasto avvenuto **dopo** l’operazione ma **prima** della scrittura del log.

Se il log non fosse scritto in anticipo, il valore precedente (**before state**) andrebbe perso, rendendo impossibile il recupero.

---

### **3. Regola della Commit-Precedenza**

La regola della **Commit-Precedenza** stabilisce che la **parte “after state” (AS)** dei record di log deve essere **scritta nel log prima del commit** della transazione.

Questa regola assicura che:

- In caso di guasto **dopo il commit**, il sistema possa eseguire un **redo** delle operazioni già approvate ma non ancora scritte fisicamente su disco dal buffer manager.

In sintesi, la **before state** serve per l’**undo**, mentre la **after state** serve per il **redo**. Entrambe devono essere presenti nel log al momento opportuno per garantire il recupero corretto.

---

### **4. Versione semplificata delle regole**

Nella pratica, anche se le due regole sembrano distinte, le **componenti del record di log** (before e after state) vengono spesso **scritte insieme**.

La versione semplificata delle regole richiede che:

- I record del log siano scritti **prima dei corrispondenti record della base di dati**;
    
- Il log sia **aggiornato completamente prima del commit**.

In questo modo il sistema può garantire sia l’**atomicità** sia la **durabilità** delle transazioni.

---

### **5. Record di commit**

Il **record di commit** viene scritto nel log **in modo sincrono (force)** quando una transazione decide di terminare con successo.

A seconda del momento del guasto, il comportamento del sistema cambia:

- **Guasto prima del commit:**  
    Le azioni della transazione devono essere **annullate (undo)** per ripristinare lo stato iniziale della base di dati.
    
- **Guasto dopo il commit:**  
    È necessario eseguire un’operazione di **redo** per ricostruire lo stato finale corretto della transazione.

Il record di commit, quindi, rappresenta il **punto di non ritorno** per la validazione delle modifiche effettuate da una transazione.

---

### **6. Record di abort**

Il **record di abort** viene scritto quando una transazione viene interrotta volontariamente (abort richiesta dalla transazione) o forzatamente (abort imposto dal sistema).

Poiché l’abort non altera le decisioni del gestore dell’affidabilità, il record può essere scritto:

- **In modo asincrono** nel buffer che contiene il blocco corrente del log;
    
- E successivamente **trasferito (flush)** su memoria stabile senza necessità di sincronizzazione immediata.

L’importante è che l’informazione sull’abort sia registrata prima di qualsiasi tentativo di recupero.

---

### **7. Scrittura congiunta: log e base di dati**

Le modalità di scrittura di **log e base di dati** possono variare a seconda del momento in cui il **buffer manager** trasferisce le pagine modificate su disco rispetto al commit.

Si distinguono **tre schemi principali**:

1. **Base di dati modificata prima del commit**
    
2. **Base di dati modificata dopo il commit**
    
3. **Base di dati modificata sia prima che dopo il commit**

---

### **8. Caso 1 – Base di dati modificata prima del commit**

In questo schema, le modifiche sulla base di dati vengono scritte **prima** che la transazione esegua il commit.

Conseguenza: **non sono necessarie operazioni di redo**, perché le modifiche sono già state rese permanenti prima della chiusura della transazione.

Rappresentazione temporale:

```
t
B(T) U(T,X,BS,AS) U(T,Y,BS,AS) C(T)
w(x) w(y)
```

Le scritture nella base di dati avvengono prima del record di commit.

---

### **9. Caso 2 – Base di dati modificata dopo il commit**

In questo schema, le modifiche della transazione vengono scritte **dopo** il commit.

Conseguenza: **non sono necessarie operazioni di undo**, poiché tutte le modifiche sono garantite dal record di commit e possono essere riflesse successivamente.

Rappresentazione temporale:

```
t
B(T) U(T,X,BS,AS) U(T,Y,BS,AS) C(T)
           w(x) w(y)
```

---

### **10. Caso 3 – Base di dati modificata in qualunque momento**

In questo schema, le modifiche possono avvenire **sia prima che dopo il commit**.

Conseguenza: sono necessarie **entrambe** le operazioni di **redo e undo** per garantire la consistenza del sistema.  
Questo schema è quello **più comune** nei DBMS moderni, poiché consente al **gestore del buffer** di ottimizzare le scritture su disco **in modo indipendente** dal gestore dell’affidabilità.

Rappresentazione temporale:

```
t
B(T) U(T,X,BS,AS) U(T,Y,BS,AS) C(T)
w(x) w(y)
```

---

### **11. In sintesi**

In questa lezione sono stati illustrati i meccanismi che regolano la **scrittura dei record di log** e la **gestione delle scritture sulla base di dati**:

- Le **regole fondamentali** del logging:
    
    - **Write Ahead Log (WAL)**
        
    - **Commit-Precedenza**
    
- Il funzionamento dei **record di commit e di abort**
    
- Le **diverse strategie di scrittura congiunta** tra log e base di dati (prima, dopo o mista rispetto al commit)

Queste regole sono alla base del corretto funzionamento del **recovery system**, assicurando che ogni transazione possa essere ripristinata in modo coerente, anche dopo un crash del sistema.

---


![](imgs/Pasted%20image%2020251125051536.png)

