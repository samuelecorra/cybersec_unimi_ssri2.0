In questa lezione vedremo un approfondimento relativo al posizionamento applicativo degli standard presentati nelle lezioni precedenti. Per fare questo introdurremo le ultime revisioni e pubblicazioni dei framework in esame.

---
# Posizionamento dei Framework

![Pasted image 20250913142730.png](../../imgs/Pasted%20image%2020250913142730.png)

### 1. ISO 27K (ISO/IEC 27001 e 27002) e NIST CSF

- **Livello di applicazione**: dal **manageriale (CEO, CISO, CIO, CPO)** fino al livello tecnico.
    
- **Focus**: protezione dei dati e gestione della sicurezza delle informazioni.
    
- **Limite**: non hanno riferimenti specifici ai sistemi di impianto (OT, Operational Technology).
    
- 👉 Sono ideali per la **governance della sicurezza** e per definire policy, processi e controlli nell’ambito IT.
    

---

### 2. IEC 62443

- **Livello di applicazione**: copre l’intera catena, dal **top management** fino agli **impianti industriali**.
    
- **Focus**: non solo protezione dei dati, ma soprattutto **business continuity** e **sicurezza fisica** degli impianti.
    
- 👉 È lo standard di riferimento quando si parla di **cybersecurity industriale**, quindi di SCADA, PLC, smart meters e in generale sistemi OT.
    

---

### 3. Differenza di paradigma

- **ISO/NIST**: paradigma “data-centric” → tutto gira attorno alla protezione del dato.
    
- **IEC 62443**: paradigma “plant-centric” → proteggere l’operatività degli impianti e garantire che non ci siano interruzioni o incidenti fisici.
    

---

### 4. Evoluzione dei controlli negli standard

- Le ultime revisioni hanno ampliato il perimetro:
    
    - **NIST SP 800-53 Rev. 5** → ha introdotto controlli aggiornati per cloud, supply chain, privacy, sicurezza OT/IoT.
        
    - **ISO/IEC 27002:2022** → ha rivisto e riorganizzato i controlli in 4 macro-categorie (organizzativi, persone, fisici, tecnologici), migliorandone applicabilità e chiarezza.
        
- 👉 L’immagine in basso mostra la sovrapposizione: NIST e ISO hanno aree comuni e più ampie rispetto al passato, ma IEC 62443 rimane indispensabile per gli impianti OT.
    

---

📌 **Sintesi Feynman**:

- Immagina che l’azienda sia una città.
    
    - **ISO/NIST** sono come le regole del municipio → decidono come proteggere gli archivi, i documenti, i flussi economici.
        
    - **IEC 62443** invece è come il corpo dei vigili del fuoco e della protezione civile → si occupa che gli impianti industriali (centrali idriche, elettriche, reti) continuino a funzionare in sicurezza.
        
- Tutti servono, ma ognuno ha un campo specifico.


---
## NIST SP 800-53 Rev. 5 – Controlli di Sicurezza e Privacy

La **NIST (National Institute of Standards and Technology)** ha pubblicato questa serie di linee guida per fornire un catalogo di **controlli di sicurezza e privacy** applicabili a sistemi informativi e organizzazioni.

- **Prima edizione (2005)**: “Recommended Security Controls for Federal Information Systems”.
    
- **Revisione 4 (2013, aggiornata nel 2015)**: ha segnato un grande salto di qualità introducendo le **17 famiglie di controlli**.
    
- **Revisione 5 (2020)**: ha ampliato ulteriormente la copertura, includendo esplicitamente **sicurezza della supply chain**, **privacy**, **IoT/OT**, **cloud** e **resilienza organizzativa**.
    

---

### Le 17 famiglie di controllo (Rev. 4 → base di Rev. 5)

1. **AC – Access Control**: regole e meccanismi per limitare l’accesso a dati e sistemi solo agli utenti autorizzati.
    
2. **AT – Awareness and Training**: programmi di formazione e sensibilizzazione del personale.
    
3. **AU – Audit and Accountability**: registrazione e monitoraggio delle attività per garantire tracciabilità.
    
4. **CA – Security Assessment and Authorization**: valutazione e autorizzazione dei sistemi prima della messa in esercizio.
    
5. **CM – Configuration Management**: gestione sicura delle configurazioni hardware e software.
    
6. **CP – Contingency Planning**: piani di continuità operativa e disaster recovery.
    
7. **IA – Identification and Authentication**: sistemi per identificare e autenticare utenti e dispositivi.
    
8. **IR – Incident Response**: gestione strutturata degli incidenti di sicurezza.
    
9. **MA – Maintenance**: gestione sicura della manutenzione dei sistemi.
    
10. **MP – Media Protection**: protezione dei supporti di memorizzazione (fisici e digitali).
    
