# RECAP 05 — Template di risposta per gli esercizi ricorrenti

> **Il file più importante per l'esame**. Ogni template è organizzato come:
>
> 1. **Quando si applica** (riconoscimento)
> 2. **Schema della soluzione** (passi fissi)
> 3. **Formule pronte all'uso**
> 4. **Esempio risolto** (con calcoli)
> 5. **Trappole comuni** da evitare
>
> Riferimenti agli esami passati segnalati con [E2024], [E5/25], [E5/26], [E3/26].

---

## TEMPLATE A — Calcolo CWND TCP round per round

> **Quando**: chiede "calcola CWND all'istante N" con eventi (timeout, 3 dup-ACK). [E2024, E5/25 A1]

### Schema della soluzione

1. **Identifica versione TCP**: Tahoe, Reno, NewReno → cambia la reazione ai 3 dup-ACK.
2. **Scrivi i dati iniziali**: $\text{CWND}_0$, $\text{SSTHRESH}_0$, eventi.
3. **Costruisci una tabella round per round** con colonne: `t | CWND | SSTHRESH | Fase | Note`.
4. **Applica le regole** ad ogni round:
   - **Slow Start** (`CWND < SSTHRESH`): CWND raddoppia.
   - **Congestion Avoidance** (`CWND ≥ SSTHRESH`): CWND += 1.
   - **Timeout** dopo round $t$: $\text{SSTHRESH} \leftarrow \text{CWND}/2$, $\text{CWND} \leftarrow 1$. Riparte SS al round $t+1$.
   - **3 dup-ACK (Reno)** dopo round $t$: $\text{SSTHRESH} \leftarrow \text{CWND}/2$, $\text{CWND} \leftarrow \text{SSTHRESH}$ (dopo deflazione FR). Riparte CA al round $t+1$.
   - **3 dup-ACK (Tahoe)** dopo round $t$: come timeout (CWND→1).
5. **Quando in SS la finestra raggiunge SSTHRESH**: clip a `SSTHRESH` e switch a CA.
6. **Disegna il grafico** se richiesto (asse x = round, asse y = CWND in MSS).

### Formule da scrivere esplicitamente

$$
\text{ActualWindow} = \min(\text{CWND}, \text{RcvWindow})
$$

$$
\begin{cases}
\text{SS}: \text{CWND}_{new} = 2 \cdot \text{CWND}_{old} \quad\text{(per RTT)} \\[4pt]
\text{CA}: \text{CWND}_{new} = \text{CWND}_{old} + 1\ \text{MSS} \quad\text{(per RTT)} \\[4pt]
\text{Timeout}: \text{SSTHRESH} = \text{CWND}/2,\ \text{CWND} = 1 \\[4pt]
\text{3 dup-ACK (Reno)}: \text{SSTHRESH} = \text{CWND}/2,\ \text{CWND} = \text{SSTHRESH}+3 \rightarrow \text{SSTHRESH} \\
\end{cases}
$$

### Esempio risolto (E2024)

Reno; $\text{CWND}_0=1$, $\text{SSTHRESH}_0=8$; timeout @ t=5; 3 dup-ACK @ t=12. Calcola CWND @ t=16.

| t | CWND | SSTHRESH | Fase | Note |
|:--:|:--:|:--:|:--:|:--|
| 0 | 1 | 8 | SS | start |
| 1 | 2 | 8 | SS | |
| 2 | 4 | 8 | SS | |
| 3 | 8 | 8 | SS→CA | raggiunta soglia |
| 4 | 9 | 8 | CA | +1 |
| 5 | 10 | 8 | CA | **TIMEOUT** |
| 6 | 1 | 5 | SS | restart, SSTHRESH=10/2 |
| 7 | 2 | 5 | SS | |
| 8 | 4 | 5 | SS | next sarebbe 8 > 5 |
| 9 | 5 | 5 | SS→CA | clip a SSTHRESH |
| 10 | 6 | 5 | CA | +1 |
| 11 | 7 | 5 | CA | |
| 12 | 8 | 5 | CA | **3 dup-ACK** |
| 13 | 5 | 4 | CA | SSTHRESH=8/2=4, CWND=SSTHRESH dopo FR |
| ... | | | | wait — see below |

**Variante (cosa fa la lezione Damiani)**: dopo FR, CWND = SSTHRESH = 4, poi CA da 4:
| 13 | 5 (=4+1) | 4 | CA | +1 da SSTHRESH |
| 14 | 6 | 4 | CA | |
| 15 | 7 | 4 | CA | |
| **16** | **8** | **4** | CA | ✅ risultato |

