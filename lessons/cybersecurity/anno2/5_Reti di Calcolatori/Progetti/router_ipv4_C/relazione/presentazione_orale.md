# crouter — copione per l'esposizione orale

> **Come usare questo file.** È scritto per essere **letto o parafrasato ad alta voce**, in prima persona. Non è la relazione (quella resta il documento ufficiale con le figure): è il *copione* che guida l'esposizione, con i concetti di teoria richiamati esattamente nel punto in cui servono per capire una scelta implementativa. Le tre parti corrispondono a: (1) cornice teorica, (2) architettura e file uno per uno, (3) dimostrazione e chiusura. In fondo, l'**Appendice** con le domande plausibili e le risposte già pronte, utile sia se il professore interrompe a metà sia per una discussione a esposizione conclusa.
>
> Indicazione di tempo (orientativa, adattarla alla durata concessa): Parte 1 ≈ 4-5 min, Parte 2 ≈ 2 min, Parte 3 ≈ 8-10 min, Parte 4 ≈ 3 min. Se il tempo stringe, la Parte 1 è comprimibile: i concetti richiamati lì torneranno comunque, uno alla volta, dentro la Parte 3.

---

## Parte 1 — Apertura e cornice teorica

### 1.1. La frase di apertura

Ho realizzato **crouter**, un router IPv4 che funziona interamente in **spazio utente**: riceve i frame Ethernet grezzi dalle interfacce di rete, li analizza byte per byte, decide come instradarli e impara dinamicamente le rotte parlando **RIPv2** con altri router — nella dimostrazione, con **FRRouting**, il successore diretto di Quagga. È scritto in C, dipende solo dalla libreria standard e dagli header Linux, gira su **socket raw** invece che nel kernel o dentro Quagga, e implementa entrambi i piani di un router vero: il piano dati e il piano di controllo.

Prima di entrare nel codice, richiamo in due minuti i concetti che mi servono per motivare le scelte che ho fatto, così che quando arriverò a spiegare un file specifico non dovrò fermarmi a definire i termini.

### 1.2. I livelli che un pacchetto attraversa

Un pacchetto che passa dal mio router attraversa concettualmente tre livelli. Il **livello 2**, di collegamento, è quello di Ethernet: muove *frame* fra dispositivi adiacenti sulla stessa rete locale, identificati dal loro indirizzo **MAC**, un indirizzo hardware a 48 bit. Il **livello 3**, di rete, è IP: muove *pacchetti* fra reti diverse tramite l'instradamento, usando indirizzi IP a 32 bit che restano validi end-to-end lungo tutto il percorso. Il **livello 4**, di trasporto, è UDP o TCP: offre un canale fra applicazioni — nel mio progetto è il livello su cui viaggia RIP, sopra UDP.

Il mio router opera soprattutto al livello 3 — è lì che decide *dove* mandare un pacchetto — ma per farlo deve necessariamente "vedere" e costruire anche i frame di livello 2: ogni volta che trasmette un pacchetto IP deve incapsularlo in un frame Ethernet con i MAC giusti.

Ogni frame Ethernet porta un campo che si chiama **EtherType**, che dice cosa trasporta: il valore `0x0800` significa "dentro c'è IPv4", `0x0806` significa "dentro c'è ARP". È il primo smistamento che il mio codice fa su ogni frame ricevuto.

### 1.3. ARP: come si scopre un MAC

Per consegnare un pacchetto IP a un vicino sulla stessa rete locale non basta il suo indirizzo IP: serve il suo MAC, perché è quello che va scritto nell'header Ethernet. **ARP**, Address Resolution Protocol, risolve questo problema con un dialogo molto semplice: chi cerca un MAC manda in **broadcast** una *richiesta* — "chi ha l'IP X? dimmi il tuo MAC" — e solo il titolare di quell'indirizzo risponde con una *reply* unicast. Il mio router fa due cose con ARP: mantiene una **cache** IP→MAC per non richiedere ogni volta la stessa informazione, e **risponde** alle richieste ARP per i propri indirizzi IP — quelli configurati sulle sue interfacce.

### 1.4. IPv4: gli elementi dell'header che contano per un router

L'header IPv4 porta l'indirizzo sorgente e destinazione a 32 bit, un campo **TTL** (Time To Live) che ogni router attraversato decrementa di uno — quando arriva a zero il pacchetto va scartato, ed è la difesa strutturale contro i loop di instradamento — e un **checksum** che protegge l'integrità dell'header. Un pacchetto può anche essere frammentato se supera la dimensione massima trasmissibile su un collegamento, ma nella mia implementazione tratto la frammentazione solo per riconoscerla, senza fare io stesso frammentazione: la spiego meglio quando arrivo al file `main.c`.

### 1.5. ICMP: il protocollo dei messaggi di servizio

**ICMP** è il protocollo che porta i messaggi di controllo e diagnostica di IP. Tre tipi mi servono in particolare: l'**Echo Request/Reply** è ciò che sta dietro al comando `ping`; il **Time Exceeded** è il messaggio che un router genera quando riceve un pacchetto con TTL già a 1 (diventerebbe 0 dopo il decremento) — ed è proprio questo meccanismo che rende un router "visibile" al comando `traceroute`, che sonda la rete mandando pacchetti con TTL crescente apposta per far scattare un Time Exceeded a ogni hop; il **Destination Unreachable** segnala che una rete, un host o una porta non sono raggiungibili.

### 1.6. Piano dati e piano di controllo

Un router vero implementa due funzioni concettualmente separate. Il **piano dati** (*data plane*) è quello che sposta i singoli pacchetti verso il prossimo hop: è l'**inoltro**, il *forwarding*. Il **piano di controllo** (*control plane*) è quello che decide *quali* percorsi esistono, costruendo e mantenendo la tabella di instradamento: è il *routing*. Il mio progetto implementa entrambi, come due sotto-sistemi distinti che condividono la stessa tabella.

### 1.7. Longest Prefix Match

Quando arriva un pacchetto destinato altrove, il router deve scegliere fra tutte le rotte in tabella quella giusta. Il criterio è il **Longest Prefix Match**: fra tutte le rotte il cui prefisso "contiene" l'indirizzo di destinazione, si sceglie quella più **specifica**, cioè con il prefisso più lungo — una `/30` ha la precedenza su una `/24` se entrambe contengono la destinazione. È il criterio fondamentale con cui ogni router al mondo decide dove mandare un pacchetto.

### 1.8. RIP e RIPv2: il protocollo di routing dinamico che ho implementato

