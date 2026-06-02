# **M3 UD2 Lezione 4 - Realizzazione dei file - Allocazione dei blocchi**

---

### **1. Introduzione**

Questa lezione studia come il file system realizza la **sequenza ordinata di blocchi fisici** necessaria a supportare la visione logica del file.

Nelle lezioni precedenti il file è stato visto come:

- sequenza di record logici per il programma;
- byte stream per il file system;
- sequenza di blocchi fisici nella memoria di massa.

Ora il problema diventa stabilire **quali blocchi fisici assegnare a un file** e **come ricordare il loro ordine**.

Le tecniche principali sono:

- allocazione contigua;
- allocazione collegata;
- allocazione indicizzata.

La lezione analizza anche la gestione della **lista libera**, cioè delle strutture con cui il sistema operativo tiene traccia dei blocchi fisici disponibili.

> 📌 Allocare i blocchi fisici significa costruire la sequenza che permette di ricostruire il byte stream del file.

---

### **2. Allocazione contigua**

Nell'**allocazione contigua**, i blocchi fisici dello stesso file vengono collocati uno dopo l'altro sul disco.

Se un file occupa $n$ blocchi e il primo blocco è $b_s$, allora i suoi blocchi sono:

$$
\{b_s, b_{s+1}, b_{s+2}, \dots, b_{s+n-1}\}
$$

Per descrivere il file bastano quindi:

- il blocco iniziale;
- la lunghezza del file in blocchi.

![](imgs/Pasted%20image%2020260601213401.png)

#### **2.1. Vantaggi**

L'accesso sequenziale è molto efficiente, perché il blocco successivo si trova fisicamente vicino al blocco corrente.

Questo riduce gli spostamenti della testina e rende rapida la lettura di file scanditi dall'inizio alla fine.

#### **2.2. Limiti**

Il limite principale è la necessità di trovare spazio contiguo sufficiente.

Quando si crea un nuovo file, il sistema deve individuare una sequenza di blocchi liberi adiacenti abbastanza grande da contenerlo.

Quando si estende un file già esistente, possono verificarsi due casi:

- dopo il file esiste spazio contiguo libero sufficiente;
- dopo il file non esiste spazio sufficiente e bisogna spostare o ricopiare il file altrove.

Nel secondo caso l'operazione diventa costosa, perché la parte già esistente del file deve essere ricopiata in una nuova zona più ampia.

> ⚠️ L'allocazione contigua è efficiente quando lo spazio è disponibile, ma rende costose la crescita dei file e la gestione di dischi frammentati.

#### **2.3. Frammentazione esterna**

Il problema tipico dell'allocazione contigua è la **frammentazione esterna**.

Il disco può contenere abbastanza blocchi liberi in totale, ma non abbastanza blocchi liberi consecutivi.

In questo caso, per creare uno spazio contiguo sufficiente, sarebbe necessario compattare il disco:

- spostare file già presenti;
- raggruppare lo spazio libero;
- aggiornare le strutture di gestione.

La compattazione richiede molto tempo e rende questa tecnica poco flessibile.

---

### **3. Allocazione collegata**

Nell'**allocazione collegata**, i blocchi del file non devono essere adiacenti.

Ogni blocco contiene:

- una parte dei dati del file;
- un puntatore al blocco successivo.

La sequenza del file viene quindi rappresentata come una lista:

$$
b_0 \rightarrow b_1 \rightarrow b_2 \rightarrow \dots \rightarrow b_n
$$

![](imgs/Pasted%20image%2020260601213512.png)

#### **3.1. Vantaggi**

Questa tecnica elimina la frammentazione esterna: qualunque blocco libero può essere usato per estendere un file.

Non è necessario trovare blocchi adiacenti e non è necessario compattare il disco quando esistono blocchi liberi sufficienti, anche se sparsi.

#### **3.2. Accesso diretto lento**

Il limite principale è l'accesso diretto.

Per raggiungere il blocco logico di indice $k$, il sistema deve partire dal primo blocco del file e seguire i puntatori:

$$
b_0 \rightarrow b_1 \rightarrow \dots \rightarrow b_k
$$

Quindi il costo è:

$$
O(k)
$$

