# **Lezione 13: Difesa della privacy, protezione del template e crittografia biometrica**

---

### **0. Panoramica della lezione**

_(slide pp. 1–5)_

Il PDF si apre con la **slide introduttiva della breve simulazione d'esame** su ottiche e sensori (p. 2), che conclude la Lezione 12. Come per le altre simulazioni parziali, gli esempi **non vengono rilasciati** in PDF; la simulazione integrale, con correzione e PDF pubblicati, si terrà nell'ultima lezione.

La lezione vera e propria (p. 3) tratta la **difesa della privacy** e la **protezione del template**. La privacy è **uno degli aspetti di progettazione** dei sistemi biometrici (p. 5), al pari di accuratezza, costo e usabilità.

**Outline** (p. 4):

1. difesa della privacy e linee guida;
2. **biometria cancellabile**: permutazione dell'IrisCode, morphing del volto;
3. **sample o template**: ricostruzione del sample;
4. **crittazione biometrica**;
5. prime soluzioni commerciali;
6. **de-identificazione** biometrica;
7. privacy biometrica nei **social media**;
8. ulteriori rischi della **heart biometrics**.

La lezione è accompagnata da una **dispensa** in inglese: R. Donida Labati, V. Piuri, F. Scotti, «Biometric Privacy Protection: Guidelines and Technologies», *Communications in Computer and Information Science*, Springer, pp. 3–19, 2012 (DOI 10.1007/978-3-642-35755-8_1). Nel testo i suoi contenuti sono integrati nelle sezioni corrispondenti e indicati come «dispensa».

<!-- SOURCE_VISUAL id="TAB13-V01" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="5" type="schema" description="La privacy come uno degli aspetti di progettazione dei sistemi biometrici" reason="Pagina di sezione con schema grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **1. Privacy e sicurezza nei sistemi biometrici**

_(dispensa pp. 1–3)_

_(slide pp. 26–27)_

#### **1.1 Perché i dati biometrici sono speciali**

Le tecniche tradizionali di identificazione (password, chiavi, token, documenti) si basano su **rappresentazioni surrogate** dell'identità: possono essere condivise, smarrite o rubate. I tratti biometrici sono invece **legati in modo univoco** al proprietario, non si condividono né si smarriscono e sono più difficili da rubare.

Proprio questo legame li rende delicati (slide p. 26; dispensa pp. 2–3):

- l'identità biometrica personale **va protetta**;
- i tratti biometrici **non possono essere sostituiti**: sono **irrevocabili** nel corso della vita;
- se vengono rubati, la persona può essere **impersonata a lungo** e **in molti sistemi diversi**, oppure inserita in sistemi biometrici **senza consenso esplicito**;
- il furto espone a **accesso a informazioni personali**, **impersonificazione** e **uso improprio**.

I dati circolano in molti punti: acquisizione e matching, **canale di trasmissione**, **database**, e anche **carte, documenti** (per esempio con **RFID**) e sistemi in tempo reale (slide p. 26).

La dispensa ricorda anche i principali **scenari applicativi**: controllo accessi fisico, sorveglianza, applicazioni governative (documenti, passaporti, immigrazione), applicazioni forensi, controllo accessi logico (home banking, ATM, e-commerce, telefoni). All'epoca il mercato valeva **5 miliardi di dollari (2011)**, con una previsione di **12 miliardi entro il 2015**.

<!-- SOURCE_VISUAL id="TAB13-V02" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="27" type="schema" description="Biometrics privacy (2): punti in cui i dati biometrici possono essere esposti" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2 Sicurezza e privacy non sono la stessa cosa**

> 📌 **Sicurezza contro privacy** (dispensa p. 2). La **privacy è più restrittiva** della sicurezza. La **sicurezza** garantisce **autenticazione, integrità dei dati, confidenzialità e non ripudio**; la **privacy** richiede **in più la protezione dei dati** personali, cioè il controllo su come vengono usati.

> 💡 **Esempio.** Un database di impronte perfettamente cifrato e accessibile solo agli operatori è **sicuro**; se però gli operatori lo usano per uno scopo diverso da quello dichiarato (per esempio incrociarlo con un altro archivio), la **privacy** è violata anche senza nessun attacco informatico.

#### **1.3 Rischi percepiti e rischi reali**

L'**accettazione** di un sistema dipende dai rischi **percepiti** dagli utenti, che di solito **differiscono da quelli reali** (dispensa pp. 2 e 4).

| Rischi percepiti | Rischi reali: da che cosa dipendono |
|---|---|
| **furto d'identità** (il più importante) | **modalità di memorizzazione** dei dati |
| uso dei dati per **tracciare tutte le attività** o per **liste di proscrizione** | **proprietario** del sistema |
| idea che il sistema sia un **archivio permanente ed esatto** dei propri comportamenti, con accuratezza del **100%** | modalità di riconoscimento (**verifica o identificazione**) |
| danni alla **salute**, per esempio dagli illuminatori infrarossi dell'iride (timore infondato) | **durabilità** del tratto e sua **classe** (fisiologico o comportamentale) |

Durante il deployment è quindi importante **informare** gli utenti sui rischi reali per salute e privacy e sulle procedure di protezione adottate.

Un sistema **privacy-protective** è un sistema che **riduce drasticamente i rischi reali**. Per progettarlo non bastano prestazioni, costi, accettabilità e condizioni applicative: servono **linee guida** specifiche (§4).

#### **1.4 Gli otto punti di attacco**

Un sistema biometrico ha quattro moduli (**sensore, estrattore, database, matcher**), ognuno attaccabile. La dispensa (Fig. 1, p. 3) distingue **otto classi di attacco**:

| # | Attacco |
|:---:|---|
| I | **tratto falso** presentato al sensore |
| II | **reinvio** di un vecchio segnale biometrico memorizzato (replay) |
| III | **scavalcamento dell'estrattore** di feature |
| IV | **manomissione** della rappresentazione delle feature |
| V | **scavalcamento del matcher** |
| VI | **manomissione dei template** memorizzati |
| VII | attacco al **canale** tra template memorizzati e matcher |
| VIII | **scavalcamento della decisione** |

**Classi di contromisure** (dispensa p. 3): metodi di **liveness detection**; protezione **fisica e crittografica del canale**; pratiche di **esecuzione sicura del codice**; metodi di **protezione del template**, il tema principale della lezione.

<!-- SOURCE_VISUAL id="TAB13-V03" source="Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf" page="3" type="schema" description="Fig. 1: gli otto punti di attacco in un sistema biometrico" reason="Schema dei moduli con i punti di attacco numerati" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. I quattro livelli di privacy biometrica**

_(slide p. 6)_

_(dispensa pp. 6–7)_

| Livello | Slide (p. 6) | Dispensa (pp. 6–7) |
|---|---|---|
| **Privacy-protective** | la biometria **protegge informazioni personali** che altrimenti potrebbero essere compromesse, per esempio in contesti di sicurezza aziendale o pubblici | la biometria permette di stabilire un'identità fidata e **limitare l'accesso a dati sensibili**; esempi: sicurezza aziendale, verifica del titolare di un conto |
| **Privacy-sympathetic** («consapevole») | contiene dati biometrici che potrebbero essere usati male, **ma si cerca di limitarlo**; è il caso della maggior parte delle applicazioni in cui l'utente è **obbligato di fatto** (confine, aeroporto, banca, lavoro, servizi essenziali): il consenso è formale, ma non del tutto libero | progettata per **proteggere i dati biometrici** da accessi e usi non autorizzati; tutti gli elementi usano tecniche di protezione |
| **Privacy-neutral** | autenticazione su **dispositivi elettronici** (locale, *user-centric*); **non protegge altri dati** personali, solo l'accesso; **uso volontario** | usa la biometria **senza considerare** la privacy, ma con impatto di solito **lieve**; esempi: controllo accessi, PC, telefoni |
| **Privacy-invasive** | applicazioni di **sorveglianza** e alcuni servizi di **identità nazionale** | usa i dati in modo **contrario ai principi** di privacy: senza conoscenza o consenso, per **scopi non dichiarati** o oltre lo scopo iniziale |

Le domande del progettista (slide p. 6): **«Che tipo di sistema sto progettando?»** e **«Il rischio per la privacy è davvero bilanciato rispetto ai benefici per gli utenti?»**

---

### **3. Valutare il rischio privacy di un'applicazione**

#### **3.1 Le domande sul contesto applicativo**

_(slide pp. 7–16)_

_(dispensa pp. 4–5)_

Le slide annunciano **«7 domande»** (p. 7) ma ne mostrano **otto** (pp. 8–15); la tabella della dispensa, basata sull'**International Biometric Group** (IBG), ne contiene **dieci**. Per ognuna, a sinistra la risposta a **rischio minore**, a destra quella a **rischio maggiore** («Privacy++» nelle slide).

| # | Domanda | Rischio minore | Rischio maggiore | Perché (dispensa) |
|:---:|---|---|---|---|
| 1 | Il sistema è palese o nascosto? | **overt** | **covert** | i sistemi nascosti (sorveglianza) possono usare i dati senza conoscenza né consenso |
| 2 | È facoltativo o obbligatorio? | **optional** | **mandatory** | se è facoltativo l'utente può scegliere un altro metodo |
| 3 | Serve per verifica o identificazione? | **verification** | **identification** | l'identificazione confronta con $N$ identità di un database, spesso lontano dal sensore |
| 4 | È usato per un periodo fisso? *(solo dispensa)* | **periodo fisso** | **indefinito** | le politiche sulla durata di conservazione riducono il rischio |
| 5 | Settore privato o pubblico? | **private sector** (cliente) | **public sector** (cittadino) | timore di abusi governativi |
| 6 | In che ruolo interagisce l'utente? | **individuo / cliente** | **dipendente / cittadino** | il rischio è minore quando l'utente conserva i diritti d'uso sui dati |
| 7 | Chi possiede i dati biometrici? *(solo dispensa)* | **l'utente** (enrollee) | **l'istituzione** | il controllo dell'utente riduce il rischio, ma non è sempre possibile |
| 8 | Dove sono memorizzati i dati? | **memoria personale** (smart card, dispositivo) | **database** | la memoria personale previene gli abusi |
| 9 | Che tipo di tecnologia? | **comportamentale** | **fisiologica** | i tratti fisiologici sono più accurati, più difficili da mascherare e acquisibili con meno collaborazione |
| 10 | Template, sample o entrambi? | **template** | **sample / immagini** | i template rivelano informazioni più limitate |

