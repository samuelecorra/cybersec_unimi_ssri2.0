In questa lezione vedremo, in maniera un po’ semplificata, come si costruisce un sistema di gestione informatica. Lo faremo a partire da un caso di studio immaginario ma derivato da un’azienda reale, con alcune modifiche e semplificazioni.

---
# Sistema informativo vs Sistema di gestione della sicurezza

## 1) La domanda di base

La lezione parte con un chiarimento fondamentale:

- Stiamo parlando di **progettare un nuovo sistema informativo**?
    
- Oppure di **progettare un sistema di gestione della sicurezza di un sistema informativo già esistente**?
    

👉 Sono due cose **diverse**, ma inevitabilmente **collegate**.

---

## 2) Differenza chiave

- **Sistema Informativo (SI)**: è l’insieme di **infrastrutture tecnologiche, software, basi dati, reti, processi e persone** che trattano informazioni per raggiungere gli obiettivi dell’organizzazione.
    
    - Es: server aziendali, database clienti, sistemi ERP, piattaforme web, reti interne.
        
- **Sistema di Gestione della Sicurezza di un SI (SGSI)**: è il **quadro organizzativo e procedurale** che serve a proteggere quel sistema informativo.
    
    - Include **policy, controlli, ruoli, responsabilità, misure tecniche e organizzative** per mantenere la sicurezza su riservatezza, integrità e disponibilità.
        

👉 In breve:

- il **SI** è la “macchina”,
    
- il **SGSI** è il “manuale di uso e manutenzione con controlli di sicurezza”.
    

---

## 3) Relazione tra i due

- La progettazione di un **SGSI** presuppone che il **SI esista già**.
    
- Tuttavia, durante la definizione del sistema di gestione possono emergere esigenze di **cambiare o rafforzare il SI stesso**.
    
    - Es: se il SGSI richiede logging centralizzato, ma il SI non lo supporta, bisogna aggiornare l’architettura.
        
    - Se il SI non ha un sistema di backup adeguato, il SGSI imporrà di implementarlo.
        

👉 Quindi il SGSI **non è separato** dal SI: lo governa, lo protegge, ma talvolta lo **influenza e lo modifica**.

---

## 4) Un esempio concreto

- **Scenario 1**: Un’azienda introduce un nuovo ERP (gestionale).
    
    - Questo è il **sistema informativo**.
        
- **Scenario 2**: La stessa azienda definisce procedure per la sicurezza:
    
    - Chi può accedere al gestionale.
        
    - Come vengono gestite le password.
        
    - Come si fanno i backup e i test di recovery.
        
    - Come si monitorano accessi e anomalie.
        
    - Questo è il **sistema di gestione della sicurezza**.
        

👉 Uno senza l’altro non basta:

- Un SI senza SGSI è **vulnerabile**.
    
- Un SGSI senza SI è **astratto**.
    

---

# Mini-riepilogo

- **SI = macchina (hardware, software, dati, processi)**.
    
- **SGSI = manuale + controlli per proteggere la macchina**.
    
- Il SGSI presuppone che il SI esista, ma può richiedere modifiche architetturali e funzionali al SI stesso.


---
# aso di studio Utility

## 1) Identità dell’azienda

- È una **Utility pubblica/privata mista** (51% proprietà del comune).
    
- Fornisce servizi idrici e di depurazione in una grande città italiana.
    
- Ha quindi un ruolo **critico**: senza di lei, niente acqua potabile, né gestione delle acque reflue → impatto diretto sulla vita dei cittadini e sulla salute pubblica.
    

---

## 2) Dati operativi principali

- **50.000+ clienti** → ampia base di utenza, quindi grande quantità di dati personali (anagrafiche, consumi, pagamenti).
    
- **230 milioni di m³/anno di acqua distribuita** → dimensioni rilevanti del servizio.
    
- **31 centrali di pompaggio + 500 pozzi** → infrastruttura complessa e distribuita.
    
- **2.228 km di rete acquedottistica + 1.500 km di fognatura** → vastissima rete fisica da gestire e monitorare.
    
- **2 impianti di trattamento reflui** → sistemi industriali critici, spesso basati su SCADA/ICS.
    
- **Rete in fibra ottica per le centrali di pompaggio** → alta interconnessione (ma anche vulnerabilità possibili, es. attacchi informatici mirati).
    

---

## 3) Governance interna

- **CEO (Chief Executive Officer)** → presente.
    
- **CIO (Chief Information Officer)** → presente, quindi gestione informatica formalizzata.
    
- **CISO (Chief Information Security Officer)** → _non presente_.
    

👉 Questo è un punto critico: l’azienda ha un CIO (quindi competenza IT generale), ma non un CISO dedicato alla sicurezza.  
Significa che la **sicurezza non è gestita in modo indipendente** e potrebbe essere solo una “funzione secondaria” dell’IT.

---

## 4) Implicazioni di sicurezza

- L’azienda ha una **infrastruttura critica nazionale (ICN)** → deve garantire disponibilità e resilienza, altrimenti si blocca l’erogazione di un bene essenziale.
    
- Dati e sistemi sensibili:
    
    - **Dati personali** (clienti, bollette).
        
    - **Dati tecnici** (telemetrie di rete idrica, SCADA).
        
    - **Processi critici** (controllo pompe, depurazione).
        
- Senza un sistema di gestione adeguato, rischia:
    
    - Attacchi ransomware ai sistemi SCADA.
        
    - Furti di dati personali dei clienti.
        
    - Sabotaggi su reti idriche o fognarie.
        

---

# Mini-riepilogo

Il caso di studio mostra un’azienda:

- con un **ruolo critico** per cittadini e ambiente;
    
- dotata di una **infrastruttura complessa e interconnessa**;
    
- che ha CIO ma non CISO, quindi **gap nella governance della sicurezza**.
    

👉 È l’esempio perfetto per capire **perché serve un sistema di gestione della sicurezza (SGSI)** e come implementarlo con ISO 27001.


