## **Lezione 5: Altri attacchi a SSL/TLS**

### **1. Introduzione**

Questa lezione completa l’analisi degli attacchi storici a SSL/TLS concentrandosi su tre famiglie di problemi:

- attacchi di **downgrade** in presenza di Man-in-the-Middle;

- attacchi di **Denial of Service** che sfruttano il costo computazionale dell’handshake SSL/TLS;

- attacchi applicativi legati all’uso misto di **HTTPS** e **HTTP**, in particolare mixed content e SSLstrip.

Il tema comune è che TLS può essere robusto dal punto di vista crittografico, ma la sicurezza effettiva dipende anche dal contesto di rete, dalle scelte del browser, dalla configurazione del sito e dalla corretta imposizione dell’uso di HTTPS.

> 📌 Un canale TLS sicuro protegge il traffico solo se viene realmente instaurato e mantenuto. Molti attacchi non rompono la crittografia: impediscono, degradano o aggirano l’uso corretto di TLS.

---

### **2. Man-in-the-Middle e downgrade del protocollo TLS**

#### **2.1. Posizione dell’attaccante**

Un attacco di downgrade richiede tipicamente che l’attaccante sia in posizione di **Man-in-the-Middle**. Questo significa che l’attaccante riesce a intercettare, filtrare, modificare o bloccare il traffico tra client e server.

![](imgs/Pasted%20image%2020260709030216.png)

In una rete locale, una tecnica classica per ottenere questa posizione è l’**ARP poisoning**:

- l’attaccante convince il client che il proprio indirizzo MAC corrisponde all’indirizzo IP del gateway;

- convince il gateway che il proprio indirizzo MAC corrisponde all’indirizzo IP del client;

- il traffico client → gateway e gateway → client passa quindi attraverso l’attaccante.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Questa posizione consente all’attaccante di osservare e manipolare la fase iniziale della connessione, inclusi i messaggi di handshake TLS prima che la protezione crittografica sia pienamente attiva.

#### **2.2. Downgrade tramite modifica o caduta dei pacchetti**

Nelle versioni più vecchie di SSL, in particolare SSL 2.0, era possibile modificare direttamente i messaggi dell’handshake, per esempio alterando l’elenco delle cipher suite supportate dal client e lasciando solo cifrari deboli.

Con SSL 3.0 e versioni successive, questa manipolazione diretta è più difficile, perché l’handshake viene verificato tramite valori hash/finali che permettono a client e server di rilevare modifiche ai messaggi scambiati.

Tuttavia, un intermediario può ancora tentare un downgrade **senza modificare direttamente** i pacchetti TLS:

1. il client invia un `ClientHello` proponendo una versione moderna, per esempio TLS 1.2;

2. l’attaccante intercetta e scarta il pacchetto;

3. l’attaccante può inviare un segmento TCP `FIN, ACK` al server per chiudere la connessione corrente;

4. il client, non riuscendo a completare la connessione, ritenta;

5. in alcune implementazioni storiche, il nuovo tentativo può usare una versione inferiore di SSL/TLS;

6. se il downgrade riesce, client e server finiscono per negoziare una versione più debole.

![](imgs/Pasted%20image%2020260709030305.png)

> ⚠️ La differenza è sottile: l’attaccante non deve necessariamente falsificare tutto l’handshake. Può anche limitarsi a far fallire selettivamente i tentativi con versioni forti, inducendo il client a riprovare con versioni più vecchie.

#### **2.3. Perché il downgrade è pericoloso**

Il server dovrebbe scegliere la versione più alta e sicura supportata da entrambe le parti. Se però il client viene indotto a presentarsi come se supportasse solo versioni più vecchie, il server può accettare parametri più deboli.

Il risultato è un paradosso operativo: sia client sia server potrebbero supportare TLS moderno, ma la connessione effettiva viene portata su una versione vulnerabile.

In alcuni casi il browser può mostrare un avviso; storicamente, però, molti utenti ignoravano gli alert e proseguivano comunque la navigazione.

---

### **3. Denial of Service contro SSL/TLS**

#### **3.1. Asimmetria computazionale**

L’handshake SSL/TLS è più costoso per il server che per il client. Il docente evidenzia una stima didattica: stabilire una connessione SSL può richiedere al server circa **15 volte** la potenza computazionale richiesta al client.

Questo squilibrio crea un vettore di **Denial of Service**:

- l’attaccante apre molte connessioni SSL/TLS;

- il server risponde avviando handshake e derivazione dei parametri crittografici;

- molte connessioni non sono realmente usate per trasmettere dati;

