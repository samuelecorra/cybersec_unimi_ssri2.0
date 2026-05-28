# **M3 UD2 Lezione 1 - Struttura e realizzazione del file system**

---

### **1. Introduzione**

In questa lezione iniziano gli aspetti relativi alla **realizzazione del file system**, con attenzione alla struttura generale e ai criteri fondamentali di implementazione.

L'obiettivo è capire come il sistema operativo trasformi la memorizzazione fisica a blocchi dei dispositivi di massa nella visione logica di file, directory e percorsi usata dai processi.

In particolare vengono analizzati:

- obiettivo principale del file system;
- differenza tra visione fisica e visione logica;
- struttura modulare della gestione del file system;
- strutture dati su disco;
- strutture dati in memoria centrale;
- apertura e accesso ai file;
- file system virtuale.

> 📌 Il file system realizza astrazione e virtualizzazione: nasconde i blocchi fisici del disco e presenta agli utenti flussi logici di informazioni chiamati file.

---

### **2. Obiettivo del file system**

L'obiettivo del file system è gestire in modo omogeneo tutte le risorse informative e fisiche del sistema di elaborazione, facendole apparire a livello astratto come **flussi di informazioni elementari**.

Le risorse informative sono i file propriamente detti.

Le risorse fisiche, come le periferiche, possono essere viste anch'esse come flussi omogenei di informazioni trasferite verso o dalla periferica.

In questa unità l'attenzione è posta sulle informazioni memorizzate in memoria secondaria, in particolare su disco. I concetti sono però estendibili anche alle altre risorse fisiche gestite dal sistema operativo.

---

### **3. Supporto fisico e visione utente**

#### **3.1. Supporto fisico**

Il supporto fisico del file system è costituito dalla memoria secondaria.

I dischi memorizzano informazioni sotto forma di **blocchi**. In alcuni casi possono essere divisi in partizioni, per gestire più efficientemente grandi strutture fisiche.

Dal punto di vista fisico:

- il disco è organizzato in blocchi;
- le operazioni elementari sono letture e scritture di blocchi;
- la posizione dei dati è fisica e dipende dalla memoria di massa.

#### **3.2. Visione utente**

Dal punto di vista dell'utente, invece, le informazioni sono **file**: flussi di elementi omogenei, in quantità non determinata a priori.

L'utente vuole memorizzare elementi di un certo tipo base, definito dall'applicazione, senza occuparsi di come tali elementi siano distribuiti nei blocchi fisici.

$$
\text{file system}: \text{blocchi fisici su disco} \longrightarrow \text{file logici}
$$

> ⚠️ La visione fisica e la visione logica sono profondamente diverse: il file system deve tradurre continuamente tra blocchi e flussi di elementi.

---

### **4. Struttura della gestione del file system**

La gestione del file system è organizzata in livelli.

#### **4.1. Gestione della periferica**

Il livello più basso si occupa della comunicazione con il dispositivo fisico.

Comprende:

- gestione dipendente dal dispositivo;
- gestione indipendente dal dispositivo;
- driver;
- comunicazione con il sottosistema di I/O.

Questa parte appartiene alla gestione delle periferiche vista nel modulo precedente.

#### **4.2. File system di base**

Il **file system di base** organizza le operazioni di lettura e scrittura dei blocchi fisici.

Il suo compito è garantire che:

- le informazioni necessarie siano lette dal supporto;
- le informazioni modificate siano riscritte correttamente;
- l'accesso ai blocchi fisici sia gestito in modo ordinato.

> 📌 Il file system di base legge e scrive blocchi fisici, senza ancora ricostruire la visione logica completa del file.

#### **4.3. Modulo di organizzazione dei file**

Il **modulo di organizzazione dei file** ricostruisce la sequenza dei blocchi fisici che compongono il file.

Si occupa di sapere:

- quali blocchi appartengono a un file;
- in quale ordine devono essere letti;
- come concatenarli per costruire il flusso logico di elementi;
- come gestire lo spazio occupato dai file.

#### **4.4. File system logico**

Il **file system logico** crea la visione astratta del reperimento delle informazioni.

Gestisce i metadati necessari per:

- navigare directory e sottodirectory;
- identificare un file tramite un percorso;
- associare nomi simbolici a descrittori;
- applicare controlli e proprietà logiche.

> 💡 I livelli inferiori gestiscono blocchi; il file system logico gestisce nomi, percorsi, directory e metadati.

---

### **5. Strutture dati su disco**

Per supportare la gestione del file system vengono usate strutture dati persistenti, memorizzate su disco.

Queste informazioni devono sopravvivere allo spegnimento del sistema, così che all'accensione il sistema operativo possa ricostruire la struttura logica e fisica dei file.

#### **5.1. Blocco di controllo del boot**

Il **blocco di controllo del boot** contiene informazioni necessarie alla partenza del sistema operativo.

Può contenere codice di bootstrap o riferimenti al caricatore del sistema.

#### **5.2. Blocco di controllo della partizione**

Il **blocco di controllo della partizione** contiene informazioni sull'organizzazione fisica del disco e sulla sua eventuale suddivisione in partizioni.

#### **5.3. Directory**

Le directory conservano la struttura logica del file system.

Ricordiamo che una directory è un file particolare il cui tipo base è il descrittore di file.

