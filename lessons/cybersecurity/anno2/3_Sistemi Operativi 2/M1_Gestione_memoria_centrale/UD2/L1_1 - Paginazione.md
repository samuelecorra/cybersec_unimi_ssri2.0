# **M1 UD2 Lezione 1 - Paginazione (parte 1)**

### **1. Introduzione**

La **paginazione** è una tecnica avanzata di gestione della memoria centrale introdotta per superare i limiti delle soluzioni precedenti, come l’**overlaying** e lo **swapping**.  
Essa permette di caricare in memoria **solo piccole porzioni di un processo**, garantendo un uso efficiente della RAM e un controllo automatico da parte del sistema operativo, senza intervento del programmatore.

L’idea centrale è dividere sia la **memoria fisica** sia lo **spazio logico dei processi** in **blocchi di uguale dimensione**, detti _pagine_, per gestire il caricamento e lo scaricamento in modo uniforme e veloce.

---

### **2. Problema di partenza**

Caricare un intero processo in memoria è spesso **inutile e inefficiente**:

- gran parte del codice e dei dati non è necessaria nell’immediato;
    
- caricare e scaricare interi processi è **lento**, come nello swapping;
    
- delegare al programmatore la gestione del caricamento, come nell’overlaying, è **complesso e soggetto a errori**.

Il sistema operativo deve quindi disporre di un meccanismo per **caricare automaticamente solo le parti realmente utili** di un processo.

---

### **3. Obiettivi della paginazione**

#### **(1) Efficienza di caricamento**

- **Caricare e scaricare piccole porzioni di memoria**, riducendo i tempi di I/O e il sovraccarico del sistema.
    
- **Mantenere in memoria** solo le pagine del processo che servono **nell’immediato futuro**, minimizzando l’occupazione fisica.
    
- Utilizzare **porzioni di memoria di uguale dimensione**, così da semplificare la gestione e ridurre la frammentazione.

#### **(2) Flessibilità e sicurezza**

- Consentire la **non contiguità** delle porzioni di un processo in memoria fisica.  
    → Ogni processo può essere sparso in più aree della RAM, senza bisogno di blocchi adiacenti.
    
- Rendere la gestione della memoria **completamente automatica**, affidata al sistema operativo anziché al programmatore, garantendo **correttezza, sicurezza ed equità** tra i processi.

---

### **4. Principi della paginazione**

La memoria viene divisa in due spazi paralleli:

- **Memoria fisica**, suddivisa in **pagine fisiche** (_frame_);
    
- **Spazio logico del processo**, suddiviso in **pagine logiche** (_pages_).

Le **pagine logiche e fisiche hanno la stessa dimensione** (ad esempio 4 KB).  
Questo permette di stabilire una **corrispondenza diretta e regolare** tra gli spazi logici e quelli fisici.

---

### **5. Tabella delle pagine**

Per ogni processo, il sistema operativo mantiene una **Tabella delle Pagine** che memorizza la relazione tra le pagine logiche e le pagine fisiche.

#### **Struttura della tabella**

$$  
\text{TabellaPagine[PaginaLogica]} =  
\begin{cases}  
\text{PaginaFisica}, & \text{se la pagina è caricata in RAM} \\\\  
\text{---}, & \text{se la pagina non è caricata}  
\end{cases}  
$$

#### **Indirizzi logici e fisici**

Ogni indirizzo logico generato dal processo è composto da due parti:

$$  
\text{Indirizzo logico} = (p, d)  
$$

dove:

- ( p ) = numero della **pagina logica**,
    
- ( d ) = **spiazzamento** (offset) all’interno della pagina.

La **MMU** (Memory Management Unit) traduce l’indirizzo logico nel corrispondente **indirizzo fisico**:

$$  
\text{Indirizzo fisico} = (f, d)  
$$

dove:

- ( f ) = numero della **pagina fisica** (frame),
    
- ( d ) = spiazzamento invariato.

---

### **6. Gestione della paginazione**

#### **(1) Caricamento**

Le **pagine logiche** necessarie per la prossima fase di computazione vengono **caricate in pagine fisiche**.  
Le pagine logiche di un processo possono quindi essere allocate in **pagine fisiche non contigue**.

#### **(2) Memoria secondaria**

Le **pagine logiche non caricate** vengono mantenute nell’**area di swap** su disco.  
Quando una pagina fisica modificata deve essere sostituita, essa viene **scritta in area di swap** prima di essere rimossa dalla RAM.

#### **(3) Gestione automatica**

Il **sistema operativo** si occupa di tutto il processo:

- seleziona le pagine da caricare,
    
- carica in memoria le pagine richieste ma non presenti,
    
- sceglie quali pagine rimuovere quando la memoria è piena,
    
- scarica le pagine non più necessarie.

Tutto questo avviene **in modo trasparente per il programmatore**.

---

### **7. Supporto hardware: la MMU**

La **Memory Management Unit (MMU)** è l’elemento hardware che **implementa la paginazione**:

- contiene la **tabella delle pagine** o un **puntatore** al suo indirizzo in memoria centrale;
    
- effettua la **traduzione automatica** degli indirizzi logici in indirizzi fisici;
    
- rileva errori come il **page fault** (accesso a pagina non caricata), segnalando al sistema operativo la necessità di caricamento.

Grazie alla MMU, la paginazione è un meccanismo **rapido, sicuro e totalmente automatico**, che non richiede intervento da parte dei programmi utente.

---

### **8. Sintesi finale**

- La **paginazione** divide la memoria logica e fisica in blocchi di uguale dimensione.
    
- Permette di caricare **solo le porzioni utili** di un processo, ottimizzando l’uso della RAM.
    
- La **tabella delle pagine** definisce la corrispondenza logico-fisica per ogni processo.
    
- Il **sistema operativo** e la **MMU** gestiscono automaticamente il caricamento e la sostituzione delle pagine.
    
- La paginazione elimina la necessità di partizioni contigue e costituisce la base della moderna **memoria virtuale**.