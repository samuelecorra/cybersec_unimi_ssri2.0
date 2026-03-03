## **Lezione 4: Attacchi TCP – ACK Storm**

### **1. Introduzione**

Gli **attacchi Ack Storm** sfruttano una debolezza del protocollo TCP nella gestione degli ACK (acknowledgment).  
Quando un host riceve un pacchetto con un numero di ACK maggiore di quello previsto, esso risponde ritrasmettendo l’ultimo ACK valido e scartando il pacchetto ricevuto.  
Questa reazione può essere manipolata da un attaccante per generare un ciclo infinito di scambi di pacchetti ACK tra due host.

---

### **2. Meccanismo dell’attacco (Two Packets Ack Storm)**

L’attacco di base si articola in tre fasi:

1. **Intercettazione di un pacchetto** da una connessione TCP legittima tra client e server.
    
2. **Creazione di due pacchetti falsificati**:  
    ciascuno è inviato a una delle due parti ma con l’indirizzo sorgente dell’altra parte (spoofing).  
    Questi pacchetti devono rientrare nelle **finestre TCP** di entrambe le parti e contenere almeno **un byte di dati**.
    
3. **Invio simultaneo dei due pacchetti** al client e al server.
    

Da questo momento, ciascun lato interpreta i pacchetti ricevuti come validi ma con **ACK superiori** al proprio `SEQ`.  
Secondo lo standard TCP (RFC, pag. 71):

1. L’host invia nuovamente l’ultimo ACK.
    
2. Scarta completamente il pacchetto ricevuto (ignorando l’eventuale payload).
    

Poiché entrambi i lati reagiscono nello stesso modo, si innesca un **loop infinito** di invii e risposte ACK reciproche.  
Questo causa **saturazione di banda** e **carico CPU elevato** per entrambi i sistemi coinvolti.

---

### **3. Scenario pratico (da Herzbaer, ComSec 2013)**

Supponiamo:

- $A.SEQ = 1000 = B.ACK$
    
- $B.SEQ = 2000 = A.ACK$
    

L’attaccante Eve invia due pacchetti falsificati, ognuno di lunghezza 10:

- Il primo verso **A**, come se provenisse da **B**.
    
- Il secondo verso **B**, come se provenisse da **A**.
    

Si verifica la seguente sequenza:

1. $A$ riceve un pacchetto con $ACK = 2010$ → invia un nuovo ACK a $B$.
    
2. $B$ riceve un pacchetto con $ACK = 1010$ → invia un nuovo ACK ad $A$.
    
3. Entrambi rilevano pacchetti con $ACK > SEQ$ → ripetono il passo 1.
    
4. Il ciclo si autoalimenta indefinitamente (**storm**).
    

---

### **4. Caratteristiche operative**

- L’attacco richiede che i pacchetti falsificati rientrino nelle **finestre di congestione TCP** di entrambe le parti.
    
- È particolarmente efficace quando la connessione TCP rimane **aperta ma inattiva**, come accade nei **browser Web** (es. Internet Explorer, Firefox) che tengono viva la sessione dopo il caricamento della pagina.
    
- Durante il trasferimento di **file di grandi dimensioni**, la finestra di congestione aumenta, ampliando la superficie d’attacco.
    
- L’attaccante deve conoscere **IP esterno e porta NAT** assegnata al client interno, per poter iniettare pacchetti correttamente indirizzati.
    

---

### **5. Contromisure**

Per prevenire un Ack Storm occorre **modificare lievemente l’implementazione TCP**:

- Se un host riceve un pacchetto con **campo ACK superiore al proprio numero di sequenza**, il pacchetto deve essere **scartato** **senza inviare risposta**.
    
- È consigliato il **filtraggio dei pacchetti ACK duplicati**, in modo da interrompere tempestivamente eventuali loop di risposta.
    

Queste contromisure neutralizzano l’effetto dei due pacchetti iniziali falsificati e impediscono che la connessione vada in storm.

---

### **6. Attacchi DoS e riflessione**

#### **6.1. Obiettivo generale**

Gli attacchi **Denial of Service (DoS)** mirano a **escludere un nodo o un servizio** con **il minimo sforzo possibile**.  
Spesso si basano su **amplificazione**, cioè la quantità di dati generata dall’attaccante è molto inferiore a quella che colpisce la vittima.

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
    

---

### **8. ACK Reflection Attack**

- L’attaccante invia un **pacchetto TCP SYN** verso il reflector (ad esempio un sito web), utilizzando come **IP source** quello della vittima.
    
- Il reflector risponde con **SYN/ACK**, che viene inviato alla vittima.
    
- La vittima riceve quindi una grande quantità di **pacchetti fuori sequenza** provenienti da server legittimi.
    

Poiché i SYN spoofati e quelli reali sono indistinguibili, **il reflector non può proteggersi** in modo affidabile.

---

### **9. Backscatter (Radiazione di ritorno)**

Il termine **backscatter** indica il traffico di risposta generato da una vittima in seguito a un attacco basato su spoofing:

- La vittima riceve **numerosi SYN con IP sorgente falsificato**.
    
- Risponde normalmente a ciascun indirizzo sorgente ricevuto.
    
- Questo genera **una grande quantità di pacchetti di ritorno**, spesso verso host inesistenti o inconsapevoli.
    

Il concetto di backscatter è usato anche in ambito email per indicare i **messaggi di rimbalzo massivi** derivanti da spam o spoofing.

---

### **10. Sintesi finale**

|Aspetto|Descrizione|
|---|---|
|**Tipo di attacco**|ACK Storm (DoS logico basato su TCP)|
|**Tecnica**|Iniezione di pacchetti falsificati con ACK superiori al SEQ|
|**Effetto**|Loop infinito di ACK tra due host|
|**Requisiti**|Conoscenza IP/porta, pacchetti entro finestra TCP|
|**Contromisure**|Scartare ACK > SEQ senza risposta, filtraggio ACK duplicati|
|**Varianti correlate**|Reflection Attack, ACK Reflection, Backscatter|
