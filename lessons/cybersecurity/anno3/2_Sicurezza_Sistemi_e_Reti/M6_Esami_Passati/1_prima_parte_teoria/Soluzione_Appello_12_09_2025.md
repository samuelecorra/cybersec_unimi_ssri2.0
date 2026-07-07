# Soluzione Appello di Sicurezza – SSRI — Teoria — 12/09/2025

**Docenti: S. Cimato – M. Anisetti**

Per ogni domanda sono indicati i riferimenti alle lezioni di teoria, l'inquadramento e lo svolgimento discorsivo completo.

---

## Domanda 1 — Set-UID Privileged Programs

> **Riferimenti di teoria**:
>
> - [M3/UD4/L1 — Set-UID, RUID-EUID e attacchi via variabili d'ambiente](../../M3/UD4_Approfondimenti_Esame/L1%20-%20Set-UID,%20RUID-EUID%20e%20attacchi%20via%20variabili%20d'ambiente.md) (§5 output getuid/geteuid prima e dopo chmod 4755)
> - [M3/UD3/L2 — Controllo degli accessi in Linux](../../M3/UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md) (§6-7)

**(a) Differenza RUID/EUID e utilizzo del bit setuid.** Il *Real User ID* identifica l'utente che ha **lanciato** il processo e non cambia durante l'esecuzione (dice *chi* usa il programma); l'*Effective User ID* è l'identità con cui il kernel esegue i **controlli di accesso** (dice *cosa* il processo può fare). Di norma coincidono. Il **bit setuid**, se attivo su un eseguibile, li disaccoppia: alla `exec()` l'EUID assume lo **UID del proprietario del file**, mentre il RUID resta quello del lanciatore. Il programma gira così con i privilegi del proprietario per la durata dell'esecuzione — è il meccanismo che consente a `passwd` (di root e setuid) di aggiornare `/etc/shadow` anche se invocato da un utente comune. Solo se il proprietario è root il processo diventa realmente privilegiato.

**(b) Completamento degli ID.** La sequenza dell'esercizio è la seguente. Dopo la compilazione (fatta dall'utente 1000), il comando `sudo chown root.root uid` rende il file di proprietà di root, **ma senza attivare il bit setuid** (i permessi restano `-rwxr-xr-x`). Alla prima esecuzione, quindi, il proprietario del file è irrilevante e il processo eredita l'identità del lanciatore (utente 1000):

```
5.  ./uid
6.  The real user ID is 1000
7.  The effective user ID is 1000
```

