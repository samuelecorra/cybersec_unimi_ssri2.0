## **Lezione 3: Narrow AI, tassonomia dell’apprendimento, sensori intelligenti e strumenti**

Le capacità di una macchina si misurano attraverso prove definite e benchmark con limiti espliciti. I metodi di apprendimento si organizzano distinguendo il segnale usato per imparare dal tipo di risultato prodotto: regressione, classificazione, clustering, riduzione dimensionale o politica di controllo. Da qui si passa ai ragionamenti deduttivo, induttivo e trasduttivo, alla valutazione operativa, ai sensori intelligenti e agli strumenti per costruire esperimenti riproducibili.

Approfondimento: [The AI Apocalypse Matrix](sources_2026-27/Lesson_03_The_AI_Apocalypse_Matrix.pdf), pubblicato da *IEEE Spectrum* nell’agosto 2023.

### **0. Il percorso della lezione**

La trattazione procede in quattro passaggi: distinguere AI ristretta e generale; capire come si misurano le capacità delle macchine; ordinare i metodi di apprendimento e i criteri di valutazione; collegare l’AI ai sensori e agli strumenti con cui si progetta.


### **1. Narrow AI e General AI**

#### **1.1. Due punti di vista**

La distinzione riguarda l’ampiezza delle capacità: la **Narrow AI** è progettata per compiti circoscritti; la **General AI** (AGI) indicherebbe la capacità di apprendere, ragionare e adattarsi in domini diversi.

![](imgs/Pasted%20image%2020261002032915.png)

![](imgs/Pasted%20image%2020261002032923.png)

#### **1.2. Narrow AI**

![](imgs/Pasted%20image%2020261002033107.png)

> 📌 **Narrow AI**, detta anche **Artificial Narrow Intelligence** (ANI) o **Weak AI**. Indica sistemi di AI **progettati e addestrati** per svolgere **un compito specifico** o un insieme ristretto di compiti, di solito in un **dominio limitato**.
> - Esempio: un modello addestrato a riconoscere **immagini di gatti** sa fare solo quello e **non generalizza** ad altri tipi di immagini o di compiti.
> - È oggi la forma **predominante** di AI: riconoscimento vocale, immagini e linguaggio naturale. Un sistema ristretto può elaborare più modalità: «ristretto» descrive l’ampiezza delle capacità, non il numero di tipi di input.

**Limiti tipici dei sistemi ristretti**:

- **autoconsapevolezza**;
- **coscienza**;
- **emozioni**;
- un’**intelligenza genuina** paragonabile a quella umana;
- e naturalmente la **Gestalt**: la capacità di cambiare dinamicamente il **contesto**, il **livello di dettaglio** e il **livello di astrazione** per capire meglio i dati, secondo la propria esperienza e conoscenza (sezione sulla Gestalt).

La Narrow AI offre vantaggi pratici nei compiti ben definiti e richiede verifiche di robustezza, qualità dei dati, spiegabilità e controllo dei bias.

> 💡 **Vantaggi e sfide tipici.** I sistemi ristretti possono essere veloci, ripetibili e accurati nel proprio dominio, riducendo il lavoro manuale. Fuori da quel dominio, però, possono non generalizzare; dipendono dalla qualità e quantità dei dati, possono essere difficili da spiegare e fragili davanti a input inattesi.

![](imgs/Pasted%20image%2020261002033128.png)

![](imgs/Pasted%20image%2020261002033140.png)
#### **1.3. General AI**

> 📌 **General AI**, detta anche **Strong AI** o **Artificial General Intelligence** (AGI). Indica sistemi con un’intelligenza **simile a quella umana**, capaci di **ragionare, apprendere e adattarsi** a un’ampia gamma di compiti e situazioni.
> - Sarebbe in grado di svolgere **qualsiasi compito intellettuale** che un essere umano sa svolgere, e forse di **superarlo** in alcuni ambiti.
> - È ancora un **concetto teorico**, anche se sono in corso progressi significativi.

> ⚠️ **Un problema di definizione.** Non esiste una definizione standard di che cosa sia «simile all’umano»: per questo sarà **difficile definire l’AGI**, e quindi anche stabilire quando la si sarà raggiunta.

Un esempio video usa con ironia la frase «disorganizzato… cominciamo dalle tue email» per introdurre la promessa di assistenti capaci di agire in contesti quotidiani.

![](imgs/Pasted%20image%2020261002033231.png)

**Alcune dichiarazioni sull’AGI**, da **Sam Altman** (7 gennaio 2025):

- «Credo che raggiungeremo l’AGI **prima di quanto pensi** la maggior parte delle persone, e **conterà molto meno**»;
- la superintelligenza potrebbe arrivare in «**qualche migliaio di giorni**», cioè circa **5–10 anni**;
- «nel **2025** potremmo vedere i primi **agenti di AI “entrare nella forza lavoro”** e cambiare in modo sostanziale la produzione delle aziende».

Queste dichiarazioni usano formule prudenti («potremmo vedere», «è possibile») e arrivano da un settore **molto competitivo**: OpenAI, Google (DeepMind), Meta, Anthropic e xAI **gareggiano** per raggiungere l’AGI. Un articolo di PCMag riporta inoltre l’affermazione di un cofondatore di Google secondo cui per arrivare all’AGI servirebbero **settimane da 60 ore**.

> 💡 **Leggere le dichiarazioni.** Le frasi al condizionale e le stime in «migliaia di giorni» non sono previsioni verificabili: fanno parte della comunicazione di aziende in competizione. È lo stesso invito alla lettura critica delle opinioni della .

#### **1.4. Il dibattito sull’AGI e la «AI Apocalypse Matrix»**

**Le questioni principali**:

- l’AGI **non è una soglia scientifica unica e condivisa**;
- ricercatori diversi intendono **cose diverse**: flessibilità di livello umano, **trasferimento tra domini**, risoluzione autonoma di problemi, **utilità economica**;
- le affermazioni sull’AGI vanno interpretate distinguendo se sono:
 1. un’affermazione **scientifica**;
 2. un’affermazione sulle **capacità ingegneristiche**;
 3. un’affermazione sull’**impatto economico**;
 4. una forma di **comunicazione pubblica** o una mossa **strategica**.

Il dibattito è illustrato anche dal **panel di IEEE Spectrum** sull’AGI, pubblicato nel 2023.

![](imgs/Pasted%20image%2020261002033454.png)

**The AI Apocalypse Matrix** (*IEEE Spectrum*, agosto 2023, di Eliza Strickland e Glenn Zorpette). L’articolo parte da due domande su GPT-4:

- è un passo importante verso l’**AGI**, cioè un sistema con un intelletto flessibile di livello umano?
- se creassimo un’AGI, potrebbe essere così diversa da noi da **non vedere motivo di tenere in vita l’Homo sapiens**?