---
# 📊 Il sistema informativo dell’azienda Utility

L’azienda usa un **ecosistema integrato** di applicativi, ciascuno con un ruolo specifico.

---

## 1) **SAP – Systems, Applications and Products in Data Processing**

_(Sistema gestionale ERP presso il CED – Centro Elaborazione Dati della sede centrale)_

- **Contabilità** → gestione entrate, uscite e bilanci.
    
- **Anagrafica clienti** → database con dati personali, contratti, indirizzi, consumi.
    
- **E-procurement** (Electronic Procurement) → piattaforma digitale per gare e ordini a fornitori.
    
- **Fatturazione** → emissione delle bollette a oltre 50.000 clienti.
    
- **Ordini a fornitore** → logistica e gestione degli acquisti aziendali.
    

👉 È il **cuore amministrativo e commerciale** dell’azienda.  
⚠️ **Rischio alto**: contiene dati personali (clienti) e dati economici (fornitori, bilanci).

---

## 2) **DSS – Decision Support System**

_(Sistema di supporto alle decisioni, sempre al CED)_

- Raccoglie e aggrega dati dai **contatori intelligenti (smart meter)** sul territorio.
    
- Fornisce report e analisi per supportare le decisioni operative e strategiche:
    
    - individuare perdite di rete,
        
    - rilevare consumi anomali,
        
    - scoprire frodi o guasti.
        

👉 È il **cervello analitico**: trasforma i dati grezzi in conoscenza utile.

---

## 3) **Maximo – IBM Maximo Asset Management**

_(Sistema di gestione degli asset e della manutenzione, al CED)_

- Gestione e pianificazione della **manutenzione** degli asset distribuiti sul territorio.
    
- Integrazione con dispositivi mobili:
    
    - i tecnici sul campo ricevono ordini di lavoro,
        
    - aggiornano lo stato degli interventi in tempo reale,
        
    - segnalano guasti o anomalie.
        

👉 È il **braccio operativo**: garantisce continuità e affidabilità tecnica delle infrastrutture.

---

## 4) **GIS – Geographic Information System**

_(Sistema informativo geografico, al CED)_

- Archivia e gestisce il **patrimonio delle reti e degli impianti**:
    
    - acquedotti,
        
    - fognature,
        
    - centrali di pompaggio.
        
- Integra i dati anagrafici degli asset (es. anno di costruzione, manutenzioni fatte).
    
- Consultabile tramite dispositivi mobili dai tecnici sul campo per rilievi e manutenzioni.
    

👉 È la **mappa digitale viva** delle infrastrutture.

---

## 5) **SCADA – Supervisory Control And Data Acquisition**

_(Sistema di controllo industriale, installato presso direzione acquedotto e depuratore)_

- **Acquedotto**:
    
    - gestione allarmi di rete,
        
    - controllo delle sottostazioni di pompaggio.
        
- **Depuratore**:
    
    - gestione allarmi,
        
    - monitoraggio e comando impianti di trattamento delle acque reflue.
        
- **Historian**: archivio cronologico che registra dati di processo (portata dell’acqua, pressione, qualità dei reflui).
    

👉 È il **sistema industriale critico (ICS – Industrial Control System)**.  
⚠️ Se compromesso → blocco del servizio idrico, gravi danni alla salute pubblica e all’ambiente.

---

# 🔑 Mini-riepilogo

Il sistema informativo è composto da:

- **SAP** → amministrazione, clienti, fornitori.
    
- **DSS** → analisi dati dei consumi.
    
- **Maximo** → gestione e manutenzione asset.
    
- **GIS** → mappa digitale delle infrastrutture.
    
- **SCADA/Historian** → controllo dei processi industriali in tempo reale.
    

👉 Insieme creano un ecosistema che copre **tutto**: dai dati amministrativi, alla manutenzione, fino al controllo fisico dell’acqua.  
Il **Sistema di Gestione della Sicurezza** (SGSI) deve quindi proteggere tutti questi livelli.


---
# 1️⃣ ISO/IEC 27001 in breve

- È lo **standard internazionale** per progettare, implementare e mantenere un SGSI.
    
- Funziona come una **cassetta degli attrezzi organizzativa**: non dice _quali_ tecnologie usare, ma _come_ strutturare il sistema di gestione della sicurezza.
    
- Si basa sul modello **PDCA** (Plan–Do–Check–Act), che garantisce il **miglioramento continuo**.
    

👉 PDCA = il “ciclo di vita” del sistema di gestione.

---

# 2️⃣ Il ciclo PDCA applicato a ISO 27001

### 🔵 PLAN (Pianificazione)

1. **Contesto (4)**
    
    - Comprendere l’organizzazione (missione, processi, infrastrutture).
        
    - Identificare **parti interessate** (clienti, fornitori, regolatori, cittadini nel caso della Utility).
        
    - Definire l’**ambito (scope)** del SGSI.
        
    - Avviare l’ISMS (Information Security Management System).
        
2. **Leadership (5)**
    
    - Impegno del **top management** (CEO, CIO).
        
    - Definizione della **politica di sicurezza**.
        
    - Assegnazione di **ruoli e responsabilità** (es. CISO).
        
3. **Pianificazione (6)**
    
    - Identificazione di **rischi e opportunità**.
        
    - Definizione di **obiettivi di sicurezza** e piani per raggiungerli.
        

👉 Questa fase è la “progettazione su carta” del SGSI.

---

### 🔵 DO (Attuazione)

4. **Supporto (7)**
    
    - Risorse economiche e umane.
        
    - Competenze e formazione del personale.
        
    - Awareness (consapevolezza).
        
    - Comunicazione interna ed esterna.
        
    - Documentazione delle procedure.
        
5. **Attività operative (8)**
    
    - Pianificazione e controllo delle operazioni.
        
    - **Valutazione del rischio** (risk assessment).
        
    - **Trattamento del rischio** (risk treatment: es. firewall, backup, formazione, policy).
        

