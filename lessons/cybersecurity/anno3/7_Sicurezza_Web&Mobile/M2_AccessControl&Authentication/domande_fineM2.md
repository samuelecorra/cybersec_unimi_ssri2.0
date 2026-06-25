# Domande di Fine Modulo 2 — Risposte

> 📌 Le risposte coprono le domande teoriche e l'esercizio finale del PDF `domande_fineM2.pdf`, relativo al Modulo 2: controllo degli accessi e autenticazione.

---

### **1. Domande Teoriche**

#### **1.1. Trojan Horse, DAC e MAC multilivello**

Un **Trojan Horse** è un programma apparentemente utile o innocuo che contiene codice nascosto malevolo. In un sistema con politica **discrezionaria** (*DAC*), il programma viene eseguito con i privilegi dell'utente che lo lancia; quindi il Reference Monitor vede richieste formalmente autorizzate, anche se l'intenzione reale è malevola.

Esempio: l'utente $S_1$ possiede un file riservato $F_1$, leggibile solo da lui. L'utente $S_2$ possiede un file $F_2$ su cui $S_1$ ha permesso di scrittura. Se $S_1$ esegue un Trojan che fa:

```text
read(F1)
write(F2)
```

entrambe le operazioni sono autorizzate dalla DAC: $S_1$ può leggere $F_1$ e può scrivere $F_2$. Il risultato è però una fuga di informazione: $S_2$ potrà leggere in $F_2$ il contenuto riservato di $F_1$.

In una politica **mandatoria multilivello** (*MAC*), come Bell-LaPadula, il sistema controlla anche il **flusso informativo** tra livelli. Se $F_1$ è a livello alto e $F_2$ è a livello basso, la regola **No Write Down** impedisce a un soggetto di scrivere informazione riservata verso un oggetto meno classificato. Per questo il Trojan fallisce: non basta che le singole operazioni sembrino lecite, perché il flusso complessivo viola la politica.

#### **1.2. Approcci principali all'autenticazione**

I principali approcci per autenticare un utente sono tre.

**Autenticazione basata sulla conoscenza:** l'utente dimostra di conoscere un segreto, come password o PIN. È economica, semplice e molto diffusa. I limiti principali sono password deboli, riuso, dimenticanza, condivisione, attacchi a dizionario, sniffing, snooping e social engineering.

**Autenticazione basata sul possesso:** l'utente dimostra di possedere un oggetto fisico, come smart card, token OTP, carta magnetica o RFID. È più difficile da attaccare rispetto a una password pura, soprattutto se il segreto nel token non è estraibile facilmente. Lo svantaggio è che autentica il token, non necessariamente la persona: se il token viene rubato, può essere usato da altri.

**Autenticazione biometrica:** l'utente dimostra di possedere una caratteristica fisica o comportamentale, come impronta digitale, iride, volto, voce o firma. Non può essere dimenticata o persa come una password o un token. I problemi principali sono costo, accettabilità, privacy, falsi positivi, falsi negativi e non revocabilità del dato biometrico.

> 💡 In pratica si usano spesso sistemi a più fattori, combinando conoscenza, possesso e biometria.

#### **1.3. Access Control List e capability list**

Una **Access Control List** (*ACL*) rappresenta la matrice degli accessi per colonne: per ogni oggetto si memorizza l'elenco dei soggetti autorizzati e dei relativi permessi.

Esempio concettuale:

```text
ACL(file1) = {(Alice, read), (Bob, read/write)}
```

Vantaggi:
- è naturale per sapere chi può accedere a un certo oggetto;
- rende semplice gestire i permessi associati a una risorsa;
- eliminare un oggetto è semplice, perché si elimina anche la sua ACL.

Svantaggi:
- revocare tutti i permessi di un soggetto può richiedere la scansione delle ACL di molti oggetti;
- la struttura è centrata sugli oggetti, non sui soggetti.

Una **capability list** rappresenta la matrice degli accessi per righe: per ogni soggetto si memorizza l'elenco degli oggetti a cui può accedere e i relativi permessi.

Esempio concettuale:

```text
CAP(Alice) = {(file1, read), (file2, write)}
```

Vantaggi:
- è naturale per sapere cosa può fare un certo soggetto;
- facilita la delega di privilegi;
- il soggetto può presentare la capability come prova del diritto di accesso.

