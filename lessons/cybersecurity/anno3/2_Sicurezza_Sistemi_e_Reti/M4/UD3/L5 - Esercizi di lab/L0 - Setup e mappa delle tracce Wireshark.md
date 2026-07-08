## **Lezione 0: Setup e mappa delle tracce Wireshark**

### **1. Obiettivo dell'unità**

Questa sottounità raccoglie i materiali pratici forniti per esercitarsi con Wireshark in modo canonico, seguendo l'impostazione dei laboratori Kurose/Ross richiamata dal docente. Rispetto al modulo M7, che è orientato alla prova d'esame e alla soluzione rapida di PCAP, qui l'obiettivo è più formativo: imparare a osservare i protocolli in azione e a collegare ogni campo della traccia al modello TCP/IP.

I materiali presenti sono:

- `Wireshark_Intro_v8.0.pdf`, introduzione generale a Wireshark e al primo esperimento HTTP.
- `Esercitazione1 (1).pdf`, scaletta del docente per esercizi su HTTP, Ethernet/ARP, ICMP, TCP, DNS, DHCP e SSL/TLS.
- `wireshark-traces.zip`, archivio di tracce già catturate.

> 📌 Questo blocco non sostituisce M7: lo prepara. M7 insegna a rispondere bene all'esame; questa unità insegna a capire cosa si sta guardando quando si apre una traccia.

### **2. Ambiente consigliato**

Per questi esercizi non serve creare una nuova VM.

Puoi lavorare in due modi:

- **Windows host:** comodo per aprire velocemente le tracce `.pcap` con Wireshark e studiare l'interfaccia grafica.
- **Ubuntu VM:** utile se vuoi usare anche `tshark`, `tcpdump`, `scapy` e confrontare le osservazioni da CLI.

Per le tracce già contenute nello ZIP, Windows e Linux sono equivalenti: sono file offline. Per catturare traffico generato dai container Docker SEED, invece, bisogna usare Ubuntu VM, perché il traffico passa sulle interfacce Docker interne.

> ✅ Regola pratica: tracce statiche del docente su Windows o Linux; traffico Docker SEED su Ubuntu VM.

### **3. Preparare i file**

Se lavori in Ubuntu VM, copia l'archivio dalla cartella condivisa o dal repository nella cartella di laboratorio:

```bash
mkdir -p ~/ssri-lab/wireshark
cp /media/sf_VM_Shared/wireshark-traces.zip ~/ssri-lab/wireshark/ 2>/dev/null || true
cd ~/ssri-lab/wireshark
unzip wireshark-traces.zip -d traces
find traces -maxdepth 1 -type f | sort
```

Se lavori da Windows, basta aprire lo ZIP e poi aprire ogni traccia con Wireshark tramite `File -> Open`.

### **4. Inventario delle tracce**

L'archivio contiene un set molto ampio:

- `http-ethereal-trace-1`, `http-ethereal-trace-2`, `http-ethereal-trace-3`, `http-ethereal-trace-4`, `http-ethereal-trace-5`: esercizi HTTP.
- `ethernet-ethereal-trace-1`: Ethernet, ARP e HTTP.
- `icmp-ethereal-trace-1`, `icmp-ethereal-trace-2`: ping e traceroute ICMP.
- `tcp-ethereal-trace-1`: connessione TCP con upload HTTP `POST`.
- `dns-ethereal-trace-1`, `dns-ethereal-trace-2`, `dns-ethereal-trace-3`, `dns-ethereal-trace-4`: query DNS, risposte, record A, NS e PTR.
- `dhcp-ethereal-trace-1`: DHCP e traffico broadcast locale.
- `udp-wireshark-trace.pcap`: traffico DNS su UDP più traffico applicativo.
- `ip-ethereal-trace-1`: datagrammi IP, TTL, ICMP e frammentazione/diagnostica IP.
- `NAT_home_side.pcap`, `NAT_ISP_side.pcap`: confronto prima/dopo NAT.
- `ssl-ethereal-trace-1`: handshake SSL/TLS e traffico cifrato.
- `Wireshark_802_11.pcap`: traffico wireless 802.11.

> 💡 Il valore di queste tracce è che isolano scenari diversi. Non devi aprirle tutte insieme: apri una traccia, applica il filtro del protocollo, rispondi alle domande, poi passa alla successiva.

### **5. Metodo di lavoro per ogni traccia**

Per ogni esercizio conviene seguire sempre lo stesso schema:

1. Apri la traccia.
2. Osserva la lista dei pacchetti senza filtro.
3. Elenca i protocolli principali visibili nella colonna `Protocol`.
4. Applica un filtro di visualizzazione mirato, ad esempio `http`, `arp`, `icmp`, `tcp`, `dns`, `dhcp`, `tls`.
5. Seleziona il pacchetto chiave.
6. Espandi i livelli: `Frame`, `Ethernet`, `IP`, `TCP/UDP/ICMP`, protocollo applicativo.
7. Leggi il campo richiesto dalla domanda.
8. Controlla se la risposta è coerente con l'incapsulamento.

### **6. Capture filter e display filter**

Nei laboratori del docente si usa soprattutto il filtro di visualizzazione, cioè quello scritto nella barra in alto di Wireshark dopo avere già catturato o aperto una traccia.

Esempi:

```text
http
arp
icmp
tcp
dns
ip.addr == 192.168.1.102
tcp.port == 80
udp.port == 53
```

Il capture filter, invece, riduce ciò che viene catturato fin dall'inizio. È utile nelle catture live, ma negli esercizi su tracce statiche non serve.

> ⚠️ Non confondere i due filtri: `tcp.port == 80` è un display filter Wireshark; `tcp port 80` è un capture filter BPF.

### **7. Perché il docente collega questi lab al port scanning**

Questa unità è collocata in M4/UD3, cioè network e port scanning. A prima vista i lab sembrano generali, ma servono esattamente per imparare a riconoscere i segnali che poi lo scanning produce:

- ARP rivela chi sta risolvendo indirizzi locali.
- ICMP mostra reachability, TTL e percorso.
- TCP mostra handshake, flag e porte.
- DNS mostra la risoluzione dei nomi e i server coinvolti.
- HTTP mostra richieste, risposte, header, autenticazione e oggetti embedded.
- NAT mostra come cambiano indirizzi e porte tra rete interna ed esterna.

> ✅ Chi sa leggere questi protocolli in Wireshark riconosce molto più facilmente scan, fingerprinting, tentativi di enumerazione e traffico anomalo.

