## **Lezione 3: Narrow AI, tassonomia dell’apprendimento, sensori intelligenti e strumenti**

In questa lezione il percorso parte da una distinzione fondamentale: l’AI ristretta, progettata per compiti specifici, e l’AGI, un’intelligenza generale ancora ipotetica. Da qui nasce la domanda su come valutare le capacità di una macchina. Passiamo poi ai principali modi di apprendere — regressione, classificazione, clustering, rinforzo, deduzione, induzione e trasduzione — e vediamo come valutare i sistemi e applicarli ai sensori. Chiudiamo con gli strumenti di lavoro e un metodo per interiorizzare la teoria.

Approfondimento collegato: [The AI Apocalypse Matrix](Lesson_03_The_AI_Apocalypse_Matrix.pdf), pubblicato da *IEEE Spectrum* nell’agosto 2023. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Il percorso della lezione**

Il percorso si sviluppa in quattro passaggi: distinguere AI ristretta e generale; capire come si misurano le capacità delle macchine; ordinare i metodi di apprendimento e i criteri di valutazione; collegare l’AI ai sensori e agli strumenti con cui si progetta.


### **1. Narrow AI e General AI**

#### **1.1. Due punti di vista**

Quando si parla di intelligenza artificiale, è utile distinguere **due prospettive**: la **Narrow AI**, progettata per compiti circoscritti, e la **General AI**, capace in ipotesi di affrontare una gamma ampia di attività.

![](imgs/Pasted%20image%2020261002032915.png)

![](imgs/Pasted%20image%2020261002032923.png)

#### **1.2. Narrow AI**

![](imgs/Pasted%20image%2020261002033107.png)

> 📌 **Narrow AI**, detta anche **Artificial Narrow Intelligence** (ANI) o **Weak AI**. Indica sistemi di AI **progettati e addestrati** per svolgere **un compito specifico** o un insieme ristretto di compiti, di solito in un **dominio limitato**.
> - Esempio: un modello addestrato a riconoscere **immagini di gatti** sa fare solo quello e **non generalizza** ad altri tipi di immagini o di compiti.
> - È oggi la forma **predominante** di AI: riconoscimento vocale, riconoscimento di immagini, elaborazione del linguaggio naturale.
> - È tipicamente **monomodale**: solo immagini, solo suoni, e così via.

**Che cosa manca alla Narrow AI**:

- **autoconsapevolezza**;
- **coscienza**;
- **emozioni**;
- un’**intelligenza genuina** paragonabile a quella umana;
- e naturalmente la **Gestalt**: la capacità di cambiare dinamicamente il **contesto**, il **livello di dettaglio** e il **livello di astrazione** per capire meglio i dati, secondo la propria esperienza e conoscenza (Lezione 02, §6).

La Narrow AI offre vantaggi pratici nei compiti ben definiti, ma presenta anche sfide che incontreremo durante il corso.

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

> 💡 **Leggere le dichiarazioni.** Le frasi al condizionale e le stime in «migliaia di giorni» non sono previsioni verificabili: fanno parte della comunicazione di aziende in competizione. È lo stesso invito alla lettura critica delle opinioni della Lezione 02, §7.

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

![](imgs/Pasted%20image%2020261002033559.png)

**Test recenti** (un articolo di *Nature* del 2023 osserva che):

- gli attuali sistemi di AI **imitano in modo convincente** la conversazione umana, mettendo in crisi i metodi di valutazione tradizionali;
- **ChatGPT ha superato il test di Turing**;
- questo ha avviato una **corsa** a nuovi metodi di valutazione, capaci di misurare **logica e ragionamento**;
- i compiti di **logica e ragionamento visivo** sono indicati come l’area in cui servono tecniche di valutazione migliori.

> 💡 **Precisazione (nota didattica).** L’articolo citato (*ChatGPT broke the Turing test — the race is on for new ways to assess AI*) è un articolo giornalistico della sezione notizie di *Nature*, non uno studio sperimentale. Il senso è che il test di Turing, nella sua forma conversazionale, **non distingue più** i modelli linguistici dagli umani e quindi non basta come misura.