Svantaggi:
- revocare un permesso su un oggetto può richiedere la scansione delle capability list di molti soggetti;
- le capability devono essere protette da falsificazione, copia o uso non autorizzato.

#### **1.4. Salt nelle password Unix-based**

Il **salt** è un valore casuale, diverso per ogni utente, salvato in chiaro insieme all'hash della password. La password non viene memorizzata direttamente: il sistema memorizza un valore del tipo:

$$H(\text{salt} \| \text{password})$$

Il salt serve a:
- fare in modo che due utenti con la stessa password abbiano hash diversi;
- impedire confronti immediati tra hash uguali;
- rendere inutili tabelle precomputate generiche, come molte rainbow table;
- costringere l'attaccante a rifare i calcoli per ogni salt.

Per un attacco contro **uno specifico utente**, il salt non aumenta la lunghezza reale della password, perché il salt è pubblico e l'attaccante può usarlo nei tentativi. Quindi, se l'attaccante calcola tutto da zero, il numero di password candidate da provare resta lo stesso. Il salt rende però l'attacco più difficile quando l'attaccante vorrebbe usare hash precomputati o attaccare molti account contemporaneamente.

#### **1.5. Least privilege in RBAC e account root**

Il principio del **minimo privilegio** (*least privilege*) richiede che un soggetto disponga solo dei privilegi strettamente necessari per svolgere il compito corrente.

In una politica **RBAC**, questo si ottiene attivando ruoli diversi in momenti diversi. Per esempio, un utente che è anche amministratore dovrebbe usare:
- il ruolo di utente normale per leggere email o navigare;
- il ruolo amministrativo solo per installare pacchetti o modificare configurazioni di sistema.

L'account **root** nei sistemi Unix-based è problematico rispetto a questo principio, perché ha tutti i privilegi su tutto il sistema. Se root viene usato per attività ordinarie, viola chiaramente il minimo privilegio. L'esistenza di root può essere giustificata per attività amministrative, ma come ruolo monolitico è meno fine-grained di un modello RBAC ben progettato.

#### **1.6. Setuid e setgid nei sistemi Unix-based**

Il privilegio **setuid** (*Set User ID*) su un file eseguibile fa sì che il processo venga eseguito con l'UID effettivo del proprietario del file, non con quello dell'utente che lo lancia.

Il privilegio **setgid** (*Set Group ID*) fa sì che il processo venga eseguito con il GID effettivo del gruppo associato al file.

Nella stringa dei permessi:
- setuid sostituisce la `x` del proprietario con `s` o `S`;
- setgid sostituisce la `x` del gruppo con `s` o `S`.

Esempio:

```text
rwsr-s--x
```

indica un file con setuid e setgid attivi. Sono meccanismi utili per permettere a utenti non privilegiati di eseguire operazioni controllate con privilegi superiori, ma sono anche rischiosi: un bug in un programma setuid o setgid può diventare una vulnerabilità grave.

#### **1.7. Autenticazione basata sul possesso**

L'autenticazione basata sul **possesso** verifica l'identità dell'utente tramite un oggetto fisico che l'utente possiede, detto **token**.

Esempi:
- carta magnetica;
- smart card;
- smart token che genera OTP;
- RFID;
- token USB con chiavi crittografiche.

Il vantaggio è che il segreto può essere custodito nel dispositivo e non deve essere ricordato dall'utente. Lo svantaggio principale è che il sistema autentica il possesso del token, non la persona: se il token viene rubato, l'attaccante può usarlo. Per questo il possesso viene spesso combinato con un PIN o una password.

#### **1.8. Gestione dei permessi di accesso in Unix-Linux**

Unix-Linux usa una politica discrezionaria basata su:
- proprietario del file;
- gruppo associato al file;
- altri utenti;
- permessi `r`, `w`, `x`.

Ogni file o directory ha tre terne di permessi:

```text
utente proprietario | gruppo | altri
```

Per i file:
- `r` consente la lettura;
- `w` consente la scrittura;
- `x` consente l'esecuzione.

Per le directory:
- `r` consente di elencare i nomi contenuti;
- `w` consente di creare, eliminare o rinominare file nella directory;
- `x` consente di attraversare la directory.

