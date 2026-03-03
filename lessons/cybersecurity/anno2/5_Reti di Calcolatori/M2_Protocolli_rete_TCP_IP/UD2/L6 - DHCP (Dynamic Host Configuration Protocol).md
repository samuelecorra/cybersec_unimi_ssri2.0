# **Lezione 6: DHCP (Dynamic Host Configuration Protocol)**

### **1. Introduzione**

Il **DHCP** (_Dynamic Host Configuration Protocol_) è lo standard moderno per l’**assegnazione automatica e dinamica degli indirizzi IP** all’interno delle reti TCP/IP.  
È stato **progettato nel 1993** come evoluzione diretta del **BOOTP**, da cui eredita la struttura dei messaggi e le **stesse porte UDP**:

- **Porta 67** → server DHCP
    
- **Porta 68** → client DHCP
    

Il DHCP non si limita ad assegnare indirizzi IP: consente anche di **configurare automaticamente tutti i parametri di rete**, come la maschera di sottorete, il gateway, il DNS e molte altre opzioni.

---

### **2. Caratteristiche fondamentali**

#### **Estensioni rispetto a BOOTP**

1. **Assegnazione temporanea (lease)**  
    Ogni indirizzo IP viene concesso per un **tempo limitato**, chiamato **lease time**.  
    Al termine del lease, l’host deve:
    
    - rinnovarlo (se ancora collegato);
        
    - oppure restituirlo al server per essere riassegnato.
        
2. **Configurazione completa**  
    Oltre all’indirizzo IP, il client riceve anche:
    
    - maschera di sottorete
        
    - indirizzo del gateway predefinito
        
    - indirizzo del server DNS
        
    - nome del dominio
        
    - indirizzo del server NTP (sincronizzazione oraria)
        
    - altri parametri opzionali di rete
        
3. **Compatibilità con BOOTP**  
    DHCP è pienamente retrocompatibile: un server DHCP può rispondere anche a **client BOOTP**.
    

---

### **3. Tipi di messaggi DHCP**

Tutte le comunicazioni DHCP si basano su **pacchetti UDP** con una struttura derivata da quella del BOOTP.  
Il **tipo di messaggio** viene specificato come **opzione** nel campo “options” del pacchetto.

#### **Messaggi principali**

|Tipo di messaggio|Funzione|Direzione|
|---|---|---|
|**DHCPDISCOVER**|Il client cerca un server DHCP disponibile|Client → Broadcast|
|**DHCPOFFER**|Il server propone un indirizzo e i parametri di configurazione|Server → Broadcast|
|**DHCPREQUEST**|Il client accetta una delle offerte ricevute|Client → Broadcast|
|**DHCPACK**|Il server conferma la concessione (lease)|Server → Client|
|**DHCPNAK**|Il server nega la richiesta (ad esempio, lease scaduto)|Server → Client|
|**DHCPRELEASE**|Il client rilascia l’indirizzo IP|Client → Server|
|**DHCPRENEW / DHCPREBIND**|Il client richiede il rinnovo del lease|Client → Server|

---

### **4. Il ciclo di vita di un lease DHCP**

Il processo di assegnazione dinamica può essere suddiviso in **cinque fasi principali**, spesso abbreviate come **DORA**:

#### **1. DHCP Discover**

All’avvio, il client non conosce alcun indirizzo IP, quindi invia un **pacchetto broadcast** all’indirizzo:  
$$  
255.255.255.255  
$$  
per cercare un server DHCP attivo nella rete.

![](imgs/Pasted%20image%2020260213051738.png)

#### **2. DHCP Offer**

Ogni server DHCP che riceve la richiesta risponde con un messaggio **DHCPOFFER**, che include:

- un indirizzo IP disponibile;
    
- la durata del lease;
    
- e gli altri parametri di rete (maschera, gateway, DNS...).
    
![](imgs/Pasted%20image%2020260213052420.png)

Il client può ricevere più offerte contemporaneamente.

#### **3. DHCP Request**

Il client seleziona una delle offerte e invia un messaggio **DHCPREQUEST** per accettarla ufficialmente.  
Anche questo messaggio è **broadcast**, così gli altri server sanno che la loro offerta non è stata scelta.