Invece di configurare le rotte a mano su ogni router, un protocollo di routing dinamico le scambia automaticamente. **RIP**, Routing Information Protocol, è un protocollo **distance-vector**: periodicamente ogni router annuncia ai vicini le reti che sa raggiungere insieme alla loro **metrica**, che per RIP è semplicemente il numero di *hop*. Chi ascolta un annuncio somma 1 alla metrica ricevuta e tiene il percorso più corto fra quelli che conosce. La metrica massima utile è **15**: il valore **16 significa infinito**, cioè "irraggiungibile" — è un limite basso apposta, perché RIP è pensato per reti piccole e questo limite mette un tetto al tempo massimo in cui un problema può propagarsi (il cosiddetto *count-to-infinity*).

Per evitare i loop di routing, RIP usa lo **split horizon con poisoned reverse**: una rotta che ho imparato da un'interfaccia la riannuncio su quella stessa interfaccia, ma con metrica infinita — è come dire al vicino da cui l'ho imparata "so raggiungere questa rete, ma non contare su di me per farlo, perché è proprio da te che l'ho imparata". **RIPv2**, quello che ho implementato, aggiunge rispetto alla versione 1 la *subnet mask* esplicita e il campo *next-hop* in ogni annuncio, il che permette di annunciare reti **classless**, cioè con maschere di lunghezza qualsiasi (CIDR/VLSM) e non solo le classi A/B/C originarie.

Nella demo, i router "di riferimento" attorno al mio eseguono **FRRouting**, la suite open source erede diretta di Quagga: il fatto che imparino le mie rotte e che io impari le loro è la prova più forte che il mio router "parla RIP vero", interoperabile con software di livello professionale, e non una versione semplificata a uso interno.

### 1.9. La scelta strategica: perché "userspace su socket raw"

La traccia ufficiale del progetto proponeva due strade: un modulo per il kernel Linux, oppure la sola configurazione di Quagga. Ho scelto una **terza via**, più istruttiva di entrambe: un router **interamente in spazio utente**, costruito su **socket raw** (`AF_PACKET`). Rispetto al modulo kernel, questa scelta evita i rischi di un kernel panic e il debugging "cieco" tipico dello spazio kernel, pur restando a basso livello — vedo e costruisco i byte del frame esattamente come farebbe un modulo kernel. Rispetto alla pura configurazione di Quagga, mi ha costretto a **scrivere davvero** il parser dei pacchetti, la tabella di forwarding e la macchina a stati del protocollo di routing, che è il cuore didattico del corso. Quagga — nella sua forma moderna FRR — resta comunque in gioco, ma come **controparte di interoperabilità**, non come motore del mio router.

Questa scelta ha una conseguenza tecnica precisa: un socket `AF_PACKET/SOCK_RAW` consegna all'applicazione il **frame Ethernet completo**, header di livello 2 incluso, e permette di trasmetterne uno costruito a mano. Di conseguenza, nel nodo che esegue crouter le interfacce di rete **non hanno un indirizzo IP a livello kernel**: gli indirizzi IP del router vivono soltanto dentro la mia configurazione applicativa. È il mio codice — non lo stack di rete del sistema operativo — a rispondere alle richieste ARP, agli echo ICMP e agli annunci RIP destinati a quegli indirizzi. Non c'è un "doppio risponditore" che confonda i test, e il parametro di sistema `ip_forward` del kernel è del tutto irrilevante: l'inoltro lo fa il mio programma.

Con questo, ho richiamato tutto ciò che serve. Passo all'architettura.

---

## Parte 2 — L'architettura in un colpo d'occhio

Il codice è organizzato in **moduli con responsabilità nette**, ciascuno in una coppia di file `.c`/`.h`. Il modo più semplice per presentarli è seguire il cammino di un pacchetto: entra da un'interfaccia, viene analizzato, e — a seconda di cosa contiene — finisce in uno di alcuni possibili percorsi.

```
                    ┌──────────────────────────────────────┐
                    │               crouter                │
  eth1 ◄──────────► │ netio ──► parse ──► forward ──► netio │
  eth2 ◄──────────► │   │                   ▲              │
  eth3 ◄──────────► │   ▼                   │              │
                    │  arp ◄── icmp        rib (LPM)       │
                    │                       ▲              │
                    │  ripd (UDP 520) ──────┘              │
                    │       ▲                              │
                    │       └── timer (timeout di select)  │
                    └──────────────────────────────────────┘
```

Nove moduli in tutto: `netio` per l'ingresso/uscita grezza sulle interfacce, `parse` per checksum e validazione, `arp` per la risoluzione degli indirizzi, `rib` per la tabella di routing con il longest prefix match, `forward` per il motore di inoltro vero e proprio, `icmp` per i messaggi di errore e di eco, `ripd` per il protocollo di routing dinamico, `config` per leggere il file di configurazione, e `main` che alloca tutto e fa girare l'unico event loop. A questi aggiungo `log`, un modulo di servizio trasversale per la diagnostica.

La pipeline che il pacchetto attraversa, in sei passi, è: demultiplexing Ethernet — validazione IPv4 — "è per me?" — decremento del TTL — lookup nella tabella — risoluzione del MAC e trasmissione. Ora la percorro file per file, spiegando per ciascuno cosa fa, con chi comunica, e perché l'ho scritto in quel modo.

---

## Parte 3 — I file, uno per uno

### 3.1. `common.h` — il vocabolario condiviso

Questo header non contiene logica: contiene le **definizioni che tutti gli altri moduli condividono**. Ci sono tre cose. Primo, le costanti globali, come la dimensione massima del buffer di un frame. Secondo, le **struct che mappano gli header dei protocolli** byte per byte — `eth_hdr`, `arp_pkt`, `ipv4_hdr`, `icmp_hdr`, `udp_hdr` — tutte marcate `__attribute__((packed))`. Questo attributo è fondamentale: dice al compilatore di non inserire byte di allineamento fra i campi, così che un puntatore a `struct ipv4_hdr` puntato direttamente dentro il buffer del frame ricevuto combaci esattamente, campo per campo, con i byte reali arrivati dalla rete — senza `packed` il compilatore potrebbe "spaziare" i campi per allinearli in memoria e la struttura non corrisponderebbe più al pacchetto vero. Terzo, la struttura di stato del router: `struct iface`, che rappresenta un'interfaccia con il suo MAC, il suo IP, la sua maschera, e se ha RIP attivo; e `struct router`, che è semplicemente l'insieme delle interfacce.

Qui vive anche una manciata di funzioni di utilità `static inline`: `prefix_to_mask`, che trasforma una lunghezza di prefisso in una maschera — con l'accortezza di trattare il prefisso `/0` come caso a parte, perché uno shift di 32 posizioni su un intero a 32 bit sarebbe comportamento indefinito in C; `mono_now`, che legge l'orologio **monotono** del sistema invece dell'orologio di calendario, perché i timer di ARP e RIP misurano intervalli e non devono essere disturbati da un aggiustamento manuale dell'ora o da NTP; `ip_str` e `mac_str`, che formattano indirizzi per i log usando **buffer statici a rotazione**, un piccolo accorgimento che serve a evitare che due chiamate nella stessa `printf` si sovrascrivano il risultato a vicenda.

