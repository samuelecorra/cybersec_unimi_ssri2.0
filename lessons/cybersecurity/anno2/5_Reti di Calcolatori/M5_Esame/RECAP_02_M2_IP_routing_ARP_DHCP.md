# RECAP 02 — M2 condensato: IP, routing, ARP, DHCP, NAT

> Recap dei moduli M2/UD1 + UD2 + UD3 + UD4. Le **lezioni in stampa integrale** sono `M2/UD2/L1`, `L3`, `L4`, `L6`, `M2/UD3/L1`, `L3`. Qui condenso ciò che resta + i ponti tra le lezioni stampate.

---

## 1. Modello TCP/IP — protocolli del livello rete (UD1)

| Protocollo | Tipo | Funzione | Header in IP |
|---|---|---|:--:|
| **IP** (IPv4/IPv6) | Network | Indirizzamento, instradamento, frammentazione | — |
| **ICMP** | Control | Diagnostica, errori, ping | proto = 1 |
| **IGMP** | Control | Gestione gruppi multicast | proto = 2 |
| **TCP** | Transport | Trasferimento affidabile, ordinato | proto = 6 |
| **UDP** | Transport | Trasferimento best-effort | proto = 17 |
| **OSPF** | Routing | IGP link-state | proto = 89 |

> **Best Effort**: IP non garantisce nulla. Affidabilità, ordine, controllo flusso/congestione sono compito di TCP.

---

## 2. Header IPv4 (campo per campo)

20 byte fissi (senza opzioni), fino a 60 con opzioni.

```
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|Version|  IHL  |Type of Service|          Total Length         |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|         Identification        |Flags|      Fragment Offset    |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|  Time to Live |    Protocol   |         Header Checksum       |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                       Source Address                          |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Destination Address                        |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                    Options                    |    Padding    |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
```

