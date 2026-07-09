# **UD2 – Firewall avanzati**

### **Introduzione**

In questa unità si approfondiscono le **tecniche avanzate di protezione perimetrale**, analizzando come i firewall si comportano in **scenari complessi** e in presenza di **applicazioni dinamiche** o **multi-connessione**.  
Si studiano le **modalità di gestione del traffico** nei protocolli più delicati (SMTP, FTP, RPC), l’uso dei **Dynamic Packet Filters** e le **architetture Proxy**, che permettono un controllo più profondo del contenuto applicativo.

L’unità affronta infine gli **scenari di attacco e difesa** tipici delle moderne infrastrutture di rete e introduce le **architetture Netfilter e IPTables**, base dei firewall nei sistemi Linux.



---

## **Lezione 1: Packet Filtering Avanzati**

### **1. Introduzione**

I firewall basati su **packet filtering** sono ancora largamente utilizzati, ma la crescente complessità delle applicazioni e la diffusione di **traffico cifrato** impongono un’evoluzione verso tecniche più sofisticate.  
Molti attacchi moderni avvengono infatti su **porte non standard** o sfruttano **canali cifrati HTTPS/TLS**, rendendo insufficiente il semplice filtraggio stateful.

Secondo il **SonicWall Report (2018)**, quasi **il 20% dei 700 milioni di attacchi analizzati** proveniva da porte non standard.  
Inoltre, circa il **58% dei siti di phishing** oggi opera sotto **HTTPS**, sfruttando la fiducia legata al lucchetto del browser.

Il problema non riguarda solo i firewall di vecchia generazione: anche un firewall avanzato che voglia ispezionare il payload incontra difficoltà quando il contenuto applicativo è cifrato. La cifratura protegge le comunicazioni legittime, ma può proteggere anche payload malevoli, canali di comando e controllo o flussi di esfiltrazione dati.

![](imgs/Pasted%20image%2020260709144543.png)

I grandi data breach mostrano anche un altro limite del filtraggio puramente perimetrale: l’attaccante può riuscire a entrare, ma il danno maggiore si produce spesso quando i dati escono dalla rete per un periodo prolungato senza essere rilevati. Le tecniche di difesa devono quindi osservare anche il **traffico in uscita**, non soltanto quello in ingresso.

Un esempio tipico è l’attacco tramite email mirata a un singolo dipendente: un messaggio costruito bene può aggirare la barriera umana e innescare una compromissione massiva. Il transcript richiama il caso Yahoo, con un numero enorme di account email compromessi dopo un attacco mirato. Dopo l’ingresso iniziale, l’esfiltrazione può avvenire anche su canali cifrati, rendendo difficile capire a livello applicativo se il traffico sia lecito o malevolo.

> 📌 Il traffico cifrato è un vantaggio anche per gli attaccanti: nasconde i payload malevoli, ostacola l’ispezione di livello 7 e rende più difficile riconoscere l’esfiltrazione dati.

---

## **2. Difficoltà del packet filtering**

Il filtraggio statico (SPF) è efficace per applicazioni semplici come **Telnet**, **SSH** o **rlogin**, dove il **ruolo client-server** è fisso e lo scambio di pacchetti segue un pattern prevedibile di tipo _request/reply_.

Tuttavia, per applicazioni più complesse — come **SMTP**, **FTP** o **RPC** — la gestione diventa molto più delicata:

- i ruoli client/server possono **invertirsi dinamicamente**;
    
- le porte di comunicazione possono essere **negoziate a runtime**;
    
- alcuni protocolli utilizzano **più connessioni TCP parallele**.
    

Inoltre, molti protocolli applicativi hanno logiche interne che vivono nel **payload**: un packet filter può vedere indirizzi, porte, protocollo e flag, ma non necessariamente il significato dei comandi applicativi. Questo diventa critico quando il protocollo negozia porte o ruoli durante la sessione.

> 📌 In questi casi, un firewall deve essere configurato con estrema attenzione, evitando politiche troppo permissive. Più un protocollo richiede eccezioni e porte dinamiche, più cresce il rischio di errore nella configurazione.

---

## **3. SMTP – Simple Mail Transfer Protocol**

### **3.1. Funzione generale**

SMTP gestisce lo **scambio di messaggi di posta elettronica** tra server di posta, utilizzando la **porta TCP 25**.  
Ogni utente è identificato da un indirizzo del tipo `nomeutente@dominio`.

I client di posta, tuttavia, usano SMTP **solo per l’invio**; per ricevere messaggi si affidano ad altri protocolli:

- **POP3 (Post Office Protocol)**
    
- **IMAP (Internet Message Access Protocol)**
    

---

### **3.2. Evoluzione e sicurezza**

In origine, SMTP **non prevedeva autenticazione**, favorendo la nascita dello **SPAM** e dei **server open relay**.  
Oggi si utilizzano estensioni come:

- **SMTP-AUTH**, per autenticare il mittente;
    
- **STARTTLS**, per cifrare la connessione sulle porte standard (25, 110, 143);
    
- versioni SSL dedicate:
    
    - POP3S → porta 995
        
    - IMAPS → porta 993
        
    - SMTPS → porta 465
        

> Inoltre, il contenuto del messaggio può essere cifrato end-to-end con **PGP**.

Anche qui vale un principio già visto: filtrare un protocollo non basta se il protocollo, o la sua configurazione, è intrinsecamente debole. Per SMTP, i controlli minimi lato server includono la verifica del dominio del mittente, l’autenticazione obbligatoria e la coerenza tra utente autenticato e indirizzo mittente dichiarato.

---

### **3.3. Principali comandi SMTP**

|Comando|Funzione|
|---|---|
|`HELO` / `EHLO`|Identifica il client SMTP al server|
|`AUTH LOGIN`|Avvia l’autenticazione|
|`MAIL FROM:`|Specifica il mittente|
|`RCPT TO:`|Specifica il destinatario|
|`DATA`|Indica l’inizio del corpo del messaggio|
|`RSET`|Annulla i comandi precedenti|
|`VRFY`|Verifica l’esistenza di un utente|
|`NOOP`|Test di connessione (nessuna operazione)|
|`QUIT`|Termina la sessione|

---

### **3.4. Fasi di una sessione SMTP**

1. **Connessione TCP** alla porta 25 (risposta `220 Ready`)
    
2. **Handshake HELO/EHLO** (risposta `250 OK`)
    
3. **Invio del mittente** con `MAIL FROM:`
    
4. **Invio dei destinatari** con `RCPT TO:`
    
5. **Scrittura del messaggio** con `DATA`, chiusura con `.`
    
6. **Comando `QUIT`** e terminazione della connessione (`221 Closing`)
    

---

### **3.5. Codici di risposta**

|Codice|Significato|Esempio|
|---|---|---|
|**1xx**|Messaggio informativo||
|**2xx**|Successo|`250 Sender OK`|
|**3xx**|Successo parziale (richiede altro comando)|`354 Enter mail, end with "."`|
|**4xx**|Errore temporaneo|`421 Service not available`|
|**5xx**|Errore permanente|`501 Syntax error in parameters`|

---

### **3.6. Cascata di protocolli nella posta elettronica**

SMTP non va considerato isolatamente. In uno scenario reale:

1. il client di posta invia il messaggio al proprio mail server usando SMTP;
    
2. il mail server del mittente consegna il messaggio al mail server del dominio destinatario, spesso tramite SMTP/ESMTP;
    
3. il destinatario scarica o consulta la posta con POP3 o IMAP.
    

![](imgs/Pasted%20image%2020260709144615.png)

Quando si configura un firewall per la posta elettronica bisogna quindi sapere quali protocolli attraversano davvero il perimetro. In alcuni scenari i client interni parlano con il server aziendale senza attraversare il firewall, mentre è il server SMTP aziendale a comunicare con l’esterno.

> ⚠️ Telnet è storicamente usato per testare manualmente una sessione SMTP, perché i comandi SMTP sono testuali. Questo uso va inteso solo come test didattico o diagnostico: Telnet resta un protocollo insicuro e non va abilitato come servizio di amministrazione.

---

### **3.7. Protezioni anti-spam di base**

- Verifica dell’esistenza del **dominio mittente**
    
- **Autenticazione obbligatoria**
    
- Associazione tra utente e login
    
- Blocco del relay non autorizzato
    

---

## **4. Packet Filtering per SMTP**

### **4.1. Scenario aziendale**

Si vuole che **solo un server SMTP aziendale** possa comunicare con l’esterno.

```text
smtpSrv := 159.149.70.23
External := not(159.149.70.0/24)
```

Lo scenario assume che i client interni non attraversino il firewall per parlare con il server SMTP aziendale. Il firewall deve quindi regolare solo le comunicazioni tra `smtpSrv` e i mail server esterni.

![](imgs/Pasted%20image%2020260709144700.png)

---

### **4.2. Prima proposta (incompleta)**

