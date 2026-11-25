## **Lezione 17: Usare i nuovi modelli nella pratica — contesto degli agenti, bias-varianza, regressori e classificatori lineari, reti feed-forward e overfitting**

La lezione unisce due fili. Il primo riguarda gli **agenti**: un LLM non ricorda nulla tra una chiamata e l’altra, e il **contesto** che gli viene inviato a ogni turno cresce, costa e, oltre una certa misura, peggiora la qualità delle risposte (*context rot*); sei tecniche di gestione — offloading, RAG, selezione dinamica degli strumenti, pruning, riassunto e quarantena — lo tengono sotto controllo. Il secondo filo costruisce i **primi modelli completi**: un albero di decisione sul dataset Wine che diventa un «learner perfetto» sul training e si rivela imperfetto in validazione incrociata, il **dilemma bias-varianza** che spiega perché, la **LDA** di Fisher come feature engineering supervisionata e come classificatore, una **checklist** per affrontare un modello nuovo, la **regressione lineare** con minimi quadrati, gradiente e il quartetto di Anscombe, e infine le prime **reti feed-forward** in MATLAB e Python, fino all’overfitting di una rete da 100 neuroni addestrata su 51 punti.

> **Fonte.** La lezione segue il PDF dell’edizione 2026/27 `sources_2026-27/L17-web.pdf` (89 pagine). I segnaposto per le figure indicano la pagina del PDF. I file di laboratorio della cartella (`Lesson_16_decision_tree_wine_confusionmatrix_crossvalidation.py`, `Lesson_16_laboratory_COLAB_linear_regressors.ipynb`, `Lesson_16_laboratory_MATLAB_linear_and_neural.m`) sono commentati nel testo e nel §10.


### **0. Panoramica**


1. **agenti**: gestione della memoria e del contesto;
2. **errori di classificazione**: matrice di confusione, accuratezza, in Python;
3. **il compromesso bias/varianza**: il dilemma dietro ogni modello;
4. **feature engineering + classificazione**: PCA contro LDA, in MATLAB;
5. **un nuovo modello?** Che cosa sapere, che cosa fare: una checklist per un avvio corretto;
6. **i primi modelli completi**: regressori e classificatori lineari, in MATLAB e Python;
7. **le prime reti neurali e l’overfitting!!**;
8. punti principali e verifica della comprensione.

![](imgs/Pasted%20image%2020261124044059.png)

---

### **1. La memoria degli agenti: la gestione del contesto**


Controllare, gestire, comprimere, ricominciare…

#### **1.1. Agenti per il ML: dove aiutano, che cosa possono fare**

| Fase del flusso | In che cosa l’agente può aiutare | Che cosa deve essere validato dalle persone |
|---|---|---|
| definizione del problema | ricostruire il contesto, estrarre i vincoli | definizione del compito, variabile target |
| dati | ispezionare file, rilevare valori mancanti, proporre la pulizia | leakage, unità, etichette |
| rappresentazione | suggerire feature, codificare variabili categoriche | significato delle feature, rischio causale |
| modello | proporre baseline e candidati | scelta del modello e ipotesi |
| valutazione | calcolare metriche, confrontare modelli | validità della divisione di test, significato statistico |
| reportistica | generare tabelle, grafici, spiegazioni | affermazioni e conclusioni |
| monitoraggio | preparare controlli di drift e dashboard | rischio di rilascio e soglie |

È la stessa tabella della Lezione 15 (§1.3): l’agente accelera, la persona valida le decisioni che possono invalidare il risultato.

#### **1.2. Il contesto negli LLM e negli agenti**

> 📌 **Contesto.** Negli LLM e negli agenti il contesto è lo **stato informativo attivo** passato al modello **al momento dell’inferenza**. Comprende: **istruzioni dell’utente**, **cronologia della conversazione**, **documenti recuperati**, **risultati degli strumenti**, **regole di sistema**, **riassunti della memoria**, **definizioni degli strumenti disponibili**.

<!-- SOURCE_VISUAL id="IS17-V01" source="L17-web.pdf" page="5" type="figura" description="Gli elementi che compongono il contesto inviato al modello" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124044136.png)

#### **1.3. La dimensione del contesto aumenta!**

Si ha **più spazio per dire qualcosa di significativo**.

<!-- SOURCE_VISUAL id="IS17-V02" source="L17-web.pdf" page="6" type="grafico" description="Crescita nel tempo della finestra di contesto dei modelli" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124044240.png)

- **A metà 2026**, una finestra di contesto da **1 milione di token** è lo standard dello stato dell’arte per i modelli cloud di fascia alta, e permette di elaborare **dataset immensi** — intere codebase o centinaia di documenti — **in un solo prompt**!
- Una finestra da 1M di token equivale a circa **750 000 parole**, oltre **2000 pagine** di testo.
- I test «**ago nel pagliaio**» (*needle in a haystack*) mostrano un’**accuratezza alta**. Tuttavia alcuni utenti segnalano **latenza maggiore** e un possibile **peggioramento delle prestazioni** al limite di 1M.

> 💡 **Il rapporto parole/token (nota didattica).** 750 000 parole su 1 000 000 di token significa circa 0,75 parole per token, cioè circa 1,33 token per parola in inglese: le parole comuni sono un token, quelle lunghe o rare si spezzano in più token (Lezione 13). In italiano, con un tokenizzatore addestrato soprattutto sull’inglese, il rapporto è di solito peggiore.

**Contesto locale e cloud: una finestra più grande non significa una comprensione migliore.**

| | **Modelli cloud di frontiera** | **Modelli locali / open-weight** |
|---|---|---|
| **contesto tipico** | ≈ **1M token** sui modelli attuali (Claude 5.x, GPT-6, Gemini 4) | 256K–1M **dichiarati**; **8K–128K pratici**, a seconda di RAM/VRAM (la *KV cache* cresce con il contesto) |
| **modelli d’esempio** | Claude Fable 5.1, Opus 5.5, Sonnet 5.5, Haiku 5.5 (tutti 1M); GPT-6 Astra (1,05M); Gemini 4 Argon (1M) | gpt-oss-20b (16 GB) e 120b (una GPU da 80 GB); Qwen3.5 (262K); Gemma 4 (256K); DeepSeek-V4 (1M, serve un cluster) |
| **vantaggio principale** | contesto ampio, uso robusto degli strumenti, infrastruttura veloce | privacy, controllo dei dati, costo marginale basso |
| **limite principale** | i dati lasciano la macchina locale; il costo cresce con i token | il contesto è limitato da hardware e velocità |
| **rischio nascosto** | più contesto può comunque causare **context rot** | l’uso degli strumenti può fallire prima che la dimensione del contesto diventi utile |

Verificato il 26 ottobre 2026 (documentazione Anthropic, OpenAI, Google AI; guida open-weight di contextstudios.ai): **le cifre cambiano rapidamente**.

> 💡 **La KV cache (nota didattica).** Durante la generazione il modello conserva, per ogni token già elaborato e per ogni strato, i vettori Key e Value dell’attenzione (Lezione 13, §4.7), per non ricalcolarli a ogni nuovo token. Questa memoria cresce linearmente con la lunghezza del contesto: su una GPU da 16 GB, che deve contenere anche i pesi del modello, lo spazio per la cache limita il contesto utilizzabile molto prima del valore dichiarato.

#### **1.4. Il contesto dell’LLM si ricostruisce a ogni turno di chat**

- Una chiamata a un LLM è **senza stato** (*stateless*). Dopo che il modello ha generato una risposta, il suo **contesto attivo interno viene scartato**.
- Ciò che **persiste** non sta dentro l’LLM, ma nell’**applicazione circostante**, come ChatGPT, Claude Code, Codex, Cursor o un framework di agenti personalizzato: un’applicazione attorno all’LLM che **conserva la chat e i file modificati**.

![](imgs/Pasted%20image%2020261124044513.png)


<!-- SOURCE_VISUAL id="IS17-V03" source="L17-web.pdf" page="9" type="figura" description="L'applicazione attorno all'LLM che conserva chat e file mentre il modello è senza stato" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V04" source="L17-web.pdf" page="10" type="schema" description="Il contesto ricostruito e reinviato al modello a ogni turno di chat" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124044531.png)

#### **1.5. Il contesto cresce nelle chat e con gli agenti**

<!-- SOURCE_VISUAL id="IS17-V05" source="L17-web.pdf" page="11" type="schema" description="Il contesto che cresce turno dopo turno in una chat e in una sessione agentica" reason="Schema visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124044654.png)

**Un esempio di contesto che cresce.** Si assume: prompt di sistema = **200 token**, ogni messaggio dell’utente ≈ **50 token**, ogni risposta dell’assistente ≈ **300 token**.

| Turno | Contesto inviato all’LLM | Risposta | Log dopo il turno |
|---:|---|---:|---:|
| 1 | $200+50=250$ | 300 | 350 |
| 2 | $200+50+300+50=600$ | 300 | 700 |
| 3 | $200+50+300+50+300+50=950$ | — | — |
| $N$ | $200+50N+300(N-1)=350N-100$ | | |

Per esempio con $N=300$: $350\cdot 300-100=104\,900$ token.

> 💡 **Perché il costo totale è quadratico (nota didattica).** Ogni turno reinvia tutta la storia: il contesto del turno $N$ cresce **linearmente**, ma la somma dei token inviati in $N$ turni è $\sum_{k=1}^{N}(350k-100)=175N(N+1)-100N$, che cresce come $N^2$. Con 300 turni si inviano in totale circa 15,8 milioni di token, anche se il contesto finale è «solo» di 105 000. A questo si somma il costo $O(n^2)$ dell’attenzione su ogni singola chiamata (Lezione 13, §4.5).

**Nota.** Una soluzione parziale: usare la **skill «caveman»**, che **minimizza le parole del prompt**, così ogni turno aggiunge meno token al contesto.

![](imgs/Pasted%20image%2020261124044727.png)

#### **1.6. Context rot: mai usare tutto il contesto fin dall’inizio**

