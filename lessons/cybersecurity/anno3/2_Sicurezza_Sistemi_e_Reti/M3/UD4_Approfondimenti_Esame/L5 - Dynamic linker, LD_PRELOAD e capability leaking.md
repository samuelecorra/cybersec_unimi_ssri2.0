# **UD4 - Dynamic linker, LD_PRELOAD e capability leaking**

Fonti integrate: `Lab5.pdf`, sezioni su capability leaking di `Lab3.pdf`, task 7 e 9 di `Environment_Variable_and_SetUID.pdf`.

### **1. Collegamento statico e dinamico**

Il linking collega il codice del programma con il codice delle librerie esterne, ad esempio `printf()` in libc.

Esistono due approcci:

1. **collegamento statico**: il codice delle librerie viene incorporato nell'eseguibile;
2. **collegamento dinamico**: il codice delle librerie condivise viene caricato a runtime.

Il collegamento statico produce eseguibili molto più grandi. Le slide indicano che un programma compilato staticamente può essere anche cento volte più grande di uno dinamico in esempi minimali.

Il collegamento dinamico risparmia spazio e memoria, ma lascia una parte della risoluzione del codice al momento dell'esecuzione.

> 📌 Il dynamic linking è efficiente, ma introduce una domanda di sicurezza: chi controlla quali librerie vengono caricate prima dell'esecuzione del programma?

### **2. Dynamic linker**

Su Linux il dynamic loader/linker è tipicamente `ld.so` o `ld-linux.so`. Viene invocato prima del `main()` del programma e risolve le dipendenze da librerie condivise.

Per vedere le librerie richieste da un programma:

```bash
ldd ./myprog
```

L'output mostra librerie come:

```text
linux-vdso.so.1
libc.so.6
/lib64/ld-linux-x86-64.so.2
```

La libc contiene funzioni come `printf()` e `sleep()`. Il dynamic linker stesso è parte essenziale del processo di avvio del programma dinamico.

### **3. Variabili `LD_PRELOAD` e `LD_LIBRARY_PATH`**

Due variabili d'ambiente sono centrali:

| Variabile | Effetto |
|---|---|
| `LD_PRELOAD` | indica librerie condivise da caricare prima delle altre |
| `LD_LIBRARY_PATH` | indica directory in cui cercare librerie prima dei percorsi standard |

Poiché possono essere impostate dall'utente, permettono di influenzare il processo di linking dinamico.

> ⚠️ Se un programma Set-UID accettasse liberamente `LD_PRELOAD`, un utente potrebbe iniettare una libreria malevola eseguita con EUID privilegiato.

### **4. Caso di studio: override di `sleep()`**

Il PDF SEED propone di creare una libreria che ridefinisce `sleep()`:

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
```

Poi si imposta:

```bash
export LD_PRELOAD=./libmylib.so.1.0.1
```

Programma vittima:

```c
#include <unistd.h>

