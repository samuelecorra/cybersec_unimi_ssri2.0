# **Lezione 1: Fasi di sviluppo del software**

---

### **1. Panoramica della lezione**

Questa lezione introduce il **ciclo di vita del software** e le **fasi di sviluppo** che caratterizzano il processo di produzione di un sistema software.

L’obiettivo principale è comprendere:

- cosa si intende per **processo di produzione del software**,
    
- cosa si intende per **ciclo di vita**,
    
- quali sono le **attività fondamentali** che compongono lo sviluppo,
    
- quali documenti vengono prodotti in ogni fase.
    

Questi concetti sono fondamentali perché la sicurezza del software non può essere progettata correttamente se non si comprende come nasce e evolve il sistema durante il suo ciclo di vita.

---

### **2. Definizioni fondamentali**

#### **2.1 Processo di produzione**

Il **processo di produzione del software** è definito come:

> la sequenza di attività svolte per progettare, realizzare, consegnare e modificare un prodotto software.

Questa definizione evidenzia che lo sviluppo non termina con la consegna, ma include anche le modifiche successive.

#### **2.2 Ciclo di vita del software**

Il **ciclo di vita del software** è:

> l’insieme degli stadi in cui il sistema viene a trovarsi nel tempo.

Include quindi tutte le fasi dall’idea iniziale fino alla manutenzione e all’evoluzione del sistema.

#### **2.3 Modelli di processo**

I **modelli di processo** determinano:

> l’organizzazione delle diverse fasi di sviluppo.

Non cambiano le attività fondamentali, ma cambiano:

- il loro ordine,
    
- il modo in cui vengono eseguite,
    
- il grado di iterazione tra le fasi.
    

---

### **3. Fasi di sviluppo del software**

Le attività del processo di sviluppo sono organizzate nelle seguenti fasi principali:

1. Studio di fattibilità
    
2. Specifica (analisi dei requisiti)
    
3. Progettazione (design)
    
4. Implementazione e test dei moduli
    
5. Integrazione e test del sistema
    
6. Consegna
    
7. Manutenzione
    

Ogni fase ha obiettivi specifici e produce documenti precisi.

---

### **4. Studio di fattibilità**

#### **4.1 Obiettivo**

Lo scopo dello **studio di fattibilità** è produrre un documento chiamato:

> **Feasibility Study Document (FSD)**

che valuta:

- costi,
    
- benefici,
    
- opportunità dell’applicazione proposta.
    

Questa fase serve a decidere se il progetto è conveniente e realizzabile.

#### **4.2 Contenuto del FSD**

Il documento deve contenere:

- la **definizione del problema**,
    
- le **possibili soluzioni** con relative motivazioni,
    
- per ciascuna soluzione:
    
    - stima dei benefici,
        
    - stima dei costi,
        
    - risorse richieste,
        
    - tempi di consegna.
        

Questa fase è cruciale per evitare investimenti inutili o progetti non sostenibili.

---

### **5. Specifica (analisi dei requisiti)**

#### **5.1 Scopo della fase**

La fase di **specifica**, detta anche **analisi dei requisiti**, ha lo scopo di determinare:

- funzionalità del software,
    
- proprietà del sistema,
    
- qualità richieste.
    

Tra le qualità considerate:

- performance,
    
- facilità d’uso,
    
- portabilità,
    
- facilità di manutenzione.
    

È una fase estremamente importante perché errori qui si propagano in tutte le fasi successive.

#### **5.2 Documento prodotto**

La fase produce il:

> **RASD — Requirements Analysis and Specification Document**

---

### **6. Attività necessarie nella specifica**

Per svolgere correttamente la fase di specifica è necessario:

#### **6.1 Comprendere l’interfaccia con l’ambiente esterno**

Bisogna capire:

- come il sistema interagisce con utenti,
    
- altri sistemi,
    
- hardware,
    
- contesto operativo.
    

#### **6.2 Comprendere il dominio applicativo**

Il dominio è il contesto reale in cui il software opera.

Una scarsa comprensione del dominio porta a requisiti errati.

#### **6.3 Identificare gli stakeholder**

Gli **stakeholder** sono tutti i soggetti interessati al sistema:

- utenti finali,
    
- clienti,
    
- sviluppatori,
    
- manutentori,
    
- organizzazioni coinvolte.
    

Stakeholder diversi possono avere:

- obiettivi diversi,
    
- aspettative diverse,
    
- visioni differenti del sistema.
    

La specifica deve quindi:

> integrare e riconciliare i diversi punti di vista.

#### **6.4 Stabilire chiaramente le qualità richieste**

Devono essere definite in modo esplicito:

- qualità funzionali,
    
- qualità non funzionali,
    
- vincoli operativi.
    

---

### **7. La specifica come processo incrementale**

La specifica non è un’attività svolta una sola volta.

È:

> un processo incrementale che richiede continua interazione con il cliente.

Questo significa che i requisiti vengono:

- raffinati,
    
- chiariti,
    
- corretti,
    
- negoziati nel tempo.
    

---

### **8. Le 5W come guida alla specifica**

La lezione propone la tecnica delle **5W** come guida per produrre una buona specifica.

#### **8.1 Dominio**

- **Who will use the system**  
    Chi sono gli utenti interessati.
    
- **Why should it be developed / why will the users use it**  
    Quali sono:
    
    - obiettivi,
        
    - aspettative degli utenti,
        
    - entità del dominio,
        
    - influenza dell’applicazione su tali entità.
        

