## **Lezione 5: ACL e protocolli applicativi**

### **1. Introduzione**

Dopo aver studiato il funzionamento generale delle **Access Control List (ACL)**, questa lezione mostra come esse vengano applicate ai **protocolli applicativi** più comuni — in particolare **FTP**, **Telnet** e **SSH**.  
L’obiettivo è capire come costruire regole di filtraggio efficaci che rispettino il **principio del minimo privilegio**, evitando politiche troppo permissive o ridondanti.

Il punto centrale è che, per scrivere regole corrette, non basta conoscere le porte standard: bisogna sapere **come funziona il protocollo**. Un packet filter vede principalmente connessioni, indirizzi, porte e flag; non vede invece il significato dei messaggi applicativi contenuti nel payload.

---

## **2. Il caso FTP (File Transfer Protocol)**

### **2.1 Struttura del protocollo FTP**

FTP è un protocollo applicativo standard per il **trasferimento di file** tra un client e un server.  
Utilizza **due connessioni distinte**:

|Tipo di connessione|Porta|Direzione|Descrizione|
|---|---|---|---|
|**Comandi**|21/tcp|Client → Server|Controlla la sessione FTP (login, comandi, risposte)|
|**Dati**|20/tcp|Server → Client|Trasmette i file veri e propri|

Entrambe le connessioni utilizzano **porte TCP maggiori di 1023** sul lato client.

#### **Sequenza di connessione tipica**

1. Il client apre la connessione **comandi** (porta sorgente >1023 → 21/tcp).
    
2. Il server risponde e apre la connessione **dati** (porta 20/tcp → porta >1023 del client).
    
3. Entrambe le connessioni utilizzano il **three-way handshake** TCP.
    
4. I dati viaggiano in parallelo alle comunicazioni di controllo.
    

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nel caso di **FTP attivo**, durante la connessione di controllo il client comunica al server, tramite un messaggio nel payload FTP, su quale porta alta vuole ricevere la connessione dati. Ad esempio, il client può aprire una connessione di controllo da `1039` verso `21/tcp` e indicare al server di aprire la connessione dati verso la porta `1038`. Il server, a quel punto, inizializza la connessione dati dalla propria porta `20/tcp` verso `1038` del client.

> 📌 FTP è un protocollo “duale” e non banale da gestire nei firewall, perché usa una connessione di controllo e una connessione dati, con direzioni diverse e una porta dati negoziata nel payload.

---

### **2.2 Blocco selettivo del traffico FTP**

Supponiamo di voler **bloccare l’FTP ma permettere il resto del traffico**.  
Poiché FTP usa le porte 20 e 21, è sufficiente definire regole `deny` specifiche su tali porte.

#### **Esempio di configurazione**

```bash
access-list 103 deny tcp 192.168.3.0 0.0.0.255 192.168.2.0 0.0.0.255 eq 21
access-list 103 deny tcp 192.168.3.0 0.0.0.255 192.168.2.0 0.0.0.255 eq 20
access-list 103 permit ip any any
interface fa0/1
ip access-group 103 in
```

Queste regole:

- negano il traffico FTP intercettato sull’interfaccia considerata verso le porte 20 e 21,
    
- ma permettono tutto il resto (`permit ip any any`),
    
- e vengono applicate **in ingresso** all’interfaccia `fa0/1`.
    

Nel caso generale, per bloccare davvero FTP attivo bisogna considerare sia la connessione di controllo verso `21/tcp`, sia la connessione dati che può partire dal server da `20/tcp` verso una porta alta del client. La difficoltà nasce dal fatto che la porta dati scelta dal client viene comunicata nel payload, che un semplice packet filter non interpreta.


---

### **2.3 Esercizio – Blocco FTP tra due reti**

#### **Richiesta**

Negare il traffico FTP tra le reti:

- `172.16.4.0/24`
    
- `172.16.3.0/24`
    

#### **Soluzione**

```bash
access-list 101 deny tcp 172.16.4.0 0.0.0.255 172.16.3.0 0.0.0.255 eq 21
access-list 101 permit ip 172.16.4.0 0.0.0.255 any
interface ethernet 0
ip access-group 101 out
```

In questo esercizio è sufficiente bloccare la porta `21/tcp`, assumendo che il server FTP esponga il servizio sulla porta standard: se il client non riesce nemmeno ad aprire la connessione di controllo, non potrà negoziare né avviare la successiva connessione dati.

> Se `ip access-group` viene usato senza specificare `in` o `out`, Cisco applica di default la ACL in **uscita**; nell’esempio la direzione `out` è indicata esplicitamente.

---

## **3. Esercizio complesso – ACL combinate**

### **Scenario**

- **Host B** non deve accedere al server FTP.
    
- **Host C** non deve accedere in alcun modo alla rete `172.16.3.0`.
    
- Gli altri host devono poter comunicare liberamente.
    