👉 Qui il SGSI prende vita con risorse, azioni concrete e controlli operativi.

---

### 🔵 CHECK (Verifica)

6. **Valutazione delle prestazioni (9)**
    
    - Monitoraggio e misurazione dei controlli.
        
    - Audit interno periodico.
        
    - Riesame da parte della Direzione (Management Review).
        

👉 È la fase di **controllo**: verificare se ciò che si è pianificato e fatto funziona davvero.

---

### 🔵 ACT (Miglioramento)

7. **Miglioramento (10)**
    
    - Gestione delle **non conformità** (quando qualcosa non rispetta lo standard).
        
    - Adozione di **azioni correttive**.
        
    - **Miglioramento continuo** → il sistema evolve nel tempo.
        

👉 È la fase che chiude e rilancia il ciclo: da ogni errore o debolezza, il sistema deve imparare e rafforzarsi.

---

# 3️⃣ Lettura del grafico

![Pasted image 20250913140143.png](../../imgs/Pasted%20image%2020250913140143.png)

Il grafico mostra come ISO 27001 **mappa i requisiti sui blocchi PDCA**:

- **PLAN** = blocchi 4–6 (contesto, leadership, pianificazione).
    
- **DO** = blocchi 7–8 (supporto, attività operative).
    
- **CHECK** = blocco 9 (valutazione).
    
- **ACT** = blocco 10 (miglioramento).
    

👉 È come un **ciclo continuo**: si pianifica, si fa, si verifica, si migliora… e si riparte.

---

# 🎯 Sintesi

- ISO 27001 = struttura organizzativa per la sicurezza delle informazioni.
    
- PDCA = garantisce che il sistema non sia statico, ma in **miglioramento costante**.
    
- Applicato al caso Utility, significa:
    
    - Pianificare la sicurezza dell’intera infrastruttura.
        
    - Implementare misure (SCADA, SAP, GIS, ecc.).
        
    - Monitorare e verificare.
        
    - Correggere e migliorare nel tempo.


---
# Da dove si parte (Contesto)?

![Pasted image 20250913140308.png](../../imgs/Pasted%20image%2020250913140308.png)

## 1) Approccio Top-Down

- La progettazione di un **Sistema di Gestione della Sicurezza delle Informazioni (SGSI)** parte sempre dall’alto: **dal top management**.
    
- Bisogna:
    
    - comprendere l’organizzazione,
        
    - definire i rischi da affrontare,
        
    - capire le necessità e aspettative delle parti interessate,
        
    - delimitare il **campo di applicazione** del SGSI,
        
    - fissare gli **obiettivi di sicurezza**.
        

👉 Non si parte dalla tecnologia, ma dalla **visione aziendale**.

---

## 2) Ciclo logico del progetto

La slide mostra un mini-flusso:

- **Stato attuale (As Is)** → fotografia della situazione attuale (punti di forza, vulnerabilità, rischi).
    
- **Progetto di cambiamento** → definizione delle azioni correttive e degli investimenti.
    
- **Obiettivo del progetto (To Be)** → dove vogliamo arrivare (livello di sicurezza e maturità desiderato).
    

👉 È come un “viaggio”: si parte da **As Is**, si attraversa il cambiamento, si arriva al **To Be**.

---

## 3) Caso di studio Utility

- Negli ultimi anni l’azienda è cresciuta, migliorando i servizi ai cittadini.
    
- Tuttavia il **business è condizionato da tre fattori critici**:
    
    1. **Sprechi dovuti a perdite** (es. acqua che non arriva ai clienti).
        
    2. **Mancata fatturazione** (dati contatori non aggiornati o errori).
        
    3. **Alti costi di manutenzione** degli apparati distribuiti sul territorio.
        

👉 Per questa azienda, la **continuità operativa** delle attività di **rilevamento perdite** e di **manutenzione** è **strategica**: senza, rischia gravi perdite economiche e perdita di fiducia degli utenti.

---

## 4) Collegamento con ISO 27001

- Questo è esattamente il **Paragrafo 4** della norma ISO 27001: _“Contesto dell’organizzazione”_.
    
- Serve per allineare la **sicurezza delle informazioni** con le **strategie e le priorità aziendali**.
    

---

# 🎯 Sintesi

- Si parte dall’**analisi del contesto** → capire dove siamo, cosa vogliamo proteggere e perché.
    
- Il progetto di sicurezza deve avere **coerenza con gli obiettivi di business**.
    
- Nel caso Utility, il focus è:
    
    - garantire **continuità operativa**;
        
    - ridurre **sprechi e costi di manutenzione**;
        
    - migliorare la **precisione della fatturazione**.


---
# Leadership nel SGSI

## 1) Che cos’è la leadership in un SGSI

- ISO/IEC 27001 (§5) stabilisce che la **leadership aziendale** deve:
    
    - dimostrare **impegno diretto** nella sicurezza delle informazioni,
        
    - approvare e diffondere una **politica di sicurezza**,
        
    - assegnare **ruoli, responsabilità e autorità chiare**.
        

👉 Senza leadership, il Sistema di Gestione della Sicurezza delle Informazioni (SGSI) resta solo sulla carta.

---

## 2) Le figure chiave (espansione acronimi)

- **CEO – Chief Executive Officer**  
    L’amministratore delegato, massima carica decisionale.
    
    - Nel caso di studio: ha **sensibilità informatica e tecnologica**, quindi comprende bene l’importanza della cyber security.
        
- **CIO – Chief Information Officer**  
    Responsabile dei sistemi informativi (IT – Information Technology).
    
    - Nel caso di studio: è presente, ma con **mansioni non ben definite** e non pienamente codificate.
        
- **CISO – Chief Information Security Officer**  
    Responsabile della sicurezza delle informazioni.
    
    - Nel caso di studio: **non è ancora stato nominato**. È una lacuna importante.
        
