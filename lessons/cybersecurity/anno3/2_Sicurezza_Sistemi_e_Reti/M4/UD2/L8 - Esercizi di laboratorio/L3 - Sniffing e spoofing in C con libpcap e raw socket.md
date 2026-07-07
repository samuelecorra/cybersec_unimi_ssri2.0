## **Lezione 3: Sniffing e spoofing in C con libpcap e raw socket**

### **1. Perché passare da Scapy al C**

Scapy è ideale per capire rapidamente la logica dei pacchetti. Il PDF SEED però vuole anche mostrare come gli strumenti di sniffing/spoofing siano implementati a livello software. Per questo introduce:

- `libpcap`, per catturare pacchetti;
- raw socket, per costruire e inviare pacchetti arbitrari;
- strutture C per interpretare header IP, TCP, UDP e ICMP;
- attenzione a byte order host/rete.

> 📌 Wireshark, tcpdump, Scapy e molti tool di sicurezza si appoggiano allo stesso principio: catturare pacchetti grezzi, interpretarli, eventualmente costruirne di nuovi.

### **2. Sniffing con libpcap**

Con `libpcap`, un programma sniffer segue questa sequenza:

1. apre una sessione live su una NIC;
2. compila un filtro BPF;
3. applica il filtro;
4. registra una callback;
5. entra in loop e riceve pacchetti;
6. chiude l'handle.

Schema operativo:

```c
pcap_t *handle;
char errbuf[PCAP_ERRBUF_SIZE];
struct bpf_program fp;
char filter_exp[] = "icmp";
bpf_u_int32 net;

handle = pcap_open_live("br-XXXXXXXXXXXX", BUFSIZ, 1, 1000, errbuf);
pcap_compile(handle, &fp, filter_exp, 0, net);
pcap_setfilter(handle, &fp);
pcap_loop(handle, -1, got_packet, NULL);
pcap_close(handle);
```

Compilazione:

```bash
gcc -o sniff sniff.c -lpcap
```

> ⚠️ Il nome interfaccia `"br-XXXXXXXXXXXX"` è un placeholder. Nel laboratorio reale va trovato con `ip addr` cercando l'interfaccia che ha IP `10.9.0.1`.

### **3. Task 2.1A del laboratorio SEED**

Il task chiede di scrivere un programma sniffer che stampi:

- indirizzo IP sorgente;
- indirizzo IP destinazione;
- almeno per ogni pacchetto catturato.

Il programma deve dimostrare che:

- il filtro funziona;
- i pacchetti vengono catturati dalla NIC giusta;
- il parsing dell'header IP avviene correttamente.

Checklist pratica:

```bash
cd ~/ssri-lab/m4-ud2-l8/tcpspoofing/Labsetup
docker compose up -d
ip addr | grep -A3 '10.9.0.1'
gcc -o sniff sniff.c -lpcap
sudo ./sniff
```

Da un container utente:

```bash
docksh hostA-10.9.0.5
ping 10.9.0.6
```

### **4. Perché servono privilegi**

Uno sniffer deve chiedere al kernel accesso ai pacchetti catturati dall'interfaccia. Un utente normale non dovrebbe poter leggere liberamente traffico di rete, perché potrebbe intercettare credenziali, sessioni, token e dati di altri processi.

Per questo:

```bash
sudo ./sniff
```

può funzionare, mentre:

```bash
./sniff
```

può fallire con errore di permessi.

> ✅ L'osservazione richiesta dal task è proprio questa: sniffing e spoofing non sono normali operazioni utente, ma richiedono privilegi o capability specifiche.

### **5. Spoofing con raw socket**

Un sistema operativo normale non lascia a un utente qualsiasi il controllo completo degli header IP/TCP/UDP. Quando usiamo socket TCP/UDP standard, il kernel imposta molti campi: porta sorgente, checksum, sequence number, lunghezze, IP sorgente coerente con l'interfaccia.

Con i raw socket, un processo privilegiato può costruire manualmente il pacchetto:

1. crea un socket raw;
2. imposta l'opzione `IP_HDRINCL`, cioè dichiara che l'header IP è già incluso;
3. riempie un buffer con header e payload;
4. invia il buffer con `sendto()`.

Schema:

```c
int sd = socket(AF_INET, SOCK_RAW, IPPROTO_RAW);
setsockopt(sd, IPPROTO_IP, IP_HDRINCL, &enable, sizeof(enable));
sendto(sd, buffer, packet_len, 0, (struct sockaddr *)&sin, sizeof(sin));
```

> 📌 `IP_HDRINCL` è cruciale: senza questa opzione il kernel potrebbe aggiungere o gestire l'header IP al posto nostro.

### **6. Type casting del buffer**

Il PDF SEED sottolinea che lavorare direttamente su un buffer di byte è scomodo. Una tecnica comune è interpretare pezzi del buffer come strutture C:

```c
char buffer[1024];

struct ipheader *ip = (struct ipheader *) buffer;
struct udpheader *udp = (struct udpheader *) (buffer + sizeof(struct ipheader));
```

Così:

- `ip` punta all'inizio del buffer;
- `udp` punta subito dopo l'header IP;
- l'accesso ai campi avviene con `ip->...` e `udp->...`.

> ⚠️ Questa tecnica richiede attenzione a padding, dimensione delle strutture, endianess e layout reale degli header. Nei laboratori funziona perché le strutture sono costruite per rappresentare header standard.

### **7. Byte order**

Molti campi di rete sono in **network byte order**, cioè big-endian. Una CPU x86 usa normalmente little-endian. Per questo in C si usano funzioni come:

```c
htons()  // host to network short
htonl()  // host to network long
ntohs()  // network to host short
ntohl()  // network to host long
```

Esempio:

```c
tcp->tcp_dport = htons(23);
ip->iph_len = htons(sizeof(struct ipheader) + sizeof(struct tcpheader));
```

> ⚠️ Un pacchetto logicamente corretto ma con byte order sbagliato diventa spazzatura per il destinatario.

### **8. Task 2.2A: scrivere un programma di spoofing**

Il task chiede di scrivere un programma C che invii pacchetti IP spoofati e di fornire evidenza tramite cattura Wireshark.

L'evidenza minima deve mostrare:

- IP sorgente scelto dal programma, non necessariamente appartenente all'attaccante;
- IP destinazione corretto;
- protocollo atteso;
- pacchetto visibile sulla rete/interfaccia corretta.

Workflow:

```bash
gcc -o spoof spoof.c
sudo ./spoof
sudo tcpdump -i br-XXXXXXXXXXXX -n
```

Oppure si apre Wireshark e si filtra:

```text
ip.src == 1.2.3.4
```

### **9. Task 2.2B: spoofing di ICMP Echo Request**

Qui l'obiettivo è creare un ICMP echo request fingendo che venga da un'altra macchina. Se il destinatario risponde, la risposta viene inviata all'IP falsificato, non necessariamente all'attaccante.

Questo dimostra due proprietà:

- il destinatario può accettare il pacchetto come se fosse legittimo;
- lo spoofing rompe il normale modello richiesta-risposta, perché la risposta segue il campo IP sorgente.

> 💡 Se falsifico `src=1.2.3.4` e mando a `dst=10.9.0.5`, la risposta andrà verso `1.2.3.4`. Lo spoofing è quindi spesso "one-way" se non controllo anche il percorso di ritorno.

### **10. Task 2.3: sniff-and-then-spoof in C**

Il task finale C unisce:

- cattura con `libpcap`;
- parsing della richiesta ICMP;
- costruzione di una risposta ICMP spoofata con raw socket.

La logica è identica alla versione Scapy:

1. sniffa ICMP echo request;
2. legge IP sorgente/destinazione;
3. costruisce echo reply invertendo sorgente e destinazione;
4. preserva dati utili come identificatore, sequenza e payload;
5. invia la risposta.

> ✅ Questo esercizio è importante perché collega basso livello C, header reali, privilegi, sniffing e spoofing in un unico flusso operativo.

