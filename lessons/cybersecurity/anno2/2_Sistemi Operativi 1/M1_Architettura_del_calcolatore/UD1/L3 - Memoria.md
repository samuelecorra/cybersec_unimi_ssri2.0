# **M1 UD1 Lezione 3 - Memoria**

---

### **1. Introduzione**

#### **1.1. Il ruolo della memoria nel sistema di elaborazione**

La **memoria** è l’elemento fondamentale che consente a un sistema di elaborazione di **immagazzinare dati e istruzioni**.  
Senza memoria, un processore non potrebbe mantenere né i programmi da eseguire né i risultati intermedi delle operazioni.

Nel modello della **macchina di von Neumann**, la memoria è concepita come un’area unica in cui convivono **codice** e **dati**, accessibili attraverso indirizzi numerici.  
Tuttavia, nella realtà fisica, le memorie sono organizzate in **una gerarchia di livelli**, ognuno con caratteristiche diverse in termini di **velocità, capacità e costo**.

![](imgs/Memory-Hierarchy-Design.webp)

---

### **2. Tipologie di memoria**

#### **2.1. Registri del processore**

I **registri** sono le memorie più piccole e più veloci dell’intero sistema.  
Si trovano **all’interno della CPU** e consentono un accesso **diretto e immediato**.  
Servono per conservare:

- risultati temporanei delle operazioni,
- indirizzi di memoria,
- contatori di programma,
- e variabili di stato (flag, puntatori, ecc.).

La loro capacità è **estremamente ridotta** (pochi byte o word), ma il **tempo di accesso** è dell’ordine di pochi **nanosecondi** o meno.

---

#### **2.2. Memoria cache**

La **memoria cache** è un livello intermedio tra i registri e la memoria centrale.  
È costituita da celle ad altissima velocità che **conservano copie dei dati più frequentemente usati** dalla CPU, in modo da ridurre il tempo medio di accesso.

Quando la CPU cerca un dato:

- se questo è già presente nella cache (**cache hit**), il recupero è rapidissimo;
- se non è presente (**cache miss**), il sistema lo recupera dalla memoria centrale e lo copia nella cache.

Il principio su cui si basa è quello di **località**, che può essere:

- **temporale:** un dato usato di recente sarà probabilmente riutilizzato presto;
- **spaziale:** i dati vicini a un indirizzo usato tendono anch’essi a essere richiesti.

La cache, quindi, migliora drasticamente le prestazioni globali del sistema.

Ci sono principalmente due tipi di cache:

- **Cache on-chip:** integrata direttamente nella CPU, con tempi di accesso minimi.
- **Cache off-chip:** posizionata esternamente alla CPU, con tempi di accesso leggermente superiori ma capacità maggiore.

Dipende dal sistema operativo e dall’architettura del processore come viene gestita la cache, ma in generale è trasparente all’utente e al software, che si limitano a leggere e scrivere dati senza preoccuparsi di dove siano fisicamente memorizzati.

In ogni caso, sia esterna che interna hanno una velocità di accesso di ALMENO un ordine di grandezza superiore alla memoria centrale, rendendole essenziali per le prestazioni del sistema.

---

#### **2.3. Memoria centrale**

La **memoria centrale** (o **RAM**) è la principale area di lavoro del calcolatore.  
Contiene i **programmi in esecuzione** e i **dati attivi** elaborati dal processore.

Le sue caratteristiche principali sono:

- **Accesso diretto:** ogni cella è identificabile tramite un indirizzo.
- **Tempo di accesso rapido**, ma inferiore a quello della cache.
- **Capacità maggiore** rispetto ai registri o alla cache.
- **Volatilità:** i dati si perdono quando il sistema viene spento.

È la memoria su cui opera direttamente il sistema operativo per gestire i **processi in esecuzione**, le **pagine di memoria virtuale** e gli **spazi di indirizzamento dei programmi**.

---

#### **2.4. Memorie di massa**

Le **memorie di massa** sono dispositivi permanenti che consentono di memorizzare grandi quantità di dati anche dopo lo spegnimento del sistema.  
Pur avendo tempi di accesso molto più lunghi, sono indispensabili per la persistenza dell’informazione. Coinvolgono tempi di attesa elettronici ma anche meccanici (movimento di testine, rotazione dei dischi, ecc.).

##### **a) Dischi magnetici**

- Usati per la **memoria secondaria** e per i **file system**.

