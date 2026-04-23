In questa lezione introdurremo un altro importante standard relativo ai sistemi informativi e alla sicurezza logica ovvero il NIST Cyber Security Framework.


---
## Cos’è il NIST

Il **National Institute of Standards and Technology (NIST)** è un’agenzia federale degli Stati Uniti, incardinata nel **Dipartimento del Commercio**. La sua missione è **sostenere la competitività economica** collaborando con l’industria per **definire standard, tecnologie e metodologie** che rendano più efficiente e sicura la produzione di beni e l’erogazione di servizi.

![Pasted image 20250912200001.png](../../imgs/Pasted%20image%2020250912200001.png)

## Perché nasce (contesto storico)

Il NIST viene fondato **nel 1901** per risolvere un problema critico dell’epoca: negli USA l’**infrastruttura di misurazione** (strumenti, unità, tarature, metodi di prova) era **inferiore** rispetto a Paesi come **Regno Unito e Germania**. Senza misure affidabili non esiste qualità né scambio equo: non puoi garantire tolleranze meccaniche, purezza dei materiali, potenza elettrica reale, ecc. Migliorare la metrologia significava **abilitare l’industria** a competere ad armi pari.

**Idea chiave:** _standard di misura solidi → processi ripetibili → prodotti intercambiabili → mercati che funzionano_.

## Cosa fa oggi (portata e impatto)

Nel tempo il NIST è diventato uno dei **laboratori di scienze fisiche** più autorevoli al mondo. Le sue attività **sorreggono tecnologie a tutte le scale**:

- **Nanoscala**: dispositivi e materiali dove contano grandezze piccolissime e precisione estrema.
    
- **Ingegneria civile**: metodologie e standard che contribuiscono a progettare edifici e **grattacieli resistenti ai terremoti**.
    
- **Reti e comunicazioni globali**: specifiche e misure che rendono **interoperabili** sistemi e protocolli.
    

Tradotto: il NIST **riduce l’ambiguità tecnica** mettendo d’accordo tutti su _come misurare, testare, confrontare_.

## Collegamento con la cybersecurity (ponte per la lezione)

Lo stesso approccio “misure + metodi + standard” viene applicato anche alla **sicurezza informatica**. Da qui nascono linee guida strutturate come il **NIST Cybersecurity Framework (CSF)**: un modo condiviso per **definire risultati attesi**, **valutare i rischi** e **migliorare continuamente** i processi di sicurezza nelle organizzazioni.

---

## Mini-glossario operativo

- **Infrastruttura di misurazione**: insieme di unità, strumenti, campioni, procedure e laboratori che assicurano **misure tracciabili e comparabili** tra organizzazioni e Paesi.
    
- **Standard**: specifiche concordate che definiscono _come_ fare o misurare qualcosa (riduce ambiguità).
    
- **Metodologie**: **processi** per applicare gli standard in modo ripetibile (passi, ruoli, controlli).
    
- **Competitività**: capacità di un sistema produttivo di **offrire qualità e innovazione** con costi sostenibili; senza standard condivisi, la qualità non è verificabile.


---
# NIST Cybersecurity Framework (CSF)

![Pasted image 20250912200053.png](../../imgs/Pasted%20image%2020250912200053.png)

## Che cos’è

Il **NIST Cybersecurity Framework (CSF)** è un insieme di **linee guida** pensate per aiutare le organizzazioni a **mitigare i rischi di sicurezza informatica**. Non è una legge, né una certificazione obbligatoria, ma uno **strumento operativo** sviluppato sulla base di standard e best practice già esistenti a livello internazionale.

Il CSF ha due caratteristiche fondamentali:

1. **Tassonomia di alto livello**: cioè una classificazione condivisa dei risultati attesi in termini di sicurezza informatica (es. “i dati devono essere protetti anche quando non sono in uso”).
    
2. **Metodologia di gestione**: fornisce un modo strutturato per **valutare e migliorare** i risultati, in un’ottica di gestione del rischio.
    

Un aspetto importante: il Framework integra anche **linee guida sulla privacy e sulle libertà civili**, riconoscendo che la sicurezza non può limitarsi alla protezione tecnica, ma deve anche rispettare i diritti delle persone.

---

## Struttura del Framework

Il CSF si divide in **tre parti principali**:

1. **Framework Core**
    
    - Il cuore del modello.
        
    - Descrive le **funzioni fondamentali** della sicurezza informatica, le **categorie** e le **sottocategorie** dei risultati attesi, con i riferimenti agli standard di settore.
        
    - Funziona come una mappa: dice _cosa bisogna ottenere_, senza prescrivere nel dettaglio _come_.
        
2. **Framework Implementation Tiers (Livelli di implementazione)**
    
    - Servono a **misurare la maturità** dell’organizzazione nella gestione della cybersecurity.
        
    - Vanno da pratiche di gestione **minimali e reattive (Tier 1)** a pratiche **adattive e integrate (Tier 4)**.
        
    - Aiutano a capire **quanto** un’organizzazione è consapevole e strutturata nel trattare i rischi.
        
3. **Framework Profiles (Profili)**
    
    - Sono una “fotografia” dello stato della sicurezza di un’organizzazione in un dato momento.
        
    - Consentono di confrontare lo **stato attuale (profilo corrente)** con lo **stato desiderato (profilo target)**.
        
    - Aiutano a definire **priorità di miglioramento** e a monitorare i progressi.
        

---

## Perché è utile

Il CSF rappresenta un linguaggio comune tra **manager, tecnici e stakeholder esterni**.

- I manager possono capire e pianificare le risorse.
    
- I tecnici possono tradurre le funzioni in controlli concreti.
    
- Gli stakeholder esterni (es. fornitori, clienti, enti regolatori) trovano un punto di riferimento condiviso.
    

In questo modo, l’organizzazione gestisce la sicurezza in maniera **coerente, misurabile e comunicabile**.


---
# Framework Core

## Definizione

Il **Framework Core** rappresenta l’insieme delle **attività di sicurezza informatica**, dei **risultati desiderati** e dei **riferimenti applicabili** comuni a tutti i settori delle infrastrutture critiche.  
Il suo obiettivo è fornire un **linguaggio condiviso** che permetta di comunicare attività e risultati in tutta l’organizzazione, dai livelli manageriali fino a quelli operativi.

## Le cinque funzioni fondamentali

Il Core è strutturato attorno a **cinque funzioni concorrenti e continue**, che costituiscono il ciclo vitale della gestione della sicurezza informatica:

1. **Identify (Identificare)**  
    Comprendere risorse, contesto aziendale e rischi.  
    Esempio: mappare gli asset IT, valutare dipendenze critiche, gestire la supply chain.
    
2. **Protect (Proteggere)**  
    Implementare salvaguardie per limitare o contenere impatti.  
    Esempio: controllo accessi, cifratura dei dati, formazione del personale.
    
3. **Detect (Rilevare)**  
    Riconoscere tempestivamente eventi di sicurezza.  
    Esempio: sistemi di monitoraggio continuo, gestione anomalie, log analysis.
    
4. **Respond (Rispondere)**  
    Reagire agli incidenti per mitigarne gli effetti.  
    Esempio: piani di risposta, comunicazione interna ed esterna, azioni correttive.
    
5. **Recover (Recuperare)**  
    Ripristinare servizi e resilienza post-incidente.  
    Esempio: disaster recovery, business continuity, comunicazioni di ripristino.
    

Queste funzioni sono **iterative e integrate**: il processo non si ferma mai, ma alimenta un ciclo di **miglioramento continuo (PDCA: Plan–Do–Check–Act)**.

---

## Categorie e sottocategorie

- Ogni **Funzione** si articola in **Categorie**, che raggruppano risultati coerenti.
    
    - Esempi: Gestione degli asset (Asset Management), Controllo degli accessi, Monitoraggio continuo.
        
- Ogni **Categoria** è ulteriormente suddivisa in **Sottocategorie**, che descrivono **risultati specifici** attesi.
    
    - Esempi: “I dati inattivi sono protetti”, “I sistemi esterni sono catalogati”, “Le notifiche degli IDS vengono esaminate”.
        
- Infine, a ogni sottocategoria sono associati **Riferimenti informativi**: standard e linee guida (COBIT, ISO/IEC 27001, NIST SP 800-53, ecc.) che spiegano _come_ realizzare i risultati attesi.
    

---

## Commento alla figura allegata

La figura è una **mappa visiva del Framework Core**:

![Pasted image 20250912202420.png](../../imgs/Pasted%20image%2020250912202420.png)

- A sinistra troviamo le **5 Funzioni** (colorate: blu, viola, giallo, rosso, verde).
    
- Ogni funzione è suddivisa in **Categorie** (es. ID.AM = Asset Management, PR.AC = Access Control, DE.AE = Anomalies and Events).
    
- A destra troviamo esempi di **Sottocategorie** (es. ID.BE-1: il ruolo dell’organizzazione nella supply chain è identificato e comunicato).
    
- Accanto alle sottocategorie ci sono i **Riferimenti informativi**: normative e standard a cui si può far riferimento per implementare i controlli (COBIT, ISO 27001, NIST SP 800-53, ecc.).
    

In sintesi, il Core funziona come una **tabella di corrispondenze**:

- **Cosa fare (Funzioni → Categorie → Sottocategorie)**
    
- **Come farlo (Riferimenti informativi)**
    

Questa struttura consente di **collegare teoria e pratica**, mantenendo un approccio modulare e adattabile ai diversi settori.


---
# Framework Implementation Tiers

## Definizione

I **Tier** sono livelli che descrivono **quanto un’organizzazione è matura** nella gestione del rischio di sicurezza informatica.  
Servono a fornire un **contesto pratico**: non dicono _cosa fare_ (questo lo fa il Framework Core), ma _quanto bene_ e _con quale sistematicità_ l’organizzazione gestisce i rischi.

In altre parole: il Tier è una misura del **grado di formalizzazione e integrazione** delle pratiche di cybersecurity.

---

## I quattro livelli

1. **Tier 1 – Gestione del rischio “Parziale”**
    
    - Il rischio è gestito in modo **ad hoc**, reattivo, non strutturato.
        
    - La consapevolezza è minima, dipende dalle singole persone.
        
    - L’organizzazione non ha ancora un ruolo definito nell’ecosistema più ampio (es. fornitori, clienti, partner).
        
2. **Tier 2 – Gestione del rischio “Consapevole”**
    
    - Esiste una **consapevolezza a livello organizzativo**, ma non un approccio uniforme.
        
    - Le pratiche sono approvate da qualche livello di management, ma non ancora consolidate come policy aziendale.
        
    - L’organizzazione inizia a capire il proprio ruolo in relazioni di dipendenza/fornitura, ma solo parzialmente.
        
3. **Tier 3 – Gestione del rischio “Ripetibile”**
    
    - Le pratiche sono **formalizzate e documentate come policy aziendali**.
        
    - Esiste un approccio organizzativo coerente, con procedure definite e riesaminate.
        
    - L’organizzazione comprende bene il proprio ruolo nei rapporti con partner esterni e contribuisce alla diffusione della consapevolezza dei rischi.
        
4. **Tier 4 – Gestione del rischio “Adattativo”**
    
    - Le pratiche non solo sono formalizzate, ma vengono **adattate dinamicamente** in base a nuove minacce, lezioni apprese e indicatori predittivi.
        
    - Si utilizzano informazioni proattive e strumenti avanzati per anticipare rischi futuri.
        
    - L’organizzazione collabora attivamente con l’ecosistema esterno, contribuendo a una più ampia comprensione del rischio nella comunità.
        

---

## Elementi di valutazione

La valutazione del Tier si basa su **tre parametri principali**:

- **Processo di gestione del rischio**: quanto è formalizzato e consolidato il ciclo di risk management.
    
- **Programma integrato di gestione del rischio**: quanto il rischio è gestito come parte integrante delle attività aziendali e strategiche.
    
- **Partecipazione esterna**: quanto l’organizzazione riconosce il proprio ruolo nell’ecosistema di relazioni con fornitori, clienti e partner, e partecipa attivamente ad esso.
    

---

## Commento alla figura

La figura mostra i **quattro Tier** come una progressione:

![Pasted image 20250912202616.png](../../imgs/Pasted%20image%2020250912202616.png)
![Pasted image 20250912202627.png](../../imgs/Pasted%20image%2020250912202627.png)
- **Tier 1 (Partial)** a sinistra → approccio reattivo e informale.
    
- **Tier 2 (Risk Informed)** → più consapevolezza, ma ancora poco sistematico.
    
- **Tier 3 (Repeatable)** → processi consolidati e ripetibili.
    