La valutazione avviene in ordine:
1. se il richiedente è il proprietario, si usano i bit del proprietario;
2. altrimenti, se appartiene al gruppo del file, si usano i bit del gruppo;
3. altrimenti, si usano i bit degli altri.

I permessi si osservano con `ls -l` e si modificano con `chmod`; proprietario e gruppo si modificano con `chown` e `chgrp`.

#### **1.9. Autenticazione basata su caratteristiche biometriche**

L'autenticazione biometrica verifica l'identità usando caratteristiche dell'utente, fisiche o comportamentali.

Esempi di caratteristiche fisiche:
- impronta digitale;
- iride;
- retina;
- volto;
- geometria della mano.

Esempi di caratteristiche comportamentali:
- voce;
- firma;
- stile di scrittura;
- andatura.

Il sistema non confronta normalmente due campioni per uguaglianza perfetta, perché due acquisizioni dello stesso dato biometrico non sono mai identiche. Usa invece un **template** e una soglia di tolleranza.

#### **1.10. Memorizzazione delle password nei sistemi Unix-based**

Nei sistemi Unix-based moderni le password non vengono memorizzate in chiaro. Vengono memorizzati:
- il salt;
- l'hash della combinazione tra salt e password;
- eventuali informazioni di scadenza o gestione dell'account.

Storicamente le informazioni erano in `/etc/passwd`, ma questo file deve essere leggibile anche dagli utenti normali perché contiene informazioni sugli account. Per evitare attacchi offline sugli hash, i sistemi moderni usano `/etc/shadow`, leggibile solo da `root`.

La verifica al login avviene così:
1. l'utente inserisce la password;
2. il sistema recupera il salt dell'utente;
3. calcola $H(\text{salt} \| \text{password inserita})$;
4. confronta il risultato con l'hash memorizzato.

#### **1.11. Pathname assoluto e relativo in Unix**

Un **pathname assoluto** identifica un file o una directory partendo dalla radice del filesystem, cioè `/`.

Esempio:

```text
/home/alice/documenti/file.txt
```

Un **pathname relativo** identifica un file o una directory partendo dalla directory corrente.

Esempio, se la directory corrente è `/home/alice`:

```text
documenti/file.txt
```

Simboli utili:
- `.` indica la directory corrente;
- `..` indica la directory madre;
- `~` indica la home directory dell'utente.

#### **1.12. Autenticazione basata sulla conoscenza e limiti**

L'autenticazione basata sulla **conoscenza** richiede che l'utente dimostri di conoscere un segreto, come una password, un PIN o la risposta a una domanda segreta.

È semplice, economica e facile da implementare, ma ha molti limiti:
- gli utenti scelgono password deboli o prevedibili;
- le password possono essere dimenticate;
- possono essere condivise o riutilizzate su più servizi;
- possono essere rubate con phishing, spoofing o social engineering;
- possono essere osservate durante la digitazione;
- possono essere intercettate in rete se il canale non è cifrato;
- possono essere attaccate offline se l'attaccante ottiene il file degli hash.

> ⚠️ Chi conosce la password di un utente può impersonarlo: per il sistema non c'è differenza tra l'utente legittimo e l'attaccante che conosce il segreto.

#### **1.13. Matrice degli accessi**

La **matrice degli accessi** è un modello concettuale per rappresentare una politica di controllo degli accessi.

- Le righe rappresentano i **soggetti**.
- Le colonne rappresentano gli **oggetti**.
- Ogni cella contiene i permessi che quel soggetto ha su quell'oggetto.

Esempio concettuale:

```text
M[S, O] = {read, write}
```

significa che il soggetto $S$ può leggere e scrivere sull'oggetto $O$.

Il problema è che la matrice è in genere molto grande e sparsa: molti soggetti non hanno alcun permesso su molti oggetti. Per questo viene implementata tramite ACL, capability list o tabelle di autorizzazione.

#### **1.14. Informazioni sugli account Unix-Linux**

Le informazioni sugli account sono salvate principalmente in:
- `/etc/passwd`;
- `/etc/shadow`;
- `/etc/group`;
- file di configurazione nella home dell'utente, come `.profile`.

Una riga di `/etc/passwd` contiene:

```text
nomeUtente:passwordUtente:UID:GID:datiPersonali:directoryHome:shell
```

