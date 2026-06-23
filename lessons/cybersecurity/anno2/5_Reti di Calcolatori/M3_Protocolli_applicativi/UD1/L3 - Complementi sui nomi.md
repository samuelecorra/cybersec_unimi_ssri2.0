# **Lezione 3: Complementi sui nomi**

---

### **1. Il namespace DNS**

Il **namespace** del DNS deve essere **gerarchico**, cioè organizzato in più livelli strutturati come un **albero**.

Il database gestito dal DNS segue uno schema di denominazione gerarchico: le risorse di rete hanno un nome diviso in parti che vanno dal suffisso più a destra al prefisso più a sinistra, in ordine di generalità.

La struttura segue questi livelli, da destra a sinistra:

1. **Root** – il punto finale (concettualmente sempre presente, anche se non digitato). Rappresenta la radice del namespace, gestita dai root server.

2. **Top Level Domain (TLD)** – il suffisso principale, ad esempio `it` per l'Italia o `com` per le organizzazioni commerciali. Identifica la nazione, il gruppo di organizzazioni o il gruppo di società.

3. **Location** – identifica un'entità specifica dentro il TLD, ad esempio `unimi` nell'indirizzo `www.dt.unimi.it` identifica l'Università degli Studi di Milano.

4. **Unità all'interno della location** – identifica una struttura interna all'organizzazione. Ad esempio `dti` nell'indirizzo `www.dti.unimi.it` identifica il Dipartimento di Tecnologia dell'Informazione.

5. **Risorsa** – il prefisso più a sinistra, che rappresenta effettivamente la risorsa di rete di cui si vuole conoscere l'indirizzo. Ad esempio `www` in `www.dti.unimi.it`.

> 📌 L'ordine di generalità va da destra (più generale: root, TLD) verso sinistra (più specifico: risorsa).

---

### **2. Fully Qualified Domain Name (FQDN)**

Il **Fully Qualified Domain Name (FQDN)** è il **nome di dominio pienamente qualificato**, ovvero l'intera sequenza delle parti del nome gerarchico, terminata da un punto finale che rappresenta la radice.

> 💡 Il punto finale nella pratica non lo si scrive, ma concettualmente va immaginato presente sulla parte destra del nome qualificato.

**Esempio:**

```
www.dti.unimi.it.
```

Da un punto di vista tecnico l'FQDN è una **sequenza di label (etichette) separate da punti**, che comprende tutte le porzioni del nome gerarchico.

Il DNS prende un FQDN comunicatogli da un resolver e, tramite opportuni scambi di messaggi, riporta l'indirizzo corrispondente. L'FQDN è quindi anche la **chiave con cui si accede al database distribuito** del DNS.

---

### **3. Struttura ad albero dei domini**

I nomi di dominio si mappano molto bene su un **albero**: il punto finale (la radice) rappresenta la radice comune, poi ci sono i TLD, poi i domini di secondo livello che rappresentano grandi organizzazioni, poi il terzo livello che rappresenta parti di grandi organizzazioni, e così via fino al nome della risorsa specifica.

![](imgs/Pasted%20image%2020260622204330.png)

> ⚠️ L'espansione del primo livello (TLD) richiede l'**attivazione di nuovi root server** e quindi richiede **decisioni collettive**: non può essere delegata. Ultimamente ne sono stati attivati diversi di nuovi, come `.ed` e altri.

Al di sotto del primo livello, invece, l'attivazione di nuovi rami dell'albero **può essere delegata** alle autorità interne delle organizzazioni che detengono un nome di secondo livello: i nomi di terzo livello possono essere creati da chi detiene il nome di secondo livello, e così via. L'ampliamento del database non richiede il continuo intervento di un'autorità centrale.

---

### **4. Domini e namespace locali**

Un **dominio** è come un **namespace locale**, cioè uno spazio dei nomi completamente autonomo che discende da un certo nodo di livello gerarchico superiore.

![](imgs/Pasted%20image%2020260622204508.png)

Esempi:

- Il **dominio `.com`** è composto da tutti quei domini di secondo livello (e poi di terzo e così via) che hanno come suffisso terminale comune `com`. La nascita di nuovi domini direttamente sotto `.com` dovrà essere gestita da chi gestisce `.com`, ma poi sarà possibile far nascere nuovi domini sotto ad esempio `google`, delegando la cosa a chi gestisce il sottodominio `google`.

- Il **RIPE** (autorità per le reti europea) ha all'interno del dominio `.net` il sottodominio `ripe.net`. Lì all'interno può creare domini di terzo livello oppure direttamente nomi di risorse. Dentro il dominio di terzo livello potrebbero ancora esserci nomi di risorse e così via.

> 📌 I domini si **annidano** uno dentro l'altro, formando un sistema gerarchico di contenitori. Tutto ciò che sta sotto `ripe.net` appartiene sia al dominio `ripe.net` sia al dominio superiore `net`.

