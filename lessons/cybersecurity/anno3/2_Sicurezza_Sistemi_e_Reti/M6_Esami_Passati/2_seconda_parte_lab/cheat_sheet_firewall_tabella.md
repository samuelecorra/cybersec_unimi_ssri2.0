# 🧱 Cheat Sheet Enciclopedica — Compilare la tabella di filtraggio del firewall (Esame Laboratorio SSRI)

> 📌 **A cosa serve questo file.** È la guida operativa da tenere aperta **durante** la prova di laboratorio (open book) per l'esercizio del firewall: quello in cui il docente mostra un'**immagine di topologia** (reti, router, server, sottoreti) e un elenco di regole in italiano, e chiede di riempire la **tabella a otto colonne** — *Direzione, IP Sorgente, IP Destinazione, Protocollo, Porta Sorgente, Porta Destinazione, Flag ACK, Azione*. Qui trovi, dalla A alla Z: cosa modella la tabella, come si leggono le colonne, la logica del flag ACK, il metodo passo-passo per tradurre *qualunque* topologia e policy in righe corrette, i casi ostici (FTP attivo, RPC, SMTP, NAT, dual-stack), i tre esercizi d'esame già risolti come casi di studio, e come entrare nella testa del docente per affrontare anche varianti mai viste. Se ti serve invece tradurre queste regole in comandi Linux reali, vedi il file gemello [cheat_sheet_iptables.md](cheat_sheet_iptables.md). Riferimenti di teoria: modulo [M7/UD2 – Configurazione firewall](../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L1%20-%20Metodologia%20della%20tabella%20di%20filtraggio%20%28le%208%20colonne%20e%20il%20flag%20ACK%29.md).

---

### **1. Che cosa modella la tabella (e perché è fatta così)**

La tabella descrive le regole di un **packet filter** — un firewall che decide, pacchetto per pacchetto, se lasciarlo passare o bloccarlo, guardando **solo i campi dell'intestazione** (indirizzi IP, protocollo, porte, flag TCP). Tre proprietà fondamentali ne governano il comportamento e vanno tenute a mente sempre:

1. **Valutazione dall'alto verso il basso, prima regola che combacia vince** (*first match wins*, esattamente come le ACL Cisco): appena un pacchetto soddisfa una regola, si applica la sua azione e la valutazione si ferma. L'**ordine delle righe conta**.
2. **Default deny finale**: l'ultima riga nega tutto ciò che non è stato esplicitamente permesso. È il principio *fail-safe defaults*: ciò che non è espressamente autorizzato è proibito. Questa singola riga è ciò che rende "gratis" l'isolamento tra sottoreti (vedi §5.5).
3. **È un filtro STATELESS**: il firewall **non tiene memoria** delle connessioni aperte. Non "ricorda" che una connessione è stata avviata dall'interno, quindi deve riconoscere il traffico di ritorno **guardando i singoli pacchetti**. È questo limite che rende necessario il trucco del **flag ACK** (§3).

> 💡 **Perché tanta enfasi sullo "stateless"?** Un firewall reale moderno è *stateful*: tiene una tabella delle connessioni e riconosce da solo le risposte. Ma l'esercizio d'esame ti chiede di ragionare come un packet filter puro — proprio per farti capire *a mano* il meccanismo che un firewall stateful automatizza. Tutta la difficoltà (e tutti i punti) stanno nel gestire correttamente il traffico di ritorno senza avere uno stato.

---

### **2. Le otto colonne, una per una**

