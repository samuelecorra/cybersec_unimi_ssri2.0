# **Lezione 2: SNMP (Simple Network Management Protocol)**

---

### **1. Visione d'insieme**

**SNMP (Simple Network Management Protocol)** è uno dei protocolli più importanti a livello applicativo e uno dei più utili per gli amministratori di rete, pur rimanendo spesso **dietro le quinte** — gli utenti finali non lo vedono mai direttamente.

Durante tutto il corso ci siamo spesso occupati di meccanismi per la gestione e la configurazione del software di rete e dei dispositivi come router e bridge, senza però mai entrare nel dettaglio di **come** questa configurazione viene concretamente eseguita.

È ovviamente possibile configurare manualmente ogni singolo dispositivo e ogni singolo host, collegandosi fisicamente alla console di ciascuno e introducendo le informazioni necessarie. Esistono però dei protocolli — e il **principio fondamentale** di SNMP è esattamente questo: gestire i dispositivi **leggendo e impostando i valori di alcune variabili** che ne controllano il funzionamento.

L'insieme dei dispositivi di un **dominio di amministrazione SNMP** — che coincide di solito con la rete dell'azienda o dell'organizzazione — è rappresentato come un insieme di oggetti, ciascuno con dei propri attributi. Su questi attributi il protocollo può operare in **test** (lettura del valore) o in **set** (impostazione del valore). Attraverso una serie di operazioni di test e set è possibile leggere e configurare l'intera rete da **un'unica console di amministrazione** che comunica tramite SNMP con i vari dispositivi.

---

### **2. Protocollo dei messaggi SNMP**

I messaggi SNMP hanno in genere un **ambito di circolazione ristretto** e, per rubare meno banda possibile alle connessioni e al trasporto dei dati utente, la scelta del protocollo di trasporto è **UDP**. Le porte standard sono:

- **161** → per le richieste di test/get e le risposte;
- **162** → per le **notifiche proattive** di cambiamento di stato da parte dei dispositivi.

#### **Tipi principali di messaggi SNMP:**

| Tipo di messaggio | Descrizione |
|---|---|
| **GetRequest** | Il manager richiede il valore di una o più variabili dal dispositivo (agente). |
| **GetNextRequest** | Richiede la **variabile successiva** nella gerarchia della MIB, utile per leggere le variabili una dopo l'altra. |
| **GetResponse** | È la **risposta dell'agente** al manager, contenente i valori richiesti. |
| **SetRequest** | Consente da una console remota di **impostare i valori** di certi attributi di un dispositivo per configurarlo. È forse il messaggio più importante. |
| **Trap** | Messaggio **spontaneo dell'agente** (porta 162) verso il manager, per segnalare eventi o cambiamenti di stato — es. caduta di un dispositivo adiacente, cambio di stato della rete. |

---

### **3. Sicurezza in SNMP**

> ⚠️ È un indice della **scarsa attenzione alla sicurezza** che caratterizzava i primi protocolli applicativi il fatto che SNMP sia ancora oggi molto usato in versione **autenticata ma non crittografata**: i messaggi viaggiano in chiaro, consentendo a un eventuale sniffer di farsi un'idea precisa dello stato della rete intercettata.

Al posto di una autenticazione formale, si utilizza attualmente il concetto di **community**: gruppi di utenti con diversi livelli di accesso in lettura e scrittura a diversi tipi di variabili. Ad esempio:

- una community di **power user** può leggere tutte le variabili del dispositivo senza poter fare operazioni di set;
- le operazioni di set sono riservate a utenti esperti o all'amministratore di rete;
- è possibile riservare le operazioni di set (configurazione) di certe classi di dispositivi ad **amministratori specializzati** in quel tipo di apparato.

> 💡 Vi è invitati a cercare autonomamente la documentazione sulle versioni di SNMP che prevedono sia l'autenticazione dei messaggi sia la loro **crittografia**.

---

### **4. Codifica dei messaggi: SMI e ASN.1**

Così come gli altri protocolli applicativi di Internet, anche SNMP è **orientato al carattere** sia per i nomi degli attributi sia per i loro valori. Questi valori devono però essere codificati in modo da poter essere scambiati tra dispositivi di tipo diverso.

