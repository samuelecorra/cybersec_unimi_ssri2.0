# RECAP 03 — M2 condensato: TCP + UDP (livello di trasporto)

> Recap di M2/UD5 + UD6. Le **lezioni in stampa integrale** sono `M2/UD5/L9` (Flusso e congestione), `L10` (Timeout e dec. moltiplicativo), `L11` (Fast Retransmit/Recovery). Qui condenso tutto il resto: controllo errore base (sliding window), controllo flusso, calcolo RTT/timeout, UDP.

---

## 1. Livello di trasporto: TCP vs UDP

| | **TCP** | **UDP** |
|---|---|---|
| Connessione | 3-way handshake | Nessuna |
| Affidabilità | Garantita (ACK + ritrasmissione) | Best-effort |
| Ordinamento | Garantito | Non garantito |
| Controllo di flusso | Sì (RcvWindow) | No |
| Controllo congestione | Sì (CWND, AIMD) | No |
| Header | 20 byte (min) | 8 byte |
| Tipo di comunicazione | Stream di byte | Datagrammi indipendenti |
| Multicast / Broadcast | No | Sì |
| Esempi applicativi | HTTP, FTP, SSH, SMTP | DNS, DHCP, NTP, streaming, VoIP |

---

## 2. UDP (UD6/L1-L2)

### Header UDP — fisso 8 byte

```
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|          Source Port          |       Destination Port        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|            Length             |           Checksum            |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                          Payload                              |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
```

| Campo | Bit | Note |
|---|:--:|---|
| Source Port | 16 | Opzionale (può essere 0 se non serve risposta) |
| Destination Port | 16 | Identifica il processo server |
| Length | 16 | Lunghezza totale (header + payload), min 8 |
| Checksum | 16 | Opzionale (può essere 0); calcolato su header + payload + pseudo-header IP |

### Caratteristiche

- **Senza connessione**: nessun handshake, ogni datagramma è indipendente.
- **Senza buffering**: l'OS non bufferizza in invio.
- **Confine di messaggio preservato**: ogni `sendto()` ↔ una `recvfrom()`.
- **MTU**: max 65 535 byte di datagramma (incluso header IP).

### Quando si usa UDP
- **DNS** (query veloci, retry applicativo)
- **DHCP** (broadcast)
- **NTP** (sincronizzazione)
- **Streaming audio/video, VoIP, gaming** (latenza > affidabilità)
- **SNMP**

### Servizi tipici sopra UDP che aggiungono middleware
- RPC, NFS (vecchio), Messaging system

---

## 3. TCP — segmento (header)

20 byte fissi, fino a 60 con opzioni.

```
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|          Source Port          |       Destination Port        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                       Sequence Number                         |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Acknowledgment Number                      |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|  Data |           |U|A|P|R|S|F|                               |
| Offset| Reserved  |R|C|S|S|Y|I|            Window             |
|       |           |G|K|H|T|N|N|                               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|           Checksum            |         Urgent Pointer        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Options                    |    Padding    |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
```

| Campo | Bit | Significato |
|---|:--:|---|
| Source/Dst Port | 16+16 | Porte sorgente/destinazione |
| Sequence Number | 32 | Numero del primo byte del payload |
| Ack Number | 32 | Prossimo byte atteso (cumulativo) |
| Data Offset | 4 | Lunghezza header in parole da 32 bit |
| Flags | 6 | **URG, ACK, PSH, RST, SYN, FIN** |
| Window | 16 | Spazio libero del ricevente (controllo flusso) |
| Checksum | 16 | Header + payload + pseudo-header IP |
| Urgent Pointer | 16 | Offset dati urgenti (se URG=1) |
| Options | var | MSS, Window Scale, SACK, Timestamps |

### Flag TCP

| Flag | Significato |
|---|---|
| **SYN** | Apertura connessione (handshake) |
| **ACK** | Acknowledgment valido (settato in tutti i pacchetti tranne primo SYN) |
| **FIN** | Chiusura ordinata della connessione |
| **RST** | Reset (chiusura forzata, errore) |
| **PSH** | Push: consegna immediata al livello sopra |
| **URG** | Dati urgenti (raramente usato) |

---

## 4. Apertura e chiusura connessione TCP

### 3-way handshake (apertura)

```
Client                              Server
  | --- SYN (seq=x)              ---> |
  | <-- SYN+ACK (seq=y, ack=x+1) ---  |
  | --- ACK (ack=y+1)            ---> |
  |       connessione attiva          |
```

### 4-way handshake (chiusura ordinata)

```
Client                              Server
  | --- FIN (seq=x)              ---> |
  | <-- ACK (ack=x+1)            ---  |
  | <-- FIN (seq=y)              ---  |
  | --- ACK (ack=y+1)            ---> |
```

> Il primo SYN ha `SYN=1, ACK=0` → ciò che il firewall stateless blocca per impedire connessioni iniziate da fuori.

---

## 5. Controllo di errore — sliding window (UD5/L1-L3)

### Modelli base

