## **Lezione 2: Packet sniffing e spoofing con Scapy**

### **1. Dal frame al pacchetto ricevuto**

Le slide partono dal comportamento di una NIC, cioè una Network Interface Card fisica o logica. Ogni interfaccia ha un indirizzo MAC e riceve frame dal mezzo di rete. In una LAN classica, una scheda può vedere frame che transitano sul segmento, ma normalmente passa al kernel solo quelli destinati al proprio MAC, broadcast o multicast accettati.

In modalità normale:

- la NIC controlla il MAC di destinazione;
- se il frame non è destinato alla scheda, lo scarta;
- se il frame è destinato alla scheda, lo copia in un buffer kernel;
- il kernel processa l'incapsulamento superiore.

In modalità promiscua:

- la NIC passa al kernel anche frame non destinati al proprio MAC;
- strumenti come Wireshark, tcpdump, libpcap e Scapy possono osservare molto più traffico;
- l'efficacia dipende comunque dalla topologia: su switch moderni non si vede automaticamente tutto il traffico della LAN.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 Sniffare non significa magicamente vedere Internet: significa ricevere ciò che arriva all'interfaccia osservata. Nei laboratori Docker, per questo si usa il bridge giusto o `network_mode: host`.

### **2. Come è costruito un pacchetto**

I pacchetti attraversano più livelli:

- livello 2, Data Link/MAC: frame Ethernet, MAC sorgente/destinazione;
- livello 3, Network: IP sorgente/destinazione, TTL, protocollo trasportato;
- livello 4, Transport: TCP/UDP/ICMP, porte, flag, numeri di sequenza.

Nel laboratorio, questa stratificazione diventa pratica perché Scapy usa l'operatore `/` per impilare protocolli:

```python
from scapy.all import *

p = IP(dst="10.9.0.5") / ICMP()
send(p)
```

Qui `IP(...)` crea il livello 3, `ICMP()` crea il contenuto ICMP e `/` costruisce il pacchetto risultante.

### **3. Strumenti per inviare pacchetti**

Le slide citano più strumenti:

- `netcat`, per aprire connessioni TCP/UDP grezze da terminale;
- pseudo-device Bash `/dev/tcp` e `/dev/udp`;
- `telnet`, utile per sessioni testuali e laboratorio TCP hijacking;
- `ping`, utile per generare ICMP echo request;
- Scapy, per costruire e manipolare pacchetti arbitrari.

> 💡 `ping` e `telnet` generano traffico "normale"; Scapy genera traffico controllato da noi campo per campo.

### **4. Cos'è Scapy**

Scapy è un programma/libreria Python per manipolare pacchetti. Serve per:

- creare pacchetti;
- impostare campi di header;
- inviare pacchetti;
- sniffare traffico;
- decodificare pacchetti ricevuti;
- costruire piccoli strumenti di scansione, traceroute, spoofing o rilevamento.

Scapy può essere usato in due modalità:

1. script Python;
2. shell interattiva Python.

Script:

```python
#!/usr/bin/env python3
from scapy.all import *

a = IP()
a.show()
```

Esecuzione:

```bash
chmod +x mycode.py
sudo ./mycode.py
```

Modalità interattiva:

```bash
sudo python3
```

```python
from scapy.all import *
a = IP()
a.show()
ls(IP)
```

> ⚠️ Per spoofing, raw packet e sniffing su interfacce reali servono privilegi elevati. Se lo script funziona con `sudo` ma fallisce da utente normale, il punto didattico è proprio quello.

### **5. Sniffing con Scapy**

Il pattern base è:

```python
#!/usr/bin/env python3
from scapy.all import *

def print_pkt(pkt):
    pkt.show()

pkt = sniff(iface="br-XXXXXXXXXXXX", filter="icmp", prn=print_pkt)
```

Elementi importanti:

- `iface` indica l'interfaccia da osservare;
- `filter` usa sintassi BPF, la stessa famiglia di filtri di `tcpdump`;
- `prn` indica la funzione callback chiamata per ogni pacchetto catturato;
- `pkt.show()` stampa i campi decodificati.

Per sniffare più interfacce:

```python
iface = ["br-XXXXXXXXXXXX", "enp0s3"]
sniff(iface=iface, filter="icmp", prn=print_pkt)
```

