# **M5 UD1 Lezione 4 - Organizzazione di file - Gestione delle tuple nelle pagine**

### **1. Introduzione**

Dopo aver studiato le principali **strutture di organizzazione dei file** (sequenziali e hash-based), questa lezione analizza **come le tuple vengono effettivamente memorizzate all’interno delle pagine**.  
Ogni pagina, che coincide con un **blocco fisico** del file system, contiene **dati e metadati di controllo**.  
La gestione interna delle pagine è cruciale per garantire **integrità, efficienza e accesso rapido** alle informazioni.

---

### **2. Struttura generale di una pagina**

Ogni **pagina** - che può appartenere sia a una struttura sequenziale che a una struttura hash-based - è suddivisa in tre zone principali:

1. **Intestazioni e trailer (header/trailer)**
    
    - A livello di **file system**, si trovano il _block header_ e il _block trailer_, che contengono informazioni di controllo come identificatori e checksum.
        
    - A livello di **DBMS**, si trovano il _page header_ e il _page trailer_, che includono i dati di controllo del metodo di accesso (sequenziale, hash, ad albero, ecc.).
    
2. **Dizionario di pagina (page directory)**
    
    - Contiene i **puntatori** alle tuple (record) presenti nella pagina.
        
    - Permette l’accesso indiretto e ordinato alle tuple senza dover spostare fisicamente i dati quando si effettuano inserimenti o cancellazioni.
    
3. **Area dati (page data area)**
    
    - Contiene le **tuple effettive**, memorizzate come blocchi di byte consecutivi.
        
    - Generalmente cresce **dal basso verso l’alto**, mentre il dizionario cresce **dall’alto verso il basso**, formando due “stack contrapposti”.

---

#### **2.1 Schema grafico**

$$  
\begin{array}{|c|}  
\hline  
\text{Block header / trailer (file system)} \\  
\hline  
\text{Page header / trailer (metodo di accesso)} \\  
\hline  
\text{Dizionario di pagina (puntatori alle tuple)} \\  
\hline  
\text{Area dati (tuple t1, t2, t3, …)} \\  
\hline  
\text{Bit di parità per controllo integrità} \\  
\hline  
\end{array}  
$$

> Il **bit di parità** viene utilizzato per verificare che le informazioni contenute nella pagina non siano state corrotte durante l’I/O.

---

### **3. Dizionario di pagina**

Il dizionario di pagina (page directory) è una piccola struttura dati interna che mantiene, per ogni tupla, un **puntatore al suo offset** nell’area dati.  
Grazie a questo meccanismo, le tuple possono essere **spostate o eliminate** senza dover riscrivere l’intera pagina, poiché il dizionario viene semplicemente aggiornato.

- Vantaggio: consente **accessi efficienti** anche dopo più operazioni di inserimento o cancellazione.
    
- Svantaggio: richiede una **gestione accurata dello spazio** per evitare frammentazioni.

---

### **4. Gestione dello spazio**

Poiché il dizionario e la parte utile crescono in direzioni opposte, il DBMS può controllare **in tempo reale** quanto spazio libero rimane nella pagina.  
Quando i due “stack” si incontrano, la pagina è piena e non è più possibile aggiungere nuove tuple.

---

### **5. Operazioni sulle tuple**

Le principali **primitive di gestione delle tuple** in una pagina sono:

---

#### **5.1 Inserimento e aggiornamento**

- L’inserimento di una nuova tupla comporta:
    
    1. l’allocazione dello spazio necessario nell’area dati;
        
    2. l’aggiornamento del dizionario con un nuovo puntatore.
    
- L’**aggiornamento** di una tupla può richiedere una **riorganizzazione della pagina** se non esiste abbastanza spazio contiguo per i nuovi byte introdotti.

> In tal caso, il DBMS può spostare le tuple e aggiornare i puntatori nel dizionario.

---

#### **5.2 Cancellazione**

- Una cancellazione **non elimina fisicamente** la tupla, ma la **marca come invalida**.
    
- Ciò evita di riscrivere l’intera pagina e consente un **recupero rapido dello spazio** in un secondo momento.

---

#### **5.3 Accesso alle tuple**

L’accesso a una tupla può avvenire in due modi:

1. **Per chiave (accesso associativo)**  
    Il DBMS usa l’indice o la chiave per determinare la pagina e la posizione della tupla.
    
2. **Per offset (accesso diretto)**  
    Il DBMS utilizza l’offset memorizzato nel dizionario per localizzare direttamente la tupla nella pagina.

---

#### **5.4 Accesso a un campo specifico**

Per accedere a un singolo **campo** di una tupla:

1. si identifica prima la tupla tramite il suo offset;
    
2. poi si calcola la posizione del campo, nota la **lunghezza dei campi precedenti**.

---

### **6. Strutture ad albero**

Le strutture ad albero (come le **B-tree** o le **B+ tree**) seguono una **gestione delle pagine diversa**.  
Ogni pagina rappresenta un **nodo dell’albero**, e i puntatori alle tuple o ai nodi figli sono gestiti con una logica gerarchica piuttosto che sequenziale o hash-based.

---

### **7. Sintesi finale**

**Abbiamo visto:**

- come le tuple sono memorizzate all’interno delle pagine;
    
- la distinzione tra **informazioni di controllo** e **dati effettivi**;
    
- la funzione del **dizionario di pagina** e del **bit di parità**;
    
- le operazioni principali su tuple: inserimento, aggiornamento, cancellazione e accesso.

> In conclusione, la gestione interna delle pagine rappresenta il **livello più basso dell’organizzazione fisica**,  
> dove il DBMS deve ottimizzare ogni byte per garantire **efficienza, coerenza e integrità dei dati**.

---


![](imgs/Pasted%20image%2020251125051229.png)

