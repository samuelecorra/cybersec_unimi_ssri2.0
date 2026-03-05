# **M5 UD1 Lezione 2 - Organizzazione di file - Strutture sequenziali**

### **1. Introduzione**

L’organizzazione fisica di un file determina **come le tuple (righe)** vengono disposte e gestite all’interno della memoria di massa.  
In questa lezione analizziamo le **strutture sequenziali**, una delle modalità più semplici ed intuitive di archiviazione, nelle quali le tuple sono memorizzate secondo un **ordine lineare**.

Le principali tipologie di organizzazione sequenziale sono:

- **seriale (heap o entry-sequenced)**
    
- **ad array**
    
- **sequenziale ordinata**

Ognuna presenta vantaggi, limiti e scenari d’uso specifici.

---

### **2. Organizzazione sequenziale delle tuple**

L’organizzazione sequenziale può essere definita come un modello in cui le tuple sono **disposte una dopo l’altra**, secondo una regola determinata (ordine di inserimento, valore di una chiave, o indice numerico).  
È la base storica dei sistemi di memorizzazione dei dati, ancora oggi usata in alcune fasi interne dei DBMS per operazioni di scansione o caricamento massivo.

---

### **3. Struttura sequenziale seriale**

#### **3.1 Descrizione**

Nella **struttura seriale**, anche detta _heap_ o _entry-sequenced_,  
le tuple sono memorizzate **nell’ordine in cui vengono inserite**.  
Non esiste un ordinamento logico: l’unica sequenza è quella temporale.

#### **3.2 Accesso ai dati**

- L’accesso avviene tramite **scansione sequenziale**: il DBMS legge il file dall’inizio alla fine finché trova la tupla richiesta.
    
- Questa modalità è efficiente solo quando si devono leggere **molti record consecutivi**, ma inefficiente per accessi puntuali.

#### **3.3 Vantaggi**

- **Scritture molto rapide**, poiché ogni nuova tupla viene aggiunta in coda.
    
- **Letture sequenziali** ottimali, utili in operazioni di scansione o analisi massiva.

#### **3.4 Svantaggi**

- Le **cancellazioni** lasciano **spazi vuoti** non riutilizzati immediatamente.
    
- Le **modifiche** che aumentano la lunghezza della tupla non possono essere fatte _in loco_ (serve una riscrittura).
    
- Gli **accessi diretti** richiedono la scansione completa del file.

---

### **4. Struttura sequenziale ad array**

#### **4.1 Descrizione**

In questa organizzazione, le tuple sono collocate in un **array** e la loro posizione dipende da **uno o più campi indice**.

L’array è composto da:

- $n$ blocchi contigui,
    
- ciascuno contenente $m$ slot disponibili per le tuple,  
    per un totale di $n \times m$ posizioni.

Ogni tupla ha un **indice numerico $i$** e si trova nella **$i$-esima celletta** dell’array.

---

#### **4.2 Caricamento e operazioni**

Durante il **caricamento iniziale**, gli indici vengono generati incrementando un contatore.

Le principali **primitive operative** sono:

- `read-ind(i)` → lettura della tupla con indice $i$
    
- `update-ind(i)` → modifica della tupla con indice $i$
    
- `delete-ind(i)` → cancellazione della tupla con indice $i$
    
- `insert-at(i)` → inserimento in un indice specifico
    
- `insert-near()` → inserimento nella posizione libera più vicina
    
- `insert-at-end()` → inserimento in coda

---

#### **4.3 Vantaggi**

- Accesso **diretto e veloce** ai dati sulla base dell’indice.
    
- Ideale per tabelle con chiavi numeriche consecutive.

#### **4.4 Svantaggi**

- Applicabile solo a tuple di **lunghezza fissa**.
    
- Le **cancellazioni** lasciano celle vuote.
    
- Funziona solo se la chiave assume **valori consecutivi**.
    
- Raramente utilizzata nei DBMS moderni, proprio per questi vincoli.

---

### **5. Struttura sequenziale ordinata**

#### **5.1 Descrizione**

In questo modello, le tuple sono **ordinate secondo il valore di un campo chiave** (detto **campo di ordinamento**).  
Non è necessario che tale chiave coincida con la **chiave primaria** della tabella.

#### **5.2 Vantaggi**

- Le **operazioni basate sulla chiave** (es. ricerca per intervallo, range queries) sono **molto efficienti**.
    
- L’accesso sequenziale ordinato facilita operazioni come **JOIN** e **ORDER BY**.

#### **5.3 Svantaggi**

- Gli **inserimenti** e le **modifiche** richiedono la **riorganizzazione delle tuple già presenti**, riducendo l’efficienza.
    
- Le **cancellazioni** possono frammentare il file.

---

#### **5.4 Utilizzo storico**

Questa struttura era tipica dei sistemi basati su **nastri magnetici**, usati per processi _batch_:

- I dati erano conservati in un **file principale**.
    
- Le modifiche venivano temporaneamente salvate in un **file differenziale**.
    
- Periodicamente, i due file venivano **fusi (merge)** per ricostruire la versione aggiornata.

> Nei DBMS moderni questo approccio non è praticabile, poiché i tempi di merge sono incompatibili con le esigenze di aggiornamento immediato.

---

### **6. Ottimizzazioni moderne dei file ordinati**

Per evitare la riorganizzazione completa, i DBMS adottano soluzioni ibride:

1. **Slot liberi pre-allocati** al momento del caricamento  
    → consentono riordinamenti locali senza spostare tutti i record.
    
2. **Aggiunta di nuovi blocchi** quando i precedenti sono saturi  
    → introduce discontinuità ma mantiene l’ordine logico.
    
3. **File di overflow**:
    
    - nuovi record vengono inseriti in un file separato,
        
    - i blocchi di overflow sono concatenati tra loro,
        
    - le ricerche sequenziali devono considerare entrambi i file.

---

### **7. Sintesi finale**

**Abbiamo visto:**

- Le tre principali forme di **strutture sequenziali**:
    
    1. seriale (heap),
        
    2. ad array,
        
    3. ordinata.
    
- I **vantaggi** in termini di semplicità e scansioni efficienti.
    
- I **limiti** legati a inserimenti, cancellazioni e riordini.

> In sintesi: le **strutture sequenziali** rappresentano la forma più basilare di organizzazione fisica,  
> oggi integrate nei DBMS da tecniche più avanzate (hashing, indici, strutture ad albero) che ne superano le limitazioni.

---


![](imgs/Pasted%20image%2020251125051146.png)

