## **Lezione 6: Esercitazioni con IPTables**

### **1. Obiettivo della lezione**

Questa lezione traduce la teoria su Netfilter e `iptables` in esercizi pratici.

Il punto centrale non è imparare singoli comandi a memoria, ma capire:

- dove va inserita una regola;
- quale chain attraversa il pacchetto;
- quali campi del pacchetto devono essere filtrati;
- quando serve filtraggio stateless;
- quando serve filtraggio stateful;
- come organizzare regole complesse in chain personalizzate.

> 📌 Una regola firewall corretta nasce sempre dalla comprensione del percorso del pacchetto e del protocollo che si vuole controllare.

---

### **2. Ambiente di laboratorio: IMUNES**

Per esercitarsi con firewall e `iptables`, il docente suggerisce l'uso di **IMUNES**.

https://imunes.net/

IMUNES è un simulatore di rete basato su container Docker. Tramite interfaccia grafica consente di:

- disegnare una topologia di rete;
- collegare host, router e switch;
- assegnare configurazioni iniziali;
- avviare la simulazione;
- aprire una shell sui singoli nodi;
- configurare `iptables` su host o router;
- installare applicazioni utili ai test, come `netcat`, `curl`, server web o servizi di rete.

![](imgs/Pasted%20image%2020260709160917.png)

Ogni nodo è un container. Questo rende l'ambiente comodo per sperimentare, ma introduce una conseguenza importante: quando la simulazione viene rilanciata, si può ripartire dall'immagine originale.

Per non perdere il lavoro è necessario:

- salvare la simulazione;
- mantenere le configurazioni rilevanti;
- reinstallare o predisporre i servizi necessari se l'immagine viene ricreata;
- evitare, quando possibile, riavvii inutili dell'intera simulazione.

> ⚠️ Nei laboratori containerizzati la configurazione non va considerata permanente se non viene salvata esplicitamente.

---

### **3. Esercizio 1: filtraggio della porta 8080**

Il primo esercizio usa una topologia minima con:

- un router;
- due host;
- due reti già configurate;
- indirizzi IP, MAC address e interfacce predisposti dal simulatore.

![](imgs/Pasted%20image%2020260709160857.png)

L'obiettivo è bloccare traffico TCP verso la porta `8080`, prima direttamente sull'host destinatario e poi sul router.

#### **3.1. Test iniziale con Netcat**

Sull'host 2 si mette `netcat` in ascolto sulla porta `8080`:

```bash
nc -l -p 8080
```

Dall'host 1 ci si collega all'host 2:

```bash
nc <ip_host2> 8080
```

Se la connessione funziona, quello che viene scritto da host 1 arriva a host 2. A questo punto si può introdurre una regola firewall e verificare che il traffico non venga più recapitato.

#### **3.2. Blocco sull'host destinatario**

Se la regola viene applicata direttamente sull'host 2, il pacchetto è destinato al sistema locale. La chain corretta è quindi `INPUT`.

```bash
iptables -t filter -I INPUT -p tcp --dport 8080 -j DROP
```

La regola dice:

- tabella `filter`;
- inserimento in testa alla chain `INPUT`;
- protocollo TCP;
- porta destinazione `8080`;
- target `DROP`.

Per rimuovere le regole di test:

```bash
iptables -F
```

#### **3.3. Blocco sul router**

Se invece il blocco viene applicato sul router, il pacchetto non è destinato al router. Il router lo deve inoltrare da una rete all'altra.

La chain corretta è quindi `FORWARD`.

```bash
iptables -t filter -I FORWARD -p tcp --dport 8080 -d <ip_host2> -j DROP
```

Qui è utile indicare anche la destinazione, perché sul router passano molti pacchetti non necessariamente diretti all'host 2.

> ⚠️ In `iptables`, molte opzioni dipendono dal protocollo. Conviene specificare prima `-p tcp` e poi opzioni TCP come `--dport`.

---

### **4. Esercizio 2: filtraggio di un web server**

Il secondo esercizio filtra l'accesso a un server web.

Un server web HTTP ascolta normalmente su:

- protocollo TCP;
- porta `80`.

Sull'host 2 si avvia il servizio web, per esempio `lighttpd`:

```bash
service lighttpd start
```

Dall'host 1 si prova a scaricare la pagina predefinita:

```bash
curl <ip_host2>
```

Se il server risponde, il traffico HTTP è consentito.

Per bloccarlo sul router:

```bash
iptables -A FORWARD -p tcp --dport 80 -d <ip_host2> -j DROP
```

Dopo la regola, `curl` non dovrebbe più ricevere la pagina.

