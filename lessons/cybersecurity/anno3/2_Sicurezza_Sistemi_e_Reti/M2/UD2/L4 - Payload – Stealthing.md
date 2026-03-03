## **Lezione 4: Payload – Stealthing**

### **1. Introduzione**

Il **payload** rappresenta la **parte operativa del malware**, ovvero il codice che realizza l’effetto dannoso o l’obiettivo dell’attacco.  
In questa lezione analizziamo le forme di payload più pericolose, progettate per **mantenere il controllo del sistema** e **occultare la propria presenza**:

- **Backdoor**
    
- **Rootkit**
    
- **Zombie** e **Botnet**
    

---

### **2. Backdoor (Trapdoor)**

Una **backdoor** è un **punto di accesso segreto** all’interno di un sistema informatico, che consente di **bypassare le normali procedure di autenticazione**.

#### **Caratteristiche principali**

- Permette l’accesso remoto **senza credenziali valide**.
    
- Può essere:
    
    - Un **servizio di rete** in ascolto su una **porta non standard**.
        
    - Una **funzione nascosta** in un software legittimo.
        
- Spesso viene inserita **dagli sviluppatori stessi** per test o manutenzione.
    
- Può essere **inclusa nei compilatori** (come nel famoso caso del “Trusting Trust Attack” di Ken Thompson).
    

#### **Esempio: Worm MyDoom**

- Crea una backdoor sulla porta **TCP 3127**, consentendo **controllo remoto** del PC infetto.
    
- Avvia il processo **SHIMGAPI.DLL** come figlio di _Windows Explorer_.
    
- La stessa backdoor era utilizzata anche dal worm **Mimail**.
    

💡 **Nota:** una volta installata, la backdoor garantisce all’attaccante gli **stessi privilegi dell’utente** compromesso, permettendo pieno accesso al sistema.

---

### **3. Rootkit**

Un **rootkit** è un insieme di **strumenti software installati per mantenere un accesso nascosto e privilegiato** a un sistema compromesso.  
Il suo scopo è **occultare la presenza dell’attaccante** e **alterare i meccanismi di monitoraggio**.

#### **Funzionalità tipiche**

- Nasconde processi, file e chiavi di registro.
    
- Intercetta o modifica le chiamate di sistema.
    
- Elude antivirus e strumenti di logging.
    
- Fornisce **controllo totale** del sistema con privilegi _root_ o _administrator_.
    

#### **Tipologie di rootkit**

|**Tipo**|**Descrizione**|
|---|---|
|**Persistente**|Si riattiva automaticamente a ogni avvio del sistema.|
|**Residente in memoria**|Non scrive su disco, resta attivo finché il sistema è acceso.|
|**Modalità utente (User-mode)**|Intercetta le chiamate alle API di sistema.|
|**Modalità kernel (Kernel-mode)**|Modifica direttamente le funzioni del kernel, risultando quasi invisibile.|
|**Basato su macchina virtuale (VM-based)**|Inserisce un monitor tra hardware e sistema operativo, controllando tutto ciò che accade nella VM.|
|**Modalità esterna**|Opera al di fuori del sistema operativo, ad esempio nel BIOS o nel firmware di gestione, con accesso diretto all’hardware.|

#### **Evoluzione**

- **Rootkit semplici:** modificano programmi utente (es. `ls`, `ps`); rilevabili con strumenti come **Tripwire**.
    
- **Rootkit avanzati:** operano a livello kernel o firmware, **difficilmente rilevabili** dallo spazio utente.
    

---

### **4. Zombie e Botnet**

Un **zombie** è un computer **controllato da remoto** senza il consenso dell’utente, solitamente tramite **backdoor o trojan**.  
Un insieme di zombie interconnessi forma una **botnet** (_robot network_).

#### **Botnet**

- È una **rete di macchine compromesse**, coordinate da un **server di comando e controllo (C&C)**.
    
- Ogni bot esegue istruzioni ricevute dal server, senza che l’utente se ne accorga.
    
- Le botnet vengono spesso create usando worm o trojan come agenti di infezione.
    

#### **Scopi principali di una botnet**

- Attacchi **DDoS (Distributed Denial of Service)**.
    
- Invio massivo di **spam** o **phishing**.
    
- **Furto di dati**, credenziali o numeri di carta di credito.
    
- **Sniffing del traffico di rete** e keylogging.
    
- Installazione di ulteriori **malware o adware**.
    
- Manipolazione di **sondaggi, votazioni o giochi online**.
    
- Diffusione di nuovo codice malevolo.
    

---

### **5. Creazione e utilizzo di una botnet**

#### **Fasi principali del processo**

##### **1. Scansione**

L’attaccante analizza Internet alla ricerca di **sistemi vulnerabili o non protetti**.

##### **2. Infezione**

Installa in modo silenzioso un **agente zombie**, trasformando i computer vulnerabili in bot.

##### **3. Connessione al server di comando**

Gli zombie infetti si connettono a un **server master** o a una **rete peer-to-peer** per ricevere istruzioni.

##### **4. Attacco coordinato**

L’attaccante invia comandi al server C&C per lanciare, ad esempio, un attacco DDoS verso un obiettivo specifico.

##### **5. Lancio dell’attacco**

Il server comanda simultaneamente tutti gli zombie, che **intasano il sistema bersaglio con richieste**.  
Il risultato è la **saturazione delle risorse**, rendendo il servizio inaccessibile agli utenti legittimi.

##### **6. Effetto finale**

Il sistema target è **sovraccarico** e non può rispondere alle richieste normali, causando un’interruzione del servizio.

---

### **6. Caso studio: Storm Botnet**

La **Storm Botnet**, scoperta nel **gennaio 2007**, è stata una delle più grandi e resilienti mai osservate.

#### **Caratteristiche principali**

- **Vettori di diffusione:**
    
    - allegati e-mail malevoli,
        
    - falsi link (“scarica il video”),
        
    - exploit _drive-by download_ (installazione automatica dal browser).
        
- **Comunicazione tra bot:**
    
    - sistema **peer-to-peer** decentralizzato per contattare il server di comando.
        
    - assenza di un singolo punto di controllo (difficile da disattivare).
        
- **Obiettivi:**
    
    - attacchi DDoS contro siti antispam e server di sicurezza,
        
    - diffusione di spam e phishing,
        
    - occultamento tramite aggiornamenti automatici dei nodi.
        

---

### **7. Conclusione**

Le tecniche di **stealthing** — come backdoor, rootkit e botnet — rappresentano la forma più sofisticata di payload, perché uniscono **persistenza, occultamento e controllo remoto**.  
Questi meccanismi sono alla base degli attacchi **APT (Advanced Persistent Threat)**, in cui il malware rimane nascosto per mesi, raccogliendo informazioni o manipolando il sistema senza essere rilevato.

> La vera forza di un attacco non sta solo nel danno immediato, ma nella **capacità di restare invisibile**.  
> Un sistema compromesso ma “silenzioso” è il peggior scenario possibile in sicurezza informatica.

---

