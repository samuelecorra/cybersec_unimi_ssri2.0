# L1 — Introduzione ad Apache

> 📌 **Apache HTTP Server** è un Web server free e open source, storico e modulare, usato per ricevere richieste HTTP dai client e servire risorse web secondo le direttive definite nei file di configurazione.

---

### **1. Breve Storia di Apache**

Lo sviluppo di Apache è curato dalla **Apache Software Foundation**, un'organizzazione no-profit.

Il progetto Apache nasce nel **1995** con l'obiettivo di creare un Web server:
- stabile;
- affidabile;
- veloce;
- pensato inizialmente per piattaforme Unix;
- successivamente disponibile anche per Windows.

Nel periodo precedente alla nascita di Apache, il Web server più diffuso era il daemon HTTP pubblico sviluppato da **Rob McCool** presso il **NCSA** (*National Center for Supercomputing Applications*) dell'Università dell'Illinois.

A partire dal **1994**, lo sviluppo del server NCSA si era fermato perché il suo autore aveva lasciato l'NCSA.

Di conseguenza, un gruppo di webmaster iniziò a sviluppare autonomamente delle patch.

Apache nasce nell'aprile **1995** come evoluzione di `httpd 1.3`:
- ne ingloba le caratteristiche;
- ne corregge problemi;
- aggiunge nuove funzionalità;
- si emancipa progressivamente dal codice NCSA.

Il nome viene spesso spiegato con il gioco di parole:

```text
A PAtCHy sErver
```

cioè "un server fatto di patch".

In realtà, il nome viene dato anche in onore della tribù di nativi americani **Apache**.

Nel dicembre **1995** viene rilasciata la versione **1.0** del server Apache, completamente indipendente dal codice NCSA.

Apache è un Web server:
- gratuito;
- open source;
- storicamente molto diffuso;
- arrivato, nella dispensa, alla versione **2.4**.

> 💡 Apache nasce quindi da un'esigenza pratica: mantenere vivo e migliorare un Web server molto usato, quando il progetto originario non veniva più sviluppato.

---

### **2. Caratteristiche Generali di Apache**

Apache è un Web server che usa il protocollo **HTTP** per ricevere e servire le richieste dei client.

Il suo compito è:
- restare in ascolto su una porta;
- ricevere richieste HTTP;
- individuare la risorsa richiesta;
- applicare le regole di configurazione;
- restituire la risposta HTTP al client.

Apache gira come un processo **standalone**.

Il comportamento del server dipende dalle impostazioni contenute nei file di configurazione.

Nella versione Ubuntu della macchina virtuale suggerita per i laboratori, il file principale è:

```text
/etc/apache2/apache2.conf
```

La configurazione può essere distribuita anche in altri file.

Apache usa queste impostazioni per decidere come gestire l'accesso alle risorse del Web server, per esempio alle pagine HTML.

#### **2.1. Struttura Modulare**

Apache ha una struttura **modulare**.

Questo significa che le funzionalità del server sono organizzate in moduli.

I moduli possono essere:
- rimossi;
- rimpiazzati;
- aggiunti;
- sviluppati anche da soggetti esterni.

Questa architettura rende più semplice ed efficace la gestione del server, perché permette di estendere Apache senza riscrivere l'intero Web server.

Esistono moduli standard inclusi nella distribuzione e moduli scritti da sviluppatori esterni.

I moduli esterni non sono necessariamente garantiti dalla distribuzione principale.

#### **2.2. Moduli per Controllo degli Accessi e Autenticazione**

La dispensa cita alcuni moduli relativi al controllo degli accessi e all'autenticazione.

Per il controllo degli accessi:
- `mod_access`, sostituito dalla versione 2.1 da `mod_authz_host`.

Per l'autenticazione:
- `mod_auth`, sostituito dalla versione 2.1 da `mod_auth_basic` e `mod_auth_digest`.

> 📌 La modularità è uno degli aspetti centrali di Apache: molte capacità del server dipendono da moduli caricati e configurati.

---

### **3. Riferimenti Utili**

