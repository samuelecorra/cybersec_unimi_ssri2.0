# **M1 UD1 Lezione 4 - Swapping**

### **1. Introduzione**

La tecnica dello **swapping** rappresenta un ulteriore passo avanti nella gestione della memoria centrale rispetto al semplice partizionamento.  
Il suo scopo è **massimizzare l’uso della memoria fisica** e **aumentare il grado di multiprogrammazione**, liberando spazio occupato da processi temporaneamente inattivi (in stato di _wait_) per far posto a processi pronti all’esecuzione.

---

### **2. Problema di partenza**

Con il **partizionamento** è possibile caricare più processi in memoria contemporaneamente.  
Tuttavia, può accadere che alcuni processi:

- si trovino in **stato di attesa (wait)**,
    
- non evolvano perché bloccati in operazioni di I/O o in sincronizzazione,
    
- continuino comunque a **occupare memoria fisica**.

Questo comporta uno **spreco di spazio**, perché tali processi impediscono al sistema di caricare altri programmi che invece potrebbero progredire.

#### **Conseguenza**

La memoria non è sfruttata in modo ottimale e il **processore resta inattivo** più spesso del necessario, riducendo le prestazioni globali.

---

### **3. Obiettivi dello swapping**

La tecnica dello swapping mira a:

1. **Liberare la memoria fisica** occupata dai processi in stato di _wait_,  
    trasferendoli temporaneamente in un’area di memoria secondaria (ad esempio su disco).
    
2. **Aumentare il grado di multiprogrammazione**,  
    consentendo al sistema di mantenere sempre un numero elevato di processi _pronti_ all’esecuzione.
    
3. **Conservare in memoria centrale** solo i processi negli stati:
    
    - **ready** (pronti all’esecuzione),
        
    - **running** (in esecuzione).

In sintesi, lo swapping ottimizza la **rotazione dei processi** e lo sfruttamento del processore.

---

### **4. Meccanismo di funzionamento**

#### **Swapping (1)**

Il funzionamento dello swapping segue una logica ciclica di caricamento e scaricamento:

1. **Identificazione dei processi inattivi**  
    Il sistema operativo individua i processi in stato di _wait_ che non possono evolvere.
    
2. **Salvataggio su memoria secondaria**  
    I **dati globali**, lo **heap** e lo **stack** di tali processi vengono copiati su un’area temporanea del disco (detta area di swap).
    
3. **Rimozione dalla memoria centrale**  
    I processi in stato di _wait_ vengono rimossi dalla RAM, liberando spazio fisico.
    
4. **Caricamento di nuovi processi**  
    Lo spazio libero viene utilizzato per caricare in memoria altri processi, prelevandoli dall’area di swap o dalla coda dei processi pronti.

Questo ciclo consente di mantenere in RAM solo i processi attivi, aumentando così l’efficienza della CPU.

---

### **5. Estensioni dello swapping**

Oltre al caso base (processi in _wait_), lo swapping può essere esteso a situazioni più ampie:

- **Processi terminati**  
    Una volta conclusi, vengono rimossi definitivamente per liberare memoria.
    
- **Processi pronti**  
    In alcuni sistemi (in particolare quelli con **schedulazione a priorità**) lo swapping viene usato per **regolare dinamicamente la turnazione dei processi**, caricandone o scaricandone alcuni in base alle priorità.  
    Questo meccanismo è noto come **“roll out / roll in”**:
    
    - _roll out_: spostamento temporaneo su disco di un processo meno prioritario;
        
    - _roll in_: ricaricamento in memoria di un processo ad alta priorità.

---

### **6. Caratteristiche e considerazioni**

- Lo swapping **aumenta il numero di processi** che possono usare la memoria centrale e quindi essere eseguiti.
    
- Tuttavia, **non modifica la quantità di memoria assegnata a ciascun processo**.
    
- È **gestito automaticamente dal sistema operativo**, senza intervento del programmatore.
    
- Può risultare **lento** se coinvolge l’intero processo, poiché richiede operazioni di I/O su disco per lo spostamento dei dati.

Nei sistemi moderni, lo swapping viene utilizzato in forma **ottimizzata e selettiva**, spesso integrato nei meccanismi di **memoria virtuale**.

---

### **7. Sintesi finale**

- Il **problema** nasce dallo spreco di memoria causato dai processi in _wait_.
    
- **Obiettivo**: liberare spazio e mantenere in RAM solo processi _ready_ e _running_.
    
- **Soluzione**: spostare temporaneamente i processi inattivi su disco, caricando al loro posto processi attivi.
    
- **Effetto**: aumento del grado di multiprogrammazione e miglior utilizzo della CPU.
    
- **Limite**: operazione costosa in tempo, motivo per cui in seguito evolverà verso il modello di **paginazione e memoria virtuale**.