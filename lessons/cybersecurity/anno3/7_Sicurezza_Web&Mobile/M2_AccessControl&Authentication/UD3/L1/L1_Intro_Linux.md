# L1 — Introduzione a Linux: Filesystem, Shell e Comandi Principali

> 📌 Questa lezione costituisce l'introduzione pratica al laboratorio del Modulo 2. Linux è il sistema operativo su cui verranno implementati e testati i meccanismi di controllo degli accessi e autenticazione visti nelle lezioni teoriche.

---

### **1. Breve Storia di Linux**

Con il termine **Linux** si fa riferimento a una famiglia di sistemi operativi di tipo **Unix-like**, rilasciati sotto varie distribuzioni, aventi la caratteristica comune di utilizzare come nucleo il **kernel Linux**.

Il kernel Linux fu scritto nell'**agosto 1991** da **Linus Torvalds**, giovane studente finlandese dell'Università di Helsinki. Torvalds era insoddisfatto di **Minix** — un sistema operativo Unix-like destinato alla didattica scritto da Andrew Tanenbaum, professore all'Università di Amsterdam — perché supportava male la nuova architettura **i386 a 32 bit**, all'epoca economica e popolare.

Inizialmente Linux utilizzava, oltre al kernel di Torvalds, l'*userspace* di Minix. Successivamente, poiché la licenza di Minix ne permetteva l'uso solo a fini didattici, Torvalds decise di:
1. Rendere il sistema indipendente da Minix.
2. Sostituire la parte applicativa con il software del progetto **GNU**.
3. Adottare la licenza **GPL** (*GNU General Public License*), che garantisce la libera distribuzione e modifica del codice.

Nel corso degli anni si unirono a Torvalds migliaia di sviluppatori da tutto il mondo. Oggi Linux è disponibile in circa **300 diverse distribuzioni** — che differiscono per interfaccia grafica e applicazioni preinstallate — ma condividono tutte il medesimo kernel Linux.

---

### **2. Il Filesystem di Linux**

#### **2.1. Tipologie di File**

In Linux esistono tre tipologie di file:

| Tipo | Descrizione |
|------|-------------|
| **Ordinari** | Archivi di dati, comandi, programmi sorgente, eseguibili, ecc. |
| **Directory** (cartelle) | File speciale contenente riferimenti ad altri file (ordinari, speciali o altre directory) |
| **Speciali** | Interfaccia ai driver di dispositivi hardware: memoria centrale, hard disk, ecc. |

#### **2.2. Struttura ad Albero**

I file sono organizzati in una **struttura gerarchica ad albero**:
- La **radice** (`/`) è una directory.
- I nodi successivi possono essere directory, file di dati o file speciali.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. Path (Percorso)**

Per identificare un nodo all'interno della gerarchia si usa il **path** (*pathname*): una sequenza di nomi di nodi separati da `/`.

Si distinguono due tipi:
- **Path assoluto:** parte dalla radice `/`. Es: `/home/braghin/lab1/slide`
- **Path relativo:** parte dalla directory corrente. Es (se si è in `/home/braghin`): `lab1/slide`

> ⚠️ Linux è **case sensitive**: `Prova`, `pRova`, `prova` e `PROVA` sono quattro file distinti.

#### **2.4. Nomi Speciali di Directory**

| Simbolo | Significato |
|---------|-------------|
| `.` | La directory corrente |
| `..` | La directory madre (un livello sopra) |
| `~` | La home directory dell'utente corrente |

#### **2.5. Directory Standard sempre Presenti**

| Directory | Contenuto |
|-----------|-----------|
| `/` | Directory radice del filesystem |
| `/usr` | Software applicativo installato |
| `/home` | Dati degli utenti (una sottodirectory per utente) |
| `/dev` | Interfacce ai driver dei dispositivi hardware |

#### **2.6. Prefissi in `/dev` per Identificare i Dispositivi**

| Prefisso | Dispositivo |
|----------|-------------|
| `fd` | Floppy disk oppure file descriptor |
| `hd` | Disco di tipo IDE (`sd` nel caso SCSI) |
| `hda` | Primo disco IDE (`hda1` = prima partizione logica) |
| `hdb` | Secondo disco IDE |
| `lp` | Stampante (*line printer*) |
| `tty` | Terminali |

---

### **3. La Shell di Linux**

