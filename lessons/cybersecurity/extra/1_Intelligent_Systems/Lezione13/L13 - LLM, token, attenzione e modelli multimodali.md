## **Lezione 13: LLM, token, attenzione e modelli multimodali**

I modelli linguistici di grandi dimensioni (LLM) estendono il repertorio del machine learning quando i dati e le attività coinvolgono linguaggio, documenti, codice e interazione in più passaggi. Per capire come funzionano occorre seguire la trasformazione del testo in token e vettori, quindi vedere come le reti elaborano sequenze e costruiscono il contesto tramite attenzione. Da qui si arriva ai Transformer, ai modelli multimodali e ai sistemi agentici. Nelle applicazioni industriali la previsione numerica, il rilevamento di anomalie e la manutenzione predittiva continuano spesso ad affidarsi a modelli classici; gli LLM li affiancano nei compiti linguistici e nei flussi che richiedono interazione e uso di strumenti.



### **0. Dal linguaggio ai sistemi agentici**

Il filo conduttore parte dai compiti di elaborazione del linguaggio e dai token, passa per le rappresentazioni vettoriali e l’attenzione, poi amplia il campo a immagini, audio e altri dati. Questa progressione chiarisce il ruolo di un LLM e prepara a distinguere il modello dal sistema agentico che lo circonda.

---

### **1. LLM e agenti nel flusso di lavoro del machine learning**

#### **1.1. Obiettivo concettuale**

Per usare questi strumenti con giudizio è sufficiente costruire un modello mentale dei loro componenti e dei loro limiti: un LLM elabora rappresentazioni numeriche e genera sequenze probabili; un agente aggiunge contesto, strumenti, controlli e un ciclo di azioni. Questa base permette di sceglierli quando il compito li richiede e di mantenere metodi predittivi validati dove servono stime numeriche affidabili.

#### **1.2. ML classico o LLM?**

Molte applicazioni industriali restano affidate al **machine learning classico**: previsione, **rilevamento di anomalie**, **manutenzione predittiva**, stima della qualità, supporto all’ottimizzazione.

> 📌 **Scelta in base al compito**.
> - usare il **ML classico** per la **previsione numerica**, i **punteggi di anomalia** e i **modelli predittivi**;
> - usare **LLM o agenti** quando nel flusso di lavoro entrano **documenti, log, codice** o **interazione a più passi**.
>
> Gli LLM diventano utili quando nel lavoro entrano **linguaggio, documenti, codice** e **interazione articolata**. In molti sistemi il ML classico resta il **nucleo predittivo**.

> 📌 **Natural Language Processing**. L’**elaborazione del linguaggio naturale** (NLP) è un campo dell’AI e dell’informatica che studia metodi per permettere ai computer di **elaborare, analizzare, capire e generare** il linguaggio umano. Permette ai sistemi intelligenti di **estrarre informazioni**, **classificare** contenuti, **rispondere a domande**, **riassumere** documenti e **generare testo**.

#### **1.3. Il flusso di lavoro resta la spina dorsale**

<!-- SOURCE_VISUAL id="IS13-V21" source="L13-web.pdf" page="4" type="schema" description="Contesto dei modelli: l’interazione avviene con pesi e probabilità" reason="Promemoria concettuale rappresentato solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Il flusso di lavoro del machine learning**. La sequenza ricorrente è:
> $$\text{problema} \to \text{dati} \to \text{rappresentazione} \to \text{modello} \to \text{valutazione} \to \text{rilascio} \to \text{monitoraggio}$$
> LLM e agenti possono affiancare le fasi di questo processo, ad esempio preparando dati, ricostruendo contesto e documentando decisioni.

Possono aiutare in:

- **ricostruzione del contesto** del progetto;
- **preparazione dei dati** e analisi dei **dati mancanti**;
- **progettazione degli esperimenti** e **reportistica**;
- **monitoraggio**, **audit** e **documentazione**.

Un agente può ricostruire il contesto di progetto raccogliendo vincoli, stato degli esperimenti e decisioni già prese.

<!-- SOURCE_VISUAL id="IS13-V01" source="L13-web.pdf" page="12" type="diagramma" description="Uso degli agenti per migliorare il contesto di progetto" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Compiti di NLP e loro metriche**

