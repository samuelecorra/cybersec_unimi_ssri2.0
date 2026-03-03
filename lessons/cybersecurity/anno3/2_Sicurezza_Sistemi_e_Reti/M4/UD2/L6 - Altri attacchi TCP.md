## **Lezione 6: Altri attacchi TCP**

### **1. Introduzione — che cosa vedremo e perché è importante**

In questa lezione esploriamo altre varianti di attacco a livello trasporto e rete che non si limitano al semplice SYN-flooding: vedremo il **TCP con flood**, attacchi costruiti con **junk-packet**, exploit su protocolli senza stato come **UDP** (con gli esempi classici di NTP amplification), il ruolo di **ICMP** (ping / smurf) e come tutte queste tecniche siano usate per generare DDoS tramite **reflection** e **amplificazione**. Capire questi meccanismi è cruciale perché mostrano un principio semplice ma potente: l'Internet è pieno di servizi che possono essere **abusati come amplificatori** o come **reflector** per scaricare grandi volumi di traffico su una vittima.

---

### **2. TCP Con Flood (TCP connection flood)**

#### **2.1. Idea di base**

Un attacco TCP Con Flood ordina a una botnet di completare legittimamente la handshake TCP verso il server (non usa più SYN spoofati per nascondersi). Ogni bot apre la connessione e invia una breve richiesta HTTP (ad esempio `HEAD`) quindi chiude o reinizia, ripetendo rapidamente l’operazione. Poiché ogni bot completa la handshake, il proxy che mitiga i SYN cookies non può filtrare facilmente questo traffico: il proxy vede connessioni apparentemente legittime.

#### **2.2. Perché è pericoloso**

Questo attacco aggira le protezioni basate su SYN (SYN cookies, SYN cache) e può saturare risorse applicative (CPU, socket, banda uplink) o la capacità del server di servire richieste reali. Inoltre, perché la connessione è completata, il reflector/proxy può identificare i bot (visto che rispondono realmente), permettendo eventuali contromisure locali ma nel frattempo il carico può essere già dannoso.

#### **2.3. Contromisure pratiche**

- Rate limiting a livello applicazione (limita richieste per IP/prefix).
    
- WAF/Reverse proxy con challenge (CAPTCHA, TLS mutual?) per distinguere bot dalle connessioni reali.
    
- Analisi comportamentale a livello di sessione (frequenza di HEAD o pattern identici).  
    Queste misure riducono l’efficacia della botnet senza dipendere unicamente dallo stack TCP.
    

---

### **3. Attacchi basati su “Junk-Packet”**

#### **3.1. Definizione e categoria**

Per _junk-packet_ si intendono pacchetti apparentemente innocui ma creati per provocare risposte diverse a seconda delle porte o del servizio destinazione, con l’obiettivo di massimizzare il traffico di ritorno (ovvero cause di “reflection”) o di costringere il target a consumare risorse rispondendo a numerosi pacchetti. La tabella riassuntiva nello studio ATLAS mostra quali pacchetti causano quale risposta (es. SYN su porta aperta → SYN/ACK; UDP su porta chiusa → ICMP port unreachable).

#### **3.2. Implicazioni**

Conoscendo la risposta tipica (TCP RST, ICMP unreachable, echo reply, ecc.) un attaccante può costruire flussi che generano il massimo traffico di ritorno verso l’IP spoofato (la vittima), massimizzando l’effetto DDoS con minimo input. Questo è il principio della maggior parte delle reflection + amplification attacks.

---

### **4. UDP: proprietà e rischio**

#### **4.1. Caratteristiche che lo rendono pericoloso**

UDP è senza connessione, senza stato e con header leggero: _pochi byte_ in ingresso possono provocare risposte molto più grandi. Non esiste handshake quindi l’attaccante può facilmente usare **IP spoofing** per far sì che la risposta vada a una vittima. Applicazioni tipiche che usano UDP (DNS, NTP, memcached, STUN, etc.) sono spesso vettori di amplificazione.

#### **4.2. Denial-of-service tramite UDP flood**

Un semplice UDP flood invia massicci datagram a una porta; la vittima, o il network a monte, deve processare e smistare questi pacchetti, saturando banda o risorse. Spesso i dispositivi di rete o i firewall hanno meno capacità di mitigare pakcets UDP rispetto a connessioni TCP consolidate.

---

### **5. NTP Amplification (esempio dettagliato di amplificazione UDP)**

#### **5.1. Meccanismo**

NTP è un protocollo UDP per sincronizzare orologi. Le versioni più vecchie di molti server NTP supportavano il comando `monlist` che elenca gli ultimi host con cui il server ha comunicato (fino a 600 voci). Un attaccante invia una richiesta `monlist` a uno o più server NTP ma con sorgente spoofata (l’IP della vittima). I server rispondono inviando la lunga lista alla vittima, generando un flusso molto più grande del packet originale.

#### **5.2. Fattore di amplificazione e impatto storico**

