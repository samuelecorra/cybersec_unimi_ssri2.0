# RECAP 04 — Routing dinamico, IPv6, IPsec

> Recap dei moduli M2/UD7 (instradamento dinamico) + UD8 (IPv6) + UD9 (IPsec). Nessuna di queste lezioni è in stampa integrale (tranne `L3` di UD7 che è nei TIER 1 di fatto perché serve per Dijkstra). Questo file copre TUTTO ciò che serve di queste UD per una risposta completa.

---

## 1. Tipi di instradamento

### Statico vs dinamico

| | Statico | Dinamico |
|---|---|---|
| Configurazione | Manuale (admin) | Automatica (protocollo) |
| Adattamento ai guasti | No | Sì |
| Overhead | Nullo | Pacchetti di routing |
| Quando si usa | Reti piccole, rotte di default, fallback | Reti medie/grandi, ridondanze |

### Intradominio (IGP) vs interdominio (EGP)

- **IGP (Interior Gateway Protocol)**: dentro un singolo AS (Autonomous System). Esempi: **RIP**, **OSPF**, **IS-IS**, EIGRP.
- **EGP (Exterior Gateway Protocol)**: tra AS diversi. Standard moderno: **BGP**.

### Distance Vector vs Link State

| | Distance Vector | Link State |
|---|---|---|
| Conoscenza | Solo dei vicini | Topologia completa (LSDB) |
| Algoritmo | Bellman-Ford | Dijkstra |
| Aggiornamento | Periodico (30 s in RIP) | LSA flooding all'evento |
| Convergenza | Lenta | Rapida |
| Problemi tipici | Count-to-infinity | LSDB grande |
| Esempi | RIP, RIPv2 | OSPF, IS-IS |

---

## 2. RIP — Routing Information Protocol (UD7/L2)

### Caratteristiche
- IGP **Distance Vector**.
- Metrica = **numero di hop** (max 15; 16 = infinity).
- Update periodici ogni **30 s** (UDP 520).
- Convergenza lenta.

### Algoritmo Bellman-Ford

Per ogni nodo $j$, vicino $i$, destinazione $P$:
$$
d_{jP} = \min_i (d_{ji} + d_{iP})
$$

### Tabella RIP di un router

| Destinazione | Next hop | Metrica (hop) |
|---|---|:--:|

### Problema "Count-to-infinity"

Quando un link cade, i router continuano a propagare informazioni stale, incrementando lentamente la metrica fino a 16 (infinity).

### Contromisure

| Tecnica | Idea |
|---|---|
| **Split Horizon** | Non annuncia una rotta sull'interfaccia da cui è stata appresa |
| **Poison Reverse** | Annuncia con metrica 16 (infinity) la rotta verso il vicino da cui l'ha imparata |
| **Triggered Update** | Annuncio immediato (non solo a 30 s) quando cambia una rotta |
| **Hold-down timer** | Ignora annunci migliori per un po' dopo aver visto un loss |
| **Hop limit = 15** | Limita propagazione di route stale |

### RIPv2 (RFC 2453)

Migliorie:
- Supporta **CIDR** (classless): include subnet mask nei messaggi
- **Next hop** esplicito (non sempre il router che annuncia)
- **Autenticazione** dei messaggi
- **Multicast** (224.0.0.9) anziché broadcast

> Limite di 15 hop e convergenza lenta restano → **non adatto a reti grandi**.

---

## 3. OSPF — Open Shortest Path First (UD7/L3-L5)

### Caratteristiche
- IGP **Link State** (RFC 2328).
- Ogni router ha una copia **identica** del **Link State Database (LSDB)** dell'intero AS (o area).
- Calcola le rotte localmente con **algoritmo di Dijkstra** (SPF).
- Convergenza **molto rapida**.
- Supporta CIDR, VLSM, **multipath ECMP**.
- IP protocol number 89.

### Algoritmo di Dijkstra — pseudocodice