### 3.2. `netio.c/h` — il livello più basso: i socket raw

È qui che vive la scelta architetturale di cui ho parlato in apertura. `netio_open` apre, per ogni interfaccia, un socket `AF_PACKET`/`SOCK_RAW`, e con due chiamate `ioctl` — `SIOCGIFINDEX` e `SIOCGIFHWADDR` — ne ricava l'indice numerico usato dal kernel e l'indirizzo MAC. Poi lo lega (`bind`) a quella singola interfaccia, altrimenti riceverebbe il traffico di tutte, e attiva la modalità **promiscua**, che serve a far arrivare anche i frame multicast — in particolare quelli di RIP, diretti al MAC multicast `01:00:5e:00:00:09` — che altrimenti la scheda di rete scarterebbe prima ancora che il socket li veda.

`netio_recv` legge in modo **non bloccante**: se non c'è nulla, torna subito con 0 invece di fermare il programma in attesa. Scarta due categorie di frame che non ci interessano: quelli che il socket "riflette" perché li abbiamo trasmessi noi stessi — altrimenti li rielaborerei come se li avessi ricevuti — e quelli destinati ad altri host che la modalità promiscua fa comunque arrivare. `netio_send` è la metà simmetrica: trasmette un frame già completo, byte per byte, così com'è.

Questo modulo non sa nulla di IP, ARP o RIP: è puro trasporto di byte. Tutti gli altri moduli lo usano per spedire, e lui restituisce a `main.c` ogni frame che riceve.

### 3.3. `parse.c/h` — la matematica del checksum e la validazione

Due responsabilità. La prima è il **checksum Internet** definito dalla RFC 1071: una somma in complemento a uno, a parole di 16 bit, su tutto il blocco, poi complementata bit a bit. La proprietà che sfrutto è duplice: per **calcolarlo** azzero il campo checksum, sommo tutto, complemento e scrivo il risultato; per **verificarlo**, sommo tutto — campo checksum incluso — e il risultato deve venire zero. `ipv4_valid` usa proprio questa proprietà per controllare che un header IPv4 sia integro, insieme ai controlli su versione, lunghezza dell'header (IHL) e coerenza fra lunghezza dichiarata e byte davvero ricevuti; un pacchetto che fallisce anche un solo controllo viene scartato **silenziosamente**, senza generare alcun ICMP — perché non è previsto rispondere a spazzatura di rete, e perché rispondere aprirebbe un vettore di amplificazione.

La seconda responsabilità è il pezzo di codice che ritengo più elegante di tutto il progetto: `ipv4_ttl_dec`, il **checksum incrementale** secondo la RFC 1624. Ogni volta che decremento il TTL, l'header cambia e il suo checksum andrebbe aggiornato. Ricalcolarlo da zero su tutto l'header sarebbe corretto ma inutilmente costoso; la RFC 1624 mostra che, sapendo che è cambiata una sola parola di 16 bit (quella che contiene TTL e protocollo, che condividono la stessa metà a 16 bit dell'header), il nuovo checksum si ottiene con **poche operazioni aritmetiche** a partire dal vecchio valore, senza toccare il resto dell'header. La formula è $HC' = \overline{\overline{HC} + \overline{m} + m'}$, dove $HC$ è il vecchio checksum, $m$ la parola prima della modifica e $m'$ la stessa parola dopo. L'ho verificato end-to-end: i pacchetti che attraversano il mio router arrivano a destinazione, e `tcpdump` sull'host finale non segnala mai un checksum IP corrotto.

### 3.4. `arp.c/h` — cache, risposte, e la coda di attesa

Questo modulo tiene due tabelle a **dimensione fissa** — niente allocazione dinamica, per costruzione niente memory leak: una **cache** IP→MAC con scadenza a 300 secondi e rimpiazzo delle entry più vecchie quando è piena (una politica LRU semplice), e una **coda di pacchetti pendenti**, cioè pacchetti che aspettano che si risolva il MAC del loro next-hop.

`arp_input` gestisce ogni frame ARP ricevuto: se è una richiesta per un nostro indirizzo, costruisco la risposta **riscrivendo il frame ricevuto sul posto** — scambio i MAC nell'header Ethernet, cambio l'operazione da request a reply, scambio mittente e destinatario nel corpo ARP — invece di allocarne uno nuovo da zero. Da *ogni* pacchetto ARP che vedo, richiesta o risposta, imparo comunque l'associazione IP→MAC del mittente: è "ARP gratuito", e mi risparmia future richieste esplicite. `arp_output` è la funzione che il motore di forwarding chiama per spedire un pacchetto verso un next-hop: se il MAC è già in cache, spedisce subito; altrimenti **accoda** il pacchetto — ne tiene una copia intera — e lancia una richiesta ARP, che `arp_tick` ritrasmette periodicamente fino a un massimo di tre tentativi, oltre i quali il pacchetto viene scartato.

`arp` dialoga con `netio` per spedire e ricevere, ed è chiamato sia da `main.c` — quando un frame in ingresso è di tipo ARP — sia da `forward.c`, che gli passa ogni pacchetto IP da inoltrare una volta trovato il next-hop nella RIB.

### 3.5. `rib.c/h` — la tabella di routing e il longest prefix match

`rib` è il cuore dell'instradamento. La tabella è un semplice **array lineare** di `struct rib_entry`, ciascuna con prefisso, lunghezza del prefisso, next-hop, interfaccia di uscita, metrica e **origine** — connessa, statica, o appresa via RIP. `rib_lookup` scandisce l'intero array e applica, in ordine, tre criteri di preferenza: primo, il prefisso più lungo che contiene la destinazione — il longest prefix match propriamente detto; a parità di lunghezza, la **distanza amministrativa** minore, cioè quanto mi fido dell'origine della rotta — connessa batte statica batte RIP, esattamente come sui router Cisco; a ultima istanza, la metrica minore.

Ho scelto deliberatamente un array scandito linearmente invece di una struttura più sofisticata come un **trie binario**, che pure era menzionato nella traccia come struttura efficiente per l'LPM. Con le poche decine di rotte della mia topologia, un array è più che sufficiente in termini di prestazioni ed è molto più leggibile e verificabile; un trie resterebbe la naturale evoluzione se il numero di rotte crescesse di ordini di grandezza, ma qui sarebbe complessità aggiunta senza un beneficio misurabile.