La codifica è definita da uno standard internazionale per i valori degli attributi dei dispositivi di telecomunicazione (usato anche in ambito telefonico): lo **SMI (Structure of Management Information)**. SMI fornisce una tecnica di codifica in stringhe di carattere dei valori degli attributi — il cosiddetto **ASN.1 (Abstract Syntax Notation One)** con regole di codifica **BER (Basic Encoding Rules)**.

> 📌 Il valore di un attributo scritto su una console deve essere convertito in una **rappresentazione di tipo di dato astratta** (ASN.1) per essere accettabile da un dispositivo remoto (router, PC). L'implementazione SNMP a bordo del dispositivo si occupa poi di convertirlo nella rappresentazione locale.

#### **Tipi di dato usati in SNMP:**

- Tipi ASN.1 di base: `INTEGER`, `OCTET STRING` (caratteri ASCII), `OBJECT IDENTIFIER`, `SEQUENCE`.
- Tipi specifici di SNMP, tra cui il più utile è l'**`IPAddress`** (indirizzo IP a 32 bit): quando si assegna un indirizzo IP a un dispositivo in configurazione remota, si modifica il valore di una sola variabile inviandolo in formato ASN.1/BER; l'implementazione SNMP del dispositivo lo converte poi nella rappresentazione locale dell'indirizzo IP.

Questo problema di rappresentare in modo indipendente dalla rete e dai dispositivi i tipi di dati sarà ripreso nelle ultime lezioni del corso parlando dei paradigmi di programmazione.

---

### **5. La MIB: Management Information Base**

Come fa l'operatore alla console a sapere quali dispositivi sono installati sulla rete e quali oggetti SMI può andare a modificare?

Tutte le rappresentazioni dei dispositivi di rete in formato SMI sono custodite in una **MIB (Management Information Base)**: un database in **formato testo** che riunisce tutte le informazioni su tutti i dispositivi — tutti gli attributi, i loro valori e i dispositivi installati sulla rete.

Per ogni dispositivo standard (router, host, bridge) esistono attributi definiti nelle **RFC dell'IETF** su cui si possono fare operazioni di test e set. Esistono però anche attributi **specifici di certi modelli o costruttori** (_proprietary MIBs_) — occorre conoscerli per poterli modificare in fase di configurazione.

---

### **6. Esempio di MIB: il gruppo Interfaces**

Per concludere, vediamo un frammento reale di MIB — si tratta di un semplice file di testo:

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

```
-- the Interfaces group

-- Implementation of the Interfaces group is mandatory for
-- all systems.

ifNumber OBJECT-TYPE
    SYNTAX  INTEGER
    ACCESS  read-only
    STATUS  mandatory
    DESCRIPTION
        "The number of network interfaces (regardless of
        their current state) present on this system."
    ::= { interfaces 1 }
```

Il **gruppo Interfaces** è un gruppo di attributi **obbligatorio per tutti i sistemi**: qualunque dispositivo deve averlo, altrimenti non sarebbe raggiungibile e gestibile tramite SNMP.

L'oggetto `ifNumber` è una semplice variabile che tiene traccia del **numero di interfacce di rete installate sul dispositivo**. L'accesso è `read-only` perché non è possibile aggiungere schede di rete mandando un messaggio: le schede sono un parametro definito in fase di acquisto e installazione fisica, non un parametro di configurazione software.

`::= { interfaces 1 }` è l'**OID assignment**: assegna a questo oggetto la posizione `1` all'interno del nodo `interfaces` nella gerarchia della MIB.

---

### **7. Conclusione**

**SNMP** è la spina dorsale dell'amministrazione remota delle reti IP: un protocollo semplice ma potente, basato sul concetto di manipolazione di variabili che descrivono lo stato di ogni apparato. Grazie a UDP, ad ASN.1/SMI e alla MIB, SNMP consente monitoraggio centralizzato, configurazione remota e notifica automatica degli eventi — anche se la trasmissione in chiaro rimane un limite nelle implementazioni più diffuse.
