# **Lezione 2: Attacchi a livello di progettazione**

---

### **1. Panoramica della lezione**

Questa lezione analizza alcune delle principali categorie di **attacchi che derivano da vulnerabilità introdotte nella fase di progettazione** del software.

L’obiettivo è comprendere:

- quali attacchi possono emergere da errori architetturali,
    
- come funzionano tecnicamente,
    
- quali strategie difensive possono essere adottate,
    
- quali responsabilità rimangono comunque in capo allo sviluppatore.
    

Un concetto fondamentale che attraversa tutta la lezione è che:

> molte vulnerabilità di sicurezza non derivano da errori di codice, ma da errori di progettazione.

---

### **2. Possibili attacchi in fase di progettazione**

Gli attacchi più comuni a livello di progettazione includono:

- Man-in-the-middle attack
    
- Race condition attack
    
- Replay attack
    
- Sniffer attack
    
- Session hijacking attack
    
- Session killing attack
    

Questi attacchi non dipendono necessariamente da bug di implementazione, ma spesso da:

- scelte architetturali deboli,
    
- protocolli non protetti,
    
- ipotesi errate sul comportamento del sistema.
    

---

### **3. Man-in-the-middle attack**

#### **3.1 Definizione**

Un **man-in-the-middle (MITM) attack** si verifica quando:

> un attaccante intercetta una comunicazione di rete tra due host e si spaccia per una delle due parti coinvolte nella transazione, eventualmente inserendo istruzioni aggiuntive nel dialogo.

L’attaccante si pone quindi “in mezzo” tra client e server, controllando la comunicazione senza che le parti se ne accorgano.

---

#### **3.2 Strategie di difesa**

Le principali difese includono:

- uso intensivo della **crittografia**, in particolare:
    
    - autenticazione crittografica forte,
        
    - protocolli sicuri (es. SSH invece di Telnet);
        
- uso di **session checksums** e **shared secrets**:
    
    - ad esempio cookies nelle interfacce web;
        
- cifratura dei file tramite strumenti come:
    
    - PGP,
        
    - Entrust.
        

Il principio generale è garantire:

> autenticità e integrità della comunicazione.

---

### **4. Race condition attack**

#### **4.1 Definizione di race condition**

Una **race condition** è un difetto che si manifesta quando:

> l’output di un processo dipende in modo inatteso dalla sequenza temporale di altri eventi.

In presenza di concorrenza tra operazioni, il risultato può variare in modo non previsto.

#### **4.2 Race condition come vulnerabilità di sicurezza**

Un **race condition attack** si verifica quando:

> una operazione di controllo apre una finestra temporale durante la quale un attaccante può compromettere la sicurezza.

Questa vulnerabilità è nota anche come:

> **TOCTTOU — Time Of Check To Time Of Use**

cioè il tempo che intercorre tra:

- verifica di una condizione,
    
- uso della risorsa verificata.
    

Se in questo intervallo lo stato cambia, la sicurezza può essere compromessa.

---

#### **4.3 Strategie di difesa**

Per difendersi è necessario:

- distinguere tra operazioni:
    
    - **atomiche** (indivisibili),
        
    - **non atomiche**;
        
- evitare operazioni non atomiche quando coinvolgono aspetti di sicurezza.
    

Esempi di operazioni sensibili:

- apertura di file,
    
- invocazione di sottoprogrammi,
    
- controllo password,
    
- verifica username.
    

Regola importante:

> se non si è sicuri che un’operazione sia atomica, bisogna assumere che non lo sia.

---

### **5. Replay attack**

#### **5.1 Definizione**

Un **replay attack** si verifica quando:

> un attaccante riesce a catturare la registrazione completa di una transazione tra client e server e può riprodurre parte della comunicazione a scopo malevolo.

L’attaccante non deve necessariamente comprendere il contenuto:

- basta replicare i messaggi.
    

---

#### **5.2 Strategie di difesa**

Le difese includono:

- le stesse tecniche del man-in-the-middle:
    
    - crittografia,
        
    - autenticazione;
        
- introduzione di elementi variabili nelle sessioni, come:
    
    - **sequence identifier** diversi per ogni sessione,
        
    - timestamp (tempo corrente).
        

Questo impedisce il replay “byte per byte” della comunicazione.

---

### **6. Sniffer attack**

#### **6.1 Definizione**

Uno **sniffer** è un programma che:

> memorizza silenziosamente tutto il traffico trasmesso su una rete locale.

Uno **sniffer attack** avviene quando lo sniffer viene utilizzato per:

- raccogliere username,
    
- raccogliere password,
    

trasmessi in chiaro sulla rete.

---

#### **6.2 Strategie di difesa**

Le difese dipendono dal ruolo.

**Come amministratore di sistema:**

- configurazione accurata della rete,
    
- utilizzo di router di rete **switchati**.
    

**Come programmatore:**

- uso estensivo della **crittografia**.
    

Il principio fondamentale è evitare trasmissioni in chiaro.

---

### **7. Session hijacking attack**

#### **7.1 Definizione**

Un **session hijacking attack** si verifica quando:

> un attaccante prende il controllo di una connessione già stabilita sfruttando debolezze del protocollo TCP/IP.

L’attaccante può quindi impersonare una delle parti della comunicazione.

---

#### **7.2 Strategie di difesa**

Questo attacco è principalmente di rete, quindi:

> è difficile per una applicazione software difendersi completamente.

Le difese possibili includono:

- uso della crittografia,
    
- logging accurato per rilevare l’attacco dopo che si è verificato.
    

---

### **8. Session killing attack**

#### **8.1 Definizione**

Nel protocollo TCP/IP una connessione può essere terminata se una delle parti invia un pacchetto **TCP reset**.

Un **session killing attack** si verifica quando:

> un attaccante falsifica gli indirizzi di una delle parti e invia un reset prematuro della connessione.

Questo attacco può essere utilizzato per:

- distruggere una comunicazione,
    
- prendere il controllo di una parte della trasmissione.
    

---

#### **8.2 Strategie di difesa**

Anche in questo caso:

> è difficile difendersi a livello applicativo.

Una possibile strategia è:

- permettere all’applicazione di ristabilire la connessione interrotta.
    

---

### **9. Sintesi della lezione**

In questa lezione abbiamo visto:

- alcuni dei più comuni attacchi a livello di progettazione,
    
- il funzionamento di ciascun attacco,
    
- possibili strategie difensive.
    

Concetti fondamentali:

- molte vulnerabilità derivano da errori di progettazione,
    
- anche quando non è possibile difendersi completamente, lo sviluppatore ha la responsabilità di:
    
    - considerare gli attacchi possibili,
        
    - pianificare strategie per minimizzarne l’impatto.
        

---

### **10. Conclusione**

Un messaggio chiave della lezione è:

> il fatto che uno sviluppatore non possa prevenire completamente un attacco non riduce la sua responsabilità nel progettare sistemi che ne limitino le conseguenze.

Questo introduce il concetto moderno di:

- sicurezza by design,
    
- resilienza dei sistemi.