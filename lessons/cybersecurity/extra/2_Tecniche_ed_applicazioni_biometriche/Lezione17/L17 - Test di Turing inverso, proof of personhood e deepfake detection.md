# **Lezione 17: Test di Turing inverso, proof of personhood e deepfake detection**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

**Outline** (p. 2):

1. **Inverse Turing Test**;
2. **Proof of Personhood**;
3. **deepfake**: che cosa sono; a che cosa servono (scopi leciti e illeciti); produzione di deepfake come **test dei sistemi biometrici**;
4. **deepfake detection**: frame singolo, video, protezione con **challenge-response**, test dei sistemi e piattaforme;
5. **conseguenze penali** in Italia.

La lezione è accompagnata dal **2025 Identity Fraud Report** dell'**Entrust Cybersecurity Institute**, basato sui dati di **Onfido** (società di Entrust). È un **report aziendale** di un produttore di soluzioni di verifica dell'identità: i numeri sono preziosi, ma riflettono il suo portafoglio clienti e includono passaggi promozionali. Nel testo è indicato come «report».

<!-- SOURCE_VISUAL id="TAB17-V01" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="1" type="copertina" description="Inverse Turing Test, Proof of Personhood, Deep Fake Detection" reason="Copertina grafica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte A — Umano o macchina?**

### **1. Il test di Turing inverso**

#### **1.1 Definizione**

_(slide pp. 3–6)_

| | **Test di Turing** (Alan Turing, 1950) | **Test di Turing inverso** (ITT, *Reverse Turing Test*) |
|---|---|---|
| Chi giudica | un **umano** | una **macchina** |
| Che cosa decide | se sta parlando con un umano o con una macchina | se l'interlocutore è **umano** o **macchina** |

Dal punto di vista biometrico e di sicurezza (p. 4):

- **non** interessa sapere **chi sei**;
- interessa sapere **che cosa sei**: umano o non umano.

È quindi un problema di **classificazione binaria** con risposta $\in \{\text{umano}, \text{bot}\}$ (p. 5):

$$P(\text{umano} \mid X) > T$$

dove $X$ sono le **osservazioni comportamentali** e $T$ la **soglia di decisione**. Esistono versioni **probabilistiche**, come reCAPTCHA v3, che restituiscono direttamente la probabilità $P(\text{umano} \mid X)$.

> 📌 **Definizione operativa** (p. 6). Un Inverse Turing Test è progettato per **distinguere utenti umani da agenti automatici** (bot), sfruttando capacità che gli umani possiedono **naturalmente** e che le macchine, **storicamente**, faticano a replicare.

> ⚠️ **Sigla.** Le slide pp. 4–5 scrivono sia «IIT» sia «ITT»; la sigla coerente con *Inverse Turing Test* è **ITT**.

> 💡 **Il legame con la biometria classica.** È lo stesso schema di soglia di verifica e identificazione (Lezione 2), ma con due sole «identità»: umano e bot. Valgono gli stessi compromessi: una soglia alta blocca più bot ma infastidisce più umani (falsi rifiuti), una soglia bassa fa il contrario.

<!-- SOURCE_VISUAL id="TAB17-V02" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="3" type="immagine" description="Inverse Turing Test: esempio di CAPTCHA con testo distorto (Bear up!)" reason="Pagina con immagine e poche parole" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2 Dal CAPTCHA al comportamento**

_(slide pp. 7–11)_

**Storicamente** (p. 7), l'ipotesi di base è: facile per un umano, difficile per una macchina. Il **CAPTCHA** (*Completely Automated Public Turing test to tell Computers and Humans Apart*) chiede di:

- **leggere testo distorto**;
- **riconoscere oggetti** nelle immagini;
- **ascoltare e trascrivere** audio.

**Recentemente** (p. 8), con **CNN** e **Vision Transformer** molti CAPTCHA sono aggirabili: si è passati a **test comportamentali**. Il punto chiave non è il puzzle, ma **come ti comporti**. Si misurano implicitamente:

- **traiettoria del mouse**;
- **tempo di risposta**;
- **micro-esitazioni**;
- **coerenza temporale**.

È una forma di **biometria comportamentale soft**. Anche nella casella «non sono un robot», l'uso del mouse rivela la **dinamica umana** (p. 9).

**reCAPTCHA v3 di Google** (pp. 10–11):

- lavora **in background** analizzando il comportamento;
- restituisce uno **score di rischio continuo** tra 0,0 e 1,0 invece di un semplice pass/fail: **1,0** indica un utente molto probabilmente **umano**, **0,0** un probabile **bot**.

I segnali valutati:

- velocità e traiettoria del mouse;
- pattern di digitazione;
- tempo di permanenza;
- cronologia di navigazione sulla pagina;
- **fingerprinting del browser** (limitato).

Lo score permette **logiche personalizzate**. L'esempio della slide:

$$\text{azione} = \begin{cases} \text{blocco} & \text{se score} < 0{,}3 \\ \text{MFA} & \text{se } 0{,}3 \leq \text{score} \leq 0{,}6 \\ \text{accesso consentito} & \text{se score} > 0{,}6 \end{cases}$$

**Analisi comportamentale continua**:

| Momento | Che cosa distingue umano e bot |
|---|---|
| **apertura della pagina** | stabilisce una **baseline**: un bot carica spesso solo le risorse necessarie, un umano carica la pagina e inizia a muovere il cursore |
| **scroll** | l'umano scrolla con **accelerazioni e pause** per leggere; uno script «salta» direttamente a una coordinata $(x, y)$ |
| **focus su un campo** | il bot **incolla** i dati istantaneamente; l'umano clicca, il cursore lampeggia, inizia a digitare |
| **ripetizione ogni X secondi** | rileva bot che restano **«dormienti»** in una scheda aperta per attivarsi dopo |

> 💡 **Due soglie invece di una.** La logica a tre fasce è la stessa della cascata del sistema ABC spagnolo (Lezione 14, §14.6): si decide subito nei casi netti e si chiede un secondo fattore solo nella **zona di incertezza**, dove si concentrano gli errori.

<!-- SOURCE_VISUAL id="TAB17-V03" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="9" type="immagine" description="reCAPTCHA con mouse: la dinamica del movimento rileva l'umano" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.3 BeCAPTCHA e fusione**

_(slide pp. 12–15)_

**BeCAPTCHA** (p. 12): CAPTCHA **comportamentale** che distingue un umano da un bot analizzando come interagisce con uno **smartphone**, invece di immagini da selezionare o testi da digitare. Usa anche **reti GAN** per produrre traiettorie **sempre più naturali**, con cui addestrare le reti di rilevamento dell'attacco.

> 💡 **L'attaccante come allenatore.** Generare traiettorie false sempre più realistiche e usarle come esempi negativi rende il rilevatore più robusto: è la stessa logica avversaria delle GAN, in cui generatore e discriminatore migliorano insieme.

**Fusione di tecniche** (p. 13): con la **fusione a livello di score** (Lezione 15) si può creare la combinazione ottimale di segnali per la propria applicazione.

**Deepfake e Inverse Turing Test sono problemi diversi** (pp. 14–15):

| | **Deepfake detection** | **Inverse Turing Test** |
|---|---|---|
| Domanda | «questo **contenuto** è reale o sintetico? è stato **manipolato**?» | «**chi o che cosa** sta interagendo è umano o no?» |
| Oggetto | il **media** (immagine, video, audio) | l'**agente** che interagisce |
| Esempio | un video del CEO che chiede un bonifico è autentico? | chi muove il mouse è una persona o uno script? |

<!-- SOURCE_VISUAL id="TAB17-V04" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="13" type="schema" description="Fusione di tecniche di Inverse Turing Test a livello di score" reason="Pagina con schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V05" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="15" type="illustrazione" description="Deepfake contro Inverse Turing Test: che cosa muove il mouse?" reason="Pagina con immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Proof of Personhood**

#### **2.1 Definizione e progetti**

_(slide pp. 16–19)_

> 📌 **Proof of Personhood** (PoP, p. 17). Insieme di metodi, protocolli o sistemi per verificare che un'entità online sia un **essere umano unico e reale**, non un bot né un **account duplicato**.

Rispetto al test di Turing inverso aggiunge l'**unicità**: non basta essere umani, bisogna esserlo **una sola volta** nel sistema.

**Metodi in sviluppo** (p. 17):

- **dati biometrici**: scansione dell'**iride**, riconoscimento facciale;
- **challenge comportamentali**: pattern motori, digitazione;
- **verifica sociale**: persone già verificate garantiscono per i nuovi membri;
- **dispositivi personali / Secure Enclave**: la prova è legata a **hardware personale sicuro**.

**Progetti in atto** (pp. 18–19):

| Progetto | Approccio |
|---|---|
| **Worldcoin**, ora **World** | scansione dell'iride con l'Orb (§2.2) |
| **Proof of Humanity** | basato su **Ethereum**: video-verifica, **depositi economici** e **dispute comunitarie** (test di Turing inversi) |
| **BrightID** | **grafi sociali**: gli utenti partecipano a «connection parties», virtuali o fisiche, dove si verificano **reciprocamente**; **nessun dato biometrico**, focus sulla privacy |
| **Human Passport** (ex Gitcoin Passport) | sistema di **«stamps»** che verificano aspetti dell'identità digitale: account social verificati, possesso di NFT, partecipazione a **DAO** (*Decentralized Autonomous Organization*), transazioni on-chain; più timbri, più alto il **«punteggio di umanità»** |

