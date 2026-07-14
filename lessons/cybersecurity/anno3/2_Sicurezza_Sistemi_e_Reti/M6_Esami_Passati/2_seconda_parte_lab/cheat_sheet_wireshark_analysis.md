# 🦈 Cheat Sheet Enciclopedica — Analisi di traffico con Wireshark (Esame Laboratorio SSRI)

> 📌 **A cosa serve questo file.** È la guida operativa da tenere aperta **durante** la prova di laboratorio (open book). Ti accompagna dal momento in cui clicchi il link del docente per scaricare la cattura fino alla stesura della risposta scritta, spiegando **cosa guardare, in che ordine, quali scorciatoie usare e come passare da un indizio alla diagnosi**. È scritta per non dare nulla per scontato: se segui le fasi nell'ordine, non ti perdi mai nel mare di pacchetti. I riferimenti di teoria puntano al modulo [M7 – Wireshark e Firewall](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L1%20-%20Interfaccia,%20cattura%20e%20anatomia%20di%20una%20traccia.md); i tre esami già risolti sono nella cartella accanto a questo file.

---

### **0. Il principio guida: non leggere mai i pacchetti uno per uno**

La tentazione, appena aperta una cattura da migliaia di righe, è scorrere l'elenco dei pacchetti dall'alto sperando di "vedere" l'attacco. **È l'errore da evitare a ogni costo.** Una cattura d'esame può contenere da qualche migliaio a decine di migliaia di pacchetti (i tre esami visti vanno da ~1.800 a ~19.700 pacchetti): a occhio non concluderesti nulla in tempo utile.

