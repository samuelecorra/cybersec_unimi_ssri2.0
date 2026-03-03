## **Lezione 2: Tecniche di scansione**

### **1. Introduzione — obiettivo della lezione**

Questa lezione spiega come si scoprono host e porte, cosa significano i risultati di una scansione e quali tecniche pratiche vengono usate sul campo (SYN, `connect()`, UDP, ARP, ICMP, scansioni stealth). Approccio Feynman: prima il principio semplice, poi la traduzione in pacchetti, comportamento di rete e comandi pratici.

### **2. Risultati della scansione — cosa interpreta lo scanner**

#### **Stati fondamentali**

- **open (aperta)**: il target risponde in modo che dimostra un servizio in ascolto (es. SYN/ACK su TCP).
    
- **closed (chiusa)**: la porta esiste ma non c’è servizio (es. risposta RST su TCP).
    
- **filtered (filtrata)**: un filtro/firewall impedisce di determinare lo stato (nessuna risposta o ICMP unreachable).
    

#### **Stati aggiuntivi (stile nmap)**

- **unfiltered**: la porta è raggiungibile ma non è possibile decidere se aperta o chiusa.
    
- **open|filtered** / **closed|filtered**: ambiguità da risposte insufficienti.  
    Ricorda: “nessuna risposta” non equivale automaticamente a “closed”.
    

### **3. Scoperta host (host discovery)**

#### **ARP scan (sottorete locale)**

Invio di richieste ARP broadcast per ottenere il MAC; i device attivi rispondono. Funziona solo sulla stessa LAN; è la tecnica più affidabile e veloce per discovery locale.

#### **ICMP (ping)**

`ICMP Echo Request` → `Echo Reply` indica host up. Molti firewall bloccano ICMP: possibile falso negativo.

#### **TCP/UDP discovery**

- **TCP SYN ping**: inviare SYN su porta nota (es. 80). Risposta SYN/ACK o RST indica host attivo.
    
- **TCP ACK ping**: inviare ACK e attendere RST; utile per bypassare firewall stateful.
    
- **UDP ping**: inviare UDP a porta probabile; ICMP port unreachable indica host up.
    
- **IP-protocol ping**: inviare pacchetti con numeri di protocollo non usati; la risposta ICMP protocol unreachable rivela l’host.
    

### **4. Principi RFC (comportamento TCP di base)**

#### **Regole operative essenziali (RFC 793)**

1. Un segmento con **RST** ricevuto viene scartato senza risposta.
    
2. Se la porta è **closed** e arriva segmento senza RST → il target risponde con **RST**.
    
3. Se la porta è in **LISTEN**:
    
    - segmento con **ACK** → rispondi con **RST**;
        
    - segmento con **SYN** → rispondi **SYN/ACK**;
        
    - altrimenti scartare.
        

Queste regole spiegano le differenze di risposta tra SYN, ACK, FIN, NULL, Xmas.

### **5. Tecniche di scansione TCP (pratiche)**

#### **TCP Connect Scan (`connect()` scan)**

- Usa la syscall `connect()` per completare la handshake.
    
- _Vantaggi_: non richiede privilegi root; semplice.
    
- _Svantaggi_: connessione completa e tipicamente loggata (più rumorosa).
    
- _Interpretazione_: SYN/ACK → open; RST → closed; nessuna risposta → filtered.  
    Esempio: `nmap -sT 192.168.1.10`
    

#### **TCP SYN Scan (half-open, “stealth”)**

- Invia SYN; se arriva SYN/ACK lo scanner invia RST per abortire la connessione.
    
- _Vantaggi_: più silenziosa; richiede privilegi per raw packets.
    
- _Svantaggi_: rilevabile da IDS/IPS; può lasciare tracce se loggati SYN non completati.
    
- _Interpretazione_: SYN/ACK → open (si invia RST); RST → closed; nessuna risposta → filtered.  
    Esempio: `nmap -sS 192.168.1.0/24`
    

#### **SYN vs Connect — riassunto**

- **SYN**: raw packet, half-open, meno intrusivo, richiede privilegi.
    
- **connect()**: usa stack OS, completo, più loggable, non richiede privilegi.
    

#### **Altre scansioni TCP (stealth / fingerprinting)**

