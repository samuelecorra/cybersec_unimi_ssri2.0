## **Lezione 1: Introduzione ai sistemi intelligenti**

Il corso **Intelligent Systems for Industry, Supply Chain and Environment**, abbreviato **IS4**, di Fabio Scotti studia come trasformare modelli e tecniche di intelligenza artificiale in sistemi utilizzabili nell’industria, nella logistica e nell’ambiente. Questa prima lezione introduce il linguaggio del corso, la sua organizzazione, le modalità d’esame descritte nelle slide e i problemi che ricorreranno nella progettazione: scelta dei dati e dei modelli, interazione con le persone, bias e rapporto fra dati disponibili e conoscenza del processo.

Fonte: [PDF della Lezione 01](Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf), 82 pagine. Le panoramiche su prodotti, risultati e regole d’esame descrivono **la versione del materiale acquisita**; le spiegazioni aggiuntive sono distinte dalle affermazioni delle slide. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **1. Che cos’è un Intelligent System**

#### **1.1. Una definizione operativa**

Non esiste una sola definizione indiscussa di intelligenza. Per lavorare sul progetto di applicazioni, il docente propone una descrizione operativa: un **Intelligent System / sistema intelligente** è un sistema basato su elaboratore che mira a riprodurre capacità cognitive umane quali **apprendimento, percezione, ragionamento e decisione**.

Queste capacità hanno ruoli diversi. La percezione interpreta ciò che arriva da sensori, immagini o voce; l’apprendimento ricava regolarità dagli esempi; il ragionamento collega informazioni e conoscenze; la decisione seleziona una risposta o un’azione. Un sistema può combinare alcune di queste capacità senza riprodurre integralmente l’intelligenza umana.

Attraverso **Machine Learning / apprendimento automatico** e altre tecnologie, il sistema elabora dati per eseguire compiti che normalmente richiedono intelligenza umana, formulare previsioni o ricavare indicazioni utili. La definizione riguarda quindi la **funzione dell’intero sistema**, non soltanto l’algoritmo al suo interno.

> 📌 Un modello è una componente di un sistema intelligente. Il sistema comprende anche dati, sensori, software, hardware, interfacce, vincoli operativi e modalità di impiego dei risultati.

#### **1.2. Glossario iniziale**

| Sigla | Espansione | Significato nel corso |
|---|---|---|
| AI | Artificial Intelligence | Intelligenza artificiale, ambito generale di metodi e sistemi. |
| IS | Intelligent Systems | Sistemi intelligenti, cioè le applicazioni costruite con tali metodi. |
| ML | Machine Learning | Tecniche che apprendono dai dati. |
| NN | Neural Network | Rete neurale, una famiglia di modelli di ML. |
| Train/Test | Training / Testing | Addestramento e valutazione del modello. |
| HW/SW | Hardware / Software | Componenti fisiche e programmi. |
| I/O | Input / Output | Dati in ingresso e risultati in uscita. |

La slide introduttiva accosta testing e validation. **Precisazione metodologica:** la validazione serve normalmente a scegliere modelli e configurazioni; il test finale stima il comportamento su dati tenuti separati dalle scelte. Il dettaglio sarà sviluppato nelle lezioni sul workflow e sulla partizione dei dataset: qui basta evitare di confondere l’apprendimento con la sua verifica.

#### **1.3. Esempi, settori e task**

Gli esempi iniziali comprendono assistenti virtuali come Siri e Alexa, veicoli autonomi, sistemi di diagnosi medica, raccomandazione, riconoscimento di immagini e voce, chatbot, manutenzione predittiva, rilevamento delle frodi e **sentiment analysis**, cioè analisi dell’orientamento espresso in un contenuto. Le immagini della slide affiancano sensori visivi, un’interfaccia conversazionale e radiografie con regioni evidenziate: input e output cambiano molto da un’applicazione all’altra.

<!-- SOURCE_VISUAL id="IS01-V01" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="6" type="esempi visivi" description="Sensore visivo, chatbot e radiografie con mappe di evidenziazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Per leggere questa varietà conviene distinguere tre livelli:

- Il **settore** è l’ambiente di applicazione: aviazione, informatica, educazione, finanza, industria pesante, ospedali e medicina, marketing, media, musica, editoria e notizie, assistenza clienti online o telefonica, manutenzione delle telecomunicazioni, giocattoli e giochi, trasporti. In finanza le slide distinguono trading algoritmico, analisi di mercato e data mining, finanza personale, gestione di portafoglio e valutazione del credito.
- Il **task** è il problema informatico: OCR (*Optical Character Recognition*), riconoscimento della scrittura, della voce o dei volti; creatività artificiale; computer vision, realtà virtuale ed elaborazione delle immagini; diagnosi; teoria dei giochi e pianificazione strategica; bot e AI dei videogiochi; NLP (*Natural Language Processing*), traduzione e chatbot; controllo non lineare e robotica.
- L’**applicazione** combina uno o più task per uno scopo concreto. Le slide citano ricerca sul Web, biologia computazionale, finanza, e-commerce, esplorazione di spazi e luoghi — aree, città, abitazioni —, robotica, estrazione di informazioni, social network e debugging.

Per esempio, il riconoscimento vocale è un task; la logistica è un settore; il prelievo di merci guidato dalla voce è un’applicazione. Questa distinzione aiuta a trasferire una tecnica fra domini senza presumere che dati e vincoli restino identici.

La diffusione dell’AI è collegata alla possibilità di elaborare grandi quantità di dati, individuare pattern, accorgersi prima dei problemi e distribuire meglio le risorse. Il grafico Google Trends presente nel PDF mostra una forte crescita dell’interesse per la ricerca “AI”, soprattutto nella parte finale della serie. **Misura interesse di ricerca, non accuratezza dei modelli né quota di imprese che li adottano.** Le immagini cinematografiche della slide precedente servono invece a contrapporre l’immaginario fantascientifico agli usi quotidiani.

<!-- SOURCE_VISUAL id="IS01-V02" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="8" type="grafico" description="Schermata Google Trends mondiale della ricerca AI dal 2004, con crescita finale dell’interesse" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **2. Organizzazione del corso e metodo di progetto**

#### **2.1. Quattro tipi di contenuto che si completano**

Il corso intreccia **Theory of AI**, **Tools (SW–HW)**, **Overviews** e **Use cases**. La figura a quattro settori e le frecce centrali indicano una relazione circolare: una scelta teorica va confrontata con strumenti e casi reali, mentre un problema applicativo può richiedere di rivedere modello e dati. I confini sono volutamente sfumati.

<!-- SOURCE_VISUAL id="IS01-V03" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="12" type="schema" description="Quattro settori Theory of AI, Tools, Overviews e Use cases con frecce di collegamento" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La **teoria** fornisce ciò che serve a capire il funzionamento: pattern matching, apprendimento, analisi ed elaborazione dei dati, estrazione e fusione di feature. Una **feature / caratteristica** è una proprietà con cui si rappresenta un caso per il modello; più avanti si vedrà quanto la rappresentazione incida sull’apprendimento.

Gli **strumenti software** servono ad analizzare, trasformare e visualizzare dati e a costruire ambienti di apprendimento. Le slide citano MATLAB, TensorFlow e Google Colab, con l’invito a mantenere flessibilità nella scelta. La parte **hardware** considera CUDA, schede dedicate, edge e fog computing: luogo di esecuzione e risorse disponibili sono parte del progetto.

