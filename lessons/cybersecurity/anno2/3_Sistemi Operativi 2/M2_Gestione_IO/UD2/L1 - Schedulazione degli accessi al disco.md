# **M2 UD2 Lezione 1 - Schedulazione degli accessi al disco**

---

### **1. Introduzione**

In questa unità viene affrontato lo studio della gestione delle **memorie di massa**, una componente fondamentale dei sistemi di elaborazione.

Una gestione efficiente delle memorie di massa è essenziale perché su di essa si basano anche la virtualizzazione del processore e la virtualizzazione della memoria centrale: swapping, caricamento delle pagine e accesso persistente ai dati dipendono dalle prestazioni del sottosistema disco.

Questa lezione si concentra sulla **schedulazione degli accessi al disco**, cioè sull'ordinamento delle richieste di lettura e scrittura per migliorare le prestazioni.

Gli aspetti principali sono:

- tempo di accesso al disco;
- larghezza di banda;
- algoritmi di schedulazione degli accessi;
- scelta dell'algoritmo più adatto al carico di lavoro.

> 📌 La schedulazione del disco serve a ordinare le richieste in modo da ridurre i movimenti inutili della testina e aumentare la quantità di dati trasferiti nel tempo.

---

### **2. Tempo di accesso al disco**

Il **tempo di accesso** (*Access Time*) è il tempo necessario per effettuare un'operazione di accesso ai dati posti sul disco.

È composto da:

- **tempo di ricerca** (*seek time*), cioè il tempo necessario per individuare la posizione dei dati spostando le testine sul cilindro corretto;
- **latenza di rotazione**, cioè il tempo necessario affinché i dati si presentino sotto le testine di lettura/scrittura;
- **tempo di trasferimento**, cioè il tempo necessario a leggere o scrivere effettivamente i dati dopo il posizionamento.

$$
T_{accesso} = T_{seek} + T_{rotazione} + T_{trasferimento}
$$

> ⚠️ Nei dischi magnetici il tempo di accesso non dipende solo dalla quantità di dati, ma anche dalla posizione fisica dei dati sul disco.

---

### **3. Larghezza di banda**

La **larghezza di banda** (*bandwidth*) misura la capacità di trasferimento dei dati verso il disco o dal disco.

È definita come:

$$
\text{Bandwidth} = \frac{\text{numero di byte trasferiti}}{\text{tempo totale di trasferimento}}
$$

Una gestione efficiente del disco ha due obiettivi complementari:

- ridurre il tempo totale di accesso, diminuendo la latenza per ottenere un dato;
- aumentare la larghezza di banda, massimizzando la quantità di dati trasferiti tra calcolatore e disco.

Le tecniche di schedulazione aiutano a migliorare entrambi i parametri ordinando opportunamente le richieste pendenti.

---

### **4. FCFS**

La politica **FCFS** (*First Come, First Served*) serve le richieste nell'ordine in cui arrivano.

Le richieste vengono mantenute in una coda di attesa e viene sempre servita la richiesta più vecchia.

#### **4.1. Esempio**

Se la testina parte dal cilindro 53 e le richieste sono distribuite su cilindri distanti, FCFS può costringere la testina a muoversi avanti e indietro seguendo un andamento a zig-zag.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nell'esempio discusso a lezione, il movimento complessivo della testina arriva a **640 cilindri**, perché l'ordine di arrivo non tiene conto della posizione fisica delle richieste.

#### **4.2. Valutazione**

Vantaggi:

- è molto semplice da implementare;
- è equa rispetto all'ordine temporale di arrivo.

Svantaggi:

- non ottimizza il movimento della testina;
- può produrre tempi di accesso elevati;
- può ridurre la larghezza di banda effettiva.

> ⚠️ FCFS è corretto dal punto di vista dell'ordine di arrivo, ma può essere pessimo dal punto di vista fisico.

---

### **5. SSTF**

La politica **SSTF** (*Shortest Seek Time First*) serve per prima la richiesta che richiede il più breve tempo di ricerca a partire dalla posizione corrente della testina.

In altre parole, viene scelta la richiesta più vicina alla posizione corrente.

#### **5.1. Esempio**

Seguendo questa strategia, la testina riduce gli spostamenti perché si dirige sempre verso la richiesta più vicina tra quelle pendenti.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nell'esempio discusso, il movimento complessivo della testina scende a **236 cilindri**, con un risparmio considerevole rispetto ai **640 cilindri** di FCFS.

#### **5.2. Valutazione**

Vantaggi:

- riduce il tempo medio di ricerca;
- migliora la larghezza di banda rispetto a FCFS.

Svantaggi:

- richiede di confrontare le richieste pendenti per trovare quella più vicina;
- può produrre starvation per richieste lontane se continuano ad arrivare richieste vicine alla posizione corrente.

> 📌 SSTF ottimizza localmente il movimento della testina, ma non garantisce tempi di attesa uniformi per tutte le richieste.

---

### **6. SCAN**

