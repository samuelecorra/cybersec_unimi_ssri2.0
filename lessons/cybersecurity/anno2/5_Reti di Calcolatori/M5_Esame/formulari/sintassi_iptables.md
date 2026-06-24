# Legenda `iptables`

> 📌 `iptables` è lo strumento Linux per configurare le regole di filtraggio del kernel (netfilter). Opera su **tabelle** (filter, nat, mangle…); la tabella di default è **filter**, quella usata in tutti gli esercizi di questo corso.

---

### 1. Struttura di un comando iptables

```
iptables  [-t tabella]  <COMANDO>  <CATENA>  [opzioni di match]  -j <TARGET>
```

---

### 2. Comandi principali (cosa fare sulla catena)

| Flag | Forma estesa | Significato |
|---|---|---|
| `-P` | `--policy` | Imposta la **policy di default** della catena: se nessuna regola fa match, si applica questo target (es. `DROP` o `ACCEPT`). |
| `-A` | `--append` | **Aggiunge** la regola in fondo alla catena. Le regole sono valutate in ordine: la prima che fa match termina la valutazione. |
| `-I` | `--insert` | **Inserisce** la regola in una posizione specifica (di default in cima). Utile per mettere regole prioritarie prima di quelle esistenti. |
| `-D` | `--delete` | **Elimina** una regola dalla catena (per numero o per pattern). |
| `-F` | `--flush` | **Svuota** tutte le regole della catena (o di tutte le catene se non specificata). |
| `-L` | `--list` | **Elenca** le regole presenti (aggiungere `-v` per contatori, `-n` per non risolvere nomi). |

---

### 3. Catene (CHAIN) — dove si applica la regola

| Catena | Quando viene attraversata |
|---|---|
| `INPUT` | Pacchetti **destinati alla macchina stessa** (il firewall è il destinatario finale). |
| `OUTPUT` | Pacchetti **generati dalla macchina stessa** e in uscita. |
| `FORWARD` | Pacchetti **in transito**: entrano da un'interfaccia ed escono da un'altra (il firewall fa da router). È la catena usata per filtrare il traffico tra LAN e Internet. |
| `PREROUTING` | Pacchetti appena arrivati, prima del routing (usata in tabella nat per DNAT). |
| `POSTROUTING` | Pacchetti dopo il routing, prima di uscire (usata in tabella nat per SNAT). |

> 💡 Negli esercizi d'esame la catena più usata è **FORWARD** perché il firewall protegge una LAN interna verso Internet: i pacchetti non sono destinati al firewall stesso ma lo attraversano.

---

### 4. Opzioni di match — criteri di selezione del pacchetto

#### 4.1 Interfacce

| Flag | Forma estesa | Significato |
|---|---|---|
| `-i eth0` | `--in-interface` | Pacchetto **arrivato** dall'interfaccia `eth0`. Usabile in INPUT e FORWARD. |
| `-o eth1` | `--out-interface` | Pacchetto **in uscita** dall'interfaccia `eth1`. Usabile in OUTPUT e FORWARD. |

> ⚠️ `-i` è l'interfaccia di **ingresso**, `-o` quella di **uscita**. In FORWARD si usano entrambi per specificare la direzione del flusso (es. `-i eth0 -o eth1` = dalla LAN verso Internet).

#### 4.2 Indirizzi IP

| Flag | Forma estesa | Significato |
|---|---|---|
| `-s 10.0.0.1` | `--source` | IP **sorgente** del pacchetto. Può essere un singolo IP o una subnet CIDR (es. `10.0.0.0/24`). |
| `-d 10.0.0.1` | `--destination` | IP **destinazione** del pacchetto. Stessa sintassi di `-s`. |
| `! -s 10.0.0.1` | negazione | Il `!` **nega** il match: significa "qualunque sorgente **tranne** `10.0.0.1`". |

#### 4.3 Protocollo

| Flag | Significato |
|---|---|
| `-p tcp` | Match solo sui pacchetti **TCP** (numero protocollo IP = 6). |
| `-p udp` | Match solo sui pacchetti **UDP** (numero protocollo IP = 17). |
| `-p icmp` | Match sui pacchetti **ICMP** (ping, traceroute…). |
| `-p all` | Match su **tutti** i protocolli (default se `-p` è omesso). |

#### 4.4 Porte (richiedono `-p tcp` o `-p udp`)

| Flag | Forma estesa | Significato |
|---|---|---|
| `--sport 80` | `--source-port` | Porta **sorgente** del segmento. |
| `--dport 443` | `--destination-port` | Porta **destinazione** del segmento. |
| `--dport 1024:65535` | range | Intervallo di porte da 1024 a 65535. |

#### 4.5 Flag TCP (richiedono `-p tcp`)

