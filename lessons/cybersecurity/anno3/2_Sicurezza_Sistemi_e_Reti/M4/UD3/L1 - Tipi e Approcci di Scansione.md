# **UD3 - Network e Port scanning**

Questa unità introduce i principi e le pratiche per mappare una rete e identificare servizi attivi su host remoti: vedremo gli **approcci** (attivo vs passivo), le **tecniche** fondamentali di scansione (es. TCP SYN/Connect, UDP, ACK/FIN/Xmas, stealth/fragmentation), i **tool** più usati e le **contromisure** pratiche.

L'obiettivo non è solo imparare come funziona lo scanning, ma comprenderne i segnali nel traffico, i limiti pratici e come trasformare quella conoscenza in difesa: rilevazione precoce, hardening, segmentazione e mitigazione.

---

## **Lezione 1: Tipi e Approcci di Scansione**

### **1. Introduzione generale**

Il **network scanning** è l'attività con cui si raccolgono informazioni su una rete, sui suoi host e sui servizi esposti. È una fase di **ricognizione**: prima di attaccare una macchina o una rete, un attaccante deve capire che cosa esiste, che cosa è raggiungibile e quali punti deboli possono essere sfruttati.

La scansione può rivelare, per esempio:

- quali host sono attivi;

- quale sistema operativo utilizzano;

- quali software e servizi sono installati;

- quali porte TCP o UDP sono aperte;

- come sono configurati alcuni servizi;

- se esistono firewall, filtri o altri sistemi di protezione.

Attraverso queste informazioni si costruisce una mappa della **superficie di attacco**. Dal punto di vista offensivo, tale mappa serve a scegliere gli exploit più promettenti; dal punto di vista difensivo, serve a scoprire in anticipo esposizioni non volute.

> 📌 La scansione non è ancora l'attacco finale: è la fase in cui si decide quali attacchi potrebbero funzionare.

---

### **2. Scopi e prospettive**

La stessa tecnica può avere significati molto diversi a seconda di chi la esegue.

![](imgs/Pasted%20image%2020260709005549.png)

- **Attaccante**: usa la scansione per individuare possibilità di intrusione, servizi vulnerabili, host mal configurati e percorsi di accesso alla rete.

- **Amministratore di rete**: usa la scansione a scopo diagnostico, per verificare quali servizi sono esposti e correggere configurazioni pericolose prima che vengano sfruttate.

- **Analista di sicurezza o penetration tester**: usa la scansione in un contesto autorizzato per valutare la postura di sicurezza di un sistema.

> ⚠️ La scansione di sistemi senza autorizzazione può avere conseguenze legali: in molti ordinamenti viene trattata come attività preparatoria o indizio di attacco. Va eseguita solo su reti proprie o con consenso esplicito.

---

### **3. Obiettivi tecnici della scansione**

Una scansione può avere granularità diverse. Gli obiettivi più comuni sono:

- **individuare servizi TCP e UDP disponibili**, cioè capire quali porte sono in ascolto su un host;

- stabilire se un servizio ammette **login anonimo** o autenticazioni deboli;

- capire quali utenti o classi di utenti sono abilitati a usare un servizio;

- riconoscere i **meccanismi di autenticazione** adottati;

- identificare eventuali **sistemi di filtraggio**, firewall o ACL che bloccano parte del traffico;

- determinare il sistema operativo tramite **OS fingerprinting**, osservando le risposte IP/TCP;

- ricavare informazioni sulla configurazione di server web, DNS, posta o altri servizi pubblici;

- individuare servizi obsoleti o vulnerabili.

Nel caso del **port scanning**, l'attenzione è concentrata sulle porte di un singolo host o di molti host: una porta aperta indica che un processo sta ricevendo pacchetti su quella porta e quindi può diventare un punto di ingresso.

---

### **4. Scenario tipico di scansione**

Una rete reale espone spesso alcuni servizi verso l'esterno: server web, server DNS, server di posta, VPN o altri servizi aziendali. Il traffico in ingresso è normalmente filtrato da un firewall, che dovrebbe distinguere il traffico legittimo da quello indesiderato.

Dal punto di vista dell'attaccante, l'obiettivo è capire:

- quali server sono effettivamente raggiungibili dall'esterno;

- quali porte attraversano il firewall;

- quali servizi rispondono;

- quali versioni software sono in uso;

- se esistono reti interne, reti wireless o zone separate.

