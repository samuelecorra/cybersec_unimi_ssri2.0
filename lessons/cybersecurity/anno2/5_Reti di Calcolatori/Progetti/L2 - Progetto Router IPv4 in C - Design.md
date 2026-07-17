# **Progetto Integrativo — Router IPv4 in C: Documento di Progettazione**

### **1. Visione del progetto**

Implementare da zero, in C, un **router IPv4 userspace** — nome in codice **`crouter`** — completo di:

- **piano dati** (data plane): ricezione, validazione, instradamento e inoltro di pacchetti IP reali su interfacce di rete reali;
- **piano di controllo** (control plane): **RIPv2** (RFC 2453) per l'apprendimento dinamico delle rotte, interoperabile con i demoni di routing standard (Quagga/FRR);
- **demo finale in IMUNES**: una topologia multi-router in cui un nodo esegue `crouter` al posto del router di sistema, dialogando in RIP con router Quagga e inoltrando traffico vero (ping, traceroute, HTTP).

> 📌 La scelta strategica: la traccia ufficiale propone "modulo kernel space **o** Quagga". Scegliamo la terza via, più didattica di entrambe: un **router interamente userspace su socket raw**. Rispetto al modulo kernel evita i rischi catastrofici (panic, debugging cieco) mantenendo il basso livello; rispetto alla pura configurazione di Quagga richiede di **scrivere davvero** parser di pacchetti, tabella di forwarding e protocollo di routing. Quagga resta in gioco come **controparte di interoperabilità** — il che dimostra che la nostra implementazione parla RIP "vero".

> 💡 Il progetto ricuce l'intero programma del corso: livello 2 (Ethernet, ARP), livello 3 (IPv4, ICMP, forwarding, routing dinamico), livello 4 (UDP per RIP), programmazione con socket e `select()` (modulo M4), emulazione di rete (IMUNES).

---

### **2. Architettura**

#### **2.1. Schema a moduli**

```
                    ┌──────────────────────────────────────┐
                    │               crouter                │
                    │                                      │
  eth0 ◄──────────► │ netio ──► parse ──► forward ──► netio│
  eth1 ◄──────────► │   │                   ▲              │
  ethN ◄──────────► │   │                   │              │
                    │   ▼                   │              │
                    │  arp ◄── icmp        rib (LPM)       │
                    │                       ▲              │
                    │                       │              │
                    │  ripd (UDP 520) ──────┘              │
                    │       ▲                              │
                    │       └── timers (select timeout)    │
                    └──────────────────────────────────────┘
```

| Modulo | File | Responsabilità |
|--------|------|----------------|
| `netio` | `netio.c/h` | Un socket `AF_PACKET/SOCK_RAW` per interfaccia; RX/TX di frame Ethernet grezzi |
| `parse` | `parse.c/h` | Parsing e validazione header Ethernet/ARP/IPv4/ICMP/UDP (checksum, lunghezze, versione) |
| `arp` | `arp.c/h` | Cache ARP con timeout, risposta alle richieste ARP, risoluzione next-hop, coda pacchetti in attesa di risoluzione |
| `rib` | `rib.c/h` | Tabella di routing: rotte connesse, statiche, RIP; **longest prefix match**; distanza amministrativa |
| `forward` | `forward.c/h` | Motore di inoltro: decremento TTL, ricalcolo checksum, lookup LPM, consegna a next-hop |
| `icmp` | `icmp.c/h` | Generazione ICMP: Time Exceeded (per traceroute!), Destination Unreachable, Echo Reply |
| `ripd` | `ripd.c/h` | RIPv2: update periodici e triggered, split horizon con poisoned reverse, timer di timeout/garbage |
| `config` | `config.c/h` | Parsing file di configurazione (interfacce, rotte statiche, abilitazione RIP) |
| `main` | `main.c` | Event loop unico basato su `select()` con timeout per i timer |

#### **2.2. Il piano dati, passo per passo**