| Campo | Bit | Significato |
|---|:--:|---|
| Version | 4 | 4 per IPv4 |
| IHL | 4 | Header length in parole da 32 bit (default 5 = 20 byte) |
| ToS / DSCP | 8 | Qualità del servizio |
| Total Length | 16 | Lunghezza totale (header + dati), max 65 535 byte |
| Identification | 16 | Id pacchetto per riassemblaggio |
| Flags | 3 | `0,DF,MF` (Don't Fragment, More Fragments) |
| Fragment Offset | 13 | Posizione frammento (in unità di 8 byte) |
| **TTL** | 8 | Decrementato di 1 per hop. Se 0 → pacchetto scartato + ICMP Time Exceeded |
| **Protocol** | 8 | Protocollo livello superiore (1=ICMP, 6=TCP, 17=UDP) |
| Header Checksum | 16 | Solo sull'header, ricalcolato ad ogni hop (TTL cambia) |
| Src IP | 32 | Indirizzo sorgente |
| Dst IP | 32 | Indirizzo destinazione |

---

## 3. Classi IP, indirizzi pubblici e privati

### Classi storiche

| Classe | 1° byte | Net-ID | Host-ID | Maschera di default | Range |
|:--:|:--:|:--:|:--:|:--:|:--:|
| A | 0xxx | 7 bit | 24 bit | 255.0.0.0 (/8) | 1.0.0.0 – 126.255.255.255 |
| B | 10xx | 14 bit | 16 bit | 255.255.0.0 (/16) | 128.0.0.0 – 191.255.255.255 |
| C | 110x | 21 bit | 8 bit | 255.255.255.0 (/24) | 192.0.0.0 – 223.255.255.255 |
| D | 1110 | — | — | — | 224.0.0.0 – 239.255.255.255 (multicast) |
| E | 1111 | — | — | — | 240.0.0.0 – 255.255.255.255 (riservato) |

### Indirizzi privati (RFC 1918)

| Classe | Range privato | Quantità host |
|:--:|:--:|:--:|
| A | `10.0.0.0/8` (10.0.0.0 – 10.255.255.255) | ~16 M |
| B | `172.16.0.0/12` (172.16.0.0 – 172.31.255.255) | ~1 M |
| C | `192.168.0.0/16` (192.168.0.0 – 192.168.255.255) | ~65 k |

### Indirizzi speciali

| Indirizzo | Significato |
|---|---|
| `127.0.0.1` | Loopback (localhost) — non esce mai dalla macchina |
| `0.0.0.0` | "This host" / default route |
| `255.255.255.255` | Broadcast locale (limitato, non instradabile) |
| `X.X.X.0` con /24 | Network address (non assegnabile a host) |
| `X.X.X.255` con /24 | Broadcast diretto della subnet |
| `169.254.0.0/16` | APIPA (link-local, autoassegnata) |

---

## 4. Subnetting — formule e procedura (riassunto operativo)

> Per il dettaglio completo vedi `M2/UD2/L3` e `L4` stampati integrali.

### Formule chiave

Per maschera `/n` (con $n$ bit di prefisso):
- **Numero di host utili per subnet** = $2^{32-n} - 2$ (esclude network + broadcast)
- **Numero di subnet** in un blocco classful: $2^s$ (con $s$ = bit aggiunti alla maschera default)
- **Network address** = IP AND mask
- **Broadcast** = network OR (NOT mask)
- **Subnet size** = $2^{32-n}$ indirizzi
- **Allineamento**: la subnet `/n` parte solo da multipli di $2^{32-n}$ (nell'ottetto interessato)

### Tabella delle maschere CIDR comuni

| CIDR | Mask | Host utili | Size | Allineamento (ultimo ottetto) |
|:--:|:--:|:--:|:--:|:--:|
| /24 | 255.255.255.0 | 254 | 256 | 0 (sempre) |
| /25 | 255.255.255.128 | 126 | 128 | 0, 128 |
| /26 | 255.255.255.192 | 62 | 64 | 0, 64, 128, 192 |
| /27 | 255.255.255.224 | 30 | 32 | multipli di 32 |
| /28 | 255.255.255.240 | 14 | 16 | multipli di 16 |
| /29 | 255.255.255.248 | 6 | 8 | multipli di 8 |
| /30 | 255.255.255.252 | 2 | 4 | multipli di 4 |
| /31 | 255.255.255.254 | 0/2* | 2 | multipli di 2 |
| /32 | 255.255.255.255 | 1 (host) | 1 | qualunque |

\* `/31` originariamente "non usabile"; RFC 3021 ne consente l'uso su link punto-punto.

| CIDR | Mask | Host utili | Size |
|:--:|:--:|:--:|:--:|
| /16 | 255.255.0.0 | 65534 | 65536 |
| /17 | 255.255.128.0 | 32766 | 32768 |
| /20 | 255.255.240.0 | 4094 | 4096 |
| /22 | 255.255.252.0 | 1022 | 1024 |
| /23 | 255.255.254.0 | 510 | 512 |

### FLSM vs VLSM

| | FLSM (Fixed Length) | VLSM (Variable Length) |
|---|---|---|
| Maschera | Uguale per tutte le subnet | Diversa per subnet diverse |
| Efficienza | Bassa (spreco quando le subnet hanno taglie diverse) | Alta |
| Complessità | Semplice | Maggiore |
| Richiede CIDR | No | Sì |
| Quando si usa | Reti omogenee semplici | Reti reali eterogenee |

### Procedura VLSM (5 passi)

1. **Ordina** le subnet richieste dalla più grande alla più piccola (in n° host).
2. Per ognuna calcola la **maschera minima** che contiene gli host (`$2^h \ge \text{host} + 2$`).
3. Assegna **a partire dall'inizio del range**, allineando ogni subnet al suo `subnet size`.
4. Per ogni subnet annota: **network, mask, broadcast, range host utili**.
5. Per i **link punto-punto** usa `/30`.

---

## 5. Configurazione IP di un host

Parametri minimi:
1. **IP address** della propria interfaccia
2. **Subnet mask**
3. **Default gateway** (IP del router di uscita)
4. **DNS server** (opzionale ma quasi sempre necessario)

### Multihoming

Una sola NIC può avere **più indirizzi IP** (fino a ~5). Ogni IP può avere un **gateway diverso**.

---

## 6. Assegnazione dinamica IP (UD2/L5) — pre-DHCP

- **Manuale**: amministratore configura ogni host.
- **RARP**: host diskless chiede al server "qual è il mio IP?" partendo dal MAC.
- **BOOTP**: estensione di RARP, riceve anche maschera + gateway. Antenato di DHCP.
- **DHCP** (RFC 2131): la soluzione moderna (vedi `M2/UD2/L6` stampato).

---

## 7. DHCP (UD2/L6) — sintesi rapida + spoofing

> Per il dettaglio completo del processo DORA → `M2/UD2/L6` stampato. Qui solo l'**attacco** richiesto nell'esame 20/3/2026.

### Porte
- Server: **UDP/67**, Client: **UDP/68**.

### Messaggi principali (DORA)

| Msg | Direzione | Funzione |
|---|---|---|
| DHCP**D**ISCOVER | Client → Broadcast | "Cerco un server DHCP" |
| DHCP**O**FFER | Server → Broadcast | "Ecco IP, mask, gw, DNS, lease" |
| DHCP**R**EQUEST | Client → Broadcast | "Accetto l'offerta di server X" |
| DHCP**A**CK | Server → Client | "Confermato, lease attivo" |
| DHCPNAK | Server → Client | Negata |
| DHCPRELEASE | Client → Server | Rilascio IP |
| DHCPRENEW | Client → Server (unicast) | Rinnovo a T1 = 50% lease |
| DHCPREBIND | Client → Broadcast | Rinnovo a T2 = 87.5% lease |

### Attacco: **Rogue DHCP / DHCP spoofing**

**Scenario**: Eve attacca la LAN aziendale `192.168.1.0/24` con server DHCP legittimo `192.168.1.10` e gateway `192.168.1.1`.

**Sequenza**:
1. Vittima (laptop appena collegato) invia `DHCPDISCOVER` in broadcast.
2. Sia il server legittimo che il **rogue server di Eve** (su un laptop attaccante) inviano `DHCPOFFER`.
3. Il rogue di Eve **risponde più rapidamente** (è sullo stesso segmento e sa che la vittima accetta la prima offerta).
4. La vittima fa `DHCPREQUEST` accettando l'offerta di Eve.
5. La vittima riceve `DHCPACK` con configurazione **falsificata**:
   - IP plausibile (es. `192.168.1.50`)
   - `gateway = 192.168.1.66` (cioè **l'IP di Eve**)
   - `DNS = 192.168.1.66` (sempre Eve)

**Campi falsificati**:
- `option 3` (default gateway) → punta all'attaccante
- `option 6` (DNS server) → punta all'attaccante
- `siaddr` (server IP)

**Conseguenze (MITM completo)**:
- **Tutto il traffico Internet della vittima passa per Eve** (può ispezionare, modificare, iniettare).
- DNS spoofing → la vittima viene reindirizzata a siti fake (phishing, furto credenziali).
- TLS può proteggere parzialmente (avvisi del browser), ma molti utenti li ignorano.
- HTTP/non-cifrato compromesso completamente.

**Contromisure**:
- **DHCP Snooping** sullo switch (Cisco): le porte sono classificate "trusted" (verso il server DHCP legittimo) o "untrusted" (tutte le altre); messaggi `DHCPOFFER/ACK` dalle untrusted sono **droppati**.
- **Dynamic ARP Inspection** (DAI), **IP Source Guard**.
- 802.1X (autenticazione delle porte).
- Su Linux/Windows: il client può preferire offerte da server noti.

---

## 8. ICMP (UD3/L2) — elenco essenziale

Pacchetti ICMP sono incapsulati direttamente in IP (proto = 1).

### Tipi principali

| Tipo | Codice | Nome | Generato da |
|:--:|:--:|---|---|
| 0 | 0 | Echo Reply | Risposta a ping |
| 3 | 0-15 | Destination Unreachable | Network/host/port unreachable |
| 3 | 4 | Fragmentation needed, DF set | Path MTU discovery |
| 5 | 0-3 | Redirect | Router suggerisce gateway migliore |
| 8 | 0 | Echo Request | Ping |
| 11 | 0 | Time Exceeded | TTL=0 (usato da `traceroute`) |
| 12 | 0 | Parameter Problem | Header IP malformato |

### Strumenti diagnostici

- **ping** (echo req/reply): verifica raggiungibilità.
- **traceroute / tracert**: invia pacchetti con TTL crescente e raccoglie i `Time Exceeded` dei router intermedi.

---

## 9. ARP (UD3/L3) — promemoria operativo

> Vedi `M2/UD3/L3` stampato per dettagli. Qui un riassunto rapido.

### Procedura ARP (per template d'esame)

1. Host A vuole inviare a B (stessa LAN), conosce solo `IP_B`.
2. A cerca `IP_B` nella **cache ARP**.
3. Se non c'è: **ARP Request** in broadcast (`dst MAC = FF:FF:FF:FF:FF:FF`, EtherType `0x0806`).
4. Tutti gli host ricevono; **solo B** risponde con **ARP Reply** in unicast.
5. A salva `(IP_B, MAC_B)` in cache.
6. A incapsula il pacchetto IP nel frame Ethernet con `dst MAC = MAC_B`.

### Campi pacchetto ARP

| Campo | Valore tipico |
|---|---|
| Hardware Type (HTYPE) | 1 (Ethernet) |
| Protocol Type (PTYPE) | 0x0800 (IPv4) |
| HLEN | 6 (MAC) |
| PLEN | 4 (IPv4) |
| Operation | 1 = request, 2 = reply |
| Sender MAC | MAC_A |
| Sender IP | IP_A |
| Target MAC | `00:00:...` nella request, MAC_B nella reply |
| Target IP | IP_B |

### Frame Ethernet di una ARP request

```
+-----------------+-----------------+-----+----------------+----+
| Dst MAC         | Src MAC         | Type| Data (ARP)     | FCS|
| FF:FF:FF:FF:FF  | MAC_A           |0x0806| pacchetto ARP | CRC|
+-----------------+-----------------+-----+----------------+----+
```

### ARP non attraversa router (locale alla LAN)

Per host fuori dalla LAN: ARP del **gateway**, non dell'host di destinazione.

### Cache ARP
- TTL tipico 2-20 min (configurabile, default Linux 60 s).
- Comandi: `arp -a` (BSD), `arp -e` (Linux), `arp -n` (no DNS reverse).

### Loopback (`127.0.0.0/8`)
- `127.0.0.1` = localhost. I pacchetti **non escono mai** dalla macchina.
- Utile per testare client/server localmente.

---

## 10. Instradamento IP (UD3/L1) — promemoria operativo

> Vedi `M2/UD3/L1` stampato. Qui il distillato per le risposte d'esame.

### Decisione diretto / indiretto (per ogni pacchetto)

```
SE (IP_dst AND mask_host) == (IP_host AND mask_host)
    → instradamento DIRETTO (ARP su IP_dst, invio diretto)
ALTRIMENTI
    → instradamento INDIRETTO (ARP sul gateway, pacchetto al gw)
```

### Tabella di routing — colonne minime

| Destination | Mask | Gateway | Interface | Metric |
|---|---|---|---|:--:|

- **Direct route**: `gateway = —` (direttamente connessa)
- **Default route**: `0.0.0.0/0` con gateway = default gw

### Longest Prefix Match (LPM)

Per ogni pacchetto:
1. Confronta `IP_dst` con tutte le righe (AND con mask).
2. Tra quelle che matchano, **scegli quella con maschera più lunga** (più specifica).
3. Inoltra al gateway / interface della riga vincente.

### Esempio: pacchetto 192.24.128.5 sulla tabella d'esame 8/5/2026

| Riga | Dst Prefix | Match? |
|---|---|---|
| 1 | 192.24.1.0/24 | `128.5` non match (subnet diversa) ❌ |
| 2 | 192.24.192.0/18 | mask /18 = 255.255.192.0; `192.24.128.5 AND 255.255.192.0 = 192.24.128.0`; il prefix è `192.24.192.0` → ❌ NON match (perché 128.0 ≠ 192.0) |

Hmm aspetta, ricalcoliamo: `192.24.192.0/18` significa prefix di 18 bit = `192.24.192.0`. La maschera è `255.255.192.0`. Applichiamo: `192.24.128.5 AND 255.255.192.0`:
- 128 in binario = `10000000`, AND con `11000000` = `10000000` = 128. Risultato: `192.24.128.0`. **Non corrisponde** a `192.24.192.0`.

| Riga | Dst Prefix | AND con mask | Match? |
|---|---|---|---|
| 1 | 192.24.1.0/24 | `192.24.128.5 AND /24` = `192.24.128.0` ≠ `192.24.1.0` | ❌ |
| 2 | 192.24.192.0/18 | `192.24.128.5 AND /18` = `192.24.128.0` ≠ `192.24.192.0` | ❌ |
| 3 | 0.0.0.0 (default) | sempre | ✅ → next hop `192.24.1.1` |

Verifica per `192.24.1.5`:
| Riga | Match? |
|---|---|
| 1 | `192.24.1.5 AND /24` = `192.24.1.0` ✅ → **direct** (LPM /24) |
| 2 | `192.24.1.5 AND /18` = `192.24.0.0` ≠ `192.24.192.0` ❌ |
| 3 | default ✅ |

LPM = riga 1 (/24 batte /0) → instradamento diretto via ARP.

---

## 11. NAT / PAT (UD4/L1-L3)

### NAT statico (1:1)

- Mappa **un IP privato → un IP pubblico fisso**.
- Usato per server interni che devono essere raggiungibili dall'esterno.

### NAT dinamico

- Pool di IP pubblici; assegnazione dinamica al primo che esce.
- Senza traduzione di porta.

### PAT / NAPT / NAT Overload (1:N)

- Un solo IP pubblico, **distinzione tramite porta**.
- Tabella di traduzione: `(IP_priv, porta_priv) ↔ (IP_pub, porta_pub_assegnata)`.
- È il NAT che hai nel router di casa.

#### Esempio

| IP priv | Porta priv | IP pub | Porta pub |
|---|:--:|---|:--:|
| 192.168.1.10 | 51432 | 151.100.20.5 | 61001 |
| 192.168.1.11 | 51433 | 151.100.20.5 | 61002 |

### Vantaggi NAT
- Risparmio IP pubblici (chiave nell'era pre-IPv6).
- Sicurezza implicita (host interni non raggiungibili dall'esterno).
- Riassegnamento privato libero.

### Svantaggi NAT
- Rompe il **principio end-to-end**.
- Problemi con protocolli che passano IP nel payload (FTP attivo, SIP, IPsec ESP).
- Difficile P2P (serve STUN/TURN/UDP hole punching).
- Soluzione "tappabuchi" in attesa di IPv6.

---

## 12. Frammentazione IP

Quando un pacchetto IP è più grande della **MTU** del link successivo:

1. Il router **frammenta** (se DF=0).
2. Ogni frammento ha:
   - Stesso `Identification`
   - `Fragment Offset` (in unità di 8 byte)
   - `MF = 1` (More Fragments) tranne l'ultimo
   - `Total Length` aggiornata
   - Checksum ricalcolato

3. Il **destinatario** riassembla (mai i router intermedi).
4. Se un frammento si perde → tutto il datagramma è perso.

**MTU tipiche**:
- Ethernet: 1500 byte
- PPPoE: 1492
- WiFi: 2304 (ma limitato da link sottostante)

**Path MTU Discovery**: DF=1, e si fa shrink in base agli ICMP "Fragmentation needed".

---

## 13. Configurazione comandi Linux/Windows

| Operazione | Linux | Windows |
|---|---|---|
| Mostra interfacce | `ip addr` / `ifconfig` | `ipconfig /all` |
| Mostra routing table | `ip route` / `route -n` | `route print` |
| Aggiungi rotta | `ip route add 10.0.0.0/24 via 192.168.1.1` | `route add 10.0.0.0 mask 255.255.255.0 192.168.1.1` |
| Mostra cache ARP | `arp -e` / `ip neigh` | `arp -a` |
| Ping | `ping host` | `ping host` |
| Traceroute | `traceroute host` | `tracert host` |

---

## 14. Cheat-sheet "se il prof chiede X, dico Y"

| Se chiede... | Rispondo includendo... |
|---|---|
| Calcolo subnet | Allineamento, network, mask, broadcast, range host (vedi §4) |
| Tabella routing R3 dato un disegno | Reti direct + indirette + default; LPM (vedi `M2/UD3/L1`) |
| Hop-by-hop di un pacchetto | IP src/dst invariati, MAC ricostruiti ad ogni hop, TTL -1, ARP locale (vedi §9) |
| DORA DHCP | Discover/Offer/Request/Ack in broadcast (vedi §7) |
| DHCP spoofing | Rogue server + falsificazione gateway/DNS + MITM, contromisura DHCP Snooping (vedi §7) |
| NAT/PAT | Tabella traduzione (IP_priv, porta) ↔ (IP_pub, porta) (vedi §11) |
| Frammentazione IP | DF/MF/Offset/Identification, riassemblaggio al destinatario (vedi §12) |
