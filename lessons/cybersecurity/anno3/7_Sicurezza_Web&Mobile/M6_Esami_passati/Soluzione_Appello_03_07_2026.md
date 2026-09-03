# Soluzione Appello — Sicurezza dei Sistemi Web e Mobili — 03/07/2026

**Docente: Chiara Braghin** · A.A. 2025/26 · Tempo a disposizione: **1:30 h** · 7 domande

> 📌 Consegna del docente: *"Rispondere brevemente ma in modo completo alle seguenti domande."*
> Budget: **~12 minuti a domanda**, 250–350 parole ciascuna. La parte in **grassetto d'apertura** di ogni paragrafo è il nucleo minimo da scrivere; il resto è approfondimento per il 30 e lode.

> ⚠️ Questo appello e quello del **10 luglio 2026** sono **identici tranne la domanda 4** (qui: `r`/`w`/`x` e ACL; là: DAC in Unix/Linux e i suoi limiti). Le altre sei domande coincidono parola per parola: è il segnale più forte sulla struttura d'esame, discusso nella [Guida al ripasso delle ultime ore](Guida_Ripasso_Ultime_Ore.md).

---

## Traccia originale dell'appello

### **1. Kerberos: ruolo di AS e TGS, e perché è un sistema di SSO**

Nell'ambito del sistema Kerberos, descrivere il ruolo dell'Authentication Server (AS) e del Ticket Granting Server (TGS). Spiegare inoltre perché Kerberos costituisce un esempio di sistema di Single Sign-On (SSO).

> **Riferimenti di teoria**:
>
> - [M4/UD6/L2 — Kerberos](../M4/UD6/L2.md) (§5 attori, §6 obiettivi, §7–10 le tre fasi, §11–13 ticket e TGT, §14 master key e chiavi di sessione, §15 la password non transita, §16 formalizzazione, §17 SSO centralizzato, §18 cross-realm, §19–20 vantaggi e svantaggi)
> - [M4/UD6/L1 — Single Sign-On](../M4/UD6/L1.md) (§5 definizione, §7–8 dominio primario/secondario e token, §10 IdP e SP, §12 SSO centralizzato)
> - [M3/UD3/L5 — Needham-Schroeder simmetrico](../M3/UD3/L5.md) (protocollo da cui Kerberos deriva, attacco Denning-Sacco e correzione con timestamp)
> - [M3/UD3/L4 — Protocolli challenge-response](../M3/UD3/L4.md) (§10–11 chiave a breve termine distribuita da un server)

**Inquadramento.** Kerberos nasce al **MIT** all'interno del progetto **Athena** per garantire accesso sicuro a risorse di rete da parte di utenti di workstation, e prende il nome da **Cerbero**, il cane a tre teste che sorveglia l'accesso all'Ade. È basato interamente su **crittografia a chiave simmetrica** (storicamente DES), è una variante del protocollo **Needham-Schroeder a chiave condivisa**, richiede una **terza parte fidata** e presuppone che tutti gli **orologi siano sincronizzati**, perché usa **timestamp** per garantire la freschezza di chiavi e messaggi. Ne esistono due versioni di dominio pubblico, la 4 e la 5, e viene adottato come sistema di autenticazione predefinito in diversi sistemi Microsoft e in Mac OS X.

Gli obiettivi che si prefigge — utili per motivare i due server — sono: autenticare Alice; **evitare che la password transiti sulla rete**; evitare che la password sia memorizzata stabilmente sulla workstation; evitare che le password siano in chiaro nel database del server di autenticazione; **far inserire la password una sola volta per sessione di lavoro** anche accedendo a più servizi; centralizzare la gestione delle identità; supportare, se richiesto, mutua autenticazione e comunicazione cifrata tramite una chiave di sessione.

**Ruolo dell'Authentication Server (AS).** L'AS fornisce il servizio di autenticazione **in fase di login**. Condivide una **chiave a lungo termine (master key)** con ogni utente e con ogni server: per un utente la chiave è **derivata dalla sua password** al momento dell'inserimento nel database, per un Service Provider è imposta dall'amministratore.

Il funzionamento è il punto chiave della domanda. L'utente inserisce username e password sulla workstation, ma **solo lo username viene trasmesso** all'AS. L'AS verifica che l'utente esista nel proprio database e, in caso affermativo:

1. genera un **Ticket Granting Ticket (TGT)**, un ticket speciale con una scadenza;
2. genera una **chiave di sessione** $K_{A,TGS}$ da usare con il Ticket Granting Server;
3. cifra questa chiave di sessione con la master key che condivide con l'utente;
4. invia al client TGT e chiave di sessione cifrata.

Il programma di login lato client **deriva localmente** la chiave dalla password appena digitata e la usa per decifrare la chiave di sessione ricevuta. La password serve dunque **solo localmente**, non viene mai spedita: è il vantaggio principale di Kerberos rispetto ai servizi tradizionali come Telnet e FTP, che trasmettono le credenziali in chiaro. Dopo questo passaggio la password può essere scartata.

**Ruolo del Ticket Granting Server (TGS).** Il TGS **emette i ticket** che provano l'identità di chi li possiede presso i singoli servizi. Il client si presenta al TGS con il **TGT** e la chiave di sessione ottenuta dall'AS — **non più con le proprie credenziali**. Il TGS autentica il client su questa base e, se la verifica ha successo, rilascia:

1. un **ticket per il servizio specifico** richiesto;
2. un'ulteriore **chiave di sessione** $K_{AB}$ da condividere con il Service Provider.

Il **ticket** è il "biglietto" che il client presenta a Bob per dimostrare la propria identità. È **cifrato con la chiave condivisa fra TGS e Service Provider destinatario**: quindi nemmeno il client che lo richiede può leggerlo o modificarlo — in particolare non può allungarne la durata di vita. Contiene l'identità del client, l'identità del servizio, l'indirizzo IP della macchina da cui può essere usato, il **timestamp** di emissione, il **tempo massimo di vita** (tipicamente ~10 ore) e la **chiave di sessione** condivisa fra client e Service Provider.

> ⚠️ Una volta emesso un ticket, il TGS non può impedirne l'uso fino alla scadenza, perché il ticket viene presentato direttamente dal client al servizio. La **durata di vita del ticket è quindi un parametro di sicurezza critico**: ticket lunghi amplificano l'impatto di una compromissione.

**Ruolo complessivo: AS + TGS = KDC = Identity Provider.** Poiché Kerberos usa crittografia simmetrica, AS e TGS insieme costituiscono il **Key Distribution Center (KDC)**, e nel linguaggio del Single Sign-On rappresentano l'**Identity Provider**. Il Service Provider è Bob, il server che eroga il servizio. La separazione dei ruoli ha una motivazione precisa: l'AS è l'unico che tratta la **password** (una credenziale a lungo termine, usata una volta sola per sessione), mentre il TGS lavora solo con **ticket e chiavi di sessione** a breve termine, riducendo l'esposizione del segreto di lungo periodo.

**Le tre fasi del protocollo.**

1. **Client ↔ AS**: autenticazione tramite password; il client ottiene TGT e chiave di sessione per il TGS.
2. **Client ↔ TGS**: il client presenta TGT e chiave; ottiene il ticket di servizio e la chiave di sessione per Bob.
3. **Client ↔ Service Provider**: il client presenta il ticket; Bob lo decifra con la propria chiave, ricava $K_{AB}$, verifica che sia ancora valida e concede il servizio; può inoltre completare la **mutua autenticazione** e usare $K_{AB}$ per cifrare la comunicazione.

Nella formalizzazione vista a lezione, fondendo AS e TGS in un unico server fidato $S$:

$$1.\ A \rightarrow S: A,\ B$$

$$2.\ S \rightarrow A: \left\{T_S,\ L,\ K_{AB},\ B,\ \left\{T_S,\ L,\ K_{AB},\ A\right\}_{K_{BS}}\right\}_{K_{AS}}$$

$$3.\ A \rightarrow B: \left\{T_S,\ L,\ K_{AB},\ A\right\}_{K_{BS}},\ \left\{A,\ T_A\right\}_{K_{AB}}$$

$$4.\ B \rightarrow A: \left\{T_A + 1\right\}_{K_{AB}}$$

dove $T_S$ è il timestamp, $L$ il *lifetime*, $K_{AS}$ e $K_{BS}$ le chiavi a lungo termine con il server. La parte $\{T_S, L, K_{AB}, A\}_{K_{BS}}$ è il **ticket**, illeggibile per Alice e decifrabile solo da Bob; $\{A, T_A\}_{K_{AB}}$ è l'**autenticatore**, che funge da challenge. Il quarto messaggio è la response di Bob: incrementando il timestamp e ricifrandolo con $K_{AB}$, Bob dimostra di aver davvero decifrato il ticket e di conoscere la chiave di sessione — è così che si ottiene la **mutua autenticazione**.

**Perché Kerberos è un sistema di Single Sign-On.** Il **Single Sign-On** è una proprietà del controllo degli accessi a sistemi software indipendenti ma collegati fra loro, che consente all'utente di **autenticarsi una sola volta con un'unica coppia di credenziali** e accedere poi a tutte le risorse per cui è abilitato, anche in domini diversi. Il meccanismo generale prevede un **dominio primario** che autentica l'utente e **domini secondari** che si fidano di lui, con un **token di autenticazione** che rappresenta l'avvenuta autenticazione al posto delle credenziali.

Kerberos realizza esattamente questo schema, in forma **centralizzata**:

- esiste un **unico Identity Provider**, realizzato dalla coppia **AS + TGS**, che concentra la gestione delle identità e delle informazioni di autenticazione;
- l'utente **inserisce la password una sola volta per sessione di lavoro**, nella fase con l'AS;
- da quel momento non usa più le credenziali: usa il **TGT** per chiedere al TGS un ticket **per ogni servizio**, e il ticket per accedere al servizio;
- i Service Provider **non gestiscono credenziali proprie**: si fidano del ticket emesso dall'infrastruttura centrale, esattamente come i domini secondari si fidano del dominio primario.