### Trappole comuni

- ❌ Confondere Tahoe e Reno (3 dup-ACK reagiscono diversamente).
- ❌ In SS dimenticare il **clip a SSTHRESH** quando il raddoppio supererebbe la soglia.
- ❌ Applicare l'evento allo **stesso round** invece che al successivo.
- ❌ Dividere SSTHRESH per 2 al round sbagliato (deve essere CWND_attuale/2, non il valore precedente).

---

## TEMPLATE B — Subnetting VLSM

> **Quando**: dato un blocco CIDR e una lista di sottoreti richieste con dimensioni diverse, progetta l'indirizzamento. [E5/25 A3, E5/26 E3]

### Schema della soluzione

1. **Lista le sottoreti** con il loro fabbisogno host (incluso router interface e crescita futura).
2. **Calcola la maschera minima** per ognuna: trova il più piccolo $h$ tale che $2^h - 2 \ge \text{host}$. CIDR = $32 - h$.
3. **Ordina dalla più grande alla più piccola** (allineamento critico).
4. **Assegna a partire dall'inizio del range**, rispettando l'allineamento (network address multiplo della subnet size).
5. **Per ogni subnet** scrivi: network, mask, broadcast, range host.
6. **Per i link punto-punto** usa `/30` (4 indirizzi: net, host1, host2, broadcast).
7. **Conta gli indirizzi non utilizzati** (gap di allineamento + spazio finale).

### Formule pronte

| CIDR | Mask | Host utili | Size | Allineamento (ultimo ottetto) |
|:--:|:--:|:--:|:--:|:--:|
| /24 | 255.255.255.0 | 254 | 256 | 0 |
| /25 | 255.255.255.128 | 126 | 128 | 0, 128 |
| /26 | 255.255.255.192 | 62 | 64 | 0, 64, 128, 192 |
| /27 | 255.255.255.224 | **30** | 32 | multipli di 32 |
| /28 | 255.255.255.240 | **14** | 16 | multipli di 16 |
| /29 | 255.255.255.248 | 6 | 8 | multipli di 8 |
| /30 | 255.255.255.252 | **2** | 4 | multipli di 4 |

Per maschere più corte:

| CIDR | Mask | Host utili | Size |
|:--:|:--:|:--:|:--:|
| /16 | 255.255.0.0 | 65534 | 65536 |
| /18 | 255.255.192.0 | 16382 | 16384 |
| /20 | 255.255.240.0 | 4094 | 4096 |
| /22 | 255.255.252.0 | **1022** | 1024 |
| /23 | 255.255.254.0 | 510 | 512 |

### Esempio risolto (E5/26 E3)

Blocco `193.204.176.0/22`. Servono: subnet A, subnet B, link A-B, link B-Internet.

> Range: `193.204.176.0 – 193.204.179.255` (1024 indirizzi).

Senza vincoli sulla dimensione di A e B, scelgo:
- A: `/24` (254 host) → `193.204.176.0/24`
- B: `/24` (254 host) → `193.204.177.0/24`
- Link A-B: `/30` → `193.204.178.0/30` (host: .1 e .2, broadcast: .3)
- Link B-Internet: `/30` → `193.204.178.4/30` (host: .5 e .6, broadcast: .7)

**Tabella finale**:

| Rete | Network | Mask | Broadcast | Range host | Note |
|---|:--:|:--:|:--:|:--:|---|
| A | `193.204.176.0` | `/24` | `193.204.176.255` | `.1-.254` | |
| B | `193.204.177.0` | `/24` | `193.204.177.255` | `.1-.254` | |
| Link A-B | `193.204.178.0` | `/30` | `193.204.178.3` | `.1-.2` | |
| Link B-Int | `193.204.178.4` | `/30` | `193.204.178.7` | `.5-.6` | |

**Router**: serve **un router per rete** (A, B). Quindi:
- **Router RA**: 2 interfacce (LAN A + link A-B)
- **Router RB**: 3 interfacce (LAN B + link A-B + link B-Internet)

> **Alternativa con 1 solo router**: RA-B con 3 interfacce serve sia A sia B; il "link A-B" diventa inutile.

**Indirizzi non utilizzati**: `193.204.178.8 – 193.204.179.255` = 1016 indirizzi (riserva).

### Trappole comuni

