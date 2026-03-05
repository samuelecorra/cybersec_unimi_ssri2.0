# **M5 UD1 Lezione 3 - Organizzazione di file - Strutture hash-based**

### **1. Introduzione**

Dopo aver analizzato le **strutture sequenziali**, si passa ora alle **strutture con accesso calcolato**, dette anche **hash-based**.  
In queste organizzazioni, la **posizione fisica delle tuple** nel file **non dipende dall’ordine di inserimento**, ma è determinata da un **algoritmo di hash** applicato a un **campo chiave**.  
Questo tipo di struttura garantisce **accessi diretti e rapidi** alle tuple, senza dover scorrere l’intero file.

---

### **2. Concetto di accesso calcolato**

#### **2.1 Definizione**

La **struttura hash-based** si basa su un algoritmo che, data una chiave $K$, calcola la **posizione fisica (bucket)** dove memorizzare la tupla corrispondente.  
Il file è diviso in $B$ **bucket**, spesso contigui, e ogni tupla viene collocata nel bucket corrispondente al valore prodotto dalla funzione di hash.

$$  
\text{hash(fileId, Key)} \rightarrow \text{BucketId}  
$$

Dove:

- **fileId** = identificatore del file,
    
- **Key** = valore della chiave della tupla,
    
- **BucketId** = identificativo del bucket risultante.

---

#### **2.2 Funzionamento generale**

L’algoritmo di hash si articola in due fasi:

1. **Folding**  
    Converte il valore della chiave (che può essere alfanumerico o composto) in un **numero intero positivo** distribuito uniformemente.
    
2. **Hashing**  
    Applica una funzione che trasforma tale numero in un valore compreso tra $0$ e $B - 1$, ovvero l’intervallo dei bucket disponibili.

$$  
\text{BucketId} = h(K) = K \bmod B  
$$

Il risultato determina **dove** memorizzare la tupla all’interno del file.

---

### **3. Dimensionamento del file**

La funzione di hash funziona meglio se il file è leggermente **sovradimensionato**, in modo da ridurre il rischio di collisioni.

Siano:

- $T$ = numero di tuple previste,
    
- $F$ = numero medio di tuple per pagina.

Un buon valore di $B$ (numero di bucket) è dato da:

$$  
B = \frac{T}{0.8 \cdot F}  
$$

In questo modo, solo l’**80% dello spazio totale** viene utilizzato, lasciando margine libero per nuove tuple e per la gestione efficiente delle collisioni.

---

### **4. Collisioni**

#### **4.1 Causa**

Poiché il numero di **valori possibili della chiave** è solitamente molto maggiore del numero di bucket, la funzione di hash **non è iniettiva**:  
più chiavi diverse possono essere associate allo stesso bucket.

> In questo caso si verifica una **collisione**, cioè due o più tuple che devono essere memorizzate nello stesso bucket.

---

#### **4.2 Effetti**

Ogni bucket ha capacità massima di $F$ tuple.  
Quando viene superato tale limite, è necessario ricorrere a **strutture di overflow** per mantenere tutti i dati accessibili.

---

### **5. Gestione delle collisioni**

#### **5.1 Catene di overflow**

La soluzione più comune consiste nell’aggiungere una **catena di overflow** per ciascun bucket:

- Quando un bucket è pieno, la nuova tupla viene salvata in un **blocco di overflow**.
    
- I blocchi di overflow vengono concatenati tra loro, formando una **lista collegata**.

Questa tecnica risolve il problema delle collisioni, ma introduce un costo aggiuntivo:  
il DBMS deve **scandire la catena di overflow** per trovare o aggiornare una tupla.

---

#### **5.2 Impatto sulle prestazioni**

La lunghezza media della catena di overflow:

- **aumenta** con l’aumentare del **coefficiente di riempimento** del file;
    
- **diminuisce** se si aumenta la **dimensione dei bucket**.

> In pratica, più si tenta di saturare il file, più crescono le collisioni e i costi di ricerca.

---

### **6. Vantaggi e svantaggi**

#### **6.1 Vantaggi**

- Accesso **associativo** e **molto efficiente** per ricerche dirette sulla chiave.
    
- Tempo medio di ricerca **costante**, indipendente dal numero totale di tuple (in assenza di troppe collisioni).
    
- Ideale per **query puntuali** su chiavi esatte.

---

#### **6.2 Svantaggi**

- Possibili **collisioni**, con conseguente overhead nella gestione dei bucket di overflow.
    
- Le modifiche alla **dimensione del file** (aggiunta di nuovi bucket o ridimensionamento) possono richiedere **ristrutturazioni costose**.
    
- Inefficiente per **ricerche su intervalli** o **ordinamenti**, poiché l’hash non preserva alcun ordine logico.
    
- Ottimizza solo gli accessi basati su **chiave esatta**, non sulle condizioni più complesse.

---

### **7. Sintesi finale**

|**Aspetto**|**Struttura hash-based**|
|---|---|
|**Accesso ai dati**|Diretto tramite funzione di hash|
|**Prestazioni**|Molto efficienti per chiavi esatte|
|**Collisioni**|Possibili; risolte con catene di overflow|
|**Aggiornamenti**|Possibili ristrutturazioni del file|
|**Ricerche per intervallo**|Inefficienti|
|**Uso tipico**|Tabelle con accessi puntuali e chiavi univoche|

---

> **In sintesi:**  
> Le strutture **hash-based** rappresentano una delle tecniche più potenti per l’accesso diretto ai dati.  
> Tuttavia, la loro efficacia dipende fortemente dalla **distribuzione uniforme della funzione di hash** e dal **corretto dimensionamento del file**, per evitare collisioni e garantire tempi costanti di ricerca.

---


![](imgs/Pasted%20image%2020251125051208.png)

