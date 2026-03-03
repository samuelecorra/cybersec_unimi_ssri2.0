## **Lezione 4: Attacchi a SSL/TLS**

### **1. Introduzione**

Il protocollo **TLS 1.3** rappresenta una profonda revisione rispetto alle versioni precedenti di SSL e TLS, con l’obiettivo di:

- **semplificare** l’architettura,
    
- **rimuovere** le funzioni insicure o obsolete,
    
- **aumentare** la privacy e le prestazioni,
    
- e **prevenire** gli attacchi che avevano colpito le versioni 1.2 e inferiori.
    

TLS 1.3 nasce proprio come risposta alle vulnerabilità accumulate in più di vent’anni di evoluzione del protocollo.

---

### **2. Obiettivi principali di TLS 1.3**

TLS 1.3 introduce quattro obiettivi fondamentali:

1. **Pulizia del protocollo (Clean-up):**  
    Eliminare tutte le funzionalità non più necessarie o potenzialmente pericolose.
    
2. **Sicurezza:**  
    Rafforzare il protocollo utilizzando tecniche di crittografia moderne e risultati delle analisi formali.
    
3. **Privacy:**  
    Aumentare la protezione dei dati trasmessi, compreso il mascheramento di metadati come il nome del server (SNI).
    
4. **Prestazioni:**  
    Ridurre la latenza di connessione con:
    
    - **Handshake 1-RTT** (una singola transazione di andata e ritorno) per le nuove connessioni,
        
    - **Handshake 0-RTT** per connessioni già note (riutilizzando chiavi precedenti).
        

#### **Funzionalità rimosse in TLS 1.3**

TLS 1.3 elimina completamente:

- Static **RSA** (chiavi fisse vulnerabili),
    
- Gruppi personalizzati (EC)DHE,
    
- **Compressione** (causa di vulnerabilità come CRIME),
    
- **Renegotiation** (problemi di sicurezza e complessità),
    
- Cifrari non **AEAD** (Authenticated Encryption with Associated Data),
    
- Meccanismi complessi di **session resumption** (sostituiti da un modello semplificato).
    

---

### **3. Compressione e cifrari vietati**

#### **a. Compressione**

La compressione dei dati era originariamente inclusa per ottimizzare le prestazioni, ma si è rivelata un **vettore di attacco**.  
Infatti:

- Alcuni attacchi (es. **CRIME** e **BREACH**) sfruttano la correlazione tra testo compresso e testo cifrato per dedurre informazioni sensibili.
    
- Non esiste un modo “generico” per usare la compressione in modo sicuro nel contesto crittografico.
    

**TLS 1.3** quindi **proibisce completamente la compressione**:  
un server TLS 1.3 **deve rifiutare** la connessione se il client propone di usarla.

#### **b. Cifrari non-AEAD**

TLS 1.3 supporta solo cifrari con **Authenticated Encryption with Associated Data (AEAD)**, una forma avanzata di crittografia che:

- garantisce **confidenzialità** e **integrità** simultaneamente,
    
- protegge da attacchi di tipo **CCA (Chosen Ciphertext Attack)**,
    
- verifica non solo i dati cifrati ma anche i **dati associati** (header, contesto, ecc.).
    

Questo impedisce attacchi di tipo _cut-and-paste_, dove un aggressore tenta di incollare porzioni di testo cifrato in un contesto diverso.

#### **Esempi di cifrari AEAD supportati:**

- **AES-GCM**
    
- **AES-CCM**
    
- **ARIA-GCM**
    
- **Camellia-GCM**
    
- **ChaCha20-Poly1305**
    

Le vecchie modalità come **RC4**, **AES-CBC** (in modalità _MAC-then-Encrypt_) e altri algoritmi simili sono **vietati** in TLS 1.3.

---

### **4. Fasi di handshake: analisi tecnica**

Per comprendere meglio dove si inseriscono gli attacchi, ripercorriamo le fasi chiave dell’**handshake** (qui mostrate nella logica delle versioni 1.2 e 3.0, dove le vulnerabilità erano più frequenti).

