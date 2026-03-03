## **Lezione 3: IPS e strumenti avanzati**

### **1. IDS attuativi e IPS**

Un **Intrusion Prevention System (IPS)** rappresenta l’evoluzione diretta dell’**IDS**: oltre a **rilevare** un attacco, può **reagire automaticamente** per impedirne la prosecuzione.

Gli **IDS attuativi** integrano azioni automatiche (es. direttiva `react`) che possono terminare connessioni sospette o modificare la configurazione del firewall.  
Tuttavia, questa capacità deve essere gestita con attenzione, poiché **una reazione automatica errata può danneggiare il sistema stesso**.

#### **Esempio – Snort**

```bash
alert tcp $EXTERNAL_NET any -> $HTTP_SERVERS $HTTP_PORTS \
(msg:"WEB-IIS cmd.exe access"; content:"cmd.exe"; react:block;)
```

Questa regola genera un allarme quando viene rilevato un accesso al file `cmd.exe` su un server IIS e **blocca immediatamente la connessione TCP** in corso.

### **Tecniche di reazione più diffuse**

1. **Reset di sessione (Session Sniping)** – invio di pacchetti TCP `RST` per interrompere la comunicazione.
    
2. **Aggiornamento dinamico del firewall** – modifica automatica delle ACL o delle regole `iptables` per bloccare IP sorgenti sospetti.
    

---

## **2. Session Sniping**

La tecnica del **Session Sniping** consente al NIDS di **forzare la chiusura di una connessione** attiva.  
Il sistema invia pacchetti **RST** (Reset) a **entrambi gli endpoint** della comunicazione, **fingendo** che provengano dalle controparti reali.  
In questo modo, entrambi i sistemi credono che la connessione sia stata chiusa regolarmente.

> È una tecnica efficace ma delicata: un uso scorretto può interrompere connessioni lecite.

---

## **3. Riconfigurazione automatica del firewall**

Un IDS o IPS può anche **interagire direttamente con il firewall**, aggiornandone le regole in risposta a eventi sospetti.  
Esempio: se viene rilevata un’attività di _port scanning_, il sistema può **bloccare automaticamente** gli indirizzi IP coinvolti.

### **Problema**

Un attaccante può sfruttare questo comportamento inviando pacchetti con **indirizzi IP falsificati (IP spoofing)**.  
Il firewall, credendo di bloccare l’attaccante, finirebbe invece per **isolare sorgenti legittime**, causando un **denial of service involontario**.

---

## **4. Fail2ban**

**Fail2ban** è un tipico **attuativo di sicurezza** a livello applicativo.  
Monitora i log di autenticazione (es. SSH, FTP, Postfix) e, al superamento di una soglia di tentativi falliti, **aggiorna automaticamente le regole del firewall**, bannando l’IP offensivo.

### **Esempio di configurazione**

```ini
[proftpd]
enabled = true
port = ftp
filter = proftpd
logpath = /var/log/auth.log
failregex = proftpd: (pam_unix) authentication failure; .* host=<HOST>
maxretry = 5
action = iptables[name=%(name)s, port=%(port)s]
```

> Fail2ban combina analisi dei log e reazione firewall, agendo come un IPS “leggero”.

---

## **5. Evasione dagli IDS**

Gli attaccanti più esperti possono **aggirare i controlli degli IDS**, sfruttando differenze tra la logica del sistema di rilevamento e quella dell’host bersaglio.

### **Tecniche di evasione comuni**

1. **Path Obfuscation (camuffamento del percorso)**
    
    - Esempio: una regola che cerca `content:"/etc/passwd";` può essere elusa scrivendo:
        
        ```
        /etc//\//passwd
        /etc/rc.d/.././\passwd
        ```
        
    - L’IDS, non normalizzando correttamente il path, non riconosce il pattern.
        
2. **Frammentazione dei pacchetti**
    
    - Se il NIDS ha una **finestra di riassemblaggio** più piccola di quella dell’host target, può considerare due frammenti come pacchetti separati.
        
    - L’host, invece, li ricompone correttamente, **subendo l’attacco senza che l’IDS lo rilevi**.
        

> Queste tecniche sfruttano **asimmetrie di interpretazione** tra NIDS e sistema vittima.

---

## **6. Limiti e critiche agli IDS**

Nel 2003, un famoso rapporto del **Gartner Group** affermava che gli IDS **non giustificano gli investimenti richiesti**, per diverse ragioni:

- **Troppi falsi positivi e negativi** → difficile discriminare i veri attacchi.
    
- **Richiedono personale dedicato** al monitoraggio continuo (24/7).
    
- **Il processo di risposta** agli incidenti è lungo e oneroso.
    
- **Prestazioni limitate:** reti oltre i **600 MB/s** causano forti decadimenti.
    

> Queste osservazioni hanno spinto verso lo sviluppo degli **IPS**, più autonomi e reattivi.

---

## **7. Assunzioni e vulnerabilità degli IDS**

