## **Lezione 0: Setup moderno del laboratorio Docker SEED**

### **1. Obiettivo dell'unità**

Questa unità contiene le esercitazioni pratiche del Modulo 4, Unità didattica 2, dedicate alle vulnerabilità TCP/IP. I materiali originali del docente includono:

- `descrizione.txt`, con le istruzioni generali dell'unità;
- `Esercitazione1.pdf`, introduzione operativa a packet receiving, Scapy, sniffing e spoofing;
- `Esercitazione2.pdf`, laboratorio su Netcat;
- `Esercitazione3.pdf`, introduzione a Docker e Docker Compose;
- `Sniffing_Spoofing2.pdf`, laboratorio SEED su packet sniffing e spoofing;
- `TCP_Attacks2.pdf`, laboratorio SEED su SYN flood, TCP reset, session hijacking e reverse shell;
- `Labsetup-tcpspoofing.zip` e `Labsetup-tcpattack.zip`, setup Docker per macchine x86/x64;
- `Labsetup-arm-tcpspoofing.zip` e `Labsetup-arm-tcpattack.zip`, setup Docker per Apple Silicon/ARM.

> 📌 L'ambiente richiesto non è una seconda VM Ubuntu completa: è un laboratorio a container dentro la VM Ubuntu che abbiamo già preparato. La VM rimane il contenitore esterno stabile; Docker crea più host virtuali leggeri per simulare la LAN del laboratorio.

### **2. Dobbiamo creare una nuova VM?**

Per il nostro caso, no. La strategia corretta è:

1. partire dalla VM `Ubuntu-SSRI-Laboratorio` già installata;
2. verificare di essere nello snapshot pulito con tool base installati;
3. creare un nuovo snapshot prima di installare Docker, per esempio `03-before-docker-seed-labs`;
4. installare Docker Engine nella VM;
5. importare i Labsetup ZIP nella VM;
6. creare un nuovo snapshot dopo il setup, per esempio `04-docker-seed-labs-ready`.

Questa scelta è più robusta di una VM nuova perché conserva Guest Additions, clipboard, cartella condivisa, terminale, tool base e workflow già verificati.

> ⚠️ Una VM nuova avrebbe senso solo se Docker alterasse pesantemente la rete della VM o se volessimo separare rigidamente i laboratori Linux/Set-UID dai laboratori networking. In questa fase è più efficiente usare snapshot, non moltiplicare macchine.

### **3. Architettura del laboratorio**

Il docente specifica che le esercitazioni usano un ambiente Linux basato su Docker. I PDF SEED chiariscono il modello: invece di creare tre VM separate, si creano più container collegati alla stessa rete Docker.

Nel laboratorio di sniffing/spoofing servono tre macchine logiche:

- una macchina attaccante;
- una o più macchine utente/vittima;
- una rete comune, tipicamente `10.9.0.0/24`.

Nel laboratorio TCP attack lo schema diventa:

- `seed-attacker`, container attaccante;
- `victim-10.9.0.5`, vittima principale;
- `user1-10.9.0.6`, host utente;
- `user2-10.9.0.7`, secondo host utente.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 Docker non sostituisce la VM: lavora dentro la VM. VirtualBox virtualizza un intero computer; Docker isola processi e reti condividendo il kernel Linux della VM.

### **4. Quali ZIP usare nel nostro ambiente**

Il nostro host fisico è Windows su architettura x86_64/amd64 e la VM Ubuntu è amd64. Quindi useremo:

- `Labsetup-tcpspoofing.zip` per packet sniffing/spoofing;
- `Labsetup-tcpattack.zip` per SYN flood, TCP reset e session hijacking.

Gli ZIP `Labsetup-arm-*` sono per Mac con processore ARM/Apple Silicon e usano immagini Docker `handsonsecurity/seed-ubuntu:large-arm`. Non sono il nostro percorso principale.

> ✅ Regola pratica: Windows + VirtualBox + Ubuntu amd64 = ZIP senza `arm` nel nome.

### **5. Installazione moderna di Docker nella VM Ubuntu**