Le **overviews / panoramiche** orientano nello stato dell’arte: che cosa si può fare nei diversi mercati, quali strumenti e dataset servono, quali limiti restano e quali direzioni di ricerca stanno emergendo. Il docente specifica che **anche queste informazioni fanno parte del programma d’esame**.

Gli **use cases / casi d’uso** rendono operativi i concetti attraverso esempi, gestione dei dati, input e output, risultati attesi, codice e frammenti di programma, strumenti hardware e software. Studiare un caso significa capire perché una configurazione risolve quel problema, non memorizzare soltanto il nome del modello.

#### **2.2. Dalla teoria all’applicazione**

Le attività comprendono lezioni su termini, nozioni, teoria, progettazione ed esempi; laboratori simulati con problemi e soluzioni mediante gli strumenti citati; simulazioni dell’esame. La figura organizza queste attività in un percorso verso la prova scritta.

<!-- SOURCE_VISUAL id="IS01-V04" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="17" type="percorso didattico" description="Lezioni in aula e laboratorio simulato confluiscono nell’esame scritto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Un secondo schema scende attraverso livelli di astrazione: modelli e metodi di apprendimento, AI classica e pattern matching, sviluppi del deep learning, applicazioni industriali/ambientali/supply chain, casi d’uso. La teoria deve quindi arrivare fino alla scelta concreta di dati, strumenti e comportamento del sistema.

<!-- SOURCE_VISUAL id="IS01-V05" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="20" type="schema" description="Freccia dei livelli di astrazione dalla teoria ai casi d’uso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le slide possono essere aggiornate dopo la lezione per correggere refusi, migliorare la leggibilità e aggiungere esempi o collegamenti. Il docente invita a **riscaricare i PDF al termine del corso**. Nella ricostruzione, ogni versione acquisita resta identificabile come fonte: un aggiornamento del materiale richiede un nuovo confronto, non l’ipotesi che il contenuto sia rimasto uguale.

#### **2.3. Perché saper addestrare una rete non basta**

**Machine Learning non coincide con Deep Neural Networks.** Conoscere bene un solo strumento può portare ad applicarlo anche quando il problema richiede altro. La slide del martello e del chiodo rappresenta proprio questa distorsione: la disponibilità di una tecnica non ne dimostra l’adeguatezza.

<!-- SOURCE_VISUAL id="IS01-V06" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="22" type="analogia visiva" description="Martello e chiodo come richiamo al rischio di usare un solo strumento per ogni problema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il progettista deve conoscere differenze e limiti dei modelli, organizzare i dati di addestramento, evitare errori tipici, capire le peculiarità del settore e costruire una propria dotazione di strumenti. Prima dell’addestramento occorre:

1. ricostruire lo **stato dell’arte**, includendo metodi precedenti, metodologie, buone pratiche, tendenze della ricerca e soluzioni presenti nella letteratura e nel mercato;
2. valutare se **aggregare o fondere dati**, conoscenze o feature possa migliorare l’apprendimento;
3. impostare **confronti equi** fra metodi e prestazioni;
4. riconoscere caratteristiche comuni e differenze fra domini e applicazioni;
5. costruire tabelle, grafici e visualizzazioni per comprendere i dati;
6. soltanto dopo, procedere a **training, validation e deployment**, cioè addestramento, verifica delle scelte e messa in esercizio.

Un confronto non è equo se attribuisce al modello un vantaggio dovuto invece a dati più favorevoli o a condizioni diverse. Questa osservazione spiega perché il corso dedichi spazio alla gestione dei dati e non soltanto alle architetture neurali.

### **3. Esame e preparazione secondo il materiale del corso**

#### **3.1. Struttura della prova**

Il PDF descrive una **prova scritta a risposta multipla, svolta in laboratorio al PC**, senza orale e senza progetto. La durata indicata è di circa un’ora. Gli argomenti comprendono teoria, panoramiche, strumenti, applicazioni, casi d’uso, progettazione di casi semplici e interpretazione del codice visto a lezione. Domande e risposte si basano sul materiale didattico fornito.

La versione acquisita assegna **1 punto all’unica risposta corretta e 0 alle risposte sbagliate**, seguiti da una normalizzazione del punteggio in voto. Il PDF non specifica qui la formula di normalizzazione né il numero totale di domande.

> ⚠️ La riga che assegnava −1 a una risposta particolarmente sbagliata è **barrata** nella slide 29; sotto compare “No more negative weights”. Riprodurla come regola vigente del PDF sarebbe un errore di lettura.

<!-- SOURCE_VISUAL id="IS01-V07" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="29" type="annotazione significativa" description="Regola della penalità negativa barrata e sostituita da No more negative weights" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Si può sostenere la prova a ogni appello; non sono consentiti libri, PC personale o materiale di consultazione. È obbligatoria l’iscrizione tramite UNIMIA **per ciascun appello**: dopo un esito negativo non avviene una reiscrizione automatica. Queste sono le indicazioni della fonte archiviata, non una verifica degli avvisi amministrativi successivi.

#### **3.2. Le regole strategiche**

La “regola zero” è iscriversi regolarmente. La prima regola di ragionamento suggerisce di **escludere le risposte errate** prima di cercare direttamente quella giusta. L’esempio del docente chiede di stimare la sua età: i numeri servono soltanto a mostrare il procedimento, non a registrare un dato anagrafico.

| Alternativa dell’esempio | Ricerca diretta | Esclusione delle alternative |
|---|---|---|
| A: 49 | Fiducia indicata: 67% | Rimane la scelta dopo le esclusioni. |
| B: 35 | Altra possibilità | Esclusa con fiducia del 75%. |
| C: 12 | Altra possibilità | Esclusa con fiducia del 99,9%. |
| D: 65 | Altra possibilità | Esclusa con fiducia dell’80%. |

Le percentuali sono valutazioni illustrative, non probabilità da combinare mediante una formula. La variante della regola suggerisce, quando resta incertezza, di scegliere la risposta che appare **meno sbagliata**, motivando perché le altre non soddisfano la domanda.

Per prepararsi, il docente invita a scorrere **tutte le slide**, chiedersi quale domanda specifica possa derivare da ciascuna ed essere precisi. Le simulazioni servono a comprendere il tipo di ragionamento richiesto e a verificare se lo studio sta funzionando; **non sostituiscono lo studio del corso**. Questa lezione non aggiunge domande presunte d’esame.

### **4. Famiglie di AI, rappresentazioni e progressi**

#### **4.1. Leggere la mappa dei “Layers of AI”**

La figura attribuita a Harry Ratcliffe presenta sei strati. È una mappa introduttiva che accosta tecniche, componenti e funzioni; non una tassonomia formale in cui ogni voce escluda le altre.

<!-- SOURCE_VISUAL id="IS01-V08" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="34" type="mappa concettuale" description="Layers of AI: AI classica, ML, reti neurali, deep learning, AI generativa e agentica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

