# **M3 UD1 Lezione 2 - Directory e loro caratteristiche (parte 1)**

### **1. Introduzione**

Le **directory** (o _direttori_) rappresentano la struttura logica che permette di **organizzare, classificare e gestire i file** all’interno del file system.  
Oltre a raggruppare i file secondo criteri logici, le directory forniscono supporto alla **ricerca efficiente**, alla **condivisione** e alla **protezione** delle informazioni.

---

### **2. Funzioni principali di una directory**

Le directory hanno tre funzioni fondamentali:

1. **Raggruppamento logico dei file**
    
    - Permettono di suddividere i file in insiemi omogenei in base a criteri di uso o contenuto.
    
2. **Gestione efficiente dell’accesso**
    
    - Consentono ricerche rapide e accessi organizzati ai file, evitando confusione e ridondanze.
    
3. **Supporto alla condivisione e protezione**
    
    - Offrono meccanismi per controllare i diritti di accesso e permettere la visibilità dei file a più utenti o processi.

---

### **3. Struttura di una directory**

Una **directory** è una **collezione di informazioni (attributi)** sui file appartenenti a un certo gruppo logico.  
Ogni voce (entry) contiene in genere:

- nome del file,
    
- tipo,
    
- dimensione,
    
- posizione fisica o puntatore al file,
    
- e attributi di accesso o proprietà.

---

### **4. Operazioni fondamentali**

Le directory supportano un insieme di **operazioni tipiche** distinte da quelle sui file.

#### **Operazioni sui file**

- Creazione
    
- Cancellazione
    
- Ridenominazione
    
- Modifica degli attributi
    
- Lettura e scrittura

#### **Operazioni sulle directory**

- Individuazione della **posizione logica** di un file nel file system.
    
- **Elencazione** dei file contenuti in una directory.
    
- **Ricerca** di file in base al nome o ad altri attributi.
    
- Creazione e cancellazione di **sottodirectory**.
    
- Navigazione nella struttura gerarchica (cambiamento della directory corrente).

---

### **5. Strutturazione del file system**

La modalità di organizzazione delle directory determina la **struttura logica del file system**.  
Nel tempo si sono sviluppati diversi modelli:

1. **A singolo livello**
    
2. **A due livelli**
    
3. **Ad albero**
    
4. **A grafo aciclico**
    
5. **A grafo generale**

---

### **6. Directory a singolo livello**

È la struttura più semplice e primitiva, tipica dei vecchi **sistemi mono-utente** con capacità di memoria limitata.

#### **Caratteristiche**

- Tutti i file si trovano nello stesso livello, in un’unica directory.
    
- Nessuna gerarchia o suddivisione logica.

#### **Problemi principali**

- **Omonimie:** non possono esistere file con lo stesso nome.
    
- **Difficoltà di ridenominazione:** ogni conflitto di nome richiede rinomina manuale.
    
- **Assenza di raggruppamento:** impossibile organizzare i file per progetto, utente o tipologia.

---

### **7. Directory a due livelli**

È una prima estensione logica della struttura a singolo livello.  
Ogni **utente** dispone di una propria **directory personale**.

#### **Caratteristiche**

- Ogni utente ha la propria area dedicata per file e sottodirectory.
    
- I file di utenti diversi possono avere **lo stesso nome**.
    
- Migliora l’efficienza di ricerca limitando il campo di esplorazione.

#### **Limiti**

- Mancanza di **raggruppamento logico** tra file di uno stesso utente.
    
- Assenza di **condivisione diretta** dei file tra utenti.
    
- Adatta solo a piccoli sistemi multi-utente.

---

### **8. Directory ad albero**

È la struttura adottata nei **sistemi operativi moderni**, basata su una **gerarchia di directory** con un’unica **radice (root)**.

#### **Caratteristiche principali**

- Ogni directory può contenere sia file che sottodirectory.
    
- Permette una **ricerca efficiente** e un’organizzazione gerarchica naturale.
    
- Supporta **raggruppamento logico** e **condivisione controllata**.

#### **Identificazione dei file**

Ogni file è identificato dal suo **percorso (path)**:

- **Percorso assoluto:** specifica il cammino completo dalla radice.  
    Esempio:
    
    ```
    /home/utente/documenti/file.txt
    ```
    
- **Percorso relativo:** specifica la posizione rispetto alla **directory corrente**.  
    Esempio:
    
    ```
    documenti/file.txt
    ```

#### **Vantaggi**

- Struttura flessibile e facilmente estendibile.
    
- Facilita la localizzazione dei file e la gestione multi-utente.
    
- Permette alias o collegamenti (link) tra file e directory.

---

### **9. Directory a grafo aciclico**

Questa struttura consente la **condivisione** di file e directory **tra utenti o applicazioni**.  
Ogni nodo (file o directory) può avere **più genitori**, ma non sono ammesse **ciclicità**.

#### **Esempio**

Un documento condiviso da più utenti può apparire in più directory, pur essendo **fisicamente unico**.

#### **Vantaggi**

- Favorisce la cooperazione e il riuso dei dati.
    
- Evita duplicazione dei file.

#### **Svantaggi**

- Necessita di meccanismi per evitare cicli.
    
- Maggiore complessità nella **gestione dei link** e delle **rimozioni** (se un nodo è condiviso, non deve essere cancellato finché altri riferimenti lo usano).

---

### **10. Directory a grafo generale**

È la generalizzazione massima del modello precedente:  
consente **collegamenti ciclici** tra directory e file, permettendo **strutture arbitrarie**.

#### **Vantaggi**

- Flessibilità estrema nella rappresentazione delle relazioni.

#### **Svantaggi**

- Richiede controlli più complessi per evitare **loop infiniti** nella ricerca o nella navigazione.
    
- Necessita di **meccanismi di riferimento (contatori o marcatori)** per la corretta gestione di cancellazioni e percorsi.

---

### **11. Sintesi finale**

|Struttura|Caratteristiche principali|Vantaggi|Svantaggi|
|---|---|---|---|
|**Singolo livello**|Tutti i file in un’unica directory|Semplice|Nessuna gerarchia, omonimie|
|**Due livelli**|Directory per utente|Evita conflitti di nome|Nessuna condivisione|
|**Ad albero**|Struttura gerarchica con root|Raggruppamento logico, efficienza|Complessità moderata|
|**Grafo aciclico**|Condivisione controllata|Evita duplicazione file|Gestione più complessa|
|**Grafo generale**|Collegamenti ciclici ammessi|Massima flessibilità|Rischio di loop, difficile gestione|

---

### **12. Conclusione**

Le **directory** rappresentano l’ossatura logica del file system.  
Attraverso strutture sempre più sofisticate — dal modello a singolo livello fino al grafo generale — i sistemi operativi riescono a coniugare **efficienza, flessibilità e sicurezza** nella gestione dei dati.