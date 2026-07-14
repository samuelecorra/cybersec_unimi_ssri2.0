# 🐧 Cheat Sheet Enciclopedica — Configurare un firewall con iptables (Esame Laboratorio SSRI)

> 📌 **A cosa serve questo file.** È la guida operativa per l'esercizio in cui devi **scrivere comandi `iptables` reali** che realizzano una politica di sicurezza su una topologia Linux (host, router/firewall, LAN, DMZ, servizi). Copre, dalla A alla Z: la differenza tra Netfilter e `iptables`, il modello tabelle→chain→regole, **come scegliere la chain giusta** (la domanda decisiva), l'anatomia di un comando, la costruzione del match, i target, il filtraggio stateful, il NAT/port forwarding, i protocolli multi-connessione (FTP), le chain personalizzate, e — soprattutto — **come tradurre la tabella astratta a 8 colonne** dell'altro esercizio in comandi eseguibili (§13). In chiusura, trappole ricorrenti, checklist e riferimento rapido dei comandi. Se ti serve prima ragionare la policy in forma di tabella, parti dal file gemello [cheat_sheet_firewall_tabella.md](cheat_sheet_firewall_tabella.md). Riferimenti di teoria: [M5/UD2/L5 – Netfilter e IPTables](../../M5/UD2/L5%20-%20Netfilter%20e%20IPTables.md) e [L6 – Esercitazioni](../../M5/UD2/L6%20-%20Esercitazioni%20con%20IPTables.md).

---

### **1. Netfilter e iptables: chi fa cosa**

Due nomi per due cose diverse, da non confondere:

- **Netfilter** è il **componente del kernel Linux** che intercetta e manipola i pacchetti. È *lui* che filtra davvero.
- **`iptables`** è il **comando in user space** con cui **configuri** Netfilter (per IPv4). Per IPv6 esiste il gemello **`ip6tables`**, con la stessa sintassi.

Quando scrivi una regola `iptables`, non stai "programmando un firewall dentro iptables": stai **modificando la configurazione** che il kernel (Netfilter) applicherà ai pacchetti.

> ⚠️ **Dual-stack.** `iptables` filtra **solo IPv4**. Se la topologia ha anche IPv6 (indirizzi `fc00:*`, `2001:*`), devi replicare le regole con **`ip6tables`**, altrimenti l'IPv6 resta completamente aperto. È lo stesso avvertimento del cheat sheet firewall.

---

### **2. Il modello: tabelle → chain → regole**

Netfilter organizza il filtraggio in **tabelle**; ogni tabella contiene **chain** (liste ordinate di regole); ogni **regola** è fatta di **match** (quali pacchetti riconosco) + **target** (cosa faccio quando li riconosco).

> 📌 Ogni regola `iptables` risponde sempre a **due domande**: *"quali pacchetti sto riconoscendo?"* (il match) e *"cosa faccio quando li riconosco?"* (il target). Tieni separate le due cose mentre scrivi.

#### **2.1. Le tabelle**

| Tabella | A cosa serve | Quando la usi all'esame |
|---|---|---|
| **`filter`** | filtraggio ordinario (passa / non passa) | **quasi sempre**: è la tabella di default se non specifichi `-t` |
| **`nat`** | traduzione di indirizzi/porte (SNAT, DNAT, MASQUERADE) | quando la LAN naviga (masquerade) o esponi un servizio (port forwarding) |
| **`mangle`** | modifiche avanzate (TTL, ToS, marcature) | raramente in esame |
| **`raw`** | eccezioni al connection tracking (`NOTRACK`) | raramente in esame |

> 💡 Se non scrivi `-t <tabella>`, iptables assume `-t filter`. Il 90% dei comandi d'esame è sulla tabella `filter`; la `nat` compare solo per masquerade e port forwarding.

#### **2.2. Le chain (e la domanda decisiva)**

Le chain predefinite della tabella `filter`:

- **`INPUT`** — pacchetti **destinati alla macchina locale** (arrivano *a* questo host).
- **`OUTPUT`** — pacchetti **generati dalla macchina locale** (partono *da* questo host).
- **`FORWARD`** — pacchetti che la macchina **inoltra** da una rete all'altra (la **attraversano** soltanto, senza esserle destinati).

