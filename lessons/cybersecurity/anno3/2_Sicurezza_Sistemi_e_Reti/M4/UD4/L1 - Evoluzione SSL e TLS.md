# **UD4: Sicurezza del livello trasporto**

**Obiettivo:** presentare in modo essenziale le funzioni di TLS (confidenzialità, integrità, autenticazione) e le principali aree vulnerabili: handshake/protocollo, PKI/certificati e implementazioni.

**In sintesi:** studieremo il modello handshake → record layer → gestione certificati, e le contromisure principali come uso di TLS 1.3, cipher moderni con forward secrecy, corretta gestione delle CA e aggiornamenti/patching delle implementazioni.

---

## **Lezione 1: Evoluzione SSL/TLS**

### **1. Introduzione**

I protocolli **SSL** (_Secure Sockets Layer_) e **TLS** (_Transport Layer Security_) appartengono alla stessa famiglia di protocolli per la sicurezza delle comunicazioni di rete. SSL è il nome storico della famiglia; TLS ne rappresenta l'evoluzione standardizzata.

SSL/TLS costituiscono uno **standard di fatto per la sicurezza di Internet**: l'utente li usa spesso in modo trasparente, per esempio quando visita un sito tramite **HTTPS**. Il loro obiettivo essenziale è fornire un canale sicuro tra due applicazioni che comunicano attraverso una rete potenzialmente insicura.

In particolare, SSL/TLS mirano a garantire:

- **confidenzialità**, impedendo a un attaccante che sniffa il traffico di leggere i dati;

- **integrità**, impedendo o rilevando modifiche ai dati in transito;

- **autenticazione**, soprattutto del server verso il client, e in alcuni casi anche del client verso il server.

> 📌 TLS non rende "sicura Internet" in senso assoluto: protegge il canale di comunicazione tra due endpoint applicativi. Non elimina, per esempio, vulnerabilità applicative, malware sul client o problemi di disponibilità.

Oltre alla navigazione web, SSL/TLS viene usato in molti altri contesti:

- connessioni HTTPS;

- sistemi VoIP;

- transazioni e pagamenti elettronici;

- servizi applicativi che hanno bisogno di trasporto cifrato e autenticato.

---

### **2. Sicurezza ai diversi livelli dello stack**

La sicurezza può essere introdotta a diversi livelli dello stack di rete. La scelta del livello influenza quali dati vengono protetti, quanto la protezione è trasparente per le applicazioni e quali configurazioni sono necessarie.

![](imgs/Pasted%20image%2020260709011313.png)

|Livello|Esempi|Idea di fondo|
|---|---|---|
|Applicazione|PGP, S/MIME, Kerberos, protocolli applicativi sicuri|La singola applicazione gestisce direttamente la sicurezza|
|Trasporto|SSL/TLS|Si protegge il canale tra due processi applicativi|
|Rete|IPsec|Si proteggono pacchetti IP o comunicazioni tra host/reti|

Mettersi al **livello rete** significa proteggere il traffico IP con meccanismi come IPsec. Mettersi al **livello applicativo** significa che ciascuna applicazione usa meccanismi specifici, per esempio per proteggere email, autenticazioni o messaggi applicativi. SSL/TLS si colloca invece al **livello di trasporto**, sopra TCP e sotto i protocolli applicativi come HTTP.

Questa posizione rende TLS molto utile: l'applicazione può continuare a usare il proprio protocollo, mentre TLS aggiunge un canale cifrato e autenticato tra client e server.

> 💡 HTTPS non è un protocollo completamente nuovo: è HTTP trasportato sopra TLS. Il livello applicativo resta HTTP, ma il canale sottostante è protetto.

---

### **3. Minaccia considerata**

Il modello intuitivo è quello di due applicazioni che comunicano su una rete osservabile da un attaccante. Senza protezione, l'attaccante può:

- intercettare il traffico;

- leggere dati sensibili;

- modificare messaggi in transito;

- inserirsi nella negoziazione iniziale per forzare parametri deboli.