| Schema | Idea | Pro | Contro |
|---|---|---|---|
| **Stop & Wait** | Invia 1 pacchetto, aspetta ACK, poi invia il successivo | Semplice | Inefficiente: utilizzo ≈ 1/(1 + 2T/Tx) → quasi 0 su link lunghi |
| **Go-Back-N** | Sliding window N. ACK cumulativo. In caso di errore, ritrasmette TUTTO da quello perso | Buffer rx semplice (size 1) | Spreco di banda |
| **Selective Repeat** | Sliding window N. ACK per ogni pacchetto. Ritrasmette **solo** il perso | Efficiente | Buffer rx grande, riordino |

### Pieno utilizzo del collegamento (BDP)

$$
\boxed{\text{Window} \ge \text{Banda} \times \text{RTT}}
$$

Questo è il **Bandwidth-Delay Product (BDP)**: la finestra di trasmissione deve almeno "riempire la pipeline" per sfruttare tutta la banda.

**Esempio**: link a 100 Mbps con RTT = 100 ms.
$$\text{BDP} = 100 \cdot 10^6 \cdot 0{,}1 = 10^7 \text{ bit} = 1{,}25 \text{ MB}$$

---

## 6. Controllo di flusso TCP (UD5/L4-L5)

### Parametri

- **RcvBuffer**: dimensione totale buffer ricezione.
- **RcvWindow** (advertised window): porzione libera, comunicata nell'header TCP campo Window.
- **Sender Window**: i dati inviati ma non ancora ACKati.

### Regola

$$
\text{SenderWindow} \le \text{RcvWindow}
$$

### Persist Timer

Se RcvWindow = 0 (ricevente saturo), il mittente attiva il **persist timer**: periodicamente invia un byte per "sondare" se RcvWindow è cresciuta. Evita il deadlock se l'ACK di apertura della finestra si perdesse.

### Algoritmo di Nagle (no-burst di piccoli segmenti)

> Se ci sono dati pendenti non ACKati, non inviare segmenti piccoli; accumula finché:
>   - ACK arriva → invia tutto subito
>   - oppure si raggiunge un MSS → invia subito

Risolve il problema di Telnet che inviava 1 byte alla volta. Disabilitabile con `TCP_NODELAY`.

### ACK ritardato

Il ricevente non ACKa immediatamente: aspetta fino a 500 ms (tipico 200 ms) per:
- vedere se ha dati propri da inviare (piggybacking)
- accumulare più segmenti (ACK cumulativo)

---

## 7. Calcolo del timeout TCP (UD5/L6-L7)

### Media esponenziale pesata (EWMA)

$$
\text{EstimatedRTT} = (1-\alpha) \cdot \text{EstimatedRTT} + \alpha \cdot \text{SampleRTT}
$$

Tipicamente $\alpha = 0{,}125$ (TCP moderno).

### Jacobson/Karels (RTO adattivo) — **DA SAPERE**

$$
\begin{cases}
\text{EstimatedRTT} = (1-\alpha)\cdot \text{EstimatedRTT} + \alpha \cdot \text{SampleRTT} \\[4pt]
\text{Error} = \big|\text{SampleRTT} - \text{EstimatedRTT}\big| \\[4pt]
\text{Deviation} = (1-\beta)\cdot \text{Deviation} + \beta\cdot \text{Error} \\[4pt]
\boxed{\text{Timeout (RTO)} = \text{EstimatedRTT} + 4 \cdot \text{Deviation}}
\end{cases}
$$

con $\alpha = 0{,}125$, $\beta = 0{,}25$.

> **Logica**: il timeout è centrato sul RTT medio + un margine di sicurezza pari a ~4 deviazioni (≈ 4 σ).

### Limiti pratici
- RTO ha un **minimo** (es. 200 ms su Linux) e un **massimo** (60 s).
- Dopo un timeout, RTO viene **raddoppiato** (exponential backoff) ad ogni ritrasmissione fallita.

---

## 8. Controllo della congestione TCP (UD5/L8-L11) — DISTILLATO

> **Tutto il resto è nelle 3 lezioni stampate** (`L9`, `L10`, `L11`). Qui solo lo schema riassuntivo per non perdere il filo.

### Le due finestre

$$
\boxed{\text{ActualWindow} = \min(\text{RcvWindow}, \text{CWND})}
$$

### Fasi

| Fase | Condizione | Regola CWND |
|---|---|---|
| **Slow Start (SS)** | `CWND < SSTHRESH` | Raddoppia ogni RTT (+1 MSS per ACK) |
| **Congestion Avoidance (CA)** | `CWND ≥ SSTHRESH` | +1 MSS per RTT |

### Eventi e reazioni

| Evento | SSTHRESH | CWND | Fase successiva |
|---|---|---|---|
| **Timeout** | `← CWND/2` | `← 1 MSS` | Slow Start |
| **3 ACK duplicati (TCP Tahoe)** | `← CWND/2` | `← 1 MSS` | Slow Start |
| **3 ACK duplicati (TCP Reno)** | `← CWND/2` | `← SSTHRESH + 3` (fast recovery), poi `← SSTHRESH` quando arriva nuovo ACK | Congestion Avoidance |

