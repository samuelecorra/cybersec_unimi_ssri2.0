## **Lezione 5: ACL e protocolli applicativi**

### **1. Introduzione**

Dopo aver studiato il funzionamento generale delle **Access Control List (ACL)**, questa lezione mostra come esse vengano applicate ai **protocolli applicativi** più comuni — in particolare **FTP**, **Telnet** e **SSH**.  
L’obiettivo è capire come costruire regole di filtraggio efficaci che rispettino il **principio del minimo privilegio**, evitando politiche troppo permissive o ridondanti.

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
    

```
Client (>1023)  ⇄ 21/tcp  Server (FTP commands)
Client (>1023)  ⇄ 20/tcp  Server (FTP data)
```

> FTP è un protocollo “duale” e non banale da gestire nei firewall, perché le due connessioni hanno **direzioni opposte**.

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

- negano il traffico FTP in entrambe le direzioni,
    
- ma permettono tutto il resto (`permit ip any any`),
    
- e vengono applicate **in ingresso** all’interfaccia `fa0/1`.
    

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

> Il comando `ip access-group 101` senza specificare `in` o `out` applica di default la regola in **uscita**.

---

## **3. Esercizio complesso – ACL combinate**

### **Scenario**

- **Host B** non deve accedere al server FTP.
    
- **Host C** non deve accedere in alcun modo alla rete `172.16.3.0`.
    
- Gli altri host devono poter comunicare liberamente.
    

#### **Rete di riferimento**

```
172.16.4.0/24  — (Router A) — (Router B) — 172.16.3.0/24
```

- Host B → `172.16.4.12`
    
- Host C → `172.16.4.1`
    
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

> In questo modo, Host C è isolato dalla rete 172.16.3.0, mentre Host B non può connettersi al servizio FTP ma può accedere ad altri protocolli.

---

## **4. Formalismo delle regole ACL**

Le ACL statiche (SPF) possono essere rappresentate come **tabelle di regole** che specificano i parametri di ogni pacchetto.

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
    

---

## **5. Variabili e riusabilità**

In configurazioni complesse è comune definire **variabili** per rendere la policy più leggibile e manutenibile.

#### **Esempio**

```bash
DMZ := 159.149.70.0/24
Internal := 192.168.20.0/24
Private := 10.0.0.0/8
External := not (Internal or DMZ or Private)
WebServer := 159.149.70.11 and 159.149.70.12
```

In questo modo si possono **modificare le reti** senza alterare direttamente le regole ACL.

---

## **6. Il protocollo Telnet**

### **6.1 Funzionamento**

**Telnet** è un protocollo standard per la connessione remota tra terminali (RFC 854).  
Permette a un utente di controllare un host remoto tramite **riga di comando**, inviando caratteri ASCII su una connessione TCP **non cifrata**.

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

> Solo le risposte provenienti da server Telnet legittimi vengono accettate (ACK = 1).

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

---

## **7. Principio del minimo privilegio**

Una buona politica firewall deve essere **più restrittiva possibile**, compatibilmente con le funzionalità richieste dai servizi.  
Definire una politica più permissiva del necessario è **un errore di sicurezza**.

> Ogni regola deve rispondere alla domanda:  
> “È davvero indispensabile permettere questo traffico?”

---

## **8. Il protocollo SSH**

### **8.1 Funzionamento**

**SSH (Secure Shell)** è il successore sicuro di Telnet.  
Stabilisce una **sessione remota cifrata**, offrendo autenticazione, integrità e riservatezza dei dati.

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
|OUT|Internal|tlnSrv|TCP|>1023|22|1/0|Permit|
|IN|tlnSrv|Internal|TCP|22|>1023|1|Permit|
|Any|Any|Any|Any|Any|Any|**|Deny|

> SSH può operare anche su porte diverse dalla 22, per motivi di sicurezza o segmentazione, ma la logica di filtraggio resta invariata.

---

## **9. Conclusione**

Le ACL applicate ai protocolli applicativi dimostrano che:

- il **filtraggio a livello 3–4** non è sufficiente se non si considerano le caratteristiche del protocollo;
    
- occorre distinguere tra **connessioni in uscita e in ingresso**, e usare correttamente i flag TCP;
    
- l’uso di **variabili e formalismi coerenti** semplifica la gestione delle policy.
    

> In sintesi:  
> **Una ACL ben progettata è come una serratura intelligente:** lascia passare solo ciò che serve, nel momento giusto e nella direzione corretta.

---