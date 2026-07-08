# Soluzione Laboratorio SSRI — 02/07/2025

**Docenti: S. Cimato – M. Anisetti** · Prova open book

Riferimenti di metodo: modulo [M7 – Wireshark e Firewall](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L1%20-%20Interfaccia,%20cattura%20e%20anatomia%20di%20una%20traccia.md).

> ℹ️ La traccia del **12/09/2025** usa **la stessa identica cattura** (file con MD5 identico) e **lo stesso esercizio firewall**: la presente soluzione vale integralmente anche per quell'appello.

---

## Traccia originale dell'appello

**Cognome:**  
**Nome:**  
**Matricola:**

**Sicurezza – SSRI - Laboratorio**

**Docenti:** S. Cimato – M. Anisetti  
**Appello del 02/07/2025**

È permesso utilizzare manuali e documentazione dei tool, ma non comunicare con altri.

### **Esercizio su Wireshark**

Esaminando il traffico reso disponibile alla pagina:

```text
https://homes.di.unimi.it/cimato/SSR/esame/2lug
```

commentare e fare considerazioni su:

1. Quali sono i nodi IP coinvolti nella prima connessione TCP?
   a. Quali sono i MAC dei dispositivi coinvolti?
   b. Quali sono le caratteristiche hardware/software dei nodi server e client?

2. Quali sono i file richiesti?

3. Dare una descrizione in breve della sessione.

4. Qual è il tentativo di phishing che si sta mettendo in atto?

### **Esercizio su firewall**

- La sottorete della reception contiene i nodi PC1 e PC2.
- La sottorete dei servizi contiene un server di backup (HTTPS e TCP 445), un servizio di autenticazione degli AP (HTTPS) e un database (TCP 5432).
- La sottorete reception può accedere al server di backup e al servizio di autenticazione.
- La sottorete guests contiene un AP WiFi in modalità bridge.
- L'AP WiFi comunica con il servizio di autenticazione per registrare gli accessi.
- L'AP WiFi comunica con il servizio di autenticazione per registrare gli accessi.
- I dispositivi connessi all'AP WiFi possono comunicare solo con Internet.
- Il servizio di autenticazione comunica con il database.
- Il server di backup è esposto a Internet sulla porta 5000.
- Il restante traffico deve essere vietato.

Utilizzare esclusivamente la seguente tabella per descrivere le regole del firewall.

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|

---

## Parte 1 — Esercizio su Wireshark (traccia `2lug`)

> **Riferimenti**: [M7/UD1/L5 — Estrazione di file e phishing](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L5%20-%20Estrazione%20di%20file,%20credenziali%20e%20individuazione%20di%20phishing.md) (§1 Export Objects, §4 phishing/malware), [M7/UD1/L4 — Analisi per protocollo](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L4%20-%20Analisi%20per%20protocollo%20%28ARP,%20DNS,%20TCP,%20TLS,%20HTTP,%20FTP%29.md)

Da `Statistics → Protocol Hierarchy` la cattura contiene HTTP (porta 80), DNS, NetBIOS/LLMNR e una connessione TCP su porta alta insolita. `Statistics → Conversations` mostra un unico host locale, `10.6.27.102`, che parla con più IP esterni.

**1. Nodi della prima connessione TCP.** Con `tcp.stream eq 0` il primo SYN è **`10.6.27.102:49157 → 23.63.254.163:80`** (a `t ≈ 14,2 s`). Il client locale è `10.6.27.102`.