L'algoritmo **SCAN**, detto anche **algoritmo dell'ascensore**, mantiene ordinate le richieste e muove la testina in una direzione servendo le richieste incontrate lungo il percorso.

Quando la testina arriva a un estremo del disco, inverte la direzione e continua a servire le richieste nel verso opposto.

#### **6.1. Esempio**

Supponiamo che la testina si trovi al cilindro 53 e si stia muovendo verso il cilindro 0. L'algoritmo serve le richieste incontrate andando verso 0, poi inverte la direzione e serve le richieste andando verso il cilindro massimo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nell'esempio discusso, il movimento complessivo è di **236 cilindri**. Il vantaggio è che non occorre ricalcolare ogni volta quale richiesta sia più vicina: la direzione di scansione determina l'ordine.

#### **6.2. Valutazione**

Vantaggi:

- evita confronti locali continui tra tutte le richieste pendenti;
- fornisce un comportamento più regolare di SSTF;
- riduce i movimenti disordinati della testina rispetto a FCFS.

Svantaggi:

- arrivando vicino a un estremo, l'inversione può favorire richieste in zone appena attraversate;
- le richieste vicino all'estremo opposto possono attendere più a lungo.

> 💡 SCAN assomiglia a un ascensore: serve le richieste lungo una direzione, poi cambia verso.

---

### **7. C-SCAN**

La politica **C-SCAN** (*Circular SCAN*) è una variante di SCAN pensata per rendere più uniforme il tempo di attesa delle richieste.

La testina serve le richieste muovendosi in una sola direzione. Quando arriva a un estremo del disco, torna direttamente all'estremo opposto senza servire richieste durante il ritorno.

#### **7.1. Motivazione**

In SCAN, dopo l'inversione vicino a un estremo, la testina torna subito a servire zone appena visitate. È invece probabile che le richieste rimaste pendenti siano più vicine all'altro estremo, che non viene visitato da più tempo.

C-SCAN evita questa asimmetria trattando il disco come se fosse scandito circolarmente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Valutazione**

Vantaggi:

- tempi di attesa più uniformi;
- comportamento più prevedibile.

Svantaggi:

- il salto da un estremo all'altro costa tempo;
- durante il ritorno non vengono servite richieste.

> ⚠️ C-SCAN migliora l'uniformità dell'attesa, ma paga un costo per il riposizionamento non produttivo della testina.

---

### **8. LOOK e C-LOOK**

Un miglioramento di SCAN e C-SCAN consiste nell'evitare di arrivare necessariamente fino al cilindro 0 o fino al cilindro massimo.

#### **8.1. LOOK**

Con **LOOK**, la testina si muove come in SCAN, ma inverte direzione quando raggiunge l'ultima richiesta pendente nella direzione corrente, senza proseguire fino all'estremo fisico del disco.

#### **8.2. C-LOOK**

Con **C-LOOK**, la testina si muove come in C-SCAN, ma il salto circolare avviene dall'ultima richiesta in una direzione alla prima richiesta nell'altra direzione, senza raggiungere gli estremi fisici del disco.

Queste varianti risparmiano il tempo necessario per spostare la testina fino a zone del disco in cui non ci sono richieste pendenti.

> ✅ LOOK e C-LOOK conservano l'idea di scansione ordinata, ma eliminano movimenti inutili verso estremi non richiesti.

---

### **9. Selezione dell'algoritmo**

La scelta dell'algoritmo di schedulazione è critica per garantire buone prestazioni nella gestione dei dischi.

Le prestazioni dipendono da:

- numero delle richieste;
- tipo delle richieste;
- distribuzione delle richieste sui cilindri;
- metodo di allocazione dei blocchi ai file;
- tipo di applicazioni eseguite;
- caratteristiche del disco.

Il driver device independent per il disco deve farsi carico di una gestione adatta al tipo di disco e al tipo di carico.

È opportuno che l'algoritmo di schedulazione sia implementato in un modulo separato e facilmente sostituibile, così da poter adattare il sistema se cambiano le esigenze.

Come scelte predefinite, algoritmi come **SSTF** e **LOOK** rappresentano spesso buoni compromessi.

---

### **10. Sintesi finale**

> ✅ La schedulazione degli accessi al disco migliora tempo di accesso e larghezza di banda ordinando le richieste in base alla posizione fisica dei dati.

In questa lezione sono stati analizzati:

- **tempo di accesso**, composto da seek time, latenza di rotazione e trasferimento;
- **larghezza di banda**, calcolata come byte trasferiti su tempo totale;
- **FCFS**, semplice ma inefficiente nei movimenti;
- **SSTF**, efficiente localmente ma con possibile starvation;
- **SCAN**, basato su scansione bidirezionale;
- **C-SCAN**, basato su scansione circolare per tempi più uniformi;
- **LOOK** e **C-LOOK**, che evitano movimenti inutili fino agli estremi del disco;
- criteri per scegliere un algoritmo modulare e adattabile.

L'obiettivo complessivo è ridurre la latenza degli accessi e massimizzare il throughput del sottosistema di memorie di massa.
