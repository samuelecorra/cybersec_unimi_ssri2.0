# **M3 UD1 Lezione 1 - File e loro caratteristiche**

---

### **1. Introduzione al file system**

Il **file system** è l'astrazione del sistema operativo che permette di accedere alle risorse informative e fisiche in modo **logico**, invece che tramite la loro posizione fisica nei dispositivi.

Fornisce una visione uniforme dei dispositivi di memorizzazione e trasferimento delle informazioni, così che dati e programmi possano essere reperiti tramite nomi, directory e posizioni logiche.

In questa lezione vengono analizzati:

- concetto di file system;
- concetto di file;
- tipi di file;
- struttura dei file;
- attributi e descrittori;
- operazioni sui file;
- uso dei file;
- metodi di accesso.

> 📌 Il file system nasconde la posizione fisica delle informazioni e offre a utenti e programmi una visione logica, omogenea e persistente dei dati.

---

### **2. File system**

Il file system è una visione logica dei dispositivi di memorizzazione e di trasferimento delle informazioni.

I suoi obiettivi principali sono:

- fornire un accesso omogeneo a dispositivi diversi;
- supportare la memorizzazione di lungo termine;
- supportare il trasferimento di informazioni verso l'esterno e dall'esterno;
- semplificare la progettazione dei programmi applicativi;
- semplificare l'uso del sistema da parte degli utenti.

Il file system consiste nella gestione di:

- **file**, cioè collezioni di dati e programmi;
- **directory**, cioè metainformazioni usate per organizzare i file in sottoinsiemi logici.

---

### **3. Aggregazione delle informazioni**

Nei linguaggi di programmazione esistono diversi costruttori per aggregare informazioni.

#### **3.1. Array**

Un **array** raccoglie $N$ elementi omogenei, tutti dello stesso tipo base.

La quantità di elementi è nota a priori:

$$
\text{array} = \{e_0, e_1, \dots, e_{N-1}\}
$$

#### **3.2. Record**

Un **record** raccoglie campi potenzialmente eterogenei.

Ogni campo può avere un tipo diverso, ma il numero dei campi e la loro struttura sono definiti a priori.

#### **3.3. File**

Un **file** è un costruttore che raccoglie elementi omogenei, tutti dello stesso tipo base, ma in quantità non definita a priori.

A differenza dell'array, il numero di elementi del file si conosce solo quando l'oggetto viene creato e usato.

> 📌 Un file è una sequenza di elementi omogenei di lunghezza variabile, gestita come unità logica persistente.

---

### **4. Concetto di file**

Un file è una sequenza di elementi logici. Ogni elemento ha una posizione nella sequenza, tipicamente numerata a partire da $0$.

Il tipo base degli elementi può essere:

- numerico;
- alfabetico;
- binario;
- definito dal programmatore;
- una struttura più complessa;
- codice programma.

Il file system associa a ogni file un nome simbolico, una posizione logica nel sistema e un insieme di metadati utili alla gestione.

---

### **5. Tipi di file**

I file possono contenere dati o programmi. Spesso il tipo viene indicato anche tramite l'estensione nel nome.

Esempi comuni:

- file eseguibili: `.exe`, `.com`, `.bin`;
- file oggetto: `.obj`, `.o`;
- codice sorgente: `.c`, `.cc`, `.java`;
- file batch o script: `.bat`, `.sh`;
- testo puro: `.txt`;
- testo formattato o documenti: `.doc`;
- librerie: `.lib`, `.a`, `.so`, `.dll`;
- immagini: `.jpg`, `.gif`, `.png`;
- documenti impaginati: `.pdf`;
- archivi: `.zip`, `.arc`, `.tar`, `.gz`;
- file multimediali: audio e video.

> 💡 L'estensione non è il contenuto del file: è una convenzione utile a sistema operativo, applicazioni e utenti per riconoscere il tipo di informazione.

---

### **6. Struttura dei file**

Un file può avere strutture diverse a seconda delle informazioni che contiene.

#### **6.1. File senza struttura**

Il file può essere una semplice sequenza di elementi del tipo base predefinito, senza struttura interna visibile al sistema operativo.

Questo approccio lascia all'applicazione il compito di interpretare il contenuto.

#### **6.2. Struttura semplice**

Il file può essere organizzato in elementi semplici, come linee a lunghezza fissa o variabile.

Questa soluzione è tipica dei file di testo.

#### **6.3. Struttura complessa**

Il file può avere una struttura complessa, con:

- intestazione iniziale;
- informazioni sul contenuto;
- metadati interni;
- dati organizzati secondo un formato specifico.

Esempi sono documenti formattati, archivi, file eseguibili e formati multimediali.

---

### **7. Attributi dei file**

Un file è caratterizzato da informazioni che ne supportano l'uso semplice ed efficiente. Queste informazioni sono dette **attributi**.

Attributi tipici:

- **nome**, cioè identificatore simbolico del file;
- **identificatore**, cioè numero o riferimento interno al sistema;
- **tipo**, che caratterizza il contenuto;
- **locazione**, cioè posizione fisica in memoria di massa;
- **dimensione**;
- **data di creazione**;
- **data di ultimo accesso**;
- **data di ultima modifica**;
- **proprietario**;
- **protezioni**, cioè permessi di lettura, scrittura, esecuzione o accesso;
- **formato**, cioè struttura interna del file.

L'insieme di questi attributi costituisce il **descrittore del file**.

