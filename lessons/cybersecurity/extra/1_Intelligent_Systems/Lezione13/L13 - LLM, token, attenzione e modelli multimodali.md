## **Lezione 13: LLM, modelli multimodali e agenti per il ML — token, embedding, attenzione, Transformer**

I **modelli linguistici di grandi dimensioni** (LLM, *Large Language Model*) entrano nel lavoro di chi progetta sistemi intelligenti quando nel flusso compaiono linguaggio, documenti, log, codice e interazioni in più passi. Per usarli con consapevolezza non serve un corso completo sul loro addestramento: serve capire che cosa sono — essenzialmente **una grande matrice di pesi e di valori di probabilità** — e come trasformano un testo in numeri. La lezione segue questa trasformazione: il testo diventa **token**, i token diventano **embedding**, gli embedding interagiscono attraverso l’**attenzione** e la **codifica posizionale**, e l’intera architettura prende il nome di **Transformer**; da lì si arriva ai modelli **multimodali**, ai modelli **guidati da prompt** e alle **mixture of experts**, fino alla soglia degli **agenti**. Il messaggio ingegneristico resta fermo: nelle applicazioni industriali il **machine learning classico rimane il nucleo predittivo**, e gli LLM lo affiancano, non lo sostituiscono.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L13-web.pdf` (70 pagine). Alcune slide sono tratte o adattate dal corso CME 295 *Transformers & Large Language Models* di Afshine Amidi e Shervine Amidi (Stanford University). I segnaposto per le figure indicano la pagina del PDF.


### **0. Panoramica**


La lezione si articola in cinque temi:

1. **perché gli LLM contano** per i professionisti del ML nei sistemi IS4;
2. **dal testo ai numeri**: token ed embedding;
3. **sequenze, attenzione e Transformer**;
4. **dagli LLM ai modelli multimodali e guidati da prompt**;
5. **verso gli agenti**: che cosa viene dopo.

![](imgs/Pasted%20image%2020261123225153.png)

#### **0.1. Che cosa questa lezione non è**

Non è un corso completo su:

- l’**addestramento degli LLM**;
- i **modelli multimodali**;
- i **dettagli interni** dei Transformer;
- la **rincorsa ai benchmark**.

La lezione copre **solo ciò che serve per lavorare con gli agenti in modo consapevole** come professionisti del ML: è volutamente selettiva.

![](imgs/Pasted%20image%2020261123225232.png)

#### **0.2. Obiettivi: essere consapevoli ed efficaci nella pratica**

> 📌 **Promemoria.** Quando si interagisce con un LLM si sta parlando con un **modello addestrato**: in sostanza una **matrice di pesi e di valori di probabilità**. L’obiettivo è **capire il sistema abbastanza bene da usarlo in modo sicuro, critico e produttivo**.


![](imgs/Pasted%20image%2020261123225240.png)

---

### **1. Introduzione agli LLM per IS4**


Che cos’è un LLM, che cos’è un agente, e dove si collocano nel flusso di lavoro IS4.

#### **1.1. Che cosa sono gli LLM**

> 📌 **Large Language Model.** Un LLM è un **modello di deep learning** addestrato su **collezioni di testo molto grandi** per **prevedere e generare sequenze di token**.
> - Nella forma più semplice riceve **testo in ingresso** e genera **testo in uscita**.
> - Nei sistemi moderni riceve un **contesto più ampio** (istruzioni, cronologia della conversazione, file, risultati di strumenti, memoria) e produce la **continuazione più probabile**: una risposta, un riassunto, una spiegazione, del codice o un piano.


![](imgs/Pasted%20image%2020261123225353.png)

> 💡 **«Continuazione più probabile» (nota didattica).** A ogni passo il modello calcola una distribuzione di probabilità sul token successivo, condizionata da tutto ciò che lo precede, e ne sceglie uno; poi ripete con il testo allungato. Una risposta lunga è quindi una catena di previsioni locali: il modello non «consulta» un archivio di risposte, e ciò spiega sia la sua flessibilità sia la possibilità di produrre affermazioni plausibili ma false (Lezione 1).

#### **1.2. Che cosa sono gli agenti**

> 📌 **Agente di AI.** È un **sistema software** che usa un modello di AI per perseguire un **obiettivo definito dall’utente**, ragionando **iterativamente** sul contesto, **scegliendo azioni**, usando **strumenti o memoria**, **osservando i risultati** e decidendo il passo successivo **finché non si raggiunge una condizione di arresto**.

Gli agenti **non sono modelli separati**: sono **sistemi costruiti attorno a un LLM**. La differenza chiave è il **ciclo**: ricevere un obiettivo, chiamare il modello, usare contesto e strumenti, osservare i risultati e iterare.


![](imgs/Pasted%20image%2020261123225345.png)

#### **1.3. ML contro LLM in IS4: il ML classico resta il nucleo**

Molte applicazioni IS4 sono ancora **machine learning classico**:

- **previsione** (*forecasting*);
- **rilevamento di anomalie**;
- **manutenzione predittiva**;
- **stima della qualità**;
- **supporto all’ottimizzazione**.

In molti problemi IS4 il ML classico resta il **nucleo predittivo**.

![](imgs/Pasted%20image%2020261123225442.png)

**La decisione ingegneristica:**

| Usare il **ML classico** per… | Usare **LLM o agenti** quando… |
|---|---|
| previsioni numeriche, punteggi di anomalia, modelli predittivi | nel flusso entrano **documenti, log, codice** o **interazione a più passi** |

> 📌 **Gli LLM completano il ML.** Diventano utili quando nel ciclo entrano **linguaggio, documenti, codice e ragionamento interattivo**: aiutano a **interpretare log**, **recuperare procedure**, **generare report** e **supportare le decisioni umane**. Non vanno presentati come sostituti del ML.

> 💡 **Perché non un LLM per la previsione numerica (nota didattica).** Un modello di regressione addestrato su dati di sensori ha errori misurati su un test set, risponde in millisecondi, costa frazioni di centesimo e dà sempre la stessa uscita per lo stesso input. Un LLM che «legge» la tabella dei sensori può dare risposte diverse a ogni chiamata, ha costi per token (§3.6) e non ha prestazioni validate su quel compito. Il criterio è quello del rasoio di Occam della Lezione 5: lo strumento più semplice che risolve il problema.

#### **1.4. NLP**

> 📌 **Elaborazione del linguaggio naturale.** Il NLP (*Natural Language Processing*) è un campo dell’intelligenza artificiale e dell’informatica che studia metodi per permettere ai computer di **elaborare, analizzare, capire e generare** il linguaggio umano. Permette ai sistemi intelligenti di **estrarre informazioni**, **classificare contenuti**, **rispondere a domande**, **riassumere documenti** e **generare testo** a partire da dati in linguaggio umano.

![](imgs/Pasted%20image%2020261123225503.png)

#### **1.5. Il flusso di lavoro IS4 resta la spina dorsale**

$$\text{problema}\to\text{dati}\to\text{rappresentazione}\to\text{modello}\to\text{valutazione}\to\text{rilascio}\to\text{monitoraggio}$$

LLM e agenti vengono introdotti per **migliorare** questo flusso, **non per sostituirlo**. Possono aiutare in:

- **ricostruzione del contesto** del progetto;
- **preparazione dei dati** e **analisi dei dati mancanti**;
- **progettazione degli esperimenti** e **reportistica**;
- **monitoraggio**, **audit** e **documentazione**.

La spina dorsale resta il flusso di lavoro del ML; gli agenti sono un **supporto attorno** a quella sequenza.

**Usare gli agenti per migliorare il contesto di progetto.** Un agente può raccogliere vincoli, stato degli esperimenti, decisioni già prese e documenti del progetto, e restituirli in una forma che rende più informata ogni fase successiva (il *context engineering* della Lezione 15).


![](imgs/Pasted%20image%2020261123225532.png)

---

### **2. Dalle basi ai GPT: compiti di NLP e metriche**


Compiti di NLP, token, embedding, attenzione, Transformer.

#### **2.1. ML contro generazione: l’input è testo**

Con un input testuale si distinguono tre famiglie di compiti:

| Famiglia | Uscita | Esempio |
|---|---|---|
| **classificazione** | **un’etichetta** per l’intero input | analisi del sentiment |
| **etichettatura a livello di token** | **etichette per parti** dell’input | riconoscimento delle entità |
| **generazione** | **nuovo testo** | traduzione, riassunto |

Gli LLM moderni si usano **soprattutto come modelli generativi**, ma molte applicazioni industriali hanno ancora bisogno di **classificazione** e di **estrazione a livello di token**.


![](imgs/Pasted%20image%2020261123225614.png)

#### **2.2. Compito di NLP: estrazione del sentiment**

Si classifica il **tono** di un testo (per esempio positivo o negativo). Le metriche di valutazione sono quelle della classificazione:

| Metrica | Significato |
|---|---|
| **accuratezza** | frazione di previsioni corrette su tutte le osservazioni |
| **precisione** | frazione dei positivi previsti che sono veri positivi |
| **recall** | frazione dei veri positivi che vengono recuperati |
| **F1** | media armonica di precisione e recall |

$$\text{precisione}=\frac{TP}{TP+FP},\qquad \text{recall}=\frac{TP}{TP+FN},\qquad F_1=\frac{2\cdot\text{precisione}\cdot\text{recall}}{\text{precisione}+\text{recall}}.$$

**Esempio**: $TP=8$, $FP=2$, $FN=4$ (veri positivi, falsi positivi, falsi negativi):

$$\text{precisione}=\frac{8}{10}=0{,}80,\qquad \text{recall}=\frac{8}{12}=0{,}67,\qquad F_1=\frac{2\cdot 0{,}80\cdot 0{,}67}{0{,}80+0{,}67}=0{,}73.$$


![](imgs/Pasted%20image%2020261123225700.png)

> 💡 **Perché la media armonica (nota didattica).** La media armonica è dominata dal valore più piccolo: con precisione 1 e recall 0,1 la media aritmetica sarebbe 0,55, la F1 vale solo 0,18. Un sistema non può quindi ottenere una F1 alta eccellendo in una sola delle due metriche. L’accuratezza, invece, è ingannevole con classi sbilanciate (Lezione 14).

#### **2.3. Compito di NLP: riconoscimento delle entità**

> 📌 **NER.** Il riconoscimento delle entità nominate (NER, *Named Entity Recognition*) **identifica e classifica le entità** citate nel testo: **nomi**, **organizzazioni**, **luoghi**, **date**, **codici** e **quantità**.

Il NER si valuta a **livello di token** o a **livello di intervallo di entità** (*entity span*), di solito **per tipo di entità**, con accuratezza, precisione, recall e F1.


![](imgs/Pasted%20image%2020261123225710.png)

> 💡 **Token contro intervallo (nota didattica).** In «macchina M-204 ferma dal 12 marzo», l’entità «12 marzo» è formata da due token. Valutando per token, riconoscere solo «12» conta come mezzo successo; valutando per intervallo, l’entità è sbagliata perché i confini non coincidono. In applicazioni industriali (codici di macchina, numeri di lotto) di solito interessa la valutazione per intervallo: un codice riconosciuto a metà è inutilizzabile.

#### **2.4. Compito di NLP: traduzione automatica e n-grammi**

> 📌 **n-gramma.** Una **sequenza contigua di $n$ parole, caratteri o token** estratta da un testo.

Dal testo «*the machine stopped suddenly*»:

| $n$ | Esempio |
|---|---|
| 1-gramma | *machine* |
| 2-gramma | *machine stopped* |
| 3-gramma | *the machine stopped* |


![](imgs/Pasted%20image%2020261123225735.png)

#### **2.5. Metriche per il testo generato: BLEU**

BLEU (*Bilingual Evaluation Understudy*) misura la **sovrapposizione di n-grammi** con traduzioni di riferimento:

$$p_n=\frac{\text{n-grammi del candidato presenti nel riferimento}}{\text{n-grammi del candidato}}.$$

- $p_n$ è la **precisione sugli n-grammi** di lunghezza $n$ (gli 1-grammi sono parole, i 2-grammi coppie di parole).
- Il **BLEU finale** combina $p_1,\dots,p_4$ con una **penalità di brevità**.

**Esempio.** Riferimento: «*the machine stopped suddenly*». Candidato: «*the machine stopped quickly*».

$$p_1=\frac34=0{,}75,\qquad p_2=\frac23=0{,}67.$$

Gli 1-grammi trovati sono *the*, *machine*, *stopped*; i 2-grammi trovati sono «*the machine*» e «*machine stopped*».

> 💡 **I conti e la formula completa (nota didattica).** Il candidato ha 4 parole, di cui 3 nel riferimento; ha 3 bigrammi (*the machine*, *machine stopped*, *stopped quickly*), di cui 2 nel riferimento. La formula completa è
> $$\text{BLEU}=BP\cdot\exp\!\left(\sum_{n=1}^{4}\tfrac14\log p_n\right),\qquad BP=\min\!\left(1,\ e^{\,1-r/c}\right),$$
> con $r$ lunghezza del riferimento e $c$ del candidato: la penalità di brevità impedisce di ottenere precisione alta con una traduzione cortissima («*the*» avrebbe $p_1=1$).

#### **2.6. Metriche per il testo generato: ROUGE**

ROUGE (*Recall-Oriented Understudy for Gisting Evaluation*) misura la **sovrapposizione con riassunti o testi di riferimento**:

$$\text{ROUGE-1 recall}=\frac{\text{parole del riferimento presenti nel candidato}}{\text{parole del riferimento}}.$$

- La **precisione** usa la stessa sovrapposizione divisa per le **parole del candidato**.
- **BLEU è orientata alla precisione, ROUGE al recall.**

**Esempio.** Riassunto di riferimento: «*the machine stopped suddenly*» (4 parole). Candidato: «*the machine stopped*» (3 parole). **Recall** $=3/4=0{,}75$; **precisione** $=3/3=1{,}00$.

> 💡 **Perché due metriche diverse (nota didattica).** In traduzione conta non dire cose che il riferimento non dice (precisione); in un riassunto conta non perdere i contenuti essenziali (recall). Entrambe misurano però la sovrapposizione delle **parole**, non del significato: una parafrasi corretta con sinonimi ottiene punteggi bassi, come nel confronto per lettere della Lezione 9 (§8.5.1).

#### **2.7. Metriche per il testo generato: la perplessità**

La **perplessità** misura **quanto una sequenza di testo è inattesa per il modello**:

$$\text{PPL}=\exp\!\left(-\frac1N\sum_{i=1}^{N}\log p(w_i\mid w_{<i})\right).$$

- $N$: numero di token del testo; $w_i$: l’$i$-esimo token; $w_{<i}$: i token precedenti.
- $p(w_i\mid w_{<i})$: probabilità che il modello ha assegnato al **token effettivo** $w_i$.
- **Perplessità più bassa** = il testo è **meno sorprendente** per il modello.

**Esempio.** $N=4$ token con probabilità 0,5; 0,25; 0,5; 0,25:

$$\text{PPL}=(0{,}5\cdot 0{,}25\cdot 0{,}5\cdot 0{,}25)^{-1/4}=64^{1/4}\approx 2{,}83,$$

come se a ogni passo il modello scegliesse tra **circa 3 token equiprobabili**.

> 💡 **Perché l’esponenziale della media dei logaritmi (nota didattica).** $\exp\!\big(-\frac1N\sum\log p_i\big)=\big(\prod p_i\big)^{-1/N}$: la perplessità è l’inverso della **media geometrica** delle probabilità assegnate ai token veri. Qui il prodotto è $1/64$ e la sua radice quarta $1/2{,}83$. Un modello che prevede ogni token con certezza ha perplessità 1; un modello che sceglie uniformemente tra $V$ token ha perplessità $V$. È lo stesso concetto della perplexity di t-SNE della Lezione 11 (§5.2): un «numero effettivo di scelte».

---

### **3. Dal testo ai numeri: token ed embedding**


#### **3.1. Il token, unità di base dell’elaborazione**

Un LLM **non elabora il testo come parole o frasi**: lo elabora come **token**. Un token può essere:

- una **parola intera**;
- **parte di una parola**;
- un **segno di punteggiatura**;
- un **frammento di numero**;
- un **frammento di un identificatore tecnico**.

> 📌 **Token.** È un’**unità di testo indivisibile**, come una parola, una sotto-parola o un carattere, e fa parte di un **vocabolario predefinito**. Token speciali:
> - **[UNK]** rappresenta porzioni di testo **sconosciute** o mai viste;
> - **[PAD]** riempie le posizioni vuote perché le sequenze abbiano **la stessa lunghezza**.

##### **3.1.1. BPE e [UNK]**

**BPE** (*Byte Pair Encoding*) costruisce il vocabolario partendo da **unità di base** e **fondendo ripetutamente le coppie più frequenti** in token più lunghi (per esempio $t+e\to te$).

Nel **BPE a livello di byte** (famiglia GPT) le unità di base sono i **256 byte possibili** del testo (UTF-8), quindi **qualsiasi stringa** si può scrivere come sequenza di token: **non serve [UNK]**.

**Esempio.** La parola mai vista «*Zxqv*», con un tokenizzatore a livello di parola, diventa **[UNK]**: l’informazione è **persa**. Con il BPE a livello di byte viene spezzata in **pezzi più piccoli**, fino ai singoli byte (per esempio *Z*, *xq*, *v*); un’emoji o un simbolo raro viene diviso nei suoi **1–4 byte UTF-8**. Il testo diventa **più lungo in token**, ma **non viene mai perso**.

> 💡 **Una fusione BPE passo per passo (nota didattica).** Con il corpus «*low lower lowest*», tra i caratteri la coppia più frequente è *l+o* (3 volte): diventa il token *lo*. Al passo successivo *lo+w* (3 volte) diventa *low*. Dopo poche fusioni le parole frequenti sono un solo token, mentre quelle rare restano spezzate. Il numero di fusioni fissa la dimensione del vocabolario (tipicamente decine di migliaia di token).

##### **3.1.2. Modi diversi di creare i token e il tokenizzatore**


![](imgs/Pasted%20image%2020261123230342.png)

Un **tokenizzatore** $T$ divide il testo in token a un livello di granularità arbitrario. Esempio:

«*this teddy bear is reaaaally cute*» $\xrightarrow{\ T\ }$ `this` · `teddy` · `bear` · `is` · `[UNK]` · `cute` · `[PAD]` · … · `[PAD]`

La parola deformata «*reaaaally*» non è nel vocabolario e diventa `[UNK]`; i `[PAD]` finali allungano la sequenza fino alla lunghezza fissata. L’esempio vale per un tokenizzatore con vocabolario finito senza scomposizione alternativa; con il BPE a livello di byte la parola sarebbe stata spezzata in frammenti noti.

##### **3.1.3. Riepilogo della tokenizzazione**

| Tipo | Pro | Contro | Esempio |
|---|---|---|---|
| **parola** | facile da interpretare; **sequenze corte** | **vocabolario grande**; le varianti delle parole non sono gestite | `teddy` ; `bear` |
| **sotto-parola** | sfrutta le **radici** delle parole; embedding intuitivi | **sequenze più lunghe**; tokenizzazione più complessa | `ted` ; `##y` ; `bear` |
| **carattere** | **nessun problema di parole fuori vocabolario**; vocabolario piccolo | **sequenze molto più lunghe**; pattern difficili da interpretare perché di livello troppo basso | `t` ; `e` ; `d` ; `d` ; `y` ; `b` ; `e` ; `a` ; `r` |

