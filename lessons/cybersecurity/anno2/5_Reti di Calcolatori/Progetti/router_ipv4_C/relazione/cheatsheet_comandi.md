# crouter — cheat sheet dei comandi per la demo dal vivo

> Tutti i comandi assumono di partire dalla **cartella del progetto** (`router_ipv4_C/`) e vanno lanciati con **`sudo`** quando toccano i network namespace (praticamente sempre, tranne `make`). Ogni blocco è pensato per essere copiato e incollato così com'è.

---

## -1. Prerequisito: sei nella shell giusta?

Questi comandi vanno lanciati in una **shell Linux vera**, non in PowerShell/cmd di Windows: servono `ip netns`, socket raw, veth, FRR, che su Windows nativo non esistono. Su questa macchina l'ambiente pronto è **WSL2, distro `Ubuntu-22.04`** (verificato: gcc/make, `ip`/tcpdump/traceroute/curl/ethtool, FRR con `vtysh`/`zebra`/`ripd` sono già installati, e il progetto compila senza warning).

Per entrare: digitare `wsl` in una finestra PowerShell/cmd, oppure aprire "Ubuntu 22.04" dal menu Start. Poi spostarsi nella cartella del progetto (il disco `C:` è montato sotto `/mnt/c`, occhio alle virgolette per lo spazio in "5_Reti di Calcolatori"):

```sh
cd "/mnt/c/Users/nabis/samu-cyberlocker/lessons/cybersecurity/anno2/5_Reti di Calcolatori/Progetti/router_ipv4_C"
```

Da qui in poi, tutti i comandi di questa pagina funzionano così come sono scritti — `sudo` dentro WSL è il vero `sudo` di Linux, non quello (disattivato) di Windows.

> ⚠️ Se per errore si lancia `sudo bash test/topo.sh up` da **PowerShell**, l'errore è "Sudo è disabilitato in questo computer": è il `sudo` nativo di Windows 11 (Developer Settings), del tutto estraneo a questo progetto — non va abilitato, va semplicemente eseguito tutto da dentro `wsl`.

---

## 0. Sequenza minima "sicura" (se hai solo 2-3 minuti)

Questa è la sequenza che **so per certo funziona**, presa esattamente dagli script di test. Usarla come base, poi deviare secondo le richieste del professore.

```sh
cd router_ipv4_C
make                                                       # 1. compila -> build/crouter
sudo bash test/topo.sh up                                  # 2. topologia + FRR su R2/R3/R4 (~5-10 s)
sudo ip netns exec r1 ./build/crouter -c conf/r1.conf -v   # 3. avvia crouter CON RIP, log a video (Ctrl-C per fermarlo)
```

Aperto un **secondo terminale** (crouter nel primo resta in foreground e mostra i log dal vivo):

```sh
cd router_ipv4_C
sudo ip netns exec pc1 ping -c 4 10.0.4.100                # 4. ping attraverso il router (aspetta ~15-20s dopo l'avvio di crouter per la convergenza)
sudo ip netns exec pc1 traceroute -n 10.0.4.100            # 5. crouter (10.0.1.1) come primo hop
```

Per fermare tutto a fine esposizione:

```sh
# Ctrl-C nel terminale di crouter, poi:
sudo bash test/topo.sh down
```

> ⚠️ **Aspetta la convergenza prima del ping.** Appena avviato, crouter manda le Request RIP e in genere in **10-15 secondi** la RIB è popolata (lo vedi scorrere nel log con `-v`, o lo confermi con `SIGUSR1`, §3). Se fai il ping troppo presto, `10.0.4.0/24` potrebbe non essere ancora in tabella e il ping fallirebbe — non è un bug, è solo che la convergenza non è istantanea.

---

## 1. Setup e build

```sh
cd router_ipv4_C
make            # compila -> build/crouter (gcc -Wall -Wextra -O2, zero warning atteso)
make clean      # rimuove build/ (per ricompilare da zero)
make debug      # ricompila senza ottimizzazioni e con simboli (-O0 -g): serve per gdb/valgrind
```

---

## 2. Topologia: avvio, stato, smontaggio