La dispensa indica alcuni riferimenti utili per approfondire Apache.

Pagina web principale di Apache:

```text
http://httpd.apache.org
```

Documentazione ufficiale:

```text
http://httpd.apache.org/docs/
```

Articoli su Apache:

```text
http://www.apacheweek.com/
```

Tutorial Ubuntu:

```text
http://wiki.ubuntu-it.org/Server/Web
```

> 📌 Per gli esercizi, la documentazione ufficiale di Apache è una fonte fondamentale perché descrive sintassi, direttive e differenze tra versioni.

---

### **4. Configurazione del Server**

Apache viene configurato inserendo **direttive** nei file di configurazione.

Le direttive sono regole testuali che specificano il comportamento del server.

I file di configurazione sono normali file di testo.

Il file principale è:
- `apache2.conf` nelle configurazioni Ubuntu citate dalla dispensa;
- `httpd.conf` in altre installazioni o nelle versioni più recenti, secondo la convenzione della distribuzione.

La posizione del file viene stabilita a tempo di compilazione e dipende dalla distribuzione.

Nella macchina virtuale Ubuntu proposta per i laboratori:

```text
/etc/apache2/apache2.conf
```

Il file segue la convenzione tipica dei file di configurazione Unix:
- una direttiva per riga o per blocco;
- commenti introdotti dal carattere `#`;
- inclusione di altri file di configurazione quando necessario.

#### **4.1. Direttiva ServerRoot**

La direttiva `ServerRoot` specifica la gerarchia di directory di default per l'installazione di Apache.

Indica quindi la directory radice dell'installazione del server.

Da questa posizione possono essere interpretati percorsi relativi relativi alla configurazione.

#### **4.2. Direttiva DocumentRoot**

La direttiva `DocumentRoot` stabilisce in quale posizione Apache deve cercare i file che compongono il sito.

Di default, le richieste vengono girate a questa directory.

Nel caso della versione Ubuntu citata nella dispensa, il valore predefinito è:

```text
/var/www/
```

Questo significa che una richiesta HTTP verso il server viene normalmente tradotta nella ricerca di un file all'interno del Web tree radicato in `/var/www/`.

La dispensa ricorda però che link simbolici e alias possono essere usati per puntare ad altre locazioni.

> 📌 `DocumentRoot` è la radice logica delle risorse servite dal sito.

#### **4.3. Direttiva ErrorLog**

La direttiva `ErrorLog` specifica dove si trovano i file dei log di errore.

Nella configurazione Ubuntu citata nella dispensa, il valore predefinito è:

```text
/var/log/apache2/error.log
```

Il log degli errori è importante per diagnosticare:
- problemi di configurazione;
- richieste fallite;
- errori di accesso;
- errori generati dal server;
- malfunzionamenti durante i test.

#### **4.4. Direttiva ServerAdmin**

La direttiva `ServerAdmin` specifica l'indirizzo email a cui il sistema deve indirizzare la posta destinata agli amministratori.

Il valore predefinito indicato nella dispensa è:

```text
webmaster@localhost
```

#### **4.5. Direttiva Listen**

La direttiva `Listen` specifica la porta su cui Apache deve mettersi in ascolto.

Opzionalmente può indicare anche l'indirizzo IP.

Il valore predefinito è la porta:

```text
80
```

La porta 80 è la porta standard del protocollo HTTP.

#### **4.6. Direttiva DirectoryIndex**

La direttiva `DirectoryIndex` specifica la pagina predefinita proposta dal server quando viene richiesta una directory.

Una richiesta di directory è tipicamente indicata con una barra finale:

```text
http://server.example/directory/
```

Se nella directory è presente un file indicato come indice, per esempio `index.html`, Apache può restituire quel file invece di mostrare o negare l'elenco della directory.

#### **4.7. Inclusione di Altri File di Configurazione**

Apache permette di aggiungere altri file di configurazione usando la direttiva:

```apacheconf
Include
```

Un file incluso di default nella configurazione Ubuntu citata dalla dispensa è:

```text
/etc/apache2/sites-enabled/000-default
```

