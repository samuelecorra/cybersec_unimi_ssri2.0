## **Lezione 2: NAT avanzato**

### **1. Introduzione**

Il **Network Address Translation (NAT)**, oltre a permettere la connessione a Internet delle reti private, può essere impiegato in **scenari avanzati** per risolvere problemi di gestione, migrazione e bilanciamento delle risorse.

In questa lezione analizziamo quattro applicazioni pratiche del NAT:

1. **Pooling di indirizzi IP**
    
2. **Supporto alla migrazione tra provider**
    
3. **IP masquerading (NAPT / PAT)**
    
4. **Bilanciamento del carico dei server**
    

---

### **2. Pooling di indirizzi IP**

#### **2.1 Il problema**

Una rete aziendale può disporre di **molti host interni**, ma di **pochi indirizzi IP pubblici**.  
Assegnare un indirizzo pubblico a ogni dispositivo risulterebbe impossibile o troppo costoso.

#### **2.2 La soluzione NAT**

Il NAT consente di **condividere un insieme (pool)** di indirizzi pubblici tra tutti gli host della rete privata.  
Un **router o firewall NAT**, posto al confine della rete aziendale, gestisce dinamicamente le associazioni.

#### **2.3 Funzionamento**

1. Gli host interni utilizzano **indirizzi privati** (es. `192.168.x.x`).
    
2. Quando un host invia un pacchetto verso Internet, il dispositivo NAT:
    
    - sceglie **uno degli indirizzi pubblici** disponibili nel pool;
        
    - sostituisce l’indirizzo sorgente privato con quello pubblico scelto;
        
    - registra la traduzione nella sua **tabella NAT**.
        
3. Quando il pacchetto di risposta ritorna, il NAT effettua la traduzione inversa verso l’host corretto.
    

> In questo modo, anche reti con centinaia di host possono accedere a Internet usando solo pochi indirizzi pubblici.

---

### **3. Supporto alla migrazione tra provider di rete**

#### **3.1 Il problema**

Gli indirizzi IP pubblici di un’azienda sono forniti dal **provider di connettività (ISP)**.  
Quando l’azienda cambia provider, cambiano anche gli indirizzi IP pubblici assegnati.  
Senza NAT, sarebbe necessario **riconfigurare manualmente tutti gli host** della rete.

#### **3.2 La soluzione NAT**

Con il NAT:

- ogni host interno mantiene il **proprio indirizzo privato stabile**;
    
- il **dispositivo NAT** effettua la traduzione statica tra IP privato ↔ IP pubblico.
    

Quando l’azienda cambia provider:

- basta **aggiornare solo le regole NAT** sul router o firewall;
    
- gli host interni **non subiscono alcuna modifica**.
    

#### **3.3 Differenza rispetto al pooling**

Nel pooling, le associazioni sono **dinamiche e temporanee**.  
Nel supporto alla migrazione, invece, le traduzioni NAT sono **statiche e permanenti**.

---

### **4. IP Masquerading (NAPT / PAT)**

#### **4.1 Il problema**

Molte reti domestiche e aziendali dispongono di **un solo indirizzo IP pubblico** ma di **numerosi host privati**.  
Serve quindi un modo per far sì che tutti gli host possano comunque accedere a Internet contemporaneamente.

#### **4.2 La soluzione**

Il dispositivo NAT estende la traduzione anche alle **porte TCP/UDP**, in modo da distinguere le connessioni provenienti da host diversi pur utilizzando un unico indirizzo IP pubblico.

Questo meccanismo è noto come:

- **NAPT (Network Address and Port Translation)**, oppure
    
- **PAT (Port Address Translation)**,
    
- o più informalmente **IP masquerading**.
    

#### **4.3 Esempio**

|Host interno|Porta sorgente privata|IP pubblico|Porta pubblica|
|---|---|---|---|
|192.168.1.10|50312|93.45.16.2|61001|
|192.168.1.11|50313|93.45.16.2|61002|

Ogni connessione in uscita viene identificata non solo dall’IP ma anche dalla **porta**, permettendo di gestire più flussi simultanei con un solo indirizzo pubblico.

---

### **5. Bilanciamento del carico dei server**

#### **5.1 Il problema**

Un servizio Internet (es. un sito web) può essere ospitato da **più server identici** per migliorare le prestazioni e la disponibilità.  
Tuttavia, si desidera che gli utenti accedano sempre a **un unico indirizzo IP pubblico**, senza sapere quale server gestirà la loro richiesta.

#### **5.2 La soluzione NAT**

Il dispositivo NAT agisce come **proxy intelligente**:

- riceve tutte le richieste in arrivo verso l’indirizzo IP pubblico del servizio;
    
- le distribuisce su diversi **server interni**, ciascuno con un **indirizzo IP privato**;
    
- riscrive l’indirizzo IP di destinazione dei pacchetti in arrivo, inoltrandoli a uno dei server.
    

#### **5.3 Strategie di bilanciamento**

Una strategia semplice è la **rotazione round-robin**:  
il NAT assegna i pacchetti in arrivo ai server interni in modo ciclico e bilanciato.

|Richiesta n°|Server scelto|
|---|---|
|1|Server A – 192.168.10.11|
|2|Server B – 192.168.10.12|
|3|Server C – 192.168.10.13|
|4|Server A – 192.168.10.11|

In scenari più avanzati, il NAT può integrare algoritmi di **load balancing dinamico**, basati su:

- carico CPU,
    
- numero di connessioni attive,
    
- o tempi di risposta dei server.
    

---

### **6. Conclusione**

Il **NAT avanzato** amplia le potenzialità della traduzione degli indirizzi IP ben oltre la semplice connettività di rete.  
Grazie alle sue varianti e applicazioni, permette di:

- **ottimizzare l’uso degli indirizzi pubblici** (pooling);
    
- **semplificare la migrazione tra provider**;
    
- **condividere un singolo IP pubblico tra più host** (masquerading);
    
- **bilanciare il carico dei server** per garantire scalabilità e continuità di servizio.
    

Il NAT si conferma così uno degli strumenti più versatili e fondamentali per la gestione moderna delle reti TCP/IP.


---

QUESITI 

1. Elencare le principali applicazioni del NAT. 

2. Che tecnica NAT si utilizza se si vuole che gli host di una rete privata accedano a internet ma ho a disposizione un insieme di indirizzi pubblici limitato. Descriverne il funzionamento. 

3. Che tecnica NAT si utilizza se si vuole che gli host di una rete privata accedano a internet ma ho a disposizione un solo indirizzi pubblico. Descriverne il funzionamento. 

RISPOSTE 

1. - Pooling di indirizzi IP. - Supporto della condivisione tra provider di servizi di rete. - IP masquerading. - Bilanciamento del carico dei server. 

2. La tecnica usata è quella del pooling di indirizzi IP. Il NAT gestisce il pool di indirizzi validi in internet. Quando un host dalla rete aziendale invia un pacchetti IP a un host nell’Internet pubblica, il dispositivo NAT sceglie un indirizzo IP pubblico dal pool di indirizzi e sostituisce questo indirizzo a quello privato dell’host. 

3. Si tratta della tecnica IP masquerading.Il dispositivo NAT modifica il dispositivo di porta per il traffico in uscita.