- La finestra di contesto **non va trattata solo come un limite rigido di memoria**.
- Man mano che la conversazione cresce, la **qualità dell’uscita può peggiorare prima che il contesto sia tecnicamente pieno**: l’informazione rilevante diventa **più difficile da mettere in priorità**, le istruzioni possono **competere con il contenuto più vecchio**, e il modello può iniziare a **ignorare o usare male le evidenze**.
- I sistemi pratici hanno quindi bisogno di **strategie di gestione del contesto** — recupero, riassunto, rotazione e **auto-compattazione** — **prima che la qualità crolli**.

<!-- SOURCE_VISUAL id="IS17-V06" source="L17-web.pdf" page="13" type="grafico" description="Qualità dell'uscita che peggiora al crescere del contesto prima del limite tecnico" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124044758.png)

#### **1.7. Tecniche di gestione del contesto**

> 📌 **Context engineering.** È un insieme di meccanismi per **prevenire il context rot** gestendo **che cosa il modello può vedere**. I metodi principali sono: **offloading**, **RAG**, **selezione degli strumenti** (*tool loadout*, selezione dinamica), **pruning**, **riassunto**, **quarantena**. In una riga: **tenere fuori ciò che è grande, mostrare solo ciò che è utile ora, comprimere o isolare il resto.**

##### **1.7.1. Gestione 1: offloading**

**Memorizzare l’informazione grande o persistente fuori dal prompt attivo** (file, archivi di memoria, *scratchpad*, database).

**Esempio**: l’agente salva il suo piano di 40 righe in `notes.md` e **rilegge solo le righe che gli servono**.

- **Input**: un piano di **2000 token**, necessario a ogni turno.
- **Output**: il contesto tiene un **puntatore da 30 token**; **150 token** vengono letti quando servono.
- Contesto prima: sistema + compito + **piano 2000** + chat; dopo: sistema + compito + **30** + chat. **Su 10 turni: da 20 000 a 450 token.**

![](imgs/Pasted%20image%2020261124044910.png)

> 💡 **Il conto (nota didattica).** Senza offloading: $2000\cdot 10=20\,000$ token del piano reinviati. Con offloading: $30\cdot 10=300$ token di puntatore più una lettura di 150 token, totale 450, una riduzione di circa 44 volte.

**Offloading con i documenti: dentro il prompt o fuori?**

![](imgs/Pasted%20image%2020261124044921.png)

| **A. Documenti dentro il prompt** | **B. Documenti fuori, riassunti nel prompt** |
|---|---|
| 5 documenti da 4000 token ciascuno nel contesto | i documenti stanno in file e archivi di memoria, **fuori dal prompt**, letti quando servono |
| contesto: $5\times 4000=$ **20 000 token** | nel prompt **5 brevi riassunti** («report.pdf: vendite T3…», «contract.pdf: scadenze…», «manual.pdf: passi di installazione…», «log.txt: ultimi errori…», «data.csv: 12 colonne…»): $5\times 30=$ **150 token** |

##### **1.7.2. Gestione 2: RAG**

**Recuperare la conoscenza esterna rilevante al momento dell’inferenza** invece di mettere tutti i documenti nel prompt.

**Esempio**: un **manuale di 200 pagine** (circa **100 000 token**) viene **indicizzato una volta** (offline) in chunk da circa 500 token; per una domanda dell’utente l’applicazione (*harness*, l’agente) recupera i **top-3 chunk**; il prompt contiene **domanda + 3 chunk = 1500 token**, e l’LLM dà una risposta **ancorata al manuale**. **Contesto: da 100 000 a 1500 token** (Lezione 15, §4).

![](imgs/Pasted%20image%2020261124044931.png)

##### **1.7.3. Gestione 3: selezione dinamica degli strumenti**

**Esporre solo gli strumenti rilevanti per il compito corrente.**

**Esempio**: il compito è «**rinomina `calc_total` in `compute_total` ovunque, poi esegui i test**»: servono **4 strumenti su 40**, non il calendario né l’e-mail.

- **Input**: il compito + **40 definizioni di strumenti** (200 token ciascuna).
- Un **selettore di strumenti** sceglie per compito: `read_file` ✓, `search_code` ✓, `edit_file` ✓, `run_tests` ✓; `send_email`, `calendar`, `web_search`, `query_database`, `create_image`, `book_travel` ✗.
- **Output**: 4 strumenti nel contesto. **Contesto: da 8000 a 800 token.**

![](imgs/Pasted%20image%2020261124143700.png)

> 💡 **Non solo token (nota didattica).** Un agente che vede 40 strumenti deve anche scegliere tra 40 possibilità a ogni passo, e può sbagliare strumento; uno che non vede `send_email` non può inviare e-mail per errore o per una prompt injection. La selezione degli strumenti riduce il costo **e** applica il principio del privilegio minimo (Lezione 15, §3.12).

##### **1.7.4. Gestione 4: pruning**

**Rimuovere il contesto irrilevante, obsoleto, duplicato o di poco valore** prima della prossima chiamata al modello.

**Esempio**: un **tentativo fallito** viene eliminato una volta annotata la sua lezione («usa `--no-cache`»).

- **Input**: una cronologia di **3000 token** con un tentativo fallito e output duplicati degli strumenti.
- **Output**: una cronologia pulita di **1200 token** + una **lezione di una riga**.

![](imgs/Pasted%20image%2020261124143710.png)

##### **1.7.5. Gestione 5: riassunto**

**Comprimere la conversazione e i risultati degli strumenti più vecchi in un riassunto.**

**Esempio**: **60 turni da 30 000 token** diventano un **riassunto di 1500 token**; **decisioni, vincoli, numeri ed evidenze sono conservati alla lettera**. Il riassuntore è una chiamata all’LLM; gli ultimi turni restano **intatti**.

> ⚠️ **Rischio**: un riassunto che **perde decisioni, vincoli, numeri o evidenze**. È il motivo per cui le istruzioni di compattazione vanno date in modo mirato (§1.8).

![](imgs/Pasted%20image%2020261124143733.png)

##### **1.7.6. Gestione 6: quarantena**

**Isolare i sottocompiti complessi in thread o sotto-agenti separati**; torna indietro **solo il risultato finale**.

**Esempio**: **cinque sotto-agenti** leggono ciascuno **un articolo** (20 000 token rumorosi); l’agente principale riceve **cinque risultati da 100 token**.

- **Input**: il compito «**confronta questi 5 articoli**».
- Contesto prima: i 5 articoli letti nel contesto principale, **100 000 token**; dopo: sistema + compito + 5 risultati, **500 token**. La lettura rumorosa resta nei **cinque contesti dei sotto-agenti** (20 000 token ciascuno, poi scartati).

![](imgs/Pasted%20image%2020261124143754.png)

#### **1.8. Gestire il contesto durante un flusso agentico**

- **Monitorare la crescita**: chat lunghe, file grandi, output ripetuti e risultati degli strumenti **consumano la finestra**.
- **Ridurre**: eliminare i file irrilevanti, **riassumere il lavoro finito**, tenere **solo le decisioni correnti**.
- **RAG o memoria di progetto** per la conoscenza stabile, invece di incollarla di nuovo.
- **Compattare o ricominciare** quando le ipotesi sono superate o i rami sono abbandonati.

<!-- SOURCE_VISUAL id="IS17-V07" source="L17-web.pdf" page="22" type="figura" description="Gestione del contesto durante un flusso agentico: monitorare, ridurre, RAG, compattare o ricominciare" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124143806.png)

> ⚠️ **Segnale di rischio**: l’agente **dimentica i vincoli**, **mescola decisioni vecchie** o **legge le evidenze sbagliate**.

> 💡 **Pratiche operative (nota didattica, dall’edizione precedente).** Chiedere un **riassunto di passaggio** (*handoff*: obiettivi, decisioni, file toccati, questioni aperte, test, prossimi passi) e aprire una nuova conversazione da quel riassunto quando la precedente diventa troppo lunga; in strumenti come Claude Code usare `/compact` quando il contesto diventa grande e `/clear` quando si cambia compito; dare istruzioni di compattazione **mirate** (conservare modifiche al codice, risultati dei test, decisioni di progetto e rischi irrisolti); usare **sotto-agenti** per i sottocompiti delimitati.

---

### **2. Strumenti: gli errori di classificazione in Python**


#### **2.1. Testare l’albero di decisione Wine**

Si studiano la **complessità** di un modello e la sua **accuratezza**, tornando all’esempio del vino (Lezione 12).

```python
wine_cultivars = ['Barolo', 'Grignolino', 'Barbera']

clf_shallow = DecisionTreeClassifier(max_depth=3,
                                     random_state=0)
clf_shallow.fit(X, y)          # training: 178 vini
y_pred = clf_shallow.predict(X)
```

- **Input** $X$: $178\times 13$ misure chimiche; $y$: 178 etichette in $\{0,1,2\}$.
- **Output**: un albero di **profondità 3** e $\hat{y}$ (178 previsioni). **Profondità 3 = al massimo tre domande in fila** prima della risposta.

> ⚠️ **I nomi delle classi.** Il file UCI numera i tre cultivar da 1 a 3; i nomi sono le **etichette usate in questo corso**. Come ricordato nella Lezione 12 (§4.9), il modello prevede una classe del dataset, non certifica un vino di marca.

**L’albero poco profondo non è un learner perfetto…** Le foglie contornate in arancione **mescolano ancora i cultivar**: per esempio una foglia con **6 campioni, 5 Grignolino e 1 Barbera**.

<!-- SOURCE_VISUAL id="IS17-V08" source="L17-web.pdf" page="25" type="schema" description="Albero Wine di profondità 3 con le foglie impure contornate in arancione" reason="Nodi e foglie visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124144055.png)

#### **2.2. Controllare l’accuratezza**

```python
from sklearn.metrics import confusion_matrix
from sklearn.metrics import ConfusionMatrixDisplay

cm = confusion_matrix(y, y_pred)
disp = ConfusionMatrixDisplay(cm,
                              display_labels=wine_cultivars)
disp.plot(cmap='Blues')
```

**Righe**: cultivar vero. **Colonne**: cultivar previsto. La **diagonale** conta i vini corretti: $57+70+47=174$ su 178.

