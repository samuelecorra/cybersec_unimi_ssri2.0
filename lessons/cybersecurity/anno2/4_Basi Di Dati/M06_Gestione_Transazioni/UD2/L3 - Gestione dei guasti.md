# **M6 UD2 Lezione 3 - Gestione dei guasti**

### **1. Introduzione ai guasti**

Nel contesto di un DBMS, un **guasto** è un evento che interrompe l’esecuzione corretta delle transazioni o compromette la coerenza dei dati.  
Esistono **due principali classi di guasti**:

- **Guasti di sistema:** errori del software (come bug del sistema operativo o del DBMS) oppure interruzioni improvvise dei dispositivi hardware (es. cali di tensione).
    
- **Guasti di dispositivo:** malfunzionamenti fisici dei supporti di memoria (es. danneggiamento del disco o strisciamento delle testine).

---

### **2. Guasti di sistema**

I **guasti di sistema** includono errori di programma, blocchi del sistema operativo o improvvise mancanze di alimentazione.

Effetti principali:

- Il contenuto della **memoria centrale** viene perso (inclusi tutti i buffer in RAM).
    
- Il contenuto della **memoria secondaria** (dischi) **non viene perso**.

In questo caso, il sistema può essere recuperato tramite una **ripresa a caldo (warm recovery)**, poiché i dati su disco sono ancora integri.

---

### **3. Guasti di dispositivo**

I **guasti di dispositivo** si riferiscono a danni fisici ai supporti di memorizzazione, come dischi rigidi o SSD.  
Esempio: rottura meccanica del disco o perdita totale dei dati a causa di un difetto del controller.

Effetti principali:

- Il contenuto della **memoria centrale** viene perso (come sempre accade in caso di crash).
    
- Il contenuto della **memoria secondaria** viene perso o danneggiato.
    
- Il contenuto della **memoria stabile** (log, dump, checkpoint) **non viene perso**.

In questi casi è necessaria una **ripresa a freddo (cold recovery)**, poiché occorre ricostruire la base di dati partendo dalle copie di backup e dai log.

---

### **4. Modello fail-stop**

Il DBMS adotta il cosiddetto **modello fail-stop**, secondo cui:

1. Quando viene rilevato un guasto, il sistema **arresta completamente** tutte le transazioni in corso.
    
2. Si garantisce che il **sistema operativo** possa ripartire correttamente (fase di _boot_).
    
3. Si effettua la **ripresa**:
    
    - **a caldo**, se si tratta di un guasto di sistema;
        
    - **a freddo**, se si tratta di un guasto di dispositivo.

Dopo la ripresa, il sistema torna ad essere **nuovamente utilizzabile**, con il **buffer vuoto** e la base di dati riportata in uno stato coerente.

---

### **5. Classificazione delle transazioni durante il ripristino**

Dopo un guasto, le transazioni si dividono in due categorie:

- **Committed:** transazioni che hanno eseguito con successo il commit → devono essere **rieseguite (redo)** per ripristinare i loro effetti.
    
- **Non committed:** transazioni interrotte prima del commit → devono essere **annullate (undo)** per riportare la base di dati allo stato precedente.

---

### **6. Ripresa a caldo – Fase 1: individuazione di UNDO e REDO**

Per la **ripresa a caldo**, il sistema esegue un processo in due fasi.

**Fase 1 – Determinazione delle transazioni da disfare (UNDO) e da rifare (REDO):**

1. Si percorre il **log all’indietro** fino al **checkpoint più recente**.
    
2. Si inizializzano due insiemi:
    
    - `UNDO :=` transazioni presenti nel checkpoint
        
    - `REDO := ∅`
    
3. Si percorre poi il log in avanti:
    
    - Per ogni `B(T)` (begin transaction): aggiungere `T` all’insieme **UNDO**
        
    - Per ogni `C(T)` (commit):
        
        - rimuovere `T` da **UNDO**
            
        - aggiungere `T` a **REDO**

In questo modo si identifica l’insieme delle transazioni da annullare e da ripetere.

---

### **7. Ripresa a caldo – Fase 2: ripristino**

**Fase 2 – Ripristino effettivo:**