![](imgs/Pasted%20image%2020261002033707.png)

#### **2.2. ConceptARC e altri test**

**Un test di pensiero astratto che batte le macchine**: finora i sistemi di AI **non hanno raggiunto prestazioni umane** nel test **ConceptARC**. Le propongono di provarlo e chiedono: quale caratteristica della mia intelligenza sto usando per risolvere il puzzle?

- un **tentativo casuale**?
- l’**elaborazione visiva**?
- l’**elaborazione linguistica**?
- la **Gestalt**?

> 💡 **Come è fatto un puzzle ARC (nota didattica).** Nei benchmark della famiglia ARC (*Abstraction and Reasoning Corpus*) si vedono poche coppie di griglie colorate «input → output» e bisogna **indovinare la regola** di trasformazione, poi applicarla a una nuova griglia. ConceptARC organizza i puzzle per **concetti** (per esempio «sopra/sotto», «dentro/fuori», «stesso/diverso»). Pochi esempi e una regola astratta: è l’opposto dell’addestramento su milioni di dati tipico della Narrow AI.

Tra gli altri riferimenti si trovano **ARC-AGI-3** e ulteriori test recenti.

![](imgs/Pasted%20image%2020261002033756.png)

![](imgs/Pasted%20image%2020261002033813.png)

![](imgs/Pasted%20image%2020261002033828.png)

### **3. Una tassonomia dei metodi di apprendimento**

#### **3.1. Un quadro comune per molte applicazioni**

I sistemi intelligenti sono applicati a problemi molto diversi, ma è possibile ricondurli a un quadro comune. Per scegliere un metodo, chiediamoci quale compito stiamo affrontando e quale capacità vogliamo automatizzare. Una **tassonomia** ci aiuta a organizzare queste possibilità.

![](imgs/Pasted%20image%2020261002033947.png)

![](imgs/Pasted%20image%2020261002034043.png)

#### **3.2. La tassonomia del machine learning**

Possiamo ora costruire una **tassonomia del machine learning** distinguendo i modi in cui il sistema riceve il segnale di apprendimento.

> 💡 **La struttura classica (nota didattica).** Le tassonomie di questo tipo dividono il machine learning in tre rami, che ritroveremo nei compiti successivi:
> - **apprendimento supervisionato**: **classificazione** (output discreto) e **regressione** (output continuo);
> - **apprendimento non supervisionato**: **clustering** (e riduzione della dimensionalità);
> - **apprendimento per rinforzo**: un agente impara da **ricompense**.
>
> Le etichette esatte e gli esempi dei rami vanno verificati sulle quattro immagini.

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

In formula (nota didattica), con il modello visto come funzione $\text{Out} = F(X)$ (Lezione 02, §3):

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

> 💡 **La ricompensa è una scelta di progetto.** Il −1 sulle coppie eccessive non serve ad afferrare l’oggetto, ma a farlo **in modo efficiente**; il +1 di avvicinamento dà un segnale anche prima del successo finale, che altrimenti sarebbe raro. Una ricompensa progettata male porta l’agente a ottimizzare la cosa sbagliata: è un esempio del «quarto driver», il cervello del progettista (Lezione 02, §9).

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
| Punto di partenza | regole **if-then**, nessun dato | **nessuna conoscenza** precedente, solo **dati** |
| Diffusione | — | **la maggior parte dei metodi di AI** |

> 💡 **Lo schema di Trochim (nota didattica).** Il ragionamento **deduttivo** va «dall’alto in basso»: teoria → ipotesi → osservazioni → conferma. Quello **induttivo** va «dal basso in alto»: osservazioni → individuazione di schemi → ipotesi provvisoria → teoria. La direzione delle frecce rende intuitiva la differenza fra i due percorsi.

![](imgs/Pasted%20image%2020261002034814.png)

![](imgs/Pasted%20image%2020261002034826.png)

