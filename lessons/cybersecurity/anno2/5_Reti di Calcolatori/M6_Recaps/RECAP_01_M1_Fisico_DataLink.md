# RECAP 01 — Modulo M1 condensato (Fisico + Data Link)

> Copre tutto M1: segnali, multiplexing, Data Link, Ethernet (frame, CSMA/CD, switch), ISO/OSI, hardware di rete. Le **lezioni in stampa integrale** sono `M1/UD2/L4`, `M1/UD2/L5`, `M1/UD2/L7` (le tre core di Ethernet/VLAN).
> Questo recap copre **il resto**: tutto ciò che NON è stato stampato integrale.

---

## 1. Segnali (UD1/L1)

### Definizioni base

- **Segnale** = grandezza fisica che varia nel tempo e trasporta informazione (elettrico, ottico, EM, acustico).
- **Forma d'onda** = grafico ampiezza-tempo del segnale, visualizzabile su oscilloscopio.
- **Segnale periodico**: $s(t) = s(t + T)$. Periodo $T$, frequenza $f = 1/T$ Hz.

### Onda sinusoidale (mattone di tutti i segnali)

$$
s(t) = A \sin(2\pi f t + \varphi)
$$
- $A$ ampiezza, $f$ frequenza, $\varphi$ fase.

### Lunghezza d'onda (EM)

$$
\boxed{\lambda = \frac{v}{f}}
$$
- $v \approx 3 \cdot 10^8$ m/s per onde EM nel vuoto.
- Esempio: $f = 900$ MHz $\Rightarrow \lambda = 0{,}33$ m.

### Fourier

> Ogni segnale periodico è somma di sinusoidi a frequenze multiple della fondamentale (**armoniche**).

Onda quadra: $s(t) = \sin(\omega t) + \frac{1}{3}\sin(3\omega t) + \frac{1}{5}\sin(5\omega t) + \dots$

### Larghezza di banda

| Esempio | Banda |
|---|:---:|
| Telefonata | 4 kHz |
| Radio AM | 10 kHz |
| Radio FM | 200 kHz |
| Canale TV | 6 MHz |

**Capacità del canale** è proporzionale alla banda (vedi Shannon-Hartley).

### Filtri

| Tipo | Lascia passare |
|---|---|
| **LPF** Low-Pass | Solo basse frequenze |
| **HPF** High-Pass | Solo alte frequenze |
| **BPF** Band-Pass | Solo una banda |
| **BSF** Band-Stop | Tutto tranne una banda |

---

## 2. Reti telefoniche e mobili (UD1/L2-L3)

- **PSTN** (Public Switched Telephone Network): commutazione di circuito, segnalazione SS7.
- **GSM (2G)**: TDMA, 200 kHz portante.
- **UMTS (3G)**: CDMA, 5 MHz portante.
- **LTE (4G), 5G**: OFDMA, scalable bandwidth.

> **Quasi mai chiesto**. Solo cenni.

---

## 3. Multiplexing digitale (UD1/L4)

### Gerarchia americana (T-carrier)

| Livello | Bit-rate | Canali DS-0 | Overhead |
|:--:|:--:|:--:|:--:|
| DS-0 | 64 kbps | 1 | — |
| DS-1 (T1) | 1.544 Mbps | 24 | 8 kbps |
| DS-2 | 6.312 Mbps | 96 | 168 kbps |
| DS-3 | 44.736 Mbps | 672 | 1.728 kbps |
| DS-4 | 274.176 Mbps | 4032 | 16.128 kbps |

### Gerarchia europea (E-carrier)

| Livello | Bit-rate | vs livello inferiore |
|:--:|:--:|:--:|
| E1 | 2.048 Mbps | — |
| E2 | 8.448 Mbps | 4 × E1 |
| E3 | 34.368 Mbps | 16 × E1 |
| E4 | 139.264 Mbps | 4 × E3 |

### Tecniche di accesso multiplo

| Tecnica | Idea | Esempio |
|---|---|---|
| **FDMA** | Ogni utente su una **frequenza diversa** | Radio analogiche, 1G |
| **TDMA** | Ogni utente in uno **slot temporale diverso** | GSM (2G), T1/E1 |
| **CDMA** | Tutti sulla **stessa banda con codici ortogonali** | IS-95, UMTS (3G) |

---

## 4. ISO/OSI vs TCP/IP (UD3/L1-L2)

### Pila ISO/OSI a 7 livelli