![](imgs/Pasted%20image%2020261124144124.png)


<!-- SOURCE_VISUAL id="IS17-V09" source="L17-web.pdf" page="26" type="grafico" description="Matrice di confusione dell'albero di profondità 3 sul training Wine" reason="Valori della matrice visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V10" source="L17-web.pdf" page="27" type="grafico" description="Matrice di confusione dell'albero di profondità 3 (dettaglio)" reason="Valori della matrice visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124144142.png)

#### **2.3. Calcolare l’accuratezza**

```python
accuracy = (y == y_pred).mean()          # frazione di previsioni corrette
print('Accuracy on training data with depth=3:',
      round(accuracy * 100, 2), '%')
# Accuracy on training data with depth=3: 97.75 %
```

$$\text{accuratezza}=\frac1N\sum_{i=1}^{N}\big[y_i=\hat{y}_i\big],$$

con $y_i$ cultivar vero del vino $i$, $\hat{y}_i$ cultivar previsto, $[\cdot]$ che vale 1 se la condizione è vera e 0 altrimenti, $N$ numero di vini testati. **Input**: $y$ e $\hat{y}$, 178 etichette ciascuno. **Output**: l’accuratezza, una frazione in $[0,1]$ (senza unità; mostrata in %). **Esempio**: $N=178$, 174 corretti: $174/178=0{,}9775=97{,}75\%$.

> 💡 **Perché `(y == y_pred).mean()` (nota didattica).** Il confronto elemento per elemento produce un vettore di booleani (`True` = corretto); la media di booleani tratta `True` come 1 e `False` come 0, ed è quindi la frazione di corretti. È la formula con la parentesi di Iverson $[\cdot]$ tradotta in una riga.

#### **2.4. Aggiungere livelli: il learner perfetto**

```python
clf_deep = DecisionTreeClassifier(max_depth=5, random_state=0)
clf_deep.fit(X, y)          # più livelli: più foglie
```

<!-- SOURCE_VISUAL id="IS17-V11" source="L17-web.pdf" page="29" type="schema" description="Albero Wine di profondità 5" reason="Nodi e foglie visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124144230.png)

**Abbiamo creato un learner perfetto**: **ogni cella fuori diagonale è zero**, nessun errore di training.

<!-- SOURCE_VISUAL id="IS17-V12" source="L17-web.pdf" page="30" type="grafico" description="Matrice di confusione dell'albero di profondità 5: tutti i vini corretti sul training" reason="Valori della matrice visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124170230.png)

```python
accuracy = (y == clf_deep.predict(X)).mean()
print('Accuracy on training data with depth=5:',
      round(accuracy * 100, 2), '%')
if accuracy == 1.0:
    print('We created a perfect learner!')
# Accuracy on training data with depth=5: 100.0 %
# We created a perfect learner!
```

**Perfetto sui dati di training. È perfetto anche su vini nuovi?**

#### **2.5. È una buona idea creare un learner perfetto?**

- **Errore di training zero $\neq$ errore di generalizzazione zero.**
- **Memorizzazione e cattura del rumore** (overfitting):
  - i modelli ad **alta capacità** memorizzano **regolarità spurie** e **rumore di misura**, rendendo le previsioni **fragili** quando la distribuzione si sposta (nuove annate, deriva dei sensori, ecc.);
  - **l’overfitting va fortemente sospettato ogni volta che l’errore di training è zero.**

**Rischi pratici**:

- **previsioni instabili**: piccole perturbazioni delle feature → foglie o percorsi diversi;
- **scarsa manutenibilità**: il modello va riaddestrato spesso, perché il drift dei dati rompe le regole memorizzate;
- **stakeholder fuorviati**: una metrica apparente del **100%** nasconde l’errore reale.

#### **2.6. Si può testare la generalizzazione con la validazione incrociata!**

```python
from sklearn.model_selection import cross_val_score

train_acc = clf_deep.score(X, y)
cv_scores = cross_val_score(clf_deep, X, y,
                            cv=10)
cv_mean = cv_scores.mean()
```

![](imgs/Pasted%20image%2020261124171648.png)

**Input**: $X$ ($178\times 13$), $y$ (178 etichette). **Output**: **10 accuratezze**, una per fold di test (circa 18 vini ciascuno), e la loro **media**.

<!-- SOURCE_VISUAL id="IS17-V13" source="L17-web.pdf" page="34" type="grafico" description="Accuratezza per fold della 10-fold CV dell'albero di profondità 5: non è così perfetto" reason="Barre per fold visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124171659.png)

```text
Training accuracy       : 1.0
Cross-validation scores : [0.889 0.889 0.722 0.889 0.833 0.833 1.    0.944 0.941 0.765]
Mean CV accuracy        : 0.871
```

> 📌 **Il divario di generalizzazione.** **Perfetto sul training set, tra 0,722 e 1,0 sui fold mai visti**: è il ***generalization gap***.

![](imgs/Pasted%20image%2020261124171720.png)

> 💡 **I conti e una sottigliezza (nota didattica).** La somma dei 10 punteggi è 8,705, quindi la media è 0,871: il 12,9% di errore contro lo 0% sul training. `cross_val_score` riaddestra un albero nuovo in ogni fold, con la stessa configurazione (Lezione 14, §6.2): misura la **procedura** «albero di profondità 5», non il singolo albero addestrato su tutti i dati. Con 178 vini e 10 fold, alcuni fold hanno 18 vini e altri 17, da cui valori come 0,941 ($16/17$) e 0,765 ($13/17$). Per le classi sbilanciate (59/71/48) scikit-learn usa di default fold stratificati per i classificatori.

---

### **3. Teoria: il compromesso (dilemma) bias/varianza**


**Una cosa da sapere per tutta la vita come esperti di AI!**

#### **3.1. Troppo semplice, giusto, troppo complesso**

**Input** $x$ (1 feature, **7 punti di training**) → modello di complessità crescente → **output** $\hat{y}$ (la curva). Polinomi di **grado 1, 2 e 6** sugli stessi punti (errore sui dati nuovi misurato su **2000 punti freschi**).

<!-- SOURCE_VISUAL id="IS17-V14" source="L17-web.pdf" page="37" type="grafico" description="Polinomi di grado 1, 2 e 6 sugli stessi 7 punti, con l'errore su 2000 punti nuovi" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124171741.png)

> 📌 **La curva complessa tocca ogni punto di training, eppure è peggiore sui dati nuovi**: il compromesso bias-varianza.

> 💡 **Perché il grado 6 tocca tutti i 7 punti (nota didattica).** Un polinomio di grado 6 ha 7 coefficienti: con 7 punti il sistema ha una soluzione esatta che passa per tutti, qualunque sia il rumore. Sono tanti gradi di libertà quanti dati (Lezione 7): il modello memorizza il rumore e oscilla tra i punti.

#### **3.2. Come cambiano media e deviazione standard dell’errore di CV con la profondità?**

**Esperimento**: profondità dell’albero da **1 a 10**, **10-fold CV**.

- L’**accuratezza di training sale in modo monotono**.
- L’**accuratezza di validazione raggiunge un picco** e poi **si appiattisce o cala** → la classica **forma a U** (dell’errore).
- **Dalla profondità 5 in poi** l’albero **memorizza** (bias ≈ 0) ma **non guadagna generalizzazione**.

<!-- SOURCE_VISUAL id="IS17-V15" source="L17-web.pdf" page="38" type="grafico" description="Accuratezza di training e di validazione al variare della profondità da 1 a 10 con 10-fold CV" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124171757.png)

**Illustrazione bias-varianza** (dataset Wine, $n=178$), con i dati dietro il grafico.

![](imgs/Pasted%20image%2020261124171807.png)


<!-- SOURCE_VISUAL id="IS17-V16" source="L17-web.pdf" page="39" type="grafico" description="Bias e varianza al variare della profondità sul dataset Wine, con la tabella dei dati" reason="Grafico e tabella visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

<!-- SOURCE_VISUAL id="IS17-V17" source="L17-web.pdf" page="40" type="grafico" description="Il dilemma bias-varianza: errore totale, bias e varianza in funzione della complessità" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124171816.png)

#### **3.3. Il dilemma bias-varianza (regressore)**

La slide usa l’esempio di una **rotta di volo** da seguire:

| **BIAS** (underfitting) | **VARIANZA** (overfitting) |
|---|---|
| la rotta reale è **troppo liscia** | la rotta reale segue la **tendenza media** |
| **non cattura l’ampiezza** richiesta | **cattura l’ampiezza** |
| l’errore è **grande e sistematico** | l’errore **cambia molto** e **non è stabile** |
| **costantemente sotto** la rotta; **molto stabile** | **oscilla sopra e sotto** la rotta; **alta sensibilità** |

<!-- SOURCE_VISUAL id="IS17-V18" source="L17-web.pdf" page="41" type="grafico" description="Esempio della rotta di volo: modello con alto bias troppo liscio e modello con alta varianza oscillante" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124171826.png)

> 📌 **Obiettivo: bias basso e varianza bassa** per un volo **sicuro e accurato**.

#### **3.4. Il compromesso bias-varianza: la teoria**

**Scomposizione dell’errore di previsione**:

$$\underbrace{\mathbb{E}\big[(y-\hat{y})^2\big]}_{\text{errore quadratico atteso}}=\underbrace{\text{Bias}[\hat{y}]^2}_{\text{errore sistematico}}+\underbrace{\text{Var}[\hat{y}]}_{\text{sensibilità ai dati}}+\underbrace{\sigma^2_{\text{rumore}}}_{\text{irriducibile}}.$$

- $\mathbb{E}[\cdot]$ è il **valore atteso**: la media che si otterrebbe **ripetendo l’esperimento molte volte**.
- **Bias alto → underfitting**: il modello è **troppo semplice**, quindi la sua **previsione media resta lontana** dalla funzione vera.
- **Varianza alta → overfitting**: il modello è **troppo complesso**, quindi le sue previsioni **oscillano molto da un dataset all’altro**; il punto giusto **bilancia i due** per minimizzare l’errore atteso.

