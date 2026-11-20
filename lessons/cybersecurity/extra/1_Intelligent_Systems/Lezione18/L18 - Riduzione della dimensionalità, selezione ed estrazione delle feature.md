## **Lezione 18: Riduzione della dimensionalità, selezione ed estrazione delle feature**

La riduzione della dimensionalità interviene dopo la preparazione dei dati e prima del modello. Può conservare alcune variabili originarie oppure trasformarle in una rappresentazione più compatta. La scelta dipende dallo scopo: mantenere feature interpretabili, comprimere i dati, separare sorgenti, visualizzare strutture o produrre rappresentazioni da usare in un classificatore.

### **1. Selezione delle feature**

#### **1.1. Selezione ed estrazione**

La **selezione** mantiene un sottoinsieme delle feature originarie: una variabile selezionata conserva il proprio significato fisico, come un sensore o un pixel. L’**estrazione** costruisce nuove feature combinando quelle originarie; le componenti ottenute possono mescolare informazioni provenienti da più sensori o pixel. Entrambe riducono la dimensionalità, ma solo la selezione conserva direttamente l’identità delle variabili iniziali.

Nella pipeline di preparazione, la riduzione segue omogeneizzazione, correzione dei valori non validi e mancanti, verifica della coerenza, codifica delle categorie, trattamento degli outlier, scomposizione, aggregazione, trasformazione e normalizzazione. In un caso industriale, per esempio, sei segnali rumorosi possono essere trasformati in tre descrittori più stabili, poi usati da un classificatore o da un regressore.

<!-- SOURCE_VISUAL id="IS18-26-V01" source="L18-web.pdf" page="3" type="schema" description="Estrazione di nuove feature a confronto con selezione di feature originarie" reason="Schema visivo che distingue le due operazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-26-V02" source="L18-web.pdf" page="5" type="diagramma" description="Sei segnali grezzi trasformati in tre feature per classificazione o regressione" reason="Esempio visivo del passaggio da segnali a feature" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le feature irrilevanti aggiungono rumore; quelle ridondanti ripetono informazioni già presenti. La loro rimozione può ridurre l’overfitting e il tempo di addestramento, e talvolta migliorare le prestazioni. I tre approcci principali differiscono per come scelgono le variabili:

| Approccio | Criterio e ricerca | Valutazione e limiti |
|---|---|---|
| **Filtro** | Assegna un punteggio alle feature usando statistiche dei dati, come varianza, correlazione o informazione mutua; spesso ordina le variabili. | Non addestra un modello, tende a essere robusto all’overfitting ma può ignorare feature utili solo in combinazione. |
| **Wrapper** | Prova sottoinsiemi e usa un algoritmo di apprendimento come funzione di valutazione; può usare k-NN o una rete neurale. | Il modello viene addestrato per ogni candidato, spesso con cross-validation: ricerca costosa e rischio di overfitting. |
| **Embedded** | La selezione avviene durante l’addestramento, guidata dall’algoritmo. Esempi: penalizzazione L1, importanza negli alberi. | È legata al modello, con costi e rischio di overfitting in genere inferiori ai wrapper. |

Nei metodi filtro, feature sulla stessa scala con alta varianza possono risultare più informative; una correlazione elevata fra variabili suggerisce ridondanza. Un criterio univariato, tuttavia, può perdere relazioni che emergono soltanto considerando più feature insieme. I wrapper misurano l’utilità sul compito, ma la selezione va eseguita all’interno dei fold di addestramento: scegliere variabili usando anche i dati di test rende ottimistica la valutazione. Nei metodi embedded, i pesi d’ingresso che vengono spinti verso zero possono indicare scarsa rilevanza, purché gli input siano normalizzati e si interpreti il peso nel contesto del modello.

<!-- SOURCE_VISUAL id="IS18-26-V03" source="L18-web.pdf" page="9" type="schema" description="Confronto tra metodi filtro, wrapper ed embedded" reason="Schema riassuntivo dei tre criteri di selezione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **1.2. Ricerca sequenziale: SFS, SBS e RFE**

