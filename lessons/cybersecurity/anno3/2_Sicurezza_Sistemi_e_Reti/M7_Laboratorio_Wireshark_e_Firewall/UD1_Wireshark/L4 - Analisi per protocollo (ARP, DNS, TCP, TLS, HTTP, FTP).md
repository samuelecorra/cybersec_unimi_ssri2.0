## **Lezione 4: Analisi per protocollo (ARP, DNS, TCP, TLS, HTTP, FTP)**

Questa lezione mette a fuoco *cosa guardare* in ciascun protocollo che ricorre agli esami, e come da esso ricavare le risposte tipiche: chi sono i nodi, che hardware/software usano, che cosa hanno fatto.

### **1. Dedurre hardware e software dei nodi (la domanda ricorrente)**

Quasi ogni traccia d'esame chiede le "*caratteristiche hardware/software dei nodi server e client*". Non è una domanda a cui si risponde per magia: le informazioni si **leggono dalla cattura** da tre fonti.

- **OUI del MAC (hardware).** I primi 24 bit del MAC identificano il produttore della scheda di rete. Wireshark li risolve automaticamente. Esempi ricorrenti: `00:0c:29` e `00:50:56` → **VMware** (la macchina è una VM); `00:01:96` → **Cisco**; `00:08:02` → **Hewlett-Packard**; `20:e5:2a` → **ASUSTek**; `00:d0:59` → **Ambit Microsystems**. Vedere due MAC VMware significa che client e server sono macchine virtuali sullo stesso host.
- **Banner e header applicativi (software).** Molti servizi si presentano: il banner FTP `220 ...`, l'header HTTP `Server:` (es. `Apache`, `nginx`, `Microsoft-IIS`), l'header `User-Agent:` del client.
- **`User-Agent` (sistema operativo e browser del client).** È la miniera più ricca: dalla stringa si leggono OS e browser. Esempio reale (traccia del 02/07): `Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0)` → **Windows 7** (NT 6.1) a 64 bit con **Internet Explorer 11** (Trident/7.0). Le richieste ai domini `*.services.mozilla.com` nella traccia del 13/06 rivelano invece un client **Firefox**.

> 📌 Formula d'esame per "hardware/software": *hardware* → produttore dal MAC-OUI (spesso "VMware ⇒ macchina virtuale"); *software del client* → `User-Agent` (OS + browser); *software del server* → banner/`Server:` header. Cita sempre il pacchetto da cui hai letto il dato.

---

### **2. ARP — chi sta sulla LAN**

L'ARP (vedi [M4/UD2/L2 – MAC Flooding e ARP Spoofing](../../M4/UD2/L2%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md)) associa IP a MAC nella rete locale. Nel filtro `arp` si vedono le *request* in broadcast ("Who has 10.0.0.1? Tell 10.0.0.5") e le *reply* in unicast. Serve a mappare gli host locali e a smascherare un **ARP spoofing**: se due reply associano lo **stesso IP a MAC diversi**, o un MAC risponde per molti IP, c'è avvelenamento della cache in corso. Wireshark segnala esplicitamente "duplicate use of ... detected".

---

### **3. DNS — cosa si sta cercando di raggiungere**

Il filtro `dns` mostra query e risposte. Ogni query ha un **nome** (`dns.qry.name`) e un **tipo** (`A` = IPv4, `AAAA` = IPv6, `MX`, `CNAME`, `PTR`…); la risposta porta gli indirizzi risolti, spesso attraverso catene di `CNAME`. Il DNS è prezioso perché **rivela le intenzioni** anche quando il traffico successivo è cifrato: i nomi cercati raccontano dove il client voleva andare.

> 💡 Esempio (13/06): la primissima query è `contile.services.mozilla.com` (tipo A → `34.117.188.166`, tipo AAAA senza risposta), seguita da `content-signature-2.cdn.mozilla.net`. Sono servizi interni di Firefox: da qui si deduce sia il browser sia il fatto che la connessione TLS immediatamente successiva verso `34.117.188.166:443` va proprio a quel servizio. Nella traccia del 02/07 le query a `smart-fax.com` e al dominio dinamico `dunlop.hopto.org` sono invece il primo indizio dell'attività malevola.

---

### **4. TCP — handshake, sequenze, flag**

Il livello TCP è dove si leggono le meccaniche di connessione (vedi [M4/UD2/L1 – Vulnerabilità di TCP/IP](../../M4/UD2/L1%20-%20Vulnerabilit%C3%A0%20di%20TCP%20IP.md)). Gli elementi da saper individuare:

- Il **three-way handshake**: `SYN` → `SYN,ACK` → `ACK`. Il primo `SYN` marca l'inizio della connessione e ne rivela client (chi lo invia) e server (chi lo riceve, sulla porta del servizio).
- I **flag** (`tcp.flags`): `SYN` apertura, `FIN` chiusura ordinata, `RST` chiusura brusca (o rifiuto/reset attack), `PSH+ACK` consegna dati, `ACK` conferma.
- I numeri di **sequenza** e **acknowledgment**: Wireshark ne mostra la versione "relativa" (a partire da 1) per leggibilità, ma i valori assoluti sono nei dettagli — indispensabili per ragionare su reset e hijacking (vedi [M4/UD5/L1 – TCP Session Hijacking e Reset Attack](../../M4/UD5_Approfondimenti_Esame/L1%20-%20TCP%20Session%20Hijacking%20e%20Reset%20Attack.md)).

