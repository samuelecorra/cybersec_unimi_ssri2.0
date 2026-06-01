# **M1 UD3 Lezione 2 - Tecniche di sostituzione della pagina (parte 1)**

---

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

Quando un processo tenta di accedere a una pagina **non presente in memoria fisica**, si verifica una **mancanza di pagina** (*page fault*).  
Questo evento genera una **trap** (interruzione software) gestita dal sistema operativo.

#### **4.1. Esempio narrativo: pagina presente vs pagina assente**

Per chiarire il meccanismo, consideriamo due scenari:

##### **Caso A — accesso a una pagina presente (es. pagina logica 2)**

1. Il processore genera un indirizzo nella **pagina logica 2**;
2. l'indirizzo viene inviato alla **MMU**;
3. la MMU consulta la **tabella delle pagine** e trova che la pagina 2 è caricata: il **bit di validità** è impostato a **valido**, e la pagina è nel **frame 6**;
4. la MMU compone l'indirizzo fisico nel frame 6 e completa l'accesso normalmente.

![](imgs/Pasted%20image%2020260531234745.png)

##### **Caso B — accesso a una pagina assente (es. pagina logica 1)**

1. Il processore genera un indirizzo nella **pagina logica 1**;
2. l'indirizzo viene inviato alla MMU;
3. la MMU consulta la tabella delle pagine e trova il **bit di validità a "invalido"**;
4. la MMU **non può procedere** alla generazione dell'accesso → solleva una **trap di page fault** verso il processore.

> 📌 Nota: la pagina **esiste comunque nell'area di swap**, perché l'area di swap contiene **lo spazio di indirizzamento completo** del processo. Va solo "portata" in RAM.

![](imgs/Pasted%20image%2020260531235127.png)

#### **4.2. Sequenza di gestione del page fault**

1. Il processore rileva che la pagina richiesta non è presente nella tabella delle pagine (bit di validità a *invalido*).
2. La **MMU** genera una **trap (page fault)**.
3. Parte la **routine di risposta all'interruzione** del sistema operativo.
4. Il SO **localizza** la pagina mancante nell'**area di swap**.
5. Il SO trova un **frame libero** in memoria fisica.
6. La pagina viene **caricata** dall'area di swap nel frame libero.
7. La **tabella delle pagine** viene **aggiornata**: bit di validità a *valido* e numero di frame assegnato.
8. Il processo viene **riattivato** ripristinando il Program Counter dallo stack.

##### **Il dettaglio chiave: ripresa dell'istruzione fallita**

All'attivazione della trap, sullo **stack** era stato salvato **l'indirizzo dell'istruzione** in memoria centrale che aveva generato l'accesso fallito. Quando la routine di gestione del page fault termina, il PC viene ripristinato a tale indirizzo: il processore **ri-esegue esattamente l'istruzione** che era fallita, ora completabile perché la pagina è in RAM.

Il tutto avviene in modo **trasparente per il processo**, che non percepisce la mancanza temporanea della pagina.

---

### **5. Prestazioni della richiesta di paginazione**

Il tempo di accesso alla memoria dipende dalla probabilità che si verifichi una mancanza di pagina.

Sia:

- $( p )$ = probabilità di mancanza di pagina (*page fault rate*),
    
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
La scelta dipende dal suo stato di modifica, tracciato tramite il **bit di modifica** (*dirty bit*) gestito dalla **MMU** e associato a ciascuna pagina fisica.

#### **a. Frame non modificata**

- Il dirty bit è a 0: la pagina **non è cambiata** dall'ultimo caricamento (è stata solo letta o eseguita).
- Il contenuto **coincide** con la copia presente nell'area di swap.
- Il frame può quindi essere **semplicemente rimosso** dalla memoria fisica, senza alcun salvataggio.

#### **b. Frame modificata**

- Il dirty bit è a 1: la pagina è stata **alterata** durante l'esecuzione.
- Prima di essere rimossa, deve essere **scritta nell'area di swap** per non perdere i dati aggiornati.

##### **Ottimizzazione: buffer delle pagine modificate**

Il processo di scaricamento può essere **lento** (richiede I/O su disco), e il sistema potrebbe dover attendere molto tempo prima di liberare il frame. Per velocizzare, alcuni sistemi operativi adottano una soluzione a due passi:

1. il contenuto del frame da scaricare viene prima **copiato in un buffer** nella memoria centrale del SO;
2. la pagina fisica **viene immediatamente liberata** appena completata la copia, e può essere riutilizzata subito;
3. il SO provvede poi al salvataggio del buffer nell'area di swap "**con calma**", in background.

In questo modo la liberazione del frame non è bloccata dal tempo di I/O su disco.

#### **c. Frame residenti**

- Alcune pagine, come quelle del **kernel** o strutture critiche del sistema operativo, **non possono mai essere rimosse** dalla memoria.
- Esse sono dette **residenti** e restano sempre in RAM perché contengono informazioni di **uso estremamente frequente** da parte del sistema operativo.

---

### **7. Sintesi finale**

- Il **caricamento** e lo **scaricamento** delle pagine consentono di gestire la memoria virtuale in modo dinamico.
    
- Ogni accesso a una pagina non presente genera un **page fault**, gestito automaticamente dal sistema operativo.
    
- Il **tempo medio di accesso** dipende fortemente dal numero di fault.
    
- Le **pagine modificate** devono essere salvate su disco prima della sostituzione.
    
- Le **pagine residenti** sono invece permanenti in memoria.

Queste basi preparano lo studio della **seconda parte** della lezione, dedicata alle **politiche di sostituzione delle pagine**.