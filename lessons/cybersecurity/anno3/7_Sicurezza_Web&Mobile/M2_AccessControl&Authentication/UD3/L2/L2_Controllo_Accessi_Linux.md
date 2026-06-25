# L2 — Controllo degli Accessi in Linux: Utenti, Permessi e Privilegi

> 📌 Questa lezione collega i concetti teorici di controllo degli accessi alla loro implementazione pratica in Linux: utenti, gruppi, file delle password, permessi DAC, privilegi speciali e comandi amministrativi.

---

### **1. Utenti in Linux**

In Linux ogni soggetto che interagisce con il sistema è rappresentato da un **account utente** (*user account*), identificato da uno **username** usato in fase di login.

Di default il sistema dispone dell'utente **root**, cioè l'amministratore del sistema. Root ha accesso a tutte le risorse e rappresenta il soggetto privilegiato principale.

#### **1.1. UID e GID**

Alla creazione di un utente viene associato un identificatore numerico chiamato **UID** (*User ID*). Nelle slide viene presentato come un numero a 16 bit:
- `0` è riservato a `root`;
- i valori da `1` a `99` sono tipicamente riservati a servizi standard;
- ogni utente deve avere un UID univoco.

Gli utenti appartengono inoltre a uno o più gruppi. Ogni utente ha almeno un **gruppo primario**, identificato da un **GID** (*Group ID*).

> 💡 I nomi utente e gruppo sono comodi per gli esseri umani; il kernel decide i permessi usando soprattutto UID e GID.

#### **1.2. File di Sistema per Utenti e Gruppi**

Le informazioni sugli utenti sono distribuite principalmente in:
- `/etc/passwd`: informazioni pubbliche sugli account;
- `/etc/shadow`: hash delle password e informazioni sensibili, leggibile solo da `root`;
- `/etc/group`: elenco dei gruppi;
- `.profile` nella home dell'utente: configurazioni caricate all'avvio della shell di login.

Una riga di `/etc/group` ha struttura:

```text
nomeGruppo:passwordGruppo:GID:listaUtenti
```

I campi hanno questo significato:

| Campo | Significato |
|-------|-------------|
| `nomeGruppo` | Nome del gruppo |
| `passwordGruppo` | Password cifrata del gruppo; di solito non usata e sostituita da `*` |
| `GID` | Identificatore numerico del gruppo |
| `listaUtenti` | Elenco degli utenti del gruppo, separati da virgole |

La presenza dei gruppi semplifica il controllo degli accessi: invece di assegnare permessi utente per utente, si possono assegnare permessi a una categoria. Per esempio, tutti gli utenti abilitati alla posta elettronica possono appartenere al gruppo `mail`.

#### **1.3. Creazione e Rimozione degli Utenti**

La gestione degli account richiede privilegi amministrativi:

```bash
useradd nomeutente   # crea un utente
userdel nomeutente   # rimuove un utente
```

In pratica si usano spesso opzioni aggiuntive, ad esempio per creare automaticamente la home directory:

```bash
sudo useradd -m -s /bin/bash nomeutente
```

---

### **2. Autenticazione degli Utenti**

Gli utenti sono identificati dallo **username** e autenticati tramite una **password**.

#### **2.1. Da Password Unshadowed a Shadowed**

Storicamente Linux usava un solo file delle password: `/etc/passwd`. Poiché questo file contiene anche informazioni non segrete necessarie al sistema, era leggibile da tutti gli utenti. Questa configurazione è detta **unshadowed password**.

Il problema è che un file leggibile da tutti contenente anche hash delle password consente attacchi offline:
1. Un utente copia `/etc/passwd`.
2. Prova parole di dizionario localmente.
3. Per ogni parola calcola `hash(salt + parola)`.
4. Confronta il risultato con l'hash memorizzato.

La modalità moderna usa due file:
- `/etc/passwd`, ancora leggibile dagli utenti, con dati non sensibili;
- `/etc/shadow`, leggibile solo da `root`, con salt e hash delle password.