| Colonna | Significato e valori tipici |
|---|---|
| **Direz.** (Direzione) | Il verso del flusso rispetto al firewall/alle zone. Si scrive come `LAN→DMZ`, `Internet→DMZ`, `IN`/`OUT`, oppure con le interfacce. Serve a legare la regola all'interfaccia giusta e a rendere leggibile la tabella. |
| **IP Sorg** | Indirizzo di partenza: un host singolo (`10.0.4.11`), un'intera sottorete (`10.0.0.0/24`) o `any` (qualsiasi, tipicamente "da Internet"). |
| **IP Dest** | Indirizzo di destinazione, stessi formati. |
| **Protoc.** | `TCP`, `UDP`, `ICMP` (o `any`). Determina quali altre colonne hanno senso (le porte valgono per TCP/UDP; l'ACK solo per TCP). |
| **Porta Sorg** | Porta di partenza. Per un **client** è una **porta effimera**, che si scrive `>1023` (o `1024:`, "porte alte"): il sistema operativo la assegna a caso sopra la 1023. Per la **risposta di un server** è la porta del servizio (80, 443, …). |
| **Porta Dest** | Porta di arrivo. Per la **richiesta a un servizio** è la porta **nota** del servizio (80, 443, 21…); per la **risposta al client** è la porta effimera (`>1023`). |
| **Flag ACK** | Il bit ACK del segmento TCP. `0` = pacchetto **senza ACK** = un SYN, cioè l'**apertura** di una connessione. `1` = pacchetto **con ACK** = appartiene a una connessione **già avviata**. Si scrive `-` o `any` quando irrilevante (UDP, ICMP) o quando si vogliono coprire entrambi i casi. |
| **Azione** | `permit`/`allow` oppure `deny`/`drop`. |

> ⚠️ **L'errore numero uno: la porta sorgente.** Uno studente su due scrive la porta del servizio (es. 80) come porta sorgente del client. **Sbagliato.** Un client non parte *mai* dalla porta del servizio: parte da una **porta effimera** `>1023` assegnata dal SO. La porta nota (80, 443, 21, …) compare come **destinazione** nella richiesta e come **sorgente** nella risposta. Incidila nella memoria: *porta nota = destinazione all'andata, sorgente al ritorno; il client usa sempre porte alte*.

---

### **3. Il flag ACK: il meccanismo che sostituisce lo stato**

Questo è il **cuore concettuale** dell'intero esercizio. Va capito, non memorizzato.

In una connessione TCP il **primo** segmento — quello che apre la connessione — è un **SYN con ACK=0**. **Tutti** i segmenti successivi (a partire dal SYN-ACK di risposta del server) hanno **ACK=1**. Questa asimmetria consente a un filtro stateless, che non ha memoria, di capire **chi ha aperto** la connessione semplicemente guardando un bit:

- Un pacchetto **ACK=0** in ingresso = qualcuno **sta tentando di aprire** una nuova connessione da quel verso.
- Un pacchetto **ACK=1** in ingresso = può essere **solo la risposta** a una connessione già aperta dall'altro lato.

Da qui **la regola d'oro**: **il traffico di ritorno si autorizza solo con `ACK=1`**. Così la risposta legittima passa, ma un host esterno **non può iniziare** una connessione verso l'interno: il suo SYN (ACK=0) non trova alcuna regola di permit nel verso d'ingresso e cade nel default deny. È l'equivalente "a mano" dell'operatore `established` delle ACL Cisco e dello stato `ESTABLISHED` di iptables.

> 📌 **Una connessione consentita = due righe.** Una connessione TCP è **bidirezionale**: i pacchetti viaggiano in entrambi i versi, e il firewall vede la richiesta e la risposta come **due flussi separati**, ciascuno dei quali attraversa il filtro per conto suo. Servono quindi, di norma, **due righe per ogni connessione consentita**:
> - **riga di richiesta**: nel verso di chi *apre*, porta nota in **destinazione**, `ACK = 0/any`;
> - **riga di risposta**: nel verso opposto, porta nota in **sorgente**, `ACK = 1`.

> 💡 **Perché `0/any` e non solo `0` nella richiesta?** Perché la connessione, dopo il SYN iniziale, continua a mandare pacchetti *nello stesso verso* con ACK=1 (i dati, gli ACK dei dati). Se nella riga di richiesta mettessi `ACK=0` secco, bloccheresti tutto tranne il primissimo pacchetto. Con `0/any` (o lasciando la colonna a `any`) permetti l'intero flusso in quel verso. Il controllo stretto `ACK=1` va messo **solo sulla riga di ritorno**, che è quella che deve impedire aperture dall'esterno.

---

### **4. Come leggere l'immagine di topologia (la parte che spaventa)**

Prima di scrivere una sola riga, devi **decodificare l'immagine** della rete. Cerca sistematicamente questi elementi:

1. **Il firewall/router centrale e le sue interfacce.** Ogni interfaccia (`eth0`, `eth1`, `wan0`…) ha un IP che è il **gateway** di una zona. Le interfacce ti dicono **quante zone** ci sono e quali sottoreti separano. Esempio: `eth0 10.0.0.1`, `eth1 10.0.1.1`, `eth2 10.0.4.1`, `wan0 75.23.x.1` → quattro zone.
2. **Le sottoreti e la loro maschera.** Un `/24` (es. `10.0.0.0/24`) raggruppa 256 indirizzi `10.0.0.*`. Ogni zona è una sottorete; gli host dentro hanno IP che iniziano con quel prefisso.
3. **Gli host e i loro IP.** Annotati i singoli indirizzi (PC1 `.10`, PC2 `.11`…) perché le regole "solo pc2" li useranno.
4. **I server e i loro servizi/porte.** Accanto a ogni server la traccia indica i servizi (HTTPS, FTP, SMTP, database su 5432…). **Traduci subito ogni servizio nella sua porta** (vedi tabella §11): è il dato che ti serve nelle colonne Porta.
5. **La DMZ.** La sottorete dei servizi esposti; capisci quali server ci stanno e se sono sullo **stesso switch** (traffico tra loro non filtrabile, §5.5).
6. **Elementi speciali**: uno **switch** che unisce più host in una sottorete (traffico intra-switch invisibile al firewall); un **access point in bridge** (i guest sono su una sottorete a parte, §8); indirizzi **IPv6** accanto agli IPv4 (dual-stack, §9).

> 💡 **Disegnati a lato un mini-schema.** Prima di compilare, scriviti l'elenco: `Zona A (eth0, 10.0.0.0/24): host…` / `Zona DMZ (eth2, 10.0.4.0/24): WebSrv 10.0.4.11:443, DB 10.0.4.10:5432` / `Internet (wan0)`. Con questa "legenda" davanti, riempire la tabella diventa meccanico.

---

### **5. Il metodo passo-passo (vale per QUALSIASI traccia)**

#### **5.1. Passo 1 — Elenca le comunicazioni consentite, una per una**

Rileggi la policy in italiano e trasformala in una lista di **triplette** «chi inizia → verso quale servizio/porta». Ogni frase della traccia è una (o più) tripletta:
- "Gli sviluppatori hanno accesso al database" → `Sviluppatori → DB : 5432` (e 443 se detto).
- "Il web server è esposto a Internet" → `Internet → WebSrv : 443`.
- "Solo pc2 accede all'http del web server" → `pc2 → WebSrv : 80`.

#### **5.2. Passo 2 — Stabilisci CHI INIZIA (il passaggio decisivo)**

Per ogni tripletta, chiediti **chi apre la connessione**. È il passaggio da cui dipende tutta la correttezza:
- "I PC interni **navigano**/accedono a X" → l'iniziativa è **interna**, la richiesta esce.
- "Il server è **esposto**/accessibile da fuori" → l'iniziativa è **esterna**, la richiesta **entra**.
- "Il mail server **invia** posta" → iniziativa del server; "**riceve** posta" → iniziativa esterna (vedi §7.3).

> ⚠️ **Chi apre determina il verso delle righe.** Se sbagli chi inizia, apri il firewall dalla parte sbagliata. "Web server esposto a Internet" ⇒ la riga di richiesta è **in ingresso** (`any → server:443`, ACK 0/any) e la risposta esce (ACK=1). "PC naviga" ⇒ la riga di richiesta è **in uscita**. Sono situazioni speculari: non confonderle.

#### **5.3. Passo 3 — Scrivi le due righe per ogni tripletta**

Applica lo schema del §3: riga di richiesta (porta nota in destinazione, ACK 0/any, verso di chi apre) + riga di risposta (porta nota in sorgente, ACK=1, verso opposto). Usa `>1023` per la porta effimera del client.

#### **5.4. Passo 4 — Traduci la parola "SOLO"**

La parola **"solo"** nella traccia è sempre una restrizione, e va tradotta in un campo ristretto:
- "**solo** pc2 può…" → **IP sorgente** = quel singolo host (`10.0.2.21`), non `any`.
- "**solo** la LAN può…" → IP sorgente = la sottorete (`10.0.2.0/24`).
- "**solo** da fuori…" → IP sorgente = `any` **dall'interfaccia esterna** (e nessuna regola equivalente dall'interno).
- "**solo** HTTPS…" → **porta destinazione** = 443 e basta (non 80).

