# **M1 UD2 Lezione 1 - Paginazione (parte 1)**

---

### **1. Introduzione**

La **paginazione** è una tecnica avanzata di gestione della memoria centrale introdotta per superare i limiti delle soluzioni precedenti, come l’**overlaying** e lo **swapping**.  
Essa permette di caricare in memoria **solo piccole porzioni di un processo**, garantendo un uso efficiente della RAM e un controllo automatico da parte del sistema operativo, senza intervento del programmatore.

L’idea centrale è dividere sia la **memoria fisica** sia lo **spazio logico dei processi** in **blocchi di uguale dimensione**, detti _pagine_, per gestire il caricamento e lo scaricamento in modo uniforme e veloce.

---

### **2. Problema di partenza**

#### **2.1. Le tecniche precedenti e i loro contributi**

Le tre tecniche viste nella UD1 affrontavano ciascuna un aspetto specifico della gestione della memoria centrale:

- **Partizionamento** → consentiva di aumentare il **grado di multiprogrammazione** (più processi in RAM contemporaneamente) e garantiva la **protezione** degli spazi di indirizzamento;
- **Swapping** → permetteva di **rimuovere dalla RAM** i processi in stato di **wait**, lasciando spazio a quelli pronti;
- **Overlaying** → permetteva di superare il **limite dello spazio di memoria fisica** assegnato a un processo, caricando solo le porzioni necessarie nell'immediato.

La **paginazione** è la prima tecnica che **fonde** questi tre obiettivi in un'unica gestione coordinata ed efficiente.

#### **2.2. Limiti residui delle tecniche di base**

Caricare un intero processo in memoria è spesso **inutile e inefficiente**:

- gran parte del codice e dei dati non è necessaria nell'immediato;
- caricare e scaricare interi processi è **lento**, come nello swapping;
- delegare al programmatore la gestione del caricamento, come nell'overlaying, è **complesso e soggetto a errori** (porta facilmente a sprechi di memoria).

Il sistema operativo deve quindi disporre di un meccanismo per **caricare automaticamente solo le parti realmente utili** di un processo.

---

### **3. Obiettivi della paginazione**

#### **(1) Efficienza di caricamento**

- **Caricare e scaricare piccole porzioni di memoria**, riducendo i tempi di I/O e il sovraccarico del sistema rispetto allo swapping di interi processi.
- **Mantenere in memoria** solo le pagine del processo che servono **nell'immediato futuro** — esattamente come nell'**overlaying**, ma con gestione automatica.
- Utilizzare **porzioni di memoria di uguale dimensione**. La motivazione è cruciale: se le porzioni hanno tutte la stessa dimensione, **non bisogna cercare** una partizione che possa contenere lo spazio desiderato — qualunque pagina fisica libera può ospitare qualunque pagina logica, semplificando enormemente la gestione.

#### **(2) Flessibilità e sicurezza**

- Consentire la **non contiguità** delle porzioni di un processo in memoria fisica.  
    → Ogni processo può essere sparso in più aree della RAM, senza bisogno di blocchi adiacenti, evitando così sprechi di tempo di gestione per la ricerca di blocchi contigui.
- Rendere la gestione della memoria **completamente automatica**, affidata al sistema operativo anziché al programmatore, garantendo **correttezza, sicurezza, equità** e nessuno **spreco inutile** di memoria centrale.

---

### **4. Principi della paginazione**

La memoria viene divisa in due spazi paralleli:

- **Memoria fisica**, suddivisa in **pagine fisiche** (_frame_);
- **Spazio logico del processo**, suddiviso in **pagine logiche** (_pages_).

Le **pagine logiche e fisiche hanno la stessa dimensione** (ad esempio 4 KB).  
Questo permette di stabilire una **corrispondenza diretta e regolare** tra gli spazi logici e quelli fisici.

#### **4.1. Mapping logico → fisico**

