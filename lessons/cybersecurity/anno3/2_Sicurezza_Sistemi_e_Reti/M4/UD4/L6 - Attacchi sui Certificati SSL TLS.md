## **Lezione 6: Attacchi sui Certificati SSL/TLS**

### **1. Introduzione**

Il modello di fiducia di SSL/TLS si basa interamente sull’infrastruttura delle **Certificate Authority (CA)**.  
Se una CA viene compromessa o commette errori nella generazione delle firme digitali, **l’intera catena di fiducia di Internet è a rischio**.

Questa lezione analizza i principali attacchi storici contro le CA e le vulnerabilità dei certificati, mostrando come un errore o una debolezza nell’hash, nella firma, nella validazione o nel codice sorgente possa portare a **falsi certificati attendibili**.

Il punto centrale è che TLS autentica il server attraverso il certificato: se un certificato falso viene comunque accettato dal browser, l’attaccante può costruire un sito malevolo che appare indistinguibile dal sito legittimo.

> 📌 In TLS la cifratura può essere perfetta, ma se l’identità autenticata è sbagliata il canale sicuro protegge la comunicazione con l’attaccante.

---

### **2. Lo scenario del 2008: vulnerabilità delle CA**

Nel 2008, molte **autorità di certificazione (CA)** usavano ancora l’algoritmo di hash **MD5** per firmare i certificati digitali.  
Tra le CA coinvolte: **RapidSSL, FreeSSL, TrustCenter, RSA Data Security, Thawte, Verisign.co.jp**.

Un gruppo di ricercatori (Sotirov et al.) analizzò circa **30.000 certificati pubblici**, scoprendo che:

- **9.000** erano ancora firmati con **MD5**,
    
- e il **97%** proveniva da RapidSSL.
    

Il loro obiettivo era dimostrare che, sfruttando le **collisioni di MD5**, si poteva creare una **CA falsa ma riconosciuta come legittima** dai browser.

Il problema non riguarda solo MD5: il principio generale è che una funzione hash usata per firmare certificati deve resistere alle collisioni. Se è possibile produrre due certificati con lo stesso digest, una firma valida sul primo può diventare valida anche sul secondo.

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

![](imgs/Pasted%20image%2020260709031218.png)

#### **c. Catena di certificazione**

Il browser non “conosce” direttamente tutti i siti sicuri del mondo. Conosce invece un insieme di certificati radice fidati, installati nel sistema operativo o nel browser dai vendor.

Quando un server HTTPS presenta il proprio certificato, il browser verifica una **certification path**:

1. il certificato del sito contiene la chiave pubblica del sito;

2. tale certificato è firmato da una CA intermedia o radice;

3. la firma viene verificata risalendo la catena;

4. la catena deve terminare in un certificato radice già presente nel trust store;

5. se le firme, le scadenze, gli usi consentiti e il nome del dominio sono corretti, il certificato viene accettato.

Tutto avviene in modo trasparente per l’utente: durante una normale navigazione web l’utente non ispeziona manualmente certificati e catene di fiducia.

#### **d. Scenario di attacco con certificato falso**

Supponiamo che l’attaccante riesca a ottenere un certificato formalmente valido ma contenente dati falsificati, per esempio una chiave pubblica associata a un sito di phishing che si presenta come la banca X.

L’attacco procede così:

1. l’attaccante induce la vittima a raggiungere il sito falso, per esempio tramite DNS poisoning, ARP poisoning, proxy compromesso o phishing;

2. il sito falso presenta un certificato apparentemente valido per la banca X;

3. il browser verifica la catena di certificazione e la considera attendibile;

4. la vittima crede di essere collegata alla banca reale;

5. le credenziali inserite vengono ricevute dall’attaccante;

6. l’attaccante può poi riutilizzarle verso il vero sito della banca.

![](imgs/Pasted%20image%2020260709031244.png)

> ⚠️ Questo è il caso peggiore: il browser non mostra alcun errore, perché dal suo punto di vista la firma del certificato è valida.

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