- **ACK scan**: invia ACK; se arriva RST → porta _unfiltered_; utile per mappare firewall stateful.
    
- **FIN / NULL / Xmas scans**: inviano flag particolari; comportamento delle risposte può rivelare implementazioni.
    
- **Idle (zombie) scan**: tecnica avanzata e anonima che sfrutta un host “zombie” con IP ID prevedibile; lo scanner deduce lo stato osservando variazioni nell’IP ID dello zombie.
    

### **6. Scansione UDP**

#### **Meccanica e limiti**

- Invio di datagrammi UDP; possibili riscontri: ICMP port unreachable → porta chiusa; nessuna risposta → open o filtered (open|filtered).
    
- Problemi: molte risposte ICMP bloccate da firewall, time-out lunghi; più lenta rispetto a TCP.  
    Esempio: `nmap -sU -p 53,69,123 192.168.1.0/24`
    

### **7. Discovery e Version Detection**

#### **Version detection**

Dopo aver trovato una porta aperta si inviano probe applicativi per identificare la versione (banner HTTP, risposte SMTP/FTP, signature). Utile per correlare con CVE, ma più rumoroso.  
Esempio: `nmap -sV -p 22,80,443 192.168.1.10`

### **8. Timing, stealth e dimensione della scansione**

#### **Scelte operative**

- **Timing**: “fast” vs “low and slow”; aumentare delay riduce probabilità di rilevamento.
    
- **Distributed scanning**: distribuisce il carico su più sorgenti per ridurre tracce.
    
- **Wide-range vs Targeted**: /16 per mappatura grossolana; scan verticale approfondito su host critici.
    

### **9. Log, rilevazione e falsi positivi**

#### **Elementi da monitorare**

- **Log host**: `auth.log`, `syslog`, connessioni incomplete, rilevazioni SYN flood.
    
- **IDS/IPS**: signature e anomalie di comportamento.
    
- **Falsi**: ARP discovery legittima vs ARP scan malevola; correlare tempo, IP e pattern per ridurre FP/FN.
    

### **10. Contromisure e difesa attiva**

#### **Filtri e firewall**

Bloccare/limitare ICMP, applicare regole stateful, filtrare ingressi sospetti, rate limiting.

#### **IDS / IPS e correlazione**

Suricata, Snort e simili per rilevare pattern di scansione e attivare risposte.

#### **Honeypots e tarpit**

Honeypot per catturare scanner; tarpit per rallentarli con risposte lente.

#### **Hardening applicativo**

Disabilitare banner, usare port knocking, autenticazione forte, VPN per servizi sensibili.

#### **Logging e automazione**

Centralizzare log (SIEM), correlare eventi e reagire con blocchi dinamici (ipset, fail2ban).

### **11. Esempi pratici (comandi e interpretazione)**

#### **Scansione TCP SYN (nmap)**

```
nmap -sS -p 1-1024 192.168.1.0/24
```

Output: elenco porte per IP con stati `open`, `closed`, `filtered`. `filtered` suggerisce firewall.

#### **Scansione UDP (nmap)**

```
nmap -sU -p 53,69,123 192.168.1.0/24
```

Più lenta: usare su target mirati.

#### **Version detection**

```
nmap -sV -p 22,80,3306 10.0.0.5
```

Restituisce stringhe di versione (es. `OpenSSH 7.6p1`, `nginx 1.14.0`).

#### **ARP scan (arp-scan / nmap)**

```
arp-scan --localnet
# oppure
nmap -sn -PR 192.168.1.0/24
```

Elenca IP attivi con MAC: affidabile in LAN.

### **12. Riassunto Feynmaniano**

- **Idea semplice**: per scoprire un servizio invio una domanda e osservo la risposta; la natura della risposta indica se la porta è aperta, chiusa o filtrata.
    
- **Cosa cambia**: il tipo di pacchetto (SYN, ACK, FIN, NULL, UDP, ICMP) determina la risposta; firewall e implementazioni diverse introducono ambiguità.
    
- **Pratica**: combina discovery (ARP/ICMP/TCP ping) → scansione porte (SYN/connect/UDP) → version detection → analisi dei log e hardening.
    
- **Difesa**: ridurre informazioni esposte, monitorare e automatizzare risposte, usare proxy/CDN/WAF e limitare banner.