> 💡 **Il prefisso `##` (nota didattica).** Nella tokenizzazione a sotto-parole di tipo WordPiece (usata da BERT), `##` segnala che il frammento **continua** la parola precedente: `ted` `##y` si ricompone in *teddy*. Senza questo segno non si saprebbe se ricostruire «*ted y*» o «*teddy*».

#### **3.2. Dal testo agli ID dei token agli embedding**

Come un testo grezzo diventa input numerico per un LLM: il **tokenizzatore** converte il testo in **token** e **ID dei token** (numeri interi, la posizione nel vocabolario), e lo **strato di embedding** associa a quegli ID dei **vettori densi**.


![](imgs/Pasted%20image%2020261123230440.png)

> 💡 **Lo strato di embedding è una tabella (nota didattica).** È una matrice $E\in\mathbb{R}^{|V|\times d}$, con una riga per ogni token del vocabolario: l’ID $i$ seleziona semplicemente la riga $i$. Le righe sono parametri appresi durante l’addestramento. Con $|V|=50\,000$ e $d=768$, la tabella ha $38{,}4$ milioni di parametri.

#### **3.3. Perché i token: la codifica one-hot**

- La codifica banale è **one-hot**: ogni token è un **vettore indipendente**, con un solo 1 nella posizione del token.
- **Token diversi sono ortogonali**, quindi la **similarità semantica non viene catturata**.
- Usando il **prodotto scalare** come similarità:

$$\langle\text{teddy bear},\text{book}\rangle=0,\qquad \langle\text{teddy bear},\text{soft}\rangle=0$$

(eppure un orsacchiotto è morbido!).

> 📌 **Per questo servono embedding (buoni!)**: collocano i **token correlati vicini** nello spazio vettoriale. Dimensioni: il vettore one-hot ha dimensione $|V|$ (il vocabolario, per esempio **50 000 numeri per token**); un embedding ha $d\ll|V|$ (per esempio **768**).


![](imgs/Pasted%20image%2020261123230516.png)

> 💡 **Il legame con la Lezione 16 (nota didattica).** È lo stesso problema della codifica one-hot delle variabili categoriche: tutte le categorie sono equidistanti tra loro ($\sqrt2$ in distanza euclidea). Va bene per poche categorie senza relazioni; per un vocabolario di decine di migliaia di parole con relazioni di significato serve una rappresentazione appresa.

#### **3.4. Perché i token: la codifica appresa**

- Gli **embedding appresi** rappresentano i token come **vettori densi**.
- A differenza dei vettori one-hot, possono **codificare la similarità semantica**:

$$\cos\text{-similarity}(\text{teddy bear},\text{soft})\ \text{alta (per esempio}\approx 0{,}8),\qquad \cos\text{-similarity}(\text{teddy bear},\text{book})\ \text{bassa (per esempio}\approx 0{,}1).$$