Nell’attacco ai certificati, la collisione deve essere costruita con molta attenzione: i prefissi dei due certificati possono contenere identità, chiavi pubbliche, campi X.509 e altri parametri scelti dall’attaccante. L’obiettivo è ottenere due strutture semanticamente diverse ma con lo stesso valore MD5 nella parte firmata dalla CA.

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

#### **b. Dettaglio RapidSSL**

RapidSSL era un bersaglio particolarmente adatto perché:

- rilasciava certificati economici e in modo molto automatizzato;

- usava ancora MD5;

- generava certificati in tempi molto rapidi;

- assegnava numeri seriali sequenziali o comunque prevedibili.

La prevedibilità era essenziale. Per costruire la collisione, l’attaccante doveva sapere in anticipo molti campi del certificato che la CA avrebbe firmato, inclusi numero seriale e timestamp. La strategia consisteva quindi nel:

1. acquistare certificati di prova per osservare l’andamento dei serial number;

2. stimare quando la CA avrebbe raggiunto un certo seriale futuro;

3. preparare in anticipo la coppia di certificati collidenti;

4. inviare la richiesta nel momento giusto, in modo che il certificato legittimo firmato dalla CA avesse proprio i campi previsti;

5. trasferire la firma ottenuta sul secondo certificato, quello malevolo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Questo spiega perché l’attacco non richiedeva la compromissione diretta della chiave privata della CA: bastava sfruttare una CA legittima che firmava ancora con MD5 e produceva certificati sufficientemente prevedibili.

#### **c. Implicazione**

> Una CA vera forniva, senza saperlo, una **firma valida** per una CA completamente falsa.

---

### **6. Generazione della collisione**

La collisione fu ottenuta in **1-2 giorni** su un cluster di **200 PlayStation 3**, equivalenti a circa **8000 core** di CPU desktop.

- Costo stimato su Amazon EC2: **~20.000 $**
    
- Tempo medio per collisione: **18 ore**
    
- Complessità: circa $2^{51}$ chiamate alla funzione di compressione MD5.

![](imgs/Pasted%20image%2020260709031403.png)

Il risultato fu un **certificato “skeleton key”**, cioè una chiave universale capace di **firmare certificati per qualunque sito web**.

Il transcript sottolinea anche l’aspetto computazionale: la ricerca di collisioni è altamente parallelizzabile. Già con hardware non specialistico ma distribuito, come il cluster di PlayStation 3 usato nell’esperimento, il tempo necessario diventava praticabile. Con l’evoluzione dell’hardware parallelo, il margine di sicurezza di hash deboli come MD5 è diventato del tutto insufficiente.

---

### **7. Il “Perfect Man-in-the-Middle”**

Una volta creata la falsa CA, bastava un semplice attacco di rete per intercettare le connessioni HTTPS.

#### **Esempio di vettori di attacco**

- Wi-Fi non sicuri,
    
- DNS poisoning,
    
- Proxy automatici compromessi,
    
- Router infetti.
    
![](imgs/Pasted%20image%2020260709031454.png)

Con questo certificato “universale”, un aggressore poteva impersonare **qualsiasi sito del mondo**, e il browser lo avrebbe riconosciuto come **autentico e sicuro**.

In pratica, il certificato falso abilita un **Perfect Man-in-the-Middle**: una volta indirizzato il traffico della vittima verso il server dell’attaccante, il browser accetta il certificato e instaura una connessione TLS apparentemente legittima.

> 📌 La parte di rete dell’attacco può essere “banale” rispetto alla parte crittografica: Wi-Fi ostile, DNS poisoning o routing manipolato bastano se l’attaccante dispone di un certificato accettato dal browser.

---

### **8. Storia successiva: virus e certificati falsi (2010-2012)**

Nel periodo 2010-2012, un **virus di cyber-spionaggio** (probabilmente di tipo _state-sponsored_) utilizzò un **certificato CA intermedio falso**, apparentemente emesso da **Microsoft**.

- Il certificato veniva accettato automaticamente da **Windows Update**.
    
- Era stato creato tramite una **collisione MD5 con prefisso scelto**, sfruttando un vecchio certificato del sistema di licenze di _Microsoft Terminal Server_ che usava ancora MD5.
    
