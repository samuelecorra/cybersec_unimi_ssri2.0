## **Lezione 3: Worm**

### **1. Definizione di worm**

Un **worm** è un programma capace di **replicarsi autonomamente** e di **diffondersi attivamente** verso altri sistemi, senza richiedere alcuna azione diretta da parte dell’utente.  
Ogni macchina infetta diventa una **“rampa di lancio”** per ulteriori infezioni.

#### **Caratteristiche principali**

- Sfrutta **vulnerabilità software** nei programmi client o server.
    
- Si diffonde tramite:
    
    - **Connessioni di rete** tra sistemi (TCP/IP, SMB, HTTP, ecc.).
        
    - **Supporti rimovibili** (USB, CD, DVD, dischi condivisi).
        
    - **Posta elettronica** e servizi di **messaggistica istantanea**, allegando script o macro malevoli.
        
- Dopo l’attivazione, il worm si **replica automaticamente** e può diffondere nuovamente copie di sé.
    
- Spesso include un **payload**, cioè un codice dannoso o funzionale aggiuntivo.
    

🧠 **Nota storica:** la prima implementazione di un worm fu sviluppata nei laboratori **Xerox Palo Alto** nei primi anni ’80.

Come ogni altro malware, il worm accede a **tutte le risorse accessibili all’utente infettato**.

---

### **2. Propagazione dei worm**

I worm possono diffondersi in diversi modi, a seconda del mezzo e delle vulnerabilità disponibili.

#### **a. Posta elettronica e messaggistica**

- Invia automaticamente copie di sé stesso come **allegati e-mail** o **file di chat**.
    
- Usa le **rubriche** o le **liste di contatti** della vittima per ampliare rapidamente il contagio.
    

#### **b. Condivisione di file**

- Crea copie di sé stesso su **supporti condivisi o rimovibili**, come pendrive o dischi di rete.
    
- In alcuni casi, infetta anche file già presenti.
    

#### **c. Esecuzione remota**

- Esegue una copia di sé su un sistema remoto, sfruttando **vulnerabilità di rete** o **servizi esposti**.
    

#### **d. Trasferimento file remoto**

- Utilizza protocolli come FTP, SMB o SSH per copiare sé stesso su altri host.
    

#### **e. Accesso remoto**

- Si connette come utente legittimo a un sistema remoto (es. tramite password rubate o chiavi SSH) e si replica eseguendo comandi locali.
    

---

### **3. Il caso storico: il Worm di Morris (1988)**

Il **Morris Worm**, creato da **Robert T. Morris** nel 1988, è considerato il **primo worm diffuso su larga scala**.  
Fu progettato per infettare i sistemi **UNIX** connessi a Internet e sfruttava **più vettori di infezione simultanei**.

#### **Funzionamento**

- Gli attacchi riusciti consentivano al worm di inviare comandi al sistema operativo della vittima.
    
- Una volta ottenuto l’accesso, il worm inviava un piccolo programma “bootstrap” al sistema bersaglio, che scaricava ed eseguiva il codice principale per continuare la diffusione.
    

---

### **4. Struttura del worm di Morris**

Il worm era composto da due parti principali:

1. **Programma di diffusione**
    
    - Cercava altre macchine vulnerabili.
        
    - Tentava di infiltrarsi in esse sfruttando vulnerabilità note.
        
2. **Programma vettore (99 righe in linguaggio C)**
    
    - Veniva compilato direttamente sulle macchine infette.
        
    - Trasferiva il codice principale per continuare l’infezione.
        

#### **Meccanismi di ingresso**

- **Buffer overflow** nel servizio `fingerd`.
    
- Esecuzione di **shell (`sh`) con privilegi di root**.
    
- Sfruttamento della **modalità debug di Sendmail**.
    

---

### **5. Vettore: fingerd**

- Il comando `finger` serviva per ottenere informazioni sugli utenti attivi su una macchina UNIX.
    
- Un **demone finger** ascoltava sulla **porta TCP 79** e restituiva le informazioni richieste.
    
