# **Lezione 1: Ciclo di vulnerabilità**

---

### **1. Panoramica della lezione**

Questa lezione introduce il concetto di **vulnerabilità del software** attraverso l’analisi del cosiddetto **ciclo di vulnerabilità**, cioè la sequenza tipica di eventi che porta dalla scoperta di una vulnerabilità fino alla sua eliminazione.

Vengono inoltre analizzati:

- cosa si intende per attacco,
    
- le fasi che compongono un attacco,
    
- le modalità con cui un sistema può essere attaccato,
    
- la classificazione degli attacchi in base alla fase del ciclo di vita del software.
    

Un’idea fondamentale che emerge è che:

> le vulnerabilità e gli attacchi non sono eventi isolati, ma fenomeni ciclici e sistematici.

---

### **2. Il ciclo di vulnerabilità**

Il **ciclo di vulnerabilità** è definito come:

> una sequenza di eventi che si susseguono molto comunemente nel mondo della sicurezza, che va dalla determinazione alla eliminazione di una vulnerabilità del software.

Un concetto chiave:

> il ciclo di vulnerabilità è ortogonale al ciclo di vita del software.

Questo significa che:

- non coincide con le fasi di sviluppo,
    
- spesso si manifesta **dopo la distribuzione** del software.
    

Il diagramma della slide (pagina 1) rappresenta graficamente un ciclo continuo che parte dalla scoperta della vulnerabilità, passa attraverso sviluppo della patch e distribuzione, e ritorna alla scoperta di nuove vulnerabilità.

---

### **3. Sequenza degli eventi nel ciclo di vulnerabilità**

La lezione descrive una sequenza tipica composta da più fasi.

#### **3.1 Scoperta della vulnerabilità**

Viene individuata una nuova vulnerabilità in un componente software.

Questa scoperta può avvenire:

- da ricercatori,
    
- da utenti,
    
- da attaccanti,
    
- da sviluppatori.
    

#### **3.2 Analisi da parte degli attaccanti**

I soggetti malevoli (“i cattivi”) analizzano l’informazione e cercano di:

- sfruttare la vulnerabilità,
    
- lanciare attacchi contro sistemi o reti.
    

#### **3.3 Intervento dei difensori**

I difensori (“i buoni”) cercano una soluzione:

- analizzano la vulnerabilità,
    
- sviluppano una correzione,
    
- testano la soluzione in ambiente controllato,
    
- distribuiscono la patch.
    

#### **3.4 Diffusione mediatica**

Se la vulnerabilità è grave o gli attacchi sono rilevanti:

- i media diffondono la notizia,
    
- possono verificarsi conseguenze catastrofiche a livello di percezione pubblica.
    

#### **3.5 Installazione della patch**

La patch viene distribuita, spesso tramite:

- aggiornamenti automatici,
    
- aggiornamenti manuali.
    

Gli amministratori o gli utenti installano la soluzione.

#### **3.6 Analisi di vulnerabilità simili**

I tecnici della sicurezza analizzano il codice per individuare:

- vulnerabilità simili,
    
- pattern ricorrenti.
    

A questo punto:

> il ciclo si ripete.

---

### **4. Complicazioni del ciclo di vulnerabilità**

La realtà è più complessa della sequenza ideale.

#### **4.1 Ritardi nelle correzioni**

Sistemi e reti vulnerabili raramente vengono riparati durante il ciclo di vita della vulnerabilità.

Spesso:

- le patch arrivano solo con versioni aggiornate del software,
    
- gli aggiornamenti non vengono installati tempestivamente.
    

#### **4.2 Diffusione di malware**

Software maligno distribuito via Internet può:

- sfruttare la vulnerabilità,
    
- propagare rapidamente danni su larga scala.
    

#### **4.3 Dimensioni del problema**

Per comprendere la portata del fenomeno:

- su 100 applicazioni Internet che forniscono servizi critici esistono circa **10.000 bug di sicurezza**,
    
- sistemi operativi come Linux o Windows possono contenere **centinaia di migliaia di vulnerabilità**.
    

Questo dimostra che la sicurezza assoluta non è realistica.

---

### **5. Cos’è un attacco**