11. **PE – Physical and Environmental Protection**: sicurezza fisica dei locali e delle infrastrutture.
    
12. **PL – Planning**: pianificazione delle misure di sicurezza all’interno dei progetti.
    
13. **PS – Personnel Security**: controlli relativi al personale (screening, gestione insider, separazione ruoli).
    
14. **RA – Risk Assessment**: valutazione sistematica dei rischi.
    
15. **SA – System and Services Acquisition**: requisiti di sicurezza nei processi di acquisizione di sistemi e servizi.
    
16. **SC – System and Communications Protection**: protezione dei dati in transito e delle comunicazioni.
    
17. **SI – System and Information Integrity**: protezione contro corruzione dei dati, malware e vulnerabilità note.
    

---

### Confronto con ISO/IEC 27001

- Entrambi gli standard prevedono **controlli simili**.
    
- **ISO/IEC 27001**: controlli organizzati in Annex A (controlli meno granulari, più alti a livello di governance).
    
- **NIST 800-53**: controlli molto più dettagliati e **operativi**, ideali per ambienti complessi e federali.
    
- 👉 In pratica: ISO dà la “cornice” del sistema di gestione (SGSI), NIST ti fornisce il “manuale tecnico” dei controlli da attuare.
    

---

📌 **Sintesi Feynman**:  
Pensa a ISO 27001 come al **manuale del direttore d’orchestra**, che dice come deve suonare l’intera sinfonia della sicurezza.  
La NIST SP 800-53, invece, è come lo **spartito dettagliato di ogni singolo strumento**: ti dice nota per nota cosa fare, con controlli estremamente precisi.

---
# ISO/IEC 27002:2022 – Novità principali

### 🔹 Nuovo titolo

- **“Information security, cybersecurity and privacy protection — Information security controls”**  
    (in italiano: _Sicurezza delle informazioni, cybersecurity e protezione della privacy – Controlli di sicurezza_).
    
- Questo cambiamento riflette subito il focus ampliato: non solo sicurezza delle informazioni, ma anche **cybersecurity** e **privacy**.
    

---

### 🔹 Nuova tassonomia dei controlli

- La vecchia versione (2013) suddivideva i controlli in **14 domini** (es. sicurezza fisica, gestione accessi, crittografia, ecc.).
    
- La versione 2022 li ha **riclassificati in 4 grandi gruppi**:
    

1. **People (Persone)** → 8 controlli (es. formazione, responsabilità, background check).
    
2. **Physical (Fisico)** → 14 controlli (es. accessi fisici, protezione attrezzature, ambienti sicuri).
    
3. **Technological (Tecnologico)** → 34 controlli (es. crittografia, backup, logging, protezione endpoint).
    
4. **Organizational (Organizzativo)** → 37 controlli (es. policy, gestione fornitori, gestione incidenti, business continuity).
    

➡️ Totale controlli: **93** (contro i 114 precedenti).  
Molti sono stati fusi o resi più generali, altri completamente nuovi (ad esempio: _Threat Intelligence_, _Cloud Services Security_, _Data Leakage Prevention_).

---

### 🔹 Introduzione degli _Attributi_

Ogni controllo ora può essere “etichettato” secondo diverse dimensioni, così da poter essere selezionato e applicato in base alle esigenze dell’azienda:

1. **Control type** → Preventivo, Detective, Correttivo.
    
2. **Information security properties** → Riservatezza, Integrità, Disponibilità (triade CIA).
    
3. **Cybersecurity concepts** → _Identify, Protect, Detect, Respond, Recover_ (mappatura con il NIST Cybersecurity Framework).
    
4. **Operational capabilities** → Capacità operative che il controllo abilita.
    
5. **Security domain** → Ambito di appartenenza (es. governance, difesa tecnica, resilienza).
    

➡️ Questa classificazione è pensata per rendere lo standard **più flessibile e navigabile** a seconda del tipo di interlocutore (CISO, tecnico, auditor, legale, ecc.).

---

### 🔹 Nuovi controlli notevoli

Alcuni esempi di **nuove introduzioni nella 27002:2022**:

- **Intelligence sulle minacce (Threat Intelligence)**
    
- **Monitoraggio della sicurezza (Security Monitoring)**
    
- **Sicurezza per i servizi cloud (Cloud Services Security)**
    
- **Prevenzione della perdita di dati (Data Leakage Prevention – DLP)**
    
- **Gestione della configurazione sicura (Secure Configuration Management)**
    
- **Sicurezza delle informazioni durante il ciclo di vita (Information Deletion, Data Masking)**
    

---

### 🔹 Relazione con ISO/IEC 27001

- La **ISO 27002** è una **linea guida**: descrive i controlli in dettaglio.
    
