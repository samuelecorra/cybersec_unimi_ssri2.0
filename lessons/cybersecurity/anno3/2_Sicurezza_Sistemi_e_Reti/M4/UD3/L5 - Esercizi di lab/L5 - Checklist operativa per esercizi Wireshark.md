## **Lezione 5: Checklist operativa per esercizi Wireshark**

### **1. Obiettivo**

Questa lezione riassume il metodo da usare davanti a una traccia Wireshark. È pensata come ponte tra gli esercizi canonici del docente e il modulo M7 orientato alla prova d'esame.

### **2. Prima lettura senza filtri**

Appena apri una traccia:

1. guarda il numero totale di pacchetti;
2. scorri la colonna `Protocol`;
3. identifica gli indirizzi più ricorrenti;
4. cerca pattern evidenti: DNS seguito da TCP, ARP prima di traffico locale, HTTP `GET`, TCP SYN, ICMP Echo.

> 📌 Non applicare subito un filtro troppo stretto: prima capisci il contesto generale.

### **3. Filtri minimi da sapere**

Filtri per protocollo:

```text
arp
icmp
ip
tcp
udp
dns
http
tls
dhcp
bootp
```

Filtri per indirizzo:

```text
ip.addr == 192.168.1.102
ip.src == 192.168.1.102
ip.dst == 128.119.245.12
eth.addr == 00:d0:59:a9:3d:68
```

Filtri per porta:

```text
tcp.port == 80
tcp.dstport == 80
udp.port == 53
udp.port == 67 || udp.port == 68
```

Filtri TCP utili:

```text
tcp.flags.syn == 1
tcp.flags.syn == 1 && tcp.flags.ack == 0
tcp.flags.reset == 1
tcp.analysis.retransmission
```

### **4. Come rispondere alle domande HTTP**

Per HTTP cerca:

```text
http
```

Poi leggi:

- metodo: `GET`, `POST`, `HEAD`;
- path richiesto;
- versione: `HTTP/1.0`, `HTTP/1.1`;
- status code: `200`, `304`, `401`, `404`;
- header `Host`;
- header `User-Agent`;
- header `Accept-Language`;
- header `Last-Modified`;
- eventuale `Authorization`.

Domande tipiche:

- il browser usa HTTP/1.0 o 1.1?
- che codice di stato restituisce il server?
- quanti byte di contenuto vengono restituiti?
- il secondo GET contiene `If-Modified-Since`?
- la risposta contiene davvero il corpo della risorsa?

### **5. Come rispondere alle domande TCP**

Per TCP cerca il three-way handshake:

```text
tcp.flags.syn == 1
```

Poi identifica:

- SYN client -> server;
- SYN,ACK server -> client;
- ACK client -> server.

Per verificare gli acknowledgment:

$$
ACK_{\text{SYNACK}} = SEQ_{\text{SYN}} + 1
$$

Per i segmenti dati:

$$
SEQ_{\text{successivo}} = SEQ_{\text{corrente}} + \text{byte di payload}
$$

> ⚠️ In Wireshark possono essere attivi i numeri di sequenza relativi. Se i valori non coincidono con quelli assoluti della traccia, controlla `Preferences -> Protocols -> TCP -> Relative sequence numbers`.

### **6. Come rispondere alle domande DNS**

Filtro:

```text
dns
```

Campi da leggere:

- `Transaction ID`;
- `Queries`;
- tipo query: `A`, `AAAA`, `NS`, `MX`, `PTR`;
- `Answers`;
- indirizzo del resolver locale;
- porta UDP sorgente e destinazione.

Domanda tipica:

> Il successivo SYN TCP va verso uno degli indirizzi restituiti dal DNS?

Metodo:

1. annota gli IP nella risposta DNS;
2. filtra `tcp.flags.syn == 1 && tcp.flags.ack == 0`;
3. cerca il SYN verso porta `80` o `443`;
4. confronta l'IP destinazione.

### **7. Come rispondere alle domande ARP/Ethernet**

Filtro:

```text
arp
```

Campi da leggere:

- `Sender MAC address`;
- `Sender IP address`;
- `Target MAC address`;
- `Target IP address`;
- Ethernet `Source`;
- Ethernet `Destination`.

Domanda tipica:

> L'indirizzo Ethernet destinazione è quello di gaia.cs.umass.edu?

Risposta ragionata: se il server è fuori dalla LAN, no. Il frame Ethernet viene inviato al gateway locale.

### **8. Come rispondere alle domande ICMP/IP**

Filtri:

```text
icmp
ip.ttl
```

Campi utili:

- `Type`;
- `Code`;
- `Identifier`;
- `Sequence Number`;
- `TTL`;
- IP sorgente e destinazione.

Per ping:

- `Echo Request`: tipo `8`;
- `Echo Reply`: tipo `0`.

Per traceroute:

- cercare `Time Exceeded`;
- osservare TTL crescente;
- elencare i router intermedi.

### **9. Come rispondere alle domande NAT**

Apri le due tracce in parallelo:

```text
NAT_home_side.pcap
NAT_ISP_side.pcap
```

Confronta la stessa connessione:

```text
home side: IP privato:porta -> server:porta
ISP side:  IP pubblico:porta -> server:porta
```

Se cambia solo l'indirizzo sorgente, è NAT semplice sull'indirizzo. Se cambia anche la porta sorgente, è PAT/NAPT.

### **10. Come rispondere alle domande TLS**

Filtro:

```text
tls
```

o, nelle versioni vecchie:

```text
ssl
```

Cerca:

- `ClientHello`;
- `ServerHello`;
- certificato;
- `Client Key Exchange`;
- `Change Cipher Spec`;
- `Application Data`.

> 📌 Il fatto che il contenuto applicativo sia illeggibile non significa che la traccia sia inutile: metadati, endpoint, tempi, dimensioni e handshake restano analizzabili.

### **11. Routine finale da esame**

Davanti a una traccia sconosciuta:

1. Identifica IP principali.
2. Identifica protocolli principali.
3. Cerca DNS iniziali.
4. Cerca TCP SYN e porte.
5. Cerca HTTP/TLS.
6. Cerca ARP/ICMP se la domanda riguarda rete locale o raggiungibilità.
7. Usa `Follow TCP Stream` solo quando vuoi ricostruire una conversazione applicativa.
8. Usa `Statistics -> Conversations` per capire chi parla di più.
9. Usa `Expert Information` come supporto, non come verità assoluta.
10. Scrivi risposte sempre motivate da pacchetti e campi.

> ✅ Una buona risposta Wireshark non dice solo "vedo HTTP": indica pacchetto, filtro usato, endpoint, porta, campo e interpretazione.

