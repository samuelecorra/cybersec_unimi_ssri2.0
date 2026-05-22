# Soluzione Esame di Reti di Calcolatori — Parte A — 20/03/2026

**Prof. Ernesto Damiani**

Per ogni esercizio è indicato il riferimento alle lezioni di teoria del corso, l'inquadramento concettuale, lo svolgimento dettagliato e — dove richiesto — diagrammi e calcoli.

---

## Indice

- [Esercizio 1 — Distanza massima in CSMA/CD a 100 Mbps con $L_{min}=512$ bit](#esercizio-1--distanza-massima-in-csmacd-a-100-mbps-con-l_min512-bit)
- [Esercizio 2 — Progetto VLAN e indirizzamento IP per due switch + router](#esercizio-2--progetto-vlan-e-indirizzamento-ip-per-due-switch--router)
- [Esercizio 3 — Spoofing DHCP (Rogue DHCP Server)](#esercizio-3--spoofing-dhcp-rogue-dhcp-server)

---

## Esercizio 1 — Distanza massima in CSMA/CD a 100 Mbps con $L_{min}=512$ bit

> **Riferimenti di teoria**:
>
> - [M1/UD2/L4 — Ethernet – Frame e CSMA-CD](../../M1_Tecniche_comunicazione_digitale/UD2/L4%20-%20Ethernet%20%E2%80%93%20Frame%20e%20CSMA-CD.md) (back-to-back delay, lunghezza minima del frame, vincoli temporali)
> - [M1/UD2/L5 — Gestione delle collisioni in Ethernet](../../M1_Tecniche_comunicazione_digitale/UD2/L5%20-%20Gestione%20delle%20collisioni%20in%20Ethernet.md) (§3–4 — vincolo $T_{tx} \geq 2T_p$, slot time 51,2 μs a 10 Mbps su 2500 m)
> - [M2/UD5/L2 — Efficienza e controllo d'errore](../../M2_Protocolli_rete_TCP_IP/UD5/L2%20-%20Efficienza%20e%20controllo%20d%E2%80%99errore.md) (formula $T_{ix} = \text{bit}/R$)

---

**Esercizio 1 (10 punti)** In una rete Ethernet che utilizza un protocollo a contesa CSMA/CD operante a bit rate R = 100 Mbps, la lunghezza minima del PDU (frame) è fissata a L_min = 512 bit. Calcolate la distanza massima consentita tra due stazioni, in modo da garantire l'integrità del frame.

---

### Inquadramento teorico

Il protocollo **CSMA/CD** (cfr. M1/UD2/L4 §8) richiede che, durante la trasmissione di un frame, una stazione sia ancora **in trasmissione** quando le arriva un eventuale segnale di collisione dall'estremo opposto del cavo. Solo così la collisione può essere rilevata e gestita (jam signal + backoff esponenziale binario, cfr. M1/UD2/L5 §6–8).

#### Lo scenario peggiore (cfr. M1/UD2/L5 §3)

Siano A e B due stazioni agli estremi del cavo, separate da una distanza $d$:

```
A ───────────────  d  ─────────────── B
│                                     │
t=0   inizia a trasmettere
t=T_p il segnale di A sta per arrivare in B
                  B (non sentendolo ancora) inizia a trasmettere → collisione
t=2T_p A riceve il segnale di B (la collisione "rimbalza")
```

- Il messaggio di **A** raggiunge **B** dopo un tempo $T_p$ (tempo di propagazione *one-way*);
- se anche B inizia a trasmettere appena prima di "sentire" A, il suo segnale collide con quello di A nel cavo e raggiunge A dopo un ulteriore $T_p$;
- quindi **A scopre la collisione dopo $2T_p$** (round-trip time, RTT del segnale fisico).

Perché A si accorga della collisione, **A deve essere ancora in trasmissione al tempo $2T_p$**. Detto $T_{tx}$ il tempo necessario a trasmettere un intero frame, deve valere:

$$
\boxed{\;T_{tx} \;\geq\; 2T_p\;}
$$

Quello che è davvero limitante è la **lunghezza minima** del frame: se il frame fosse troppo corto, A potrebbe finire di trasmettere prima di accorgersi che B ha collisione, e dichiarerebbe la trasmissione "riuscita" (frame perso silenziosamente!).

### Formula utilizzata

Da $T_{tx} \geq 2T_p$ e $T_{tx} = L_{min}/R$:

$$
\frac{L_{min}}{R} \;\geq\; \frac{2d}{v} \quad\Longrightarrow\quad d \;\leq\; \frac{L_{min} \cdot v}{2 \cdot R}
$$

dove:

- $L_{min}$ = lunghezza minima del frame (bit);
- $R$ = bit-rate del canale (bit/s);
- $v$ = velocità di propagazione del segnale nel mezzo (m/s);
- $d$ = distanza fisica tra le due stazioni (m).

### Dati del problema

| Parametro | Valore |
|---|---|
| Bit-rate | $R = 100\,\text{Mbps} = 10^8\,\text{bit/s}$ |
| Lunghezza minima del frame | $L_{min} = 512\,\text{bit}$ |
| Velocità di propagazione nel cavo (ipotesi standard) | $v = 2 \cdot 10^8\,\text{m/s}$ ($\approx \tfrac{2}{3} c$, valore tipico per twisted-pair in rame) |

### Calcolo numerico

**Tempo di trasmissione del frame minimo (slot time)**:

$$
T_{tx} \;=\; \frac{L_{min}}{R} \;=\; \frac{512}{10^8} \;=\; 5{,}12\,\mu s
$$

**Tempo massimo di propagazione one-way**:

$$
T_p \;\leq\; \frac{T_{tx}}{2} \;=\; \frac{5{,}12}{2} \;=\; 2{,}56\,\mu s
$$

**Distanza massima**:

$$
d_{max} \;=\; T_p \cdot v \;=\; 2{,}56 \cdot 10^{-6}\,\text{s} \;\cdot\; 2 \cdot 10^8\,\text{m/s} \;=\; 512\,\text{m}
$$

### Risultato

$$
\boxed{\;d_{max} \;\approx\; 512\ \text{metri}\;}
$$

### Confronto con Ethernet 10 Mbps

A bit-rate dieci volte inferiore (10 Mbps, Ethernet classica) lo stesso $L_{min}$ diventa "più lungo nel tempo" (cfr. M1/UD2/L5 §4):

$$
T_{tx}^{(10Mbps)} \;=\; \frac{512}{10^7} \;=\; 51{,}2\,\mu s
\quad \Longrightarrow \quad d_{max}^{(10Mbps)} \;\approx\; 5120\,\text{m}
$$

(ridotto a $\mathbf{2500\,\text{m}}$ dallo standard IEEE 802.3 per tenere conto dei ritardi di hub/ripetitori).

Per la stessa ragione, in Fast Ethernet a 100 Mbps la distanza massima nominale con hub (collision domain) è ulteriormente ridotta a **~100–200 m**, perché bisogna includere i ritardi degli apparati nel budget di $2T_p$. Il **512 m** calcolato è quindi il limite **teorico** (cavo nudo, velocità nominale).

### Variante con $v = 3 \cdot 10^8\,\text{m/s}$ (segnale alla velocità della luce nel vuoto)

Se si usa la velocità della luce nel vuoto (ipotesi limite, fisicamente non raggiungibile in un cavo):

$$
d_{max}^{(c)} \;=\; 2{,}56 \cdot 10^{-6} \;\cdot\; 3 \cdot 10^8 \;=\; 768\,\text{m}
$$

Entrambi i valori sono accettabili in compito purché la **velocità di propagazione assunta sia esplicitata**.

---

## Esercizio 2 — Progetto VLAN e indirizzamento IP per due switch + router

> **Riferimenti di teoria**:
>
> - [M1/UD2/L6 — Switched Ethernet](../../M1_Tecniche_comunicazione_digitale/UD2/L6%20-%20Switched%20Ethernet.md) (CAM, switching, domini di collisione)
> - [M1/UD2/L7 — VLAN e Trunking](../../M1_Tecniche_comunicazione_digitale/UD2/L7%20-%20VLAN%20e%20Trunking.md) (VLAN ID, 802.1Q, **router-on-a-stick**)
> - [M2/UD2/L2 — Introduzione alla configurazione IP](../../M2_Protocolli_rete_TCP_IP/UD2/L2%20-%20Introduzione%20alla%20configurazione%20IP.md) (gateway predefinito, indirizzi privati)
> - [M2/UD2/L4 — Progetto di inter-reti](../../M2_Protocolli_rete_TCP_IP/UD2/L4%20-%20Progetto%20di%20inter-reti.md) (assegnazione subnet)
> - [M2/UD4/L1 — Indirizzi pubblici e privati](../../M2_Protocolli_rete_TCP_IP/UD4/L1%20-%20Indirizzi%20pubblici%20e%20privati.md) (RFC1918)

---

**Esercizio 2 (10 punti)** Due piani di un edificio, due switch (A piano terra, B primo piano), trunk su Gi0/24. VLAN Ufficio (10) e Wi-Fi (20) su entrambi; VLAN Internet (30) solo su SW-A, con router esterno su Gi0/1.

---

### Topologia

```
                        Internet
                            │
                            │  (porta WAN)
                       ┌────┴────┐
                       │ Router  │   (router-on-a-stick, sub-interfacce 802.1Q)
                       │ R_int   │
                       └────┬────┘
                            │  Gi0/0 (trunk con VLAN 10/20/30)
                            │
       ┌────────────────────┴───────────────────────┐
       │             Switch A (piano terra)          │
       │  Gi0/1  ─── trunk al router R_int           │
       │  Fa0/2-12  ─ access VLAN 10 (Ufficio)       │
       │  Fa0/13-23 ─ access VLAN 20 (Wi-Fi/AP)      │
       │  Gi0/24 ─── trunk verso SW-B                │
       └────────────────────┬───────────────────────┘
                            │  Gi0/24 ↔ Gi0/24 (cavo trunk)
                            │
       ┌────────────────────┴───────────────────────┐
       │           Switch B (primo piano)            │
       │  Fa0/2-12  ─ access VLAN 10 (Ufficio)       │
       │  Fa0/13-23 ─ access VLAN 20 (Wi-Fi/AP)      │
       │  Gi0/24 ─── trunk verso SW-A                │
       └────────────────────────────────────────────┘
```

### (1) Configurazione Cisco IOS

#### Switch A (piano terra)

```cisco
hostname SW-A
!
! ─── Creazione delle VLAN ─────────────────────────────────────
vlan 10
 name UFFICIO
vlan 20
 name WIFI
vlan 30
 name INTERNET
!
! ─── Porte di accesso per host ufficio (VLAN 10) ─────────────
interface range FastEthernet 0/2 - 12
 switchport mode access
 switchport access vlan 10
 spanning-tree portfast
!
! ─── Porte di accesso per gli AP Wi-Fi (VLAN 20) ─────────────
interface range FastEthernet 0/13 - 23
 switchport mode access
 switchport access vlan 20
 spanning-tree portfast
!
! ─── Porta trunk verso SW-B (Gi0/24) ─────────────────────────
interface GigabitEthernet 0/24
 description Trunk verso SW-B
 switchport trunk encapsulation dot1q
 switchport mode trunk
 switchport trunk allowed vlan 10,20         ! VLAN 30 NON serve su SW-B
 switchport trunk native vlan 99             ! native VLAN dedicata (best practice)
!
! ─── Porta trunk verso il router R_int (Gi0/1) ───────────────
!     PORTA DI TRUNK, non porta di accesso:
!     il router deve raggiungere DUE VLAN diverse (20 Wi-Fi, 30 Internet)
!     sullo stesso link fisico → router-on-a-stick (cfr. M1/UD2/L7 §9).
interface GigabitEthernet 0/1
 description Trunk verso Router R_int (router-on-a-stick)
 switchport trunk encapsulation dot1q
 switchport mode trunk
 switchport trunk allowed vlan 10,20,30      ! tutte le VLAN che il router deve gestire
!
end
```

#### Switch B (primo piano)

```cisco
hostname SW-B
!
! ─── Creazione delle VLAN ─────────────────────────────────────
vlan 10
 name UFFICIO
vlan 20
 name WIFI
! NB: la VLAN 30 (Internet) NON serve su SW-B: nessuna porta locale la usa.
!
! ─── Porte di accesso per host ufficio (VLAN 10) ─────────────
interface range FastEthernet 0/2 - 12
 switchport mode access
 switchport access vlan 10
 spanning-tree portfast
!
! ─── Porte di accesso per gli AP Wi-Fi (VLAN 20) ─────────────
interface range FastEthernet 0/13 - 23
 switchport mode access
 switchport access vlan 20
 spanning-tree portfast
!
! ─── Porta trunk verso SW-A (Gi0/24) ─────────────────────────
interface GigabitEthernet 0/24
 description Trunk verso SW-A
 switchport trunk encapsulation dot1q
 switchport mode trunk
 switchport trunk allowed vlan 10,20
 switchport trunk native vlan 99
!
end
```

#### Motivazione: porta verso il router → **TRUNK** (router-on-a-stick)

Il testo chiede esplicitamente di motivare la scelta. La porta `Gi0/1` di SW-A che va al router deve essere **trunk 802.1Q**, NON access, perché (cfr. M1/UD2/L7 §9):

| Motivazione | Spiegazione |
|---|---|
| **Multi-VLAN sul router** | Il router deve essere gateway di **tutte** le VLAN (10, 20, 30) → deve ricevere frame da/verso tutte le VLAN sulla stessa interfaccia fisica. |
| **Risparmio di porte fisiche** | Una sola interfaccia GigabitEthernet sul router (con sub-interfacce VLAN-aware) invece di 3 fisiche separate. |
| **Architettura standard** | È il pattern *router-on-a-stick*: una porta fisica + N sub-interfacce con tag 802.1Q diversi. |
| **Coerenza con il design** | Permette l'inter-VLAN routing senza dover passare per uno switch L3. |

Una porta di **access** avrebbe permesso al router di raggiungere solo **una** VLAN, e l'inter-VLAN routing sarebbe stato impossibile.

---

### (2) Piano di indirizzamento IP

#### Subnet per VLAN (RFC 1918, cfr. M2/UD4/L1)

| VLAN | Nome | Subnet | Maschera | Broadcast | Gateway (router) | Host utili |
|---|---|---|---|---|---|---|
| 10 | UFFICIO | `192.168.10.0/24` | `255.255.255.0` | `192.168.10.255` | `192.168.10.1` | `.2` – `.254` (253 host) |
| 20 | WIFI | `192.168.20.0/24` | `255.255.255.0` | `192.168.20.255` | `192.168.20.1` | `.2` – `.254` (253 host) |
| 30 | INTERNET | `192.168.30.0/30` | `255.255.255.252` | `192.168.30.3` | `192.168.30.1` (lato router R_int) | `.1` (R_int), `.2` (router esterno verso ISP) |

> Per la VLAN 30 (uplink verso il router esterno) basta un **/30** (cfr. M2/UD2/L4 — VLSM): è un link punto-punto fra due router, servono solo 2 indirizzi utili.

#### Indirizzi del router interno R_int (router-on-a-stick)

Il router ha **una sola interfaccia fisica** (es. `Gi0/0`) ma **tre sub-interfacce logiche**, una per VLAN. Ogni sub-interfaccia ha il proprio tag 802.1Q e funge da gateway della rispettiva subnet (cfr. M1/UD2/L7 §9):

```cisco
hostname R_int
!
interface GigabitEthernet 0/0
 no shutdown
 ! l'interfaccia fisica non riceve IP: serve solo come "portante"
 ! per le sub-interfacce VLAN-aware.
!
interface GigabitEthernet 0/0.10
 description Gateway VLAN 10 - UFFICIO
 encapsulation dot1Q 10
 ip address 192.168.10.1 255.255.255.0
!
interface GigabitEthernet 0/0.20
 description Gateway VLAN 20 - WI-FI
 encapsulation dot1Q 20
 ip address 192.168.20.1 255.255.255.0
!
interface GigabitEthernet 0/0.30
 description Uplink verso router esterno (VLAN 30)
 encapsulation dot1Q 30
 ip address 192.168.30.1 255.255.255.252
!
! ─── Default route: tutto il traffico non locale verso il router esterno ───
ip route 0.0.0.0 0.0.0.0 192.168.30.2
!
! ─── Routing inter-VLAN: già abilitato di default per le reti
!     direttamente connesse (cfr. M2/UD3/L1) ───────────────────────────────
ip routing
!
end
```

#### Tabella riassuntiva delle interfacce del router

| Sub-interfaccia | Tag 802.1Q | IP | Maschera | Ruolo |
|---|---|---|---|---|
| `Gi0/0.10` | 10 | `192.168.10.1` | `/24` | Gateway VLAN Ufficio |
| `Gi0/0.20` | 20 | `192.168.20.1` | `/24` | Gateway VLAN Wi-Fi |
| `Gi0/0.30` | 30 | `192.168.30.1` | `/30` | Uplink verso router esterno (Internet) |

#### Come la VLAN Wi-Fi (20) raggiunge Internet (via VLAN 30)

Sia `H_wifi = 192.168.20.50` un client Wi-Fi che vuole raggiungere `8.8.8.8`:

1. `H_wifi` calcola che `8.8.8.8` **non** appartiene a `192.168.20.0/24` → inoltra al gateway `192.168.20.1`.
2. Il client cerca il MAC di `192.168.20.1` via ARP (broadcast nella VLAN 20).
3. L'ARP request arriva agli switch SW-A e SW-B; gli AP la inoltrano. R_int (sulla sub-interfaccia `Gi0/0.20`, tag VLAN 20) risponde con il proprio MAC.
4. Il client incapsula il pacchetto IP in un frame Ethernet destinato al MAC di R_int (tag VLAN 20 nei segmenti di trunk).
5. R_int de-incapsula, consulta la routing table, applica la rotta di default `0.0.0.0/0 → 192.168.30.2`.
6. Riencapsula il pacchetto in un frame Ethernet con tag VLAN 30 e lo invia sulla stessa interfaccia fisica `Gi0/0` (sub-interfaccia `.30`).
7. SW-A vede il frame taggato VLAN 30 sul trunk `Gi0/1`, lo inoltra all'unica porta di accesso VLAN 30 (un'eventuale porta dedicata che porta al router esterno) o direttamente al router esterno collegato a Gi0/1 — dipende da come è cablato; tipicamente il router esterno è connesso direttamente in VLAN 30 con un cavo che esce dallo switch.
8. Il router esterno fa NAT e instrada verso Internet.

> Sul ritorno, il NAT del router esterno gestisce il mapping inverso e il pacchetto risale per la VLAN 30 → R_int → VLAN 20 → client Wi-Fi.

#### Realizzazione L3 fra VLAN Wi-Fi e VLAN Internet: **router-on-a-stick**

> **Risposta finale al punto (2)**: la connessione L3 tra le VLAN è realizzata mediante **router-on-a-stick** (M1/UD2/L7 §9). Il router interno R_int possiede una sola interfaccia fisica trunk verso SW-A e tre sub-interfacce, ciascuna con tag 802.1Q diverso e un IP gateway per la rispettiva VLAN.
>
> Alternativa tradizionale (sconsigliata per costo): tre interfacce fisiche separate, ciascuna in access su una VLAN diversa. È quella che il testo chiama "tradizionale". Si scarta perché:
> - richiede 3 cavi e 3 porte invece di 1;
> - non è scalabile (per aggiungere una VLAN bisognerebbe aggiungere un'altra interfaccia fisica);
> - moltiplica i punti di guasto.

---

## Esercizio 3 — Spoofing DHCP (Rogue DHCP Server)

> **Riferimenti di teoria**:
>
> - [M2/UD2/L5 — Assegnazione dinamica di indirizzi IP](../../M2_Protocolli_rete_TCP_IP/UD2/L5%20-%20Assegnazione%20dinamica%20di%20indirizzi%20IP.md) (motivazioni, lease)
> - [M2/UD2/L6 — DHCP (Dynamic Host Configuration Protocol)](<../../M2_Protocolli_rete_TCP_IP/UD2/L6%20-%20DHCP%20(Dynamic%20Host%20Configuration%20Protocol).md>) (DORA, broadcast, opzioni 1/3/6/51/53)
> - [M2/UD2/L1 — Internet Protocol (IP)](../../M2_Protocolli_rete_TCP_IP/UD2/L1%20-%20Internet%20Protocol%20(IP).md) (broadcast `255.255.255.255`)
> - [M1/UD2/L7 — VLAN e Trunking](../../M1_Tecniche_comunicazione_digitale/UD2/L7%20-%20VLAN%20e%20Trunking.md) (segmentazione domini broadcast, contromisure su switch)
> - [M2/UD3/L3 — Address Resolution Protocol (ARP)](../../M2_Protocolli_rete_TCP_IP/UD3/L3%20-%20Address%20Resolution%20Protocol%20(ARP).md) (analogo problema di trust nelle LAN — pattern di MITM)

### Inquadramento teorico

DHCP è un protocollo **fiducia-implicita**: ogni client invia in broadcast (`255.255.255.255`) e si fida del **primo** `DHCPOFFER` che riceve (o di quello che gli sembra migliore). Non c'è autenticazione né del server né del messaggio. Un attaccante che riesce a piazzare un proprio server DHCP nella stessa LAN (broadcast domain) può quindi **rispondere al posto del server legittimo**, ottenendo il controllo dei parametri di rete che le vittime useranno.

Questo attacco si chiama **DHCP spoofing** (o **rogue DHCP**). Una volta che la vittima accetta i parametri "ostili", l'attaccante può:

- diventare il **default gateway** della vittima → tutto il traffico verso Internet passa per lui → **Man-in-the-Middle (MITM)**;
- diventare il **DNS server** della vittima → può **redirigere** `bancaintesa.it` su un sito phishing locale → **furto di credenziali**;
- esfiltrare dati cifrati con TLS via *SSL stripping* o certificati malevoli (se la vittima accetta CA non legittime);
- raccogliere metadati di navigazione anche senza decifrare TLS.

### Scenario concreto

**Topologia**: una LAN aziendale, VLAN Ufficio (10), `192.168.10.0/24`.

| Host | Ruolo | IP | MAC |
|---|---|---|---|
| `gw-legit` | Router/server DHCP **legittimo** | `192.168.10.1` | `aa:aa:aa:aa:aa:01` |
| `dns-legit` | DNS interno | `192.168.10.2` | `aa:aa:aa:aa:aa:02` |
| `attacker` | PC dell'attaccante (con `dnsmasq` o `dhcpd`) — **server DHCP rogue** | `192.168.10.66` | `bb:bb:bb:bb:bb:bb` |
| `alice` | Vittima (laptop nuovo che si collega) | da assegnare | `cc:cc:cc:cc:cc:cc` |

L'attaccante ha già un *toehold* nella LAN (ad es. una porta libera in sala riunioni, un cavo Ethernet non protetto, o un *poisoning* via switch port mal configurata) e ha avviato sul proprio PC un demone tipo:

```bash
# /etc/dnsmasq.conf  (server DHCP malevolo)
interface=eth0
dhcp-range=192.168.10.150,192.168.10.200,12h
dhcp-option=option:router,192.168.10.66           # ← gateway = attaccante!
dhcp-option=option:dns-server,192.168.10.66       # ← DNS = attaccante!
dhcp-option=option:netmask,255.255.255.0
```

### Sequenza di messaggi DHCP (D-O-R-A "corrotta")

```
┌── alice (nuovo client, no IP) ──────────────────────────────────────────────────┐
│                                                                                  │
│  1. DHCPDISCOVER                                                                 │
│      ─── broadcast (FF:FF:FF:FF:FF:FF, IP 255.255.255.255) ────────────────►     │
│      ricevuto da gw-legit (192.168.10.1) E da attacker (192.168.10.66)          │
│                                                                                  │
│  2a. DHCPOFFER (dal server legittimo)                                            │
│      ◄── unicast dal MAC di gw-legit ──── arrivo ritardato (~50 ms)              │
│      yiaddr        = 192.168.10.55                                               │
│      option 1  netmask         = 255.255.255.0                                   │
│      option 3  router          = 192.168.10.1   ← gateway VERO                  │
│      option 6  dns-server      = 192.168.10.2   ← DNS VERO                       │
│      option 51 lease           = 86400 s                                         │
│      siaddr/server_id          = 192.168.10.1                                    │
│                                                                                  │
│  2b. DHCPOFFER (dall'ATTACCANTE)         ◄── arrivo ANTICIPATO (~5 ms)           │
│      yiaddr        = 192.168.10.150                                              │
│      option 1  netmask         = 255.255.255.0                                   │
│      option 3  router          = 192.168.10.66  ← gateway FALSO (l'attaccante)  │
│      option 6  dns-server      = 192.168.10.66  ← DNS FALSO (l'attaccante)       │
│      option 51 lease           = 43200 s        ← magari più breve, per          │
│                                                  riprendere il controllo prima  │
│      siaddr/server_id          = 192.168.10.66                                   │
│                                                                                  │
│      ►►► alice accetta la prima offerta che le arriva: quella dell'attaccante    │
│                                                                                  │
│  3. DHCPREQUEST                                                                  │
│      ─── broadcast ──────────────────►                                           │
│      requested_ip               = 192.168.10.150                                 │
│      server_id (option 54)      = 192.168.10.66   ← conferma scelta dell'attacker│
│                                                                                  │
│      gw-legit vede `server_id != 192.168.10.1` → ritira silenziosamente la        │
│      sua offerta. L'attacker capisce che ha vinto la gara.                       │
│                                                                                  │
│  4. DHCPACK (dall'attaccante)                                                    │
│      ◄── unicast ───────                                                         │
│      yiaddr        = 192.168.10.150                                              │
│      tutti i parametri di prima confermati                                       │
│                                                                                  │
└─────────────────────────────────────────────────────────────────────────────────┘
```

### Campi falsificati dall'attaccante

Tutti i campi importanti del `DHCPOFFER`/`DHCPACK` malevolo sono "regolari" dal punto di vista di formato (il client non ha modo di accorgersene), ma puntano all'attaccante:

| Campo / Opzione | Valore legittimo | Valore falsificato dall'attaccante | Effetto |
|---|---|---|---|
| `siaddr` / `server_id` (opt. 54) | `192.168.10.1` | `192.168.10.66` | Identifica l'attaccante come "il server DHCP" |
| `yiaddr` | da pool del DHCP vero | da pool dell'attaccante (`.150`) | La vittima accetta un IP del range del rogue |
| **option 3** `router` (default gateway) | `192.168.10.1` | **`192.168.10.66`** | **MITM — tutto il traffico verso Internet passa dall'attaccante** |
| **option 6** `dns-server` | `192.168.10.2` | **`192.168.10.66`** | **DNS hijacking — siti redirezionati** |
| option 1 `netmask` | `/24` | `/24` (lascia uguale per non insospettire) | — |
| option 51 `lease time` | 24 h | spesso più breve (12 h) | L'attaccante "rinegozia" prima |
| `chaddr` (MAC del client) | quello di Alice | quello di Alice (non si tocca) | — |

> A livello di formato, **nessun byte** è "fuori specifica RFC 2131": l'attacco vive interamente del **conflitto a chi risponde per primo** (race condition di rete).

### Conseguenze per le vittime

#### 1. Man-in-the-Middle integrale

Dopo l'accettazione del lease malevolo, Alice ha questa configurazione:

```
ip addr:        192.168.10.150/24
default route:  192.168.10.66           ← attacker
dns:            192.168.10.66           ← attacker
```

Qualunque pacchetto verso Internet (http, https, smtp, ssh, …) viene inviato in frame Ethernet con `dst MAC = MAC(attacker)`. L'attaccante:

- **inoltra** il traffico verso il gateway vero (forwarding abilitato) per mantenere la connettività e non insospettire la vittima;
- nel frattempo **ispeziona, modifica o registra** il traffico in chiaro (HTTP, FTP, telnet, DNS, alcuni protocolli IoT).

#### 2. DNS hijacking → phishing

L'attaccante risponde alle query DNS della vittima come gli pare:

```
alice ─ "www.bancaintesa.it ?" ─► 192.168.10.66
              ◄─ "157.66.66.66 (server phishing dell'attaccante)" ─
```

Alice viene servita una **pagina clone** del sito bancario; se inserisce credenziali (e magari un OTP), l'attaccante le acquisisce in tempo reale. È il pattern classico di **furto di credenziali** via DHCP spoofing.

#### 3. SSL stripping / certificati malevoli

Se l'attaccante riesce anche a installare una CA root malevola sul dispositivo (es. tramite captive portal manipolato), può intercettare anche TLS — altrimenti la vittima riceve un warning del browser, che molti utenti ignorano.

#### 4. Esfiltrazione metadati

Anche senza decifrare TLS, l'attaccante vede:
- a quali IP la vittima si connette (`5-tuple`);
- pattern temporali (analisi del traffico);
- le query DNS in chiaro (a meno che la vittima usi DoH/DoT);
- header SNI nei `ClientHello` TLS (rivela il dominio).

#### 5. Denial-of-Service involontario

Se due server DHCP (legittimo e rogue) assegnano IP da pool sovrapposti, si verificano **conflitti d'IP** sulla LAN: alcuni host smettono di funzionare casualmente. L'attaccante può sfruttare il caos per nascondere meglio il proprio operato.

### Contromisure

#### Contromisura semplice e standard: **DHCP Snooping** (a livello di switch)

Lo switch — quando ha la feature attiva — distingue le porte in **trusted** e **untrusted**:

| Tipo porta | Cosa è ammesso |
|---|---|
| **Trusted** (es. uplink al server DHCP vero / al router) | Tutti i messaggi DHCP, inclusi `DHCPOFFER` e `DHCPACK` server→client |
| **Untrusted** (porte dei client) | Solo messaggi **client→server**: `DHCPDISCOVER`, `DHCPREQUEST`, `DHCPRELEASE`. Tutto ciò che è **server→client** (cioè *response*) viene **scartato** dallo switch |

Configurazione Cisco (sintesi):

```cisco
! Abilita DHCP snooping globalmente e nelle VLAN sensibili
ip dhcp snooping
ip dhcp snooping vlan 10,20
!
! Porta trusted: solo verso il server DHCP legittimo (o router)
interface GigabitEthernet 0/1
 description Uplink al router/server DHCP legittimo
 ip dhcp snooping trust
!
! Porte di accesso utenti: untrusted per default → scarta DHCPOFFER da loro
interface range FastEthernet 0/2 - 23
 description Porte client (untrusted)
 ip dhcp snooping limit rate 10        ! anti-DoS sul numero di DHCP/sec
```

Con questa configurazione:

- se l'attaccante è collegato a una porta access "untrusted" e invia un `DHCPOFFER`, lo **switch scarta** il frame prima ancora che esca dalla porta;
- la vittima riceverà solo l'`OFFER` legittimo dal `gw-legit` → niente MITM.

#### Contromisure complementari (cenni)

| Contromisura | Livello | Cosa fa |
|---|---|---|
| **Port Security** | L2 (switch) | Limita il numero di MAC per porta, blocca MAC sconosciuti |
| **Dynamic ARP Inspection (DAI)** | L2 (switch) | Verifica le ARP reply confrontandole con la DHCP snooping binding table → blocca ARP spoofing complementare |
| **IP Source Guard** | L2 (switch) | Permette solo l'IP che la DHCP snooping ha registrato per quella porta |
| **802.1X** | L2 (switch) | Autentica gli host prima di consentirgli accesso alla porta → impedisce all'attaccante di collegarsi |
| **DHCPv6 + RA Guard** | L3 (IPv6) | Versione IPv6 dei pari controlli, contro rogue router advertisements |
| **Segmentazione VLAN** | L2 | Confinare il dominio broadcast: più sono piccoli, più è difficile per l'attaccante raggiungere le vittime |
| **Monitoraggio** | L7 (SOC) | Allarmi su `DHCPOFFER` da source MAC non in whitelist → caccia attiva al rogue server |

### Sintesi finale

| Aspetto | Sintesi |
|---|---|
| **Cosa fa l'attaccante** | Inietta un server DHCP malevolo in LAN; risponde a `DHCPDISCOVER` prima del server legittimo |
| **Cosa falsifica** | Soprattutto **option 3 (gateway)** e **option 6 (DNS)** del `DHCPOFFER`/`DHCPACK` |
| **Cosa ottiene** | MITM completo: vede/altera tutto il traffico in chiaro, fa phishing via DNS spoofing, raccoglie credenziali |
| **Contromisura semplice** | **DHCP Snooping** sullo switch: porte trusted = solo verso server DHCP vero; porte untrusted = niente OFFER/ACK in uscita |