- ❌ Non rispettare l'allineamento (es. mettere /27 in `.100` invece che `.96`).
- ❌ Dimenticare di sottrarre 2 (network + broadcast) dal count host.
- ❌ Usare `/30` con allineamento ai multipli di 8 (sbagliato: sono multipli di 4).
- ❌ Mettere il link punto-punto in una /24 (spreco di 254 indirizzi).

---

## TEMPLATE C — Tabella di routing + Longest Prefix Match

> **Quando**: dato un disegno di rete o una tabella, mostra come instradare un pacchetto. [E5/25 A3d-e, E5/26 E2 + E4]

### Schema della soluzione

1. **Per ogni router**, elenca le **interfacce e le reti direttamente connesse**.
2. **Costruisci la tabella di routing** con colonne: `Destination | Mask | Gateway | Interface`.
   - Reti direttamente connesse: gateway = "—" (direct).
   - Reti remote: gateway = IP del prossimo hop.
   - Default route: `0.0.0.0/0` con gateway uplink.
3. **Per ogni pacchetto da instradare**:
   a. Calcola `IP_dst AND mask` per ogni riga.
   b. Confronta col `network` di ogni riga.
   c. Tra le righe che matchano, scegli quella con **mask più lunga** (LPM).
   d. Inoltra al gateway / interface della riga vincente.

### Esempio risolto (E5/26 E2)

Tabella data:
| Dest. prefix | Next Hop |
|---|---|
| `192.24.1.0/24` | direct |
| `192.24.192.0/18` | `192.24.1.5` |
| `0.0.0.0/0` | `192.24.1.1` |

**Pacchetto a `192.24.128.5`**:

| Riga | Mask | IP_dst AND mask | Match con prefix? |
|---|---|---|:--:|
| 1 | `255.255.255.0` (/24) | `192.24.128.0` | ❌ ≠ `192.24.1.0` |
| 2 | `255.255.192.0` (/18) | `192.24.128.0` | ❌ ≠ `192.24.192.0` *(128 ≠ 192 in /18)* |
| 3 | `0.0.0.0` (/0) | `0.0.0.0` | ✅ |

> **LPM: solo la default route matcha.** Pacchetto → next hop `192.24.1.1`.

**Pacchetto a `192.24.1.5`**:

| Riga | IP_dst AND mask | Match? |
|---|---|:--:|
| 1 | `192.24.1.0` | ✅ |
| 2 | `192.24.0.0` | ❌ |
| 3 | `0.0.0.0` | ✅ |

> **LPM: /24 batte /0**. Pacchetto → direct, ARP su `192.24.1.5` sull'interfaccia connessa.

### Verifica di /18

`/18` = `255.255.192.0` (i primi 2 bit del 3° ottetto a 1).
- `192` in binario: `11000000`. 
- `128` in binario: `10000000`. 
- AND con `11000000`: `128` ≠ `192`.

> **Trucco**: `192.24.192.0/18` significa rete che inizia con `192.24.192.0`. La maschera /18 ha range del 3° ottetto a multipli di 64. Quindi le subnet /18 di `192.24` sono: `0`, `64`, `128`, `192`. La nostra rete `192.24.192.0/18` contiene `.192.0` – `.255.255`. Il pacchetto a `192.24.128.5` cade nella subnet `192.24.128.0/18` (range `.128.0 – .191.255`), che NON è il nostro prefix.

### Trappole comuni

- ❌ Confondere "il numero che appare nel prefix" con "il vero network address". `192.24.192.0/18` ha network = `192.24.192.0` perché i primi 18 bit di `192.24.192.0` sono già allineati alla maschera.
- ❌ Calcolare l'AND male nel 3° ottetto (ricordarsi: maschera `/18` = `11111111.11111111.11000000.00000000`).
- ❌ Dimenticare la **default route**: matcha SEMPRE (è il fallback).
- ❌ Dimenticare che LPM = **mask più lunga**, non distance vector o numero di hop.

---

## TEMPLATE D — Hop-by-hop con ARP + Ethernet

> **Quando**: descrivi il percorso di un pacchetto da host A in LAN1 a host B in LAN5 attraverso N router. [E5/25 A3e, E5/26 E3c]

### Schema della soluzione

1. **Decisione iniziale dell'host A** (livello IP):
   - Calcola `IP_A AND mask_A` e `IP_B AND mask_A`.
   - Se uguali → instradamento **diretto** (ARP su IP_B).
   - Se diversi → instradamento **indiretto**: pacchetto al **default gateway**.

2. **ARP sul gateway/destinazione**:
   - Cache miss → **ARP Request** in broadcast (`dst MAC = FF:FF:FF:FF:FF:FF`).
   - **ARP Reply** in unicast dal destinatario.
   - A salva `(IP, MAC)` in cache.