```sh
sudo bash test/topo.sh up        # crea i 6 namespace (pc1 r1 r2 r3 r4 pc2), i link veth, avvia FRR su r2/r3/r4
sudo bash test/topo.sh status    # indirizzi IP di ogni nodo + tabella RIP di ciascun FRR
sudo bash test/topo.sh down      # termina tutti i processi nei namespace e li cancella
```

> ⚠️ Se `up` fallisce con "namespace già esistente", quasi certamente è rimasta una topologia precedente in piedi: lancia `down` e riprova.
> ⚠️ **R1 non ha MAI un indirizzo IP a livello kernel** (`ip netns exec r1 ip addr` mostra solo `lo` e le interfacce senza IP) — è voluto: gli IP di R1 vivono solo dentro `conf/r1.conf`, li possiede `crouter`, non il kernel. Se il professore lo nota e chiede "perché R1 non ha IP?", è esattamente il punto della scelta userspace (v. presentazione, §1.9).

Topologia (per orientarsi a voce):

```
 pc1 ──(10.0.1.0/24)── R1[crouter] ══(10.0.12.0/30)══ R2[FRR] ══(10.0.23.0/30)══ R3[FRR] ──(10.0.4.0/24)── pc2
                            ║                                                      ║
                            ╚═════════(10.0.14.0/30)══ R4[FRR] ══(10.0.34.0/30)════╝
```

---

## 3. Avviare/fermare crouter e leggere la RIB

```sh
# Avvio in foreground con log verboso (si vede tutto: ARP, RIP, forwarding) — CONSIGLIATO in demo, Ctrl-C per fermare
sudo ip netns exec r1 ./build/crouter -c conf/r1.conf -v

# Avvio in background con log su file (se serve liberare il terminale)
sudo ip netns exec r1 ./build/crouter -c conf/r1.conf -v >/tmp/crouter.log 2>&1 &
tail -f /tmp/crouter.log            # segui il log dal vivo
# Ctrl-C esce da tail SENZA fermare crouter

# Verificare che sia in esecuzione / trovarne il PID
pgrep -x crouter

# Fermarlo "gentilmente" (se è in background)
sudo pkill -x crouter

# Chiedere il DUMP della RIB a video, in qualsiasi momento, SENZA fermarlo (SIGUSR1)
sudo pkill -USR1 -x crouter
# se il log va su file invece che a video:
tail -n 15 /tmp/crouter.log         # oppure: grep -A 11 "RIB (tabella" /tmp/crouter.log | tail -12
```

Esempio di RIB a convergenza avvenuta (quello che ci si aspetta di vedere):

```
================== RIB (tabella di routing) ==================
destinazione         next-hop        dev    met  org  eta'
10.0.1.0/24          -               eth1   1    C    -
10.0.12.0/30         -               eth2   1    C    -
10.0.14.0/30         -               eth3   1    C    -
10.0.4.0/24          10.0.12.2       eth2   3    R    14s
10.0.23.0/30         10.0.12.2       eth2   2    R    14s
10.0.34.0/30         10.0.14.2       eth3   2    R    11s
===============================================================
```

`C` = connessa, `R` = appresa via RIP (con l'"età" in secondi dall'ultimo aggiornamento).

> 💡 Senza `-v` il livello di log è INFO: si vedono comunque apprendimento/perdita di rotte, ma non ogni singolo pacchetto ARP/ICMP. Con `-v` (DEBUG) si vede tutto — utile se il professore vuole capire il dettaglio di cosa succede pacchetto per pacchetto, più rumoroso se vuole solo il quadro d'insieme.

---

## 4. Richieste "ai vari server": ping, traceroute, in entrambe le direzioni

Il pattern generale è `sudo ip netns exec <nodo> <comando>`. Sostituendo `<nodo>` si esegue il comando **da quel nodo** — utile se il professore chiede "e se lo fai dall'altro lato?".

