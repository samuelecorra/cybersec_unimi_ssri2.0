# **M3 UD1 Lezione 3 - Condivisione dei file e protezione (parte 2)**

---

### **1. Introduzione**

Questa seconda parte approfondisce due aspetti fondamentali della condivisione dei file:

- **coerenza** delle informazioni;
- **protezione** dagli accessi impropri.

Quando un file system è distribuito su macchine diverse e più processi possono accedere allo stesso file, il sistema operativo deve garantire che i dati letti abbiano significato compiuto e che solo utenti autorizzati possano eseguire certe operazioni.

> 📌 La condivisione richiede due garanzie: i dati devono restare coerenti e gli accessi devono rispettare i diritti concessi agli utenti.

---

### **2. Coerenza dei file condivisi**

La **coerenza** definisce le modalità di accesso e aggiornamento di un file condiviso, in modo che le informazioni osservate dai processi siano consistenti.

Un elemento letto da un processo non deve essere parzialmente modificato da un altro processo, perché in quel caso potrebbe non avere significato e portare a errori di elaborazione.

#### **2.1. Obiettivo**

L'obiettivo è stabilire quando le modifiche effettuate da un processo diventano visibili agli altri processi.

Le politiche possibili sono diverse:

- modifiche immediatamente visibili;
- modifiche visibili alla chiusura del file;
- modifiche visibili solo nelle sessioni successive;
- file condivisi immutabili.

---

### **3. Modalità di aggiornamento**

#### **3.1. Modifiche immediatamente visibili**

In questa politica, ogni modifica apportata a un file condiviso diventa subito visibile agli altri processi che accedono al file.

È una semantica forte, ma richiede sincronizzazione accurata per evitare letture di stati intermedi.

#### **3.2. Modifiche visibili alla chiusura**

In questa politica, le modifiche diventano visibili quando il processo che sta scrivendo chiude il file.

Finché il file resta aperto in scrittura, gli altri processi possono continuare a vedere una versione precedente o non aggiornata.

#### **3.3. Modifiche visibili solo agli accessi successivi**

Una variante più conservativa rende visibili le modifiche solo ai processi che aprono il file dopo la chiusura da parte dello scrittore.

I processi che stavano già accedendo al file continuano a vedere la vecchia versione.

#### **3.4. File immutabili**

All'estremo opposto, i file condivisi possono essere definiti **immutabili**.

In questo caso, durante l'uso condiviso il file non può essere modificato.

$$
\text{visibilità delle modifiche} =
\begin{cases}
\text{immediata} & \text{massima propagazione, più sincronizzazione} \\
\text{alla chiusura} & \text{aggiornamento visibile a fine uso} \\
\text{agli accessi successivi} & \text{stabilità per chi ha già aperto il file} \\
\text{immutabilità} & \text{nessuna modifica ammessa}
\end{cases}
$$

> ⚠️ La scelta della semantica di coerenza influenza direttamente ciò che i processi possono osservare durante accessi concorrenti.

---

### **4. Coerenza in caso di guasti**

Il problema della coerenza non riguarda solo il funzionamento normale, ma anche i guasti.

In un ambiente di rete possono verificarsi:

- guasti del client;
- guasti del server;
- guasti della rete;
- disconnessioni tra client e server.

#### **4.1. Esempio di incoerenza**

Supponiamo che il meccanismo di scrittura preveda:

1. copia del file dal server al client;
2. modifica locale sul client;
3. reinvio del file modificato al server.

Se durante questo processo si guasta il server o la rete, può crearsi una situazione incoerente: il client possiede una versione modificata, mentre il server conserva una versione diversa.

> ⚠️ Nei sistemi distribuiti, un guasto durante una modifica può lasciare copie divergenti dello stesso file.

#### **4.2. Gestione dei guasti**

Il sistema deve:

- rilevare le situazioni di guasto;
- garantire l'uso in mutua esclusione quando necessario;
- ripristinare uno stato coerente;
- permettere eventuale sopravvivenza con capacità operative limitate;
- fornire tolleranza ai guasti tramite ridondanza, quando richiesta.

La tolleranza completa può essere ottenuta duplicando le informazioni su più macchine, in modo che la presenza del guasto sia trasparente e le operazioni possano continuare.

---

### **5. Stato del file system**

Per gestire la coerenza in caso di guasti, è fondamentale introdurre il concetto di **stato del file system**.

Lo stato del file system raccoglie le informazioni necessarie per capire se il sistema si trova in una configurazione corretta e coerente.

Queste informazioni permettono di:

- riconoscere aggiornamenti incompleti;
- rilevare inconsistenze;
- ripristinare lo stato corretto dopo un guasto;
- gestire errori derivanti dall'uso distribuito dei file.

> 📌 Lo stato del file system è la base per decidere se le informazioni memorizzate sono coerenti e recuperabili dopo un guasto.

---

### **6. Protezione dei file**

