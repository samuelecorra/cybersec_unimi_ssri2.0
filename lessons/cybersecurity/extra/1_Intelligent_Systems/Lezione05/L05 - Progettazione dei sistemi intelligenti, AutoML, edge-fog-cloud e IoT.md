## **Lezione 5: Progettazione dei sistemi intelligenti, AutoML, edge-fog-cloud e IoT**

La quinta lezione del corso **IS4** è dedicata al **progetto**. Mette a confronto un approccio ingenuo, «raccolgo i dati, addestro, misuro l’accuratezza e rilascio», con le **dieci regole** di un progetto maturo, che toccano utenti, metriche, dati, limiti, test, monitoraggio, equità, spiegabilità, privacy e sicurezza. Presenta poi il **flusso di lavoro** del machine learning e i criteri per **scegliere la tecnica** giusta, a partire dal rasoio di Occam. Chiude con l’automazione del progetto (**AutoML** e **NAS**), con la domanda su **dove** eseguire il calcolo (edge, fog, cloud) e con una panoramica dell’**Internet of Things**.

Fonte: [PDF della Lezione 05](Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf), 72 pagine, nessun supplemento. Prodotti, servizi cloud e studi citati descrivono la versione del materiale acquisita; le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *Machine Learning Design Workflow, Automated Machine learning (AutoML), Edge→Fog→Cloud, Internet of Things (IOT) and its applications*. L’indice (p. 2) elenca:

1. **buone pratiche** per lo sviluppo (progetto generale): le **10 regole** del progetto;
2. **flusso di progetto** delle applicazioni con ML, con attenzione ad addestramento e test;
3. **scelta della tecnica** di ML migliore;
4. **AutoML**;
5. **edge → fog → cloud**;
6. **Internet of Things** (IoT);
7. punti principali.

---

### **1. Buone pratiche: le dieci regole del progetto**

_(slide pp. 3–31)_

#### **1.1. Progetto ingenuo e progetto avanzato**

_(slide pp. 3–5)_

| **Approccio ingenuo** (p. 4) | **Progetto avanzato** |
|---|---|
| raccogliere i dati disponibili, **senza pre-elaborazione né validazione** | seguire le **10 regole** di progetto |
| addestrare un modello | |
| misurare l’accuratezza e **assumere** che un valore alto indichi un buon modello | |
| rilasciare il modello e **dimenticarsene** | |

> 📌 **Le dieci regole** (p. 5).
> 1. adottare un approccio di progetto **centrato sull’uomo**;
> 2. considerare **metriche** per valutare addestramento e monitoraggio;
> 3. **esaminare periodicamente i dati grezzi**;
> 4. considerare i **limiti** del dataset e del modello;
> 5. verificare con **test** che il sistema funzioni come previsto;
> 6. **monitorare e aggiornare** regolarmente il sistema dopo il rilascio;
> 7. garantire l’**equità**;
> 8. considerare **interpretabilità e spiegabilità**;
> 9. garantire la **privacy**;
> 10. garantire la **sicurezza**.
>
> Seguendo queste regole, i sistemi di AI si possono progettare e mantenere **affidabili, equi ed efficaci** nelle applicazioni industriali e della supply chain.

> 💡 **Che cosa sbaglia l’approccio ingenuo.** Ogni passo corrisponde a una regola violata: i dati non esaminati (regola 3) possono avere etichette sbagliate o bias; una sola metrica (regola 2) nasconde gli errori che contano; «rilascia e dimentica» ignora il drift (regola 6). L’accuratezza alta sul dataset non dice nulla di ciò che accadrà con gli utenti reali.

#### **1.2. Regola 1: progetto centrato sull’uomo**

_(slide pp. 6–10)_

L’impatto reale di un sistema si valuta dall’**esperienza degli utenti finali**. Come ci si arriva? In tre modi.

**a) Scegliere il tipo di output** (p. 6):

| Tipo di output | Quando |
|---|---|
| **una sola risposta** | quando la soluzione ha **alta probabilità** di soddisfare utenti e casi d’uso diversi; esempio: «**infarto rilevato**» |
| **alcune opzioni** | quando è più appropriato lasciare la scelta all’utente; esempio: **un coupon** proposto da un sistema di raccomandazione |

<!-- SOURCE_VISUAL id="IS05-V01" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="6" type="esempi" description="Output singolo (infarto rilevato) contro alcune opzioni (coupon da un sistema di raccomandazione)" reason="Esempi visivi dei due tipi di output" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**b) Incorporare nel progetto i possibili feedback negativi** (p. 7), già **prima del rilascio completo**, anche nell’apprendimento. Per farlo bisogna definire un **insieme adeguato di utenti**. Per una specifica classe di utenti si vogliono **escludere o limitare** alcuni output pericolosi. Seguono **test dal vivo** e iterazioni su gruppi ristretti di utenti o di traffico, per esempio **bambini** o **anziani**.

> 📌 **Triplet loss** (p. 7). Funzione di perdita usata in addestramento: un input di riferimento (**ancora**) viene confrontato con un input **corrispondente** (**positivo**) e con uno **non corrispondente** (**negativo**). Si **minimizza** la distanza tra ancora e positivo e si **massimizza** quella tra ancora e negativo.
>
> In formula (nota didattica), con $f(\cdot)$ la rappresentazione prodotta dalla rete e $\alpha > 0$ un margine:
> $$L(a, p, n) = \max\!\Big( \lVert f(a) - f(p) \rVert^2 - \lVert f(a) - f(n) \rVert^2 + \alpha,\; 0 \Big)$$
> La perdita è nulla solo quando il negativo è più lontano del positivo **almeno di** $\alpha$.