La **Sequential Forward Selection (SFS)** è una ricerca wrapper greedy: parte dall’insieme vuoto, aggiunge a ogni passaggio la feature che produce il miglior punteggio in cross-validation e conserva il miglior sottoinsieme incontrato lungo il percorso. La scelta è irreversibile; alternative scartate non vengono riprese.

Con $d$ feature, i sottoinsiemi di cardinalità $j$ sono ${d \choose j}=\frac{d!}{j!(d-j)!}$. Il numero di sottoinsiemi non vuoti nella ricerca esaustiva è $2^d-1$. La SFS valuta $d+(d-1)+\dots+1=\frac{d(d+1)}{2}$ candidati nel percorso massimo: con 4 feature ne prova 10 su 15 possibili, con 20 ne prova 210 su 1.048.575. Il risparmio computazionale comporta il rischio di una scelta iniziale che impedisca combinazioni migliori.

La **Sequential Backward Selection (SBS)** parte da tutte le feature e rimuove a ogni passaggio quella la cui eliminazione danneggia meno il punteggio. La **Recursive Feature Elimination (RFE)** segue un principio affine: addestra ripetutamente un modello, elimina le feature meno importanti e assegna un rango a ciascuna. Una feature con rango 1 è l’ultima rimasta e, secondo il modello scelto, la più utile.

Nel laboratorio si usa una SVM lineare per classificare immagini sintetiche da $8\times8$ pixel, rappresentate da 64 valori; le etichette sono dieci cifre. La RFE calcola i ranghi dei pixel. Il confronto fra 5-NN e diverse quantità di pixel, con selezione ripetuta dentro ciascun fold, dà accuratezza media del 45,8%, 83,4%, 96,3%, 98,2% e 98,6% usando rispettivamente 2, 5, 10, 20 e 64 pixel. Dieci pixel mantengono quindi gran parte della prestazione dell’insieme completo nel dataset sintetico dell’esperimento.

<!-- SOURCE_VISUAL id="IS18-26-V04" source="L18-web.pdf" page="12" type="diagramma" description="Spazio dei sottoinsiemi esplorato dalla selezione SFS" reason="La figura mostra candidati e percorso greedy" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-26-V05" source="L18-web.pdf" page="22" type="tabella" description="Matrice dei ranghi RFE dei 64 pixel" reason="Ranghi numerici per la posizione dei pixel" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-26-V06" source="L18-web.pdf" page="24" type="grafico" description="Accuratezza 5-NN al variare del numero di pixel selezionati con RFE" reason="Confronto prestazionale riportato per il dataset sintetico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **2. Estrazione: analisi delle componenti principali**

#### **2.1. Geometria e obiettivo della PCA**

La **Principal Component Analysis (PCA)** è una trasformazione lineare non supervisionata: non usa le etichette. Centra i dati e li proietta su assi ortogonali, ciascuno combinazione lineare delle feature originarie. La prima componente, PC1, segue la direzione di massima varianza; ogni componente successiva è ortogonale alle precedenti e massimizza la varianza residua.

La proiezione su un sottospazio di dimensione inferiore massimizza la varianza conservata ed equivale a minimizzare l’errore quadratico medio di ricostruzione. Per dati centrati, la norma quadrata di un punto si scompone nella norma della sua proiezione e nell’errore ortogonale; la quantità totale è fissa, quindi massimizzare la prima riduce il secondo.

Per una matrice $X\in\mathbb{R}^{N\times d}$, con un campione per riga, si sottrae la media delle colonne e si calcola la covarianza $C=\frac{1}{N-1}X_c^\top X_c$. Gli autovettori di $C$ definiscono gli assi principali e gli autovalori ne misurano la varianza. Se $W$ contiene i primi $k$ autovettori, la rappresentazione ridotta è $Z=X_cW$; la frazione di varianza trattenuta è $\frac{\sum_{j=1}^k\lambda_j}{\sum_{j=1}^d\lambda_j}$. La divisione per $N$ invece di $N-1$ cambia la scala degli autovalori, ma non gli autovettori.