#### **Rete di riferimento**

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- Host B → `172.16.4.1`
    
- Host C → `172.16.4.12`
    
- Server FTP → `172.16.3.52`
    

---

### **Soluzione**

#### **1. ACL standard su Router B**

Blocca Host C verso la rete interna:

```bash
access-list 1 deny host 172.16.4.12
access-list 1 permit any
interface ethernet 1
ip access-group 1 in
```

#### **2. ACL estesa su Router A**

Blocca Host B verso il server FTP:

```bash
access-list 101 deny tcp host 172.16.4.1 172.16.3.0 0.0.0.255 eq ftp
access-list 101 permit ip 172.16.4.0 0.0.0.255 any
interface ethernet 0
ip access-group 101 out
```

La ACL standard basta per Host C perché l’obiettivo è bloccare qualunque accesso alla rete `172.16.3.0`: è sufficiente filtrare la sorgente. Per Host B serve invece una ACL estesa, perché bisogna bloccare solo il traffico FTP e lasciare passare altri protocolli.

> 📌 In questo modo, Host C è isolato dalla rete `172.16.3.0`, mentre Host B non può connettersi al servizio FTP ma può accedere ad altri protocolli.

---

## **4. Formalismo delle regole ACL**

Le ACL statiche (SPF) possono essere rappresentate come **tabelle di regole** che specificano i parametri di ogni pacchetto.

Questo formalismo è utile perché separa la **politica di filtraggio** dalla sintassi del prodotto concreto: una stessa tabella può poi essere tradotta in comandi Cisco, regole `iptables` o configurazioni di altri firewall.

|Direzione|IP sorgente|IP destinazione|Protocollo|Porta sorgente|Porta destinazione|Flag ACK|Azione|
|---|---|---|---|---|---|---|---|
|OUT|Internal|Any|TCP|>1023|23|1/0|Permit|
|IN|Any|Internal|TCP|23|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

---

### **4.1 Campi principali**

- **Direzione:** IN, OUT, o percorso logico tra zone (es. `DMZ → Internet`)
    
- **IP sorgente/destinazione:** singoli host, subnet o variabili simboliche
    
- **Protocollo:** TCP, UDP, ICMP o IP
    
- **Porte:** numeriche o in intervalli (`eq`, `gt`, `lt`)
    
- **Flag ACK:** utile per distinguere pacchetti di richiesta (SYN) e di risposta (ACK)
    
- **Azione:** `permit` o `deny`
    

Per TCP, il campo ACK può essere usato per distinguere pacchetti che aprono una connessione da pacchetti di risposta. Per protocolli non TCP, oppure quando il flag non è rilevante, il valore viene indicato con un wildcard come `*` o `**`.


---

## **5. Variabili e riusabilità**

In configurazioni complesse è comune definire **variabili** per rendere la policy più leggibile e manutenibile.

#### **Esempio**

```bash
DMZ := 159.149.70.0/24
Internal := 192.168.20.0/24
Private := 10.0.0.0/8
External := not (Internal or DMZ or Private)
WebServer := 159.149.70.11 or 159.149.70.12
```

In questo modo si possono **modificare le reti** senza alterare direttamente le regole ACL. Inoltre le regole diventano più leggibili: `DMZ`, `Internal`, `External` o `WebServer` esprimono il ruolo logico degli indirizzi, non solo il loro valore numerico.

---

## **6. Il protocollo Telnet**

### **6.1 Funzionamento**

**Telnet** è un protocollo standard per la connessione remota tra terminali (RFC 854).  
Permette a un utente di controllare un host remoto tramite **riga di comando**, inviando caratteri ASCII su una connessione TCP **non cifrata**.

Telnet implementa il modello di **Network Virtual Terminal**: collega un client a un server che interpreta comandi e produce risposte. Le specifiche di base non introducono meccanismi propri di autenticazione, autorizzazione o cifratura; questi aspetti dipendono dalle applicazioni e dai sistemi che lo usano.

|Caratteristica|Valore|
|---|---|
|Porta standard|23/tcp|
|Tipo di comunicazione|Bidirezionale|
|Sicurezza|Nessuna cifratura|
|Modello|Network Virtual Terminal (NVT)|
|Sostituito da|**SSH**|

---

### **6.2 Politiche di filtraggio Telnet**

#### **Obiettivo**

Permettere solo connessioni Telnet **dall’interno verso l’esterno**.

#### **Regole**

|Direzione|IP sorg.|IP dest.|Protoc.|Porta sorg.|Porta dest.|Flag ACK|Azione|
|---|---|---|---|---|---|---|---|
|OUT|Internal|Any|TCP|>1023|23|1/0|Permit|
|IN|Any|Internal|TCP|23|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

Una prima tabella ingenua potrebbe permettere traffico in ingresso da qualunque host esterno con porta sorgente `23` verso qualunque porta alta interna. Questa scelta è troppo permissiva: un pacchetto esterno costruito con porta sorgente `23` potrebbe attraversare il firewall anche se non è una vera risposta a una connessione Telnet avviata dall’interno.

