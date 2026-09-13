## **Lezione 13: LLM, token, attenzione e modelli multimodali**

La tredicesima lezione del corso **IS4** è la prima parte di un blocco dedicato a **LLM, modelli multimodali e agenti** per il machine learning. Non è un corso completo sui Transformer: tiene solo i concetti necessari a usare gli agenti **con consapevolezza** da professionisti del ML. Il percorso va dai compiti classici di elaborazione del linguaggio ai **token**, dagli **embedding** a word2vec, dalle **reti ricorrenti** all’**attenzione** e al **Transformer**, fino ai **LLM moderni**, ai modelli **multimodali** e alla **mixture of experts**. Il messaggio di fondo: nella maggior parte dei problemi industriali il **nucleo predittivo resta il ML classico**, e LLM e agenti servono quando entrano in gioco documenti, codice e interazione.

Fonte: [PDF della Lezione 13](Lesson_13_LLM_MultimodalModels.pdf), 51 pagine, nessun supplemento. Alcune slide sono tratte o adattate dal corso **CME 295: Transformers & Large Language Models** di Afshine e Shervine Amidi (Stanford University). Le formule che nelle slide sono immagini sono riportate in forma standard; le note didattiche aggiunte sono segnalate. [Indice del corso](../L0%20-%20Indice%20del%20corso.md).

### **0. Struttura della lezione**

_(slide pp. 1–2)_

Titolo originale: *LLM, Multimodal models, Agents for ML (Part A)*. L’indice (p. 2) elenca:

1. **introduzione agli agenti** per il ML;
2. **LLM**;
3. **modelli multimodali**;
4. **agenti**;
5. esempi.

---

### **1. LLM e agenti nel corso IS4**

_(slide pp. 3–8)_

#### **1.1. Che cosa questa lezione non è**

Non è un corso completo su (p. 4):

- **addestramento** degli LLM;
- **modelli multimodali**;
- **dettagli interni** del Transformer;
- **rincorsa ai benchmark**.

Copre solo ciò che serve per **lavorare con gli agenti in modo consapevole** da professionisti del ML: la lezione è **volutamente selettiva**.

#### **1.2. ML classico o LLM?**

Molte applicazioni IS4 restano di **ML classico** (p. 5): previsione, **rilevamento di anomalie**, **manutenzione predittiva**, stima della qualità, supporto all’ottimizzazione.

> 📌 **Decisione di progetto** (p. 5).
> - usare il **ML classico** per la **previsione numerica**, i **punteggi di anomalia** e i **modelli predittivi**;
> - usare **LLM o agenti** quando nel flusso di lavoro entrano **documenti, log, codice** o **interazione a più passi**.
>
> Gli LLM diventano utili quando nel ciclo entrano **linguaggio, documenti, codice** e **ragionamento interattivo**. In molti problemi IS4 il ML classico resta il **nucleo predittivo**.

> 📌 **Natural Language Processing** (p. 6). L’**elaborazione del linguaggio naturale** (NLP) è un campo dell’AI e dell’informatica che studia metodi per permettere ai computer di **elaborare, analizzare, capire e generare** il linguaggio umano. Permette ai sistemi intelligenti di **estrarre informazioni**, **classificare** contenuti, **rispondere a domande**, **riassumere** documenti e **generare testo**.

#### **1.3. Il flusso di lavoro resta la spina dorsale**

> 📌 **Il flusso di lavoro IS4** (p. 7). La sequenza ricorrente resta:
> $$\text{problema} \to \text{dati} \to \text{rappresentazione} \to \text{modello} \to \text{valutazione} \to \text{rilascio} \to \text{monitoraggio}$$
> LLM e agenti vengono introdotti per **migliorare** questo flusso, **non per sostituirlo**.

Possono aiutare in:

- **ricostruzione del contesto** del progetto;
- **preparazione dei dati** e analisi dei **dati mancanti**;
- **progettazione degli esperimenti** e **reportistica**;
- **monitoraggio**, **audit** e **documentazione**.

La p. 8 mostra come usare gli agenti per migliorare il **contesto di progetto**.

<!-- SOURCE_VISUAL id="IS13-V01" source="Lesson_13_LLM_MultimodalModels.pdf" page="8" type="diagramma" description="Uso degli agenti per migliorare il contesto di progetto" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **2. Compiti di NLP e loro metriche**