> ⚠️ Lo shadowing non rende impossibile un attacco a dizionario se l'attaccante ottiene `/etc/shadow`; riduce però drasticamente l'esposizione perché gli hash non sono leggibili dagli utenti normali.

#### **2.2. Struttura di `/etc/passwd`**

Una entry di `/etc/passwd` ha struttura:

```text
nomeUtente:passwordUtente:UID:GID:datiPersonali:directoryHome:shell
```

| Campo | Significato |
|-------|-------------|
| `nomeUtente` | Nome usato per identificare l'utente |
| `passwordUtente` | Password cifrata oppure segnaposto |
| `UID` | Identificatore numerico dell'utente |
| `GID` | Identificatore numerico del gruppo primario |
| `datiPersonali` | Nome completo o informazioni descrittive |
| `directoryHome` | Directory home assegnata all'utente |
| `shell` | Shell avviata dopo il login |

Nel campo `passwordUtente`:
- campo vuoto: l'utente può accedere senza password;
- `*` oppure `x`: la password cifrata è memorizzata in `/etc/shadow`.

#### **2.3. Ruolo del Salt**

Il **salt** è un valore casuale memorizzato in chiaro insieme all'hash della password. Serve a rendere più costosi gli attacchi basati su dizionario e tabelle precomputate.

Senza salt, lo stesso hash corrisponderebbe alla stessa password su tutti gli account. Con il salt, invece, la stessa password produce hash diversi se il salt cambia.

> 📌 Il salt non è segreto: la sua funzione non è nascondere la password, ma impedire confronti immediati e riuso efficiente di tabelle precomputate.

---

### **3. Controllo degli Accessi in Linux**

Linux adotta una politica **discrezionaria** (*Discretionary Access Control*, DAC) basata su:
- UID del soggetto che richiede l'accesso;
- GID del gruppo;
- UID e GID associati alla risorsa;
- bit di permesso della risorsa.

Ogni file ha un **proprietario** e un **gruppo proprietario**. Normalmente UID e GID del file corrispondono a quelli del processo che lo ha creato.

#### **3.1. Permessi Base**

Ogni file o directory ha nove permessi, divisi in tre classi:
- **utente proprietario** (*user*);
- **gruppo proprietario** (*group*);
- **altri utenti** (*others*).

Per ciascuna classe si possono assegnare:
- `r` (*read*);
- `w` (*write*);
- `x` (*execute*).

#### **3.2. Significato dei Permessi sui File**

Per un file ordinario:

| Permesso | Effetto |
|----------|---------|
| `r` | Permette di leggere il contenuto del file |
| `w` | Permette di modificare il contenuto del file |
| `x` | Permette di eseguire il file come programma o script |

#### **3.3. Significato dei Permessi sulle Directory**

Per una directory:

| Permesso | Effetto |
|----------|---------|
| `r` | Permette di leggere l'elenco dei nomi contenuti nella directory |
| `w` | Permette di modificare il contenuto della directory, ad esempio creare, rinominare o rimuovere file |
| `x` | Permette di attraversare la directory, cioè usarla in un path e accedere agli oggetti contenuti |

> ⚠️ Sulle directory il permesso `x` è fondamentale: senza `x` non si può entrare nella directory con `cd`, anche se è presente `r`.

#### **3.4. Lettura dei Permessi con `ls -al`**

Il comando:

```bash
ls -al
```

mostra anche i file nascosti e stampa informazioni estese, tra cui:
- stringa dei permessi;
- numero di link;
- proprietario;
- gruppo;
- dimensione;
- data di modifica;
- nome.

La stringa dei permessi è composta da dieci caratteri:
1. Il primo indica il tipo di oggetto:
   - `d`: directory;
   - `l`: link simbolico;
   - `-`: file ordinario.
2. I nove successivi indicano i permessi di proprietario, gruppo e altri.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.5. Ordine di Valutazione dei Permessi**

Quando un processo richiede accesso a un file, Linux valuta i permessi in questo ordine:

1. Se lo UID del richiedente coincide con lo UID del proprietario del file, si usano i permessi del proprietario.
2. Se il richiedente non è proprietario ma appartiene al gruppo del file, si usano i permessi del gruppo.
3. Se nessuna delle condizioni precedenti vale, si usano i permessi degli altri utenti.

