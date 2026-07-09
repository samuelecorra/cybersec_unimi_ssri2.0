## **Lezione 4: Attacchi a SSL/TLS**

### **1. Introduzione**

Il protocollo **TLS 1.3** rappresenta una profonda revisione rispetto alle versioni precedenti di SSL e TLS, con l’obiettivo di:

- **semplificare** l’architettura,
    
- **rimuovere** le funzioni insicure o obsolete,
    
- **aumentare** la privacy e le prestazioni,
    
- e **prevenire** gli attacchi che avevano colpito le versioni 1.2 e inferiori.
    

TLS 1.3 nasce proprio come risposta alle vulnerabilità accumulate in più di vent’anni di evoluzione del protocollo.

Il punto di partenza è l’analisi critica di TLS 1.2 e delle versioni precedenti: alcune funzionalità erano poco usate, altre erano difficili da implementare correttamente, altre ancora erano ormai considerate insicure alla luce dell’evoluzione della crittoanalisi e degli attacchi pratici.

> 📌 TLS 1.3 non aggiunge semplicemente nuove opzioni: elimina molte scelte legacy per impedire che client e server possano negoziare accidentalmente configurazioni deboli.

---

### **2. Obiettivi principali di TLS 1.3**

TLS 1.3 introduce quattro obiettivi fondamentali:

1. **Pulizia del protocollo (Clean-up):**  
    Eliminare tutte le funzionalità non più necessarie o potenzialmente pericolose.
    
2. **Sicurezza:**  
    Rafforzare il protocollo utilizzando tecniche di crittografia moderne, risultati delle analisi formali e algoritmi oggi considerati robusti.
    
3. **Privacy:**  
    Aumentare la protezione dei dati trasmessi, compreso il mascheramento di metadati come il nome del server (SNI).
    
4. **Prestazioni:**  
    Ridurre la latenza di connessione con:
    
    - **Handshake 1-RTT** (una singola transazione di andata e ritorno) per le nuove connessioni,
        
    - **Handshake 0-RTT** per connessioni già note (riutilizzando chiavi precedenti).
        

Il miglioramento delle prestazioni riguarda soprattutto il protocollo di handshake: l’obiettivo è ridurre il numero di messaggi necessari quando si apre una nuova connessione e permettere, in casi controllati, la ripresa di sessioni già stabilite in precedenza.

#### **Funzionalità rimosse in TLS 1.3**

TLS 1.3 elimina completamente:

- Static **RSA** per lo scambio delle chiavi, perché non garantisce forward secrecy e concentra troppa sicurezza sulla chiave privata a lungo termine del server,
    
- gruppi personalizzati (EC)DHE non sufficientemente analizzati o non considerati adeguati,
    
- **Compressione** (causa di vulnerabilità come CRIME),
    
- **Renegotiation** (problemi di sicurezza e complessità),
    
- Cifrari non **AEAD** (Authenticated Encryption with Associated Data),
    
- Meccanismi complessi di **session resumption** (sostituiti da un modello semplificato).
    

> ⚠️ La rimozione di funzionalità è una misura di sicurezza: se una scelta debole non è più negoziabile, un attaccante non può forzarne l’uso tramite downgrade o manipolazione dell’handshake.

---

### **3. Compressione e cifrari vietati**

#### **a. Compressione**

La compressione dei dati era originariamente inclusa per ottimizzare le prestazioni, ma si è rivelata un **vettore di attacco**.  
Infatti:

- Alcuni attacchi (es. **CRIME** e **BREACH**) sfruttano la correlazione tra testo compresso e testo cifrato per dedurre informazioni sensibili.
    
- Non esiste un modo “generico” per usare la compressione in modo sicuro nel contesto crittografico.
    

**TLS 1.3** quindi **proibisce completamente la compressione**:  
un server TLS 1.3 **deve rifiutare** la connessione se il client propone di usarla.

La ragione è strutturale: comprimere prima di cifrare può far dipendere la lunghezza del testo cifrato da somiglianze tra dati segreti e dati controllati dall’attaccante. Questo apre canali laterali basati sulla lunghezza.

#### **b. Cifrari non-AEAD**

TLS 1.3 supporta solo cifrari con **Authenticated Encryption with Associated Data (AEAD)**, una forma avanzata di crittografia che:

- garantisce **confidenzialità** e **integrità** simultaneamente,
    
- protegge da attacchi di tipo **CCA (Chosen Ciphertext Attack)**,
    
- verifica non solo i dati cifrati ma anche i **dati associati** (header, contesto, ecc.).
    

Questo impedisce attacchi di tipo _cut-and-paste_, dove un aggressore tenta di incollare porzioni di testo cifrato in un contesto diverso.