Con input testuale si distinguono **tre famiglie di compiti**:

| Famiglia | Uscita | Esempio |
|---|---|---|
| **classificazione** | **un’etichetta** per tutto l’input | analisi del sentiment |
| **etichettatura a livello di token** | **etichette per parti** dell’input | riconoscimento delle entità |
| **generazione** | **nuovo testo** | traduzione, riassunto |

Gli LLM moderni si usano soprattutto come modelli **generativi**, ma molte applicazioni industriali hanno ancora bisogno di **classificazione** ed **estrazione a livello di token**.

<!-- SOURCE_VISUAL id="IS13-V02" source="L13-web.pdf" page="14" type="schema" description="Tre famiglie di compiti sul testo: classificazione, etichettatura dei token, generazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| Compito | Descrizione | Metriche |
|---|---|---|
| **estrazione del sentiment** | classificare il tono di un testo | **accuratezza** (frazione di predizioni corrette), **precisione** (frazione di positivi predetti che sono veri positivi), **recall** (frazione di veri positivi recuperati), **F1** (media armonica di precisione e recall) |
| **riconoscimento delle entità** (NER, ) | identificare e classificare le **entità** citate nel testo: nomi, organizzazioni, luoghi, date, codici, quantità | accuratezza, precisione, recall, F1, calcolate a livello di **token** o di **intervallo di entità**, di solito **per tipo** di entità |
| **traduzione automatica** | produrre un testo in un’altra lingua | **BLEU (*Bilingual Evaluation Understudy*, valutazione bilingue automatica)**: sovrapposizione di **n-grammi** con traduzioni di riferimento; **ROUGE (*Recall-Oriented Understudy for Gisting Evaluation*, valutazione orientata al richiamo per la sintesi)**: sovrapposizione con riassunti o testi di riferimento; **perplessità**: quanto una sequenza è **inattesa** per il modello |

> 📌 **n-gramma**. Una sequenza **contigua** di $n$ parole, caratteri o token estratta da un testo. Per esempio, i bigrammi di «il gatto dorme» sono «il gatto» e «gatto dorme».

> 💡 **Perplessità e BLEU.** Per una sequenza di $N$ token, la perplessità è l’esponenziale della log-verosimiglianza media cambiata di segno:
> $$\text{PPL} = \exp\!\left( -\frac{1}{N} \sum_{i=1}^{N} \log p(t_i \mid t_1, \dots, t_{i-1}) \right)$$
> Vale 1 se il modello prevede ogni token con certezza; più è bassa, meglio il modello «si aspetta» il testo. BLEU combina le **precisioni** $p_n$ degli n-grammi (di solito fino a $n = 4$) con una **penalità di brevità** $BP$ per le traduzioni troppo corte:
> $$\text{BLEU} = BP \cdot \exp\!\left( \sum_{n=1}^{4} \tfrac{1}{4} \log p_n \right)$$

<!-- SOURCE_VISUAL id="IS13-V03" source="L13-web.pdf" page="16" type="esempio" description="Riconoscimento delle entità in un testo con i tipi evidenziati" reason="Esempio visivo delle entità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Token ed embedding**

#### **3.1. I token e la tokenizzazione**

**Il token è l’unità di base** dell’elaborazione di un LLM. Un LLM **non elabora il testo come parole o frasi**, ma come **token**. Un token può essere:

- una **parola intera**;
- **parte** di una parola;
- un segno di **punteggiatura**;
- un **frammento di numero**;
- un frammento di un **identificatore tecnico**.

> 📌 **Token**. Un’unità **indivisibile** di testo, come una parola, una sotto-parola o un carattere, che fa parte di un **vocabolario predefinito**. Token speciali:
> - **[UNK]**: rappresenta porzioni di testo **sconosciute** o mai viste;
> - **[PAD]**: riempie le posizioni vuote perché le sequenze abbiano **la stessa lunghezza**.

**Il tokenizzatore**. Un tokenizzatore $T$ divide il testo in token a un livello di granularità arbitrario. Esempio:

«this teddy bear is reaaaally cute» → `this` · `teddy` · `bear` · `is` · `[UNK]` · `cute` · `[PAD]` · … · `[PAD]`