Il metodo corretto è **a imbuto** (in inglese *top-down*): si parte dalle viste **aggregate** (statistiche che riassumono l'intera cattura in poche righe), si formula un'ipotesi su *cosa* sta succedendo e *chi* è coinvolto, e solo alla fine si scende ai singoli pacchetti — ma già sapendo **quali** filtrare. Ogni fase di questa guida restringe l'imbuto:

1. **Controlli preliminari** (uguali per ogni esercizio) → capisci le dimensioni e la "forma" del traffico.
2. **Protocol Hierarchy** → capisci *quale protocollo* domina, e quindi *che tipo* di esercizio è.
3. **Conversations / Endpoints** → capisci *chi parla con chi* (spesso risolve già la domanda sui nodi IP e sui MAC).
4. **Display filter mirati + Follow Stream** → scendi al dettaglio solo sul traffico rilevante.
5. **Deduzione e stesura** → traduci gli indizi tecnici in frasi da mettere sul foglio.

> 💡 Tienilo a mente come mantra: **prima le statistiche, poi i filtri, infine i pacchetti.** Il 70% delle risposte d'esame si ricava senza mai aprire un singolo pacchetto in dettaglio.

---

### **1. Fase 0 — Prima ancora di aprire Wireshark**

#### **1.1. Scaricare la cattura dal link del docente**

Nella traccia il professore fornisce un URL del tipo `https://homes.di.unimi.it/cimato/SSR/esame/<data>`. Cliccandolo, il browser scarica un file di cattura. Il file può essere in uno di due formati, ed è importante sapere che **per Wireshark sono equivalenti**:

- **`.pcap`** (*Packet CAPture*, formato storico basato su libpcap): il formato classico, più compatto, senza fronzoli.
- **`.pcapng`** (*pcap Next Generation*): il formato moderno e oggi predefinito. Aggiunge metadati (commenti sui pacchetti, informazioni su più interfacce di cattura, annotazioni), ma **contiene gli stessi pacchetti** e si analizza in modo assolutamente identico.

> ⚠️ **Non farti confondere dal formato.** Qualunque dei due tu scarichi, ogni filtro, statistica e funzione di Wireshark descritta qui funziona allo stesso modo. Il formato del file **non cambia nulla** nel metodo di analisi. L'esame del 21/02/2025 era in `.pcapng`, quelli del 13/06 e 02/07 in `.pcap`: stesso approccio.

#### **1.2. Aprire il file in Wireshark**

Tre modi, tutti equivalenti:
- **Trascina** il file scaricato sopra la finestra di Wireshark (drag & drop).
- Menu **`File → Open`**.
- Scorciatoia **`Ctrl+O`**.

#### **1.3. (Facoltativo ma professionale) l'impronta del file**

Se la traccia lo chiede o se vuoi dimostrare rigore, puoi calcolare l'**hash MD5/SHA del file** per identificarlo univocamente. Serve, per esempio, a dimostrare che **due catture sono identiche byte per byte**: è esattamente il caso degli appelli **02/07/2025 e 12/09/2025**, i cui file hanno lo **stesso MD5** (`2b3d5a45e8a1d157a2bd577980eb044c`) — quindi sono lo stesso identico esercizio riproposto. In laboratorio lo calcoli da terminale (`md5sum file.pcap` su Linux, `Get-FileHash -Algorithm MD5 file.pcap` in PowerShell). Non è quasi mai richiesto, ma è un'informazione che "colpisce" nella risposta.

---

### **2. Fase 1 — Preparare l'ambiente di visualizzazione (30 secondi che valgono oro)**

Prima di analizzare, imposta Wireshark perché ti mostri le informazioni utili senza doverle cercare. Sono impostazioni che valgono per **qualunque** esercizio.

#### **2.1. Il formato del tempo**

`View → Time Display Format → Seconds Since Beginning of Capture` (di solito è già il default). Così la colonna **Time** mostra i secondi trascorsi dall'inizio della cattura: tutti i riferimenti temporali di questa guida e delle soluzioni (`t ≈ 14,2 s`, ecc.) usano questo formato. In alternativa, `UTC Time of Day` ti dà l'orario reale, utile se la domanda chiede "a che ora è avvenuto".

> 💡 **Trucco del delta.** Se ti serve misurare *quanto tempo passa* tra due pacchetti (es. tra la richiesta e la risposta, o tra un download e il successivo), clicca col destro sul primo pacchetto → **Set/Unset Time Reference** (`Ctrl+T`): da lì in poi la colonna Time riparte da zero. Oppure aggiungi la colonna *Delta time displayed*.

#### **2.2. La risoluzione dei nomi (name resolution)**

`View → Name Resolution`:
- **Resolve Physical Addresses** (attivo di default): traduce i primi 3 byte del MAC nel nome del produttore (è la **risoluzione OUI**, vedi §7). È il motivo per cui vedi `HewlettPacka_1c:47:ae` invece di `00:08:02:1c:47:ae`. **Tienila accesa**: risponde da sola alla domanda "che hardware è?".
- **Resolve Network Addresses** (spesso spento): tenterebbe un reverse-DNS degli IP. In esame **lascialo spento**, perché genera traffico verso l'esterno e può rallentare/falsare; i nomi degli host li ricavi dal traffico DNS/SNI già presente nella cattura, che è la cosa che l'esame vuole verificare.

#### **2.3. Le colonne utili**

Le colonne di default (No., Time, Source, Destination, Protocol, Length, Info) bastano quasi sempre. Quando servono, aggiungi una colonna al volo: **clic destro su un campo nel pannello di dettaglio → Apply as Column**. Le più utili in esame:
- **SNI** (dal Client Hello TLS, campo *Server Name*): trasforma una cattura cifrata nell'elenco dei siti visitati.
- **HTTP Host** e **HTTP User-Agent**: identità del server contattato e del software client.
- **TCP Stream index**: numero del flusso, per raggruppare a colpo d'occhio.

---

### **3. Fase 2 — I controlli preliminari da fare SEMPRE (a prescindere dall'esercizio)**

Questi quattro passaggi si eseguono **identici** su ogni cattura, prima ancora di sapere che tipo di attacco (o non-attacco) ci sia dietro. In due minuti ti danno la mappa del territorio.

#### **3.1. `Statistics → Capture File Properties` — le dimensioni del problema**

Ti dice, in una schermata: **numero totale di pacchetti**, **durata** della cattura, byte totali, e (se presenti) i commenti lasciati dall'autore. Serve a **calibrare le aspettative**:
- Pochi pacchetti su lunga durata (es. 1.790 pacchetti in ~14 minuti) → probabile navigazione/attività umana rada, con del "rumore" di sistema.
- Moltissimi pacchetti su brevissima durata (es. 19.730 pacchetti in ~45 secondi) → **automazione**: un essere umano non genera 400+ eventi al secondo. È già un fortissimo indizio di *brute-force*, *flooding* o *scanning*.

> 💡 La sola coppia **(pacchetti, durata)** è spesso il primo indizio diagnostico. Il rapporto pacchetti/secondo distingue l'attività umana (unità o decine al secondo) da quella automatica (centinaia o migliaia al secondo).

#### **3.2. `Statistics → Protocol Hierarchy` — che tipo di esercizio è**

È **la vista più importante di tutte**. Mostra ad albero i protocolli presenti e la percentuale di pacchetti per ciascuno. Il protocollo dominante ti dice immediatamente su cosa ruota l'esercizio e qual è il **primo filtro** da scrivere:
- Dominano **FTP / porta 21** → esercizio su FTP (credenziali, trasferimenti, o brute-force). Filtro: `ftp`.
- Dominano **TLS / porta 443** → navigazione cifrata (descrivere handshake, SNI, certificati). Filtro: `tls`.
- Molto **HTTP / porta 80** → richieste web in chiaro, download di file, possibile malware/phishing. Filtro: `http`.
- Molto **DNS** → risoluzioni; guarda *quali nomi* vengono risolti (spesso rivela C2, tunneling, o semplicemente i siti visitati).
- Molto **ARP** → attività di livello 2 (scanning ARP, ARP spoofing/poisoning, MITM).
- **ICMP** consistente → ping sweep, traceroute, o tunneling/DoS.
- Presenza di **porte non standard** (numeri "strani" come 2032, 4444, 31337…) → quasi sempre **traffico malevolo** (backdoor, canale C2), da annotare subito.

> ⚠️ **LA TRAPPOLA DEL CONTEGGIO (leggila due volte).** La Protocol Hierarchy attribuisce **ogni pacchetto a un solo protocollo: il più alto che riesce a decodificare al suo interno.** Conseguenza pratica che spiazza tutti la prima volta: la riga di un protocollo applicativo mostra **solo** i pacchetti che portano dati di *quel* livello, non l'intera conversazione. Esempio reale (esame 02/07): la riga **HTTP mostra appena 6 pacchetti** — i 3 `GET` e le 3 risposte `200 OK` — mentre gli ACK, gli handshake e i **segmenti intermedi dei download** ("TCP segment of a reassembled PDU") restano contati alla sola riga **TCP** (1.651 pacchetti). Se cerchi "i 1.400 pacchetti HTTP" nella gerarchia non li trovi: sono lì, ma classificati come TCP. **Il numero di pacchetti "di un servizio" si legge con un filtro, non nella gerarchia.**

> 💡 **Come leggere il numero vero per porta.** Scrivi nella barra dei filtri `tcp.port == 80` (o 443, 21, …) e guarda in basso a destra nella **barra di stato** la voce **Displayed**: è il conteggio reale dei pacchetti di quella porta. Sull'esame 02/07: `tcp.port == 80` → **1.389**, `tcp.port == 2032` → **262**, che sommati fanno i 1.651 TCP totali.

#### **3.3. `Statistics → Conversations` — chi parla con chi**

Apre una tabella delle "conversazioni" (coppie di interlocutori), con schede per ogni livello:
- **Scheda Ethernet**: coppie di **indirizzi MAC** con i produttori risolti. Da qui leggi i MAC per la domanda "quali sono i MAC dei dispositivi?".
- **Scheda IPv4 / IPv6**: coppie di **indirizzi IP** con pacchetti/byte scambiati. Da qui leggi i nodi coinvolti: se c'è **una sola coppia** che scambia (quasi) tutto, l'esercizio ruota attorno a loro; se c'è **un host locale** che parla con **molti IP esterni**, quell'host è la vittima/protagonista.
- **Scheda TCP / UDP**: le singole connessioni (porta per porta). Ordinandola per numero di pacchetti scopri i flussi più intensi; **centinaia di connessioni brevissime** verso la stessa porta = automazione (brute-force/scan).

> 💡 **Ordina cliccando sull'intestazione di colonna.** Per *Packets* o *Bytes* decrescenti porti in cima i flussi che "pesano" di più — di solito i download di file o l'attacco. Spunta in basso **"Limit to display filter"** per vedere solo le conversazioni che soddisfano il filtro attivo.

#### **3.4. `Statistics → Endpoints` — l'elenco dei protagonisti**

Come Conversations ma elenca i **singoli** nodi (non le coppie), con quanti pacchetti ha generato ciascuno. La scheda **Ethernet** dà l'elenco dei MAC (e produttori) presenti; la scheda **IPv4** i singoli IP. Utile per contare **quanti host** ci sono in gioco e individuare a colpo d'occhio l'IP più "chiacchierone".

#### **3.5. `Analyze → Expert Information` — gli avvisi automatici**

Wireshark segnala da solo anomalie (ritrasmissioni, reset, pacchetti malformati, connessioni rifiutate) organizzate per gravità (Errors, Warnings, Notes, Chats). Non risolve l'esercizio, ma un'occhiata rapida ti dice se c'è **congestione, scansione (tanti RST), o problemi di connessione** — indizi di scan o DoS.

> ✅ **Riepilogo Fase 2.** Dopo questi cinque sguardi (Capture File Properties → Protocol Hierarchy → Conversations → Endpoints → Expert Info) sai già: quanto è grande la cattura, se è attività umana o automatica, quale protocollo domina, chi sono gli attori (IP e MAC), e se ci sono anomalie. Nella maggioranza degli esami, le domande 1 (nodi IP), 1a (MAC) e parte della descrizione generale sono **già risolte qui**.

---

### **4. Fase 3 — Dalla gerarchia dei protocolli alla diagnosi (albero decisionale)**

Individuato il protocollo dominante nella Protocol Hierarchy, segui il ramo corrispondente. Ogni ramo indica: **cosa filtrare**, **cosa cercare**, **quale attacco/scenario** è tipico.

#### **4.1. Ramo FTP (porta 21 dominante)**

Filtra `ftp`. Guarda il **dialogo di autenticazione** (comandi `USER`/`PASS`) e le risposte numeriche del server:
- Tantissimi tentativi `USER`/`PASS` in pochi secondi, tutte risposte **`530 Login incorrect`** → **brute-force a dizionario fallito**. Se non compare **mai** un `230` (login riuscito), l'attacco non è andato a segno.
- Un `230` seguito da comandi `CWD`, `LIST`, `RETR`, `STOR` → **login riuscito** e trasferimento di file; il canale dati è `ftp-data` (guarda cosa è stato scaricato/caricato).
- Solo `USER`/`PASS`, nessun `RETR`/`STOR`, `ftp-data` vuoto → **nessun file trasferito**.

Domande tipiche: nodi coinvolti, utenti e password provati, esito, comandi usati, file scambiati. Vedi la scheda dettagliata in §8.1 e il caso reale in §9.1.

#### **4.2. Ramo HTTP (porta 80 consistente)**

Filtra `http.request` per vedere **tutte le richieste** in poche righe (in una cattura d'esame sono spesso una manciata). Poi:
- Analizza gli `User-Agent` per identificare browser/OS (o l'assenza di browser: traffico automatico).
- Con `File → Export Objects → HTTP` ottieni la **lista di tutti i file scaricati** con host, nome e `Content-Type`.
- Un download di **`.exe`** (`application/x-msdownload`) o un **documento Office** civetta (`.doc`, `.xls` con nomi tipo *Invoice*, *Fattura*, *Receipt*) → **catena di infezione / phishing**.
- Cerca poi **connessioni verso porte non standard** o domini **DNS dinamici** (`.hopto.org`, `.no-ip.org`, `.ddns.net`) → **canale C2**.

Domande tipiche: nodi, file richiesti, descrizione della sessione, individuazione del phishing. Vedi §8.2 e il caso reale in §9.3.

#### **4.3. Ramo TLS/HTTPS (porta 443 dominante)**

Il contenuto è cifrato: **non leggerai i dati applicativi**. Ma la parte iniziale dell'handshake è in chiaro:
- Filtra `tls.handshake.type == 1` (i **Client Hello**) e leggi l'estensione **SNI** (*Server Name Indication*): ti dice **a quale sito** è diretta ogni sessione cifrata, pur senza decifrare nulla.
- Il **Server Hello** rivela versione TLS e cipher suite scelte; il **Certificate** rivela l'identità del server e la CA.
- Se domina TLS con DNS e un po' di OCSP (porta 80 verso `ocsp.*`), è quasi sempre **navigazione web benigna**: la domanda ti chiede di **descrivere** traffico cifrato, non di trovare un attacco.

Domande tipiche: descrivere l'handshake, dire quali siti sono stati contattati (dall'SNI), distinguere il traffico "vero" dal rumore di sistema. Vedi §8.4 e il caso reale in §9.2.

#### **4.4. Ramo DNS (molte query)**

Filtra `dns`. Guarda **quali nomi** vengono risolti e **l'ordine**: la sequenza delle risoluzioni racconta la storia (prima risolvo il sito civetta, poi scarico, poi risolvo il dominio C2). Query verso nomi **lunghi e casuali** o un volume anomalo di query TXT → possibile **DNS tunneling/exfiltration**. Nomi di **DNS dinamico** → probabile C2.

#### **4.5. Ramo ARP (molto traffico livello 2)**

Filtra `arp`. Pattern tipici:
- Un host che invia **richieste ARP a raffica per tutta la sottorete** (`who has 10.0.0.1? … .2? … .3?`) → **ARP/host scanning** (ricognizione).
- **Due MAC diversi che rivendicano lo stesso IP**, o *gratuitous ARP* sospetti → **ARP spoofing / poisoning** (preludio a un MITM). Wireshark spesso lo segnala in Expert Info come "duplicate use of address".

#### **4.6. Ramo ICMP / porte strane / flooding**

- Tanti **ICMP echo** verso IP consecutivi → **ping sweep** (ricognizione).
- Raffiche di **SYN** senza completamento del three-way handshake, spesso da IP sorgente falsificati → **SYN flooding** (DoS). Filtra `tcp.flags.syn == 1 && tcp.flags.ack == 0`.
- Traffico verso **porte non standard** (4444, 31337, 2032, ecc.) con payload → **backdoor / reverse shell / C2**.

> ✅ **Come usare l'albero.** La Protocol Hierarchy ti mette su un ramo; il ramo ti dà il filtro d'ingresso e l'ipotesi; da lì scendi in dettaglio con gli strumenti della Fase 4-5. Se un esame mescola più protocolli (tipico: DNS + HTTP + porta strana della catena di infezione), segui i rami **in ordine temporale** per ricostruire la sequenza.

---

### **5. Fase 4 — La cassetta degli attrezzi: i display filter**

I **filtri di visualizzazione** (*display filter*) sono il cuore dell'analisi mirata. Si scrivono nella barra in alto (portaci il cursore con **`Ctrl+/`**). La barra diventa **verde** quando la sintassi è valida, **rossa** quando è errata, gialla quando è valida ma "sospetta". Premi Invio per applicare.

> ⚠️ **Non confondere i due tipi di filtro.** I **filtri di cattura** (*capture filter*, sintassi BPF, es. `port 80`) si impostano *prima* di catturare e scartano i pacchetti per sempre. I **filtri di visualizzazione** (sintassi Wireshark, es. `tcp.port == 80`) si applicano *dopo*, su una cattura già salvata, e **nascondono soltanto** senza cancellare: in esame usi **sempre e solo questi ultimi**, perché parti da un file già registrato. Dettagli in [M7/UD1/L2](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L2%20-%20Filtri%20di%20cattura%20e%20di%20visualizzazione.md).

#### **5.1. La sintassi essenziale**

- **Confronto**: `==` (uguale), `!=` (diverso), `>`, `<`, `>=`, `<=`. Es. `ip.addr == 10.0.0.5`, `tcp.port == 443`.
- **Operatori logici**: `&&` (and), `||` (or), `!` (not). Es. `http && ip.addr == 10.6.27.102`.
- **Presenza di un campo**: basta scriverne il nome. Es. `http.request` (tutti i pacchetti che *sono* una richiesta HTTP), `tls.handshake` (tutti gli handshake).
- **Sottostringa / contenuto**: `contains` (byte/testo presente) e `matches` (espressione regolare). Es. `http.host contains "fax"`, `frame matches "(?i)password"`.
- **Parentesi** per raggruppare: `(a || b) && c`.

> 💡 **`ip.addr == X` vs `ip.src == X` / `ip.dst == X`.** `ip.addr` prende il pacchetto se X è **o** sorgente **o** destinazione (tutto il traffico *da e verso* X). Usa `ip.src`/`ip.dst` quando ti interessa **la direzione**. Stesso discorso per `tcp.port` (qualsiasi verso) contro `tcp.srcport`/`tcp.dstport`.

#### **5.2. I filtri d'oro da ricordare a memoria**

| Obiettivo | Filtro | Note |
|---|---|---|
| Prima connessione TCP | `tcp.stream eq 0` | Wireshark numera i flussi TCP nell'ordine in cui compaiono: lo *stream 0* è il primo |
| Un flusso TCP specifico | `tcp.stream eq N` | N si legge nel dettaglio del pacchetto o si imposta come colonna |
| Tutti i SYN "iniziali" | `tcp.flags.syn == 1 && tcp.flags.ack == 0` | il primo in ordine di tempo = inizio della connessione; a raffica = SYN flood/scan |
| Traffico di/verso un host | `ip.addr == 10.6.27.102` | i nodi si prendono dalle Conversations |
| Traffico di una porta | `tcp.port == 80` | il conteggio *Displayed* è il numero vero di pacchetti del servizio |
| Tutte le richieste HTTP | `http.request` | poche righe: tutti i `GET`/`POST` |
| Risposte HTTP | `http.response` | codici di stato, `Content-Type` |
| Comandi FTP | `ftp.request.command == "USER"` | e `"PASS"`, `"RETR"`, `"STOR"`, `"LIST"` |
| Esiti FTP | `ftp.response.code == 530` | 530 = fallito, 230 = login OK, 227 = PASV |
| Client Hello TLS (SNI) | `tls.handshake.type == 1` | l'SNI dice a quale sito va la sessione cifrata |
| Query DNS | `dns.flags.response == 0` | solo le domande; `dns` mostra domande e risposte |
| Attività ARP | `arp` | scan e spoofing di livello 2 |
| Escludere il rumore Windows | `!(nbns \|\| llmnr \|\| dhcp \|\| igmp \|\| mdns)` | lascia in vista solo il traffico interessante |

> 💡 **Il modo più veloce di scrivere un filtro è non scriverlo.** Nel pannello di dettaglio, **clic destro su qualsiasi campo → Apply as Filter → Selected**: Wireshark costruisce il filtro esatto per te. Con **Prepare as Filter** lo mette nella barra senza applicarlo (per comporlo con altri). È utilissimo per campi dalla sintassi che non ricordi.

#### **5.3. Colorare invece di filtrare**

A volte non vuoi *nascondere* il resto, ma **evidenziare**. `View → Coloring Rules` (o clic destro → *Colorize Conversation*) assegna un colore a un pattern: utile per seguire un host in mezzo agli altri senza perdere il contesto temporale.

---

### **6. Fase 5 — Gli strumenti di ricostruzione (dal pacchetto al significato)**

I filtri isolano i pacchetti; questi strumenti li **ricompongono** in qualcosa di leggibile.

#### **6.1. Follow Stream — la ricostruzione della conversazione**

È lo strumento che risponde a "**cosa si sono detti davvero** i due host?". Clic destro su un pacchetto → **Follow →** e scegli il tipo:
- **TCP Stream** (`Ctrl+Alt+Shift+T`): ricostruisce l'intero flusso TCP come un dialogo continuo (client in un colore, server in un altro). Su una sessione FTP mostra tutta la sequenza `USER`/`PASS`/`530`; su una connessione a una backdoor mostra i comandi scambiati.
- **HTTP Stream**: come sopra ma "capisce" l'HTTP — vedi la richiesta con tutti gli header e la risposta completa. **È lo strumento chiave per gli esercizi HTTP**: selezioni un `GET` e leggi richiesta+risposta in un colpo solo.
- **UDP Stream**: per i protocolli su UDP.
- **TLS Stream**: mostra i record TLS; il contenuto resta cifrato (leggibile solo se hai le chiavi, cosa che in esame non hai), ma vedi la struttura dell'handshake.

> 💡 Quando apri Follow Stream, Wireshark **applica automaticamente** il filtro `tcp.stream eq N` corrispondente. Chiudendo la finestra il filtro resta attivo: così ti ritrovi già isolato quel flusso nella lista principale. Il menu a tendina in basso nella finestra permette di saltare da uno stream all'altro.

#### **6.2. Export Objects — estrarre i file trasferiti**

`File → Export Objects → HTTP` (o `→ FTP-DATA`, `→ SMB`, `→ IMF` per la posta) elenca **tutti gli oggetti/file** trasportati, con host, nome, `Content-Type` e dimensione. È il modo diretto per rispondere a "**quali file sono stati richiesti/scaricati?**". Cliccando una riga salti al pacchetto; con *Save* estrai il file su disco.

> ⚠️ **Igiene di sicurezza.** Se la cattura contiene malware (un `.exe`, un documento con macro), `Export Objects` te lo **estrae davvero sul disco**. In esame ti serve solo **leggere nome, `Content-Type`, dimensione** ed eventualmente i primi byte (i *magic bytes*: `MZ` per un eseguibile Windows, `PK` per uno ZIP/Office moderno, `%PDF` per un PDF): **non aprire né eseguire mai** ciò che estrai.

#### **6.3. Credenziali e ricerche mirate**

- **`Tools → Credentials`** (o `Edit → Find Packet`): nelle versioni recenti elenca le credenziali trovate in chiaro nei protocolli non cifrati (FTP, HTTP Basic, Telnet…). Comodo per la domanda "quali utenti e password?".
- **`Edit → Find Packet`** (`Ctrl+F`): cerca una **stringa** nei pacchetti (scegli *Packet bytes* + *String* per cercare nel payload). Utile per trovare una parola chiave (`password`, un nome file, un dominio).

#### **6.4. Le statistiche applicative**

- **`Statistics → HTTP → Requests`**: albero di tutti i domini/URL richiesti — un colpo d'occhio su "dove è andato" il client.
- **`Statistics → DNS`**: riepilogo delle query per tipo.
- **`Statistics → Flow Graph`**: diagramma a frecce della sequenza dei pacchetti tra gli host — utile per *vedere* un handshake o una sequenza d'attacco nel tempo.

---

### **7. Fase 6 — Dedurre hardware e software dei nodi**

Molte domande d'esame chiedono di ricavare **caratteristiche hardware/software** di client e server. Ecco tutte le fonti, in ordine di affidabilità.

#### **7.1. L'OUI del MAC → il produttore dell'hardware**

Un indirizzo MAC è composto da 6 byte: i **primi 3 (l'OUI, *Organizationally Unique Identifier*)** identificano il **produttore** della scheda di rete, assegnati dalla IEEE. Wireshark li risolve automaticamente (§2.2) e mostra il nome. Esempi visti negli esami:

| OUI | Produttore | Interpretazione |
|---|---|---|
| `00:01:96` | Cisco Systems | apparato di rete professionale (server FTP dietro NIC Cisco) |
| `00:d0:59` | Ambit Microsystems | schede/modem consumer (il client attaccante) |
| `00:0c:29`, `00:50:56` | VMware, Inc. | **macchina virtuale** VMware (ambiente di laboratorio) |
| `00:08:02` | Hewlett-Packard | PC HP |
| `20:e5:2a` | NETGEAR | router/gateway domestico |

> 💡 **L'OUI VMware/VirtualBox è un indizio potente.** MAC che iniziano per `00:0c:29`/`00:50:56` (VMware) o `08:00:27` (VirtualBox) tradiscono un **ambiente virtualizzato**: quasi sempre significa che la cattura è stata prodotta in un laboratorio su VM, non su hardware reale. Dirlo nella risposta dimostra attenzione.

#### **7.2. ⚠️ MAC del gateway ≠ MAC del server (concetto chiave d'esame)**

Gli indirizzi MAC hanno validità **solo sul segmento di rete locale**. Quando il client comunica con un **IP remoto** (pubblico, fuori dalla LAN), il MAC di destinazione dei frame **non è quello del server**, ma quello del **gateway/router locale** che fa da primo salto: oltre il router, il frame viene rincapsulato e i MAC originali si perdono. Regola operativa:
- Se l'IP di destinazione è **nella stessa sottorete** del client → il MAC di destinazione è **davvero** quello dell'host di destinazione.
- Se l'IP di destinazione è **remoto** → il MAC di destinazione è quello del **gateway**.

Esempio (esame 02/07): il client `10.6.27.102` contatta `23.63.254.163` (IP pubblico); nel frame il MAC di destinazione `20:e5:2a:b6:93:f1` è il **router NETGEAR** di casa, non il server. Sbagliare qui è l'errore classico: attribuire al server il MAC del gateway.

#### **7.3. Lo User-Agent HTTP → sistema operativo e browser**

Nell'header `User-Agent` delle richieste HTTP il client si autodescrive. Va **saputo leggere**:
- `Windows NT 10.0` = Windows 10/11; `Windows NT 6.1` = **Windows 7**; `NT 6.3` = Windows 8.1.
- `WOW64` o `Win64; x64` = sistema a **64 bit**.
- `Trident/7.0` + `rv:11.0` = **Internet Explorer 11** (Trident è il motore di IE).
- `Gecko`/`Firefox` = Firefox; `Chrome` = Chrome/Chromium; `Safari` senza Chrome = Safari.

Esempio (esame 02/07): `Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko` = **Windows 7 a 64 bit con IE11**.

> 💡 **Due User-Agent diversi dallo stesso host = due software diversi che agiscono.** Sempre nell'esame 02/07, il download del `.exe` porta uno User-Agent `Mozilla/4.0 (compatible; MSIE 7.0; … .NET CLR …)`: è la stringa dei **componenti programmatici di Windows/Office** (WinInet/URLMon), non del browser. Trovare, a pochi secondi di distanza, prima lo User-Agent del browser (l'utente apre il documento) e poi quello di WinInet (un programma scarica il payload) **dimostra** che il secondo download l'ha avviato **il documento appena aperto**, non l'utente: è la prova della catena esca → payload.

#### **7.4. Il banner del server → software e versione del servizio**

Molti servizi in chiaro si presentano con un **banner**: la prima risposta del server. In FTP è il messaggio `220` (es. `220 FTP Service`, volutamente scarno per non rivelare software/versione — buona pratica di *security through minimal disclosure*; oppure `220 ProFTPD 1.3.5 Server` che invece rivela tutto). In SMTP è il `220 mail.example.com ESMTP Postfix`. In HTTP è l'header `Server:` della risposta (`Server: Apache/2.4.41 (Ubuntu)`). Da qui deduci **software e a volte versione** del servizio.

#### **7.5. Il fingerprint TCP → il sistema operativo (indizio secondario)**

I valori di default nel pacchetto SYN (finestra iniziale `Window`, `TTL`, opzioni `MSS`/`Window Scale`/`SACK_PERM` e il loro ordine) variano tra sistemi operativi. `Win=8192, MSS=1460, WS=256, SACK_PERM` è, per esempio, un profilo tipico Windows. È un indizio **debole** (si conferma con User-Agent o banner), ma quando manca tutto il resto può indicare la famiglia di OS.

---

### **8. Ricettario per protocollo (schede operative)**

Schede sintetiche: per ciascun protocollo, cosa filtrare e come rispondere alle domande d'esame. Approfondimenti in [M7/UD1/L4](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L4%20-%20Analisi%20per%20protocollo%20%28ARP,%20DNS,%20TCP,%20TLS,%20HTTP,%20FTP%29.md).

#### **8.1. FTP**

- **Filtri**: `ftp` (canale comandi, porta 21), `ftp-data` (canale dati, i file). Comandi: `ftp.request.command == "USER"`/`"PASS"`/`"RETR"`/`"STOR"`/`"LIST"`. Esiti: `ftp.response.code == 230` (OK) / `530` (fallito) / `227` (entra in passivo).
- **Nodi**: le due estremità della/e connessione/i su porta 21 (Conversations → TCP).
- **Credenziali**: `USER` e `PASS` viaggiano **in chiaro** → leggibili direttamente o via `Tools → Credentials`.
- **Attivo vs passivo**: in **attivo** il *server* apre la connessione dati dalla porta **20** verso il client (comando `PORT`); in **passivo** il *client* apre la dati verso una porta alta indicata dal server (comando `PASV`, risposta `227`). Determina la direzione della connessione dati.
- **Domande tipiche**: nodi, MAC, HW/SW, utenti/password, esito, comandi, file. Caso reale: §9.1.

#### **8.2. HTTP**

- **Filtri**: `http`, `http.request`, `http.response`, `http.request.method == "POST"`, `http.host contains "..."`.
- **Header chiave**: `Host` (dove), `User-Agent` (chi/che software), `Referer` (da quale pagina si arriva — **la sua assenza** su un download suggerisce un link aperto da email, non da navigazione), `Content-Type` (tipo di file), `Location` (redirect nei `3xx`).
- **File**: `File → Export Objects → HTTP`.
- **Segnali di malware/phishing**: download di `.exe`/`.scr`, documenti civetta (*Invoice*, *Fattura*), `Content-Type: application/x-msdownload`, connessioni successive a domini DNS dinamici o porte non standard.
- **Domande tipiche**: nodi, file richiesti, descrizione, individuazione del phishing. Caso reale: §9.3.

#### **8.3. DNS**

- **Filtri**: `dns`, `dns.flags.response == 0` (query), `dns.qry.name contains "..."`.
- **Cosa leggere**: nomi risolti e **ordine temporale**; tipo di record (A = IPv4, AAAA = IPv6, CNAME = alias, MX = mail, TXT); la catena di CNAME per i CDN.
- **Segnali**: nomi lunghi/casuali o troppe TXT → **tunneling/exfiltration**; DNS dinamico → C2.

#### **8.4. TLS/HTTPS**

- **Filtri**: `tls`, `tls.handshake.type == 1` (Client Hello), `tls.handshake.type == 2` (Server Hello), `tls.handshake.type == 11` (Certificate).
- **Cosa leggere**: **SNI** (nel Client Hello, in chiaro → il sito richiesto), versione TLS e cipher suite (Server Hello), identità del server e CA (Certificate).
- **Limite**: i dati applicativi sono cifrati; puoi solo **descrivere** l'handshake e dire *dove* va il traffico, non *cosa* contiene.
- **Domande tipiche**: descrivere la sessione TLS, elencare i siti dall'SNI. Caso reale: §9.2.

#### **8.5. ARP e ICMP (livello 2/3)**

- **ARP** (`arp`): richieste a raffica sulla sottorete = scan; due MAC per un IP = spoofing/poisoning (MITM).
- **ICMP** (`icmp`): echo verso IP consecutivi = ping sweep; `Destination Unreachable`/`Time Exceeded` = diagnostica o traceroute.

#### **8.6. Il "rumore" da riconoscere e ignorare**

Su reti Windows compaiono sempre protocolli di servizio che **non fanno parte dell'attacco** e vanno esclusi per non distrarsi: **NBNS** (NetBIOS, porta 137), **LLMNR** (5355), **mDNS** (5353), **DHCP** (67/68), **IGMP**, **WPAD** (ricerca proxy automatica), **OCSP** (controllo validità certificati, HTTP verso `ocsp.*`), **Microsoft NCSI** (`www.msftncsi.com`, `dns.msftncsi.com` — il test di connettività di Windows). Escludili con `!(nbns || llmnr || mdns || dhcp || igmp)` e riconoscili per quello che sono: **traffico di sistema legittimo**.

> ⚠️ **Distinguere il rumore dal segnale è metà dell'esame.** Nell'esame 02/07 la primissima connessione (`www.msftncsi.com`, `GET /ncsi.txt`) **non** è l'attacco: è Windows che verifica di essere online. Scambiare il rumore di sistema per l'attacco (o viceversa) è l'errore che costa più punti.

---

### **9. I tre esami risolti come casi di studio (e come riconoscerli in 30 secondi)**

Gli appelli di laboratorio finora visti sono quattro, ma **02/07/2025 e 12/09/2025 sono lo stesso identico esercizio** (file byte-identico, stesso MD5): i casi **unici** sono **tre**. Per ciascuno: come si presenta, come lo si diagnostica, la soluzione completa linkata.

#### **9.1. Caso A — Brute-force FTP fallito (appello 21/02/2025)**

- **Impronta a colpo d'occhio**: ~**19.730 pacchetti in ~45 secondi**, Protocol Hierarchy **quasi tutto FTP/porta 21**, Conversations con **una sola coppia** di host e **centinaia di connessioni** brevissime. Rapporto pacchetti/secondo altissimo → **automazione**.
- **Diagnosi**: client `10.234.125.254` (MAC Ambit) contro server FTP `10.121.70.151` (MAC Cisco, banner `220 FTP Service`); ~1.400 tentativi `USER admin`/`administrator` + `PASS <parola di dizionario>`, tutte risposte **`530`**, **mai** un `230`. Nessun `RETR`/`STOR`, `ftp-data` vuoto → **nessun file trasferito**. Brute-force a dizionario **fallito**.
- **Soluzione completa**: [Soluzione_Lab_21_02_2025.md](lab_21_febbraio_2025/Soluzione_Lab_21_02_2025.md).

#### **9.2. Caso B — Navigazione cifrata benigna (appello 13/06/2025)**

- **Impronta a colpo d'occhio**: ~**8.987 pacchetti in ~5-6 minuti**, Protocol Hierarchy dominata da **TLS/443** (~86%) con DNS e un po' di HTTP (quasi tutto **OCSP**). MAC del client e del gateway **entrambi VMware** → ambiente virtuale. Nessuna porta anomala.
- **Diagnosi**: client `192.168.120.231` (VM VMware, **Firefox**: risolve `contile.services.mozilla.com`, `content-signature-2.cdn.mozilla.net`); prima connessione TCP verso `34.117.188.166:443`; handshake TLS con **SNI leggibile**, payload cifrato; HTTP in chiaro = solo controlli **OCSP** dei certificati + una visita a `www.example.com` (`200 OK`, poi `favicon.ico` `404`). **Nessun attacco**: l'esercizio verifica la capacità di *descrivere* traffico cifrato e distinguere il rumore (OCSP, servizi del browser) dalla navigazione vera.
- **Soluzione completa**: [Soluzione_Lab_13_06_2025.md](lab_13_giugno_2025/Soluzione_Lab_13_06_2025.md).

#### **9.3. Caso C — Phishing → download di malware → C2 (appelli 02/07/2025 e 12/09/2025)**

- **Impronta a colpo d'occhio**: ~**1.790 pacchetti in ~14 minuti**, Protocol Hierarchy con **HTTP/80** dominante + una **connessione TCP su porta 2032** (non standard!) + rumore NBNS/LLMNR/DHCP. **Un solo host locale** verso più IP esterni.
- **Diagnosi**: client `10.6.27.102` (MAC **HP**, gateway **NETGEAR**), **Windows 7 x64 + IE11** (User-Agent). Sequenza: controllo **NCSI** di Windows (benigno) → DNS di **`smart-fax.com`** → download del **finto documento-fattura** `Invoice&MSO-Request.doc` (`application/msword`) → download di **`knr.exe`** (`application/x-msdownload`) → DNS di **`dunlop.hopto.org`** (DNS dinamico) → connessioni ripetute verso la **porta 2032** = **canale C2**. Il phishing è il documento civetta; il secondo download è avviato dal documento (User-Agent WinInet, non browser). **Nessun redirect** da NCSI: gli eventi sono legati dalla sola cronologia, l'innesco (link in email) è fuori cattura.
- **Soluzione completa**: [Soluzione_Lab_02_07_2025.md](lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md) (vale anche per il 12/09).

#### **9.4. Tabella di riconoscimento rapido**

| Segnale iniziale (Fase 2) | Caso probabile | Primo filtro |
|---|---|---|
| Migliaia di pacchetti in pochi secondi, tutto FTP | Brute-force FTP (Caso A) | `ftp`, poi `ftp.response.code == 230` |
| TLS/443 dominante, MAC VMware, DNS + OCSP | Navigazione benigna (Caso B) | `tls.handshake.type == 1` |
| HTTP/80 + porta non standard + un host locale | Phishing/malware/C2 (Caso C) | `http.request`, poi `tcp.port == <porta strana>` |
| ARP a raffica / due MAC per un IP | Scan o ARP poisoning (variante) | `arp` |
| Raffica di SYN da IP sorgente vari | SYN flood / DoS (variante) | `tcp.flags.syn==1 && tcp.flags.ack==0` |

---

### **10. Nella testa del docente: cosa aspettarsi e come prepararsi alle varianti**

Analizzando i tre esercizi unici emergono i **criteri di progettazione** del docente (Cimato/Anisetti). Capirli ti fa arrivare preparato anche a una traccia mai vista.

#### **10.1. I pattern ricorrenti**

- **Struttura fissa delle domande**: (1) nodi IP della prima/e connessione/i, (1a) MAC, (1b) caratteristiche HW/SW, poi domande sul *contenuto* specifico (credenziali, file, sessioni, phishing). Le prime tre domande sono **sempre le stesse** e si risolvono con Conversations + OUI + User-Agent/banner: **preparale a memoria**.
- **Tre archetipi di scenario**: (A) **attacco automatico rumoroso** su un solo servizio in chiaro (brute-force FTP); (B) **traffico benigno cifrato** da descrivere (navigazione HTTPS); (C) **catena di infezione** multi-fase in chiaro (phishing → download → C2). Coprono le tre competenze chiave: leggere un attacco evidente, descrivere traffico cifrato, ricostruire una sequenza.
- **Il "rumore" è deliberato**: NCSI, OCSP, NBNS/LLMNR sono lasciati apposta per verificare che tu sappia **distinguerli** dal traffico rilevante.
- **Riuso delle catture**: l'appello 12/09 ha riproposto **identico** il file del 02/07. Quindi è **plausibile** che una traccia futura riusi una cattura già vista, o una molto simile con piccole varianti (IP/porte/nomi diversi ma stessa logica).

#### **10.2. Riproporrà lo stesso file, uno simile, o uno nuovo?**

Tre scenari, in ordine di probabilità, e come affrontarli:

1. **Cattura identica o quasi** (già capitato tra 02/07 e 12/09). Se riconosci l'impronta (pacchetti/durata/protocollo), applichi la soluzione nota — ma **verifica sempre** i valori (IP, MAC, nomi, porte) sulla cattura davanti a te, perché il docente potrebbe cambiare i dettagli lasciando la struttura.
2. **Stesso archetipo, dati diversi**: es. un altro brute-force ma su **SSH** o **HTTP Basic** invece che FTP; un'altra catena di infezione con nomi di file e dominio C2 diversi; un'altra navigazione benigna verso siti diversi. Qui **il metodo è identico**, cambiano solo i valori da leggere: segui le fasi e le schede per protocollo.
3. **Archetipo nuovo** (mai visto in questi appelli ma nel programma M7): **ARP poisoning/MITM**, **port scanning** (Nmap: SYN scan, tanti SYN verso porte diverse dello stesso host), **SYN flooding**, **DNS tunneling**, **Telnet in chiaro** (credenziali come FTP), **SMTP** (analisi di una mail, magari con allegato malevolo estraibile via `Export Objects → IMF`). Per ognuno, l'albero decisionale della §4 e le schede della §8 ti danno filtro d'ingresso e cosa cercare.

> 💡 **Regola aurea contro l'ignoto**: qualunque sia la traccia, le Fasi 2-3 (statistiche → protocollo dominante → chi parla con chi) funzionano **sempre**. Non devi "sapere già" l'attacco: lo **scopri** restringendo l'imbuto. Se ti blocchi, torna alla Protocol Hierarchy e chiediti "qual è il protocollo dominante e cosa implica?".

#### **10.3. Schema di risposta per le domande standard**

Template mentale da riempire con i valori della tua cattura:

- **"Quali sono i nodi IP coinvolti?"** → *Conversations → IPv4*. «La connessione avviene tra il client `<IP>` (porta effimera) e il server `<IP>` sulla porta `<N>` (`<servizio>`)».
- **"Quali sono i MAC?"** → *Conversations → Ethernet* o *Ethernet II* nel dettaglio. «Client `<MAC>` (OUI → `<produttore>`), server/**gateway** `<MAC>` (OUI → `<produttore>`)». Ricorda §7.2: se il server è remoto, quel MAC è il **gateway**.
- **"Caratteristiche HW/SW?"** → OUI (hardware) + User-Agent/banner/fingerprint (software). «Hardware: NIC `<produttore>`; software: `<OS>` con `<browser/servizio>` dedotto da `<User-Agent/banner>`».
- **"Quando inizia la connessione?"** → `tcp.stream eq 0` o primo SYN; colonna *Time*. «La prima connessione si apre a `t ≈ <valore> s` con il SYN da `<IP:porta>` verso `<IP:porta>`».
- **"Descrivi la sessione."** → ricostruisci la **sequenza temporale** (DNS → connessioni → trasferimenti → esito) con `http.request || dns.flags.response == 0` letto in ordine, o Follow Stream.
- **"Quali file / credenziali?"** → `Export Objects` / `Tools → Credentials` / filtro `ftp`.
- **"Qual è l'attacco / il phishing?"** → nomina lo **schema** (brute-force, catena esca→payload→C2, MITM…) e cita gli **indicatori** concreti (nomi file, porte, domini, User-Agent) che lo provano.

---

### **11. Checklist finale d'esame (il workflow in un colpo d'occhio)**

> 📌 **Da seguire in ordine, ogni volta:**
> 1. **Scarica** la cattura dal link del docente (pcap o pcapng: indifferente) e **aprila** (`Ctrl+O` / drag & drop).
> 2. **Imposta** il tempo (`View → Time Display Format → Seconds Since Beginning`) e verifica che la risoluzione OUI dei MAC sia attiva.
> 3. **`Statistics → Capture File Properties`**: quanti pacchetti, quanta durata → umano o automatico?
> 4. **`Statistics → Protocol Hierarchy`**: protocollo dominante → che tipo di esercizio è. (Ricorda la trappola del conteggio: i numeri per porta si leggono col filtro + *Displayed*.)
> 5. **`Statistics → Conversations`**: chi parla con chi (schede IPv4 ed Ethernet) → nodi e MAC.
> 6. Scegli il **ramo** (§4) e scrivi il **primo filtro** (§5.2).
> 7. Scendi al dettaglio con **Follow Stream** (HTTP/TCP), **Export Objects**, **Credentials**.
> 8. **Deduci** HW/SW (OUI, User-Agent, banner, fingerprint) ricordando **gateway ≠ server**.
> 9. **Distingui** il rumore di sistema (NCSI, OCSP, NBNS/LLMNR) dal traffico rilevante.
> 10. **Scrivi** la risposta con lo schema §10.3: nomina lo scenario e cita gli **indicatori concreti** che lo provano.

---

### **12. Tabella di riferimento rapido dei filtri (da consultare al volo)**

| Categoria | Filtro | A cosa serve |
|---|---|---|
| **Generali** | `ip.addr == X` | tutto il traffico da/verso l'host X |
| | `ip.src == X` / `ip.dst == X` | solo in una direzione |
| | `tcp.port == N` / `udp.port == N` | traffico di una porta (leggi *Displayed* per il conteggio) |
| | `frame contains "testo"` | qualsiasi pacchetto contenente quella stringa |
| | `!(arp \|\| nbns \|\| llmnr \|\| mdns \|\| dhcp)` | nasconde il rumore di livello basso |
| **TCP** | `tcp.stream eq 0` | la prima connessione TCP |
| | `tcp.flags.syn == 1 && tcp.flags.ack == 0` | SYN iniziali (inizio conn. / SYN flood / scan) |
| | `tcp.flags.reset == 1` | RST (connessioni rifiutate → scan/porte chiuse) |
| | `tcp.analysis.retransmission` | ritrasmissioni (congestione/perdita) |
| **HTTP** | `http.request` / `http.response` | richieste / risposte |
| | `http.request.method == "POST"` | invii di dati (login, upload, OCSP) |
| | `http.host contains "..."` | richieste verso un dominio |
| | `http.user_agent contains "..."` | filtra per software client |
| **FTP** | `ftp` / `ftp-data` | canale comandi / canale dati |
| | `ftp.request.command == "USER"` / `"PASS"` | credenziali |
| | `ftp.response.code == 230` / `530` | login riuscito / fallito |
| **DNS** | `dns` / `dns.flags.response == 0` | tutte le risoluzioni / solo le query |
| | `dns.qry.name contains "..."` | risoluzioni di un dominio |
| **TLS** | `tls.handshake.type == 1` | Client Hello (leggi l'SNI) |
| | `tls.handshake.type == 2` | Server Hello (versione, cipher) |
| | `tls.handshake.type == 11` | Certificate (identità server, CA) |
| **Livello 2/3** | `arp` | scan/spoofing ARP |
| | `icmp` | ping sweep, diagnostica, traceroute |

> ✅ **In sintesi.** Non esiste una cattura "troppo grande" se applichi il metodo a imbuto: le statistiche riducono migliaia di pacchetti a poche righe di significato, i filtri isolano ciò che conta, Follow Stream e Export Objects lo rendono leggibile, e la deduzione HW/SW + il riconoscimento del rumore completano il quadro. I tre archetipi visti (brute-force, navigazione cifrata, catena di infezione) coprono le competenze fondamentali; qualunque variante il docente proponga, **le Fasi 2-3 la smascherano sempre**. Consulta questa guida durante la prova e muoviti veloce.

---

> **Riferimenti**: modulo [M7 – Wireshark e Firewall](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L1%20-%20Interfaccia,%20cattura%20e%20anatomia%20di%20una%20traccia.md) — [L2 Filtri](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L2%20-%20Filtri%20di%20cattura%20e%20di%20visualizzazione.md), [L3 Statistiche e Follow Stream](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L3%20-%20Statistiche,%20Conversations,%20Follow%20Stream%20ed%20Expert%20Info.md), [L4 Analisi per protocollo](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L4%20-%20Analisi%20per%20protocollo%20%28ARP,%20DNS,%20TCP,%20TLS,%20HTTP,%20FTP%29.md), [L5 Estrazione file e phishing](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L5%20-%20Estrazione%20di%20file,%20credenziali%20e%20individuazione%20di%20phishing.md), [L6 Cheat-sheet operativa](../../M7_Laboratorio_Wireshark_e_Firewall/UD1_Wireshark/L6%20-%20Cheat-sheet%20operativa%20d'esame.md). Casi risolti: [21/02](lab_21_febbraio_2025/Soluzione_Lab_21_02_2025.md), [13/06](lab_13_giugno_2025/Soluzione_Lab_13_06_2025.md), [02/07 ≡ 12/09](lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md).