<!-- SOURCE_VISUAL id="TAB17-V06" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="16" type="pagina di sezione" description="Identità digitale e Proof of Personhood" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2 World ID e l'Orb**

_(slide pp. 20–25)_

**Ideazione e prototipazione** (2019–2022, p. 21):

- **obiettivo**: distinguere umani e IA (Proof of Personhood) in vista di un futuro dominato dall'intelligenza artificiale;
- **sviluppo**: **Sam Altman** e **Alex Blania** progettano l'**Orb**, il dispositivo biometrico per la **scansione dell'iride**, e il protocollo **World ID**.

**Componenti dell'Orb** (pp. 22–23). Si ritrovano i componenti delle lezioni sulle ottiche:

| Componente | Funzione |
|---|---|
| **anello di LED nel vicino infrarosso** | illuminazione nel range compatibile con l'iride, circa **740–850 nm** |
| **ottica** probabilmente con attacco **C** e di tipo **tele** | ingrandire l'iride a distanza |
| **sensore CMOS senza filtro per il visibile** | aumentare la **sensibilità nel NIR**; alta risoluzione e rapporto di forma adatto a raccogliere diverse posizioni della testa |
| **specchio basculante** (per IR) | **cercare gli occhi** nell'inquadratura; solo i sistemi di fascia alta hanno parti mobili |
| **filtro NIR** | lascia passare il NIR e **blocca il visibile** |

**Deepfake detection sull'Orb** (pp. 24–25):

1. **verifica della vivacità** (*liveness detection*):
   - l'Orb è un sensore **multispettrale**: analizza come la luce infrarossa si **riflette** sull'occhio; i materiali sintetici (maschere, schermi) riflettono diversamente dai **tessuti biologici**;
   - **sensori di profondità** verificano che l'occhio sia un oggetto **tridimensionale** e non una foto o uno schermo;
2. **risoluzione e micro-dettagli**: l'Orb cattura dettagli **microscopici** dell'iride che l'IA attuale non riproduce ancora con precisione fisica assoluta. Queste tecniche tornano nell'antispoofing dell'iride (Lezione 20).

<!-- SOURCE_VISUAL id="TAB17-V07" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="22" type="fotografia prodotto" description="Orb, lo scanner dell'iride di Worldcoin" reason="Pagina con sola immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V08" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="23" type="spaccato annotato" description="Componenti dell'Orb: anello LED NIR 740-850 nm, ottica tele, CMOS senza filtro visibile, specchio basculante, filtro NIR" reason="Posizione dei componenti indicata sull'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3 Le fasi di creazione del World ID**

_(slide pp. 26–31)_

| Fase | Che cosa succede (slide) |
|---|---|
| **1. Scansione** (p. 26) | l'Orb inquadra l'occhio e scatta una foto ad alta risoluzione dell'iride in **infrarosso**; sensori **3D** verificano che si tratti di un umano vivo |
| **2. Conversione** (p. 26) | un algoritmo trasforma i dettagli dell'iride in un **IrisCode**; per la privacy la **foto originale viene cancellata** dall'Orb e resta solo il codice, da cui non si torna alla foto |
| **3. Verifica dell'unicità** (p. 27) | il codice viene confrontato con il database globale per controllare che **non esista già** (evita registrazioni doppie); se è nuovo, si crea il **World ID**. La slide collega questo passo alla **blockchain**, registro **pubblico e immutabile** su cui non finisce il nome ma solo una **prova crittografica** dell'esistenza umana |
| **4. Attivazione** (p. 28) | il World ID viene salvato sullo **smartphone** e si ricevono **token WLD** come incentivo |

> 📌 **Zero-Knowledge Proof** (p. 28). Quando ci si autentica su un sito con World ID **non si invia l'IrisCode**. Semplificando, il telefono invia solo una **prova matematica** che dice: «esiste un IrisCode nel database mondiale che mi appartiene, ma non ti dico quale».

> 💡 **Che cosa garantisce una ZKP (nota didattica).** Una prova a conoscenza zero convince il verificatore che un'affermazione è vera **senza rivelare nient'altro**. Qui l'affermazione è «possiedo la chiave associata a una delle identità registrate». Il sito sa che l'utente è un umano unico, ma non sa quale, e due siti diversi non possono collegare tra loro le autenticazioni della stessa persona.

> ⚠️ **Uno schema semplificato.** Le slide riassumono il protocollo. Il confronto per l'unicità richiede di tenere gli IrisCode (o loro rappresentazioni protette) in un **database** consultabile, mentre sulla blockchain finiscono gli **impegni crittografici** delle identità; i dettagli sono cambiati più volte nella storia del progetto. Diverse autorità europee per la protezione dei dati hanno inoltre esaminato il trattamento dei dati biometrici di World (per esempio con misure cautelari dell'autorità spagnola nel 2024): è un caso concreto dei rischi discussi nella Lezione 13.

**Riassunto ed enrollment** (pp. 29–30). Il simbolo del dollaro davanti a un nome, come per WLD, indica in generale un **token**: un asset con valore di mercato **scambiabile**.

**Worldcoin agli esordi** come esempio di sistema biometrico crittografico decentralizzato, DBC (p. 31; Lezione 13, §13):

- **claim**: costruire «la più grande rete di identità e finanziaria del mondo come servizio pubblico», con accesso universale all'economia globale;
- **creazione del World ID**: scansione dell'iride → IrisCode → chiave privata unica → blockchain;
- dopo la prova di umanità e la creazione della chiave, **l'Orb non serve più** per usare il servizio;
- la **prova di unicità** è fatta una volta sola, all'inizio.

<!-- SOURCE_VISUAL id="TAB17-V09" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="29" type="infografica" description="Riassunto delle fasi di creazione del World ID" reason="Pagina con sola infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V10" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="30" type="schema" description="Enrollment e claim del token" reason="Schema del flusso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.4 Il progetto World oggi**

_(slide pp. 32–34)_

Il progetto è passato da **«criptovaluta per tutti»** a **infrastruttura di identità digitale globale** per proteggere gli umani dalle **imitazioni dell'IA** (p. 32):

- **World Network**, da ottobre 2024: **rebranding** da Worldcoin a **World**;
- **Orb 2.0**, più veloce e «sicuro», e **World Chain**, blockchain dedicata agli **umani verificati** per ridurre lo spam dei bot;
- **deepfake**: integrazione del World ID in app come **FaceTime, WhatsApp e Zoom**.

**Face Auth per World ID** (p. 33): **confronto locale** del volto per sbloccare l'app e confermare che chi ha il telefono è **la stessa persona** della scansione originale, così **non si può vendere il proprio ID**.

**In Italia** (p. 34): nel **gennaio 2026** inizia la campagna di acquisizione anche in Italia (Roma), con integrazione nelle banche. La slide avverte: **non è un consiglio finanziario**.

> 💡 **Perché serve Face Auth.** La prova di unicità lega l'identità a un'iride, ma una volta creata la chiave sul telefono nulla impedirebbe di **cedere il telefono** a un altro. Il controllo locale del volto ricollega ogni uso alla persona registrata: è una verifica 1:1 che chiude un buco dell'enrollment una tantum, come l'autenticazione continua della Lezione 16.

---

## **Parte B — I deepfake**

### **3. Che cosa sono**

_(slide pp. 35–39)_

In aula viene mostrato un video (p. 35) in cui il docente pronuncia frasi **mai dette**: sono state scritte in uno script su una piattaforma online, **HeyGen**, per la generazione automatica di contenuti (p. 36).

> 📌 **Deepfake** (p. 37). Contenuto creato con l'**intelligenza artificiale**, in particolare con algoritmi di **deep learning**, che genera video, immagini o audio **sorprendentemente realistici** ma **falsificati o manipolati**.

**Frodi e deepfake** (p. 38). Secondo il report 2025 di una delle maggiori società del settore (Onfido), i deepfake stanno diventando **uno dei metodi più frequenti e pericolosi**, anche grazie al **fraud-as-a-service**.

**Spoofing e deepfake: due cose diverse** (p. 39):

| | **Deepfake** | **Spoofing** (attacco al sensore) |
|---|---|---|
| Domanda | il **campione** è stato creato artificialmente? | davanti al sensore c'è un **oggetto falso**? |
| Esempi | il volto è **sintetico**? il «padrone» della voce ha detto **davvero** quella frase? | c'è uno **schermo** o una **maschera**? la voce esce da un **registratore**? |
| Lezioni | questa | Lezioni 19–20 |

I due approcci si possono **combinare**: con uno schermo si proietta un deepfake davanti alla telecamera di un sistema biometrico o di videosorveglianza, oppure si fa l'**enrollment remoto** di una persona **che non esiste**.

---

### **4. Tipi e usi dei deepfake**

#### **4.1 Tipi per output**

_(slide p. 40)_

