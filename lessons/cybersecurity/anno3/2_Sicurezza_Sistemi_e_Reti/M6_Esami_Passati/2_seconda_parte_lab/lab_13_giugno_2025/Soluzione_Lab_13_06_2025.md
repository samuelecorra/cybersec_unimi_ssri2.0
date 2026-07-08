# Soluzione Laboratorio SSRI — 13/06/2025

**Docenti: S. Cimato – M. Anisetti** · Prova open book

Riferimenti di metodo: modulo [M7 – Wireshark e Firewall](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L1%20-%20Interfaccia,%20cattura%20e%20anatomia%20di%20una%20traccia.md).

---

## Traccia originale dell'appello

### **Esercizio su Wireshark**

Esaminando il traffico reso disponibile alla pagina:

```text
https://homes.di.unimi.it/cimato/SSR/esame/13giu
```

commentare e fare considerazioni su:

1. Quali sono i nodi IP coinvolti nella prima connessione TCP?
   a. Quali sono i MAC dei dispositivi coinvolti?
   b. Quali sono le caratteristiche hardware/software dei nodi server e client?

2. Descrivere le prime due sessioni DNS.

3. Dare una descrizione in breve della sessione TLS.

4. Quali sono le richieste e le relative risposte ricevute nella sessione HTTP?

> **Riferimenti**: [M7/UD1/L4 — Analisi per protocollo](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L4%20-%20Analisi%20per%20protocollo%20%28ARP,%20DNS,%20TCP,%20TLS,%20HTTP,%20FTP%29.md) (§1 HW/SW, §3 DNS, §5 TLS, §6 HTTP), [M7/UD1/L3 — Statistiche e Follow Stream](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L3%20-%20Statistiche,%20Conversations,%20Follow%20Stream%20ed%20Expert%20Info.md)

Da `Statistics → Protocol Hierarchy` la cattura è dominata da **TLS (443)** e **DNS**, con una piccola quota **HTTP (80)** quasi tutta dedicata a OCSP: il profilo tipico di una **normale navigazione web cifrata**.

**1. Nodi della prima connessione TCP.** Con il filtro `tcp.stream eq 0`, il primo SYN è **`192.168.120.231:42298 → 34.117.188.166:443`** (a `t ≈ 0,025 s`). Il client è dunque `192.168.120.231`; il server remoto è `34.117.188.166` sulla porta 443 (HTTPS).

**1a. MAC coinvolti.** Il client ha MAC **`00:0c:29:87:4b:76`**; poiché `34.117.188.166` è un indirizzo **remoto** (pubblico), il MAC di destinazione del frame non è quello del server ma quello del **gateway/next-hop**, **`00:50:56:e0:7d:58`**. Entrambi i prefissi OUI — `00:0c:29` e `00:50:56` — appartengono a **VMware, Inc.**