Il **TGT è dunque il meccanismo che rende possibile il SSO**: è ciò che sostituisce la password nelle richieste successive. Kerberos supporta inoltre l'autenticazione **cross-realm** — dove un *realm* è l'insieme di client e server sotto il controllo amministrativo di una singola coppia AS/TGS — che avvicina il modello al **SSO federato**: client di un realm possono accedere a server di un altro realm, a condizione che esista un **pre-agreement** e che le due coppie AS/TGS condividano una chiave, perché la crittografia simmetrica richiede chiavi concordate a priori.

**Vantaggi e svantaggi (chiusura).** Il vantaggio centrale è che **la password non viene mai trasmessa**, sostituita da ticket temporanei e chiavi di sessione. Gli svantaggi sono la complessità implementativa, la migrazione non automatizzata delle password, la necessità di modificare le applicazioni per renderle *kerberizzate*, la dipendenza dalla sincronizzazione degli orologi e soprattutto il fatto che, essendo simmetrico, la **compromissione del KDC compromette l'intero sistema** — comunicazioni passate, presenti e future — perché AS e TGS sono il centro della fiducia.

> ✅ **Sintesi da scrivere in chiusura:** l'AS autentica l'utente al login usando la chiave derivata dalla password (che non viaggia mai in rete) e rilascia il TGT con una chiave di sessione; il TGS, su presentazione del TGT, rilascia i ticket per i singoli servizi con le relative chiavi di sessione. Insieme costituiscono il KDC, cioè l'Identity Provider. Kerberos è SSO centralizzato perché l'utente si autentica **una sola volta per sessione** e accede poi a più servizi presentando ticket anziché credenziali, con la gestione delle identità concentrata in un'unica infrastruttura fidata.

---

### **2. Cross-Site Request Forgery (CSRF)**

Che cosa si intende per Cross-Site Request Forgery (CSRF)? Descrivere in che modo viene realizzato questo tipo di attacco e discutere le principali contromisure.

> **Riferimenti di teoria**:
>
> - [M4/UD4/L1 — Same Origin Policy](../M4/UD4/L1.md) (§8 cosa limita la SOP, §10 SOP e cookie, §16.2 cosa **non** protegge, §18.5 *invio* dei cookie ≠ *lettura* dei cookie — è il cardine dell'attacco)
> - [M4/UD2/L2 — Cookie HTTP, sessioni e privacy](../M4/UD2/L2.md) (§5 header `Set-Cookie`/`Cookie`, §8 attributi `Domain`/`Path`/`Secure`/`HttpOnly`, §12 first-party e third-party)
> - [M4/UD2/L3 — Vulnerabilità dei cookie e contromisure](../M4/UD2/L3.md) (§13 contromisure generali, §10 campi hidden nei form)
> - [M4/UD2/L1 — HTTP: funzionamento e vulnerabilità](../M4/UD2/L1.md) (protocollo stateless, semantica di GET e POST)
> - [M4/UD4/L2 — Cross-Site Scripting](../M4/UD4/L2.md) (confronto XSS/CSRF; l'XSS annulla ogni difesa anti-CSRF)
> - [M3/UD3/L4 — Protocolli challenge-response](../M3/UD3/L4.md) (il token anti-CSRF è concettualmente un **nonce**: freschezza e imprevedibilità)
>
> ⚠️ **Nota di copertura**: il CSRF non ha una lezione dedicata nel materiale del corso attualmente digitalizzato. La risposta qui è ricostruita a partire dai meccanismi che il corso tratta esplicitamente — sessioni via cookie, Same Origin Policy, freschezza dei protocolli — e ne usa la terminologia.

**Che cos'è.** Il **Cross-Site Request Forgery** (detto anche *sea surf*, XSRF, *session riding*, *confused deputy attack*) è un attacco in cui un sito malevolo **induce il browser della vittima a inviare a un sito terzo una richiesta che modifica lo stato**, sfruttando il fatto che la vittima ha con quel sito una **sessione autenticata attiva**. Il server riceve una richiesta perfettamente valida, corredata del cookie di sessione corretto, e la esegue: non ha modo di sapere che **non è stata voluta dall'utente**.

Il modo più chiaro di enunciarlo è per contrasto con l'XSS:

| | XSS | CSRF |
|---|---|---|
| Quale fiducia viene abusata | La fiducia dell'**utente nel sito** (lo script gira nell'origine fidata) | La fiducia del **sito nel browser dell'utente** (la richiesta arriva con le credenziali giuste) |
| Che cosa fa l'attaccante | Esegue codice nell'origine della vittima: **legge** dati, cookie, DOM | **Scrive**: provoca un'azione con effetti collaterali. Non legge la risposta |
| Serve una vulnerabilità nel sito bersaglio? | Sì: input riflesso o memorizzato senza escaping | Non necessariamente: basta che il sito autentichi **solo** tramite cookie ambientale |
| Vittima diretta | Il browser del client | Lo **stato lato server** (trasferimento, cambio password, ordine, cancellazione) |

**Perché è possibile: i due presupposti.**

1. **Le credenziali sono "ambientali".** HTTP è **stateless**, quindi la sessione si mantiene con un **cookie** contenente il session identifier. Il browser allega automaticamente quel cookie **a ogni richiesta diretta verso il dominio per cui il cookie è valido**, indipendentemente da *quale pagina* abbia originato la richiesta. Il server, ricevendo il cookie giusto, considera la richiesta autenticata. Lo stesso vale per le altre credenziali ambientali: HTTP Basic Authentication, certificati client, autenticazione integrata di dominio.
2. **La Same Origin Policy non impedisce di *inviare* richieste cross-origin.** La SOP limita ciò che uno script può **leggere o manipolare** attraverso i confini di origine: il DOM, i cookie, il contenuto delle risposte. Ma una pagina può tranquillamente **includere risorse e inviare richieste** verso domini diversi — è così che funzionano immagini da CDN, script di librerie esterne, banner, iframe. È esattamente l'errore concettuale segnalato a lezione: **invio dei cookie e lettura dei cookie sono due cose distinte**. L'attaccante non ha bisogno di *leggere* nulla: gli basta che la richiesta **parta e venga eseguita**. Per questo il CSRF viene detto attacco "cieco" (*blind*).

**Come viene realizzato — scenario tipico.** Sia `bank.example` un sito di home banking in cui il trasferimento di denaro è esposto come:

```http
GET /transfer?to=mario&amount=100 HTTP/1.1
Host: bank.example
Cookie: JSESSIONID=8f4a...
```

Le fasi dell'attacco sono cinque:

1. **La vittima è autenticata.** Alice ha fatto login su `bank.example` e possiede un cookie di sessione valido. Non serve che la finestra della banca sia aperta: basta che il cookie non sia scaduto e che non abbia fatto logout.
2. **L'attaccante prepara la pagina trappola.** Su `evil.example` (oppure in un post su un forum, in una email HTML, in un banner pubblicitario) inserisce un elemento che genera automaticamente la richiesta verso la banca. Nella forma più semplice, se l'operazione è esposta in `GET`, basta un tag immagine:

   ```html
   <img src="https://bank.example/transfer?to=trudy&amount=5000" width="1" height="1">
   ```

   L'immagine non esiste e non verrà mai visualizzata, ma **la richiesta viene inviata**. Se l'operazione richiede `POST`, si usa un form nascosto con auto-invio:

   ```html
   <body onload="document.forms[0].submit()">
     <form action="https://bank.example/transfer" method="POST">
       <input type="hidden" name="to"     value="trudy">
       <input type="hidden" name="amount" value="5000">
     </form>
   </body>
   ```

3. **La vittima viene indotta a visitare la pagina**, tipicamente con una tecnica di **phishing** — email con un pretesto urgente, link su un social, commento su un forum — oppure semplicemente navigando su un sito che ospita contenuti di terze parti non controllati.
4. **Il browser invia la richiesta e allega automaticamente il cookie** di `bank.example`, perché la destinazione è quel dominio.
5. **Il server esegue l'operazione.** Vede una richiesta ben formata, con sessione valida, proveniente da un utente autenticato: bonifico effettuato. Trudy non legge la risposta — la SOP glielo impedisce — ma non le serve: l'effetto collaterale è già avvenuto.

Gli obiettivi tipici sono tutte le operazioni **con effetti di stato**: trasferimenti di denaro, cambio dell'indirizzo email o della password (che porta al takeover completo dell'account), acquisti, cancellazione di dati, modifica delle impostazioni di sicurezza, creazione di utenti amministrativi in un pannello di gestione, o — su un router domestico, dove le credenziali di default sono spesso invariate — il cambio del server DNS.

**Contromisure.** Vanno ordinate dalla più solida alla più accessoria.

1. **Token anti-CSRF (synchronizer token pattern)** — la contromisura di riferimento. Il server genera un valore **casuale, imprevedibile e legato alla sessione** (o alla singola form), lo inserisce come campo `hidden` in ogni form che modifica lo stato e **lo verifica lato server** a ogni richiesta, rifiutando quelle che non lo portano o lo portano sbagliato. Funziona perché il token **non è una credenziale ambientale**: il browser non lo allega da solo, va letto dalla pagina, e la Same Origin Policy impedisce alla pagina di `evil.example` di leggere il documento di `bank.example` per estrarlo. È concettualmente lo stesso meccanismo del **nonce** nei protocolli challenge-response: aggiungere alla richiesta un valore fresco e imprevedibile che solo il partecipante legittimo può conoscere.
2. **Attributo `SameSite` sul cookie di sessione.** `SameSite=Strict` impedisce al browser di allegare il cookie a qualunque richiesta originata da un altro sito; `SameSite=Lax` (oggi il default nei browser moderni) lo allega solo alle navigazioni di primo livello in `GET`, bloccando quindi i form `POST` cross-site e le richieste da `<img>`/`<iframe>`. È una difesa **strutturale** e molto efficace, ma va considerata **difesa in profondità** e non sostitutiva del token: dipende dal browser, e `Lax` lascia scoperte le operazioni esposte in `GET`.
3. **Verifica degli header `Origin` e `Referer`.** Il server accetta le richieste che modificano lo stato solo se provengono dalla propria origine. È semplice ed efficace, ma va gestito il caso in cui l'header sia assente (alcuni proxy e configurazioni di privacy lo rimuovono): la scelta *fail-closed* è rifiutare.
4. **Non usare `GET` per operazioni che modificano lo stato.** La semantica di HTTP prescrive che `GET` sia **sicura e idempotente**, cioè usata solo per recuperare risorse; le operazioni con effetti vanno esposte in `POST`/`PUT`/`DELETE`. Da sola non è una difesa — un form auto-inviato genera senza problemi una `POST` — ma elimina il vettore banale del tag `<img>` e rende applicabili `SameSite=Lax` e i controlli sui metodi.
5. **Ri-autenticazione o step-up per le operazioni critiche.** Richiedere di reinserire la password, un codice OTP o una conferma via app per bonifici, cambi di password e cambi di email. È la contromisura più forte perché reintroduce un fattore che **il browser non allega automaticamente**, ed è per questo che l'home banking la usa sistematicamente.
6. **Igiene di sessione**: durata limitata, logout effettivo lato server (invalidazione del session identifier, non solo cancellazione del cookie), rigenerazione dell'identificatore dopo il login, e `Secure` + HTTPS per proteggere il cookie sul canale.

