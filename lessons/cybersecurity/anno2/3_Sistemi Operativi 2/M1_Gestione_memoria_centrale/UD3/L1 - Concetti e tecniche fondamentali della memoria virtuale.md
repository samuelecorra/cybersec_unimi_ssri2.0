# **M1 UD3 Lezione 1 - Concetti e tecniche fondamentali della memoria virtuale**

---

### **1. Introduzione**

La **memoria virtuale** rappresenta una delle evoluzioni più importanti nella gestione della memoria centrale.
Il suo scopo è **superare i limiti fisici della RAM**, consentendo a ciascun processo di percepire uno **spazio di memoria molto più grande e continuo** di quello realmente disponibile.

Attraverso opportuni meccanismi di **virtualizzazione e mappaggio**, il sistema operativo garantisce a ogni processo l’illusione di avere a disposizione l’intero spazio di indirizzamento del processore, isolato dagli altri processi.

---

### **2. Problemi di fondo**

Le tecniche di memoria virtuale nascono per risolvere alcune limitazioni strutturali dei sistemi multiprogrammati:

1. **Dimensioni ridotte della memoria fisica** rispetto allo spazio di indirizzamento richiesto dai programmi moderni — limita la possibilità che i programmi vengano caricati ed eseguiti.
2. **Multiprogrammazione con grado elevato**: più processi devono convivere in memoria condividendo le stesse risorse, in modo da garantire la presenza di processi pronti sufficienti a **sfruttare al massimo il processore**.
3. **Risparmio di memoria**: si vuole abilitare la **condivisione di memoria** tra processi, evitando duplicazioni inutili (es. codice di librerie comuni).
4. **Protezione**: ogni processo deve essere isolato dagli altri, impedendo letture o scritture non lecite verso lo spazio altrui.
5. **Gestione efficiente**: l'assegnazione e il rilascio della memoria devono essere dinamici e automatizzati, minimizzando il sovraccarico operativo.
6. **Supporto al time-sharing e al real-time**: il sistema deve permettere l'alternanza rapida dei processi in esecuzione, e — per molte applicazioni di **interazione in tempo reale** con l'ambiente esterno — è cruciale **minimizzare il tempo di gestione** della memoria virtuale per non compromettere la reattività.

---

### **3. Obiettivo della memoria virtuale**

Come per ogni componente della macchina di von Neumann, il sistema operativo deve creare per la memoria centrale una **astrazione** e una **virtualizzazione** del dispositivo. Sono i due obiettivi complementari della memoria virtuale.

#### **3.1. Astrazione della memoria centrale**

L'astrazione consiste nel mostrare ai processi una memoria con caratteristiche **migliori** di quella fisica reale:

- **più grande** della memoria fisica realmente assegnata al processo;
- **indipendente** dalla quantità di memoria fisica installata nel sistema;
- **gestita in modo efficiente**, riducendo sovraccarico e spreco di tempo per attività non strettamente legate alla computazione.

#### **3.2. Virtualizzazione della memoria centrale**

La virtualizzazione consiste nel mostrare a ogni processo una memoria che **appare interamente sua**:

- ogni processo può utilizzare **tutto lo spazio di indirizzamento del processore** (il massimo indirizzabile);
- ogni processo **ignora la presenza degli altri**, credendo di disporre di una memoria privata e continua.

Insieme, astrazione e virtualizzazione garantiscono che la gestione sia **completamente trasparente** per i processi: nessun intervento del programmatore, nessuna percezione del fatto che la memoria fisica sia limitata e condivisa.

---

### **4. Concetto di memoria virtuale**

La memoria virtuale è un **insieme di tecniche e politiche** che permettono di creare per ogni processo una visione dedicata e sicura dello spazio di memoria.

#### **4.1. Lo spazio di indirizzamento del processore**

Un processore dispone di un **bus degli indirizzi** di $N$ bit; può quindi indirizzare al massimo $2^N$ byte distinti di memoria. Questo definisce il suo **spazio di indirizzamento massimo**:

$$
\text{Spazio di indirizzamento del processore} = 2^N \text{ byte}
$$

Nella stragrande maggioranza dei sistemi reali, la memoria **fisicamente installata** è una quantità **molto più piccola** di $2^N$:

$$
\text{Memoria fisica installata} \ll 2^N
$$

#### **4.2. Definizione di memoria virtuale**