- **OT – Operational Technology**  
    Tecnici che gestiscono i sistemi industriali (SCADA, pompe, depuratori).
    
    - Nel caso di studio: non rispondono al CIO, perché lavorano direttamente sugli impianti → rischio di frammentazione tra **IT (information technology)** e **OT (operational technology)**.
        

---

## 3) Il problema emerso

- Il CEO è consapevole.
    
- C’è un CIO, ma senza autorità pienamente definite.
    
- Non c’è un CISO, quindi manca una **figura indipendente e dedicata alla sicurezza**.
    
- I tecnici OT non sono integrati nella catena di responsabilità IT.
    

👉 Rischio: **governance frammentata** → nessuno ha la responsabilità univoca della sicurezza.

---

## 4) La decisione correttiva

- Definire meglio le **responsabilità del CIO**.
    
- Nominare un **CISO** e dargli **mandato ufficiale** per la sicurezza delle informazioni.
    
- Coordinare i tecnici **IT e OT** sotto un unico quadro di gestione della sicurezza.
    

---

## 5) Collegamento ISO 27001

- Questa fase corrisponde al **Paragrafo 5 (Leadership)** della norma.
    
- I **controlli associati** (slide: paragrafi 6,7) riguardano la successiva pianificazione e il supporto:
    
    - senza leadership, non puoi pianificare né allocare risorse.
        

---

# 🎯 Sintesi

La leadership nel SGSI significa:

- impegno visibile del top management,
    
- politica chiara di sicurezza,
    
- ruoli ben definiti (CEO → strategia, CIO → sistemi, CISO → sicurezza),
    
- integrazione tra IT e OT.
    

👉 Nel caso Utility, il primo passo concreto è **nominare il CISO e integrare IT e OT**, per evitare falle di responsabilità.


---
# Pianificazione (Plan)

## 1) Obiettivo della pianificazione

- Definire **azioni concrete** per:
    
    - affrontare i **rischi** legati al sistema informativo,
        
    - sfruttare le **opportunità** (es. tecnologie innovative come smart meters),
        
    - stabilire **policy** e **procedure di sicurezza** con relativi controlli.
        

👉 È la fase in cui la sicurezza da concetto astratto diventa **progetto organizzato**, con obiettivi e regole chiare.

---

## 2) Caso di studio Utility – rischi e priorità

- **Continuità operativa** = obiettivo di primo livello.
    
    - Senza acqua → danno sociale, ambientale ed economico.
        
- **Sistema di tubature complesso e vetusto** → rischio di perdite e sprechi.
    
    - Necessità di monitoraggio continuo con **smart meters (contatori intelligenti)** che misurano portata e pressione.
        
- **Manutenzione tempestiva** = fattore critico.
    
    - Interventi rapidi riducono perdite, migliorano servizio e riducono rischi fisici per le persone.
        
- **Rapporti commerciali (clienti e fornitori)** = area sensibile.
    
    - Essendo utility pubblica → gestione delicata di appalti, fornitori e contratti.
        
- **Sistemi fondamentali da proteggere**:
    
    - **Gestionale SAP (Systems, Applications and Products)** → clienti, contabilità, fornitori.
        
    - **Maximo e DSS (Decision Support System)** → manutenzione e analisi tecnica.
        
    - **GIS (Geographic Information System)** → patrimonio delle reti e consultazione sul campo.
        
    - **SCADA (Supervisory Control And Data Acquisition)** → controllo industriale degli impianti.
        

👉 Ognuno di questi sistemi comporta rischi diversi (furto dati, sabotaggio, indisponibilità) → quindi policy e controlli mirati.

---

## 3) Policy e procedure

In questa fase si devono:

- definire **policy di sicurezza** (linee guida generali approvate dal top management);
    
- scrivere **procedure operative** (come si implementano i controlli nella pratica);
    
- attribuire le **responsabilità** alle persone coinvolte.
    

👉 Nel caso Utility: viene istituita una **squadra di tecnici specializzati** per i vari sottosistemi (SAP, GIS, SCADA…) che risponde direttamente al **CISO (Chief Information Security Officer)**.

---

## 4) Collegamento ISO 27001

- Questa fase corrisponde al **Paragrafo 6 (Pianificazione)**.
    
- Ma la slide cita anche i collegamenti con paragrafi successivi (6, 8, 9, 11, 13) perché:
    
    - i rischi definiti qui → diventano **attività operative (§8)**,
        
    - vengono poi **monitorati e valutati (§9)**,
        
    - collegati ai controlli dell’**Annex A** (es. A.11 Physical Security, A.13 Network Security).
        

---

# 🎯 Sintesi

- Pianificazione = decidere **cosa fare, come farlo, chi lo fa**.
    
- Nel caso Utility:
    
    - mantenere continuità operativa,
        
    - monitorare tubature con smart meters,
        
    - gestire correttamente manutenzione, clienti e fornitori,
        
    - proteggere i sistemi fondamentali (SAP, DSS, Maximo, GIS, SCADA).
        
- Azione chiave → creare una **squadra di tecnici dedicata**, che risponde al **CISO**.
    

👉 È la fase in cui il SGSI prende forma sulla base dei rischi e degli obiettivi di business.


---
# Pianificazione: Il Sistema Gestionale

## 1) Importanza del gestionale

- È il **sistema più critico** dell’organizzazione: da esso dipendono tutte le attività centrali.
    
- Funzioni principali:
    
    - **Contabilità** → bilanci, spese, entrate.
        
    - **Fatturazione** → emissione bollette per oltre 50.000 clienti.
        
    - **Anagrafica Clienti** → dati personali e contratti.
        
    - **E-procurement (Electronic Procurement)** → gestione gare pubbliche, bandi di appalto, ordini a fornitori.
        

👉 Senza gestionale → si blocca sia la parte **economico-finanziaria**, sia la parte **operativa e contrattuale**.

---

## 2) Architettura logica e fisica (dalla slide)

