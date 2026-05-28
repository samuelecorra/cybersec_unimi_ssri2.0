# **M1 UD1 Lezione 4 - Swapping**

---

### **1. Introduzione**

La tecnica dello **swapping** rappresenta un ulteriore passo avanti nella gestione della memoria centrale rispetto al semplice partizionamento.  
Il suo scopo è **massimizzare l’uso della memoria fisica** e **aumentare il grado di multiprogrammazione**, liberando spazio occupato da processi temporaneamente inattivi (in stato di _wait_) per far posto a processi pronti all’esecuzione.

---

### **2. Problema di partenza**

Con il **partizionamento** è possibile caricare più processi in memoria contemporaneamente, aumentando **statisticamente** la probabilità di avere sempre processi nello stato di **pronto** in grado di subentrare nell'esecuzione quando altri vanno in attesa.

Tuttavia, può accadere che alcuni processi:

- si trovino in **stato di attesa (wait)**,
- non evolvano perché bloccati in operazioni di I/O o in sincronizzazione (oppure in attesa di una risorsa),
- continuino comunque a **occupare memoria fisica**.

Questo comporta uno **spreco di spazio**: lo spazio occupato da tali processi **non è utilizzabile** da altri processi che, pur avendo già tutte le risorse informative e fisiche necessarie, **non possono essere caricati** in RAM per essere eseguiti.

#### **Conseguenza**

Il **grado effettivo di multiprogrammazione si riduce**: la memoria non è sfruttata in modo ottimale e il **processore resta inattivo** più spesso del necessario (perché meno processi pronti possono concorrere per la CPU), riducendo le prestazioni globali.

---

### **3. Obiettivi dello swapping**

La tecnica dello swapping mira a:

1. **Liberare la memoria fisica** occupata dai processi in stato di _wait_,  
    trasferendoli temporaneamente in un'area di memoria secondaria (ad esempio su disco).
2. **Aumentare il grado di multiprogrammazione**: più precisamente, lo swapping **elimina la perdita** di grado di multiprogrammazione dovuta alla presenza di processi in stato di attesa residenti in memoria centrale, permettendo di sostituirli con processi che possono effettivamente progredire.
3. **Conservare in memoria centrale** solo i processi negli stati:
    - **ready** (pronti all'esecuzione),
    - **running** (in esecuzione).

In sintesi, lo swapping ottimizza la **rotazione dei processi** e lo sfruttamento del processore, aumentando la probabilità di avere sempre processi pronti pronti per ottenere la CPU.

---

### **4. Meccanismo di funzionamento**

#### **4.1. Swapping (1)**

Il funzionamento dello swapping segue una logica ciclica di caricamento e scaricamento:

1. **Identificazione dei processi inattivi**  
    Il sistema operativo individua i processi in stato di _wait_ che non possono evolvere.
2. **Salvataggio su memoria secondaria**  
    I **dati globali**, lo **heap** e lo **stack** del processo — se sono stati modificati (nei casi in cui sia possibile rilevarlo) — vengono copiati su un'area temporanea del disco (detta **area di swap**). Il **codice** è assunto **non modificabile**, quindi non serve salvarlo.
3. **Rimozione dalla memoria centrale**  
    I processi in stato di _wait_ vengono rimossi dalla RAM, liberando spazio fisico.
4. **Caricamento di nuovi processi**  
    Lo spazio libero viene utilizzato per caricare in memoria altri processi, prelevandoli dall'area di swap (per i processi che erano stati salvati e che ora hanno tutte le risorse informative e fisiche) o dalla coda dei processi pronti.

Questo ciclo consente di mantenere in RAM solo i processi attivi, aumentando così l'efficienza della CPU.

#### **4.2. Scenario completo**

In ogni istante, la memoria centrale è composta da:

- uno **spazio per il sistema operativo** (sempre residente);
- uno **spazio per i processi applicativi** in stato **ready** o **running**.

Le transizioni di stato attivano le operazioni di swapping:

- un processo che da **running/ready** passa a **wait** → viene **swap-out** dalla memoria centrale verso l'area di swap (per salvare il suo stato di evoluzione);
- un processo nell'area di swap che ottiene tutte le risorse di cui era in attesa → viene **swap-in** in memoria centrale, dove può finalmente competere per il processore con gli altri processi pronti.

#### **4.3. Area di swap**

Lo spazio temporaneo dove vengono conservati i processi al di fuori della memoria centrale è una **memoria di supporto** — tipicamente un **disco** con alcune **zone riservate** a questo trattamento, chiamata appunto **area di swap** (o _swap space_).

---

### **5. Estensioni dello swapping**

Oltre al caso base (processi in _wait_), lo swapping può essere esteso a situazioni più ampie.

#### **5.1. Processi terminati: conservazione per analisi successive**

I processi che hanno terminato la loro esecuzione possono essere **tenuti temporaneamente** nell'area di swap anziché essere rimossi subito, in modo da consentire **analisi successive**:

- finché il sistema non ha completato l'**accounting** (addebito delle risorse usate in caso di terminazione corretta);
- per consentire l'**analisi post-mortem** in caso di errori di esecuzione (cioè quando il processo è stato abortito) — utile a livello di **debugging** o investigazione.

Solo dopo aver completato queste operazioni, il processo viene definitivamente rimosso.

#### **5.2. Processi pronti: roll-out / roll-in**

In alcuni sistemi (in particolare quelli con **schedulazione a priorità**) lo swapping viene usato per **regolare dinamicamente la turnazione dei processi**, caricandone o scaricandone alcuni in base alle priorità — anche quando si tratta di processi che sarebbero comunque nello **stato di pronto**.

Questo meccanismo è noto come **"roll-out / roll-in"**:

- _roll-out_: spostamento temporaneo su disco di un processo meno prioritario per fare spazio in RAM;
- _roll-in_: ricaricamento in memoria di un processo ad alta priorità.

In questo modo si **aumenta la turnazione globale** dei processi sul processore, dando più spazio in RAM ai processi più prioritari.

---

### **6. Caratteristiche e considerazioni**

- Lo swapping **aumenta il numero di processi** che possono usare la memoria centrale e quindi essere eseguiti.
- ⚠️ Tuttavia, **non modifica la quantità di memoria assegnata a ciascun processo**: ogni processo continua a vedere lo stesso spazio fisico — lo swapping cambia _quali_ processi sono in RAM, non _quanto_ spazio ottiene ognuno.
- È **gestito automaticamente dal sistema operativo** ed è **completamente trasparente** sia agli **utenti** sia ai **programmatori**: non richiede alcuna modifica al codice applicativo né scelte progettuali esplicite (a differenza dell'overlaying, che richiedeva al programmatore di identificare le sezioni mutuamente esclusive).
- Può risultare **lento** se coinvolge l'intero processo, poiché richiede operazioni di I/O su disco per lo spostamento dei dati.

Nei sistemi moderni, lo swapping viene utilizzato in forma **ottimizzata e selettiva**, spesso integrato nei meccanismi di **memoria virtuale**.

---

### **7. Sintesi finale**

- Il **problema** nasce dallo spreco di memoria causato dai processi in _wait_.
    
- **Obiettivo**: liberare spazio e mantenere in RAM solo processi _ready_ e _running_.
    
- **Soluzione**: spostare temporaneamente i processi inattivi su disco, caricando al loro posto processi attivi.
    
- **Effetto**: aumento del grado di multiprogrammazione e miglior utilizzo della CPU.
    
- **Limite**: operazione costosa in tempo, motivo per cui in seguito evolverà verso il modello di **paginazione e memoria virtuale**.