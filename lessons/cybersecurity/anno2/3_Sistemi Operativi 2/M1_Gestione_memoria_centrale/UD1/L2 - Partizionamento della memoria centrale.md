# **M1 UD1 Lezione 2 - Partizionamento della memoria centrale**

### **1. Introduzione**

Dopo aver studiato come avviene il collegamento tra indirizzi logici e fisici, questa lezione introduce una delle prime tecniche di gestione della memoria: il **partizionamento**.  
L’obiettivo è consentire l’esecuzione contemporanea di più processi in memoria (**multiprogrammazione**) e impedire che un processo interferisca con le aree riservate ad altri o al sistema operativo.

---

### **2. Allocazione della memoria centrale**

Ogni sistema dispone di uno **spazio di indirizzamento** definito dal processore e di una quantità limitata di **memoria centrale fisica (RAM)**.  
Tale memoria deve essere condivisa tra:

- il **Sistema Operativo**,
    
- i **processi utente** in esecuzione.

Per poter eseguire più programmi contemporaneamente, è necessario **suddividere** la memoria in aree distinte e assegnarne una parte a ciascun processo.

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
L’insieme delle partizioni (più la parte riservata al sistema operativo) deve coprire l’intera memoria fisica:

$$  
\text{Somma dello spazio del S.O. + Spazi dei processi} = \text{Memoria centrale fisica}  
$$

A seconda della modalità con cui vengono create e gestite, distinguiamo due principali tipologie di partizionamento:

#### **a. Partizioni fisse (statiche)**

- La memoria viene divisa in **blocchi di dimensione predefinita**.
    
- Ogni partizione può contenere **un solo processo alla volta**.
    
- Le dimensioni e il numero delle partizioni sono stabilite **al momento dell’avvio del sistema** e non possono cambiare.

**Vantaggi:**

- Implementazione semplice.
    
- Facilità di gestione e controllo.

**Svantaggi:**

- **Frammentazione interna:** se il processo occupa meno spazio della partizione, la parte rimanente rimane inutilizzata.
    
- **Rigidezza:** il numero massimo di processi in memoria è limitato al numero di partizioni disponibili.

---

#### **b. Partizioni variabili (dinamiche)**

- Le partizioni vengono create **dinamicamente** in base alla dimensione dei processi da caricare.
    
- Quando un processo termina, la sua partizione viene liberata e può essere riutilizzata.

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

Entrambe riducono la quantità di memoria realmente disponibile per nuovi processi, motivo per cui le tecniche di partizionamento verranno in seguito sostituite da approcci più evoluti (come il paging e la memoria virtuale).

---

### **6. Sintesi finale**

- Il **partizionamento** consente al sistema di ospitare più processi contemporaneamente, isolandone le aree di memoria.
    
- Esistono due principali approcci:
    
    - **Partizioni fisse (statiche)** → semplici ma soggette a frammentazione interna.
        
    - **Partizioni variabili (dinamiche)** → più flessibili ma soggette a frammentazione esterna.
    
- Il partizionamento **non aumenta la quantità totale di memoria disponibile**, ma ne migliora la gestione e la sicurezza.
    
- La configurazione delle partizioni è compito del **gestore di sistema** e rappresenta la base storica da cui evolveranno le moderne tecniche di **gestione dinamica e virtuale della memoria**.