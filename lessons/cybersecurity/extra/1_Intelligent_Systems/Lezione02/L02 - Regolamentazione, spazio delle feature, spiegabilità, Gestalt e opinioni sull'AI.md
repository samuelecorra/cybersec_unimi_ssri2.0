## **Lezione 2: Regolamentazione, spazio delle feature, spiegabilità, Gestalt e opinioni sull’AI**

Norme, capacità tecniche e qualità delle decisioni si incontrano nella progettazione dei sistemi intelligenti. La regolazione europea definisce obblighi in base ai rischi; il funzionamento dei modelli introduce problemi di robustezza e spiegabilità; l’interazione con le persone porta infine a considerare percezione, agenti e responsabilità di progetto.

### **0. Quadro d’insieme**

Il filo conduttore è la responsabilità lungo tutto il ciclo di vita: definire il contesto d’uso, capire che cosa calcola il modello, misurare i suoi limiti e rendere le decisioni controllabili. L’interazione naturale e gli agenti ampliano il sistema oltre la singola previsione, mentre la conoscenza di chi progetta orienta dati, modelli e verifiche.

---

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

La difficoltà di ricostruire le ragioni di una decisione diventa centrale quando si definiscono i requisiti di trasparenza e le tecniche di spiegazione.

**Che cosa è «intelligente» per la legge**. Secondo l’AI Act un **sistema di AI** è un sistema basato su macchina, progettato per operare con **diversi livelli di autonomia**, che, per obiettivi espliciti o impliciti, **deduce dall’input** che riceve come generare **output** quali previsioni, contenuti, raccomandazioni o decisioni in grado di **influenzare ambienti fisici o virtuali**.

> ⚠️ **Una precisazione sulla definizione.** L’art. 3 del Regolamento (UE) 2024/1689 specifica che il sistema può presentare **adattabilità dopo la diffusione**. La formulazione inglese riportata qui è abbreviata e, senza quel passaggio, risulta sintatticamente incompleta («may exhibit, for explicit or implicit objectives, infers…»).

La definizione normativa descrive il comportamento del sistema: riceve input, li elabora con un certo grado di autonomia e genera output capaci di influenzare un ambiente fisico o virtuale.

#### **1.3. I quattro livelli di rischio**

| Livello | Che cosa comprende | Conseguenza |
| ---------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------- |
| **Rischio inaccettabile** | sistemi considerati una **chiara minaccia** per sicurezza, mezzi di sussistenza e diritti delle persone: dal **social scoring** da parte dei governi ai **giocattoli con assistente vocale** che incoraggiano comportamenti pericolosi | **vietati** |
| **Rischio alto** | infrastrutture critiche (trasporti); istruzione e formazione professionale (valutazione degli esami); componenti di sicurezza di prodotti (chirurgia assistita da robot); lavoro e gestione dei lavoratori (software di selezione dei CV); servizi essenziali pubblici e privati (credit scoring che nega un prestito); forze dell’ordine (valutazione dell’affidabilità delle prove); migrazione, asilo e frontiere (verifica dei documenti di viaggio) | **obblighi rigorosi** prima dell’immissione sul mercato |
| **Rischio limitato** | sistemi con **obblighi di trasparenza**: con un **chatbot** l’utente deve sapere che interagisce con una macchina, per decidere consapevolmente se continuare | trasparenza |
| **Rischio minimo o nullo** | videogiochi con AI, **filtri antispam**; la **grande maggioranza** dei sistemi in uso nell’UE | nessun obbligo specifico |

Il rischio inaccettabile diventa concreto quando un sistema incoraggia comportamenti pericolosi. Un esempio è un **giocattolo potenziato con ChatGPT** che suggerisce a un bambino: «Usa uno sgabello o una scala!». Un consiglio apparentemente innocuo può creare un pericolo proprio per il contesto in cui viene dato.

![](imgs/Pasted%20image%2020261002024716.png)

