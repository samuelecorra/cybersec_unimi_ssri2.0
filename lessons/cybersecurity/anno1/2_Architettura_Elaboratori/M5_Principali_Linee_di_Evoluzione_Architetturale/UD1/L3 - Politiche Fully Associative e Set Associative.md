## ***Lezione 3: Politiche Fully Associative e Set Associative***

---

> 📌 Questa lezione rielabora integralmente le pagine 16–22 di `M5doc.pdf`: mapping libero, memoria associativa dei tag, sostituzione LRU e compromesso delle cache set-associative.

### **1. Perché rimuovere il mapping fisso**

Nella cache direct-mapped del PDF, chiamata Tag Associative, ogni blocco di memoria può occupare una sola linea. La soluzione è semplice ma può lasciare inutilizzate alcune linee mentre due blocchi frequentemente richiesti si contendono sempre la stessa.

La politica **Fully Associative** elimina il vincolo:

> 📌 Qualunque blocco della memoria di lavoro può essere copiato in qualunque linea della cache.

Non esiste più il gruppo associato a una linea. Una cache da 128 linee offre 128 possibili destinazioni a ogni blocco.

### **2. Nuova struttura dell'indirizzo**

Nell'esempio con indirizzi a 16 bit e blocchi da 16 parole:

- i 4 LSB continuano a indicare la parola nel blocco (`NP`);
- i restanti 12 bit identificano direttamente uno dei 4096 blocchi di MdL (`NBMdL`).

| Campo | Bit | Funzione |
| --- | ---: | --- |
| `NBMdL` | 12 | numero completo del blocco in memoria di lavoro |
| `NP` | 4 | offset della parola nel blocco |

La suddivisione è:

$$
\text{indirizzo}=NBMdL\;\Vert\;NP.
$$

Non esiste più un indice di linea ricavabile dall'indirizzo. Perciò ogni tag deve contenere tutti i 12 bit del numero di blocco, anziché i soli 5 bit dell'organizzazione precedente.

### **3. Ricerca associativa**

Il blocco richiesto potrebbe essere in qualunque linea. Una scansione sequenziale dei 128 tag vanificherebbe il vantaggio della cache. La tag memory viene quindi realizzata come **memoria ad accesso associativo**, o *content-addressable memory* (CAM):

1. `NBMdL` viene presentato come registro associativo;
2. tutte le righe confrontano contemporaneamente i propri bit con il valore cercato;
3. ogni riga produce 1 soltanto se tutti i bit coincidono;
4. la memoria restituisce l'indicazione di assenza oppure la linea che ha prodotto il match.

Per ogni bit, una XNOR segnala uguaglianza; l'AND di tutti i confronti produce il match della riga:

$$
match_j=\bigwedge_{k=0}^{11}\overline{tag_{j,k}\oplus NBMdL_k}.
$$

Tutte le $match_j$ vengono calcolate in parallelo. Un encoder può convertire la linea attiva nell'indice `NBMC` del blocco di cache trovato.

> 💡 La memoria ordinaria risponde alla domanda “che contenuto ha questo indirizzo?”. La memoria associativa risponde a “in quale indirizzo si trova questo contenuto?”.

### **4. Hit in una cache fully associative**

Se la CAM trova `NBMdL` nella linea `NBMC`, il gestore:

1. legge `MC[NBMC,NP]`;
2. incrementa a saturazione i contatori di età delle altre linee;
3. azzera il contatore di `NBMC`, segnalandola come appena usata.

Il trasferimento del dato avviene quindi dalla linea individuata associativamente, non da un indice presente nell'indirizzo.

### **5. Il problema della sostituzione**

La libertà di collocazione introduce una scelta che il mapping diretto non aveva: se la cache è piena, quale linea deve essere espulsa?

Il PDF propone **LRU**, *Least Recently Used*:

> si sostituisce il blocco che non viene usato da più tempo, perché statisticamente è il meno probabile candidato al riuso immediato.