> ⚠️ **Una precisione sulla notazione.** La slide scrive «$\text{Bias}[\hat{y}]^2$» come termine della somma: è il **quadrato** del bias, $\big(\mathbb{E}[\hat{y}]-f(x)\big)^2$, non il bias stesso. La dimostrazione è nel §9.

**Visualizzazione del compromesso.** Sull’asse orizzontale **non c’è il tempo né le epoche: c’è la complessità!**

<!-- SOURCE_VISUAL id="IS17-V19" source="L17-web.pdf" page="43" type="grafico" description="Errore di training e di test in funzione della complessità del modello" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261124172947.png)

---

### **4. Strumenti: feature engineering + classificazione in MATLAB**


#### **4.1. Scegliere una direzione: PCA o LDA?**

- **PCA** (senza etichette): **input** $X$, **output** gli **assi di varianza massima**.
- **LDA** (con etichette): **input** $X$ **e** $y$, **output** l’**asse che separa meglio le classi**.

<!-- SOURCE_VISUAL id="IS17-V20" source="L17-web.pdf" page="45" type="grafico" description="La stessa nuvola di punti con la direzione PCA di massima varianza e la direzione LDA che separa le classi" reason="Direzioni visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004107.png)

> 💡 **Perché possono essere diverse (nota didattica).** Due classi allungate e parallele hanno la massima varianza lungo la loro lunghezza: la PCA sceglie quella direzione, lungo la quale le due classi si sovrappongono completamente. La LDA, che conosce le etichette, sceglie la direzione perpendicolare, lungo la quale le classi sono separate. La varianza non è sinonimo di informazione utile per classificare.

#### **4.2. Richiamo: feature engineering con le etichette**

- **Feature engineering**: feature migliori, su **tutto il dataset in una volta** (il livello (C) della Lezione 10).
- **Usando le etichette $y$** nell’analisi si può **migliorare ulteriormente** la feature engineering: la **PCA usa solo $X$** (non supervisionata), la **LDA usa $X$ e $y$** (supervisionata).
- **La LDA di Fisher (1936)**: la **direzione che separa meglio le classi**. In MATLAB: `fitcdiscr`, `resubPredict`.

![](imgs/Pasted%20image%2020261125004120.png)

#### **4.3. Da 4 misure a una specie: un classificatore lineare**

Oltre a dare una **rotazione migliore degli assi**, la LDA aiuta anche a **costruire un classificatore**.

- **Addestramento**: feature $X$ ($N\times 4$, cm) e etichette $y$ ($N$) → addestramento LDA → **modello**: $w$ (4 numeri) e una soglia $t$.
- **Uso**: un **fiore nuovo**, solo le feature $x=(x_1,x_2,x_3,x_4)^\top$ → modello LDA $(w,t)$ → **classe** $\in\{0,1,2\}$.

![](imgs/Pasted%20image%2020261125004137.png)

$x_1,\dots,x_4$: lunghezza del sepalo, larghezza del sepalo, lunghezza del petalo, larghezza del petalo. **Supervisionato**: le etichette $y$ si usano **solo nell’addestramento**.

#### **4.4. Che cosa fa la LDA: ruota la nuvola etichettata**

$$z=W^\top x,$$

con $W$ matrice di rotazione $2\times 2$ le cui colonne sono **LDA1** e **LDA2**, e $z_1=w^\top x$. **Input**: $N=240$ punti (120 blu, 120 rossi) con 2 feature $x_1,x_2$ e **classi sovrapposte**. **Output**: gli **stessi 240 punti** nei nuovi assi $z_1,z_2$: le **classi si separano lungo $z_1$**.

<!-- SOURCE_VISUAL id="IS17-V21" source="L17-web.pdf" page="48" type="grafico" description="240 punti di due classi sovrapposte nelle feature originali e separati lungo z1 dopo la rotazione LDA" reason="Nuvole di punti visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004150.png)

#### **4.5. LDA passo per passo: matrici, proiezione, soglia**

Con $X$ ($N\times d$) e $y$ ($N$ etichette), due classi:

1. **medie di classe**: $\mu_k=\dfrac{1}{N_k}\sum_{i\in k}x_i$ ($\mu_0,\mu_1$: $d$ numeri ciascuna);
2. **dispersione intra-classe**: $S_W=\sum_k\sum_{i\in k}(x_i-\mu_k)(x_i-\mu_k)^\top$ (matrice $d\times d$);
3. **direzione di Fisher**: $w=S_W^{-1}(\mu_1-\mu_0)$ (vettore di $d$ numeri);
4. **proiezione**: $z=w^\top x$ (prodotto scalare: da $d$ numeri a 1);
5. **soglia**: $z>t\Rightarrow$ classe 1, con $t=w^\top\dfrac{\mu_0+\mu_1}{2}$.

![](imgs/Pasted%20image%2020261125004202.png)

> 💡 **Perché quella direzione (nota didattica).** Fisher cerca la direzione $w$ che massimizza il rapporto tra la distanza delle medie proiettate e la dispersione delle classi proiettate:
> $$J(w)=\frac{\big(w^\top(\mu_1-\mu_0)\big)^2}{w^\top S_W\,w}.$$
> Il massimo si ottiene proprio per $w\propto S_W^{-1}(\mu_1-\mu_0)$: si allontanano le medie, ma tenendo conto di quanto ogni classe è sparsa in ciascuna direzione. La soglia a metà tra le medie proiettate è la scelta naturale con classi di uguale numerosità e dispersione. Con $K$ classi si ottengono al più $K-1$ direzioni discriminanti (2 per Iris).

#### **4.6. Classificazione lineare in Python (1/4): guardare i dati**

```python
from sklearn.datasets import load_iris

iris = load_iris()
P, T = iris.data, iris.target
print(P.shape, np.bincount(T))
# (150, 4) [50 50 50]
```

**Input**: $P$, 150 fiori × 4 feature (cm); $T$: 150 etichette (0 setosa, 1 versicolor, 2 virginica). **EDA minima**: **lunghezza e larghezza del petalo separano già la setosa**.

<!-- SOURCE_VISUAL id="IS17-V22" source="L17-web.pdf" page="50" type="grafico" description="Lunghezza e larghezza del petalo di Iris: la setosa è già separata" reason="Punti colorati visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004215.png)

`np.bincount(T)` conta le occorrenze di ogni etichetta: 50 per specie, un dataset bilanciato.

#### **4.7. Classificazione lineare in Python (2/4): training e validazione**

```python
from sklearn.model_selection import train_test_split
from sklearn.discriminant_analysis import (
    LinearDiscriminantAnalysis)

P_tr, P_va, T_tr, T_va = train_test_split(
    P, T, test_size=0.3, stratify=T,
    random_state=42)

lda = LinearDiscriminantAnalysis()
lda.fit(P_tr, T_tr)               # impara SOLO sul TRAINING
T_pred = lda.predict(P_va)        # testa sulla VALIDAZIONE
```

**Input**: $P$ ($150\times 4$ cm), $T$ (150 etichette). **Output**: **105 fiori di training** (35 per classe) e **45 di validazione** (15 per classe); `T_pred`: 45 etichette previste. La versione MATLAB (`resubPredict`) testava sui dati di training; **qui i fiori di validazione non sono mai stati visti**. È solo un esempio: l’argomento sarà presentato più avanti nel corso.

#### **4.8. Classificazione lineare in Python (4/4): matrici di confusione**

**Righe**: specie vera. **Colonne**: specie prevista. La matrice di training mostra **3 errori su 105**; quella di validazione **1 errore su 45**.

<!-- SOURCE_VISUAL id="IS17-V23" source="L17-web.pdf" page="52" type="grafico" description="Matrici di confusione della LDA su Iris: training (3 errori su 105) e validazione (1 errore su 45)" reason="Valori delle matrici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004232.png)

> 💡 **La validazione migliore del training (nota didattica).** Accuratezza di training $102/105=97{,}1\%$, di validazione $44/45=97{,}8\%$: la validazione risulta leggermente migliore, il contrario di ciò che ci si aspetta. Con soli 45 fiori un errore in più o in meno sposta l’accuratezza di 2,2 punti: la differenza è dentro l’incertezza (Lezione 14, §4.2), e non indica un problema. La numerazione delle slide passa da (2/4) a (4/4): la parte (3/4) non è presente nel PDF.

---

### **5. Teoria: un modello nuovo? Che cosa sapere per un avvio corretto**


#### **5.1. Che cosa sapere davanti a un modello nuovo**

Ogni giorno saranno disponibili **nuovi modelli o tecniche di apprendimento**. Come affrontare questa sfida e **tenere il passo**? «*Usiamolo, non serve leggere la documentazione!*» è l’atteggiamento da evitare.

**Consultare documentazione ed esempi prima di sprecare tempo**:

- capire **l’argomento, il tipo, il contesto**;
- controllare **in quali librerie** si usa il modello;
- è **generale o specifico**?
- qual è la **complessità computazionale**: in **addestramento**, in **test/rilascio**; serve **CUDA** (cioè una GPU NVIDIA)?
- quali **figure di merito** dell’accuratezza si possono usare? Recall? ROC? Quali **casi di valutazione**?

<!-- SOURCE_VISUAL id="IS17-V24" source="L17-web.pdf" page="54" type="figura" description="Illustrazione del motto: usiamolo, non serve leggere la documentazione" reason="Illustrazione visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004319.png)

**(continua)**:

- **memoria necessaria**: in addestramento, in test;
- **dimensione degli esempi e del dataset**: 1000 campioni o 10 milioni?
- **parametri**: complessi da capire? **Sensibili alla regolazione**?
- si può fare **solo un fine-tuning** o bisogna **riaddestrare**?
- **portabilità** su librerie e ambienti diversi: è **aperto / GNU / libero**, o lo gestisce **una sola azienda**?

Una **scheda piccola** ha poca memoria e modelli piccoli; un **server GPU** ha molta memoria e addestramenti grandi.

#### **5.2. Che cosa fare davanti a un modello nuovo**

