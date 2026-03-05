# **M7 UD3 Lezione 3 (parte 1) - Protocollo di commit a due fasi**

### **1. Introduzione**

Nelle **basi di dati distribuite**, una transazione coinvolge spesso **più nodi** e quindi più sistemi di gestione delle risorse.  
È necessario garantire che tutti i nodi coinvolti **concordino sulla stessa decisione**: o la transazione viene **committata interamente**, oppure viene **annullata ovunque**.

Per ottenere questa coerenza si utilizzano i **protocolli di commit distribuito**, il più diffuso dei quali è il **Two-Phase Commit (2PC)**.  
Il suo scopo è assicurare che tutti i partecipanti raggiungano **una decisione globale corretta e coerente** tra _commit_ e _abort_.

---

### **2. Struttura del Two-Phase Commit**

Il **Two-Phase Commit (2PC)** è un protocollo che coinvolge due tipi di processi:

- **Transaction Manager (TM)** – o _coordinatore_:  
    controlla la transazione globale, raccoglie le risposte dai nodi e decide se fare _commit_ o _abort_.
    
- **Resource Manager (RM)** – o _partecipante_:  
    rappresenta ciascun server che gestisce risorse locali (come file, tabelle o frammenti di dati).

Il protocollo si basa su uno **scambio di messaggi rapidi** (broadcast o seriale) tra TM e RM, e sull’uso di **log persistenti** per garantire resistenza ai guasti.

---

### **3. Log del Transaction Manager (TM)**

Il **TM** mantiene un log che registra tutte le fasi del protocollo:

- **`prepare`**  
    Contiene l’identità di tutti i processi RM coinvolti (con identificatori di nodo e di processo).
    
- **`global commit`** / **`global abort`**  
    Registra la **decisione globale finale**.  
    Questa decisione diventa _vincolante_ nel momento in cui è scritta nel log.
    
- **`complete`**  
    Indica la fine del protocollo 2PC dopo la ricezione di tutti gli _acknowledgement_.

---

### **4. Log del Resource Manager (RM)**

Anche ogni **RM** mantiene un proprio log locale:

- **`ready`**  
    Indica la **disponibilità irrevocabile** a partecipare al protocollo e ad accettare la decisione globale del TM.  
    Può essere scritto solo se l’RM:
    
    - si trova in uno **stato affidabile**,
        
    - ha ottenuto il **lock** su tutte le risorse da scrivere.
    
    
    Il record contiene anche l’identificatore del **TM** coordinatore.
    
- **`begin`**, **`insert`**, **`delete`**, **`update`**  
    Riguardano le operazioni della transazione locale eseguite prima della decisione finale.

---

### **5. Perdita di autonomia del Resource Manager**

Quando un RM si dichiara **`ready`**, perde la propria autonomia decisionale:  
non può più decidere indipendentemente se fare _commit_ o _abort_, ma deve attendere la decisione del TM.

Questo periodo è detto **finestra di incertezza**, e deve essere mantenuto **il più breve possibile**.  
Durante questo tempo:

- le **risorse rimangono bloccate**,
    
- non possono essere rilasciate né usate da altre transazioni.

Se invece un RM **non si è ancora dichiarato ready**, può **abortire autonomamente**, annullando i propri effetti (_undo_), senza partecipare al protocollo.

---

### **6. Fase 1 – Prepare Phase**

1. Il **TM**:
    
    - scrive il record `prepare` nel proprio log;
        
    - invia un messaggio `prepare` a tutti gli RM;
        
    - imposta un **timeout** in attesa delle risposte.
    
2. Ogni **RM**:
    
    - se in **stato affidabile**, scrive nel proprio log `ready` e invia al TM il messaggio `ready`;
        
    - se in **stato non affidabile**, invia `non-ready` e si ritira dal protocollo.
    
3. Il **TM** raccoglie tutte le risposte e decide:
    
    - **`global commit`** se _tutti_ gli RM hanno risposto `ready`;
        
    - **`global abort`** se almeno uno ha risposto `non-ready` o se è scattato il **timeout**.

---

### **7. Fase 2 – Commit Phase**

1. Il **TM**:
    
    - trasmette a tutti gli RM la **decisione globale** (`commit` o `abort`);
        
    - imposta un secondo **timeout** per la ricezione degli _ack_.
    
2. Ogni **RM** in stato `ready`:
    
    - scrive nel log il record della **decisione globale** ricevuta;
        
    - esegue in locale l’azione corrispondente (commit/abort);
        
    - invia al TM un messaggio di **acknowledgement** (`ack`).
    
3. Il **TM**:
    
    - raccoglie tutti gli `ack` dagli RM;
        
    - se non riceve una risposta entro il timeout, **ritrasmette la decisione** ai nodi mancanti;
        
    - una volta ricevuti tutti gli ack, scrive nel log il record `complete`.

---

### **8. Sequenza di messaggi del protocollo**

Il protocollo può essere visualizzato così:

```
RM                           TM
│                            │
│ --- prepare ------------>  │
│ <--- ready --------------  │
│ --- decision ------------> │
│ <--- ack ----------------  │
```

Durante l’intero processo:

- il **TM** coordina le fasi,
    
- ogni **RM** conferma esplicitamente ogni passaggio,
    
- i **timeout** assicurano che le parti non restino in attesa indefinita.

---

### **9. Finestra di incertezza**

La **finestra di incertezza** rappresenta l’intervallo in cui un RM:

- ha dichiarato `ready`,
    
- ma non conosce ancora la decisione globale.

Durante questo periodo:

- le risorse rimangono bloccate;
    
- eventuali guasti o ritardi possono lasciare il sistema in **stato incerto**.

Il TM cerca di minimizzare questa finestra con **timeout brevi e comunicazioni rapide**.

---

### **10. Sintesi finale**

In questa prima parte della lezione abbiamo analizzato:

- il **protocollo Two-Phase Commit (2PC)** e il suo ruolo nel garantire l’**atomicità delle transazioni distribuite**;
    
- i ruoli di **Transaction Manager (TM)** e **Resource Manager (RM)**;
    
- la struttura dei **log** e le due fasi fondamentali del protocollo (_prepare_ e _commit_);
    
- la **finestra di incertezza**, momento critico in cui gli RM attendono la decisione globale.

**Ricorda:**  
quando un RM si dichiara _ready_, perde la sua autonomia e deve attenersi alla decisione finale del TM, anche in caso di guasto o ritardo nella rete.