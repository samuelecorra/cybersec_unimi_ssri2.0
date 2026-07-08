# **L0 - Setup moderno del laboratorio VM**

Fonti integrate: `istruzioniVMSetup.pdf`, `istruzioniVMSetup-MAC_ARM.pdf`, documentazione ufficiale Oracle VirtualBox 7.2 e pagine ufficiali Ubuntu 26.04 LTS consultate a luglio 2026.

### **1. Obiettivo del setup**

I PDF originali spiegano come preparare una VM SEED basata su Ubuntu 20.04 e come configurare un ambiente alternativo per Apple Silicon. Il contenuto resta utile perché i laboratori su Set-UID, variabili di ambiente, dynamic linker e capability leaking dipendono dal comportamento di Linux in una VM isolata.

Nel 2026, però, il setup va interpretato con cautela:

1. VirtualBox 6.1.16 non è più il riferimento operativo: la documentazione ufficiale Oracle disponibile nel 2026 riguarda VirtualBox 7.2;
2. Ubuntu 20.04 ha terminato la manutenzione standard nel 2025, anche se può rimanere usabile come VM didattica isolata;
3. Ubuntu 26.04 LTS è la LTS corrente per nuove installazioni generiche;
4. su Apple Silicon non bisogna forzare una VDI x86_64 dentro VirtualBox: serve un'immagine ARM64 o un hypervisor compatibile.

> 📌 Per riprodurre fedelmente i lab SEED conviene usare la VM SEED Ubuntu 20.04 indicata dal docente, isolata e con snapshot. Per un ambiente Linux personale nel 2026 conviene invece usare Ubuntu 26.04 LTS e adattare i comandi quando le protezioni moderne cambiano il comportamento osservato.

### **2. Credenziali della VM SEED originale**

Il PDF indica una VM SEED preconfigurata con:

```text
utente: seed
password: dees
```

Queste credenziali hanno senso solo dentro la VM di laboratorio. Non vanno riutilizzate come password personali o su macchine connesse a servizi reali.

### **3. Scelta della piattaforma nel 2026**

Su host Windows o Linux x86_64, la scelta più naturale resta VirtualBox. Il documento del docente parla di VirtualBox 6.1.16, ma nel 2026 il riferimento aggiornato è Oracle VirtualBox 7.2.

Su host macOS Intel si può usare VirtualBox o un altro hypervisor compatibile con VM x86_64.

Su host Apple Silicon, il PDF propone VMware Fusion Player e una VM Ubuntu ARM64. Questo è concettualmente corretto: il punto importante non è il prodotto specifico, ma evitare di confondere architetture. Una VDI x86_64 prebuilt non è equivalente a una VM ARM64.

> ⚠️ I lab di sicurezza vanno eseguiti in una VM dedicata, non sul sistema host. Molti esercizi modificano shell, permessi, bit Set-UID, variabili di ambiente e file di sistema.

### **4. Creazione della VM VirtualBox**

Per la VM SEED prebuilt il flusso originale è:

1. scaricare e decomprimere `SEED-Ubuntu20.04.zip`;
2. ottenere il file `.vdi`;
3. creare una nuova macchina virtuale;
4. selezionare tipo Linux e versione Ubuntu 64-bit;
5. collegare il disco virtuale esistente invece di crearne uno nuovo.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Se VirtualBox segnala che l'UUID del disco esiste già, significa che la stessa VDI è già registrata in un'altra VM. La soluzione pulita è non duplicare a mano la cartella: o si rimuove la VM precedente dal VirtualBox Manager, oppure si clona il disco/VM in modo che VirtualBox assegni identificatori nuovi.

### **5. Risorse consigliate**

Il PDF suggerisce 1024 MB di RAM come minimo e 2 GB come valore consigliato per la VM Ubuntu 20.04 SEED. Nel 2026 questi valori sono bassi per un desktop Ubuntu moderno.

Configurazione pragmatica:

| Scenario | RAM | CPU | Disco |
|---|---:|---:|---:|
| VM SEED Ubuntu 20.04 prebuilt | 2-4 GB | 2 vCPU | disco VDI fornito |
| Ubuntu 26.04 LTS desktop | almeno 6 GB | 2 vCPU o più | 25 GB minimo, meglio 40 GB |
| VM leggera solo terminale | 2-4 GB | 1-2 vCPU | 20-30 GB |

Ubuntu 26.04 LTS richiede ufficialmente un processore dual-core da 2 GHz, 6 GB di memoria e 25 GB di spazio per l'installazione desktop.

> 💡 Se l'obiettivo è solo svolgere lab C da terminale, una VM desktop pesante non è indispensabile; ma per coerenza con i materiali del docente, la VM SEED preconfigurata riduce differenze di pacchetti, shell e permessi.

### **6. Impostazioni grafiche e integrazione host-guest**

Il PDF consiglia:

1. controller grafico `VMSVGA`;
2. memoria video aumentata se compaiono schermi neri o glitch;
3. fattore di scala se la VM appare troppo piccola su display ad alta risoluzione;
4. clipboard condivisa bidirezionale;
5. drag and drop bidirezionale, con la nota che non è sempre affidabile.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La clipboard bidirezionale è comoda per copiare comandi, ma nei lab di sicurezza va usata con disciplina: copiare payload o comandi distruttivi tra host e guest aumenta il rischio di errore operativo.

### **7. Rete: NAT come default sicuro**

