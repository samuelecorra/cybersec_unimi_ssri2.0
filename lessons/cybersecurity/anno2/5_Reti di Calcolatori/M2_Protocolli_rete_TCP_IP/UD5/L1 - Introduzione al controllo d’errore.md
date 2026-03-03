# **Lezione 1: Introduzione al controllo d’errore**

---

### **1. Introduzione generale**

Quando due **host** comunicano su una rete, il segnale trasmesso può essere alterato da **rumore, interferenze o perdite di pacchetti**.  
Di conseguenza, il messaggio ricevuto può differire da quello inviato.

Per garantire un **trasporto affidabile**, i protocolli di rete devono prevedere **meccanismi di rilevazione e correzione degli errori**, noti come **controllo d’errore**.

Queste tecniche si applicano **a tutti i livelli dello stack di protocolli**, ma in questa lezione ci concentriamo sui **livelli 3 (rete)** e **4 (trasporto)** della suite **TCP/IP**.

I due strumenti fondamentali del controllo d’errore sono:

- la **somma di controllo** (_checksum_), per rilevare la presenza di errori;
    
- e la **correzione d’errore**, per rilevarli e, in alcuni casi, correggerli.
    

---

### **2. Il checksum IP**

Il **checksum IP** serve a verificare che l’**intestazione di un datagramma IP** non sia stata alterata durante la trasmissione.

#### **2.1 Procedura di calcolo**

1. Si considera l’intestazione IP come una sequenza di interi **a 16 bit** (due byte alla volta).
    
2. Il campo del checksum viene inizialmente posto a **0**.
    
3. Si sommano tutti i valori a 16 bit usando l’**aritmetica in complemento a 1**.
    
4. Si calcola il **complemento a 1** (NOT binario) del risultato finale.
    

![](imgs/Pasted%20image%2020251211165311.png)

Il valore ottenuto viene inserito nel campo _Checksum dello header_.

#### **2.2 Verifica al ricevimento**

Il destinatario ripete il calcolo sul datagramma ricevuto e confronta il risultato con il checksum presente nell’header:

- se coincidono, il datagramma è integro;
    
- se differiscono, il pacchetto è corrotto e viene **scartato**.
    

---

### **3. Il checksum TCP**

Il protocollo **TCP** utilizza un meccanismo analogo, ma più completo, poiché deve garantire **affidabilità end-to-end** (non solo sull’intestazione).

#### **3.1 Differenze rispetto al checksum IP**

Oltre a considerare l’intestazione TCP, il calcolo comprende:

- una **pseudo-intestazione**, che contiene gli indirizzi IP sorgente e destinazione;
    
- i **dati trasportati** nel segmento TCP.
    

Questo garantisce che eventuali errori nei dati o negli indirizzi vengano rilevati.

#### **3.2 Campi principali del segmento TCP**

![](imgs/Pasted%20image%2020251211165344.png)

|Campo|Descrizione|
|---|---|
|**Porta sorgente / destinazione**|Identificano i processi di origine e destinazione|
|**Numero di sequenza**|Ordina i byte trasmessi|
|**Numero di acknowledgment (ACK)**|Conferma i dati ricevuti|
|**Finestra**|Indica la dimensione del buffer di ricezione|
|**Checksum**|Verifica l’integrità dei dati e dell’intestazione|
|**Puntatore urgente / opzioni**|Gestione priorità e parametri speciali|

---

### **4. Gestione dell’errore**

Il controllo di ridondanza mediante checksum consente di capire **se un frame è integro o corrotto**.  
Il comportamento del protocollo dipende dal tipo di servizio fornito:

- Nei protocolli **connectionless** (es. UDP), il pacchetto corrotto viene **scartato** senza ritrasmissione.
    
- Nei protocolli **connection-oriented** (es. TCP), occorre invece una **strategia di reazione**, per garantire la consegna dei dati in modo affidabile.
    

---

### **5. Strategie di gestione dell’errore**

#### **5.1 Forward Error Correction (FEC)**

