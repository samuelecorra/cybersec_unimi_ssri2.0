## **Lezione 2: Regolamentazione, spazio delle feature, spiegabilità, Gestalt e opinioni sull’AI**

La seconda lezione del corso **IS4** mette in relazione regole, tecnica e responsabilità. Partiamo dall’AI Act e dai diversi livelli di rischio; poi entriamo nel funzionamento dei modelli, nello spazio delle feature e negli attacchi avversari. Da qui nasce il problema della spiegabilità. Il percorso si allarga infine alla percezione umana, alla Gestalt, alle opinioni sull’AI e alla maturità delle tecnologie.

Materiale di riferimento: [PDF del corso](Lesson_02_Regulatios_InputSpace_Pertubations_XAI_Gestalt_Opinions_Drivers.pdf). [Indice del corso](../L0%20-%20Indice%20del%20corso.md) · [Lezione 01](../Lezione01/L01%20-%20Introduzione%20ai%20sistemi%20intelligenti.md).

### **0. Il percorso della lezione**

La lezione procede dal quadro normativo alle domande tecniche: come apprendono i modelli, perché possono sbagliare e come rendere comprensibili le loro decisioni. Chiude collegando queste questioni alla percezione umana e al modo in cui valutiamo le promesse dell’AI.


### **1. La regolamentazione europea dell’AI**

#### **1.1. L’AI Act**

L’AI è ormai presente in molti ambiti, mentre le regole hanno dovuto inseguire tecnologie in rapida evoluzione. La risposta dell’Unione europea è l’**AI Act**:

- è la **prima legge organica** sull’intelligenza artificiale adottata da un grande regolatore;
- i **27 Stati membri** dell’UE l’hanno approvata all’unanimità;
- è stata pubblicata nella Gazzetta Ufficiale dell’UE il **12 luglio 2024** ed è **entrata in vigore il 1° agosto 2024**.

#### **1.2. Perché servono regole**

Le regole distinguono i sistemi in base al rischio e associano a ciascun livello obblighi diversi. Per capire perché questa classificazione sia necessaria, partiamo dagli effetti che un sistema può avere sulle persone.

![](imgs/Pasted%20image%2020261002024546.png)

![](imgs/Pasted%20image%2020261002024618.png)

**Perché regolare**:

- per **evitare esiti indesiderati**;
- spesso **non è possibile capire perché** un sistema di AI abbia preso una decisione, fatto una previsione o compiuto un’azione;
- diventa quindi difficile stabilire se qualcuno sia stato **svantaggiato ingiustamente**, per esempio in una selezione del personale o in una domanda di accesso a un beneficio pubblico.

Esempi citati: una donna morta in Arizona dopo essere stata investita da un’auto a guida autonoma di **Uber** a Tempe (fonte CNN, 2018); **bot finanziari** che comprano e vendono azioni; un’AI che decide se concedere un **prestito**; il calcolo del costo di un’**assicurazione** con l’AI.

> 💡 **Il filo con il resto della lezione.** Il secondo motivo, «non si sa perché il sistema ha deciso», è esattamente il problema della spiegabilità del §5. Le regole e le tecniche XAI rispondono alla stessa mancanza.

**Che cosa è «intelligente» per la legge**. Secondo l’AI Act un **sistema di AI** è un sistema basato su macchina, progettato per operare con **diversi livelli di autonomia**, che, per obiettivi espliciti o impliciti, **deduce dall’input** che riceve come generare **output** quali previsioni, contenuti, raccomandazioni o decisioni in grado di **influenzare ambienti fisici o virtuali**.

> ⚠️ **Una precisazione sulla definizione.** L’art. 3 del Regolamento (UE) 2024/1689 specifica che il sistema può presentare **adattabilità dopo la diffusione**. La formulazione inglese riportata qui è abbreviata e, senza quel passaggio, risulta sintatticamente incompleta («may exhibit, for explicit or implicit objectives, infers…»).

> 💡 **Una definizione funzionale.** La legge non definisce l’AI con una tecnica (reti neurali, alberi di decisione…) ma con ciò che il sistema **fa**: ricavare output da input con un certo grado di autonomia. È la stessa impostazione operativa della Lezione 01, §1.1.

#### **1.3. I quattro livelli di rischio**