- **Classical AI:** AI simbolica, sistemi esperti, rappresentazione della conoscenza, logica e ragionamento. Si rende esplicita almeno una parte della conoscenza con cui operare.
- **Machine Learning:** apprendimento supervisionato, non supervisionato e per rinforzo; classificazione e regressione. I primi termini descrivono modalità di apprendimento; gli ultimi due descrivono task, rispettivamente assegnare categorie e stimare valori numerici.
- **Neural Networks:** percettroni, funzioni di costo, backpropagation, funzioni di attivazione e strati nascosti. La funzione di costo misura l’errore secondo un criterio; la backpropagation consente di calcolare come i parametri contribuiscono a tale errore; le attivazioni e gli strati definiscono le trasformazioni interne.
- **Deep Learning:** Transformer, LSTM (*Long Short-Term Memory*), RNN (*Recurrent Neural Networks*), CNN (*Convolutional Neural Networks*) e autoencoder. Sono famiglie che gestiscono rappresentazioni e strutture dei dati in modi differenti; una LSTM appartiene alle reti ricorrenti, quindi le etichette della figura non sono classi disgiunte.
- **Generative AI:** LLM (*Large Language Models*), diffusion models, modelli multimodali e VAE (*Variational Autoencoders*), associati alla generazione di contenuti.
- **Agentic AI:** memoria, pianificazione, uso di strumenti ed esecuzione autonoma. L’attenzione si sposta dalla singola risposta a una sequenza di attività orientate a un obiettivo.

Questi richiami spiegano la figura senza anticipare l’intera teoria delle lezioni successive. In particolare, un agente può usare un modello generativo, ma la capacità di generare testo e quella di gestire azioni non sono la stessa proprietà.

#### **4.2. Word embeddings e analogie**

Un **word embedding** rappresenta una parola o un’espressione mediante un vettore di numeri reali, utilizzabile come ingresso di un modello. L’idea è trasformare elementi linguistici in rappresentazioni su cui sia possibile calcolare relazioni. La slide richiama Word2Vec (2013) e l’evoluzione verso rappresentazioni contestuali e Transformer.

Nel disegno tridimensionale, gli assi sono *wings*, *engine* e *sky*. Le coordinate illustrative sono: goose $(2,0,4)$, eagle $(3,0,3)$, bee $(3,0,2)$, helicopter $(0,2,4)$, drone $(0,3,3)$, rocket $(0,4,2)$, jet $(1,1,1)$. Le parole diventano punti/vettori che ne esprimono proprietà. **È un’illustrazione didattica**: non sono misure fisiche né valori sperimentali di un modello; le dimensioni di un embedding appreso non hanno necessariamente etichette interpretabili così semplici.

Il secondo disegno confronta due frasi, una su un intervento di ricerca a Boston e l’altra su una lezione scientifica a Seattle. Le corrispondenze *research/science*, *talk/lecture*, *Boston/Seattle* mostrano perché parole diverse possano occupare regioni semanticamente vicine.

<!-- SOURCE_VISUAL id="IS01-V09" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="35" type="grafici di embedding" description="Vettori con assi wings-engine-sky e corrispondenze fra i due documenti Boston-Seattle" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le analogie verificano se una relazione può essere trasferita: **Parigi sta alla Francia come Tokyo sta al Giappone**. Il disegno successivo usa calzini/piedi e guanti/mani; le schermate del modello linguistico risolvono entrambe le analogie e aggiungono “automobili : città” e “formiche : colonia”, resa nella slide come formicaio.

<!-- SOURCE_VISUAL id="IS01-V10" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="36" type="analogia visiva" description="Calzini rispetto ai piedi e guanti rispetto alle mani" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS01-V11" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="37" type="schermate" description="Risposte alle analogie capitali-paesi, calzini-guanti e automobili-formiche" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Una risposta corretta dimostra successo su quel quesito, non comprensione generale o correttezza su ogni altro input. **Precisazione storica aggiunta:** la slide raggruppa Transformer ed embedding contestuali sotto “2018+”; l’articolo che introduce il Transformer risale al 2017, come documenta [Attention Is All You Need](https://arxiv.org/abs/1706.03762).

#### **4.3. Ragionamento matematico e confronto con l’intelligenza naturale**

La panoramica cita modelli di OpenAI e Google DeepMind capaci, nel contesto IMO 2025, di risolvere cinque problemi su sei mediante ragionamento in linguaggio naturale, con punteggio equivalente al livello della medaglia d’oro. La piccola tabella distingue però **condizioni di valutazione diverse**:

| Voce nella tabella della slide | Contesto | Punteggio su 42 | Interpretazione riportata |
|---|---|---:|---|
| Concorrente umano tipico | IMO 2024 | 16,0 | Sotto la soglia bronzo di quell’anno. |
| Concorrente umano tipico | IMO 2023 | 17,7 | Intorno al bronzo in diversi anni. |
| Soglia bronzo | IMO 2025 | 19+ | Bronzo. |
| Soglia argento | IMO 2025 | 28+ | Argento. |
| Soglia oro | IMO 2025 | 35+ | Oro. |
| AlphaProof + AlphaGeometry 2 | Valutazione sul modello IMO 2024 | 28 | Argento. |
| Gemini Deep Think | Valutazione ufficiale IMO 2025 | 35 | Oro. |
| Modello OpenAI | Valutazione esterna non ufficiale IMO 2025 | 35 | Risultato dichiarato equivalente all’oro. |

<!-- SOURCE_VISUAL id="IS01-V12" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="38" type="tabella di confronto" description="IMO human vs AI, punteggi e distinzione fra valutazione ufficiale ed esterna" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il messaggio della slide è la crescita delle capacità di ragionamento; la formulazione “human-level” va letta **in relazione al benchmark**, non come prova di equivalenza all’intelligenza umana in ogni contesto. Non vanno confusi punteggio equivalente, medaglia di un concorrente e modalità di certificazione.

Il confronto biologico successivo riporta per *Caenorhabditis elegans* 302 neuroni e circa 7.000 sinapsi, e per il cervello umano ordini di grandezza di $10^{11}$ neuroni e più di $10^3$ sinapsi per neurone, con richiamo a Nature del 21 agosto 2019. La figura colloca le reti artificiali fra ape e rana e le contrappone al cervello umano.

<!-- SOURCE_VISUAL id="IS01-V13" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="39" type="confronto illustrativo" description="C. elegans, ape, rana e cervello umano nel confronto storico con le reti artificiali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Limite del confronto:** quei numeri e la collocazione fra animali appartengono al quadro storico della slide. Un’unità artificiale, un parametro e un neurone biologico non sono unità intercambiabili di capacità cognitiva. Il numero di componenti, da solo, non ordina in modo universale l’intelligenza dei sistemi.

### **5. Panoramica delle applicazioni e delle tecnologie**

#### **5.1. Rilevanza economica: leggere correttamente gli esempi**

La slide sui progressi dell’AI collega nuove applicazioni e ricavi. La tabella incorporata, etichettata “FY 2025”, riporta i valori seguenti; si conservano come **dati della slide**, senza convertirli in una stima aggiornata del mercato.

| Società | Ricavi, miliardi USD | Utile netto, miliardi USD | Indicazione sulla crescita attribuita all’AI |
|---|---:|---:|---|
| Nvidia | 130,5 | 60+ (stima) | Ricavi Data Center: +427% anno su anno. |
| Alphabet | 402,8 | 132,2 | Ricavi Google Cloud: +48%. |
| Microsoft | 281,7 | 101,8 | Ricavi Azure: +34%. |

Un’infografica distinta, con copyright 2024, mostra ricavi di Apple 94,9, Meta 40,59, Microsoft 65,6, Netflix 9,8, Amazon 158,9 e Google 88,3 miliardi USD. Richiama personalizzazione, pubblicità e infrastrutture; menziona oltre 2.500 app native e 1,5 milioni compatibili per Vision Pro, oltre 3,2 miliardi di utenti quotidiani delle app Meta, quasi il 70% delle Fortune 500 con Microsoft 365 Copilot, uso della Gemini API cresciuto 14 volte in sei mesi, AI Overview in oltre 100 nuovi paesi con oltre un miliardo di utenti mensili, e oltre un quarto del nuovo codice Google generato con AI e poi revisionato. Riporta anche un’aspettativa su modelli Llama 4 più piccoli, l’interrogativo di Netflix sul miglioramento dei contenuti e l’investimento infrastrutturale di Amazon.