| Flag | Significato |
|---|---|
| `--syn` | Match sui pacchetti con **solo SYN settato** (FIN, RST, ACK azzerati). Equivale a `--tcp-flags FIN,SYN,RST,ACK SYN`. Identifica i pacchetti di **apertura connessione** (primo passo del three-way handshake). Bloccare `--syn` impedisce nuove connessioni in ingresso senza toccare le risposte. |
| `--tcp-flags <mask> <set>` | Forma generica: `<mask>` = insieme di flag da esaminare; `<set>` = quelli che devono risultare settati. Es. `--tcp-flags SYN,RST SYN,RST` = pacchetto con SYN e RST entrambi settati (illegale). |

> ⚠️ `--syn` cattura solo il primo SYN. I pacchetti successivi della stessa connessione TCP (ACK, FIN…) non hanno il solo SYN settato, quindi non fanno match e vengono gestiti dalle regole successive o dalla policy di default.

#### 4.6 Moduli estesi (`-m`)

I moduli estendono le capacità di match. Si caricano con `-m <nome_modulo>`.

**Modulo `conntrack`** — connection tracking stateful:

| Stato (`--ctstate`) | Significato |
|---|---|
| `NEW` | Pacchetto che avvia una **nuova** connessione (il kernel non ha visto questo flusso prima). |
| `ESTABLISHED` | Pacchetto appartenente a una connessione già **stabilita** (andata e ritorno già visti). |
| `RELATED` | Pacchetto **correlato** a una connessione esistente (es. ICMP error su un flusso TCP, o canale dati FTP correlato al controllo). |
| `INVALID` | Pacchetto che non corrisponde ad alcuna connessione nota e non è un nuovo SYN: va droppato. |
| `ESTABLISHED,RELATED` | Match su entrambi: permette il traffico di **ritorno** per connessioni avviate dall'interno. È il pattern tipico del firewall stateful che blocca le connessioni nuove in ingresso ma lascia passare le risposte. |

---

### 5. Target (`-j`) — cosa fare con il pacchetto

| Target | Significato |
|---|---|
| `ACCEPT` | **Lascia passare** il pacchetto. Nessuna ulteriore regola viene valutata per questo pacchetto. |
| `DROP` | **Scarta silenziosamente** il pacchetto. Il mittente non riceve alcuna notifica: la connessione risulta in timeout. |
| `REJECT` | **Scarta** il pacchetto **e invia un errore ICMP** al mittente. Più cortese di DROP ma rivela la presenza del firewall. |
| `LOG` | **Registra** le informazioni del pacchetto nel log di sistema e poi **continua** la valutazione delle regole successive (non è un target terminale). |

> 💡 **DROP vs REJECT**: in contesti di sicurezza si preferisce `DROP` perché non rivela informazioni al potenziale attaccante (il port scan restituisce timeout anziché ICMP error). `REJECT` è più utile nella LAN interna per dare feedback agli utenti.

---

### 6. Logica di valutazione delle regole

```
Pacchetto in arrivo
        |
        v
  Regola 1: fa match?  --NO-->  Regola 2: fa match?  --NO-->  ...  --NO-->  Policy di default (-P)
        |                               |
       SI                              SI
        |                               |
        v                               v
   Applica target                Applica target
   (ACCEPT o DROP)               (ACCEPT o DROP)
   Fine valutazione              Fine valutazione
```

> ⚠️ Le regole sono valutate **in ordine**. Al primo match, la valutazione si interrompe e il target viene applicato. Se nessuna regola fa match, si applica la **policy di default** (impostata con `-P`). Per questo la policy `DROP` con regole `ACCEPT` selettive è la configurazione più sicura.

---

### 7. Esempio di lettura di una regola completa

```bash
iptables -A FORWARD -i eth0 -o eth1 -s 10.0.0.1 -p tcp -j ACCEPT
```

| Pezzo | Significato |
|---|---|
| `-A FORWARD` | Aggiungi in coda alla catena FORWARD. |
| `-i eth0` | Il pacchetto è arrivato dall'interfaccia `eth0` (LAN interna). |
| `-o eth1` | Il pacchetto uscirà dall'interfaccia `eth1` (Internet). |
| `-s 10.0.0.1` | L'IP sorgente è `10.0.0.1` (il proxy). |
| `-p tcp` | Il protocollo è TCP. |
| `-j ACCEPT` | Se tutti i criteri sono soddisfatti: lascia passare il pacchetto. |

**Lettura in chiaro:** *"Aggiungi alla catena FORWARD una regola che accetta i pacchetti TCP provenienti dalla LAN (eth0) verso Internet (eth1) se la sorgente è il proxy (10.0.0.1)."*
