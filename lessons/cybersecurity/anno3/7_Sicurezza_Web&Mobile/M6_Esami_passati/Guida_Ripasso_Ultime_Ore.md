# Guida al ripasso delle ultime ore — Sicurezza dei Sistemi Web e Mobili

**Docente: Chiara Braghin** · A.A. 2025/26 · Appello di settembre 2026

> 📌 Questo documento risponde a due domande: **che cosa chiede più spesso la docente** e **dove concentrarsi nelle ultime ore**. È costruito sull'analisi dei tre appelli disponibili — **16/06/2026**, **03/07/2026**, **10/07/2026** — incrociata con la copertura del materiale dei cinque moduli di teoria.

> ⚠️ **Onestà statistica.** Il campione è di **soli 3 appelli**, e due di essi (3 e 10 luglio) sono **quasi identici**. Le regolarità che seguono sono quindi indizi forti sulla *struttura* dell'esame, ma **non una previsione**: usarle per stabilire le **priorità**, mai per decidere che cosa **saltare del tutto**.

---

### **1. Il formato dell'esame**

| Voce | Valore |
|------|--------|
| Durata | **1 ora e 30 minuti** |
| Numero di domande | **7**, tutte obbligatorie |
| Tipologia | Domande aperte, **solo teoria discorsiva** (nessun esercizio di laboratorio nelle tracce disponibili) |
| Consegna letterale | *"Rispondere **brevemente** ma in modo **completo**"* |
| Tempo per domanda | **~12–13 minuti** |
| Lunghezza attesa | **250–350 parole** a risposta, cioè circa **mezza facciata** |

> 💡 La formula *"brevemente ma in modo completo"* è la chiave della strategia: la docente **non** vuole tre pagine per domanda. Vuole che, in mezza facciata, ci siano **tutti gli elementi richiesti dalla traccia**, ciascuno riconoscibile. Le domande a sotto-punti espliciti (le domande 3 e 7 dei due appelli di luglio) vanno risposte **rispettando i sotto-punti**, uno per capoverso: è il modo più semplice per far vedere che nessuna parte è stata dimenticata.

---

### **2. Matrice degli argomenti nei tre appelli**

| # | 16/06/2026 | 03/07/2026 | 10/07/2026 | Modulo |
|:-:|------------|------------|------------|--------|
| **1** | SQL injection: condizioni client/server e contromisure | **Kerberos**: ruolo di AS e TGS, perché è SSO | **Kerberos**: ruolo di AS e TGS, perché è SSO | M4/UD3 · M4/UD6 |
| **2** | Proprietà di sicurezza; confidenzialità vs privatezza vs anonimato | **CSRF**: realizzazione e contromisure | **CSRF**: realizzazione e contromisure | M1/UD2 · M4/UD2+UD4 |
| **3** | **Certificato digitale**: cos'è, a cosa serve, creazione e uso | **Firma digitale**: cosa garantisce / non garantisce / ruolo dell'hash | **Firma digitale**: idem | M3/UD2 |
| **4** | **Unix**: `-rwxr-----`, chi può fare cosa + setuid/setgid | **Unix**: `r`/`w`/`x` su file e directory + **ACL** | **Unix**: **DAC** e suoi **limiti** | M2/UD1 · M2/UD3 |
| **5** | **Apache**: che regole si scrivono con le direttive user-based | **Apache**: host-based **vs** user-based + un esempio ciascuna | **Apache**: idem | M4/UD5 |
| **6** | **Reflected XSS** e contromisure | **MFA**: categorie di fattori, vantaggi e svantaggi | **MFA**: idem | M4/UD4 · M2/UD2 |
| **7** | **Challenge-response** (nonce o timestamp): no-replay + mutua autenticazione | **Challenge-response con nonce**: no-replay + mutua + ruolo del nonce | idem | M3/UD3 |

---

### **3. La scoperta più importante: l'esame ha uno scheletro fisso a 7 slot**

Guardando la matrice **per riga** anziché per colonna, emerge che ogni posizione della traccia corrisponde a un'**area tematica stabile**, dentro la quale la domanda ruota:

| Slot | Area tematica | Che cosa è uscito |
|:----:|---------------|-------------------|
| **1** | Autenticazione distribuita **oppure** attacco web | Kerberos/SSO (2×), SQL injection (1×) |
| **2** | Attacco web **oppure** fondamenti | CSRF (2×), proprietà di sicurezza (1×) |
| **3** | **Crittografia applicata (M3/UD2)** — sempre | Firma digitale (2×), certificato digitale (1×) |
| **4** | **Unix/Linux, controllo degli accessi (M2)** — sempre | Permessi + setuid/setgid, `rwx` + ACL, DAC + limiti |
| **5** | **Apache (M4/UD5)** — sempre | User-based, host-based vs user-based (2×) |
| **6** | Autenticazione **oppure** attacco web | MFA (2×), Reflected XSS (1×) |
| **7** | **Protocolli challenge-response (M3/UD3)** — sempre | Nonce/timestamp, replay, mutua autenticazione |

**Conclusioni operative:**

- **Quattro slot su sette sono praticamente garantiti**: crittografia applicata (3), Unix (4), Apache (5), protocolli (7). Da soli valgono **oltre metà dell'esame**.
- Gli slot **1, 2 e 6** pescano da un bacino ristretto e ricorrente: Kerberos/SSO, CSRF, XSS, SQLi, MFA, proprietà CIA.
- Lo slot **4** è **sempre Unix**, ma cambia l'angolazione ogni volta (permessi concreti → semantica `rwx` + ACL → modello DAC + limiti): va studiato **sapendolo raccontare da tre punti di vista diversi**, non memorizzando una singola risposta.
- Lo slot **7** è **sempre lo stesso identico contenuto**, con gli stessi sotto-punti: è la domanda con il miglior rapporto tra sforzo e resa dell'intero esame.

---

### **4. Classifica delle priorità di ripasso**

#### **Fascia A — praticamente certe. Da sapere alla perfezione.**

| Argomento | Lezione | Perché |
|-----------|---------|--------|
| **Challenge-response, nonce, replay, mutua autenticazione** | [M3/UD3/L4](../M3/UD3/L4.md) · [L2](../M3/UD3/L2.md) | 3/3 appelli, sempre con gli stessi sotto-punti |
| **Apache: host-based vs user-based, `Allow`/`Deny`/`Order`, `Auth*`, `require`, `Satisfy`** | [M4/UD5/L1](../M4/UD5/L1/L1_Introduzione_ad_Apache.md) · [L2](../M4/UD5/L2/L2_Direttive_User_Based_e_Autenticazione_Apache.md) | 3/3 appelli. **Servono esempi di codice scritti a mano** |
| **Unix: permessi `rwx` su file e directory, ottale, ordine di valutazione, setuid/setgid/sticky, DAC e limiti, ACL** | [M2/UD3/L2](../M2_AccessControl&Authentication/UD3/L2/L2_Controllo_Accessi_Linux.md) · [M2/UD1/L2](../M2_AccessControl&Authentication/UD1/L2_DAC_MAC_RBAC.md) · [M2/UD1/L3](../M2_AccessControl&Authentication/UD1/L3_Matrice_Accessi.md) | 3/3 appelli, con angolazioni diverse |
| **Firma digitale + funzioni hash + MAC + certificati digitali** | [M3/UD2/L5](../M3/UD2/L5.md) · [L4](../M3/UD2/L4.md) · [L6](../M3/UD2/L6.md) | 3/3 appelli (slot 3). Studiare i tre insieme: si tengono a vicenda |

#### **Fascia B — molto probabili. Da sapere bene.**

| Argomento | Lezione |
|-----------|---------|
| **Kerberos: AS, TGS, TGT, ticket, tre fasi, perché è SSO** | [M4/UD6/L2](../M4/UD6/L2.md) · [L1](../M4/UD6/L1.md) |
| **CSRF: meccanismo e contromisure** ⚠️ *non coperto dalle lezioni* | vedi §8 |
| **MFA: tre categorie, vantaggi/svantaggi, FAR/FRR/EER, OTP** | [M2/UD2/L1](../M2_AccessControl&Authentication/UD2/L1_Autenticazione_Intro.md) → [L5](../M2_AccessControl&Authentication/UD2/L5_Biometria_Esempi_Confronto.md) |
| **XSS reflected e stored + contromisure + Same Origin Policy** | [M4/UD4/L2](../M4/UD4/L2.md) · [L1](../M4/UD4/L1.md) |
| **SQL injection: condizioni e contromisure** | [M4/UD3/L2](../M4/UD3/L2.md) · [L3](../M4/UD3/L3.md) |
| **Proprietà di sicurezza: CIA + autenticazione + non ripudio; confidenzialità ≠ privatezza ≠ anonimato** | [M1/UD2/L2](../M1_Intro_CyberSec/UD2_CybersecNotions/L2_Cybersec_Definition.md) |

