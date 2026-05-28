# **M4 UD4 Lezione 1 - File system distribuiti**

---

### **1. Introduzione**

In questa lezione vengono presentate la struttura e le funzioni di un **file system distribuito**.

L'obiettivo e' capire come gestire l'accesso alle risorse informative e fisiche presenti sulle diverse macchine di un'architettura distribuita.

Un file system per ambienti distribuiti deve consentire:

- condivisione di file collocati su macchine diverse;
- accesso alle risorse informative e fisiche del sistema;
- visione globale e astratta dell'insieme delle risorse;
- trasparenza della posizione fisica dei file;
- gestione efficiente degli accessi.

> 📌 L'utente dovrebbe accedere a un file usando la sua posizione logica nel file system, senza dover sapere su quale macchina sia fisicamente memorizzato.

---

### **2. Obiettivi di un file system distribuito**

Gli obiettivi principali sono:

- fornire una visione unitaria delle risorse distribuite;
- nascondere la posizione fisica delle informazioni;
- permettere accessi locali e remoti con la stessa interfaccia;
- supportare condivisione, replica e migrazione dei file;
- migliorare prestazioni e tolleranza ai guasti.

La trasparenza e' l'aspetto centrale.

Un processo non dovrebbe dover distinguere tra:

- file locale;
- file remoto;
- file replicato;
- file migrato da una macchina a un'altra.

---

### **3. File system di rete e file system distribuiti**

I file system per architetture distribuite si dividono in due grandi categorie:

- **file system di rete**;
- **file system distribuiti** in senso stretto.

Entrambi permettono la condivisione di file delocalizzati, ma differiscono nel livello di trasparenza offerto.

---

### **4. File system di rete**

Un **file system di rete** e' una collezione dei file system delle singole macchine connesse in rete.

Ogni macchina mantiene il proprio file system locale e puo' montare porzioni di file system remoti.

In questo modello:

- la struttura della rete rimane visibile;
- l'utente puo' percepire dove si trova una risorsa;
- l'accesso remoto avviene tramite montaggio o servizi remoti;
- la visione globale e' ottenuta componendo file system distinti.

> ⚠️ Il limite principale e' che la localizzazione delle risorse rimane almeno in parte visibile agli utenti e ai processi.

---

### **5. File system distribuito**

Un **file system distribuito** integra i file system delle singole macchine in un unico file system globale.

In questo caso il sistema fornisce:

- una visione omogenea su tutte le macchine;
- un unico spazio dei nomi;
- accesso trasparente alle risorse;
- allocazione nascosta dei file;
- indipendenza dalla posizione fisica.

La struttura interna sulle varie macchine non dovrebbe essere visibile.

> ✅ In un vero file system distribuito, l'utente vede un unico file system astratto, non una collezione di file system remoti montati.

---

### **6. Identificazione delle risorse**

Uno dei problemi principali e' l'identificazione delle risorse, cioe' la gestione dei **nomi dei file**.

Ogni file deve avere un identificatore che consenta al sistema di individuarlo.

Nei sistemi distribuiti si vuole ottenere un identificatore:

- unico;
- stabile;
- indipendente dalla posizione fisica;
- utilizzabile da tutte le macchine.

---

### **7. Nomi nei file system di rete**

In un file system di rete, il nome puo' includere:

- nome della macchina;
- percorso del file su quella macchina;
- punto di montaggio locale del file system remoto.

Un file remoto puo' quindi essere individuato attraverso una forma logica del tipo:

$$
\text{macchina} + \text{percorso locale del file}
$$

Oppure, dopo il montaggio, puo' essere raggiunto usando un percorso locale che attraversa il punto di montaggio.

In questo caso la rete e la locazione fisica rimangono ancora concettualmente visibili.

---

### **8. Nomi nei file system distribuiti**

In un file system distribuito il nome del file e' unico nell'intero sistema.

Il processo usa il nome logico del file.

Il sistema operativo distribuito provvede poi a mappare automaticamente quel nome:

- su un indirizzo locale, se il file e' sulla stessa macchina;
- su un indirizzo remoto, se il file si trova su un'altra macchina.

Questa mappatura e' trasparente per il processo richiedente.

---

### **9. Trasparenza della locazione**

La locazione fisica del file puo' essere:

- **visibile**, se compare nel nome o nel percorso;
- **invisibile**, se il sistema la nasconde completamente.

La trasparenza della locazione e' importante per due motivi:

- permette di spostare file senza cambiare i programmi;
- permette di replicare file senza modificare i nomi usati dagli utenti.

Replica e migrazione diventano molto piu' semplici quando il nome non dipende dalla posizione fisica.

---

### **10. Accesso ai file in un file system di rete**

Nel file system di rete l'accesso ai file remoti puo' avvenire in due modi principali.

Il primo consiste nell'usare servizi remoti del sistema operativo, spesso realizzati tramite **RPC**.

In questo caso il processo invoca operazioni sui file:

- open;
- read;
- write;
- close.

Queste operazioni vengono eseguite remotamente sulla macchina che possiede il file.

Il secondo modo consiste nel copiare localmente il file:

1. il file viene trasferito sul client;
2. il processo lo manipola localmente;
3. il file modificato viene salvato di nuovo in remoto.

Questa soluzione richiede attenzione alla mutua esclusione e alla consistenza, soprattutto se piu' processi possono modificare lo stesso file.

---

### **11. Accesso ai file in un file system distribuito**

Nel file system distribuito l'accesso appare come una normale richiesta al sistema operativo.

Il processo non decide se l'operazione debba essere locale o remota.

Il servizio di file system:

- riceve la richiesta;
- individua la posizione effettiva del file;
- decide se operare localmente;
- oppure inoltra la richiesta a un'altra macchina;
- restituisce il risultato al processo.

Tutto questo avviene in modo trasparente.

> 📌 L'interfaccia resta unica: cambia solo la realizzazione interna dell'accesso.

---

### **12. Caching dei file**

Per migliorare le prestazioni si introducono tecniche di **caching**.

La cache mantiene copie di file o porzioni di file remoti, evitando di accedere ogni volta alla macchina che contiene l'originale.

La cache puo' trovarsi:

- sulla singola macchina client;
- su una macchina della sottorete;
- su file server distribuiti nel sistema.

Il caching riduce:

- traffico di rete;
- tempo di accesso;
- carico sui server.

---

### **13. Politiche di aggiornamento della cache**

Le principali politiche di aggiornamento sono:

| Politica | Comportamento |
|---|---|
| **Write-through** | Ogni scrittura in cache viene completata solo quando e' stata scritta anche su memoria di massa o sul server |
| **Scrittura ritardata** | La scrittura locale viene separata dall'aggiornamento remoto, che avviene successivamente |
| **Write-on-close** | Le modifiche vengono propagate solo alla chiusura del file |

La scelta incide sul compromesso tra:

- prestazioni;
- coerenza;
- traffico di rete;
- rischio di perdita di aggiornamenti in caso di guasto.

---

### **14. Consistenza della cache**

L'uso della cache introduce il problema della consistenza.

Se piu' macchine conservano copie dello stesso file, il sistema deve garantire che le copie non evolvano in modo incoerente.

La verifica della consistenza puo' essere:

- **attivata dal client**, che chiede al server se la propria copia e' ancora valida;
- **attivata dal server**, che notifica o invalida le copie conservate dai client.

Un altro parametro critico e' la dimensione della cache.

Una cache troppo piccola riduce poco il traffico di rete.

Una cache troppo grande puo' occupare spazio inutilmente e complicare la gestione della consistenza.

---

### **15. Stato del file server**

Per migliorare la gestione degli accessi si puo' introdurre il concetto di **stato** del file server.

Lo stato e' l'insieme delle informazioni che descrivono l'uso di un file aperto, ad esempio:

- file aperti;
- posizione corrente;
- diritti di accesso;
- informazioni di sessione;
- dati necessari a completare letture e scritture successive.

Esistono due modelli:

- file server senza stato;
- file server con stato.

---

### **16. File server senza stato**

Un file server **senza stato** non mantiene informazioni persistenti sulle operazioni precedenti.

Ogni richiesta di lettura o scrittura viene gestita in modo indipendente.

Ogni richiesta deve quindi contenere tutte le informazioni necessarie:

- nome del file;
- posizione da leggere o scrivere;
- operazione richiesta;
- dati o parametri dell'accesso.

Il vantaggio e' la semplicita' e una maggiore tolleranza ai guasti.

Lo svantaggio e' il costo: a ogni richiesta il server deve tradurre il nome simbolico e reperire le informazioni fisiche necessarie.

---

### **17. File server con stato**

Un file server **con stato** conserva informazioni sugli accessi in corso.

L'operazione di `open` crea lo stato di uso del file.

Le operazioni successive possono usare direttamente le informazioni gia' memorizzate, senza ripetere ogni volta tutta la traduzione del nome e il reperimento dei metadati.

Questo rende l'accesso piu' efficiente.

Il limite e' che il server deve gestire:

- accessi concorrenti;
- guasti;
- recupero dello stato;
- coerenza tra piu' processi.

---

### **18. Replica dei file**

La replica consiste nel mantenere piu' copie dello stesso file su macchine diverse.

Serve a migliorare:

- **tolleranza ai guasti**, perche' se una copia non e' disponibile se ne puo' usare un'altra;
- **prestazioni**, perche' processi diversi possono accedere a copie diverse;
- **tempo di accesso**, perche' le copie possono essere piu' vicine ai processi richiedenti.

La replicazione dovrebbe essere invisibile agli utenti.

L'utente continua a usare lo stesso nome logico del file, mentre il sistema sceglie quale copia utilizzare.

---

### **19. Problema della consistenza delle repliche**

La replica introduce un problema essenziale: tutte le copie devono restare coerenti.

Se una copia viene modificata, il sistema deve evitare che altre copie rimangano obsolete o divergano.

Per questo servono politiche di aggiornamento e sincronizzazione.

> ⚠️ La replica aumenta disponibilita' e prestazioni, ma rende piu' complessa la consistenza.

---

### **20. Sintesi**

| Aspetto | File system di rete | File system distribuito |
|---|---|---|
| Visione | Collezione di file system remoti | Unico file system globale |
| Struttura della rete | Visibile | Nascosta |
| Nome del file | Puo' includere macchina o montaggio | Unico nel sistema globale |
| Accesso | RPC, montaggio remoto, copia locale | Servizio trasparente del sistema operativo |
| Locazione | Spesso visibile | Trasparente |
| Replica e migrazione | Piu' difficili da nascondere | Naturalmente supportate dalla trasparenza |

---

### **21. Conclusione**

I file system di rete e i file system distribuiti hanno lo stesso obiettivo generale: permettere la condivisione di file in un'architettura distribuita.

La differenza fondamentale e' il livello di trasparenza.

Il file system di rete mostra ancora la struttura delle macchine e dei file system remoti.

Il file system distribuito, invece, mira a fornire una visione unica, omogenea e indipendente dalla locazione fisica delle risorse.

Per ottenere questa visione deve gestire nomi globali, accessi remoti, cache, stato del server, replica e consistenza delle informazioni.
