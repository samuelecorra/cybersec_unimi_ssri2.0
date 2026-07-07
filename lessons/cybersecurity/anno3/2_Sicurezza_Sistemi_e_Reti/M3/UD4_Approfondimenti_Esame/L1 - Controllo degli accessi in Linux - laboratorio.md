# **UD4 - Controllo degli accessi in Linux: laboratorio**

Fonte integrata: `Lab1.pdf`.

### **1. Obiettivo del laboratorio**

Il laboratorio riprende operativamente il modello Linux già studiato nella teoria del controllo degli accessi: utenti, gruppi, permessi su file e directory, `sudo`, account root, file `/etc/passwd` e casi reali di programmi che richiedono privilegi specifici.

Questa lezione serve come base pratica per i lab successivi su Set-UID, variabili di ambiente e programmi privilegiati.

### **2. Utenti e UID**

In Linux a ogni utente è associato un identificatore numerico univoco, lo **UID**. Il nome utente è comodo per le persone; il kernel ragiona principalmente su identificatori numerici.

Il file storico in cui sono descritte le informazioni principali sugli account è `/etc/passwd`. Il PDF lo scrive come `/ etc /password`, ma il percorso corretto in Linux è:

```bash
/etc/passwd
```

Ogni riga rappresenta un account e contiene campi separati da `:`. Per trovare l'UID dell'utente corrente si possono usare:

```bash
id
id -u
grep "^$USER:" /etc/passwd
```

> 📌 Lo UID è l'identità su cui si fondano i controlli di accesso UNIX/Linux. I nomi sono risolti in UID e GID, ma le decisioni del kernel sono numeriche.

### **3. Aggiunta utenti e cambio utente**

Il PDF indica due modi per aggiungere utenti:

1. modifica diretta di `/etc/passwd`;
2. uso del comando `adduser`.

La modifica diretta è storicamente possibile ma sconsigliata in un sistema reale, perché bisogna mantenere coerenti più file (`/etc/passwd`, `/etc/shadow`, `/etc/group`) e rispettare formati precisi. In laboratorio e in amministrazione ordinaria si usa:

```bash
sudo adduser nomeutente
```

Per passare a un altro utente:

```bash
su - nomeutente
```

oppure, se si dispone dei privilegi `sudo`:

```bash
sudo -u nomeutente comando
```

### **4. Gruppi**

Un gruppo rappresenta un insieme di utenti. Serve per assegnare autorizzazioni a più utenti senza duplicare permessi file per file.

Ogni utente ha:

1. un **gruppo primario**, indicato nel record dell'utente;
2. zero o più **gruppi supplementari**, indicati in `/etc/group`.

Per sapere a quali gruppi appartiene un utente:

```bash
id nomeutente
groups nomeutente
```

Per aggiungere un utente a un gruppo:

```bash
sudo usermod -aG nomegruppo nomeutente
```

> ⚠️ Con `usermod`, l'opzione `-aG` va usata con attenzione: `-G` senza `-a` può sostituire la lista dei gruppi supplementari invece di aggiungerne uno.

### **5. Permessi tradizionali sui file**

Il modello UNIX tradizionale distingue tre classi:

1. owner;
2. group;
3. others.

Per ciascuna classe sono disponibili tre permessi:

| Permesso | Nome | Su file |
|---|---|---|
| `r` | read | leggere il contenuto |
| `w` | write | modificare il contenuto |
| `x` | execute | eseguire il file se è programma o script |

Esempio:

```bash
ls -l script.sh
-rwxr-x--- 1 alice dev 1024 ... script.sh
```

La stringa significa: `alice` può leggere, scrivere ed eseguire; il gruppo `dev` può leggere ed eseguire; gli altri non hanno permessi.

### **6. Permessi tradizionali sulle directory**

Sulle directory i simboli hanno significato diverso:

| Permesso | Su directory |
|---|---|
| `r` | elencare i nomi contenuti nella directory |
| `w` | creare, rinominare o rimuovere voci nella directory |
| `x` | attraversare la directory, cioè usarla in un percorso |

Il permesso `x` su directory è spesso il più importante: senza `x` non si può entrare con `cd` né raggiungere file al suo interno, anche conoscendone il nome.

> 💡 Per leggere un file serve il permesso `r` sul file, ma per raggiungerlo serve anche `x` su tutte le directory del percorso.

### **7. `umask` e permessi predefiniti**

`umask` decide quali permessi vengono rimossi automaticamente quando un processo crea nuovi file o directory.

I valori base sono:

```text
file:      666
directory: 777
```