#### **5.5. Passo 5 — L'isolamento è GRATIS (non scrivere regole = vietare)**

Molte tracce impongono che due sottoreti **non si vedano** ("i PC della segreteria non hanno accesso ai PC degli sviluppatori e viceversa"). Con il default deny questo è **automatico**: **non scrivere** alcuna regola di permit tra loro **è già** vietarlo. Non servono righe di *deny* esplicite (a meno che la traccia non chieda espressamente di evidenziarle).

> ⚠️ **Occhio al traffico intra-sottorete.** Se due host stanno sullo **stesso switch / stessa sottorete** (es. Web Server e Database entrambi in DMZ `10.0.4.0/24`), il loro traffico **non attraversa il firewall** e quindi **non è da esso filtrabile**: la regola "il web server accede al database" in quel caso **non genera righe**. Verifica sempre, sulla topologia, se sorgente e destinazione stanno su **lati diversi** del firewall (interfacce diverse). Solo il traffico che cambia interfaccia si può filtrare.

#### **5.6. Passo 6 — Chiudi con il default deny**

L'ultima riga nega tutto il resto:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| any | any | any | any | any | any | any | **deny** |

---

### **6. Gli schemi base (da riconoscere a colpo d'occhio)**

#### **6.1. Client interno → servizio esterno (la LAN naviga)**

