## **Lezione 2: New Generation Packet Filtering**

### **1. Limiti del filtraggio statico**

Il principale limite del **packet filtering statico** è che per garantire la comunicazione desiderata è necessario **lasciare aperta un’intera gamma di porte**.  
Questo approccio crea un **perimetro di rete esposto**, in cui molte porte rimangono aperte anche quando **non sono realmente necessarie**, favorendo così potenziali attacchi.

Inoltre:

- Lo static packet filter permette vari **attacchi diretti agli host interni**.
    
- Gli amministratori, spesso concentrati sulla sicurezza perimetrale, **trascurano la protezione interna**, lasciando vulnerabili i sistemi più sensibili.
    

Questo è particolarmente critico perché gli host interni sono spesso meno hardenizzati dei server esposti in DMZ: l’organizzazione concentra attenzione e patching sui servizi pubblici, mentre workstation e host interni possono rimanere più vulnerabili. Se un firewall statico consente connessioni verso questi host, l’attaccante può trovare superfici meno protette.

> 📌 In sintesi: lo static filtering è semplice ma rigido; se per far funzionare un protocollo bisogna lasciare aperte molte porte, la rigidità diventa esposizione.

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
    

Il dinamismo consiste quindi nel fatto che una porta non resta sempre disponibile: viene aperta quando il traffico osservato indica che serve, e viene richiusa quando il flusso termina o non è più attivo.

> 📌 Il dynamic packet filter riduce la finestra temporale d’attacco: una porta aperta solo per pochi istanti è molto meno sfruttabile di una porta permanentemente esposta.

---

### **2.2. Vantaggi**

- Le **porte restano aperte solo per brevi periodi**.
    
- Supporta la maggior parte dei servizi di rete.
    
- Riduce la possibilità di successo di molti attacchi comuni (es. TCP hijacking o port scanning).
    

> 📌 La finestra temporale d’attacco si riduce, rendendo molto più difficile replicare un exploit.

---

### **2.3. Svantaggi**

- I DPF sono **application-unaware**, ovvero non comprendono il significato dei protocolli applicativi.
    
- Consentono connessioni IP dirette verso host interni, senza distinguere il contenuto o la logica del traffico.
    
- Non offrono **autenticazione integrata**: una volta stabilita la connessione, tutto dipende dal sistema target.
    

In particolare, se una connessione malevola viene accettata, la sicurezza dipende dalle vulnerabilità dell’host bersaglio. Inoltre, una volta compromesso un host interno, il traffico malevolo può spostarsi lateralmente nella rete interna, dove il firewall perimetrale spesso non osserva più i flussi.

> ⚠️ I DPF migliorano la sicurezza rispetto agli statici, ma rimangono ciechi rispetto ad autenticazione dell’utente, autorizzazione del servizio e semantica applicativa completa. Per questi aspetti servono tecnologie più vicine agli application gateway.

---

## **3. Stateful Packet Filter**

### **3.1. Concetto**

Lo **stateful packet filtering** rappresenta un’evoluzione del modello dinamico: è un **firewall “state-aware”**.  
Tiene traccia delle **informazioni di stato** provenienti dal **livello di trasporto** (TCP/UDP) e, in parte, anche da quello **applicativo** (es. comando `PORT` dell’FTP).

Il firewall diventa così capace di:

- distinguere **nuove connessioni** da quelle già aperte;
    
- mantenere **tabelle di stato** per ciascuna sessione;
    
- accettare automaticamente i pacchetti appartenenti a connessioni note, senza controllarli di nuovo.
    

Nella pratica, il termine **stateful packet filter** è spesso usato per indicare firewall dinamici che conservano memoria delle connessioni. L’idea è distinguere il pacchetto che avvia una sessione, da verificare accuratamente con le ACL, dai pacchetti successivi che possono essere riconosciuti tramite lo stato già registrato.

---

### **3.2. Meccanismo operativo**