La tabella `nat` usa invece **`PREROUTING`** (prima del routing, per **DNAT**) e **`POSTROUTING`** (dopo il routing, per **SNAT/MASQUERADE**).

> 📌 **LA DOMANDA CHE RISOLVE TUTTO**: *"il pacchetto è **destinato** a questa macchina, è **generato** da questa macchina, o la **attraversa** soltanto?"*
> - destinato → **INPUT**
> - generato → **OUTPUT**
> - attraversa (routing tra reti) → **FORWARD**

> ⚠️ **Host vs router — l'errore più comune.** Se scrivi le regole **su un router/firewall** che sta in mezzo a due reti, il traffico da filtrare **attraversa** la macchina → chain **`FORWARD`**. Se le scrivi **su un server** che riceve le connessioni → chain **`INPUT`**. Una regola giusta nella chain sbagliata è **formalmente corretta ma totalmente inefficace**. Prima di scrivere qualsiasi regola, chiediti sempre: *su quale macchina sto configurando, e quel pacchetto la attraversa o le è destinato?*
> - Eccezione: anche su un router, il traffico diretto **al router stesso** (SSH di amministrazione, sua pagina di config) passa da `INPUT`, non `FORWARD`.

#### **2.3. Il flusso del pacchetto (dove finisce la regola)**

Semplificato (tabelle `nat` + `filter`):

1. Pacchetto in arrivo → `nat/PREROUTING` (eventuale **DNAT**) → **decisione di routing**.
2. Se **destinato al sistema locale** → `filter/INPUT` → processo locale.
3. Se **da inoltrare** (routing tra reti) → `filter/FORWARD` → `nat/POSTROUTING` (eventuale **SNAT/MASQUERADE**) → rete.
4. Pacchetto **generato localmente** → `filter/OUTPUT` → `nat/POSTROUTING` → rete.

> 💡 Ti serve ricordare l'essenziale: **DNAT si fa in PREROUTING** (cambio la destinazione *prima* di decidere dove instradare), **SNAT/MASQUERADE in POSTROUTING** (cambio la sorgente *dopo*, appena prima di uscire). Il filtraggio vero e proprio è in INPUT/OUTPUT/FORWARD.

---

### **3. Anatomia di un comando iptables**

La forma generale:

```bash
iptables -t <tabella> <operazione> <chain> <match...> -j <target>
```

Esempio commentato:

```bash
iptables -t filter -A FORWARD -p tcp --dport 80 -d 10.0.2.10 -j DROP
#          └tabella  └append  └chain └─── match ──────────────┘ └azione
```

- `-t filter` — tabella (omissibile: è il default).
- `-A FORWARD` — **append** (aggiunge in coda) alla chain FORWARD.
- `-p tcp --dport 80 -d 10.0.2.10` — il match: TCP verso la porta 80 dell'host 10.0.2.10.
- `-j DROP` — il target: scarta.

#### **3.1. Le operazioni sulle regole**

| Operazione | Comando | Effetto |
|---|---|---|
| **Append** (in coda) | `-A <chain>` | aggiunge la regola in fondo alla chain |
| **Insert** (in testa/posizione) | `-I <chain> [pos]` | inserisce in cima (o alla posizione `pos`) |
| **Delete** | `-D <chain> <num>` o `-D <chain> <regola>` | cancella per numero o per contenuto |
| **Policy di default** | `-P <chain> <target>` | azione se **nessuna** regola fa match |
| **Flush** (svuota) | `-F [chain]` | elimina tutte le regole della chain (o di tutte) |
| **New chain** | `-N <nome>` | crea una chain personalizzata |
| **List** | `-L` | elenca le regole |

> ⚠️ **L'ordine conta: `-A` vs `-I`.** Le regole si valutano dall'alto in basso, **prima che fa match vince**. `-A` mette in coda, `-I` mette in testa: due firewall con le **stesse** regole in **ordine diverso** possono comportarsi in modo opposto. Se una regola permissiva sta *prima* di una restrittiva, quest'ultima non verrà mai raggiunta.