Host `10.0.0.10` verso un web server esterno in HTTPS. L'iniziativa è interna:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| OUT | 10.0.0.10 | any | TCP | >1023 | 443 | 0/any | permit |
| IN | any | 10.0.0.10 | TCP | 443 | >1023 | 1 | permit |

#### **6.2. Servizio interno esposto → esterno raggiunge il server**

Web server `10.0.4.11` esposto a Internet in HTTPS. L'iniziativa è **esterna** (nota come cambia il verso rispetto a sopra):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN | any | 10.0.4.11 | TCP | >1023 | 443 | 0/any | permit |
| OUT | 10.0.4.11 | any | TCP | 443 | >1023 | 1 | permit |

#### **6.3. Servizio UDP (niente ACK)**

DNS verso un resolver esterno. UDP non ha il bit ACK: si scrivono comunque due righe, distinguendo i versi con porte e direzione, e mettendo `-` nella colonna ACK:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| OUT | 10.0.0.0/24 | any | UDP | >1023 | 53 | - | permit |
| IN | any | 10.0.0.0/24 | UDP | 53 | >1023 | - | permit |

> 💡 Senza il bit ACK, un filtro stateless è intrinsecamente più debole con UDP: non sa distinguere una "risposta" da una nuova richiesta in arrivo. All'esame ci si limita ad aprire il verso di ritorno sulla porta corretta; nella realtà servono firewall stateful.

---

### **7. I casi ostici (quelli che il docente ama chiedere)**

#### **7.1. FTP attivo — il trabocchetto classico**

L'FTP usa **due connessioni TCP**: la **connessione di controllo** (aperta dal client verso la **porta 21**, ci passano i comandi) e la **connessione dati** (i file). In modalità **attiva**, la connessione dati la apre **il server**, dalla propria **porta 20** verso una porta effimera del client. È l'**iniziativa rovesciata**: è il server a bussare.

Web server con FTP attivo accessibile dall'esterno (`10.0.1.10`):

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN (controllo, richiesta) | any | 10.0.1.10 | TCP | >1023 | 21 | 0/any | permit |
| OUT (controllo, risposta) | 10.0.1.10 | any | TCP | 21 | >1023 | 1 | permit |
| OUT (dati, **aperta dal server**) | 10.0.1.10 | any | TCP | 20 | >1023 | 0/any | permit |
| IN (dati, risposta) | any | 10.0.1.10 | TCP | 20 | >1023 | 1 | permit |

> ⚠️ Nella riga della **connessione dati attiva** il pacchetto di apertura ha **ACK=0/any e parte dalla porta 20 del server**: è il server che inizia, verso l'esterno/il client. Questo è il punto che quasi tutti sbagliano, ed è il motivo per cui l'FTP attivo è detto "firewall-hostile". In **passivo** (`PASV`), invece, anche la connessione dati la apre il **client**, verso una porta alta del server: tutte e due le connessioni partono dal client (più amico del firewall lato client, ma richiede di aprire un range di porte alte lato server).