- Il worm inviava una **stringa più lunga di 512 byte**, sovrascrivendo il buffer interno e alterando l’indirizzo di ritorno della funzione.  
    → Questo permetteva di **eseguire codice arbitrario** sulla macchina remota (classico _buffer overflow_).
    

---

### **6. Vettore: sendmail**

`Sendmail` era (ed è tuttora) il programma standard per la gestione della posta su UNIX.

- In ascolto sulla **porta 25 (SMTP)**.
    
- Nel 1988, la **modalità debug** era attiva di default.
    
- Con il comando SMTP `DEBUG`, un utente remoto poteva **inviare uno script di shell da eseguire sul server**.
    

Il worm sfruttava questa funzionalità per **iniettare codice malevolo** e ottenere controllo completo sul sistema bersaglio.

---

### **7. Vettore: rsh e attacchi alle password**

Il worm tentava anche di **entrare nei sistemi remoti** utilizzando i file di fiducia e le credenziali utente:

- Leggeva i file:
    
    - `/etc/hosts.equiv`
        
    - `~/.rhosts`
        
- Tentava connessioni con `rsh` verso host fidati, supponendo che la **relazione di trust** fosse reciproca.
    
- Provava a violare **account locali**:
    
    - senza password,
        
    - con password deboli,
        
    - tramite **attacco a dizionario** (utilizzava un proprio dizionario di 432 parole).
        

Se trovava la password, il worm usava i file `.forward` e `.rhosts` per propagarsi automaticamente ad altri sistemi.

---

### **8. Altri esempi di worm famosi**

|**Nome**|**Anno**|**Caratteristiche principali**|
|---|---|---|
|**Nimda**|2001|Usava cinque diversi vettori di infezione, inclusi file condivisi e backdoor di altri worm.|
|**SQL Slammer**|2003|Sfruttava una vulnerabilità in Microsoft SQL Server; infettò **75.000 host in meno di 10 minuti**.|
|**Conficker**|2008–2009|Colpì da **9 a 15 milioni di computer**, sfruttando servizi Windows (IIS e SMB). Si auto-aggiornava e installava componenti di spam e scareware.|

---

### **9. Tecnologia dei worm moderni**

I worm contemporanei presentano caratteristiche evolute:

- **Multipiattaforma:** colpiscono sistemi diversi (Windows, Linux, macOS, mobile).
    
- **Multi-exploit:** utilizzano contemporaneamente più vulnerabilità note.
    
- **Diffusione ultraveloce:** propagazione in pochi minuti attraverso reti globali.
    
- **Polimorfismo:** ogni copia genera codice cifrato o offuscato diverso.
    
- **Metamorfismo:** riscrive completamente il proprio codice a ogni replica, variando struttura e comportamento per eludere i sistemi di difesa.
    

---

### **10. Worm per dispositivi mobili**

I primi worm mobili colpirono telefoni con sistema **Symbian**:

|**Nome**|**Anno**|**Caratteristiche**|
|---|---|---|
|**CommWarrior**|2004|Si replicava via **Bluetooth** e **MMS**, inviando copie di sé ai contatti in rubrica.|
|**Lasco**|2005|Sfruttava file di installazione `.sis` per infettare altri programmi.|

#### **Funzionamento tipico**

- Si diffonde tramite Bluetooth ai telefoni vicini.
    
- Si invia come file MMS ai contatti o nelle risposte automatiche.
    
- Copia sé stesso sulla **memory card**, inserendosi nei file di installazione del telefono.
    

Oggi, la maggior parte dei malware mobile si diffonde come **trojan app**, ossia applicazioni apparentemente legittime che installano codice malevolo.

---

### **11. Sintesi finale**

In questa lezione abbiamo visto:

- la **definizione e il funzionamento dei worm**,
    
- i principali **meccanismi di propagazione**,
    
- il **caso storico del Morris Worm**,
    
- e l’evoluzione verso **worm polimorfici, metamorfi e mobili**.
    

> I worm rappresentano una delle minacce più pericolose perché combinano **automazione, velocità e capacità di rete**.  
> La loro efficacia deriva dalla completa assenza di interazione umana nel processo di diffusione.


---