3. **A invia il primo frame Ethernet**:
   - `src MAC = MAC_A`, `dst MAC = MAC_R1`
   - `src IP = IP_A`, `dst IP = IP_B` (rimangono invariati per tutto il percorso!)

4. **Ad ogni router intermedio Ri**:
   - **De-incapsula** il frame Ethernet.
   - Legge `dst IP` dal pacchetto IP.
   - **LPM** sulla propria routing table → trova next hop.
   - **TTL -= 1**; ricalcola **header checksum** IP.
   - Risolve via **ARP** il MAC del next hop (sul link uscente).
   - **Re-incapsula** in NUOVO frame Ethernet: `src MAC = MAC_Ri (interfaccia uscente)`, `dst MAC = MAC_next_hop`.

5. **Ultimo router (Rn, quello connesso alla LAN di B)**:
   - LPM dice "direct" → ARP su `IP_B` direttamente.
   - Incapsula in frame con `dst MAC = MAC_B`, lo invia sulla LAN.

6. **Host B**:
   - Riceve frame, controlla `dst MAC == MAC_B` (sì).
   - De-incapsula, controlla `dst IP == IP_B` (sì).
   - Consegna al livello superiore.

### Regola d'oro

> **A livello IP src e dst non cambiano MAI**. A livello Ethernet src e dst cambiano AD OGNI HOP. TTL decrementa di 1 per hop.

### Esempio risolto (E5/25 A3e)

Da H1 in LAN1 (`194.1.1.34`) a H5 in LAN5 (`194.1.1.130`), attraverso R1 → R2 → R3 → R5.

```
H1                R1                R2                R3                R5            H5
 |  Eth: H1→R1     |  Eth: R1→R2     |  Eth: R2→R3     |  Eth: R3→R5    |  Eth: R5→H5
 |  IP: H1→H5      |  IP: H1→H5      |  IP: H1→H5      |  IP: H1→H5     |  IP: H1→H5
 |  TTL=64         |  TTL=63         |  TTL=62         |  TTL=61        |  TTL=60
 +→←→←→←→←→←→←→ → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → →+
```

A ogni hop:
- IP src/dst invariati
- TTL decrementato
- Frame Ethernet **distrutto e ricostruito** con MAC src/dst del link corrente
- ARP su ogni link per risolvere il next hop

### Trappole comuni

- ❌ Dire che il MAC dst del primo frame è `MAC_H5` (è invece `MAC_R1` perché la LAN è diversa).
- ❌ Non decrementare il TTL.
- ❌ Dimenticare che la **checksum IP va ricalcolata ad ogni hop** (perché cambia TTL).
- ❌ Dire che ARP attraversa router (NO! ARP è solo locale alla LAN).

---

## TEMPLATE E — CSMA/CD: calcolo distanza massima

> **Quando**: dati R (bit-rate) e L_min (lunghezza minima frame), calcola la distanza massima tra due stazioni. [E3/26 E1]

### Schema della soluzione

1. **Definisci $T_{tx}$** = tempo di trasmissione del frame minimo:
   $$T_{tx} = \frac{L_{min}}{R}$$
2. **Imponi la condizione di rilevamento delle collisioni**: il mittente deve essere **ancora in trasmissione** al ritorno del segnale collidente:
   $$T_{tx} \ge 2 \cdot T_{prop}$$
   dove $T_{prop} = d/v$ è il tempo di propagazione (one-way).
3. **Ricava la distanza massima**:
   $$\boxed{d_{max} = \frac{v \cdot L_{min}}{2 \cdot R}}$$

### Costanti

| Quantità | Valore |
|---|:--:|
| Velocità segnale in cavo Ethernet ($v$) | $\sim 2 \cdot 10^8$ m/s (66% di $c$) |
| Velocità onde EM nel vuoto ($c$) | $3 \cdot 10^8$ m/s |

### Esempio risolto (E3/26 E1)

Dati: $R = 100$ Mbps $= 10^8$ bps, $L_{min} = 512$ bit.

$$T_{tx} = \frac{512}{10^8} = 5{,}12 \cdot 10^{-6} \text{ s} = 5{,}12\ \mu s$$

$$T_{prop} \le \frac{T_{tx}}{2} = 2{,}56\ \mu s$$

$$d_{max} = v \cdot T_{prop} = 2 \cdot 10^8 \cdot 2{,}56 \cdot 10^{-6} = 512 \text{ m}$$

