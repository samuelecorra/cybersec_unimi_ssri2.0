## **Lezione 6: Attacchi sui Certificati SSL/TLS**

### **1. Introduzione**

Il modello di fiducia di SSL/TLS si basa interamente sull’infrastruttura delle **Certificate Authority (CA)**.  
Se una CA viene compromessa o commette errori nella generazione delle firme digitali, **l’intera catena di fiducia di Internet è a rischio**.

Questa lezione analizza i principali attacchi storici contro le CA e le vulnerabilità dei certificati, mostrando come un errore o una debolezza nell’hash, nella firma o nel codice sorgente possa portare a **falsi certificati attendibili**.

---

### **2. Lo scenario del 2008: vulnerabilità delle CA**

Nel 2008, molte **autorità di certificazione (CA)** usavano ancora l’algoritmo di hash **MD5** per firmare i certificati digitali.  
Tra le CA coinvolte: **RapidSSL, FreeSSL, TrustCenter, RSA Data Security, Thawte, Verisign.co.jp**.

Un gruppo di ricercatori (Sotirov et al.) analizzò circa **30.000 certificati pubblici**, scoprendo che:

- **9.000** erano ancora firmati con **MD5**,
    
- e il **97%** proveniva da RapidSSL.
    

Il loro obiettivo era dimostrare che, sfruttando le **collisioni di MD5**, si poteva creare una **CA falsa ma riconosciuta come legittima** dai browser.

---

### **3. Come funziona il sistema di certificazione**

#### **a. Il ruolo della CA**

- Ogni CA distribuisce il proprio **certificato radice** ai browser (tramite i produttori di software).
    
- Questo certificato è memorizzato nel cosiddetto **trust store** del sistema operativo o del browser.
    
- Tutti i certificati firmati da quella CA vengono **automaticamente considerati validi**.
    

#### **b. Procedura tipica**

1. Un’azienda acquista un certificato da una CA per il proprio sito web.
    
2. La CA verifica l’identità del richiedente e **firma** il certificato.
    
3. Quando un utente si connette al sito:
    
    - Il **browser riceve il certificato**,
        
    - Ne verifica la firma confrontandola con la **CA nel trust store**,
        
    - Se la verifica ha successo, il sito viene marcato come **“sicuro”** e la comunicazione avviene in modo cifrato.
        

Questo modello funziona solo finché **le CA rimangono affidabili e le loro chiavi non vengono compromesse**.

---

### **4. Collisioni su MD5**

#### **a. Scoperta della collisione**