I dischi magnetici sono componenti costituiti da **piatti metallici** ricoperti di materiale ferromagnetico. La direzione del campo magnetico di questo materiale viene **polarizzata** dal campo elettromagnetico generato dalle **testine di scrittura**; successivamente, la polarizzazione viene riletta dalle **testine di lettura**, permettendo così di memorizzare zeri e uni, rappresentati da polarizzazioni in una direzione o in quella opposta.

Posizionando le testine sui piatti è possibile scrivere l'informazione su più **tracce**, aumentando la quantità di dati memorizzabili su ciascun piatto.

###### **Tempo di accesso e capacità**

Il **tempo di accesso** è almeno uno o due ordini di grandezza superiore rispetto a quello della memoria centrale, poiché sono coinvolti sia tempi elettromagnetici sia tempi **elettromeccanici**: in particolare, i dischi devono ruotare fino a posizionarsi in corrispondenza dell'informazione da leggere o da scrivere. Si parla dell'ordine di **millisecondi** (ms), mentre per la RAM siamo nell'ordine di **nanosecondi** (ns).

La **capacità**, al contrario, è enormemente superiore a quella delle memorie centrali.

###### **Modalità di accesso**

- **Accesso diretto**: è possibile selezionare il blocco di dati specifico da leggere o scrivere.
- **Accesso sequenziale**: le informazioni memorizzate sul disco vengono lette una dopo l'altra.

![](imgs/Pasted%20image%2020260523191307.png)

##### **b) Dischi ottici (CD-ROM, DVD, Blu-Ray)**

## Dischi ottici (CD-ROM, DVD, Blu-Ray)

I dischi ottici sono supporti di memorizzazione in cui i dati vengono incisi sotto forma di microscopiche variazioni sulla superficie di un disco in policarbonato, tipicamente costituite da piccole cavità chiamate **pit** alternate a zone piatte dette **land**. 

La lettura avviene tramite un **raggio laser** che colpisce la superficie del disco: la luce riflessa varia a seconda che incontri un pit o un land, e un fotosensore interpreta queste differenze come zeri e uni. 
La scrittura, nei formati registrabili (CD-R, DVD-R) e riscrivibili (CD-RW, DVD-RW), avviene modificando le proprietà riflettive di uno strato di materiale organico o a cambiamento di fase tramite un laser a potenza più elevata. 

Le tre principali generazioni si distinguono per la lunghezza d'onda del laser impiegato: il **CD-ROM** utilizza un laser a infrarosso (780 nm) e offre una capacità di circa 700 MB; il **DVD** impiega un laser rosso (650 nm), raggiungendo circa 4,7 GB per strato; il **Blu-Ray** sfrutta un laser blu-violetto (405 nm) che, grazie alla lunghezza d'onda più corta, consente di leggere tracce molto più ravvicinate, arrivando a 25 GB per strato e oltre 100 GB nelle varianti multi-strato. 

Il tempo di accesso è significativamente più lento rispetto ai dischi magnetici e alle memorie a stato solido, poiché dipende dalla velocità di rotazione del disco e dal posizionamento meccanico della testina ottica. Per questa ragione i dischi ottici sono stati storicamente utilizzati per la **distribuzione di software**, contenuti multimediali e l'**archiviazione di dati statici** a lungo termine, piuttosto che come memorie di lavoro.

##### **c) Nastri magnetici**

I nastri magnetici sono tra le più antiche tecnologie di memorizzazione digitale, commercializzati per la prima volta negli anni '50, eppure ancora oggi ampiamente utilizzati. Il principio di funzionamento è analogo a quello dei dischi magnetici: i dati vengono registrati polarizzando il materiale ferromagnetico depositato su un lungo nastro in materiale plastico, ma con una differenza fondamentale nella modalità di accesso. 

Essendo il nastro un supporto **lineare**, l'accesso ai dati è esclusivamente **sequenziale**: per raggiungere un'informazione specifica è necessario scorrere tutto il nastro che la precede, il che rende i tempi di accesso enormemente più lenti rispetto ai dischi. A compensare questa lentezza vi è però una **capacità di archiviazione molto elevata** a costi estremamente contenuti per terabyte, accompagnata da consumi energetici minimi e una longevità del supporto che può superare i 30 anni. 

Proprio per queste caratteristiche, i nastri magnetici non sono affatto obsoleti: nel 2024 le spedizioni globali hanno raggiunto il record di 176,5 exabyte, trainate soprattutto dalla crescita esponenziale dei dati generati dall'intelligenza artificiale e dall'IoT. 