| Livello | Che cosa comprende | Conseguenza |
| ---------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------- |
| **Rischio inaccettabile** | sistemi considerati una **chiara minaccia** per sicurezza, mezzi di sussistenza e diritti delle persone: dal **social scoring** da parte dei governi ai **giocattoli con assistente vocale** che incoraggiano comportamenti pericolosi | **vietati** |
| **Rischio alto** | infrastrutture critiche (trasporti); istruzione e formazione professionale (valutazione degli esami); componenti di sicurezza di prodotti (chirurgia assistita da robot); lavoro e gestione dei lavoratori (software di selezione dei CV); servizi essenziali pubblici e privati (credit scoring che nega un prestito); forze dell’ordine (valutazione dell’affidabilità delle prove); migrazione, asilo e frontiere (verifica dei documenti di viaggio) | **obblighi rigorosi** prima dell’immissione sul mercato |
| **Rischio limitato** | sistemi con **obblighi di trasparenza**: con un **chatbot** l’utente deve sapere che interagisce con una macchina, per decidere consapevolmente se continuare | trasparenza |
| **Rischio minimo o nullo** | videogiochi con AI, **filtri antispam**; la **grande maggioranza** dei sistemi in uso nell’UE | nessun obbligo specifico |

Il rischio inaccettabile diventa concreto quando un sistema incoraggia comportamenti pericolosi. Un esempio è un **giocattolo potenziato con ChatGPT** che suggerisce a un bambino: «Usa uno sgabello o una scala!». Un consiglio apparentemente innocuo può creare un pericolo proprio per il contesto in cui viene dato.

![](imgs/Pasted%20image%2020261002024716.png)

> 💡 **Il rischio dipende dall’uso, non dalla tecnica.** Lo stesso modello di classificazione può essere a rischio minimo in un filtro antispam e ad alto rischio nella selezione dei CV. Chi progetta un sistema intelligente deve quindi chiedersi prima di tutto **in quale contesto** verrà usato.

#### **1.4. Gestione e obblighi per i sistemi ad alto rischio**

**Una volta sul mercato**:

- le **autorità** si occupano della sorveglianza del mercato;
- gli **utilizzatori** garantiscono supervisione e monitoraggio umani; i **fornitori** hanno un sistema di **monitoraggio post-commercializzazione**;
- fornitori e utilizzatori **segnalano** incidenti gravi e malfunzionamenti;
- per richieste generali a livello europeo esiste l’**European AI Office**.

**Obblighi per i sistemi ad alto rischio**:

| Obbligo | Scopo |
| ------------------------------------------------------- | --------------------------------------------------- |
| valutazione e mitigazione dei **rischi** adeguate | prevenire danni |
| **alta qualità dei dataset** che alimentano il sistema | minimizzare rischi ed **esiti discriminatori** |
| **registrazione dell’attività** (logging) | **tracciabilità** dei risultati |
| **documentazione dettagliata** su sistema e scopo | permettere alle autorità di valutarne la conformità |
| **informazioni chiare** all’utilizzatore | uso consapevole |
| misure adeguate di **supervisione umana** | minimizzare il rischio |
| alto livello di **robustezza, sicurezza e accuratezza** | affidabilità |

Per immettere sul mercato un sistema ad alto rischio occorre seguire un processo di conformità e documentazione.

> 💡 **Il processo di conformità.** In sintesi, il fornitore sviluppa il sistema, ne valuta la conformità, lo registra quando previsto e firma la dichiarazione di conformità prima di apporre la **marcatura CE** e immetterlo sul mercato. Se durante il ciclo di vita intervengono **modifiche sostanziali**, la valutazione va ripetuta.

![](imgs/Pasted%20image%2020261002024755.png)

> 📌 **Collegamento con il corso.** Qualità dei dataset, tracciabilità, documentazione, robustezza e accuratezza non sono solo obblighi legali: sono i temi tecnici di molte lezioni successive (raccolta e armonizzazione dei dati, errori nei dataset, partizione dei dati, valutazione dell’accuratezza).

L’AI Act è entrato in vigore il **1° agosto 2024**, ma le disposizioni si applicano in modo **scaglionato**:

| Categoria | Data di applicazione |
| --------------------------------------------------------- | ----------------------------------------------- |
| sistemi **vietati** | dal **2 febbraio 2025** |
| sistemi di AI per **finalità generali** (general-purpose) | obblighi specifici dal **2 agosto 2025** |
| sistemi ad **alto rischio** | conformità richiesta entro il **2 agosto 2026** |