Il PDF imposta l'adattatore 1 su NAT. È una scelta ragionevole per iniziare:

1. la VM può uscire verso Internet per aggiornamenti e pacchetti;
2. l'host non espone direttamente servizi della VM alla rete locale;
3. non serve configurare manualmente indirizzi IP.

Per lab di rete più avanzati si possono usare NAT Network, Host-only o Bridge, ma per i lab Set-UID e variabili d'ambiente non serve esporre la VM.

> ⚠️ Bridge rende la VM un host visibile sulla rete locale. È utile in alcuni laboratori di rete, ma non è la configurazione predefinita da usare con VM vulnerabili o deliberatamente modificate.

### **8. Snapshot**

Il PDF raccomanda di avviare la VM e creare snapshot. È una pratica essenziale:

1. creare uno snapshot subito dopo il primo boot funzionante;
2. creare uno snapshot prima di modificare `/bin/sh`, installare pacchetti, cambiare permessi o rendere binari Set-UID;
3. ripristinare lo snapshot se il lab lascia il sistema in uno stato incoerente.

> 📌 Nei lab su Set-UID e shell, lo snapshot è parte della sicurezza dell'esperimento: permette di tornare a uno stato noto dopo aver alterato componenti sensibili.

### **9. Arresto della VM**

Il PDF distingue tra spegnimento e salvataggio dello stato. Il salvataggio dello stato è più rapido: congela la VM e permette di riprenderla dallo stesso punto. Lo spegnimento è più pulito quando si cambiano impostazioni hardware virtuali, rete, disco o cartelle condivise.

Regola pratica:

1. usare **Save State** per pause brevi;
2. usare **Shutdown** quando si modifica la configurazione della VM;
3. evitare di spegnere brutalmente la VM durante compilazioni o modifiche a file di sistema.

### **10. Cartelle condivise**

Il PDF propone una cartella condivisa host-guest, montata nella home della VM:

```bash
mkdir -p ~/Share
sudo mount -t vboxsf VM_Shared ~/Share
```

Se il mount non persiste al riavvio, il PDF suggerisce uno script in `/etc/profile.d` che esegua il mount automatico.

Nel 2026 resta valida la raccomandazione più importante del PDF: usare la cartella condivisa solo per trasferire file, non come directory di lavoro dei lab.

> ⚠️ Le cartelle condivise possono alterare semantica dei permessi, ownership, bit eseguibili e operazioni Set-UID. Nei lab di controllo accessi lavorare sempre dentro il filesystem nativo della VM, ad esempio sotto `~/lab` o `/tmp/lab`.

### **11. Setup Apple Silicon**

Il PDF per Mac ARM propone:

1. installazione di Homebrew;
2. eventuale installazione degli strumenti `xcode-select`;
3. uso di VMware Fusion Player;
4. creazione di una VM Ubuntu ARM64;
5. installazione di `open-vm-tools-desktop` per clipboard e integrazione;
6. download di `src-arm.zip` dal sito SEED;
7. esecuzione dello script `install.sh`;
8. scelta `No` quando Wireshark chiede se utenti non privilegiati possano catturare pacchetti;
9. scelta di `LightDM` come display manager quando richiesto.

Questa parte va letta come percorso ARM: non garantisce che ogni lab x86_64 si comporti identicamente, ma permette di avere un ambiente Linux coerente su Apple Silicon.

### **12. Aggiornamenti e pacchetti base**

Su una VM Ubuntu moderna appena installata:

```bash
sudo apt update
sudo apt upgrade
sudo apt install build-essential gdb make gcc man-db strace ltrace zsh
```

Per i lab del docente servono soprattutto compilatore C, strumenti di base, manuali e shell alternative. `zsh` è utile perché alcuni esercizi SEED richiedono di sostituire temporaneamente `/bin/sh` per osservare cosa accade quando la shell non declassa i privilegi.

### **13. Nota su `/bin/sh`**

Alcuni lab chiedono di modificare il link simbolico `/bin/sh`, per esempio:

```bash
sudo ln -sf /bin/zsh /bin/sh
```

Questa modifica è invasiva: cambia il comportamento di script di sistema e programmi che invocano `system()`.

> ⚠️ Eseguire questa modifica solo in una VM snapshot dedicata, mai sul sistema host. Alla fine del lab ripristinare lo snapshot o riportare `/bin/sh` alla shell predefinita della distribuzione.

### **14. Fonti ufficiali usate per l'aggiornamento 2026**

| Tema | Fonte |
|---|---|
| VirtualBox 7.2 | Oracle VirtualBox User Guide for Release 7.2 |
| Stato documentazione VirtualBox | Oracle VirtualBox Documentation |
| Ubuntu 26.04 LTS | Ubuntu Desktop download page |
| Ciclo di vita Ubuntu | Ubuntu release cycle |
| ISO Ubuntu 26.04 | releases.ubuntu.com/resolute |

> ✅ **Ricapitolando** - Il setup originale del docente resta valido come setup SEED Ubuntu 20.04, ma nel 2026 va trattato come VM didattica isolata. Per nuove VM generiche usare VirtualBox 7.2 su host x86_64 e Ubuntu 26.04 LTS; su Apple Silicon usare un percorso ARM64. Per i lab Set-UID contano soprattutto isolamento, snapshot, filesystem nativo della VM, NAT come rete predefinita e cautela assoluta quando si modificano `/bin/sh`, permessi e binari privilegiati.