```text
function Dijkstra(G, sorgente s):
    for each v in V:
        d[v] ← ∞;  prev[v] ← undefined
    d[s] ← 0
    S ← ∅                  # nodi "definitivi"
    Q ← V                  # coda di priorità
    while Q non vuoto:
        u ← extract_min(Q)
        S ← S ∪ {u}
        for each vicino v di u (con v ∉ S):
            if d[u] + w(u,v) < d[v]:
                d[v] ← d[u] + w(u,v)
                prev[v] ← u
    return d, prev
```

**Complessità**: $O((|N| + |A|) \log |N|)$ con heap binario.

**Vincolo**: tutti i pesi $\ge 0$. Su grafi non orientati, le distanze sono simmetriche.

> Esempio risolto: vedi `Soluzione_esame_2024.md` (Esercizio 3) per il trace passo-passo.

### Struttura gerarchica OSPF

- L'AS è diviso in **aree** (Area 0 = Backbone, le altre vi confluiscono).
- Tipi di area:
  - **Backbone (Area 0)**: cuore, deve esistere.
  - **Stub**: pacchetti non escono dall'area.
  - **Transit**: attraversata da pacchetti diretti altrove.
- Tipi di router:
  - **Internal Router (IR)**: dentro un'area.
  - **Area Border Router (ABR)**: tra area X e backbone.
  - **AS Boundary Router (ASBR)**: tra OSPF e un altro protocollo (es. BGP).

### Fasi operative OSPF

| Fase | Cosa fa |
|---|---|
| **Hello** | Pacchetti periodici (10 s) per scoprire e mantenere i vicini |
| **Synchronization (DBD/LSR/LSU)** | Sincronizza LSDB con i vicini |
| **Flooding (LSA)** | Annunci dei cambi topologici, propagati a tutta l'area |
| **SPF (Dijkstra)** | Ogni router calcola il SPT radicato in sé |

### Metriche OSPF

Costo di un link basato sulla **banda di riferimento** (default 100 Mbps):

$$
\text{Cost} = \frac{\text{Bandwidth riferimento (bps)}}{\text{Bandwidth interfaccia (bps)}}
$$

Esempi (con riferimento 100 Mbps):

| Interface | Bandwidth | Cost |
|---|---|:--:|
| FastEthernet (100 Mbps) | $10^8$ | **1** |
| Ethernet (10 Mbps) | $10^7$ | **10** |
| T1 (1.544 Mbps) | $1{,}544 \cdot 10^6$ | **64** |
| 56 kbps | $56 \cdot 10^3$ | **1785** |

> Con Gigabit/10G moderni si usa una **reference bandwidth** maggiore (es. 100 Gbps).

### Tipi di LSA

