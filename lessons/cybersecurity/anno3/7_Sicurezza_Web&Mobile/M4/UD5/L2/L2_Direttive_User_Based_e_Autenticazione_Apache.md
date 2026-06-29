# L2 — Apache: Direttive User-Based e Autenticazione

> 📌 Apache può proteggere risorse web anche in base all'identità dell'utente, non solo in base all'host di provenienza. In questo caso entrano in gioco autenticazione, file delle password, gruppi e direttive user-based come `Require`.

---

### **1. Autenticazione in Apache**

Apache supporta più modalità di autenticazione.

La dispensa si concentra su due meccanismi principali:
- **Autenticazione Basic**;
- **Autenticazione Digest**.

Questi meccanismi permettono al Web server di richiedere all'utente un nome utente e una password prima di concedere accesso a una risorsa.

L'autenticazione viene poi combinata con direttive di autorizzazione user-based, che stabiliscono quali utenti o gruppi possano accedere.

> 📌 Autenticare significa verificare l'identità dichiarata dall'utente; autorizzare significa decidere se quell'utente autenticato può accedere alla risorsa richiesta.

---

### **2. Autenticazione Basic**

L'autenticazione **Basic** è la forma più comunemente utilizzata tra quelle descritte nella dispensa.

Il motivo principale è che è supportata da tutti i browser.

Nel meccanismo Basic:
- il browser richiede all'utente login e password;
- le credenziali vengono inviate al server;
- login e password sono codificati in **Base64**;
- la codifica Base64 non è cifratura.

Quindi, nella forma base, login e password vengono spediti in chiaro dal punto di vista della sicurezza crittografica.

La Base64 serve solo a rappresentare i dati in forma testuale adatta al trasporto, non a proteggerli.

> ⚠️ Autenticazione Basic senza canale cifrato espone le credenziali: deve essere usata con HTTPS/TLS quando le credenziali hanno valore reale.

---

### **3. Autenticazione Digest**

L'autenticazione **Digest** è nata per evitare l'invio diretto della password in chiaro.

Secondo la dispensa, Digest spedisce login e password cifrati in **MD5**.

Più precisamente, il protocollo si basa su un digest calcolato con MD5 invece che sull'invio semplice della password.

In passato Digest era ritenuta più sicura della Basic.

Tuttavia, a causa dei problemi noti di MD5, oggi viene considerata insicura o comunque non sufficiente come protezione autonoma.

La dispensa suggerisce quindi di usarla insieme a:
- `mod_ssl`;
- trasmissione cifrata.

In pratica, anche per Digest è opportuno usare un canale HTTPS.

> ⚠️ Digest non deve essere considerata una soluzione moderna completa: il canale cifrato resta essenziale.

---

### **4. Backend per Login e Password**

Apache può appoggiarsi a diversi backend per controllare login e password.

Il backend è la sorgente dati in cui il server cerca le informazioni necessarie per autenticare l'utente.

I moduli standard già inclusi citati dalla dispensa sono:
- `mod_auth_basic`;
- `mod_auth_digest`.

`mod_auth_basic` gestisce autenticazione Basic con file.

`mod_auth_digest` gestisce autenticazione Digest con file.

La dispensa cita anche altri moduli, la cui lista completa è disponibile all'indirizzo:

```text
http://modules.apache.org/
```

Esempi:
- `mod_authn_dbd`;
- `mod_authn_dbm`;
- `mod_authnz_ldap`.

`mod_authn_dbd` carica i dati degli utenti da autenticare da tabelle SQL.

`mod_authn_dbm` carica i dati degli utenti da file in formato DBM.

`mod_authnz_ldap` fornisce supporto per LDAP.

> 💡 Il backend può essere un semplice file locale, ma anche un database o un servizio di directory come LDAP.

---

### **5. Direttive Relative all'Autenticazione**

Le direttive di autenticazione vanno inserite quando è necessario autenticare un utente.

Devono essere usate insieme alle direttive user-based, perché autenticare l'utente non basta: bisogna anche specificare chi è autorizzato.

