# **M1 UD3 Lezione 5 - Ottimizzazione delle prestazioni**

---

### **1. Introduzione**

Questa lezione affronta alcuni aspetti diretti a **ottimizzare le prestazioni della memoria virtuale**.

Le prestazioni dipendono da molti fattori: numero di page fault, dimensione delle pagine, costo della traduzione degli indirizzi, struttura del programma e presenza di pagine che non possono essere scaricate senza conseguenze operative.

Le tecniche trattate sono:

- **prepaginazione**;
- scelta della **dimensione ottimale delle pagine**;
- uso efficiente del **Translation Lookaside Buffer** (**TLB**);
- uso di **tabelle invertite delle pagine**;
- **strutturazione del programma** per migliorare la località;
- mantenimento di **pagine residenti** per dispositivi di I/O e processi in tempo reale.

---

### **2. Prepaginazione**

La **prepaginazione** consiste nel caricare in memoria centrale alcune pagine logiche **in anticipo** rispetto alla loro effettiva necessità.

L'obiettivo è evitare che, poco dopo, il processo generi un page fault per una pagina che si poteva prevedere sarebbe servita.

> 📌 La prepaginazione cerca di trasformare più caricamenti reattivi causati da page fault in un caricamento anticipato e più efficiente.

#### **2.1. Esempio operativo**

Quando si verifica un page fault, invece di caricare soltanto la pagina richiesta, il sistema può caricare anche una o più pagine immediatamente successive.

Questa scelta si basa sull'ipotesi di **località di esecuzione**: se il processo sta accedendo a una certa zona di memoria, è probabile che a breve acceda anche a pagine vicine.

#### **2.2. Effetto sul working set**

Nel modello del working set, l'obiettivo è portare in memoria centrale tutte le pagine del **working set corrente**, così che il processo le trovi già residenti durante la propria esecuzione.

Questo riduce:

- il numero di page fault;
- il tempo di gestione degli interrupt o trap generati dai page fault;
- il costo globale delle operazioni di I/O verso l'area di swap.

> ⚠️ La prepaginazione è utile solo se la previsione è corretta. Se vengono caricate pagine che il processo non userà, si spreca memoria centrale e banda di I/O.

---

### **3. Dimensione ottimale della pagina**

La dimensione della pagina è un parametro critico. Dipende dall'hardware, in particolare dal sistema di generazione degli indirizzi e dalla **Memory Management Unit** (**MMU**).

Una volta definita a livello hardware, il sistema operativo deve adeguarsi: non può modificarla liberamente. Tuttavia, questa scelta influenza fortemente l'efficienza della gestione della memoria virtuale.

> 📌 Il dimensionamento della pagina è un compromesso tra granularità, dimensione delle tabelle, frammentazione interna, costo di I/O e sfruttamento della località.

#### **3.1. Pagine grandi**

Con pagine grandi:

- il numero totale di pagine del sistema diminuisce;
- le tabelle delle pagine sono più piccole;
- la MMU e il sistema operativo gestiscono meno voci;
- le operazioni di caricamento e scaricamento possono essere più efficienti perché trasferiscono blocchi grandi;
- aumenta però la **frammentazione interna**;
- diminuisce la risoluzione con cui il sistema può scegliere quali porzioni caricare o scaricare.

Il rischio è caricare automaticamente molta memoria non usata, soprattutto se la località del processo è piccola rispetto alla dimensione della pagina.

#### **3.2. Pagine piccole**

Con pagine piccole:

- aumenta il numero totale di pagine;
- le tabelle delle pagine diventano più grandi;
- diminuisce la frammentazione interna;
- aumenta la granularità con cui si rappresenta la località;
- si carica meno memoria inutilizzata;
- possono però aumentare le operazioni di I/O necessarie per caricare molte pagine.

#### **3.3. Compromesso**

La scelta ottimale deve bilanciare:

$$
\text{prestazioni} = f(\text{dimensione tabelle}, \text{frammentazione}, \text{I/O}, \text{località})
$$

Pagine grandi semplificano la gestione ma possono sprecare memoria; pagine piccole riducono lo spreco ma aumentano overhead e dimensione delle strutture di traduzione.

---

### **4. Translation Lookaside Buffer**

Il **Translation Lookaside Buffer** (**TLB**) è una memoria associativa che contiene le traduzioni più recentemente effettuate tra pagine logiche e frame fisici.

Quando una traduzione è presente nel TLB, la MMU può evitare di consultare la tabella delle pagine in memoria, riducendo il tempo medio di accesso.

#### **4.1. Copertura del TLB**

La **copertura** del TLB indica quanta memoria virtuale può essere tradotta direttamente tramite le voci presenti nel TLB.

Se:

- $E$ è il numero di voci del TLB;
- $P$ è la dimensione di una pagina;

allora:

$$
\text{copertura TLB} = E \cdot P
$$

Maggiore è la copertura, maggiore è la porzione di spazio utile del processo che può beneficiare di traduzioni rapide.

#### **4.2. Strategie di ottimizzazione**

Per aumentare la copertura del TLB si può:

- aumentare il numero di voci del TLB;
- aumentare la dimensione delle pagine;
- usare pagine di dimensione eterogenea, riducendo alcuni effetti negativi della frammentazione interna.