Le slide del docente risalgono a un ambiente SEED Ubuntu 20.04. Nel nostro laboratorio reale siamo su Ubuntu 26.04 in VirtualBox, quindi conviene usare il repository ufficiale Docker e il plugin moderno `docker compose`, non il vecchio binario separato `docker-compose`.

I passaggi ufficiali Docker per Ubuntu prevedono:

1. aggiunta della chiave GPG ufficiale;
2. aggiunta del repository `download.docker.com`;
3. installazione di `docker-ce`, `docker-ce-cli`, `containerd.io`, `docker-buildx-plugin`, `docker-compose-plugin`;
4. verifica con `hello-world`.

Comandi operativi:

```bash
sudo apt update
sudo apt install -y ca-certificates curl

sudo install -m 0755 -d /etc/apt/keyrings
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc
sudo chmod a+r /etc/apt/keyrings/docker.asc

sudo tee /etc/apt/sources.list.d/docker.sources >/dev/null <<EOF
Types: deb
URIs: https://download.docker.com/linux/ubuntu
Suites: $(. /etc/os-release && echo "${UBUNTU_CODENAME:-$VERSION_CODENAME}")
Components: stable
Architectures: $(dpkg --print-architecture)
Signed-By: /etc/apt/keyrings/docker.asc
EOF

sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo docker run hello-world
```

Riferimento operativo: documentazione ufficiale Docker Engine per Ubuntu, sezione "Install using the apt repository".

> ⚠️ Docker gestisce reti, iptables/nftables, bridge virtuali e container privilegiati. Per questo conviene installarlo solo dopo uno snapshot dedicato.

### **6. Permessi dell'utente `seed`**

Dopo l'installazione, Docker funziona sicuramente con `sudo`:

```bash
sudo docker ps
sudo docker compose version
```

Per usare Docker senza `sudo`, si può aggiungere l'utente al gruppo `docker`:

```bash
sudo usermod -aG docker $USER
newgrp docker
docker ps
```

> ⚠️ Il gruppo `docker` è praticamente equivalente a privilegi amministrativi sulla macchina: chi può controllare Docker può montare filesystem, creare container privilegiati e ottenere accesso esteso al sistema. In una VM di laboratorio va bene; su un server reale va trattato come un privilegio alto.

### **7. Importare i Labsetup nella VM**

Copiamo gli ZIP nella cartella condivisa Windows `C:\VM_SHARE`, poi dalla VM li portiamo in una cartella interna. È meglio lavorare nel filesystem Linux, non direttamente nella share VirtualBox, perché permessi, eseguibili e path Unix sono più prevedibili.

```bash
mkdir -p ~/ssri-lab/m4-ud2-l8
cp /media/sf_VM_Shared/Labsetup-tcpspoofing.zip ~/ssri-lab/m4-ud2-l8/
cp /media/sf_VM_Shared/Labsetup-tcpattack.zip ~/ssri-lab/m4-ud2-l8/

cd ~/ssri-lab/m4-ud2-l8
unzip Labsetup-tcpspoofing.zip -d tcpspoofing
unzip Labsetup-tcpattack.zip -d tcpattack
```

Verifica:

```bash
find ~/ssri-lab/m4-ud2-l8 -maxdepth 3 -type f
```

Dovremmo vedere:

- `tcpspoofing/Labsetup/docker-compose.yml`;
- `tcpattack/Labsetup/docker-compose.yml`;
- `tcpattack/Labsetup/volumes/synflood.c`.

### **8. `docker compose` moderno vs `docker-compose` vecchio**

I PDF SEED usano spesso:

```bash
docker-compose up
docker-compose down
```

Nel setup moderno useremo:

```bash
docker compose up -d
docker compose down
docker compose ps
docker compose logs -f
```

La differenza è soprattutto sintattica: oggi Compose è un plugin del comando `docker`, quindi lo spazio tra `docker` e `compose` è normale.

> 📌 Quando una guida vecchia dice `docker-compose`, nel nostro ambiente moderno prova prima `docker compose`.

### **9. Alias utili**