> ⚠️ **Un dettaglio sulle scadenze (nota didattica).** Nel regolamento la data del 2 agosto 2026 riguarda in generale i sistemi ad alto rischio dell’allegato III; per i sistemi ad alto rischio che sono componenti di sicurezza di prodotti già regolati (allegato I) il termine è il **2 agosto 2027**. Eventuali rinvii successivi alla data del materiale vanno verificati sulle fonti ufficiali.

### **2. Che cos’è un sistema intelligente**

#### **2.1. Sistemi intelligenti, AI e machine learning**

**Sistemi intelligenti artificiali**:

- programmi software o sistemi software/hardware progettati per svolgere **compiti complessi** con strategie che **imitano qualche aspetto del pensiero umano**;
- si può discutere all’infinito se un certo sistema sia intelligente o no;
- **la chiave è la capacità di evolvere**: un sistema è intelligente se **può imparare**, anche solo in senso limitato, e/o **migliorare nel tempo**.

**AI contro ML**:

| | Significato |
| -------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| **Artificial Intelligence** (AI) | il concetto **più ampio** di macchine capaci di svolgere compiti in un modo che consideriamo «intelligente» |
| **Machine Learning** (ML) | un’**applicazione attuale** dell’AI basata sull’idea di dare alle macchine **accesso ai dati** e lasciarle **imparare da sole** |

L’AI dialoga con discipline diverse. Per costruire un sistema di machine learning servono almeno tre ingredienti: dati, feature e un algoritmo di apprendimento.

> 💡 **Tre ingredienti del ML.** I dati forniscono gli esempi; le **feature** descrivono ciascun caso; l’algoritmo usa queste informazioni per apprendere una regolarità.

![](imgs/Pasted%20image%2020261002024914.png)

![](imgs/Pasted%20image%2020261002024925.png)

#### **2.2. Intelligenza computazionale**

La **Computational Intelligence** è un’area collegata all’AI. Nel testo di **A. P. Engelbrecht**, _Computational Intelligence: An Introduction_ (2006), è descritta attraverso i meccanismi adattivi che rendono possibile un comportamento intelligente in ambienti complessi e mutevoli.

> 💡 Tra i paradigmi associati alla Computational Intelligence rientrano reti neurali artificiali, calcolo evolutivo, swarm intelligence, sistemi immunitari artificiali e sistemi fuzzy.

**La definizione della IEEE Computational Intelligence Society**: la **teoria, progettazione, applicazione e sviluppo** di paradigmi computazionali **ispirati alla biologia e al linguaggio**, con enfasi su:

- **reti neurali**;
- **sistemi connessionisti**, che rappresentano fenomeni mentali o comportamentali come processi **emergenti** di reti di unità semplici interconnesse;
- **algoritmi genetici**;
- **programmazione evolutiva**;
- **sistemi fuzzy (= la logica fuzzy è utilizzata per gestire l’incertezza e il ragionamento approssimato, infatti fuzzy vuol dire "sfocato")**;
- **sistemi intelligenti ibridi** che contengono questi paradigmi.

Possiamo ora classificare i principali metodi di **machine learning**.

![](imgs/Pasted%20image%2020261002025025.png)

![](imgs/Pasted%20image%2020261002025523.png)

### **3. Una rete addestrata è una funzione non lineare**

#### **3.1. Nessuna magia: solo funzioni**

> 📌 **Modello addestrato come funzione**. Un modello di machine learning addestrato è **soltanto una funzione**, in generale **non lineare**:
> $$\text{Out} = F(X)$$
> dove $X$ è il **vettore di input** e $\text{Out}$ il **vettore di output**. L’uscita può essere un **singolo valore** (intero o reale) oppure un **vettore** o una **matrice**.

![](imgs/Pasted%20image%2020261002025633.png)

> 💡 **Che cosa cambia l’addestramento.** Prima dell’addestramento $F$ ha una forma fissata (per esempio una rete con una certa architettura) e parametri qualsiasi. L’addestramento sceglie i parametri in modo che $F$ produca gli output desiderati sugli esempi. Dopo, il modello non «ragiona»: calcola $F(X)$ per ogni nuovo input. Capire questo toglie la «magia» e rende naturali i problemi dei paragrafi successivi.

