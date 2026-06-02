# **M3 UD2 Lezione 5 - Valutazione dell'efficienza e delle prestazioni**

---

### **1. Introduzione**

Questa lezione approfondisce la valutazione del file system da due punti di vista:

- **efficienza**, cioè uso ottimale delle risorse;
- **prestazioni**, cioè uso rapido delle risorse.

L'obiettivo è capire quali scelte di progettazione permettono di ridurre gli sprechi di spazio, diminuire i tempi di accesso e migliorare la qualità complessiva della gestione dei file.

> 📌 Efficienza e prestazioni sono collegate, ma non coincidono: la prima riguarda quanto bene si usano le risorse, la seconda quanto rapidamente vengono usate.

---

### **2. Efficienza del file system**

L'**efficienza** riguarda lo sfruttamento ottimale delle risorse del file system.

Gli aspetti principali sono:

- dimensionamento dei blocchi;
- dimensionamento dei puntatori;
- organizzazione dei metadati;
- rendimento dello spazio occupato sul disco;
- minimizzazione delle aree dedicate alle informazioni di gestione.

Un file system efficiente deve usare lo spazio disponibile evitando sprechi e strutture di controllo eccessivamente pesanti.

---

### **3. Frammentazione e uso dello spazio**

Uno dei problemi principali per l'efficienza è la frammentazione.

#### **3.1. Frammentazione interna**

La **frammentazione interna** è lo spazio non usato all'interno di un blocco già assegnato a un file.

Se un file non riempie completamente l'ultimo blocco allocato, la parte rimanente del blocco resta inutilizzata.

In generale:

$$
\text{frammentazione interna} = \text{spazio allocato} - \text{spazio effettivamente usato}
$$

#### **3.2. Frammentazione esterna**

La **frammentazione esterna** è lo spazio libero che esiste sul disco ma non è facilmente utilizzabile a causa della disposizione dei blocchi già allocati.

Questo problema è particolarmente evidente nelle tecniche che richiedono blocchi contigui.

> ⚠️ La frammentazione riduce il rendimento dello spazio su disco e può rendere più costose le operazioni di allocazione.

---

### **4. Tecniche per migliorare l'efficienza**

Per migliorare l'efficienza si possono adottare scelte progettuali mirate.

#### **4.1. Cluster di dimensione variabile**

L'uso di cluster di dimensione diversa permette di adattare l'unità di allocazione alla dimensione effettiva dei file.

Cluster più piccoli riducono la frammentazione interna per file piccoli.

Cluster più grandi possono essere convenienti per file grandi e accessi sequenziali, perchè riducono il numero di riferimenti da gestire.

#### **4.2. Dimensionamento di blocchi, puntatori e metadati**

La dimensione dei blocchi, dei puntatori e dei metadati deve essere scelta in funzione di:

- tecnologia del disco;
- dimensione totale del supporto;
- numero atteso di file;
- dimensione media dei file;
- tipo di carico di lavoro;
- modalità prevalente di accesso.

Per esempio, in una File Allocation Table il numero di bit usati per identificare un blocco deve essere adeguato alla dimensione del disco.

Se i numeri di blocco sono troppo piccoli, non si riesce a indirizzare tutto il disco.

Se sono troppo grandi, si spreca spazio nelle strutture di gestione.

#### **4.3. Allocazione statica e dinamica delle tabelle**

Le tabelle di gestione possono essere allocate:

- **staticamente**, riservando spazio fisso;
- **dinamicamente**, adattando lo spazio alle esigenze effettive.

L'allocazione statica è semplice e rapida, ma può sprecare spazio.

L'allocazione dinamica è più flessibile, ma richiede una gestione più complessa.

> 💡 L'efficienza migliora quando le strutture di gestione sono dimensionate sul modo reale in cui il file system verrà usato.

---

### **5. Prestazioni del file system**

Le **prestazioni** riguardano la rapidità con cui il file system usa le risorse.

Dipendono da:

- algoritmi di gestione;
- strutture dati usate per reperire i file e i blocchi;
- supporti hardware disponibili;
- memoria centrale usata per cache e buffer;
- numero di copie dei dati tra livelli diversi;
- modalità sincrona o asincrona delle operazioni.

Un file system con buone prestazioni deve ridurre il tempo speso nella gestione e lasciare più tempo all'esecuzione utile dei processi.

---

### **6. Algoritmi e strutture dati semplici**

Una prima tecnica per migliorare le prestazioni è usare algoritmi semplici ed efficienti.

Se la gestione del file system richiede troppo tempo di elaborazione, il processore viene impegnato nella gestione anziché nell'esecuzione dei processi applicativi.

Le strutture dati devono quindi permettere accessi rapidi:

- tabelle indicizzate;
- strutture ad albero;
- cache di descrittori;
- bitmap efficienti;
- indici dei blocchi.

> ✅ Una struttura dati più adatta al tipo di accesso può ridurre drasticamente il tempo necessario a reperire metadati e blocchi.

---

### **7. Cache del disco**

Un supporto hardware importante è la **cache a bordo del disco**.

Il disco può conservare in una propria memoria interna i blocchi letti o scritti più recentemente.

Se una richiesta successiva riguarda un blocco già presente nella cache del disco:

- non serve accedere fisicamente al piatto del disco;
- si riducono gli spostamenti della testina;
- si riduce la latenza di rotazione;
- il tempo di accesso diminuisce.

Questa tecnica è utile quando i blocchi usati più recentemente sono anche quelli usati più frequentemente.

![](imgs/Pasted%20image%2020260601220944.png)

> 📌 Leggere dalla cache del disco è molto più rapido che posizionare fisicamente le testine e attendere la rotazione del disco.

---

### **8. Cache di pagina e buffer cache**

Il sistema operativo può mantenere in memoria centrale una **cache di pagina** per conservare informazioni provenienti dal disco o dirette verso il disco.

Durante una lettura:

1. il disco fornisce i dati;
2. i dati vengono portati in una cache di pagina;
3. il file system alimenta il buffer usato dal processo;
4. il processo riceve i dati richiesti.

Durante una scrittura:

1. il processo scrive nel buffer del sistema operativo;
2. il buffer alimenta la cache di pagina;
3. il sistema operativo scrive poi i dati su disco.

Se cache di pagina e buffer cache sono separate, possono verificarsi copie aggiuntive dei dati tra le due aree.

> ⚠️ Ogni copiatura in memoria ha un costo: ridurre il numero di copie migliora le prestazioni complessive.

---

### **9. Cache unificata**

Per ridurre le copie, il sistema operativo può usare una **cache unificata**.

Invece di mantenere:

- una cache per il disco;
- una cache per i buffer del file system;
- una cache per le pagine usate dai processi;

si mantiene una sola copia dei dati in memoria centrale.

In lettura, i dati vengono scritti una volta dal gestore della periferica e poi resi disponibili al processo.

In scrittura, i dati scritti dal processo possono essere usati direttamente dal sistema operativo per completare l'operazione sul disco.

![](imgs/Pasted%20image%2020260601221134.png)

> ✅ La cache unificata evita duplicazioni inutili e riduce i tempi di copiatura tra aree di memoria diverse.

---

### **10. Gestione della cache**

La cache deve essere gestita con politiche di sostituzione.

Una tecnica classica è **LRU**:

$$
\text{LRU} = \text{Least Recently Used}
$$

cioè viene eliminata dalla cache l'informazione usata meno recentemente.

Si possono anche usare priorità di paginazione:

- metadati importanti possono avere priorità maggiore;
- blocchi di file usati frequentemente possono restare più a lungo;
- dati temporanei o già consumati possono essere rimossi prima.

> 💡 La cache è efficace solo se contiene informazioni che verranno riusate prima di essere eliminate.

---

### **11. I/O mediato da cache**

Le tecniche di ingresso/uscita mediate da cache permettono di ridurre gli accessi fisici al disco.

Il sistema può:

- accumulare richieste;
- riordinare accessi per ridurre gli spostamenti della testina;
- soddisfare richieste direttamente dalla cache;
- anticipare letture future.

Questo riduce il tempo medio di accesso e aumenta il throughput del file system.

---

### **12. Scritture asincrone**

Le **scritture asincrone** permettono al processo di continuare l'esecuzione senza attendere il completamento fisico della scrittura su disco.

Il processo scrive in un buffer del sistema operativo e considera conclusa l'operazione.

Successivamente, il sistema operativo completa la scrittura sul disco quando è opportuno.

Questo consente di:

- raggruppare più scritture;
- scegliere un ordine più efficiente;
- ridurre il tempo di attesa del processo;
- mantenere più fluida l'esecuzione.

> ⚠️ La scrittura asincrona migliora le prestazioni, ma richiede attenzione alla coerenza in caso di guasto prima del salvataggio effettivo.

---

### **13. Free-behind e read-ahead**

Oltre a LRU, si possono usare tecniche specializzate.

#### **13.1. Free-behind**

Con il **free-behind**, un'informazione viene rimossa dalla cache subito dopo essere stata usata.

Questa tecnica è utile quando si prevede che il dato appena letto non verrà più riusato, per esempio in una scansione sequenziale di un file molto grande.

#### **13.2. Read-ahead**

Con il **read-ahead**, il sistema legge anticipatamente dal disco blocchi successivi a quelli richiesti.

Se il processo sta leggendo sequenzialmente, è probabile che richieda presto i blocchi immediatamente successivi.

Il sistema li porta quindi in memoria centrale prima della richiesta esplicita.

> 📌 Il read-ahead riduce i tempi di attesa futuri; il free-behind libera subito spazio quando il riuso è improbabile.

---

### **14. Memoria virtuale unificata e RAM disk**

Un'altra tecnica per migliorare le prestazioni è realizzare una **memoria virtuale unificata** che includa non solo la memoria centrale, ma anche la memoria di massa.

In questo modello, le informazioni vengono gestite in uno spazio astratto uniforme, riducendo la necessità di passare esplicitamente tra buffer separati.

Una realizzazione particolare è il **RAM disk**.

Un RAM disk usa una porzione di memoria centrale come se fosse un disco.

Questo offre tempi di accesso molto bassi, perchè le operazioni avvengono in RAM.

Il limite è che:

- la dimensione è normalmente ridotta;
- i dati sono volatili se non vengono salvati altrove;
- la memoria usata dal RAM disk non è disponibile per altri scopi.

Il RAM disk è utile quando bisogna gestire un numero limitato di file di dimensioni limitate con accesso molto rapido.

---

### **15. Sintesi**

| Aspetto | Obiettivo | Tecniche principali |
|---|---|---|
| **Efficienza** | Sfruttare bene spazio e strutture | Blocchi e cluster ben dimensionati, puntatori adeguati, metadati proporzionati |
| **Frammentazione** | Ridurre sprechi di spazio | Cluster variabili, allocazione adatta al carico, gestione accurata dello spazio libero |
| **Prestazioni** | Ridurre i tempi di accesso | Algoritmi semplici, strutture dati efficienti, caching |
| **Cache** | Evitare accessi fisici inutili | Cache disco, page cache, cache unificata, LRU |
| **I/O** | Ridurre attese e movimenti del disco | Scritture asincrone, read-ahead, free-behind, riordino richieste |
| **Memoria virtuale unificata** | Ridurre copie e accelerare accessi | Cache unificata, RAM disk |

---

### **16. Conclusione**

La valutazione di un file system richiede di distinguere tra efficienza e prestazioni.

L'efficienza mira a usare bene lo spazio e a ridurre lo spreco di risorse.

Le prestazioni mirano a ridurre i tempi di accesso e a rendere più rapido l'uso delle informazioni.

Le tecniche più importanti sono il dimensionamento accurato delle strutture, la riduzione della frammentazione, l'uso intelligente delle cache, le scritture asincrone, il read-ahead, il free-behind e, quando opportuno, una memoria virtuale unificata o un RAM disk.
