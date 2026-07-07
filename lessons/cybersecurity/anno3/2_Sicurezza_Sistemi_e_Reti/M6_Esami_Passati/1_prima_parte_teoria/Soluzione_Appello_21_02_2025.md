# Soluzione Appello di Sicurezza – SSRI — Teoria — 21/02/2025

**Docenti: S. Cimato – V. Anisetti** · Esame da 6 CFU (rispondere a tutte le domande)

Per ogni domanda sono indicati i riferimenti alle lezioni di teoria, l'inquadramento e lo svolgimento discorsivo completo.

---

## Domanda 1 — SET-UID

> **Riferimenti di teoria**:
>
> - [M3/UD4/L1 — Set-UID, RUID-EUID e attacchi via variabili d'ambiente](../../M3/UD4_Approfondimenti_Esame/L1%20-%20Set-UID,%20RUID-EUID%20e%20attacchi%20via%20variabili%20d'ambiente.md) (§4 procedura di trasformazione, §7 attacco via PATH)
> - [M3/UD3/L2 — Controllo degli accessi in Linux](../../M3/UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md) (§6 RUID/EUID/SUID, §7 Set-UID/Set-GID)
> - [M2/UD1/L4 — Principi di sicurezza](../../M2/UD1/L4%20-%20Principi%20di%20sicurezza.md) (§6 privilegio minimo)

**(a) Funzionamento e problematiche di sicurezza.** Ogni processo UNIX porta con sé tre identità: il *Real User ID* (RUID), che indica l'utente che ha effettivamente lanciato il programma, l'*Effective User ID* (EUID), che è l'identità realmente usata dal kernel per decidere se un accesso è consentito, e il *Saved User ID*, che serve a sospendere e riprendere i privilegi. Normalmente questi tre valori coincidono, perché un programma eredita l'identità di chi lo esegue. Il meccanismo Set-UID rompe di proposito questa coincidenza: quando un eseguibile ha il bit Set-UID attivo, all'atto della `exec()` il kernel pone l'EUID pari allo **UID del proprietario del file**, lasciando invariato il RUID. Il programma gira così con i privilegi del proprietario per tutta la durata dell'esecuzione: è il modo con cui, ad esempio, `passwd` — di proprietà di root e Set-UID — consente a un utente comune di modificare `/etc/shadow`, file scrivibile solo da root.

Il rovescio della medaglia è che un binario Set-UID di proprietà di root è, in pratica, **codice root eseguibile da chiunque**: un suo difetto diventa immediatamente una via di *privilege escalation*. I pericoli tipici sono l'invocazione di comandi esterni o shell (che apre la strada ad attacchi via `PATH` e metacaratteri), la fiducia in variabili di ambiente controllate dall'utente (`PATH`, `IFS`, `LD_PRELOAD`), l'uso di percorsi relativi e il passaggio di input non validato a funzioni che lo interpretano. La buona pratica è applicare rigorosamente il principio del privilegio minimo, rilasciando i privilegi appena l'operazione critica è conclusa.

**(b) Compilazione e trasformazione in Set-UID.** Dato il sorgente `myls.c` che contiene `system("ls")`, la sequenza corretta è di tre comandi, e l'ordine è vincolante:

```bash
gcc myls.c -o myls        # il binario nasce di proprietà di chi compila
sudo chown root myls      # il proprietario diventa root
sudo chmod 4755 myls      # si attiva il bit Set-UID → -rwsr-xr-x
```

La verifica con `ls -l myls` mostra `-rwsr-xr-x 1 root root ... myls`, dove la `s` al posto della `x` del proprietario segnala il Set-UID attivo. È essenziale dare il `chmod` **dopo** il `chown`: quando il proprietario di un file cambia, il kernel azzera automaticamente i bit Set-UID/Set-GID come misura di sicurezza, quindi invertendo i passi il bit verrebbe cancellato. Il `chown` a root, inoltre, richiede privilegi di root: un utente comune non può cedere a root un proprio file e renderlo Set-UID root, altrimenti l'escalation sarebbe immediata.