Il secondo problema fondamentale della condivisione è la **protezione**.

Proteggere un file significa impedire accessi impropri da parte di processi o utenti che non hanno diritto a vedere, modificare o usare certe informazioni.

L'obiettivo è garantire la consistenza delle informazioni rispetto agli accessi, evitando che un processo acceda a dati che non gli devono essere visibili o modificabili.

#### **6.1. Protezione fisica**

Una prima tecnica è impedire fisicamente l'accesso alla macchina su cui si trova il file.

Questa soluzione può avere senso in sistemi isolati, ma è difficilmente applicabile in sistemi connessi in rete.

#### **6.2. Permessi sulle risorse**

Nei sistemi moderni è più opportuno definire permessi di uso sulle risorse informative.

Per ogni file si stabilisce quali operazioni siano lecite per:

- un utente;
- un gruppo;
- un processo;
- classi di utenti.

---

### **7. Autenticazione e autorizzazione**

La protezione richiede due passaggi.

#### **7.1. Autenticazione**

Il sistema identifica l'utente, verificando che corrisponda a uno degli utenti ammessi.

#### **7.2. Assegnazione dei diritti**

Dopo l'autenticazione, all'utente viene associato un insieme di diritti predefiniti.

I processi dell'utente ereditano tali diritti, eventualmente ridotti dal sistema operativo o dal gestore secondo politiche specifiche.

Quando un processo prova ad accedere a un file, il sistema verifica:

- identità dell'utente;
- identità o contesto del processo;
- autorizzazioni concesse;
- operazione richiesta.

> ✅ Il controllo degli accessi dipende dall'identità autenticata e dai diritti autorizzati in precedenza dal gestore del sistema.

---

### **8. Access Control List**

Una tecnica molto usata è la **Access Control List** (**ACL**).

Per ogni risorsa del sistema, quindi per ogni file, viene definita una lista degli utenti che possono usare quella risorsa.

Per ciascun utente vengono indicate le operazioni consentite.

#### **8.1. Gruppi**

Nella lista possono comparire anche gruppi di utenti, ai quali vengono concessi globalmente determinati permessi.

#### **8.2. Vantaggi e limiti**

Le ACL sono molto flessibili, perché permettono di definire permessi specifici per molti utenti e gruppi.

Il limite è che la lista può diventare lunga e difficile da gestire, con un costo amministrativo elevato.

> ⚠️ Le ACL sono flessibili, ma possono diventare pesanti da mantenere quando utenti, gruppi e file sono numerosi.

---

### **9. ACL compatta: proprietario, gruppo, universo**

Spesso viene usata una forma compatta di ACL.

In questa versione si definiscono solo i diritti per:

- **proprietario** del file;
- **gruppo** a cui appartiene il proprietario;
- **universo**, cioè tutti gli altri utenti del sistema.

I processi del proprietario, i processi degli utenti appartenenti al gruppo e i processi degli altri utenti sono soggetti ai vincoli associati a queste tre categorie.

> 💡 Il modello proprietario-gruppo-universo sacrifica parte della flessibilità delle ACL complete, ma rende la gestione molto più semplice.

---

### **10. Capability List**

Una soluzione opposta alle ACL è la **capability list**.

Invece di associare a ogni file la lista degli utenti autorizzati, si associa a ogni utente o processo l'elenco delle risorse a cui può accedere.

Per ciascuna risorsa vengono indicate anche le operazioni consentite.

$$
\text{ACL}: \text{file} \rightarrow \{(\text{utente}, \text{permessi})\}
$$

$$
\text{Capability}: \text{utente/processo} \rightarrow \{(\text{file}, \text{permessi})\}
$$

> 📌 ACL e capability list rappresentano due viste duali del controllo accessi: una centrata sulla risorsa, l'altra sul soggetto.

---

### **11. Protezione tramite password**

Una terza tecnica consiste nell'associare a ciascun file o risorsa una password.

L'accesso viene consentito solo se il processo è in grado di fornire la password corretta.

Questa tecnica è semplice, ma può essere meno gestibile in sistemi complessi, soprattutto quando molte risorse devono avere password diverse o quando serve revocare selettivamente i diritti.

---

### **12. Sintesi finale**

> ✅ La condivisione dei file produce due problemi centrali: garantire la coerenza delle informazioni e proteggere i dati da accessi indebiti.

In questa lezione sono stati analizzati:

- semantiche di coerenza dei file condivisi;
- visibilità immediata, alla chiusura, agli accessi successivi o immutabilità;
- problemi di coerenza in caso di guasti di client, server o rete;
- stato del file system come supporto al ripristino;
- protezione dei file;
- autenticazione e autorizzazione;
- access control list;
- modello compatto proprietario-gruppo-universo;
- capability list;
- protezione tramite password.

Questi meccanismi permettono di usare file condivisi mantenendo dati consistenti e accessi controllati.