- il server consuma CPU, memoria e risorse di connessione;

- gli utenti legittimi possono subire rallentamenti o indisponibilità.

#### **3.2. THC-SSL-DOS**

Citiamo ora **THC-SSL-DOS**, strumento incluso in distribuzioni orientate al penetration testing come Kali Linux, usato per verificare la resistenza di un server SSL/TLS a molte richieste di handshake o rinegoziazione. Fu sviluppato da The Hacker's Choice, da cui l'acronimo <3.

![](imgs/Pasted%20image%2020260709030512.png)

Il principio è semplice: automatizzare l’apertura di molte connessioni verso un servizio TLS, costringendo il server a svolgere ripetutamente operazioni crittografiche costose.

![](imgs/Pasted%20image%2020260709030541.png)

> ⚠️ Dal punto di vista difensivo, questo attacco mostra che TLS protegge il contenuto della comunicazione, ma non elimina automaticamente i problemi di disponibilità. La disponibilità va difesa con rate limiting, reverse proxy, bilanciamento, caching e mitigazioni anti-DoS.

![](imgs/Pasted%20image%2020260709030812.png)

#### **3.3. Contromisure**

Le contromisure includono:

- limitare il numero di handshake per IP o per prefisso di rete;

- usare bilanciatori e terminatori TLS dedicati;

- disabilitare rinegoziazioni non necessarie;

- applicare rate limiting e circuit breaker;

- monitorare picchi anomali di handshake falliti o incompleti;

- usare configurazioni TLS moderne, più efficienti e meno esposte a funzionalità legacy.

---

### **4. HTTPS come modello di protezione web**

HTTPS è progettato come protocollo **end-to-end sicuro per il Web**. In condizioni corrette, browser e server HTTPS instaurano un tunnel TLS che fornisce:

- **confidenzialità**, perché il contenuto HTTP non è leggibile da osservatori di rete;

- **integrità**, perché modifiche ai messaggi vengono rilevate;

- **autenticazione**, perché il browser verifica il certificato del server.

![](imgs/Pasted%20image%2020260709030829.png)

Questo modello dovrebbe impedire a un attaccante in rete di comportarsi come Man-in-the-Middle. Il problema è che molte applicazioni web, soprattutto storicamente, non usavano HTTPS in modo uniforme e obbligatorio.

---

### **5. Contenuto misto: HTTPS e HTTP nella stessa pagina**

#### **5.1. Definizione**

Si parla di **mixed content** quando una pagina principale viene servita via HTTPS, ma include risorse caricate via HTTP.

Esempi di risorse miste:

- script JavaScript;

- immagini;

- file Flash nelle applicazioni storiche;

- fogli di stile;

- frame o risorse esterne.

Il problema è grave soprattutto per i contenuti **attivi**, come script o componenti Flash: se arrivano via HTTP, un attaccante di rete può modificarli prima che raggiungano il browser.

#### **5.2. Comportamento storico dei browser**

Il PDF cita differenze storiche tra browser:

![](imgs/Pasted%20image%2020260709030952.png)

- Internet Explorer 7 mostrava un warning di contenuto misto o rimuoveva l’icona di lucchetto;

- Firefox indicava il problema in modo meno invasivo, per esempio con un simbolo sopra il lucchetto;

- Safari, in alcuni casi, non rilevava correttamente il mixed content.

Inoltre, contenuti Flash serviti via HTTP potevano non generare avvisi in IE7 e Firefox, pur essendo in grado di interagire con la pagina che li incorporava.

> 📌 Il lucchetto HTTPS può essere fuorviante se la pagina include contenuti attivi non protetti. Basta uno script HTTP modificabile dall’attaccante per compromettere la sessione dell’utente.

#### **5.3. Errore dello sviluppatore**

Un errore tipico consiste nell’includere script con URL HTTP assoluti:

```html
<script src="http://www.site.com/script.js"></script>
```

Se la pagina della banca o del servizio web è HTTPS, ma lo script viene caricato in HTTP, un attaccante attivo può sostituire lo script e dirottare la sessione.

Una forma storicamente più corretta era usare URL senza schema:

```html
<script src="//www.site.com/script.js"></script>
```

In questo modo la risorsa viene caricata con lo stesso protocollo della pagina che la incorpora. Nelle applicazioni moderne, la scelta più chiara è usare direttamente HTTPS e policy che impediscano il caricamento di contenuti attivi non sicuri.

---

### **6. HTTP → HTTPS e attacco SSLstrip**

#### **6.1. Pattern tipico di upgrade**

Molti siti, soprattutto in passato, seguivano questo schema:

- l’utente entra sul sito digitando un URL HTTP;

- il server reindirizza a HTTPS solo per il login;

- alcune parti del sito restano navigabili in HTTP;

- il checkout, il pagamento o l’area privata vengono poi spostati in HTTPS.

Questo pattern crea una finestra di attacco: prima che l’utente sia davvero dentro HTTPS, un Man-in-the-Middle può manipolare i link e i redirect.

#### **6.2. Meccanismo di SSLstrip**

**SSLstrip** è un attacco/tool che rimuove la protezione SSL/TLS dal punto di vista della vittima, trasformando collegamenti e richieste HTTPS in HTTP.

L’attaccante deve prima mettersi in mezzo, per esempio tramite ARP poisoning. Poi:

1. intercetta il traffico del client;

2. inoltra al server le richieste reali, anche usando HTTPS lato server;

3. modifica verso il client i riferimenti `https://` in `http://`;

4. riscrive redirect `Location: https://...` in `Location: http://...`;

5. riscrive form con `action="https://..."` in `action="http://..."`;

6. mantiene con la vittima una connessione non protetta.

![](imgs/Pasted%20image%2020260709031100.png)

![](imgs/Pasted%20image%2020260709031127.png)

Il client crede di interagire normalmente con il sito, ma le credenziali e i dati sensibili viaggiano in chiaro tra client e attaccante. SSLstrip può quindi registrare su file di testo transazioni, richieste e dati di login.

#### **6.3. Perché funziona**

L’attacco funziona quando il sito o il browser non impongono HTTPS per tutta la sessione di navigazione. Se è ancora possibile richiedere pagine o risorse in HTTP, l’attaccante può mantenere la vittima nel canale debole.

> ⚠️ SSLstrip non rompe TLS: evita che TLS venga usato correttamente tra vittima e sito. È un attacco alla transizione HTTP→HTTPS e alla politica di accesso.

---

### **7. HTTP Strict Transport Security**

#### **7.1. Idea**

La principale contromisura contro SSLstrip è **HTTP Strict Transport Security**, abbreviato **HSTS**. Nel transcript viene citato come “HTST”, ma il nome corretto dello standard è HSTS.

HSTS impone che, per un certo sito, il browser usi sempre HTTPS per tutta la durata della policy.

Con HSTS:

- il browser rifiuta connessioni HTTP semplici verso il dominio protetto;

- eventuali richieste HTTP vengono automaticamente trasformate in HTTPS;

- se la connessione sicura non è possibile, il browser deve fallire invece di degradare a HTTP;

- il sito comunica la policy tramite un header HTTP ricevuto su connessione HTTPS.

#### **7.2. Requisiti**

Per funzionare correttamente, la policy deve essere supportata sia dal server sia dal browser.

Il server deve dichiarare la policy; il browser deve ricordarla e applicarla nelle visite successive. Per evitare il problema della prima visita, i siti più importanti possono anche essere inseriti in liste di **HSTS preload**, distribuite direttamente con i browser.

#### **7.3. Effetto sulla sicurezza**

HSTS riduce drasticamente la superficie per SSLstrip:

- non permette all’attaccante di mantenere la vittima in HTTP;

- elimina downgrade silenziosi a HTTP;

- rende più evidente un tentativo di intercettazione;

- obbliga l’uso di HTTPS anche per risorse e pagine successive.

> 📌 Oggi l’uso sistematico di HTTPS e HSTS è una delle ragioni per cui SSLstrip è molto meno efficace contro i grandi servizi web rispetto al passato.

---

### **8. Sintesi finale**

|Attacco|Idea|Condizione necessaria|Contromisura principale|
|---|---|---|---|
|Protocol downgrade|Indurre client/server a usare una versione TLS più debole|MITM e fallback insicuro|TLS moderno, downgrade protection, disabilitare versioni obsolete|
|SSL/TLS DoS|Sfruttare il costo dell’handshake lato server|Molte connessioni o rinegoziazioni|Rate limiting, terminatori TLS, anti-DoS|
|Mixed content|Inserire o modificare risorse HTTP dentro pagina HTTPS|Risorse attive caricate via HTTP|Bloccare active mixed content, usare solo HTTPS|
|SSLstrip|Trasformare HTTPS in HTTP verso la vittima|MITM e assenza di HSTS|HSTS, HTTPS obbligatorio, preload|

> ✅ Per l’esame: questi attacchi mostrano che la sicurezza TLS è un sistema completo. Non basta scegliere un buon cifrario: bisogna impedire downgrade, proteggere l’handshake, usare HTTPS ovunque, evitare mixed content e imporre HSTS.
