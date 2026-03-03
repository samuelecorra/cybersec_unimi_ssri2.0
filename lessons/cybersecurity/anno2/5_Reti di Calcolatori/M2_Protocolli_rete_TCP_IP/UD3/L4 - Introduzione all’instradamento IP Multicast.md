## **Lezione 4: Introduzione all’instradamento IP Multicast**

### **1. Cos’è l’instradamento multicast**

L’**instradamento multicast** è una tecnica che consente di **inviare lo stesso pacchetto IP a più destinatari contemporaneamente**, senza duplicarlo per ognuno di essi.  
A differenza dell’unicast (uno a uno) e del broadcast (uno a tutti), il multicast permette una comunicazione **uno a molti**, ottimizzando il traffico di rete.

Un singolo pacchetto viene inviato a un **indirizzo di gruppo** che rappresenta un insieme di host interessati allo stesso flusso di dati (ad esempio, una trasmissione video in diretta o una conferenza online).

---

### **2. La rete MBONE**

#### **2.1 Origine e significato**

La **MBONE (Multicast Backbone)** è una rete **virtuale** creata nei primi anni ’90 per **sperimentare il multicast su Internet**.  
Si tratta di una dorsale logica che si **appoggia a Internet**, ma collega solo i router e le sottoreti che supportano il multicast.

#### **2.2 Struttura**

- È composta da “**isole multicast**”: porzioni di Internet in grado di gestire il traffico multicast.
    
- Queste isole sono collegate da **tunnel virtuali punto-punto**, che consentono il passaggio dei pacchetti multicast anche attraverso segmenti di rete che **non supportano il multicast**.
    

![](imgs/Pasted%20image%2020251211155510.png)

> In altre parole, MBONE crea un “Internet parallelo” basato su router compatibili con il multicast, interconnessi da tunnel che aggirano i nodi tradizionali.

---

### **3. Funzionamento dei tunnel multicast**

I **tunnel MBONE** funzionano attraverso un meccanismo di **incapsulamento IP-in-IP**.

1. Quando un pacchetto multicast deve attraversare una parte di Internet che non supporta il multicast, viene **incapsulato** dentro un normale pacchetto unicast IP.
    
2. All’ingresso del tunnel, il router multicast aggiunge un nuovo **header IP esterno** (con numero di protocollo 4).
    
3. All’uscita del tunnel, l’incapsulamento viene **rimosso**, e il pacchetto originale viene reinserito nel traffico multicast.
    

In questo modo, il pacchetto multicast appare come un **normale pacchetto unicast** ai router intermedi non compatibili.

#### **Riepilogo**

|Fase|Operazione|Effetto|
|---|---|---|
|Entrata nel tunnel|Incapsulamento IP-in-IP|Il pacchetto multicast diventa un unicast|
|Attraversamento|Transito attraverso Internet “non-multicast”|Router standard non lo scartano|
|Uscita|Rimozione dell’incapsulamento|Il pacchetto torna multicast|

La serie di router multicast, le loro sottoreti locali e i tunnel di connessione costituiscono la **dorsale multicast MBONE**.

---

### **4. Indirizzamento multicast**

I pacchetti multicast vengono inviati a **indirizzi IP di classe D**, che rappresentano **gruppi di destinatari** anziché singoli host.

#### **4.1 Struttura degli indirizzi di classe D**

Gli indirizzi di classe D hanno i **quattro bit più significativi** impostati a `1110`.  
I restanti 28 bit identificano il **gruppo multicast**.

$$
\text{Indirizzo Classe D: } 1110\,xxxx\,xxxx\,xxxx\,xxxx\,xxxx\,xxxx\,xxxx
$$

Espressi nella notazione decimale puntata, gli indirizzi di gruppo multicast variano da:

$$
224.0.0.0 \; \text{ a } \; 239.255.255.255
$$

#### **4.2 Range riservati (RFC 5771)**

|Range IP|Scopo|
|---|---|
|224.0.0.0 – 224.0.0.255|Indirizzi locali alla rete (non instradabili)|
|224.0.1.0 – 238.255.255.255|Multicast globale (instradabile su Internet)|
|239.0.0.0 – 239.255.255.255|Multicast amministrativo locale (privato)|

---

### **5. Mappatura tra indirizzi IP multicast e MAC Ethernet**

Per permettere alle schede di rete di ricevere pacchetti multicast, l’IANA ha riservato **una porzione dello spazio di indirizzi Ethernet (MAC)** dedicata al multicast.

#### **5.1 Blocco riservato IANA**

Tutti gli indirizzi MAC multicast riservati all’uso IP iniziano con i 24 bit:

$$  
01-00-5E  
$$

#### **5.2 Regola di conversione**

Per ottenere l’indirizzo MAC corrispondente a un indirizzo IP multicast di classe D:

- si prendono i **23 bit meno significativi** dell’indirizzo IP;
    
- e li si inserisce nei **23 bit meno significativi** del blocco MAC `01-00-5E-00-00-00`.

![](imgs/Pasted%20image%2020251211155840.png)

$$  
\text{MAC}_{multicast} = 01:00:5E:xx:xx:xx  
$$

> Questo mapping non è univoco: più indirizzi IP multicast possono corrispondere allo stesso indirizzo MAC, ma il rischio di collisione è basso e gestito a livello software.

---

### **6. Trasmissione e consegna dei datagram multicast**

Quando il mittente e i destinatari appartengono alla **stessa LAN**, la trasmissione multicast è relativamente semplice:

1. La **scheda di rete del mittente** invia un frame Ethernet con **indirizzo MAC multicast**.
    
2. Tutte le schede riceventi vedono il frame, ma **solo quelle che si sono iscritte al gruppo multicast** lo accettano.
    
3. Le schede interessate notificano al livello IP locale che vogliono ricevere i pacchetti destinati a quel gruppo.
    

> In questo modo, si evita di inondare la rete con dati non richiesti, mantenendo efficiente la comunicazione.

---

### **7. Esempi di applicazione**

Le tecniche multicast vengono utilizzate in diversi contesti:

- **Streaming audio/video** in tempo reale (videoconferenze, IPTV);
    
- **Aggiornamenti simultanei** su più host (es. aggiornamenti software);
    
- **Distribuzione di dati in rete** (repliche di database, giochi online, servizi IoT di gruppo).
    

---

### **8. Conclusione**

L’**instradamento IP multicast** è una soluzione elegante per ridurre il traffico e migliorare l’efficienza delle comunicazioni “uno-a-molti”.  
La rete **MBONE** ha rappresentato il primo esperimento pratico di questa tecnologia su scala Internet, basata su:

- incapsulamento IP-in-IP;
    
- indirizzi di classe D;
    
- e mapping tra IP multicast e indirizzi MAC riservati.
    

Oggi, i principi introdotti con MBONE sopravvivono nei moderni protocolli multicast come **IGMP (Internet Group Management Protocol)** e **PIM (Protocol Independent Multicast)**, che gestiscono in modo dinamico i gruppi e le rotte multicast.