> ⚠️ **Due precisazioni che valgono il voto pieno.**
> **(a) `HttpOnly` non protegge dal CSRF.** `HttpOnly` impedisce a `document.cookie` di *leggere* il cookie e serve contro il furto di sessione via XSS; ma il CSRF non ha bisogno di leggere il cookie, ha bisogno che il browser lo *invii*, cosa che `HttpOnly` non impedisce affatto. Confondere le due difese è l'errore tipico su questa domanda.
> **(b) Una vulnerabilità XSS annulla ogni difesa anti-CSRF.** Se l'attaccante riesce a eseguire uno script **dentro l'origine del sito bersaglio**, quello script può leggere il token anti-CSRF dal DOM e costruire richieste perfettamente legittime. Per questo XSS e CSRF vanno affrontati insieme: eliminare l'XSS è una precondizione della protezione anti-CSRF.

> ✅ **Sintesi da scrivere in chiusura:** il CSRF induce il browser di un utente autenticato a inviare a un sito terzo una richiesta che modifica lo stato, sfruttando l'invio automatico del cookie di sessione e il fatto che la Same Origin Policy non impedisce l'**invio** di richieste cross-origin (ma solo la **lettura** delle risposte). Si realizza con un tag `<img>` o un form auto-inviato ospitati su una pagina malevola, recapitata via phishing. La difesa principale è il **token anti-CSRF** — un nonce imprevedibile, non ambientale, verificato lato server — affiancato da `SameSite`, controllo di `Origin`/`Referer`, uso corretto dei metodi HTTP e ri-autenticazione per le operazioni critiche.

---

### **3. Firma digitale**

Si descriva il funzionamento della firma digitale. In particolare spiegare:

- quali proprietà di sicurezza garantisce;
- quali proprietà non garantisce;
- quale ruolo svolgono gli algoritmi di hash.

> **Riferimenti di teoria**:
>
> - [M3/UD2/L5 — Firma digitale](../M3/UD2/L5.md) (§1 obiettivo, §2 requisiti, §3 perché il MAC non basta, §4 costruzione asimmetrica, §5–6 firma e verifica, §7 sicurezza, §8 associazione chiave–identità, §9 confronto hash/MAC/firma, §10 firma e confidenzialità)
> - [M3/UD2/L4 — Funzioni hash e MAC](../M3/UD2/L4.md) (§2 proprietà, §4 birthday paradox, §7 MAC, §10 limiti del MAC, §11 famiglie MD/SHA)
> - [M3/UD2/L6 — Certificati digitali e PKI](../M3/UD2/L6.md) (soluzione al problema dell'autenticità della chiave pubblica)
> - [M3/UD2/L3 — Crittografia simmetrica e asimmetrica](../M3/UD2/L3.md) (chiavi pubblica/privata, costo delle primitive asimmetriche)
> - [M1/UD2/L2 — Proprietà di sicurezza](../M1_Intro_CyberSec/UD2_CybersecNotions/L2_Cybersec_Definition.md) (§4.2 non ripudio)

**Obiettivo e requisiti.** La firma digitale realizza nel cyberspazio l'analogo della firma su carta: collega un messaggio al suo autore in modo verificabile. I requisiti che deve soddisfare sono quattro:

1. **facile da produrre** per il legittimo firmatario, che dispone della propria informazione segreta;
2. **difficile da contraffare**: nessun altro deve poter produrre una firma valida al posto di Alice, nemmeno conoscendo messaggio, algoritmo e chiave pubblica;
3. **facile da verificare** — e da **chiunque**, non solo dal destinatario: altrimenti la firma non sarebbe utilizzabile come prova verso terzi;
4. **non riutilizzabile su un altro documento**: la firma di $M$ non deve valere per $M' \neq M$.

**Perché il MAC non basta.** Un **Message Authentication Code** garantisce integrità e autenticazione fra i soggetti che condividono la chiave $K$, ma fallisce su due requisiti. Primo, sul **non ripudio**: se Bob riceve $M, MAC_K(M)$ sa che il codice è stato prodotto da qualcuno che conosce $K$ — ma **Bob stesso conosce $K$**, quindi potrebbe averlo prodotto lui, e non può usarlo come prova verso un giudice o una terza parte. Secondo, sulla **verifica pubblica**: solo chi possiede la chiave condivisa può ricalcolare $MAC_K(M)$, quindi un terzo esterno non può verificare nulla. Occorre perciò una primitiva **asimmetrica**, in cui **chi verifica non possiede il segreto usato per firmare**.

**Funzionamento.** Alice possiede una coppia di chiavi: $K_{priv,A}$, nota solo a lei, e $K_{pub,A}$, disponibile a tutti. Poiché gli algoritmi asimmetrici sono computazionalmente costosi, non si firma l'intero messaggio ma il suo **digest**.

*Creazione della firma:*

1. Alice calcola il digest $h = H(M)$;
2. applica l'algoritmo di firma al digest con la propria chiave privata: $s = Sign_{K_{priv,A}}(h)$;
3. invia a Bob la coppia $M,\ s$ — con $M$ **in chiaro**.

*Verifica:*

1. Bob separa messaggio e firma;
2. usa la chiave pubblica di Alice per ricavare dalla firma il digest firmato: $h_1 = Verify_{K_{pub,A}}(s)$;
3. calcola **autonomamente** il digest del messaggio ricevuto: $h_2 = H(M)$;
4. confronta: se $h_1 = h_2$ la firma è valida, altrimenti il messaggio è stato alterato oppure la firma non è stata prodotta con la chiave privata corrispondente.

L'intuizione è che, se un valore si verifica correttamente con la chiave pubblica di Alice, deve essere stato prodotto con la chiave privata corrispondente; e poiché solo Alice dovrebbe possederla, la firma le viene attribuita.

**Proprietà garantite.**

| Proprietà | Perché è garantita |
|-----------|--------------------|
| **Integrità** | Il digest dipende dall'intero contenuto: se cambia anche un solo bit di $M$, $h_2 \neq h_1$ e la verifica fallisce |
| **Autenticazione dell'origine** | Solo chi possiede $K_{priv,A}$ può produrre una firma verificabile con $K_{pub,A}$ |
| **Non ripudio** | Bob **non conosce** la chiave privata di Alice, quindi non avrebbe potuto fabbricare la firma: può esibirla a una terza parte come prova. È la proprietà che distingue nettamente la firma dal MAC |
| **Verifica pubblica** | Chiunque possieda la chiave pubblica può verificare, senza conoscere alcun segreto |

**Proprietà NON garantite.** È la parte in cui si guadagna o si perde il punto pieno.

- **Confidenzialità.** Il messaggio $M$ viaggia **in chiaro** accanto alla firma: chiunque osservi il canale può leggerlo. È del tutto coerente con l'analogia cartacea — firmare un documento non lo rende segreto, lo rende attribuibile. Se serve anche la segretezza, occorre **combinare firma e cifratura** (firmare e poi cifrare con la chiave pubblica del destinatario, o con una chiave di sessione simmetrica in uno schema ibrido).
- **L'autenticità dell'associazione chiave pubblica ↔ identità.** L'algoritmo garantisce solo che *"questa firma è verificabile con questa chiave pubblica"*, **non** che *"questa chiave pubblica appartiene ad Alice"*. Se Trudy riesce a far credere a Bob che la propria chiave pubblica sia quella di Alice, Bob verificherà come "firma di Alice" una firma prodotta da Trudy: è il **Man in the Middle** visto nella costruzione dei protocolli di autenticazione. Il problema si risolve **fuori** dallo schema di firma, con i **certificati digitali** emessi da una **Certification Authority** e con la PKI.
- **Freschezza e protezione dal replay.** Una coppia $(M, s)$ resta valida per sempre: Trudy può registrarla e **rigiocarla** più tardi. La firma dice *chi* e *che cosa*, non *quando* e *in quale sessione*. Per ottenere la freschezza bisogna inserire **dentro il materiale firmato** un nonce, un timestamp o un numero di sequenza — esattamente come nei protocolli challenge-response.
- **La protezione della chiave privata.** Se $K_{priv,A}$ viene compromessa, l'attaccante produce firme valide indistinguibili da quelle di Alice, e cade retroattivamente anche il non ripudio (da cui l'importanza della **revoca** tramite CRL).
- **Disponibilità e veridicità del contenuto.** La firma non protegge dal DoS, e soprattutto non certifica che quanto affermato nel documento sia *vero*: certifica solo **chi lo ha sottoscritto** e che **non è stato alterato dopo**.

**Ruolo degli algoritmi di hash.** L'hash non è un dettaglio implementativo: svolge tre funzioni distinte.