![](imgs/Pasted%20image%2020260213052433.png)

A questo punto, il client può **iniziare a utilizzare l’indirizzo IP**.

#### **4. DHCP Acknowledge (ACK)**

Il server conferma la concessione del lease con un messaggio **DHCPACK**, rendendo l’assegnazione effettiva.

Da questo momento il client è completamente configurato e può comunicare nella rete.

#### **5. DHCP Release / Rinnovo**

- Quando il **50% del tempo di lease è scaduto**, il client tenta un **rinnovo** (messaggio DHCPREQUEST).
    
- Se il server risponde con un **DHCPACK**, il lease viene esteso.
    
- Se invece invia un **DHCPNACK**, l’indirizzo viene **rilasciato** e il client deve richiederne uno nuovo.
    
- Alla fine del lease o allo spegnimento del dispositivo, il client invia un **DHCPRELEASE** per restituire l’indirizzo al server.

![](imgs/Pasted%20image%2020260213052452.png)

![](imgs/Pasted%20image%2020260213052507.png)

---

### **5. Opzioni DHCP**

Oltre ai messaggi base, il DHCP consente di trasmettere numerose **opzioni di configurazione**, che arricchiscono l’informazione fornita al client.

#### **Esempi di opzioni comuni**

- **1** → Maschera di sottorete
    
- **3** → Gateway predefinito
    
- **6** → Server DNS
    
- **12** → Nome host del client
    
- **15** → Nome del dominio
    
- **28** → Indirizzo broadcast
    
- **51** → Durata del lease
    
- **53** → Tipo di messaggio DHCP
    
- **58** → Tempo di rinnovo (T1)
    
- **59** → Tempo di rebinding (T2)
    
- **66** → Server TFTP o STP (per boot remoto)
    
- **69** → Server SMTP
    
- **71** → Nome della stampante di rete
    

Ogni opzione ha una **lunghezza e un formato predefiniti**, ma può essere ignorata se non supportata dal client.

---

### **6. Rinnovo e rilascio**

Durante il periodo di lease, il client monitora il **tempo residuo** e si comporta così:

#### **Fase di rinnovo (T1 = 50% del lease)**

- Il client tenta di **rinnovare l’indirizzo** contattando direttamente il server che lo ha concesso.
    
- Se il server risponde con un **ACK**, il lease viene rinnovato.
    

#### **Fase di rebinding (T2 = 87,5% del lease)**

- Se il server originario non risponde, il client invia una richiesta broadcast per **qualsiasi server DHCP disponibile**.
    

#### **Rilascio**

- Quando il lease scade o il dispositivo si disconnette, il client invia un **DHCPRELEASE** per liberare l’indirizzo.
    
- A questo punto, l’IP torna disponibile nel **pool del server**.
    

---

### **7. Schema riassuntivo del processo DORA**

$$  
\begin{array}{lll}  
\textbf{Client} & \quad & \textbf{Server DHCP} \\  
\hline  
\text{DHCPDISCOVER} & \longrightarrow & \text{(broadcast: ricerca server)} \\  
\text{DHCPOFFER} & \longleftarrow & \text{(proposta con IP e parametri)} \\  
\text{DHCPREQUEST} & \longrightarrow & \text{(accettazione dell’offerta)} \\  
\text{DHCPACK} & \longleftarrow & \text{(conferma e lease attivo)} \\  
\text{(eventuale DHCPRENEW)} & \longrightarrow & \text{(rinnovo lease)} \\  
\text{(DHCPRELEASE)} & \longrightarrow & \text{(rilascio indirizzo)} \\  
\end{array}  
$$

---

### **8. Conclusione**

Il **DHCP** è oggi uno dei protocolli più diffusi e indispensabili nel mondo TCP/IP.  
Grazie a esso:

- le reti si configurano **automaticamente**;
    
- si evitano **errori e conflitti di indirizzo**;
    
- si semplifica la **gestione centralizzata** degli host.
    

Ogni volta che un dispositivo si collega a una rete — dal laptop allo smartphone — è il DHCP a fornire **tutto ciò che serve per comunicare**, rendendolo il pilastro invisibile dell’infrastruttura IP moderna.