- La **ISO 27001** rimane lo **standard certificabile**: stabilisce i requisiti del Sistema di Gestione della Sicurezza delle Informazioni (SGSI).
    
- La revisione della 27002 ha spinto l’ISO a rivedere anche la **27001** (attesa fine 2022, infatti è uscita in ottobre 2022 la nuova edizione).
    

---

📌 **Sintesi Feynman**:

- Prima (2013) avevi 14 cassetti diversi, ciascuno con un gruppo di controlli.
    
- Ora (2022) hai 4 scatole più grandi: **Persone, Fisico, Tecnologia, Organizzazione**, e dentro ci sono i controlli ridotti a 93, meglio organizzati e aggiornati.
    
- In più ogni controllo porta un’etichetta (tipo codice a barre) che ti dice se è preventivo, detective o correttivo, se tutela riservatezza/integrità/disponibilità, e a quale fase del modello NIST appartiene (_Identify, Protect, Detect, Respond, Recover_).


---
## 🔹 ISA-95 (Purdue Model)

![Pasted image 20250913143214.png](../../imgs/Pasted%20image%2020250913143214.png)

Lo standard **ISA-95** (detto anche _Purdue Model_) è una struttura di riferimento internazionale che divide un impianto industriale in **5 livelli (0–4)**, ciascuno corrispondente a un diverso ambito tecnologico e gestionale:

- **Livello 0 – Processi fisici**  
    → Sensori e segnali che rilevano lo stato del processo reale (pressioni, temperature, flussi, ecc.).
    
- **Livello 1 – Controllo di base**  
    → PLC (Programmable Logic Controllers), sistemi elettronici che ricevono segnali dai sensori e attuano comandi sui processi.
    
- **Livello 2 – Supervisione e controllo**  
    → SCADA (Supervisory Control and Data Acquisition), HMI (Human Machine Interface). Qui avviene la supervisione e il controllo centralizzato del processo.
    
- **Livello 3 – Gestione della produzione**  
    → MES (Manufacturing Execution System). Si occupa di pianificazione operativa: gestione flussi di lavoro, scheduling, reportistica di produzione.
    
- **Livello 4 – Pianificazione aziendale**  
    → ERP/Gestionale. Pianificazione risorse aziendali (logistica, contabilità, e-procurement, fatturazione).
    

---

## 🔹 ISO 27001 / NIST

- Coprono i **livelli 3 e 4**, e parzialmente il livello 2.
    
- **Obiettivo principale**: protezione dei **dati e delle informazioni** nei sistemi computerizzati.
    
- Quindi la loro efficacia è legata ai sistemi **con sistemi operativi standard** (server, database, software gestionali).
    
- Si concentrano sulla **riservatezza, integrità, disponibilità (CIA)** delle informazioni e sulla gestione dei rischi informatici.
    

---

## 🔹 IEC 62443

- È lo standard di riferimento per la **sicurezza dei sistemi industriali (OT – Operational Technology)**.
    
- Copre **dal livello 0 al livello 3**, arrivando fino ai **sensori e ai PLC**, cioè dispositivi che di norma non rientrano nei confini tradizionali della cybersecurity IT.
    
- **Obiettivo principale**:
    
    - garantire la **continuità operativa** degli impianti,
        
    - proteggere le **persone e i processi fisici**,
        
    - gestire la sicurezza in ambienti **non standard** (macchine industriali, reti OT, SCADA).
        

---

## 🔹 Sovrapposizioni e differenze

Il grafico evidenzia due aree di **sovrapposizione** (livelli 2–3):

- **ISO 27001 / NIST** → approccio orientato alla **protezione dei dati e delle informazioni**.
    
- **IEC 62443** → approccio orientato alla **sicurezza fisica e alla continuità dei processi**.
    

➡️ I due standard **non sono in conflitto**: guardano lo stesso livello da due prospettive diverse.  
Un’azienda matura integra entrambi:

- **ISO 27001/NIST** per la governance, la gestione delle informazioni e la protezione del dato.
    
- **IEC 62443** per la protezione degli impianti e la resilienza operativa.
    

---

📌 **Sintesi Feynman**:  
Immagina una fabbrica:

- in alto (ERP e MES) ci sono i manager che pianificano, gestiscono e fatturano → qui servono ISO 27001 e NIST, per proteggere i dati e i processi gestionali.
    
- in basso (SCADA, PLC, sensori) ci sono le macchine e le persone che fanno funzionare tutto → qui entra IEC 62443, che protegge gli impianti da guasti, attacchi e incidenti fisici.
    
- nel mezzo (MES/SCADA) i due mondi si incontrano: IT (informazioni) e OT (operazioni).