![Pasted image 20250913140919.png](../../imgs/Pasted%20image%2020250913140919.png)

La slide rappresenta i principali componenti del gestionale e i loro collegamenti:

- **Mobile Front-End**
    
    - Accesso client tramite dispositivi mobili.
        
    - Include:
        
        - **MSA Client** (Mobile Sales Application),
            
        - **IPC** (Inter-Process Communication),
            
        - **JVM – Java Virtual Machine** (ambiente di esecuzione Java),
            
        - **SQL Server** (gestione basi di dati relazionali),
            
        - **Backweb Client** (interfaccia per la comunicazione sincrona via web).
            
- **Communication Station**
    
    - Contiene **MTS – Microsoft Transaction Server** (per gestire transazioni distribuite).
        
    - **DCOM Connector – Distributed Component Object Model** → middleware Microsoft per la comunicazione fra componenti software distribuiti.
        
    - Collegamento tramite **RFC – Remote Function Call** con gli altri sistemi.
        
- **CRM Server – Customer Relationship Management**
    
    - Gestito tramite **Middleware + Admin Console**.
        
    - Si appoggia a un **RDBMS – Relational DataBase Management System**.
        
    - Serve a gestire i rapporti con clienti e fornitori.
        
- **Mobile Development Station**
    
    - Comprende:
        
        - **Mobile Application Studio**,
            
        - **Application Repository**,
            
        - **SQL Server** → per sviluppare e testare applicazioni mobili aziendali.
            
- **SAP R/3**
    
    - ERP centrale (Enterprise Resource Planning).
        
    - Utilizza **RDBMS**.
        
    - Estendibile con **Plugin 2000**.
        
- **Sistemi opzionali**:
    
    - **SAP BW – Business Warehouse** (data warehouse per analisi dati).
        
    - **SAP APO – Advanced Planner and Optimizer** (pianificazione avanzata).
        

---

## 3) Criticità di sicurezza

- Il gestionale ha un’interfaccia **web verso l’esterno** per l’E-procurement.
    
    - Questo lo rende un **punto di esposizione diretto a minacce esterne** (attacchi informatici).
        
    - Es. attacchi SQL injection, exploit delle piattaforme web, furto di credenziali.
        
- Il rischio è amplificato perché l’azienda è **pubblica**:
    
    - ogni operazione deve rispettare procedure legali complesse,
        
    - un incidente di sicurezza può portare non solo a danni economici, ma anche a **conseguenze legali e reputazionali**.
        

---

# 🎯 Sintesi

- Il gestionale (SAP e moduli collegati) è il **sistema più critico**.
    
- Supporta funzioni vitali: contabilità, clienti, fatturazione, appalti.
    
- Esposto via web → vulnerabile a minacce esterne.
    
- Essendo azienda pubblica → impatti amplificati (legali, reputazionali, operativi).
    

👉 La pianificazione della sicurezza deve concentrarsi qui in modo prioritario: controlli sugli accessi, protezione delle interfacce web, logging, cifratura dei dati, separazione dei ruoli.


---
# Pianificazione – Architetture Operative

![Pasted image 20250913141058.png](../../imgs/Pasted%20image%2020250913141058.png)

## 1) Perché sono critiche

Non solo il **sistema gestionale** è vitale, ma anche i sistemi **operativi** che lavorano sul territorio:

- **DSS – Decision Support System** → sistema di supporto alle decisioni, elabora le telemisure (consumi, portate, pressioni).
    
- **SCADA – Supervisory Control And Data Acquisition** → controlla in tempo reale gli impianti (stazioni di pompaggio, depuratori).
    
- **Historian** → database storico che archivia tutti i dati di processo provenienti da SCADA e sensori.
    
- **GIS – Geographic Information System** → geolocalizza tubature, reti, impianti.
    
- **Sistema di manutenzione (Maximo)** → coordina gli interventi sul territorio, anche tramite app mobile.
    

👉 Tutti questi sistemi **impattano direttamente sulla continuità operativa**: se SCADA o DSS si fermano, non solo l’acqua smette di essere monitorata, ma si rischiano **gravi danni economici e di servizio pubblico**.

---

## 2) Flusso dei dati (parte alta del grafico)

La catena è questa:

1. **Smart Meters** (contatori intelligenti, oltre 50.000 installati, di cui 200 integrati con concentratori multiservizio) → rilevano quantità e pressione dell’acqua in tempo reale.
    
2. **Concentratori multiservizio** → raccolgono i dati da più smart meters e li inviano.
    
3. **SAC-M / SAC-T** (moduli di raccolta e trasmissione dati, anche via GPRS).
    
4. **MDM – Meter Data Management** → piattaforma per la gestione dei dati di misura.
    
5. I dati confluiscono nel **DB Historian** → archivio centrale.
    
6. Lo **SCADA** li usa per la supervisione in tempo reale (pressioni, portate, consumi energetici).
    
7. Il **DSS** (Aquadvanced Energy) elabora i dati per analisi avanzate e supporto decisionale.
    

👉 Quindi: i dati scorrono **dal campo (sensori/smart meters) → Historian → SCADA → DSS**.

---

## 3) Architettura territoriale (parte bassa del grafico)

- Le **stazioni di pompaggio** sono collegate in un **anello di rete** (fibra ottica).
    
- Ogni stazione comunica con il sistema **SCADA/Historian** centrale.
    
- Questo rende possibile monitorare e controllare l’intera rete idrica.
    

Ma:

- L’uso di tecnologie standard (es. TCP/IP, protocolli industriali aperti) espone gli impianti a **vulnerabilità comuni**.
    
- Lo SCADA, essendo distribuito e connesso, è potenzialmente bersaglio di **attacchi DoS (Denial of Service)** o attacchi mirati ai sistemi di controllo.
    

---

## 4) Problema organizzativo

Un ulteriore punto critico:

- Le **responsabilità tecniche** dei vari sistemi (SCADA, DSS, manutenzione, GIS) sono in mano a reparti diversi.
    
