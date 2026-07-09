## **Lezione 4: Access Control List (ACL)**

### **1. Introduzione alle ACL**

Le **Access Control List (ACL)** sono insiemi di regole utilizzate per **filtrare il traffico di rete** nei dispositivi di instradamento, come i router o i firewall.  
Ogni pacchetto che transita nel dispositivo viene confrontato con le regole definite in una ACL per decidere se **consentirne il passaggio (ACCEPT)** o **bloccarlo (DENY)**.

Questo meccanismo rappresenta il **cuore del filtraggio statico** dei pacchetti e costituisce il metodo più diffuso nei sistemi di rete **Cisco**.

Nel contesto Cisco, le ACL sono particolarmente utili anche a fini didattici: permettono di vedere concretamente cosa significa applicare una regola di filtraggio a una specifica interfaccia di un router o di un firewall packet filter.

---

### **2. Semantica e principio di funzionamento**

Le ACL operano secondo una **semantica binaria**:

$$  
\text{Azione} \in \{ \text{ACCEPT}, \text{DENY} \}  
$$

e vengono valutate **in ordine sequenziale (criterio top-down)**.  
Il primo match trovato determina la decisione finale sull’intero pacchetto; la verifica continua solo finché non viene trovata una regola corrispondente. Se nessuna regola corrisponde, si applica la **regola di default**.

#### **Regola di default**

Alla fine di ogni ACL esiste implicitamente una regola di tipo:

$$  
\text{deny any}  
$$

che **blocca tutto il traffico non esplicitamente autorizzato**.

> 📌 Le ACL seguono il principio del _first match wins_: l’ordine delle regole è parte integrante della politica di sicurezza.

---

### **3. Strategie di default**

Le ACL possono essere impostate secondo due strategie generali:

- **Default Permit:** tutto è permesso, tranne ciò che è esplicitamente vietato.  
    → _Politica permissiva, ma rischiosa._
    
- **Default Deny:** tutto è vietato, tranne ciò che è esplicitamente consentito.  
    → _Politica restrittiva e consigliata._
    

Nei router Cisco, se non viene specificata una regola finale permissiva, il comportamento implicito è assimilabile a un **default deny**. Una politica di tipo **default permit** si ottiene invece inserendo esplicitamente una regola finale ampia, ad esempio `permit any`, dopo eventuali regole di blocco.

---

### **4. Perché usare le ACL**

Le ACL vengono utilizzate per diversi scopi pratici:

- **Limitare il traffico indesiderato**, migliorando le prestazioni della rete.
    
- **Controllare la diffusione di aggiornamenti di routing**, riducendo l’overhead.
    
- **Aggiungere un ulteriore livello di sicurezza** a firewall e proxy.
    
- **Definire quali aree di rete sono accessibili** a determinati client.
    
- **Filtrare tipi di traffico specifici** in ingresso o in uscita.
    

- **Controllare o indirizzare il traffico** verso zone di rete sottoposte a controlli specifici.
    
- **Limitare le attività dall’interno verso l’esterno**, ad esempio per controllare traffico generato dai PC aziendali.
    

---

### **5. Tipologie di ACL (secondo gli standard Cisco)**

Cisco distingue due tipi principali di ACL:

|Tipo|Range numerico|Criteri di filtraggio|Livello OSI|
|---|---|---|---|
|**Standard ACL**|0–99|Solo indirizzo IP sorgente|Network (Layer 3)|
|**Extended ACL**|100–199|IP sorgente, destinazione, protocollo, porte TCP/UDP, tipo ICMP|Network/Transport (Layer 3–4)|

Le ACL standard effettuano quindi un **pre-screening molto semplice** basato sulla sola sorgente. Le ACL estese sono più espressive perché permettono di includere anche destinazione, protocollo, porte e codici ICMP.

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

Verifica i primi 24 bit dell’indirizzo e ignora l’ultimo byte: la wildcard `0.0.0.255` corrisponde quindi alla logica della netmask `255.255.255.0` e identifica tutta la rete `192.168.1.x`.

> 📌 La wildcard ha semantica opposta rispetto alla netmask: `0` significa “controlla questo bit”, `1` significa “ignora questo bit”.

> Le wildcard vengono utilizzate anche in protocolli di routing come **EIGRP** e **OSPF**.

---

### **8. Esempi di ACL standard**