**(c) Esecuzione di codice dannoso tramite la variabile `PATH`.** Il punto debole di `myls` è che `system("ls")` non esegue direttamente il binario `/bin/ls`, ma avvia una shell (`/bin/sh -c "ls"`) che **cerca** l'eseguibile `ls` scorrendo le directory elencate in `PATH`. Poiché `ls` è indicato senza percorso assoluto, la shell si affida ciecamente a una variabile che è sotto il controllo dell'attaccante. Basta quindi creare un finto `ls` in una directory scrivibile e anteporla al `PATH`:

```bash
cd /tmp
echo '#!/bin/sh' > ls
echo '/bin/sh'  >> ls        # il finto "ls" apre una shell
chmod +x /tmp/ls
export PATH=/tmp:$PATH        # /tmp viene consultata per prima
./myls                        # myls (EUID root) chiama system("ls")
```

Quando `myls`, che gira con EUID 0, invoca `system("ls")`, la shell trova prima `/tmp/ls` e lo esegue **con l'EUID del processo, cioè root**: l'attaccante ottiene una shell di root. L'attacco funziona perché convergono tre ingredienti — input non fidato (`PATH`), risoluzione di un comando per nome relativo e privilegi elevati; la contromisura consiste nel sostituire `system()` con `execve("/bin/ls", …)` usando percorso assoluto e ambiente ripulito, e nel reimpostare `PATH` a un valore sicuro. Da notare che l'esito dipende anche dalla shell: `dash` (la `/bin/sh` di Kali/Debian) mantiene i privilegi, mentre bash li declasserebbe rilevando EUID ≠ RUID.

---

## Domanda 2 — Politiche di sicurezza

> **Riferimenti di teoria**:
>
> - [M3/UD2/L1 — Principi di controllo degli accessi](../../M3/UD2/L1%20-%20Principi%20di%20controllo%20degli%20accessi.md) (§1 definizione di politica di sicurezza, NIST/RFC 4949/X.800)
> - [M3/UD2/L2 — Politiche di controllo degli accessi – MAC](../../M3/UD2/L2%20-%20Politiche%20di%20controllo%20degli%20accessi%20%E2%80%93%20MAC.md)
> - [M3/UD2/L3 — Politiche di controllo degli accessi – DAC](../../M3/UD2/L3%20-%20Politiche%20di%20controllo%20degli%20accessi%20%E2%80%93%20DAC.md)
> - [M3/UD3/L1 — Controllo degli accessi in Windows](../../M3/UD3/L1%20-%20Controllo%20degli%20accessi%20in%20Windows.md) · [M3/UD3/L2 — Controllo degli accessi in Linux](../../M3/UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md)

**(a) Che cos'è una politica di sicurezza.** Una politica di sicurezza è l'insieme delle **regole che stabiliscono chi (quali soggetti) può accedere a cosa (quali oggetti) e in quale modo (con quali diritti)**. È la formulazione, a livello logico e organizzativo, degli obiettivi di protezione del sistema — riservatezza, integrità e disponibilità (la triade CIA) — mentre i **meccanismi** di controllo degli accessi sono gli strumenti tecnici che la fanno rispettare. La distinzione è importante: la politica dice *cosa* deve valere, il meccanismo *come* lo si impone. La RFC 4949 definisce infatti il controllo degli accessi come il processo con cui l'uso delle risorse è regolato *in base a una politica di sicurezza*, e la X.800 come "la prevenzione dell'uso non autorizzato di una risorsa, incluso l'uso improprio di una risorsa autorizzata". Una politica ben posta poggia su tre elementi — soggetto, oggetto e diritto di accesso — e si accompagna a autenticazione (verifica dell'identità), autorizzazione (verifica del diritto) e auditing (tracciamento).