#### **8.2 Requisiti funzionali**

- **What (vs How) will it provide**
    

Bisogna descrivere:

- cosa deve fare il sistema,
    
- non come deve farlo.
    

Questa distinzione è fondamentale: l’analisi definisce il comportamento, non l’implementazione.

#### **8.3 Requisiti non funzionali**

- **Where will it be used, on which architecture**
    

Descrivono aspetti come:

- affidabilità,
    
- disponibilità,
    
- integrità,
    
- sicurezza,
    
- prestazioni,
    
- interfacce,
    
- limiti operativi,
    
- limiti fisici.
    

#### **8.4 Requisiti di processo e manutenzione**

- **When and how long will it be used**
    

Riguardano:

- procedure di controllo qualità,
    
- priorità di sviluppo,
    
- possibili cambiamenti futuri,
    
- durata del sistema.
    

---

### **9. Proprietà del RASD**

Il documento RASD deve:

- permettere al cliente di verificare le caratteristiche specificate,
    
- consentire al progettista di sviluppare l’architettura.
    

Le proprietà richieste sono:

- facilmente comprensibile,
    
- preciso,
    
- completo,
    
- coerente,
    
- non ambiguo,
    
- modificabile.
    

#### **9.1 Contenuti aggiuntivi consigliati**

Il RASD dovrebbe includere:

- un **manuale utente preliminare**,
    
- un **system test plan**, cioè:
    
    - definizione delle modalità di test del sistema.
        

---

### **10. Progettazione (Design)**

#### **10.1 Obiettivo della fase**

Lo scopo della progettazione è produrre un documento che descriva:

- l’architettura del software,
    
- la struttura globale,
    
- i singoli moduli.
    

La fase produce il:

> **Design Document**

#### **10.2 Contenuti del Design Document**

Il documento deve includere:

- definizione delle componenti (moduli),
    
- relazioni tra componenti,
    
- interazioni tra componenti.
    

#### **10.3 Obiettivi della progettazione**

Due obiettivi fondamentali sono:

- sviluppo concorrente,
    
- separazione delle responsabilità.
    

Questi obiettivi migliorano:

- organizzazione del lavoro,
    
- manutenibilità,
    
- qualità del sistema.
    

---

### **11. Implementazione e test dei moduli**

Questa fase riguarda la realizzazione concreta del software.

I programmatori:

- scrivono il codice,
    
- effettuano test sui singoli moduli.
    

Per ogni componente vengono prodotti:

- codifica,
    
- documentazione,
    
- specifica dei test effettuati.
    

Questa fase è spesso chiamata **unit testing**.

---

### **12. Integrazione e test del sistema**

Lo scopo della fase è:

- assemblare il codice prodotto dai diversi gruppi,
    
- verificare la compatibilità tra moduli,
    
- risolvere errori dovuti alle interazioni.
    

Un punto importante:

> questa fase non sempre viene considerata distinta dall’implementazione.

In molti progetti, infatti, implementazione e integrazione sono strettamente intrecciate.

---

### **13. Consegna**

La fase di consegna consiste nella distribuzione del sistema agli utenti.

Gli utenti:

- verificano il funzionamento,
    
- individuano malfunzionamenti,
    
- segnalano differenze rispetto alle specifiche.
    

La consegna avviene in due momenti principali.

#### **13.1 Beta testing**

Il software viene distribuito a un gruppo selezionato di utenti per:

- test in condizioni reali,
    
- individuazione di errori.
    

Gli errori vengono corretti prima del rilascio definitivo.

#### **13.2 Distribution**

Il software viene rilasciato definitivamente.

Gli errori successivi vengono corretti:

- nelle versioni successive,
    
- tramite patch.
    

---

### **14. Distribuzione del carico di lavoro**

Uno studio su 125 progetti HP mostra una distribuzione media del lavoro:

- 18% analisi requisiti e specifica
    
- 19% design
    
- 34% codifica
    
- 29% testing
    

Sono possibili variazioni del ±10%.

Questo dato evidenzia che:

- la codifica non è la fase dominante,
    
- testing e analisi hanno un peso molto rilevante.
    

---

### **15. Manutenzione**

#### **15.1 Definizione**

La manutenzione include:

> tutti i cambiamenti (correzione + evoluzione) successivi alla distribuzione.

È spesso la fase più costosa.

La lezione riporta che:

- può superare il 50% del costo totale del software,
    
- circa l’80% del budget IT è speso per il mantenimento.
    

#### **15.2 Classificazione della manutenzione**

- **Manutenzione correttiva** ≈ 20%  
    Correzione degli errori.
    
- **Manutenzione adattativa** ≈ 20%  
    Adattamento a cambiamenti dell’ambiente.
    
- **Manutenzione perfettiva** ≈ 50%  
    Miglioramento delle funzionalità e delle prestazioni.
    

---

### **16. Sintesi della lezione**

In questa lezione abbiamo visto:

- le fasi in cui è organizzato lo sviluppo del software,
    
- il concetto di ciclo di vita del software.
    

Concetti fondamentali:

- ogni fase ha finalità precise,
    
- ogni fase termina con un documento specifico,
    
- il processo continua anche dopo la consegna.
    

---

### **17. Prossimi passi**

Nella prossima lezione verranno analizzati:

> i diversi modi di organizzare le fasi di sviluppo.

Questi diversi modi daranno origine ai:

> **modelli di ciclo di vita del software.**