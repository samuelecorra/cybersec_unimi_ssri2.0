# **M1 UD3 Lezione 2 - Tecniche di sostituzione della pagina (parte 1)**

### **1. Introduzione**

La **gestione delle pagine** è il cuore operativo della **memoria virtuale**.  
Quando un processo necessita di accedere a una porzione del proprio spazio logico non presente in memoria fisica, il sistema operativo deve **caricare la pagina mancante** dalla memoria secondaria (area di swap).  
Se la memoria è piena, è necessario **scegliere quale pagina rimuovere**, applicando apposite **politiche di sostituzione**.

In questa prima parte si studiano:

- il **meccanismo di caricamento** e **scaricamento** delle pagine;
    
- il **fenomeno del page fault**;
    
- e l’impatto prestazionale di questi meccanismi.

---

### **2. Caricamento della pagina**

#### **(1) Necessità del caricamento**

Durante l’esecuzione, un programma richiede l’accesso a determinate istruzioni e dati.  
Tali informazioni devono trovarsi in **memoria centrale (RAM)**, all’interno dei **frame** assegnati al processo.

Poiché non tutte le pagine logiche di un processo possono essere mantenute contemporaneamente in RAM, il sistema operativo carica **solo le pagine effettivamente richieste nel momento corrente**.

#### **Stringa di riferimento**

La **stringa di riferimento** rappresenta la **sequenza delle pagine** che un processo richiede nel tempo durante la sua esecuzione.  
Essa costituisce la base per l’analisi e la simulazione delle politiche di sostituzione.

---

### **3. Architettura del caricamento**

Il meccanismo di caricamento coinvolge quattro componenti fondamentali:

1. **Memoria virtuale** — spazio logico del processo.
    
2. **Memoria fisica** — insieme dei frame disponibili in RAM.
    
3. **Tabella delle pagine** — struttura dati che mantiene la corrispondenza tra pagine logiche e frame fisici.
    
4. **Area di swap** — porzione della memoria secondaria (tipicamente su disco) dove risiedono le pagine non caricate.

Il sistema operativo coordina queste componenti per eseguire la traduzione logico-fisica e caricare in RAM solo ciò che serve al processo.

---

### **4. Gestione del page fault**

Quando un processo tenta di accedere a una pagina **non presente in memoria fisica**, si verifica una **mancanza di pagina** (_page fault_).  
Questo evento genera una **trap** (interruzione software) gestita dal sistema operativo.

#### **Sequenza di gestione del page fault**

1. Il processore rileva che la pagina richiesta non è presente nella tabella delle pagine.
    
2. La **MMU** genera una **trap (page fault)**.
    
3. Il **sistema operativo** identifica la pagina mancante e la localizza nell’**area di swap**.
    
4. La pagina viene **caricata in un frame libero** della memoria fisica.
    
5. La **tabella delle pagine** del processo viene **aggiornata** con il nuovo mapping.
    
6. L’istruzione che aveva generato il fault viene **riattivata** e riprende l’esecuzione normalmente.

Il tutto avviene in modo **trasparente per il processo**, che non percepisce la mancanza temporanea della pagina.

---

### **5. Prestazioni della richiesta di paginazione**

Il tempo di accesso alla memoria dipende dalla probabilità che si verifichi una mancanza di pagina.

Sia:

- $( p )$ = probabilità di mancanza di pagina (_page fault rate_),
    
- $( m_a )$ = tempo medio di accesso alla memoria centrale fisica,
    
- $( s_{pf} )$ = tempo di servizio per la gestione della mancanza di pagina (caricamento da disco).

Allora, il **tempo di accesso effettivo** alla memoria è:

$$  
T_{\text{eff}} = (1 - p) \cdot m_a + p \cdot s_{pf}  
$$

#### **Interpretazione**

- Se $( p )$ è molto piccolo, il sistema opera quasi alla velocità della RAM.
    
- Se $( p )$ cresce, il tempo medio di accesso aumenta drasticamente a causa dei tempi elevati di I/O su disco.
    
- Il compito del sistema operativo è **minimizzare $p$**, riducendo il numero di page fault attraverso politiche di sostituzione efficienti.

---

### **6. Scaricamento della pagina**

Quando la memoria è piena e si deve caricare una nuova pagina, il sistema operativo deve decidere **quale pagina rimuovere**.  
La scelta dipende dal suo stato di modifica.

#### **a. Frame non modificata**

- La pagina non è cambiata rispetto alla copia su disco.
    
- Il frame può essere **semplicemente rimosso** dalla memoria fisica.

#### **b. Frame modificata**

- La pagina è stata alterata durante l’esecuzione.
    
- Prima di essere rimossa, deve essere **scritta su disco** (nell’area di swap) per non perdere i dati aggiornati.
    
- Alcuni sistemi utilizzano un **buffer delle pagine modificate**, che raccoglie temporaneamente le scritture prima del salvataggio effettivo, riducendo i tempi di attesa.

#### **c. Frame residenti**

- Alcune pagine, come quelle del **kernel** o strutture critiche del sistema operativo, **non possono essere rimosse** dalla memoria.
    
- Esse sono dette **residenti** e restano sempre in RAM.

---

### **7. Sintesi finale**

- Il **caricamento** e lo **scaricamento** delle pagine consentono di gestire la memoria virtuale in modo dinamico.
    
- Ogni accesso a una pagina non presente genera un **page fault**, gestito automaticamente dal sistema operativo.
    
- Il **tempo medio di accesso** dipende fortemente dal numero di fault.
    
- Le **pagine modificate** devono essere salvate su disco prima della sostituzione.
    
- Le **pagine residenti** sono invece permanenti in memoria.

Queste basi preparano lo studio della **seconda parte** della lezione, dedicata alle **politiche di sostituzione delle pagine**.