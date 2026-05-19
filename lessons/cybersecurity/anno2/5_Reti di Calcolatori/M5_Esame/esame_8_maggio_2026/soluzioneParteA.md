# Soluzione Esame di Reti di Calcolatori — Parte A — 8/05/2026

**Prof. Ernesto Damiani**

Per ogni esercizio è indicato il riferimento alle lezioni di teoria del corso e l'inquadramento concettuale, lo svolgimento dettagliato e — dove richiesto — diagrammi e calcoli.

---

## Indice

- [Esercizio 1 — Tempo di trasferimento di 1 GB via TCP su Ethernet 100 Mbps](#esercizio-1--tempo-di-trasferimento-di-1-gb-via-tcp-su-ethernet-100-mbps)
- [Esercizio 2 — Routing con Longest Prefix Match](#esercizio-2--routing-con-longest-prefix-match)
- [Esercizio 3 — VLSM su 193.204.176.0/22 + switching L2 + ARP](#esercizio-3--vlsm-su-1932041760022--switching-l2--arp)
- [Esercizio 4 — Tabella di instradamento del Router A](#esercizio-4--tabella-di-instradamento-del-router-a)

---

## Esercizio 1 — Tempo di trasferimento di 1 GB via TCP su Ethernet 100 Mbps

> **Riferimenti di teoria**:
>
> - [M1/UD2/L4 — Ethernet – Frame e CSMA-CD](../../M1_Tecniche_comunicazione_digitale/UD2/L4%20-%20Ethernet%20%E2%80%93%20Frame%20e%20CSMA-CD.md) (struttura frame, lunghezze massime, overhead)
> - [M2/UD5/L2 — Efficienza e controllo d'errore](../../M2_Protocolli_rete_TCP_IP/UD5/L2%20-%20Efficienza%20e%20controllo%20d%E2%80%99errore.md) (formula $T_{ix} = \text{bit}/\text{bps}$, efficienza, $\alpha = T_p/T_{ix}$)
> - [M2/UD5/L9 — Flusso e congestione TCP](../../M2_Protocolli_rete_TCP_IP/UD5/L9%20-%20Flusso%20e%20congestione%20TCP.md) (slow start, CWND, RTT)
> - [M2/UD6/L2 — UDP e TCP a confronto](../../M2_Protocolli_rete_TCP_IP/UD6/L2%20-%20UDP%20e%20TCP%20a%20confronto.md) (header TCP = 20 byte, overhead)

---

**Esercizio 1 (5 punti)**

Calcolate il tempo richiesto in secondi per trasferire via TCP un file di 1 Gbyte tra un computer A e un computer B attraverso un cavo Ethernet 100 Mbps che collega direttamente i due computer. Fate tutte le ipotesi necessarie per rendere la vostra analisi il più precisa possibile.

---

### Inquadramento teorico

Sul tempo totale di trasferimento di un file $F$ via TCP intervengono **tre famiglie di contributi**:

1. **Tempo di trasmissione "ideale"** $T_{ix}^{ideal} = F / R$, dove $R$ è la bit-rate fisica (cfr. M2/UD5/L2).
2. **Overhead di protocollo** dei livelli sottostanti (Ethernet, IP, TCP).
3. **Latenza di setup TCP** (three-way handshake) + **fase di slow start** del controllo di congestione (cfr. M2/UD5/L9). Su Ethernet diretto fra due PC, l'RTT è dell'ordine dei microsecondi: questi contributi sono **trascurabili** rispetto agli 80 secondi di trasmissione effettiva.

### Ipotesi adottate (esplicite, come richiede il testo)

| Parametro                         | Valore                                                                            | Giustificazione                                                                                                      |
| --------------------------------- | --------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| Bit-rate Ethernet                 | $R = 100\,\text{Mbps} = 10^8\,\text{bit/s}$                                       | Specifica nel testo (Fast Ethernet, IEEE 802.3u)                                                                     |
| Cavo punto-punto, **full-duplex** | sì                                                                                | Connessione "direttamente" fra A e B → niente CSMA/CD, niente collisioni (cfr. M1/UD2/L4 §10 → switched/full-duplex) |
| Lunghezza del cavo                | trascurabile (es. 100 m)                                                          | $T_p \approx 100\,m / 2{,}0 \cdot 10^8\,m/s \approx 0{,}5\,\mu s$                                                    |
| Dimensione file                   | $F = 1\,\text{GB} = 10^9\,\text{byte} = 8 \cdot 10^9\,\text{bit}$ (potenza di 10) | Convenzione "commerciale"; il testo usa "Gbyte"                                                                      |
| **MTU Ethernet**                  | $1500$ byte (payload IP+TCP+dati)                                                 | Cfr. M1/UD2/L4 §6                                                                                                    |
| Header Ethernet                   | $14$ byte (Dst MAC + Src MAC + Type) + $4$ byte CRC                               | Cfr. M1/UD2/L4 §2                                                                                                    |
| Preambolo + SFD                   | $8$ byte                                                                          | non conta nei "bit del frame" ai fini RFC; lo includiamo nell'**inter-frame overhead**                               |
| Inter-Frame Gap                   | $96$ bit ($9{,}6\,\mu s$ a 100 Mbps)                                              | Cfr. M1/UD2/L4 §9 — back-to-back delay                                                                               |
| Header IP                         | $20$ byte (no opzioni)                                                            | Cfr. M2/UD2/L1                                                                                                       |
| Header TCP                        | $20$ byte (no opzioni)                                                            | Cfr. M2/UD6/L2                                                                                                       |
| **MSS**                           | $1500 - 20 - 20 = 1460$ byte                                                      | dati TCP utili per segmento                                                                                          |
| RTT                               | $\sim 0{,}5$ ms (stima generosa per cavo diretto)                                 | trascurabile rispetto a 80 s                                                                                         |

### Calcolo del payload utile per frame

Ogni frame Ethernet contiene:

$$
\underbrace{14\,\text{B}}_{\text{Eth header}} + \underbrace{20\,\text{B}}_{\text{IP}} + \underbrace{20\,\text{B}}_{\text{TCP}} + \underbrace{1460\,\text{B}}_{\text{dati utili}} + \underbrace{4\,\text{B}}_{\text{CRC}} = 1518\,\text{byte}
$$

Aggiungendo preambolo (8 B) + IFG (12 B), il "costo" totale per frame è $1518 + 8 + 12 = 1538\,\text{byte}$.

### Efficienza di protocollo

$$
\eta \;=\; \frac{1460}{1538} \;\approx\; 0{,}949
$$

Quindi il **goodput utile** è:

$$
R_{utile} \;=\; \eta \cdot R \;\approx\; 0{,}949 \cdot 100\,\text{Mbps} \;\approx\; 94{,}9\,\text{Mbps}
$$

### Tempo di trasferimento

$$
T \;=\; \frac{F}{R_{utile}} \;=\; \frac{8 \cdot 10^9\,\text{bit}}{0{,}949 \cdot 10^8\,\text{bit/s}} \;\approx\; \mathbf{84{,}3\;\text{secondi}}
$$

### Approssimazione "spannometrica" (utile in compito)

Se trascuriamo gli overhead (overhead < 5%):

$$
T_{ideale} \;=\; \frac{F}{R} \;=\; \frac{8 \cdot 10^9}{10^8} \;=\; \mathbf{80\;\text{secondi}}
$$

Aggiungendo $\sim 5\%$ di overhead di protocollo:

$$
\boxed{\;T \;\approx\; 80 \text{–} 85\;\text{secondi}\;}
$$

### Contributo del setup TCP e dello slow start

- Three-way handshake: $1{,}5 \cdot RTT \approx 0{,}75$ ms.
- Slow start: $\text{CWND}$ parte da $1\,\text{MSS}$ e raddoppia ad ogni RTT (cfr. M2/UD5/L9). Per saturare $100\,\text{Mbps}$ servono $\sim 8\,\text{frame in volo}$ (BDP $= R \cdot RTT = 10^8 \cdot 5 \cdot 10^{-4} = 5 \cdot 10^4\,\text{bit} = 6{,}25\,\text{KB} \approx 4\,\text{MSS}$). Si raggiunge in $\sim 3$ RTT $\approx 1{,}5$ ms.

Su una scala di 80 secondi, sono **circa $2\,$ms su 84\,300 ms**, ossia **trascurabili** ($<0{,}003\%$). Per analisi più precise (es. reti geografiche), il modello esatto è:

$$
T \;\approx\; \underbrace{1{,}5 \cdot RTT}_{\text{handshake}} \;+\; \underbrace{\log_2\!\left(\frac{R\cdot RTT}{MSS}\right)\cdot RTT}_{\text{slow start}} \;+\; \underbrace{\frac{F}{R_{utile}}}_{\text{steady state}}
$$

ma su un cavo diretto come quello del testo i primi due termini sono nel rumore.

### Risultato finale

$$
\boxed{\;T \;\approx\; 84{,}3\;\text{secondi}\;}
$$

(o $80\,\text{s}$ se si trascura ogni overhead — risposta accettata in compito se l'ipotesi è esplicita).

---

## Esercizio 2 — Routing con Longest Prefix Match

> **Riferimenti di teoria**:
>
> - [M2/UD3/L1 — Introduzione all'instradamento IP](../../M2_Protocolli_rete_TCP_IP/UD3/L1%20-%20Introduzione%20all%E2%80%99instradamento%20IP.md) (Longest Prefix Match, struttura della tabella, default route)
> - [M2/UD2/L3 — Complementi di IP](../../M2_Protocolli_rete_TCP_IP/UD2/L3%20-%20Complementi%20di%20IP.md) (CIDR, maschere variabili)
> - [M2/UD2/L1 — Internet Protocol (IP)](<../../M2_Protocolli_rete_TCP_IP/UD2/L1%20-%20Internet%20Protocol%20(IP).md>) (operazione AND bit-a-bit)

---

**Esercizio 2 (8 punti)**

Considerando la seguente tabella di instradamento

| Dest. prefix    | Next Hop   |
| --------------- | ---------- |
| 192.24.1.0/24   | direct     |
| 192.24.192.0/18 | 192.24.1.5 |
| 0.0.0.0         | 192.24.1.1 |

Mostrate i dettagli del calcolo eseguito per instradare pacchetti indirizzati all'host 192.24.128.5 e all'host 192.24.1.5.

---
### Inquadramento teorico

Quando un router riceve un pacchetto con `dst IP = D`, esegue questa procedura (cfr. M2/UD3/L1, §6–9):

1. Per **ogni riga** della tabella calcola $D \land \text{mask}_i$.
2. Confronta il risultato con $\text{net\_id}_i$ → `match_i = 1` se coincidono.
3. Tra tutte le righe con `match_i = 1`, sceglie quella con **prefisso più lungo** (più bit a 1 nella maschera) → **Longest Prefix Match**.
4. Inoltra al `Next Hop` indicato.

La riga `0.0.0.0` ha $\text{mask}=0.0.0.0$ e matcha sempre, ma con priorità minima: è la **default route**.

### Tabella di instradamento data

| Dest. prefix    | Mask (esplicita) | Next Hop   | Prefix len |
| --------------- | ---------------- | ---------- | ---------: |
| 192.24.1.0/24   | 255.255.255.0    | direct     |        /24 |
| 192.24.192.0/18 | 255.255.192.0    | 192.24.1.5 |        /18 |
| 0.0.0.0         | 0.0.0.0          | 192.24.1.1 |         /0 |

Per la riga `192.24.192.0/18`, espandiamo la maschera per evitare ambiguità:

$$
192 \;=\; \mathtt{1100\,0000_2}
$$

Il prefisso `192.24.192.0/18` copre il terzo ottetto dal valore `11000000` a `11111111`, ovvero dal **192 al 255**. In notazione decimale, copre tutti gli IP della forma `192.24.X.Y` con $X \in [192,\,255]$ → range $192.24.192.0 \to 192.24.255.255$.

---

### Caso (a) — Pacchetto verso `192.24.128.5`

#### Riga 1: `192.24.1.0/24`, mask `255.255.255.0`

$$
192.24.128.5 \;\land\; 255.255.255.0 \;=\; 192.24.128.0
$$

$$
192.24.128.0 \;\neq\; 192.24.1.0 \quad \Rightarrow\quad \textbf{NO MATCH}
$$

#### Riga 2: `192.24.192.0/18`, mask `255.255.192.0`

Conversione binaria del terzo ottetto:

$$
128 \;=\; \mathtt{1000\,0000_2}, \quad 192 \;=\; \mathtt{1100\,0000_2}
$$

AND bit-a-bit:

$$
\mathtt{1000\,0000_2} \;\land\; \mathtt{1100\,0000_2} \;=\; \mathtt{1000\,0000_2} \;=\; 128
$$

Quindi:

$$
192.24.128.5 \;\land\; 255.255.192.0 \;=\; 192.24.128.0
$$

$$
192.24.128.0 \;\neq\; 192.24.192.0 \quad \Rightarrow\quad \textbf{NO MATCH}
$$

#### Riga 3 (default): `0.0.0.0/0`, mask `0.0.0.0`

$$
192.24.128.5 \;\land\; 0.0.0.0 \;=\; 0.0.0.0 \;=\; 0.0.0.0 \quad \Rightarrow\quad \textbf{MATCH (default)}
$$

#### Selezione

Tra i match, vince quello con prefisso più lungo: qui solo la default ha matchato → si usa **next hop `192.24.1.1`**. Poiché `192.24.1.1` appartiene alla rete direttamente connessa `192.24.1.0/24`, il pacchetto viene consegnato in **un solo hop** sull'interfaccia "direct".

> Nota concettuale: il pacchetto destinato a `192.24.128.5` viene rispedito al **gateway di default** (probabilmente un router di confine verso Internet), perché il prefisso `/18` copre solo `192.24.192.0–192.24.255.255` e quindi **NON contiene** `192.24.128.5`.

$$
\boxed{\;\text{Inoltra a Next Hop } 192.24.1.1 \;\text{ via rotta direct (rete } 192.24.1.0/24)\;}
$$

---

### Caso (b) — Pacchetto verso `192.24.1.5`

#### Riga 1: `192.24.1.0/24`, mask `255.255.255.0`

$$
192.24.1.5 \;\land\; 255.255.255.0 \;=\; 192.24.1.0 \quad \Rightarrow\quad \textbf{MATCH (/24)}
$$

#### Riga 2: `192.24.192.0/18`, mask `255.255.192.0`

Terzo ottetto: $1 = \mathtt{0000\,0001_2}$.

$$
\mathtt{0000\,0001_2} \;\land\; \mathtt{1100\,0000_2} \;=\; \mathtt{0000\,0000_2} \;=\; 0
$$

$$
192.24.1.5 \;\land\; 255.255.192.0 \;=\; 192.24.0.0 \;\neq\; 192.24.192.0 \quad \Rightarrow\quad \textbf{NO MATCH}
$$

#### Riga 3 (default): matcha sempre.

#### Selezione (Longest Prefix Match)

Match attivi: `/24` (riga 1) e `/0` (default). Vince la più specifica → **`/24`**.

$$
\boxed{\;\text{Inoltra direttamente (rete direct } 192.24.1.0/24\text{): consegna L2 a } 192.24.1.5\;}
$$

Poiché la rotta è "direct", il router risolve via ARP il MAC di `192.24.1.5` sulla LAN locale e incapsula il pacchetto IP in un frame Ethernet (cfr. M2/UD3/L3).

---

### Sintesi

| Destinazione   | Riga vincente       | Maschera        | Next Hop     | Modalità                   |
| -------------- | ------------------- | --------------- | ------------ | -------------------------- |
| `192.24.128.5` | default `0.0.0.0/0` | `0.0.0.0`       | `192.24.1.1` | indiretta (via gw default) |
| `192.24.1.5`   | `192.24.1.0/24`     | `255.255.255.0` | direct       | diretta (ARP locale)       |

---

## Esercizio 3 — VLSM su 193.204.176.0/22 + switching L2 + ARP

> **Riferimenti di teoria**:
>
> - [M2/UD2/L4 — Progetto di inter-reti](../../M2_Protocolli_rete_TCP_IP/UD2/L4%20-%20Progetto%20di%20inter-reti.md) (FLSM/VLSM, fasi progettuali)
> - [M2/UD2/L3 — Complementi di IP](../../M2_Protocolli_rete_TCP_IP/UD2/L3%20-%20Complementi%20di%20IP.md) (CIDR, supernetting)
> - [M2/UD2/L1 — Internet Protocol (IP)](<../../M2_Protocolli_rete_TCP_IP/UD2/L1%20-%20Internet%20Protocol%20(IP).md>) (struttura indirizzi)
> - [M1/UD2/L6 — Switched Ethernet](../../M1_Tecniche_comunicazione_digitale/UD2/L6%20-%20Switched%20Ethernet.md) (tabella MAC, CAM)
> - [M1/UD2/L7 — VLAN e Trunking](../../M1_Tecniche_comunicazione_digitale/UD2/L7%20-%20VLAN%20e%20Trunking.md) (VLAN ID, 802.1Q, router-on-a-stick)
> - [M2/UD3/L3 — Address Resolution Protocol (ARP)](<../../M2_Protocolli_rete_TCP_IP/UD3/L3%20-%20Address%20Resolution%20Protocol%20(ARP).md>) (ARP request/reply, frame Ethernet, broadcast)

---

**Esercizio 3 (12 punti)**

- Dato il blocco CIDR 193.204.176.0/22, sub-nettatelo con la tecnica VLSM per creare due sottoreti A, B e due collegamenti IP punto-punto: A-B, B-Internet. Precisate quanti router utilizzerete, a quali sottoreti saranno connessi. Per ciascuna sottorete fornite subnetID, intervallo d'indirizzamento e indirizzo di broadcast.
- Fornite un progetto di switching a livello 2 che supporti il piano d'indirizzamento IP usando due switch, lasciando su ciascuno switch porte di entrambe le reti. Specificate la configurazione degli switch, e la tecnica di connessione dei router agli switch.
- Simulate una richiesta ARP fatta da un host sulla rete A, facendo le opportune ipotesi e mostrando tutti i frame generati.

---

### a) VLSM: piano di indirizzamento

#### Spazio di partenza

$$
193.204.176.0/22 \;\Rightarrow\; 2^{32-22} = 2^{10} = 1024 \text{ indirizzi}
$$

Range completo: `193.204.176.0 → 193.204.179.255`.

Notiamo che il bit di confine `/22` lavora sul terzo ottetto: i primi 6 bit (di `176 = 10110000`) sono fissi, gli ultimi 2 bit del terzo ottetto e l'intero quarto ottetto sono "host-id". Quindi il prefisso copre `193.204.176.0` … `193.204.179.255` (cioè terzo ottetto da 176 a 179 incluso).

#### Requisiti

- **2 LAN** (A e B): grandi, host non specificati → assegniamo blocchi ampi (/24, 254 host utili ciascuno);
- **2 link punto-punto** (A↔B, B↔Internet): bastano 2 indirizzi utili ciascuno → **/30** (4 indirizzi, 2 utili).

#### Numero di router e topologia

Servono **2 router** (`R_A` e `R_B`). `R_A` connette LAN A e il link A-B; `R_B` connette LAN B, il link A-B e il link verso Internet (ISP).

```
   ┌──────── LAN A ────────┐                ┌──────── LAN B ────────┐
   │      193.204.176.0/24 │                │   193.204.177.0/24    │
   │                       │                │                       │
   │     host_A1 host_A2…  │                │  host_B1 host_B2…     │
   └──────────┬────────────┘                └───────────┬───────────┘
              │ (eth0)                                  │ (eth0)
              │193.204.176.1                          193.204.177.1
        ┌─────┴────┐                              ┌─────┴────┐
        │   R_A    │     193.204.178.0/30         │   R_B    │
        │          ├───── point-to-point ─────────┤          │
        │          │ .1                        .2 │          │
        └──────────┘                              └────┬─────┘
                                                       │
                                                  193.204.178.4/30 (link B-ISP)
                                                       │ .1
                                                       ▼
                                                  ┌──────┐
                                                  │ ISP  │ .2
                                                  └──────┘
                                                       │
                                                       ▼
                                                   Internet
```

| Router     | Connesso a | Interfaccia | Indirizzo          |
| ---------- | ---------- | ----------- | ------------------ |
| R_A        | LAN A      | eth0        | `193.204.176.1/24` |
| R_A        | link A-B   | eth1        | `193.204.178.1/30` |
| R_B        | LAN B      | eth0        | `193.204.177.1/24` |
| R_B        | link A-B   | eth1        | `193.204.178.2/30` |
| R_B        | link B-ISP | eth2        | `193.204.178.5/30` |
| ISP (edge) | link B-ISP | —           | `193.204.178.6/30` |

#### Tabella riassuntiva delle sottoreti

| Sottorete           | Notazione CIDR     | Maschera          | Subnet-ID       | Broadcast         | Range host utili                               |
| ------------------- | ------------------ | ----------------- | --------------- | ----------------- | ---------------------------------------------- |
| **LAN A**           | `193.204.176.0/24` | `255.255.255.0`   | `193.204.176.0` | `193.204.176.255` | `193.204.176.1` – `193.204.176.254` (254 host) |
| **LAN B**           | `193.204.177.0/24` | `255.255.255.0`   | `193.204.177.0` | `193.204.177.255` | `193.204.177.1` – `193.204.177.254` (254 host) |
| **link A-B**        | `193.204.178.0/30` | `255.255.255.252` | `193.204.178.0` | `193.204.178.3`   | `193.204.178.1` (R_A), `193.204.178.2` (R_B)   |
| **link B-Internet** | `193.204.178.4/30` | `255.255.255.252` | `193.204.178.4` | `193.204.178.7`   | `193.204.178.5` (R_B), `193.204.178.6` (ISP)   |

**Spazio residuo libero**: da `193.204.178.8` a `193.204.179.255` → $\sim 504$ indirizzi disponibili per espansioni future (utile per nuove LAN o link).

> Notare l'**efficienza VLSM** (cfr. M2/UD2/L4 §3.2): i link punto-punto consumano solo 4 indirizzi ciascuno invece dei 256 che spreca un FLSM con /24 ovunque.

---

### b) Progetto di switching a livello 2 (due switch, porte per entrambe le reti)

> **Riferimenti**: [M1/UD2/L6 — Switched Ethernet](../../M1_Tecniche_comunicazione_digitale/UD2/L6%20-%20Switched%20Ethernet.md), [M1/UD2/L7 — VLAN e Trunking](../../M1_Tecniche_comunicazione_digitale/UD2/L7%20-%20VLAN%20e%20Trunking.md).

#### Idea progettuale

Lasciare "su ciascuno switch porte di entrambe le reti" significa che, per ragioni di cablaggio (es. host di LAN A e host di LAN B distribuiti nei vari piani dell'edificio), uno stesso switch fisico ospita porte di **due reti logicamente diverse**. La tecnica corretta è quella delle **VLAN** (cfr. M1/UD2/L7 §1–3):

- **VLAN 10** → LAN A (`193.204.176.0/24`)
- **VLAN 20** → LAN B (`193.204.177.0/24`)

#### Topologia di switching

```
                          ┌─────────────┐
                          │   R_A       │
                          │   (vlan 10) │
                          └──┬──────────┘
                             │ (untagged: VLAN 10)
                             ▼
       ┌──────────────────┐ porta 1
       │      SW1         │
       │  vlan 10: 2-12   │◄────── trunk 802.1Q ──────► ┌──────────────────┐
       │  vlan 20: 13-24  │       (porte 24 ↔ 24)      │      SW2         │
       └──────────────────┘                            │  vlan 10: 2-12   │
                                                        │  vlan 20: 13-23  │
                                                        └──────────────────┘
                                                                  ▲ (untagged: VLAN 20)
                                                                  │
                                                            ┌─────┴────┐
                                                            │   R_B    │
                                                            │ (vlan 20)│
                                                            └──────────┘
```

#### Configurazione degli switch (sintassi Cisco-like, indicativa)

```cisco
! ───── SW1 ─────
vlan 10
 name LAN_A
vlan 20
 name LAN_B
!
! Porta di accesso → host LAN A
interface range Fa0/2 - 12
 switchport mode access
 switchport access vlan 10
!
! Porta di accesso → host LAN B
interface range Fa0/13 - 23
 switchport mode access
 switchport access vlan 20
!
! Porta verso R_A (untagged VLAN 10)
interface Fa0/1
 switchport mode access
 switchport access vlan 10
!
! Porta di trunk verso SW2 (trasporta entrambe le VLAN)
interface Fa0/24
 switchport mode trunk
 switchport trunk encapsulation dot1q
 switchport trunk allowed vlan 10,20
```

Su `SW2` la configurazione è speculare, con la sola differenza che la porta verso `R_B` è in modalità **access su VLAN 20**.

#### Tecnica di connessione dei router agli switch

Due opzioni canoniche (cfr. M1/UD2/L7 §9):

1. **Una porta per router** (semplice, scelta qui):
   - `R_A` è collegato a una porta access di SW1 in VLAN 10.
   - `R_B` è collegato a una porta access di SW2 in VLAN 20.
   - Il traffico inter-VLAN passa per il link `R_A`–`R_B` (link punto-punto `193.204.178.0/30`).

2. **Router-on-a-stick** (alternativa, più compatta):
   - Un unico router con sub-interfacce VLAN-aware, collegato a una porta di trunk.
   - Pro: un solo router; contro: bottleneck del trunk.

#### Tabella MAC negli switch (CAM)

Ogni switch impara dinamicamente le coppie MAC↔porta osservando i frame in transito (cfr. M1/UD2/L6 §4–5):

```
SW1 — CAM Table
┌──────────────────────┬──────┬──────┐
│ MAC                  │ Vlan │ Port │
├──────────────────────┼──────┼──────┤
│ 00:1A:2B:00:00:01   │  10  │ Fa0/1 │   ← R_A
│ 00:1A:2B:00:00:11   │  10  │ Fa0/2 │   ← host_A1
│ 00:1A:2B:00:00:12   │  10  │ Fa0/3 │   ← host_A2
│ …                   │  …   │  …    │
│ 00:1A:2B:00:00:21   │  20  │ Fa0/13│   ← host_B (lato SW1)
└──────────────────────┴──────┴──────┘
```

Il link di trunk porta i frame **etichettati** con tag 802.1Q (4 byte aggiuntivi tra Src MAC e Type, cfr. M1/UD2/L7 §8).

---

### c) Simulazione di una richiesta ARP da un host di LAN A

> **Riferimento**: [M2/UD3/L3 — ARP](<../../M2_Protocolli_rete_TCP_IP/UD3/L3%20-%20Address%20Resolution%20Protocol%20(ARP).md>).

#### Scenario

`host_A1` (`193.204.176.10`, MAC `00:1A:2B:00:00:11`) vuole inviare un ping (`ICMP Echo`) a `host_A2` (`193.204.176.20`, MAC `00:1A:2B:00:00:12`). Entrambi sono in LAN A, quindi l'instradamento è **diretto** (cfr. M2/UD3/L1 §2–4):

$$
193.204.176.10 \;\land\; 255.255.255.0 \;=\; 193.204.176.0 \;=\; 193.204.176.20 \;\land\; 255.255.255.0
$$

Stesso subnet → consegna L2 diretta, serve il **MAC** del destinatario.

#### Ipotesi

- La **cache ARP** di `host_A1` è vuota per `host_A2`.
- Entrambi gli host sono in VLAN 10 (LAN A) ma su switch diversi (`host_A1` su SW1, `host_A2` su SW2 — passa per il trunk).

#### Frame 1 — ARP Request (broadcast)

Generato da `host_A1`, incapsulato direttamente in un frame Ethernet (cfr. M2/UD3/L3 §1.3 — `EtherType = 0x0806`):

```
┌─────────────────────────────────────────────────────────────────┐
│  Ethernet Frame (broadcast)                                      │
├──────────────────────────┬──────────────────────────────────────┤
│ Preambolo + SFD          │ 8 byte                                │
├──────────────────────────┼──────────────────────────────────────┤
│ Dst MAC                  │ FF:FF:FF:FF:FF:FF      (broadcast)    │
│ Src MAC                  │ 00:1A:2B:00:00:11      (host_A1)      │
│ EtherType                │ 0x0806                  (ARP)         │
├──────────────────────────┴──────────────────────────────────────┤
│ ARP Payload:                                                     │
│   HTYPE = 1                       (Ethernet)                     │
│   PTYPE = 0x0800                  (IPv4)                          │
│   HLEN  = 6                       (lunghezza MAC)                │
│   PLEN  = 4                       (lunghezza IP)                 │
│   OPER  = 1                       (ARP Request)                  │
│   Sender MAC  = 00:1A:2B:00:00:11 (host_A1)                      │
│   Sender IP   = 193.204.176.10                                   │
│   Target MAC  = 00:00:00:00:00:00 (sconosciuto)                  │
│   Target IP   = 193.204.176.20                                   │
├─────────────────────────────────────────────────────────────────┤
│ CRC                                                              │
└─────────────────────────────────────────────────────────────────┘
```

#### Cammino del frame negli switch

1. **SW1** riceve il frame su `Fa0/2` (porta di host_A1).
   - Aggiorna la CAM: `00:1A:2B:00:00:11 ↔ Fa0/2 (VLAN 10)`.
   - DST = broadcast → **flooding** su tutte le altre porte di **VLAN 10**:
     - `Fa0/1` (R_A) — riceve il frame ma essendo per IP diverso lo scarta;
     - `Fa0/3..12` (altri host LAN A su SW1);
     - `Fa0/24` (trunk verso SW2) — il frame viene **taggato 802.1Q VLAN 10**.

2. **SW2** riceve il frame taggato sul trunk.
   - Rimuove il tag (è esterno alle porte di accesso).
   - Aggiorna CAM: `00:1A:2B:00:00:11 ↔ Fa0/24 (VLAN 10)`.
   - Flooda su tutte le porte VLAN 10 (eccetto il trunk).
   - Su `Fa0/3` (host_A2) il frame arriva.

3. **host_A2** riceve il broadcast, legge l'ARP payload, vede che `Target IP = 193.204.176.20` coincide con il proprio, e prepara una **ARP Reply** unicast.

> Tutti gli altri host della VLAN 10 ignorano il broadcast (il loro IP non corrisponde). Gli host della VLAN 20 **non ricevono il frame** perché lo switch isola i domini broadcast per VLAN (cfr. M1/UD2/L7 §3).

#### Frame 2 — ARP Reply (unicast)

Generato da `host_A2`:

```
┌─────────────────────────────────────────────────────────────────┐
│  Ethernet Frame (unicast)                                        │
├──────────────────────────┬──────────────────────────────────────┤
│ Dst MAC                  │ 00:1A:2B:00:00:11     (host_A1)       │
│ Src MAC                  │ 00:1A:2B:00:00:12     (host_A2)       │
│ EtherType                │ 0x0806                (ARP)            │
├──────────────────────────┴──────────────────────────────────────┤
│ ARP Payload:                                                     │
│   HTYPE = 1, PTYPE = 0x0800, HLEN = 6, PLEN = 4                  │
│   OPER  = 2                        (ARP Reply)                   │
│   Sender MAC = 00:1A:2B:00:00:12   (host_A2)                     │
│   Sender IP  = 193.204.176.20                                    │
│   Target MAC = 00:1A:2B:00:00:11   (host_A1)                     │
│   Target IP  = 193.204.176.10                                    │
└─────────────────────────────────────────────────────────────────┘
```

#### Cammino di ritorno

1. SW2 riceve il frame su `Fa0/3`, consulta la CAM, trova `00:1A:2B:00:00:11 ↔ Fa0/24` → inoltra **solo** sul trunk (taggato VLAN 10).
2. SW1 riceve il frame taggato, rimuove il tag, consulta la CAM, trova `00:1A:2B:00:00:11 ↔ Fa0/2` → inoltra solo su quella porta.
3. `host_A1` riceve l'ARP Reply, aggiorna la propria cache ARP:
   ```
   193.204.176.20 → 00:1A:2B:00:00:12 (Fa0/2)
   ```

#### Conseguenza: il ping può partire

`host_A1` invia ora un **frame Ethernet con payload IP+ICMP** verso `00:1A:2B:00:00:12`, senza più broadcast. Le richieste successive sono unicast finché la cache ARP non scade (di norma 60–300 s).

> Riepilogo: **2 frame Ethernet** generati dall'ARP — uno in broadcast (request) e uno in unicast (reply). Il broadcast attraversa il trunk con tag 802.1Q ma resta confinato alla VLAN 10. La VLAN 20 (LAN B) non vede traffico ARP perché è un dominio broadcast separato.

---

## Esercizio 4 — Tabella di instradamento del Router A

> **Riferimenti di teoria**:
>
> - [M2/UD3/L1 — Introduzione all'instradamento IP](../../M2_Protocolli_rete_TCP_IP/UD3/L1%20-%20Introduzione%20all%E2%80%99instradamento%20IP.md) (struttura tabella, rotte dirette/indirette/default)
> - [M2/UD2/L4 — Progetto di inter-reti](../../M2_Protocolli_rete_TCP_IP/UD2/L4%20-%20Progetto%20di%20inter-reti.md) (assegnazione indirizzi alle interfacce)

---

**Esercizio 4 (5 punti)**

Data la rete in figura, specificate la tabella di instradamento per il router A.

![](imgs/Pasted%20image%2020260519141502.png)

**Interfacce:**

- **Router B:** 10.0.1.2/24 eth0 | 10.0.2.2/24 eth1
- **Host A:** 10.0.3.1/24 eth0
- **Host B:** 10.0.3.2/24 eth0 (collegato a Router B eth1 side)
- **Router A:** 10.0.1.1/24 eth0 | 100.4.1.24 d0 | 10.0.2.1/24 eth1 | 10.0.1.2/24 eth0

> _Nota: la topologia nell'immagine originale mostra Host A e Host B collegati rispettivamente a Router B e Router A tramite diverse interfacce, con i due router interconnessi. Gli indirizzi esatti come visibili dall'immagine sono:_
>
> - **Host A:** 10.0.1.1/24 eth0
> - **Host B:** 10.0.3.2/24 eth0 (oppure 10.0.2.1/24 eth1)
> - **Router B:** 10.0.1.2/24 eth0, 10.0.2.2/24 eth1, 10.0.3.1/24 eth0
> - **Router A:** 100.4.1.24 d0, 10.0.2.1/24 eth1, 10.0.1.2/24 eth0

---

### Topologia (interpretazione coerente dell'immagine)

Dalla figura e dalla nota a margine, la topologia leggibile è:

```
                ┌──────────────────────────┐
                │     Router B             │
                │ eth0: 10.0.3.1/24        │── (verso Host B)
                │ eth1: 10.0.2.2/24        │── (verso Router A)
                │ "10.0.4.2/24"            │── (eventuale rete extra / upstream)
                └────────────┬─────────────┘
                             │ 10.0.2.0/24
                             │
                ┌────────────┴─────────────┐
                │      Router A            │
                │ eth0: 10.0.1.2/24        │── (verso Host A)
                │ eth1: 10.0.2.1/24        │── (verso Router B)
                │ d0:   100.4.1.24/?       │── (verso Internet)
                └────────────┬─────────────┘
                             │ 10.0.1.0/24
                             │
                        ┌────┴─────┐
                        │ Host A   │
                        │ 10.0.1.1/24 eth0
                        └──────────┘

       ┌──────────┐
       │ Host B   │  10.0.3.2/24 eth0    (collegato a Router B eth0)
       └──────────┘
```

#### Reti coinvolte

| Rete          | Maschera        | Significato                                            |
| ------------- | --------------- | ------------------------------------------------------ |
| `10.0.1.0/24` | `255.255.255.0` | LAN che contiene Host A e Router A (eth0)              |
| `10.0.2.0/24` | `255.255.255.0` | link interno fra Router A (eth1) e Router B (eth1)     |
| `10.0.3.0/24` | `255.255.255.0` | LAN che contiene Router B (eth0) e Host B              |
| `10.0.4.0/24` | `255.255.255.0` | rete ulteriore visibile solo da Router B (se presente) |
| `100.4.1.0/?` | —               | uplink di Router A verso Internet (interfaccia d0)     |

> **Disambiguazione**: la nota all'esercizio elenca alcuni indirizzi in modo apparentemente contraddittorio (Router A con due `eth0`, Host A con due IP differenti). Mantenendo la lettura più coerente con la figura — quella adottata sopra — si ottiene una tabella ben formata. Se il docente intendeva diversamente, basta sostituire i valori delle interfacce nella stessa struttura logica.

### Costruzione della tabella di Router A

Procedo per **categorie** (cfr. M2/UD3/L1 §7):

#### 1. Rotte dirette (reti direttamente connesse alle interfacce di Router A)

| Rete destinazione | Maschera              | Next Hop   | Interfaccia |
| ----------------- | --------------------- | ---------- | ----------- |
| `10.0.1.0`        | `255.255.255.0`       | — (direct) | `eth0`      |
| `10.0.2.0`        | `255.255.255.0`       | — (direct) | `eth1`      |
| `100.4.1.0`       | (es. `255.255.255.0`) | — (direct) | `d0`        |

#### 2. Rotte indirette (reti raggiungibili tramite un next hop)

Router A **non** è direttamente connesso a `10.0.3.0/24` (LAN di Host B), ma sa che si può raggiungere passando per Router B → next hop `10.0.2.2` su `eth1`.

Se esiste anche la rete `10.0.4.0/24` connessa a Router B, vale lo stesso ragionamento.

| Rete destinazione | Maschera        | Next Hop                   | Interfaccia |
| ----------------- | --------------- | -------------------------- | ----------- |
| `10.0.3.0`        | `255.255.255.0` | `10.0.2.2` (Router B eth1) | `eth1`      |
| `10.0.4.0`        | `255.255.255.0` | `10.0.2.2` (Router B eth1) | `eth1`      |

#### 3. Rotta di default (verso Internet)

Tutto ciò che non rientra nelle reti `10.x.x.x/24` interne è "Internet" e va spedito sull'uplink `d0`. Si suppone che il gateway dell'ISP, lato d0, abbia un indirizzo noto (es. `100.4.1.1`).

| Rete destinazione | Maschera  | Next Hop                  | Interfaccia |
| ----------------- | --------- | ------------------------- | ----------- |
| `0.0.0.0`         | `0.0.0.0` | `100.4.1.1` (ISP gateway) | `d0`        |

### Tabella di routing completa di Router A

| #   | Net_ID Destinazione | Maschera        | Next Hop    | Interfaccia | Tipo                |
| --- | ------------------- | --------------- | ----------- | ----------- | ------------------- |
| 1   | `10.0.1.0`          | `255.255.255.0` | direct      | `eth0`      | diretta             |
| 2   | `10.0.2.0`          | `255.255.255.0` | direct      | `eth1`      | diretta             |
| 3   | `100.4.1.0`         | `255.255.255.0` | direct      | `d0`        | diretta             |
| 4   | `10.0.3.0`          | `255.255.255.0` | `10.0.2.2`  | `eth1`      | indiretta (via R_B) |
| 5   | `10.0.4.0`          | `255.255.255.0` | `10.0.2.2`  | `eth1`      | indiretta (via R_B) |
| 6   | `0.0.0.0`           | `0.0.0.0`       | `100.4.1.1` | `d0`        | default             |

### Verifica con Longest Prefix Match (cfr. M2/UD3/L1 §10)

Esempio: pacchetto da Host A (`10.0.1.1`) verso Host B (`10.0.3.2`).

1. AND con `255.255.255.0` per ciascuna riga:
   - `10.0.3.2 ∧ 255.255.255.0 = 10.0.3.0` → matcha la **riga 4** (`/24`).
   - La default (`/0`) matcha sempre, ma con prefisso più corto.
2. Vince `/24` → next hop `10.0.2.2`, interfaccia `eth1`.
3. Router A risolve via ARP il MAC di `10.0.2.2` (Router B eth1) sul link interno e inoltra.
4. Router B riceve, consulta la propria tabella, vede che `10.0.3.0/24` è direttamente connessa su `eth0`, risolve via ARP il MAC di `10.0.3.2` e consegna a Host B.

Esempio: pacchetto da Host A verso `8.8.8.8` (Internet).

1. Nessuna riga `10.0.x.0/24` o `100.4.1.0/24` matcha → resta solo la default `0.0.0.0/0`.
2. Next hop `100.4.1.1`, interfaccia `d0` → uscita verso l'ISP.

### Comandi pratici (Linux, IPv4)

Equivalente Linux della tabella di Router A:

```bash
ip route add 10.0.1.0/24 dev eth0
ip route add 10.0.2.0/24 dev eth1
ip route add 100.4.1.0/24 dev d0
ip route add 10.0.3.0/24 via 10.0.2.2 dev eth1
ip route add 10.0.4.0/24 via 10.0.2.2 dev eth1
ip route add default via 100.4.1.1 dev d0
```

In stile Windows (`route add`, cfr. M2/UD3/L1 §11):

```cmd
route add 10.0.3.0 mask 255.255.255.0 10.0.2.2
route add 10.0.4.0 mask 255.255.255.0 10.0.2.2
route add 0.0.0.0  mask 0.0.0.0       100.4.1.1
```

> **Osservazione**: il numero esatto di righe e i next-hop dipendono dall'interpretazione finale dell'immagine. La struttura logica (3 rotte dirette + 1–2 rotte indirette via R_B + 1 default verso Internet) **resta valida** indipendentemente dai numeri esatti, ed è quella che il professore si attende.

---

## Riferimenti incrociati al corso

Per ogni esercizio, i materiali di studio rilevanti:

- **TCP/Ethernet — throughput, overhead, slow start**: `M1/UD2/L4`, `M2/UD5/L2`, `M2/UD5/L9`, `M2/UD6/L2`.
- **Routing, Longest Prefix Match, default route**: `M2/UD3/L1`, `M2/UD2/L3`.
- **Subnetting VLSM, FLSM, CIDR**: `M2/UD2/L1`, `M2/UD2/L3`, `M2/UD2/L4`.
- **Switching L2, VLAN, trunking 802.1Q**: `M1/UD2/L6`, `M1/UD2/L7`.
- **ARP, frame Ethernet**: `M1/UD2/L4`, `M2/UD3/L3`.
- **Tabella di routing**: `M2/UD3/L1`.

---