```
access-list 17 permit host 192.168.1.100
access-list 17 deny 192.168.1.0 0.0.0.255
access-list 17 permit any
```

- La parola chiave **host** equivale alla wildcard `0.0.0.0` (match esatto su un singolo IP).
    
- La parola chiave **any** equivale a “qualsiasi sorgente”.
    
- Se non specificato diversamente, Cisco applica **implicitamente `deny any`** alla fine della lista.
    

Nell’esempio, `permit any` rende esplicita una politica finale permissiva: prima si consente l’host specifico, poi si nega una rete, infine si permette tutto il resto. Senza l’ultima regola, tutto ciò che non ha fatto match sarebbe bloccato dalla regola implicita.


---

### **9. Ingress ed Egress firewall**

|Tipo|Direzione del traffico|Descrizione|
|---|---|---|
|**Ingress firewall**|Entrante|Controlla le connessioni che arrivano dall’esterno. Utile per proteggere servizi pubblici.|
|**Egress firewall**|Uscente|Controlla le connessioni originate dall’interno. Utile per monitorare l’attività del personale.|

Il traffico **ingress** comprende sia richieste provenienti dall’esterno verso servizi offerti dalla rete, sia traffico di ritorno collegato a comunicazioni avviate dall’interno. Il traffico **egress**, invece, è tipicamente quello generato dai client interni verso Internet.

> È facile distinguere i due tipi nei protocolli orientati alla connessione (es. TCP), perché il SYN chiarisce chi sta aprendo la connessione; è più complesso per protocolli _connectionless_ come ICMP e UDP.

---

### **10. Filtraggio dei pacchetti**

Un router può applicare filtri in ingresso e in uscita secondo lo schema:

![](imgs/Pasted%20image%2020260709143254.png)

In questo modo il firewall controlla entrambi i flussi, impedendo che pacchetti non autorizzati entrino o escano.

Più precisamente:

- un filtro **in ingresso** viene applicato quando il pacchetto arriva sull’interfaccia;
    
- poi il router decide, tramite forwarding/routing, verso quale rete inviare il pacchetto, ad esempio LAN o DMZ;
    
- un filtro **in uscita** può essere applicato prima che il pacchetto lasci l’interfaccia di destinazione.
    

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

Il principio è semplice: se l’interfaccia è rivolta verso Internet, non dovrebbe arrivare traffico con sorgente privata, perché gli indirizzi RFC 1918 non sono instradabili sulla rete pubblica. Un pacchetto in ingresso con sorgente privata è quindi sospetto, tipicamente spoofato o comunque incoerente con la provenienza dichiarata.

---

### **12. Attacchi DDoS con indirizzi locali**

Esistono casi documentati di attacchi **DDoS** condotti interamente usando indirizzi IP locali (RFC 1918).  
Se l’**ISP** avesse applicato correttamente il filtraggio in ingresso, l’attacco **non avrebbe avuto effetto**.

Il problema è che le richieste raggiungono la vittima, ma le risposte non possono essere instradate correttamente verso indirizzi privati non routabili su Internet. Un filtraggio tempestivo da parte degli ISP evita che traffico evidentemente non valido venga propagato fino al bersaglio.

> 📌 Da qui nasce la necessità del filtraggio coerente “ingress/egress” a livello di ISP.

---

### **13. RFC 2827 – Filtraggio raccomandato**

La **RFC 2827** (anche nota come _Ingress Filtering_) stabilisce le buone pratiche per evitare spoofing e attacchi:

- Ogni pacchetto **in uscita** deve avere un **indirizzo sorgente interno alla rete assegnata**.
    
- Ogni pacchetto **in ingresso** non deve mai contenere un indirizzo sorgente appartenente a quella rete.
    

#### **Esempio**

Per una rete `192.0.2.0/24`:

- Tutti i pacchetti in uscita devono avere IP sorgente ∈ `192.0.2.0/24`;
    
- Tutti i pacchetti in ingresso con IP sorgente in quel range devono essere **bloccati**.

Questa impostazione protegge anche da attività malevole originate dall’interno: se un host interno prova a inviare pacchetti con indirizzo sorgente falsificato, l’egress filtering dovrebbe bloccarli perché non appartengono al range assegnato alla rete.
    

---

### **14. Egress ACL**

```
access-list 14 permit <rete_interna> <wildcard>
access-list 14 deny any
```