Ogni processo $P_1, P_2, \dots, P_k$ vorrà utilizzare uno spazio di memoria per il proprio codice, dati, heap e stack. Ciascuno di questi processi deve poter vedere uno spazio **fino a $2^N$ byte** (l'intero spazio di indirizzamento del processore).

> 📌 **Definizione operativa.** L'**insieme degli spazi di indirizzamento dei vari processi** del sistema costituisce la **memoria virtuale**: una memoria *concettuale* enormemente più grande della RAM fisica, di cui si occupa il sistema operativo.

#### **4.3. Caratteristiche principali**

- dimensione pari allo **spazio di indirizzamento del processore** ($2^N$);
- **dedicata** a ciascun processo;
- **protetta** dagli accessi di altri processi;
- **gestita in modo efficiente** dal sistema operativo;
- con **porzioni eventualmente condivise**, per esempio librerie comuni o segmenti di codice condiviso.

---

### **5. Struttura e funzionamento**

Per realizzare la memoria virtuale, lo spazio di indirizzamento di ciascun processo viene suddiviso in **porzioni logiche** (pagine o segmenti), che vengono poi **mappate** in modo controllato sui **frame fisici** della memoria centrale.

#### **Principi di funzionamento**

1. Lo **spazio logico del processo** è diviso in pagine o segmenti.
    
2. La **memoria centrale fisica** è divisa in **frame** di uguale dimensione.
    
3. Ogni porzione logica viene **mappata** su un frame tramite una **tabella di corrispondenza** gestita dalla **MMU**.
    
4. Solo le porzioni **necessarie nel prossimo futuro** vengono caricate in memoria centrale (per processi *ready* o *running*).
    
5. Le porzioni non necessarie restano memorizzate nell’**area di swap** su disco, da cui possono essere recuperate in caso di bisogno.

#### **Schema concettuale**

$$  
\text{Memoria virtuale}  
\longleftrightarrow  
\text{Mappaggio}  
\longleftrightarrow  
\text{Memoria centrale fisica}  
\longleftrightarrow  
\text{Area di swap}  
$$

In questo modo, la memoria virtuale realizza un **meccanismo di estensione dinamica** della RAM, caricato su richiesta e invisibile ai processi.

#### **5.1. Tre scenari operativi del mapping**

Quando il processo accede a una porzione del proprio spazio virtuale, la MMU effettua la traduzione e si possono presentare tre scenari distinti:

##### **Scenario A — Pagina presente in RAM**

Si fa direttamente riferimento al frame fisico e l'operazione di lettura o scrittura viene completata normalmente.

##### **Scenario B — Pagina non presente, frame libero disponibile**

La pagina (es. mai utilizzata prima, oppure precedentemente scaricata) deve essere caricata dall'area di swap:

1. il SO **individua un frame libero** in memoria centrale;
2. **carica** la pagina dallo swap nel frame libero;
3. **aggiorna** la tabella di corrispondenza;
4. **riprende** l'accesso che era stato sospeso.

##### **Scenario C — Pagina non presente, nessun frame libero (il caso più complesso)**

Il SO deve liberare un frame prima di poter caricare la nuova pagina:

1. **sceglie un frame "vittima"** secondo una politica di sostituzione;
2. se la vittima è stata modificata, la **scrive in swap** prima di rimuoverla;
3. **carica** la pagina richiesta nel frame appena liberato;
4. **aggiorna** la tabella e riprende l'esecuzione.

Questo terzo scenario è quello che richiede le **politiche di sostituzione delle pagine** (oggetto delle prossime lezioni).

---

### **6. Tecniche per la realizzazione della memoria virtuale**

La memoria virtuale si realizza componendo un insieme di **tecniche di base** (già viste nelle UD precedenti) con un insieme di **politiche e meccanismi specifici** della VM.

#### **6.1. Tecniche di base (dividono e mappano la memoria)**

Queste tecniche definiscono **come** lo spazio logico viene diviso in porzioni e **mappato** sulla memoria centrale:

- **Paginazione**,
- **Segmentazione**,
- **Segmentazione con paginazione**,
- **Swapping** (caricamento e scaricamento delle porzioni tra RAM e disco).

#### **6.2. Politiche e meccanismi specifici della memoria virtuale**

Sopra le tecniche di base, la VM aggiunge un livello di **gestione intelligente** delle pagine in memoria:

- **Meccanismi per il rilevamento di mancanze di frame** (*page fault detection*): la MMU si accorge quando una porzione richiesta non è caricata e solleva una trap.
- **Meccanismi per il caricamento dei frame**: la routine che effettivamente trasferisce la porzione dallo swap alla RAM al verificarsi di un page fault.
- **Politiche di sostituzione delle pagine**: quale pagina rimuovere quando serve liberare un frame e non ce ne sono di liberi (FIFO, LRU, Optimal, …).
- **Politiche di allocazione dei frame**: quanti frame allocare a ciascun processo, e su quale criterio.
- **Politiche di selezione delle porzioni da caricare**: scelta delle pagine da caricare in anticipo (es. *prepaging*) o solo on-demand.
- **Gestione del thrashing**: situazioni patologiche in cui il sistema spende più tempo a fare swap che a eseguire computazione utile.

Ciascuna di queste componenti mira a bilanciare **prestazioni, efficienza e isolamento**, evitando che le limitazioni fisiche della RAM compromettano l'esecuzione dei processi. Le prossime lezioni di questa UD esploreranno in dettaglio politiche di sostituzione, allocazione e gestione del thrashing.

---

### **7. Sintesi finale**

- La **memoria virtuale** è un’astrazione che permette a ogni processo di percepire un proprio spazio di memoria indipendente e continuo.
    
- Consente di **superare i limiti fisici** della memoria centrale, garantendo protezione e condivisione controllata.
    
- Viene realizzata tramite tecniche di **paginazione, segmentazione o combinazioni** delle due, gestite dal sistema operativo e supportate dalla **MMU**.
    
- È la base della **multiprogrammazione moderna**, permettendo l'esecuzione efficiente di molti processi contemporanei anche su sistemi con memoria limitata.
