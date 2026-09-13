# **Lezione 1: Descrizione del corso, esame e introduzione alla biometria**

---

### **0. Panoramica della lezione**

_(slide pp. 1–2)_

La prima lezione di **Tecniche e Applicazioni Biometriche** (docente: Fabio Scotti, Università degli Studi di Milano) ha una doppia funzione:

- **Parte A** presenta l'insegnamento: struttura delle lezioni, differenze rispetto a _Sistemi Biometrici_, temi che verranno affrontati e modalità d'esame;
- **Parte B** è un **crash course** di sistemi biometrici: definizioni, autenticazione e identificazione, le 7 proprietà del tratto, funzionamento di base, principali tratti e architetture dei sistemi (classici, per documenti, distribuiti, on card, on sensor).

La slide di apertura avverte che _alcuni lucidi sono tratti dall'insegnamento di «Sistemi biometrici»_, per allineare chi non lo ha frequentato. Per chi lo ha già superato la Parte B è un ripasso strutturato; in questi appunti i concetti già trattati sono richiamati con collegamenti alle lezioni del corso precedente, mentre lo spazio è dedicato a precisare le distinzioni utili all'esame.

> **Richiamo — Sistemi Biometrici.** I contenuti della Parte B corrispondono, in forma compressa, a [L01 — Introduzione ai sistemi biometrici](<../../../anno3/6_Sistemi_Biometrici/L01 - Introduzione ai sistemi biometrici.md>), [L02 — Tratti biometrici](<../../../anno3/6_Sistemi_Biometrici/L02 - Tratti biometrici e evoluzione della biometria.md>) e [L04 — Struttura dei sistemi biometrici](<../../../anno3/6_Sistemi_Biometrici/L04 - Struttura dei sistemi biometrici e aspetti analitici del tratto.md>).

---

## **Parte A — L'insegnamento e l'esame**

### **1. Struttura dell'insegnamento**

_(slide pp. 3–6)_

#### **1.1 Crash course e lezioni vere e proprie**

Le prime lezioni sono divise in **due parti**:

1. un **crash course di Sistemi Biometrici**;
2. le lezioni di **Complementi di Biometria** vere e proprie.

Un _crash course_ è definito nelle slide come un corso intensivo pensato per trasmettere le competenze e le informazioni essenziali su un argomento in tempi molto brevi, fornendo una panoramica rapida ma completa. È utile a chi non ha frequentato, sta frequentando o non frequenterà _Sistemi Biometrici_, e a tutti gli altri come ripasso.

> ⚠️ Le slide precisano che **alcuni argomenti base presentati nel crash course possono essere presenti all'esame**. La Parte B di questa lezione non è quindi "fuori programma".

> 💡 **Nota sul nome.** Nelle slide l'insegnamento compare anche come **"Complementi di Biometria"**, lo stesso nome che si trova nell'intestazione degli script di laboratorio della Lezione 12. Si tratta del nome precedente dell'attuale _Tecniche e Applicazioni Biometriche_: nei materiali i due nomi indicano lo stesso corso.

#### **1.2 Componenti delle lezioni**

Ogni lezione può contenere:

| Componente | Contenuto |
|---|---|
| **Crash course** | solo nelle prime lezioni |
| **Teoria** | modelli, algoritmi, metodi statistici |
| **Applicazioni** | casi d'uso reali dei sistemi biometrici |
| **Sistemi HW/SW** | sensori, dispositivi, librerie software |
| **Laboratorio** | non strettamente previsto; possono essere mostrati esempi di codice e librerie per alcune applicazioni |

> 📌 **Il codice dei laboratori non è richiesto all'esame.** Sono invece richiedibili gli **argomenti teorici e applicativi** presentati durante i laboratori (slide p. 23).

---

### **2. Che cosa distingue questo insegnamento da Sistemi Biometrici**

_(slide p. 7)_

L'insegnamento è progettato per **costruire sopra** le conoscenze di _Sistemi Biometrici_, ma anche per funzionare **stand-alone** o come esame a libera scelta. Le slide individuano tre fattori distintivi.

| Fattore | Che cosa significa |
|---|---|
| **Approfondimenti specializzati** | _Sistemi Biometrici_ fornisce la base sui vari tratti e sistemi; qui si va oltre con **biometria comportamentale**, **protezione della privacy** nelle applicazioni biometriche e **tecniche avanzate di fusione** dei tratti. |
| **Tecniche avanzate** | metodi e algoritmi più avanzati: **elaborazione di immagini e segnali** per la biometria e **metodi statistici avanzati** per la valutazione delle prestazioni. |
| **Applicazioni d'avanguardia** | sistemi per **ambient intelligence**, **autenticazione continua**, sistemi biometrici nella **videosorveglianza**. |

L'obiettivo dichiarato è fornire le basi per **immaginare, progettare e realizzare** nuovi sistemi e applicazioni biometriche, non solo per usarli.

---

### **3. Panoramica dei temi del corso**

_(slide pp. 8–22)_

#### **3.1 Nuovi tipi di informazione biometrica**

La prima slide della panoramica mostra un'immagine annotata con informazioni che un sistema moderno può estrarre da una scena, ben oltre la sola identità:

| Annotazione nella slide | Tipo di informazione |
|---|---|
| `happy = 0.0001%` | stima di uno **stato emotivo**, espressa come punteggio |
| `Caucasian` | attributo di **soft biometrics** (etnia stimata) |
| `h = 1,78 m`, `p = 78 kg` | **soft biometrics** antropometriche: altezza e peso stimati |
| `Metriche movimenti` | caratteristiche **comportamentali** (movimento, andatura) |
| `P(Attacco con Maschera) = 3%` | **stima probabilistica di un attacco di presentazione** (maschera) |
| `Reidentificazione` | **re-identificazione** della stessa persona in immagini diverse |

> 💡 La slide anticipa un cambio di prospettiva: un sistema biometrico moderno non risponde solo a "chi sei?", ma produce **molte stime probabilistiche** su stato, attributi, comportamento e genuinità della presentazione. Ciascuna ha una propria incertezza, e il valore numerico va interpretato come punteggio del modello, non come verità.

<!-- SOURCE_VISUAL id="TAB01-V01" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="8" type="annotated image" description="Scene with overlaid biometric estimates: emotion (happy = 0.0001%), ethnicity, height 1.78 m, weight 78 kg, movement metrics, P(mask attack) = 3%, re-identification." reason="Shows at a glance the new kinds of information extracted by modern biometric systems." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2 I temi che verranno sviluppati**