- **Tier 4 (Adaptive)** → organizzazione resiliente e capace di adattarsi dinamicamente.
    

Le tre frecce orizzontali (Risk Management Process, Integrated Risk Management Program, External Participation) rappresentano i **criteri di valutazione**: mano a mano che si passa da Tier 1 a Tier 4, questi elementi diventano più robusti, integrati e maturi.

In sintesi: il grafico mostra un **percorso evolutivo** dalla gestione minimale alla gestione proattiva e adattiva della sicurezza.


---
# Valutazione dei livelli di implementazione

![Pasted image 20250912202805.png](../../imgs/Pasted%20image%2020250912202805.png)

## Logica generale

La tabella evidenzia che ogni **Tier** viene valutato rispetto a tre parametri chiave:

1. **Processo di gestione del rischio** → quanto è formalizzato e strutturato.
    
2. **Programma integrato di gestione del rischio** → quanto è diffuso e coerente a livello aziendale.
    
3. **Partecipazione esterna** → quanto l’organizzazione si inserisce nell’ecosistema più ampio (clienti, fornitori, partner).
    

Man mano che si sale di Tier, i requisiti diventano **più stringenti, integrati e maturi**.

---

## Analisi dei Tier

### **Tier 1 – Parziale**

- **Processo di gestione del rischio:** non formalizzato, approccio ad hoc, spesso reattivo.
    
- **Programma integrato:** la consapevolezza del rischio è limitata e frammentata.
    
- **Partecipazione esterna:** l’organizzazione non riconosce il proprio ruolo nell’ecosistema più ampio.  
    👉 È il livello “minimo”, dove la sicurezza dipende da iniziative isolate e non da una strategia.
    

---

### **Tier 2 – Consapevole**

- **Processo:** le pratiche sono approvate dalla direzione, ma non ancora consolidate come policy aziendali.
    
- **Programma integrato:** c’è una consapevolezza diffusa, ma manca un approccio uniforme e sistematico.
    
- **Partecipazione esterna:** l’organizzazione capisce il proprio ruolo, ma solo in parte (es. comprende i rapporti di dipendenza, ma non quelli di fornitura o viceversa).  
    👉 È una fase “intermedia”: la sicurezza è percepita, ma ancora frammentata.
    

---

### **Tier 3 – Ripetibile**

- **Processo:** le pratiche sono formalmente approvate e definite come policy.
    
- **Programma integrato:** l’organizzazione ha un approccio chiaro, con procedure definite, implementate e periodicamente riesaminate.
    
- **Partecipazione esterna:** l’organizzazione comprende appieno i rapporti di dipendenza e fornitura, contribuendo alla consapevolezza collettiva dei rischi.  
    👉 È un livello “maturo”: la gestione del rischio diventa **ripetibile e standardizzata**.
    

---

### **Tier 4 – Adattativo**

- **Processo:** l’organizzazione adatta le proprie pratiche in modo dinamico, basandosi su esperienze passate e indicatori predittivi.
    
- **Programma integrato:** gestione del rischio evoluta, che utilizza lezioni apprese e modelli proattivi per anticipare eventi futuri.
    
- **Partecipazione esterna:** l’organizzazione collabora attivamente con l’ecosistema, contribuendo a migliorare la resilienza collettiva.  
    👉 È il livello “eccellente”: la sicurezza non è solo difesa, ma diventa **capacità di adattamento continuo**.
    

---

## Commento al grafico allegato

![Pasted image 20250912202810.png](../../imgs/Pasted%20image%2020250912202810.png)

Il grafico sintetizza bene questa progressione:

- A sinistra (Tier 1) troviamo approcci **informali e isolati**.
    
- A destra (Tier 4) troviamo pratiche **integrate, dinamiche e collaborative**.
    
- Le tre frecce (Risk Management Process, Integrated Program, External Participation) mostrano chiaramente che **ogni parametro evolve** con il passaggio da un Tier all’altro.
    

È una sorta di **scala di maturità organizzativa**: più sali, più la gestione del rischio diventa **strategica, sistemica e interconnessa**.


---
# Framework Profile

## Definizione

I **Profili** sono come una **fotografia** dello stato di sicurezza informatica di un’organizzazione in un dato momento o scenario.  
Mettono in relazione:

- **Standard**,
    
- **Linee guida**,
    
