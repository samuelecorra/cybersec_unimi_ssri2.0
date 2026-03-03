# **UD3 - Network e Port scanning

Questa unità introduce i principi e le pratiche per mappare una rete e identificare servizi attivi su host remoti: vedremo gli **approcci** (attivo vs passivo), le **tecniche** fondamentali di scansione (es. TCP SYN/Connect, UDP, ACK/FIN/Xmas, stealth/fragmentation), i **tool** più usati e le **contromisure** pratiche. L’obiettivo non è solo imparare come funziona lo scanning, ma comprenderne i segnali nel traffico, i limiti pratici, e come trasformare quella conoscenza in difesa — cioè rilevazione precoce, hardening e mitigazione.

In questa unità imparerai, a livello concettuale e operativo, a:

- distinguere quando una scansione è pura ricognizione (passiva) o potenzialmente dannosa (attiva);
    
- riconoscere i trade-off tra accuratezza, velocità e rumorosità dell’indagine;
    
- correlare tecniche di scansione a contromisure reali (filtri, rate-limit, IDS/IPS, honeypots, network segmentation).
    


---

## **Lezione 1: Tipi e Approcci di Scansione**

### **1. Introduzione generale**

Il **network scanning** è una delle attività più delicate — e ambigue — nel mondo della sicurezza informatica. Può essere uno **strumento di difesa** per un amministratore di rete, utile a comprendere la configurazione dei propri sistemi, oppure un **mezzo di ricognizione** per un attaccante che prepara un’intrusione.  
In ogni caso, rappresenta la fase preliminare fondamentale in qualunque attacco o audit di sicurezza: il momento in cui si **osserva la superficie esposta**.

L’obiettivo del network scanning è **raccogliere informazioni strutturate** su:

- i **sistemi operativi** presenti nella rete;
    
- i **servizi attivi** (TCP/UDP);
    
- le **configurazioni software** e gli eventuali **punti deboli**.
    

Attraverso la scansione si costruisce una mappa logica della rete, base per determinare quali vulnerabilità possono essere sfruttate o devono essere corrette.

---

### **2. Scopi e prospettive**

Il network scanning può avere finalità diverse a seconda del soggetto che lo esegue:

- **Amministratore di rete:** usa lo scanning a fini diagnostici, per inventariare host, servizi e policy di sicurezza.
    
- **Attaccante:** lo utilizza per identificare bersagli potenziali e pianificare exploit mirati.
    
- **Analista di sicurezza:** lo impiega per condurre test di penetrazione o per valutare vulnerabilità operative.
    

---

### **3. Obiettivi tecnici della scansione**

Un’attività di network e port scanning ben condotta mira a:

- **Riconoscere i servizi TCP e UDP disponibili**, e su quali **porte** sono in ascolto.
    
- Verificare **quali utenti** possono accedere a determinati servizi (es. login anonimi o autenticazione debole).
    
- Identificare eventuali **sistemi di filtraggio** o firewall interposti tra sorgente e target.
    
- **Determinare il sistema operativo (OS fingerprinting)** analizzando i pattern delle risposte IP.
    
- Rilevare servizi vulnerabili o obsoleti che possano consentire escalation.
    

> ⚠️ **Nota legale:** la scansione di sistemi senza autorizzazione costituisce un reato informatico in molti ordinamenti, incluso quello italiano (art. 615-ter c.p.). È ammessa solo su reti di proprietà o con consenso esplicito.

---

### **4. Tipologie e approcci generali**

Le modalità di scanning si classificano secondo due criteri principali: **direzione (verticale/orizzontale/ibrido)** e **metodo (attivo/passivo)**.

#### **4.1. Direzione della scansione**

- **Vertical scan:** esplora **tutte o molte porte** di un singolo host.  
    Esempio: analizzare un server per capire quali servizi sono esposti (HTTP, SSH, SMTP, ecc.).
    
- **Horizontal scan:** analizza **una sola porta** su **molti host**.  
    Esempio: cercare tutti i dispositivi della rete che espongono la porta 22 (SSH).
    
- **Ibrido:** combina entrambe le modalità, esplorando selettivamente più porte su più host.
    

#### **4.2. Natura della scansione**