#### **3.2. Spazio di input e spazio interno delle feature**

Nello spazio delle feature la rete **non «pensa», ma «misura»**.

| Spazio | Descrizione |
| ---------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| **Spazio di input** | ogni oggetto (un panda, un volto, un fungo) è un punto descritto da $N$ feature: Feature 1, …, Feature $N$ |
| **Spazio interno delle feature** | la rete trasforma gli oggetti in punti di un altro spazio a $M$ feature, dove prende le decisioni |

Una rete profonda si può vedere come composizione di due parti,

$$X \in \mathbb{R}^N \;\xrightarrow{\;\phi\;}\; Z = \phi(X) \in \mathbb{R}^M \;\xrightarrow{\;g\;}\; \text{Out} = g(Z)$$

dove $\phi$ sono gli strati interni che costruiscono la **rappresentazione** e $g$ l’ultimo stadio che **decide**.

![](imgs/Pasted%20image%2020261002025715.png)

![](imgs/Pasted%20image%2020261002025741.png)
#### **3.3. Frontiere di decisione**

Nello spazio delle feature la rete separa le regioni associate a «negozi di scarpe», «struzzi» e «aspirapolvere» con **frontiere di decisione**. Come può una **piccola distorsione dell’immagine di un negozio di scarpe** farlo classificare come uno **struzzo** o un **aspirapolvere**? Perché la rete, per esempio un rilevatore visivo di oggetti, è una **funzione non lineare**: le frontiere possono passare **molto vicino** ai punti degli esempi, e un piccolo spostamento dell’input può spingere il punto nella regione sbagliata.

**Chihuahua o muffin?** (esempio di Mariya Yao, Metamaven). Il concetto di **somiglianza** del cervello umano **non coincide** con le metriche delle reti profonde; tuttavia si percepisce che muffin e chihuahua sono vicini anche nel **nostro** spazio interno delle feature: colori, forme rotonde e macchie scure li avvicinano.

![](imgs/Pasted%20image%2020261002025850.png)

![](imgs/Pasted%20image%2020261002025932.png)

### **4. Problema: gli attacchi avversari**

Anche i modelli neurali profondi **possono essere attaccati**. Come mostra il lavoro di I. Goodfellow presentato al NIPS 2016 Workshop on Bayesian Deep Learning, piccole distorsioni o perturbazioni progettate possono produrre **errori**:

- si scelgono gli **input peggiori** (_worst case inputs_) da perturbare con **pattern additivi progettati**;
- **non tutte le regioni** dell’input si prestano a creare esempi avversari.

> 📌 **Esempio avversario (nota didattica).** Dato un input $x$ classificato correttamente, un esempio avversario è
> $$x' = x + \eta, \qquad \lVert \eta \rVert \text{ piccola}$$
> tale che il modello classifica $x'$ in modo diverso da $x$, mentre per un essere umano le due immagini sono praticamente uguali. La perturbazione $\eta$ non è rumore casuale: è **progettata** sfruttando la conoscenza del modello.

**Una «vista» migliore**: con una rete **AlexNet** «off the shelf», addestrata su **ImageNet** (1,3 milioni di immagini), si parte da un’immagine simile nello spazio interno delle feature e la si modifica fino a cambiarne la classe.

**O anche peggio**: con la stessa rete si ottengono immagini che non assomigliano a nulla ma vengono classificate con sicurezza. La domanda del docente: **la si può considerare uno strumento di elaborazione delle immagini sicuro e robusto?** Durante il corso si vedrà come **testare e prevenire** questi esiti indesiderati.

> 💡 **Collegamento con la Lezione 01.** La Lezione 01, §7.1, ha già mostrato pattern artificiali e immagini simili a rumore classificati con alta confidenza. Qui l’accento è sul meccanismo: la rete misura distanze in uno spazio delle feature che non coincide con la percezione umana, e le frontiere di decisione possono essere raggiunte con spostamenti minimi.

![](imgs/Pasted%20image%2020261002030232.png)

![](imgs/Pasted%20image%2020261002030258.png)

O ancor peggio...

![](imgs/Pasted%20image%2020261002030312.png)

### **5. Problema: la spiegabilità minima**

#### **5.1. Reti come scatole nere**