#### **7.2. RPC (portmapper, porta 111)**

I servizi **RPC** (NFS, servizi Windows) usano il **portmapper** sulla **porta 111** (TCP e UDP) per pubblicare i servizi, che poi girano su **porte dinamiche alte**. Per "solo la LAN accede a RPC nella DMZ" si autorizza la LAN verso la 111 del server:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| LAN→DMZ | 10.0.2.0/24 | 10.0.1.10 | TCP | >1023 | 111 | 0/any | permit |
| DMZ→LAN | 10.0.1.10 | 10.0.2.0/24 | TCP | 111 | >1023 | 1 | permit |

> ⚠️ Come l'FTP, RPC negozia **porte dinamiche** oltre alla 111: un filtro statico non le copre tutte (servirebbe un helper stateful). All'esame si modella l'accesso alla porta 111 restringendo l'IP sorgente alla LAN, che è ciò che la traccia chiede.

#### **7.3. SMTP (posta, porta 25) — sempre due versi**

Un mail server "che funziona secondo SMTP" deve **ricevere** posta (altri aprono verso la sua porta 25) **e inviare** posta (lui apre verso la porta 25 di altri server). Sono **due flussi con iniziativa opposta**, entrambi sulla 25:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| IN (ricezione, richiesta) | any | mailserver | TCP | >1023 | 25 | 0/any | permit |
| OUT (ricezione, risposta) | mailserver | any | TCP | 25 | >1023 | 1 | permit |
| OUT (invio, richiesta) | mailserver | any | TCP | >1023 | 25 | 0/any | permit |
| IN (invio, risposta) | any | mailserver | TCP | 25 | >1023 | 1 | permit |

> 💡 Dimenticare uno dei due versi lascia la posta monodirezionale. "Funzionare correttamente" implica **entrambe** le coppie di righe.

---

### **8. NAT, port forwarding e access point in bridge**

#### **8.1. Port forwarding (DNAT) — esporre un servizio interno su una porta pubblica**

"Il server di backup è esposto a Internet sulla porta 5000" si realizza con un **DNAT**: le connessioni che arrivano sull'IP pubblico del firewall alla porta 5000 vengono rigirate al server interno `10.0.4.15`. Nella tabella astratta si esprime come richiesta **in ingresso** verso il server sulla 5000:

| Direz. | IP Sorg | IP Dest | Protoc. | Porta Sorg | Porta Dest | Flag ACK | Azione |
|---|---|---|---|---|---|---|---|
| Internet→Servizi | any | 10.0.4.15 | TCP | >1023 | 5000 | 0/any | permit |
| Servizi→Internet | 10.0.4.15 | any | TCP | 5000 | >1023 | 1 | permit |

> 💡 Che si indichi l'IP pubblico del firewall (prima del DNAT) o l'IP privato del server (dopo il DNAT) è questione di punto di vista: la sostanza è che dall'esterno si raggiunge **solo** quel server e **solo** su quella porta. Nella soluzione conviene puntare l'IP del server e annotare che a monte il firewall fa port forwarding.

#### **8.2. Access point in bridge e sottorete guest**

Un **AP in bridge** collega il Wi-Fi alla rete cablata senza fare da router. Nelle tracce l'AP ha spesso due indirizzi (lato Wi-Fi `192.168.4.1`, lato firewall `10.0.1.10`) e i guest stanno su `192.168.4.0/24`. Contano due cose:
- **Chi genera il traffico**: i **client guest** (`192.168.4.0/24`) oppure l'**AP stesso** (`10.0.1.10`) — soggetti diversi, permessi diversi.
- **L'isolamento**: "i dispositivi guest possono comunicare **solo con Internet**" ⇒ dalla sottorete guest si permette solo il traffico verso Internet, negando (per default) LAN e servizi.

> ⚠️ **AP ≠ guest.** Se la traccia dice che "l'AP registra gli accessi sul servizio di autenticazione", quella è una regola dell'**AP** (`10.0.1.10 → 10.0.4.11:443`), non dei telefoni. Permettere ai guest di raggiungere il servizio interno violerebbe l'isolamento richiesto.

#### **8.3. Dual-stack IPv4 / IPv6**