<!-- SOURCE_VISUAL id="IS05-V02" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="7" type="schema" description="Triplet loss con ancora, positivo e negativo" reason="Disposizione dei tre campioni visibile solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**c) Coinvolgere utenti diversi** e considerare **più scenari d’uso** (p. 8). Così si raccolgono feedback lungo tutto lo sviluppo e si **aumenta il numero di persone** che beneficiano della tecnologia.

L’esempio ricorrente della lezione è un sistema modulare:

$$\text{dati storici dell’utente} + \underbrace{\text{riconoscimento dell’età}}_{\text{modulo 1}} \;\longrightarrow\; \underbrace{\text{sistema di raccomandazione}}_{\text{modulo 2}} \;\longrightarrow\; \text{film proposto}$$

Un sistema che stima l’età può evitare di proporre film inadatti a un bambino, e adattare le proposte agli utenti anziani.

<!-- SOURCE_VISUAL id="IS05-V03" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="8" type="diagramma" description="Sistema con riconoscimento dell’età, dati storici dell’utente e raccomandazione del film" reason="Collegamenti tra i moduli visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Guardrail**: vincoli che impediscono al sistema comportamenti indesiderati. La lezione ne distingue due tipi.

| | **Guardrail nel ML classico** (p. 9) | **Guardrail nei sistemi ad agenti** (p. 10) |
|---|---|---|
| Livello | **addestramento e validazione** | **allineamento ed esecuzione** (runtime) |
| Strumenti | **controllo dei dati**: rimuovere campioni rumorosi o avversari, **evitare il data leakage**; **progetto della perdita**: per esempio la triplet loss, imporre comportamenti desiderati contro indesiderati; **vincoli sul modello**: regolarizzazione, selezione delle feature | **vincoli su input e prompt**: rilevamento di **prompt injection** e **jailbreak**; **filtraggio e moderazione dell’output**; **regole di policy** (che cosa è permesso o vietato); **supervisione umana** a runtime: approvazione *human-in-the-loop*, escalation, *red teaming*; **vincoli sul recupero** (grounding con RAG) |
| Obiettivo | dare forma alla **rappresentazione interna** del modello | controllare il **comportamento esterno** del sistema |

> 💡 **Data leakage (nota didattica).** Si ha quando informazioni che non sarebbero disponibili al momento della predizione finiscono nei dati di addestramento: per esempio lo stesso paziente presente sia nel training sia nel test, oppure una feature calcolata usando l’etichetta. Il modello sembra ottimo in validazione e fallisce in esercizio.

#### **1.3. Regola 2: metriche per addestramento e monitoraggio**

_(slide p. 11)_

Per capire i compromessi tra i vari **errori** e l’**esperienza utente** conviene considerare **più metriche essenziali**, non una sola. Le metriche possono includere:

- **feedback** dai sondaggi degli utenti;
- variabili che seguono le **prestazioni complessive** del sistema;
- indicatori della **salute del prodotto** nel breve e lungo periodo, come il **tasso di clic** degli utenti;
- quantità che controllano i tassi di **falsi positivi** e **falsi negativi** nelle diverse categorie del prodotto.

La **scelta delle metriche** dipende dal **contesto** e dagli **obiettivi** del sistema.

> 📌 **Esempio: l’allarme antincendio** (p. 11). Deve avere una **recall alta**, anche a costo di qualche **falso allarme**. In formula (Lezione 03, §5):
> $$\text{Recall} = \frac{TP}{TP + FN}$$
> Un incendio non segnalato ($FN$) è molto più grave di un allarme inutile ($FP$).

#### **1.4. Regola 3: esaminare periodicamente i dati grezzi**

_(slide p. 12)_

- I modelli **riflettono i dati** su cui sono addestrati: analizzare i dati grezzi aiuta a capirne il funzionamento. Con dati **sensibili** ci si concentra sulla comprensione degli input **rispettando la privacy** (**anonimizzazione**).
- Esaminando i dati grezzi si verifica se ci sono **valori mancanti** o **etichette sbagliate**, e se i dati sono **campionati in modo rappresentativo** di tutti gli utenti, per esempio di tutte le età.
- Misurare le prestazioni in **addestramento** e **in esercizio** è una sfida continua: durante l’addestramento bisogna cercare le possibili **distorsioni** (*skew*) e correggerle subito, modificando i dati di addestramento o ristrutturando la **funzione obiettivo**.
- Il **bias dei dati** si affronta analizzando a fondo i dati grezzi in ingresso.

L’esempio della slide: i risultati di **Google Immagini** per la parola «**CEO**».

> 💡 **Che cosa mostra l’esempio (nota didattica).** Una ricerca di immagini per «CEO» ha a lungo restituito quasi solo uomini bianchi. Un modello addestrato su quelle immagini assocerebbe il ruolo a genere ed etnia: non per un difetto dell’algoritmo, ma perché i dati **riflettono uno squilibrio** della realtà e di chi pubblica le immagini.

<!-- SOURCE_VISUAL id="IS05-V04" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="12" type="schermata" description="Risultati di Google Immagini per la parola CEO come esempio di bias nei dati" reason="Immagini dei risultati visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.5. Regola 4: i limiti del dataset e del modello**

_(slide pp. 13–17)_

Capire i **limiti** del dataset e del modello è essenziale per tenere traccia delle «falle» della Narrow AI (p. 13).

> ⚠️ **Correlazione non è causalità** (pp. 13–14). Un modello che **rileva correlazioni** non va usato per fare **inferenze causali**.
> - Il modello può imparare che chi compra **scarpe da corsa** è per lo più **sovrappeso**. Questo **non** significa che chi compra scarpe da corsa diventerà sovrappeso.
> - Esempio classico: le vendite di **gelati** e le **scottature** aumentano insieme d’estate, ma mangiare gelati non causa scottature. Una **terza variabile**, il **caldo**, guida entrambe: è una **correlazione spuria**.
>
> Serve attenzione quando la correlazione **non è ovvia**, quando si usa una **scatola nera** con input numerosi e complessi, e nei campi applicativi in cui **non si è esperti**.