#### **5.4. Blocchi di controllo dei file**

I **blocchi di controllo dei file** contengono le informazioni necessarie a descrivere i singoli file.

Possono includere:

- permessi;
- nome;
- proprietario;
- date di creazione, modifica e accesso;
- dimensione;
- diritti di accesso;
- riferimenti ai blocchi dati;
- informazioni per ricostruire l'ordine dei blocchi.

#### **5.5. Blocchi dati**

I blocchi dati contengono effettivamente le informazioni dei file.

Il file system deve collegare questi blocchi nell'ordine corretto per ricostruire il flusso logico visto dal processo applicativo.

---

### **6. Strutture dati in memoria centrale**

Durante l'esecuzione, il sistema operativo mantiene in memoria centrale varie strutture dati per rendere più efficiente l'accesso al file system.

#### **6.1. Tabella delle partizioni**

La **tabella delle partizioni** descrive la struttura della memoria di massa.

Permette di conoscere la configurazione dei dischi senza dover leggere ogni volta le strutture persistenti sul supporto.

#### **6.2. Tabelle dei descrittori delle directory**

Le tabelle dei descrittori delle directory conservano informazioni operative sulle directory considerate dal sistema.

Servono a velocizzare navigazione e ricerca nella struttura logica.

#### **6.3. Tabella globale dei file aperti**

La **tabella globale dei file aperti** contiene informazioni sui file aperti nel sistema da tutti i processi.

Conserva dati comuni relativi a un file aperto, evitando di leggere continuamente dal disco il blocco di controllo del file.

> 💡 La tabella globale dei file aperti funziona come una cache delle informazioni contenute nei blocchi di controllo dei file.

#### **6.4. Tabella dei file aperti per processo**

Ogni processo ha una propria tabella dei file aperti.

Questa tabella conserva informazioni specifiche del processo, come:

- puntatore alla posizione corrente;
- modalità di apertura;
- riferimenti alla tabella globale;
- informazioni specifiche di accesso.

Se più processi condividono lo stesso file, le informazioni comuni stanno nella tabella globale, mentre quelle specifiche di ciascun processo restano nelle rispettive tabelle locali.

#### **6.5. Tabella di montaggio dei file system**

La **tabella di montaggio** permette di risolvere rapidamente i percorsi nei diversi file system montati.

Serve a tradurre un nome simbolico in un accesso fisico, attraversando eventualmente file system presenti su dischi diversi o volumi diversi.

---

### **7. Apertura di un file**

Quando un processo apre un file, il sistema operativo crea e aggiorna diverse strutture dati in memoria centrale.

Il processo specifica un nome di file. Il sistema operativo:

1. cerca il file nell'albero delle directory;
2. individua il descrittore del file;
3. porta in memoria centrale le informazioni necessarie;
4. raggiunge il blocco di controllo del file;
5. crea o aggiorna la tabella globale dei file aperti;
6. crea un elemento nella tabella dei file aperti del processo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il blocco di controllo del file contiene le informazioni essenziali per sapere dove reperire i blocchi fisici dei dati nella memoria di massa.

---

### **8. Lettura e scrittura**

Quando il processo esegue una lettura o una scrittura, usa le strutture create durante l'apertura.

Nella tabella dei file aperti del processo si trova il puntatore alla posizione corrente nel file e il riferimento alla tabella globale dei file aperti.

La tabella globale contiene le informazioni comuni sul file, tra cui i riferimenti ai blocchi dati e l'ordine con cui devono essere letti per ricostruire il flusso logico.

$$
\text{processo}
\rightarrow
\text{tabella file aperti del processo}
\rightarrow
\text{tabella globale file aperti}
\rightarrow
\text{blocco di controllo del file}
\rightarrow
\text{blocchi dati}
$$

> 📌 Le strutture in memoria riducono gli accessi ripetuti al disco per recuperare metadati e rendono più rapido l'accesso ai singoli elementi logici del file.

---

### **9. File system virtuale**

Il file system può essere distribuito su più dischi fisici o persino su macchine remote.

Il processo, però, dovrebbe poter usare un'unica interfaccia senza sapere dove si trovano fisicamente i file.

Il **file system virtuale** (**VFS**) introduce un livello di astrazione che incapsula le differenze tra:

- file system locali;
- file system remoti;
- file system di rete;
- file system distribuiti;
- implementazioni diverse del file system.

Il VFS traduce le richieste generiche dei processi in richieste specifiche per il file system concreto coinvolto.

> ✅ Il file system virtuale rende indipendente l'accesso logico al file dalla sua locazione fisica e dalla specifica implementazione del file system.

---

### **10. Sintesi finale**

> ✅ La realizzazione del file system consiste nel trasformare blocchi fisici di memoria secondaria in file logici, directory e percorsi accessibili dai processi.

In questa lezione sono stati analizzati:

- obiettivo del file system;
- differenza tra supporto fisico a blocchi e visione utente come file;
- gestione della periferica;
- file system di base;
- modulo di organizzazione dei file;
- file system logico;
- strutture dati su disco;
- strutture dati in memoria centrale;
- apertura, lettura e scrittura dei file;
- file system virtuale.

La struttura multilivello consente al sistema operativo di separare gestione fisica, organizzazione dei blocchi e visione logica delle informazioni.