> ⚠️ La valutazione non cerca "il permesso più favorevole". Se sei il proprietario, vengono considerati i bit del proprietario; non si passa ai bit del gruppo o degli altri solo perché sarebbero più permissivi.

---

### **4. Rappresentazione Numerica dei Permessi**

I permessi possono essere rappresentati in forma **ottale**, raggruppando i nove bit in tre terne:
- prima cifra: proprietario;
- seconda cifra: gruppo;
- terza cifra: altri.

Ogni permesso ha un valore:
- `r = 4`;
- `w = 2`;
- `x = 1`;
- assenza del permesso `= 0`.

La cifra di una classe è la somma dei permessi concessi:
- `7 = 4 + 2 + 1 = rwx`;
- `6 = 4 + 2 = rw-`;
- `5 = 4 + 1 = r-x`;
- `0 = ---`.

Esempi:

| Valore | Significato |
|--------|-------------|
| `777` | Tutti hanno lettura, scrittura ed esecuzione |
| `755` | Proprietario `rwx`, gruppo e altri `r-x` |
| `700` | Solo il proprietario ha tutti i permessi |
| `666` | Tutti hanno lettura e scrittura, nessuno ha esecuzione |
| `600` | Solo il proprietario può leggere e scrivere |

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Privilegi Addizionali**

Linux supporta tre privilegi speciali:
- **setuid**;
- **setgid**;
- **sticky bit**.

Sono rilevanti soprattutto per file eseguibili e directory pubbliche.

#### **5.1. Setuid**

Se il bit **setuid** (*Set User ID*) è impostato su un file eseguibile, il processo viene eseguito con i privilegi del **proprietario del file**, non con quelli dell'utente che lo lancia.

In termini di UID, lo UID effettivo del processo coincide con lo UID del file.

Nella stringa dei permessi, la `x` del proprietario viene sostituita da:
- `s` se setuid è impostato e il proprietario ha anche il permesso di esecuzione;
- `S` se setuid è impostato ma manca il permesso di esecuzione.

Esempi di posizione:

```text
rws------   # setuid + esecuzione per il proprietario
rwS------   # setuid senza esecuzione per il proprietario
```

> ⚠️ I programmi setuid sono delicati: se contengono vulnerabilità, un utente non privilegiato può sfruttarle con i privilegi del proprietario del file, spesso `root`.

#### **5.2. Setgid**

Se il bit **setgid** (*Set Group ID*) è impostato su un file eseguibile, il processo viene eseguito con il GID del file.

Nella stringa dei permessi, la `x` del gruppo viene sostituita da:
- `s` se setgid è impostato e il gruppo ha anche il permesso di esecuzione;
- `S` se setgid è impostato ma manca il permesso di esecuzione.

Esempi di posizione:

```text
rwxrws---   # setgid + esecuzione per il gruppo
rwxrwS---   # setgid senza esecuzione per il gruppo
```

#### **5.3. Sticky Bit**

Se lo **sticky bit** è impostato su una directory, solo:
- il proprietario della directory;
- il proprietario del file contenuto;
- `root`;

possono rimuovere o rinominare i file contenuti in quella directory.

È usato tipicamente per directory temporanee condivise da più utenti.

Nella stringa dei permessi, la `x` degli altri utenti viene sostituita da:
- `t` se sticky bit è impostato e gli altri hanno anche il permesso di esecuzione;
- `T` se sticky bit è impostato ma manca il permesso di esecuzione.

Esempi di posizione:

```text
rwxrwxrwt   # sticky bit + attraversamento per altri
rwxrwxrwT   # sticky bit senza attraversamento per altri
```

---

### **6. Comandi per la Gestione dei Privilegi**

#### **6.1. Variare i Permessi con `chmod`**

Il comando `chmod` modifica i bit di protezione di un file o di una directory.

Sintassi simbolica:

```bash
chmod [ugoa][+-][rwx] nomefile
```

Dove:

| Simbolo | Significato |
|---------|-------------|
| `u` | Utente proprietario |
| `g` | Gruppo proprietario |
| `o` | Altri utenti |
| `a` | Tutti gli utenti |
| `+` | Concede un permesso |
| `-` | Rimuove un permesso |

Solo il proprietario del file o `root` possono modificare i permessi.

Esempi:

```bash
chmod 666 file
# assegna lettura e scrittura a tutti

chmod -R go-rwx ./*
# rimuove a gruppo e altri lettura, scrittura ed esecuzione
# dalla directory corrente e dalle sottodirectory

chmod -R a+rx ./*
# assegna a tutti lettura ed esecuzione ricorsivamente
```

#### **6.2. Cambiare Proprietario con `chown`**

Il comando `chown` cambia il proprietario di un file.

```bash
chown [opzioni] [utente][:[gruppo]] file
```

Esempi:

```bash
chown andrew miofile
# imposta andrew come proprietario di miofile

chown andrew:users miofile
# imposta andrew come proprietario e users come gruppo

chown -R --from=root andrew miadir
# cambia ricorsivamente in andrew il proprietario dei file
# che appartengono a root
```

#### **6.3. Cambiare Gruppo con `chgrp`**

Il comando `chgrp` cambia il gruppo associato a un file o a una directory.

```bash
chgrp [opzioni] gruppo file...
```

Esempi:

```bash
chgrp users file
# imposta users come gruppo del file

chgrp -R users /home/andrew
# imposta ricorsivamente users come gruppo dei file
# nella home di andrew
```

#### **6.4. Maschera dei Permessi con `umask`**

Quando viene creato un nuovo file o una nuova directory, Linux applica una **maschera** che specifica i permessi da non concedere.

Il comando:

```bash
umask
```

mostra la maschera corrente. Con:

```bash
umask -S
```

si visualizza la configurazione in forma simbolica.

Valori tipici:

| Umask | Effetto |
|-------|---------|
| `002` | Impedisce la scrittura agli utenti che non sono proprietari né membri del gruppo |
| `022` | Impedisce la scrittura a gruppo e altri; è un valore di default comune |
| `044` | Impedisce la lettura a gruppo e altri |
| `077` | Impedisce lettura, scrittura ed esecuzione a gruppo e altri |

Esempi pratici:
- file creato con permessi base `666` e `umask 022` → permessi effettivi `644`;
- directory creata con permessi base `777` e `umask 022` → permessi effettivi `755`;
- file creato con permessi base `666` e `umask 077` → permessi effettivi `600`;
- directory creata con permessi base `777` e `umask 077` → permessi effettivi `700`.

> 📌 `umask` definisce i permessi da sottrarre ai permessi richiesti dal programma che crea il file.

#### **6.5. Effetto di `cp` e `mv` sui Permessi**

Quando un file viene creato con `cp`, i permessi del nuovo file dipendono anche da `umask`.

Quando un file viene spostato o rinominato con `mv` all'interno dello stesso filesystem, non viene creato un nuovo file: i permessi restano quelli del file originale.

---

### **7. Esercizi con Soluzioni**

Gli esercizi seguenti richiedono l'uso della shell Linux e dei comandi di gestione dei permessi. Per opzioni non viste a lezione, consultare `man nome_comando`.

#### **7.1. Esercizio 1 — Osservare e modificare i permessi di un file**

**Testo:** Scegliete un file, osservatene i permessi e provate a modificarli con il comando `chmod`.

**Soluzione:**

```bash
touch prova-permessi
ls -l prova-permessi

chmod u+x prova-permessi
ls -l prova-permessi

chmod 600 prova-permessi
ls -l prova-permessi
```

Spiegazione:
- `touch prova-permessi` crea un file vuoto, se non esiste già;
- `ls -l` mostra proprietario, gruppo e permessi;
- `chmod u+x` aggiunge il permesso di esecuzione al proprietario;
- `chmod 600` lascia lettura e scrittura solo al proprietario.

#### **7.2. Esercizio 2 — Creare una directory privata per proprietario e gruppo**

