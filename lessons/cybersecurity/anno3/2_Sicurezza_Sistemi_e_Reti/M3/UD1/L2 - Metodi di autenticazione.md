## **Lezione 2: Metodi di autenticazione**

### **1. Introduzione**

L’autenticazione è la **prima linea di difesa** contro gli accessi non autorizzati.  
Serve a **verificare l’identità dell’utente** e ad assegnargli i **privilegi appropriati** per l’accesso alle risorse del sistema.  
Questa lezione presenta i principali **metodi di autenticazione**: basati su **conoscenza**, **possesso**, **biometria** e **combinazioni multifattore**.

Il focus principale è l’autenticazione basata su password, perché è ancora il meccanismo più diffuso nei sistemi operativi e nei servizi online, ma anche uno dei più esposti a errori di implementazione, cattive scelte dell’utente e attacchi automatizzati.

---

### **2. Metodi di autenticazione dell’identità**

L’autenticazione può basarsi su quattro diverse categorie di elementi, ognuna associata a una modalità distinta di verifica:

|**Categoria**|**Descrizione**|**Esempi**|
|---|---|---|
|**Qualcosa che l’individuo sa**|Si fonda sulla **conoscenza di un’informazione segreta** condivisa tra utente e sistema.|Password, PIN, risposte a domande segrete|
|**Qualcosa che l’individuo possiede (token)**|Richiede il **possesso fisico o digitale** di un oggetto di autenticazione.|Smart card, chiavi USB, token hardware, tessere elettroniche|
|**Qualcosa che l’individuo è (biometria statica)**|Basata su **caratteristiche fisiche uniche** e difficilmente replicabili.|Impronta digitale, viso, retina, geometria della mano|
|**Qualcosa che l’individuo fa (biometria dinamica)**|Si basa su **comportamenti unici e ripetitivi** nel tempo.|Ritmo di digitazione, voce, firma, modo di camminare|

Nel caso del **possesso**, la prova può consistere nell’inserimento fisico di una smart card in un lettore oppure nell’uso di un token che genera codici dinamici. L’idea è che l’attaccante non debba limitarsi a conoscere un segreto: deve anche disporre dell’oggetto corretto.

Nel caso biometrico è utile distinguere tra caratteristiche **fisiche statiche** e caratteristiche **comportamentali**. Le prime includono impronte digitali, retina, volto, geometria della mano o pattern vascolari; le seconde includono intonazione della voce, ritmo di digitazione sulla tastiera e modalità di firma o scrittura.

---

### **3. Autenticazione a più fattori (MFA)**

L’**autenticazione multifattore** combina **due o più categorie diverse** per rafforzare la sicurezza.  
Per esempio:

- Password + token
    
- Smart card + impronta digitale
    
- App mobile + riconoscimento facciale
    

Questa tecnica riduce drasticamente la probabilità che un attaccante possa impersonare un utente, anche in caso di furto di uno dei fattori.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Un esempio tipico è l’accesso a un servizio web con password seguito dalla richiesta di un codice ricevuto tramite SMS o generato da un’app dedicata. In questo caso la password dimostra qualcosa che l’utente **sa**, mentre il codice temporaneo dimostra il possesso di un dispositivo o di un token associato all’account.

> 📌 La MFA è efficace solo se combina fattori realmente distinti. Due password diverse non costituiscono vera autenticazione multifattore: sono sempre due prove basate sulla conoscenza.

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

Il nome utente o login non serve soltanto a selezionare la password corretta da verificare: identifica anche il profilo a cui il sistema assocerà privilegi, risorse accessibili e operazioni consentite. Per questo autenticazione e autorizzazione sono distinte, ma strettamente collegate.
    

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

La prima contromisura di base è proteggere il canale tra client e server: una password non deve mai essere trasmessa in chiaro. Tuttavia, un canale cifrato non elimina tutti i rischi: il server può essere compromesso e rivelare il database delle credenziali, mentre il client può essere compromesso da malware, keylogger o componenti malevole del browser.

Gli attacchi di ingegneria sociale sfruttano invece la componente umana: l’attaccante induce l’utente a digitare, comunicare o salvare la password in modo insicuro, ad esempio tramite phishing, pretesti credibili o pagine di login contraffatte.
    

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

#### **Contromisure principali**

- Per gli **attacchi dizionario offline**, la difesa primaria è impedire l’accesso al file delle password e memorizzare solo valori hashati e salati. Se l’attaccante ottiene il database e conosce la procedura di hashing, può calcolare hash candidati e confrontarli con quelli memorizzati.
    
- Per gli **attacchi mirati a un singolo account**, è essenziale limitare il numero di tentativi, introdurre ritardi progressivi o blocchi temporanei e monitorare gli accessi anomali.
    
- Per gli **attacchi a password popolari**, serve una policy che impedisca la scelta di password presenti in dizionari, liste comuni o raccolte di credenziali compromesse.
    
- Per gli attacchi basati sulla **conoscenza della vittima**, occorre educare gli utenti a non scegliere password legate a dati personali facilmente ricavabili e a non conservarle in file non protetti o su supporti fisici visibili.
    
