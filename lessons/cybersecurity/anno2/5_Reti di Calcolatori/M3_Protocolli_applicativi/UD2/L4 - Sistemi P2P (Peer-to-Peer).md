# **Lezione 4: Sistemi P2P (Peer-to-Peer)**

---

### **1. Definizione generale**

Un **sistema P2P (Peer-to-Peer)** è un’architettura di rete in cui i **nodi ai margini di Internet** (cioè i computer degli utenti) condividono direttamente le proprie risorse, **senza dipendere da un server centrale**.  
Ogni nodo può agire **sia da client che da server**, scambiando dati, potenza di calcolo o contenuti.

Le risorse condivise includono:

- **Memoria** (spazio per memorizzare dati o file);
    
- **Cicli di CPU** (potenza di elaborazione);
    
- **Contenuti** (file, musica, video, ecc.);
    
- **Utenti stessi**, che offrono disponibilità alla rete.
    

Poiché i nodi possono connettersi o disconnettersi liberamente, le **connessioni P2P sono intermittenti**: vengono create e rimosse dinamicamente.

---

### **2. Due grandi categorie di sistemi P2P**

I sistemi P2P possono essere suddivisi in due macro-approcci:

1. **Sistemi non strutturati**  
    Esempi: _Napster_, _Gnutella_, _KaZaA_.
    
    - Le connessioni tra nodi non seguono regole fisse.
        
    - La ricerca dei contenuti avviene tramite tecniche di **flooding** o **flashflood**: ogni richiesta viene “inondatta” nella rete.
        
    - Semplici da implementare ma **poco efficienti** per grandi reti.
        
2. **Sistemi strutturati (DHT – Distributed Hash Table)**  
    Esempi: _Chord_, _CAN_, _Pastry_, _Tapestry_.
    
    - I nodi e i contenuti sono organizzati secondo una **struttura logica precisa** (spesso ad anello o ad albero).
        
    - Consentono **ricerche rapide e deterministiche**.
        
    - Ideali per **memorizzazione persistente**, **distribuzione di file** o **gestione della mobilità** dei peer.
        

---

### **3. Concetto di rete overlay**

Un **overlay** è una **rete virtuale** costruita sopra la rete fisica Internet.  
Ogni peer possiede un **indirizzo overlay** (logico), che può essere tradotto nel suo **indirizzo IP reale**.

![](imgs/Pasted%20image%2020260225161523.png)

#### **Manutenzione dell’overlay**

Per mantenere in vita la rete overlay:

- i nodi eseguono periodicamente un **ping** per verificare se i vicini sono ancora attivi;
    
- se un nodo si disconnette, viene **ricostruito un nuovo collegamento** per mantenere la connettività;
    
- ogni nuovo nodo che entra deve essere **inizializzato**, cioè inserito correttamente nella struttura overlay.
    

#### **Tipologie di overlay**

- **Overlay non strutturate**: un nuovo nodo sceglie in modo **casuale** alcuni vicini già esistenti.
    
- **Overlay strutturate**: i collegamenti seguono una **logica precisa** (es. struttura ad albero, anello o DHT).
    
- **Prossimità IP**: la vicinanza fisica o geografica **non è necessariamente considerata**, poiché i legami overlay sono logici.
    

---

### **4. Overlay a livello applicativo**

Le reti P2P operano **completamente nello strato di applicazione** del modello TCP/IP.  
Questo offre una **straordinaria flessibilità di progettazione**, perché gli sviluppatori possono decidere liberamente:

![](imgs/Pasted%20image%2020260225161540.png)

- la **topologia** della rete (maglia, anello, albero…);
    
- i **meccanismi di mantenimento** e aggiornamento dei nodi;
    
- il **tipo di messaggi** e di **protocollo di comunicazione** (basato su TCP o UDP);
    
- il **formato** e la **gestione delle query** tra i peer.
    

La rete fisica sottostante rimane **trasparente**, perché l’overlay si comporta come una rete logica indipendente.

---

### **5. Esempi di overlay nella pratica**

Esempi di reti overlay reali o concettualmente simili:

- **DNS** – è una rete distribuita di server che risolvono nomi in indirizzi IP.
    
- **Router BGP** – stabiliscono collegamenti logici di _peering_ tra domini di rete.
    
- **CDN (Content Distribution Networks)** – distribuiscono contenuti web tramite server dislocati geograficamente.
    
- **Multicast a livello applicativo** – simula la trasmissione multicast anche dove non è supportata dall’IP nativo.
    