In quel file si trovano alcuni parametri di configurazione già descritti.

> 💡 La configurazione Apache reale è spesso distribuita: un file principale include file più specifici per siti, moduli, porte e direttive locali.

---

### **5. Il File .htaccess**

Apache permette una gestione distribuita e decentralizzata della configurazione mediante file speciali inseriti nel **Web tree**.

In genere questi file sono chiamati:

```text
.htaccess
```

Il nome può essere modificato nel file di configurazione principale usando la direttiva:

```apacheconf
AccessFileName
```

Il file `.htaccess` segue la stessa sintassi degli altri file di configurazione.

Le direttive contenute in un file `.htaccess` si applicano:
- alla directory in cui il file viene inserito;
- alle sottodirectory di quella directory.

#### **5.1. Abilitare l'Uso di .htaccess**

Se si vuole usare `.htaccess`, bisogna comunicare ad Apache che deve cercare questi file.

La direttiva indicata nella dispensa è:

```apacheconf
AllowOverride All
```

Questa direttiva permette alle direttive contenute nei file `.htaccess` di sovrascrivere o integrare la configurazione principale, secondo quanto consentito.

#### **5.2. Differenza tra File Principale e .htaccess**

Se si modificano i file di configurazione principali, il server deve essere riavviato o ricaricato affinché le modifiche vengano lette.

Con `.htaccess`, invece, il file viene letto a ogni richiesta.

Quindi le modifiche a `.htaccess` hanno effetto immediato.

Questa caratteristica è comoda, ma ha anche un costo: Apache deve controllare la presenza di `.htaccess` durante la gestione delle richieste.

#### **5.3. Principio Most Specific Takes Precedence**

I file `.htaccess` vengono combinati secondo il principio:

```text
most specific takes precedence
```

Data una richiesta di accesso a un file, Apache cerca eventuali file `.htaccess`:
- partendo dalla directory in cui si trova il file richiesto;
- risalendo fino alla radice del server.

Se non viene trovato alcun `.htaccess`, per decidere se concedere o negare l'accesso alla risorsa viene usato il file di configurazione principale.

> 📌 `.htaccess` consente una configurazione locale della directory; le regole più specifiche prevalgono su quelle più generali.

---

### **6. Direttive per il Controllo degli Accessi**

Le direttive per il controllo degli accessi possono essere di due tipi:
- **host-based**;
- **user-based**.

Le direttive **host-based** definiscono le regole in base a proprietà del richiedente, come:
- indirizzo IP;
- nome di dominio;
- valore di una variabile d'ambiente.

Le direttive **user-based** definiscono le regole in base all'identità dell'utente.

In questo secondo caso è necessaria autenticazione.

Questa prima lezione si concentra soprattutto sulle direttive host-based.

Le direttive user-based e l'autenticazione vengono approfondite nella lezione successiva.

---

### **7. Direttive Host-Based**

Le direttive principali per il controllo host-based, nella sintassi storica, sono:
- `Allow`;
- `Deny`;
- `Order`.

`Allow` specifica chi può accedere a una risorsa.

`Deny` specifica a chi viene negato l'accesso a una risorsa.

`Order` stabilisce l'ordine di valutazione delle direttive `Allow` e `Deny`.

Nelle versioni più recenti, dalla versione 2.4, queste direttive sono state sostituite da direttive come:

```apacheconf
Require host address
Require ip ip.address
```

Per negare l'accesso si usa la clausola:

```apacheconf
not
```

Esempio:

```apacheconf
Require not ip 192.168.205.13
```

La dispensa sottolinea però che Apache 2.4 rimane retrocompatibile rispetto alla sintassi precedente, con le opportune condizioni di configurazione.

> ⚠️ Negli esercizi e nella documentazione bisogna prestare attenzione alla versione di Apache: la sintassi storica `Allow/Deny/Order` e quella moderna basata su `Require` non coincidono perfettamente.

---

### **8. Direttiva Allow**

La sintassi della direttiva `Allow` è:

```apacheconf
Allow from host
```

Il valore `host` può assumere diverse forme.

