# **UD4 - Attacchi a programmi Set-UID tramite ambiente e PATH**

Fonti integrate: `Lab3.pdf`, `Lab6.pdf`, task 6 e 8 di `Environment_Variable_and_SetUID.pdf`, e parti offensive della precedente lezione Set-UID.

### **1. Classi di input pericolose**

Le slide distinguono tre categorie di input che possono compromettere programmi privilegiati:

1. **input espliciti dell'utente**, come argomenti, standard input, file o richieste di rete;
2. **input di sistema**, come file temporanei, link simbolici e oggetti in directory scrivibili da tutti;
3. **input ambientali**, cioè variabili d'ambiente ereditate dal processo.

Gli input espliciti portano a vulnerabilità classiche come buffer overflow e format string. Gli input di sistema portano a race condition, per esempio quando un programma privilegiato segue un link simbolico in una directory come `/tmp`. Le variabili d'ambiente portano invece ad attacchi meno visibili, perché il dato malevolo non compare necessariamente nel codice sorgente come parametro diretto.

> 📌 Nei programmi Set-UID è pericoloso tutto ciò che l'utente reale controlla mentre il processo opera con EUID privilegiato.

### **2. Attacco tramite `PATH`**

`PATH` è usata dalla shell per trovare un comando quando il programma non specifica un percorso assoluto.

Se un programma Set-UID contiene:

```c
#include <stdlib.h>

int main()
{
    system("ls");
    return 0;
}
```

il programmatore pensa di eseguire `/bin/ls`, ma in realtà `system()` invoca:

```text
/bin/sh -c "ls"
```

La shell cerca `ls` nelle directory elencate in `PATH`.

Un attaccante può creare un falso `ls`:

```bash
mkdir -p /tmp/evil
cat > /tmp/evil/ls << 'EOF'
#!/bin/sh
/bin/sh
EOF
chmod +x /tmp/evil/ls
export PATH=/tmp/evil:$PATH
```

Se il binario Set-UID root invoca `system("ls")` e la shell non declassa i privilegi, verrà eseguito `/tmp/evil/ls` con EUID privilegiato.

> ⚠️ La vulnerabilità non è `PATH` da sola, ma la combinazione: comando senza percorso assoluto, shell, ambiente controllato dall'utente, privilegi elevati.

### **3. Nota su `/bin/sh`, `dash`, `bash` e `zsh`**

I materiali SEED osservano che su Ubuntu 20.04 `/bin/sh` punta a `/bin/dash`. La shell può implementare contromisure quando rileva di essere eseguita in un processo Set-UID.

Nel laboratorio SEED, per osservare l'attacco senza quella contromisura, si usa:

```bash
sudo ln -sf /bin/zsh /bin/sh
```

Questo rende `/bin/sh` un link a `zsh`, che nel contesto del lab non declassa automaticamente l'EUID.

> ⚠️ Modificare `/bin/sh` è una modifica globale alla VM. Va fatta solo in ambiente snapshot e ripristinata al termine del laboratorio.

### **4. Attacchi tramite input utente trasformato in comando**

Il caso di studio del PDF SEED è `catall.c`, pensato per permettere a Bob di leggere file senza modificarli. Il programma è Set-UID root e costruisce una stringa di comando:

```c
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
  char *v[3];
  char *command;

  if(argc < 2) {
    printf("Please type a file name.\n");
    return 1;
  }

  v[0] = "/bin/cat"; v[1] = argv[1]; v[2] = NULL;

  command = malloc(strlen(v[0]) + strlen(v[1]) + 2);
  sprintf(command, "%s %s", v[0], v[1]);

  system(command);
  // execve(v[0], v, NULL);

  return 0 ;
}
```

Il programma dovrebbe eseguire:

```bash
/bin/cat nomefile
```

ma poiché passa una stringa alla shell, l'argomento dell'utente può contenere metacaratteri:

```bash
./catall "file.txt; rm /tmp/vittima"
```

La shell interpreta `;` come separatore di comandi. Una parte dei dati dell'utente diventa codice.

> 📌 Principio di isolamento: non mescolare codice e dati. Se i dati dell'utente entrano in una stringa interpretata dalla shell, possono smettere di essere dati.

### **5. `system()` come approccio non sicuro**

`system()` è comoda perché accetta una stringa, ma proprio questa comodità è il problema:

1. invoca `/bin/sh`;
2. eredita l'ambiente;
3. interpreta metacaratteri;
4. usa `PATH` per comandi relativi;
5. espone il programma a tutte le regole della shell.

