# **M4 UD2 Lezione 1 - Gestione della comunicazione in rete**

---

### **1. Introduzione**

Questa lezione introduce la gestione della rete come periferica speciale nell'ambito dei sistemi distribuiti.

Il sistema operativo deve garantire la trasmissione di informazioni tra nodi diversi, rendendo possibile la cooperazione tra processi e l'accesso a risorse remote.

Gli aspetti principali sono:

- risoluzione dei nomi;
- identificazione univoca di risorse e processi;
- instradamento delle richieste;
- strategie di invio dei messaggi;
- gestione della sequenza dei messaggi;
- gestione dei conflitti durante la comunicazione.

> 📌 La rete è una periferica speciale: non collega un processo a un solo dispositivo, ma permette l'interazione tra più sistemi di elaborazione.

---

### **2. Identificazione delle risorse**

Il primo problema nella gestione della rete è identificare in modo univoco risorse e processi nell'architettura distribuita.

Una soluzione tipica usa una coppia:

$$
\langle \text{nome host}, \text{identificatore locale} \rangle
$$

dove:

- il **nome host** identifica la macchina su cui si trova la risorsa o il processo;
- l'**identificatore locale** identifica quella risorsa o quel processo all'interno della macchina.

Il nome host può essere:

- numerico, come un indirizzo IP;
- simbolico, come un nome logico associato poi a un indirizzo numerico.

Esempio:

```text
192.168.1.20
server.dominio.it
```

> ⚠️ In un vero sistema operativo distribuito questa coppia deve restare interna: l'utente dovrebbe vedere un identificatore unico, senza conoscere l'host.

---

### **3. Trasparenza dell'identificazione**

In un sistema distribuito, l'identificazione delle risorse dovrebbe essere trasparente.

L'utente non dovrebbe sapere:

- su quale host si trova la risorsa;
- quale indirizzo numerico deve essere usato;
- quale percorso di rete deve essere seguito.

Il sistema operativo distribuito deve quindi trasformare un nome simbolico in una localizzazione effettiva, in modo analogo a quanto fa il compilatore quando traduce nomi simbolici in indirizzi.

> 💡 Come il file system nasconde la posizione fisica dei file sul disco, il sistema distribuito deve nascondere la posizione fisica delle risorse nella rete.

---

### **4. Risoluzione dei nomi**

La **risoluzione dei nomi** è il processo con cui un nome simbolico viene tradotto in un indirizzo utilizzabile per accedere alla risorsa.

Esistono due approcci principali.

#### **4.1. Tabelle locali replicate**

Ogni host può mantenere una tabella o un file con l'elenco dei nomi simbolici degli altri host e i relativi indirizzi.

Vantaggi:

- risoluzione rapida;
- nessuna interrogazione remota se la tabella è aggiornata.

Limiti:

- duplicazione delle informazioni;
- difficoltà di aggiornamento;
- rischio di riferimenti obsoleti;
- scarsa scalabilità nei sistemi grandi.

#### **4.2. Risoluzione distribuita**

In alternativa, le informazioni possono essere distribuite nella rete.

Il sistema interroga opportuni server finchè non ottiene la traduzione desiderata.

Questi server sono i **domain name server**, cioè server dei nomi di dominio.

La risoluzione può essere gerarchica:

1. si interroga un primo server;
2. questo risolve una parte del nome o indica un altro server;
3. la ricerca procede finchè il nome è tradotto completamente.

> 📌 Il DNS è un esempio di risoluzione distribuita e gerarchica dei nomi.

---

### **5. Caching dei nomi**

Per migliorare le prestazioni, le traduzioni già effettuate possono essere conservate in cache.

La cache evita di ripercorrere tutta la catena di risoluzione per nomi già usati.

Il caching può essere:

- locale alla singola macchina;
- condiviso da un gruppo di macchine;
- gerarchico a livello di rete locale;
- gerarchico a livello geografico.

È però necessario aggiornare o rinfrescare periodicamente la cache.

Se la cache non viene aggiornata, può contenere:

- riferimenti a macchine non più esistenti;
- indirizzi di macchine guaste;
- informazioni non aggiornate su nuove macchine.

> ⚠️ Il caching accelera la risoluzione dei nomi, ma introduce il problema della validità temporale delle informazioni.

---

### **6. Instradamento**

Dopo aver identificato il destinatario, bisogna trovare il percorso per raggiungerlo.

