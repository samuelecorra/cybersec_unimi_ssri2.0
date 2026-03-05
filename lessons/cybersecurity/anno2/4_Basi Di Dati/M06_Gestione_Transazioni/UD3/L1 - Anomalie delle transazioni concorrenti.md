# **M6 UD3 Lezione 1 - Anomalie delle transazioni concorrenti**

### **1. Introduzione al controllo della concorrenza**

Il **controllo della concorrenza** consente l’esecuzione **simultanea di più transazioni** in un sistema di gestione di basi di dati.  
Questo aspetto è **fondamentale** nei sistemi informativi ad alto carico, come quelli di:

- istituti bancari,
    
- aziende finanziarie,
    
- sistemi di prenotazione aerea o ferroviaria.

L’esecuzione concorrente permette di **sfruttare al massimo le risorse del DBMS**, aumentando il numero di transazioni servite al secondo (**throughput**) e riducendo i **tempi di risposta** complessivi.

Il carico applicativo viene misurato in **transazioni per secondo (tps)**, con valori tipici che variano da **decine a migliaia**.

---

### **2. Funzione del controllo della concorrenza**

Il **controllore della concorrenza** è il modulo del DBMS che:

- **media** le richieste di accesso ai dati provenienti dalle diverse transazioni;
    
- **decide** se ciascun accesso può essere eseguito immediatamente o deve essere sospeso;
    
- **stabilisce l’ordine di esecuzione** delle operazioni, agendo come uno **scheduler** che coordina le letture e le scritture concorrenti.

In sostanza, il suo compito è mantenere l’equilibrio tra **efficienza** (massimizzare la concorrenza) e **correttezza** (garantire consistenza e isolamento).

---

### **3. Semplificazioni per lo studio**

Per analizzare il comportamento del controllo della concorrenza, si adottano alcune **semplificazioni**:

- Si considerano basi di dati composte da **oggetti astratti** `x`, `y`, `z` con **valori numerici**.
    
- Le operazioni possibili sono:
    
    - `read(x)` → lettura del valore di `x`;
        
    - `write(x)` → scrittura di un nuovo valore di `x`.
    
- Ciascuna lettura o scrittura è vista come un’operazione sull’**intera pagina di memoria** che contiene il dato.

---

### **4. Architettura del controllo di concorrenza**

Il modulo di controllo della concorrenza opera insieme agli altri componenti del DBMS.  
La sua collocazione nell’architettura generale può essere schematizzata così:

```
Gestore dei metodi d’accesso
Gestore delle transazioni
Gestore della concorrenza
Tabella dei lock
Gestore della memoria secondaria
Base di dati (BD)
```

Flusso delle operazioni:

- Le transazioni emettono richieste di **read, write, begin, commit, abort**.
    
- Il **gestore della concorrenza** riceve e **riordina** le richieste di lettura/scrittura, eventualmente modificandone l’ordine per garantire un’esecuzione corretta e serializzabile.

---

### **5. Problemi di concorrenza e anomalie**

L’esecuzione concorrente di più transazioni può introdurre **problemi di correttezza** se non viene controllata adeguatamente.  
Le principali **anomalie** che possono verificarsi sono:

1. **Perdita di aggiornamenti**
    
2. **Lettura sporca (dirty read)**
    
3. **Letture inconsistenti (inconsistent read)**
    
4. **Aggiornamento fantasma**
    
5. **Inserimento fantasma**

---

### **6. Perdita di aggiornamenti**

La **perdita di aggiornamenti** si verifica quando le modifiche di una transazione vengono **sovrascritte** da una seconda transazione eseguita in concorrenza.

**Esempio:**

|Transazione T1|Transazione T2|
|---|---|
|`begin`|`begin`|
|`read(x)`|`read(x)`|
|`x := x + 1`|`x := x + 1`|
|`write(x)`|`write(x)`|
|`commit`|`commit`|

**Valore iniziale:** `x = 2`  
**Valore finale corretto:** `x = 4`  
**Valore finale effettivo:** `x = 3`

