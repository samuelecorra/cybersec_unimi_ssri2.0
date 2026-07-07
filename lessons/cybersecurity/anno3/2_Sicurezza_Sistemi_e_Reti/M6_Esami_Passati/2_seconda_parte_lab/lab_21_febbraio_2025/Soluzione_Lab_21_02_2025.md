# Soluzione Laboratorio SSRI — 21/02/2025

**Docenti: S. Cimato – M. Anisetti** · Prova open book (ammessi manuali e documentazione dei tool)

Riferimenti di metodo: [UD1 Wireshark](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L1%20-%20Interfaccia,%20cattura%20e%20anatomia%20di%20una%20traccia.md) e [UD2 Firewall](../../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L1%20-%20Metodologia%20della%20tabella%20di%20filtraggio%20%28le%208%20colonne%20e%20il%20flag%20ACK%29.md) del modulo M7.

---

## Parte 1 — Esercizio su Wireshark (traccia `21feb`)

> **Riferimenti**: [M7/UD1/L4 — Analisi per protocollo](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L4%20-%20Analisi%20per%20protocollo%20%28ARP,%20DNS,%20TCP,%20TLS,%20HTTP,%20FTP%29.md) (§7 FTP), [M7/UD1/L5 — Estrazione e attacchi](../../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L5%20-%20Estrazione%20di%20file,%20credenziali%20e%20individuazione%20di%20phishing.md) (§3 credenziali, §5 brute-force)

Aprendo la traccia e guardando `Statistics → Protocol Hierarchy`, la cattura è quasi interamente **FTP sulla porta 21**: è il segnale che l'esercizio ruota attorno a una (o più) sessioni FTP. `Statistics → Conversations` mostra un'unica coppia di host che si scambia decine di migliaia di pacchetti.

**1. Nodi IP coinvolti nelle sessioni FTP.** La conversazione avviene fra due soli host: il **server FTP `10.121.70.151`** (che ascolta sulla porta 21) e il **client `10.234.125.254`** (che apre le connessioni da porte effimere). Tutte le sessioni FTP sono fra questi due nodi.

**1a. MAC dei dispositivi.** Espandendo *Ethernet II* (o dalla scheda Ethernet delle Conversations): il server ha MAC **`00:01:96:3c:3f:a8`**, il client **`00:d0:59:aa:af:80`**. I prefissi OUI risolvono i produttori: `00:01:96` → **Cisco Systems**, `00:d0:59` → **Ambit Microsystems** (fornitore di schede di rete/modem). Essendo entrambi sulla stessa LAN, i due MAC compaiono direttamente come sorgente/destinazione dei frame.

**1b. Caratteristiche hardware/software.** Dall'OUI si deduce l'hardware: NIC Cisco lato server, Ambit Microsystems lato client. Il **software del server** si legge dal banner di benvenuto, che è il minimale **`220 FTP Service`**: non espone né nome né versione del demone (banner volutamente scarno, buona pratica di *security through minimal disclosure*). Il client non è un browser (niente `User-Agent`), ma il comportamento lo tradisce: **circa 1400 tentativi di login in ~44,6 secondi** sono impossibili per un operatore umano e rivelano uno **strumento automatico di brute-force** (tipo Hydra/Medusa/Ncrack).

**1c. Quando inizia la connessione FTP.** La prima connessione di controllo si apre con il primo **SYN verso la porta 21** all'inizio della cattura (`t ≈ 0 s`, colonna *Time* relativa). Da lì in poi il client apre e chiude ripetutamente connessioni di controllo per ritentare l'autenticazione.

**2. Utenti e password coinvolti.** Filtrando `ftp` (o con *Tools → Credentials*) si legge in chiaro il dialogo di autenticazione. Il client prova due account, **`admin`** e **`administrator`**, contro un **dizionario** di password: `merlin`, `mercury`, `mets`, `mgr`, `mickey`, `michael`, `michelle`, `michele`, `minimum`, `mit`, `minsky`, `mike`, `modem`, `mogul`, `moose`, `monica`, `mouse`, `mozart`, `napoleon`, `nancy`… (parole in ordine alfabetico, tipiche di un wordlist). In totale circa **1400 tentativi** con **oltre 700 password distinte**. Il server risponde a ogni tentativo con **`530 Login incorrect`**: **nessuna password è corretta** e **non compare mai un `230` (login riuscito)**. L'attacco, per quanto è contenuto nella cattura, **fallisce**.

**3. Descrizione delle sessioni FTP.** Si tratta di un **attacco di forza bruta a dizionario** contro il servizio FTP. Il client `10.234.125.254` apre in rapida successione connessioni di controllo verso `10.121.70.151:21`, invia `USER admin`/`administrator` seguito da `PASS <parola>`, riceve `331 Password required` e poi `530 Login incorrect`, quindi ritenta con la password successiva. Il ritmo (≈1400 tentativi in ~45 s) conferma l'automazione. Nessuna sessione arriva alla fase autenticata.

