# **M1 UD1 Lezione 1 - Indirizzamento in memoria centrale**

---

### **1. Introduzione**

Il sistema operativo deve gestire in modo efficiente la **memoria centrale**, garantendo che ogni processo possa accedere soltanto alle proprie aree e che il codice venga caricato e collegato correttamente.  
Per farlo, si utilizzano **diversi tipi di indirizzi** e **diverse fasi di collegamento** tra il codice sorgente e la memoria fisica effettiva.

---

### **2. Indirizzi logici e indirizzi fisici**

#### **2.1. Lo spazio di indirizzamento del programma**

Quando scriviamo un programma e generiamo il codice eseguibile, il programma $P$ ha un **proprio spazio di indirizzamento** in cui pone:

- **codice** (le istruzioni eseguibili),
- **dati globali**,
- **heap** (memoria allocata dinamicamente),
- **stack** (contesti delle procedure attive e variabili locali).

Un **indirizzo logico** (o _indirizzo virtuale_) è l'indirizzo di una cella di memoria **all'interno di questo spazio**, calcolato come spiazzamento **a partire dall'indirizzo zero di base** del programma (il primo byte occupato dal programma nel suo spazio di indirizzamento).

> Esempio: la cella di memoria all'**indirizzo logico `100h`** sta a un offset di `100h` byte dall'inizio del programma — **non** dall'inizio della RAM fisica.

#### **2.2. L'indirizzo fisico in memoria centrale**

Nella **memoria centrale** sono caricati i vari programmi attivi, ciascuno con il proprio spazio di indirizzamento riservato. La posizione fisica a cui il programma è caricato (il primo byte occupato in RAM) prende il nome di **indirizzo base**.

Un **indirizzo fisico** è quindi lo **spiazzamento in byte misurato a partire dall'indirizzo `0h`** di inizio della memoria centrale fisica.

##### **Formula di traduzione**

$$
\text{indirizzo fisico} = \text{indirizzo base} + \text{indirizzo logico}
$$

##### **Esempio numerico**

Supponiamo che il programma $P$ sia caricato in memoria centrale a partire dall'**indirizzo base `500h`**. La cella che nel programma è all'**indirizzo logico `100h`** si troverà fisicamente all'**indirizzo `600h`**:

$$
\underbrace{600h}_{\text{indirizzo fisico}} = \underbrace{500h}_{\text{base}} + \underbrace{100h}_{\text{logico}}
$$

![](imgs/Pasted%20image%2020260529214033.png)

#### **2.3. Riferimenti simbolici nella scrittura del programma**

Durante la scrittura del codice — sia in **linguaggio ad alto livello** sia in **assembly simbolico** — il programmatore **non utilizza mai riferimenti numerici assoluti fisici** alle celle di memoria. Si usano sempre **nomi simbolici** (es. il nome di una procedura, di una variabile), che saranno poi tradotti dal compilatore e dal linker in indirizzi numerici.

Esempio:

```text
miaprocedura(V1, …, Vn);
CALL miaprocedura
```

Durante il processo di compilazione e caricamento, la chiamata `CALL miaprocedura` dovrà essere associata a un indirizzo fisico specifico, come ad esempio `CALL 0011H`.

Questo processo di associazione prende il nome di **collegamento degli indirizzi** o **binding**.

##### **Ruoli di linker e loader**

- Il **compilatore** e il **linker** effettuano il _linking_ del nome simbolico a un **indirizzo logico** nello spazio del programma: mettono insieme i codici oggetto e definiscono lo spazio di indirizzamento logico, attribuendo a ogni variabile / istruzione / procedura il proprio indirizzo logico.
- Il **loader** (caricatore) provvede successivamente a effettuare il **binding definitivo**: associa l'indirizzo logico al **valore fisico** in memoria centrale.

---

### **3. Il collegamento degli indirizzi (linking e binding)**

#### **3.1. Flusso di produzione del codice eseguibile**

Per capire **dove** può avvenire il binding, è utile prima richiamare il flusso completo di produzione e attivazione di un programma:

$$
\text{Sorgente} \;\longrightarrow\; \text{Compilatore/Assemblatore} \;\longrightarrow\; \text{Oggetti} \;\longrightarrow\; \text{Linker} \;\longrightarrow\; \text{Eseguibile} \;\longrightarrow\; \text{Loader} \;\longrightarrow\; \text{RAM} \;\longrightarrow\; \text{Esecuzione}
$$

![](imgs/Pasted%20image%2020260529214250.png)

In dettaglio:

1. **Compilatore / assemblatore**: a partire dal codice sorgente, produce un **modulo oggetto** per ciascun file compilato.
2. **Linker**: mette insieme i vari oggetti e ne risolve i riferimenti incrociati, producendo l'**eseguibile**. Eventualmente può collegare anche la **libreria di sistema** direttamente nell'eseguibile.
3. **Caricamento delle librerie**: la libreria di sistema può essere:
   - linkata immediatamente nell'eseguibile (collegamento statico),
   - oppure caricata successivamente al loading, **eventualmente in fase di esecuzione** (collegamento dinamico).
4. **Loader**: carica l'eseguibile in memoria centrale, lo rende attivo e idoneo a competere per la CPU; può a sua volta caricare la libreria di sistema richiesta.
5. **Esecuzione**: durante questa fase è ancora possibile caricare **ulteriori componenti** richieste — tipicamente librerie a caricamento dinamico.

#### **3.2. I tre momenti del binding**

Il binding può quindi avvenire in **tre momenti** diversi del ciclo di vita del programma:

- in **fase di compilazione**,
- in **fase di caricamento**,
- durante l'**esecuzione** stessa del programma.

Più si "ritarda" il binding lungo il flusso, più si guadagna in **rilocabilità** e flessibilità del codice (a fronte di un costo gestionale maggiore).

---

#### **a. Collegamento in fase di compilazione**

Si fornisce al compilatore un **indirizzo di base** a cui si vuole caricare il programma in memoria centrale. Il compilatore rende i riferimenti **assoluti fisici** direttamente nel codice eseguibile: a ogni spiazzamento logico viene **sommato l'indirizzo di base** in modo da produrre fin da subito l'indirizzo fisico finale.