---
Perfetto, torniamo al **caso di studio Utility** e vediamo **come combinare ISO/IEC 27001 e IEC 62443**.

# Cosa fa ciascuno standard (sul caso reale)

- **ISO/IEC 27001** (ISMS = _Information Security Management System_): guida **manageriale+tecnica** per la sicurezza **dei sistemi informatici** (dati, processi, persone, policy e controlli).
    
- **IEC 62443** (IACS Security = _Industrial Automation and Control Systems_): guida **tecnica+operativa** per la sicurezza **degli impianti di automazione** (continuità del processo fisico e sicurezza delle persone), con focus su SCADA/PLC/reti OT.
    

# Lettura della figura (da sinistra a destra)

![Pasted image 20250913143649.png](../../imgs/Pasted%20image%2020250913143649.png)

## 1) Tabella di sinistra – “cosa copre chi”

**Dispositivi IT → ISO 27001 / NIST**

- **Client systems of staff** (PC d’ufficio), **Laptops**
    
- **Web server**, **Mail server**, **File server**
    
- **SAP systems** (ERP)
    
- **Networks** (LAN/WAN, Wi-Fi, firewall IT)
    

**Dispositivi OT → IEC 62443**

- **PLC – Programmable Logic Controller** (controllo locale)
    
- **HMI – Human Machine Interface / touch panel** (interfacce operatore)
    
- **Production control server** (server di cella/linea)
    
- **Industrial robots**
    
- **Remote I/O systems** (moduli I/O remoti)
    
- **Real-time networks** (Profinet, Modbus/TCP, EtherNet/IP, ecc.)
    

👉 Messaggio: **ISO 27001** presidia l’IT; **IEC 62443** presidia l’OT; **zona di confine** = entrambi.

## 2) Diagramma di destra – “dove cade ogni sistema”

Blocchi applicativi del caso Utility:

- **SAP** (ERP), **GIS – Geographic Information System**, **Maximo** (manutenzione) → **IT puro** → **ISO 27001**.
    
- **DSS – Decision Support System** e **Historian** → stanno **sul confine IT/OT**:
    
    - raccolgono dati di processo, parlano con SCADA e con l’IT → **valgono entrambi gli standard** (ISO 27001 per dati/processi, IEC 62443 per integrazione sicura con OT).
        
- **SCADA – Supervisory Control And Data Acquisition** e **RTU – Remote Terminal Unit** → **OT** → **IEC 62443** (priorità: disponibilità, sicurezza del processo, protezione fisica).
    

Le due ellissi mostrano proprio la **sovrapposizione**:

- l’ovale **ISO 27001** copre tutti i sistemi “computerizzati standard”;
    
- l’ovale **IEC 62443** copre SCADA/RTU (e scavalca su **Historian/DSS** perché sono direttamente connessi all’automazione).
    

# Come si integra, in pratica (pillole operative)

- **Governance unica**: ISMS ISO 27001 + Programma IACS IEC 62443 sotto il **CISO** (Chief Information Security Officer), con **CIO** (Chief Information Officer) e responsabile **OT** coinvolti.
    
- **Zone & Conduits (IEC 62443-3-2)**: segmentare reti **IT/DMZ/OT**, limitare i flussi (es. dati solo _dal_ SCADA _verso_ Historian/DSS), usare gateway/jump server, anche **data diode** se necessario.
    
- **Security Level (SL) per impianti**: definire obiettivi SL per SCADA/PLC; hardening HMI/engineering station; whitelisting applicativo; finestre di patching controllate.
    
- **Controlli ISO per i sistemi IT**: IAM/MFA, logging centralizzato (**SIEM**), cifratura, backup testati, gestione fornitori, hardening server (SAP, DSS, GIS, Maximo).
    
- **Gestione incidenti congiunta**: playbook **IT+OT** (ransomware su SCADA ≠ incidente su ERP); esercitazioni con i tecnici di impianto.
    
- **Procurement sicuro**: nei capitolati OT richiedere requisiti **IEC 62443-4-1/4-2** per vendor e componenti; per IT riferimenti a **ISO 27001/27002**.
    
- **Confine DSS/Historian**: autenticazione forte, protocolli sicuri/convertiti, filtraggio tag/protocolli, replica _pull_ dal lato IT, nessun comando operativo dal DSS verso OT.
    

# Morale (Feynman-style)

Pensa all’azienda come a **due “mondi” che si toccano**:

- **Mondo IT (uffici/dati)** → ISO 27001 tiene in ordine regole, persone, processi e controlli.
    
- **Mondo OT (impianti/processo fisico)** → IEC 62443 assicura che l’acqua continui a scorrere in sicurezza.
    
- **DSS/Historian** sono il **ponte**: lì applichi **entrambi** per non far passare il “fuoco” da un mondo all’altro.