|Direzione|IP sorg.|IP dest.|Prot.|Porta sorg.|Porta dest.|Flag|Azione|
|---|---|---|---|---|---|---|---|
|IN|External|smtpSrv|TCP|>1023|25|1/0|Permit|
|OUT|smtpSrv|External|TCP|25|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Questa configurazione copre **solo metà del comportamento SMTP**: consente ai server esterni di collegarsi al server aziendale e consente le relative risposte, ma ignora le connessioni generate dal server aziendale quando deve inviare posta verso altri mail server.

Il problema non è una vulnerabilità di sicurezza della regola, ma un errore funzionale: il protocollo non funzionerebbe completamente, perché `smtpSrv` potrebbe ricevere posta ma non consegnare posta all’esterno.

---

### **4.3. Configurazione corretta**

|Direzione|IP sorg.|IP dest.|Prot.|Porta sorg.|Porta dest.|Flag|Azione|
|---|---|---|---|---|---|---|---|
|IN|External|smtpSrv|TCP|>1023|25|1/0|Permit|
|OUT|smtpSrv|External|TCP|25|>1023|1|Permit|
|OUT|smtpSrv|External|TCP|>1023|25|1/0|Permit|
|IN|External|smtpSrv|TCP|25|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 In questo modo si gestiscono entrambe le direzioni: ricezione di posta da server esterni e invio di posta dal server aziendale verso altri server SMTP.

---

## **5. FTP – File Transfer Protocol**

### **5.1. Struttura**

FTP usa **due processi separati**:

- **PI (Protocol Interpreter)** per comandi e risposte
    
- **DTP (Data Transfer Process)** per il trasferimento dei dati
    

Il server ascolta sulla **porta TCP 21**, ma il trasferimento file avviene sulla **porta TCP 20**.

Una sessione FTP attraversa quindi, in forma semplificata, queste fasi:

1. il client contatta il server sulla porta `21/tcp` e instaura il canale di controllo;
    
2. sul canale di controllo avvengono autenticazione, comandi e risposte;
    
3. il trasferimento dei file avviene su un canale dati separato, gestito dal DTP;
    
4. al termine, si chiude la sessione TCP e termina la sessione FTP.
    

![](imgs/Pasted%20image%2020260709144733.png)

La difficoltà nasce dal fatto che il firewall statico non vede il significato dei comandi FTP nel payload. In particolare, nella modalità attiva il client comunica al server, tramite il comando `PORT`, quale porta alta dovrà essere contattata per la connessione dati.

---

### **5.2. Modalità operative**

|Modalità|Descrizione|
|---|---|
|**Active mode**|Il **server** apre la connessione dati verso il client (porta 20 → porta casuale >1023)|
|**Passive mode (PASV)**|Il **client** apre entrambe le connessioni (verso porta casuale del server)|

Nella **modalità attiva**, il client apre il canale di controllo verso `21/tcp`, poi comunica al server la porta su cui attende la connessione dati. Il server apre quindi la connessione dati dalla propria porta `20/tcp` verso quella porta alta del client.

Nella **modalità passiva**, invece, il client invia il comando `PASV`; è il server a comunicare una porta su cui resterà in ascolto, e il client apre anche la connessione dati verso quella porta. La porta `20/tcp` non viene più usata per iniziare la connessione dati.

> 📌 Oggi quasi tutti gli FTP moderni usano **Passive mode** di default (es. browser web), perché è molto più compatibile con firewall e NAT.

---

### **5.3. Problemi nel packet filtering**

Nel caso **Active mode**, il firewall deve accettare connessioni **in entrata da porte elevate (>1023)**.  
Questo è **molto pericoloso**, perché:

- espone porte usate anche da servizi e trojan;
    
- consente attacchi di **port scanning** e spoofing.
    

Il problema fondamentale è che la porta dati del client non è nota quando si scrive la regola del firewall: diventa nota solo durante lo scambio dei comandi FTP sul canale di controllo. Un filtro statico ha quindi due alternative entrambe problematiche:

- chiudere le porte alte e rompere FTP attivo;
    
- aprire molte porte alte e aumentare drasticamente la superficie d’attacco.
    

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il rischio specifico è che una macchina esterna finga di essere un server FTP e tenti di aprire una connessione dalla porta sorgente `20/tcp` verso una porta alta della vittima. Molti servizi, trojan e malware usano proprio porte non well-known, cioè maggiori di `1023`.

> ⚠️ Una regola `established` può peggiorare l’impatto: se la connessione viene accettata inizialmente, il firewall potrebbe trattare il traffico successivo come parte di una sessione ammessa.