**(b) MAC contro DAC nei sistemi operativi moderni.** Le due grandi famiglie di politiche differiscono per **chi decide** i permessi. Nel **DAC (Discretionary Access Control)** la decisione è "discrezionale": il proprietario di una risorsa può concedere ad altri, a sua scelta, i diritti su di essa. È il modello dei permessi UNIX classici (`rwx` per owner/group/other) e delle DACL di Windows: flessibile e intuitivo, ma esposto alla propagazione incontrollata dei privilegi, perché un utente legittimo — o un malware che agisce con la sua identità — può ridistribuire liberamente gli accessi. Nel **MAC (Mandatory Access Control)** le regole sono invece imposte centralmente dal sistema attraverso **etichette di sicurezza** associate a soggetti e oggetti, e nemmeno il proprietario può modificarle: l'accesso è concesso solo se l'autorizzazione del soggetto è compatibile con la classificazione dell'oggetto. È il modello degli ambienti militari e governativi (multilevel security), più rigido ma molto più robusto.

I sistemi operativi moderni adottano un approccio **ibrido**: il DAC resta la modalità operativa quotidiana, con sopra uno strato MAC per contenere i danni in caso di compromissione. Su Linux questo strato è **SELinux** (nato in NSA e integrato nel kernel dal 2003) o **AppArmor**, che confinano i processi entro profili predefiniti indipendentemente dai permessi discrezionali: così, anche se un servizio esposto viene bucato, il MAC ne limita ciò che può toccare. Su Windows, da Vista in poi, il **Mandatory Integrity Control** assegna a processi e oggetti livelli di integrità (basso, medio, alto, sistema), impedendo a un processo di livello inferiore di modificare oggetti di livello superiore. In entrambi i casi il MAC agisce come rete di sicurezza *sopra* il DAC, coniugando la comodità del modello discrezionale con la garanzia di un controllo non aggirabile dall'utente.

---

## Domanda 3 — TCP attacks

> **Riferimenti di teoria**:
>
> - [M4/UD2/L5 — Attacchi TCP – SYN flooding](../../M4/UD2/L5%20-%20Attacchi%20TCP%20%E2%80%93%20SYN%20flooding.md) (meccanismo, SYN cookies, contromisure)
> - [M4/UD5/L1 — TCP Session Hijacking e Reset Attack](../../M4/UD5_Approfondimenti_Esame/L1%20-%20TCP%20Session%20Hijacking%20e%20Reset%20Attack.md) (§3 reset attack, calcolo del SEQ)
> - [M4/UD2/L3 — IP Spoofing](../../M4/UD2/L3%20-%20IP%20Spoofing.md) · [M4/UD2/L4 — ACK Storm](../../M4/UD2/L4%20-%20Attacchi%20TCP%20%E2%80%93%20ACK%20Storm.md)

**(a) SYN flood e contromisure.** L'attacco SYN flood è un Denial of Service che sfrutta l'**asimmetria di costo** nell'apertura di una connessione TCP. Quando un server riceve un SYN, avvia il three-way handshake allocando un blocco di stato (il TCB, dell'ordine di centinaia di byte) e rispondendo con SYN/ACK; poi resta in stato SYN-RECEIVED in attesa dell'ACK finale. L'attaccante invia una raffica di SYN con indirizzo sorgente **spoofato**: il server risponde a indirizzi inesistenti, l'ACK finale non arriva mai e le connessioni half-open restano appese fino al timeout. Bastano pochi byte per SYN (circa 40) per saturare la *backlog queue* — spesso piccola (128 voci) con timeout di centinaia di secondi — e da quel momento il server rifiuta le connessioni legittime. È stato l'attacco che nel 1996 mise fuori uso i servizi di Panix.

Le contromisure lato host sono l'ampliamento della backlog, la riduzione del timeout SYN-RECEIVED, l'uso di *SYN cache* più leggere e, soprattutto, i **SYN cookies**: il server risponde al SYN **senza allocare stato**, codificando i parametri della connessione (indirizzi, porte, un contatore temporale e un segreto) dentro il numero di sequenza del SYN/ACK; solo quando arriva un ACK valido, dal quale ricostruisce e verifica il cookie, alloca il TCB completo. A queste si aggiungono difese infrastrutturali: filtraggio anti-spoofing sui router (BCP38) e servizi di scrubbing/reverse proxy che terminano i SYN e inoltrano all'origine solo il traffico già stabilito.

