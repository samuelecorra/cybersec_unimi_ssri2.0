# crouter — router IPv4 userspace in C con RIPv2

Progetto integrativo per Reti di Calcolatori (Università degli Studi di Milano).
Design completo in [`../L2 - Progetto Router IPv4 in C - Design.md`](../L2%20-%20Progetto%20Router%20IPv4%20in%20C%20-%20Design.md), relazione in [`relazione/relazione.md`](relazione/relazione.md).

Un router IPv4 interamente in spazio utente: riceve frame Ethernet grezzi da socket
`AF_PACKET/SOCK_RAW`, gestisce ARP e ICMP in proprio, inoltra pacchetti con longest
prefix match e apprende le rotte dinamicamente con RIPv2 (RFC 2453), interoperando
con FRRouting (successore di Quagga).

## Build (Linux / WSL2)

```sh
make            # produce build/crouter
make debug      # build con simboli per gdb/valgrind
```

## Topologia di test (network namespaces + FRR)

```
 pc1 ──(10.0.1.0/24)── R1[crouter] ══(10.0.12.0/30)══ R2[FRR] ══(10.0.23.0/30)══ R3[FRR] ──(10.0.4.0/24)── pc2
                            ║                                                     ║
                            ╚════════(10.0.14.0/30)══ R4[FRR] ══(10.0.34.0/30)═══╝
```

```sh
sudo bash test/topo.sh up                                    # crea namespaces + FRR su R2/R3/R4
sudo ip netns exec r1 ./build/crouter -c conf/r1.conf        # avvia crouter su R1 (RIP attivo)
sudo ip netns exec pc1 traceroute -n 10.0.4.100              # R1 appare come primo hop
sudo bash test/topo.sh vtysh r2 "show ip rip"                # la RIB vista da FRR
sudo bash test/topo.sh down
```

`kill -USR1 <pid di crouter>` stampa la RIB corrente a video.

## Struttura

| Modulo | Responsabilità |
|--------|----------------|
| `src/netio.c` | Un socket `AF_PACKET/SOCK_RAW` per interfaccia, RX/TX frame |
| `src/parse.c` | Validazione header, checksum Internet, checksum incrementale RFC 1624 |
| `src/arp.c` | Cache ARP, risposte, risoluzione next-hop con coda di attesa |
| `src/rib.c` | Tabella di routing (connesse/statiche/RIP), longest prefix match |
| `src/forward.c` | Motore di inoltro: TTL, checksum, lookup, riscrittura MAC |
| `src/icmp.c` | Echo Reply, Time Exceeded, Destination Unreachable |
| `src/ripd.c` | RIPv2: request/response, update periodici e triggered, split horizon con poisoned reverse, timer 180/120 s |
| `src/main.c` | Event loop `select()` con timer |