I modelli neurali sono spesso descritti come **«scatole nere»**: osserviamo l’input e l’output, ma non è immediato capire quali passaggi interni abbiano portato alla decisione.

**Mancanza di spiegabilità delle grandi reti**:

| Modello | Accuratezza | Spiegabilità |
| ----------------------- | -------------------- | -------------------------------------------------- |
| **rete neurale** | tipicamente **alta** | **non interpretabile**: perché l’output è «gatto»? |
| **albero di decisione** | **minore** | la decisione **si può spiegare** |

![](imgs/Pasted%20image%2020261002030355.png)

![](imgs/Pasted%20image%2020261002030411.png)

#### **5.2. Explainable Artificial Intelligence**

> 📌 **XAI**. Un insieme di **processi e metodi** che permette agli utenti umani di:
>
> - **comprendere** e **fidarsi** dei risultati prodotti dagli algoritmi di machine learning;
> - **descrivere** il modello, il suo **impatto atteso** e i **possibili bias**.
>
> La XAI aiuta a caratterizzare, nelle decisioni basate sull’AI: **accuratezza** del modello, **equità** (_fairness_), **trasparenza**, **esiti**.

**Esempio: rilevatore di allerta tornado**:

| | Output |
| -------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **albero di decisione classico** | `Watch = 1` |
| **output XAI** | «È stata prevista un’allerta tornado nella contea di Fairfax, con una **confidenza del 75%** e una durata minima e massima di **1–2 ore**. La previsione si basa su **dati storici con caratteristiche simili**, come frequenza storica, temperatura, velocità del vento e letture del barometro.» |

> 💡 **Che cosa aggiunge l’output XAI.** Non cambia la decisione (`Watch = 1`), ma aggiunge **confidenza**, **portata** della previsione e **ragioni**: quali feature hanno contato. È la differenza tra un sistema che si può solo eseguire e uno che si può **controllare**.

![](imgs/Pasted%20image%2020261002030504.png)

#### **5.3. Interpretabilità e spiegabilità**

| | **Interpretabilità** | **Spiegabilità** |
| ----------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------------- |
| Definizione | il grado in cui un osservatore **comprende la causa** di una decisione; il **tasso di successo** con cui un umano **prevede** l’output dell’AI; se un modello «ha senso» per l’utente | va **un passo oltre** e guarda **come** l’AI è arrivata al risultato |
| Oggetto | la relazione **input/output**: dato $X$, prevedo $y$ | l’interno della «scatola»: il **meccanismo** del modello |

**Il compromesso interpretabilità-accuratezza**: i modelli più interpretabili tendono a essere meno accurati e viceversa. Yi Mei mette a confronto gli approcci alla spiegabilità di ieri e di oggi.

> 💡 **Come leggere il compromesso.** Una regressione lineare o un albero poco profondo si leggono direttamente, ma possono non catturare relazioni complesse. Una rete profonda cattura relazioni molto complesse, ma i suoi milioni di parametri non hanno un significato leggibile. La XAI cerca di spostare questa frontiera: mantenere l’accuratezza del modello complesso aggiungendo spiegazioni.

![](imgs/Pasted%20image%2020261002030753.png)

![](imgs/Pasted%20image%2020261002030805.png)

#### **5.4. Funzionalità desiderabili e un esempio reale**

**Funzionalità desiderabili della XAI**:

| Funzionalità | Che cosa fa |
| ------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **fairness e debiasing** | gestire e monitorare l’equità; **analizzare** il sistema in esercizio alla ricerca di **bias** |
| **mitigazione del model drift** | analizzare il modello e dare raccomandazioni sull’esito più logico; **allertare** quando il modello si allontana dagli esiti previsti |
| **gestione del rischio del modello** | **quantificare** e mitigare il rischio; allertare quando il modello funziona in modo inadeguato; capire che cosa è successo quando le deviazioni **persistono** |

> 💡 **Model drift (nota didattica).** Un modello è addestrato su dati di un certo periodo; se nel tempo cambiano i dati in ingresso (per esempio le abitudini dei clienti) o la relazione tra input e output, le sue prestazioni peggiorano senza che nessuno modifichi il modello. Monitorare il drift significa accorgersene prima che le decisioni sbagliate si accumulino.

**Dashboard per l’approvazione di prestiti** (IBM Watson Explainable AI):

