## **Lezione 4: Access Control List (ACL)**

### **1. Introduzione alle ACL**

Le **Access Control List (ACL)** sono insiemi di regole utilizzate per **filtrare il traffico di rete** nei dispositivi di instradamento, come i router o i firewall.  
Ogni pacchetto che transita nel dispositivo viene confrontato con le regole definite in una ACL per decidere se **consentirne il passaggio (ACCEPT)** o **bloccarlo (DENY)**.

Questo meccanismo rappresenta il **cuore del filtraggio statico** dei pacchetti e costituisce il metodo più diffuso nei sistemi di rete **Cisco**.

---

### **2. Semantica e principio di funzionamento**

Le ACL operano secondo una **semantica binaria**:

$$  
\text{Azione} \in { \text{ACCEPT}, \text{DENY} }  
$$

e vengono valutate **in ordine sequenziale (criterio top-down)**.  
Il primo match trovato determina la decisione finale sul pacchetto; se nessuna regola corrisponde, si applica la **regola di default**.

#### **Regola di default**

Alla fine di ogni ACL esiste implicitamente una regola di tipo:

$$  
\text{deny any}  
$$

che **blocca tutto il traffico non esplicitamente autorizzato**.

> Le ACL seguono il principio del _first match wins_.

---

### **3. Strategie di default**

Le ACL possono essere impostate secondo due strategie generali:

- **Default Permit:** tutto è permesso, tranne ciò che è esplicitamente vietato.  
    → _Politica permissiva, ma rischiosa._
    
- **Default Deny:** tutto è vietato, tranne ciò che è esplicitamente consentito.  
    → _Politica restrittiva e consigliata._
    

---

### **4. Perché usare le ACL**

Le ACL vengono utilizzate per diversi scopi pratici:

- **Limitare il traffico indesiderato**, migliorando le prestazioni della rete.
    
- **Controllare la diffusione di aggiornamenti di routing**, riducendo l’overhead.
    
- **Aggiungere un ulteriore livello di sicurezza** a firewall e proxy.
    
- **Definire quali aree di rete sono accessibili** a determinati client.
    
- **Filtrare tipi di traffico specifici** in ingresso o in uscita.
    

---

### **5. Tipologie di ACL (secondo gli standard Cisco)**

Cisco distingue due tipi principali di ACL:

|Tipo|Range numerico|Criteri di filtraggio|Livello OSI|
|---|---|---|---|
|**Standard ACL**|0–99|Solo indirizzo IP sorgente|Network (Layer 3)|
|**Extended ACL**|100–199|IP sorgente, destinazione, protocollo, porte TCP/UDP, tipo ICMP|Network/Transport (Layer 3–4)|

---

### **6. Struttura delle ACL standard**

Le ACL standard seguono la sintassi:

```
access-list <numero> <azione> <sorgente> [wildcard] | any
```

- **Numero:** da 0 a 99
    
- **Azione:** `permit` o `deny`
    
- **Sorgente:** indirizzo IP sorgente
    
- **Wildcard:** maschera che indica quali bit dell’indirizzo verificare
    
- **Any:** rappresenta “qualsiasi indirizzo”
    

#### **Esempio**

```
access-list 20 permit 192.168.1.0 0.0.0.255
```

Permette il traffico proveniente dalla sottorete `192.168.1.0/24`.

---

### **7. La Wildcard Mask**

La **wildcard mask** determina quali bit dell’indirizzo IP devono essere verificati.  
È simile alla _netmask_, ma **inverte la semantica dei bit**:

|Bit wildcard|Significato|
|---|---|
|`0`|Il bit dell’indirizzo IP deve essere verificato|
|`1`|Il bit dell’indirizzo IP viene ignorato|

#### **Esempio**

```
access-list 20 permit 192.168.1.0 0.0.0.255
```

Controlla solo l’ultimo byte dell’indirizzo (poiché la wildcard `0.0.0.255` ignora i primi 24 bit).

> Le wildcard vengono utilizzate anche in protocolli di routing come **EIGRP** e **OSPF**.

---

### **8. Esempi di ACL standard**

```
access-list 17 permit host 192.168.1.100
access-list 17 deny 192.168.1.0 0.0.0.255
access-list 17 permit any
```

- La parola chiave **host** equivale alla wildcard `0.0.0.0` (match esatto su un singolo IP).
    
- Se non specificato diversamente, Cisco applica **implicitamente `deny any`** alla fine della lista.
    

---

### **9. Ingress ed Egress firewall**

|Tipo|Direzione del traffico|Descrizione|
|---|---|---|
|**Ingress firewall**|Entrante|Controlla le connessioni che arrivano dall’esterno. Utile per proteggere servizi pubblici.|
|**Egress firewall**|Uscente|Controlla le connessioni originate dall’interno. Utile per monitorare l’attività del personale.|

> È facile distinguere i due tipi nei protocolli orientati alla connessione (es. TCP), più complesso per protocolli _connectionless_ (es. ICMP, UDP).

---

### **10. Filtraggio dei pacchetti**

Un router può applicare filtri in ingresso e in uscita secondo lo schema:

```
INCOMING PACKETS → [Filtro Ingress] → Forwarding → [Filtro Egress] → OUTGOING PACKETS
```

In questo modo il firewall controlla entrambi i flussi, impedendo che pacchetti non autorizzati entrino o escano.