1. Si percorre il **log all’indietro**:
    
    - per ogni azione appartenente a una transazione in **UNDO**, si esegue `undo(A)`
        
    - si procede fino alla **prima azione** della transazione più vecchia presente in `UNDO ∪ REDO`
    
2. Si percorre poi il **log in avanti**:
    
    - per ogni azione appartenente a una transazione in **REDO**, si esegue `redo(A)`

---

### **8. Proprietà garantite dalla ripresa a caldo**

Il processo di **ripresa a caldo** assicura due proprietà fondamentali:

- **Atomicità:** ogni transazione in corso al momento del guasto lascia la base di dati **solo** in uno dei due stati estremi (iniziale o finale).
    
- **Persistenza:** tutte le modifiche delle transazioni concluse vengono **salvate stabilmente** su memoria secondaria.

---

### **9. Esempio di ripresa a caldo**

**Log semplificato:**

```
B(T1), B(T2), I(T2,O1,A1), B(T3), I(T3,O2,A2), D(T1,O3,B3),
B(T4), U(T3,O2,B4,A4), I(T4,O4,A5), U(T4,O2,B6,A6), C(T2),
CK(T1,T3,T4), C(T4), B(T5), D(T5,O4,B7), U(T1,O2,B8,A8),
A(T3), C(T1), guasto
```

#### **Determinazione di UNDO e REDO**

|Evento|UNDO|REDO|
|---|---|---|
|CK(T1,T3,T4)|T1, T3, T4|—|
|C(T4)|T1, T3|T4|
|B(T5)|T1, T3, T5|T4|
|C(T1)|T3, T5|T4, T1|

**Risultato:**

- `UNDO = {T3, T5}`
    
- `REDO = {T1, T4}`

---

### **10. Operazioni di UNDO e REDO**

**Undo delle transazioni T3 e T5:**

|Record|Azione|
|---|---|
|`D(T5,O4,B7)`|Reinserire `O4`, cioè `O4 := B7`|
|`U(T3,O2,B4,A4)`|Ripristinare `O2 := B4`|
|`I(T3,O2,A2)`|Eliminare `O2`|

**Redo delle transazioni T1 e T4:**

|Record|Azione|
|---|---|
|`D(T1,O3,B3)`|Eliminare `O3`|
|`I(T4,O4,A5)`|Inserire `O4 := A5`|
|`U(T4,O2,B6,A6)`|Aggiornare `O2 := A6`|
|`U(T1,O2,B8,A8)`|Aggiornare `O2 := A8`|

---

### **11. Ripresa a freddo**

La **ripresa a freddo** si applica in caso di **guasto di dispositivo**, cioè quando il contenuto della memoria secondaria è perso o danneggiato.

#### **Fase 1 – Ripristino del contenuto fisico**

1. Accedere al **dump** e ricopiare le **parti deteriorate** della base di dati.
    
2. Trovare nel log il **record di dump più recente**.
    
3. Percorrere il log in avanti applicando, per la parte recuperata:
    
    - le **azioni di modifica della base di dati**;
        
    - i **record di commit e abort**.

#### **Fase 2 – Ripresa logica**

Dopo il ripristino fisico, si esegue una **ripresa a caldo**, per garantire la consistenza logica della base di dati e completare eventuali undo/redo rimasti.

---

### **12. In sintesi**

In questa lezione abbiamo analizzato i meccanismi che garantiscono la **ripresa del sistema** dopo un guasto:

- **Tipologie di guasti:**
    
    - Guasti di **sistema** → perdita della memoria centrale → **ripresa a caldo**
        
    - Guasti di **dispositivo** → perdita della memoria secondaria → **ripresa a freddo**
    
- **Modello fail-stop:** il sistema si arresta, viene ripristinato e riparte in modo sicuro.
    
- **Procedure di recovery:**
    
    - Prima si eseguono le operazioni di **undo**, poi quelle di **redo**.
        
    - Le operazioni di **undo** vengono sempre eseguite **in ordine inverso** rispetto all’esecuzione originaria.

Grazie a questi meccanismi, il DBMS può garantire **affidabilità, atomicità e durabilità** anche in presenza di malfunzionamenti gravi.