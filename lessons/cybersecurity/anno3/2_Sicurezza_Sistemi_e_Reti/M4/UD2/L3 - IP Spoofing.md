## **Lezione 3 — IP Spoofing

### **1. Che cos’è l’IP spoofing

**IP spoofing** è il tentativo di inviare pacchetti con un **IP sorgente falsificato** in modo che sembrino provenire da un altro host.  
Il campo `SRC` dell’header IP è facilmente falsificabile: questo permette vari attacchi (impersonazione, MITM semplificati, amplificazione DoS, ecc.).

**Nota:** spoofare l’IP da solo _di solito_ non è sufficiente per inserirsi in una connessione TCP già stabilita — serve anche prevedere/indovinare numeri di sequenza TCP o usare altri stratagemmi.

---

### **2. Tipi di spoofing

- **Blind Spoofing**  
    L’attaccante non può vedere le risposte inviate al vero host (ossia i reply vanno al nodo che si sta impersonando). Tipico quando l’attaccante è su una rete diversa dalla vittima.
    
- **Non-Blind (Same-Subnet) Spoofing**  
    L’attaccante è sulla stessa sottorete e può sniffare il traffico; quindi può osservare ACK/SEQ e registrare i numeri di sequenza necessari per un takeover più semplice.
    

---

### **3. Spoofing + TCP: perché serve prevedere i numeri di sequenza

Per falsificare una connessione TCP (es. aprire una sessione rlogin che si basa sull’IP sorgente) l’attaccante deve:

1. Inviare SYN con IP sorgente falsificato.
    
2. Il server risponderà con SYN-ACK al vero host (che non è l’attaccante).
    
3. L’attaccante deve inviare l’**ACK finale** con il **numero di sequenza corretto** per completare l’handshake.
    

Quindi l’attacco richiede la **predizione del TCP ISN** (Initial Sequence Number) o la possibilità di ottenere SEQ values tramite sniffing.

---

### **4. Generazione degli ISN e mitigazioni

- RFC793 raccomandava un incremento ISN costante (un valore che cambia nel tempo).
    
- Per evitare predicibilità, RFC1948 → RFC6528 raccomandano calcolo ISN = contatore + funzione hash (salt) di (localhost, localport, remotehost, remoteport) → ISN difficilmente prevedibile da un attaccante remoto.
    

**Contromisure applicabili lato server / stack TCP:**

- usare ISN non prevedibili (RFC6528),
    
- impiegare **SYN cookies** per mitigare SYN flood e ridurre la necessità di tavole di stato,
    
- rafforzare randomizzazione dei numeri di sequenza,
    
- disabilitare servizi «trusted» che basano autorizzazioni sull’IP (rlogin, rsh, .rhosts),
    
- applicare **ingress filtering** / BCP38 (bloccare pacchetti con IP sorgente non appartenenti alla rete di ingresso).
    

---

### **5. Modalità operative di un attacco IP spoofing (esempio pratico)

1. Scelta bersaglio e identificazione di relazioni di fiducia (host A si fida di host B).
    
2. (Opzionale) Knock-out dell’host fidato (es. via SYN flood) per impedirgli di rispondere.
    
3. Invio di SYN spoofati al server con SRC = host fidato.
    
4. Il server manda SYN-ACK al vero host fidato; l’attaccante invia l’ACK finale con SEQ previsto → connessione aperta.
    
5. Se la connessione sfrutta trust basato su IP, l’attaccante ora può inviare comandi (es. `echo "+ +" > .rhosts`) e guadagnare accesso.
    

**Problema pratico:** se il vero host riceve il SYN-ACK risponderà con RST o altro, spesso causando il fallimento dell’attacco — perciò gli attaccanti combinano spoofing con DoS sul host legittimo.

---

### **6. ISN prediction (come gli attaccanti analizzano)

- l’attaccante invia connessioni non spoofate per osservare la sequenza di ISN generate dal server;
    
- misura incrementi nel tempo (es. ISN aumenta X per unità di tempo);
    
- usa questi campioni per stimare il prossimo ISN e inviare ACK con un valore probabile;
    
- se il server usa finestre TCP grandi, una serie di tentativi con SEQ plausibili può avere successo anche senza perfetta predizione.
    

---

### **7. Sniffer e ruolo nelle varianti «non-blind»

- Gli sniffer (Wireshark, tcpdump) permettono di osservare SEQ/ACK e pattern di generazione ISN; con ARP poisoning si può posizionarsi come MITM per captare risposte in LAN.
    
- Gli sniffer possono essere usati legittimamente (debug/rete) o malevolmente (raccolta credenziali, analisi per attacco).
    

---

### **8. Contromisure e buone pratiche

#### **a) Sul piano di rete / routing

- **Ingress/Egress filtering** (BCP38) — bloccare pacchetti con sorgente non valida all’edge degli ISP/reti.
    
- Bloccare IP spoofing ai confini di rete.
    

#### **b) Sullo stack TCP

- **ISN non prevedibili** (RFC6528); SYN cookies; limite alle risorse allocate per half-open connections.
    
- Rate-limiting e protezioni contro SYN flood (firewall stateful / IPS).
    

#### **c) A livello servizi/applicazioni

- **Non usare trust basati su IP** (eliminare rlogin/.rhosts, usare SSH con autenticazione forte).
    
- Autenticazione forte (chiavi, password, MFA) per i servizi critici.
    
- Monitoraggio e logging delle connessioni anomale (connessioni da IP inattesi, tentativi ripetuti).
    

#### **d) Difese LAN

- Evitare ARP poisoning (DHCP snooping, port security, ARP inspection) perché lo spoofing di livello 2 facilita sniffing e non-blind spoofing.
    

---

### **9. Rischi pratici e casi d’uso

- Accesso non autorizzato a servizi che accettano trust per IP.
    
- Inserimento di payload malevoli in sessioni non protette.
    
- Uso combinato con DoS per aumentare probabilità di successo.
    
- Ricognizione (analisi del comportamento del server per prevedere ISN).
    

---

### **10. Sintesi rapida

- **IP spoofing** = falsificare IP sorgente; per attacchi TCP servono anche **predizione dei SEQ** o capacità di sniffing.
    
- **Blind** vs **non-blind**: il secondo è molto più potente perché permette osservare il traffico.
    
- **Contromisure efficaci:** ISN non prevedibili (RFC6528), SYN cookies, BCP38, rimozione di servizi basati su trust IP, monitoraggio e difese di rete.