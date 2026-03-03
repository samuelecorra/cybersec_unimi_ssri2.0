# **UD3 – Controllo degli accessi nei sistemi operativi**

Questa unità approfondisce l’applicazione pratica dei **modelli di controllo degli accessi** all’interno dei principali sistemi operativi moderni, **Windows** e **Linux**.  
Vengono analizzate le **politiche di sicurezza integrate** e i **meccanismi tecnici** che regolano l’autenticazione, la gestione delle autorizzazioni e l’accesso alle risorse locali e di rete.  
L’obiettivo è comprendere come i concetti teorici di **DAC, MAC e RBAC** vengano implementati concretamente nei sistemi reali, evidenziando **differenze architetturali, strumenti amministrativi** e **strategie di protezione** tipiche di ciascun ambiente.


---

## **Lezione 1: Controllo degli accessi in Windows**

### **1. Architettura della sicurezza di Windows**

Il sistema operativo **Windows** integra una complessa architettura di sicurezza composta da diversi moduli che cooperano per garantire **autenticazione, autorizzazione e auditing**.

I principali componenti sono:

- **Security Reference Monitor (SRM)**
    
- **Local Security Authority (LSA)**
    
- **Security Account Manager (SAM)**
    
- **Active Directory (AD)**
    
- **Authentication Packages**
    
- **WinLogon e NetLogon**
    

Questi moduli cooperano a livello di kernel e di spazio utente per eseguire i controlli di accesso, applicare le politiche di sicurezza e gestire i token degli utenti autenticati.

---

### **2. Componenti fondamentali della sicurezza Windows**

#### **Security Reference Monitor (SRM)**

- È un componente in **modalità kernel** che:
    
    - Esegue i **controlli di accesso** su tutte le risorse del sistema.
        
    - Genera **voci di log** per gli eventi di auditing.
        
    - Gestisce i **privilegi utente** e le **autorizzazioni**.
        
- Ogni volta che un processo tenta di accedere a un oggetto protetto, è l’SRM a decidere se concedere o negare l’operazione.
    

> Gli SRM moderni sono progettati per essere **piccoli e verificabili**, in modo da ridurre le possibilità di errore o bypass.

---

#### **Local Security Authority (LSA)**

- È un processo in **modalità utente** (lsass.exe).
    
- È responsabile dell’**applicazione della politica di sicurezza locale**.
    
- Genera i **token di sicurezza** quando un account accede al sistema.
    
- Applica:
    
    - **Criteri password** (complessità, scadenza, lunghezza minima).
        
    - **Politiche di auditing** (quali operazioni loggare e su quali oggetti).
        
    - **Gestione dei privilegi** (quali account possono eseguire operazioni privilegiate).
        

---

#### **Security Account Manager (SAM)**

- È un **database** che memorizza:
    
    - Gli **account locali** e i **gruppi locali**.
        
    - Le informazioni di sicurezza e autenticazione relative a tali account.
        
- Quando un utente effettua il login locale:
    
    - Il processo **SamSrv** ricerca le credenziali nel database **SAM** situato in  
        `\Windows\System32\Config`.
        
- Il **SAM** è l’equivalente di `/etc/passwd` nei sistemi UNIX.
    

Le password sono archiviate in forma **hashata**, storicamente con **MD4**, ma dalle versioni più recenti (da **Vista in poi**) si utilizza una funzione derivata da **PBKDF2**, più robusta contro gli attacchi di forza bruta.

> L’autenticazione locale è gestita dal **LSA**, non direttamente dal SAM.

---

#### **Active Directory (AD)**

- È un **database centralizzato** basato su **LDAP** (Lightweight Directory Access Protocol).
    
- Gestisce gli **account di dominio** e consente autenticazioni su larga scala.
    
- È utilizzato nei contesti aziendali in cui i computer fanno parte di un **dominio** Windows.
    

**Differenza tra gruppo di lavoro e dominio:**

- **Gruppo di lavoro:** ogni macchina gestisce localmente i propri account.
    
- **Dominio:** le credenziali vengono gestite in modo centralizzato dal **controller di dominio** tramite **AD**.
    

> Un computer può appartenere a un dominio o a un gruppo di lavoro, ma non a entrambi.

---

### **3. Modello di sicurezza Windows**

Windows implementa un sistema di controllo degli accessi **complesso e granulare**, in cui ogni oggetto ha un proprio **ACL** (Access Control List).

Caratteristiche principali:

- Autorizzazioni **granulari** su singoli oggetti (file, chiavi di registro, processi, ecc.).
    
- Ogni utente può appartenere a **più gruppi**, e i gruppi possono essere **annidati**.
    
