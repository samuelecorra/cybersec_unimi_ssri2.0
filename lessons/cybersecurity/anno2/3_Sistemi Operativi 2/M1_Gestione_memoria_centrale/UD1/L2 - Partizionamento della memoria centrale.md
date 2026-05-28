# **M1 UD1 Lezione 2 - Partizionamento della memoria centrale**

---

### **1. Introduzione**

Dopo aver studiato come avviene il collegamento tra indirizzi logici e fisici, questa lezione introduce una delle prime tecniche di gestione della memoria: il **partizionamento**.  
L’obiettivo è consentire l’esecuzione contemporanea di più processi in memoria (**multiprogrammazione**) e impedire che un processo interferisca con le aree riservate ad altri o al sistema operativo.

---

### **2. Allocazione della memoria centrale**

Ogni sistema dispone di uno **spazio di indirizzamento** definito dal processore e di una quantità limitata di **memoria centrale fisica (RAM)**.  
Tale memoria deve essere condivisa tra:

- il **Sistema Operativo**,
- i **processi utente** in esecuzione.

#### **2.1. Spazio di indirizzamento vs memoria fisica installata**

È importante distinguere due grandezze:

- lo **spazio di indirizzamento del processore** = numero di parole fisiche diverse che la CPU può indirizzare (es. $2^{32}$ o $2^{64}$ celle);
- lo **spazio fisico di memoria installato** = dispositivi di memoria effettivamente presenti nel sistema di elaborazione.

Tipicamente il primo è **molto superiore** al secondo: il processore può indirizzare molto più di quanto sia fisicamente disponibile. Ogni processo potrà quindi utilizzare **al più una porzione** dello spazio fisico, **non più grande della memoria installata** e di norma **ancora più piccola**, per consentire ad altri processi di essere caricati contemporaneamente e realizzare il **multitasking**.

#### **2.2. Necessità di caricare i processi in RAM**

I processi possono effettuare operazioni di **lettura e scrittura solo dalla memoria centrale**: il processore non accede direttamente alla memoria di massa per eseguire istruzioni o manipolare dati. Per questo motivo, il sistema operativo deve **caricare in RAM** lo spazio di indirizzamento dei processi che vuole mandare in esecuzione, garantendo loro:

- spazio per il **codice**, i **dati globali**, lo **heap** e lo **stack**;
- la possibilità di **competere per l'uso del processore** ed evolvere nella propria computazione.

Per poter eseguire più programmi contemporaneamente, è quindi necessario **suddividere** la memoria centrale in aree distinte e assegnarne una a ciascun processo.

#### **Problema da risolvere**

Come suddividere la memoria centrale fisica in modo da:

1. Permettere la **co-esistenza di più processi** (multiprogrammazione);
2. Garantire la **protezione** di ogni area di memoria contro accessi non autorizzati.

---

### **3. Obiettivi del partizionamento**

Il partizionamento della memoria mira a due obiettivi fondamentali:

1. **Ripartire la memoria fisica** tra sistema operativo e processi utente,  
    permettendo l’esecuzione parallela di più programmi.
    
2. **Proteggere la memoria** da accessi indebiti:  
    un processo non deve poter leggere o modificare zone appartenenti ad altri processi o al sistema operativo.

In altre parole, il sistema operativo deve fornire **isolamento e sicurezza**, mantenendo però una gestione efficiente dello spazio disponibile.

---

### **4. Partizioni di memoria**

Il concetto di **partizione** indica una **porzione contigua di memoria** assegnata a un singolo processo.  
L'insieme delle partizioni (più la parte riservata al sistema operativo) deve coprire l'intera memoria fisica:

$$  
\text{Somma dello spazio del S.O. + Spazi dei processi} = \text{Memoria centrale fisica}  
$$

#### **4.0. Esempio narrativo**

Supponiamo di avere 4 processi $P$, $Q$, $R$, $S$ e di aver diviso la memoria centrale in 4 partizioni (più lo spazio per il sistema operativo). Se ciascun processo entra nella partizione corrispondente, tutto procede regolarmente.

**Cosa succede però se le partizioni sono tutte occupate?**

- Se arriva un nuovo processo $T$, questo dovrà **attendere** che si liberi una partizione di dimensioni adeguate.
- Se arriva un processo **più grande** della partizione massima disponibile, **non potrà mai essere eseguito** con quel partizionamento — è un limite strutturale della tecnica.

A seconda della modalità con cui vengono create e gestite, distinguiamo due principali tipologie di partizionamento:

#### **a. Partizioni fisse (statiche)**

- La memoria viene divisa in **blocchi di dimensione predefinita**.
- Ogni partizione può contenere **un solo processo alla volta**.
- Le dimensioni e il numero delle partizioni sono stabilite **all'atto del bootstrap** del sistema e **non possono più cambiare** per tutta l'esecuzione, fino allo spegnimento.

##### **Tabella delle partizioni**

Per gestire le partizioni il SO mantiene una **tabella delle partizioni** in cui sono memorizzati gli **indirizzi fisici di inizio** di ciascuna partizione, insieme alla sua dimensione e al suo stato (libera/occupata).

