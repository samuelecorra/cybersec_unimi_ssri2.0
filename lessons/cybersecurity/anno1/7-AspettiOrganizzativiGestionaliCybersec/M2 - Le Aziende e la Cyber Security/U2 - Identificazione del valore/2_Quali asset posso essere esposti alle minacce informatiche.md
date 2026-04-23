In questa lezione vedremo quali asset posso essere particolarmente esposti alle minacce informatiche in funzione del valore che rappresentano per un attaccante. Inoltre cercheremo di chiarire i vettori e le dinamiche di attacco.


---
## Gli asset aziendali in caso di attacco esterno

Gli asset aziendali, come abbiamo visto, possono diventare bersaglio di attacchi provenienti dall’esterno, mossi da diversi tipi di motivazioni e condotti con modalità differenti. Le principali categorie di attacco esterno sono due:

![Pasted image 20250910134727.png](../../imgs/Pasted%20image%2020250910134727.png)
### 1. Attacchi mirati e persistenti

Questi attacchi sono pianificati da organizzazioni criminali o terroristiche e hanno due possibili obiettivi:

- **Obiettivo economico**:
    
    - Gli attaccanti cercano di ottenere informazioni a valore economico.
        
    - I sistemi più colpiti sono i **sistemi amministrativi e gestionali** e i **sistemi di progettazione**, in quanto contengono dati finanziari, progetti, know-how aziendale.
        
    - L’accesso avviene spesso tramite phishing o tecniche che sfruttano la scarsa formazione e la distrazione del personale interno.
        
- **Obiettivo di interruzione del servizio o sabotaggio**:
    
    - L’attaccante mira a bloccare la produzione o arrecare danni al funzionamento aziendale.
        
    - I bersagli principali diventano i **sistemi amministrativi e gestionali**, i **portali web** e i **sistemi produttivi**.
        
    - Colpire i sistemi produttivi è più complesso, ma può avere effetti concreti anche a livello fisico (macchinari, impianti fermi).
        

### 2. Attacchi opportunistici

Sono gli attacchi più frequenti, caratterizzati da una logica veloce e orientata al guadagno immediato.

- **Obiettivo principale: economico**
    
    - Gli aggressori possono bloccare i **sistemi amministrativi e gestionali** attraverso ransomware, chiedendo un riscatto per il ripristino dei dati.
        
    - In alternativa, possono colpire i **portali web** con attacchi DoS, rendendoli temporaneamente inutilizzabili.
        

---

👉 In sintesi, la distinzione chiave è tra:

- attacchi **mirati e persistenti** → con finalità economiche o di sabotaggio, portati avanti da soggetti organizzati;
    
- attacchi **opportunistici** → più rapidi e diffusi, mirati a ottenere un guadagno economico immediato.


---
## Gli asset aziendali in caso di incidente interno

Gli incidenti informatici **interni** sono molto più frequenti degli attacchi esterni, anche se meno noti e meno pubblicizzati. In questo contesto possiamo distinguere due situazioni principali:

![Pasted image 20250910134826.png](../../imgs/Pasted%20image%2020250910134826.png)
### 1. Incidente legato ad un attacco interno

In questo caso il danno non è frutto del caso, ma è pianificato e può derivare da minacce interne o da compromissione di sistemi da parte di attori malevoli.

- **Obiettivo economico**
    
    - Target principali: **sistemi amministrativi e gestionali**, **sistemi di progettazione**.
        
    - Qui l’aggressore punta a rubare o compromettere dati con valore economico.
        
- **Obiettivo di interruzione del servizio**
    
    - Target principali: **sistemi amministrativi e gestionali**, **portali web**, **sistemi di produzione**.
        
    - Lo scopo è bloccare processi critici e generare un danno operativo all’azienda.
        

### 2. Incidente casuale

Si tratta di eventi non intenzionali, spesso causati da errori umani, guasti, malware, virus o altre circostanze accidentali.