| # | Livello | Funzione | Esempio |
|:--:|---|---|---|
| 7 | **Applicazione** | Servizi applicativi | HTTP, FTP, SMTP, DNS |
| 6 | **Presentazione** | Codifica, cifratura, compressione | TLS, JPEG, ASCII |
| 5 | **Sessione** | Apertura/chiusura sessioni | NetBIOS, RPC |
| 4 | **Trasporto** | Comunicazione end-to-end, affidabilità | TCP, UDP |
| 3 | **Rete** | Instradamento pacchetti | IP, ICMP, ARP |
| 2 | **Data Link** | Frame, MAC, controllo errore link | Ethernet, PPP, 802.11 |
| 1 | **Fisico** | Bit sul mezzo | Cavo, fibra, radio |

### Modello TCP/IP (4 livelli)

| TCP/IP | OSI equivalente |
|---|---|
| Application | 5+6+7 |
| Transport | 4 |
| Internet | 3 |
| Network Interface | 1+2 |

### PDU per livello

| Livello | Nome PDU |
|---|---|
| Applicazione | Message / Data |
| Trasporto | **Segment** (TCP) / **Datagram** (UDP) |
| Rete | **Packet** |
| Data Link | **Frame** |
| Fisico | **Bit** |

---

## 5. Data Link e sottolivello MAC (UD2/L1-L2)

### Funzioni del livello 2

1. **Framing**: delimita inizio/fine del frame (preambolo + SFD).
2. **Indirizzamento fisico**: MAC src/dst.
3. **Controllo d'errore**: CRC (rilevamento, non correzione).
4. **Controllo di accesso al mezzo**: chi parla quando (sottolivello MAC).
5. **Controllo di flusso**: a livello link (raro, poco usato in Ethernet).

### Architettura IEEE 802

Il livello 2 IEEE è diviso in:
- **LLC (Logical Link Control)** — IEEE 802.2, comune a tutte le tecnologie 802.
- **MAC (Medium Access Control)** — specifico (802.3 Ethernet, 802.11 Wi-Fi, …).

### Indirizzo MAC

- **48 bit** scritti in esadecimale: `00-1A-2B-3C-4D-5E`.
- Primi 24 bit = **OUI** (produttore); ultimi 24 bit = id interfaccia.
- **Unico** (in teoria) per ogni NIC del mondo.
- **Broadcast** = `FF:FF:FF:FF:FF:FF`.
- **Multicast** = primo bit del primo byte = 1.

---

## 6. Ethernet (UD2/L3-L6) — supplemento al `L4` stampato

### Standard 802.3 — varianti principali

| Standard | Bit-rate | Mezzo | Distanza tipica |
|---|---|---|---|
| 10BASE-T | 10 Mbps | UTP cat. 3 | 100 m |
| 100BASE-TX | 100 Mbps | UTP cat. 5 | 100 m |
| 1000BASE-T | 1 Gbps | UTP cat. 5e/6 | 100 m |
| 10GBASE-T | 10 Gbps | UTP cat. 6a | 100 m |
| 1000BASE-LX | 1 Gbps | Fibra ottica | km |

### Switched Ethernet (UD2/L6)