La parte “Associated Data” è importante perché permette di autenticare anche informazioni non cifrate ma semanticamente legate al record, come header o contesto del messaggio. In questo modo un testo cifrato valido in un contesto non può essere semplicemente riutilizzato in un altro senza che la verifica fallisca.

#### **Esempi di cifrari AEAD supportati:**

- **AES-GCM**
    
- **AES-CCM**
    
- **ARIA-GCM**
    
- **Camellia-GCM**
    
- **ChaCha20-Poly1305**
    

Le vecchie modalità come **RC4**, **AES-CBC** (in modalità _MAC-then-Encrypt_) e altri algoritmi simili sono **vietati** in TLS 1.3.

> 📌 TLS 1.3 accetta solo cifrari per cui la protezione di confidenzialità e integrità è progettata come un unico meccanismo coerente.

---

### **4. Fasi di handshake: analisi tecnica**

Per comprendere meglio dove si inseriscono gli attacchi, ripercorriamo le fasi chiave dell’**handshake** (qui mostrate nella logica delle versioni 1.2 e 3.0, dove le vulnerabilità erano più frequenti).

#### **a. ClientHello**

Il client avvia la negoziazione inviando:

- la versione massima del protocollo che supporta;

- le combinazioni di cifratura e MAC che è disposto a usare;

- valori casuali, cioè nonce, che contribuiranno alla generazione delle chiavi;

- eventuali altri parametri di sessione.

Queste informazioni delimitano lo spazio di scelta del server: un attacco che le altera può condizionare l’intera connessione.

#### **b. ServerHello**

Il server risponde al client in chiaro con:

- la **versione più alta** del protocollo supportata da entrambi,
    
- la **cipher suite** selezionata tra quelle offerte dal client,
    
- un **numero casuale (nonce)** per contribuire alla generazione delle chiavi.
    

Il server dovrebbe scegliere la versione più sicura tra quelle effettivamente supportate da entrambe le parti, non una versione arbitrariamente più vecchia. Anche il nonce del server deve essere fresco, cioè non riutilizzato in precedenza.

![](imgs/Pasted%20image%2020260709012532.png)

#### **c. ServerKeyExchange**

Il server invia:

- il **certificato pubblico**, contenente per esempio una chiave RSA o parametri/chiavi pubbliche Diffie-Hellman,
    
- i **parametri di chiave pubblica** coerenti con la suite crittografica scelta.
    

Il client deve verificare il certificato del server prima di fidarsi della chiave pubblica ricevuta. Se questa verifica viene saltata o aggirata, l’intero handshake può essere esposto a Man-in-the-Middle.

![](imgs/Pasted%20image%2020260709012618.png)

#### **d. ClientKeyExchange**

Il client:

- genera il **materiale segreto** (pre-master secret),
    
- lo **cifra con la chiave pubblica del server**,
    
	- e lo invia al server, che potrà decifrarlo solo con la propria chiave privata.
    
![](imgs/Pasted%20image%2020260709012641.png)

> 📌 Molti attacchi storici colpiscono proprio questa fase: se l’attaccante riesce a modificare versione, cipher suite o chiave pubblica accettata, la cifratura successiva può essere formalmente attiva ma sostanzialmente debole.

---

### **5. Struttura formale (RFC)**

Nel linguaggio dell’RFC, i messaggi chiave dell’handshake sono descritti come segue:

```c
struct {
  select (KeyExchangeAlgorithm) {
    case rsa: EncryptedPreMasterSecret;
    case diffie_hellman: ClientDiffieHellmanPublic;
  } exchange_keys;
} ClientKeyExchange;

struct {
  ProtocolVersion client_version;
  opaque random[46];
} PreMasterSecret;
```

Il _PreMasterSecret_ contiene i **bit casuali** da cui verranno derivate le chiavi simmetriche di sessione, combinati con i _nonce_ del client e del server.

Nel caso RSA classico, il campo `random[46]` rappresenta il materiale casuale generato dal client. Questi bit vengono poi combinati con i valori casuali scambiati nell’handshake tramite funzioni di derivazione, fino a ottenere le chiavi simmetriche effettive.

---

### **6. Importanza della casualità**

Un sistema di cifratura è sicuro solo quanto lo è la sua **sorgente di numeri casuali**.  
Un caso storico evidenzia quanto una minima vulnerabilità possa compromettere la sicurezza globale.

#### **Caso Debian Linux (2006–2008)**

- Una singola riga di codice in OpenSSL (`MD_Update(&m, buf, j);`) fu **commentata per errore**.
    
- Senza quella riga, il generatore pseudocasuale usava solo il **Process ID** come seed.
    
