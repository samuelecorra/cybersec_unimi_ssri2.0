## ***Lezione 5: Gerarchia di memoria***

---

> 📌 Questa lezione rielabora integralmente le pagine 26–32 di `M5doc.pdf`: livelli di memorizzazione, memoria online/offline, disk cache, memoria virtuale, MMU e compromesso costo–capacità–velocità.

### **1. Una piramide di supporti**

Il calcolatore usa supporti con caratteristiche differenti. Ordinandoli dalla CPU verso l'esterno si ottiene la gerarchia illustrata nel PDF:

| Livello | Capacità indicativa nel contesto del PDF | Velocità | Ruolo |
| --- | --- | --- | --- |
| GPR | pochi registri | massima | operandi immediati della CPU |
| Cache L1 | alcuni KiB | molto alta | blocchi più probabilmente utili |
| Cache L2 | centinaia di KiB o MiB | alta | livello intermedio |
| Memoria di lavoro | centinaia di MiB o GiB | media | dati e programmi direttamente indirizzabili |
| Disco magnetico | decine/centinaia di GiB o più | bassa | memoria di massa online |
| Disco ottico o nastro | capacità elevata e basso costo | molto bassa | backup e archiviazione offline |

Le quantità sono fotografie storiche, non limiti architetturali attuali. Resta valido l'ordinamento: scendendo aumentano capacità e tempo di accesso, mentre diminuisce il costo per bit.

### **2. Memoria online e offline**

Il disco magnetico rappresenta la tipica memoria di massa **online**: programmi e dati devono essere prontamente raggiungibili dal sistema, anche se non risiedono in RAM.

Supporti ottici e nastri rappresentano la memoria **offline** o di archivio. Hanno tempi di accesso maggiori, da centinaia di millisecondi a secondi nel quadro descritto, e sono destinati a:

- versioni originali o installabili dei programmi;
- software conservato ma non usato abitualmente;
- backup e copie di sicurezza;
- ripristino dopo guasti del supporto online.

La distinzione riguarda la prontezza d'accesso e il ruolo, non soltanto la tecnologia fisica.

### **3. Accesso elettronico e accesso meccanico**

La DRAM è elettronica e ad accesso casuale: all'interno di un dispositivo, ogni cella è raggiunta con un tempo comparabile. In una gerarchia di cache, la latenza complessiva dipende però dal verificarsi di hit o miss.

Il disco magnetico è elettromeccanico. Il tempo dipende dalla posizione:

1. la testina deve raggiungere la traccia corretta (**seek**);
2. occorre attendere che la rotazione porti il settore sotto la testina;
3. il settore viene trasferito.

Per questo il PDF parla di accesso sequenziale o misto: una sequenza di settori vicini costa molto meno di numerosi accessi sparsi.

### **4. Disk cache**

Un primo modo di collegare memoria di lavoro e disco è la **disk cache**. Una porzione della RAM conserva i settori su disco richiesti più recentemente o ritenuti più probabili:

$$
\text{CPU}\longleftrightarrow\text{RAM/disk cache}\longleftrightarrow\text{disco}.
$$

Il principio è ancora la località. I programmi che riutilizzano spesso gli stessi file possono evitare molti accessi meccanici. Il guadagno dipende però fortemente dal carico: per questo il PDF considera la tecnica meno universalmente efficace rispetto alla cache della CPU.

### **5. Memoria virtuale**

La **memoria virtuale** dà a ogni programma l'impressione di possedere un ampio spazio di indirizzamento privato e continuo. Solo una parte è realmente presente nella memoria fisica; il resto rimane su disco fino a quando serve.

Durante l'esecuzione la CPU genera **indirizzi virtuali**, appartenenti allo spazio del processo. Essi non coincidono necessariamente con gli indirizzi fisici della RAM.

La quantità di RAM non impone direttamente la dimensione logica del singolo programma o il numero di programmi avviati. Ne condiziona però fortemente le prestazioni:

- più pagine utili risiedono in RAM, meno trasferimenti servono;
- molti programmi che condividono poca RAM generano frequenti movimenti fra disco e memoria;
- un eccesso di trasferimenti può annullare i vantaggi del multiprogramming.

### **6. Compiti della MMU**

La **Memory Management Unit** (MMU) è l'hardware che si interpone nella traduzione degli indirizzi. Deve collaborare con il sistema operativo per:

1. tradurre l'indirizzo virtuale generato dalla CPU in indirizzo fisico;
2. verificare se la parte richiesta è presente in memoria;
3. rilevare l'assenza e trasferire il controllo al sistema operativo;
4. permettere il caricamento da disco e l'eventuale espulsione di un'altra parte;
5. riprendere l'accesso quando la traduzione diventa valida.

Le principali organizzazioni citate dal PDF sono:

- paginazione;
- segmentazione;
- segmentazione paginata.

La loro trattazione dettagliata appartiene ai Sistemi Operativi; qui interessa il ruolo architetturale della traduzione e del trasferimento fra livelli.

### **7. Costo per bit e tempo di accesso**

Il grafico qualitativo del PDF pone:

- il **costo per bit** sull'asse verticale;
- il **tempo di accesso** sull'asse orizzontale.

I supporti si dispongono lungo una curva decrescente:

| Tecnologia | Costo per bit | Tempo di accesso |
| --- | --- | --- |
| Memorie bipolari a semiconduttore | molto alto | minimo |
| Memorie MOS | alto | basso |
| Dischi magnetici | inferiore | elevato |
| Dischi ottici | ancora inferiore | maggiore |
| Nastri | minimo | massimo |

La memoria ideale occuperebbe l'angolo in basso a sinistra: costo minimo e velocità massima. Nessun singolo supporto possiede entrambe le qualità.

### **8. L'effetto complessivo della gerarchia**

La gerarchia avvicina il sistema alla memoria ideale senza inventare una nuova tecnologia. L'informazione viene stratificata:

- la parte quotidianamente utile risiede sui dischi online;
- la parte in esecuzione risiede in memoria elettronica;
- la parte calda sale nei livelli di cache;
- le copie di sicurezza restano sui supporti più capienti ed economici.

Se $h_i$ è la probabilità che la richiesta sia soddisfatta al livello $i$, il tempo medio è una combinazione pesata dei costi di hit e delle penalità necessarie a scendere. Un'elevata località mantiene piccola la probabilità di raggiungere i livelli più lenti.

### **9. Lo sforzo architetturale**

Il risultato non dipende soltanto dall'evoluzione dei dispositivi. Occorre progettare:

- dimensione dei livelli e dei blocchi;
- politiche di mapping e sostituzione;
- scritture e coerenza;
- traduzione virtuale e trasferimenti;
- percorsi capaci di sovrapporre le latenze.

> ✅ Sfruttando la località a ogni livello, il sistema offre alla CPU un tempo medio vicino a quello delle memorie più veloci e, contemporaneamente, uno spazio informativo vicino alla capacità dei supporti più grandi ed economici.
