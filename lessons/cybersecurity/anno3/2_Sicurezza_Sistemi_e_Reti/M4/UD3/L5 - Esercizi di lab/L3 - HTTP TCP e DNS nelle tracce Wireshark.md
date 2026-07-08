## **Lezione 3: HTTP, TCP e DNS nelle tracce Wireshark**

### **1. Obiettivo**

Questa lezione copre la parte più vicina alla prova pratica: seguire una connessione, riconoscere richieste e risposte, misurare tempi, leggere header HTTP, interpretare il three-way handshake TCP e collegare DNS alla successiva connessione verso il server.

### **2. Tracce HTTP**

Le tracce HTTP principali sono:

```text
http-ethereal-trace-1
http-ethereal-trace-2
http-ethereal-trace-3
http-ethereal-trace-4
http-ethereal-trace-5
```

Servono rispettivamente per osservare:

- richiesta semplice e risposta `200 OK`;
- caching e risposta `304 Not Modified`;
- documento HTTP semplice;
- pagina con oggetti embedded;
- autenticazione HTTP Basic.

### **3. HTTP semplice: richiesta e risposta**

Nella traccia `http-ethereal-trace-1` compaiono:

```text
192.168.1.102:4127 -> 128.119.245.12:80
GET /ethereal-labs/lab2-1.html HTTP/1.1

128.119.245.12:80 -> 192.168.1.102:4127
HTTP/1.1 200 OK
```

Subito dopo compare anche una richiesta per:

```text
GET /favicon.ico HTTP/1.1
```

con risposta:

```text
HTTP/1.1 404 Not Found
```

> 💡 Anche una pagina apparentemente singola può generare richieste aggiuntive automatiche, come `favicon.ico`.

### **4. Caching HTTP**

Nella traccia `http-ethereal-trace-2` si vede il comportamento della cache:

```text
GET /ethereal-labs/lab2-2.html HTTP/1.1
HTTP/1.1 200 OK
```

Poi, dopo il refresh:

```text
GET /ethereal-labs/lab2-2.html HTTP/1.1
HTTP/1.1 304 Not Modified
```

Il codice `304 Not Modified` indica che il server non reinvia esplicitamente il contenuto perché il client ha già una copia valida.

> 📌 Se la risposta è `304`, il corpo della pagina non viene ritrasmesso come in un `200 OK` completo.

### **5. Oggetti embedded**

Nella traccia `http-ethereal-trace-4` il browser scarica una pagina e poi oggetti incorporati:

```text
GET /ethereal-labs/lab2-4.html HTTP/1.1
GET /catalog/images/pearson-logo-footer.gif HTTP/1.1
GET /~kurose/cover.jpg HTTP/1.1
```

Gli oggetti non arrivano necessariamente dallo stesso indirizzo IP:

```text
128.119.245.12
165.193.123.218
134.241.6.82
```

Questo spiega perché una pagina web può generare connessioni parallele verso più server.

### **6. Autenticazione HTTP**

Nella traccia `http-ethereal-trace-5` si vede una pagina protetta:

```text
GET /ethereal-labs/protected_pages/lab2-5.html HTTP/1.1
HTTP/1.1 401 Authorization Required
```

Dopo l'invio delle credenziali:

```text
GET /ethereal-labs/protected_pages/lab2-5.html HTTP/1.1
HTTP/1.1 200 OK
```

Il PDF del docente indica credenziali:

```text
username: wireshark-students
password: network
```

> ⚠️ In HTTP Basic su canale non cifrato, le credenziali sono facilmente recuperabili dalla traccia. Per questo è un caso didattico utile ma insicuro.

### **7. TCP three-way handshake**

La traccia:

```text
tcp-ethereal-trace-1
```

mostra una connessione TCP verso `128.119.245.12:80` dal client `192.168.1.102:1161`.

I primi tre pacchetti sono:

```text
1. 192.168.1.102:1161 -> 128.119.245.12:80  SYN
   seq = 232129012

2. 128.119.245.12:80 -> 192.168.1.102:1161  SYN, ACK
   seq = 883061785
   ack = 232129013

3. 192.168.1.102:1161 -> 128.119.245.12:80  ACK
   seq = 232129013
   ack = 883061786
```

Il valore `ack = 232129013` è ottenuto come:

$$
232129012 + 1 = 232129013
$$

perché il flag SYN consuma un numero di sequenza.

> 📌 Nel three-way handshake, SYN e FIN consumano un numero di sequenza anche se non trasportano dati applicativi.

### **8. Segmenti dati TCP**

Nella stessa traccia, il pacchetto 4 contiene:

```text
POST /ethereal-labs/lab3-1-reply.htm HTTP/1.1
```

Il segmento ha:

```text
seq = 232129013
payload TCP = 565 byte
```

Il segmento successivo del client ha:

```text
seq = 232129578
```

La relazione è:

$$
232129013 + 565 = 232129578
$$

> ✅ I numeri di sequenza TCP avanzano in base ai byte di payload trasportati.

### **9. DNS: query e risposta**

La traccia:

```text
dns-ethereal-trace-1
```

contiene la query:

```text
www.ietf.org A
```

e una risposta con due indirizzi:

```text
132.151.6.75
65.246.255.51
```

La query DNS usa UDP porta `53`. Tipicamente:

- porta sorgente client: effimera;
- porta destinazione query: `53`;
- porta sorgente risposta: `53`;
- porta destinazione risposta: la porta effimera del client.

### **10. DNS con record A, NS e PTR**

Le altre tracce mostrano esempi diversi:

- `dns-ethereal-trace-2`: `www.mit.edu A -> 18.7.22.83`.
- `dns-ethereal-trace-3`: `mit.edu NS -> bitsy.mit.edu`, `strawb.mit.edu`, `w20ns.mit.edu`.
- `dns-ethereal-trace-4`: `www.aiit.or.kr A -> 218.36.94.200`.
- `udp-wireshark-trace.pcap`: query DNS su UDP in una traccia mista.

I record più importanti sono:

- `A`: nome host -> indirizzo IPv4.
- `NS`: dominio -> name server autoritativi.
- `PTR`: indirizzo IP -> nome, tramite reverse DNS.

### **11. Collegare DNS e TCP SYN**

Una domanda tipica chiede se l'indirizzo IP destinazione del successivo pacchetto TCP SYN coincide con uno degli indirizzi ricevuti nella risposta DNS.

Metodo:

1. filtra `dns`;
2. leggi gli indirizzi nella risposta;
3. rimuovi il filtro o applica `tcp.flags.syn == 1 && tcp.flags.ack == 0`;
4. guarda il primo SYN verso il servizio web;
5. confronta l'IP destinazione.

> ✅ DNS prepara la connessione: prima il nome viene risolto, poi TCP usa uno degli indirizzi ottenuti.

