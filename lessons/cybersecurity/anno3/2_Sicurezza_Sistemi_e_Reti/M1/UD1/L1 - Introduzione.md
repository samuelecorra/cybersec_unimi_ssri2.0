Il primo modulo del corso ha lo scopo di **presentare il contesto generale della sicurezza informatica**, illustrandone le finalità, le aree di applicazione e il ruolo centrale che riveste nella società digitale contemporanea.  
Viene introdotta la disciplina nel suo insieme, delineando il legame tra **tecnologia, organizzazione e tutela dell’informazione**.  
L’obiettivo è fornire una visione chiara del perché la sicurezza sia oggi una **necessità strutturale** per sistemi, reti e servizi informatici.

---

# **UD1 – Concetti fondamentali**

Questa unità costituisce la base concettuale del corso.  
Si definisce il **concetto di sicurezza informatica**, analizzandone le dimensioni principali — **riservatezza, integrità e disponibilità (CIA triad)** — e si introduce il linguaggio tecnico della disciplina.  
Viene inoltre descritto il **mercato della sicurezza informatica**, con le sue caratteristiche, le tendenze e i principali attori, per comprendere come la sicurezza sia diventata un vero e proprio **settore industriale strategico**.

---

## **Lezione 1: Introduzione**

### **1. Struttura generale del corso**

==Il corso di **Sicurezza dei Sistemi e delle Reti** affronta in modo integrato i principi teorici e le pratiche operative che permettono di proteggere dati, sistemi e infrastrutture informatiche da minacce interne ed esterne.  
Si articola in **due grandi aree tematiche**:==

- **Sicurezza dei sistemi**, dedicata ai meccanismi di protezione a livello di host e sistema operativo.
    
- **Sicurezza delle reti**, dedicata alla protezione dei dati in transito e delle comunicazioni.
    

---

### **2. Sicurezza dei sistemi**

Questa parte del corso introduce i **concetti fondamentali** della sicurezza informatica:

- **Proprietà di sicurezza:** riservatezza, integrità e disponibilità (la _triade CIA_).
    
- **Standard e concetti di base:** linee guida, best practice e modelli di riferimento internazionali.
    
- **Minacce e risorse:** analisi dei rischi, degli attacchi e dei beni da proteggere.
    
- **Classificazione del malware:** virus, worm, trojan, ransomware, rootkit, botnet, APT (Advanced Persistent Threats).
    

Si affrontano inoltre i **meccanismi di protezione logica**, come:

- **Autenticazione:** metodi per verificare l’identità di un utente o di un’entità (password, token, biometria, autenticazione a più fattori).
    
- **Controllo degli accessi:** definizione e gestione delle politiche di sicurezza, con distinzione tra i modelli:
    
    - **DAC (Discretionary Access Control)** → controllo discrezionale basato sul proprietario della risorsa.
        
    - **MAC (Mandatory Access Control)** → controllo obbligatorio imposto dal sistema.
        
- **DAC e MAC nei sistemi operativi moderni:** applicazioni pratiche di questi modelli in ambienti reali (es. Linux, Windows).
    
- **Gestione delle identità:** amministrazione sicura delle credenziali e dei ruoli utente.
    

---

### **3. Sicurezza delle reti**

La seconda parte del corso analizza la sicurezza dei **protocolli di comunicazione** e delle **infrastrutture di rete**, includendo:

- **Modello ISO/OSI** e minacce associate a ciascun livello.
    
- **Vulnerabilità dei protocolli TCP/IP** e loro possibili contromisure.
    
- **Attività di ricognizione e port scanning**, con studio degli strumenti utilizzati per individuare servizi attivi e porte aperte.
    
- **Firewall e sistemi IDS (Intrusion Detection System):** tecniche di rilevamento e prevenzione delle intrusioni.
    
- **Sicurezza del livello trasporto:** protocolli **SSL/TLS** per la cifratura e l’autenticazione delle comunicazioni.
    
- **Software security:** analisi delle vulnerabilità applicative, come il **buffer overflow**.
    

---

### **4. Laboratori e strumenti**

Le attività pratiche del corso prevedono l’uso di ambienti virtuali e strumenti open source:

- **Wireshark:** analisi del traffico di rete e identificazione dei pacchetti.
    
- **Configurazione di firewall:** definizione di regole di filtraggio e politiche di sicurezza.
    
- **Attacchi in ambiente virtuale:** simulazioni di exploit e contromisure controllate.
    
- **Linux, VirtualBox e Docker:** ambienti base per la sperimentazione delle tecniche di sicurezza.
    

---

### **5. Materiali e testi di riferimento**

Tutti i materiali didattici sono disponibili sul **sito Ariel del corso**.  
Non esiste un libro di testo obbligatorio, ma i testi consigliati includono:

- _William Stallings_, **Sicurezza dei computer e delle reti** (Pearson, 2022).
    
- Articoli scientifici e dispense fornite durante le lezioni.
    

---

### **6. Modalità d’esame**

L’esame è articolato in più parti:

1. **Prova scritta:** domande teoriche e brevi esercizi a risposta aperta.
    
2. **Prova di laboratorio (open book):** esercizi pratici da svolgere utilizzando i tool trattati (es. Wireshark, firewall).
    
3. **Progettino facoltativo:** una relazione o presentazione di approfondimento, utile per migliorare il voto finale.
    

Per l’esame da **6 CFU**, il programma comprende i moduli principali del corso base.  
Per l’esame da **12 CFU**, è richiesto lo studio completo di tutti i moduli (inclusi quelli di _Sicurezza II_).