- **Switch** = bridge multi-porta, opera a livello 2.
- Mantiene una **MAC address table** (CAM) con mapping MAC → porta.
- **Apprendimento dinamico**: legge il MAC sorgente di ogni frame e lo associa alla porta di ingresso.
- **Forwarding**: se MAC dst è in tabella → invia solo a quella porta; altrimenti **flooding** su tutte le porte tranne quella di ingresso.
- Elimina le collisioni (rispetto all'hub) creando **un dominio di collisione per ogni porta**.

### Modalità di commutazione (UD4/L9)

| Modalità | Quando inoltra | Pro | Contro |
|---|---|---|---|
| **Store-and-forward** | Dopo aver letto tutto il frame e verificato CRC | Filtra frame errati | Latenza maggiore |
| **Cut-through** | Appena letto MAC dst (primi 6 byte) | Latenza minima | Inoltra anche frame corrotti |
| **Fragment-free** | Dopo i primi 64 byte (la "collision window") | Compromesso | Latenza media |

---

## 7. Hardware di rete (UD4)

### Hub / Repeater (L1)
- Dispositivo **layer 1**: ripete il segnale su tutte le porte.
- Tutti gli host condividono lo stesso **dominio di collisione** e di **broadcast**.
- **Obsoleto** (sostituito dagli switch).

### Bridge (L2)
- Antenato dello switch: 2 porte, separa due segmenti di rete in due domini di collisione distinti.

### Switch (L2)
- Bridge multi-porta hardware veloce. **Pilastro delle LAN moderne**.

### Router (L3)
- Opera a livello rete: instrada pacchetti IP tra reti diverse.
- Separa sia dominio di collisione sia dominio di broadcast.

### Schema riassuntivo

| Dispositivo | Livello | Domini di collisione | Domini di broadcast |
|---|:--:|:--:|:--:|
| Hub | 1 | 1 (condiviso) | 1 |
| Switch | 2 | 1 per porta | 1 (per VLAN) |
| Router | 3 | 1 per porta | 1 per porta |

---

## 8. Topologie ridondanti e Spanning Tree (UD4/L2-L3)

### Problema delle topologie ridondanti

In una rete switched con più cammini tra switch:
- **Broadcast storm**: un frame di broadcast circola indefinitamente.
- **MAC table instability**: lo stesso MAC viene visto su porte diverse.
- **Duplicazione frame**.

### Spanning Tree Protocol (STP — IEEE 802.1D)

- Costruisce un **albero ricoprente** del grafo degli switch, **disattivando** logicamente alcuni link.
- Elegge un **Root Bridge** (lowest Bridge ID).
- Ogni switch sceglie una **Root Port** (verso il root al costo minimo).
- Per ogni segmento, eletto un **Designated Port**.
- Porte non Root né Designated → **bloccate**.
- BPDU (Bridge Protocol Data Unit) scambiati ogni 2 s.
- Convergenza ~30-50 s in 802.1D; molto più rapida con RSTP (802.1w).

> Mai esplicitamente chiesto nei 4 esami, ma "se cade un link in una rete con due switch, cosa succede?" è una domanda possibile → risposta = STP riconfigura l'albero.

---

## 9. VTP (Vlan Trunking Protocol)

- Protocollo **Cisco proprietario** per propagare la configurazione VLAN tra switch.
- Modalità: **server** (può modificare), **client** (riceve solo), **transparent** (non partecipa).
- Numero di revisione: il database più recente vince.

> Possibile contesto per domande VLAN, ma non centrale.

---

## 10. Formule e numeri da ricordare

| Quantità | Valore |
|---|:--:|
| Velocità EM nel vuoto | $3 \cdot 10^8$ m/s |
| Velocità segnale in cavo Ethernet | $\sim 2 \cdot 10^8$ m/s (66% c) |
| Lunghezza d'onda | $\lambda = v/f$ |
| Lunghezza minima frame Ethernet | **64 byte = 512 bit** |
| Tempo massimo round-trip Ethernet | **51.2 μs** (a 10 Mbps) |
| Tempo trasmissione 1 bit a 10 Mbps | 0.1 μs |
| Tempo trasmissione 1 bit a 100 Mbps | 0.01 μs |
| Tempo trasmissione 1 bit a 1 Gbps | 1 ns |
| Back-to-back delay | 9.6 μs (a 10 Mbps), 0.96 μs (100), 96 ns (1G) |
| MAC broadcast | `FF:FF:FF:FF:FF:FF` |
| EtherType IPv4 | `0x0800` |
| EtherType ARP | `0x0806` |
| EtherType IPv6 | `0x86DD` |
| EtherType 802.1Q | `0x8100` |
| Tag VLAN 802.1Q | 4 byte (TPID + TCI con VLAN ID a 12 bit) |
| ID VLAN | 1 – 4094 (0 e 4095 riservati) |

---

## 11. Cheat-sheet "se al prof chiede X, dico Y"

| Se chiede... | Rispondo includendo... |
|---|---|
| Calcolo distanza max Ethernet | $L_{min} = 2T \cdot R \Rightarrow T = L_{min}/(2R) \Rightarrow d = v \cdot T$, con $v \approx 2 \cdot 10^8$ m/s |
| Configurazione VLAN + trunk | Cisco IOS: `vlan 10/20/30`, `switchport mode access` + `switchport access vlan X` per host, `switchport mode trunk` + `switchport trunk allowed vlan ...` per i trunk |
| Inter-VLAN routing | **Router-on-a-stick**: trunk verso il router, sub-interface per VLAN (`encapsulation dot1Q X`), ognuna con IP gateway della relativa subnet |
| Differenza hub/switch/router | Vedi tabella §7 (dominio collisione, dominio broadcast) |
| Indirizzo MAC vs IP | MAC = fisico, 48 bit, livello 2, locale alla LAN. IP = logico, 32 bit, livello 3, globalmente instradabile |