`rib_set` inserisce o aggiorna una rotta, e segna un flag `changed` che il modulo RIP userà per decidere cosa annunciare nel prossimo *triggered update*; `rib_delete` libera lo slot. `rib_dump`, infine, stampa la tabella a video: è quella che si vede quando mando il segnale `SIGUSR1` al processo.

### 3.6. `forward.c/h` — il motore di inoltro

Questo file mette in sequenza tre moduli già visti: `parse`, `rib` e `arp`. `forward_ip` riceve un pacchetto IP **già validato** e destinato altrove, ed esegue tre passi. Primo: se il TTL è già a 1 (diventerebbe 0), non lo inoltro — genero invece un ICMP Time Exceeded verso il mittente tramite il modulo `icmp`, ed è esattamente questo il meccanismo che rende il mio router visibile a `traceroute`. Secondo: cerco la rotta nella RIB con `rib_lookup`; se non c'è nessuna rotta, genero un Destination Unreachable di tipo "rete non raggiungibile". Terzo, se la rotta c'è: decremento il TTL con l'aggiornamento incrementale del checksum di cui ho parlato, riscrivo il MAC sorgente del frame con quello dell'interfaccia di uscita, e passo il pacchetto ad `arp_output` per la risoluzione del MAC di destinazione e la trasmissione.

Un dettaglio a cui tengo: il next-hop è il gateway della rotta se la rotta ne ha uno, altrimenti — cioè se la rotta è una rete **direttamente connessa** — il next-hop è la destinazione stessa, perché si trova sulla stessa rete locale del router. È lo stesso identico modello concettuale di una tabella di routing vera.

### 3.7. `icmp.c/h` — Echo Reply e messaggi di errore

Due funzioni. `icmp_input` gestisce un Echo Request destinato a un nostro IP: **riscrive il pacchetto ricevuto sul posto** — scambia i MAC, scambia gli indirizzi IP sorgente/destinazione, assegna un TTL fresco, cambia il tipo da Echo Request a Echo Reply, e ricalcola i due checksum (IP e ICMP) da zero, perché qui il pacchetto è cambiato per intero e non ha senso l'aggiornamento incrementale. Il payload dell'eco — i dati che `ping` include per misurare il tempo di andata e ritorno — resta invariato, com'è corretto che sia.

`icmp_send_error` è la funzione che genera Time Exceeded e Destination Unreachable durante l'inoltro. Prima di generare un errore, applico i controlli che la RFC 1122 impone per evitare tempeste di messaggi: non rispondo a un frammento che non sia il primo, non rispondo se la sorgente del pacchetto originale non è un indirizzo unicast valido — zero, broadcast, o multicast — e non rispondo a un pacchetto che è già un messaggio ICMP di errore, per non innescare una catena di errori che generano altri errori all'infinito. Il messaggio generato **cita** il pacchetto che ha causato il problema: include il suo header IP e i primi 8 byte di payload, che per TCP/UDP contengono le porte, così chi riceve l'errore può capire a quale connessione si riferisce.

### 3.8. `ripd.c/h` — il piano di controllo: RIPv2

È il file più grande e concettualmente più ricco: implementa il protocollo RIPv2 punto per punto, senza usare un socket UDP del kernel — anche i pacchetti RIP li costruisco a mano, Ethernet più IP più UDP più il messaggio RIP, coerentemente con la scelta di fare tutto in spazio utente.

`ripd_init` invia, all'avvio, una **Request** su ogni interfaccia con RIP attivo — per convergere subito senza aspettare il primo annuncio periodico dei vicini — e programma il primo annuncio periodico fra 3 e 5 secondi. `send_update` costruisce e trasmette un annuncio (Response): può contenere o l'intera tabella (annuncio periodico, o risposta a una Request) o solo le rotte cambiate (*triggered update*). È proprio qui che vive lo **split horizon con poisoned reverse**: se una rotta esce dalla stessa interfaccia da cui l'ho imparata, la annuncio su quell'interfaccia con metrica 16 invece di ometterla — bastano due righe di codice, ma è la differenza fra un protocollo che può fare loop e uno che non li fa.

`process_entry` è il cuore dell'algoritmo distance-vector: per ogni rotta annunciata da un vicino, calcola la metrica "vista da me" — quella ricevuta più uno — e decide se adottarla. Ci sono tre casi principali. Se la rotta è nuova, la inserisco se è raggiungibile. Se la conosco già dallo **stesso** gateway che sta annunciando ora, aggiorno comunque, anche se la metrica peggiora — è la mia unica fonte per quella rotta, e se quel gateway mi dice che la metrica è 16, cioè irraggiungibile, faccio partire il **processo di cancellazione**: metrica a 16, si avvia un timer di *garbage collection*, e annuncio subito la variazione. Se invece la rotta arriva da un gateway **diverso** da quello che uso oggi, la adotto solo se la metrica è migliore — o se è uguale ma la mia rotta attuale sta per scadere, un'euristica della RFC 2453 per non restare appesi a un gateway che sta per sparire.

`ripd_tick`, chiamato a ogni giro dell'event loop, gestisce i due timer per rotta previsti dalla RFC: **timeout a 180 secondi** — se non arriva nessun aggiornamento per quel tempo, la rotta è sospetta e viene invalidata a metrica 16 — e **garbage collection a 120 secondi** dopo l'invalidazione, trascorsi i quali la rotta viene rimossa del tutto. Nello stesso punto gestisco anche l'invio dei *triggered update* pendenti e degli annunci periodici, questi ultimi con un **jitter casuale** di qualche secondo attorno ai 30, per evitare che più router finiscano per annunciare tutti insieme e congestionare la rete a raffiche — un dettaglio piccolo ma imposto dalla RFC 2453, e necessario per l'interoperabilità con FRR.

`ripd_input` valida rigorosamente ogni datagramma ricevuto — versione RIP, porta sorgente 520, mittente sulla rete direttamente connessa all'interfaccia, non un mio stesso annuncio riflesso — prima di passare le singole entry a `process_entry`. Questo file dialoga con `rib` per leggere e scrivere la tabella, con `arp` per imparare "gratis" il MAC dei vicini dai loro stessi annunci, e con `netio` per spedire.

### 3.9. `config.c/h` — il file di configurazione

Un parser volutamente semplice, riga per riga, di un file di testo con due direttive: `interface <nome> <ip>/<prefisso> [rip]` per dichiarare un'interfaccia e opzionalmente attivarci RIP, e `static <rete>/<prefisso> via <gateway>` per una rotta statica. Ogni riga che non rispetta esattamente la sintassi attesa produce un errore con numero di riga, e il caricamento si interrompe: preferisco fallire subito e in modo chiaro a un avvio con una configurazione parzialmente sbagliata.

### 3.10. `main.c` — l'inizializzazione e l'event loop