Lo standard attuale è l'**LTO Ultrium** (Linear Tape-Open), giunto alla generazione **LTO-10** con cartucce da 40 TB nativi (fino a 100 TB compressi), sviluppato congiuntamente da IBM, HPE e Quantum. I nastri rappresentano inoltre una difesa efficace contro i **cyberattacchi**, poiché possono essere fisicamente isolati dalla rete creando un cosiddetto *air gap*: un nastro scollegato è semplicemente inaccessibile da remoto. 

Per queste ragioni, i nastri magnetici restano la soluzione preferita dai grandi data center — inclusi quelli di Google, Microsoft e Amazon — per il **backup**, il **disaster recovery** e l'**archiviazione a lungo termine** di dati a cui si accede raramente (*cold storage*).

---

### **3. Gerarchia di memoria**

#### **3.1. Il principio della gerarchia**

Poiché nessuna memoria singola può contemporaneamente essere **veloce, capiente ed economica**, i sistemi reali combinano più livelli in una **gerarchia**.  
Ogni livello è **più lento ma più grande** di quello che lo precede.

$$
\begin{cases}
\text{1.}~ \text{Registri}~ \rightarrow~ \text{velocissimi, capacità minima, integrati nella CPU} \\\\
\text{2.}~ \text{Cache}~ \rightarrow~ \text{molto veloce, capacità ridotta, costosa} \\\\
\text{3.}~ \text{Memoria centrale (RAM)}~ \rightarrow~ \text{rapida, capacità media, volatile} \\\\
\text{4.}~ \text{Dischi magnetici/SSD}~ \rightarrow~ \text{lenti, molto capienti, persistenti} \\\\
\text{5.}~ \text{Nastri magnetici o archiviazione remota}~ \rightarrow~ \text{lentissimi, ma di enorme capacità}
\end{cases}
$$

Il sistema operativo deve **ottimizzare i trasferimenti tra i livelli** di questa gerarchia per ottenere il miglior compromesso tra **velocità e capacità disponibile**.

---

### **4. Caching**

#### **4.1. Il meccanismo di caching**

Il **caching** è la tecnica di **memorizzare temporaneamente copie dei dati** provenienti da un livello più lento in un livello più rapido.  
Quando il processore richiede un’informazione:

1. si controlla se essa è già presente nella cache (cache hit);
2. se non lo è (cache miss), il dato viene copiato dalla memoria inferiore;
3. se necessario, vecchi dati vengono **scaricati** per far spazio a quelli nuovi.

Il caching si basa su tre operazioni fondamentali:

$$
\begin{cases}
\text{Caricamento}~ (\text{loading})~ &\text{: trasferimento di dati dal livello inferiore al superiore;} \\\\
\text{Scaricamento}~ (\text{write-back})~ &\text{: aggiornamento dei dati modificati dal livello superiore;} \\\\
\text{Coerenza}~ (\text{consistency})~ &\text{: mantenimento della sincronizzazione tra le copie.}
\end{cases}
$$

Una **cache coerente** assicura che ogni dato modificato in un livello superiore sia correttamente aggiornato anche in quello inferiore.

In particolare, il contenuto della cache interna al processore deve **coincidere sempre** con il contenuto della memoria centrale: se viene fatta una modifica nella cache, questa deve essere **riportata nella memoria centrale**, in modo tale che al successivo svuotamento o sostituzione della cache si mantenga il valore di riferimento, che potrà essere riutilizzato successivamente.

---

#### **4.2. Caching applicato alle memorie di massa**

Lo stesso approccio del caching può essere utilizzato anche per **accelerare l'accesso a dispositivi di memoria più lenti**, in particolare ai dischi.

Quando il processore accede in modo molto frequente a determinati blocchi di dati posti sul disco, è possibile **scaricare in una zona di memoria RAM** le informazioni contenute in tali blocchi. In questo modo si evita che il processore debba **chiedere continuamente** di leggere le informazioni dal disco e quindi debba continuamente attendere il **riposizionamento meccanico della testina** nella zona del disco su cui si trovano tali informazioni.

La cache per i dispositivi di memoria di massa può essere realizzata in due punti:

- **in memoria centrale del calcolatore**: il sistema operativo riserva una porzione di RAM in cui mantenere copia dei blocchi di disco più frequentemente acceduti;
- **a bordo del disco stesso**: il disco dispone di una propria memoria cache interna che mantiene una copia dei dati letti o in attesa di essere scritti.

In entrambi i casi si **evita il tempo elettromeccanico** di riposizionamento della testina, e serve solo attendere il **tempo di trasferimento** dell'informazione dalla cache verso le variabili della memoria centrale su cui il programma opererà.

---