1. Quando arriva un pacchetto con **flag SYN**, il firewall verifica la **ACL**.
    
    - Se non autorizzato → `DENY`.
        
    - Se autorizzato → `ACCEPT` e **aggiunge un record nella connection table**.
        
2. I pacchetti successivi vengono confrontati con la **connection table**:
    
    - Se trovano una corrispondenza → passano direttamente.
        
    - Se non trovano corrispondenza → vengono scartati.
        

Questa logica consente di scrivere policy più semplici: di norma si definiscono le regole relative all’apertura della connessione, mentre le risposte vengono accettate perché associate a una connessione già presente nella tabella.

> 📌 Ciò migliora sia la sicurezza sia le prestazioni: l’analisi più costosa avviene soprattutto all’inizio della connessione, poi i pacchetti successivi sono verificati tramite la connection table.

![](imgs/Pasted%20image%2020260709145811.png)

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

![](imgs/Pasted%20image%2020260709145849.png)

La connection table deve anche gestire la **scadenza** delle entry. Se una connessione TCP termina correttamente, l’entry può essere rimossa in modo esplicito. Se invece client o server smettono di comunicare per errore, crash o perdita di pacchetti, il firewall usa un **timeout** per evitare che una sessione vecchia rimanga valida indefinitamente.

> ⚠️ Un timeout troppo lungo può lasciare aperte finestre sfruttabili; un timeout troppo breve può far scadere una connessione ancora attiva, costringendo il firewall a rivalutarla.

---

## **4. Stati di una connessione TCP**

Durante il three-way handshake, i due endpoint attraversano una sequenza di stati.

![](imgs/Pasted%20image%2020260709145829.png)

| Client      | Server      | Fase | Descrizione                    |
| ----------- | ----------- | ---- | ------------------------------ |
| SYN_SENT    | LISTEN      | 1    | Il client invia un SYN         |
| SYN_RCVD    | SYN_RCVD    | 2    | Il server risponde con SYN+ACK |
| ESTABLISHED | ESTABLISHED | 3    | Il client conferma con ACK     |

- Quando il server è in **LISTEN**, il firewall deve **verificare la ACL**.
    
- Quando la sessione è **ESTABLISHED**, il firewall può accettare pacchetti in base alla **connection table**.
    

In uno scenario tipico:

1. arriva un SYN dal client: la connessione non è ancora nota, quindi si controlla la policy;
    
2. se la policy consente il traffico, il firewall inserisce in tabella la coppia client/porta sorgente e server/porta destinazione;
    
3. il SYN+ACK del server e l’ACK finale del client vengono riconosciuti come appartenenti a quella connessione;
    
4. i pacchetti successivi sono accettati perché coerenti con lo stato memorizzato.

![](imgs/Pasted%20image%2020260709145934.png)

---

## **5. Stateful Filtering per UDP**

### **5.1. Gestione del traffico connectionless**

UDP non è un protocollo orientato alla connessione: non possiede un vero “stato”.  
Per gestirlo, il firewall implementa uno **pseudo-stato**, basato sulla correlazione di:

- indirizzo IP sorgente e destinazione;
    
- porte sorgente e destinazione.
    

Se il firewall rileva pacchetti coerenti con una comunicazione già in corso, li considera parte della stessa sessione.

È importante distinguere tra stato applicativo e stato di trasporto: un’applicazione sopra UDP può avere una propria logica di sessione, ma UDP non fornisce handshake, chiusura o stati analoghi a TCP. Il firewall può quindi registrare solo tuple di indirizzi/porte e osservare se arrivano pacchetti coerenti con traffico transitato poco prima.

![](imgs/Pasted%20image%2020260709150011.png)

### **5.2. Timeout**

Poiché non esiste un meccanismo di chiusura nel protocollo UDP, il firewall imposta un **timeout** predefinito (es. 30-60 secondi) dopo il quale la sessione viene rimossa dalla tabella.