Le direttive citate sono:
- `AuthType`;
- `AuthName`;
- `AuthBasicProvider`;
- `AuthUserFile`;
- `AuthGroupFile`.

---

### **6. Direttiva AuthType**

La direttiva:

```apacheconf
AuthType Basic
```

seleziona il metodo di autenticazione.

I valori principali discussi sono:
- `Basic`;
- `Digest`.

Esempio:

```apacheconf
AuthType Basic
```

In questo caso Apache userà autenticazione Basic.

---

### **7. Direttiva AuthName**

La direttiva:

```apacheconf
AuthName "Area Protetta"
```

assegna un nome all'area da proteggere.

Questo nome è il **realm**.

Quando l'utente tenta di accedere alla risorsa protetta, il browser presenta una finestra di dialogo per l'inserimento di nome utente e password.

In quella finestra compare la dicitura indicata in `AuthName`.

Lo scopo principale è rendere evidente all'utente che tipo di informazioni riservate sta richiedendo.

Le virgolette doppie sono necessarie quando l'etichetta è composta da più parole separate da spazi.

Esempio:

```apacheconf
AuthName "Area Protetta"
```

> 📌 Il realm aiuta a identificare l'area protetta e compare nella richiesta di credenziali mostrata dal browser.

---

### **8. Direttiva AuthBasicProvider**

La direttiva:

```apacheconf
AuthBasicProvider file
```

specifica il tipo di sorgente per l'autenticazione.

Il valore predefinito è:

```text
file
```

Quando il valore è `file`, la clausola è opzionale.

Questo significa che Apache cerca le credenziali in un file indicato da altre direttive, tipicamente `AuthUserFile`.

---

### **9. Direttiva AuthUserFile**

La direttiva:

```apacheconf
AuthUserFile PathName
```

specifica il file che contiene i nomi degli utenti e le relative password.

La creazione di questo file avviene tramite l'utility da riga di comando:

```bash
htpasswd
```

che viene descritta più avanti nella lezione.

Esempio:

```apacheconf
AuthUserFile /usr/local/httpd/passwd/passwords
```

> ⚠️ Il file delle password non dovrebbe trovarsi nel document tree del Web server, altrimenti gli utenti potrebbero visualizzarlo con il browser.

---

### **10. Direttiva AuthGroupFile**

La direttiva:

```apacheconf
AuthGroupFile PathName
```

specifica il file che contiene le informazioni sui gruppi.

Va usata quando si vuole autorizzare l'accesso a gruppi di utenti.

Il file dei gruppi permette di associare più utenti a uno stesso nome di gruppo e poi autorizzare il gruppo con una direttiva user-based.

Esempio:

```apacheconf
AuthGroupFile /usr/local/httpd/passwd/groups
```

Anche il file dei gruppi dovrebbe essere tenuto fuori dal document tree.

> 📌 `AuthUserFile` identifica il file utenti/password; `AuthGroupFile` identifica il file che associa utenti a gruppi.

---

### **11. Direttive User-Based**

Le direttive user-based decidono quali utenti autenticati possano accedere.

La sintassi storica riportata nella dispensa usa `require`.

Le forme principali sono:

```apacheconf
require valid-user
```

```apacheconf
require user utente1 utente2
```

```apacheconf
require group group1 group2
```

#### **11.1. require valid-user**

Con:

```apacheconf
require valid-user
```

si richiede che l'utente sia un qualsiasi utente valido contenuto nel file che memorizza utenti e password.

Quindi ogni utente presente e autenticabile è autorizzato.

#### **11.2. require user**

Con:

```apacheconf
require user utente1 utente2
```

solo gli utenti elencati sono autorizzati.

Esempio:

```apacheconf
require user alice bob
```

In questo caso possono accedere solo `alice` e `bob`, a condizione che si autentichino correttamente.

#### **11.3. require group**

Con:

```apacheconf
require group group1 group2
```

possono accedere solo gli utenti appartenenti ai gruppi elencati.

In questo caso bisogna usare anche:

```apacheconf
AuthGroupFile
```