- Poiché il PID massimo in Linux è 32768, ciò significava che esistevano solo **32768 chiavi possibili**.

![](imgs/Pasted%20image%2020260709012811.png)

Conseguenze:

- Tutte le chiavi generate su sistemi Debian-based in quel periodo erano **prevedibili**.
    
- Furono compromessi certificati **X.509**, chiavi **SSH**, **OpenVPN**, **DNSSEC**, e persino **chiavi di sessione TLS**.
    

Per un attaccante, uno spazio di 32768 possibilità è enumerabile. Diventa quindi realistico provare tutti i semi possibili, ricostruire le chiavi candidate e verificare quale corrisponde al traffico osservato.

Un esempio perfetto di come **una singola linea di codice** può minare la fiducia di un intero ecosistema: se la casualità è prevedibile, le chiavi derivate non offrono più confidenzialità né integrità affidabile.

> ⚠️ La sicurezza di TLS dipende anche dall’implementazione. Un protocollo teoricamente robusto può diventare insicuro se la generazione dei numeri casuali è sbagliata.

---

### **7. Version Rollback Attack**

Uno dei principali attacchi contro SSL è il **Version Rollback Attack** (attacco di retrocessione).

#### **Funzionamento:**

1. Il client propone di usare la versione più recente (es. SSL 3.0).
    
2. L’attaccante in-the-middle intercetta il messaggio e lo modifica, facendolo apparire come **SSL 2.0**.
    
3. Il server, credendo che il client supporti solo SSL 2.0, risponde di conseguenza.
    
4. Entrambi finiscono per comunicare con una **versione vecchia e vulnerabile** del protocollo.

![](imgs/Pasted%20image%2020260709012912.png)

#### **Conseguenza:**

SSL 2.0 non prevedeva i messaggi “Finished” finali dell’handshake, quindi l’attaccante poteva manipolare la connessione senza essere rilevato.

> In pratica: l’aggressore forza le parti a usare un protocollo debole, poi sfrutta le vulnerabilità di quella versione.

![](imgs/Pasted%20image%2020260709012926.png)

Il problema nasce perché la parte iniziale dell’handshake, nelle versioni storiche, non era ancora protetta in modo sufficiente. Un attaccante poteva intervenire prima che client e server avessero stabilito chiavi e autenticazione complete, alterando i parametri che sarebbero poi stati usati per proteggere la sessione.

---

### **8. Debolezze di SSL 2.0 (corrette in 3.0)**

SSL 2.0, oggi completamente deprecato (vietato dal 2011), soffriva di gravi problemi strutturali:

1. **Cipher Suite Rollback Attack**  
    Le preferenze di cifratura non erano autenticate → l’attaccante poteva alterare i messaggi e far credere al server che il client supportasse solo cifrari deboli.
    
2. **Messaggi non protetti durante l’handshake**  
    Il client poteva inviare messaggi di cambio delle specifiche di cifratura in chiaro → un attaccante poteva anticipare o manipolare l’aggiornamento dei parametri di sicurezza.
    
3. **Hashing debole**  
    L’autenticazione dei messaggi usava **MD5**, vulnerabile a collisioni.
    
4. **MAC in export mode**  
    La versione “export” usava solo **40 bit di chiave** per motivi legali (restrizioni USA anni ’90).
    
5. **Padding non autenticato**  
    Il padding usato per il calcolo del MAC non era verificato: un attaccante poteva rimuovere byte dal messaggio senza essere scoperto.
    
6. **Limitato supporto per certificati**  
    Non gestiva catene di certificati né algoritmi non-RSA.
    

Questi problemi spiegano perché SSL 2.0 non è semplicemente “vecchio”, ma strutturalmente inadatto: permetteva all’attaccante di intervenire su scelte che avrebbero dovuto essere protette dalla negoziazione stessa.

#### **Soluzione in SSL 3.0**

Tutte queste debolezze furono corrette in SSL 3.0:

- autenticazione delle suite di cifratura,
    
- handshake firmato e verificabile,
    
- uso di funzioni hash più sicure,
    
- struttura del MAC migliorata.
    

---

### **9. Conclusione**

TLS 1.3 rappresenta oggi il punto d’arrivo di una lunga evoluzione:

- ha **rimosso le funzionalità pericolose**,
    
- ha **unificato la sicurezza e l’efficienza**,
    
- e ha **chiuso tutte le falle storiche** di SSL e TLS 1.2.
    

Le lezioni apprese dagli attacchi precedenti — da Debian Bug al Version Rollback — hanno portato alla definizione di un protocollo più **robusto, snello e matematicamente fondato**.

> TLS 1.3 non è semplicemente un aggiornamento tecnico, ma una vera e propria **riforma della sicurezza Internet**.