> ⚠️ L'allocazione collegata è adatta all'accesso sequenziale, ma rende lento il reperimento diretto di un blocco interno al file.

#### **3.3. Overhead dei puntatori**

Un altro problema è lo spazio occupato dai puntatori.

Ogni blocco fisico deve riservare una porzione per memorizzare l'indirizzo del blocco successivo. Questa porzione non può essere usata per i dati del file.

Per ridurre l'overhead, spesso i blocchi vengono raggruppati in **cluster**.

Un cluster è un insieme di blocchi adiacenti trattato come un'unità di allocazione.

In questo modo:

- si riduce il numero di puntatori;
- si migliora l'efficienza degli accessi sequenziali;
- si mantiene una certa flessibilità rispetto all'allocazione contigua.

#### **3.4. Problemi di affidabilità**

L'allocazione collegata è fragile rispetto ai guasti.

Se un blocco si rovina e contiene il puntatore al blocco successivo, il sistema può perdere il riferimento a tutta la parte successiva del file.

> ⚠️ La perdita di un puntatore nella catena può impedire di raggiungere i blocchi successivi del file.

---

### **4. File Allocation Table**

Per migliorare l'allocazione collegata si può usare una **File Allocation Table**.

In questa soluzione, i puntatori ai blocchi successivi non sono memorizzati dentro i blocchi dati, ma in una tabella separata conservata sul disco.

La directory contiene il descrittore del file, che individua il primo blocco fisico del file.

Usando il numero di quel blocco come indice nella File Allocation Table, si trova il blocco successivo.

Il procedimento viene ripetuto fino alla fine del file.

![](imgs/Pasted%20image%2020260601213723.png)

Esempio concettuale:

$$
\text{descrittore} \rightarrow b_0
$$

$$
\text{FAT}[b_0] = b_1,\quad \text{FAT}[b_1] = b_2,\quad \dots
$$

#### **4.1. Vantaggi**

La File Allocation Table migliora la gestione perché:

- evita di sottrarre spazio ai blocchi dati per i puntatori;
- concentra le informazioni di collegamento in una struttura separata;
- può essere duplicata in più posizioni del disco per aumentare l'affidabilità;
- permette di seguire la catena dei blocchi senza leggere ogni blocco dati.

#### **4.2. Limite**

Rimane però un limite importante: per trovare un blocco interno al file bisogna comunque seguire una catena di riferimenti nella tabella.

Per raggiungere il blocco logico $k$, occorre scandire:

$$
\text{FAT}[b_0], \text{FAT}[b_1], \dots, \text{FAT}[b_{k-1}]
$$

Quindi l'accesso diretto resta meno efficiente rispetto a una struttura indicizzata.

---

### **5. Allocazione indicizzata**

Nell'**allocazione indicizzata**, ogni file ha una propria tabella di indici.

La tabella contiene, in ordine, i numeri dei blocchi fisici che compongono il file.

Se si vuole accedere al blocco logico di indice $i$, basta leggere l'elemento $i$ della tabella:

$$
\text{blocco fisico} = I[i]
$$

dove $I$ è il blocco indice del file.

![](imgs/Pasted%20image%2020260601213908.png)

#### **5.1. Vantaggi**

L'allocazione indicizzata offre:

- assenza di frammentazione esterna;
- accesso diretto veloce;
- indipendenza tra i blocchi dati;
- maggiore affidabilità, perché gli indici possono essere duplicati;
- nessun puntatore dentro i blocchi dati.

Per accedere al terzo blocco logico, numerando da zero, si legge direttamente:

$$
I[2]
$$

Il valore trovato è il numero del blocco fisico da leggere sul disco.

> ✅ L'allocazione indicizzata trasforma il reperimento di un blocco logico in un accesso diretto alla tabella degli indici.

#### **5.2. Problema del dimensionamento**

Il blocco indice limita la dimensione massima del file.

Se il blocco indice contiene solo un numero limitato di riferimenti, allora anche il file può contenere solo un numero limitato di blocchi.

Serve quindi una strategia per gestire file grandi senza sprecare troppo spazio per i file piccoli.

---

### **6. Blocco indice collegato**

Una prima soluzione è usare blocchi indice collegati.