int main()
{
    sleep(1);
    return 0;
}
```

Se il programma è normale, il dynamic linker carica la libreria indicata in `LD_PRELOAD` e la chiamata a `sleep()` viene intercettata.

### **5. Cosa cambia con Set-UID**

Il PDF chiede di provare più scenari:

1. `myprog` normale, eseguito da utente normale;
2. `myprog` Set-UID root, eseguito da utente normale;
3. `myprog` Set-UID root, con `LD_PRELOAD` impostata dall'account root;
4. `myprog` Set-UID di un altro utente, eseguito da un utente differente.

L'osservazione attesa è che il comportamento cambia quando EUID e RUID differiscono. Il dynamic linker applica una contromisura: ignora o sanitizza variabili `LD_*` in esecuzioni privilegiate, proprio per impedire iniezioni di librerie.

> 📌 Quando EUID e RUID differiscono, il loader entra in modalità più restrittiva e non tratta l'ambiente come in un programma ordinario.

### **6. Verifica con copia di `env`**

Le slide suggeriscono di creare una copia del programma `env`, renderla Set-UID e confrontare:

```bash
cp /usr/bin/env ./myenv
sudo chown root:root myenv
sudo chmod 4755 myenv
export LD_PRELOAD=./libmylib.so.1.0.1
export LD_LIBRARY_PATH=/tmp
/usr/bin/env | grep LD_
./myenv | grep LD_
```

Nel programma normale le variabili possono comparire; nel programma Set-UID possono sparire o essere ignorate.

> ⚠️ Questa è una mitigazione del sistema, non una licenza per scrivere programmi Set-UID disattenti. Il codice privilegiato deve comunque ripulire l'ambiente.

### **7. Dynamic linker e OS X Yosemite**

Il PDF cita un caso di studio su OS X Yosemite 10.10, divulgato nel luglio 2015. Apple introdusse una variabile:

```text
DYLD_PRINT_TO_FILE
```

che permetteva di indicare un file su cui il dynamic linker `dyld` scriveva informazioni diagnostiche. Il problema era la combinazione con programmi Set-UID root: il linker poteva aprire un file protetto con privilegi elevati e non chiudere correttamente il descrittore.

Lo scenario didattico:

1. l'utente imposta `DYLD_PRINT_TO_FILE` verso un file protetto, ad esempio `/etc/sudoers`;
2. esegue un programma Set-UID root;
3. il linker apre il file con privilegi root;
4. il programma declassa i privilegi ma conserva il descrittore aperto;
5. il processo non privilegiato riesce ancora a scrivere tramite quel descrittore.

> 📌 Il caso OS X mostra che anche una variabile apparentemente diagnostica può diventare una primitiva di escalation se interagisce con programmi privilegiati.

### **8. Capability leaking**

Il capability leaking si verifica quando un processo perde i privilegi formali, ma conserva una capacità privilegiata acquisita prima del declassamento.

Esempio generale:

1. processo Set-UID root apre un file protetto;
2. ottiene un file descriptor valido;
3. chiama `setuid(getuid())` e diventa non privilegiato;
4. non chiude il file descriptor;
5. il codice successivo, pur non essendo più root, può ancora scrivere sul file tramite il descrittore già aperto.

> ⚠️ Il privilegio non è solo l'EUID corrente. Può essere incorporato in risorse già acquisite: file descriptor, socket, capability, handle e mapping.

### **9. Esempio `cap_leak.c`**

Il file `cap_leak.c` del pacchetto `Labsetup-setuid.zip` è:

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

Prima del lab si crea `/etc/zzz`, di proprietà di root e non scrivibile dall'utente ordinario:

```bash
sudo touch /etc/zzz
sudo chown root:root /etc/zzz
sudo chmod 0644 /etc/zzz
```

Poi:

```bash
gcc cap_leak.c -o cap_leak
sudo chown root:root cap_leak
sudo chmod 4755 cap_leak
./cap_leak
```

Il programma apre `/etc/zzz` mentre è root, stampa il numero del file descriptor, declassa i privilegi e lancia una shell. Nella shell non privilegiata, il descriptor resta aperto. Se il descriptor stampato è `3`, si può scrivere:

```bash
echo "test" >&3
```

La scrittura riesce non perché l'utente abbia permesso su `/etc/zzz`, ma perché il processo conserva un canale già aperto da root.

### **10. Come correggere il capability leaking**

La correzione minima è chiudere il descriptor prima di perdere privilegi o prima di consegnare il controllo a codice non fidato:

```c
close(fd);
setuid(getuid());
```

Più in generale:

1. chiudere file descriptor non necessari;
2. impostare `FD_CLOEXEC` sui descriptor che non devono sopravvivere a `execve()`;
3. declassare i privilegi prima di aprire risorse non necessarie;
4. evitare di lanciare shell dopo aver acquisito capacità privilegiate;
5. controllare esplicitamente cosa viene ereditato dai processi figli.

### **11. Collegamento con il programma `su`**

Le slide usano `su` come esempio concettuale: `su` parte come programma Set-UID root, verifica la password e poi cambia identità verso l'utente richiesto. Dopo la verifica, sia RUID sia EUID possono diventare quelli del nuovo utente.

Se prima del declassamento `su` conservasse risorse root aperte e le lasciasse disponibili al nuovo contesto, si avrebbe capability leaking.

### **12. Contromisure di progettazione**

Per programmi privilegiati:

1. ridurre al minimo le operazioni eseguite con EUID elevato;
2. pulire l'ambiente all'avvio;
3. non fidarsi di variabili `LD_*` o simili;
4. compilare staticamente solo quando ha senso e non come falsa soluzione universale;
5. chiudere o marcare `close-on-exec` ogni descriptor non necessario;
6. testare il comportamento con RUID/EUID differenti.

> ✅ **Ricapitolando** - Il dynamic linker rende efficiente l'esecuzione dei programmi, ma variabili come `LD_PRELOAD` e `LD_LIBRARY_PATH` possono modificare il codice caricato a runtime. Linux mitiga il rischio ignorando variabili `LD_*` quando EUID e RUID differiscono, ma resta necessario progettare i programmi privilegiati con ambiente minimo. Il capability leaking è un errore distinto: il processo perde l'EUID privilegiato ma conserva risorse aperte mentre era root, come un file descriptor verso un file protetto.

Collegamenti: [L2 - Programmi privilegiati e Set-UID](L2%20-%20Programmi%20privilegiati%20e%20Set-UID.md), [L4 - Attacchi a programmi Set-UID tramite ambiente e PATH](L4%20-%20Attacchi%20a%20programmi%20Set-UID%20tramite%20ambiente%20e%20PATH.md), [L7 - Laboratorio SEED Environment Variable and Set-UID](L7%20-%20Laboratorio%20SEED%20Environment%20Variable%20and%20Set-UID.md).
