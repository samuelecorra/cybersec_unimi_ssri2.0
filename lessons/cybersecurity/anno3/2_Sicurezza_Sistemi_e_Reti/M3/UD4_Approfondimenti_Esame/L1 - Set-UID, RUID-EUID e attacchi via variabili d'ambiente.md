# **UD4 – Approfondimenti d'esame (Set-UID e attacchi ai programmi privilegiati)**

Questa unità integrativa nasce dall'analisi sistematica degli appelli scritti di Sicurezza dei Sistemi e delle Reti (Cimato–Anisetti). La **prima domanda di ogni appello** riguarda i programmi **Set-UID**: non basta conoscere la definizione teorica di RUID/EUID (già presente in [M3/UD3/L2 – Controllo degli accessi in Linux](../UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md)), ma occorre saper **prevedere l'output di un programma**, saper **trasformare un binario in Set-UID** dalla riga di comando e saper **descrivere gli attacchi** che sfruttano `system()` e le variabili di ambiente. Questa lezione colma esattamente quel divario operativo.

---

## **Lezione 1: Set-UID, RUID/EUID e attacchi via variabili d'ambiente**

### **1. Perché esistono i programmi Set-UID**

In UNIX/Linux ogni processo agisce con l'identità dell'utente che lo ha lanciato, e il kernel concede o nega gli accessi confrontando tale identità con i permessi degli oggetti. Questo modello, per quanto solido, entra in contraddizione con un'esigenza concreta: **alcune operazioni legittime richiedono privilegi che l'utente comune non possiede**.

L'esempio canonico è il comando `passwd`. Quando un utente qualunque cambia la propria password, il sistema deve scrivere sul file `/etc/shadow`, che è leggibile e scrivibile **solo da root**. Se `passwd` girasse con i privilegi dell'utente, non potrebbe mai aggiornare quel file; se lo lasciassimo scrivere liberamente a tutti, chiunque potrebbe alterare le password altrui. La soluzione adottata da UNIX è il **meccanismo Set-UID**: il binario `passwd` è di proprietà di root e ha il bit Set-UID attivo, così che — **per la durata della sua esecuzione** — assuma i privilegi del proprietario (root) pur essendo stato invocato da un utente non privilegiato.

> 📌 Un programma Set-UID è un **delegato fidato**: eleva temporaneamente i privilegi per compiere un compito ben preciso e circoscritto. Tutta la sicurezza del meccanismo dipende dal fatto che il programma **non faccia nulla di diverso** da ciò per cui è stato pensato. È l'incarnazione più delicata del principio del privilegio minimo (cfr. [M2/UD1/L4 – Principi di sicurezza](../../M2/UD1/L4%20-%20Principi%20di%20sicurezza.md), §6).

---

### **2. La tripletta RUID, EUID, SUID**

Ogni processo porta con sé tre identità utente, che il kernel gestisce separatamente:

| Identità | Nome | Significato |
|---|---|---|
| **RUID** | Real User ID | L'utente che ha **lanciato** il processo. Non cambia con il Set-UID: indica *chi sta usando* il programma. |
| **EUID** | Effective User ID | L'identità usata **nei controlli di accesso**. È il valore che conta quando il kernel decide se una `open()`, una `read()` o una `write()` sono lecite. |
| **SUID** | Saved User ID | Copia di salvataggio dell'EUID, che consente a un processo di **abbassare e poi riprendere** temporaneamente i privilegi. |

All'atto della `exec()` di un binario:

- se il bit Set-UID **non è attivo**, l'EUID viene posto uguale al RUID (il processo eredita l'identità di chi lo lancia);
- se il bit Set-UID **è attivo**, l'EUID viene posto uguale allo **UID del proprietario del file**, mentre il RUID resta quello dell'utente lanciatore. Il SUID prende il valore del nuovo EUID.

> ⚠️ Il punto più frainteso agli esami: il Set-UID conferisce i privilegi **del proprietario del file**, non necessariamente quelli di root. Un binario Set-UID **posseduto da un utente ordinario** eleva l'EUID a quell'utente ordinario, cioè **non concede alcun privilegio aggiuntivo** rispetto a chi lo lancia. Il potere del meccanismo si sblocca solo quando il proprietario è **root (UID 0)**.

