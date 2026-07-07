# **UD4 - Approfondimenti d'esame: programmi privilegiati, Set-UID e ambiente**

Fonti integrate: `Lab2.pdf`, sezioni Set-UID del PDF `Environment_Variable_and_SetUID.pdf`, e contenuto già presente nella precedente lezione `L1 - Set-UID, RUID-EUID e attacchi via variabili d'ambiente.md`.

### **1. Necessità dei programmi privilegiati**

Nei sistemi UNIX/Linux il controllo degli accessi tradizionale è applicato dal kernel confrontando l'identità effettiva del processo con i permessi dell'oggetto richiesto. Questo modello funziona bene per le autorizzazioni di base, ma non basta quando un utente ordinario deve compiere un'operazione legittima che richiede privilegi superiori.

L'esempio classico è il cambio della password. Il file `/etc/shadow` contiene gli hash delle password e non è scrivibile dagli utenti comuni. Tuttavia un utente deve poter aggiornare la propria password senza poter modificare quella degli altri. Rendere `/etc/shadow` scrivibile da tutti sarebbe inaccettabile; spostare nel kernel ogni controllo capillare renderebbe il sistema operativo troppo complesso.

La soluzione storica è un approccio a due livelli:

1. il kernel mantiene un meccanismo di controllo generale, semplice e robusto;
2. alcune applicazioni fidate applicano controlli più specifici e operano temporaneamente con privilegi elevati.

> 📌 Un programma privilegiato è un'estensione controllata del meccanismo di protezione del sistema operativo: non dà direttamente privilegi all'utente, ma esegue per suo conto un'operazione limitata.

### **2. Tipi di programmi privilegiati**

Le slide distinguono due famiglie principali.

I **demoni** sono processi in background che forniscono servizi di sistema. Spesso devono partire come `root` o come utenti dedicati con privilegi particolari, perché devono aprire porte privilegiate, accedere a risorse globali o gestire richieste di altri utenti.

I **programmi Set-UID** sono eseguibili UNIX marcati con un bit speciale. Quando vengono lanciati, il processo non opera solo con l'identità dell'utente chiamante, ma assume come identità effettiva quella del proprietario del file eseguibile.

> 💡 `passwd` è il caso didattico più importante: è eseguibile dagli utenti normali, ma il file appartiene a `root` ed è Set-UID, quindi durante l'esecuzione può aggiornare `/etc/shadow` dopo aver verificato che l'utente stia cambiando solo la propria password.

### **3. Concetto di Set-UID**

Il Set-UID consente a un utente di eseguire un programma con i privilegi del **proprietario del programma**. Se il proprietario è `root`, il programma gira con privilegi di root; se il proprietario è un utente ordinario, il programma assume i privilegi di quell'utente ordinario.

Il meccanismo non deve essere interpretato come "dare root all'utente". L'utente non riceve una shell privilegiata e non può scegliere liberamente cosa fare: può solo attivare il comportamento previsto dal programma.

> ⚠️ Errore frequente d'esame: Set-UID non significa automaticamente root. Il privilegio assunto è quello del proprietario del file, non quello più alto disponibile nel sistema.

### **4. RUID, EUID e SUID**

Ogni processo porta con sé più identità. Quelle essenziali per questa unità sono:

| Identità | Nome | Ruolo |
|---|---|---|
| RUID | Real User ID | Identifica l'utente reale che ha lanciato il processo. |
| EUID | Effective User ID | È l'identità usata dal kernel nei controlli di accesso. |
| SUID | Saved Set-User-ID | Conserva un'identità privilegiata recuperabile dopo un downgrade temporaneo. |

Quando viene eseguito un programma normale, vale:

```text
RUID = EUID = UID dell'utente chiamante
```

Quando viene eseguito un programma Set-UID, vale invece:

```text
RUID = UID dell'utente chiamante
EUID = UID del proprietario dell'eseguibile
```

Il controllo degli accessi si basa sull'EUID. Se un utente con UID `1000` lancia un binario Set-UID di proprietà di `root`, `getuid()` restituisce `1000`, mentre `geteuid()` restituisce `0`.

> 📌 Regola sintetica: il RUID dice chi ha chiesto l'esecuzione; l'EUID dice quali permessi ha il processo in quel momento.

### **5. Creare un programma Set-UID**

La sequenza operativa richiesta negli esercizi è:

```bash
gcc prog.c -o prog
sudo chown root:root prog
sudo chmod 4755 prog
ls -l prog
```

Il permesso `4755` somma:

| Cifra | Significato |
|---|---|
| `4` | bit Set-UID |
| `7` | permessi owner `rwx` |
| `5` | permessi group `r-x` |
| `5` | permessi others `r-x` |

L'output atteso di `ls -l` è del tipo:

```text
-rwsr-xr-x 1 root root ... prog
```

La `s` nella terna del proprietario indica che il bit Set-UID è attivo e che il file è anche eseguibile.

> ⚠️ L'ordine è importante: dopo `chown` va eseguito `chmod`. Molti sistemi rimuovono i bit Set-UID/Set-GID quando cambia il proprietario o quando il file viene riscritto, per evitare escalation banali.

### **6. Esempio con `getuid()` e `geteuid()`**

Il programma minimo per osservare il meccanismo è:

```c
#include <stdio.h>
#include <unistd.h>

int main(void)
{
    printf("The real user ID is %d\n", getuid());
    printf("The effective user ID is %d\n", geteuid());
    return 0;
}
```

Se il binario non è Set-UID, oppure se è Set-UID ma appartiene allo stesso utente che lo lancia, RUID ed EUID coincidono. Se invece il binario appartiene a `root` ed è marcato Set-UID, l'output tipico diventa:

```text
The real user ID is 1000
The effective user ID is 0
```

Questa differenza spiega perché il processo può accedere a risorse normalmente vietate all'utente reale.

### **7. Esempi di Set-UID sicuri e insicuri**

`passwd` è un esempio legittimo: consente una sola operazione precisa, verifica l'identità dell'utente, controlla le policy di password e aggiorna file protetti.

Rendere Set-UID programmi generici come `/bin/sh`, editor testuali o interpreti è invece pericoloso, perché l'utente potrebbe sfruttarli per eseguire comandi arbitrari, modificare file o lanciare altri programmi con l'EUID privilegiato.

> 📌 La sicurezza del Set-UID non dipende dal bit in sé, ma dal comportamento vincolato del programma marcato con quel bit.

### **8. Superficie di attacco dei programmi Set-UID**

Un programma Set-UID è sensibile a ogni canale attraverso cui l'utente può influenzarne il comportamento:

1. input espliciti da riga di comando, file, standard input o rete;
2. variabili di ambiente ereditate dal processo chiamante;
3. percorsi relativi e risoluzione tramite `PATH`;
4. librerie dinamiche e variabili `LD_*`;
5. race condition su file temporanei o directory scrivibili da tutti;
6. descrittori di file privilegiati lasciati aperti dopo un downgrade;
7. uso improprio di `system()` o di funzioni `exec*()` che invocano la shell o cercano nel `PATH`.

> ⚠️ La domanda pratica non è "un programma Set-UID è pericoloso?", ma "quali parti del suo comportamento possono essere controllate dall'utente reale mentre l'EUID è più privilegiato?".

### **9. Privilegio minimo e declassamento**

Un programma privilegiato dovrebbe mantenere i privilegi elevati solo durante l'operazione che li richiede. Dopo quella fase può:

1. declassare temporaneamente l'EUID con `seteuid()`;
2. declassare permanentemente tutti gli UID con `setuid(getuid())`;
3. chiudere file descriptor, socket o handle aperti mentre era privilegiato;
4. invocare codice non fidato solo dopo aver perso i privilegi.

Il declassamento, da solo, non basta se il processo conserva capacità privilegiate già acquisite. Questo problema è approfondito nella lezione su dynamic linker e capability leaking.

> ✅ **Ricapitolando** - Il Set-UID è un meccanismo UNIX che permette a un eseguibile di girare con l'EUID del proprietario del file. Serve per implementare programmi privilegiati limitati, come `passwd`, senza dare all'utente una delega illimitata. La distinzione RUID/EUID è centrale: il RUID identifica chi lancia, l'EUID determina cosa può fare il processo. La superficie d'attacco nasce quando input, ambiente, librerie, shell o file descriptor permettono all'utente reale di far eseguire azioni non previste mentre l'EUID è privilegiato.

Collegamenti: [L1 - Controllo degli accessi in Linux - laboratorio](L1%20-%20Controllo%20degli%20accessi%20in%20Linux%20-%20laboratorio.md), [L4 - Attacchi a programmi Set-UID tramite ambiente e PATH](L4%20-%20Attacchi%20a%20programmi%20Set-UID%20tramite%20ambiente%20e%20PATH.md), [L5 - Dynamic linker, LD_PRELOAD e capability leaking](L5%20-%20Dynamic%20linker,%20LD_PRELOAD%20e%20capability%20leaking.md).