> 📌 In pratica, lo stato UDP è “virtuale”: non deriva dal protocollo UDP, ma da una correlazione temporale tra pacchetti con stessi endpoint e porte compatibili.

---

## **6. Stateful Filtering e Applicazioni**

### **6.1. Supporto ai protocolli applicativi**

Alcuni firewall stateful avanzati includono un **modulo di analisi applicativa** che riconosce comandi specifici dei protocolli.  
Esempio: nel protocollo FTP, il firewall può riconoscere il comando `PORT` e aprire temporaneamente la porta dati corrispondente.

Questo consente:

- una gestione corretta dei protocolli multi-connessione;
    
- la simulazione di connessioni anche per protocolli “connectionless” (es. NFS, RPC).
    

Nel caso di **FTP attivo**, il firewall deve leggere il canale di controllo e riconoscere un comando come `PORT 1038`: solo così può capire che il server FTP tenterà una connessione dati dalla porta `20/tcp` verso la porta `1038` del client. Questa informazione non è nell’header IP/TCP, ma nel payload applicativo.

![](imgs/Pasted%20image%2020260709150030.png)

Il firewall può quindi inserire una entry temporanea nella connection table marcandola come traffico FTP e aprendo selettivamente solo la porta dati negoziata. Senza questa capacità, dovrebbe lasciare aperte molte porte alte o bloccare FTP attivo.

> 📌 Qui lo stateful filtering diventa application-aware: non si limita a ricordare connessioni, ma interpreta un frammento del protocollo applicativo per modificare dinamicamente le regole.

---

### **6.2. Limiti e prestazioni**

- Le performance calano sensibilmente: l’ispezione dei livelli superiori è costosa.
    
- Spesso solo un **sottoinsieme di protocolli standard** è effettivamente supportato.
    
- Se il firewall non interpreta correttamente la semantica del protocollo, può essere **bypassato con tunnel applicativi** (es. HTTP tunneling).
    

Per ragioni di performance, molti firewall implementano l’analisi applicativa tramite **plugin** per protocolli specifici, ad esempio FTP. Un plugin può riconoscere solo alcuni comandi e tradurli in azioni firewall, come aprire o chiudere porte temporanee.

Questa soluzione è efficace per protocolli standardizzati e molto diffusi, ma diventa fragile con:

- protocolli custom;
    
- varianti non standard di protocolli noti;
    
- tunneling applicativo;
    
- payload cifrato.
    

> ⚠️ Se il parser applicativo è semplificato o interpreta male la semantica del protocollo, un attaccante può costruire traffico che il firewall classifica in modo errato.

---

## **7. Deep Packet Inspection (DPI)**

### **7.1. Definizione**

La **Deep Packet Inspection** rappresenta l’evoluzione più recente dello stateful filtering, con funzionalità di analisi del contenuto applicativo.  
Il termine “DPI” non ha ancora una definizione univoca: spesso viene **abusato in ambito commerciale**, ma indica sempre la capacità di **esaminare i dati del payload**, non solo gli header.

Il vero DPI non si limita a leggere un singolo campo applicativo: può correlare più pacchetti, ricostruire stream applicativi e cercare pattern distribuiti su più segmenti. È quindi molto più vicino a un motore di analisi del contenuto che a una semplice estensione delle ACL.

### **7.2. Funzionamento**

- Analizza il contenuto delle sessioni applicative, cercando **pattern di stringhe** tipici di worm, malware o exploit.
    
- Si basa su firme (simili agli antivirus) e su analisi comportamentali.
    
- È implementata solo nei **firewall di fascia alta**, dove le prestazioni hardware consentono ispezioni in tempo reale.
    

Esempi di uso sono l’ispezione del payload delle email, degli allegati, degli stream applicativi o di sequenze che sembrano parti di worm, malware o tentativi di exploit. Proprio per questo il termine viene spesso usato in modo commerciale: non tutti i prodotti che dichiarano DPI eseguono davvero un’analisi profonda e correlata del traffico.

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
    