$$
\text{indirizzo fisico (nell'eseguibile)} = \text{indirizzo base (fissato in compilazione)} + \text{spiazzamento logico}
$$

Questo valore è **congelato nel codice eseguibile**: tutte le volte che il programma verrà caricato, sarà caricato **sempre allo stesso indirizzo di base**.

- È una modalità **rigida**, detta **caricamento statico in posizione fissa**.
- È usata solo nei sistemi molto semplici o nei microcontrollori, dove non esiste multiprogrammazione.

Vantaggio: semplicità.  
Svantaggio: nessuna flessibilità nel caricamento.

---

#### **b. Collegamento in fase di caricamento**

In questo caso il codice viene compilato con **indirizzi logici** (non ancora associati a una posizione fisica). L'assegnazione degli indirizzi fisici avviene in modo **completo** e **fisso** all'atto del caricamento, **non più** in fase di compilazione.

##### **Rilocabilità in fase di caricamento**

Il vantaggio è la **rilocabilità**: a seconda della posizione in cui si decide di caricare il programma, il loader ridefinisce l'indirizzo di base. Esempio:

- se si sceglie come base la posizione $A$, una certa cella avrà indirizzo fisico $A + \text{logico}$;
- se invece si sceglie la base $B \neq A$, **la stessa cella** avrà indirizzo fisico $B + \text{logico}$;
- il programma può quindi essere caricato in **posizioni diverse** della memoria centrale a seconda della disponibilità.

##### **Limite: staticità dopo il caricamento**

Una volta effettuato il caricamento, gli indirizzi sono **statici e bloccati** fintanto che non si **ricarica** completamente il programma (rifacendo il binding da zero in una nuova posizione).

- È detto **caricamento statico con rilocazione**.
- Permette di caricare il programma in posizioni differenti, ma la rilocazione avviene **una sola volta**, durante il caricamento.

Vantaggio: maggiore flessibilità rispetto al binding in compilazione.  
Svantaggio: il programma non può essere spostato in memoria dopo l'avvio.

---

#### **c. Collegamento in fase di esecuzione**

In questo caso il collegamento viene effettuato **dinamicamente** durante l'esecuzione. È gestito da un componente hardware chiamato **MMU (Memory Management Unit)**.

##### **Meccanismo della MMU**

Quando il programma viene caricato in memoria, il suo **indirizzo di base** viene memorizzato in un **registro apposito** della MMU. Da quel momento, **ogni volta che il programma genera un indirizzo logico**, la MMU lo intercetta e lo traduce **al volo** sommandogli il valore della base contenuta nel registro:

$$
\text{indirizzo fisico} = \text{registro\_base (MMU)} + \text{indirizzo logico}
$$

##### **Rilocabilità banale durante l'esecuzione**

Il grande vantaggio: per **rilocare** il programma in memoria centrale **mentre è in esecuzione**, è sufficiente:

1. spostare fisicamente lo spazio del programma in una nuova posizione,
2. **aggiornare il contenuto del registro di base** nella MMU al nuovo valore.

**Non bisogna toccare nulla** nel codice eseguibile: la MMU rigenererà automaticamente tutti gli indirizzi fisici al volo, in modo del tutto trasparente al programma.

- Si parla di **caricamento statico con rilocazione in esecuzione**.
- Permette la **movimentazione dinamica dei processi in memoria**, utile per il _multitasking_ e la _memoria virtuale_.

---

### **4. Tecniche di caricamento**

Indipendentemente da **quando** avviene il binding (compilazione, caricamento, esecuzione), c'è una scelta ortogonale: **quanto** del programma caricare in memoria centrale. L'obiettivo è **minimizzare l'uso della RAM**, perché non sempre serve avere tutto il programma in memoria.

#### **4.1. Caricamento statico**

L'**intero programma** viene caricato in memoria **prima dell'esecuzione** e **rimane** in RAM fintanto che l'esecuzione non è completata.

È tipico dei sistemi più semplici, dove non si ha gestione dinamica della memoria.

#### **4.2. Caricamento dinamico**

Solo le **porzioni di codice effettivamente necessarie** vengono caricate in memoria, **quando servono**. Questo riduce l'occupazione di memoria centrale e permette di eseguire programmi più grandi della RAM disponibile.

##### **Meccanismo operativo**

Si supponga di avere in memoria centrale solo una **porzione iniziale** del programma, attualmente in esecuzione. Nel momento in cui questa porzione esegue un'istruzione che fa riferimento a una **procedura non presente** in memoria (es. una procedura definita dall'utente in un modulo non ancora caricato), accade quanto segue:

1. il **supporto runtime** del linguaggio di programmazione si accorge che la procedura **non è presente** in memoria centrale;
2. provvede a **caricare** in RAM la porzione di codice che realizza tale procedura;
3. **trasferisce il controllo** alla **prima istruzione** della procedura appena caricata.

Il tutto avviene in modo **trasparente** per il codice applicativo: il programmatore scrive una normale chiamata di procedura e il runtime si occupa del caricamento on-demand.

Il caricamento dinamico si integra naturalmente con il **collegamento in esecuzione** e con il supporto della **MMU**.

---

### **5. Collegamento dinamico e librerie condivise**

#### **5.1. Collegamento statico: duplicazione della libreria**

Nel **collegamento statico**, tutte le procedure e le librerie necessarie vengono **incorporate nel programma** al momento della compilazione/linking.  
Ciò significa che se **due programmi diversi** usano la stessa libreria di sistema, ciascuno avrà la **propria copia completa** caricata in memoria centrale:

```text
Programma 1: codice procedure + COPIA INTERA di lib_sistema
Programma 2: codice procedure + COPIA INTERA di lib_sistema
```

→ Doppio caricamento dello **stesso** codice della libreria, con un **evidente spreco** di memoria centrale.

#### **5.2. Collegamento dinamico: una sola copia condivisa**

Con il **collegamento dinamico**, ogni programma contiene soltanto delle **parti minime di libreria** — degli **stub** che mantengono i riferimenti al **corpo vero e proprio** della libreria, caricato **una sola volta** in una regione di memoria condivisa.

```text
Programma 1: codice procedure + stub → ┐
                                       ├──→ lib_sistema (un'unica copia in RAM)
Programma 2: codice procedure + stub → ┘
```

Quando un programma invoca una procedura della libreria, lo **stub** intercetta la chiamata e la **redirige al codice reale** della libreria, condiviso con tutti gli altri programmi che la usano.

#### **5.3. Vantaggi principali**

- **Riduzione dell'uso di memoria**: una sola copia della libreria condivisa anziché $N$ copie indipendenti.
- **Aggiornabilità**: se la libreria cambia, tutti i programmi useranno automaticamente la **nuova versione** alla successiva esecuzione, senza ricompilare.
- **Maggiore modularità** e separazione del codice.

---

### **6. In sintesi**

- Gli **indirizzi logici** sono usati dai programmi, mentre gli **indirizzi fisici** corrispondono alle celle reali in memoria.
    
- Il **collegamento degli indirizzi (binding)** può avvenire:
    
    - in **compilazione** → indirizzi fissi
        
    - in **caricamento** → rilocazione statica
        
    - in **esecuzione** → rilocazione dinamica tramite MMU
    
- Esistono due tipi principali di caricamento:
    
    - **statico** → tutto il programma caricato subito
        
    - **dinamico** → solo le parti necessarie caricate all’occorrenza
    
- Il **collegamento dinamico** con **librerie condivise** permette di risparmiare memoria e mantenere i programmi modulari.