SSL/TLS nasce per impedire che il traffico applicativo sia banalmente leggibile o modificabile lungo il percorso. Per questo la protezione deve coprire non solo i dati applicativi, ma anche la fase in cui client e server decidono **quali algoritmi** usare e **quali chiavi** stabilire.

---

### **4. Origini storiche**

SSL nacque in ambito **Netscape** negli anni Novanta, quando la crescita del web rese evidente la necessità di proteggere le comunicazioni dei browser. Netscape era una delle prime software house concentrate sullo sviluppo di browser web; Netscape Navigator può essere visto come un antenato storico della linea Mozilla/Firefox.

L'evoluzione del protocollo può essere riassunta così:

|Versione|Anno|Autore / Ente|Note principali|
|---|---|---|---|
|**SSL 1.0**|1994|Netscape|Prototipo interno, mai pubblicato|
|**SSL 2.0**|1994|Netscape|Prima versione pubblica, con diverse debolezze|
|**SSL 3.0**|1996|Netscape / Paul Kocher|Revisione sostanziale del protocollo|
|**TLS 1.0**|1999|IETF|Standard Internet derivato da SSL 3.0|
|**TLS 1.1**|2006|IETF|Correzioni e miglioramenti di sicurezza|
|**TLS 1.2**|2008|IETF|Maggiore modularità crittografica, uso moderno delle funzioni hash|
|**TLS 1.3**|2018|IETF, RFC 8446|Handshake riprogettato, meno round trip, maggiore sicurezza e privacy|

TLS 1.0 è molto simile a SSL 3.0, ma non è semplicemente intercambiabile con esso: usa standard, dettagli crittografici e formati di messaggio differenti. Da TLS in poi, l'evoluzione è documentata tramite RFC dell'IETF, per esempio **RFC 8446** per TLS 1.3.

> 📌 SSL e TLS indicano quindi una continuità storica, ma nelle configurazioni moderne il termine corretto è TLS. Le versioni SSL sono da considerare obsolete.

---

### **5. Perché il protocollo è evoluto**

L'evoluzione da SSL a TLS, e poi da TLS 1.0 fino a TLS 1.3, non è stata solo un aggiornamento nominale. Ogni nuova versione nasce dalla scoperta di vulnerabilità o limiti nelle versioni precedenti.

I problemi emersi nel tempo possono essere classificati in tre categorie.

#### **5.1. Debolezze crittografiche**

Alcune versioni o configurazioni precedenti permettevano l'uso di algoritmi non più considerati robusti:

- algoritmi di scambio delle chiavi deboli o superati;

- modalità di cifratura vulnerabili;

- firme digitali o funzioni hash non più adeguate;

- cipher suite negoziabili ma non sicure.

#### **5.2. Bug di implementazione**

Anche quando il protocollo è progettato correttamente, un'implementazione può contenere errori. Esempi noti, trattati nelle lezioni successive, riguardano librerie e software TLS largamente usati, come nel caso di bug in OpenSSL o nella validazione delle firme/certificati.

#### **5.3. Difetti progettuali del protocollo**

Alcune vulnerabilità non dipendono dal singolo software, ma da scelte progettuali del protocollo. Il caso più importante è la possibilità di attacchi durante l'**handshake**, cioè proprio nella fase in cui client e server negoziano versione, algoritmi e chiavi.

Se l'handshake non protegge adeguatamente la negoziazione, un attaccante in posizione Man-in-the-Middle può provare a:

- forzare l'uso di una versione vecchia del protocollo;

- imporre una cipher suite debole;

- manipolare i parametri prima che l'integrità del canale sia pienamente garantita.

> ⚠️ Punto d'esame: una connessione cifrata è sicura solo se è sicura anche la negoziazione che porta alla scelta degli algoritmi e delle chiavi. Proteggere i dati dopo una negoziazione manipolata può non bastare.

---

### **6. Problemi risolti da TLS 1.3**

TLS 1.3 nasce per correggere diversi limiti delle versioni precedenti, in particolare di TLS 1.2 e delle configurazioni legacy.

#### **6.1. Attacchi di downgrade**

