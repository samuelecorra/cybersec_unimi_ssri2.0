# **M3 UD2 Lezione 4 - Realizzazione dei file – Allocazione dei blocchi**

### **1. Introduzione**

La **fase di allocazione dei blocchi fisici** rappresenta il momento in cui il file system stabilisce **come i dati di un file vengono distribuiti sul disco**.  
L’obiettivo è creare un **insieme ordinato di blocchi fisici** che rappresenti la struttura logica del file, garantendo al contempo **efficienza di accesso** e **ottimizzazione dello spazio**.

Le principali **tecniche di allocazione** sono:

- **allocazione contigua**,
    
- **allocazione collegata**,
    
- **allocazione indicizzata**.

---

### **2. Allocazione contigua**

#### **2.1 Descrizione**

Nell’**allocazione contigua**, i blocchi fisici di un file sono **memorizzati in modo sequenziale e adiacente** sul disco.

$$  
\text{File} = { B_i \ ,\ B_{i+1}\ , \ \dots \ ,\ B_{i+n} }  
$$

Ogni file è identificato da:

- **indirizzo del primo blocco**,
    
- **numero totale di blocchi allocati**.

#### **2.2 Vantaggi**

- **Accesso sequenziale molto rapido**, poiché i blocchi sono contigui.
    
- **Accesso diretto semplice**, tramite calcolo dell’indice:  
    $$  
    B_k = B_0 + k  
    $$

#### **2.3 Svantaggi**

- **Frammentazione esterna:** difficoltà nel trovare spazio contiguo per nuovi file o per l’estensione di file esistenti.
    
- **Compattazione costosa:** la deframmentazione richiede spostamenti massicci di dati.
    
- **Estensione dei file problematica:** non sempre è possibile aggiungere blocchi contigui.

#### **2.4 Tipi di frammentazione**

- **Esterna:** spazio libero non contiguo disperso sul disco.
    
- **Interna:** spazio inutilizzato all’interno dei blocchi allocati in eccesso.

---

### **3. Allocazione collegata**

#### **3.1 Descrizione**

L’**allocazione collegata (linked allocation)** utilizza una **lista concatenata di blocchi fisici**.  
Ogni blocco contiene:

- **i dati del file**,
    
- **un puntatore** al blocco successivo.

Esempio di rappresentazione:

$$  
B_1 \rightarrow B_2 \rightarrow B_3 \rightarrow \dots \rightarrow B_n  
$$

#### **3.2 Vantaggi**

- **Nessuna frammentazione esterna**, poiché i blocchi possono essere collocati ovunque.
    
- **Facilità di estensione del file**, aggiungendo semplicemente un nuovo blocco alla lista.

#### **3.3 Svantaggi**

- **Accesso diretto inefficiente:** per raggiungere un blocco intermedio, occorre percorrere l’intera catena.
    
- **Sovraccarico di spazio:** ogni blocco deve contenere un puntatore (riduzione dello spazio utile).
    
- **Rischio di perdita dei dati:** un guasto in un singolo blocco può interrompere la catena.

#### **3.4 Soluzione intermedia – Cluster**

Per ridurre l’overhead, più blocchi possono essere aggregati in **cluster**, ciascuno con un unico puntatore al successivo.

---

### **4. File Allocation Table (FAT)**

Un miglioramento dell’allocazione collegata è rappresentato dal modello **FAT (File Allocation Table)**, utilizzato in molti sistemi (es. MS-DOS, FAT32).

#### **Funzionamento**

- Tutti i puntatori ai blocchi vengono **concentrati in una tabella unica** situata all’inizio del disco.
    
- Ogni voce della tabella indica il **blocco successivo** nella catena o un **marcatore di fine file (EOF)**.

#### **Vantaggi**

- **Accesso più veloce**, poiché la catena dei blocchi può essere seguita direttamente in memoria.
    
- **Riduzione del rischio di perdita della catena**, essendo centralizzata.

#### **Svantaggi**

- La tabella FAT può diventare **molto grande** nei dischi di grandi dimensioni.
    
- Rimane comunque **inefficiente per l’accesso diretto casuale**.

---

### **5. Allocazione indicizzata**

#### **5.1 Descrizione**

Nell’**allocazione indicizzata**, a ogni file è associato un **blocco indice (i-node)** contenente la lista dei puntatori ai blocchi fisici del file.

Esempio:

```
i-node
 ├── B1
 ├── B2
 ├── B3
 └── ...
```

#### **5.2 Vantaggi**

- **Accesso diretto rapido**, grazie ai puntatori memorizzati in un’unica struttura.
    
- **Nessuna frammentazione esterna.**
    
- **Maggiore affidabilità**, poiché i blocchi del file non dipendono l’uno dall’altro.

#### **5.3 Svantaggi**

