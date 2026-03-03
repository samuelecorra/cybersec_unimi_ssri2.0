## **Lezione 2: New Generation Packet Filtering**

### **1. Limiti del filtraggio statico**

Il principale limite del **packet filtering statico** è che per garantire la comunicazione desiderata è necessario **lasciare aperta un’intera gamma di porte**.  
Questo approccio crea un **perimetro di rete esposto**, in cui molte porte rimangono aperte anche quando **non sono realmente necessarie**, favorendo così potenziali attacchi.

Inoltre:

- Lo static packet filter permette vari **attacchi diretti agli host interni**.
    
- Gli amministratori, spesso concentrati sulla sicurezza perimetrale, **trascurano la protezione interna**, lasciando vulnerabili i sistemi più sensibili.
    

> In sintesi: lo static filtering è semplice ma rigido — e ogni rigidità, in sicurezza, diventa un punto debole.

---

## **2. Dynamic Packet Filter**

### **2.1. Concetto generale**

Per superare i limiti del filtraggio statico, nascono i **Dynamic Packet Filter (DPF)**.  
Questi sistemi **aprono e chiudono dinamicamente le porte** del firewall in base alle informazioni presenti negli header dei pacchetti che attraversano il sistema.

#### **Funzionamento**

1. Quando viene avviata una connessione, il firewall **apre temporaneamente la porta** necessaria.
    
2. Una volta terminato il flusso di pacchetti, la porta **viene automaticamente chiusa**.
    

In questo modo:

- Si riduce il numero di porte aperte contemporaneamente.
    
- Diminuisce drasticamente la possibilità di sfruttare vulnerabilità passive.
    

> Il dynamic packet filter trasforma il firewall da “muro statico” a “porta intelligente”: apre solo quando serve e solo per il tempo necessario.

---

### **2.2. Vantaggi**

- Le **porte restano aperte solo per brevi periodi**.
    
- Supporta la maggior parte dei servizi di rete.
    
- Riduce la possibilità di successo di molti attacchi comuni (es. TCP hijacking o port scanning).
    

> La finestra temporale d’attacco si riduce, rendendo molto più difficile replicare un exploit.

---

### **2.3. Svantaggi**

- I DPF sono **application-unaware**, ovvero non comprendono il significato dei protocolli applicativi.
    
- Consentono connessioni IP dirette verso host interni, senza distinguere il contenuto o la logica del traffico.
    
- Non offrono **autenticazione integrata**: una volta stabilita la connessione, tutto dipende dal sistema target.
    

> I DPF migliorano la sicurezza rispetto agli statici, ma rimangono ciechi a ciò che accade “dentro” la connessione.

---

## **3. Stateful Packet Filter**

### **3.1. Concetto**

Lo **stateful packet filtering** rappresenta un’evoluzione del modello dinamico: è un **firewall “state-aware”**.  
Tiene traccia delle **informazioni di stato** provenienti dal **livello di trasporto** (TCP/UDP) e, in parte, anche da quello **applicativo** (es. comando `PORT` dell’FTP).

Il firewall diventa così capace di:

- distinguere **nuove connessioni** da quelle già aperte;
    
- mantenere **tabelle di stato** per ciascuna sessione;
    
- accettare automaticamente i pacchetti appartenenti a connessioni note, senza controllarli di nuovo.
    

---

### **3.2. Meccanismo operativo**

1. Quando arriva un pacchetto con **flag SYN**, il firewall verifica la **ACL**.
    
    - Se non autorizzato → `DENY`.
        
    - Se autorizzato → `ACCEPT` e **aggiunge un record nella connection table**.
        
2. I pacchetti successivi vengono confrontati con la **connection table**:
    
    - Se trovano una corrispondenza → passano direttamente.
        
    - Se non trovano corrispondenza → vengono scartati.
        

> Ciò migliora sia la sicurezza sia le prestazioni, poiché le connessioni già note non vengono rivalutate a ogni pacchetto.

---

### **3.3. Connection Table (esempio)**

Ogni entry contiene:

- Indirizzo IP sorgente e destinazione
    
- Porte sorgente e destinazione
    
- Protocollo
    
- Stato della connessione (es. `ESTABLISHED`)
    
- Timestamp dell’ultima attività
    

```
TCP 192.168.1.10:1045 → 159.149.70.11:80  ESTABLISHED
```

---

## **4. Stati di una connessione TCP**

Durante il three-way handshake, i due endpoint attraversano una sequenza di stati.

|Client|Server|Fase|Descrizione|
|---|---|---|---|
|SYN_SENT|LISTEN|1|Il client invia un SYN|
|SYN_RCVD|SYN_RCVD|2|Il server risponde con SYN+ACK|
|ESTABLISHED|ESTABLISHED|3|Il client conferma con ACK|

- Quando il server è in **LISTEN**, il firewall deve **verificare la ACL**.
    
- Quando la sessione è **ESTABLISHED**, il firewall può accettare pacchetti in base alla **connection table**.
    

---

## **5. Stateful Filtering per UDP**

### **5.1. Gestione del traffico connectionless**

UDP non è un protocollo orientato alla connessione: non possiede un vero “stato”.  
Per gestirlo, il firewall implementa uno **pseudo-stato**, basato sulla correlazione di:

- indirizzo IP sorgente e destinazione;
    
- porte sorgente e destinazione.
    