<!-- SOURCE_VISUAL id="TAB13-V04" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="16" type="tabella grafica" description="Lista completa delle domande con le risposte a rischio minore e maggiore (Privacy / Privacy++)" reason="Riepilogo grafico delle domande" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Trappola d'esame.** Il titolo della slide dice «7 domande», ma le slide ne elencano otto e la dispensa dieci. Conviene ricordare tutte e dieci: le due in più sono la **durata** dell'uso e la **proprietà** dei dati.

#### **3.2 Il rischio legato al tratto**

_(dispensa pp. 5–6)_

Anche il **tratto scelto** introduce rischi diversi. La dispensa, sempre da IBG, considera **quattro caratteristiche** della tecnologia:

1. **uso in identificazione**: servono accuratezza e velocità elevate (iride, impronta); i tratti adatti all'identificazione sono **più invasivi**;
2. **uso in sistemi nascosti**: il **volto** si presta molto più dell'impronta a un uso covert;
3. **fisiologico o comportamentale**: il confine non è netto (il volto è fisiologico ma cambia con espressioni e trucco); i tratti **comportamentali** sono più rispettosi perché **modificabili** e **meno permanenti**;
4. **compatibilità con i database**: interoperabilità tecnologica e presenza di **database grandi e numerosi**; l'**impronta**, con molti grandi archivi di template standard, ha interoperabilità elevata; minore interoperabilità significa più privacy.

Con una **media pesata** di queste caratteristiche si ottiene il livello di rischio:

| Rischio | Tratti |
|---|---|
| **alto** | volto, impronta |
| **medio** | iride, retina |
| **basso** | mano, voce, dinamica di digitazione (keystroke), firma |

#### **3.3 I nove aspetti di valutazione**

_(dispensa pp. 6–7)_

Lo sviluppo di un sistema biometrico richiede di analizzare almeno **nove aspetti**: **costo, usabilità, velocità, accettazione sociale, accuratezza, scalabilità, interoperabilità, sicurezza e privacy**. Si possono quantizzare e rappresentare in uno **spazio a nove dimensioni**, per esempio con un **diagramma a ragno** (*spider diagram*): ogni applicazione è un punto in questo spazio, e la tecnologia si sceglie in base agli aspetti più importanti per quell'applicazione.

<!-- SOURCE_VISUAL id="TAB13-V05" source="Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf" page="7" type="diagramma a ragno" description="Fig. 2: i nove aspetti di valutazione dei sistemi biometrici" reason="Diagramma radar degli aspetti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.4 Un caso reale: la Cina nel 2019**

_(slide p. 17)_

Dal **1° dicembre 2019** in Cina chi acquista un **cellulare e una scheda telefonica** deve sottoporsi alla **scansione del volto** (obbligo del Ministero dell'industria e della tecnologia dell'informazione). Applicando le domande:

- **settore privato → settore pubblico**;
- **cliente → cittadino**;
- quindi un **sicuro peggioramento** della privacy, a fronte di un **probabile** aumento della sicurezza dichiarato («salvaguardare i diritti legittimi e gli interessi dei cittadini nel cyberspazio»).

La scala è enorme: in Cina **oltre 800 milioni** di persone usano Internet (**57,7%** della popolazione), il **98%** da telefono mobile.

> 💡 **Leggerlo con la tabella.** Il caso tocca almeno quattro domande: l'uso diventa **obbligatorio**, il titolare è di fatto **pubblico**, l'utente è un **cittadino**, e i dati finiscono in un **database** centrale.

---

### **4. Linee guida per sistemi rispettosi della privacy**

_(dispensa pp. 7–8)_

Per progettare sistemi **privacy-sympathetic** e **privacy-protective**, la dispensa riporta quattro gruppi di linee guida (IBG, Bioprivacy Initiative):

1. **Scopo e capacità del sistema.**
   - Vanno **dichiarati** agli utenti e **non estesi** durante la vita del sistema.
   - I dati vanno **cancellati** dopo un periodo noto agli utenti.
   - Si memorizza **solo il minimo** necessario: solo i **template**, eliminando **il prima possibile** i dati grezzi (immagini, segnali, sequenze).
   - Nel template **non** vanno integrati altri dati personali, e i template **non** vanno usati come **identificativi univoci**.
2. **Controllo dell'utente sui propri dati.**
   - L'uso deve essere **volontario**.
   - L'utente deve potersi **cancellare** (*unenroll*) e **modificare** i propri dati.
   - Deve essere possibile un certo grado di **anonimato** nell'enrollment.
3. **Trasparenza, audit e responsabilità.**
   - Serve un documento di **disclosure** su scopo, modalità di enrollment e matching, uso facoltativo o obbligatorio, responsabili e sistema di protezione dei dati.
   - Gli utenti devono sapere **quando** il sistema è in uso.
   - Ogni **operatore** deve essere **responsabile** delle proprie azioni.
   - Serve un processo di **audit** chiaro per revisioni di terze parti.
4. **Protezione dei dati.**
   - Primitive di **cifratura**, **reti private**, algoritmi e infrastrutture secondo lo **stato dell'arte**, collocazione in **aree sicure e controllate**, per tutto il ciclo di vita.
   - Vanno protetti anche i **risultati** dei riconoscimenti.
   - L'accesso ai dati va **limitato** a un numero definito di operatori.

Le tecniche di **protezione del template** migliorano inoltre l'**accettazione** degli utenti e aiutano a rispettare le **leggi sulla privacy**.

---

### **5. Il quadro normativo europeo**

#### **5.1 GDPR**

_(slide p. 18)_

Le norme del **Regolamento generale sulla protezione dei dati** (GDPR) sono discusse nel corso di **Sistemi Biometrici**. Punti principali richiamati:

- i **dati biometrici** sono compresi nella legge;
- la privacy va considerata **fin dalla progettazione** (*privacy by design*);
- un **data breach** va comunicato entro **72 ore**, con sanzioni che la slide indica fino a **20 milioni di euro**;
- il **titolare del trattamento** e i custodi dei dati;
- **cifratura** e protezione dei dati;
- **pseudonimizzazione**;
- **resilienza** del sistema.

> 💡 **Richiamo di Biometria I.** Nel GDPR i dati biometrici trattati per identificare univocamente una persona sono una **categoria particolare di dati** (art. 9), il cui trattamento è vietato salvo eccezioni specifiche, come il consenso esplicito. Le sanzioni massime sono 20 milioni di euro **o** il 4% del fatturato mondiale annuo, se superiore.

#### **5.2 L'AI Act e la biometria**

_(slide pp. 19–23)_

**Sistemi proibiti e sistemi ad alto rischio** (p. 19):

| | **Proibiti** | **Ad alto rischio** |
|---|---|---|
| Norma citata | **art. 5(g)** | **art. 6(2)** e **allegato III**; obblighi dell'**art. 50(3)** |
| Che cosa | categorizzazione biometrica che **deduce o inferisce** attributi sensibili (razza, opinioni politiche, orientamento sessuale) | categorizzazione biometrica regolata, non vietata |
| Regime | divieto **specifico e assoluto** | regolamentazione rigorosa: **informare** le persone esposte, trattare i dati secondo il **GDPR** |

La differenza sembra risiedere in questo: la categorizzazione è **ad alto rischio** se gli attributi sensibili sono **immediatamente evidenti**, ma è **vietata** se tali attributi sono **dedotti** da altri dati.

La distinzione può generare **confusione** (p. 20):

- un sistema che categorizza le immagini per **colore di capelli o occhi** a fini di applicazione della legge potrebbe essere **ad alto rischio** e regolato;
- un sistema che **deduce le convinzioni politiche** dal riconoscimento facciale è **proibito**;
- la sovrapposizione tra **leggi nazionali** e AI Act aggiunge complessità.

