# AGENTS.md - Codex Configuration for Multi-Subject University Notes Integration Project

## Project Context

- This repository digitizes and academically enriches University of Milan course notes for Cybersecurity and Computer Science.
- Repository structure: `[Subject] / [Module] / [Didactic_Unit] / [Lesson_Name].md`.
- Treat each lesson integration as a targeted markdown file update driven by the user's subject, target file, and transcript.

## Input Workflow Protocol

- User prompts for integration tasks follow this pattern:
  `Subject: [Name] | Target File: [Path/To/Lesson.md] | Transcript: [Verbatim text]`
- Extract `Subject`, `Target File`, and `Transcript` dynamically from the prompt.
- If the target file is embedded in a natural-language request and the transcript is provided in the prompt body or as an attachment, infer the target from the explicit path and read the attached/body transcript before editing.
- Do not assume hardcoded courses, subjects, modules, units, or lesson names.
- Use the extracted `Target File` as the only lesson file to enrich unless the user explicitly asks otherwise.
- For transcript-driven integrations, first read the current target lesson, then integrate every concept mentioned by the professor while preserving the lesson's narrative flow.
- Do not remove existing lesson content. At most correct inaccurate or poorly explained concepts; normally add, expand, make the exposition more discursive, and avoid leaving implicit mathematical or logical steps.

## Execution Constraints

- NO CHAT DUMP: do not write gap analyses, explanations, preambles, or progress summaries in chat for integration tasks.
- ZERO CONVENTIONAL TEXT: omit conversational fillers, greetings, and post-summaries such as `Sure!`, `Done.`, or equivalent acknowledgements.
- Keep chat output tokens as close to zero as possible.
- BATCH EDITS: group lesson content changes and progress-state updates into a single comprehensive edit pass whenever feasible.
- IGNORE ALL FORMATTING LINTERS: do not spend time or tokens fixing non-breaking markdown lint issues such as trailing spaces, missing blank lines around headings, final newline style, or emphasis style differences (`*` vs `_`).

## Markdown Enrichment Rules

- Preserve the target lesson's existing structure and style unless the integration requires a direct addition.
- Use this strict section hierarchy for new or reorganized lesson sections:
  - `### **N. Title**`
  - `#### **N.M. Title**`
  - `##### **N.M.X. Title**`
- Render mathematical formulas according to the canonical Markdown/MathJax style: use `$...$` for inline math, for example `$L$`, and `$$...$$` for display math. Do not use `\(...\)` for inline formulas.
- Render mathematical systems using standard LaTeX block format with double dollar signs, using `\begin{cases}` for matching definitions or piecewise systems.
- Write Italian text with proper accented characters (`è`, `é`, `ò`, `à`, `ù`, `ì`) instead of apostrophe transliterations such as `e'`, `perche'`, `puo'`, `piu'`, `unita'`.
- Do not use ASCII art, pseudo-diagrams, or code blocks to recreate visual material.

## Visual Callouts

- Emphasize key notes strictly with markdown blockquotes using the exact markers below:
  - `> 📌` for critical core concepts or definitions.
  - `> ⚠️` for warnings, edge cases, or frequent exam pitfalls.
  - `> 💡` for intuitive insights or contextual analogies.
  - `> ✅` for concise section recaps.
- Keep callouts concise and directly tied to lesson content.

## Visual Placeholder Policy

- If the transcript explicitly implies the professor is pointing at a slide, diagram, graph, image, table, drawing, or visual example, never attempt to recreate it using ASCII art, symbols, markdown tables, or code blocks.
- Insert this exact empty HTML comment line placeholder where the visual belongs: `<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->`
- Leave the placeholder empty so the user can manually paste screenshots later.

## Automatic Lifecycle & State Management

- As the absolute final step of every future integration task, update the `Project Progress State` section in both root agent files: `AGENTS.md` and `CLAUDE.md`.
- Locate the current subject from the user's prompt.
- Locate the current lesson or task corresponding to the target file.
- Move the current lesson/task to `[COMPLETED]`.
- Mark the immediate next chronological lesson/task in the same subject as `[NEXT TASK]`.
- Perform the lifecycle update in both files in the same edit context as the lesson integration whenever feasible.
- Keep the `Project Progress State` sections in `AGENTS.md` and `CLAUDE.md` synchronized after every integration, regardless of whether the integration is executed by Codex or Claude Code CLI.
- Do not mark unrelated subjects or non-chronological tasks as next.