#### **3.2. Visualizzazione e diagnostica**

```bash
iptables -L -v -n --line-numbers        # elenca: verbose, numerico, con numeri di riga
iptables -t nat -L -v -n                 # elenca la tabella nat
```

- `-v` verbose (conta pacchetti/byte per regola — utile per capire se una regola "scatta"),
- `-n` niente risoluzione DNS/nomi porte (più veloce e chiaro: vedi numeri),
- `--line-numbers` numera le regole (per poterle cancellare con `-D <chain> <num>`).

> 💡 `-n` è prezioso in debug: evita ritardi e ambiguità della risoluzione dei nomi, mostrandoti indirizzi e porte numerici così come li vede il kernel.

#### **3.3. Policy di default e flush (con cautela)**

```bash
iptables -P FORWARD DROP        # default deny sul forwarding (l'equivalente del "deny any" finale)
iptables -F                     # svuota tutte le regole
```

> ⚠️ **Flush e policy sono a doppio taglio.** `iptables -F` su una macchina con policy di default `DROP` può **tagliarti fuori** (blocchi anche la tua SSH). Su un firewall reale, imposta prima le regole che ti tengono dentro, poi la policy restrittiva. In esame (ambiente simulato tipo IMUNES) è meno rischioso, ma il ragionamento va capito.

---

### **4. Costruire il match (riconoscere i pacchetti giusti)**

I parametri di match più usati:

| Parametro | Significato | Esempio |
|---|---|---|
| `-s <ip>` | IP **sorgente** (host o sottorete `/24`) | `-s 10.0.2.0/24` |
| `-d <ip>` | IP **destinazione** | `-d 10.0.1.10` |
| `-p <proto>` | protocollo (`tcp`, `udp`, `icmp`) | `-p tcp` |
| `--sport <porta>` | porta **sorgente** (richiede `-p tcp/udp`) | `--sport 1024:` |
| `--dport <porta>` | porta **destinazione** | `--dport 443` |
| `-i <iface>` | interfaccia di **ingresso** (solo INPUT/FORWARD/PREROUTING) | `-i eth0` |
| `-o <iface>` | interfaccia di **uscita** (solo OUTPUT/FORWARD/POSTROUTING) | `-o wan0` |
| `!` | **negazione** del criterio che segue | `! --syn` |

> ⚠️ **`-p` prima delle opzioni di porta.** `--dport`/`--sport` sono estensioni **del protocollo**: `iptables` le accetta solo se hai già scritto `-p tcp` (o `-p udp`). Metti sempre `-p tcp` **prima** di `--dport`. Stessa cosa per `--tcp-flags` (richiede `-p tcp`) e `--icmp-type` (richiede `-p icmp`).

#### **4.1. Match avanzati con `-m`**

Il flag `-m` carica un **modulo** che aggiunge criteri:

```bash
-m multiport --dports 22,80,443      # più porte in una regola
-m state --state NEW,ESTABLISHED     # stato della connessione (vedi §6)
-m string --string "facebook" --algo kmp   # cerca una stringa nel payload
-m owner --uid-owner 1000            # processo di un dato utente locale (solo OUTPUT)
--tcp-flags SYN,ACK SYN,ACK          # combinazioni di flag TCP
--icmp-type 8                        # tipo ICMP (8 = echo request/ping)
```

- **`multiport`**: raggruppa porte (`22,80,443`) invece di scrivere tre regole.
- **`string`**: ispeziona il **payload** — funziona **solo su traffico in chiaro** (con HTTPS il contenuto è cifrato e la regola non vede nulla).
- **`--icmp-type`**: ICMP non ha porte, si filtra per tipo (8 = Echo Request/ping, 11 = Time Exceeded/traceroute).

---

### **5. I target (cosa fare del pacchetto)**

