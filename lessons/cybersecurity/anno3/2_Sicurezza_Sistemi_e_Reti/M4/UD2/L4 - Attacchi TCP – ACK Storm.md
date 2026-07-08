## **Lezione 4: Attacchi TCP – ACK Storm**

### **1. Introduzione**

Gli **attacchi Ack Storm** sfruttano una debolezza del protocollo TCP nella gestione degli ACK (acknowledgment).  
Quando un host riceve un pacchetto con un numero di ACK maggiore di quello previsto, esso risponde ritrasmettendo l’ultimo ACK valido e scartando il pacchetto ricevuto.  
Questa reazione può essere manipolata da un attaccante per generare un ciclo infinito di scambi di pacchetti ACK tra due host.

TCP, come già visto nelle lezioni precedenti, nasce per fornire **affidabilità di trasporto**, non sicurezza: i segmenti viaggiano normalmente in chiaro e non sono autenticati crittograficamente. Se un attaccante riesce a costruire pacchetti con indirizzi, porte e numeri `SEQ`/`ACK` coerenti con lo stato della connessione, gli endpoint non hanno un meccanismo nativo per distinguere il segmento genuino da quello falsificato.

Il comportamento normale di una connessione TCP è il seguente:

![](imgs/Pasted%20image%2020260708215243.png)

1. un client avvia una richiesta di connessione verso un server in ascolto su una certa porta;

2. il client invia un `SYN`;

3. il server registra il tentativo nel **Transmission Control Block** (`TCB`), includendo almeno indirizzo e porta del client, indirizzo e porta del server, stato della connessione e numeri di sequenza iniziali;

4. il server risponde con `SYN/ACK`;

5. il client invia l’`ACK` finale e lo stato passa a `ESTABLISHED`;

6. da quel momento i due host scambiano dati usando numeri di sequenza e acknowledgement coerenti con lo stato memorizzato.

> 📌 L’ACK storm sfrutta proprio questa logica di stato: non rompe la crittografia, ma induce due stack TCP legittimi a reagire automaticamente a pacchetti falsificati.

---

### **2. Meccanismo dell’attacco (Two Packets Ack Storm)**

L’attacco di base si articola in tre fasi:

1. **Osservazione o ricostruzione dello stato** di una connessione TCP legittima tra client e server.
    
2. **Creazione di due pacchetti falsificati**:  
    ciascuno è inviato a una delle due parti ma con l’indirizzo sorgente dell’altra parte (spoofing).  
    Questi pacchetti devono rientrare nelle **finestre di ricezione TCP** di entrambe le parti, usare numeri di sequenza accettabili e contenere almeno **un byte di dati**.
    
3. **Invio simultaneo dei due pacchetti** al client e al server.
    

Da questo momento, ciascun lato riceve un pacchetto che sembra provenire dall’altro host, ma che contiene un valore di `ACK` superiore a quello atteso rispetto ai byte effettivamente trasmessi.  
Secondo lo standard TCP (RFC, pag. 71):

1. L’host invia nuovamente l’ultimo ACK.
    
2. Scarta completamente il pacchetto ricevuto (ignorando l’eventuale payload).
    

Poiché entrambi i lati reagiscono nello stesso modo, si innesca un **loop infinito** di invii e risposte ACK reciproche.  
Questo causa **saturazione di banda** e **carico CPU elevato** per entrambi i sistemi coinvolti.

> ⚠️ Il punto delicato non è solo falsificare l’IP sorgente: i pacchetti devono cadere nella finestra TCP accettata. Se il numero di sequenza è completamente fuori finestra, il pacchetto viene scartato senza produrre l’effetto desiderato.

---

### **3. Scenario pratico (da Herzbaer, ComSec 2013)**

Supponiamo:

- $A.SEQ = 1000 = B.ACK$
    
- $B.SEQ = 2000 = A.ACK$

L’attaccante Eve invia due pacchetti falsificati, ognuno di lunghezza 10:

- Il primo verso **A**, come se provenisse da **B**.
    
- Il secondo verso **B**, come se provenisse da **A**.
    

![](imgs/Pasted%20image%2020260708215314.png)

Si verifica la seguente sequenza:

1. $A$ riceve un pacchetto con $ACK = 2010$ → invia un nuovo ACK a $B$.
    
2. $B$ riceve un pacchetto con $ACK = 1010$ → invia un nuovo ACK ad $A$.
    
3. Entrambi rilevano pacchetti con $ACK > SEQ$ → ripetono il passo 1.
    
4. Il ciclo si autoalimenta indefinitamente (**storm**).
    

---

### **4. Caratteristiche operative**

- L’attacco richiede che i pacchetti falsificati rientrino nelle **finestre di ricezione TCP** di entrambe le parti.
    
- È particolarmente efficace quando la connessione TCP rimane **aperta ma inattiva**, come accade nei **browser Web** che tengono viva la sessione dopo il caricamento della pagina.
    
- Durante il trasferimento di **file di grandi dimensioni**, la finestra può diventare ampia: aumentano quindi i valori `SEQ`/`ACK` accettabili e diventa più semplice per l’attaccante indovinare un numero valido.
    
- L’attaccante deve conoscere **IP esterno e porta NAT** assegnata al client interno, per poter iniettare pacchetti correttamente indirizzati.
    
- Il rapporto costo/effetto è molto favorevole all’attaccante: due soli pacchetti iniziali possono generare una quantità molto maggiore di traffico tra gli host coinvolti.


---

### **5. Contromisure**

