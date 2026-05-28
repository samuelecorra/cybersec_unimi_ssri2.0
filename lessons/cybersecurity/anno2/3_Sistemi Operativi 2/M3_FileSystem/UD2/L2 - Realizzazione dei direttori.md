# **M3 UD2 Lezione 2 - Realizzazione dei direttori**

---

### **1. Introduzione**

La realizzazione dei **direttori** è un aspetto fondamentale dell'implementazione del file system, perché influenza direttamente il tempo necessario per reperire i file.

Un direttorio contiene i descrittori dei file appartenenti a un certo insieme logico. La struttura scelta per memorizzare tali descrittori determina il costo delle operazioni di ricerca, inserimento, cancellazione ed elencazione.

Le tecniche principali sono:

- realizzazione con **lista**;
- ottimizzazioni tramite ordinamento, cache e strutture ad albero;
- realizzazione con **tabella di hash**.

> 📌 Realizzare un direttorio significa scegliere una struttura dati per conservare e reperire efficientemente i descrittori dei file.

---

### **2. Struttura a lista**

La struttura più semplice per realizzare un direttorio è una **lista** di descrittori di file.

Dal punto di vista logico, il direttorio contiene un riferimento al primo descrittore della lista. Ogni descrittore contiene poi un puntatore al descrittore successivo.

La realizzazione concreta dei puntatori dipende dalla soluzione adottata dal file system.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.1. Caratteristiche**

La lista contiene tutti i descrittori dei file della directory.

Ogni elemento della lista rappresenta un file e contiene informazioni come:

- nome del file;
- attributi;
- riferimenti al blocco di controllo del file;
- puntatore al prossimo elemento della lista.

#### **2.2. Visualizzazione dell'elenco**

La struttura a lista è efficiente per visualizzare l'elenco dei file, perché è sufficiente scandire la lista dall'inizio alla fine.

Se però si vuole visualizzare l'elenco in ordine alfabetico, occorre ordinare gli elementi, oppure mantenere la lista già ordinata.

> 💡 Una lista non ordinata è semplice da aggiornare, ma una lista ordinata rende più efficiente la visualizzazione ordinata e alcune ricerche.

---

### **3. Inserimento ordinato**

Una semplificazione consiste nel mantenere la lista sempre ordinata.

Invece di inserire un nuovo elemento in testa o in coda in tempo costante, il sistema scandisce la lista e inserisce il descrittore nella posizione corretta.

#### **3.1. Vantaggio**

Se la lista è mantenuta ordinata, la visualizzazione ordinata dell'elenco dei file diventa immediata: basta scandire la lista.

#### **3.2. Costo**

Il costo viene pagato durante l'inserimento, perché è necessario trovare la posizione corretta.

$$
\text{inserimento in testa} = O(1)
$$

$$
\text{inserimento ordinato} = O(n)
$$

> ⚠️ Mantenere la lista ordinata sposta il costo dall'elencazione all'inserimento.

---

### **4. Ricerca nella lista**

Il reperimento di un file in una lista è costoso, perché richiede una scansione lineare.

Non esiste un accesso diretto agli elementi: per trovare un file bisogna partire dal primo descrittore e procedere uno dopo l'altro.

$$
\text{ricerca in lista non ordinata} = O(n)
$$

#### **4.1. Ricerca in lista ordinata**

Se la lista è ordinata, la ricerca può terminare prima quando il file non esiste.

Per esempio, cercando un nome, se si incontra un elemento lessicograficamente superiore al nome desiderato, si può concludere che il file non è presente.

Questo evita di arrivare sempre in fondo alla lista nei casi di fallimento, ma la complessità resta lineare nel caso generale.

> 📌 La lista ordinata migliora alcuni casi pratici, ma non elimina il problema della scansione sequenziale.

---

### **5. Ottimizzazioni della lista**

Per migliorare le prestazioni della struttura a lista si possono adottare diverse tecniche.

#### **5.1. Cache**

Una prima tecnica consiste nel mantenere in memoria centrale una cache della lista del direttorio o delle parti più usate.

Questo riduce gli accessi al disco e velocizza ricerche ripetute.