1. **Efficienza.** Gli algoritmi asimmetrici sono lenti e operano su blocchi di dimensione limitata. La funzione hash $H:\{0,1\}^* \rightarrow \{0,1\}^n$ comprime un messaggio di lunghezza arbitraria in un digest di lunghezza fissa (128, 160, 256 bit), così l'operazione costosa con la chiave privata viene applicata **una sola volta a un input piccolo**, indipendentemente dalla dimensione del documento.
2. **Legame con l'intero contenuto.** Firmare il digest equivale a firmare tutto il messaggio, perché per l'**effetto valanga** una modifica anche di un solo bit cambia il digest in modo radicale e imprevedibile. L'alternativa ingenua — spezzare il messaggio in blocchi e firmarli separatamente — sarebbe sia inefficiente sia **insicura**, perché consentirebbe a un attaccante di riordinare, eliminare o riutilizzare blocchi firmati.
3. **Fondamento di sicurezza dello schema.** La robustezza della firma **si riduce** alle proprietà della funzione hash:
   - la **preimage resistance** (data $Y=H(X)$ è difficile trovare $X$) impedisce di risalire a un messaggio a partire dal digest recuperato da una firma;
   - la **second preimage resistance** (dato $M$ è difficile trovare $M' \neq M$ con $H(M')=H(M)$) impedisce di **trasferire** una firma esistente su un documento diverso;
   - la **collision resistance** (è difficile trovare **due** messaggi qualsiasi con lo stesso digest) è la proprietà critica contro il firmatario disonesto o contro l'attaccante che sceglie i messaggi: chi trova una coppia $(M_1, M_2)$ collidente può far firmare $M_1$ (innocuo) e poi esibire la **stessa firma** su $M_2$ (malevolo). Poiché per il **paradosso del compleanno** trovare collisioni costa circa $2^{n/2}$ tentativi anziché $2^n$, la lunghezza del digest va scelta di conseguenza.

> ⚠️ Conseguenza pratica: **MD5** e **SHA-1** sono considerati rotti rispetto alla collision resistance e non vanno usati per la firma; si usa la famiglia **SHA-2** (o SHA-3). Il caso storico delle CA che firmavano certificati con MD5 mostra esattamente questo attacco portato a termine nel mondo reale.

**Confronto conclusivo.**

| Proprietà | Hash senza chiave | MAC | Firma digitale |
|-----------|:-----------------:|:---:|:--------------:|
| Integrità | Sì, solo se il digest è conservato in modo protetto | Sì | Sì |
| Autenticazione dell'origine | No | Sì, fra chi condivide la chiave | Sì |
| Non ripudio | No | **No** | **Sì** |
| Verifica da parte di terzi | Solo se il digest è fidato | No | Sì |
| Confidenzialità | No | No | No |
| Segreto per **verificare** | Nessuno | Chiave condivisa | Nessuno (basta la chiave pubblica) |
| Segreto per **produrre** | Nessuno | Chiave condivisa | Chiave privata |

> ✅ **Sintesi da scrivere in chiusura:** la firma digitale si ottiene applicando la chiave privata del mittente al **digest** del messaggio e si verifica con la chiave pubblica corrispondente. Garantisce **integrità, autenticazione dell'origine, non ripudio e verificabilità pubblica**; **non** garantisce confidenzialità, né freschezza, né — da sola — che la chiave pubblica appartenga davvero al firmatario, problema risolto dai certificati e dalla PKI. La funzione hash serve a rendere l'operazione efficiente, a legare la firma all'intero contenuto e a fondarne la sicurezza: preimage, second preimage e soprattutto **collision resistance** sono i requisiti che rendono la firma non contraffabile né trasferibile.

---

### **4. Permessi `r`, `w`, `x` in Unix/Linux e Access Control List**

Nell'ambito dei sistemi Unix/Linux, spiegare il significato dei privilegi `r`, `w` e `x` per file e directory. Descrivere inoltre il funzionamento delle Access Control List (ACL) e le differenze rispetto ai permessi tradizionali.

> **Riferimenti di teoria**:
>
> - [M2/UD3/L2 — Controllo degli accessi in Linux](../M2_AccessControl&Authentication/UD3/L2/L2_Controllo_Accessi_Linux.md) (§3.1–3.3 permessi su file e directory, §3.4 lettura di `ls -al`, §3.5 ordine di valutazione, §4 notazione ottale, §5 privilegi addizionali, §6 `chmod`/`chown`/`umask`, §7.4 esercizio `chmod 444` su directory)
> - [M2/UD1/L3 — La matrice degli accessi](../M2_AccessControl&Authentication/UD1/L3_Matrice_Accessi.md) (§3 ACL come compressione **per colonne**, §4 capability list, §5 tabelle di autorizzazione, §6 i 9 bit come ACL semplificata)
> - [M2/UD1/L2 — DAC, MAC e RBAC](../M2_AccessControl&Authentication/UD1/L2_DAC_MAC_RBAC.md) (§2 politica discrezionaria e Trojan Horse)
> - [M2/domande_fineM2](../M2_AccessControl&Authentication/domande_fineM2.md) (§1.3, §1.22, §1.23, §2 esercizio sui permessi)

**Il modello.** Linux adotta una politica **discrezionaria (DAC)**: il **proprietario** di una risorsa decide chi può accedervi e in quale modalità. La decisione viene presa dal kernel confrontando **UID del soggetto**, **GID dei suoi gruppi**, **UID e GID della risorsa** e **bit di permesso** della risorsa. Ogni file e ogni directory hanno un utente proprietario e un gruppo proprietario, e nove bit di permesso divisi in tre classi — **user** (proprietario), **group** (gruppo proprietario), **others** (tutti gli altri) — ciascuna con i tre bit `r`, `w`, `x`.

**Significato di `r`, `w`, `x` sui file ordinari.**

| Permesso | Effetto su un file |
|:--------:|--------------------|
| `r` (read) | Consente di **leggere il contenuto** del file (`cat`, `less`, apertura in lettura) |
| `w` (write) | Consente di **modificare il contenuto** del file: sovrascriverlo, troncarlo, appenderlo |
| `x` (execute) | Consente di **eseguirlo** come programma o script. Per uno script serve anche `r`, perché l'interprete deve poterlo leggere |

**Significato di `r`, `w`, `x` sulle directory.** È il punto in cui si gioca la domanda, perché **la semantica non coincide** con quella dei file: una directory è un elenco di associazioni nome → inode, e i tre bit operano su quell'elenco.

| Permesso | Effetto su una directory |
|:--------:|--------------------------|
| `r` | Consente di **leggere l'elenco dei nomi** contenuti (`ls`). Senza `x`, però, si ottengono solo i nomi: non si possono leggere i metadati dei file né accedervi |
| `w` | Consente di **modificare il contenuto della directory**: creare, rinominare, cancellare voci. Ha effetto solo in presenza di `x` |
| `x` | Consente di **attraversare** la directory, cioè usarla in un percorso, entrarci con `cd` e accedere agli oggetti contenuti di cui si conosca il nome |

Le conseguenze operative da citare:

- **Senza `x` non si entra in una directory**, anche in presenza di `r`. È esattamente l'esercizio del docente: `mkdir d1; chmod 444 d1; cd d1` fallisce, perché `r--r--r--` permette di elencare i nomi ma non di attraversare. Serve almeno `chmod 500` (o `755`).
- **Con `x` ma senza `r`** (`--x`, tipicamente `711`) si ottiene una directory "cieca": non si può elencarne il contenuto, ma si può accedere a un file di cui si conosca esattamente il nome. È il modo standard di esporre una `public_html` senza rivelare l'elenco dei file.
- **Cancellare un file non richiede `w` sul file, ma `w` (+ `x`) sulla directory che lo contiene.** Si può quindi eliminare un file su cui non si ha alcun permesso — motivo per cui esiste lo **sticky bit**, che su una directory limita rimozione e rinomina al proprietario del file, al proprietario della directory e a `root` (è il caso di `/tmp`, `drwxrwxrwt`).
- Per accedere a `/a/b/c/file` serve `x` su **tutte** le directory del percorso.

**Notazione ottale e comandi.** I nove bit si raggruppano in tre cifre ottali, con $r=4$, $w=2$, $x=1$: `755` = `rwxr-xr-x`, `640` = `rw-r-----`, `700` = `rwx------`. Si gestiscono con `chmod` (simbolico `chmod g+w file` o numerico `chmod 640 file`; solo il proprietario o `root` possono modificarli), `chown` (cambio di proprietario), `chgrp` (cambio di gruppo) e `umask`, che definisce i permessi **da sottrarre** alla creazione: con `umask 022` un file nasce `644` e una directory `755`; con `umask 077` nascono `600` e `700`.

**Ordine di valutazione.** Il kernel applica **la prima classe applicabile e si ferma**, senza cercare il permesso più favorevole: se lo UID coincide con quello del proprietario usa **solo** i bit di *user*; altrimenti, se il soggetto appartiene al gruppo del file, usa **solo** quelli di *group*; altrimenti quelli di *others*. Un file `r--rwx---` di proprietà di alice è quindi, per alice, di sola lettura, anche se appartiene al gruppo che avrebbe `rwx`.

**Access Control List — il concetto.** Il termine ACL va inteso su due livelli, ed è opportuno mostrarli entrambi.

**(a) ACL come implementazione della matrice degli accessi.** Concettualmente una politica di controllo degli accessi è una **matrice** in cui le righe sono i soggetti, le colonne gli oggetti e la cella $(S_i, O_j)$ contiene i privilegi di $S_i$ su $O_j$. La matrice è però **grande e sparsa**, e memorizzarla esplicitamente è inefficiente. Le tre implementazioni compresse sono:

| Approccio | Struttura | Direzione |
|-----------|-----------|-----------|
| **Access Control List** | Per ogni **oggetto**, la lista dei soggetti con i relativi permessi: $\text{ACL}(O_j)=\{(S_i,p_{ij}) \mid p_{ij}\neq\emptyset\}$ | Per **colonne** |
| **Capability list** | Per ogni **soggetto**, la lista degli oggetti a cui può accedere: $\text{CAP}(S_i)=\{(O_j,p_{ij}) \mid p_{ij}\neq\emptyset\}$ | Per **righe** |
| **Tabella di autorizzazione** | Solo le triple non nulle $(S,O,p)$ — tipica dei DBMS | Solo celle non vuote |

L'ACL dà il **sommario immediato di chi può accedere a un certo oggetto**, ma rende **onerosa la revoca globale** di un soggetto (bisogna scorrere le ACL di tutti gli oggetti); la capability list è comoda per la **delega** ma rende onerosa la revoca di un permesso su un oggetto (bisogna scorrere tutti i soggetti).

