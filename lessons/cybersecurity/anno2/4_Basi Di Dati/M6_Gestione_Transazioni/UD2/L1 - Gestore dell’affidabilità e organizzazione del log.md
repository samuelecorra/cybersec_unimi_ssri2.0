# **M6 UD2 Lezione 1 - Gestore dell’affidabilità e organizzazione del log**

### **1. Il gestore dell’affidabilità**

Il **gestore dell’affidabilità** è il modulo del DBMS responsabile della corretta esecuzione delle **transazioni** e del **ripristino del sistema** in caso di guasti.  
Garantisce le proprietà di **atomicità** e **durabilità**, assicurando che ogni transazione venga completata integralmente o annullata del tutto, e che i dati restino persistenti anche dopo eventuali malfunzionamenti.

È responsabile di due principali ambiti operativi:

- **Esecuzione dei comandi transazionali**, cioè:
    
    - `begin transaction` (B)
        
    - `commit` (C)
        
    - `rollback` (A, da _Abort_)
    
- **Ripristino del sistema dopo malfunzionamenti**, attraverso:
    
    - **Ripresa a caldo (warm recovery)**
        
    - **Ripresa a freddo (cold recovery)**

---

### **2. Memoria stabile**

La **memoria stabile** è un’astrazione che rappresenta una memoria **resistente ai guasti**.  
In pratica, non esiste un dispositivo fisico che garantisca una probabilità di guasto pari a zero, ma è possibile avvicinarsi a questo obiettivo tramite **replicazione** e **protocolli di scrittura affidabili**.

La memoria stabile può essere organizzata in diversi modi, a seconda della criticità dell’applicazione:

- Un’unica **unità a nastro**
    
- Un **nastro e un disco**
    
- Due **unità disco a specchio (mirror)**

Un guasto della memoria stabile è considerato **catastrofico e impossibile**: il sistema la assume come totalmente affidabile.

---

### **3. Il log**

Il **log** è un **file sequenziale** che registra in **ordine cronologico** tutte le azioni eseguite dalle transazioni.  
È scritto su **memoria stabile**, così che il suo contenuto sia disponibile anche dopo eventuali crash del sistema.

Il log è gestito dal **controllore dell’affidabilità** e svolge un ruolo fondamentale perché:

- consente di eseguire operazioni di **UNDO** (annullamento) e **REDO** (ripetizione);
    
- funge da “**filo di Arianna**” che permette al sistema di ricostruire le operazioni compiute;
    
- può essere paragonato alle **briciole di pane di Hansel e Gretel**, che guidano il percorso di ripristino.

---

### **4. Organizzazione del log**

Il log è organizzato come un **file sequenziale** composto da **record in ordine cronologico**.

Esistono **due categorie di record**:

#### **a. Record di transazione**

Registrano le azioni eseguite da una singola transazione:

- `B(T)` → _begin transaction_
    
- `I(T, O, AS)` → _insert_ (oggetto `O`, after state `AS`)
    
- `D(T, O, BS)` → _delete_ (oggetto `O`, before state `BS`)
    
- `U(T, O, BS, AS)` → _update_ (oggetto `O`, before e after state)
    
- `C(T)` → _commit_
    
- `A(T)` → _abort_

#### **b. Record di sistema**

Riguardano lo stato complessivo del DBMS:

- `dump` → creazione di una copia completa della base di dati
    
- `checkpoint` → salvataggio periodico dello stato corrente delle transazioni

---

### **5. Before state e After state**

Per ogni operazione di modifica (insert, delete, update), i record del log contengono:

- **Before State (BS):** lo stato dell’oggetto **prima** dell’operazione
    
- **After State (AS):** lo stato dell’oggetto **dopo** l’operazione

Grazie a queste informazioni, il sistema è in grado di eseguire correttamente sia **undo** che **redo**, ricostruendo in modo preciso la situazione precedente o successiva a ciascuna azione.

---

### **6. Undo**

L’operazione di **undo** serve per **disfare** un’azione già eseguita.

- `undo(U(T,O,BS,AS))` → copia il valore **BS** nell’oggetto `O`
    
- `undo(D(T,O,BS))` → ripristina l’oggetto `O` con il valore **BS**
    
- `undo(I(T,O,AS))` → **cancella** l’oggetto `O` inserito

---

### **7. Redo**

L’operazione di **redo** serve per **ripetere** un’azione e ripristinarne gli effetti.

- `redo(U(T,O,BS,AS))` → copia il valore **AS** nell’oggetto `O`
    
- `redo(D(T,O,BS))` → cancella l’oggetto `O`
    
- `redo(I(T,O,AS))` → ripristina l’oggetto `O` con il valore **AS**

---

### **8. Idempotenza di Undo e Redo**

Le operazioni di undo e redo godono della proprietà di **idempotenza**:  
eseguire più volte lo stesso undo o redo produce lo stesso effetto che eseguirlo una sola volta.

$$  
undo(A) \dots undo(A) = undo(A)  
$$

$$  
redo(A) \dots redo(A) = redo(A)  
$$

L’idempotenza garantisce la **correttezza** del sistema anche se, a causa di errori o ripetizioni accidentali, le stesse operazioni di undo o redo vengono applicate più di una volta.

---

### **9. Checkpoint**

Il **checkpoint** è un’operazione di sistema eseguita dal **gestore dell’affidabilità** in coordinamento con il **buffer manager**.  
Serve per ridurre il tempo di ripristino e aggiornare lo stato della base di dati su memoria stabile.

Durante l’esecuzione di un checkpoint:

1. Si **sospende** temporaneamente l’accettazione di operazioni di scrittura, commit o abort.
    
2. Si **trasferiscono su memoria secondaria** (flush) tutte le pagine sporche del buffer relative a transazioni che hanno già effettuato il commit.
    
3. Si scrive **sincronamente nel log** un record di checkpoint `CK(T1, …, Tn)` contenente l’elenco delle **transazioni attive**.
    
4. Si **riprende** l’esecuzione normale del sistema.

Il checkpoint è eseguito **periodicamente** e rappresenta un punto di riferimento stabile da cui far partire le operazioni di recupero.

---

### **10. Dump**

Il **dump** è una **copia completa della base di dati** memorizzata su **memoria stabile** (backup).  
Viene creato quando il sistema **non è operativo**, in mutua esclusione con tutte le altre transazioni, e in genere è salvato su **nastro magnetico**.

Alla conclusione del dump, viene scritto nel log un record `dump` che:

- indica che è stato effettuato un backup in un determinato istante;
    
- identifica la **copia di riferimento** della base di dati.

Il dump consente una **ripresa a freddo**, mentre il checkpoint supporta la **ripresa a caldo**.

---

### **11. In sintesi**

In questa lezione abbiamo analizzato i meccanismi fondamentali di controllo dell’affidabilità:

- Il ruolo del **gestore dell’affidabilità** nel garantire atomicità e durabilità
    
- Il concetto di **memoria stabile** e la sua implementazione pratica
    
- L’organizzazione e la funzione del **log**
    
- Le operazioni di **undo** e **redo**, con la loro **idempotenza**
    
- Le procedure di **checkpoint** e **dump**, che consentono il recupero sicuro della base di dati

Questi strumenti costituiscono la base per comprendere i meccanismi di **ripresa a caldo e a freddo**, oggetto della prossima lezione.

---


![](imgs/Pasted%20image%2020251125051613.png)