#### **5.2. Lista sempre ordinata**

Mantenere la lista sempre ordinata semplifica l'elencazione e permette di interrompere prima alcune ricerche negative.

#### **5.3. Alberi bilanciati**

Una struttura più complessa è l'uso di alberi bilanciati, come i **B-tree**.

Questa soluzione conserva l'ordinamento logico degli elementi, ma permette inserimenti e ricerche più efficienti rispetto a una lista lineare.

$$
\text{ricerca in B-tree} = O(\log n)
$$

> 💡 I B-tree sono adatti a directory grandi perché riducono il costo di ricerca e inserimento mantenendo l'ordinamento.

---

### **6. Struttura con tabella di hash**

Una soluzione alternativa è realizzare il direttorio con una **tabella di hash**.

In questo caso si usa ancora una lista per conservare i descrittori, ma non si cerca più scandendo l'intera lista.

Si aggiunge una tabella di hash che permette di individuare rapidamente la porzione di lista in cui cercare.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.1. Funzionamento**

Quando si cerca un file:

1. si applica una funzione di hash al nome del file;
2. si ottiene un indice nella tabella di hash;
3. l'indice individua una porzione della lista;
4. si scandisce solo quella sottolista;
5. si reperisce il descrittore desiderato, se presente.

$$
h(\text{nome file}) = \text{indice nella tabella hash}
$$

> 📌 La tabella di hash evita la scansione completa della directory: la ricerca viene limitata alla sottolista individuata dalla funzione di hash.

---

### **7. Collisioni**

La scelta della funzione di hash è critica.

La funzione deve distribuire i file tra gli indici della tabella in modo uniforme, minimizzando le **collisioni**.

Una collisione si verifica quando più descrittori producono lo stesso valore di hash.

In quel caso, tutti questi descrittori finiscono nella stessa porzione di lista, e la ricerca deve scandire una sottolista più lunga.

#### **7.1. Obiettivo della funzione di hash**

Una buona funzione di hash deve:

- distribuire bene i nomi dei file;
- ridurre la lunghezza media delle sottoliste;
- limitare le collisioni;
- mantenere basso il costo di calcolo dell'indice.

> ⚠️ Se molte collisioni finiscono nello stesso indice, la tabella di hash degenera verso una scansione lineare della lista.

---

### **8. Prestazioni della tabella di hash**

Con una buona funzione di hash, la ricerca richiede:

1. calcolo dell'indice in tempo costante;
2. accesso alla tabella in tempo costante;
3. scansione lineare solo della piccola porzione di lista associata all'indice.

$$
\text{ricerca media con hash} \approx O(1) + O(k)
$$

dove $k$ è la dimensione della sottolista individuata dalla funzione di hash.

Se la tabella è ben dimensionata e le collisioni sono poche, $k$ resta piccolo.

> ✅ La tabella di hash rende l'accesso ai descrittori molto più rapido rispetto alla lista pura, soprattutto con directory grandi.

---

### **9. Confronto tra liste e hash**

La lista è semplice e richiede poca struttura aggiuntiva, ma ha costo lineare nelle ricerche.

La tabella di hash è più complessa, ma consente accessi molto più rapidi.

$$
\text{lista} =
\begin{cases}
\text{semplice} \\
\text{ricerca lineare}
\end{cases}
$$

$$
\text{hash} =
\begin{cases}
\text{più complessa} \\
\text{ricerca media rapida} \\
\text{dipendente dalle collisioni}
\end{cases}
$$

---

### **10. Sintesi finale**

> ✅ I direttori possono essere realizzati con strutture semplici a lista o con strutture più efficienti basate su tabelle di hash.

In questa lezione sono stati analizzati:

- lista di descrittori come struttura più semplice;
- inserimento in testa o in coda;
- inserimento ordinato;
- costo della ricerca lineare;
- vantaggi della lista ordinata;
- uso della cache;
- uso di B-tree;
- tabella di hash;
- funzione di hash;
- collisioni e loro impatto sulle prestazioni.

La scelta della struttura dipende dalla dimensione delle directory e dal compromesso desiderato tra semplicità, costo di aggiornamento e velocità di ricerca.
