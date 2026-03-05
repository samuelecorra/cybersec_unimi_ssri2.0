# **M4 UD1 Lezione 2 - Raccolta e analisi dei requisiti**

### **1. Introduzione**

La **raccolta e analisi dei requisiti** rappresenta una delle fasi più delicate dell’intero ciclo di vita di un sistema informativo.  
In questa fase si definisce **che cosa il sistema dovrà fare**, **quali dati dovrà gestire** e **quali operazioni dovrà supportare**.  
Un errore in questa fase può compromettere la qualità del progetto successivo, quindi chiarezza, precisione e completezza sono fondamentali.

---

### **2. Concetto di raccolta e analisi dei requisiti**

La **raccolta dei requisiti** consiste nell’individuazione completa di:

- i **problemi** che l’applicazione dovrà risolvere;
    
- le **caratteristiche** che il sistema dovrà possedere:
    
    - **statiche**, cioè relative ai **dati**;
        
    - **dinamiche**, cioè relative alle **operazioni sui dati**.

I requisiti vengono inizialmente **descritti in linguaggio naturale**, perciò risultano spesso **ambigui, ridondanti o disorganizzati**.

L’**analisi dei requisiti** serve proprio a **chiarire e organizzare** queste specifiche, eliminando le ambiguità e portando a una **descrizione coerente e formalmente più precisa**.

---

### **3. Raccolta dei requisiti**

#### **Fonti dei requisiti**

I requisiti di un sistema informativo possono provenire da **fonti diverse**, tra cui:

- **Utenti dell’applicazione:** forniscono informazioni dirette sui dati da gestire e sulle operazioni necessarie.
    
- **Documentazione esistente:** include moduli, regolamenti, normative interne o leggi che regolano l’attività dell’organizzazione.
    
- **Applicazioni preesistenti:** possono essere sistemi da sostituire o applicazioni che dovranno interagire con quella da sviluppare.

#### **Ruolo dell’interazione con gli utenti**

L’interazione con gli utenti del sistema informativo è **cruciale**.  
Spesso utenti diversi forniscono **informazioni discordanti o incomplete**, quindi è necessario:

- **verificare la consistenza e la comprensione** delle specifiche raccolte;
    
- **distinguere gli aspetti essenziali** da quelli marginali;
    
- procedere per **raffinamenti successivi**, migliorando progressivamente la precisione delle informazioni.

---

### **4. Analisi dei requisiti**

Una volta raccolte le informazioni, si passa alla fase di **analisi**, in cui le specifiche vengono **verificate, corrette e strutturate** in modo da ottenere una base chiara e non ambigua per la progettazione successiva.

Poiché le specifiche iniziali sono spesso espresse in **linguaggio naturale**, possono contenere **inesattezze, vaghezze o contraddizioni**.  
L’obiettivo dell’analisi è quello di **riscrivere e sistematizzare** tali specifiche, garantendo **precisione, coerenza e un linguaggio condiviso**.

---

### **5. Regole per l’analisi dei requisiti**

#### **Regola 1 – Scegliere il corretto livello di astrazione**

Evitare termini **troppo specifici** o **troppo generici**.  
L’obiettivo è mantenere un linguaggio coerente con il livello concettuale del progetto.  
Esempio:

> “Titolo” per un impiegato può riferirsi al titolo di studio o al titolo professionale: è necessario specificarlo chiaramente.

---

#### **Regola 2 – Standardizzare la struttura delle frasi**

Utilizzare uno **stile sintattico omogeneo** per tutte le definizioni.  
Un buon formato può essere:

$$  
\langle \text{entità o dato} \rangle \text{ ha } \langle \text{insieme di proprietà} \rangle  
$$

Esempio:

> “Studente ha nome, matricola e corso di laurea.”

Questa coerenza sintattica facilita la comprensione e la traduzione successiva in modelli concettuali formali.

---

#### **Regola 3 – Evitare frasi contorte**

Le definizioni devono essere **semplici, dirette e chiare**.  
Esempio:

> È preferibile dire “lavoratori dipendenti” piuttosto che “coloro che lavorano alle dipendenze di altri”.

---

#### **Regola 4 – Correggere sinonimi e omonimi**

Bisogna individuare e risolvere ambiguità linguistiche dovute a:

- **Sinonimi:** termini diversi con lo stesso significato → devono essere **unificati**.
    
- **Omonimi:** stesso termine con significati diversi → devono essere **distinti**.

Esempi:

> “Insegnante” e “professore” → unificati in “docente”.  
> “Posto” può significare “città” o “aula”: va sostituito con il termine corretto in base al contesto.

---

#### **Regola 5 – Esplicitare i riferimenti tra termini**

Quando un termine può avere **più significati**, è necessario **specificare a chi o a cosa si riferisce**.  
Esempio:

> “Indirizzo” per un impiegato può indicare l’indirizzo **di casa** o **di lavoro**: va esplicitato nel requisito.

---

#### **Regola 6 – Costruire un glossario dei termini**

È utile creare un **glossario** che elenchi tutti i termini rilevanti, fornendo per ciascuno:

- una **breve descrizione del significato**,
    
- eventuali **sinonimi o varianti**,
    
- i **legami con altri termini**.

Questo strumento garantisce **uniformità terminologica** in tutte le fasi del progetto.

---

### **6. Requisiti sulle operazioni**

Oltre ai dati, occorre specificare anche **le operazioni** che il sistema dovrà eseguire e **la loro frequenza**.  
Queste informazioni saranno fondamentali nella fase di **progettazione logica** per valutare l’efficienza e il carico del sistema.

**Esempi:**

- Inserimento di un nuovo docente e dei corsi che può insegnare → **2 volte al giorno**
    
- Produzione e stampa della lista di tutti i docenti e dei corsi assegnati → **15 volte al giorno**

Questi dati aiutano a definire le **priorità operative** e l’**ottimizzazione delle prestazioni** del database.

---

### **7. Sintesi finale**

In questa lezione abbiamo approfondito le fasi di **raccolta** e **analisi dei requisiti**, distinguendo tra:

- **requisiti sui dati** (parte statica del sistema);
    
- **requisiti sulle operazioni** (parte dinamica del sistema).

È fondamentale che le **specifiche dei requisiti** siano **chiare, precise e prive di ambiguità**, poiché costituiscono la **base logica** su cui si costruirà l’intero progetto di base di dati.

---

**In sintesi:**

> Una buona raccolta e analisi dei requisiti non è solo il primo passo del progetto, ma il fondamento che determina la qualità, la robustezza e la longevità del sistema informativo.

---


![](imgs/Pasted%20image%2020251125050138.png)

