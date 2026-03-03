## **Lezione 3: Standard X.509**

### **1. Introduzione**

Lo **standard X.509** è il formato più utilizzato al mondo per i **certificati digitali**.  
Fa parte della famiglia di raccomandazioni **X.500**, che definisce i cosiddetti **directory service**, ovvero sistemi distribuiti per conservare e gestire informazioni sugli utenti, comprese le loro chiavi pubbliche.  
Definito nel **1988** dall’**ITU-T** (International Telecommunication Union – Telecommunication Standardization Sector) e aggiornato nel 1993 e 1995, è oggi adottato in numerosi protocolli di sicurezza, tra cui **S/MIME, SSL/TLS, SET e IPsec**.

---

### **2. Struttura di un certificato X.509**

Ogni certificato X.509 contiene un insieme di campi organizzati in una struttura gerarchica.  
Le **versioni 1, 2 e 3** differiscono per i campi aggiuntivi e le estensioni supportate.

#### **Campi principali (tutte le versioni)**

1. **Version**  
    Indica la versione dello standard:
    
    - `v1` se contiene solo i campi base;
        
    - `v2` se include identificatori univoci per issuer o subject;
        
    - `v3` se comprende estensioni aggiuntive.
        
2. **Serial Number**  
    Numero univoco (intero) assegnato dalla CA per identificare senza ambiguità il certificato.
    
3. **Signature Algorithm ID**  
    Specifica l’algoritmo usato per firmare il certificato (es. RSA, ECDSA) e i relativi parametri.
    
4. **Issuer Name**  
    È il **nome X.500** della CA che ha emesso e firmato il certificato, espresso come sequenza di coppie nome-valore:
    
    ```
    C=US, O=Netscape Communications Corp., CN=John Doe, E=doe@netscape.com
    ```
    
5. **Validity Period**  
    Coppia di date che indicano l’inizio (**Not Before**) e la fine (**Not After**) del periodo di validità del certificato.
    
6. **Subject Name**  
    Nome X.500 del soggetto titolare del certificato, cioè l’entità che possiede la **chiave privata** corrispondente alla chiave pubblica contenuta nel certificato.
    
7. **Subject Public Key Info**  
    Include la **chiave pubblica del soggetto** e l’identificativo dell’algoritmo con eventuali parametri.
    
8. **Issuer Unique Identifier (v2 e v3)**  
    Campo opzionale: stringa di bit utile per distinguere la CA in caso di riutilizzo dello stesso nome X.500.
    
9. **Subject Unique Identifier (v2 e v3)**  
    Campo opzionale: serve a distinguere un soggetto con nome X.500 duplicato.
    
10. **Extensions (solo v3)**  
    Contiene **campi di estensione** aggiuntivi, come l’uso consentito della chiave (es. “Digital Signature”, “Key Encipherment”) o i percorsi di certificazione (Certificate Policies, CRL Distribution Points, ecc.).
    
11. **Signature**  
    Firma digitale dell’hash di tutti i campi precedenti, realizzata con l’algoritmo indicato nel campo _Signature Algorithm ID_.
    

---

### **3. Esempio di certificato X.509 (testuale)**

```text
Certificate:
    Data:
        Version: v3 (0x2)
        Serial Number: 3 (0x3)
        Signature Algorithm: PKCS#1 MD5 With RSA Encryption
        Issuer: OU=Ace Certificate Authority, O=Ace Industry, C=US
        Validity:
            Not Before: Fri Oct 17 18:36:25 1997
            Not After : Sun Oct 17 18:36:25 1999
        Subject: CN=Jane Doe, OU=Finance, O=Ace Industry, C=US
        Subject Public Key Info:
            Algorithm: PKCS#1 RSA Encryption
            Public Key: (modulus, exponent)
```

---

### **4. Esempio in formato codificato (Base64)**

I certificati X.509 vengono comunemente distribuiti in formato **PEM (Privacy Enhanced Mail)**, codificato in **Base64** e racchiuso tra due intestazioni testuali:

```text
-----BEGIN CERTIFICATE-----
MIICKzCCAZSgAwIBAgIBAzANBgkqhkiG9w0BAQQFADA3MQswCQYDVQQGEwJVUzER
MA8GA1UEChMITmV0c2NhcGUxFTATBgNVBAsTDFN1cHJpeWEncyBDQTAeFw05NzEw
...
UkdGYpcd2cYRCgKi4MwqdWyLtpuHAH18hHZ5uvi00mJYw8W2wUOsY0RC/a/IDy84
hW3WWehBUqVK5SY4/zJ4oTjx7dwNMdGwbWfpRqjd1A==
-----END CERTIFICATE-----
```

Questo è il formato standard utilizzato da browser, server web e software di posta per scambiare e memorizzare certificati.

---

### **5. Sintesi finale**

- Lo **standard X.509** definisce il **formato universale dei certificati digitali**.
    
- Ogni certificato contiene campi obbligatori (identità, chiave pubblica, validità) e opzionali (identificatori ed estensioni).
    
- Le versioni più recenti (**v3**) consentono maggiore flessibilità grazie alle **estensioni**, oggi indispensabili nei sistemi PKI.
    
- I certificati X.509 sono alla base di tutte le comunicazioni sicure in rete: browser, e-mail, VPN e infrastrutture aziendali di autenticazione.