- Non c’è un coordinamento unico sotto il **CIO (Chief Information Officer)** o il **CISO (Chief Information Security Officer)**.
    
- Questo frammenta la gestione e aumenta il rischio di buchi di sicurezza.
    

---

# 🎯 Sintesi

- I sistemi operativi (SCADA, DSS, GIS, manutenzione) sono altrettanto critici quanto il gestionale.
    
- Il flusso dati dai sensori → Historian → SCADA → DSS è complesso e vitale.
    
- La topologia ad anello delle stazioni di pompaggio è robusta ma esposta ad attacchi informatici.
    
- Serve un coordinamento centrale (CISO) per garantire sicurezza e continuità operativa.


---
# Pianificazione – Definizione del valore e del rischio

![Pasted image 20250913141228.png](../../imgs/Pasted%20image%2020250913141228.png)

La slide mostra una **matrice di valutazione** che associa ad ogni sistema:

- **rischio principale** (cosa succede in caso di malfunzionamento o attacco),
    
- **valore indicativo** (quanto pesa in termini economici e operativi),
    
- **disponibilità richiesta** (uptime),
    
- **criticità** (priorità di intervento).
    

Il riferimento economico è il **fatturato aziendale di 270 milioni di euro**: qualsiasi perdita o interruzione va quindi rapportata a questa base.

---

## Analisi dei sistemi

### 1. Gestionale SAP

- **Rischio**: mancata fatturazione, perdita di dati sensibili.
    
- **Valore**: altissimo → cuore dell’amministrazione aziendale.
    
- **Disponibilità**: media (99,0%).
    
- **Criticità**: elevata → perché un blocco prolungato compromette la fatturazione e la gestione contrattuale.
    

👉 Una pausa breve (es. 30 minuti) può essere gestita, ma interruzioni lunghe hanno impatti gravi su **cash flow e credibilità legale**.

---

### 2. DSS (Decision Support System)

- **Rischio**: mancata gestione delle perdite d’acqua, mancata fatturazione legata ai consumi, possibili danni fisici (es. pressione anomala nelle condotte).
    
- **Valore**: altissimo → gestisce la parte “intelligente” della rete idrica.
    
- **Disponibilità**: media (99,0%).
    
- **Criticità**: elevata → perché un malfunzionamento genera non solo perdite economiche, ma anche **danni fisici e reputazionali**.
    

---

### 3. Maximo (manutenzione)

- **Rischio**: ritardi nella manutenzione, con impatti su guasti e interventi sul territorio.
    
- **Valore**: medio.
    
- **Disponibilità**: medio-alta (99,5%).
    
- **Criticità**: media → importante, ma non catastrofico: eventuali ritardi si accumulano nel tempo.
    

---

### 4. GIS (Geographic Information System)

- **Rischio**: ritardi nella localizzazione guasti, nelle nuove installazioni, o nella riparazione.
    
- **Valore**: medio.
    
- **Disponibilità**: medio-alta (99,5%).
    
- **Criticità**: media → fondamentale per la precisione sul campo, ma tollera brevi interruzioni.
    

---

### 5. SCADA/Historian

- **Rischio**: perdita dati di telemisura, mancata gestione anomalie negli impianti di pompaggio e depurazione, possibili **danni fisici alle persone**.
    
- **Valore**: alto.
    
- **Disponibilità**: altissima (99,9%).
    
- **Criticità**: elevata → perché ogni fermo può avere conseguenze **immediate e gravi** sul servizio e sulla sicurezza pubblica.
    

---

## Sintesi generale

- Sistemi più critici: **Gestionale SAP, DSS e SCADA/Historian**.
    
- Sistemi di supporto (Maximo, GIS) hanno criticità media ma vanno comunque inclusi nella pianificazione.
    
- **Disponibilità** è il driver principale della priorità:
    
    - 99,0% (SAP, DSS) → tollerano downtime limitato.
        
    - 99,9% (SCADA) → downtime minimo, rischio altissimo.
        

---

📊 **Decisione di budget e priorità**:  
Le risorse dovranno concentrarsi sul rafforzamento di SAP, DSS e SCADA/Historian, mentre GIS e Maximo, pur importanti, possono avere un livello di protezione meno spinto.


---
# Supporto nel Sistema di Gestione della Sicurezza delle Informazioni (SGSI)

Questa fase riguarda tutto ciò che serve a **dare gambe e sostanza** al sistema di gestione: senza risorse, competenze e comunicazione, anche il miglior piano resta lettera morta.

### 1. Risorse

L’organizzazione deve **destinare risorse adeguate** per:

- istituire il SGSI (Sistema di Gestione per la Sicurezza delle Informazioni),
    
- mantenerlo nel tempo,
    
- aggiornarlo quando cambiano tecnologia, minacce o normativa,
    
- garantire un **ciclo di miglioramento continuo** (coerente con il modello PDCA: Plan–Do–Check–Act).
    

Nel caso dell’azienda Utility, la buona gestione ha permesso di investire circa **2–3% degli utili annuali (1–1,2 milioni di euro)** in tecnologie, garantendo solidità economica al piano di sicurezza. Questo è un fattore chiave di successo: senza budget dedicato, un SGSI non può reggere.

---

### 2. Competenze

Le persone coinvolte devono avere le competenze necessarie. Questo significa:

- **definire i ruoli** e i livelli di competenza richiesti,
    
- fornire **istruzione, formazione e addestramento** mirati (es. corsi su SCADA, cybersecurity, gestione incidenti),
    
- mettere in atto misure per colmare gap di competenze (es. assunzioni, outsourcing, training on the job),
    
- **verificare periodicamente** che le competenze siano realmente acquisite ed efficaci.
    

L’azienda deve anche **conservare evidenze documentate** (certificazioni, attestati, registri di formazione) per dimostrare la conformità agli audit ISO 27001.

---

### 3. Awareness (Consapevolezza)