**1a. MAC coinvolti.** Client `10.6.27.102` → MAC **`00:08:02:1c:47:ae`**; essendo `23.63.254.163` un indirizzo remoto, il MAC di destinazione è quello del **gateway**, **`20:e5:2a:b6:93:f1`**. Gli OUI risolvono: `00:08:02` → **Hewlett-Packard** (l'host), `20:e5:2a` → **ASUSTek** (il router di casa/gateway).

**1b. Caratteristiche hardware/software.** Hardware: PC **HP** (OUI del client), gateway **ASUS**. Software del client dallo `User-Agent`: **`Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0)`** → **Windows 7 a 64 bit con Internet Explorer 11**. La primissima connessione, verso `23.63.254.163:80` con `Host: www.msftncsi.com` e `GET /ncsi.txt`, è il **controllo di connettività di Windows** (Microsoft NCSI): traffico di sistema del tutto **legittimo**, non l'attacco.

**2. File richiesti.** Da `File → Export Objects → HTTP` (o `Statistics → HTTP → Requests`) emergono, oltre al benigno `/ncsi.txt`, due download dall'host **`smart-fax.com`** (`107.180.50.162`):
- **`/Documents/Invoice&MSO-Request.doc`** — un documento Word (`Content-Type: application/msword`);
- **`/knr.exe`** — un **eseguibile Windows** (`Content-Type: application/x-msdownload`).

**3. Descrizione della sessione.** La sequenza ricostruisce una **catena di infezione**. Dopo il controllo di connettività Windows (`www.msftncsi.com`), l'host risolve via DNS **`smart-fax.com`** e ne scarica il finto documento di fattura `Invoice&MSO-Request.doc`; subito dopo scarica il binario **`knr.exe`**. Infine il DNS risolve il dominio dinamico **`dunlop.hopto.org`** (`23.105.131.229`) e l'host apre connessioni ripetute verso di esso sulla **porta 2032** (≈90 pacchetti): è il **canale di comando e controllo (C2)** del malware appena eseguito. La successione *DNS → GET del `.doc` → GET del `.exe` → traffico verso il dominio `hopto.org`* è la firma dell'intera compromissione.

**4. Tentativo di phishing in atto.** Il phishing è il **finto documento di fattura** (`Invoice&MSO-Request.doc`) ospitato su `smart-fax.com`: un'**esca di ingegneria sociale** che, con il pretesto di una fattura da consultare, induce la vittima ad aprirlo. Il documento innesca il download e l'esecuzione di **`knr.exe`** (il payload malevolo), che stabilisce poi il canale C2 verso **`dunlop.hopto.org:2032`**. L'uso di un **DNS dinamico gratuito** (`.hopto.org`) per il C2 è un ulteriore indicatore classico di attività malevola. In breve: **phishing tramite documento-fattura esca → download di un eseguibile → beacon verso un server C2**.

> ⚠️ Da distinguere dal rumore legittimo: `www.msftncsi.com` (controllo connettività Windows) e le eventuali risoluzioni WPAD/LLMNR **non** fanno parte dell'attacco; il segnale malevolo è tutto e solo nella catena `smart-fax.com` → `knr.exe` → `dunlop.hopto.org`.

---

## Parte 2 — Esercizio su firewall (Reception / Servizi / Guest / Internet)

> **Riferimenti**: [M7/UD2/L2 — DMZ ed esposizione](../../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L2%20-%20DMZ%20ed%20esposizione%20di%20servizi%20verso%20Internet.md), [M7/UD2/L4 — NAT, bridge, port forwarding](../../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L4%20-%20NAT,%20bridge,%20port%20forwarding%20e%20dual-stack%20IPv4-IPv6.md)

**Topologia.** Firewall con: `eth2 10.0.0.1` verso la **Reception** (`10.0.0.0/24`: PC1 .10, PC2 .11); `eth1 10.0.4.1` verso i **Servizi** (`10.0.4.0/24`: **AP-Auth** `10.0.4.11`, **Backup** `10.0.4.15`, **Database** `10.0.4.10`); `eth0 10.0.1.1` verso l'**AP Guests** (AP `eth0 10.0.1.10`, lato Wi-Fi `wlan0 192.168.4.1`, guest `192.168.4.100`/`.101`); `wan0 75.23.48.1` verso **Internet**. Porte: backup HTTPS 443 e TCP 445 (ed esposto su 5000), auth HTTPS 443, database TCP 5432.

**Policy → chi inizia → servizio:** Reception → Backup (443, 445) e → Auth (443); l'**AP** → Auth (443) per registrare gli accessi; i **dispositivi guest** → **solo Internet**; Auth → Database (5432); Backup **esposto a Internet sulla porta 5000** (port forwarding); resto negato.

**Osservazioni di topologia (importanti).**
- **Auth ↔ Database** stanno entrambi nella sottorete Servizi (`10.0.4.0/24`, stesso switch): il loro traffico **non attraversa il firewall** e quindi non è da esso filtrabile. La regola "il servizio di autenticazione comunica con il database" è **intra-subnet** e non genera righe.
- L'**AP è in bridge**: assumo che i client guest si presentino al firewall con il proprio IP `192.168.4.0/24` (il bridge non fa NAT). È invece l'**AP** (`10.0.1.10`) a contattare il servizio di autenticazione: sono due sorgenti diverse, con permessi diversi.
- Il backup "esposto sulla porta 5000" implica un **DNAT** dall'IP pubblico del firewall (`wan0`) verso `10.0.4.15:5000` (vedi tabella e nota finale).

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Reception→Servizi (backup HTTPS) | 10.0.0.0/24 | 10.0.4.15 | TCP | >1023 | 443 | 0/any | permit |
| Servizi→Reception (risp.) | 10.0.4.15 | 10.0.0.0/24 | TCP | 443 | >1023 | 1 | permit |
| Reception→Servizi (backup 445) | 10.0.0.0/24 | 10.0.4.15 | TCP | >1023 | 445 | 0/any | permit |
| Servizi→Reception (risp.) | 10.0.4.15 | 10.0.0.0/24 | TCP | 445 | >1023 | 1 | permit |
| Reception→Servizi (auth HTTPS) | 10.0.0.0/24 | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| Servizi→Reception (risp.) | 10.0.4.11 | 10.0.0.0/24 | TCP | 443 | >1023 | 1 | permit |
| AP→Servizi (auth HTTPS) | 10.0.1.10 | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| Servizi→AP (risp.) | 10.0.4.11 | 10.0.1.10 | TCP | 443 | >1023 | 1 | permit |
| Guest→Internet | 192.168.4.0/24 | any | TCP | >1023 | 80,443 | 0/any | permit |
| Internet→Guest (risp.) | any | 192.168.4.0/24 | TCP | 80,443 | >1023 | 1 | permit |
| Guest→Internet (DNS) | 192.168.4.0/24 | any | UDP | >1023 | 53 | - | permit |
| Internet→Guest (DNS risp.) | any | 192.168.4.0/24 | UDP | 53 | >1023 | - | permit |
| Internet→Servizi (backup :5000, DNAT) | any | 10.0.4.15 | TCP | >1023 | 5000 | 0/any | permit |
| Servizi→Internet (risp.) | 10.0.4.15 | any | TCP | 5000 | >1023 | 1 | permit |
| any | any | any | any | any | any | any | **deny** |

**Note di lettura.**
- **Isolamento dei guest:** i client `192.168.4.0/24` possono aprire connessioni **solo verso Internet** (80/443 + DNS); non esistendo righe verso Reception o Servizi, il default deny impedisce loro di raggiungere le reti interne, come richiesto.
- **AP ≠ guest:** la registrazione degli accessi è fatta dall'**AP** (`10.0.1.10 → 10.0.4.11:443`), non dai telefoni: distinguere i due soggetti è essenziale per non violare l'isolamento.
- **Reception → backup su 443 e 445:** due servizi distinti sullo stesso host, quindi due coppie di righe (445 è SMB/condivisione).
- **Backup esposto su 5000:** a monte della tabella il firewall fa **port forwarding** (`iptables -t nat -A PREROUTING -i wan0 -p tcp --dport 5000 -j DNAT --to 10.0.4.15:5000`); nella tabella di filtraggio la connessione appare come richiesta in ingresso verso `10.0.4.15:5000`. È l'**unico** servizio interno raggiungibile da Internet.
- **Auth ↔ Database:** intra-subnet, non filtrato dal firewall (nessuna riga), come spiegato sopra.