- **Dimensionamento del blocco indice:** se troppo piccolo, non può gestire file grandi; se troppo grande, spreca spazio.
    
- **Maggiore complessità di implementazione.**

---

### **6. Varianti dell’allocazione indicizzata**

#### **a. Schema collegato**

- I blocchi indice sono organizzati a **catena**: quando un indice è pieno, punta al blocco indice successivo.
    
- Soluzione adatta a file di grandi dimensioni ma rallenta l’accesso diretto.

#### **b. Indice multilivello**

- Introduce una **gerarchia di blocchi indice** (es. primario, secondario, terziario).
    
- Utilizzato in UNIX e derivati: permette di gestire file di dimensioni molto grandi.

#### **c. Schema combinato**

- Usa **un numero fisso di puntatori diretti** per i file piccoli e **puntatori indiretti** per i file grandi.
    
- È la soluzione adottata nella maggior parte dei file system moderni (es. ext, NTFS).

---

### **7. Ottimizzazione delle prestazioni**

Per migliorare le prestazioni di accesso e scrittura, vengono impiegate tecniche di **ottimizzazione** complementari:

1. **Caching**
    
    - Le informazioni di gestione (blocchi indice, FAT, directory) vengono mantenute temporaneamente in memoria principale.
        
    - Riduce drasticamente gli accessi al disco.
    
2. **Lettura anticipata (Read Ahead)**
    
    - Durante la lettura sequenziale, il sistema preleva **blocchi successivi prima che siano richiesti**.
        
    - Migliora il throughput complessivo, specialmente nei file di grandi dimensioni.

---

### **8. Gestione dello spazio libero**

La gestione dello spazio libero ha il compito di **tenere traccia dei blocchi fisici non allocati**.  
Le principali tecniche sono:

#### **8.1 Vettore di bit (bitmap)**

- Ogni bit rappresenta un blocco del disco:
    
    - `1` → blocco occupato
        
    - `0` → blocco libero

Esempio:

```
1011000110110
```

Indica che i blocchi 2, 5, 6, 9, 10 e 12 sono occupati.

**Vantaggi:**

- Rappresentazione compatta.
    
- Ricerca veloce dei blocchi liberi, soprattutto con supporto hardware.

---

#### **8.2 Lista collegata**

- I blocchi liberi sono collegati in una lista concatenata.
    
- Ogni blocco libero contiene un puntatore al successivo.

**Vantaggi:**

- Semplice da implementare.

**Svantaggi:**

- Accesso lento ai blocchi liberi lontani.
    
- Necessità di percorrere la lista per allocazioni multiple.

---

#### **8.3 Raggruppamento**

- Variante della lista collegata: ogni blocco libero contiene **indirizzi di più blocchi liberi**.
    
- L’ultimo indirizzo punta a un altro blocco di lista.

**Vantaggi:**

- Riduce gli accessi al disco per trovare nuovi blocchi liberi.
    
- Più efficiente della lista semplice.

---

#### **8.4 Conteggio**

- Ogni voce della struttura di gestione indica:
    
    - l’**indirizzo del primo blocco libero**,
        
    - e il **numero di blocchi consecutivi liberi**.

**Vantaggi:**

- Ideale per gestire **sequenze contigue di blocchi** liberi.
    
- Riduce l’overhead di memorizzazione.

**Svantaggi:**

- Meno efficiente se lo spazio libero è molto frammentato.

---

### **9. Sintesi finale**

|Tecnica di allocazione|Descrizione|Vantaggi|Svantaggi|
|---|---|---|---|
|**Contigua**|Blocchi memorizzati in modo sequenziale|Accesso rapido, semplice|Frammentazione esterna, difficile estensione|
|**Collegata**|Lista di blocchi con puntatori interni|Nessuna frammentazione esterna|Accesso diretto lento, rischio di perdita catena|
|**Indicizzata**|i-node con puntatori ai blocchi|Accesso diretto veloce, affidabilità|Blocco indice da dimensionare|
|**Bitmap**|Un bit per blocco|Rapida ricerca, compatta|Richiede scansione|
|**Lista collegata**|Blocchi liberi concatenati|Semplice|Accesso lento|
|**Raggruppamento**|Blocchi liberi a gruppi|Efficiente, meno accessi|Struttura più complessa|
|**Conteggio**|Indica blocco iniziale e numero consecutivo|Ottimo per blocchi contigui|Inefficiente se frammentato|

---

### **10. Conclusione**

Le strategie di **allocazione e gestione dello spazio libero** sono fondamentali per bilanciare **prestazioni, affidabilità e flessibilità** del file system.  
I sistemi moderni adottano approcci **ibridi**, combinando allocazione indicizzata, caching e bitmap per garantire **efficienza di accesso e gestione dinamica dello spazio**.