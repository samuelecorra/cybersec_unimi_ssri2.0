# **Lezione 4: Risoluzione dei nomi**

---

### **1. Il processo di risoluzione**

Quando un'applicazione (ad esempio un browser) deve connettersi a un sito web come `www.ripe.net`, non conosce il relativo indirizzo IP. Per ottenerlo, si affida al **DNS**, attraverso un meccanismo chiamato **risoluzione dei nomi** (_name resolution_).

Il processo di risoluzione e caching funziona così:

![](imgs/Pasted%20image%2020260622211235.png)

1. La query parte dal **resolver**, un programma che gira all'interno del client (spesso come parte del sistema operativo), il quale invia il nome completo al **DNS locale di rete**, che è un **caching forwarder** (non un server autorevole).

2. Il forwarder si rende conto di non avere ancora risolto quel nome, quindi ha bisogno di aiuto. Si rivolge al **root server**, che risponde indicando il **server TLD** appropriato (in questo caso il server per `.net`, identificabile guardando l'ultima parte del nome da destra).

3. Il forwarder interroga il **gtld-server** (server TLD per `.net`) con una **query di tipo A**. Il server TLD risponde indicando il name server autorevole di secondo livello a cui rivolgersi (es. `@ns.ripe.net`).

4. Il forwarder interroga il **ripe-server** (name server autorevole per `ripe.net`) con un'altra query di tipo A, e riceve finalmente l'indirizzo IP cercato.

5. La traduzione ricevuta viene **aggiunta alla cache** del DNS di rete locale e la risposta viene inviata al resolver.

> 💡 Questo gioco avrebbe potuto avere molte più tappe se il nome fosse stato più lungo. Ad esempio, con `www.cor.edu` la presenza di più parti avrebbe allungato la catena: ci sarebbero stati più passaggi intermedi tra un DNS di un certo livello e quello del livello inferiore.

Questo meccanismo sfrutta due strumenti chiave:

- **Caching** – per velocizzare le risposte successive;
- **Forwarder** – un server DNS intermedio che può inoltrare richieste ad altri server in modo ricorsivo.

---

### **2. Il resolver**

Il **resolver** è un programma che gira sul client, spesso come componente normale del sistema operativo. Il suo compito è **porre le domande al sistema DNS** per conto delle applicazioni.

Come programmatori, abbiamo accesso a questo sistema anche attraverso una **API** (_Application Programming Interface_): esistono librerie che si possono usare da programma per chiedere al resolver una certa traduzione. Due chiamate tipiche di queste librerie, di cui si parlerà nel Modulo 4:

```c
gethostbyname(char *name);
gethostbyaddr(char *addr, int len, int type);
```

Queste funzioni permettono rispettivamente di ottenere l'indirizzo IP a partire da un nome, o il nome a partire da un indirizzo.

---

### **3. Resource Record (RR)**

Il DNS ha un **formato di record** più che un schema, e questo formato è composto da vari **tipi di record**: è come se ci fossero tante tabelle distinte, ognuna con il proprio schema, anche se poi vengono mantenute tutte insieme.

Il tipo fondamentale di record è il **Resource Record (RR)**, che contiene il nome, dei metadati aggiuntivi e l'indirizzo corrispondente a quel nome.

![](imgs/Pasted%20image%2020260622211430.png)

**Esempio:**

```
www.ripe.net.   3600   IN   A   10.10.10.2
```

![](imgs/Pasted%20image%2020260622211502.png)

Le colonne del database dei Resource Record sono:

| Campo                  | Significato                                                                                      |
| :--------------------- | :----------------------------------------------------------------------------------------------- |
| **Etichetta**          | Il nome del dominio o host                                                                       |
| **TTL (Time To Live)** | Parametro di timing: quanto tempo vale questa traduzione (se ne parla in sezione 8)              |
| **Classe**             | Identifica il tipo di rete (la più comune è `IN` per Internet)                                   |
| **Tipo**               | La natura dell'informazione: `A` per una macchina finale, `NS` per un rimando a un name server  |
| **RDATA**              | Il dato vero e proprio: l'indirizzo IP della risorsa, oppure il nome del name server da contattare |

> 📌 La **classe** è necessaria perché una traduzione può essere di tipo diverso: può essere la traduzione finale verso una macchina (indirizzo IP) oppure un forwarding, cioè l'indicazione di un name server a cui rivolgersi per ottenere la traduzione vera.

---

### **4. Esempio di file di zona**

Un **file di zona** contiene tutti i Resource Record di una determinata zona DNS.

![](imgs/Pasted%20image%2020260622211523.png)

Esempio del file di zona di `ripe.net`:

```
ripe.net. 7200 IN SOA ns.ripe.net. olaf.ripe.net. (
    2001061501 ; Serial
    43200      ; Refresh (12 ore)
    14400      ; Retry (4 ore)
    345600     ; Expire (4 giorni)
    7200       ; Negative cache (2 ore)
)

ripe.net.        7200 IN NS ns.ripe.net.
ripe.net.        7200 IN NS ns.eu.net.
pinkie.ripe.net. 3600 IN A  193.0.1.162
host25.ripe.net. 2600 IN A  193.0.3.25
```

Il file descrive:

- I dati generali della zona, compreso il **serial number**: ogni file di zona può evolvere con velocità diverse rispetto agli altri, quindi occorre un numero seriale gestito localmente a livello di zona per tenere traccia di quale versione si sta trattando (e coordinare gli aggiornamenti tra master e slave).
- I dati relativi alle **scadenze dei record**.
- I record di tipo **NS** (name server).
- I record di tipo **A** (macchine con i loro indirizzi IP).

---

### **5. Resource Record di tipo NS**

I **record NS (Name Server)** sono un tipo diverso dai record di tipo A. Mentre i record **A** rappresentano le risorse di rete nel senso più intuitivo (traduzione nome → indirizzo IP), i record **NS** rappresentano una **traduzione di un nome in un altro nome**: indicano qual è il name server responsabile per una certa zona.

Esempio:

```
ripe.net. 7200 IN NS ns.ripe.net.
ripe.net. 7200 IN NS ns.eu.net.
```

Ciò significa che `ns.ripe.net` e `ns.eu.net` sono i server autorevoli per la zona `ripe.net` (presumibilmente il master e uno slave).

Ogni zona conterrà un record NS che dice che quel name server stesso è responsabile di quella zona. Per le sottozone potrà indicare degli altri server responsabili. Il record NS può indicare anche degli slave o altri name server che sono responsabili per la stessa zona.

---

### **6. Resource Record di tipo SOA**

Siccome molti server di zona hanno ricevuto la propria autorità da un server di livello superiore, ogni file di zona contiene sempre anche un record **SOA (Start of Authority)**.

Il record SOA fornisce informazioni sull'**origine dell'autorità** per quella zona (detta anche APEX). Indica:

- il **server master** che gestisce la zona;
- un **indirizzo di contatto** (indirizzo email dell'amministratore);
- una serie di **parametri di timing** relativi alle scadenze e alla frequenza con cui il file di zona viene aggiornato, in modo da mantenere sincronizzazione tra gli aggiornamenti locali del master e quelli degli eventuali slave.

![](imgs/Pasted%20image%2020260622211707.png)

Esempio:

```
net. 3600 IN SOA a.gtld-servers.net. nstld.verisign-grs.com. (
    2002021301 ; Serial (numero di versione)
    300M       ; Refresh
    15M        ; Retry
    1W         ; Expire
    1D         ; Negative answer TTL
)
```

Spiegazione dei parametri:

- **Serial** → numero di versione del file di zona (incrementato a ogni modifica);
- **Refresh** → intervallo tra due sincronizzazioni master–slave;
- **Retry** → tempo d'attesa prima di ritentare una sincronizzazione fallita;
- **Expire** → tempo massimo oltre il quale la copia slave non è più valida;
- **Negative answer TTL** → durata delle risposte negative (es. "nome inesistente").

---

### **7. Distinzione tra TTL dei record e timer SOA**

Esistono due tipi distinti di parametri temporali nel DNS, con scopi completamente diversi:

- **TTL** nei singoli record RR → serve per il **caching**: indica agli eventuali server forwarder (quelli di rete locale) quanto tempo devono mantenere quella voce in cache, dopo di che la entry potrebbe diventare non valida e va richiesta nuovamente al server autorevole.

- **Timer nel record SOA** → servono per la **sincronizzazione master–slave**: indicano ogni quanto il server di zona viene aggiornato localmente, in modo da mantenere una certa sincronizzazione tra gli aggiornamenti del master e quelli degli slave che ne mantengono una copia.

> ⚠️ I timer SOA non riguardano il caching dei client: sono parametri operativi per l'infrastruttura dei server autorevoli.

---

### **8. Il significato del TTL**

Il **TTL (Time To Live)** è il timer fondamentale per la **gestione delle cache DNS**:

- indica per **quanto tempo** una risposta può essere riutilizzata dai forwarder;
- i record più **stabili** (come i domini principali) possono avere TTL **più lunghi**;
- quelli soggetti a cambiamenti frequenti (come indirizzi dinamici) hanno TTL **più brevi**.

I valori TTL hanno dei default quando si installano i sistemi operativi sui server, ma un buon amministratore andrà a calibrarli in base alle proprie esigenze.

---

### **9. Propagazione dei dati**

Il DNS rappresenta il mapping domini→indirizzi per il 99% delle macchine di Internet. Questo rende la **stabilità dell'indirizzo IP** di un server importante anche in presenza di un nome: se c'è il nome si potrebbe pensare di poter cambiare l'IP a volontà, ma non è così.

![](imgs/Pasted%20image%2020260622211847.png)

Quando si aggiorna una zona DNS (ad esempio cambiando un indirizzo IP), le modifiche **non si propagano istantaneamente**. Ecco cosa succede:

1. Localmente viene aggiornato il **file di zona del master**.
2. Nel frattempo, gli **slave** continuano a rispondere alle interrogazioni con i dati vecchi, fino al prossimo aggiornamento (regolato dal timer globale del SOA).
3. I **caching forwarder DNS** presenti sulle varie reti locali di Internet continuano a usare i record in cache finché non scade il loro TTL, senza rivolgersi allo slave.
4. Alcune versioni di sistemi operativi (es. Windows) hanno dei **resolver locali** che cachano i record e li utilizzano in modo quasi indefinito: in questi casi occorre **forzare lo svuotamento delle cache sui client**.

> ⚠️ Può capitare che per un po' di tempo, dopo aver cambiato l'IP associato a un nome, si verifichi una **macchia d'olio** di malfunzionamenti qua e là nella traduzione nome→indirizzo, sparsi tra i vari client e resolver di Internet.

---

### **10. DNS e prestazioni**

Il DNS è il punto d'accesso alla nostra rete: il 99% delle macchine lo userà per ottenere l'indirizzo IP dei nostri server. Per questo le prestazioni e la sicurezza sono aspetti critici.

**Prestazioni:**

- **Server multipli** (master + slave + eventualmente master ridondanti) per gestire il carico e ridurre i rischi in caso di guasti.
- **Cache** di dimensioni adeguate nei forwarder, per ridurre il carico di lavoro ai server di zona.
- **Timer TTL e SOA calibrati** dall'amministratore (i valori di default del sistema operativo non sono sempre ottimali):
    - valori **alti** per domini stabili;
    - valori **bassi** per domini dinamici.

**Aspetti di sicurezza:**

- Il mapping DNS non deve necessariamente rispecchiare la realtà interna della rete:
    - **Un nome → più IP** (_load balancing_): quando arrivano richieste per lo stesso nome, si possono smistare su macchine con IP differenti. Vedremo quando e come questo è possibile, perché non sempre lo è.
    - **Più nomi → un unico IP**: ogni richiesta per vari nomi viene mappata sullo stesso indirizzo.

> 📌 Buona pratica di sicurezza: la distribuzione dei nomi visibile su Internet **non dovrebbe mai corrispondere all'effettiva struttura delle macchine** all'interno della rete. In questo modo nessuno potrà usare il DNS per capire come è organizzato l'ambiente interno.

> ✅ La risoluzione dei nomi è il cuore operativo del DNS: trasforma nomi simbolici in indirizzi IP reali grazie a un processo distribuito basato su resolver, caching e server autorevoli, bilanciando efficienza, affidabilità e aggiornabilità.