- **Pratiche operative**
    

con le funzioni, le categorie e le sottocategorie del **Framework Core**.

Il risultato è una mappa che mostra dove l’organizzazione **si trova oggi** (profilo corrente) e dove **vorrebbe arrivare** (profilo target).

---

## Cosa contengono i Profili

Un **Framework Profile** racchiude tre elementi fondamentali:

1. **Obiettivi organizzativi legati al business** → es. garantire continuità dei servizi, rispettare la normativa, tutelare la reputazione.
    
2. **Propensione al rischio dell’organizzazione** → quanto l’organizzazione è disposta a tollerare rischi, in relazione alle minacce ambientali (alto rischio ≠ sempre negativo, dipende dal contesto).
    
3. **Requisiti e risorse** → ciò che serve per raggiungere i risultati desiderati del Framework Core (competenze, budget, tecnologie, processi).
    

---

## Funzione pratica

I Profili permettono di:

- **Confrontare** il profilo corrente con il profilo target.
    
- **Individuare le priorità** di intervento (gap analysis).
    
- **Misurare i progressi** nel tempo.
    
- Supportare processi di **autovalutazione** interna o di **comunicazione** verso clienti, partner o autorità.
    

---

## Come si sviluppa un profilo

1. **Revisione delle Categorie e Sottocategorie** del Framework Core.
    
2. **Scelta di quelle più rilevanti** per il business, la missione e il rischio dell’organizzazione.
    
3. Eventuale **aggiunta di nuove categorie** se necessario.
    
4. **Definizione del profilo corrente** (stato attuale).
    
5. **Definizione del profilo target** (stato desiderato).
    
6. Pianificazione delle azioni per colmare il gap (tenendo conto anche di vincoli come costi e innovazione).
    

---

## Commento al grafico 

![Pasted image 20250912202942.png](../../imgs/Pasted%20image%2020250912202942.png)

La figura rappresenta bene il concetto:

- In alto: **Business Objectives**, **Threat Environment** e **Requirements & Controls** → i tre input fondamentali.
    
- In basso: la sintesi in un **Cybersecurity Profile**, che integra i cinque pilastri del Framework (Identify, Protect, Detect, Respond, Recover).
    

Il messaggio chiave è che il **Profilo** non è un elenco teorico, ma un documento pratico che mette in relazione:

- **Cosa vuole l’organizzazione (obiettivi)**,
    
- **Quali minacce deve affrontare**,
    
- **Quali controlli e requisiti servono**.


---
Perfetto 🚀, ora entriamo nell’**esempio di applicazione dei Profili**.

---

# Esempio di applicazione dei Framework Profiles

## Scenario

Un’organizzazione analizza la funzione **Identify** del Framework Core, e in particolare la categoria **Asset Management (ID.AM)**.  
Qui l’obiettivo è sapere **quali asset informativi** possiede, chi li gestisce e come sono protetti.

Per gestire questa funzione, l’organizzazione può sviluppare **più profili progressivi** (Low, Medium, High), che rappresentano il percorso di maturità dal **profilo corrente** al **profilo target**.

![Pasted image 20250912203139.png](../../imgs/Pasted%20image%2020250912203139.png)

---

## I tre livelli dell’esempio

1. **Situazione attuale (Profilo corrente – Low)**
    
    - L’organizzazione conosce solo in maniera parziale i propri asset.
        
    - Non esistono procedure formali per la loro gestione.
        
    - I responsabili non sono chiaramente identificati.
        
2. **Target 1 (Profilo intermedio – Medium)**
    
    - Si stabilisce **chi è responsabile** di ogni sistema o servizio critico.
        
    - Inizia un processo di gestione più ordinato e formalizzato.
        
    - Viene creata documentazione che collega asset → responsabili → processi.
        
3. **Target 2 (Profilo avanzato – High)**
    
    - L’organizzazione si dota di **strumenti automatici** per rilevare asset non autorizzati o anomali (es. software di inventory, sistemi di scansione).
        
    - La gestione diventa **proattiva**, riducendo il rischio che hardware o software non autorizzati entrino nel sistema informativo.
        
    - La resilienza aumenta sensibilmente.
        

---

## Interpretazione didattica

Questo esempio mostra bene la logica dei Profili:

- **Dal profilo corrente al target**: il gap viene identificato e colmato passo dopo passo.
    
