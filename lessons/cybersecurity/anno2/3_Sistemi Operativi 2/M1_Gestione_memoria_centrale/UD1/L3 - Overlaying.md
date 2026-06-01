# **M1 UD1 Lezione 3 - Overlaying**

---

### **1. Introduzione**

La tecnica dell’**overlaying** nasce per superare un limite strutturale dei sistemi più datati: la **memoria centrale fisica** disponibile può essere inferiore alla dimensione del **programma logico** da eseguire.  
L’idea è di caricare in RAM solo le **porzioni del programma effettivamente necessarie** in un dato momento, liberando e ricaricando dinamicamente le altre quando servono.

Questa soluzione consente di eseguire programmi più grandi della memoria fisica, pur mantenendo l’esecuzione corretta e continua.

---

### **2. Problema di fondo**

Nei primi sistemi, la **dimensione fisica della memoria centrale assegnata a un processo** era spesso insufficiente a contenere tutto il suo **spazio logico di indirizzamento** (codice, dati globali, heap, stack).

#### **2.1. Il doppio limite del partizionamento**

Il partizionamento (visto nella lezione precedente) permette il multitasking, **ma non aumenta lo spazio per processo — anzi, lo riduce**:

- se il processo fosse l'**unico** caricato in memoria, vedrebbe disponibile **tutta la RAM**, eccetto lo spazio del sistema operativo;
- con il partizionamento, la **stessa** memoria fisica viene ripartita tra **più processi** → ogni processo riceve **meno spazio** di quanto otterrebbe se fosse l'unico nel sistema.

Questo è il prezzo che si paga per il multitasking. Ma in entrambi i casi — singolo o più processi — il processo **può comunque eccedere lo spazio disponibile** se le sue richieste superano quanto allocato.

#### **2.2. Problema della portabilità**

Un processo che gira correttamente su una macchina **può non girare su un'altra** — o addirittura **sulla stessa**, ma con un partizionamento diverso — semplicemente perché lo spazio fisico assegnatogli risulta insufficiente. La portabilità del software viene quindi vincolata a un **dettaglio hardware non significativo**: la quantità fisica di memoria messa a disposizione al momento del caricamento.

Ciò comporta vari vincoli:

- Non tutti i processi possono essere eseguiti contemporaneamente.
- L'eseguibilità dipende fortemente dall'hardware e dalla quantità di memoria installata.
- I programmi diventano **poco portabili**, poiché il loro funzionamento è legato alle dimensioni fisiche della memoria.

#### **Obiettivo dell'overlaying**

Permettere a un processo di avere **uno spazio logico più grande dello spazio fisico** che gli è stato allocato,  
caricando in memoria **solo la parte del codice e dei dati necessaria nell'immediato futuro** ed eliminando quelle che non lo sono.

---

### **3. Concetto di Overlaying**

#### **Overlaying (1)**

Il principio base è **identificare le porzioni di codice e dati** di un programma che vengono:

- utilizzate **sempre**, e devono quindi rimanere residenti in memoria;
- utilizzate **in modo mutuamente esclusivo**, cioè mai contemporaneamente (queste porzioni formano gli _overlay_).

In memoria centrale vengono quindi predisposte due aree:

1. **Spazio fisso**, che ospita il codice e i dati sempre necessari;
2. **Spazio variabile**, dove vengono caricati di volta in volta gli overlay necessari alla fase corrente del programma.

##### **Cosa va e cosa NON va negli overlay**

- ✅ **Codice** delle funzioni mutuamente esclusive (ad es. moduli usati in fasi diverse del programma).
- ✅ **Dati globali** mutuamente esclusivi che servono solo in certe fasi.
- ❌ **Heap** e **stack** **NON** vengono posti in overlay: sono utilizzati da **tutto il programma** durante tutta l'esecuzione, quindi devono restare costantemente accessibili in memoria centrale.
    

---

#### **Overlaying (2)**

Durante l'esecuzione:

- il sistema carica solo le **porzioni attualmente necessarie** (quelle usate sempre e quelle richieste dal flusso di esecuzione immediato);
- gli overlay **non più utili vengono salvati temporaneamente** su memoria secondaria (ad esempio su disco) per liberare spazio;
- quando serve un nuovo overlay, esso **rimpiazza** quello precedente nello stesso spazio di memoria.

##### **Cosa serve salvare quando un overlay viene rimosso**

Quando un overlay deve essere scaricato per fare spazio a un altro, bisogna **salvare in una memoria temporanea** solo ciò che è stato **modificato** durante l'uso — tipicamente i **dati globali** dell'overlay che il programma ha aggiornato.

> ℹ️ **Ipotesi del corso.** Si assume che **il codice non sia modificabile** durante l'esecuzione (caso dei linguaggi convenzionali come C, C++, Pascal, Fortran). Quindi il codice degli overlay scaricati **non va salvato**: è già su disco nell'eseguibile e può essere ricaricato identico. Se il linguaggio supportasse codice **automodificante**, anche le porzioni di codice modificate andrebbero salvate.

Questa logica consente di **riutilizzare la stessa area fisica di memoria** per caricare porzioni di codice differenti nel tempo.

---

#### **Overlaying (3)**

Quando un programma richiama una funzione appartenente a un overlay non ancora presente in memoria:

1. Il sistema riconosce la necessità di caricare l'overlay corrispondente.
2. Vengono **scaricate** le porzioni non più necessarie (con salvataggio dei dati globali modificati).
3. Viene **caricato** il nuovo overlay al loro posto.
4. L'esecuzione prosegue senza interruzioni apparenti per l'utente.