In questa prospettiva, **i 9 bit `rwxrwxrwx` di Unix sono già una ACL**, ma una **ACL semplificata e compressa**, in cui l'elenco dei soggetti non è arbitrario ma ridotto a **tre sole categorie fisse**: proprietario, gruppo, resto del mondo.

**(b) ACL POSIX — l'estensione concreta.** Da qui nasce l'esigenza pratica: con tre sole classi non si può esprimere una politica come *"alice può leggere e scrivere, bob può solo leggere, carla non può accedere, il gruppo studenti legge"* senza creare un gruppo apposito per ogni combinazione. Le **ACL POSIX** (`getfacl` / `setfacl`) risolvono il problema permettendo di associare a ogni oggetto un **numero arbitrario di voci** per singolo utente e singolo gruppo:

```bash
getfacl report.txt                          # mostra l'ACL corrente
setfacl -m u:bob:r-- report.txt             # aggiunge una voce per l'utente bob
setfacl -m g:tutor:rw- report.txt           # aggiunge una voce per il gruppo tutor
setfacl -x u:bob report.txt                 # rimuove la voce di bob
setfacl -d -m g:studenti:r-x /srv/progetto  # ACL di default (ereditata) su una directory
```

L'output di `getfacl` ha la forma:

```text
# file: report.txt
# owner: alice
# group: studenti
user::rwx
user:bob:r--
group::r--
group:tutor:rw-
mask::rw-
other::---
```

