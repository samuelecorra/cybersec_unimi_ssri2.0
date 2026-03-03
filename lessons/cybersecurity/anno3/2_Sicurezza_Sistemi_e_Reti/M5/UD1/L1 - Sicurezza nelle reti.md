Questo modulo introduce i **principi fondamentali della sicurezza di rete**, ossia l’insieme di tecniche, dispositivi e strategie progettate per **proteggere l’integrità, la disponibilità e la riservatezza dei dati** che transitano all’interno di una rete informatica.

Si parte dallo studio delle **componenti chiave della sicurezza**, come i meccanismi di autenticazione, controllo degli accessi e monitoraggio del traffico, per arrivare alle **architetture dei firewall** e alle principali **configurazioni di protezione perimetrale**.

L’obiettivo è comprendere **come difendere una rete** da minacce interne ed esterne, analizzando sia l’aspetto **strutturale** (hardware e topologia) sia quello **logico** (politiche di filtraggio e gestione del traffico).


---

# **UD1 – Firewall**

### **Introduzione**

In questa unità si approfondisce il concetto di **firewall**, uno dei dispositivi più importanti per la **protezione delle reti informatiche**.  
Il firewall agisce come **barriera di sicurezza** tra una rete interna e l’esterno, controllando e filtrando tutto il traffico in base a **regole definite**.

L’unità illustra le **principali architetture di firewall**, i loro **meccanismi di funzionamento** e i **criteri fondamentali di configurazione**, indispensabili per progettare politiche di difesa efficaci contro accessi non autorizzati e attacchi di rete.


---

## **Lezione 1: Sicurezza nelle reti**

### **1. Introduzione**

La sicurezza delle reti informatiche rappresenta oggi una delle priorità assolute nel panorama tecnologico globale.  
Con l’aumento esponenziale di dispositivi connessi, flussi di dati e servizi digitali, le **superfici di attacco** sono diventate **più ampie, dinamiche e difficili da controllare**.

Per questo motivo, prima di introdurre strumenti tecnici come i **firewall**, è fondamentale comprendere i **concetti base della sicurezza di rete**: minacce, vulnerabilità, attacchi e vettori di attacco.

---

### **2. Concetti fondamentali**

#### **Minaccia (Threat)**

È la **causa potenziale di un incidente di sicurezza**.  
Rappresenta tutto ciò che può compromettere la riservatezza, l’integrità o la disponibilità dei dati.  
**Esempio:** perdita di informazioni sensibili a causa di un malware o di un errore umano.

#### **Vulnerabilità (Vulnerability)**

È una **debolezza** in un sistema informatico, in un’applicazione o in una procedura che **può essere sfruttata** da una minaccia.  
**Esempio:** un controllo d’accesso errato o una configurazione di default non sicura.

Le vulnerabilità note sono catalogate pubblicamente nel database **CVE (Common Vulnerabilities and Exposures)**, mantenuto da MITRE, per permettere agli esperti di sicurezza di monitorarle e correggerle.

#### **Attacco (Attack)**

È l’**azione concreta** con cui una minaccia sfrutta una vulnerabilità.  
Un attacco è quindi la **materializzazione della minaccia**.

#### **Vettore di attacco (Attack Vector)**

È il **percorso o metodo** attraverso cui l’attacco viene condotto.  
Può includere:

- e-mail di phishing,
    
- virus,
    
- vulnerabilità di rete,
    
- accessi fisici non autorizzati.
    

> In sintesi: **minaccia → vulnerabilità → attacco**, e il **vettore** è il canale che rende l’attacco possibile.

---

### **3. Scenario attuale**

Viviamo in uno scenario informatico caratterizzato da **estrema complessità** e **continua evoluzione**:

- Le reti moderne hanno **perimetri difficili da definire**: cloud, mobile, edge computing e IoT hanno reso la difesa tradizionale (basata sul “confine”) meno efficace.
    
- Le **superfici di attacco** cambiano rapidamente, seguendo l’evoluzione dei sistemi e dei servizi.
    
- Ogni giorno vengono prodotti **oltre 4 quintilioni di byte di dati (4×10¹⁸)**.
    
- Il numero di dispositivi connessi (**Internet of Things**) cresce costantemente: nel 2024 si stimano **oltre 18,8 miliardi di dispositivi**.
    