Se ogni nodo ha **sia IPv4 sia IPv6** (`10.0.1.10` e `fc00:1::10`), la logica delle regole è **identica** — cambiano solo gli indirizzi. Ma vanno **scritte per entrambe le famiglie**: filtrare solo l'IPv4 lascia l'IPv6 completamente aperto. Nella tabella d'esame o duplichi le righe con gli indirizzi `fc00:…`, o annoti che vanno replicate (con `ip6tables` sul firewall reale). `fc00::/7` è il blocco IPv6 Unique Local (l'equivalente degli IP privati RFC 1918).

---

### **9. Le trappole ricorrenti (checklist degli errori)**

> ⚠️ **I sette errori che costano punti:**
> 1. **Porta sorgente = porta del servizio** invece di `>1023` per il client. → La porta nota è *destinazione* all'andata, *sorgente* al ritorno.
> 2. **Chi inizia la connessione sbagliato** → righe nel verso errato (servizio esposto ⇒ richiesta in ingresso).
> 3. **Riga di ritorno mancante** → la connessione funziona solo in un verso. Ogni connessione = due righe.
> 4. **`ACK=1` messo sulla richiesta** invece che sulla risposta → blocchi l'apertura o, peggio, permetti aperture dall'esterno.
> 5. **Filtrare traffico intra-sottorete** che non passa dal firewall → righe inutili (Web↔DB nella stessa DMZ).
> 6. **"Solo" non applicato** → accesso troppo largo (tutta la LAN invece del solo pc2).
> 7. **IPv6 dimenticato** in dual-stack, o **default deny finale assente**.

---

### **10. I tre esercizi d'esame risolti (casi di studio)**

Gli appelli di laboratorio finora sono quattro, ma **02/07/2025 e 12/09/2025 hanno lo stesso esercizio firewall**: i casi unici sono **tre**. Per ciascuno, la struttura e la soluzione completa linkata.

#### **10.1. Caso A — Firewall a 4 zone con isolamento (appello 21/02/2025)**

- **Topologia**: firewall a **quattro interfacce** — Sviluppatori (`10.0.0.0/24`), Segreteria (`10.0.1.0/24`), DMZ Servizi (`10.0.4.0/24`: DB, Web, Servizi segreteria), Internet.
- **Policy chiave**: Sviluppatori→DB (443 e 5432); Segreteria→Web e→Servizi (443); Internet→Web (443); Sviluppatori↮Segreteria **isolate**; resto negato.
- **Punti didattici**: l'isolamento Sviluppatori↔Segreteria è **gratis** (default deny, niente righe); il traffico Web↔DB è **intra-DMZ** e **non genera righe**; il web server è raggiungibile da Internet solo su 443 e solo verso il suo IP.
- **Soluzione completa**: [Soluzione_Lab_21_02_2025.md](lab_21_febbraio_2025/Soluzione_Lab_21_02_2025.md).

#### **10.2. Caso B — Dual-stack con FTP attivo, RPC e SMTP (appello 13/06/2025)**