L'**instradamento** consiste nell'identificare il percorso da una sorgente $A$ a una destinazione $B$.

Il percorso può essere scelto considerando:

- velocità dei collegamenti;
- carico della rete;
- costo di uso dei collegamenti;
- disponibilità dei nodi intermedi;
- presenza di guasti.

Le tabelle di instradamento aiutano a calcolare o memorizzare i percorsi.

---

### **7. Instradamento statico**

Nell'**instradamento statico** o fisso, il percorso tra due macchine viene definito a priori.

Caratteristiche:

- non richiede ricerca durante l'esecuzione;
- è semplice da gestire;
- non si adatta alle variazioni della rete.

Limiti:

- non reagisce ai cambiamenti di carico;
- non gestisce bene guasti di nodi o collegamenti;
- può usare percorsi inefficienti se la rete cambia.

> ⚠️ L'instradamento statico è semplice, ma rigido.

---

### **8. Instradamento virtuale**

Nell'**instradamento virtuale**, il percorso tra $A$ e $B$ viene scelto all'inizio di una sessione di comunicazione.

Il percorso resta fisso per tutta la sessione, ma può cambiare in sessioni successive.

Vantaggi:

- la ricerca del percorso avviene solo all'inizio;
- la sessione ha un percorso stabile;
- sessioni future possono scegliere percorsi diversi.

Limiti:

- durante la sessione non si adatta a variazioni di carico;
- durante la sessione non reagisce bene a guasti lungo il percorso.

> 📌 L'instradamento virtuale è più flessibile dello statico, ma solo tra sessioni diverse.

---

### **9. Instradamento dinamico**

Nell'**instradamento dinamico**, il percorso viene scelto per ogni messaggio.

Messaggi diversi della stessa sessione possono seguire percorsi diversi.

Vantaggi:

- si adatta ai cambiamenti di carico;
- può aggirare guasti;
- sfrutta meglio la rete.

Limiti:

- richiede una scelta del percorso per ogni messaggio;
- introduce più overhead;
- i messaggi possono arrivare in ordine diverso da quello di invio.

Per ricostruire la sequenza corretta, occorre aggiungere informazioni di ordinamento.

> ✅ L'instradamento dinamico è il più adattabile, ma richiede gestione aggiuntiva dell'ordine dei messaggi.

---

### **10. Gateway e router**

Alcuni componenti specializzati supportano l'instradamento.

#### **10.1. Gateway**

Il **gateway** collega una rete locale ad altre reti.

Può anche gestire il cambiamento di protocollo tra reti diverse.

Spesso:

- dall'host al gateway si usa instradamento statico;
- dal gateway verso altri host o reti si usa instradamento dinamico.

Il gateway può essere realizzato:

- in software su un sistema di elaborazione;
- con un dispositivo hardware dedicato.

#### **10.2. Router**

Il **router** instrada messaggi tra reti diverse.

Anche il router può essere:

- software;
- hardware dedicato.

Il suo compito è decidere dove inoltrare i pacchetti per raggiungere la destinazione.

---

### **11. Messaggi e pacchetti**

Dopo aver identificato destinatario e percorso, bisogna inviare le informazioni.

A livello applicativo i messaggi possono avere lunghezza variabile.

Per gestirli in modo efficiente nella rete, vengono divisi in porzioni di dimensione più controllata.

Queste porzioni possono essere chiamate:

- pacchetti;
- frame;
- datagrammi.

Il destinatario deve poi:

- ricevere i pacchetti;
- rilevare eventuali errori;
- individuare pacchetti persi;
- riordinare pacchetti arrivati fuori sequenza;
- riassemblare il messaggio originario.

> 📌 La pacchettizzazione rende efficiente l'invio, ma sposta sul ricevente il problema del riassemblaggio corretto.

---

### **12. Commutazione di circuito**

Nella **commutazione di circuito**, due processi stabiliscono un collegamento fisico fisso per tutta la sessione.

Caratteristiche:

- il canale è dedicato ai due processi;
- nessun altro processo può usare quel collegamento durante la sessione;
- l'attivazione può richiedere tempo;
- una volta stabilito il collegamento, l'overhead di gestione è basso.

Limiti:

- spreco di banda se il canale resta inattivo;
- attesa se parti del collegamento sono già in uso;
- ridotta flessibilità.

> ⚠️ La commutazione di circuito garantisce una connessione stabile, ma usa male la banda quando la comunicazione non è continua.

---

### **13. Commutazione di messaggio**