<!-- SOURCE_VISUAL id="IS01-V14" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="42" type="tabella e infografica" description="Dati FY2025 e infografica Big Tech con ricavi e indicatori di adozione, di periodi differenti" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Avvertenza di lettura:** i due riquadri appartengono a contesti temporali diversi. Il ricavo totale di un’azienda non è il ricavo della sola AI, e il ricavo non coincide con l’utile. La slide illustra la rilevanza economica, ma non fornisce un esperimento che isoli il contributo causale dell’AI alla crescita.

#### **5.2. Modelli generativi e assistenza alla programmazione**

La panoramica cita testualmente GPT-5.4 / GPT-5.4 Thinking, Claude Opus 4.6, Gemini 3.1 Pro, Muse Spark e Grok 4.1; richiama inoltre GPT-4, Gemini e LLaMA come esempi dell’evoluzione di generazione di testo, ragionamento e multimodalità. Questo è **l’elenco della fonte acquisita**, non una graduatoria né una verifica della disponibilità corrente dei prodotti. Gli impieghi considerati sono assistenza al coding, scrittura creativa e soluzione di problemi. È esplicitamente evidenziato l’interesse del corso per **l’impiego locale dei modelli**.

GitHub Copilot e Code Llama esemplificano l’assistenza a scrittura, debugging e ottimizzazione del software. Nello schema storico del PDF, codice e testo pubblici contribuiscono al modello, un servizio intermedio riceve il contesto dell’editor e restituisce suggerimenti, e l’interazione può affinare le proposte.

<!-- SOURCE_VISUAL id="IS01-V15" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="44" type="architettura e codice" description="Schema storico modello-servizio Copilot-editor e frammento ricorsivo merge_sort" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La schermata a fianco mostra questo frammento Python:

```python
def merge_sort(list):
    if len(list) <= 1:
        return list
    mid = len(list) // 2
    left = merge_sort(list[:mid])
    right = merge_sort(list[mid:])
    return merge(left, right)
```

L’input è una lista, divisa in due metà finché si raggiungono liste di al più un elemento; `merge` dovrebbe fondere i risultati ordinati. **Il frammento della slide è incompleto come programma autonomo**, perché non definisce `merge`; inoltre chiama `list` il parametro, nascondendo localmente il nome del tipo predefinito. Si mantiene il codice visibile senza presentarlo come implementazione completa o corretta mediante modifiche silenziose.

#### **5.3. Multimodalità, generazione visiva e scoperta scientifica**

Un sistema **multimodale** combina tipi diversi di informazione, come testo, immagini, audio e video. Le schermate mostrano due esempi: contare nove mele disposte in tre righe da tre; usare fotografia di una bicicletta, manuale e immagine degli attrezzi per spiegare una regolazione del sellino, distinguendo un bullone da una leva e indicando una chiave esagonale. Il contributo dell’immagine è fornire il contesto che la domanda testuale da sola non descrive.

<!-- SOURCE_VISUAL id="IS01-V16" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="45" type="esempi multimodali" description="Conteggio delle nove mele e conversazione su bicicletta, manuale e attrezzi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La slide sulla generazione di immagini e video menziona Stable Diffusion, Midjourney, DALL-E 3, Runway ML e Pika Labs. Il collage di Nicolas Cage in ruoli differenti mostra variazioni sintetiche di uno stesso soggetto. Il diagramma sopra il collage rappresenta **encoder, bottleneck e decoder**: l’immagine viene trasformata in una rappresentazione latente e poi ricostruita. Tale disegno illustra la codifica e ricostruzione; **non descrive da solo il procedimento iterativo di un diffusion model**.

<!-- SOURCE_VISUAL id="IS01-V17" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="46" type="architettura ed esempi scientifici" description="Encoder-bottleneck-decoder, variazioni di Nicolas Cage, struttura proteica e Nobel per la chimica 2024" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La stessa slide presenta **AlphaFold 3**, previsione delle strutture proteiche, ricerca farmacologica e scoperta di materiali e composti. Il riferimento ai “20 + 2” amminoacidi evidenzia come un insieme limitato di componenti consenta un’enorme varietà di proteine; non significa che ogni proteina contenga tutti i 22 tipi. La figura richiama il Nobel per la chimica 2024: David Baker per la progettazione computazionale di proteine, Demis Hassabis e John Jumper per la previsione delle strutture. Il filo comune è usare modelli computazionali per esplorare strutture e relazioni difficili da trattare soltanto con procedure manuali.

#### **5.4. Robotica, Edge AI e apprendimento nel tempo**

Nella **robotica**, percezione, controllo e decisione producono effetti fisici. La panoramica presenta robot umanoidi, fra cui Optimus, esempi Boston Dynamics e Agility Robotics, manipolatori e mezzi per magazzini. Destrezza, autonomia e movimento fra merci e persone collegano AI, automazione industriale e supply chain; l’atlante visivo degli umanoidi illustra la varietà delle piattaforme, senza fornire qui una valutazione comparativa delle prestazioni.

<!-- SOURCE_VISUAL id="IS01-V18" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="47" type="panoramica robotica" description="Manipolazione industriale, robot di magazzino, Boston Dynamics, Agility Robotics e atlante umanoidi 2024" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Edge AI / elaborazione AI al margine della rete** significa eseguire almeno parte dell’elaborazione vicino alla sorgente dei dati, per esempio sul dispositivo. Nel confronto della slide, il cloud offre calcolo e archiviazione remoti; l’edge è associato a latenza ridotta, riservatezza/sicurezza e risparmio energetico. Sono motivazioni progettuali, non garanzie automatiche: l’effetto reale dipende da modello, hardware, comunicazioni e carico di lavoro. Le slide citano acceleratori per smartphone di Apple, Google e Qualcomm.

<!-- SOURCE_VISUAL id="IS01-V19" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="48" type="confronto" description="AI cloud con calcolo e storage remoti rispetto a elaborazione edge sul dispositivo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Due ulteriori direzioni non vanno confuse:

- Nel **self-supervised learning**, il segnale di apprendimento viene costruito a partire dai dati stessi, riducendo la necessità di annotazione manuale. Il diagramma mostra un dataset senza etichette, un *pretext task* di preaddestramento e il trasferimento della conoscenza a un *target task* con dati etichettati. Le applicazioni citate sono immagini radiografiche e riconoscimento vocale con wav2vec 2.0.
- Nel **continual learning**, il problema è aggiornare l’apprendimento nel tempo, per nuovi dati o compiti. La figura contrappone “imparare una volta e distribuire una volta” a un ciclo di apprendimento e distribuzione continui; la guida autonoma è proposta come contesto applicativo.

<!-- SOURCE_VISUAL id="IS01-V20" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="50" type="workflow" description="Pretext task e trasferimento verso target task; confronto Static ML e Adaptive ML" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La prima distinzione riguarda **da dove viene la supervisione**, la seconda **quando e come si aggiorna il modello**. Come chiarimento aggiuntivo, apprendere da dati nuovi richiede anche di controllare che non si perdano capacità già acquisite; non basta riavviare l’addestramento senza misurarne gli effetti.