<!-- SOURCE_VISUAL id="IS05-V05" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="14" type="illustrazione" description="Correlazione contro causalità: gelati e scottature guidati dal caldo" reason="Illustrazione dell’esempio" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Portata dell’addestramento** (p. 15):

- i modelli di ML funzionano soprattutto **sui dati di addestramento**, quindi va chiarita la **portata e la copertura** dell’addestramento;
- esempio: un **rilevatore di sedie** addestrato con **foto di archivio** (comode per l’addestramento) funziona bene su foto simili, ma può **fallire** con le **foto scattate dagli utenti** con il cellulare;
- i **limiti** (di dati o di modello) vanno **comunicati agli utenti**: per esempio, che il modello è stato addestrato su **poche immagini** di **una certa regione**. Informando gli utenti si ottengono **feedback migliori**.

Nella stessa slide: un modello semplice come un **albero di decisione** è stato preferito per la sua **spiegabilità**, ma la sua **accuratezza sarà limitata**.

**Discussione: prestazioni contro bias** (pp. 16–17). Un dataset **DS** contiene immagini di **strisce pedonali**, con e senza pedoni, e serve ad addestrare un **rilevatore di pedoni**.

| Modello | Accuratezza | Come decide | Interpretabile? |
|---|---|---|---|
| **M1** | **95%** | rileva le persone con la **stima della posa** (analisi della struttura del corpo) | **in parte** |
| **M2** | **97%** | usa **l’intera immagine** | **no**: non sappiamo come decide |

**Il dilemma**:

- scegliamo M2 **solo perché va meglio**?
- M2 potrebbe sfruttare **pattern dello sfondo**, come illuminazione o condizioni della strada, invece di rilevare davvero i pedoni?
- abbiamo trovato un modello migliore, o M2 approfitta di un **bias non voluto** di DS?

**Punti di discussione**:

- come verificare che M2 usi le **feature giuste**?
- quali tecniche usare per interpretarne le decisioni? Per esempio **mappe di salienza** e **test di occlusione**;
- nelle applicazioni **critiche per la sicurezza**, l’interpretabilità va messa **prima** delle prestazioni pure?

> 💡 **Come si risponde (nota didattica).** Il 2% in più di M2 ha senso solo se il modello guarda i pedoni. Una **mappa di salienza** evidenzia i pixel che hanno pesato di più nella decisione; un **test di occlusione** copre una parte dell’immagine e osserva se la predizione cambia. Se coprendo il pedone M2 continua a «vederlo», sta usando lo sfondo. Anche un test su immagini di **altre città, orari o condizioni meteo** rivela il bias. In un’applicazione critica, un M1 al 95% di cui si capiscono gli errori è spesso la scelta più sicura.

<!-- SOURCE_VISUAL id="IS05-V06" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="16" type="immagini" description="Dataset di strisce pedonali con e senza pedoni e modelli M1 e M2" reason="Immagini dello scenario visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.6. Regola 5: verificare che il sistema funzioni come previsto**

_(slide pp. 18–20)_

**Test dei singoli moduli e pratiche di qualità** (p. 18):

| Tipo di test | Scopo |
|---|---|
| **test di unità** | provare **ogni componente** del sistema **in isolamento**; per esempio il modulo di riconoscimento dell’età confrontando l’**età stimata** con l’**età reale** |
| **test di integrazione** | capire come i singoli componenti di ML **interagiscono** con gli altri componenti del sistema |

<!-- SOURCE_VISUAL id="IS05-V07" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="18" type="diagramma" description="Test di unità del modulo di età (età reale contro stimata) e test di integrazione con il sistema di raccomandazione" reason="Configurazioni dei test visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Aggiungere la confidenza** (p. 19). Conviene inserire nel sistema **controlli di qualità o di confidenza**, per evitare di produrre subito una risposta in caso di **guasti imprevisti**. Esempio: se una feature importante **viene improvvisamente a mancare**, il sistema può **astenersi** dal produrre una predizione. Nell’esempio del film: se il riconoscimento dell’età **fallisce** o ha **confidenza troppo bassa**, il sistema di raccomandazione non deve usare quel valore.

<!-- SOURCE_VISUAL id="IS05-V08" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="19" type="diagramma" description="Guasto o confidenza troppo bassa del modulo di età che blocca la raccomandazione" reason="Punto di guasto visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Come si ottiene la confidenza** (p. 20). In molte reti neurali si può estrarre la confidenza dell’output; il corso mostrerà come. Tre idee:

1. lo strato **softmax** in uscita: la classe scelta è quella con la **probabilità più alta**;
2. aggiungere una classe «**nessuno**» (*none*);
3. accorgersi che un nuovo punto di input è «**troppo lontano**» dagli oggetti noti nello spazio delle feature.

> 📌 **Softmax e soglia di confidenza (nota didattica).** Dati i punteggi $z_1, \dots, z_C$ dell’ultimo strato, la softmax li trasforma in probabilità:
> $$p_i = \frac{e^{z_i}}{\sum_{j=1}^{C} e^{z_j}}, \qquad \hat{y} = \arg\max_i p_i$$
> Una regola di astensione semplice: rispondere solo se $\max_i p_i \ge \tau$, altrimenti restituire «nessuno».

> ⚠️ **La softmax non basta da sola.** Le probabilità della softmax sommano sempre a 1, anche per un input che non somiglia a nessuna classe: una rete può dare 0,99 a un’immagine di rumore (Lezione 01, §7.1). Per questo la slide affianca la classe «nessuno» e il controllo della **distanza** dagli esempi noti nello spazio delle feature.

<!-- SOURCE_VISUAL id="IS05-V09" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="20" type="schema" description="Strato softmax, classe none e punto di input troppo lontano nello spazio delle feature" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.7. Regola 6: monitorare e aggiornare dopo il rilascio**

