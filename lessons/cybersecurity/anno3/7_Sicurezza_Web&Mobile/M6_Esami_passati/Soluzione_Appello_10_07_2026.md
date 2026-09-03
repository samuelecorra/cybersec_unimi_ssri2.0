# Soluzione Appello — Sicurezza dei Sistemi Web e Mobili — 10/07/2026

**Docente: Chiara Braghin** · A.A. 2025/26 · Tempo a disposizione: **1:30 h** · 7 domande

> 📌 Consegna del docente: *"Rispondere brevemente ma in modo completo alle seguenti domande."* Budget: **~12 minuti a domanda**.

> ⚠️ **Rapporto con l'appello del 3 luglio 2026.** Le due tracce sono **identiche tranne la domanda 4**: qui si chiede il **DAC di Unix/Linux e i suoi limiti**, là i permessi `r`/`w`/`x` e le **ACL**. Le altre sei domande sono le stesse, parola per parola.
> Questo file è **completo e autonomo**, ma le sei domande condivise sono qui svolte in forma **più densa e schematica**, pensata come **secondo passaggio di ripasso**: la [soluzione del 3 luglio](Soluzione_Appello_03_07_2026.md) ne contiene la trattazione estesa e discorsiva. La **domanda 4** è invece sviluppata qui integralmente, perché è l'unica specifica di questo appello.
> Suggerimento di studio: leggere prima la versione estesa del 3 luglio, poi rileggere queste come verifica di ciò che si ricorda.

---

## Traccia originale dell'appello

### **1. Kerberos: ruolo di AS e TGS, e perché è un sistema di SSO**

Nell'ambito del sistema Kerberos, descrivere il ruolo dell'Authentication Server (AS) e del Ticket Granting Server (TGS). Spiegare inoltre perché Kerberos costituisce un esempio di sistema di Single Sign-On (SSO).

> **Riferimenti di teoria**:
>
> - [M4/UD6/L2 — Kerberos](../M4/UD6/L2.md) (§5 attori, §7–10 le tre fasi, §11–13 ticket e TGT, §14 chiavi, §15 la password non transita, §16 formalizzazione, §17 SSO centralizzato, §18 cross-realm, §20 svantaggi)
> - [M4/UD6/L1 — Single Sign-On](../M4/UD6/L1.md) (§5 definizione, §7 dominio primario/secondario, §8 token, §10 IdP/SP, §12 SSO centralizzato)
> - [M3/UD3/L5 — Needham-Schroeder simmetrico](../M3/UD3/L5.md) (protocollo di origine; Denning-Sacco e correzione con timestamp)
> - Trattazione estesa: [Soluzione 03/07/2026 — domanda 1](Soluzione_Appello_03_07_2026.md)

**Contesto.** Kerberos nasce al MIT (progetto Athena), prende il nome da Cerbero, usa **crittografia a chiave simmetrica** (storicamente DES), deriva dal **Needham-Schroeder a chiave condivisa**, richiede una **terza parte fidata** e **orologi sincronizzati**, perché la freschezza è garantita da **timestamp**. Obiettivi dichiarati: autenticare l'utente, **evitare che la password transiti in rete**, non memorizzarla sulla workstation, non tenerla in chiaro nel database del server, **richiederla una sola volta per sessione di lavoro**, centralizzare l'identità, e opzionalmente fornire mutua autenticazione e chiave di sessione per la comunicazione cifrata.

**Authentication Server (AS).** È il servizio di autenticazione **della fase di login**. Condivide una **master key** (chiave a lungo termine) con ogni utente e con ogni server: per l'utente è **derivata dalla password**, per il Service Provider è imposta dall'amministratore. Al login l'utente digita username e password, ma **solo lo username viene trasmesso**. L'AS verifica la presenza dell'utente nel database e restituisce:

- un **Ticket Granting Ticket (TGT)**, dotato di scadenza;
- una **chiave di sessione** $K_{A,TGS}$, **cifrata con la master key dell'utente**.

Il client **deriva localmente** la chiave dalla password digitata e la usa per decifrare la chiave di sessione. La password **serve, ma non viene inviata**: è il vantaggio centrale di Kerberos rispetto a Telnet e FTP, che le trasmettono in chiaro.

**Ticket Granting Server (TGS).** Emette i **ticket di servizio**. Il client si presenta con **TGT e chiave di sessione**, non più con le credenziali; se la verifica ha successo il TGS rilascia un ticket per il servizio richiesto e una nuova **chiave di sessione** $K_{AB}$ per Bob. Il **ticket** è **cifrato con la chiave condivisa fra TGS e Service Provider**: nemmeno il client può leggerlo o alterarlo (per esempio per allungarne la durata). Contiene identità del client, identità del servizio, **indirizzo IP** della macchina autorizzata, **timestamp**, **lifetime** (tipicamente ~10 ore) e la **chiave di sessione**.

> ⚠️ Emesso il ticket, il TGS non può più impedirne l'uso fino alla scadenza: **la durata di vita è un parametro di sicurezza critico**.

**Insieme AS + TGS = KDC = Identity Provider.** La separazione dei ruoli è motivata: l'AS è l'unico a maneggiare il segreto **a lungo termine** (la chiave derivata dalla password), usato una sola volta per sessione; il TGS lavora solo con **ticket e chiavi a breve termine**, riducendo l'esposizione della credenziale permanente.

**Le tre fasi e la formalizzazione.** *(1)* Client ↔ AS: autenticazione con password, si ottengono TGT e $K_{A,TGS}$. *(2)* Client ↔ TGS: con il TGT si ottengono ticket di servizio e $K_{AB}$. *(3)* Client ↔ Service Provider: si presenta il ticket, Bob lo decifra, ricava $K_{AB}$, verifica la validità, eroga il servizio ed eventualmente completa la mutua autenticazione. Fondendo AS e TGS in un unico server $S$:

$$1.\ A \rightarrow S: A,\ B$$

$$2.\ S \rightarrow A: \left\{T_S,\ L,\ K_{AB},\ B,\ \left\{T_S,\ L,\ K_{AB},\ A\right\}_{K_{BS}}\right\}_{K_{AS}}$$

$$3.\ A \rightarrow B: \left\{T_S,\ L,\ K_{AB},\ A\right\}_{K_{BS}},\ \left\{A,\ T_A\right\}_{K_{AB}}$$

$$4.\ B \rightarrow A: \left\{T_A + 1\right\}_{K_{AB}}$$

dove $\{T_S, L, K_{AB}, A\}_{K_{BS}}$ è il **ticket** (illeggibile per Alice, decifrabile solo da Bob) e $\{A, T_A\}_{K_{AB}}$ è l'**autenticatore**, che funge da challenge. Il quarto messaggio è la response di Bob: incrementando il timestamp e ricifrandolo, dimostra di conoscere $K_{AB}$ — così si realizza la **mutua autenticazione**.

**Perché è SSO.** Il **Single Sign-On** consente di autenticarsi **una sola volta con un'unica coppia di credenziali** e accedere poi a più sistemi indipendenti ma collegati, tramite un **dominio primario** che autentica e **domini secondari** che si fidano di lui, con un **token** che rappresenta l'avvenuta autenticazione. Kerberos ne è la realizzazione **centralizzata**:

- unico **Identity Provider**, cioè la coppia **AS + TGS**, che concentra la gestione delle identità;
- la **password si inserisce una sola volta per sessione di lavoro**;
- da quel momento il client usa il **TGT** per chiedere ticket, mai più le credenziali — **il TGT è il meccanismo che rende possibile il SSO**;
- i Service Provider non gestiscono credenziali proprie: si fidano dei ticket emessi dall'infrastruttura centrale.

L'autenticazione **cross-realm** (un *realm* è l'insieme di client e server sotto un'unica coppia AS/TGS) avvicina il modello al **SSO federato**, ma richiede un **pre-agreement** e una chiave condivisa fra le coppie AS/TGS, perché la crittografia simmetrica esige chiavi concordate a priori.

