# **Lezione 6: Switched Ethernet**

### **1. Dall’Ethernet condivisa all’Ethernet commutata**

Le prime reti Ethernet utilizzavano un **mezzo condiviso** (cavo coassiale o hub), dove tutti i nodi ricevevano tutti i frame.  
Questo modello, seppur semplice, generava **collisioni frequenti** e **limiti di banda**, poiché solo un nodo alla volta poteva trasmettere.

L’evoluzione naturale fu l’introduzione dello **switch Ethernet**, un dispositivo in grado di:

- **filtrare** i frame in base al loro indirizzo MAC,
    
- **inoltrare** i dati solo verso la porta corretta,
    
- **eliminare le collisioni** dividendo la rete in più **domini di collisione indipendenti**.
    

---

### **2. Il principio dello switching**

L’operazione di **switching** consiste nel determinare **su quale porta di uscita** deve essere inviato ciascun frame Ethernet, in base all’**indirizzo MAC di destinazione**.

![](imgs/Pasted%20image%2020260212151613.png)

In questo modo, due comunicazioni distinte (ad esempio A→B e C→D) possono avvenire **in parallelo**, senza interferire, migliorando drasticamente le **prestazioni complessive** della rete.

La velocità della rete commutata non si misura solo in bit per secondo, ma anche in **frame recapitati al secondo**: più cammini paralleli → maggiore throughput effettivo.

---

### **3. VLAN: Ethernet virtuali**

Gli switch moderni possono **suddividere le porte in gruppi logici** detti **VLAN (Virtual LAN)**.  
Ogni VLAN funziona come una rete Ethernet indipendente:

- gli host collegati alla stessa VLAN possono comunicare direttamente;
    
- host appartenenti a VLAN diverse **non possono comunicare** senza passare attraverso un router o un layer superiore.
    

Questo consente di **segmentare logicamente la rete** senza modificare la topologia fisica, migliorando sicurezza e gestione.

---

### **4. Apprendimento degli indirizzi MAC**

Quando uno switch riceve un frame, analizza l’**indirizzo MAC sorgente** e lo **associa alla porta** da cui il frame è arrivato.  
Crea quindi una voce nella propria tabella interna del tipo:

```
<Indirizzo MAC> → <Porta>
```

Questa tabella è salvata in una memoria speciale chiamata **CAM (Content Addressable Memory)**.

#### **Funzionamento della CAM**

La CAM è una memoria in cui è possibile cercare un valore in modo **diretto e istantaneo**:

- inserendo un indirizzo MAC, lo switch trova immediatamente la porta corrispondente;
    
- se l’indirizzo non è presente, lo switch **inoltra il frame su tutte le porte** (flooding), così che il destinatario possa rispondere e permettere allo switch di “imparare” la corretta associazione.
    

---

### **5. Esempio di tabella MAC**

Ogni switch mantiene una **tabella dinamica** che si aggiorna automaticamente:

|Indirizzo MAC|Porta|
|---|---|
|00:1A:2B:3C:4D:5E|Gi4/29|
|00:1B:44:11:3A:B7|Gi7/8|
|...|...|

![](imgs/Pasted%20image%2020260212155051.png)

Se due dispositivi vogliono comunicare (es. Gi4/29 ↔ Gi7/8), lo switch **attiva temporaneamente un cammino** tra le due porte.  
Tuttavia, su grandi switch con centinaia di porte, **non è possibile collegarle tutte simultaneamente**: occorre gestire le connessioni in modo ottimizzato per evitare colli di bottiglia interni.

---

### **6. Verifica della connessione al proprio switch**

Su sistemi **Unix/Linux**, è possibile individuare la propria interfaccia di rete e il suo indirizzo MAC con il comando:

```
ifconfig -a
```

Dopodiché, collegandosi allo switch (via terminale o SSH), si può consultare la tabella CAM per verificare a quale porta è connesso il proprio computer:

```
show mac address-table | include <indirizzo MAC>
```

Questo comando mostra la riga corrispondente nella tabella MAC dello switch.

---

### **7. Tipi di switch**

Gli switch Ethernet si dividono in due grandi categorie.

#### **a) Switch a configurazione fissa**

- Hanno un **numero fisso di porte** (fino a 48, tipicamente).
    
- Sono compatti, economici e usati in reti locali o di reparto.
    
- Alcuni modelli possono essere **impilati (stacked)** per aumentare il numero di porte, ma la **banda di collegamento tra gli switch impilati** è limitata (es. 32 Gbps), quindi non sempre garantisce piena prestazione.
    

#### **b) Switch modulari**

- Possiedono **architetture interne scalabili** con slot per schede aggiuntive di porte.
    
- Possono raggiungere capacità di **centinaia di Gbps** (es. 720 Gbps o più).
    
- Sono impiegati nei **data center** e nelle **reti backbone**.
    
- Richiedono sistemi di **alimentazione e raffreddamento dedicati**, con ingombri notevoli.
    

---

### **8. La negoziazione automatica (Auto-Negotiation)**

Gli standard Ethernet a **100 Mbps (Fast Ethernet)** e **1 Gbps (Gigabit Ethernet)** prevedono una fase di **negoziazione automatica** tra lo switch e la scheda di rete dell’host.

- È lo **switch** che avvia la negoziazione;
    
- si stabiliscono parametri come **velocità (10/100/1000 Mbps)** e **modalità duplex (half/full)**;
    
- se l’interfaccia non risponde, lo switch presume che sia una vecchia scheda **10 Mbps half-duplex** e adatta la connessione di conseguenza.
    

Questo meccanismo garantisce compatibilità tra **vecchie e nuove generazioni di Ethernet**.

---

### **9. L’aumento di velocità e i vincoli fisici**

L’aumento di velocità da 10 Mbps a 100 Mbps e 1 Gbps non è stato ottenuto solo migliorando l’hardware, ma anche **riducendo la lunghezza massima del collegamento** tra host e switch.

Il motivo è legato al **tempo di propagazione ($T$)** del segnale:

- il tempo di trasmissione di un frame deve sempre essere **maggiore di $2T$**,  
    in modo che eventuali collisioni (nelle versioni non full-duplex) possano ancora essere rilevate.
    

Se si riduce di 10 volte il tempo di trasmissione (da 10 Mbps → 100 Mbps), ma la velocità di propagazione resta la stessa, bisogna **ridurre di 10 volte la lunghezza del collegamento** per mantenere la stessa proporzione.

$$  
v = \frac{d}{T} \Rightarrow d \propto T  
$$

👉 **Conclusione:**  
più alta è la velocità, **più breve deve essere la distanza massima** tra host e switch.  
Ecco perché **Ethernet veloce = distanze brevi!**

---

### **10. Sintesi concettuale**

- Lo **switch Ethernet** elimina le collisioni e aumenta il throughput segmentando la rete.
    
- Ogni frame è inoltrato in base alla tabella **MAC → Porta** salvata nella **CAM**.
    
- Le **VLAN** permettono di creare reti logiche indipendenti sulla stessa infrastruttura.
    
- Gli **switch modulari** dominano i data center, mentre quelli fissi servono le reti locali.
    
- La **negoziazione automatica** assicura compatibilità tra dispositivi di generazioni diverse.
    
- Aumentare la **velocità di Ethernet** richiede ridurre la **lunghezza massima dei collegamenti** per mantenere l’integrità dei frame.