- **Impatto maggiore sugli asset critici per la produzione**:
    
    - **sistemi amministrativi e gestionali**;
        
    - **sistemi di produzione**.
        
- Questi sistemi, essendo centrali per il funzionamento dell’azienda, sono quelli che risentono di più anche di un semplice malfunzionamento non pianificato.
    

---

👉 In sintesi, mentre negli attacchi esterni il nemico è “fuori dall’azienda”, negli incidenti interni il rischio nasce **dall’interno**, intenzionalmente o per caso, e colpisce comunque in modo privilegiato i sistemi centrali dell’amministrazione e della produzione.


---
# Le tipologie di S.I. nelle funzioni aziendali

![Pasted image 20250910135337.png](../../imgs/Pasted%20image%2020250910135337.png)
## Vendite

- **Sistemi principali**
    
    - **Portale web di procurement**: inserimento/gestione ordini da parte dei clienti o dei buyer. Dati trattati: anagrafiche clienti, carrelli/ordini, listini, scontistiche, stato ordini.
        
        
    - **CRM (Customer Relationship Management)**: pipeline commerciali, offerte/quotazioni, obiettivi, attività su clienti, reportistica.
        
        
- **Perché è critico**: è spesso **esposto su Internet** (portale) e contiene **dati personali e commerciali** ad alto valore. Un blocco o una violazione impatta direttamente **ricavi** e **reputazione**.
    

## Amministrazione

- **Sistema gestionale (ERP o modulo Finance)**: ordini di vendita/acquisto, **fatturazione**, **paghe**, contabilità, tesoreria. È il **cuore informativo** dell’azienda.
    
    
- **Perché è critico**: contiene **dati finanziari e payroll**, è essenziale per **ciclo attivo/passivo**; un fermo blocca **fatture**, **pagamenti** e **compliance**.
    

## Magazzino / Logistica

- **Gestionale di magazzino**: inventario, **scorte**, tracciamento lotti/seriali, **movimentazioni** (ingressi/uscite), ubicazioni, packing & shipping.
    
    
- **Perché è critico**: impatta **evasione ordini**, **accuratezza stock**, **lead time**; integra con vendite e produzione per disponibilità materiali.
    

## Ufficio Tecnico (Ingegneria / R&D)

- **CAD (Computer Aided Design)**: progettazione (disegni, modelli 2D/3D).
    
    
- **CAM (Computer Aided Manufacturing)**: genera programmi per macchine utensili/impianti; ponte tra progetto e produzione.
    
    
- **PLM (Product Lifecycle Management)**: piattaforma che **integra** progettazione e (spesso) produzione, governa versioni, BOM, change request.
    
    
- **Perché è critico**: custodisce **know-how**, **proprietà intellettuale**, distinte base e specifiche: un esfiltrazione equivale a **perdita di vantaggio competitivo**.
    

## Produzione (IT/OT)

- **MRP (Material Requirements Planning)**: calcola **fabbisogni materiali** in base agli ordini e alle distinte base.
    
    
- **MES (Manufacturing Execution Systems)**: collega ordini/ERP agli impianti, **lancia le lavorazioni**, traccia avanzamenti e qualità.
    
    
- **CMMS (Computerized Maintenance Management System)**: **pianifica e registra la manutenzione** su macchine/impianti.
    
    
- **SCADA (Supervisory Control And Data Acquisition)**: **supervisione e controllo** di impianti e linee (telemetria, comandi, allarmi).
    
    
- **Perché è critico**: impatta **continuità operativa** e **sicurezza fisica**; un fermo o un’alterazione dei parametri produttivi genera **scarti**, **ritardi** e **rischi**.
    

---

## Piattaforme “ombrello” e integrazioni chiave

- **ERP** (ENTERPRISE RESOURCE PLANNING): piattaforma che **raccoglie e integra** Amministrazione, Vendite, Magazzino/Logistica (e talvolta parti di Produzione). È lo **spina dorsale** transazionale.
    
    
- **PLM (PRODUCT LIFECYLCE MANAGEMENT)**: piattaforma che **connette** progettazione e (in varie forme) produzione, allineando versioni, distinte, change management.
    
    