- Ogni profilo intermedio rappresenta un obiettivo concreto e raggiungibile.
    
- La crescita non è solo tecnica (strumenti automatici), ma anche **organizzativa** (assegnazione responsabilità) e **procedurale** (documentazione).
    

---

## Commento al grafico

![Pasted image 20250912203102.png](../../imgs/Pasted%20image%2020250912203102.png)

- **Function:** Identify (ID) → Funzione fondamentale del Framework Core.
    
- **Category:** Asset Management (ID.AM).
    
- **Subcategory:** ID.AM-1, ID.AM-2, ecc. → Obiettivi specifici di gestione degli asset.
    
- **Manufacturing Profile:** qui vengono descritte le pratiche concrete in tre varianti di maturità (**Low, Moderate, High**).
    
- **Reference:** standard e controlli a cui la sottocategoria fa riferimento (es. ISO, NIST SP, COBIT).
    

---

## Analisi delle sottocategorie

### **ID.AM-1** – Inventario dei componenti hardware

- **Low (Situazione attuale):** l’organizzazione documenta un inventario dei componenti del sistema produttivo (PLC, router, attuatori, firmware, ecc.), ma lo fa in modo **statico e manuale**. L’inventario è aggiornato dall’organizzazione ma resta basilare.
    
- **Moderate (Target 1):** vengono identificati **i responsabili** per ogni componente, con ruoli chiari di responsabilità e accountability.
    
- **High (Target 2):** si adottano **meccanismi automatici** per rilevare la presenza di hardware/firmware non autorizzati. → massima maturità, perché l’inventario diventa **dinamico e proattivo**.
    

---

### **ID.AM-2** – Inventario dei componenti software

- **Low (Situazione attuale):** viene documentato un inventario dei componenti software (licenze, versioni, applicazioni ICS, sistemi operativi, HMI, ecc.), ma anche qui in forma **manuale e statica**.
    
- **Moderate (Target 1):** l’inventario software viene **aggiornato come parte integrante** della gestione di installazioni, aggiornamenti e rimozioni. Vengono inoltre identificati i responsabili per ogni componente.
    
- **High (Target 2):** si adottano strumenti **automatici** per rilevare la presenza di software non autorizzato.
    

---

## Commento didattico

- **La colonna "Manufacturing Profile" è il cuore della tabella**: non sono tre colonne diverse, ma tre **livelli di maturità** (Low → Moderate → High) descritti per ogni sottocategoria.
    
- Questo mostra come i **Framework Profiles** permettano di rappresentare la crescita da uno stato **basilare/manuale** a uno stato **avanzato/automatico**.
    
- La logica è incrementale:
    
    1. documentazione di base (Low),
        
    2. attribuzione di responsabilità e accountability (Moderate),
        
    3. automazione e proattività (High).


---
# Coordinamento dell’implementazione del Framework

## I tre livelli organizzativi

### 1. **Senior Executive Level** (livello direzionale/esecutivo)

- **Focus:** rischio organizzativo complessivo.
    
- **Azioni principali:**
    
    - Definisce le **priorità della missione**.
        
    - Approva la scelta del **Tier di implementazione**.
        
    - Prende le **decisioni strategiche sul rischio** (accettare, ridurre, trasferire).
        
- **Output:** comunica agli altri livelli la **tolleranza al rischio**, le **risorse disponibili** e gli **obiettivi strategici**.
    

---

### 2. **Business/Process Level** (livello aziendale/di processo)

- **Focus:** gestione del rischio delle **infrastrutture critiche** e dei processi di business.
    
- **Azioni principali:**
    
    - Traduce le priorità in **Profili** (current vs target).
        
    - Nomina i **Tier di implementazione** più adeguati.
        
    - Sviluppa piani concreti e **allinea il budget** alle esigenze di sicurezza.
        
- **Ruolo:** fa da **ponte** tra la direzione e l’implementazione, comunicando i bisogni aziendali e valutando l’impatto delle scelte.
    

---

### 3. **Implementation/Operations Level** (livello operativo/di implementazione)

- **Focus:** sicurezza delle **infrastrutture critiche** e applicazione concreta delle misure.
    
