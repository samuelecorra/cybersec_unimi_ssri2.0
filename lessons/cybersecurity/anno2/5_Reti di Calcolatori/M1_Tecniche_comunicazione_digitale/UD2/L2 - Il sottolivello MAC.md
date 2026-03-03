# **Lezione 2: Il sottolivello MAC**

### **1. Il ruolo del sottolivello MAC**

Nel modello OSI, il **livello Data Link** (livello 2) è diviso in due sottolivelli:

- **LLC (Logical Link Control)**, che gestisce la logica della connessione,
    
- **MAC (Medium Access Control)**, che regola **l’accesso al mezzo fisico condiviso**.
    

Il sottolivello **MAC** è cruciale nelle reti locali (LAN) e wireless, perché in esse **più dispositivi condividono lo stesso canale di comunicazione**.  
Il suo compito è stabilire **chi può trasmettere e quando**, evitando collisioni e massimizzando l’efficienza.

---

### **2. Strategie di accesso al mezzo**

Esistono varie strategie di **Medium Access Control (MAC)**, che si distinguono per come gestiscono la condivisione del canale.  
In generale, possiamo dividerle in tre famiglie:

1. **Protocolli semplici**, come ALOHA o CSMA/CD, in cui le stazioni trasmettono liberamente.
    
2. **Protocolli collision free**, come TDMA e FDMA, in cui le risorse sono allocate rigidamente.
    
3. **Protocolli ibridi**, che combinano entrambi gli approcci per adattarsi al carico di rete.
    

---

### **3. Strategie ibride (adaptive MAC)**

I **protocolli ibridi** sono detti anche **adattivi** perché modificano il proprio comportamento a seconda del traffico.

L’idea è semplice:  
le stazioni vengono **divise in gruppi**, e ogni gruppo può contendersi uno **slot temporale** diverso.  
A seconda del carico di rete, il numero di stazioni per gruppo viene variato:

- **Caso limite 1:** un solo membro per gruppo → comportamento **collision free** (nessuna contesa).
    
- **Caso limite 2:** tutti in un unico gruppo → comportamento tipo **Slotted ALOHA** (massima contesa).
    
- **Caso intermedio:** si regola dinamicamente la dimensione del gruppo in base al traffico.
    

Un esempio pratico:  
inizialmente tutte le stazioni partecipano alla contesa; se si verifica una collisione, il sistema **divide il gruppo a metà**, poi ancora a metà, e così via, fino a eliminare le collisioni.  
È un meccanismo simile alla **ricerca binaria** applicata al controllo dell’accesso.

---

### **4. Tabella di sintesi delle strategie MAC**

|**Contesto di rete**|**Obiettivo**|**Soluzione consigliata**|
|---|---|---|
|**Basso carico**|Minimizzare la latenza|Protocolli a contesa (es. ALOHA, CSMA)|
|**Alto carico**|Massimizzare l’efficienza|Protocolli collision free (es. TDMA, FDMA)|
|**Carico variabile**|Adattamento dinamico|Protocolli ibridi (contesa limitata)|
|**Reti wireless**|Gestire interferenze e copertura non uniforme|Varianti MAC specifiche (MACA, CSMA/CA)|

---

### **5. Le reti LAN wireless**

![](imgs/Pasted%20image%2020260212141251.png)

In una **rete wireless**, però, non sempre tutte le stazioni si trovano nel raggio di copertura reciproco. Potrebbe capitare, e in quel caso nessun problema:

![](imgs/Pasted%20image%2020260212141615.png)

Però spesso non siamo in questa situazione ideale, bensì prevale il caso in cui non tutte le stazioni si trovano nel raggio di copertura reciproco:

![](imgs/Pasted%20image%2020260212141726.png)

Questo aspetto, da un lato, **permette più trasmissioni simultanee** (maggiore parallelismo), ma dall’altro **introduce nuovi problemi di coordinamento**.

#### **a) Il problema della stazione nascosta**

Consideriamo dalla foto precedente i primi tre nodi A, B e C:

- A trasmette a B;
    
- C non sente la trasmissione di A, quindi crede che il canale sia libero;
    
- C inizia a trasmettere, generando una **collisione** con il messaggio di A su B.
    

In sintesi: C è **“nascosta”** ad A ma interferisce comunque con B.

#### **b) Il problema della stazione esposta**

Ora invece prendiamo in esame tutti e quattro i nodi A, B, C, D in linea:

- B sta trasmettendo ad A;
    
- C percepisce il canale occupato da B e quindi non trasmette a D, anche se **non interferirebbe affatto**.
    

In questo caso, C è **“esposta”**: rinuncia a trasmettere pur potendolo fare senza problemi.  
Entrambi i casi riducono **l’efficienza complessiva** della rete wireless.

---

### **6. MACA – Multiple Access with Collision Avoidance**

Per risolvere questi problemi, fu introdotto il protocollo **MACA (Multiple Access with Collision Avoidance)**, base del moderno **CSMA/CA** usato nel Wi-Fi (IEEE 802.11).

Il principio è quello di **prenotare il canale** prima della trasmissione, tramite due messaggi di controllo:

#### **a) RTS – Request To Send**

Quando una stazione (es. A) vuole trasmettere, invia un messaggio **RTS** (Request To Send).  
Tutte le stazioni che sentono questo segnale (es. B e D) capiscono che sono **vicine al trasmettitore** e devono **restare in silenzio** fino al termine della comunicazione.

![](imgs/Pasted%20image%2020260212142041.png)

#### **b) CTS – Clear To Send**

Il destinatario (es. B) risponde con un messaggio **CTS** (Clear To Send).  
Le stazioni che ricevono il CTS (es. C) comprendono che si trovano **vicine al ricevitore** e devono **rimanere in silenzio** durante la trasmissione dei dati.

#### **c) Trasmissione e ACK**

Dopo il CTS, A invia i **dati**.  
Alla fine, B risponde con un **ACK** (acknowledgment) per confermare la corretta ricezione.  
Se l’ACK non arriva, A presume una collisione e **ritrasmette** dopo un intervallo di **backoff casuale**.

#### **d) Caratteristiche aggiuntive**

- **Rilevazione di portante:** le stazioni ascoltano il canale prima di trasmettere per evitare collisioni tra RTS.
    
- **Informazioni sulla congestione:** ogni nodo può regolare il backoff in base al traffico locale.
    
- **Algoritmo di backoff per flusso:** ogni connessione gestisce autonomamente il proprio intervallo di attesa.
    

In questo modo, **MACA riduce drasticamente le collisioni** e risolve i problemi di **stazione nascosta** ed **esposta**.

---

### **7. Sintesi concettuale**

- Il sottolivello **MAC** controlla l’accesso al mezzo fisico.
    
- I **protocolli ibridi** si adattano al carico, combinando flessibilità e controllo.
    
- Nelle **reti wireless**, le problematiche di copertura introducono i fenomeni di **stazioni nascoste** ed **esposte**.
    
- Il **protocollo MACA**, tramite RTS/CTS/ACK, è la base delle moderne reti Wi-Fi, permettendo comunicazioni **più affidabili e ordinate**.