Tutti i dipendenti devono conoscere:

- la **policy di sicurezza** aziendale,
    
- le **responsabilità personali** legate alla gestione dei dati e dei sistemi,
    
- le conseguenze della **non conformità** (che possono andare da richiami disciplinari a responsabilità penali in caso di dolo o negligenza).
    

In un’azienda come quella del caso di studio, dove la sicurezza dell’acqua e della rete idrica incide direttamente sulla salute pubblica, la consapevolezza del personale è **critica quanto la tecnologia**.

---

### 4. Comunicazione

Devono essere stabiliti criteri chiari su **come, quando e a chi** comunicare informazioni relative alla sicurezza:

- **interna** (es. procedure per segnalare incidenti, linee guida per i tecnici, report periodici alla direzione),
    
- **esterna** (es. comunicazioni agli enti regolatori, al Garante Privacy, o ai cittadini in caso di disservizi).
    

---

### 5. Informazioni documentate

Tutte le attività del SGSI devono essere **registrate, tracciate e archiviate**:

- policy, procedure, manuali, registri di formazione, piani di manutenzione, report di monitoraggio,
    
- documentazione delle misure correttive e dei miglioramenti adottati.
    

---

## Caso di studio – Utility

L’azienda ha inserito nella **mission aziendale** la continuità degli investimenti tecnologici. Questo ha due effetti positivi:

1. rende la sicurezza parte integrante della strategia di lungo periodo,
    
2. dà stabilità al SGSI, evitando oscillazioni legate a singoli esercizi economici.
    

In altre parole, non si tratta solo di “fare un investimento una tantum”, ma di **garantire un flusso costante di risorse** a sostegno della sicurezza.

---

📌 **Controlli ISO 27001**: questa fase è coperta dai paragrafi **7, 8, 9 e 11** della norma, che riguardano risorse, competenze, awareness, comunicazione e gestione delle informazioni documentate.


---
# Attività operative / realizzative

Dopo aver analizzato il contesto, definito la leadership, pianificato e predisposto le risorse (fasi precedenti del ciclo ISO 27001–PDCA), ora è il momento di **passare all’azione**.

### 1. Dalla teoria alla pratica

Questa fase serve a trasformare le policy e le procedure scritte in **attività concrete**:

- pianificazione delle attività operative,
    
- definizione di una roadmap con priorità e tempi,
    
- applicazione dei controlli di sicurezza,
    
- monitoraggio costante dei rischi per adattare le contromisure.
    

👉 È la fase in cui il sistema di gestione “vive” nella quotidianità aziendale.

---

### 2. Pianificazione e controllo operativi

Gli obiettivi definiti in fase di pianificazione (es. continuità operativa, riduzione dei rischi sugli SCADA, protezione dei dati sensibili del gestionale SAP) vengono tradotti in:

- **progetti concreti** (es. aggiornamento software, segmentazione di rete, backup protetti),
    
- **procedure operative** (es. come gestire incidenti di data breach, come monitorare smart meters, come fare manutenzione sicura sugli impianti),
    
- **controlli tecnici e organizzativi** (es. firewall, IDS/IPS, autenticazioni a più fattori, procedure di escalation).
    

Ogni attività deve avere:

- un **responsabile** (chi la esegue o la supervisiona),
    
- una **tempistica** (entro quando),
    
- un **criterio di verifica** (come misuro che è stata fatta bene).
    

---

### 3. Valutazione e trattamento del rischio

La valutazione del rischio non è statica:

- ogni attività implementata deve essere **riesaminata** per capire se ha ridotto davvero il rischio,
    
- i nuovi rischi che emergono (es. minacce ransomware o attacchi DoS agli impianti) devono essere gestiti con misure aggiornate,
    
- il trattamento del rischio deve seguire logiche di priorità (prima i rischi più critici per continuità e sicurezza pubblica).
    

---

### 4. Roadmap di implementazione

Nel caso dell’azienda Utility, la roadmap deve comprendere:

- interventi sui sistemi **gestionali** (SAP, DSS, GIS, Maximo),
    
- potenziamento della sicurezza degli impianti **SCADA/Historian** (molto esposti e con impatti anche fisici sulle persone),
    
- creazione di una **squadra di sicurezza** che risponde al CISO (Chief Information Security Officer),
    
- programmi di formazione continua al personale,
    
- aggiornamenti costanti di hardware e software.
    

👉 In pratica, questa roadmap è una **mappa temporale**: cosa faccio subito (azioni correttive urgenti), cosa faccio entro 6 mesi, cosa pianifico a lungo termine.

---

### 5. Collegamento con ISO 27001

Le attività operative corrispondono ai controlli previsti da vari paragrafi della norma, in particolare:

- **§10**: pianificazione e controllo operativo,
    
- **§12**: sicurezza operativa,
    
- **§13**: sicurezza delle comunicazioni,
    
- **§14**: acquisizione, sviluppo e manutenzione sicura dei sistemi,
    
- **§17**: continuità operativa.
    

---

📌 **Sintesi Feynman**: se finora abbiamo scritto il “manuale di volo” del SGSI, qui iniziamo a **pilotare davvero l’aereo**. Significa accendere i motori, seguire la rotta definita, monitorare gli strumenti e correggere subito la rotta se si presenta una turbolenza (nuovo rischio).


---
# Valutazione delle prestazioni

### 1. Cosa significa

Dopo aver pianificato (Plan) e realizzato (Do), bisogna **verificare**:

- se le misure di sicurezza introdotte hanno raggiunto gli obiettivi prefissati,
    
- se ci sono scostamenti tra risultati attesi e risultati reali,
    
- se il sistema ha mantenuto la sua efficacia anche di fronte a cambiamenti (nuove minacce, aggiornamenti IT/OT, riorganizzazioni interne).
    

---

### 2. Attività principali

- **Monitoraggio** → raccolta costante di dati (es. tempi medi di risposta agli incidenti, numero di vulnerabilità rilevate, percentuale di sistemi aggiornati).
    
