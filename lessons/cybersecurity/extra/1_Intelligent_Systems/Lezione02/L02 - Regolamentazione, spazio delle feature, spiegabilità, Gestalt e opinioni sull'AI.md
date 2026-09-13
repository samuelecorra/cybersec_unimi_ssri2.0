## **Lezione 2: Regolamentazione, spazio delle feature, spiegabilità, Gestalt e opinioni sull’AI**

La seconda lezione del corso **IS4** affianca due prospettive. Da un lato il **contesto**: le regole europee sull’intelligenza artificiale, le opinioni di pubblico ed esperti, la maturità delle tecnologie. Dall’altro la **teoria**: che cosa fa davvero una rete addestrata, perché piccole perturbazioni dell’input possono ingannarla, perché è difficile spiegarne le decisioni e quali capacità del cervello umano, come la **Gestalt**, restano lontane dai modelli attuali.

Fonte: [PDF della Lezione 02](Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf), 84 pagine, nessun supplemento. Date, prodotti e sondaggi descrivono la versione del materiale acquisita; le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md) · [Lezione 01](../Lezione01/L01%20-%20Introduzione%20ai%20sistemi%20intelligenti.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

L’indice della lezione (p. 2) elenca:

1. **teoria**: la legislazione sull’AI; una rete addestrata come funzione non lineare «e basta»; spazio di input e spazio interno; Gestalt e limiti attuali del machine learning; modelli di intelligenza computazionale; **Explainable Artificial Intelligence** (XAI);
2. **panoramica** dei metodi di AI e ML;
3. **opinioni** rilevanti sull’AI;
4. i **quattro driver** principali dell’AI;
5. punti principali.

---

### **1. La regolamentazione europea dell’AI**

#### **1.1. L’AI Act**

_(slide pp. 3–4)_

L’AI è usata in quasi ogni campo applicativo, ma la legislazione **sembrava non ancora pronta** (p. 3). La risposta europea è l’**AI Act** (p. 4):

- è la **prima legge organica** sull’intelligenza artificiale adottata da un grande regolatore;
- i **27 Stati membri** dell’UE l’hanno approvata all’unanimità;
- è stata pubblicata nella Gazzetta Ufficiale dell’UE il **12 luglio 2024** ed è **entrata in vigore il 1° agosto 2024**.

#### **1.2. Perché servono regole**

_(slide pp. 5–8)_

Le slide p. 5 e p. 6 introducono la classificazione per livelli di rischio e annunciano **nuovi obblighi**: nel corso si incontreranno anche argomenti e strumenti che servono a rispettarli.

<!-- SOURCE_VISUAL id="IS02-V01" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="5" type="schema" description="Piramide dei livelli di rischio dell’AI Act, dal rischio inaccettabile al rischio minimo" reason="Schema dei livelli presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V02" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="6" type="infografica" description="Nuovi obblighi in arrivo con l’AI Act" reason="Pagina con sola infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Perché regolare** (p. 7):

- per **evitare esiti indesiderati**;
- spesso **non è possibile capire perché** un sistema di AI abbia preso una decisione, fatto una previsione o compiuto un’azione;
- diventa quindi difficile stabilire se qualcuno sia stato **svantaggiato ingiustamente**, per esempio in una selezione del personale o in una domanda di accesso a un beneficio pubblico.

Esempi citati: una donna morta in Arizona dopo essere stata investita da un’auto a guida autonoma di **Uber** a Tempe (fonte CNN, 2018); **bot finanziari** che comprano e vendono azioni; un’AI che decide se concedere un **prestito**; il calcolo del costo di un’**assicurazione** con l’AI.

> 💡 **Il filo con il resto della lezione.** Il secondo motivo, «non si sa perché il sistema ha deciso», è esattamente il problema della spiegabilità del §5. Le regole e le tecniche XAI rispondono alla stessa mancanza.

**Che cosa è «intelligente» per la legge** (p. 8). Secondo l’AI Act un **sistema di AI** è un sistema basato su macchina, progettato per operare con **diversi livelli di autonomia**, che, per obiettivi espliciti o impliciti, **deduce dall’input** che riceve come generare **output** quali previsioni, contenuti, raccomandazioni o decisioni in grado di **influenzare ambienti fisici o virtuali**.

> ⚠️ **Citazione abbreviata.** Il testo della slide salta un passaggio della definizione dell’art. 3 del Regolamento (UE) 2024/1689, che dice anche che il sistema «può presentare **adattabilità dopo la diffusione**». Per questo la frase della slide risulta sintatticamente incompleta («may exhibit, for explicit or implicit objectives, infers…»).

> 💡 **Una definizione funzionale.** La legge non definisce l’AI con una tecnica (reti neurali, alberi di decisione…) ma con ciò che il sistema **fa**: ricavare output da input con un certo grado di autonomia. È la stessa impostazione operativa della Lezione 01, §1.1.

#### **1.3. I quattro livelli di rischio**

_(slide pp. 9–12)_

| Livello | Che cosa comprende (slide) | Conseguenza |
|---|---|---|
| **Rischio inaccettabile** (p. 9) | sistemi considerati una **chiara minaccia** per sicurezza, mezzi di sussistenza e diritti delle persone: dal **social scoring** da parte dei governi ai **giocattoli con assistente vocale** che incoraggiano comportamenti pericolosi | **vietati** |
| **Rischio alto** (p. 11) | infrastrutture critiche (trasporti); istruzione e formazione professionale (valutazione degli esami); componenti di sicurezza di prodotti (chirurgia assistita da robot); lavoro e gestione dei lavoratori (software di selezione dei CV); servizi essenziali pubblici e privati (credit scoring che nega un prestito); forze dell’ordine (valutazione dell’affidabilità delle prove); migrazione, asilo e frontiere (verifica dei documenti di viaggio) | **obblighi rigorosi** prima dell’immissione sul mercato |
| **Rischio limitato** (p. 12) | sistemi con **obblighi di trasparenza**: con un **chatbot** l’utente deve sapere che interagisce con una macchina, per decidere consapevolmente se continuare | trasparenza |
| **Rischio minimo o nullo** (p. 12) | videogiochi con AI, **filtri antispam**; la **grande maggioranza** dei sistemi in uso nell’UE | nessun obbligo specifico |

Per il rischio inaccettabile la slide invita a considerare gli esiti «cattivi» e pericolosi dei chatbot documentati in letteratura. L’esempio della p. 10 è un **giocattolo potenziato con ChatGPT** che suggerisce «Usa uno sgabello o una scala!»: un consiglio che, rivolto a un bambino, può diventare un pericolo.

<!-- SOURCE_VISUAL id="IS02-V03" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="10" type="esempio illustrato" description="Giocattolo con ChatGPT che risponde Use a step stool or ladder!" reason="Esempio visivo di esito pericoloso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Il rischio dipende dall’uso, non dalla tecnica.** Lo stesso modello di classificazione può essere a rischio minimo in un filtro antispam e ad alto rischio nella selezione dei CV. Chi progetta un sistema intelligente deve quindi chiedersi prima di tutto **in quale contesto** verrà usato.

#### **1.4. Gestione e obblighi per i sistemi ad alto rischio**

_(slide pp. 13–16)_

**Una volta sul mercato** (p. 13):

- le **autorità** si occupano della sorveglianza del mercato;
- gli **utilizzatori** garantiscono supervisione e monitoraggio umani; i **fornitori** hanno un sistema di **monitoraggio post-commercializzazione**;
- fornitori e utilizzatori **segnalano** incidenti gravi e malfunzionamenti;
- per richieste generali a livello europeo esiste l’**European AI Office**.

**Obblighi per i sistemi ad alto rischio** (p. 14):

| Obbligo | Scopo |
|---|---|
| valutazione e mitigazione dei **rischi** adeguate | prevenire danni |
| **alta qualità dei dataset** che alimentano il sistema | minimizzare rischi ed **esiti discriminatori** |
| **registrazione dell’attività** (logging) | **tracciabilità** dei risultati |
| **documentazione dettagliata** su sistema e scopo | permettere alle autorità di valutarne la conformità |
| **informazioni chiare** all’utilizzatore | uso consapevole |
| misure adeguate di **supervisione umana** | minimizzare il rischio |
| alto livello di **robustezza, sicurezza e accuratezza** | affidabilità |

La p. 15 mostra lo **schema del processo** previsto per i sistemi ad alto rischio.

> 💡 **Il processo nello schema della Commissione (nota didattica).** Lo schema ufficiale della Commissione europea descrive quattro passi: (1) il sistema ad alto rischio viene **sviluppato**; (2) supera la **valutazione di conformità** ai requisiti; (3) i sistemi autonomi vengono **registrati** in una banca dati dell’UE; (4) si firma una **dichiarazione di conformità** e si appone la **marcatura CE**, poi il sistema può essere immesso sul mercato. Se durante il ciclo di vita avvengono **modifiche sostanziali**, si torna al passo 2. Il contenuto preciso della slide va verificato sull’immagine.

<!-- SOURCE_VISUAL id="IS02-V04" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="15" type="diagramma di flusso" description="Workflow di gestione dei sistemi di AI ad alto rischio" reason="Passi del processo presenti solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Collegamento con il corso.** Qualità dei dataset, tracciabilità, documentazione, robustezza e accuratezza non sono solo obblighi legali: sono i temi tecnici di molte lezioni successive (raccolta e armonizzazione dei dati, errori nei dataset, partizione dei dati, valutazione dell’accuratezza).

**Quando** (p. 16). L’AI Act è in vigore dal **1° agosto 2024**, ma l’applicazione è **scaglionata**:

| Categoria | Data di applicazione |
|---|---|
| sistemi **vietati** | dal **2 febbraio 2025** |
| sistemi di AI per **finalità generali** (general-purpose) | obblighi specifici dal **2 agosto 2025** |
| sistemi ad **alto rischio** | conformità richiesta entro il **2 agosto 2026** |

> ⚠️ **Un dettaglio sulle scadenze (nota didattica).** Nel regolamento la data del 2 agosto 2026 riguarda in generale i sistemi ad alto rischio dell’allegato III; per i sistemi ad alto rischio che sono componenti di sicurezza di prodotti già regolati (allegato I) il termine è il **2 agosto 2027**. Eventuali rinvii successivi alla data del materiale vanno verificati sulle fonti ufficiali.

---

### **2. Che cos’è un sistema intelligente**

_(slide pp. 17–24)_

#### **2.1. Sistemi intelligenti, AI e machine learning**

**Sistemi intelligenti artificiali** (p. 18):

- programmi software o sistemi software/hardware progettati per svolgere **compiti complessi** con strategie che **imitano qualche aspetto del pensiero umano**;
- si può discutere all’infinito se un certo sistema sia intelligente o no;
- secondo la slide **la chiave è l’evoluzione**: un sistema è intelligente se **può imparare**, anche solo in senso limitato, e/o **migliorare nel tempo**.

**AI contro ML** (p. 19):

| | Significato |
|---|---|
| **Artificial Intelligence** (AI) | il concetto **più ampio** di macchine capaci di svolgere compiti in un modo che consideriamo «intelligente» |
| **Machine Learning** (ML) | un’**applicazione attuale** dell’AI basata sull’idea di dare alle macchine **accesso ai dati** e lasciarle **imparare da sole** |

La p. 20 mostra le **connessioni** tra AI e discipline collegate; la p. 21 gli **«ingredienti» del ML** (da vas3k.com).

> 💡 **Gli ingredienti (nota didattica).** Nella guida di vas3k, «Machine Learning for Everyone», gli ingredienti di base del ML sono tre: i **dati**, le **feature** (le proprietà con cui si descrive ciascun caso) e l’**algoritmo** di apprendimento. La composizione esatta della slide va verificata sull’immagine.

<!-- SOURCE_VISUAL id="IS02-V05" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="20" type="diagramma" description="Connessioni tra AI, machine learning e discipline collegate" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V06" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="21" type="infografica" description="Ingredienti del machine learning (vas3k.com)" reason="Pagina con sola infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. Intelligenza computazionale**

La p. 22 richiama la definizione di **Computational Intelligence** di **A. P. Engelbrecht** (*Computational Intelligence: An Introduction*, 2006).

> 💡 **La definizione di Engelbrecht (nota didattica).** Nel libro l’intelligenza computazionale è lo studio dei **meccanismi adattivi** che rendono possibile o facilitano un **comportamento intelligente** in ambienti **complessi e mutevoli**; i paradigmi principali sono reti neurali artificiali, calcolo evolutivo, swarm intelligence, sistemi immunitari artificiali e sistemi fuzzy. Il testo della slide non è estraibile e va verificato sull’immagine.

**La definizione della IEEE Computational Intelligence Society** (p. 23): la **teoria, progettazione, applicazione e sviluppo** di paradigmi computazionali **ispirati alla biologia e al linguaggio**, con enfasi su:

- **reti neurali**;
- **sistemi connessionisti**, che rappresentano fenomeni mentali o comportamentali come processi **emergenti** di reti di unità semplici interconnesse;
- **algoritmi genetici**;
- **programmazione evolutiva**;
- **sistemi fuzzy**;
- **sistemi intelligenti ibridi** che contengono questi paradigmi.

La p. 24 presenta una **tassonomia del machine learning**.

<!-- SOURCE_VISUAL id="IS02-V07" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="22" type="definizione" description="Definizione di Computational Intelligence secondo Engelbrecht (2006)" reason="Testo della definizione non estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V08" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="24" type="tassonomia" description="Tassonomia del machine learning" reason="Rami della tassonomia presenti solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Una rete addestrata è una funzione non lineare**

_(slide pp. 25–31)_

#### **3.1. Nessuna magia: solo funzioni**

> 📌 **Modello addestrato come funzione** (p. 26). Un modello di machine learning addestrato è **soltanto una funzione**, in generale **non lineare**:
> $$\text{Out} = F(X)$$
> dove $X$ è il **vettore di input** e $\text{Out}$ il **vettore di output**. L’uscita può essere un **singolo valore** (intero o reale) oppure un **vettore** o una **matrice**.

> 💡 **Che cosa cambia l’addestramento.** Prima dell’addestramento $F$ ha una forma fissata (per esempio una rete con una certa architettura) e parametri qualsiasi. L’addestramento sceglie i parametri in modo che $F$ produca gli output desiderati sugli esempi. Dopo, il modello non «ragiona»: calcola $F(X)$ per ogni nuovo input. Capire questo toglie la «magia» e rende naturali i problemi dei paragrafi successivi.

#### **3.2. Spazio di input e spazio interno delle feature**

Il titolo della parte (p. 27) riassume l’idea: nello spazio delle feature la rete **non «pensa», ma «misura»**.

| Spazio | Descrizione (slide) |
|---|---|
| **Spazio di input** (p. 28) | ogni oggetto (un panda, un volto, un fungo) è un punto descritto da $N$ feature: Feature 1, …, Feature $N$ |
| **Spazio interno delle feature** (p. 29) | la rete trasforma gli oggetti in punti di un altro spazio a $M$ feature, dove prende le decisioni |

In formula (nota didattica): una rete profonda si può vedere come composizione di due parti,

$$X \in \mathbb{R}^N \;\xrightarrow{\;\phi\;}\; Z = \phi(X) \in \mathbb{R}^M \;\xrightarrow{\;g\;}\; \text{Out} = g(Z)$$

dove $\phi$ sono gli strati interni che costruiscono la **rappresentazione** e $g$ l’ultimo stadio che **decide**.

<!-- SOURCE_VISUAL id="IS02-V09" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="28" type="schema" description="Spazio di input a N feature con gli oggetti panda, volto e fungo" reason="Disposizione dei punti nello spazio visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V10" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="29" type="schema" description="Spazio interno delle feature a M dimensioni con gli stessi oggetti" reason="Disposizione dei punti nello spazio visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. Frontiere di decisione**

Nello spazio delle feature la rete divide la regione dei «negozi di scarpe» da quella degli «struzzi» o degli «aspirapolvere» con **frontiere di decisione** (p. 30). La domanda della slide: perché un **negozio di scarpe**, con una **distorsione minima dell’input**, può diventare uno **struzzo** o un **aspirapolvere**? Perché la rete, per esempio un rilevatore visivo di oggetti, è una **funzione non lineare**: le frontiere possono passare **molto vicino** ai punti degli esempi, e un piccolo spostamento dell’input può spingere il punto nella regione sbagliata.

**Chihuahua o muffin?** (p. 31, da Mariya Yao, Metamaven). Il concetto di **somiglianza** del cervello umano **non coincide** con le metriche delle reti profonde; tuttavia si percepisce che muffin e chihuahua sono vicini anche nel **nostro** spazio interno delle feature: colori, forme rotonde e macchie scure li avvicinano.

<!-- SOURCE_VISUAL id="IS02-V11" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="30" type="schema" description="Frontiere di decisione nello spazio delle feature: negozio di scarpe che diventa struzzo o aspirapolvere con una piccola distorsione" reason="Frontiere e spostamenti visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V12" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="31" type="griglia di immagini" description="Chihuahua o muffin: griglia di immagini simili" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Problema: gli attacchi avversari**

_(slide pp. 32–35)_

Anche i modelli neurali profondi **possono essere attaccati** (p. 32). Piccole distorsioni o rumore possono produrre **errori** (p. 33, da I. Goodfellow, NIPS 2016 Workshop on Bayesian Deep Learning):

- si scelgono gli **input peggiori** (*worst case inputs*) da perturbare con **pattern additivi progettati**;
- **non tutte le regioni** dell’input si prestano a creare esempi avversari.

> 📌 **Esempio avversario (nota didattica).** Dato un input $x$ classificato correttamente, un esempio avversario è
> $$x' = x + \eta, \qquad \lVert \eta \rVert \text{ piccola}$$
> tale che il modello classifica $x'$ in modo diverso da $x$, mentre per un essere umano le due immagini sono praticamente uguali. La perturbazione $\eta$ non è rumore casuale: è **progettata** sfruttando la conoscenza del modello.

**Una «vista» migliore** (p. 34): con una rete **AlexNet** «off the shelf», addestrata su **ImageNet** (1,3 milioni di immagini), si parte da un’immagine simile nello spazio interno delle feature e la si modifica fino a cambiarne la classe.

**O anche peggio** (p. 35): con la stessa rete si ottengono immagini che non assomigliano a nulla ma vengono classificate con sicurezza. La domanda del docente: **la si può considerare uno strumento di elaborazione delle immagini sicuro e robusto?** Durante il corso si vedrà come **testare e prevenire** questi esiti indesiderati.

> 💡 **Collegamento con la Lezione 01.** La Lezione 01, §7.1, ha già mostrato pattern artificiali e immagini simili a rumore classificati con alta confidenza. Qui l’accento è sul meccanismo: la rete misura distanze in uno spazio delle feature che non coincide con la percezione umana, e le frontiere di decisione possono essere raggiunte con spostamenti minimi.

<!-- SOURCE_VISUAL id="IS02-V13" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="33" type="esempio" description="Piccole distorsioni che producono errori: input peggiori perturbati con pattern additivi (Goodfellow, NIPS 2016)" reason="Immagini originali e perturbate visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V14" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="34" type="esempio" description="Esempi avversari con AlexNet addestrata su ImageNet partendo da un’immagine simile nello spazio interno" reason="Immagini dell’esempio visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V15" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="35" type="esempio" description="Immagini classificate con sicurezza da AlexNet pur non rappresentando oggetti reali" reason="Immagini dell’esempio visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Problema: la spiegabilità minima**

_(slide pp. 36–51)_

#### **5.1. Reti come scatole nere**

I modelli neurali sono come **«scatole nere»** (p. 36); la p. 37 lo sottolinea con una vignetta («umorismo o verità?»).

**Mancanza di spiegabilità delle grandi reti** (p. 38):

| Modello | Accuratezza | Spiegabilità |
|---|---|---|
| **rete neurale** | tipicamente **alta** | **non interpretabile**: perché l’output è «gatto»? |
| **albero di decisione** | **minore** | la decisione **si può spiegare** |

<!-- SOURCE_VISUAL id="IS02-V16" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="37" type="vignetta" description="Vignetta sulle reti neurali come scatole nere" reason="Pagina con sola vignetta" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V17" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="38" type="confronto" description="Rete neurale accurata ma non interpretabile (perché gatto?) contro albero di decisione spiegabile" reason="Confronto grafico dei due modelli" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.2. Explainable Artificial Intelligence**

> 📌 **XAI** (p. 39). Un insieme di **processi e metodi** che permette agli utenti umani di:
> - **comprendere** e **fidarsi** dei risultati prodotti dagli algoritmi di machine learning;
> - **descrivere** il modello, il suo **impatto atteso** e i **possibili bias**.
>
> La XAI aiuta a caratterizzare, nelle decisioni basate sull’AI: **accuratezza** del modello, **equità** (*fairness*), **trasparenza**, **esiti**.

**Esempio: rilevatore di allerta tornado** (p. 40):

| | Output |
|---|---|
| **albero di decisione classico** | `Watch = 1` |
| **output XAI** | «È stata prevista un’allerta tornado nella contea di Fairfax, con una **confidenza del 75%** e una durata minima e massima di **1–2 ore**. La previsione si basa su **dati storici con caratteristiche simili**, come frequenza storica, temperatura, velocità del vento e letture del barometro.» |

> 💡 **Che cosa aggiunge l’output XAI.** Non cambia la decisione (`Watch = 1`), ma aggiunge **confidenza**, **portata** della previsione e **ragioni**: quali feature hanno contato. È la differenza tra un sistema che si può solo eseguire e uno che si può **controllare**.

<!-- SOURCE_VISUAL id="IS02-V18" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="40" type="schema" description="Rilevatore di allerta tornado: albero di decisione con Watch = 1 contro spiegazione XAI" reason="Schema del confronto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3. Interpretabilità e spiegabilità**

_(slide pp. 41–44)_

| | **Interpretabilità** | **Spiegabilità** |
|---|---|---|
| Definizione (pp. 41–42) | il grado in cui un osservatore **comprende la causa** di una decisione; il **tasso di successo** con cui un umano **prevede** l’output dell’AI; se un modello «ha senso» per l’utente | va **un passo oltre** e guarda **come** l’AI è arrivata al risultato |
| Oggetto | la relazione **input/output**: dato $X$, prevedo $y$ | l’interno della «scatola»: il **meccanismo** del modello |

**Il compromesso interpretabilità-accuratezza** (p. 43): i modelli più interpretabili tendono a essere meno accurati e viceversa. La p. 44 (slide di Yi Mei) confronta i modelli spiegabili «di ieri» e «di oggi».

> 💡 **Come leggere il compromesso.** Una regressione lineare o un albero poco profondo si leggono direttamente, ma possono non catturare relazioni complesse. Una rete profonda cattura relazioni molto complesse, ma i suoi milioni di parametri non hanno un significato leggibile. La XAI cerca di spostare questa frontiera: mantenere l’accuratezza del modello complesso aggiungendo spiegazioni.

<!-- SOURCE_VISUAL id="IS02-V19" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="43" type="grafico" description="Compromesso interpretabilità-accuratezza tra famiglie di modelli" reason="Posizione dei modelli nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V20" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="44" type="schema" description="Modelli spiegabili di ieri e di oggi (slide di Yi Mei)" reason="Confronto presente solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.4. Funzionalità desiderabili e un esempio reale**

**Funzionalità desiderabili della XAI** (p. 45):

| Funzionalità | Che cosa fa |
|---|---|
| **fairness e debiasing** | gestire e monitorare l’equità; **analizzare** il sistema in esercizio alla ricerca di **bias** |
| **mitigazione del model drift** | analizzare il modello e dare raccomandazioni sull’esito più logico; **allertare** quando il modello si allontana dagli esiti previsti |
| **gestione del rischio del modello** | **quantificare** e mitigare il rischio; allertare quando il modello funziona in modo inadeguato; capire che cosa è successo quando le deviazioni **persistono** |

> 💡 **Model drift (nota didattica).** Un modello è addestrato su dati di un certo periodo; se nel tempo cambiano i dati in ingresso (per esempio le abitudini dei clienti) o la relazione tra input e output, le sue prestazioni peggiorano senza che nessuno modifichi il modello. Monitorare il drift significa accorgersene prima che le decisioni sbagliate si accumulino.

**Dashboard per l’approvazione di prestiti** (pp. 46–51, IBM Watson Explainable AI):

- una **spiegazione testuale** della decisione;
- la classe predetta, **Risk** (prestito non concesso) o **No Risk** (prestito concesso), con la sua **confidenza**;
- **quali feature e quanto** hanno contribuito alla decisione di rischio (p. 48) o di assenza di rischio (p. 49);
- la **minima modifica** degli input che **ribalterebbe** l’output;
- la **massima modifica** che **manterrebbe** lo stesso output.

> 📌 **Spiegazioni controfattuali.** «La minima modifica che ribalta la decisione» è una **spiegazione controfattuale**: dice al cliente che cosa dovrebbe cambiare (per esempio il reddito o la durata del prestito) per ottenere l’esito opposto. «La massima modifica che mantiene l’output» misura invece la **stabilità** della decisione: se basta pochissimo per ribaltarla, la decisione è fragile.

<!-- SOURCE_VISUAL id="IS02-V21" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="46" type="schermata" description="Dashboard IBM di approvazione prestiti con spiegazione testuale, minima modifica per ribaltare e massima modifica per mantenere l’output" reason="Elementi della dashboard indicati sulla schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V22" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="48" type="schermata" description="Contributo delle feature alla predizione Risk con confidenza" reason="Valori delle feature presenti solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V23" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="49" type="schermata" description="Contributo delle feature alla predizione No Risk con confidenza" reason="Valori delle feature presenti solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. La Gestalt umana**

_(slide pp. 52–62)_

#### **6.1. Il tutto è più della somma delle parti**

Perché siamo ancora **lontani dal cervello umano** (p. 52)?

> 📌 **Gestalt** (p. 53). Termine tedesco che significa all’incirca **schema** o **forma**, introdotto da **Christian von Ehrenfels** (filosofo austriaco, 1859–1932):
> - «il **tutto** è **più della somma delle sue parti**»;
> - «apprendere è **più che suscitare risposte meccaniche**»;
> - **esperienze e percezioni** di chi apprende influenzano molto il modo in cui apprende.
>
> Questa caratteristica del cervello umano è **difficile da imitare** con i modelli di AI attuali.

#### **6.2. Figura e sfondo**

Una parte dell’immagine può essere **«solo rumore»** per un pattern oppure la **feature più saliente** per un altro (pp. 54–55). Esempi classici:

| Immagine | Letture possibili |
|---|---|
| vaso di Rubin | **vaso** oppure due **volti** di profilo |
| immagine composta | **scimmia**, **leone** oppure **albero** |
| immagine composta | **sassofono**, **volto** oppure **uomo che suona il sassofono** |

> 💡 **Perché è difficile per una rete.** Un classificatore assegna a ogni pixel un peso fisso rispetto a una classe. Il cervello, invece, **riorganizza** la stessa scena: ciò che era sfondo diventa figura. Non esiste una sola risposta «giusta»; conta l’organizzazione globale.

<!-- SOURCE_VISUAL id="IS02-V24" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="55" type="illusioni figura-sfondo" description="Vaso e volti, scimmia/leone/albero, sassofono/volto/uomo che suona" reason="Esempi visivi di figura e sfondo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.3. Leggi dell’organizzazione percettiva**

La p. 56 mostra esempi di **leggi dell’organizzazione percettiva**.

| Legge | Contenuto (slide) |
|---|---|
| **Prägnanz** («buona forma», p. 57) | gli oggetti vengono percepiti nel modo **più semplice possibile**: si vede una serie di **cerchi sovrapposti** invece di un insieme di linee curve collegate |
| **Destino comune** (*common fate*, pp. 58–59) | gli elementi che si muovono con la **stessa velocità e/o direzione** vengono percepiti come parti di **un unico stimolo** |

<!-- SOURCE_VISUAL id="IS02-V25" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="56" type="illustrazioni" description="Esempi delle leggi di organizzazione percettiva della Gestalt" reason="Pagina con sole illustrazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V26" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="57" type="illustrazione" description="Legge di Prägnanz: cerchi sovrapposti percepiti come forme semplici" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V27" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="59" type="illustrazione" description="Destino comune: elementi con la stessa direzione percepiti come un unico stimolo" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **6.4. Mettere alla prova ChatGPT-4**

_(slide pp. 60–62)_

Il docente sottopone a **ChatGPT-4** due immagini Gestalt (dopo un’ulteriore immagine di esempio a p. 60).

| Test | Risposta del modello | Esito |
|---|---|---|
| **destino comune + figura/sfondo** (p. 61) | descrive **figure stilizzate ripetute** in sequenza, che suggeriscono movimento o progressione, su uno **zigzag** che potrebbe rappresentare montagne o un grafico, forse un elemento di identità visiva sul tema della crescita o del lavoro di squadra | **mancato**: invertendo lo sfondo, le figure sono **frecce** |
| **Prägnanz** («buona forma», p. 62) | riconosce la forma | **«Nice job!»** |

> 💡 **Che cosa mostra il test.** Il modello descrive molto bene le **parti** (sagome, zigzag, ritmo), ma non esegue la **riorganizzazione figura-sfondo** che fa emergere le frecce. È un esempio concreto del limite del §6.1: descrivere le parti non equivale a percepire il tutto. Il risultato riguarda la versione del modello usata dal docente.

<!-- SOURCE_VISUAL id="IS02-V28" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="61" type="schermata" description="Risposta di ChatGPT-4 su un’immagine con destino comune e figura/sfondo, e immagine con sfondo invertito che rivela frecce" reason="Immagini del test visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V29" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="62" type="schermata" description="ChatGPT-4 riconosce correttamente un esempio della legge di Prägnanz" reason="Immagine del test visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Opinioni sull’AI**

_(slide pp. 63–75)_

#### **7.1. Il pubblico: il sondaggio britannico**

Dati recenti (p. 64): l’**Ada Lovelace Institute**, istituto di ricerca indipendente con la missione di far lavorare dati e AI a vantaggio di persone e società, con l’**Alan Turing Institute**, ha pubblicato un **sondaggio nazionale rappresentativo** sugli atteggiamenti del pubblico verso l’AI: **novembre 2022**, **4000 adulti** in Gran Bretagna. Le pp. 65–67 ne riportano i grafici.

**Sintesi** (p. 68):

- per la **maggior parte** degli usi dell’AI chiesti, le persone hanno opinioni **nel complesso positive**, ma esprimono **preoccupazioni** su alcuni usi;
- approfondendo, il pubblico britannico ha opinioni **molto sfumate** su vantaggi e svantaggi dei **diversi** usi;
- il pubblico **vuole una regolamentazione** delle tecnologie di AI, anche se questo varia con l’**età**.

<!-- SOURCE_VISUAL id="IS02-V30" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="65" type="grafici" description="Risultati del sondaggio Ada Lovelace e Alan Turing Institute sugli atteggiamenti verso l’AI (1)" reason="Valori presenti solo nei grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V31" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="66" type="grafici" description="Risultati del sondaggio sugli atteggiamenti verso l’AI (2)" reason="Valori presenti solo nei grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V32" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="67" type="grafici" description="Risultati del sondaggio sugli atteggiamenti verso l’AI (3)" reason="Valori presenti solo nei grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Gli esperti**

| Chi | Opinione (slide) |
|---|---|
| **Bill Gates**, Microsoft (p. 69) | «Una svolta nel machine learning varrebbe **dieci Microsoft**» |
| **Tony Tether**, DARPA (p. 69) | «Il machine learning è la **prossima Internet**» |
| **John Hennessy**, Stanford (p. 69) | «Il machine learning è **la novità del momento**» |
| **Stephen Hawking** (p. 70) | l’AI sarà «**la cosa migliore o la peggiore**» per l’umanità; lo sviluppo di un’AI completa potrebbe segnare **la fine della specie umana**: si riprogetterebbe da sola a velocità crescente, e gli umani, limitati dalla lenta evoluzione biologica, **non potrebbero competere** |
| **Larry Page**, Google (p. 71) | l’AI sarebbe **la versione definitiva di Google**, il motore di ricerca che capisce tutto il Web e ciò che vuoi; «siamo lontanissimi», ma ci si avvicina un passo alla volta |
| **Elon Musk** (p. 72, «probabilmente cambiando opinione») | il progresso dell’AI (non quella «ristretta») è **quasi esponenziale**; il rischio di qualcosa di seriamente pericoloso è in un orizzonte di **cinque anni, dieci al massimo**; serve una **supervisione regolatoria** nazionale e internazionale: «con l’intelligenza artificiale **evochiamo il demone**» |
| **Bill Gates**, opinione recente (p. 73) | gli umani **dovrebbero preoccuparsi**; prima le macchine faranno molti lavori senza essere superintelligenti, e sarà positivo se gestito bene; qualche decennio dopo l’intelligenza sarà abbastanza forte da **destare preoccupazione**; «sono d’accordo con Elon Musk». La slide ricorda anche che **Microsoft ha investito 10 miliardi di dollari** in OpenAI (Bloomberg, 2023) |
| **Andrew Ng** (p. 74), informatico con oltre 167.000 citazioni | preoccuparsi oggi di una superintelligenza malvagia è come **preoccuparsi della sovrappopolazione su Marte**: non ci siamo ancora nemmeno atterrati; «l’AI è la **nuova elettricità**» |

La lezione chiede infine di **scrivere la propria opinione** (p. 75): se ne riparlerà.

> 💡 **Leggere le opinioni.** Le citazioni coprono quasi tutto lo spettro: entusiasmo economico, rischio esistenziale, cautela regolatoria, scetticismo sul rischio a breve. Il fatto che la stessa persona (Gates, Musk) cambi posizione nel tempo mostra che non sono conclusioni scientifiche, ma valutazioni legate al momento e al ruolo di chi parla.

---

### **8. Usabilità e maturità delle tecnologie**

_(slide pp. 76–80)_

#### **8.1. Neuralink: interazione avanzata**

**Usabilità e valore di business** (p. 77): la slide cita **Neuralink** di Elon Musk, gli anni di sviluppo necessari e gli **elettrodi in nitinol** di un gruppo della University of California.

**Un esempio di interazione avanzata** (p. 78):

- **Noland Arbaugh** è il **primo paziente** Neuralink con il chip **Telepathy** impiantato nel cervello, che gli permette di usare dispositivi elettronici **solo con il pensiero**;
- è rimasto **paralizzato dalle spalle in giù** dopo un incidente subacqueo otto anni prima; come primo passo **gioca a scacchi e a Civilization**;
- il sistema comprende **batteria**, **caricatore wireless**, **processore**, **I/O**, **elettrodi** e **involucro**;
- un **robot chirurgico** impianta fili ultrasottili con **1024 elettrodi**, più sottili di un capello, nelle regioni specifiche del cervello.

<!-- SOURCE_VISUAL id="IS02-V33" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="77" type="immagini" description="Neuralink ed elettrodi in nitinol della University of California" reason="Esempio visivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **8.2. Il «Hype cycle» di Gartner**

> 📌 **Hype cycle** (p. 79). Grafico di **Gartner**, società statunitense di ricerca e consulenza IT, che rappresenta **maturità, adozione e applicazione sociale** di tecnologie specifiche, con le **aspettative** in funzione del **tempo**.

La slide ne elenca anche i **limiti**:

- **non è un ciclo**;
- **non ha natura scientifica**;
- assume **nessun cambiamento nel tempo** della velocità con cui la tecnologia si sviluppa;
- e altre riserve, ma resta **interessante**.

La p. 80 mostra l’**hype cycle dell’AI e del ML**.

> 💡 **Le fasi (nota didattica).** Nella versione classica le fasi sono: innesco tecnologico, **picco delle aspettative gonfiate**, **fossa della disillusione**, pendio dell’illuminazione, **altopiano della produttività**. Il valore didattico è ricordare che l’entusiasmo per una tecnologia non misura la sua maturità: la posizione delle singole tecnologie AI va letta sul grafico della slide.

<!-- SOURCE_VISUAL id="IS02-V34" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="79" type="grafico" description="Hype cycle di Gartner con aspettative in funzione del tempo e critiche al modello" reason="Forma della curva presente solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS02-V35" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="80" type="grafico" description="Hype cycle delle tecnologie di AI e ML" reason="Posizione delle tecnologie presente solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **9. I quattro driver dell’AI**

_(slide pp. 81–83)_

**I tre driver «classici»** dell’AI (p. 82) sono presentati graficamente; il testo nativo conserva esplicitamente gli **algoritmi**, con un richiamo al **deep learning** e l’invito «tenetelo a mente».

> ⚠️ **Da verificare sulla slide.** Nella letteratura i tre motori classici del successo dell’AI, e in particolare del deep learning, sono di solito i **dati** disponibili in grandi quantità, la **potenza di calcolo** (per esempio le GPU) e gli **algoritmi**. Solo gli algoritmi compaiono nel testo estratto: gli altri due vanno controllati sull’immagine.

<!-- SOURCE_VISUAL id="IS02-V36" source="Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf" page="82" type="infografica" description="I tre driver classici dell’AI, tra cui gli algoritmi, soprattutto per il deep learning" reason="Nomi degli altri driver presenti solo nell’immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Il quarto driver: il cervello del progettista** (p. 83). È ciò che trasforma dati, calcolo e algoritmi in un sistema che funziona:
> - **conoscenza a priori** del problema;
> - **selezione** dei dati;
> - **filtraggio e miglioramento** dei dati;
> - **scelta del modello**;
> - scelta della **tecnica di apprendimento**;
> - **progettazione degli esperimenti**;
> - **evitare la forza bruta**;
> - migliorare la **spiegabilità**;
> - **sistemi ibridi**;
> - **divide et impera**.

> 💡 **Il senso del quarto driver.** Più dati e più calcolo non sostituiscono le scelte di progetto. È lo stesso messaggio della Lezione 01 (il martello e il chiodo, lo spettro dati-conoscenza): conoscere il processo permette di usare meno dati, modelli più semplici e spiegabili, esperimenti meglio progettati.

---

### **10. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **AI** / **ML** | concetto ampio di macchine «intelligenti» / apprendimento dai dati come applicazione attuale dell’AI |
| **Intelligenza computazionale** / **AI in generale** | paradigmi ispirati a biologia e linguaggio (reti, algoritmi genetici, fuzzy) / insieme di tutti i metodi |
| **Rischio inaccettabile** / **alto** / **limitato** / **minimo** | vietato / obblighi rigorosi / trasparenza / nessun obbligo specifico |
| **Spazio di input** / **spazio interno delle feature** | $N$ feature misurate sull’oggetto / $M$ feature costruite dalla rete |
| **Rumore casuale** / **perturbazione avversaria** | variazione qualsiasi / variazione progettata per ingannare il modello |
| **Interpretabilità** / **spiegabilità** | prevedere l’output dall’input / capire come il modello arriva al risultato |
| **Rete neurale** / **albero di decisione** | accurata ma non interpretabile / meno accurato ma spiegabile |
| **Minima modifica che ribalta** / **massima modifica che mantiene** | spiegazione controfattuale / stabilità della decisione |
| **Prägnanz** / **destino comune** | forma più semplice / elementi con stesso moto come unico stimolo |
| **Hype** / **maturità** | aspettative / produttività reale |
| **Tre driver classici** / **quarto driver** | risorse (tra cui algoritmi) / scelte e conoscenza del progettista |

---

### **11. Sintesi della lezione**

_(slide p. 84)_

> ✅ **Punti principali** (p. 84).
> - L’AI è ormai **quasi ovunque** (anche per effetto dell’hype) e continua a migliorare, ma **non si sa esattamente che cosa impari** una rete neurale.
> - Alcune caratteristiche umane rilevanti, come la **Gestalt**, **non sono (ancora) disponibili**.
> - **Piccole perturbazioni** dell’input possono provocare **grandi cambiamenti** nell’output di una rete: servono test e contromisure.
> - La **XAI** risponde alla mancanza di spiegabilità con confidenza, contributi delle feature e spiegazioni controfattuali.
> - L’**AI Act** classifica i sistemi per **rischio** e impone ai sistemi ad alto rischio dati di qualità, tracciabilità, documentazione, supervisione umana e robustezza, con scadenze dal 2025 al 2026.
> - Le **opinioni** sull’AI sono **molto diverse**, tra pubblico ed esperti.
> - Il **quarto driver**, il cervello del progettista, è **il più importante**.