![](imgs/Pasted%20image%2020261002034943.png)

#### **4.2. Due esempi mnemonici**

> 📌 **Apprendimento deduttivo**. Una tecnica di AI che **parte da un insieme di regole** e le applica per prendere decisioni su dati nuovi; poi può **inferire regole nuove**, più efficienti nel contesto in cui vanno applicate. È l’«**esperto di dominio che tramanda le regole**».

L’esempio mnemonico: la **nonna** ti dà le regole per scegliere un buon fidanzato. Se sei d’accordo, **erediti la conoscenza** della sua esperienza e la applichi alla scelta del nuovo partner; poi puoi anche **adattare le regole** ai tempi moderni.

> 📌 **Apprendimento induttivo**. Si basa sull’**inferire una regola generale** da dataset di coppie **input-output**. È il «**learner senza conoscenza a priori**».

L’esempio mnemonico: non vuoi regole, vuoi farti la conoscenza **con l’esperienza** (solo dati, nessuna regola). «Le ultime 10 volte che sono entrato in tangenziale a Milano alle 17:30 sono rimasto in coda» → **nuova regola generale**: evitare di partire nelle ore di traffico intenso. L’immagine è James Dean in *Gioventù bruciata* (*Rebel Without a Cause*, 1955): il ribelle che non accetta regole ricevute.

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

**Dati: esempi di una funzione** $(X, F(X))$.

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

> ⚠️ **I «se» della generalizzazione.** L’induzione non garantisce nulla fuori dai dati visti: se il dataset non copre certe situazioni, il modello non ha modo di conoscerle. È il motivo per cui il corso dedicherà molto spazio a qualità, completezza e partizione dei dati.

### **5. Valutazione di un sistema intelligente**

Dopo aver scelto un metodo e addestrato un modello, resta la domanda decisiva: **funziona per l’applicazione a cui è destinato?** La valutazione è articolata e considera più aspetti:

**Obiettivo della valutazione**:

- garantire che il sistema **soddisfi le aspettative di prestazione**;
- garantire **sicurezza e affidabilità**;
- individuare le **aree da migliorare**.

| Aspetto | Che cosa valutare |
|---|---|
| **Metriche** | accuratezza, precisione, recall, F1; AUC e curva ROC; MAE, MSE, RMSE; **soddisfazione e usabilità**, **ergonomia** (in futuro anche l’esperienza d’uso: il modello è empatico? assertivo? gentile? prolisso?); **etica ed equità** (è stato creato con dati distorti?) |
| **Metodi di validazione** | suddivisione **training/validation/test**; **cross-validation**; **bootstrap**; in futuro forse test basati sull’esperienza d’uso o test «psicologici» |
| **Benchmarking** | confronto con **modelli precedenti** o con le **prestazioni umane**; standard e benchmark di settore; **classifiche pubbliche** (leaderboard) |
| **Prestazioni reali** | studi con utenti e **test sul campo**; valutazione di **robustezza e generalizzabilità**; **stress test** |
| **Monitoraggio continuo** | rilevamento del **data drift**; strategie di **riaddestramento**; **allarmi** di degrado delle prestazioni |
| **Etica e conformità** | analisi di **bias ed equità**; valutazione di **privacy e sicurezza**; verifiche di conformità normativa (**AI Act 2024**) |

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

### **7. Gli strumenti del corso**

#### **7.1. MATLAB, Colab e Keras**

Qualche nota per **preparare gli strumenti** delle prossime lezioni, che saranno ripresi in dettaglio più avanti. Gli strumenti principali del corso sono:

- **MATLAB**: moltissimi strumenti e funzioni;
- **Colab**: server online, TPU, …;
- **Keras**: la libreria Python per il deep learning.

Offrono il **miglior compromesso** tra usabilità e complessità, con una buona curva di apprendimento, e supportano Keras e molte **reti neurali pre-addestrate**.

**Strumento 1: MATLAB**:

- è il **primo strumento** del corso: non solo machine learning, ma molto di più, con **migliaia di funzioni** ben documentate e potenti;
- gli studenti UNIMI possono scaricare una **licenza gratuita** (istruzioni sul portale d’Ateneo, o cercando «UNIMI MATLAB»), anche per installazioni su macchine offline;
- conviene scaricare **l’ultima versione**, perché i toolbox migliorano rapidamente;
- installare **tutti i toolbox**; se mancano spazio su disco o banda, almeno quelli principali del corso.

| Toolbox MATLAB principali per il corso |
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
- è **molto utile** per ottimizzare il tempo del corso senza perdersi in dettagli tecnici.

> ⚠️ **Colab non è obbligatorio**. Richiede un account Google per salvare dati e codice. Le alternative, meno immediate, sono:
> - usare **MATLAB** per tutto;
> - installare strumenti locali (Jupyter Notebook, Anaconda, Python, …), probabilmente con una **GPU**;
> - creare un **account Google dedicato** senza informazioni personali.
>
> Per queste alternative il corso non fornisce assistenza tecnica.

**Keras**:

- la **libreria Python per il deep learning**: un’API di alto livello per reti neurali, scritta in Python e capace di girare sopra **TensorFlow, CNTK o Theano**, pensata per una **sperimentazione rapida**;
- motto: «dall’idea al risultato con il **minor ritardo possibile**»;
- **non serve scaricarla**: è già disponibile in MATLAB e in Colab.

> 💡 **Aggiornamento (nota didattica).** CNTK e Theano non sono più sviluppati. Le versioni recenti di Keras (Keras 3) girano sopra **TensorFlow, JAX o PyTorch**. Il ruolo di Keras come API di alto livello resta invariato.

<!-- SOURCE_VISUAL id="IS03-V32" source="Lesson_03_Tools_AITaxonomy_SensorApplications.pdf" page="63" type="immagine" description="Apertura della sezione sugli strumenti per le prossime lezioni" reason="Illustrazione della sezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Anaconda, Jupyter e gli IDE**

**Strumento 3: Anaconda**:

- uno strumento potente per il **calcolo scientifico** e l’**analisi dei dati**, che semplifica la **gestione dei pacchetti** e il deployment; molto usato da data scientist, ricercatori e sviluppatori;
- è una **distribuzione di Python e R**;
- include **molte librerie preinstallate** per data science, machine learning e altre applicazioni scientifiche;
- ha un ottimo **sistema di gestione dei pacchetti**: installare, aggiornare e gestire versioni diverse di pacchetti e dipendenze;
- **Anaconda Navigator** è l’applicazione desktop per gestire applicazioni, pacchetti e **ambienti** senza riga di comando.

**Jupyter Notebook e Colab**: Colab è **pronto all’uso** e più **indipendente dall’hardware** (per esempio si può provare il codice su TPU), con alcune limitazioni poco rilevanti per il corso.

**Oppure un IDE** (*Integrated Development Environment*, ):

| IDE | Caratteristiche |
|---|---|
| **Visual Studio Community** | versione **gratuita** e completa di Visual Studio per studenti, contributori open source e sviluppatori individuali; ottimi strumenti di **debug**. Strumenti utili per i sistemi intelligenti: GitHub Copilot, ML.NET Model Builder, IntelliCode, estensione Azure Machine Learning, TensorFlow.NET Tools, Bito (assistente alternativo a Copilot), **Python Tools for Visual Studio** (estensione open source che trasforma Visual Studio in un IDE Python, con NumPy, Pandas e scikit-learn) |
| **Visual Studio Code** | IDE **gratuito, leggero e flessibile**, con ottimo supporto a **Python**, estensioni e flussi di lavoro per lo sviluppo di AI: GitHub, estensioni, **agenti di programmazione**, Copilot, «tutto a un clic» |

![](imgs/Pasted%20image%2020261002040833.png)

#### **7.3. Sondaggio in aula e il quarto strumento**

**Sondaggio**: quanti di voi