- **Azioni principali:**
    
    - **Implementa i Profili** sviluppati dal livello Business.
        
    - Monitora vulnerabilità, minacce e asset.
        
    - Riporta i **progressi di implementazione** e le variazioni di rischio.
        
- **Ruolo:** fornisce feedback costante ai livelli superiori sull’efficacia delle misure adottate.
    

---

## Flusso delle informazioni

- **Top-down (dall’alto verso il basso):**
    
    - Il livello esecutivo definisce priorità, budget e tolleranza al rischio.
        
    - Queste informazioni vengono trasmesse al livello Business, che le usa per sviluppare i Profili.
        
    - I Profili vengono comunicati al livello Operations per l’implementazione.
        
- **Bottom-up (dal basso verso l’alto):**
    
    - L’Operations segnala progressi e cambiamenti (nuove vulnerabilità, minacce emergenti).
        
    - Il Business valuta l’impatto sul processo aziendale e fa una revisione dei Profili.
        
    - I risultati di questa valutazione ritornano all’Executive Level per aggiornare la strategia complessiva.
        

---

## Commento alla figura

![Pasted image 20250912203413.png](../../imgs/Pasted%20image%2020250912203413.png)

Il diagramma mostra un **ciclo continuo**:

- In alto (blu) il **Risk Management**: priorità strategiche e decisioni sul rischio.
    
- Al centro (grigio) il **Business/Process Level**: traduzione in Profili e allocazione risorse.
    
- In basso (verde) l’**Implementation**: applicazione tecnica e monitoraggio.
    

Le frecce chiudono il cerchio: ogni livello comunica in entrambe le direzioni, garantendo **allineamento costante** tra strategia, processi e operazioni


---
# Supply Chain Risk Management (SCRM)

## Definizione

Il **Cyber SCRM** è l’insieme delle attività necessarie a gestire i **rischi di sicurezza informatica derivanti dai rapporti con soggetti esterni**.

- Considera sia l’effetto che **l’organizzazione ha sui partner esterni** (es. fornitori, clienti, terze parti).
    
- Sia l’effetto che **i partner esterni hanno sull’organizzazione** (es. vulnerabilità introdotte da un fornitore non sicuro).
    

In altre parole: la catena di fornitura è un **ecosistema interconnesso** e ogni anello può rappresentare un punto debole.

---

## L’ecosistema organizzativo

Lo schema della slide rappresenta le relazioni fondamentali:

- **Organization (al centro)**: l’azienda che deve proteggere le proprie infrastrutture.
    
- **Technology Supplier**: fornitori di soluzioni tecnologiche (hardware, software, servizi cloud).
    
- **Technology Buyer**: clienti o acquirenti di tecnologia dall’organizzazione.
    
- **Supplier / Buyer non tecnologici**: partner che forniscono o acquisiscono beni e servizi non strettamente IT, ma che comunque hanno un impatto (es. logistica, consulenze).
    

Questo mostra che la sicurezza non è mai isolata: un problema in un fornitore può **propagarsi** e colpire l’organizzazione o i suoi clienti.

---

## Attività tipiche del Cyber SCRM

1. **Definizione dei requisiti di sicurezza informatica** per i fornitori.
    
    - Es. standard minimi di crittografia, procedure di backup, controlli di accesso.
        
2. **Formalizzazione dei requisiti tramite accordi contrattuali.**
    
    - Clausole specifiche nei contratti che obbligano i fornitori a rispettare certe pratiche di sicurezza.
        
3. **Comunicazione delle modalità di verifica e validazione.**
    
    - Far sapere ai fornitori come verranno controllati i requisiti (audit, test, certificazioni).
        
4. **Verifica del rispetto dei requisiti.**
    
    - Audit periodici, penetration test, attestazioni di conformità.
        
5. **Governance e gestione del processo.**
    
    - Definizione di ruoli interni per la supervisione del SCRM.
        
    - Allineamento costante tra funzioni legali, acquisti, IT e sicurezza.
        

---

## Commento alla figura

Il diagramma evidenzia che l’organizzazione è **al centro di una rete di relazioni**:

- da un lato riceve prodotti e servizi (supplier),
    
- dall’altro li eroga a clienti (buyer).
    
- in entrambi i casi, la componente tecnologica e quella non tecnologica sono interdipendenti.
    

Questo implica che la **sicurezza deve essere trattata come requisito di filiera**, non solo interno.


