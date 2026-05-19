# Soluzione Esame di Reti di Calcolatori — 23/05/2025

**Prof. Ernesto Damiani**

Soluzione integrale di entrambi i compiti (**Parte A** + **Compito B**), redatta in chiave “30 e lode”: ogni esercizio è preceduto dall’inquadramento teorico e seguito dai calcoli numerici, dalle formule e dai diagrammi richiesti.

---

## Indice

- [Parte A](#parte-a)
  - [Esercizio 1 — Andamento CWND e SSTHRESH (TCP)](#esercizio-1--andamento-cwnd-e-ssthresh-tcp)
  - [Esercizio 2 — Datagrammi UDP del sensore di temperatura](#esercizio-2--datagrammi-udp-del-sensore-di-temperatura)
  - [Esercizio 3 — Progetto di inter-rete (subnetting)](#esercizio-3--progetto-di-inter-rete-subnetting)
- [Compito B](#compito-b)
  - [Esercizio 1 — Chat UDP fra due peer con fork()](#esercizio-1--chat-udp-fra-due-peer-con-fork)
  - [Esercizio 2 — UDP vs TCP, sicurezza, multipeer](#esercizio-2--udp-vs-tcp-sicurezza-multipeer)
  - [Esercizio 3 (8 pt) — Query DNS non autoritativa](#esercizio-3-8-pt--query-dns-non-autoritativa)
  - [Esercizio 3 (4 pt) — Regola di firewall TCP](#esercizio-3-4-pt--regola-di-firewall-tcp)

---

# Parte A

## Esercizio 1 — Andamento CWND e SSTHRESH (TCP)

> **Riferimento di teoria**: [M2/UD5/L9 — Flusso e congestione TCP](../../M2_Protocolli_rete_TCP_IP/UD5/L9%20-%20Flusso%20e%20congestione%20TCP.md)

### Inquadramento teorico

TCP coordina due meccanismi:

- **Controllo di flusso**: il ricevitore annuncia `RCVWND` (spazio libero nel buffer di ricezione) per non sovraccaricare se stesso.
- **Controllo della congestione**: il mittente regola `CWND` (Congestion Window) in funzione delle condizioni della rete (ACK ricevuti, perdite, RTT).

In ogni istante il mittente può immettere in rete al massimo:

$$
\text{ActualWindow} \;=\; \min\bigl(\text{RCVWND},\, \text{CWND}\bigr)
$$

Le due fasi della crescita di `CWND`:

- **Slow Start** (`CWND < SSTHRESH`): per ogni ACK ricevuto, `CWND += 1 MSS`. Effetto netto: `CWND` raddoppia ad ogni RTT (crescita **esponenziale**).
- **Congestion Avoidance** (`CWND ≥ SSTHRESH`): `CWND` cresce di **1 MSS per RTT** (crescita **additiva**, AIMD).

In caso di perdita rilevata da timeout: `SSTHRESH ← CWND/2`, `CWND ← 1 MSS`, si ricomincia da Slow Start.
In caso di 3 ACK duplicati (Fast Retransmit + Fast Recovery): `SSTHRESH ← CWND/2`, `CWND ← SSTHRESH + 3·MSS`.

### Dati

| Parametro | Valore |
|---|---|
| Dati da trasferire | $D = 128 \text{ KB} = 128 \cdot 1024 = 131\,072$ byte |
| MSS | $1000$ byte |
| RCVWND annunciato | $32 \text{ KB} = 32\,768$ byte $\approx 32$ MSS |
| SSTHRESH iniziale | $\text{RCVWND}/2 = 16 \text{ KB} = 16\,384$ byte $\approx 16$ MSS |
| RTT | $0{,}5$ s costante; varia $0{,}5$–$0{,}8$ s dopo $3$ s |
| Eventi di perdita | non sono indicati → **nessuna riduzione di CWND** |

> $D \approx 131$ MSS (i 72 byte residui costituiscono l’ultimo segmento parziale).

### Calcolo round per round

Convenzione: in ogni round (un RTT) il mittente trasmette un’intera “raffica” pari alla `CWND` corrente; alla ricezione di tutti gli ACK la `CWND` viene aggiornata.

**Fase 1 — Slow Start** ($\text{CWND} < 16$ MSS), raddoppio per round:

| Round | $t$ (s) | CWND (MSS) | Inviati nel round | Cumulato (MSS) |
|---:|---:|---:|---:|---:|
| 1 | 0,0 | 1 | 1 | 1 |
| 2 | 0,5 | 2 | 2 | 3 |
| 3 | 1,0 | 4 | 4 | 7 |
| 4 | 1,5 | 8 | 8 | 15 |
| 5 | 2,0 | 16 | 16 | 31 |

A $t=2{,}0$ s si ha $\text{CWND} = \text{SSTHRESH} = 16$ MSS: passaggio a **Congestion Avoidance**.

**Fase 2 — Congestion Avoidance** ($\text{CWND} \geq 16$ MSS), incremento +1 MSS per RTT.
Da $t \geq 3$ s il RTT cresce nell’intervallo $[0{,}5;\, 0{,}8]$ s (segnale di congestione incipiente, ma senza perdite l’algoritmo continua additivamente):

| Round | $t$ (s) | CWND (MSS) | Inviati | Cumulato (MSS) |
|---:|---:|---:|---:|---:|
| 6 | 2,5 | 17 | 17 | 48 |
| 7 | 3,0 | 18 | 18 | 66 |
| 8 | ≈ 3,7 | 19 | 19 | 85 |
| 9 | ≈ 4,5 | 20 | 20 | 105 |
| 10 | ≈ 5,3 | 21 | 21 | 126 |
| 11 | ≈ 6,1 | 22 | **5** (residui) | **131** |

Alla fine dell’11° round il mittente ha trasmesso esattamente i $\approx 131$ MSS che costituiscono i 128 KB richiesti.

### Risultato

$$
\boxed{\;\text{CWND}_{\text{finale}} \approx 21\text{–}22\ \text{MSS} \;\approx\; 21\ \text{KB}\;}
\qquad
\boxed{\;\text{SSTHRESH}_{\text{finale}} = 16\ \text{KB} \;\approx\; 16\ \text{MSS}\;}
$$

`SSTHRESH` resta invariata rispetto al valore iniziale: non essendoci alcun evento di perdita, non viene ricalcolata.
Si noti inoltre che $\text{CWND} \leq \text{RCVWND} = 32$ MSS in tutti i round: la finestra di ricezione non è mai stata vincolante.

### Grafico dell’andamento di CWND

```
CWND
(MSS)
  32 ┤ . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .  ← RCVWND = 32 MSS
  24 ┤
  22 ┤                                                       ●
  21 ┤                                                ●─────╯
  20 ┤                                          ●────╯
  19 ┤                                    ●────╯
  18 ┤                              ●────╯
  17 ┤                          ●──╯                                  Congestion
  16 ┤- - - - - - - - - - - ●──╯- - - - - - - - - - - - - - - - - - - Avoidance (+1/RTT)
  14 ┤                    ╱                                           ← SSTHRESH=16 MSS
  12 ┤                  ╱
   8 ┤              ●  (raddoppio)                                    Slow Start
   4 ┤          ●                                                     (×2 per RTT)
   2 ┤      ●
   1 ┤●
     └┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬───→ t (s)
      0  0.5  1.0  1.5  2.0  2.5  3.0  3.7  4.5  5.3  6.1
            Slow Start       │       Congestion Avoidance
                          SSTHRESH
```

Caratteristiche chiave del grafico:

- da $t=0$ a $t=2$ s: raddoppio per RTT (esponenziale);
- a $t=2$ s la curva interseca `SSTHRESH = 16 MSS`;
- da $t=2$ s in poi: crescita lineare con pendenza $+1\ \text{MSS}/\text{RTT}$;
- da $t \geq 3$ s la pendenza si riduce visivamente perché un RTT più lungo dilata l’asse temporale.

---

## Esercizio 2 — Datagrammi UDP del sensore di temperatura

> **Riferimento di teoria**: [M2/UD6/L1 — User Datagram Protocol (UDP)](../../M2_Protocolli_rete_TCP_IP/UD6/L1%20-%20User%20Datagram%20Protocol%20(UDP).md)

### Inquadramento teorico

UDP (RFC 768) è un protocollo di livello di trasporto **senza connessione** e **inaffidabile**. L’intestazione è fissa, $8$ byte:

| Campo | Lunghezza | Significato |
|---|---:|---|
| Porta sorgente | 16 bit | Identifica il processo mittente (opzionale, può essere $0$) |
| Porta destinazione | 16 bit | Identifica il processo destinatario |
| Length | 16 bit | Lunghezza totale del datagramma (header + payload) in byte |
| Checksum | 16 bit | Controllo di integrità su header + payload (opzionale) |

> Ogni `sendto()` su un socket `SOCK_DGRAM` genera **un singolo datagramma UDP**: il confine del messaggio è preservato (a differenza di TCP che è uno stream).

### Dati del problema

Il client invia **un valore intero per datagramma**. I valori del sensore sono $2{,}3$ e $4{,}1$, scomposti come coppie (intero, decimale):

$$
2{,}3 \;\longrightarrow\; (2,\,3) \qquad 4{,}1 \;\longrightarrow\; (4,\,1)
$$

Pertanto sono prodotti **4 datagrammi UDP indipendenti**. Si assume un intero a 2 byte (`int16`), porte applicative: client effimera $p_c$ (es. $49152$) ↔ server $p_s$ (es. $5000$).

### Diagramma del traffico (client → server)

```
   Client (porta p_c=49152)                              Server (porta p_s=5000)
   ─────────────────────────                              ───────────────────────
   t1  ── Datagram #1 ───────────────────────────────►
   t2  ── Datagram #2 ───────────────────────────────►
   t3  ── Datagram #3 ───────────────────────────────►
   t4  ── Datagram #4 ───────────────────────────────►
```

### Dettaglio di ciascun datagramma

Header lungo $8$ byte (4 campi da 2 byte) + payload da $2$ byte $\Rightarrow$ Length totale = $10$ byte.

**Datagram #1 — parte intera di 2,3**

```
┌──────────────────────┬──────────────────────┐
│ Src Port  = 49152    │ Dst Port  = 5000     │
├──────────────────────┼──────────────────────┤
│ Length    = 10       │ Checksum  = 0xXXXX   │
├──────────────────────┴──────────────────────┤
│ Payload   = 0x0002   ← intero "2"           │
└─────────────────────────────────────────────┘
```

**Datagram #2 — parte decimale di 2,3**

```
┌──────────────────────┬──────────────────────┐
│ Src Port  = 49152    │ Dst Port  = 5000     │
├──────────────────────┼──────────────────────┤
│ Length    = 10       │ Checksum  = 0xXXXX   │
├──────────────────────┴──────────────────────┤
│ Payload   = 0x0003   ← intero "3"           │
└─────────────────────────────────────────────┘
```

**Datagram #3 — parte intera di 4,1**

```
┌──────────────────────┬──────────────────────┐
│ Src Port  = 49152    │ Dst Port  = 5000     │
├──────────────────────┼──────────────────────┤
│ Length    = 10       │ Checksum  = 0xXXXX   │
├──────────────────────┴──────────────────────┤
│ Payload   = 0x0004   ← intero "4"           │
└─────────────────────────────────────────────┘
```

**Datagram #4 — parte decimale di 4,1**

```
┌──────────────────────┬──────────────────────┐
│ Src Port  = 49152    │ Dst Port  = 5000     │
├──────────────────────┼──────────────────────┤
│ Length    = 10       │ Checksum  = 0xXXXX   │
├──────────────────────┴──────────────────────┤
│ Payload   = 0x0001   ← intero "1"           │
└─────────────────────────────────────────────┘
```

### Osservazioni critiche

- **Ordine non garantito**: UDP non garantisce che i quattro datagrammi arrivino nella sequenza di invio. Il server deve essere in grado di ricostruirli (es. numero di sequenza applicativo o invio della coppia in un unico datagramma).
- **Perdita**: se uno dei due datagrammi che compongono un valore va perso, il server riceverà un valore deformato (es. solo la parte intera). In un caso reale converrebbe inviare la coppia in un **unico datagramma** (es. payload $4$ byte: `[intero | decimale]`) per atomicità.
- **Overhead**: $80\%$ del datagramma è header ($8/10$). Su flussi ad alta cadenza l’efficienza è bassa; tuttavia per un sensore di temperatura la latenza è più importante del throughput.

---

## Esercizio 3 — Progetto di inter-rete (subnetting)

> **Riferimento di teoria**: [M2/UD2/L4 — Progetto di inter-reti](../../M2_Protocolli_rete_TCP_IP/UD2/L4%20-%20Progetto%20di%20inter-reti.md) (subnetting FLSM/VLSM) · [M2/UD3/L1 — Introduzione all'instradamento IP](../../M2_Protocolli_rete_TCP_IP/UD3/L1%20-%20Introduzione%20all%E2%80%99instradamento%20IP.md) (tabella di routing, ARP, hop)

### Topologia

```
   LAN1 ── R1 ─── R2 ─── R3 ─── R4 ── LAN4
            eth11/12   eth21/22/23    eth31/32/33   eth41/42
                       │              │
                       LAN2           R5 ── LAN5
                                      eth51/52
```

Interfacce per router (dal disegno):

| Router | Interfacce |
|---|---|
| R1 | eth11 → LAN1; eth12 → link R1-R2 |
| R2 | eth21 → link R1-R2; eth22 → link R2-R3; eth23 → LAN2 |
| R3 | eth31 → link R2-R3; eth32 → link R3-R4; eth33 → link R3-R5 |
| R4 | eth41 → link R3-R4; eth42 → LAN4 |
| R5 | eth51 → link R3-R5; eth52 → LAN5 |

### Range disponibile

$194.1.1.17 \;\div\; 194.1.1.255 \;\Rightarrow\; 255 - 17 + 1 = 239$ indirizzi.

Net-ID “naturale”: $194.1.1.0/24$ (classe C), con maschera di default $255.255.255.0$.

> Adottiamo **VLSM**: maschere diverse per LAN e per i link punto-punto, per massimizzare l’efficienza.

### a) Indirizzi per LAN 1, 2, 4, 5

Le quattro LAN richiedono spazio per un numero non specificato di host: scegliamo **/27** (subnet mask $255.255.255.224$), che fornisce $2^5 - 2 = 30$ host per LAN — soluzione ampiamente sufficiente e regolare.

Vincolo di allineamento: una /27 inizia su un multiplo di $32$. Il primo multiplo di $32$ nel range disponibile è $\mathbf{.32}$ (gli indirizzi $.17$–$.31$ non costituiscono una /27 valida).

| LAN | Subnet (network) | Maschera | Broadcast | Range host utili |
|---|---|---|---|---|
| LAN1 | `194.1.1.32/27` | `255.255.255.224` | `194.1.1.63` | `194.1.1.33` – `194.1.1.62` |
| LAN2 | `194.1.1.64/27` | `255.255.255.224` | `194.1.1.95` | `194.1.1.65` – `194.1.1.94` |
| LAN4 | `194.1.1.96/27` | `255.255.255.224` | `194.1.1.127` | `194.1.1.97` – `194.1.1.126` |
| LAN5 | `194.1.1.128/27` | `255.255.255.224` | `194.1.1.159` | `194.1.1.129` – `194.1.1.158` |

Le interfacce di router collegate alle LAN (gateway predefiniti) consumano $1$ indirizzo della rispettiva LAN; tipicamente si sceglie il primo host utile:

| Interfaccia router | Indirizzo IP assegnato |
|---|---|
| R1.eth11 (gateway LAN1) | `194.1.1.33` |
| R2.eth23 (gateway LAN2) | `194.1.1.65` |
| R4.eth42 (gateway LAN4) | `194.1.1.97` |
| R5.eth52 (gateway LAN5) | `194.1.1.129` |

### b) Indirizzi per i collegamenti punto-punto

Un link punto-punto richiede esattamente **2 indirizzi**: per i 4 link bastano sottoreti **/30** (mask $255.255.255.252$), $2^2 - 2 = 2$ host utili ciascuna. Partiamo dal primo multiplo di $4$ dopo `.159`, ovvero `.160`.

| Link | Subnet | Maschera | Network | Broadcast | IP estremo 1 | IP estremo 2 |
|---|---|---|---|---|---|---|
| link R1-R2 | `194.1.1.160/30` | `255.255.255.252` | `.160` | `.163` | R1.eth12 = `.161` | R2.eth21 = `.162` |
| link R2-R3 | `194.1.1.164/30` | `255.255.255.252` | `.164` | `.167` | R2.eth22 = `.165` | R3.eth31 = `.166` |
| link R3-R4 | `194.1.1.168/30` | `255.255.255.252` | `.168` | `.171` | R3.eth32 = `.169` | R4.eth41 = `.170` |
| link R3-R5 | `194.1.1.172/30` | `255.255.255.252` | `.172` | `.175` | R3.eth33 = `.173` | R5.eth51 = `.174` |

### c) Indirizzi IP non utilizzati

Indirizzi **non assegnabili** ad alcun host (rimasti liberi nello spazio fornito):

| Blocco | Da | A | Quantità | Motivo |
|---|---|---|---:|---|
| Iniziale | `194.1.1.17` | `194.1.1.31` | 15 | Non costituiscono una /27 allineata |
| Finale | `194.1.1.176` | `194.1.1.255` | 80 | Spazio residuo non assegnato |

**Totale indirizzi non utilizzati**: $15 + 80 = \mathbf{95}$.

(Non si conteggiano network/broadcast delle subnet assegnate: sono “inutilizzabili” per definizione, non “non utilizzati”.)

### d) Tabella di routing di R3

Reti **direttamente connesse** a R3:
- `194.1.1.164/30` (eth31)
- `194.1.1.168/30` (eth32)
- `194.1.1.172/30` (eth33)

Reti **raggiungibili tramite next-hop**:

| Destinazione (Network) | Subnet mask | Gateway (Next-hop) | Interfaccia uscita |
|---|---|---|---|
| `194.1.1.32`  (LAN1) | `255.255.255.224` | `194.1.1.165` (R2.eth22) | eth31 |
| `194.1.1.64`  (LAN2) | `255.255.255.224` | `194.1.1.165` (R2.eth22) | eth31 |
| `194.1.1.96`  (LAN4) | `255.255.255.224` | `194.1.1.170` (R4.eth41) | eth32 |
| `194.1.1.128` (LAN5) | `255.255.255.224` | `194.1.1.174` (R5.eth51) | eth33 |
| `194.1.1.160` (link R1-R2) | `255.255.255.252` | `194.1.1.165` (R2.eth22) | eth31 |
| `194.1.1.164` (link R2-R3) | `255.255.255.252` | *direct* (`194.1.1.166`) | eth31 |
| `194.1.1.168` (link R3-R4) | `255.255.255.252` | *direct* (`194.1.1.169`) | eth32 |
| `194.1.1.172` (link R3-R5) | `255.255.255.252` | *direct* (`194.1.1.173`) | eth33 |

> Una **default route** non è inserita perché tutto lo spazio in uso è esplicitamente raggiungibile dal router.

### e) Trattamento di un pacchetto LAN1 → LAN5

Sia `H1 = 194.1.1.34` (host in LAN1) sorgente, e `H5 = 194.1.1.130` (host in LAN5) destinazione.

#### 1. Origine — host H1 (livello IP)

- H1 calcola il prefisso della destinazione applicando AND con la propria maschera:
  $$
  \texttt{194.1.1.130 AND 255.255.255.224} = \texttt{194.1.1.128} \;\neq\; \texttt{194.1.1.32}
  $$
  La destinazione è **fuori dalla subnet locale** ⇒ il pacchetto va consegnato al **default gateway** R1.eth11 (`194.1.1.33`).
- H1 costruisce il pacchetto IP:
  - `src IP = 194.1.1.34`, `dst IP = 194.1.1.130`
  - `protocol`, `TTL = 64` (tipico), checksum header, payload.

#### 2. Origine — host H1 (livello Ethernet)

- H1 deve incapsulare il pacchetto in un frame Ethernet destinato al **MAC di R1.eth11**, non al MAC di H5.
- Se la coppia `IP_gateway → MAC_gateway` non è in cache, H1 emette una **ARP request** in broadcast (`dst MAC = FF:FF:FF:FF:FF:FF`, `EtherType = 0x0806`) sulla LAN1; R1 risponde in unicast con il proprio MAC.
- Il frame Ethernet finale ha `src MAC = MAC(H1)`, `dst MAC = MAC(R1.eth11)`, `EtherType = 0x0800`, payload = pacchetto IP.

#### 3. R1 (livello IP)

- R1 riceve il frame, lo de-incapsula, esamina `dst IP = 194.1.1.130`.
- Consulta la propria routing table: per raggiungere `194.1.1.128/27` il next-hop è R2 (`194.1.1.162`) via eth12.
- Decrementa **TTL** (e ricalcola la checksum dell’header IP).

#### 4. R1 (livello Ethernet)

- ARP per ottenere `MAC(R2.eth21)` (o usa la cache), poi incapsula il pacchetto in un nuovo frame su eth12: `src MAC = MAC(R1.eth12)`, `dst MAC = MAC(R2.eth21)`.

#### 5. R2 → R3 → R5 (analogo)

Stesso procedimento: ad ogni hop, **gli indirizzi IP source/destination del pacchetto restano invariati**, mentre **gli indirizzi MAC vengono riscritti** ad ogni link; **TTL** è decrementato di 1.

#### 6. R5 — consegna finale

- R5 osserva che `194.1.1.130` appartiene alla sua LAN5 (direttamente connessa).
- ARP per `MAC(H5)`, incapsula in frame Ethernet su eth52 e consegna direttamente all’host destinazione.

> **Regola d’oro**: a livello IP gli endpoint **non cambiano mai**; a livello Ethernet (link) **cambiano ad ogni hop**, perché ogni rete locale ha il proprio dominio di broadcast e ARP.

---

# Compito B

## Esercizio 1 — Chat UDP fra due peer con fork()

> **Riferimento di teoria**: [M4/UD1/L4 — Funzioni di comunicazione via socket](../../M4_Tecniche_programmazione_distribuita/UD1/L4%20-%20Funzioni%20di%20comunicazione%20via%20socket.md) (`sendto`/`recvfrom`) · [M4/UD1/L2 — Creazione di un socket](../../M4_Tecniche_programmazione_distribuita/UD1/L2%20-%20Creazione%20di%20un%20socket.md)

### Inquadramento teorico

Per una comunicazione peer-to-peer in tempo reale UDP è la scelta naturale:

- non occorre fase di handshake (`connect`),
- ogni `sendto`/`recvfrom` opera su datagrammi indipendenti,
- la latenza è minima.

Per gestire **invio e ricezione simultanei** su un singolo socket sono possibili tre approcci canonici:

1. **`select()`** o **`poll()`** su un unico processo (multiplexing I/O);
2. **Multithreading**;
3. **`fork()`** che genera due processi figli che condividono il descrittore di socket.

Il testo richiede la terza soluzione. Dopo `fork()`, **padre e figlio condividono il socket** ($\Rightarrow$ entrambi possono leggere e scrivere senza interferenze, perché `recvfrom` e `sendto` operano in direzioni diverse). Il padre gestisce l’invio (da `stdin`), il figlio gestisce la ricezione (verso `stdout`).

### Pseudocodice

```python
# ───────────────────────────────────────────────────────────────────
# peer_chat.py — chat UDP P2P con fork()
# Avvio: ./peer_chat <local_port> <remote_ip> <remote_port>
# ───────────────────────────────────────────────────────────────────

CONST BUF_SIZE = 4096
CONST EXIT_CMD = "exit"

function main(argv):

    # 1) Parsing argomenti -------------------------------------------
    local_port  = int(argv[1])                  # porta locale (bind)
    remote_ip   = argv[2]                       # IP del peer remoto
    remote_port = int(argv[3])                  # porta del peer remoto

    # 2) Creazione e bind del socket UDP -----------------------------
    sock = socket(AF_INET, SOCK_DGRAM)
    bind(sock, ("0.0.0.0", local_port))         # ascolta su tutte le NIC

    print("[INFO] In ascolto su porta " + local_port +
          ", peer = " + remote_ip + ":" + remote_port)

    # 3) Fork: split del processo ------------------------------------
    pid = fork()

    if pid < 0:
        print("[ERRORE] fork fallito")
        close(sock); exit(1)

    # -----------------------------------------------------------------
    # 4) PROCESSO FIGLIO  →  ricezione asincrona
    # -----------------------------------------------------------------
    if pid == 0:                                # figlio
        while true:
            (data, from_addr) = recvfrom(sock, BUF_SIZE)   # bloccante
            if data == EMPTY: continue
            msg = decode_utf8(data)
            print("\n[" + from_addr.ip + ":" + from_addr.port + "] "
                  + msg + "\n> ")
            # NB: non rispondiamo automaticamente; spetta all'utente.
        # mai raggiunto: il figlio termina via signal dal padre

    # -----------------------------------------------------------------
    # 5) PROCESSO PADRE  →  invio da stdin
    # -----------------------------------------------------------------
    else:                                       # padre  (pid > 0)
        install_signal_handler(SIGCHLD, on_child_exit)

        while true:
            print("> ", no_newline=true)
            line = read_line(stdin)             # bloccante su tastiera

            if line == EXIT_CMD:
                # terminazione ordinata
                kill(pid, SIGTERM)              # ferma il figlio
                waitpid(pid)                    # raccoglie lo zombie
                close(sock)
                print("[INFO] Chat terminata.")
                exit(0)

            payload = encode_utf8(line)
            sendto(sock, payload, (remote_ip, remote_port))

# Handler invocato se il figlio muore inaspettatamente -----------------
function on_child_exit(signal):
    waitpid(ANY_CHILD, WNOHANG)
    print("[WARN] Processo di ricezione terminato.")
    exit(1)

main(argv)
```

### Punti di robustezza messi in chiaro

- **Bind solo lato locale**: UDP non ha `connect` obbligatorio. Si fa `bind` sulla porta locale per ricevere; in invio è il sistema operativo a scegliere la porta sorgente, ma qui usiamo la stessa porta sia in entrata sia in uscita (semplifica il `recvfrom` lato peer).
- **Condivisione del socket**: il descrittore restituito da `socket()` è ereditato attraverso `fork()` ed è condiviso fra i due processi. Le primitive `recvfrom` (figlio) e `sendto` (padre) non collidono perché operano in direzioni opposte sul medesimo handle.
- **Terminazione pulita**: il padre invia `SIGTERM` al figlio e attende con `waitpid()` per evitare processi *zombie*. La gestione di `SIGCHLD` copre il caso opposto (crash del figlio).
- **Tempo reale**: il `recvfrom` nel figlio stampa appena arriva il datagramma (no buffer in attesa) — vincolo del testo.
- **Esuriente per UDP**: nessun controllo di sequenza, niente ritrasmissione: se un messaggio si perde, è perso. Questo limite è accettabile per una chat semplice.

---

## Esercizio 2 — UDP vs TCP, sicurezza, multipeer

> **Riferimento di teoria**: [M2/UD6/L2 — UDP e TCP a confronto](../../M2_Protocolli_rete_TCP_IP/UD6/L2%20-%20UDP%20e%20TCP%20a%20confronto.md)

### 1. Vantaggi e svantaggi di UDP rispetto a TCP nel contesto della chat

| Aspetto | TCP | UDP |
|---|---|---|
| Connessione | 3-way handshake | nessuno |
| Affidabilità | consegna garantita, ordinata, deduplicata | best-effort |
| Controllo di flusso | sì (RcvWindow) | no |
| Controllo di congestione | sì (Slow Start + AIMD) | no |
| Header | 20 byte (min) | 8 byte |
| Latenza setup | RTT ≥ 1 | 0 |
| Multicast/Broadcast | no (1-a-1) | sì |
| Framing | stream continuo (Nagle) | preserva i confini dei datagrammi |

**Vantaggi di UDP nella chat P2P**:

- **Latenza minima**: niente handshake, niente attesa di ACK ⇒ il messaggio compare “in tempo reale”.
- **Header leggero**: 8 byte contro 20+, importante per messaggi corti (analogo al ragionamento sui flussi interattivi visto in `M2/UD5/L5`).
- **Confini di messaggio preservati**: un `sendto(line)` corrisponde a un `recvfrom(line)` lato peer ⇒ niente parsing custom.
- **Multicast nativo**: facilita un’evoluzione verso chat di gruppo.
- **Semplicità**: non occorre `listen()`/`accept()`, riduce il codice e i potenziali bug.

**Svantaggi**:

- **Nessuna garanzia di consegna**: un messaggio perso non viene ritrasmesso ⇒ l’utente non sa se l’altro l’ha letto.
- **Nessun ordinamento**: i messaggi possono essere visualizzati nell’ordine sbagliato.
- **Nessuna deduplicazione**: un datagramma può apparire due volte.
- **MTU**: messaggi più lunghi di $\sim 1500$ byte vengono frammentati a livello IP; perdita di un solo frammento $\Rightarrow$ perdita dell’intero datagramma.
- **NAT/firewall**: i datagrammi UDP attraversano i NAT con più difficoltà rispetto a TCP (timeout dei mapping più brevi).

In una chat reale UDP è accettabile per messaggi *fire-and-forget* e per la presenza online (typing indicator, heartbeat). Se serve garanzia di consegna conviene aggiungere un piccolo protocollo applicativo sopra UDP (numero di sequenza + ACK + ritrasmissione) — è il pattern del **middleware** su UDP.

### 2. Miglioramento della sicurezza

La chat dell’Esercizio 1 viaggia **in chiaro** e **non autenticata**. Possibili contromisure, in ordine di efficacia crescente:

| Livello | Tecnica | Cosa protegge |
|---|---|---|
| Trasporto | **DTLS** (TLS over UDP, RFC 6347/9147) | confidenzialità + integrità + autenticazione del canale |
| Rete | **IPsec** (AH + ESP) | confidenzialità/integrità a livello IP, trasparente all’applicazione |
| VPN | tunnel **WireGuard / OpenVPN** | incapsula tutto il traffico fra i peer |
| Applicazione | cifratura **end-to-end** con AEAD (es. `libsodium`: `crypto_secretbox` o `crypto_box`) + scambio chiavi **Diffie-Hellman** | confidenzialità + integrità + autenticazione |
| Identità | **certificati X.509** o **chiavi pubbliche** firmate | autentica i peer, blocca *man-in-the-middle* |
| Replay | **numero di sequenza + nonce + timestamp** | impedisce di rigiocare datagrammi catturati |

Esempio di pacchetto applicativo sicuro:

$$
\text{datagram} \;=\; \underbrace{\text{seq} \;\|\; \text{nonce}}_{\text{12 byte}} \;\|\; \underbrace{\text{AES-GCM}_{k}(\text{plaintext})}_{\text{ciphertext + tag}}
$$

Considerazioni aggiuntive: chiave $k$ derivata da scambio DH **all’avvio della sessione**, rotazione periodica, **PFS** (Perfect Forward Secrecy), validazione dell’indirizzo del peer (vincolare `recvfrom` a *peer noti*), eventuale firewall (Esercizio 3 di seguito).

### 3. Estensione a più peer

Tre topologie possibili:

**(a) Mesh full P2P** — ogni peer mantiene la lista `peers[] = {(ip, porta), …}`. All’invio si itera:

```python
function broadcast(msg):
    for p in peers:
        sendto(sock, msg, (p.ip, p.port))
```

Costi: $O(n)$ datagrammi per messaggio, ogni peer deve conoscere tutti gli altri (problema di **discovery**).

**(b) Multicast UDP** — si assegna un indirizzo di classe D (224.0.0.0 – 239.255.255.255), tutti i peer eseguono `setsockopt(IP_ADD_MEMBERSHIP, group)` e ricevono ogni datagramma inviato sul gruppo.

```python
group_addr = "239.1.2.3"
sock.setsockopt(IPPROTO_IP, IP_ADD_MEMBERSHIP, group_addr + iface_addr)
# invio: un solo sendto a (group_addr, port)
```

Vantaggi: **scalabilità** ($1$ datagramma indipendentemente dal numero di destinatari). Svantaggi: il multicast non è instradato sull’Internet pubblica (resta confinato alla LAN o richiede infrastruttura PIM).

**(c) Server di rendezvous** — un processo server centrale tiene la lista dei peer connessi e *ribradiata* i messaggi. Ogni peer parla solo con il server, che è anche utile per **NAT traversal** (STUN/TURN) e per la persistenza dei messaggi offline. È l’architettura usata da IRC, XMPP, Slack, Discord, ecc.

Per una piccola chat di gruppo su LAN la soluzione (b) è elegante; per una chat su Internet è realistica solo la (c).

---

## Esercizio 3 (8 pt) — Query DNS non autoritativa

> **Riferimento di teoria**: [M3/UD1/L4 — Risoluzione dei nomi](../../M3_Protocolli_applicativi/UD1/L4%20-%20Risoluzione%20dei%20nomi.md) (resolver, RR, cache vs autoritativo) · [M3/UD1/L2 — Caratteristiche del DNS](../../M3_Protocolli_applicativi/UD1/L2%20-%20Caratteristiche%20del%20DNS.md)

### Inquadramento teorico

Il **DNS** è un sistema distribuito: ogni zona è gestita da almeno un **name server autorevole** (record `NS`). Un name server è **autoritativo per una zona** se la sua copia è il *master* o uno *slave* sincronizzato e marcato come tale.

I resolver pubblici come `8.8.8.8` (Google) **non sono autoritativi** per `example.com`: si limitano a inoltrare la query (o a servirla dalla loro cache). Il flag che distingue le due risposte è il bit **AA** (*Authoritative Answer*) presente nell’header del messaggio DNS (RFC 1035, §4.1.1).

| Flag | Significato |
|---|---|
| `qr` | risposta (vs query) |
| `aa` | **Authoritative Answer** — la risposta proviene dal name server autoritativo per la zona |
| `tc` | TrunCated — risposta troncata |
| `rd` | Recursion Desired — il client chiede la ricorsione |
| `ra` | Recursion Available — il server offre ricorsione |
| `ad` | Authentic Data (DNSSEC) — risposta validata DNSSEC |
| `cd` | Checking Disabled (DNSSEC) |

### Query e risposta commentata

Eseguendo:

```bash
dig @8.8.8.8 example.com A
```

L’output tipico è:

```dns
; <<>> DiG 9.18.0 <<>> @8.8.8.8 example.com A
; (1 server found)
;; global options: +cmd
;; Got answer:
;; ->>HEADER<<- opcode: QUERY, status: NOERROR, id: 17924
;; flags: qr rd ra; QUERY: 1, ANSWER: 6, AUTHORITY: 0, ADDITIONAL: 1
                ^^^^^^^^^
                ⟵ NB: NESSUN flag "aa"

;; OPT PSEUDOSECTION:
; EDNS: version: 0, flags:; udp: 1232
;; QUESTION SECTION:
;example.com.                   IN      A

;; ANSWER SECTION:
example.com.            136     IN      A       23.220.75.232
example.com.            136     IN      A       23.220.75.245
example.com.            136     IN      A       23.215.0.136
example.com.            136     IN      A       23.215.0.138
example.com.            136     IN      A       96.7.128.198
example.com.            136     IN      A       96.7.128.175

;; Query time: 28 msec
;; SERVER: 8.8.8.8#53(8.8.8.8) (UDP)
;; WHEN: Fri May 16 12:00:00 UTC 2026
;; MSG SIZE  rcvd: 135
```

#### Lettura riga per riga

- `opcode: QUERY` ⇒ il messaggio è una query DNS standard.
- `status: NOERROR` ⇒ la risoluzione è riuscita.
- `flags: qr rd ra;` ⇒ sono settati **`qr`** (è una risposta), **`rd`** (l’abbiamo chiesta ricorsiva), **`ra`** (il server la offre).
  **Manca `aa`**: la risposta **non è autoritativa**.
- `ANSWER SECTION` ⇒ contiene 6 record `A` (IPv4) per `example.com` — il dominio è servito in *load balancing* su 6 indirizzi.
- Il campo `TTL = 136` (anziché un valore “rotondo” come $3600$, $7200$, …) indica che il record sta **scadendo nella cache**: era originariamente più alto ed è stato decrementato dal cache server — ulteriore segnale di risposta cached.
- `AUTHORITY: 0` ⇒ il server non ha incluso i record `NS` autoritativi della zona (sarebbero stati `a.iana-servers.net.`, `b.iana-servers.net.`).

### Da dove si evince che la risposta NON è autoritativa

1. **Header DNS — assenza del flag `aa`**: è la prova formale, presa direttamente dal byte di flag della risposta (bit 5 del secondo ottetto dell’header, RFC 1035).
2. In `nslookup` la stessa cosa appare come riga esplicita:
   ```
   Server:        8.8.8.8
   Address:       8.8.8.8#53

   Non-authoritative answer:
   Name:   example.com
   Address: 23.220.75.232
   ```
3. **TTL non-rotondo**: rivela che il record proviene da una cache (è stato decrementato del tempo trascorso dal fetch).
4. **Server interrogato**: `8.8.8.8` non compare nei record `NS` di `example.com` (i NS autorevoli sono `a.iana-servers.net.` e `b.iana-servers.net.`); per ottenere una risposta autoritativa bisognerebbe interrogare uno di questi:
   ```bash
   dig @a.iana-servers.net example.com A
   ;; flags: qr aa rd;          ← flag "aa" presente
   ```

### Perché è utile sapere se una risposta è autoritativa

| Motivazione | Spiegazione |
|---|---|
| **Freshness** | Una risposta autorevole rispecchia lo stato attuale della zona; una cached può essere obsoleta (fino al TTL residuo). Cruciale per chi ha appena aggiornato un record (es. cambio IP di un server). |
| **Troubleshooting** | In caso di errori di propagazione DNS bisogna confrontare ciò che dice il NS autoritativo con ciò che restituiscono i resolver — se differiscono, è in corso una propagazione. |
| **DNS hijacking / cache poisoning** | Le risposte cached sono il principale bersaglio degli attacchi di cache poisoning. Sapere se la risposta è autorevole aiuta a distinguere fra cache compromessa e zona compromessa. |
| **DNSSEC** | Solo i NS autoritativi pubblicano i record `RRSIG`. La validazione DNSSEC parte dalla risposta firmata dell’autoritativo. |
| **Security / forensics** | In contesti come *split-horizon DNS*, server autorevoli interni rispondono diversamente da quelli pubblici: per audit serve sapere quale fonte ha risposto. |
| **CDN / load balancing geo-DNS** | I CDN restituiscono IP diversi a seconda della località del resolver; sapere chi ha risposto (autorevole o cache locale) aiuta a interpretare i risultati. |

---

## Esercizio 3 (4 pt) — Regola di firewall TCP

> **Riferimento di teoria**: [M2/UD2/L1 — Internet Protocol (IP)](../../M2_Protocolli_rete_TCP_IP/UD2/L1%20-%20Internet%20Protocol%20(IP).md) (§1.6 instradamento e filtraggio dei pacchetti)

### Inquadramento

Una connessione TCP è instaurata con un **three-way handshake**:

```
Client                            Server
  │  ── SYN ───────────────────►   │
  │  ◄── SYN+ACK ───────────────   │
  │  ── ACK ────────────────────►   │
  │     ─── connessione attiva ──   │
```

Il **primo segmento** di una connessione è l’unico che porta il flag `SYN` settato e il flag `ACK` **non** settato (`SYN=1, ACK=0`). I segmenti successivi, sia in invio sia in ricezione, hanno **sempre `ACK=1`**.

> **Conseguenza operativa**: per impedire connessioni *iniziate dall’esterno*, basta scartare i pacchetti con `SYN=1 AND ACK=0` provenienti da Internet, senza toccare gli altri. Le risposte ai *nostri* SYN (cioè i SYN+ACK del server remoto) hanno `SYN=1 AND ACK=1` e quindi **non vengono colpite** dalla regola.

Un firewall che ragiona in questo modo è detto **stateless**; può comunque distinguere connessioni nuove da quelle in corso grazie ai flag TCP.

Un firewall **stateful** (detto anche *connection-tracking*) tiene una tabella delle connessioni attive e permette di esprimere la stessa politica in modo più diretto e più sicuro (copre anche segmenti riassemblati o casi limite).

### Soluzione stateless

Linguaggio neutro:

```text
INPUT  (interfaccia esterna):
    DROP    se  protocol == TCP  AND  flag SYN = 1  AND  flag ACK = 0
    ACCEPT  altrimenti
OUTPUT (interfaccia esterna):
    ACCEPT
```

Espressione `iptables` (Linux, netfilter):

```bash
# blocca i SYN (senza ACK) che arrivano dall'esterno
iptables -A INPUT  -i eth0 -p tcp --syn -j DROP

# tutto il resto (incluso SYN+ACK) è ammesso
iptables -A INPUT  -i eth0 -p tcp -j ACCEPT

# il traffico in uscita è libero
iptables -A OUTPUT -o eth0 -p tcp -j ACCEPT
```

> Il match `--syn` di iptables è l’abbreviazione di `--tcp-flags FIN,SYN,RST,ACK SYN`, ovvero “SYN settato, tutti gli altri (FIN, RST, ACK) azzerati”.

### Soluzione stateful (consigliata in produzione)

```bash
# default policy: blocco tutto in ingresso
iptables -P INPUT  DROP
iptables -P FORWARD DROP
iptables -P OUTPUT ACCEPT

# il loopback è sempre lecito
iptables -A INPUT -i lo -j ACCEPT

# accetto solo pacchetti che appartengono a connessioni
# AVVIATE DA NOI (ESTABLISHED) o correlate (RELATED, es. errori ICMP)
iptables -A INPUT  -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT

# qualunque cosa NEW in ingresso (incluso SYN) viene scartata implicitamente
# dalla policy di default
```

Cosa accade in concreto:

| Evento | Pacchetto | Stato conntrack | Esito |
|---|---|---|---|
| Esterno tenta `telnet ip 22` | `SYN` (ext → noi) | NEW | **DROP** |
| Noi facciamo `curl https://server.com` | `SYN` (noi → ext) | NEW (OUTPUT) | ACCEPT |
| Risposta del server | `SYN+ACK` (ext → noi) | ESTABLISHED | **ACCEPT** |
| Continuazione | `ACK`, `PSH+ACK`, … | ESTABLISHED | ACCEPT |
| Chiusura | `FIN+ACK` | ESTABLISHED | ACCEPT |

### Sintesi della regola richiesta

> **Regola**: scartare in ingresso ogni segmento TCP con `SYN=1, ACK=0` (ovvero **ogni primo segmento di una nuova connessione** entrante); accettare tutti gli altri segmenti TCP, sia in ingresso (es. `SYN+ACK`, `ACK`) sia in uscita.
>
> In termini *stateful*: `iptables -A INPUT -m conntrack --ctstate ESTABLISHED,RELATED -j ACCEPT`, con policy di `INPUT` impostata a `DROP`.

Questa è una delle politiche più diffuse: **“out OK, in solo se è risposta a un nostro out”** — è la base concettuale del NAT residenziale e del modello di sicurezza della maggior parte dei firewall personali.

---

## Riferimenti al corso

Per ogni esercizio, i materiali di studio rilevanti del modulo:

- TCP CWND / SSTHRESH / Slow Start / Congestion Avoidance: `M2/UD5/L8`, `M2/UD5/L9`, `M2/UD5/L10`, `M2/UD5/L11`.
- UDP, header, confini di messaggio: `M2/UD6/L1`, `M2/UD6/L2`.
- Subnetting, FLSM/VLSM, routing table, ARP: `M2/UD2/L1`, `M2/UD2/L2`, `M2/UD2/L4`, `M2/UD3/L3`.
- Socket UDP, `fork()`, comunicazione client-server: `M4/UD1/L2`, `M4/UD1/L3`, `M4/UD1/L4`.
- DNS, resolver, autoritativo vs cache: `M3/UD1/L2`, `M3/UD1/L4`.
- TCP setup / flag SYN / firewall: `M2/UD5/L5_1`, `M4/UD1/L3`.

---

*Fine soluzione.*