Gli elementi caratteristici sono le voci **named user** e **named group** (i soggetti aggiuntivi), la **`mask`**, che definisce il tetto massimo dei permessi effettivi concessi alle voci diverse dal proprietario e da *others* (i permessi effettivi sono l'intersezione fra la voce e la mask, ed è la ragione per cui una voce può apparire concessa ma risultare inefficace), e le **ACL di default** su directory, che vengono **ereditate** dagli oggetti creati al loro interno. Un file dotato di ACL estesa viene segnalato da `ls -l` con un **`+`** in coda alla stringa dei permessi (`-rw-rw----+`). Le ACL sono memorizzate negli **attributi estesi** del filesystem e richiedono che questo le supporti (opzione di mount `acl`).

**Differenze rispetto ai permessi tradizionali.**

| Aspetto | Permessi tradizionali (9 bit) | ACL POSIX |
|---------|-------------------------------|-----------|
| Soggetti esprimibili | Solo tre classi fisse: owner, group, others | Numero arbitrario di utenti e gruppi nominati |
| Granularità | Grossolana: per esprimere un caso particolare si è costretti a creare gruppi ad hoc | Fine: eccezioni per singolo utente senza toccare la struttura dei gruppi |
| Ereditarietà | Assente: i permessi alla creazione dipendono da `umask` | ACL di **default** sulle directory, ereditate dai nuovi oggetti |
| Permessi effettivi | Coincidono con i bit | Limitati dalla **mask** |
| Visibilità | `ls -l` mostra tutto | `ls -l` mostra solo un `+`; serve `getfacl` per il dettaglio |
| Memorizzazione | Nell'inode | Negli attributi estesi; richiede supporto del filesystem |
| Portabilità e semplicità | Universali, semplici da leggere e verificare | Meno portabili, più complessi da auditare |

**Limiti che ACL e permessi condividono.** Vale la pena chiudere osservando che le ACL **aumentano la granularità ma non cambiano il modello**: restano un meccanismo **discrezionario**. Quindi (i) non controllano il **flusso dell'informazione dopo** che l'accesso è stato concesso, e restano perciò vulnerabili agli attacchi di tipo **Trojan Horse** — un programma eseguito con i privilegi di $S_1$ può leggere $F_1$ e scriverne il contenuto su $F_2$, accessibile a $S_2$, senza violare formalmente alcuna regola; (ii) `root` scavalca comunque tutti i controlli; (iii) la **revoca resta onerosa**, perché rispondere alla domanda *"a quali file può accedere alice?"* richiede di scorrere le ACL di tutti gli oggetti del filesystem (limite strutturale dell'organizzazione per colonne). Per superarli servono meccanismi di livello diverso: politiche **mandatorie** (SELinux, AppArmor) sovrapposte al DAC, e modelli **RBAC**.

> ✅ **Sintesi da scrivere in chiusura:** sui file `r`/`w`/`x` significano leggere il contenuto, modificarlo ed eseguirlo; sulle **directory** significano elencare i nomi, modificare l'elenco (creare/rinominare/cancellare) e **attraversare**, e senza `x` non si entra nella directory nemmeno avendo `r`. I 9 bit sono una **ACL semplificata a tre categorie fisse**; le **ACL POSIX** (`getfacl`/`setfacl`) estendono il modello con voci per singoli utenti e gruppi, una `mask` che limita i permessi effettivi e ACL di default ereditabili, guadagnando granularità al prezzo di maggiore complessità di gestione e audit — ma restando pur sempre un meccanismo discrezionario, incapace di controllare il flusso dell'informazione.

---

### **5. Apache: direttive host-based e user-based**

In Apache HTTP Server, descrivere le differenze tra direttive host-based e user-based. Riportare un esempio di utilizzo per ciascuna delle due tipologie.

> **Riferimenti di teoria**:
>
> - [M4/UD5/L1 — Introduzione ad Apache](../M4/UD5/L1/L1_Introduzione_ad_Apache.md) (§6 le due tipologie, §7 direttive host-based, §8 `Allow`, §9 `Deny`, §10 `Order`, §11 contenitori, §12 esempi, §13 ordine di valutazione, §5 `.htaccess`)
> - [M4/UD5/L2 — Apache: direttive user-based e autenticazione](../M4/UD5/L2/L2_Direttive_User_Based_e_Autenticazione_Apache.md) (§2–3 Basic e Digest, §5–10 direttive `Auth*`, §11 `require`, §12–13 esempi, §14 `Satisfy`, §15 `htpasswd`)
> - [M2/UD2/L1 — Introduzione all'autenticazione](../M2_AccessControl&Authentication/UD2/L1_Autenticazione_Intro.md) (autenticazione precede autorizzazione)
> - [M4/UD2/L1 — HTTP](../M4/UD2/L1.md) (metodi HTTP, header, codici di stato 401/403)

**La differenza di fondo.** Apache offre due famiglie di direttive per il controllo degli accessi alle risorse web, e la differenza sta in **che cosa viene valutato per prendere la decisione**:

- le direttive **host-based** decidono in base a **proprietà del richiedente come host**: indirizzo IP, nome di dominio, oppure il valore di una **variabile d'ambiente** derivata da un header HTTP (per esempio `User-Agent` o `Referer`). Rispondono alla domanda *"da dove arriva la richiesta?"*;
- le direttive **user-based** decidono in base all'**identità dell'utente**. Rispondono alla domanda *"chi è che sta chiedendo?"*.

Da qui discende la differenza operativa più importante: le direttive user-based **richiedono una fase di autenticazione**, quelle host-based no. Vale infatti il principio generale che **l'autenticazione precede l'autorizzazione**: prima si stabilisce chi è il soggetto, poi si decide se può accedere. Le host-based saltano del tutto la prima fase, perché non c'è alcun soggetto da identificare — decidono su un attributo della connessione.

**Tabella di confronto.**

| Aspetto | Host-based | User-based |
|---------|------------|------------|
| Criterio di decisione | IP, dominio, variabile d'ambiente da header | Identità dell'utente autenticato |
| Autenticazione richiesta | **No** | **Sì** (Basic o Digest) |
| Direttive (sintassi storica) | `Allow`, `Deny`, `Order` | `require valid-user`, `require user`, `require group` |
| Direttive (Apache 2.4) | `Require ip`, `Require host`, `Require not …` | `Require valid-user`, `Require user`, `Require group` |
| Direttive di supporto | `SetEnvIf`, `BrowserMatch` | `AuthType`, `AuthName`, `AuthBasicProvider`, `AuthUserFile`, `AuthGroupFile` |
| Impatto sull'utente | Nessuno: trasparente | Il browser presenta la finestra di login |
| Robustezza | Debole: l'IP è **falsificabile**, cambia con NAT, proxy, VPN, DHCP; l'`User-Agent` e il `Referer` sono header inviati dal client, quindi arbitrari | Più forte, ma esposta a furto/indovinamento delle credenziali; con **Basic** le credenziali viaggiano in **Base64**, che **non è cifratura** → serve HTTPS |
| Granularità | Per rete, host, dominio | Per singolo utente o gruppo |
| Uso tipico | Restringere un'area alla rete interna o all'intranet; bloccare host o domini indesiderati | Aree riservate, pannelli di amministrazione, materiale per soli iscritti |

**Direttive host-based — funzionamento.** Le direttive principali sono `Allow from`, `Deny from` e `Order`. Il valore di `host` può essere la parola chiave `all`, un nome di dominio completo (`mioserver.miodominio.it`) o parziale (`.org`), un indirizzo IP completo (`217.199.180.21`) o un pattern (`192.168.205`), una rete con maschera (`192.20.250.0/255.255.255.0`) oppure una variabile d'ambiente (`env=nome`).

La direttiva **`Order`** è la più insidiosa e va spiegata con precisione, perché stabilisce **l'ordine di valutazione** e con esso la politica di default:

- `Order Deny,Allow` → si valutano prima le `Deny`, poi le `Allow`; l'accesso è **permesso di default** (politica aperta salvo negazioni esplicite);
- `Order Allow,Deny` → si valutano prima le `Allow`, poi le `Deny`; l'accesso è **negato di default** (politica chiusa salvo autorizzazioni esplicite);
- `Order Mutual-failure` → accedono solo gli host presenti in `Allow` e **non** presenti in `Deny`.

> ⚠️ Non basta guardare `Order` per prevedere il risultato: bisogna considerare tutte le `Allow` e le `Deny` presenti. In `Order Deny,Allow` + `Deny from all` + `Allow from dominiofidato.it` la politica *di default* sarebbe aperta, ma `Deny from all` nega comunque tutti e solo `dominiofidato.it` viene riammesso.

**Esempio host-based.** *Requisito: i documenti riservati devono essere accessibili solo dalla rete interna `192.168.1.0/24`, con l'eccezione della macchina `192.168.1.66`, esclusa perché è un chiosco pubblico.*

```apacheconf
<Directory /var/www/html/documenti>
    Order Allow,Deny
    Allow from 192.168.1
    Deny  from 192.168.1.66
</Directory>
```

`Order Allow,Deny` imposta la **politica chiusa**: chi non è esplicitamente ammesso viene rifiutato con `403 Forbidden`. `Allow from 192.168.1` riapre l'intera sottorete interna; `Deny from 192.168.1.66` ne esclude il singolo host, e poiché le `Deny` sono valutate dopo, prevale. Nessun utente deve autenticarsi: la decisione dipende solo dall'IP sorgente. In sintassi Apache 2.4 la stessa politica si scrive:

```apacheconf
<Directory /var/www/html/documenti>
    <RequireAll>
        Require ip 192.168.1
        Require not ip 192.168.1.66
    </RequireAll>
</Directory>
```

**Direttive user-based — funzionamento.** Servono due gruppi di direttive. Quelle di **autenticazione** dicono ad Apache *come* e *dove* verificare l'identità: `AuthType` (`Basic` o `Digest`), `AuthName` (il **realm**, cioè l'etichetta mostrata nella finestra di dialogo del browser), `AuthBasicProvider` (la sorgente delle credenziali, `file` per impostazione predefinita), `AuthUserFile` (il file utenti/password, creato con `htpasswd`) e `AuthGroupFile` (il file dei gruppi). Quelle di **autorizzazione** sono le tre forme di `require`: `valid-user` (qualunque utente autenticabile), `user u1 u2` (elenco esplicito), `group g1 g2` (appartenenza a gruppi).

> ⚠️ Il file delle password e quello dei gruppi **non devono stare nel document tree**, altrimenti sarebbero scaricabili via browser.

**Esempio user-based.** *Requisito: l'area di amministrazione deve essere accessibile solo agli utenti `alice` e `bob`, che devono autenticarsi.*

```apacheconf
<Directory /var/www/html/cartella_nascosta>
    AuthType Basic
    AuthName "Area Riservata"
    AuthBasicProvider file
    AuthUserFile /usr/local/httpd/passwd/passwords
    Require user alice bob
</Directory>
```

Alla richiesta, Apache risponde con `401 Unauthorized`; il browser mostra la finestra di login con la dicitura *Area Riservata*; le credenziali vengono verificate contro il file delle password (creato con `htpasswd -c /usr/local/httpd/passwd/passwords alice`, poi `htpasswd … bob` per gli utenti successivi) e l'accesso è concesso solo se l'utente autenticato è `alice` o `bob`. Se gli autorizzati fossero molti, la forma preferibile sarebbe per gruppo:

```apacheconf
    AuthGroupFile /usr/local/httpd/passwd/groups
    Require group amministratori
```

con il file dei gruppi nella forma `amministratori: alice bob chiara`.

**Combinazione delle due tipologie.** Non sono mutuamente esclusive e possono coesistere nello stesso scope; il loro rapporto logico si specifica con **`Satisfy`**:

- **`Satisfy all`** → l'accesso richiede che siano soddisfatte **entrambe** le condizioni: bisogna provenire da un host ammesso **e** autenticarsi;
- **`Satisfy any`** → **basta una** delle due: si accede autenticandosi **oppure** provenendo da un host ammesso.

```apacheconf
Require valid-user
Order allow,deny
Allow from 192.168.1
Satisfy Any
```

Questa configurazione, tipica di una intranet, evita il login agli utenti già in rete interna e lo richiede a tutti gli altri. `Satisfy any` è naturalmente **più permissiva**: va usata solo quando è davvero accettabile che una sola condizione basti.

**Scope delle regole.** Entrambe le tipologie hanno effetto solo dove viene dichiarato uno **scope**: i contenitori `<Directory>` (directory del filesystem e sottodirectory), `<Files>` e `<FilesMatch>` (per nome del file o espressione regolare, indipendentemente dalla directory), `<Location>` (path dello spazio web, non del filesystem) e `<Limit>` (ristretto a determinati metodi HTTP). In un file `.htaccess` lo scope è implicito — la directory che lo contiene e le sue sottodirectory — ma occorre che la configurazione principale lo consenta con `AllowOverride` (almeno `AuthConfig` per le direttive di autenticazione). La valutazione parte dalla directory del file e risale fino alla radice, applicando il principio ***most specific takes precedence***, e l'ordine fra contenitori è `<Directory>`, `<Files>`, `<Location>`.

> ✅ **Sintesi da scrivere in chiusura:** le direttive **host-based** (`Allow`/`Deny`/`Order`, o `Require ip`/`host` in 2.4) filtrano in base a IP, dominio o variabili d'ambiente e **non richiedono autenticazione**; le **user-based** (`require valid-user`/`user`/`group`) filtrano in base all'identità dell'utente e **presuppongono** le direttive `Auth*` con un file di password creato via `htpasswd`. Le prime sono trasparenti ma deboli, perché l'IP è falsificabile; le seconde sono più robuste ma richiedono HTTPS, dato che l'autenticazione Basic trasmette le credenziali in Base64. Si combinano con `Satisfy all` (entrambe) o `Satisfy any` (una delle due).

---

### **6. Autenticazione a più fattori (MFA)**

Che cosa si intende per autenticazione a più fattori (Multi-Factor Authentication)? Descrivere le principali categorie di fattori di autenticazione, evidenziandone vantaggi e svantaggi.

> **Riferimenti di teoria**:
>
> - [M2/UD2/L1 — Introduzione all'autenticazione](../M2_AccessControl&Authentication/UD2/L1_Autenticazione_Intro.md) (§2 definizione, §4 i cinque elementi, §5 i tre fattori)
> - [M2/UD2/L2 — Autenticazione basata sulla conoscenza: password](../M2_AccessControl&Authentication/UD2/L2_Autenticazione_Password.md) (§2 memorizzazione, salt, §3 attacchi, §4 contromisure, §6 linee guida)
> - [M2/UD2/L3 — Autenticazione basata sul possesso: token](../M2_AccessControl&Authentication/UD2/L3_Autenticazione_Possesso.md) (§1 limite fondamentale, §2–4 carte e token, §5 OTP e RSA SecurID, §6 RFID)
> - [M2/UD2/L4 — Autenticazione biometrica](../M2_AccessControl&Authentication/UD2/L4_Autenticazione_Biometrica.md) (§2 proprietà ideali, §5 template, §6 FAR/FRR/EER, §7 attacchi)
> - [M2/UD2/L5 — Biometria: esempi e confronto](../M2_AccessControl&Authentication/UD2/L5_Biometria_Esempi_Confronto.md) (§4 confronto, §6 quale tecnica è migliore)
> - [M1/UD2/L2 — Proprietà di sicurezza](../M1_Intro_CyberSec/UD2_CybersecNotions/L2_Cybersec_Definition.md) (§6 l'anello più debole)

**Definizione.** L'**autenticazione** è il processo di verifica dell'identità di un utente (o di un sistema) che richiede accesso a una risorsa, ed è precondizione necessaria di qualunque controllo degli accessi corretto: senza autenticazione affidabile, sapere chi è autorizzato a fare cosa è inutile, perché il sistema non può distinguere l'utente legittimo dall'impostore.

Il processo di autenticazione utente → computer coinvolge cinque elementi: la **persona** da autenticare, la **caratteristica** che la contraddistingue, l'**amministratore** che gestisce le credenziali registrate, il **meccanismo di autenticazione** che verifica la caratteristica e il **meccanismo di autorizzazione** che entra in gioco dopo. La caratteristica distintiva è ciò che si chiama **fattore di autenticazione**.

Si parla di **autenticazione a più fattori (MFA)** quando il sistema richiede **due o più fattori appartenenti a categorie diverse**. La precisazione *"categorie diverse"* è essenziale: chiedere due password, o una password e la risposta a una domanda segreta, **non è MFA** — sono due istanze dello stesso fattore, vulnerabili allo stesso identico insieme di attacchi. Il caso a due fattori si indica come **2FA**.

**Il razionale.** Vale il principio dell'**anello più debole**: la robustezza di un sistema è quella del suo componente più fragile, e l'autenticazione è tipicamente quello. Combinando fattori di categorie diverse si ottiene che **la compromissione di un solo fattore non basta**: chi ruba la password deve comunque possedere fisicamente il token; chi ruba il token deve comunque conoscere il PIN. Gli attacchi ai singoli fattori sono infatti fra loro **indipendenti** — un attacco a dizionario non aiuta a rubare uno smart token, e il furto di un token non rivela una password — quindi le probabilità di successo si moltiplicano invece di sommarsi.

**Le tre categorie di fattori.**

| Categoria | Principio | Esempi |
|-----------|-----------|--------|
| **Qualcosa che l'utente conosce** (*knowledge*) | Un'informazione segreta nota solo all'utente | Password, PIN, passphrase, risposta a domanda segreta |
| **Qualcosa che l'utente possiede** (*possession*) | Un oggetto fisico detenuto dall'utente | Chiave fisica, carta magnetica, smart card, smart token OTP, RFID, smartphone con app di autenticazione |
| **Qualcosa che l'utente è** (*inherence*) | Una caratteristica biometrica univoca, fisica o comportamentale | Impronta digitale, iride, retina, geometria di volto o mano; voce, firma, andatura |

**1. Conoscenza — vantaggi e svantaggi.**

*Vantaggi.* È il meccanismo **più diffuso, economico e semplice da implementare**: non richiede hardware dedicato, funziona su qualunque canale, è **revocabile e sostituibile** istantaneamente (a differenza della biometria) e non pone problemi di privacy. Se compromessa, si cambia.

*Svantaggi.* È anche il **più debole**. È esposta a: **attacco a forza bruta** (prova sistematica di tutte le stringhe), **attacco a dizionario** (che sfrutta la tendenza a scegliere password brevi e legate a informazioni personali), **password guessing**, **shoulder surfing**, **sniffing** se il canale non è cifrato, **trojan login** che simula l'interfaccia di autenticazione, e **social engineering**. Le cause strutturali di vulnerabilità sono note: la password resta invariata a lungo, viene condivisa, è debole, viene riusata su servizi diversi o — se complessa — scritta su carta. Sul lato server, le password non vanno mai memorizzate in chiaro: si conserva $H(\text{salt} \Vert \text{password})$, dove il **salt** è un valore casuale univoco per utente, memorizzato in chiaro, che elimina la coincidenza degli hash fra utenti con la stessa password e vanifica le tabelle precalcolate. Le contromisure classiche sono limitare i tentativi falliti, imporre complessità e cambio periodico, usare le **shadow password** per sottrarre gli hash alla lettura degli utenti comuni. Resta il limite insuperabile: **chiunque conosca la password può impersonare completamente l'utente**, e il sistema non ha modo di accorgersene.

**2. Possesso — vantaggi e svantaggi.**

*Vantaggi.* È **difficile estrarre il segreto** da un token fisico, soprattutto se dotato di coprocessore crittografico come una smart card. Le **One-Time Password** neutralizzano di colpo sniffing, replay e riuso: la password vale una sola volta e per un intervallo limitato. Il meccanismo OTP si basa su tre elementi condivisi fra token e server — una **chiave segreta (seme)** inserita dal produttore, un **algoritmo di generazione** identico sui due lati e un'**informazione esterna variabile**, tipicamente l'ora corrente o un contatore: token e server calcolano indipendentemente lo stesso valore nello stesso intervallo. L'esempio di riferimento è l'**RSA SecurID** usato dalle banche, che realizza già di per sé una **2FA**: il PIN segreto (conoscenza) più il codice a 6 cifre rigenerato dal token ogni 60 secondi (possesso). Un'altra funzione dei token è custodire **credenziali digitali**, cioè chiavi crittografiche per firma e comunicazioni sicure, sbloccate da una password (per esempio il liToken Pro su porta USB).

*Svantaggi.* Il limite **fondamentale** è che si autentica **il token, non l'utente**: chiunque lo possegga può impersonarlo, esattamente come chi ruba le chiavi di casa può entrarvi. Da qui la regola pratica di **abbinarlo sempre a un secondo fattore** (PIN o password). Inoltre: le carte magnetiche hanno memoria limitata e sono **clonabili** con dispositivi reperibili sul mercato; la gestione di **furto o smarrimento** è complessa, e nel periodo transitorio fino all'emissione del sostitutivo l'utente legittimo **non può autenticarsi**; c'è un costo di distribuzione e gestione del parco token; i token sincroni richiedono orologi allineati. Se il "possesso" è realizzato via **SMS**, si aggiungono le vulnerabilità del canale (intercettazione, SIM swap).

**3. Biometria — vantaggi e svantaggi.**

*Vantaggi.* Risolve alla radice i problemi delle altre due categorie: **non può essere dimenticata, indovinata con attacchi a dizionario, sottratta con social engineering, persa o prestata**. È intrinsecamente legata all'identità biologica dell'utente, ed è comodissima per l'utente finale (nessuna memorizzazione, nessun oggetto da portare). Una caratteristica biometrica ideale dovrebbe soddisfare **universalità, unicità, stabilità nel tempo, rilevabilità, accettabilità e difficoltà di contraffazione**; nel confronto fra impronta digitale, geometria della mano e riconoscimento facciale, l'**impronta digitale** risulta complessivamente vincente ed è per questo la più diffusa.

*Svantaggi.* Il nodo tecnico è che **la perfetta uguaglianza fra due acquisizioni è impossibile**: la stessa impronta letta due volte produce immagini leggermente diverse (pressione, angolazione, condizioni della pelle). Serve quindi una fase di **enrollment** con misurazioni multiple per costruire un **template**, e al login si accetta l'utente se la distanza fra campione e template è sotto una **soglia di tolleranza** $\delta$:

$$d(\text{campione}_{nuovo},\ \text{template}_{utente}) \leq \delta \Rightarrow \text{autenticato}$$

La calibrazione di $\delta$ genera un trade-off inevitabile fra due metriche: il **False Acceptance Rate (FAR)**, probabilità di accettare un impostore, e il **False Rejection Rate (FRR)**, probabilità di respingere un utente legittimo. Alzando la tolleranza cresce il FAR (si accettano impostori), abbassandola cresce il FRR (si respingono utenti legittimi); il punto di lavoro di riferimento è l'**Equal Error Rate (EER)**, dove FAR = FRR. Gli altri svantaggi: **sensori costosi**; **accettazione non universale** da parte degli utenti, che la percepiscono come intrusiva; problemi di **privacy** aggravati dal fatto che un dato biometrico è **permanente e non revocabile** — una password compromessa si cambia, un'impronta compromessa no; **non è universale** (alcune persone non possiedono o non possono usare una data caratteristica); ed è esposta ad attacchi specifici: **contraffazione** della caratteristica (dito in gelatina, lente a contatto, maschera), **replay** di un template intercettato, attacchi al **canale sensore–database**, **modifica del template** memorizzato e **override della decisione finale**. Va infine distinta l'**autenticazione 1:1** (confronto con il solo template dichiarato) dall'**identificazione 1:N** (confronto con tutti i template), molto più esposta ai falsi match.

**Vantaggi e svantaggi della MFA nel suo complesso.**

*Vantaggi.* Aumenta significativamente la robustezza dell'autenticazione perché un solo fattore compromesso non basta; neutralizza le classi di attacco più diffuse (phishing di sole password, dizionario, riuso di credenziali fra servizi, database di password trafugati); permette di **compensare i difetti di ciascun fattore con i pregi degli altri**. La combinazione tecnicamente ottimale indicata a lezione è: **autenticazione biometrica fra utente e token** (il token si sblocca con l'impronta, così il token non è più utilizzabile da chi lo ruba) e **autenticazione mutua crittografica fra token e sistema** — un esempio reale è la smart card con impronta digitale integrata usata dagli atleti tedeschi alle Olimpiadi di Atene 2004.

*Svantaggi.* Costo di distribuzione e gestione; **attrito di usabilità**, che può indurre gli utenti ad aggirare la misura; problema del **recupero** in caso di perdita del secondo fattore, che spesso reintroduce un canale debole (domande segrete, email di reset) vanificando la protezione; rischio di un nuovo **single point of failure** se il secondo fattore è concentrato sullo smartphone; e attacchi specifici come la **MFA fatigue** (bombardamento di notifiche di conferma fino all'approvazione distratta) o il **relay in tempo reale** dell'OTP tramite un sito di phishing che fa da proxy verso il servizio legittimo.

> 💡 **Osservazione conclusiva.** Dal punto di vista puramente tecnico la biometria è la tecnica più robusta; dal punto di vista pratico le **password restano il meccanismo più usato**, e probabilmente lo resteranno, per il classico trade-off costi/benefici. La MFA è precisamente il modo di conciliare le due prospettive: si tiene la password per economicità e universalità, e le si affianca un fattore di categoria diversa per coprirne le debolezze.

> ✅ **Sintesi da scrivere in chiusura:** la MFA richiede due o più fattori appartenenti a **categorie diverse** — conoscenza (password, PIN: economica e revocabile ma esposta a dizionario, sniffing, social engineering), possesso (smart card, token OTP: resiste a replay e sniffing ma autentica il token e non l'utente, ed è soggetto a furto e clonazione), biometria (non dimenticabile né prestabile, ma costosa, non revocabile, soggetta al trade-off FAR/FRR e a contraffazione). La forza dell'MFA sta nel fatto che gli attacchi ai diversi fattori sono indipendenti, quindi la compromissione di uno solo non è sufficiente; i costi sono in usabilità, gestione e procedure di recupero.

---

### **7. Challenge-response basato su nonce**

Si consideri un protocollo di autenticazione di tipo challenge-response basato su nonce. Descrivere il protocollo, spiegando:

- perché risulta resistente agli attacchi di tipo replay;
- come modificarlo per ottenere la mutua autenticazione;
- quale ruolo svolge il nonce nella sicurezza del protocollo.

> **Riferimenti di teoria**:
>
> - [M3/UD3/L4 — Protocolli challenge-response](../M3/UD3/L4.md) (§2 idea generale, §3 freshness, §4 caso simmetrico, §5 il nonce, §6 le tre varianti, §12 mutua autenticazione, §13 confronto fra meccanismi di freshness, §14 errori comuni)
> - [M3/UD3/L2 — Protocollo di autenticazione unilaterale](../M3/UD3/L2.md) (§4–8 costruzione incrementale e replay, §9 challenge-response, §11–13 versione a chiave pubblica e certificati)
> - [M3/UD3/L3 — Attacchi comuni ai protocolli](../M3/UD3/L3.md) (replay, freshness, sessioni parallele, reflection, MITM)
> - [M3/UD3/L1 — Notazione per protocolli crittografici](../M3/UD3/L1.md) (freschezza, chiavi a lungo e breve termine, notazione)
> - [M3/UD3/L7 — Principi di progettazione dei protocolli](../M3/UD3/L7.md) (principi di Abadi-Needham: esplicitare identità e contesto)

**Il protocollo.** Un protocollo challenge-response è un **test di autenticazione**: verifica se un partecipante possiede una certa informazione segreta, **senza che il segreto venga trasmesso**. Coinvolge due ruoli — il **challenger**, che richiede l'autenticazione, e il **responder**, che deve autenticarsi — e si articola in due passi: il challenger invia una sfida, il responder restituisce una risposta che dipende sia dalla sfida sia dal segreto.

Nella versione con **nonce** e **chiave simmetrica condivisa** $K_{AB}$, in cui Alice vuole autenticare Bob:

$$1.\ A \rightarrow B : N_A$$

$$2.\ B \rightarrow A : \{N_A\}_{K_{AB}}$$

Alice genera un **nonce** $N_A$ — una sequenza casuale di bit, tipicamente da 32 a 128 — lo memorizza temporaneamente e lo invia **in chiaro**. Bob lo cifra con la chiave condivisa e lo restituisce. Alice decifra il response e verifica che il valore ottenuto coincida **esattamente** con il nonce appena generato. Se coincide, conclude che chi ha risposto conosce $K_{AB}$ e ha elaborato *quella* sfida: poiché $K_{AB}$ è nota solo ad Alice e Bob, Alice autentica Bob.

Due punti concettuali:

- **Il segreto non viene trasmesso.** Bob non invia $K_{AB}$ ma un valore derivato $f(\text{challenge}, \text{segreto})$. Trudy osserva sia $N_A$ sia $\{N_A\}_{K_{AB}}$, ma non ricava la chiave e non può produrre risposte corrette per sfide nuove.
- **Il challenge può essere pubblico.** Non è la segretezza della sfida ad autenticare Bob, ma la sua **capacità di trasformarla** con la chiave giusta. La funzione $f$ può infatti essere realizzata con cifratura simmetrica, MAC, firma digitale o decifratura, senza che la logica cambi.

Le varianti viste a lezione sono tre: *(1)* challenge in chiaro e response cifrato — quella appena descritta; *(2)* challenge cifrato $\{N_A\}_{K_{AB}}$ e response in chiaro $N_A$, in cui la prova sta nell'aver **recuperato** un nonce inizialmente nascosto (funziona proprio perché $N_A$ è casuale e Trudy non può prevederlo); *(3)* challenge e response entrambi cifrati, che richiede però una precauzione, discussa più avanti.

**Perché resiste agli attacchi di replay.** Un **replay attack** consiste nel registrare un messaggio valido e reinviarlo in una sessione successiva. I protocolli ingenui vi soccombono perché il messaggio di autenticazione è **statico**: se Alice invia sempre `A, P_A`, o anche `A, {P_A}_K`, Trudy registra quella coppia e la ripropone identica; il verificatore non può distinguere l'originale dalla copia. Cifrare non risolve: **un cifrato costante diventa esso stesso una credenziale riutilizzabile**.

Il challenge-response con nonce introduce la **freschezza (freshness)**. Sia $\{N_{old}\}_{K_{AB}}$ una risposta registrata da Trudy in una sessione precedente. Nella sessione corrente Alice **non invia** $N_{old}$: genera un nonce nuovo $N_A$ e attende esattamente $\{N_A\}_{K_{AB}}$. La vecchia risposta, decifrata, restituisce $N_{old} \neq N_A$ e viene rifiutata. Perché l'attacco riuscisse, Trudy dovrebbe **produrre la risposta corretta per un valore mai visto prima**, e per farlo dovrebbe conoscere $K_{AB}$.

La resistenza al replay poggia dunque su due condizioni, entrambe da enunciare:

1. il nonce è **casuale e imprevedibile**, quindi Trudy non può precalcolare la risposta prima di riceverlo;
2. il nonce **non viene mai riutilizzato**: se $N_A$ si ripetesse, una vecchia risposta tornerebbe valida e il protocollo ricadrebbe nel caso statico.

Un dettaglio operativo che qualifica la risposta: **la freschezza deve essere verificata da chi ha generato il nonce**. Bob non deve memorizzare i nonce ricevuti — gli basta usarli per rispondere; è Alice che confronta il response con il valore in memoria. Questo rende i nonce leggeri da gestire: non richiedono né orologi sincronizzati né stato condiviso.

> ⚠️ **Attenzione alla variante 3.** Se challenge e response fossero entrambi $\{N_A\}_{K_{AB}}$, Trudy potrebbe limitarsi a **riflettere** verso Alice lo stesso valore cifrato appena intercettato, senza decifrare nulla e senza conoscere la chiave. Per questo il response deve contenere una **trasformazione riconoscibile** del nonce, tipicamente $2.\ B \rightarrow A : \{N_A + 1\}_{K_{AB}}$: solo chi ha davvero decifrato il challenge può incrementare il nonce e ricifrarlo.

**Come ottenere la mutua autenticazione.** Il protocollo descritto è **unilaterale**: al termine Alice ha autenticato Bob, ma Bob non sa nulla del suo interlocutore, perché chiunque può generare un numero casuale e spedirlo. Per la mutua autenticazione occorre eseguire il test **in entrambe le direzioni**. La forma più compatta usa **due nonce** e tre messaggi:

$$1.\ A \rightarrow B : N_A$$

$$2.\ B \rightarrow A : N_B,\ \{N_A\}_{K_{AB}}$$

$$3.\ A \rightarrow B : \{N_B\}_{K_{AB}}$$

Il secondo messaggio fa doppio lavoro: Bob **risponde** alla sfida di Alice e contemporaneamente **lancia la propria** (*piggybacking*), così il costo è di un solo messaggio in più. Alice verifica $\{N_A\}_{K_{AB}}$ e autentica Bob; poi risponde a $N_B$; Bob verifica $\{N_B\}_{K_{AB}}$ e autentica Alice.

> ⚠️ **La mutua autenticazione non è "eseguire due volte lo stesso protocollo".** Se i messaggi delle due direzioni sono troppo simmetrici si apre la porta ai **reflection attack**: Trudy, che vuole autenticarsi come Alice presso Bob, apre una **seconda sessione parallela con Bob stesso** e gli gira la sfida $N_B$ appena ricevuta, facendosi calcolare da lui $\{N_B\}_{K_{AB}}$, che poi rispedisce nella prima sessione. Bob finisce per **autenticare se stesso** credendo di autenticare Alice.

La contromisura è **legare ogni messaggio al proprio contesto**, includendo nel materiale autenticato almeno l'**identità**, e possibilmente ruolo, direzione, fase e identificativo di sessione:

$$2.\ B \rightarrow A : N_B,\ \{N_A,\ B\}_{K_{AB}} \qquad 3.\ A \rightarrow B : \{N_B,\ A\}_{K_{AB}}$$

Ora una risposta valida in una direzione non è più riutilizzabile nell'altra, perché contiene l'identità sbagliata. Alternative equivalenti: usare **chiavi distinte per verso**, oppure imporre trasformazioni asimmetriche ai due messaggi. È lo stesso principio di progettazione — *ogni messaggio deve dire esplicitamente a chi è destinato e in quale contesto vale* — che ricorre nella correzione di Lowe al Needham-Schroeder a chiave pubblica e nei principi di Abadi-Needham.

**Il ruolo del nonce.** Riassumendo in modo esplicito, come chiede la traccia, il nonce svolge **quattro funzioni**:

1. **Garantisce la freschezza.** Trasforma l'autenticazione dall'invio di una **credenziale statica** alla **risposta a una sfida fresca**: la risposta vale solo per *questa* esecuzione del protocollo. È ciò che distingue "qualcuno conosce il segreto" da "qualcuno conosce il segreto **adesso**".
2. **Impedisce il replay**, per costruzione: essendo generato ex novo a ogni esecuzione, rende inutilizzabile qualunque risposta registrata in passato.
3. **Impedisce il precalcolo**, grazie all'**imprevedibilità**. È qui che il nonce si distingue dal timestamp: un timestamp è **prevedibile**, e infatti con i timestamp non tutte le varianti restano lecite — la variante *challenge cifrato / response in chiaro*, sicura con un nonce, diventa **insicura** con un timestamp, perché Trudy può stimare l'ora corrente e inviare il valore in chiaro fingendosi Bob.
4. **Individua la sessione.** Il nonce lega la risposta all'esecuzione corrente e, se inserito nel materiale autenticato insieme alle identità, contribuisce a distinguere sessioni parallele, ostacolando reflection attack e attacchi con sessioni concorrenti.

Il nonce **non è un segreto**: può viaggiare in chiaro. È un valore **temporaneo, casuale e usato una sola volta**, e tutta la sua utilità sta in queste tre proprietà. Le alternative per ottenere la freschezza — timestamp (leggeri ma prevedibili e dipendenti dalla sincronizzazione degli orologi, con necessità di finestre di accettazione e memoria dei valori recenti), numeri di sequenza (senza orologi ma con stato persistente, legato a partecipanti, direzione e sessione, e soggetto a desincronizzazione), chiavi a breve termine (che uniscono autenticazione e distribuzione di chiave di sessione) — hanno ciascuna il proprio compromesso: **non esiste una scelta universalmente migliore**, dipende da costo, stato disponibile, sincronizzazione e obiettivo del protocollo.

**Nota finale sulla variante a chiave pubblica.** Sostituendo la cifratura simmetrica con la firma digitale si ottiene

$$1.\ A \rightarrow B : A \qquad 2.\ B \rightarrow A : N_B \qquad 3.\ A \rightarrow B : [N_B]_{K_A^-}$$

che elimina il vincolo pratico della versione simmetrica — la necessità che $K_{AB}$ sia già stata condivisa attraverso un canale sicuro che, per ipotesi, non esiste. Resta però l'assunzione che Bob possieda in modo **autentico** la chiave pubblica di Alice: se la richiede sul canale insicuro, Trudy la sostituisce con la propria e realizza un **Man in the Middle**, firmando lei stessa il nonce. La correzione è inviare non la chiave nuda ma il **certificato** $C_A$, verificandone firma della CA, validità temporale e catena di fiducia.

> ✅ **Sintesi da scrivere in chiusura:** nel challenge-response con nonce il verificatore invia un valore casuale fresco e il richiedente dimostra di conoscere il segreto restituendone una trasformazione, senza mai trasmettere il segreto. Il replay è impossibile perché il nonce è **imprevedibile e mai riutilizzato**, quindi ogni risposta registrata è legata a una sfida ormai obsoleta. Per la mutua autenticazione si esegue il test in entrambe le direzioni con due nonce distinti (tre messaggi, con piggyback), includendo le **identità** nel materiale autenticato per prevenire i reflection attack. Il nonce fornisce freschezza, anti-replay, imprevedibilità contro il precalcolo e identificazione della sessione — e per farlo non deve essere segreto, ma solo casuale e usato una sola volta.

---

## Nota metodologica

Questo appello e quello del **10 luglio 2026** condividono sei domande su sette. Per la matrice completa degli argomenti ricorrenti nei tre appelli disponibili, la classifica delle priorità di ripasso e le trappole tipiche del docente, si veda la [Guida al ripasso delle ultime ore](Guida_Ripasso_Ultime_Ore.md).