Date riportate: **2 febbraio 2025** (divieto dei sistemi dell'art. 5) e **2 agosto 2026** (entrata in vigore delle norme sui sistemi di categorizzazione biometrica).

**Il divieto di categorizzazione biometrica** (p. 21). È **vietato** usare sistemi che inferiscono o assegnano:

- **origine razziale o etnica**;
- **convinzioni religiose o filosofiche**;
- **orientamento sessuale**;
- **opinioni politiche**;
- **appartenenza sindacale**.

Il divieto vale anche se l'accuratezza è alta, il dataset è «bilanciato» o l'utente ha firmato un consenso: è un **divieto strutturale, non mitigabile**.

**Applicazioni permesse ma regolate** (p. 22): stima dell'**età** per l'accesso a contenuti, categorizzazione per la **sicurezza fisica**, classificazioni **funzionali** (adulto o minore). Sono **high-risk AI systems** soggetti a **risk management, data governance, logging, supervisione umana e trasparenza**.

**Emotion recognition, un caso speciale** (p. 23). L'AI Act la tratta quasi come la categorizzazione biometrica:

- è **vietata** nei **luoghi di lavoro** e nelle **scuole**;
- è **fortemente limitata** altrove (trattata come alto rischio);
- il divieto vale **indipendentemente** dal consenso, dall'accuratezza e dal fine dichiarato.

> ⚠️ **Date da verificare sul testo del regolamento.** Le slide collegano sia il divieto di categorizzazione (p. 21) sia quello di emotion recognition (p. 23) al **2 agosto 2026**, mentre la p. 20 indica che i sistemi dell'art. 5 sono vietati dal **2 febbraio 2025**. Nel Regolamento (UE) 2024/1689 i divieti dell'art. 5, compresi la categorizzazione per attributi sensibili (art. 5, par. 1, lett. g) e il riconoscimento delle emozioni sul lavoro e a scuola (lett. f, salvo motivi medici o di sicurezza), si applicano **dal 2 febbraio 2025**. Il **2 agosto 2026** è la data di applicazione generale, che include gli obblighi per i sistemi ad alto rischio dell'allegato III. Eventuali rinvii successivi vanno controllati sulle fonti ufficiali.

#### **5.3 Le critiche all'AI Act**

_(slide pp. 24–25)_

- **EDRi** (p. 24): l'AI Act stabilisce un **divieto di principio** sul riconoscimento facciale di massa **in tempo reale** e su altre forme di sorveglianza biometrica pubblica da parte della polizia, ma le **ampie eccezioni** potrebbero **legittimare** l'uso di questi sistemi.
- **ARTICLE 19** (p. 25): apprezza i divieti espliciti di **polizia predittiva**, **punteggio sociale** e **influenza surrettizia** sul comportamento, ma teme che la legge **non vieti completamente** il **riconoscimento delle emozioni** e l'**identificazione biometrica remota in tempo reale** negli spazi pubblici.

---

### **6. Basta memorizzare i template?**

#### **6.1 Interoperabilità e mercato**

_(slide p. 28)_

Memorizzare **solo i template** aiuta, perché **riduce l'interoperabilità**. Tuttavia:

- esistono **template standard** conformi alle norme **ISO**, quindi scambiabili tra sistemi;
- le società del settore sono **internazionali**, lavorano su **larga scala** e servono sia il **settore privato** sia quello **pubblico e governativo** con le stesse tecnologie: è possibile una **commistione** e un passaggio di informazioni.

#### **6.2 Dal template al sample**

_(slide p. 29)_

C'è un terzo motivo per cui i soli template non bastano: esistono esempi di **ricostruzione** (parziale o limitata) **del sample a partire dal template**. Nell'esempio della slide, un'impronta sintetica viene ricostruita da una **lista di minuzie**, e il pattern ricostruito è **perfettamente compatibile** con la lista iniziale. La ricostruzione è utile anche per creare **attacchi al sensore**.

> 💡 **Perché è pericoloso.** Un'impronta ricostruita non sarà identica a quella vera, ma per il matcher conta solo che produca **le stesse minuzie**. Stampata su un dito finto, può ingannare il sensore (attacco I) partendo da un template rubato dal database (attacco VI).

<!-- SOURCE_VISUAL id="TAB13-V06" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="29" type="confronto di immagini" description="Ricostruzione di un'impronta a partire dalla lista di minuzie del template" reason="Mostra il pattern ricostruito compatibile con le minuzie" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3 Perché l'hash della password non funziona**

_(slide pp. 30–31)_

Con le **password** la protezione classica è semplice: si memorizza $H(\text{password})$; in autenticazione si calcola l'hash della password inserita e si **confrontano gli hash** (vero o falso).

> ⚠️ **Con la biometria non si può.** Il dato biometrico **cambia a ogni acquisizione** (variabilità intraclasse). Una funzione hash crittografica ha l'**effetto valanga**: due campioni quasi uguali danno hash completamente diversi, e il confronto fallisce sempre. Se invece due campioni risultano **identici bit per bit**, non è un successo: è il segnale di un **replay attack**.

<!-- SOURCE_VISUAL id="TAB13-V07" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="31" type="schema" description="Protezione classica della password con hash e confronto in autenticazione" reason="Schema del flusso di enrollment e verifica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4 Le famiglie di protezione del template**

_(slide p. 32)_

_(dispensa pp. 8–9)_

> 📌 **Le proprietà ideali di un metodo di protezione del template** (dispensa p. 8):
> 1. **diversità**: il template protetto non deve permettere il **cross-matching** tra database diversi;
> 2. **revocabilità**: un template compromesso deve poter essere **revocato**;
> 3. **sicurezza**: stimare il template in chiaro da quello protetto deve essere **computazionalmente difficile**;
> 4. **prestazioni**: l'accuratezza del sistema **non deve peggiorare**.

Queste proprietà **non** si ottengono cifrando i template con metodi standard come **RSA** o **AES**: per la variabilità intraclasse il matching non si può fare nel dominio cifrato, quindi bisognerebbe **decifrare** i template a ogni tentativo, esponendoli.

Le famiglie di metodi (slide p. 32; dispensa pp. 3 e 9):

| Famiglia | Idea | Matcher |
|---|---|---|
| **Biometria cancellabile** | trasformazione intenzionale del template; si **resta nello stesso spazio** delle feature | **stesso** estrattore e matcher della biometria normale |
| **Crittosistemi biometrici** (*biometric encryption*) | dal template si ottiene o si lega una **chiave crittografica**; si confrontano chiavi | matcher **appositi** (codici a correzione d'errore) |
| **Metodi crittograficamente sicuri** | operazioni svolte **senza decifrare** i dati | **matcher speciali** nel **dominio crittato** |

<!-- SOURCE_VISUAL id="TAB13-V08" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="32" type="schema" description="Distinzione tra biometria cancellabile nello stesso spazio delle feature ed elaborazione nel dominio crittato con matcher speciali" reason="Schema comparativo delle famiglie" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Biometria cancellabile**

#### **7.1 Contesto e definizione**

_(slide pp. 33–36)_

_(dispensa p. 9)_

Se rubano la **password**, la si cambia; se rubano il **token**, lo si sostituisce. Se rubano o copiano **impronta, volto o iride**, non si possono cambiare. La biometria cancellabile è la **prima soluzione per la privacy sul dato**: incorpora nella biometria una **funzione di sostituzione** (pp. 33–34).

> 📌 **Biometria cancellabile** (p. 35). La **distorsione intenzionale e sistematicamente ripetibile** delle caratteristiche biometriche al fine di proteggere i dati sensibili dell'utente. Deve permettere di usare **lo stesso estrattore di template e lo stesso matcher** della biometria normale.

**Come funziona** (dispensa p. 9, Fig. 3):

- in **enrollment** il template $T$ viene trasformato con una funzione $F$ i cui parametri $K$ derivano da una **chiave casuale** o da una **password**; nel database si salva solo $F(T, K)$;
- in **verifica** la stessa trasformazione si applica alla query $Q$ e si confrontano direttamente $F(Q, K)$ e $F(T, K)$.

**Attuazione della protezione** (p. 36): se una biometria **rinnovabile** viene compromessa, si cambiano i parametri della distorsione e lo stesso tratto viene mappato su un **nuovo modello**.

| Mondo fisico | Biometria rinnovabile |
|---|---|
| perdo una chiave → **cambio la serratura** | perdo una biometria rinnovabile → **cambio metodo di deformazione** |

La dispensa aggiunge due requisiti di progetto: la funzione deve essere **robusta alla variabilità intraclasse**, per non ridurre l'accuratezza, e la **correlazione** tra template trasformati non deve rivelare la funzione. Le trasformazioni si applicano a **sample** (immagini del volto), **segnali elaborati** (pattern dell'iride) o **template** (feature del volto).

<!-- SOURCE_VISUAL id="TAB13-V09" source="Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf" page="9" type="schema" description="Fig. 3: biometria cancellabile, enrollment e verifica con F(T,K) e F(Q,K)" reason="Schema del flusso di enrollment e verifica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2 I quattro obiettivi**

_(slide p. 37)_

| Obiettivo | Significato (slide) |
|---|---|
| **Diversità** | non usare lo stesso template cancellabile in applicazioni diverse, ma **uno per applicazione** |
| **Riusabilità / revocabilità** | deve essere **facile e immediato** revocare il template e crearne uno nuovo in caso di compromissione |
| **Non invertibilità** | il template cancellabile non deve essere invertibile al template originale in **nessun modo praticabile** |
| **Prestazioni** | il metodo non deve peggiorare **apprezzabilmente** l'accuratezza |

Sono le stesse quattro proprietà della dispensa (§6.4), dove la non invertibilità si chiama **sicurezza**.

#### **7.3 Salting e trasformazioni non invertibili**

_(slide pp. 38–42)_

_(dispensa p. 10)_

Le trasformazioni delle feature si dividono in due classi (p. 39).

**1. Bio-hashing (salting).** La funzione di trasformazione è **invertibile**: chi ottiene **chiave** e template trasformato può ricostruire l'originale. La sicurezza **dipende dalla segretezza della chiave**.

> 💡 **L'esempio della slide.** Pensa un numero $X$, somma $K$, trasmetti $Y = X + K$. La somma è invertibile con la sottrazione, ma senza conoscere $K$ non puoi ricavare $X$.

Nello schema del salting (pp. 40–41) si **aggiunge «rumore»** al template con una chiave $K$, e **sia la chiave sia il template** vanno tenuti **segreti**.

> ⚠️ **Il problema del salting** (p. 42). La funzione $F(\cdot)$ è **invertibile**. Se si perde la chiave $K$, il sistema è compromesso: invertendo il template memorizzato $F(T, K)$ con $K$ si ricava proprio $T$, cioè il dato che si voleva proteggere.

**2. Trasformazione non invertibile.** È una funzione **one-way**: anche **conoscendo la chiave** è computazionalmente difficile tornare al template originale.

> 💡 **L'esempio della slide.** Pensa un numero $X$, trasmetti $Y = F(X)$: pur conoscendo $Y$ e $F$ non sai tornare a $X$.

**Pro e contro secondo la dispensa** (p. 10):

| | **Salting** | **Non invertibile** |
|---|---|---|
| Vantaggi | template **multipli** dello stesso tratto, perché le chiavi sono dell'utente | protezione **più sicura**: con chiave e template trasformato la stima dell'originale resta difficile (forza bruta); diversità e revocabilità con funzioni diverse |
| Svantaggi | la password va custodita e presentata a ogni autenticazione; se la chiave è nota si risale al template | è difficile progettare funzioni **discriminanti e non invertibili** insieme; la funzione dipende dalle feature dell'applicazione |
| Identificazione | **non usabile** con chiavi o password | **non usabile** se le chiavi derivano da password o token |

**Esempi di metodi** citati dalla dispensa:

- **salting**: il **BioHashing** per l'impronta, in due passi: una trasformazione invariante e discriminante, poi una **discretizzazione**. Per il volto: analisi discriminante di Fisher seguita da proiezione su **direzioni ortogonali casuali**, oppure filtri a **minima energia di correlazione media**. Altri tratti: iride, palmo, firma dinamica;
- **non invertibili**: per impronta, volto e firma. Tra questi: lo schema generale di Ratha et al. con **polinomi di ordine elevato** su pattern di punti (minuzie, voce); le trasformazioni **cartesiana, polare e funzionale** delle minuzie; lo schema **Biotope**, basato su primitive crittografiche e metriche di distanza robuste.

<!-- SOURCE_VISUAL id="TAB13-V10" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="38" type="schema" description="Schema delle trasformazioni delle feature nella biometria cancellabile" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V11" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="41" type="schema" description="Salting: template e chiave K segreti, template memorizzato F(T,K)" reason="Schema del salting con i blocchi segreti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **8. Biometria cancellabile per l'iride: le permutazioni dell'IrisCode**

_(slide pp. 43–46)_

**Il problema** (p. 43): «se mi rubano l'IrisCode, non posso cambiare il mio occhio!». È vero che dall'IrisCode **non** si ricostruisce completamente l'iride, ma si può applicare una **trasformazione non facilmente invertibile**.

**L'idea.** Si permutano i bit dell'IrisCode secondo uno **schema di permutazione** definito da una **chiave privata** $K$. La slide conta $256! \approx 10^{507}$ schemi possibili: il vecchio IrisCode diventa di fatto **inservibile**.

> 💡 **Verifica del numero.** $256! \approx 8{,}6 \times 10^{506}$, cioè circa $10^{507}$. Il conteggio corrisponde a permutazioni di **256 elementi** (per esempio 256 blocchi); permutando singolarmente tutti i 2048 bit di un IrisCode classico lo spazio sarebbe ancora più grande.

> 📌 **Perché si usa lo stesso matcher.** Lo XOR **non risente delle permutazioni**, purché entrambi i codici siano permutati con la stessa chiave:
> $$\mathrm{XOR}(A, B) = \mathrm{XOR}\big(\mathrm{Perm}(A, K_1),\ \mathrm{Perm}(B, K_1)\big)$$
> a meno dell'ordine dei bit. Il numero di bit diversi, e quindi la **distanza di Hamming**, resta identico:
> $$\big\| \pi_K(A) \oplus \pi_K(B) \big\| = \big\| \pi_K(A \oplus B) \big\| = \| A \oplus B \|$$
> Si usa perciò il **matcher classico** dell'IrisCode (Lezione 4).

**Esempio con un IrisCode di 5 bit** (p. 45):

$$\text{IrisCode} = [b_1, b_2, b_3, b_4, b_5], \qquad K_1 = [5, 3, 2, 4, 1] \ \text{(segreta)}$$

$$\text{IrisCode}_{P1} = \mathrm{Perm}(\text{IrisCode}, K_1) = [c_1, c_2, c_3, c_4, c_5] = [b_5, b_3, b_2, b_4, b_1]$$

> 💡 **Nota sulla lettura della chiave.** Con $c_i = b_{K_1(i)}$ si ottiene $[b_5, b_3, b_2, b_4, b_1]$. Questa particolare $K_1$ scambia 1 con 5 e 2 con 3 e lascia fisso 4: è la **propria inversa**, quindi anche la lettura opposta ($b_i$ va in posizione $K_1(i)$) dà lo stesso risultato.

> ⚠️ **Il prezzo della permutazione** (p. 45). Le **rotazioni della testa** non si possono più correggere con gli **shift circolari** dei bit visti nella lezione sull'iride: la permutazione distrugge l'adiacenza angolare dei bit, quindi uno shift del codice permutato non corrisponde a una rotazione dell'occhio.

**Permutazioni diverse per usi diversi** (p. 44). La permutazione deve restare segreta, ma se ne possono «mandare in giro» di **diverse**, creando IrisCode permutati specifici per ogni **applicazione**, **sensore**, **intervallo di tempo** e così via: è la **diversità**.

**Protezione dal furto del template** (p. 46):

1. **oggi** il mio dispositivo invia all'applicazione esterna $\mathrm{Perm}(\text{IrisCode}, K_1)$, che lo usa per lo XOR;
2. l'IrisCode permutato $P_1$ viene **rubato**;
3. **domani** il dispositivo usa una nuova chiave $K_2$: si **rifà l'enrollment** con $\mathrm{Perm}(\text{IrisCode}, K_2)$;
4. il codice $P_1$ rubato **non è più utilizzabile**.

<!-- SOURCE_VISUAL id="TAB13-V12" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="43" type="schema" description="Schema di permutazione dell'IrisCode con chiave K1 tra dispositivo e applicazione esterna" reason="Schema del flusso con permutazione e XOR" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V13" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="45" type="schema" description="Esempio di permutazione di un IrisCode di 5 bit con K1 = [5, 3, 2, 4, 1]" reason="Frecce della permutazione visibili solo nel disegno" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V14" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="46" type="schema" description="Cambio della permutazione da K1 a K2 dopo il furto del template: nuovo enrollment e codice rubato inutilizzabile" reason="Schema temporale oggi/domani" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. Trasformazioni non invertibili**

_(slide pp. 47–53)_

#### **9.1 Funzioni «many-to-one»**

Una funzione **molti-a-uno** associa lo stesso output a molti input diversi (pp. 47–48). L'esempio più semplice su $\mathbb{R} \to \mathbb{R}$:

$$F(x) = x^2, \qquad F(2) = F(-2) = 4$$

Se in uscita vedo 4, **non so** se l'ingresso era $x = 2$ o $x = -2$. Con un polinomio di **grado molto maggiore di 2** le preimmagini possibili aumentano.

**Il procedimento** (p. 49):

1. il modello biometrico acquisito viene **trasformato** e **solo il modello trasformato** viene salvato nel database;
2. i **parametri** della trasformazione derivano da una **chiave** o password casuale, per esempio i **coefficienti di un polinomio**;
3. la **stessa** trasformazione si applica alla **query**, che viene confrontata con il modello trasformato.

> 📌 **La differenza con il salting** (p. 50). Nella trasformazione non invertibile $F(X, K)$ la chiave $K$ può essere **pubblica**: anche conoscendo $F$ e $K$ non si torna a $T_1$, perché esistono **tantissimi** template che arrivano allo stesso $F(T_1, K)$.

#### **9.2 Esempi su volto e impronte**

- **Volto** (pp. 51–52): **morphing non invertibile** dell'immagine del volto.
- **Impronte** (p. 53): **deformazione geometrica delle coordinate** delle minuzie. Dopo l'estrattore di minuzie si applica una funzione **non reversibile**: altre minuzie in ingresso possono arrivare **negli stessi punti** in uscita di $F(T_1, K)$.

> 💡 **Perché serve la collisione.** Se la deformazione fosse biunivoca, basterebbe invertirla. Facendo «collassare» regioni diverse del piano sugli stessi punti, dalla sola uscita non si sa più da quale regione venisse ogni minuzia. Il matcher però funziona ancora, perché due impronte simili subiscono la stessa deformazione.

<!-- SOURCE_VISUAL id="TAB13-V15" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="47" type="grafico" description="Funzione many-to-one usata come trasformazione non invertibile" reason="Grafico della funzione senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V16" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="50" type="schema" description="Trasformazione non invertibile F(X,K) con chiave pubblica e molti template che arrivano allo stesso risultato" reason="Schema del flusso con chiave non segreta" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V17" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="52" type="sequenza di immagini" description="Volto sottoposto a morphing non invertibile" reason="Esempio visivo senza testo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V18" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="53" type="schema" description="Deformazione geometrica non reversibile delle coordinate delle minuzie" reason="Schema dell'estrattore e della funzione non reversibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **10. Biometric Encryption: i crittosistemi biometrici**

#### **10.1 Il template diventa la chiave**

_(slide pp. 54–57)_

_(dispensa p. 11)_

Nei crittosistemi biometrici il **template biometrico deve diventare la chiave**, e funzionare (p. 54). Rispetto alla biometria cancellabile, dove dopo la trasformazione si potevano usare i sistemi tradizionali, sono approcci **più strutturali e interni** al sistema, che **richiedono matcher appositi** (p. 55).

> 📌 **Biometric Encryption (BE)** (p. 56). Gruppo di tecnologie che, in modo sicuro:
> - **legano** una chiave digitale a un dato biometrico: $\text{template} = \mathrm{Encrypt}[\text{key} + \text{biometric}]$;
> - oppure **generano** una chiave digitale dal dato biometrico: $\text{Biometric} \rightarrow [\text{Key}]$;
>
> così che **nessuna immagine o template** biometrico venga memorizzato.

**Lista dei desideri** (p. 56):

- deve essere **computazionalmente difficile** recuperare la chiave o il dato biometrico dal template BE memorizzato, detto **helper data**;
- la chiave si **ricrea solo** se in verifica viene presentato il campione **genuino**;
- l'uscita dell'autenticazione è una **chiave** (corretta o errata) oppure un **messaggio di fallimento**.

**Caratteristiche** (p. 57):

- a differenza della crittografia convenzionale, il processo di cifratura e decifratura è **fuzzy**, per la variabilità naturale della biometria;
- è **concettualmente diverso** sia dai sistemi che cifrano immagini o template con la crittografia convenzionale, sia da quelli che memorizzano una chiave e la **rilasciano** dopo un'autenticazione biometrica riuscita.

**Secondo la dispensa** (p. 11): i crittosistemi biometrici nascono per **proteggere chiavi crittografiche** con la biometria o per **calcolarle** dai dati biometrici; oggi si usano anche per la privacy dei template. Memorizzano dati pubblici (helper data), da cui in autenticazione si **estrae una chiave** usando il campione query; il matching **verifica la validità** della chiave ottenuta.

#### **10.2 Key binding e key generation**

_(slide p. 58)_

| | **Key binding** | **Key generation** |
|---|---|---|
| Helper data | ottenuto **legando una chiave** al template | derivato **solo dal template** |
| Chiave | scelta e **esterna** | generata dall'helper data e dalle feature della query |
| Sicurezza | dal solo helper data è difficile ottenere chiave o template | la chiave non è mai memorizzata |

Quando si combinano più approcci di protezione si parla di **schema ibrido** (*hybrid template protection scheme*).

#### **10.3 Key binding**

_(slide pp. 59–61)_

_(dispensa pp. 11–12)_

**Il meccanismo** (p. 60):

1. serve una **chiave** $k$;
2. la chiave viene legata a una trasformazione del template $b$ tramite un **codice a correzione d'errore**, creando l'**helper data**;
3. in verifica, se arriva un template **abbastanza simile**, l'estrattore produce un codice $b'$ che permette di **ricostruire la chiave** dall'helper data; altrimenti fallisce, cioè $k' \neq k$;
4. per sapere se la chiave è quella giusta si memorizza **in chiaro** $\mathrm{Hash}(k)$.

> 📌 **Perché si chiamano «helper» data** (p. 60). **Aiutano** la ricostruzione, **non contengono** l'informazione sensibile e **non sono segreti** in senso crittografico.

Nell'esempio di schema completo (p. 61, **non all'esame**) il «trucco» è il codice a correzione d'errore, per esempio **Reed-Solomon**.

