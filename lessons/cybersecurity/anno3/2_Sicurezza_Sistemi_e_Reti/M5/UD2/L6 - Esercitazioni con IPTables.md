## **Lezione 6: Esercitazioni con IPTables**

### **1. Introduzione**

Questa lezione traduce la teoria su Netfilter e IPTables in una serie di esercitazioni pratiche, da eseguire in laboratorio o con simulatori come **IMUNES**.  
L’obiettivo è comprendere come applicare le regole di filtraggio, logging e costruzione di catene personalizzate per gestire il traffico in rete.

---

## **2. IMUNES**

**IMUNES** (Inter-active MUltimedia Network Emulator) è un simulatore di reti basato su **Docker** che permette di disegnare graficamente una topologia di rete e configurarne gli host.

- Interfaccia grafica per creare router, host e collegamenti.
    
- Ogni nodo è un container Docker su cui è possibile accedere alla shell ed eseguire **iptables**.
    
- Le configurazioni non sono permanenti → devono essere salvate manualmente.
    

📎 Sito: [http://imunes.net/](http://imunes.net/)

---

## **3. Esercizio 1 – Filtraggio di una porta**

### **Obiettivo**

Bloccare il traffico sulla porta TCP 8080 prima sull’host e poi sul router.

### **Passaggi**

1. Avviare netcat sull’host 2:
    

```bash
nc -l -p 8080
```

2. Connettersi dall’host 1:
    

```bash
nc <ip_host2> 8080
```

3. Sul **client** la connessione funziona; ora blocchiamo il traffico.
    

### **Regole**

**Sull’host 2**

```bash
iptables -t filter -I INPUT -p tcp --dport 8080 -j DROP
```

**Flush**

```bash
iptables -F
```

**Sul router**

```bash
iptables -t filter -I FORWARD -p tcp --dport 8080 -d 10.0.1.10 -j DROP
```

> 🧠 Ricorda: prima si specifica il protocollo (`-p`), poi tutte le altre opzioni.

---

## **4. Esercizio 2 – Filtraggio di un Web Server**

### **Scenario**

Il Web Server (Host 2) è in ascolto sulla porta 80 TCP.

**Avvio**

```bash
service lighttpd start
```

**Test da Host 1**

```bash
curl 10.0.1.10
```

### **Regola di blocco**

```bash
iptables -A FORWARD -p tcp --dport 80 -d 10.0.1.10 -j DROP
```

Per SSH → protocollo TCP, porta 22.

---

## **5. Scelta della Chain**

|Scenario|Catena corretta|
|---|---|
|Regole sul router|**FORWARD** (instradamento tra reti)|
|Regole sul server|**INPUT** / **OUTPUT** (a seconda della direzione del traffico)|

> Un web server riceve HTTP → `INPUT`.  
> Un server SMTP invia email → `OUTPUT`.

---

## **6. Esercizio 3 – Filtraggio selettivo**

### **Obiettivo A**

Aggiungere un terzo host che **non può accedere** al web server.

```bash
iptables -A FORWARD -p tcp --dport 80 -d 10.0.1.10 -s 10.0.2.10 -j DROP
```

### **Obiettivo B**

Host 1 non può usare SSH verso host 2 né host 3.

```bash
iptables -A FORWARD -p tcp --dport 22 -d 10.0.1.10 -s 10.0.0.10 -j DROP
iptables -A FORWARD -p tcp --dport 22 -d 10.0.2.10 -s 10.0.0.10 -j DROP
```

### **Estensione**

Aggiungendo Host 4 nella rete di Host 3, bloccare l’intera rete:

```bash
iptables -A FORWARD -p tcp --dport 80 -d 10.0.1.10 -s 10.0.2.0/24 -j DROP
```

> `/24` → tutti gli indirizzi della rete di classe C.

---

## **7. Esempi di Firewall Stateful**

Politica iniziale: tutto chiuso, si aprono solo servizi autorizzati.

```bash
$IPTABLES -A INPUT -p tcp -i $INET_IFACE --dport 22 -m state --state NEW -j ACCEPT
$IPTABLES -A INPUT -p tcp -i $INET_IFACE --dport 80 -m state --state NEW -j ACCEPT
$IPTABLES -A OUTPUT -o $INET_IFACE -m state --state ESTABLISHED,RELATED -j ACCEPT
```

- Permesse solo nuove connessioni SSH/HTTP.
    
- In uscita solo connessioni già aperte.
    

---

## **8. Ispezione dei Pacchetti (Deep Match)**

Bloccare un URL contenente una stringa specifica (es. facebook):

```bash
$IPTABLES -A OUTPUT -p tcp --dport 80 -m string --string "facebook" --algo kmp -j DROP
```

> L’opzione `--algo kmp` usa l’algoritmo **Knuth-Morris-Pratt** per il match di stringhe nei payload.

---

## **9. Supporto FTP Stateful**

L’FTP utilizza più connessioni:

- **Control Port 21** → connessione principale (client → server)
    
- **Active Mode Port 20** → connessione relativa aperta dal client
    
- **Passive Mode** → connessione dal server verso il client su porte casuali
    

### **Regole**

```bash
# Control
iptables -A OUTPUT -p tcp --dport 21 -m state --state NEW,ESTABLISHED -j ACCEPT
iptables -A INPUT  -p tcp --sport 21 -m state --state ESTABLISHED -j ACCEPT

# Active
iptables -A OUTPUT -p tcp --dport 20 --sport 1024: -m state --state RELATED,ESTABLISHED -j ACCEPT
iptables -A INPUT  -p tcp --sport 20 --dport 1024: -m state --state ESTABLISHED -j ACCEPT

# Passive
iptables -A OUTPUT -p tcp --dport 1024: --sport 1024: -m state --state ESTABLISHED -j ACCEPT
iptables -A INPUT  -p tcp --sport 1024: --dport 1024: -m state --state RELATED,ESTABLISHED -j ACCEPT
```

---

## **10. Logging dei Pacchetti**

Il target **LOG** consente di registrare il traffico per debug e analisi.

### **Esempio base**

```bash
iptables -A FORWARD -j LOG --log-prefix="FORWARD: "
iptables -A INPUT  -j LOG --log-prefix="INPUT: "
iptables -A OUTPUT -j LOG --log-prefix="OUTPUT: "
```

### **Opzioni principali**

|Opzione|Descrizione|
|---|---|
|`--log-level`|livello syslog|
|`--log-prefix`|testo prefisso (≤ 29 caratteri)|
|`--log-tcp-sequence`|registra sequence number|
|`--log-tcp-options` / `--log-ip-options`|registra opzioni header|
|`--log-uid`|logga UID del processo (OUTPUT)|

Esempio di log:

```
Mar 24 11:38:50 FW-5 kernel: INPUT: IN=eth1 SRC=10.1.0.5 DST=224.0.0.251 PROTO=2
```

---

## **11. Catene User-Specific**

Creare catene dedicate rende la configurazione leggibile e modulare.  
Il salto tra catene avviene con `-j <nome_chain>`.

### **Categorie**

- **bad_tcp_packets** → pacchetti malformati
    
- **allowed** → connessioni valide
    
- **tcp_packets**, **udp_packets**, **icmp_packets** → classi di protocolli
    

---

### **11.1 Catena bad_tcp_packets**

Gestisce errori di protocollo e tentativi di spoofing.

```bash
$IPTABLES -N bad_tcp_packets
$IPTABLES -A bad_tcp_packets -p tcp ! --syn -m state --state NEW -j LOG --log-prefix "Pacchetto New senza SYN:"
$IPTABLES -A bad_tcp_packets -p tcp ! --syn -m state --state NEW -j DROP
$IPTABLES -A bad_tcp_packets -p tcp --tcp-flags SYN,ACK SYN,ACK -m state --state NEW -j REJECT --reject-with tcp-reset
```

---

### **11.2 Catena allowed**

Accetta solo connessioni nuove e già stabilite.

```bash
$IPTABLES -N allowed
$IPTABLES -A allowed -p tcp --syn -j ACCEPT
$IPTABLES -A allowed -p tcp -m state --state ESTABLISHED,RELATED -j ACCEPT
$IPTABLES -A allowed -p tcp -j DROP
```

---

### **11.3 Catene TCP / UDP / ICMP**

**TCP**

```bash
$IPTABLES -N tcp_packets
$IPTABLES -A tcp_packets -p tcp -s 0/0 --dport 21 -j allowed
$IPTABLES -A tcp_packets -p tcp -s 0/0 --dport 22 -j allowed
$IPTABLES -A tcp_packets -p tcp -s 0/0 --dport 80 -j allowed
```

**UDP**

```bash
$IPTABLES -N udp_packets
$IPTABLES -A udp_packets -p udp --dport 53 -j ACCEPT
$IPTABLES -A udp_packets -p udp --dport 123 -j ACCEPT
$IPTABLES -A udp_packets -p udp -i $INET_IFACE -d $INET_BROADCAST --dport 135:139 -j DROP
```

**ICMP**

```bash
$IPTABLES -N icmp_packets
$IPTABLES -A icmp_packets -p icmp --icmp-type 8 -j ACCEPT
$IPTABLES -A icmp_packets -p icmp --icmp-type 11 -j ACCEPT
```

---

### **11.4 Integrazione nelle Chain Principali**

```bash
$IPTABLES -A INPUT  -p all -m state --state ESTABLISHED,RELATED -j ACCEPT
$IPTABLES -A INPUT  -p tcp -i $INET_IFACE -j tcp_packets
$IPTABLES -A INPUT  -p udp -i $INET_IFACE -j udp_packets
$IPTABLES -A INPUT  -p icmp -i $INET_IFACE -j icmp_packets
$IPTABLES -A FORWARD -p tcp -j bad_tcp_packets
```

---

## **12. Esercizio Complesso – Protezione DMZ**

### **Servizi attivi**

- Web server → Lighttpd
    
- DNS → Bind9
    
- Mail server → SMTP
    

### **Requisiti**

- Solo la LAN interna può accedere ai servizi web nella DMZ.
    
- Il server mail può **solo inviare** messaggi verso l’esterno.
    

> Questo scenario integra concetti di DMZ, FORWARD stateful e NAT avanzato.

---

## **13. Conclusione**

Le esercitazioni dimostrano che la sicurezza di rete non dipende da una singola regola, ma dalla **coerenza logica del firewall**:

- filtraggio di porte e protocolli,
    
- logging intelligente,
    
- catene personalizzate per organizzare il traffico,
    
- e analisi stateful per controllare l’evoluzione delle connessioni.
    

> In una rete reale, la configurazione efficace è quella che unisce **rigore logico** e **chiarezza strutturale**.

---