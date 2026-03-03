## **Lezione 5: Autenticazione in X.509**

### **1. Introduzione**

Lo **standard X.509** non si limita a definire il formato dei certificati digitali, ma specifica anche **procedure di autenticazione** che sfruttano tali certificati.  
Queste procedure permettono di **verificare l’identità delle parti** coinvolte in una comunicazione, garantendo **integrità, autenticità e non ripudio** dei messaggi.  
Sono previste tre modalità principali di autenticazione:

- **Autenticazione One-Way**
    
- **Autenticazione Two-Way**
    
- **Autenticazione Three-Way**
    

---

### **2. Autenticazione One-Way**

Questa modalità prevede che **solo un partecipante (Alice)** si autentichi nei confronti dell’altro (Bob).  
Si assume che entrambi conoscano già le rispettive **chiavi pubbliche**, ottenute tramite scambio diretto o directory PKI.

#### **Schema del messaggio**

Alice invia a Bob:

$$  
\text{Firma}_A\{ \ t_A, r_A, B, \text{sgnData}, E_{PK_B}(K_{AB}) \ \}  
$$

dove:

- **$t_A$**: timestamp (indicazione temporale, spesso con _expiration time_);
    
- **$r_A$**: _nonce_, numero casuale unico per evitare replay;
    
- **$B$**: identificativo del destinatario;
    
- **sgnData**: informazioni aggiuntive o metadati utili a Bob;
    
- **$E_{PK_B}(K_{AB})$**: chiave di sessione cifrata con la chiave pubblica di Bob.
    

Bob, decifrando con la propria chiave privata e verificando la firma, ottiene la **garanzia che il messaggio proviene da Alice** e che **non sia stato alterato o ripetuto**.

#### **Sicurezza**

Per evitare **attacchi di replay**, il _nonce_ deve essere univoco e conservato fino alla scadenza del timestamp associato.

---

### **3. Autenticazione Two-Way**

In questa modalità **entrambe le parti** si autenticano reciprocamente.  
Dopo il messaggio di Alice, Bob risponde con un messaggio firmato analogo.

#### **Sequenza dei messaggi**

1. Alice → Bob  
    $$  
    \text{Firma}_A\{ \ t_A, r_A, B, \text{sgnData}, E_{PK_B}(K_{AB})\ \}  
    $$
    
2. Bob → Alice  
    $$  
    \text{Firma}_B\{ \ t_B, r_B, A, r_A, \text{sgnData}, E_{PK_A}(K_{BA})\ \}  
    $$
    

dove:

- **$t_B$** è il timestamp generato da Bob;
    
- **$r_B$** è un nuovo nonce;
    
- **$r_A$** viene incluso per collegare la risposta alla richiesta di Alice.
    

Questa procedura garantisce **autenticazione reciproca** e **integrità bidirezionale** del canale.

---

### **4. Autenticazione Three-Way**

L’autenticazione a tre vie (Three-Way Handshake) è una **variante del Two-Way** progettata per eliminare la dipendenza dai **timestamp**.  
È utile nei sistemi dove gli orologi dei dispositivi **non sono sincronizzati**.

#### **Sequenza dei messaggi**

1. Alice → Bob  
    $$  
    \text{Firma}_A\{ \ t_A, r_A, B, \text{sgnData}, E_{PK_B}(K_{AB})\ \}  
    $$
    
2. Bob → Alice  
    $$  
    \text{Firma}_B\{ \ t_B, r_B, A, r_A, \text{sgnData}, E_{PK_A}(K_{BA})\ \}  
    $$
    
3. Alice → Bob  
    $$  
    \text{Firma}_A{r_B}  
    $$
    

In questo terzo messaggio, Alice restituisce a Bob il suo _nonce_ ($r_B$), dimostrando di aver ricevuto correttamente il messaggio precedente e **concludendo l’autenticazione reciproca** senza necessità di riferimenti temporali.

---

### **5. Sintesi finale**

- X.509 definisce **tre meccanismi di autenticazione**:  
    **One-Way**, **Two-Way** e **Three-Way**.
    
- L’autenticazione **One-Way** serve per validare un solo partecipante.
    
- L’autenticazione **Two-Way** introduce la **reciprocità** e il controllo sui _nonce_.
    
- L’autenticazione **Three-Way** elimina la dipendenza dai **timestamp**, risultando più robusta in sistemi **asincroni**.
    
- In tutti i casi, la **firma digitale** e i **certificati X.509** garantiscono autenticità, integrità e non ripudio dei messaggi scambiati.