| Tipo | Nome | Origine |
|:--:|---|---|
| 1 | Router LSA | Ogni router (link interni all'area) |
| 2 | Network LSA | DR (Designated Router) di un segmento broadcast |
| 3 | Summary LSA | ABR (riassume route inter-area) |
| 4 | ASBR Summary | ABR (per raggiungere un ASBR) |
| 5 | External LSA | ASBR (route esterne) |

### Indirizzi multicast OSPF
- **224.0.0.5** — All OSPF Routers
- **224.0.0.6** — All OSPF DR Routers

---

## 4. BGP — Border Gateway Protocol (UD7/L6)

### Caratteristiche
- **EGP** unico in uso oggi (BGP-4, RFC 4271).
- Distance Vector con **path vector** (porta tutto l'AS-path).
- Trasporto: **TCP porta 179** (no broadcast/multicast).
- Decisioni basate su **policy** (non solo metriche).

### Tipi
- **eBGP**: tra router di AS diversi.
- **iBGP**: tra router dello stesso AS (sincronizzazione).

### Attributi principali
- **AS-Path**: lista degli AS attraversati (usato per loop detection).
- **Next-Hop**: prossimo hop IP.
- **Local Preference**: preferenza locale dell'AS.
- **MED (Multi-Exit Discriminator)**: indicazione per ingress traffic.
- **Community**: tag per policy.

### Selezione del percorso (semplificata)

1. Preferisci `Local Preference` più alta.
2. Preferisci AS-Path più corto.
3. Preferisci `MED` più basso (verso lo stesso AS).
4. Preferisci eBGP su iBGP.
5. ...

> Mai chiesto in dettaglio nei 4 esami. Solo sapere che BGP esiste e cosa fa.

---

## 5. IPv6 (UD8/L1-L2)

### Motivazioni
- Esaurimento IPv4 (4 miliardi di indirizzi insufficienti).
- Header IPv4 complesso (frammentazione, opzioni, checksum).
- Sicurezza non integrata.

### Indirizzo IPv6
- **128 bit** (vs 32 bit di IPv4) → $\sim 3{,}4 \cdot 10^{38}$ indirizzi.
- Notazione esadecimale, 8 gruppi di 4 hex separati da `:`:
  ```
  2001:0db8:85a3:0000:0000:8a2e:0370:7334
  ```
- **Compressione**:
  - Omettere zeri leading: `2001:db8:85a3:0:0:8a2e:370:7334`
  - Sostituire un gruppo di `0000` consecutivi con `::` (solo una volta): `2001:db8:85a3::8a2e:370:7334`

### Struttura
- Primi 64 bit = **prefisso di rete** (subnet)
- Ultimi 64 bit = **Interface ID** (spesso derivato dal MAC con EUI-64)

### Header IPv6 — 40 byte fissi

```
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|Version| Traffic Class |              Flow Label               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|         Payload Length        |  Next Header  |   Hop Limit   |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                                                               |
+                       Source Address (128 bit)                +
|                                                               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                                                               |
+                    Destination Address (128 bit)              +
|                                                               |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
```

### Differenze IPv6 vs IPv4

| Aspetto | IPv4 | IPv6 |
|---|---|---|
| Indirizzo | 32 bit | 128 bit |
| Header | 20-60 byte | 40 byte fissi |
| Frammentazione | A router e host | Solo host (Path MTU Discovery obbligatoria) |
| Checksum header | Sì (ricalcolato ad ogni hop) | **No** (semplifica router) |
| Broadcast | Sì | **No** (sostituito da multicast) |
| Autoconfigurazione | DHCP | **SLAAC** + opzionale DHCPv6 |
| Sicurezza | IPsec opzionale (estensione) | **IPsec obbligatorio (in teoria)** |
| Mobilità | Mobile IP "bolt-on" | Mobile IPv6 integrato |
| QoS | ToS | Flow Label + Traffic Class |

### Tipi di indirizzi IPv6

| Tipo | Prefisso | Uso |
|---|---|---|
| **Unicast globale** | `2000::/3` | Internet pubblico |
| **Link-local** | `fe80::/10` | Solo nel link (analogo a 169.254 di IPv4) |
| **Multicast** | `ff00::/8` | Sostituisce broadcast |
| **Loopback** | `::1/128` | localhost |
| **Unspecified** | `::/128` | "non assegnato" |
| **Unique Local** | `fc00::/7` | Equivalente RFC 1918 di IPv4 |

### Indirizzi multicast notevoli

| Indirizzo | Significato |
|---|---|
| `ff02::1` | Tutti i nodi sul link |
| `ff02::2` | Tutti i router sul link |
| `ff02::5` | OSPFv3 routers |
| `ff02::1:ff00:0/104` | Solicited-node (per Neighbor Discovery) |

### Transizione IPv4 → IPv6
- **Dual stack**: host che parlano entrambi.
- **Tunneling** (6in4, 6to4, Teredo): IPv6 incapsulato in IPv4.
- **NAT64 / DNS64**: traduzione protocollo.

---

## 6. IPsec (UD9/L1) — Sicurezza a livello IP

### Motivazione
- IP non offre **autenticazione, integrità, confidenzialità**.
- IPsec lavora a livello 3 → protegge **tutto** il traffico, trasparente alle applicazioni.

### Protocolli IPsec

| Protocollo | IP proto | Funzione |
|---|:--:|---|
| **AH** (Authentication Header) | 51 | Autenticazione + integrità (non confidenzialità) |
| **ESP** (Encapsulating Security Payload) | 50 | Autenticazione + integrità + confidenzialità (cifratura) |
| **IKE** (Internet Key Exchange) | UDP 500 | Scambio chiavi automatico |

### Modalità

| Modalità | Cosa cifra/autentica | Usato per |
|---|---|---|
| **Transport mode** | Solo payload del pacchetto IP | Comunicazione end-to-end host ↔ host |
| **Tunnel mode** | L'intero pacchetto IP originale, incapsulato in un nuovo header IP | VPN site-to-site (gateway ↔ gateway) |

### Header AH (semplificato)

```
+---------------+---------------+---------------+---------------+
| Next Header   | Payload Len   |         Reserved             |
+---------------+---------------+---------------+---------------+
|              Security Parameters Index (SPI)                  |
+---------------+---------------+---------------+---------------+
|                  Sequence Number                              |
+---------------+---------------+---------------+---------------+
|              Authentication Data (ICV)                        |
+---------------+---------------+---------------+---------------+
```

- **SPI**: identifica la Security Association (SA)
- **Sequence Number**: anti-replay
- **ICV** (Integrity Check Value): HMAC-SHA / HMAC-MD5

### Header ESP

```
+---------------+---------------+---------------+---------------+
|              Security Parameters Index (SPI)                  |
+---------------+---------------+---------------+---------------+
|                  Sequence Number                              |
+---------------+---------------+---------------+---------------+
|                  Payload Data (cifrato)                       |
|                       ...                                     |
+---------------+---------------+---------------+---------------+
|        Padding (variable)                |Pad Len|Next Header |
+---------------+---------------+---------------+---------------+
|              Authentication Data (ICV)                        |
+---------------+---------------+---------------+---------------+
```

### Security Association (SA)
- Unidirezionale (servono 2 SA per traffico bidirezionale).
- Identificata da `(SPI, Destination IP, Protocol AH/ESP)`.
- Conserva: algoritmi crittografici, chiavi, sequenza, lifetime.
- Negoziata con **IKE/ISAKMP** (RFC 7296).

### Algoritmi tipici
- Cifratura: AES-128/256, 3DES (deprecato), ChaCha20.
- Hash/MAC: HMAC-SHA256, HMAC-SHA1 (deprecato).
- Scambio chiavi: Diffie-Hellman (gruppi 14/19/20+).

### IPsec vs TLS

| | IPsec | TLS |
|---|---|---|
| Livello | 3 (rete) | 4-5 (sopra TCP) |
| Trasparenza | Trasparente alle app | Le app devono usare TLS |
| Granularità | Per host/subnet | Per connessione |
| Tipico utilizzo | VPN, IPv6 | HTTPS, email, DBs |

---

## 7. Cheat-sheet "se il prof chiede X, dico Y"

| Se chiede... | Rispondo... |
|---|---|
| Cammini minimi Dijkstra | Trace passo-passo (vedi `Soluzione_esame_2024.md`); LPM scelto tra match |
| Differenze RIP/OSPF | Tabella §1 + §3 (Distance Vector vs Link State, hop vs cost, 15 max vs scalabile) |
| Count-to-infinity | Spiego il problema + 3 contromisure (split horizon, poison reverse, triggered update) |
| Costo OSPF | $\text{Cost} = \frac{Bw_{ref}}{Bw_{link}}$, default $Bw_{ref}=10^8$ |
| BGP | Path-vector, eBGP/iBGP, su TCP/179, decisioni per policy |
| Differenza IPv4/IPv6 | Tabella §5 (128 bit, no broadcast, header fisso, no checksum, SLAAC, IPsec integrato) |
| IPsec AH vs ESP | AH = autenticazione, ESP = autenticazione + cifratura. Transport mode (end-to-end) vs Tunnel mode (VPN gateway) |
| Cosa fa IPsec rispetto a TLS | IPsec è L3, trasparente alle app, protegge tutto; TLS è L4-5, per-connessione |