- **Provare demo ed esempi** della documentazione prima di sprecare tempo, verificando se è adatto o no alle proprie applicazioni.
- **Provare i nuovi modelli su un insieme di dataset standard** e confrontare **a) l’accuratezza** e **b) i tempi di calcolo**: **non solo il dataset Iris**.
- **Ricordare il rasoio di Occam!** Stesse prestazioni ma più complessità: **ne vale la pena?**

<!-- SOURCE_VISUAL id="IS17-V25" source="L17-web.pdf" page="57" type="figura" description="Applicazione di un nuovo classificatore su dataset standard" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004351.png)

> ✅ **Checklist per un modello nuovo (nota didattica).** 1) Che cosa fa e su quali dati? 2) Quali librerie, quale licenza, quale hardware? 3) Costo in addestramento e in inferenza (tempo, memoria)? 4) Quanti dati servono? 5) Quali iperparametri contano? 6) Fine-tuning o riaddestramento? 7) Quali metriche e quali casi di test? 8) Funziona sui dataset standard e batte una baseline semplice? Se la risposta all’ultima domanda è no, il rasoio di Occam ha già deciso.

---

### **6. Teoria: un semplice esempio del flusso completo — il regressore lineare**


#### **6.1. La regressione lineare**

Si vuole la **migliore funzione lineare che spiega i dati**.

**Forma classica**: $y=mx+q$, con $x$ input, $y$ output, $m$ **pendenza** (quanto cresce $y$ quando $x$ cresce di 1) e $q$ **intercetta** (il valore di $y$ in $x=0$). **Esempio**: $m=2$, $q=-5$, $x=3$: $y=1$.

Qui si preferisce

$$z=[w_1]\,[x_1]+b,$$

cioè $z=w^\top x+b$ con una sola feature: $w_1\leftrightarrow m$, $b\leftrightarrow q$, $z\leftrightarrow y$.

<!-- SOURCE_VISUAL id="IS17-V26" source="L17-web.pdf" page="59" type="grafico" description="Retta di regressione y = mx + q tra i punti" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004402.png)

**Esempi di regressione lineare** (il modello è $z=w^\top x+b$):

- **età, sesso, dieta** (3 input $x_1,x_2,x_3$) → **altezza del bambino** (1 output $z$);
- **tipo e voti dell’università** (2 input) → **reddito annuo** (1 output);
- **ore di studio** (1 input $x_1$) → **voto finale di questo esame** (1 output $z$).

**Il caso del grafico**: 1 input, 1 output, **51 punti** $(x_i,t_i)$, con $t_i\approx 2x_i-5$ più rumore. Retta interpolata: $w\approx 2$, $b\approx -5$. **Nuovo input** $x_1=2$: $z=2\cdot 2-5=-1$.

<!-- SOURCE_VISUAL id="IS17-V27" source="L17-web.pdf" page="60" type="grafico" description="51 punti rumorosi attorno a t = 2x - 5 con la retta interpolata" reason="Punti e retta visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004644.png)

#### **6.2. La notazione con 3 e con $N$ feature di ingresso**

**3 input, 1 output**:

$$z=w_1x_1+w_2x_2+w_3x_3+b=[w_1\ w_2\ w_3]\begin{bmatrix}x_1\\x_2\\x_3\end{bmatrix}+b=w^\top x+b.$$

$x_1,x_2,x_3$: le 3 feature (input); $w_1,w_2,w_3$: **un peso per feature**; $b$: **bias**; $z$: l’uscita. **Esempio**: $w=(2,-1,0{,}5)$, $b=1$, $x=(1,3,4)$: $z=2-3+2+1=2$. Per **descrivere completamente** il modello servono **4 parametri**: $w_1,w_2,w_3,b$ → **servono 4 punti** (ma se ne useranno molti di più!).

![](imgs/Pasted%20image%2020261125004707.png)

**$N$ input, 1 output**:

$$z=w_1x_1+\dots+w_Nx_N+b=\sum_{i=1}^{N}w_ix_i+b=w^\top x+b.$$

$N$: numero di feature; $x_i$: la feature $i$; $w_i$: il suo peso; $b$: bias; $z$: l’uscita. **Esempio**, $N=2$: $w=(1,1)$, $b=-7$, $x=(5,5)$: $z=5+5-7=3$. Per fissare il modello servono **$N+1$ parametri** → **almeno $N+1$ punti** (ma se ne useranno molti di più!).

![](imgs/Pasted%20image%2020261125004731.png)

> 💡 **Perché $N+1$ punti e perché «molti di più» (nota didattica).** Con $N+1$ incognite servono almeno $N+1$ equazioni indipendenti: con esattamente $N+1$ punti l’iperpiano passa per tutti, rumore compreso (lo stesso problema del polinomio di grado 6 del §3.1). Con molti più punti i minimi quadrati fanno una **media** del rumore, e la stima dei parametri diventa stabile: è il bilancio tra gradi di libertà e dati della Lezione 7.

#### **6.3. Regressore lineare e classificatore lineare**

**Simboli**: $x$ (o $x_1,x_2$) input; $w$ peso, uno per feature; $b$ bias (offset); $z$ uscita lineare; $\text{sgn}$ segno, $+1$ se l’argomento è positivo, $-1$ altrimenti.

- **Regressore** (di nuovo 1 input): $z=wx+b$ con $w=2$, $b=-5$ (la retta interpolata sugli stessi dati). Input $x=2$: $z=2\cdot 2-5=-1$, **l’uscita è il numero $-1$**.
- **Classificatore** (2 input): $z=w_1x_1+w_2x_2+b$ con $w=(1,1)$, $b=-7$. Input $x=(1,1)$: $z=1+1-7=-5$ (prima del sgn), quindi la classe è $\text{sgn}(-5)=-1$. Input $(5,5)$: $z=3$, **classe $+1$**.

<!-- SOURCE_VISUAL id="IS17-V28" source="L17-web.pdf" page="63" type="grafico" description="Regressore lineare come retta e classificatore lineare come retta di separazione x1 + x2 = 7 tra due classi" reason="Grafici visibili solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125004756.png)

> 📌 **Stessa forma, uso diverso.** Regressore e classificatore lineari calcolano la stessa $z=w^\top x+b$: il regressore la restituisce come numero, il classificatore ne guarda solo il segno. La frontiera del classificatore è l’iperpiano $w^\top x+b=0$ (qui la retta $x_1+x_2=7$); è lo stesso schema del perceptron della Lezione 20.

#### **6.4. Regressione lineare: rappresentazione**

Il valore previsto di $y$ è

$$\hat{y}=\hat{\beta}_0+\sum_{j=1}^{p}X_j\hat{\beta}_j,$$

con $p$ numero di feature $(X_1,\dots,X_p)$. **Il vettore dei coefficienti $\hat{\beta}$ è il modello di regressione.**

**Una nota sul calcolo**: aggiungendo agli input un termine $X_0=1$, la formula diventa un **semplice prodotto matriciale** (notazione compatta): $\hat{y}=X\hat{\beta}$. L’algebra della soluzione è più facile da trattare.

> 💡 **Il trucco della colonna di uni (nota didattica).** Con $X_0=1$ si ha $\hat{\beta}_0X_0=\hat{\beta}_0$: il bias diventa un peso come gli altri, moltiplicato per una feature che vale sempre 1. Così $w$ e $b$ della notazione precedente si uniscono nel solo vettore $\beta=(b,w_1,\dots,w_p)$.

#### **6.5. La somma dei quadrati dei residui (RSS): ottimizzazione**

Per determinare i parametri $\hat{\beta}$ (i pesi) da alcuni dati si **minimizza la somma dei quadrati dei residui**:

$$\text{RSS}(\beta)=\sum_{i=1}^{N}\big(y_i-x_i^\top\beta\big)^2=(y-X\beta)^\top(y-X\beta),\qquad \beta=\begin{bmatrix}\beta_0\\\beta_1\\\vdots\\\beta_p\end{bmatrix}.$$

$N$ è il numero di campioni (osservazioni distinte); $X$ è la matrice $N\times(p+1)$ dei campioni, **una riga $x_i^\top$ per campione** (prima colonna di uni per il bias); $y$ è il vettore $N\times 1$ delle **uscite misurate** (numeri, non classi). Quindi $y_i$ è l’uscita misurata del campione $i$, e $x_i^\top\beta$ è l’uscita del modello per lo stesso campione (riga $i$ di $X$ per $\beta$). **Unità di RSS**: unità di $y$ al quadrato.

**Quale $\hat{\beta}$ rende RSS più piccola?** La risposta passa per il **gradiente** (d’esame); la forma chiusa esatta è nel §6.8.

#### **6.6. Gradiente, gradiente, gradiente! (d’esame)**

- La maggior parte del ML consiste nel **trovare i parametri / pesi** $\beta$.
- $\beta$ si trova attraverso il **gradiente dell’errore**:

$$\nabla=\frac{\partial\big(\frac12\text{RSS}\big)}{\partial\beta}=X^\top(X\beta-y).$$

Applicando la **discesa del gradiente**, il gradiente al passo $t$,

$$\nabla^{(t)}=X^\top\big(X\beta^{(t)}-y\big),$$

si usa per calcolare i **nuovi parametri**:

$$\beta^{(t+1)}=\beta^{(t)}-\eta\cdot\nabla^{(t)}.$$

$\eta>0$ è il **learning rate** (passo), un numero piccolo scelto dall’utente, per esempio 0,1 o 0,01. **Troppo grande**: le iterazioni **divergono**. **Troppo piccolo**: la convergenza è **lenta**. Il ciclo è: gradiente → aggiornamento → gradiente successivo → … finché il gradiente è **vicino a 0**.

> 💡 **Da dove viene il gradiente (nota didattica).** Sviluppando $\text{RSS}=(y-X\beta)^\top(y-X\beta)=y^\top y-2\beta^\top X^\top y+\beta^\top X^\top X\beta$ e derivando rispetto a $\beta$ si ottiene $\partial\,\text{RSS}/\partial\beta=-2X^\top y+2X^\top X\beta=2X^\top(X\beta-y)$. Il fattore $\frac12$ davanti a RSS serve solo a cancellare il 2: non cambia il punto di minimo, cambia solo la scala di $\eta$. Nella slide la parentesi di $X^\top(X\beta^{(t)}-y)$ è stampata in modo da sembrare $X^\top X\beta^{(t)}-y$: la forma corretta è quella con la parentesi, come nella formula precedente.

