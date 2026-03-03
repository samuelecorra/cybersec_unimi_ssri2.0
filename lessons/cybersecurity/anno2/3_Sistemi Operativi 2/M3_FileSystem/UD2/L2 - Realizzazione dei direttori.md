# **M3 UD2 Lezione 2 - Realizzazione dei direttori**

### **1. Introduzione**

La **realizzazione dei direttori** è un aspetto fondamentale dell’implementazione del file system, poiché determina **efficienza, scalabilità e rapidità di accesso** ai file.  
Le directory contengono le **informazioni descrittive dei file** e i **puntatori ai blocchi di dati** associati.

Le principali **tecniche di implementazione** utilizzate sono:

- la **struttura a lista**,
    
- e la **struttura a tabella di hash**.

---

### **2. Struttura a lista**

#### **2.1 Descrizione generale**

La **struttura a lista** rappresenta la forma più semplice di implementazione di un direttorio.  
Ogni directory è composta da una **lista sequenziale di record**, in cui ciascun record contiene:

- il **nome del file**,
    
- e un **riferimento ai blocchi di dati** o al descrittore del file.

#### **2.2 Vantaggi**

- **Semplicità di realizzazione:** struttura diretta, facile da implementare.
    
- **Buona efficienza** per la **visualizzazione ordinata dell’elenco dei file**.

#### **2.3 Svantaggi**

- **Accesso costoso:** per trovare un file è necessaria una **scansione lineare** dell’intera lista.
    
- Le prestazioni peggiorano con l’aumentare del numero di file nella directory.

#### **2.4 Possibili ottimizzazioni**

Per migliorare l’efficienza della struttura a lista si possono introdurre:

- **Cache** → mantiene in memoria gli elementi più utilizzati.
    
- **Liste ordinate** → consente una ricerca più rapida (ad esempio tramite ricerca binaria).
    
- **Alberi bilanciati (B-tree)** → permettono accesso logaritmico ai file, migliorando la scalabilità.

---

### **3. Struttura a tabella di hash**

#### **3.1 Descrizione generale**

La **struttura a tabella di hash** utilizza una **funzione di hashing** per individuare rapidamente l’elemento cercato all’interno della directory.

Il meccanismo prevede:

- una **lista** che memorizza i record dei file,
    
- e una **tabella di hash** che associa ogni nome di file a una **posizione logica** nella lista.

#### **3.2 Scelta della funzione di hash**

La funzione di hashing deve:

- **ridurre le collisioni**,
    
- e **distribuire uniformemente i nomi dei file** tra le posizioni della tabella.

Esempio di funzione di hash semplificata:  
$$  
h(\text{nome}) = \text{(somma dei codici ASCII dei caratteri del nome)} \mod N  
$$  
dove $( N )$ è la dimensione della tabella.

#### **3.3 Vantaggi**

- **Accesso diretto e veloce** ai file.
    
- **Ottima efficienza media** anche con directory di grandi dimensioni.

#### **3.4 Svantaggi**

- **Collisioni:** due file diversi possono generare lo stesso valore di hash.  
    → richiedono tecniche di gestione come _chaining_ (liste concatenate) o _open addressing_.
    
- Maggiore **complessità di implementazione** rispetto alla struttura a lista.

---

### **4. Confronto tra le due strutture**

|Caratteristica|**Struttura a lista**|**Struttura a tabella di hash**|
|---|---|---|
|**Semplicità**|Molto semplice|Più complessa|
|**Prestazioni di ricerca**|Lente (scansione lineare)|Veloci (accesso diretto)|
|**Ordinamento**|Possibile ma richiede costo aggiuntivo|Non ordinata|
|**Gestione delle collisioni**|Non necessaria|Necessaria|
|**Memoria richiesta**|Minima|Maggiore (tabella + lista)|
|**Scalabilità**|Limitata|Elevata|

---

### **5. Sintesi finale**

|Tecnica|Descrizione sintetica|Vantaggi|Limiti|
|---|---|---|---|
|**Lista**|Elenco sequenziale di file e riferimenti ai blocchi|Semplice, adatta a directory piccole|Accesso lineare, lenta con molti file|
|**Hash**|Associazione file–posizione tramite funzione di hash|Accesso rapido, buona scalabilità|Collisioni, maggiore complessità|

---

### **6. Conclusione**

Le tecniche di realizzazione dei direttori influenzano direttamente le **prestazioni globali del file system**.

- Le **liste** offrono **semplicità e linearità**, ideali per directory di dimensioni ridotte.
    
- Le **tabelle di hash** garantiscono **accesso rapido e scalabilità**, ma richiedono **una gestione più sofisticata delle collisioni e della memoria**.

In pratica, molti file system moderni adottano **soluzioni ibride**, combinando hash e alberi bilanciati per ottenere **efficienza e affidabilità ottimali**.