#### **5.5. Governance e responsabilità**

La panoramica include AI Act, uso responsabile e mitigazione dei bias. La piramide della slide distingue rischio inaccettabile, alto, limitato — con specifici obblighi di trasparenza — e minimo. La timeline riportata collega 1 agosto 2024 all’entrata in vigore, 2 febbraio 2025 ai divieti, 2 agosto 2025 agli obblighi GPAI e alle autorità nazionali, 2 agosto 2026 all’applicazione generale e 2 agosto 2027 ad alcuni sistemi ad alto rischio regolati da specifica legislazione europea.

<!-- SOURCE_VISUAL id="IS01-V21" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="49" type="piramide e cronologia" description="Livelli di rischio dell’AI Act e calendario rappresentato nel PDF" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Si conserva **il calendario rappresentato dalla fonte**, distinguendo entrata in vigore e applicazione delle disposizioni. È una panoramica introduttiva, non una checklist di conformità: la base normativa originaria è il [Regolamento UE 2024/1689](https://eur-lex.europa.eu/eli/reg/2024/1689/oj/eng), e la ricostruzione non sostituisce il controllo di disposizioni e modifiche applicabili a un caso concreto.

### **6. Usare un LLM nella progettazione di sistemi intelligenti**

#### **6.1. Collaborazione fra progettista e modello**

La risposta del docente alla possibilità di usare gli LLM è positiva, accompagnata da limiti precisi. La pipeline parte da dati puliti, prosegue con esplorazione, formulazione della domanda, costruzione del modello, interpretazione e presentazione. Sopra compare “Human Intelligence + LLM”: il modello può assistere le fasi, mentre il progettista deve controllare significato e correttezza del risultato.

<!-- SOURCE_VISUAL id="IS01-V22" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="52" type="pipeline" description="Clean data, exploration, question, build model, interpretation e presentation con Human Intelligence più LLM" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

L’assistenza comprende brainstorming e prototipazione, codice Python per problemi industriali, ambientali e logistici, analisi esplorativa, report, documentazione e spiegazione delle metodologie. Un esempio domanda se, per migliorare la spiegabilità, convenga una rete neurale o un albero decisionale. La risposta nella schermata privilegia l’albero perché rende visibili nodi decisionali, regole, importanza delle feature e percorsi traducibili in **IF–THEN**.

<!-- SOURCE_VISUAL id="IS01-V23" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="53" type="schermata di confronto" description="Decision tree e rete neurale rispetto a struttura interpretabile, feature importance e regole IF-THEN" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il valore didattico è partire da un **requisito**, la spiegabilità, e confrontare alternative. Come precisazione, l’albero deve restare di dimensioni comprensibili; un’indicazione sull’importanza delle feature non dimostra causalità e una spiegazione linguistica convincente non certifica il funzionamento interno di un modello.

#### **6.2. Esempio di codice generato: un livello convoluzionale**

La schermata chiede una chiamata Keras per immagini di $256\times256$ pixel e 25 filtri di dimensione $3\times3$. Il blocco generato e mostrato nelle slide è:

```python
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Input

# Define the model
model = Sequential([
    Input(shape=(256, 256, 3)),  # Input shape for 256x256 RGB images
    Conv2D(filters=25, kernel_size=(3,3), activation='relu')  # 25 filters of size 3x3
])

# Print model summary
model.summary()
```

<!-- SOURCE_VISUAL id="IS01-V24" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="54" type="codice in schermata" description="Prompt Keras e modello Sequential con Input 256x256x3 e Conv2D a 25 filtri 3x3" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

`Input` dichiara altezza, larghezza e tre canali RGB; questa scelta dei canali è aggiunta nella risposta generata. `Conv2D` costruisce 25 filtri e usa ReLU come attivazione. `summary()` descrive l’architettura: **non addestra il modello** e non dimostra che risolva una classificazione. Mancano dati, obiettivo, procedura di apprendimento e una testa di uscita adatta al task.

**Controllo esplicativo aggiunto:** con configurazione `channels_last`, stride 1 e padding `valid`, i default documentati di [Conv2D](https://keras.io/api/layers/convolution_layers/convolution2d/) danno una dimensione spaziale $256-3+1=254$, quindi l’output per immagine è $254\times254\times25$. Ogni filtro ha $3\cdot3\cdot3$ pesi e un bias: i parametri sono $(27+1)\cdot25=700$. È un controllo di coerenza del frammento, non un risultato sperimentale del docente.

#### **6.3. Contesto, analisi e allucinazioni**

Lo schema dell’assistenza all’analisi distingue **utente, prompt, context window, LLM e output**, con una freccia di ritorno che richiama l’iterazione. La finestra di contesto è l’informazione fornita al modello nell’interazione; non va confusa con tutto ciò che esiste in un archivio esterno. Per analizzare un dataset occorre che i dati necessari siano effettivamente accessibili e che i calcoli dichiarati siano eseguiti e verificati.

<!-- SOURCE_VISUAL id="IS01-V25" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="55" type="schema" description="Utente e prompt alimentano la context window, seguita da LLM, output e ciclo di feedback" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le **hallucinations / allucinazioni** sono output coerenti nella forma e grammaticalmente plausibili che contengono informazioni false o prive di senso. La figura propone una domanda con premessa infondata, la capitale di Marte, e una risposta inventata, “Elonville”; la schermata superiore mostra una trascrizione con una lunga sequenza numerica anomala. Sono esempi di output da verificare, non informazioni sul mondo.

<!-- SOURCE_VISUAL id="IS01-V26" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="56" type="esempi di errore" description="Trascrizione numerica anomala e schema della falsa capitale di Marte" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ Correttezza linguistica, quantità di dettagli e tono sicuro non sono prove di correttezza fattuale. Anche una risposta utile può contenere un singolo elemento inventato che rende inutilizzabile il risultato.

#### **6.4. Privacy, bias e dipendenza dai dati di training**

La slide richiama il rischio di esporre dati riservati usando servizi LLM pubblici, con possibili incompatibilità con NDA e GDPR. Il diagramma interpone un **privacy layer** fra il prompt con dati personali (*PII, Personally Identifiable Information*) e il servizio: il flusso di andata rimuove i dati identificativi e quello di ritorno è controllato prima dell’output all’utente. Il riquadro contempla anche informazioni sensibili nella risposta; il controllo non riguarda quindi solo il prompt.

<!-- SOURCE_VISUAL id="IS01-V27" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="57" type="architettura" description="Privacy layer fra prompt con PII e strumenti LLM, controllo degli input e degli output" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Questa è una misura architetturale illustrativa, **non una garanzia automatica di anonimato o conformità**. Il tema si collega alla scelta fra elaborazione locale e remota: occorre sapere dove passano i dati e quali componenti possono trattarli.

Gli LLM possono inoltre riprodurre **bias presenti nei dati di addestramento**. Una risposta può essere poco adatta a un dominio industriale specialistico o basata su informazioni datate. La conoscenza generale del modello non sostituisce la documentazione del dominio né la verifica sui dati effettivi.

#### **6.5. L’esperimento biografico delle slide**

Il docente mostra un piccolo esperimento con una versione indicata come “ChatGPT 3”. Alla domanda su Fabio Scotti, il sistema costruisce una biografia di matematico di geometria algebrica alla Milano-Bicocca, con dettagli su ricerca e carriera. La slide indica esplicitamente che **questa descrizione è errata**.

<!-- SOURCE_VISUAL id="IS01-V28" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="59" type="schermata annotata" description="Biografia inventata di Fabio Scotti, segnalata dal docente come errata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Aggiungendo il contesto “professore all’Università di Milano”, il modello cambia risposta ma inventa una cattedra di statistica e attribuisce il libro *Statistical Models and Methods for Financial Markets*. La copertina mostrata accanto riporta invece Tze Leung Lai e Haipeng Xing. Il contesto restringe l’interpretazione della domanda, ma non rende automaticamente veri i dettagli prodotti.

<!-- SOURCE_VISUAL id="IS01-V29" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="60" type="confronto documentale" description="Risposta con contesto universitario e copertina che smentisce l’attribuzione del libro" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Nella schermata sui propri limiti, il modello dichiara di non provare emozioni, non avere opinioni o bias personali, non svolgere azioni fisiche, non cogliere l’umorismo come una persona, e pone limiti a consulenze, attività illegali e accesso a informazioni private. Il docente evidenzia soprattutto le difficoltà con **sfumature e contesto**. Queste frasi sono **autodescrizioni del modello nella schermata**, non dimostrazioni delle sue proprietà: in particolare, “non avere bias personali” non implica che gli output siano privi di bias statistici o sociali.

<!-- SOURCE_VISUAL id="IS01-V30" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="61" type="schermata e annotazioni" description="Autodescrizione dei limiti del modello e richiamo a input, contesto e Gestalt" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Con la versione indicata come ChatGPT 4, la risposta mostrata è più pertinente all’informatica, esplicita un limite temporale della conoscenza e invita a consultare fonti ufficiali. La slide affianca anche una risposta successiva diversa: **gli output cambiano nel tempo**. Il miglioramento consiste anche nel comunicare meglio i limiti; non elimina la necessità di test continui e confronto con lo stato dell’arte.

<!-- SOURCE_VISUAL id="IS01-V31" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="62" type="confronto di risposte" description="ChatGPT 4 dichiara cutoff e limiti; una risposta successiva mostra variazione temporale" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il collegamento alla Lezione 02 riguarda **input space, prompting e contesto/Gestalt**: per capire un modello bisogna capire quali informazioni riceve e quali distinzioni riesce a rappresentare. Questa connessione riguarda sia gli input d’uso sia quelli impiegati per addestrare e valutare.

### **7. Percezione e Natural Interaction: capacità e limiti**

#### **7.1. Una classificazione sicura può essere sbagliata**

Le slide su AlexNet mostrano immagini artificiali che una rete addestrata su ImageNet — indicato nel PDF come dataset di 1,3 milioni di immagini — associa a oggetti riconoscibili: per esempio pattern etichettati come tastiera, pallone, fragola o scuolabus. Un’altra griglia contiene immagini simili a rumore, classificate come pettirosso, ghepardo, armadillo, panda minore, centopiedi, pavone, jackfruit e bolla.

<!-- SOURCE_VISUAL id="IS01-V32" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="40" type="griglia sperimentale" description="Pattern artificiali associati da AlexNet a classi di oggetti pur non riconoscibili come tali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS01-V33" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="41" type="griglia sperimentale" description="Immagini simili a rumore con etichette robin, cheetah, armadillo, lesser panda, centipede, peacock, jackfruit e bubble" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il punto è la differenza fra caratteristiche che attivano un classificatore e ciò che un essere umano riconosce come un oggetto. La fonte scientifica dell’esempio, [Nguyen e colleghi, Deep Neural Networks are Easily Fooled](https://arxiv.org/abs/1412.1897), studia proprio predizioni ad alta confidenza su immagini irriconoscibili. Non si tratta necessariamente di fotografie naturali alterate impercettibilmente: le slide mostrano anche immagini sintetiche prive di un oggetto evidente.

Una vignetta successiva etichetta come “Snail” un gatto dentro una scatola; a fianco compare una persona con passeggino sulle strisce pedonali. Il passaggio dall’umorismo alla preoccupazione serve a ricordare che **la conseguenza dell’errore dipende dal task**: sbagliare il riconoscimento in un veicolo autonomo ha implicazioni diverse da una didascalia buffa.

<!-- SOURCE_VISUAL id="IS01-V34" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="63" type="confronto di scenari" description="Gatto nella scatola etichettato Snail e attraversamento pedonale con passeggino" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.2. Che cosa significa interagire naturalmente**

La **Natural Interaction / interazione naturale** mira a rendere l’interazione uomo–macchina più vicina alle modalità umane: linguaggio, gesti, segnali emotivi e contesto. La fotografia del bambino con un robot sociale rappresenta questa promessa. Riconoscere correttamente un segnale è però diverso dal comprenderne tutte le implicazioni in una situazione reale.

<!-- SOURCE_VISUAL id="IS01-V35" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="65" type="fotografia applicativa" description="Bambino che interagisce con robot sociale nella presentazione della Natural Interaction" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le modalità considerate sono:

- **Voce e NLP:** comandi vocali in applicazioni sanitarie, smart home e navigazione dei veicoli. Il riconoscimento trasforma il parlato in una rappresentazione; l’elaborazione linguistica interpreta la richiesta.
- **Gesti:** controllo di robot industriali, formazione in realtà aumentata e traduzione della lingua dei segni. Il sistema deve distinguere movimenti significativi da quelli incidentali.
- **Interazione aptica:** contatto, forza e feedback tattile in simulazioni chirurgiche, dispositivi indossabili e diagnostica industriale.
- **Riconoscimento visivo ed emotivo:** analisi del sentiment dei clienti, coinvolgimento degli studenti e monitoraggio in contesti di salute mentale, presentati come possibili applicazioni. L’inferenza di uno stato interno da segnali esterni resta un’interpretazione, non una lettura diretta della mente.

<!-- SOURCE_VISUAL id="IS01-V36" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="66" type="esempi multimodali" description="Comandi vocali, gesti e segni, simulazione aptica e rilevamento di punti del volto" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Il **context-aware sensing** interpreta le misure considerando la situazione; i **sistemi di feedback adattivo** modificano la risposta in base a ciò che osservano. Gli esempi sono manutenzione predittiva in fabbrica, piattaforme di apprendimento adattive e traffico delle smart city.

Il diagramma multimodale collega immagini, audio e video a encoder specifici, poi a proiezioni di ingresso che allineano le rappresentazioni al modello linguistico centrale. Dal modello partono proiezioni d’uscita e componenti di generazione per immagini, audio e video; il testo ha il proprio percorso. Le etichette inferiori distinguono codifica multimodale, allineamento al LLM, elaborazione semantica, allineamento alle istruzioni e generazione dell’output. È **un’architettura esemplificativa**, non la struttura obbligatoria di ogni modello multimodale.

<!-- SOURCE_VISUAL id="IS01-V37" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="67" type="architettura multimodale" description="Encoder e proiezioni di ingresso, LLM centrale, proiezioni di uscita e generatori per immagini-audio-video; esempi contestuali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **7.3. Perché una bassa percentuale d’errore può non bastare**

La slide sul riconoscimento vocale confronta un tasso inferiore al 5% con valori superiori al 10% di anni precedenti, e attribuisce ad Andrew Ng l’indicazione di scendere sotto l’1% nelle applicazioni reali per un’interazione naturale. Si tratta di **valori e opinione riportati nel materiale storico**, non di misure universali o soglie certificate per ogni sistema attuale.

L’esempio dice che su 800 parole un errore del 5% corrisponde a:

$$
800\cdot0{,}05=40.
$$

Precisazione: 40 è il valore **al 5%**, mentre un tasso strettamente inferiore produrrebbe meno di 40 errori medi nell’approssimazione dell’esempio. Più del conteggio conta la funzione delle parole: sbagliare una negazione, un numero d’ordine o una destinazione può invalidare l’intero compito.

> 📌 La qualità di un’interazione va valutata sul successo del task e sulle conseguenze degli errori, non soltanto sulla percentuale media di parole riconosciute.

#### **7.4. Voice picking e cobot**

Nel **voice picking** logistico, l’operatore riceve istruzioni vocali per il prelievo e interagisce durante l’esecuzione. Le fotografie mostrano operatori con cuffie che movimentano merci: la voce permette di consultare le istruzioni mantenendo le mani disponibili. Le slide associano la soluzione a produttività, accuratezza e sicurezza; il precedente esempio chiarisce perché un codice o una quantità riconosciuti male possano compromettere il risultato.

<!-- SOURCE_VISUAL id="IS01-V38" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="69" type="fotografie applicative" description="Operatori di magazzino con cuffie durante il prelievo guidato dalla voce" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

L’interazione non si limita alla voce. I **cobot**, espansi nella slide come *Cooperative Robots*, cooperano con le persone in attività fisiche. La fotografia mostra il rapporto fra mano umana, manipolatore e interfaccia tablet usata per impartire istruzioni. L’aspetto intelligente riguarda quindi anche la possibilità di comunicare intenzioni e coordinare attività.

<!-- SOURCE_VISUAL id="IS01-V39" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="70" type="esempio di interazione" description="Manipolatore cooperativo con guida umana e insegnamento mediante tablet" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **8. Bias: quando si apprende la relazione sbagliata**

#### **8.1. Bias umani e rappresentazione dei dati**

L’analogia introduttiva è quella del bambino che migliora nel tempo ma il cui apprendimento dipende da chi insegna. Un modello può acquisire non soltanto regolarità utili, ma anche distorsioni della fonte dei dati. Le slide definiscono i **bias cognitivi** come deviazioni sistematiche nel giudizio rispetto a una norma o alla razionalità.

L’esempio attribuito a Daphne Koller riguarda una ricerca di immagini per “CEO” che restituisce prevalentemente uomini bianchi, con una “CEO Barbie” come rara rappresentazione femminile. Il docente riporta il conteggio illustrativo di 50 immagini maschili e una Barbie. Non è una misurazione attuale del motore di ricerca: il caso mostra come una collezione di dati o risultati possa riprodurre stereotipi e squilibri.

<!-- SOURCE_VISUAL id="IS01-V40" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="72" type="risultati di ricerca" description="Composizione delle immagini CEO usata per illustrare bias di rappresentazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Non bisogna attribuire al modello un’intenzione personale per riconoscere il problema. È sufficiente che l’apprendimento o il recupero di contenuti conservi una rappresentazione distorta, che si rifletterà nei risultati.

#### **8.2. Il caso delle radiografie provenienti da ospedali diversi**

Un secondo esempio mostra un bias meno evidente. Si vuole prevedere la presenza di fratture usando radiografie raccolte da più ospedali. Gli apparecchi producono immagini con caratteristiche differenti e gli ospedali non hanno la stessa percentuale di fratture: un grande reparto traumatologico può trattarne molte più di altre strutture.

Il modello può quindi imparare questa scorciatoia:

1. riconoscere nelle immagini le caratteristiche dell’apparecchio o dell’ospedale;
2. associarle alla frequenza di fratture osservata in quella provenienza;
3. ottenere buone predizioni nel dataset disponibile **senza analizzare realmente l’osso**.

<!-- SOURCE_VISUAL id="IS01-V41" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="73" type="esempio radiografico" description="Radiografia associata al caso del modello che riconosce l’ospedale anziché la frattura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

La correlazione funziona nei dati raccolti, ma può fallire cambiando ospedale, apparecchio o composizione dei pazienti. Il problema è che si è appresa una proprietà della raccolta dei dati al posto della relazione desiderata. Come conseguenza metodologica aggiunta, valutare il sistema su provenienze diverse aiuta a capire se la prestazione dipenda da tale scorciatoia.

> ⚠️ Un’alta accuratezza sul dataset disponibile non dimostra che il modello utilizzi le caratteristiche giuste o che generalizzi al contesto di impiego.

### **9. Il Data–Knowledge Spectrum**

#### **9.1. Rapporto fra dati e conoscenza del processo**

Il **Data–Knowledge Spectrum** mette in relazione la quantità di dati richiesta e la conoscenza disponibile sul fenomeno. Non è la piramide “dati, informazione, conoscenza, saggezza”: la figura del corso considera **quanta struttura del processo è già nota al progettista** e quanta debba essere ricavata dai dati.

L’asse verticale è **DATA**, quello orizzontale **KNOWLEDGE**. La curva scende andando verso destra. Si incontrano quattro situazioni: assenza di conoscenza, comprensione parziale — esempio: visual object detection —, modello matematico dei dati — crescita logistica — e leggi della natura — seconda legge di Newton. Le etichette Deep NN e Trad. NN richiamano posizioni indicative sullo spettro. Il disegno è attribuito a *Machine Learning Refined* di Watt, Borhani e Katsaggelos; la slide lo presenta anche come strumento concettuale per valutare la forza delle feature progettate. L’inciso etimologico su *speciō*, “guardare”, accompagna il termine *spectrum* senza introdurre una nozione tecnica ulteriore.

<!-- SOURCE_VISUAL id="IS01-V42" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="75" type="grafico concettuale" description="Curva DATA-KNOWLEDGE con assenza di conoscenza, comprensione parziale, modello matematico e leggi naturali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Quando una legge o una struttura affidabile è già nota, i dati possono servire soprattutto a stimarne pochi parametri. Quando manca questa conoscenza, il modello deve ricavare anche una rappresentazione e relazioni più complesse, e tipicamente richiede più dati. **Il grafico è qualitativo**: non fornisce una formula per calcolare il numero di esempi necessari e non afferma che più dati compensino qualsiasi difetto di qualità.

#### **9.2. Primo esempio: crescita di una popolazione e fitting**

La slide affianca crescita senza limiti e crescita limitata dalla competizione. Nel primo grafico la popolazione aumenta rapidamente; nel secondo rallenta fino a una capacità $K$, indicata graficamente a 2.000 nell’esempio teorico. Il grafico sperimentale a fianco rappresenta una popolazione di **Paramecium** per millilitro nel tempo, in giorni, con punti e una curva a S che tende a stabilizzarsi. La sequenza illustrata dei giorni 0–6 rende visibile l’aumento degli organismi.

<!-- SOURCE_VISUAL id="IS01-V43" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="76" type="grafici ed esempio biologico" description="Crescita illimitata, crescita logistica con K, sequenza giorni 0-6 e fitting di Paramecium nel tempo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Qui si dispone già di un **modello specifico e semplice**. Fare *fitting* significa adattarne i parametri ai dati osservati. Non occorre imporre una rete enorme per riscoprire da zero una struttura già disponibile: aumentare indiscriminatamente i gradi di libertà complica il problema senza un beneficio giustificato. La slide non scrive un’equazione della crescita logistica e non fornisce i dati numerici dei punti; non si ricostruisce un dataset immaginario dal grafico.

#### **9.3. Secondo esempio: visual object detection**

Nel rilevamento visivo degli oggetti, gli ingressi sono immagini e il task comprende l’identificazione e la localizzazione degli oggetti. La figura mostra riquadri attorno a notebook, laptop, occhiali, telefono e tazza di caffè, accanto a una collezione di immagini etichettate.

<!-- SOURCE_VISUAL id="IS01-V44" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="77" type="task e dataset" description="Oggetti localizzati tramite bounding box e raccolta di immagini con etichette" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Non si dispone di una piccola formula esplicita che trasformi ogni possibile immagine negli oggetti presenti. Occorre apprendere relazioni più generali, ma le **label / etichette** forniscono almeno il risultato desiderato per gli esempi. La slide colloca perciò il caso nella comprensione parziale dei dati e lo associa a modelli ampi e a un maggiore fabbisogno di esempi.

La differenza dal fitting precedente è soprattutto nella **conoscenza iniziale**. Non significa che ogni problema visivo richieda necessariamente il modello più grande disponibile: la scelta concreta dipende dai dati, dal compito e dalle risorse.

#### **9.4. Terzo esempio: attacchi informatici senza etichette**

Nel caso di sicurezza si dispone di milioni di log, **senza etichette**, e si cerca di capire quali comportamenti o feature identifichino attacchi nell’applicazione considerata. Mancano sia un modello specifico già noto sia la risposta corretta associata a ogni record.

<!-- SOURCE_VISUAL id="IS01-V45" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="78" type="task e dati" description="Domanda sui comportamenti di attacco e molti log senza etichette, sul lato povero di conoscenza dello spettro" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Quando la slide dice che non si può creare un classificatore di attacchi, il significato da preservare è: **non si può addestrare direttamente un classificatore supervisionato con quelle sole etichette, perché non ci sono**. Si possono invece cercare strutture o anomalie con metodi non supervisionati, che poi richiedono interpretazione. Un’anomalia non è automaticamente un attacco: può essere un comportamento raro ma legittimo.

La raccomandazione della slide di usare modelli generali capaci di lavorare senza label descrive l’esigenza funzionale; l’assenza di etichette non impone da sola una rete neurale grande. Il confronto fra i tre casi mette in ordine le domande progettuali: **che cosa sappiamo del processo, quali dati abbiamo, quali risultati conosciamo già e quale parte dobbiamo apprendere?**

### **10. Caso integrato: consegna mediante veicoli autonomi**

#### **10.1. Un sistema composto da sottosistemi**

Il caso conclusivo riguarda la consegna di cibo con un robot autonomo. La fotografia annotata presenta **Ottobot di Ottonomy**, descritto nella slide come capace di muoversi in luoghi affollati senza GPS. Attorno al veicolo sono collocate funzioni distinte: NLP e interazione umana, elaborazione delle immagini per la visione, elaborazione sensoriale, autocalibrazione e diagnosi, AI locale, gestione della folla, navigazione e ricostruzione della mappa.

<!-- SOURCE_VISUAL id="IS01-V46" source="Lesson_01_Introduction_Exam_Natural_Interaction_Data_Knowledge_tradeoff.pdf" page="80" type="architettura applicativa annotata" description="Ottobot con navigazione, mappa, visione, sensori, autodiagnosi, edge, folla e interazione umana" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Per portare a termine una consegna non basta classificare una fotografia. Il robot deve stimare dove si trova, riconoscere ciò che lo circonda, scegliere un percorso, controllare i movimenti, interagire con la persona e rilevare problemi interni. Alcune elaborazioni devono avvenire localmente: affidare ogni decisione a una comunicazione remota introduce una dipendenza incompatibile con determinate esigenze di reazione.

#### **10.2. Tecnologie e modelli associati alle funzioni**

La slide finale sulle tecnologie è una **mappa di possibili componenti per sistemi autonomi**, non una distinta dei componenti realmente installati su Ottobot. Include anche tecniche classiche di controllo e stima, non tutte riconducibili al Machine Learning.

| Funzione | Tecnologie elencate nelle slide | Modelli/metodi elencati nelle slide |
|---|---|---|
| Navigazione e localizzazione | SLAM, GNSS (GPS, RTK), lidar, localizzazione mediante camere | Deep Reinforcement Learning per pianificazione; filtri di Kalman per fusione sensoriale. |
| Attuazione e controllo | Motori brushless, attuatori idraulici, controllori PID/MPC | Controllo adattivo; Reinforcement Learning per stabilità dinamica. |
| Pianificazione del percorso | Algoritmi di ricerca come A*, mappe 3D, reti predittive | CNN + LSTM per anticipare gli ostacoli. |
| Interazione uomo–macchina | NLP, riconoscimento di volti e gesti | Transformer, con ChatGPT e BERT citati; sentiment analysis basata sulla visione. |
| Diagnostica e manutenzione predittiva | Fusione di IMU, vibrazioni e temperatura; edge computing | Modelli predittivi per serie temporali, RNN e LSTM. |
| Sensing e percezione ambientale | Lidar, radar, camere stereoscopiche, sensori a ultrasuoni | Object detection con YOLO e Faster R-CNN; PointNet per dati lidar. |
| Edge AI ed elaborazione in tempo reale | FPGA, TPU, Nvidia Jetson per inferenza locale | Reti quantizzate per ridurre il costo energetico del calcolo. |

Per leggere la tabella: **SLAM** significa *Simultaneous Localization and Mapping*, cioè localizzazione e costruzione della mappa contemporanee; **GNSS** indica sistemi satellitari di navigazione, con GPS e tecniche RTK (*Real-Time Kinematic*) citati come riferimenti. **IMU** è un’unità di misura inerziale. **PID** indica un controllo proporzionale–integrale–derivativo e **MPC** un *Model Predictive Control*. FPGA e TPU sono piattaforme hardware, mentre YOLO, Faster R-CNN e PointNet sono modelli/famiglie di elaborazione. **Quantizzare** significa usare rappresentazioni numeriche a precisione ridotta, con effetti da valutare su costo e accuratezza.

La presenza di GPS nella tabella generale non contraddice la descrizione del caso senza GPS: una elenca alternative tecnologiche per sistemi autonomi, l’altra descrive la capacità dichiarata per il caso illustrato. Analogamente, citare un metodo non significa che sia obbligatorio per quella funzione.

#### **10.3. Collegamenti da conservare nello studio**

I punti conclusivi del docente collegano teoria dei modelli e dei metodi, conoscenza di pregi e limiti, esperienza applicativa, stato dell’arte, gestione di dati e modelli e strumenti adeguati. I progressi dell’AI sono accompagnati da problemi di **bias, interazione naturale e spiegabilità**. Il Data–Knowledge Spectrum spiega perché, al diminuire della conoscenza disponibile sul processo, cresca in genere il lavoro che deve essere svolto a partire dai dati.

> ✅ Progettare un sistema intelligente significa scegliere e collegare dati, conoscenza, modelli, strumenti e vincoli. Un risultato impressionante su un esempio non elimina la verifica sul task reale; un modello più complesso non sostituisce la comprensione del problema.

La Lezione 01 non ha dataset, notebook o script separati: i due frammenti di codice sono nelle schermate del PDF. I riferimenti applicativi conservati nelle slide includono il sito di sistemi voice picking e un video di Ottobot; sono **rimandi esterni**, non allegati locali letti o video trascritti. L’introduzione originale e la risorsa RAG restano accessibili dall’[indice del corso](../L0%20-%20Indice%20del%20corso.md). Il seguito cronologico è la Lezione 02, su regolamentazione, input space, perturbazioni, spiegabilità e Gestalt.