_(slide pp. 9–13)_

**Dalle basi ai GPT** (p. 9). Con input testuale esistono **tre famiglie di compiti** (p. 10):

| Famiglia | Uscita | Esempio |
|---|---|---|
| **classificazione** | **un’etichetta** per tutto l’input | analisi del sentiment |
| **etichettatura a livello di token** | **etichette per parti** dell’input | riconoscimento delle entità |
| **generazione** | **nuovo testo** | traduzione, riassunto |

Gli LLM moderni si usano soprattutto come modelli **generativi**, ma molte applicazioni industriali hanno ancora bisogno di **classificazione** ed **estrazione a livello di token**.

<!-- SOURCE_VISUAL id="IS13-V02" source="Lesson_13_LLM_MultimodalModels.pdf" page="10" type="schema" description="Tre famiglie di compiti sul testo: classificazione, etichettatura dei token, generazione" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

| Compito | Descrizione (slide) | Metriche |
|---|---|---|
| **estrazione del sentiment** (p. 11) | classificare il tono di un testo | **accuratezza** (frazione di predizioni corrette), **precisione** (frazione di positivi predetti che sono veri positivi), **recall** (frazione di veri positivi recuperati), **F1** (media armonica di precisione e recall) |
| **riconoscimento delle entità** (NER, p. 12) | identificare e classificare le **entità** citate nel testo: nomi, organizzazioni, luoghi, date, codici, quantità | accuratezza, precisione, recall, F1, calcolate a livello di **token** o di **intervallo di entità**, di solito **per tipo** di entità |
| **traduzione automatica** (p. 13) | produrre un testo in un’altra lingua | **BLEU**: sovrapposizione di **n-grammi** con traduzioni di riferimento; **ROUGE**: sovrapposizione con riassunti o testi di riferimento; **perplessità**: quanto una sequenza è **inattesa** per il modello |

> 📌 **n-gramma** (p. 13). Una sequenza **contigua** di $n$ parole, caratteri o token estratta da un testo. Per esempio, i bigrammi di «il gatto dorme» sono «il gatto» e «gatto dorme».

> 💡 **Perplessità e BLEU in formula (nota didattica).** Per una sequenza di $N$ token, la perplessità è l’esponenziale della log-verosimiglianza media cambiata di segno:
> $$\text{PPL} = \exp\!\left( -\frac{1}{N} \sum_{i=1}^{N} \log p(t_i \mid t_1, \dots, t_{i-1}) \right)$$
> Vale 1 se il modello prevede ogni token con certezza; più è bassa, meglio il modello «si aspetta» il testo. BLEU combina le **precisioni** $p_n$ degli n-grammi (di solito fino a $n = 4$) con una **penalità di brevità** $BP$ per le traduzioni troppo corte:
> $$\text{BLEU} = BP \cdot \exp\!\left( \sum_{n=1}^{4} \tfrac{1}{4} \log p_n \right)$$

<!-- SOURCE_VISUAL id="IS13-V03" source="Lesson_13_LLM_MultimodalModels.pdf" page="12" type="esempio" description="Riconoscimento delle entità in un testo con i tipi evidenziati" reason="Esempio visivo delle entità" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **3. Token ed embedding**

_(slide pp. 14–28)_

#### **3.1. I token**

**Il token è l’unità di base** dell’elaborazione di un LLM (p. 14). Un LLM **non elabora il testo come parole o frasi**, ma come **token**. Un token può essere:

- una **parola intera**;
- **parte** di una parola;
- un segno di **punteggiatura**;
- un **frammento di numero**;
- un frammento di un **identificatore tecnico**.

> 📌 **Token** (p. 15). Un’unità **indivisibile** di testo, come una parola, una sotto-parola o un carattere, che fa parte di un **vocabolario predefinito**. Token speciali:
> - **[UNK]**: rappresenta porzioni di testo **sconosciute** o mai viste;
> - **[PAD]**: riempie le posizioni vuote perché le sequenze abbiano **la stessa lunghezza**.

**Il tokenizzatore** (pp. 16–17). Un tokenizzatore $T$ divide il testo in token a un livello di granularità arbitrario. Esempio:

«this teddy bear is reaaaally cute» → `this` · `teddy` · `bear` · `is` · `[UNK]` · `cute` · `[PAD]` · … · `[PAD]`

La parola deformata «reaaaally» non è nel vocabolario e diventa `[UNK]`; i `[PAD]` finali allungano la sequenza fino alla lunghezza fissata.

**Tre livelli di tokenizzazione** (p. 18):

| Tipo | Pro | Contro | Esempio |
|---|---|---|---|
| **parola** | facile da interpretare; **sequenze corte** | **vocabolario grande**; le varianti delle parole non sono gestite | `teddy` ; `bear` |
| **sotto-parola** | sfrutta le **radici** delle parole; embedding intuitivi | sequenze **più lunghe**; tokenizzazione più complessa | `ted` ; `##y` ; `bear` |
| **carattere** | **nessuna parola fuori vocabolario**; vocabolario piccolo | sequenze **molto più lunghe**; pattern difficili da interpretare perché troppo di basso livello | `t` ; `e` ; `d` ; `d` ; `y` ; `b` ; `e` ; `a` ; `r` |

> 💡 **Perché gli LLM usano le sotto-parole (nota didattica).** È il compromesso tra i due estremi: un vocabolario di qualche decina di migliaia di pezzi copre qualsiasi testo, anche parole nuove, spezzandole in parti note. Con le sotto-parole «reaaaally» non diventerebbe `[UNK]`, ma una sequenza di frammenti come `re` · `aaaa` · `lly`.

<!-- SOURCE_VISUAL id="IS13-V04" source="Lesson_13_LLM_MultimodalModels.pdf" page="16" type="schema" description="Modi diversi di creare i token" reason="Schema presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.2. Dal testo agli embedding**

**Testo → ID dei token → embedding** (p. 19). Il **tokenizzatore** converte il testo in token e **identificativi numerici** (ID); lo **strato di embedding** associa a ogni ID un **vettore denso**.

<!-- SOURCE_VISUAL id="IS13-V05" source="Lesson_13_LLM_MultimodalModels.pdf" page="19" type="diagramma" description="Dal testo ai token, agli ID e agli embedding" reason="Passaggi visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**Codifica banale: one-hot** (p. 20). Ogni token è un vettore **indipendente**, con un solo 1 in corrispondenza della sua posizione nel vocabolario. Token diversi sono **ortogonali**, quindi la **similarità semantica non viene catturata**: se la similarità è il prodotto scalare,

$$\langle \text{teddy bear}, \text{book} \rangle = 0 \qquad \langle \text{teddy bear}, \text{soft} \rangle = 0$$

cioè per la codifica one-hot un orsacchiotto è «morbido» quanto un libro. Ecco perché servono gli **embedding**: collocano **vicini** nello spazio vettoriale i token correlati.

**Codifica appresa** (p. 21). Gli **embedding appresi** rappresentano i token come **vettori densi** e possono codificare la **similarità semantica**:

$$\langle \text{teddy bear}, \text{soft} \rangle \approx 1 \qquad \langle \text{teddy bear}, \text{book} \rangle \approx 0$$

«Teddy bear» è **più vicino** a «soft» che a «book» nello spazio appreso.

> 📌 **Embedding** (p. 22). Una **rappresentazione numerica** di un elemento (un token, una frase), caratterizzata da un vettore $\mathbf{x} \in \mathbb{R}^n$.

> 📌 **Similarità coseno** (pp. 23–24, formula standard). Tra gli embedding $\mathbf{t}_1$ e $\mathbf{t}_2$ di due token:
> $$\operatorname{similarity}(t_1, t_2) = \frac{\mathbf{t}_1 \cdot \mathbf{t}_2}{\lVert \mathbf{t}_1 \rVert \, \lVert \mathbf{t}_2 \rVert} = \cos\theta$$
> L’**angolo** $\theta$ caratterizza la similarità **direzionale** tra i due token. A differenza della distanza euclidea, la similarità coseno **ignora il modulo** dei vettori e confronta solo la loro **orientazione** (Lezione 08, §4.2).

<!-- SOURCE_VISUAL id="IS13-V06" source="Lesson_13_LLM_MultimodalModels.pdf" page="23" type="schema" description="Similarità coseno tra due token come angolo tra i loro embedding" reason="Formula e angolo resi graficamente" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **3.3. L’economia dei token**