> Con $v = 2 \cdot 10^8$ m/s la risposta è **≈ 512 metri**.
> Con $c = 3 \cdot 10^8$ m/s sarebbe **≈ 768 metri** (limite teorico massimo).

### Note importanti

- A **10 Mbps** ($L_{min} = 512$ bit fissi): $d_{max} = \frac{2 \cdot 10^8 \cdot 512}{2 \cdot 10^7} = 5120$ m → in pratica **2500 m** (con margine per dispositivi).
- A **100 Mbps** Fast Ethernet: distanza scende a 10× meno (~250-500 m).
- A **1 Gbps**: si tiene $L_{min} = 512$ bit aumentando con **carrier extension** o eliminando CSMA/CD (full-duplex).

### Trappole comuni

- ❌ Usare $v = c = 3 \cdot 10^8$ m/s (il prof può preferire $2 \cdot 10^8$ per cavo). Se non specificato, scegli e **motiva** la scelta.
- ❌ Confondere $T_{tx}$ con $T_{prop}$.
- ❌ Dimenticare il fattore 2 ($2T$ round-trip).

---

## TEMPLATE F — Configurazione VLAN + router-on-a-stick (Cisco IOS)

> **Quando**: chiede config Cisco IOS per VLAN su 2 switch con trunk + routing inter-VLAN. [E3/26 E2]

### Schema della soluzione

1. **Su ogni switch**, crea le VLAN necessarie:
   ```
   vlan 10
    name Ufficio
   vlan 20
    name WiFi
   ```

2. **Configura le porte access** (host finali, AP):
   ```
   interface GigabitEthernet 0/1
    switchport mode access
    switchport access vlan 10
   ```

3. **Configura la porta trunk** (collegamento switch-switch):
   ```
   interface GigabitEthernet 0/24
    switchport mode trunk
    switchport trunk encapsulation dot1q       ! 802.1Q
    switchport trunk allowed vlan 10,20,30
   ```

4. **Inter-VLAN routing — router-on-a-stick**:
   - Collega il router allo switch tramite **una porta trunk**.
   - Sul router crea una **sub-interface per VLAN**:
   ```
   interface GigabitEthernet 0/0.10
    encapsulation dot1Q 10
    ip address 192.168.10.1 255.255.255.0
   !
   interface GigabitEthernet 0/0.20
    encapsulation dot1Q 20
    ip address 192.168.20.1 255.255.255.0
   ```

5. **Piano IP**:
   - Una subnet per VLAN.
   - Il router su ogni sub-interface ha l'IP del **default gateway** della subnet.

### Esempio risolto (E3/26 E2)

VLAN 10 (Ufficio), VLAN 20 (Wi-Fi), VLAN 30 (Internet, solo su switch A).

**Switch A**:
```
vlan 10
 name Ufficio
vlan 20
 name WiFi
vlan 30
 name Internet
!
interface GigabitEthernet 0/1
 description Router esterno
 switchport mode access
 switchport access vlan 30
!
interface range GigabitEthernet 0/2-10
 description Host Ufficio
 switchport mode access
 switchport access vlan 10
!
interface range GigabitEthernet 0/11-15
 description Access Point Wi-Fi
 switchport mode access
 switchport access vlan 20
!
interface GigabitEthernet 0/24
 description Trunk verso Switch B
 switchport mode trunk
 switchport trunk encapsulation dot1q
 switchport trunk allowed vlan 10,20
```

**Switch B**:
```
vlan 10
 name Ufficio
vlan 20
 name WiFi
!
interface range GigabitEthernet 0/1-20
 description Host Ufficio piano 1
 switchport mode access
 switchport access vlan 10
!
interface range GigabitEthernet 0/21-23
 description AP Wi-Fi piano 1
 switchport mode access
 switchport access vlan 20
!
interface GigabitEthernet 0/24
 description Trunk verso Switch A
 switchport mode trunk
 switchport trunk encapsulation dot1q
 switchport trunk allowed vlan 10,20
```

**Piano IP**:

| VLAN | Subnet | Gateway (router) |
|:--:|:--:|:--:|
| 10 Ufficio | `192.168.10.0/24` | `192.168.10.1` |
| 20 Wi-Fi | `192.168.20.0/24` | `192.168.20.1` |
| 30 Internet | `192.168.30.0/24` | `192.168.30.1` |

**Router (router-on-a-stick)**:

Sub-interface per VLAN 10 e 20 (connessione trunk sullo switch A), e interface fisica per VLAN 30 verso il router esterno → l'inter-VLAN routing avviene su sub-interface, con encapsulation 802.1Q. La VLAN 30 può essere su interfaccia fisica perché collega direttamente al router esterno (non serve trunking).

```
interface GigabitEthernet 0/0
 description Trunk verso Switch A
 no ip address
!
interface GigabitEthernet 0/0.10
 encapsulation dot1Q 10
 ip address 192.168.10.1 255.255.255.0
!
interface GigabitEthernet 0/0.20
 encapsulation dot1Q 20
 ip address 192.168.20.1 255.255.255.0
!
interface GigabitEthernet 0/1
 description Router esterno (VLAN 30)
 ip address 192.168.30.1 255.255.255.0
!
ip route 0.0.0.0 0.0.0.0 192.168.30.254   ! default route verso router esterno
```

### Perché trunk vs access?

- **Trunk** per il link switch-switch (porta più VLAN simultaneamente, con tag 802.1Q).
- **Trunk** per il router-on-a-stick (riceve frame di più VLAN tagged).
- **Access** per host singoli (parlano una sola VLAN, no tag).

### Trappole comuni

- ❌ Mettere host su porte trunk (devono essere access).
- ❌ Dimenticare `encapsulation dot1q` sul trunk.
- ❌ Dimenticare le sub-interface sul router per inter-VLAN routing.
- ❌ Non assegnare IP gateway su ogni sub-interface.

---

## TEMPLATE G — DHCP DORA + spoofing attack

> **Quando**: chiede di descrivere il funzionamento DHCP o un attacco di rogue DHCP server. [E3/26 E3]

### DORA (4 messaggi)

```
Client                            DHCP Server
  | --- DHCPDISCOVER (broadcast) ---> |
  | <-- DHCPOFFER     (broadcast) --- |
  | --- DHCPREQUEST   (broadcast) --> |
  | <-- DHCPACK                  --- |
        [lease attivo]
```

### Porte
- Server: **UDP 67**, Client: **UDP 68**.

### Cosa viene assegnato
- IP, subnet mask, default gateway (option 3), DNS (option 6), lease time, nome dominio, …

### Spoofing attack (rogue DHCP) — esempio narrativo

**Scenario**:
- Rete `192.168.1.0/24`
- Server DHCP legittimo: `192.168.1.10`, fornisce gateway `192.168.1.1`
- Attaccante **Eve**: laptop `192.168.1.66` collegato allo stesso switch

**Sequenza dell'attacco**:

1. **Vittima** (laptop appena collegato): emette `DHCPDISCOVER` in broadcast.
2. Sia il server legittimo che il **rogue server di Eve** ricevono e rispondono con `DHCPOFFER`.
3. Eve risponde **più velocemente** (script ottimizzato, server più vicino).
4. La vittima sceglie l'offerta che le arriva per prima (quella di Eve) e invia `DHCPREQUEST`.
5. Eve risponde con `DHCPACK` contenente:
   - IP plausibile: `192.168.1.50`
   - Subnet mask: `255.255.255.0`
   - **Default gateway: `192.168.1.66`** (= Eve!)
   - **DNS server: `192.168.1.66`** (= Eve!)
   - Lease: 1 ora

**Campi falsificati**:
- `option 3` (router/gateway)
- `option 6` (DNS)
- Eventualmente `siaddr` (next server)

**Conseguenze**:
- **MITM completo**: tutto il traffico Internet della vittima passa per Eve.
- Eve può:
  - Ispezionare ogni richiesta HTTP non cifrata.
  - **DNS spoofing**: reindirizza la vittima a siti fake (phishing, furto credenziali).
  - **SSL strip** / **HSTS bypass** (su utenti che ignorano gli warning del browser).
  - Iniettare codice malevolo in pagine HTTP.
  - Disconnettere selettivamente certi servizi.

**Contromisure**:

| Contromisura | Cosa fa |
|---|---|
| **DHCP Snooping** (switch Cisco) | Le porte sono classificate trusted/untrusted; `DHCPOFFER` e `DHCPACK` dalle untrusted sono **droppati**. Solo la porta verso il vero server è trusted |
| **Dynamic ARP Inspection (DAI)** | Usa la tabella DHCP Snooping per validare che il binding IP↔MAC sia autentico |
| **IP Source Guard** | Blocca traffico con `src IP` che non risulta nella DHCP Snooping table |
| **Port Security** | Limita il numero di MAC su una porta switch |
| **802.1X** | Autenticazione della porta prima dell'accesso alla rete |

