## **Lezione 2: Il livello di trasporto**

### **1. Introduzione al livello di trasporto**

Il **livello di trasporto** ha la responsabilità di garantire la **consegna dei dati da processo a processo**, cioè di assicurare che le informazioni inviate da un’applicazione su un host arrivino correttamente all’applicazione corrispondente su un altro host.

Mentre il **livello di rete** si occupa della trasmissione _host-to-host_, il livello di trasporto gestisce la comunicazione _process-to-process_.

---

### **2. Funzioni principali del protocollo TCP**

Il protocollo **TCP (Transmission Control Protocol)** fornisce una **trasmissione affidabile e orientata alla connessione**.  
Le sue funzioni principali sono:

- Il **mittente** suddivide i dati in pacchetti, numerando ciascun segmento con un **numero di sequenza**.
    
- Il **ricevitore** riassembla i pacchetti nell’ordine corretto, riconosce la ricezione e richiede la **ritrasmissione** dei pacchetti persi.
    
- Entrambe le parti (mittente e destinatario) **mantengono lo stato della connessione** per tutta la durata della comunicazione.
    

> In sintesi, TCP garantisce che i dati arrivino **completi, ordinati e senza duplicazioni**.

---

### **3. Identificazione dei processi: porte e socket**

Ogni processo in esecuzione su un host è identificato da un **numero di porta**, che serve per distinguere le diverse applicazioni che utilizzano la rete.  
Le porte vengono gestite attraverso l’**API socket**, che rappresenta il punto di accesso ai servizi di rete.

Un **socket** è definito come la combinazione di un **indirizzo IP** e un **numero di porta**:

$$  
\text{Socket} = (\text{IP}, \text{Porta})  
$$

Una comunicazione tra due processi è identificata da una **coppia di socket (socket pair)**:

$$  
(ip_1, n_1) : (ip_2, n_2)  
$$

Ogni scambio di segmenti TCP o UDP tra due processi si basa su questa quadrupla di identificazione.

---

### **4. Numeri di porta e assegnazione**

I **numeri di porta** sono interi a 16 bit, con valori compresi tra **0 e 65535**.  
La loro assegnazione è regolata dalla **IANA (Internet Assigned Numbers Authority)**, un dipartimento dell’**ICANN**, che si occupa di riservare porte per servizi specifici.

#### **Classificazione delle porte**

|**Intervallo**|**Tipo**|**Descrizione**|
|---|---|---|
|0 – 1023|**Well-known ports**|Riservate per servizi noti (HTTP, FTP, SMTP, ecc.)|
|1024 – 49151|**Registered ports**|Assegnate ad applicazioni specifiche|
|49152 – 65535|**Dynamic / Private ports**|Utilizzate temporaneamente dai client|

#### **Esempi di porte note**

|**Servizio**|**Porta**|**Protocollo**|
|---|---|---|
|FTP – file transfer|21 / 20|TCP|
|SSH – secure shell|22|TCP/UDP|
|Telnet|23|TCP|
|SMTP – posta elettronica|25|TCP|
|DNS|53|TCP/UDP|
|HTTP – web|80|TCP|
|POP3 – posta in arrivo|110|TCP|
|NTP – sincronizzazione oraria|123|UDP|
|HTTPS – web sicuro|443|TCP|
|Printer spooler|515|TCP|

---

### **5. Porte e processi**

- Un **numero di porta di destinazione** identifica **il processo destinatario** su un host remoto.
    
- Tuttavia, **più processi possono condividere lo stesso numero di porta**, a seconda del tipo di protocollo e configurazione (ad esempio, server multipli in ascolto tramite binding).
    
- Un singolo processo può utilizzare **più socket**, ciascuno con un identificatore univoco.
    
- Un processo può ricevere pacchetti **da più host** sulla stessa porta **solo con UDP** (non con TCP).
    

> In TCP, ogni connessione è univocamente identificata da una coppia di socket, quindi due client possono connettersi alla stessa porta server ma con indirizzi IP diversi.

---

### **6. Uso e restrizioni delle porte note**

Il fatto che alcune porte (0–1023) siano “riservate” non significa che sia **impossibile** utilizzarle per altri scopi, ma solo che è **sconsigliato** o **limitato** per motivi di sicurezza e compatibilità.

Esempio:

- Vietare l’uso della porta **22** non significa vietare **SSH**, ma impedire ai client e ai server di stabilire connessioni standard su quella porta, costringendoli a utilizzare porte alternative.
    

> La riservazione serve a **standardizzare le comunicazioni** e **ridurre i conflitti** tra applicazioni.

---

### **7. Protocolli del livello di trasporto: TCP e UDP**

Nel livello di trasporto della suite **TCP/IP** operano due protocolli fondamentali:

#### **a. Transmission Control Protocol (TCP)**

- Orientato alla connessione (_connection-oriented_).
    
- Affidabile (garantisce la consegna).
    
- Garantisce l’ordinamento dei pacchetti e la ritrasmissione in caso di perdita.
    
- Ideale per applicazioni che richiedono **integrità dei dati** (es. HTTP, FTP, SMTP).
    

#### **b. User Datagram Protocol (UDP)**

- Senza connessione (_connectionless_).
    
- Non garantisce la consegna né l’ordinamento.
    
- È più veloce, con minor overhead.
    
- Adatto per applicazioni che privilegiano la **rapidità alla completezza**, come DNS, streaming e giochi online.
    

---

### **8. Sintesi finale**

Il **livello di trasporto** è il punto di collegamento tra la rete e le applicazioni.  
Gestisce la comunicazione tra processi, identificandoli tramite **porte** e **socket**, e fornisce protocolli con **diversi livelli di affidabilità** in base alle esigenze.

> In sintesi:
> 
> - TCP garantisce **affidabilità e controllo**,
>     
> - UDP offre **leggerezza e velocità**,
>     
> - e l’intera architettura poggia sull’uso coerente dei numeri di porta definiti dallo **standard IANA**.
>     