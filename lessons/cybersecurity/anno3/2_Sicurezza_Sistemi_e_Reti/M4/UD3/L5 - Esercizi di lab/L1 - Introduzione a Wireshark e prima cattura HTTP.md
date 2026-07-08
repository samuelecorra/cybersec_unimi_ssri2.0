## **Lezione 1: Introduzione a Wireshark e prima cattura HTTP**

### **1. Perché usare Wireshark**

Wireshark è un packet analyzer: osserva i frame che passano su una certa interfaccia e li interpreta secondo le strutture dei protocolli conosciuti. Il laboratorio introduttivo insiste su un'idea fondamentale: capire i protocolli non significa solo conoscere la teoria, ma vedere la sequenza reale dei messaggi.

Un packet sniffer è passivo: non genera traffico in autonomia, ma riceve una copia dei frame trasmessi o ricevuti dall'interfaccia monitorata. Sopra questa cattura grezza, Wireshark aggiunge l'analisi dei protocolli.

> 📌 Wireshark non vede "HTTP" direttamente sul filo: cattura frame di livello collegamento, poi decapsula Ethernet, IP, TCP e infine interpreta il payload come HTTP.

### **2. Struttura logica dello sniffer**

Il PDF introduttivo distingue due componenti:

- **Packet capture library:** cattura una copia dei frame di livello collegamento che attraversano l'interfaccia.
- **Packet analyzer:** interpreta i campi dei protocolli e li mostra in forma leggibile.

Il processo è gerarchico:

```text
Frame Ethernet/Wi-Fi -> datagramma IP -> segmento TCP/UDP -> messaggio applicativo
```

Non bisogna però confondere il linguaggio di Wireshark con i livelli formali: Wireshark usa spesso la parola "packet" in senso pratico per indicare frame, datagrammi, segmenti e messaggi.

### **3. Interfaccia di Wireshark**

Il PDF mostra la finestra di Wireshark durante la cattura.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Gli elementi principali sono:

- **Command menus:** menu `File`, `Capture`, `Analyze`, `View`, ecc.
- **Packet-listing window:** elenco dei pacchetti con numero, tempo, sorgente, destinazione, protocollo e info.
- **Packet-header details window:** struttura espandibile del pacchetto selezionato.
- **Packet-contents window:** bytes grezzi in esadecimale e ASCII.
- **Display filter field:** barra in cui scrivere filtri come `http`, `tcp`, `dns`, `ip.addr == ...`.

> 💡 Il pannello più importante per studiare è quello centrale: lì vedi l'incapsulamento e puoi espandere solo il protocollo che ti interessa.

### **4. Cattura live dell'esercizio introduttivo**

L'esercizio base proposto è:

1. Avviare Wireshark.
2. Scegliere l'interfaccia con connettività Internet.
3. Avviare la cattura.
4. Aprire nel browser:

```text
http://gaia.cs.umass.edu/wireshark-labs/INTRO-wireshark-file1.html
```

5. Fermare la cattura.
6. Scrivere `http` nel display filter.
7. Trovare la richiesta `HTTP GET`.

Se si lavora oggi con browser moderni, bisogna ricordare due cose:

- una pagina `http://` è volutamente non cifrata e quindi leggibile;
- se si visita un sito `https://`, Wireshark vede TCP/TLS ma non vede il contenuto HTTP in chiaro.

> ⚠️ VPN, HTTPS, HTTP/3 e QUIC possono nascondere o spostare il traffico applicativo rispetto a ciò che il laboratorio classico si aspetta. Per il primo esercizio usare esattamente URL `http://`, non `https://`.

### **5. Domande del primo laboratorio**

Le domande canoniche servono a verificare che tu sappia usare l'interfaccia:

- Elencare tre protocolli visibili nella colonna `Protocol` senza filtro.
- Calcolare quanto tempo passa tra `HTTP GET` e `HTTP OK`.
- Trovare l'indirizzo IP di `gaia.cs.umass.edu`.
- Trovare l'indirizzo IP del proprio host.
- Stampare o esportare i messaggi HTTP `GET` e `OK`.

### **6. Come misurare il tempo GET -> OK**

Nel packet list seleziona il pacchetto `GET` e annota il valore `Time`; poi seleziona la risposta `HTTP/1.1 200 OK` e annota il valore `Time`.

La differenza è:

$$
\Delta t = t_{\text{OK}} - t_{\text{GET}}
$$

> 📌 Questo non è il tempo totale di caricamento della pagina: è il tempo tra una richiesta HTTP e la risposta corrispondente osservate nella traccia.

### **7. Lettura stratificata del pacchetto HTTP**

Nel pacchetto `GET`, devi saper individuare:

- a livello Ethernet/Wi-Fi: MAC sorgente e MAC destinazione;
- a livello IP: IP del client e IP del server;
- a livello TCP: porta effimera del client e porta `80` del server;
- a livello HTTP: metodo `GET`, path richiesto, versione HTTP e header.

Per esempio, in una traccia HTTP classica, la connessione è riconoscibile perché il traffico usa TCP porta `80`.

### **8. Perché compaiono molti altri pacchetti**

Anche se l'utente "ha solo aperto una pagina", nella cattura possono apparire:

- DNS, per risolvere il nome del server.
- ARP, per trovare il MAC del gateway locale.
- TCP, per stabilire e gestire la connessione.
- HTTP, per scaricare la pagina.
- Eventuale traffico di sistema, browser, multicast o servizi locali.

> ✅ Prima lezione pratica: una cattura reale contiene sempre rumore. L'abilità non è eliminarlo a caso, ma filtrarlo mantenendo il contesto.