In programmi non privilegiati può già essere pericolosa; in programmi Set-UID è una fonte diretta di escalation.

### **6. `execve()` come invocazione più sicura**

La versione sicura separa comando e dati:

```c
v[0] = "/bin/cat";
v[1] = argv[1];
v[2] = NULL;
execve(v[0], v, NULL);
```

Qui il nome del comando è fissato dal programma (`/bin/cat`) e l'input dell'utente è solo un elemento dell'array `argv`. Non esiste una shell che interpreti `;`, `&&`, backtick, `$()` o redirezioni.

Se l'utente passa:

```text
file.txt; rm /tmp/vittima
```

`cat` cercherà un file con quel nome letterale; non eseguirà `rm`.

> 💡 `execve()` non rende automaticamente sicuro ogni programma, ma elimina la classe di bug in cui la shell trasforma dati in codice.

### **7. Attenzione alla famiglia `exec()`**

Le slide aggiungono una distinzione importante: non tutte le funzioni `exec*()` sono equivalenti dal punto di vista della superficie d'attacco.

Più sicure:

```c
execve("/bin/cat", argv, envp);
execl("/bin/cat", "cat", file, NULL);
```

Più delicate:

```c
execlp("cat", "cat", file, NULL);
execvp("cat", argv);
execvpe("cat", argv, envp);
```

Le varianti con `p` cercano il programma nel `PATH`, replicando una parte del comportamento della shell. Se il programma è privilegiato, questa scelta può riaprire la superficie d'attacco.

### **8. Attacchi in altri linguaggi**

Il rischio non riguarda solo C. Le slide citano:

1. Perl, dove alcune forme di `open()` possono passare dalla shell;
2. PHP, con funzioni come `system()`.

Esempio concettuale:

```text
http://localhost/list.php?dir=.;date
```

Se il server costruisce:

```bash
/bin/ls .;date
```

allora la richiesta non si limita a elencare una directory: fa eseguire un secondo comando.

> 📌 La vulnerabilità è indipendente dal linguaggio: nasce quando input non fidato viene concatenato in un comando interpretato.

### **9. Collegamento con XSS, SQL injection e buffer overflow**

Il PDF collega questo principio ad altri attacchi:

1. `system()` vulnerabile: dati utente diventano comandi shell;
2. SQL injection: dati utente diventano frammenti di query SQL;
3. Cross-Site Scripting: dati utente diventano codice JavaScript nel browser;
4. buffer overflow: dati utente sovrascrivono controllo del flusso o memoria critica.

Il principio comune è la violazione della separazione tra codice e dati.

### **10. Privilegio minimo**

Il principio del privilegio minimo conclude il blocco:

1. un programma privilegiato deve avere solo il potere necessario;
2. deve abbandonare temporaneamente o permanentemente i privilegi quando non servono;
3. in Linux si possono usare `seteuid()` e `setuid()`;
4. sistemi diversi offrono meccanismi diversi, ma la logica è la stessa.

> ⚠️ Declassare i privilegi non basta se prima sono state aperte risorse privilegiate e non sono state chiuse. Questo è il tema del capability leaking.

### **11. Contromisure operative**

Per programmi privilegiati:

1. evitare `system()` e `popen()`;
2. usare percorsi assoluti;
3. preferire `execve()` con array `argv` e ambiente controllato;
4. validare gli argomenti come dati, non come frammenti di comando;
5. non usare varianti `exec*p()` se il `PATH` è influenzabile;
6. declassare i privilegi prima di invocare programmi esterni non essenziali;
7. chiudere descrittori privilegiati prima del declassamento.

> ✅ **Ricapitolando** - Gli attacchi tramite ambiente e programmi esterni sfruttano il fatto che un programma Set-UID esegue codice con EUID privilegiato ma riceve input dall'utente reale. `system()` è pericolosa perché invoca una shell, eredita l'ambiente, interpreta metacaratteri e usa `PATH`. `execve()` riduce la superficie d'attacco separando comando, argomenti e ambiente. Il principio generale è non permettere mai che dati non fidati diventino codice.

Collegamenti: [L3 - Variabili d'ambiente nei processi Unix](L3%20-%20Variabili%20d'ambiente%20nei%20processi%20Unix.md), [L5 - Dynamic linker, LD_PRELOAD e capability leaking](L5%20-%20Dynamic%20linker,%20LD_PRELOAD%20e%20capability%20leaking.md), [L8 - Shellshock e ambiente non fidato](L8%20-%20Shellshock%20e%20ambiente%20non%20fidato.md).