**Vantaggi e limiti** (dispensa pp. 11–12, Fig. 4):

- ✅ l'helper data rivela **poco** su chiave e template;
- ✅ l'approccio **tollera la variabilità** intra-utente;
- ❌ l'accuratezza **peggiora**, perché gli algoritmi di matching originali sono sostituiti da schemi di correzione d'errore;
- ❌ **non** garantisce **diversità** e **revocabilità**.

**Metodi noti** (dispensa p. 12):

- **Mytec1 e Mytec2**: i primi, su impronte, basati sulla correlazione tra funzioni filtro e immagini;
- **fuzzy commitment** (Juels-Wattenberg);
- **fuzzy vault** (Juels-Sudan);
- **shielding functions** e **distributed source coding**, su volto, impronta, iride e firma.

> 📌 **Fuzzy commitment** (dispensa p. 12). In enrollment si legano un template $x$ (vettore di lunghezza fissa) e una **parola di codice** $w$ di un codice a correzione d'errore $C$. L'**helper data** è la coppia
> $$\big(x - w,\ h(w)\big)$$
> con $h$ funzione hash. In verifica, dalla query $x'$ si sottrae il valore memorizzato:
> $$w' = x' - (x - w) = w + \delta, \qquad \delta = x' - x$$
> Se la decodifica di $w'$ con $C$ restituisce $w$ (verificato con $h(w)$), il matching è **positivo**.

