# **Lezione 4: Sistemi P2P (Peer-to-Peer)**

---

### **1. Definizione generale**

Un **sistema P2P (Peer-to-Peer)** è un'architettura collaborativa composta da **programmi tutti uguali** (peer), contrariamente al modello client/server in cui i ruoli sono distinti. Dal punto di vista ingegneristico presenta caratteristiche importanti:

- Sfrutta risorse **non del cuore della rete**, ma dei computer degli utenti:
    - **Memoria e contenuti** detenuti dagli utenti;
    - **Cicli di CPU** sui computer degli utenti;
    - **Il lavoro degli utenti stessi**.

Questo lo rende potenzialmente a **costo molto basso**: non occorrono grandi server centralizzati. Tuttavia crea la necessità di gestire **problemi di discontinuità di presenza**: un computer di un utente può essere spento, la connessione può andare e venire, quindi il sistema deve funzionare anche in condizioni di **limitata disponibilità** dei nodi.

---

### **2. Due grandi categorie di sistemi P2P**

I sistemi P2P si dividono in due macro-approcci:

1. **Sistemi non strutturati**
    Esempi storici: _Napster_, _Gnutella_, _KaZaA_.
    - Non c'è nessuna relazione tra un programma, la sua posizione rispetto agli altri e il suo compito.
    - La ricerca dei contenuti avviene tramite tecniche di **flooding** (o "flash flood"): le richieste vengono propagate a tutti i vicini.
    - I vicini vengono scelti **casualmente**, trovandoli localmente sulla propria subnet IP → più facile che i vicini di overlay siano anche vicini IP.
    - Semplici da implementare ma poco efficienti per reti molto grandi.

2. **Sistemi strutturati (DHT – Distributed Hash Table)**
    Esempi: _Chord_, _CAN_, _Pastry_, _Tapestry_.
    - Il compito di un programma è determinato dalla sua **posizione sull'overlay** rispetto agli altri.
    - I vicini vengono scelti in base al ruolo/contenuto che si vuole gestire, non casualmente.
    - La prossimità IP non viene tenuta in considerazione: ci si colloca vicino a nodi con contenuti simili.
    - L'operazione di adesione all'overlay è più complessa rispetto ai sistemi non strutturati.

---

### **3. Concetto di rete overlay**

La rete P2P è un **overlay** della rete IP: è situata **sopra** di essa. I computer che ne fanno parte si conoscono tramite **pseudonimi, nick o indirizzi tipici dell'overlay** che non hanno nulla a che fare con gli indirizzi IP delle macchine su cui girano. Tuttavia ogni nodo deve essere in grado di risolvere l'indirizzo overlay dei propri vicini in indirizzo IP (almeno per quelli diretti), e tramite propagazione tra vicini è possibile raggiungere tutti i nodi dell'overlay.

![](imgs/Pasted%20image%2020260622221655.png)

#### **Manutenzione dell'overlay**

- Periodicamente ogni nodo effettua un **ping** dei propri vicini per verificarne l'attività.
- Se un vicino cade e il numero di vicini scende sotto il minimo necessario, bisogna **reclutarne un altro**.
- Ogni nuovo nodo che entra deve trovare dei vicini prima di poter aderire all'overlay.

#### **Adesione all'overlay non strutturato**

Il nuovo nodo cerca localmente sulla propria subnet IP qualcuno da reclutare come vicino, si auto-attribuisce un nick/indirizzo overlay, lo comunica ai vicini trovati: entra nella rete in modo completamente non strutturato, in base a chi si trovava nelle vicinanze IP.

#### **Adesione all'overlay strutturato**

Bisogna trovare i vicini "giusti" in base al ruolo che si vuole assumere. Si riceve un overlay address in base alla posizione che si deve occupare. Ad esempio: se un nodo ha tutti i file immagine e sulla propria subnet IP trova un nodo dell'overlay che ha tutti i file di testo, non potrà eleggerlo come vicino. Quel nodo risponderà "chiedi ai miei vicini", e man mano il nuovo nodo si avvicinerà alla parte dell'overlay dove si trovano gli altri nodi con file di tipo immagine.