**Flusso tipico semplificato (ordine → prodotto):**  
Ordine cliente (Portale/CRM) → ERP (ordini) → MRP (fabbisogni) → MES (esecuzione) ↔ SCADA (comando/supervisione) → feedback a ERP/MES; PLM fornisce CAD/BOM/varianti al MRP/MES. (Deriva dalla descrizione integrata di ERP, PLM, MRP, MES, SCADA nei reparti.)


---

## Perimetro di rischio (perché qui “fa male”)

- **Vendite/Portali, CRM**: esposti, quindi attaccabili da Internet; rischio **credential-stuffing**, blocchi DoS, data leak.
    
    
- **Finance/Gestionale**: dati **sensibili** e **paghe** ⇒ bersaglio di **ransomware** e frodi; bloccarlo ferma l’azienda.
    
    
- **Magazzino/Logistica**: alterare stock/ubicazioni compromette **evasione** e **pianificazione**.
    
    
- **Ufficio Tecnico (CAD/PLM/CAM)**: **IP leakage** e sabotaggi su file di progetto/programmi macchina.
    
    
- **Produzione (MES/SCADA/CMMS)**: è il confine **IT/OT**; un attacco qui provoca **fermi impianto** e potenziali **effetti fisici**.
    

---

# I vettori e le dinamiche di attacco

## Definizione

Il **vettore di attacco** è il veicolo attraverso il quale un aggressore trasporta uno o più strumenti malevoli per sfruttare **debolezze o vulnerabilità** presenti in un sistema.  
Non sempre si tratta di un attacco diretto “contro il firewall”: spesso l’aggressore sceglie percorsi più semplici, sfruttando **debolezze umane e tecnologiche**.

![Pasted image 20250910135548.png](../../imgs/Pasted%20image%2020250910135548.png)

---

## Esempi di vettori di attacco

- **Phishing via e-mail** → link fraudolenti o allegati malevoli che ingannano l’utente.
    
- **Applicazioni malevole** → app infette scaricate da store non sicuri o installate all’interno dell’organizzazione.
    
- **Chiavette USB compromesse** → un supporto rimovibile infettato può introdurre malware direttamente nei sistemi aziendali.
    
- **Botnet** → reti di computer infetti utilizzati come “esercito digitale” per lanciare attacchi distribuiti (DDoS, spam, brute force).
    

---

## Debolezze tecnologiche sfruttate

- **Vulnerabilità informatiche** dovute a mancati aggiornamenti software.
    
- **Gestione identità e permessi non adeguata** (account con privilegi eccessivi o non monitorati).
    
- **Meccanismi di autenticazione deboli** (assenza di MFA, autenticazioni obsolete).
    
- **Password deboli** e facilmente indovinabili.
    
- **Obsolescenza** di sistemi e dispositivi non più supportati dai vendor.
    

---

## Debolezze umane sfruttate

- **Mancanza di conoscenza** → utenti inconsapevoli di rischi e procedure.
    
- **Superficialità** → mancanza di attenzione nell’aprire allegati/link sospetti.
    
- **Mancanza di vigilanza** → sottovalutazione delle policy di sicurezza o delle buone pratiche quotidiane.
    

---

## Dinamica complessiva

1. **Il vettore** (e-mail, USB, app, botnet) veicola l’attacco.
    
2. **La debolezza** (tecnologica o umana) viene sfruttata.
    
3. **Il sistema aziendale** viene compromesso → aprendo la strada a furti di dati, blocchi operativi o sabotaggi.
    

---

👉 Concetto chiave: l’attaccante preferisce **la strada più facile**, cioè colpire l’anello debole della catena — spesso **l’essere umano** o un **software non aggiornato** — piuttosto che tentare di abbattere direttamente barriere perimetrali ben difese.


---