> ⚠️ **Refuso nella dispensa.** Il testo scrive «$w' = w + \delta$, where $w' = x' - x$»; la definizione corretta è $\delta = x' - x$, cioè la differenza tra query e template, che il codice deve correggere.

> 💡 **Versione binaria (nota didattica).** Con template binari come l'IrisCode la sottrazione diventa XOR: si salva $x \oplus w$, e in verifica $x' \oplus (x \oplus w) = w \oplus (x' \oplus x)$. I bit in cui la query differisce dal template sono **errori** sulla parola di codice: se sono meno di quelli correggibili dal codice, si recupera $w$. La **distanza di Hamming** tra genuini diventa così la capacità di correzione del codice.

**Fuzzy vault** (dispensa p. 12). Un insieme $A$ **chiude** una chiave segreta $k$ in un «vault» $V_A$. Se si ricostruisce $k$ usando un insieme $B$ **sufficientemente simile** ad $A$, il vault si apre. Si basa su **codifica polinomiale** e **correzione d'errore**.

> 💡 **Come si costruisce il vault (nota didattica).** La chiave è codificata nei coefficienti di un polinomio $p$. Per ogni elemento $a_i$ di $A$ (per esempio una minuzia) si salva il punto $(a_i, p(a_i))$, poi si aggiungono molti **punti falsi** (*chaff*) che non stanno sul polinomio. Chi possiede un insieme $B$ con abbastanza elementi in comune con $A$ seleziona i punti giusti e ricostruisce $p$ per interpolazione; un attaccante non sa quali punti scegliere.

<!-- SOURCE_VISUAL id="TAB13-V19" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="59" type="schema" description="Schema generale del crittosistema biometrico con helper data" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V20" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="60" type="schema" description="Key binding: chiave k legata al template b con codice a correzione d'errore, helper data e Hash(k)" reason="Schema del flusso di enrollment e verifica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V21" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="61" type="schema" description="Esempio completo di key binding con codice Reed-Solomon (non all'esame)" reason="Schema dettagliato dei blocchi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V22" source="Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf" page="11" type="schema" description="Fig. 4: crittosistema biometrico key-binding, enrollment e verifica" reason="Schema del flusso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.4 Key generation**

_(slide pp. 62–64)_

_(dispensa pp. 12–13)_

Nella generazione della chiave **entra solo il template**: **non serve una chiave esterna** (p. 63). L'helper data **può essere pubblico**.

**Procedimento** (p. 64):

- si memorizza l'**helper data**, informazione pubblica sul template che **non rivela** informazioni significative;
- l'helper data serve a **estrarre una chiave** crittografica dalla query;
- il matching è **indiretto**: si verifica la **validità** della chiave estratta;
- per gestire le variazioni si usano tipicamente **codici a correzione d'errore** (per esempio Reed-Solomon).

**Secondo la dispensa** (pp. 12–13, Fig. 5):

- **vantaggio**: le chiavi ottenute si possono usare in **molte applicazioni**;
- **problema**: è difficile generare chiavi con **alta stabilità** e **alta entropia**.

| Metodo | Idea (dispensa) |
|---|---|
| **Secure sketch** | risolve la **tolleranza agli errori**: da una lettura $r$ si calcola un dato pubblico $P$ tale che, da un'altra lettura $r'$ **abbastanza vicina** a $r$, si ricostruisce l'originale $r$ |
| **Fuzzy extractor** | risolve la **non uniformità**: associa a $P$ una stringa casuale **uniforme** $R$, recuperabile da $P$ e da una lettura $r'$ vicina; si può costruire a partire da un secure sketch |
| **PinSketch** | schema basato sulla **sindrome** e sull'interpolazione polinomiale; rispetto al fuzzy vault **riduce** il tempo di calcolo e la lunghezza della chiave pubblica |
| **Approccio multi-tratto** (rif. [13]) | basato sul fuzzy commitment: una lettura viene messa in XOR con una stringa ottenuta per **permutazione pseudo-casuale** dell'altra (è lo schema del §12) |

<!-- SOURCE_VISUAL id="TAB13-V23" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="62" type="schema" description="Schema di key generation senza chiave esterna" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V24" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="63" type="schema" description="Helper data pubblico: entra solo il template, non serve una chiave esterna" reason="Schema del flusso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V25" source="Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf" page="12" type="schema" description="Fig. 5: crittosistema biometrico key-generating, enrollment e verifica" reason="Schema del flusso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **10.5 Riepilogo dei metodi**

_(slide pp. 65–66)_

La slide p. 65 riassume graficamente i **metodi di protezione standard**. La p. 66 elenca le altre tecniche di protezione dei template:

- **feature transformation**;
- **helper data approach**: fuzzy commitment, fuzzy vault, secure sketch, fuzzy extractor, PinSketch;
- **bio-hashing**;
- **revocable bio-token**;
- **Biotope**;
- **Bio-Encryptor**.

<!-- SOURCE_VISUAL id="TAB13-V26" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="65" type="schema riassuntivo" description="Riassunto grafico dei metodi di protezione standard del template" reason="Classificazione presente solo nell'immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **11. Elaborazione biometrica nel dominio crittato**

_(slide pp. 67–70)_

_(dispensa pp. 13–14)_

**Il limite delle famiglie precedenti** (dispensa p. 13): le trasformazioni della biometria cancellabile possono **ridurre l'accuratezza**; nei crittosistemi biometrici non si possono sempre usare i matcher migliori. I **metodi crittograficamente sicuri** eseguono il riconoscimento **senza trasformare** i dati e **senza cambiare** il matcher, operando **direttamente sui dati cifrati**. Di solito si basano su **crittosistemi omomorfi**.

> 📌 **Crittosistema omomorfo** (dispensa p. 13). Dati l'insieme $M$ dei testi in chiaro e $C$ dei testi cifrati, per ogni chiave $k$ la funzione di cifratura $E$ soddisfa
> $$\forall m_1, m_2 \in M: \quad E(m_1 \odot_M m_2) \leftarrow E(m_1) \odot_C E(m_2)$$
> per qualche operatore $\odot_M$ su $M$ e $\odot_C$ su $C$, dove $\leftarrow$ significa «**si può calcolare direttamente da**», cioè **senza decifrare**.

**Nelle slide** (p. 69): nell'elaborazione nel dominio crittato **il server non sa che cosa sta facendo**. Sono possibili solo operazioni come **somme, differenze, moltiplicazioni e soglie**, quindi matching **piuttosto semplici**; di recente si è realizzata la **prima CNN**, estesa ma semplice. Per questi temi la slide p. 70 rimanda alla **dispensa**.

| | Metodi crittograficamente sicuri |
|---|---|
| **Vantaggio** | accuratezza **molto simile** a quella in chiaro; un calo può venire solo da **quantizzazione** o riduzione dei dati eccessive |
| **Svantaggi** | difficili da usare con **funzioni di matching complesse**; **computazionalmente costosi** |

**Esempio sulle impronte** (dispensa p. 14, Fig. 6), per architetture distribuite:

- come feature si usa il **FingerCode**: valori numerici ottenuti filtrando l'impronta con **filtri di Gabor** a orientazioni diverse (Lezione 3);
- il protocollo sfrutta la **cifratura additivamente omomorfa**: lo schema di **Paillier** e una variante di **ElGamal su curve ellittiche**;
- lato **client** il template viene calcolato, **quantizzato** e **cifrato** con la chiave pubblica del client;
- il **server** calcola il punteggio nel dominio cifrato: la **distanza euclidea al quadrato** tra i template;
- infine server e client interagiscono per selezionare, **sempre nel dominio cifrato**, le identità con distanza **sotto soglia**.

> 💡 **Come si calcola una distanza senza decifrare (nota didattica).** Con Paillier vale $E(a) \cdot E(b) = E(a + b)$ ed $E(a)^c = E(c \cdot a)$. La distanza euclidea al quadrato si scompone in
> $$d^2(\mathbf{x}, \mathbf{y}) = \sum_i x_i^2 - 2 \sum_i x_i y_i + \sum_i y_i^2$$
> Se il client invia $E(x_i)$ ed $E\big(\sum_i x_i^2\big)$ e il server conosce $y_i$, il server ottiene
> $$E(d^2) = E\Big(\sum_i x_i^2\Big) \cdot \prod_i E(x_i)^{-2 y_i} \cdot E\Big(\sum_i y_i^2\Big)$$
> usando solo moltiplicazioni ed esponenziazioni sui cifrati. Il confronto con la soglia richiede invece un protocollo interattivo, come quello descritto dalla dispensa.

