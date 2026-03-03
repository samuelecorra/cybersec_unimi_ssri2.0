# **M1 UD3 Lezione 1 - Concetti e tecniche fondamentali della memoria virtuale**

### **1. Introduzione**

La **memoria virtuale** rappresenta una delle evoluzioni più importanti nella gestione della memoria centrale.
Il suo scopo è **superare i limiti fisici della RAM**, consentendo a ciascun processo di percepire uno **spazio di memoria molto più grande e continuo** di quello realmente disponibile.

Attraverso opportuni meccanismi di **virtualizzazione e mappaggio**, il sistema operativo garantisce a ogni processo l’illusione di avere a disposizione l’intero spazio di indirizzamento del processore, isolato dagli altri processi.

---

### **2. Problemi di fondo**

Le tecniche di memoria virtuale nascono per risolvere alcune limitazioni strutturali dei sistemi multiprogrammati:

1. **Dimensioni ridotte della memoria fisica** rispetto allo spazio di indirizzamento richiesto dai programmi moderni.
    
2. **Multiprogrammazione**: più processi devono convivere in memoria condividendo le stesse risorse.
    
3. **Protezione**: ogni processo deve essere isolato dagli altri, impedendo accessi non autorizzati.
    
4. **Gestione efficiente**: l’assegnazione e il rilascio della memoria devono essere dinamici e automatizzati.
    
5. **Supporto al time-sharing**: il sistema deve permettere l’alternanza rapida dei processi in esecuzione, garantendo continuità e reattività.

---

### **3. Obiettivo della memoria virtuale**

L’obiettivo è **creare una memoria centrale virtuale**, cioè un’astrazione della memoria fisica reale.  
Questa astrazione deve essere:

- **più grande** della memoria fisica realmente installata;
    
- **indipendente** dall’hardware sottostante;
    
- **gestita in modo efficiente** dal sistema operativo;
    
- **completamente trasparente** per i processi.

#### **Virtualizzazione**

Ogni processo:

- può utilizzare **tutto lo spazio di indirizzamento** previsto dal processore;
    
- **ignora la presenza degli altri processi**, credendo di disporre di una memoria privata e continua.

---

### **4. Concetto di memoria virtuale**

La memoria virtuale è un **insieme di tecniche e politiche** che permettono di creare per ogni processo una visione dedicata e sicura dello spazio di memoria.

Caratteristiche principali:

- dimensione pari allo **spazio di indirizzamento del processore**;
    
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
    
4. Solo le porzioni **necessarie nel prossimo futuro** vengono caricate in memoria centrale (per processi _ready_ o _running_).
    
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

---

### **6. Tecniche per la realizzazione della memoria virtuale**

La memoria virtuale può essere implementata utilizzando differenti strategie:

- **Paginazione**,
    
- **Segmentazione**,
    
- **Segmentazione con paginazione**,
    
- **Swapping**,
    
- **Rilevamento di mancanze di frame** (_page fault detection_),
    
- **Politiche di sostituzione delle pagine**,
    
- **Politiche di allocazione dei frame**,
    
- **Gestione del thrashing** (situazioni di sovraccarico della memoria virtuale).

Ciascuna di queste tecniche mira a bilanciare **prestazioni, efficienza e isolamento**, evitando che le limitazioni fisiche della RAM compromettano l’esecuzione dei processi.

---

### **7. Sintesi finale**

- La **memoria virtuale** è un’astrazione che permette a ogni processo di percepire un proprio spazio di memoria indipendente e continuo.
    
- Consente di **superare i limiti fisici** della memoria centrale, garantendo protezione e condivisione controllata.
    
- Viene realizzata tramite tecniche di **paginazione, segmentazione o combinazioni** delle due, gestite dal sistema operativo e supportate dalla **MMU**.
    
- È la base della **multiprogrammazione moderna**, permettendo l’esecuzione efficiente di molti processi contemporanei anche su sistemi con memoria limitata.