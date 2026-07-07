## **Lezione 1: Docker essenziale per i laboratori SEED**

### **1. Perché Docker entra nel corso**

Le esercitazioni TCP/IP richiedono più host sulla stessa LAN: un attaccante, una vittima e uno o più utenti legittimi. Farlo con tre VM complete sarebbe pesante: ogni VM avrebbe un proprio sistema operativo, una propria RAM, un proprio disco virtuale e una propria configurazione.

Docker permette invece di creare host leggeri chiamati **container**. Ogni container ha processi, filesystem, rete e hostname isolati, ma condivide il kernel Linux della VM.

> 📌 VirtualBox virtualizza hardware; Docker isola processi. Nel nostro laboratorio i container Docker girano dentro la VM Ubuntu di VirtualBox.

### **2. Esecuzione tradizionale, virtualizzata e containerizzata**

Nel modello tradizionale, più applicazioni girano sullo stesso sistema operativo fisico. Questo è semplice ma non isola bene le risorse: se un'applicazione consuma CPU, memoria o file in modo aggressivo, può disturbare le altre.

Nel modello virtualizzato, un hypervisor fornisce hardware virtuale a più macchine virtuali. Ogni VM ha un intero sistema operativo guest. L'isolamento è forte, ma il costo è alto: boot più lento, più memoria, più spazio disco, gestione più complessa.

Nel modello a container, più ambienti isolati condividono il kernel del sistema host. Ogni container parte da un'immagine e ottiene un livello scrivibile proprio. È più leggero di una VM, ma l'isolamento non è identico a quello di un sistema operativo separato.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ Container non significa "sicurezza perfetta". Per un laboratorio didattico sono ideali; per isolamento forte di codice ostile, una VM resta più robusta.

### **3. Immagini e container**

Una **immagine Docker** è un pacchetto immutabile che contiene ciò che serve per eseguire un'applicazione o un ambiente: file, librerie, runtime, strumenti di sistema e configurazioni.

Un **container** è un'istanza in esecuzione di un'immagine. Quando parte, Docker aggiunge sopra l'immagine un livello scrivibile: le modifiche fatte durante l'esecuzione del container finiscono lì.

Conseguenze pratiche:

- se elimini un container, perdi il suo livello scrivibile;
- se l'immagine resta, puoi creare nuovi container uguali;
- se vuoi persistere dati o condividere codice tra host e container, devi usare un volume.

> 💡 Nel laboratorio SEED scriviamo codice nella cartella `volumes` della VM e Docker la monta nel container come `/volumes`. Così editiamo comodamente da VM, ma il codice è visibile anche all'attaccante.

### **4. Ciclo di vita Docker**

Le slide del docente distinguono tra ciclo di vita delle immagini e ciclo di vita dei container.

Per le immagini:

```bash
docker pull <immagine>       # scarica da un registry
docker build -t <nome> .     # crea immagine da Dockerfile
docker images                # elenca immagini locali
docker rmi <immagine>        # elimina immagine
```

Per i container:

```bash
docker run <immagine>                  # crea e avvia un container
docker run --entrypoint <cmd> <image>  # sovrascrive entrypoint
docker exec -it <container> /bin/bash  # entra in un container già avviato
docker stop <container>                # ferma container
docker rm <container>                  # elimina container
docker ps                              # container in esecuzione
docker ps -a                           # anche container fermi
```

> ✅ La sequenza mentale è: immagine = modello; container = istanza; volume = dati persistenti/condivisi.

### **5. Dockerfile**

Un `Dockerfile` è un file di testo che descrive come costruire un'immagine. Le slide citano le istruzioni fondamentali:

- `FROM <image>`: immagine di partenza;
- `RUN <cmd>`: comando eseguito durante la build;
- `COPY <src> <dest>`: copia file nel filesystem dell'immagine;
- `CMD [...]`: comando predefinito quando parte il container.

Esempio minimale:

```dockerfile
FROM ubuntu
RUN apt update && apt install -y net-tools iputils-ping
CMD ["/bin/bash"]
```