Per ogni frame ricevuto da `netio`:

1. **Demultiplexing Ethernet**: se EtherType = ARP → modulo `arp`; se = IPv4 → prosegui.
2. **Validazione IPv4**: versione, IHL, checksum header, lunghezza totale. Pacchetto malformato → scarto silenzioso.
3. **Destinato a noi?** (IP di una nostra interfaccia): se ICMP Echo Request → `icmp` risponde; se UDP porta 520 → `ripd`.
4. **Forwarding**: $TTL \leftarrow TTL - 1$; se $TTL = 0$ → ICMP **Time Exceeded** al mittente (è ciò che rende il nostro router visibile a `traceroute`). Altrimenti ricalcolo checksum incrementale (RFC 1624).
5. **Lookup LPM** nella RIB: nessuna rotta → ICMP **Destination Unreachable (net)**. Rotta trovata → determina next-hop e interfaccia di uscita.
6. **Risoluzione ARP** del next-hop (cache o richiesta con coda di attesa) e **trasmissione** del frame riscritto (MAC sorgente/destinazione aggiornati).

#### **2.3. Il piano di controllo: RIPv2 (RFC 2453)**

- **Trasporto**: UDP porta 520, multicast `224.0.0.9`.
- **Messaggi**: Request (all'avvio, per convergenza rapida) e Response (update periodici ogni **30 s** ± jitter, e triggered update alla variazione di una rotta).
- **Metrica**: hop count, $1 \le m \le 15$, con $m = 16 = \infty$ (irraggiungibile).
- **Anti-loop**: split horizon con **poisoned reverse**; count-to-infinity limitato dal tetto 15.
- **Timer per rotta**: timeout **180 s** (rotta invalidata, metrica 16), garbage collection **120 s** (rotta rimossa).
- **Autenticazione**: entry di tipo 0xFFFF con password in chiaro (opzionale, punto bonus).

> ⚠️ Insidia da gestire (e da raccontare bene in sede d'esame): l'interoperabilità con Quagga richiede rispetto rigoroso del formato delle entry (Address Family = 2, campi Route Tag/Subnet Mask/Next Hop di RIPv2) e del jitter sugli update per evitare sincronizzazione degli annunci.

#### **2.4. Strutture dati chiave**

```c
struct rib_entry {
    struct in_addr prefix;      /* rete di destinazione            */
    uint8_t        prefix_len;  /* lunghezza prefisso (LPM)        */
    struct in_addr next_hop;    /* 0.0.0.0 se rete connessa        */
    int            ifindex;     /* interfaccia di uscita           */
    uint8_t        metric;      /* hop count RIP (16 = infinito)   */
    uint8_t        origin;      /* CONNECTED | STATIC | RIP        */
    time_t         last_update; /* per timeout/garbage collection  */
};
```

La RIB è un array ordinato per `prefix_len` decrescente: il **longest prefix match** è la prima entry che matcha — semplice, corretto, e con le decine di rotte della demo più che efficiente (l'eventuale trie binario è discusso in relazione come evoluzione).

---

### **3. Demo in IMUNES**

#### **3.1. Topologia**

```
  LAN A (10.0.1.0/24)                              LAN B (10.0.4.0/24)
   pc1 ──┐                                              ┌── pc2
         ├── R1 (crouter) ══ R2 (Quagga) ══ R3 (Quagga) ┤
         │        ║                            ║        │
         │        ╚════════ R4 (Quagga) ═══════╝        │
                     (cammino alternativo)
```

- R1 esegue **`crouter`**; R2, R3, R4 eseguono Quagga `ripd`.
- Link punto-punto in `10.0.12.0/30`, `10.0.14.0/30`, `10.0.23.0/30`, `10.0.34.0/30`.

#### **3.2. Scenari del walkthrough (→ screenshot per la relazione)**

1. **Convergenza a freddo**: avvio di `crouter`, richiesta RIP iniziale, tabella popolata in < 30 s (dump della RIB a video).
2. **Data plane**: `ping` e `traceroute` da pc1 a pc2 — il nostro router appare come hop grazie all'ICMP Time Exceeded generato da noi.
3. **Interoperabilità**: `tcpdump` degli update RIPv2 tra `crouter` e Quagga; confronto con `show ip rip` su R2.
4. **Guasto e riconvergenza**: abbattimento del link R1–R2; triggered update, poisoned reverse, traffico che rientra via R4.
5. **Traffico applicativo**: sessione HTTP attraverso la rete (server su pc2), a chiudere il cerchio col livello applicativo.

---

### **4. Roadmap incrementale**

| Fase | Deliverable | Test di uscita |
|------|-------------|----------------|
| 0 | Setup IMUNES + scheletro progetto (Makefile, config, log) | Topologia emulata funzionante con soli Quagga |
| 1 | `netio` + `parse`: sniffing e dump dei frame | Vedo ARP/IP transitare sulle interfacce |
| 2 | `arp` + `icmp` Echo Reply | Il router risponde al ping sui propri IP |
| 3 | `rib` (statiche + connesse) + `forward` | Forwarding statico: ping pc1→pc2 con rotte a mano |
| 4 | `icmp` Time Exceeded / Unreachable | `traceroute` mostra R1 come hop |
| 5 | `ripd` completo | Convergenza dinamica e interop con Quagga |
| 6 | Scenari di guasto + hardening (validazioni, memoria) | Riconvergenza < 180 s, nessun leak (`valgrind`) |
| 7 | Relazione + screenshot + walkthrough | Consegna su `upload.unimi.it` |

> ✅ Ogni fase produce qualcosa di **dimostrabile**: anche nel caso peggiore, un progetto fermo alla fase 4 resta un router statico funzionante con ICMP — già più di molti progetti IMUNES puri. Il rischio è controllato per costruzione.

---

### **5. Struttura della relazione (requisiti ufficiali → sezioni)**

1. **Descrizione del progetto** — obiettivi, architettura (§2), scelte progettuali e loro motivazione (userspace vs kernel vs Quagga).
2. **Brani di codice significativi** — LPM, checksum incrementale RFC 1624, macchina a stati RIP, gestione `select()` con timer.
3. **Walkthrough completo con screenshot** — i cinque scenari del §3.2, con capture `tcpdump`/Wireshark commentate.

---

### **6. Prossimi passi operativi**

1. Verificare la disponibilità dell'ambiente: IMUNES (nativo su FreeBSD, via Docker/VM su Linux) e Quagga/FRR.
2. Congelare una pagina di **proposta di estensione** da mandare al docente (facoltativa ma astuta: "Router in C userspace con RIPv2 interoperabile con Quagga, demo IMUNES" — così i 3 punti sono negoziati *prima*).
3. Fase 0 della roadmap.

---

### **7. Implementazione realizzata**

> ✅ Il progetto è stato **implementato per intero**: codice, test e relazione si trovano nella cartella [`router_ipv4_C/`](router_ipv4_C/). Il router `crouter` (~1500 righe di C, solo libc + header Linux) supera tutte le fasi della roadmap: data plane completo, RIPv2 interoperabile con FRRouting, i cinque scenari del walkthrough catturati, fuzzing di 2200 frame malformati e Valgrind puliti. L'ambiente di demo usa Linux network namespaces + FRR (`router_ipv4_C/test/topo.sh`) al posto di IMUNES, scelta per riproducibilità e per poter automatizzare i test; la topologia riprodotta è quella del §3.1. La relazione finale in PDF è [`router_ipv4_C/relazione/relazione.pdf`](router_ipv4_C/relazione/relazione.pdf) (rigenerabile con `router_ipv4_C/relazione/build_pdf.sh`). Restano da incollare a mano gli screenshot GUI nei segnaposto già predisposti nella relazione.