La classificazione dipende dal contesto d’impiego: lo stesso modello può operare in un filtro antispam a rischio minimo oppure contribuire alla selezione dei CV, un ambito ad alto rischio. La valutazione parte quindi dalla finalità e dagli effetti del sistema.

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

Qualità dei dati, tracciabilità, documentazione, supervisione, robustezza e accuratezza sono requisiti di progettazione che permettono di controllare il sistema anche dopo la sua immissione sul mercato.

L’AI Act è entrato in vigore il **1° agosto 2024**. Le sue disposizioni hanno date di applicazione diverse; alcune scadenze per i sistemi ad alto rischio sono state successivamente prorogate:

| Obbligo | Decorrenza aggiornata |
|---|---|
| divieti relativi alle pratiche di AI a rischio inaccettabile | dal **2 febbraio 2025** |
| specifiche disposizioni aggiuntive dell’articolo 5 introdotte dal Regolamento (UE) 2026/1744 | dal **2 dicembre 2026** |
| obblighi per i modelli di AI per finalità generali | dal **2 agosto 2025** |
| obblighi di trasparenza | dal **2 agosto 2026** |
| adeguamento alla marcatura dei contenuti sintetici (art. 50(2)) per sistemi già sul mercato prima del 2 agosto 2026 | entro il **2 dicembre 2026** |
| sistemi ad alto rischio dell’**Allegato III** (casi d’uso, per esempio lavoro e istruzione) | **2 dicembre 2027** |
| sistemi ad alto rischio dell’**Allegato I** incorporati in prodotti regolamentati | **2 agosto 2028** |