Lo spazio di indirizzamento di un processo viene **mappato** nella memoria centrale fisica caricando le **pagine logiche** in **pagine fisiche** disponibili. È **come se** la memoria fosse divisa in tante piccole **partizioni** tutte della stessa dimensione, e si caricassero le "partizioni logiche" del processo nelle "partizioni fisiche" della RAM — senza alcun vincolo di contiguità.

#### **4.2. Analogia implicita con overlaying e swapping**

La paginazione **incorpora implicitamente** i meccanismi delle tecniche precedenti:

- **Overlaying implicito**: in memoria centrale vengono caricate solo le **pagine logiche** che servono nell'immediato futuro — non l'intero spazio logico del processo;
- **Swapping implicito**: le pagine fisiche che non servono più vengono **rimosse** dalla memoria centrale per fare spazio a nuove pagine logiche, salvandone il contenuto nell'area di swap se modificato.

A differenza dell'overlaying e dello swapping classici, però:

- la **granularità** è la **pagina** (porzione piccola e uniforme), non l'intero processo o blocchi arbitrari;
- la gestione è **completamente automatica** e affidata al SO + MMU.

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

- $p$ = numero della **pagina logica**,
- $d$ = **spiazzamento** (offset) all'interno della pagina.

La **MMU** (Memory Management Unit) traduce l'indirizzo logico nel corrispondente **indirizzo fisico**:

$$  
\text{Indirizzo fisico} = (f, d)  
$$

dove:

- $f$ = numero della **pagina fisica** (frame),
- $d$ = spiazzamento invariato.

##### **Esempio narrativo**

Supponiamo che lo spazio di indirizzamento del processo sia diviso in pagine numerate da 0. Se ci interessa una cella di memoria situata nella **seconda pagina** dello spazio logico, questa si trova nella **pagina logica 1** con un certo **spiazzamento $d$** dall'inizio della pagina.

L'**indirizzo fisico** sarà allora dato dal numero della **pagina fisica** in cui la pagina logica 1 è stata caricata, più lo **stesso spiazzamento $d$** dall'inizio di quella pagina fisica (lo spiazzamento è invariato rispetto a quello logico).

##### **Schema di traduzione effettuato dalla MMU**
 
$$
\begin{array}{c}
\text{CPU genera indirizzo logico } (p, d) \\\\
\downarrow \\\\
\text{Si usa } p \text{ come indice nella tabella delle pagine del processo} \\\\
\downarrow \\\\
\text{Si legge } f = \text{TabellaPagine}[p] \\\\
\downarrow \\\\
\text{Indirizzo fisico } (f, d) \;\longrightarrow\; \text{accesso alla cella di memoria}
\end{array}
$$

![](imgs/Pasted%20image%2020260531215645.png)

---

### **6. Gestione della paginazione**

#### **(1) Caricamento**

Le **pagine logiche** necessarie per la prossima fase di computazione vengono **caricate in pagine fisiche**.  
Le pagine logiche di un processo possono quindi essere allocate in **pagine fisiche non contigue**, in qualunque posizione della RAM — la tabella delle pagine permette di reperirle ovunque siano.

> 📌 **Importante**: in memoria centrale sono caricate solo le pagine logiche dei **processi nello stato di pronto** (e di quello in running), **non di tutti i processi** del sistema. Questo evita di tenere occupato lo spazio fisico con pagine di processi che, essendo in attesa, non possono comunque progredire.

#### **(2) Memoria secondaria**

Le **pagine logiche non caricate** vengono mantenute nell'**area di swap** su disco.  
Quando una pagina fisica **modificata** deve essere sostituita, essa deve essere **scritta in area di swap PRIMA** di essere rimossa dalla RAM — altrimenti le modifiche andrebbero perse. Le pagine non modificate (es. codice) possono essere semplicemente sovrascritte, perché una copia identica è già presente nell'eseguibile su disco.