Parallelamente, le richieste di rete devono garantire **bassa latenza** e **alta disponibilità**, mentre i sistemi di business si basano su **Big Data** e **Intelligenza Artificiale** — due tecnologie che ampliano sia le opportunità sia i rischi.

---

### **4. L’urgenza della cybersecurity**

Secondo il **Consiglio Europeo**, la **cybersecurity è oggi la seconda emergenza per l’Europa**, subito dopo i cambiamenti climatici e prima dei flussi migratori.

Uno dei problemi principali è la **difficoltà di misurare la sicurezza**: determinare la relazione causa-effetto tra vulnerabilità, minacce e incidenti è una delle sfide più complesse per le organizzazioni moderne.

Il **costo stimato degli attacchi informatici globali** raggiungerà i **10,5 trilioni di dollari nel 2025**, superando i danni economici prodotti da tutte le forme di criminalità tradizionale messe insieme.

---

### **5. Statistiche sugli attacchi**

Le statistiche più recenti mostrano un panorama preoccupante:

- Avviene **un attacco informatico ogni 39 secondi**, soprattutto contro **grandi aziende e infrastrutture nazionali**.
    
- Il **43%** degli attacchi colpisce **piccole e medie imprese (PMI)**, spesso meno protette.
    
- Gli attacchi all’**IoT** sono in crescita del **13% annuo**.
    
- Il numero di **data breach** (violazioni di dati) continua ad aumentare: tra il 2013 e il 2019 si sono registrati **quasi 3,8 milioni di record violati ogni giorno**.
    

> Ogni record perso o rubato rappresenta un potenziale rischio di danni economici, reputazionali e legali per le organizzazioni.

---

### **6. Security breaches e perdita di dati**

Uno dei principali fattori che contribuiscono agli incidenti di sicurezza è la **configurazione errata dei sistemi**.

Secondo il **SoC Radar Report (2023)**:

- il **35%** degli incidenti di sicurezza deriva da **errori di configurazione**,
    
- seguiti da:
    
    - **errori umani**,
        
    - **mancanza di competenze**,
        
    - **uso di configurazioni di default**,
        
    - e **linee guida errate**.
        

> Gli errori di configurazione, spesso banali, restano tra le cause principali dei _security breaches_.

Per questo motivo, le statistiche di settore sono strumenti fondamentali per i **professionisti della sicurezza**, che possono individuare trend e prevenire vulnerabilità ricorrenti.

---

### **7. Lezioni apprese e importanza della configurazione**

Gli strumenti di protezione, per quanto sofisticati, **non sono efficaci se non vengono configurati e gestiti correttamente**.

Tre principi emergono chiaramente:

1. È più importante **sapere scegliere e configurare** gli strumenti che possederli.
    
2. Serve una **visione complessiva delle minacce** (non solo tecniche, ma anche umane, come nel caso dei _malicious insider_).
    
3. Occorre investire nelle **competenze delle persone** e nella **formazione continua** per ridurre gli errori.
    

> La sicurezza non è solo tecnologia, ma soprattutto **organizzazione, competenza e consapevolezza**.

---

### **8. La complessità della sicurezza moderna**

Secondo un report Cisco, **quasi la metà dei rischi di sicurezza deriva dall’uso di troppi prodotti e vendor diversi**, spesso non integrati tra loro.

Questo causa:

- sovrapposizioni di funzioni,
    
- incoerenza delle policy di sicurezza,
    
- e maggiore difficoltà di gestione.
    

Per questo motivo si promuove l’approccio:

- **Security by design** → integrare la sicurezza sin dalla progettazione del sistema.
    
- **Privacy by design** → proteggere i dati personali fin dalla nascita del processo informatico.
    

Infine, occorre riconoscere che:

- gli attacchi sono **dinamici** e si evolvono rapidamente,
    
- mentre i tool e gli assessment di sicurezza sono spesso **statici** e non aggiornati in tempo reale.
    

---

### **9. Conclusione: dal concetto alla pratica**

La lezione si conclude sottolineando che la sicurezza di rete richiede un approccio **sistemico e consapevole**.  
Non basta installare strumenti di difesa, bisogna **saperli configurare e integrare** in un ecosistema coerente e aggiornato.

Come primo passo concreto nello studio della protezione delle reti, si introduce ora **uno degli strumenti più classici e fondamentali della sicurezza perimetrale: il firewall**.