Può essere la parola chiave:

```apacheconf
all
```

che indica che l'accesso è dato a tutti.

Può essere un nome di dominio completo:

```text
mioserver.miodominio.it
```

Può essere un nome di dominio parziale:

```text
miodominio.it
.org
```

Può essere un indirizzo IP completo:

```text
217.199.180.21
```

Può essere un pattern IP:

```text
217.199.180
```

Può essere una rete con maschera:

```text
192.20.250.0/255.255.255.0
```

Può infine riferirsi a una variabile d'ambiente basata su header HTTP:

```apacheconf
env=variabile
```

Si possono indicare anche liste di indirizzi o nomi di dominio.

#### **8.1. Esempi di Allow**

Esempi di uso della direttiva:

```apacheconf
Allow from 10.252.46.165
Allow from host.example.com
Allow from 192.168.205
Allow from phishers.example.com moreidiots.example
Allow from all
Allow from miodominio.it
Allow from .org altrodominio.biz
Allow from 217.171.50 62.110
```

#### **8.2. Allow con Variabili d'Ambiente**

La direttiva può essere usata anche con variabili.

Esempio basato sul browser:

```apacheconf
BrowserMatch MSIE ms_browser
Allow from env=ms_browser
```

Esempio basato sul campo `Referer`:

```apacheconf
SetEnvIf Referer www.miosito.it internal_pg
Allow from env=internal_pg
```

Nel primo caso viene creata una variabile d'ambiente se il browser corrisponde al pattern indicato.

Nel secondo caso viene creata una variabile se il campo `Referer` contiene `www.miosito.it`.

> 💡 Le variabili d'ambiente permettono di definire regole di accesso non solo in base all'IP, ma anche in base a informazioni contenute negli header HTTP.

---

### **9. Direttiva Deny**

La sintassi della direttiva `Deny` è:

```apacheconf
Deny from host
```

Anche in questo caso `host` può essere:
- la parola chiave `all`;
- un nome di dominio completo;
- un nome di dominio parziale;
- un indirizzo IP completo;
- un pattern IP;
- una rete con maschera;
- una variabile d'ambiente.

La parola chiave `all` indica che l'accesso è negato a tutti.

#### **9.1. Esempi di Deny**

Esempi di uso della direttiva:

```apacheconf
Deny from 10.252.46.165
Deny from host.example.com
Deny from 192.168.205
Deny from phishers.example.com moreidiots.example
Deny from env=GoAway
```

> 📌 `Allow` e `Deny` usano una sintassi simile, ma hanno semantica opposta: la prima concede, la seconda nega.

---

### **10. Direttiva Order**

La sintassi della direttiva `Order` è:

```apacheconf
Order tipo_ordine
```

Il valore `tipo_ordine` stabilisce come Apache combina `Allow` e `Deny`.

#### **10.1. Order Deny,Allow**

Con:

```apacheconf
Order Deny,Allow
```

Apache valuta prima le direttive `Deny` e poi le direttive `Allow`.

L'accesso è permesso di default.

Questa corrisponde a una politica aperta, salvo negazioni esplicite.

#### **10.2. Order Allow,Deny**

Con:

```apacheconf
Order Allow,Deny
```

Apache valuta prima le direttive `Allow` e poi le direttive `Deny`.

L'accesso è negato di default.

Questa corrisponde a una politica chiusa, salvo autorizzazioni esplicite.

#### **10.3. Mutual-Failure**

Con:

```apacheconf
Order Mutual-failure
```

solo gli host presenti nella lista di `Allow` e non presenti in `Deny` hanno il permesso di accedere.

#### **10.4. Esempio di Order**

Esempio:

```apacheconf
Order Deny,Allow
Deny from all
Allow from dominiofidato.it
```

Anche se `Order Deny,Allow` è associata a una politica aperta di default, la direttiva `Deny from all` nega inizialmente l'accesso a tutti.

Poi `Allow from dominiofidato.it` concede l'accesso al dominio fidato.

> ⚠️ Per capire il risultato di una configurazione non basta guardare `Order`: bisogna considerare anche tutte le direttive `Allow` e `Deny` presenti.