- Questo attacco dimostra che la tecnica era già nota e usata **prima** della pubblicazione ufficiale di Sotirov et al.
    

L’impatto è particolarmente grave: se un falso certificato viene accettato come appartenente all’infrastruttura Microsoft, una vittima può collegarsi a un falso servizio di aggiornamento e scaricare malware credendolo un update legittimo.

> ⚠️ I certificati non proteggono solo siti web: proteggono anche canali di aggiornamento software. Un certificato falso in quel contesto può trasformarsi in distribuzione automatica di malware.

![](imgs/Pasted%20image%2020260709031637.png)

---

### **9. Errori di implementazione: “Failing to Check Hostname”**

Nel 2012, ricercatori delle Università del Texas e di Stanford scoprirono che molte **API SSL** non verificavano correttamente che il nome dell’host nel certificato corrispondesse al dominio del sito.

![](imgs/Pasted%20image%2020260709031809.png)

#### **Conseguenza**

Applicazioni come client di posta, app Android o software gestionali accettavano **certificati validi ma per domini diversi**, rendendosi vulnerabili a **MITM attacchi**.

Esempio:

```
Server certificato: Issued by GoDaddy to AllYourSSLAreBelongTo.us
App: "Hello, I am Chase.com"
Risposta: Ok!
```

Il certificato è formalmente valido, ma **l’identità del sito è sbagliata**.

![](imgs/Pasted%20image%2020260709031734.png)

![](imgs/Pasted%20image%2020260709031900.png)

La verifica corretta deve quindi controllare due aspetti distinti:

- la **catena di certificazione**, cioè che il certificato sia firmato da una CA fidata;

- il **nome del dominio**, cioè che il certificato sia stato emesso proprio per l’host che l’utente sta visitando, tipicamente tramite Subject Alternative Name.

Se la seconda verifica manca, un certificato valido per un dominio qualsiasi può essere riusato per impersonare un altro dominio.

> 📌 “Certificato firmato da una CA fidata” non basta: deve essere firmato per il sito che sto effettivamente visitando.

---

### **10. Bug “Goto Fail” (Apple, 2014)**

#### **a. Il problema**

Nel 2014, un bug nel codice sorgente di Apple **disabilitò di fatto la verifica delle firme TLS** in tutte le versioni di iOS e OS X.

Il codice (nel file `sslKeyExchange.c` di SecureTransport) conteneva un doppio `goto fail;` (complotto???):

```c
if ((err = SSLHashSHA1.update(&hashCtx, &clientRandom)) != 0) goto fail;
if ((err = SSLHashSHA1.update(&hashCtx, &serverRandom)) != 0) goto fail;
if ((err = SSLHashSHA1.update(&hashCtx, &signedParams)) != 0) goto fail;
goto fail;   // <- errore critico
if ((err = SSLHashSHA1.final(&hashCtx, &hashOut)) != 0) goto fail;
```

![](imgs/Pasted%20image%2020260709031925.png)

Poiché le chiamate `update()` non generavano errori, il secondo `goto fail` veniva sempre eseguito.  
Il risultato: **la verifica della firma saltava completamente**, e la connessione veniva accettata come sicura.

Dal punto di vista logico, il codice usciva dalla routine di verifica prima di eseguire il controllo finale. Poiché fino a quel punto non era stato registrato alcun errore, la funzione restituiva successo anche se la firma non era stata verificata fino in fondo.

#### **b. Impatto**

- Tutti i dispositivi **iOS e OS X** divennero vulnerabili a **MITM attack** per mesi.
    
- Bastava indurre l’utente a visitare un sito HTTPS malevolo (es. tramite Wi-Fi pubblico).
    
- Safari utilizzava proprio la versione affetta della libreria **SecureTransport**.
    
Un attaccante poteva quindi predisporre un sito HTTPS malevolo, attirare la vittima tramite Wi-Fi pubblico, DNS manipolato o routing controllato, e far accettare al client una connessione che avrebbe dovuto essere rifiutata.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->


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

