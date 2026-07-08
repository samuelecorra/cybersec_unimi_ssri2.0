## **Lezione 4: DHCP, NAT, TLS e 802.11 nelle tracce Wireshark**

### **1. Obiettivo**

Questa lezione completa il pacchetto di esercizi del docente con protocolli e scenari che non sono solo "web browsing": assegnazione dinamica degli indirizzi, traduzione NAT, traffico cifrato e catture wireless.

### **2. DHCP**

La traccia:

```text
dhcp-ethereal-trace-1
```

contiene traffico DHCP, ARP, ICMP e broadcast locale. Gli indirizzi più ricorrenti sono:

```text
0.0.0.0
255.255.255.255
192.168.1.1
192.168.1.101
192.168.1.255
```

DHCP usa UDP:

```text
client: porta 68
server: porta 67
```

Nella traccia compaiono pacchetti come:

```text
0.0.0.0:68 -> 255.255.255.255:67
192.168.1.1:67 -> 255.255.255.255:68
192.168.1.101:68 -> 192.168.1.1:67
```

> 📌 `0.0.0.0` compare perché il client può non avere ancora un indirizzo IP valido quando chiede una configurazione DHCP.

### **3. Sequenza DHCP**

La sequenza concettuale è:

1. **Discover:** il client cerca un server DHCP.
2. **Offer:** il server propone una configurazione.
3. **Request:** il client richiede formalmente l'indirizzo proposto.
4. **ACK:** il server conferma.

Filtri utili:

```text
dhcp
bootp
udp.port == 67 || udp.port == 68
```

Wireshark usa spesso il dissector `BOOTP/DHCP`, perché DHCP è storicamente costruito sopra BOOTP.

### **4. NAT: confronto home side e ISP side**

Le tracce:

```text
NAT_home_side.pcap
NAT_ISP_side.pcap
```

mostrano lo stesso traffico visto da due lati diversi del NAT.

Sul lato interno compare l'indirizzo privato:

```text
192.168.1.100
```

Sul lato ISP compare l'indirizzo pubblico tradotto:

```text
71.192.34.104
```

Un esempio verificato:

```text
home side:
192.168.1.100:4331 -> 74.125.106.31:80

ISP side:
71.192.34.104:4331 -> 74.125.106.31:80
```

La destinazione esterna resta la stessa, ma l'indirizzo sorgente cambia.

> 📌 NAT riscrive gli indirizzi, e spesso anche le porte. In questa traccia l'esempio mantiene la porta `4331`, ma non bisogna assumerlo come regola generale.

### **5. Come analizzare due tracce NAT**

Metodo pratico:

1. apri `NAT_home_side.pcap`;
2. filtra `http || dns || tcp.port == 80`;
3. annota tuple sorgente/destinazione;
4. apri `NAT_ISP_side.pcap`;
5. cerca gli stessi server esterni e le stesse porte;
6. confronta il sorgente interno con il sorgente pubblico.

La tupla da confrontare è:

```text
IP_sorgente:porta_sorgente -> IP_destinazione:porta_destinazione
```

### **6. TLS/SSL**

La traccia:

```text
ssl-ethereal-trace-1
```

contiene traffico su TCP porta `443` e mostra i record principali del handshake:

- `ClientHello`;
- `ServerHello`;
- `Client Key Exchange`;
- `Change Cipher Spec`;
- `Encrypted Handshake`;
- `Application Data`.

Il punto didattico è che TLS lascia visibile la struttura del canale, ma cifra il contenuto applicativo.

> ⚠️ In una traccia TLS puoi spesso vedere IP, porte, handshake, certificati e metadati; non puoi leggere il contenuto HTTP applicativo se non hai le chiavi di sessione.

### **7. Differenza tra HTTP e TLS**

Nel traffico HTTP in chiaro puoi leggere:

```text
GET /path HTTP/1.1
Host: ...
User-Agent: ...
Authorization: ...
```

Nel traffico TLS normalmente vedi:

```text
TCP 443
TLS ClientHello
TLS ServerHello
Application Data
```

Il payload applicativo è cifrato. Questo è essenziale anche in ottica difensiva: l'analista può ancora ricostruire flussi, endpoint, timing, dimensioni e handshake, ma non può sempre leggere il contenuto.

### **8. Traccia 802.11**

La traccia:

```text
Wireshark_802_11.pcap
```

contiene traffico wireless e IP. È più ricca e rumorosa: 2364 pacchetti nel set verificato. Gli indirizzi IP principali includono:

```text
192.168.1.109
128.119.245.12
128.119.240.19
64.233.187.104
```

Questa traccia è utile per capire che una cattura Wi-Fi può includere sia campi 802.11 sia traffico IP/TCP/HTTP decapsulato, a seconda del punto di cattura e del formato della traccia.

Filtri utili:

```text
wlan
wlan.fc.type_subtype
ip
tcp.port == 80
http
```

### **9. Collegamento con network scanning**

DHCP, NAT, TLS e Wi-Fi sembrano temi diversi, ma nello scanning contano molto:

- DHCP rivela configurazioni di rete, gateway e server locali.
- NAT spiega perché l'IP visto dall'esterno può non coincidere con l'IP reale dell'host interno.
- TLS limita l'ispezione del contenuto ma non nasconde endpoint e metadati.
- 802.11 introduce traffico radio, associazioni e frame di gestione.

> ✅ L'analista deve sempre chiedersi da quale punto della rete sta osservando la traccia. Lo stesso evento cambia aspetto se visto lato client, lato gateway, lato ISP o su rete wireless.