- **Misurazione** → trasformazione dei dati in indicatori (KPI, Key Performance Indicators) confrontabili nel tempo.
    
- **Analisi** → interpretazione dei risultati per capire se le performance sono in linea con le aspettative.
    
- **Valutazione** → giudizio finale: le misure adottate sono adeguate? Devono essere rafforzate o modificate?
    

👉 In pratica, questa fase è come un “tagliando periodico” dell’intero SGSI.

---

### 3. Strumenti utilizzati

- **Audit interni** → ispezioni sistematiche condotte da personale qualificato (ma indipendente dai processi controllati) per verificare che policy, procedure e controlli siano applicati davvero.
    
- **Riesame della direzione** → momento in cui il top management (CEO, CIO, CISO) esamina i risultati degli audit e delle analisi per decidere eventuali correzioni.
    
- **Gestione dei cambiamenti** → valutazione dell’impatto che hanno modifiche strutturali (nuovi software, nuove normative, riorganizzazioni aziendali) sulla sicurezza delle informazioni.
    

---

### 4. Caso di studio Utility

- Creazione di un **team di Audit informatico** → dedicato al controllo periodico di tutti i sistemi (Gestionale SAP, DSS, Maximo, GIS, SCADA).
    
- Identificazione dei **controlli chiave** → ad esempio: verifica dei backup, controlli sugli accessi, test di continuità operativa sugli impianti SCADA, conformità alle policy GDPR.
    
- Confronto costante tra **risultati attesi** (es. disponibilità 99,9% del sistema SCADA) e **risultati reali** (eventuali disservizi o anomalie).
    

---

### 5. Riferimento normativo

Questa fase corrisponde al **Paragrafo 18 della ISO/IEC 27001**, che tratta proprio di:

- monitoraggio,
    
- misurazione,
    
- analisi,
    
- valutazione delle prestazioni del SGSI.
    

---

📌 **Sintesi Feynman**: pensa al SGSI come a un motore: non basta costruirlo e farlo partire, bisogna avere strumenti sul cruscotto che ti dicano **quanto carburante hai**, **se il motore gira bene**, **se i giri sono troppo alti**. Gli audit e le valutazioni servono a capire se stai viaggiando in sicurezza o se rischi un guasto improvviso.


---
# Miglioramento continuo del SGSI

### 1. L’idea di fondo

Un **Sistema di Gestione della Sicurezza delle Informazioni (SGSI)** non è mai “finito una volta per tutte”:

- le minacce cambiano,
    
- le tecnologie si aggiornano,
    
- le normative evolvono,
    
- l’azienda stessa cresce e si trasforma.
    

👉 Per questo la ISO/IEC 27001 richiede **un processo ciclico di miglioramento continuo**, che interviene ogni volta che vengono rilevate **non conformità (nc)** o semplicemente quando emergono nuove opportunità di ottimizzazione.

---

### 2. Gestione delle non conformità

Una **non conformità (nc)** è quando una regola, una procedura o un controllo previsto dal SGSI **non viene rispettato**.  
Esempi:

- un backup non eseguito nei tempi stabiliti,
    
- un accesso non autorizzato non bloccato in tempo,
    
- la mancata applicazione di una patch di sicurezza critica.
    

Il processo prevede:

1. **Controllo e correzione immediata** → bloccare l’effetto della nc (es. ripristinare subito un backup mancante).
    
2. **Analisi della causa radice** → capire perché si è verificata (errore umano, mancata formazione, procedura inadeguata, mancanza di risorse).
    
3. **Prevenzione di recidive** → determinare se potrebbero verificarsi nc simili in futuro.
    
4. **Attuazione delle azioni correttive (ac)** → modificare procedure, introdurre nuovi controlli o strumenti.
    
5. **Verifica di efficacia** → valutare se la correzione adottata ha davvero eliminato il problema.
    
6. **Aggiornamento del SGSI** → rivedere e, se necessario, cambiare parti del sistema di gestione che si sono rivelate inadeguate.
    

---

### 3. Miglioramento continuo

Oltre alla gestione delle nc, l’organizzazione deve:

- **riesaminare regolarmente** l’idoneità, l’adeguatezza e l’efficacia del SGSI;
    
- **apportare modifiche proattive**, non solo reattive, ad esempio introducendo nuove tecnologie di sicurezza (SIEM, SOC), migliorando le policy di formazione, adottando standard aggiornati;
    
- integrare il miglioramento nella cultura aziendale: ogni dipendente deve sentirsi parte del processo.
    

---

### 4. Caso di studio Utility

Per la nostra Utility:

- Viene creato un **flusso di segnalazione immediata** per le non conformità (es. guasto SCADA, perdita di telemisura, malfunzionamento DSS).
    
- Il team guidato dal **CISO** si occupa di analizzare le cause radice → esempio: ritardi di manutenzione causati da assenza di procedure standardizzate.
    
- Si attuano **azioni correttive**: nuova procedura per la gestione delle squadre di manutenzione, sistemi di alert automatici per i sensori, introduzione di un sistema di ticketing centralizzato.
    
- Dopo l’azione, viene eseguita una **verifica di efficacia** (audit interni, test di resilienza).
    
- Infine, si procede con un **aggiornamento del SGSI**, migliorando la policy di monitoraggio e manutenzione.
    

---

### 5. Riferimenti normativi

Il miglioramento continuo è trattato nei **paragrafi 14 e 18 della ISO/IEC 27001**.

- Par. 14 → requisiti su incidenti e non conformità.
    
- Par. 18 → valutazione delle prestazioni e miglioramento.
    

---

📌 **Sintesi Feynman**: pensa al SGSI come a un organismo vivente. Se si ammala (non conformità), va curato subito, va capito perché si è ammalato, e bisogna rafforzarlo per evitare ricadute. Ma anche quando è sano, va allenato e migliorato, perché il mondo esterno cambia continuamente.