> 💡 Un TLB con maggiore copertura riduce i miss di traduzione e quindi abbassa il tempo medio di accesso alla memoria.

---

### **5. Tabelle invertite delle pagine**

Per gestire in modo efficiente l'accesso alle tabelle delle pagine, si può usare una **tabella invertita delle pagine**.

Nelle tabelle tradizionali, per ogni pagina logica di un processo si memorizza il frame fisico corrispondente. Nella tabella invertita si fa il contrario: per ogni frame fisico si memorizza quale processo e quale pagina logica si trovano in quel frame.

#### **5.1. Struttura**

Ogni voce della tabella invertita è indicizzata dal numero di pagina fisica e contiene:

$$
\text{TabellaInvertita}[\text{frame fisico}] = (\text{processo}, \text{pagina logica})
$$

#### **5.2. Traduzione**

Per tradurre un indirizzo, il sistema cerca nella tabella invertita la coppia:

$$
(\text{processo corrente}, \text{pagina logica richiesta})
$$

L'indice della voce trovata identifica il frame fisico che contiene la pagina logica del processo corrente.

#### **5.3. Vantaggio**

La tabella invertita riduce la quantità di memoria fisica necessaria per mantenere le strutture di traduzione, soprattutto nei sistemi con spazi virtuali molto grandi.

Se la pagina desiderata non è presente nella tabella, viene generato un page fault e il sistema può usare le tabelle esterne o le informazioni su disco per risolvere l'indirizzamento.

> ⚠️ La tabella invertita riduce lo spazio occupato dalle tabelle, ma richiede una ricerca efficiente della coppia processo-pagina logica, spesso tramite memoria associativa o hashing.

---

### **6. Strutturazione del programma**

Il numero di page fault può essere ridotto anche tramite una **strutturazione intelligente del programma**.

Se un programma ha forte località, il suo working set è più piccolo e risulta più facile mantenerlo interamente in memoria centrale.

#### **6.1. Località e struttura**

Una buona località può essere favorita da:

- organizzazione strutturata del codice;
- uso ordinato di frasi condizionali e cicliche;
- modularizzazione coerente del programma;
- organizzazione attenta delle strutture dati;
- disposizione in memoria di codice e dati usati insieme.

#### **6.2. Ruolo di compilatore e linker**

Compilatore e linker possono sfruttare la struttura del programma per produrre codice con maggiore località, riducendo accessi frequenti a pagine lontane.

> 💡 Un programma ben strutturato non migliora solo la leggibilità: può ridurre il working set e quindi il numero di page fault durante l'esecuzione.

---

### **7. Pagine residenti per dispositivi di I/O**

In alcuni casi è utile o necessario mantenere alcune pagine **residenti** in memoria centrale, cioè non eleggibili per la sostituzione.

Un caso importante riguarda i buffer usati dai dispositivi di **ingresso/uscita**.

Se un buffer contiene dati da inviare a una periferica o dati ricevuti da una periferica, un page fault su quel buffer potrebbe interrompere o ritardare l'operazione di I/O.

#### **7.1. Buffer residenti**

Per evitare il problema, le pagine che contengono buffer di I/O possono essere mantenute residenti in memoria centrale.

Una soluzione consiste nel collocare i buffer nello spazio di indirizzamento del sistema operativo. Quando i dati devono essere trasferiti nelle variabili del processo, il sistema operativo effettua poi il trasferimento verso lo spazio del processo.

> 📌 I buffer di I/O devono spesso rimanere residenti perché le periferiche richiedono disponibilità continua dei dati durante il trasferimento.

---

### **8. Pagine residenti per processi in tempo reale**

Anche i processi in **tempo reale** possono richiedere pagine residenti.

In questi sistemi, il tempo necessario a caricare una pagina mancante può portare al mancato rispetto dei vincoli temporali imposti all'esecuzione del processo.

Per questo motivo può essere utile mantenere in memoria centrale:

- alcune pagine critiche;
- oppure, nei casi più stringenti, tutte le pagine del processo.

In questo modo non sarà necessario effettuare sostituzioni per caricare le pagine critiche durante l'esecuzione, riducendo i tempi di completamento delle operazioni.

> ⚠️ Nei processi real-time, un page fault non è solo un rallentamento: può causare la violazione di una deadline.

---

### **9. Sintesi finale**

> ✅ L'ottimizzazione della memoria virtuale combina tecniche hardware, politiche del sistema operativo e scelte di organizzazione del programma.

Le principali tecniche viste sono:

- **prepaginazione**, per caricare in anticipo le pagine probabilmente necessarie;
- scelta della **dimensione della pagina**, bilanciando tabelle, frammentazione, I/O e località;
- uso efficiente del **TLB**, aumentando la copertura delle traduzioni rapide;
- **tabelle invertite**, per ridurre la memoria necessaria alle strutture di traduzione;
- **strutturazione del programma**, per aumentare la località e ridurre il working set;
- **pagine residenti**, per buffer di I/O e processi in tempo reale.

L'obiettivo complessivo è ridurre page fault, tempi di traduzione, trasferimenti inutili e ritardi non prevedibili.