#### **6.7. Il gradiente dipende da… (esempio svolto)**

Il gradiente al passo $t$ dipende dai **dati** $(X,y)$ e dai **parametri correnti** $\beta^{(t)}$, costruiti dagli aggiornamenti precedenti: è il **percorso dell’ottimizzazione**.

**Input**: $X=\begin{bmatrix}1&1\\1&2\end{bmatrix}$ (2 campioni: colonna del bias + 1 feature), $y=\begin{bmatrix}3\\5\end{bmatrix}$ (2 target), $\beta^{(0)}=\begin{bmatrix}0\\0\end{bmatrix}$ (partenza), $\eta=0{,}1$. **Output**: $\beta^{(2)}$ ($2\times 1$) dopo due passi.

$$\nabla^{(0)}=X^\top(X\beta^{(0)}-y)=\begin{bmatrix}-8\\-13\end{bmatrix},\qquad \beta^{(1)}=\beta^{(0)}-0{,}1\,\nabla^{(0)}=\begin{bmatrix}0{,}8\\1{,}3\end{bmatrix},$$

$$X\beta^{(1)}-y=\begin{bmatrix}-0{,}9\\-1{,}6\end{bmatrix},\qquad \nabla^{(1)}=\begin{bmatrix}-2{,}5\\-4{,}1\end{bmatrix},\qquad \beta^{(2)}=\begin{bmatrix}1{,}05\\1{,}71\end{bmatrix}.$$

La **soluzione esatta** è $\hat{\beta}=[1,2]^\top$ (la retta $y=1+2x$ passa per entrambi i punti): **le iterazioni si avvicinano** ad essa, e **il gradiente si riduce**.

> 💡 **I passaggi (nota didattica).** Passo 0: $X\beta^{(0)}-y=(-3,-5)$; $X^\top(-3,-5)^\top=(-3-5,\ -3-10)=(-8,-13)$. Passo 1: $X\beta^{(1)}=(0{,}8+1{,}3,\ 0{,}8+2{,}6)=(2{,}1;\ 3{,}4)$, quindi i residui sono $(-0{,}9;\ -1{,}6)$ e $\nabla^{(1)}=(-0{,}9-1{,}6,\ -0{,}9-3{,}2)=(-2{,}5;\ -4{,}1)$; $\beta^{(2)}=(0{,}8+0{,}25;\ 1{,}3+0{,}41)$. La norma del gradiente passa da circa 15,3 a circa 4,8: si sta scendendo verso il minimo, dove il gradiente vale 0.

#### **6.8. Soluzioni iterative e forma chiusa**

**Due modi per minimizzare RSS($\beta$):**

| **Approssimato: discesa del gradiente** (d’esame) | **Esatto: forma chiusa** (non d’esame) |
|---|---|
| si parte da $\beta^{(0)}$ e si ripete, passo dopo passo: $\beta^{(t+1)}=\beta^{(t)}-\eta\,X^\top(X\beta^{(t)}-y)$ | derivata di RSS posta a zero: $X^\top(y-X\beta)=0$, quindi $\hat{\beta}=(X^\top X)^{-1}X^\top y$ |
| $\beta^{(t)}$: parametri al passo $t$; $\eta$: learning rate; $X^\top(X\beta-y)$: gradiente | **ipotesi**: bisogna invertire $X^\top X$, una matrice $(p+1)\times(p+1)$, quindi deve essere **non singolare**: $\det(X^\top X)\ne 0$ |
| solo **prodotti matrice×vettore**, quindi funziona anche per $X$ **molto grandi**; il risultato è **approssimato** | **limite**: l’inversione richiede **molta memoria e calcolo** con molte feature o molti dati: spesso è troppo grande e non facilmente risolvibile |
| **esempio**: $[0,0]^\top\to[0{,}8;\ 1{,}3]^\top\to[1{,}05;\ 1{,}71]^\top\to\cdots\to[1,2]^\top$ | **esempio** (stessi dati): $X^\top X=\begin{bmatrix}2&3\\3&5\end{bmatrix}$, $\det=1\ne 0$, quindi $\hat{\beta}=(1,2)^\top$ |

Per molti classificatori la decisione **basata sul gradiente** descritta qui si applica allo stesso modo, **anche nelle reti neurali**.

> 💡 **Il conto della forma chiusa (nota didattica).** $(X^\top X)^{-1}=\frac{1}{1}\begin{bmatrix}5&-3\\-3&2\end{bmatrix}$ e $X^\top y=(3+5,\ 3+10)=(8,13)$; quindi $\hat{\beta}=(5\cdot 8-3\cdot 13,\ -3\cdot 8+2\cdot 13)=(1,2)$. Il determinante si annulla quando una feature è combinazione lineare di altre (per esempio una one-hot completa con il bias, Lezione 16, §5.5): allora i coefficienti non sono unici.

#### **6.9. La regressione è buona? $R^2$ non basta**

| | I | II | III | IV |
|---|---:|---:|---:|---:|
| media di $x$ | 9,00 | 9,00 | 9,00 | 9,00 |
| varianza di $x$ | 11,00 | 11,00 | 11,00 | 11,00 |
| media di $t$ | 7,50 | 7,50 | 7,50 | 7,50 |
| varianza di $t$ | 4,13 | 4,13 | 4,12 | 4,12 |
| corr($x$, $t$) | 0,816 | 0,816 | 0,816 | 0,817 |
| retta $z=wx+b$ | $z=3{,}00+0{,}50x$ (tutti e quattro) | | | |
| $R^2$ | 0,67 | 0,67 | 0,67 | 0,67 |

$$R^2=1-\frac{\sum_i(t_i-z_i)^2}{\sum_i(t_i-\bar{t})^2}:$$

la **frazione della varianza totale spiegata dal modello** (quartetto di **Anscombe**, *The American Statistician* 27(1), 1973).

<!-- SOURCE_VISUAL id="IS17-V29" source="L17-web.pdf" page="68" type="grafico" description="I quattro grafici del quartetto di Anscombe con la stessa retta" reason="Forme dei quattro dataset visibili solo nei grafici" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125005038.png)

> 📌 **Stesse statistiche, stessa retta, stesso $R^2=0{,}67$, quattro dataset molto diversi: disegnare sempre i dati!** È lo stesso messaggio della Lezione 10 (§5.3): un riassunto uguale non significa dati uguali.

> 💡 **I quattro casi (nota didattica).** I: andamento lineare con rumore (il caso in cui la retta va bene). II: una curva, che una retta non può descrivere. III: una retta perfetta con un solo outlier che sposta la regressione. IV: tutti i punti sulla stessa ascissa tranne uno, che da solo determina la pendenza. Si noti anche che, con una sola feature, $R^2=r^2$: $0{,}816^2=0{,}67$.

---

### **7. Strumenti: MATLAB e Python — regressore lineare, classificatori lineari (concetto)**


#### **7.1. Lo stesso regressore in due toolbox**

- **Input**: **51 punti** $(x_i,t_i)$: $t=2x-5+$ rumore, $x\in[-3,3]$.
- **Modello**: regressore lineare, addestramento.
- **Output**: retta interpolata $z=wx+b$: $w\approx 2$, $b\approx -5$; **MSE = 0,76**, **$R^2$ = 0,94**.

In MATLAB `train(net1,x,t)`, in Python `LinearRegression().fit`. Si procede: addestrare, leggere i parametri, disegnare il risultato e **misurare l’accuratezza su un test set**.

**Il file MATLAB**: 1) seguire le slide successive; 2) poi lanciare il proprio script. **Partendo dalla regressione si addestreranno e testeranno le prime reti neurali.**

#### **7.2. MATLAB: creare e addestrare un regressore lineare**

Si creano **51 punti** per l’equazione $t=2x-5$ più **rumore gaussiano**.

<!-- SOURCE_VISUAL id="IS17-V30" source="L17-web.pdf" page="73" type="schermata" description="Codice MATLAB che crea 51 punti t = 2x - 5 con rumore gaussiano e il loro grafico" reason="Codice e grafico visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125005107.png)

> 💡 **Il codice di generazione (nota didattica, dallo script `Lesson_16_laboratory_MATLAB_linear_and_neural.m`).** La forma è del tipo `x = -3:0.12:3; t = 2*x - 5 + randn(size(x));`: 51 valori equispaziati tra $-3$ e $3$ (passo 0,12), e target sulla retta più rumore gaussiano di varianza unitaria.

![](imgs/Pasted%20image%2020261125005128.png)

**Creare e addestrare il regressore** (`net1 = newlind(x,t)`, **metodo #1**, diretto):

```matlab
%% create the linear model and train it
if (1) % optimized solution: inversion of the data matrix
    net1 = newlind(x,t);
else % not optimized: the general learning of non-linear networks
    net1 = linearlayer;
    net1 = configure(net1,[0],0);
    % a little help to tune the initial points
    net1.IW{1,1} = 3.1;
    net1.b{1} = -3.1;
    [net1, tr] = train(net1,x,t);   % WARNING: no train/validation partition (cheating)
    figure(h2); plotperform(tr)     % needs more tuning
end
```

![](imgs/Pasted%20image%2020261125005204.png)

| Ramo | Che cosa fa |
|---|---|
| `if (1)` → `newlind(x,t)` | progetta lo strato lineare **risolvendo direttamente** i minimi quadrati (inversione della matrice dei dati): è la **forma chiusa** del §6.8 |
| `else` → `linearlayer` + `train` | crea uno strato lineare generico e lo addestra con l’**apprendimento iterativo** delle reti (la discesa del gradiente); i pesi iniziali vengono impostati a mano (3,1 e −3,1) |

> ⚠️ **I commenti dello script.** «*no train/validation partition (cheating)*»: il ramo iterativo usa tutti i dati per addestrare, senza validazione, ed è dichiarato dallo stesso autore un «imbroglio» didattico. `if (1)` è un modo rapido di scegliere sempre il primo ramo: cambiando in `if (0)` si prova il secondo.

**Il regressore creato**: uscita $=w\cdot\text{input}+b$.

<!-- SOURCE_VISUAL id="IS17-V31" source="L17-web.pdf" page="75" type="schermata" description="Rappresentazione della rete lineare creata da newlind" reason="Schema della rete visibile solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125005218.png)

**Accedere ai parametri.** In MATLAB il peso è `net1.IW{1,1}` (*Input Weights*) e il bias `net1.b{1}`.

<!-- SOURCE_VISUAL id="IS17-V32" source="L17-web.pdf" page="76" type="schermata" description="Lettura del peso e del bias della rete lineare in MATLAB" reason="Valori visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125005232.png)

**Elaborare gli input e disegnare i risultati**: `y1 = net1(x)` applica la rete agli input; si disegnano i punti e la retta.

<!-- SOURCE_VISUAL id="IS17-V33" source="L17-web.pdf" page="77" type="grafico" description="Punti di training e retta prodotta dalla rete lineare" reason="Grafico visibile solo nella figura" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125005250.png)