`main.c` fa quattro cose in sequenza. Primo, carica la configurazione e apre un socket per ogni interfaccia. Secondo, popola la RIB: una rotta **connessa** per ogni interfaccia — a distanza zero, perché è direttamente raggiungibile — e le eventuali rotte **statiche**, verificando per ciascuna che il suo gateway sia effettivamente su una rete connessa, altrimenti non ci sarebbe modo di raggiungerlo. Terzo, avvia RIP. Quarto, entra nell'**event loop**.

L'event loop è basato su una singola chiamata `select()` che attende contemporaneamente su tutti i socket delle interfacce, con un **timeout di 500 millisecondi**. Quel timeout non è tempo perso: è il "battito" che fa avanzare i timer di ARP e di RIP anche quando non arriva traffico. Tutto il router — ricezione su più interfacce, timer ARP, timer RIP — gira in un **singolo processo a thread singolo**: non c'è alcun rischio di race condition, non servono lock, non serve sincronizzazione. È una scelta di semplicità che pago in scalabilità — con migliaia di interfacce servirebbe altro — ma che è pienamente adeguata a un router didattico con poche interfacce e traffico moderato.

Dentro l'event loop vive anche `handle_frame`, la funzione che implementa la pipeline dei sei passi di cui ho parlato in apertura: guarda l'EtherType e smista fra ARP e IPv4; valida l'header IPv4; controlla se il pacchetto è destinato a me — un mio IP, il multicast RIP, o un broadcast — nel qual caso lo consegno localmente a ICMP o a RIP, oppure genero un Port Unreachable se è UDP verso una porta che non sto ascoltando; altrimenti lo passa a `forward_ip`. Un segnale `SIGUSR1` fa stampare la RIB corrente a video in qualunque momento — comodissimo durante i test — mentre `SIGINT`/`SIGTERM` fanno uscire in modo pulito dal loop, chiudendo tutti i socket.

### 3.11. `log.c/h` — la diagnostica

Un modulo di servizio trasversale, usato da tutti gli altri: quattro livelli di gravità (errore, avviso, informazione, debug), un timestamp con i millisecondi, e colori ANSI se l'output va a un vero terminale — disattivati automaticamente se l'output è rediretto su file, dove sarebbero solo caratteri sporchi. Con `-v` sulla riga di comando alzo la soglia a debug e vedo ogni singolo pacchetto che il router tratta; di default resto a informazione, per non affogare i log importanti — apprendimento di rotte, timeout, triggered update — nel rumore di ogni singolo pacchetto.

Con questo ho coperto tutti i file. La cosa che vorrei sottolineare, tirando le fila, è che ogni modulo ha **una sola responsabilità** e comunica con gli altri attraverso un'interfaccia minima — è la stessa disciplina di progettazione modulare vista a lezione applicata a un sistema reale, non a un esercizio.

---

## Parte 4 — La dimostrazione e la chiusura

Ho verificato il progetto con cinque scenari, tutti riprodotti in modo scriptato su **network namespace Linux** — la stessa tecnologia su cui si fondano i container Docker — con FRR in esecuzione sui router vicini; la stessa identica topologia gira anche su **IMUNES**, senza toccare una riga di codice, ed è documentata in appendice alla relazione.

Primo, la **convergenza a freddo**: all'avvio invio le Request, e in circa 14 secondi la tabella è popolata con tutte le rotte remote, ciascuna con la metrica corretta. Secondo, **ping e traceroute**: il ping arriva con TTL 61 — partito a 64, decrementato tre volte, una per router attraversato — prova diretta che decremento davvero il TTL; il traceroute mostra il mio router come primo hop, perché genera correttamente il Time Exceeded. Terzo, l'**interoperabilità con FRR**: catturo con `tcpdump` un annuncio che il mio router invia, e lascio che sia il decoder di tcpdump — non il mio codice — a interpretarlo: riconosce un RIPv2 Response ben formato, e vedo a occhio nudo il poisoned reverse nelle tre rotte annunciate a metrica 16. Dall'altra parte, FRR accetta i miei annunci e mostra la mia rete come appresa da me. Quarto, **guasto e riconvergenza**: abbatto un collegamento centrale mentre un ping continua a scorrere, e osservo nel log la sequenza esatta prevista dalla teoria — l'annuncio avvelenato, il triggered update, la riconvergenza sul cammino alternativo — con solo una manciata di pacchetti persi durante la finestra di transizione. Quinto, il **traffico applicativo**: una sessione HTTP completa, con il suo handshake TCP a tre vie, attraversa il router senza problemi — prova che l'inoltro funziona anche per un protocollo con stato, non solo per ICMP.

Ho sottoposto il router anche a due prove di robustezza. Un **fuzzer** ha iniettato 2200 frame malformati — byte casuali, header corrotti, TTL nullo, pacchetti troncati — e il router li ha scartati tutti senza mai andare in crash, continuando a instradare il traffico legittimo. **Valgrind**, eseguito durante una sessione con ping, traceroute, HTTP e fuzzing insieme, riporta zero errori di memoria e zero leak — anche perché ho scelto deliberatamente buffer e strutture a dimensione fissa, senza allocazione dinamica, il che elimina alla radice un'intera classe di bug.

Per concludere: in circa 1500 righe di C senza dipendenze esterne, crouter copre l'intero ciclo di vita di un pacchetto in un router IPv4, dalla ricezione del frame grezzo fino all'apprendimento dinamico delle rotte. Ho lasciato fuori deliberatamente, per contenere il perimetro del progetto, tre estensioni che ho comunque analizzato: l'autenticazione RIPv2 con password in chiaro della RFC 2453, il trie binario per l'LPM come evoluzione se le rotte crescessero di numero, e le rotte di default con la ridistribuzione fra protocolli. Resto a disposizione per qualunque domanda.

---

## Appendice — Domande plausibili e risposte pronte

> Organizzate per area tematica. Ogni risposta è pensata per stare in **30-60 secondi** di parlato; se il professore vuole andare più a fondo, il dettaglio aggiuntivo è nella frase successiva o rimanda al file del codice.

### A. Scelte architetturali generali

**D. Perché non un modulo kernel?**
Un modulo kernel avrebbe richiesto scrivere codice che gira con i privilegi e i vincoli dello spazio kernel — nessuna libreria standard, nessun crash recuperabile, un bug può bloccare l'intera macchina — a fronte di un beneficio didattico minore, perché la logica di forwarding e di RIP sarebbe la stessa. I socket raw mi danno lo stesso accesso a basso livello ai frame, ma in un ambiente in cui un bug produce al più un crash del mio processo, non del sistema, e in cui posso usare `gdb` e Valgrind normalmente.