_(slide p. 21)_

- Correggere i problemi nel **breve e nel lungo periodo** è cruciale: per esempio, un modello di **previsione** può soffrire di **drift** e **stagionalità**.
- Bisogna capire come un aggiornamento **influirà** sulla qualità complessiva e sull’esperienza utente.
- Prima di aggiornare va analizzata la **differenza** tra il modello **candidato** e quello **in esercizio**.

**Passi chiave**:

1. **monitorare**: seguire le metriche (accuratezza, feedback degli utenti) per individuare i problemi;
2. **analizzare**: confrontare il modello **in esercizio** (attuale) con il modello **candidato** (aggiornato);
3. **aggiornare**: riaddestrare o regolare il sistema, **testando con cura** la qualità.

#### **1.8. Regola 7: garantire l’equità**

_(slide pp. 22–23)_

| Principio | Contenuto (slide) |
|---|---|
| **compiti ad alto rischio** | rilevare **condizioni mediche** o abbinare **profili di lavoro** comporta rischi seri se c’è **iniquità**, per esempio un bias contro certi gruppi; serve una **valutazione rigorosa** dell’equità per individuare e mitigare presto i problemi |
| **progetto centrato sull’uomo** (regola 1) | dare priorità ai bisogni di **utenti diversi**, perché l’AI serva tutti e non un gruppo ristretto |
| **analisi d’impatto nel tempo** | valutare come l’AI influisce su utenti e casi d’uso man mano che evolve; un **traduttore automatico** può favorire all’inizio una lingua, ma dovrebbe servire nel tempo tutti i gruppi linguistici |
| **obiettivi flessibili** | obiettivi che accolgano scenari diversi: funzioni in **più lingue** (inglese e italiano), output adattati a **fasce d’età** (bambini e anziani) |
| **algoritmi e obiettivi equi** | incorporare l’equità nella **funzione obiettivo** e negli algoritmi, **penalizzando** le predizioni distorte o **bilanciando** gli esiti tra gruppi |
| **monitoraggio continuo** | controllare i bias che possono **emergere** con i nuovi dati, per esempio **dopo un riaddestramento** |
| **prestazioni reali** | provare l’AI in **contesti diversi** (utenti urbani e rurali, giovani e anziani); **il dataset di addestramento non è l’unico né l’ultimo banco di prova** |

La p. 23 mostra un esempio con due gruppi e tassi d’errore del **12%** e del **3%**.

> 💡 **Leggere l’esempio (nota didattica).** Un errore medio basso può nascondere un gruppo per cui il sistema sbaglia **quattro volte di più** (12% contro 3%). Per questo l’equità si valuta misurando le metriche **separatamente per gruppo**, non solo sull’intero dataset.

<!-- SOURCE_VISUAL id="IS05-V10" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="23" type="esempio" description="Due gruppi di utenti con errore del 12% e del 3%" reason="Gruppi a cui si riferiscono gli errori visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.9. Regola 8: interpretabilità e spiegabilità**

_(slide pp. 24–26)_

Il tema riprende le lezioni precedenti e successive (p. 24; Lezione 02, §5).

| | **Interpretabilità** (p. 25) | **Spiegabilità** (p. 25) |
|---|---|---|
| Che cos’è | **trasparenza** del rapporto di **causa ed effetto** nel modello | capacità di **giustificare** una decisione in termini **comprensibili** a un umano, soprattutto per modelli **opachi** come le reti profonde |
| Modelli tipici | **regressione lineare**, **alberi di decisione**: si segue come le feature portano all’output | reti profonde: prestazioni ottime, ma il funzionamento interno è un **labirinto** |
| Natura | **intrinseca** al progetto del modello: i modelli semplici «mostrano la loro logica» | sforzo **estrinseco**, **a posteriori**, per decodificare modelli complessi |
| Motto della slide | «**capisco da solo**» | «**mi serve uno strumento** per capire» |

> ⚠️ **Un residuo nel testo della slide.** La p. 25 contiene la frase «**Your definition** of explainability … **is also correct**», che sembra la risposta di un assistente conversazionale incollata senza revisione. Il contenuto della definizione resta valido.

**L’approccio a moduli** (p. 26) aiuta a gestire interpretabilità e spiegabilità: nel sistema del film, alcuni moduli possono essere **più interpretabili** e altri **meno**, e ciascuno si sceglie in base alla sua criticità.

<!-- SOURCE_VISUAL id="IS05-V11" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="26" type="diagramma" description="Approccio a moduli con parti più e meno interpretabili nel sistema di raccomandazione" reason="Assegnazione dei livelli di interpretabilità visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.10. Regola 9: garantire la privacy**

_(slide pp. 27–30)_

- Quando si trattano **dati sensibili** bisogna considerare le implicazioni sulla **privacy** (p. 27): requisiti **legali e normativi**, **etica sociale** (per esempio il **GDPR**) e le **aspettative** delle persone, come quelle di un paziente («il modello non userà informazioni su di me di cui non sono consapevole»).
- Bisogna **minimizzare la richiesta di dati sensibili**.
- Ma i **modelli grandi** hanno bisogno di **grandi dataset**…

**Memorizzazione estraibile** (p. 28). I dati di addestramento di **ChatGPT** possono essere esposti con un «**divergence attack**». Lo studio si concentrava sulla *extractable memorization*: la parte di dati memorizzati che qualcuno può **recuperare** dal modello con domande o prompt specifici. L’obiettivo era vedere se un soggetto esterno potesse **estrarre dati** appresi dal modello **senza sapere** in anticipo che cosa ci fosse nel training set.