#### **7.3. Valutare l’accuratezza**

$$\text{MSE}=\frac1n\sum_{i=1}^{n}(\hat{y}_i-y_i)^2,\qquad R^2=1-\frac{\sum_i(y_i-\hat{y}_i)^2}{\sum_i(y_i-\bar{y})^2}.$$

> ⚠️ **ATTENZIONE.** Queste misure riguardano il **dataset di TRAINING**. Vanno **rifatte su un dataset di TEST**.

<!-- SOURCE_VISUAL id="IS17-V34" source="L17-web.pdf" page="78" type="schermata" description="Calcolo di MSE e R^2 della rete lineare sul training in MATLAB" reason="Codice e valori visibili solo nella schermata" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125005305.png)

#### **7.4. Proviamo una rete neurale feed-forward!**

| Istruzione | Ruolo |
|---|---|
| `net2 = feedforwardnet(3)` | inizializzazione con **3 neuroni** nascosti |
| `net2 = train(net2,x,t);` | addestramento |
| `y2 = net2(x)` | esecuzione |

<!-- SOURCE_VISUAL id="IS17-V35" source="L17-web.pdf" page="79" type="schermata" description="Rete feed-forward con 3 neuroni in MATLAB" reason="Schermata visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010206.png)

```matlab
%% Occam's razor: start from a small network
net2 = feedforwardnet(3);
net2.divideParam.trainRatio = 1.0;
net2.divideParam.testRatio = 0.0;
net2.divideParam.valRatio  = 0;
[net2, tr] = train(net2,x,t);
h2 = figure;
plotperform(tr)
view(net2)
y2 = net2(x);
perf = perform(net2,y2,t) % error on **training**
```

<!-- SOURCE_VISUAL id="IS17-V36" source="L17-web.pdf" page="80" type="schermata" description="Addestramento della rete da 3 neuroni: finestra di training, grafico delle prestazioni e struttura della rete" reason="Schermate visibili solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010236.png)

> 💡 **Che cosa fanno le righe `divideParam` (nota didattica).** Per default `feedforwardnet` divide i dati in 70% training, 15% validation (per l’early stopping) e 15% test (Lezione 20). Qui le tre righe mettono il 100% nel training: la rete non ha alcun controllo dell’overfitting, ed è voluto per mostrarlo. **Parametri**: con 1 input, 3 neuroni nascosti e 1 output, la rete ha $3\cdot 1+3=6$ parametri nello strato nascosto e $3\cdot 1+1=4$ in quello di uscita, **10 in totale** contro 51 punti.

#### **7.5. Progetto sbagliato: 100 neuroni con 51 punti!**

```matlab
%% real overfitting
% a deliberately oversized network, to show overfitting
net3 = feedforwardnet(100);
net3.divideParam.trainRatio = 1.0;
net3.divideParam.testRatio = 0.0;
net3.divideParam.valRatio  = 0;
[net3, tr] = train(net3,x,t);
figure(h2); plotperform(tr)
view(net3)
y3 = net3(x);
```

<!-- SOURCE_VISUAL id="IS17-V37" source="L17-web.pdf" page="81" type="schermata" description="Rete feed-forward da 100 neuroni sovradimensionata in MATLAB" reason="Schermata visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010253.png)

> 💡 **Il conto dei parametri (nota didattica).** Con 100 neuroni: $100\cdot 1+100=200$ parametri nello strato nascosto e $100+1=101$ in uscita, **301 parametri** per 51 punti: circa sei volte più gradi di libertà che dati.

**Costruiamo un test set…** Si **iniettano nella rete `net3` (100 neuroni) molti più punti $x$**:

```matlab
%% test points
x_bis = 0:0.0005:10;
y3_bis = net3(x_bis);

perf = perform(net3,y3,t) % error on **training**

subplot(1,2,2)
plot(x,t,'o');
title('PROBLEM 01 liner data plus noise -- FFNN 100 neurons');
xlabel('x');
ylabel('t');
hold on
plot(x_bis,y3_bis,'.k');
legend('train data','nn100 on validation data')
```

La stringa del titolo del grafico è conservata come nello script originale (compare nel grafico successivo, con il refuso «*liner*» per «*linear*»).

![](imgs/Pasted%20image%2020261125010405.png)


> ⚠️ **I punti di test escono dall’intervallo di training (nota didattica).** I dati di training hanno $x\in[-3,3]$, mentre `x_bis` va da 0 a 10: da 0 a 3 la rete interpola, da 3 a 10 **estrapola** in una regione mai vista. Il comportamento caotico della rete grande si vede soprattutto lì, ma anche tra i punti di training la curva oscilla. È un test della generalizzazione e insieme dell’estrapolazione, due cose diverse.

#### **7.6. …e abbiamo fatto overfitting (molto!)**

| MSE (unità di $y$ al quadrato) | training (51 punti) | test (2000 punti nuovi) |
|---|---:|---:|
| regressore lineare | 0,91 | **1,02** |
| FFNN, 3 neuroni | 0,76 | 1,16 |
| FFNN, 100 neuroni | **0,20** | **1,66** |

<!-- SOURCE_VISUAL id="IS17-V38" source="L17-web.pdf" page="83" type="grafico" description="Uscita della rete da 100 neuroni sui punti di training e sui punti nuovi: overfitting" reason="Curve visibili solo nel grafico" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

Replica in Python (scikit-learn, seme 1, $t=2x-5+$ rumore gaussiano unitario, $x\in[-3,3]$), non l’esecuzione MATLAB della lezione: **conta solo la tendenza**.

> 📌 **Lettura della tabella.** Più il modello è complesso, **più l’errore di training scende e più quello di test sale**. Il regressore lineare, il più semplice e coerente con la legge vera dei dati, ha il **miglior errore di test** (1,02, vicino alla varianza del rumore, 1). La rete da 100 neuroni ha l’errore di training più basso (0,20) e il peggiore sul test (1,66): ha imparato il rumore. È il rasoio di Occam in numeri.

**Da ricordare la prossima volta che si inizializza un modello con #DoF ≫ #campioni…**

<!-- SOURCE_VISUAL id="IS17-V39" source="L17-web.pdf" page="84" type="figura" description="Avvertimento sui modelli con molti più gradi di libertà che campioni" reason="Figura visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010453.png)

#### **7.7. Python in Colab: il file e i dati**

1) Aprire `laboratory_COLAB_linear_regressors.ipynb`, seguire la slide successiva; 2) poi lanciare lo script.

```python
rng = np.random.RandomState(1)
num_points = 50
x = 10 * rng.rand(num_points)
t = 2 * x - 5 + rng.randn(num_points)
plt.scatter(x, t);
```

**Output**: i dati, $x$ (50 valori in $[0,10]$) e $t$ (50 target). **Modello da trovare**: $z=wx+b$. Dati sparsi attorno a una retta con **pendenza $w=2$** e **intercetta $b=-5$**, più **rumore gaussiano** (varianza unitaria). **Stessa legge** dell’esercizio MATLAB ($t=2x-5+$ rumore): le due toolbox sono simmetriche.

<!-- SOURCE_VISUAL id="IS17-V40" source="L17-web.pdf" page="85" type="grafico" description="Scatter dei 50 punti generati in Colab" reason="Grafico visibile solo nella slide" -->
<!-- INSERT INSTRUCTOR SLIDE/DIAGRAM HERE -->

![](imgs/Pasted%20image%2020261125010511.png)

#### **7.8. Python: creare, disegnare e valutare il regressore**

```python
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error
from sklearn.metrics import r2_score

# 1) creare e addestrare
model = LinearRegression(fit_intercept=True)
model.fit(x[:, np.newaxis], t)
# 2) previsioni sulle x di training
z = model.predict(x[:, np.newaxis])
# 3) grafico: 1000 punti per disegnare la retta
xfit = np.linspace(0, 10, 1000)
z_fit = model.predict(xfit[:, np.newaxis])
plt.scatter(x, t); plt.plot(xfit, z_fit);
# 4) accuratezza
mse = mean_squared_error(z, t)
rmse = mse ** 0.5
R2 = r2_score(z, t)
```

1. **Addestrare**: `LinearRegression` di scikit-learn interpola $z=wx+b$ (`np.newaxis` aggiunge la dimensione delle feature richiesta dalla funzione: da un vettore di 50 a una matrice $50\times 1$).
2. **Prevedere**: $z$ per ciascuno dei 50 input.
3. **Disegnare**: i punti e la retta interpolata.
4. **Accuratezza** (training): **MSE = 0,818** (unità di $t$ al quadrato), **RMSE = 0,905** (unità di $t$), **$R^2$ = 0,979**.

![](imgs/Pasted%20image%2020261125010543.png)

**Risultato** (esecuzione reale del codice): $w=2{,}03$, $b=-5{,}00$.