```sh
# pc1 -> pc2 (attraverso crouter -> R2 -> R3)
sudo ip netns exec pc1 ping -c 4 10.0.4.100
sudo ip netns exec pc1 traceroute -n 10.0.4.100

# pc2 -> pc1 (direzione inversa, attraverso R3 -> R2 -> crouter)
sudo ip netns exec pc2 ping -c 4 10.0.1.100
sudo ip netns exec pc2 traceroute -n 10.0.1.100

# ping "infinito" (utile per far vedere un guasto dal vivo, §6) — Ctrl-C per fermarlo
sudo ip netns exec pc1 ping 10.0.4.100

# ping con TTL basso apposta, per vedere il Time Exceeded generato da crouter
sudo ip netns exec pc1 ping -c 1 -t 1 10.0.4.100
# -> "Time to live exceeded" con risposta DA 10.0.1.1 (crouter): è l'ICMP che genera forward.c/icmp.c

# ping direttamente a un'interfaccia di crouter (per mostrare che RISPONDE lui stesso, non il kernel)
sudo ip netns exec pc1 ping -c 2 10.0.1.1     # l'IP di eth1 di crouter
sudo ip netns exec r2  ping -c 2 10.0.12.1    # l'IP di eth2 di crouter, visto da R2

# traceroute verso una destinazione INESISTENTE, per mostrare il Destination Unreachable
sudo ip netns exec pc1 traceroute -n 10.0.99.99
```

Se serve rifare `ping`/`traceroute` più *snelli* (come nello script automatico, per non stare lì ad aspettare i timeout):

```sh
sudo ip netns exec pc1 ping -c 4 -W 2 10.0.4.100
sudo ip netns exec pc1 traceroute -n -w 1 -q 1 10.0.4.100
```

---

## 5. Guardare cosa passa sul filo: tcpdump

```sh
# Cattura generica su un'interfaccia di un router FRR (es. R2 verso crouter)
sudo ip netns exec r2 tcpdump -ni eth0

# Cattura SOLO gli annunci RIPv2 (UDP porta 520), con decodifica dettagliata (-vv)
sudo ip netns exec r2 tcpdump -ni eth0 -vv udp port 520

# Con timeout automatico (utile per non doverlo interrompere a mano dopo N secondi)
sudo ip netns exec r2 timeout 32 tcpdump -ni eth0 -vv udp port 520

# Cattura ARP (per mostrare che crouter risponde da solo alle richieste per i suoi IP)
sudo ip netns exec r1 tcpdump -ni eth1 arp

# Cattura ICMP (ping / Time Exceeded / Unreachable)
sudo ip netns exec pc1 tcpdump -ni eth0 icmp

# Salvare una cattura su file (per riguardarla dopo, o aprirla con Wireshark)
sudo ip netns exec r2 tcpdump -ni eth0 -w /tmp/cattura.pcap udp port 520
```

Cosa aspettarsi da una cattura RIP di crouter (poisoned reverse ben visibile):

```
10.0.12.1.520 > 10.0.12.2.520:  RIPv2, Response, length: 124, routes: 6 or less
  AFI IPv4,   10.0.1.0/24,  tag 0x0000, metric: 1,  next-hop: self
  AFI IPv4,  10.0.12.0/30,  tag 0x0000, metric: 16, next-hop: self   <- poisoned reverse
  ...
```

---

## 6. Vedere la tabella dal punto di vista di FRR (vtysh)

```sh
# Diretto
sudo vtysh -N r2 -c "show ip rip"
sudo vtysh -N r2 -c "show ip route"

# Tramite lo script (fa lo stesso, comodo se si è già dentro topo.sh)
sudo bash test/topo.sh vtysh r2 "show ip rip"
sudo bash test/topo.sh vtysh r3 "show ip rip"
sudo bash test/topo.sh vtysh r4 "show ip rip"

# Entrare in una shell interattiva vtysh su un router (per esplorare a mano)
sudo vtysh -N r2
# dentro: show ip rip / show ip route / show interface brief / exit
```

Nella tabella di R2 ci si aspetta di vedere `10.0.1.0/24` appresa **da 10.0.12.1**, cioè da crouter:

```
     Network            Next Hop         Metric From            Tag Time
R(n) 10.0.1.0/24        10.0.12.1             2 10.0.12.1         0 02:42
```