### **5. Protezione della memoria**

#### **5.1. Necessità di protezione**

Quando più programmi vengono eseguiti in contemporanea (**multiprogrammazione**), è essenziale garantire che **ognuno operi solo nello spazio di memoria assegnato**.  
Senza protezione, un processo potrebbe sovrascrivere aree di memoria di altri processi o del sistema operativo, compromettendo la stabilità dell’intero sistema.

#### **5.2. Meccanismi di protezione per livello**

Ogni livello di memoria dispone di specifiche modalità di protezione:

$$
\begin{cases}
\textbf{Registri:}~ & \text{Protezione implicita. Cambiano automaticamente con il processo attivo.} \\\\
\textbf{Cache:}~ & \text{Protezione implicita grazie ai meccanismi hardware di gestione.} \\\\
\textbf{Memoria centrale:}~ & \text{Gestita tramite la MMU (Memory Management Unit), che traduce e controlla gli indirizzi.} \\\\
\textbf{Memorie di massa:}~ & \text{Gestite dal file system, che stabilisce permessi e privilegi di accesso.}
\end{cases}
$$

La protezione dei **registri** è implicita nel meccanismo di cambio del programma in esecuzione: quando si passa da un programma all'altro, il sistema operativo dovrà **salvare il contenuto dei registri** del programma uscente e ricaricarli con i valori del programma entrante (concetto che verrà approfondito nelle lezioni successive sui processi).

La protezione del contenuto della **cache** è invece **automaticamente aggiornata e gestita** dai meccanismi hardware preposti, per cui non è necessario preoccuparsi esplicitamente di garantire che il suo contenuto venga aggiornato correttamente o protetto dall'accesso da parte di altri programmi.

Per la **memoria centrale**, invece, occorre uno specifico dispositivo hardware dedicato — la **MMU (Memory Management Unit)** — che garantisca che le porzioni di memoria assegnate a un programma non vengano utilizzate dagli altri programmi.

Per la **memoria di massa**, infine, la protezione delle informazioni viene realizzata nell'ambito della **gestione del file system**, che verrà trattata in dettaglio in seguito.

---

#### **5.3. Il funzionamento della MMU: registro di base e registro limite**

Quando il processore vuole accedere a una porzione di memoria centrale, emette l'**indirizzo desiderato** di tale porzione. È necessario garantire che questo indirizzo individui effettivamente una parola appartenente alla **zona riservata al programma in esecuzione**, e che per sbaglio (o per intento malevolo) non vada a referenziare una zona non assegnata al processo.

Si vuole infatti impedire che vengano eseguite operazioni di **lettura o scrittura in zone non assegnate** al programma in esecuzione.

La MMU supporta tali verifiche tramite due registri fondamentali:

- **Registro di base**: contiene il **primo indirizzo** occupato dal programma in memoria centrale. Il suo valore viene impostato dal sistema operativo nel momento in cui il programma viene avviato.
- **Registro limite**: contiene l'**ultimo indirizzo** (o, equivalentemente, la dimensione della zona) occupato dal programma in memoria centrale.

Quando il processore presenta un indirizzo, anziché inoltrarlo direttamente alla memoria centrale, l'indirizzo viene fornito alla **MMU**, la quale verifica che esso sia **compreso tra il valore di base e il valore limite** assegnati al programma in esecuzione:

$$
\begin{cases}
\text{Base}~ \leq~ \text{Indirizzo}~ \leq~ \text{Limite}~ \rightarrow~ \textbf{Accesso consentito} \\\\
\text{Indirizzo}~ <~ \text{Base}~ \text{oppure}~ \text{Indirizzo}~ >~ \text{Limite}~ \rightarrow~ \textbf{Errore di accesso}
\end{cases}
$$

- Se la verifica ha **esito positivo**, viene consentito l'accesso alla parola desiderata e il suo valore viene **ritornato al processore**.
- Se invece l'indirizzo **non è compreso** nell'intervallo, la MMU **lancia un'interruzione** al processore per segnalare tale errore. La risposta all'interruzione dovrà farsi carico di **capire qual è il problema** che si è verificato e quindi di **segnalare l'errore di accesso** a una zona di memoria non consentita, terminando tipicamente in modo controllato il processo offendente.

In sintesi, attraverso questo meccanismo la MMU realizza l'**isolamento tra processi**: ogni programma può operare esclusivamente sulla propria area di memoria, senza poter alterare — accidentalmente o malevolmente — il contenuto delle aree assegnate ad altri programmi, salvaguardando così la stabilità e la sicurezza dell'intero sistema.

