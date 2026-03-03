
## **Lezione 1: Distribuzione di chiavi pubbliche**

### **1. Introduzione**

Uno dei problemi fondamentali nei sistemi a **chiave pubblica** è la **distribuzione sicura delle chiavi**.  
In altre parole, quando riceviamo una chiave pubblica, come possiamo essere certi che **appartenga davvero alla persona dichiarata** e non a un attaccante che tenta di impersonarla?

Questa lezione esplora le principali tecniche utilizzate per affrontare questo problema, dai metodi più semplici (scambio diretto) a quelli più strutturati (directory o autorità di chiavi pubbliche).

---

### **2. Tecniche di distribuzione**

Esistono varie modalità per distribuire chiavi pubbliche:

- **Invio point-to-point su canale fidato**
    
- **Annuncio pubblico**
    
- **Directory pubblica di chiavi**
    
- **Autorità per le chiavi pubbliche (Public Key Authority)**
    
- **Certificati di chiavi pubbliche**
    

Ciascun metodo offre diversi livelli di sicurezza e praticità.

---

### **3. Invio point-to-point su canale fidato**

Questo metodo consiste nel **trasferire direttamente** la chiave pubblica tra i due soggetti tramite un **canale sicuro**.  
Esempi:

- consegna fisica tramite corriere fidato;
    
- scambio diretto tra le parti;
    
- invio su canale pubblico, ma con **autenticazione** (es. un hash verificato su canale protetto).
    

È una soluzione valida per:

- **scambi occasionali**;
    
- **sistemi di piccole dimensioni**;  
    ma **non scala bene** in reti ampie o distribuite.
    

---

### **4. Annuncio pubblico**

In questo caso, la chiave pubblica viene resa nota tramite **broadcast o messaggi pubblici**.  
Esempio pratico:  
aggiungere la propria chiave PGP ai messaggi inviati in un forum o newsgroup pubblico.

**Problema principale:**  
come possiamo sapere che l’annuncio proviene davvero dall’utente legittimo?  
Questo metodo **non fornisce alcuna garanzia di autenticità**.

---

### **5. Directory pubblica di chiavi**

Una **directory pubblica** è gestita da un’entità **fidata**, che conserva e distribuisce le chiavi pubbliche degli utenti.

Ogni utente:

- registra la propria chiave pubblica, di persona o in modo autenticato;
    
- può aggiornare la chiave in caso di compromissione o scadenza;
    
- può consultare la directory per ottenere la chiave di un altro utente.
    

**Limite:** anche l’accesso alla directory deve avvenire tramite un **canale autenticato** per evitare manipolazioni.

---

### **6. Autorità per le chiavi pubbliche (Public Key Authority)**

L’**autorità per le chiavi pubbliche** (PKA) è un server che:

- mantiene la directory aggiornata delle chiavi pubbliche;
    
- possiede una chiave pubblica **nota a tutti gli utenti**;
    
- invia le chiavi pubbliche richieste **in forma autenticata**.
    

**Svantaggi:**

- deve restare **online** costantemente;
    
- può diventare un **collo di bottiglia** o punto singolo di errore.
    

---

### **7. Protocollo di scambio chiavi pubbliche**

Per richiedere una chiave pubblica in modo sicuro, l’autorità e gli utenti scambiano messaggi autenticati con timestamp e nonce:

1. Alice richiede la chiave pubblica di Bob all’autorità.
    
2. L’autorità risponde inviando $E_{PK_B}(\text{ID}_A, \text{nonce}_1)$ firmato.
    
3. Alice verifica e conferma, completando lo scambio.
    
4. La chiave pubblica di Bob viene poi **memorizzata (caching)** per uso futuro, ma deve essere **periodicamente aggiornata**.
    

---

### **8. Caching e aggiornamento**

Gli utenti possono mantenere una **cache locale** delle chiavi pubbliche più utilizzate:

|Utente|Chiave pubblica|Firma di autenticazione|
|---|---|---|
|B|PKB|PKBB|
|C|PKC|PKCC|

Tuttavia, la cache va **aggiornata periodicamente**, poiché le chiavi possono scadere o essere compromesse.

---

### **9. Sintesi finale**

- La **distribuzione delle chiavi pubbliche** è un problema centrale nella crittografia a chiave asimmetrica.
    
- Le soluzioni possibili vanno dallo **scambio diretto** ai sistemi con **autorità centralizzata**.
    
- L’uso di una **Public Key Authority** introduce autenticazione, ma anche limiti di scalabilità.
    
- La necessità di garantire autenticità, validità e aggiornamento delle chiavi porterà, nelle lezioni successive, all’introduzione delle **PKI (Public Key Infrastructures)** e dei **certificati digitali** come soluzione definitiva.