Se il firewall rileva pacchetti coerenti con una comunicazione già in corso, li considera parte della stessa sessione.

### **5.2. Timeout**

Poiché non esiste un meccanismo di chiusura nel protocollo UDP, il firewall imposta un **timeout** predefinito (es. 30-60 secondi) dopo il quale la sessione viene rimossa dalla tabella.

> In pratica, lo stato UDP è “virtuale”: si basa solo sulla ricorrenza temporale dei pacchetti.

---

## **6. Stateful Filtering e Applicazioni**

### **6.1. Supporto ai protocolli applicativi**

Alcuni firewall stateful avanzati includono un **modulo di analisi applicativa** che riconosce comandi specifici dei protocolli.  
Esempio: nel protocollo FTP, il firewall può riconoscere il comando `PORT` e aprire temporaneamente la porta dati corrispondente.

Questo consente:

- una gestione corretta dei protocolli multi-connessione;
    
- la simulazione di connessioni anche per protocolli “connectionless” (es. NFS, RPC).
    

---

### **6.2. Limiti e prestazioni**

- Le performance calano sensibilmente: l’ispezione dei livelli superiori è costosa.
    
- Spesso solo un **sottoinsieme di protocolli standard** è effettivamente supportato.
    
- Se il firewall non interpreta correttamente la semantica del protocollo, può essere **bypassato con tunnel applicativi** (es. HTTP tunneling).
    

> Per questo motivo, il filtraggio applicativo è spesso implementato come **plugin opzionale** nei firewall commerciali.

---

## **7. Deep Packet Inspection (DPI)**

### **7.1. Definizione**

La **Deep Packet Inspection** rappresenta l’evoluzione più recente dello stateful filtering, con funzionalità di analisi del contenuto applicativo.  
Il termine “DPI” non ha ancora una definizione univoca: spesso viene **abusato in ambito commerciale**, ma indica sempre la capacità di **esaminare i dati del payload**, non solo gli header.

### **7.2. Funzionamento**

- Analizza il contenuto delle sessioni applicative, cercando **pattern di stringhe** tipici di worm, malware o exploit.
    
- Si basa su firme (simili agli antivirus) e su analisi comportamentali.
    
- È implementata solo nei **firewall di fascia alta**, dove le prestazioni hardware consentono ispezioni in tempo reale.
    

---

## **8. Valutazioni generali**

|Aspetto|Static|Dynamic|Stateful|DPI|
|---|---|---|---|---|
|Apertura porte|Fissa|Temporanea|Gestita da tabelle|Gestita + analisi contenuto|
|Performance|Alta|Alta|Media|Bassa|
|Sicurezza|Bassa|Media|Alta|Molto alta|
|Supporto applicazioni|Nessuno|Parziale|Limitato|Completo (solo top-tier)|

---

### **8.1. Vantaggi del filtraggio stateful**

- Politiche di sicurezza **più semplici** (basta definire le regole di apertura).
    
- Protezione superiore rispetto allo static filtering.
    
- Base tecnologica di **tutti i firewall moderni**.
    

### **8.2. Svantaggi**

- **Impatto sulle prestazioni**: richiede hardware dedicato.
    
- **Scarso supporto applicativo** (senza DPI).
    
- Vulnerabile allo **spoofing IP**: il firewall non verifica se l’host “trusted” è davvero chi dichiara di essere.
    
- Mancanza di **autenticazione forte** integrata.
    

> Il punto debole dei packet filter, anche evoluti, è che “si fidano del mittente”. Solo i sistemi applicativi possono fornire autenticazione reale.

---

## **9. Firewall e minacce cifrate (Encrypted Threats)**

### **9.1. Il paradosso della cifratura**

La cifratura, nata per proteggere, può essere **sfruttata per attaccare**.  
I firewall tradizionali non possono analizzare il contenuto dei pacchetti **HTTPS o TLS**, e ciò permette ai malware di nascondersi nel traffico sicuro.

### **9.2. Contromisure moderne**

Per contrastare le minacce cifrate, i firewall di nuova generazione integrano moduli avanzati:

|Modulo|Funzione|
|---|---|
|**IPS** (_Intrusion Prevention System_)|Blocca exploit e intrusioni note|
|**GAV** (_Gateway AntiVirus_)|Analizza il traffico alla ricerca di virus|
|**AIC** (_Application Inspection & Control_)|Identifica applicazioni e comportamenti anomali|
|**DLP** (_Data Loss Prevention_)|Impedisce la fuoriuscita di dati sensibili|

> Il firewall moderno non è più solo un “guardiano delle porte”, ma un **centro di ispezione integrata** per tutto il traffico di rete, anche cifrato.

---

## **10. Conclusione**

Il **New Generation Packet Filtering** rappresenta l’unione di:

- **intelligenza di stato** (stateful filtering),
    
- **consapevolezza applicativa** (DPI),
    
- e **integrazione con moduli di sicurezza avanzati** (IPS, DLP, AIC).
    

È la risposta all’evoluzione del traffico moderno: cifrato, distribuito e dinamico.  
Tuttavia, resta essenziale bilanciare sicurezza e prestazioni, poiché ogni strato di analisi aggiunge protezione ma anche latenza.

> In sintesi: il firewall di nuova generazione non si limita a bloccare pacchetti — **comprende il contesto, riconosce le minacce e reagisce in tempo reale**.