---

### **6. Ruolo del sistema operativo**

Il sistema operativo coordina l’intera gerarchia di memoria, gestendo:

- l’**allocazione dinamica** della memoria ai processi;
- la **paginazione e segmentazione**;
- la **gestione della cache**;
- e la **protezione degli spazi di indirizzamento**.

In questo modo, l’utente percepisce una **memoria uniforme e virtualmente illimitata**, anche se fisicamente limitata e multilivello.

---

### **7. Sintesi finale**

$$
\begin{cases}
\textbf{Memorie:}~ & \text{registri, cache, RAM, dischi, nastri;} \\\\
\textbf{Gerarchia:}~ & \text{più il livello è alto, più è veloce ma costoso;} \\\\
\textbf{Caching:}~ & \text{replica temporanea dei dati per ridurre i tempi di accesso;} \\\\
\textbf{Protezione:}~ & \text{isolamento dei processi e controllo degli indirizzi tramite MMU.}
\end{cases}
$$

---

### **8. Collegamento con i sistemi operativi**

Tutti i concetti introdotti in questa lezione costituiscono la base della **gestione della memoria virtuale**, che affronteremo in _Sistemi Operativi 2_.  
Lì vedremo come il sistema operativo:

- sfrutta la gerarchia fisica per creare **uno spazio di indirizzamento virtuale per processo**,
- applica politiche di **caching e swapping**,
- e utilizza la **MMU** per garantire **protezione e isolamento**.

---

### **9. Approfondimento facoltativo: dischi magnetici vs dischi elettronici

#### **Il tipo di memoria: come vengono salvati i dati**

La distinzione fondamentale è tra due tecnologie di memorizzazione:

- **HDD (Hard Disk Drive)** — è il classico "disco rigido", ovvero un disco **magnetico**. All'interno contiene piatti metallici rotanti e testine meccaniche che leggono e scrivono polarizzando il materiale ferromagnetico, esattamente come descritto nella sezione sui dischi magnetici. È lento perché deve fisicamente spostare le testine e attendere che il piatto ruoti nella posizione giusta, ma offre grandi capacità a basso costo.

- **SSD (Solid State Drive)** — è un disco **elettronico** (o "a stato solido"). Non contiene alcuna parte in movimento: i dati vengono memorizzati in chip di memoria flash (celle di transistor che intrappolano cariche elettriche per rappresentare 0 e 1). L'accesso è puramente elettronico, quindi enormemente più veloce rispetto a un HDD, nell'ordine dei microsecondi anziché dei millisecondi.

#### **L'interfaccia: come il disco comunica con il computer**

Qui entrano in gioco **SATA** e **NVMe**, che non sono tipi di disco ma **protocolli e connettori** attraverso cui il disco si collega alla scheda madre:

- **SATA (Serial ATA)** — è l'interfaccia più vecchia e diffusa, nata nell'era degli HDD. Supporta velocità massime di circa 550 MB/s. Sia gli HDD che gli SSD possono usare SATA: un SSD collegato via SATA sarà molto più veloce di un HDD, ma resterà "imbottigliato" dal limite del protocollo.

- **NVMe (Non-Volatile Memory Express)** — è un protocollo progettato specificamente per sfruttare la velocità delle memorie flash. Utilizza il bus **PCIe** (lo stesso delle schede grafiche) e raggiunge velocità di diversi GB/s, fino a 10 volte superiori rispetto a SATA. Gli SSD NVMe si presentano spesso nel formato **M.2**, una piccola scheda che si inserisce direttamente nella scheda madre.

#### **Riepilogo pratico**

| | **HDD** | **SSD SATA** | **SSD NVMe** |
|---|---|---|---|
| **Tecnologia** | Magnetica (meccanica) | Elettronica (flash) | Elettronica (flash) |
| **Interfaccia** | SATA | SATA | PCIe (M.2) |
| **Velocità tipica** | 80–160 MB/s | ~550 MB/s | 3.000–7.000+ MB/s |
| **Parti mobili** | Sì | No | No |
| **Costo per TB** | Basso | Medio | Medio-alto |
| **Uso tipico** | Archiviazione massiva | Uso generale | OS, gaming, lavoro intensivo |

In sintesi: **HDD e SSD** rispondono alla domanda *"come vengono salvati i dati?"*; **SATA e NVMe** rispondono alla domanda *"quanto velocemente viaggiano quei dati verso il processore?"*. Un SSD NVMe è la combinazione più veloce possibile oggi: memoria elettronica su un'interfaccia ad alta banda.