Per prevenire un Ack Storm occorre **modificare lievemente l’implementazione TCP**:

- Se un host riceve un pacchetto con **campo ACK superiore al proprio numero di sequenza**, il pacchetto deve essere **scartato** **senza inviare risposta**.
    
- È consigliato il **filtraggio dei pacchetti ACK duplicati**, in modo da interrompere tempestivamente eventuali loop di risposta.
    
- A livello di rete si possono inoltre applicare controlli di filtraggio e rate limiting per individuare flussi anomali o duplicati, soprattutto quando lo scambio ACK cresce senza corrispondente traffico applicativo utile.
    

Queste contromisure neutralizzano l’effetto dei due pacchetti iniziali falsificati e impediscono che la connessione vada in storm.

---

### **6. Attacchi DoS e riflessione**

#### **6.1. Obiettivo generale**

Gli attacchi **Denial of Service (DoS)** mirano a **escludere un nodo o un servizio** con **il minimo sforzo possibile**.  
Spesso si basano su **amplificazione**, cioè la quantità di dati generata dall’attaccante è molto inferiore a quella che colpisce la vittima.

Nel caso dell’ACK storm l’amplificazione deriva da una particolarità del protocollo: pochi pacchetti falsificati inducono host legittimi a generare traffico di risposta. In altri attacchi, invece, l’amplificazione nasce dall’uso di molte macchine compromesse, cioè **zombie** o bot, che generano traffico coordinato verso la vittima.

#### **6.2. Due categorie principali**

- **DoS Bug:** sfrutta difetti di progettazione o implementazione.
    
- **DoS Flood:** genera traffico massiccio, tipicamente tramite **botnet**.
    

---

### **7. Reflection e IP Spoofing**

Negli attacchi **Reflection**, l’attaccante non colpisce direttamente la vittima ma invia il proprio traffico a un **reflector** (es. server web o DNS), contraffacendo l’indirizzo sorgente.  
Il reflector, rispondendo, **rimanda i pacchetti alla vittima**.

- Con lo **IP spoofing**, l’attaccante imposta come indirizzo sorgente l’IP della vittima.
    
- Il reflector risponde al pacchetto pensando sia legittimo, **inondando la vittima di risposte**.
    
- Poiché la risposta proviene da un host “pulito”, risulta **difficile risalire all’attaccante reale** o filtrare il traffico (anche con firewall stateful).
    
Il reflector è spesso un server perfettamente legittimo: dal suo punto di vista sta soltanto rispondendo a richieste che sembrano provenire dalla vittima. Per questo motivo la vittima vede arrivare traffico da host apparentemente innocenti e non ha un’indicazione diretta dell’origine reale dell’attacco.

> 📌 Reflection e IP spoofing sono strettamente collegati: senza source address spoofing, le risposte del reflector tornerebbero all’attaccante, non alla vittima.


---

### **8. ACK Reflection Attack**

L’esempio più semplice discusso a lezione usa un reflector TCP, per esempio un sito web in ascolto sulla porta 80:

![](imgs/Pasted%20image%2020260708215350.png)

- L’attaccante invia un **pacchetto TCP SYN** verso il reflector, utilizzando come **IP source** quello della vittima.
    
- Il reflector risponde con **SYN/ACK**, che viene inviato alla vittima.
    
- La vittima riceve quindi una grande quantità di **pacchetti fuori sequenza** provenienti da server legittimi.
    

Poiché i SYN spoofati e quelli reali sono indistinguibili, **il reflector non può proteggersi** in modo affidabile.

Dal punto di vista del reflector non c’è un criterio locale sicuro per dire se il `SYN` sia reale o falsificato: un server web deve normalmente accettare nuove connessioni da indirizzi arbitrari. La difesa più efficace deve quindi intervenire prima, impedendo agli host sorgente o agli ISP di inoltrare pacchetti con indirizzo sorgente non plausibile.

---

### **9. Backscatter (Radiazione di ritorno)**

Il termine **backscatter** o **radiazione di ritorno** indica il traffico di risposta generato come effetto collaterale di un attacco basato su spoofing:

- l’attaccante genera molti pacchetti con indirizzo sorgente falsificato;
    
- host intermedi o vittime rispondono a quegli indirizzi secondo il normale comportamento del protocollo;
    
- il risultato è una grande quantità di traffico di ritorno verso host che non hanno iniziato davvero la comunicazione.
    
Nel caso della reflection TCP, i reflector ricevono `SYN` con sorgente impostata all’IP della vittima e rispondono con `SYN/ACK` verso la vittima. La vittima, a sua volta, può reagire ai pacchetti inattesi generando ulteriore traffico, per esempio `RST`, perché non riconosce quelle connessioni come proprie.

![](imgs/Pasted%20image%2020260708215423.png)


Il concetto di backscatter è usato anche in ambito email per indicare i **messaggi di rimbalzo massivi** derivanti da spam o spoofing.

---

### **10. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Tipo di attacco**|ACK Storm (DoS logico basato su TCP)|
|**Tecnica**|Iniezione di pacchetti falsificati con ACK superiori al SEQ|
|**Effetto**|Loop infinito di ACK tra due host|
|**Requisiti**|Conoscenza dello stato TCP, IP/porta, pacchetti entro finestra di ricezione|
|**Contromisure**|Scartare ACK > SEQ senza risposta, filtraggio ACK duplicati, rate limiting e anti-spoofing|
|**Concetti correlati**|Amplification, Reflection Attack, Backscatter|