Le chiamate di sistema per leggere queste identità sono `getuid()` (restituisce il RUID) e `geteuid()` (restituisce l'EUID). Distinguerle è esattamente ciò che gli appelli chiedono di dimostrare.

---

### **3. I bit speciali del permesso e la notazione `chmod 4755`**

Oltre ai nove bit classici (`rwx` per owner, group, other) UNIX riserva **tre bit speciali** codificati nella cifra ottale più significativa:

| Bit | Valore ottale | Effetto su un eseguibile |
|---|---|---|
| **Set-UID** | `4000` | Il processo assume l'EUID del **proprietario** del file. |
| **Set-GID** | `2000` | Il processo assume l'EGID del **gruppo** proprietario del file. |
| **Sticky** | `1000` | Su directory: solo il proprietario di un file può rimuoverlo (es. `/tmp`). |

Il comando `chmod 4755` imposta quindi il bit Set-UID (`4`) più i permessi `755` (`rwxr-xr-x`): il risultato è la modalità **`-rwsr-xr-x`**, dove la `s` al posto della `x` del proprietario segnala visivamente il Set-UID attivo. In forma simbolica lo stesso effetto si ottiene con `chmod u+s`, mentre `chmod ug+s` attiva **contemporaneamente** Set-UID e Set-GID.

> 💡 La lettura di `ls -l` è diagnostica immediata: `-rws...` indica Set-UID attivo; `-rwS...` (S maiuscola) indica Set-UID attivo ma senza permesso di esecuzione per il proprietario. Se al posto dell'ultima `x` compare una `t` la directory ha lo sticky bit.

---

### **4. Trasformare un programma in Set-UID (procedura da riga di comando)**

Questa è la sequenza richiesta esplicitamente in più appelli (es. dato il sorgente `myls.c` che esegue `system("ls")`). I passi sono tre e vanno eseguiti **in quest'ordine**:

```bash
# 1. Compilazione: il binario nasce di proprietà dell'utente che compila
gcc myls.c -o myls

# 2. Cambio di proprietario a root (richiede privilegi di root)
sudo chown root myls          # equivalente: sudo chown root:root myls

# 3. Attivazione del bit Set-UID
sudo chmod 4755 myls          # equivalente: sudo chmod u+s myls
```

Verifica finale:

```
$ ls -l myls
-rwsr-xr-x 1 root root 16712 ... myls
```

> ⚠️ L'ordine conta e nasconde una salvaguardia del kernel: quando il proprietario di un file Set-UID viene modificato (o il file viene scritto) il kernel **azzera automaticamente i bit Set-UID/Set-GID**. Per questo il `chmod 4755` va dato **dopo** il `chown`: se si invertissero i passi, il `chown` cancellerebbe il bit appena impostato. Inoltre il `chown` a root deve essere eseguito da root: un utente comune non può regalare un proprio file a root e renderlo Set-UID root, altrimenti l'escalation sarebbe banale.

---

### **5. Prevedere l'output: `getuid()` e `geteuid()` prima e dopo il Set-UID**

Consideriamo il programma tipico degli appelli, che stampa RUID ed EUID:

```c
#include <stdio.h>
#include <unistd.h>
int main(void){
    printf("The real user ID is %d\n", getuid());
    printf("The effective user ID is %d\n", geteuid());
    return 0;
}
```

Supponiamo che l'utente `kali` (UID **1000**) compili il file, che poi venga fatto `sudo chown root:root uid` e infine `sudo chmod 4755 uid`. Analizziamo l'output nelle due fasi:

**Fase A — dopo `chown root` ma PRIMA del Set-UID** (permessi `-rwxr-xr-x`, proprietario root ma bit Set-UID spento):

```
$ ./uid
The real user ID is 1000
The effective user ID is 1000
```

Il file è di root, ma **senza bit Set-UID il proprietario è irrilevante**: il processo eredita l'identità del lanciatore. RUID = EUID = 1000.

**Fase B — dopo `chmod 4755`** (permessi `-rwsr-xr-x`, proprietario root, bit Set-UID acceso):

```
$ ./uid
The real user ID is 1000
The effective user ID is 0
```

Ora il RUID resta 1000 (è sempre `kali` a lanciare), ma l'EUID diventa **0** perché il binario è Set-UID **root**. È questo il valore che il kernel userà per i controlli di accesso, dando al processo pieni poteri di root.

| Fase | Comando | RUID (`getuid`) | EUID (`geteuid`) |
|---|---|---|---|
| A | `-rwxr-xr-x root root` | 1000 | 1000 |
| B | `-rwsr-xr-x root root` | 1000 | **0** |

> 📌 Regola mnemonica per l'esame: **il RUID racconta chi sei, l'EUID racconta cosa puoi fare.** Il Set-UID tocca solo il secondo, e solo se il proprietario ha più privilegi di te.

#### **5.1. La variante insidiosa: proprietario NON root**

Se invece il binario **non** viene fatto `chown root` (resta di proprietà di `kali`) e si applica `chmod ug+s`, il bit Set-UID punta a `kali` stesso. In quel caso:

```
The real user ID is 1000
The effective user ID is 1000
```

e un eventuale `system("cat /etc/sudoers")` fallirebbe con **Permission denied**, perché `/etc/sudoers` è leggibile solo da root e l'EUID è ancora 1000. È la trappola concettuale che gli appelli usano per verificare se lo studente ha capito che **Set-UID ≠ root automaticamente**.

---

### **6. Quando un programma Set-UID diventa pericoloso**

Un binario Set-UID root è, di fatto, un frammento di codice root **eseguibile da chiunque**. Se contiene un difetto, quel difetto diventa una via di **privilege escalation**. Le fonti di pericolo classiche sono:

1. **Invocazione di una shell o di un comando esterno** (`system()`, `popen()`), che apre le porte agli attacchi via `PATH` e ai metacaratteri di shell.
2. **Uso di percorsi relativi** invece che assoluti per i file e i comandi.
3. **Fiducia nelle variabili di ambiente** (`PATH`, `IFS`, `LD_PRELOAD`, `LD_LIBRARY_PATH`), che sono **interamente sotto il controllo dell'utente** che lancia il programma.
4. **Passaggio di input non validato** a funzioni che lo interpretano (command injection, format string, buffer overflow).
5. **Mancato rilascio dei privilegi** dopo l'operazione critica (confused deputy): il programma resta root più a lungo del necessario.

> ⚠️ La radice comune di quasi tutti questi problemi è che il programma **eredita l'ambiente dell'attaccante** ma lo esegue con i privilegi di root. Le variabili di ambiente attraversano la `exec()` e non vengono "ripulite" per magia: sono un canale di input a tutti gli effetti, e come ogni input non fidato vanno trattate con sospetto.

---

### **7. L'attacco via `PATH` su `system()` (caso `myls.c`)**

Consideriamo il programma dell'appello del 21/02/2025:

```c
int main() {
    system("ls");
    return 0;
}
```

reso Set-UID root. La funzione `system("ls")` non esegue direttamente `ls`: **lancia una shell** con `/bin/sh -c "ls"`, e la shell **cerca** l'eseguibile `ls` scorrendo le directory elencate nella variabile di ambiente `PATH`. Poiché `ls` è indicato **senza percorso assoluto**, la shell si affida ciecamente a `PATH`.

L'attaccante sfrutta il fatto che `PATH` è sotto il suo controllo:

```bash
# 1. Creo un finto "ls" che fa ciò che voglio (qui apre una shell di root)
$ cd /tmp
$ cat > ls << 'EOF'
#!/bin/sh
/bin/sh          # oppure: cp /bin/sh /tmp/rootsh; chmod 4755 /tmp/rootsh
EOF
$ chmod +x /tmp/ls

# 2. Antepongo /tmp al PATH, così sarà cercato per primo
$ export PATH=/tmp:$PATH

# 3. Lancio il programma Set-UID root
$ ./myls
```

Quando `myls` (EUID = 0) chiama `system("ls")`, la shell trova **prima** `/tmp/ls` e lo esegue **con l'EUID del processo, cioè root**. L'attaccante ottiene così l'esecuzione di codice arbitrario con privilegi di root: escalation completa.

> 💡 Feynman-style: è come consegnare a un fattorino con le chiavi della cassaforte un biglietto che dice "vai a prendere *il* martello" senza specificare quale. L'attaccante sposta di nascosto un martello-grimaldello all'inizio del percorso del fattorino, che lo raccoglierà per primo — con le chiavi ancora in mano.

#### **7.1. La contromisura del linker per `LD_PRELOAD`/`LD_LIBRARY_PATH`**

Una variante concettualmente identica tenta di far caricare al programma una libreria dinamica malevola tramite `LD_PRELOAD` o `LD_LIBRARY_PATH`. Il **dynamic loader** di Linux implementa però una difesa: quando rileva che un binario è Set-UID (EUID ≠ RUID), **ignora** queste variabili. È un buon esempio di *fail-safe default*: il caso privilegiato disattiva un comportamento comodo ma pericoloso.

#### **7.2. La contromisura della shell: il rilascio dei privilegi**

Esiste una seconda salvaguardia, questa volta nella shell. **Bash**, quando viene avviata e nota che EUID ≠ RUID, **abbassa l'EUID al valore del RUID** (a meno di essere invocata con l'opzione `-p`). Perciò, su un sistema in cui `/bin/sh` è un link a bash, `system("ls")` in un binario Set-UID root eseguirebbe la shell **già declassata** a utente normale, vanificando in parte l'escalation. Al contrario **dash** (la `/bin/sh` predefinita su Debian/Kali/Ubuntu) **non** applica questo declassamento, quindi lì l'attacco funziona pienamente. Questo dettaglio spiega perché nei laboratori SEED gli studenti debbano prima verificare a cosa punta `/bin/sh`.

