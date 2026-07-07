## **Lezione 3: Servizi multi-connessione (FTP attivo/passivo, RPC, SMTP)**

Alcuni protocolli non usano una sola connessione o una sola porta, e sono proprio quelli che complicano — e quindi vengono chiesti — negli esercizi firewall. Qui si vede come modellarli nella tabella.

### **1. FTP: due connessioni, due modalità**

L'FTP usa **due connessioni TCP separate** (vedi Reti, M3/UD2/L1):

- **Connessione di controllo**: sempre aperta **dal client** verso la **porta 21** del server; vi passano i comandi (`USER`, `RETR`, `PORT`/`PASV`…).
- **Connessione dati**: trasporta i file, e **chi la apre dipende dalla modalità**.

Questa dualità è la ragione per cui l'FTP è ostico per i firewall stateless.

#### **1.1. FTP attivo**

In modalità **attiva**, la connessione dati viene aperta **dal server**, dalla propria **porta 20** verso una porta effimera del client (comunicata dal client con il comando `PORT`). È l'opposto del solito: **è il server a iniziare** la seconda connessione, in ingresso verso il client.

Per un web server che offre FTP attivo accessibile dall'esterno (`10.0.1.10`, client esterni):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN (controllo, richiesta) | any | 10.0.1.10 | TCP | >1023 | 21 | 0/any | permit |
| OUT (controllo, risposta) | 10.0.1.10 | any | TCP | 21 | >1023 | 1 | permit |
| OUT (dati, **aperta dal server**) | 10.0.1.10 | any | TCP | 20 | >1023 | 0/any | permit |
| IN (dati, risposta) | any | 10.0.1.10 | TCP | 20 | >1023 | 1 | permit |

> ⚠️ Nella riga della **connessione dati attiva** il pacchetto di apertura ha **ACK=0/any e parte dalla porta 20 del server**: è il server a bussare al client. Questo è il punto che quasi tutti sbagliano. È anche il motivo per cui l'FTP attivo è considerato "firewall-hostile": costringe ad autorizzare una connessione iniziata dal server verso l'esterno/il client.

#### **1.2. FTP passivo**

In modalità **passiva** (comando `PASV`), è di nuovo **il client** ad aprire anche la connessione dati, verso una **porta alta** che il server comunica nella risposta. Tutte e due le connessioni partono quindi dal client:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN (controllo, richiesta) | any | 10.0.1.10 | TCP | >1023 | 21 | 0/any | permit |
| OUT (controllo, risposta) | 10.0.1.10 | any | TCP | 21 | >1023 | 1 | permit |
| IN (dati passivi, richiesta) | any | 10.0.1.10 | TCP | >1023 | >1023 | 0/any | permit |
| OUT (dati passivi, risposta) | 10.0.1.10 | any | TCP | >1023 | >1023 | 1 | permit |

> 💡 Il passivo è più "amico" dei firewall **lato client** (il client apre tutto), ma richiede di aprire un **intervallo di porte alte** sul lato server, il che è impreciso in un filtro statico. I firewall reali risolvono il problema con moduli *stateful* di connection tracking (`nf_conntrack_ftp`, vedi [M5/UD2/L6](../../M5/UD2/L6%20-%20Esercitazioni%20con%20IPTables.md) §9) che leggono i comandi `PORT`/`PASV` e aprono al volo la porta dati come `RELATED`. Nell'esercizio d'esame ci si limita a descrivere le porte come sopra.

---

### **2. RPC (portmapper, porta 111)**

I servizi **RPC** (Remote Procedure Call, es. NFS, servizi Windows) usano il **portmapper/rpcbind** sulla **porta 111** (TCP e UDP) per pubblicare i servizi, che poi girano su **porte dinamiche alte** negoziate a runtime. Per una regola d'esame del tipo "*solo la LAN può accedere ai servizi RPC nella DMZ*", si autorizza la LAN verso la porta 111 del server (spesso sia TCP sia UDP):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| LAN→DMZ | 10.0.2.0/24 | 10.0.1.10 | TCP | >1023 | 111 | 0/any | permit |
| DMZ→LAN | 10.0.1.10 | 10.0.2.0/24 | TCP | 111 | >1023 | 1 | permit |

> ⚠️ Come l'FTP, anche RPC negozia **porte dinamiche** oltre alla 111: un filtro statico rigoroso non basterebbe a coprire tutto il dialogo, e nella realtà servono helper stateful. All'esame si modella l'accesso alla porta 111 restringendo l'IP sorgente alla sola LAN, che è ciò che la traccia chiede ("solo la LAN").

---

### **3. SMTP (posta, porta 25)**

Un server di posta "che funziona correttamente secondo SMTP" deve poter **ricevere** posta (altri server e/o client aprono connessioni verso la sua porta **25**) e **inviare** posta (lui apre connessioni verso la porta 25 di altri server). Sono quindi **due flussi con iniziativa opposta**, entrambi sulla porta 25:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN (ricezione, richiesta) | any | mailserver | TCP | >1023 | 25 | 0/any | permit |
| OUT (ricezione, risposta) | mailserver | any | TCP | 25 | >1023 | 1 | permit |
| OUT (invio, richiesta) | mailserver | any | TCP | >1023 | 25 | 0/any | permit |
| IN (invio, risposta) | any | mailserver | TCP | 25 | >1023 | 1 | permit |

> 💡 "Funzionare secondo SMTP" significa proprio questo doppio verso: la posta in *entrata* arriva sulla 25 del server (iniziativa esterna), la posta in *uscita* parte dal server verso la 25 dei destinatari (iniziativa del server). Dimenticare uno dei due versi lascia la mail monodirezionale.

---

### **4. Regola pratica per i servizi multi-connessione**

Di fronte a un protocollo "strano", poniti sempre tre domande: **quante connessioni** usa? **chi apre** ciascuna? **su quali porte**? Traducendo poi ogni connessione nelle solite due righe (richiesta ACK 0/any nel verso di apertura, risposta ACK=1 nel verso opposto). L'unica vera insidia è ricordare che in **FTP attivo** e in parte in **RPC** l'iniziativa di una connessione è **del server**, quindi la sua riga di richiesta va nel verso "insolito".

> ✅ **Ricapitolando** — FTP usa controllo (client→server:21) e dati: in **attivo** i dati partono dal **server:20** verso il client (connessione in ingresso al client, ACK 0/any — il caso ostico), in **passivo** anche i dati partono dal client verso una porta alta del server. RPC pubblica sulla 111 (TCP/UDP) e usa porte dinamiche; SMTP richiede **entrambi i versi** sulla porta 25 (ricezione con iniziativa esterna, invio con iniziativa del server). Per ogni protocollo: conta le connessioni, individua chi apre ciascuna, assegna le porte, poi scrivi le due righe con l'ACK giusto.

Precedente: [[L2 - DMZ ed esposizione di servizi verso Internet]] · Prossima: [[L4 - NAT, bridge, port forwarding e dual-stack IPv4-IPv6]].