**Testo:** Create nella vostra home una directory di nome `tmp` e permettere accesso, scrittura e lettura in tale directory a proprietario e gruppo, mentre per gli altri negate l'accesso.

**Soluzione:**

```bash
mkdir -p ~/tmp
chmod 770 ~/tmp
ls -ld ~/tmp
```

`770` significa:
- proprietario: `rwx`;
- gruppo: `rwx`;
- altri: `---`.

Per una directory, `x` è necessario per l'accesso e l'attraversamento. Quindi, per concedere accesso effettivo a proprietario e gruppo, non basta `rw`: serve `rwx`.

Se la directory deve appartenere a un gruppo specifico:

```bash
chgrp nomegruppo ~/tmp
chmod 770 ~/tmp
```

#### **7.3. Esercizio 3 — Verificare i settaggi di default della `umask`**

**Testo:** Quali sono i settaggi di default della vostra `umask`?

**Soluzione:**

```bash
umask
umask -S
```

Un output comune è:

```text
0022
u=rwx,g=rx,o=rx
```

Interpretazione:
- `0022` nega la scrittura a gruppo e altri;
- un nuovo file tipicamente nasce come `644`;
- una nuova directory tipicamente nasce come `755`.

Per verificarlo:

```bash
touch prova-file
mkdir prova-dir
ls -ld prova-file prova-dir
```

#### **7.4. Esercizio 4 — Effetto di `chmod 444` su una directory**

**Testo:** I seguenti comandi che effetto producono? Perché?

```bash
cd
mkdir d1
chmod 444 d1
cd d1
```

**Soluzione:**

1. `cd` senza argomenti porta nella home directory.
2. `mkdir d1` crea una directory `d1`.
3. `chmod 444 d1` assegna solo lettura a proprietario, gruppo e altri.
4. `cd d1` fallisce con un errore di permesso.

Il motivo è che sulle directory il permesso `x` consente l'attraversamento. Con `444`, la directory ha permessi `r--r--r--`: può essere letta come elenco di nomi, ma non può essere attraversata.

Per correggere:

```bash
chmod 755 d1
cd d1
```

> ⚠️ La semantica di `r`, `w`, `x` sulle directory non coincide con quella sui file ordinari. Per entrare in una directory serve `x`.

#### **7.5. Esercizio 5 — Creare l'utente `pippo` con `umask 077`**

**Testo:** Creare l'utente `pippo`, impostare la sua `umask` in modo tale che possa creare file e directory leggibili e scrivibili solo da lui, negando qualsiasi altro permesso al gruppo e agli altri.

**Soluzione:**

Creazione dell'utente:

```bash
sudo useradd -m -s /bin/bash pippo
sudo passwd pippo
```

Impostazione persistente della `umask`:

```bash
sudo sh -c 'printf "\numask 077\n" >> /home/pippo/.profile'
sudo chown pippo:pippo /home/pippo/.profile
```

Se la distribuzione non crea automaticamente un gruppo primario chiamato `pippo`, usare il gruppo primario reale dell'utente:

```bash
id -gn pippo
```

Con `umask 077`:
- un nuovo file nasce tipicamente con permessi `600`, cioè `rw-------`;
- una nuova directory nasce tipicamente con permessi `700`, cioè `rwx------`.

Verifica:

```bash
sudo -u pippo sh -lc 'umask 077; touch file-test; mkdir dir-test; ls -ld file-test dir-test'
```

Output atteso nella parte dei permessi:

```text
-rw------- ... file-test
drwx------ ... dir-test
```

---

### **8. Riepilogo**

> ✅ Linux identifica utenti e gruppi tramite UID e GID. Le informazioni pubbliche sugli account stanno in `/etc/passwd`, mentre salt e hash delle password stanno in `/etc/shadow`. Il controllo degli accessi è discrezionario e usa permessi `r`, `w`, `x` distinti per proprietario, gruppo e altri. Sui file `x` significa esecuzione; sulle directory significa attraversamento. I permessi si gestiscono con `chmod`, `chown`, `chgrp` e `umask`; i privilegi speciali `setuid`, `setgid` e `sticky bit` modificano il comportamento standard dei processi o delle directory condivise.
