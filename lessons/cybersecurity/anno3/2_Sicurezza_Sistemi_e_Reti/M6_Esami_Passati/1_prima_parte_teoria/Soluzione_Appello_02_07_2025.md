# Soluzione Appello di Sicurezza – SSRI — Teoria — 02/07/2025

**Docenti: S. Cimato – V. Anisetti**

Per ogni domanda sono indicati i riferimenti alle lezioni di teoria, l'inquadramento e lo svolgimento discorsivo completo.

---

## Traccia originale dell'appello

**Cognome:**  
**Nome:**  
**Matricola:**

**Sicurezza - SSRI**

**Docenti:** S. Cimato – V. Anisetti  
**Appello del 02/07/2025**

Non è ammesso alcun materiale per la consultazione. Buon lavoro!

### **1. Set-UID Privileged Programs**

a. Ogni processo Unix è associato con un real user ID (RUID) e un effective user ID (EUID). Spiegare la differenza fra RUID e EUID e l'utilizzo del bit setuid.

b. Si consideri un comando che abbia il setuid, in quali condizioni può diventare pericoloso?

### **2. Attacks**

c. Descrivere le problematiche di sicurezza del protocollo SSL.

### **3. TCP attacks**

Descrivere in dettaglio in cosa consiste il ARP spoofing attack ed elencare i passi necessari per un attacco tra le seguenti macchine in rete:

```text
Victim: Windows 10 machine (192.168.1.130)
Attacker: Kali Linux machine (192.168.1.111)
Router: Default gateway (192.168.1.1)
```

### **4. Politiche di sicurezza**

a. Definire la differenza fra l'utilizzo delle politiche di sicurezza basate su DAC e MAC.

### **5. Firewall e NIDS**

a. Cosa si intende per deep packet inspection? Quali funzioni addizionali si trovano generalmente integrate in firewall di questo tipo?

b. Differenza tra application-level gateway e circuit-level gateway.

---

## Domanda 1 — Set-UID Privileged Programs

> **Riferimenti di teoria**:
>
> - [M3/UD4/L1 — Set-UID, RUID-EUID e attacchi via variabili d'ambiente](../../M3/UD4_Approfondimenti_Esame/L1%20-%20Set-UID,%20RUID-EUID%20e%20attacchi%20via%20variabili%20d'ambiente.md) (§2 RUID/EUID, §6-7 pericoli e attacco PATH)
> - [M3/UD3/L2 — Controllo degli accessi in Linux](../../M3/UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md) (§6-8)

**(a) Differenza RUID/EUID e utilizzo del bit setuid.** Il *Real User ID* identifica l'utente che ha **lanciato** il processo e resta invariato per tutta l'esecuzione: dice *chi* usa il programma. L'*Effective User ID* è l'identità con cui il kernel effettua i **controlli di accesso**, cioè quella che decide se un'operazione è permessa. Di norma i due coincidono; il **bit setuid**, quando attivo su un eseguibile, li disaccoppia: alla `exec()` l'EUID viene posto pari allo **UID del proprietario del file**, mentre il RUID rimane quello del lanciatore. Il programma acquista così, temporaneamente, i privilegi del proprietario. È il meccanismo che permette a un comando come `passwd` — di proprietà di root e setuid — di scrivere `/etc/shadow` pur essendo eseguito da un utente qualunque. Va sottolineato che il setuid conferisce i privilegi *del proprietario del file*: solo se il proprietario è root il processo diventa realmente privilegiato.

**(b) Quando un comando setuid diventa pericoloso.** Un binario setuid **di proprietà di root** è, in pratica, codice root eseguibile da chiunque: diventa pericoloso ogni volta che il suo comportamento può essere **piegato dall'utente** che lo lancia. Le condizioni tipiche sono quattro. La più frequente è l'**invocazione di comandi esterni tramite una shell** (`system()`, `popen()`): se il comando è indicato per nome relativo, la shell lo cerca lungo `PATH`, che è controllato dall'attaccante — è l'attacco via `PATH`, in cui un finto `ls` in una directory anteposta viene eseguito con EUID root. Ad essa si affianca, più in generale, la **fiducia nelle variabili di ambiente** (`PATH`, `IFS`, e in passato `LD_PRELOAD`/`LD_LIBRARY_PATH` per iniettare librerie malevole), che attraversano la `exec()` e restano input non fidato. La terza condizione è l'**uso di percorsi relativi** per file e comandi, che rende il programma dipendente dalla directory di lavoro e dall'ambiente. La quarta è il **passaggio di input non validato** a funzioni che lo interpretano, aprendo a command injection, format string o buffer overflow. A queste si aggiunge il **mancato rilascio dei privilegi** dopo l'operazione critica (il *confused deputy* che resta root più del necessario). In tutti i casi la radice è la stessa: il programma esegue con privilegi elevati un'azione influenzabile da chi lo lancia. Le contromisure sono usare `execve()` con percorso assoluto e ambiente ripulito invece di `system()`, reimpostare `PATH` a un valore sicuro, validare gli input e rilasciare i privilegi (`setuid(getuid())`) appena possibile, preferendo dove possibile le *capabilities* al setuid pieno.