## Project Progress State

### Sistemi Operativi 1 [COMPLETED]

- Stato contenutistico: [COMPLETED]
- Tutti i file `.md` dell'insegnamento sono allineati con il programma del professore.
- Rimane solo l'integrazione manuale delle foto/screenshot nei placeholder visivi gia' predisposti.

### Sistemi Operativi 2 [COMPLETED]

- Stato contenutistico: [COMPLETED]
- Tutti i file `.md` dell'insegnamento sono allineati con il programma del professore.
- Rimane solo l'integrazione manuale delle foto/screenshot nei placeholder visivi gia' predisposti.

### Stato Esami Sistemi Operativi [COMPLETED]

- Sistemi Operativi 1 + Sistemi Operativi 2: 6 CFU + 6 CFU = 12 crediti universitari.
- Entrambi gli insegnamenti sono preparati a livello contenutistico per i rispettivi esami scritti.
- Non aggiungere nuove integrazioni contenutistiche a Sistemi Operativi 1 o 2 salvo richiesta esplicita dell'utente.

### Focus Operativo Corrente

- Da ora il lavoro principale passa a Statistica e Crittografia.
- Per i prossimi prompt integrativi, privilegiare l'avanzamento di queste due materie.

### Sicurezza Web&Mobile [IN PROGRESS]