| Tipo | Descrizione (slide) |
|---|---|
| **Video** | il volto di una persona è sostituito con quello di un'altra, mantenendo espressioni e movimenti originali; usati per satira e intrattenimento, ma anche per scopi illegali come la **diffamazione** |
| **Audio** | la voce viene **clonata** o modificata per dire cose mai dette; usata anche per la sintesi vocale per persone con disabilità, ma rischiosa per **frodi e disinformazione** |
| **Immagini** | immagini statiche modificate o generate, compresa la creazione di **persone inesistenti** |
| **Testo** | IA che genera testi apparentemente umani: notizie false, post, libri |
| **Emotivi** | cambiano lo **stile** o l'**emotività** di un messaggio per renderlo più persuasivo o ingannevole |

#### **4.2 Esempi di impiego**

_(slide pp. 41–45)_

**Impieghi generali** (p. 41): deepfake di **personaggi pubblici**; **intrattenimento** (cambiare aspetto, inserirsi in scene di film); **sintesi di espressioni** e sincronizzazione labiale con un nuovo audio; **ricerca ed educazione** (simulazione di eventi storici).

**Impieghi leciti per i sistemi biometrici** (p. 42):

- **creazione di nuovi individui** sintetici, per ampliare i dataset senza problemi di privacy;
- **test delle metodologie di antispoofing**, generando attacchi realistici controllati.

> ⚠️ **Descrizioni scambiate.** Nella p. 42 i testi sotto i due punti sono copiati dalla p. 41 (volti di celebrità, intrattenimento) e non corrispondono ai titoli; il significato corretto è quello dei titoli, riportato sopra.

**Altri esempi**:

| Esempio | Descrizione |
|---|---|
| **Nvidia Eye Contact** (p. 43) | sposta gli occhi del parlante per simulare il **contatto visivo** con la camera, stimando e allineando lo sguardo. **Di fatto è un deepfake: simula attenzione**. Mantiene colore naturale degli occhi e battiti delle palpebre, con una disattivazione se si guarda troppo lontano per una transizione fluida |
| **Face Poke, Expression Editor** (p. 44) | modificano fotografie con realismo crescente; quando funzioneranno **in tempo reale** si potranno usare per **rispondere a una challenge** (§7) |
| **Avatar e individui sintetici** (p. 45) | restare «giovani» nelle rappresentazioni; i **Kiss**, al concerto d'addio al Madison Square Garden, sono stati sostituiti da **avatar** volanti che hanno cantato *God Gave Rock 'n' Roll to You*: «per sempre giovani e per sempre iconici» (Gene Simmons) |

<!-- SOURCE_VISUAL id="TAB17-V11" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="43" type="confronto di immagini" description="Nvidia Eye Contact: sguardo originale e sguardo simulato verso la camera" reason="Effetto visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.3 Impieghi illeciti e diritto**

_(slide pp. 46–48)_

- **Il vero problema** (p. 46) non sono gli avatar ai concerti: il grado di realismo pone **rischi seri** e conseguenze **legali e commerciali** importanti.
- **Fake news, contenuti e account** (p. 47): contenuti provocatori sempre più realistici per fini **commerciali** (click bait), **politici** e **delinquenziali** (truffe, estorsioni, riciclaggio), con conseguenze civili e penali. Esempi: **volti noti** in filmati falsi con audio, **account completamente falsi** con dati verosimili. La generazione sintetica dei tratti biometrici va quindi rivista quando è usata per **fini illeciti**.
- **Il corpo sotto «copyright»** (p. 48): nel **giugno 2025** la **Danimarca** propone di modificare la legge sul diritto d'autore per includere **corpo, tratti somatici e voce** come elementi protetti, simili alle opere d'arte, non riproducibili senza autorizzazione, con **multe severe** per le piattaforme che diffondono contenuti manipolati senza consenso.

---

### **5. I tre tipi principali di attacco**

_(slide pp. 49–54)_

La slide p. 49 presenta i **tre tipi principali**:

| Tipo | Descrizione |
|---|---|
| **1. Face swap** (p. 50) | immagini sintetiche da **due input**: il criminale combina caratteristiche di un volto, come il **movimento**, con l'**aspetto** di un altro, anche con **modelli 3D**. L'output ha i tratti del genuino e può corrispondere alla **foto del documento** durante una verifica di vitalità, se la tecnologia non ha le difese antispoofing più recenti |
| **2. Morphing e manipolazione** (p. 51) | tecniche **«classiche»** di morphing e manipolazione delle immagini **sui documenti d'identità** |
| **3. IA generativa** (p. 52) | è **facilissimo** creare immagini realistiche con le caratteristiche di un utente da impersonare o adatte alla frode in corso |

**Le GAN in biometria** (p. 53):

| Uso | Liceità |
|---|---|
| **data augmentation** | **lecito** |
| **attacchi** | **illecito**, oppure lecito se per **scopi di test** |

Esempio di volti falsi: il sito **thispersondoesnotexist.com** (p. 54).

> 💡 **Il morphing sui documenti.** Si fondono le foto di due persone in un'unica immagine che somiglia abbastanza a **entrambe** da superare il confronto 1:1 per tutte e due. Se la foto «morphata» finisce in un passaggio legittimo di rilascio del passaporto, due persone possono usare **lo stesso documento** a un e-Gate (Lezione 14): il documento è autentico, l'attacco è avvenuto all'**enrollment**.

<!-- SOURCE_VISUAL id="TAB17-V12" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="49" type="infografica" description="I tre tipi principali di deepfake per le frodi d'identità" reason="Pagina senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V13" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="51" type="immagini" description="Morphing e manipolazione di immagini su documenti d'identità" reason="Esempi visivi senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V14" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="54" type="griglia di immagini" description="Esempi di volti falsi generati da thispersondoesnotexist.com" reason="Esempi visivi senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. Trend delle frodi e strumenti**

#### **6.1 I numeri delle slide**

_(slide pp. 55–59)_

**Trend attuali** (p. 55), secondo un'analisi Onfido:

- **aumento di 31 volte** delle frodi con deepfake **dal 2022 al 2023**;
- la biometria è la **nuova frontiera** delle frodi, ma resta **una delle difese più robuste**: la verifica biometrica registra **tre volte meno** tentativi fraudolenti rispetto ai documenti.

**Frodi documentali con e senza biometria** (pp. 56–57). Dal report 2023:

- nel 2023 il tasso di frode biometrica sale all'**1,31%**, **il doppio** dello **0,68%** del 2022;
- i truffatori usano spesso **documenti legittimi** e poi **modificano il proprio aspetto** per i controlli biometrici, con **cheapfake** e **deepfake**;
- la frode biometrica resta comunque **tre volte inferiore** a quella documentale, a dimostrazione dell'efficacia dei sistemi biometrici, soprattutto con **rilevamento della vitalità**;
- secondo il produttore, la sua tecnologia biometrica riduce di **10 volte** il tasso di successo degli attacchi.

**Aggiornamento di fine 2024** (pp. 58–59). Gli attacchi si sono spostati su **video, selfie e «motion»** (movimenti richiesti) e i deepfake crescono **molto velocemente**. Nelle categorie biometriche, i deepfake intervengono **soprattutto quando il sistema richiede movimenti del volto**.

> 💡 **31 volte e 3000% sono lo stesso numero.** Un aumento percentuale del 3000% significa $V_1 = V_0 (1 + 30) = 31\, V_0$. Il report 2025 (§12.3) parla infatti di un aumento del **3000%** dei deepfake tra 2022 e 2023.

> 💡 **Perché i deepfake si concentrano sui controlli «motion».** Un selfie statico si inganna con una **foto di uno schermo**, un attacco economico. Un controllo che chiede di muovere la testa costringe l'attaccante a produrre un **video coerente in tempo reale**: serve un deepfake. Più il controllo è robusto, più sofisticato deve essere l'attacco, quindi la quota di deepfake cresce proprio dove la difesa è migliore.

<!-- SOURCE_VISUAL id="TAB17-V15" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="56" type="grafico" description="Frodi documentali con e senza biometria secondo il report Onfido 2025" reason="Valori leggibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V16" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="58" type="grafico" description="Attacchi biometrici di fine 2024 su video con crescita rapida dei deepfake" reason="Andamento visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V17" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="59" type="grafico" description="Attacchi biometrici di fine 2024 su selfie e motion" reason="Ripartizione degli attacchi visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.2 Strumenti per creare deepfake**

_(slide pp. 60–67)_

| Strumento | Descrizione (slide) |
|---|---|
| **Swap Face** (p. 60) | scambio di volti in video, immagini e **streaming**; le modalità di qualità superiore (Pro, Expert) richiedono una buona GPU |
| **Roop** (p. 60) | **open source** su GitHub, usabile in cloud con **Google Colab** senza GPU di fascia alta, in quattro passaggi |
| **ElevenLabs** (p. 61) | **clonazione vocale** da pochi minuti di audio, **29 lingue** e oltre **50 accenti**; tracce praticamente indistinguibili dalla sorgente |
| **voice.ai** (p. 63) | **cambio di voce in tempo reale** gratuito |
| **Azure AI Speech text to speech avatar** (p. 64) | video con **avatar parlanti** da testo e bot interattivi in tempo reale addestrati con immagini di esseri umani |
| **Sora 2** (OpenAI, ottobre 2025, pp. 66–67) | generazione video e audio realistici; funzione **cameo** che inserisce un volto e una voce reali in video sintetici |

