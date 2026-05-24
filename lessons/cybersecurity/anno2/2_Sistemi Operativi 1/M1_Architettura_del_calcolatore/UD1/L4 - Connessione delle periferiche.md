# **M1 UD1 Lezione 4 - Connessione delle periferiche**

### **1. Introduzione**

#### **1.1. Il ruolo delle periferiche nel sistema di elaborazione**

Un calcolatore, per essere utile, deve **interagire con l’esterno**.  
Questa interazione avviene attraverso dispositivi chiamati **periferiche**, che permettono di:

- **inserire dati** (input),
    
- **visualizzare o salvare risultati** (output),
    
- e **scambiare informazioni** con altri sistemi (comunicazione).

Le periferiche comprendono tastiere, schermi, stampanti, dischi, reti, sensori, attuatori, e così via.  
Per farle comunicare con il processore, serve una **connessione logica e fisica** che consenta il trasferimento ordinato dei dati.  
Tale connessione è realizzata tramite un **canale di comunicazione**, intermediato da una **interfaccia di controllo**.

---
### **2. Canale di comunicazione**

#### **2.1. Struttura generale**

Il **canale di comunicazione** è l’insieme dei **collegamenti hardware e protocolli logici** che permettono lo scambio di dati tra la **CPU** e i **dispositivi di I/O**.

Esso comprende:

- le **linee fisiche** (bus dati, bus indirizzi, bus di controllo),
    
- e i **registri di interfaccia** che consentono alla CPU di leggere o scrivere informazioni verso la periferica.

La comunicazione avviene attraverso un dispositivo chiamato **interfaccia** (o controller), che traduce i segnali del processore in comandi comprensibili dalla periferica, e viceversa.

---

#### **2.2. Composizione del canale di comunicazione**

Più precisamente, il canale di comunicazione è costituito da tre elementi che cooperano fra loro:

1. **Elettronica di gestione del canale a bordo della scheda di interfaccia** (lato unità centrale), che dialoga con il bus di I/O;
2. **Cavo di connessione** che collega fisicamente l'interfaccia alla periferica;
3. **Elettronica di gestione del canale a bordo della periferica**, che dialoga con la sua controparte.

![](imgs/Pasted%20image%2020260523221558.png)

Attraverso questo canale fluiscono le informazioni dal sistema di elaborazione alla periferica e viceversa.

L'elettronica a bordo della periferica è a sua volta composta da:

- una **parte di gestione della connessione** (la già citata controparte del canale di comunicazione);
- una **parte di controllo della periferica vera e propria**, che si occupa della movimentazione delle componenti fisiche del dispositivo — ad esempio, nel caso del disco, del mantenimento della rotazione dei piatti e del posizionamento delle testine di lettura e scrittura.

L'insieme dell'**elettronica di controllo** e degli eventuali **dispositivi elettromeccanici** costituisce la **periferica nel suo complesso**.

L'elettronica di gestione della connessione, sia quella a bordo della periferica sia quella a bordo della scheda di interfaccia nell'unità di elaborazione costituisce, insieme al cavo di connessione della periferica all'unità di elaborazione, quello che è noto come canale di comunicazione.  
  
È quello attraverso cui vengono fatte fluire le informazioni dal sistema di elaborazione alla periferica e viceversa.  
  
Per gestire tale canale all'interno dell'unità centrale abbiamo un insieme di dispositivi appositi connessi attraverso il bus di ingresso uscita che costituiscono l'interfaccia della periferica.

Questa contiene un insieme di registri che permettono il colloquio con la periferica, l'invio di informazioni e il ritorno di risultati dell'elaborazione effettuata dalla periferica verso il calcolatore.

---

### **3. Interfaccia nell’unità centrale**

#### **3.1. Funzione dell’interfaccia**

L’interfaccia di I/O è un componente elettronico che si occupa di:

- ricevere i comandi della CPU e trasmetterli alla periferica;
    
- segnalare lo stato della periferica al processore;
    
- sincronizzare il flusso dei dati;
    
- e gestire eventuali errori di comunicazione.

Può essere **integrata nella scheda madre** o **separata**, come nel caso delle schede di rete, schede video o controller esterni.