> 📌 Nell'overlay **non strutturato** è più facile che i vicini di overlay siano anche vicini IP (trovati localmente). Nell'overlay **strutturato** invece la prossimità IP non viene considerata: si è vicini in base alla similitudine di contenuto/ruolo.

---

### **4. Overlay a livello applicativo**

Le reti P2P operano **completamente nello strato di applicazione** del modello TCP/IP, e questo offre una straordinaria flessibilità: il progettista può scegliere liberamente topologia, tipi di messaggi, protocollo di comunicazione (TCP o UDP), e formato delle query.

![](imgs/Pasted%20image%2020260622221710.png)

> 💡 Chi sviluppa applicazioni **sopra** l'overlay (si parla informalmente di "livello 8") può non sapere nulla dell'IP che sta sotto: per queste applicazioni l'overlay è il trasporto, e la rete fisica rimane trasparente.

---

### **5. Esempi di overlay nella pratica**

L'overlay non nasce con i sistemi P2P moderni: esistono esempi precedenti nella rete Internet tradizionale.

- **DNS** — può essere considerato una sorta di overlay: ogni server DNS ha un superiore gerarchico che è il suo "vicino" sulla gerarchia DNS, senza alcun rapporto con la sua prossimità IP.

- **Router BGP** — i router BGP si scambiano annunci di rotte; ciascuno può essere considerato vicino degli altri con cui si scambia gli annunci, ma questa vicinanza è una vicinanza di overlay, non ha nulla a che vedere con la vicinanza IP.

- **Multicast applicativo** — overlay appositamente costruite per fare multicast senza usare gli indirizzi classe D. Non fa guadagnare performance come il multicast a livello IP (richiede comunque connessioni unicast), ma semplifica molto la scrittura di certi programmi e quindi è comunque molto utilizzato.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Applicazioni P2P** — file sharing, streaming distribuito, calcolo distribuito (vedi sezioni successive).

---

### **6. Esempio pratico: condivisione di file P2P**

![](imgs/Pasted%20image%2020260622221807.png)

Uno scenario tipico di file sharing P2P:

1. **Alice** attiva il client P2P sul suo computer, si connette a Internet ottenendo un indirizzo IP, e trova dei vicini sull'overlay.
2. Alice **registra il proprio contenuto**: annuncia ai suoi vicini di che cosa dispone; l'annuncio viene propagato sull'overlay.
3. Alice lancia una **query** sull'overlay chiedendo se qualcuno dispone del file "Hey Jude" dei Beatles.
4. La richiesta viaggia per l'overlay tramite propagazione tra vicini fino a raggiungere **Roberto**, che ha il file.
5. Roberto risponde comunicando la sua disponibilità ad Alice.
6. **I due si scambiano gli indirizzi IP** per creare una **media session** — una connessione TCP diretta che **non usa l'overlay** — attraverso cui avviene il download del file.
7. Mentre Alice scarica da Roberto, altri utenti possono aprire media session verso Alice per scaricare i file che lei condivide, generando una rete di flussi di upload incrociati.

> 📌 L'overlay viene usato **solo per trovare il contenuto**. Il trasferimento effettivo avviene tramite **media session dirette tra indirizzi IP**, bypassando completamente l'overlay.

Per un certo periodo si è pensato che questo meccanismo avrebbe **sostituito il download dai server** come modello di diffusione dei contenuti: immettendo contenuti su una rete P2P la velocità di propagazione è molto superiore rispetto a offrirli da un server web (che deve essere promosso, inserito nel DNS, ecc.).

---

### **7. Software di condivisione P2P e analogia Dropbox**

Un tipico software P2P simula una **cartella del file system** visibile agli altri utenti sull'overlay, offrendo tre funzionalità:

1. **Server virtuale** – apre una directory condivisa accessibile agli altri peer.
2. **Client virtuale** – permette di copiare file dalle directory condivise di altri peer.
3. **Motore di ricerca distribuito** – consente di cercare contenuti per parole chiave tra i nodi della rete.

