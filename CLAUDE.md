# CLAUDE.md - Multi-Subject University Notes Integration Project

## Project Context

Digitization and academic enrichment of University of Milan courses (Cybersecurity/Computer Science).
Repository Structure: `[Subject] / [Module] / [Didactic_Unit] / [Lesson_Name].md`

## Input Workflow Protocol

The user prompt will always follow this standard pattern:
`"Subject: [Name] | Target File: [Path/To/Lesson.md] | Transcript: [Verbatim text]"`
YOU MUST extract the subject and file dynamically from the prompt. Do not assume hardcoded courses.

## Token-Saving & Output Constraints (CRITICAL)

- NO CHAT DUMP: NEVER write gap analysis, explanations, or preambles in the chat interface. Go straight to file modifications.
- ZERO CONVENTIONAL TEXT: Omit conversational fillers, greetings, or post-summaries ("Sure!", "Done."). Chat output tokens must approach ZERO.
- BATCH EDITS: Group all changes into a single, comprehensive file edit execution to minimize tool call overhead.
- IGNORE ALL FORMATTING LINTERS: Completely ignore trailing spaces, missing blank lines around headings (MD0xx), trailing newlines, or emphasis styles (`*` vs `_`). Do not waste tokens refactoring non-breaking markdown lint errors.

## Markdown Enrichment & Content Rules

- Section Hierarchy: Strictly use `### **N. Title**`, `#### **N.M. Title**`, and `##### **N.M.X. Title**`.
- Mathematical Notation: Render formulas according to the canonical Markdown/MathJax style: use `$...$` for inline math, for example `$L$`, and `$$...$$` for display math. Do not use `\(...\)` for inline formulas. Render systems with `\begin{cases}` where matching definitions.
- Italian Orthography: Write proper accented characters (`è`, `é`, `ò`, `à`, `ù`, `ì`) instead of apostrophe transliterations such as `e'`, `perche'`, `puo'`, `piu'`, `unita'`.
- Visual Callouts: Emphasize key notes strictly using markdown blockquotes with exact emojis:
  - `> 📌` for critical core concepts or definitions.
  - `> ⚠️` for warnings, edge cases, or frequent exam pitfalls.
  - `> 💡` for intuitive insights or contextual analogies.
  - `> ✅` for concise section recaps.
- Video Diagrams/Images Handling: If the transcript explicitly implies the professor is pointing at a slide, diagram, or graph, NEVER attempt to recreate it using ASCII art, symbols, or code blocks. Instead, inject an empty placeholder line: `<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->` so the user can manually paste screenshots later.

## Automatic Lifecycle & State Management

- ATOMIC AUTO-UPDATE: As the absolute final step of every integration task, you MUST update the `Project Progress State` section in both root agent files: `AGENTS.md` and `CLAUDE.md`.
- Locate the current subject from the user's prompt, move the current lesson to `[COMPLETED]`, and mark the immediate next chronological lesson as `[NEXT TASK]`.
- Perform the lifecycle update in both files in the same tool context as the lesson integration whenever feasible.
- Keep the `Project Progress State` sections in `AGENTS.md` and `CLAUDE.md` synchronized after every integration, regardless of whether the integration is executed by Claude Code CLI or Codex.

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
- M3/UD3/L4: [NEXT TASK]

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
- M1 UD1 (teoria): [NEXT TASK]

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