L'interfaccia è essenzialmente costituita da **due parti**:

- una **parte rivolta verso il calcolatore** (connessa al bus di I/O dell'unità centrale);
- una **parte rivolta verso la periferica** (connessa al cavo che termina sull'elettronica di controllo della periferica).

---

#### **3.2. I registri dell'interfaccia**

Per gestire il canale di comunicazione all'interno dell'unità centrale, l'interfaccia mette a disposizione un insieme di **registri** attraverso cui avviene il colloquio con la periferica e con cui si inviano informazioni e si ricevono risultati dell'elaborazione richiesta.

I registri fondamentali sono **tre**:

$$
\begin{cases}
\textbf{Registro di stato:}~ & \text{indica se la periferica è libera, occupata, pronta o in errore.} \\\\
\textbf{Registro di comando:}~ & \text{contiene l'ordine che la CPU vuole far eseguire all'interfaccia/periferica.} \\\\
\textbf{Registro dati:}~ & \text{contiene il dato o l'informazione da trasferire (in ingresso o in uscita).}
\end{cases}
$$

![](imgs/Pasted%20image%2020260523222320.png)

##### **Flusso operativo del colloquio CPU ↔ interfaccia ↔ periferica**

Il funzionamento tipico di una richiesta di I/O è il seguente:

1. L'unità centrale chiede all'interfaccia di **verificare lo stato della comunicazione** con la periferica, leggendo il contenuto del **registro di stato**.
2. Se la connessione è corretta e la periferica è libera, il processore può chiedere l'esecuzione di un comando di I/O: **deposita nel registro dati** l'informazione (l'ordine o il dato) che vuole far pervenire alla periferica.
3. La CPU **scrive nel registro di comando** l'ordine per l'interfaccia di inviare i dati richiesti alla periferica.
4. L'elettronica a bordo dell'interfaccia, riconoscendo l'arrivo del comando, **trasferisce il contenuto del registro dati** alla parte di controllo del canale di comunicazione.
5. Questa elettronica **colloquia con la sua controparte a bordo della periferica** e trasferisce l'ordine richiesto alla periferica.
6. La periferica **interpreta ed esegue il comando** richiesto.
7. Il risultato viene **ritornato all'interno del registro dati** e, una volta completata l'operazione, viene **aggiornato lo stato** del sistema nell'interfaccia.
8. Il processore, **interrogando il registro di stato**, può di nuovo sapere qual è stato il risultato della computazione effettuata e della richiesta di gestione della periferica.

---

### **4. Controllo dell’interfaccia**

#### **4.1. Attesa attiva**

La modalità più semplice di controllo è l’**attesa attiva** (_busy waiting_).  
In questo caso, la CPU **interroga continuamente** l’interfaccia per sapere se la periferica è pronta a ricevere o fornire dati.

Il flusso è il seguente:

$$  
\begin{cases}  
\text{1.}~ \text{La CPU invia un comando alla periferica.} \\\\  
\text{2.}~ \text{La CPU controlla ciclicamente un bit di stato dell’interfaccia.} \\\\  
\text{3.}~ \text{Quando il bit segnala che la periferica è pronta, la CPU effettua il trasferimento.} \\\\  
\text{4.}~ \text{Il ciclo si ripete fino al completamento dell’operazione.}  
\end{cases}  
$$

Questo metodo è molto semplice da implementare, ma **inefficiente**: il processore resta bloccato in attesa, sprecando tempo che potrebbe dedicare ad altre elaborazioni.

---
#### **4.2. Controllo tramite interruzioni**

Per migliorare l’efficienza, si adotta un meccanismo basato sulle **interruzioni (interrupt)**.  
In questo caso, la CPU invia un comando alla periferica e poi **prosegue l’esecuzione di altri processi**, evitando di sprecare tempo in istruzioni di attesa non utili all'evoluzione della computazione applicativa. Eventualmente la CPU può anche **cambiare il programma in esecuzione**, occupandosi nel frattempo di altre attività.

Quando finalmente la parte di gestione del canale di comunicazione, a bordo dell'interfaccia, **modifica il registro di stato** per segnalare il risultato dell'elaborazione, questa modifica del flag genera **automaticamente** un'interruzione verso il processore. La CPU si accorge così che l'operazione è terminata e procede ad **acquisire lo stato dell'operazione** all'interno della **risposta all'interruzione** associata a quella specifica interfaccia, attivando le operazioni conseguenti.

Il comportamento può essere rappresentato così:

$$  
\begin{cases}  
\text{1.}~ \text{La CPU avvia l’operazione di I/O.} \\\\  
\text{2.}~ \text{La CPU continua altre elaborazioni.} \\\\  
\text{3.}~ \text{La periferica genera un interrupt quando l’operazione è completata.} \\\\  
\text{4.}~ \text{La CPU salva il contesto e attiva la routine di gestione dell’interruzione.} \\\\  
\text{5.}~ \text{Terminata la gestione, la CPU riprende l’esecuzione originale.}  
\end{cases}  
$$

Questo metodo consente una **gestione asincrona e parallela delle operazioni di I/O**, liberando il processore dall’attesa attiva.

---
### **5. Trasferimento dei dati**

#### **5.1. Tipologie di trasferimento**

Il trasferimento dei dati tra la CPU e la periferica può avvenire in due modi principali:

$$  
\begin{cases}  
\textbf{A parole}~ & \text{(word-at-a-time): i dati vengono trasferiti uno per volta, sotto il controllo diretto della CPU.} \\\\  
\textbf{A blocchi}~ & \text{(block transfer): i dati vengono trasferiti in gruppi, in modo automatico tramite il DMA.}  
\end{cases}  
$$

---
#### **5.2. Il ruolo del DMA (Direct Memory Access)**

Nel trasferimento a blocchi, interviene un dispositivo chiamato **DMA controller**, che permette a una periferica di leggere o scrivere direttamente in memoria **senza passare dalla CPU**.  
Questo riduce il carico del processore e accelera i trasferimenti di grandi quantità di dati (ad esempio tra disco e memoria).

Il ciclo logico è il seguente:

$$  
\begin{cases}  
\text{1.}~ \text{La CPU programma il controller DMA con l’indirizzo di memoria e la quantità di dati.} \\\\  
\text{2.}~ \text{Il DMA avvia autonomamente il trasferimento.} \\\\  
\text{3.}~ \text{Durante il trasferimento, la CPU può continuare altre attività.} \\\\  
\text{4.}~ \text{A operazione conclusa, il DMA genera un interrupt per segnalare la fine.}  
\end{cases}  
$$

Grazie al DMA, il processore diventa **supervisore** del trasferimento, ma non il suo esecutore diretto.

---
#### **5.3. Periferica mappata in memoria**

Un'ulteriore tecnica per velocizzare le operazioni di trasferimento dei dati consiste nel considerare la periferica **mappata in memoria** (_Memory-Mapped I/O_).

In questo schema, una **porzione dello spazio di indirizzamento** della memoria centrale viene **mappata sulla scheda di interfaccia** della periferica. Ad esempio, una porzione che dovrebbe rispondere a partire dall'indirizzo `F00` esadecimale, anziché essere quella contenuta nella memoria centrale, è quella **contenuta sulla scheda di interfaccia di I/O**.

Quando l'unità centrale chiede di accedere a un indirizzo appartenente a questo blocco:

- **non risponde la memoria centrale**, ma risponde l'**interfaccia di I/O**, fornendo l'accesso alle informazioni corrispondenti a quell'indirizzo;
- il blocco di memoria centrale corrispondente viene quindi **sostituito completamente dal punto di vista operativo** dal blocco analogo sull'interfaccia; **fisicamente la RAM rimane**, ma in quella porzione **non viene utilizzata**.

##### **Flusso operativo**

- In **input** (dati dalla periferica al processore): la porzione di controllo del canale provvede a **caricare nella memoria mappata** sull'interfaccia i dati provenienti dalla periferica. Il processore li legge come se stesse leggendo dalla RAM.
- In **output** (dati dal processore alla periferica): il processore scrive **direttamente in questa memoria mappata**; sarà poi la porzione di controllo del canale a trasferire alla periferica il blocco di dati.

##### **Vantaggio rispetto al DMA classico**

Con questa tecnica si evita il **doppio passaggio** tipico del DMA: non è più necessario scrivere prima i dati nella memoria centrale per poi spostarli, sempre tramite DMA, nell'interfaccia della periferica e da qui infine verso il dispositivo. I dati arrivano direttamente nello spazio mappato dell'interfaccia, riducendo i trasferimenti intermedi.

Ciò permette inoltre di utilizzare le **stesse istruzioni di lettura e scrittura della memoria** per comunicare con le periferiche, senza necessità di istruzioni di I/O dedicate.

---
### **6. Gestione del canale di comunicazione**

#### **6.1. Tipologie di controllo**

Il canale di comunicazione può essere gestito in due modi:

$$  
\begin{cases}  
\textbf{Controllo nell’interfaccia dell’unità centrale:}~ & \text{la CPU gestisce direttamente il trasferimento dei dati.} \\\\  
\textbf{Controllo nella periferica:}~ & \text{la periferica dispone di un proprio processore dedicato (controller intelligente).}  
\end{cases}  
$$

Le periferiche moderne (come dischi o schede di rete) includono spesso microprocessori che eseguono autonomamente le proprie operazioni, segnalando solo gli eventi importanti al sistema operativo.

---
### **7. Gestione della periferica**

#### **7.1. Esecuzione dei comandi a bordo della periferica**

Una volta che il trasferimento delle richieste dal processore all'interfaccia è avvenuto, e una volta che il canale di comunicazione ha fatto pervenire i comandi desiderati alla periferica, è necessario **interpretare ed eseguire** i comandi richiesti dal processore all'interno della periferica stessa.

A bordo della periferica troviamo quindi un'elettronica suddivisa in due porzioni cooperanti:

- una porzione che provvede ad **acquisire i comandi e i dati** provenienti dal processore (la controparte del canale di comunicazione);
- una porzione che è in grado di **analizzare tali richieste, comprenderle e mandarle in esecuzione** sulla periferica, eseguendo le azioni desiderate dal programma in esecuzione.

Al termine dell'attività, l'elettronica della periferica **ritorna i valori** relativi al completamento (con successo o con errore) dell'attività richiesta, inviandoli indietro attraverso il canale di comunicazione fino al registro dati e al registro di stato dell'interfaccia.

---

#### **7.2. Controllo del dispositivo fisico**

Il **sistema operativo** deve coordinare tutte le periferiche per garantire un uso ordinato delle risorse.  
Questo include:

- l’inizializzazione e la configurazione del dispositivo,
    
- la gestione delle code di richieste,
    
- la sincronizzazione degli accessi concorrenti,
    
- la gestione degli errori e delle eccezioni hardware.

Ogni dispositivo è gestito da un **driver**, un modulo software che traduce le chiamate del sistema operativo in comandi comprensibili dall’hardware.

---
### **8. Sintesi finale**

$$  
\begin{cases}  
\textbf{Connessione CPU-periferiche:}~ & \text{realizzata tramite canale di comunicazione e interfacce dedicate.} \\\\  
\textbf{Controllo dell’interfaccia:}~ & \text{può avvenire per attesa attiva o tramite interrupt.} \\\\ 
\textbf{Trasferimento dati:}~ & \text{a parole (CPU diretta) o a blocchi (DMA).} \\  
\textbf{Gestione del canale:}~ & \text{CPU o periferica possono avere il controllo.} \\\\  
\textbf{Gestione della periferica:}~ & \text{affidata al sistema operativo e ai driver.}  
\end{cases}  
$$

---
### **9. Collegamento con i sistemi operativi**

Il sistema operativo agisce come **intermediario tra hardware e processi**:

- controlla le interfacce di I/O,
    
- gestisce gli interrupt e i DMA,
    
- garantisce la sicurezza e la priorità delle operazioni di input/output.
    

Questo garantisce che le periferiche possano operare in modo **asincrono**, **sicuro** e **indipendente**, pur restando pienamente coordinate con la CPU e la memoria.