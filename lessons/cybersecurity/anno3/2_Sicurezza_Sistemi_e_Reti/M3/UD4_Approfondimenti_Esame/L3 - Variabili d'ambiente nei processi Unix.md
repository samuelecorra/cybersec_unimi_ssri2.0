# **UD4 - Variabili d'ambiente nei processi Unix**

Fonti integrate: `Lab4.pdf` e task 1-5 di `Environment_Variable_and_SetUID.pdf`.

### **1. Definizione e ruolo**

Le variabili d'ambiente sono valori dinamici nominati che fanno parte dell'ambiente operativo di un processo. Sono state introdotte in Unix e sono presenti anche in altri sistemi, incluso Windows.

Esempi tipici:

```bash
PATH
HOME
PWD
USER
SHELL
LANG
LD_PRELOAD
LD_LIBRARY_PATH
```

Influenzano il comportamento dei processi senza comparire necessariamente come argomenti espliciti del programma. Proprio per questo sono importanti in sicurezza: un programma può dipendere da variabili che il programmatore non considera input.

> 📌 Una variabile d'ambiente è input implicito. Se un utente può impostarla prima di lanciare un programma, allora fa parte della superficie d'attacco di quel programma.

### **2. Ispezionare e modificare l'ambiente**

Per stampare tutte le variabili d'ambiente:

```bash
printenv
env
```

Per osservarne una sola:

```bash
printenv PWD
env | grep PWD
```

Per definire una variabile d'ambiente in `bash`:

```bash
export NOME=valore
```

Per rimuoverla:

```bash
unset NOME
```

`export` e `unset` non sono programmi esterni: sono comandi interni della shell.

### **3. Variabili di shell e variabili d'ambiente**

Le slide insistono su una distinzione spesso confusa.

Una **variabile di shell** è interna alla shell corrente:

```bash
FOO=bar
```

Una **variabile d'ambiente** viene esportata verso i processi figli:

```bash
export FOO=bar
```

Se si esegue:

```bash
FOO=bar
env | grep FOO
```

di norma `FOO` non compare, perché è solo variabile di shell. Se invece:

```bash
export FOO=bar
env | grep FOO
```

allora `FOO` viene ereditata dal processo figlio che esegue `env`.

> 💡 La shell conserva molte variabili per sé. Solo quelle esportate diventano ambiente dei processi figli.

### **4. Accesso alle variabili d'ambiente da C**

Un programma C può accedere all'ambiente in più modi. Le slide indicano due strade:

1. tramite il terzo argomento di `main`, spesso chiamato `envp`;
2. tramite la variabile globale `environ`, considerata più affidabile quando l'ambiente può cambiare.

Esempio:

```c
#include <stdio.h>

extern char **environ;

int main(void)
{
    for (int i = 0; environ[i] != NULL; i++) {
        printf("%s\n", environ[i]);
    }
    return 0;
}
```

Le slide sottolineano che `envp` ed `environ` inizialmente puntano allo stesso ambiente, ma se il programma modifica le variabili d'ambiente, la struttura può essere spostata nello heap e `environ` viene aggiornato, mentre `envp` può restare legato alla posizione iniziale.

### **5. Ereditarietà tramite `fork()`**

In Unix, `fork()` crea un processo figlio duplicando il processo chiamante. Il figlio non eredita proprio tutto, ma eredita l'ambiente.

Il file `myprintenv.c` del pacchetto `Labsetup-setuid.zip` stampa l'ambiente nel figlio o nel padre:

```c
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

extern char **environ;

void printenv()
{
  int i = 0;
  while (environ[i] != NULL) {
     printf("%s\n", environ[i]);
     i++;
  }
}

void main()
{
  pid_t childPid;
  switch(childPid = fork()) {
    case 0:
      printenv();
      exit(0);
    default:
      // printenv();
      exit(0);
  }
}
```

Compilazione:

```bash
gcc myprintenv.c -o myprintenv
./myprintenv > child.env
```

Poi si commenta la `printenv()` del figlio e si decommenta quella del padre:

```bash
gcc myprintenv.c -o myprintenv
./myprintenv > parent.env
diff child.env parent.env
```

Conclusione attesa: il processo figlio riceve una copia dell'ambiente del padre.

> 📌 `fork()` conserva l'ambiente: un programma lanciato da una shell parte già con le variabili esportate dalla shell.

### **6. `execve()` e ambiente**

`execve()` non crea un nuovo processo: sostituisce il programma corrente con un nuovo eseguibile nello stesso processo. Codice, dati, bss e stack vengono rimpiazzati dal nuovo programma.

La firma concettuale è:

```c
execve(path, argv, envp);
```

Il terzo argomento decide l'ambiente del nuovo programma.

Nel file `myenv.c`:

```c
#include <unistd.h>

extern char **environ;

int main()
{
  char *argv[2];

  argv[0] = "/usr/bin/env";
  argv[1] = NULL;

  execve("/usr/bin/env", argv, NULL);

  return 0;
}
```