#### **(3) Gestione automatica**

Il **sistema operativo** si occupa di tutto il processo, in modo **automatico** e **trasparente** per il programmatore:

- seleziona le pagine da caricare,
- carica in memoria le pagine richieste ma non presenti,
- sceglie quali pagine rimuovere quando la memoria è piena,
- scarica le pagine non più necessarie (con eventuale salvataggio in swap se modificate).

#### **(4) Page fault: pagina richiesta non presente in RAM**

Quando un processo tenta di accedere a una pagina:

- **se la pagina è caricata** in memoria centrale → l'accesso procede normalmente e l'operazione si completa;
- **se la pagina NON è caricata** → si verifica un **page fault**: la MMU/SO segnala al processore la **mancanza** della pagina, e il sistema deve provvedere a:
    1. **individuare** la pagina logica necessaria (cercandola nell'area di swap);
    2. **caricarla** in una pagina fisica disponibile (eventualmente scaricandone un'altra);
    3. **aggiornare** la tabella delle pagine;
    4. **riprendere l'esecuzione** dell'istruzione che aveva provocato il fault, ora completabile.

---

### **7. Supporto hardware: la MMU**

#### **7.1. Perché serve l'hardware**

Le operazioni di traduzione indirizzo logico → fisico, controllo di presenza della pagina, generazione di trap in caso di page fault, ecc., devono essere eseguite **a ogni singolo accesso in memoria**. Realizzarle in **software** sarebbe inammissibile: il numero di operazioni necessarie per ogni indirizzamento sarebbe eccessivo, rendendo il sistema **inutilizzabile** per lentezza. Da qui la necessità di un dispositivo hardware dedicato: la **MMU**.

#### **7.2. Ruolo della MMU**

La **Memory Management Unit (MMU)** è l'elemento hardware che **implementa la paginazione**:

- contiene la **tabella delle pagine** del processo in esecuzione o, se troppo grande, un **puntatore** al suo indirizzo in memoria centrale;
- effettua la **traduzione automatica** degli indirizzi logici in indirizzi fisici a ogni accesso;
- rileva il **page fault** (accesso a pagina non caricata) e **solleva una trap** (interruzione software) per segnalare al processore la necessità di intervento.

#### **7.3. Context switch e cambio della tabella delle pagine**

La tabella delle pagine caricata nella MMU è **quella del processo correntemente in esecuzione**. Al **context switch**, oltre a salvare/ripristinare registri e stato CPU, è necessario **cambiare anche la tabella delle pagine** attiva nella MMU per puntare a quella del nuovo processo — altrimenti gli indirizzi logici del nuovo processo verrebbero tradotti usando la mappatura sbagliata.

#### **7.4. Gestione del page fault tramite trap**

Quando la MMU rileva che la pagina richiesta non è in RAM:

1. **solleva una trap** (interruzione software) verso il processore;
2. il sistema operativo intercetta la trap e attiva la corrispondente **routine di risposta all'interruzione**;
3. all'interno di tale routine vengono eseguite le operazioni di caricamento della pagina mancante;
4. al termine, il controllo torna all'istruzione che aveva provocato il fault, che ora può completare correttamente.

Grazie alla MMU, la paginazione è un meccanismo **rapido, sicuro e totalmente automatico**, che non richiede intervento da parte dei programmi utente.

---

### **8. Sintesi finale**

- La **paginazione** divide la memoria logica e fisica in blocchi di uguale dimensione.
    
- Permette di caricare **solo le porzioni utili** di un processo, ottimizzando l’uso della RAM.
    
- La **tabella delle pagine** definisce la corrispondenza logico-fisica per ogni processo.
    
- Il **sistema operativo** e la **MMU** gestiscono automaticamente il caricamento e la sostituzione delle pagine.
    
- La paginazione elimina la necessità di partizioni contigue e costituisce la base della moderna **memoria virtuale**.