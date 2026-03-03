## **Lezione 4: Altre tecniche di scansione e contromisure**

### **1. Introduzione — obiettivo della lezione**

In questa lezione vediamo tecniche pratiche avanzate di discovery e scanning (UDP scan, FTP bounce, OS fingerprinting, traceroute, evasione firewall/IDS) e, per ciascuna, le contromisure efficaci. Approccio Feynman: partiamo dal principio, osserviamo il comportamento della rete, traduciamo in comandi reali e termini di difesa.

### **2. UDP scan**

#### **2.1 Principio e comportamento**

Nel UDP scan lo scanner invia datagrammi UDP (spesso con 0 byte di payload) alle porte target e interpreta le eventuali risposte ICMP o UDP. Poiché molti servizi UDP non rispondono se non c’è un trigger applicativo, il risultato spesso sarà **open|filtered** (assenza di risposta) piuttosto che un `open` netto. Risultati tipici:

- **Risposta UDP applicativa** → `open` (es. DNS risponde).
    
- **ICMP port unreachable** → `closed`.
    
- **Nessuna risposta** → `open|filtered`.
    

#### **2.2 Vantaggi e limiti**

Vantaggio: permette di scoprire servizi UDP (DNS, NTP, SNMP, etc.). Limite: molto più lento del TCP perché ci si basa su timeout ICMP e molte reti bloccano o limitano ICMP. Per efficienza, si inviano probe applicative (es. query DNS su porta 53) invece di datagrammi vuoti.

#### **2.3 Esempio pratico**

```
nmap -sU -p 53,123,161 192.168.1.0/24
# Per DNS: nmap --script=dns-recursion -p 53 <target> (probe applicativa)
```

#### **2.4 Contromisure**

- Limitare risposte a query non autenticate; rate-limitare UDP; disabilitare servizi non necessari; applicare filtraggio ICMP a livello perimetro.
    

---

### **3. FTP bounce scan**

#### **3.1 Principio**

L’FTP bounce sfrutta il comando `PORT` della modalità FTP attiva: un server FTP viene istruito a connettersi ad un terzo indirizzo/porta (spoof del target). Il server FTP diventa un “zombie” che prova connessioni verso la vittima; osservando l’esito, l’attaccante deduce lo stato della porta target. È analogo all’idlescan ma usa un servizio applicativo come vettore.

#### **3.2 Vantaggi e svantaggi**

- Vantaggi: stealth (la vittima vede il server FTP come sorgente), non richiede raw packets.
    
- Svantaggi: funziona solo se il server FTP non blocca `PORT` per IP diversi, è lento e lascia tracce su server FTP usati come riflettori.
    

#### **3.3 Contromisure pratiche**

- Configurare server FTP per rifiutare richieste `PORT` con IP diversi da quello del client; limitare le porte <1024; disabilitare modalità attiva se non necessaria; logging e monitor delle richieste `PORT`.
    

---

### **4. OS Fingerprinting**

#### **4.1 Scopo e principio**

L’OS fingerprinting determina il sistema operativo analizzando differenze di implementazione dello stack TCP/IP: ISN generation, valori della window, comportamento a pacchetti non standard, risposta a probe che violano o sfruttano ambiguità RFC. Si può eseguire **attivamente** (invia probe e osserva le risposte) o **passivamente** (osservando traffico reale).

#### **4.2 Tecniche e segnali tipici**

- **FIN prob**: diversi OS rispondono diversamente a FIN su porta chiusa.
    
- **FIN/SYN probing**: alcune implementazioni restituiscono flag particolari.
    
- **Bogus-flag probes**: bit TCP non usati possono essere mantenuti in risposta su vecchie versioni.
    
- **Window size e ISN sampling**: valori e algoritmi mostrano pattern OS-specific.
    
- **ICMP quoting length**: alcuni OS includono più/meno byte nel reply ICMP.
    

#### **4.3 Difese contro fingerprinting**

- **Detection**: NIDS che segnalano probe anomali.
    
- **Blocking / Normalization**: firewall che normalizzano pacchetti (strip di flags non standard).
    
- **Deception**: cambiare la “finta” fingerprint (OS fingerprint spoofing) o usare honeypots per ingannare scanner.
    

---

### **5. nmap: il Network Mapper**

#### **5.1 Funzionalità principali**

nmap è lo strumento di riferimento per port & network scanning: supporta SYN/Connect/UDP/ICMP scans, OS detection, version detection, scripting (NSE) e molte opzioni di timing/evazione. È la cassetta degli attrezzi sia per il pentester che per il sysadmin.