Nel 2004 **Xiaoyun Wang** e **Hongbo Yu** dimostrarono la possibilità di costruire due blocchi di dati distinti $(C, C')$ tali che:

$$  
MD5(P | C | S) = MD5(P | C' | S)  
$$

per qualsiasi prefisso $P$ e suffisso $S$.

#### **b. Evoluzioni successive**

- **2005:** Lenstra e Weger mostrarono come integrare queste collisioni all’interno di **certificati X.509**, ottenendo due certificati diversi con lo stesso hash MD5.
    
- **2007:** Marc Stevens introdusse le **collisioni con prefisso scelto**, permettendo di:
    
    - scegliere arbitrariamente i prefissi (contenenti le identità e le chiavi pubbliche),
        
    - generare due certificati diversi ma con **hash MD5 identico**,
        
    - e ottenere **firme identiche** da parte della CA.
        

> In pratica: la CA firmava inconsapevolmente due certificati diversi, credendo di firmarne uno solo.

---

### **5. Creazione di una falsa CA (2008)**

I ricercatori riuscirono a trasformare questa teoria in pratica con un attacco storico.

#### **a. Strategia**

1. Identificare una CA che **usasse ancora MD5** (RapidSSL).
    
2. Ottenere un **certificato legittimo** il cui contenuto “da firmare” fosse **prevedibile**.
    
3. Inserire all’interno di questo certificato un **blocco di collisione controllato**.
    
4. Calcolare un **secondo certificato** con lo stesso hash MD5 ma con:
    
    - identità diversa,
        
    - chiave pubblica diversa,
        
    - e privilegi di **CA intermedia**.
        

Quando RapidSSL firmò il primo certificato, la stessa firma fu **valida anche per il secondo**, che diventava così una **CA falsa ma perfettamente attendibile**.

#### **b. Implicazione**

> Una CA vera forniva, senza saperlo, una **firma valida** per una CA completamente falsa.

---

### **6. Generazione della collisione**

La collisione fu ottenuta in **1-2 giorni** su un cluster di **200 PlayStation 3**, equivalenti a circa **8000 core** di CPU desktop.

- Costo stimato su Amazon EC2: **~20.000 $**
    
- Tempo medio per collisione: **18 ore**
    
- Complessità: circa $2^{51}$ chiamate alla funzione di compressione MD5.
    

Il risultato fu un **certificato “skeleton key”**, cioè una chiave universale capace di **firmare certificati per qualunque sito web**.

---

### **7. Il “Perfect Man-in-the-Middle”**

Una volta creata la falsa CA, bastava un semplice attacco di rete per intercettare le connessioni HTTPS.

#### **Esempio di vettori di attacco**

- Wi-Fi non sicuri,
    
- DNS poisoning,
    
- Proxy automatici compromessi,
    
- Router infetti.
    

Con questo certificato “universale”, un aggressore poteva impersonare **qualsiasi sito del mondo**, e il browser lo avrebbe riconosciuto come **autentico e sicuro**.

---

### **8. Storia successiva: virus e certificati falsi (2010-2012)**

Nel periodo 2010-2012, un **virus di cyber-spionaggio** (probabilmente di tipo _state-sponsored_) utilizzò un **certificato CA intermedio falso**, apparentemente emesso da **Microsoft**.

- Il certificato veniva accettato automaticamente da **Windows Update**.
    
- Era stato creato tramite una **collisione MD5 con prefisso scelto**, sfruttando un vecchio certificato del sistema di licenze di _Microsoft Terminal Server_ che usava ancora MD5.
    
- Questo attacco dimostra che la tecnica era già nota e usata **prima** della pubblicazione ufficiale di Sotirov et al.
    

---

### **9. Errori di implementazione: “Failing to Check Hostname”**

Nel 2012, ricercatori delle Università del Texas e di Stanford scoprirono che molte **API SSL** non verificavano correttamente che il nome dell’host nel certificato corrispondesse al dominio del sito.

#### **Conseguenza**

Applicazioni come client di posta, app Android o software gestionali accettavano **certificati validi ma per domini diversi**, rendendosi vulnerabili a **MITM attacchi**.

Esempio:

```
Server certificato: Issued by GoDaddy to AllYourSSLAreBelongTo.us
App: "Hello, I am Chase.com"
Risposta: Ok!
```

Il certificato è formalmente valido, ma **l’identità del sito è sbagliata**.

---

### **10. Bug “Goto Fail” (Apple, 2014)**

#### **a. Il problema**

Nel 2014, un bug nel codice sorgente di Apple **disabilitò di fatto la verifica delle firme TLS** in tutte le versioni di iOS e OS X.

Il codice (nel file `sslKeyExchange.c` di SecureTransport) conteneva un doppio `goto fail;`:

```c
if ((err = SSLHashSHA1.update(&hashCtx, &clientRandom)) != 0) goto fail;
if ((err = SSLHashSHA1.update(&hashCtx, &serverRandom)) != 0) goto fail;
if ((err = SSLHashSHA1.update(&hashCtx, &signedParams)) != 0) goto fail;
goto fail;   // <- errore critico
if ((err = SSLHashSHA1.final(&hashCtx, &hashOut)) != 0) goto fail;
```

Poiché le chiamate `update()` non generavano errori, il secondo `goto fail` veniva sempre eseguito.  
Il risultato: **la verifica della firma saltava completamente**, e la connessione veniva accettata come sicura.

#### **b. Impatto**

- Tutti i dispositivi **iOS e OS X** divennero vulnerabili a **MITM attack** per mesi.
    
- Bastava indurre l’utente a visitare un sito HTTPS malevolo (es. tramite Wi-Fi pubblico).
    
- Safari utilizzava proprio la versione affetta della libreria **SecureTransport**.
    

---

### **11. Revoca dei certificati**

La **revoca** è fondamentale per mantenere sicuro l’ecosistema TLS.

#### **a. Motivi per la revoca**

- Compromissione della chiave privata,
    
- Mancato pagamento o scadenza,
    
- Compromissione della CA stessa.
    

#### **b. Meccanismi**

1. **CRL (Certificate Revocation List):**
    
    - Lista firmata periodicamente rilasciata dalla CA con tutti i certificati revocati.
        
    - Può includere anche un _delta CRL_ (solo aggiornamenti).
        
2. **OCSP (Online Certificate Status Protocol):**
    
    - Il browser contatta un server online per verificare in tempo reale la validità del certificato.
        

Molti sistemi, tuttavia, **ignorano la revoca** per motivi di performance, e le CA **guadagnano** dalla riemissione dei certificati revocati.

---

### **12. Compromissioni di CA reali**

#### **a. Caso Comodo (2011)**

- Comodo, una CA radice affidabile, emetteva certificati tramite **rivenditori**.
    
- Un hacker iraniano violò i sistemi di **instantSSL.it** e **GlobalTrust.it**, rubando credenziali e accedendo all’API di Comodo.
    
- Il 15 marzo 2011, furono emessi **9 certificati malevoli** per domini come:
    
    - `mail.google.com`
        
    - `login.yahoo.com`
        
    - `login.live.com`
        
    - `addons.mozilla.org`
        
    - `login.skype.com`
        

Tutti **formalmente validi** e accettati dai browser.

---

#### **b. Caso TrustWave (2012)**

- TrustWave ammise di aver fornito a un’azienda cliente un **certificato CA intermedio**, con lo scopo dichiarato di “prevenzione della perdita di dati”.
    
- In realtà, ciò consentiva al cliente di **intercettare e riscrivere** qualsiasi connessione HTTPS dei propri dipendenti.
    
- Se tale certificato fosse trapelato, avrebbe permesso di **falsificare qualsiasi certificato nel mondo**.
    

---

#### **c. Caso TurkTrust (2013)**

- La CA turca **TurkTrust** rilasciò per errore **certificati intermedi CA** a clienti che avevano richiesto normali certificati di dominio.
    
- Uno di questi clienti (l’autorità di Ankara) usò il certificato per emettere un **falso certificato *.google.com**, usato per intercettare traffico HTTPS locale.
    
- Poiché era firmato da una CA radice fidata, il certificato risultava **valido in tutti i browser del mondo**.
    

---

### **13. Conclusione**

Gli attacchi ai certificati SSL/TLS mostrano che la **sicurezza del web non è solo matematica**, ma anche **organizzativa e procedurale**.  
Gli errori di hash, di implementazione o di gestione della fiducia possono **annullare completamente** i benefici della crittografia.

Per garantire la sicurezza del canale TLS occorre:

- usare **algoritmi di hash robusti** (SHA-256 o superiori),
    
- evitare **CA non affidabili o automatizzate**,
    
- **verificare hostname e catena di fiducia**,
    
- e gestire attivamente la **revoca** dei certificati.
    

> La crittografia protegge solo ciò che è matematicamente solido.  
> La fiducia, invece, va difesa ogni giorno con vigilanza e trasparenza.