> 📌 Sintesi dell'attacco `PATH`: **input non fidato (PATH) + risoluzione di un comando per nome relativo + privilegi elevati**. Rimuovere anche uno solo di questi tre ingredienti neutralizza l'attacco.

---

### **8. Contromisure lato programmatore**

Chi scrive un programma Set-UID deve assumere che l'ambiente e l'input siano ostili:

- **Non invocare shell**: sostituire `system("ls")` con `execve("/bin/ls", argv, envp)` usando un **percorso assoluto** e un ambiente **controllato** (non ereditato). `execve()` non passa dalla shell e quindi non è soggetto agli attacchi via `PATH` o metacaratteri.
- **Ripulire l'ambiente**: reimpostare `PATH` a un valore sicuro (`/bin:/usr/bin`) e azzerare `IFS`, `LD_*` ecc. all'avvio.
- **Rilasciare i privilegi il prima possibile**: dopo l'operazione critica, `setuid(getuid())` riporta in modo *permanente* l'EUID (e il SUID) al RUID, così che un eventuale bug successivo non giri più da root.
- **Validare ogni input** prima di passarlo a funzioni che lo interpretano.
- **Preferire alternative al Set-UID**: le **Linux capabilities** consentono di concedere il singolo privilegio necessario (es. `CAP_NET_BIND_SERVICE`) invece dell'onnipotenza di root.

> ✅ **Ricapitolando** — Un programma Set-UID conferisce l'EUID del *proprietario* del file per la durata dell'esecuzione: il RUID identifica chi lancia, l'EUID governa i controlli di accesso, e solo un proprietario root eleva davvero i privilegi. `chmod 4755` su un binario di root produce `-rwsr-xr-x` ed EUID 0; senza `chown root` il Set-UID è innocuo. Il pericolo maggiore è l'invocazione di comandi per nome relativo tramite `system()`, che consente l'attacco via `PATH`: il rimedio è `execve()` con percorso assoluto e ambiente ripulito, oltre al rilascio tempestivo dei privilegi. Le salvaguardie del sistema (loader che ignora `LD_PRELOAD`, bash che declassa l'EUID) mitigano ma non sostituiscono la buona programmazione.

Collegamenti: [[L2 - Shellshock]] (stesso tema: ambiente non fidato + esecuzione privilegiata), [M3/UD3/L2 – Controllo degli accessi in Linux](../UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md) (modello DAC, inode, RUID/EUID teorici).