- **Active scanning:** invia **pacchetti di probe** al target e osserva le risposte.  
    Può usare pacchetti **generici** (ICMP ping) o **specifici per protocollo** (es. TCP SYN, UDP echo, o handshake applicativo).
    
- **Passive scanning:** osserva **passivamente il traffico esistente** tra client e server, senza generare nuovi pacchetti.  
    È meno intrusivo ma richiede di **monitorare connessioni reali** (es. tramite mirroring o sniffing).
    

---

### **5. Active vs Passive scanning**

|Approccio|**Vantaggi**|**Svantaggi**|
|---|---|---|
|**Attivo**|- Fornisce un rapporto completo delle porte aperte.- È molto veloce.- Permette test di servizi filtrati o nascosti.|- Intrusivo e facilmente rilevabile da IDS/IPS.- Non rileva host temporaneamente inattivi.- Può generare log e allarmi.|
|**Passivo**|- Non intrusivo, invisibile ai sistemi di difesa.- Rileva attività da host temporanei.- Non consuma risorse di rete.|- Osserva solo host attivi durante la cattura.- Dipende dalla disponibilità di traffico reale.|

---

### **6. Dimensione della scansione**

Esistono diversi approcci in funzione dell’ampiezza e del ritmo dell’analisi:

- **Wide-range scanning:** scansione rapida su un ampio blocco di indirizzi IP.  
    Spesso automatizzata, con poca interazione umana. È il metodo tipico dei **worm** o degli **auto-rooter** che cercano bersagli vulnerabili per costruire botnet.
    
- **Target-specific scanning:** più **mirato e stealthy**, focalizzato su obiettivi di alto valore.
    
- **Indirect scanning:** sfrutta **terze parti** (proxy, bot, o sistemi infetti) per mascherare la sorgente reale.
    
- **Botnet scanning:** distribuisce la scansione su centinaia di nodi per ridurre le tracce.
    
- **Low and slow scanning:** invia sonde molto rarefatte nel tempo per **sfuggire ai sistemi di rilevamento**.
    

---

### **7. Metodi di scansione**

Le strategie di scansione possono essere basate su **una singola sorgente** o distribuite su più nodi.

#### **7.1. Single-source scanning**

Un solo host (scanner) effettua richieste verso più destinazioni:

- **Vertical scan:** tutte le porte di un host.
    
- **Horizontal scan:** una porta su molti host.
    
- **Strobe scan:** un insieme selezionato di porte su più host (es. solo servizi noti).
    
- **Block scan:** tutte le porte su molti host (es. 0–65535 su un range di IP).
    

#### **7.2. Distributed scanning**

La scansione è eseguita da **molteplici sistemi coordinati** (es. botnet).  
Ogni nodo sonda una porzione diversa dello spazio IP.  
Vantaggi:

- Difficile da correlare e rilevare.
    
- Diluisce il traffico per evitare alert sugli IDS.
    
- Aumenta la copertura geografica e temporale.
    

---

### **8. Sintesi finale**

|**Categoria**|**Descrizione**|**Esempio pratico**|
|---|---|---|
|**Vertical scan**|Tutte le porte di un singolo host|Analisi approfondita di un server web|
|**Horizontal scan**|Una porta su molti host|Ricerca di tutti i dispositivi SSH nella rete|
|**Active scan**|Invia probe diretti e osserva risposte|`nmap -sS 192.168.1.0/24`|
|**Passive scan**|Osserva traffico esistente|Monitoraggio tramite Wireshark|
|**Wide-range scan**|Molti indirizzi, pochi dettagli|Worm automatico|
|**Low and slow**|Sonde rarefatte e invisibili|Attacco stealth distribuito|
|**Distributed scan**|Coordinato tra più sorgenti|Scansione botnet|

---

### **9. Conclusione**

La scansione è la **fase di ricognizione** della sicurezza informatica: ciò che per un attaccante è l’occhio che cerca la breccia, per un difensore è lo strumento per chiuderla.  
Capire i **tipi e gli approcci** — attivi, passivi, verticali, orizzontali, distribuiti — permette non solo di usarli in modo etico (es. test di sicurezza autorizzati), ma anche di **riconoscerne i pattern** nel traffico e **bloccarli prima che diventino un attacco**.