- sanno programmare in **MATLAB o Python**?
- hanno già **addestrato una rete neurale**, sui **propri dati** e non su un dataset standard?
- usano **Colab**?
- quale libreria usano per **grafici** e rappresentazioni?

> 📌 **Strumento 4: pazienza e teoria**. Questo **non** è un corso «come creare una rete neurale» né una «guida passo passo». Prima delle attività di laboratorio e di programmazione bisogna affrontare alcuni **temi teorici** rilevanti.

### **8. Come studiare il corso: l’interiorizzazione**

**Dalla superficialità all’interiorizzazione.** Come si impara la teoria dell’AI? Lavorando e progettando **con «il libro» in mano**, oppure **a mani libere**, con la conoscenza nello zaino? **La conoscenza vera richiede interiorizzazione.** Il libro esiste davvero, ma il docente chiede di **non comprarlo**.

**Come ottenere l’interiorizzazione**. Soprattutto nel machine learning bisogna:

1. **studiare la teoria** e le relative **ipotesi di lavoro**;
2. studiare **quanti più casi d’uso possibile**, per allargare il proprio punto di vista;
3. **applicare la teoria** ad applicazioni reali;
4. fare ogni volta che si può una **discussione e un confronto onesti**: chiedersi «**perché questo e non quello?**», per esempio «**che cosa sta imparando davvero il modello?**».

Inoltre:

- costruirsi un **portfolio** di modelli e metodi di apprendimento, il più ampio possibile;
- tenersi aggiornati sullo **stato dell’arte** (SOA) e sulle tendenze;
- **scegliere sempre tra soluzioni diverse**;
- **non considerare solo l’accuratezza**: c’è un’ampia gamma di **figure di merito** da considerare nel progetto (prossime lezioni);
- migliorare il progetto **partendo dalle esperienze e dalle scoperte precedenti**: «**sulle spalle dei giganti**». L’analisi dello stato dell’arte è **fondamentale**.

> 💡 **Il collegamento con il quarto driver.** L’elenco coincide con il «cervello del progettista» della Lezione 02, §9: conoscere più modelli, confrontarli, chiedersi che cosa imparano ed evitare la forza bruta è ciò che distingue chi **progetta** un sistema intelligente da chi **esegue** un tutorial.

### **9. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Narrow AI** / **AGI** | compito specifico, monomodale, oggi predominante / intelligenza generale di tipo umano, ancora teorica |
| **Probabilità di AGI** / **probabilità di disastro** | i due assi separati della AI Apocalypse Matrix |
| **Test di Turing** / **ConceptARC** | abilità conversazionale / pensiero astratto su pochi esempi |
| **Regressione** / **classificazione** | output continuo / output discreto (etichette) |
| **Classificazione** / **clustering** | classi note e dati etichettati / gruppi scoperti in dati non etichettati |
| **Supervisionato** / **semi-supervisionato** / **non supervisionato** / **rinforzo** | tutte le etichette / poche etichette / nessuna etichetta / ricompense da sequenze di azioni |
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
> - La **Narrow AI** è l’AI di oggi: specifica e monomodale, senza Gestalt. L’**AGI** è ancora teorica e **manca di una definizione condivisa**; sulle sue probabilità e sui suoi rischi gli esperti sono divisi.
> - Il **test di Turing** misura la conversazione, non l’intelligenza generale; test come **ConceptARC** e ARC-AGI mettono ancora in difficoltà le macchine.
> - **Tassonomia**: regressione e classificazione (supervisionate), clustering (non supervisionato), apprendimento per rinforzo con agente, stato, azione, politica e ricompensa.
> - **Deduttivo** contro **induttivo**: la maggior parte dei metodi di AI è induttiva e generalizza solo se dati e modello sono adeguati; la **trasduzione** va dal particolare al particolare.
> - La **valutazione** comprende metriche, validazione, benchmarking, test sul campo, monitoraggio continuo ed etica/conformità.
> - **AI e sensori**: sensori intelligenti, reti di sensori, strumentazione avanzata e **sensori virtuali**, come la stima dello stato di carica di una batteria.
