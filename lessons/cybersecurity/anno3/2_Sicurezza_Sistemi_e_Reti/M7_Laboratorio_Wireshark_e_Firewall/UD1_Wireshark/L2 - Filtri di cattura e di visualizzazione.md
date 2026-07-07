## **Lezione 2: Filtri di cattura e di visualizzazione**

### **1. Due filtri diversi, spesso confusi**

Wireshark ha **due sistemi di filtraggio** che rispondono a due domande diverse e usano **sintassi diverse**:

- **Capture filter** (filtro di cattura): decide *quali pacchetti registrare*. Si applica **prima** della cattura, usa la sintassi **BPF** (Berkeley Packet Filter, la stessa di `tcpdump`). I pacchetti scartati non finiscono nel file: la selezione è irreversibile.
- **Display filter** (filtro di visualizzazione): decide *quali pacchetti mostrare* fra quelli già catturati. Si applica **dopo**, usa la **sintassi propria di Wireshark**. Non cancella nulla: nasconde e mostra a piacere.

> ⚠️ All'esame la traccia è già stata catturata: quello che userai **quasi sempre** è il **display filter** (la barra in alto, che diventa verde se la sintassi è valida, rossa se è errata). Il capture filter serve solo quando si registra dal vivo. Confonderne le sintassi è l'errore più comune: `tcp port 80` è un *capture* filter, `tcp.port == 80` è un *display* filter.

---

### **2. Sintassi dei display filter**

Un display filter è un'espressione booleana su **campi di protocollo**. Le regole essenziali:

- Nominare un protocollo da solo mostra tutti i suoi pacchetti: `http`, `dns`, `ftp`, `tls`, `arp`, `icmp`.
- Confrontare un campo con un valore: operatori `==`, `!=`, `>`, `<`, `>=`, `<=` (esistono anche le forme testuali `eq`, `ne`, `gt`…).
- Combinare condizioni: `and` (`&&`), `or` (`||`), `not` (`!`).
- I campi seguono la notazione `protocollo.campo`: `ip.addr`, `tcp.port`, `tcp.flags.syn`, `http.request.method`, `dns.qry.name`.

Esempi di uso immediato:

```text
ip.addr == 10.0.2.15                 # tutto il traffico da/verso quell'host
tcp.port == 21                       # sessione FTP di controllo (mittente O destinatario 21)
ip.src == 10.0.0.5 && tcp.dstport == 80   # richieste web da quell'host
http.request.method == "POST"        # solo le POST (invio dati/credenziali)
dns.qry.name contains "bank"         # query DNS il cui nome contiene "bank"
tcp.flags.syn == 1 && tcp.flags.ack == 0  # solo i SYN "puri" (aperture di connessione)
tcp.analysis.retransmission          # ritrasmissioni segnalate da Wireshark
```

> 💡 Differenza sottile ma frequente all'esame: `ip.addr == X` è vera se **sorgente o destinazione** è X (comodo per isolare tutto il traffico di un host); `ip.src == X` e `ip.dst == X` fissano la direzione. Analogamente `tcp.port == 80` vale per porta sorgente o destinazione, mentre `tcp.dstport == 80` distingue le richieste dalle risposte.

---

### **3. Il filtro più potente: `tcp.stream`**

Wireshark assegna a ogni connessione TCP un **numero di stream** progressivo (`tcp.stream`), a partire da 0. Filtrare su di esso isola **un'unica conversazione**, ripulendo lo schermo da tutto il resto:

```text
tcp.stream eq 0        # la primissima connessione TCP della cattura
tcp.stream eq 3        # la quarta connessione TCP
```

Questo è il modo più rapido per rispondere a "*quali nodi sono coinvolti nella prima connessione TCP?*": si applica `tcp.stream eq 0`, si guarda il primo pacchetto (il SYN) e si leggono direttamente IP e porte di client e server. L'analogo per UDP è `udp.stream`.

> 📌 Regola operativa d'esame: **"prima connessione TCP" = `tcp.stream eq 0`**, e il suo primo pacchetto è il SYN inviato dal client. Da lì ricavi in un secondo client, server, porte e — espandendo Ethernet — i MAC.

---

### **4. Filtri per protocollo, pronti all'uso**

Una piccola tavolozza di filtri copre la quasi totalità dei quesiti d'esame:

| Obiettivo | Display filter |
|---|---|
| Sessioni FTP (comandi) | `ftp` |
| Dati FTP (file trasferiti) | `ftp-data` |
| Query e risposte DNS | `dns` |
| Handshake e traffico TLS | `tls` (storicamente `ssl`) |
| Richieste HTTP | `http.request` |
| Risposte HTTP | `http.response` |
| Aperture di connessione | `tcp.flags.syn == 1` |
| Reset di connessione | `tcp.flags.reset == 1` |
| Traffico ARP | `arp` |
| Un host specifico | `ip.addr == <IP>` |
| Un MAC specifico | `eth.addr == aa:bb:cc:dd:ee:ff` |

> 💡 Per costruire un filtro senza ricordare il nome esatto del campo: clicca il campo desiderato nel *Packet Details*, tasto destro → **Apply as Filter → Selected**. Wireshark scrive il filtro corretto da solo. È il trucco che fa risparmiare più tempo in assoluto.

---

### **5. Capture filter (BPF): per completezza**

Quando invece si cattura dal vivo, il capture filter riduce il rumore fin dall'inizio. La sintassi BPF usa parole chiave diverse:

```text
host 10.0.0.5            # solo traffico da/verso quell'host
net 192.168.1.0/24       # un'intera sottorete
port 53                  # solo porta 53 (DNS)
tcp port 80              # solo TCP porta 80
src host 10.0.0.5 and dst port 443
```

> ⚠️ Non mischiare le due sintassi: scrivere `ip.addr == 10.0.0.5` nel campo *capture filter* dà errore, così come `host 10.0.0.5` nel *display filter*. La regola mnemonica: **BPF usa spazi e parole (`tcp port 80`), il display filter usa punti e operatori (`tcp.port == 80`)**.

---

### **6. Salvare ed esportare un sottoinsieme**

Dopo aver applicato un display filter si può salvare solo ciò che è visibile con **File → Export Specified Packets**, scegliendo "Displayed". È utile per isolare una singola sessione in un file più piccolo, o per allegare a una relazione solo i pacchetti rilevanti. Analogamente **Edit → Find Packet** cerca una stringa (es. una password, un nome file) nel payload, nei dettagli o nei byte.

> ✅ **Ricapitolando** — Wireshark distingue *capture filter* (sintassi BPF, `tcp port 80`, applicato in fase di registrazione) e *display filter* (sintassi a campi, `tcp.port == 80`, applicato sulla cattura). All'esame si usa quasi solo il display filter: `tcp.stream eq 0` isola la prima connessione, i nomi di protocollo (`ftp`, `dns`, `http.request`, `tls`) isolano le sessioni, e "Apply as Filter → Selected" costruisce l'espressione al posto tuo. Ricorda che `ip.addr`/`tcp.port` non fissano la direzione, `ip.src`/`tcp.dstport` sì.

Precedente: [[L1 - Interfaccia, cattura e anatomia di una traccia]] · Prossima: [[L3 - Statistiche, Conversations, Follow Stream ed Expert Info]].