---

### **11. Granularità delle Regole d'Accesso**

Dopo aver definito come permettere o negare l'accesso, bisogna specificare **a quali risorse** applicare le direttive.

Se le direttive si trovano in un file `.htaccess`, lo scope è già determinato:
- la directory in cui si trova `.htaccess`;
- le sue sottodirectory.

Se invece le direttive sono nel file di configurazione principale, bisogna specificare lo scope mediante contenitori.

I contenitori citati nella dispensa sono:
- `Directory`;
- `Files`;
- `FilesMatch`;
- `Location`;
- `Limit`.

#### **11.1. Directory**

`Directory` applica le direttive alla directory indicata e alle sue sottodirectory.

Sintassi:

```apacheconf
<Directory nomeDir>
    direttive
</Directory>
```

#### **11.2. Files**

`Files` applica le direttive a tutti i file corrispondenti al nome indicato, indipendentemente dalla directory in cui si trovano.

Sintassi:

```apacheconf
<Files nomefile>
    direttive
</Files>
```

#### **11.3. FilesMatch**

`FilesMatch` applica le direttive a tutti i file il cui nome corrisponde all'espressione regolare indicata.

Sintassi:

```apacheconf
<FilesMatch regexpr>
    direttive
</FilesMatch>
```

#### **11.4. Location**

`Location` si riferisce allo **spazio Web**, cioè agli URL o ai path web esposti dal server, non direttamente al filesystem.

Sintassi:

```apacheconf
<Location URL-path>
    direttive
</Location>
```

#### **11.5. Limit**

`Limit` restringe l'uso dei metodi HTTP.

Sintassi:

```apacheconf
<Limit method>
    direttive principali
</Limit>
```

La dispensa mostra anche un esempio di pagina della documentazione ufficiale relativa alla direttiva `Files`.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 I contenitori servono a definire lo scope delle regole: senza scope, una regola di accesso non dice chiaramente a quali risorse si applica.

---

### **12. Esempi di Contenitori e Regole**

#### **12.1. Directory con Politica Chiusa**

Esempio:

```apacheconf
<Directory /dir_controllata>
    Order Allow,Deny
    ...
</Directory>
```

In questo caso si è scelto di partire da una politica chiusa.

Con:

```apacheconf
Order Allow,Deny
```

l'accesso di default è negato.

#### **12.2. Directory con Dominio Fidato ed Host Escluso**

Esempio:

```apacheconf
<Directory /miosito/dir_controllata>
    Order Allow,Deny
    Allow from dominiofidato.it
    Deny from escluso.dominiofidato.it
</Directory>
```

Qui si specifica una sottorete o un dominio da cui si accettano le richieste.

Si esclude però un host specifico:

```text
escluso.dominiofidato.it
```

Quindi:
- le richieste provenienti da `dominiofidato.it` sono ammesse;
- le richieste provenienti da `escluso.dominiofidato.it` sono negate.

#### **12.3. Limit su POST e PUT**

Esempio:

```apacheconf
<Limit POST PUT>
    Order Deny,Allow
    Deny from all
    Allow from dominiofidato.it
</Limit>
```

Il contenitore `Limit` restringe le direttive ai metodi HTTP `POST` e `PUT`.

Anche se è presente:

```apacheconf
Order Deny,Allow
```

la direttiva:

```apacheconf
Deny from all
```

nega l'accesso a tutti coloro che effettuano richieste usando `POST` e `PUT`.

Poi:

```apacheconf
Allow from dominiofidato.it
```

concede l'accesso alle richieste provenienti da `dominiofidato.it`.

> ⚠️ In questo esempio la politica effettiva è chiusa per `POST` e `PUT`, perché `Deny from all` nega tutti e poi si riapre solo verso il dominio fidato.

#### **12.4. Files con Accesso Negato a Tutti**

Esempio:

```apacheconf
<Files nonsitocca.html>
    Order allow,deny
    Deny from all
</Files>
```

L'accesso al file:

```text
nonsitocca.html
```