- Le ACL possono includere **regole di Allow, Deny, Audit** e combinazioni complesse.
    

---

### **4. Security Identifier (SID)**

Ogni account o gruppo in Windows è identificato da un **Security ID (SID)** univoco.

#### **Struttura del SID**

```
S-1-5-21-AAA-BBB-CCC-RRR
```

- **S:** indica che si tratta di un SID.
    
- **1:** versione del formato SID.
    
- **5:** autorità di identificazione (`SECURITY_NT_AUTHORITY`).
    
- **21:** indica un’identità non universale.
    
- **AAA-BBB-CCC:** identificatore univoco del dominio.
    
- **RRR:** Relative ID (RID), incrementato di 1 per ogni nuovo account creato.
    

> Esempio: `S-1-5-21-123625317-425641126-188346712-2895`.

Il SID è utilizzato in tutti i processi di autenticazione e nelle ACL per identificare utenti, gruppi e oggetti.

---

### **5. Security Descriptor (SD)**

Ogni oggetto in Windows è associato a un **Security Descriptor**, che definisce:

- L’**owner** (proprietario) e il **gruppo primario** (tramite i rispettivi SID).
    
- La **DACL (Discretionary ACL)** → specifica chi può accedere e in che modo.
    
- La **SACL (System ACL)** → specifica quali tentativi devono essere registrati nei log di sicurezza.
    

Ogni processo è associato a un **token di sicurezza**, chiamato anche **security context**, che include:

- ID utente.
    
- ID gruppi.
    
- ID sessione di login.
    
- Lista dei privilegi di sistema.
    

> Quando un processo tenta di accedere a un oggetto, Windows confronta il **token di sicurezza** del processo con il **security descriptor** dell’oggetto per determinare se l’accesso è consentito.

---

### **6. Access Control Lists (ACL) in Windows**

Esistono due principali tipologie di ACL:

1. **DACL (Discretionary ACL):**
    
    - Concede o nega l’accesso a risorse come file, pipe, memoria condivisa, ecc.
        
    - Contiene una lista di **Access Control Entries (ACE)**, ognuna delle quali specifica:
        
        - Un SID (utente o gruppo).
            
        - Una maschera di accesso (read, write, execute, delete, ecc.).
            
2. **SACL (System ACL):**
    
    - Serve per l’auditing e per il controllo dei **criteri di integrità obbligatori** (MAC).
        
    - Determina quali azioni generano eventi nei log di sicurezza.
        

#### **Esempio di DACL**

```
Owner: CORP\Blake
ACE[0]: Allow CORP\Paige Full Control
ACE[1]: Allow Administrators Full Control
ACE[2]: Allow CORP\Cheryl Read, Write, and Delete
```

- **Paige** → controllo totale sull’oggetto.
    
- **Amministratori** → stessi privilegi.
    
- **Cheryl** → può leggere, scrivere ed eliminare.
    
- **Blake**, in quanto proprietario, mantiene sempre il controllo completo.
    

---

### **7. Mandatory Access Control (MAC) in Windows**

Dalla versione **Windows Vista** in poi, Microsoft ha introdotto il **Mandatory Integrity Control (MIC)**, una forma di **MAC** basata su **livelli di integrità**.

Ogni processo o oggetto è etichettato con un livello:

|**Livello di integrità**|**SID associato**|
|---|---|
|Bassa integrità|S-1-16-4096|
|Media integrità|S-1-16-8192|
|Alta integrità|S-1-16-12288|
|Integrità di sistema|S-1-16-16384|

- I processi con livello basso non possono modificare oggetti con livello superiore.
    
- Se un oggetto o un processo non ha etichetta, viene considerato **a integrità media**.
    
- I **livelli di integrità** vengono aggiunti al **token del processo** e gestiti dal sistema in modo automatico.
    

> Questo meccanismo previene, ad esempio, che un’applicazione eseguita con privilegi bassi possa modificare file o impostazioni di sistema.

---

### **8. Sintesi finale**

Il sistema di controllo degli accessi di Windows combina **meccanismi DAC, RBAC e MAC**, integrandoli in un’architettura centralizzata e modulare.

- **DAC:** con le DACL e i diritti discrezionali.
    
- **MAC:** con il sistema di **integrity levels**.
    
- **RBAC:** tramite gruppi e ruoli utente gestiti localmente o via Active Directory.
    

> In sintesi, Windows implementa un modello ibrido e multilivello che garantisce **flessibilità amministrativa, sicurezza strutturale e auditing accurato**, adattandosi tanto a contesti domestici quanto a infrastrutture aziendali complesse.


---