- M2_AccessControl&Authentication/UD3/L1 (Intro Linux): [COMPLETED] — creato `L1_Intro_Linux.md` da `linux_m2_d1.pdf` + `es_linux1.pdf`.
- M2_AccessControl&Authentication/UD3/L2 (Controllo accessi Linux): [COMPLETED] — creato `L2_Controllo_Accessi_Linux.md` da `linux_m2_d2.pdf` + `es_linux2.pdf`.
- M2_AccessControl&Authentication/domande_fineM2: [COMPLETED] — creato `domande_fineM2.md` con risposte complete a 23 domande teoriche + esercizio Unix sui permessi.
- M3/UD1/L1 (Comunicazione sicura su canale insicuro): [COMPLETED] — integrato transcript su Internet come canale insicuro, Alice/Bob, confidenzialità, integrità, autenticazione, non ripudio, entity vs message authentication.
- M3/UD1/L2 (Attacchi ai protocolli di comunicazione): [COMPLETED] — integrato transcript su protocolli, handshake, Trudy, attaccante passivo/attivo, intercettazione, cancellazione, modifica, impersonificazione, replay attack, limiti della cifratura.
- M3/UD2/L1 (Introduzione storica alla crittografia): [COMPLETED] — integrato transcript su scopi della crittografia, steganografia, Erodoto, cifrario di Cesare, sostituzione/permutazione, chiave segreta, simmetrica, chiave pubblica, Maria Stuart, analisi delle frequenze, Vernam, uso militare/commerciale.
- M3/UD2/L2 (Primitive di cifratura e decifratura): [COMPLETED] — integrato transcript su schema Alice/Bob/Trudy, chiavi di cifratura/decifratura, principio di Kerckhoffs, correttezza, efficienza, difficoltà per l'attaccante, forza bruta, spazio $2^N$, lunghezza chiavi, block cipher e stream cipher.
- M3/UD2/L3 (Crittografia simmetrica e asimmetrica): [COMPLETED] — integrato transcript su chiave segreta, vantaggi/svantaggi, distribuzione chiavi, KDC, chiavi di sessione, chiave pubblica/privata, uso ibrido, algoritmi DES/3DES/DESX/AES/IDEA/RC2/RSA/Rabin/ElGamal, limiti su replay e autenticazione.
- M3/UD2/L4 (Funzioni hash e MAC): [COMPLETED] — integrato transcript su definizione hash, one-way, preimage/second-preimage/collision resistance, effetto valanga, birthday paradox, usi per password/integrità/firma/sync, hash con chiave, MAC, limiti su segretezza/non ripudio/terze parti, MD4/MD5/SHA-1/RIPEMD.
- M3/UD2/L5 (Firma digitale): [COMPLETED] — integrato transcript su analogia con firma cartacea, integrità/autenticazione/non ripudio, limiti del MAC, firma con chiave privata, verifica con chiave pubblica, firma del digest, verifica, resistenza alla contraffazione, associazione chiave pubblica-identità, confronto hash/MAC/firma.
- M3/UD2/L6 (Certificati digitali e PKI): [COMPLETED] — integrato transcript su problemi di autenticazione chiavi pubbliche, certificati digitali X.509, Certification Authority, emissione certificati, chain of trust, root/intermediate CA, verifica certificati, CRL/revoca, confronto KDC vs PKI.
- M3/UD2/L7: [PENDING]
- M3/UD3/L1 (Notazione per protocolli crittografici): [COMPLETED] — integrato transcript su obiettivi dei protocolli, session/run, chiavi lungo/breve termine, freschezza, efficienza, terze parti, controllo chiavi, non ripudio, assunzioni d'attacco, notazione partecipanti/attaccanti/server/chiavi/cifratura/firma/certificati/messaggi.
- M3/UD3/L2 (Protocollo di autenticazione unilaterale): [COMPLETED] — integrato transcript su progettazione incrementale del protocollo, identità/IP/password, replay attack, nonce, challenge-response con chiave condivisa, firma digitale, MITM e certificati.
- M3/UD3/L3 (Attacchi comuni ai protocolli): [COMPLETED] — integrato transcript su osservazione passiva, modifica messaggi, replay, freshness, sessioni parallele, reflection attack, MITM, Denial of Service e SYN flood TCP.
- M3/UD3/L4 (Protocolli challenge-response): [COMPLETED] — integrato transcript su test di autenticazione, freshness, nonce, timestamp, numeri di sequenza, chiavi a breve termine, varianti simmetriche, server/KDC e rischi di replay/reflection.
- M3/UD3/L5 (Needham-Schroeder simmetrico): [COMPLETED] — integrato transcript su protocollo a chiave condivisa, KDC, chiavi a lungo termine/sessione, ticket per Bob, autenticazione mutua challenge-response, attacco Denning-Sacco e correzione con timestamp/Kerberos.
- M3/UD3/L6 (Needham-Schroeder a chiave pubblica): [COMPLETED] — integrato transcript su protocollo asimmetrico, certificati, mutua autenticazione con nonce, segretezza dei nonce, attacco di Lowe/sessioni parallele e correzione con identità di Bob nel messaggio critico.
- M3/UD3/L7 (Principi di progettazione dei protocolli): [COMPLETED] — integrato transcript sui principi di Abadi-Needham, efficienza, doppia cifratura in Kerberos, identità nei messaggi, Denning-Sacco, scopi della cifratura, firma/cifratura, X.509, non ripudio e verifica formale.
- M3/UD3/L8: [PENDING]
- M4/UD1_Contesto/L1 (Introduzione al Web e sicurezza applicazioni): [COMPLETED] — integrato transcript su funzionamento del web, browser/web server/database/Internet, HTTP richiesta-risposta, URL, siti statici/dinamici, crescita del web, sicurezza client/server/protocolli/DBMS e vulnerabilità OWASP come injection, autenticazione/sessione e XSS.
- M4/UD1_Contesto/L2 (Architettura Internet e stack TCP/IP): [COMPLETED] — integrato transcript su modello ISO/OSI, comunicazione a livelli, stack TCP/IP, TCP/IP, incapsulamento messaggio-segmento-pacchetto-frame, sicurezza ai livelli applicazione/trasporto/rete, RFC, IETF e struttura dei documenti tecnici.
- M4/UD1_Contesto/L3: [PENDING]
- M4/UD2/L1 (HTTP: funzionamento e vulnerabilità): [COMPLETED] — integrato transcript su HTTP come protocollo applicativo stateless, oggetti web, browser/server, TCP porta 80, connessioni persistenti/non persistenti, request/response, header, metodi GET/HEAD/POST, codici di stato, limiti di sicurezza, HTTPS/TLS, certificati X.509, porta 443 e S-HTTP.
- M4/UD2/L2 (Cookie HTTP, sessioni e privacy): [COMPLETED] — integrato transcript su HTTP stateless, Set-Cookie/Cookie, identificatori di sessione, backend server-side, attributi Domain/Path/Expires/Secure/HttpOnly, cookie di sessione/persistenti, first-party/third-party, tracking cookie, gestione browser, document.cookie e rischi privacy/XSS.
- M4/UD2/L3 (Vulnerabilità dei cookie e contromisure): [COMPLETED] — integrato transcript su intercettazione cookie via HTTP, flag Secure/HTTPS, cookie poisoning, modifica lato client, carrello manipolabile, furto tramite document.cookie/XSS, HttpOnly, tracking cookie, campi hidden nei form e validazione server-side.
- M4/UD2/L4: [PENDING]
- M4/UD3/L1 (Introduzione a SQL per SQL injection): [COMPLETED] — integrato transcript su basi di dati relazionali, tabelle/record/attributi, query come tabella risultato, SELECT/FROM/WHERE, proiezione, restrizione, query su più tabelle, multinsiemi e DISTINCT, qualificazione attributi, terminazione con punto e virgola, DROP e INSERT.
- M4/UD3/L2 (SQL injection: funzionamento dell'attacco): [COMPLETED] — integrato transcript su input non fidato, query SQL concatenate lato server, condizioni necessarie dell'attacco, bypass login con tautologia/commento, cancellazione tabella utenti con DROP, limiti legati alla conoscenza dello schema e attacco UNION ALL per esfiltrare dati sensibili.
- M4/UD3/L3 (SQL injection: contromisure): [COMPLETED] — integrato transcript su vignetta Bobby Tables, distinzione dati/controllo, metacaratteri SQL, validazione input, blacklist e relativi limiti, whitelist con regex, prepared statement, bind variable tipate, uso ingenuo ancora vulnerabile e caso storico ASP/Microsoft 2008.
- M4/UD3/L4: [PENDING]
- M4/UD4/L1: [PENDING]
- M4/UD4/L2 (Same Origin Policy): [COMPLETED] — integrato transcript su browser con sessioni parallele, cookie/session identifier, definizione di origine come protocollo+host+porta, path escluso dalla definizione generale, esempi di origini uguali/diverse, risorse cross-origin incluse nella stessa pagina, regole su cookie/document.cookie, varianti tra browser e collegamento con XSS.
- M4/UD4/L3: [NEXT TASK]

### Matematica Discreta [IN PROGRESS]

- M1_Insiemi_numerici/UD1_Numeri_naturali: [COMPLETED] — creati L1-L6 (operazioni in ℕ, induzione, definizioni ricorsive, numeri primi, divisori/crivello, MCD/mcm)
- M1_Insiemi_numerici/UD2_Relazioni_di_equivalenza: [COMPLETED] — creati L1-L2 (relazioni e proprietà, equivalenza e ordine)
- M1_Insiemi_numerici/UD3_Congruenze_e_criteri_divisibilita: [COMPLETED] — creati L1-L7 (congruenza mod n, ℤₙ, congruenze lineari, TCR, criteri divisibilità, Wilson/Fermat/Eulero, esercizi)
- M2_Gruppi_Anelli_Campi/UD1_Gruppi: [COMPLETED] — creati L1-L6 (strutture algebriche, gruppi e proprietà, ℤₙ/ℤₙ* e gruppi ciclici, gruppo simmetrico Sₙ, omomorfismi, teorema di Lagrange)
- M2_Gruppi_Anelli_Campi/UD2_Campi_e_anelli: [COMPLETED] — creati L1-L3 (campi e anelli, anelli di polinomi A[x], anello delle matrici Mₙₓₙ[K])
- M3 e moduli successivi: [NEXT TASK]

### Statistica [IN PROGRESS]

- M4_Esami_svolti/esami_2025: [COMPLETED] — creati 4 file soluzione completa (23-lug-2025, 5-lug-2025, 12-feb-2025, 15-gen-2025); coprono Binomiale, Poisson, Gaussiana, Ipergeometrica, Esponenziale, Bayes, stat. descrittiva, TLC
- M1-M3 (teoria): [PENDING]

### Crittografia [IN PROGRESS]

- M7_Appelli_Svolti/UD3_Anno_2025: [COMPLETED] — creati L0 (intro) + L1-L6 (6 appelli 2025 con soluzioni complete passo-passo)
- M7_Appelli_Svolti/UD1_Anno_2023: [COMPLETED] — creati L0 + L1-L8 (8 appelli 2023 risolti da zero dai PDF in `Temi d'esame`; L7=08/09 è rimando a L6=07/09, contenuto identico).
- Unità "Esercizi_Appelli" per modulo: [COMPLETED] — per ogni modulo M1-M6 una UD che accorpa, un file per esercizio, tutte le domande degli appelli M7 (2023+2024+2025) di quel solo modulo (80 esercizi: M1=18, M2=20, M3=13, M4=11, M5=5, M6=13). Generate da `scripts/build-crypto-exercise-units.mjs` (+ validate). **Naming**: `NN - [gg-mm-aaaa] Titolo.md` (prefisso numerico zero-padded per ordine cronologico + data italiana; intro `00 - Intro.md`). Modifiche di contenuto vanno fatte nel SORGENTE M7 (le copie si rigenerano e sovrascrivono). NB: alcuni link prerequisiti restano rotti nei sorgenti M7 (difetti preesistenti).
- M1 UD1 (teoria): [PENDING]
- M4_Funzioni_Hash_e_Mac/UD2/L1 (Costruzione di funzioni hash): [COMPLETED] — integrato transcript su costruzione di funzioni hash per messaggi arbitrariamente lunghi, padding, blocchi fissi, schema parallelo ad albero, risultato di Damgård, schema seriale/iterato con IV e funzione di compressione, cascata H1/H2, costruzioni da cifrari a blocchi con funzione G e schemi Matyas-Meyer-Oseas/Miyaguchi-Preneel/Davies-Meyer, attacco meet-in-the-middle e probabilità tipo birthday.
- M4_Funzioni_Hash_e_Mac/UD2/L2 (MD4 e MD5): [COMPLETED] — integrato transcript su algoritmi Message Digest di Ron Rivest, MD4/MD5 progettati da zero, limite $2^{64}$ bit, digest 128 bit, architetture 32 bit little-endian/big-endian, requisiti di sicurezza/velocità/semplicità, padding a blocchi 512 bit, word 32 bit, operazioni bitwise e modulo $2^{32}$, funzioni logiche F/G/H/I, compressione MD5 a quattro round, costanti $T[i]$ da seno, inizializzazione ABCD, differenze MD4-MD5, effetto valanga e attacchi storici fino alla vulnerabilità birthday/collisioni.
- M4_Funzioni_Hash_e_Mac/UD2/L3 (SHA-1): [COMPLETED] — integrato transcript su SHS/SHA/SHA-0/SHA-1, NIST, digest 160 bit, limite $2^{64}$ bit, padding come MD4/MD5, blocchi 512 bit e word 32 bit, espansione 16→80 word con rotazione, funzioni primitive per intervalli 0-19/20-39/40-59/60-79, costanti additive da radici quadrate, buffer ABCDE, compressione a 80 operazioni, confronto con MD5, famiglia SHA-2, altre funzioni hash e distinzione collision attack/preimage attack.
- M4_Funzioni_Hash_e_Mac/UD2/L4 (Applicazione delle funzioni hash – Notaio digitale): [COMPLETED] — integrato transcript su digital timestamping, marca temporale, esempi reali notaio/posta/brevetto/giornale/protocollo/foto con quotidiano, prova prima/dopo una data, soluzione con autorità fidata, invio di digest $H(D)$, problemi di fiducia, protocollo distribuito con $K$ partecipanti scelti dal digest, TSS con alberi di hash, superhash concatenati, cammino di verifica per la terza richiesta, sicurezza da resistenza alle collisioni e implementazioni Surety/PGP.
- M4_Funzioni_Hash_e_Mac/UD3/L1 (Codici MAC): [COMPLETED] — integrato transcript su Message Authentication Code, definizione $MAC(M,K)$, chiave segreta condivisa, tag a lunghezza fissata, schema Alice/Bob, autenticità e integrità, assenza di confidenzialità, combinazione con cifratura e chiave distinta, proprietà easy computation/compression/computational resistance/key non-recovery, total break/selective forgery/existential forgery, known/chosen/adaptive chosen message attack, ricerca esaustiva su chiave/tag e soglia minima 128 bit.
- M4_Funzioni_Hash_e_Mac/UD3/L2 (Costruzione di MAC): [COMPLETED] — integrato transcript su tecniche basate su cifrari a blocchi e funzioni hash, esempio XOR+DES/ECB con $\Delta_M$, attacco di falsificazione per stesso XOR, limiti dello XOR globale, CBC-MAC con IV zero e ultimo blocco cifrato/troncamento, vantaggi dei MAC hash-based, differenze tra requisiti hash e MAC, metodo del segreto prefisso con attacco di estensione e contromisura della lunghezza, metodo del segreto suffisso con attacco birthday e preview HMAC.
- M4_Funzioni_Hash_e_Mac/UD3/L3 (HMAC): [COMPLETED] — integrato transcript su standardizzazione FIPS 198/RFC 2104, uso in SSL/TLS, vantaggi delle funzioni hash come black-box, parametri $H$, $IV$, $n$, $b$, $L$, normalizzazione della chiave $K_0$, costanti `ipad=0x36` e `opad=0x5C`, formula completa HMAC, schema operativo a hash interno/esterno, precomputazione degli stati dipendenti dalla chiave, costo vicino al solo hash del messaggio, output troncato, sicurezza ridotta alle proprietà della hash e attacco birthday con $2^{n/2}$ coppie messaggio-MAC.
- M4_Funzioni_Hash_e_Mac/UD4_Approfondimenti_Esame/L1 (H(x)=DES_k(x) - analisi one-wayness): [PENDING]
- M5_Firme_Digitali/UD1/L1 (Scenario e applicazioni): [COMPLETED] — integrato transcript su scenario delle firme digitali, differenza tra firma convenzionale e digitale, problemi dei documenti digitali modificabili/trasmissibili/duplicabili, fallimento della firma digitalizzata, requisiti di uno schema di firma, procedure Sign/Verify con chiave privata e pubblica, trasmissione della coppia messaggio-firma su canale insicuro senza confidenzialità, modelli di attacco key-only/known message/chosen message/adaptive chosen message e obiettivi total break/selective forgery/existential forgery.
- M5_Firme_Digitali/UD2/L1 (Schema RSA): [COMPLETED] — integrato transcript su adattamento del crittosistema RSA alla firma digitale, inversione concettuale rispetto alla cifratura, chiavi $(n,e)$ e $d$ con $ed \equiv 1 \pmod{\varphi(n)}$, firma $F=M^d \bmod n$, verifica $F^e \bmod n=M$, schema con recupero del messaggio, esempio numerico $n=3337$, $e=79$, $d=1019$, $M=1570$, $F=668$, problema dei messaggi lunghi, limiti della firma separata dei blocchi e soluzione tramite firma dell'hash $h(M)$.
- M5_Firme_Digitali/UD2/L2 (Sicurezza di RSA): [COMPLETED] — integrato transcript su sicurezza dello schema RSA diretto, key-only selective forgery equivalente a rompere RSA, existential forgery scegliendo $F$ e calcolando $M=F^e \bmod n$, ruolo di ridondanza/formato, known-message forgery tramite omomorfismo moltiplicativo $F_1F_2$ su $M_1M_2$, chosen-message selective forgery con fattorizzazione del messaggio bersaglio, schema RSA con hash, attacco key-only ridotto alla preimage resistance e attacco con messaggi scelti ridotto alla collision resistance.
- M5_Firme_Digitali/UD3/L1 (Schema DSS): [COMPLETED] — integrato transcript su standard DSS/NIST 1991 e revisione 1993, algoritmo DSA collegato a ElGamal e al logaritmo discreto, uso di SHA a 160 bit, firme fisse da 320 bit, parametri $p$, $q$, $\alpha$, chiave privata $s$ e pubblica $\beta=\alpha^s \bmod p$, concetto di ordine con esempio in $\mathbb{Z}_{15}^*$, esempio numerico $p=7879$, $q=101$, $\alpha=170$, $s=75$, $\beta=4567$, generazione firma $(\gamma,\delta)$, verifica con $e'$ ed $e''$, precomputazione di $(r,\gamma,r^{-1})$, confronto di efficienza con RSA e dimostrazione di correttezza.
- M5_Firme_Digitali/UD3/L2 (Parametri dello schema DSS): [COMPLETED] — integrato transcript su generazione dei parametri DSS/DSA, inefficienza della scelta diretta di $p$ seguita dalla fattorizzazione di $p-1$, generazione prima di $q$ a 160 bit e poi di $p$ con $p \equiv 1 \pmod{2q}$, procedura DSS verificabile con seed $S$ e counter, decomposizione $L-1=160n+b$, costruzione di $q$ tramite SHA e XOR, costruzione di $p$ tramite valori $V_i$, $W$, $X$ e test di primalità, generazione di $\alpha=g^{(p-1)/q}\bmod p$, prova che $\alpha$ ha ordine $q$ se $\alpha\ne1$, probabilità di successo e numero medio di tentativi.
- M5_Firme_Digitali/UD3/L3 (Sicurezza dello schema DSS): [COMPLETED] — integrato transcript su attacchi a DSS/DSA, total break key-only tramite recupero di $s$ come logaritmo discreto di $\beta$ in base $\alpha$, selective forgery key-only ricondotta a relazione di logaritmo discreto, existential forgery con scelta di $(\gamma,\delta)$, calcolo di $z$ tramite logaritmo discreto e inversione di SHA, parametri globali $(p,q,\alpha)$ condivisibili, generazione efficiente della chiave individuale $s,\beta$, confronto prestazionale con RSA, verifica RSA veloce con esponente pubblico piccolo, verifica DSA con due esponenziazioni, firma DSA accelerabile tramite precomputazione e criticità del valore casuale $r$.
- M5_Firme_Digitali/UD4_Approfondimenti_Esame/L1 (DSA - firma (a,0) e caso delta=0): [PENDING]
- M6_Applicazioni_Crittografiche/UD1/L1 (Protocollo Diffie-Hellman): [COMPLETED] — integrato transcript su protocolli di accordo su chiave, necessità di stabilire una chiave simmetrica condivisa su canale insicuro, avversari passivi/attivi, confronto con Puzzle di Merkle, parametri pubblici $p,g$, generatore di $\mathbb{Z}_p^*$, scelte segrete $x,y$, scambio $g^x$/$g^y$, chiave $K=g^{xy}\bmod p$, esempi numerici con $p=11$ e $p=25307$, problema di Diffie-Hellman e relazione non dimostrata equivalente al logaritmo discreto, attacco Man-in-the-Middle e necessità di autenticazione.
- M6_Applicazioni_Crittografiche/UD1/L2 (Scelta dei parametri): [NEXT TASK]

### Reti di Calcolatori [IN PROGRESS]

- M3_Protocolli_applicativi/UD1 (DNS): [COMPLETED] — integrati L3 (complementi sui nomi: FQDN, gerarchia, delegazione, zone/dominio, ridondanza slave) e L4 (risoluzione: flusso forwarder→root→gtld→autoritativo, RR format, TTL vs SOA timers, propagazione "macchia d'olio")
- M3_Protocolli_applicativi/UD2 (FTP/Telnet/SMTP/P2P): [COMPLETED] — integrati L1 (FTP: accesso anonimo, server-initiates-data-connection, firewall bypass, sessione esempio), L2 (Da FTP a Telnet: porte specifiche, PASV, NVT, tinygram→Nagle, importanza storica Telnet), L3 (SMTP intro: spool directory, daemon MTA, Sendmail punto sicurezza critico), L4 (P2P: overlay strutturato/non strutturato, media session, Dropbox analogy, copyright, IM/Skype, SETI@home)
- M3_Protocolli_applicativi/UD3 (Email protocols): [COMPLETED] — integrati L1-L5 (SMTP, MIME, posta elettronica/sicurezza, spam: origini/open-relay/proxy, difficoltà anti-spam: honeypot/Bayes/SpamAssassin/Vipul's Razor/spam grafico/harvesting)
- M3_Protocolli_applicativi/UD4 (WWW/HTTP): [COMPLETED] — integrati L1 (WWW: paradigma pull, URL, porta 80/sicurezza, stateless+vantaggi/problemi, HTTP/1.0 vs 1.1), L2 (HTTP: URI/URN/URL, GET/POST/PUT/HEAD semantics CGI, header campi from/accept/user-agent/referer/authorization/pragma/if-modified-since, codici stato, 3 tecniche stateful), L3 (browser come macchina, servizi statici/dinamici/attivi, riepilogo messaggi, CONNECT/OPTIONS, entity header, esempio esercizio esame)
- M3_Protocolli_applicativi/UD5 (Gestione reti/SNMP): [COMPLETED] — integrati L1 (problema gestione rete, OO paradigm, MIB distribuita partizionata, GET/SET primitives, CMIP contributi concettuali), L2 (SNMP: dominio amministrazione, UDP rationale, messaggi GetRequest/GetNextRequest/GetResponse/SetRequest/Trap, community in luogo autenticazione formale, SMI+ASN.1 BER, IPAddress tipo più utile, esempio ifNumber), L3 (OBJECT-TYPE+MODULE-IDENTITY costrutti, modulo UDP, OID gerarchia albero ISO/DoD, modalità sincrona/trap, software gestione CiscoWorks/OpenView/Solstice)
- M4_Tecniche_programmazione_distribuita/UD1/L1 (Socket API intro): [COMPLETED] — Socket Library da Unix BSD, port TCP/UDP multiplexing, quaderna, file descriptor analogy, interfaccia basso livello
- M4_Tecniche_programmazione_distribuita/UD1/L2 (Creazione socket): [COMPLETED] — ruolo client vs server, socket connessione vs servizio, metafora postale, socket() nominale (domain/type/protocol), SOCK_DGRAM/SOCK_STREAM/raw, bind() quaterna + wildcard INADDR_ANY, bind implicita UDP invio (scope = 1 pacchetto)
- M4_Tecniche_programmazione_distribuita/UD1/L3 (Setup connessione): [COMPLETED] — 4 fasi (2 passivo/1 attivo/1 condivisa), quaterna parziale lato server (2 wildcard), listen() non bloccante + parametro QL backlog, accept() bloccante + riempimento quaterna da SO, connect() lato client con tutti 4 parametri noti, flusso bidirezionale piggyback
- M4_Tecniche_programmazione_distribuita/UD1/L4 (Funzioni comunicazione): [COMPLETED] — connect() TCP bloccante vs connect() UDP (solo memorizzazione locale), send()/recv() bloccanti con semantica ritrasmissione TCP, socket di servizio in recv(), sendto()/recvfrom() UDP con quaterna parziale + riempimento SO, recvfrom() bloccante senza semantica TCP, close() resource leak
- M4_Tecniche_programmazione_distribuita/UD1/L5–L8 (esercizi C): [COMPLETED] — creati L5.1 (TCPClientEcho), L5.2 (TCPServerEcho), L6.1 (TCPServerEchoSelect con select()), L8.1 (HTTP Viewer lab); tutti con consegna + soluzione commentata step-by-step
- M4_Tecniche_programmazione_distribuita/UD1/L6 (Select): [COMPLETED] — 3 strategie connessioni multiple (fork/exec, non-blocking, select), fd_set tre vettori bit (readfds/writefds/exceptfds), FD_ZERO+FD_SET pre/FD_ISSET post, timeout=0/NULL/positivo, altre funzioni utili (bzero/gethostname/gethostbyaddr/inet_addr/inet_ntoa), porte libere (SIGTERM/SIGINT + cleanExit)
- M4_Tecniche_programmazione_distribuita/UD1/L7 (Socket Java): [COMPLETED] — 2 significati socket (RFC793/BSD), tipi flusso/datagramma, welcome vs connection socket, chiamate fondamentali, flusso completo TCP, TCPClient.java + TCPServer.java (capitalizzazione), UDP: DatagramSocket/DatagramPacket, UDPClient.java + UDPServer.java, confronto TCP vs UDP
- M5_Esame/esame_21_marzo_2025: [COMPLETED] — creati parteB.md (traccia) + soluzioneParteB.md (soluzione Parte B: server HTTP TCP moderazione commenti in C con fork/accept/recv/send/mkdir, multipart/form-data, HTTP/1.0 vs 1.1 con 10 immagini, firewall sottorete con iptables)
- M5_Esame (refactor): pseudocodice Python → C in esame_23_maggio_2025/SoluzioneB.md (chat UDP fork, broadcast, multicast) e esame_8_maggio_2026/soluzioneParteB.md (proxy TCP autenticato)
- M4 moduli successivi / UD2+: [NEXT TASK]
