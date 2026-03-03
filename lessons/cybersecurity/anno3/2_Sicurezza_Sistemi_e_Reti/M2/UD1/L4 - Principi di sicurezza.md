## **Lezione 4: Principi di sicurezza**

### **1. Introduzione**

La progettazione di un sistema sicuro richiede **principi strutturali chiari** che guidino lo sviluppo dei meccanismi di protezione.  
Questi principi, proposti originariamente da **Jerome Saltzer e Michael Schroeder** nel 1975 (_Proceedings of the IEEE_), rappresentano ancora oggi la base della **sicurezza dei sistemi informatici**.

Essi si fondano su due idee centrali:

- **Semplicità**, per ridurre gli errori e rendere il sistema verificabile.
    
- **Limitazione**, per minimizzare l’impatto di eventuali fallimenti.
    

Negli anni, tali principi sono stati ripresi e ampliati da enti come la **NSA**, il **National Centers of Academic Excellence in Cyber Defense** e il **Department of Homeland Security**.

---

### **2. Gli otto principi classici di Saltzer e Schroeder**

1. **Economia di meccanismo (Economy of Mechanism)**  
    I meccanismi di sicurezza devono essere **i più semplici possibile**.  
    La semplicità riduce il rischio di errori e facilita verifica, test e manutenzione.  
    _Esempio:_ il protocollo **Ident** associava un nome utente a un processo TCP, ma un host compromesso poteva inviare qualsiasi identità.  
    → Un meccanismo troppo complesso o con troppe interfacce diventa facilmente vulnerabile.
    

---

2. **Default libero da fallimento (Fail-Safe Defaults)**  
    In assenza di permessi espliciti, **l’accesso deve essere negato**.  
    Se un programma fallisce, il sistema deve **tornare in uno stato sicuro**.  
    _Esempio:_ un server di posta che va in crash non deve scrivere messaggi in directory non autorizzate; deve interrompere la connessione e fermarsi in sicurezza.
    

---

3. **Mediazione completa (Complete Mediation)**  
    Ogni accesso alle risorse deve essere **controllato e validato**.  
    Non basta verificare i permessi solo all’apertura: occorre farlo **a ogni utilizzo**.  
    _Esempio:_
    
    - un file aperto con permessi validi, ma poi revocati, non dovrebbe essere ancora leggibile;
        
    - un server DNS che memorizza risultati in cache può subire un **cache poisoning**, indirizzando gli utenti verso siti falsi.
        

---

4. **Progetto aperto (Open Design)**  
    La sicurezza **non deve basarsi sulla segretezza della progettazione o del codice**, ma sull’efficacia dei principi adottati.  
    Questo contrasta la logica della _security by obscurity_.  
    _Esempi:_
    
    - Algoritmi crittografici pubblici (AES, RSA) restano sicuri perché basati su principi matematici, non sulla segretezza del codice.
        
    - Il **Content Scramble System (CSS)** dei DVD fu violato nel 1999 proprio perché basato sulla segretezza del design.
        

---

5. **Separazione dei privilegi (Separation of Privilege)**  
    L’autorizzazione a un’azione non deve dipendere da **una singola condizione**.  
    È preferibile richiedere **più fattori o verifiche** indipendenti.  
    _Esempi:_
    
    - Assegni aziendali oltre 75.000 € devono essere firmati da **due responsabili**.
        
    - In Berkeley UNIX, l’accesso a _root_ richiedeva sia la password di amministratore sia l’appartenenza al gruppo `wheel`.
        

---

6. **Minimo privilegio (Least Privilege)**  
    Ogni soggetto deve disporre **solo dei privilegi strettamente necessari** per svolgere il proprio compito.  
    Se deve elevarli temporaneamente, questi devono essere **revocati subito dopo**.  
    _Problema pratico:_ molti sistemi non permettono una granularità sufficiente nei diritti di accesso, rendendo difficile applicare pienamente questo principio.
    

---

7. **Meccanismo al minimo comune (Least Common Mechanism)**  
    I meccanismi di sicurezza **condivisi da più utenti o processi** devono essere ridotti al minimo, per evitare che una vulnerabilità comune comprometta più componenti.  
    Ogni processo deve interagire con **il minor numero possibile di risorse condivise**.
    

---

8. **Accettabilità psicologica (Psychological Acceptability)**  
    Le misure di sicurezza devono essere **facili da usare e non penalizzare l’utente legittimo**.  
    In caso contrario, l’utente tenderà ad aggirarle.  
    _Esempio:_ l’autenticazione SSH che blocca l’accesso dopo tre tentativi falliti è accettabile solo se non ostacola il normale lavoro dell’utente.
    

---

### **3. Altri principi di progettazione moderna**

Oltre agli otto principi classici, la progettazione sicura include ulteriori concetti complementari.

#### **a. Isolamento (Isolation)**

- I sistemi di accesso pubblico devono essere **separati dalle risorse critiche**.
    
- L’isolamento può essere **fisico o logico**:
    
    - evitare connessioni dirette tra reti pubbliche e sistemi interni sensibili;
        
    - separare processi e file degli utenti;
        
    - isolare i meccanismi di sicurezza stessi, per evitare che vengano compromessi.  
        _Esempio:_ isolare il software crittografico dal resto del sistema host per impedirne la manomissione.
        

---

#### **b. Incapsulamento (Encapsulation)**

- Forma specifica di isolamento basata sull’**orientamento agli oggetti**.
    
- La struttura interna di un oggetto è accessibile solo attraverso le sue **interfacce di sicurezza controllate**.  
    Questo principio è fondamentale nella programmazione sicura.
    

---

#### **c. Modularità (Modularity)**

- Le funzioni di sicurezza devono essere sviluppate come **moduli separati e protetti**.
    
- Favorisce la manutenzione, il riutilizzo e la verifica formale.
    
- Un’architettura modulare consente di aggiornare o correggere un componente senza compromettere l’intero sistema.
    

---

#### **d. Stratificazione (Layering)**

- L’uso di **più livelli di protezione sovrapposti** aumenta la resilienza del sistema.
    
- Gli strati possono riguardare:
    
    - persone (policy, formazione, procedure),
        
    - tecnologia (firewall, antivirus, IDS),
        
    - aspetti operativi (backup, auditing, recovery plan).
        
- Nessun livello è infallibile, ma la loro combinazione garantisce **difesa in profondità** (_defense in depth_).
    

---

#### **e. Minima sorpresa (Least Astonishment)**

- Un programma o un’interfaccia devono comportarsi in modo **prevedibile** per l’utente.
    
- Le risposte del sistema devono essere **coerenti e intuitive**, riducendo il rischio di errore umano.  
    _Esempio:_ un messaggio di conferma chiaro prima di cancellare definitivamente un file.
    

---

### **4. Conclusione**

Progettare un sistema sicuro è un compito complesso.  
I principi esposti da Saltzer e Schroeder rappresentano **linee guida senza tempo**, ancora oggi adottate in standard e framework moderni come **NIST SP 800**, **ISO/IEC 27001** e **Zero Trust Architecture**.

In sintesi:

- La sicurezza nasce da **semplicità, isolamento e controllo**.
    
- I meccanismi devono essere **verificabili, comprensibili e proporzionati** al rischio.
    
- Un sistema sicuro non è quello privo di attacchi, ma quello in cui **ogni fallimento è previsto e contenuto**.
    

> “La sicurezza è un processo, non un prodotto.” — Bruce Schneier
