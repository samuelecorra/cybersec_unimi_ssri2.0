# **Lezione 4: OSPF avanzato**

---

### **1. Il messaggio HELLO**

In OSPF, ogni router che partecipa al protocollo invia periodicamente un **messaggio HELLO**, detto anche **LSP (Link State Packet)**, per scoprire e mantenere la lista dei router vicini.

- Ogni router invia pacchetti HELLO ai propri **vicini diretti**.
    
- I vicini rispondono con un altro pacchetto HELLO, confermando la connessione.
    
- In questo modo, ciascun router **stabilisce chi sono i suoi adiacenti attivi**.
    

Il periodo di invio, detto **HELLO time**, è di solito compreso tra **10 e 30 secondi**.  
Se un router non riceve più HELLO dal vicino entro un tempo massimo (**Dead Interval**, circa quattro volte il valore di HELLO time), lo considera **non più raggiungibile** e ne rimuove le rotte.

> L’HELLO è la “stretta di mano” costante che mantiene in vita le relazioni tra router OSPF.

---

### **2. Flooding affidabile**

OSPF utilizza una tecnica di **flooding affidabile** per diffondere le informazioni sui collegamenti (Link State) in tutta la rete.

Ogni pacchetto LSP (Link State Packet) contiene:

- l’**ID del router** che ha generato il messaggio,
    
- la **lista dei vicini diretti** e il **costo** di ciascun collegamento,
    
- un **numero di sequenza**,
    
- e un **tempo di vita (TTL)**.
    

Ciascun elemento della lista è chiamato **LSA (Link State Advertisement)**, e rappresenta una “fotografia” dello stato di un collegamento.

Quando un router riceve un LSP:

1. **verifica il numero di sequenza** per capire se è più recente rispetto a quello memorizzato;
    
2. se lo è, **aggiorna la propria copia** e **propaga** il nuovo LSA a tutti i vicini (tranne a quello da cui l’ha ricevuto);
    
3. in caso contrario, **lo scarta**.
    

> Questo meccanismo garantisce che ogni router mantenga una visione coerente e aggiornata della topologia di rete.

---

### **3. Il numero di sequenza**

Il **numero di sequenza** è un contatore a 32 bit che identifica la versione più recente di ciascun LSA.

- Ogni volta che un router genera un nuovo LSA, **incrementa il numero di sequenza**.
    
- Poiché il contatore è a 32 bit, il valore **non torna mai a zero**.
    
- Se un LSA ricevuto ha un numero di sequenza **maggiore**, viene accettato; se è **uguale o minore**, viene **scartato**.
    

> In questo modo OSPF evita di diffondere informazioni obsolete o duplicate, mantenendo la rete stabile e coerente.

---

### **4. Scambio regolare di LSA**

I router OSPF si scambiano LSA in modo **bidirezionale e continuo**.  
Esempio:

![](imgs/Pasted%20image%2020260225154211.png)

- Il router `10.1.10.2` richiede esplicitamente tutti gli LSA da `10.1.10.1`.
    
- `10.1.10.1` invia le informazioni richieste.
    
- Se `10.1.10.2` ha un’informazione più recente su un altro nodo (es. `10.0.1.6`), la invia a `10.1.10.1` con **numero di sequenza superiore**.
    

> Così, ogni router contribuisce ad aggiornare gli altri, mantenendo sincronizzata la conoscenza collettiva della rete.

---

### **5. Il campo TTL (Time To Live)**

Ogni LSA ha un **tempo di vita limitato (TTL)**.  
Ad ogni secondo o trasmissione, il TTL viene **decrementato**.  
Quando il valore raggiunge **zero**, il LSA viene **scartato** e rimosso da tutti i router.

Procedura:

1. Quando un router scarta un LSA, **invia a tutti un nuovo LSA con TTL = 0**.
    
2. Gli altri router, ricevuto questo segnale, **cancellano a loro volta** l’informazione obsoleta.
    

> Il TTL serve a “pulire” periodicamente la rete da informazioni vecchie o non più valide.

---

### **6. Struttura dell’intestazione OSPF**

Un pacchetto OSPF contiene un’intestazione con i seguenti campi principali:

![](imgs/Pasted%20image%2020260225154237.png)

|Campo|Descrizione|
|---|---|
|**Versione**|Versione del protocollo OSPF.|
|**Tipo**|Identifica il tipo di messaggio (vedi sotto).|
|**Lunghezza**|Dimensione complessiva del pacchetto.|
|**Indirizzo sorgente**|IP del router che invia il pacchetto.|
|**Area ID**|Identifica l’area OSPF di provenienza.|
|**Checksum**|Controllo d’errore sul pacchetto.|
|**Autenticazione**|Campo per la verifica del mittente.|

#### **Tipi di pacchetti OSPF**

|Tipo|Significato|
|---|---|
|**1**|HELLO – rilevamento e mantenimento dei vicini|
|**2**|Database Description – descrizione del database|
|**3**|Link State Request – richiesta dello stato dei collegamenti|
|**4**|Link State Update – aggiornamento sullo stato dei collegamenti|
|**5**|Link State Acknowledgment – conferma di ricezione|

> Questi messaggi permettono a OSPF di sincronizzare costantemente le informazioni tra router in modo affidabile e preciso.

---

### **7. Conclusione**

L’**OSPF avanzato** si basa su tre pilastri:

1. **HELLO** per la scoperta e il mantenimento dei vicini,
    
2. **Flooding affidabile** per la diffusione coerente delle informazioni,
    
3. **Controllo tramite numero di sequenza e TTL** per la coerenza e la pulizia del database.
    

> Insieme, questi meccanismi fanno di OSPF un protocollo **rapido, stabile e scalabile**, adatto a reti di grandi dimensioni e backbone IP complessi.


---

![](imgs/Pasted%20image%2020251125073033.png)

