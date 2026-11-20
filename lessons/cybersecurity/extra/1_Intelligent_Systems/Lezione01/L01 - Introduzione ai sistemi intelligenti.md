## **Lezione 1: Introduzione ai sistemi intelligenti**

Un sistema intelligente si progetta partendo da un bisogno e arrivando a una soluzione che funzioni nel contesto reale. Per costruirlo servono requisiti chiari, dati adeguati, conoscenza del problema, modelli, strumenti e verifiche.

Il percorso parte dalla definizione di sistema intelligente, passa dalla progettazione alla valutazione e considera infine i limiti che emergono nell’uso reale. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. JARVIS: recuperare le fonti e controllarne l’attualità**

Un sistema basato su **RAG** (*Retrieval-Augmented Generation*, generazione aumentata dal recupero) cerca informazioni in una raccolta di documenti e le usa per formulare una risposta. JARVIS, un assistente basato su RAG, mostra due proprietà utili: può recuperare un passaggio pertinente e citarne la fonte, ma la risposta dipende dai documenti disponibili e dal loro aggiornamento.

Alla domanda se sia possibile portare gli appunti all’esame, JARVIS risponde di no e cita la fonte che stabilisce le regole. Alla domanda sulla scadenza per i sistemi ad alto rischio, invece, restituisce il 2 agosto 2026 perché la base documentale conserva una data precedente.