---
# 🛡️ NIST Cybersecurity Framework — Riepilogo

## 1. **NIST**

- Agenzia USA (1901, Dept. of Commerce).
    
- Missione: sviluppare **standard, tecnologie, metodologie** per sostenere l’economia.
    
- Storico focus: **misurazioni affidabili** → competitività industriale.
    
- Oggi: da **nanotecnologie** a **reti globali**.
    
- In cybersecurity → linee guida strutturate per gestire i rischi.
    

---

## 2. **Cybersecurity Framework (CSF)**

- **Obiettivo:** mitigare rischi di sicurezza informatica organizzativa.
    
- **Caratteristiche:**
    
    - Tassonomia di alto livello dei risultati attesi.
        
    - Metodologia per valutare e migliorare i risultati.
        
    - Attenzione anche a **privacy e libertà civili**.
        
- **Struttura:**
    
    1. **Framework Core**
        
    2. **Implementation Tiers**
        
    3. **Profiles**
        

---

## 3. **Framework Core**

- **5 Funzioni fondamentali:**
    
    1. Identify 🔵
        
    2. Protect 🟣
        
    3. Detect 🟡
        
    4. Respond 🔴
        
    5. Recover 🟢
        
- Ogni funzione → **Categorie** (es. gestione asset, access control).
    
- Ogni categoria → **Sottocategorie** (risultati specifici, es. “dati inattivi protetti”).
    
- Ogni sottocategoria → **Riferimenti informativi** (es. ISO 27001, COBIT, NIST SP 800-53).
    
- Logica PDCA → miglioramento continuo.
    

---

## 4. **Implementation Tiers (Livelli)**

Valutano la **maturità organizzativa** su 3 parametri:

- Processo di gestione del rischio.
    
- Programma integrato.
    
- Partecipazione esterna.
    

**4 livelli:**

1. **Parziale** → ad hoc, reattivo, poca consapevolezza.
    
2. **Consapevole** → prime pratiche approvate, ma non uniformi.
    
3. **Ripetibile** → policy formalizzate, procedure consolidate e riviste.
    
4. **Adattativo** → pratiche dinamiche, basate su lezioni apprese e indicatori predittivi, collaborazione attiva con l’ecosistema.
    

---

## 5. **Framework Profiles**

- Sono “**fotografie**” dello stato di sicurezza:
    
    - **Profilo corrente** = situazione attuale.
        
    - **Profilo target** = stato desiderato.
        
- Contengono:
    
    - Obiettivi organizzativi (business).
        
    - Propensione al rischio.
        
    - Requisiti e risorse.
        
- Funzioni:
    
    - Supportano la **gap analysis**.
        
    - Aiutano a **prioritizzare azioni** e a **misurare i progressi**.
        
- Esempio: funzione _Identify_ →
    
    - Low = inventario manuale,
        
    - Moderate = responsabili chiari,
        
    - High = strumenti automatici.
        

---

## 6. **Coordinamento del Framework**

Tre livelli che comunicano in ciclo continuo:

- **Executive** → definisce missione, budget, risk appetite, approva Tier.
    
- **Business/Process** → sviluppa Profili, nomina Tier, alloca risorse.
    
- **Operations** → implementa controlli, monitora vulnerabilità, riporta progressi.  
    ➡️ Flusso **top-down** (strategie → implementazione) e **bottom-up** (feedback → strategie).
    

---

## 7. **Supply Chain Risk Management (SCRM)**

- Gestisce i rischi di sicurezza legati a **soggetti esterni**.
    
- Considera:
    
    - Effetto dell’organizzazione sui partner.
        
    - Effetto dei partner sull’organizzazione.
        
- Ecosistema: fornitori tecnologici e non, clienti, partner.
    
- Attività tipiche:
    
    - Definizione requisiti di sicurezza.
        
    - Formalizzazione contrattuale.
        
    - Comunicazione criteri di verifica.
        
    - Audit/verifiche periodiche.
        
    - Governance centralizzata.
        

---

✅ **Idea chiave della lezione:**  
Il NIST CSF non è un obbligo normativo ma uno **strumento pratico**:

- Crea un linguaggio comune tra direzione, business e IT.
    
- Consente di valutare lo stato di maturità.
    
- Supporta miglioramenti graduali e concreti, anche lungo tutta la supply chain.