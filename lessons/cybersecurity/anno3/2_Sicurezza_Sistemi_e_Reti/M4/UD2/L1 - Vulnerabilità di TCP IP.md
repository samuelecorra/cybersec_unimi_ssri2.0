# **UD2 – Vulnerabilità di TCP/IP**

Questa unità introduce le **principali debolezze** dei protocolli TCP/IP e le **tipologie di attacco** che ne derivano.  
Si analizzano i limiti strutturali della suite — come l’assenza di autenticazione, la gestione debole delle connessioni e la fiducia implicita tra host — per comprendere come vengano sfruttati in attacchi di **spoofing**, **sniffing**, **man-in-the-middle** e **denial of service**.  
L’obiettivo è riconoscere le vulnerabilità più comuni e individuare le **contromisure** per mitigare i rischi a livello di rete e di trasporto.


---

## **Lezione 1: Vulnerabilità di TCP/IP**

### **1. Tipi di indirizzi in Internet**

In Internet coesistono diversi tipi di indirizzi, ciascuno associato a un livello specifico del modello di rete.

|**Livello**|**Tipo di indirizzo**|**Esempio**|**Dimensione**|
|---|---|---|---|
|**Accesso alla rete**|Indirizzo MAC (_Media Access Control_)|`00:23:A2:D6:F2:15`|48 o 64 bit|
|**Rete (Network Layer)**|Indirizzo IP|`128.3.23.3`|32 bit (IPv4) / 128 bit (IPv6)|
|**Trasporto (Transport Layer)**|IP + Porta|`128.3.23.3:80`|—|
|**Applicazione (Application Layer)**|Nome di dominio|`www.purdue.edu`|—|

#### **Descrizione sintetica**

- **MAC address:** associato alla **scheda di rete (NIC)**, identifica univocamente un dispositivo fisico nella LAN.
    
- **IP address:** utilizzato per **instradare pacchetti** sulla rete globale (IPv4 o IPv6).
    
- **Porte di trasporto:** distinguono le applicazioni all’interno di uno stesso host.
    
- **Nomi di dominio:** forniscono una **rappresentazione simbolica e leggibile** degli indirizzi IP.
    

---

### **2. Routing e traduzione degli indirizzi**

Il corretto funzionamento di Internet si basa su una serie di **meccanismi di traduzione e instradamento** che collegano i diversi livelli del modello di rete.

#### **Meccanismi principali**

- **Traduzione IP ↔ MAC:**
    
    - IPv4 utilizza l’**ARP (Address Resolution Protocol)**.
        
    - IPv6 usa il **Neighbour Discovery Protocol (NDP)**.
        
- **Routing IP:**
    
    - I protocolli **TCP, UDP e IP** permettono di instradare pacchetti tra host.
        
    - L’aggiornamento delle **tabelle di routing** avviene tramite protocolli come il **BGP (Border Gateway Protocol)**.
        
- **Traduzione IP ↔ Nome di dominio:**
    
    - Gestita dal **DNS (Domain Name System)**, che converte i nomi simbolici (es. `www.unimi.it`) in indirizzi IP numerici.
        

> Questi meccanismi, pur essendo essenziali, rappresentano anche **punti critici di vulnerabilità**, in quanto non sempre includono meccanismi di autenticazione o verifica dell’integrità.

---

### **3. Vulnerabilità per livello di rete**

La sicurezza dei protocolli TCP/IP può essere analizzata a partire dai diversi **livelli dello stack di rete**, evidenziando i rischi associati a ciascuno.

|**Livello**|**Esempi di protocolli**|**Principali vulnerabilità**|
|---|---|---|
|**Applicazione**|HTTP, FTP, NFS|Phishing, exploit software, version rollback|
|**Trasporto**|TCP, UDP|SYN Flooding, predizione del numero di sequenza|
|**Rete**|IP, RIP|IP Spoofing, attacchi di routing|
|**Collegamento**|Ethernet, 802.11|ARP Spoofing, WEP cracking|
|**Fisico**|Bit, RF|Fingerprinting, Denial of Service a livello radio|

> Nessun livello è completamente immune: la sicurezza deve quindi essere **multilivello** e **coordinata**.

---

### **4. Minacce principali nelle reti**

Le minacce alla sicurezza delle reti TCP/IP si possono classificare secondo le tre proprietà fondamentali della **triade CIA**:

|**Obiettivo compromesso**|**Tipo di attacco**|**Descrizione**|
|---|---|---|
|**Confidenzialità**|_Packet sniffing_|Intercettazione passiva dei pacchetti in transito.|
|**Integrità**|_Session hijacking_|Manipolazione o dirottamento di una sessione TCP attiva.|
|**Disponibilità**|_Denial of Service (DoS)_|Sovraccarico delle risorse di rete per impedirne l’uso legittimo.|
|**Attacchi comuni**|_Translation poisoning, Routing attacks_|Corruzione delle tabelle DNS o di routing per deviare il traffico.|

---

### **5. Principali vulnerabilità dei protocolli**

Le debolezze più rilevanti della suite TCP/IP derivano da scelte progettuali orientate alla **funzionalità piuttosto che alla sicurezza**.

|**Componente**|**Problema di sicurezza**|**Effetto**|
|---|---|---|
|**ARP**|Nessuna autenticazione|Possibile ARP Spoofing o avvelenamento ARP.|
|**Livello IP**|Pacchetti attraversano host non fidati|Rischio di sniffing e intercettazione.|
|**TCP**|Predizione facile dello stato della connessione|Attacchi di spoofing TCP e session hijacking.|
|**Accesso libero (No access control)**|Mancanza di limitazioni native|Vulnerabilità agli attacchi DoS.|
|**DNS**|Assenza di autenticazione e validazione|DNS Spoofing e Cache Poisoning.|

> Queste vulnerabilità sono **intrinseche** alla progettazione originaria di Internet, che nasceva in un contesto di fiducia tra nodi cooperanti.

---

### **6. Interfaccia di rete e sniffing**

Ogni computer può avere una o più **interfacce di rete** (Ethernet, Wi-Fi, ecc.), che gestiscono la trasmissione e la ricezione dei pacchetti.

- In condizioni normali, un’interfaccia riceve **solo i frame destinati al proprio MAC address**.
    
- Tuttavia, è possibile configurare la scheda in **modalità promiscua**, permettendo di leggere **tutti i frame in transito** sulla rete locale.
    

> Questa modalità è alla base del **packet sniffing**, una tecnica usata per analizzare il traffico (a fini legittimi o malevoli).  
> Strumenti come **Wireshark** o **tcpdump** consentono di catturare pacchetti, decodificarli e visualizzarne i contenuti.

---

### **7. Sintesi finale**

Il modello TCP/IP, pur essendo la colonna portante di Internet, presenta **vulnerabilità strutturali** dovute alla sua natura aperta e interoperabile.  
I protocolli originari (ARP, IP, TCP, DNS) **non prevedono autenticazione o cifratura nativa**, rendendoli suscettibili a manipolazioni e intercettazioni.

> In sintesi:
> 
> - Internet è nata come rete “di fiducia”, non “di sicurezza”.
>     
> - Ogni livello dello stack può essere sfruttato come vettore d’attacco.
>     
> - La sicurezza moderna richiede **strati aggiuntivi di protezione** (firewall, IDS/IPS, crittografia end-to-end, DNSSEC, VPN).
>     


---