---

## Domanda 2 — Attacks: problematiche di sicurezza di SSL

> **Riferimenti di teoria**:
>
> - [M4/UD4/L4 — Attacchi a SSL e TLS](../../M4/UD4/L4%20-%20Attacchi%20a%20SSL%20e%20TLS.md) (rollback, cifrari deboli, RNG Debian)
> - [M4/UD4/L7 — Attacchi Heartbleed e BEAST](../../M4/UD4/L7%20-%20Attacchi%20Heartbleed%20e%20BEAST.md)
> - [M4/UD4/L6 — Attacchi sui Certificati SSL TLS](../../M4/UD4/L6%20-%20Attacchi%20sui%20Certificati%20SSL%20TLS.md)

Le vulnerabilità di SSL/TLS si articolano in quattro categorie. La prima è quella dei **downgrade / rollback**: un *man-in-the-middle* altera la negoziazione per forzare una versione o una cipher suite più debole. Nel *version rollback* si spinge il client verso SSL 2.0, privo dei messaggi *Finished* che sigillano l'handshake, così che la manipolazione resti invisibile; nel *cipher suite rollback* si impone un algoritmo debole perché in SSL 2.0 le preferenze crittografiche non erano autenticate. A ciò si legano le **debolezze storiche** delle vecchie versioni: MD5 per l'autenticazione (soggetto a collisioni), la modalità *export* con chiavi di 40 bit, il padding non autenticato.

La seconda categoria riguarda le **modalità crittografiche**. **BEAST** (2011) sfrutta il riuso, in TLS 1.0-CBC, dell'ultimo blocco cifrato come IV del record successivo: rendendo l'IV prevedibile, un MITM che inietta testo controllato può indovinare byte per byte i cookie di sessione. **CRIME/BREACH** sfruttano invece la compressione, correlando la lunghezza del testo compresso al contenuto — ragione per cui TLS 1.3 ha vietato compressione e cifrari non-AEAD.

La terza categoria è quella dei **difetti di implementazione**. **Heartbleed** (2014) è il caso simbolo: un mancato controllo di lunghezza nell'estensione *Heartbeat* di OpenSSL faceva restituire fino a 64 KB di memoria del server per richiesta, esponendo password, cookie e la stessa chiave privata. Il **bug del generatore casuale di Debian** (2006-2008) è l'opposto: la perdita di entropia riduceva lo spazio delle chiavi a 32768 valori, rendendo prevedibili chiavi TLS/SSH e certificati.

La quarta categoria è la **fiducia nei certificati e nella PKI**: certificati emessi da CA compromesse o accettati senza validazione rigorosa vanificano anche un canale crittograficamente perfetto, perché il client finisce per fidarsi del certificato dell'attaccante. In sintesi, la sicurezza di SSL/TLS dipende dalla robustezza matematica, dalla correttezza dell'implementazione, dall'aggiornamento dei protocolli e dall'affidabilità del sistema di certificati: TLS 1.3 nasce proprio dalla rimozione di versioni, cifrari e funzioni divenuti insicuri.

---

## Domanda 3 — ARP spoofing attack

> **Riferimenti di teoria**:
>
> - [M4/UD2/L2 — MAC Flooding e ARP Spoofing](../../M4/UD2/L2%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md) (§6 ARP cache poisoning, §7 difese)
> - [M4/UD1/L3 — MAC Flooding e ARP Spoofing](../../M4/UD1/L3%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md)
> - [M4/UD2/L1 — Vulnerabilità di TCP/IP](../../M4/UD2/L1%20-%20Vulnerabilit%C3%A0%20di%20TCP%20IP.md) (assenza di autenticazione in ARP)