| Target | Effetto |
|---|---|
| **`ACCEPT`** | lascia passare il pacchetto |
| **`DROP`** | scarta **silenziosamente** (il mittente non riceve nulla, va in timeout) |
| **`REJECT`** | scarta **notificando** (`--reject-with tcp-reset` invia un RST; di default ICMP unreachable) |
| **`LOG`** | **registra** il pacchetto (non decide: la valutazione prosegue) — utile per debug |
| **`RETURN`** | torna alla chain chiamante (come il return da una funzione) |
| **`DNAT`** | riscrive la **destinazione** (port forwarding) — tabella `nat`, chain `PREROUTING` |
| **`SNAT`** | riscrive la **sorgente** — tabella `nat`, chain `POSTROUTING` |
| **`MASQUERADE`** | SNAT dinamico (per IP pubblico che può cambiare) |
| **`-j <chain>`** | salta a una chain personalizzata |

> 💡 **`DROP` vs `REJECT`.** `DROP` rende l'host "invisibile" (il mittente aspetta invano): utile verso l'esterno per non rivelare che il firewall c'è. `REJECT` chiude *educatamente* dicendo "no": più veloce per il client interno che così non aspetta il timeout. `LOG` non è un'azione finale: **non ferma** il pacchetto, quindi va messo **prima** della regola `DROP`/`REJECT` che vuoi documentare.

---

### **6. Il metodo passo-passo per un esercizio iptables**

Di fronte a "configura il firewall in modo che…", procedi così:

1. **Su quale macchina** stai configurando? Un **router/firewall** (traffico che attraversa → `FORWARD`) o un **server/host** (traffico destinato/generato → `INPUT`/`OUTPUT`)?
2. **Che direzione** ha il traffico da regolare? (chi è sorgente, chi è destinazione)
3. **Che protocollo e porte**? (studia il servizio: HTTP=TCP/80, SSH=TCP/22, DNS=UDP/53, FTP=TCP/21+dati…)
4. **Stateless o stateful**? Se basta bloccare/permettere per IP+porta, stateless; se devi gestire il **ritorno** senza aprire tutto, usa `-m state` (§6.1).
5. **Scrivi la regola** con la chain, il match e il target giusti; cura **l'ordine**.
6. **Chiudi** con la policy/regola di **default deny** se la traccia chiede "il resto vietato".

#### **6.1. Ricette pratiche (dagli esercizi del corso)**

**Bloccare una porta verso un host, sul router** (il pacchetto attraversa → FORWARD):

```bash
iptables -A FORWARD -p tcp --dport 8080 -d 10.0.2.10 -j DROP
```

**Bloccare una porta sull'host destinatario stesso** (il pacchetto è destinato a lui → INPUT):

```bash
iptables -I INPUT -p tcp --dport 8080 -j DROP
```

**Bloccare un servizio (web) da un host specifico verso un altro**:

```bash
iptables -A FORWARD -p tcp --dport 80 -s 10.0.2.30 -d 10.0.1.10 -j DROP
```

**Bloccare SSH da un host verso un altro**:

```bash
iptables -A FORWARD -p tcp --dport 22 -s 10.0.0.1 -d 10.0.2.10 -j DROP
```

**Bloccare un'intera rete `/24` verso un server** (una regola invece di tante host-per-host):

```bash
iptables -A FORWARD -p tcp --dport 80 -s 10.0.2.0/24 -d 10.0.1.10 -j DROP
```

> 💡 Nel `FORWARD`, indicare la **destinazione** (`-d`) è quasi sempre necessario: sul router passano tanti pacchetti, e senza `-d` bloccheresti quel servizio verso *chiunque*, non solo verso il server voluto.

---

### **7. Filtraggio stateful (la coppia magica)**

Il modulo `state` (o il più moderno `conntrack`) permette di distinguere i pacchetti per **stato della connessione**:

- **`NEW`** — pacchetto che **avvia** una nuova connessione (il SYN).
- **`ESTABLISHED`** — pacchetto di una connessione **già stabilita** (il traffico di ritorno e i dati).
- **`RELATED`** — pacchetto **correlato** a una connessione esistente (es. la connessione dati FTP rispetto a quella di controllo).

Lo schema classico — **policy chiusa + apri solo il NEW dei servizi voluti + lascia passare l'ESTABLISHED/RELATED**:

```bash
iptables -P INPUT DROP                                              # default deny
iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT    # le risposte passano sempre
iptables -A INPUT -p tcp --dport 22 -m state --state NEW -j ACCEPT  # nuove connessioni SSH
iptables -A INPUT -p tcp --dport 80 -m state --state NEW -j ACCEPT  # nuove connessioni HTTP
```

> 📌 **Perché è "magica".** Questa è la versione iptables del **flag ACK** dell'esercizio a tabella: invece di scrivere a mano la riga di ritorno con `ACK=1` per ogni servizio, **una sola riga** `ESTABLISHED,RELATED -j ACCEPT` lascia passare *tutte* le risposte alle connessioni che hai aperto. È esattamente ciò che un firewall stateful automatizza e che il packet filter stateless doveva simulare con l'ACK. Con lo stateful **non ti servono più le righe di ritorno per ogni servizio**: le copre quella riga.

---

### **8. NAT: masquerade e port forwarding**

#### **8.1. SNAT / MASQUERADE — la LAN naviga dietro un IP pubblico**

Gli host interni con IP privati escono su Internet mascherati dietro l'unico IP pubblico del firewall:

```bash
iptables -t nat -A POSTROUTING -o wan0 -s 10.0.0.0/24 -j MASQUERADE
```

`POSTROUTING` perché la sorgente si riscrive **dopo** la decisione di routing, appena prima di uscire da `wan0`. `MASQUERADE` (invece di `SNAT --to <ip>`) quando l'IP pubblico può cambiare (dinamico).

#### **8.2. DNAT / port forwarding — esporre un servizio interno**

"Il server di backup è esposto a Internet sulla porta 5000" → le connessioni che arrivano sull'IP pubblico del firewall alla 5000 vengono rigirate al server interno:

```bash
iptables -t nat -A PREROUTING -i wan0 -p tcp --dport 5000 -j DNAT --to-destination 10.0.4.15:5000
iptables -A FORWARD -p tcp -d 10.0.4.15 --dport 5000 -m state --state NEW,ESTABLISHED -j ACCEPT
```

`PREROUTING` perché la destinazione si riscrive **prima** di decidere l'instradamento. **Attenzione**: il DNAT **non basta**: devi anche **permettere** il traffico risultante in `FORWARD` (seconda riga), altrimenti il pacchetto viene tradotto ma poi bloccato dal filtro.

> 💡 Questo è il "dietro le quinte" della riga «backup esposto su 5000» della tabella astratta: nella tabella scrivi una richiesta in ingresso verso `10.0.4.15:5000`; in iptables quella riga si realizza con **DNAT in PREROUTING + ACCEPT in FORWARD**.

---

### **9. FTP stateful e ispezione del payload**

#### **9.1. FTP — il caso multi-connessione**

FTP usa la connessione di **controllo** (TCP/21) e una di **dati** (attiva dalla porta 20 del server, o passiva verso una porta alta). La chiave stateful è riconoscere che la connessione dati è **`RELATED`** rispetto a quella di controllo:

```bash
# controllo
iptables -A OUTPUT -p tcp --dport 21 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A INPUT  -p tcp --sport 21 -m state --state ESTABLISHED     -j ACCEPT
# dati in modalità attiva (server:20 → client): correlata alla connessione di controllo
iptables -A INPUT  -p tcp --sport 20 -m state --state RELATED,ESTABLISHED -j ACCEPT
iptables -A OUTPUT -p tcp --sport 1024: --dport 20 -m state --state ESTABLISHED -j ACCEPT
```

> 💡 Nella pratica il kernel ha un helper dedicato (`nf_conntrack_ftp`) che **legge** i comandi `PORT`/`PASV` nel dialogo FTP e apre al volo la porta dati marcandola `RELATED`. È il motivo per cui, con lo stateful, non devi aprire a mano un intero range di porte alte.

#### **9.2. Match `string` (solo traffico in chiaro)**

Bloccare richieste HTTP che contengono una parola:

```bash
iptables -A OUTPUT -p tcp --dport 80 -m string --string "facebook" --algo kmp -j DROP
```

> ⚠️ Funziona **solo** se il contenuto è visibile. Con **HTTPS** il payload è cifrato: una regola `string` su HTTP non vede né URL né contenuto. È lo stesso limite del deep packet inspection su traffico cifrato.