La parola deformata «reaaaally» non è nel vocabolario e diventa `[UNK]`; i `[PAD]` finali allungano la sequenza fino alla lunghezza fissata.

Questo esempio vale per un tokenizzatore con vocabolario finito che non dispone di una scomposizione alternativa. I tokenizzatori byte-level BPE usati da molte famiglie GPT partono invece dai 256 possibili byte UTF-8 e possono rappresentare qualsiasi sequenza di testo scomponendola in unità note: in quel caso `[UNK]` non è necessario. `[PAD]` resta un simbolo di riempimento usato quando un modello richiede sequenze di lunghezza uniforme.

**Tre livelli di tokenizzazione**:

| Tipo | Pro | Contro | Esempio |
|---|---|---|---|
| **parola** | facile da interpretare; **sequenze corte** | **vocabolario grande**; le varianti delle parole non sono gestite | `teddy` ; `bear` |
| **sotto-parola** | sfrutta le **radici** delle parole; embedding intuitivi | sequenze **più lunghe**; tokenizzazione più complessa | `ted` ; `##y` ; `bear` |
| **carattere** | **nessuna parola fuori vocabolario**; vocabolario piccolo | sequenze **molto più lunghe**; pattern difficili da interpretare perché troppo di basso livello | `t` ; `e` ; `d` ; `d` ; `y` ; `b` ; `e` ; `a` ; `r` |

> 💡 **La tokenizzazione a sotto-parole** combina un vocabolario gestibile con la capacità di rappresentare parole rare tramite frammenti già presenti. La segmentazione effettiva dipende dal tokenizzatore: una parola insolita può diventare più token e allungare la sequenza.

<!-- SOURCE_VISUAL id="IS13-V04" source="L13-web.pdf" page="23" type="schema" description="Modi diversi di creare i token" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Dal testo agli embedding**

**Testo → ID dei token → embedding**. Il **tokenizzatore** converte il testo in token e **identificativi numerici** (ID); lo **strato di embedding** associa a ogni ID un **vettore denso**.

<!-- SOURCE_VISUAL id="IS13-V05" source="L13-web.pdf" page="26" type="diagramma" description="Dal testo ai token, agli ID e agli embedding" reason="Passaggi visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Codifica one-hot**. Ogni token è un vettore **indipendente**, con un solo 1 in corrispondenza della sua posizione nel vocabolario. Token diversi sono **ortogonali**, quindi la **similarità semantica non viene catturata**: se la similarità è il prodotto scalare,

$$\langle \text{teddy bear}, \text{book} \rangle = 0 \qquad \langle \text{teddy bear}, \text{soft} \rangle = 0$$

cioè per la codifica one-hot un orsacchiotto è «morbido» quanto un libro. Ecco perché servono gli **embedding**: collocano **vicini** nello spazio vettoriale i token correlati.

**Codifica appresa**. Gli **embedding appresi** rappresentano i token come **vettori densi** e possono codificare la **similarità semantica**:

$$\langle \text{teddy bear}, \text{soft} \rangle \approx 1 \qquad \langle \text{teddy bear}, \text{book} \rangle \approx 0$$

«Teddy bear» è **più vicino** a «soft» che a «book» nello spazio appreso.

> 📌 **Embedding**. Una **rappresentazione numerica** di un elemento (un token, una frase), caratterizzata da un vettore $\mathbf{x} \in \mathbb{R}^n$.

> 📌 **Similarità coseno**. Tra gli embedding $\mathbf{t}_1$ e $\mathbf{t}_2$ di due token:
> $$\operatorname{similarity}(t_1, t_2) = \frac{\mathbf{t}_1 \cdot \mathbf{t}_2}{\lVert \mathbf{t}_1 \rVert \, \lVert \mathbf{t}_2 \rVert} = \cos\theta$$
> L’**angolo** $\theta$ caratterizza la similarità **direzionale** tra i due token. A differenza della distanza euclidea, la similarità coseno **ignora il modulo** dei vettori e confronta solo la loro **orientazione** 

<!-- SOURCE_VISUAL id="IS13-V06" source="L13-web.pdf" page="30" type="schema" description="Similarità coseno tra due token come angolo tra i loro embedding" reason="Formula e angolo resi graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. L’economia dei token**