**Altri esempi** (dispensa p. 14): approcci omomorfi per il **volto**, e per sistemi con **distanza di Hamming** (come l'IrisCode) basati su **Blum-Goldwasser**, **Goldwasser-Micali**, combinazioni di Goldwasser-Micali e **Paillier**, **ElGamal** con **garbled circuits**.

<!-- SOURCE_VISUAL id="TAB13-V27" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="67" type="pagina di sezione" description="Elaborazione biometrica nel dominio crittato" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V28" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="68" type="schema" description="Matcher speciali per l'elaborazione nel dominio crittato" reason="Schema del flusso cifrato" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V29" source="Lezione_13_Protezione_della_privacy_nei_sistemi_biometrici_regole_e_progettazione_DISPENSA.pdf" page="14" type="schema" description="Fig. 6: riconoscimento di impronte nel dominio cifrato con crittosistema omomorfo" reason="Schema del protocollo client-server" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **12. Caso di studio: cifrare un occhio con l'altro**

#### **12.1 L'obiettivo e l'idea**

_(slide pp. 71–75)_

**Obiettivo** (p. 72): generare un **identificativo** combinando tratti biometrici in modo che sia **impossibile risalire ai template** originali, preservando la privacy.

**Soluzione** (p. 73): creare un'identificazione biometrica **cifrando l'occhio destro con l'occhio sinistro**. Senza il permesso dell'utente, i dati biometrici **non possono essere usati**.

**L'idea semplificata** (p. 74):

1. si ottengono gli IrisCode dell'occhio **destro** $D$ e **sinistro** $S$;
2. **enrollment**: il template è un mix **non reversibile** dei due, che si può immaginare come
$$T = D + S$$
Senza conoscere $D$ e $S$ non si ha **nessuna informazione usabile** dell'utente;
3. **verifica**: si acquisiscono i nuovi campioni $D'$ e $S'$, si **sottrae** $S'$ al template e si confronta il risultato con $D'$:
$$T - S' = (D + S) - S' \approx D \qquad \text{perché } S' \approx S$$
La decisione (sì o no) dipende dalla distanza tra $T - S'$ e $D'$.

**Contesto** (p. 75): è un **crittosistema biometrico** con helper data, ma invece di un solo campione usa **lo stesso tratto** con **due campioni indipendenti**, occhio sinistro e destro.

<!-- SOURCE_VISUAL id="TAB13-V30" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="71" type="pagina di sezione" description="Esempi avanzati di trasformazioni per la protezione dei template" reason="Pagina grafica di sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V31" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="74" type="schema" description="Idea IrideSX+IrideDX: T = D + S, verifica con T - S' confrontato con D'" reason="Schema a blocchi della verifica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.2 Lo schema completo (non all'esame)**

_(slide pp. 76–82)_

> ⚠️ **Non richiesto all'esame.** Le slide pp. 76–82 riportano che lo schema dettagliato è un esempio. All'esame servono i **concetti** e le **prestazioni** (§12.3).

La slide mostra i blocchi dello schema: **codifica a correzione d'errore**, **funzione hash**, **permutazione pseudo-casuale**, due IrisCode $I_1$ (lungo) e $I_2$ (corto), l'helper data $\delta$ e l'hash $H(I_2)$. L'**identificativo** memorizzato è $ID = \{H(I_2), \delta\}$.

**Enrollment** (p. 76). La sequenza dei blocchi sulla slide, coerente con la descrizione della dispensa (p. 13, rif. [13]: Cimato, Gamassi, Piuri, Sassi, Scotti, 2007), è:

$$\delta = I_1 \oplus \mathrm{PRP}\big(\mathrm{ECC}(I_2)\big), \qquad ID = \{H(I_2),\ \delta\}$$

**Verifica** (p. 77):

1. si acquisisce $I_1'$ e si calcola $I_1' \oplus \delta$;
2. si applica la **permutazione inversa** e la **decodifica** a correzione d'errore, ottenendo una stima di $I_2$;
3. se il suo hash coincide con $H(I_2)$, il sottomodulo **abilita** il passo successivo;
4. la stima di $I_2$ viene confrontata con l'iride $I_2'$ appena acquisita tramite **matching biometrico** (sì o no).

> ⚠️ **Formula ricostruita.** Il livello testo delle pp. 76–77 contiene solo le etichette dei blocchi: l'ordine delle operazioni è ricostruito dal diagramma e dalla dispensa e va verificato sulla slide.

**Nota sul codice** (p. 76): data $N_1$, la lunghezza in bit di $I_1$, il codice deve correggere al più $r_1 N_1$ errori su parole lunghe $N_1$ bit.

**Dataset** (p. 78): **CASIA versione 1.0**, **7 immagini** dello stesso occhio per **108 soggetti**, acquisite dalla Chinese Academy of Sciences in **infrarosso vicino**, in due sessioni ad almeno **un mese** di distanza (**3** immagini nella prima e **4** nella seconda).

**Template** (pp. 79–80):

- IrisCode creati con il **metodo di Daugman** (libreria pubblica): immagine → binarizzazione → normalizzazione (con bit di mascheramento) → IrisCode;
- $I_1$: **occhio destro**, **9600 bit** (risoluzione radiale 20);
- $I_2$: **occhio sinistro**, **1920 bit** (risoluzione radiale 20);
- per sicurezza è meglio **non includere i bit di mascheramento**, perché la distribuzione degli zeri nelle maschere è **lontana dall'essere uniforme**.

> 💡 **Da dove vengono i 9600 e 1920 bit (nota didattica).** Con 20 anelli radiali e 2 bit per campione (fase quantizzata in 4 quadranti, Lezione 4), $20 \times 240 \times 2 = 9600$ e $20 \times 48 \times 2 = 1920$: i due codici differiscono per la **risoluzione angolare** (240 contro 48 campioni).

> 💡 **Perché le maschere sono pericolose.** Le maschere segnano palpebre, ciglia e riflessi, che cadono sempre in zone simili dell'iride: i loro bit sono molto prevedibili. Mescolati in XOR con i dati, abbasserebbero l'entropia effettiva e aiuterebbero un attaccante a ricostruire i bit veri.

**Codici** (p. 81):

- **Reed-Solomon** $[9600, 1920, 7681]$ con $m = 14$, $n_1 = 9600$ e $r_1 = 0{,}4$, il **punto di separazione ottimale** tra due template di genuini;
- con questa configurazione si possono memorizzare fino a $k = 1920$ bit per il secondo template;
- **permutazione pseudo-casuale**: **AES in modalità CTR**.

> 💡 **Verifica dei parametri (nota didattica).** Per un codice Reed-Solomon $[n, k, d]$ vale $d = n - k + 1 = 9600 - 1920 + 1 = 7681$ ✓. Il codice corregge fino a $t = \lfloor (d-1)/2 \rfloor = 3840$ errori, cioè esattamente $0{,}4 \times 9600$ ✓. I simboli stanno in $\mathrm{GF}(2^{14})$, che ammette lunghezze fino a $2^{14} - 1 = 16383 \geq 9600$ ✓. La slide parla di bit, ma nel codice RS l'unità è il simbolo.

**Procedure** (p. 82):

- **enrollment**: **best of three**, cioè la migliore di tre acquisizioni, con qualità misurata dal **numero di bit di mascheramento**;
- **autenticazione**: **best of four**, cioè 4 tentativi di sblocco e 4 matching biometrici.

<!-- SOURCE_VISUAL id="TAB13-V32" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="76" type="schema" description="IrideSX+IrideDX, enrollment: codifica a correzione d'errore, hash, permutazione pseudo-casuale, I1, I2, delta, ID" reason="Diagramma a blocchi con formule non estraibili" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V33" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="77" type="schema" description="IrideSX+IrideDX, verifica: decodifica, permutazione inversa, hash, abilitazione e matching biometrico" reason="Diagramma a blocchi con formule non estraibili" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V34" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="81" type="schema" description="Parametri del codice Reed-Solomon e della permutazione AES-CTR" reason="Parametri del codice nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **12.3 Prestazioni e conclusioni (in esame)**

_(slide pp. 83–84)_

È un **esempio di fusione biometrica con metodi crittografici** (p. 83):

| Sistema | EER |
|---|---:|
| occhio destro (9600 bit) | **0,5%** |
| occhio sinistro (1800 bit nella slide) | **9,9%** |
| **schema integrato** | **0,96%** |

Per un'**ampia porzione della curva ROC**, lo schema proposto ottiene prestazioni **migliori** del miglior componente (l'occhio destro).

> ⚠️ **Due incongruenze della slide.** L'occhio sinistro è indicato con **1800 bit** a p. 83 e con **1920 bit** a p. 80; il valore del sistema integrato è etichettato «ERR» invece di **EER**.

**Conclusioni** (p. 84). Lo schema bilancia **accuratezza e protezione**:

- **peggiora leggermente l'EER** rispetto al miglior tratto singolo (0,96% contro 0,5%);
- ma **migliora la privacy**: il template dell'utente è **inutilizzabile** senza l'altro occhio presentato «live», che **non è mai memorizzato**;
- i metodi migliori realizzano proprio questo tipo di bilanciamento.

> 💡 **Come conciliare «migliora» e «peggiora».** L'EER è un solo punto della curva, quello in cui FMR = FNMR. La p. 83 dice che **in gran parte della ROC** lo schema integrato è migliore, la p. 84 che **nel punto EER** è leggermente peggiore. Entrambe le affermazioni sono vere: per scegliere bisogna guardare il punto di lavoro dell'applicazione.

<!-- SOURCE_VISUAL id="TAB13-V35" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="83" type="curva ROC" description="Prestazioni: occhio destro EER 0,5%, sinistro 9,9%, schema integrato 0,96%" reason="Confronto delle curve ROC" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **13. Soluzioni commerciali e sistemi decentralizzati**

_(slide pp. 85–88)_

**ZeroBiometrics, ZeroFace** (p. 85):

- genera un **ZeroHash da 256 bit** unico per ogni persona;
- **nessun** dato biometrico o identificativo viene richiesto o memorizzato;
- le **ZeroKeys** vengono create durante l'autenticazione e **distrutte** quando si è offline;
- dai dati disponibili **sembra** trattarsi di una **trasformazione non invertibile**.

**Decentralized Biometric Cryptosystem (DBC)** (pp. 86–87):

- l'informazione biometrica **non** sta in un unico database centrale, ma è memorizzata in modo **distribuito o federato**, con **primitive crittografiche**, per ridurre rischi di compromissione, abuso e violazioni della privacy;
- un DBC **non** è un semplice sistema biometrico distribuito: DBC = **biometria + crittografia + distribuzione** (la slide scrive «DBD», refuso);
- impiega **fuzzy commitment, fuzzy vault, secure sketch, MPC** (calcolo multiparte sicuro);
- esempi: **applicazioni web 3.0** su **blockchain**, **crypto-biometrics-coins**, un esempio completo nella lezione su **Proof of Personhood e deepfake** (Lezione 17), autenticazione d'identità biometrica decentralizzata in sistemi federati (brevetto **WO2018089098A1**).

**Funzionalità di privacy da ricercare nei software biometrici** (p. 88). Il progettista deve verificare che siano disponibili:

| Funzionalità | Descrizione |
|---|---|
| **Anti-profiling** | progettato per impedire la **profilazione** per razza, età, genere o origine nazionale |
| **Encryption** | immagini **cifrate** a riposo e in trasmissione; i template non si possono riconvertire in immagini in caso di data breach |
| **Approval process** (rilevante per il GDPR) | l'enrollment **provvisorio** richiede l'approvazione di livelli di gestione adeguati per l'inserimento in una **watchlist** e crea un **audit trail** contro gli usi non autorizzati |
| **Data purging** | le immagini delle watchlist si possono **cancellare** secondo una tempistica rigorosa |

---

### **14. Bias, fairness e leakage informativo**

_(slide pp. 89–92)_

#### **14.1 Bias nei modelli pre-addestrati**

Non sono ancora diffusi **modelli fondazionali «biometrici puri»**: si usano **foundation model generalisti** riadattati ai compiti biometrici con **fine-tuning, prompting o adapter** (p. 89). Tra i primi riallenati per la biometria la slide cita:

- **Meta**: DeepFace, DINOv2;
- **Google**: FaceNet;
- **Microsoft**: ArcFace (linea MS-Celeb-1M).

Un sistema che li usa può **ereditare i bias** dei dati di pre-training, con **prestazioni diverse tra gruppi demografici**: è un **rischio concreto** nel deployment.

**Bias osservati** (p. 90):

| Modello | Bias (slide) |
|---|---|
| **FaceNet** | **FMR più alto** per donne, pelle scura, giovani e anziani estremi |
| **ArcFace** | bias demografico **sistemico** per etnia, genere ed età; migliori prestazioni sulle popolazioni **sovrarappresentate** |
| **DeepFace / DINOv2** | tende a **raggruppare** per etnia, genere ed età: le distanze cambiano secondo il cluster in cui ci si trova |
| **VGG128** (così nella slide) e **FaceNet** | sbagliano molto di più su **asiatici e africani** che su **caucasici** |

> ⚠️ **Attribuzioni da precisare.** **ArcFace** (Deng et al., 2019) non è un modello Microsoft: nasce da Imperial College e InsightFace ed è stato addestrato anche su **MS-Celeb-1M**, un dataset Microsoft (da qui la «linea» citata dalla slide). **DINOv2** è un modello di visione **auto-supervisionato generalista**, non specifico per i volti.

> 💡 **Collegamento con la Lezione 10.** Un FMR più alto per un gruppo significa che, **a parità di soglia**, due persone diverse di quel gruppo vengono confuse più spesso. Per questo il testing va fatto **per sottogruppo demografico**, non solo sull'intero dataset.

<!-- SOURCE_VISUAL id="TAB13-V36" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="90" type="grafici" description="Esempi di bias demografici osservati in FaceNet, ArcFace, DeepFace/DINOv2 e VGG" reason="Grafici dei bias per gruppo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **14.2 Leakage informativo nei vettori biometrici**

> 💡 **La domanda della slide** (p. 91). Se nel file che serve a farti entrare in un'area del tuo dipartimento c'è scritto che «sei in forte sovrappeso e a rischio di infarto», sei contento?

I vettori biometrici possono nascondere informazioni in modo **meno esplicito**. Il **leakage informativo**:

- il template contiene **più informazioni del necessario**;
- informazioni **non richieste né autorizzate**;
- **difficili da controllare** a posteriori;
- rischio per **privacy e sicurezza**.

> 📌 **Principio fondamentale** (p. 92). «Un buon template biometrico deve essere **utile** per il matching o la misurazione richiesta, ma **inutile per qualsiasi altra cosa**.»

Le reti restituiscono vettori che **sembrano casuali e anonimi, ma non lo sono**. Da essi non si dovrebbero poter estrarre informazioni **non autorizzate** (genere, orientamenti, predisposizioni a malattie, profilazione). È **vero in teoria, difficile da evitare in pratica**: la slide mostra un vettore in cui una componente codifica «autorizzato» e un'altra, nascosta, l'**ipertensione**.

---

### **15. Privacy nei social media e de-identificazione**

_(slide pp. 93–100)_

#### **15.1 Il caso Sharbat Gula**

La domanda (p. 93): **quanto è preoccupante** la diffusione dei nostri dati biometrici nei social media?

**Il caso** (p. 94, note di John Daugman). **Steve McCurry**, in Afghanistan per documentare la guerra civile, scatta alcune foto in una scuola femminile del **campo profughi di Nasir Bagh** (Pakistan), al confine. Nel **2002** la ragazza della celebre copertina viene ritrovata e identificata con il **riconoscimento dell'iride** (Lezione 4).

**Problemi di privacy? Sì** (p. 95). L'impresa di Daugman, riconoscimento dell'iride **da foto ad alta risoluzione nel visibile** a **18 anni** di distanza, mostra il pericolo di **screening di massa** degli archivi fotografici (governativi, social…). I fattori chiave:

- **segmentazione ed estrazione dell'IrisCode nel visibile** sempre migliori;
- **ottiche dedicate**, risoluzione crescente e **fuoco perfetto sul volto** (i selfie);
- IrisCode con **FMR anche di $10^{-7}$**.

Da qui un **enorme problema di privacy nel futuro**.

> ⚠️ **Refuso sulla data.** La p. 95 riporta «**1885**»; la data corretta è quella della p. 94, **1985**, anno della copertina (la foto fu scattata nel 1984, da cui i 18 anni fino al 2002). Lo stesso refuso compare nella Lezione 4.

**Possibili soluzioni** (p. 96): **iride sintetica** generata da una CNN, **iride linearizzata** (conversione cartesiana→polare e segmentazione), applicazione di un **face morpher**.

<!-- SOURCE_VISUAL id="TAB13-V37" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="94" type="fotografie" description="Sharbat Gula nel 1985 e nel 2002, riconoscimento con l'iride" reason="Confronto fotografico del caso di studio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V38" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="96" type="immagini" description="Possibili soluzioni: iride sintetica deep, iride linearizzata, face morpher" reason="Esempi visivi delle soluzioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **15.2 Quando sono gli utenti stessi**

**Facebook 10 Year Challenge** (p. 97): sfida virale del **2019** in cui milioni di utenti hanno pubblicato una foto di **dieci anni prima** e una **attuale**, spesso con **informazioni temporali esplicite**.

- Sono sorti dubbi che potesse costituire un dataset ideale per addestrare algoritmi sull'**invecchiamento del volto** (*age progression*).
- Tecnicamente equivale a un **dataset etichettato ad alta qualità**, **senza costi** di acquisizione.
- Facebook (oggi Meta) ha **negato** ogni coinvolgimento diretto.
- Solo una legislazione di tutela come il **GDPR** protegge gli utenti «incauti».

#### **15.3 De-identificazione**

**Rimozione delle PII** (pp. 98–99). Per aziende e nuove applicazioni servono dati, immagini e video, ma nel rispetto delle norme (GDPR). Esistono software che **rimuovono le Personally Identifiable Information** da video e immagini.

Esempio: **D-ID** (*smart video anonymization*) permette di usare **video registrati in pubblico** per analisi di intelligenza artificiale. Con visione artificiale e deep learning rimuove le PII **preservando attributi** come **età, genere, emozione e direzione dello sguardo**.

> 💡 **Il limite concettuale.** De-identificare significa impedire il **riconoscimento dell'identità**, non eliminare ogni informazione: età, genere ed emozioni restano e sono proprio le soft biometrics e le inferenze discusse nei §§14 e 16.

**Confronto degli scenari** (p. 100): la slide confronta le **caratteristiche dei sistemi biometrici** in scenari eterogenei rispetto al rischio privacy.

<!-- SOURCE_VISUAL id="TAB13-V39" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="99" type="sequenza di immagini" description="Smart video anonymization: rimozione delle PII preservando età, genere, emozione e sguardo" reason="Esempio visivo della de-identificazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="TAB13-V40" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="100" type="tabella grafica" description="Comparazione delle caratteristiche dei sistemi biometrici in scenari eterogenei rispetto alla privacy" reason="Tabella presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **16. Inferenze comportamentali e soft biometrics**

_(slide pp. 101–103)_

#### **16.1 Inferire lo stato interno**

Molti sistemi possono ormai **dedurre** informazioni su comportamento, stato cognitivo o emotivo, **anche quando i dati non nascono per scopi biometrici** (p. 101). Possono stimare:

- attenzione e distrazione;
- affaticamento;
- stress operativo;
- calo cognitivo;
- emozioni;
- micro-espressioni o posture anomale;
- pattern comportamentali ricorrenti.

> 📌 **Il punto critico.** Se queste inferenze sono **riconducibili a un individuo identificato**, diventano **informazioni sensibili** e possono essere classificate come una forma di **biometria comportamentale**, esplicita o implicita. Esempi della slide: «La tua produttività è scesa oggi!», «Il tuo cliente non era contento!», «Tu non sorridevi al cliente!».

#### **16.2 Il caso degli uiguri**

**Contesto** (p. 102). Gli **uiguri** sono un'etnia turcofona di religione islamica nel nord-ovest della Cina.

- Il **18 novembre 2019** il New York Times ha pubblicato **oltre 400 pagine** di documenti riservati sulla stretta del governo cinese contro le minoranze musulmane dello **Xinjiang**, rinchiuse in campi di prigionia o carceri.
- Secondo le fonti citate, la Cina è accusata di aver imprigionato **1,8 milioni** di uiguri, kazaki e kirghisi.
- **43 nazioni** si sono unite alla condanna presso le Nazioni Unite.
- La Cina **nega** le accuse, parlando di **centri di formazione professionale** e progetti contro povertà, terrorismo e separatismo.

**Il ruolo della tecnologia** (p. 103):

- **SenseTime**, la più grande startup cinese di riconoscimento facciale, sostiene di saper **«classificare gli uiguri»** ed è sotto crescente scrutinio del governo statunitense;
- a **luglio 2019** SenseTime ha depositato un **brevetto** per una funzione di riconoscimento facciale in grado, tra l'altro, di **distinguere** chi è di etnia uigura da chi non lo è (fonte: The Guardian, 2022).

> ⚠️ **La lezione della slide.** Persino dietro un **«semplice» tratto di soft biometrics** possono celarsi **enormi problemi di privacy**. È esattamente il tipo di categorizzazione per origine etnica che l'AI Act oggi **vieta** (§5.2).

---

### **17. La situazione italiana**

_(slide pp. 104–106)_