> 📌 **Token come unità di calcolo e di costo** (p. 25). Anche gli LLM più avanzati elaborano il testo come token, e i token servono anche a stimare **carico di lavoro**, **utilizzo**, **latenza** e **fatturazione**. I token sono **insieme un’unità computazionale e un’unità economica**: per questo le scelte di progetto su **prompt** e **contesto** hanno **conseguenze economiche dirette**.

> 💡 **Un conto rapido (nota didattica).** Se un servizio costa $c$ per milione di token e un’applicazione invia ogni giorno $R$ richieste da $k$ token (prompt e contesto più risposta), il costo giornaliero è $R \cdot k \cdot c / 10^6$. Allegare a ogni richiesta un documento di 20 000 token invece di un estratto da 2 000 moltiplica per circa dieci il costo, e allunga anche la latenza.

#### **3.4. Word2Vec**

_(slide pp. 26–28)_

**Word2Vec** (p. 26, in più versioni) associa le parole di un vocabolario a **vettori numerici densi**, appresi dalla **co-occorrenza** delle parole in grandi raccolte di testi. Parole che compaiono in **contesti simili** ottengono **vettori simili**: una parola → [Word2Vec] → un vettore.

**Addestramento** (p. 27): Word2Vec impara i vettori osservando **quali parole compaiono vicine** tra loro nei testi.

**Architettura** (p. 28):

1. la rete è addestrata a **predire parole dal loro contesto**;
2. la parola in ingresso è rappresentata come vettore **sparso**, per esempio one-hot;
3. dentro la rete questa rappresentazione viene **compressa** in una rappresentazione **densa e più piccola**: lo **spazio latente**;
4. dopo l’addestramento, le coordinate nello spazio latente **sono** l’embedding della parola, per esempio «A» → [0,2; 0,9], «cute» → [0,8; 0,4].

> 💡 **L’idea in una frase (nota didattica).** «Una parola si conosce dalla compagnia che frequenta.» Il compito di predizione è solo un pretesto: ciò che interessa alla fine non è la rete, ma i **pesi dello strato nascosto**, che diventano la tabella degli embedding. È lo stesso principio dell’estrazione neurale delle feature della Lezione 08, §3.2.

<!-- SOURCE_VISUAL id="IS13-V07" source="Lesson_13_LLM_MultimodalModels.pdf" page="27" type="schema" description="Addestramento di Word2Vec sulle parole vicine nel testo" reason="Finestre di contesto visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V08" source="Lesson_13_LLM_MultimodalModels.pdf" page="28" type="diagramma" description="Architettura di Word2Vec: input one-hot, spazio latente denso, output" reason="Struttura della rete visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **4. Sequenze, reti ricorrenti e attenzione**

_(slide pp. 29–36)_

#### **4.1. Le reti ricorrenti**

**La sequenza conta** (p. 29): parole in una frase, letture di sensori nel tempo, eventi di una macchina in un log, azioni di un utente in un processo. «La macchina si è fermata dopo l’allarme» è diverso da «l’allarme si è fermato dopo la macchina». Una rete **feed-forward** standard **non ricorda** che cosa è venuto prima.

> 📌 **Reti neurali ricorrenti** (RNN, pp. 29–30), le **precursori degli LLM**. Elaborano le sequenze **un passo alla volta**, mantenendo uno **stato interno** che porta con sé l’informazione degli input precedenti. Esempio: la frase «The movie was great» è la sequenza $x^{\langle 1 \rangle}$ = «The», $x^{\langle 2 \rangle}$ = «movie», $x^{\langle 3 \rangle}$ = «was», $x^{\langle 4 \rangle}$ = «great», con lunghezza $T_x = 4$.

> 💡 **L’aggiornamento dello stato (nota didattica).** A ogni passo $t$ lo stato nascosto combina l’input corrente con lo stato precedente:
> $$h^{\langle t \rangle} = f\big( W_h\, h^{\langle t-1 \rangle} + W_x\, x^{\langle t \rangle} + b \big)$$
> Gli stessi pesi $W_h$, $W_x$ si usano a ogni passo. Tutta la storia deve «passare» attraverso $h$, un vettore di dimensione fissa: per frasi lunghe l’informazione iniziale tende a sbiadire.