per specificare il file in cui sono indicati i gruppi.

> ⚠️ Una direttiva `Require` ha senso solo se Apache sa dove trovare utenti, password ed eventualmente gruppi.

---

### **12. Esempio di Configurazione con Utenti**

La dispensa indica alcuni prerequisiti.

Le direttive vanno inserite:
- nel file di configurazione principale, in genere dentro una sezione `<Directory>`;
- oppure in un file `.htaccess`.

Nel caso di `.htaccess`, deve essere impostata almeno la direttiva:

```apacheconf
AllowOverride AuthConfig
```

Anche:

```apacheconf
AllowOverride All
```

è sufficiente.

Per Apache 2.4, i moduli:
- `mod_authn_core`;
- `mod_authz_core`;

devono essere compilati o caricati nel file `apache2.conf`.

La dispensa osserva che questo avviene già di default, quindi in genere non bisogna preoccuparsene.

#### **12.1. Passi da Seguire**

I passi da seguire sono:

1. Creare un file delle password usando l'utility `htpasswd`.
2. Configurare il server in modo che visualizzi una pagina di autenticazione.
3. Specificare gli utenti che hanno il permesso di accedere.

#### **12.2. Configurazione Base**

Codice di configurazione:

```apacheconf
AuthType Basic
AuthName "Area Protetta"
AuthBasicProvider file
AuthUserFile /usr/local/httpd/passwd/password
```

Queste direttive stabiliscono:
- autenticazione Basic;
- realm `Area Protetta`;
- sorgente delle credenziali su file;
- percorso del file delle password.

#### **12.3. Autorizzare un Utente Specifico**

Per concedere accesso all'utente `chiara`:

```apacheconf
Require user chiara
```

Configurazione completa essenziale:

```apacheconf
AuthType Basic
AuthName "Area Protetta"
AuthBasicProvider file
AuthUserFile /usr/local/httpd/passwd/password
Require user chiara
```

In questo caso:
- il browser chiede username e password;
- Apache controlla il file indicato da `AuthUserFile`;
- l'accesso è concesso solo se l'utente autenticato è `chiara`.

---

### **13. Esempio di Configurazione con Gruppi**

In caso di accesso a un gruppo, bisogna creare un file dei gruppi.

La sintassi del file è:

```text
groupName: chiara alice bob
```

In questo esempio, il gruppo `groupName` contiene gli utenti:
- `chiara`;
- `alice`;
- `bob`.

Combinando le direttive, si ottiene:

```apacheconf
AuthType Basic
AuthName "Area Protetta"
AuthBasicProvider file
AuthUserFile /usr/local/httpd/passwd/passwords
AuthGroupFile /usr/local/httpd/passwd/groups
Require group groupName
```

Il significato è:
- gli utenti sono autenticati tramite il file `/usr/local/httpd/passwd/passwords`;
- l'appartenenza ai gruppi è letta da `/usr/local/httpd/passwd/groups`;
- accedono solo gli utenti appartenenti a `groupName`.

> 📌 Le autorizzazioni per gruppo sono utili quando molti utenti condividono lo stesso diritto di accesso.

---

### **14. Uso Congiunto di Direttive Host-Based e User-Based**

Le direttive host-based e user-based non sono mutuamente esclusive.

Possono essere usate contemporaneamente.

Quando sono entrambe presenti, bisogna però specificare come interagiscono.

Per questo si usa la direttiva:

```apacheconf
Satisfy
```

La direttiva `Satisfy` definisce se devono essere soddisfatte tutte le condizioni o se basta soddisfarne una.

#### **14.1. satisfy all**

Con:

```apacheconf
Satisfy all
```

un accesso è permesso solo se sono soddisfatte sia:
- le direttive host-based;
- le direttive user-based.

Quindi l'utente deve provenire da un host ammesso e deve anche autenticarsi correttamente secondo le regole user-based.

#### **14.2. satisfy any**

Con:

```apacheconf
Satisfy any
```

un accesso è permesso se almeno una direttiva è soddisfatta.