Il quadro è stato modificato dal **Regolamento (UE) 2026/1744**, entrato in vigore il 27 luglio 2026 nell’ambito del Digital Omnibus sull’AI. La modifica non sposta in blocco tutti gli obblighi: alcune disposizioni specifiche dell’articolo 5 e la transizione di marcatura per i sistemi generativi già sul mercato hanno la scadenza ravvicinata del 2 dicembre 2026; i termini per i sistemi ad alto rischio sono invece il 2 dicembre 2027 (Allegato III) e il 2 agosto 2028 (Allegato I). L’articolo 50(2) riguarda marcature leggibili automaticamente per contenuti sintetici e prevede condizioni e limiti: la data transitoria non va estesa indistintamente a ogni fornitore o contenuto. Consultare il [testo ufficiale del Regolamento](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX%3A32026R1744) e la [pagina della Commissione europea sull’AI Act](https://digital-strategy.ec.europa.eu/en/policies/regulatory-framework-ai).

![](imgs/Pasted%20image%2020261120151449.png)

> ⚠️ **Applicazione progressiva.** L’entrata in vigore del regolamento, la decorrenza di un singolo obbligo e l’inizio delle regole per una specifica categoria di sistemi sono momenti diversi. La data corretta dipende dall’obbligo e dalla classificazione del caso d’uso.

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

![](imgs/Pasted%20image%2020261120152816.png)

> 💡 Tra i paradigmi associati alla Computational Intelligence rientrano reti neurali artificiali, calcolo evolutivo, swarm intelligence, sistemi immunitari artificiali e sistemi fuzzy.

![](imgs/Pasted%20image%2020261002025025.png)

**La definizione della IEEE Computational Intelligence Society**: la **teoria, progettazione, applicazione e sviluppo** di paradigmi computazionali **ispirati alla biologia e al linguaggio**, con enfasi su:

- **reti neurali**;
- **sistemi connessionisti**, che rappresentano fenomeni mentali o comportamentali come processi **emergenti** di reti di unità semplici interconnesse;
- **algoritmi genetici**;
- **programmazione evolutiva**;
- **sistemi fuzzy (= la logica fuzzy è utilizzata per gestire l’incertezza e il ragionamento approssimato, infatti fuzzy vuol dire "sfocato")**;
- **sistemi intelligenti ibridi** che contengono questi paradigmi.

Possiamo ora classificare i principali metodi di **machine learning**.

![](imgs/Pasted%20image%2020261002025523.png)

### **3. Il modello addestrato come funzione non lineare**

#### **3.1. Dall’input all’output**

> 📌 **Modello addestrato come funzione**. Un modello di machine learning addestrato calcola una funzione, in generale **non lineare**:
> $$\text{Out} = F(X)$$
> dove $X$ è il **vettore di input** e $\text{Out}$ il **vettore di output**. Se $X\in\mathbb{R}^{n}$ e $\text{Out}\in\mathbb{R}^{m}$, $n$ è il numero di feature d’ingresso e $m$ il numero di uscite. In classificazione, $m$ può corrispondere al numero di classi; in regressione scalare, $m=1$. L’uscita può quindi essere un singolo numero, un vettore o una matrice.

Una singola immagine RGB di $256\times256$ pixel contiene $n=196\,608$ valori d’ingresso. Con le 1.000 classi di ImageNet, il classificatore produce $m=1\,000$ uscite.

![](imgs/Pasted%20image%2020261002025633.png)

> 💡 **Che cosa cambia l’addestramento.** Prima dell’addestramento $F$ ha un’architettura fissata e parametri ancora da stimare. L’addestramento seleziona questi parametri affinché la funzione produca output adeguati sugli esempi disponibili. Durante l’inferenza, il modello applica la funzione appresa a ogni nuovo input.

#### **3.2. Spazio di input e spazio interno delle feature**

La rete trasforma l’input in misure nello **spazio interno delle feature**, dove le rappresentazioni possono essere confrontate e le decisioni calcolate.

| Spazio | Descrizione |
| ---------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| **Spazio di input** | ogni oggetto (un panda, un volto, un fungo) è un punto descritto da $N$ feature: Feature 1, …, Feature $N$ |
| **Spazio interno delle feature** | la rete trasforma gli oggetti in punti di un altro spazio a $M$ feature, dove prende le decisioni |

Una rete profonda si può vedere come composizione di due parti,

$$X \in \mathbb{R}^N \;\xrightarrow{\;\phi\;}\; Z = \phi(X) \in \mathbb{R}^M \;\xrightarrow{\;g\;}\; \text{Out} = g(Z)$$

dove $\phi$ sono gli strati interni che costruiscono la **rappresentazione** e $g$ l’ultimo stadio che **decide**.

![](imgs/Pasted%20image%2020261002025715.png)

![](imgs/Pasted%20image%2020261002025741.png)

---

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

Con la stessa rete si possono ottenere anche immagini prive di una forma riconoscibile per una persona, ma classificate con sicurezza. La domanda tecnica è se un classificatore che produce questi risultati possa essere considerato **robusto**. La risposta richiede valutazioni progettate per misurare il comportamento del modello in condizioni avverse.

Il meccanismo dipende dalla rappresentazione: il modello misura distanze nel proprio spazio delle feature, che può divergere dalla percezione umana, e una perturbazione minima può portare il campione oltre una frontiera di decisione.

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

![](imgs/Pasted%20image%2020261120154641.png)

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

### **6. Interazione naturale tra persone e sistemi intelligenti**

Un sistema intelligente entra spesso in relazione diretta con le persone. In questi casi la qualità della previsione è solo una parte del risultato: il sistema deve interpretare richieste, segnali e condizioni operative, quindi restituire risposte comprensibili e adatte al contesto.

L’interazione naturale mira a rendere lo scambio più vicino a quello umano. I sistemi di deep learning hanno ampliato le capacità di elaborazione del linguaggio, dei gesti e delle emozioni; la loro affidabilità dipende però dalla modalità e dall’ambiente d’uso.

| Modalità                                               | Tecnologie e applicazioni                                                                                                                              |
| ------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Voce e linguaggio**                                  | riconoscimento vocale e NLP per diagnosi sanitarie, domotica e navigazione dei veicoli                                                                 |
| **Gesti**                                              | robot industriali controllati con gesti, formazione in realtà aumentata e traduzione della lingua dei segni                                            |
| **Feedback aptico**                                    | simulazioni per addestramento chirurgico, dispositivi sanitari indossabili e diagnostica industriale                                                   |
| **Riconoscimento visivo ed emotivo**                   | analisi del sentiment dei clienti, coinvolgimento degli studenti e monitoraggio della salute mentale                                                   |
| **Interazione multimodale e consapevole del contesto** | combinazione di più segnali, sensori contestuali e feedback adattivo in manutenzione predittiva, apprendimento adattivo e gestione del traffico urbano |

![](imgs/Pasted%20image%2020261120155549.png)

![](imgs/Pasted%20image%2020261120155636.png)

Il riconoscimento vocale mostra perché una metrica media non basta a valutare un sistema operativo. Su benchmark puliti, i sistemi automatici di speech recognition possono raggiungere un **Word Error Rate (WER) intorno all’1–3%**. In ambienti reali, rumore, accenti, lingua, vocabolario e dominio possono alzare sensibilmente il tasso d’errore. Anche un WER medio basso può nascondere una parola critica fraintesa: la valutazione deve quindi considerare l’effetto dell’errore sul compito completo, non soltanto la trascrizione.

Nel **voice picking** in logistica, per esempio, l’operatore riceve istruzioni vocali mentre prepara gli ordini. Un errore di riconoscimento può rallentare il lavoro, compromettere la correttezza del prelievo o incidere sulla sicurezza. Il sistema va valutato in relazione a produttività, accuratezza e condizioni operative.

![](imgs/Pasted%20image%2020261120155721.png)

Le applicazioni avanzate comprendono anche i **cobot** (_collaborative robots_), robot progettati per operare a fianco delle persone. In questi sistemi, percezione dell’ambiente, comunicazione e controllo devono lavorare insieme per coordinare le azioni in sicurezza.

![](imgs/Pasted%20image%2020261120155835.png)

---

### **7. La Gestalt umana**

![](imgs/Pasted%20image%2020261002031211.png)

#### **7.1. Il tutto è più della somma delle parti**

Perché siamo ancora **lontani dal cervello umano**?

> 📌 **Gestalt**. Termine tedesco che significa all’incirca **schema** o **forma**, introdotto da **Christian von Ehrenfels** (filosofo austriaco, 1859–1932):
>
> - «il **tutto** è **più della somma delle sue parti**»;
> - «apprendere è **più che suscitare risposte meccaniche**»;
> - **esperienze e percezioni** di chi apprende influenzano molto il modo in cui apprende.
>
La percezione gestaltica organizza gli elementi in configurazioni globali: la lettura dell’insieme dipende dai rapporti fra le parti e può cambiare quando cambia la loro disposizione.

#### **7.2. Figura e sfondo**

Una parte dell’immagine può essere **«solo rumore»** per un pattern oppure la **feature più saliente** per un altro. Esempi classici:

| Immagine | Letture possibili |
| ----------------- | --------------------------------------------------------- |
| vaso di Rubin | **vaso** oppure due **volti** di profilo |
| immagine composta | **scimmia**, **leone** oppure **albero** |
| immagine composta | **volto** di donna oppure **uomo che suona il sassofono** |

La stessa scena può essere organizzata in più modi: ciò che in una lettura è sfondo, in un’altra diventa figura. Il riconoscimento richiede di combinare relazioni globali, contesto e aspettative percettive, oltre alle caratteristiche locali dei pixel.

![](imgs/Pasted%20image%2020261002031227.png)

#### **7.3. Leggi dell’organizzazione percettiva**

La percezione raggruppa gli elementi usando indizi locali e relazioni tra parti. La tavola seguente raccoglie le principali leggi illustrate: 

| Legge                              | Come organizza la scena                                                                                                    |
| ---------------------------------- | -------------------------------------------------------------------------------------------------------------------------- |
| **Prossimità**                     | Elementi vicini tendono a essere percepiti come un gruppo.                                                                 |
| **Somiglianza**                    | Elementi con caratteristiche visive simili vengono raggruppati.                                                            |
| **Inclusione**                     | Gli elementi racchiusi entro un contorno comune sono letti come un insieme.                                                |
| **Simmetria**                      | Configurazioni simmetriche tendono a essere percepite come unità.                                                          |
| **Chiusura**                       | Il sistema percettivo completa contorni incompleti e riconosce forme non disegnate per intero.                             |
| **Continuità**                     | Tratti allineati o con andamento regolare vengono interpretati come parti di un percorso continuo.                         |
| **Connessione**                    | Elementi collegati da una linea o da un legame visivo sono percepiti come appartenenti allo stesso gruppo.                 |
| **Figura e sfondo**                | Una regione emerge come figura mentre le aree circostanti assumono il ruolo di sfondo; l’interpretazione può alternarsi.   |
| **Prägnanz** («buona forma»)       | La scena viene organizzata nella configurazione più semplice e stabile compatibile con gli stimoli.                        |
| **Destino comune** (_common fate_) | Elementi che si muovono nella stessa direzione o con lo stesso andamento vengono percepiti come un unico insieme dinamico. |

![](imgs/Pasted%20image%2020261120160209.png)

La prossimità e la somiglianza operano già in una disposizione statica; il destino comune richiede invece di osservare come gli elementi cambiano nel tempo. La figura-sfondo evidenzia che il riconoscimento dipende anche dal modo in cui la scena viene segmentata.

![](imgs/Pasted%20image%2020261002031358.png)

![](imgs/Pasted%20image%2020261002031417.png)

![](imgs/Pasted%20image%2020261002031436.png)

![](imgs/Pasted%20image%2020261002031551.png)

#### **7.4. Verificare la lettura gestaltica con un modello visivo**

Un test del 2024 ha sottoposto a **ChatGPT-4** due immagini costruite per mettere alla prova il destino comune e il rapporto figura-sfondo.

| Test | Risposta del modello | Esito |
| ------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------ |
| **destino comune + figura/sfondo** | descrive **figure stilizzate ripetute** in sequenza, che suggeriscono movimento o progressione, su uno **zigzag** che potrebbe rappresentare montagne o un grafico, forse un elemento di identità visiva sul tema della crescita o del lavoro di squadra | **mancato**: invertendo lo sfondo, le figure sono **frecce** |
| **Prägnanz** («buona forma») | riconosce la forma | **«Nice job!»** |

Il primo risultato descrive le **parti** — sagome, zigzag e ritmo — senza individuare le frecce che emergono invertendo lo sfondo; nel secondo riconosce la forma semplice. Il confronto mostra che descrivere dettagli locali e interpretare la struttura globale sono capacità distinte.

> 💡 **Aggiornamento sui modelli visivi (20 novembre 2026).** Il test con GPT-4 è un’osservazione storica legata a una versione specifica. OpenAI ha annunciato **GPT-6.2 Astra** e **GPT-6.2 Sol** il 17 novembre 2026; entrambi accettano testo e immagini. Un singolo esempio non consente di concludere che un modello possieda o non possieda una capacità percettiva generale: occorrono prove controllate su più immagini e condizioni. [Note ufficiali sui modelli OpenAI](https://help.openai.com/en/articles/9624314-model-release-notes), [documentazione GPT-6.2 Astra](https://developers.openai.com/api/docs/models/gpt-6.2-astra).

![](imgs/Pasted%20image%2020261002031632.png)

![](imgs/Pasted%20image%2020261002031715.png)

### **8. Usabilità e maturità delle tecnologie**

#### **8.1. Neuralink: interazione avanzata**

Un esempio di tecnologia che ambisce a cambiare il modo in cui le persone interagiscono con i dispositivi è **Neuralink**, che sviluppa interfacce neurali. Anche la ricerca sui materiali degli elettrodi, tra cui il nitinol, contribuisce alla progettazione di questi sistemi.

**Un esempio di interazione avanzata**:

- nel **2024**, **Noland Arbaugh** è stato il primo partecipante del trial clinico PRIME a ricevere l’impianto N1 di Neuralink;
- dopo una lesione midollare, ha usato l’interfaccia per controllare un cursore e giocare a scacchi e a *Civilization VI*;
- l’impianto registra attività neurale con **1.024 elettrodi distribuiti su 64 fili flessibili**; un robot chirurgico inserisce i fili nella corteccia;
- elettronica, batteria ricaricabile per induzione e collegamento wireless trasmettono i segnali a un’applicazione esterna, che li decodifica in comandi.

Il dispositivo resta **sperimentale**: il trial PRIME valuta sicurezza e funzionalità iniziale del sistema. Neuralink riferiva **21 partecipanti** nei propri aggiornamenti del 28 gennaio 2026; il dato è una dichiarazione aziendale riferita a quella data. [Studio PRIME e descrizione tecnica](https://neuralink.com/updates/prime-study-progress-update/), [aggiornamenti dello studio](https://neuralink.com/updates/).

![](imgs/Pasted%20image%2020261002031954.png)

![](imgs/Pasted%20image%2020261002032103.png)

#### **8.2. L'«Hype cycle» di Gartner**

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

### **9. Agenti per sistemi intelligenti**

In molte applicazioni il compito non termina con una previsione. Il sistema deve anche interpretare un obiettivo, coordinare più passaggi, consultare fonti diverse e sostenere una decisione. Un **agente** estende il modello con un’interfaccia verso strumenti, dati e persone.

Un modello puro realizza una mappatura tra input e output. Un agente può invece:

- interpretare un obiettivo e suddividerlo in sotto-compiti;
- consultare database, documenti e altri strumenti;
- combinare fonti eterogenee e chiedere chiarimenti alle persone;
- produrre raccomandazioni o avviare azioni autorizzate.

Gli agenti basati su LLM e modelli multimodali sono utili quando occorre osservare, ragionare e agire su dati diversi. Un assistente per la manutenzione industriale può consultare manuali, allarmi e immagini delle macchine; un sistema di monitoraggio ambientale può integrare immagini satellitari, misure dei sensori e regolamenti; un assistente per la supply chain può collegare email, eventi ERP, previsioni della domanda e immagini del magazzino. Gli stessi strumenti possono supportare etichettatura e trasformazione dei dati.

L’accesso a strumenti amplia le capacità del sistema e introduce nuovi requisiti: permessi minimi, tracciabilità delle azioni, conferma umana per operazioni rilevanti e gestione degli errori delle fonti.

### **10. I quattro driver dell’AI**

Tre risorse sostengono il progresso dell’AI: **dati**, **modelli** e **potenza di calcolo**, resa disponibile in particolare dalle GPU. Gli algoritmi definiscono come costruire e addestrare i modelli; nel deep learning, la disponibilità di GPU può incidere in modo rilevante su tempi e costi.

![](imgs/Pasted%20image%2020261002032417.png)

> 📌 **Il quarto driver: la conoscenza di chi progetta**. È ciò che orienta dati, modelli e calcolo verso un sistema utile:
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

> 💡 **Il senso del quarto driver.** La conoscenza del problema permette di selezionare dati pertinenti, scegliere modelli proporzionati e progettare esperimenti informativi. Questa attività riduce l’uso indiscriminato della forza bruta e favorisce sistemi più controllabili.

### **11. Opinioni sull’AI: previsioni, rischi e priorità attuali**

Le valutazioni sull’intelligenza artificiale riflettono aspettative e rischi diversi. Le dichiarazioni storiche aiutano a vedere come il dibattito sia cambiato nel tempo; per orientarsi oggi occorre distinguere le previsioni già verificabili dalle questioni ancora aperte.

| Voce e periodo | Posizione espressa |
|---|---|
| **Stephen Hawking** | l’AI potrebbe diventare «la cosa migliore o la peggiore» per l’umanità; un’AI completa potrebbe riprogettarsi a velocità crescente e superare gli esseri umani, limitati dall’evoluzione biologica |
| **Elon Musk, 2014–2017** | il progresso dell’AI generale sarebbe vicino a una crescita esponenziale; Musk collocava entro cinque anni, al massimo dieci, il rischio di un evento seriamente pericoloso e chiedeva una supervisione regolatoria. La finestra temporale indicata è trascorsa senza l’evento previsto |
| **Bill Gates, 2015** | le macchine potrebbero prima svolgere molti lavori senza essere superintelligenti, con effetti positivi se ben gestite; una capacità molto maggiore avrebbe richiesto cautela. Nel 2023 Microsoft ha investito 10 miliardi di dollari in OpenAI, mostrando che attenzione ai rischi e investimento nell’AI possono coesistere |

Nel dibattito del **2026** ricorrono quattro prospettive:

- **la crescita per scaling continua**: modelli più grandi restano la via per migliorare i sistemi di frontiera;
- **lo scaling incontra rendimenti ridotti**: i progressi dipendono sempre più da strumenti, memoria e agenti;
- **il rischio principale è operativo**: sistemi ordinari, distribuiti su larga scala e gestiti male possono produrre danni concreti;
- **il rischio è anche la concentrazione**: pochi fornitori controllano modelli da cui dipendono molte organizzazioni.

Queste posizioni restano controverse. Per valutarle servono evidenze su capacità, costi, incidenti, distribuzione dei benefici e concentrazione del mercato, oltre alle previsioni sul futuro.

**Domanda di riflessione:** quali applicazioni dell’AI offrono benefici concreti e quali richiedono verifiche, supervisione o limiti più severi?

### **12. Distinzioni da non confondere**

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
| **Tre driver classici** / **quarto driver** | dati, modelli e GPU / conoscenza e scelte di progetto |

### **13. Sintesi della lezione**

> ✅ **Punti principali**.
>
> - Un sistema di AI si valuta nel contesto d’uso e lungo il suo ciclo di vita, tenendo conto dei rischi e degli obblighi applicabili.
> - Un modello addestrato realizza una funzione dai vettori di input alle uscite; le sue rappresentazioni interne possono divergere dalla percezione umana.
> - **Piccole perturbazioni** dell’input possono provocare **grandi cambiamenti** nell’output di una rete: servono test e contromisure.
> - La **XAI** presenta fattori, confidenza e cambiamenti controfattuali; interpretabilità e spiegabilità descrivono aspetti diversi della comprensione del modello.
> - L’interazione naturale combina voce, gesti, feedback aptico, visione e sensori contestuali. La qualità va misurata sul compito e sulle sue condizioni reali.
> - Gli agenti estendono i modelli con strumenti e accesso a fonti di dati; permessi, tracciabilità e supervisione diventano requisiti di progetto.
> - La Gestalt descrive come figura, sfondo e relazioni globali organizzino la percezione; le prove su modelli visivi vanno ripetute su più esempi.
> - Le scadenze dell’AI Act sono progressive: per l’alto rischio, Allegato III dal **2 dicembre 2027** e Allegato I dal **2 agosto 2028**.
> - Dati, modelli e GPU sono driver tecnici; la conoscenza di chi progetta orienta la scelta e l’uso di ciascuna risorsa.
> - Le opinioni sull’AI comprendono previsioni storiche e questioni ancora aperte, come la continuità dello scaling, i rischi operativi e la concentrazione del mercato.