Nel caso illustrativo di tre geni e 240 cellule, la proiezione preserva la varianza complessiva: le varianze nelle coordinate centrate sono 27,9, 20,3 e 12,4; nelle componenti sono 40,0, 19,7 e 0,8. La terza componente contiene una quota piccola. In un esempio a dieci feature, PC1 e PC2 trattengono il 60%; il gomito della curva suggerisce due dimensioni per la visualizzazione, mentre una soglia del 90% richiede otto componenti. La scelta di $k$ dipende dal compromesso tra compressione, ricostruzione e prestazione sul compito.

La PCA non crea cluster e non considera i colori di un grafico: le etichette possono essere sovrapposte dopo il calcolo per aiutare la lettura. Per una pipeline predittiva si normalizzano le variabili quando le scale differiscono e si stimano scaler e PCA esclusivamente sul training set. La validazione di un classificatore su cifre sintetiche mostra accuratezza 0,98 con PCA a 10 componenti e 0,67 con 2 componenti; 20 componenti bastano a superare il 90% della varianza nell’esempio. La PCA è usata per visualizzazione, compressione, riduzione del rumore, analisi delle tendenze e pre-elaborazione.

<!-- SOURCE_VISUAL id="IS18-26-V07" source="L18-web.pdf" page="27" type="grafico" description="Proiezione PCA di tre geni su due componenti principali" reason="Nuvola tridimensionale e piano proiettato" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-26-V08" source="L18-web.pdf" page="37" type="grafico" description="Autovalori, varianza cumulativa e criterio del gomito per scegliere k" reason="La scelta illustrata dipende dalla forma della curva" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **2.2. PCA sulle immagini di cifre**

La PCA porta 1.800 immagini sintetiche da 64 pixel a due componenti, che spiegano circa il 34% della varianza (19% e 15%). I colori delle classi aiutano a osservare la proiezione, ma non entrano nell’algoritmo. L’esempio rende visibile la differenza tra usare due pixel arbitrari e costruire combinazioni lineari che raccolgono più variazione.

<!-- SOURCE_VISUAL id="IS18-26-V09" source="L18-web.pdf" page="45" type="grafico" description="Proiezione PCA delle cifre sintetiche colorata per etichetta" reason="Mappa bidimensionale delle osservazioni" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **3. LDA, t-SNE, ICA e mappe di visualizzazione**

#### **3.1. LDA**

L’**Analisi Discriminante Lineare (LDA)** usa le etichette per trovare combinazioni lineari che separino le classi. Può ridurre le dimensioni prima di un altro classificatore oppure fungere da classificatore lineare. Il numero di componenti è al massimo $\min(d,C-1)$, dove $C$ è il numero di classi: dieci cifre e 64 pixel consentono al più nove direzioni discriminanti. Il criterio di Fisher confronta la dispersione fra le classi con quella interna alle classi. Nell’esempio di quattro stati cellulari, PCA privilegia un effetto comune di dimensione cellulare; LDA utilizza le etichette e ottiene, con 5-NN, 98% contro il 79% della proiezione PCA a due dimensioni.

#### **3.2. t-SNE e metodi per visualizzare**

La **t-SNE** (*t-distributed Stochastic Neighbor Embedding*) è una mappa non lineare che mira a conservare le relazioni fra vicini. È adatta all’esplorazione in due o tre dimensioni. Le distanze fra gruppi e le loro dimensioni nel disegno non sono interpretabili come distanze globali nello spazio originale; inoltre, la procedura standard non offre una trasformazione per collocare nuovi campioni. La mappa serve a osservare i dati, non a certificare accuratezza o generalizzazione.

Su 1.797 cifre reali da $8\times8$, la t-SNE produce gruppi visivamente più separati della PCA. La divergenza di Kullback–Leibler confronta le probabilità di vicinanza nello spazio originale e nella mappa; in un’esecuzione il valore finale è 0,71. Questo numero misura la discrepanza dell’obiettivo di embedding, non la qualità di un classificatore.

Cinque tecniche applicate ai dati con scopi diversi:

| Metodo | Relazione ottimizzata | Uso tipico |
|---|---|---|
| **PCA** | Assi lineari che trattengono la varianza | Compressione e proiezione di nuovi dati |
| **t-SNE** | Probabilità dei vicinati locali | Visualizzazione esplorativa |
| **UMAP** | Grafo fuzzy dei vicini | Visualizzazione della struttura locale |
| **PaCMAP** | Coppie vicine, intermedie e lontane | Bilanciare struttura locale e più ampia |
| **TriMAP** | Vincoli relativi fra triplette | Esplorare relazioni più globali |