> 💡 Per rispondere a "*quali nodi nella prima connessione TCP*" basta `tcp.stream eq 0` e leggere il SYN; espandendo Ethernet si ottengono anche i MAC. Le porte dicono il servizio: 21 FTP, 25 SMTP, 53 DNS, 80 HTTP, 443 HTTPS/TLS.

---

### **5. TLS — cosa si vede di una sessione cifrata**

In una sessione **TLS** (filtro `tls`) il contenuto applicativo è cifrato, ma **l'handshake iniziale è in chiaro** e racconta parecchio:

- **Client Hello**: propone versioni e cipher suite, e — cruciale — l'estensione **SNI** (*Server Name Indication*, `tls.handshake.extensions_server_name`), che contiene **in chiaro** il nome del server che il client vuole raggiungere. È il modo per sapere "dove" va una connessione HTTPS senza poterne leggere i dati.
- **Server Hello**: il server sceglie versione e cipher suite.
- **Certificate**: il certificato del server (nelle versioni fino a TLS 1.2 è in chiaro; in TLS 1.3 è cifrato), da cui si leggono soggetto ed emittente.
- **Key Exchange / Change Cipher Spec / Finished**: completano lo scambio, dopodiché segue **Application Data** cifrata.

> 💡 Descrivere una sessione TLS all'esame significa: identificare il server dall'**SNI** (es. nel 13/06 `contile.services.mozilla.com` o `www.exploit-db.com`), riconoscere la sequenza Client Hello → Server Hello → Certificate → scambio chiavi → dati cifrati, e osservare la versione negoziata (TLS 1.2/1.3). Il fatto stesso che il payload sia illeggibile è la risposta al "perché non vedo i dati".

---

### **6. HTTP — richieste e risposte in chiaro**

L'HTTP (porta 80, filtro `http`) è testuale e quindi completamente leggibile. Da guardare:

- **Richiesta**: metodo (`GET`, `POST`, `HEAD`), URL richiesto (`http.request.uri`), header `Host:` (il sito), `User-Agent:`, `Referer:`; nelle `POST` il **body** può contenere dati inviati (form, credenziali).
- **Risposta**: status line (`200 OK`, `301/302` redirect, `404 Not Found`, `401/403`), `Server:`, `Content-Type:` (che dice *che tipo di file* è: `text/html`, `application/msword`, `application/x-msdownload` per un eseguibile…).

> 💡 Esempi reali: nel 13/06 gran parte del traffico HTTP è **OCSP** (`POST` a `r3.o.lencr.org`, `ocsp.digicert.com`, con `Content-Type: application/ocsp-response`) più un `GET http://www.example.com/` che risponde `200 OK text/html` e un `GET /favicon.ico` che dà `404`. Nel 02/07 invece i `GET` verso `smart-fax.com` scaricano `/Documents/Invoice&MSO-Request.doc` (`application/msword`) e `/knr.exe` (`application/x-msdownload`, cioè un **eseguibile Windows**): il `Content-Type` è la prova che è stato scaricato un binario.

---

### **7. FTP — comandi, credenziali e file in chiaro**

L'FTP (vedi [M5/UD… e Reti M3/UD2/L1]) trasmette **tutto in chiaro** su due canali: **controllo** (porta 21, filtro `ftp`) e **dati** (porta 20 in modalità attiva, o porte alte in passiva, filtro `ftp-data`). Sul canale di controllo si leggono:

- I comandi del client: `USER`, `PASS`, `CWD`, `LIST`, `RETR` (download), `STOR` (upload), `TYPE`, `PASV`/`PORT`, `QUIT`.
- Le risposte numeriche del server: `220` (banner di benvenuto), `331` (serve la password), `230` (login riuscito), `530` (login fallito), `150`/`226` (apertura/completamento trasferimento dati).

Il filtro `ftp-data` isola invece i **file effettivamente trasferiti**, che si possono ricostruire con Follow Stream o estrarre (vedi L5).

> 💡 Esempio reale (21/02): sul canale di controllo si vede il client provare `USER admin` e `USER administrator` seguiti da centinaia di `PASS` diverse (`merlin`, `mercury`, `mickey`…), con il server che risponde sempre `530 Login incorrect`. Nessun `230` e nessun `RETR`/`STOR`: è un **brute-force a dizionario** fallito, senza alcun file scambiato. Il banner `220 FTP Service` è volutamente scarno e non rivela la versione del software.

> ✅ **Ricapitolando** — Ogni protocollo ha i suoi punti di lettura: ARP mappa la LAN e smaschera lo spoofing; DNS rivela le intenzioni (nomi cercati) anche prima della cifratura; TCP dà handshake, flag e sequenze; TLS lascia in chiaro l'SNI (il "dove") pur cifrando il "cosa"; HTTP espone richieste/risposte, URL e `Content-Type` (incluso il download di eseguibili); FTP mostra in chiaro credenziali, comandi ed eventuali file. Hardware e software dei nodi si deducono da OUI del MAC, `User-Agent` e banner/`Server:`.

Precedente: [[L3 - Statistiche, Conversations, Follow Stream ed Expert Info]] · Prossima: [[L5 - Estrazione di file, credenziali e individuazione di phishing]].