**(b) Reset attack sul pacchetto dato.** Nella cattura fornita il server `10.0.2.69` (porta 23, Telnet) invia al client `10.0.2.68` (porta 45634) un segmento con `SEQ = 2737422009`, *Next SEQ* `2737422033` (quindi 24 byte di dati) e `ACK = 718532383`. Per abbattere la connessione con un RST occorre fabbricare un segmento che, impersonando un estremo, porti nel campo sequenza il valore che il **bersaglio si aspetta di ricevere** (il suo `rcv_nxt`), con il flag RST attivo; le implementazioni conformi alla RFC 5961 accettano il reset solo se il SEQ è **esattamente** quello atteso.

Per resettare il **server** `10.0.2.69` si spoofa un segmento proveniente dal client. Il server ha ackato `718532383`, quindi attende dal client proprio il byte `718532383`: questo è il SEQ da usare.

```
IP:  src = 10.0.2.68 (spoofed)   dst = 10.0.2.69
TCP: src port = 45634             dst port = 23
     SEQ   = 718532383            (= rcv_nxt del server = ACK che il server aveva inviato)
     Flags = RST
```

In alternativa, per resettare il **client** `10.0.2.68`, si spoofa un segmento dal server con `SEQ = 2737422033` (il *Next SEQ* del server, cioè il prossimo byte che il client attende), porta sorgente 23 e destinazione 45634, flag RST. In entrambi i casi l'estremo colpito riceve un RST perfettamente in sequenza e proveniente in apparenza dal proprio interlocutore, e chiude la connessione. La regola generale è che **il SEQ del pacchetto spoofato coincide sempre con il `rcv_nxt` del destinatario**. La difesa strutturale è la cifratura autenticata (SSH al posto di Telnet), che rende inaccettabili i segmenti falsi e nasconde i numeri di sequenza allo sniffer.

---

## Domanda 4 — Malware

> **Riferimenti di teoria**:
>
> - [M2/UD2/L1 — Tipi di malware](../../M2/UD2/L1%20-%20Tipi%20di%20malware.md)
> - [M2/UD2/L2 — Tipi di virus e propagazione](../../M2/UD2/L2%20-%20Tipi%20di%20virus%20e%20propagazione.md) (§8 tecniche di camuffamento)
> - [M2/UD2/L3 — Worm](../../M2/UD2/L3%20-%20Worm.md) (Morris, SQL Slammer, Conficker)

**(a) Virus contro worm.** Entrambi sono programmi capaci di autoreplicarsi, ma differiscono per **autonomia di diffusione**. Un **virus** è un frammento di codice parassita: si inserisce dentro un file ospite (un eseguibile, il boot sector, una macro di documento) e non può propagarsi da solo, ma ha bisogno di un'**azione dell'utente** — aprire un allegato, avviare un programma infetto, condividere una chiavetta — perché il codice ospite venga eseguito e il virus si attivi. Esempi tipici sono **Brain** (1986, boot sector) e **Melissa** (1999, macro virus di Word che si autoinviava ai primi 50 contatti di Outlook). Un **worm**, invece, è un programma **autonomo**: sfrutta vulnerabilità software nei servizi di rete per replicarsi e diffondersi da una macchina all'altra **senza alcun intervento umano**, usando ogni host infetto come rampa di lancio per i successivi. Il caso storico è il **Morris Worm** (1988), che combinava buffer overflow in `fingerd`, la modalità debug di Sendmail e attacchi a dizionario su `rsh`; esempi più recenti sono **SQL Slammer** (2003, 75.000 host in meno di 10 minuti) e **Conficker** (2008-2009, milioni di macchine). In sintesi, il virus richiede un ospite e un'azione, il worm è autosufficiente e sfrutta la rete.