**D. Perché non hai semplicemente configurato Quagga/FRR?**
Perché non avrei scritto una riga di parser, di tabella di forwarding o di macchina a stati RIP: avrei dimostrato di saper leggere una documentazione, non di aver capito come funziona un router. Ho tenuto Quagga/FRR in gioco, ma dall'altra parte, come controparte di interoperabilità: è quello che mi permette di dire che il mio RIP è "vero" e non una semplificazione.

**D. Perché un solo processo, un solo thread?**
Perché tutto lo stato del router — RIB, cache ARP, coda di pending — è condiviso fra la ricezione dei pacchetti e i timer, e con un solo thread non serve alcuna sincronizzazione: niente mutex, niente race condition per costruzione. `select()` mi permette di aspettare su più socket contemporaneamente restando comunque a singolo thread. Con migliaia di interfacce o throughput molto alto servirebbe altro — più processi, più code — ma per un router didattico con poche interfacce è la scelta più semplice che risolve correttamente il problema.

**D. Perché `select()` e non `epoll` o i thread?**
`epoll` scala meglio con moltissimi file descriptor, ma con al più otto interfacce (`MAX_IFACES`) la differenza di prestazioni è nulla; `select()` è più semplice, più portabile, ed è lo stesso strumento visto a lezione nel modulo sulla programmazione con i socket. Il timeout di `select` mi serve comunque come "battito" per i timer, quindi lo avrei dovuto gestire in ogni caso.

**D. Perché niente `malloc`, solo buffer e array statici a dimensione fissa?**
Perché elimina alla radice un'intera classe di bug — leak, doppio free, uso dopo free — che Valgrind infatti non trova, perché non possono esistere. Il costo è un limite superiore fisso a interfacce, rotte, entry di cache ARP; per un router didattico questi limiti (128 rotte, 64 entry ARP, 8 interfacce) sono ampiamente sufficienti, e se servisse scalarli basterebbe alzare una costante, ricompilare.

**D. Il codice compila su Windows?**
No, ed è previsto: usa `AF_PACKET`, `linux/if_packet.h` e le `ioctl` specifiche di Linux per accedere ai frame grezzi. Su Windows l'equivalente sarebbe Npcap/WinPcap con un'API diversa. Compila ed esegue senza warning su Linux con `gcc -Wall -Wextra`; l'ho sviluppato e testato su una VM Ubuntu.

### B. Il piano dati

**D. Cosa succede se ricevo un pacchetto con TTL a 1?**
Non lo inoltro: se lo facessi il TTL diventerebbe 0 dal lato ricevente, che a sua volta lo scarterebbe senza dire nulla a nessuno. Genero invece un ICMP Time Exceeded verso il mittente originale, prima che il pacchetto venga scartato — è la regola generale di IP, ed è proprio il meccanismo su cui si basa `traceroute`.

**D. E se il TTL fosse già 0 all'arrivo?**
Non dovrebbe mai succedere da un mittente corretto, ma nel mio controllo tratto `ttl <= 1` come unico caso, quindi anche un TTL 0 in ingresso genera comunque un Time Exceeded invece di un underflow silenzioso: è una scelta difensiva.

**D. Perché il checksum incrementale e non un ricalcolo completo?**
Perché il ricalcolo completo dell'header a ogni hop, su ogni pacchetto, è lavoro ripetuto inutilmente: la RFC 1624 dimostra che, cambiando una singola parola di 16 bit nota, il nuovo checksum si ottiene con due addizioni a partire dal vecchio valore. È un'ottimizzazione classica dei router reali, non solo un mio vezzo.

**D. Come verifichi che il checksum incrementale sia corretto?**
L'ho verificato end-to-end: se fosse sbagliato, l'host di destinazione scarterebbe silenziosamente i pacchetti come corrotti, e infatti durante i test ho controllato con `tcpdump` che non comparisse mai `bad ip cksum`. È anche verificabile algebricamente: la formula RFC 1624 è una proprietà del complemento a uno, non un'euristica.

**D. Che cos'è il longest prefix match e perché serve?**
È il criterio con cui un router sceglie, fra tutte le rotte che "contengono" l'indirizzo di destinazione — cioè per cui l'indirizzo, mascherato con la netmask della rotta, coincide col prefisso della rotta — quella più specifica, col prefisso più lungo. Serve perché una tabella di routing normalmente ha sia rotte generali (una `/24` o una rotta di default) sia rotte più specifiche per sottoreti particolari, e bisogna sempre preferire la più specifica.

**D. Perché un array lineare per la RIB e non un trie binario?**
Perché con le decine di rotte della mia topologia un array scandito linearmente è già rapidissimo in pratica, ed è molto più semplice da scrivere correttamente e da verificare. Un trie binario (o PATRICIA) diventa necessario quando le rotte sono migliaia o milioni, come su un router di dorsale Internet: lì la complessità del trie si ripaga. L'ho lasciato come estensione dichiarata, non implementata, per non aggiungere complessità senza un beneficio misurabile nel mio contesto.

**D. Cos'è la distanza amministrativa e perché serve oltre alla metrica?**
È un ordine di fiducia fra origini diverse della stessa rotta: una rotta connessa è per definizione più affidabile di una statica, che è più affidabile di una imparata dinamicamente via RIP, perché un annuncio RIP può sbagliare o essere in transizione mentre una connessione fisica no. A parità di lunghezza di prefisso, decido prima in base a questo, e solo poi in base alla metrica — è esattamente il modello usato dai router Cisco.

**D. Che differenza c'è fra una rotta connessa e una rotta appresa via RIP nella tua RIB?**
Una rotta connessa ha `next_hop` uguale a zero, perché la destinazione è sulla stessa rete locale del router — il "next-hop" è la destinazione stessa, risolta via ARP direttamente. Una rotta RIP ha un `next_hop` reale, l'indirizzo del router vicino da cui bisogna passare, e in più porta i due campi di stato `last_update` e `garbage_at` che servono ai timer di scadenza — cose che una rotta connessa non ha bisogno di avere perché non scade mai.

**D. Come gestisci un pacchetto con opzioni IP o un header più lungo di 20 byte?**
`ipv4_valid` calcola l'IHL a partire dal nibble basso di `ver_ihl` e verifica solo che sia `>= 20` byte e dentro i byte disponibili: un header con opzioni viene validato correttamente. Il mio codice del piano dati calcola sempre l'offset dei livelli superiori a partire da questo IHL, non da una costante 20, quindi non si rompe con le opzioni; semplicemente non ho bisogno di *interpretarle*, perché nessuna delle opzioni IP standard cambia le mie decisioni di forwarding.