#### Esempio di policy FTP attiva (rischiosa)

|Dir.|IP sorg.|IP dest.|Porta sorg.|Porta dest.|Azione|
|---|---|---|---|---|---|
|OUT|Internal|External|>1023|21|Permit|
|IN|External|Internal|21|>1023|Permit|
|IN|External|Internal|20|>1023|Permit|
|OUT|Internal|External|>1023|20|Permit|
|Any|Any|Any|Any|Any|Any|Deny|

---

### **5.4. FTP Passive mode**

Nel **Passive mode**, il client avvia la connessione dati, mantenendo così il **principio “solo connessioni interne in uscita”**.

| Dir. | IP sorg. | IP dest. | Porta sorg. | Porta dest. | Azione |
| ---- | -------- | -------- | ----------- | ----------- | ------ |
| OUT  | Internal | External | >1023       | 21          | Permit |
| IN   | External | Internal | 21          | >1023       | Permit |
| OUT  | Internal | External | >1023       | >1023       | Permit |
| IN   | External | Internal | >1023       | >1023       | Permit |
| Any  | Any      | Any      | Any         | Any         | Deny   |

![](imgs/Pasted%20image%2020260709145540.png)

Anche in modalità passiva rimane una certa dinamicità, perché la porta dati viene scelta dal server. Tuttavia, il verso delle connessioni è più favorevole: il client interno apre sia il canale di controllo sia quello dati verso l’esterno. Per un packet filter statico questo è molto più gestibile rispetto all’accettare connessioni dati aperte dall’esterno verso l’interno.

> 📌 È la modalità consigliata, poiché riduce drasticamente i rischi di apertura indesiderata di porte interne.

### **5.5. Perché FTP attivo richiede firewall più evoluti**

Per gestire FTP attivo in modo preciso, il firewall dovrebbe:

1. ispezionare il payload del canale di controllo;
    
2. interpretare i comandi `PORT` e `PASV`;
    
3. capire quale porta dati è stata negoziata;
    
4. aprire dinamicamente solo quella porta e solo per il tempo necessario;
    
5. richiuderla al termine del trasferimento.
    

Questo comportamento non appartiene allo static packet filtering tradizionale: richiede almeno un filtro dinamico capace di interpretare il protocollo applicativo.

---

## **6. RPC – Remote Procedure Call**

### **6.1. Funzione e vulnerabilità**

RPC consente a un’applicazione di **eseguire funzioni su un host remoto**.  
Microsoft ha riscontrato gravi vulnerabilità nelle **interfacce DCOM** che utilizzano **RPC su TCP/135**.

Un attaccante può inviare **messaggi malformati** alla porta 135 per sfruttare errori di gestione del protocollo e arrivare, nei casi peggiori, all’**esecuzione di codice arbitrario** sul sistema remoto.

Per questo motivo, quando RPC/DCOM non è strettamente necessario dall’esterno, la porta `135` viene normalmente bloccata al perimetro: lasciare esposto un servizio RPC significa consentire a host esterni di iniziare una conversazione con un componente storicamente delicato.

---

### **6.2. Difficoltà di filtraggio**

Il problema principale del filtraggio RPC è che **le porte sono dinamiche e imprevedibili**.  
Dopo la connessione iniziale su 135 o 111, il server assegna **una porta casuale** per la sessione.

Più precisamente:

1. un servizio RPC si registra presso un **port mapper**;
    
2. il port mapper conosce quale servizio è disponibile e su quale porta;
    
3. il client chiede al port mapper dove trovare il servizio desiderato;
    
4. il port mapper risponde indicando la porta da contattare;
    
5. il client apre una nuova connessione verso quella porta.
    

![](imgs/Pasted%20image%2020260709145602.png)

Il firewall statico non vede la semantica dello scambio con il port mapper: vede pacchetti, ma non interpreta il fatto che una certa porta sia stata appena comunicata come porta valida per un servizio RPC.

> ⚠️ Di conseguenza, un firewall tradizionale non può sapere in anticipo quali porte aprire, creando enormi buchi di sicurezza.

---

### **6.3. Policy di filtraggio RPC**

|Direzione|IP sorg.|IP dest.|Prot.|Porta sorg.|Porta dest.|Azione|
|---|---|---|---|---|---|---|
|IN|External|rpcSrv|TCP/UDP|>1023|111,135|Permit|
|OUT|rpcSrv|External|TCP/UDP|111,135|>1023|Permit|
|IN|External|rpcSrv|TCP/UDP|>1023|any|Permit|
|OUT|rpcSrv|External|TCP/UDP|any|>1023|Permit|
|Any|Any|Any|Any|Any|Deny|

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La terza riga è il punto critico: per permettere davvero i servizi RPC bisognerebbe accettare connessioni dall’esterno verso porte non note a priori. In pratica si finisce per aprire dall’esterno verso l’interno un insieme troppo ampio di porte, con una granularità incompatibile con una politica conservativa.