Serve per bloccare ogni traffico in uscita non proveniente dalla rete interna legittima.

L’ordine è essenziale: prima si permette ciò che appartiene alla rete autorizzata, poi si nega tutto il resto. Se le regole fossero invertite, il `deny any` bloccherebbe ogni pacchetto prima di arrivare alla regola permissiva.

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

L’opzione `log` è utile quando si vuole osservare quali pacchetti fanno match con una certa regola: ogni pacchetto corrispondente viene registrato, permettendo attività di controllo e diagnosi.

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

Per le porte note si possono usare keyword al posto del numero: ad esempio `ftp` per la porta 21, `ftp-data` per la porta 20 e `telnet` per la porta 23. Questo rende le regole più leggibili, ma il significato resta quello di un confronto sulla porta TCP/UDP.

![](imgs/Pasted%20image%2020260709143537.png)

---

### **17. Operatore “established”**

L’operatore **`established`** serve a filtrare il traffico in ingresso **solo se la sessione TCP è già attiva**.  
Il filtro verifica la presenza dei flag **ACK** o **RST**:

```
access-list 102 permit tcp any 192.168.2.0 0.0.0.255 established
```

Questo operatore è usato per consentire traffico di ritorno coerente con connessioni iniziate dall’interno, ad esempio risposte HTTP/HTTPS a richieste partite dalla LAN.

> ⚠️ `established` non è una state table completa: nelle ACL Cisco classiche il controllo è basato sui flag TCP, quindi è meno robusto di un vero firewall stateful.

---

### **18. Esempio di configurazione – Navigazione Web**

#### **Obiettivo**

Consentire solo la navigazione web (HTTP/HTTPS) dalla rete interna `192.168.2.0/24`.

![](imgs/Pasted%20image%2020260709143557.png)

#### **Configurazione**

```
access-list 101 permit tcp 192.168.2.0 0.0.0.255 any eq 80
access-list 101 permit tcp 192.168.2.0 0.0.0.255 any eq 443
access-list 102 permit tcp any 192.168.2.0 0.0.0.255 established
```

- La **ACL 101** consente connessioni **in uscita** verso porte 80 e 443.
    
- La **ACL 102** consente **solo il traffico di ritorno** (risposte HTTP/HTTPS già stabilite).
    

La logica è questa: HTTP e HTTPS richiedono una connessione in uscita verso un server web, rispettivamente su porta 80 o 443, ma la pagina deve poi tornare al client interno. La ACL 102 permette quindi le risposte solo se appaiono associate a una comunicazione già stabilita; risposte finte o pacchetti non legati a richieste interne vengono bloccati.

> 📌 Tutto il traffico in ingresso non associato a connessioni esistenti viene negato.

---

### **19. Applicazione delle ACL alle interfacce**

Per associare una ACL a un’interfaccia di rete:

```
Router(config-if)# ip access-group <numero> {in | out}
```

- `in` → applicata al traffico in ingresso prima del routing.
    
- `out` → applicata al traffico in uscita dopo il routing.
    
- Se non specificato, il default è **out**.
    

Il punto di applicazione è decisivo: la stessa ACL può produrre effetti diversi se applicata a un’interfaccia diversa o nella direzione sbagliata.

![](imgs/Pasted%20image%2020260709143710.png)


#### **Esempio**

```
R1(config)# interface s0/0/0
R1(config-if)# ip access-group 101 out
R1(config-if)# ip access-group 102 in
R1#(config)#end
```

Nell’esempio, l’interfaccia `s0/0/0` è quella vicina alla rete `192.168.2.0/24` nel percorso verso Internet: la ACL 101 viene applicata in uscita per controllare le richieste web che lasciano la rete, mentre la ACL 102 viene applicata in ingresso per controllare le risposte che rientrano da Internet.

---

### **20. Conclusione**

Le **ACL** sono strumenti potenti per **controllare e segmentare il traffico di rete**, ma richiedono:

- una **pianificazione accurata** delle regole,
    
- una **comprensione profonda delle direzioni (in/out)**,
    
- e una **politica di sicurezza coerente** (idealmente _default deny_).
    

> 📌 Le ACL sono la base del filtraggio nei firewall e nei router: semplici, efficaci, ma da usare con estrema precisione per evitare errori o vulnerabilità.