> 💡 Se ognuno si limitasse a condividere file di cui ha disponibilità completa, un sistema del genere — come dimostra il grande successo di **Dropbox** — non sarebbe altro che un utile ausilio alla collaborazione a distanza. Il problema nasce quando si condividono file che non si ha il diritto di distribuire.

---

### **8. Problemi di copyright**

L'uso dei sistemi P2P per la condivisione di contenuti protetti ha generato ampie controversie legali.

#### **a. Violazione diretta**

L'utente che scarica dalla cartella di Alice una canzone che Alice detiene legalmente, ma che non avrebbe il diritto di scaricare (e che Alice non avrebbe il diritto di dargli), commette una **violazione diretta**. Alice a sua volta, pur avendo il DVD acquistato per visione privata, non ha il diritto di distribuirlo ad altri.

#### **b. Violazione indiretta**

- **Il sistema P2P di ricerca** può configurarsi come violazione indiretta: non trasferisce nulla (la media session avviene direttamente tra gli IP dei due utenti), ma supporta la ricerca e l'identificazione del contenuto senza verificare se chi lo pubblicizza ha il diritto di distribuirlo.
- **Chi sviluppa e distribuisce il software** P2P che consente ad altri di distribuire contenuto su cui non hanno diritti è considerato contraffattore indiretto.
- **Le compagnie di telecomunicazione** che consentono ai propri utenti di usare software P2P possono essere ritenute responsabili per **omesso controllo** (responsabilità oggettiva): hanno lasciato sviluppare da parte degli utenti l'utilizzo di software di ricerca che consentono lo stabilire di media session di trasferimento di materiale protetto.

> ⚠️ In gran parte del mondo, Italia compresa, sono state approvate disposizioni di legge che distinguono **contraffattori contributivi** (chi facilitava direttamente la violazione) e **contraffattori indiretti** (chi era in grado di controllare i trasgressori diretti ma non lo ha fatto, ottenendo vantaggi economici).

---

### **9. Instant Messaging e P2P**

L'**instant messaging** è un caso d'uso del P2P privo delle criticità legali del file sharing:

1. Alice avvia il client IM, che si registra sull'overlay trovando dei vicini.
2. Invia messaggi sull'overlay per avere informazioni sulla **presenza in rete** dei propri amici in quel momento.
3. Scopre che Roberto è attivo e avvia una **connessione TCP diretta** con lui per la chat.

Siccome non c'è trasferimento di file, il sistema è un'applicazione di chat interattiva P2P estremamente flessibile, che usa l'overlay solo per scoprire la presenza degli amici.

**Skype** è l'esempio più noto: con l'aiuto di **super peer** (nodi con capacità maggiore) cerca sull'overlay gli amici tramite i loro Nick Skype. Quando uno è in linea, invia un invite (richiesta di connessione telefonica) e parte una **media session** per il dialogo diretto.

---

### **10. Calcolo distribuito P2P**

Un ulteriore utilizzo dell'overlay è la **delega di compiti di calcolo distribuito**: un nodo annuncia la propria disponibilità di cicli macchina sull'overlay; un sito centrale (o super peer) riceve l'annuncio e trasmette un compito da elaborare.

Esempio emblematico: **SETI@home**

- Progetto per la **ricerca di segnali extraterrestri** provenienti da radiotelescopi.
- Il client in background esegue una **FFT (Fast Fourier Transform)** su blocchi di dati ricevuti dai radiotelescopi, alla ricerca di regolarità che indicherebbero una trasmissione proveniente da un pianeta abitato da una specie intelligente.
- In questo modo migliaia di computer domestici collaborano come un **supercomputer globale**, analizzando una quantità di dati molto maggiore di quella possibile con un supercomputer centralizzato.

> ✅ I sistemi P2P dimostrano che una rete senza centro può essere efficiente, scalabile e resiliente, sfruttando le risorse distribuite degli utenti per obiettivi collettivi che vanno dalla condivisione di contenuti al calcolo scientifico.