L’uso del flag ACK migliora la situazione: i pacchetti in ingresso devono apparire come risposte a una connessione già avviata. Tuttavia, in un filtro statico, questo non prova davvero che la connessione sia stata instaurata correttamente.

> ⚠️ Solo le risposte con ACK vengono accettate, ma un packet filter stateless non può escludere completamente risposte simulate.

---

### **6.3 Restrizione ai soli server Telnet autorizzati**

Per evitare eccessiva permissività, si limita l’accesso ai soli host autorizzati.

#### **Esempio**

```
tlnSrv := 159.149.70.13
```

|Direzione|IP sorg.|IP dest.|Protoc.|Porta sorg.|Porta dest.|Flag ACK|Azione|
|---|---|---|---|---|---|---|---|
|OUT|Internal|tlnSrv|TCP|>1023|23|1/0|Permit|
|IN|tlnSrv|Internal|TCP|23|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

Questa versione è più restrittiva perché non ammette Telnet verso qualunque server Internet, ma soltanto verso server esplicitamente autorizzati. Anche così, resta possibile tentare spoofing o costruire pacchetti che sembrano provenire dal server ammesso: il miglioramento è reale, ma non equivale a una verifica stateful completa.

---

## **7. Principio del minimo privilegio**

Una buona politica firewall deve essere **più restrittiva possibile**, compatibilmente con le funzionalità richieste dai servizi.  
Definire una politica più permissiva del necessario è **un errore di sicurezza**.

Il progettista non deve limitarsi a implementare passivamente una richiesta generica, ma deve valutarla criticamente: spesso una specifica iniziale può essere resa più precisa e meno permissiva senza impedire il servizio richiesto.

> 📌 Ogni regola deve rispondere alla domanda:  
> “È davvero indispensabile permettere questo traffico?”

Nel caso di Telnet, la conclusione operativa è ancora più netta: anche se si riescono a scrivere ACL relativamente restrittive, il protocollo è deprecato e insicuro. In una rete moderna, di norma, Telnet dovrebbe essere vietato e sostituito da SSH.

---

## **8. Il protocollo SSH**

### **8.1 Funzionamento**

**SSH (Secure Shell)** è il successore sicuro di Telnet.  
Stabilisce una **sessione remota cifrata**, offrendo autenticazione, integrità e riservatezza dei dati.

SSH è più articolato di Telnet: comprende un livello di trasporto sicuro, un livello di autenticazione dell’utente e un connection layer che consente di aprire canali ed eseguire comandi. Dal punto di vista del packet filtering semplice, però, interessa soprattutto il fatto che usa TCP e normalmente la porta `22`.

|Caratteristica|Descrizione|
|---|---|
|Porta predefinita|22/tcp|
|Sicurezza|Tutta la comunicazione è cifrata|
|Livelli del protocollo|Transport Layer, User Authentication, Connection Layer|
|Meccanismi di autenticazione|Password, chiavi pubbliche, challenge-response|
|Utilizzo tipico|Amministrazione remota, tunneling sicuro, file transfer (SCP, SFTP)|

---

### **8.2 Filtraggio SSH**

#### **Regole**

|Direzione|IP sorg.|IP dest.|Protoc.|Porta sorg.|Porta dest.|Flag ACK|Azione|
|---|---|---|---|---|---|---|---|
|OUT|Internal|sshSrv|TCP|>1023|22|1/0|Permit|
|IN|sshSrv|Internal|TCP|22|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

Dal punto di vista della tabella ACL, rispetto a Telnet cambia quasi solo la porta: `23` diventa `22`. Dal punto di vista della sicurezza del protocollo, invece, cambia tutto, perché SSH cifra la comunicazione e fornisce meccanismi di autenticazione.

> 📌 SSH può operare anche su porte diverse dalla 22, per motivi di sicurezza o segmentazione, ma la logica di filtraggio resta invariata.

---

## **9. Conclusione**

Le ACL applicate ai protocolli applicativi dimostrano che:

- il **filtraggio a livello 3–4** non è sufficiente se non si considerano le caratteristiche del protocollo;
    
- occorre distinguere tra **connessioni in uscita e in ingresso**, e usare correttamente i flag TCP;
    
- l’uso di **variabili e formalismi coerenti** semplifica la gestione delle policy.
    

Un firewall packet filter può decidere se un flusso deve transitare, ma non può trasformare un protocollo insicuro in un protocollo sicuro: ciò che resta dentro il payload e nella logica applicativa è responsabilità del protocollo e del servizio.

> 📌 In sintesi: una ACL ben progettata lascia passare solo ciò che serve, nella direzione corretta, ma deve essere accompagnata dalla scelta di protocolli intrinsecamente sicuri.

---
