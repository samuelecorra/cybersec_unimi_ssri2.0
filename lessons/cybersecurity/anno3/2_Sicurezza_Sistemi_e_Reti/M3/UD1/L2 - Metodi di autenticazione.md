## **Lezione 2: Metodi di autenticazione**

### **1. Introduzione**

L’autenticazione è la **prima linea di difesa** contro gli accessi non autorizzati.  
Serve a **verificare l’identità dell’utente** e ad assegnargli i **privilegi appropriati** per l’accesso alle risorse del sistema.  
Questa lezione presenta i principali **metodi di autenticazione**: basati su **conoscenza**, **possesso**, **biometria** e **combinazioni multifattore**.

---

### **2. Metodi di autenticazione dell’identità**

L’autenticazione può basarsi su quattro diverse categorie di elementi, ognuna associata a una modalità distinta di verifica:

|**Categoria**|**Descrizione**|**Esempi**|
|---|---|---|
|**Qualcosa che l’individuo sa**|Si fonda sulla **conoscenza di un’informazione segreta** condivisa tra utente e sistema.|Password, PIN, risposte a domande segrete|
|**Qualcosa che l’individuo possiede (token)**|Richiede il **possesso fisico o digitale** di un oggetto di autenticazione.|Smart card, chiavi USB, token hardware, tessere elettroniche|
|**Qualcosa che l’individuo è (biometria statica)**|Basata su **caratteristiche fisiche uniche** e difficilmente replicabili.|Impronta digitale, viso, retina, geometria della mano|
|**Qualcosa che l’individuo fa (biometria dinamica)**|Si basa su **comportamenti unici e ripetitivi** nel tempo.|Ritmo di digitazione, voce, firma, modo di camminare|

---

### **3. Autenticazione a più fattori (MFA)**

L’**autenticazione multifattore** combina **due o più categorie diverse** per rafforzare la sicurezza.  
Per esempio:

- Password + token
    
- Smart card + impronta digitale
    
- App mobile + riconoscimento facciale
    

Questa tecnica riduce drasticamente la probabilità che un attaccante possa impersonare un utente, anche in caso di furto di uno dei fattori.

---

### **4. Autenticazione basata su password**

È il metodo più diffuso e rappresenta ancora oggi una **difesa standard** in molti sistemi informatici.

#### **Procedura tipica**

1. L’utente inserisce **nome utente (login)** e **password**.
    
2. Il sistema confronta la password fornita con quella **memorizzata in forma cifrata o hashata**.
    
3. Se corrispondono, l’accesso viene concesso; in caso contrario, negato.
    

#### **Ruolo dell’ID utente**

- Identifica l’utente e ne determina i **privilegi di accesso**.
    
- È usato nei **modelli di controllo discrezionale (DAC)** per stabilire chi può accedere a cosa.
    

---

### **5. Scenari di autenticazione e vulnerabilità**

#### **Scenari tipici**

- Accesso a un **computer locale**.
    
- Accesso remoto a un sistema tramite rete.
    
- Accesso a **siti web e applicazioni online**.
    

#### **Principali vulnerabilità**

- **Compromissione del canale di comunicazione** tra client e server.
    
- **Compromissione del server** o del file delle password.
    
- **Compromissione del client** (malware, keylogger, browser hijacking).
    
- **Ingegneria sociale** (phishing, manipolazione psicologica).
    
- **Password deboli** o riutilizzate su più servizi.
    

---

### **6. Tipologie di attacco alle password**

|**Tipo di attacco**|**Descrizione**|
|---|---|
|**Attacco dizionario offline**|L’attaccante genera un elenco di password probabili e le confronta con gli hash rubati.|
|**Attacco specifico all’account**|Mira a un singolo utente, tentando password legate alle sue informazioni personali.|
|**Attacco a password popolari**|Tenta un insieme ristretto di password comunemente usate (es. “123456”, “password”, “qwerty”).|
|**Indovinare la password**|Approccio sistematico o casuale verso un solo utente.|
|**Workstation hijacking**|L’attaccante sfrutta una sessione già autenticata lasciata aperta.|
|**Sfruttare errori dell’utente**|Riutilizzo o scrittura delle password in luoghi non sicuri.|
|**Uso di password multiple**|Una password compromessa può compromettere anche altri servizi.|
|**Shoulder surfing**|Osservazione diretta mentre l’utente digita le credenziali.|
|**Social engineering**|Manipolazione psicologica per ottenere la password.|