I valori sono illustrativi. Così «*teddy bear*» è **più vicino a «*soft*» che a «*book*»** nello spazio vettoriale appreso.


![](imgs/Pasted%20image%2020261123230806.png)

#### **3.5. Embedding e similarità coseno**

> 📌 **Embedding.** È una **rappresentazione numerica** di un elemento (per esempio un token o una frase), caratterizzata da un vettore $z\in\mathbb{R}^d$: l’embedding di un elemento, come nello spazio di rappresentazione $z=f(x)\in\mathbb{R}^d$ della Lezione 8.

| Stringa del token | ID del token | Vettore di embedding |
|---|---:|---|
| «The» | 101 | $[0{,}5;\ 0{,}8;\ \dots;\ 0{,}65]$ |
| «cat» | 2598 | $[0{,}1;\ 0{,}3;\ \dots;\ -0{,}82]$ |
| «sat» | 4450 | $[0{,}41;\ -0{,}53;\ \dots;\ 0{,}8]$ |
| «.» | 119 | $[0{,}17;\ 0{,}75;\ \dots;\ 0{,}2]$ |

**La similarità coseno** tra due embedding $z_1,z_2$ è

$$\text{similarity}(z_1,z_2)=\frac{z_1\cdot z_2}{\lVert z_1\rVert\,\lVert z_2\rVert}=\cos\theta\in[-1,1].$$

- $z_1,z_2\in\mathbb{R}^d$ sono i vettori di embedding dei due token ($d$ = dimensione; $d=2$ nell’esempio); $z_1\cdot z_2$ è il loro **prodotto scalare**; $\lVert\cdot\rVert$ la **lunghezza** (norma euclidea).
- L’**angolo** $\theta$ tra i due vettori caratterizza la similarità tra i due token.
- **Esempio**: $z_1=(1,0)$, $z_2=(1,1)$ danno $\dfrac{1}{1\cdot\sqrt2}\approx 0{,}71$, cioè $\theta=45^\circ$.

Similarità e distanza coseno sono state introdotte nella Lezione 8.

**Direzione, non modulo.** L’angolo caratterizza la similarità **direzionale** tra due token o vettori di feature. A differenza della distanza euclidea, la similarità coseno **ignora il modulo** dei vettori e confronta l’**orientazione** nello spazio degli embedding.

**Esempio**: $a=(1,1)$, $b=(2,2)$: stessa direzione, $b$ lungo il doppio. $\cos(a,b)=\dfrac{4}{\sqrt2\cdot\sqrt8}=\dfrac44=1$, ma la distanza euclidea è $\sqrt2\approx 1{,}41$.


![](imgs/Pasted%20image%2020261123231010.png)

#### **3.6. L’economia dei token**

Anche gli LLM più avanzati elaborano il testo come token. I token **non sono solo una rappresentazione tecnica**: servono anche a stimare **carico di lavoro**, **utilizzo**, **latenza** e **fatturazione**.

> 📌 **I token sono insieme un’unità computazionale e un’unità economica.** Questo doppio ruolo spiega perché le **scelte di progetto su prompt e contesto** hanno **conseguenze economiche dirette**.

**Esempio illustrativo** (input a 1 $ e output a 4 $ per milione di token): 2000 token di input + 500 di output costano

$$2000\cdot\frac{1\ \$}{10^6}+500\cdot\frac{4\ \$}{10^6}=0{,}002+0{,}002=0{,}004\ \$\ \text{per richiesta};\qquad 10^6\ \text{richieste}=4000\ \$.$$

##### **3.6.1. L’economia dei token: esempio di listino**

Dollari per milione di token (scala logaritmica nella figura), con il costo di una richiesta da 2000 token di input e 500 di output:

| Modello | Input ($/1M) | Output ($/1M) | Per richiesta | 1M richieste |
|---|---:|---:|---:|---:|
| GPT-6 Luna (piccolo) | 0,10 | 0,50 | 0,00045 $ | 450 $ |
| DeepSeek V4.1 Flash | 0,15 | 0,60 | 0,0006 $ | 600 $ |
| Gemini 3.8 Flash | 0,75 | 3,75 | 0,0034 $ | 3 375 $ |
| Claude Sonnet 5.5 | 2 | 10 | 0,009 $ | 9 000 $ |
| Claude Opus 5.5 | 4 | 20 | 0,018 $ | 18 000 $ |
| GPT-6 Astra (ammiraglia) | 10 | 50 | 0,045 $ | 45 000 $ |


![](imgs/Pasted%20image%2020261123231128.png)

- L’**output costa 4–5 volte l’input**: la generazione è **sequenziale**.
- Tra modello ammiraglio e modello piccolo il costo differisce di **circa 100 volte**.
- I prezzi di listino sono stati controllati il **18 ottobre 2026** (livello standard, contesto corto) e **cambiano spesso** (per esempio tariffe fuori picco o promozionali): **non d’esame**.

> 💡 **Perché l’output costa di più (nota didattica).** I token di input vengono elaborati **tutti insieme in parallelo** in un solo passaggio; i token di output vengono generati **uno alla volta**, e ognuno richiede un passaggio completo del modello. Per lo stesso numero di token la generazione occupa l’hardware molto più a lungo. Allegare a ogni richiesta un documento di 20 000 token invece di un estratto da 2000 moltiplica per circa dieci il costo di input e allunga la latenza.