#### **Fascia C — nel programma, mai usciti nei tre appelli. Da saper impostare in 5 righe.**

Sono i candidati più credibili per una **variazione** negli slot 1, 2 o 6. Non serve padroneggiarli come quelli di fascia A: serve non restare in bianco.

| Argomento | Lezione | Nucleo da ricordare |
|-----------|---------|---------------------|
| **SAML e Web Browser SSO** | [M4/UD6/L3](../M4/UD6/L3.md) | Standard OASIS su XML; principal, IdP, SP; asserzioni (authentication/attribute/authorization); protocolli, binding, profili; il caso Google Apps; MITM sulla variante semplificata |
| **Cookie: attributi, poisoning, tracking** | [M4/UD2/L2](../M4/UD2/L2.md) · [L3](../M4/UD2/L3.md) | HTTP stateless; `Set-Cookie`/`Cookie`; `Domain`, `Path`, `Expires`, `Secure`, `HttpOnly`; sessione vs persistenti; first vs third-party; poisoning e carrello; campi `hidden` |
| **PGP e sicurezza della posta elettronica** | [M4/UD7/L2](../M4/UD7/L2.md) · [L1](../M4/UD7/L1.md) | Sistema **ibrido**; hash+firma, ZIP, ASCII armor; keyring; **web of trust** vs PKI X.509; Zimmermann; MUA/MTA, SMTP/POP3/IMAP/MIME |
| **Needham-Schroeder e attacchi ai protocolli** | [M3/UD3/L5](../M3/UD3/L5.md) · [L6](../M3/UD3/L6.md) · [L3](../M3/UD3/L3.md) | Simmetrico: KDC, ticket, **attacco Denning-Sacco** → timestamp (Kerberos). Asimmetrico: **attacco di Lowe** con sessioni parallele → inserire l'identità di Bob nel messaggio critico |
| **Principi di progettazione (Abadi-Needham)** | [M3/UD3/L7](../M3/UD3/L7.md) | Esplicitare identità e contesto in ogni messaggio; sapere **a che scopo** si cifra; firma+cifratura; verifica formale |
| **DAC/MAC/RBAC, Bell-LaPadula, Biba** | [M2/UD1/L2](../M2_AccessControl&Authentication/UD1/L2_DAC_MAC_RBAC.md) | NRU/NWD per la segretezza; NWU/NRD per l'integrità; gruppo (statico) ≠ ruolo (dinamico); least privilege, separation of duty |
| **Matrice degli accessi: ACL, capability, tabella** | [M2/UD1/L3](../M2_AccessControl&Authentication/UD1/L3_Matrice_Accessi.md) | ACL = per colonne (buona per "chi accede a X", revoca costosa); capability = per righe (buona per la delega); tabella = triple non nulle (DBMS) |
| **Crittografia simmetrica vs asimmetrica** | [M3/UD2/L3](../M3/UD2/L3.md) · [L2](../M3/UD2/L2.md) | Principio di **Kerckhoffs**; problema della distribuzione delle chiavi; KDC vs PKI; schema **ibrido**; block vs stream cipher; spazio delle chiavi $2^N$ |
| **Sicurezza mobile: Android vs iOS** | [M5/UD1/L3](../M5/UD1/L3.md) · [L1](../M5/UD1/L1/L1.md) · [L2](../M5/UD1/L2/L2.md) | Sandbox iOS + firma **vendor-signed** Apple vs Android **self-signed**, mercato aperto, permessi all'installazione, isolamento con UID per app, Dalvik/APK; virus vs worm vs trojan; polimorfismo |
| **HTTP e HTTPS** | [M4/UD2/L1](../M4/UD2/L1.md) · [M4/UD1/L2](../M4/UD1_Contesto/L2.md) | Stateless, GET/HEAD/POST, codici di stato, TLS, certificati X.509, porta 443; ISO/OSI e stack TCP/IP; incapsulamento |
| **Phishing** | [M4/UD4/L3](../M4/UD4/L3.md) | Testo del link ≠ attributo `href`; urgenza/minacce/premi; controllo del mittente; relazione con il reflected XSS |
| **Apache: `.htaccess` e contenitori** | [M4/UD5/L1](../M4/UD5/L1/L1_Introduzione_ad_Apache.md) | `AllowOverride`, *most specific takes precedence*, `<Directory>`/`<Files>`/`<FilesMatch>`/`<Location>`/`<Limit>`, `DocumentRoot` |