---

#### **3.1. Struttura della memoria centrale durante l'overlaying**

Tipicamente, in memoria centrale durante l'overlaying troviamo quattro elementi convivere:

![](imgs/Pasted%20image%2020260531204429.png)

$$
\begin{cases}
\textbf{Parte comune del programma:} & \text{codice e dati sempre residenti (incluso heap e stack).} \\\\
\textbf{Tabella dei simboli:} & \text{associa ogni nome simbolico al suo overlay di appartenenza.} \\\\
\textbf{Codice della libreria di gestione:} & \text{routine fornite dalla libreria di sistema per gestire load/unload.} \\\\
\textbf{Area degli overlay:} & \text{spazio riservato dove vengono caricati di volta in volta gli overlay.}
\end{cases}
$$

##### **Ruolo della tabella dei simboli**

Quando il programma fa riferimento a una procedura o a una variabile che **non è** nella parte comune ma si trova in un overlay, la **tabella dei simboli** consente di:

- riconoscere che il riferimento punta a un overlay,
- identificare **quale specifico overlay** contiene quella procedura/variabile,
- delegare alla libreria di gestione il caricamento dell'overlay corretto.

##### **Dinamica dell'area overlay**

L'area di overlay è una **finestra fisica unica** in cui vengono caricati overlay diversi nel tempo: ad esempio, durante una fase usiamo l'overlay $O_1$; quando serve $O_2$, $O_1$ viene rimosso e $O_2$ viene caricato **nella stessa area fisica**.
    

---

### **4. Caratteristiche principali**

#### **a. Gestione dello spazio**

- L’overlaying consente di **ripiegare lo spazio logico** di un processo su uno spazio fisico più piccolo.
    
- In pratica, la memoria centrale ospita solo la parte “attiva” del programma in quel momento.
    

#### **b. Ruolo del programmatore**

- È il **programmatore** (o l’ambiente di sviluppo) a dover **identificare le sezioni sovrapponibili** di codice e dati.
    
- Occorre progettare overlay **omogenei**, cioè di dimensioni simili, per ridurre sprechi e frammentazione.
    

#### **c. Supporto del compilatore e delle librerie**

- Il **compilatore**, una volta istruito dal programmatore su quali porzioni sono mutuamente esclusive:
  - inserisce automaticamente le **chiamate di gestione** necessarie al caricamento e scaricamento degli overlay;
  - collega le funzioni della **libreria di gestione dell'overlay**;
  - costruisce la **tabella dei simboli** con le associazioni nome → overlay.
- Sono spesso fornite **librerie di gestione dell'overlaying** che semplificano il compito del programmatore.

##### **Meccanismo del controllo prima della chiamata**

Per ogni **chiamata di procedura** che ricade in zona di overlay, il compilatore introduce nel codice eseguibile delle **operazioni di verifica** che vengono effettuate **prima** della chiamata effettiva:

1. si verifica che l'overlay che contiene la procedura sia **già presente** in memoria centrale;
2. se **non è presente**: si attiva la routine della libreria che:
   - **scarica** eventualmente gli overlay correnti (salvando i dati globali modificati);
   - **carica** dall'eseguibile su disco l'overlay richiesto nell'area dedicata.
3. solo a questo punto si esegue la **chiamata effettiva** della procedura.

Al termine della procedura, il controllo torna alla procedura chiamante; se quest'ultima dovrà a sua volta invocare una procedura in un altro overlay, il meccanismo si ripete.
    

#### **d. Tipologie di overlay**

##### **Overlay multipli**

Nella memoria centrale a disposizione del processo si possono avere **più aree di overlay indipendenti**, ciascuna con la propria "finestra" dinamica in cui caricare uno tra più overlay alternativi. Ad esempio:

- area $A_1$ → carica alternativamente uno tra $\{O_{1a}, O_{1b}, O_{1c}, \dots\}$;
- area $A_2$ → carica alternativamente uno tra $\{O_{2a}, O_{2b}, \dots\}$;
- e così via.

Le due aree coesistono e operano in modo indipendente.

##### **Overlay gerarchici**

All'interno di un'area di overlay si può definire una **sotto-area** in cui caricare ulteriori overlay, "innestati" rispetto a quello di primo livello.

- Nell'area di primo livello viene caricato un overlay $O$;
- all'interno di $O$ si individua una sotto-area che a sua volta può ospitare overlay $O'_1, O'_2, \dots$ utilizzabili dalle procedure di $O$;
- si crea così una **gerarchia di livelli** di overlay.

> ⚠️ **Proprietà importante.** Quando l'overlay di **primo livello viene rimosso** dalla memoria, **automaticamente vengono rimossi anche** tutti gli overlay innestati di livello gerarchico superiore: non avrebbero più senso senza il loro contenitore.
    

---

### **5. In sintesi**

- L’**overlaying** è una tecnica che consente a un processo di avere **uno spazio logico superiore** alla memoria fisica disponibile.
    
- Funziona **caricando e rimpiazzando dinamicamente** le sezioni del programma in base alle necessità del flusso di esecuzione.
    
- Deve essere **gestito dal programmatore**, con il supporto del **compilatore** e delle **librerie del linguaggio**.
    
- Ha rappresentato una **tappa fondamentale** nello sviluppo delle moderne tecniche di **memoria virtuale**, che automatizzano completamente il processo di caricamento e sostituzione delle porzioni di codice.