**Svantaggi (chiusura).** Complessità implementativa; migrazione delle password non automatizzata; necessità di *kerberizzare* le applicazioni modificandone il codice; dipendenza dalla sincronizzazione degli orologi; e soprattutto, essendo simmetrico, **la compromissione del KDC compromette l'intero sistema** — comunicazioni passate, presenti e future — perché AS e TGS sono il centro della fiducia.

> ✅ **Sintesi:** l'AS autentica al login con la chiave derivata dalla password (mai trasmessa) e rilascia TGT + chiave di sessione; il TGS, su presentazione del TGT, rilascia i ticket di servizio con le relative chiavi di sessione. Insieme formano il KDC/Identity Provider. È SSO centralizzato perché l'utente si autentica **una sola volta per sessione** e accede poi a più servizi presentando ticket anziché credenziali.

---

### **2. Cross-Site Request Forgery (CSRF)**

Che cosa si intende per Cross-Site Request Forgery (CSRF)? Descrivere in che modo viene realizzato questo tipo di attacco e discutere le principali contromisure.

> **Riferimenti di teoria**:
>
> - [M4/UD4/L1 — Same Origin Policy](../M4/UD4/L1.md) (§8 cosa limita, §10 SOP e cookie, §16.2 cosa **non** protegge, §18.5 *invio* ≠ *lettura* dei cookie)
> - [M4/UD2/L2 — Cookie HTTP, sessioni e privacy](../M4/UD2/L2.md) (§5 `Set-Cookie`/`Cookie`, §8 attributi)
> - [M4/UD2/L3 — Vulnerabilità dei cookie e contromisure](../M4/UD2/L3.md) · [M4/UD2/L1 — HTTP](../M4/UD2/L1.md) (stateless, semantica di GET e POST)
> - [M4/UD4/L2 — Cross-Site Scripting](../M4/UD4/L2.md) (confronto; l'XSS annulla le difese anti-CSRF)
> - [M3/UD3/L4 — Protocolli challenge-response](../M3/UD3/L4.md) (il token anti-CSRF **è** un nonce)
> - Trattazione estesa: [Soluzione 03/07/2026 — domanda 2](Soluzione_Appello_03_07_2026.md)
>
> ⚠️ **Nota di copertura**: il CSRF non ha una lezione dedicata nel materiale digitalizzato del corso; la risposta è ricostruita a partire dai meccanismi trattati esplicitamente (sessioni via cookie, SOP, freschezza).

**Definizione.** Il **CSRF** è l'attacco in cui un sito malevolo **induce il browser di un utente autenticato a inviare a un sito terzo una richiesta che modifica lo stato**. Il server la riceve corredata del cookie di sessione corretto, la considera legittima e la esegue: non ha modo di distinguere una richiesta voluta dall'utente da una indotta da terzi.

Formula mnemonica: **l'XSS abusa della fiducia dell'utente nel sito; il CSRF abusa della fiducia del sito nel browser dell'utente.** L'XSS **legge** (cookie, DOM, dati) eseguendo codice nell'origine fidata; il CSRF **scrive**, provocando un'azione con effetti collaterali, ed è un attacco **cieco**, perché l'attaccante non vede la risposta.

**I due presupposti.**

1. **Le credenziali sono "ambientali".** HTTP è **stateless**, quindi la sessione si mantiene con un **cookie**: il browser lo allega **automaticamente a ogni richiesta diretta a quel dominio**, indipendentemente da quale pagina l'abbia originata. Lo stesso vale per HTTP Basic, certificati client e autenticazione integrata di dominio.
2. **La Same Origin Policy non impedisce di *inviare* richieste cross-origin.** La SOP limita ciò che uno script può **leggere o manipolare** attraverso i confini di origine (DOM, cookie, contenuto delle risposte); una pagina può però tranquillamente **includere risorse e inviare richieste** verso altri domini — è così che funzionano immagini da CDN, script esterni, banner, iframe. È l'errore concettuale segnalato a lezione: **invio e lettura dei cookie sono cose distinte**. All'attaccante non serve leggere: gli basta che la richiesta **parta e venga eseguita**.

**Realizzazione.** Sia `bank.example` un home banking in cui il trasferimento è esposto come `GET /transfer?to=…&amount=…`. Le fasi:

1. **Alice è autenticata** su `bank.example` e possiede un cookie di sessione valido (non serve che la finestra sia aperta: basta che non abbia fatto logout).
2. **L'attaccante prepara la trappola** su `evil.example`, in un post di forum, in una email HTML o in un banner. Se l'operazione è in `GET`, basta un tag immagine invisibile:

   ```html
   <img src="https://bank.example/transfer?to=trudy&amount=5000" width="1" height="1">
   ```

   Se richiede `POST`, un form nascosto con auto-invio:

   ```html
   <body onload="document.forms[0].submit()">
     <form action="https://bank.example/transfer" method="POST">
       <input type="hidden" name="to"     value="trudy">
       <input type="hidden" name="amount" value="5000">
     </form>
   </body>
   ```

3. **La vittima visita la pagina**, tipicamente indotta via **phishing**.
4. **Il browser invia la richiesta e allega automaticamente il cookie** di `bank.example`.
5. **Il server esegue l'operazione**: richiesta ben formata, sessione valida, bonifico effettuato.

Bersagli tipici: trasferimenti di denaro, **cambio della password o dell'email** (che porta al takeover dell'account), acquisti, cancellazione di dati, creazione di utenze amministrative, modifica delle impostazioni di un router domestico.

**Contromisure, in ordine di robustezza.**

1. **Token anti-CSRF (synchronizer token)** — la difesa di riferimento. Il server genera un valore **casuale, imprevedibile e legato alla sessione**, lo inserisce come campo `hidden` in ogni form che modifica lo stato e **lo verifica lato server**. Funziona perché **non è una credenziale ambientale**: il browser non lo allega da solo, va letto dalla pagina, e la SOP impedisce a `evil.example` di leggere il documento di `bank.example` per estrarlo. È concettualmente un **nonce**: freschezza e imprevedibilità, come nei protocolli challenge-response.
2. **Attributo `SameSite` sul cookie di sessione.** `Strict` blocca l'invio del cookie in qualunque richiesta originata da un altro sito; `Lax` (oggi default nei browser) lo consente solo nelle navigazioni di primo livello in `GET`, bloccando quindi form `POST` cross-site e richieste da `<img>`/`<iframe>`. Difesa strutturale ed efficace, ma **in profondità**: dipende dal browser e `Lax` lascia scoperte le operazioni esposte in `GET`.
3. **Verifica degli header `Origin` e `Referer`**: accettare le richieste che modificano lo stato solo se provengono dalla propria origine, adottando la scelta *fail-closed* quando l'header è assente.
4. **Non usare `GET` per operazioni con effetti.** La semantica HTTP prescrive che `GET` sia sicura e idempotente. Da sola non basta (un form auto-inviato genera una `POST`), ma elimina il vettore banale del tag `<img>` e rende applicabili `SameSite=Lax` e i controlli sui metodi.
5. **Ri-autenticazione o step-up per le operazioni critiche** (password, OTP, conferma via app): è la difesa più forte, perché reintroduce un fattore che **il browser non allega automaticamente**. È il motivo per cui l'home banking la usa sistematicamente.
6. **Igiene di sessione**: durata limitata, logout con invalidazione lato server, rigenerazione del session identifier dopo il login, `Secure` + HTTPS.