### Configurazione DHCP Snooping (Cisco)

```
ip dhcp snooping
ip dhcp snooping vlan 10
!
interface GigabitEthernet 0/24
 description Verso server DHCP legittimo
 ip dhcp snooping trust
!
interface range GigabitEthernet 0/1-23
 description Porte utente (untrusted di default)
```

### Trappole comuni

- ❌ Dire che DHCP è su TCP (è UDP).
- ❌ Confondere broadcast vs unicast: `DHCPREQUEST` è ancora **broadcast** perché informa tutti i server che hanno offerto.
- ❌ Dimenticare la contromisura → il prof la vuole sempre.

---

## TEMPLATE H — Dijkstra step-by-step

> **Quando**: dato un grafo pesato, calcola cammini minimi da/verso un nodo. [E2024 E3]

### Schema della soluzione

1. **Disegna il grafo** o trascrivi l'elenco degli archi.
2. **Inizializzazione**: $d(s)=0$, $d(v)=\infty$ per gli altri; $S = \emptyset$.
3. **Iterazione** (ripeti finché tutti i nodi non sono in S):
   a. Estrai il nodo $u \notin S$ con $d(u)$ minimo.
   b. Aggiungilo a $S$.
   c. Per ogni vicino $v$ di $u$ ($v \notin S$):
      - Se $d(u) + w(u,v) < d(v)$: aggiorna $d(v)$ e $prev(v) = u$.
4. **Tabella finale**: distanza e cammino (ricostruito via `prev`) per ogni nodo.
5. **Disegna SPT** (Shortest Path Tree) se richiesto.

### Esempio risolto (E2024 E3) — sintetico

Grafo con 8 nodi, sorgente O. Risultato:

| Nodo | $d$ | Cammino verso O |
|---|:--:|---|
| O | 0 | — |
| A | 2 | A→O |
| B | 4 | B→A→O |
| C | 4 | C→O |
| D | 8 | D→B→A→O (o D→E→B→A→O, parità) |
| E | 7 | E→B→A→O |
| T | 9 | T→E→B→A→O |
| F | 12 | F→T→E→B→A→O |

> Vedi `Soluzione_esame_2024.md` per i 8 passi di estrazione e rilassamento.

### Trappole comuni

- ❌ Non riordinare la priority queue dopo un aggiornamento.
- ❌ Riprocessare nodi già in $S$.
- ❌ Dimenticare la parità (cammini di costo uguale).

---

## TEMPLATE I — Calcolo throughput / tempo di trasferimento

> **Quando**: "Quanto tempo per trasferire N byte su link X tramite TCP?" [E5/26 E1]

### Schema della soluzione

1. **Identifica i dati**:
   - File size $N$ (in byte, attento alle unità: MB vs Mbit, GB binario vs decimale).
   - Bandwidth $R$ (in bps).
   - Eventualmente: RTT, MTU, overhead.

2. **Fai ipotesi esplicite** (e dichiarale!):
   - **Approccio ideale**: trascuro slow-start, overhead, ACK opposti, jitter.
   - **Approccio realistico**: aggiungo overhead ~5%, slow start iniziale.

3. **Calcola**:
   - Conversione: $N$ byte $= N \cdot 8$ bit.
   - $T = N \cdot 8 / R$ (in secondi).

4. **Aggiungi correzioni** se richiesto:
   - **Slow start**: i primi $\log_2(\text{RcvWindow}/MSS)$ RTT sono persi a "warm-up" (per file molto grandi, trascurabile).
   - **Overhead headers**: TCP 20 + IP 20 + Eth 18 = 58 B su 1518 B → 3.8% di overhead. Tempo reale ≈ $T / (1 - 0.038)$.
   - **Inter-frame gap Ethernet**: 9.6 μs × numero frame, in genere trascurabile.

### Esempio risolto (E5/26 E1)

Trasferire 1 GB su Ethernet 100 Mbps direttamente collegata.

**Ipotesi**:
- 1 GB = $1 \cdot 2^{30} \cdot 8 = 8{,}59 \cdot 10^9$ bit (notazione binaria)
- Banda = 100 Mbps = $10^8$ bps
- Collegamento diretto (no router intermedi, no slow start significativo)

**Tempo ideale**:
$$T = \frac{8{,}59 \cdot 10^9}{10^8} = 85{,}9 \text{ s}$$

**Con overhead headers (~4%)**:
$$T_{realistico} \approx \frac{85{,}9}{0{,}96} \approx 89{,}5 \text{ s}$$

