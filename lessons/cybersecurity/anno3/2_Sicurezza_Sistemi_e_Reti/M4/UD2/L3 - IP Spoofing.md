## **Lezione 3 — IP Spoofing**

### **1. Che cos’è l’IP spoofing**

**IP spoofing** è il tentativo di inviare pacchetti con un **IP sorgente falsificato** in modo che sembrino provenire da un altro host.  
Il campo `SRC` dell’header IP è facilmente falsificabile: questo permette vari attacchi (impersonazione, MITM semplificati, amplificazione DoS, ecc.).

**Nota:** spoofare l’IP da solo _di solito_ non è sufficiente per inserirsi in una connessione TCP già stabilita — serve anche prevedere/indovinare numeri di sequenza TCP o usare altri stratagemmi.

In pratica, l’attaccante cerca di far apparire un pacchetto come proveniente da un client o da un host fidato. Questo è possibile perché, a livello IP, il campo sorgente non è autenticato: il router e il destinatario vedono il valore inserito nel pacchetto, ma non hanno garanzia crittografica che quel valore corrisponda davvero alla macchina che ha trasmesso il pacchetto.

Il problema è particolarmente evidente in reti locali o in contesti con meccanismi di assegnazione degli indirizzi poco protetti. La presenza di indirizzi IP duplicati o falsificati può produrre anche effetti di **Denial of Service**: pacchetti destinati alla vittima possono essere instradati male, generare conflitti o rendere instabile l’erogazione del servizio.

> 📌 L’IP spoofing consente di inviare pacchetti “a nome di altri”, ma non garantisce automaticamente di ricevere le risposte: le risposte saranno indirizzate al vero proprietario dell’IP falsificato.

---

### **2. Tipi di spoofing**

- **Blind Spoofing**  
    L’attaccante non può vedere le risposte inviate al vero host (ossia i reply vanno al nodo che si sta impersonando). Tipico quando l’attaccante è su una rete diversa dalla vittima.
    In questo caso l’attaccante può inviare pacchetti con IP sorgente falsificato, ma non vede i pacchetti di risposta e deve quindi indovinare o prevedere lo stato della connessione.
    
- **Non-Blind (Same-Subnet) Spoofing**  
    L’attaccante è sulla stessa sottorete e può sniffare il traffico; quindi può osservare ACK/SEQ e registrare i numeri di sequenza necessari per un takeover più semplice.
    È molto più pericoloso perché l’attaccante può osservare direttamente i pacchetti scambiati tra vittima e server, ricavando porte, numeri di sequenza e acknowledgement correnti.
    

---

### **3. Spoofing + TCP: perché serve prevedere i numeri di sequenza**

Per falsificare una connessione TCP (es. aprire una sessione rlogin che si basa sull’IP sorgente) l’attaccante deve:

1. Inviare SYN con IP sorgente falsificato.
    
2. Il server risponderà con SYN-ACK al vero host (che non è l’attaccante).
    
3. L’attaccante deve inviare l’**ACK finale** con il **numero di sequenza corretto** per completare l’handshake.

![](imgs/Pasted%20image%2020260708214610.png)

Quindi l’attacco richiede la **predizione del TCP ISN** (Initial Sequence Number) o la possibilità di ottenere SEQ values tramite sniffing.

Ogni connessione TCP ha infatti uno **stato** associato: coppia IP/porta del client, coppia IP/porta del server, numeri di sequenza, numeri di acknowledgement e finestra di ricezione. Per iniettare un pacchetto credibile non basta modificare l’IP sorgente: il pacchetto deve arrivare con porte corrette e con numeri `SEQ`/`ACK` accettabili per il destinatario.

Indovinare esattamente un numero di sequenza a 32 bit è difficile, ma TCP accetta pacchetti all’interno di una certa **finestra** per tollerare ritardi, riordinamenti e consegne fuori ordine. Se l’attaccante riesce a stimare il numero corretto o a cadere nella finestra valida, può aumentare la probabilità che il pacchetto spoofato venga considerato parte della connessione.