<!-- SOURCE_VISUAL id="IS13-V09" source="Lesson_13_LLM_MultimodalModels.pdf" page="30" type="diagramma" description="RNN che elabora The movie was great un token alla volta" reason="Catena degli stati visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

#### **4.2. L’attenzione**

**Serve l’attenzione: che cosa guardare nei token di input?** (p. 31). Nei compiti di **traduzione** c’era un problema reale con le **dipendenze a lungo termine**: i convertitori di testo classici come le RNN *seq2seq* possono non «ricordare» che cosa diceva la frase di ingresso.

> 📌 **Self-attention** (p. 32). Introdotta nell’articolo del **2017** «**Attention Is All You Need**», con risultati allo stato dell’arte nella traduzione automatica. La self-attention permette a **ogni token di guardare gli altri token** della stessa sequenza e crea il **contesto**.
>
> Esempio: per costruire la rappresentazione di «teddy bear» il modello può prestare attenzione ai token vicini come «cute», «is», «reading». La rappresentazione finale di «teddy bear» **dipende quindi dal contesto**, non è isolata. Il numero di token che il modello può considerare insieme è la **dimensione della finestra di contesto**.

<!-- SOURCE_VISUAL id="IS13-V10" source="Lesson_13_LLM_MultimodalModels.pdf" page="32" type="diagramma" description="Self-attention: il token teddy bear collegato ai token del contesto" reason="Collegamenti di attenzione visibili solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

**L’attenzione è quadratica** (p. 33). La self-attention standard crea una **matrice di attenzione** $n \times n$ per una sequenza di $n$ token. Ogni elemento misura **quanto un token presta attenzione a un altro** nella finestra di contesto. Tutti i token possono interagire, ma il **costo cresce come** $O(n^2)$.

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

**Il pezzo mancante: la codifica posizionale** (p. 34). L’attenzione da sola **non conosce l’ordine** della sequenza: «the machine stopped» e «stopped the machine» contengono le stesse parole, ma il significato cambia con l’ordine. La **codifica posizionale** aggiunge all’embedding l’informazione sulla **posizione** del token:

$$\text{rappresentazione del token} = \text{embedding del token} + \text{codifica posizionale}$$

Così il Transformer elabora sia **che cosa** è il token sia **dove** si trova nella sequenza.

#### **4.3. Query, Key, Value**

**Query, Key, Value** (p. 35). Per calcolare l’attenzione, la rappresentazione di ogni token viene **proiettata** in tre spazi vettoriali appresi:

| Vettore | Ruolo |
|---|---|
| **Query** $Q$ | «che cosa sto cercando» |
| **Key** $K$ | «che cosa offro»: con la Query determina **quanto** i token si prestano attenzione |
| **Value** $V$ | l’**informazione** che viene combinata nella rappresentazione di uscita |

> 📌 **Softmax dell’attenzione** (p. 36, **non d’esame**; formula standard). L’uscita dell’attenzione si ottiene calcolando con una **softmax** una distribuzione di probabilità sui token, e usandola per una **somma pesata dei vettori Value**:
> $$\operatorname{Attention}(Q, K, V) = \operatorname{softmax}\!\left( \frac{Q K^{\top}}{\sqrt{d_k}} \right) V$$
> dove $d_k$ è la **dimensione di ciascun vettore Key**. La matrice $\operatorname{softmax}(QK^\top/\sqrt{d_k})$ è proprio la matrice di attenzione $n \times n$ della p. 33.

> 💡 **Perché dividere per $\sqrt{d_k}$ (nota didattica).** I prodotti scalari tra vettori di dimensione grande tendono ad avere valori grandi; una softmax con input molto grandi diventa quasi un «tutto o niente» e i gradienti si annullano (la saturazione della Lezione 10, §6.3). Il fattore di scala tiene i valori in un intervallo in cui la softmax resta morbida.

<!-- SOURCE_VISUAL id="IS13-V11" source="Lesson_13_LLM_MultimodalModels.pdf" page="35" type="diagramma" description="Proiezioni Query, Key e Value delle rappresentazioni dei token" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V12" source="Lesson_13_LLM_MultimodalModels.pdf" page="36" type="formula" description="Softmax dell’attenzione con la dimensione dei vettori Key" reason="Formula resa come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **5. Encoder-decoder, Transformer e GPT**