---

### **5. Piano per le prossime ore**

Il piano è pensato su **~20 ore utili** prima dell'esame, includendo **almeno 7 ore di sonno**, che non sono un lusso ma parte del metodo: la memoria consolida dormendo, e all'orale scritto conta la lucidità.

#### **Blocco 1 — le certezze (3 ore)**
Le quattro aree di fascia A, in quest'ordine:

1. **Challenge-response** (45 min). Rileggi [M3/UD3/L4](../M3/UD3/L4.md), poi **scrivi a mano** i tre schemi: unilaterale a 2 messaggi, mutuo a 3 messaggi, mutuo con identità. Devono uscire senza pensarci.
2. **Apache** (45 min). Rileggi le due lezioni e **scrivi a mano** due blocchi di configurazione: uno host-based con `Order Allow,Deny` + `Allow` + `Deny`, uno user-based completo con `AuthType`/`AuthName`/`AuthUserFile`/`Require user`. È l'unica domanda in cui **si scrive codice**: se lo si ha in mano, sono 8 minuti di risposta.
3. **Unix** (45 min). Rileggi [M2/UD3/L2](../M2_AccessControl&Authentication/UD3/L2/L2_Controllo_Accessi_Linux.md) e fai **l'esercizio sui permessi** in [domande_fineM2 §2](../M2_AccessControl&Authentication/domande_fineM2.md). Poi ripeti a voce le tre angolazioni: *permessi concreti*, *semantica su directory + ACL*, *DAC e limiti*.
4. **Firma digitale + hash + certificati** (45 min). I tre argomenti insieme: la tabella hash/MAC/firma è il centro di gravità.

#### **Blocco 2 — le probabili (2 ore e 30)**
Kerberos (40 min, con le tre fasi disegnate a mano), CSRF (30 min, sulla soluzione del [3 luglio](Soluzione_Appello_03_07_2026.md)), MFA (30 min), XSS + SOP (30 min), SQL injection (20 min).

#### **Blocco 3 — simulazione a tempo (1 ora e 30)**
**Il passaggio più importante di tutto il piano.** Prendi la traccia del **16 giugno** (è quella con le sei domande *diverse* da quelle di luglio, quindi la meno "già letta"), metti un timer da 90 minuti e **scrivi le risposte a mano, senza appunti**. Poi confronta con la [soluzione](Soluzione_Appello_16_06_2026.md) e segna solo **che cosa hai dimenticato**, non che cosa hai scritto peggio.

> 💡 Se il tempo è poco, questo blocco vale più della lettura passiva di due moduli. Si perdono punti quasi sempre per **omissione di un sotto-punto**, non per ignoranza dell'argomento.

#### **Blocco 4 — la fascia C in superficie (1 ora e 30)**
Della tabella di fascia C, **solo la colonna "nucleo da ricordare"**. Obiettivo: saper produrre 5 righe corrette su ciascuno. Non aprire le lezioni intere, non c'è tempo e non serve.

#### **Blocco 5 — sonno (7+ ore)**
Non negoziabile.

#### **Blocco 6 — la mattina dell'esame (1 ora)**
Solo tre cose, in quest'ordine:

1. Le **risposte lampo** della sezione 6 di questo documento.
2. Le **trappole** della sezione 7.
3. Riscrivere a mano, un'ultima volta, lo **schema del challenge-response mutuo** e i **due blocchi di configurazione Apache**.

Poi chiudere tutto.

---

### **6. Risposte lampo — il nucleo di ogni argomento in 60 secondi**

Se ricordi solo questo di ciascun argomento, la risposta è già sufficiente. Tutto il resto è contorno.