---

### **11. Esempio di Ingress ACL**

```
access-list 14 deny 10.0.0.0 0.255.255.255
access-list 14 deny 127.0.0.0 0.255.255.255
access-list 14 deny 172.16.0.0 0.15.255.255
access-list 14 deny 192.168.0.0 0.0.255.255
access-list 14 deny <rete_interna> <wildcard>
access-list 14 permit any
```

Queste regole **bloccano tutto il traffico in ingresso** con indirizzi IP di rete locale (RFC 1918).  
Servono a **evitare attacchi provenienti da indirizzi privati mascherati come pubblici**.

---

### **12. Attacchi DDoS con indirizzi locali**

Esistono casi documentati di attacchi **DDoS** condotti interamente usando indirizzi IP locali (RFC 1918).  
Se l’**ISP** avesse applicato correttamente il filtraggio in ingresso, l’attacco **non avrebbe avuto effetto**.

> Da qui nasce la necessità del filtraggio coerente “ingress/egress” a livello di ISP.

---

### **13. RFC 2827 – Filtraggio raccomandato**

La **RFC 2827** (anche nota come _Ingress Filtering_) stabilisce le buone pratiche per evitare spoofing e attacchi:

- Ogni pacchetto **in uscita** deve avere un **indirizzo sorgente interno alla rete assegnata**.
    
- Ogni pacchetto **in ingresso** non deve mai contenere un indirizzo sorgente appartenente a quella rete.
    

#### **Esempio**

Per una rete `192.0.2.0/24`:

- Tutti i pacchetti in uscita devono avere IP sorgente ∈ `192.0.2.0/24`;
    
- Tutti i pacchetti in ingresso con IP sorgente in quel range devono essere **bloccati**.
    

---

### **14. Egress ACL**

```
access-list 14 permit <rete_interna> <wildcard>
access-list 14 deny any
```

Serve per bloccare ogni traffico in uscita non proveniente dalla rete interna legittima.

---

### **15. Extended ACL: formato generale**

```
access-list <numero> <azione> <tipo> <sorgente> [wildcard] <opzioni> <destinazione> [wildcard] [log]
```

|Campo|Descrizione|
|---|---|
|**Numero**|da 100 a 199|
|**Azione**|`permit` / `deny`|
|**Tipo**|Protocollo (IP, TCP, UDP, ICMP)|
|**Sorgente/Destinazione**|Indirizzi IP|
|**Opzioni**|Porte TCP/UDP o tipo/codice ICMP|
|**Log**|(opzionale) registra gli eventi nel log|

---

### **16. Operatori nelle Extended ACL**

Le Extended ACL consentono di specificare **porte** o **condizioni logiche** tramite operatori:

|Operatore|Significato|
|---|---|
|`eq`|equal → porta esatta|
|`neq`|not equal|
|`gt`|greater than|
|`lt`|less than|

Esempi:

```
access-list 101 permit tcp 192.168.2.0 0.0.0.255 any eq 23
access-list 101 permit tcp 192.168.2.0 0.0.0.255 any eq 21
```

oppure con keyword:

```
access-list 101 permit tcp 192.168.2.0 0.0.0.255 any eq ftp
```

---

### **17. Operatore “established”**

L’operatore **`established`** serve a filtrare il traffico in ingresso **solo se la sessione TCP è già attiva**.  
Il filtro verifica la presenza dei flag **ACK** o **RST**:

```
access-list 102 permit tcp any 192.168.2.0 0.0.0.255 established
```

> Questo evita attività di _port scanning_ o connessioni non richieste.

---

### **18. Esempio di configurazione – Navigazione Web**

#### **Obiettivo**

Consentire solo la navigazione web (HTTP/HTTPS) dalla rete interna `192.168.2.0/24`.

#### **Configurazione**

```
access-list 101 permit tcp 192.168.2.0 0.0.0.255 any eq 80
access-list 101 permit tcp 192.168.2.0 0.0.0.255 any eq 443
access-list 102 permit tcp any 192.168.2.0 0.0.0.255 established
```

- La **ACL 101** consente connessioni **in uscita** verso porte 80 e 443.
    
- La **ACL 102** consente **solo il traffico di ritorno** (risposte HTTP/HTTPS già stabilite).
    

> Tutto il traffico in ingresso non associato a connessioni esistenti viene negato.

---

### **19. Applicazione delle ACL alle interfacce**

Per associare una ACL a un’interfaccia di rete:

```
Router(config-if)# ip access-group <numero> {in | out}
```

- `in` → applicata al traffico in ingresso prima del routing.
    
- `out` → applicata al traffico in uscita dopo il routing.
    
- Se non specificato, il default è **out**.
    

#### **Esempio**

```
R1(config)# interface s0/0/0
R1(config-if)# ip access-group 101 out
R1(config-if)# ip access-group 102 in
```

---

### **20. Conclusione**

Le **ACL** sono strumenti potenti per **controllare e segmentare il traffico di rete**, ma richiedono:

- una **pianificazione accurata** delle regole,
    
- una **comprensione profonda delle direzioni (in/out)**,
    
- e una **politica di sicurezza coerente** (idealmente _default deny_).
    

> Le ACL sono la base del filtraggio nei firewall e nei router: semplici, efficaci, ma da usare con estrema precisione per evitare errori o vulnerabilità.