_(slide pp. 37–39)_

> 📌 **Encoder-decoder** (p. 37). Un modello encoder-decoder trasforma una **sequenza di ingresso** in una **sequenza di uscita**: prima **codifica** l’input in una **rappresentazione numerica compatta**, poi la **decodifica** nell’uscita desiderata. Si usa **anche per le immagini**.

La p. 38 presenta il **Transformer**.

<!-- SOURCE_VISUAL id="IS13-V13" source="Lesson_13_LLM_MultimodalModels.pdf" page="37" type="diagramma" description="Architettura encoder-decoder, anche per immagini" reason="Diagramma presente solo come immagine" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V14" source="Lesson_13_LLM_MultimodalModels.pdf" page="38" type="diagramma" description="Architettura del Transformer" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 📌 **GPT** (p. 39). **Generative Pre-trained Transformer**: un modello linguistico basato sul Transformer, **pre-addestrato** su grandi corpora di testo e usato per **generare testo un token alla volta**. Esempio di traduzione con il Transformer originale: «I like science» → «Ich mag Wissenschaft», con probabilità 0,97.

> 💡 **Encoder-only e decoder-only (nota didattica).** La slide confronta il Transformer originale, con encoder **e** decoder, con varianti che ne usano **solo una parte** («only»). Le famiglie tipiche sono: **solo encoder** (per esempio BERT), adatta a classificare ed estrarre; **solo decoder** (i GPT), adatta a generare; **encoder-decoder** (il Transformer originale, T5), adatta a trasformare una sequenza in un’altra. L’abbinamento esatto con la figura va verificato sull’immagine.

<!-- SOURCE_VISUAL id="IS13-V15" source="Lesson_13_LLM_MultimodalModels.pdf" page="39" type="diagramma" description="Transformer originale e GPT con la traduzione I like science in Ich mag Wissenschaft" reason="Confronto delle architetture visibile solo nel diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **6. LLM moderni e modelli multimodali**

_(slide pp. 40–49)_

#### **6.1. Dai Transformer ai LLM moderni**

GPT ha introdotto l’idea di un Transformer **generativo pre-addestrato** (pp. 40–41). I sistemi moderni come **ChatGPT**, **Gemini**, **Claude** e gli **assistenti di programmazione** appartengono alla stessa famiglia generale di modelli.

> 📌 **Large Language Model** (p. 41). Un LLM è un **grande modello neurale del linguaggio**, addestrato su **enormi quantità di testo** per **elaborare, generare, riassumere, tradurre, classificare** e **ragionare** sul linguaggio. **Punto chiave**: input e output di base sono ancora **sequenze di token**.

#### **6.2. Modelli multimodali**

**Dagli LLM ai modelli multimodali** (p. 42). I modelli multimodali moderni elaborano **modalità di input diverse**: testo, immagini, audio, video, codice, documenti, e anche dati strutturati simili a quelli dei sensori, **se rappresentati in modo adeguato**.

> ⚠️ **Il modello non «vede»** (p. 42). Un modello multimodale **non vede** un’immagine come un essere umano: l’immagine viene convertita in **rappresentazioni numeriche** che possono interagire con quelle del testo. Il **testo** diventa **token**; le **immagini** diventano **patch visive** o **embedding visivi**.

> 💡 **Le patch (nota didattica).** Nei Vision Transformer un’immagine $224 \times 224$ viene divisa in patch $16 \times 16$: $(224/16)^2 = 14^2 = 196$ patch. Ogni patch diventa un vettore, cioè un «token visivo», e da lì l’attenzione funziona come per il testo. Anche un’immagine consuma quindi token, e quindi costo (§3.3).

**Modalità di input e output** (p. 43):

| Da → a | Esempio |
|---|---|
| testo → testo | risposta di un chatbot, riassunto, report |
| immagine → testo | *visual question answering*, **descrizione di un difetto** |
| testo → immagine | diagramma, immagine sintetica, concetto visivo |
| audio → testo | **trascrizione** del parlato |
| testo + immagine → dati strutturati | **rapporto di ispezione**, record JSON, tabella |
| testo + strumenti → azione | **aprire un ticket**, eseguire uno script, interrogare un database |

#### **6.3. Mixture of experts e modelli guidati da prompt**