Il campo della password contiene spesso `x` o `*`, indicando che la password reale non è lì. Gli hash delle password sono in `/etc/shadow`, leggibile solo da `root`. In `/etc/shadow` non viene salvata la password in chiaro, ma una forma basata su salt e hash.

Il file `/etc/group` contiene invece le informazioni sui gruppi:

```text
nomeGruppo:passwordGruppo:GID:listaUtenti
```

#### **1.15. Autenticazione e tre tecniche utente-computer**

L'**autenticazione** è il processo con cui un sistema verifica l'identità dichiarata da un utente. Nel caso utente-computer, l'utente deve dimostrare al computer di essere chi afferma di essere.

Le tre tecniche principali sono:

1. **Conoscenza:** l'utente conosce un segreto, come password o PIN. È semplice ma vulnerabile a furto, guessing e riuso.
2. **Possesso:** l'utente possiede un token, come smart card o generatore OTP. È più robusta di una password sola, ma il token può essere perso o rubato.
3. **Biometria:** l'utente presenta una caratteristica personale, come impronta o iride. È legata alla persona, ma introduce problemi di costo, privacy, errori di riconoscimento e non revocabilità.

Un sistema a più fattori combina almeno due di queste tecniche.

#### **1.16. Differenze tra autenticazione e autorizzazione**

**Autenticazione** e **autorizzazione** sono fasi diverse.

L'autenticazione risponde alla domanda:

```text
Chi sei?
```

Serve a verificare l'identità del soggetto.

L'autorizzazione risponde alla domanda:

```text
Che cosa puoi fare?
```

Serve a decidere se il soggetto autenticato può eseguire una certa operazione su una certa risorsa.

Esempio: un utente inserisce username e password e viene autenticato. Questo non implica che possa leggere qualsiasi file: il Reference Monitor deve poi verificare se quell'utente ha il permesso di leggere il file richiesto.

#### **1.17. Password spoofing, snooping e sniffing**

**Password spoofing:** l'attaccante crea una falsa interfaccia di login, simile a quella legittima, per indurre l'utente a inserire le credenziali. Il programma malevolo raccoglie username e password.

**Snooping** o **shoulder surfing:** l'attaccante osserva la password mentre l'utente la digita, fisicamente o tramite telecamere.

**Sniffing:** l'attaccante intercetta la password durante la trasmissione in rete, soprattutto se il canale non è cifrato.

#### **1.18. Regole per una buona gestione delle password**

Buone regole di gestione delle password:
- non usare parole di dizionario;
- non usare nomi, date di nascita o informazioni personali;
- usare password sufficientemente lunghe;
- combinare lettere maiuscole, minuscole, numeri e simboli;
- non condividere la password;
- non riutilizzare la stessa password su servizi diversi;
- cambiare password periodicamente quando richiesto dalla politica del sistema;
- non scrivere la password in luoghi accessibili;
- cambiare le password iniziali o di default;
- usare password temporanee con cambio obbligatorio al primo accesso.

Contromisure lato sistema:
- limitare i tentativi falliti;
- registrare i tentativi nei log;
- rifiutare password troppo semplici;
- usare shadow password;
- proteggere il canale di autenticazione con cifratura.

#### **1.19. Processo di autenticazione biometrica**

Il processo biometrico ha due fasi.

**Enrollment:**
1. Il sistema acquisisce più campioni della caratteristica biometrica.
2. Estrae le caratteristiche rilevanti.
3. Costruisce un **template**.
4. Memorizza il template nel database.

**Autenticazione:**
1. L'utente dichiara la propria identità.
2. Il sistema acquisisce un nuovo campione biometrico.
3. Estrae le caratteristiche dal nuovo campione.
4. Confronta il campione con il template associato all'identità dichiarata.
5. Accetta l'utente se la distanza è minore o uguale a una soglia $\delta$.

Formalmente:

$$d(\text{campione}, \text{template}) \leq \delta \Rightarrow \text{utente autenticato}$$

La soglia deve bilanciare:
- **FAR** (*False Acceptance Rate*), cioè accettazione di impostori;
- **FRR** (*False Rejection Rate*), cioè rifiuto di utenti legittimi.

#### **1.20. Tre implementazioni della matrice degli accessi**

Le tre implementazioni principali sono:

| Tecnica | Idea | Vantaggi | Svantaggi |
|---------|------|----------|-----------|
| ACL | Per ogni oggetto si memorizzano soggetti e permessi | Naturale per controllare una risorsa; eliminare l'oggetto è semplice | Revocare tutti i diritti di un soggetto richiede cercarlo in molte ACL |
| Capability list | Per ogni soggetto si memorizzano oggetti e permessi | Naturale per sapere cosa può fare un soggetto; facilita la delega | Revocare diritti su un oggetto richiede cercarlo in molte liste |
| Tabella di autorizzazione | Si memorizzano solo le triple non vuote $(S, O, p)$ | Compatta e adatta a DBMS; interrogabile con indici | Può crescere molto; richiede gestione efficiente degli indici |

La matrice completa è concettualmente semplice, ma inefficiente perché grande e sparsa.

#### **1.21. Principio di separazione dei privilegi**

Il principio di **separazione dei privilegi** richiede che un'operazione critica non dipenda da un solo privilegio o da una sola condizione, ma dalla combinazione di più privilegi indipendenti.

Esempio in RBAC: in una banca, chi crea una richiesta di pagamento non dovrebbe poterla anche approvare. Il pagamento richiede quindi due ruoli distinti: operatore e approvatore.

Esempio nell'autenticazione: un sistema può richiedere sia password sia token OTP. Il furto della sola password non basta per autenticarsi.

Questo principio riduce il rischio che la compromissione di un singolo utente, ruolo o fattore permetta di completare un'azione sensibile.

#### **1.22. Confronto operativo tra ACL, capability list e tabella di autorizzazione**

| Operazione | ACL | Capability list | Tabella di autorizzazione |
|------------|-----|-----------------|---------------------------|
| Determinare se una richiesta è permessa | Facile: si consulta la ACL dell'oggetto | Facile: si consulta la lista del soggetto o la capability presentata | Facile se indicizzata per $(S, O)$ o $(S, O, p)$ |
| Aggiungere un'autorizzazione per un nuovo soggetto | Facile per un oggetto: si aggiunge una voce nella ACL dell'oggetto | Facile: si aggiunge una capability alla lista del soggetto | Facile: si inserisce una nuova tupla |
| Cancellare un'autorizzazione per un soggetto | Facile se l'oggetto è noto; difficile se si vogliono rimuovere tutti i suoi diritti globalmente | Facile se si modifica la lista del soggetto; più difficile se capability già copiate o delegate | Facile se la tupla è indicizzata; si elimina il record |
| Creare un nuovo oggetto leggibile da tutti per default | Facile se esiste una voce `all`/`others`; altrimenti bisogna aggiungere tutti i soggetti alla ACL | Difficile: bisogna aggiungere una capability per il nuovo oggetto a tutti i soggetti | Difficile senza wildcard: bisogna inserire una tupla per ogni soggetto |
| Cancellare un oggetto | Facile: si elimina l'oggetto e la sua ACL | Difficile: bisogna rimuovere le capability verso quell'oggetto da tutti i soggetti | Abbastanza facile se indicizzata per oggetto: si eliminano tutte le tuple relative |

> 📌 Le ACL sono più comode per operazioni centrate sugli oggetti; le capability list sono più comode per operazioni centrate sui soggetti.

#### **1.23. Permessi Unix e significato di `-rw-r--r--`**

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nei sistemi Unix-based i permessi dei file sono mostrati da comandi come:

```bash
ls -al
```

La stringa dei permessi ha dieci caratteri. Nel caso:

```text
-rw-r--r--
```

il significato è:

- primo carattere `-`: l'oggetto è un file ordinario;
- `rw-`: il proprietario può leggere e scrivere, ma non eseguire;
- `r--`: il gruppo può solo leggere;
- `r--`: gli altri utenti possono solo leggere.

In forma numerica, questi permessi corrispondono a:

```text
644
```

perché:
- proprietario: `r + w = 4 + 2 = 6`;
- gruppo: `r = 4`;
- altri: `r = 4`.

Quando un soggetto richiede accesso, Unix-Linux controlla prima se è il proprietario, poi se appartiene al gruppo del file, infine applica i permessi degli altri.

---

### **2. Esercizio sui Permessi Unix**

#### **2.1. Dati dell'esercizio**