Tra i migliori esperti **non c’è consenso**:

| Posizione | Chi (secondo l’articolo) |
|---|---|
| GPT-4 è un **chiaro segnale** che l’AGI è vicina | **Geoffrey Hinton** |
| non siamo **più vicini** all’AGI di 30 anni fa | **Rodney Brooks** |
| un’AGI molto superiore a noi troverebbe il modo di **uscire dal mondo digitale** e distruggere la civiltà | **Nick Bostrom**, tra i primi «profeti di sventura» |
| scenari del genere sono **fantascienza senza senso** | **Yann LeCun** |
| rischi **seri ma non apocalittici**: disoccupazione, amplificazione dei **bias**, **propaganda**, disinformazione e **deepfake** su larga scala | ricercatori nella posizione intermedia |

L’articolo ricorda due iniziative del 2023: la **lettera aperta** di marzo che chiedeva a tutti i laboratori di **sospendere per sei mesi** gli «esperimenti giganti di AI», e la **dichiarazione di una frase** di fine maggio che invitava a prendere sul serio il rischio di «**estinzione causata dall’AI**».

**La matrice.** IEEE Spectrum ha raccolto le posizioni pubblicate di **23 esperti** e le ha collocate in una matrice a due assi:

- asse dell’**AGI**: probabilità **alta** o **bassa** di arrivare all’AGI;
- asse del **disastro**: probabilità **alta** o **bassa** di un disastro causato dall’AGI;
- una zona intermedia «**forse**».

I 23 esperti sono: Christof Koch, Jaron Lanier, Andrew Ng, Max Tegmark, Yann LeCun, Gary Marcus, Meredith Whittaker, Eliezer Yudkowsky, Margaret Mitchell, Melanie Mitchell, Dan Hendrycks, Geoffrey Hinton, Emily M. Bender, Yoshua Bengio, Sébastien Bubeck, Joy Buolamwini, Nick Bostrom, Rodney Brooks, Timnit Gebru, Alison Gopnik, Sam Altman, Jacob Andreas, Marc Andreessen.

**Citazioni riportate nella matrice**:

| Chi | Citazione |
|---|---|
| **Eliezer Yudkowsky** | un funzionamento **non allineato** a un livello di intelligenza pericoloso «**uccide tutti sulla Terra** e poi non abbiamo un’altra possibilità» |
| **Christof Koch** | GPT-4 mostra chiaramente che esistono **strade diverse verso l’intelligenza** |
| **Marc Andreessen** | l’AI **non è** software o robot assassini che prendono vita e decidono di sterminare l’umanità «come nei film» |
| **Yoshua Bengio** | abbiamo superato una **soglia critica**: le macchine possono conversare e **fingersi umane**, e questo potere può essere usato per fini politici **a scapito della democrazia** |
| **Yann LeCun** | l’amplificazione dell’intelligenza umana da parte delle macchine permetterà un **nuovo rinascimento** o una nuova età dei lumi |
| **Margaret Mitchell** | **ignorare i danni attuali** è un privilegio che alcuni di noi non hanno |
| **Gary Marcus** | c’è ancora **moltissimo lavoro** da fare per avere macchine che **comprendano e ragionino** davvero sul mondo |

> ⚠️ **Da verificare sulla figura.** La posizione di ciascun esperto nei quadranti è visibile solo nella grafica e non viene ricostruita qui. Nell’articolo compaiono due piccoli refusi: «MARK ANDREESSEN» nella citazione, contro «Marc Andreessen» nell’elenco, e «Sante Fe Institute» invece di **Santa Fe Institute**.

> 💡 **Perché la matrice è utile.** Separa due domande che nel dibattito pubblico si confondono: «**arriveremo** all’AGI?» e «**sarebbe pericolosa**?». Si può credere all’AGI senza temere l’apocalisse (LeCun) oppure preoccuparsi soprattutto dei **danni già presenti**, come bias e disinformazione, indipendentemente dall’AGI (Margaret Mitchell).

### **2. Come misurare l’intelligenza di una macchina**

#### **2.1. Il test di Turing**

> 📌 **Test di Turing**. Proposto da **Alan Turing** nel **1950**, misura la capacità di una macchina di mostrare un comportamento intelligente **indistinguibile** da quello umano.
> - Un **giudice umano** conversa in linguaggio naturale con **un umano e una macchina**, senza vederli.
> - Se il giudice **non riesce a distinguere** in modo affidabile la macchina dall’umano, la macchina **supera il test**.

> ⚠️ **Il limite del test.** Il test di Turing misura la **capacità conversazionale**, **non l’intelligenza generale**.