Una **shell** (detta anche *terminale* o *interprete di comandi*) è un programma che gestisce l'interazione tra l'utente e il sistema operativo: permette di impartire comandi, avviare programmi e gestire il sistema. È una delle componenti fondamentali di un SO, insieme al kernel.

#### **3.1. Tipi di Shell**

- **Testuale:** l'utente interagisce tramite un'interfaccia a **riga di comando** (CLI), digitando comandi su un terminale o emulatore di terminale.
- **Grafica:** ambienti grafici (*desktop environment*) come X-Windows, che forniscono un'interfaccia visuale per gestire file e avviare programmi.

#### **3.2. Ciclo di Interazione (CLI)**

1. L'utente apre un terminale.
2. Il sistema operativo mostra il **prompt** e rimane in attesa di un comando.
3. L'utente digita un comando e preme **Invio**: il comando viene eseguito, mostrando eventuale output sul terminale.
4. Al termine dell'esecuzione ricompare il prompt.

**Per terminare la shell:** digitare `logout` o `exit`, oppure premere `Ctrl-D`.

#### **3.3. Il Prompt**

Il simbolo del prompt varia a seconda del tipo di utente:

| Utente | Prompt di default |
|--------|------------------|
| Utente normale | `$` |
| Root (amministratore) | `#` |

In molti sistemi Linux il prompt è preceduto da informazioni contestuali: nome del computer, directory corrente e nome dell'utente. Es: `chiara@MacBook-Pro:~$`

---

### **4. Comandi Principali**

#### **4.1. Convenzioni di Sintassi**

La sintassi generale di un comando Linux è:

```
nomeComando [opzioni...] [argomenti...]
```

Convenzioni usate nella documentazione (e nel comando `man`):

| Notazione | Significato |
|-----------|-------------|
| `[opzione]` | L'opzione può essere omessa |
| `arg1 \| arg2` | Le due opzioni sono mutuamente esclusive |
| `arg...` | L'argomento può essere ripetuto n volte |

---

#### **4.2. Il Manuale: `man`**

> 📌 Il comando più importante da conoscere è `man`: legge la pagina del manuale relativa a qualsiasi comando.

```bash
man nome_comando
```

Il manuale fornisce:
- Formato del comando (input) e risultato atteso (output).
- Descrizione di tutte le opzioni disponibili.
- Eventuali restrizioni e file di sistema interessati.
- Comandi correlati ed eventuali bug noti.

