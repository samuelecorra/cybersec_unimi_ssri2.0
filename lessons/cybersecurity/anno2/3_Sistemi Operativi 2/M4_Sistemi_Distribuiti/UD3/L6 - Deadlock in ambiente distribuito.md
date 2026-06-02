# **M4 UD3 Lezione 6 - Deadlock in ambiente distribuito**

---

### **1. Introduzione**

In questa lezione viene esteso il problema del **deadlock** al caso di un **sistema distribuito**.

Nel caso di una singola macchina il sistema operativo può osservare direttamente lo stato delle risorse e dei processi. In un ambiente distribuito, invece, ogni macchina possiede solo una vista **locale**:

- conosce i processi e le risorse presenti sul proprio sito;
- può avere informazioni incomplete sulle attese che coinvolgono altre macchine;
- riceve aggiornamenti con ritardi dovuti alla rete.

Per questo motivo la gestione dello stallo distribuito deve affrontare due aspetti:

- **prevenzione**, evitando che si formino cicli di attesa;
- **rilevamento e gestione**, individuando i cicli quando si formano e risolvendoli con rollback o rilascio di risorse.

---

### **2. Prevenzione dello stallo**

La prevenzione dello stallo in ambiente distribuito può essere ottenuta estendendo le tecniche già viste per la macchina singola.

L'idea generale è:

> 📌 Imporre regole sull'assegnazione delle risorse in modo che non possa formarsi una catena ciclica di attese.

Le tecniche principali sono:

- ordinamento globale delle risorse;
- algoritmo del banchiere centralizzato;
- tecniche basate su marche di tempo e rollback.

---

### **3. Ordinamento globale delle risorse**

Una prima tecnica consiste nell'assegnare a tutte le risorse del sistema distribuito un **identificatore univoco**.

Gli identificatori sono ordinati in modo crescente:

$$
R_1 < R_2 < R_3 < \dots < R_n
$$

Un processo può ottenere una nuova risorsa solo se questa ha identificatore maggiore delle risorse che già possiede.

Formalmente, se un processo detiene una risorsa $R_i$, può richiedere una risorsa $R_j$ solo se:

$$
R_i < R_j
$$

Questa regola impedisce la formazione di cicli, perchè le attese possono procedere solo in una direzione dell'ordinamento.

> ✅ Il vantaggio è la semplicità: la tecnica generalizza direttamente il caso centralizzato.

> ⚠️ Il limite è che l'ordinamento globale deve essere condiviso e mantenuto coerente in tutto il sistema distribuito.

---

### **4. Algoritmo del banchiere centralizzato**

Un'altra soluzione consiste nell'usare l'**algoritmo del banchiere** in forma centralizzata.

In questo caso una macchina del sistema distribuito esegue l'algoritmo e decide se concedere o meno le risorse richieste.

Il coordinatore centrale:

- conosce lo stato globale delle risorse;
- valuta se l'assegnazione richiesta mantiene il sistema in uno stato sicuro;
- concede la risorsa solo se non si rischia di arrivare a uno stallo.

Questa soluzione è teoricamente corretta, ma introduce problemi pratici:

- il coordinatore diventa un **collo di bottiglia**;
- tutte le richieste devono passare da un unico punto;
- un guasto del coordinatore può compromettere il servizio;
- le prestazioni si riducono a causa della centralizzazione.

---

### **5. Prevenzione con priorità e rollback**

Un approccio diverso assegna a ogni processo una **priorità**.

La tecnica classica di rilascio anticipato prevede che, se:

- un processo $P$ possiede una risorsa;
- un processo $Q$ con priorità maggiore richiede quella risorsa;

allora:

- $P$ rilascia la risorsa;
- $P$ effettua rollback;
- la risorsa viene assegnata a $Q$.

Questo rompe possibili attese cicliche, ma può generare **starvation**: un processo a bassa priorità potrebbe essere continuamente interrotto.

Per evitare questo problema si usano le **marche di tempo**.

---

### **6. Marche di tempo**

A ogni processo viene associata una marca di tempo.

La marca rappresenta l'anzianità del processo:

- marca più piccola: processo più vecchio;
- marca più grande: processo più giovane.

L'obiettivo è dare progressivamente vantaggio ai processi più vecchi, evitando che vengano continuamente penalizzati.

> 📌 Per evitare starvation, quando un processo effettua rollback non deve ricevere una nuova marca: deve conservare la marca originaria.

In questo modo, dopo ogni rollback, il processo mantiene la propria anzianità e prima o poi riuscirà ad avanzare.

---

### **7. Schema wait-die**

Lo schema **wait-die** non usa il rilascio anticipato della risorsa.

Supponiamo che:

- la risorsa sia detenuta dal processo $P$;
- il processo $Q$ chieda quella risorsa.

Il comportamento è:

| Condizione | Azione |
|---|---|
| $Q$ è più vecchio di $P$ | $Q$ attende |
| $Q$ è più giovane di $P$ | $Q$ effettua rollback |

Quindi:

- un processo vecchio può aspettare un processo giovane;
- un processo giovane non può aspettare un processo vecchio e viene fatto morire.

> ✅ Lo schema evita la starvation se il processo riavviato conserva la marca originaria.

---

### **8. Schema wound-wait**

Lo schema **wound-wait** usa invece il rilascio anticipato.

Supponiamo ancora che:

- la risorsa sia detenuta dal processo $P$;
- il processo $Q$ chieda quella risorsa.

Il comportamento è:

| Condizione | Azione |
|---|---|
| $Q$ è più giovane di $P$ | $Q$ attende |
| $Q$ è più vecchio di $P$ | $Q$ forza il rollback di $P$ |

Quindi:

- un processo giovane aspetta un processo vecchio;
- un processo vecchio può interrompere un processo giovane.

Anche in questo caso la starvation viene evitata mantenendo invariata la marca di tempo dopo il rollback.

---

### **9. Rilevamento dello stallo**

Quando non si previene lo stallo, bisogna rilevarlo.

La tecnica di base usa il **grafo di attesa** o **wait-for graph**.

Nel grafo:

- ogni nodo rappresenta un processo;
- un arco $P_i \rightarrow P_j$ indica che $P_i$ attende una risorsa detenuta da $P_j$.

Se nel grafo esiste un ciclo, allora esiste uno stallo.

In ambiente distribuito il problema è più complesso perché:

- ogni macchina possiede un proprio grafo locale;
- uno stallo può coinvolgere processi posti su macchine diverse;
- nessun nodo possiede automaticamente il grafo globale completo.

---

### **10. Grafi locali e grafo globale**

Ogni macchina mantiene un proprio **grafo di attesa locale**.

Se in un grafo locale esiste un ciclo, allora lo stallo è sicuramente presente almeno su quella macchina.

Se invece non esistono cicli locali, non si può concludere che il sistema sia libero da stallo.

![](imgs/Pasted%20image%2020260602192311.png)

Infatti uno stallo può comparire solo nel grafo ottenuto come unione dei grafi locali:

$$
G_{globale} = G_1 \cup G_2 \cup \dots \cup G_n
$$

Se il grafo globale non contiene cicli, allora non ci sono stalli.

> ⚠️ L'assenza di cicli locali non implica l'assenza di deadlock globale.

---

### **11. Rilevamento centralizzato**

Nel rilevamento centralizzato viene introdotto un **coordinatore centrale**.

Il coordinatore:

- riceve informazioni dai siti locali;
- costruisce il grafo di attesa globale;
- cerca cicli nel grafo globale;
- segnala lo stallo quando viene individuato un ciclo.

Questa soluzione richiede di distinguere tra:

- il **grafo reale**, cioè lo stato effettivo del sistema in un certo istante;
- il **grafo costruito**, cioè il grafo posseduto dal coordinatore sulla base dei messaggi ricevuti.

I due grafi possono differire perchè i messaggi impiegano tempo ad arrivare.

Un algoritmo corretto deve garantire due proprietà:

- se esiste uno stallo, prima o poi deve essere segnalato;
- se viene segnalato uno stallo, il sistema deve essere effettivamente in stallo.

---

### **12. Aggiornamento del grafo centralizzato**

Il grafo globale centralizzato può essere aggiornato in due modi.

Nel primo modo, ogni macchina invia un messaggio al coordinatore ogni volta che:

- viene inserito un arco nel grafo locale;
- viene rimosso un arco dal grafo locale.

Questa soluzione è precisa, ma può produrre molto traffico di rete.

Nel secondo modo, ogni macchina invia un aggiornamento dopo un certo numero di modifiche locali.

In questo caso:

- si riduce il numero di messaggi;
- il grafo globale può essere meno aggiornato;
- il coordinatore lavora su una fotografia approssimata dello stato reale.

![](imgs/Pasted%20image%2020260602192514.png)

Il coordinatore analizza poi il grafo globale e cerca eventuali cicli.

---

### **13. Rilevamento distribuito**

Nel rilevamento distribuito non esiste un coordinatore unico.

Ogni macchina costruisce una porzione del grafo globale usando:

- le attese locali;
- informazioni ricevute dalle altre macchine;
- messaggi di rilevamento inviati quando compare un possibile ciclo distribuito.

Per rappresentare le attese verso risorse esterne si introduce un nodo speciale:

$$
P_{ex}
$$

Il nodo $P_{ex}$ indica che l'attesa coinvolge una risorsa o un processo presente su un'altra macchina.

---

### **14. Cicli con il nodo P_ex**

Nel grafo locale possono verificarsi due casi.

![](imgs/Pasted%20image%2020260602193024.png)

Se esiste un ciclo che **non coinvolge** $P_{ex}$, allora lo stallo è sicuramente locale.

Se invece esiste un ciclo che **coinvolge** $P_{ex}$, allora c'è solo una possibilità di stallo.

In questo caso bisogna verificare se l'attesa esterna prosegue su altre macchine fino a chiudere davvero un ciclo globale.

Quindi:

- ciclo senza $P_{ex}$: deadlock certo;
- ciclo con $P_{ex}$: deadlock possibile, da verificare.

---

### **15. Riduzione dei messaggi nel rilevamento distribuito**

Il rilevamento distribuito può generare molti messaggi.

Per ridurre il sovraccarico si assegna a ogni processo un identificatore univoco nel sistema distribuito.

Quando una macchina $S_i$ scopre un ciclo che coinvolge $P_{ex}$, invia messaggi di rilevamento alle altre macchine solo se:

$$
id(P_{precedente}) < id(P_{successivo})
$$

dove $P_{precedente}$ è il processo che precede $P_{ex}$ nel ciclo e $P_{successivo}$ è quello che lo segue.

Se la condizione non vale, la macchina non avvia il rilevamento e lascia che un'altra macchina individui l'eventuale stallo.

> 💡 Questa regola evita che più siti avviino contemporaneamente rilevamenti ridondanti dello stesso ciclo.

---

### **16. Gestione dello stallo rilevato**

Quando uno stallo viene rilevato, bisogna romperlo.

La soluzione tipica consiste nel selezionare una **vittima** tra i processi coinvolti nel ciclo.

Il coordinatore o l'algoritmo distribuito:

- individua i processi in stallo;
- sceglie un processo vittima;
- impone il rollback della vittima;
- informa le macchine coinvolte;
- libera le risorse necessarie a rompere il ciclo.

I processi che stavano interagendo con la vittima possono dover effettuare a loro volta un rollback parziale, in modo da riportare il sistema a uno stato coerente.

---

### **17. Falsi cicli e rollback inutili**

In ambiente distribuito possono comparire **falsi cicli**.

Un falso ciclo è un ciclo presente nel grafo costruito dall'algoritmo, ma non realmente presente nello stato effettivo del sistema.

Questo può accadere a causa di:

- ritardi nella trasmissione dei messaggi;
- acquisizioni e rilasci di risorse avvenuti mentre il grafo veniva aggiornato;
- informazioni locali arrivate al coordinatore in ordine diverso da quello reale.

Il rischio è eseguire rollback inutili.

Per ridurre questo problema:

- ogni richiesta deve essere identificata in modo univoco;
- si possono usare marche di tempo uniche;
- nel grafo globale devono essere inserite solo richieste non immediatamente soddisfacibili.

Una richiesta può quindi essere identificata come:

$$
\text{richiesta} = (\text{timestamp}, \text{id processo})
$$

---

### **18. Sintesi**

| Tema | Tecnica | Idea principale |
|---|---|---|
| Prevenzione | Ordinamento globale | Impedire cicli imponendo un ordine sulle risorse |
| Prevenzione | Banchiere centralizzato | Assegnare risorse solo se lo stato resta sicuro |
| Prevenzione | Wait-die | I processi vecchi attendono, i giovani fanno rollback |
| Prevenzione | Wound-wait | I processi vecchi possono forzare rollback dei giovani |
| Rilevamento | Grafo di attesa | Un ciclo indica uno stallo |
| Rilevamento centralizzato | Coordinatore unico | Costruisce e analizza il grafo globale |
| Rilevamento distribuito | Nodo $P_{ex}$ | Verifica cooperativa dei cicli distribuiti |
| Gestione | Scelta della vittima | Rollback per rompere il ciclo |

---

### **19. Conclusione**

Il deadlock in ambiente distribuito richiede tecniche più complesse rispetto al caso centralizzato.

La prevenzione cerca di impedire la formazione dello stallo tramite ordinamenti, controllo centralizzato o marche di tempo.

Il rilevamento costruisce invece una vista locale o globale delle attese e cerca cicli nei grafi.

La gestione, infine, rompe lo stallo scegliendo una vittima e applicando rollback coordinati.

> 📌 La difficoltà principale non è solo individuare un ciclo, ma farlo usando informazioni distribuite, ritardate e potenzialmente non perfettamente allineate.
