# **M4 UD3 Lezione 3 - Allocazione dei processi**

### **1. Introduzione**

Nei sistemi distribuiti, la **computazione può essere eseguita su nodi diversi** della rete.  
Per sfruttare al meglio le risorse disponibili, è necessario **decidere su quale macchina attivare ciascun processo**, tenendo conto delle prestazioni, del carico e delle risorse necessarie.

L’**allocazione dei processi** consiste quindi nello **stabilire dove eseguire ogni processo** in modo da:

- ottimizzare le prestazioni complessive,
    
- bilanciare il carico di lavoro,
    
- minimizzare i tempi di accesso a dati e periferiche,
    
- garantire tolleranza ai guasti e disponibilità dei servizi.

---

### **2. Obiettivi dell’allocazione**

Gli obiettivi principali dell’allocazione sono:

1. **Eseguire i processi sulla macchina più adatta**, in base al:
    
    - carico computazionale,
        
    - disponibilità delle risorse informative o fisiche.
    
2. **Bilanciamento del carico (load balancing)**
    
    - Distribuire equamente il lavoro tra i processori per evitare sovraccarichi.
    
3. **Ottimizzazione delle risorse**
    
    - Utilizzare in modo efficiente memoria, CPU, dispositivi e reti.
    
4. **Minimizzazione dell’overhead gestionale**
    
    - Ridurre il carico aggiuntivo dovuto alla gestione della distribuzione.
    
5. **Tolleranza ai guasti**
    
    - Permettere la riallocazione o la replica dei processi in caso di guasti di nodi.

---

### **3. Tipologie di allocazione**

L’allocazione dei processi può essere di due tipi principali:

- **Allocazione statica** → definita all’attivazione dei processi.
    
- **Allocazione dinamica** → modificabile durante l’esecuzione.

---

## **Allocazione statica**

### **4. Definizione**

Nell’**allocazione statica**, la collocazione dei processi viene **decisa prima della loro attivazione**.  
Una volta effettuata la scelta, l’allocazione rimane **permanente** per tutta la durata dell’esecuzione.

---

### **5. Tipologie di allocazione statica**

1. **Allocazione completa**
    
    - Tutti i processi vengono allocati **contemporaneamente**.
        
    - Dopo l’allocazione, l’intero gruppo di processi viene attivato.
    
2. **Allocazione incrementale**
    
    - Quando viene attivato un nuovo gruppo di processi, si tiene **fissa l’allocazione di quelli già attivi**.
        
    - L’allocazione si effettua solo sui nuovi processi.

---

### **6. Criteri di ottimizzazione**

L’allocazione statica mira a **ottimizzare una funzione obiettivo**, che può comprendere diversi aspetti:

#### **a. Sfruttamento dei processori**

- Minimizzare il **tempo totale di inattività (idle)**.
    
- Equilibrare la **distribuzione del tempo di idle** tra i processori.
    
- Minimizzare la **latenza media** dei processi.
    
- Massimizzare il **throughput complessivo**.
    
- Ridurre il **tempo di risposta**, specialmente nei sistemi real-time.

#### **b. Efficienza gestionale**

- Minimizzare i tempi di accesso:
    
    - alle informazioni,
        
    - alle periferiche,
        
    - ai servizi del sistema operativo.

---

### **7. Vincoli dell’ottimizzazione**

Durante l’allocazione devono essere considerati vari **vincoli fisici e logici**:

- **Locazione dei processi e delle risorse necessarie**
    
    - Risorse con accesso solo locale: basi dati, sensori, attuatori, interfacce utente.
    
- **Incompatibilità hardware o software**
    
    - Un processo può richiedere un ambiente specifico o essere incompatibile con altri processi.
    
- **Vincoli di sicurezza e autenticazione.**

---

### **8. Algoritmi di allocazione statica**

Gli algoritmi di allocazione si classificano in base a:

|Categoria|Possibili approcci|
|---|---|
|**Modalità di ricerca della soluzione**|- Deterministici- Euristici|
|**Modalità di esecuzione**|- Centralizzati- Distribuiti|
|**Qualità della soluzione**|- Ottima- Sub-ottima|

#### **Allocazione globale vs locale**

- **Globale:** tiene conto dell’intero sistema distribuito.
    
- **Locale:** ottimizza le decisioni a livello di singolo nodo.

#### **Attivazione dell’algoritmo**

- **Dal processore mittente:** è il nodo che decide dove inviare il processo.
    
- **Dal processore ricevente:** è il nodo che decide se accettare o meno un processo.

---

## **Allocazione dinamica**

### **9. Definizione**

Nell’**allocazione dinamica**, la posizione dei processi **può variare durante la loro vita**.  
L’obiettivo è reagire ai cambiamenti del carico o alle condizioni del sistema, mantenendo ottimale la distribuzione.

A differenza dell’allocazione statica, quella dinamica **non è permanente** e si adatta nel tempo.

---

### **10. Tipologie di allocazione dinamica**

1. **Allocazione totale**
    
    - Considera **tutti i processi contemporaneamente** per ridefinirne la posizione.
    
2. **Allocazione parziale**
    
    - Considera solo un **sottoinsieme di processi**, selezionati secondo criteri di riallocazione (es. processi troppo lenti o nodi sovraccarichi).

---

### **11. Strategie operative**

|Tipo di strategia|Descrizione|
|---|---|
|**Allocazione periodica**|Avviene a intervalli di tempo regolari, per esempio ogni T secondi.|
|**Allocazione reattiva**|Si attiva solo quando si verifica una condizione particolare (es. sovraccarico).|
|**Riallocazione volontaria**|Innescata direttamente da un processo che richiede di essere spostato.|

---

### **12. Migrazione dei processi**

La **migrazione** è l’operazione con cui un processo viene **trasferito da un nodo a un altro**.

Le fasi principali sono:

1. **Valutazione dello stato di evoluzione** del processo.
    
2. **Trasferimento del processo** (codice, dati, stato).
    
3. **Riattivazione** sul nodo di destinazione.
    
4. **Compatibilità** e **traduzione** di dati e codice, se le architetture differiscono.

---

### **13. Costi della migrazione**

La migrazione comporta costi dovuti a:

- **Tempo di gestione dell’algoritmo di allocazione.**
    
- **Tempo di trasferimento del processo** (inclusi dati e stato).
    
- **Tempo di riconfigurazione** e riattivazione sul nodo di destinazione.

---

### **14. Sintesi finale**

|Tipo di allocazione|Momento di definizione|Flessibilità|Esempi tipici|
|---|---|---|---|
|**Statica**|All’attivazione del processo|Bassa|Sistemi a carico prevedibile|
|**Dinamica**|Durante l’esecuzione|Alta|Sistemi con carico variabile|

---

### **15. Conclusione**

L’**allocazione dei processi** è una componente essenziale della **computazione distribuita**.  
Attraverso l’uso di strategie statiche e dinamiche, i sistemi distribuiti possono **adattarsi alle variazioni di carico**, **ottimizzare le prestazioni** e **garantire continuità operativa** anche in presenza di guasti.

La capacità di gestire correttamente l’allocazione rappresenta una **forma di intelligenza del sistema operativo distribuito**, che bilancia continuamente efficienza, affidabilità e flessibilità.