Con `umask 022`, i nuovi file tendono a nascere come `644` e le nuove directory come `755`:

```bash
umask
umask 022
touch prova.txt
mkdir prova_dir
ls -l
```

La maschera non assegna permessi: li sottrae dal massimo iniziale.

### **8. Uso di `sudo`**

`sudo` permette a un utente autorizzato di eseguire un comando con privilegi superiori, tipicamente come `root`.

L'autorizzazione è gestita tramite `/etc/sudoers` e file inclusi, normalmente sotto `/etc/sudoers.d/`.

Esempi:

```bash
sudo comando
sudo -u altro_utente comando
sudo -l
```

`sudo -l` mostra quali comandi l'utente può eseguire con `sudo`.

> ⚠️ Il file `/etc/sudoers` va modificato con `visudo`, non con un editor generico: `visudo` controlla la sintassi prima di salvare e riduce il rischio di bloccare l'amministrazione del sistema.

### **9. Shell di root**

Il PDF ricorda che in Ubuntu l'account `root` è normalmente bloccato per il login diretto. Questo non significa che root non esista: significa che l'amministrazione passa da `sudo`.

Per ottenere una shell privilegiata:

```bash
sudo -s
sudo bash
sudo su
```

La raccomandazione didattica è evitare shell root prolungate e preferire `sudo comando` per singole operazioni.

> 📌 Una shell root elimina molte barriere cognitive: ogni errore viene eseguito con massima autorità. Nei lab è utile, ma va usata solo in VM e per il tempo necessario.

### **10. Eseguire comandi come altro utente**

`sudo` non serve solo per root. Può eseguire un comando come un utente specifico:

```bash
sudo -u nomeutente id
sudo -u nomeutente whoami
```

Questo è utile per verificare permessi e comportamento di programmi senza cambiare sessione interattiva.

### **11. Caso di studio: Wireshark e `dumpcap`**

Wireshark è un analizzatore di pacchetti. La cattura del traffico richiede privilegi elevati o capability specifiche, perché accedere direttamente all'interfaccia di rete è un'operazione sensibile.

Il modello moderno separa:

1. interfaccia grafica non privilegiata;
2. componente di cattura `dumpcap`, con privilegi o capability limitate.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 La separazione riduce il rischio: un bug nella GUI di Wireshark non dovrebbe automaticamente equivalere a codice arbitrario con privilegi di root.

### **12. Caso di studio: `ping` e `CAP_NET_RAW`**

`ping` usa pacchetti ICMP e storicamente richiedeva privilegi elevati perché doveva aprire raw socket. Nei sistemi Linux moderni il programma può avere la capability `CAP_NET_RAW` invece di essere Set-UID root.

Per ispezionare capability:

```bash
getcap /usr/bin/ping
```

Esempio tipico:

```text
/usr/bin/ping cap_net_raw=ep
```

> 💡 Le capability realizzano meglio il privilegio minimo: invece di dare tutto root, si concede solo il privilegio necessario, ad esempio creare pacchetti raw.

### **13. Il file `/etc/passwd`**

Ogni voce contiene informazioni sull'account:

```text
nome:x:UID:GID:commento:home:shell
```

Il campo `x` indica che la password non è memorizzata in chiaro né come hash in `/etc/passwd`: l'hash è in `/etc/shadow`, leggibile solo da utenti privilegiati.

L'ultimo campo indica il primo programma eseguito dopo il login, tipicamente una shell:

```text
/bin/bash
/bin/sh
/usr/sbin/nologin
```

### **14. Account bloccati**

Un account può essere bloccato rendendo non valida la password o impostando una shell non interattiva. Nel caso di root su Ubuntu, il login diretto è disabilitato: si amministra tramite utenti autorizzati a usare `sudo`.

Un campo password non valido impedisce l'autenticazione tramite password, ma non elimina necessariamente l'account: processi e file possono ancora appartenere a quell'UID.

> ✅ **Ricapitolando** - Il laboratorio consolida il modello Linux: utenti identificati da UID, gruppi da GID, permessi `rwx` distinti per file e directory, `umask` per i permessi predefiniti, `sudo` per operazioni privilegiate controllate, e file `/etc/passwd`/`/etc/shadow` come base della gestione account. I casi Wireshark e `ping` mostrano che i privilegi vanno separati e ridotti, anticipando Set-UID e capability.

Collegamenti: [M3/UD3/L2 - Controllo degli accessi in Linux](../UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md), [L2 - Programmi privilegiati e Set-UID](L2%20-%20Programmi%20privilegiati%20e%20Set-UID.md).