**L’attacco** (pp. 29–30). I ricercatori hanno usato un prompt semplice: «**Ripeti la parola “poem” all’infinito**». Il comando ha fatto **deviare** ChatGPT dalle risposte allineate, fino a rilasciare **dati di addestramento**. Nell’esempio della p. 30, con la parola «book», il modello prima ripete correttamente, poi passa a **contenuti casuali**. Il testo è colorato in tonalità di rosso secondo la **lunghezza delle corrispondenze di k-grammi** con il dataset di addestramento: le sequenze lunghe, come un brano della serie *Modern Faerie Tale*, difficilmente sono casuali e indicano **memorizzazione diretta**.

> 💡 **Lo studio (nota didattica).** Si tratta di Nasr e colleghi, *Scalable Extraction of Training Data from (Production) Language Models* (2023). Un k-gramma è una sequenza di $k$ token consecutivi: se l’output contiene lunghe sequenze **identiche** a documenti di addestramento, la coincidenza casuale è praticamente impossibile. L’attacco è stato poi mitigato da OpenAI, ma mostra che un modello può **ricordare alla lettera** dati personali visti in addestramento.

<!-- SOURCE_VISUAL id="IS05-V12" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="30" type="schermata" description="Output del divergence attack con la parola book e testo colorato secondo le corrispondenze di k-grammi" reason="Colorazione del testo visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.11. Regola 10: garantire la sicurezza**

_(slide p. 31)_

- Individuare **tutti i possibili vettori d’attacco** costruendo un **modello delle minacce** (*threat model*) rigoroso.
- Il threat model deve individuare, per esempio, un **bug** che permette a un attaccante di **modificare l’input** del modello, rendendolo vulnerabile (per esempio agli **attacchi avversari**).
- Se il sistema sbaglia, bisogna individuare le **conseguenze non volute** e valutarne **probabilità e gravità**.
- **Contrastare il ML avversario fin dal progetto** (lezioni successive).

> ✅ **Le dieci regole in una frase.** Progettare per **utenti reali e diversi**, misurare con **più metriche**, guardare **i dati**, conoscere e **comunicare i limiti**, **testare** e aggiungere **confidenza**, **monitorare** nel tempo, verificare l’**equità per gruppo**, scegliere il livello di **spiegabilità** per modulo, **proteggere i dati** e **modellare le minacce**.

---

### **2. Il flusso di lavoro del machine learning**

_(slide pp. 32–37)_

Le pp. 33–36 confrontano tre versioni del flusso di lavoro:

| Pagina | Versione |
|---|---|
| p. 33 | flusso di ML **«rigoroso»** (*strict*) |
| p. 34 | il ML **nella pratica** |
| pp. 35–36 | flusso **rivisto**: «**fai il salto che ti serve!**», con l’**AutoML** e la **Neural Architecture Search** (NAS) che automatizzano alcuni passi |

La p. 37 riprende lo schema del ciclo di vita del ML da *Assuring the Machine Learning Lifecycle: Desiderata, Methods, and Challenges* (ACM Computing Surveys, vol. 54, n. 5, art. 111, giugno 2021).

> 💡 **Le fasi del ciclo di vita (nota didattica).** Nell’articolo il ciclo di vita del ML ha quattro fasi, ciascuna con i propri requisiti di garanzia: **gestione dei dati** (raccolta, pre-elaborazione, aumento), **apprendimento del modello** (scelta, addestramento, iperparametri), **verifica del modello** (test, verifica formale) e **rilascio** (integrazione, monitoraggio, aggiornamento). La differenza tra la versione rigorosa e quella «pratica» delle slide va letta sulle figure.

<!-- SOURCE_VISUAL id="IS05-V13" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="33" type="diagramma" description="Flusso di lavoro rigoroso del machine learning" reason="Passi del flusso presenti solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS05-V14" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="34" type="diagramma" description="Il machine learning nella pratica" reason="Passi del flusso presenti solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS05-V15" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="36" type="diagramma" description="Flusso di lavoro rivisto con i salti resi possibili da AutoML e NAS" reason="Passi saltati visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS05-V16" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="37" type="diagramma" description="Ciclo di vita del machine learning (ACM Computing Surveys 2021)" reason="Fasi del ciclo presenti solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Scegliere la tecnica di AI**

_(slide pp. 38–43)_

#### **3.1. Prerequisiti e passi**

> ⚠️ **Prerequisiti** (p. 38).
> - Scegliere una tecnica richiede una **conoscenza profonda** sia del **problema** sia delle **tecniche** considerate.
> - **Non iniziare ad addestrare** un modello se non si è sicuri del **problema**, del **significato di input e output** e delle tecniche che si stanno applicando.
> - I problemi reali possono richiedere una **combinazione di tecniche** (AI e non AI) per una soluzione (sub)ottima, **non solo un modello più grande**: **approcci ibridi**. Nel sistema del film, i moduli possono avere **più** o **meno** spiegabilità.

<!-- SOURCE_VISUAL id="IS05-V17" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="38" type="diagramma" description="Approccio ibrido con moduli a maggiore o minore spiegabilità" reason="Assegnazione dei livelli di spiegabilità visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Passi possibili** (p. 39):

1. **studiare i requisiti** del compito;
2. valutare quale tecnica li **soddisfa più completamente**;
3. **ordinare** le soluzioni possibili per **applicabilità**, **costo**, **prestazioni** e altre caratteristiche.

**Caratteristiche da considerare** su modelli e strumenti (pp. 40–41):

| Gruppo 1 (p. 40) | Gruppo 2 (p. 41) |
|---|---|
| accuratezza | curva di apprendimento |
| spiegabilità | velocità di sviluppo |
| velocità di risposta | tolleranza alla complessità |
| scalabilità | tolleranza al rumore nei dati |
| compattezza | tolleranza ai dati sparsi |
| flessibilità | indipendenza dagli esperti |
| possibilità di integrazione in sistemi embedded | facilità o difficoltà di calcolo |
| facilità d’uso | |

