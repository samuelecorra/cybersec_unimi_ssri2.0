# **M4 UD3 Lezione 5: Coordinamento distribuito tra processi (Parte 2)**

### **1. Introduzione**

In questa seconda parte vengono approfonditi i **meccanismi di coordinamento** tra processi nei sistemi distribuiti, concentrandosi su:

- **Atomicità** delle transazioni distribuite,
    
- **Gestione della concorrenza** mediante protocolli di lock,
    
- **Algoritmi di elezione del coordinatore** per mantenere la coerenza del sistema.

---

### **2. Atomicità**

L’**atomicità** garantisce che una **transazione distribuita** venga eseguita **completamente o per nulla**, anche in presenza di guasti o malfunzionamenti di rete.

Ogni macchina coinvolta nella transazione possiede un **coordinatore locale** incaricato di:

- iniziare la transazione;
    
- dividerla in **sotto-transazioni**;
    
- distribuirle alle macchine partecipanti;
    
- coordinare la fase di conclusione.

---

### **3. Protocollo di commit a due fasi (Two-Phase Commit Protocol – 2PC)**

Il **2PC** è il metodo più diffuso per garantire l’atomicità nei sistemi distribuiti.  
Consente a tutte le macchine coinvolte di **decidere congiuntamente** se eseguire o annullare una transazione.

#### **Fase 1 – Preparazione**

1. Il **coordinatore principale (Cᵢ)** invia un messaggio _“prepare T”_ a tutte le macchine coinvolte.
    
2. Ogni macchina locale (Cⱼ) valuta se può completare la propria parte:
    
    - se non può, registra `<no T>` e risponde con _abort_;
        
    - se può, registra `<ready T>` e risponde con _ready T_.

#### **Fase 2 – Decisione**

1. Quando il coordinatore riceve tutte le risposte (o scade il timeout), decide:
    
    - **commit T** se tutti hanno risposto _ready T_;
        
    - **abort T** in caso contrario.
    
2. Registra la decisione nel proprio log e la comunica a tutti i partecipanti.
    
3. Ogni nodo aggiorna il proprio log con `<commit T>` o `<abort T>` e completa la transazione.

#### **Tolleranza ai guasti**

Il 2PC gestisce:

- **Guasti delle macchine**: grazie ai log persistenti, il sistema può recuperare lo stato delle transazioni.
    
- **Guasti di rete**: la decisione può essere ritardata finché la connessione non è ristabilita, mantenendo la consistenza globale.

---

### **4. Gestione della concorrenza**

In un ambiente distribuito, più transazioni possono competere per le stesse risorse.  
La **gestione della concorrenza** avviene tramite:

- **gestori delle transazioni locali e globali**,
    
- **file di log**,
    
- **protocolli bloccanti o non bloccanti**.

---

### **5. Protocolli bloccanti**

#### **5.1 Coordinatore centralizzato dei lock**

- I dati **non sono replicati**.
    
- Esiste un **unico responsabile dei lock** per tutto il sistema.
    
- Ogni richiesta richiede **due messaggi** (richiesta e risposta).
    
- **Svantaggi**:
    
    - complessa gestione degli **stalli** (deadlock);
        
    - **collo di bottiglia** sul coordinatore;
        
    - bassa **tolleranza ai guasti**.

---

#### **5.2 Coordinatori multipli dei lock**

- I dati **non sono replicati**.
    
- Ogni nodo ha un **responsabile locale dei lock**.
    
- La richiesta di lock richiede ancora **due messaggi**.
    
- Gestione degli **stalli** ancora complessa, ma maggiore **distribuzione del carico**.

---

#### **5.3 Coordinatore a maggioranza**

- I dati **sono replicati**.
    
- Ogni sito mantiene un **proprio responsabile dei lock**.
    
- Per ottenere un lock globale servono almeno **n/2 + 1 lock locali**.
    
- Numero di messaggi:
    
    - $2 \times (n/2 + 1)$ per bloccare,
        
    - $(n/2 + 1)$ per sbloccare.
    
- **Vantaggio:** maggiore **tolleranza ai guasti**.
    
- **Svantaggio:** aumento del **sovraccarico comunicativo** e maggiore complessità nella gestione degli stalli.

---

#### **5.4 Protocollo polarizzato**

- I dati **sono replicati**.
    
- Ogni sito ha un **responsabile dei lock**.
    
- Blocchi:
    
    - **Condivisi** → gestiti localmente.
        
    - **Esclusivi** → gestiti come nel lock a maggioranza.
    
- **Vantaggi:**
    
    - minor sovraccarico in lettura rispetto al metodo a maggioranza.
    
- **Svantaggi:**
    
    - maggiore overhead in scrittura;
        
    - gestione dello **stallo complessa**.

---

### **6. Coordinatore nei sistemi distribuiti**

Il **coordinatore** è il processo responsabile del mantenimento dell’ordine e della coerenza nel sistema.  
I suoi compiti principali includono:

- garantire la **mutua esclusione**;
    
- rilevare e risolvere **stalli (deadlock)**;
    
- gestire la **perdita del token** (nei sistemi token-based);
    
- controllare **input/output condivisi**.

---

### **7. Algoritmi di elezione del coordinatore**

Quando il coordinatore fallisce, è necessario eleggerne uno nuovo.  
Gli algoritmi di elezione garantiscono che **esattamente un processo** assuma il ruolo di coordinatore.

---

#### **7.1 Algoritmo del Bullo**

- Ogni processo ha un **identificatore di priorità**.
    
- Quando un processo $P$ rileva che il coordinatore è inattivo:
    
    1. Invia un messaggio di **elezione** ai processi con priorità più alta.
        
    2. Se non riceve risposta entro un timeout $T$,  
        → si **autoproclama coordinatore** e informa tutti gli altri.
        
    3. Se riceve una risposta, attende che venga eletto un nuovo coordinatore.
        
    4. Se non riceve l’identificatore del nuovo coordinatore,  
        → riavvia il processo di elezione.

---

#### **7.2 Algoritmo dell’anello**

- I processi sono collegati in un **anello logico unidirezionale**.
    
- Quando un processo $P$ rileva che il coordinatore è inattivo:
    
    1. Crea una **lista attiva vuota** e la invia al successivo nell’anello.
        
    2. Ogni processo che riceve il messaggio:
        
        - se non compare nella lista, **aggiunge sé stesso** e inoltra il messaggio;
            
        - se riceve un messaggio che contiene il proprio identificatore,  
            → la lista è completa.
        
    1. Il nuovo coordinatore è il **processo con priorità più alta** nella lista.

---

### **8. Sintesi finale**

|Tema|Meccanismo o Protocollo|Obiettivo principale|
|---|---|---|
|**Atomicità**|Commit a due fasi (2PC)|Tutte le transazioni completate o annullate|
|**Concorrenza**|Protocolli di lock (centralizzato, multiplo, a maggioranza, polarizzato)|Controllo accessi simultanei|
|**Coordinamento**|Algoritmo del Bullo, Algoritmo dell’Anello|Elezione del coordinatore e gestione guasti|

---

### **9. Conclusione**

Il **coordinamento distribuito** richiede la combinazione di più tecniche:

- **orologi logici** per ordinare gli eventi,
    
- **protocolli di commit** per garantire l’atomicità,
    
- **protocolli di lock** per gestire la concorrenza,
    
- **algoritmi di elezione** per mantenere la stabilità in caso di guasti.

Insieme, questi meccanismi consentono di realizzare sistemi distribuiti **coerenti, affidabili e resilienti**, capaci di mantenere integrità e prestazioni anche in ambienti complessi e dinamici.