Il controllo di revoca dovrebbe essere parte della validazione: quando un sito presenta un certificato, il client dovrebbe verificare non solo firma, scadenza e hostname, ma anche che quel certificato non compaia nelle liste di revoca o non risulti revocato tramite OCSP.

Il problema pratico è che interrogare sempre CRL o OCSP introduce latenza, dipendenza da servizi esterni e possibili problemi di disponibilità. Per questo, storicamente, molti client hanno adottato comportamenti permissivi quando il controllo di revoca non era raggiungibile.

> ⚠️ Una revoca non controllata equivale spesso a una revoca inefficace: un certificato compromesso può continuare a essere accettato dai client.

---

### **12. Compromissioni di CA reali**

#### **a. Caso Comodo (2011)**

![](imgs/Pasted%20image%2020260709032121.png)

- Comodo, una CA radice affidabile, emetteva certificati tramite **rivenditori**.
    
- Un hacker iraniano violò i sistemi di **instantSSL.it** e **GlobalTrust.it**, rubando credenziali e accedendo all’API di Comodo.
    
- Il 15 marzo 2011, furono emessi **9 certificati malevoli** per domini come:
    
    - `mail.google.com`
        
    - `login.yahoo.com`
        
    - `login.live.com`
        
    - `addons.mozilla.org`
        
    - `login.skype.com`
        

Tutti **formalmente validi** e accettati dai browser.

Il punto critico è che Comodo era una CA radice fidata: un certificato emesso tramite il suo sistema, anche se ottenuto abusivamente tramite rivenditori compromessi o API violate, risultava credibile per i browser degli utenti.

---

#### **b. Caso TrustWave (2012)**

![](imgs/Pasted%20image%2020260709032130.png)

- TrustWave ammise di aver fornito a un’azienda cliente un **certificato CA intermedio**, con lo scopo dichiarato di “prevenzione della perdita di dati”.
    
- In realtà, ciò consentiva al cliente di **intercettare e riscrivere** qualsiasi connessione HTTPS dei propri dipendenti.
    
- Se tale certificato fosse trapelato, avrebbe permesso di **falsificare qualsiasi certificato nel mondo**.
    
Un certificato CA intermedio non è un normale certificato di sito: consente di firmare altri certificati. Consegnarlo a un soggetto esterno significa trasferire una parte del potere di certificazione della CA.

---

#### **c. Caso TurkTrust (2013)**

![](imgs/Pasted%20image%2020260709032152.png)

- La CA turca **TurkTrust** rilasciò per errore **certificati intermedi CA** a clienti che avevano richiesto normali certificati di dominio.
    
- Uno di questi clienti (l’autorità di Ankara) usò il certificato per emettere un **falso certificato *.google.com**, usato per intercettare traffico HTTPS locale.
    
- Poiché era firmato da una CA radice fidata, il certificato risultava **valido in tutti i browser del mondo**.
    
Anche in questo caso il problema non era una debolezza matematica di TLS, ma un errore procedurale: certificati con privilegi di CA erano stati rilasciati a soggetti che avrebbero dovuto ricevere semplici certificati finali.

---

### **13. Conclusione**

Gli attacchi ai certificati SSL/TLS mostrano che la **sicurezza del web non è solo matematica**, ma anche **organizzativa e procedurale**.  
Gli errori di hash, di implementazione o di gestione della fiducia possono **annullare completamente** i benefici della crittografia.

Per garantire la sicurezza del canale TLS occorre:

- usare **algoritmi di hash robusti** (SHA-256 o superiori),
    
- evitare **CA non affidabili o automatizzate**,
    
- **verificare hostname e catena di fiducia**,
    
- e gestire attivamente la **revoca** dei certificati.
    

> ✅ Punto d’esame: la validazione TLS non è solo “verificare una firma”. Bisogna verificare catena, hostname, uso consentito del certificato, scadenza, revoca e affidabilità della CA.

> La crittografia protegge solo ciò che è matematicamente solido.  
> La fiducia, invece, va difesa ogni giorno con vigilanza e trasparenza.