I sistemi **signature-based** si basano sull’assunto di **conoscere la forma di un attacco**.

### **Principali assunzioni**

1. **Identificare una vulnerabilità**: la firma deve rappresentare **tutti gli attacchi** che la sfruttano.
    
2. **Riconoscere un exploit**: la firma deve coprire **tutte le varianti** possibili.
    

### **Problema Zero-Day**

Un **attacco zero-day** è una minaccia **completamente nuova**, per la quale non esiste ancora una firma.  
Finché non viene identificata e codificata, l’IDS **non può riconoscerla**.

> Questa “vulnerability window” può durare anche anni, lasciando i sistemi esposti.

---

## **8. Port Knocking: concetto**

Il **Port Knocking** è una **tecnica di autenticazione a livello di rete**, progettata per **migliorare la politica di “deny all”** dei firewall.

Funziona come un **sistema di comunicazione cifrato su porte chiuse**:

- Il **firewall** è completamente chiuso.
    
- Il **server** monitora le connessioni su porte specifiche.
    
- Il **client** “bussa” inviando pacchetti TCP/UDP verso una **sequenza predeterminata di porte**.
    
- Se la sequenza (il “knock”) è corretta, il server esegue un’azione (es. apre una porta).
    

> In pratica, il firewall “riconosce chi bussa” e apre solo a chi conosce la sequenza.

---

## **9. Funzionamento del Port Knocking**

### **Fasi operative**

1. **Firewall chiuso:** nessuna porta accessibile.
    
2. **Client → Knock:** invia pacchetti con flag `SYN` verso le porte definite nella sequenza.
    
3. **Daemon (knockd)**:
    
    - intercetta la sequenza,
        
    - decripta il messaggio,
        
    - verifica la correttezza del knock.
        
4. **Azione:** se la sequenza è valida, il server:
    
    - apre la porta designata,
        
    - o esegue un comando di amministrazione (es. script o modifica firewall).
        

---

### **Esempio di scenario**

**Step 1** – Il client non può accedere alla porta di destinazione.  
**Step 2** – Invia tentativi di connessione alle porte `{7000, 8000, 9000}`.  
**Step 3** – Il daemon verifica la sequenza.  
**Step 4** – Se la sequenza è corretta, apre temporaneamente la porta 22 (SSH).

> Tutti i pacchetti “di knock” sono bloccati dal firewall, ma vengono comunque **registrati** e **interpretati** dal demone.

---

## **10. Configurazione di Knockd**

Il servizio `knockd` consente di implementare il port knocking in modo automatico, reagendo alle sequenze rilevate con comandi `iptables`.

### **Esempio – Apertura e chiusura SSH**

```ini
[options]
logfile = /var/log/knockd.log 

[openSSH]
sequence = 7000,8000,9000
seq_timeout = 10
tcpflags = syn
command = /usr/sbin/iptables -A INPUT -s %IP% -p tcp --dport 22 -j ACCEPT

[closeSSH]
sequence = 9000,8000,7000
seq_timeout = 10
tcpflags = syn
command = /usr/sbin/iptables -D INPUT -s %IP% -p tcp --dport 22 -j ACCEPT
```

### **Esempio con variante temporale**

```ini
[options]
logfile = /var/log/knockd.log 

[opencloseSSH]
sequence = 2222:udp,3333:tcp,4444:udp
seq_timeout = 15
tcpflags = syn,ack
start_command = /usr/sbin/iptables -A INPUT -s %IP% -p tcp --syn --dport 22 -j ACCEPT
cmd_timeout = 10
stop_command = /usr/sbin/iptables -D INPUT -s %IP% -p tcp --syn --dport 22 -j ACCEPT
```

> In questo caso, la porta SSH si apre per un tempo limitato, poi si richiude automaticamente.

---

## **11. Vantaggi e limiti del Port Knocking**

### **Vantaggi**

- Migliora la sicurezza nascondendo le porte aperte.
    
- Riduce la superficie d’attacco.
    
- Può essere integrato con `iptables` o sistemi di logging.
    

### **Limiti**

- Vulnerabile a intercettazioni se la sequenza non è cifrata.
    
- Soggetto a errori di sincronizzazione o spoofing.
    
- Può complicare la gestione in ambienti con NAT o firewall multipli.
    

> È un ottimo _layer_ aggiuntivo, ma non sostituisce meccanismi di autenticazione robusti.

---

## **12. Conclusione**

In questa lezione abbiamo visto come gli **IDS attuativi evolvono in IPS**, capaci di **bloccare attivamente** gli attacchi e **adattare** la propria configurazione.  
Abbiamo poi esplorato strumenti e tecniche operative come **Fail2ban** e **Port Knocking**, che rappresentano **forme pratiche di difesa adattiva**.

> L’evoluzione dalla rilevazione (IDS) alla reazione (IPS) segna il passaggio da una **sicurezza passiva** a una **sicurezza dinamica**, capace di apprendere, adattarsi e rispondere in tempo reale.

---
