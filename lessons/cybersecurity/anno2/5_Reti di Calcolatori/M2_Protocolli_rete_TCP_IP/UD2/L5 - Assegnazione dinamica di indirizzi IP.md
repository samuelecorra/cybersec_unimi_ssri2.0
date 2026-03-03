# **Lezione 5: Assegnazione dinamica di indirizzi IP**

### **1. Introduzione**

L’assegnazione dinamica degli indirizzi IP nasce dall’esigenza di **semplificare la configurazione di rete** e di **supportare la mobilità dei dispositivi**.  
In una rete moderna, non è più pratico configurare manualmente ogni host: è necessario un sistema automatico che distribuisca gli indirizzi **a richiesta**.

Questa funzione è oggi svolta dal **DHCP (Dynamic Host Configuration Protocol)**, ma nel corso del tempo sono esistiti due predecessori fondamentali:

- **RARP** (Reverse Address Resolution Protocol)
    
- **BOOTP** (Bootstrap Protocol)
    

---

### **2. Motivazioni dell’assegnazione dinamica**

I vantaggi principali di un sistema dinamico sono:

1. **Niente configurazioni manuali**  
    Ogni host riceve automaticamente i propri parametri IP all’avvio.
    
2. **Efficienza nell’uso degli indirizzi**  
    Gli indirizzi vengono **assegnati solo quando servono**, poi restituiti al pool.
    
3. **Supporto alla mobilità**  
    Un laptop o uno smartphone che cambia rete può ricevere **nuovi parametri IP** automaticamente, senza interventi dell’utente.
    

---

### **3. Evoluzione storica dei protocolli**

|Protocollo|Anni di utilizzo|Caratteristiche principali|
|---|---|---|
|**RARP**|Fino al 1985|Assegna solo l’indirizzo IP a partire dal MAC.|
|**BOOTP**|1985 – 1993|Assegna IP + maschera + gateway; utilizza UDP.|
|**DHCP**|Dal 1993|Sistema completo e dinamico, con lease temporanei.|

---

### **4. Il protocollo RARP**

Il **Reverse Address Resolution Protocol** è il più semplice dei tre.

#### **Funzionamento**

- Opera in modo simile all’**ARP** (Address Resolution Protocol), ma in senso inverso.
    
- L’host invia una **richiesta broadcast** per conoscere l’indirizzo IP associato al proprio **indirizzo MAC**.
    
- Un **server RARP** risponde con l’indirizzo IP corrispondente.
    

#### **Limiti**

- RARP fornisce **solo l’indirizzo IP**, senza altri parametri fondamentali:
    
    - **maschera di sottorete**
        
    - **gateway predefinito**
        
- Non consente configurazioni complesse o distribuite.
    

> È stato rapidamente sostituito da protocolli più avanzati come BOOTP.

---

### **5. Il protocollo BOOTP**

Il **Bootstrap Protocol (BOOTP)** è stato progettato per configurare automaticamente host **al momento dell’avvio** (boot time).  
È il primo vero sistema di configurazione automatica TCP/IP.

#### **Funzionamento**

1. L’host, appena acceso, **invia una richiesta broadcast** all’indirizzo:  
    $$  
    255.255.255.255  
    $$  
    Questo indirizzo non è **instradabile**, quindi il messaggio rimane nella rete locale.
    
2. Il **server BOOTP** risponde fornendo:
    
    - l’indirizzo IP dell’host;
        
    - la maschera di sottorete;
        
    - il gateway predefinito;
        
    - eventuali informazioni aggiuntive (nome host, file di boot, ecc.).
        

#### **Caratteristiche**

- La comunicazione avviene su **UDP**:
    
    - **porta 67**: server BOOTP
        
    - **porta 68**: client BOOTP
        
- L’indirizzamento fornito da BOOTP è **statico**, cioè ogni host riceve **sempre lo stesso IP**, preconfigurato nel server.
    

---

### **6. BOOTP e host diskless**

Uno dei campi di applicazione più interessanti del BOOTP era nelle **stazioni diskless**, cioè computer senza disco fisso.  

![](imgs/Pasted%20image%2020260213051034.png)

In questi sistemi, il BOOTP permetteva di:

- ricevere l’indirizzo IP;
    
- conoscere l’indirizzo del server;
    
- **scaricare via rete un’immagine del sistema operativo** da caricare in RAM.
    

> Tuttavia, la natura statica del protocollo lo rendeva poco adatto a reti dinamiche o mobili.

---

### **7. Formato dei messaggi BOOTP / DHCP**

Entrambi i protocolli (BOOTP e DHCP) condividono una **struttura di messaggio simile**, definita per funzionare su UDP.

![](imgs/Pasted%20image%2020260213051100.png)

#### **Campi principali**

|Campo|Descrizione|Esempio / Valori tipici|
|---|---|---|
|**Codice operativo (op)**|Tipo di messaggio|`1 = Request`, `2 = Reply`|
|**Tipo hardware (htype)**|Tipo di rete|`1 = Ethernet`|
|**Lunghezza indirizzo hardware (hlen)**|Byte dell’indirizzo MAC|`6`|
|**Conteggio hop (hops)**|Inizialmente 0, usato per relay agent|`0`|
|**Transaction ID (xid)**|Numero casuale per abbinare richiesta/risposta|–|
|**Secondi (secs)**|Tempo trascorso dall’avvio del client|–|
|**Client IP (ciaddr)**|Se noto, IP del client|–|
|**Your IP (yiaddr)**|IP assegnato dal server|–|
|**Server IP (siaddr)**|Indirizzo del server BOOTP/DHCP|–|
|**Gateway IP (giaddr)**|Indirizzo di un eventuale relay|–|
|**Client hardware address (chaddr)**|MAC address dell’host|–|
|**Server host name**|Nome simbolico del server|–|
|**Boot file name**|Nome del file di avvio remoto|–|

> Il campo **Transaction ID** è fondamentale: consente di collegare ogni risposta al corretto messaggio di richiesta, anche in presenza di più client attivi.

---

### **8. Dal BOOTP al DHCP**

Il **DHCP (Dynamic Host Configuration Protocol)** è un’evoluzione del BOOTP, di cui mantiene compatibilità a livello di formato dei messaggi ma con importanti miglioramenti:

|Funzione|BOOTP|DHCP|
|---|---|---|
|Tipo di assegnazione|Statica|Dinamica o automatica|
|Tempo di validità IP|Permanente|Temporaneo (lease)|
|Parametri aggiuntivi|Limitati|Estesi (DNS, NTP, dominio, ecc.)|
|Riassegnazione IP|Manuale|Automatica|
|Reti mobili / laptop|No|Sì|

Il DHCP consente una **gestione completamente automatizzata** del piano IP, riducendo drasticamente il rischio di conflitti e semplificando la manutenzione.

---

### **9. Conclusione**

L’assegnazione dinamica degli indirizzi IP rappresenta una delle evoluzioni più importanti del networking.  
Dai primi esperimenti con **RARP**, passando per la struttura semi-automatica di **BOOTP**, fino al sistema completo e flessibile di **DHCP**, la rete ha imparato a **configurarsi da sola**.

Oggi il DHCP è uno **standard universale**, capace di:

- fornire indirizzi temporanei o statici;
    
- configurare automaticamente tutti i parametri IP;
    
- supportare dispositivi mobili e connessioni ibride.
    

In breve, è il **cuore invisibile** che tiene in vita Internet e ogni rete locale moderna.