NTP può raggiungere fattori di amplificazione elevati: ad esempio un pacchetto di ~234 byte che genera una risposta aggregata di migliaia di byte (diverse decine di KB), con fattori di 10x–100x a seconda della query e della implementazione. Tra dicembre 2013 e febbraio 2014 si sono osservati DDoS massivi (fino a 400 Gbps) sfruttando migliaia di server NTP mal configurati. Questo dimostra come un singolo host con 1 Gbps di uplink possa dirigere decine di Gbps verso la vittima tramite reflector mal configurati.

#### **5.3. Contromisure**

- Disabilitare comandi pericolosi (`monlist`) o aggiornare NTP a versioni sicure.
    
- Filtraggio anti-spoofing (BCP38) sui router del provider per bloccare IP sorgente falsificati.
    
- Limitare le risposte dei server (rate limit, risposta solo a address not local).  
    Queste misure riducono drasticamente la superficie di amplificazione.
    

---

### **6. ICMP: funzioni e abusi (Smurf, Fraggle)**

#### **6.1. ICMP in breve**

ICMP è il protocollo che fornisce messaggi di controllo e segnalazione errori a livello rete (echo request/reply per `ping`, destination unreachable, TTL expired, ecc.). È trasportato su IP e include tipo/codice più alcuni byte del datagramma originale.

#### **6.2. Smurf attack**

Lo Smurf è un attacco che invia un ICMP Echo Request indirizzato a un broadcast di una rete (es. `x.y.z.255`) con IP sorgente spoofato impostato sull’IP della vittima. Tutti gli host che ricevono il broadcast inviano a loro volta un Echo Reply alla vittima, moltiplicando il traffico di ritorno. La variante **Fraggle** è analoga ma usa UDP (es. servizi tipo chargen o echo).

#### **6.3. Prevenzione**

- Non inoltrare pacchetti IP con indirizzo di destinazione broadcast dall’esterno.
    
- Disabilitare la risposta a broadcast su host/router quando non necessario.
    
- Applicare filtri anti-spoofing a livello ISP (BCP38).  
    Queste pratiche sono efficaci per annullare Smurf e Fraggle.
    

---

### **7. Reflection vs Amplification — chiarimento concettuale**

- **Reflection**: l’attaccante invia una richiesta a un server (reflector) con IP sorgente falsificato (della vittima); il server risponde alla vittima. Qui la vittima viene “riflessa” con il traffico di risposta.
    
- **Amplification**: il reflector risponde con dati **molto più grandi** rispetto alla richiesta inviata dall’attaccante (es. DNS, NTP, memcached), quindi la vittima subisce un flusso di dati amplificato rispetto a quanto generato dall’attaccante.
    

Capire la differenza aiuta a progettare mitigazioni: per reflection serve anti-spoofing; per amplification serve sia anti-spoofing sia configurazioni dei servizi che limitino la dimensione della risposta.

---

### **8. Strategie di difesa multilivello (best practice)**

#### **8.1. Lato ISP / rete**

- Implementare **ingress filtering** (BCP38) per impedire IP spoofing.
    
- Deploy di sistemi di rilevazione/mitigazione DDoS (scrubbing centers) e rate limiting a livello backbone.
    

#### **8.2. Lato server / applicazione**

- Hardening dei servizi UDP/TCP: disabilitare comandi non necessari (es. `monlist`), aggiornare software, applicare rate-limit e SYN cookies quando utile.
    
- Usare reverse proxy / CDN / WAF che terminano e verificano le connessioni prima che raggiungano l’origine.
    

#### **8.3. Lato host**

- Configurare firewall per rifiutare pacchetti diretti a broadcast da fonti esterne, limitare risposte ICMP non necessarie, e applicare limiti di connessioni per IP.
    
- Monitoraggio continuo (NetFlow / sflow) per individuare anomalie in tempo reale.
    

---

### **9. Esempio pratico (schema mentale Feynman)**

Spiego come funziona un NTP amplification con parole semplici ma precise: un attaccante scrive una lettera molto piccola (request `monlist`) e la imbuca in migliaia di cassette postali (i server NTP). Ogni cassette postale risponde non all’autore ma a un indirizzo sbagliato (la vittima), e invia una busta molto grande (la lista di host). La vittima riceve centinaia o migliaia di grandi buste e non riesce più a leggere la posta ufficiale. Per fermare l’attacco dobbiamo o impedire che le cassette postali rispondano a indirizzi sbagliati (anti-spoofing) o chiudere la funzione che genera le grandi buste (disabilitare `monlist`).

---

### **10. Riassunto tecnico (quick cheat-sheet)**

|Attacco|Vettore|Caratteristica chiave|Contromisure|
|---|--:|---|---|
|TCP Con Flood|TCP completo + richieste HTTP|Aggira protezioni SYN perché handshake completata|Rate limit app, WAF, reverse proxy|
|Junk-Packet floods|TCP/UDP/ICMP vari|Generano risposte di tipo diverso (RST / ICMP / echo)|Filtri, limitazione rate|
|NTP Amplification|UDP (NTP `monlist`)|Alto fattore di amplificazione (×10–×100)|Aggiornare NTP, disabilitare comandi, BCP38|
|Smurf / Fraggle|ICMP/UDP broadcast|Moltiplica le risposte via broadcast|Bloccare broadcast dall’esterno, anti-spoofing|


---