Quindi può bastare:
- provenire da un host ammesso;
- oppure essere un utente valido.

#### **14.3. Esempio**

Esempio:

```apacheconf
Require valid-user
Order allow,deny
Allow from 192.168.1
Satisfy Any
```

Questa configurazione permette l'accesso:
- a un utente valido;
- oppure a una richiesta proveniente dalla rete `192.168.1`.

> ⚠️ `Satisfy Any` è più permissiva di `Satisfy All`: va usata solo quando è davvero accettabile che una sola delle condizioni basti.

---

### **15. Utility htpasswd**

L'utility:

```bash
htpasswd
```

serve a:
- creare il file delle password;
- inserire nuovi utenti nel file delle password;
- aggiornare la password di utenti già presenti.

Si usa da riga di comando.

#### **15.1. Creare un File delle Password**

Sintassi:

```bash
htpasswd -c nome_file username
```

L'opzione `-c` crea il file `nome_file`.

Se il file esiste già, viene sovrascritto.

Il comando inserisce una nuova entry con identificativo `username` e chiede di immettere la password due volte.

> ⚠️ Usare `-c` su un file già esistente può cancellare gli utenti già presenti, perché il file viene ricreato.

#### **15.2. Inserire o Aggiornare un Utente**

Sintassi:

```bash
htpasswd /etc/apache/passwd user
```

Il comando può comportarsi in due modi:
- se l'utente non esiste, inserisce il nuovo username e chiede la password;
- se l'utente esiste, cambia la password dell'utente specificato.

#### **15.3. Esempio di Entry**

Un esempio di entry del file delle password è:

```text
username:XWY5JwrAVBXsQ
```

Il primo campo è lo username.

Il secondo campo è la rappresentazione della password gestita dal meccanismo usato da `htpasswd`.

#### **15.4. Comunicazione delle Password agli Utenti**

La dispensa sottolinea che si assume che le password vengano comunicate agli utenti tramite un canale **out-of-band**.

Questo significa che la password non dovrebbe essere comunicata attraverso lo stesso canale o lo stesso meccanismo che si sta proteggendo.

Esempi concettuali di canali separati:
- comunicazione diretta;
- canale amministrativo separato;
- consegna fuori dal sito web protetto.

> 📌 `htpasswd` gestisce il file delle password, ma la sicurezza complessiva dipende anche da dove il file viene conservato e da come le password vengono comunicate agli utenti.

---

### **16. Esercizi e Domande Proposte**

Per lo svolgimento di alcuni esercizi viene richiesto di ricorrere al manuale ufficiale di Apache:

```text
http://httpd.apache.org/docs/
```

1. Creare una cartella `cartella_nascosta` e fare in modo che solo gli utenti `alice` e `bob` vi possano accedere.

2. Creare una cartella `cartella_segreta` e fare in modo che l'utente `trudy` non vi possa accedere.

3. Creare una cartella `cartella_tennis` e fare in modo che solo gli utenti del gruppo `tennis` vi possano accedere.

4. Creare una cartella `cartella_sport` e fare in modo che solo gli utenti dei gruppi `nuoto` e `tennis` vi possano accedere, tranne quelli del gruppo `cucina`.

5. Creare una cartella `cartella_nascosta2` che contenga diversi file, tra cui:
   - `index.html`;
   - `README.txt`;
   - `quite_secret.html`.

   Per questa cartella:
   - fare in modo che `alice` e `bob`, oppure un utente che fa la richiesta con IP `192.168.13.2`, possano leggere il file `index.html`;
   - fare in modo che tutti gli altri file presenti nella cartella siano leggibili da tutti, salvo le restrizioni dei punti successivi;
   - fare in modo che solo gli utenti appartenenti al gruppo `fortunati` possano leggere il file `README.txt`;
   - fare in modo che l'utente `trudy` non possa leggere il file `quite_secret.html`.

> ✅ Gli esercizi verificano l'uso di autenticazione Basic, file password, utenti specifici, gruppi, esclusioni, combinazione tra vincoli host-based e user-based, e restrizioni granulari su singoli file.