### **6. Filtri BPF utili**

Il laboratorio chiede di provare filtri separati. Esempi:

```python
filter="icmp"
```

Cattura solo ICMP.

```python
filter="tcp and src host 10.9.0.6 and dst port 23"
```

Cattura pacchetti TCP provenienti da `10.9.0.6` e diretti alla porta Telnet `23`.

```python
filter="net 128.230.0.0/16"
```

Cattura pacchetti provenienti da o destinati a una subnet specifica.

> ⚠️ Nel task SEED viene chiesto di scegliere una subnet diversa da quella a cui è collegata la VM. Questo evita di catturare tutto il rumore della propria rete locale e costringe a ragionare sul filtro.

### **7. Visualizzare pacchetti**

Scapy offre più livelli di visualizzazione:

```python
pkt.show()
```

Mostra la struttura logica: livelli, campi, valori.

```python
hexdump(pkt)
```

Mostra i byte grezzi, utile per collegare header teorici e rappresentazione reale.

```python
ls(pkt)
```

Mostra campi disponibili e valori.

> 💡 `show()` è per capire; `hexdump()` è per verificare byte e offset; Wireshark è per correlare il risultato con una cattura grafica.

### **8. Spoofing ICMP con Scapy**

Lo spoofing consiste nel costruire un pacchetto con campi arbitrari, ad esempio un IP sorgente non appartenente alla macchina che invia.

Esempio base:

```python
from scapy.all import *

a = IP()
a.dst = "10.9.0.5"
a.src = "1.2.3.4"
b = ICMP()
p = a / b
send(p)
```

Con `a.src = "1.2.3.4"` stiamo dichiarando che il pacchetto sembra arrivare da `1.2.3.4`, anche se viene emesso dalla VM/container attaccante.

> 📌 TCP/IP classico non autentica l'indirizzo IP sorgente. Se la rete non filtra lo spoofing, il destinatario può ricevere pacchetti con sorgente falsificata.

### **9. Traceroute con TTL**

Il laboratorio chiede di implementare il principio di `traceroute` con Scapy. L'idea:

1. invio un pacchetto verso la destinazione con `TTL=1`;
2. il primo router decrementa TTL a zero, scarta il pacchetto e risponde con ICMP Time Exceeded;
3. ripeto con `TTL=2`, poi `TTL=3`, ecc.;
4. ogni risposta rivela un hop lungo il percorso.

Un singolo round:

```python
from scapy.all import *

a = IP()
a.dst = "1.2.3.4"
a.ttl = 3
b = ICMP()
send(a / b)
```

Si può automatizzare con Python oppure cambiare manualmente il TTL e osservare le risposte in Wireshark.

> ⚠️ Il risultato è una stima: pacchetti diversi possono seguire percorsi diversi, e firewall/router possono filtrare ICMP.

### **10. Sniff-and-then-spoof**

Il task finale combina sniffing e spoofing:

1. un host A esegue `ping X`;
2. il programma dell'attaccante sniffa le richieste ICMP echo request;
3. appena vede una richiesta, costruisce una risposta ICMP echo reply falsificata;
4. A riceve una risposta anche se `X` non è realmente attivo.

Schema logico:

```python
from scapy.all import *

def spoof_reply(pkt):
    if ICMP in pkt and pkt[ICMP].type == 8:
        ip = IP(src=pkt[IP].dst, dst=pkt[IP].src)
        icmp = ICMP(type=0, id=pkt[ICMP].id, seq=pkt[ICMP].seq)
        payload = pkt[Raw].load if Raw in pkt else b""
        send(ip / icmp / payload, verbose=0)

sniff(iface="br-XXXXXXXXXXXX", filter="icmp", prn=spoof_reply)
```

La parte importante non è il codice in sé, ma l'inversione dei campi:

- sorgente della risposta = destinazione della richiesta;
- destinazione della risposta = sorgente della richiesta;
- ICMP type passa da `8` echo request a `0` echo reply;
- id/seq/payload vengono mantenuti per rendere la risposta plausibile.

> ✅ Questo laboratorio dimostra il cuore dello spoofing: se riesco a osservare una richiesta e costruire rapidamente una risposta coerente, posso falsare la percezione del mittente.