Nelle configurazioni più semplici può esserci una sola sottorete con alcuni server esposti. Nelle reti aziendali più realistiche si trovano invece zone diverse: una **DMZ** con i server pubblici, una rete interna per gli host dei dipendenti e talvolta una rete wireless separata.

![](imgs/Pasted%20image%2020260709005621.png)

> 📌 Scansionare una rete significa anche inferirne la topologia: non solo "quali porte sono aperte", ma anche "quali zone esistono e come sono filtrate".

---

### **5. Rilevabilità e conseguenze operative**

La scansione lascia spesso tracce. Per esempio, una sequenza di pacchetti diretti a molte porte dello stesso host o alla stessa porta di molti host è facilmente riconoscibile da sistemi di logging, firewall, IDS/IPS e strumenti di monitoraggio.

Un amministratore può:

- rilevare l'IP sorgente della scansione;

- inserirlo in blacklist;

- generare alert;

- correlare i pacchetti con pattern noti di scanning;

- applicare rate limiting o blocchi temporanei.

Per questo gli attaccanti cercano spesso di rendere la scansione più lenta, distribuita, indiretta o meno evidente.

---

### **6. Classificazioni principali**

Le scansioni possono essere classificate secondo più dimensioni:

- **natura**: attiva o passiva;

- **direzione**: verticale, orizzontale o ibrida;

- **sorgente**: single-source o distribuita;

- **ampiezza del target**: wide-range o target-specific;

- **posizione dello scanner**: esterno, interno o sulla stessa rete locale della vittima.

![](imgs/Pasted%20image%2020260709005656.png)

Queste classificazioni non sono alternative: una stessa scansione può essere, per esempio, attiva, orizzontale, distribuita e low-and-slow.

![](imgs/Pasted%20image%2020260709005733.png)

---

### **7. Scansione attiva e passiva**

#### **7.1. Scansione attiva**

Nella **scansione attiva** lo scanner invia pacchetti di probe verso il target e osserva le risposte. I probe possono essere:

- generici, per esempio ICMP o pacchetti usati per verificare se un host è raggiungibile;

- specifici di un protocollo, per esempio segmenti TCP usati per testare una porta;

- applicativi, per esempio richieste HTTP, DNS o SMTP inviate a un servizio noto.

Il vantaggio principale è che la scansione attiva può produrre rapidamente un quadro completo delle porte aperte e dei servizi raggiungibili. Lo svantaggio è che è intrusiva e spesso rilevabile: genera traffico artificiale, log e pattern riconoscibili.

#### **7.2. Scansione passiva**

Nella **scansione passiva** lo scanner non invia pacchetti verso la vittima, ma osserva il traffico esistente. Può farlo con strumenti software o hardware, per esempio tramite sniffing, mirroring di porta o cattura su un segmento di rete.

Osservando il traffico, è possibile dedurre:

- quali host comunicano;

- quali servizi sono usati;

- quali porte compaiono nelle conversazioni;

- quale sistema operativo o applicazione potrebbe essere in uso, in base ai pattern dei pacchetti;

- se una connessione TCP viene stabilita con successo.

Il vantaggio è la bassa visibilità: lo scanner non genera traffico proprio. Il limite è che vede solo ciò che passa nel punto di osservazione e solo gli host che stanno effettivamente comunicando.

|Approccio|Vantaggi|Svantaggi|
|---|---|---|
|**Attivo**|Rapido; può testare molte porte; produce un quadro più completo dei servizi raggiungibili|Intrusivo; rilevabile da IDS/IPS; funziona solo su host raggiungibili in quel momento|
|**Passivo**|Non intrusivo; difficilmente rilevabile; può osservare host temporanei mentre comunicano|Dipende dal traffico reale; non vede host silenziosi; richiede un punto di osservazione adeguato|

---

### **8. Direzione della scansione**

#### **8.1. Vertical scan**

Una **vertical scan** esplora molte porte su un singolo host. Serve quando l'attaccante o l'analista vuole capire in profondità quali servizi sono esposti da una macchina specifica.

Esempio: testare le porte di un server per scoprire se espone HTTP, SSH, SMTP, database o servizi amministrativi.

#### **8.2. Horizontal scan**

Una **horizontal scan** esplora una stessa porta su molti host. Serve per trovare tutte le macchine che espongono uno specifico servizio.

Esempio: cercare tutti gli host di una sottorete che hanno aperta la porta 22/SSH o la porta 80/HTTP.

#### **8.3. Scansione ibrida**

La scansione **ibrida** alterna i due approcci: può testare un insieme di porte su un insieme di host, senza coprire necessariamente tutte le combinazioni.

---

