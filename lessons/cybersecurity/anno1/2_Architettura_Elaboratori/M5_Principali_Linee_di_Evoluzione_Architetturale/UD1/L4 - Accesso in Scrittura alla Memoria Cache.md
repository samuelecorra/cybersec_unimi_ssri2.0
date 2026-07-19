## ***Lezione 4: Accesso in scrittura alla memoria cache***

---

> 📌 Questa lezione rielabora integralmente le pagine 23–25 di `M5doc.pdf`: coerenza fra cache e memoria di lavoro, politiche Store Thru e Store In, bit di modifica e sostituzione dei blocchi sporchi.

### **1. Il problema delle copie**

Una lettura non modifica lo stato: se la cache contiene la parola richiesta, può semplicemente restituirla. Una scrittura produce invece un valore nuovo e pone una domanda essenziale:

> quale copia deve essere aggiornata: quella nella cache, quella nella memoria di lavoro oppure entrambe?

Il PDF presenta due strategie:

- **Store Thru**, denominazione corrispondente al comune *write-through*;
- **Store In**, denominazione corrispondente al comune *write-back*.

Le due politiche stabiliscono quando propagare verso la memoria di lavoro una modifica effettuata dalla CPU.

### **2. Politica Store Thru o write-through**

Con Store Thru ogni scrittura aggiorna contemporaneamente:

$$
MC[blocco,offset]\leftarrow dato,
$$

$$
MdL[indirizzo]\leftarrow dato.
$$

La cache viene “attraversata”: il livello inferiore riceve subito la stessa modifica.

#### **2.1. Vantaggi**

- Cache e memoria di lavoro restano sempre coerenti.
- La sostituzione non richiede di salvare il blocco espulso.
- Il controllo è semplice: la cache non contiene una versione più recente della MdL.
- Altri componenti che leggono la memoria di lavoro osservano subito il valore aggiornato.

#### **2.2. Svantaggio**

Ogni scrittura paga il tempo del livello più lento. Anche in caso di hit, la CPU deve attendere o disporre di un buffer che assorba temporaneamente la scrittura. La cache accelera le letture, ma non elimina il traffico di scrittura verso MdL.

Il PDF considera la soluzione accettabile perché, tipicamente:

- un risultato richiede più operandi in ingresso e quindi più letture che scritture;
- ogni istruzione richiede almeno il fetch, che è una lettura;
- le istruzioni di memorizzazione sono meno numerose delle operazioni che leggono istruzioni e dati.

### **3. Politica Store In o write-back**

Con Store In una scrittura che colpisce in cache aggiorna soltanto la copia in MC:

$$
MC[blocco,offset]\leftarrow dato.
$$

La memoria di lavoro resta temporaneamente invariata. La scrittura verso MdL viene rinviata fino a quando il blocco deve essere sostituito.

#### **3.1. Vantaggi**

- gli hit in scrittura usano la velocità della cache;
- più modifiche allo stesso blocco possono essere aggregate in un'unica riscrittura;
- si riduce il traffico sul collegamento verso la memoria di lavoro.

#### **3.2. Svantaggi**

- cache e MdL possono contenere versioni differenti;
- la sostituzione può richiedere un trasferimento aggiuntivo;
- il gestore deve conservare metadati e garantire la coerenza;
- un miss può dover prima salvare il blocco vittima e poi importare quello richiesto.

### **4. Il bit di modifica**

A ogni linea viene associato un **bit di modifica** `M`, detto anche **dirty bit**:

- quando un blocco fresco viene caricato da MdL, `M ← 0`;
- quando la CPU scrive nella linea, `M ← 1`;
- `M=1` indica che la cache contiene una copia più recente della memoria di lavoro.

Prima di sostituire la linea:

$$
M=0\quad\Longrightarrow\quad\text{il blocco può essere scartato},
$$

$$
M=1\quad\Longrightarrow\quad\text{il blocco deve essere scritto in MdL}.
$$

Dopo il salvataggio e il caricamento del nuovo blocco, il dirty bit torna a 0.

### **5. Sequenza di sostituzione in write-back**

Supponiamo che un miss scelga una linea vittima:

1. si legge il dirty bit;
2. se vale 1, tag e indice ricostruiscono l'indirizzo MdL del blocco vittima;
3. l'intero blocco viene scritto nel livello inferiore;
4. si preleva il blocco richiesto;
5. si aggiornano dati e tag della linea;
6. si azzera il dirty bit;
7. si completa l'accesso originario.

Il costo del miss dipende quindi dallo stato della vittima. Un blocco pulito richiede soltanto il riempimento; uno sporco richiede prima il write-back.

### **6. Scrittura su hit e su miss**

Store Thru e Store In descrivono **quando** il dato raggiunge il livello inferiore. Una scelta distinta stabilisce che cosa fare se la scrittura produce un miss:

- con **write-allocate** si carica il blocco e poi lo si modifica in cache;
- con **no-write-allocate** la scrittura viene inviata al livello inferiore senza riempire la linea.

Il PDF si concentra sul primo problema e non assegna esplicitamente una di queste due politiche. Non bisogna quindi confondere write-through/write-back con write-allocate/no-write-allocate.

### **7. Confronto**

| Aspetto | Store Thru / write-through | Store In / write-back |
| --- | --- | --- |
| Aggiornamento MdL | a ogni scrittura | all'espulsione del blocco sporco |
| Coerenza immediata | sì | no, serve gestione |
| Dirty bit | non necessario | necessario |
| Traffico verso MdL | maggiore | ridotto |
| Hit in scrittura | può essere penalizzato | rapido |
| Miss con vittima sporca | nessun salvataggio | richiede write-back |
| Complessità | minore | maggiore |

### **8. Criterio di scelta**

La scelta dipende dal valore prestazionale della cache e dal costo della complessità. Il PDF osserva che una L1 molto piccola, veloce e preziosa tende a richiedere una politica che ne sfrutti al massimo le prestazioni, giustificando l'hardware aggiuntivo del write-back.

> ⚠️ “Coerente” non significa soltanto che la CPU ottenga il valore corretto. In un sistema con DMA, più core o altre cache, bisogna anche impedire che altri agenti osservino copie obsolete: il dirty bit è necessario al write-back, ma non esaurisce da solo tutti i problemi di coerenza di un sistema moderno.

> ✅ Store Thru privilegia semplicità e allineamento immediato fra cache e MdL. Store In privilegia gli hit e riduce il traffico, ma richiede un dirty bit e il salvataggio delle linee modificate prima della loro sostituzione.