#### **a. ServerHello**

Il server risponde al client in chiaro con:

- la **versione più alta** del protocollo supportata da entrambi,
    
- la **cipher suite** selezionata tra quelle offerte dal client,
    
- un **numero casuale (nonce)** per contribuire alla generazione delle chiavi.
    

#### **b. ServerKeyExchange**

Il server invia:

- il **certificato pubblico** (RSA o Diffie-Hellman),
    
- i **parametri di chiave pubblica** coerenti con la suite crittografica scelta.
    

#### **c. ClientKeyExchange**

Il client:

- genera il **materiale segreto** (pre-master secret),
    
- lo **cifra con la chiave pubblica del server**,
    
- e lo invia al server, che potrà decifrarlo solo con la propria chiave privata.
    

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

---

### **6. Importanza della casualità**

Un sistema di cifratura è sicuro solo quanto lo è la sua **sorgente di numeri casuali**.  
Un caso storico evidenzia quanto una minima vulnerabilità possa compromettere la sicurezza globale.

#### **Caso Debian Linux (2006–2008)**

- Una singola riga di codice in OpenSSL (`MD_Update(&m, buf, j);`) fu **commentata per errore**.
    
- Senza quella riga, il generatore pseudocasuale usava solo il **Process ID** come seed.
    
- Poiché il PID massimo in Linux è 32768, ciò significava che esistevano solo **32768 chiavi possibili**.
    

Conseguenze:

- Tutte le chiavi generate su sistemi Debian-based in quel periodo erano **prevedibili**.
    
- Furono compromessi certificati **X.509**, chiavi **SSH**, **OpenVPN**, **DNSSEC**, e persino **chiavi di sessione TLS**.
    

Un esempio perfetto di come **una singola linea di codice** può minare la fiducia di un intero ecosistema.

---

### **7. Version Rollback Attack**

Uno dei principali attacchi contro SSL è il **Version Rollback Attack** (attacco di retrocessione).

#### **Funzionamento:**

1. Il client propone di usare la versione più recente (es. SSL 3.0).
    
2. L’attaccante in-the-middle intercetta il messaggio e lo modifica, facendolo apparire come **SSL 2.0**.
    
3. Il server, credendo che il client supporti solo SSL 2.0, risponde di conseguenza.
    
4. Entrambi finiscono per comunicare con una **versione vecchia e vulnerabile** del protocollo.
    

#### **Conseguenza:**

SSL 2.0 non prevedeva i messaggi “Finished” finali dell’handshake, quindi l’attaccante poteva manipolare la connessione senza essere rilevato.

> In pratica: l’aggressore forza le parti a usare un protocollo debole, poi sfrutta le vulnerabilità di quella versione.

---

### **8. Debolezze di SSL 2.0 (corrette in 3.0)**

SSL 2.0, oggi completamente deprecato (vietato dal 2011), soffriva di gravi problemi strutturali:

1. **Cipher Suite Rollback Attack**  
    Le preferenze di cifratura non erano autenticate → l’attaccante poteva costringere l’uso di un algoritmo debole.
    
2. **Messaggi non protetti durante l’handshake**  
    Il client poteva inviare _Change Cipher Spec_ in chiaro → un attaccante poteva intercettare o bloccare l’aggiornamento dei parametri di sicurezza.
    
3. **Hashing debole**  
    L’autenticazione dei messaggi usava **MD5**, vulnerabile a collisioni.
    
4. **MAC in export mode**  
    La versione “export” usava solo **40 bit di chiave** per motivi legali (restrizioni USA anni ’90).
    
5. **Padding non autenticato**  
    Il padding usato per il calcolo del MAC non era verificato: un attaccante poteva rimuovere byte dal messaggio senza essere scoperto.
    
6. **Limitato supporto per certificati**  
    Non gestiva catene di certificati né algoritmi non-RSA.
    

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