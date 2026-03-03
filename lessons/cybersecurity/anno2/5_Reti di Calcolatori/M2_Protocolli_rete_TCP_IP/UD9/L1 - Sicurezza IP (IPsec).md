
---

# **Lezione 1: Sicurezza IP (IPsec)**

### **1. Introduzione**

La suite **TCP/IP** è nata in un contesto di fiducia, senza prevedere controlli di sicurezza integrati.  
Per colmare queste lacune è stato sviluppato **IPsec (Internet Protocol Security)**:  
un insieme di protocolli che aggiunge **autenticazione, integrità e cifratura** alle comunicazioni IP.

> IPsec non è un singolo protocollo, ma un **framework modulare**, capace di adattarsi a diversi algoritmi e scenari di sicurezza.

![](imgs/Pasted%20image%2020260225155348.png)

---

### **2. Funzioni e applicazioni di IPsec**

IPsec fornisce una base comune per instaurare **comunicazioni sicure tra due entità IP**, qualunque sia la loro posizione nella rete.  
Le sue principali applicazioni sono:

- **Connessione sicura tra sottoreti** via Internet (VPN site-to-site);
    
- **Accesso remoto protetto** di singoli utenti verso una rete aziendale (VPN client-to-site).
    
	
IPsec permette di scegliere **gli algoritmi di cifratura e autenticazione più adatti** al livello di protezione richiesto, offrendo un approccio flessibile e interoperabile.

![](imgs/Pasted%20image%2020260225155421.png)

---

### **3. Vantaggi principali di IPsec**

1. **Trasparenza applicativa:**  
    IPsec agisce **sotto al livello trasporto**, tra IP e TCP/UDP → nessuna modifica necessaria ai programmi utente.
    
2. **Servizi di sicurezza personalizzabili:**  
    Ogni utente o sottorete può disporre di **politiche di protezione dedicate**.
    
3. **Validazione dei router e delle rotte:**  
    IPsec può garantire che:
    
    - un **advertisement di un router** provenga da un dispositivo autorizzato;
        
    - un **messaggio di reindirizzamento** sia effettivamente inviato dal router corretto;
        
    - un **aggiornamento di routing** non sia stato falsificato.
        

> In altre parole, IPsec protegge la **fiducia nel livello IP**, fondamento dell’intero stack TCP/IP.

---

### **4. IKE – Internet Key Exchange**

Per stabilire una connessione IPsec sicura serve un meccanismo di scambio delle chiavi:  
questo ruolo è affidato al **protocollo IKE (Internet Key Exchange)**.

IKE:

- negozia una **Security Association (SA)** tra due entità,
    
- autentica i partecipanti,
    
- concorda **metodi e algoritmi di cifratura**,
    
- e scambia in modo sicuro la **chiave segreta di sessione**.
    

Il protocollo deriva dal framework **ISAKMP** e nella versione più recente (RFC 4306, dicembre 2005) utilizza **cifratura a chiave pubblica** per proteggere lo scambio iniziale.

> In sintesi, IKE costruisce la base di fiducia su cui IPsec opera.

---

### **5. Servizi di sicurezza forniti da IPsec**

IPsec implementa una gamma completa di servizi:

- **Controllo degli accessi**
    
- **Integrità dei dati**
    
- **Autenticazione dell’origine**
    
- **Confidenzialità (cifratura)**
    
- **Protezione dagli attacchi di replay** (rifiuto dei pacchetti duplicati)
    

> Questi servizi possono essere combinati o adattati a seconda del tipo di comunicazione.

---

### **6. Security Association (SA)**

Una **Security Association** è una **connessione logica unidirezionale** che specifica come due entità devono comunicare in modo sicuro.  
Ogni SA è identificata da tre parametri:

1. **SPI (Security Parameter Index):** un identificatore univoco della sessione;
    
2. **Indirizzo IP di destinazione;**
    
3. **Protocollo di sicurezza utilizzato (AH o ESP).**
    

> In pratica, una SA definisce le “regole di cifratura e autenticazione” valide per uno specifico flusso di traffico IP.

---

### **7. AH – Authentication Header**

L’**Authentication Header (AH)** fornisce:

- **integrità dei dati**,
    
- **autenticazione dell’origine**,
    
- e **protezione dagli attacchi di replay**.

![](imgs/Pasted%20image%2020260225155629.png)

Il campo AH contiene un **codice MAC (Message Authentication Code)** calcolato sui dati IP.  
In questo modo il destinatario può verificare che:

- i dati **non siano stati modificati**, e
    
- il pacchetto **provenga realmente dal mittente dichiarato**.
    

> AH protegge l’intestazione IP e i dati, ma **non offre cifratura del contenuto**.

![](imgs/Pasted%20image%2020260225155655.png)

---

### **8. ESP – Encapsulating Security Payload**

Il protocollo **ESP** aggiunge ai servizi di AH anche la **confidenzialità**, grazie alla cifratura del payload IP.

![](imgs/Pasted%20image%2020260225155715.png)

Funzioni principali:

- **Cifratura dei dati**, per mantenerli riservati;
    
- **Autenticazione opzionale**, per verificare l’integrità;
    
- **Protezione contro replay attacks**, come in AH.
    

> ESP è il componente più usato nelle VPN, poiché combina cifratura e autenticazione in un unico meccanismo efficiente.

---

### **9. Algoritmi di cifratura e autenticazione**

IPsec supporta diversi algoritmi, scelti in base a prestazioni e livello di sicurezza desiderato.

#### **Algoritmi di cifratura**

- **3DES (Triple DES)**
    
- **RC5**
    
- **IDEA**
    
- **Triple IDEA**
    
- **CAST**
    
- **Blowfish**
    

#### **Algoritmi di autenticazione**

- **HMAC-MD5-96**
    
- **HMAC-SHA-1-96**
    

> Tutti i protocolli e algoritmi sono **negoziabili tramite IKE**, garantendo compatibilità e aggiornabilità nel tempo.

---

### **10. Combinazioni di associazioni di sicurezza**

Le **SA** possono essere combinate per ottenere diversi livelli di protezione:

- Solo **AH** → autenticazione e integrità, senza cifratura.
    
- Solo **ESP** → cifratura (con o senza autenticazione).
    
- **AH + ESP** → protezione completa end-to-end.

![](imgs/Pasted%20image%2020260225155743.png)

> La scelta dipende dalle esigenze:  
> per esempio, una VPN aziendale privilegia ESP, mentre un sistema militare può richiedere AH + ESP.

---

### **11. Conclusione**

IPsec è il meccanismo standard che fornisce **sicurezza nativa a livello IP**, indipendente dalle applicazioni.  
Grazie a IKE, AH ed ESP, consente di costruire **canali cifrati e autenticati** tra nodi o intere reti, rendendo Internet finalmente adatta anche a **comunicazioni sensibili e mission-critical**.

> In sintesi: IPsec trasforma un protocollo “aperto” come IP in una **rete sicura, autenticata e riservata**.
