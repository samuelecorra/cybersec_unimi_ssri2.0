## **Lezione 2: Ethernet, ARP, ICMP e IP nelle tracce**

### **1. Obiettivo**

Questa lezione copre gli esercizi del docente su Ethernet, ARP, ICMP e IP. Sono i livelli più vicini alla diagnosi di rete e allo scanning: prima di sapere quali porte sono aperte, bisogna capire chi è raggiungibile, quale gateway viene usato e quali indirizzi compaiono nel traffico.

### **2. Traccia Ethernet e ARP**

La traccia principale è:

```text
ethernet-ethereal-trace-1
```

Contiene 17 pacchetti: ARP, TCP e HTTP. È utile perché mostra bene la differenza tra:

- indirizzo MAC del prossimo hop;
- indirizzo IP del server remoto;
- indirizzo IP del client locale.

### **3. Prima richiesta ARP verificata**

Nella traccia, il primo pacchetto è una richiesta ARP:

```text
Ethernet src: 00:d0:59:a9:3d:68
Ethernet dst: ff:ff:ff:ff:ff:ff
ARP: who has 192.168.1.1? tell 192.168.1.105
```

Il secondo pacchetto è la risposta:

```text
192.168.1.1 is at 00:06:25:da:af:73
```

Quindi:

- host locale: `192.168.1.105`, MAC `00:d0:59:a9:3d:68`;
- gateway locale: `192.168.1.1`, MAC `00:06:25:da:af:73`;
- broadcast Ethernet: `ff:ff:ff:ff:ff:ff`.

> 📌 ARP non risolve l'indirizzo MAC del server remoto su Internet. Risolve il MAC del prossimo hop locale, tipicamente il gateway.

### **4. Perché il MAC destinazione non è quello di gaia**

Nella stessa traccia compare una richiesta HTTP verso:

```text
IP client: 192.168.1.105
IP server: 128.119.245.12
TCP: 1058 -> 80
HTTP: GET /ethereal-labs/HTTP-ethereal-lab-file3.html HTTP/1.1
```

Il frame Ethernet che trasporta questo pacchetto ha:

```text
MAC sorgente: 00:d0:59:a9:3d:68
MAC destinazione: 00:06:25:da:af:73
```

Il MAC destinazione è quello del gateway, non quello del server `128.119.245.12`.

> ⚠️ Errore tipico: pensare che un frame Ethernet verso Internet contenga il MAC del server remoto. Ethernet vale solo sul link locale.

### **5. ARP e scanning**

ARP è rilevante per il network scanning perché permette di scoprire host nella stessa LAN. Una scansione ARP è spesso più affidabile di un ping IP nella rete locale, perché molti host bloccano ICMP ma devono comunque rispondere ad ARP se comunicano sulla LAN.

Filtri utili:

```text
arp
arp.opcode == 1
arp.opcode == 2
eth.addr == 00:d0:59:a9:3d:68
```

### **6. ICMP ping**

Le tracce principali sono:

```text
icmp-ethereal-trace-1
icmp-ethereal-trace-2
```

La prima contiene 22 pacchetti, di cui 20 ICMP. Gli indirizzi principali sono:

```text
192.168.1.101 <-> 143.89.14.34
```

Il ping produce coppie:

- `Echo Request`;
- `Echo Reply`.

In Wireshark il filtro è:

```text
icmp
```

Oppure:

```text
icmp.type == 8
icmp.type == 0
```

dove `8` indica Echo Request e `0` indica Echo Reply.

### **7. ICMP traceroute**

La seconda traccia ICMP contiene 102 pacchetti ICMP e mostra un caso più vicino a traceroute. Gli indirizzi principali includono:

```text
192.168.1.101
138.96.146.2
10.216.228.1
24.218.0.153
24.128.190.197
```

Il principio è:

1. inviare pacchetti con TTL crescente;
2. ogni router decrementa il TTL;
3. quando il TTL arriva a zero, il router risponde con ICMP `Time Exceeded`;
4. quando si raggiunge la destinazione, si riceve una risposta diversa, a seconda del tipo di traceroute.

> 💡 Traceroute non "vede" magicamente la strada: forza i router intermedi a rivelarsi tramite messaggi ICMP.

### **8. Traccia IP**

La traccia:

```text
ip-ethereal-trace-1
```

contiene 380 pacchetti, soprattutto IP e ICMP. È utile per osservare:

- indirizzi sorgente e destinazione;
- TTL;
- protocollo trasportato da IP;
- relazione tra IP e ICMP;
- eventuali messaggi diagnostici.

Filtri utili:

```text
ip
ip.addr == 192.168.1.102
icmp
ip.ttl < 10
```

### **9. Checklist per rispondere alle domande**

Quando una domanda chiede un indirizzo Ethernet:

1. seleziona il frame;
2. espandi `Ethernet II`;
3. leggi `Source` e `Destination`.

Quando chiede un indirizzo IP:

1. espandi `Internet Protocol`;
2. leggi `Source Address` e `Destination Address`.

Quando chiede se un indirizzo Ethernet è quello del server remoto:

1. confronta IP server e MAC destinazione;
2. se il server è fuori LAN, il MAC destinazione è il gateway;
3. conferma cercando la risposta ARP.

> ✅ Ethernet e ARP spiegano il traffico locale; IP e ICMP spiegano la raggiungibilità logica tra reti.