è negato a tutti.

Il contenitore `Files` agisce sul nome del file, indipendentemente dalla directory in cui si trova.

---

### **13. Ordine di Valutazione delle Regole**

Data una richiesta di accesso a un file, la valutazione per decidere se concedere o negare l'accesso parte dalla directory in cui si trova il file e risale fino alla radice del server.

Se sono presenti file `.htaccess`, essi vengono combinati secondo il principio:

```text
most specific takes precedence
```

Nel caso di uso contemporaneo di diversi contenitori, l'ordine di valutazione indicato dalla dispensa è:

```text
<Directory>, <Files>, <Location>
```

Quindi Apache combina:
- regole legate alla directory fisica;
- regole legate al nome del file;
- regole legate allo spazio web dell'URL.

> 📌 L'esito finale dipende dalla combinazione di scope, ordine di valutazione, regole `Allow/Deny` e direttiva `Order`.

---

### **14. Usare Apache in Laboratorio**

Apache2 è disponibile anche per Linux.

A seconda della distribuzione, cambia il comando di installazione.

Nel caso di Ubuntu, si usa:

```bash
sudo apt-get install apache2
```

#### **14.1. Verificare se Apache è in Esecuzione**

Per sapere se il server è già in esecuzione si può usare uno dei seguenti comandi:

```bash
ps aux | grep apache2
```

oppure:

```bash
service apache2 status
```

#### **14.2. Avviare o Riavviare Apache**

Per far partire il server:

```bash
service apache2 start
```

Per riavviarlo:

```bash
service apache2 restart
```

Questi comandi vanno eseguiti con privilegi di amministrazione, quindi da `root` o tramite `sudo`, a seconda della distribuzione e della configurazione.

#### **14.3. Visualizzare le Pagine Locali**

Se si apre un browser sulla stessa macchina in cui è installato Apache, si possono visualizzare le pagine del Web tree usando:

```text
http://localhost/
```

oppure:

```text
http://127.0.1.1/
```

#### **14.4. Verificare Versione e Librerie**

Per vedere quale versione di Apache è installata:

```bash
apache2 -v
```

Per vedere quali librerie sono già installate:

```bash
apache2 -l
```

> ✅ Per lavorare con Apache in laboratorio bisogna saper installare il server, verificarne lo stato, avviarlo o riavviarlo, raggiungerlo da browser e controllare versione e librerie disponibili.

---

### **15. Esercizi e Domande Proposte**

Per lo svolgimento di alcuni esercizi viene richiesto di ricorrere al manuale ufficiale di Apache:

```text
http://httpd.apache.org/docs/
```

1. Avviare il Web Server e provare a visualizzare la pagina di benvenuto di default.

2. Nella direttiva `<Directory>` si deve usare il path assoluto o quello relativo? Giustificare la risposta.

3. Usando il file di configurazione principale e dei file `.htaccess` separati:
   - creare due directory, `immagini` e `documenti`, nella root directory di Apache;
   - dentro la directory `immagini` mettere alcuni file in formato JPEG e un file `index.html`;
   - dentro la directory `documenti` mettere solo alcuni file HTML e di testo;
   - fare in modo che nella directory `immagini` sia possibile visualizzare solo i file in formato JPEG;
   - aggiungere una direttiva in modo che anche il file `index.html` inserito nella directory `immagini` sia visibile, mentre tutti gli altri eventuali file `.html` non siano accessibili;
   - fare in modo che i file nella cartella `documenti` siano visibili solo a richieste che provengono dal proprio indirizzo IP.

4. Cercare nel manuale a cosa serve la parola chiave `Indexes`. Provare a utilizzarla.

5. Quali sono i permessi minimi che si devono dare a un file e a una directory nel Web tree per fare in modo che vengano letti tramite richiesta HTTP?

> ✅ Gli esercizi verificano installazione e uso base di Apache, comprensione dello scope di `<Directory>`, uso di `.htaccess`, restrizioni per tipo di file, restrizioni per indirizzo IP, direttiva `Indexes` e permessi minimi necessari nel Web tree.
