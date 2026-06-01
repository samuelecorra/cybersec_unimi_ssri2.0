# **M3 UD1 Lezione 2 - Directory e loro caratteristiche (parte 1)**

---

### **1. Introduzione**

Le **directory** o **direttori** sono strutture logiche usate per organizzare i file in sottoinsiemi, permettendo il loro reperimento secondo criteri logici e non secondo la posizione fisica sul disco.

In questa lezione vengono trattati:

- concetto di directory;
- operazioni lecite sui direttori;
- uso delle directory per strutturare il file system;
- modelli di organizzazione: singolo livello, due livelli, albero, grafo aciclico e grafo generale.

> 📌 La directory serve a raggruppare file secondo criteri logici, rendendo più semplice ricerca, condivisione e protezione.

---

### **2. Concetto di directory**

Una directory è una struttura logica creata per supportare il raggruppamento dei file.

Sostanzialmente, una directory è una collezione omogenea di informazioni su un gruppo di file.

Per ogni file viene conservato il suo **descrittore**, cioè l'insieme delle informazioni che lo caratterizzano:

- nome;
- locazione;
- dimensione;
- tipo;
- attributi;
- protezioni;
- altre informazioni gestite dal sistema operativo.

Il descrittore del file è quindi il tipo base degli elementi contenuti nella directory.

![](imgs/Pasted%20image%2020260601065327.png)

> 📌 Una directory può essere vista come un file il cui tipo base è il descrittore di file.

Il tipo esatto del descrittore dipende dallo specifico sistema operativo.

---

### **3. Operazioni sulle directory**

Le operazioni lecite sui direttori si dividono in due gruppi:

- operazioni relative ai file descritti dalla directory;
- operazioni relative alla struttura delle directory.

#### **3.1. Operazioni sui file tramite directory**

Poiché una directory contiene descrittori di file, permette operazioni come:

- creazione di un file;
- cancellazione di un file;
- ridenominazione;
- modifica degli attributi;
- aggiornamento delle informazioni associate al file.

#### **3.2. Operazioni sulla struttura**

Sulla struttura delle directory si possono effettuare operazioni come:

- ricerca di un file nel file system;
- elencazione dei file presenti in una directory;
- analisi dell'intera struttura del file system;
- creazione di sottodirectory;
- cancellazione di sottodirectory;
- navigazione tra directory.

> 💡 Le directory non servono solo a contenere nomi: sono la struttura che rende possibile cercare e interpretare logicamente l'intero file system.

---

### **4. Strutturazione del file system**

Un file system può essere strutturato in vari modi:

1. directory a singolo livello;
2. directory a due livelli;
3. directory ad albero;
4. directory a grafo aciclico;
5. directory a grafo generale.

Ogni modello offre un diverso compromesso tra semplicità, condivisione, possibilità di raggruppamento e complessità di gestione.

---

### **5. Directory a singolo livello**

La struttura più semplice è il **direttorio a singolo livello**.

In questo modello, su un disco esiste un unico direttorio che contiene tutti i descrittori dei file presenti.

![](imgs/Pasted%20image%2020260601065508.png)

#### **5.1. Caratteristiche**

È una soluzione tipica di vecchi sistemi monoutente con piccola memoria di massa.

Funziona in modo accettabile quando:

- esiste un solo utente;
- il numero di file è limitato;
- non c'è necessità di raggruppare logicamente i file.

#### **5.2. Limiti**

I limiti principali sono:

- forte problema di omonimia in sistemi multiutente;
- impossibilità di avere due file con lo stesso nome;
- assenza di raggruppamenti logici;
- ricerca difficile quando il numero di file cresce;
- scarsa scalabilità.

> ⚠️ Il direttorio a singolo livello è semplice, ma non supporta bene né multiutenza né organizzazione logica dei file.

---

### **6. Directory a due livelli**

La directory a due livelli introduce una prima soluzione per piccoli sistemi multiutente.

Il direttorio di livello più alto contiene riferimenti ai direttori associati ai singoli utenti. Per ogni utente viene creato un sottodirettorio con i file relativi a quell'utente.

![](imgs/Pasted%20image%2020260601065704.png)

#### **6.1. Vantaggi**

Questa soluzione:

- riduce i problemi di omonimia tra utenti diversi;
- rende più efficiente la ricerca, perché ogni utente vede il proprio insieme di file;
- separa almeno parzialmente gli spazi degli utenti.

#### **6.2. Limiti**

Restano però alcuni limiti:

- lo stesso utente non può organizzare logicamente i propri file in sottoinsiemi;
- la condivisione diretta tra utenti è difficile;
- per condividere un file può essere necessario replicarlo;
- la struttura è ancora troppo rigida per sistemi moderni.

---

### **7. Directory ad albero**

Nei sistemi operativi moderni si vuole una visione logica del file system indipendente dal singolo utente.

![](imgs/Pasted%20image%2020260601070720.png)

I file vengono raggruppati secondo criteri logici: progetto, funzione, tipo di contenuto, applicazione, utente o qualsiasi altra classificazione utile.

![](imgs/Pasted%20image%2020260601070731.png)

La struttura naturale per rappresentare queste suddivisioni gerarchiche è un **albero**.

![](imgs/Pasted%20image%2020260601070742.png)

#### **7.1. Radice e sottodirectory**

La struttura ad albero ha:

- un direttorio radice, che rappresenta l'intero file system;
- sottodirectory che rappresentano sottoinsiemi logici;
- file contenuti nelle foglie o nelle directory interne;
- ulteriori livelli di suddivisione gerarchica.

Ogni directory può contenere file e altre directory.

#### **7.2. Vantaggi**

La struttura ad albero:

- supporta sistemi multiutente moderni;
- consente raggruppamenti logici;
- rende la ricerca più efficiente;
- permette una gestione più naturale dei dati;
- supporta percorsi assoluti e relativi;
- favorisce una visione unitaria del file system.

---

### **8. Percorsi e directory corrente**

In una struttura ad albero, un file può essere identificato tramite un **percorso**.

#### **8.1. Percorso assoluto**

Il percorso assoluto parte dalla radice del file system e specifica l'intero cammino fino al file.

#### **8.2. Percorso relativo**

Il percorso relativo parte dal **direttorio corrente** o **direttorio di lavoro**.

Il direttorio corrente permette di evitare di specificare ogni volta il cammino completo dalla radice.

> 📌 Il direttorio corrente rende rilocabile il sottoalbero su cui si lavora: se il sottoalbero viene spostato, i percorsi relativi interni possono continuare a funzionare.

Questa proprietà consente di creare strutture analoghe in directory diverse e di far operare gli stessi programmi su istanze diverse dei dati.

---

### **9. Directory a grafo aciclico**

La struttura ad albero può essere generalizzata introducendo un **grafo aciclico**.

In un grafo aciclico è possibile condividere file o directory, permettendo che lo stesso elemento sia raggiungibile tramite percorsi diversi.

![](imgs/Pasted%20image%2020260601070928.png)

#### **9.1. Condivisione**

Un file può appartenere contemporaneamente a due sottoinsiemi logici, senza essere duplicato fisicamente.

Analogamente, una directory può essere condivisa da più punti della struttura.

#### **9.2. Vincolo di aciclicità**

Il grafo non deve contenere cicli.

Questo evita che algoritmi di ricerca, visita o cancellazione restino intrappolati in percorsi infiniti.

> ✅ Il grafo aciclico permette condivisione senza duplicazione, mantenendo però controllabile la navigazione della struttura.

---

### **10. Directory a grafo generale**

La generalizzazione ulteriore è la directory a **grafo generale**, in cui sono ammessi anche cicli.

![](imgs/Pasted%20image%2020260601071019.png)

Questo modello è molto flessibile, ma introduce problemi gestionali significativi.

#### **10.1. Problemi**

La presenza di cicli può complicare:

- ricerca dei file;
- cancellazione;
- visita dell'intera struttura;
- conteggio dei riferimenti;
- rilevamento di elementi ancora raggiungibili.

Se non gestiti con attenzione, gli algoritmi possono entrare in cicli infiniti.

> ⚠️ I grafi generali offrono massima flessibilità, ma richiedono meccanismi espliciti per evitare loop e cancellazioni scorrette.

---

### **11. Sintesi finale**

> ✅ Le directory sono file speciali composti da descrittori di file e servono a organizzare logicamente il file system.

In questa lezione sono stati analizzati:

- directory come collezione di descrittori;
- operazioni sui file e sulla struttura dei direttori;
- direttorio a singolo livello;
- direttorio a due livelli;
- direttorio ad albero;
- percorsi assoluti, relativi e direttorio corrente;
- grafo aciclico per la condivisione;
- grafo generale con cicli e relative complessità.

La struttura ad albero è la soluzione naturale per rappresentare il raggruppamento logico dei file nei sistemi moderni, mentre i grafi introducono condivisione e maggiore flessibilità al prezzo di una gestione più complessa.