Utenti:
- `john`: membro dei gruppi `john` e `users`;
- `andrew`: membro del gruppo `andrew`;
- `nat`: membro dei gruppi `nat` e `users`.

File in `/users/all/files`:

| File | Permessi | Owner | Gruppo |
|------|----------|-------|--------|
| `a.out` | `rwxr-xr-x` | `andrew` | `sw` |
| `config.init` | `rw-r----x` | `root` | `dev` |
| `notes.html` | `rw-rw----` | `john` | `john` |
| `prova.cgi` | `rwsr-s--x` | `nat` | `dev` |
| `readme.txt` | `rw-r-xr-x` | `nat` | `users` |

#### **2.2. File che `john` può leggere**

`john` può leggere:

```text
a.out
notes.html
readme.txt
```

Motivazione:
- `a.out`: `john` non è owner né nel gruppo `sw`, ma gli altri hanno `r-x`;
- `notes.html`: `john` è owner e ha `rw-`;
- `readme.txt`: `john` appartiene al gruppo `users`, che ha `r-x`;
- `config.init` e `prova.cgi` non sono leggibili da `john`.

#### **2.3. File che il processo `a.out` eseguito da `john` può scrivere**

`a.out` non ha setuid né setgid. Se lo esegue `john`, il processo mantiene i privilegi di `john`.

Il processo può scrivere:

```text
notes.html
```

Motivazione:
- `notes.html`: `john` è owner e ha `rw-`;
- `a.out`: per `john` valgono i permessi degli altri `r-x`, senza `w`;
- `config.init`: per `john` valgono i permessi degli altri `--x`, senza `w`;
- `prova.cgi`: per `john` valgono i permessi degli altri `--x`, senza `w`;
- `readme.txt`: `john` è nel gruppo `users`, ma il gruppo ha `r-x`, senza `w`.

#### **2.4. File che `andrew` può scrivere**

`andrew` può scrivere:

```text
a.out
```

Motivazione:
- `a.out`: `andrew` è owner e ha `rwx`;
- sugli altri file `andrew` non è owner, non appartiene ai gruppi rilevanti e non trova permesso di scrittura negli altri.

#### **2.5. File che il processo `prova.cgi` eseguito da `andrew` può eseguire**

`prova.cgi` ha permessi:

```text
rwsr-s--x
```

quindi ha **setuid** e **setgid** attivi. Se `andrew` esegue `prova.cgi`, il processo risultante ha:
- UID effettivo `nat`;
- GID effettivo `dev`.

Il processo può eseguire:

```text
a.out
prova.cgi
```

Motivazione:
- `a.out`: il processo non è owner e non appartiene al gruppo `sw`, ma gli altri hanno `r-x`;
- `prova.cgi`: il processo ha UID effettivo `nat`, quindi usa i permessi del proprietario `rws`, che includono esecuzione;
- `config.init`: il processo ha GID effettivo `dev`, quindi usa i permessi del gruppo `r--`, che non includono esecuzione;
- `notes.html`: non ci sono permessi di esecuzione applicabili;
- `readme.txt`: il processo ha UID effettivo `nat`, quindi usa i permessi del proprietario `rw-`, che non includono esecuzione.

> ⚠️ Quando una classe di permessi viene selezionata, non si passa a una classe successiva solo perché sarebbe più permissiva. Per esempio, su `readme.txt` il processo è owner effettivo (`nat`), quindi valgono i permessi owner `rw-`, anche se gli altri avrebbero `r-x`.

#### **2.6. Risposte sintetiche**

| Domanda | Risposta |
|---------|----------|
| File che `john` può leggere | `a.out`, `notes.html`, `readme.txt` |
| File che il processo `a.out` eseguito da `john` può scrivere | `notes.html` |
| File che `andrew` può scrivere | `a.out` |
| File che il processo `prova.cgi` eseguito da `andrew` può eseguire | `a.out`, `prova.cgi` |

---

### **3. Riepilogo**

> ✅ Le domande di fine modulo ruotano attorno a tre nuclei: politiche di controllo degli accessi (DAC, MAC, RBAC, matrice degli accessi e sue implementazioni), autenticazione (conoscenza, possesso, biometria) e gestione Unix-Linux di utenti, password e permessi. L'esercizio finale richiede di applicare con precisione l'ordine di valutazione dei permessi Unix e gli effetti di setuid/setgid.