- una **spiegazione testuale** della decisione;
- la classe predetta, **Risk** (prestito non concesso) o **No Risk** (prestito concesso), con la sua **confidenza**;
- **quali feature e quanto** hanno contribuito alla decisione di rischio o di assenza di rischio;
- la **minima modifica** degli input che **ribalterebbe** l’output;
- la **massima modifica** che **manterrebbe** lo stesso output.

> 📌 **Spiegazioni controfattuali.** «La minima modifica che ribalta la decisione» è una **spiegazione controfattuale**: dice al cliente che cosa dovrebbe cambiare (per esempio il reddito o la durata del prestito) per ottenere l’esito opposto. «La massima modifica che mantiene l’output» misura invece la **stabilità** della decisione: se basta pochissimo per ribaltarla, la decisione è fragile.

![](imgs/Pasted%20image%2020261002031013.png)

![](imgs/Pasted%20image%2020261002031023.png)

![](imgs/Pasted%20image%2020261002031032.png)

![](imgs/Pasted%20image%2020261002031129.png)

![](imgs/Pasted%20image%2020261002031141.png)

![](imgs/Pasted%20image%2020261002031158.png)

### **6. La Gestalt umana**

![](imgs/Pasted%20image%2020261002031211.png)

#### **6.1. Il tutto è più della somma delle parti**

Perché siamo ancora **lontani dal cervello umano**?

> 📌 **Gestalt**. Termine tedesco che significa all’incirca **schema** o **forma**, introdotto da **Christian von Ehrenfels** (filosofo austriaco, 1859–1932):
>
> - «il **tutto** è **più della somma delle sue parti**»;
> - «apprendere è **più che suscitare risposte meccaniche**»;
> - **esperienze e percezioni** di chi apprende influenzano molto il modo in cui apprende.
>
> Questa caratteristica del cervello umano è **difficile da imitare** con i modelli di AI attuali.

#### **6.2. Figura e sfondo**

Una parte dell’immagine può essere **«solo rumore»** per un pattern oppure la **feature più saliente** per un altro. Esempi classici:

| Immagine | Letture possibili |
| ----------------- | --------------------------------------------------------- |
| vaso di Rubin | **vaso** oppure due **volti** di profilo |
| immagine composta | **scimmia**, **leone** oppure **albero** |
| immagine composta | **volto** di donna oppure **uomo che suona il sassofono** |

> 💡 **Perché è difficile per una rete.** Un classificatore assegna a ogni pixel un peso fisso rispetto a una classe. Il cervello, invece, **riorganizza** la stessa scena: ciò che era sfondo diventa figura. Non esiste una sola risposta «giusta»; conta l’organizzazione globale.

![](imgs/Pasted%20image%2020261002031227.png)

#### **6.3. Leggi dell’organizzazione percettiva**

La percezione visiva segue alcune **leggi di organizzazione**:

| Legge | Contenuto |
| --------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Prägnanz** («buona forma», ) | gli oggetti vengono percepiti nel modo **più semplice possibile**: si vede una serie di **cerchi sovrapposti** invece di un insieme di linee curve collegate |
| **Destino comune** (_common fate_, ) | gli elementi che si muovono con la **stessa velocità e/o direzione** vengono percepiti come parti di **un unico stimolo** |

![](imgs/Pasted%20image%2020261002031340.png)

![](imgs/Pasted%20image%2020261002031358.png)

![](imgs/Pasted%20image%2020261002031417.png)

![](imgs/Pasted%20image%2020261002031436.png)

![](imgs/Pasted%20image%2020261002031551.png)

#### **6.4. Mettere alla prova ChatGPT-4**

Per osservare questi limiti, il docente propone a **ChatGPT-4** due immagini basate sulla Gestalt.

| Test | Risposta del modello | Esito |
| ------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| **destino comune + figura/sfondo** | descrive **figure stilizzate ripetute** in sequenza, che suggeriscono movimento o progressione, su uno **zigzag** che potrebbe rappresentare montagne o un grafico, forse un elemento di identità visiva sul tema della crescita o del lavoro di squadra | **mancato**: invertendo lo sfondo, le figure sono **frecce** |
| **Prägnanz** («buona forma», ) | riconosce la forma | **«Nice job!»** |

