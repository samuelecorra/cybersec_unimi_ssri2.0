# Soluzione Appello — Sicurezza dei Sistemi Web e Mobili — 16/06/2026 (SSRI online)

**Docente: Chiara Braghin** · A.A. 2025/26 · Tempo a disposizione: **1:30 h** · 7 domande

> 📌 Consegna del docente: *"Rispondere brevemente ma in modo completo alle seguenti domande."*
> Con 90 minuti e 7 domande il budget realistico è **~12 minuti a domanda**, cioè 250–350 parole ben strutturate. Ogni soluzione qui sotto è più ampia del necessario: la parte in **grassetto d'apertura** di ciascun paragrafo è il nucleo minimo da scrivere, il resto è approfondimento per il 30 e lode.

Per ogni domanda sono riportati la traccia originale, i riferimenti alle lezioni di teoria e lo svolgimento discorsivo completo.

---

## Traccia originale dell'appello

### **1. SQL Injection**

In che cosa consiste un attacco di tipo SQL injection e quali condizioni devono essere soddisfatte a lato client e a lato server perché sia possibile un tale attacco? Quali sono delle contromisure efficaci?

> **Riferimenti di teoria**:
>
> - [M4/UD3/L2 — SQL injection: funzionamento dell'attacco](../M4/UD3/L2.md) (§3 idea di base, §6 condizioni necessarie, §8 tautologia, §12–15 UNION)
> - [M4/UD3/L3 — SQL injection: contromisure](../M4/UD3/L3.md) (§4 metacaratteri, §6–8 blacklist/whitelist, §10–14 prepared statement e bind variable, §17 buone pratiche)
> - [M4/UD3/L1 — Introduzione a SQL](../M4/UD3/L1.md) (SELECT/FROM/WHERE, DROP, UNION)
> - [M4/UD2/L1 — HTTP: funzionamento e vulnerabilità](../M4/UD2/L1.md) (GET/POST, query string come vettore di input)

**In che cosa consiste.** Una SQL injection è un attacco in cui l'attaccante riesce a **modificare la semantica della query SQL costruita dall'applicazione lato server**, inserendo frammenti di codice SQL dentro campi che il programmatore aveva previsto contenessero solo dati. Il punto critico non è SQL in sé, ma il fatto che l'applicazione costruisca il comando per **concatenazione di stringhe** mescolando in un'unica frase il *codice* (scritto dal programmatore) e i *dati* (forniti dall'utente). Il database riceve una stringa sintatticamente valida e la esegue: non ha alcun modo di sapere quale porzione fosse "intenzione del programmatore" e quale sia stata iniettata. È esattamente la stessa classe di problema dell'XSS — confusione fra dati ed elementi di controllo — ma spostata sul lato server e sul DBMS.

L'esempio canonico è il login. Il codice vulnerabile è:

```php
$username = $_POST["username"];
$password = $_POST["password"];
$query = "SELECT * FROM users
          WHERE username = '$username' AND password = '$password'";
```

Se l'attaccante inserisce nel campo username

```sql
' OR 1=1 --
```

la query diventa

```sql
SELECT * FROM users WHERE username = '' OR 1=1 --' AND password = 'qualunque';
```

L'apice iniziale **chiude anticipatamente la stringa**, `OR 1=1` è una **tautologia** che rende vera la clausola `WHERE` per ogni riga, e `--` apre un **commento SQL** che annulla tutto ciò che segue, compreso il controllo sulla password. Se l'applicazione ragiona come fa quasi sempre — "se la query restituisce almeno una riga, l'utente è autenticato" — l'attaccante entra senza conoscere alcuna credenziale valida.

Le altre due varianti viste a lezione mostrano l'ampiezza dell'impatto. Con il **query stacking** (`'; DROP TABLE users; --`) l'attaccante può distruggere dati, purché DBMS, driver e privilegi dell'account applicativo lo consentano. Con la **UNION-based injection** può cambiare la *sorgente* dei dati mostrati: dato `list_orders?userid=42` che genera `SELECT item, quantity, date, shipping FROM orders WHERE userid = 42`, il payload

```sql
NULL UNION ALL SELECT name, creditNumber, expMonth, expYear FROM creditCards
```

produce una seconda `SELECT` con lo stesso numero di colonne e tipi compatibili, e la pagina — che si limita a stampare il risultato della query — mostra all'attaccante i numeri di carta di credito. Le tre categorie di impatto sono quindi **bypass dell'autenticazione**, **violazione dell'integrità/disponibilità** e **esfiltrazione di dati**, cioè una violazione di tutte e tre le proprietà CIA.

**Condizioni lato client.** Deve esistere un **punto di input controllabile dal client**. Non è necessario che sia un campo di form: qualunque valore che il client può scegliere e che il server userà per costruire una query è un vettore — campi di form, parametri nella *query string* di una `GET`, corpo di una `POST`, cookie, header HTTP, campi `hidden`, dati inviati da JavaScript. È essenziale sottolineare che il client è **interamente sotto il controllo dell'attaccante**: i vincoli `maxlength`, i campi `hidden`, i menu a tendina e la validazione JavaScript non sono condizioni di sicurezza, perché l'attaccante può ignorare del tutto il browser e confezionare la richiesta HTTP a mano (con `curl`, un proxy di intercettazione, o modificando la pagina). L'unica condizione lato client che conta davvero è quindi: *l'attaccante può far arrivare al server una stringa arbitraria in un parametro che il server userà*.

**Condizioni lato server.** Devono valere quattro condizioni congiunte:

1. esiste uno **script server-side dinamico** che legge quei parametri;
2. lo script li **inserisce direttamente in un comando SQL per concatenazione**, senza separare strutturalmente codice e dati;
3. esiste un **database** interrogato dallo script (l'attaccante non deve accedervi direttamente: gli basta influenzare la query che l'applicazione gli invia);
4. **manca il controllo dell'input**: nessuna validazione di forma, nessun vincolo di tipo, nessuna neutralizzazione dei metacaratteri (`'`, `"`, `;`, `--`, `/* */`), nessuna query parametrizzata.

Condizioni accessorie che aumentano la sfruttabilità: l'account con cui l'applicazione si connette al DBMS possiede privilegi eccessivi (`DROP`, accesso a tabelle non pertinenti), i messaggi d'errore SQL vengono rimandati al client rivelando struttura delle tabelle e nomi delle colonne, e il driver consente l'esecuzione di più statement in una sola chiamata.

**Contromisure efficaci.** La difesa deve attaccare la causa radice — *l'input dell'utente diventa parte del comando* — e non i sintomi.

La contromisura **fondamentale** sono i **prepared statement con bind variable tipate**. La query viene dichiarata come **template statico** con segnaposto, e i valori vengono associati in un secondo momento tramite metodi tipati:

```java
String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
PreparedStatement stmt = connection.prepareStatement(sql);
stmt.setString(1, username);
stmt.setString(2, password);
ResultSet rs = stmt.executeQuery();
```

Il DBMS compila la struttura della query *prima* di vedere i valori: da quel momento il contenuto della bind variable non può più cambiare l'albero sintattico. Se `username` vale `' OR 1=1 --`, quella stringa viene confrontata letteralmente con il campo `username` e semplicemente non trova corrispondenze. La tipizzazione (`setInt`, `setDate`, `setBigDecimal`) rafforza la separazione perché impone al parametro un ruolo semantico preciso.

> ⚠️ Errore da segnalare esplicitamente: usare la classe `PreparedStatement` **continuando a concatenare** (`"... WHERE username = '" + username + "'"`) non protegge nulla. Ciò che protegge è il placeholder, non il nome della classe.

Le contromisure complementari sono:

- **Validazione dell'input lato server**, sempre, anche quando esiste quella lato client (che va considerata solo un aiuto all'usabilità). Meglio **whitelist** che **blacklist**: una blacklist di caratteri pericolosi è per costruzione incompleta (si dimenticano codifiche alternative, commenti `/* */`, `%27`) e allo stesso tempo blocca dati leciti (`O'Brien`, `L'Aquila`). Una whitelist descrive con espressioni regolari la forma *ammessa* del campo — ad esempio `^[0-9]{1,4}$` per un identificativo numerico o `^(0?[1-9]|1[0-2])$` per un mese — e rifiuta tutto il resto.
- **Privilegio minimo sul database**: l'utente applicativo deve avere solo i permessi che servono (tipicamente `SELECT`/`INSERT` sulle sole tabelle necessarie, mai `DROP`). Questo non impedisce l'iniezione ma ne limita drasticamente l'impatto.
- **Gestione sicura degli errori**: messaggi generici al client, dettagli SQL solo nei log del server, per non regalare all'attaccante lo schema del database (che, come mostrato a lezione, gli serve per costruire un attacco `UNION`).
- **Centralizzazione dell'accesso ai dati** in un unico strato (DAO/ORM) — così la regola "niente concatenazione" si verifica in un solo punto — e **test di sicurezza** mirati.

> ✅ **Sintesi da scrivere in chiusura:** la SQL injection nasce quando input non fidato diventa codice. Lato client serve solo un parametro controllabile; lato server serve una query costruita per concatenazione senza validazione su un'applicazione che interroga un DB. La difesa corretta è mantenere **separati dati e controllo** con prepared statement e bind variable tipate, rinforzati da validazione whitelist lato server, privilegio minimo ed errori non verbosi.

---

### **2. Proprietà di sicurezza; confidenzialità, privatezza e anonimato**

Spiegare cosa significa garantire la sicurezza in termini di proprietà di sicurezza di un sistema. Nella risposta discutere se i termini confidenzialità, privatezza e anonimato siano sinonimi o meno.

> **Riferimenti di teoria**:
>
> - [M1/UD2/L2 — Proprietà di sicurezza: CIA, autenticazione e non ripudio](../M1_Intro_CyberSec/UD2_CybersecNotions/L2_Cybersec_Definition.md) (§2 CIA, §2.1 confidenzialità/privatezza/anonimato, §3 conflitti, §4 autenticazione e non ripudio, §6 Schneier)
> - [M1/UD2/L1 — Introduzione alla cybersecurity](../M1_Intro_CyberSec/UD2_CybersecNotions/L1_Cybersec_Intro.md) (definizione informale)
> - [M1/UD2/L5 — Progettazione della sicurezza](../M1_Intro_CyberSec/UD2_CybersecNotions/L5_Progettazione_della_Sicurezza.md)
> - [M3/UD1/L1 — Comunicazione sicura su canale insicuro](../M3/UD1/L1.md) (le stesse proprietà nel contesto dei protocolli)

**Che cosa significa "garantire la sicurezza".** La definizione informale di sicurezza informatica — *prevenzione e protezione contro l'accesso, la distruzione e l'alterazione di risorse da parte di utenti non autorizzati* — è troppo vaga per essere operativa. Si formalizza allora dicendo che un sistema è sicuro se soddisfa un insieme di **proprietà di sicurezza** dichiarate esplicitamente. La definizione semiformale di riferimento è la triade **CIA**:

- **Confidenzialità** (*Confidentiality*): le informazioni non sono accessibili a utenti non autorizzati.
- **Integrità** (*Integrity*): le informazioni non sono alterabili da utenti non autorizzati **in maniera invisibile agli utenti autorizzati**. Non si richiede l'impossibilità della modifica, ma la sua **rilevabilità**: se Trudy intercetta e modifica il messaggio di Alice, Bob deve accorgersene. La violazione dell'integrità si chiama anche *falsificazione*.
- **Disponibilità** (*Availability*): informazioni e risorse sono accessibili agli utenti autorizzati quando servono. La violazione tipica è il **Denial of Service**: il sistema resta perfettamente integro e confidenziale, ma inutilizzabile.

A queste si aggiungono due proprietà frequentemente richieste, centrali nella parte del corso sui protocolli:

- **Autenticazione**: i soggetti sono effettivamente chi affermano di essere. Si distingue fra autenticazione **unilaterale** (solo una parte si autentica) e **mutua**, e fra autenticazione **di entità** e **del messaggio**.
- **Non ripudio**: il mittente non può negare di aver spedito un messaggio e il destinatario non può negare di averlo ricevuto. È l'equivalente digitale della firma autografa e si realizza con la firma digitale, non con un MAC.

Talvolta si citano anche **safety** e **reliability**, che però riguardano la correttezza del software rispetto a guasti accidentali più che la protezione da attacchi intenzionali, e non sono oggetto del corso.

Il passaggio metodologico da sottolineare è che **garantire la sicurezza non significa garantire tutte le proprietà**: significa **dichiarare quali proprietà servono in quel contesto, con quale priorità, contro quale modello di attaccante**, e poi scegliere i meccanismi che le realizzano. Le proprietà infatti possono **entrare in conflitto** fra loro: portare la confidenzialità al massimo (nessuno legge nulla) azzera la disponibilità; centralizzare il controllo degli accessi migliora la confidenzialità ma introduce un collo di bottiglia che degrada la disponibilità. Per lo stesso motivo la sicurezza **non è assoluta**: vale il doppio aforisma di Bruce Schneier, *"la sicurezza non è un prodotto, ma un processo"* e *"la sicurezza è una catena e la sua resistenza è determinata dall'anello più debole"* — se Alice cifra tutto con chiavi lunghissime ma sceglie come password `alice`, l'attaccante non attacca la crittografia, attacca l'autenticazione. Infine pesa il **costo**: spesso proteggere un dato costa più del dato stesso, e in ambito commerciale il trade-off va valutato esplicitamente.

**I tre termini non sono sinonimi.** È il punto che la domanda chiede di discutere e va risposto in modo netto.

| Termine | Che cosa protegge | Domanda a cui risponde |
|---------|-------------------|------------------------|
| **Confidenzialità** (segretezza) | Il **contenuto** dell'informazione, verso chiunque non sia autorizzato | *Chi può leggere il dato?* |
| **Privatezza** (privacy) | Il **controllo dell'individuo** su quali dati che lo riguardano vengono raccolti, da chi e per quale scopo | *Chi decide che cosa si raccoglie su di me e come lo si usa?* |
| **Anonimato** | L'**identità** di chi compie un'azione | *Si può risalire all'autore di questa azione?* |

Le differenze concrete:

- La **confidenzialità è più generale**: si applica a qualunque risorsa (un file di progetto, una chiave, un segreto industriale), anche quando non c'è alcuna persona coinvolta. La **privatezza si usa propriamente solo quando l'informazione fa riferimento a individui specifici**, ed è una proprietà di *controllo*, non solo di segretezza: un dato personale può essere perfettamente confidenziale (nessun estraneo lo legge) e tuttavia violare la privacy, perché è stato raccolto senza che l'interessato potesse decidere. Il contesto normativo lo rende evidente: in Europa, con il GDPR, i dati personali sono privati per legge e un supermercato che li raccoglie con la tessera fedeltà non può rivenderli; negli USA la tradizione normativa è opposta e i dati appartengono a chi li ha raccolti. La differenza non è tecnica, è di *chi ha il potere di decidere*.
- L'**anonimato è una proprietà diversa da entrambe**: se la privatezza è il diritto di rilasciare o meno informazioni che mi riguardano, l'anonimato è il diritto di rilasciare o meno la **mia identità**. Si può avere confidenzialità senza anonimato (una conversazione cifrata fra due interlocutori noti: nessuno legge il contenuto, ma tutti sanno chi parla con chi — è il classico problema dei *metadati*) e anonimato senza confidenzialità (un messaggio pubblico e in chiaro, ma non attribuibile). Si distinguono inoltre anonimato **commerciale** (acquisti senza registrazione), **sanitario** (test medici senza nome) e **pseudo-anonimato** (uso di uno pseudonimo), quest'ultimo particolarmente fragile: nella navigazione Internet resta comunque possibile risalire all'indirizzo IP e quindi, spesso, all'identità reale. Esiste infine un dibattito aperto se l'anonimato in rete sia davvero un diritto, e resta comunque una proprietà **tecnicamente difficile da garantire**.

> ✅ **Sintesi da scrivere in chiusura:** garantire la sicurezza significa dichiarare e far rispettare un insieme di proprietà — CIA più autenticazione e non ripudio — sapendo che possono confliggere, che dipendono dal contesto e dal modello di attaccante, e che non esiste sicurezza assoluta. Confidenzialità, privatezza e anonimato **non sono sinonimi**: la prima riguarda l'accesso al contenuto, la seconda il controllo dell'individuo sui propri dati personali, la terza la non attribuibilità di un'azione a un soggetto.

---

### **3. Certificato digitale**

Dire cosa si intende per certificato digitale, a cosa serve, come venga creato e utilizzato.

> **Riferimenti di teoria**:
>
> - [M3/UD2/L6 — Certificati digitali e PKI](../M3/UD2/L6.md) (§1 problema aperto, §2 definizione e X.509, §3–4 CA ed emissione, §5 uso, §7–9 PKI, gerarchie e catena, §10 verifica, §12 revoca, §13 KDC vs PKI)
> - [M3/UD2/L3 — Crittografia simmetrica e asimmetrica](../M3/UD2/L3.md) (chiave pubblica/privata, KDC)
> - [M3/UD2/L5 — Firma digitale](../M3/UD2/L5.md) (§8 associazione chiave pubblica–identità)
> - [M3/UD3/L2 — Protocollo di autenticazione unilaterale](../M3/UD3/L2.md) (§12 attacco Man in the Middle, §13 correzione con certificato)

**Il problema che risolve.** La crittografia a chiave pubblica elimina il bisogno di scambiare in anticipo un segreto condiviso, ma lascia aperta una domanda: **come faccio a sapere che una certa chiave pubblica appartiene davvero a Bob?** La chiave pubblica non deve essere confidenziale, ma deve essere **autentica**. Se Bob riceve una chiave pubblica su un canale insicuro, Trudy può sostituirla con la propria e realizzare un attacco **Man in the Middle**: nel protocollo challenge-response a firma digitale visto a lezione, Trudy firma il nonce con $K_T^-$ e, quando Bob chiede la chiave pubblica di Alice, risponde con $K_T^+$; la verifica riesce, ma ciò che Bob ha verificato è solo *"la firma è coerente con la chiave che ho appena ricevuto"*, non *"quella chiave è di Alice"*. Il certificato digitale è esattamente la soluzione a questo problema.

**Che cos'è.** Un **certificato digitale** è un documento **firmato digitalmente da una terza parte fidata** che attesta l'associazione fra una **chiave pubblica** e un **nome** — di una persona, un computer, un server, un'organizzazione, un dominio. La terza parte è la **Certification Authority (CA)**, che firma il certificato con la propria chiave privata. L'analogia è con carta d'identità, passaporto o patente: un'autorità riconosciuta lega dati anagrafici e fotografia a una persona; qui l'autorità lega **identità e chiave pubblica**.

Il formato standard è **X.509**, che prevede almeno:

| Campo | Contenuto |
|-------|-----------|
| Soggetto | Dati identificativi del titolare della chiave |
| Chiave pubblica | La chiave pubblica del soggetto |
| Periodo di validità | Data di inizio e data di fine |
| Emittente | La CA che ha rilasciato il certificato |
| Informazioni di revoca | Riferimento alla lista dei certificati revocati (CRL) |
| Firma della CA | Firma digitale che protegge integrità del certificato e autentica l'emittente |

**A che cosa serve.** Serve a rendere **verificabile e trasferibile la fiducia** su una chiave pubblica. Grazie al certificato, chi riceve una chiave non deve fidarsi del canale su cui l'ha ricevuta: gli basta fidarsi della CA. Questo abilita concretamente firma digitale con non ripudio, cifratura verso un destinatario mai incontrato prima, autenticazione dei server (HTTPS/TLS) e dei client, e i protocolli challenge-response a chiave pubblica.

**Come viene creato.** L'emissione si articola in sei fasi:

1. **Generazione della coppia di chiavi.** Alice genera $(K_A^+, K_A^-)$. La chiave privata **non lascia mai** il suo controllo.
2. **Invio della chiave pubblica alla CA**, con modalità che impediscano a Trudy di sostituirla in transito.
3. **Prova dell'identità.** La CA verifica che il richiedente sia identificabile in modo non ambiguo con il nome dichiarato, gestendo omonimie, nomi simili, dati incompleti. La verifica può essere documentale, amministrativa, aziendale, oppure — nel caso dei certificati per server — un controllo di possesso del dominio.
4. **Prova di possesso della chiave privata.** Non basta che Alice invii *una* chiave pubblica: la CA deve accertare che Alice conosca la chiave privata corrispondente, tipicamente con un protocollo **challenge-response** (la CA invia una sfida, Alice la elabora con $K_A^-$, la CA verifica con $K_A^+$). Senza questo passo, Trudy potrebbe farsi certificare la chiave pubblica di qualcun altro.
5. **Generazione e firma del certificato.** La CA assembla soggetto, chiave pubblica, validità e dati dell'emittente, e firma il tutto con la propria chiave privata.
6. **Rilascio** del certificato ad Alice.

> ⚠️ Le fasi 3 e 4 sono **l'anello debole della catena**: se sono implementate male, Trudy ottiene un certificato apparentemente intestato ad Alice ma contenente la *propria* chiave pubblica, e la crittografia sottostante non serve più a nulla.

**Come viene utilizzato.** Alice può allegare il certificato a ogni messaggio o alla fase iniziale del protocollo, oppure inviarlo su richiesta di Bob. Bob estrae la chiave pubblica dal certificato **solo dopo averlo verificato**. La procedura di verifica prevede:

1. **Validità temporale**: il certificato non deve essere scaduto né non ancora valido.
2. **Localizzazione del certificato dell'emittente**, che può trovarsi nel database locale, essere fornito nella catena, o essere recuperabile dai riferimenti contenuti nel certificato.
3. **Verifica della firma** del certificato con la chiave pubblica contenuta nel certificato dell'emittente.
4. **Risalita della catena**: se l'emittente è già fidato perché presente nel database locale, la verifica si ferma; altrimenti si ripete la procedura sul certificato successivo, fino a raggiungere una **Root Authority** fidata o fallire.
5. **Controllo di revoca**: il certificato non deve comparire nella **Certificate Revocation List (CRL)**, la lista firmata dalla CA dei certificati invalidati prima della scadenza naturale (compromissione della chiave privata, cambio di ruolo o dati, licenziamento, errore di emissione).

Solo se tutti i controlli passano, Bob può concludere che quella chiave pubblica appartiene davvero al soggetto dichiarato.

**PKI, gerarchie e catena di certificati.** Una singola CA sarebbe un **punto singolo di fallimento** (disservizio, collo di bottiglia, bersaglio di DoS o di compromissione) e lascerebbe irrisolto il problema di come distribuire in modo autentico la *sua* chiave pubblica. Per questo le CA sono organizzate gerarchicamente in una **Public Key Infrastructure (PKI)**, cioè l'insieme di certificati, autorità, procedure di verifica e meccanismi di revoca. Una CA superiore firma il certificato di una CA subordinata, che a sua volta può certificare utenti, server o altre CA: si forma così una **catena di certificati** in cui ciascun certificato è firmato dall'emittente successivo. Al vertice sta la **Root Authority**, il cui certificato è **self-signed** (autofirmato) e viene **preinstallato** dal produttore nel browser o nel sistema operativo — è questo, operativamente, a risolvere il problema della distribuzione autentica della radice di fiducia. Il rovescio è che la fiducia nella root è una fiducia di base non verificabile: una root CA compromessa o disonesta compromette tutta la catena che dipende da essa.

**Confronto con il KDC.** Utile per chiudere la risposta, perché mostra che PKI e KDC risolvono problemi diversi:

| Aspetto | KDC (simmetrico) | CA / PKI (asimmetrico) |
|---------|------------------|------------------------|
| Presenza online | Necessaria a ogni sessione in cui serve una chiave | Necessaria soprattutto per emissione e revoca |
| Segreti conosciuti | Conosce le chiavi segrete che genera | Conosce solo chiavi pubbliche |
| Effetto della compromissione | Espone comunicazioni passate, presenti e future | Consente di emettere certificati falsi (impatto sul futuro) |
| Prestazioni | Primitive simmetriche più veloci | Primitive asimmetriche più lente |
| Problema risolto | Distribuzione di chiavi segrete | Autenticazione di chiavi pubbliche |

> ✅ **Sintesi da scrivere in chiusura:** il certificato digitale è l'attestazione, firmata da una CA, che una certa chiave pubblica appartiene a una certa identità. Serve a rendere autentica — non segreta — una chiave pubblica e quindi a rendere sicuri firma, cifratura e protocolli a chiave pubblica. Viene creato dopo verifica dell'identità e prova di possesso della chiave privata, e viene usato presentandolo al verificatore, che ne controlla validità temporale, firma, catena fino a una root fidata e assenza dalla CRL.

---

### **4. Permessi Unix e privilegi setuid/setgid**

Si consideri il seguente output del comando `ls -l`:

```text
-rwxr----- 1 alice studenti 1250 mag 20 10:15 report.txt
```

Spiegare il significato dei permessi associati al file, indicando quali operazioni possono essere eseguite dal proprietario, dagli utenti appartenenti al gruppo `studenti` e dagli altri utenti del sistema. Descrivere inoltre il significato dei privilegi setuid e setgid.

> **Riferimenti di teoria**:
>
> - [M2/UD3/L2 — Controllo degli accessi in Linux](../M2_AccessControl&Authentication/UD3/L2/L2_Controllo_Accessi_Linux.md) (§3 permessi base, §3.4 lettura di `ls -al`, §3.5 ordine di valutazione, §4 notazione ottale, §5 setuid/setgid/sticky, §6 chmod/chown/umask)
> - [M2/UD1/L3 — La matrice degli accessi](../M2_AccessControl&Authentication/UD1/L3_Matrice_Accessi.md) (§6 i 9 bit come ACL semplificata)
> - [M2/UD1/L2 — DAC, MAC e RBAC](../M2_AccessControl&Authentication/UD1/L2_DAC_MAC_RBAC.md) (§2 politica discrezionaria)
> - [M2/UD3/L1 — Introduzione a Linux](../M2_AccessControl&Authentication/UD3/L1/L1_Intro_Linux.md)
> - [M2/domande_fineM2](../M2_AccessControl&Authentication/domande_fineM2.md) (§1.6, §1.23, §2 esercizio sui permessi)

**Lettura della riga.** La stringa dei permessi è composta da **dieci caratteri**. Il primo indica il **tipo di oggetto**, i nove successivi sono tre terne che corrispondono, nell'ordine, a **proprietario (user)**, **gruppo proprietario (group)** e **altri (others)**:

```text
-   rwx   r--   ---
│    │     │     └── others : nessun permesso
│    │     └──────── group  (studenti) : sola lettura
│    └────────────── user   (alice)    : lettura, scrittura, esecuzione
└─────────────────── tipo: file ordinario
```

Gli altri campi della riga: `1` è il numero di hard link, `alice` è l'**utente proprietario**, `studenti` è il **gruppo proprietario**, `1250` la dimensione in byte, `mag 20 10:15` la data di ultima modifica, `report.txt` il nome.

In **notazione ottale** i permessi valgono $740$, poiché $r=4$, $w=2$, $x=1$: proprietario $4+2+1=7$, gruppo $4+0+0=4$, altri $0$.

**Chi può fare che cosa.**

| Soggetto | Permessi | Operazioni consentite |
|----------|:--------:|-----------------------|
| **alice** (proprietaria) | `rwx` (7) | Leggere il contenuto (`cat`, `less`), modificarlo o troncarlo (`>`, editor), eseguirlo come programma o script |
| Utenti del gruppo **studenti** | `r--` (4) | Solo leggere il contenuto. Non possono modificarlo né eseguirlo |
| **Tutti gli altri** utenti | `---` (0) | Nessuna operazione sul file |

Tre precisazioni che qualificano la risposta:

1. **L'ordine di valutazione non è "il permesso più favorevole"**. Il kernel confronta prima lo UID del richiedente con quello del proprietario: se coincidono usa **soltanto** i bit del proprietario e si ferma. Solo se non coincidono verifica l'appartenenza al gruppo del file e usa **soltanto** i bit del gruppo. Solo in ultima istanza usa i bit di *others*. Conseguenza controintuitiva ma classica d'esame: se un file avesse permessi `r--rwx---` e alice fosse la proprietaria e insieme membro del gruppo, alice potrebbe **solo leggere**, pur essendo nel gruppo che ha `rwx`.
2. **Il permesso `x` su un file di testo è tecnicamente concesso ma praticamente inutile**: il kernel proverebbe a eseguirlo e fallirebbe (formato non riconosciuto, oppure — se iniziasse con `#!` — invocherebbe l'interprete indicato). Il bit `x` è significativo per binari e script.
3. **I permessi del file non sono l'unica cosa che conta**: per *aprire* `report.txt` serve anche il permesso `x` (attraversamento) su tutte le directory del percorso; per **cancellarlo o rinominarlo** non serve `w` sul file, serve `w` sulla directory che lo contiene — motivo per cui esiste lo sticky bit. Inoltre alice, in quanto proprietaria, può sempre **modificare i permessi** con `chmod` anche quando i bit correnti glielo negherebbero, e `root` (UID 0) scavalca comunque ogni controllo.

**Setuid.** Il bit **setuid** (*Set User ID*), impostato su un file **eseguibile**, fa sì che il processo venga eseguito con i privilegi del **proprietario del file** anziché con quelli dell'utente che lo lancia: alla `exec()` il kernel pone lo **UID effettivo (EUID)** del processo pari allo UID del proprietario del file, lasciando invariato lo UID reale. È il meccanismo che permette, per esempio, a `passwd` — di proprietà di `root` e setuid — di consentire a un utente comune di modificare `/etc/shadow`, file scrivibile solo da root.

Nella stringa dei permessi il bit occupa la posizione della `x` del proprietario:

```text
rws------   # setuid attivo e proprietario ha anche il permesso di esecuzione
rwS------   # setuid attivo ma manca il permesso di esecuzione
```

Si imposta con `chmod u+s file` oppure con la notazione ottale a quattro cifre `chmod 4755 file`.

> ⚠️ **Problema di sicurezza.** Un binario setuid di proprietà di root è, di fatto, *codice root eseguibile da chiunque*: un suo qualunque difetto diventa immediatamente una via di **privilege escalation**. I pericoli tipici sono l'invocazione di comandi esterni tramite `system()` con nomi relativi (attacco via variabile `PATH`), la fiducia in variabili d'ambiente controllate dall'utente (`PATH`, `IFS`, `LD_PRELOAD`), i buffer overflow e l'input non validato. La regola è **privilegio minimo**: usare setuid solo quando indispensabile, rilasciare i privilegi appena l'operazione critica è conclusa, usare percorsi assoluti e ambiente ripulito. Da notare che il kernel **azzera automaticamente i bit setuid/setgid quando cambia il proprietario del file**, quindi in una sequenza di preparazione il `chmod` va sempre dato **dopo** il `chown`.

**Setgid.** Il bit **setgid** (*Set Group ID*) è l'analogo per il gruppo: su un file eseguibile fa sì che il processo giri con il **GID del file** anziché con il gruppo primario dell'utente che lo lancia (EGID = GID del file). Nella stringa occupa la posizione della `x` del gruppo:

```text
rwxrws---   # setgid attivo con permesso di esecuzione per il gruppo
rwxrwS---   # setgid attivo senza permesso di esecuzione per il gruppo
```

Si imposta con `chmod g+s file` o `chmod 2755 file`. Serve tipicamente a dare a un programma l'accesso a risorse riservate a un gruppo (per esempio i giochi che scrivono su un file di punteggi condiviso) senza dover ricorrere ai privilegi di root — è quindi la variante *meno pericolosa*, coerente con il principio del privilegio minimo. Applicato a una **directory**, su Linux ha un significato diverso e molto usato: i file creati al suo interno ereditano il **gruppo della directory** anziché il gruppo primario del creatore, il che è comodissimo per le cartelle condivise di progetto.

**Sticky bit (completamento).** Il terzo privilegio speciale, spesso chiesto insieme ai primi due: impostato su una **directory**, fa sì che al suo interno solo il proprietario del singolo file, il proprietario della directory e `root` possano rimuovere o rinominare i file. Nella stringa occupa la posizione della `x` degli *others* (`rwxrwxrwt` con esecuzione, `rwxrwxrwT` senza) e si imposta con `chmod +t` o `chmod 1777`. È il meccanismo che rende sicura `/tmp`, dove tutti possono scrivere ma nessuno può cancellare i file altrui.

> ✅ **Sintesi da scrivere in chiusura:** `-rwxr-----` (ottale 740) descrive un file ordinario su cui la proprietaria `alice` ha lettura, scrittura ed esecuzione, il gruppo `studenti` ha la sola lettura e gli altri utenti non hanno alcun permesso; la valutazione è per classe di appartenenza e si arresta alla prima classe applicabile. I bit **setuid** e **setgid** su un eseguibile fanno girare il processo con l'identità (UID o GID) del proprietario del file anziché con quella del chiamante: sono potenti e necessari, ma trasformano ogni bug del programma in una possibile escalation di privilegi.

---

### **5. Apache: direttive user-based**

In Apache, che tipo di regole si possono scrivere utilizzando le direttive user-based?

> **Riferimenti di teoria**:
>
> - [M4/UD5/L2 — Apache: direttive user-based e autenticazione](../M4/UD5/L2/L2_Direttive_User_Based_e_Autenticazione_Apache.md) (§2–3 Basic e Digest, §5–10 direttive di autenticazione, §11 direttive user-based, §12–13 esempi con utenti e gruppi, §14 Satisfy, §15 htpasswd)
> - [M4/UD5/L1 — Introduzione ad Apache](../M4/UD5/L1/L1_Introduzione_ad_Apache.md) (§6 host-based vs user-based, §11 contenitori, §5 `.htaccess`)
> - [M2/UD2/L2 — Autenticazione basata sulla conoscenza: password](../M2_AccessControl&Authentication/UD2/L2_Autenticazione_Password.md) (memorizzazione con hash e salt)

**Inquadramento.** Apache offre due famiglie di direttive per il controllo degli accessi: le **host-based**, che decidono in base a proprietà del *richiedente come host* (indirizzo IP, nome di dominio, variabile d'ambiente derivata da un header HTTP), e le **user-based**, che decidono in base all'**identità dell'utente**. La differenza operativa fondamentale è che le direttive user-based **richiedono una fase di autenticazione**: prima si stabilisce *chi* è il soggetto, poi si decide *se* può accedere. Autenticazione e autorizzazione restano quindi concettualmente distinte anche nella configurazione, e infatti servono due gruppi di direttive: quelle `Auth*` che dicono ad Apache *come e dove* autenticare, e le `Require` che dicono *chi* è autorizzato.

**Le direttive di autenticazione (prerequisito).**

| Direttiva | Funzione |
|-----------|----------|
| `AuthType Basic \| Digest` | Sceglie il metodo di autenticazione |
| `AuthName "Area Protetta"` | Assegna il nome all'area protetta (**realm**), mostrato nella finestra di dialogo del browser. Le virgolette servono se il nome contiene spazi |
| `AuthBasicProvider file` | Indica la sorgente delle credenziali; `file` è il valore predefinito ed è quindi opzionale |
| `AuthUserFile /percorso/passwords` | File che contiene utenti e password, creato con l'utility `htpasswd` |
| `AuthGroupFile /percorso/groups` | File che associa utenti a gruppi, necessario per `require group` |

Su **Basic** e **Digest** va detto che Basic è la più usata perché supportata da tutti i browser, ma trasmette login e password **codificati in Base64**, e la Base64 **non è cifratura**: serve solo a rappresentare i dati in forma testuale trasportabile. Digest nasce per evitare l'invio della password, basandosi su un digest MD5, ma i problemi noti di MD5 la rendono oggi insufficiente da sola. In entrambi i casi la conclusione è la stessa: **si usa HTTPS/`mod_ssl`**.

> ⚠️ `AuthUserFile` e `AuthGroupFile` **non devono trovarsi nel document tree** del server, altrimenti gli utenti potrebbero scaricarli con il browser.

**Le regole che si possono scrivere.** Le direttive user-based vere e proprie sono le forme di `require`, e permettono tre tipi di regola:

1. **Accesso a qualunque utente autenticabile** — *"chiunque sia registrato, ma nessun anonimo"*:

   ```apacheconf
   Require valid-user
   ```

   Autorizza qualsiasi utente presente nel file indicato da `AuthUserFile` che si autentichi correttamente. È la regola giusta quando la risorsa deve essere riservata "agli iscritti", senza distinzioni fra loro.

2. **Accesso a utenti nominati esplicitamente** — *"solo queste persone"*:

   ```apacheconf
   Require user alice bob
   ```

   Solo `alice` e `bob`, previa autenticazione, sono autorizzati; tutti gli altri utenti del file password vengono rifiutati con `401`/`403`.

3. **Accesso per appartenenza a un gruppo** — *"solo chi fa parte di…"*:

   ```apacheconf
   Require group tennis nuoto
   ```

   Sono autorizzati solo gli utenti appartenenti ai gruppi elencati. Richiede obbligatoriamente `AuthGroupFile`, il cui formato è

   ```text
   tennis: chiara alice bob
   nuoto: alice davide
   ```

   È la forma da preferire quando gli autorizzati sono molti o cambiano nel tempo: si modifica il file dei gruppi senza toccare la configurazione del server.

**Esempio completo (regola per utente).**

```apacheconf
<Directory /var/www/html/cartella_nascosta>
    AuthType Basic
    AuthName "Area Protetta"
    AuthBasicProvider file
    AuthUserFile /usr/local/httpd/passwd/passwords
    Require user alice bob
</Directory>
```

Il browser presenta la finestra di autenticazione con la dicitura *Area Protetta*, Apache verifica le credenziali contro il file indicato e concede l'accesso solo ad `alice` e `bob`. Le stesse direttive possono essere messe in un file `.htaccess` dentro la directory da proteggere, a condizione che la configurazione principale contenga almeno `AllowOverride AuthConfig` (o `AllowOverride All`); in tal caso lo scope è implicito, ed è la directory stessa con le sue sottodirectory.

**Esempio completo (regola per gruppo).**

```apacheconf
<Directory /var/www/html/cartella_tennis>
    AuthType Basic
    AuthName "Circolo Tennis"
    AuthUserFile /usr/local/httpd/passwd/passwords
    AuthGroupFile /usr/local/httpd/passwd/groups
    Require group tennis
</Directory>
```

**Creazione del file delle password.** Con l'utility `htpasswd`:

```bash
htpasswd -c /usr/local/httpd/passwd/passwords chiara   # -c CREA il file (solo la prima volta)
htpasswd /usr/local/httpd/passwd/passwords alice       # aggiunge/aggiorna un utente
```

Ogni entry ha la forma `utente:hash`: le password non sono memorizzate in chiaro, coerentemente con quanto visto in M2 sulla memorizzazione delle password.

> ⚠️ L'opzione `-c` **azzera** un file esistente: va usata solo alla creazione.

**Granularità e combinazione con le regole host-based.** Le regole user-based possono essere applicate a scope diversi usando i contenitori `<Directory>` (directory del filesystem e sottodirectory), `<Files>` e `<FilesMatch>` (per nome o espressione regolare del file, indipendentemente dalla directory), `<Location>` (path dello spazio web) e `<Limit>` (ristretto a determinati metodi HTTP, per esempio autenticare solo le `POST` e le `PUT` lasciando libere le `GET`). Quando nello stesso scope convivono direttive host-based e user-based, il loro rapporto logico si specifica con `Satisfy`:

```apacheconf
Require valid-user
Order allow,deny
Allow from 192.168.1
Satisfy Any
```

Con `Satisfy any` basta soddisfare **una** delle due condizioni: nell'esempio, accede chi si autentica **oppure** chi proviene dalla rete `192.168.1`. Con `Satisfy all` devono valere **entrambe**: si accede solo autenticandosi **e** provenendo da un host ammesso — la scelta corretta quando si vuole restringere un'area riservata anche alla rete interna.

**Che cosa non si può esprimere direttamente.** Vale la pena osservarlo perché è esattamente il punto degli esercizi del docente: `require` è una direttiva **positiva**, elenca chi è autorizzato. Per *escludere* un singolo utente (`trudy`) dalla sintassi storica non esiste una forma diretta: o si elencano tutti gli altri, o si costruisce un gruppo che non lo contiene. Con la sintassi di Apache 2.4 l'esclusione diventa esprimibile combinando i contenitori di autorizzazione:

```apacheconf
<RequireAll>
    Require valid-user
    Require not user trudy
</RequireAll>
```

> ✅ **Sintesi da scrivere in chiusura:** con le direttive user-based si scrivono regole di autorizzazione basate sull'**identità autenticata** dell'utente, in tre forme: qualunque utente valido (`Require valid-user`), un elenco esplicito di utenti (`Require user`), l'appartenenza a uno o più gruppi (`Require group`). Presuppongono le direttive di autenticazione `AuthType`, `AuthName`, `AuthUserFile` ed eventualmente `AuthGroupFile`, si applicano allo scope definito dai contenitori o da `.htaccess`, e si combinano con le regole host-based tramite `Satisfy all`/`Satisfy any`.

---

### **6. Reflected XSS**

In che cosa consiste un attacco di tipo Reflected XSS? Quali sono le possibili contromisure?

> **Riferimenti di teoria**:
>
> - [M4/UD4/L2 — Cross-Site Scripting](../M4/UD4/L2.md) (§2 definizione, §3 bypass della SOP, §4 differenza con SQLi, §5 obiettivi, §7 tipo 1, §10–11 condizioni, §13–16 esempio e schema, §22–23 contromisure)
> - [M4/UD4/L1 — Same Origin Policy](../M4/UD4/L1.md) (§4 definizione di origine, §16 cosa protegge e cosa no, §18.4 la SOP non blocca XSS)
> - [M4/UD2/L3 — Vulnerabilità dei cookie e contromisure](../M4/UD2/L3.md) (§6–7 furto cookie via script, §8 flag HttpOnly)
> - [M4/UD2/L2 — Cookie HTTP, sessioni e privacy](../M4/UD2/L2.md) (session identifier, attributi `Secure`/`HttpOnly`)
> - [M4/UD4/L3 — Phishing](../M4/UD4/L3.md) (veicolo tipico dell'URL malevolo)

**Che cos'è.** Il **Cross-Site Scripting** è un attacco in cui l'attaccante riesce a **far eseguire codice arbitrario (uno script) dal browser di un client vittima**. Il tratto distintivo è che la vittima non scarica lo script dal server dell'attaccante come risorsa esterna evidentemente non fidata: lo riceve **dentro una pagina proveniente da un server fidato**, con cui ha una sessione aperta. Lo script eredita quindi l'**origine** del server vulnerabile, e il browser lo esegue con tutti i privilegi di quella origine.

Nella variante **reflected** (XSS di tipo 1, detta anche *riflessa* o **non persistente**) lo script viaggia **dentro la richiesta della vittima** e torna indietro **nella risposta del server**. La vittima segue un link contenente lo script; il server vulnerabile prende quel valore come input e lo inserisce **senza verificarlo** nella pagina di risposta. La modifica della pagina è **non permanente**: vale solo per quella specifica richiesta HTTP con quell'URL forgiato, non altera il database del server e non cambia la pagina per gli altri utenti. È la differenza fondamentale con lo **stored XSS** (tipo 2, persistente), in cui lo script viene memorizzato lato server (in un commento, un post, un profilo, un file caricato) e viene poi servito automaticamente a **tutti** i visitatori.

**Le due condizioni necessarie.** Perché un reflected XSS sia possibile devono valere contemporaneamente:

1. lo script viene inserito **lato client come parametro di input** — nella query string, in un campo di form, in una richiesta costruita dall'attaccante o modificata da un attaccante attivo;
2. quel parametro viene **inserito dal server nella pagina rispedita al client** senza sanitizzazione né escaping.

La coppia di condizioni si presenta tipicamente nei **form di ricerca**, perché quasi tutti stampano nella pagina dei risultati la stringa cercata (*"Risultati della ricerca per: casa"*). Il server deve dunque essere **dinamico**: genera HTML a partire da input non fidato. È questa la caratteristica critica, non la presenza di un database.

**Come si realizza — l'esempio del dizionario online.** L'uso legittimo è

```http
GET /search?word=casa HTTP/1.1
Host: dictionary.example
```

e il server risponde con una pagina che contiene `Risultati della ricerca per: casa`. Se il valore di `word` non viene verificato, l'attaccante costruisce un URL in cui al posto della parola c'è un tag `<script>` che legge `document.cookie` e lo invia a un server sotto il proprio controllo. Le fasi dell'attacco sono cinque:

1. l'attaccante **recapita l'URL malevolo** alla vittima — via email, messaggio istantaneo, post su un forum, tipicamente con una tecnica di **phishing** che rende il link credibile; in alternativa, se è attivo sul canale, modifica direttamente la richiesta HTTP del client;
2. la vittima clicca e il browser **invia lo script come parametro** al server vulnerabile;
3. il server **riflette l'input nella pagina di risposta** senza verificarlo;
4. il browser fa il **parsing** del documento ricevuto, riconosce il contenuto come script e lo **esegue**, perché per lui proviene da un'origine fidata;
5. lo script **invia all'attaccante i cookie** del documento, fra cui il **session identifier** della sessione fra vittima e server fidato.

Il risultato è che l'attaccante può **impersonare la vittima** presso quel server per tutta la durata della sessione. Altri obiettivi possibili sono compiere azioni per cui solo la vittima ha i privilegi (una forma di *elevazione dei privilegi* nel contesto applicativo), leggere dati riservati, o mostrare alla vittima contenuti falsificati.

**Perché la Same Origin Policy non protegge.** La SOP stabilisce che uno script possa accedere solo a documenti, cookie e oggetti del DOM della **stessa origine** (stessa terna protocollo + host + porta). In un XSS la policy **non viene violata frontalmente: viene aggirata**, perché l'attaccante riesce a collocare il proprio codice *dentro* l'origine che il browser considera legittima. Il browser non esegue lo script perché si fida dell'attaccante, ma perché lo vede dentro una pagina ricevuta dal server fidato — e la SOP non ha modo di distinguere uno script legittimo da uno iniettato se finiscono entrambi nello stesso documento HTML. È l'errore concettuale che il docente segnala esplicitamente: **la SOP non blocca l'XSS**.

Va anche sottolineata la differenza con la **SQL injection**: nella SQLi il bersaglio è il **lato server** (la logica applicativa e il database); nell'XSS il bersaglio è il **client**, e il server vulnerabile è il *veicolo* — indispensabile, ma non la vittima diretta.

**Contromisure.** La prima linea di difesa è **lato server**, ed è l'unica risolutiva.

- **Validazione dell'input**: accettare solo ciò che è coerente con il requisito del campo (whitelist), rifiutando il resto. Come per la SQLi, la blacklist di stringhe pericolose è fragile — il caso MySpace/Samy mostra che una blacklist incompleta viene aggirata.
- **Sanitizzazione** dei contenuti quando l'HTML è davvero necessario (per esempio in un editor di post): si consente solo un insieme ristretto e noto di tag e attributi, usando una libreria consolidata e non espressioni regolari fatte in casa.
- **Escaping dell'output** — la misura decisiva. L'input dell'utente non deve mai essere inserito nella risposta **come codice**, ma sempre **come dato**: i caratteri `<`, `>`, `"`, `'`, `&` vanno convertiti nelle rispettive entità HTML. L'escaping deve essere **contestuale**, perché le regole cambiano se il valore finisce nel corpo HTML, dentro un attributo, dentro un blocco JavaScript, in un URL o in un foglio di stile.
- **Controllo dei file caricati**: il browser è "best effort" nell'interpretare i contenuti, quindi un file con estensione innocua ma contenuto HTML/JavaScript può essere eseguito. Vanno verificati tipo reale, `Content-Type` corretto e, idealmente, i contenuti utente vanno serviti da un **dominio separato** in modo che finiscano in un'origine diversa da quella dell'applicazione.

Le contromisure **di mitigazione**, che riducono il danno ma non eliminano la vulnerabilità:

- **Cookie `HttpOnly`**: impedisce a `document.cookie` di leggere il cookie di sessione, neutralizzando la specifica tecnica di furto descritta sopra. Non elimina l'XSS: lo script continua a girare nell'origine e può comunque compiere azioni per conto dell'utente.
- **Cookie `Secure`** e uso sistematico di **HTTPS**, per impedire l'intercettazione del cookie sul canale.
- **Content Security Policy (CSP)**: consente al sito di dichiarare, per tipologia di contenuto, quali origini siano ammesse per script, immagini, fogli di stile e frame, e in particolare di **vietare l'esecuzione di codice inline**. È una politica più fine della SOP e riduce drasticamente la superficie di esecuzione di molte iniezioni.
- **Lato client**, mantenere il browser aggiornato: le versioni recenti includono meccanismi di mitigazione, che però **non sostituiscono la correzione lato server**.
- Misure di contenimento del danno: sessioni di durata limitata, rigenerazione del session identifier dopo il login, ri-autenticazione per le operazioni sensibili.

> ✅ **Sintesi da scrivere in chiusura:** nel reflected XSS l'attaccante inserisce uno script in un parametro di una richiesta (tipicamente in un URL recapitato via phishing); il server vulnerabile lo riflette nella pagina di risposta senza sanitizzarlo; il browser della vittima lo esegue **nell'origine fidata**, aggirando la Same Origin Policy, e lo script può rubare il cookie di sessione o agire per conto dell'utente. La modifica non è persistente. La difesa risolutiva è lato server — validazione whitelist, sanitizzazione ed **escaping contestuale dell'output** — affiancata da `HttpOnly`, `Secure`, HTTPS e Content Security Policy come mitigazioni.

---

### **7. Protocollo challenge-response e mutua autenticazione**

Descrivere uno dei protocolli "di base" di tipo challenge-response per l'autenticazione visti a lezione (è indifferente l'uso di nonce o di timestamp) e spiegare:

- perché il protocollo non può subire un replay attack;
- come si deve modificare il protocollo in modo che si ottenga mutua autenticazione.

> **Riferimenti di teoria**:
>
> - [M3/UD3/L4 — Protocolli challenge-response](../M3/UD3/L4.md) (§2 idea generale, §3 freshness, §4 caso simmetrico, §5–6 nonce e varianti, §7–8 timestamp, §9 numeri di sequenza, §12 mutua autenticazione, §14 errori comuni)
> - [M3/UD3/L2 — Protocollo di autenticazione unilaterale](../M3/UD3/L2.md) (§4–8 costruzione incrementale, §9 challenge-response con chiave condivisa)
> - [M3/UD3/L3 — Attacchi comuni ai protocolli](../M3/UD3/L3.md) (replay, reflection, MITM)
> - [M3/UD3/L1 — Notazione per protocolli crittografici](../M3/UD3/L1.md) (notazione, freschezza, chiavi a lungo/breve termine)

**Il protocollo scelto: challenge-response con nonce e chiave simmetrica condivisa.** Alice e Bob condividono una chiave segreta a lungo termine $K_{AB}$. Alice vuole autenticare Bob, cioè vuole convincersi che dall'altra parte del canale insicuro ci sia davvero chi conosce $K_{AB}$, **ora**. Il protocollo, nella variante con challenge in chiaro e response cifrato, è di due soli messaggi:

$$1.\ A \rightarrow B : N_A$$

$$2.\ B \rightarrow A : \{N_A\}_{K_{AB}}$$

dove $N_A$ è un **nonce** generato da Alice, cioè una sequenza casuale di bit (tipicamente da 32 a 128) usata **una sola volta**.

I ruoli sono asimmetrici: Alice è il **challenger** (genera e verifica la sfida), Bob è il **responder** (deve autenticarsi). Alice genera $N_A$, lo memorizza temporaneamente e lo invia in chiaro. Bob lo cifra con la chiave condivisa e lo rimanda. Alice decifra il response con $K_{AB}$ e verifica che il valore ottenuto coincida **esattamente con il nonce che ha appena generato**. Se coincide, Alice conclude che chi ha risposto conosce $K_{AB}$ e ha elaborato *quella* sfida.

Due osservazioni concettuali:

- **Il segreto non viene mai trasmesso.** Bob non invia $K_{AB}$, ma un valore derivato $f(\text{challenge},\ \text{segreto})$. Trudy, che osserva il canale, vede sia $N_A$ sia $\{N_A\}_{K_{AB}}$, ma non ricava la chiave e non è in grado di produrre il response corretto. È il principio generale del challenge-response: *dimostrare la conoscenza di un segreto senza rivelarlo*.
- **Il challenge può essere pubblico.** Non è un problema che $N_A$ viaggi in chiaro: ciò che autentica Bob non è la segretezza della sfida, ma la sua **capacità di trasformarla** con la chiave giusta.

La funzione $f$ può essere realizzata con primitive diverse — cifratura simmetrica (come qui), MAC, firma digitale, decifratura di un valore cifrato — senza che cambi la logica del protocollo.

**Perché non subisce replay attack.** Un replay attack consiste nel registrare un messaggio valido di una sessione passata e reinviarlo in una sessione successiva per farsi accettare. Nei protocolli ingenui questo funziona perché il messaggio di autenticazione è **statico**: se Alice si autentica sempre inviando `A, P_A` (o anche `A, {P_A}_K`), Trudy registra quella coppia e la ripropone tale e quale; il verificatore non ha modo di distinguere il messaggio originale dalla sua copia. Cifrare non basta: un cifrato costante diventa esso stesso una credenziale riutilizzabile.

Il challenge-response con nonce elimina il problema perché introduce la **freschezza (freshness)**. Supponiamo che Trudy abbia registrato la risposta di una sessione precedente,

$$\{N_{old}\}_{K_{AB}}$$

In una nuova sessione Alice **non invia** $N_{old}$: genera un nonce nuovo $N_A$ e attende esattamente $\{N_A\}_{K_{AB}}$. La vecchia risposta, una volta decifrata, restituisce $N_{old} \neq N_A$ e viene rifiutata. Perché l'attacco riuscisse, Trudy dovrebbe **produrre il response corretto per un valore che non ha mai visto prima**, e per farlo dovrebbe conoscere $K_{AB}$.

La sicurezza poggia quindi su due condizioni, entrambe da enunciare:

1. il nonce è **casuale e imprevedibile**, quindi Trudy non può precalcolare la risposta;
2. il nonce **non viene mai riutilizzato** dal challenger — se $N_A$ si ripetesse, una vecchia risposta tornerebbe valida e il protocollo ricadrebbe nel caso statico.

È importante notare che a dover verificare la freschezza è **chi ha generato il nonce**: Bob non deve memorizzare i nonce ricevuti, gli basta usarli per rispondere; è Alice che confronta il response con il valore che ha in memoria. Questo è un vantaggio pratico dei nonce: non richiedono orologi sincronizzati né stato condiviso.

> 💡 Confronto rapido con le altre informazioni di freschezza, utile per mostrare padronanza. Il **timestamp** $T_A$ evita di memorizzare i challenge (basta una finestra di accettazione) ma è **prevedibile** e richiede orologi sincronizzati: proprio per questo con i timestamp **non tutte le varianti sono lecite**. La variante *challenge cifrato, response in chiaro* — sicura con un nonce, perché Trudy non può prevedere $N_A$ — diventa **insicura** con un timestamp, perché Trudy può stimare l'ora corrente e inviare il valore in chiaro fingendosi Bob. I **numeri di sequenza** non richiedono sincronizzazione oraria ma impongono stato persistente, legato alla coppia di partecipanti, alla direzione e alla sessione, e soffrono di desincronizzazione. La **chiave a breve termine** unisce autenticazione e distribuzione di chiave di sessione.

> ⚠️ Una trappola classica: nella variante in cui **sia il challenge sia il response sono cifrati**, il response **non deve essere identico** al challenge. Se il protocollo fosse $1.\ A \rightarrow B : \{N_A\}_{K_{AB}}$ e $2.\ B \rightarrow A : \{N_A\}_{K_{AB}}$, Trudy potrebbe limitarsi a **riflettere** verso Alice lo stesso valore cifrato che ha appena intercettato, senza decifrare nulla e senza conoscere la chiave. Per questo il protocollo prescrive una trasformazione riconoscibile, tipicamente $2.\ B \rightarrow A : \{N_A + 1\}_{K_{AB}}$: solo chi ha davvero **decifrato** il challenge può incrementare il nonce e ricifrarlo.

**Come ottenere la mutua autenticazione.** Il protocollo descritto è **unilaterale**: al termine Alice ha autenticato Bob, ma Bob non sa nulla di chi gli ha inviato la sfida — chiunque può generare un numero casuale e spedirlo. Per la **mutua autenticazione** occorre eseguire il test in **entrambe le direzioni**, cioè far sì che ciascuna parte risponda a una sfida fresca dell'altra. La forma più compatta usa **due nonce** e tre messaggi:

$$1.\ A \rightarrow B : N_A$$

$$2.\ B \rightarrow A : N_B,\ \{N_A\}_{K_{AB}}$$

$$3.\ A \rightarrow B : \{N_B\}_{K_{AB}}$$

Il secondo messaggio fa doppio lavoro: Bob **risponde** alla sfida di Alice e contemporaneamente **lancia la propria**. Alice verifica $\{N_A\}_{K_{AB}}$ — e a questo punto ha autenticato Bob — poi risponde a $N_B$. Bob verifica $\{N_B\}_{K_{AB}}$ e autentica Alice. Il costo è di un solo messaggio in più rispetto alla versione unilaterale, grazie al *piggybacking* della sfida sulla risposta.

> ⚠️ **La mutua autenticazione non è "eseguire due volte lo stesso protocollo".** Se i messaggi delle due direzioni sono troppo simmetrici si apre la porta ai **reflection attack**: Trudy, che vuole autenticarsi come Alice presso Bob, apre una seconda sessione parallela **con Bob stesso** e gli gira la sfida $N_B$ appena ricevuta, facendosi calcolare da lui la risposta $\{N_B\}_{K_{AB}}$, che poi rispedisce nella prima sessione. Bob finisce per autenticare se stesso credendo di autenticare Alice.

La contromisura è **legare ogni messaggio al proprio contesto**, includendo nel materiale autenticato almeno l'**identità** del mittente o del destinatario, e possibilmente ruolo, direzione, fase del protocollo e identificativo di sessione:

$$2.\ B \rightarrow A : N_B,\ \{N_A,\ B\}_{K_{AB}}$$

$$3.\ A \rightarrow B : \{N_B,\ A\}_{K_{AB}}$$

Ora una risposta valida in una direzione non è più riutilizzabile nell'altra, perché contiene l'identità sbagliata. Un'alternativa equivalente è usare **chiavi o trasformazioni distinte per verso** oppure imporre una struttura asimmetrica ai due messaggi.

È lo stesso principio di progettazione — *ogni messaggio deve dire esplicitamente a chi è destinato e in quale contesto vale* — che ricorre nella correzione di Lowe al Needham-Schroeder a chiave pubblica e fra i principi di Abadi-Needham.

**Nota sulla variante a chiave pubblica.** Se si sostituisce la cifratura simmetrica con la **firma digitale**, il protocollo diventa

$$1.\ A \rightarrow B : A \qquad 2.\ B \rightarrow A : N_B \qquad 3.\ A \rightarrow B : [N_B]_{K_A^-}$$

ed elimina il vincolo pratico più fastidioso della versione simmetrica, cioè la necessità che $K_{AB}$ sia **già** condivisa attraverso un canale sicuro che, per ipotesi, non esiste. Resta però una nuova assunzione: Bob deve possedere in modo **autentico** la chiave pubblica di Alice. Se Bob la richiede sul canale insicuro, Trudy la sostituisce con la propria e realizza un **Man in the Middle**. La correzione è inviare non la chiave nuda ma il **certificato** $C_A$, verificando firma della CA, validità e catena di fiducia.

> ✅ **Sintesi da scrivere in chiusura:** nel challenge-response il verificatore invia una sfida fresca e il richiedente prova la propria identità restituendo una trasformazione della sfida che richiede il segreto, senza mai trasmetterlo. Il replay è impossibile perché il nonce è casuale, imprevedibile e mai riutilizzato: una risposta registrata è legata a una sfida vecchia e viene rifiutata. Per la mutua autenticazione si eseguono i due test in entrambe le direzioni con due nonce distinti (3 messaggi), includendo le identità nel materiale autenticato per prevenire i reflection attack.

---

## Nota metodologica

Le sette domande di questo appello coprono, nell'ordine: **M4 attacchi web** (SQLi, XSS), **M1 fondamenti** (proprietà di sicurezza), **M3 crittografia applicata** (certificati, challenge-response), **M2 controllo degli accessi** (permessi Unix, setuid/setgid) e **M4 configurazione** (Apache). È la stessa ripartizione dei due appelli di luglio: si veda la [Guida al ripasso delle ultime ore](Guida_Ripasso_Ultime_Ore.md) per la matrice di ricorrenza degli argomenti e per la strategia di studio.