Poi `sudo chmod 4755 uid` attiva il bit setuid (i permessi diventano `-rwsr-xr-x`, con la `s` sul proprietario). Ora, essendo il file setuid **root**, alla seconda esecuzione il RUID resta 1000 (è sempre l'utente 1000 a lanciare) ma l'EUID diventa **0**:

```
11. ./uid
12. The real user ID is 1000
13. The effective user ID is 0
```

In tabella:

| Fase | Permessi | RUID (riga 6/12) | EUID (riga 7/13) |
|---|---|---|---|
| Dopo `chown root.root` | `-rwxr-xr-x` | 1000 | 1000 |
| Dopo `chmod 4755` | `-rwsr-xr-x` | 1000 | **0** |

Il punto della domanda è che il RUID racconta sempre chi lancia (1000), mentre l'EUID passa da 1000 a 0 **solo** dopo l'attivazione del setuid, perché il file è di proprietà di root.

---

## Domanda 2 — Attacks: Shellshock

> **Riferimenti di teoria**:
>
> - [M3/UD4/L2 — Shellshock](../../M3/UD4_Approfondimenti_Esame/L2%20-%20Shellshock.md) (parsing delle funzioni via ambiente, vettori CGI/DHCP/setuid)
> - [M3/UD4/L1 — Set-UID e variabili d'ambiente](../../M3/UD4_Approfondimenti_Esame/L1%20-%20Set-UID,%20RUID-EUID%20e%20attacchi%20via%20variabili%20d'ambiente.md) (ambiente come input non fidato)

Shellshock (CVE-2014-6271, settembre 2014) è una vulnerabilità di **bash** legata al modo in cui la shell importa **funzioni esportate tramite variabili di ambiente**. Bash consente di esportare funzioni verso i processi figli codificandole in variabili il cui valore inizia con la sintassi `() { … }`: alla partenza di una nuova bash, tali variabili vengono riconosciute e trasformate in funzioni. Il difetto è che il parser **non si fermava alla chiusura della graffa**: qualunque comando scritto *dopo* la definizione veniva eseguito immediatamente all'avvio della shell. La prova classica è:

```bash
env x='() { :; }; echo VULNERABILE' bash -c "echo test"
```

Su un sistema vulnerabile compare `VULNERABILE` (eseguito al solo avvio di bash) seguito da `test`. La gravità nasce dal fatto che in moltissimi contesti **input esterno finisce in una variabile di ambiente passata a bash**. Il vettore più clamoroso è quello dei **CGI su web server**: con `mod_cgi`, Apache copia le intestazioni HTTP in variabili di ambiente (`User-Agent` → `HTTP_USER_AGENT`) prima di invocare lo script CGI; se questo è o richiama bash, un attaccante remoto e non autenticato inietta codice con una semplice intestazione:

```bash
curl -H "User-Agent: () { :; }; /bin/cat /etc/passwd" http://vittima/cgi-bin/status
```

Altri vettori sono i **client DHCP** (un server malevolo mette payload in campi che gli hook, eseguiti come root, passano in ambiente), le sessioni **SSH con comando forzato** (variabile `SSH_ORIGINAL_COMMAND`) e i **programmi setuid** che invocano bash ereditando l'ambiente dell'attaccante, con conseguente *privilege escalation* locale. Il codice difettoso era presente in bash dal 1989 — circa 25 anni di esposizione — e la combinazione remoto + non autenticato + esecuzione di codice la rese immediatamente *wormable*, colpendo milioni di server. La contromisura primaria è la **patch di bash** (le funzioni si importano solo da variabili con nome speciale `BASH_FUNC_…%%` e il parser non esegue più il codice in coda); quelle strutturali sono non usare bash per i CGI, ripulire e minimizzare l'ambiente prima di invocare interpreti e applicare il privilegio minimo. A differenza di Heartbleed, che *leggeva* memoria, Shellshock *esegue* comandi: è per questo ancora più pericolosa.

---

## Domanda 3 — Network scanning (riconoscere lo scan e completare il caso mancante)

> **Riferimenti di teoria**:
>
> - [M4/UD3/L3 — Tecniche di scansione stealth](../../M4/UD3/L3%20-%20Tecniche%20di%20scansione%20stealth.md) (§8 Idle / zombie scan)
> - [M4/UD3/L1 — Tipi e Approcci di Scansione](../../M4/UD3/L1%20-%20Tipi%20e%20Approcci%20di%20Scansione.md) · [M4/UD3/L2 — Tecniche di scansione](../../M4/UD3/L2%20-%20Tecniche%20di%20scansione.md)

**Tipo di scan.** La figura mostra un **Idle Scan** (o *zombie scan*), la tecnica di scansione più anonima, in cui l'attaccante non contatta mai direttamente il bersaglio ma si serve di un host terzo inconsapevole, lo **zombie**. Il meccanismo sfrutta il campo **IP ID (IPID)** dell'header IP: su molti sistemi datati questo contatore viene **incrementato di uno per ogni pacchetto** inviato dall'host, ed è quindi prevedibile. Perché l'attacco funzioni lo zombie deve essere realmente *idle* (non generare altro traffico che alteri l'IPID) e avere un IPID incrementale prevedibile.

**Lettura della figura (caso porta APERTA).** Nel *first stage* l'attaccante interroga lo zombie con un pacchetto (SYN/ACK) e ne osserva la risposta RST, leggendo il valore corrente `IPID = 31337`. Invia poi al **target** un SYN con **indirizzo sorgente spoofato pari a quello dello zombie**. Poiché la porta è **aperta**, il target risponde con un **SYN/ACK diretto allo zombie**; lo zombie, che non ha aperto alcuna connessione, reagisce inviando al target un **RST** e **incrementa** il proprio IPID. Nel *second stage* l'attaccante interroga di nuovo lo zombie e legge `IPID = 31338`: l'incremento aggiuntivo rivela che, nel frattempo, lo zombie ha emesso un pacchetto verso il target, cioè che il target gli ha inviato un SYN/ACK — segno inequivocabile che **la porta è aperta**.

**Caso mancante da aggiungere (porta CHIUSA).** Se la porta del target fosse **chiusa**, alla ricezione del SYN spoofato il target risponderebbe allo zombie con un **RST** invece che con un SYN/ACK. Ma un RST **non provoca alcuna risposta** da parte dello zombie, che quindi **non incrementa** il proprio IPID per questo motivo. Quando l'attaccante re-interroga lo zombie, ne trova l'IPID avanzato **solo di uno** rispetto alla misura precedente — l'incremento dovuto unicamente al proprio pacchetto di probe. In sintesi:

- **incremento dell'IPID pari a 2** tra le due misure → il target ha emesso un pacchetto verso lo zombie → **porta aperta**;
- **incremento pari a 1** (nessuna variazione oltre al probe dell'attaccante) → **porta chiusa (o filtrata)**.

Il grande vantaggio dell'idle scan è l'**anonimato**: dal punto di vista del target la scansione sembra provenire dallo zombie, non dall'attaccante. I limiti sono che serve uno zombie realmente inattivo con IPID prevedibile — condizione sempre più rara, dato che i sistemi moderni usano IPID casuali o per-flusso — e che gli IDS possono correlare i pattern e rilevarlo.

---

## Domanda 4 — Attacchi: problematiche di sicurezza di ARP e ARP poisoning

> **Riferimenti di teoria**:
>
> - [M4/UD2/L2 — MAC Flooding e ARP Spoofing](../../M4/UD2/L2%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md) (§3-7 ARP, poisoning, difese)
> - [M4/UD1/L3 — MAC Flooding e ARP Spoofing](../../M4/UD1/L3%20-%20MAC%20Flooding%20e%20ARP%20Spoofing.md)
> - [M4/UD2/L1 — Vulnerabilità di TCP/IP](../../M4/UD2/L1%20-%20Vulnerabilit%C3%A0%20di%20TCP%20IP.md) (§5 ARP privo di autenticazione)

**Problematiche di sicurezza del protocollo ARP.** ARP traduce indirizzi IP in indirizzi MAC nella LAN, ma è stato progettato in un contesto di piena fiducia tra nodi e per questo è **intrinsecamente insicuro**. I difetti fondamentali sono due. Primo, **assenza totale di autenticazione**: nulla nel protocollo permette di verificare che chi invia una ARP reply sia davvero il titolare dell'indirizzo IP annunciato, per cui qualunque host può affermare di possedere qualsiasi IP. Secondo, la natura **stateless**: ogni host memorizza nella propria *ARP cache* le associazioni IP-MAC ricevute **anche senza averle richieste** (le cosiddette *gratuitous ARP*), e una risposta successiva sovrascrive semplicemente la precedente. A ciò si aggiunge il fatto che le ARP request viaggiano in broadcast, rivelando la struttura della rete. La conseguenza è che le tabelle ARP degli host sono **facilmente manipolabili** da chiunque si trovi sulla stessa LAN.

**ARP poisoning.** L'ARP poisoning (o ARP spoofing) sfrutta esattamente queste debolezze: l'attaccante invia risposte ARP falsificate che associano il **proprio MAC** all'indirizzo IP di un altro host — tipicamente il gateway — inducendo le vittime ad aggiornare la cache con l'informazione fasulla. Da quel momento il traffico che le vittime destinano a quell'IP viene consegnato all'attaccante. Avvelenando **entrambe le direzioni** (dicendo alla vittima che il gateway è al proprio MAC, e al gateway che la vittima è al proprio MAC), l'attaccante si colloca come **man-in-the-middle** sull'intero flusso vittima↔Internet. Abilitando l'IP forwarding può inoltrare in modo trasparente il traffico verso la destinazione reale (così la vittima non nota interruzioni) mentre lo **intercetta** (sniffing), lo **modifica** o lo **blocca** (DoS); l'ARP poisoning è anche il presupposto abituale di attacchi successivi come il *session hijacking*. Il poisoning va ripetuto periodicamente perché le voci ARP scadono. Le contromisure sono voci ARP **statiche** per gli host critici, **DHCP Snooping** con **Dynamic ARP Inspection** sugli switch, monitoraggio con **arpwatch** e **port security**; sul piano applicativo, l'uso di HTTPS/SSH limita i danni anche in caso di intercettazione riuscita.

---

## Domanda 5 — Firewall e NIDS

> **Riferimenti di teoria**:
>
> - [M5/UD1/L2 — Introduzione ai Firewall](../../M5/UD1/L2%20-%20Introduzione%20ai%20Firewall.md) (§4 principi di Cheswick-Bellovin, §9 default deny)
> - [M5/UD3/L2 — Network IDS (NIDS)](../../M5/UD3/L2%20-%20Network%20IDS%20%28NIDS%29.md) (§10 Honey Pot e Honey Net)
> - [M5/UD3/L1 — Intrusion Detection System (IDS)](../../M5/UD3/L1%20-%20Intrusion%20Detection%20System%20%28IDS%29.md)

**(a) Principi inderogabili dei firewall.** I tre principi fondanti, dovuti a **Cheswick e Bellovin**, restano i pilastri della progettazione di qualsiasi firewall. Il primo è che **il firewall deve essere l'unico punto di contatto** tra la rete interna e l'esterno: tutto il traffico, in entrata e in uscita, deve obbligatoriamente attraversarlo, perché una sola via alternativa che lo aggiri vanifica ogni controllo. Il secondo è che **solo il traffico esplicitamente autorizzato può attraversarlo**: tutto ciò che non è espressamente permesso deve essere bloccato. Questo si traduce operativamente nella politica di *default deny* (nega tutto tranne ciò che è consentito), coerente con il principio del privilegio minimo, preferita al più rischioso *default allow*. Il terzo è che **il firewall deve essere sicuro in sé stesso**: essendo il punto di passaggio obbligato, se il dispositivo è vulnerabile o compromesso cade l'intera rete che protegge, per cui va irrobustito, mantenuto aggiornato con le patch e ridotto ai soli servizi indispensabili. Vale infine ricordare che il firewall, per quanto ben configurato, non è sufficiente da solo: non ferma gli attacchi interni, le minacce cifrate senza DPI, gli errori di configurazione o i DDoS, e va quindi integrato in una difesa multilivello.

**(b) Come funziona una honeypot, a cosa serve e come realizzarla.** Una **honeypot** è un sistema (o un servizio) **deliberatamente vulnerabile e privo di qualunque funzione produttiva legittima**, messo in rete con il solo scopo di attirare gli attaccanti. Proprio perché non ha utenti né usi leciti, vale un principio potente: **qualunque interazione con essa è per definizione sospetta**, e questo abbatte drasticamente i falsi positivi rispetto a un IDS che deve distinguere il traffico malevolo da quello normale. Serve a diversi scopi: **rilevare precocemente** scansioni e attacchi, **studiare le tecniche** e gli strumenti degli aggressori (raccogliendo comandi, exploit e campioni di malware), **distrarre** l'attaccante dai sistemi reali e **alimentare gli IDS** con firme aggiornate ricavate dagli attacchi osservati. Si distinguono honeypot a **bassa interazione**, che emulano solo alcuni servizi (più sicure ma con dati limitati), e ad **alta interazione**, che espongono sistemi reali vulnerabili in ambiente controllato (dati molto più ricchi ma rischio maggiore); un insieme coordinato di honeypot forma una **honeynet**, e possono essere collocate sia all'esterno (per attirare attacchi da Internet) sia all'interno della LAN (per scoprire minacce interne e movimenti laterali).

Per **realizzarne una** si predispone tipicamente una macchina o VM dedicata che espone servizi apparentemente appetibili e volutamente deboli — ad esempio un finto SSH, un server web o un database con credenziali facili — configurata con **logging estensivo** di ogni connessione, comando e file caricato. Il requisito critico è l'**isolamento**: la honeypot va confinata in una sottorete separata (es. una DMZ o una rete virtuale) e con **traffico in uscita fortemente limitato**, così che, se un attaccante la compromette davvero, non possa usarla come rampa di lancio verso terzi o verso la rete interna. I dati raccolti vengono infine correlati con l'IDS/SIEM per aggiornare regole e firme. In pratica si può partire da soluzioni a bassa interazione (es. `honeyd`, che emula stack e servizi di più host virtuali) e salire verso honeynet ad alta interazione monitorate a livello di rete quando serve maggiore profondità d'analisi.