Con `envp = NULL`, `/usr/bin/env` non riceve l'ambiente del chiamante. Se si cambia la riga in:

```c
execve("/usr/bin/env", argv, environ);
```

il nuovo programma riceve l'ambiente corrente.

> ⚠️ L'ambiente non passa magicamente attraverso `execve()`: è il chiamante a scegliere cosa fornire come terzo argomento.

### **7. `system()` e ambiente**

`system()` è diversa da `execve()`: non esegue direttamente il comando. Invoca:

```text
/bin/sh -c comando
```

La shell riceve l'ambiente del processo chiamante e poi interpreta la stringa di comando. Il PDF SEED propone:

```c
#include <stdio.h>
#include <stdlib.h>

int main()
{
    system("/usr/bin/env");
    return 0;
}
```

L'esecuzione mostra che l'ambiente arriva alla shell e quindi al comando.

Questa differenza è cruciale per la sicurezza: `system()` introduce un interprete intermedio influenzato da variabili come `PATH`, `IFS`, `SHELL` e da metacaratteri nella stringa.

### **8. `/proc` e ambiente dei processi**

Linux espone molte informazioni sui processi tramite il filesystem virtuale `/proc`. Ogni processo ha una directory:

```text
/proc/<PID>/
```

Il file:

```text
/proc/<PID>/environ
```

contiene l'ambiente del processo, con variabili separate da byte nulli. Per visualizzarlo:

```bash
strings /proc/$$/environ
```

`$$` viene sostituito dalla shell con il proprio PID.

> 💡 Quando si esegue `env` dalla shell, `env` gira in un processo figlio: stampa l'ambiente del figlio, che normalmente è una copia dell'ambiente esportato dalla shell madre.

### **9. Variabili d'ambiente e programmi Set-UID**

Il PDF SEED chiede di verificare quali variabili arrivano dentro un programma Set-UID. Il programma è:

```c
#include <stdio.h>
#include <stdlib.h>

extern char **environ;

int main()
{
    int i = 0;
    while (environ[i] != NULL) {
        printf("%s\n", environ[i]);
        i++;
    }
}
```

Compilazione e trasformazione in Set-UID:

```bash
gcc printenv.c -o foo
sudo chown root:root foo
sudo chmod 4755 foo
```

Poi, da utente normale:

```bash
export PATH=/tmp:$PATH
export LD_LIBRARY_PATH=/tmp
export ANY_NAME=qualunque
./foo
```

L'osservazione didattica è che molte variabili arrivano nel processo Set-UID, ma alcune variabili sensibili, in particolare quelle che influenzano il dynamic linker (`LD_*`), possono essere rimosse o ignorate dal sistema quando EUID e RUID differiscono.

> ⚠️ Non bisogna concludere che "l'ambiente è sempre filtrato". Il sistema filtra alcune variabili note e pericolose, ma molte altre variabili arbitrarie arrivano comunque al programma privilegiato.

### **10. Superficie d'attacco nascosta**

Le slide chiudono con il punto centrale: l'uso nascosto delle variabili di ambiente è pericoloso. Un programma può apparire deterministico guardando solo codice e argomenti, ma cambiare comportamento perché eredita:

1. `PATH`, per la ricerca dei comandi;
2. variabili di localizzazione;
3. variabili di configurazione dell'interprete;
4. variabili usate da librerie;
5. variabili arbitrarie lette direttamente dal programma.

> 📌 Nei programmi privilegiati, ogni dipendenza dall'ambiente deve essere esplicita, ridotta e controllata.

### **11. Regole difensive**

Per programmi Set-UID o servizi privilegiati:

1. non fidarsi dell'ambiente ereditato;
2. usare `clearenv()` o costruire un ambiente minimo;
3. impostare `PATH` a un valore noto, se proprio serve;
4. preferire `execve()` con `envp` controllato;
5. evitare `system()`;
6. documentare quali variabili sono accettate.

> ✅ **Ricapitolando** - Le variabili d'ambiente sono un canale di input implicito. `fork()` le eredita, `execve()` le passa solo se il chiamante le fornisce, mentre `system()` invoca una shell che eredita l'ambiente e interpreta il comando. Nei programmi Set-UID alcune variabili pericolose possono essere filtrate dal loader, ma molte altre restano disponibili: per questo l'ambiente va considerato superficie d'attacco.

Collegamenti: [L2 - Programmi privilegiati e Set-UID](L2%20-%20Programmi%20privilegiati%20e%20Set-UID.md), [L4 - Attacchi a programmi Set-UID tramite ambiente e PATH](L4%20-%20Attacchi%20a%20programmi%20Set-UID%20tramite%20ambiente%20e%20PATH.md), [L7 - Laboratorio SEED Environment Variable and Set-UID](L7%20-%20Laboratorio%20SEED%20Environment%20Variable%20and%20Set-UID.md).