> 📌 Per bloccare un servizio bisogna conoscere il protocollo che usa. HTTP usa TCP/80; SSH usa TCP/22; altri servizi possono usare più connessioni o porte dinamiche.

Il docente insiste su questo punto: prima di scrivere una regola bisogna studiare il funzionamento del servizio al livello di filtraggio disponibile.

Se si lavora a livello packet filter, servono soprattutto:

- protocollo di trasporto;
- porte;
- direzione delle connessioni;
- eventuali connessioni multiple.

Se invece si usa un filtro applicativo, bisogna conoscere anche la semantica del protocollo dentro il payload.

---

### **5. Scelta della chain corretta**

La scelta della chain è uno degli aspetti più importanti degli esercizi.

#### **5.1. Regole sul router**

Su un router o firewall perimetrale, nella maggior parte dei casi si lavora sulla chain `FORWARD`.

Il router riceve pacchetti non destinati a sé e li inoltra verso un'altra rete.

Eccezioni:

- traffico diretto all'interfaccia di amministrazione del router;
- pagina web di configurazione del firewall;
- SSH o altro accesso remoto al router;
- servizi locali esposti dal router.

In questi casi il pacchetto è destinato al router e quindi attraversa `INPUT`.

#### **5.2. Regole sul server**

Su un server si usano soprattutto:

- `INPUT`, per traffico ricevuto dal server;
- `OUTPUT`, per traffico generato dal server.

Esempi:

- un web server che riceve richieste HTTP va filtrato in `INPUT`;
- un mail server che invia posta verso l'esterno genera traffico in `OUTPUT`;
- un servizio bidirezionale può richiedere regole in entrambe le direzioni.

> 📌 Prima domanda operativa: "il pacchetto è destinato a questa macchina, è generato da questa macchina, o la attraversa soltanto?".

---

### **6. Esercizio 3: filtraggio selettivo**

Il terzo esercizio estende la topologia con un terzo host.

![](imgs/Pasted%20image%2020260709161024.png)

Obiettivi:

- host 3 non deve accedere al web server;
- host 1 non deve accedere via SSH né a host 2 né a host 3.

Le regole sono da applicare sul router, quindi sulla chain `FORWARD`.

Per bloccare l'accesso web di host 3 verso host 2:

```bash
iptables -A FORWARD -p tcp --dport 80 -d <ip_host2> -s <ip_host3> -j DROP
```

Per bloccare SSH da host 1 verso host 2:

```bash
iptables -A FORWARD -p tcp --dport 22 -d <ip_host2> -s <ip_host1> -j DROP
```

Per bloccare SSH da host 1 verso host 3:

```bash
iptables -A FORWARD -p tcp --dport 22 -d <ip_host3> -s <ip_host1> -j DROP
```

---

### **7. Estensione: blocco di un'intera rete**

L'esercizio viene poi esteso aggiungendo un quarto host nella stessa rete di host 3, tramite uno switch.

![](imgs/Pasted%20image%2020260709161046.png)

L'obiettivo diventa bloccare l'accesso al server web da tutta la nuova rete.

Se la rete è `10.0.2.0/24`, la regola può essere:

```bash
iptables -A FORWARD -p tcp --dport 80 -d <ip_web_server> -s 10.0.2.0/24 -j DROP
```

La notazione `/24` indica che i primi 24 bit identificano la rete. In un indirizzamento classico di questo tipo, equivale a dire `10.0.2.*`: tutti gli host della rete `10.0.2.0`.

> 📌 Usare una rete sorgente consente di sostituire molte regole host-per-host con una sola regola più generale.

---

### **8. Filtraggio stateful con `iptables`**

Finora gli esempi hanno usato un filtraggio prevalentemente stateless: indirizzi, protocolli e porte.

`iptables` può però usare il connection tracking tramite moduli di stato.

Per abilitare regole stateful si usa un modulo di match, storicamente:

```bash
-m state
```

oppure, nelle configurazioni più moderne:

```bash
-m conntrack
```

Il transcript usa il modulo `state`, quindi gli esempi mantengono quella forma.

#### **8.1. Politica di partenza chiusa**

Lo scenario didattico parte da una policy restrittiva: di default tutto è negato, e si aggiungono solo le regole che devono consentire traffico.

Per accettare nuove connessioni SSH e HTTP dall'esterno:

```bash
$IPTABLES -A INPUT -p tcp -i $INET_IFACE --dport 22 -m state --state NEW -j ACCEPT
$IPTABLES -A INPUT -p tcp -i $INET_IFACE --dport 80 -m state --state NEW -j ACCEPT
```