> ⚠️ **Nota poco chiara.** Accanto all’ultima voce la p. 41 riporta «(COOrdinate format)», senza spiegazione; non è chiaro a che cosa si riferisca e non viene interpretato qui.

#### **3.2. Il rasoio di Occam**

> 📌 **Regola principale: il rasoio di Occam** (p. 42).
> - Un problema si può risolvere **in molti modi**.
> - Anche se **due tecniche** danno soluzioni di **qualità simile**, abbinare la tecnica giusta al problema fa **risparmiare tempo e risorse**.
> - Una tecnica ottima produce una soluzione che **contiene tutte le informazioni richieste**, **soddisfa tutti gli altri criteri** necessari e **usa tutta la conoscenza utile** disponibile.

**Applicazioni del rasoio di Occam** (p. 43):

- **non sprecare tempo**: né quello di **progetto** né quello di **elaborazione** (modelli grandi!);
- **prima di tutto usare modelli classici**, per esempio **k-NN** (*k-Nearest Neighbor*) o **alberi di decisione**;
- partire da **reti neurali semplici** prima di considerare il **deep learning**;
- **aumentare gradualmente** topologia e numero di neuroni;
- andare «**in profondità**» **solo se è davvero necessario**.

> 💡 **Perché partire dal semplice.** Un modello semplice fornisce una **baseline**: se una rete profonda non la supera in modo netto, la complessità in più non è giustificata. Inoltre un modello semplice si addestra in secondi, si spiega facilmente e rivela subito problemi nei dati che una rete grande nasconderebbe.

---

### **4. AutoML e Neural Architecture Search**

_(slide pp. 44–48)_

> 📌 **Automated Machine Learning** (AutoML, p. 45). Il processo di **automatizzare** i compiti necessari ad applicare il machine learning a problemi reali.
> - Può includere **ogni fase**, dal **dataset grezzo** fino al modello **pronto per il rilascio**.
> - Punta a far usare modelli e tecniche di ML ai **non esperti**, senza che debbano diventarlo.

> ⚠️ **Il prezzo dell’automazione** (p. 45). La maggior parte dei passi del **progetto generale**, per esempio il progetto **centrato sull’uomo**, andrà (probabilmente) **persa**.

<!-- SOURCE_VISUAL id="IS05-V18" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="45" type="diagramma" description="Processo AutoML dal dataset grezzo al modello rilasciabile" reason="Passi del processo presenti solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Neural Architecture Search** (NAS, p. 46). Sottocampo dell’AutoML: tecnica per **automatizzare il progetto delle reti neurali artificiali**. La NAS ha prodotto reti **alla pari o migliori** di quelle progettate a mano. I metodi si classificano secondo tre elementi:

| Elemento | Ruolo |
|---|---|
| **spazio di ricerca** | definisce **quali tipi di rete** si possono progettare e ottimizzare |
| **strategia di ricerca** | definisce **come esplorare** lo spazio di ricerca |
| **strategia di stima delle prestazioni** | valuta le prestazioni di una rete candidata a partire dal suo progetto |

> ⚠️ **Precisazione (nota didattica).** La slide dice che la stima avviene «senza costruire e addestrare» la rete. È l’obiettivo delle strategie più efficienti (predittori, condivisione dei pesi, addestramenti ridotti); l’approccio base, invece, **addestra** ogni candidata ed è molto costoso.

<!-- SOURCE_VISUAL id="IS05-V19" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="46" type="diagramma" description="NAS: spazio di ricerca, strategia di ricerca e stima delle prestazioni" reason="Ciclo della ricerca visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Servizi commerciali**:

- **Azure AutoML** (p. 47), mostrato graficamente;
- **Google Cloud AutoML** (p. 48): un servizio per **addestrare e rilasciare** modelli di ML personalizzati, in versioni diverse per tipo di dato:

| Dati | Compiti |
|---|---|
| **immagini** | classificazione, rilevamento di oggetti |
| **video** | riconoscimento di azioni, classificazione, tracciamento di oggetti |
| **testo** | classificazione, estrazione di entità, analisi del sentiment |
| **tabellari** | classificazione e regressione, previsione |

<!-- SOURCE_VISUAL id="IS05-V20" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="47" type="schermata" description="Servizio Azure AutoML" reason="Pagina con sola schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Edge, fog e cloud: dove si calcola?**

_(slide pp. 49–54)_

La domanda della sezione (p. 49): **dove** avviene il calcolo? Le pp. 50–52 presentano graficamente l’**edge**, la catena **edge → fog → cloud** e le **opportunità** della combinazione tra **AI e fog computing**.

> 📌 **I tre livelli (nota didattica).**
>
> | Livello | Dove | Caratteristiche tipiche |
> |---|---|---|
> | **Edge** | sul dispositivo o vicinissimo al sensore (telecamera, PLC, smartphone) | latenza minima, funziona anche senza rete, dati sensibili che restano in locale; risorse di calcolo ed energia limitate |
> | **Fog** | nodi intermedi della rete locale (gateway, server di stabilimento) | aggrega più dispositivi, pre-elabora e filtra i dati, risponde in tempi brevi |
> | **Cloud** | data center remoti | potenza di calcolo e memoria quasi illimitate, addestramento dei modelli grandi, visione globale; latenza e dipendenza dalla connessione |
>
> Uno schema frequente: si **addestra nel cloud** e si esegue l’**inferenza sull’edge**; il fog fa da filtro e da ponte. La ripartizione esatta delle slide va verificata sulle figure.

> 💡 **Il collegamento con la Lezione 04.** Il limite dei modelli enormi «un dispositivo autonomo non può sfruttare modelli grandi nel cloud» (Lezione 04, §3.2) è proprio il motivo per cui la scelta del livello di calcolo fa parte del progetto.