Si utilizzano **codici di ridondanza** (come il CRC dell’Ethernet frame) che permettono non solo di rilevare l’errore, ma anche di **correggerlo** direttamente al ricevimento, senza ritrasmissione.

✅ **Vantaggi**

- Correzione immediata, senza bisogno di reinviare il pacchetto.
    
- Ideale in canali con **alta latenza** (es. comunicazioni satellitari).
    

⚠️ **Svantaggi**

- Maggiore complessità e ridondanza nei dati trasmessi.
    

---

#### **5.2 Automatic Repeat reQuest (ARQ)**

Significa “**Richiesta automatica di ritrasmissione**”.  
È la tecnica più usata nei livelli superiori (3 e 4), in particolare in **TCP**.

Funziona così:

1. Il ricevente verifica se il pacchetto è integro.
    
2. Se è corretto, invia un **ACK (Acknowledgment)** al mittente.
    
3. Se è corrotto o mancante, invia un **NAK (Negative ACK)** o non risponde.
    
4. Il mittente ritrasmette il pacchetto fino a ricevere conferma.
    

---

### **6. Tipi di ARQ**

#### **6.1 IDLE RQ (Stop & Wait)**

Detto anche “Send & Wait”, è il metodo più semplice:

- Il mittente invia **un pacchetto** e attende un **ACK** prima di inviarne un altro.
    
- Se il pacchetto risulta corrotto o l’ACK non arriva entro il tempo previsto, viene **ritrasmesso**.
    

Questo schema assicura la correttezza, ma è **poco efficiente** perché la banda resta inutilizzata durante l’attesa.

![](imgs/Pasted%20image%2020251211165809.png)

![](imgs/Pasted%20image%2020251211170034.png)

![](imgs/Pasted%20image%2020251211170219.png)

![](imgs/Pasted%20image%2020251211170237.png)

> Idle RQ viene usato solo come base teorica o in sistemi semplici dove la velocità non è critica.

---

#### **6.2 Continuous RQ**

In questo caso, il mittente **non aspetta** ogni singolo ACK prima di inviare il pacchetto successivo.  
Può quindi inviare **più pacchetti consecutivi**, migliorando notevolmente l’efficienza.

Le strategie principali sono due:

|Tipo|Descrizione|
|---|---|
|**Go-Back-N**|Se un pacchetto è perso o corrotto, il mittente ritrasmette **dal pacchetto errato in poi**.|
|**Selective Repeat**|Vengono ritrasmessi **solo i pacchetti errati**, non l’intera sequenza.|

---

### **7. Timeout e numerazione dei frame**

Nel protocollo **Idle RQ**, il mittente dispone di un **timer di timeout**.  
Se entro un certo intervallo non riceve un ACK, **ritrasmette il pacchetto**.

Poiché la ritrasmissione può generare duplicati, ogni pacchetto deve essere **numerato**.  
Nel caso di Stop & Wait, bastano solo **due numeri di frame** (0 e 1), poiché non vengono mai inviati due pacchetti consecutivi senza conferma.

---

### **8. Obiettivi delle strategie di controllo**

Ogni tecnica di gestione dell’errore persegue due obiettivi fondamentali:

1. **Correttezza** → assicurare che tutti i pacchetti arrivino **integri e nell’ordine corretto**.
    
2. **Efficienza** → sfruttare al massimo la **banda di trasmissione**, riducendo ritardi e ritrasmissioni inutili.
    

---

### **9. Conclusione**

Il **controllo d’errore** è la base di ogni comunicazione affidabile.  
TCP e IP utilizzano meccanismi diversi ma complementari:

- il livello **IP** rileva errori nei datagrammi (checksum);
    
- il livello **TCP** garantisce la **correzione e ritrasmissione** dei dati errati o persi.
    

Questi principi costituiscono le fondamenta del **trasporto affidabile**, da cui derivano le più avanzate tecniche di controllo di flusso e congestione che verranno studiate nelle prossime lezioni


---

![](imgs/Pasted%20image%2020251125065637.png)