Per consentire in uscita le risposte appartenenti a connessioni già note:

```bash
$IPTABLES -A OUTPUT -o $INET_IFACE -m state --state ESTABLISHED,RELATED -j ACCEPT
```

La logica è:

- si accettano nuove connessioni solo verso servizi autorizzati;
- si accettano pacchetti di risposta solo se appartengono a connessioni già stabilite o correlate;
- non si aprono indiscriminatamente porte in uscita.

> 📌 Lo stateful filtering evita di scrivere regole simmetriche troppo permissive: le risposte passano perché sono riconosciute come parte di una connessione ammessa.

---

### **9. Ispezione del payload con match `string`**

Un esempio più avanzato usa il modulo `string` per cercare una stringa nel payload.

L'obiettivo è bloccare richieste HTTP che contengono una certa parola, per esempio `facebook`.

```bash
$IPTABLES -A OUTPUT -p tcp --dport 80 -m string --string "facebook" --algo kmp -j DROP
```

La regola:

- opera in `OUTPUT`;
- considera traffico TCP verso porta `80`;
- attiva il modulo `string`;
- cerca la stringa `facebook`;
- usa l'algoritmo `kmp`, cioè Knuth-Morris-Pratt;
- droppa il pacchetto se la stringa è trovata.

> ⚠️ Questo tipo di controllo funziona solo se il contenuto è visibile. Con HTTPS, il payload applicativo è cifrato e una regola `string` su HTTP non vede l'URL o il contenuto in chiaro.

---

### **10. Supporto FTP stateful**

FTP è un protocollo più articolato perché usa più connessioni.

Bisogna distinguere:

- connessione di controllo, sulla porta TCP `21`;
- connessione dati in modalità attiva;
- connessione dati in modalità passiva.

Per gestire correttamente FTP in modo stateful, il firewall deve riconoscere che le connessioni dati sono **RELATED** rispetto alla connessione di controllo.

Questo può richiedere moduli specifici di connection tracking per FTP.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.1. Connessione di controllo**

La connessione di controllo verso il server FTP usa porta destinazione `21`.

```bash
iptables -A OUTPUT -p tcp --dport 21 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A INPUT  -p tcp --sport 21 -m state --state ESTABLISHED -j ACCEPT
```

La prima regola consente l'apertura della connessione di controllo. La seconda consente le risposte del server sulla stessa connessione già stabilita.

#### **10.2. FTP attivo**

Nella modalità attiva, il server apre la connessione dati dalla porta `20` verso una porta alta del client.

```bash
iptables -A INPUT  -p tcp --sport 20 --dport 1024: -m state --state RELATED,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -p tcp --sport 1024: --dport 20 -m state --state ESTABLISHED -j ACCEPT
```

La connessione dati è correlata alla connessione di controllo, quindi `RELATED` è essenziale.

#### **10.3. FTP passivo**

Nella modalità passiva, il client apre una connessione dati verso una porta alta indicata dal server.

```bash
iptables -A OUTPUT -p tcp --dport 1024: --sport 1024: -m state --state RELATED,ESTABLISHED -j ACCEPT
iptables -A INPUT  -p tcp --sport 1024: --dport 1024: -m state --state ESTABLISHED -j ACCEPT
```

> 📌 FTP mostra perché conoscere il protocollo è indispensabile: non basta sapere che "FTP usa la porta 21", perché il trasferimento dati usa connessioni ulteriori.

---

### **11. Logging dei pacchetti**

Il target `LOG` permette di registrare pacchetti che fanno match con una regola.

È utile per:

- debug;
- analisi del traffico;
- verifica delle regole;
- ricostruzione di eventi;
- alimentare strumenti di analisi successivi.

Esempi:

```bash
iptables -A FORWARD -j LOG --log-prefix "FORWARD: "
iptables -A INPUT  -j LOG --log-prefix "INPUT: "
iptables -A OUTPUT -j LOG --log-prefix "OUTPUT: "
```

> ⚠️ Se si vuole loggare un pacchetto prima di bloccarlo, la regola di `LOG` deve comparire prima della regola di `DROP` o `REJECT`.

#### **11.1. Opzioni di logging**

Opzioni utili:

- `--log-level`, per scegliere il livello syslog;
- `--log-prefix`, per aggiungere un prefisso riconoscibile;
- `--log-tcp-sequence`, per includere sequence number TCP;
- `--log-tcp-options`, per includere opzioni TCP;
- `--log-ip-options`, per includere opzioni IP;
- `--log-uid`, per loggare l'UID del processo che ha generato il pacchetto, utile in `OUTPUT`.

