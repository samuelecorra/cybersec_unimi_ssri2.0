# **M1 UD1 Lezione 3 - Overlaying**

### **1. Introduzione**

La tecnica dell’**overlaying** nasce per superare un limite strutturale dei sistemi più datati: la **memoria centrale fisica** disponibile può essere inferiore alla dimensione del **programma logico** da eseguire.  
L’idea è di caricare in RAM solo le **porzioni del programma effettivamente necessarie** in un dato momento, liberando e ricaricando dinamicamente le altre quando servono.

Questa soluzione consente di eseguire programmi più grandi della memoria fisica, pur mantenendo l’esecuzione corretta e continua.

---

### **2. Problema di fondo**

Nei primi sistemi, la **dimensione fisica della memoria centrale assegnata a un processo** era spesso insufficiente a contenere tutto il suo **spazio logico di indirizzamento**.  
Ciò comportava vari vincoli:

- Non tutti i processi potevano essere eseguiti contemporaneamente.
    
- L’eseguibilità dipendeva fortemente dall’hardware e dalla quantità di memoria installata.
    
- I programmi diventavano **poco portabili**, poiché il loro funzionamento era legato alle dimensioni fisiche della memoria.
    

#### **Obiettivo dell’overlaying**

Permettere a un processo di avere **uno spazio logico più grande dello spazio fisico** che gli è stato allocato,  
caricando in memoria **solo la parte del codice e dei dati necessaria nell’immediato futuro**.

---

### **3. Concetto di Overlaying**

#### **Overlaying (1)**

Il principio base è **identificare le porzioni di codice e dati** di un programma che vengono:

- utilizzate **sempre**, e devono quindi rimanere residenti in memoria;
    
- utilizzate **in modo mutuamente esclusivo**, cioè mai contemporaneamente (queste porzioni formano gli _overlay_).
    

In memoria centrale vengono quindi predisposte due aree:

1. **Spazio fisso**, che ospita il codice e i dati sempre necessari;
    
2. **Spazio variabile**, dove vengono caricati di volta in volta gli overlay necessari alla fase corrente del programma.
    

---

#### **Overlaying (2)**

Durante l’esecuzione:

- il sistema carica solo le **porzioni attualmente necessarie** (quelle usate sempre e quelle richieste dal flusso di esecuzione immediato);
    
- gli overlay **non più utili vengono salvati temporaneamente** su memoria secondaria (ad esempio su disco) per liberare spazio;
    
- quando serve un nuovo overlay, esso **rimpiazza** quello precedente nello stesso spazio di memoria.
    

Questa logica consente di **riutilizzare la stessa area fisica di memoria** per caricare porzioni di codice differenti nel tempo.

---

#### **Overlaying (3)**

Quando un programma richiama una funzione appartenente a un overlay non ancora presente in memoria:

1. Il sistema riconosce la necessità di caricare l’overlay corrispondente.
    
2. Vengono **scaricate** le porzioni non più necessarie.
    
3. Viene **caricato** il nuovo overlay al loro posto.
    
4. L’esecuzione prosegue senza interruzioni apparenti per l’utente.
    

---

### **4. Caratteristiche principali**

#### **a. Gestione dello spazio**

- L’overlaying consente di **ripiegare lo spazio logico** di un processo su uno spazio fisico più piccolo.
    
- In pratica, la memoria centrale ospita solo la parte “attiva” del programma in quel momento.
    

#### **b. Ruolo del programmatore**

- È il **programmatore** (o l’ambiente di sviluppo) a dover **identificare le sezioni sovrapponibili** di codice e dati.
    
- Occorre progettare overlay **omogenei**, cioè di dimensioni simili, per ridurre sprechi e frammentazione.
    

#### **c. Supporto del compilatore e delle librerie**

- Il **compilatore** inserisce automaticamente le **chiamate di gestione** necessarie al caricamento e scaricamento degli overlay.
    
- Sono spesso fornite **librerie di gestione dell’overlaying** che semplificano il compito del programmatore.
    

#### **d. Tipologie di overlay**

- **Overlay multipli:** più overlay indipendenti possono coesistere in memoria.
    
- **Overlay gerarchici:** overlay organizzati in livelli (un overlay può contenere al suo interno altri overlay caricabili a richiesta).
    

---

### **5. In sintesi**

- L’**overlaying** è una tecnica che consente a un processo di avere **uno spazio logico superiore** alla memoria fisica disponibile.
    
- Funziona **caricando e rimpiazzando dinamicamente** le sezioni del programma in base alle necessità del flusso di esecuzione.
    
- Deve essere **gestito dal programmatore**, con il supporto del **compilatore** e delle **librerie del linguaggio**.
    
- Ha rappresentato una **tappa fondamentale** nello sviluppo delle moderne tecniche di **memoria virtuale**, che automatizzano completamente il processo di caricamento e sostituzione delle porzioni di codice.