- **Moratoria terminata** (p. 104). La moratoria che vietava in Italia l'installazione e l'uso di sistemi di **riconoscimento facciale** con dati biometrici, salvo indagini della magistratura o prevenzione e repressione dei reati, **è terminata il 31/12/2025**. Nasceva per disciplinare requisiti, condizioni e garanzie nel rispetto della **proporzionalità**, ma nel frattempo **non** sono stati forniti casi d'uso e indicazioni generali (fonte: Federprivacy).
- **Nuovo Decreto Sicurezza, febbraio 2026** (p. 105). Il Governo ha approvato norme che consentono il riconoscimento facciale **«a posteriori»**, cioè sull'analisi delle registrazioni dopo un fatto, in contesti specifici come gli **stadi**, per identificare autori di reati o violenze.
  - Il volto di chi va allo stadio viene rilevato e finisce in un **database**, consultabile se vengono commessi reati.
  - Vige il **no concealing**: non si entra con cappucci, sciarpe o passamontagna che nascondano il volto.
  - Tutti gli stadi di **serie A** dovrebbero avere **una telecamera per ogni tornello**.
- **La posizione del Garante Privacy** (p. 106).
  - **Settore privato** (negozi, hotel, aziende): il riconoscimento facciale resta **di fatto vietato** nella quasi totalità dei casi. È difficilissimo ottenere un **consenso libero e informato** in contesti aperti al pubblico o con **squilibrio di potere**, e il trattamento non è in genere **necessario né proporzionato** rispetto a soluzioni meno invasive.
  - **Trasporto pubblico**: nel **febbraio 2026** il Garante ha **bloccato** sistemi di *facial scanning* usati in aeroporto al posto del biglietto, per l'assenza di garanzie tecniche adeguate (in particolare sulla **cifratura**) e di un **controllo effettivo e continuativo** dell'utente sul proprio dato. Il tema torna nella lezione su ABC e aree ad elevata sicurezza (Lezione 14).

> 💡 **Leggerlo con la tabella del §3.1.** Il riconoscimento «a posteriori» negli stadi è **obbligatorio** per chi entra, **pubblico**, basato su **database** e rivolto a **cittadini**: tutte risposte a rischio maggiore. La scelta del «a posteriori» invece del tempo reale e la limitazione a contesti specifici sono le misure di **proporzionalità**.

---

### **18. Segnali fisiologici, «heart biometrics» e sharenting**

_(slide pp. 107–109)_

**Heart biometrics** (p. 107). Molti dispositivi **consumer largamente diffusi**, come gli anelli **Oura**, leggono **segnali fisiologici**, specialmente **cardiaci**. Con essi il tuo **stato di salute** è esposto e può essere **trasmesso o diagnosticato**.

> 💡 **La vignetta** (p. 108). Un'assicurazione comunica che il premio aumenterà del **75%** perché il cliente «non è in forma come aveva dichiarato». Alla domanda «E chi ve lo ha detto?», la risposta è: «Il suo X watch…». È il leakage informativo del §14.2 applicato ai dati fisiologici.

**Raccomandazioni di buon senso e sharenting** (p. 109). Lo spot di Deutsche Telekom **«Share with care»**, particolarmente efficace e quasi disturbante, riguarda la privacy dei dati dei **minori condivisi dai parenti** (*sharenting*).

<!-- SOURCE_VISUAL id="TAB13-V41" source="Lezione_13_Difesa_della_Privacy_Crittazione.pdf" page="107" type="fotografia prodotto" description="Dispositivi consumer per segnali fisiologici e cardiaci (anello Oura)" reason="Esempio di dispositivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **19. Conclusioni**

_(slide pp. 110–111)_

_(dispensa p. 15)_

**Sommario** (p. 110): difesa della privacy e linee guida; biometria cancellabile; sample o template e ricostruzione del sample; crittazione biometrica; de-identificazione; privacy nei social media; rischi della heart biometrics.

**Considerazioni finali** (p. 111):

- **La protezione a livello di template**:
  - è un **buon metodo** per migliorare la privacy, usabile e fattibile;
  - richiede **metodi più complessi** degli algoritmi biometrici tradizionali;
  - può **abbassare l'accuratezza** del sistema tradizionale corrispondente;
  - **purtroppo non viene quasi mai impiegata**, né nelle applicazioni commerciali né in quelle governative;
  - di solito si preferiscono **tecniche crittografiche tradizionali** per proteggere il **canale** e la **memorizzazione** dei template.
- **L'elaborazione nel dominio crittato**:
  - è **promettente e applicabile**;
  - oggi funziona solo per **metodi di matching semplici** (fino ad alcune CNN), non per funzioni matematiche non lineari.

**Conclusioni della dispensa** (p. 15). Rischi percepiti e reali **differiscono**: i primi, difficili da valutare, riguardano furti d'identità e usi impropri; i secondi dipendono dal **contesto** (memorizzazione, proprietario, tratti, scelte di progetto). Le tre famiglie di protezione del template aumentano la sicurezza, ma con costi diversi:

| Famiglia | Costo principale |
|---|---|
| biometria cancellabile | le trasformazioni possono **ridurre l'accuratezza** |
| crittosistemi biometrici | non si possono usare **i matcher migliori** |
| metodi crittograficamente sicuri | algoritmi **costosi** e possibile **riduzione dei dati** per velocizzare |

---

### **20. Bibliografia della dispensa**

_(dispensa pp. 16–18)_

La dispensa riporta **56 riferimenti**. I più rilevanti per gli argomenti della lezione:

| Tema | Riferimenti |
|---|---|
| Punti di attacco | Ratha, Connell, Bolle (2001); Faundez-Zanuy (2004) |
| Sicurezza dei template (rassegne) | Jain, Nandakumar, Nagar, *Biometric template security* (2008); Rathgeb, Uhl, *A survey on biometric cryptosystems and cancelable biometrics* (2011) |
| Biometria cancellabile | Ratha et al., *Generating cancelable fingerprint templates* (2007); Jin, Ling, Goh, *BioHashing* (2004); Boult, biotoken revocabili (2006–2007) |
| Crittosistemi biometrici | Juels, Wattenberg, *fuzzy commitment* (1999); Juels, Sudan, *fuzzy vault* (2002); Dodis, Ostrovsky, Reyzin, Smith, *fuzzy extractors* (2008); Nandakumar, Jain, Pankanti, *fuzzy vault* per impronte (2007) |
| Schema iride destra + sinistra | Cimato, Gamassi, Piuri, Sassi, Scotti, *A biometric verification system addressing privacy concerns* (2007) |
| Dominio cifrato | Barni et al., FingerCode con cifratura omomorfa (2010); Erkin et al., *Privacy-preserving face recognition* (2009); Paillier (1999); ElGamal (1985) |
| Linee guida | International Biometric Group, *Bioprivacy Initiative* (2003) |

---

### **21. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Sicurezza** / **privacy** | autenticazione, integrità, confidenzialità, non ripudio / in più la protezione e il controllo d'uso dei dati |
| **Rischio percepito** / **reale** | furto d'identità, tracciamento, salute / memorizzazione, proprietario, modalità, tratto |
| **Privacy-protective** / **privacy-sympathetic** | la biometria protegge altri dati / il sistema protegge i dati biometrici |
| **Privacy-neutral** / **privacy-invasive** | impatto lieve, uso volontario locale / sorveglianza, uso senza consenso o oltre lo scopo |
| **Verifica** / **identificazione** | 1:1, rischio minore / 1:N su database, rischio maggiore |
| **Template** / **sample** | informazione ridotta, ma ricostruibile in parte / dato grezzo, rischio maggiore |
| **Salting** / **non invertibile** | invertibile, chiave segreta / one-way, chiave anche pubblica |
| **Biometria cancellabile** / **crittosistema biometrico** | stesso spazio delle feature e stesso matcher / chiave legata o generata, matcher appositi |
| **Key binding** / **key generation** | chiave esterna legata al template / chiave derivata solo dal template |
| **Fuzzy commitment** / **fuzzy vault** | vettore di lunghezza fissa più parola di codice / insieme non ordinato che chiude un polinomio |
| **Secure sketch** / **fuzzy extractor** | tolleranza agli errori, ricostruisce $r$ / in più una stringa uniforme $R$ usabile come chiave |
| **Crittosistema biometrico** / **dominio crittato** | matching indiretto sulla chiave / matching diretto sui dati cifrati con crittografia omomorfa |
| **Categorizzazione proibita** / **ad alto rischio** (AI Act) | attributi sensibili dedotti o inferiti / attributi evidenti o classificazioni funzionali, regolati |
| **Riconoscimento in tempo reale** / **a posteriori** | durante l'evento / sulle registrazioni dopo un fatto |
| **Anonimizzazione** / **eliminazione dell'informazione** | impedire l'identificazione / età, genere ed emozioni possono restare |

---

### **22. Sintesi della lezione**

> ✅ **In sintesi.**
> - **Privacy e sicurezza**: i tratti biometrici sono unici e **irrevocabili**; la privacy è più restrittiva della sicurezza; rischi percepiti e reali differiscono; otto punti di attacco e quattro classi di contromisure.
> - **Valutazione del rischio**: quattro livelli (protective, sympathetic, neutral, invasive); dieci domande IBG (otto nelle slide) su palese/nascosto, facoltativo/obbligatorio, verifica/identificazione, durata, settore, ruolo, proprietà, memorizzazione, tipo di tratto, template/sample; rischio per tratto (volto e impronta alti).
> - **Linee guida e norme**: scopo dichiarato, controllo dell'utente, trasparenza e audit, protezione dei dati; GDPR (privacy by design, 72 ore); AI Act con divieto di categorizzazione per attributi sensibili e di emotion recognition al lavoro e a scuola; situazione italiana nel 2026.
> - **Protezione del template**: i soli template non bastano (standard ISO, ricostruzione del sample, niente hash come per le password); quattro proprietà (diversità, revocabilità, sicurezza, prestazioni).
> - **Biometria cancellabile**: salting invertibile contro trasformazioni many-to-one; permutazioni dell'IrisCode che conservano la distanza di Hamming ma impediscono gli shift.
> - **Crittosistemi biometrici**: helper data, key binding (fuzzy commitment, fuzzy vault) e key generation (secure sketch, fuzzy extractor, PinSketch); schema iride destra + sinistra con EER 0,96% e privacy migliorata.
> - **Dominio crittato**: crittografia omomorfa, accuratezza quasi invariata ma solo matcher semplici e costo elevato.
> - **Rischi emergenti**: bias dei modelli pre-addestrati, leakage nei vettori, Sharbat Gula e screening di massa, social media, de-identificazione, inferenze comportamentali, caso uiguri, heart biometrics.