---

### **5. Delegazione**

Il **meccanismo di delega** prevede che l'amministrazione di un dominio di alto livello (un TLD, ma anche un sottodominio diretto e così via) può delegare qualcun altro a gestire un dominio di livello inferiore.

Esempio: se si gestisce il dominio `unimi.it` (essendo stati delegati da chi gestisce `it`), si può a propria volta delegare chi gestisce `dt.unimi.it`.

> ⚠️ La delega **non è obbligatoria**: chi gestisce un dominio di livello superiore può benissimo mantenere la gestione diretta dei sottodomini, senza delegare.

Per "gestione" si intende l'aggiunta di nuovi nomi e l'aggiornamento del database dei nomi e indirizzi. Le relazioni di delega sono memorizzate: **a livello padre si ricorda quanti sono i domini figli e a chi è stata affidata la loro gestione**.

---

### **6. Zone e delegazioni**

Per motivi di **semplicità amministrativa**, la nozione di dominio e sottodominio non coincide esattamente con quella di **zona**.

Una **zona** è una parte che può comprendere un dominio oppure un dominio con alcuni dei suoi sottodomini.

![](imgs/Pasted%20image%2020260622204708.png)

Esempio: la zona `ripe.net` comprende alcuni nomi (come `www, FTP`) all'interno di ripe, mentre il sottodominio `disi` con le sue macchine `WS1` e `WS2` fa parte di un'altra zona. I due database che contengono le traduzioni di `www, FTP` e di `WS1`/`WS2` sono quindi diversi, perché **i database sono associati alle zone, non ai domini**.

> 📌 La zona relativa a un dominio è composta dai nomi delle macchine di quel dominio che si vogliono tradurre localmente.

L'autorità viene delegata anche a livello di zona: il **server di zona** che contiene le traduzioni di `www` ed `FTP` è quello originale, sempre aggiornato, che contiene le ultime copie per quelle traduzioni. Queste copie sono dette **copie di autorità** (_authoritative copies_). Il server di zona di `disy` è quello che contiene le ultime copie aggiornate per `WS1` e `WS2`.

---

### **7. Name server**

Esistono diversi tipi di **name server**, ognuno con funzioni specifiche:

1. **Server autorevoli (authoritative)**

    - Contengono fisicamente una parte del database di traduzione nome→indirizzi.
    - Possono essere:
        - **Master (primari)**: l'unico aggiornato, carica i dati da un file di zona locale.
        - **Slave (secondari)**: semplici copie del master, aggiornate periodicamente tramite trasferimento di zona.

2. **Server ricorsivi (forwarder/caching)**

    - Non contengono traduzioni proprie, ma servono per sapere a quali altri server rivolgersi.
    - Eseguono **forwarding** e **caching**: recuperano le informazioni interrogando server autorevoli e le memorizzano in cache per le richieste successive.
    - I **TLD** sono tipicamente di questo tipo, perché non esiste una macchina che si chiami direttamente `pippo.it`: tutte le macchine sono in qualche sottodominio di `pippo`, pertanto `it` deve soltanto fare forwarding, cioè indicare qual è il giusto name server a cui rivolgersi per una certa traduzione.

3. **Server misti**

    - I server di zona possono fungere da forwarder/caching per certe query e avere invece traduzioni autorevoli per altre.

---

### **8. Funzionamento dei server DNS**

Il **server master** è l'unico sempre aggiornato e contiene la copia originale dei record DNS di una zona. Periodicamente gli **slave** ricevono copie dei dati del master.

> ⚠️ Gli slave sono situati in genere **non sulla stessa rete locale del master**: non avrebbe senso, perché se andasse giù il collegamento con il master andrebbe giù anche il collegamento con le copie. Vengono invece distribuiti in **altre parti di Internet** — questa è la **ridondanza esterna**. Può esistere anche una **ridondanza interna** al master stesso (ad esempio ridondanza dei dischi) per evitare che un guasto interno interrompa il servizio.

Un esempio di **server ricorsivo** è il DNS server presente nella rete locale. Nella rete locale può esserci con molta probabilità una macchina che funge da DNS server ricorsivo (anche se non è obbligatorio: nulla vieta di configurare le macchine per usare un DNS server remoto). Il server ricorsivo locale risponde alle domande del resolver andando a sua volta a chiedere ad altri name server.

> 💡 Il server ricorsivo, volendo fare caching, si rivolge al server autorevole solo la prima volta che riceve una certa query. Se la stessa query viene posta più volte (ad esempio cinque volte al minuto), traduce con l'aiuto del name server autorevole solo alla prima richiesta, poi risponde con la copia memorizzata in cache — ma la **contrassegna come non autorevole**.

> ✅ Il modello distribuito di server (master, slave, ricorsivi) rende il DNS **efficiente, ridondante e veloce**, evitando sovraccarichi e garantendo risposte anche in caso di guasti parziali.