Un prefisso come `INPUT TCP:` o `FORWARD:` permette di capire rapidamente quale parte della configurazione ha prodotto la riga di log.

![](imgs/Pasted%20image%2020260709161325.png)

Il log può includere informazioni come:

- timestamp;
- host che ha generato il log;
- interfaccia di ingresso/uscita;
- sorgente;
- destinazione;
- protocollo;
- lunghezza del pacchetto;
- eventuali dettagli TCP/IP.

---

### **12. Chain personalizzate**

Con molte regole, una configurazione firewall può diventare difficile da leggere.

Per questo `iptables` permette di creare chain definite dall'utente, dette anche **user-specific chain**.

Le chain personalizzate servono a separare logicamente controlli diversi, per esempio:

- pacchetti TCP malformati;
- regole TCP;
- regole UDP;
- regole ICMP;
- traffico ammesso;
- controlli specifici per un servizio.

Il passaggio da una chain all'altra avviene tramite target `-j <nome_chain>`.

> 📌 Le chain personalizzate rendono il firewall più leggibile: non cambiano la semantica di Netfilter, ma organizzano meglio le decisioni.

---

### **13. Chain `bad_tcp_packets`**

Una chain tipica può raccogliere controlli sui pacchetti TCP anomali o malformati.

```bash
$IPTABLES -N bad_tcp_packets
```

#### **13.1. Pacchetti NEW senza SYN**

Un pacchetto classificato come `NEW` ma senza flag SYN è sospetto: una nuova connessione TCP dovrebbe iniziare con SYN.

Può derivare da:

- errori di protocollo;
- perdita dello stato del firewall;
- pacchetti residui di connessioni precedenti;
- tentativi di scansione o evasione.

Prima si logga:

```bash
$IPTABLES -A bad_tcp_packets -p tcp ! --syn -m state --state NEW -j LOG --log-prefix "NEW not SYN: "
```

Poi si scarta:

```bash
$IPTABLES -A bad_tcp_packets -p tcp ! --syn -m state --state NEW -j DROP
```

#### **13.2. Pacchetti SYN+ACK classificati come NEW**

Un pacchetto `SYN,ACK` classificato come `NEW` può indicare un'anomalia, perché un `SYN,ACK` è normalmente risposta a un SYN precedente.

```bash
$IPTABLES -A bad_tcp_packets -p tcp --tcp-flags SYN,ACK SYN,ACK -m state --state NEW -j REJECT --reject-with tcp-reset
```

In questo caso si usa `REJECT --reject-with tcp-reset`: invece di ignorare silenziosamente, si invia un reset TCP e si chiude in modo esplicito la connessione anomala.

> ⚠️ `DROP` e reset TCP hanno effetti protocollari diversi: `DROP` tace, `REJECT --reject-with tcp-reset` comunica la chiusura della connessione.

---

### **14. Chain `allowed`**

Una chain `allowed` può incapsulare la logica delle connessioni TCP ammesse.

```bash
$IPTABLES -N allowed
$IPTABLES -A allowed -p tcp --syn -j ACCEPT
$IPTABLES -A allowed -p tcp -m state --state ESTABLISHED,RELATED -j ACCEPT
$IPTABLES -A allowed -p tcp -j DROP
```

La logica è:

1. accettare nuove connessioni TCP coerenti con SYN;
2. accettare pacchetti di connessioni già stabilite o correlate;
3. droppare tutto il resto.

Questa chain può poi essere riutilizzata dalle chain specifiche di protocollo o servizio.

---

### **15. Chain per TCP, UDP e ICMP**

#### **15.1. TCP**

Una chain dedicata ai pacchetti TCP può inoltrare verso `allowed` solo le porte che devono essere aperte.

```bash
$IPTABLES -N tcp_packets
$IPTABLES -A tcp_packets -p tcp -s 0/0 --dport 21 -j allowed
$IPTABLES -A tcp_packets -p tcp -s 0/0 --dport 22 -j allowed
$IPTABLES -A tcp_packets -p tcp -s 0/0 --dport 80 -j allowed
```

In questo modo la lista delle porte TCP consentite resta separata dalla logica generale di validità della connessione.

#### **15.2. UDP**

UDP non ha connessione TCP-like, quindi si ragiona soprattutto su porte e direzioni.

```bash
$IPTABLES -N udp_packets
$IPTABLES -A udp_packets -p udp --dport 53 -j ACCEPT
$IPTABLES -A udp_packets -p udp --dport 123 -j ACCEPT
$IPTABLES -A udp_packets -p udp -i $INET_IFACE -d $INET_BROADCAST --dport 135:139 -j DROP
```