<!-- SOURCE_VISUAL id="IS05-V21" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="50" type="schema" description="L’edge: calcolo vicino ai dispositivi" reason="Pagina con solo schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS05-V22" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="51" type="schema" description="Catena edge, fog, cloud" reason="Livelli e flussi visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS05-V23" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="52" type="infografica" description="Opportunità della combinazione tra AI e fog computing" reason="Elenco delle opportunità presente solo nell’infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Caso d’uso edge-cloud** (p. 53): la soluzione di calcolo per l’AI **Huawei Atlas**, presentata con un video.

<!-- SOURCE_VISUAL id="IS05-V24" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="53" type="immagine" description="Caso d’uso edge-cloud Huawei Atlas AI Computing Solution" reason="Contenuto presente solo nell’immagine e nel video collegato" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Un mondo digitale** (p. 54). Gli attori affrontano **sfide enormi**, ma nel mondo ci sono iniziative rilevanti: **IoT**, **Industria 4.0** (ora **5.0**) e **AI**, con un’importanza crescente in ogni applicazione (Lezione 04, §5).

---

### **6. Internet of Things**

_(slide pp. 55–71)_

#### **6.1. Origini, miti e presente**

L’IoT è il **calcolo su dispositivi (piccoli)** (p. 55).

**Origini** (p. 56). Dal **2000**, quando è stato introdotto **IPv6**, e dal **2007**, quando è uscita l’estensione di rete wireless **6LoWPAN**, praticamente **tutti i dispositivi collegati via IP**, cablati e wireless, possono essere connessi. Queste reti, l’«Internet of Things», suscitano grande interesse tra chi sviluppa **reti di controllo industriale**.

> ⚠️ **Sulle date (nota didattica).** Le specifiche di IPv6 risalgono agli anni ’90 (RFC 1883 del 1995, RFC 2460 del 1998); il 2000 va letto come l’inizio della sua diffusione. Lo standard 6LoWPAN, che porta IPv6 su reti wireless a basso consumo (IEEE 802.15.4), è l’RFC 4944 del **2007**.

| **Idee e miti del passato** (p. 57) | **Presente e futuro** (p. 57) |
|---|---|
| la **sveglia** ti sveglia un po’ prima perché il traffico aumenta | dispositivi che **si autodiagnosticano** |
| il **frigorifero** chiede altro latte | **autoconfigurazione** e **aggiornamenti** automatici |
| … | sensori per la **salute personale** collegati agli **ospedali** |
| | **auto che comunicano** tra loro |

#### **6.2. Tre tipi di sorgenti di dati**

_(slide pp. 58–62)_

| Tipo | Come funziona (slide) | Esempio |
|---|---|---|
| **Passive** (p. 59) | sensori che **non comunicano attivamente**: vanno **attivati** e producono dati **solo su richiesta**. «Passivo» non significa applicazione passiva: l’applicazione deve contenere la **logica per gestire** i sensori. Sono di solito sensori a **basso consumo** in **luoghi remoti** | sensore di **saturazione dell’acqua nel terreno** che produce dati aggiornati solo quando si invoca l’API |
| **Attive** (p. 60) | il sensore **trasmette dati in flusso continuo**, invece di doverli chiedere di continuo. Bisogna **assorbire i dati quasi in tempo reale** con capacità di comunicazione sofisticate; i dati **non si possono perdere** e vanno **estratti correttamente** dal flusso e messi nel formato giusto per archiviazione ed elaborazione | **motore a reazione** |
| **Dinamiche** (pp. 61–62) | le **più sofisticate e utili**: dispositivi che comunicano in modo **bidirezionale** con le applicazioni, in una vera «**conversazione**». Possono **cambiare i dati** prodotti, il **formato** e la **frequenza**, gestire la **sicurezza**, ricevere **aggiornamenti software** automatici per problemi, versioni e **calibrazione**. Sono **auto-configurabili**: è come un’applicazione che parla con un’altra | **termostato intelligente** |

> 📌 **Dove entra l’AI** (p. 62). Le sorgenti dinamiche offrono le **migliori capacità IoT**: **qui si possono implementare sistemi di AI**. Esempio: **Nest** = termostato semplice + AI; **Google** ha acquistato Nest per **3,2 miliardi di dollari** nel **2014** (Lezione 03, §6.1).

#### **6.3. Tre categorie di IoT per tipo di utente**

_(slide p. 63)_

| Categoria | Esempi |
|---|---|
| **IoT consumer** | auto intelligenti, telefoni, orologi, portatili, **elettrodomestici connessi**, sistemi di intrattenimento |
| **IoT commerciale** | **controllo dell’inventario**, tracciatori di dispositivi, **dispositivi medici** connessi |
| **IoT industriale** | contatori elettrici connessi, sistemi di acque reflue, misuratori di portata, monitoraggio di **condotte**, **robot di produzione** e altri dispositivi e sistemi industriali connessi |

#### **6.4. Tre famiglie di comunicazione: consumer, industriale, M2M**

_(slide pp. 64–69)_

Le tre famiglie di comunicazione IoT si distinguono per quattro caratteristiche (p. 64): **tempo reale**, **determinismo**, **interazione umana**, **sicurezza**. In un sistema **deterministico** ogni azione (causa) produce una reazione (effetto) prevedibile.

