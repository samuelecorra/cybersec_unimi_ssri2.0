# **M3 UD2 Lezione 5 - Valutazione dell'efficienza e delle prestazioni**

---

### **1. Introduzione**

Questa lezione approfondisce la valutazione del file system da due punti di vista:

- **efficienza**, cioe' uso ottimale delle risorse;
- **prestazioni**, cioe' uso rapido delle risorse.

L'obiettivo e' capire quali scelte di progettazione permettono di ridurre gli sprechi di spazio, diminuire i tempi di accesso e migliorare la qualita' complessiva della gestione dei file.

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

Uno dei problemi principali per l'efficienza e' la frammentazione.

#### **3.1. Frammentazione interna**

La **frammentazione interna** e' lo spazio non usato all'interno di un blocco gia' assegnato a un file.

Se un file non riempie completamente l'ultimo blocco allocato, la parte rimanente del blocco resta inutilizzata.

In generale:

$$
\text{frammentazione interna} = \text{spazio allocato} - \text{spazio effettivamente usato}
$$

#### **3.2. Frammentazione esterna**

La **frammentazione esterna** e' lo spazio libero che esiste sul disco ma non e' facilmente utilizzabile a causa della disposizione dei blocchi gia' allocati.

Questo problema e' particolarmente evidente nelle tecniche che richiedono blocchi contigui.

> ⚠️ La frammentazione riduce il rendimento dello spazio su disco e puo' rendere piu' costose le operazioni di allocazione.

---

### **4. Tecniche per migliorare l'efficienza**

Per migliorare l'efficienza si possono adottare scelte progettuali mirate.

#### **4.1. Cluster di dimensione variabile**

L'uso di cluster di dimensione diversa permette di adattare l'unita' di allocazione alla dimensione effettiva dei file.

Cluster piu' piccoli riducono la frammentazione interna per file piccoli.

Cluster piu' grandi possono essere convenienti per file grandi e accessi sequenziali, perche' riducono il numero di riferimenti da gestire.

#### **4.2. Dimensionamento di blocchi, puntatori e metadati**

La dimensione dei blocchi, dei puntatori e dei metadati deve essere scelta in funzione di:

- tecnologia del disco;
- dimensione totale del supporto;
- numero atteso di file;
- dimensione media dei file;
- tipo di carico di lavoro;
- modalita' prevalente di accesso.

Per esempio, in una File Allocation Table il numero di bit usati per identificare un blocco deve essere adeguato alla dimensione del disco.

Se i numeri di blocco sono troppo piccoli, non si riesce a indirizzare tutto il disco.

Se sono troppo grandi, si spreca spazio nelle strutture di gestione.

#### **4.3. Allocazione statica e dinamica delle tabelle**

Le tabelle di gestione possono essere allocate:

- **staticamente**, riservando spazio fisso;
- **dinamicamente**, adattando lo spazio alle esigenze effettive.

L'allocazione statica e' semplice e rapida, ma puo' sprecare spazio.

L'allocazione dinamica e' piu' flessibile, ma richiede una gestione piu' complessa.

> 💡 L'efficienza migliora quando le strutture di gestione sono dimensionate sul modo reale in cui il file system verra' usato.

---

### **5. Prestazioni del file system**

Le **prestazioni** riguardano la rapidita' con cui il file system usa le risorse.

Dipendono da:

- algoritmi di gestione;
- strutture dati usate per reperire i file e i blocchi;
- supporti hardware disponibili;
- memoria centrale usata per cache e buffer;
- numero di copie dei dati tra livelli diversi;
- modalita' sincrona o asincrona delle operazioni.

Un file system con buone prestazioni deve ridurre il tempo speso nella gestione e lasciare piu' tempo all'esecuzione utile dei processi.

---

### **6. Algoritmi e strutture dati semplici**

Una prima tecnica per migliorare le prestazioni e' usare algoritmi semplici ed efficienti.

Se la gestione del file system richiede troppo tempo di elaborazione, il processore viene impegnato nella gestione anziche' nell'esecuzione dei processi applicativi.

Le strutture dati devono quindi permettere accessi rapidi:

- tabelle indicizzate;
- strutture ad albero;
- cache di descrittori;
- bitmap efficienti;
- indici dei blocchi.

> ✅ Una struttura dati piu' adatta al tipo di accesso puo' ridurre drasticamente il tempo necessario a reperire metadati e blocchi.

---

### **7. Cache del disco**

Un supporto hardware importante e' la **cache a bordo del disco**.

Il disco puo' conservare in una propria memoria interna i blocchi letti o scritti piu' recentemente.

Se una richiesta successiva riguarda un blocco gia' presente nella cache del disco:

- non serve accedere fisicamente al piatto del disco;
- si riducono gli spostamenti della testina;
- si riduce la latenza di rotazione;
- il tempo di accesso diminuisce.