#### **5.2 Esempi utili**

```
nmap -sS -sV -O -p 1-1024 10.0.0.5   # SYN scan + version + OS detection
nmap -sU -p 53,123 10.0.0.0/24      # UDP scan mirato
nmap --script vuln 10.0.0.5         # lancia script NSE per vulnerabilità
```

#### **5.3 Avvertenza**

Molti IDS hanno regole per rilevare nmap; l’uso in ambienti non autorizzati è illegale.

---

### **6. Traceroute**

#### **6.1 Principio tecnico**

Traceroute ricostruisce il percorso verso una destinazione incrementando il TTL dei pacchetti e raccogliendo i messaggi ICMP “Time Exceeded” (ogni hop restituisce l’indirizzo del router che decrementa TTL a 0). Può usare ICMP, UDP o TCP (tcptraceroute) a seconda di ciò che si vuole raggiungere/aggirare.

#### **6.2 Limiti pratici**

Firewall/router possono bloccare o rate-limitare i messaggi ICMP o i pacchetti con TTL basso; per questo traceroute può terminare prima della destinazione. L’uso di TCP (port 80) a volte permette di superare filtri che bloccano UDP/ICMP.

#### **6.3 Comandi**

```
traceroute <host>            # UDP/ICMP depending on OS
traceroute -T -p 80 <host>   # TCP traceroute (on systems that support it)
tcptraceroute <host> 80
```

---

### **7. Firewall / IDS evasion & spoofing**

#### **7.1 Tecniche di evasione comuni**

- **Fragmentation**: spezzare probe in frammenti IP per ostacolare signature-based IDS che non riassemblano.
    
- **Timing (low-and-slow)**: rallentare la scansione per evitare pattern riconoscibili.
    
- **Payload obfuscation**: variare payload e flags per sfuggire a signature.
    
- **Source distribution**: usare molte sorgenti (botnet, proxy) per diluire il traffico.
    
- **Packet crafting**: inviare pacchetti non standard per provocare risposte diverse dagli stack target.
    

#### **7.2 Contromisure efficaci**

- **Stateful inspection e normalizzazione**: i firewall moderni devono riassemblare frammenti e normalizzare flussi prima di ispezionarli.
    
- **Rate limiting & anomaly detection**: limiti di connessioni per IP/prefix e IDS behaviorali (SIEM) per correlare eventi.
    
- **Honeypots / tarpits**: deviare scanner su risorse controllate e rallentarli.
    
- **Ingress filtering (BCP38)**: impedire spoofing degli indirizzi sorgente a livello ISP.
    

---

### **8. Difese generali e best practice**

#### **8.1 Prevenzione**

- Disabilitare servizi non necessari; chiudere porte non utilizzate; usare firewall stateful; applicare least privilege.
    

#### **8.2 Rilevazione**

- Deploy NIDS (Snort/Suricata), centralizzazione log (SIEM), regole per rilevare pattern di scansione (SYN floods, FIN/NULL/Xmas sequences).
    

#### **8.3 Risposta**

- Blocchi dinamici (ipset, firewall automation), throttling, engagement con honeypot per intelligence sulla fonte.
    

---

### **9. Esempi pratici rapidi**

#### **9.1 UDP scan (nmap)**

```
nmap -sU -p 53 192.168.1.0/24
```

#### **9.2 FTP bounce (storico) — test in lab**

- Non eseguire su sistemi di terzi; riprodurre su lab con server FTP vulnerabile per capire meccanica PORT→target.
    

#### **9.3 OS fingerprinting (nmap)**

```
nmap -O 10.0.0.5
```

#### **9.4 Traceroute TCP**

```
tcptraceroute example.com 80
```

---

### **10. Sintesi Feynmaniana**

- **Idea semplice:** per mappare una rete uso sonde mirate (UDP/TCP/ICMP/APP) e interpreto le risposte; le tecniche avanzate sfruttano funzionalità di servizi o debolezze di implementazione.
    
- **Cosa cambia la pratica:** UDP è rumoroso e lento, FTP bounce e idlescan permettono anonimato/reflection, fingerprinting sfrutta varianze implementative, traceroute rivela path ma può essere bloccato.
    
- **Difesa:** multilivello: ridurre superficie, normalizzare e riassemblare, rilevare pattern, bloccare spoofing e usare deception quando utile.
    

---