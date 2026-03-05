# **M9 UD1 Lezione 3 - Proprietà delle regole attive**

### **1. Introduzione**

Nelle **basi di dati attive**, la presenza di molteplici **regole** (o **trigger**) che possono attivarsi in cascata comporta la necessità di garantire un comportamento **corretto, prevedibile e terminante** del sistema.

È quindi fondamentale assicurare che l’interferenza tra le varie regole e le loro **attivazioni a catena** non produca **anomalie** o situazioni di **non determinismo**.  
Le tre proprietà chiave da verificare sono:

- **Terminazione**
    
- **Confluenza**
    
- **Determinismo delle osservazioni**

---

### **2. Terminazione**

Una collezione di regole attive soddisfa la **terminazione** se, per qualunque stato iniziale e per qualsiasi sequenza di modifiche, il sistema:

> **produce sempre uno stato finale**,  
> cioè non si verificano **cicli infiniti di attivazione**.

In altre parole, la terminazione garantisce che l’esecuzione delle regole **non entri in loop**, e che il sistema raggiunga sempre un **punto stabile** (stato finale).

È la **proprietà più importante** tra le tre, perché senza di essa **nessun’altra garanzia** (come confluenza o determinismo) può essere considerata valida.

---

### **3. Confluenza**

Una collezione di regole soddisfa la **confluenza** se, per qualunque stato iniziale e qualunque sequenza di modifiche:

> le regole **producono sempre uno stato finale unico**,  
> indipendente dall’**ordine in cui i trigger vengono eseguiti**.

In pratica, anche se più regole possono essere attivate nello stesso momento e in ordine diverso, il risultato finale deve essere **lo stesso**.

La confluenza è particolarmente significativa nei sistemi in cui esiste **non determinismo nella scelta** delle regole da eseguire, cioè quando più regole sono simultaneamente attivabili.

---

### **4. Determinismo delle osservazioni**

Il **determinismo delle osservazioni** rappresenta il livello più alto di garanzia.  
Un insieme di regole è deterministico se, per qualunque stato iniziale e qualunque sequenza di modifiche:

1. le regole **terminano**,
    
2. producono un **unico stato finale** (confluenza),
    
3. e inoltre producono **la stessa sequenza di azioni visibili** verso l’esterno, indipendentemente dall’ordine di esecuzione.

Questo implica che non solo lo **stato finale del database** è identico, ma anche il **comportamento osservabile** (ad esempio, messaggi, scritture su log, invii di notifiche, ecc.) è **deterministico e ripetibile**.

---

### **5. Analisi della terminazione**

Per verificare la terminazione si utilizza spesso il **grafo di attivazione**, che rappresenta le possibili relazioni di attivazione tra le regole.

#### **Definizione: grafo di attivazione**

- Ogni **nodo** rappresenta una **regola** (es. R₁, R₂, …).
    
- Si disegna un **arco** da un nodo Rᵢ a un nodo Rⱼ se:
    
    > l’azione di Rᵢ può **attivare** la regola Rⱼ.  
    > In altre parole, se l’azione di Rᵢ contiene una **primitiva** che coincide con uno **degli eventi** che fanno scattare Rⱼ.

#### **Criterio di terminazione**

- Se il grafo di attivazione è **aciclico**, il sistema è **terminante**.  
    Non ci sono infatti sequenze infinite di regole che si riattivano a vicenda.
    
- L’**aciclicità** è una **condizione sufficiente**, ma **non necessaria**:  
    un sistema con cicli può comunque terminare, purché le regole non generino un’attivazione infinita.

---

### **6. Esempio di regole non terminanti**

```sql
R1: CREATE TRIGGER AggiustaContributi
AFTER UPDATE OF Stipendio ON Impiegato
REFERENCING NEW_TABLE AS NuovoImp
BEGIN
  UPDATE Impiegato
  SET Contributi = Stipendio * 0.8
  WHERE Matr IN (SELECT Matr FROM NuovoImp);
END;

R2: CREATE TRIGGER ControllaSogliaBudget
AFTER UPDATE ON Impiegato
WHEN 50000 > (SELECT SUM(Stipendio + Contributi)
              FROM Impiegato)
BEGIN
  UPDATE Impiegato
  SET Stipendio = 0.9 * Stipendio;
END;
```

In questo caso:

- L’**azione di R1** (aggiornamento dello stipendio) attiva **R2**;
    
- L’**azione di R2**, a sua volta, aggiorna gli stipendi e **riattiva R1**.

→ Si genera quindi **un ciclo di attivazione infinita**, e il sistema **non termina**.

---

### **7. Esempio di regole terminanti**

```sql
R1: CREATE TRIGGER AggiustaContributi
AFTER UPDATE OF Stipendio ON Impiegato
REFERENCING NEW_TABLE AS NuovoImp
BEGIN
  UPDATE Impiegato
  SET Contributi = Stipendio * 0.8
  WHERE Matr IN (SELECT Matr FROM NuovoImp);
END;

R2: CREATE TRIGGER ControllaSogliaBudget
AFTER UPDATE ON Impiegato
WHEN 50000 < (SELECT SUM(Stipendio + Contributi)
              FROM Impiegato)
BEGIN
  UPDATE Impiegato
  SET Stipendio = 0.9 * Stipendio;
END;
```

Qui, la condizione logica nel `WHEN` è invertita (`<` invece di `>`),  
per cui dopo alcune iterazioni **il ciclo si interrompe**.  
Il sistema converge a uno **stato stabile**, quindi è **terminante**.

---

### **8. Sintesi finale**

In questa lezione abbiamo analizzato le **proprietà fondamentali delle regole attive**, indispensabili per garantire la **correttezza e la stabilità** delle basi di dati attive:

- **Terminazione** → evita cicli infiniti di attivazione.
    
- **Confluenza** → assicura un unico stato finale, indipendente dall’ordine di esecuzione.
    
- **Determinismo delle osservazioni** → garantisce anche la stessa sequenza di azioni osservabili.

Abbiamo inoltre visto come la **terminazione possa essere studiata tramite il grafo di attivazione**, e che l’**aciclicità** di tale grafo è una **condizione sufficiente ma non necessaria** per assicurare che il sistema si arresti correttamente.

---


![](imgs/Pasted%20image%2020251125054923.png)

