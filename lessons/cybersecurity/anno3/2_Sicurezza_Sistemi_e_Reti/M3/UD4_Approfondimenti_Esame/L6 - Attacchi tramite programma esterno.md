# **UD4 - Attacchi tramite programma esterno**

Fonte integrata: `Lab6.pdf`, con raccordo ai task SEED su `system()` ed `execve()`.

### **1. Perché l'invocazione di programmi esterni è critica**

Un'applicazione può richiamare un programma esterno per delegare un'operazione. Il rischio è che l'applicazione sembri non usare variabili di ambiente o metacaratteri, ma il programma esterno o la shell invocata li usino.

Le superfici di attacco cambiano molto a seconda del meccanismo scelto:

1. famiglia `exec()`, che alla fine usa `execve()` e può eseguire direttamente un programma;
2. `system()`, che invoca una shell;
3. funzioni analoghe in linguaggi di scripting o server-side.

> 📌 Invocare un programma esterno significa allargare il confine di fiducia: non conta solo il codice chiamante, ma anche l'interprete, l'ambiente e il modo in cui comando e argomenti vengono passati.

### **2. `execve()`**

`execve()` esegue direttamente un file specificato dal path:

```c
execve("/bin/ls", argv, envp);
```

Il nome del programma è indicato dal codice chiamante, gli argomenti sono elementi separati dell'array `argv`, e l'ambiente è scelto esplicitamente tramite `envp`.

Se si passa `NULL` come ambiente, il nuovo programma non riceve l'ambiente del chiamante. Se si passa `environ`, lo riceve.

### **3. `system()`**

`system()` non esegue direttamente il comando. Il suo comportamento è equivalente a:

```text
/bin/sh -c comando
```

Quindi:

1. parte una shell;
2. la shell eredita l'ambiente;
3. la shell interpreta la stringa;
4. la shell usa `PATH` se il comando non è assoluto;
5. metacaratteri e separatori hanno significato.

> ⚠️ `system()` va considerata non adatta a programmi privilegiati quando la stringa contiene anche solo in parte dati controllabili dall'utente.

### **4. Caso di studio con `PATH`**

Il codice vulnerabile:

```c
#include <stdlib.h>

int main()
{
    system("ls");
    return 0;
}
```

Il percorso completo non è fornito. La shell cercherà `ls` tramite `PATH`.

Attacco:

```bash
mkdir -p /tmp/labpath
cat > /tmp/labpath/ls << 'EOF'
#!/bin/sh
echo "Comando malevolo eseguito"
/bin/sh
EOF
chmod +x /tmp/labpath/ls
export PATH=/tmp/labpath:$PATH
./programma_setuid
```

Se il programma è Set-UID e la shell mantiene l'EUID, il falso `ls` viene eseguito con privilegi elevati.

### **5. Primo test senza attacco**

Prima di modificare `PATH`, si esegue il programma:

```bash
./programma_setuid
```

L'output deve essere quello di `ls` legittimo.

Poi si modifica `PATH`:

```bash
export PATH=/tmp/labpath:$PATH
./programma_setuid
```

Se compare l'output del falso script, il programma ha usato la ricerca tramite `PATH`.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **6. Perché `execve()` riduce la superficie**

La variante:

```c
char *argv[] = {"/bin/ls", NULL};
execve("/bin/ls", argv, NULL);
```

non chiede alla shell di trovare `ls`. Il path assoluto è scelto dal programma e `PATH` non decide quale eseguibile caricare.

Questo non elimina ogni rischio: `/bin/ls` potrebbe ancora dipendere da librerie, permessi o ambiente se gli viene passato un ambiente non controllato. Però elimina il vettore specifico `PATH` + shell.

### **7. Differenza concettuale tra comando e argomento**

Nel modello sicuro:

1. il comando è deciso dal programmatore;
2. gli argomenti sono dati;
3. l'ambiente è minimo o controllato.

Nel modello insicuro:

1. comando e argomenti sono concatenati in una stringa;
2. la stringa viene interpretata;
3. caratteri come `;`, `|`, `&`, `>`, `<`, `` ` `` e `$()` possono cambiare la semantica.

> 💡 Il problema non è solo "comando sbagliato": è il fatto che il confine tra sintassi e dati viene spostato all'attaccante.

### **8. Superficie d'attacco comparata**

| Meccanismo | Shell | Usa `PATH` | Interpreta metacaratteri | Ambiente esplicito |
|---|---|---|---|---|
| `system("ls")` | sì | sì, se path relativo | sì | no |
| `execve("/bin/ls", argv, NULL)` | no | no | no | sì |
| `execvp("ls", argv)` | no | sì | no | eredita ambiente |

Le varianti `execvp()` e simili non interpretano metacaratteri come la shell, ma cercano l'eseguibile in `PATH`. In programmi privilegiati è preferibile evitare anche questa dipendenza.

### **9. Regole pratiche per l'esame**

Quando in una traccia compare `system()` dentro un programma Set-UID:

1. verificare se il comando è assoluto o relativo;
2. verificare se dati dell'utente sono concatenati nella stringa;
3. verificare se la shell può mantenere l'EUID;
4. proporre `execve()` con path assoluto;
5. spiegare la separazione codice/dati;
6. citare `PATH` come superficie d'attacco se il comando non è assoluto.

> ✅ **Ricapitolando** - L'invocazione di programmi esterni è sicura solo se comando, argomenti e ambiente restano separati e controllati. `system()` amplia la superficie perché passa da `/bin/sh`, eredita l'ambiente e interpreta una stringa. `execve()` con path assoluto e ambiente minimo è la soluzione corretta nei programmi privilegiati.

Collegamenti: [L4 - Attacchi a programmi Set-UID tramite ambiente e PATH](L4%20-%20Attacchi%20a%20programmi%20Set-UID%20tramite%20ambiente%20e%20PATH.md), [L7 - Laboratorio SEED Environment Variable and Set-UID](L7%20-%20Laboratorio%20SEED%20Environment%20Variable%20and%20Set-UID.md).