#### **9.3. Logging**

```bash
iptables -A FORWARD -j LOG --log-prefix "FORWARD-DROP: " --log-level 4
iptables -A FORWARD -j DROP
```

Il `LOG` va **prima** del `DROP` (LOG non ferma il pacchetto). Il `--log-prefix` ti fa riconoscere nel syslog quale regola ha prodotto la riga.

---

### **10. Chain personalizzate (per configurazioni ordinate)**

Con molte regole, si creano **chain definite dall'utente** per separare i controlli logicamente. Schema tipico visto a lezione:

```bash
# chain per i pacchetti TCP anomali
iptables -N bad_tcp_packets
iptables -A bad_tcp_packets -p tcp ! --syn -m state --state NEW -j DROP           # NEW senza SYN = sospetto
iptables -A bad_tcp_packets -p tcp --tcp-flags SYN,ACK SYN,ACK -m state --state NEW -j REJECT --reject-with tcp-reset

# chain che incapsula la logica delle connessioni TCP ammesse
iptables -N allowed
iptables -A allowed -p tcp --syn -j ACCEPT
iptables -A allowed -p tcp -m state --state ESTABLISHED,RELATED -j ACCEPT
iptables -A allowed -p tcp -j DROP

# chain per protocollo, che inoltra ad "allowed" solo le porte aperte
iptables -N tcp_packets
iptables -A tcp_packets -p tcp --dport 22 -j allowed
iptables -A tcp_packets -p tcp --dport 80 -j allowed

# collegamento dalle chain principali (senza jump, le chain custom non filtrano nulla!)
iptables -A INPUT -m state --state ESTABLISHED,RELATED -j ACCEPT
iptables -A INPUT -p tcp -j bad_tcp_packets
iptables -A INPUT -p tcp -i eth0 -j tcp_packets
```

> ⚠️ **Una chain personalizzata inutilizzata non filtra nulla.** Crearla (`-N`) non basta: devi **saltarci dentro** con `-j <nome>` da una chain realmente attraversata dal traffico (INPUT/FORWARD/OUTPUT). Le chain custom non cambiano la semantica di Netfilter: **organizzano** solo le decisioni per renderle leggibili.

---

### **11. Ambiente di laboratorio (IMUNES)**

Gli esercizi del corso si fanno con **IMUNES** (simulatore di rete su container Docker): disegni la topologia, apri una shell su ogni nodo, installi strumenti (`netcat`, `curl`, `lighttpd`) e configuri `iptables` su host o router. Test tipici:

```bash
# su host 2: metti un servizio in ascolto
nc -l -p 8080
# su host 1: prova a connetterti (prima e dopo la regola firewall)
nc <ip_host2> 8080
curl <ip_host2>          # per un web server
```

> ⚠️ **I container non sono persistenti.** Se rilanci la simulazione riparti dall'immagine originale: salva la simulazione e le configurazioni, e ricorda che le regole `iptables` vanno riscritte se l'ambiente viene ricreato.

---

### **12. Le trappole ricorrenti (checklist degli errori)**

> ⚠️ **Gli errori che costano punti:**
> 1. **Chain sbagliata**: regola in `INPUT` su un router che invece inoltra (serve `FORWARD`), o viceversa. → Chiediti sempre: destinato / generato / attraversa?
> 2. **`--dport` senza `-p tcp`**: iptables rifiuta l'opzione. Metti `-p tcp` prima.
> 3. **Ordine delle regole**: una `ACCEPT` larga prima di una `DROP` mirata rende quest'ultima inutile. Cura `-A` vs `-I`.
> 4. **DNAT senza ACCEPT in FORWARD**: il pacchetto viene tradotto ma poi bloccato dal filtro.
> 5. **Manca il ritorno** (in configurazione stateless): senza la riga `ESTABLISHED,RELATED` o senza la regola di risposta, la connessione va solo in un verso.
> 6. **`LOG` dopo `DROP`**: non logga nulla (DROP ferma il pacchetto prima). Il LOG va prima.
> 7. **IPv6 dimenticato**: `iptables` non tocca l'IPv6, serve `ip6tables`.
> 8. **Chain custom creata ma non richiamata** con `-j`.
> 9. **Default policy non impostata**: se la traccia dice "il resto vietato", serve `-P <chain> DROP` (o una regola `DROP` finale).