### **9. Dimensione e ritmo della scansione**

#### **9.1. Wide-range scanning**

Il **wide-range scanning** copre rapidamente un ampio blocco di indirizzi IP. È tipico di strumenti automatici, worm o routine di preparazione all'infezione, che cercano host vulnerabili su larga scala.

In questo caso l'obiettivo non è studiare a fondo una singola macchina, ma trovare molti bersagli potenziali con il minimo tempo possibile.

#### **9.2. Target-specific scanning**

La scansione **target-specific** è diretta verso un bersaglio preciso: un host, una rete aziendale, una DMZ o un servizio di particolare valore. Può essere più accurata, meno rumorosa e più adattata al contesto.

Può assumere forme diverse:

- **stealth**, se cerca di essere poco visibile;

- **indiretta**, se nasconde l'origine tramite proxy, host intermedi o macchine compromesse;

- **distribuita**, se usa più sorgenti;

- **low and slow**, se invia sonde molto distanziate nel tempo per evitare soglie di rilevamento.

---

### **10. Sorgente e target della scansione**

#### **10.1. Single-source e distributed scanning**

Nella scansione **single-source**, un solo host genera i pacchetti di probe. È semplice da eseguire, ma lascia una traccia chiara: molti pacchetti provengono dallo stesso indirizzo.

Nella scansione **distribuita**, molti host cooperano per scandire uno o più target. Questo riduce la visibilità della singola sorgente: ogni scanner invia pochi pacchetti, rendendo più difficile correlare l'attività complessiva.

#### **10.2. One-to-many, many-to-one, many-to-many**

Il transcript distingue anche tre forme rispetto al rapporto tra sorgenti e vittime:

- **one-to-many**: un solo host scandisce molti target;

- **many-to-one**: molti host scandiscono un solo target;

- **many-to-many**: molti host scandiscono molti target.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nel caso many-to-one e many-to-many, la scansione può sembrare meno evidente se osservata localmente, perché ciascuna sorgente genera solo una parte del traffico complessivo.

#### **10.3. Posizione dello scanner**

La posizione dello scanner cambia ciò che può osservare:

- **esterno verso interno**: tipico scenario dell'attaccante su Internet contro una rete aziendale;

- **locale verso esterno**: utile per capire quali servizi remoti sono raggiungibili dalla rete interna;

- **locale verso locale**: scansione all'interno della stessa LAN, spesso più informativa perché attraversa meno filtri;

- **esterno verso esterno**: scansioni tra host pubblici o infrastrutture distribuite.

---

### **11. Metodi operativi**

I metodi principali combinano direzione e sorgente:

- **Vertical scan**: molte porte su un host;

- **Horizontal scan**: una porta su molti host;

- **Strobe scan**: un insieme selezionato di porte, spesso associate a servizi noti, su molti host;

- **Block scan**: molte porte su molti host o su un blocco di indirizzi;

- **Distributed scan**: scansione coordinata da più sorgenti.

La scelta dipende dall'obiettivo: trovare un servizio specifico, mappare un server in profondità, costruire una botnet, verificare una DMZ o passare inosservati.

---

### **12. Sintesi finale**

|Categoria|Descrizione|Esempio|
|---|---|---|
|**Active scan**|Invia probe e osserva risposte|Test TCP/UDP su porte note|
|**Passive scan**|Osserva traffico esistente|Sniffing o mirroring di porta|
|**Vertical scan**|Molte porte su un host|Mappare tutti i servizi di un server|
|**Horizontal scan**|Una porta su molti host|Cercare host con SSH aperto|
|**Wide-range scan**|Ampio blocco IP, ritmo rapido|Worm o auto-rooter|
|**Target-specific scan**|Bersaglio preciso e più analisi|Ricognizione su una DMZ aziendale|
|**Low and slow**|Sonde rarefatte nel tempo|Evasione di soglie IDS|
|**Distributed scan**|Più sorgenti coordinate|Botnet scanning|

---

### **13. Conclusione**

La scansione è ambigua perché è utile sia all'attaccante sia al difensore. Per l'attaccante è il modo per passare da un bersaglio generico a un piano operativo; per l'amministratore è il modo per scoprire e correggere esposizioni prima che vengano sfruttate.

> ✅ Punto d'esame: classificare correttamente una scansione significa guardare almeno quattro aspetti: se genera traffico o osserva passivamente, se va in profondità su un host o in ampiezza su molti host, se parte da una o più sorgenti, e se mira a un bersaglio specifico o a un blocco ampio di indirizzi.