> ⚠️ **È l’accuratezza di TRAINING**; per l’accuratezza reale si costruisce un test set. I numeri differiscono da quelli MATLAB perché i dati sono diversi (50 punti in $[0,10]$ contro 51 in $[-3,3]$).

> ⚠️ **L’ordine degli argomenti di `r2_score` (nota didattica).** La firma è `r2_score(y_true, y_pred)`: il codice passa `r2_score(z, t)`, cioè le previsioni al posto dei valori veri. Il MSE è simmetrico e non cambia, ma l’$R^2$ **no**, perché il denominatore usa la varianza del primo argomento: la forma corretta è `r2_score(t, z)`. Con dati in $[0,10]$ la varianza di $t$ è grande rispetto al rumore, quindi i due valori restano vicini, ma l’abitudine va corretta. La stessa attenzione vale per `mean_squared_error(t, z)`, anche se lì l’ordine è indifferente.

> 💡 **Perché $R^2$ = 0,979 qui e 0,94 in MATLAB (nota didattica).** Il rumore ha la stessa varianza (1), ma in Colab $x$ varia in $[0,10]$, quindi $t=2x-5$ varia tra $-5$ e $15$: la varianza totale di $t$ è molto più grande. Lo stesso rumore è una frazione più piccola della variazione totale, e $R^2$ sale. L’$R^2$ dipende quindi anche dall’intervallo dei dati, non solo dalla qualità del modello: un motivo in più per riportare anche RMSE e MAE, che hanno le unità di $y$.

---

### **8. Punti principali**


> ✅ **Punti principali della lezione.**
> - **Memoria e gestione del contesto degli agenti.**
> - **Caricamento, EDA e grafici in MATLAB.**
> - **Un modello nuovo?** Che cosa sapere? Che cosa fare?
> - **Il compromesso (dilemma) bias/varianza.**
> - **Regressione lineare**: RSS, discesa del gradiente $\beta\leftarrow\beta-\eta\nabla$ e il **learning rate** $\eta$.
> - **$R^2$ va riportato, ma non basta: disegnare i dati.**
> - **I primi modelli in codice**: `newlind`, `feedforwardnet`, `LinearRegression` di scikit-learn.
> - **L’accuratezza va misurata su un test set, non sul training set.**
> - **Overfitting**: **#DoF ≫ #campioni** dà un **adattamento perfetto sul training** e un **errore di test cattivo**.

---

### **9. Appendice: media e deviazione standard della k-FCV — la scomposizione bias-varianza (non d’esame)**


Sia $y=f(x)+\varepsilon$, con $\mathbb{E}[\varepsilon]=0$ e $\text{Var}[\varepsilon]=\sigma^2_{\text{rumore}}$; $\hat{y}$ è la previsione del modello addestrato su un dataset $D$.

$$\mathbb{E}_{D,\varepsilon}\big[(y-\hat{y})^2\big]=\mathbb{E}_\varepsilon[\varepsilon^2]+\mathbb{E}_D\big[(f(x)-\hat{y})^2\big]\tag{1}$$

$$=\sigma^2_{\text{rumore}}+\big(\mathbb{E}_D[\hat{y}]-f(x)\big)^2+\mathbb{E}_D\big[(\hat{y}-\mathbb{E}_D[\hat{y}])^2\big]\tag{2}$$

$$=\underbrace{\sigma^2_{\text{rumore}}}_{\text{irriducibile}}+\underbrace{\text{Bias}[\hat{y}]^2}_{\text{si riduce con un modello migliore}}+\underbrace{\text{Var}[\hat{y}]}_{\text{si riduce con un modello più semplice}}\tag{3}$$

**Passi**: (1) il rumore è indipendente da $\hat{y}$, quindi il suo termine si separa; (2) si aggiunge e si sottrae $\mathbb{E}_D[\hat{y}]$, e il termine incrociato è zero; (3) si danno i nomi ai termini. $D$ indica le diverse partizioni del dataset (per esempio i $k$ fold della validazione incrociata); $\mathbb{E}_D[\bullet]$ è la media su di esse, quindi $\mathbb{E}_D[\hat{y}]$ è la **previsione media**.

> 💡 **Perché i termini incrociati si annullano (nota didattica).** In (1): $(y-\hat{y})^2=(f-\hat{y}+\varepsilon)^2=(f-\hat{y})^2+2\varepsilon(f-\hat{y})+\varepsilon^2$, e il valore atteso del termine centrale è $2\,\mathbb{E}[\varepsilon]\,\mathbb{E}[f-\hat{y}]=0$ perché $\varepsilon$ ha media nulla ed è indipendente da $\hat{y}$. In (2): posto $\bar{y}=\mathbb{E}_D[\hat{y}]$, $(f-\hat{y})^2=\big((f-\bar{y})+(\bar{y}-\hat{y})\big)^2$, e il termine incrociato $2(f-\bar{y})\,\mathbb{E}_D[\bar{y}-\hat{y}]$ è zero perché $\mathbb{E}_D[\hat{y}]=\bar{y}$.

La k-FCV è una **simulazione Monte Carlo dell’errore di previsione**, quindi è più intuitivo **monitorare l’errore medio e la sua deviazione standard**:

$$\mathbb{E}\big[(y-\hat{y})^2\big]=\text{Bias}^2(x)+\text{Var}(x)+\sigma^2_{\text{rumore}}.$$

La media degli errori sui fold stima l’errore atteso; la loro deviazione standard dà un’indicazione della componente di varianza, cioè di quanto il modello dipende dalla particolare partizione (Lezione 14, §6.9).

---

### **10. Approfondimento — Iris in MATLAB (dall’edizione precedente e dagli script)**


L’edizione precedente caricava ed esplorava Iris in MATLAB prima della LDA; il materiale resta utile per leggere gli script della cartella.

**Caricare il dataset**:

```matlab
url = 'https://archive.ics.uci.edu/ml/machine-learning-databases/iris/iris.data';
filename = 'iris.dat';
outfilename = websave(filename,url);
fid = fopen(outfilename, 'r');
S = textscan(fid,'%f%f%f%f%s','delimiter',',');
fclose(fid);
meas = [S{1}, S{2}, S{3}, S{4}];   % meas = measurements
species = S{5};
% Equivalent to ">>load fisheriris"
```

**Creare le strutture dati**: la matrice delle **feature** `P` e il vettore delle **etichette** `T`.

```matlab
P = meas;                          % Problem (X in seguito)
T = zeros(size(species));          % Targets (Y in seguito)
for i = 1 : size(T,1)
    switch species{i}
        case 'setosa'
            T(i) = 0;
        case 'versicolor'
            T(i) = 1;
        case 'virginica'
            T(i) = 2;
    end
end
figure; bar(T); xlabel('Sample'); ylabel('Class')
```

> ⚠️ **Nomi delle specie diversi (nota didattica).** Nel file UCI scaricato con `websave` i nomi delle specie sono `'Iris-setosa'`, `'Iris-versicolor'`, `'Iris-virginica'`, mentre `load fisheriris` usa `'setosa'`, `'versicolor'`, `'virginica'`. Se si combinano il file UCI e i `case` senza prefisso, **nessuna condizione corrisponde** e $T$ resta interamente a zero; il grafico `bar(T)` dopo il ciclo permette di rilevare l’errore. È un esempio perfetto della regola d’oro della Lezione 16: con un dataset noto, un risultato strano (tutte le classi uguali a 0) rivela il bug.

**`plotmatrix` contro `pairplot`.** `plotmatrix(P)` disegna le coppie di feature **senza etichette**: è un’analisi **non supervisionata**. Il `pairplot` di seaborn colorato per specie usa le **etichette**: è un’analisi **supervisionata**. Lo stesso contrasto separa PCA (non supervisionata) e LDA (supervisionata) nel §4.

---

### **11. Distinzioni da non confondere**


| Coppia | Differenza chiave |
|---|---|
| **contesto** / **memoria persistente** | stato inviato a ogni chiamata, poi scartato / file e dati conservati dall’applicazione |
| **finestra dichiarata** / **contesto utile** | limite tecnico / qualità che può degradare prima (context rot) |
| **contesto del turno $N$** / **token totali inviati** | cresce linearmente ($350N-100$) / cresce quadraticamente |
| **offloading** / **RAG** | dati fuori dal prompt, letti su richiesta / recupero dei chunk pertinenti da un indice |
| **pruning** / **riassunto** | elimina ciò che non serve / comprime ciò che serve, con rischio di perdere vincoli |
| **selezione degli strumenti** / **quarantena** | meno strumenti visibili / sottocompiti in contesti separati |
| **accuratezza di training** / **di validazione incrociata** | 100% per l’albero profondo / 87,1% medio, tra 72,2% e 100% |
| **learner perfetto** / **buon modello** | errore di training zero / basso errore su dati nuovi |
| **bias alto** / **varianza alta** | modello troppo semplice, errore sistematico / modello troppo complesso, instabile |
| **asse della complessità** / **asse delle epoche** | curva bias-varianza / curva di addestramento |
| **PCA** / **LDA** | massima varianza, senza etichette / massima separazione, con etichette |
| **`resubPredict`** / **validazione** | test sul training, ottimistico / test su dati mai visti |
| **regressore lineare** / **classificatore lineare** | restituisce $z$ / restituisce $\text{sgn}(z)$ |
| **discesa del gradiente** / **forma chiusa** | iterativa, scala a dati enormi, approssimata / esatta, richiede l’inversione di $X^\top X$ |
| **learning rate grande** / **piccolo** | divergenza / convergenza lenta |
| **$R^2$ uguale** / **dati uguali** | quartetto di Anscombe: non implica dati simili |
| **`newlind`** / **`train` iterativo** | minimi quadrati diretti / addestramento con la discesa del gradiente |
| **`r2_score(t, z)`** / **`r2_score(z, t)`** | ordine corretto (veri, previsti) / ordine invertito, $R^2$ diverso |
| **FFNN 3 neuroni** / **100 neuroni** su 51 punti | 10 parametri / 301 parametri, overfitting |
| **interpolazione** / **estrapolazione** | dentro l’intervallo di training / fuori, comportamento imprevedibile |