Il Regolamento (UE) 2026/1744, in vigore dal 27 luglio 2026, ha introdotto nuove scadenze: 2 dicembre 2027 per i sistemi dell’Allegato III e 2 agosto 2028 per quelli integrati nei prodotti dell’Allegato I. Le date sono riportate anche dalla [Commissione europea](https://digital-strategy.ec.europa.eu/en/policies/enforcement-ai-act) e il regolamento è consultabile su [EUR-Lex](https://eur-lex.europa.eu/eli/reg/2026/1744/oj).

La risposta obsoleta non nasce necessariamente da un’invenzione del modello: il sistema ha recuperato correttamente un documento non aggiornato. Perciò una citazione aiuta a rintracciare la provenienza dell’informazione, mentre l’attualità va verificata separatamente.

Questo controllo è particolarmente importante per norme, procedure e fatti che cambiano nel tempo. JARVIS può anche ricostruire il caso delle radiografie raccolte da ospedali diversi: un classificatore può riconoscere l’ospedale attraverso le caratteristiche dell’apparecchiatura e usare quella scorciatoia per prevedere le fratture.

Lo stesso meccanismo permette di collegare una risposta documentata all’individuazione di una scorciatoia appresa dal modello e alla sua spiegazione.

---

### **1. Dalle esigenze a un sistema intelligente funzionante**

![](imgs/Pasted%20image%2020261119115632.png)

Il progetto comincia dalle persone che useranno il sistema e dal bisogno da soddisfare. Le esigenze diventano **requisiti**, poi vengono raccolte e organizzate in documenti.

Una base di conoscenza, una wiki o un *second brain* («secondo cervello», archivio personale di conoscenze) può aiutare a conservare informazioni e decisioni; gli LLM (*Large Language Model*, modello linguistico di grandi dimensioni) e gli agenti possono assistere nell’analisi, purché le loro proposte siano controllate. Il passaggio successivo consiste nel tradurre i requisiti in **specifiche e decisioni di progetto finite e verificabili**.

Solo allora si definiscono sensori, acquisizione dei dati, filtraggio e armonizzazione, controlli di qualità e di leakage, addestramento e valutazione dei modelli. Il modello entra dunque in una catena più ampia, che termina nel dispositivo o servizio usato sul campo.

Una volta distribuito, il sistema riceve riscontri dal mondo reale. Questi feedback permettono di individuare problemi, aggiornare requisiti e dati, e decidere se intervenire sul modello o su altri componenti.

Nel contesto regolamentato dei sistemi ad alto rischio, il monitoraggio successivo alla commercializzazione è anche un obbligo previsto dalla normativa; il ciclo di feedback, però, è utile a qualunque sistema soggetto a cambiamenti d’uso o di ambiente.

> 📌 Il flusso di progetto procede da bisogno e requisiti a specifiche, dati, modello, distribuzione e feedback.

Le decisioni iniziali determinano che cosa il modello dovrà imparare e come sarà valutato.

![](imgs/Pasted%20image%2020261119115653.png)

Una domanda apparentemente semplice aiuta a motivare questa attenzione: **un’accuratezza del 99,2% descrive da sola un buon modello?** L’accuratezza indica la quota di predizioni corrette nel campione valutato; per interpretarla occorre sapere quale compito si sta svolgendo, come sono distribuite le classi e quali errori hanno conseguenze maggiori. Se gli errori sono concentrati proprio nei casi critici, una percentuale complessiva elevata può nascondere un sistema inadeguato.

La valutazione deve quindi partire dai requisiti e includere metriche coerenti con il rischio operativo.

---

### **2. Che cos’è un Intelligent System**

#### **2.1. Che cosa intendiamo per sistema intelligente**

Non esiste una sola definizione indiscussa di intelligenza. Per progettare applicazioni è utile questa descrizione operativa: un **Intelligent System / sistema intelligente** è un sistema basato su elaboratore che mira a riprodurre capacità cognitive umane quali **apprendimento, percezione, ragionamento e decisione**.

Queste capacità hanno ruoli diversi. La percezione interpreta ciò che arriva da sensori, immagini o voce; l’apprendimento ricava regolarità dagli esempi; il ragionamento collega informazioni e conoscenze; la decisione seleziona una risposta o un’azione.

Un sistema può combinare alcune di queste capacità senza riprodurre integralmente l’intelligenza umana. Attraverso **Machine Learning / apprendimento automatico** e altre tecnologie, il sistema elabora dati per eseguire compiti che normalmente richiedono intelligenza umana, formulare previsioni o ricavare indicazioni utili.

La definizione riguarda quindi la **funzione dell’intero sistema**, non soltanto l’algoritmo al suo interno.

> 📌 Un modello è una componente di un sistema intelligente.

Il sistema comprende anche dati, sensori, software, hardware, interfacce, vincoli operativi e modalità di impiego dei risultati.

#### **2.2. Glossario iniziale**

| Sigla | Espansione | Significato |
|---|---|---|
| AI         | Artificial Intelligence | Intelligenza artificiale, ambito generale di metodi e sistemi.        |
| IS         | Intelligent Systems     | Sistemi intelligenti, cioè le applicazioni costruite con tali metodi. |
| ML         | Machine Learning        | Tecniche che apprendono dai dati.                                     |
| NN         | Neural Network          | Rete neurale, una famiglia di modelli di ML.                          |
| Train/Test | Training / Testing      | Addestramento e valutazione del modello.                              |
| HW/SW      | Hardware / Software     | Componenti fisiche e programmi.                                       |
| I/O        | Input / Output          | Dati in ingresso e risultati in uscita.                               |

In ambito di machine learning si usano i termini testing e validation. **Precisazione metodologica:** la validazione serve normalmente a scegliere modelli e configurazioni; il test finale stima il comportamento su dati tenuti separati dalle scelte. Il dettaglio sarà sviluppato nelle lezioni sul workflow e sulla partizione dei dataset: qui basta evitare di confondere l’apprendimento con la sua verifica.

#### **2.3. Esempi, settori e task**

Tra gli esempi rientrano assistenti virtuali come Siri e Alexa, veicoli autonomi, sistemi di diagnosi medica, raccomandazione, riconoscimento di immagini e voce, chatbot, manutenzione predittiva, rilevamento delle frodi e **sentiment analysis**, cioè analisi dell’orientamento espresso in un contenuto. Si va da sensori visivi e interfacce conversazionali fino all’analisi di radiografie: cambiano gli ingressi e cambiano i risultati attesi, ma in ogni caso il modello è solo una parte dell’applicazione.

![](imgs/Pasted%20image%2020260929043529.png)

Per orientarsi in questa varietà, conviene separare tre livelli che spesso vengono confusi:
- Il **settore** è l’ambiente di applicazione: aviazione, informatica, educazione, finanza, industria pesante, ospedali e medicina, marketing, media, musica, editoria e notizie, assistenza clienti online o telefonica, manutenzione delle telecomunicazioni, giocattoli e giochi, trasporti. In finanza rientrano trading algoritmico, analisi di mercato e data mining, finanza personale, gestione di portafoglio e valutazione del credito.
- Il **task** è il problema informatico: OCR (_Optical Character Recognition_, riconoscimento ottico dei caratteri), riconoscimento della scrittura, della voce o dei volti; creatività artificiale; computer vision, realtà virtuale ed elaborazione delle immagini; diagnosi; teoria dei giochi e pianificazione strategica; bot e AI dei videogiochi; NLP (_Natural Language Processing_, elaborazione del linguaggio naturale), traduzione e chatbot; controllo non lineare e robotica.
#### **2.4. Tecnologie e modelli nei sistemi autonomi**

Un sistema autonomo combina funzioni di navigazione, controllo, pianificazione, interazione, manutenzione e percezione. Ogni funzione richiede sensori o strumenti specifici e può integrare modelli di AI con metodi classici.

| Funzione | Tecnologie | Modelli e metodi |
|---|---|---|
| Navigazione e localizzazione | SLAM (_Simultaneous Localization and Mapping_, localizzazione e mappatura simultanee); GNSS (_Global Navigation Satellite System_, sistema globale di navigazione satellitare), con GPS (_Global Positioning System_, sistema di posizionamento globale) e RTK (_Real-Time Kinematic_, posizionamento cinematico in tempo reale); lidar e telecamere | Apprendimento per rinforzo profondo (*Deep Reinforcement Learning*) per pianificare il percorso; filtri di Kalman per la fusione sensoriale |
| Attuazione e controllo | Motori brushless, attuatori idraulici, controllori PID (_Proportional–Integral–Derivative_, proporzionale–integrale–derivativo) e MPC (_Model Predictive Control_, controllo predittivo basato su modello) | Controllo adattivo; apprendimento per rinforzo (*Reinforcement Learning*) per la stabilità dinamica |
| Pianificazione del percorso | Algoritmi di ricerca come A*, mappe 3D, reti neurali predittive | CNN (_Convolutional Neural Network_, rete neurale convoluzionale) e LSTM (_Long Short-Term Memory_, memoria a breve e lungo termine) per anticipare gli ostacoli |
| Interazione uomo–macchina, HMI (*Human–Machine Interaction*, interazione uomo–macchina) | Elaborazione del linguaggio naturale (NLP, già definita sopra), riconoscimento di volti e gesti | Transformer come ChatGPT e BERT (*Bidirectional Encoder Representations from Transformers*, rappresentazioni bidirezionali dei testi tramite Transformer); analisi del sentiment basata sulla visione |
| Diagnostica e manutenzione predittiva | Fusione di sensori IMU (_Inertial Measurement Unit_, unità di misura inerziale), vibrazioni e temperatura; calcolo vicino alla sorgente (*edge computing*) | Modelli predittivi per serie temporali, come RNN (_Recurrent Neural Network_, rete neurale ricorrente) e LSTM (già definita sopra) |
| Percezione dell’ambiente | Lidar, radar, telecamere stereoscopiche, sensori a ultrasuoni | Rilevamento di oggetti con YOLO (_You Only Look Once_, «guarda una sola volta») e Faster R-CNN (_Faster Region-based Convolutional Neural Network_, rete neurale convoluzionale veloce basata su regioni); PointNet per dati lidar |
| AI al margine della rete ed elaborazione in tempo reale | FPGA (_Field-Programmable Gate Array_, matrice di porte logiche programmabile), TPU (_Tensor Processing Unit_, unità di elaborazione dei tensori) e Nvidia Jetson per l’inferenza locale | Reti neurali quantizzate per ridurre il costo energetico del calcolo |

SLAM costruisce la mappa mentre stima la posizione del sistema; GNSS comprende sistemi satellitari come GPS e tecniche RTK. IMU indica un’unità di misura inerziale, PID un controllo proporzionale–integrale–derivativo e MPC un _Model Predictive Control_. FPGA e TPU sono piattaforme hardware; YOLO, Faster R-CNN e PointNet sono metodi di elaborazione. La quantizzazione riduce la precisione numerica per contenere i costi di calcolo ed energia, valutando l’effetto sulle prestazioni.

Il caso autonomo riunisce molte funzioni proprie di un sistema intelligente complesso: ogni singolo modello opera entro una catena di sensori, decisioni e azioni.

#### **2.5. Dalle categorie alle applicazioni**

Un’applicazione combina uno o più task per uno scopo concreto. Tra gli esempi rientrano ricerca sul Web, biologia computazionale, finanza, e-commerce, esplorazione di spazi e luoghi — aree, città, abitazioni —, robotica, estrazione di informazioni, social network e debugging. Per esempio, il riconoscimento vocale è un task; la logistica è un settore; il prelievo di merci guidato dalla voce è un’applicazione.

Questa distinzione aiuta a trasferire una tecnica fra domini senza presumere che dati e vincoli restino identici. La diffusione dell’AI è legata anche alla possibilità di elaborare grandi quantità di dati, riconoscere regolarità, individuare prima alcuni problemi e distribuire meglio le risorse.

L’interesse pubblico è cresciuto insieme alla presenza di applicazioni quotidiane, spesso molto diverse dall’immaginario fantascientifico. Un indicatore come Google Trends misura però le ricerche effettuate: non dice quanto siano accurati i modelli né quante imprese li adottino.

![](imgs/Pasted%20image%2020260929043804.png)

### **3. Come si articola lo studio dei sistemi intelligenti**

#### **3.1. Quattro prospettive complementari**

Per affrontare un’applicazione si intrecciano quattro prospettive: **Theory of AI**, **Tools (SW–HW)**, **Overviews** e **Use cases**. Non sono tappe isolate.

La teoria orienta la scelta dei metodi; gli strumenti rendono possibile sperimentare; le panoramiche aiutano a capire lo stato dell’arte; i casi d’uso riportano ogni decisione al problema reale. Se il caso non funziona, si torna sui dati o sul modello: il percorso è circolare.

![](imgs/Pasted%20image%2020260929043929.png)

La **teoria** fornisce ciò che serve a capire il funzionamento: pattern matching, apprendimento, analisi ed elaborazione dei dati, estrazione e fusione di feature. Una **feature / caratteristica** è una proprietà con cui si rappresenta un caso per il modello; più avanti si vedrà quanto la rappresentazione incida sull’apprendimento.

Gli **strumenti software** servono ad analizzare, trasformare e visualizzare dati e a costruire ambienti di apprendimento. Fra gli strumenti software si incontrano MATLAB, TensorFlow e Google Colab, con l’invito a mantenere flessibilità nella scelta.

La parte **hardware** considera CUDA (_Compute Unified Device Architecture_, architettura unificata di calcolo per GPU), CPU (_Central Processing Unit_, unità centrale di elaborazione), GPU (_Graphics Processing Unit_, unità di elaborazione grafica), edge e fog computing: luogo di esecuzione e risorse disponibili sono parte del progetto. Le **overviews / panoramiche** orientano nello stato dell’arte: che cosa si può fare nei diversi mercati, quali strumenti e dataset servono, quali limiti restano e quali direzioni di ricerca stanno emergendo.

**Anche queste informazioni fanno parte del programma d’esame.** Gli **use cases / casi d’uso** rendono operativi i concetti attraverso esempi, gestione dei dati, input e output, risultati attesi, codice e frammenti di programma, strumenti hardware e software.

Studiare un caso significa capire perché una configurazione risolve quel problema, non memorizzare soltanto il nome del modello.

#### **3.2. Dalla teoria all’applicazione**

Le lezioni costruiscono il lessico e la teoria, mentre laboratori e simulazioni permettono di applicarli a problemi concreti e di familiarizzare con gli strumenti. La prova scritta verifica proprio questo passaggio: riconoscere i concetti e usarli per ragionare su un caso.

![](imgs/Pasted%20image%2020260929044135.png)

Un secondo schema scende attraverso livelli di astrazione: modelli e metodi di apprendimento, AI classica e pattern matching, sviluppi del deep learning, applicazioni industriali/ambientali/supply chain, casi d’uso. La teoria deve quindi arrivare fino alla scelta concreta di dati, strumenti e comportamento del sistema.

![](imgs/Pasted%20image%2020261118155326.png)

Le regole d’esame possono cambiare: prima di ogni appello vanno controllati gli avvisi aggiornati.

#### **3.3. Perché saper addestrare una rete non basta**

**Machine Learning non coincide con Deep Neural Networks.** Conoscere bene un solo strumento può portare ad applicarlo anche quando il problema richiede altro. La metafora del martello e del chiodo rende intuitiva questa distorsione: la disponibilità di una tecnica non ne dimostra l’adeguatezza.

![](imgs/Pasted%20image%2020260929044242.png)

Il progettista deve conoscere differenze e limiti dei modelli, organizzare i dati di addestramento, evitare errori tipici, capire le peculiarità del settore e costruire una propria dotazione di strumenti. Prima dell’addestramento, il progettista ricostruisce lo **stato dell’arte** (metodi precedenti, buone pratiche, tendenze e soluzioni disponibili), valuta se aggregare dati, conoscenze o feature, imposta confronti equi, cerca somiglianze e differenze tra domini e visualizza i dati. Su queste basi può avviare **training, validation e deployment**, cioè addestramento, selezione e verifica delle scelte, e messa in esercizio.

Un confronto non è equo se attribuisce al modello un vantaggio dovuto invece a dati più favorevoli o a condizioni diverse. Per questo la gestione dei dati richiede attenzione quanto la scelta delle architetture neurali.

### **4. Prepararsi all’esame**

#### **4.1. Struttura della prova**

La prova è **scritta, a risposta multipla e svolta in laboratorio al PC**; non prevede orale o progetto. La durata indicata è di circa un’ora.

Gli argomenti comprendono teoria, panoramiche, strumenti, applicazioni, casi d’uso, progettazione di casi semplici e interpretazione del codice visto a lezione. Le domande verificano concetti, esempi, strumenti e ragionamenti affrontati nello studio.

Il quiz si svolge su Moodle in laboratorio, con browser bloccato e senza consultare materiali personali. Sono previste simulazioni nelle lezioni 4, 6, 12 e 24; quella della lezione 24 è riservata a chi frequenta in presenza. Per ogni appello fanno fede gli avvisi aggiornati. La regola di punteggio assegna **1 punto all’unica risposta corretta e 0 alle risposte sbagliate**, seguiti da una normalizzazione del punteggio in voto.

La formula di normalizzazione e il numero totale di domande non sono specificati.

> ⚠️ Nel prospetto dei punteggi compare anche una penalità di −1, ma è barrata e accompagnata dall’indicazione “No more negative weights”.

La regola da seguire è quindi quella senza punteggi negativi.

![](imgs/Pasted%20image%2020260929143202.png)

Si può sostenere la prova a ogni appello; non sono consentiti libri, PC personale o materiale di consultazione. È obbligatoria l’iscrizione tramite UNIMIA **per ciascun appello**: dopo un esito negativo non avviene una reiscrizione automatica.

Per l’appello specifico vanno controllati gli avvisi amministrativi aggiornati.

#### **4.2. Le regole strategiche**

La “regola zero” è iscriversi regolarmente. Una strategia utile consiste nell’**escludere prima le risposte chiaramente errate** e solo dopo confrontare quelle rimaste.

Un esempio chiede di stimare l’età di una persona: i numeri mostrano il procedimento e non registrano un dato anagrafico.

| Alternativa dell’esempio | Ricerca diretta | Esclusione delle alternative |
|---|---|---|
| A: 49                    | Fiducia indicata: 67% | Rimane la scelta dopo le esclusioni. |
| B: 35                    | Altra possibilità     | Esclusa con fiducia del 75%.         |
| C: 12                    | Altra possibilità     | Esclusa con fiducia del 99,9%.       |
| D: 65                    | Altra possibilità     | Esclusa con fiducia dell’80%.        |

Le percentuali sono valutazioni illustrative, non probabilità da combinare mediante una formula. La variante della regola suggerisce, quando resta incertezza, di scegliere la risposta che appare **meno sbagliata**, motivando perché le altre non soddisfano la domanda.

Per prepararsi, conviene ripercorrere **tutti gli argomenti**, chiedendosi quale domanda precisa possa verificare ciascun concetto. Le simulazioni aiutano a riconoscere il tipo di ragionamento richiesto e a verificare se la preparazione sta funzionando.

Questa lezione non aggiunge domande presunte d’esame.

### **5. Famiglie di AI, rappresentazioni e progressi**

#### **5.1. Leggere la mappa dei “Layers of AI”**

Una mappa attribuita a Harry Ratcliffe organizza queste idee in sei livelli. È una mappa introduttiva che accosta tecniche, componenti e funzioni; non una tassonomia formale in cui ogni voce escluda le altre.

![](imgs/Pasted%20image%2020260929143226.png)

- **Classical AI:** AI simbolica, sistemi esperti, rappresentazione della conoscenza, logica e ragionamento. Almeno una parte della conoscenza da usare viene resa esplicita.
- **Machine Learning:** apprendimento supervisionato, non supervisionato e per rinforzo; classificazione e regressione. I primi tre termini indicano modalità di apprendimento; gli ultimi due sono task: assegnare categorie o stimare valori numerici.
- **Neural Networks:** percettroni, funzioni di costo, backpropagation, funzioni di attivazione e strati nascosti. La funzione di costo misura l’errore; la backpropagation calcola come i parametri contribuiscono a tale errore.
- **Deep Learning:** Transformer, LSTM (_Long Short-Term Memory_), RNN (_Recurrent Neural Networks_), CNN (_Convolutional Neural Networks_) e autoencoder. Le famiglie si distinguono per le rappresentazioni e le strutture dei dati che elaborano; una LSTM, per esempio, è una rete ricorrente.
- **Generative AI:** LLM (_Large Language Models_), diffusion models, modelli multimodali e VAE (_Variational Autoencoders_, autoencoder variazionali), usati per generare contenuti.
- **Agentic AI:** memoria, pianificazione, uso di strumenti ed esecuzione di azioni orientate a un obiettivo. L’unità di lavoro diventa una sequenza di attività, non una singola risposta.

L’attenzione si sposta dalla singola risposta a una sequenza di attività orientate a un obiettivo. Questi richiami spiegano il diagramma senza anticipare l’intera teoria delle lezioni successive.

In particolare, un agente può usare un modello generativo, ma la capacità di generare testo e quella di gestire azioni non sono la stessa proprietà.

#### **5.2. Word embeddings e analogie**

Un **word embedding** rappresenta una parola o un’espressione mediante un vettore di numeri reali, utilizzabile come ingresso di un modello. In questo modo il sistema può calcolare relazioni tra elementi linguistici.

Word2Vec, introdotto nel 2013, è un passaggio importante di questa storia; i modelli successivi hanno sviluppato rappresentazioni contestuali e architetture Transformer. Nel disegno tridimensionale, gli assi sono _wings_, _engine_ e _sky_.

Le coordinate illustrative sono: goose $(2,0,4)$, eagle $(3,0,3)$, bee $(3,0,2)$, helicopter $(0,2,4)$, drone $(0,3,3)$, rocket $(0,4,2)$, jet $(1,1,1)$. Le parole diventano punti/vettori che ne esprimono proprietà. **È un’illustrazione didattica**: non sono misure fisiche né valori sperimentali di un modello; le dimensioni di un embedding appreso non hanno necessariamente etichette interpretabili così semplici.

Un secondo esempio mette a confronto due frasi, una su un intervento di ricerca a Boston e l’altra su una lezione scientifica a Seattle. Le corrispondenze _research/science_, _talk/lecture_, _Boston/Seattle_ mostrano perché parole diverse possano occupare regioni semanticamente vicine.

![](imgs/Pasted%20image%2020260929143724.png)

Le analogie verificano se una relazione può essere trasferita: **Parigi sta alla Francia come Tokyo sta al Giappone**. Lo stesso schema vale per calzini e piedi, guanti e mani.

In un esempio, il modello linguistico risolve queste analogie e ne propone altre: “automobili : città” e “formiche : colonia”, cioè formicaio.

![](imgs/Pasted%20image%2020260929143812.png)

![](imgs/Pasted%20image%2020260929143828.png)

Una risposta corretta documenta il successo su quel quesito; per valutare capacità più generali servono prove su altri input e compiti. **Nota storica:** il materiale raggruppa Transformer ed embedding contestuali sotto “2018+”; l’articolo che introduce il Transformer risale al 2017, come documenta [Attention Is All You Need](https://arxiv.org/abs/1706.03762).

#### **5.3. Ragionamento matematico e confronto con l’intelligenza naturale**

La panoramica cita modelli di OpenAI e Google DeepMind capaci, nel contesto IMO 2025, di risolvere cinque problemi su sei mediante ragionamento in linguaggio naturale, con punteggio equivalente al livello della medaglia d’oro.

La piccola tabella distingue però **condizioni di valutazione diverse**:

| Voce nella tabella del materiale | Contesto | Punteggio su 42 | Interpretazione riportata |
|---|---|---:|---|
| Concorrente umano tipico         | IMO 2024                                   |            16,0 | Sotto la soglia bronzo di quell’anno.     |
| Concorrente umano tipico         | IMO 2023                                   |            17,7 | Intorno al bronzo in diversi anni.        |
| Soglia bronzo                    | IMO 2025                                   |             19+ | Bronzo.                                   |
| Soglia argento                   | IMO 2025                                   |             28+ | Argento.                                  |
| Soglia oro                       | IMO 2025                                   |             35+ | Oro.                                      |
| AlphaProof + AlphaGeometry 2     | Valutazione sul modello IMO 2024           |              28 | Argento.                                  |
| Gemini Deep Think                | Valutazione ufficiale IMO 2025             |              35 | Oro.                                      |
| Modello OpenAI                   | Valutazione esterna non ufficiale IMO 2025 |              35 | Risultato dichiarato equivalente all’oro. |

L’esempio vuole mostrare la crescita delle capacità di ragionamento; la formulazione “human-level” va letta **in relazione al benchmark**, non come prova di equivalenza all’intelligenza umana in ogni contesto. Non vanno confusi punteggio equivalente, medaglia di un concorrente e modalità di certificazione.

Nel confronto biologico si considerano per _Caenorhabditis elegans_ 302 neuroni e circa 7.000 sinapsi, e per il cervello umano ordini di grandezza di $10^{11}$ neuroni e più di $10^3$ sinapsi per neurone, con richiamo a Nature del 21 agosto 2019. Il diagramma colloca le reti artificiali fra ape e rana e le contrappone al cervello umano.

![](imgs/Pasted%20image%2020260930003454.png)

**Limite del confronto:** quei numeri e la collocazione fra animali appartengono al quadro storico del materiale. Un’unità artificiale, un parametro e un neurone biologico non sono unità intercambiabili di capacità cognitiva.

Il numero di componenti, da solo, non ordina in modo universale l’intelligenza dei sistemi.

#### **5.4. Progressi recenti e fotografie temporali dei modelli**

Il modello dots-note-3.0 di RedNote avrebbe risolto tutti e sei i problemi dell’IMO 2026, ottenendo 42 punti su 42 in una valutazione esterna. La pagina ufficiale dell’[IMO 2026](https://www.imo-official.org/editions/2026/) presenta i risultati della competizione e segnala sette punteggi perfetti, senza elencare un sistema AI tra i concorrenti: il punteggio del modello va quindi distinto dalla classifica ufficiale.

I modelli *frontier* sono disponibili via cloud o API (_Application Programming Interface_, interfaccia di programmazione delle applicazioni); i modelli *open-weight* possono essere eseguiti localmente o su server aziendali. La fotografia al **20 novembre 2026** comprende GPT-6.2 Astra e Sol, Claude Fable 5.2 e Opus 5.5, Gemini 4 Neon e Carbon, Muse Wonder e Muse Wonder Full, Muse Spark 1.3 e Grok 4.6. Claude Mythos 5.2, annunciato insieme a Fable 5.2 il 9 novembre, è accessibile tramite programmi di verifica per organizzazioni autorizzate; non va presentato come un prodotto disponibile indistintamente. GPT-6.2 Astra/Sol sono stati annunciati il 17 novembre, Gemini 4 Neon il 18 novembre e Muse Wonder il 12 ottobre. È una fotografia temporale, non un catalogo stabile: nomi, versioni, disponibilità e prezzi cambiano rapidamente. Le descrizioni di capacità e i confronti prestazionali riportati dagli annunci sono dichiarazioni dei produttori. [OpenAI](https://openai.com/index/introducing-gpt-6-2-astra-sol/), [Anthropic](https://www.anthropic.com/claude-fable-and-mythos-5-2), [Google](https://blog.google/innovation-and-ai/models-and-research/gemini-models/introducing-gemini-4-neon/), [Meta](https://ai.meta.com/research/).

L’esecuzione locale può mantenere i dati sul dispositivo o sull’infrastruttura aziendale, ma la riservatezza dipende da tutta la configurazione: dove gira il modello, quali servizi di supporto vengono chiamati e come vengono trattati prompt e log. La memoria video della GPU, o VRAM (_Video Random-Access Memory_, memoria video ad accesso casuale), è un vincolo pratico importante; non determina da sola qualità e adeguatezza del modello.

![](imgs/Pasted%20image%2020261119133610.png)

### **6. Dove l’intelligenza artificiale prende forma**

#### **6.1. Rilevanza economica: leggere correttamente gli esempi**

La crescita dell’AI ha anche una dimensione economica, visibile negli investimenti e nei ricavi delle imprese. La tabella, riferita all’anno fiscale 2025, riporta i valori seguenti.

Sono dati legati al periodo indicato, non una fotografia aggiornata del mercato.

Come riferimento più recente, NVIDIA ha comunicato il 17 novembre 2026 ricavi trimestrali per **115,8 miliardi di dollari** nel terzo trimestre dell’anno fiscale 2027, di cui **107,6 miliardi** nel Data Center. È un dato aziendale riferito al trimestre concluso il 25 ottobre, non una misura del mercato AI complessivo né una stima causale del contributo dell’AI ai ricavi di ogni impresa. [Risultati ufficiali NVIDIA](https://nvidianews.nvidia.com/news/nvidia-announces-financial-results-for-third-quarter-fiscal-2027).

| Società | Ricavi, miliardi USD | Utile netto, miliardi USD | Indicazione sulla crescita attribuita all’AI |
|---|---:|---:|---|
| Nvidia    |                130,5 |               60+ (stima) | Ricavi Data Center: +427% anno su anno.      |
| Alphabet  |                402,8 |                     132,2 | Ricavi Google Cloud: +48%.                   |
| Microsoft |                281,7 |                     101,8 | Ricavi Azure: +34%.                          |

Un’infografica distinta, con copyright 2024, mostra ricavi di Apple 94,9, Meta 40,59, Microsoft 65,6, Netflix 9,8, Amazon 158,9 e Google 88,3 miliardi USD. Richiama personalizzazione, pubblicità e infrastrutture; menziona oltre 2.500 app native e 1,5 milioni compatibili per Vision Pro, oltre 3,2 miliardi di utenti quotidiani delle app Meta, quasi il 70% delle Fortune 500 con Microsoft 365 Copilot, uso della Gemini API cresciuto 14 volte in sei mesi, AI Overview in oltre 100 nuovi paesi con oltre un miliardo di utenti mensili, e oltre un quarto del nuovo codice Google generato con AI e poi revisionato.

Riporta anche un’aspettativa su modelli Llama 4 più piccoli, l’interrogativo di Netflix sul miglioramento dei contenuti e l’investimento infrastrutturale di Amazon.

![](imgs/Pasted%20image%2020260930003722.png)

**Attenzione al contesto temporale:** i due insiemi di dati appartengono ad anni diversi. I ricavi aziendali comprendono attività diverse dall’AI e vanno distinti dall’utile.

Questi dati mostrano la rilevanza economica del settore, senza isolare il contributo causale dell’AI alla crescita delle singole aziende.

#### **6.2. Modelli generativi e assistenza alla programmazione**

Gli impieghi comprendono assistenza al coding, scrittura creativa e soluzione di problemi; la versione aggiornata amplia il confronto fra servizi cloud e modelli eseguibili localmente. GitHub Copilot e Code Llama ([Ollama](https://ollama.com/library/codellama)) esemplificano l’assistenza a scrittura, debugging e ottimizzazione del software.

In un flusso tipico, un servizio riceve dal contesto dell’editor le informazioni necessarie e restituisce suggerimenti; il programmatore li valuta e può affinare la richiesta. I dati usati per addestrare il modello e quelli inviati durante l’uso sono due aspetti distinti.

![](imgs/Pasted%20image%2020260930003758.png)

Il frammento divide ricorsivamente la lista in due metà fino a ottenere sottoliste di un solo elemento, poi affida a `merge` la fusione ordinata:

```python
def merge_sort(list):
    if len(list) <= 1:
        return list
    mid = len(list) // 2
    left = merge_sort(list[:mid])
    right = merge_sort(list[mid:])
    return merge(left, right)
```

`merge` non è definita, quindi il frammento non è eseguibile da solo; inoltre il parametro `list` nasconde il nome della funzione predefinita Python. Il codice illustra la ricorsione, ma va completato e corretto prima dell’esecuzione.

Un'applicazione concreta sono le ghost suggestions:

![](imgs/Pasted%20image%2020261119134133.png)


![](imgs/Pasted%20image%2020260930003824.png)

#### **6.3. Multimodalità, generazione visiva e scoperta scientifica**

Un sistema **multimodale** combina tipi diversi di informazione, come testo, immagini, audio e video. Due esempi chiariscono il punto: contare nove mele disposte in tre righe da tre; usare fotografia di una bicicletta, manuale e immagine degli attrezzi per spiegare una regolazione del sellino, distinguendo un bullone da una leva e indicando una chiave esagonale.

Il contributo dell’immagine è fornire il contesto che la domanda testuale da sola non descrive.

![](imgs/Pasted%20image%2020260930003934.png)

![](imgs/Pasted%20image%2020260930003957.png)

Tra gli strumenti di generazione di immagini e video compaiono Stable Diffusion, Midjourney, DALL-E 3, Runway ML e Pika Labs. Il collage di Nicolas Cage in ruoli differenti mostra variazioni sintetiche di uno stesso soggetto.

Il diagramma sopra il collage rappresenta **encoder, bottleneck e decoder**: l’immagine viene trasformata in una rappresentazione latente e poi ricostruita. Tale disegno illustra la codifica e ricostruzione; **non descrive da solo il procedimento iterativo di un diffusion model**.

![](imgs/Pasted%20image%2020261002015957.png)

![](imgs/Pasted%20image%2020261002020009.png)

Tra le applicazioni scientifiche rientra anche **AlphaFold 3**, previsione delle strutture proteiche, ricerca farmacologica e scoperta di materiali e composti. Il riferimento ai “20 + 2” amminoacidi evidenzia come un insieme limitato di componenti consenta un’enorme varietà di proteine; non significa che ogni proteina contenga tutti i 22 tipi.

Il diagramma richiama il Nobel per la chimica 2024: David Baker per la progettazione computazionale di proteine, Demis Hassabis e John Jumper per la previsione delle strutture. Il filo comune è usare modelli computazionali per esplorare strutture e relazioni difficili da trattare soltanto con procedure manuali.

#### **6.4. Robotica, Edge AI e apprendimento nel tempo**

Nella **robotica**, percezione, controllo e decisione producono effetti fisici. La panoramica presenta robot umanoidi, fra cui Optimus, esempi Boston Dynamics e Agility Robotics, manipolatori e mezzi per magazzini.

Destrezza, autonomia e movimento fra merci e persone collegano AI, automazione industriale e supply chain; l’atlante visivo degli umanoidi illustra la varietà delle piattaforme, senza fornire qui una valutazione comparativa delle prestazioni.

![](imgs/Pasted%20image%2020261002020104.png)

![](imgs/Pasted%20image%2020261002020118.png)

**Edge AI / elaborazione AI al margine della rete** significa eseguire almeno parte dell’elaborazione vicino alla sorgente dei dati, per esempio sul dispositivo. Nel confronto del materiale, il cloud offre calcolo e archiviazione remoti; l’edge è associato a latenza ridotta, riservatezza/sicurezza e risparmio energetico.

Sono motivazioni progettuali, non garanzie automatiche: l’effetto reale dipende da modello, hardware, comunicazioni e carico di lavoro. Il corso cita acceleratori per smartphone di Apple, Google e Qualcomm.

![](imgs/Pasted%20image%2020261002020136.png)

Due ulteriori direzioni riguardano aspetti diversi dell’apprendimento:

- Nel **self-supervised learning**, il segnale viene costruito a partire dai dati stessi, riducendo la necessità di annotazioni manuali. Le applicazioni citate comprendono immagini radiografiche e riconoscimento vocale con wav2vec 2.0.
- Nel **continual learning**, il modello si aggiorna nel tempo per affrontare nuovi dati o compiti. L’esempio contrappone un unico ciclo di apprendimento e distribuzione a un aggiornamento continuo, come può servire nella guida autonoma.

Il diagramma mostra un dataset senza etichette, un _pretext task_ di preaddestramento e il trasferimento della conoscenza a un _target task_ con dati etichettati.

![](imgs/Pasted%20image%2020261002020243.png)

La prima distinzione riguarda **da dove viene la supervisione**, la seconda **quando e come si aggiorna il modello**. Come chiarimento aggiuntivo, apprendere da dati nuovi richiede anche di controllare che non si perdano capacità già acquisite; non basta riavviare l’addestramento senza misurarne gli effetti.

#### **6.5. Governance e responsabilità**

Il corso introduce anche l’AI Act, l’uso responsabile e la mitigazione dei bias. La classificazione distingue rischio inaccettabile, alto, limitato — con specifici obblighi di trasparenza — e minimo.

Il calendario originario scandiva l’entrata in vigore il 1° agosto 2024, i divieti dal 2 febbraio 2025, gli obblighi per i sistemi di intelligenza artificiale per finalità generali (GPAI, *General-Purpose AI*, AI per finalità generali) e le autorità nazionali dal 2 agosto 2025 e l’applicazione generale dal 2 agosto 2026. Il Regolamento (UE) 2026/1744 ha poi modificato alcune decorrenze: specifiche disposizioni dell’articolo 5 si applicano dal **2 dicembre 2026**; per i sistemi generativi già immessi sul mercato prima del 2 agosto 2026, il termine transitorio per adeguarsi all’obbligo di marcatura dell’articolo 50(2) è il 2 dicembre 2026; le scadenze per i sistemi ad alto rischio sono il 2 dicembre 2027 per quelli dell’Allegato III e il 2 agosto 2028 per quelli dell’Allegato I. Il calendario va letto disposizione per disposizione e in base alla categoria del sistema. [Regolamento (UE) 2026/1744](https://eur-lex.europa.eu/legal-content/EN/TXT/?uri=CELEX%3A32026R1744).

![](imgs/Pasted%20image%2020261002020217.png)

Si conserva **il calendario rappresentato dalla fonte**, distinguendo entrata in vigore e applicazione delle disposizioni. È una panoramica introduttiva, non una checklist di conformità: la base normativa originaria è il [Regolamento UE 2024/1689](https://eur-lex.europa.eu/eli/reg/2024/1689/oj/eng), e questa panoramica non sostituisce il controllo di disposizioni e modifiche applicabili a un caso concreto.

### **7. Lavorare con un modello linguistico**

#### **7.1. Collaborazione fra progettista e modello**

Gli LLM possono assistere quattro attività di progettazione: generare e rifinire idee, scrivere o correggere codice, esplorare dataset e produrre analisi, preparare documentazione e spiegazioni. In ogni caso accelerano una parte del lavoro; requisiti, calcoli, dati e risultati vanno verificati prima dell’impiego. La pipeline parte da dati puliti, prosegue con esplorazione, formulazione della domanda, costruzione del modello, interpretazione e presentazione.

Sopra compare “Human Intelligence + LLM”: il modello può assistere le fasi, mentre il progettista deve controllare significato e correttezza del risultato.

![](imgs/Pasted%20image%2020261002020730.png)

In pratica, possono sostenere brainstorming e prototipazione, scrivere codice in qualsiasi linguaggio di programmazione per problemi industriali, ambientali e logistici, esplorare dati, preparare report e documentazione, e spiegare metodologie. Un esempio domanda se, per migliorare la spiegabilità, convenga una rete neurale o un albero decisionale.

La risposta nell’esempio privilegia l’albero perché rende visibili nodi decisionali, regole, importanza delle feature e percorsi traducibili in **IF–THEN**.

![](imgs/Pasted%20image%2020261002020748.png)

Il valore didattico è partire da un **requisito**, la spiegabilità, e confrontare alternative. Come precisazione, l’albero deve restare di dimensioni comprensibili; un’indicazione sull’importanza delle feature non dimostra causalità e una spiegazione linguistica convincente non certifica il funzionamento interno di un modello.

#### **7.2. Esempio di codice generato: un livello convoluzionale**

L’esempio chiede una chiamata Keras per immagini di $256\times256$ pixel e 25 filtri di dimensione $3\times3$. Una possibile risposta generata da GPT è:

```python
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Input

# Define the model
model = Sequential([
    Input(shape=(256, 256, 3)),  # Input shape for 256x256 RGB (*Red, Green, Blue*, rosso, verde e blu) images
    Conv2D(filters=25, kernel_size=(3, 3), activation="relu")
])

# Print model summary
model.summary()
```

![](imgs/Pasted%20image%2020261002020820.png)

`Input` dichiara altezza, larghezza e tre canali RGB; questa scelta dei canali è aggiunta nella risposta generata. `Conv2D` costruisce 25 filtri e usa ReLU come attivazione. `summary()` descrive l’architettura: **non addestra il modello** e non dimostra che risolva una classificazione. Mancano dati, obiettivo, procedura di apprendimento e una testa di uscita adatta al task.

**Controllo esplicativo:** con configurazione `channels_last`, stride 1 e padding `valid`, i default documentati di [Conv2D](https://keras.io/api/layers/convolution_layers/convolution2d/) danno una dimensione spaziale $256-3+1=254$, quindi l’output per immagine è $254\times254\times25$. Ogni filtro ha $3\cdot3\cdot3$ pesi e un bias: i parametri sono $(27+1)\cdot25=700$.

È un controllo di coerenza del frammento, non un risultato sperimentale.

#### **7.3. Contesto, analisi e allucinazioni**

Lo schema dell’assistenza all’analisi distingue **utente, prompt, context window, LLM e output**, con una freccia di ritorno che richiama l’iterazione. La finestra di contesto è l’informazione fornita al modello nell’interazione; non va confusa con tutto ciò che esiste in un archivio esterno.

Per analizzare un dataset occorre che i dati necessari siano effettivamente accessibili e che i calcoli dichiarati siano eseguiti e verificati.

![](imgs/Pasted%20image%2020261002020947.png)

Le **hallucinations / allucinazioni** sono output coerenti nella forma e grammaticalmente plausibili che contengono informazioni false o prive di senso. L’esempio propone una domanda con premessa infondata, la capitale di Marte, e una risposta inventata, “Elonville”; l’esempio superiore mostra una trascrizione con una lunga sequenza numerica anomala.

Sono esempi di output da verificare, non informazioni sul mondo.

![](imgs/Pasted%20image%2020261002021018.png)

> ⚠️ Correttezza linguistica, quantità di dettagli e tono sicuro non sono prove di correttezza fattuale.

Anche una risposta utile può contenere un singolo elemento inventato che rende inutilizzabile il risultato.

#### **7.4. Privacy, bias e dipendenza dai dati di training**

Un rischio concreto è esporre dati riservati usando servizi LLM pubblici, con possibili incompatibilità con NDA e GDPR. Il diagramma interpone un **privacy layer** fra il prompt con dati personali (PII, *Personally Identifiable Information*, informazioni personali identificabili) e il servizio: il flusso di andata rimuove i dati identificativi e quello di ritorno è controllato prima dell’output all’utente.

Il riquadro contempla anche informazioni sensibili nella risposta; il controllo non riguarda quindi solo il prompt.

![](imgs/Pasted%20image%2020261002022545.png)

Questa è una misura architetturale illustrativa, **non una garanzia automatica di anonimato o conformità**. Il tema si collega alla scelta fra elaborazione locale e remota: occorre sapere dove passano i dati e quali componenti possono trattarli.

Gli LLM possono inoltre riprodurre **bias presenti nei dati di addestramento**. Una risposta può essere poco adatta a un dominio industriale specialistico o basata su informazioni datate.

La conoscenza generale del modello non sostituisce la documentazione del dominio né la verifica sui dati effettivi.

#### **7.5. Un esempio di risposta plausibile ma falsa**

Un esperimento con una versione indicata come “ChatGPT 3” mostra quanto sia facile ottenere una risposta inventata. Alla domanda su Fabio Scotti, il sistema costruisce una biografia di matematico di geometria algebrica alla Milano-Bicocca, con dettagli su ricerca e carriera.

La biografia prodotta è **errata**: la fluidità della risposta non l’ha resa attendibile.

![](imgs/Pasted%20image%2020261002022634.png)

Aggiungendo il contesto “professore all’Università di Milano”, il modello cambia risposta ma inventa una cattedra di statistica e attribuisce il libro _Statistical Models and Methods for Financial Markets_. La copertina del volume attribuisce invece il libro a Tze Leung Lai e Haipeng Xing.

Il contesto restringe l’interpretazione della domanda, ma non rende automaticamente veri i dettagli prodotti.

![](imgs/Pasted%20image%2020261002022652.png)

Nell’esempio sui propri limiti, il modello dichiara di non provare emozioni, non avere opinioni o bias personali, non svolgere azioni fisiche, non cogliere l’umorismo come una persona, e pone limiti a consulenze, attività illegali e accesso a informazioni private. L’esempio mette in luce soprattutto le difficoltà nel cogliere **sfumature e contesto**.

Il confronto con gli esempi più recenti mostra che l’errore può cambiare forma: una biografia inventata è facile da riconoscere quando si conosce la persona, mentre una citazione plausibile ma inesistente o una chiamata errata a uno strumento possono propagarsi lungo una catena di azioni. Dichiarare meglio i propri limiti è utile, ma va misurato insieme all’affidabilità dei risultati. Queste frasi sono **autodescrizioni del modello nell’esempio**, non dimostrazioni delle sue proprietà: in particolare, “non avere bias personali” non implica che gli output siano privi di bias statistici o sociali.

![](imgs/Pasted%20image%2020261002022729.png)

Con la versione indicata come ChatGPT 4, la risposta mostrata è più pertinente all’informatica, esplicita un limite temporale della conoscenza e invita a consultare fonti ufficiali. Una risposta successiva è diversa: **gli output cambiano nel tempo**.

Il miglioramento consiste anche nel comunicare meglio i limiti; non elimina la necessità di test continui e confronto con lo stato dell’arte. Il confronto mostra che l’allucinazione si è spostata: dalla biografia inventata a citazioni plausibili ma inesistenti e chiamate errate agli strumenti dentro una catena agentica. È scomparsa o è soltanto più difficile da vedere? Le risposte più recenti rendono più leggibili le informazioni e dichiarano meglio i propri limiti; gli output, però, cambiano nel tempo. Per questo la qualità va verificata sia nelle risposte sia nelle azioni eseguite tramite gli strumenti.

![](imgs/Pasted%20image%2020261002022807.png)

Per interpretare una risposta occorre quindi sapere quali informazioni il modello ha ricevuto e quali distinzioni riesce a rappresentare. La stessa attenzione al contesto serve quando si scelgono gli input per addestramento e valutazione.

### **8. Percezione e interazione con le persone**

#### **8.1. Una classificazione sicura può essere sbagliata**

Gli esempi con AlexNet mostrano immagini artificiali che una rete addestrata su ImageNet, dataset indicato con 1,3 milioni di immagini, associa a oggetti riconoscibili: per esempio pattern etichettati come tastiera, pallone, fragola o scuolabus. Un’altra griglia contiene immagini simili a rumore, classificate come pettirosso, ghepardo, armadillo, panda minore, centopiedi, pavone, jackfruit e bolla.

![](imgs/Pasted%20image%2020261002023034.png)

![](imgs/Pasted%20image%2020261002023044.png)

Il punto è la differenza fra caratteristiche che attivano un classificatore e ciò che un essere umano riconosce come un oggetto. La fonte scientifica dell’esempio, [Nguyen e colleghi, Deep Neural Networks are Easily Fooled](https://arxiv.org/abs/1412.1897), studia proprio predizioni ad alta confidenza su immagini irriconoscibili.

Non si tratta necessariamente di fotografie naturali alterate impercettibilmente: compaiono anche immagini sintetiche prive di un oggetto evidente. Una vignetta successiva etichetta come “Snail” un gatto dentro una scatola; a fianco compare una persona con passeggino sulle strisce pedonali.

Il passaggio dall’umorismo alla preoccupazione serve a ricordare che **la conseguenza dell’errore dipende dal task**: sbagliare il riconoscimento in un veicolo autonomo ha implicazioni diverse da una didascalia buffa. Una verifica concreta consiste nel chiedersi che cosa accadrebbe se un veicolo autonomo dotato di sole telecamere RGB interpretasse male la scena. Il rischio dipende dagli oggetti e dalle relazioni che l’immagine consente di rilevare, dalla robustezza della percezione e dalle misure previste quando il sistema è incerto.

![](imgs/Pasted%20image%2020261002023057.png)

#### **8.2. Che cosa significa interagire naturalmente**

La **Natural Interaction / interazione naturale** mira a rendere l’interazione uomo–macchina più vicina alle modalità umane: linguaggio, gesti, segnali emotivi e contesto. La fotografia del bambino con un robot sociale rappresenta questa promessa.

Riconoscere correttamente un segnale è però diverso dal comprenderne tutte le implicazioni in una situazione reale.

![](imgs/Pasted%20image%2020261002023122.png)

Le modalità considerate comprendono:

- **Voce e NLP:** comandi vocali in applicazioni sanitarie, smart home e navigazione. Il riconoscimento trasforma il parlato in una rappresentazione; l’elaborazione linguistica interpreta la richiesta.
- **Gesti:** controllo di robot industriali, formazione in realtà aumentata e traduzione della lingua dei segni. Il sistema deve distinguere movimenti significativi da quelli incidentali.
- **Interazione aptica:** contatto, forza e feedback tattile in simulazioni chirurgiche, dispositivi indossabili e diagnostica industriale.
- **Riconoscimento visivo ed emotivo:** analisi del sentiment, coinvolgimento degli studenti e possibili applicazioni in salute mentale. L’inferenza di uno stato interno da segnali esterni resta un’interpretazione, non una lettura diretta della mente.

![](imgs/Pasted%20image%2020261002023158.png)

Il **context-aware sensing** interpreta le misure considerando la situazione; i **sistemi di feedback adattivo** modificano la risposta in base a ciò che osservano. Gli esempi sono manutenzione predittiva in fabbrica, piattaforme di apprendimento adattive e traffico delle smart city.

Il diagramma multimodale collega immagini, audio e video a encoder specifici, poi a proiezioni di ingresso che allineano le rappresentazioni al modello linguistico centrale. Dal modello partono proiezioni d’uscita e componenti di generazione per immagini, audio e video; il testo ha il proprio percorso.

Le etichette inferiori distinguono codifica multimodale, allineamento al LLM, elaborazione semantica, allineamento alle istruzioni e generazione dell’output. È **un’architettura esemplificativa**, non la struttura obbligatoria di ogni modello multimodale.

![](imgs/Pasted%20image%2020261002023221.png)

![](imgs/Pasted%20image%2020261002023232.png)

#### **8.3. Perché una bassa percentuale d’errore può non bastare**

Il confronto sul riconoscimento vocale riporta un tasso inferiore al 5% con valori superiori al 10% di anni precedenti, e attribuisce ad Andrew Ng l’indicazione di scendere sotto l’1% nelle applicazioni reali per un’interazione naturale. Sono stime e un’indicazione pratica, non soglie universali o certificate per ogni sistema attuale.

L’esempio dice che, su 800 parole, un tasso d’errore del 5% corrisponde a:

$$
800\cdot0{,}05=40.
$$

Quaranta è il conteggio al 5%; un tasso inferiore produrrebbe meno errori nell’approssimazione dell’esempio. Più del conteggio conta la funzione delle parole: sbagliare una negazione, un numero d’ordine o una destinazione può invalidare l’intero compito.

> 📌 La qualità di un’interazione va valutata sul successo del task e sulle conseguenze degli errori, non soltanto sulla percentuale media di parole riconosciute.

#### **8.4. Voice picking e cobot**

Nel **voice picking** logistico, l’operatore consulta tramite cuffie le istruzioni per il prelievo e interagisce con il sistema durante l’esecuzione, mantenendo le mani disponibili per movimentare le merci. A questa soluzione vengono associati produttività, accuratezza e sicurezza; il precedente esempio chiarisce perché un codice o una quantità riconosciuti male possano compromettere il risultato.

![](imgs/Pasted%20image%2020261002023344.png)

L’interazione non si limita alla voce. I **cobot**, noti come _Cooperative Robots_, cooperano con le persone in attività fisiche.

In questo esempio si vede il rapporto fra mano umana, manipolatore e interfaccia tablet usata per impartire istruzioni. L’aspetto intelligente riguarda quindi anche la possibilità di comunicare intenzioni e coordinare attività.

![](imgs/Pasted%20image%2020261002023401.png)

### **9. Bias: quando si apprende la relazione sbagliata**

#### **9.1. Bias umani e rappresentazione dei dati**

L’analogia introduttiva è quella del bambino che migliora nel tempo ma il cui apprendimento dipende da chi insegna. Un modello può acquisire non soltanto regolarità utili, ma anche distorsioni della fonte dei dati.

Nel corso i **bias cognitivi** sono definiti come deviazioni sistematiche nel giudizio rispetto a una norma o alla razionalità. L’esempio attribuito a Daphne Koller riguarda una ricerca di immagini per “CEO” che restituisce prevalentemente uomini bianchi, con una “CEO Barbie” come rara rappresentazione femminile.

L’esempio riporta il conteggio illustrativo di 50 immagini maschili e una Barbie. Il caso illustra come una collezione di dati o risultati possa riprodurre stereotipi e squilibri; il valore citato è storico, non una misurazione attuale del motore di ricerca.

![](imgs/Pasted%20image%2020261002023437.png)

![](imgs/Pasted%20image%2020261002023504.png)

Non bisogna attribuire al modello un’intenzione personale per riconoscere il problema. È sufficiente che l’apprendimento o il recupero di contenuti conservi una rappresentazione distorta, che si rifletterà nei risultati.

#### **9.2. Il caso delle radiografie provenienti da ospedali diversi**

Un secondo esempio mostra un bias meno evidente. Si vuole prevedere la presenza di fratture usando radiografie raccolte da più ospedali.

Gli apparecchi producono immagini con caratteristiche differenti e gli ospedali non hanno la stessa percentuale di fratture: un grande reparto traumatologico può trattarne molte più di altre strutture. Il modello può imparare a riconoscere nelle immagini le caratteristiche dell’apparecchio o dell’ospedale, associarle alla frequenza di fratture osservata in quella provenienza e ottenere buone predizioni nel dataset disponibile **senza analizzare realmente l’osso**.

![](imgs/Pasted%20image%2020261002023556.png)

La correlazione funziona nei dati raccolti, ma può fallire cambiando ospedale, apparecchio o composizione dei pazienti. Il problema è che si è appresa una proprietà della raccolta dei dati al posto della relazione desiderata.

Come conseguenza metodologica aggiunta, valutare il sistema su provenienze diverse aiuta a capire se la prestazione dipenda da tale scorciatoia.

> ⚠️ Un’alta accuratezza sul dataset disponibile non dimostra che il modello utilizzi le caratteristiche giuste o che generalizzi al contesto di impiego.

### **10. Il Data–Knowledge Spectrum**

#### **10.1. Rapporto fra dati e conoscenza del processo**

Il diagramma mette in relazione la quantità di dati richiesta con la conoscenza disponibile del processo: quando si conoscono poche regole che lo governano, il sistema deve ricavare più struttura dai dati. L’asse verticale è **DATA**, quello orizzontale **KNOWLEDGE**.

La curva scende andando verso destra. Si incontrano quattro situazioni: assenza di conoscenza, comprensione parziale — esempio: visual object detection —, modello matematico dei dati — crescita logistica — e leggi della natura — seconda legge di Newton.

Le etichette Deep NN e Traditional NN indicano posizioni indicative sullo spettro. Il diagramma, tratto da _Machine Learning Refined_ di Watt, Borhani e Katsaggelos, offre anche un criterio concettuale per valutare la forza delle feature progettate.

L’inciso etimologico su _speciō_, “guardare”, accompagna il termine _spectrum_ senza introdurre una nozione tecnica ulteriore.

![](imgs/Pasted%20image%2020261002023631.png)

Quando una legge o una struttura affidabile è già nota, i dati possono servire soprattutto a stimarne pochi parametri. Quando manca questa conoscenza, il modello deve ricavare anche una rappresentazione e relazioni più complesse, e tipicamente richiede più dati. **Il grafico è qualitativo**: non fornisce una formula per calcolare il numero di esempi necessari e non afferma che più dati compensino qualsiasi difetto di qualità.

#### **10.2. Primo esempio: crescita di una popolazione e fitting**

Consideriamo una popolazione che cresce senza vincoli e una che incontra limiti dovuti alla competizione. Nel secondo caso l’aumento rallenta fino a una capacità $K$, posta a 2.000 nell’esempio teorico.

I dati sperimentali riguardano una popolazione di **Paramecium** per millilitro, osservata per sei giorni: i punti seguono una curva a S che tende a stabilizzarsi.

![](imgs/Pasted%20image%2020261002023649.png)

In questo caso abbiamo già un **modello specifico e semplice**. Fare _fitting_ significa adattarne i parametri ai dati osservati.

Non serve imporre una rete enorme per riscoprire da zero una struttura già nota: aumentare i gradi di libertà senza una ragione complica il problema. L’esempio illustra la forma della crescita, senza fornire un’equazione o i valori numerici dei singoli punti.

#### **10.3. Secondo esempio: visual object detection**

Nel rilevamento visivo degli oggetti, gli ingressi sono immagini e il task comprende l’identificazione e la localizzazione degli oggetti. Si possono immaginare riquadri che identificano un notebook, un laptop, gli occhiali, un telefono e una tazza di caffè in una fotografia; un insieme di immagini etichettate fornisce gli esempi da cui apprendere.

![](imgs/Pasted%20image%2020261002023708.png)

Non si dispone di una piccola formula esplicita che trasformi ogni possibile immagine negli oggetti presenti. Occorre apprendere relazioni più generali, ma le **label / etichette** forniscono almeno il risultato desiderato per gli esempi.

Il caso presuppone una conoscenza solo parziale del processo: le etichette dicono che cosa cercare negli esempi, mentre il modello deve imparare a riconoscere forme e contesti visivi. La differenza rispetto al fitting precedente riguarda soprattutto la **conoscenza iniziale**.

Non significa che ogni problema visivo richieda necessariamente il modello più grande disponibile: la scelta concreta dipende dai dati, dal compito e dalle risorse.

#### **10.4. Terzo esempio: attacchi informatici senza etichette**

Nel caso di sicurezza si dispone di milioni di log, **senza etichette**, e si cerca di capire quali comportamenti o feature identifichino attacchi nell’applicazione considerata. Mancano sia un modello specifico già noto sia la risposta corretta associata a ogni record.

![](imgs/Pasted%20image%2020261002023722.png)

Senza etichette non si può addestrare direttamente un classificatore supervisionato di attacchi. Si possono invece cercare strutture o anomalie con metodi non supervisionati, che richiedono interpretazione. Un’anomalia non è automaticamente un attacco: può essere un comportamento raro ma legittimo.

Modelli generali capaci di lavorare senza label possono aiutare a individuare pattern; l’assenza di etichette, da sola, non impone una rete neurale grande. Il confronto fra i tre casi conduce alle domande progettuali fondamentali: **che cosa sappiamo del processo, quali dati abbiamo, quali risultati conosciamo già e quale parte dobbiamo apprendere?**

### **11. Caso integrato: consegna mediante veicoli autonomi**

#### **11.1. Un sistema composto da sottosistemi**

Consideriamo **Ottobot di Ottonomy**, un robot autonomo per la consegna di cibo, descritto come capace di muoversi in luoghi affollati senza GPS. Per riuscirci, il robot deve coordinare funzioni distinte: interazione in linguaggio naturale, visione, elaborazione dei sensori, autocalibrazione e diagnosi, elaborazione locale, gestione della folla, navigazione e ricostruzione della mappa.

![](imgs/Pasted%20image%2020261002023739.png)

Per portare a termine una consegna non basta classificare una fotografia. Il robot deve stimare dove si trova, riconoscere ciò che lo circonda, scegliere un percorso, controllare i movimenti, interagire con la persona e rilevare problemi interni.

Alcune elaborazioni devono avvenire localmente: affidare ogni decisione a una comunicazione remota introduce una dipendenza incompatibile con determinate esigenze di reazione.

#### **11.2. Tecnologie e modelli associati alle funzioni**

Una **mappa generale delle componenti per sistemi autonomi** collega tecniche di Machine Learning con metodi classici di controllo e stima. I componenti effettivamente installati su Ottobot possono differire da questa panoramica.

Le funzioni si distribuiscono fra localizzazione, controllo, pianificazione, interazione, manutenzione, percezione ed elaborazione edge, ciascuna associata a tecnologie e metodi differenti.

![](imgs/Pasted%20image%2020261119231612.png)

Per leggere la tabella: **SLAM** significa _Simultaneous Localization and Mapping_, cioè localizzazione e costruzione della mappa contemporanee; **GNSS** indica sistemi satellitari di navigazione, con GPS e tecniche RTK (_Real-Time Kinematic_) citati come riferimenti. **IMU** è un’unità di misura inerziale. **PID** indica un controllo proporzionale–integrale–derivativo e **MPC** un _Model Predictive Control_. FPGA e TPU sono piattaforme hardware, mentre YOLO, Faster R-CNN e PointNet sono modelli/famiglie di elaborazione. **Quantizzare** significa usare rappresentazioni numeriche a precisione ridotta, con effetti da valutare su costo e accuratezza.

La tabella include GPS fra le alternative per i sistemi autonomi; il caso di Ottobot descrive invece un robot capace di operare senza GPS. Le tecnologie elencate sono opzioni, non componenti obbligatori per ciascuna funzione.

#### **11.3. Collegamenti da conservare nello studio**

Un progetto solido unisce conoscenza dei modelli e dei loro limiti, esperienza applicativa, aggiornamento sullo stato dell’arte, gestione dei dati e scelta degli strumenti. I progressi dell’AI sono accompagnati da problemi di **bias, interazione naturale e spiegabilità**.

Il Data–Knowledge Spectrum spiega perché, al diminuire della conoscenza disponibile sul processo, cresca in genere il lavoro che deve essere svolto a partire dai dati.

> ✅ Progettare un sistema intelligente significa scegliere e collegare dati, conoscenza, modelli, strumenti e vincoli.

Ogni risultato va verificato sul task reale. La complessità del modello va scelta in base al problema e alla conoscenza disponibile. Un sistema intelligente si progetta mettendo in relazione capacità del modello, qualità dei dati, conoscenza del dominio, risorse tecniche e contesto d’uso.

Per consultare l’organizzazione complessiva degli appunti, torna all’[indice](../L0%20-%20Indice%20del%20corso.md).