> ⚠️ La cifratura o l’autenticazione applicativa cambiano radicalmente lo scenario: anche se un pacchetto TCP venisse accettato, un protocollo applicativo autenticato impedirebbe di trasformarlo facilmente in comandi validi.

---

### **4. Generazione degli ISN e mitigazioni**

- RFC793 raccomandava un incremento ISN costante (un valore che cambia nel tempo).
    In particolare, l’ISN veniva incrementato periodicamente, storicamente con riferimento a intervalli dell’ordine dei microsecondi, con l’obiettivo originario di evitare confusione tra connessioni duplicate o riaperte troppo rapidamente. Questa scelta non era però pensata come meccanismo anti-spoofing.
    
- Per evitare predicibilità, RFC1948 → RFC6528 raccomandano calcolo ISN = contatore + funzione hash (salt) di (localhost, localport, remotehost, remoteport) → ISN difficilmente prevedibile da un attaccante remoto.
    L’idea moderna è legare l’ISN ai dettagli della connessione e a un segreto locale, aggiungendo comunque una componente temporale/contatore. Così due connessioni diverse non producono una sequenza facilmente interpolabile dall’esterno.
    

**Contromisure applicabili lato server / stack TCP:**

- usare ISN non prevedibili (RFC6528),
    
- impiegare **SYN cookies** per mitigare SYN flood e ridurre la necessità di tavole di stato,
    
- rafforzare randomizzazione dei numeri di sequenza,
    
- disabilitare servizi «trusted» che basano autorizzazioni sull’IP (rlogin, rsh, .rhosts),
    
- applicare **ingress filtering** / BCP38 (bloccare pacchetti con IP sorgente non appartenenti alla rete di ingresso).
    

---

### **5. Modalità operative di un attacco IP spoofing (esempio pratico)**

![](imgs/Pasted%20image%2020260708215004.png)

L’attacco diventa interessante quando il server accetta richieste sulla base della provenienza IP, ad esempio in vecchi servizi trusted come `rlogin`, `rsh` o configurazioni `.rhosts`. In questi casi il server non richiede una nuova autenticazione forte, ma assume che un certo host sorgente sia già fidato.

1. Scelta bersaglio e identificazione di relazioni di fiducia (host A si fida di host B).
    
2. (Opzionale) Knock-out dell’host fidato (es. via SYN flood) per impedirgli di rispondere.
    
3. Invio di SYN spoofati al server con SRC = host fidato.
    
4. Il server manda SYN-ACK al vero host fidato; l’attaccante invia l’ACK finale con SEQ previsto → connessione aperta.
    
5. Se la connessione sfrutta trust basato su IP, l’attaccante ora può inviare comandi (es. `echo "+ +" > .rhosts`) e guadagnare accesso.
    

**Problema pratico:** se il vero host riceve il SYN-ACK risponderà con RST o altro, spesso causando il fallimento dell’attacco — perciò gli attaccanti combinano spoofing con DoS sul host legittimo.

La sequenza tipica è quindi:

- l’attaccante sceglie un host fidato da impersonare;
    
- rende temporaneamente inattivo quell’host, ad esempio con flooding, in modo che non possa rispondere ai pacchetti destinati a lui;
    
- invia al server un `SYN` con IP sorgente falsificato;
    
- predice il numero di sequenza del `SYN-ACK` che il server invierà al vero host fidato;
    
- invia l’`ACK` finale e poi dati o comandi, usando numeri `SEQ`/`ACK` coerenti.
    
Se il vero host fidato non viene silenziato, può ricevere il `SYN-ACK` inatteso e rispondere con `RST`, facendo chiudere la connessione prima che l’attaccante riesca a sfruttarla.

> 📌 Il punto debole sfruttato non è solo TCP, ma la fiducia applicativa basata sull’indirizzo IP. Se l’applicazione richiede autenticazione forte, lo spoofing IP da solo non basta.

---

### **6. ISN prediction (come gli attaccanti analizzano)**

- l’attaccante invia connessioni non spoofate per osservare la sequenza di ISN generate dal server;
    
- misura incrementi nel tempo (es. ISN aumenta X per unità di tempo);
    
- usa questi campioni per stimare il prossimo ISN e inviare ACK con un valore probabile;
    