Per **uscire dal manuale**: digitare `:q` (*quit*, come nell'editor `vi`).

---

#### **4.3. Informazioni su File e Directory: `ls`**

```bash
ls [opzioni...] [file...]
```

Elenca informazioni su file e contenuto delle directory. Principali opzioni:

| Opzione | Significato |
|---------|-------------|
| `-l` | *Long format*: nome, proprietario, gruppo, dimensione, data e ora dell'ultima modifica |
| `-a` | *All files*: mostra anche i file nascosti (il cui nome inizia con `.`) |
| `-t` | Ordina per data dell'ultima modifica |
| `-u` | Ordina per data dell'ultimo accesso |
| `-r` | *Reverse order*: inverte l'ordine alfabetico |

Le opzioni si possono combinare: `ls -la` mostra tutti i file (inclusi nascosti) in formato lungo.

---

#### **4.4. Creazione e Rimozione di Directory: `mkdir` / `rmdir`**

```bash
mkdir d1       # crea la directory d1
rmdir d1       # rimuove la directory d1 (deve essere vuota)
```

---

#### **4.5. Copia di File: `cp`**

```bash
cp f1 d1
```

Copia il file `f1` nella directory `d1`. È possibile copiare più file contemporaneamente:

```bash
cp f1 f2 f3 d1     # copia f1, f2, f3 nella directory d1
```

Se il secondo argomento è una directory, i file vengono copiati al suo interno mantenendo il nome originale.

---

#### **4.6. Rinomina e Spostamento: `mv`**

```bash
mv f1 f2
```

- Se `f2` è un **nome di file**: rinomina `f1` in `f2`.
- Se `f2` è una **directory esistente**: sposta `f1` all'interno di `f2`.

---

#### **4.7. Navigazione nel Filesystem: `cd` e `pwd`**

```bash
cd dir       # sposta nella directory dir
cd           # senza argomenti: torna alla home directory
cd ..        # sale di un livello (directory madre)
pwd          # mostra il path assoluto della directory corrente (present working directory)
```

---

#### **4.8. Visualizzazione del Contenuto di un File**

Tutti i comandi seguenti prendono come argomento il nome del file:

| Comando | Comportamento |
|---------|---------------|
| `cat file` | Stampa l'intero contenuto del file sullo schermo in una volta sola |
| `more file` | Mostra il contenuto una schermata alla volta; spazio = avanza, `q` = esce |
| `tail file` | Mostra le ultime righe del file (di default le ultime 10) |
| `head file` | Mostra le prime righe del file (di default le prime 10) |

> 💡 **Differenza pratica:** `cat` è utile per file corti; `more` per file lunghi; `tail` per leggere la fine di log di sistema in tempo reale.

---

#### **4.9. Metacaratteri (Wildcard)**

La shell riconosce caratteri speciali (*wildcard*) nei nomi di file, espandendoli prima di eseguire il comando:

| Metacarattere | Significato |
|---------------|-------------|
| `*` | Qualsiasi stringa di zero o più caratteri |
| `?` | Esattamente un carattere qualsiasi |
| `[zfc]` | Esattamente uno dei caratteri elencati tra le parentesi |
| `[a-d]` | Esattamente un carattere nel range indicato |
| `{}` | Una stringa tra quelle elencate tra le graffe |
| `\` | *Escape*: il carattere successivo viene trattato letteralmente, non come speciale |

**Esempi:**

```bash
ls [q-s]*
# elenca i file il cui nome inizia con un carattere compreso tra q e s

ls [a-d,0-9]*[c,g,l]?
# elenca i file il cui nome:
#   - inizia con un carattere tra a-d oppure tra 0-9
#   - ha come penultimo carattere c, g o l
```

---

#### **4.10. Ridirezione dell'I/O**

Di default, i comandi Linux leggono l'input da tastiera (*stdin*) e scrivono l'output e gli errori su video (*stdout*, *stderr*). È possibile redirigere entrambi:

| Sintassi | Effetto |
|----------|---------|
| `comando < fileInput` | Il comando legge l'input dal file anziché da tastiera |
| `comando > fileOutput` | L'output va nel file (creato o sovrascritto) anziché su video |
| `comando >> fileOutput` | L'output viene **accodato** in fondo al file (senza sovrascrivere) |

**Esempi:**

```bash
ls -l > fileListato
# il risultato di "ls -l" viene salvato in fileListato

sort < file > file2
# ordina il contenuto di "file" e scrive il risultato in "file2"
```

---

#### **4.11. Piping (`|`)**

L'operatore **pipe** (`|`) concatena due comandi: l'output del primo diventa l'input del secondo, **senza file temporanei**.

```bash
comando1 | comando2
```

> 💡 **Suggerimento pratico:** costruire le pipe aggiungendo un comando alla volta, verificando l'output intermedio a ogni passaggio.

**Esempi:**

```bash
who | wc -l > numUtenti
# "who" elenca gli utenti connessi
# "wc -l" conta le righe (= numero di utenti)
# il risultato viene scritto in numUtenti

ls | rev
# elenca i file della directory corrente con i nomi invertiti carattere per carattere
```

---

#### **4.12. Autocompletamento con Tab**

La shell **Bash** offre autocompletamento: digitando l'inizio di un comando e premendo **Tab due volte**, viene mostrato l'elenco di tutti i comandi che iniziano con i caratteri digitati. Funziona anche per nomi di file e directory.

---

### **5. Esercizi con Soluzioni**

Gli esercizi seguenti richiedono l'uso della shell Linux. Per opzioni non elencate in questa dispensa, consultare `man nome_comando`.

---

#### **Esercizio 1 — Listato completo con `ls -la`**

**Testo:** Digitare `ls -la`. Se l'elenco è troppo lungo, visualizzarlo a schermate con `ls -la | more`.

**Soluzione:**
```bash
ls -la
# oppure, se l'output è lungo:
ls -la | more
```

`-l` attiva il formato lungo (permessi, proprietario, dimensione, data); `-a` mostra anche i file nascosti (quelli il cui nome inizia con `.`, come `.bashrc`, `.profile`).

Con `more`: la barra spaziatrice avanza alla schermata successiva; `q` esce.

---

#### **Esercizio 2 — Esplorazione del Filesystem**

**Testo:** Qual è il pathname della propria home directory? Quali sono le sotto-directory di `/`?

**Soluzione:**
```bash
# Pathname della home directory:
echo ~
# oppure:
pwd     # dopo essersi spostati in ~ con: cd ~

# Sotto-directory di /:
ls /
```

Tipiche sotto-directory di `/` in un sistema Linux: `bin`, `boot`, `dev`, `etc`, `home`, `lib`, `media`, `mnt`, `opt`, `proc`, `root`, `run`, `srv`, `sys`, `tmp`, `usr`, `var`.

---

#### **Esercizio 3 — Differenze tra `cat`, `more` e `tail`**

**Testo:** Che differenza c'è tra i comandi `cat`, `more` e `tail`?

**Soluzione:**

| Comando | Comportamento |
|---------|---------------|
| `cat file` | Stampa **tutto** il contenuto del file in una volta sola. Utile per file corti o per concatenare file (`cat f1 f2 > f3`). |
| `more file` | Mostra il contenuto **una schermata alla volta**. Utile per file lunghi: spazio = avanza, `q` = esce. |
| `tail file` | Mostra solo le **ultime righe** del file (default: ultime 10). Con `-f` segue il file in tempo reale (utile per log di sistema). |

---

#### **Esercizio 4 — Comportamento di `mv` e `cp` quando `f2` è una directory**

**Testo:** Cosa succede se `f2` è una directory nei comandi `mv f1 f2` e `cp f1 f2`?

**Soluzione:**

- `mv f1 f2` (con `f2` directory): **sposta** il file `f1` all'interno della directory `f2`, mantenendo il nome `f1`. Equivale a: `mv f1 f2/f1`.
- `cp f1 f2` (con `f2` directory): **copia** il file `f1` all'interno della directory `f2`, mantenendo il nome `f1`. Equivale a: `cp f1 f2/f1`.

Se invece `f2` è un nome di file inesistente, `mv` rinomina e `cp` crea una copia con quel nome.

---

#### **Esercizio 5 — Creare un file con la lista ordinata dei file**

**Testo:** Creare un file `lista-ordinata` che contiene la lista ordinata dei file della directory corrente.

**Soluzione:**
```bash
ls | sort > lista-ordinata
```

`ls` elenca i file; `sort` li ordina alfabeticamente; `>` redirige l'output nel file `lista-ordinata`.

---

#### **Esercizio 6 — File della home ordinati per data di ultima modifica**

**Testo:** Visualizzare i file della propria home directory ordinati in base alla data di ultima modifica.

**Soluzione:**
```bash
ls -lt ~
```

`-l` = formato lungo (mostra la data), `-t` = ordina per data di ultima modifica (dal più recente al meno recente). Per ordine inverso (dal più vecchio): `ls -ltr ~`.

---

#### **Esercizio 7 — Lista ordinata dei file `.txt`**

**Testo:** Dopo aver creato almeno 5 file con estensione `.txt`, creare un file `lista-file` con la lista ordinata dei file `.txt` della directory corrente.

**Soluzione:**

```bash
# Creazione di 5 file .txt di prova (con touch):
touch file1.txt file2.txt file3.txt file4.txt file5.txt

# Creazione della lista ordinata dei soli .txt:
ls *.txt | sort > lista-file
```

Il metacarattere `*.txt` espande a tutti i file con estensione `.txt` nella directory corrente. La pipe con `sort` garantisce l'ordine alfabetico. Il risultato è salvato in `lista-file`.

---

### **6. Riepilogo**

> ✅ Linux è un sistema operativo Unix-like open source nato nel 1991. Il filesystem è organizzato ad albero con radice `/`; i path possono essere assoluti (dalla radice) o relativi (dalla directory corrente). La shell (testuale o grafica) interpreta i comandi nella forma `comando [opzioni] [argomenti]`. I comandi fondamentali sono: `ls` (elencare), `cd`/`pwd` (navigare), `mkdir`/`rmdir` (creare/rimuovere directory), `cp`/`mv` (copiare/spostare), `cat`/`more`/`tail`/`head` (visualizzare file). Metacaratteri (`*`, `?`, `[]`), ridirezione (`<`, `>`, `>>`) e piping (`|`) permettono di combinare comandi in modo potente.