**1b. Caratteristiche hardware/software.** I due MAC VMware dicono subito che siamo in un **ambiente virtualizzato**: client e gateway sono **macchine virtuali VMware** (tipico setup di laboratorio). Il software del client si deduce dal traffico DNS/TLS: le richieste verso `contile.services.mozilla.com`, `push.services.mozilla.com`, `content-signature-2.cdn.mozilla.net` sono servizi interni di **Mozilla Firefox** → il client è un **browser Firefox** su una VM. Il server `34.117.188.166` ricade in spazio **Google Cloud** e ospita il servizio Mozilla *contile* (confermato dall'SNI del Client Hello, vedi punto 3).

**2. Prime due sessioni DNS.** Filtrando `dns`:
- La **prima** risolve `contile.services.mozilla.com`: query di tipo **A** → risposta `34.117.188.166`; la query gemella di tipo **AAAA** (IPv6) non ottiene indirizzi. È la risoluzione che precede immediatamente la prima connessione TCP.
- La **seconda** risolve `content-signature-2.cdn.mozilla.net`: la risposta segue una **catena di CNAME** (`…autograph.services.mozaws.net` → `…webservices.mozgcp.net`) fino all'indirizzo A `34.160.144.191`. È il servizio con cui Firefox verifica le firme dei contenuti.

**3. Descrizione della sessione TLS.** La prima sessione TLS è quella verso `34.117.188.166:443`. Nel **Client Hello** (in chiaro) si legge l'estensione **SNI = `contile.services.mozilla.com`**, che identifica il server richiesto; seguono **Server Hello** (scelta di versione e cipher suite), **Certificate**, lo scambio di chiavi e infine i record di **Application Data cifrati**. Il contenuto applicativo **non è leggibile** (è protetto): tutto ciò che si può affermare sul "cosa" viene dall'handshake e dal certificato, mentre il "dove" è dato dall'SNI. Più avanti nella cattura compaiono altre sessioni TLS, tra cui una verso **`www.exploit-db.com`** (SNI), segno che l'utente ha visitato anche quel sito in HTTPS.

**4. Richieste e risposte HTTP.** Il traffico in chiaro sulla porta 80 è quasi tutto **OCSP** (controllo di validità dei certificati): `POST` verso `r3.o.lencr.org` (Let's Encrypt), `ocsp.digicert.com`, `ocsp.starfieldtech.com`, `o.pki.goog`, con risposta `200 OK` e `Content-Type: application/ocsp-response`. L'unica navigazione HTTP "vera" è verso **`www.example.com`**: `GET / HTTP/1.1` → **`200 OK` (`text/html`)**, seguito da `GET /favicon.ico` → **`404 Not Found`**. Nessuna di queste richieste è malevola.

> ✅ In sintesi: sessione di **navigazione benigna** da una **VM Firefox (VMware)**. Prima connessione TCP verso il servizio Mozilla *contile* (`34.117.188.166:443`), prime due risoluzioni DNS per servizi Firefox, handshake TLS con SNI leggibile e payload cifrato, e traffico HTTP costituito da controlli OCSP dei certificati più una visita a `www.example.com`. Non c'è alcun attacco: l'esercizio verifica la capacità di *descrivere* traffico cifrato e di distinguere il rumore di sistema (OCSP, servizi del browser) dalla navigazione effettiva.

---

### **Esercizio su firewall**

![](imgs/Pasted%20image%2020260708202116.png)

Il server Web ospita http, https, RPC e FTP attivo.  
Il server di posta ospita SMTP.

Configurare il firewall su Router 1 in modo che:

- Solo la LAN può accedere ai servizi RPC nella DMZ.
- Solo https del Web Server è accessibile da fuori.
- FTP attivo del Web Server è accessibile solo da fuori.
- http del server Web è accessibile solo per pc2.
- Il server di posta deve funzionare correttamente secondo SMTP.

Utilizzare esclusivamente la seguente tabella per descrivere le regole del firewall.

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|

> **Riferimenti**: [M7/UD2/L3 — Servizi multi-connessione (FTP attivo, RPC, SMTP)](../../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L3%20-%20Servizi%20multi-connessione%20%28FTP%20attivo-passivo,%20RPC,%20SMTP%29.md), [M7/UD2/L4 — dual-stack](../../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L4%20-%20NAT,%20bridge,%20port%20forwarding%20e%20dual-stack%20IPv4-IPv6.md)

**Topologia.** Router1 con tre lati: `eth0 10.0.0.1 / fc00::1` verso l'**esterno** (ext1 `10.0.0.20 / fc00::20`, cioè Internet); `eth1 10.0.1.1 / fc00:1::1` verso la **DMZ** (Switch1: **Web Server** `10.0.1.10`, **DNS** `10.0.1.11`, **Mail** `10.0.1.12`); `eth2 10.0.2.1 / fc00:2::1` verso la **LAN** (Switch2: pc1 `10.0.2.20`, **pc2 `10.0.2.21`**, pc3 `10.0.2.22`). Il Web Server offre http(80), https(443), RPC(111) e **FTP attivo** (21 controllo + 20 dati); il Mail Server offre SMTP(25).

**Policy → chi inizia → servizio:** solo la **LAN** accede a **RPC** in DMZ; **solo da fuori** è accessibile **HTTPS** del Web Server; **solo da fuori** l'**FTP attivo** del Web Server; **solo pc2** accede all'**HTTP** del Web Server; il **Mail Server** deve ricevere e inviare **SMTP** correttamente. Le regole seguono lo schema richiesta (ACK 0/any) + risposta (ACK 1). Tabella per IPv4 (identica logica su IPv6: replicare con `ip6tables` sostituendo `fc00:*`).

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| LAN→DMZ (RPC) | 10.0.2.0/24 | 10.0.1.10 | TCP | >1023 | 111 | 0/any | permit |
| DMZ→LAN (RPC) | 10.0.1.10 | 10.0.2.0/24 | TCP | 111 | >1023 | 1 | permit |
| Est→DMZ (HTTPS) | any | 10.0.1.10 | TCP | >1023 | 443 | 0/any | permit |
| DMZ→Est (HTTPS) | 10.0.1.10 | any | TCP | 443 | >1023 | 1 | permit |
| Est→DMZ (FTP ctrl) | any | 10.0.1.10 | TCP | >1023 | 21 | 0/any | permit |
| DMZ→Est (FTP ctrl) | 10.0.1.10 | any | TCP | 21 | >1023 | 1 | permit |
| DMZ→Est (FTP dati attivi) | 10.0.1.10 | any | TCP | 20 | >1023 | 0/any | permit |
| Est→DMZ (FTP dati, risp.) | any | 10.0.1.10 | TCP | >1023 | 20 | 1 | permit |
| LAN→DMZ (HTTP, solo pc2) | 10.0.2.21 | 10.0.1.10 | TCP | >1023 | 80 | 0/any | permit |
| DMZ→LAN (HTTP, solo pc2) | 10.0.1.10 | 10.0.2.21 | TCP | 80 | >1023 | 1 | permit |
| Est→DMZ (SMTP in) | any | 10.0.1.12 | TCP | >1023 | 25 | 0/any | permit |
| DMZ→Est (SMTP in, risp.) | 10.0.1.12 | any | TCP | 25 | >1023 | 1 | permit |
| DMZ→Est (SMTP out) | 10.0.1.12 | any | TCP | >1023 | 25 | 0/any | permit |
| Est→DMZ (SMTP out, risp.) | any | 10.0.1.12 | TCP | 25 | >1023 | 1 | permit |
| any | any | any | any | any | any | any | **deny** |

**Note di lettura.**
- **FTP attivo (il punto delicato):** la connessione di **controllo** è aperta dal client esterno verso `21` (righe 5–6), ma la connessione **dati** in modalità attiva è aperta **dal server** dalla propria porta **20** verso la porta effimera del client (righe 7–8): il pacchetto di apertura ha quindi **sorgente porta 20 e ACK 0/any in uscita**. È l'iniziativa "rovesciata" che rende l'FTP attivo ostico per i firewall.
- **"Solo da fuori" / "solo pc2":** non essendoci righe che permettono alla LAN di aprire HTTPS o FTP verso il Web Server, il default deny le vieta (accesso solo dall'esterno); l'HTTP è limitato all'IP sorgente `10.0.2.21` (pc2), quindi pc1 e pc3 non possono raggiungerlo.
- **SMTP nei due versi:** il mail server **riceve** posta (iniziativa esterna verso la porta 25, righe 11–12) e **invia** posta (iniziativa propria verso la porta 25 dei destinatari, righe 13–14); servono entrambe le coppie perché "funzioni correttamente".
- **RPC:** si autorizza la sola porta 111 (portmapper) dalla LAN; RPC negozia poi porte dinamiche che un filtro statico non copre integralmente (servirebbe un helper stateful), ma la policy chiede esattamente di limitare l'accesso RPC alla LAN.
- **DNS server (`10.0.1.11`):** la policy non ne richiede l'accesso, perciò non compaiono righe e resta irraggiungibile attraverso il firewall (default deny).
- **Dual-stack:** la stessa tabella va replicata per gli indirizzi `fc00:*` con **`ip6tables`**; filtrare solo l'IPv4 lascerebbe l'IPv6 completamente aperto.