Nel nostro materiale SEED non dobbiamo scrivere da zero i Dockerfile: le immagini `handsonsecurity/seed-ubuntu:large` sono già pronte. Serve però capire il concetto, perché Docker Compose userà quelle immagini per creare i container del laboratorio.

### **6. Volumi**

Un volume Docker serve a salvare o condividere dati fuori dal livello scrivibile del container.

Le slide distinguono:

- volume anonimo o nominato, gestito internamente da Docker;
- volume host/bind mount, collegato a una directory reale del filesystem host.

Nei Labsetup troviamo:

```yaml
volumes:
  - ./volumes:/volumes
```

Significa:

- `./volumes` è una cartella nella directory del Labsetup sulla VM;
- `/volumes` è il percorso visibile dentro il container;
- un file creato nella VM in `./volumes` compare nel container in `/volumes`;
- un file creato dal container in `/volumes` compare nella VM in `./volumes`.

> 📌 Questo è il ponte operativo più importante: scrivi codice nella VM, eseguilo nel container attaccante.

### **7. Docker Compose**

Compose è lo strumento per definire ed eseguire applicazioni multi-container. Invece di lanciare a mano più `docker run`, si scrive un file YAML con:

- **services**, cioè i container da creare;
- **networks**, cioè le reti virtuali;
- **volumes**, cioè le condivisioni di file/directory.

Il file `docker-compose.yml` del laboratorio `tcpattack` definisce:

- `attacker`, con privilegi elevati e `network_mode: host`;
- `Victim`, con IP `10.9.0.5` e `tcp_syncookies=0`;
- `User1`, con IP `10.9.0.6`;
- `User2`, con IP `10.9.0.7`;
- rete `net-10.9.0.0` con subnet `10.9.0.0/24`.

Il laboratorio `tcpspoofing` definisce:

- `attacker`;
- `hostA-10.9.0.5`;
- `hostB-10.9.0.6`;
- rete `10.9.0.0/24`.

### **8. Comandi Compose da sapere**

Nel formato moderno:

```bash
docker compose up -d        # crea e avvia servizi in background
docker compose ps           # mostra servizi del progetto
docker compose logs -f      # mostra log continui
docker compose stop         # ferma senza rimuovere
docker compose start        # riavvia container esistenti
docker compose down         # ferma e rimuove container e rete del progetto
```

Quando stai dentro una cartella Labsetup, la regola pratica è:

```bash
docker compose up -d
docker ps
docksh <nome-container>
docker compose down
```

> ⚠️ `docker compose down` rimuove i container e la rete, ma non cancella i file nella cartella `volumes`. È il comando giusto per chiudere pulitamente un laboratorio.

### **9. Privilegi e capability nei Labsetup**

I file Compose includono:

```yaml
cap_add:
  - ALL
privileged: true
```

Queste opzioni sono necessarie perché gli esercizi richiedono operazioni normalmente vietate:

- creare raw socket;
- costruire header IP/TCP/ICMP manualmente;
- modificare variabili kernel con `sysctl`;
- sniffare interfacce;
- usare modalità promiscua;
- generare traffico spoofato.

Nel container vittima del laboratorio TCP attack troviamo anche:

```yaml
sysctls:
  - net.ipv4.tcp_syncookies=0
```

Questo disabilita le SYN cookies, così il SYN flood è osservabile.

> ⚠️ Se copi questi pattern fuori dal laboratorio, stai creando container con privilegi molto alti. Qui è accettabile perché siamo in una VM isolata e controllata.

### **10. Diagnostica minima**

Quando qualcosa non funziona:

```bash
docker version
docker compose version
docker ps -a
docker network ls
docker network inspect net-10.9.0.0
ip addr
```

Se un container non parte:

```bash
docker compose logs
```

Se i nomi sono già occupati:

```bash
docker ps -a
docker rm -f seed-attacker victim-10.9.0.5 user1-10.9.0.6 user2-10.9.0.7
```

Se la rete è rimasta appesa:

```bash
docker network ls
docker network rm net-10.9.0.0
```

> ✅ Per l'esame/lab non serve diventare amministratori Docker: serve saper avviare, fermare, entrare nei container, capire IP/reti/volumi e leggere il `docker-compose.yml`.