**4. Comandi FTP e file scambiati.** Gli unici comandi presenti sono quelli di **autenticazione**: `USER` e `PASS`, con le risposte `220`/`331`/`530`. **Non compare alcun comando di navigazione o trasferimento** (`LIST`, `CWD`, `RETR`, `STOR`, `PASV`/`PORT`) e **non viene aperto alcun canale dati** (`ftp-data` è vuoto). Di conseguenza **nessun file viene scambiato** — coerente con il fatto che il brute-force non ha mai ottenuto l'accesso.

> ✅ In sintesi: brute-force FTP automatico e **fallito**, da `10.234.125.254` (OUI Ambit) verso il server `10.121.70.151` (OUI Cisco, banner `220 FTP Service`), account `admin`/`administrator`, ~700 password di dizionario, tutte respinte con `530`, nessun file trasferito.

---

## Parte 2 — Esercizio su firewall

> **Riferimenti**: [M7/UD2/L1 — Tabella e flag ACK](../../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L1%20-%20Metodologia%20della%20tabella%20di%20filtraggio%20%28le%208%20colonne%20e%20il%20flag%20ACK%29.md), [M7/UD2/L2 — DMZ ed esposizione](../../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L2%20-%20DMZ%20ed%20esposizione%20di%20servizi%20verso%20Internet.md)

**Topologia.** Firewall a quattro interfacce: `eth0 10.0.0.1` verso gli **Sviluppatori** (`10.0.0.0/24`: PC1 .10, PC2 .11, PC3 .12), `eth1 10.0.1.1` verso la **Segreteria** (`10.0.1.0/24`: PC4 .10, PC5 .11, PC6 .12), `eth2 10.0.4.1` verso la **DMZ Servizi** (`10.0.4.0/24`: Database .10, Web Server .11, Servizi segreteria .12), `eth3 75.23.47.1` verso **Internet**. Porte: HTTPS = 443, database anche su TCP 5432.

**Osservazione di topologia (importante).** Database, Web Server e Servizi segreteria stanno **tutti nella stessa sottorete DMZ** (Switch DMZ). Il traffico *fra loro* resta sullo switch e **non attraversa il firewall**, quindi non è da esso filtrabile: la regola "il web server ha accesso al database" è intra-DMZ e non genera righe. Le righe della tabella riguardano solo i flussi che **cambiano interfaccia**: Sviluppatori→DB, Segreteria→Web, Segreteria→Servizi, Internet→Web.

**Regole richieste (chi inizia → servizio):** Sviluppatori → Database (443 e 5432); Segreteria → Web Server (443) e → Servizi segreteria (443); Internet → Web Server (443). Sviluppatori↮Segreteria isolate (garantito dal default deny). Ogni connessione consentita = riga di richiesta (ACK 0/any) + riga di risposta (ACK 1).

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Svil→DMZ | 10.0.0.0/24 | 10.0.4.10 | TCP | >1023 | 443 | 0/any | permit |
| DMZ→Svil | 10.0.4.10 | 10.0.0.0/24 | TCP | 443 | >1023 | 1 | permit |
| Svil→DMZ | 10.0.0.0/24 | 10.0.4.10 | TCP | >1023 | 5432 | 0/any | permit |
| DMZ→Svil | 10.0.4.10 | 10.0.0.0/24 | TCP | 5432 | >1023 | 1 | permit |
| Segr→DMZ | 10.0.1.0/24 | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| DMZ→Segr | 10.0.4.11 | 10.0.1.0/24 | TCP | 443 | >1023 | 1 | permit |
| Segr→DMZ | 10.0.1.0/24 | 10.0.4.12 | TCP | >1023 | 443 | 0/any | permit |
| DMZ→Segr | 10.0.4.12 | 10.0.1.0/24 | TCP | 443 | >1023 | 1 | permit |
| Internet→DMZ | any | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| DMZ→Internet | 10.0.4.11 | any | TCP | 443 | >1023 | 1 | permit |
| any | any | any | any | any | any | any | **deny** |

**Note di lettura.** L'isolamento Sviluppatori↔Segreteria non richiede righe di *deny* esplicite: non essendoci alcuna regola di *permit* fra le due sottoreti, il *default deny* finale le tiene separate. Il web server è raggiungibile da Internet **solo** in HTTPS (porta 443), non in altri protocolli, e **solo** verso `10.0.4.11`: nessun'altra riga in ingresso dall'esterno esiste. Le porte note (443, 5432) compaiono come **destinazione** nella richiesta e come **sorgente** nella risposta con `ACK=1`, così che dall'esterno non si possa aprire una connessione verso l'interno (un SYN in ingresso con ACK=0 non trova regole e cade nel deny).