> 📌 Una configurazione simile è troppo permissiva e rischiosa, poiché consente connessioni da esterno a interno su porte alte decise dinamicamente dal protocollo.

---

### **6.4. Attacchi e contromisure**

- **Vulnerabilità RPC/DCOM su porta 135**: messaggi malformati possono sfruttare errori di parsing o gestione del protocollo e portare a denial of service o esecuzione remota di codice.
    
- **BlueKeep (CVE-2019-0708)**: vulnerabilità wormable su sistemi Windows non patchati via RDP (porta 3389), concettualmente rilevante perché mostra quanto sia pericoloso esporre servizi remoti vulnerabili.
    
- **EternalBlue/WannaCry**: famiglia storica di attacchi wormable su servizi Microsoft esposti, distinta da BlueKeep ma utile come confronto per capire l’impatto di vulnerabilità sfruttabili senza interazione dell’utente.
    
- **Derivati CVE-2019-0887**: vulnerabilità RDP anche in FreeRDP e rdesktop (client Kali Linux)
    
- **Contromisure**:
    
    - bloccare porte RPC/RDP non necessarie
        
    - mantenere sistemi aggiornati
        
    - usare firewall di nuova generazione con ispezione profonda (DPI)
        

Una vulnerabilità diventa molto più grave quando è **wormable**, cioè sfruttabile per propagare malware da macchina a macchina senza intervento dell’utente. In quel caso l’esposizione di un servizio al perimetro può trasformare una vulnerabilità locale di prodotto in un problema sistemico di rete.

Va considerato anche l’effetto cloud: se lo stesso codice o lo stesso servizio viene riutilizzato in ambienti on-premise e cloud, una vulnerabilità scoperta in un contesto può diventare rilevante anche nell’altro. La convergenza tecnologica facilita migrazione e compatibilità, ma può anche trasportare le stesse strategie d’attacco su infrastrutture più esposte e multi-tenant.

---

## **7. Protocolli firewall-friendly**

|Firewall-friendly|Non firewall-friendly|
|---|---|
|**SSH**, **Telnet**|**FTP attivo**, **RPC**, **RDP**|

I protocolli “friendly” mantengono porte e ruoli statici, hanno una direzionalità chiara e non negoziano dinamicamente porte nel payload. Questo li rende compatibili anche con firewall statici tradizionali.

I protocolli “non friendly”, invece, richiedono regole più articolate, aprono molte più possibilità di errore e spesso necessitano di firewall con capacità superiori allo static packet filtering, come ispezione applicativa, regole dinamiche o proxy.

> ⚠️ Firewall-friendly non significa sicuro: Telnet è semplice da filtrare perché usa un comportamento prevedibile, ma resta un protocollo insicuro e deprecato.

> 📌 Più regole servono per far funzionare un protocollo, più aumenta la probabilità di sbagliare configurazione. Quando possibile, è preferibile scegliere protocolli più semplici da filtrare e più sicuri per progetto.

---

## **8. Conclusione**

Il **packet filtering avanzato** richiede una conoscenza profonda dei protocolli applicativi.  
Ogni configurazione deve:

- mantenere una **politica conservativa**;
    
- considerare il comportamento **dinamico** delle applicazioni;
    
- evitare l’apertura indiscriminata di porte alte;
    
- integrare sistemi **stateful** e **DPI-SSL** per il traffico cifrato.
    

Quando un protocollo è noto per creare problemi al firewall o per avere vulnerabilità intrinseche, l’esperto di sicurezza deve valutarne l’uso già in fase di progettazione. Se non può evitarlo, perché il protocollo è già in uso o necessario al business, deve compensare con una soluzione di filtraggio più adeguata e con un posizionamento di rete che limiti l’impatto di eventuali attacchi.

Questo vale ancora di più per protocolli custom o proprietari: spesso non sono firewall-friendly, hanno scambi applicativi complessi e richiedono analisi specifiche prima di essere esposti oltre il perimetro.

> 📌 In sintesi: la sicurezza non consiste nel bloccare tutto, ma nel sapere **esattamente cosa permettere**, **perché**, **in quale direzione** e **con quali effetti collaterali sul firewall**.


---
