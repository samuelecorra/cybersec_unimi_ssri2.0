## **Lezione 6: Cheat-sheet operativa d'esame**

Questa lezione è un **prontuario** da tenere davanti durante il laboratorio (che è open book). Non introduce concetti nuovi: condensa filtri, menu e procedure delle lezioni precedenti in forma immediatamente usabile, nell'ordine in cui servono.

### **1. Procedura standard in 6 mosse**

1. **Apri** la traccia. `Statistics → Protocol Hierarchy` per capire di cosa parla (FTP? TLS+DNS? HTTP?).
2. `Statistics → Conversations` (scheda TCP/IPv4) ordinata per pacchetti: individua l'host protagonista e con chi parla. Scheda **Ethernet** per i MAC/produttori.
3. **Prima connessione TCP**: filtro `tcp.stream eq 0`, leggi il **SYN** → client, server, porte; espandi *Ethernet II* → MAC.
4. **HW/SW dei nodi**: OUI del MAC (hardware/VM), `http.user_agent` (OS+browser del client), banner/`http.server` (software server).
5. **Isola la sessione** richiesta (`ftp`, `dns`, `http`, `tls`, o `tcp.stream eq N`) e fai **Follow Stream** per leggere il dialogo.
6. **Estrai gli artefatti**: `File → Export Objects → HTTP` per i file, `Tools → Credentials` per le password, DNS/`Host:` per i domini.

---

### **2. Filtri di visualizzazione più usati**

```text
# --- isolare connessioni ---
tcp.stream eq 0                     # prima connessione TCP
udp.stream eq 0                     # prima "sessione" UDP
ip.addr == 10.0.0.5                 # tutto il traffico di un host (src o dst)
ip.src == 10.0.0.5                  # solo in uscita da quell'host
eth.addr == 00:0c:29:87:4b:76       # per indirizzo MAC

# --- per protocollo ---
ftp                                 # comandi FTP (porta 21)
ftp-data                            # file trasferiti via FTP
ftp.request.command == "PASS"       # solo le password FTP
dns                                 # query/risposte DNS
dns.qry.name contains "fax"         # query verso un nome sospetto
http.request                        # richieste HTTP
http.request.method == "POST"       # invio dati/credenziali
http.response.code == 404           # risposte con un dato status
tls.handshake.type == 1             # Client Hello (contiene l'SNI)
tls.handshake.extensions_server_name # filtra chi ha un SNI

# --- flag TCP / anomalie ---
tcp.flags.syn == 1 && tcp.flags.ack == 0   # aperture (SYN puri) → scan/inizio
tcp.flags.reset == 1                       # reset (RST) → chiusure brusche/attacchi
tcp.analysis.retransmission                # ritrasmissioni
arp.duplicate-address-detected             # ARP spoofing
```

---

### **3. Menu e viste chiave**

| Serve a… | Percorso |
|---|---|
| Capire i protocolli presenti | `Statistics → Protocol Hierarchy` |
| Chi parla con chi + MAC/vendor | `Statistics → Conversations` |
| Totali per singolo host | `Statistics → Endpoints` |
| Elenco domini DNS | `Statistics → DNS` |
| Elenco URL richiesti | `Statistics → HTTP → Requests` |
| Ricostruire un dialogo | tasto destro → `Follow → TCP/HTTP Stream` |
| Salvare i file trasferiti | `File → Export Objects → HTTP / FTP-DATA` |
| Estrarre credenziali | `Tools → Credentials` |
| Anomalie automatiche | `Analyze → Expert Information` |
| Diagramma dei messaggi | `Statistics → Flow Graph` |
| Costruire un filtro senza saperlo | tasto destro su un campo → `Apply as Filter → Selected` |

---

### **4. Numeri e sigle da riconoscere al volo**

**Porte:** 20/21 FTP (dati/controllo), 22 SSH, 23 Telnet, 25 SMTP, 53 DNS, 80 HTTP, 110 POP3, 111 RPC/portmapper, 143 IMAP, 443 HTTPS/TLS, 445 SMB, 5432 PostgreSQL, 3389 RDP.

**Codici FTP:** `220` benvenuto, `331` serve password, `230` login OK, `530` login fallito, `150` apertura dati, `226` trasferimento completato.

**Codici HTTP:** `200` OK, `301/302` redirect, `304` non modificato, `401` autenticazione richiesta, `403` vietato, `404` non trovato, `500` errore server.

**Magic bytes file:** `MZ` eseguibile Windows, `PK` ZIP/docx, `%PDF` PDF, `D0CF11E0` Office legacy, `FFD8FF` JPEG, `.PNG` PNG.

**OUI ricorrenti:** `00:0c:29` / `00:50:56` VMware, `00:01:96` Cisco, `00:08:02` HP, `20:e5:2a` ASUSTek, `00:d0:59` Ambit.

---

### **5. Come impostare una risposta "da 30 e lode"**

Ogni risposta del laboratorio dovrebbe: (1) **dire il fatto** (es. "il client è 10.6.27.102, il server 23.63.254.163"); (2) **indicare la prova** ("SYN al pacchetto n. …, porta 80"); (3) **interpretare** ("si tratta del controllo di connettività di Windows, traffico legittimo"). Non limitarti a elencare dati: collega ciò che vedi al *significato* di sicurezza (è un attacco? è benigno? quale fase?).

> ⚠️ Errori che costano punti: confondere numero di pacchetto e tempo; scambiare capture filter e display filter; dire "è cifrato quindi non si vede nulla" su TLS senza notare che l'**SNI** è in chiaro; dare per malevolo il traffico di sistema (NCSI, OCSP, Mozilla); dimenticare di verificare se un login/brute-force è andato **a buon fine** (`230`) o è **fallito** (`530`).

> ✅ **Ricapitolando** — Procedura fissa in 6 mosse (panoramica → conversazioni → prima connessione → HW/SW → follow stream → estrazione), una tavolozza di display filter pronti, la mappa dei menu di *Statistics*/*Follow*/*Export*, e i numeri chiave (porte, codici FTP/HTTP, magic bytes, OUI). Rispondi sempre con fatto + prova + interpretazione, distinguendo il traffico legittimo dagli indicatori d'attacco.

Precedente: [[L5 - Estrazione di file, credenziali e individuazione di phishing]] · Unità successiva: [[L1 - Metodologia della tabella di filtraggio (le 8 colonne e il flag ACK)]] (UD2).
