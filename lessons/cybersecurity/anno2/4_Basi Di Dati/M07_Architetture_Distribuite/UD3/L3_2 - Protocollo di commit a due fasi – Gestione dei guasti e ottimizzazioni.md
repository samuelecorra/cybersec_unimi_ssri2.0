# **M7 UD3 Lezione 3 (parte 2) - Protocollo di commit a due fasi – Gestione dei guasti e ottimizzazioni**

### **1. Introduzione**

Nella prima parte abbiamo visto come il **Two-Phase Commit (2PC)** garantisca l’**atomicità delle transazioni distribuite**.  
Tuttavia, se durante l’esecuzione si verificano **guasti di nodi, perdita di messaggi o partizionamenti della rete**, il protocollo può rimanere bloccato in uno **stato di incertezza**.

Questa seconda parte analizza:

- i **protocolli di ripristino dai guasti**,
    
- le **ottimizzazioni** per ridurre la finestra di incertezza,
    
- e le **varianti del 2PC** (a tre e a quattro fasi).

---

### **2. Gestione dei guasti**

Quando un **Resource Manager (RM)** entra nello stato _ready_, perde la propria autonomia e deve attendere la decisione del **Transaction Manager (TM)**.  
Durante questa **finestra di incertezza**, qualsiasi guasto può compromettere l’esito della transazione.  
Per evitare incoerenze vengono adottati **protocolli di ripristino**, che riportano i nodi a uno stato corretto.

#### Tipologie di guasto:

- **Caduta di un RM**
    
- **Caduta del TM**
    
- **Perdita di messaggi**
    
- **Partizione della rete**

---

### **3. Caduta di un Resource Manager (RM)**

Quando un RM subisce un guasto, la **ripresa a caldo** (recovery) dipende dall’**ultimo record di log** registrato prima dell’interruzione:

|Ultimo record di log|Azione di recovery|
|---|---|
|`abort` o azione locale|Esegue **undo** della transazione|
|`commit`|Esegue **redo** della transazione|
|`ready`|Guasto avvenuto durante il 2PC → l’RM deve chiedere la decisione finale al TM|

Durante il recovery, gli RM:

- raccolgono in un **insieme READY** gli identificatori delle transazioni in stato incerto;
    
- per ciascuna transazione, inviano una **richiesta al TM** per conoscere la decisione globale (commit o abort).

---

### **4. Caduta del Transaction Manager (TM)**

Anche la **caduta del TM** richiede una ripresa basata sull’ultimo record del suo log:

|Ultimo record nel log TM|Azione di recovery|
|---|---|
|`prepare`|Alcuni RM potrebbero essere bloccati. Il TM può:|
|1️⃣ Scrivere `global abort` e completare la seconda fase, oppure||
|2️⃣ Ripetere la prima fase del protocollo.||
|`global commit` / `global abort`|Alcuni RM potrebbero aver ricevuto la decisione, altri no.|
|Il TM deve **ripetere la seconda fase** per notificare tutti.||
|`complete`|Il protocollo è già concluso → nessuna azione necessaria.|

---

### **5. Perdita di messaggi**

Le perdite di messaggi possono coinvolgere varie fasi del protocollo:

- **Perdita di `prepare` o `ready`**  
    → Il TM non può distinguere la perdita da un nodo non responsivo.  
    → Al termine del timeout, il TM assume la decisione **global abort**.
    
- **Perdita di `commit/abort` o `ack`**  
    → Il TM non riceve conferma dai nodi coinvolti.  
    → Quando il timeout della seconda fase scade, **ritrasmette** la decisione a tutti gli RM da cui non ha ricevuto risposta.

In entrambi i casi, i **timeout** e le **ripetizioni** dei messaggi garantiscono la progressione del protocollo.

---

### **6. Partizione della rete**

Un **partizionamento della rete** (network partition) divide il sistema in più sottoreti isolate.  
In questo caso:

- La transazione può avere successo **solo se** il TM e **tutti gli RM coinvolti** si trovano **nella stessa partizione**.
    
- Gli RM nelle altre partizioni restano **bloccati** fino al ripristino della comunicazione.

Nonostante il problema della disponibilità, la partizione **non compromette la correttezza del protocollo**.

---

### **7. Ottimizzazioni del protocollo Two-Phase Commit**

Il 2PC è affidabile ma **oneroso**, poiché ogni fase richiede **scritture sincrone (force)** nei log locali.  
Per ridurre il carico, i sistemi adottano due importanti ottimizzazioni:

#### **a) Abort presunto (Presumed Abort)**

- Se un TM riceve una **richiesta di recovery** da un RM incerto e non trova informazioni sulla transazione,  
    → risponde con un **global abort** di default.

**Vantaggi:**

- evita la scrittura forzata dei record `prepare` e `global abort`;
    
- riduce i tempi di logging;
    
- la mancanza del record `complete` non è critica: in caso di guasto, il TM può semplicemente **ripetere la seconda fase**.

---

#### **b) Read-only**

- Un partecipante che ha eseguito **solo operazioni di lettura** può dichiararsi `read-only`.
    
- Dopo questa dichiarazione:
    
    - l’RM **lascia il protocollo** e non partecipa alla seconda fase;
        
    - il TM **ignora** i partecipanti _read-only_ nel commit globale.

**Risultato:**  
Riduzione del traffico di messaggi e della finestra di incertezza.

---

### **8. Varianti del Two-Phase Commit**

#### **a) Commit a quattro fasi**

- Il **TM** è **replicato** su un nodo di backup.
    
- A ogni fase del protocollo:
    
    1. Il TM **informa prima il backup** della propria decisione;
        
    2. Poi comunica la decisione agli RM.
    
- In caso di guasto del TM, il **backup** può **subentrare** e completare il protocollo.

➡️ Migliora l’affidabilità, ma aumenta la latenza complessiva.

---

#### **b) Commit a tre fasi**

- Introduce una fase intermedia detta **pre-commit** tra le due del 2PC.
    
- In caso di caduta del TM, uno dei partecipanti può essere **eletto nuovo coordinatore** e portare a termine la transazione.

➡️ Questo riduce i blocchi ma **allunga la finestra di incertezza**, rendendolo **poco pratico** nei sistemi reali.

---

### **9. Sintesi finale**

In questa seconda parte abbiamo approfondito:

- i **protocolli di ripristino dai guasti**:
    
    - caduta del TM o RM,
        
    - perdita di messaggi,
        
    - partizione della rete;
    
- le **ottimizzazioni**:
    
    - _abort presunto_,
        
    - _transazioni read-only_;
    
- le **varianti del 2PC**:
    
    - _commit a tre fasi_ e _a quattro fasi_.

**In sintesi:**  
Il protocollo 2PC resta lo **standard industriale** per garantire l’**atomicità** nelle basi di dati distribuite, bilanciando affidabilità e complessità.  
Le sue varianti e ottimizzazioni servono a ridurre la latenza, prevenire blocchi e migliorare la gestione dei guasti senza compromettere la coerenza del sistema.

---


![](imgs/Pasted%20image%2020251125053518.png)

![](imgs/Pasted%20image%2020251125053530.png)