- **Applicazioni P2P** – come sistemi di file sharing o streaming distribuito.

![](imgs/Pasted%20image%2020260225161630.png)

---

### **6. Esempio pratico: condivisione di file P2P**

Immaginiamo **Alice**, che usa un’applicazione P2P di file sharing:

1. Alice **avvia il client P2P** sul suo notebook.
    
2. Si **connette saltuariamente** a Internet, ottenendo ogni volta un nuovo indirizzo IP.
    
3. Registra il contenuto che vuole condividere (es. canzoni o documenti).
    
4. Cerca un file scrivendo “Hey Jude”.
    
5. Il programma mostra un elenco di **altri peer** che possiedono una copia del file.
    
6. Alice sceglie un peer, ad esempio **Roberto**, e scarica il file **direttamente dal suo computer**.
    
7. Mentre scarica, anche **altri utenti possono scaricare** parti dello stesso file dal computer di Alice.
    

Questo modello trasforma **ogni utente in un piccolo server di contenuti**, creando una rete di milioni di nodi che si scambiano dati simultaneamente.

---

### **7. Software di condivisione P2P**

Un tipico software P2P offre tre funzionalità principali:

1. **Server virtuale** – consente di aprire una directory nel proprio file system, accessibile agli altri utenti (simile a un server web).
    
2. **Client virtuale** – permette di copiare file dalle directory condivise di altri peer (come un browser web).
    
3. **Motore di ricerca distribuito** – consente di cercare contenuti per **parole chiave**, analogamente a Google, ma limitato ai nodi della rete.
    

---

### **8. Problemi di copyright**

L’uso dei sistemi P2P per la **condivisione di contenuti protetti da copyright** ha generato controversie legali su due fronti:

#### **a. Violazione diretta**

- Gli **utenti finali** che scaricano o condividono materiale protetto (musica, film, software) commettono direttamente la violazione.
    

#### **b. Violazione indiretta**

Coinvolge chi, **senza violare direttamente il copyright**, contribuisce o trae vantaggio dalla violazione:

- **Contraffazione contributiva** → chi sapeva della violazione e ha **favorito o facilitato** l’atto (es. sviluppatore o gestore del sistema).
    
- **Contraffazione indiretta** → chi era in grado di **controllare i trasgressori diretti** (es. chi può disattivare account) ma **non lo ha fatto**, ottenendo **vantaggi economici** dalla violazione (più utenti, pubblicità, guadagni).
    

---

### **9. Instant Messaging e P2P**

Anche la **messaggistica istantanea (IM)** può basarsi su architetture P2P.  
Esempio:

1. Alice avvia il **client IM** sul suo PC.
    
2. Si connette a Internet e si **registra** su un sistema centrale.
    
3. Apprende dall’elenco di amici che **Roberto è online**.
    
4. Stabilisce una **connessione TCP diretta** con lui.
    
5. I due **chattano direttamente**, scambiandosi testo, voce o video.
    

Molti sistemi VoIP moderni, come **Skype**, si basano su un’architettura **ibrida P2P**, in cui il traffico multimediale passa direttamente tra i peer.

---

### **10. Calcolo distribuito P2P**

Un altro impiego dei sistemi P2P è il **calcolo distribuito**, cioè l’unione della potenza di molti computer sparsi nel mondo per risolvere problemi scientifici complessi.

Esempio emblematico: **SETI@home**

- Progetto per la **ricerca di segnali extraterrestri** provenienti da radiotelescopi.
    
- Un **server centrale** divide i dati in **blocchi di lavoro** (circa 300 KB ciascuno).
    
- Gli utenti scaricano un **client** che lavora in background:
    
    1. scarica un blocco di dati;
        
    2. esegue calcoli di analisi (trasformate FFT);
        
    3. invia i risultati al server e riceve un nuovo blocco.
        

In questo modo, migliaia di computer domestici collaborano come un **supercomputer globale**.

---

### **11. Conclusione**

I sistemi **Peer-to-Peer** rappresentano una delle evoluzioni più significative nella storia di Internet:  
hanno dimostrato che una rete **senza centro** può essere **efficiente, scalabile e resiliente**.

Oggi i principi del P2P sono alla base di:

- **reti di distribuzione dei contenuti (CDN)**,
    
- **tecnologie di blockchain e criptovalute**,
    
- **piattaforme di streaming e comunicazione diretta**.
    

Il futuro della rete continua a muoversi verso questa logica **distribuita e collaborativa**, in cui ogni nodo contribuisce con una parte delle proprie risorse per costruire un sistema globale più potente.