**In cosa consiste.** Il protocollo ARP traduce indirizzi IP in indirizzi MAC all'interno di una LAN. La sua debolezza è strutturale: è **privo di autenticazione** ed è **stateless**, cioè ogni host accetta e memorizza nella propria *ARP cache* qualunque risposta ARP riceva, anche se non ne ha mai fatta richiesta (*gratuitous ARP*). L'**ARP spoofing** (o *ARP cache poisoning*) sfrutta questo comportamento: l'attaccante invia risposte ARP falsificate che associano il **proprio MAC** all'indirizzo IP di un altro host — tipicamente il gateway — inducendo le vittime ad aggiornare la cache con l'informazione fasulla. Da quel momento il traffico destinato a quell'IP viene inviato all'attaccante, che si pone così come *man-in-the-middle*: può intercettarlo (sniffing), modificarlo o bloccarlo.

**Passi dell'attacco** tra vittima Windows 10 (`192.168.1.130`), attaccante Kali (`192.168.1.111`) e router/gateway (`192.168.1.1`), tutti sulla stessa LAN:

1. **Abilitare l'IP forwarding** sull'attaccante, così che il traffico intercettato venga comunque inoltrato alla destinazione reale e la vittima non si accorga dell'interruzione:
   ```bash
   echo 1 > /proc/sys/net/ipv4/ip_forward     # oppure: sysctl -w net.ipv4.ip_forward=1
   ```
2. **Ricognizione dei MAC**: individuare gli indirizzi MAC di vittima e gateway, ad esempio con `arp-scan --localnet` o pingando gli host e leggendo `arp -a`.
3. **Avvelenare la vittima**: inviare ripetutamente a `192.168.1.130` una ARP reply che dichiara *"192.168.1.1 (il gateway) si trova al MAC dell'attaccante"*. La cache della vittima associa così il gateway al MAC di Kali.
4. **Avvelenare il gateway**: inviare ripetutamente a `192.168.1.1` una ARP reply che dichiara *"192.168.1.130 (la vittima) si trova al MAC dell'attaccante"*. Questo cattura anche il traffico di ritorno, realizzando un MITM **bidirezionale**.

   Con la suite `dsniff`, i due comandi corrispondenti sono:
   ```bash
   arpspoof -i eth0 -t 192.168.1.130 192.168.1.1     # dice alla vittima: "il router sono io"
   arpspoof -i eth0 -t 192.168.1.1  192.168.1.130    # dice al router:  "la vittima sono io"
   ```
   In alternativa si usano **ettercap** o **bettercap**, che automatizzano poisoning e sniffing.
5. **Intercettazione**: tutto il traffico vittima↔Internet transita ora per Kali, dove può essere analizzato con Wireshark/`tcpdump`, alterato o usato per attacchi successivi (es. dirottamento di sessioni). Il poisoning va **ripetuto periodicamente** perché le voci ARP scadono e verrebbero altrimenti riscritte con i valori corretti.

**Contromisure.** Voci ARP **statiche** per gli host critici (il gateway anzitutto), **DHCP Snooping** con **Dynamic ARP Inspection** sugli switch (che scarta le ARP reply incoerenti con le associazioni IP-MAC legittime), strumenti di monitoraggio come **arpwatch** (che segnalano cambi sospetti nella mappatura IP-MAC) e **port security**. Sul piano applicativo, l'uso di protocolli cifrati e autenticati (HTTPS, SSH) limita i danni anche quando l'attaccante riesce a intercettare il traffico.

---

## Domanda 4 — Politiche di sicurezza: DAC contro MAC

> **Riferimenti di teoria**:
>
> - [M3/UD2/L3 — Politiche di controllo degli accessi – DAC](../../M3/UD2/L3%20-%20Politiche%20di%20controllo%20degli%20accessi%20%E2%80%93%20DAC.md)
> - [M3/UD2/L2 — Politiche di controllo degli accessi – MAC](../../M3/UD2/L2%20-%20Politiche%20di%20controllo%20degli%20accessi%20%E2%80%93%20MAC.md)
> - [M3/UD3/L2 — Controllo degli accessi in Linux](../../M3/UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md) · [M3/UD3/L1 — Windows](../../M3/UD3/L1%20-%20Controllo%20degli%20accessi%20in%20Windows.md)