Le pp. 44–45 presentano la **mixture of experts**.

> 💡 **Mixture of experts (nota didattica).** Invece di un unico blocco feed-forward, uno strato contiene $E$ **esperti** (sottoreti) e un **router** che, per ogni token, sceglie solo i $k$ esperti più adatti (per esempio 2 su 8) e ne combina le uscite con pesi $g_e(x)$:
> $$y = \sum_{e \in \text{top-}k(x)} g_e(x)\, \text{Expert}_e(x)$$
> Il modello ha **molti parametri in totale**, ma ne **attiva solo una parte** per token: capacità alta con costo di calcolo contenuto. È una delle architetture citate nella Lezione 04, §3.1, tra le vie verso l’efficienza.

<!-- SOURCE_VISUAL id="IS13-V16" source="Lesson_13_LLM_MultimodalModels.pdf" page="44" type="diagramma" description="Architettura mixture of experts con router ed esperti" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V17" source="Lesson_13_LLM_MultimodalModels.pdf" page="45" type="diagramma" description="Mixture of experts: selezione degli esperti per token" reason="Pagina con solo diagramma" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Le pp. 46–47 presentano i **modelli guidati da prompt** (nella slide «Promotable models», verosimilmente per *promptable*), con l’esempio dei modelli **LLaVA**: modelli multimodali che svolgono compiti diversi in base alle istruzioni date nel prompt.

<!-- SOURCE_VISUAL id="IS13-V18" source="Lesson_13_LLM_MultimodalModels.pdf" page="46" type="immagini" description="Modelli guidati da prompt" reason="Pagina con sole immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS13-V19" source="Lesson_13_LLM_MultimodalModels.pdf" page="47" type="immagini" description="Esempi dei modelli LLaVA guidati da prompt" reason="Esempi visibili solo nelle immagini" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> ⚠️ **Non come motore decisionale principale** (p. 48). Nelle applicazioni **critiche** o **in tempo reale**, i modelli guidati da prompt spesso **non sono adatti come motore decisionale principale**. In questi casi è di solito preferibile usare **modelli di ML addestrati e validati**, con **prestazioni misurate chiaramente**, **input e output stabili** e **vincoli di rilascio deterministici**.

> 💡 **Perché (nota didattica).** Lo stesso prompt può produrre risposte diverse, una piccola modifica del testo può cambiare l’esito, e le prestazioni su un compito specifico raramente sono misurate come quelle di un modello validato su un test set. È il ragionamento dell’AI Act sui sistemi ad alto rischio (Lezione 02, §1.4) e della scatola nera della Lezione 04, §2.

La p. 49 contiene solo un’immagine.

<!-- SOURCE_VISUAL id="IS13-V20" source="Lesson_13_LLM_MultimodalModels.pdf" page="49" type="immagine" description="Immagine di chiusura della parte sui modelli multimodali" reason="Pagina senza testo estraibile" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

---

### **7. Verso gli agenti**

_(slide p. 50)_

**Prossime lezioni**: che cos’è un agente? Come usare gli agenti in IS4?

> 📌 **Agente** (p. 50). Un agente **non è solo un modello linguistico**: è un LLM inserito in un **ciclo di lavoro** con **contesto**, **memoria**, **strumenti**, **controlli** ed eventualmente l’**approvazione umana**.

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

### **9. Sintesi della lezione**

_(slide p. 51)_

> ✅ **Punti principali** (p. 51).
> - Gli LLM sono **strumenti di supporto utili**, **non sostituti** dei modelli di ML validati.
> - Il testo viene elaborato come **token**, poi associato a **vettori di embedding** densi.
> - Gli **embedding** rappresentano la **similarità semantica** in uno spazio vettoriale numerico.
> - La **self-attention** fa interagire i token dentro la **finestra di contesto**, con costo quadratico.
> - La **codifica posizionale** aggiunge l’informazione sull’**ordine** dei token.
> - Le proiezioni **Query, Key e Value** calcolano e applicano l’attenzione.
> - I **Transformer** combinano embedding, informazione posizionale, attenzione e strati feed-forward.
> - **Modelli multimodali** (LMM): testo, immagini, audio e altro diventano rappresentazioni numeriche comuni.
> - **Siamo pronti a usare gli agenti.**