> 💡 **Che cosa mostra il test.** Il modello descrive molto bene le **parti** (sagome, zigzag, ritmo), ma non esegue la **riorganizzazione figura-sfondo** che fa emergere le frecce. È un esempio concreto del limite del §6.1: descrivere le parti non equivale a percepire il tutto. Il risultato riguarda la versione del modello usata dal docente.

![](imgs/Pasted%20image%2020261002031632.png)

![](imgs/Pasted%20image%2020261002031715.png)

### **7. Opinioni sull’AI**

#### **7.1. Il pubblico: il sondaggio britannico**

Nel novembre 2022 l’**Ada Lovelace Institute**, insieme all’**Alan Turing Institute**, ha condotto in Gran Bretagna un **sondaggio nazionale rappresentativo** sugli atteggiamenti verso l’AI, coinvolgendo **4.000 adulti**. I risultati mostrano un’opinione pubblica sfumata, con valutazioni diverse a seconda dell’uso considerato.

**Sintesi**:

- per la **maggior parte** degli usi dell’AI chiesti, le persone hanno opinioni **nel complesso positive**, ma esprimono **preoccupazioni** su alcuni usi;
- approfondendo, il pubblico britannico ha opinioni **molto sfumate** su vantaggi e svantaggi dei **diversi** usi;
- il pubblico **vuole una regolamentazione** delle tecnologie di AI, anche se questo varia con l’**età**.

![](imgs/Pasted%20image%2020261002031749.png)

![](imgs/Pasted%20image%2020261002031805.png)

![](imgs/Pasted%20image%2020261002031816.png)

#### **7.2. Gli esperti**

| Chi                                                    | Opinione                                                                                                                                                                                                                                                                                                                                                                              |
| ------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Bill Gates**, Microsoft                              | «Una svolta nel machine learning varrebbe **dieci Microsoft**»                                                                                                                                                                                                                                                                                                                        |
| **Tony Tether**, DARPA                                 | «Il machine learning è la **prossima Internet**»                                                                                                                                                                                                                                                                                                                                      |
| **John Hennessy**, Stanford                            | «Il machine learning è **la novità del momento**»                                                                                                                                                                                                                                                                                                                                     |
| **Stephen Hawking**                                    | ==l’AI sarà «**la cosa migliore o la peggiore**» per l’umanità; lo sviluppo di un’AI completa potrebbe segnare **la fine della specie umana**: si riprogetterebbe da sola a velocità crescente, e gli umani, limitati dalla lenta evoluzione biologica, **non potrebbero competere**==                                                                                                |
| **Larry Page**, Google                                 | l’AI sarebbe **la versione definitiva di Google**, il motore di ricerca che capisce tutto il Web e ciò che vuoi; «siamo lontanissimi», ma ci si avvicina un passo alla volta                                                                                                                                                                                                          |
| **Elon Musk** («probabilmente cambiando opinione»)     | il progresso dell’AI (non quella «ristretta») è **quasi esponenziale**; il rischio di qualcosa di seriamente pericoloso è in un orizzonte di **cinque anni, dieci al massimo**; serve una **supervisione regolatoria** nazionale e internazionale: «con l’intelligenza artificiale **evochiamo il demone**»                                                                           |
| **Bill Gates**, opinione recente                       | gli umani **dovrebbero preoccuparsi**; prima le macchine faranno molti lavori senza essere superintelligenti, e sarà positivo se gestito bene; qualche decennio dopo l’intelligenza sarà abbastanza forte da **destare preoccupazione**; «sono d’accordo con Elon Musk». La citazione ricorda anche che **Microsoft ha investito 10 miliardi di dollari** in OpenAI (Bloomberg, 2023) |
| **Andrew Ng**, informatico con oltre 167.000 citazioni | preoccuparsi oggi di una superintelligenza malvagia è come **preoccuparsi della sovrappopolazione su Marte**: non ci siamo ancora nemmeno atterrati; «l’AI è la **nuova elettricità**»                                                                                                                                                                                                |

A questo punto, prova a formulare la tua opinione: quali applicazioni ti sembrano promettenti e quali richiedono più cautela?

> 💡 **Leggere le opinioni.** Le citazioni coprono quasi tutto lo spettro: entusiasmo economico, rischio esistenziale, cautela regolatoria, scetticismo sul rischio a breve. Il fatto che la stessa persona (Gates, Musk) cambi posizione nel tempo mostra che non sono conclusioni scientifiche, ma valutazioni legate al momento e al ruolo di chi parla.