> 📌 Il descrittore del file raccoglie le informazioni necessarie per sapere com'è fatto il file, dove si trova, chi può usarlo e come è stato usato.

---

### **8. Operazioni sui file**

Il file system rende disponibili diverse operazioni.

Le più tipiche sono:

- **creazione**, per creare un nuovo file;
- **scrittura**, per inserire o modificare dati;
- **lettura**, per recuperare dati;
- **posizionamento**, per scegliere l'elemento da leggere o scrivere;
- **cancellazione**, per rimuovere il file;
- **troncamento**, per eliminare parte del contenuto mantenendo il file;
- **accodamento**, per aggiungere nuovi elementi in coda;
- **modifica degli attributi**;
- **blocco**, per gestire l'uso condiviso o mutuamente esclusivo.

---

### **9. Apertura del file**

L'**apertura** di un file è fondamentale per predisporre l'ambiente di gestione.

Durante l'apertura, il sistema operativo:

1. verifica le autorizzazioni del processo;
2. identifica il descrittore del file nel file system;
3. individua la locazione del file nei dispositivi fisici;
4. predispone le strutture per reperire i blocchi che contengono i dati;
5. verifica eventuali conflitti di condivisione;
6. inizializza la tabella dei file aperti.

#### **9.1. Apertura e condivisione**

Se un file è usato da più processi, il sistema operativo deve controllare se le modalità richieste sono compatibili.

Esempio:

- più processi possono leggere lo stesso file contemporaneamente;
- una scrittura può richiedere mutua esclusione rispetto ad altre letture o scritture.

Se un processo chiede di aprire in scrittura un file già letto da altri processi, può essere sospeso finché gli altri processi non terminano l'uso del file.

> ⚠️ L'apertura non serve solo a trovare il file: serve anche a verificare permessi, inizializzare strutture e gestire conflitti di accesso condiviso.

---

### **10. Puntatore all'elemento corrente**

Quando un file è aperto, il sistema operativo gestisce un **puntatore all'elemento corrente**.

Gli elementi logici del file hanno una posizione:

$$
0, 1, 2, \dots, n-1
$$

All'apertura, il puntatore viene posizionato all'inizio del file.

#### **10.1. Lettura**

L'operazione di lettura legge l'elemento nella posizione indicata dal puntatore corrente.

Dopo la lettura, il puntatore avanza automaticamente alla posizione successiva.

#### **10.2. Scrittura**

L'operazione di scrittura scrive nella posizione indicata dal puntatore corrente.

Anche in questo caso, il puntatore viene poi spostato automaticamente verso la posizione successiva.

> 💡 Il puntatore corrente permette a letture e scritture sequenziali di procedere senza specificare ogni volta la posizione dell'elemento.

---

### **11. Tabella dei file aperti e chiusura**

Le operazioni di lettura e scrittura usano la **tabella dei file aperti**.

Questa tabella conserva:

- riferimento al descrittore;
- posizione dei blocchi nei dispositivi fisici;
- puntatore all'elemento corrente;
- informazioni su lock e condivisione;
- dati temporanei necessari alla gestione efficiente.

#### **11.1. Chiusura**

Al termine dell'uso, la **chiusura** del file:

- aggiorna le informazioni di gestione;
- salva eventuali modifiche agli attributi;
- rilascia lock o uso condiviso;
- consente ad altri processi di proseguire;
- elimina le informazioni temporanee dalla tabella dei file aperti.

---

### **12. Metodi di accesso**

Il metodo di accesso agli elementi di un file dipende dalle caratteristiche del sistema operativo e del dispositivo fisico.

#### **12.1. Accesso sequenziale**

Nell'**accesso sequenziale**, si parte dall'elemento iniziale e si procede ordinatamente fino all'ultimo elemento.

Se si vuole tornare a un elemento precedente, occorre riavvolgere il file all'inizio e ripercorrere la sequenza.

Questa tecnica è tipica dei nastri magnetici.

#### **12.2. Accesso diretto**

Nell'**accesso diretto**, tipico dei dischi, è possibile accedere direttamente al blocco o settore desiderato conoscendone la posizione.

Non è necessario scandire tutto il supporto dall'inizio.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.3. Accesso indicizzato**

Nell'**accesso indicizzato**, tipico di grandi file e basi di dati, viene costruito un indice che rappresenta sinteticamente porzioni del file.

La ricerca avviene prima sull'indice. Una volta individuata la voce rilevante, l'indice fornisce la posizione della porzione del file dati in cui si trova l'informazione cercata.

Questo permette di conservare nell'indice solo una parte compatta dell'informazione, per esempio una chiave come un cognome, lasciando nel file dati il resto delle informazioni associate.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 L'accesso indicizzato accelera la ricerca perché evita di scandire direttamente tutti gli elementi del file dati.

---

### **13. Sintesi finale**

> ✅ Il file system fornisce un'astrazione logica delle informazioni, organizzandole in file e directory indipendentemente dalla loro posizione fisica.

In questa lezione sono stati analizzati:

- concetto di file system;
- file come sequenza di elementi omogenei di lunghezza non nota a priori;
- tipi di file e uso delle estensioni;
- strutture possibili dei file;
- attributi e descrittore;
- operazioni fondamentali;
- apertura, condivisione, puntatore corrente e chiusura;
- metodi di accesso sequenziale, diretto e indicizzato.

Il file è quindi l'unità logica fondamentale con cui il sistema operativo rende persistenti e accessibili dati e programmi.