> ⚠️ **Strumenti amatoriali e attacchi reali.** Swap Face e Roop sono facili da installare e usare; **non sono al livello degli attacchi reali**, ma aiutano ad **allenare le reti** di rilevamento (p. 60).

**Clonazione vocale** (pp. 61–63):

- **usi positivi** (p. 62): ricordo e sicurezza in caso di **perdita della voce**; audiolibri con la propria voce, risposte automatiche ai clienti, intrattenimento, campo medico; voce clonata **multilingue**;
- **rischi**: furto d'identità e frodi. La **legislazione non è pronta** e probabilmente sarà **sempre in ritardo**: protezione e dissuasione saranno soprattutto **tecniche**, non legislative;
- società come **Nuance**, che fanno anche riconoscimento biometrico della voce, sviluppano tecnologie contro gli usi impropri (p. 63). Il tema torna nella lezione sull'antispoofing.

**DeepMasterPrints** (p. 65): attacco con **IA generativa** in cui un'impronta sintetica supera la **verifica** per **più utenti** diversi.

> 💡 **Come funziona una master print (nota didattica).** Molti sensori piccoli, come quelli degli smartphone, acquisiscono solo una **porzione** del dito e memorizzano più template parziali per utente. Un generatore produce un'impronta parziale «tipica», che contiene le configurazioni di creste più comuni. Ogni singolo confronto ha un FMR piccolo, ma con molti template e molti utenti vale la formula della Lezione 9:
> $$FMR_N = 1 - (1 - FMR)^N$$
> Con $FMR = 10^{-3}$ e $N = 100$ confronti si ottiene $FMR_N \approx 9{,}5\%$. Un'impronta costruita apposta per aumentare l'FMR del singolo confronto moltiplica ulteriormente l'effetto, e diventa una «chiave passe-partout».

**Sora 2 e i cameo** (pp. 66–67). **Minacce**: furto d'identità biometrica (volto usato senza consenso), disinformazione, frode e impersonificazione su sistemi a volto o voce, cyberbullismo e danni reputazionali. L'esempio della slide è un finto video di Sam Altman che ruba una GPU.

**Policy di protezione adottate da OpenAI**:

- **consenso e controllo**: solo l'utente decide dove usare il proprio cameo, in modo **revocabile**;
- **provenance e watermarking**;
- **moderazione attiva**: filtri automatici più revisione umana;
- **parental control** e limitazioni di default (numero di generazioni al giorno, restrizioni più forti per i minori).

**Come difendersi da utenti**:

- non condividere video e registrazioni inutili;
- usare **autenticazione multifattore** e non solo volto o voce;
- verificare la **provenienza** dei contenuti;
- attivare le **notifiche** dei cameo;
- fare **educazione digitale**.

<!-- SOURCE_VISUAL id="TAB17-V18" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="65" type="schema" description="DeepMasterPrints: un'impronta sintetica supera la verifica di più utenti" reason="Enrollment e verifiche mostrati nel disegno" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

## **Parte C — Deepfake detection**

### **7. Approcci generali**

_(slide pp. 68–74)_

| Approccio | Descrizione (slide) |
|---|---|
| **Analisi forense** (p. 69) | analisi dettagliata dei media: **inconsistenze nei pixel**, **artefatti** dei processi di generazione, discrepanze di **illuminazione e ombre** |
| **Verifica dell'autenticità del contenuto** (p. 70) | tracciamento della **provenienza**, analisi dei **metadati**, metodi basati su **blockchain**; nuovi standard come **C2PA** |
| **Rilevamento con IA** (p. 72) | modelli addestrati a distinguere reale e falso su esempi, analizzando caratteristiche sottili anche **non visibili a occhio nudo** |
| **Challenge-response** (pp. 73–74) | si chiede di rispondere a una **sfida imprevista** |
| **Analisi del comportamento e della consistenza** (p. 73) | incoerenze in **movimento degli occhi**, **espressioni**, **modo di parlare** e accenti |

**Analisi forense e tribunali** (p. 69). Nello **stato di Washington** un giudice ha **respinto** filmati di un triplice omicidio **migliorati con l'IA**: il primo caso di questo tipo, un **precedente** importante nel sistema giudiziario statunitense.

> 💡 **Perché un video «migliorato» è un problema.** Un algoritmo di super-risoluzione **inventa** dettagli plausibili che non erano nel filmato originale. Per la giustizia è una prova alterata; per la biometria è la stessa cosa: un volto ricostruito da un modello generativo non è un campione acquisito (Lezione 11, il caso della luna del Galaxy S21).

**C2PA** (p. 70). La **Coalition for Content Provenance and Authenticity** definisce **metadati firmati crittograficamente**, i **C2PA manifest**, per verificare **fonte**, **cronologia** e **origine** dei contenuti.

**Blockchain** (p. 71). Come per le fake news si pensa a meccanismi di autenticazione su blockchain; **non esistono ancora soluzioni commerciali**. Esempio: **KBC Bank**, terza banca belga, certifica le proprie **rassegne stampa** con *proof of authenticity*, *proof of consistency* e *proof of existence*. Giornalisti, agenzie ed editori verificano che il PDF in loro possesso corrisponda a quello ufficiale per contenuto e data.

> 💡 **Il principio, già visto con il notaio digitale.** Si pubblica l'**hash** del documento in un registro immutabile con la sua data. Chiunque ricalcola l'hash del file ricevuto: se coincide, il file è identico all'originale e già esisteva in quella data. L'autenticità è provata **alla fonte**, senza dover riconoscere il falso guardandolo.

**La qualità del rilevamento con IA** (p. 72) dipende da **modello**, **metodo di apprendimento** e **qualità e completezza dei dati** di addestramento.

**Challenge-response** (p. 74). Nasce nella **cybersecurity**: il verificatore presenta una sfida (numeri, una stringa) e l'altra parte deve dare la risposta corretta. In biometria e nella detezione dei deepfake:

1. **challenge**: il sistema chiede di **modificare l'espressione**, **muovere la testa** in una direzione, **pronunciare una frase**; semplice per un umano reale, difficile da replicare accuratamente e in tempo reale per un deepfake;
2. **response**: il soggetto risponde; una persona reale lo fa in modo **naturale e coerente**;
3. **analisi e verifica**: movimenti, **sincronizzazione labiale**, coerenza delle espressioni.

> 💡 **Perché la sfida deve essere imprevedibile.** Se la richiesta è sempre la stessa, l'attaccante prepara un video preregistrato che la esegue. Una sfida **casuale** (una sequenza di numeri, una direzione a caso) obbliga a generare la risposta **in tempo reale**, ed è lì che i deepfake mostrano ritardi e incoerenze. È la stessa ragione del nonce nei protocolli crittografici contro il replay.

<!-- SOURCE_VISUAL id="TAB17-V19" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="68" type="pagina di sezione" description="Approcci generali per la deepfake detection" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. La difesa dipende dall'input**

_(slide pp. 75–82)_

| Input | Tecniche (p. 75) |
|---|---|
| **Frame singolo** | caratteristiche e **alterazioni**; mancanza di **Gestalt**; **decolorazioni** |
| **Video senza audio** | **pletismografia remota** (rPPG); **flusso ottico** e variazioni non conformi a un volto |
| **Video con audio** | **sincronia parlato-video**; antispoofing **multimodale** su canali separati con fusione delle probabilità di spoofing; **challenge linguistiche** (dire una parola specifica e controllare parlato e sincronia) |

#### **8.1 Indizi nel frame singolo**

_(slide pp. 76–78)_

| Indizio | Che cosa cercare |
|---|---|
| **Transizione di colore** | nei deepfake di **neural puppetry** e **face swap** il volto è manipolato: a volte si nota una transizione di colore **netta ai bordi** dell'area manipolata |
| **Accessori** | occhiali convincenti a prima vista possono contenere **artefatti** da vicino |
| **Sfondo o contesto sfocato** | i deepfake **StyleGAN** creano bene i volti ma male i **contesti coerenti**: controllare sfondo e abbigliamento |
| **Occhi innaturali** | gli occhi sono difficili da falsificare: i **riflessi** hanno lo stesso angolo nei due occhi? la persona **batte le ciglia**? le iridi sono **ugualmente grandi**? |

> 📌 **La Gestalt manca ancora, ma per poco** (p. 78). La teoria della **Gestalt**, dalla psicologia, studia come percepiamo gli elementi come **parti di un tutto** più grande. I suoi principi (**figura/sfondo**, **prossimità**, **somiglianza**, **chiusura**, **semplicità**, **continuità**) spiegano perché un volto perfetto in un contesto incoerente «non torna» a un osservatore attento.

<!-- SOURCE_VISUAL id="TAB17-V20" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="77" type="immagini annotate" description="Indizi nel frame singolo: sfondo incoerente e riflessi negli occhi con angoli diversi" reason="Artefatti visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2 Video: challenge, sincronia e rPPG**

_(slide pp. 79–82)_

- **Motion biometric verification** (p. 79). Movimenti **specifici e diversi** per ogni individuo per controllare **liveness**, **3D** e **risposta**. Esempio: **Onfido Motion** ferma frodi sofisticate con un semplice **movimento della testa**; secondo il produttore è conforme al **livello 2 del PAD di iBeta** e il **95%** dei risultati arriva in pochi secondi.
- **Sincronia parlato-video** (p. 80):
  - **BioID** ha aumentato la sicurezza del riconoscimento facciale valutando il **flusso ottico del movimento delle labbra** tra due fotogrammi (Lezione 16);
  - **Onfido** chiede di pronunciare una **sequenza di numeri casuali** durante il video;
  - esistono **brevetti** che rilevano gli **echi** dal cellulare per dimostrare la **presenza fisica** di una persona vicina.