---

### **13. Il ponte: dalla tabella a 8 colonne ai comandi iptables**

Se hai già compilato la **tabella astratta** dell'altro esercizio (vedi [cheat_sheet_firewall_tabella.md](cheat_sheet_firewall_tabella.md)), tradurla in `iptables` è meccanico. Corrispondenze:

| Colonna della tabella | In iptables |
|---|---|
| **Direzione** | scelta della **chain** (`FORWARD` sul router; `INPUT`/`OUTPUT` sul server) + `-i`/`-o` per l'interfaccia |
| **IP Sorgente** | `-s <ip/sottorete>` |
| **IP Destinazione** | `-d <ip/sottorete>` |
| **Protocollo** | `-p tcp` / `-p udp` / `-p icmp` |
| **Porta Sorgente** | `--sport <porta>` (`1024:` per porte alte) |
| **Porta Destinazione** | `--dport <porta>` |
| **Flag ACK** | **non si scrive a mano**: si usa `-m state --state` (`NEW` = ACK 0; `ESTABLISHED,RELATED` = traffico di ritorno = ACK 1) |
| **Azione** | `-j ACCEPT` / `-j DROP` (o `REJECT`) |

**Esempio di traduzione.** La coppia di righe della tabella per "web server `10.0.4.11` esposto in HTTPS":

| Direz. | IP Sorg | IP Dest | Protoc. | P.Sorg | P.Dest | ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN | any | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| OUT | 10.0.4.11 | any | TCP | 443 | >1023 | 1 | permit |

diventa, sul firewall che fa da router, in forma **stateful** (una riga per il NEW + la riga universale del ritorno):

```bash
iptables -A FORWARD -p tcp -d 10.0.4.11 --dport 443 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A FORWARD -m state --state ESTABLISHED,RELATED -j ACCEPT   # copre TUTTI i ritorni
iptables -P FORWARD DROP                                             # il "deny any" finale
```

oppure, se la traccia vuole l'equivalente **stateless** riga-per-riga (mappando l'ACK sui flag TCP):

```bash
iptables -A FORWARD -p tcp -d 10.0.4.11 --dport 443 -j ACCEPT                       # richiesta
iptables -A FORWARD -p tcp -s 10.0.4.11 --sport 443 ! --syn -j ACCEPT               # risposta (ACK=1 ⇒ non-SYN)
iptables -A FORWARD -j DROP
```

> 💡 `! --syn` sui pacchetti di ritorno è l'equivalente iptables di `ACK=1`: un segmento che **non** è un SYN puro appartiene a una connessione già avviata. In pratica quasi sempre conviene la forma **stateful** (`ESTABLISHED,RELATED`), più semplice e robusta: una sola riga copre i ritorni di tutti i servizi.

---

### **14. Checklist finale (prima di consegnare)**

> 📌 **Verifica una per una:**
> 1. Hai identificato **su quale macchina** configuri e quindi la **chain** giusta (FORWARD sul router, INPUT/OUTPUT sul server)?
> 2. Ogni regola ha `-p tcp/udp` **prima** delle opzioni di porta?
> 3. L'**ordine** delle regole è corretto (permit specifici prima dei deny larghi; ESTABLISHED in alto)?
> 4. Il **traffico di ritorno** è gestito (riga `ESTABLISHED,RELATED` o regole di risposta)?
> 5. Se esponi un servizio su porta pubblica, hai **DNAT in PREROUTING + ACCEPT in FORWARD**?
> 6. Se la LAN naviga, hai il **MASQUERADE in POSTROUTING**?
> 7. Per **FTP** hai gestito il `RELATED` della connessione dati?
> 8. I `LOG` sono **prima** dei `DROP`?
> 9. Le **chain personalizzate** sono richiamate con `-j`?
> 10. Se dual-stack, hai replicato con **`ip6tables`**?
> 11. Hai impostato la **policy di default `DROP`** (o la regola deny finale) se serve "il resto vietato"?

