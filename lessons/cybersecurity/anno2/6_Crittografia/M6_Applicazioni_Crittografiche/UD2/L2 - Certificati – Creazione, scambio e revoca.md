## **Lezione 2: Certificati – Creazione, scambio e revoca**

### **1. Introduzione**

Questa lezione approfondisce il concetto di **certificato digitale**, strumento centrale nelle **Public Key Infrastructures (PKI)**.  
Un certificato permette di **associare in modo sicuro** un’identità (persona, ente o server) a una **chiave pubblica**, grazie alla **firma digitale di un’autorità di certificazione (CA)** riconosciuta e fidata.  
Nel mondo digitale, il certificato svolge lo stesso ruolo che nel mondo fisico ha la **carta d’identità**.

---

### **2. Dal mondo fisico al digitale**

|Mondo fisico|Mondo digitale|
|---|---|
|**Carta d’identità**: un’autorità riconosciuta (Comune, Stato) lega un nome a una foto|**Certificato digitale**: un’autorità riconosciuta (CA) lega un nome a una **chiave pubblica**|

In entrambi i casi, l’obiettivo è lo stesso: **garantire l’autenticità dell’identità dichiarata**.

---

### **3. Autorità di Certificazione (CA)**

L’**Autorità di Certificazione** è una **terza parte fidata** che:

- verifica l’identità dell’utente;
    
- genera e **firma digitalmente il certificato**, garantendo il legame tra nome e chiave pubblica.
    

**Proprietà principali dei certificati:**

- Sono **pubblici**: chiunque può leggerli e verificare la firma.
    
- Sono **autenticabili**: la firma della CA assicura che non siano stati alterati.
    
- Sono **creati e aggiornati solo dalla CA**, che ne mantiene anche la validità nel tempo.
    

---

### **4. Contenuto di un certificato digitale**

Un certificato contiene vari campi informativi, tra cui:

- **Nome del soggetto** (es. “Alice Rossi”)
    
- **Chiave pubblica associata**
    
- **Periodo di validità** (data di emissione e scadenza)
    
- **Numero seriale o identificativo univoco**
    
- **Algoritmo di firma** e **scopo d’uso** (es. cifratura, autenticazione)
    
- **Informazioni aggiuntive sull’utente o sull’ente**
    
- **Stato della chiave pubblica**
    

Il formato più diffuso per i certificati è definito dallo **standard internazionale ITU-T X.509**.

---

### **5. Creazione e scambio di certificati**

#### **5.1 Creazione**

L’utente (es. Alice) invia alla CA una **richiesta di certificato**, contenente:

- la sua chiave pubblica;
    
- dati identificativi (nome, organizzazione, email, ecc.);
    
- un **timestamp** o identificativo univoco della richiesta.
    

La CA verifica i dati, genera il certificato e lo **firma digitalmente**.

#### **5.2 Scambio**

Durante una comunicazione (es. Alice ↔ Bob):

1. Alice e Bob inviano reciprocamente i **propri certificati**;
    
2. ciascuno verifica la **firma della CA** sull’altro certificato;
    
3. se valida, la chiave pubblica contenuta nel certificato viene considerata **autentica**.
    

---

### **6. Revoca dei certificati**

Quando un certificato non è più affidabile, deve essere **revocato** prima della scadenza naturale.  
Le principali **cause di revoca** sono:

- compromissione della chiave privata;
    
- cambiamento di informazioni personali o aziendali;
    
- perdita del dispositivo o del token di sicurezza;
    
- compromissione dell’algoritmo di cifratura;
    
- cambio delle politiche di sicurezza della CA.
    

L’utente può quindi richiedere formalmente la revoca alla CA, che aggiornerà gli elenchi pubblici di revoca.

---

### **7. Metodi di revoca**

- **Data di scadenza interna**  
    Ogni certificato ha una validità temporale limitata.  
    (Spesso si utilizzano certificati “a breve scadenza” per ridurre i rischi.)
    
- **Notifica manuale**  
    Revoca comunicata tramite canali riservati; metodo adatto solo per reti piccole o chiuse.
    
- **File pubblico di chiavi revocate**  
    La CA mantiene una lista pubblica denominata **Certificate Revocation List (CRL)**, consultabile da tutti.
    
- **Certificato di revoca**  
    Viene inserito nella directory al posto del certificato originale, indicando che quest’ultimo non è più valido.
    

---

### **8. Certificate Revocation List (CRL)**

La **CRL** è un file firmato digitalmente dalla CA che contiene:

- i **numeri seriali** dei certificati revocati ma non ancora scaduti;
    
- la **data di revoca** e, se necessario, il **motivo**;
    
- la **data di aggiornamento** della lista stessa.
    

La verifica della CRL permette ai sistemi di sapere in tempo reale se un certificato è ancora valido.

---

### **9. Sintesi finale**

- I **certificati digitali** collegano in modo sicuro un’identità a una chiave pubblica.
    
- Sono **firmati** da una **CA**, la cui chiave pubblica è nota e distribuita in modo sicuro.
    
- Possono essere **scambiati e verificati** in modo automatico durante le comunicazioni.
    
- Possono essere **revocati** tramite **CRL** o sostituiti in caso di compromissione.
    

I certificati rappresentano quindi il **cuore della fiducia** nelle reti digitali: permettono di autenticare utenti, server e servizi in tutto il mondo.