1. **Challenge-response.** *Il verificatore invia una sfida fresca; il richiedente prova di conoscere il segreto restituendone una trasformazione, senza trasmetterlo.* No-replay = il nonce è **imprevedibile e mai riutilizzato**, quindi una risposta registrata è legata a una sfida obsoleta. Mutua = due nonce, tre messaggi, **con le identità dentro il cifrato** contro i reflection attack.
2. **Apache host-based vs user-based.** Host-based = *da dove arriva* (IP/dominio/env), **niente autenticazione**, `Allow`/`Deny`/`Order`. User-based = *chi è*, **richiede autenticazione**, `Auth*` + `require valid-user|user|group`. Si combinano con `Satisfy all|any`.
3. **Permessi Unix.** 10 caratteri: tipo + 3 terne (user/group/others). Su **file**: leggere / modificare / eseguire. Su **directory**: elencare / creare-cancellare / **attraversare**. Valutazione **per prima classe applicabile**, non "la più favorevole". Ottale $r=4, w=2, x=1$.
4. **setuid/setgid.** Su un eseguibile, il processo gira con **UID (o GID) del proprietario del file** anziché del chiamante. `rws------` / `rwxrws---`. Rischio: un binario setuid root è **codice root eseguibile da chiunque** → escalation. `chmod` **dopo** `chown`, perché il cambio di proprietario azzera i bit.
5. **DAC e limiti.** Decide il **proprietario**, con **delega**. Limite fondamentale: **nessun controllo sul flusso dell'informazione dopo l'accesso** → **Trojan Horse** (`read(F1); write(F2)` con i privilegi di $S_1$: nessuna regola violata, politica aggirata). Altri limiti: 3 sole classi, root onnipotente, setuid, revoca/audit costosi, nessun confinamento dei processi, nessuna etichetta. Rimedio: strato **MAC** (SELinux/AppArmor) + **RBAC**.
6. **ACL.** Implementazione della matrice degli accessi **per colonne** (per ogni oggetto, chi accede). I 9 bit sono una **ACL semplificata a 3 categorie fisse**; le **ACL POSIX** (`getfacl`/`setfacl`) aggiungono voci per singoli utenti/gruppi, la `mask` e le ACL di **default** ereditabili. Capability = per righe, buona per la delega. Revoca costosa in entrambe.
7. **Firma digitale.** Si firma il **digest** con la chiave privata, si verifica con la pubblica. Garantisce **integrità, autenticazione, non ripudio, verifica pubblica**. **Non** garantisce **confidenzialità**, **freschezza** (replay!) né l'**autenticità della chiave pubblica** (→ certificati).
8. **Ruolo dell'hash nella firma.** Tre cose: **efficienza** (input fisso e corto), **legame con tutto il contenuto** (effetto valanga; firmare blocchi separati sarebbe insicuro), **fondamento di sicurezza** (preimage, second preimage, **collision resistance**; birthday $\Rightarrow 2^{n/2}$; MD5 e SHA-1 rotti).
9. **Perché il MAC non basta per firmare.** Bob **conosce la chiave condivisa**, quindi avrebbe potuto produrre lui il MAC → **niente non ripudio**; e nessun terzo può verificare → **niente verifica pubblica**.
10. **Certificato digitale.** Attestazione **firmata da una CA** che una **chiave pubblica** appartiene a una **identità**. X.509: soggetto, chiave pubblica, validità, emittente, revoca, firma della CA. Creazione: genera coppia → invia pubblica → prova identità → **prova possesso della privata** (challenge-response) → CA firma → rilascia. Verifica: validità temporale → certificato dell'emittente → firma → **risalita della catena** fino a una root fidata (self-signed, preinstallata) → **CRL**.
11. **Kerberos.** **AS** = autentica al login, chiave derivata dalla password (**mai trasmessa**), rilascia **TGT** + chiave di sessione. **TGS** = su presentazione del TGT rilascia **ticket di servizio** + chiave di sessione; il ticket è **cifrato con la chiave del servizio**, quindi il client non può leggerlo né modificarlo; contiene identità, IP, timestamp, lifetime (~10 h), chiave di sessione. **AS+TGS = KDC = Identity Provider**. È **SSO** perché la password si inserisce **una volta per sessione** e poi si usano i ticket. Simmetrico → **compromesso il KDC, compromesso tutto**.
12. **CSRF.** Il browser di un utente **autenticato** viene indotto a inviare a un sito terzo una richiesta che **modifica lo stato**. Funziona perché il **cookie è ambientale** (allegato automaticamente) e perché la **SOP non impedisce di *inviare*** richieste cross-origin, solo di **leggere** le risposte. Realizzazione: `<img src>` o form auto-inviato su pagina malevola (phishing). Difese: **token anti-CSRF** (un nonce non ambientale), `SameSite`, controllo `Origin`/`Referer`, niente `GET` per operazioni con effetti, ri-autenticazione. **`HttpOnly` non serve** contro il CSRF.
13. **XSS reflected.** Lo script viaggia **nella richiesta** e torna **nella risposta**; il server lo riflette senza sanitizzare; il browser lo esegue **nell'origine fidata** e ruba il cookie di sessione. Non persistente (vale solo per quell'URL). Difese: **escaping contestuale dell'output**, validazione whitelist, sanitizzazione; poi `HttpOnly`, `Secure`, **CSP**.
14. **Same Origin Policy.** Stessa origine = **stesso protocollo + host + porta** (il **path non conta**). Limita ciò che gli script possono **leggere/manipolare**, non ciò che la pagina può **includere o inviare**. **Non blocca l'XSS** (il codice gira dentro l'origine) e **non blocca il CSRF** (la richiesta parte lo stesso).
15. **SQL injection.** Modifica la **semantica della query** perché input non fidato viene **concatenato** nel comando. Lato client: un parametro controllabile (form, query string, cookie, header) — la validazione JavaScript **non conta**. Lato server: query per concatenazione, nessuna validazione, DB presente. Payload: `' OR 1=1 --`, `; DROP TABLE`, `UNION ALL SELECT`. Difesa: **prepared statement + bind variable tipate** (+ whitelist, privilegio minimo, errori non verbosi). *`PreparedStatement` + concatenazione = ancora vulnerabile.*
16. **MFA.** Due o più fattori di **categorie diverse**: **conoscenza** (economica, revocabile / dizionario, sniffing, social engineering), **possesso** (resiste a replay e sniffing / autentica **il token, non l'utente**, furto e clonazione), **biometria** (non dimenticabile né prestabile / costosa, **non revocabile**, FAR-FRR-EER, contraffazione). Funziona perché gli attacchi ai fattori sono **indipendenti**.
17. **FAR / FRR / EER.** FAR = accetto un impostore. FRR = respingo un legittimo. Soglia alta → FAR ↑; soglia bassa → FRR ↑. **EER** = punto in cui FAR = FRR, usato per confrontare i sistemi.
18. **Proprietà di sicurezza.** **CIA** + **autenticazione** + **non ripudio**. Integrità = modifiche non autorizzate **rilevabili**, non impossibili. Le proprietà **confliggono** (confidenzialità vs disponibilità) e dipendono dal contesto. Schneier: *processo, non prodotto*; *anello più debole*.
19. **Confidenzialità ≠ privatezza ≠ anonimato.** Confidenzialità = **il contenuto** non è accessibile a non autorizzati (vale per qualunque risorsa). Privatezza = **controllo dell'individuo** su quali suoi dati sono raccolti, da chi e per quale scopo (solo per dati personali; GDPR vs USA). Anonimato = **non attribuibilità dell'azione a un'identità**; c'è anche lo **pseudo-anonimato**, fragile perché resta l'IP.
20. **Salt.** Valore casuale, **univoco per utente**, memorizzato **in chiaro** insieme all'hash. Non nasconde la password: serve a far sì che **la stessa password produca hash diversi**, eliminando le collisioni fra utenti e vanificando le tabelle precalcolate. `/etc/shadow` (leggibile solo da root) vs `/etc/passwd` (pubblico).

---

### **7. Le trappole — frasi che valgono il voto pieno**

Sono le distinzioni su cui, tipicamente, si separa un 24 da un 30. Ciascuna sta in una riga e va **scritta esplicitamente** quando l'argomento la tocca.

| # | Trappola | La formulazione corretta |
|:-:|----------|--------------------------|
| 1 | Privacy = confidenzialità | **Non** sono sinonimi: la confidenzialità riguarda l'accesso al contenuto, la privatezza il **controllo dell'individuo** sui propri dati |
| 2 | «La SOP protegge dall'XSS» | La SOP **non blocca l'XSS**: lo script viene eseguito **dentro l'origine fidata**, quindi la policy è aggirata, non violata |
| 3 | «`HttpOnly` protegge dal CSRF» | **No.** `HttpOnly` impedisce di **leggere** il cookie (difesa anti-XSS); il CSRF ha bisogno solo che il browser lo **invii** |
| 4 | «Base64 cifra le credenziali» | **La Base64 non è cifratura**: è una codifica. L'autenticazione Basic richiede **HTTPS** |
| 5 | «Il MAC garantisce il non ripudio» | **No**: chi verifica **possiede la stessa chiave**, quindi avrebbe potuto produrlo. Serve la **firma digitale** |
| 6 | «La firma digitale rende il messaggio segreto» | **No**: $M$ viaggia **in chiaro**. Firmare rende **attribuibile**, non **confidenziale** |
| 7 | «La firma protegge dal replay» | **No**: la coppia $(M, s)$ resta valida per sempre. Serve un **nonce o un timestamp dentro il materiale firmato** |
| 8 | «Il token autentica l'utente» | **No: autentica il token.** Chi lo possiede impersona l'utente → va sempre abbinato a un secondo fattore |
| 9 | «Due password = MFA» | **No**: sono due istanze dello **stesso fattore**. Servono **categorie diverse** |
| 10 | «Il timestamp è come un nonce» | **No: è prevedibile.** Perciò la variante *challenge cifrato / response in chiaro*, lecita con un nonce, è **insicura** con un timestamp |
| 11 | Nella variante 3, rispedire lo stesso cifrato | Il response **deve trasformare** il nonce ($N_A + 1$), altrimenti Trudy lo **riflette** senza decifrare |
| 12 | «Mutua = eseguire due volte il protocollo» | **No**: senza le **identità** nel materiale autenticato si apre il **reflection attack** con sessioni parallele |
| 13 | «`PreparedStatement` mi protegge» | Solo se si usano i **placeholder**. Con la concatenazione dentro il template **si resta vulnerabili** |
| 14 | «Valido l'input lato client» | Il client è **interamente sotto il controllo dell'attaccante**: la validazione di sicurezza è **solo lato server** |
| 15 | «`x` su una directory = eseguirla» | `x` su directory significa **attraversarla**: senza `x` non si entra nemmeno avendo `r` (`chmod 444 d1; cd d1` fallisce) |
| 16 | «Il kernel prende il permesso migliore» | **No**: applica **la prima classe applicabile** (owner → group → others) e si ferma |
| 17 | «Per cancellare un file serve `w` sul file» | Serve **`w` sulla directory** che lo contiene → per questo esiste lo **sticky bit** |
| 18 | «Kerberos usa la chiave pubblica» | **No: è interamente simmetrico.** Deriva dal Needham-Schroeder a chiave condivisa e usa **timestamp** |
| 19 | «In Kerberos la password viaggia cifrata» | **Non viaggia affatto**: viene usata **localmente** per derivare la chiave che decifra la risposta dell'AS |
| 20 | «Il salt è segreto» | **È in chiaro.** Serve a rendere **diversi** gli hash di password uguali, non a nasconderle |
| 21 | «`Order Deny,Allow` = tutto permesso» | `Order` fissa solo **l'ordine di valutazione** e il default: l'esito dipende da **tutte** le `Allow` e `Deny` presenti |
| 22 | «Il DAC è insicuro perché i permessi sono pochi» | Il limite **fondamentale** è un altro: **nessun controllo sul flusso dell'informazione dopo l'accesso** → **Trojan Horse** |
| 23 | «Il certificato serve a cifrare meglio» | Serve ad **autenticare l'associazione identità ↔ chiave pubblica**, non a migliorare la cifratura |
| 24 | «Gruppo e ruolo sono la stessa cosa» | Il **gruppo** è un insieme **statico di utenti**; il **ruolo** è un insieme **dinamico di permessi**, attivabile per sessione |

---

### **8. Due lacune del materiale, da colmare a mano**

Entrambe riguardano argomenti **effettivamente usciti** ma **non coperti** dalle lezioni digitalizzate. Vanno studiate direttamente sulle soluzioni di questo modulo.

**(a) CSRF** — uscito in **2 appelli su 3**, nessuna lezione dedicata in M1–M5. → Studiare la [domanda 2 della soluzione del 3 luglio](Soluzione_Appello_03_07_2026.md), che lo ricostruisce a partire dai meccanismi del corso (cookie di sessione, SOP, freschezza dei protocolli). **È la lacuna più rischiosa dell'intero programma**, data la frequenza.

**(b) ACL POSIX (`getfacl`/`setfacl`)** — richieste esplicitamente dalla traccia del 3 luglio. Nel materiale il termine "ACL" compare solo nel senso teorico di *colonna della matrice degli accessi* ([M2/UD1/L3](../M2_AccessControl&Authentication/UD1/L3_Matrice_Accessi.md)). → La [domanda 4 della soluzione del 3 luglio](Soluzione_Appello_03_07_2026.md) copre **entrambe le accezioni**: rispondere presentandole tutte e due (prima la ACL come implementazione della matrice, poi le ACL POSIX come estensione concreta) è la strategia corretta, perché copre qualunque cosa la docente intendesse.

---

### **9. Strategia d'aula**

**Prima di scrivere (5 minuti).** Leggi tutte e sette le domande e **numera i sotto-punti** di ciascuna a margine. Poi parti dalle domande che sai meglio — quasi sicuramente le 4, 5 e 7 — per mettere in cascina punti sicuri e guadagnare fiducia. Le domande **non** vanno svolte nell'ordine della traccia, basta numerarle chiaramente.

**Struttura di ogni risposta** (funziona per tutte e sette):

1. **Definizione secca** in una o due righe: *"Un attacco CSRF è …"*, *"Il DAC è la politica in cui …"*.
2. **Meccanismo o funzionamento**, con lo schema o la formula se ce n'è uno.
3. **Un esempio concreto** — anche breve: `' OR 1=1 --`, `-rwxr-----`, un blocco `<Directory>`, i tre messaggi del protocollo. È l'elemento che distingue la risposta studiata da quella riassunta.
4. **La distinzione critica**, presa dalla tabella delle trappole (§7).
5. **Chiusura in una riga**, che risponde letteralmente alla domanda posta.

**Gestione del tempo.** Timer mentale a 12 minuti per domanda. **Se una domanda sfora, fermati e passa oltre**: una risposta completa in più vale molto più di un capoverso aggiuntivo su una risposta già sufficiente. Tieni **15 minuti finali** per completare le domande lasciate a metà.

**Se non sai qualcosa.** Non lasciare in bianco e non divagare. Scrivi ciò che sai con certezza collegandolo a quello che la domanda chiede: quasi ogni argomento del corso si collega ad almeno un altro (autenticazione ↔ controllo degli accessi, hash ↔ firma ↔ certificati, cookie ↔ SOP ↔ XSS ↔ CSRF, DAC ↔ MAC ↔ RBAC). Una risposta parziale ma corretta e pertinente prende punti; una pagina di divagazioni no.

**Cose da scrivere sempre, quando pertinenti.** Il nome proprio dei concetti (*Reference Monitor*, *Ticket Granting Ticket*, *delegation of duty*, *Equal Error Rate*, *synchronizer token*, *effetto valanga*, *collision resistance*, *most specific takes precedence*), perché segnalano immediatamente che l'argomento è stato studiato sulle lezioni e non intuito.

---

### **10. Riepilogo in una pagina**

> ✅ **Le quattro cose che vanno sapute con certezza** (≈ 4 domande su 7): challenge-response con nonce e mutua autenticazione; Apache host-based vs user-based con esempi scritti; permessi/DAC/setuid/ACL in Unix; firma digitale con hash, MAC e certificati.
>
> ✅ **Le cinque molto probabili**: Kerberos (AS/TGS/TGT e perché è SSO), CSRF, MFA con FAR/FRR, XSS reflected con SOP, SQL injection.
>
> ✅ **Le lacune da colmare a mano**: CSRF e ACL POSIX, entrambe coperte solo dalle soluzioni di questo modulo.
>
> ⚠️ **L'errore più costoso non è ignorare un argomento: è dimenticare un sotto-punto della traccia.** Numerali a margine prima di scrivere e spuntali mentre rispondi.
>
> 💡 **La singola ora meglio spesa** delle prossime 20 è la simulazione a tempo sull'appello del 16 giugno, scritta a mano e senza appunti.

---

## Indice delle soluzioni

- [Soluzione Appello 16/06/2026](Soluzione_Appello_16_06_2026.md) — SQLi · proprietà di sicurezza · certificati · permessi Unix e setuid/setgid · Apache user-based · reflected XSS · challenge-response
- [Soluzione Appello 03/07/2026](Soluzione_Appello_03_07_2026.md) — Kerberos · CSRF · firma digitale · `rwx` e ACL · Apache host vs user · MFA · challenge-response con nonce *(trattazione estesa)*
- [Soluzione Appello 10/07/2026](Soluzione_Appello_10_07_2026.md) — come sopra, ma con **DAC e suoi limiti** al posto della domanda 4 *(le altre in forma condensata, per il secondo passaggio di ripasso)*