Il PDF scrive “Last Recently Used”; la denominazione tecnica corretta è **Least Recently Used**.

Una realizzazione concettuale associa a ogni linea un contatore di età:

- all'accesso della linea, il suo contatore viene azzerato;
- gli altri vengono incrementati fino alla saturazione;
- sul miss si sceglie una linea con contatore massimo.

Se più contatori sono saturi, il circuito deve applicare una regola deterministica aggiuntiva. Nelle implementazioni reali sono comuni anche approssimazioni di LRU, meno costose dell'ordinamento esatto di tutte le linee.

### **6. Miss e caricamento**

Se nessun tag coincide con `NBMdL`:

1. si cerca associativamente il contatore di età massimo;
2. la linea individuata diventa `NBMC`;
3. il blocco `MdL[NBMdL]` viene copiato in `MC[NBMC]`;
4. `tag[NBMC] ← NBMdL`;
5. la parola `MC[NBMC,NP]` viene fornita alla CPU;
6. si aggiornano i contatori e si azzera quello della linea appena caricata.

Questa sequenza richiede sia una CAM per i tag sia la logica necessaria alla politica di rimpiazzo.

### **7. Pregi e costi della fully associative**

| Aspetto | Conseguenza |
| --- | --- |
| Mapping libero | elimina i conflitti dovuti a una posizione obbligata |
| Ottimizzazione globale | tutte le linee possono essere sfruttate dai blocchi più recenti |
| Tag più largo | deve identificare l'intero blocco di MdL |
| Ricerca parallela | richiede un comparatore per ogni linea |
| LRU | richiede metadati e logica di aggiornamento/sostituzione |

La politica usa meglio la capacità disponibile, ma è complessa e costosa, soprattutto al crescere del numero di linee.

### **8. Cache Set Associative**

La politica **Set Associative** è il compromesso fra mapping diretto e fully associative. Le linee della cache sono suddivise in insiemi, detti **set**. Ogni blocco di MdL:

- viene associato a un solo set;
- può occupare qualunque linea, o **way**, all'interno di quel set.

Una cache a $n$ vie è detta **$n$-way set associative**:

| Associatività | Posizioni possibili per ogni blocco |
| ---: | ---: |
| 2-way | 2 linee nello stesso set |
| 4-way | 4 linee nello stesso set |
| 8-way | 8 linee nello stesso set |

Se la cache contiene $L$ linee e ha $n$ vie:

$$
N_{set}=\frac{L}{n},
$$

$$
set(b)=b\bmod N_{set}.
$$

L'indirizzo viene diviso in tag, indice del set e offset. Il tag viene confrontato in parallelo soltanto con le $n$ vie del set selezionato.

### **9. I casi limite**

Le tre politiche appartengono alla stessa famiglia:

- $n=1$: cache direct-mapped/Tag Associative;
- $1<n<L$: cache set-associative;
- $n=L$: cache fully associative con un unico set.

All'aumentare di $n$:

- diminuiscono i conflict miss;
- aumenta il numero di confronti paralleli;
- cresce la complessità della scelta LRU all'interno del set;
- può aumentare il tempo e il costo dell'accesso.

### **10. Esempio di mapping a 4 vie**

Con 128 linee e 4 vie:

$$
N_{set}=128/4=32.
$$

I blocchi 3, 35, 67 e 99 hanno tutti indice di set 3, ma possono convivere nelle quattro vie del set. Un quinto blocco con lo stesso indice richiede la sostituzione di una delle quattro linee, per esempio secondo LRU.

Il confronto non coinvolge tutte le 128 linee, ma soltanto i quattro tag del set 3.

> ✅ Fully associative massimizza la libertà di collocazione al prezzo di una CAM estesa e di una politica di sostituzione globale. Set associative limita la ricerca a poche vie e offre il compromesso normalmente desiderato fra conflitti, velocità e costo hardware.