**Risposta**: **≈ 86-90 secondi**, dichiarando le ipotesi (1 GB binario, no slow start, overhead 4%).

### Formule chiave

$$
T_{tx} = \frac{\text{Bytes} \cdot 8}{\text{Bandwidth (bps)}}
$$

$$
\text{Throughput TCP saturato} = \frac{\text{CWND}}{\text{RTT}}
$$

$$
\text{BDP (Bandwidth-Delay Product)} = \text{Bandwidth} \times \text{RTT}
$$

### Trappole comuni

- ❌ Confondere bit/byte (factor 8).
- ❌ Confondere GB decimali ($10^9$) e binari ($2^{30}$).
- ❌ Dimenticare il `bandwidth/8` per ottenere byte/s.

---

## TEMPLATE J — Datagrammi UDP (rappresentazione)

> **Quando**: rappresenta i datagrammi UDP scambiati tra client e server. [E5/25 A2]

### Schema della soluzione

1. **Identifica quanti datagrammi**: leggi attentamente il testo. Conta i `sendto()`.
2. **Per ogni datagramma** rappresenta:
   - Source port (effimera lato client, es. 49152)
   - Destination port (well-known lato server, es. 5000)
   - Length (header 8 + payload)
   - Checksum (opzionale)
   - **Payload** (l'informazione applicativa, con la sua codifica)

3. **Aggiungi un diagramma del traffico** (timeline client ↔ server).

### Esempio risolto (E5/25 A2)

Sensore invia valori 2.3 e 4.1. Ogni valore = coppia (intero, decimale).

**Interpretazione 1**: 2 datagrammi (ogni valore di temperatura = 1 datagramma con 2 interi).

**Interpretazione 2**: 4 datagrammi (ogni intero = 1 datagramma).

Entrambe difendibili — **dichiara l'interpretazione**.

#### Datagramma tipo (interpretazione 1)

```
+------------------+------------------+
| Src Port  = 49152| Dst Port  = 5000 |
+------------------+------------------+
| Length    = 12   | Checksum  = ...  |
+------------------+------------------+
| Payload: 0x0002 0x0003   (2.3)     |
+-------------------------------------+
```

### Trappole comuni

- ❌ Dimenticare l'header UDP (8 byte fissi).
- ❌ Mettere ack o sequence number (è UDP, non TCP).
- ❌ Non specificare l'interpretazione di "ogni valore".

---

## Recap finale dei numeri-chiave da ricordare a memoria

| Quantità | Valore |
|---|:--:|
| Lunghezza minima frame Ethernet | **64 byte = 512 bit** |
| Tempo round-trip Ethernet 10 Mbps | **51.2 μs** |
| MTU Ethernet | **1500 byte** |
| MSS TCP tipico (su Ethernet) | **1460 byte** |
| Header IP / TCP / UDP | 20 / 20 / 8 byte |
| Indirizzi privati | `10/8`, `172.16/12`, `192.168/16` |
| Loopback | `127.0.0.1` (IPv4), `::1` (IPv6) |
| DHCP porte | 67 server / 68 client (UDP) |
| DNS porta | 53 (UDP/TCP) |
| HTTP / HTTPS | 80 / 443 (TCP) |
| OSPF, BGP | IP proto 89, TCP 179 |
| Velocità segnale cavo | $\sim 2 \cdot 10^8$ m/s |
| Max hop RIP | **15** |
| OSPF reference bandwidth default | 100 Mbps |
| Lease DHCP rinnovo (T1) | 50% lease |
| Lease DHCP rebinding (T2) | 87.5% lease |
| Backoff esponenziale max | $2^{10} - 1 = 1023$ slot |
| TTL default Linux | 64 |

---

## Strategia in aula (al momento dell'esame)

1. **Leggi tutto** prima di iniziare a scrivere. Identifica quale template applicare ad ogni esercizio.
2. **Dichiara sempre le ipotesi** (es. "assumo $v = 2 \cdot 10^8$ m/s", "assumo nessuna perdita prima del timeout dichiarato").
3. **Scrivi le formule prima dei calcoli**, sempre.
4. **Mostra il passaggio** (es. tabella round-per-round, non solo il risultato).
5. **Verifica le unità di misura** (bit vs byte, ms vs μs, MB vs Mbit).
6. **Se non sai una cosa precisa**: scrivi "assumendo che...", non lasciare in bianco.
7. **Riserva 10 minuti finali** per rileggere le risposte.

> **Buona fortuna. L'ossessione batte il talento.**