Nelle versioni precedenti, l'handshake poteva essere sfruttato per forzare client e server a usare una versione più vecchia o un algoritmo meno sicuro. TLS 1.3 riprogetta la negoziazione per ridurre drasticamente queste possibilità.

L'obiettivo è evitare che un attaccante inserito tra le parti possa far credere a entrambi che l'altra parte supporti solo modalità deboli.

#### **6.2. Uso delle firme digitali nell'handshake**

TLS 1.3 corregge anche il modo in cui le firme digitali vengono usate durante l'handshake. La firma deve coprire correttamente il contesto della negoziazione, altrimenti un attaccante potrebbe manipolare messaggi iniziali senza essere rilevato in modo tempestivo.

#### **6.3. Rimozione di modalità insicure**

TLS 1.3 elimina molte opzioni storiche pericolose invece di lasciarle negoziabili. Questo riduce la superficie di attacco: meno combinazioni legacy significa meno opportunità per downgrade, configurazioni deboli o errori implementativi.

---

### **7. Miglioramenti di TLS 1.3: performance e privacy**

TLS 1.3 non migliora solo la sicurezza: riduce anche il costo della creazione del canale sicuro e migliora alcune proprietà di privacy.

#### **7.1. Handshake più rapido**

TLS 1.3 introduce un nuovo handshake con meno scambi necessari per stabilire le chiavi tra client e server.

La modalità **1-RTT** (_One Round Trip Time_) permette di stabilire il canale sicuro in un solo round trip: il client può inviare già nel primo messaggio il proprio contributo Diffie-Hellman, così il server dispone subito del materiale necessario per derivare le chiavi.

#### **7.2. Ripresa di sessione e 0-RTT**

Se client e server hanno già comunicato in passato, possono memorizzare localmente materiale crittografico condiviso e usarlo per riprendere una sessione. Questo consente la modalità **0-RTT** (_Zero Round Trip Time_), in cui il client può inviare dati applicativi molto presto, sfruttando una chiave precondivisa derivata da una sessione precedente.

Questa ottimizzazione riduce la latenza, ma deve essere gestita con attenzione perché i dati 0-RTT hanno proprietà di sicurezza più delicate, in particolare rispetto ai replay.

#### **7.3. Privacy e Server Name Indication**

Nelle versioni precedenti, durante l'handshake il client poteva inviare in chiaro il campo **SNI** (_Server Name Indication_), che indica il nome del server richiesto. Questo era necessario per permettere a uno stesso indirizzo IP di ospitare più siti HTTPS, ma aveva un effetto collaterale: un osservatore di rete poteva conoscere il dominio visitato anche se il contenuto HTTP era cifrato.

TLS 1.3 riduce le informazioni esposte durante l'handshake e abilita un'evoluzione verso la protezione del nome del server tramite estensioni dedicate, come ESNI/ECH quando supportate. Nel linguaggio della lezione, l'idea fondamentale è che, se correttamente implementato, il nome di dominio del server non dovrebbe restare visibile a un osservatore esterno.

> 📌 HTTPS nasconde il contenuto delle pagine, ma non automaticamente ogni metadato. Il nome del server, se trasmesso in chiaro tramite SNI, può rivelare comunque molto sull'attività dell'utente.

---

### **8. Conclusione**

L'evoluzione da SSL a TLS 1.3 mostra un principio generale della sicurezza dei protocolli: non basta progettare una volta un meccanismo crittografico, perché nuove vulnerabilità, nuove capacità degli attaccanti e nuovi errori implementativi richiedono revisioni periodiche.

In sintesi:

- SSL nasce storicamente in Netscape per proteggere le comunicazioni web;

- TLS standardizza ed evolve quella famiglia di protocolli;

- il protocollo protegge confidenzialità, integrità e autenticazione del canale;

- la sicurezza può essere collocata a livelli diversi dello stack, ma TLS opera al livello di trasporto;

- TLS 1.3 migliora handshake, resistenza ai downgrade, performance e privacy.

> ✅ Per l'esame: TLS va capito come un protocollo di canale sicuro. Le sue parti più critiche sono handshake, scelta degli algoritmi, autenticazione tramite certificati e protezione dei dati applicativi dopo la negoziazione.