---

## 7. Simulare un guasto dal vivo

**Opzione A — riprodurre esattamente lo Scenario 4** (link R2↔R3, quello scriptato e testato):

```sh
sudo ip netns exec pc1 ping -i 0.5 -W 1 10.0.4.100 > /tmp/ping_guasto.txt 2>&1 &   # ping continuo in background
sudo ip netns exec r2 ip link set eth1 down     # ABBATTI il link R2<->R3
# ... aspetta 10-20s, poi guarda il log di crouter: vedrai "irraggiungibile", "triggered update",
#     "percorso migliore ... via 10.0.14.2" (reinstradato via R4)
sudo ip netns exec r2 ip link set eth1 up       # ripristina
kill %1                                          # ferma il ping in background quando hai finito
```

Oppure, tutto insieme, lo script già pronto (ping continuo + guasto + log + RIB finale, in un colpo solo):

```sh
sudo bash test/scenari/guasto_link.sh
```

**Opzione B — se il professore chiede di abbattere un link DIRETTAMENTE attaccato a crouter** (es. R1↔R2, `eth2` di R1):

```sh
sudo ip netns exec r1 ip link set eth2 down
```

> ⚠️ **Attenzione, comportamento diverso e va spiegato a voce.** crouter non ha alcun meccanismo di notifica "link down" dal kernel (non fa netlink monitoring: è una scelta di design, resta fedele all'idea "tutto passa dai socket e dal timer di RIP"). Se si stacca un link **su cui crouter stesso è affacciato**, lui se ne accorge SOLO quando il timeout RIP di quella rotta scade — **180 secondi**, non pochi secondi come nello Scenario 4 scriptato. Nello Scenario 4 la riconvergenza è rapida perché a notare il guasto per primo è **FRR su R2** (che ha la sua propria detection) e ad annunciare subito la rotta avvelenata a crouter; se il guasto è sul lato di crouter, è crouter stesso che deve accorgersene, e lo fa solo col timeout. Se il professore insiste su questa variante, vale la pena dirlo esplicitamente **prima** di lanciare il comando, così un'attesa di 3 minuti non sembra un blocco imprevisto ma una conseguenza prevista e spiegabile della scelta implementativa.

---

## 8. Traffico applicativo: sessione HTTP end-to-end

```sh
# Terminale 1: avvia un server HTTP minimale su pc2
sudo ip netns exec pc2 python3 -m http.server 8080 --bind 10.0.4.100

# Terminale 2: interrogalo da pc1, ATTRAVERSO crouter
sudo ip netns exec pc1 curl -s -D - http://10.0.4.100:8080/
# -D - stampa gli header di risposta; con -o /dev/null si scarta il corpo se non serve mostrarlo
```

Atteso: `HTTP/1.0 200 OK` e gli header del server Python — prova che anche una connessione TCP con handshake a tre vie attraversa il router correttamente.

---

## 9. Robustezza: fuzzing e Valgrind

```sh
# Fuzzing: inietta ~2200 frame malformati verso crouter (deve scartarli senza crash)
sudo ip netns exec pc1 python3 test/scenari/fuzz.py
# subito dopo, verifica che crouter sia ANCORA vivo e risponda:
sudo ip netns exec pc1 ping -c 2 10.0.1.1
pgrep -x crouter

# Valgrind: build coi simboli, poi crouter SOTTO valgrind invece che diretto
make debug
sudo ip netns exec r1 valgrind --leak-check=full --track-origins=yes \
    ./build/crouter -c conf/r1.conf -v
# lascialo girare mentre fai ping/traceroute/HTTP/fuzzing da un altro terminale,
# poi Ctrl-C: valgrind stampa il riepilogo finale ("ERROR SUMMARY: 0 errors from 0 contexts")
```

> ⚠️ Dopo `make debug` il binario non è più ottimizzato (`-O0 -g`): va bene per una dimostrazione mirata di Valgrind, ma se poi si torna alla demo normale conviene ricompilare con `make` (che pulisce e ricostruisce in `-O2`).

---

## 10. Rieseguire l'intera demo automatica (i 5 scenari in sequenza)

Se il professore chiede "fammeli vedere tutti e cinque di seguito": questo script **ricrea la topologia da zero**, esegue i cinque scenari del walkthrough in ordine e salva ogni output testuale in `relazione/cattura/`. Ci mette un paio di minuti (ha `sleep` interni per lasciar convergere/riconvergere il protocollo).

```sh
sudo bash test/demo.sh
```

Alla fine elenca i file prodotti; per rivederne uno al volo:

```sh
cat relazione/cattura/01_convergenza_rib.txt
cat relazione/cattura/02_dataplane.txt
cat relazione/cattura/03_rip_interop.txt
cat relazione/cattura/04_guasto.txt
cat relazione/cattura/05_http.txt
```

---

## 11. IMUNES (solo se viene chiesto esplicitamente questo ambiente)

L'ambiente principale di test è a network namespace (sopra); IMUNES è il porting alternativo con GUI, documentato in Appendice A della relazione. Sequenza:

```sh
# Una tantum, se non già fatto sulla macchina della demo:
sudo apt-get install -y openvswitch-switch tk tcllib xterm ethtool
git clone https://github.com/imunes/imunes.git ~/imunes && cd ~/imunes && sudo make install
sudo imunes -p

# Ogni volta:
cd router_ipv4_C
make                                   # assicurati che build/crouter esista
sudo imunes imunes/topo.imn            # apre la GUI con la topologia
# nella GUI: pulsante Execute (▶) per istanziare l'esperimento
bash imunes/deploy_crouter.sh          # copia crouter + r1.conf nel nodo R1, imposta le rotte di default su pc1/pc2
```

Poi, **dentro la GUI**: doppio clic su **R1-crouter** → nel terminale del nodo, lanciare **una sola volta**:

```sh
/crouter -c /r1.conf -v
```

E doppio clic su **pc1** per verificare:

```sh
ping -c 4 10.0.4.100
traceroute -n -I 10.0.4.100
```

> ⚠️ Avviare crouter **una sola volta** in IMUNES: istanze multiple duplicherebbero l'inoltro (si vedrebbero `DUP!` nel ping). Il `traceroute` va lanciato con `-I` (modalità ICMP): quello UDP classico mostra la destinazione come `*` perché i nodi `pc` di IMUNES non rispondono ai probe UDP — non è un problema di crouter.

---

## 12. Pulizia a fine esposizione

```sh
# Ctrl-C su crouter se è in foreground, altrimenti:
sudo pkill -x crouter

sudo bash test/topo.sh down
```

---

## 13. Problemi comuni e come uscirne al volo

| Sintomo | Causa probabile | Comando risolutivo |
|---|---|---|
| `topo.sh up` dice "namespace già esistente" | Topologia precedente non smontata | `sudo bash test/topo.sh down` poi riprova `up` |
| Ping fallisce subito dopo l'avvio di crouter | RIP non ancora convergiuto (10-15s) | Aspetta, o controlla la RIB con `SIGUSR1` (§3) prima di concludere che qualcosa non va |
| Ping/HTTP falliscono con "checksum" strano lato host finale | Checksum offload dei `veth` non disattivato | `sudo bash test/offload.sh` (lo fa già `topo.sh up` da solo, ma se hai ricreato link a mano rilancialo) |
| `crouter: command not found` / file non trovato | Non compilato, o percorso relativo sbagliato | `make` dalla cartella del progetto; lancialo sempre con `./build/crouter` dalla cartella `router_ipv4_C` |
| Due processi `crouter` in esecuzione, traffico duplicato | Una vecchia istanza non terminata | `pgrep -x crouter` per vederli, `sudo pkill -x crouter`, poi rilancia una sola istanza |
| `vtysh` non risponde / dice errore di connessione | FRR non avviato in quel namespace | Verifica con `sudo bash test/topo.sh status`; se assente, `topo.sh up` non è stato eseguito o è fallito a metà — `down` e riprova |
| Serve rifare tutto da capissimo, senza pensarci | — | `sudo bash test/demo.sh` ricrea tutto e riesegue i 5 scenari da solo |