---

### **7. Memorizzazione sicura delle password (Unix)**

Nei sistemi **Unix/Linux**, le password non vengono salvate in chiaro, ma in forma **hashata** con l’aggiunta di un **salt casuale**.

#### **Schema**

- Si memorizza la coppia **(r, H(password, r))**, dove:
    
    - `r` è un valore casuale unico per ogni password (salt);
        
    - `H` è una funzione di hash crittografica.
        
- Il salt è **pubblico**, ma impedisce il riutilizzo di tabelle precalcolate (Rainbow Tables).
    

#### **Vantaggi**

- Rende gli **attacchi a dizionario** molto più difficili.
    
- Evita che due utenti con la stessa password abbiano lo stesso hash.
    
- Limita il danno in caso di furto parziale del database delle password.
    

---

### **8. Implementazioni storiche in UNIX**

#### **Schema originale**

- Password fino a **8 caratteri stampabili**.
    
- Salt di **12 bit**, usato per modificare il comportamento del **DES** come funzione hash.
    
- Il valore “0” veniva cifrato **25 volte** e poi convertito in 11 caratteri.
    
- Oggi è considerato **insufficiente** per la potenza di calcolo moderna.
    

#### **Evoluzioni successive**

- Uso di **hash MD5** con salt fino a **48 bit** e lunghezza password illimitata.
    
- Hash da **128 bit**, con **1000 iterazioni** interne per rallentare gli attacchi.
    
- Nei sistemi moderni (es. **OpenBSD**) è adottato **bcrypt**, basato su **Blowfish**, con:
    
    - salt da **128 bit**;
        
    - hash da **192 bit**;
        
    - elevata resistenza a brute force e Rainbow Tables.
        

---

### **9. Tecniche di cracking**

|**Tecnica**|**Descrizione**|
|---|---|
|**Attacchi a dizionario**|Ogni parola di un dizionario è trasformata in hash (con tutti i salt) e confrontata con i valori memorizzati.|
|**Tabelle Rainbow**|Tabelle precalcolate di hash per ridurre il tempo di ricerca, ma inefficaci contro salt lunghi e hash robusti.|
|**Forza bruta (brute force)**|Prova sistematicamente tutte le combinazioni possibili di caratteri.|
|**Password Cracker Tools**|Software dedicati come **John the Ripper** (1996), che combina forza bruta e dizionario con ottimizzazioni avanzate.|

---

### **10. Approcci moderni alla protezione delle password**

L’aumento della potenza di calcolo ha reso necessarie **politiche di sicurezza più rigide**:

#### **Misure adottate**

- Obbligo di **password complesse** e con lunghezza minima.
    
- Divieto di riutilizzo di password precedenti.
    
- Controlli automatici sulla qualità delle password.
    
- Scadenza periodica delle credenziali.
    
- Verifica contro dizionari di password compromesse.
    

Tuttavia, la complessità eccessiva può ridurre l’usabilità, spingendo gli utenti a **comportamenti insicuri** (es. scrivere le password o riutilizzarle).

---

### **11. Meccanismi per evitare password deboli**

Per migliorare la sicurezza mantenendo l’usabilità, si adottano alcune **strategie preventive**:

- **Consentire passphrase lunghe** (più facili da ricordare ma resistenti).
    
- **Generare automaticamente password casuali**.
    
- **Verificare la qualità** delle password scelte dagli utenti.
    
- **Usare regole dinamiche** e strumenti di controllo automatizzati.
    
- **Fornire linee guida** o suggerimenti per creare password forti:
    
    > “Pensa a una frase e prendi le iniziali o parti di parole, mescolando lettere, numeri e simboli.”  
    > Esempio:  
    > “It’s 12 and I am hungry” → “I’S12&IAH”
    

---

### **12. Sintesi finale**

- L’autenticazione basata su password resta **lo standard più comune**, ma anche **il più vulnerabile**.
    
- I metodi moderni uniscono **hash robusti, salt lunghi e controlli di qualità**.
    
- La sicurezza deve sempre bilanciare **robustezza e usabilità**, poiché una password sicura ma difficile da gestire porta spesso a comportamenti rischiosi.
    

> **In sintesi:** la vera forza di un sistema di autenticazione non sta nella complessità della password, ma nel **modello di gestione complessivo** che la protegge.


---