UMAP, PaCMAP e TriMAP, come t-SNE, sono spesso impiegate per visualizzare. Il loro risultato dipende da obiettivo, parametri e inizializzazione; assi e coordinate non sono confrontabili tra metodi. Si possono applicare a Iris (150 campioni, 4 misure), Wine (178 campioni, 13 misure) e MNIST. Nelle proiezioni non supervisionate le etichette servono a colorare i punti, non a stimare la mappa. Una maggiore separazione visiva non dimostra che un classificatore funzionerà meglio.

<!-- SOURCE_VISUAL id="IS18-26-V10" source="L18-web.pdf" page="48" type="grafico" description="Proiezione LDA guidata dalle classi e confronto con PCA" reason="Il confronto geometrico mostra direzioni di separazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-26-V11" source="L18-web.pdf" page="56" type="tabella" description="Confronto tra PCA, t-SNE, UMAP, PaCMAP e TriMAP" reason="Tabella comparativa dei meccanismi e degli usi" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. ICA e sorgenti indipendenti**

L’**Analisi delle Componenti Indipendenti** (ICA, *Independent Component Analysis*) cerca di ricostruire sorgenti statisticamente indipendenti a partire da osservazioni che ne sono miscele. Se $\mathbf{x}=A\mathbf{s}$, con $\mathbf{s}$ sorgenti e $A$ matrice di miscelazione, l’ICA stima componenti che separino le sorgenti sotto ipotesi quali indipendenza, non gaussianità e miscelazione lineare. Un’applicazione intuitiva è separare voci registrate da più microfoni; in un esempio con due voci e rumore, una componente può essere riconosciuta come rumore e scartata.

### **4. Embedding come feature apprese**

Un **embedding** è un vettore prodotto da un estrattore appreso: $z=f_\theta(x)\in\mathbb{R}^d$. Contenuti simili dovrebbero avere rappresentazioni vicine secondo una metrica scelta. Un embedding può essere visualizzato con PCA o t-SNE oppure passato a un classificatore leggero. La mappa bidimensionale è uno strumento di osservazione; le decisioni si basano sul vettore completo o su un modello validato.

La similarità coseno fra due vettori è $\cos(z_a,z_b)=\frac{z_a\cdot z_b}{\|z_a\|\|z_b\|}$; la distanza coseno è $1-\cos(z_a,z_b)$. Il confronto riguarda la direzione dei vettori. Per esempio, $(1,2)$ e $(2,1)$ hanno coseno $4/5=0{,}8$. Le singole coordinate di un embedding generalmente non hanno interpretazione semantica autonoma.

Il PDF descrive EmbeddingGemma 2 come codificatore multimodale da circa 740 milioni di parametri, con vettori da 768 dimensioni; la verifica delle modalità, dimensioni e provenienza è riportata nella figura con i metadati del progetto. Il flusso illustrato converte immagini MNIST in vettori, poi confronta cinque proiezioni 2D. Il contenuto si aggiorna rapidamente: dimensioni e modalità vanno lette come snapshot del materiale, non come specifica permanente del prodotto.

#### **4.1. Aggiornamento dei modelli decisionali (20 novembre 2026)**