- **rPPG in tempo reale** (p. 81). **Intel** ha realizzato nel **2022** la prima piattaforma online per la detezione dei deepfake. Il cuore è **FakeCatcher**, basato sulla **fotopletismografia remota**, più il rilevamento della **direzione dello sguardo**, su processori Intel Xeon Scalable con **OpenVINO**.
- **Segnali non visivi** (p. 82): **integrità del dispositivo**, **intelligenza di rete**, **geolocalizzazione**, **scansione NFC** del chip dei documenti d'identità supportati, come ulteriore livello di protezione.

> 💡 **Perché l'rPPG smaschera i deepfake.** Il battito cardiaco modula leggermente il colore della pelle, soprattutto nel canale verde (Lezioni 11 e 12). In un volto reale questo segnale è **periodico** e **coerente** su fronte, guance e mento. Un generatore di volti non modella la circolazione sanguigna: il segnale manca, è rumore, oppure non è sincronizzato tra le regioni del volto.

> ⚠️ **iBeta PAD livello 2.** È una certificazione di laboratorio sulla **presentation attack detection** secondo ISO/IEC 30107-3, con strumenti e tempo d'attacco limitati. Riguarda gli attacchi **al sensore** (Lezioni 19–20), non gli **injection attack** in cui il deepfake entra direttamente nel flusso dati (§12.4).

<!-- SOURCE_VISUAL id="TAB17-V21" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="79" type="sequenza di immagini" description="Onfido Motion: challenge con movimento della testa per verifica di liveness e 3D" reason="Sequenza del movimento visibile solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Quanto sono bravi gli umani**

_(slide pp. 83–87)_

- **Quiz iProov** (p. 84): solo il **24%** dei consumatori sembra in grado di riconoscere i deepfake di un modello generativo recente.
- **Studio della James Cook University** (Somoray e Miller, *Computers in Human Behavior*, p. 85): **450 persone**, **20 video** (10 reali e 10 deepfake). In media i partecipanti ne hanno classificati correttamente **circa 12 su 20**. **Insegnare strategie di rilevamento non ha migliorato** né l'accuratezza né la fiducia, e nemmeno il tempo speso per video.
- **Altri studi** (MIT, UCL, UC Berkeley, Microsoft, benchmark Synthesia, p. 86): accuratezza umana media circa **50–65%**, spesso vicina al **lancio di una moneta**:

| Tipo di deepfake | Accuratezza umana |
|---|---:|
| volti statici | 55–60% |
| video moderni (GAN 2023–2025) | 50–55% |
| audio | spesso < 50% |

> 💡 **12 su 20 è davvero meglio del caso? (nota didattica).** Rispondendo a caso ($p = 0{,}5$), la probabilità di indovinarne almeno 12 su 20 è
> $$P(K \geq 12) = \sum_{k=12}^{20} \binom{20}{k} 0{,}5^{20} = \frac{263\,950}{1\,048\,576} \approx 0{,}25$$
> Un singolo partecipante con 12 risposte giuste **non** è distinguibile da chi tira a indovinare. Solo la media su 450 persone rende significativo lo scarto dal 50%, secondo il ragionamento binomiale della Lezione 9.

**Fattori che influenzano la prestazione umana** (p. 87). Le GAN moderne hanno **coerenza temporale**, **riflessi oculari** realistici, **micro-espressioni** coerenti e **sincronizzazione labiale** accurata. Gli artefatti «classici» (sfocature, ammiccamento anomalo) **non sono più affidabili**; restano segnali come i **riflessi non perfetti**.

<!-- SOURCE_VISUAL id="TAB17-V22" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="83" type="pagina di sezione" description="Deepfake detection: performance degli umani" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Rilevamento con l'IA**

_(slide pp. 88–97)_

#### **10.1 Servizi e piattaforme**

_(slide pp. 88–92)_

La p. 88 introduce la **catena di passi** della classificazione con IA.

**Servizi online di base** (p. 89):

- **WeVerify** deepfake detector;
- **deepfakedetector.ai**;
- **DuckDuckGoose DeepfakeProof**, estensione del browser.

Con la politica **«make or buy»**, se l'esito è **«make»** bisogna realizzare un modello per la propria applicazione.

**DeepFake-o-Meter** (p. 90): piattaforma **open source** dell'**Università di Buffalo**, guidata da **Siwei Lyu**, accessibile con registrazione gratuita per analizzare contenuti sospetti. La p. 91 mostra l'analisi del **video falso** della richiesta «a Gianni» visto in aula.

**Servizi professionali** (p. 92):

| Servizio | Ideale per |
|---|---|
| **Incode Deepsight** | rilevamento enterprise: banche, fintech, piattaforme ad alta sicurezza |
| **Sensity AI** | rilevamento **multimodale** con **reporting forense**, per verifica multimediale e compliance |
| **Reality Defender** | rilevamento via **API** aziendali: team di sicurezza, automazione **KYC**, social media |
| altri | Mitek Systems, AuthenticID, DuckDuckGoose AI, Veridas |

<!-- SOURCE_VISUAL id="TAB17-V23" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="91" type="schermata" description="Risultati del DeepFake-o-Meter sul video falso della richiesta a Gianni" reason="Punteggi dei rilevatori visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.2 Costruire un rilevatore**

_(slide pp. 93–97)_

**Catena di inferenza classica** (p. 93). Attenzione al **downsampling** che la rete deep esegue nel primo strato (per esempio a **256 × 256** pixel): è fondamentale **trovare il volto** e **ritagliare** l'immagine attorno a esso, per conservare i **dettagli** necessari alla detezione.

> 💡 **Quanto dettaglio si perde (nota didattica).** Un frame Full HD $1920 \times 1080$ ridotto a $256 \times 256$ è scalato di $1920 / 256 = 7{,}5$ in orizzontale e $1080 / 256 \approx 4{,}2$ in verticale. Un volto largo 200 pixel diventa largo circa **27 pixel**: gli artefatti ai bordi del face swap o i riflessi negli occhi scompaiono. Ritagliando prima un riquadro di 256 pixel attorno al volto, la rete lo vede a **risoluzione piena**.

**Sei passi per l'allenamento** (p. 94). Il primo è **trovare un modello adatto** tra quelli disponibili per la classificazione: modelli di **deep learning**, di **machine learning** tradizionale e **statistici**. La slide illustra graficamente l'intera sequenza.

**Approcci deep per immagini e video** (pp. 95–96). Per le **sequenze** si usano reti ricorrenti che apprendono le dipendenze temporali tra $t$ fotogrammi; le strutture più diffuse sono le **LSTM** (*Long Short-Term Memory*) e le **GRU** (*Gated Recurrent Unit*).

> 💡 **Perché servono le sequenze.** Un deepfake può essere quasi perfetto in ogni singolo frame e tradirsi solo nel tempo: sfarfallio dei bordi, battiti di ciglia irregolari, rPPG assente, labbra non sincronizzate. Una CNN estrae le feature per frame e la LSTM o la GRU ne analizza l'evoluzione.

**Dataset per l'addestramento** (p. 97):

| Dataset | Contenuto |
|---|---|
| **Deepfake Detection Challenge** (Meta) | varietà di video manipolati e autentici creati per una competizione |
| **Celeb-DF** | video deepfake di **celebrità**, manipolazioni realistiche |
| **DeeperForensics-1.0** | molti video con **diversità** di illuminazione e qualità |
| **Real and Fake Face Detection** | immagini di volti reali e falsi per testare classificatori |
| **WildDeepfake** | deepfake trovati **online** in condizioni non controllate |
| **Fake Face in the Wild** | simile, con immagini e video falsi da ambienti online naturali |
| **FaceForensics++** | ampio dataset di video manipolati con vari tipi di deepfake |

> ⚠️ **Il problema della generalizzazione.** Un rilevatore addestrato su un dataset impara spesso gli artefatti **di quei generatori**. Su deepfake prodotti da un modello nuovo le prestazioni possono crollare: vale la stessa regola della validazione LOPO (Lezione 6), qui come «leave one generator out».

<!-- SOURCE_VISUAL id="TAB17-V24" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="93" type="schema" description="Catena di inferenza classica con rilevamento e ritaglio del volto prima del downsampling della rete" reason="Schema a blocchi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V25" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="94" type="diagramma" description="Sei passi per l'allenamento di modelli AI per la detezione dei deepfake" reason="Solo il primo passo è nel livello testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V26" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="95" type="schema" description="Approcci basati su deep learning per immagini e video" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V27" source="Lezione_17_Test_Turing_Inverso_Deepfake_Detection.pdf" page="96" type="schema" description="Reti ricorrenti LSTM e GRU per sequenze di t fotogrammi" reason="Architettura mostrata nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Riflessioni finali e legge italiana**

_(slide pp. 98–103)_

