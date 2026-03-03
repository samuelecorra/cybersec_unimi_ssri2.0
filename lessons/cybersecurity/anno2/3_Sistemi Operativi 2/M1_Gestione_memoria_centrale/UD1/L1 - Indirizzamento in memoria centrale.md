# **M1 UD1 Lezione 1 - Indirizzamento in memoria centrale**

### **1. Introduzione**

Il sistema operativo deve gestire in modo efficiente la **memoria centrale**, garantendo che ogni processo possa accedere soltanto alle proprie aree e che il codice venga caricato e collegato correttamente.  
Per farlo, si utilizzano **diversi tipi di indirizzi** e **diverse fasi di collegamento** tra il codice sorgente e la memoria fisica effettiva.

---

### **2. Indirizzi logici e indirizzi fisici**

Un **indirizzo logico** (o _indirizzo virtuale_) è l’indirizzo usato da un programma durante l’esecuzione.  
È un riferimento **astratto**, indipendente dalla posizione reale del programma in memoria.

Un **indirizzo fisico**, invece, è la posizione **reale** di una cella di memoria nella RAM.

Quando un programma viene eseguito, gli indirizzi logici generati dalle istruzioni devono essere **collegati (linked)** agli indirizzi fisici della memoria.  
Questo processo prende il nome di **collegamento degli indirizzi** o **binding**.

Esempio:

```text
miaprocedura(V1, …, Vn);
CALL miaprocedura
```

Durante il processo di compilazione e caricamento, la chiamata `CALL miaprocedura` dovrà essere associata a un indirizzo fisico specifico, come ad esempio `CALL 0011H`.

---

### **3. Il collegamento degli indirizzi (linking e binding)**

Il **binding** può avvenire in tre momenti diversi:

#### **a. Collegamento in fase di compilazione**

Il compilatore associa subito gli indirizzi logici a indirizzi fisici **fissi**.  
Questo metodo presuppone che il programma verrà sempre caricato **nella stessa posizione di memoria**.

- È una modalità **rigida**, detta **caricamento statico in posizione fissa**.
    
- È usata solo nei sistemi molto semplici o nei microcontrollori, dove non esiste multiprogrammazione.

Vantaggio: semplicità.  
Svantaggio: nessuna flessibilità nel caricamento.

---

#### **b. Collegamento in fase di caricamento**

In questo caso, il codice viene compilato con **indirizzi logici**, e solo al momento del caricamento in memoria il sistema operativo **riloca** (cioè adatta) tali indirizzi in base alla posizione effettiva in cui il programma è stato caricato.

- È detto **caricamento statico con rilocazione**.
    
- Permette di caricare il programma in posizioni differenti, ma la rilocazione avviene **una sola volta**, durante il caricamento.

Vantaggio: maggiore flessibilità rispetto al binding in compilazione.  
Svantaggio: il programma non può essere spostato in memoria dopo l’avvio.

---

#### **c. Collegamento in fase di esecuzione**

In questo caso, il collegamento viene effettuato **dinamicamente** mentre il programma è in esecuzione.  
È gestito da un componente hardware chiamato **MMU (Memory Management Unit)**, che traduce automaticamente gli indirizzi logici in indirizzi fisici ogni volta che un’istruzione viene eseguita.

- Si parla di **caricamento statico con rilocazione in esecuzione**.
    
- Permette la **movimentazione dinamica dei processi in memoria**, utile per il _multitasking_ e la _memoria virtuale_.

---

### **4. Tecniche di caricamento**

#### **Caricamento statico**

L’intero programma viene caricato in memoria **prima dell’esecuzione**.  
È tipico dei sistemi più semplici, dove non si ha gestione dinamica della memoria.

#### **Caricamento dinamico**

Solo le parti del programma effettivamente necessarie vengono caricate in memoria **al momento dell’esecuzione**.  
Questa tecnica riduce l’occupazione di memoria e permette di eseguire programmi più grandi della RAM disponibile.

Il caricamento dinamico si integra naturalmente con il **collegamento in esecuzione** e con il supporto della **MMU**.

---

### **5. Collegamento dinamico e librerie condivise**

Nel **collegamento statico**, tutte le procedure e le librerie necessarie vengono incorporate nel programma al momento della compilazione.  
Ciò significa che se più programmi usano la stessa libreria, ciascuno avrà una copia separata in memoria:

```text
CALL miaprocedura1
CALL lib_sistema
CALL miaprocedura2
CALL lib_sistema
```

→ Ogni programma contiene la propria versione di `lib_sistema`.

Nel **collegamento dinamico**, invece, le librerie vengono **caricate solo una volta** e **condivise** tra i programmi che le usano.  
Ogni programma fa riferimento alla stessa area di memoria dove risiede la libreria:

```text
miaprocedura1 → link lib_sistema
miaprocedura2 → link lib_sistema
```

Vantaggi principali:

- **Riduzione dell’uso di memoria**
    
- **Aggiornabilità**: se la libreria cambia, tutti i programmi la useranno nella nuova versione
    
- **Maggiore modularità** e separazione del codice

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