### TCP Tahoe vs Reno vs NewReno vs Cubic

| Versione | Caratteristica |
|---|---|
| **Tahoe** (1988) | Slow Start, Congestion Avoidance, Fast Retransmit. **No Fast Recovery**: anche con 3 dup-ACK → CWND=1 |
| **Reno** (1990) | Aggiunge **Fast Recovery**: con 3 dup-ACK CWND non torna a 1 |
| **NewReno** | Migliora il recovery in caso di **multiple perdite** nella stessa finestra |
| **Cubic** (default Linux moderno) | Crescita CWND come funzione cubica del tempo dall'ultimo loss; ottimo su reti ad alta banda-latenza |

---

## 9. Throughput TCP — formule da esame

### Throughput istantaneo (semplificato)

$$
\text{Throughput} = \frac{\text{CWND}}{\text{RTT}}
$$

### Tempo di trasferimento (esempio 8/5/2026: 1 GB su Ethernet 100 Mbps)

**Approccio "ideale" (no overhead, no slow start)**:

$$
T_{transfer} = \frac{\text{File Size}}{\text{Throughput}}
$$

Per 1 GB = $1 \cdot 2^{30} \cdot 8$ bit $= 8{,}59 \cdot 10^9$ bit su 100 Mbps:

$$
T = \frac{8{,}59 \cdot 10^9}{10^8} \approx 85{,}9 \text{ s} \approx 86 \text{ s}
$$

**Approccio "realistico" (con header overhead)**:

Ogni MSS reale (1460 byte) viaggia incapsulato in:
- TCP header: 20 byte
- IP header: 20 byte
- Ethernet header + trailer: 18 byte (+preambolo 8 bit non contati nel frame ma sul filo)
- **Totale overhead per segmento**: 58 byte su 1518 → ~3.8%

Tempo realistico ≈ 86 / 0.96 ≈ **89 s**.

**Considerazioni aggiuntive**:
- Slow Start iniziale aggiunge qualche RTT (trascurabile su file grandi)
- ACK consumano banda nella direzione opposta (se half-duplex)
- Inter-frame gap Ethernet 9.6 μs × n° frame (trascurabile)

### Formula con RTT (Mathis)

Per reti con perdita $p$:

$$
\text{Throughput}_{Mathis} \approx \frac{MSS \cdot 1{,}22}{RTT \cdot \sqrt{p}}
$$

---

## 10. MSS, MTU, segmento massimo

- **MTU Ethernet**: 1500 byte
- **MSS tipico**: 1500 − 20 (IP) − 20 (TCP) = **1460 byte**
- MSS negoziato nei flag SYN come **TCP option**.

---

## 11. Porte ben note (well-known ports)

| Porta | Protocollo | Servizio |
|:--:|:--:|---|
| 20/21 | TCP | FTP (data/control) |
| 22 | TCP | SSH |
| 23 | TCP | Telnet |
| 25 | TCP | SMTP |
| 53 | TCP/UDP | DNS |
| 67/68 | UDP | DHCP (server/client) |
| 69 | UDP | TFTP |
| 80 | TCP | HTTP |
| 110 | TCP | POP3 |
| 123 | UDP | NTP |
| 143 | TCP | IMAP |
| 161/162 | UDP | SNMP |
| 443 | TCP | HTTPS |

---

## 12. Firewall TCP — regola "no connessioni dall'esterno"

### Logica
- **Primo SYN entrante** (`SYN=1, ACK=0`): bloccare.
- **SYN+ACK entrante** (risposta al nostro SYN): permettere.
- **Tutti i pacchetti di sessioni già aperte** (ESTABLISHED): permettere.

### iptables stateful (consigliato)

```bash
iptables -P INPUT DROP
iptables -A INPUT -i lo -j ACCEPT
iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT
# OUTPUT libero
iptables -P OUTPUT ACCEPT
```

### iptables stateless

```bash
iptables -A INPUT -i eth0 -p tcp --syn -j DROP
iptables -A INPUT -i eth0 -p tcp -j ACCEPT  # SYN+ACK, ACK, FIN passano
```

---

## 13. Cheat-sheet "se il prof chiede X, dico Y"

| Se chiede... | Rispondo... |
|---|---|
| Quanto tempo per trasferire N byte su link X | $T = \frac{N \cdot 8}{R}$ + overhead ~5% (vedi §9) |
| Differenza UDP vs TCP | Tabella §1 + UDP per VoIP/DNS, TCP per HTTP/FTP |
| Calcolo CWND round per round | Vedi `M2/UD5/L9-L11` stampati; tabella eventi §8 |
| Formula del timeout TCP | Jacobson §7: `RTO = EstRTT + 4·Dev` |
| 3-way handshake | SYN → SYN+ACK → ACK (§4) |
| Sliding window pieno utilizzo | `Window ≥ Banda × RTT` (BDP, §5) |
| Bloccare connessioni TCP da fuori | iptables conntrack ESTABLISHED,RELATED ACCEPT + default DROP (§12) |