Un **attacco** è definito come:

> ogni atto malevolo contro un sistema o un complesso di sistemi.

Per analizzare un attacco è utile distinguere diverse componenti.

---

### **6. Componenti di un attacco**

#### **6.1 Goal**

Il **goal** rappresenta:

> il danno finale causato al sistema.

Esempi:

- rubare denaro,
    
- ottenere servizi,
    
- sottrarre informazioni.
    

#### **6.2 Sottogoal**

I **sottogoal** sono:

> obiettivi intermedi necessari per raggiungere il goal.

Esempio:

- ottenere privilegi di accesso.
    

#### **6.3 Attività**

Le **attività** sono:

> le azioni svolte dall’attaccante per raggiungere i sottogoal.

Esempi:

- usare credenziali rubate,
    
- impersonare un utente o un dispositivo,
    
- inviare pacchetti malformati,
    
- sfruttare vulnerabilità di rete.
    

---

### **7. Eventi, conseguenze e impatto**

#### **7.1 Eventi**

Gli **eventi** sono:

> i risultati immediati delle attività di attacco.

Esempi:

- accesso improprio,
    
- sospensione di richieste,
    
- esaurimento memoria,
    
- arresto del sistema.
    

#### **7.2 Conseguenze**

Le **conseguenze** rappresentano:

> gli effetti operativi degli eventi.

Esempi:

- errori negli estratti conto,
    
- indisponibilità del sistema,
    
- perdita di dati.
    

#### **7.3 Impatto**

L’**impatto** riguarda:

> gli effetti di business.

Esempi:

- danno reputazionale,
    
- perdita economica,
    
- perdita di fiducia.
    

---

### **8. Esempio completo di attacco**

La lezione propone un esempio sintetico:

- Goal: rubare denaro
    
- Sottogoal: ottenere accesso non autorizzato
    
- Attività: usare password rubata
    
- Eventi: violazione dei controlli di accesso
    
- Conseguenze: mancanza di bilancio
    
- Impatto: perdita di guadagno
    

Questo schema aiuta a modellare gli attacchi in modo sistematico.

---

### **9. Modalità di attacco**

Una domanda fondamentale è:

> come un attacker attacca un sistema?

La risposta dipende da:

- motivazione (perché),
    
- competenza tecnica.
    

Per rafforzare la sicurezza è essenziale porsi due domande:

- cosa può accadere,
    
- come può accadere.
    

Questo approccio è tipico del **threat modeling**.

---

### **10. Tipi di attacco nel ciclo di vita**

Gli attacchi possono essere classificati in base alla fase del ciclo di vita del software in cui si verificano.

#### **10.1 Attacchi in fase di progettazione**

Avvengono mentre si progetta l’applicazione.

Sono spesso i più difficili da correggere perché:

- coinvolgono decisioni architetturali,
    
- richiedono cambiamenti profondi.
    

#### **10.2 Attacchi in fase di implementazione**

Avvengono durante la scrittura del codice.

Sono causati da:

- bug,
    
- errori di programmazione,
    
- cattive pratiche.
    

#### **10.3 Attacchi in fase operativa**

Avvengono dopo che l’applicazione è in produzione.

Sono legati a:

- configurazione,
    
- uso,
    
- gestione del sistema.
    

---

### **11. Sintesi della lezione**

In questa lezione abbiamo visto:

- il concetto di ciclo di vulnerabilità,
    
- le fasi che lo caratterizzano,
    
- le complicazioni legate alla distribuzione delle patch,
    
- la struttura di un attacco,
    
- la classificazione dei tipi di attacco.
    

Concetti fondamentali da ricordare:

- il ciclo di vulnerabilità è ortogonale al ciclo di sviluppo,
    
- attacchi e difese dipendono dalla fase del ciclo di vita,
    
- le vulnerabilità più difficili da risolvere sono quelle progettuali.
    

---

### **12. Prossimi passi**

Nelle lezioni successive verranno analizzate:

- le diverse classi di attacchi:
    
    - progettuali,
        
    - implementativi,
        
    - operativi;
        
- le tecniche con cui lo sviluppatore può prevenire tali attacchi.