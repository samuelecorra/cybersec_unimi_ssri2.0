La **crittografia** è la scienza che studia i metodi per **proteggere l’informazione** trasformandola in una forma **incomprensibile a chi non è autorizzato** a leggerla.  
Il suo scopo è garantire che la comunicazione tra due o più parti rimanga **riservata, autentica e integra** anche in presenza di un avversario.

In termini formali, la crittografia progetta **funzioni matematiche difficili da invertire** senza la conoscenza di una chiave segreta.  
Essa rappresenta la base della **sicurezza informatica moderna**, su cui si fondano protocolli come HTTPS, VPN, firme digitali, blockchain e autenticazioni online.

---

### 1. Origini e sviluppo

L’arte di nascondere messaggi è antichissima: risale ai cifrari militari dell’antica Grecia e Roma (come il **Cifrario di Cesare**).  
Tuttavia, la crittografia moderna nasce nel XX secolo grazie a:

- la formalizzazione **matematica e algoritmica** dei cifrari,
    
- lo sviluppo di **calcolatori elettronici**,
    
- e il lavoro pionieristico di studiosi come **Claude Shannon** (padre della teoria dell’informazione).
    

Oggi la crittografia è una **disciplina scientifica rigorosa**, che unisce:

- **matematica** (algebra, teoria dei numeri, probabilità),
    
- **informatica** (algoritmi e complessità computazionale),
    
- **ingegneria dei sistemi** (protocol design, reti, sicurezza applicata).
    

---

### 2. Obiettivi della crittografia

La crittografia si propone di garantire quattro proprietà fondamentali, dette anche **obiettivi di sicurezza**:

1. **Riservatezza (Confidentiality)**  
    Solo i soggetti autorizzati devono poter leggere le informazioni.
    
2. **Integrità (Integrity)**  
    Il messaggio non deve essere alterato durante la trasmissione.
    
3. **Autenticazione (Authentication)**  
    Il destinatario deve poter verificare l’identità del mittente.
    
4. **Non ripudio (Non-repudiation)**  
    Chi invia un messaggio non deve poter negare di averlo fatto.
    

---

### 3. Struttura di un sistema crittografico

Un sistema crittografico è composto da:

- un **messaggio in chiaro** (_plaintext_)
    
- una **funzione di cifratura** $E_k(m)$ che, con una chiave $k$, produce il **testo cifrato** (_ciphertext_)
    
- una **funzione di decifratura** $D_k(c)$ che, usando la stessa o un’altra chiave, ricostruisce il messaggio originale
    

$$  
\begin{cases}  
c = E_k(m) \  
m = D_k(c)  
\end{cases}  
$$

Un sistema è **sicuro** se, senza conoscere la chiave, risulta **computazionalmente impraticabile** risalire al messaggio originario.

---

### 4. Tipologie di crittografia

#### **Crittografia simmetrica**

- Usa **la stessa chiave** per cifrare e decifrare.
    
- È veloce, adatta per grandi quantità di dati (es. AES, DES).
    
- Problema principale: **distribuzione sicura delle chiavi**.
    

#### **Crittografia asimmetrica**

- Usa **una coppia di chiavi**: una pubblica e una privata.
    
- Permette la **cifratura, la firma e lo scambio sicuro delle chiavi** (es. RSA, Diffie-Hellman, ECC).
    
- È più lenta, ma essenziale per l’infrastruttura a chiave pubblica (PKI).
    

---

### 5. Componenti fondamentali dello studio

Durante il corso, studieremo le principali famiglie di algoritmi e le loro proprietà:

1. **Crittografia classica** – dai cifrari storici (Cesare, Vigenère) fino all’idea moderna di sicurezza.
    
2. **Crittografia simmetrica** – cifrari a blocchi, strutture di Feistel, DES e AES.
    
3. **Crittografia asimmetrica** – RSA, El-Gamal, curve ellittiche.
    
4. **Funzioni Hash e MAC** – integrità e autenticazione dei messaggi.
    
5. **Firme digitali** – autenticazione forte e non ripudio.
    
6. **Gestione delle chiavi e applicazioni** – Diffie-Hellman, PKI, certificati, segreti condivisi.
    

---

### 6. Il ruolo della crittografia nella cybersecurity

La crittografia è la **spina dorsale della sicurezza informatica**.  
Ogni servizio digitale che richiede fiducia — dall’e-banking alla messaggistica — si fonda su protocolli crittografici che proteggono **identità, dati e comunicazioni**.  
Capire la crittografia significa comprendere **i meccanismi logici** che rendono sicuri i sistemi digitali.

---