La scrittura di **T2** ha **sovrascritto** la modifica di **T1**, che è andata perduta.

---

### **7. Lettura sporca**

La **lettura sporca** si verifica quando una transazione legge un **valore temporaneo** modificato da un’altra transazione **non ancora confermata (non committed)**, che in seguito viene **annullata (rollback)**.

**Esempio:**

|Transazione T1|Transazione T2|
|---|---|
|`begin`|`begin`|
|`read(x)`|—|
|`x := x + 1`|—|
|`write(x)`|`read(x)`|
|`abort`|`x := x + 1; write(x); commit`|

**Descrizione:**  
La transazione T2 legge il valore di `x` **dopo** che T1 lo ha modificato, ma **prima** che T1 venga abortita.  
T2 elabora quindi un **valore non valido**, perché il cambiamento di T1 verrà annullato.

---

### **8. Letture inconsistenti**

Si parla di **letture inconsistenti** quando una transazione legge dati **mentre un’altra li sta modificando**, ottenendo valori **non coerenti** all’interno della stessa esecuzione.

**Esempio:**

|Transazione T1|Transazione T2|
|---|---|
|`begin`|`begin`|
|`read(x)`|`read(x)`|
|`write(y)`|`x := x + 1; write(x)`|
|`read(x)`|`commit`|
|`write(z)`|—|
|`commit`|—|

**Descrizione:**  
T1 legge `x` due volte: la prima **prima** della modifica di T2, la seconda **dopo**.  
Il risultato è incoerente: T1 osserva **due versioni diverse** dello stesso dato `x`.

---

### **9. Aggiornamento fantasma**

Un’**anomalia di aggiornamento fantasma** si verifica quando una transazione osserva solo **una parte degli effetti** di un’altra, violando temporaneamente un vincolo di integrità.

**Esempio:**

Vincolo: $x + y + z = 1000$

|Transazione T1|Transazione T2|
|---|---|
|`begin`|`begin`|
|`read(x)`|`read(y)`|
|`read(y)`|`y := y - 100`|
|`read(z)`|`read(z)`|
|`s := x + y + z`|`z := z + 100`|
|`commit`|`write(y); write(z); commit`|

**Descrizione:**  
Durante l’esecuzione, T1 calcola una somma $s = x + y + z = 1100$, che **temporaneamente viola il vincolo** di integrità.  
Il risultato non rappresenta uno stato valido della base di dati, perché le due operazioni di T2 non sono ancora entrambe completate.

---

### **10. Inserimento fantasma**

L’**inserimento fantasma** si verifica quando una transazione esegue più volte una **query aggregata o selettiva**, ma ottiene risultati **diversi** perché un’altra transazione ha **inserito o rimosso** tuple che soddisfano il predicato della selezione.

**Esempio:**

```sql
SELECT AVG(Voto)
FROM Esame
WHERE Corso = 'BasiDati';
```

Se, tra due esecuzioni della stessa query, un’altra transazione inserisce una nuova riga relativa al corso _BasiDati_, i due risultati differiranno.

Questo fenomeno **non può essere evitato** semplicemente bloccando i dati già presenti, poiché l’anomalia deriva dall’**inserimento di nuove tuple** che prima non esistevano.

---

### **11. In sintesi**

In questa lezione abbiamo introdotto il **controllo della concorrenza** e analizzato le principali **anomalie** che possono verificarsi quando più transazioni vengono eseguite in parallelo:

- **Perdita di aggiornamenti**
    
- **Lettura sporca**
    
- **Letture inconsistenti**
    
- **Aggiornamento fantasma**
    
- **Inserimento fantasma**

Queste situazioni evidenziano la necessità di **meccanismi di controllo della concorrenza**, che saranno approfonditi nelle lezioni successive per garantire **esecuzioni serializzabili e coerenti**.

---


![](imgs/Pasted%20image%2020251125051815.png)

![](imgs/Pasted%20image%2020251125051824.png)

![](imgs/Pasted%20image%2020251125051852.png)