- se il server usa finestre TCP grandi, una serie di tentativi con SEQ plausibili può avere successo anche senza perfetta predizione.

Storicamente alcune implementazioni TCP generavano ISN in modo abbastanza prevedibile: osservando alcune connessioni reali e misurando il ritmo di crescita del contatore, l’attaccante poteva stimare il prossimo valore usato dal server. Le RFC più recenti hanno reso questo approccio molto meno praticabile.

Nel caso blind, la predizione è essenziale perché l’attaccante non vede il `SYN-ACK`. Nel caso non-blind, invece, l’attaccante può osservare direttamente il valore e costruire pacchetti spoofati molto più precisi.
    

---

### **7. Sniffer e ruolo nelle varianti «non-blind»**

- Gli sniffer (Wireshark, tcpdump) permettono di osservare SEQ/ACK e pattern di generazione ISN; con ARP poisoning si può posizionarsi come MITM per captare risposte in LAN.
    
- Gli sniffer possono essere usati legittimamente (debug/rete) o malevolmente (raccolta credenziali, analisi per attacco).

Un **packet sniffer** consente di osservare il contenuto dei pacchetti che attraversano una rete o un’interfaccia. L’uso legittimo è diagnostico: monitorare traffico, filtrare pacchetti, capire malfunzionamenti o analizzare protocolli. L’uso malevolo consiste invece nel raccogliere dati in chiaro, credenziali, informazioni sensibili o dettagli tecnici utili per costruire attacchi successivi.

Nel non-blind spoofing, lo sniffer riduce drasticamente l’incertezza: l’attaccante può leggere numeri di sequenza e acknowledgement correnti, osservare porte e direzione del traffico, e quindi generare pacchetti che sembrano molto più plausibili per il destinatario.
    

---

### **8. Contromisure e buone pratiche**

#### **a) Sul piano di rete / routing**

- **Ingress/Egress filtering** (BCP38) — bloccare pacchetti con sorgente non valida all’edge degli ISP/reti.
    
- Bloccare IP spoofing ai confini di rete.
    

#### **b) Sullo stack TCP**

- **ISN non prevedibili** (RFC6528); SYN cookies; limite alle risorse allocate per half-open connections.
    
- Rate-limiting e protezioni contro SYN flood (firewall stateful / IPS).
    

#### **c) A livello servizi/applicazioni**

- **Non usare trust basati su IP** (eliminare rlogin/.rhosts, usare SSH con autenticazione forte).
    
- Autenticazione forte (chiavi, password, MFA) per i servizi critici.
    
- Monitoraggio e logging delle connessioni anomale (connessioni da IP inattesi, tentativi ripetuti).
    

#### **d) Difese LAN**

- Evitare ARP poisoning (DHCP snooping, port security, ARP inspection) perché lo spoofing di livello 2 facilita sniffing e non-blind spoofing.
    

---

### **9. Rischi pratici e casi d’uso**

- Accesso non autorizzato a servizi che accettano trust per IP.
    
- Inserimento di payload malevoli in sessioni non protette.
    
- Uso combinato con DoS per aumentare probabilità di successo.
    
- Ricognizione (analisi del comportamento del server per prevedere ISN).

- Denial of Service o malfunzionamenti dovuti a indirizzi IP duplicati, conflitti di indirizzamento o risposte dirette verso host diversi dall’attaccante.
    

---

### **10. Sintesi rapida**

- **IP spoofing** = falsificare IP sorgente; per attacchi TCP servono anche **predizione dei SEQ** o capacità di sniffing.
    
- **Blind** vs **non-blind**: il secondo è molto più potente perché permette osservare il traffico.
    
- **Contromisure efficaci:** ISN non prevedibili (RFC6528), SYN cookies, BCP38, rimozione di servizi basati su trust IP, monitoraggio e difese di rete.

> ✅ Lo spoofing IP è concettualmente semplice perché il campo sorgente è falsificabile, ma gli attacchi TCP realmente efficaci richiedono di controllare o prevedere lo stato della connessione. La difesa robusta combina stack TCP moderni, filtraggio di rete e soprattutto eliminazione della fiducia basata solo sull’indirizzo IP.