### **8. Usabilità e maturità delle tecnologie**

#### **8.1. Neuralink: interazione avanzata**

Un esempio di tecnologia che ambisce a cambiare il modo in cui le persone interagiscono con i dispositivi è **Neuralink**, che sviluppa interfacce neurali. Anche la ricerca sui materiali degli elettrodi, tra cui il nitinol, contribuisce alla progettazione di questi sistemi.

**Un esempio di interazione avanzata**:

- **Noland Arbaugh** è il **primo paziente** Neuralink con il chip **Telepathy** impiantato nel cervello, che gli permette di usare dispositivi elettronici **solo con il pensiero**;
- è rimasto **paralizzato dalle spalle in giù** dopo un incidente subacqueo otto anni prima; come primo passo **gioca a scacchi e a Civilization**;
- il sistema comprende **batteria**, **caricatore wireless**, **processore**, **I/O**, **elettrodi** e **involucro**;
- un **robot chirurgico** impianta fili ultrasottili con **1024 elettrodi**, più sottili di un capello, nelle regioni specifiche del cervello.

![](imgs/Pasted%20image%2020261002031954.png)

![](imgs/Pasted%20image%2020261002032103.png)

#### **8.2. Il «Hype cycle» di Gartner**

> 📌 **Hype cycle**. Grafico di **Gartner**, società statunitense di ricerca e consulenza IT, che rappresenta **maturità, adozione e applicazione sociale** di tecnologie specifiche, con le **aspettative** in funzione del **tempo**.

Il modello ha però dei **limiti**:

- **non è un ciclo**;
- **non ha natura scientifica**;
- assume **nessun cambiamento nel tempo** della velocità con cui la tecnologia si sviluppa;
- e altre riserve, ma resta **interessante**.

L’**hype cycle** applicato all’AI e al machine learning colloca queste tecnologie lungo le fasi di aspettativa, disillusione e maturazione.

> 💡 **Le fasi (nota didattica).** Nella versione classica le fasi sono: innesco tecnologico, **picco delle aspettative gonfiate**, **fossa della disillusione**, pendio dell’illuminazione, **altopiano della produttività**. Il punto da ricordare è che l’entusiasmo per una tecnologia non ne misura la maturità: occorre distinguere le aspettative dalla produttività dimostrata.

![](imgs/Pasted%20image%2020261002032146.png)

![](imgs/Pasted%20image%2020261002032157.png)

### **9. I quattro driver dell’AI**

**I tre driver «classici»** dell’AI sono presentati graficamente; il testo nativo conserva esplicitamente gli **algoritmi**, con un richiamo al **deep learning** e l’invito «tenetelo a mente».

Tre risorse sostengono il progresso dell’AI: **dati**, **potenza di calcolo** e **algoritmi**. Il deep learning è un esempio di come questi fattori possano combinarsi.

![](imgs/Pasted%20image%2020261002032417.png)

> 📌 **Il quarto driver: il cervello del progettista**. È ciò che trasforma dati, calcolo e algoritmi in un sistema che funziona:
>
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

### **10. Distinzioni da non confondere**

| Coppia | Differenza chiave |
| ------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------- |
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

### **11. Sintesi della lezione**

> ✅ **Punti principali**.
>
> - L’AI è ormai **quasi ovunque** (anche per effetto dell’hype) e continua a migliorare, ma **non si sa esattamente che cosa impari** una rete neurale.
> - Alcune caratteristiche umane rilevanti, come la **Gestalt**, **non sono (ancora) disponibili**.
> - **Piccole perturbazioni** dell’input possono provocare **grandi cambiamenti** nell’output di una rete: servono test e contromisure.
> - La **XAI** risponde alla mancanza di spiegabilità con confidenza, contributi delle feature e spiegazioni controfattuali.
> - L’**AI Act** classifica i sistemi per **rischio** e impone ai sistemi ad alto rischio dati di qualità, tracciabilità, documentazione, supervisione umana e robustezza, con scadenze dal 2025 al 2026.
> - Le **opinioni** sull’AI sono **molto diverse**, tra pubblico ed esperti.
> - Il **quarto driver**, il cervello del progettista, è **il più importante**.