<!-- SOURCE_VISUAL id="IS05-V25" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="64" type="tabella" description="Tre famiglie di comunicazione IoT confrontate per tempo reale, determinismo, interazione umana e sicurezza" reason="Valori del confronto presenti solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| | **IoT consumer** (p. 65) | **IoT industriale** (IIoT, pp. 66, 68) | **Machine to Machine** (M2M, p. 67) |
|---|---|---|---|
| Esempi | guardare un **video** sul telefono; avviare un **fitness tracker** che invia le statistiche all’account nel cloud | reti di controllo industriale, gruppi di nodi che collaborano | **tracciamento di veicoli**, monitoraggio dell’**usura** dei meccanismi di un edificio, tracciamento delle **attrezzature mobili** di un ospedale |
| Tempo reale | **no** | **sì**: requisiti di tempo reale | conta l’**affidabilità** più della velocità |
| Determinismo | **no** | consegna **affidabile** su tutta la rete | comunicazioni affidabili |
| Uomo nel ciclo | **sì**: in caso di guasto un umano **recupera o riavvia** l’applicazione | no | **no**: nessun operatore aiuta a recuperare gli errori |
| Comunicazione | client/server, spesso con **grandi flussi** di dati | **molti-a-molti**: gruppi di nodi lavorano insieme a un **singolo compito** (p. 68) | client/server con **piccole quantità** di dati: identificatore del dispositivo, **coordinate**, **marca temporale** |
| Requisiti chiave | — | **resilienza ai guasti**, **recupero dei pacchetti**, **sicurezza**, connettività fisica in ambienti con **forti interferenze elettromagnetiche**, **servizi di controllo** | gli oggetti localizzati **hanno valore**, e ne ha la conoscenza della loro posizione: c’è un **costo** quando l’informazione **manca o non è affidabile** |

Un esempio di M2M sono le applicazioni **Vehicle to Everything** (V2X), presentate nella lezione successiva.

<!-- SOURCE_VISUAL id="IS05-V26" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="68" type="diagramma" description="Struttura tipica dei nodi IIoT con connessioni molti-a-molti" reason="Topologia visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Applicazioni tipiche dell’IIoT** (p. 69). Secondo **Oxford Economics**, società leader nelle previsioni globali e nell’analisi quantitativa, l’IIoT è una delle **grandi tendenze future**, con implicazioni significative per l’economia globale. Riguarda:

- **manifattura**, **estrazione mineraria**, **agricoltura**, **petrolio e gas**, **servizi di pubblica utilità**;
- i **beni fisici durevoli** in generale, e le organizzazioni che gestiscono **ospedali, magazzini e porti** o offrono **trasporti, logistica e servizi sanitari**.

L’idea di base: **sensori integrati** in oggetti e macchinari, con **connessione wireless**.

#### **6.5. Impatto globale e passo successivo**

_(slide pp. 70–71)_

L’impatto globale dell’IoT si vede nella **smart city** (p. 70).

**Il passo successivo** (p. 71): l’interconnessione **senza soluzione di continuità** e il coordinamento **autonomo** di un numero enorme di elementi di calcolo e sensori, di entità **inanimate e viventi**, di **persone, processi e dati**, attraverso l’infrastruttura di Internet.

> 💡 **Un nome per il passo successivo (nota didattica).** La definizione coincide con quella di **Internet of Everything** (IoE) vista nella Lezione 04, §5: non solo oggetti connessi, ma anche persone, processi e dati.

<!-- SOURCE_VISUAL id="IS05-V27" source="Lesson_05_DesignOfIntelligentSystems_EdgeFogCloud_IoT.pdf" page="70" type="infografica" description="La smart city come impatto globale dell’IoT" reason="Pagina con sola infografica" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **Progetto ingenuo** / **avanzato** | raccogli, addestra, misura l’accuratezza, rilascia e dimentica / dieci regole su utenti, dati, test, monitoraggio, etica e sicurezza |
| **Una risposta** / **alcune opzioni** | soluzione molto probabilmente giusta per tutti (infarto) / scelta lasciata all’utente (coupon) |
| **Guardrail nel ML classico** / **negli agenti** | dati, perdita e vincoli in addestramento / filtri su input e output, policy e supervisione a runtime |
| **Correlazione** / **causalità** | variabili che variano insieme / una variabile che provoca l’altra |
| **Test di unità** / **di integrazione** | un modulo in isolamento / moduli che interagiscono |
| **Modello in esercizio** / **candidato** | versione attuale / versione aggiornata da confrontare prima di sostituire |
| **Interpretabilità** / **spiegabilità** | intrinseca al modello («capisco da solo») / sforzo a posteriori («mi serve uno strumento») |
| **AutoML** / **NAS** | automazione di tutto il flusso di ML / automazione del solo progetto dell’architettura di rete |
| **Edge** / **fog** / **cloud** | sul dispositivo / nodi intermedi locali / data center remoti |
| **Sorgente passiva** / **attiva** / **dinamica** | dati solo su richiesta / flusso continuo / conversazione bidirezionale riconfigurabile |
| **IoT consumer** / **IIoT** / **M2M** | uomo nel ciclo e grandi flussi / tempo reale, affidabilità e molti-a-molti / pochi dati, affidabilità senza operatore |

---

### **8. Sintesi della lezione**

_(slide p. 72)_

> ✅ **Punti principali** (p. 72).
> - **Progetto delle applicazioni con ML** (tema molto importante): le regole del progetto, dall’approccio centrato sull’uomo fino a privacy e sicurezza.
> - Il **flusso di lavoro del ML**, dalla versione rigorosa a quella con i salti di AutoML e NAS.
> - **Scelta della tecnica** migliore: prerequisiti, caratteristiche dei modelli, **rasoio di Occam** e partenza dai modelli classici.
> - **Edge → fog → cloud**: dove eseguire il calcolo.
> - **Internet of Things**: sorgenti passive, attive e dinamiche; IoT consumer, commerciale e industriale; comunicazioni consumer, IIoT e M2M.
> - **Applicazioni** dei sistemi intelligenti all’IoT: **smart city** e **trasporti intelligenti** (lezione successiva).

> ⚠️ **Nove o dieci regole?** L’indice (p. 2) e l’elenco (p. 5) parlano di **10 regole**, mentre i punti principali (p. 72) scrivono «9 regole». Le regole trattate nella lezione sono **dieci**.