> ⚠️ **Due precisazioni decisive.**
> **(a) `HttpOnly` non protegge dal CSRF**: impedisce a `document.cookie` di *leggere* il cookie (difesa contro l'XSS), ma il CSRF non ha bisogno di leggerlo — ha bisogno che il browser lo **invii**, cosa che `HttpOnly` non impedisce.
> **(b) Un XSS annulla ogni difesa anti-CSRF**: uno script che gira **nell'origine del sito bersaglio** può leggere il token dal DOM e costruire richieste perfettamente valide. Eliminare l'XSS è una precondizione della protezione anti-CSRF.

> ✅ **Sintesi:** il CSRF sfrutta l'invio automatico del cookie di sessione e il fatto che la SOP non impedisce l'**invio** di richieste cross-origin (ma solo la **lettura** delle risposte). Si realizza con un `<img>` o un form auto-inviato su una pagina malevola recapitata via phishing. Difesa principale: **token anti-CSRF** (un nonce non ambientale verificato lato server), più `SameSite`, controllo di `Origin`/`Referer`, uso corretto dei metodi HTTP e ri-autenticazione per le operazioni critiche.

---

### **3. Firma digitale**

Si descriva il funzionamento della firma digitale. In particolare spiegare: quali proprietà di sicurezza garantisce; quali proprietà non garantisce; quale ruolo svolgono gli algoritmi di hash.

> **Riferimenti di teoria**:
>
> - [M3/UD2/L5 — Firma digitale](../M3/UD2/L5.md) (§2 requisiti, §3 perché il MAC non basta, §5–6 firma e verifica, §8 associazione chiave–identità, §9 confronto, §10 firma e confidenzialità)
> - [M3/UD2/L4 — Funzioni hash e MAC](../M3/UD2/L4.md) (§2 proprietà, §4 birthday paradox, §10 limiti del MAC, §11 famiglie MD/SHA)
> - [M3/UD2/L6 — Certificati digitali e PKI](../M3/UD2/L6.md) · [M3/UD2/L3 — Crittografia simmetrica e asimmetrica](../M3/UD2/L3.md)
> - [M1/UD2/L2 — Proprietà di sicurezza](../M1_Intro_CyberSec/UD2_CybersecNotions/L2_Cybersec_Definition.md) (§4.2 non ripudio)
> - Trattazione estesa: [Soluzione 03/07/2026 — domanda 3](Soluzione_Appello_03_07_2026.md)

**Requisiti.** La firma digitale è l'analogo della firma su carta: deve essere **facile da produrre** per il legittimo firmatario, **difficile da contraffare** anche conoscendo messaggio, algoritmo e chiave pubblica, **facile da verificare da chiunque** (altrimenti non varrebbe come prova verso terzi) e **non riutilizzabile su un altro documento**.

**Perché il MAC non basta.** Il MAC garantisce integrità e autenticazione fra chi condivide la chiave $K$, ma fallisce su due punti. **Non ripudio**: Bob, ricevendo $M, MAC_K(M)$, sa che il codice viene da qualcuno che conosce $K$ — ma **conosce $K$ anche lui**, quindi potrebbe averlo prodotto, e non può usarlo come prova verso un terzo. **Verifica pubblica**: chi non possiede $K$ non può ricalcolare il MAC. Serve una primitiva **asimmetrica**, in cui **chi verifica non possiede il segreto usato per firmare**.

**Funzionamento.** Alice possiede $K_{priv,A}$ (segreta) e $K_{pub,A}$ (pubblica). Poiché gli algoritmi asimmetrici sono lenti, **non si firma il messaggio ma il suo digest**.

*Firma:* $h = H(M)$; $s = Sign_{K_{priv,A}}(h)$; Alice invia $M,\ s$ con **$M$ in chiaro**.
*Verifica:* Bob calcola $h_1 = Verify_{K_{pub,A}}(s)$ e, indipendentemente, $h_2 = H(M)$; accetta se $h_1 = h_2$.

**Proprietà garantite.**

| Proprietà | Motivo |
|-----------|--------|
| **Integrità** | Il digest dipende dall'intero contenuto: un solo bit diverso e $h_2 \neq h_1$ |
| **Autenticazione dell'origine** | Solo chi possiede $K_{priv,A}$ produce firme verificabili con $K_{pub,A}$ |
| **Non ripudio** | Bob **non conosce** la chiave privata: non avrebbe potuto fabbricare la firma, quindi può esibirla come prova. È ciò che distingue la firma dal MAC |
| **Verifica pubblica** | Chiunque verifica con la chiave pubblica, senza conoscere alcun segreto |

**Proprietà NON garantite** — la parte che vale il punto pieno.

- **Confidenzialità**: $M$ viaggia **in chiaro**. Coerente con l'analogia cartacea: firmare non rende segreto, rende attribuibile. Per la segretezza si **combina firma e cifratura**.
- **Autenticità dell'associazione chiave pubblica ↔ identità**: l'algoritmo garantisce solo *"questa firma si verifica con questa chiave"*, non *"questa chiave è di Alice"*. Se Trudy sostituisce la chiave pubblica, Bob verifica come firma di Alice una firma di Trudy (**Man in the Middle**). Si risolve **fuori** dallo schema, con **certificati digitali** e **PKI**.
- **Freschezza / protezione dal replay**: la coppia $(M, s)$ resta valida per sempre e può essere **rigiocata**. La firma dice *chi* e *che cosa*, non *quando* e *in quale sessione*: serve inserire **nel materiale firmato** un nonce, un timestamp o un numero di sequenza.
- **La segretezza della chiave privata**: se compromessa, cade retroattivamente anche il non ripudio (da cui l'importanza della **revoca** via CRL).
- **Disponibilità e veridicità del contenuto**: la firma certifica **chi ha sottoscritto** e che il testo **non è stato alterato dopo**, non che quanto affermato sia vero.

**Ruolo degli algoritmi di hash** — tre funzioni distinte.

1. **Efficienza.** $H:\{0,1\}^* \rightarrow \{0,1\}^n$ comprime un input di lunghezza arbitraria in un digest di lunghezza fissa: l'operazione costosa con la chiave privata si applica **una sola volta a un input piccolo**.
2. **Legame con l'intero contenuto.** Per l'**effetto valanga**, una modifica di un solo bit cambia radicalmente il digest. L'alternativa ingenua — spezzare il messaggio e firmare i blocchi separatamente — sarebbe inefficiente e **insicura**, perché consentirebbe di riordinare, eliminare o riutilizzare blocchi firmati.
3. **Fondamento di sicurezza.** La robustezza della firma **si riduce** a quella dell'hash: la **preimage resistance** impedisce di risalire al messaggio dal digest; la **second preimage resistance** impedisce di **trasferire** una firma su un documento diverso; la **collision resistance** è la proprietà critica, perché chi trova $(M_1, M_2)$ con lo stesso digest può far firmare $M_1$ (innocuo) ed esibire la **stessa firma** su $M_2$ (malevolo). Per il **paradosso del compleanno** trovare collisioni costa circa $2^{n/2}$ anziché $2^n$, il che vincola la lunghezza del digest.

> ⚠️ Conseguenza: **MD5** e **SHA-1** sono rotti rispetto alla collision resistance e non vanno usati per la firma; si usa **SHA-2**. Il caso storico delle CA che firmavano certificati con MD5 è esattamente questo attacco realizzato nel mondo reale.

| Proprietà | Hash | MAC | Firma digitale |
|-----------|:----:|:---:|:--------------:|
| Integrità | Sì, solo se il digest è protetto | Sì | Sì |
| Autenticazione origine | No | Sì, fra chi condivide la chiave | Sì |
| **Non ripudio** | No | **No** | **Sì** |
| Verifica da terzi | Solo se il digest è fidato | No | Sì |
| Confidenzialità | No | No | No |
| Segreto per verificare | Nessuno | Chiave condivisa | Nessuno |
| Segreto per produrre | Nessuno | Chiave condivisa | Chiave privata |

> ✅ **Sintesi:** si firma il **digest** con la chiave privata e si verifica con la chiave pubblica. Garantisce **integrità, autenticazione dell'origine, non ripudio e verificabilità pubblica**; **non** garantisce confidenzialità, freschezza, né — da sola — l'autenticità della chiave pubblica (che richiede certificati e PKI). L'hash serve per efficienza, per legare la firma all'intero contenuto e come **fondamento di sicurezza**: preimage, second preimage e soprattutto **collision resistance**.

---

### **4. Discretionary Access Control (DAC) in Unix/Linux e suoi limiti**

Descrivere il modello di controllo degli accessi Discretionary Access Control (DAC) implementato nei sistemi Unix/Linux. Quali sono i suoi limiti?

> **Riferimenti di teoria**:
>
> - [M2/UD1/L2 — DAC, MAC e RBAC](../M2_AccessControl&Authentication/UD1/L2_DAC_MAC_RBAC.md) (§1 chi stabilisce la politica, §2 DAC e delegation of duty, §2.2 **limite del Trojan Horse**, §3 MAC come risposta, §4 RBAC, §5 confronto)
> - [M2/UD3/L2 — Controllo degli accessi in Linux](../M2_AccessControl&Authentication/UD3/L2/L2_Controllo_Accessi_Linux.md) (§1 utenti, UID/GID, §2 `/etc/passwd` e `/etc/shadow`, §3 permessi e ordine di valutazione, §4 ottale, §5 setuid/setgid/sticky, §6 comandi)
> - [M2/UD1/L3 — La matrice degli accessi](../M2_AccessControl&Authentication/UD1/L3_Matrice_Accessi.md) (§3 ACL per colonne, §4 capability list, §6 implementazione in Linux)
> - [M2/UD1/L1 — Politiche di sicurezza](../M2_AccessControl&Authentication/UD1/L1_Politiche_Sicurezza.md) (politica, meccanismo, Reference Monitor)
> - [M2/UD3/L1 — Introduzione a Linux](../M2_AccessControl&Authentication/UD3/L1/L1_Intro_Linux.md) · [M2/domande_fineM2](../M2_AccessControl&Authentication/domande_fineM2.md) (§1.1, §1.5, §1.8, §2)

**Che cos'è il DAC.** Le politiche di controllo degli accessi si classificano in base a **chi ha la responsabilità di stabilirle**. Nella politica **discrezionaria (DAC)** la decisione spetta al **proprietario della risorsa**, che stabilisce **chi** può accedervi e in **quale modalità** (lettura, scrittura, esecuzione). Il proprietario dispone inoltre del potere di **delegare i propri privilegi ad altri utenti** — capacità nota come *delegation of duty*. Si contrappone alla politica **mandatoria (MAC)**, in cui gli accessi sono decisi centralmente dal sistema tramite **livelli di sicurezza** assegnati a soggetti e oggetti, senza che i soggetti possano modificarli.

È importante distinguere i tre piani: la **politica** dice *che cosa* deve valere; il **meccanismo** la implementa; il **Reference Monitor** la fa rispettare, intercettando ogni richiesta di accesso, purché sia preceduta da una corretta fase di **autenticazione**. **Linux implementa una politica discrezionaria.**

**Implementazione in Unix/Linux — i soggetti.** Ogni soggetto è un **account utente** identificato da uno **username** al login e, internamente, da un **UID** numerico: `0` è riservato a **root**, l'amministratore che ha accesso a tutte le risorse; i valori bassi (`1`–`99`) sono tipicamente riservati a servizi di sistema. Ogni utente appartiene a uno o più **gruppi**, con un **gruppo primario** identificato dal **GID**. I gruppi semplificano il controllo degli accessi: invece di assegnare permessi utente per utente, si assegnano a una categoria (per esempio il gruppo `mail` per tutti gli abilitati alla posta).

Le informazioni sono distribuite in:

| File | Contenuto |
|------|-----------|
| `/etc/passwd` | Informazioni **pubbliche** sugli account: `nomeUtente:passwordUtente:UID:GID:datiPersonali:directoryHome:shell` |
| `/etc/shadow` | **Salt e hash** delle password, leggibile **solo da root** |
| `/etc/group` | Gruppi: `nomeGruppo:passwordGruppo:GID:listaUtenti` |

Il passaggio da **unshadowed** a **shadowed password** è esso stesso una lezione di controllo degli accessi: storicamente le password stavano in `/etc/passwd`, leggibile da tutti perché contiene anche dati necessari al sistema, e questo consentiva **attacchi a dizionario offline** (copiare il file, provare parole calcolando `hash(salt + parola)` e confrontare). Separando i dati sensibili in `/etc/shadow`, accessibile solo a root, l'esposizione si riduce drasticamente — pur senza rendere impossibile l'attacco a chi riesca comunque a ottenere il file. Il **salt** è un valore casuale **memorizzato in chiaro**: non è un segreto, serve a far sì che la stessa password produca hash diversi per utenti diversi, vanificando le tabelle precalcolate.

**Implementazione — gli oggetti e i diritti.** In Unix vale il principio *"tutto è un file"*: file ordinari, directory, dispositivi, socket sono trattati uniformemente. Ogni file ha un **proprietario** e un **gruppo proprietario** (normalmente UID e GID del processo che lo ha creato) e **nove bit di permesso** organizzati in tre classi — **user**, **group**, **others** — ciascuna con i diritti **`r`**, **`w`**, **`x`**.

| | File ordinario | Directory |
|:-:|---|---|
| `r` | Leggere il contenuto | Elencare i nomi contenuti |
| `w` | Modificare il contenuto | Creare, rinominare, cancellare voci |
| `x` | Eseguirlo come programma | **Attraversarla**: entrarci con `cd`, usarla in un path |

> ⚠️ La semantica sulle **directory non coincide** con quella sui file: senza `x` non si entra in una directory nemmeno avendo `r` (`chmod 444 d1; cd d1` fallisce), e per **cancellare** un file non serve `w` sul file ma `w` sulla directory che lo contiene — da cui l'esistenza dello **sticky bit**.

In **notazione ottale** ($r=4$, $w=2$, $x=1$): `755` = `rwxr-xr-x`, `640` = `rw-r-----`, `700` = `rwx------`. I comandi di gestione sono `chmod` (solo il proprietario o root possono usarlo), `chown`, `chgrp` e `umask`, che definisce i permessi **da sottrarre** alla creazione (con `022` un file nasce `644` e una directory `755`; con `077`, `600` e `700`).

**Ordine di valutazione.** Il kernel applica **la prima classe applicabile e si ferma**: se lo UID coincide con quello del proprietario usa **solo** i bit di *user*; altrimenti, se il soggetto appartiene al gruppo del file, usa **solo** quelli di *group*; altrimenti quelli di *others*. **Non si cerca il permesso più favorevole**: su un file `r--rwx---` di proprietà di alice, alice può **solo leggere**, anche se appartiene al gruppo che avrebbe `rwx`.

**Relazione con la matrice degli accessi.** Concettualmente la politica è una **matrice** soggetti × oggetti, grande e sparsa, che si memorizza in forma compressa. Unix la implementa **per colonne**, cioè come **ACL**: per ogni oggetto si registra chi può accedervi. I nove bit `rwxrwxrwx` sono quindi **una ACL semplificata**, in cui l'elenco dei soggetti non è arbitrario ma ridotto a **tre categorie fisse**.

**Processi e identità.** I programmi vengono eseguiti in aree di memoria protette e, in generale, **con i privilegi del chiamante**: un processo eredita l'identità dell'utente che lo lancia (e, tramite `fork()`, quella del padre). Esistono tre **privilegi addizionali** che modificano questa regola:

- **setuid**: su un eseguibile, il processo gira con lo **UID effettivo del proprietario del file** anziché con quello del chiamante. È ciò che permette a `passwd` (di proprietà di root) di far modificare `/etc/shadow` a un utente comune. Nella stringa dei permessi: `rws------` (con esecuzione) o `rwS------` (senza); si imposta con `chmod u+s` o `chmod 4755`.
- **setgid**: analogo per il gruppo (`rwxrws---` / `rwxrwS---`, `chmod g+s` o `2755`). Su una directory, i file creati al suo interno ereditano il gruppo della directory — utile per le cartelle di progetto condivise.
- **sticky bit**: su una directory, solo il proprietario del file, il proprietario della directory e root possono rimuovere o rinominare i file contenuti (`rwxrwxrwt` / `rwxrwxrwT`, `chmod +t` o `1777`). È ciò che rende sicura `/tmp`.

**I limiti del DAC.** Sono la seconda metà della domanda e vanno elencati in ordine di gravità concettuale.

**1. Nessun controllo sul flusso dell'informazione — il Trojan Horse.** È **il limite fondamentale**, quello da mettere per primo. Il DAC controlla soltanto gli **accessi diretti** alla risorsa: non ha alcun controllo su che cosa accade all'informazione **dopo** che l'accesso è stato concesso e il dato è stato letto. Questo rende i sistemi DAC vulnerabili ai **Trojan Horse**, programmi apparentemente utili che contengono codice nascosto malevolo. Lo scenario canonico:

- $S_1$ è proprietario di $F_1$ e ha stabilito che **solo lui** possa leggerlo;
- $S_2$ è proprietario di $F_2$, che può leggere, e ha concesso a $S_1$ il diritto di **scrittura** su $F_2$;
- $S_1$ invoca inconsapevolmente un programma di utilità che contiene al suo interno le operazioni `read(F1); write(F2)`;
- il programma gira **con i privilegi di $S_1$**: il Reference Monitor **approva entrambe le operazioni**, perché $S_1$ ha davvero il diritto di leggere $F_1$ e di scrivere su $F_2$;
- il contenuto di $F_1$ finisce in $F_2$, e $S_2$ lo legge.

**Nessuna regola DAC è stata formalmente violata**, eppure la politica di riservatezza è stata aggirata. La causa è che **i permessi sono attaccati alla risorsa, non al programma**: il sistema non distingue fra "l'utente vuole leggere $F_1$" e "un programma che l'utente ha lanciato vuole leggere $F_1$". È esattamente il problema che la politica **MAC** risolve: assegnando **livelli di sicurezza** a soggetti e oggetti, il flusso dal livello di $F_1$ verso $F_2$ verrebbe rilevato e bloccato (regola **No Write Down** in Bell-LaPadula).

**2. Granularità grossolana.** Le tre sole classi *user*/*group*/*others* non permettono di esprimere politiche del tipo *"alice può scrivere, bob può solo leggere, carla non deve accedere"* senza creare un **gruppo dedicato per ogni combinazione**, il che porta a una proliferazione ingestibile di gruppi. La risposta parziale sono le **ACL POSIX** (`getfacl`/`setfacl`), che aggiungono voci per singoli utenti e gruppi, una `mask` che limita i permessi effettivi e ACL di default ereditabili sulle directory — ma restano un'estensione dello stesso modello discrezionario.

**3. Il superuser è all-or-nothing.** `root` (UID 0) **scavalca ogni controllo**: non esiste una gradazione fra "utente normale" e "onnipotente". Ne consegue che qualunque compromissione di un processo root equivale alla compromissione dell'intero sistema, e che il **principio del privilegio minimo è difficile da applicare**. Le risposte moderne sono le *capabilities* del kernel Linux, `sudo` con politiche fini (di fatto un elemento **RBAC** innestato sul DAC) e i meccanismi MAC.

**4. I programmi setuid trasformano i bug in escalation.** Poiché setuid è l'unico modo per far compiere a un utente comune un'operazione privilegiata, i sistemi Unix ne sono pieni; ma un binario setuid di proprietà di root è di fatto **codice root eseguibile da chiunque**, e ogni suo difetto diventa una via di **privilege escalation**: invocazione di comandi esterni con nomi relativi (attacco via `PATH`), fiducia in variabili d'ambiente controllate dall'utente (`PATH`, `IFS`, `LD_PRELOAD`), buffer overflow, input non validato.

**5. Revoca e audit onerosi.** Essendo un'implementazione **per colonne**, il modello risponde bene alla domanda *"chi può accedere a questo file?"*, ma malissimo a quella complementare *"a quali file può accedere alice?"*, che richiede di **scorrere l'intero filesystem**. Di conseguenza revocare globalmente i privilegi di un soggetto è costoso, e verificare che una politica sia effettivamente rispettata su un sistema reale è difficile.

**6. Confusione fra utente e processo.** Il DAC identifica il soggetto con l'**utente**, ma il vero soggetto attivo è il **processo**. Un browser, un client di posta o un servizio esposto in rete girano con tutti i diritti dell'utente che li ha lanciati: se vengono compromessi, l'attaccante ottiene immediatamente accesso a **tutti** i file di quell'utente. Non esiste alcun **confinamento** applicativo. È il motivo per cui i sistemi moderni sovrappongono al DAC uno strato **MAC** (SELinux, AppArmor) che confina i processi entro profili predefiniti, indipendentemente dai permessi discrezionali.

**7. Delega incontrollata.** La *delegation of duty*, che è il punto di forza del DAC in termini di flessibilità, è anche una debolezza: un utente legittimo — o un malware che agisce con la sua identità — può **ridistribuire liberamente gli accessi** senza che l'organizzazione possa impedirlo o accorgersene, perché la politica di fatto non è centralizzata ma è la somma delle decisioni individuali dei proprietari.

**8. Assenza di classificazione.** Non esistono **etichette di sicurezza**: il DAC non può esprimere né far rispettare una politica **multilivello** (public / restricted / secret / top secret), né le garanzie formali di segretezza (**Bell-LaPadula**: *no read up*, *no write down*) o di integrità (**Biba**: *no write up*, *no read down*). È per questo che negli ambienti militari e governativi il DAC puro non è considerato sufficiente.

**Conclusione: perché lo si usa comunque.** Il DAC resta la modalità operativa quotidiana perché è **flessibile, intuitivo, poco costoso e adeguato ai sistemi consumer e generalisti**. I sistemi operativi moderni adottano quindi un approccio **ibrido**: il DAC per la gestione ordinaria, con sopra uno strato **MAC** (SELinux, AppArmor su Linux; il *Mandatory Integrity Control* su Windows da Vista in poi) che agisce come rete di sicurezza per contenere i danni in caso di compromissione, e componenti **RBAC** per l'amministrazione. In questo modo si concilia la comodità del modello discrezionale con la garanzia di controlli non aggirabili dall'utente.

| Proprietà | DAC | MAC | RBAC |
|-----------|:---:|:---:|:----:|
| Chi stabilisce gli accessi | Il proprietario della risorsa | Il sistema (centralizzato) | Il sistema, tramite ruoli |
| Controllo del flusso informativo | ✗ | ✓ | Parziale |
| Vulnerabile ai Trojan Horse | ✓ | ✗ | Dipende |
| Flessibilità | Alta | Bassa | Alta |
| Contesto d'uso tipico | Sistemi consumer (Linux) | Militare/governativo | Sistemi aziendali |

> ✅ **Sintesi da scrivere in chiusura:** in Unix/Linux il DAC assegna a ogni file un proprietario e un gruppo, e nove bit `rwx` per *user*, *group* e *others*, valutati per classe di appartenenza con arresto alla prima applicabile; è il **proprietario** a decidere e a poter delegare. I limiti principali sono: **nessun controllo sul flusso dell'informazione dopo l'accesso**, da cui la vulnerabilità ai **Trojan Horse**; granularità limitata a tre classi; `root` onnipotente; i programmi **setuid** come vettori di escalation; revoca e audit onerosi per l'organizzazione per colonne; assenza di confinamento dei processi e di etichette di sicurezza. Per superarli si affianca al DAC uno strato **MAC** (SELinux, AppArmor) e componenti **RBAC**.

---

### **5. Apache: direttive host-based e user-based**

In Apache HTTP Server, descrivere le differenze tra direttive host-based e user-based. Riportare un esempio di utilizzo per ciascuna delle due tipologie.

> **Riferimenti di teoria**:
>
> - [M4/UD5/L1 — Introduzione ad Apache](../M4/UD5/L1/L1_Introduzione_ad_Apache.md) (§6 le due tipologie, §7–10 `Allow`/`Deny`/`Order`, §11 contenitori, §13 ordine di valutazione, §5 `.htaccess`)
> - [M4/UD5/L2 — Apache: direttive user-based e autenticazione](../M4/UD5/L2/L2_Direttive_User_Based_e_Autenticazione_Apache.md) (§2–3 Basic/Digest, §5–10 `Auth*`, §11 `require`, §14 `Satisfy`, §15 `htpasswd`)
> - [M2/UD2/L1 — Introduzione all'autenticazione](../M2_AccessControl&Authentication/UD2/L1_Autenticazione_Intro.md) (l'autenticazione precede l'autorizzazione)
> - Trattazione estesa: [Soluzione 03/07/2026 — domanda 5](Soluzione_Appello_03_07_2026.md)

**La differenza di fondo.** Le due famiglie di direttive differiscono per **che cosa viene valutato**: le **host-based** decidono in base a proprietà del **richiedente come host** — indirizzo IP, nome di dominio, variabile d'ambiente derivata da un header HTTP — e rispondono alla domanda *"da dove arriva la richiesta?"*; le **user-based** decidono in base all'**identità dell'utente** e rispondono a *"chi sta chiedendo?"*. Da qui la conseguenza operativa decisiva: **le user-based richiedono una fase di autenticazione, le host-based no**, coerentemente con il principio che **l'autenticazione precede l'autorizzazione**.

| Aspetto | Host-based | User-based |
|---------|------------|------------|
| Criterio | IP, dominio, variabile d'ambiente da header | Identità dell'utente autenticato |
| Autenticazione | **No** | **Sì** (Basic o Digest) |
| Direttive (2.2) | `Allow`, `Deny`, `Order` | `require valid-user \| user \| group` |
| Direttive (2.4) | `Require ip`, `Require host`, `Require not …` | `Require valid-user \| user \| group` |
| Supporto | `SetEnvIf`, `BrowserMatch` | `AuthType`, `AuthName`, `AuthBasicProvider`, `AuthUserFile`, `AuthGroupFile` |
| Impatto sull'utente | Trasparente | Finestra di login del browser |
| Robustezza | Debole: l'IP è **falsificabile** e cambia con NAT, proxy, VPN, DHCP; gli header sono scelti dal client | Più forte, ma con **Basic** le credenziali viaggiano in **Base64**, che **non è cifratura** → serve HTTPS |
| Granularità | Rete, host, dominio | Singolo utente o gruppo |
| Uso tipico | Restringere alla rete interna; bloccare host o domini | Aree riservate, pannelli amministrativi |

**Host-based: `Allow`, `Deny`, `Order`.** Il valore di `host` può essere `all`, un dominio completo o parziale (`.org`), un IP completo o un pattern (`192.168.205`), una rete con maschera (`192.20.250.0/255.255.255.0`) o una variabile d'ambiente (`env=nome`). La direttiva **`Order`** stabilisce l'ordine di valutazione e con esso la politica di default:

- `Order Deny,Allow` → prima le `Deny`, poi le `Allow`; accesso **permesso di default** (politica aperta);
- `Order Allow,Deny` → prima le `Allow`, poi le `Deny`; accesso **negato di default** (politica chiusa);
- `Order Mutual-failure` → accedono solo gli host in `Allow` e non in `Deny`.

> ⚠️ `Order` da sola non determina l'esito: in `Order Deny,Allow` + `Deny from all` + `Allow from dominiofidato.it` la politica *di default* sarebbe aperta, ma `Deny from all` nega tutti e solo il dominio fidato viene riammesso.

**Esempio host-based.** *I documenti riservati devono essere accessibili solo dalla rete interna `192.168.1.0/24`, escluso il chiosco pubblico `192.168.1.66`.*

```apacheconf
<Directory /var/www/html/documenti>
    Order Allow,Deny
    Allow from 192.168.1
    Deny  from 192.168.1.66
</Directory>
```

`Order Allow,Deny` impone la politica chiusa (chi non è esplicitamente ammesso riceve `403 Forbidden`); `Allow` riapre la sottorete; `Deny`, valutata dopo, esclude il singolo host. Nessuna autenticazione è richiesta. In sintassi 2.4:

```apacheconf
<RequireAll>
    Require ip 192.168.1
    Require not ip 192.168.1.66
</RequireAll>
```

**User-based: `Auth*` + `require`.** Servono due gruppi di direttive. **Autenticazione**: `AuthType` (`Basic`/`Digest`), `AuthName` (il **realm**, mostrato nella finestra del browser), `AuthBasicProvider` (`file` di default), `AuthUserFile` (creato con `htpasswd`), `AuthGroupFile`. **Autorizzazione**: `require valid-user` (qualunque utente autenticabile), `require user u1 u2` (elenco esplicito), `require group g1 g2` (appartenenza a gruppi, con file nella forma `gruppo: alice bob`).

> ⚠️ I file di password e gruppi **non devono stare nel document tree**, altrimenti sarebbero scaricabili via browser.

**Esempio user-based.** *L'area di amministrazione è accessibile solo ad `alice` e `bob`, previa autenticazione.*

```apacheconf
<Directory /var/www/html/cartella_nascosta>
    AuthType Basic
    AuthName "Area Riservata"
    AuthBasicProvider file
    AuthUserFile /usr/local/httpd/passwd/passwords
    Require user alice bob
</Directory>
```

Apache risponde `401 Unauthorized`, il browser mostra la finestra con la dicitura *Area Riservata*, le credenziali vengono verificate contro il file creato con `htpasswd -c /usr/local/httpd/passwd/passwords alice` (poi `htpasswd … bob`, **senza** `-c`, che azzererebbe il file), e l'accesso è concesso solo ai due utenti indicati.

**Combinazione: `Satisfy`.** Le due tipologie possono coesistere nello stesso scope; il loro rapporto logico si specifica con `Satisfy all` (devono valere **entrambe**: host ammesso **e** autenticazione) o `Satisfy any` (**basta una**):

```apacheconf
Require valid-user
Order allow,deny
Allow from 192.168.1
Satisfy Any
```

Configurazione tipica di intranet: nessun login per chi è già in rete interna, login per tutti gli altri. `Satisfy any` è naturalmente **più permissiva**.

**Scope.** Entrambe hanno effetto solo dove si dichiara uno scope: `<Directory>` (filesystem), `<Files>`/`<FilesMatch>` (per nome o regex, ovunque si trovi il file), `<Location>` (spazio web), `<Limit>` (solo per certi metodi HTTP). In `.htaccess` lo scope è implicito, ma serve `AllowOverride` (almeno `AuthConfig`). La valutazione risale dalla directory del file fino alla radice con il principio ***most specific takes precedence***, e l'ordine fra contenitori è `<Directory>`, `<Files>`, `<Location>`.

> ✅ **Sintesi:** le **host-based** filtrano su IP/dominio/variabili d'ambiente e **non richiedono autenticazione**; le **user-based** filtrano sull'identità e **presuppongono** le direttive `Auth*` con un file di password. Le prime sono trasparenti ma deboli (IP falsificabile), le seconde più robuste ma richiedono HTTPS perché Basic usa Base64. Si combinano con `Satisfy all`/`any`.

---

### **6. Autenticazione a più fattori (MFA)**

Che cosa si intende per autenticazione a più fattori (Multi-Factor Authentication)? Descrivere le principali categorie di fattori di autenticazione, evidenziandone vantaggi e svantaggi.

> **Riferimenti di teoria**:
>
> - [M2/UD2/L1 — Introduzione all'autenticazione](../M2_AccessControl&Authentication/UD2/L1_Autenticazione_Intro.md) (§2 definizione, §4 i cinque elementi, §5 i tre fattori)
> - [M2/UD2/L2 — Password](../M2_AccessControl&Authentication/UD2/L2_Autenticazione_Password.md) (salt, attacchi, contromisure)
> - [M2/UD2/L3 — Token](../M2_AccessControl&Authentication/UD2/L3_Autenticazione_Possesso.md) (§1 limite fondamentale, §5 OTP e RSA SecurID)
> - [M2/UD2/L4 — Biometria](../M2_AccessControl&Authentication/UD2/L4_Autenticazione_Biometrica.md) (§2 proprietà ideali, §6 FAR/FRR/EER, §7 attacchi) · [M2/UD2/L5 — Confronto](../M2_AccessControl&Authentication/UD2/L5_Biometria_Esempi_Confronto.md) (§6 quale tecnica è migliore)
> - Trattazione estesa: [Soluzione 03/07/2026 — domanda 6](Soluzione_Appello_03_07_2026.md)

**Definizione.** L'autenticazione è la verifica dell'identità di chi richiede accesso a una risorsa, e precede necessariamente l'autorizzazione. Si parla di **MFA** quando si richiedono **due o più fattori appartenenti a categorie diverse**. La precisazione è essenziale: **due password non sono MFA**, perché sono due istanze dello stesso fattore, vulnerabili allo stesso insieme di attacchi. Il caso a due fattori si indica come **2FA**.

**Il razionale.** Vale il principio dell'**anello più debole**: la robustezza del sistema è quella del componente più fragile. Poiché gli attacchi alle diverse categorie sono fra loro **indipendenti** (un attacco a dizionario non aiuta a rubare un token, il furto di un token non rivela una password), combinando categorie diverse le probabilità di successo si **moltiplicano** anziché sommarsi: **la compromissione di un solo fattore non basta**.

**Le tre categorie.**

| Categoria | Principio | Esempi |
|-----------|-----------|--------|
| **Conoscenza** (*knowledge*) | Informazione segreta nota solo all'utente | Password, PIN, passphrase, domanda segreta |
| **Possesso** (*possession*) | Oggetto fisico detenuto dall'utente | Carta magnetica, smart card, smart token OTP, RFID, smartphone |
| **Inerenza / biometria** (*inherence*) | Caratteristica univoca, fisica o comportamentale | Impronta, iride, retina, volto, mano; voce, firma, andatura |

**1. Conoscenza.** *Vantaggi*: la più **diffusa, economica e semplice**, non richiede hardware, funziona ovunque, è **revocabile e sostituibile** istantaneamente e non pone problemi di privacy. *Svantaggi*: la più **debole**. Esposta a **forza bruta**, **attacco a dizionario** (che sfrutta la scelta di password brevi e legate a dati personali), **guessing**, **shoulder surfing**, **sniffing** su canale non cifrato, **trojan login** che simula l'interfaccia, **social engineering**. Cause strutturali: password invariata a lungo, condivisa, debole, riusata su più servizi o — se complessa — scritta su carta. Lato server non vanno mai memorizzate in chiaro: si conserva $H(\text{salt} \Vert \text{password})$, con **salt** casuale univoco per utente, in chiaro, che elimina la coincidenza degli hash e vanifica le tabelle precalcolate. Limite insuperabile: **chi conosce la password impersona completamente l'utente**, e il sistema non può accorgersene.

**2. Possesso.** *Vantaggi*: è **difficile estrarre il segreto** da un token fisico, tanto più se dotato di coprocessore crittografico (smart card). Le **One-Time Password** neutralizzano sniffing, replay e riuso: il meccanismo combina un **seme segreto** condiviso, un **algoritmo** identico sui due lati e un'**informazione variabile** (ora corrente o contatore), così token e server calcolano lo stesso valore nello stesso intervallo. L'**RSA SecurID** delle banche è già di per sé **2FA**: PIN (conoscenza) + codice a 6 cifre rigenerato ogni 60 secondi (possesso). I token possono anche custodire **credenziali digitali** (chiavi per firma e comunicazioni sicure), sbloccate da una password. *Svantaggi*: il limite **fondamentale** è che si autentica **il token, non l'utente** — chi lo possiede lo impersona, come chi ruba le chiavi di casa; da qui la regola di abbinarlo sempre a un secondo fattore. Inoltre: carte magnetiche **clonabili** e con memoria limitata; gestione complessa di **furto o smarrimento**, con un periodo transitorio in cui l'utente legittimo **non può autenticarsi**; costi di distribuzione; necessità di orologi allineati per i token sincroni; e, se il possesso è realizzato via **SMS**, vulnerabilità del canale (intercettazione, SIM swap).

**3. Biometria.** *Vantaggi*: **non si dimentica, non si indovina con un dizionario, non si sottrae con social engineering, non si perde né si presta**, ed è comodissima per l'utente. Una caratteristica ideale soddisfa **universalità, unicità, stabilità, rilevabilità, accettabilità, difficoltà di contraffazione**; nel confronto fra impronta, geometria della mano e volto, l'**impronta digitale** risulta complessivamente vincente. *Svantaggi*: **la perfetta uguaglianza fra due acquisizioni è impossibile**, quindi serve una fase di **enrollment** con misurazioni multiple per costruire un **template**, e al login si accetta se

$$d(\text{campione}_{nuovo},\ \text{template}_{utente}) \leq \delta$$

La soglia $\delta$ genera il trade-off fra **FAR** (*False Acceptance Rate*: si accetta un impostore) e **FRR** (*False Rejection Rate*: si respinge un legittimo) — alzando la tolleranza cresce il FAR, abbassandola cresce il FRR — con punto di lavoro di riferimento nell'**EER**, dove FAR = FRR. Poi: **sensori costosi**; **accettazione non universale**, percepita come intrusiva; **privacy**, aggravata dal fatto che il dato biometrico è **permanente e non revocabile** (una password compromessa si cambia, un'impronta no); **non universale** (alcuni non possiedono o non possono usare la caratteristica); attacchi specifici di **contraffazione** (dito in gelatina, lente a contatto, maschera), **replay del template**, attacchi al **canale sensore–database**, **modifica del template** e **override della decisione**. Va infine distinta l'**autenticazione 1:1** dall'**identificazione 1:N**, molto più esposta ai falsi match.

**MFA nel complesso.** *Vantaggi*: neutralizza le classi di attacco più diffuse (phishing di sole password, dizionario, riuso fra servizi, database trafugati) e **compensa i difetti di ciascun fattore con i pregi degli altri**. La combinazione tecnicamente ottimale indicata a lezione è **biometria fra utente e token** (così il token rubato è inutilizzabile) più **autenticazione mutua crittografica fra token e sistema** — realizzata per esempio dalle smart card con impronta integrata usate agli atleti tedeschi alle Olimpiadi di Atene 2004. *Svantaggi*: costi di distribuzione e gestione; **attrito di usabilità**, che spinge ad aggirare la misura; il problema del **recupero** in caso di perdita del secondo fattore, che spesso reintroduce un canale debole (domande segrete, email di reset); rischio di un nuovo **single point of failure** se tutto è concentrato sullo smartphone; attacchi specifici come la **MFA fatigue** (bombardamento di notifiche fino all'approvazione distratta) e il **relay in tempo reale** dell'OTP tramite un sito di phishing che fa da proxy.

> 💡 Tecnicamente la biometria è la più robusta; **praticamente le password restano il meccanismo più usato**, per il trade-off costi/benefici. La MFA è precisamente il modo di conciliare le due prospettive.

> ✅ **Sintesi:** MFA = due o più fattori di **categorie diverse** — conoscenza (economica e revocabile, ma esposta a dizionario, sniffing, social engineering), possesso (resiste a replay e sniffing, ma autentica il token e non l'utente ed è soggetto a furto e clonazione), biometria (non dimenticabile né prestabile, ma costosa, **non revocabile** e soggetta al trade-off FAR/FRR e alla contraffazione). La forza sta nell'**indipendenza** degli attacchi ai diversi fattori; i costi sono in usabilità, gestione e procedure di recupero.

---

### **7. Challenge-response basato su nonce**

Si consideri un protocollo di autenticazione di tipo challenge-response basato su nonce. Descrivere il protocollo, spiegando: perché risulta resistente agli attacchi di tipo replay; come modificarlo per ottenere la mutua autenticazione; quale ruolo svolge il nonce nella sicurezza del protocollo.

> **Riferimenti di teoria**:
>
> - [M3/UD3/L4 — Protocolli challenge-response](../M3/UD3/L4.md) (§2 idea generale, §3 freshness, §5 il nonce, §6 le tre varianti, §12 mutua autenticazione, §13 confronto, §14 errori comuni)
> - [M3/UD3/L2 — Protocollo di autenticazione unilaterale](../M3/UD3/L2.md) (§6–8 replay, §9 challenge-response, §12–13 MITM e certificati)
> - [M3/UD3/L3 — Attacchi comuni ai protocolli](../M3/UD3/L3.md) · [M3/UD3/L1 — Notazione](../M3/UD3/L1.md) · [M3/UD3/L7 — Principi di progettazione](../M3/UD3/L7.md)
> - Trattazione estesa: [Soluzione 03/07/2026 — domanda 7](Soluzione_Appello_03_07_2026.md)

**Il protocollo.** Un challenge-response è un **test di autenticazione**: verifica che un partecipante possieda un segreto **senza che il segreto venga trasmesso**. Ruoli: **challenger** (chi richiede l'autenticazione) e **responder** (chi si autentica). Nella versione con **nonce** e **chiave simmetrica condivisa** $K_{AB}$, con Alice che vuole autenticare Bob:

$$1.\ A \rightarrow B : N_A \qquad\qquad 2.\ B \rightarrow A : \{N_A\}_{K_{AB}}$$

Alice genera un **nonce** $N_A$ (sequenza casuale, tipicamente 32–128 bit), lo memorizza e lo invia **in chiaro**; Bob lo cifra con la chiave condivisa e lo restituisce; Alice decifra e verifica che il valore coincida **esattamente** con quello generato. Se coincide, chi ha risposto conosce $K_{AB}$ **e** ha elaborato *quella* sfida.

Due punti concettuali: **il segreto non viene trasmesso** (Bob invia $f(\text{challenge}, \text{segreto})$, non la chiave), e **il challenge può essere pubblico**, perché ciò che autentica Bob non è la segretezza della sfida ma la sua **capacità di trasformarla**. La funzione $f$ può essere cifratura simmetrica, MAC, firma digitale o decifratura, senza che la logica cambi.

Le tre varianti: *(1)* challenge in chiaro, response cifrato; *(2)* challenge cifrato $\{N_A\}_{K_{AB}}$, response in chiaro $N_A$ (la prova sta nell'aver **recuperato** un nonce nascosto, e funziona proprio perché $N_A$ è imprevedibile); *(3)* entrambi cifrati, con la precauzione descritta sotto.

**Resistenza al replay.** Un **replay attack** consiste nel registrare un messaggio valido e reinviarlo più tardi. I protocolli ingenui vi soccombono perché il messaggio è **statico**: `A, P_A` o anche `A, {P_A}_K` sono sempre uguali e quindi riproducibili — **cifrare non basta**, un cifrato costante diventa esso stesso una credenziale riutilizzabile.

Il nonce introduce la **freschezza**. Sia $\{N_{old}\}_{K_{AB}}$ una risposta registrata da Trudy. Nella sessione corrente Alice **non invia** $N_{old}$: genera $N_A$ nuovo e attende $\{N_A\}_{K_{AB}}$. La vecchia risposta, decifrata, dà $N_{old} \neq N_A$ e viene rifiutata. Per riuscire, Trudy dovrebbe **produrre la risposta per un valore mai visto**, cioè conoscere $K_{AB}$.

Le due condizioni da enunciare: il nonce è **casuale e imprevedibile** (niente precalcolo) e **non viene mai riutilizzato** (altrimenti si ricade nel caso statico). Dettaglio operativo: **la freschezza la verifica chi ha generato il nonce** — Bob non deve memorizzare i nonce ricevuti, è Alice a confrontare. Per questo i nonce sono leggeri: **niente orologi sincronizzati, niente stato condiviso**.

> ⚠️ **Variante 3.** Se challenge e response fossero entrambi $\{N_A\}_{K_{AB}}$, Trudy potrebbe **riflettere** verso Alice lo stesso cifrato appena intercettato, senza decifrare nulla. Il response deve contenere una **trasformazione riconoscibile**, tipicamente $\{N_A + 1\}_{K_{AB}}$: solo chi ha davvero decifrato può incrementare e ricifrare.

**Mutua autenticazione.** Il protocollo è **unilaterale**: Alice ha autenticato Bob, ma Bob non sa nulla di chi gli ha inviato la sfida — chiunque può generare un numero casuale. Servono i test in **entrambe le direzioni**; la forma compatta usa **due nonce** e tre messaggi:

$$1.\ A \rightarrow B : N_A \qquad 2.\ B \rightarrow A : N_B,\ \{N_A\}_{K_{AB}} \qquad 3.\ A \rightarrow B : \{N_B\}_{K_{AB}}$$

Il secondo messaggio fa doppio lavoro (*piggybacking*): Bob **risponde** e contemporaneamente **sfida**. Costo: un solo messaggio in più.

> ⚠️ **Non è "eseguire due volte lo stesso protocollo".** Se i messaggi sono troppo simmetrici si aprono i **reflection attack**: Trudy, volendo autenticarsi come Alice presso Bob, apre una **seconda sessione parallela con Bob stesso** e gli gira $N_B$, facendosi calcolare da lui $\{N_B\}_{K_{AB}}$, che rispedisce nella prima sessione. Bob **autentica se stesso** credendo di autenticare Alice.

La contromisura è **legare ogni messaggio al contesto**, inserendo nel materiale autenticato le **identità** (e possibilmente ruolo, direzione, fase, identificativo di sessione):

$$2.\ B \rightarrow A : N_B,\ \{N_A,\ B\}_{K_{AB}} \qquad 3.\ A \rightarrow B : \{N_B,\ A\}_{K_{AB}}$$

Ora una risposta valida in una direzione non è riutilizzabile nell'altra. È lo stesso principio della correzione di Lowe al Needham-Schroeder a chiave pubblica e dei principi di **Abadi-Needham**.

**Ruolo del nonce — le quattro funzioni.**

1. **Freschezza**: trasforma l'autenticazione dall'invio di una **credenziale statica** alla **risposta a una sfida fresca**, valida solo per *questa* esecuzione. Distingue "qualcuno conosce il segreto" da "qualcuno conosce il segreto **adesso**".
2. **Anti-replay**, per costruzione: essendo generato ex novo, rende inutilizzabile qualunque risposta registrata.
3. **Anti-precalcolo**, grazie all'**imprevedibilità**. È qui che si distingue dal **timestamp**, che è **prevedibile**: con i timestamp la variante *challenge cifrato / response in chiaro* — sicura con un nonce — diventa **insicura**, perché Trudy può stimare l'ora e inviare il valore in chiaro fingendosi Bob.
4. **Individuazione della sessione**: legato alle identità nel materiale autenticato, distingue sessioni parallele e ostacola i reflection attack.

Il nonce **non è un segreto** e può viaggiare in chiaro: è un valore **temporaneo, casuale, usato una sola volta**, e tutta la sua utilità sta in queste tre proprietà. Le alternative — **timestamp** (leggeri ma prevedibili, richiedono orologi sincronizzati, finestre di accettazione e memoria dei valori recenti), **numeri di sequenza** (nessun orologio, ma stato persistente legato a partecipanti, direzione e sessione, e rischio di desincronizzazione), **chiavi a breve termine** (uniscono autenticazione e distribuzione di chiave di sessione) — hanno ciascuna il proprio compromesso: **non esiste una scelta universalmente migliore**.

**Variante a chiave pubblica.** Con la firma digitale:

$$1.\ A \rightarrow B : A \qquad 2.\ B \rightarrow A : N_B \qquad 3.\ A \rightarrow B : [N_B]_{K_A^-}$$

si elimina il vincolo della chiave simmetrica già condivisa, ma si introduce l'assunzione che Bob possieda in modo **autentico** $K_A^+$: se la chiede sul canale insicuro, Trudy la sostituisce con la propria e realizza un **Man in the Middle**. La correzione è inviare il **certificato** $C_A$, verificandone firma della CA, validità e catena.

> ✅ **Sintesi:** il verificatore invia una sfida fresca e il richiedente prova la propria identità restituendone una trasformazione, senza trasmettere il segreto. Il replay è impossibile perché il nonce è **imprevedibile e mai riutilizzato**. Per la mutua autenticazione si eseguono i due test in entrambe le direzioni con due nonce (tre messaggi con piggyback), includendo le **identità** per prevenire i reflection attack. Il nonce fornisce freschezza, anti-replay, imprevedibilità e identificazione della sessione, e per farlo non deve essere segreto ma solo casuale e usato una volta sola.

---

## Nota metodologica

Le sei domande condivise con l'appello del 3 luglio sono qui in forma condensata **per scelta**, come secondo passaggio di ripasso; la versione estesa è nella [Soluzione 03/07/2026](Soluzione_Appello_03_07_2026.md). Per la matrice completa degli argomenti ricorrenti nei tre appelli, la classifica delle priorità e le trappole tipiche del docente, si veda la [Guida al ripasso delle ultime ore](Guida_Ripasso_Ultime_Ore.md).