**D. Gestisci la frammentazione IP?**
La riconosco — nel demux iniziale distinguo il primo frammento (quello con gli header di trasporto) dagli altri tramite il campo *fragment offset* — ma non la genero mai io stesso, perché tutta la mia topologia di test usa un MTU di 1500 byte su cui i pacchetti generati dagli host e da RIP entrano senza frammentazione. Riassemblare i frammenti in ingresso non è necessario per il forwarding — un router inoltra ogni frammento indipendentemente, non li riassembla mai, quello lo fa solo l'host finale.

### C. RIP e RIPv2

**D. Spiega lo split horizon con poisoned reverse, con un esempio concreto della tua demo.**
Il mio router impara la rete `10.0.4.0/24` dal vicino R2, sull'interfaccia `eth2`. Quando è il momento di annunciare la mia tabella proprio su `eth2` — cioè verso R2 — non ometto quella rotta, la annuncio comunque, ma con metrica 16 invece della metrica reale: sto dicendo a R2 "conosco questa rete, ma non contare su di me per raggiungerla, perché l'ho imparata proprio da te". Se non lo facessi, e R2 perdesse la sua rotta diretta, potrebbe finire per imparare da me una rotta che in realtà passa di nuovo per lui: un loop. L'ho verificato letteralmente guardando la cattura tcpdump: le tre reti che ho imparato da R2 compaiono, nel mio annuncio verso R2, tutte a metrica 16.

**D. Perché la metrica massima di RIP è 16 e non un numero più alto?**
Perché RIP non ha altro meccanismo per fermare un loop temporaneo se non il fatto che la metrica cresce di uno a ogni giro e si ferma a un tetto: più basso è il tetto, meno tempo un problema di loop può durare prima che tutti concordino che la rete è irraggiungibile. È un compromesso esplicito della progettazione originale di RIP fra semplicità e dimensione massima della rete che il protocollo può gestire — non a caso RIP è pensato per reti piccole, non per l'instradamento globale di Internet.

**D. Cos'è il count-to-infinity e come lo mitighi?**
È il fenomeno per cui, in assenza di contromisure, due o più router possono incrementarsi a vicenda la metrica di una rotta ormai persa, un passo alla volta, fino a raggiungere l'infinito molto lentamente — nel frattempo, instradano traffico verso un buco nero. Lo split horizon con poisoned reverse elimina il caso più comune, quello a due router; il tetto a 16 limita comunque la durata massima del fenomeno anche nei casi più complessi con più router in un ciclo.

**D. Differenza fra RIPv1 e RIPv2?**
RIPv1 non porta la subnet mask nei suoi annunci: assume le classi A/B/C storiche, quindi non può gestire reti VLSM/CIDR di lunghezza arbitraria. RIPv2 aggiunge il campo subnet mask esplicito e il campo next-hop in ogni entry, oltre all'autenticazione opzionale, mantenendo lo stesso formato di messaggio base — è per questo che ho potuto scegliere RIPv2 senza dover reinventare l'architettura del protocollo, solo arricchire il formato dell'entry.

**D. Perché multicast (`224.0.0.9`) e non broadcast per gli annunci?**
Perché il multicast raggiunge solo chi è interessato ad ascoltarlo — in teoria, solo gli altri router RIP — mentre il broadcast arriverebbe a ogni host della rete locale, sprecando risorse su macchine a cui RIP non interessa affatto. È anche il comportamento standard di RIPv2 secondo la RFC 2453, necessario per l'interoperabilità con FRR.

**D. Perché il TTL degli annunci RIP multicast è 1?**
Perché RIP è un protocollo fra vicini diretti: un annuncio non deve mai superare un router, altrimenti si romperebbe il modello a hop singolo su cui si basa tutto il distance-vector. TTL 1 garantisce che l'annuncio muoia se qualcosa tentasse comunque di inoltrarlo oltre la LAN locale.

**D. Cosa succede se ricevi due annunci della stessa rete da due vicini diversi con la stessa metrica?**
Mantengo la rotta che ho già, senza cambiare gateway: cambiare senza un vantaggio di metrica produrrebbe solo instabilità (il cosiddetto *route flapping*) senza alcun beneficio. Faccio eccezione solo se la rotta attuale è "vecchia" — più di metà del timeout senza rinfresco — nel qual caso accetto comunque il nuovo gateway, un'euristica della RFC 2453 pensata per non restare appesi a una fonte che sta per scadere.

**D. Come decidi il next-hop quando ricevi un annuncio RIP?**
Normalmente è l'indirizzo IP sorgente del pacchetto che porta l'annuncio — il vicino che mi sta parlando. RIPv2 permette anche di dichiarare un next-hop diverso nel campo apposito dell'entry, per i casi in cui il vicino annuncia una rotta per conto di un altro router sulla stessa rete locale; io lo accetto solo se quell'indirizzo è realmente sulla rete connessa all'interfaccia e non è un mio stesso indirizzo, altrimenti ricado sul mittente diretto — è una validazione difensiva contro annunci malformati o ambigui.

**D. Hai implementato l'autenticazione RIPv2?**
No, l'ho lasciata deliberatamente fuori dal perimetro: la RFC 2453 la prevede come un'entry speciale di tipo `0xFFFF` con una password in chiaro, che rinforzerebbe il control plane contro annunci ostili di terzi ma non contro un attaccante che intercetta il traffico, visto che la password non è cifrata. L'ho documentata come estensione naturale ma non l'ho implementata per contenere lo scopo del progetto.

**D. Cosa succederebbe se un host malevolo iniettasse annunci RIP falsi nella mia rete?**
Senza autenticazione, il mio router li accetterebbe se rispettano i controlli di validità che ho implementato — mittente sulla rete connessa, formato corretto, metrica nel range — esattamente come farebbe un router RIP qualunque senza autenticazione attivata. È un limite reale e riconosciuto del protocollo RIP di base, non solo della mia implementazione: è il motivo per cui la RFC prevede l'autenticazione come opzione, ed è il motivo per cui protocolli più moderni (OSPF, BGP) hanno meccanismi di sicurezza più solidi.

### D. Networking di base e header

**D. Perché serve la conversione network/host byte order (`htons`/`ntohl`...)?**
Perché i protocolli di rete trasmettono i campi multi-byte in ordine **big-endian** per convenzione, mentre le CPU x86/ARM su cui giro sono **little-endian**: se leggessi o scrivessi un campo a 16 o 32 bit senza convertirlo, i byte risulterebbero scambiati e ogni confronto o calcolo su quel campo sarebbe sbagliato. Ogni struttura header nel mio `common.h` ha un commento che ricorda quali campi sono "network order" per non dimenticarlo mai durante lo sviluppo.