- **Topologia**: Router1 a tre lati **dual-stack IPv4/IPv6** — Esterno, DMZ (Web `10.0.1.10`, DNS, Mail `10.0.1.12`), LAN (pc1/pc2/pc3). Web offre http/https/RPC/**FTP attivo**; Mail offre SMTP.
- **Policy chiave**: solo LAN→RPC; solo da fuori HTTPS; solo da fuori FTP attivo; http solo per pc2; SMTP funzionante nei due versi.
- **Punti didattici**: l'**FTP attivo** è il trabocchetto (connessione dati aperta dal server, porta 20, ACK 0/any in uscita); SMTP richiede **entrambi i versi**; tutta la tabella va **replicata in IPv6** (`ip6tables`, `fc00:*`).
- **Soluzione completa**: [Soluzione_Lab_13_06_2025.md](lab_13_giugno_2025/Soluzione_Lab_13_06_2025.md).

#### **10.3. Caso C — Reception/Servizi/Guest con AP bridge e DNAT (appelli 02/07/2025 e 12/09/2025)**

- **Topologia**: firewall a 4 zone — Reception (`10.0.0.0/24`), Servizi (`10.0.4.0/24`: Auth `.11`, Backup `.15`, DB `.10`), **AP-Guest** in bridge (guest `192.168.4.0/24`), Internet.
- **Policy chiave**: Reception→Backup (443,445) e→Auth (443); l'**AP**→Auth (443); guest→**solo Internet**; Auth→DB; Backup **esposto su 5000** (DNAT); resto negato.
- **Punti didattici**: **Auth↔DB è intra-subnet** (non filtrato, niente righe); **AP ≠ guest** (soggetti diversi); il backup su 5000 richiede **port forwarding/DNAT**; i guest isolati da tutto tranne Internet.
- **Soluzione completa**: [Soluzione_Lab_02_07_2025.md](lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md) (vale anche per il 12/09).

---

### **11. Nella testa del docente: pattern e varianti**

Dai tre esercizi unici emergono i **criteri di progettazione** del docente (Cimato/Anisetti). Capirli ti prepara anche a topologie mai viste.

#### **11.1. I mattoni ricorrenti**

Ogni traccia firewall è composta rimescolando gli stessi mattoni concettuali, ciascuno con la sua "mossa" obbligata:

| Mattone nella traccia | Mossa nella tabella |
|---|---|
| "X ha accesso a Y" (interno→interno o interno→servizio) | coppia richiesta/risposta, iniziativa di X |
| "Server esposto a Internet [in HTTPS]" | richiesta **in ingresso** verso quel server, quella porta |
| "Esposto sulla porta N non standard" | come sopra + nota **DNAT/port forwarding** |
| "Solo X può…" / "solo da fuori" | restringi **IP sorgente** e/o porta |
| "A e B non si vedono" | **niente righe** (default deny) |
| Due server nella stessa DMZ che si parlano | **intra-subnet, niente righe** |
| "FTP attivo" | connessione dati **dal server:20**, ACK 0/any |
| "SMTP funzionante" | **due versi** sulla 25 |
| "RPC" | porta 111 (TCP/UDP), IP sorgente ristretto |
| AP in bridge + guest | distingui **AP** dai **client guest** |
| IPv4 + IPv6 accanto | **replica** in `ip6tables` |
| (sempre) | **default deny** finale |

#### **11.2. Riproporrà la stessa topologia, una simile o una nuova?**

- **Stessa topologia** (già capitato tra 02/07 e 12/09): riconosci lo schema e applichi la soluzione — ma **rileggi** IP, porte e policy sulla traccia davanti a te, perché il docente cambia spesso i dettagli.
- **Stessi mattoni, disposizione diversa** (lo scenario più probabile): un'altra rete a 3-4 zone con un mix diverso degli stessi ingredienti (magari un database MySQL 3306 invece di PostgreSQL 5432, o un servizio LDAP, o FTP **passivo** invece che attivo). Il **metodo dei §5 è identico**: cambia solo quali mattoni monti.
- **Mattone nuovo** (mai visto ma nel programma): potrebbe comparire una **VPN**, un **proxy**, un servizio su porta inusuale, o la richiesta di scrivere **deny espliciti** oltre al default deny. Per ognuno vale la regola generale del §5.2: *quante connessioni, chi apre ciascuna, su quali porte* → due righe con l'ACK giusto.

> 💡 **Regola aurea contro l'ignoto**: qualunque sia la topologia, il metodo non cambia — leggi l'immagine (§4), elenca le triplette (§5.1), stabilisci chi inizia (§5.2), scrivi due righe per connessione (§5.3), applica i "solo" (§5.4), sfrutta il default deny per gli isolamenti (§5.5), chiudi con deny any (§5.6). Non devi "aver già visto" quella rete: la **compili** applicando il metodo.

---

### **12. Checklist finale (prima di consegnare la tabella)**

> 📌 **Verifica una per una:**
> 1. Hai **letto l'immagine**: interfacce, sottoreti, host, servizi→porte, DMZ, switch, AP, IPv6?
> 2. Ogni comunicazione della policy è diventata una **tripletta** «chi inizia → servizio»?
> 3. Per ciascuna hai stabilito **chi apre** la connessione (specie FTP attivo e SMTP)?
> 4. Ogni connessione consentita ha **entrambe le righe** (richiesta + risposta con **ACK=1**)?
> 5. Le porte note sono in **destinazione** nella richiesta e in **sorgente** nella risposta? Il client usa `>1023`?
> 6. La parola "**solo**" è stata tradotta in restrizioni di IP sorgente e/o porta?
> 7. Le sottoreti da **isolare** non sono raggiunte da alcuna regola di permit?
> 8. Non stai filtrando traffico **intra-sottorete** che non passa dal firewall?
> 9. C'è un servizio esposto su porta non standard che richiede **port forwarding/DNAT**?
> 10. Se la rete è **dual-stack**, hai coperto anche l'**IPv6**?
> 11. Hai chiuso con il **default deny** finale?

---

### **13. Tabella di riferimento rapido (porte dei servizi comuni)**

| Servizio | Porta | Protoc. | Note per l'esame |
|---|---|---|---|
| HTTP | 80 | TCP | web in chiaro |
| HTTPS | 443 | TCP | web cifrato |
| FTP controllo | 21 | TCP | comandi; sempre aperta dal client |
| FTP dati (attivo) | 20 | TCP | **aperta dal server** verso il client |
| FTP dati (passivo) | >1023 | TCP | aperta dal client verso porta alta del server |
| SSH | 22 | TCP | accesso remoto sicuro |
| Telnet | 23 | TCP | accesso remoto **in chiaro** (insicuro) |
| SMTP | 25 | TCP | posta; **due versi** (ricezione+invio) |
| DNS | 53 | UDP (e TCP) | risoluzione nomi; niente ACK su UDP |
| POP3 / IMAP | 110 / 143 | TCP | ritiro posta client |
| RPC portmapper | 111 | TCP+UDP | + porte dinamiche |
| NetBIOS | 137-139 | TCP/UDP | condivisione Windows (spesso da bloccare) |
| SMB / CIFS | 445 | TCP | condivisione file Windows |
| NTP | 123 | UDP | sincronizzazione ora |
| SNMP | 161 | UDP | gestione rete |
| LDAP | 389 | TCP | directory |
| RDP | 3389 | TCP | desktop remoto Windows |
| MySQL | 3306 | TCP | database |
| PostgreSQL | 5432 | TCP | database |

> ✅ **In sintesi.** La tabella è un packet filter **stateless**, valutato *first-match* con **default deny** finale. Poiché non ha stato, il traffico di ritorno si riconosce dal **flag ACK**: richiesta con ACK 0/any nel verso di chi apre, risposta con **ACK=1** nel verso opposto — **due righe per connessione**. La porta nota è **destinazione** all'andata e **sorgente** al ritorno; il client usa porte effimere `>1023`. Il passo decisivo è stabilire **chi inizia** ogni connessione; da lì tutto discende. Leggi l'immagine, elenca le triplette, applica il metodo dei §5, gestisci i casi ostici (FTP attivo, SMTP, RPC, DNAT, dual-stack) e chiudi con il deny any. Qualunque topologia il docente proponga, **il metodo la compila sempre**.

---

> **Riferimenti**: modulo [M7/UD2 – Configurazione firewall](../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L1%20-%20Metodologia%20della%20tabella%20di%20filtraggio%20%28le%208%20colonne%20e%20il%20flag%20ACK%29.md) — [L1 le 8 colonne e l'ACK](../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L1%20-%20Metodologia%20della%20tabella%20di%20filtraggio%20%28le%208%20colonne%20e%20il%20flag%20ACK%29.md), [L2 DMZ ed esposizione](../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L2%20-%20DMZ%20ed%20esposizione%20di%20servizi%20verso%20Internet.md), [L3 FTP/RPC/SMTP](../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L3%20-%20Servizi%20multi-connessione%20%28FTP%20attivo-passivo,%20RPC,%20SMTP%29.md), [L4 NAT/bridge/dual-stack](../../M7_Laboratorio_Wireshark_e_Firewall/UD2_Configurazione_Firewall/L4%20-%20NAT,%20bridge,%20port%20forwarding%20e%20dual-stack%20IPv4-IPv6.md). Teoria packet filter e ACL: [M5/UD1/L3-L4](../../M5/UD1/L4%20-%20Access%20Control%20List%20%28ACL%29.md). Traduzione in iptables: [cheat_sheet_iptables.md](cheat_sheet_iptables.md). Casi risolti: [21/02](lab_21_febbraio_2025/Soluzione_Lab_21_02_2025.md), [13/06](lab_13_giugno_2025/Soluzione_Lab_13_06_2025.md), [02/07 ≡ 12/09](lab_2_luglio_2025/Soluzione_Lab_02_07_2025.md).