<!-- SOURCE_VISUAL id="IS13-V22" source="L13-web.pdf" page="33" type="tabella" description="Confronto dei costi per milione di token tra modelli e stima per richiesta" reason="Prezzi e costi derivati visualizzati in tabella" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **Token come unità di calcolo e di costo**. Anche gli LLM più avanzati elaborano il testo come token, e i token servono anche a stimare **carico di lavoro**, **utilizzo**, **latenza** e **fatturazione**. I token sono **insieme un’unità computazionale e un’unità economica**: per questo le scelte di progetto su **prompt** e **contesto** hanno **conseguenze economiche dirette**.



**Aggiornamento dei prezzi dei modelli.** La tabella della lezione è uno snapshot del 18 ottobre 2026, dichiarato non d’esame. Il listino API consultato il 20 novembre 2026 riporta per GPT‑6.2 Astra 8 USD input e 40 USD output per milione di token, e per GPT‑6.2 Sol 2 USD e 10 USD: la tariffa di Astra è scesa rispetto ai 10/50 USD riportati nella tabella di ottobre. I prezzi dipendono da modello, modalità e fascia di contesto e vanno ricontrollati prima di una stima economica. [Listino API OpenAI](https://developers.openai.com/api/docs/pricing), [annuncio GPT‑6.2 Astra e Sol](https://openai.com/index/introducing-gpt-6-2-astra-sol/).

> 💡 **Un conto rapido (nota didattica).** Se un servizio costa $c$ per milione di token e un’applicazione invia ogni giorno $R$ richieste da $k$ token (prompt e contesto più risposta), il costo giornaliero è $R \cdot k \cdot c / 10^6$. Allegare a ogni richiesta un documento di 20 000 token invece di un estratto da 2 000 moltiplica per circa dieci il costo, e allunga anche la latenza.

#### **3.4. Word2Vec**



**Word2Vec** associa le parole di un vocabolario a **vettori numerici densi**, appresi dalla **co-occorrenza** delle parole in grandi raccolte di testi. Parole che compaiono in **contesti simili** ottengono **vettori simili**: una parola → [Word2Vec] → un vettore.

**Addestramento**: Word2Vec impara i vettori osservando **quali parole compaiono vicine** tra loro nei testi.

**Architettura**:

1. la rete è addestrata a **predire parole dal loro contesto**;
2. la parola in ingresso è rappresentata come vettore **sparso**, per esempio one-hot;
3. dentro la rete questa rappresentazione viene **compressa** in una rappresentazione **densa e più piccola**: lo **spazio latente**;
4. dopo l’addestramento, le coordinate nello spazio latente **sono** l’embedding della parola, per esempio «A» → [0,2; 0,9], «cute» → [0,8; 0,4].

> 💡 **L’idea centrale.** «Una parola si conosce dalla compagnia che frequenta.» Il compito di predizione è solo un pretesto: ciò che interessa alla fine non è la rete, ma i **pesi dello strato nascosto**, che diventano la tabella degli embedding. Il principio è quello dell’estrazione neurale delle feature: l’addestramento orienta la rappresentazione interna verso le regolarità utili al compito.

<!-- SOURCE_VISUAL id="IS13-V07" source="L13-web.pdf" page="35" type="schema" description="Addestramento di Word2Vec sulle parole vicine nel testo" reason="Finestre di contesto visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V08" source="L13-web.pdf" page="36" type="diagramma" description="Architettura di Word2Vec: input one-hot, spazio latente denso, output" reason="Struttura della rete visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Sequenze, reti ricorrenti e attenzione**



#### **4.1. Le reti ricorrenti**

**La sequenza conta**: parole in una frase, letture di sensori nel tempo, eventi di una macchina in un log, azioni di un utente in un processo. «La macchina si è fermata dopo l’allarme» è diverso da «l’allarme si è fermato dopo la macchina». Una rete **feed-forward** standard **non ricorda** che cosa è venuto prima.

> 📌 **Reti neurali ricorrenti** (RNN), precursori delle architetture moderne per il linguaggio. Elaborano le sequenze **un passo alla volta**, mantenendo uno **stato interno** che porta con sé l’informazione degli input precedenti. Esempio: la frase «The movie was great» è la sequenza $x^{\langle 1 \rangle}$ = «The», $x^{\langle 2 \rangle}$ = «movie», $x^{\langle 3 \rangle}$ = «was», $x^{\langle 4 \rangle}$ = «great», con lunghezza $T_x = 4$.

> 💡 **L’aggiornamento dello stato.** A ogni passo $t$ lo stato nascosto combina l’input corrente con lo stato precedente:
> $$h^{\langle t \rangle} = f\big( W_h\, h^{\langle t-1 \rangle} + W_x\, x^{\langle t \rangle} + b \big)$$
> Gli stessi pesi $W_h$, $W_x$ si usano a ogni passo. Tutta la storia deve «passare» attraverso $h$, un vettore di dimensione fissa: per frasi lunghe l’informazione iniziale tende a sbiadire.

<!-- SOURCE_VISUAL id="IS13-V09" source="L13-web.pdf" page="41" type="diagramma" description="RNN che elabora The movie was great un token alla volta" reason="Catena degli stati visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. L’attenzione**

**Serve l’attenzione: che cosa guardare nei token di input?** Nei compiti di **traduzione** c’era un problema reale con le **dipendenze a lungo termine**: i convertitori di testo classici come le RNN *seq2seq* possono non «ricordare» che cosa diceva la frase di ingresso.

> 📌 **Self-attention**. Introdotta nell’articolo del **2017** «**Attention Is All You Need**», con risultati allo stato dell’arte nella traduzione automatica. La self-attention permette a **ogni token di guardare gli altri token** della stessa sequenza e crea il **contesto**.
>
> Esempio: per costruire la rappresentazione di «teddy bear» il modello può prestare attenzione ai token vicini come «cute», «is», «reading». La rappresentazione finale di «teddy bear» **dipende quindi dal contesto**, non è isolata. Il numero di token che il modello può considerare insieme è la **dimensione della finestra di contesto**.

<!-- SOURCE_VISUAL id="IS13-V10" source="L13-web.pdf" page="44" type="diagramma" description="Self-attention: il token teddy bear collegato ai token del contesto" reason="Collegamenti di attenzione visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’attenzione è quadratica**. La self-attention standard crea una **matrice di attenzione** $n \times n$ per una sequenza di $n$ token. Ogni elemento misura **quanto un token presta attenzione a un altro** nella finestra di contesto. Tutti i token possono interagire, ma il **costo cresce come** $O(n^2)$.

Esempio con 5 token, «A cute teddy bear reads» (la riga indica chi guarda, la colonna chi viene guardato):

| | A | cute | teddy | bear | reads |
|---|---|---|---|---|---|
| **A** | 0,10 | 0,20 | 0,30 | 0,20 | 0,20 |
| **cute** | 0,05 | 0,10 | 0,45 | 0,30 | 0,10 |
| **teddy** | 0,05 | 0,20 | 0,25 | 0,40 | 0,10 |
| **bear** | 0,05 | 0,10 | 0,50 | 0,25 | 0,10 |
| **reads** | 0,10 | 0,15 | 0,35 | 0,30 | 0,10 |

Le matrici di attenzione **in generale non sono simmetriche**.

> 💡 **Leggere la matrice (nota didattica).** Ogni **riga somma a 1** (per esempio $0{,}05 + 0{,}10 + 0{,}45 + 0{,}30 + 0{,}10 = 1$), perché è una distribuzione di probabilità prodotta da una softmax. «cute» guarda soprattutto «teddy» (0,45), mentre «teddy» guarda soprattutto «bear» (0,40): l’attenzione di $i$ verso $j$ non coincide con quella di $j$ verso $i$. Raddoppiare la lunghezza del contesto **quadruplica** il numero di elementi della matrice, e quindi memoria e calcolo.

**La codifica posizionale**. L’attenzione da sola **non conosce l’ordine** della sequenza: «the machine stopped» e «stopped the machine» contengono le stesse parole, ma il significato cambia con l’ordine. La **codifica posizionale** aggiunge all’embedding l’informazione sulla **posizione** del token:

$$\text{rappresentazione del token} = \text{embedding del token} + \text{codifica posizionale}$$

Così il Transformer elabora sia **che cosa** è il token sia **dove** si trova nella sequenza.

#### **4.3. Query, Key, Value**

**Query, Key e Value**. Per calcolare l’attenzione, la rappresentazione di ogni token viene **proiettata** in tre spazi vettoriali appresi:

| Vettore | Ruolo |
|---|---|
| **Query** $Q$ | «che cosa sto cercando» |
| **Key** $K$ | «che cosa offro»: con la Query determina **quanto** i token si prestano attenzione |
| **Value** $V$ | l’**informazione** che viene combinata nella rappresentazione di uscita |

> 📌 **Softmax dell’attenzione** (formula standard; contenuto di approfondimento). L’uscita dell’attenzione si ottiene calcolando con una **softmax** una distribuzione di probabilità sui token, e usandola per una **somma pesata dei vettori Value**:
> $$\operatorname{Attention}(Q, K, V) = \operatorname{softmax}\!\left( \frac{Q K^{\top}}{\sqrt{d_k}} \right) V$$
> dove $d_k$ è la **dimensione di ciascun vettore Key**. La matrice $\operatorname{softmax}(QK^\top/\sqrt{d_k})$ è proprio la matrice di attenzione $n \times n$ della .

> 💡 **Perché dividere per $\sqrt{d_k}$ (nota didattica).** I prodotti scalari tra vettori di dimensione grande tendono ad avere valori grandi; una softmax con input molto grandi diventa quasi un «tutto o niente» e i gradienti si annullano  Il fattore di scala tiene i valori in un intervallo in cui la softmax resta morbida.

<!-- SOURCE_VISUAL id="IS13-V11" source="L13-web.pdf" page="49" type="diagramma" description="Proiezioni Query, Key e Value delle rappresentazioni dei token" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V12" source="L13-web.pdf" page="51" type="formula" description="Softmax dell’attenzione con la dimensione dei vettori Key" reason="Formula resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Encoder-decoder, Transformer e GPT**



> 📌 **Encoder-decoder**. Un modello encoder-decoder trasforma una **sequenza di ingresso** in una **sequenza di uscita**: prima **codifica** l’input in una **rappresentazione numerica compatta**, poi la **decodifica** nell’uscita desiderata. Si usa **anche per le immagini**.

Il Transformer organizza questi passaggi in un’architettura di elaborazione basata su attenzione e strati feed-forward.

<!-- SOURCE_VISUAL id="IS13-V13" source="L13-web.pdf" page="53" type="diagramma" description="Architettura encoder-decoder, anche per immagini" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V14" source="L13-web.pdf" page="54" type="diagramma" description="Architettura del Transformer" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **GPT**. **Generative Pre-trained Transformer**: un modello linguistico basato sul Transformer, **pre-addestrato** su grandi corpora di testo e usato per **generare testo un token alla volta**. Esempio di traduzione con il Transformer originale: «I like science» → «Ich mag Wissenschaft», con probabilità 0,97.

> 💡 **Famiglie di architetture.** BERT è un esempio encoder-only, adatto a produrre rappresentazioni bidirezionali; GPT usa il decoder per generare token in sequenza; il Transformer originale combina encoder e decoder per trasformare una sequenza in un’altra.

<!-- SOURCE_VISUAL id="IS13-V15" source="L13-web.pdf" page="55" type="diagramma" description="Transformer originale e GPT con la traduzione I like science in Ich mag Wissenschaft" reason="Confronto delle architetture visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. LLM moderni e modelli multimodali**



#### **6.1. Dai Transformer ai LLM moderni**

Il nome GPT indica un Transformer **generativo e pre-addestrato**. Sistemi come **ChatGPT**, **Gemini**, **Claude**, **Muse** e gli **assistenti di programmazione** comprendono modelli e prodotti con capacità diverse. Come fotografia dei rilasci al **20 novembre 2026**, OpenAI ha annunciato GPT‑6.2 Astra/Sol il 17 novembre, Anthropic Claude Fable 5.2 e Mythos 5.2 il 9 novembre, Google Gemini 4 Neon il 18 novembre e Meta Muse Wonder il 12 ottobre. Mythos è accessibile tramite programmi di verifica; nomi, disponibilità e capacità non sono intercambiabili fra prodotti dello stesso fornitore. I benchmark pubblicati dai produttori non costituiscono da soli un confronto indipendente. [OpenAI](https://openai.com/index/introducing-gpt-6-2-astra-sol/), [Anthropic](https://www.anthropic.com/claude-fable-and-mythos-5-2), [Google](https://blog.google/innovation-and-ai/models-and-research/gemini-models/introducing-gemini-4-neon/), [Meta](https://ai.meta.com/research/).

> 📌 **Large Language Model**. Un LLM è un **grande modello neurale del linguaggio**, addestrato su **enormi quantità di testo** per **elaborare, generare, riassumere, tradurre, classificare** e **ragionare** sul linguaggio. **Punto chiave**: input e output di base sono ancora **sequenze di token**.

#### **6.2. Modelli multimodali**

<!-- SOURCE_VISUAL id="IS13-V23" source="L13-web.pdf" page="60" type="schema" description="Conversione di testo e immagini in token e patch visive" reason="Rappresentazione multimodale illustrata con schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Dagli LLM ai modelli multimodali**. I modelli multimodali moderni elaborano **modalità di input diverse**: testo, immagini, audio, video, codice, documenti, e anche dati strutturati simili a quelli dei sensori, **se rappresentati in modo adeguato**.

> ⚠️ **Rappresentazione numerica delle modalità**. Un modello multimodale **non vede** un’immagine come un essere umano: l’immagine viene convertita in **rappresentazioni numeriche** che possono interagire con quelle del testo. Il **testo** diventa **token**; le **immagini** diventano **patch visive** o **embedding visivi**.

> 💡 **Le patch visive.** Nei Vision Transformer un’immagine $224 \times 224$ viene divisa in patch $16 \times 16$: $(224/16)^2 = 14^2 = 196$ patch. Ogni patch diventa un vettore, cioè un «token visivo», e da lì l’attenzione funziona come per il testo. Anche un’immagine consuma quindi token, e quindi costo (§3.3).

**Modalità di input e output** :

| Da → a | Esempio |
|---|---|
| testo → testo | risposta di un chatbot, riassunto, report |
| immagine → testo | *visual question answering*, **descrizione di un difetto** |
| testo → immagine | diagramma, immagine sintetica, concetto visivo |
| audio → testo | **trascrizione** del parlato |
| testo + immagine → dati strutturati | **rapporto di ispezione**, record JSON, tabella |
| testo + strumenti → azione | **aprire un ticket**, eseguire uno script, interrogare un database |

#### **6.3. Mixture of experts e modelli guidati da prompt**

Un’altra architettura riduce il calcolo attivo selezionando solo una parte dei parametri a ogni passo: la **mixture of experts**.

> 💡 **Mixture of experts.** Invece di un unico blocco feed-forward, uno strato contiene $E$ **esperti** (sottoreti) e un **router** che, per ogni token, sceglie solo i $k$ esperti più adatti (per esempio 2 su 8) e ne combina le uscite con pesi $g_e(x)$:
> $$y = \sum_{e \in \text{top-}k(x)} g_e(x)\, \text{Expert}_e(x)$$
> Il modello ha **molti parametri in totale**, ma ne **attiva solo una parte** per token: capacità alta con costo di calcolo contenuto. Questa architettura aumenta la capacità complessiva limitando il calcolo attivo per ogni token.

<!-- SOURCE_VISUAL id="IS13-V16" source="L13-web.pdf" page="67" type="diagramma" description="Architettura mixture of experts con router ed esperti" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V17" source="L13-web.pdf" page="68" type="diagramma" description="Mixture of experts: selezione degli esperti per token" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

I modelli **promptable**, come LLaVA, possono svolgere compiti multimodali diversi in base alle istruzioni contenute nel prompt.

<!-- SOURCE_VISUAL id="IS13-V18" source="L13-web.pdf" page="62" type="immagini" description="Modelli guidati da prompt" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V19" source="L13-web.pdf" page="63" type="immagini" description="Esempi dei modelli LLaVA guidati da prompt" reason="Esempi visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Impiego in sistemi critici**. Nelle applicazioni **critiche** o **in tempo reale**, i modelli guidati da prompt spesso **non sono adatti come motore decisionale principale**. In questi casi è di solito preferibile usare **modelli di ML addestrati e validati**, con **prestazioni misurate chiaramente**, **input e output stabili** e **vincoli di rilascio deterministici**.

> 💡 **Valutazione prima dell’impiego.** Lo stesso prompt può produrre risposte diverse, una piccola modifica del testo può cambiare l’esito, e le prestazioni su un compito specifico raramente sono misurate come quelle di un modello validato su un test set. È il ragionamento dell’AI Act sui sistemi ad alto rischio 

<!-- SOURCE_VISUAL id="IS13-V20" source="L13-web.pdf" page="64" type="immagine" description="Immagine di chiusura della parte sui modelli multimodali" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Verso gli agenti**

<!-- SOURCE_VISUAL id="IS13-V24" source="L13-web.pdf" page="58" type="diagramma" description="Dal Transformer al sistema agentico" reason="Passaggio architetturale illustrato graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->



**Agente**: un sistema che integra un modello linguistico in un flusso di lavoro dotato di contesto, memoria, strumenti e verifiche.

> 📌 **Agente**. Un agente **non è solo un modello linguistico**: è un LLM inserito in un **ciclo di lavoro** con **contesto**, **memoria**, **strumenti**, **controlli** ed eventualmente l’**approvazione umana**.

---

### **8. Distinzioni da non confondere**

| Coppia | Differenza chiave |
|---|---|
| **ML classico** / **LLM e agenti** | nucleo predittivo su dati numerici / supporto quando entrano documenti, log, codice e interazione |
| **Classificazione** / **etichettatura dei token** / **generazione** | un’etichetta per tutto il testo / etichette per parti / testo nuovo |
| **BLEU** / **ROUGE** / **perplessità** | n-grammi rispetto a traduzioni di riferimento / rispetto a riassunti di riferimento / quanto il testo è inatteso per il modello |
| **Token parola** / **sotto-parola** / **carattere** | sequenze corte, vocabolario grande / compromesso / nessun fuori vocabolario, sequenze lunghissime |
| **[UNK]** / **[PAD]** | testo sconosciuto / riempimento per lunghezza fissa |
| **One-hot** / **embedding appreso** | vettori ortogonali, nessuna similarità / vettori densi con similarità semantica |
| **Similarità coseno** / **distanza euclidea** | solo l’orientazione / anche il modulo |
| **RNN** / **self-attention** | stato che passa passo per passo / ogni token guarda direttamente tutti gli altri |
| **Attenzione** / **codifica posizionale** | che cosa guardare, costo $O(n^2)$ / informazione sull’ordine |
| **Query e Key** / **Value** | decidono quanto attendere / contenuto combinato in uscita |
| **Encoder-decoder** / **solo decoder (GPT)** | trasforma una sequenza in un’altra / genera un token alla volta |
| **Modello denso** / **mixture of experts** | tutti i parametri attivi per ogni token / solo alcuni esperti per token |
| **Modello guidato da prompt** / **modello validato** | flessibile ma non deterministico / prestazioni misurate, adatto a decisioni critiche |

---

### **9. Sintesi**

> ✅ **Punti principali**.
> - Gli LLM sono **strumenti di supporto utili**, **non sostituti** dei modelli di ML validati.
> - Il testo viene tokenizzato e convertito in **vettori di embedding** densi.
> - Gli **embedding** rappresentano la **similarità semantica** in uno spazio vettoriale numerico.
> - La **self-attention** fa interagire i token dentro la **finestra di contesto**, con costo quadratico.
> - La **codifica posizionale** aggiunge l’informazione sull’**ordine** dei token.
> - Le proiezioni **Query, Key e Value** calcolano e applicano l’attenzione.
> - I **Transformer** combinano embedding, informazione posizionale, attenzione e strati feed-forward.
> - I **modelli multimodali** convertono testo, immagini, audio e altre modalità in rappresentazioni numeriche elaborabili insieme.
> - Gli agenti integrano questi modelli con contesto, strumenti, permessi e cicli di verifica.