Questa tecnica e' utile quando i blocchi usati piu' recentemente sono anche quelli usati piu' frequentemente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Leggere dalla cache del disco e' molto piu' rapido che posizionare fisicamente le testine e attendere la rotazione del disco.

---

### **8. Cache di pagina e buffer cache**

Il sistema operativo puo' mantenere in memoria centrale una **cache di pagina** per conservare informazioni provenienti dal disco o dirette verso il disco.

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

Per ridurre le copie, il sistema operativo puo' usare una **cache unificata**.

Invece di mantenere:

- una cache per il disco;
- una cache per i buffer del file system;
- una cache per le pagine usate dai processi;

si mantiene una sola copia dei dati in memoria centrale.

In lettura, i dati vengono scritti una volta dal gestore della periferica e poi resi disponibili al processo.

In scrittura, i dati scritti dal processo possono essere usati direttamente dal sistema operativo per completare l'operazione sul disco.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ✅ La cache unificata evita duplicazioni inutili e riduce i tempi di copiatura tra aree di memoria diverse.

---

### **10. Gestione della cache**

La cache deve essere gestita con politiche di sostituzione.

Una tecnica classica e' **LRU**:

$$
\text{LRU} = \text{Least Recently Used}
$$

cioe' viene eliminata dalla cache l'informazione usata meno recentemente.

Si possono anche usare priorita' di paginazione:

- metadati importanti possono avere priorita' maggiore;
- blocchi di file usati frequentemente possono restare piu' a lungo;
- dati temporanei o gia' consumati possono essere rimossi prima.

> 💡 La cache e' efficace solo se contiene informazioni che verranno riusate prima di essere eliminate.

---

### **11. I/O mediato da cache**

Le tecniche di ingresso/uscita mediate da cache permettono di ridurre gli accessi fisici al disco.

Il sistema puo':

- accumulare richieste;
- riordinare accessi per ridurre gli spostamenti della testina;
- soddisfare richieste direttamente dalla cache;
- anticipare letture future.

Questo riduce il tempo medio di accesso e aumenta il throughput del file system.

---

### **12. Scritture asincrone**

Le **scritture asincrone** permettono al processo di continuare l'esecuzione senza attendere il completamento fisico della scrittura su disco.

Il processo scrive in un buffer del sistema operativo e considera conclusa l'operazione.

Successivamente, il sistema operativo completa la scrittura sul disco quando e' opportuno.

Questo consente di:

- raggruppare piu' scritture;
- scegliere un ordine piu' efficiente;
- ridurre il tempo di attesa del processo;
- mantenere piu' fluida l'esecuzione.

> ⚠️ La scrittura asincrona migliora le prestazioni, ma richiede attenzione alla coerenza in caso di guasto prima del salvataggio effettivo.

---

### **13. Free-behind e read-ahead**

Oltre a LRU, si possono usare tecniche specializzate.

#### **13.1. Free-behind**

Con il **free-behind**, un'informazione viene rimossa dalla cache subito dopo essere stata usata.

Questa tecnica e' utile quando si prevede che il dato appena letto non verra' piu' riusato, per esempio in una scansione sequenziale di un file molto grande.

#### **13.2. Read-ahead**

Con il **read-ahead**, il sistema legge anticipatamente dal disco blocchi successivi a quelli richiesti.

Se il processo sta leggendo sequenzialmente, e' probabile che richieda presto i blocchi immediatamente successivi.

Il sistema li porta quindi in memoria centrale prima della richiesta esplicita.

> 📌 Il read-ahead riduce i tempi di attesa futuri; il free-behind libera subito spazio quando il riuso e' improbabile.

---

### **14. Memoria virtuale unificata e RAM disk**

Un'altra tecnica per migliorare le prestazioni e' realizzare una **memoria virtuale unificata** che includa non solo la memoria centrale, ma anche la memoria di massa.

In questo modello, le informazioni vengono gestite in uno spazio astratto uniforme, riducendo la necessita' di passare esplicitamente tra buffer separati.

Una realizzazione particolare e' il **RAM disk**.

Un RAM disk usa una porzione di memoria centrale come se fosse un disco.

Questo offre tempi di accesso molto bassi, perche' le operazioni avvengono in RAM.

Il limite e' che:

- la dimensione e' normalmente ridotta;
- i dati sono volatili se non vengono salvati altrove;
- la memoria usata dal RAM disk non e' disponibile per altri scopi.

Il RAM disk e' utile quando bisogna gestire un numero limitato di file di dimensioni limitate con accesso molto rapido.

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

Le prestazioni mirano a ridurre i tempi di accesso e a rendere piu' rapido l'uso delle informazioni.

Le tecniche piu' importanti sono il dimensionamento accurato delle strutture, la riduzione della frammentazione, l'uso intelligente delle cache, le scritture asincrone, il read-ahead, il free-behind e, quando opportuno, una memoria virtuale unificata o un RAM disk.