**Aggiornamento dei prezzi dei modelli.** La tabella è uno snapshot del 18 ottobre 2026, dichiarato non d’esame. Il listino API consultato il 20 novembre 2026 riporta per GPT‑6.2 Astra 8 USD input e 40 USD output per milione di token, e per GPT‑6.2 Sol 2 USD e 10 USD: la tariffa di Astra è scesa rispetto ai 10/50 USD della tabella di ottobre. I prezzi dipendono da modello, modalità e fascia di contesto e vanno ricontrollati prima di una stima economica. [Listino API OpenAI](https://developers.openai.com/api/docs/pricing), [annuncio GPT‑6.2 Astra e Sol](https://openai.com/index/introducing-gpt-6-2-astra-sol/).

#### **3.7. Word2Vec**

**Word2Vec** associa le parole di un vocabolario a **vettori numerici densi**, appresi dalla **co-occorrenza delle parole** in grandi corpora di testo.

- **Parole che compaiono in contesti simili tendono a ottenere vettori simili.**
- Una parola → [Word2Vec] → un vettore.


![](imgs/Pasted%20image%2020261123231234.png)

**Addestramento.** Word2Vec impara i vettori delle parole **osservando come le parole compaiono vicine** tra loro in grandi raccolte di testo: una finestra scorre sul testo e, per ogni parola centrale, le parole nella finestra sono il suo **contesto**.

![](imgs/Pasted%20image%2020261123231822.png)


<!-- SOURCE_VISUAL id="IS13-V16" source="L13-web.pdf" page="35" type="schema" description="Finestra di contesto che scorre sul testo per creare le coppie parola-contesto di Word2Vec" reason="Finestre di contesto visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->


![](imgs/Pasted%20image%2020261123231831.png)

**Dallo spazio latente all’embedding.**

- **Skip-gram** (il caso della figura): la rete riceve **una parola** e predice **una parola del suo contesto**. **CBOW** (*Continuous Bag of Words*) è l’opposto: riceve le **parole del contesto** e predice la **parola centrale**.
- La parola di ingresso è prima rappresentata come **vettore sparso**, per esempio **one-hot**.
- Dentro la rete questa rappresentazione sparsa viene **compressa** in una rappresentazione **densa più piccola**: lo **spazio latente**.
- Dopo l’addestramento, **le coordinate in questo spazio latente diventano il vettore di embedding** della parola.

**Esempio**: «A» → $[0{,}2;\ 0{,}9]$, «cute» → $[0{,}8;\ 0{,}4]$, …

> 💡 **L’idea centrale (nota didattica).** «Una parola si conosce dalla compagnia che frequenta» (J. R. Firth, 1957). Il compito di predizione è solo un pretesto: alla fine non interessa la rete, ma i **pesi dello strato nascosto**, che diventano la tabella degli embedding. Moltiplicare un vettore one-hot per la matrice dei pesi equivale a selezionarne una riga: quella riga è l’embedding. È lo stesso principio dell’encoder appreso della Lezione 9 e degli autoencoder della Lezione 22.

---

### **4. Sequenze, attenzione e Transformer**


#### **4.1. Reti neurali ricorrenti: perché la sequenza conta**

**La sequenza è importante**:

- parole in una frase;
- letture di sensori nel tempo;
- eventi di una macchina in un log;
- azioni di un utente in un processo.

**Esempio**: «*the machine stopped after the alarm*» (la macchina si è fermata dopo l’allarme) è diverso da «*the alarm stopped after the machine*» (l’allarme si è fermato dopo la macchina).

Una rete **feed-forward** standard **non ricorda naturalmente** che cosa è venuto prima. Le **reti neurali ricorrenti** (RNN, *Recurrent Neural Network*) sono state introdotte per elaborare le sequenze **un passo alla volta**, mantenendo uno **stato interno** che porta l’informazione degli input precedenti: sono le **precursori degli LLM**.


![](imgs/Pasted%20image%2020261123231953.png)

Lo schema srotolato della slide successiva mostra gli input $x_1,\dots,x_n$ («*A cute teddy bear is reading*»), gli stati nascosti $h_0,\dots,h_n$ e le previsioni $y_1,\dots,y_n$ («*Un ours en peluche mignon lit*»): ogni stato riceve l’input corrente e lo stato precedente.

![](imgs/Pasted%20image%2020261123232010.png)

**Esempio.** La frase «*The movie was great*» è la sequenza $x^{\langle 1\rangle}$ = «The», $x^{\langle 2\rangle}$ = «movie», $x^{\langle 3\rangle}$ = «was», $x^{\langle 4\rangle}$ = «great», con lunghezza $T_x=4$. Nella figura lo stato nascosto $h^{\langle t\rangle}$ è indicato con $a^{\langle t\rangle}$.


![](imgs/Pasted%20image%2020261123232039.png)

> 💡 **L’aggiornamento dello stato (nota didattica).** A ogni passo $t$ lo stato nascosto combina l’input corrente con lo stato precedente:
> $$h^{\langle t\rangle}=f\big(W_h\,h^{\langle t-1\rangle}+W_x\,x^{\langle t\rangle}+b\big).$$
> Gli **stessi pesi** $W_h$, $W_x$ si usano a ogni passo. Tutta la storia deve passare attraverso $h$, un vettore di dimensione fissa: per frasi lunghe l’informazione iniziale tende a sbiadire, e durante l’addestramento il gradiente, moltiplicato ripetutamente per $W_h$, tende a svanire o esplodere (Lezione 20). Inoltre il calcolo è **sequenziale**: il passo $t$ non può iniziare prima del passo $t-1$.

#### **4.2. Serve l’attenzione!**

**Che cosa devo guardare nei token di input?**

- I compiti di **traduzione** avevano un problema reale con le **dipendenze a lungo termine**.
- I convertitori di testo classici come le RNN (*seq2seq*) possono **non «ricordare»** che cosa diceva la frase di ingresso.
- **Attenzione**: per prevedere la parola successiva (?) il modello **guarda indietro ai token di input rilevanti**; una freccia più spessa indica un peso maggiore.

Pesi di attenzione illustrativi per la parola successiva: **reading 0,60**, **teddy bear 0,25**, **is 0,15**.


![](imgs/Pasted%20image%2020261123232058.png)

#### **4.3. Soluzione 1: la self-attention, che crea il «contesto»**

- Introdotta nell’articolo del **2017** «***Attention Is All You Need***» (Vaswani et al.).
- Risultati **allo stato dell’arte** nella traduzione automatica.


![](imgs/Pasted%20image%2020261123232144.png)

**Come leggere il diagramma.**

- La self-attention permette a **ogni token di guardare gli altri token della stessa sequenza**.
- Il modello sta costruendo la rappresentazione del token «*teddy bear*».
- Può prestare attenzione ai token circostanti come «*cute*», «*is*» e «*reading*».
- Le linee rappresentano le **possibili connessioni di attenzione** tra il token corrente e gli altri.
- La rappresentazione finale di «*teddy bear*» è quindi **dipendente dal contesto**, non isolata.

<!-- SOURCE_VISUAL id="IS13-V22" source="L13-web.pdf" page="44" type="schema" description="Il token teddy bear collegato da linee di attenzione a cute, is e reading" reason="Collegamenti di attenzione visibili solo nello schema" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

> 💡 **Contesto contro stato (nota didattica).** Nella RNN l’informazione su «*cute*» deve sopravvivere passando di stato in stato fino a «*teddy bear*»; con la self-attention ogni token accede **direttamente** a ogni altro, a distanza uno, qualunque sia la sua posizione. In più tutte le coppie si calcolano **in parallelo**, ed è questo che ha permesso di addestrare modelli enormi sulle GPU.

#### **4.4. L’attenzione è quadratica: un esempio con 5 token**

Sequenza di $n=5$ token: «*A cute teddy bear reads*». Righe: il token che guarda; colonne: il token guardato.

| | A | cute | teddy | bear | reads |
|---|---:|---:|---:|---:|---:|
| **A** | 0,10 | 0,20 | 0,30 | 0,20 | 0,20 |
| **cute** | 0,05 | 0,10 | **0,45** | 0,30 | 0,10 |
| **teddy** | 0,05 | **0,20** | 0,25 | 0,40 | 0,10 |
| **bear** | 0,05 | 0,10 | 0,50 | 0,25 | 0,10 |
| **reads** | 0,10 | 0,15 | 0,35 | 0,30 | 0,10 |

- La matrice ha $5\times 5=25$ elementi; **ogni riga somma a 1**.
- **Cella più scura** = il token della riga presta più attenzione al token della colonna.
- **Le matrici di attenzione in generale non sono simmetriche**: «*cute*» → «*teddy*» $=0{,}45$, ma «*teddy*» → «*cute*» $=0{,}20$.
- La matrice mostrata è il caso **bidirezionale**. Nei decoder **di tipo GPT** si applica una **maschera causale**: ogni token presta attenzione **solo ai token precedenti** (gli elementi **sopra la diagonale valgono 0**).

> 💡 **La maschera causale (nota didattica).** Un modello che genera testo non può guardare il futuro, perché quando produce il token $i$ i token successivi non esistono ancora; durante l’addestramento, in cui l’intera frase è nota, la maschera impedisce di «barare» guardando la risposta. Con la maschera la prima riga avrebbe un solo elemento non nullo (A → A = 1), la seconda due, e così via: la matrice diventa triangolare inferiore, sempre con righe a somma 1.

#### **4.5. L’attenzione è quadratica: $O(n^2)$ nella lunghezza del contesto**

- La self-attention standard crea una **matrice di attenzione $n\times n$** per una sequenza di $n$ token.
- Ogni elemento misura **quanto un token presta attenzione a un altro** nella finestra di contesto corrente.
- **Tutti i token interagiscono**, ma il **costo computazionale cresce come $O(n^2)$**.
- **Finestra di contesto** = numero **massimo di token** (prompt + risposta) che il modello elabora **in una volta**.
- **Elementi**: $n=5\to 25$; $n=1000\to 10^6$; $n=10\,000\to 10^8$: **10 volte più token = 100 volte più elementi**.


![](imgs/Pasted%20image%2020261123232323.png)

> 💡 **Conseguenze pratiche (nota didattica).** Raddoppiare il contesto quadruplica memoria e calcolo dell’attenzione: è il motivo per cui i contesti lunghi costano di più, per cui i modelli hanno una finestra massima e per cui un contesto pieno di materiale irrilevante peggiora sia il costo sia la qualità (il *context rot* della Lezione 17). Molte architetture recenti usano varianti più efficienti dell’attenzione proprio per attenuare questa crescita.

#### **4.6. Il pezzo mancante: la codifica posizionale**

L’**attenzione da sola non conosce l’ordine** della sequenza.

| Sequenza | Significato |
|---|---|
| *the machine stopped* | la macchina si è fermata |
| *stopped the machine* | (qualcuno) ha fermato la macchina |

Compaiono le **stesse parole**, ma il **significato cambia perché cambia l’ordine**: un modello che mescola i token **solo con l’attenzione** non può distinguere le due sequenze.

> 💡 **Perché l’attenzione ignora l’ordine (nota didattica).** I pesi dipendono solo dai prodotti scalari tra le rappresentazioni dei token, e la somma pesata finale non cambia se si permutano i termini. Senza un’informazione aggiuntiva, l’attenzione tratta la frase come un **insieme** di parole (*bag of words*).

**La codifica posizionale aggiunge dove si trova il token.** La catena è: testo grezzo → tokenizzatore → ID dei token → embedding dei token **+ informazione posizionale** → input della self-attention.

$$\text{rappresentazione del token}=\text{embedding del token}+\text{codifica posizionale}.$$

Il Transformer elabora così sia **che cosa** è il token sia **dove** si trova nella sequenza.


![](imgs/Pasted%20image%2020261123232405.png)

> 💡 **La codifica sinusoidale originale (nota didattica).** Nel Transformer del 2017 la posizione $pos$ è codificata con seni e coseni di frequenze diverse: $PE_{(pos,2i)}=\sin\!\big(pos/10000^{2i/d}\big)$, $PE_{(pos,2i+1)}=\cos\!\big(pos/10000^{2i/d}\big)$. Ogni posizione riceve un vettore unico della stessa dimensione dell’embedding, e lo spostamento tra due posizioni corrisponde a una trasformazione lineare, che l’attenzione può imparare a sfruttare. Molti modelli moderni usano varianti apprese o rotazionali.

#### **4.7. Attenzione: Query, Key, Value**

Per calcolare l’attenzione, la rappresentazione di ogni token viene **proiettata in tre spazi vettoriali appresi**: **Query** $q$, **Key** $k$, **Value** $v$.

- **Query e Key** determinano **quanto i token si prestano attenzione** a vicenda;
- **Value** contiene l’**informazione combinata** nella rappresentazione di uscita.

$$q_i=W_Qx_i,\qquad k_i=W_Kx_i,\qquad v_i=W_Vx_i,\qquad s_{ij}=q_i\cdot k_j.$$

$x_i$ è la rappresentazione del token (embedding + posizione, $d_{\text{model}}$ numeri); $W_Q,W_K\in\mathbb{R}^{d_k\times d_{\text{model}}}$ e $W_V\in\mathbb{R}^{d_v\times d_{\text{model}}}$ sono **appresi**. **Esempio**: $d_{\text{model}}=512$, $d_k=64$: $W_Q$ ha $64\cdot 512=32\,768$ numeri.


![](imgs/Pasted%20image%2020261123232543.png)

| Vettore | Domanda intuitiva |
|---|---|
| **Query** $q$ | che cosa sto cercando? |
| **Key** $k$ | che cosa offro a chi cerca? |
| **Value** $v$ | quale informazione passo se vengo scelto? |

In questi esempi «*teddy bear*» è trattato come **un solo token**, per leggibilità.

##### **4.7.1. I pesi di attenzione: l’esempio di «teddy bear»**

$$\text{pesi di attenzione}=\text{softmax}(\text{punteggi}).$$

| Token | Peso |
|---|---:|
| a | 0,05 |
| cute | 0,30 |
| teddy bear | 0,25 |
| is | 0,10 |
| reading | 0,25 |
| . | 0,05 |
| **somma** | **1,00** |

I punteggi nascono dal **confronto della query** $q_{\text{teddy bear}}$ **con ogni key** $k$; la **softmax** li trasforma in **pesi che sommano a 1**. La **rappresentazione di uscita** di «*teddy bear*» è la **somma pesata dei vettori value** $v$:

$$\text{out}_{\text{teddy bear}}=0{,}05\,v_a+0{,}30\,v_{\text{cute}}+0{,}25\,v_{\text{teddy bear}}+0{,}10\,v_{\text{is}}+0{,}25\,v_{\text{reading}}+0{,}05\,v_{.}$$

> 💡 **La softmax (nota didattica).** Dati i punteggi $s_1,\dots,s_n$, la softmax produce $w_j=e^{s_j}/\sum_m e^{s_m}$: valori tra 0 e 1, che sommano a 1, e che amplificano le differenze tra i punteggi. È la stessa funzione che trasforma i punteggi di classe in probabilità nell’uscita di un classificatore (Lezione 11, §5.14.3).

##### **4.7.2. La softmax dell’attenzione (non d’esame)**

L’uscita dell’attenzione si ottiene calcolando con una **softmax** una distribuzione di probabilità sui token e usandola per una **somma pesata dei vettori Value**:

$$\text{Attention}(Q,K,V)=\text{softmax}\!\left(\frac{QK^\top}{\sqrt{d_k}}\right)V.$$

- $Q$, $K$, $V$: matrici che impilano i vettori query, key e value di **tutti i token** (una riga per token).
- $QK^\top$: **tutti i punteggi** query–key in una volta (matrice $n\times n$); $d_k$: dimensione di ogni vettore Key; **dividere per $\sqrt{d_k}$** tiene i punteggi in un intervallo **stabile** (per esempio $d_k=64$: si divide per 8).
- La softmax agisce **su ogni riga**: pesi in $[0,1]$ che sommano a 1; **moltiplicando per $V$** si ottiene l’uscita.

> 💡 **Perché dividere per $\sqrt{d_k}$ (nota didattica).** Se le componenti di $q$ e $k$ sono indipendenti con media 0 e varianza 1, il loro prodotto scalare è una somma di $d_k$ termini e ha varianza $d_k$, cioè deviazione standard $\sqrt{d_k}$. Con $d_k=64$ i punteggi sarebbero dell’ordine di $\pm 8$: la softmax diventerebbe quasi un «tutto o niente» e i gradienti si annullerebbero. Dividere per $\sqrt{d_k}$ riporta i punteggi a varianza 1.

#### **4.8. Calcolo efficiente con le matrici: l’attenzione multi-testa**

Ognuna delle $h$ **teste** ha i propri $W_Q$, $W_K$, $W_V$; le $h$ uscite vengono **concatenate** e **mescolate da uno strato lineare**. **Esempio**: $h=8$, $d_k=64$, $d_{\text{model}}=8\cdot 64=512$.


![](imgs/Pasted%20image%2020261123232634.png)

> 💡 **Perché più teste (nota didattica).** Una sola testa produce un’unica distribuzione di attenzione per token: deve scegliere se guardare la relazione grammaticale, quella di significato o quella di posizione. Con 8 teste da 64 dimensioni, ciascuna può specializzarsi su una relazione diversa, allo stesso costo di una testa da 512. È lo stesso principio dei molti filtri di uno strato convolutivo (Lezione 9, §3.10): più «rilevatori» paralleli, ciascuno con i propri pesi.

#### **4.9. Soluzione 2: gli encoder-decoder**

> 📌 **Encoder-decoder.** Un modello encoder-decoder **trasforma una sequenza di ingresso in una sequenza di uscita**: prima **codifica** l’input in una **rappresentazione numerica compatta**, poi la **decodifica** nell’uscita desiderata. Si usa **anche per le immagini**.


![](imgs/Pasted%20image%2020261123232926.png)

#### **4.10. Benvenuti Transformer**

Il **Transformer** combina gli elementi visti: embedding, codifica posizionale, attenzione multi-testa, strati feed-forward, connessioni residue e normalizzazioni, impilati in un encoder e in un decoder.


![](imgs/Pasted%20image%2020261123232942.png)

#### **4.11. Il Transformer originale e GPT**

> 📌 **GPT.** **Generative Pre-trained Transformer**: un modello linguistico basato sul Transformer, **pre-addestrato** su grandi corpora di testo e usato per **generare testo un token alla volta**.

GPT tiene **solo la parte decoder** del Transformer originale (**decoder-only**). Il Transformer originale, usato nell’esempio della traduzione «*I like science*» → «*Ich mag Wissenschaft*», è un **encoder-decoder**.


![](imgs/Pasted%20image%2020261123233018.png)

> 💡 **Tre famiglie (nota didattica).** **Encoder-only** (per esempio BERT): attenzione bidirezionale, adatto a produrre rappresentazioni e a classificare o estrarre entità. **Decoder-only** (GPT): attenzione con maschera causale, adatto a generare. **Encoder-decoder** (il Transformer originale, T5): adatto a trasformare una sequenza in un’altra, come nella traduzione.

---

### **5. LLM moderni e modelli multimodali**


#### **5.1. Dai Transformer agli LLM moderni**

- **GPT** ha introdotto l’idea di un **Generative Pre-trained Transformer**: un modello basato sul Transformer, addestrato su grandi corpora di testo e usato per **generare testo token per token**.
- Sistemi moderni come **ChatGPT**, **Gemini**, **Claude** e gli **assistenti di programmazione** sono costruiti attorno alla **stessa famiglia generale di modelli**.


![](imgs/Pasted%20image%2020261123233054.png)

> ⚠️ **Prospettiva 2026, non d’esame (nomi di prodotto).** Come fotografia dei rilasci al **20 novembre 2026**, OpenAI ha annunciato GPT‑6.2 Astra/Sol il 17 novembre, Anthropic Claude Fable 5.2 e Mythos 5.2 il 9 novembre, Google Gemini 4 Neon il 18 novembre e Meta Muse Wonder il 12 ottobre. Mythos è accessibile tramite programmi di verifica; nomi, disponibilità e capacità non sono intercambiabili fra prodotti dello stesso fornitore. I benchmark pubblicati dai produttori non costituiscono da soli un confronto indipendente. [OpenAI](https://openai.com/index/introducing-gpt-6-2-astra-sol/), [Anthropic](https://www.anthropic.com/claude-fable-and-mythos-5-2), [Google](https://blog.google/innovation-and-ai/models-and-research/gemini-models/introducing-gemini-4-neon/), [Meta](https://ai.meta.com/research/).

#### **5.2. Dal Transformer al sistema agentico**

La slide mostra il passaggio dall’architettura del modello al **sistema** che lo circonda: il Transformer diventa il nucleo di un sistema con contesto, strumenti, memoria e controlli.


![](imgs/Pasted%20image%2020261123233128.png)

#### **5.3. Dagli LLM ai modelli multimodali**

I modelli multimodali moderni possono elaborare **diverse modalità di input**:

- **testo**;
- **immagini**;
- **audio**;
- **video**;
- **codice**;
- **documenti**;
- **dati strutturati simili a quelli dei sensori**, **se rappresentati in modo adeguato**.


![](imgs/Pasted%20image%2020261123233139.png)

#### **5.4. Modelli multimodali: token ed embedding visivi**

- Il modello **non «vede» direttamente** un’immagine come gli esseri umani.
- L’immagine viene convertita in **rappresentazioni numeriche** che possono **interagire con le rappresentazioni del testo**.
- Il **testo diventa token**.
- Le **immagini diventano patch visive** o **embedding visivi**.


![](imgs/Pasted%20image%2020261123233202.png)

> 💡 **Le patch visive (nota didattica).** Nei Vision Transformer un’immagine $224\times 224$ viene divisa in patch $16\times 16$: $(224/16)^2=14^2=196$ patch. Ogni patch (16·16·3 = 768 valori) viene proiettata in un vettore, cioè un «token visivo», e da lì l’attenzione funziona come per il testo (Lezione 24). Anche un’immagine consuma quindi token, e costo (§3.6). L’allineamento tra token visivi e testuali richiede l’addestramento congiunto visto nella Lezione 9 (§8.6).

#### **5.5. Modalità di input e di output**

Un sistema di AI multimodale può combinare **diversi canali di ingresso e di uscita**:

| Input → output | Esempio |
|---|---|
| testo → testo | risposta di un chatbot, riassunto, report |
| immagine → testo | *visual question answering*, **descrizione di un difetto** |
| testo → immagine | diagramma, immagine sintetica, concetto visivo |
| audio → testo | **trascrizione** del parlato |
| testo + immagine → dati strutturati | **rapporto di ispezione**, record JSON, tabella |
| testo + strumenti → azione | **aprire un ticket**, eseguire uno script, interrogare un database |

> 💡 **L’ultima riga è il ponte verso gli agenti (nota didattica).** Le prime cinque righe producono informazione; l’ultima produce un **effetto nel mondo** (un ticket aperto, uno script eseguito). È qui che servono permessi, controlli e approvazione umana (Lezione 15).

#### **5.6. Modelli guidati da prompt (promptable)**

Un **modello promptable** svolge compiti diversi in base alle **istruzioni del prompt**, senza essere riaddestrato per ciascun compito: lo stesso modello descrive un’immagine, risponde a una domanda su di essa o estrae dati strutturati.


![](imgs/Pasted%20image%2020261123233303.png)

**Modelli promptable (modelli LLaVA).** LLaVA (*Large Language and Vision Assistant*) collega un encoder visivo a un LLM e risponde a domande sulle immagini. *Prospettiva 2026, non d’esame (LLaVA e altri nomi di prodotto).*


![](imgs/Pasted%20image%2020261123233337.png)

**Ancora visiva: usi IS4 dei modelli promptable.**


![](imgs/Pasted%20image%2020261123233425.png)

##### **5.6.1. Modelli promptable: quando non usarli**

> ⚠️ **Impiego in sistemi critici.** Nelle applicazioni **critiche** o **in tempo reale**, i modelli promptable **spesso non sono adatti come motore decisionale principale**. In questi casi è di solito preferibile usare **modelli di ML addestrati e validati**, con **prestazioni misurate chiaramente**, **input e output stabili** e **vincoli di rilascio deterministici**.

> 💡 **Perché (nota didattica).** Lo stesso prompt può produrre risposte diverse, una piccola modifica del testo può cambiare l’esito, e le prestazioni su un compito specifico raramente sono misurate come quelle di un modello validato su un test set. Per i sistemi ad alto rischio l’AI Act richiede accuratezza, robustezza e monitoraggio documentati (Lezioni 2 e 11): requisiti più facili da dimostrare per un modello addestrato e validato su quel compito.

**Ancora visiva: i modelli promptable come strumenti di supporto in IS4.** Il ruolo corretto è quello di **supporto**: preparare report, descrivere difetti per un operatore, estrarre dati da documenti, lasciando la decisione critica a un modello validato o a una persona.


![](imgs/Pasted%20image%2020261123233517.png)

#### **5.7. Mixture of experts**

Un **router** (*gating network*, rete di instradamento) invia ogni token a **$k$ dei $E$ esperti**; **solo quei $k$ vengono calcolati**. Nella figura $k=1$ su $E=4$. **Parametri totali $\gg$ parametri attivi.**


![](imgs/Pasted%20image%2020261123233543.png)

> 💡 **In formula (nota didattica).** Invece di un unico blocco feed-forward, lo strato contiene $E$ esperti (sottoreti) e un router che, per ogni token $x$, sceglie i $k$ esperti più adatti e ne combina le uscite con i pesi $g_e(x)$:
> $$y=\sum_{e\in\text{top-}k(x)}g_e(x)\,\text{Expert}_e(x).$$
> Con $E=8$ esperti e $k=2$, il modello ha i parametri di 8 blocchi ma ne calcola 2 per token: **capacità alta con costo di calcolo contenuto**. Tutti gli esperti però devono stare in memoria, e il router va addestrato in modo che il carico si distribuisca tra gli esperti.

**Mixture of experts (esempio multimodale).** Lo stesso principio si applica ai modelli multimodali: esperti diversi possono specializzarsi su token di modalità diverse.


![](imgs/Pasted%20image%2020261123233604.png)

---

### **6. Verso gli agenti e punti principali**


#### **6.1. Prossime lezioni: che cos’è un agente? Come usare gli agenti in IS4?**

> 📌 **Agente.** Un agente **non è solo un modello linguistico**: è un **LLM inserito in un ciclo di lavoro** con **contesto**, **memoria**, **strumenti**, **controlli** ed eventualmente **approvazione umana**.


![](imgs/Pasted%20image%2020261123233626.png)

#### **6.2. Punti principali**

> ✅ **Punti principali della lezione.**
> - Gli LLM sono **strumenti di supporto utili**, **non sostituti dei modelli di ML validati**.
> - Il testo viene elaborato come **token**, poi mappato in **vettori di embedding densi**.
> - Gli **embedding** rappresentano la **similarità semantica** in uno spazio vettoriale numerico.
> - La **self-attention** fa interagire i token **dentro la finestra di contesto**.
> - La **codifica posizionale** aggiunge l’informazione sull’**ordine** dei token.
> - Le proiezioni **Query, Key e Value** calcolano e applicano l’attenzione.
> - I **Transformer** combinano embedding, informazione posizionale, attenzione e strati feed-forward.
> - **Embedding e similarità coseno** (Lezioni 6 e 8) sono la **base del retrieval**: le prossime lezioni li usano per **RAG e agenti**.
> - I **modelli multimodali** (LMM, *Large Multimodal Model*) estendono tutto questo ad altre modalità: si è pronti a usare gli agenti per il flusso di lavoro del ML nelle lezioni successive.

---

### **7. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **LLM** / **agente** | modello che genera la continuazione più probabile / sistema che usa l’LLM in un ciclo con strumenti, memoria e arresto |
| **ML classico** / **LLM e agenti** | nucleo predittivo su dati numerici / supporto quando entrano documenti, log, codice e interazione |
| **classificazione** / **etichettatura dei token** / **generazione** | un’etichetta per tutto il testo / etichette per parti / testo nuovo |
| **precisione** / **recall** / **F1** | $TP/(TP+FP)$ / $TP/(TP+FN)$ / media armonica |
| **BLEU** / **ROUGE** | orientata alla precisione sugli n-grammi / orientata al recall rispetto al riferimento |
| **perplessità bassa** / **alta** | testo atteso dal modello / testo sorprendente |
| **token parola** / **sotto-parola** / **carattere** | sequenze corte, vocabolario grande / compromesso / nessun fuori vocabolario, sequenze lunghissime |
| **[UNK]** / **[PAD]** | testo sconosciuto / riempimento per lunghezza fissa |
| **tokenizzatore a parole** / **BPE a livello di byte** | perde le parole mai viste / non perde mai il testo, lo allunga |
| **one-hot** / **embedding appreso** | vettori ortogonali di dimensione $\lvert V\rvert$ / vettori densi di dimensione $d\ll\lvert V\rvert$ con similarità semantica |
| **similarità coseno** / **distanza euclidea** | solo l’orientazione / anche il modulo |
| **skip-gram** / **CBOW** | dalla parola al contesto / dal contesto alla parola |
| **RNN** / **self-attention** | stato che passa passo per passo, sequenziale / ogni token guarda direttamente tutti gli altri, in parallelo |
| **attenzione bidirezionale** / **causale** | tutti i token / solo i precedenti (triangolare inferiore) |
| **attenzione** / **codifica posizionale** | che cosa guardare, costo $O(n^2)$ / dove si trova il token |
| **Query e Key** / **Value** | decidono quanto prestare attenzione / contenuto combinato in uscita |
| **encoder-decoder** / **decoder-only (GPT)** | trasforma una sequenza in un’altra / genera un token alla volta |
| **token di input** / **token di output** | elaborati in parallelo, costano meno / generati in sequenza, costano 4–5 volte di più |
| **modello denso** / **mixture of experts** | tutti i parametri attivi per ogni token / solo $k$ esperti su $E$ per token |
| **modello promptable** / **modello validato** | flessibile ma non deterministico / prestazioni misurate, adatto a decisioni critiche |