Ogni blocco indice contiene:

- riferimenti a blocchi dati nelle prime posizioni;
- un riferimento a un altro blocco indice nell'ultima posizione.

In forma concettuale:

$$
I_0 \rightarrow I_1 \rightarrow I_2 \rightarrow \dots
$$

Ogni indice punta a molti blocchi dati e all'indice successivo.

![](imgs/Pasted%20image%2020260601214535.png)

Questa soluzione permette di gestire file grandi, ma per trovare blocchi molto avanzati puo' essere necessario scandire piu' blocchi indice.

> ⚠️ Il blocco indice collegato estende la dimensione massima del file, ma reintroduce una forma di scansione sequenziale degli indici.

---

### **7. Indice multilivello**

Una seconda soluzione e' usare un indice **multilivello**.

Nel caso a due livelli:

- il primo livello non punta direttamente ai blocchi dati;
- ogni elemento del primo livello punta a una tabella indice di secondo livello;
- le tabelle di secondo livello puntano ai blocchi fisici del file.

![](imgs/Pasted%20image%2020260601215002.png)

Questa struttura crea una forma ad albero:

$$
\text{indice di primo livello} \rightarrow \text{indice di secondo livello} \rightarrow \text{blocchi dati}
$$

Il vantaggio è che il blocco desiderato può essere individuato più rapidamente rispetto a una catena lunga di indici, ora in un tempo tipicamente logaritmico!

In generale, una struttura multilivello permette di gestire file molto grandi aumentando progressivamente il numero di livelli.

---

### **8. Schema combinato**

Lo **schema combinato** mescola più tecniche.

Il descrittore del file contiene:

- riferimenti diretti ai primi blocchi dati;
- un riferimento indiretto di primo livello;
- eventualmente riferimenti indiretti di secondo o terzo livello.

L'idea è adattare il costo di gestione alla dimensione del file.

![](imgs/Pasted%20image%2020260601215059.png)

#### **8.1. File piccoli**

I file piccoli possono essere gestiti usando solo riferimenti diretti.

Questo evita di creare strutture indirette inutili.

#### **8.2. File medi**

I file un po' più grandi usano un blocco indiretto di primo livello, che contiene riferimenti a blocchi dati.

#### **8.3. File grandi**

I file molto grandi usano indici a due o tre livelli.

Questo consente di aumentare molto la dimensione massima gestibile senza penalizzare eccessivamente i file piccoli.

> 💡 Lo schema combinato è efficiente perché usa strutture semplici per file piccoli e strutture più potenti solo quando servono.

---

### **9. Ottimizzazione delle prestazioni**

Le informazioni di allocazione sono essenziali per raggiungere i blocchi del file.

Se restano solo sul disco, ogni accesso può richiedere letture aggiuntive delle strutture di gestione.

Per migliorare le prestazioni si usano due tecniche principali.

#### **9.1. Caching**

Le informazioni di gestione vengono portate in memoria centrale:

- File Allocation Table;
- blocchi indice;
- descrittori;
- directory;
- bitmap o liste dello spazio libero.

In questo modo il sistema riduce gli accessi al disco e può risolvere più rapidamente la posizione dei blocchi fisici.

#### **9.2. Lettura anticipata**

Quando il sistema prevede che alcuni blocchi saranno richiesti a breve, può leggerli anticipatamente.

La lettura anticipata può riguardare:

- informazioni di gestione;
- blocchi fisici del file;
- porzioni successive del byte stream.

> 📌 Preparare in memoria centrale una porzione più ampia del byte stream riduce il tempo di attesa delle letture successive.

---

### **10. Gestione dello spazio libero**

Il file system deve sapere quali blocchi fisici sono liberi e possono essere assegnati a nuovi file o a file in crescita.

La struttura che descrive i blocchi disponibili viene detta **lista libera**, anche quando non è realizzata letteralmente come lista.

Le tecniche principali sono:

- bitmap;
- lista collegata;
- conteggio di gruppi contigui.

---

### **11. Bitmap**

La **bitmap** associa un bit a ogni blocco fisico.

Una convenzione possibile è:

$$
\begin{cases}
0 & \text{blocco libero} \\
1 & \text{blocco occupato}
\end{cases}
$$

Per trovare un blocco libero, il sistema cerca nella bitmap un bit posto a zero.

L'indice del bit corrisponde al blocco fisico libero.

![](imgs/Pasted%20image%2020260601215342.png)

La bitmap è efficiente soprattutto quando esiste supporto hardware o istruzioni macchina capaci di cercare rapidamente bit con un certo valore.

> ✅ La bitmap è compatta e rende semplice verificare lo stato di un blocco fisico.

---

### **12. Lista collegata dei blocchi liberi**

Un'altra tecnica consiste nel creare una **lista collegata dei blocchi liberi**.

E' come se il sistema mantenesse un file speciale che collega uno dopo l'altro tutti i blocchi disponibili.

![](imgs/Pasted%20image%2020260601215441.png)

Per allocare un blocco, il sistema prende il primo blocco della lista libera.

Questa soluzione è semplice, ma può essere inefficiente quando si vogliono trovare blocchi liberi adiacenti.

> ⚠️ La lista collegata semplice permette di trovare blocchi liberi, ma non evidenzia subito gruppi contigui utili per migliorare le prestazioni.

---

### **13. Conteggio di blocchi contigui**

Per migliorare la gestione dello spazio libero, il sistema può memorizzare gruppi di blocchi liberi contigui.

Invece di registrare ogni blocco libero singolarmente, ogni elemento della struttura contiene:

- il riferimento al primo blocco libero;
- il numero di blocchi fisici adiacenti liberi.

![](imgs/Pasted%20image%2020260601215824.png)

Formalmente:

$$
(\text{primo blocco}, \text{numero di blocchi contigui})
$$

Esempio:

$$
(120, 8)
$$

significa che sono liberi 8 blocchi consecutivi a partire dal blocco 120.

Questa tecnica riduce la dimensione della lista libera e permette di cercare rapidamente gruppi di blocchi contigui.

> 💡 Anche quando non è obbligatorio allocare tutto un file in modo contiguo, usare gruppi contigui migliora le prestazioni di lettura e scrittura.

---

### **14. Sintesi**

| Tecnica | Idea principale | Vantaggi | Limiti |
|---|---|---|---|
| **Allocazione contigua** | Blocchi del file adiacenti | Accesso sequenziale rapido | Frammentazione esterna, difficile estensione |
| **Allocazione collegata** | Blocchi collegati da puntatori | Nessuna frammentazione esterna | Accesso diretto lento, fragilita' dei puntatori |
| **File Allocation Table** | Puntatori raccolti in una tabella | Meno overhead nei blocchi dati, duplicabile | Accesso diretto ancora basato su scansione |
| **Allocazione indicizzata** | Tabella specifica per file | Accesso diretto veloce, affidabile | Dimensionamento del blocco indice |
| **Indice collegato** | Lista di blocchi indice | Gestisce file grandi | Ricerca piu' lenta nei file molto grandi |
| **Indice multilivello** | Struttura ad albero di indici | Gestisce file grandi con ricerca piu' efficiente | Maggiore complessita' |
| **Schema combinato** | Diretti + indiretti | Adatto a file piccoli e grandi | Struttura piu' articolata |
| **Bitmap** | Un bit per blocco | Compatta, semplice | Richiede scansione della mappa |
| **Lista libera collegata** | Blocchi liberi concatenati | Semplice | Non facilita la ricerca di contiguita' |
| **Conteggio** | Primo blocco + lunghezza | Efficiente per gruppi contigui | Meno efficace se lo spazio e' molto frammentato |

---

### **15. Conclusione**

L'allocazione dei blocchi fisici è il meccanismo con cui il file system costruisce la sequenza necessaria a ricreare il byte stream di un file.

Le tecniche contigua, collegata e indicizzata rappresentano compromessi diversi tra:

- velocità di accesso;
- facilità di crescita dei file;
- affidabilità;
- uso dello spazio;
- complessità delle strutture dati.

La gestione dello spazio libero completa il problema, perché permette al sistema operativo di reperire rapidamente blocchi disponibili e, quando possibile, di privilegiare gruppi contigui per migliorare le prestazioni.