La rassegna del materiale fotografa prodotti e versioni di fine ottobre 2026. Alcuni riferimenti sono già superati: OpenAI ha portato la Decisions API in disponibilità generale il 22 ottobre e indica `gpt-6-luna` come modello supportato; Perplexity ha annunciato la famiglia `pplx-decider` 1.3 il 12 novembre; Cloudflare ha aggiunto Clef-omni con input audio e video il 9 ottobre. TypeSafe ha pubblicato Jev 1.14 il 14 ottobre. Microsoft documenta Microsoft-Decision-1 nel catalogo Foundry, mentre AWS descrive Strands Decider 2B come modello sperimentale open source. I numeri di latenza, prezzo e benchmark della tabella restano quelli dichiarati nelle fonti e nelle condizioni dell’istantanea, non risultati direttamente comparabili. ([OpenAI](https://developers.openai.com/api/docs/guides/decisions), [Perplexity](https://community.perplexity.ai/c/announcements/9), [Cloudflare](https://developers.cloudflare.com/changelog/post/2026-10-09-clef-omni-workers-ai/), [TypeSafe](https://typesafe.ai/blog/jev-1-14), [Microsoft](https://learn.microsoft.com/en-us/azure/foundry/foundry-models/how-to/use-foundry-models-microsoft-decision), [AWS](https://aws.amazon.com/blogs/machine-learning/cut-generative-ai-costs-code-for-rules-models-for-judgment/))

<!-- SOURCE_VISUAL id="IS18-26-V12" source="L18-web.pdf" page="63" type="diagramma" description="Codificatore multimodale EmbeddingGemma 2 e vettore da 768 componenti" reason="Schema delle modalità di input e della rappresentazione appresa" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **5. Modelli System 1 e decisioni veloci**

#### **5.1. Rappresentazione, costo e confini del compito**

La seconda parte introduce modelli progettati per decisioni brevi, frequenti e con risposte vincolate. L’idea è investire calcolo nella progettazione e usare poi una valutazione rapida per ogni nuovo caso: un classificatore o encoder produce una rappresentazione riutilizzabile, mentre il compito operativo specifica le opzioni e la soglia d’azione.

La distinzione fra una fase di progettazione lenta e una fase d’uso rapida si applica anche a filtri, PCA, reti addestrate e ricerca wrapper. Filtri e PCA richiedono un costo di stima iniziale, poi elaborano campioni nuovi; t-SNE standard ricostruisce la mappa e non colloca direttamente nuovi punti; SFS comporta fino a $d(d+1)/2$ addestramenti per fold. La scelta va valutata su costo iniziale, costo per campione e possibilità di elaborare nuovi dati.

Gli errori di ragionamento descritti da Kahneman hanno analoghi nella progettazione: una selezione può nascondere feature scartate ma utili congiuntamente; correlazione o varianza possono sostituire impropriamente la metrica del compito; scala e inizializzazione possono ancorare la visualizzazione; una probabilità del modello può essere scambiata per certezza. Cross-validation annidata, dati tenuti da parte, confronti fra più inizializzazioni e calibrazione aiutano a controllare questi effetti.

#### **5.2. Domande tipizzate: Choice, Score e Noul**

Un modello di decisione tipizzata riceve uno stato (testo, JSON o immagine) e una o più domande strutturate. Può valutare più quesiti sul medesimo stato e restituire risposte tipizzate; il codice applicativo usa gli output per instradare o autorizzare azioni. I tre primitivi descritti sono:

| Primitivo | Output | Esempio di lettura |
|---|---|---|
| **Choice** | Distribuzione sulle opzioni finite | Selezionare la categoria con probabilità maggiore |
| **Score** | Distribuzione su livelli ordinali | Calcolare un punteggio atteso, per esempio 3,8 su 5 |
| **Noul** | Probabilità che una proposizione sì/no sia vera | Valutare se un pacco è danneggiato |

Per Choice, i punteggi delle opzioni vengono normalizzati con softmax: $p_c=\frac{e^{z_c}}{\sum_{j=1}^K e^{z_j}}$. Se $z=(2,0{,}5,-1)$, si ottiene circa $(0{,}79,0{,}17,0{,}04)$. Per Score, una distribuzione sui livelli consente di calcolare il valore atteso. Per Noul, l’uscita è $p=P(q\text{ vera}\mid x)$; la confidenza nella risposta selezionata è $\max(p,1-p)$.

Una soglia centrale può inviare i casi incerti a una persona o a un sistema più lento: per esempio si agisce sopra 0,9 o sotto 0,1, e si inoltra il resto. Le soglie sono scelte di policy, determinate dai costi di falsi positivi e falsi negativi; la probabilità deve essere calibrata sul dominio d’uso. Un output formalmente valido può comunque essere falso: validità del formato, correttezza e calibrazione sono proprietà distinte.

<!-- SOURCE_VISUAL id="IS18-26-V13" source="L18-web.pdf" page="77" type="diagramma" description="Flusso di stato, domande tipizzate, risposte e decisione nel codice" reason="Schema del flusso operativo" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-26-V14" source="L18-web.pdf" page="79" type="tabella" description="Primitivi Choice, Score e Noul con output probabilistici" reason="Esempi di output e interpretazione" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **5.3. Calibrazione, soglie e cascata**

La calibrazione chiede se, fra le previsioni con confidenza 90%, circa il 90% sia corretto. L’errore di calibrazione attesa si calcola dividendo le previsioni in $B$ intervalli e aggregando gli scarti fra accuratezza e confidenza media: $\mathrm{ECE}=\sum_{b=1}^{B}\frac{n_b}{N}|\mathrm{acc}_b-\mathrm{conf}_b|$. Su 540 cifre di test, l’esperimento riportato passa da accuratezza 98% ed ECE 0,008 in distribuzione a 40% ed ECE 0,466 dopo uno spostamento delle immagini e l’aggiunta di rumore. La confidenza può quindi restare alta quando l’input cambia distribuzione.

Una cascata usa il sistema rapido per proporre una risposta e inoltra i casi con rischio atteso elevato a un controllo più lento. Se $C_{err}$ è il costo di un errore e $C_2$ il costo del controllo, si inoltra quando $(1-p_{sel})C_{err}>C_2$. Con $C_{err}=100$ e $C_2=5$, la soglia è 0,95. Le stime di tempo e costo riportate per prodotti specifici sono snapshot vendor o benchmark; hardware, rete e prompt differiscono, quindi i valori non costituiscono confronti universali.

Le applicazioni adatte comprendono instradamento di richieste, controlli di accesso agli strumenti, ranking, ispezione visiva e classificazione di avvisi di filiera. Limiti frequenti sono l’assenza di spiegazioni, debolezza nell’aritmetica e nel conteggio, vulnerabilità agli input manipolativi e calo fuori distribuzione. Prima del rilascio occorrono esempi etichettati del contesto reale, esecuzione in modalità ombra, misure di latenza nella rete effettiva e verifica indipendente della calibrazione.

<!-- SOURCE_VISUAL id="IS18-26-V15" source="L18-web.pdf" page="84" type="grafico" description="Diagramma di affidabilità e cambiamento della calibrazione dopo data shift" reason="Curva di calibrazione e valori sperimentali" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS18-26-V16" source="L18-web.pdf" page="85" type="diagramma" description="Cascata tra decisione rapida e controllo lento con soglia di rischio" reason="Flusso di escalation basato sul costo atteso" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

### **6. Riepilogo**

La selezione mantiene feature originali: filtri classificano le variabili con statistiche, wrapper confrontano sottoinsiemi rispetto a un modello ed embedded selezionano durante l’addestramento. SFS e SBS effettuano una ricerca greedy; RFE elimina ricorsivamente le feature meno utili secondo un modello. La PCA proietta senza etichette sugli assi ortogonali che massimizzano la varianza; LDA utilizza le classi e produce al più $C-1$ dimensioni; ICA cerca sorgenti indipendenti. t-SNE, UMAP, PaCMAP e TriMAP offrono mappe esplorative con obiettivi diversi, le cui geometrie non sono direttamente confrontabili. Gli embedding appresi diventano feature per ispezione o classificazione. Nelle decisioni tipizzate, soglie, controllo a cascata e calibrazione collegano le probabilità alle conseguenze operative.

### **7. Procedura PCA**

Per $X\in\mathbb{R}^{N\times d}$, con campioni in riga: (1) sottrarre a ogni riga la media $\bar{x}$; (2) calcolare $C=\frac{1}{N-1}X_c^\top X_c$; (3) ordinare gli autovalori $\lambda_1\ge\dots\ge\lambda_d$ e i relativi autovettori; (4) formare $W=[u_1,\dots,u_k]$; (5) proiettare $Z=X_cW$. Ogni colonna di $W$ ha norma unitaria e il proprio autovalore misura la varianza della componente. PC1 massimizza la varianza fra le direzioni unitarie; PC2 massimizza quella residua restando ortogonale a PC1.