**Ci stiamo avvicinando a questa scena?** (p. 98). Nel romanzo *Do Androids Dream of Electric Sheep?* di Philip K. Dick (1968), da cui è tratto *Blade Runner*, il cacciatore di replicanti **Rick Deckard** usa la macchina **Voight-Kampff**, che misura le **reazioni emotive** (reazioni pupillari, espressioni) per distinguere **replicanti e umani**: un test di Turing inverso biometrico. Oggi però tutto è **mediato da uno schermo**. Reazioni pupillari e micro-espressioni tornano nelle prossime lezioni.

**«Filosofia» biometrica** (p. 99):

- quasi tutta la nostra esperienza è **mediata** da immagini e suoni digitali; la componente **biologica** dell'interazione con gli altri è sempre meno frequente, mentre le **rappresentazioni virtuali** contano sempre di più;
- la tecnologia sfida la percezione tradizionale della realtà e solleva domande su **verità, autenticità e fiducia**;
- la biometria ha un **ruolo cruciale** nel verificare **la parte umana** del mondo digitale, in due modi:
  - **A.** con **test di liveness** e **antispoofing**, garantire che le interazioni siano **autentiche** e legate a individui reali;
  - **B.** con il **riconoscimento**, identificare in modo unico le caratteristiche biologiche o comportamentali di ciascuno.

**Adattamento continuo** (p. 100):

| Direzione | Contenuto |
|---|---|
| **innovazione tecnologica** | nuovi strumenti di analisi e rilevamento |
| **apprendimento e adattamento** | i rilevatori devono imparare continuamente dai nuovi deepfake |
| **ricerca interdisciplinare** | grafica, IA, psicologia, sicurezza informatica |
| **sensibilizzazione** | educare pubblico e professionisti |
| **standard e legislazione** | norme internazionali e leggi adeguate |

**Reato penale dal 2025** (p. 101): nuovo **art. 612-quater c.p.**, *Illecita diffusione di contenuti generati o alterati con sistemi di IA*.

- Chi **diffonde senza consenso** immagini, video o voci falsificati o alterati con IA **idonei a indurre in inganno** sulla loro genuinità è punito con la **reclusione da 1 a 5 anni**.
- La slide riporta pene fino a **6 anni** con aggravanti di inganno e aggiotaggio, e fino a **12 anni** in caso di abuso di informazioni privilegiate e insider trading.
- **Procedibilità**: a **querela** della persona offesa, ma **d'ufficio** se il fatto è connesso a un altro reato procedibile d'ufficio o è commesso contro **incapaci, minori, infermi** o **pubbliche autorità**.

> ⚠️ **Fonte normativa.** L'art. 612-quater è stato introdotto dalla legge italiana sull'intelligenza artificiale (legge 23 settembre 2025, n. 132). Le pene più alte citate dalla slide riguardano le aggravanti introdotte dalla stessa legge per reati come l'aggiotaggio e la manipolazione del mercato commessi con l'IA: per i dettagli conviene consultare il testo ufficiale.

**Sintesi** (p. 102): Inverse Turing Test, Proof of Personhood, deepfake (che cosa sono, a che cosa servono), detection su frame singolo e video, challenge-response, test dei sistemi e piattaforme.

**Stato dell'arte 2024** (p. 103, **non all'esame**): G. Pei et al., «Deepfake Generation and Detection: A Benchmark and Survey», arXiv:2403.17881. Rassegna su generazione (VAE, GAN e **modelli di diffusione**) e detection, nei quattro campi **face swapping**, **face reenactment**, **talking face generation** e **facial attribute editing**, con benchmark su dataset diffusi.

---

## **Parte D — Il report sulle frodi d'identità 2025**

### **12. Entrust Identity Fraud Report 2025**

#### **12.1 Metodologia e risultati chiave**

_(report pp. 1–6)_

**Il documento** (pp. 1–2): *2025 Identity Fraud Report* dell'**Entrust Cybersecurity Institute**, con dati proprietari di **Onfido**.

**Prefazione** (p. 3), di Siddharth (Bobby) Mehta, presidente del consiglio di Entrust:

- nel 2024 c'è stato **un tentativo di deepfake ogni cinque minuti**;
- le **falsificazioni digitali di documenti** sono aumentate del **244%** in un anno;
- il **13 novembre 2024** il **FinCEN** del Tesoro statunitense ha emesso un'allerta sull'uso di deepfake per aggirare verifica e autenticazione dell'identità;
- il costo globale del cybercrimine è stimato in **10.500 miliardi di dollari** annui entro il 2025 (Cybersecurity Ventures).

**Metodologia** (p. 4): dati dal **1° settembre 2023 al 31 agosto 2024**, **decine di milioni** di verifiche in **oltre 30 settori** e **195 paesi**; l'azienda stima di aver fatto risparmiare **5,5 miliardi di dollari** di perdite ai clienti. I dati riflettono il settore della **verifica dell'identità** e non sempre il mercato generale.

**Il panorama 2024** (p. 5). I truffatori **combinano** sempre più vettori:

- **PII** da data breach e phishing per creare **identità sintetiche**;
- gruppi criminali che condividono metodi e offrono **fraud-as-a-service**;
- **deepfake inviati tramite injection attack**.

La frode è sempre più **complessa e stratificata**, quindi richiede una difesa **su più fronti**.

**Risultati chiave** (p. 6):

1. **la manipolazione digitale supera la contraffazione fisica** dei documenti, per la prima volta;
2. **la frode assistita dall'IA è in crescita**: i deepfake sono il **40% di tutte le frodi biometriche**;
3. **la frode è più sofisticata e più accessibile**: con FaaS, RaaS (ransomware) e PHaaS (phishing) crescono i truffatori **amatoriali** e isolati.

| Indicatore | Valore |
|---|---|
| settori più colpiti | 1. **criptovalute**, 2. **prestiti**, 3. **banche tradizionali** |
| tipo di documento più vulnerabile | **carte d'identità nazionali** |
| documento più preso di mira | **codice fiscale indiano** (India Tax ID) |
| regione con il tasso più alto | **Asia** |
| regione con l'aumento maggiore | **Americhe** |

<!-- SOURCE_VISUAL id="TAB17-V28" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="5" type="grafico" description="Vettori di frode a rischio più alto nel 2024, emergenti e consolidati" reason="Posizione dei vettori nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.2 Andamenti, settori e regioni**

_(report pp. 7–15)_

**Tassi medi** (p. 8): stabili rispetto all'anno precedente, con frode **documentale al 4,5%** e frode **biometrica sotto il 2%**. La biometria, soprattutto con un elemento di **liveness** (video, motion), resta un **deterrente più forte** del solo documento.

**Tre periodi** (p. 9):

| Periodo | Caratteristiche |
|---|---|
| **pre-COVID** (2019) | frodi su documenti **fisici**; attacchi concentrati **dal lunedì al venerdì**, come una settimana lavorativa |
| **picco pandemico** (2020–2021) | forte aumento: il **75%** dei merchant online segnala più tentativi (Statista); **100–135 miliardi di dollari** di sussidi di disoccupazione ottenuti fraudolentemente negli USA (GAO); onboarding digitali implementati in fretta; attacchi **24 ore su 24**; alto volume ma **bassa sofisticazione** |
| **post-pandemia** (2022–2024) | tassi tornati poco sopra i livelli pre-pandemici; lavoro ibrido e **Zero Trust**; tensioni geopolitiche e sanzioni che spingono attori statali verso la frode; inflazione ai massimi decennali nel 2023; aumento di **volume e sofisticazione** con la GenAI |

**Settori sotto attacco** (pp. 10–11):

- **criptovalute**: quasi il doppio dei tentativi rispetto a qualsiasi altro settore; tentativi fraudolenti in onboarding dal **6,4% al 9,5%** (+50%); perdite da frodi crypto 2023 oltre **5,6 miliardi di dollari**, +45% (FBI); oltre **9.000** criptovalute; regolamento europeo **MiCA** (maggio 2023). Al salire del prezzo di Bitcoin salgono i tentativi di frode;
- **prestiti**: tassi e costo della vita spingono domande e documenti falsificati; la FTC ha registrato circa **26.000** casi di truffe sui prestiti con anticipo di commissioni, per **75 milioni di dollari**;
- **banche tradizionali**: tentativi fraudolenti di onboarding **+13%**; aumento dell'**856%** delle minacce via email e messaggi malevoli in 12 mesi.

I truffatori **«rinse and repeat»**: una falla sfruttata in un settore viene riprovata altrove.

**Regioni** (p. 12). I dati si riferiscono alla sede delle **aziende**, perché i truffatori nascondono la loro posizione con **VPN**:

| Regione | Tasso medio di frode |
|---|---:|
| **APAC** | **6,8%** |
| **Americhe** | **6,2%** |
| **EMEA** | inferiore, probabilmente grazie a una regolazione più forte su KYC, onboarding e crypto |

**Documenti** (pp. 13–15):

- i più presi di mira: **codice fiscale indiano**, **carta d'identità del Pakistan**, **carta d'identità del Bangladesh**, **passaporto francese**. Per il documento indiano esistono molti **modelli online**; molte carte d'identità nazionali hanno ancora **versioni cartacee**, facili da stampare;
- il **40,8%** delle frodi documentali colpisce le **carte d'identità nazionali**, che, non essendo documenti di viaggio, **non devono rispettare le linee guida ICAO** (Lezione 14) e hanno meno elementi di sicurezza. Tra i bersagli principali: **Filippine, Francia e Italia**, dove circolano ancora versioni vecchie;
- i documenti fraudolenti sono segnalati soprattutto per **autenticità visiva** (**47%**) o **validazione dei dati** (**36%**).