Una prova pubblicata nel 2026 ha usato un protocollo a tre partecipanti: in due studi preregistrati, GPT-4.5 accompagnato da una persona testuale è stato identificato come umano nel 73% delle valutazioni; Llama 3.1 405B ha raggiunto il 56% (Jones e Bergen, 2026, [PNAS](https://doi.org/10.1073/pnas.2524472123)). Il risultato riguarda l’indistinguibilità conversazionale in quel protocollo, non il trasferimento generale tra compiti. Una persona definisce ruolo, stile, obiettivi e vincoli della risposta; non aggiunge capacità intrinseche al modello.

<!-- SOURCE_VISUAL id="IS03-V02" source="Lesson_03_Tools_AITaxonomy_SensorApplications.pdf" page="15" type="tabella" description="Confronto dei risultati del test di Turing a tre partecipanti per GPT-4.5 e Llama 3.1 405B" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261002033559.png)

**Nel 2023** un articolo di *Nature* osservava che i modelli conversazionali stavano mettendo in crisi il test tradizionale e spingevano verso prove di logica e ragionamento visivo. Quell’articolo giornalistico è una fotografia del dibattito dell’epoca; la capacità conversazionale va misurata con protocolli controllati e non basta come indice di intelligenza generale.

- gli attuali sistemi di AI **imitano in modo convincente** la conversazione umana, mettendo in crisi i metodi di valutazione tradizionali;
- **ChatGPT ha superato il test di Turing**;
- questo ha avviato una **corsa** a nuovi metodi di valutazione, capaci di misurare **logica e ragionamento**;
- i compiti di **logica e ragionamento visivo** sono indicati come l’area in cui servono tecniche di valutazione migliori.

![](imgs/Pasted%20image%2020261002033707.png)

#### **2.2. ConceptARC e altri test**

**ConceptARC** propone puzzle di astrazione che richiedono di inferire una regola da pochi esempi. Il benchmark è utile per interrogare le capacità di generalizzazione; i risultati dipendono dalla versione, dal protocollo e dai modelli valutati. La consegna invita a chiedersi: quale caratteristica della mia intelligenza sto usando per risolvere il puzzle?

- un **tentativo casuale**?
- l’**elaborazione visiva**?
- l’**elaborazione linguistica**?
- la **Gestalt**?

> 💡 **Come è fatto un puzzle ARC (nota didattica).** Nei benchmark della famiglia ARC (*Abstraction and Reasoning Corpus*) si vedono poche coppie di griglie colorate «input → output» e bisogna **indovinare la regola** di trasformazione, poi applicarla a una nuova griglia. ConceptARC organizza i puzzle per **concetti** (per esempio «sopra/sotto», «dentro/fuori», «stesso/diverso»). Pochi esempi e una regola astratta: è l’opposto dell’addestramento su milioni di dati tipico della Narrow AI.

Tra gli altri riferimenti si trovano **ARC-AGI-3** e ulteriori test recenti.

![](imgs/Pasted%20image%2020261002033756.png)

![](imgs/Pasted%20image%2020261002033813.png)

![](imgs/Pasted%20image%2020261002033828.png)


ARC-AGI-3, introdotto nel 2026, sposta la valutazione da puzzle statici ad ambienti interattivi: un agente deve esplorare, inferire obiettivi, costruire un modello del mondo e adattarsi a feedback limitati. I punteggi ARC riportati il 22 settembre 2026 mostrano una forte dipendenza dal protocollo. GPT-6 Astra (configurazione Max) ottiene 97,5% su ARC-AGI-1, 95,0% su ARC-AGI-2 e 62,71% su ARC-AGI-3 con harness Standard; il risultato pubblicato per ARC-AGI-3 sale al 99,95% con Provider Adapter, che conserva lo stato di ragionamento tra richieste e applica compattazione. Anche i costi differiscono. Questi numeri descrivono configurazioni e harness specifici e sono uno snapshot datato, non una proprietà atemporale del modello.

<!-- SOURCE_VISUAL id="IS03-V03" source="Lesson_03_Tools_AITaxonomy_SensorApplications.pdf" page="20" type="tabella" description="Confronto ARC-AGI-1, 2 e 3 e differenza tra harness Standard e Provider Adapter" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.3. Il ciclo di vita dei benchmark**

Un benchmark misura una capacità definita da compiti e regole precise. Nel tempo può perdere potere discriminante: i modelli si avvicinano al massimo, i dati possono entrare nei corpora di addestramento e le procedure di ottimizzazione possono favorire il test stesso. Il ciclo tipico è quindi: benchmark informativo → ottimizzazione diffusa → possibile saturazione o contaminazione → revisione o sostituzione.

| Benchmark | Capacità bersaglio | Limite da tenere presente |
|---|---|---|
| HellaSwag (2019) | completamento di scenari di senso comune | la saturazione riduce la capacità di separare i modelli migliori |
| GSM8K (2021) | problemi matematici di livello scolastico | misura una porzione circoscritta del ragionamento |
| MMLU (2021) | conoscenze accademiche ampie | ampiezza non elimina memorizzazione, contaminazione o ottimizzazione specifica |
| SWE-bench Verified (2024) | correzione autonoma di problemi software | un audit del 2026 ha individuato problemi in parte delle specifiche e dei test, riducendo il valore del benchmark come misura di capacità di frontiera ([audit OpenAI, 8 luglio 2026](https://openai.com/index/separating-signal-from-noise-coding-evaluations/)) |

Per confrontare sistemi, occorre riportare versione del benchmark, split, prompt o harness, configurazione del modello, costo e data. Un risultato elevato su un compito statico non equivale a capacità generale. La fotografia numerica del deck è datata 22 settembre 2026 e descrive GPT-6 Astra. Il 17 novembre sono stati rilasciati GPT-6.2 Astra e Sol; i punteggi qui riportati restano un confronto storico relativo a configurazioni specifiche. [Risultati ARC Prize](https://arcprize.org/results), [aggiornamento ufficiale GPT-6.2](https://openai.com/index/introducing-gpt-6-2-astra-sol/).

<!-- SOURCE_VISUAL id="IS03-V01" source="Lesson_03_Tools_AITaxonomy_SensorApplications.pdf" page="16" type="tabella" description="Ciclo di vita dei benchmark con esempi HellaSwag, GSM8K, MMLU e SWE-bench Verified" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->


### **3. Una tassonomia dei metodi di apprendimento**

#### **3.1. Un quadro comune per molte applicazioni**

I sistemi intelligenti sono applicati a problemi molto diversi, ma è possibile ricondurli a un quadro comune. Per scegliere un metodo, chiediamoci quale compito stiamo affrontando e quale capacità vogliamo automatizzare. Una **tassonomia** ci aiuta a organizzare queste possibilità.

![](imgs/Pasted%20image%2020261002033947.png)

![](imgs/Pasted%20image%2020261002034043.png)

#### **3.2. La tassonomia del machine learning**

Possiamo ora costruire una **tassonomia del machine learning** distinguendo i modi in cui il sistema riceve il segnale di apprendimento.

> 💡 **Una tassonomia ridotta.** Uno schema introduttivo raggruppa i metodi in tre rami classici; una classificazione più completa distingue anche self-supervised e semi-supervised e tiene separati paradigma di apprendimento e tipo di compito:
> - **apprendimento supervisionato**: **classificazione** (output discreto) e **regressione** (output continuo);
> - **apprendimento non supervisionato**: **clustering** (e riduzione della dimensionalità);
> - **apprendimento per rinforzo**: un agente impara da **ricompense**.
>

> 📌 **Due assi da tenere distinti.** Il paradigma descrive il segnale disponibile durante l’apprendimento; il compito descrive l’uscita prodotta. Classificazione e regressione sono compiti che possono essere supervisionati; clustering e riduzione dimensionale sono tipicamente non supervisionati; il rinforzo apprende una politica da ricompense. L’apprendimento **self-supervised** ricava gli obiettivi dai dati stessi ed è centrale nel preaddestramento dei modelli fondazionali.

| Paradigma | Segnale | Esempi di compito o uscita |
|---|---|---|
| Supervisionato | etichette desiderate $y$ | classificazione, regressione |
| Non supervisionato | dati $x$ senza etichette esterne | clustering, riduzione dimensionale |
| Self-supervised | obiettivi costruiti da $x$ | predizione del token successivo, mascheramento, apprendimento di rappresentazioni |
| Semi-supervisionato | pochi esempi etichettati e molti non etichettati | classificazione o regressione con supervisione parziale |
| Per rinforzo | ricompense da interazione sequenziale | politiche e decisioni nel tempo |

![](imgs/Pasted%20image%2020261002034122.png)

![](imgs/Pasted%20image%2020261002034135.png)

![](imgs/Pasted%20image%2020261002034145.png)

![](imgs/Pasted%20image%2020261002034154.png)

#### **3.3. Regressione, classificazione e clustering**

![](imgs/Pasted%20image%2020261002034356.png)

| Compito | Che cosa fa | Esempio |
|---|---|---|
| **Regressione** | predice un **output a valori continui**; calcola la relazione probabilistica tra variabili per **previsione**; è il modello statistico che predice **dati numerici** invece di etichette; può individuare **tendenze** nei dati storici | predire il **reddito** di una persona da età, istruzione, … |
| **Classificazione** | predice un **numero discreto di valori**: i dati vengono categorizzati sotto **etichette** e l’etichetta viene predetta per i nuovi dati | email **spam / non spam**; classificazione di input in 2D |
| **Clustering** | **partiziona** il dataset in gruppi, i **cluster**, di elementi simili: punti dello stesso cluster **molto simili**, punti di cluster diversi **diversi**; trova raggruppamenti in dati **non etichettati** | punti nel piano 2D |

In formula (nota didattica), con il modello visto come funzione $\text{Out} = F(X)$ (rappresentazione interna del modello):

$$\text{regressione: } F: \mathbb{R}^N \to \mathbb{R} \qquad\qquad \text{classificazione: } F: \mathbb{R}^N \to \{1, 2, \dots, C\}$$

Nel clustering non esiste un output «giusto» da imparare: l’algoritmo assegna a ogni punto un gruppo $k \in \{1, \dots, K\}$ basandosi solo sulle **somiglianze** tra i punti.

> ⚠️ **Classificazione contro clustering.** Entrambi producono gruppi, ma la classificazione **impara da esempi etichettati** (le classi sono note in anticipo), mentre il clustering **scopre** i gruppi in dati **senza etichette**.

![](imgs/Pasted%20image%2020261002034415.png)

![](imgs/Pasted%20image%2020261002034429.png)
#### **3.4. Apprendimento per rinforzo**

> 📌 **Reinforcement learning**. I modelli di apprendimento per rinforzo usano dinamiche opposte, **ricompense e punizioni**, per «rinforzare» diversi tipi di conoscenza. È una tecnica **sempre più diffusa** nelle soluzioni di AI moderne.

**Esempio: il robot aspirapolvere**. Il robot può sviluppare comportamenti come:

- **passeggiata casuale** (*random walk*);
- andare **il più dritto possibile**;
- **spirale**.

Politiche e ricompense diverse:

| Problema | Politica | Ricompensa |
|---|---|---|
| pulire una stanza | seguire **file parallele** nella stanza | pulire una **superficie più grande** con la stessa batteria |
| uscire da un labirinto | tenere **la mano sul muro** (meglio che girare a caso) | **uscire** dal labirinto con **meno passi** |

**Obiettivo**: ottenere **la massima ricompensa possibile**.

![](imgs/Pasted%20image%2020261002034507.png)

![](imgs/Pasted%20image%2020261002034522.png)

![](imgs/Pasted%20image%2020261002034626.png)

Un’illustrazione generata automaticamente di un labirinto mostra un percorso arancione che attraversa i muri. L’esempio ricorda che un’immagine plausibile non garantisce una geometria corretta: i contenuti generati vanno verificati prima di usarli come dimostrazione o riferimento.

**Gli elementi**:

| Elemento | Significato |
|---|---|
| **Agente** | l’entità che prende le decisioni per **massimizzare la ricompensa** |
| **Stato** $s$ | la rappresentazione della condizione corrente dell’ambiente, osservata dall’agente |
| **Rete neurale profonda** (DNN) | usata dall’agente per **approssimare la politica** $\pi$, che associa stati e azioni |
| **Politica** $\pi_\theta(s, a)$ | funzione che dà la **probabilità di scegliere l’azione** $a$ nello stato $s$; $\theta$ sono i parametri della rete |
| **Azione** | la decisione o il movimento che l’agente compie in base alla politica |
| **Ambiente** | il sistema esterno in cui l’agente opera |
| **Ricompensa** | il segnale di **feedback** dato all’agente in base all’azione, che guida l’apprendimento |
| **Osservazione** | l’agente riceve il **nuovo stato** dall’ambiente dopo aver eseguito l’azione |

> 📌 **L’obiettivo in formula (nota didattica).** A ogni passo $t$ l’agente osserva $s_t$, sceglie $a_t \sim \pi_\theta(s_t, \cdot)$, riceve la ricompensa $r_{t+1}$ e il nuovo stato $s_{t+1}$. Si vuole massimizzare la **ricompensa cumulativa scontata** attesa:
> $$J(\theta) = \mathbb{E}_{\pi_\theta}\!\left[\sum_{t=0}^{\infty} \gamma^{t}\, r_{t+1}\right], \qquad 0 \le \gamma < 1$$
> Il fattore di sconto $\gamma$ pesa meno le ricompense lontane nel tempo.

**Esempio: il braccio robotico**. Un braccio con **più giunti** deve **afferrare un oggetto** posto in una posizione casuale su un tavolo; è comandato dalle **coppie** applicate ai giunti e riceve ricompense in base a quanto si avvicina all’oggetto e lo afferra.

![](imgs/Pasted%20image%2020261002034715.png)

| Componente | Nel problema del braccio |
|---|---|
| **Stato** $s$ | posizione e orientamento dell’**end-effector** (la pinza) e posizione dell’oggetto: angoli e velocità dei giunti, posizione 3D dell’oggetto, distanza pinza-oggetto |
| **Azione** $a$ | le **coppie** applicate a ciascun giunto |
| **Politica** $\pi_\theta(s,a)$ | **stocastica**: distribuzione di probabilità sui valori di coppia; **deterministica**: la coppia esatta da applicare |
| **Ricompensa** $R$ | **+10** se afferra l’oggetto; **+1** se la pinza si avvicina; **−1** per coppie eccessive (efficienza); **−5** se l’oggetto cade |
| **Ambiente** | un simulatore fisico come **PyBullet**, **MuJoCo** o **Gazebo** |

> 💡 **La ricompensa è una scelta di progetto.** La penalità sulle coppie eccessive orienta il braccio verso un’afferrata **efficiente**; il +1 di avvicinamento dà un segnale anche prima del successo finale, che altrimenti sarebbe raro. Una ricompensa progettata male porta l’agente a ottimizzare la cosa sbagliata: mostra il ruolo delle scelte progettuali nel definire l’obiettivo da ottimizzare.

#### **3.5. Tipi di apprendimento ed etichette**

| Tipo | Dati di addestramento |
|---|---|
| **Supervisionato** (induttivo) | includono gli **output desiderati** (etichette) |
| **Non supervisionato** | **non** includono gli output desiderati |
| **Semi-supervisionato** | includono **pochi** output desiderati |
| **Per rinforzo** | **ricompense** ottenute da sequenze di azioni |

Un’altra classificazione distingue i tipi di apprendimento in base al modo in cui il sistema riceve dati ed etichette.

![](imgs/Pasted%20image%2020261002034750.png)

### **4. Apprendimento deduttivo, induttivo e trasduttivo**

#### **4.1. Regole o dati?**

Una distinzione fondamentale riguarda il punto di partenza: si **impara dai dati** oppure si **applicano regole**? Lo schema del *Knowledge Base* di **William M. K. Trochim** mette a confronto questi due percorsi:

| | **Deduttivo** | **Induttivo** |
|---|---|---|
| Punto di partenza | regole e conoscenze esplicite, poi applicate a un caso | esempi osservati, senza regole di dominio esplicite; le scelte di modello e obiettivo incorporano comunque assunzioni |
| Diffusione | — | **la maggior parte dei metodi di AI** |

> 💡 **Lo schema di Trochim (nota didattica).** Il ragionamento **deduttivo** va «dall’alto in basso»: teoria → ipotesi → osservazioni → conferma. Quello **induttivo** va «dal basso in alto»: osservazioni → individuazione di schemi → ipotesi provvisoria → teoria. La direzione delle frecce rende intuitiva la differenza fra i due percorsi.

![](imgs/Pasted%20image%2020261002034814.png)

![](imgs/Pasted%20image%2020261002034826.png)

![](imgs/Pasted%20image%2020261002034943.png)

#### **4.2. Due esempi mnemonici**

> 📌 **Apprendimento deduttivo**. Una tecnica di AI che **parte da un insieme di regole** e le applica per prendere decisioni su dati nuovi; poi può **inferire regole nuove**, più efficienti nel contesto in cui vanno applicate. È l’«**esperto che applica regole esplicite a nuovi casi**».

L’esempio mnemonico: la **nonna** ti dà le regole per scegliere un buon fidanzato. Se sei d’accordo, **erediti la conoscenza** della sua esperienza e la applichi alla scelta del nuovo partner; poi puoi anche **adattare le regole** ai tempi moderni.

> 📌 **Apprendimento induttivo**. Inferisce un modello generale da esempi input-output. Non richiede regole di dominio scritte in anticipo, ma usa sempre un bias induttivo definito dalla rappresentazione, dal modello e dall’obiettivo.

L’esempio mnemonico: non ricevi una regola esplicita e impari dai casi osservati; il modello incorpora comunque assunzioni. «Le ultime 10 volte che sono entrato in tangenziale a Milano alle 17:30 sono rimasto in coda» → **nuova regola generale**: evitare di partire nelle ore di traffico intenso. L’immagine è James Dean in *Gioventù bruciata* (*Rebel Without a Cause*, 1955): il ribelle che non accetta regole ricevute.

#### **4.3. Trasduzione**

Dal libro *The Nature of Statistical Learning Theory*:

| Processo | Definizione |
|---|---|
| **Induzione** | ricavare **la funzione** dai dati disponibili |
| **Deduzione** | ricavare i **valori della funzione data** nei punti di interesse |
| **Trasduzione** | ricavare i **valori della funzione sconosciuta** nei punti di interesse **direttamente dai dati** |

![](imgs/Pasted%20image%2020261002035051.png)

> 💡 **In una riga (nota didattica).** Induzione e poi deduzione: dati → **modello generale** → risposta nel punto. Trasduzione: dati → risposta nel punto, **senza passare** da un modello generale. Il libro citato è di **Vladimir Vapnik**.

La trasduzione va **dal particolare al particolare**, da punti di dati a punti di dati. Esempi:

- **k-Nearest Neighbors**: un algoritmo che **non modella** i dati di addestramento, ma li **usa direttamente** ogni volta che serve una predizione;
- **linguistica**: un insieme di regole che trasforma esempi di una lingua in un’altra; molti compiti di **NLP** si possono vedere come trasduzione, perché il modello converte una stringa in un’altra;
- **predizione di sequenze**: un modello che produce un passo temporale di output per ogni passo di input, per esempio le **reti neurali ricorrenti**.

#### **4.4. L’apprendimento induttivo in dettaglio**

**Dati: esempi di una funzione** $(X, F(X))$. Un learner induttivo non parte da regole di dominio esplicite, ma non è privo di assunzioni: classe di modelli, rappresentazione, feature, obiettivo e procedura di addestramento determinano il **bias induttivo**, cioè le preferenze che guidano la generalizzazione.

- **Caso monodimensionale**: coppie come $X = 0{,}2 \to Y = 32$ e $X = 0{,}5 \to Y = 52$, …
- **Caso N-dimensionale**: ogni input (un vettore, per esempio un’immagine) ha un’etichetta, come $Y = 1$, $Y = 1$, $Y = 2$.

**Obiettivo: creare una funzione di predizione** $F(X)$ per esempi nuovi:

| Tipo di $F(X)$ | Compito |
|---|---|
| **discreta** | **classificazione**: $\text{out} = F(X)$ è un intero |
| **continua** | **regressione** |
| $F(X) = \text{Probabilità}(X)$ | **stima di probabilità**; esempio nel piano altezza (cm) – peso (kg) |

![](imgs/Pasted%20image%2020261002035244.png)

![](imgs/Pasted%20image%2020261002035321.png)

**Vantaggi dell’apprendimento induttivo**:

- **alleggerisce il collo di bottiglia dell’acquisizione della conoscenza**: niente regole, niente equazioni, solo dati;
- **non richiede ingegneri della conoscenza**;
- è **scalabile** nella costruzione della base di conoscenza;
- è **adattivo**: si adatta alle condizioni che cambiano;
- **migra facilmente** a domini nuovi.

**Generalizzazione**. Il modello addestrato può **estendere** la capacità di risolvere il problema dalla specifica applicazione a casi più generali, **SE** (e «ci sono molti se»):

- il dataset è **buono e completo**;
- il metodo di apprendimento e il modello sono **abbastanza potenti**;
- ecc.

> ⚠️ **I «se» della generalizzazione.** L’induzione non garantisce nulla fuori dai dati visti: se il dataset non copre certe situazioni, il modello non può inferire in modo affidabile casi assenti dai dati. Perciò qualità, copertura e partizione dei dati vanno valutate in rapporto all’uso previsto.

#### **4.5. Applicare i concetti a casi d’uso**

Tre casi aiutano a distinguere i segnali di apprendimento: classificare componenti industriali accettabili o difettosi da immagini etichettate è apprendimento induttivo; applicare regole di sicurezza definite da esperti a misure correnti è ragionamento deduttivo; un robot che esplora un magazzino e riceve ricompense per il raggiungimento della destinazione apprende per rinforzo.

#### **4.6. Tre casi per distinguere apprendimento e ragionamento**

| Caso | Segnale disponibile | Processo prevalente | Perché |
|---|---|---|---|
| Classificare componenti industriali da immagini con etichette accettabile/difettoso | esempi input-output | induzione supervisionata | dai campioni si stima una funzione da applicare a immagini nuove |
| Applicare regole esplicite su vibrazione e temperatura a una macchina | conoscenze e regole di un esperto più misure correnti | deduzione | le regole producono una conclusione specifica senza addestrare un modello |
| Imparare a navigare in un magazzino ricevendo ricompense per destinazione, collisioni e mosse | feedback da interazioni ripetute | apprendimento per rinforzo | le ricompense guidano l’ottimizzazione di una politica di azione |

<!-- SOURCE_VISUAL id="IS03-V05" source="Lesson_03_Tools_AITaxonomy_SensorApplications.pdf" page="52-57" type="tabella" description="Tre quesiti applicativi per distinguere induzione supervisionata, deduzione e apprendimento per rinforzo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **5. Valutazione di un sistema intelligente**

Dopo aver scelto un metodo e addestrato un modello, resta la domanda decisiva: **funziona per l’applicazione a cui è destinato?** La valutazione è articolata e considera più aspetti:

**Obiettivo della valutazione**:

- garantire che il sistema **soddisfi le aspettative di prestazione**;
- garantire **sicurezza e affidabilità**;
- individuare le **aree da migliorare**.

| Aspetto | Che cosa valutare |
|---|---|
| **Metriche** | accuratezza, precisione, recall, F1, curva ROC e AUC, MAE, MSE e RMSE; usabilità, soddisfazione, ergonomia, equità e bias |
| **Metodi di validazione** | split training/validation/test, cross-validation e bootstrap; split per gruppi o soggetti quando esistono misure ripetute e split temporali per previsioni e processi non stazionari |
| **Benchmarking** | confronto con **modelli precedenti** o con le **prestazioni umane**; standard e benchmark di settore; **classifiche pubbliche** (leaderboard) |
| **Prestazioni reali** | studi con utenti e **test sul campo**; valutazione di **robustezza e generalizzabilità**; **stress test** |
| **Monitoraggio continuo** | rilevamento del **data drift**; strategie di **riaddestramento**; **allarmi** di degrado delle prestazioni |
| **Etica e conformità** | analisi di **bias ed equità**; valutazione di **privacy e sicurezza**; verifiche di conformità normativa |

> 📌 **Le metriche principali (nota didattica).** Per un classificatore binario, con veri positivi $TP$, falsi positivi $FP$, veri negativi $TN$ e falsi negativi $FN$:
> $$\text{Accuracy} = \frac{TP + TN}{TP + TN + FP + FN} \qquad \text{Precision} = \frac{TP}{TP + FP} \qquad \text{Recall} = \frac{TP}{TP + FN}$$
> $$F_1 = 2 \cdot \frac{\text{Precision} \cdot \text{Recall}}{\text{Precision} + \text{Recall}}$$
> Per una regressione con valori veri $y_i$ e predetti $\hat{y}_i$ su $n$ esempi:
> $$\text{MAE} = \frac{1}{n}\sum_{i=1}^{n} |y_i - \hat{y}_i| \qquad \text{MSE} = \frac{1}{n}\sum_{i=1}^{n} (y_i - \hat{y}_i)^2 \qquad \text{RMSE} = \sqrt{\text{MSE}}$$
> La curva **ROC** traccia il tasso di veri positivi contro il tasso di falsi positivi al variare della soglia; l’**AUC** è l’area sotto questa curva.

> 💡 **Perché non basta l’accuratezza.** Se il 99% delle email non è spam, un classificatore che risponde sempre «non spam» ha accuratezza 99% ma recall 0 sullo spam. Precisione, recall e F1 mostrano ciò che l’accuratezza nasconde quando le classi sono **sbilanciate**. Lo stesso messaggio torna in §8: «non considerare solo l’accuratezza».

### **6. AI e sensori**

L’AI può elaborare le misure prodotte da **sensori intelligenti**, contribuire al **controllo ambientale** e stimare grandezze tramite **sensori virtuali**.
#### **6.1. Sensori intelligenti e reti di sensori**

**Sensori intelligenti**, temi principali:

- sistemi **multisensore eterogenei**;
- **analisi dei dati** dei sensori;
- **diagnosi**;
- **tolleranza ai guasti**;
- **autocalibrazione**;
- **adattività**;
- **gestione**.

**Esempio: Nest.** Nest Labs è stata fondata nel **2010** da **Tony Fadell** e **Matt Rogers**, ex ingegneri Apple; il prodotto di punta era il **Nest Learning Thermostat** (dal 2011), un termostato che impara le abitudini degli utenti. **Google** ha acquisito Nest Labs per **3,2 miliardi di dollari** nel **gennaio 2014**, quando l’azienda aveva 280 dipendenti.

![](imgs/Pasted%20image%2020261002035942.png)

**Reti di sensori**, temi principali:

- **struttura** e **funzioni**;
- **adattività** e **gestione**;
- **analisi distribuita** dei dati;
- **tolleranza ai guasti** e **diagnosi**.

![](imgs/Pasted%20image%2020261002040013.png)

#### **6.2. Strumentazione e sensori virtuali**

**Strumentazione e misure**:

- acquisizione ed elaborazione di misure da sensori in **infrastrutture avanzate e adattive**;
- **fusione di dati** multisensoriali;
- **sensori virtuali** basati sull’AI: l’AI offre alternative **praticabili ed economiche** a strumenti di misura fisici **costosi o poco pratici**.

> 📌 **Sensore virtuale**. Un sistema **software** che **stima una grandezza fisica** usando i dati di **più sensori reali** e **modelli computazionali**. Usa le informazioni disponibili da altre misure e dai parametri di processo per calcolare una **stima** della grandezza di interesse. **Sostituisce o affianca** i sensori fisici quando la misura diretta è **costosa, poco pratica o impossibile**.

**Flusso di lavoro di un sensore virtuale basato sull’AI**, dai sensori fisici (PS) ai sensori virtuali (VS):

1. **raccolta e armonizzazione** dei dati;
2. **estrazione delle feature**;
3. **modellazione e stima**;
4. **validazione e rifinitura**.

![](imgs/Pasted%20image%2020261002040056.png)ì

**Vantaggi principali**:

- **riduzione dei costi**: servono meno sensori fisici;
- **maggiore affidabilità**: ridondanza e correzione degli errori;
- **fusione dei dati** migliore: integrazione di più fonti;
- **capacità predittive** migliori: stime basate sul ML.

**Applicazioni tipiche**:

| Settore | Esempio |
|---|---|
| monitoraggio di **processi industriali** | stima di **portata di gas** e **temperatura** |
| **automotive** | monitoraggio virtuale di **velocità** e **pressione degli pneumatici** |
| **monitoraggio ambientale** | **qualità dell’aria**, rilevamento di **contaminazione dell’acqua** |
| **Industria 4.0** | **manutenzione predittiva** |

**Esempio**: un sensore virtuale basato sull’AI che stima lo **stato di carica** (SOC, *State Of Charge*) di una batteria a partire da **tensione, corrente e temperatura**.

![](imgs/Pasted%20image%2020261002040150.png)

In formula (nota didattica), un sensore virtuale è ancora una funzione appresa dai dati:

$$\widehat{\text{SOC}}(t) = F\big(V(t),\, I(t),\, T(t)\big)$$

dove $F$ si addestra su dati in cui il SOC è stato misurato con metodi di laboratorio costosi, per poi stimarlo in esercizio con sensori economici.

> 💡 **Perché il SOC non si misura direttamente.** La carica residua di una batteria non ha un sensore dedicato: dipende in modo non lineare da tensione, corrente assorbita, temperatura e invecchiamento. Un modello che unisce queste misure è l’esempio tipico di grandezza che «si può solo stimare».

### **7. Strumenti e ambienti di lavoro**

#### **7.1. MATLAB, Colab e Keras**

Qualche nota per **preparare gli strumenti** delle applicazioni successive, che saranno ripresi in dettaglio più avanti. Gli strumenti principali  sono:

- **MATLAB**: moltissimi strumenti e funzioni;
- **Colab**: server online, TPU, …;
- **Keras**: la libreria Python per il deep learning.

Offrono il **miglior compromesso** tra usabilità e complessità, con una buona curva di apprendimento, e supportano Keras e molte **reti neurali pre-addestrate**.

**Strumento 1: MATLAB**:

- è il **primo strumento** : non solo machine learning, ma molto di più, con **migliaia di funzioni** ben documentate e potenti;
- gli studenti UNIMI possono scaricare una **licenza gratuita** (istruzioni sul portale d’Ateneo, o cercando «UNIMI MATLAB»), anche per installazioni su macchine offline;
- conviene scaricare **l’ultima versione**, perché i toolbox migliorano rapidamente;
- installare **tutti i toolbox**; se mancano spazio su disco o banda, almeno quelli principali .

| Toolbox MATLAB per attività ingegneristiche |
|---|
| Deep Learning Toolbox |
| Fuzzy Logic Toolbox |
| Statistics and Machine Learning Toolbox |
| Image Processing Toolbox |
| Signal Processing Toolbox |
| Computer Vision Toolbox |

Per i **progetti futuri** si possono inoltre scegliere altri toolbox disponibili gratuitamente agli studenti.

**Strumento 2: Google Colab**:

- **Colab** (*Colaboratory*) è un ambiente di **notebook Jupyter gratuito**, senza configurazione, che gira **interamente nel cloud**;
- permette di **scrivere ed eseguire codice**, **salvare e condividere** le analisi e accedere a **risorse di calcolo potenti**, tutto gratis dal browser;
- è **molto utile** per ottimizzare il tempo senza perdersi in dettagli tecnici.

> ⚠️ **Colab non è obbligatorio**. Richiede un account Google per salvare dati e codice. Le alternative, meno immediate, sono:
> - usare **MATLAB** per tutto;
> - installare strumenti locali (Jupyter Notebook, Anaconda, Python, …), probabilmente con una **GPU**;
> - creare un **account Google dedicato** senza informazioni personali.
>
> La scelta dell’ambiente dipende dall’hardware disponibile, dalle dipendenze e dai requisiti di privacy.

**Keras**:

- la **libreria Python per il deep learning**: un’API di alto livello per reti neurali, scritta in Python e capace di girare sopra **TensorFlow, CNTK o Theano**, pensata per una **sperimentazione rapida**;
- motto: «dall’idea al risultato con il **minor ritardo possibile**»;
- **non serve scaricarla**: è già disponibile in MATLAB e in Colab.

> 💡 CNTK e Theano sono progetti storici. Keras 3 è un’API di alto livello con backend TensorFlow, JAX o PyTorch; va scelta la versione compatibile con l’ambiente di esecuzione.

<!-- SOURCE_VISUAL id="IS03-V32" source="Lesson_03_Tools_AITaxonomy_SensorApplications.pdf" page="70" type="immagine" description="Apertura della sezione sugli strumenti software" reason="Illustrazione della sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Anaconda, Jupyter e gli IDE**

**Anaconda è una distribuzione possibile, non un requisito.** Include strumenti e librerie per calcolo scientifico e gestione degli ambienti; Miniforge/conda-forge è un’alternativa distinta.

- uno strumento potente per il **calcolo scientifico** e l’**analisi dei dati**, che semplifica la **gestione dei pacchetti** e il deployment; molto usato da data scientist, ricercatori e sviluppatori;
- è una **distribuzione di Python e R**;
- include **molte librerie preinstallate** per data science, machine learning e altre applicazioni scientifiche;
- ha un ottimo **sistema di gestione dei pacchetti**: installare, aggiornare e gestire versioni diverse di pacchetti e dipendenze;
- **Anaconda Navigator** è l’applicazione desktop per gestire applicazioni, pacchetti e **ambienti** senza riga di comando.

**Jupyter Notebook e Colab**: Colab è **pronto all’uso** e più **indipendente dall’hardware** (per esempio si può accedere a un acceleratore gestito), con limiti di disponibilità e risorse che dipendono dal servizio.

**Oppure un IDE** (*Integrated Development Environment*):

| IDE | Caratteristiche |
|---|---|
| **Visual Studio Community** | versione **gratuita** e completa di Visual Studio per studenti, contributori open source e sviluppatori individuali; ottimi strumenti di **debug**. Strumenti utili per i sistemi intelligenti: GitHub Copilot, ML.NET Model Builder, IntelliCode, estensione Azure Machine Learning, TensorFlow.NET Tools, Bito (assistente alternativo a Copilot), **Python Tools for Visual Studio** (estensione open source che trasforma Visual Studio in un IDE Python, con NumPy, Pandas e scikit-learn) |
| **Visual Studio Code** | IDE **gratuito, leggero e flessibile**, con ottimo supporto a **Python**, estensioni e flussi di lavoro per lo sviluppo di AI: GitHub, estensioni, **agenti di programmazione**, Copilot, «tutto a un clic» |

![](imgs/Pasted%20image%2020261002040833.png)

#### **7.3. Pazienza e basi teoriche**

> 📌 **Teoria e pazienza.** La competenza richiede di comprendere ipotesi, dati, valutazione e ragioni per cui un metodo funziona o fallisce; la familiarità con i passaggi di uno strumento da sola non basta.

#### **7.4. Un flusso software aggiornato e riproducibile**

Nel settembre 2026 NVIDIA ha annunciato un accordo per acquisire Hugging Face per circa 12,93 miliardi di dollari. Al 20 novembre 2026 si tratta ancora di un’operazione annunciata, con chiusura prevista nella prima metà del 2027; l’annuncio dichiara che la piattaforma resterà aperta e non vincolata all’hardware NVIDIA. La neutralità futura dell’ecosistema resta una questione da osservare. [Annuncio NVIDIA](https://nvidianews.nvidia.com/news/nvidia-to-acquire-hugging-face).

Una configurazione di lavoro attuale combina Python con NumPy, pandas e Matplotlib per dati e visualizzazione; scikit-learn per il machine learning classico; PyTorch o Keras per il deep learning; Hugging Face per modelli e artefatti preaddestrati; MATLAB quando è utile un ambiente integrato di calcolo ingegneristico. Per modelli locali si possono usare runtime come Ollama o LM Studio, verificando memoria, licenza, quantizzazione e lunghezza del contesto.

Gli ambienti `venv`, `uv` e Miniforge/conda-forge isolano dipendenze e configurazioni. Un progetto riproducibile conserva in Git il codice e i file di ambiente, registra versioni dei dati, split, semi casuali e parametri degli esperimenti; segreti, pesi e grandi artefatti generati restano fuori dal repository salvo scelta esplicita. Jupyter/Colab accelera le prove, mentre un IDE come Visual Studio Code è adatto a progetti con più file. I notebook cloud richiedono autorizzazione prima di caricare dati personali, riservati o industriali.

Durante sviluppo e laboratorio si possono usare assistenti di programmazione quando l’attività lo consente, ma chi consegna resta responsabile di correttezza, test, sicurezza e licenze e deve saper spiegare il codice. In una prova d’esame, chatbot, ricerca web e materiali esterni sono esclusi salvo diversa indicazione esplicita.

<!-- SOURCE_VISUAL id="IS03-V04" source="Lesson_03_Tools_AITaxonomy_SensorApplications.pdf" page="71" type="diagramma" description="Flusso riproducibile dai dati e sensori fino a esplorazione, addestramento, valutazione e distribuzione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **8. Come consolidare la conoscenza**

Per interiorizzare i metodi occorre collegare ipotesi, esempi, applicazioni e confronto tra alternative. Nel machine learning è utile:

1. **studiare la teoria** e le relative **ipotesi di lavoro**;
2. studiare **quanti più casi d’uso possibile**, per allargare il proprio punto di vista;
3. **applicare la teoria** ad applicazioni reali;
4. fare ogni volta che si può una **discussione e un confronto onesti**: chiedersi «**perché questo e non quello?**», per esempio «**che cosa sta imparando davvero il modello?**».

Inoltre:

- costruirsi un **portfolio** di modelli e metodi di apprendimento, il più ampio possibile;
- tenersi aggiornati sullo **stato dell’arte** (SOA) e sulle tendenze;
- **scegliere sempre tra soluzioni diverse**;
- **non considerare solo l’accuratezza**: c’è un’ampia gamma di **figure di merito** da considerare nel progetto (applicazioni successive);
- migliorare il progetto **partendo dalle esperienze e dalle scoperte precedenti**: «**sulle spalle dei giganti**». L’analisi dello stato dell’arte è **fondamentale**.

> 💡 La conoscenza del problema aiuta a scegliere dati, metodi e metriche pertinenti e a progettare confronti equi tra soluzioni.

### **9. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Narrow AI** / **AGI** | capacità circoscritte, anche multimodali / capacità generali trasferibili tra domini, senza definizione condivisa |
| **Probabilità di AGI** / **probabilità di disastro** | i due assi separati della AI Apocalypse Matrix |
| **Test di Turing** / **ConceptARC** | abilità conversazionale / pensiero astratto su pochi esempi |
| **Regressione** / **classificazione** | output continuo / output discreto (etichette) |
| **Classificazione** / **clustering** | classi note e dati etichettati / gruppi scoperti in dati non etichettati |
| **Supervisionato** / **semi-supervisionato** / **non supervisionato** / **self-supervised** / **rinforzo** | etichette / poche etichette / nessuna etichetta esterna / obiettivi ricavati dai dati / ricompense da interazione |
| **Politica stocastica** / **deterministica** | distribuzione di probabilità sulle azioni / azione esatta |
| **Deduttivo** / **induttivo** | dalle regole ai dati («la nonna») / dai dati alla regola («la tangenziale alle 17:30») |
| **Induzione** / **trasduzione** | dati → funzione generale / dati → valori nei punti di interesse, senza modello generale (k-NN) |
| **Precision** / **recall** | quanti positivi predetti sono veri / quanti positivi veri sono trovati |
| **Sensore fisico** / **sensore virtuale** | misura diretta / stima software da altre misure e modelli |
| **Colab** / **MATLAB** / **IDE locale** | notebook nel cloud con account Google / ambiente con toolbox e licenza UNIMI / sviluppo locale con gestione degli ambienti |

### **10. Sintesi della lezione**

> ✅ **Punti principali**.
> - **Preparare gli strumenti**: MATLAB con i toolbox principali, Colab (o le sue alternative), Anaconda e Jupyter, un IDE, Keras.
> - In questo campo, per creare competenze serve l’**interiorizzazione**: teoria, molti casi d’uso, applicazione e confronto critico.
> - La **Narrow AI** copre capacità circoscritte, anche con input multimodali. L’**AGI** resta priva di una definizione condivisa; le previsioni sulla sua comparsa e sui rischi associati richiedono un criterio di valutazione esplicito.
> - Il **test di Turing** misura l’indistinguibilità conversazionale secondo uno specifico protocollo. ConceptARC e ARC-AGI esplorano l’astrazione e l’interazione in compiti differenti; i risultati vanno letti con data, versione e protocollo.
> - **Tassonomia**: regressione e classificazione (supervisionate), clustering (non supervisionato), apprendimento per rinforzo con agente, stato, azione, politica e ricompensa.
> - **Deduttivo** contro **induttivo**: la maggior parte dei metodi di AI è induttiva e generalizza solo se dati e modello sono adeguati; la **trasduzione** va dal particolare al particolare.
> - La **valutazione** comprende metriche quantitative e qualitative, validazione, benchmark, test sul campo, monitoraggio e requisiti etici e normativi.
> - **AI e sensori**: sensori intelligenti, reti di sensori, strumentazione avanzata e **sensori virtuali**, come la stima dello stato di carica di una batteria.