Un limite ulteriore è che il packet filter ragiona ancora prevalentemente su **indirizzi IP**. Se un host trusted viene compromesso, oppure se un attaccante riesce a presentarsi con un indirizzo considerato affidabile, il firewall può consentire traffico che in realtà non proviene da un utente o servizio autorizzato.

Il punto debole dei packet filter, anche evoluti, è quindi che “si fidano del mittente” a livello di rete. Non verificano davvero che dietro quell’IP ci sia un utente autenticato e autorizzato a usare un certo servizio: questo sarà uno dei motivi per introdurre gli **application gateway**.

---

## **9. Firewall e minacce cifrate (Encrypted Threats)**

### **9.1. Il paradosso della cifratura**

La cifratura, nata per proteggere, può essere **sfruttata per attaccare**.  
I firewall tradizionali non possono analizzare il contenuto dei pacchetti **HTTPS o TLS**, e ciò permette ai malware di nascondersi nel traffico sicuro.

Anche quando un firewall dispone di DPI, davanti a un payload cifrato deve prima poterlo decifrare. Questo introduce due problemi:

- **tecnico-prestazionale**, perché decifrare, analizzare e ricifrare il traffico è molto costoso;
    
- **legale e di privacy**, perché il firewall si trova a leggere comunicazioni che l’utente si aspetta protette end-to-end.
    

Gli attaccanti sfruttano lo stesso meccanismo: malware e canali di comando e controllo possono usare TLS per rendere opaco il traffico malevolo.

![](imgs/Pasted%20image%2020260709150212.png)

### **9.2. Contromisure moderne**

Per contrastare le minacce cifrate, i firewall di nuova generazione integrano moduli avanzati:

|Modulo|Funzione|
|---|---|
|**IPS** (_Intrusion Prevention System_)|Blocca exploit e intrusioni note|
|**GAV** (_Gateway AntiVirus_)|Analizza il traffico alla ricerca di virus|
|**AIC** (_Application Inspection & Control_)|Identifica applicazioni e comportamenti anomali|
|**DLP** (_Data Loss Prevention_)|Impedisce la fuoriuscita di dati sensibili|

La **DLP** è particolarmente importante rispetto ai data breach: invece di limitarsi a bloccare traffico in ingresso, analizza anche ciò che esce dalla rete e può individuare dati sensibili non autorizzati in transito.

Nei prodotti di fascia alta, l’ispezione del traffico cifrato può avvenire con una tecnica di interposizione controllata: il firewall si pone tra client e server, stabilisce due sessioni cifrate distinte e decritta il traffico nel mezzo per analizzarlo, per poi ricifrarlo e inoltrarlo.

> ⚠️ Questa tecnica assomiglia a un Man-in-the-Middle controllato: può essere legittima in reti aziendali gestite, ma dipende da policy, configurazione dei certificati, consenso/legislazione e impatto prestazionale.

> 📌 Il firewall moderno non è più solo un “guardiano delle porte”, ma un **centro di ispezione integrata** per traffico, applicazioni e dati.

![](imgs/Pasted%20image%2020260709150236.png)

---

## **10. Conclusione**

Il **New Generation Packet Filtering** rappresenta l’unione di:

- **intelligenza di stato** (stateful filtering),
    
- **consapevolezza applicativa** (DPI),
    
- e **integrazione con moduli di sicurezza avanzati** (IPS, DLP, AIC).
    

È la risposta all’evoluzione del traffico moderno: cifrato, distribuito e dinamico.  
Tuttavia, resta essenziale bilanciare sicurezza e prestazioni, poiché ogni strato di analisi aggiunge protezione ma anche latenza.

> ✅ In sintesi: il firewall di nuova generazione non si limita a bloccare pacchetti — **comprende il contesto, riconosce le minacce e reagisce in tempo reale**.