**(b) Polimorfismo e metamorfismo.** Sono le due tecniche con cui i virus eludono il rilevamento basato su **firme** (pattern binari fissi). Un virus **polimorfico** mantiene il proprio corpo invariato ma lo **cifra con una chiave diversa a ogni infezione**, cambiando così l'aspetto binario di copia in copia; ciascuna copia porta con sé un piccolo *motore di decifratura* che, all'esecuzione, ricostruisce e lancia il codice originale. Poiché il corpo cifrato appare ogni volta differente, una firma statica non lo riconosce; l'antivirus deve piuttosto individuare il decryptor o attendere che il codice si decifri in memoria (emulazione in sandbox). Un virus **metamorfico** va oltre e **riscrive integralmente il proprio codice** a ogni replica, senza cifratura: reintroduce le stesse funzionalità con istruzioni diverse, inserendo codice inutile (*junk*), riordinando i blocchi, sostituendo istruzioni con equivalenti e permutando i registri. Il risultato è funzionalmente identico ma strutturalmente irriconoscibile, e non esiste nemmeno un decryptor costante da intercettare, il che rende il metamorfismo assai più difficile da contrastare del polimorfismo.

---

## Domanda 5 — Firewall e NIDS

> **Riferimenti di teoria**:
>
> - [M5/UD2/L2 — New Generation Packet Filtering](../../M5/UD2/L2%20-%20New%20Generation%20Packet%20Filtering.md) (§7 DPI, §9 moduli IPS/GAV/AIC/DLP)
> - [M5/UD2/L3 — Proxy](../../M5/UD2/L3%20-%20Proxy.md) (§7 application-level gateway, §12 circuit-level gateway)
> - [M5/UD1/L2 — Introduzione ai Firewall](../../M5/UD1/L2%20-%20Introduzione%20ai%20Firewall.md) (§6 livelli ISO/OSI)

**(1) Deep Packet Inspection e funzioni addizionali.** La *Deep Packet Inspection* è la capacità di un firewall di esaminare non solo le intestazioni dei pacchetti (indirizzi, porte, flag), come fanno il packet filtering statico e lo stateful filtering, ma anche il **contenuto del payload** a livello applicativo (Layer 7). Il firewall ricostruisce le sessioni e cerca nel loro contenuto i **pattern tipici** di worm, malware, exploit o comandi vietati, secondo una logica simile a quella delle firme antivirus, affiancata da analisi comportamentale. È l'evoluzione più recente dello stateful filtering e, poiché ispezionare ogni byte in tempo reale è costoso, viene implementata solo nei firewall di fascia alta con hardware dedicato. Proprio perché "capisce" il traffico, un firewall DPI integra generalmente funzioni aggiuntive che ne fanno un *centro di ispezione* unificato: un **IPS** (Intrusion Prevention System) che blocca exploit e intrusioni note, un **Gateway AntiVirus (GAV)** che analizza il traffico alla ricerca di malware, moduli di **Application Inspection & Control** che identificano e regolano le applicazioni, e sistemi di **Data Loss Prevention (DLP)** che impediscono la fuoriuscita di dati sensibili; spesso vi si aggiunge l'ispezione del traffico cifrato TLS, altrimenti cieco.

**(2) Application-level gateway contro circuit-level gateway.** Entrambi sono **proxy firewall** — mediano le connessioni interponendosi tra client e server, spezzando la comunicazione in due sessioni distinte — ma operano a livelli diversi e con profondità di analisi diverse. L'**application-level gateway (ALG)** lavora al **livello applicativo (Layer 7)** e dispone di un modulo proxy dedicato per ciascun protocollo (HTTP, FTP, SMTP…): interpreta i **comandi applicativi** e ne ispeziona il contenuto, potendo così autenticare gli utenti, mascherare gli indirizzi interni, filtrare comandi pericolosi e proteggere da vulnerabilità applicative. Offre la protezione più fine, al prezzo di prestazioni inferiori e della necessità di un proxy per ogni nuovo protocollo. Il **circuit-level gateway** opera invece a un livello più basso (Layer 4-5): stabilisce e autorizza il **circuito virtuale** tra client e server — verificando chi apre la connessione — ma **non interpreta i dati** che vi transitano, limitandosi a inoltrarli. È più veloce e generico (l'esempio canonico è **SOCKS**), ma "non ascolta la conversazione": isola gli host dagli attacchi sull'handshake TCP e può autenticare i client, senza però offrire alcun controllo sul contenuto applicativo. In breve, l'ALG è un proxy consapevole del linguaggio delle applicazioni, il circuit-level gateway è un centralino che verifica chi chiama ma non cosa si dice.