Esempi:

- `53/udp` per DNS;
- `123/udp` per NTP;
- blocco di traffico broadcast o NetBIOS indesiderato verso porte `135:139`.

#### **15.3. ICMP**

ICMP non usa porte, quindi si filtra per tipo di messaggio.

```bash
$IPTABLES -N icmp_packets
$IPTABLES -A icmp_packets -p icmp --icmp-type 8 -j ACCEPT
$IPTABLES -A icmp_packets -p icmp --icmp-type 11 -j ACCEPT
```

Tipo `8` indica Echo Request, usato dal ping; tipo `11` indica Time Exceeded, utile per segnalazioni di superamento del TTL e strumenti come traceroute.

> 📌 Ogni protocollo richiede campi diversi: TCP/UDP usano porte, ICMP usa tipi e codici.

---

### **16. Collegamento delle chain personalizzate**

Creare chain non basta: bisogna anche fare in modo che il traffico ci salti dentro.

Esempio di integrazione:

```bash
$IPTABLES -A INPUT -p all -m state --state ESTABLISHED,RELATED -j ACCEPT
$IPTABLES -A INPUT -p tcp -j bad_tcp_packets
$IPTABLES -A INPUT -p tcp -i $INET_IFACE -j tcp_packets
$IPTABLES -A INPUT -p udp -i $INET_IFACE -j udp_packets
$IPTABLES -A INPUT -p icmp -i $INET_IFACE -j icmp_packets
```

La struttura completa diventa:

- prima si accetta ciò che appartiene a connessioni già note;
- poi si controllano anomalie TCP;
- poi si invia il traffico TCP, UDP o ICMP alle chain dedicate;
- ogni chain applica le proprie regole specializzate.

![](imgs/Pasted%20image%2020260709175445.png)

> ⚠️ Una chain personalizzata inutilizzata non filtra nulla. Deve essere richiamata da una chain attraversata realmente dal traffico.

---

### **17. Esempio di script completo**

Il transcript mostra un esempio di script che crea chain e poi collega i vari controlli.

L'idea generale è:

1. creare `bad_tcp_packets`;
2. creare `allowed`;s
3. creare `tcp_packets`, `udp_packets`, `icmp_packets`;
4. inserire in ciascuna chain le regole specifiche;
5. fare `jump` dalle chain principali verso le chain personalizzate.

![](imgs/Pasted%20image%2020260709175457.png)

In uno script reale questo approccio evita di avere una configurazione monolitica e difficile da verificare.

> 📌 Le chain personalizzate sono uno strumento di progettazione: permettono di separare controlli generali, controlli per protocollo e regole di servizio.

---

### **18. Esercizio complesso: DMZ, LAN e servizi**

L'ultima parte propone un esercizio più realistico.

La topologia contiene:

- un router/firewall;
- una LAN interna;
- una DMZ separata dalla LAN;
- un web server nella DMZ;
- un DNS server nella DMZ;
- un mail server nella DMZ, con SMTP.

![](imgs/Pasted%20image%2020260709175509.png)

I requisiti indicati sono:

- solo la LAN interna può accedere ai servizi web nella DMZ;
- il server di posta deve poter inviare messaggi verso la rete esterna;
- la configurazione deve essere implementata sul firewall rispettando le direzioni dei flussi;
- la soluzione va verificata sperimentalmente ed eventualmente discussa con il tutor.

Questo esercizio combina:

- scelta corretta della chain `FORWARD`;
- conoscenza delle porte dei servizi;
- separazione LAN/DMZ/esterno;
- filtraggio stateful;
- policy restrittiva;
- eventuale logging per debug.

> ✅ Il laboratorio finale obbliga a ragionare come in una rete reale: non basta bloccare una porta, bisogna tradurre requisiti di sicurezza in flussi consentiti e negati.

---

### **19. Conclusione**

Le esercitazioni mostrano una progressione:

- prima si blocca una singola porta;
- poi si filtra un servizio web;
- poi si distingue tra host, router e server;
- poi si filtra una rete intera;
- poi si usa lo stato delle connessioni;
- poi si analizza il payload;
- poi si gestiscono protocolli multi-connessione come FTP;
- infine si organizzano regole complesse tramite chain personalizzate.

Il messaggio didattico è che `iptables` non è solo una sintassi di comandi: è uno strumento per trasformare una politica di sicurezza in regole eseguibili dal kernel.

> 📌 Una configurazione efficace è quella che resta verificabile: chi legge le regole deve capire quali flussi sono ammessi, quali sono bloccati e perché.