Nella **commutazione di messaggio**, il collegamento viene creato per la durata dell'invio di un singolo messaggio.

Caratteristiche:

- il collegamento non dura per tutta la sessione;
- viene creato dinamicamente messaggio per messaggio;
- usa meno banda rispetto alla commutazione di circuito;
- introduce più overhead di gestione.

> 💡 La commutazione di messaggio è più flessibile della commutazione di circuito, ma richiede più gestione.

---

### **14. Commutazione di pacchetto**

Nella **commutazione di pacchetto**, ogni pacchetto di un messaggio viene inviato separatamente.

Ogni pacchetto può usare una connessione dinamica propria.

Vantaggi:

- migliore uso della larghezza di banda;
- maggiore flessibilità;
- possibilità di sfruttare percorsi diversi;
- migliore adattamento alla rete.

Svantaggi:

- bisogna suddividere il messaggio;
- bisogna riassemblare i pacchetti;
- bisogna gestire errori, perdite e ritrasmissioni;
- i pacchetti possono arrivare fuori ordine.

> ✅ La commutazione di pacchetto aumenta l'efficienza della rete, ma richiede maggiore complessità di controllo.

---

### **15. Gestione dei conflitti**

Durante le comunicazioni possono verificarsi conflitti, soprattutto quando più nodi condividono lo stesso mezzo trasmissivo.

I casi principali sono:

- reti a bus multiaccesso;
- reti ad anello.

---

### **16. Reti a bus multiaccesso**

In una rete a bus multiaccesso, più nodi condividono lo stesso canale.

Se due sistemi trasmettono contemporaneamente, si verifica una collisione.

La collisione può essere rilevata osservando il segnale sulla portante.

Se i dati trasmessi sono diversi da quelli osservati sul canale, significa che un altro nodo sta trasmettendo nello stesso momento.

In caso di collisione:

1. le trasmissioni in conflitto vengono interrotte;
2. i nodi attendono tempi diversi;
3. i messaggi vengono ritrasmessi.

Per ridurre le collisioni, si può limitare il numero di nodi presenti sulla rete.

> ⚠️ Nei bus multiaccesso il conflitto nasce dalla trasmissione contemporanea sullo stesso mezzo.

---

### **17. Reti ad anello**

In una rete ad anello, la comunicazione può essere regolata da un **token**.

Il token circola nell'anello e abilita il nodo che lo possiede a trasmettere.

Il problema principale è la perdita del token.

Per rilevarla, ogni macchina può usare un tempo massimo di attesa.

Se il token non arriva entro il tempo massimo, si presume che sia stato perso.

La gestione della perdita del token può richiedere un meccanismo di elezione per ricreare il token e farlo ripartire nella rete.

> 📌 Nelle reti ad anello il problema non è la collisione sul bus, ma la perdita o gestione scorretta del token.

---

### **18. Sintesi**

| Aspetto | Funzione |
|---|---|
| **Identificazione** | Individua risorse e processi tramite host e identificatore locale |
| **Risoluzione dei nomi** | Traduce nomi simbolici in indirizzi utilizzabili |
| **Caching dei nomi** | Riduce i tempi di risoluzione |
| **Instradamento statico** | Percorso fisso e non adattabile |
| **Instradamento virtuale** | Percorso fissato per una sessione |
| **Instradamento dinamico** | Percorso scelto per ogni messaggio |
| **Gateway** | Collega reti e può convertire protocolli |
| **Router** | Instrada messaggi tra reti |
| **Pacchetti** | Porzioni del messaggio da inviare e riassemblare |
| **Commutazione di circuito** | Collegamento fisso per tutta la sessione |
| **Commutazione di messaggio** | Collegamento temporaneo per un messaggio |
| **Commutazione di pacchetto** | Invio separato dei pacchetti |
| **Bus multiaccesso** | Gestione collisioni e ritrasmissioni |
| **Rete ad anello** | Gestione token e perdita del token |

---

### **19. Conclusione**

La gestione della comunicazione in rete richiede diversi livelli di controllo.

Prima bisogna identificare le risorse e risolvere i nomi simbolici.

Poi occorre scegliere un percorso di instradamento verso il destinatario.

Infine, i messaggi devono essere divisi in pacchetti, inviati, eventualmente riordinati e riassemblati, gestendo errori, perdite e conflitti.

Questi meccanismi costituiscono la base operativa della comunicazione nei sistemi distribuiti.