**D. Cos'è un socket `AF_PACKET`/`SOCK_RAW` e in cosa differisce da un socket TCP/UDP normale?**
Un socket TCP o UDP normale lavora a livello di trasporto: il kernel si occupa lui degli header di rete e di collegamento, e l'applicazione vede solo il payload. Un socket `AF_PACKET`/`SOCK_RAW` invece consegna il **frame Ethernet completo**, header di livello 2 e 3 inclusi, e permette di costruirne e trasmetterne uno scritto a mano byte per byte: è ciò che rende possibile scrivere un router in spazio utente invece che affidarsi allo stack IP del kernel.

**D. Come ricavi MAC e indice di un'interfaccia dal solo nome ("eth1")?**
Con due chiamate `ioctl` sul socket: `SIOCGIFINDEX` restituisce l'indice numerico che il kernel usa internamente per identificare l'interfaccia, e `SIOCGIFHWADDR` restituisce il suo indirizzo MAC. Entrambe passano attraverso una `struct ifreq` in cui scrivo il nome e da cui rileggo il campo riempito dal kernel.

### E. Robustezza, qualità del codice e limiti

**D. Cosa hai imparato dal fuzzing?**
Che la validazione difensiva scritta in `ipv4_valid` e nei controlli di lunghezza sparsi in ogni parser fa davvero il suo lavoro: su 2200 frame malformati — byte casuali, header con versione o IHL sbagliati, checksum corrotti, pacchetti troncati — nessuno ha causato un crash, e il router ha continuato a instradare correttamente il traffico legittimo durante e dopo il fuzzing.

**D. Cosa controlla esattamente Valgrind, e cosa significa "zero errori"?**
Valgrind, in modalità `--leak-check=full`, intercetta ogni allocazione e accesso a memoria e segnala letture/scritture fuori dai limiti di un blocco, uso di memoria non inizializzata, e blocchi allocati mai liberati (leak). "Zero errori da zero contesti" significa che durante l'intera sessione — ping, traceroute, HTTP, fuzzing — non si è mai verificato nessuno di questi problemi. Aiuta molto, in questo, la scelta di non usare mai `malloc`: gran parte delle categorie di errore che Valgrind cerca semplicemente non possono presentarsi con buffer statici.

**D. Quali sono i limiti dimensionali del tuo router (quante rotte, quante interfacce...)?**
Fino a 8 interfacce (`MAX_IFACES`), 128 rotte in RIB (`RIB_MAX`), 64 entry nella cache ARP, 16 pacchetti in coda di risoluzione, 25 entry per datagramma RIP come da RFC 2453, e 32 rotte statiche configurabili. Sono tutte costanti definite negli header: alzarle significa cambiare un numero e ricompilare, non riscrivere logica.

**D. Se dovessi estendere il progetto, cosa faresti per primo?**
Nell'ordine: l'autenticazione RIPv2, perché è la lacuna di sicurezza più concreta e la più semplice da aggiungere senza toccare l'architettura; poi rotte di default e ridistribuzione fra protocolli, per avvicinarsi a un router realmente utilizzabile in una rete più grande; il trie binario per l'LPM lo farei solo se il numero di rotte lo giustificasse davvero, altrimenti sarebbe complessità aggiunta senza beneficio.

**D. Perché hai usato i network namespace invece di IMUNES per i test principali?**
Perché sono completamente **scriptabili e riproducibili da riga di comando**: uno script ricrea l'intera topologia, avvia FRR sui vicini, ed esegue tutti e cinque gli scenari in sequenza salvando ogni cattura, senza intervento manuale — il che mi ha permesso di rieseguire i test tutte le volte che serviva durante lo sviluppo. È la stessa tecnologia di virtualizzazione di rete su cui si fondano i container Docker, quindi un ambiente realistico, non una scorciatoia. Ho comunque portato l'identica topologia su IMUNES per fedeltà alla piattaforma del corso, ed è documentato in appendice alla relazione con crouter in esecuzione, identico, in un nodo IMUNES.

**D. Il router supporta IPv6?**
No: il progetto è specificamente IPv4, come da titolo e come da traccia. Estenderlo a IPv6 richiederebbe un header diverso (niente checksum IP, un campo "Next Header" al posto di "Protocol", indirizzi a 128 bit) e un protocollo di routing dinamico diverso (RIPng, non RIP), quindi non una semplice modifica ma un lavoro sostanzialmente parallelo.

### F. Domande "trabocchetto" o di confine

**D. Cosa distingue un router da uno switch, in termini di quello che fa il tuo codice?**
Uno switch opera a livello 2, inoltra frame Ethernet dentro la stessa rete locale guardando solo i MAC, senza mai guardare l'IP. Il mio router opera a livello 3: decide l'instradamento guardando l'indirizzo IP di destinazione e la tabella di routing, decrementa il TTL, e attraversa reti diverse — è un concetto strutturalmente diverso, anche se entrambi "inoltrano" pacchetti.

**D. Che differenza c'è fra forwarding e routing, con riferimento al tuo codice?**
Il forwarding è l'azione meccanica e veloce di spedire un singolo pacchetto verso il next-hop corretto, una volta che la tabella esiste già: è quello che fa `forward.c`, guardando la RIB. Il routing è il processo, più lento e continuo, di costruire e mantenere quella tabella: è quello che fa `ripd.c`, scambiando annunci con i vicini. Nel mio progetto sono due file distinti proprio per rendere esplicita questa distinzione concettuale.

**D. Perché RIP e non OSPF o BGP?**
La traccia del progetto chiedeva un protocollo di routing dinamico interoperabile con Quagga/FRR; RIP è il più semplice dei tre da implementare da zero in modo completo e corretto nel tempo a disposizione, pur restando un protocollo "vero" e ampiamente standardizzato (RFC 2453), e permette comunque di toccare concetti fondamentali — distance-vector, convergenza, anti-loop — che sono la base concettuale anche di protocolli più complessi come OSPF (link-state) o BGP (path-vector, per il routing fra sistemi autonomi).

**D. Hai lavorato da solo? Quanto è durato lo sviluppo?**
(Risposta da personalizzare in base alla propria esperienza reale: cronologia, eventuali strumenti di supporto usati per scrivere/rivedere il codice, difficoltà incontrate — ad esempio la messa a punto del jitter RIP o la scoperta del comportamento dei `veth` con l'offload del checksum.)

**D. Qual è stata la parte più difficile da far funzionare?**
(Risposta personale — spunti plausibili dal codice: calibrare correttamente `process_entry` su tutti i casi previsti dalla RFC 2453 §3.9.2, in particolare la distinzione fra "stesso gateway" e "gateway diverso"; oppure scoprire che le interfacce `veth` fanno checksum offload e quindi vanno disattivate esplicitamente nell'emulazione perché altrimenti l'host finale scarterebbe pacchetti in realtà corretti.)