<!-- SOURCE_VISUAL id="TAB17-V29" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="8" type="grafico" description="Andamento dei tassi di frode documentale e biometrica in cinque anni con i tre periodi" reason="Andamento visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V30" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="13" type="classifica grafica" description="I 10 documenti più presi di mira nel 2024" reason="Classifica completa presente solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.3 Tecniche dei truffatori**

_(report pp. 16–22)_

**Falsificazioni digitali** (pp. 17–18):

| | **Fisico** | **Digitale** |
|---|---|---|
| **Contraffazione** (riproduzione completa) | stampare un documento falso e fotografarlo | creare da zero un documento digitale |
| **Falsificazione** (alterazione di un originale) | modificare un documento reale | modificare l'immagine di un documento reale |

- Le **falsificazioni digitali** sono il **57,46%** delle frodi documentali: **+244%** rispetto all'anno prima (16,7%) e **+1600%** rispetto al 2021, quando quasi tutti i documenti falsi erano contraffazioni fisiche.
- Si ottengono da **modelli online** o immagini di documenti da **data breach**, modificati con Photoshop e accelerati dalla **GenAI**. Sono più **economiche** e **scalabili**.
- Il metodo principale per inserirle nei sistemi di verifica sono gli **injection attack** (§12.4).

> 💡 **Verifica del +244% (nota didattica).** L'aumento relativo è $\dfrac{57{,}46 - 16{,}7}{16{,}7} \approx 2{,}44$, cioè +244%: la quota di falsificazioni digitali è più che triplicata in un anno.

**Deepfake: il nuovo volto della frode biometrica video** (pp. 19–20). Tecniche d'attacco ai controlli biometrici:

| Tecnica | Controlli colpiti |
|---|---|
| **foto di uno schermo** (per esempio un'immagine del profilo social) | selfie |
| **foto di una stampa** | selfie |
| **foto o video del documento** | entrambi |
| **maschera 2D** | entrambi |
| **maschera 3D** o altro oggetto 3D | entrambi |
| **deepfake** | entrambi |
| **video di uno schermo** | video/motion |
| **video di una stampa** | video/motion |

- Il **selfie** è una soluzione **passiva** e subisce attacchi più **basilari**: la **foto di uno schermo** è il **50,29%** dei tentativi fraudolenti sui selfie nel 2024.
- I controlli con **liveness attiva** (video, motion) chiedono un compito all'utente, alzano la barriera e costringono a usare **deepfake**, che sono il **40,80%** dei tentativi sulla biometria video. Analizzando **più frame** questi sistemi rilevano meglio le anomalie.
- Il report presenta il prodotto **Motion** del produttore come «100% AI driven», con modelli anti-spoofing addestrati sui deepfake: «combattere l'IA con l'IA».

**L'impatto dei deepfake** (pp. 21–22). **Usi malevoli**:

- **apertura fraudolenta** di conti aggirando il KYC;
- **phishing e truffe sugli investimenti** con finti video di celebrità o dirigenti;
- **account takeover** ingannando i controlli biometrici;
- campagne **politiche** e di **disinformazione**.

Il report distingue due categorie di deepfake:

1. **face swap**: un nuovo volto sovrapposto a una testa bersaglio; la versione rozza, un volto incollato sull'altro, è un **cheapfake**, mentre quelle sofisticate usano l'IA per fondere il volto;
2. **immagini completamente generate** da modelli generativi.

Andamento:

- il **2023** è stato il primo anno in cui i deepfake sono diventati un vettore **diffuso**: **+3000%** tra 2022 e 2023, con picco a giugno 2023;
- a **gennaio 2024** nuovo massimo storico; i volumi oscillano mentre i fornitori introducono nuove difese e i truffatori si adattano;
- un **sondaggio Deloitte 2024** indica che oltre metà dei dirigenti si aspetta più attacchi deepfake ai dati finanziari.

<!-- SOURCE_VISUAL id="TAB17-V31" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="17" type="grafico" description="Falsificazioni digitali contro contraffazioni fisiche nel tempo" reason="Andamento visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V32" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="22" type="grafico" description="Volumi dei deepfake 2022-2024 con picco a giugno 2023 e massimo a gennaio 2024" reason="Andamento visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.4 Injection attack, FaaS e identità sintetiche**

_(report pp. 23–27)_

> 📌 **Injection attack** (p. 23). Attacco informatico che compromette l'integrità del processo di acquisizione **inserendo contenuti falsi**, di solito un deepfake, **nel flusso dati**, scavalcando la cattura dal vivo del documento e del volto.

| Tipo | Descrizione |
|---|---|
| **camera virtuale** | il **più comune**: un software sostituisce la camera hardware e permette di usare qualsiasi video sorgente |
| **network injection** | meno comune ma più sofisticato: il deepfake viene inviato con **codice**, richiede competenze tecniche |

**Prevenzione a più livelli**:

1. **alla sorgente** (input video): segnali di background su media e **dispositivo** che invia il controllo;
2. **alla presentazione** (contenuto video): acquisizione biometrica in tempo reale con **interazione dinamica** e analisi delle anomalie nel movimento del volto;
3. **all'invio** (confronto incrociato): IA e ML che rilevano i deepfake arrivati nel back-end.

> 💡 **Collegamento con gli otto punti di attacco.** Un injection attack è un attacco di **tipo 2** di Ratha (Lezione 13, §1.4): non si presenta nulla al sensore, si sostituisce il segnale nel canale tra sensore ed estrattore. Per questo i test di presentation attack detection al sensore da soli non bastano.

**Fraud-as-a-Service** (p. 24). Condivisione a pagamento, soprattutto nel **dark web**, di tutorial, playbook, corsi di cybercrimine e **PII rubate** (credenziali, numeri di previdenza sociale, carte di credito). Secondo uno studio del 2023, i dati di una carta con **5.000 dollari** di credito costavano circa **110 dollari**. Crescerà perché:

1. strumenti GenAI come ChatGPT, e cloni malevoli come **WormGPT**, facilitano la produzione su scala;
2. **abbassa la barriera d'ingresso** per truffatori amatoriali.

**Identità sintetiche** (p. 25):

| Metodo | Esempio |
|---|---|
| **manipolazione** dell'identità | modificare leggermente elementi autentici, come data di nascita o nome sulla patente |
| **compilazione** | unire dati reali e inventati, come un numero di previdenza sociale reale con un documento completamente inventato |
| **fabbricazione** | identità del tutto fittizia senza PII reali: un modello di documento compilato con dati inventati più un deepfake |

- Nella frode d'identità **tradizionale** si ruba l'identità di una persona reale; in quella **sintetica** se ne crea una **nuova** combinando dati reali e falsi.
- Deloitte stima almeno **23 miliardi di dollari** di perdite negli USA entro il 2030.
- Le identità sintetiche **persistono**: se non vengono fermate al primo giorno, costruiscono un **merito creditizio** nel tempo e poi lo sfruttano al massimo.

**Globalizzazione** (pp. 26–27):

- la frode avviene **24 ore su 24, 7 giorni su 7**, con un piccolo picco tra le **3 e le 6 UTC**, forse per sfruttare le ore di presunto «fermo» delle aziende;
- i **picchi giornalieri** possono dipendere da eventi geopolitici o stagionalità, ma anche da **attacchi coordinati** di gruppi organizzati contro una o poche aziende.

<!-- SOURCE_VISUAL id="TAB17-V33" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="23" type="schema" description="Injection attack: il truffatore inserisce un deepfake nel flusso dati tramite camera virtuale o rete" reason="Schema del flusso d'attacco" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB17-V34" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="26" type="grafico" description="Distribuzione oraria delle frodi, 24/7 con piccolo picco tra le 3 e le 6 UTC" reason="Andamento visibile solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.5 Prospettive per il 2025**

_(report pp. 28–34)_

**1. Uso crescente dell'IA** (p. 29):

- **creazione di deepfake** con app di face swap;
- **voice spoofing** per aggirare il riconoscimento vocale;
- **generazione di testo e immagini** (ChatGPT, DALL-E, Midjourney) per phishing;
- **data scraping** per identità sintetiche o *credential stuffing*;
- **bot** per credential stuffing o per inviare in automatico richieste di prestito e carte di credito.

È difficile stabilire quali casi coinvolgano davvero l'IA: secondo SlashNext i messaggi di phishing malevoli sono aumentati del **4151%** dopo il lancio di ChatGPT (novembre 2022), ma non si sa quanti fossero generati con l'IA. Le aziende usano invece l'**IA applicata** per la prevenzione.

**2. Regolazione dell'IA e dei deepfake** (pp. 30–31):

| Area | Situazione descritta dal report |
|---|---|
| **USA** | nessuna legge federale organica. Esistono l'Executive Order sull'IA e il Blueprint for an AI Bill of Rights; proposte No AI FRAUD Act, NO FAKES Act, DEFIANCE Act. Leggi statali: **California** dal 2019 (AB 602 sulla pornografia deepfake non consensuale, AB 730 sui deepfake in campagna elettorale), **Texas** (SB 751 sui video per influenzare le elezioni, poi sui video espliciti), e altri stati |
| **UE** | l'**AI Act** è la legge più completa al mondo: in vigore dal **1° agosto 2024**, applicazione della maggior parte delle disposizioni dal **2 agosto 2026**. Il report cita tre categorie (rischio inaccettabile vietato, alto rischio con requisiti, resto largamente non regolato) e osserva che i deepfake **non sono vietati** ma soggetti a **obblighi di trasparenza** |
| **Regno Unito** | approccio **«pro-innovation»** con un quadro basato su cinque principi: sicurezza e robustezza, trasparenza e spiegabilità, equità, responsabilità e governance, contestabilità e rimedio. L'**Online Safety Act 2023** rende illegale condividere immagini esplicite manipolate, in circostanze specifiche |

> ⚠️ **Due imprecisioni del report.** L'AI Act distingue di norma **quattro** livelli di rischio (inaccettabile, alto, rischio limitato con obblighi di trasparenza, minimo). L'articolo sulla trasparenza dei deepfake è citato come «Article 52(3)», la numerazione della proposta; nel testo definitivo del Regolamento (UE) 2024/1689 gli obblighi sono all'**art. 50**. Sulle date di applicazione si veda la Lezione 13, §5.2.

**3. Sicurezza dei dati e Zero Trust** (p. 32). Il costo medio globale di un data breach ha raggiunto **4,88 milioni di dollari** nel 2024 (+10%, IBM). Più dati compromessi significa più PII per le frodi, quindi serve **Zero Trust** («never trust, always verify») con verifica biometrica, MFA e firma digitale.

**4. Era post-quantistica** (p. 33):

- costo del cybercrimine stimato in **15.630 miliardi di dollari** entro il 2029;
- i futuri **computer quantistici crittograficamente rilevanti** romperanno la crittografia attuale; gli attacchi **«Harvest Now, Decrypt Later»** colpiscono già oggi i dati a lunga vita;
- il **NIST** ha pubblicato i primi standard di **crittografia post-quantistica** nell'agosto 2024;
- il **61%** dei professionisti IT prevede la migrazione entro cinque anni.

**5. eID e portafogli d'identità digitale** (p. 33):

- le **eID** hanno lo stesso valore legale dei documenti fisici; il **regolamento europeo sull'identità digitale** mira all'uso transfrontaliero, ma manca ancora un formato **globalmente interoperabile**;
- Gartner prevede **mezzo miliardo** di utenti di portafogli d'identità digitale entro il 2026;
- l'**UE** impone l'emissione dei portafogli entro il 2026;
- la **TSA** statunitense accetta patenti digitali in molti stati.

**6. Biometria comportamentale contro i bot** (p. 34). I bot sono veloci e costanti, ma proprio per questo il loro comportamento è **meccanico**: la biometria comportamentale li rileva da movimento del mouse, velocità di digitazione e interazione touch (§1.2).

**7. Passaporto biometrico ICAO 2025** (p. 34). Le specifiche in evoluzione, basate sugli standard **ISO e IEC**, forniranno best practice su **equità** dei sistemi biometrici, **presentation attack detection** e **qualità** delle immagini di impronte e volto, con effetti su tutti i settori.

<!-- SOURCE_VISUAL id="TAB17-V35" source="Lezione_17_ONFIDO_2025-identity-fraud-report.pdf" page="28" type="pagina di sezione" description="Looking ahead: identity fraud in 2025" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.6 Best practice di prevenzione**

_(report pp. 35–42)_

Nella parte finale il report unisce **raccomandazioni** e **presentazione dei prodotti** del produttore (pp. 35–41):

| Raccomandazione | Contenuto |
|---|---|
| **Conoscere davvero il cliente** | l'onboarding è la **prima linea di difesa**: fermare il truffatore alla porta. Per i settori regolati la verifica KYC/AML è obbligatoria |
| **SDK invece di API** | un SDK di acquisizione garantisce qualità d'immagine costante e **acquisizione dal vivo**, riducendo le immagini manipolate e rilevando gli injection attack alla sorgente |
| **Controlli per tutto il ciclo di vita** | nei momenti a rischio si **ri-autentica** l'utente con il volto rispetto al documento dell'onboarding; con l'autenticazione **bio-to-bio** l'azienda sceglie se conservare i dati sul dispositivo o sui propri server |
| **Zero Trust e cybersecurity** | IAM, **MFA resistente al phishing**, **PKI** per cifrare le comunicazioni e **firmare digitalmente** video, immagini e documenti contro i deepfake |
| **IA contro IA** | architettura a **micro-modelli** con oltre **10.000** modelli di ML specializzati su singoli marcatori di frode, fino al **50%** di frodi documentali in più rispetto a modelli generalisti (dato del produttore) |
| **Workflow flessibili** | configurare verifiche e condizioni *if-this-then-that* per bilanciare **attrito** e **rischio** |
| **Approccio a strati** | **verifica del documento**, **verifica biometrica**, rilevamento delle **frodi ripetute** (stessi nomi, numeri di documento o volti), **verifica dei dati** (watchlist, sanzioni, PEP, prova di residenza), **segnali passivi** (dispositivo, geolocalizzazione) |
| **Analisi di pattern e rischio** | cercare schemi come i tentativi ripetuti e i **volti già noti**; micro-modelli che analizzano variazioni a livello di pixel di colore, forma e texture del documento |

La p. 42 descrive Entrust (clienti in oltre 150 paesi) e Onfido (oltre 1.200 aziende partner).

> 💡 **Il «volto già noto» è un'identificazione 1:N.** Cercare lo stesso volto dietro identità diverse significa confrontare ogni nuovo selfie con **tutti** quelli già registrati: con i relativi problemi di FPIR crescente con $N$ (Lezione 14, §16.2) e di privacy (Lezione 13).

---

### **13. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Test di Turing** / **test di Turing inverso** | l'umano giudica la macchina / la macchina giudica se l'interlocutore è umano |
| **Riconoscimento** / **test di Turing inverso** | chi sei / che cosa sei |
| **CAPTCHA** / **reCAPTCHA v3** | puzzle esplicito pass/fail / score comportamentale continuo in background |
| **Inverse Turing Test** / **Proof of Personhood** | umano o bot / umano **unico** e reale |
| **Deepfake detection** / **inverse Turing test** | il contenuto è sintetico? / l'agente è umano? |
| **Deepfake** / **spoofing** | campione generato o manipolato / oggetto falso presentato al sensore |
| **Spoofing** / **injection attack** | attacco al sensore (tipo 1) / contenuto inserito nel flusso dati (tipo 2) |
| **Face swap** / **immagine completamente generata** | volto sovrapposto a una testa reale / volto e scena creati da zero |
| **Cheapfake** / **deepfake** | manipolazione rozza / manipolazione con IA |
| **Contraffazione** / **falsificazione** di documenti | riproduzione completa / alterazione di un originale |
| **Frode d'identità tradizionale** / **sintetica** | identità reale rubata / identità nuova con dati reali e falsi |
| **Selfie** (liveness passiva) / **motion** (liveness attiva) | attacchi basilari, foto di schermo / attacchi sofisticati, deepfake |
| **Verifica dell'autenticità** (C2PA, blockchain) / **rilevamento** (forense, IA) | prova alla fonte / riconoscimento del falso dal contenuto |
| **Challenge prevedibile** / **imprevedibile** | aggirabile con video preregistrati / costringe a generare in tempo reale |

---

### **14. Sintesi della lezione**

> ✅ **In sintesi.**
> - **Inverse Turing Test**: classificazione binaria $P(\text{umano} \mid X) > T$; dai CAPTCHA, ormai aggirabili con CNN e ViT, ai test comportamentali (reCAPTCHA v3 con score 0–1 e soglie, BeCAPTCHA con GAN).
> - **Proof of Personhood**: umano unico; biometria (World ID con l'Orb NIR, IrisCode, unicità, ZKP, Face Auth), grafi sociali (BrightID), stamp (Human Passport), Proof of Humanity.
> - **Deepfake**: video, audio, immagini, testo ed emotivi; usi leciti (dati sintetici, test antispoofing) e illeciti (frodi, disinformazione); tre tipi di attacco (face swap, morphing, GenAI); strumenti come Roop, ElevenLabs, Sora 2 con cameo; DeepMasterPrints.
> - **Detection**: forense, provenienza (C2PA, blockchain), IA, challenge-response; indizi nel frame singolo (bordi, occhi, sfondo, Gestalt), nel video (rPPG con Intel FakeCatcher, flusso ottico delle labbra), nell'audio (sincronia); umani vicini al caso; rilevatori con ritaglio del volto, CNN più LSTM/GRU, dataset come FaceForensics++ e Celeb-DF.
> - **Legge**: art. 612-quater c.p. dal 2025 (reclusione da 1 a 5 anni); proposta danese del corpo sotto copyright.
> - **Report 2025**: un deepfake ogni cinque minuti, deepfake al 40% delle frodi biometriche, falsificazioni digitali +244% e sopra le contraffazioni fisiche, injection attack con camere virtuali, FaaS, identità sintetiche, criptovalute e carte d'identità nazionali come bersagli; difesa a strati con liveness attiva, Zero Trust e analisi dei pattern.
