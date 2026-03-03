# **Lezione 8: Controllo della congestione**

---

### **1. Introduzione**

Il **controllo della congestione** è un meccanismo fondamentale delle reti TCP/IP che serve a **mantenere l’equilibrio tra la quantità di dati immessa nella rete e la capacità effettiva della rete stessa** di gestirli.

> Senza un controllo adeguato, troppi pacchetti inviati simultaneamente possono saturare router e collegamenti, portando a ritardi, perdite e — nei casi estremi — al **collasso della rete**.

---

### **2. Congestione: definizione informale**

Si parla di **congestione** quando:

- **troppe sorgenti** inviano **troppi dati**,
    
- **troppo velocemente**,
    
- per una rete che **non è in grado di gestire tale traffico complessivo**.
    

In questa situazione:

- i **router si sovraccaricano**;
    
- i **buffer si riempiono**;
    
- alcuni **pacchetti vengono scartati**;
    
- i **tempi di attesa aumentano** per tutti.
    

---

### **3. Differenza tra controllo di flusso e controllo di congestione**

È fondamentale distinguere i due concetti:

|**Tipo di controllo**|**Scopo**|**Agisce su**|**Livello di intervento**|
|---|---|---|---|
|**Controllo di flusso**|Evita che il **mittente sovraccarichi il destinatario**|Mittente ↔ Destinatario|End-to-end (TCP)|
|**Controllo di congestione**|Evita che **l’intera rete si sovraccarichi**|Tutti i mittenti ↔ rete|Globale (rete)|

> In breve: il **controllo di flusso** protegge un ricevente singolo, mentre il **controllo di congestione** protegge la rete nel suo complesso.

---

### **4. Come gestire la congestione**

Esistono due grandi strategie, a seconda del tipo di rete.

#### **4.1. Rete orientata alla connessione**

- L’azione è di tipo **ex-ante** (preventiva).
    
- Le risorse vengono **riservate in anticipo** (es. banda, buffer, slot temporali).
    
- È tipico dei sistemi con **circuito virtuale**, come ATM o Frame Relay.
    

#### **4.2. Rete non orientata alla connessione (come Internet)**

- L’azione è **ex-post** (correttiva).
    
- Non si riserva nulla in anticipo: la congestione viene **rilevata quando avviene**, e la rete o il mittente **reagiscono** di conseguenza.
    

> Il controllo della congestione in TCP appartiene a questa seconda categoria.

---

### **5. Rilevazione della congestione**

La congestione può essere **rilevata in due modi**:

#### **5.1. Notifica esplicita**

- I router **avvisano direttamente** i mittenti modificando le intestazioni dei pacchetti.
    
- Questa tecnica è detta **ECN (Explicit Congestion Notification)**.
    
    - Bit specifici nel pacchetto IP o TCP segnalano la presenza di congestione lungo il percorso.
        
    - Gli host finali riducono la velocità di trasmissione.
        

#### **5.2. Autodiagnosi (rilevazione implicita)**

- I mittenti **si accorgono da soli** che c’è congestione osservando:
    
    - **perdita di pacchetti** (nessun ACK ricevuto → timeout o ritrasmissioni),
        
    - **ritardo crescente** negli ACK (aumento del tempo di risposta).
        

> TCP interpreta la **perdita o il ritardo** come un segnale implicito di congestione e riduce la propria finestra di invio.

---

### **6. Modelli di congestione: primi esperimenti**

Per capire come la congestione influenza le prestazioni, si considerano alcuni **modelli di rete semplificati**.

---

#### **6.1. Modello 1 – Due mittenti, due destinatari, un router (buffer infinito)**

- Ogni mittente invia pacchetti verso il proprio destinatario attraverso **un unico router comune**.
    
- Non ci sono ritrasmissioni.
    
- Quando la congestione aumenta:
    
    - i pacchetti **restano in coda più a lungo**;
        
    - si ottiene comunque **il massimo throughput possibile**.
        
![](imgs/Pasted%20image%2020260225145532.png)

> In teoria, con buffer infiniti non si perde alcun pacchetto, ma i ritardi diventano enormi.

![](imgs/Pasted%20image%2020260225145620.png)

---

#### **6.2. Modello 2 – Con ritrasmissione e buffer finiti**

- Stesso schema, ma ora i buffer hanno **capacità limitata**.
    
- Quando si riempiono, **i pacchetti vengono scartati**.
    
- I mittenti ritrasmettono automaticamente i pacchetti persi.

![](imgs/Pasted%20image%2020260225145650.png)

##### **Effetti osservati**

- Maggiore **lavoro complessivo** per ottenere lo stesso risultato.
    
- **Ritrasmissioni inutili** (spesso il pacchetto originale non era perso, solo in ritardo).
    
- La rete inizia a trasportare **più copie dello stesso pacchetto** → efficienza complessiva ridotta.
    

---

### **7. Capacità di upstream sprecata**

Con più mittenti e percorsi **multi-hop**, il problema diventa ancora più serio.

#### **Scenario**

- Quattro mittenti trasmettono simultaneamente.
    
- Ogni pacchetto attraversa più router.
    
- Quando un router intermedio è congestionato e scarta pacchetti, la **capacità utilizzata a monte (upstream)** è **persa inutilmente**.

![](imgs/Pasted%20image%2020260225145742.png)

> L’intero lavoro di trasmissione svolto fino al punto di congestione è **sprecato**, poiché il pacchetto scartato non arriverà mai a destinazione.

In termini di throughput:  
$$  
A = 0  
$$  
(dove $A$ è la quantità effettiva di informazioni consegnate).

![](imgs/Pasted%20image%2020260225145803.png)

---

### **8. Effetti complessivi della congestione**

Senza un controllo adeguato:

- la **rete collassa**: la quantità di pacchetti immessi cresce, ma **quelli che arrivano realmente a destinazione diminuiscono** dopo una certa soglia;
    
- ogni pacchetto scartato **rende inutili le risorse consumate** lungo tutto il percorso (banda, buffer, CPU dei router);
    
- i **ritardi aumentano**, i **timeout scattano**, e le **ritrasmissioni** aggravano ulteriormente la congestione.
    

---

### **9. La crisi del 1987**

Nel 1987, Internet subì un grave **collasso dovuto alla congestione**.  
Aumentando il traffico, il numero di pacchetti trasmessi cresceva — ma il numero di pacchetti **effettivamente consegnati** crollava.

Il fenomeno venne studiato da **Van Jacobson** (Lawrence Berkeley Labs), che sviluppò i **moderni algoritmi di controllo della congestione TCP**, introducendo:

- **Slow Start**,
    
- **Congestion Avoidance**,
    
- **Fast Retransmit**,
    
- **Fast Recovery**.
    

> Questi algoritmi formarono la base del TCP moderno, tuttora utilizzato con varianti (Reno, Tahoe, Cubic…).

---

### **10. Conclusione**

Il **controllo della congestione** è una componente vitale della rete TCP/IP:

- protegge la rete dal collasso globale,
    
- riduce sprechi di risorse,
    
- e stabilizza le prestazioni dei collegamenti.
    

> Il suo obiettivo non è soltanto “limitare la velocità”, ma **mantenere il flusso ottimale**, adattandosi dinamicamente alle condizioni della rete per garantire un servizio affidabile e continuo.