Quando il sistema vuole **caricare un programma**, scandisce la tabella delle partizioni per individuare:

1. quali partizioni sono **libere**;
2. quali di esse hanno **dimensione sufficiente** a contenere il programma desiderato.

Trovata una partizione idonea, il programma viene caricato lì e la partizione viene marcata come occupata.

**Vantaggi:**

- Implementazione semplice.
- Facilità di gestione e controllo.

**Svantaggi:**

- **Frammentazione interna:** se il processo occupa meno spazio della partizione, la parte rimanente rimane inutilizzata.
- **Rigidezza:** il numero massimo di processi in memoria è limitato al numero di partizioni disponibili, fissato a boot.

---

#### **b. Partizioni variabili (dinamiche)**

A differenza di quelle fisse, le partizioni **non sono più bloccate** all'accensione: possono essere **modificate durante l'esecuzione**.

- Le partizioni vengono create dinamicamente in base alla dimensione dei processi da caricare.
- Quando un processo termina, la sua partizione viene liberata e può essere riutilizzata.

##### **Meccanismo di riallocazione**

Anche qui il SO mantiene una **tabella delle partizioni** con gli indirizzi fisici di inizio. Se durante l'esecuzione si rende necessario **modificare** la configurazione delle partizioni — per esempio perché serve creare più spazio per caricare un programma grosso che non entra in nessuna partizione esistente — il sistema può effettuare una **riallocazione**:

1. **spostare** alcuni processi all'interno della memoria centrale per liberare uno spazio contiguo;
2. **ridefinire** le partizioni: ad esempio, **allargare** una partizione, **restringere** o **cancellare** altre per fare spazio;
3. **aggiornare** la tabella delle partizioni con i nuovi indirizzi di inizio, così che i processi possano operare correttamente sulle nuove partizioni.

**Vantaggi:**

- Maggiore flessibilità: ogni processo riceve solo lo spazio necessario.
- Migliore utilizzo complessivo della memoria.

**Svantaggi:**

- **Frammentazione esterna:** nel tempo, gli spazi liberi si distribuiscono in modo disordinato, creando piccole aree inutilizzabili.
- La gestione della memoria risulta più complessa, richiedendo strategie di compattazione o riunione degli spazi liberi.

---

### **5. Frammentazione della memoria**

Il termine **frammentazione** indica la perdita di efficienza nella gestione della memoria a causa della distribuzione irregolare dello spazio libero.

- **Frammentazione interna:** spazio inutilizzato _dentro_ le partizioni (tipica delle partizioni fisse).
- **Frammentazione esterna:** spazio inutilizzato _tra_ le partizioni (tipica delle partizioni variabili).

#### **5.1. Il caso "spiacevole" della frammentazione**

Consideriamo questo scenario tipico delle partizioni fisse:

- la **partizione 1** ospita un programma che non la riempie tutta → resta una **porzione libera** $f_1$;
- la **partizione 2** stessa cosa → porzione libera $f_2$;
- la **partizione 3** stessa cosa → porzione libera $f_3$;
- la **partizione 4** stessa cosa → porzione libera $f_4$.

Sommando le porzioni libere:

$$
f_1 + f_2 + f_3 + f_4 = \text{spazio totale teoricamente sufficiente per un nuovo processo}
$$

**Tuttavia**, questo spazio è **distribuito tra le varie partizioni** ed è quindi **non contiguo**: di fatto, i frammenti sono **persi** e non utilizzabili per caricare un nuovo processo, perché un processo richiede uno **spazio contiguo**.

> Risultato: **teoricamente** lo spazio c'è, **praticamente** non posso caricare nulla. È questo il limite più critico del partizionamento.

Entrambe le tipologie di frammentazione riducono la quantità di memoria realmente disponibile per nuovi processi, motivo per cui le tecniche di partizionamento verranno in seguito sostituite da approcci più evoluti (come il **paging** e la **memoria virtuale**), che eliminano il vincolo di contiguità.

---

### **6. Sintesi finale**

- Il **partizionamento** consente al sistema di ospitare più processi contemporaneamente, **isolandone le aree di memoria** e proteggendole da accessi non autorizzati.
- Esistono due principali approcci:
  - **Partizioni fisse (statiche)** → semplici ma soggette a frammentazione interna e definite all'atto del bootstrap.
  - **Partizioni variabili (dinamiche)** → più flessibili (rialloccabili a runtime) ma soggette a frammentazione esterna.
- ⚠️ **Limite chiave**: il partizionamento **non aumenta lo spazio di memoria a disposizione di ciascun processo**. Ciascun processo può vedere **soltanto** la porzione di spazio fisico che gli viene assegnata — non c'è alcun "ingrandimento virtuale". Il partizionamento migliora **la gestione** e la **sicurezza**, non la **capacità**.
- ⚠️ **Onere amministrativo**: la configurazione delle partizioni (numero, dimensioni) è compito del **gestore del sistema di elaborazione** e **non è sotto il controllo degli utenti** — è una scelta che impatta l'intero sistema.
- Questa tecnica rappresenta la base storica da cui evolveranno le moderne tecniche di **gestione dinamica e virtuale della memoria** (paginazione, segmentazione, memoria virtuale).
