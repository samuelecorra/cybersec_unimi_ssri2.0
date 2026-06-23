# Soluzione Esame di Reti di Calcolatori — Parte A — 23/05/2025

**Prof. Ernesto Damiani**

---

## Esercizio 1 — Andamento CWND e SSTHRESH (TCP)

**Esercizio 1 (5 punti)** Un'applicazione A deve trasferire 128 kbyte all'applicazione B utilizzando il protocollo TCP. Si supponga che la connessione sia già stata instaurata. Le variabili note sono le seguenti: MSS concordata pari a 1000 byte; RCVWND annunciata pari a 32 kbyte; SSTHRESH = RCVWND/2; RTT costante pari a 0.5 secondi. Si supponga che RTT vari da 0.5 a 0.8 sec dopo 3 secondi dall'inizio della trasmissione. Determinate con un grafico l'andamento della CWND e il valore finale della CWND e della SSTHRESH.

> **Riferimento di teoria**: [M2/UD5/L9 — Flusso e congestione TCP](../../M2_Protocolli_rete_TCP_IP/UD5/L9%20-%20Flusso%20e%20congestione%20TCP.md)

---

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

**Esercizio 2 (5 punti)** Un client usa UDP per inviare a un server remoto i valori di temperatura rilevati da un sensore rappresentati come coppie di interi, uno per la parte intera e l'altro per la parte decimale. Il client manda ogni valore al server in un datagramma separato. Supponiamo di monitorare il traffico in transito tra client e server, e che il sensore generi i valori 2.3 e 4.1. Rappresentate con un diagramma i datagrammi UDP generati evidenziando il payload di ciascuno.

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

**Esercizio 3 (20 punti)** I cinque router (R1-R5), riportati in figura, sono forniti di schede Ethernet e sono fra loro connessi mediante link punto a punto. Ai router sono collegate delle LAN indicate in figura con i nomi LAN 1, LAN2, LAN 4, LAN5.

![](imgs/Pasted%20image%2020260623184328.png)

Avete a disposizione il seguente range di indirizzi IP pubblici da 194.1.1.17 a 194.1.1.255:

a. assegnate gli indirizzi IP, subnet mask, network address e broadcast address per le LAN 1,2,4,5, a partire dai valori inferiori del range che avete a disposizione.
b. usando gli indirizzi rimasti del range assegnate gli indirizzi IP alle schede di rete dei collegamenti punto-punto, specificando anche, per ognuno di tali link, network address, subnet mask e broadcast address. Nota: indicate ogni link con il nome "link Rx-Ry" ove x e y sono i due router collegati.
c. indicate gli indirizzi IP non utilizzati.
d. fornite la configurazione, per il router 3, della tabella di routing (destination network, subnet mask, gateway, interface).
e. specificate il trattamento effettuato dai layer IP ed Ethernet di un host della LAN 1 quando deve instradare un pacchetto che ha, come indirizzo IP di destinazione, l'indirizzo di un host della LAN 5.

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