I PDF SEED assumono alias già presenti nella VM SEED ufficiale. Nella nostra VM possiamo crearli in `~/.bashrc`:

```bash
cat >> ~/.bashrc <<'EOF'
alias dcbuild='docker compose build'
alias dcup='docker compose up -d'
alias dcdown='docker compose down'
alias dcps='docker compose ps'
alias dockps='docker ps --format "{{.ID}} {{.Names}}"'
docksh() { docker exec -it "$1" /bin/bash; }
EOF

source ~/.bashrc
```

Uso:

```bash
dockps
docksh seed-attacker
docksh victim-10.9.0.5
```

> 💡 Docker accetta anche prefissi brevi dell'ID del container, purché siano univoci. Con nomi espliciti come `seed-attacker` o `victim-10.9.0.5` è però più leggibile.

### **10. Avviare e spegnere i laboratori**

Per il laboratorio sniffing/spoofing:

```bash
cd ~/ssri-lab/m4-ud2-l8/tcpspoofing/Labsetup
docker compose up -d
docker ps
```

Per spegnerlo:

```bash
docker compose down
```

Per il laboratorio TCP attacks:

```bash
cd ~/ssri-lab/m4-ud2-l8/tcpattack/Labsetup
docker compose up -d
docker ps
```

Per spegnerlo:

```bash
docker compose down
```

> ⚠️ Non tenere avviati contemporaneamente due Labsetup che creano la stessa rete `10.9.0.0/24` o container con nomi uguali. Prima fai `docker compose down` nel laboratorio corrente.

### **11. Capire la rete Docker del laboratorio**

I Labsetup usano una rete `10.9.0.0/24`. La VM ottiene normalmente l'indirizzo `10.9.0.1` sul bridge Docker, mentre i container ricevono indirizzi fissi.

Comandi utili:

```bash
docker network ls
ip addr
ip addr | grep -A3 '10.9.0.1'
```

L'interfaccia host corrispondente di solito si chiama `br-<id>`, dove `<id>` è derivato dall'ID della rete Docker. Nei programmi Scapy/libpcap va specificata questa interfaccia quando si vuole sniffare traffico tra container.

> 📌 Se il filtro Scapy non cattura nulla, il primo sospetto è l'interfaccia sbagliata. Prima trova il bridge con IP `10.9.0.1`, poi usa quel nome in `iface=...`.

### **12. Perché l'attacker container usa `network_mode: host`**

Nei file Compose l'attaccante è configurato diversamente:

```yaml
network_mode: host
privileged: true
cap_add:
  - ALL
volumes:
  - ./volumes:/volumes
```

Questa scelta ha tre effetti:

- il container attaccante vede le interfacce di rete dell'host VM;
- può sniffare traffico più ampio rispetto a un container collegato solo al proprio bridge;
- può eseguire operazioni privilegiate necessarie per raw socket, spoofing e manipolazione di pacchetti.

> ⚠️ `privileged: true` e `cap_add: ALL` non sono impostazioni "normali" da produzione. Sono intenzionali nel laboratorio perché l'obiettivo didattico è costruire pacchetti e osservare vulnerabilità di basso livello.

### **13. Account `seed` nei container**

Nel laboratorio TCP attack, i container includono un account:

- username: `seed`;
- password: `dees`.

Serve per creare sessioni Telnet tra container, così da sperimentare reset e hijacking di sessione.

Esempio:

```bash
docksh user1-10.9.0.6
telnet 10.9.0.5
```

### **14. Snapshot consigliati**

Prima di lavorare:

1. spegni la VM;
2. crea snapshot `03-before-docker-seed-labs`;
3. avvia la VM e installa Docker;
4. importa e testa i Labsetup;
5. spegni la VM;
6. crea snapshot `04-docker-seed-labs-ready`.

> ✅ Alla fine del setup dobbiamo avere una VM con Docker funzionante, ZIP importati, alias pronti e laboratorio avviabile con `docker compose up -d`. Da lì possiamo iniziare gli esercizi senza rifare configurazione ogni volta.