La distinzione fondamentale tra le due politiche riguarda **chi ha l'autorità di stabilire i permessi**. Nel **DAC (Discretionary Access Control)** l'autorità è del **proprietario** della risorsa, che a propria discrezione può concedere ad altri i diritti di accesso. È il modello dei permessi UNIX (`rwx` per owner, group, other) e delle DACL di Windows: comodo, flessibile e adatto alla condivisione, ma vulnerabile alla propagazione incontrollata dei privilegi, perché chi possiede una risorsa — o un malware che agisce sotto la sua identità — può ridistribuirne liberamente l'accesso. Nel **MAC (Mandatory Access Control)** l'autorità è del **sistema**: le decisioni si basano su **etichette di sicurezza** associate a soggetti e oggetti secondo regole centrali che nemmeno il proprietario può modificare. L'accesso è concesso solo quando l'autorizzazione del soggetto è compatibile con la classificazione dell'oggetto (ad esempio, un utente "Segreto" legge dati "Riservati" ma non "Top Secret"). Il MAC garantisce sicurezza molto più elevata — è pensato per ambienti militari, governativi e finanziari — al prezzo di un'amministrazione onerosa e di scarsa flessibilità.

Nella pratica i sistemi operativi moderni **combinano** i due modelli: il DAC è la modalità d'uso quotidiana, mentre uno strato MAC funge da rete di sicurezza per contenere le compromissioni. Su Linux ciò si concretizza in **SELinux** (NSA, nel kernel dal 2003) e **AppArmor**, che confinano i processi entro profili predefiniti a prescindere dai permessi discrezionali; su Windows, dal Vista in poi, nel **Mandatory Integrity Control**, che assegna livelli di integrità e impedisce ai processi di livello inferiore di modificare oggetti di livello superiore. Così, anche se un servizio esposto viene compromesso, il MAC ne limita il raggio d'azione, mentre il DAC continua a garantire la flessibilità richiesta dall'uso normale.

---

## Domanda 5 — Firewall e NIDS

> **Riferimenti di teoria**:
>
> - [M5/UD2/L2 — New Generation Packet Filtering](../../M5/UD2/L2%20-%20New%20Generation%20Packet%20Filtering.md) (§7 DPI, §9 moduli integrati)
> - [M5/UD2/L3 — Proxy](../../M5/UD2/L3%20-%20Proxy.md) (§7 ALG, §12 circuit-level gateway)
> - [M5/UD1/L2 — Introduzione ai Firewall](../../M5/UD1/L2%20-%20Introduzione%20ai%20Firewall.md) (§6 livelli ISO/OSI)

**(a) Deep Packet Inspection e funzioni addizionali.** La *Deep Packet Inspection* è la capacità di un firewall di ispezionare non solo le intestazioni dei pacchetti (indirizzi, porte, flag) — come il packet filtering statico e lo stateful filtering — ma anche il **contenuto del payload** a livello applicativo. Il firewall ricostruisce le sessioni e vi cerca i **pattern** tipici di worm, malware, exploit o comandi vietati, con una logica affine alle firme antivirus integrata da analisi comportamentale. Poiché esaminare ogni byte in tempo reale è oneroso, la DPI è implementata solo nei firewall di fascia alta con hardware dedicato. Proprio perché "comprende" il traffico, un firewall DPI integra tipicamente funzioni aggiuntive che ne fanno un centro di ispezione unificato: un **IPS** che blocca exploit e intrusioni note, un **Gateway AntiVirus** che analizza il traffico alla ricerca di malware, moduli di **Application Inspection & Control** che identificano e regolano le applicazioni, sistemi di **Data Loss Prevention** che impediscono la fuoriuscita di dati sensibili, e spesso l'ispezione del traffico TLS altrimenti cieco.

**(b) Application-level gateway contro circuit-level gateway.** Entrambi sono **proxy firewall** che spezzano la comunicazione in due sessioni distinte, ma differiscono per livello e profondità di analisi. L'**application-level gateway (ALG)** opera al **Layer 7** con un modulo proxy dedicato per ogni protocollo (HTTP, FTP, SMTP): interpreta i comandi applicativi e ne ispeziona il contenuto, potendo autenticare gli utenti, mascherare gli indirizzi interni, filtrare comandi pericolosi e proteggere da vulnerabilità applicative; offre la protezione più fine ma con prestazioni inferiori e la necessità di un proxy per ogni nuovo protocollo. Il **circuit-level gateway** opera invece al **Layer 4-5**: crea e autorizza il **circuito virtuale** tra client e server (verificando chi apre la connessione) ma **non interpreta i dati** che vi transitano, limitandosi a inoltrarli — l'esempio classico è **SOCKS**. È più veloce e generico, isola gli host dagli attacchi sull'handshake e può autenticare i client, ma non offre alcun controllo sul contenuto. In sintesi, l'ALG è un proxy consapevole del linguaggio delle applicazioni, mentre il circuit-level gateway è un centralino che controlla chi chiama senza ascoltare la conversazione.