---

### **15. Riferimento rapido dei comandi**

| Scopo | Comando |
|---|---|
| Elenca regole (leggibile) | `iptables -L -v -n --line-numbers` |
| Elenca la tabella nat | `iptables -t nat -L -v -n` |
| Aggiungi in coda | `iptables -A <chain> <match> -j <target>` |
| Inserisci in testa | `iptables -I <chain> 1 <match> -j <target>` |
| Cancella per numero | `iptables -D <chain> <num>` |
| Policy di default | `iptables -P <chain> DROP` |
| Svuota una chain | `iptables -F <chain>` |
| Crea chain custom | `iptables -N <nome>` / salta con `-j <nome>` |
| Permetti porta TCP | `iptables -A INPUT -p tcp --dport 22 -j ACCEPT` |
| Più porte insieme | `iptables -A INPUT -p tcp -m multiport --dports 22,80,443 -j ACCEPT` |
| Blocca host→host su una porta | `iptables -A FORWARD -p tcp -s <src> -d <dst> --dport 80 -j DROP` |
| Blocca una `/24` | `iptables -A FORWARD -p tcp -s 10.0.2.0/24 -d <dst> --dport 80 -j DROP` |
| Stateful: lascia i ritorni | `iptables -A <chain> -m state --state ESTABLISHED,RELATED -j ACCEPT` |
| Stateful: apri un servizio | `iptables -A <chain> -p tcp --dport N -m state --state NEW -j ACCEPT` |
| Masquerade LAN | `iptables -t nat -A POSTROUTING -o wan0 -s 10.0.0.0/24 -j MASQUERADE` |
| Port forwarding (DNAT) | `iptables -t nat -A PREROUTING -i wan0 -p tcp --dport 5000 -j DNAT --to-destination 10.0.4.15:5000` |
| ICMP/ping | `iptables -A INPUT -p icmp --icmp-type 8 -j ACCEPT` |
| Log prima di droppare | `iptables -A <chain> -j LOG --log-prefix "DROP: "` poi `-j DROP` |

> ✅ **In sintesi.** `iptables` configura Netfilter tramite **tabelle → chain → regole (match + target)**. La decisione più importante è la **chain**: destinato → INPUT, generato → OUTPUT, attraversa → FORWARD (i router lavorano quasi sempre in FORWARD). Il match riconosce i pacchetti (`-s -d -p --dport -i -o`, più `-m` per moduli), il target decide (`ACCEPT`/`DROP`/`REJECT`/`DNAT`/`SNAT`…). Lo **stateful** (`-m state`) sostituisce le righe di ritorno del packet filter: una sola `ESTABLISHED,RELATED -j ACCEPT` copre tutte le risposte. Il **NAT** vive nella tabella `nat` (DNAT in PREROUTING, SNAT/MASQUERADE in POSTROUTING). Cura sempre **ordine delle regole**, `-p` prima delle porte, ritorni gestiti, IPv6 con `ip6tables`, e default deny finale. Con il §13 traduci qualunque tabella astratta in comandi eseguibili.

---

> **Riferimenti**: [M5/UD2/L5 – Netfilter e IPTables](../../M5/UD2/L5%20-%20Netfilter%20e%20IPTables.md), [M5/UD2/L6 – Esercitazioni con IPTables](../../M5/UD2/L6%20-%20Esercitazioni%20con%20IPTables.md); firewall stateful e NGFW: [M5/UD2/L2](../../M5/UD2/L2%20-%20New%20Generation%20Packet%20Filtering.md). Metodo a tabella (astratto): [cheat_sheet_firewall_tabella.md](cheat_sheet_firewall_tabella.md). NAT/dual-stack lato esame: [M7/UD2/L4](../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L4%20-%20NAT,%20bridge,%20port%20forwarding%20e%20dual-stack%20IPv4-IPv6.md). Casi risolti: [21/02](lab_21_febbraio_2025/Soluzione_Lab_21_02_2025.md), [13/06](lab_13_giugno_2025/Soluzione_Lab_13_06_2025.md), [02/07 ≡ 12/09](lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md).
