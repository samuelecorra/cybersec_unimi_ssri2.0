# **UD2 – Controllo degli accessi**

Questa unità introduce i **principi e i modelli di controllo degli accessi**, fondamentali per garantire che **ogni utente o processo possa eseguire solo le operazioni autorizzate**.  
Si studiano i modelli **DAC (Discretionary Access Control)** e **MAC (Mandatory Access Control)**, insieme alle loro logiche di funzionamento e ai criteri di gestione delle autorizzazioni.  
L’obiettivo è comprendere come le organizzazioni definiscono, applicano e supervisionano le **politiche di accesso** ai dati e alle risorse, bilanciando **sicurezza, flessibilità e responsabilità operativa**.


---

## **Lezione 1: Principi di controllo degli accessi**

### **1. Definizione e ruolo del controllo degli accessi**

Il **controllo degli accessi** è un elemento centrale nella **sicurezza informatica**, poiché regola **chi può accedere a cosa** e **in che modo**.  
Consente di proteggere risorse e informazioni, garantendo che solo **utenti o processi autorizzati** possano utilizzarle secondo regole prestabilite.

#### **Definizioni normative**

- **NISTIR 7298 (2013)** definisce il controllo degli accessi come il processo di concessione o diniego di richieste specifiche per:
    
    1. ottenere e utilizzare informazioni o servizi informatici;
        
    2. accedere a strutture fisiche protette.
        
- **RFC 4949 (Internet Security Glossary)** lo descrive come il meccanismo attraverso cui l’uso delle risorse di sistema è regolato in base a una **politica di sicurezza**, che concede l’accesso **solo alle entità autorizzate** (utenti, processi, programmi o altri sistemi).
    
- **ITU-T X.800** lo definisce come:
    
    > “La prevenzione dell’uso non autorizzato di una risorsa, incluso l’uso improprio di una risorsa autorizzata.”
    

In sintesi, il controllo degli accessi **implementa la politica di sicurezza** che stabilisce:

- **chi o cosa** può accedere a una risorsa di sistema;
    
- **in quale modo** tale accesso è consentito.
    

---

### **2. Componenti principali del controllo degli accessi**

Il controllo degli accessi è parte di un sistema più ampio che comprende **autenticazione, autorizzazione e auditing**.

|**Funzione**|**Descrizione**|
|---|---|
|**Autenticazione**|Verifica l’identità dell’utente o dell’entità che richiede l’accesso.|
|**Autorizzazione**|Determina se l’entità autenticata ha il diritto di eseguire una determinata operazione su una risorsa.|
|**Auditing**|Monitora e registra le attività del sistema, verificando la conformità alle politiche di sicurezza e individuando eventuali anomalie.|

Il **meccanismo di controllo degli accessi** media tra **utente/processo** e **risorse di sistema** (file, directory, database, applicazioni, ecc.), verificando — dopo l’autenticazione — se la richiesta di accesso è consentita.  
Le decisioni si basano su un **database di autorizzazioni** gestito da un **amministratore di sicurezza**, mentre il modulo di **auditing** mantiene i log delle operazioni.

---

### **3. Elementi fondamentali**

Il controllo degli accessi si basa su tre concetti chiave: **soggetto**, **oggetto** e **diritto di accesso**.

#### **Soggetto**

È l’entità che può accedere a un oggetto.  
Di solito è rappresentata da un **processo** che agisce per conto di un **utente o applicazione**.  
I soggetti vengono generalmente classificati in tre categorie:

- **Proprietario (Owner):** creatore o responsabile di una risorsa; può essere un utente, un amministratore o un project leader.
    
- **Gruppo (Group):** insieme di utenti con privilegi condivisi su una determinata risorsa.
    
- **Resto del mondo (World):** utenti che non appartengono né al proprietario né al gruppo, e che ricevono solo i permessi minimi.
    

#### **Oggetto**

È la risorsa soggetta a controllo, cioè l’entità a cui si accede.  
Esempi: file, record, directory, messaggi, mailbox, segmenti di memoria, programmi, dispositivi.  
Il numero e il tipo di oggetti dipendono dall’ambiente operativo e dal sistema di sicurezza adottato.

#### **Diritto di accesso**

Indica **come** il soggetto può interagire con l’oggetto.  
Esempi di diritti: `read`, `write`, `execute`, `delete`, `create`, `search`.  
Ogni diritto viene assegnato in base alle regole di **autorizzazione** e può essere diverso per ciascun utente o gruppo.

---

### **4. Requisiti di sicurezza secondo NIST SP 800-171**

Il documento **NIST SP 800-171** (agosto 2016) elenca i requisiti per la **protezione delle informazioni non classificate controllate (CUI)** nei sistemi e nelle organizzazioni non federali.  
Questi requisiti si suddividono in **base** e **derivati**.

#### **Requisiti di sicurezza di base**

1. **Limitare l’accesso** al sistema informativo agli utenti, processi o dispositivi autorizzati.
    
2. **Limitare le operazioni** eseguibili dagli utenti autorizzati alle sole transazioni e funzioni permesse.
    

#### **Requisiti di sicurezza derivati**

3. Controllare il flusso delle informazioni **CUI** secondo le autorizzazioni approvate.
    
4. Separare i compiti degli individui per ridurre il rischio di attività malevole.
    
5. Applicare il **principio del privilegio minimo**, anche per gli account amministrativi.
    
6. Utilizzare **account non privilegiati** per funzioni ordinarie.
    
7. Impedire agli utenti non privilegiati di eseguire operazioni riservate.
    
8. Limitare il numero di **tentativi di accesso falliti**.
    
9. Fornire **avvisi di privacy e sicurezza** all’utente.
    
10. Implementare **blocchi automatici di sessione** dopo periodi di inattività.
    
11. Terminare automaticamente le sessioni utente dopo un timeout definito.
    
12. **Monitorare e controllare** le sessioni di accesso remoto.
    
13. Proteggere la **riservatezza delle connessioni remote** tramite meccanismi crittografici.
    
14. Instradare l’accesso remoto attraverso **punti di controllo gestiti**.
    
15. Autorizzare l’esecuzione remota di comandi privilegiati.
    
16. Autorizzare l’**accesso wireless** prima della connessione effettiva.
    

---

### **5. Sintesi finale**

Il controllo degli accessi è la **colonna portante della sicurezza dei sistemi informatici**.  
Esso combina **autenticazione, autorizzazione e auditing** per garantire che solo soggetti legittimi possano interagire con le risorse, nel rispetto delle politiche aziendali.

> In un sistema sicuro, ogni accesso è **identificato, autorizzato e tracciato**.  
> Il controllo degli accessi non si limita a bloccare gli utenti, ma **assicura che ciascuno operi entro i limiti dei propri diritti**.