- Per il **workstation hijacking**, servono blocco automatico dello schermo, timeout di inattività e richiesta di nuove credenziali dopo un periodo senza attività.
    
- Per gli **errori dell’utente**, la difesa passa da formazione, MFA, sistemi di rilevamento delle intrusioni e, per account privilegiati, password generate automaticamente con caratteristiche di robustezza elevate.
    
- Per il **riutilizzo delle password**, la regola corretta è usare credenziali diverse per contesti diversi: una password rubata in un servizio non deve permettere accesso anche ad altri account.
    
- Per **keylogger, shoulder surfing e phishing**, servono protezione del client, attenzione all’ambiente fisico, verifica del sito su cui si inseriscono credenziali e meccanismi aggiuntivi come MFA o token temporanei.
    
> ⚠️ Un sistema di autenticazione può essere crittograficamente corretto ma comunque vulnerabile se non limita i tentativi, non protegge gli endpoint o consente password deboli e riutilizzate.

---

### **7. Memorizzazione sicura delle password (Unix)**

Nei sistemi **Unix/Linux**, le password non vengono salvate in chiaro, ma in forma **hashata** con l’aggiunta di un **salt casuale**.

#### **Schema**

- Si memorizza la coppia **(r, H(password, r))**, dove:
    
    - `r` è un valore casuale unico per ogni password (salt);
        
    - `H` è una funzione di hash crittografica.
        
- Il salt è **pubblico**, ma impedisce il riutilizzo di tabelle precalcolate (Rainbow Tables).

In fase di verifica, il sistema recupera il salt associato all’utente, ricalcola l’hash della password inserita usando quello stesso salt e confronta il risultato con il valore memorizzato. La password in chiaro non deve quindi essere salvata.
    

#### **Vantaggi**

- Rende gli **attacchi a dizionario** molto più difficili.
    
- Evita che due utenti con la stessa password abbiano lo stesso hash.
    
- Limita il danno in caso di furto parziale del database delle password.

Il salt produce anche un effetto importante sugli utenti che scelgono la stessa password: poiché il valore casuale è diverso, gli hash memorizzati risultano diversi. L’attaccante non può quindi dedurre immediatamente che due account condividono la stessa password.
    

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

L’evoluzione da DES a MD5 e poi a bcrypt mostra un principio generale: gli schemi di memorizzazione delle password devono essere aggiornati quando cresce la potenza di calcolo disponibile agli attaccanti. Un algoritmo accettabile in un certo periodo può diventare insufficiente pochi anni dopo.
        

---

### **9. Tecniche di cracking**

|**Tecnica**|**Descrizione**|
|---|---|
|**Attacchi a dizionario**|Ogni parola di un dizionario è trasformata in hash (con tutti i salt) e confrontata con i valori memorizzati.|
|**Tabelle Rainbow**|Tabelle precalcolate di hash per ridurre il tempo di ricerca, ma inefficaci contro salt lunghi e hash robusti.|
|**Forza bruta (brute force)**|Prova sistematicamente tutte le combinazioni possibili di caratteri.|
|**Password Cracker Tools**|Software dedicati come **John the Ripper** (1996), che combina forza bruta e dizionario con ottimizzazioni avanzate.|

Il cracking resta essenzialmente un confronto: il programma genera una password candidata, applica la stessa procedura di hashing usata dal sistema, eventualmente con il salt corretto, e verifica se l’output coincide con quello memorizzato.

Le **Rainbow Tables** riducono il tempo di calcolo tramite precomputazione e possono essere condivise o reperite online, ma perdono efficacia quando ogni password è associata a un salt lungo e casuale. Strumenti come **John the Ripper** combinano dizionari, forza bruta e generatori di pattern, producendo varianti automatiche delle parole più probabili.

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

La scadenza periodica deve quindi essere calibrata: imporre cambi troppo frequenti può peggiorare la sicurezza, perché l’utente tende a scegliere password prevedibili, annotarle su supporti insicuri o modificarle solo in modo minimo.

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

L’obiettivo è aumentare lo spazio di ricerca per l’attaccante senza rendere la password ingestibile per l’utente. Per questo si combinano lunghezza, lettere maiuscole e minuscole, numeri, caratteri speciali e, quando possibile, generazione casuale o passphrase robuste.

Un controllo di qualità efficace può anche simulare attacchi a dizionario sulla password proposta: se una password è facilmente indovinabile dagli stessi strumenti usati dall’attaccante, non dovrebbe essere accettata.
    

---

### **12. Sintesi finale**

- L’autenticazione basata su password resta **lo standard più comune**, ma anche **il più vulnerabile**.
    
- I metodi moderni uniscono **hash robusti, salt lunghi e controlli di qualità**.
    
- La sicurezza deve sempre bilanciare **robustezza e usabilità**, poiché una password sicura ma difficile da gestire porta spesso a comportamenti rischiosi.

- Le contromisure efficaci non sono solo tecniche: includono policy, educazione dell’utente, protezione del canale, protezione degli endpoint e corretta memorizzazione lato server.
    

> **In sintesi:** la vera forza di un sistema di autenticazione non sta nella complessità della password, ma nel **modello di gestione complessivo** che la protegge.


---