Le slide successive sono "copertine" dei grandi temi del corso, ognuna accompagnata da immagini. La colonna di destra indica dove il tema viene sviluppato, **dedotta dai titoli dei file delle lezioni** (non è un'indicazione esplicita delle slide).

| Pag. | Tema presentato | Dove viene sviluppato |
|---:|---|---|
| 9 | Sensori, ottiche e dispositivi avanzati per la biometria | Lezioni 11–12 |
| 10 | Deep learning per la biometria: dal semplice impiego alla customizzazione dei modelli | Lezioni 5–9 |
| 11 | Sorveglianza biometrica e relativi problemi di privacy | Lezioni 13 e 18 |
| 12 | Riconoscere gli individui da caratteristiche comportamentali | Lezione 16 |
| 13 | Anomaly behavior detection | Lezione 18 |
| 14 | Emozioni e stati interni: librerie e dispositivi a disposizione | Lezioni 16 e 21 |
| 15 | Progettare sistemi multimodali | Lezione 15 |
| 16 | Autenticazione continua, da fusione di tratti o da sistemi monomodali | Lezioni 16 e 21 |
| 17 | Attacco e difesa per i principali sistemi biometrici | Lezioni 19–20 |
| 18 | Deepfake liveness detection (single frame / video) | Lezione 17 |
| 19 | Sistemi ad elevata sicurezza | Lezione 14 |
| 20 | Privacy biometrica e la sua difesa | Lezione 13 |
| 21 | Sistemi biometrici innovativi | Lezione 21 |

Quattro di queste slide contengono informazione utile anche senza le lezioni successive.

**Deep learning (p. 10).** Lo schema mostra la pipeline tipica di una **rete convoluzionale**: _Convolution → Max Pooling → Convolution → Max Pooling → Flattened → Fully Connected Feedforward network_, con uscita di classificazione (l'esempio generico è _cat / dog_, quello biometrico è _Face / nonFace_). È citato **DeepFace** (Meta) come esempio di rete deep per il riconoscimento del volto.

<!-- SOURCE_VISUAL id="TAB01-V02" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="10" type="diagram" description="CNN pipeline (convolution, max pooling, convolution, max pooling, flatten, fully connected) classifying cat/dog and face/non-face; DeepFace (Meta) example." reason="First visual reference of the CNN architecture developed in Lessons 5–9." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sistemi multimodali (p. 15).** La slide associa un valore percentuale a ciascuna modalità: **volto 67%**, **voce 71%**, **camminata 30%**, **soft biometrics 7%**. La slide non specifica se si tratti di punteggi di matching o di confidenza; il messaggio è che **ogni modalità produce un proprio punteggio**, con affidabilità diversa, e che progettare un sistema multimodale significa decidere come **fondere** questi punteggi.

<!-- SOURCE_VISUAL id="TAB01-V03" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="15" type="annotated image" description="Multimodal scenario with per-modality percentages: face 67%, voice 71%, gait 30%, soft biometrics 7%." reason="Introduces score-level fusion, developed in Lesson 15." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Privacy biometrica (p. 20).** La slide pone la domanda _"Where is the biometric data stored?"_ e confronta due scelte: **Personal Storage** (dato conservato presso l'utente, ad esempio su carta o dispositivo personale), associata a _Privacy++_, e **Database Storage** (archivio centralizzato), associata a un segnale di attenzione. Il concetto verrà ripreso nelle architetture on card (§14.8) e nella Lezione 13. L'abbinamento di _Privacy++_ al Personal Storage è coerente con il resto del corso, ma la disposizione grafica delle etichette non è ricavabile dal livello testo del PDF e va confermata sull'immagine.

<!-- SOURCE_VISUAL id="TAB01-V04" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="20" type="diagram" description="«Where is the biometric data stored?» — personal storage (Privacy++) versus database storage (warning sign)." reason="Visual summary of the storage/privacy trade-off." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3 Una disciplina multidisciplinare**

_(slide p. 22)_

Progettare sistemi biometrici richiede conoscenze da sei aree:

| Area | Contributo |
|---|---|
| **Pattern matching** | confronto tra rappresentazioni, decisione |
| **Elaborazione di immagini e segnali** | filtraggio, enhancement, estrazione di caratteristiche |
| **Statistica** | stima degli errori, soglie, valutazione delle prestazioni |
| **Ottica e dispositivi** | qualità dell'acquisizione, sensori |
| **Sicurezza** | attacchi, anti-spoofing, protezione dei template |
| **Interazione uomo-macchina** | usabilità, accettabilità, cooperazione dell'utente |

<!-- SOURCE_VISUAL id="TAB01-V05" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="22" type="concept map" description="Knowledge in biometric systems at the centre of six areas: pattern matching, image and signal processing, statistics, optics and devices, security, human-machine interaction." reason="Visual summary of the multidisciplinary nature of the course." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Modalità d'esame**

_(slide pp. 23–26)_

#### **4.1 Regole dell'esame**

| Domanda | Risposta delle slide |
|---|---|
| Tipologia | **domande ed esercizi di progettazione a risposta multipla** |
| Materiale didattico | dispense su Ariel (`https://ariel.unimi.it/`) e letture/video consigliati |
| Laboratorio e codice | **non presenti** all'esame; possono essere chiesti gli argomenti teorici e applicativi presentati nei laboratori |
| Salti d'appello | **no** |
| Temi d'esame | **no** (non vengono distribuiti testi di esami passati) |
| Autovalutazione | test a lezione **simili all'esame** |

> 📌 L'esame è a **risposta multipla** ma comprende **esercizi di progettazione**: non basta riconoscere una definizione, bisogna saper scegliere e dimensionare una soluzione.

#### **4.2 Iscrizione**

_(slide p. 24)_

- L'iscrizione all'appello su **UNIMIA** è obbligatoria: chi non è iscritto **non può sostenere l'esame**, senza eccezioni.
- Il consiglio è di segnarsi la scadenza sul calendario **almeno 15 giorni prima**.
- Non esiste la reiscrizione automatica: in caso di esito negativo **bisogna iscriversi di nuovo a ogni appello**.

#### **4.3 Riferimenti bibliografici**

_(slide p. 25)_

La slide mostra alcuni testi di riferimento **non obbligatori**, precisando che **il materiale su Ariel è più che sufficiente**. I titoli compaiono solo come immagini di copertina e non sono riportati nel testo della slide.

<!-- SOURCE_VISUAL id="TAB01-V06" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="25" type="book covers" description="Optional reference books (titles only visible in the cover images)." reason="Lets the student identify the suggested books; titles are not in the text layer." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.4 Come prepararsi**

_(slide p. 26)_

La slide indica quattro aree di preparazione:

1. **precisione sulle nozioni**: le domande a risposta multipla premiano le definizioni esatte (per esempio, autenticazione e identificazione non sono sinonimi);
2. **dimensionamenti ed esercizi**: calcoli su soglie, tassi d'errore, dimensioni di database e test;
3. **procedure**: la sequenza corretta delle fasi di un sistema (acquisizione, controllo qualità, estrazione, matching, decisione);
4. **porsi delle domande e trovare le risposte**.

---

## **Parte B — Introduzione ai sistemi biometrici (crash course)**

### **5. Un nuovo modo di identificare**

_(slide pp. 27–30, 39–40)_

#### **5.1 I quattro fattori di identificazione**

La slide p. 29 classifica i modi con cui una persona può dimostrare la propria identità:

| Fattore | Esempi | Famiglia |
|---|---|---|
| **Una cosa che sai** | password, PIN | metodi classici |
| **Una cosa che hai** | chiave, carta, documento | metodi classici |
| **Quello che sei** | iride, impronta | sistemi biometrici |
| **Quello che fai** | firma, voce | sistemi biometrici |

I metodi classici verificano **qualcosa che si possiede o si conosce**, quindi qualcosa che può essere ceduto, rubato o dimenticato. La biometria verifica **la persona stessa**, attraverso il corpo o il comportamento.

<!-- SOURCE_VISUAL id="TAB01-V07" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="29" type="diagram" description="Four identification factors (something you know, have, are, do) grouped into classic methods and biometric systems." reason="Visual anchor for classic versus biometric methods." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2 Definizione di biometria**

> 📌 **Biometria**: insieme di tecniche **automatiche** per il **riconoscimento degli individui** basato sulle loro **caratteristiche fisiche e comportamentali**.

La definizione contiene tre vincoli:

- **automatiche**: il riconoscimento è svolto da un sistema, non da un esperto umano (un esame dattiloscopico manuale non è, in senso stretto, un sistema biometrico);
- **riconoscimento degli individui**: l'obiettivo è associare un'identità, non soltanto classificare (per esempio, stimare l'età è una stima di attributo, non un riconoscimento);
- **caratteristiche fisiche e comportamentali**: sono ammessi entrambi i tipi di tratto.

#### **5.3 Tratti fisici e comportamentali**

_(slide p. 39)_

| Tratti **fisici** (quello che sei) | Tratti **comportamentali** (quello che fai) |
|---|---|
| iride | voce |
| impronta digitale | firma |
| geometria della mano | camminata (_gait_) |
| volto | … |

> ⚠️ La distinzione non è sempre netta. La **voce** dipende anche dall'anatomia del tratto vocale, e la **firma** dipende dalla struttura della mano. La classificazione si basa sulla componente **dominante**: nel tratto comportamentale conta il modo in cui un'azione è eseguita, ed è proprio questa componente a renderlo più variabile nel tempo.

#### **5.4 Confronto qualitativo della sicurezza**

_(slide p. 40)_

La slide confronta qualitativamente il **livello di sicurezza** delle tre modalità "qualche cosa che sai", "qualche cosa che hai" e "qualche cosa che sei". Il messaggio, coerente con i vantaggi elencati a p. 41, è che la modalità biometrica offre il **livello di sicurezza potenziale più elevato**, perché il tratto non può essere ceduto o dimenticato e falsificarlo è più difficile che falsificare una chiave o un documento.

<!-- SOURCE_VISUAL id="TAB01-V08" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="40" type="bar chart" description="Qualitative security level of «something you have», «something you are», «something you know»." reason="Shows the relative ranking of the three authentication factors." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> **Richiamo — Sistemi Biometrici, L01 §2.10.** Lo stesso grafico è commentato in [L01 — Introduzione ai sistemi biometrici](<../../../anno3/6_Sistemi_Biometrici/L01 - Introduzione ai sistemi biometrici.md>): i tratti biometrici offrono un potenziale di sicurezza più alto, soprattutto se **combinati** con token o password (_multi-factor authentication_).

> ⚠️ Il livello di sicurezza è **potenziale**. Un sistema biometrico con soglia mal scelta, sensore privo di anti-spoofing o template non protetti può essere meno sicuro di una buona password. Questo tema attraversa tutto il corso (Lezioni 13, 19, 20).

---

### **6. Riconoscimento: autenticazione e identificazione**

_(slide pp. 31–34)_

#### **6.1 Riconoscimento dell'identità**

> 📌 **Riconoscimento dell'identità**: l'operazione che **associa un'identità a un individuo**.

Il riconoscimento si divide in due categorie, con **funzione e complessità diverse**:

- **verifica dell'identità**, detta **autenticazione**;
- **ricerca dell'identità**, detta **identificazione**.

#### **6.2 Autenticazione (1:1) e identificazione (1:N)**

| | **Autenticazione** (verifica) | **Identificazione** (ricerca) |
|---|---|---|
| Domanda | _"Sono chi dico di essere?"_ | _"Chi sono io?"_ |
| Input | tratto + **identità dichiarata** (nome, PIN, badge) | solo il tratto |
| Confronti | **uno a uno (1:1)**: col template dell'identità dichiarata | **uno a molti (1:N)**: con i template del database |
| Output | **conferma o nega** l'identità dichiarata (Yes/No) | **l'identità** trovata, oppure "utente non identificato" |
| Costo computazionale | costante rispetto alla dimensione del DB | cresce con $N$ |

Nella terminologia internazionale (ISO/IEC 2382-37) le due operazioni si chiamano _biometric verification_ e _biometric identification_; nelle slide il termine "autenticazione" è usato come sinonimo di verifica.

> ⚠️ **Perché l'identificazione è più difficile, non solo più lenta.** In autenticazione un impostore ha una sola occasione di essere accettato per errore: il confronto col template dichiarato. In identificazione ogni probe viene confrontato con $N$ template, quindi ha $N$ occasioni di produrre un falso match. A parità di algoritmo e soglia, **l'errore di identificazione cresce con la dimensione del database**.

> 💡 **Nota didattica (non presente nelle slide) — dimensionamento.** Se ogni confronto con un template non corrispondente produce un falso match con probabilità $FMR$, e i confronti sono indipendenti, la probabilità che un probe produca **almeno un** falso match su $N$ template è
>
> $$FPIR \approx 1-(1-FMR)^N \approx N \cdot FMR \qquad (N \cdot FMR \ll 1)$$
>
> dove $FPIR$ è il _False Positive Identification Rate_. Con $FMR = 10^{-4}$ e $N = 1000$ si ottiene $1-(1-10^{-4})^{1000} \approx 0{,}095$: un errore trascurabile in verifica diventa quasi **un falso allarme ogni dieci ricerche** in identificazione. L'approssimazione lineare vale solo finché $N \cdot FMR$ è piccolo (qui dà $0{,}1$). La relazione è sviluppata in [L06A §16](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>) e [L6B §11](<../../../anno3/6_Sistemi_Biometrici/L6B - Dispensa operativa sulla misura dei parametri dei sistemi biometrici.md>).

#### **6.3 Identificazione chiusa e aperta**

_(slide p. 33)_

| | **Identificazione chiusa** (_closed-set_) | **Identificazione aperta** (_open-set_) |
|---|---|---|
| Ipotesi | il soggetto **appartiene** a un insieme di identità note | il soggetto **può non essere** nel database |
| Compito | stabilire **quale** identità nota corrisponde | stabilire **se** il soggetto è noto e, in caso positivo, chi è |
| Esito "nessuno" | non previsto: si restituisce sempre l'identità più simile | necessario: il sistema deve saper rispondere "non identificato" |

Le slide danno due esempi di scenario **aperto**:

1. **videosorveglianza biometrica**: nuovi individui entrano continuamente nelle immagini; vanno inseriti nel database e **ri-identificati** nei vari fotogrammi finché non escono dalla scena;
2. **impronte latenti** raccolte sulle scene del crimine: arricchiscono un database di sospetti **senza identità nota**, che devono comunque essere ricercati nel tempo.

> ⚠️ In un sistema **chiuso** la decisione può essere "prendi il template più simile" (_rank-1_). In un sistema **aperto** questo è sbagliato: il template più simile esiste sempre, anche quando il soggetto non è nel database. Serve quindi **anche una soglia**, sotto la quale il sistema risponde _User not identified_ (§14.4).

#### **6.4 Terminologia attuale**

_(slide p. 34)_

$$\text{Riconoscimento biometrico} \begin{cases} \text{Autenticazione (1:1)} \\ \text{Identificazione (1:N)} \end{cases}$$

**Riconoscimento** è quindi il termine generale, che comprende entrambe le operazioni. È un errore frequente usarlo come sinonimo di identificazione.

<!-- SOURCE_VISUAL id="TAB01-V09" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="34" type="terminology tree" description="Biometric recognition split into authentication (1:1) and identification (1:N)." reason="Fixes the hierarchy of the terms." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Riconoscimento positivo e negativo**

_(slide pp. 35–36)_

#### **7.1 Definizioni**

Autenticazione e identificazione possono essere **positive** o **negative**, a seconda di **che cosa il sistema vuole impedire**.

| | **Positivo** | **Negativo** |
|---|---|---|
| Obiettivo (slide) | stabilire con elevata accuratezza che l'utente **sia** chi dice di essere | stabilire con elevata accuratezza se l'utente **non è** chi dice di essere |
| Frode impedita | _"prevent the use of a **single identity** by **several people**"_ | _"prevent the use of **multiple identities** by a **single person**"_ |
| Tipico scenario | controllo accessi: solo gli abilitati entrano | watch-list, prevenzione di iscrizioni multiple |

> ⚠️ **Precisazione sulle slide.** Il titolo della slide p. 35 riporta "positiva e **passiva**", ma il contenuto (e la letteratura) usa la coppia **positiva / negativa**. "Passiva" è un refuso: il termine corretto è **negativa**.

> 💡 **Nota didattica (non presente nelle slide).** Nel riconoscimento **negativo** l'utente, esplicitamente o implicitamente, **nega** di appartenere a un database: chi chiede un secondo sussidio dichiara di non averne già uno, chi passa davanti alla telecamera di un aeroporto non dichiara di essere un ricercato. Il sistema cerca invece di dimostrare che **è** presente. Per questo il riconoscimento negativo è quasi sempre un'**identificazione**: non esiste un'identità dichiarata con cui fare un confronto 1:1.

#### **7.2 Esempi**

| Scenario | Classificazione | Perché |
|---|---|---|
| Telecamere controllano se chi passa **non è un terrorista** presente in una lista | **identificazione negativa** | nessuna identità dichiarata; si vuole scoprire se il soggetto è nella watch-list |
| L'accesso a un sito militare controlla l'**iride** per verificare l'appartenenza alla **lista degli abilitati** | **identificazione positiva** | nessuna identità dichiarata; si vuole ammettere solo chi è nella lista |
| Al **bancomat** si controlla che chi usa la carta ne sia il **possessore** | **autenticazione positiva** | la carta fornisce l'identità dichiarata; confronto 1:1 |

> 💡 **Come classificare un nuovo scenario.** Servono due domande indipendenti.
>
> 1. **1:1 o 1:N?** Se l'utente fornisce un'identità (carta, badge, nome), il confronto avviene con un solo template: è un'autenticazione. Se il sistema cerca in una lista senza identità dichiarata, è un'identificazione.
> 2. **Positivo o negativo?** Se il sistema deve confermare che la persona **è** un soggetto registrato e autorizzato, il riconoscimento è positivo. Se deve accorgersi che la persona **è già presente** in una lista a cui dichiara, esplicitamente o implicitamente, di non appartenere (watch-list, beneficiari già iscritti), è negativo.

> 📌 **Solo i metodi biometrici possono realizzare un'identificazione negativa** (slide p. 41). Una persona può procurarsi più password o più documenti e presentarsi con identità diverse, ma non può presentare un tratto biometrico diverso dal proprio: il sistema può quindi accorgersi che due identità dichiarate appartengono alla stessa persona.

---

### **8. Probe e gallery**

_(slide pp. 37–38)_

| | **Sonda (_probe_)** | **Galleria (_gallery_)** |
|---|---|---|
| Che cos'è | il campione biometrico **attualmente interrogato**: il dato che si vuole identificare o verificare | il **database** o la collezione di campioni **memorizzati** con cui la sonda viene confrontata |
| Quando nasce | al momento del riconoscimento | durante l'enrollment, prima del riconoscimento |
| Caratteristiche | acquisita "sul campo", qualità variabile | di solito **pre-elaborata** e ben documentata, spesso con **metadati** (nomi, ID, altri dettagli identificativi) utili al matching |
| Esempio (volto) | l'immagine del volto della persona da riconoscere | l'insieme di immagini del volto raccolte in precedenza e annotate |

La sonda **viene confrontata con la galleria** per trovare una corrispondenza (identificazione) o confermare un'identità (autenticazione). In autenticazione il confronto usa solo l'elemento della galleria associato all'identità dichiarata; in identificazione usa tutta la galleria o una sua porzione.

> 💡 _Probe_ e _gallery_ sono i termini standard dei benchmark (per esempio le valutazioni di riconoscimento facciale): un esperimento di identificazione si descrive indicando **quali immagini formano la galleria** e **quali sono usate come sonde**.

---

### **9. Vantaggi e svantaggi dei sistemi biometrici**

_(slide pp. 41–42)_

#### **9.1 Vantaggi**

1. **I tratti sono sempre con te**: non possono essere dimenticati né usati da altri.
2. **Falsificare un tratto biometrico è molto più difficile** che falsificare un documento o una chiave.
3. **L'accuratezza di identificazione può essere molto più elevata** dei metodi tradizionali.
4. **Si possono combinare con i metodi tradizionali** (autenticazione a più fattori).
5. **Solo i metodi biometrici possono realizzare un'identificazione negativa** ("il sistema dice che io non sono lui", §7).
6. **Riducono quasi a zero i reclami di ripudio** ("sono innocente, qualcun altro ha usato il mio PIN").

> 💡 Il **non ripudio** è una conseguenza diretta del punto 1: se l'operazione è stata autorizzata dal tratto, l'utente non può sostenere di aver ceduto o perso la credenziale, come invece può fare con un PIN.

#### **9.2 Svantaggi**

1. **Costo maggiore** dei sistemi tradizionali.
2. **La risposta è un livello di matching, non una decisione binaria**: il sistema produce un punteggio di somiglianza e la decisione Yes/No dipende da una **soglia**, quindi esistono sempre errori di entrambi i tipi (§12.4).
3. **Percezione di invasione della privacy**: alcune persone non desiderano essere "schedate".
4. **Non si possono cambiare a piacimento**: una password compromessa si sostituisce, un'impronta no.
5. **Non tutti possiedono tutti i tratti**: mancanza dell'iride, impronte usurate, assenza della voce.

> ⚠️ Lo svantaggio 4 è il più grave dal punto di vista della sicurezza: un template compromesso resta compromesso per sempre. È la motivazione dei **template revocabili** (_cancellable biometrics_) e delle tecniche di protezione della privacy trattate nella Lezione 13.
>
> **Richiamo — Sistemi Biometrici, L13C §11–12.** Il problema della non revocabilità e la contromisura delle permutazioni del template sono introdotti in [L13C — Iride nel visibile, spoofing e antispoofing](<../../../anno3/6_Sistemi_Biometrici/L13C - Iride nel visibile, scenari operativi, spoofing e antispoofing.md>).

---

### **10. Le 7 proprietà del tratto biometrico**

_(slide p. 43)_

Un tratto è utilizzabile in un sistema biometrico se possiede, in misura adeguata, sette proprietà.

| # | Proprietà (slide) | Definizione (slide) | Domanda di progetto | Termine inglese |
|---:|---|---|---|---|
| 1 | **Universalità** | ogni persona deve possedere il tratto | quanti utenti ne sono privi? | _Universality_ |
| 2 | **Unicità** | due persone non devono avere lo stesso tratto | quanto distingue due individui? | _Uniqueness / Distinctiveness_ |
| 3 | **Permanenza** | la caratteristica deve essere invariante nel tempo | quanto cambia con età, usura, malattie? | _Permanence_ |
| 4 | **Misurabilità** | il tratto deve poter essere esaminato quantitativamente | si acquisisce facilmente con un sensore? | _Collectability_ |
| 5 | **Performabilità** | accuratezza adeguata, garantita senza particolari condizioni operative | l'accuratezza regge fuori dal laboratorio? | _Performance_ |
| 6 | **Accettabilità** | percentuale di persone disposte ad accettare il sistema | gli utenti lo useranno davvero? | _Acceptability_ |
| 7 | **Circonvenzione** | grado di difficoltà nell'ingannare il sistema con tecniche fraudolente | quanto è facile un attacco di spoofing? | _Circumvention_ |

> ⚠️ **Attenzione al verso della "circonvenzione".** Nelle slide è definita come **grado di difficoltà** nell'ingannare il sistema: un valore alto è quindi un pregio. Nella letteratura anglosassone (Jain, Ross, Prabhakar, 2004) _circumvention_ misura invece **quanto facilmente** il sistema può essere ingannato: un valore alto è un difetto. Davanti a una tabella comparativa dei tratti bisogna **verificare quale convenzione è adottata** prima di leggere "alto" o "basso". Il termine italiano corretto è _circonvenzione_ (la slide scrive "Circonvezione").

> 💡 Nessun tratto massimizza tutte e sette le proprietà. Le proprietà sono spesso **in tensione**: il volto ha alta accettabilità e misurabilità ma unicità e permanenza più basse dell'iride, che a sua volta richiede un'acquisizione più cooperativa. La scelta del tratto è un **compromesso dettato dall'applicazione**.

> **Richiamo — Sistemi Biometrici, L03.** La tabella comparativa dei tratti rispetto a queste proprietà è discussa in [L03 — Comparazione dei sistemi biometrici](<../../../anno3/6_Sistemi_Biometrici/L03 - Comparazione dei sistemi biometrici + Aspetti di Privacy, GDPR e AI Act.md>).

I tratti che **non** possiedono tutte e sette le proprietà possono comunque essere usati come supporto: sono le **soft biometrics** (§13.7).

---

### **11. Sistemi reali e applicazioni**

_(slide pp. 44–51)_

#### **11.1 Sistemi in uso da oltre vent'anni**

_(slide p. 44)_

| Sistema (didascalia della slide) | Tratto | Note della slide |
|---|---|---|
| Aeroporto di **Schiphol** — programma **Privium** | iride | frequent traveller, attivo dal **2001** |
| **Aeroporto di Francoforte** | iride | ora non più attivo con l'iride |
| **Disney World** — _Ticket Tag system_ | impronte | attivo dal **1996**; dal **2005** usato per tutti gli ingressi |
| **Germania 2004 — Summer Olympics** | impronte | — |

La slide riporta inoltre le etichette _Test, Pilots_ e _Frequent Flyers_, che classificano il tipo di impiego.

> ⚠️ **Precisazione sulle slide.** La didascalia indica "Schiphol Airport (**Belgio**)", ma l'aeroporto di Schiphol si trova ad **Amsterdam, nei Paesi Bassi**. Inoltre i Giochi Olimpici estivi del 2004 si sono svolti ad **Atene**: la didascalia "Germania 2004 — Summer Olympics" non chiarisce il legame con la Germania, che va verificato sull'immagine.

<!-- SOURCE_VISUAL id="TAB01-V10" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="44" type="photo collage" description="Long-running real deployments: Schiphol Privium iris (2001), Frankfurt airport iris, Disney World Ticket Tag fingerprints (1996/2005), «Germania 2004 - Impronte / Summer Olympics»; labels «Test, Pilots» and «Frequent Flyers»." reason="Needed to verify which photo each caption refers to." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.2 Automated Border Control (ABC)**

_(slide p. 45)_

I sistemi di controllo automatico delle frontiere sono citati con due esempi: **Visionbox**, che combina **volto e impronta**, e le **SmartGates** della **Nuova Zelanda**. Il tema è sviluppato nella Lezione 14.

> ⚠️ **Nota terminologica.** La slide scrive «**Advanced** Border Control», ma la sigla ABC sta per **Automated Border Control**, che è la forma standard (in Sistemi Biometrici, L01 §3.3, compare come «Automatic Border Control»). All'esame va evitata la forma «Advanced».

<!-- SOURCE_VISUAL id="TAB01-V11" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="45" type="photo" description="Automated Border Control gates: Visionbox face+fingerprint, SmartGates in New Zealand." reason="Concrete image of an ABC e-gate." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.3 Sorveglianza e applicazioni commerciali**

_(slide pp. 46–47)_

La slide p. 46 illustra sistemi di **sorveglianza** biometrica; la p. 47 elenca applicazioni commerciali: **videosorveglianza**, **sportelli bancomat (ATM)** e **supermercati**.

<!-- SOURCE_VISUAL id="TAB01-V12" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="46" type="photo collage" description="Examples of biometric surveillance systems." reason="The slide has no text beyond its title; the images carry the content." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB01-V13" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="47" type="photo collage" description="Commercial applications: video surveillance, ATMs, supermarkets." reason="Concrete examples of commercial deployments." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.4 Dispositivi mobili e applicazioni particolari**

_(slide pp. 48–49)_

La slide p. 48 ricostruisce l'arrivo della biometria nei dispositivi mobili:

| Dispositivo (slide) | Anno (slide) | Tecnologia |
|---|---|---|
| Willcom WX310J | 2006 | impronta |
| Apple iPhone — _Touch ID_ | "iPhone 5, fino a 2020" | impronta |
| Samsung Galaxy S5 | 2015 | impronta |
| Honor 7 | 2015 | impronta |
| Google Nexus (5 e 6) — _Fingerprint Sensor_ | 2015 | impronta |
| Apple — _Face ID_ | "dal 2020" | volto |

> ⚠️ **Precisazione sulle slide.** Alcune date sono approssimate. _Touch ID_ è stato introdotto con l'**iPhone 5s (2013)**; _Face ID_ con l'**iPhone X (2017)**, non nel 2020; il **Galaxy S5** è del **2014**; i Nexus con sensore d'impronta sono i **Nexus 5X e 6P (2015)**. Il messaggio della slide, cioè il passaggio da impronta a volto nei dispositivi di fascia alta, resta valido.

<!-- SOURCE_VISUAL id="TAB01-V14" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="48" type="photo timeline" description="Mobile devices with biometrics: Willcom WX310J (2006), iPhone Touch ID, Samsung Galaxy S5, Honor 7, Nexus fingerprint sensor, Apple Face ID." reason="Needed to check the device labels and dates flagged in the text." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La slide p. 49 mostra applicazioni particolari: **LiveGrip™** (Advanced Biometrics, prototipo), la tecnologia di **autenticazione delle vene del dito a impugnatura** di **Hitachi**, una **serratura a impronta** Digi, un **dispositivo portatile** delle forze armate statunitensi e il **lucchetto smart a impronta** Xiaomi Uodi Kitty.

<!-- SOURCE_VISUAL id="TAB01-V15" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="49" type="product photos" description="Special-purpose devices: LiveGrip prototype, Hitachi grip-type finger vein, Digi fingerprint door lock, US forces portable device, Xiaomi Uodi Kitty padlock." reason="Shows the variety of biometric device form factors." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **11.5 Applicazioni per settore**

_(slide p. 50)_

| **Forensi** | **Governative** | **Commerciali** |
|---|---|---|
| identificazione dei corpi | carte d'identità e passaporti (USA, Svizzera, …) | ATM (bancomat, …) |
| investigazione, criminologia (\*) | votazioni elettorali, patenti di guida | controllo di accesso |
| determinazione della parentela | controllo delle frontiere (\*\*) | telefoni cellulari |
| bambini scomparsi | carta sanitaria (protezione dati sensibili) | e-commerce |
| intelligence | controllo di accesso | smart card |
| | | internet banking |

- (\*) L'**FBI** usa un sistema per le impronte (**IAFIS**) con **47 milioni × 10 impronte** e oltre **50.000 accessi al giorno**.
- (\*\*) Il programma **US-VISIT** (volto + impronta) gestisce circa **500 milioni di persone all'anno** che transitano per le frontiere degli Stati Uniti.

> 💡 **Nota didattica (non presente nelle slide).** I due dati sono storici: l'IAFIS è stato sostituito nel 2014 dal sistema **NGI** (_Next Generation Identification_) dell'FBI, e US-VISIT è confluito nel 2013 nell'**OBIM** (_Office of Biometric Identity Management_). Il valore didattico è l'ordine di grandezza: i sistemi forensi e governativi sono **identificazioni 1:N su decine o centinaia di milioni di soggetti**, dove la crescita dell'errore con $N$ (§6.2) diventa il problema dominante.

#### **11.6 Trend del mercato**

_(slide p. 51)_

La slide mostra un grafico sull'andamento del mercato biometrico. I valori non sono presenti nel testo della slide e non vengono quindi riportati.

<!-- SOURCE_VISUAL id="TAB01-V16" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="51" type="chart" description="General biometric market trend." reason="Figures are only in the image; needed to cite market growth." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Funzionamento di base**

_(slide pp. 52–56)_

#### **12.1 Enrollment e riconoscimento**

_(slide p. 53)_

| Fase | Che cosa accade |
|---|---|
| **Acquisizione (_Enrollment_)** | il tratto viene acquisito **per la prima volta** e registrato nel sistema, oppure viene creato il **documento biometrico** |
| **Riconoscimento (identificazione / verifica)** | il tratto viene **acquisito di nuovo**; se risulta **sufficientemente aderente** alle informazioni registrate, l'accesso è consentito |

L'espressione "sufficientemente aderente" è centrale: due acquisizioni dello stesso tratto non sono mai identiche (posizione, pressione, illuminazione, invecchiamento), quindi il riconoscimento è sempre una **misura di somiglianza** confrontata con una soglia, mai un confronto di uguaglianza.

#### **12.2 La biometria come sistema di pattern recognition**

_(slide p. 54)_

Un sistema biometrico è un caso particolare di sistema di **pattern recognition**, articolato in due fasi.

| Fase | Catena |
|---|---|
| **Training** | Acquisition → Feature extraction → **DataBase** |
| **Testing** | Acquisition → Feature extraction → **Matching** (contro il DataBase) → Outcome |

Nell'esempio della slide l'oggetto riconosciuto è una mela (_Outcome = apple_). In biometria il "training" corrisponde all'**enrollment** e l'outcome è un'**identità** o una decisione Yes/No.

<!-- SOURCE_VISUAL id="TAB01-V17" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="54" type="diagram" description="Generic pattern recognition system: training (acquisition, feature extraction, database) and testing (acquisition, feature extraction, matching, outcome = apple)." reason="Shows the parallel between generic pattern recognition and biometric systems." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.3 Fasi e terminologia**

_(slide p. 55)_

$$\text{Tratto} \xrightarrow{\text{acquisition}} \text{Campione} \xrightarrow{\text{feature extraction}} \text{Caratteristiche} \xrightarrow{\text{coding}} \text{Template}$$

| Termine | Inglese | Significato |
|---|---|---|
| **Tratto** | _trait_ | la caratteristica fisica o comportamentale della persona (l'impronta sul dito) |
| **Campione** | _sample_ | il dato grezzo acquisito dal sensore (l'immagine dell'impronta) |
| **Caratteristiche** | _features_ | le informazioni discriminanti estratte dal campione (le minuzie) |
| **Codifica** | _coding_ | la trasformazione delle feature in un formato memorizzabile e confrontabile |
| **Template** | _template_ | la rappresentazione compatta memorizzata nel database (per esempio le coordinate e gli angoli delle minuzie) |

- In **enrollment**: Acquisition → Feature extraction → Coding → **Template nel DataBase**.
- In **identificazione / verifica**: Acquisition → Feature extraction → Coding → **Matching** con i template del DataBase → **Yes/No**.

> ⚠️ **Campione e template non sono la stessa cosa.** Il campione è il dato grezzo (un'immagine); il template è la sua rappresentazione compatta, dopo estrazione e codifica. Memorizzare **template** anziché **campioni** riduce lo spazio occupato e, in parte, il rischio per la privacy, ma non lo elimina: da alcuni template è possibile ricostruire un campione plausibile.

> **Richiamo — Sistemi Biometrici, L05.** Spazio delle feature, rappresentazione e matching sono trattati in [L05 — Rappresentazione, estrazione delle caratteristiche e matching](<../../../anno3/6_Sistemi_Biometrici/L05 - Rappresentazione, Estrazione delle Caratteristiche, Matching e Indicizzazione nei DB biometrici.md>).

<!-- SOURCE_VISUAL id="TAB01-V18" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="55" type="diagram" description="Enrollment and identification chains with terminology: trait, sample, features, coding, template, database, matching, yes/no." reason="Core terminology diagram of the course." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.4 Matching score e soglia**

_(slide p. 56)_

Il modulo di matching non restituisce direttamente Sì o No: produce un **matching score**, cioè un grado di somiglianza tra il template della sonda e quello memorizzato. La decisione nasce dal confronto con una **soglia**; nell'esempio della slide la soglia vale **87%**.

Indicando con $s = S(X_Q, X_T)$ lo score di similarità tra il template della sonda $X_Q$ e il template memorizzato $X_T$, e con $t$ la soglia:

$$\text{decisione} = \begin{cases} \text{Yes (match)} & \text{se } s \ge t \\ \text{No (non-match)} & \text{se } s < t \end{cases}$$

La slide illustra i due estremi:

- **soglia molto bassa → "entrano tutti"**: anche gli impostori superano la soglia;
- **soglia molto alta → "non entra nessuno"**: anche gli utenti genuini vengono respinti.

<!-- SOURCE_VISUAL id="TAB01-V19" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="56" type="diagram" description="Matching score compared with a threshold (87%); low threshold «entrano tutti», high threshold «non entra nessuno»." reason="Visual anchor for the threshold trade-off." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> **Richiamo — Sistemi Biometrici, L06A §7–9.** Chiamiamo $p_{gen}(s)$ la densità degli score **genuini** (confronti tra campioni della stessa persona) e $p_{imp}(s)$ quella degli score **impostori** (confronti tra persone diverse), con score normalizzati in $[0,1]$. Allora
>
> $$FMR(t) = \int_{t}^{1} p_{imp}(s)\,ds \qquad\qquad FNMR(t) = \int_{0}^{t} p_{gen}(s)\,ds$$
>
> - il **False Match Rate (FMR)** / tasso di falso match è la frazione di confronti **impostori** con score sopra soglia, cioè accettati per errore;
> - il **False Non-Match Rate (FNMR)** / tasso di falso non-match è la frazione di confronti **genuini** con score sotto soglia, cioè respinti per errore.
>
> Entrambi valgono tra 0 e 1. **Alzando $t$**, $FMR$ diminuisce e $FNMR$ aumenta; abbassando $t$ accade il contrario. Il punto in cui i due tassi coincidono è l'**Equal Error Rate (EER)**. Dettagli e curve ROC/DET in [L06A — Misura dei parametri di un sistema biometrico](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>).

> ⚠️ **Uno score dell'87% non è la probabilità che l'utente sia genuino.** È un grado di somiglianza sulla scala dell'algoritmo. La probabilità di errore associata a una soglia si ricava solo dalle **distribuzioni** degli score genuini e impostori misurate su un database di test.

> 💡 **Nota didattica (non presente nelle slide) — FMR/FNMR contro FAR/FRR.** FMR e FNMR descrivono il **singolo confronto** dell'algoritmo. False Acceptance Rate (FAR) e False Rejection Rate (FRR) descrivono invece il **sistema**, e includono i casi in cui il campione non viene nemmeno acquisito (_Failure To Acquire_, FTA). Per un singolo tentativo, secondo le definizioni ISO/IEC 19795-1:
>
> $$FAR = FMR \cdot (1-FTA) \qquad\qquad FRR = FTA + FNMR \cdot (1-FTA)$$
>
> Un'acquisizione fallita non può produrre un falso accesso, ma respinge sempre un utente genuino. Questa differenza è ripresa nei metodi statistici delle Lezioni 9–10.

---

### **13. I principali tratti biometrici**

_(slide pp. 57–80)_

#### **13.1 Panoramica**

_(slide pp. 57–59)_

I tratti più impiegati nei sistemi biometrici sono: **impronte**, **volto**, **iride**, **geometria della mano / vene**, **voce**, **firma**, oltre ai **sistemi multimodali**.

**Impronta e iride** sono solo citate: verranno analizzate in dettaglio nelle Lezioni 3 e 4 secondo lo schema **sensori → estrattori → matching → attacco → difesa**.

<!-- SOURCE_VISUAL id="TAB01-V20" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="59" type="full-slide image" description="Image-only slide following the fingerprint/iris introduction (no text besides the footer)." reason="Illustrative image between trait sections; content only visible in the picture." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.2 Volto**

_(slide pp. 60–63)_

**Caratteristiche.**

- È tra i tratti **meno intrusivi**.
- È il tratto che **le persone usano normalmente** per riconoscersi a vicenda, il che ne spiega l'alta accettabilità.
- È usato in una **grandissima varietà di applicazioni**, dalla _mug shot_ (fotosegnalazione) all'acquisizione **3D**.

<!-- SOURCE_VISUAL id="TAB01-V21" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="60" type="photos" description="Face as a biometric trait: «mug shot» example and 3D face acquisition." reason="Shows the difference between 2D mug shots and 3D face data." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Sensori** (slide p. 61): telecamere, macchine fotografiche digitali, webcam, smartphone e cellulari, scanner 3D.

**Difficoltà.** È molto difficile realizzare sistemi robusti rispetto a:

| Fattore | Perché è un problema |
|---|---|
| **invecchiamento del volto** | riduce la **permanenza**: il template di enrollment si allontana dal volto attuale |
| **variazioni di sfondo e illuminazione** | cambiano i valori dei pixel senza che cambi l'identità |
| **variazioni della posa** | una rotazione della testa altera la geometria visibile |
| **espressioni del volto** | deformano la forma del volto |

> 💡 Tutti questi fattori aumentano la **variabilità intraclasse** (lo stesso individuo appare diverso), mentre la **similitudine interclasse** resta non trascurabile (sosia, gemelli). È questa combinazione a rendere il volto meno accurato dell'iride o dell'impronta, nonostante la grande diffusione. **Richiamo:** [L14 — Sistemi biometrici basati sul volto](<../../../anno3/6_Sistemi_Biometrici/L14 - Sistemi biometrici basati sul volto - introduzione, sensori 2D e algoritmi di prefiltraggio.md>).

**Matching classico** (slide p. 62). Il riconoscimento classico segue due approcci.

**(a) Approccio per trasformazione — Eigenfaces.** Si crea una **"base di immagini"** che permette di ricostruire un nuovo volto come **combinazione delle immagini della base**.

> 💡 **Nota didattica (non presente nelle slide) — formalizzazione.** Le Eigenfaces (Turk e Pentland, 1991) si ottengono con la _Principal Component Analysis_ applicata a un insieme di volti allineati. Detto $\bar{\mathbf{x}}$ il volto medio e $\mathbf{u}_1, \dots, \mathbf{u}_k$ le prime $k$ componenti principali (le _eigenfaces_), un volto $\mathbf{x}$ si approssima come
>
> $$\mathbf{x} \approx \bar{\mathbf{x}} + \sum_{i=1}^{k} w_i\,\mathbf{u}_i, \qquad w_i = \mathbf{u}_i^{\top}(\mathbf{x}-\bar{\mathbf{x}})$$
>
> Il **template** è il vettore dei pesi $\mathbf{w} = (w_1, \dots, w_k)$, molto più piccolo dell'immagine, e il matching confronta i vettori dei pesi con una distanza.

**(b) Approccio per attributi (geometrico).** Si **localizza il volto** nell'immagine e si misurano caratteristiche come la **distanza tra gli occhi**, la **lunghezza del naso**, quella della **bocca** e così via. Il template è l'insieme di queste misure.

**Approccio deep.** L'approccio basato su **deep learning** (esempio: **DeepFace**, Meta) ha portato **miglioramenti assolutamente rilevanti** nell'accuratezza. La rete apprende direttamente dai dati la rappresentazione del volto, senza che le caratteristiche vengano scelte a mano. Il tema è approfondito nelle Lezioni 7–9.

<!-- SOURCE_VISUAL id="TAB01-V22" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="62" type="diagram" description="Classic face matching approaches: (a) Eigenfaces image basis, (b) geometric attributes (eye distance, nose and mouth length); deep approach (DeepFace, Meta)." reason="Visual comparison of transformation-based, attribute-based and deep approaches." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB01-V23" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="63" type="full-slide image" description="Image-only slide closing the face section (no text besides the footer)." reason="Illustrative image; content only visible in the picture." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.3 Mano**

_(slide pp. 64–66)_

**Caratteristiche.**

- **Molto ben accettata** dagli utenti perché poco invasiva.
- Offre un **discreto livello di accuratezza**.
- Permette di funzionare in modo **multimodale**, perché si controllano più aspetti della stessa mano.
- Di solito si lavora su **tre viste**: **palmare**, **laterale**, **dorsale**.

**Sensori.**

- **scanner**, anche a bassa risoluzione (meno di 180 dpi);
- **telecamere CCD** a media risoluzione, nel **visibile** o nell'**infrarosso (IR)**.

**Algoritmi** presenti nel sistema:

| Algoritmo | Scopo |
|---|---|
| **allineamento** | riportare le mani in una posizione di riferimento per poterle confrontare |
| **rilevamento dei contorni** | estrarre la sagoma della mano, da cui misurare lunghezze e larghezze delle dita |
| **analisi dell'immagine termica** | verificare la **vitalità** (_liveness_) e misurare le **vene** |

> 💡 L'immagine **infrarossa** svolge due funzioni: rivela il pattern delle vene sottocutanee, che è un tratto aggiuntivo, e dimostra che la mano è viva, perché una mano finta non ha lo stesso profilo termico. Il riconoscimento delle vene è quindi anche una contromisura anti-spoofing.

<!-- SOURCE_VISUAL id="TAB01-V24" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="65" type="diagram" description="Hand geometry pipeline: alignment for comparison, contour detection, thermal image analysis for liveness and vein measurement." reason="Shows the three processing steps on real hand images." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB01-V25" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="66" type="full-slide image" description="Image-only slide closing the hand section (no text besides the footer)." reason="Illustrative image; content only visible in the picture." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.4 Voce**

_(slide pp. 67–69)_

| Aspetto | Valutazione (slide) |
|---|---|
| Accettabilità | tratto **accettato** dagli utenti |
| Accuratezza | **bassa** |
| Costo | **moderato** |
| Template | di **grandi dimensioni** |
| Sicurezza | **piuttosto facile da frodare** (per esempio con registrazioni) |

> ⚠️ **Speech recognition e speaker recognition non sono la stessa cosa.**
>
> | | **Speech Recognition** | **Speaker Recognition** |
> |---|---|---|
> | Riconosce | **che cosa** viene detto (testi, frasi, numeri) | **chi** sta parlando |
> | È biometria? | no | **sì** |
> | Esempio | dettatura vocale, assistenti vocali | verifica dell'identità al telefono |

<!-- SOURCE_VISUAL id="TAB01-V26" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="68" type="comparison diagram" description="Speech recognition (what is said) versus speaker recognition (who is speaking)." reason="Visual anchor for a frequent exam confusion." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB01-V27" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="69" type="full-slide image" description="Image-only slide closing the voice section (no text besides the footer)." reason="Illustrative image; content only visible in the picture." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **13.5 Firma**

_(slide pp. 70–78)_

##### **13.5.1 Caratteristiche generali**

La firma è un metodo **molto diffuso e semplice**, con **bassa accuratezza** e **costo moderato del sensore**. Si distingue in **firma statica** (_offline_) e **firma dinamica** (_online_).

È ragionevolmente unica non solo per la forma, ma anche per il **modo in cui viene tracciata**:

- **velocità** di scrittura;
- **punti** in cui si esercita **più pressione**;
- **angolo d'inclinazione** della penna;
- **accelerazione** del movimento;
- **numero di sollevamenti** della penna dalla carta.

Trova la sua maggiore applicazione negli ambienti **bancari e finanziari**.

##### **13.5.2 Firma online (dinamica)**

_(slide pp. 72–74)_

La firma viene acquisita come insieme di **segnali nel tempo**:

- coordinate $x(t)$ e $y(t)$;
- **pressione** $p(t)$;
- **azimut** della penna;
- **inclinazione** della penna.

In fase di matching si confrontano:

- i **tracciati dei parametri nel tempo**;
- le **distanze relative spazio-temporali** dei **punti singolari**;
- i **punti singolari** dei tracciati: **inversioni di moto**, **cuspidi** delle curve, **intersezioni** delle linee.

Le **tecniche di confronto** citate sono: **distanze euclidee**, **Hidden Markov Model**, **correlazione di segnale**, **classificatori bayesiani**, **reti neurali**.

> 📌 **È necessario applicare il Dynamic Time Warping (DTW).** Una persona non firma mai due volte alla stessa velocità: due tracciati della stessa firma hanno durate e ritmi diversi. Un confronto punto a punto sullo stesso istante $t$ fallirebbe. Il DTW **allinea non linearmente** i due segnali nel tempo prima di misurarne la distanza.

> 💡 **Nota didattica (non presente nelle slide) — DTW.** Date due sequenze $a_1, \dots, a_n$ e $b_1, \dots, b_m$ e una distanza locale $d(a_i, b_j)$, il DTW calcola la distanza cumulata minima con la ricorrenza
>
> $$D(i,j) = d(a_i, b_j) + \min\{D(i-1,j),\; D(i,j-1),\; D(i-1,j-1)\}$$
>
> con $D(0,0) = 0$ e $D(i,0) = D(0,j) = +\infty$ per $i, j \ge 1$. La distanza tra le due firme è $D(n,m)$. I tre termini del minimo corrispondono a far avanzare solo la prima sequenza, solo la seconda o entrambe: così un tratto eseguito più lentamente viene "stirato" sul corrispondente tratto più veloce.

**Acquisizione** (slide p. 74): l'utente firma con una **penna speciale** o su una **tavoletta elettronica** in grado di rilevare i parametri descritti. Il template risultante è di circa **1500 byte**.

<!-- SOURCE_VISUAL id="TAB01-V28" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="73" type="signal plots" description="Online (dynamic) signature: time traces of the acquired parameters and singular points compared during matching." reason="Shows why time alignment (DTW) is needed." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

##### **13.5.3 Firma offline (statica)**

_(slide pp. 75–76)_

È disponibile **solo l'immagine** della firma come campione iniziale: tutte le informazioni temporali sono perse. Le tecniche di confronto citate sono:

1. **istogrammi delle proiezioni orizzontali e verticali** dei toni di grigio: si somma l'intensità dell'inchiostro riga per riga e colonna per colonna, ottenendo due profili che descrivono la distribuzione del tratto;
2. **Extended Shadow Code**: si **sovrappone alla firma una serie di matrici di segmenti** (una maschera) e si verificano le **intersezioni** tra la firma e i segmenti; l'insieme delle intersezioni costituisce un codice che descrive la dislocazione dei tratti nell'area della firma.

<!-- SOURCE_VISUAL id="TAB01-V29" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="75" type="method illustration" description="Offline signature with horizontal and vertical projection histograms and the segment mask of the Extended Shadow Code." reason="The Extended Shadow Code is much clearer with the mask overlaid on the signature." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La slide p. 76 mostra un esempio di template ottenuto con **Adaptive SIFT–SURF matching**, basato sull'estrazione di **punti salienti** dall'immagine.

> 💡 **Nota didattica (non presente nelle slide) — SIFT e SURF.** _Scale-Invariant Feature Transform_ (SIFT) e _Speeded-Up Robust Features_ (SURF) individuano nell'immagine **punti salienti** (angoli, incroci, estremità dei tratti) e descrivono l'intorno di ciascuno con un vettore **invariante a scala e rotazione**. Per la firma offline questa invarianza è preziosa: la stessa firma può essere più grande, più piccola o inclinata rispetto a quella di enrollment. Il matching valuta poi quante coppie di punti corrispondenti sono coerenti tra le due immagini.

<!-- SOURCE_VISUAL id="TAB01-V30" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="76" type="image with keypoints" description="Offline (static) signature template: salient points matched with Adaptive SIFT-SURF." reason="Shows keypoint-based matching on a static signature image." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

##### **13.5.4 Punti di forza e di debolezza**

_(slide p. 77)_

| Punti di forza | Punti di debolezza |
|---|---|
| hardware **poco costoso** | **instabilità temporale** del campione: **elevata variabilità intraclasse** |
| **buona accettabilità** da parte degli utenti | **dimensioni del template** da pochi kB fino a 1,5 MB |
| **difficilmente falsificabile** nel caso della firma **online** | **numero limitato** di applicazioni adatte |
| | problemi con firme **molto brevi o troppo semplici**: **elevata similitudine interclasse** |

La slide mostra lo **stesso individuo che ripete 10 volte la firma**, per rendere visibile la variabilità intraclasse.

> 📌 **Variabilità intraclasse e similitudine interclasse.** La **variabilità intraclasse** misura quanto differiscono tra loro i campioni della **stessa persona** (la firma cambia a ogni ripetizione); la **similitudine interclasse** misura quanto si somigliano i campioni di **persone diverse** (firme brevi e semplici si assomigliano). Un buon tratto ha **bassa variabilità intraclasse** e **bassa similitudine interclasse**: la firma soffre di entrambi i problemi, e per questo ha bassa accuratezza.

> **Richiamo — Sistemi Biometrici, L04 §8.** I due concetti sono trattati come aspetti analitici del tratto in [L04 — Struttura dei sistemi biometrici](<../../../anno3/6_Sistemi_Biometrici/L04 - Struttura dei sistemi biometrici e aspetti analitici del tratto.md>).

<!-- SOURCE_VISUAL id="TAB01-V31" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="77" type="image" description="The same person signing 10 times." reason="Direct visual evidence of intra-class variability of signatures." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ Le due indicazioni sulle dimensioni del template (circa **1500 byte** a p. 74, **da pochi kB a 1,5 MB** a p. 77) non si contraddicono: la prima riguarda il template dei parametri di una firma online, la seconda l'intera gamma delle rappresentazioni possibili, incluse quelle basate su immagini.

> 💡 Solo la firma **online** è "difficilmente falsificabile": un falsario può imitare la **forma** di una firma osservandola, ma difficilmente ne riproduce **velocità, pressione e accelerazione**, che non sono visibili sul foglio.

##### **13.5.5 Applicazioni e requisiti del settore bancario**

_(slide p. 78)_

Nel mondo bancario e delle transazioni finanziarie, i requisiti dell'**APACS** (_Association for Payment Clearing Services_, associazione britannica dei sistemi di pagamento) richiedono:

$$FRR = 0{,}001\% = 10^{-5} \qquad FAR = 5\%$$

**Nessun sistema commerciale** garantisce ad oggi questi requisiti. Alcune banche usano comunque questi sistemi **per aiutare l'operatore**, come **avvertimento** o **"second reading"**: un secondo parere che si affianca a quello dell'operatore umano.

> 💡 **Come leggere i requisiti.** Sono fortemente **asimmetrici**. Si tollera un FAR alto (5%) perché la firma è solo uno dei controlli della transazione, mentre si pretende un FRR bassissimo perché respingere un cliente legittimo ha un costo commerciale elevato. È un esempio del principio generale: **il punto di lavoro (la soglia) si sceglie in base all'applicazione**, non "al valore migliore in assoluto".

> 💡 **Nota didattica (non presente nelle slide) — dimensionamento con la regola del 3.** Per dimostrare con confidenza al 95% che $FRR \le 10^{-5}$ osservando **zero** errori, la regola del 3 richiede almeno
>
> $$N \approx \frac{3}{FRR} = \frac{3}{10^{-5}} = 300\,000$$
>
> tentativi genuini **indipendenti** senza nemmeno un falso rifiuto. Il requisito APACS è quindi difficile anche solo da **verificare sperimentalmente**. La regola è ricavata in [L06A §14](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>).

#### **13.6 Sistemi multimodali**

_(slide p. 79)_

> 📌 Un **sistema multimodale** unisce **più tecnologie biometriche** in un unico sistema. Non si parla solo di più **tratti** biometrici: si possono combinare anche più **sensori**, più **algoritmi** o più **istanze** dello stesso tratto.

**Obiettivo**: aumentare l'**accuratezza** oppure la **robustezza alle frodi**. Un attaccante deve ingannare più modalità contemporaneamente, e un tratto degradato (per esempio un'impronta usurata) può essere compensato dagli altri. Progettazione e fusione dell'informazione sono sviluppate nella Lezione 15.

#### **13.7 Soft biometrics**

_(slide p. 80)_

> 📌 Le **soft biometrics** sono tratti che **non possiedono le 7 caratteristiche necessarie** (§10) ma possono essere **usati in aggiunta** ad altri tratti.

Esempi: **genere**, **colore della pelle**, **colore dei capelli**, **colore degli occhi**, **peso**, **altezza**.

Da sole non bastano a riconoscere un individuo, perché non hanno sufficiente **unicità** (milioni di persone sono alte 1,78 m) né, in alcuni casi, sufficiente **permanenza** (peso, colore dei capelli). Sono però utili per **restringere la ricerca** in identificazione o per **pesare** il risultato di un sistema multimodale. Sono esattamente il tipo di informazione annotata nella slide p. 8 (§3.1).

> ⚠️ **Multimodale non significa soft biometrics.** Un sistema multimodale combina tratti che possono essere ciascuno riconoscitivo (volto + impronta); le soft biometrics sono tratti **non** riconoscitivi da soli, usati solo come supporto.

---

### **14. Strutture dei sistemi biometrici**

_(slide pp. 81–93)_

#### **14.1 Strutture possibili**

_(slide p. 82)_

| Struttura | Varianti |
|---|---|
| **Sistema classico** | autenticazione, identificazione |
| **Documento biometrico** | template memorizzato nel documento |
| **Multimodale** | più tecnologie combinate |
| **Distribuito** | moduli separati e collegati in rete |
| **Rappresentazione funzionale** | modello di Mansfield & Wayman |
| **On card** | template su smart card |
| **On sensor** | elaborazione e matching nel sensore |

#### **14.2 Enrollment**

_(slide pp. 83–84)_

La catena di enrollment è:

$$\text{Trait} \rightarrow \text{Acquisition Module} \rightarrow \text{Quality Checker} \rightarrow \text{Feature Extraction Module} \rightarrow \text{Template} \rightarrow \text{DataBase}$$

| Modulo | Funzione |
|---|---|
| **Acquisition Module** | acquisisce il tratto e produce il **campione** |
| **Quality Checker** | verifica che il campione sia di qualità sufficiente; se non lo è, l'acquisizione va ripetuta |
| **Feature Extraction Module** | estrae le **feature** e le codifica nel **template** |
| **DataBase** | memorizza il template |

Le due slide differiscono per **che cosa viene memorizzato**:

- p. 83: _Enrollment: (template) → DB_;
- p. 84: _Enrollment: (**template + identity**) → DB_, con l'identità fornita come **nome o PIN**.

L'associazione tra template e identità è necessaria sia per l'**autenticazione**, che deve recuperare il template a partire dall'identità dichiarata, sia per l'**identificazione**, che deve restituire un'identità.

> ⚠️ Nelle slide il modulo di **Feature Extraction** comprende anche gli algoritmi di **codifica** delle feature nel template (nota (\*) a p. 83), e il modulo di **Matching** comprende anche l'algoritmo di **decisione**, di solito un confronto con **soglia fissa** (nota (\*) a p. 85). Negli schemi non compaiono quindi blocchi separati per codifica e decisione.

> 💡 Il **Quality Checker** è il modulo più sottovalutato: un template di enrollment scadente degrada **tutti** i riconoscimenti futuri di quell'utente. È più economico ripetere un'acquisizione in enrollment che accettare per anni un template di bassa qualità.

<!-- SOURCE_VISUAL id="TAB01-V32" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="83" type="block diagram" description="Enrollment: trait, acquisition module, quality checker, feature extraction module, template stored in the database." reason="Reference block diagram of the enrollment chain." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB01-V33" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="84" type="block diagram" description="Enrollment storing template + identity (name, PIN) in the database." reason="Shows where the identity is bound to the template." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.3 Verifica con database**

_(slide p. 85)_

$$\text{Trait} \rightarrow \text{Acquisition} \rightarrow \text{Feature Extraction} \rightarrow \text{Matching Module} \rightarrow \text{Yes/No}$$

1. L'utente **dichiara la propria identità** (nome, PIN).
2. Il sistema recupera dal database **un solo template**, quello dell'identità dichiarata.
3. Il campione acquisito viene elaborato ed estratto.
4. Il **Matching Module** confronta i due template e applica la soglia.
5. L'esito è **Yes / No**.

<!-- SOURCE_VISUAL id="TAB01-V34" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="85" type="block diagram" description="Verification using a database: claimed identity (name, PIN) retrieves 1 template; matching module outputs yes/no." reason="Reference block diagram of 1:1 verification." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.4 Identificazione**

_(slide p. 86)_

$$\text{Trait} \rightarrow \text{Acquisition} \rightarrow \text{Feature Extraction} \rightarrow \text{Matching Module} \;(N \text{ template}) \rightarrow \text{User Identity} \;/\; \text{User not identified}$$

L'utente **non dichiara nulla**. Il Matching Module confronta il template della sonda con **N template** del database e restituisce l'**identità** trovata oppure **"User not identified"**.

<!-- SOURCE_VISUAL id="TAB01-V35" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="86" type="block diagram" description="Identification: probe compared with N templates; output user identity or «user not identified»." reason="Reference block diagram of 1:N identification." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| | Verifica (p. 85) | Identificazione (p. 86) |
|---|---|---|
| Input dell'utente | tratto + **identità dichiarata** | solo il tratto |
| Template letti dal DB | **1** | **N** |
| Output | **Yes / No** | **identità** oppure **non identificato** |
| Errore al crescere del DB | invariato | cresce con $N$ |

#### **14.5 Sistemi per documenti biometrici**

_(slide pp. 87–88)_

Nei sistemi per **documenti biometrici** il template **non viene memorizzato in un database**, ma nel **documento** stesso.

- **Enrollment** (p. 87): _Trait → Acquisition → Quality Checker → Feature Extraction → Template → **Document**_.
- **Verifica** (p. 88): il template viene **letto dal documento** (_1 Template_) e confrontato con quello estratto dal tratto acquisito sul momento; l'esito è **Yes / No**.

> 📌 Un documento biometrico supporta per costruzione **solo la verifica**: il documento contiene un solo template, quello del titolare. Il vantaggio è che **non serve un database centralizzato**, e questo va nella direzione del _Personal Storage_ della slide p. 20 (§3.2).

<!-- SOURCE_VISUAL id="TAB01-V36" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="87" type="block diagram" description="Enrollment for biometric documents: template written to the document instead of a database." reason="Contrast with database enrollment." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB01-V37" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="88" type="block diagram" description="Verification with a biometric document: 1 template read from the document, matching, yes/no." reason="Contrast with database verification." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.6 Sistemi biometrici distribuiti**

_(slide pp. 89–90)_

> 📌 Un sistema biometrico si dice **distribuito** quando i **moduli componenti sono separati e collegati in rete**.

- È **piuttosto raro** nei sistemi di **autenticazione**.
- È **comune** nei sistemi di **identificazione di grandi dimensioni**: identificazione in aeroporto, controllo delle frontiere, sistema nazionale di identificazione investigativa (IAFIS), videosorveglianza.
- Di solito è il **database dei template** a essere dislocato rispetto ai terminali:
  - per **esigenze di sicurezza**;
  - per **collegare database distribuiti** su una LAN o una WAN.

> 💡 Distribuire i moduli ha un costo di sicurezza: i **canali di rete** tra acquisizione, estrazione, database e matching diventano nuovi **punti di attacco**, dove campioni e template possono essere intercettati o reimmessi (_replay attack_). Per questo i canali vanno cifrati e autenticati. **Richiamo:** [L06A §6 — Distanza tra template e replay attack](<../../../anno3/6_Sistemi_Biometrici/L06A - Misura dei parametri di un sistema biometrico.md>).

> ⚠️ **Distribuito non significa multimodale.** «Distribuito» riguarda la **collocazione in rete** dei moduli; «multimodale» riguarda il **numero di tecnologie biometriche** combinate. Un sistema può essere l'uno, l'altro, entrambi o nessuno dei due.

<!-- SOURCE_VISUAL id="TAB01-V38" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="90" type="architecture diagram" description="Structure of distributed biometric systems (modules connected over a network)." reason="The slide has only a title; the architecture is in the diagram." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.7 Rappresentazione funzionale di Mansfield & Wayman**

_(slide p. 91)_

La slide p. 91 riporta soltanto il titolo e lo schema della **rappresentazione funzionale** di Mansfield & Wayman.

<!-- SOURCE_VISUAL id="TAB01-V39" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="91" type="functional diagram" description="Mansfield & Wayman functional model of a biometric system." reason="The slide text has only the title; the whole content is in the diagram." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> **Richiamo — Sistemi Biometrici, L04 §4.3.** Lo schema è commentato in [L04 — Struttura dei sistemi biometrici](<../../../anno3/6_Sistemi_Biometrici/L04 - Struttura dei sistemi biometrici e aspetti analitici del tratto.md>), che lo descrive in quattro macro-fasi:
>
> 1. **Data Collection**: presentazione del tratto, sensore, generazione dei campioni;
> 2. **Data Storage**: memorizzazione di template e immagini;
> 3. **Signal Processing**: segmentazione, estrazione delle feature, controllo qualità, pattern matching;
> 4. **Decision**: decisione finale di accettazione o rifiuto in base ai criteri di accuratezza.

#### **14.8 Sistemi biometrici on card**

_(slide p. 92)_

> 📌 **On card**: il **template biometrico risiede su una smart card**.

Di solito la smart card:

- **non effettua il matching**, a causa della bassa potenza computazionale disponibile;
- **non possiede un sensore** di acquisizione: si usa un sistema esterno.

La slide cita quattro prodotti, con la collocazione del matching indicata nella slide:

| Prodotto | Dove avviene il matching (slide) |
|---|---|
| **Siemens Matcher on Card** | sul **lettore di carte** (_card reader_) |
| **Precise Biometrics** | sul **PC** |
| **Veridicom 5th Sense** | sul **PC** |
| **Zwipe ID** | presentata come _"the world's first fingerprint-activated contactless card"_: **sensore integrato nella carta** (_Fingerprint On-card Authentication_) |

> ⚠️ Nella soluzione on card "classica" il template **esce dalla carta** per essere confrontato sul lettore o sul PC, dove potrebbe essere intercettato. Zwipe è diversa, perché acquisizione e autenticazione avvengono sulla carta. **Richiamo:** la distinzione tra _on card_ e _Biometric on Card_ (sensore, template e matching tutti sulla carta) è in [L04 §5](<../../../anno3/6_Sistemi_Biometrici/L04 - Struttura dei sistemi biometrici e aspetti analitici del tratto.md>).

<!-- SOURCE_VISUAL id="TAB01-V40" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="92" type="product photos" description="On-card systems: Siemens Matcher on Card, Precise Biometrics, Veridicom 5th Sense, Zwipe ID fingerprint-activated contactless card with on-card sensor." reason="Shows the physical difference between cards with external and integrated sensors." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.9 Match on sensor**

_(slide p. 93)_

Arrivano sul mercato **sensori con molte funzionalità** utili allo sviluppo del sistema. L'esempio è **Synaptics SentryPoint**, che integra:

- **cifratura** (_encryption_);
- architettura **Match-in-Sensor**: il confronto avviene dentro il sensore;
- tecnologia **anti-spoof**.

Le slide precisano che il _match on sensor_ riguarda sensori **rigidi**, quindi **non adatti alle carte di credito**. La tecnologia **"Under the glass"** colloca il sensore sotto il vetro, rendendolo ancora **meno invasivo** e "immerso" nel dispositivo.

> 💡 **Perché il match on sensor aumenta la sicurezza.** Se il matching avviene nel sensore, campione e template **non transitano mai** verso il processore principale del dispositivo, che potrebbe essere compromesso da malware. All'esterno esce solo l'esito, cifrato. È l'estremo opposto del sistema distribuito (§14.6): la superficie d'attacco sul dato biometrico si riduce al minimo.

<!-- SOURCE_VISUAL id="TAB01-V41" source="Lezione_01_Descrizione_del_Corso_Esame_Introduzione_alla_biometria.pdf" page="93" type="product photos" description="Synaptics SentryPoint (encryption, match-in-sensor, anti-spoof) and «under the glass» fingerprint sensor." reason="Illustrates match-on-sensor hardware." -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.10 Dove stanno il template e il matching**

> 💡 **Sintesi didattica (confronto costruito sulle §14.2–14.9).**

| Architettura | Dove sta il template | Dove avviene il matching | Operazioni possibili | Esposizione del dato biometrico |
|---|---|---|---|---|
| Classica con DB | database | server o terminale | verifica e identificazione | alta: archivio centralizzato |
| Distribuita | database remoto | nodo di rete | soprattutto identificazione su larga scala | alta: archivio e canali di rete |
| Documento biometrico | documento | terminale di lettura | solo verifica | media: il template esce dal documento |
| On card | smart card | lettore o PC | solo verifica | media: il template esce dalla carta |
| Match on sensor | sensore / dispositivo | sensore | verifica locale | bassa: esce solo l'esito cifrato |

---

### **15. Sintesi della lezione**

_(slide p. 94)_

> ✅ **In sintesi**
>
> - **Struttura dell'insegnamento e dell'esame**: crash course iniziale, teoria, applicazioni, sistemi HW/SW, laboratori; esame a risposta multipla con esercizi di progettazione, codice non richiesto.
> - **Definizioni principali**: biometria come riconoscimento **automatico** da tratti fisici e comportamentali; **autenticazione (1:1)** e **identificazione (1:N)**, chiusa e aperta; riconoscimento **positivo** e **negativo**; **probe** e **gallery**.
> - **Funzionamento**: acquisizione → estrazione → matching → decisione con **soglia** sul **matching score**.
> - **Esempi di template** citati nella sintesi: **IrisCode**, **lunghezza delle dita** di una mano, **coordinate nel tempo** di una firma, **posizione delle minuzie** di un'impronta (IrisCode e minuzie sono sviluppati nelle Lezioni 3–4).
> - **Principali tratti**: volto, mano, voce, firma online e offline; impronta e iride rimandate alle lezioni dedicate.
> - **Sistemi multimodali** e **soft biometrics**.
> - **Moduli del sistema**: acquisizione, controllo qualità, estrazione, database, matching.
> - **Sistemi distribuiti**, **documenti biometrici**, **on card**, **match on sensor**.

#### **15.1 Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Autenticazione** / **Identificazione** | 1:1 con identità dichiarata / 1:N senza identità dichiarata |
| **Riconoscimento** / **Identificazione** | termine generale che comprende verifica e identificazione / solo la ricerca 1:N |
| **Identificazione chiusa** / **aperta** | il soggetto è sicuramente nel DB / può non esserci, serve l'esito "non identificato" |
| **Positivo** / **Negativo** | impedisce che più persone usino un'identità / impedisce che una persona usi più identità |
| **Probe** / **Gallery** | campione interrogato ora / campioni memorizzati con cui confrontarlo |
| **Enrollment** / **Riconoscimento** | prima acquisizione e memorizzazione / nuova acquisizione e confronto |
| **Campione** / **Feature** / **Template** | dato grezzo / informazione discriminante estratta / rappresentazione codificata memorizzata |
| **Matching score** / **Decisione** | grado di somiglianza / Yes/No ottenuto confrontando lo score con una soglia |
| **FMR / FNMR** / **FAR / FRR** | errori del singolo confronto / errori del sistema, inclusa la mancata acquisizione |
| **Speech** / **Speaker recognition** | che cosa si dice / chi parla (solo il secondo è biometria) |
| **Firma online** / **offline** | segnali nel tempo (con DTW) / solo immagine statica |
| **Sistema multimodale** / **Soft biometrics** | più tecnologie riconoscitive combinate / tratti non riconoscitivi da soli, usati come supporto |
| **On card** / **Match on sensor** | template sulla carta, matching esterno / matching dentro il sensore |
| **Circonvenzione** (slide) / **Circumvention** (Jain) | difficoltà di ingannare il sistema (alto = pregio) / facilità di ingannarlo (alto = difetto) |
| **Variabilità intraclasse** / **Similitudine interclasse** | differenze tra campioni della stessa persona / somiglianze tra campioni di persone diverse |
| **Distribuito** / **Multimodale** | moduli collocati in rete / più tecnologie biometriche combinate |
